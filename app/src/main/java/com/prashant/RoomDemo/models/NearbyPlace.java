package com.prashant.RoomDemo.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class NearbyPlace {

    @Embedded(prefix = "geo")
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("icon")
    @Expose
    private String icon;

    private String xyzabc;
    /*@PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    private long id;*/

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NonNull
    private String placeId = "";

    @SerializedName("name")
    @Expose
    private String name;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

  /*  public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public class Geometry {

        @Embedded(prefix = "loc")
        @Ignore
        @SerializedName("location")
        private Location mLocation;

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Location getmLocation() {
            return mLocation;
        }

        public void setmLocation(Location mLocation) {

            this.mLocation = mLocation;
        }


        public class Location {

            @SerializedName("lat")
            private Double mLat;
            @SerializedName("lng")
            private Double mLng;

            public Double getLat() {
                return mLat;
            }

            public void setLat(Double lat) {
                mLat = lat;
            }

            public Double getLng() {
                return mLng;
            }

            public void setLng(Double lng) {
                mLng = lng;
            }

        }
    }*/


}
