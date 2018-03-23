package com.prashant.RoomDemo.models.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "result", strict = false)
public class NearbyPlaceXml {

    @Path("geometry")
    @Element
    private LocationXml location;

    @Element
    private String icon;

    @Element
    private String name;

    @Element
    private String id;

    public LocationXml getLocation() {
        return location;
    }

    public void setLocation(LocationXml location) {
        this.location = location;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
