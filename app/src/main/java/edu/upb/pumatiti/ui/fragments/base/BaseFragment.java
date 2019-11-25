package edu.upb.pumatiti.ui.fragments.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.ui.activities.MainActivity;

public class BaseFragment extends Fragment {

    protected Context context;
    protected MainActivity activity;
    protected UserLogged userLogged;

    public BaseFragment(UserLogged userLogged) {
        this.userLogged = userLogged;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
