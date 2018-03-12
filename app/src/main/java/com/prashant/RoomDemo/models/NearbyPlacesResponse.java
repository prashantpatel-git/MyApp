package com.prashant.RoomDemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearbyPlacesResponse {

    @SerializedName("results")
    @Expose
    private List<NearbyPlace> mPlaces;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("error_message")
    @Expose
    private String errorMsg = "";

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<NearbyPlace> getmPlaces() {
        return mPlaces;
    }

    public void setmPlaces(List<NearbyPlace> mPlaces) {
        this.mPlaces = mPlaces;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

}
