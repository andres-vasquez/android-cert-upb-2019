package edu.upb.pumatiti.preparation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.upb.pumatiti.preparation.R;
import edu.upb.pumatiti.preparation.models.repository.Bus;
import edu.upb.pumatiti.preparation.models.ui.BusMarker;
import edu.upb.pumatiti.preparation.models.ui.StopMarker;
import edu.upb.pumatiti.preparation.models.ui.UserLogged;
import edu.upb.pumatiti.preparation.ui.fragments.base.BaseFragment;
import edu.upb.pumatiti.preparation.utils.Constants;
import edu.upb.pumatiti.preparation.utils.IconUtils;
import edu.upb.pumatiti.preparation.viewmodel.MainViewModel;

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private static final String LOG = MapFragment.class.getSimpleName();

    private GoogleMap map;
    private MapView mapView;

    //Panels
    private LinearLayout headerLinearLayout;
    private LinearLayout footerLinearLayout;
    private ImageView playImageView;
    private ImageView pauseImageView;
    private TextView plateTextView;
    private TextView routeTextView;

    private MainViewModel viewModel;

    private String busUuid;
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
        initMap(savedInstanceState);
        initEvents();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (userLogged != null) {
            switch (userLogged.getUserType()) {
                case REGULAR_USER:
                    headerLinearLayout.setVisibility(View.VISIBLE);
                    footerLinearLayout.setVisibility(View.GONE);
                    viewModel.getRouteDetails("route-01");
                    break;
                case HOST:
                    headerLinearLayout.setVisibility(View.GONE);
                    footerLinearLayout.setVisibility(View.VISIBLE);
                    viewModel.getRouteDetails("route-01");
                    busUuid = getFakeBusUuid();
                    viewModel.getMyBus(busUuid);
                    break;
                case ANALYST:
                    headerLinearLayout.setVisibility(View.GONE);
                    footerLinearLayout.setVisibility(View.GONE);
                    viewModel.getRouteDetails(null);
                    break;
            }
        }
    }

    private String getFakeBusUuid() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(Constants.DEFAULT_BUS_MAX_ID);
        if (randomInt == 0) {
            return getFakeBusUuid();
        } else {
            return Constants.DEFAULT_BUS_ID + String.format("%02d", randomInt);
        }
    }

    private void initUI(View parent) {
        mapView = parent.findViewById(R.id.map);
        headerLinearLayout = parent.findViewById(R.id.headerLinearLayout);
        footerLinearLayout = parent.findViewById(R.id.footerLinearLayout);
        playImageView = parent.findViewById(R.id.playImageView);
        pauseImageView = parent.findViewById(R.id.pauseImageView);
        plateTextView = parent.findViewById(R.id.plateTextView);
        routeTextView = parent.findViewById(R.id.routeTextView);
    }

    private void initMap(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);
    }

    private void initEvents() {
        playImageView.setOnClickListener(view -> {
            viewModel.startTimer();
            playImageView.setVisibility(View.GONE);
            pauseImageView.setVisibility(View.VISIBLE);
        });
        pauseImageView.setOnClickListener(view -> {
            playImageView.setVisibility(View.VISIBLE);
            pauseImageView.setVisibility(View.GONE);
            viewModel.stopTimer();
        });
    }

    public void locationClick() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setPadding(0, 0, 0, mapView.getHeight());

        LatLng lapazUpb = new LatLng(Constants.DEFAULT_LAT, Constants.DEFAULT_LNG);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lapazUpb, Constants.DEFAULT_ZOOM));
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.subscribeToBuses().observe(this, this::displayBuses);
        viewModel.subscribeToStops().observe(this, this::displayStops);
        viewModel.subscribeToMyBus().observe(this, this::displayMyBusInfo);
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.subscribeToBuses().removeObservers(this);
        viewModel.subscribeToStops().removeObservers(this);
        viewModel.subscribeToMyBus().removeObservers(this);
    }

    private void displayBuses(List<BusMarker> buses) {
        clearMarkers(busesMarkersList);
        for (BusMarker marker : buses) {
            marker.getMarker().icon(IconUtils.vectorAsIcon(context, marker.getIconResource()));
            busesMarkersList.add(map.addMarker(marker.getMarker()));
        }
    }

    private void displayStops(List<StopMarker> stops) {
        clearMarkers(stopsMarkersList);
        for (StopMarker marker : stops) {
            marker.getMarker().icon(IconUtils.vectorAsIcon(context, marker.getIconResource()));
            stopsMarkersList.add(map.addMarker(marker.getMarker()));
        }
    }

    private void clearMarkers(List<Marker> markers) {
        for (Marker marker : markers) {
            marker.remove();
        }
    }

    private void displayMyBusInfo(Bus bus) {
        if (bus != null) {
            plateTextView.setText(bus.getPlate());
            routeTextView.setText(bus.getDriveName());
        }
    }
}
