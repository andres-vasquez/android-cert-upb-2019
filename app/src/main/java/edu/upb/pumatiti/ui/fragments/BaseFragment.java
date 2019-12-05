package edu.upb.pumatiti.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.ui.activities.MainActivity;
import edu.upb.pumatiti.viewmodel.MainViewModel;

public class BaseFragment extends Fragment {
    protected UserLogged userLogged;

    protected MainViewModel viewModel;
    protected MainActivity activity;
    protected Context context;

    public BaseFragment(UserLogged userLogged) {
        this.userLogged = userLogged;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof MainActivity) {
            this.activity = (MainActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

}
