package edu.upb.pumatiti.models.ui;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.upb.pumatiti.R;

public class BusMarker {
    private String title;
    private LatLng latLng;
    private MarkerOptions marker;

    public BusMarker(String title, LatLng latLng, MarkerOptions marker) {
        this.title = title;
        this.latLng = latLng;
        this.marker = marker;
    }

    public BusMarker(String title, LatLng latLng, int iconResource) {
        this.title = title;
        this.latLng = latLng;
        this.marker = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromResource(iconResource));
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
}
