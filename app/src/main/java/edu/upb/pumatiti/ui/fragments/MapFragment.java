package edu.upb.pumatiti.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.ui.BusMarker;
import edu.upb.pumatiti.models.ui.StopMarker;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.ui.fragments.base.BaseFragment;
import edu.upb.pumatiti.utils.Constants;
import edu.upb.pumatiti.viewmodel.MainViewModel;

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private static final String LOG = MapFragment.class.getSimpleName();

    private GoogleMap map;
    private MapView mapView;

    //Panels
    private LinearLayout headerLinearLayout;
    private LinearLayout footerLinearLayout;

    private MainViewModel viewModel;

    private List<Marker> busesMarkersList = new ArrayList<>();
    private List<Marker> stopsMarkersList = new ArrayList<>();


    public MapFragment(UserLogged userLogged) {
        super(userLogged);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate: Mostrar el XML del layout y vincular con sus variables
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initUI(view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (userLogged != null) {
            switch (userLogged.getUserType()) {
                case REGULAR_USER:
                    headerLinearLayout.setVisibility(View.VISIBLE);
                    footerLinearLayout.setVisibility(View.GONE);
                    break;
                case HOST:
                    headerLinearLayout.setVisibility(View.GONE);
                    footerLinearLayout.setVisibility(View.VISIBLE);
                    break;
                case ANALYST:
                    headerLinearLayout.setVisibility(View.GONE);
                    footerLinearLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }

    private void initUI(View parent) {
        mapView = parent.findViewById(R.id.map);
        headerLinearLayout = parent.findViewById(R.id.headerLinearLayout);
        footerLinearLayout = parent.findViewById(R.id.footerLinearLayout);
    }

    public void locationClick() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        LatLng lapazUpb = new LatLng(Constants.DEFAULT_LAT, Constants.DEFAULT_LNG);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lapazUpb, Constants.DEFAULT_ZOOM));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // and so on
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.subscribeToBuses().observe(this, this::displayBuses);
        viewModel.subscribeToStops().observe(this, this::displayStops);
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.subscribeToBuses().removeObservers(this);
        viewModel.subscribeToStops().removeObservers(this);
    }

    private void displayBuses(List<BusMarker> buses) {
        clearMarkers(stopsMarkersList);
        for (BusMarker marker : buses) {
            stopsMarkersList.add(map.addMarker(marker.getMarker()));
        }
    }

    private void displayStops(List<StopMarker> stops) {
        clearMarkers(stopsMarkersList);
        for (StopMarker marker : stops) {
            stopsMarkersList.add(map.addMarker(marker.getMarker()));
        }
    }

    private void clearMarkers(List<Marker> markers) {
        for (Marker marker : markers) {
            marker.remove();
        }
    }
}
