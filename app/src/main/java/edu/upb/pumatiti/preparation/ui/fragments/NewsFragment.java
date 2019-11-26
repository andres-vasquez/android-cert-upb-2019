package edu.upb.pumatiti.preparation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import edu.upb.pumatiti.preparation.R;
import edu.upb.pumatiti.preparation.models.ui.UserLogged;
import edu.upb.pumatiti.preparation.ui.fragments.base.BaseFragment;

public class NewsFragment extends BaseFragment {

    public NewsFragment(UserLogged userLogged) {
        super(userLogged);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate: Mostrar el XML del layout y vincular con sus variables
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initUI();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initUI() {

    }
}
