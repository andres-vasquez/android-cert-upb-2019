package edu.upb.pumatiti.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.repository.Base;
import edu.upb.pumatiti.models.repository.Route;
import edu.upb.pumatiti.models.ui.UserLogged;

public class MapFragment extends BaseFragment {

    private TextView myTextTextView;

    public MapFragment(UserLogged userLogged) {
        super(userLogged);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate: Mostrar el XML del layout y vincular con sus variables
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initUI(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myTextTextView.setText("Mis mapitas");

        subscribeToRoutes();
    }

    private void initUI(View view) {
        myTextTextView = view.findViewById(R.id.myTextTextView);
    }

    private void subscribeToRoutes() {
        viewModel.getRoutes().observe(this, new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                if (base.isSuccess()) {
                    List<Route> routes = (List<Route>) base.getData();
                    Log.e("Cantidad de rutas", "" + routes.size());

                    for (Route route : routes) {
                        Log.e("Ruta:", "" + route.getName());
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
}
