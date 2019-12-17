package edu.upb.pumatiti.preparation.models.ui;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StopMarker {
    private String uuid;
    private String name;
    private String address;
    private LatLng latLng;
    private MarkerOptions marker;
    private int iconResource;

    public StopMarker(String uuid, String name, String address, LatLng latLng, int iconResource) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.latLng = latLng;
        this.iconResource = iconResource;
        this.marker = new MarkerOptions()
                .title(name)
                .snippet(address)
                .position(latLng);
    }

    public StopMarker(String uuid, String name, String address, LatLng latLng, MarkerOptions marker) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.latLng = latLng;
        this.marker = marker;
    }



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public MarkerOptions getMarker() {
        return marker;
    }

    public void setMarker(MarkerOptions marker) {
        this.marker = marker;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
