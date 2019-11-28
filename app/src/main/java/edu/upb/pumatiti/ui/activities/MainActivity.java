package edu.upb.pumatiti.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.ui.fragments.BaseFragment;
import edu.upb.pumatiti.ui.fragments.MapFragment;
import edu.upb.pumatiti.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private UserLogged userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserInfo();
    }

    private void getUserInfo() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.INTENT_KEY_USER_LOGGED)) {
            String json = intent.getStringExtra(Constants.INTENT_KEY_USER_LOGGED);
            try {
                userLogged = new Gson().fromJson(json, UserLogged.class);
                MapFragment f1 = new MapFragment(userLogged);
                BaseFragment f2 = new MapFragment(userLogged);
                Fragment f3 = new MapFragment(userLogged);
            } catch (Exception ex) {

            }
        }
    }
}
