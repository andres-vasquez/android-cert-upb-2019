package edu.upb.pumatiti.preparation.models.ui;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BusMarker {
    private String title;
    private LatLng latLng;
    private MarkerOptions marker;
    private int iconResource;

    public BusMarker(String title, LatLng latLng, MarkerOptions marker) {
        this.title = title;
        this.latLng = latLng;
        this.marker = marker;
    }

    public BusMarker(String title, LatLng latLng, int iconResource) {
        this.title = title;
        this.latLng = latLng;
        this.iconResource = iconResource;
        this.marker = new MarkerOptions().title(title).position(latLng);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
