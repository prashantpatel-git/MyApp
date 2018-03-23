package com.prashant.RoomDemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.prashant.RoomDemo.Utils.AppPreferences;
import com.prashant.RoomDemo.Utils.GlobalVariables;
import com.prashant.RoomDemo.models.Geometry;
import com.prashant.RoomDemo.models.NearbyPlace;
import com.prashant.RoomDemo.models.NearbyPlacesResponse;
import com.prashant.RoomDemo.models.xml.NearbyPlacesXmlResponse;
import com.prashant.RoomDemo.retrofit.RetrofitClient;
import com.prashant.RoomDemo.room.MyAppDatabase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    Location lastLocation;
    Button getPlacesBtn;
    TextView locationTv;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlacesBtn = findViewById(R.id.main_btn_nearby_places);
        locationTv = findViewById(R.id.main_tv_location);

        //createFusedLocationClient();

        getPlacesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationUpdate();
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            lastLocation = location;
                            AppPreferences.getInstance().setLocation(location);

                            //getNearByPlaces();
                            getNearByPlacesXml();
                            //startActivity(new Intent(MainActivity.this, NearByPlacesActivity.class));
                        }

                    }
                });
            }
        });

        findViewById(R.id.main_btn_teachers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TeacherListActivity.class));
            }
        });
    }

    private void startLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MainActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();

            return;
        }

        createFusedLocationClient();
        fusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), getLocationCallback(), null);
    }

    private void stopLocationUpdate() {
        fusedLocationProviderClient.removeLocationUpdates(getLocationCallback());
    }

    private LocationRequest getLocationRequest() {

        if (locationRequest == null) {
            locationRequest = new LocationRequest()
                    .setFastestInterval(5000)
                    .setInterval(5000)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }

        return locationRequest;
    }

    private LocationCallback getLocationCallback() {
        if (locationCallback == null) {
            locationCallback = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null && locationResult.getLastLocation() != null) {
                        lastLocation = locationResult.getLastLocation();
                        locationTv.setText(lastLocation.getLatitude() + ", " + lastLocation.getLongitude());
                        AppPreferences.getInstance().setLocation(locationResult.getLastLocation());
                    }
                    Log.d("Location", locationTv.getText().toString());

                }

                @Override
                public void onLocationAvailability(LocationAvailability locationAvailability) {
                    super.onLocationAvailability(locationAvailability);
                }
            };
        }

        return locationCallback;
    }

    private FusedLocationProviderClient createFusedLocationClient() {

        if (fusedLocationProviderClient == null)
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        return fusedLocationProviderClient;
    }

    private void getNearByPlaces() {
        io.reactivex.Observable<NearbyPlacesResponse> nearbyResponseObservable
                = RetrofitClient.getRetrofitInterface().getNearbyPlaces(
                AppPreferences.getInstance().getLatitude() + "," + AppPreferences.getInstance().getLongitude(),
                "bank",
                5000,
                GlobalVariables.API_KEY
        );

        DisposableObserver<NearbyPlacesResponse> disposableObserver = new DisposableObserver<NearbyPlacesResponse>() {
            @Override
            public void onNext(NearbyPlacesResponse nearbyResponse) {

                if (nearbyResponse != null) {

                    if (nearbyResponse.getmStatus().equals("OK")) {
                        insertPlaces(nearbyResponse.getmPlaces());
                    } else {
                        Log.d("Nearbyplaces", nearbyResponse.getErrorMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Nearbyplaces", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        compositeDisposable.add(disposableObserver);

        nearbyResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

    }

    private void getNearByPlacesXml() {
        io.reactivex.Observable<NearbyPlacesXmlResponse> nearbyResponseObservable
                = RetrofitClient.getRetrofitInterface().getNearbyPlacesXml(
                AppPreferences.getInstance().getLatitude() + "," + AppPreferences.getInstance().getLongitude(),
                "bank",
                5000,
                GlobalVariables.API_KEY
        );

        DisposableObserver<NearbyPlacesXmlResponse> disposableObserver = new DisposableObserver<NearbyPlacesXmlResponse>() {
            @Override
            public void onNext(NearbyPlacesXmlResponse nearbyResponse) {

                if (nearbyResponse != null) {

                    if (nearbyResponse.getmStatus().equals("OK")) {
                        //insertPlaces(nearbyResponse.getmPlaces());
                    } else {
                        Log.d("Nearbyplaces", nearbyResponse.getErrorMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Nearbyplaces", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        compositeDisposable.add(disposableObserver);

        nearbyResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

    }

    private void insertPlaces(final List<NearbyPlace> placeList) {

        DisposableObserver<Boolean> disposableObserver = new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean isSuccess) {
                if (isSuccess) {
                    getPlacesFromDb();
                    Log.d("InsertPlace", "success");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d("InsertPlace", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("InsertPlace", "Complete");

            }
        };
        compositeDisposable.add(disposableObserver);

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                try {
                    MyAppDatabase.getInstance().roomDao().insertAllPlaces(placeList);
                    e.onNext(true);
                    e.onComplete();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onNext(false);
                    e.onError(e1.getCause());
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }


    private void getPlacesFromDb() {
        DisposableObserver<List<NearbyPlace>> disposableObserver = new DisposableObserver<List<NearbyPlace>>() {
            @Override
            public void onNext(List<NearbyPlace> places) {
                if (places != null) {

                    Log.d("InsertPlace", "success");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d("InsertPlace", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("InsertPlace", "Complete");

            }
        };
        compositeDisposable.add(disposableObserver);

        Observable.create(new ObservableOnSubscribe<List<NearbyPlace>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NearbyPlace>> e) throws Exception {
                try {
                    List<NearbyPlace> places = MyAppDatabase.getInstance().roomDao().getPlaces();
                    e.onNext(places);
                    e.onComplete();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onError(e1.getCause());
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //startLocationUpdate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stopLocationUpdate();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        compositeDisposable = null;

        super.onDestroy();
    }

    public class Async extends AsyncTask<Geometry, Void, Void> {

        @Override
        protected Void doInBackground(Geometry... geometries) {
            return null;
        }
    }
}

