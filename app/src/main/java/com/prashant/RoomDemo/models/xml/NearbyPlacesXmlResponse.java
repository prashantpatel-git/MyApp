package com.prashant.RoomDemo.models.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "PlaceSearchResponse", strict = false)
public class NearbyPlacesXmlResponse {

    @ElementList(inline = true)
    private List<NearbyPlaceXml> results;

    @Element(name = "status", required = false)
    private String mStatus;

    @Element(name = "error_message", required = false)
    private String errorMsg = "";

    public List<NearbyPlaceXml> getResults() {
        return results;
    }

    public void setResults(List<NearbyPlaceXml> results) {
        this.results = results;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
