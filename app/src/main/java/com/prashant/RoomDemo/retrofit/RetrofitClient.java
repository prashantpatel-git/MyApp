package com.prashant.RoomDemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prashant on 11/03/18.
 */

public class RetrofitClient {

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/";

    private static Retrofit retrofit;
    private static RetrofitApiInterface retrofitApiInterface;

    private RetrofitClient() {

    }

    private static Retrofit getRetrofitClient() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // <- add this
                    .client(createOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitApiInterface getRetrofitInterface() {
        if (retrofitApiInterface == null)
            retrofitApiInterface = getRetrofitClient().create(RetrofitApiInterface.class);

        return retrofitApiInterface;
    }

    private static OkHttpClient createOkHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        return okHttpClient;
    }
}
