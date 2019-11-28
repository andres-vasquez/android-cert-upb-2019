package edu.upb.pumatiti.ui.fragments;

import androidx.fragment.app.Fragment;

import edu.upb.pumatiti.models.ui.UserLogged;

public class BaseFragment extends Fragment {
    protected UserLogged userLogged;

    public BaseFragment(UserLogged userLogged) {
        this.userLogged = userLogged;
    }
}
