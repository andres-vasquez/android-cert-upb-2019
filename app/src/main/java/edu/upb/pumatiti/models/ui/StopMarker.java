package edu.upb.pumatiti.models.ui;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StopMarker {
    private String name;
    private String address;
    private LatLng latLng;
    private MarkerOptions marker;

    public StopMarker(String name, String address, LatLng latLng, MarkerOptions marker) {
        this.name = name;
        this.address = address;
        this.latLng = latLng;
        this.marker = marker;
    }

    public StopMarker(String name, String address, LatLng latLng, int iconResource) {
        this.name = name;
        this.address = address;
        this.latLng = latLng;
        this.marker = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromResource(iconResource));
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
}
