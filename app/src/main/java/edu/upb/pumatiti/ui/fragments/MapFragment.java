package edu.upb.pumatiti.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.ui.StopMarker;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.utils.Constants;
import edu.upb.pumatiti.utils.IconUtils;

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap map;
    private MapView mapView;

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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        subscribeToRoutes();
    }

    private void initUI(View view) {
        mapView = view.findViewById(R.id.map);

    }

    private void subscribeToRoutes() {
        viewModel.getRoutes().observe(this, new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                if (base.isSuccess()) {
                    List<StopMarker> stopMarkers = (List<StopMarker>) base.getData();
                    if (!stopMarkers.isEmpty()) {
                        for (StopMarker stopMarker : stopMarkers) {
                            stopMarker.getMarker().icon(IconUtils.vectorAsIcon(context, stopMarker.getIconResource()));
                            map.addMarker(stopMarker.getMarker());
                        }
                    }
                } else {
                    Log.e("Error:", base.getMessage());
                    if (base.getException() != null) {
                        Log.e("Exception:", "" + base.getException().getMessage());
                    }
                }
            }
        });
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setPadding(0, 0, 0, mapView.getHeight());

        LatLng lapazUpb = new LatLng(Constants.DEFAULT_LAT, Constants.DEFAULT_LNG);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lapazUpb, Constants.DEFAULT_ZOOM));
    }

}
