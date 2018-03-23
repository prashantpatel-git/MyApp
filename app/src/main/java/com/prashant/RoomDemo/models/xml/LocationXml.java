package com.prashant.RoomDemo.models.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Prashant on 12/03/18.
 */

@Root(name = "location", strict = false)
public class LocationXml {

    @Element(name = "lat")
    private Double mLat;

    @Element(name = "lng")
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

