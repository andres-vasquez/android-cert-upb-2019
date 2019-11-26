package edu.upb.pumatiti.preparation.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import edu.upb.pumatiti.preparation.R;
import edu.upb.pumatiti.preparation.models.types.UserType;
import edu.upb.pumatiti.preparation.models.ui.UserLogged;
import edu.upb.pumatiti.preparation.ui.fragments.MapFragment;
import edu.upb.pumatiti.preparation.ui.fragments.NewsFragment;
import edu.upb.pumatiti.preparation.ui.fragments.RulesFragment;
import edu.upb.pumatiti.preparation.utils.Constants;
import edu.upb.pumatiti.preparation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private UserLogged userLogged;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private FloatingActionButton locationFloatingActionButton;

    private Map<String, Fragment> fragmentsMap = new HashMap<>();

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        getUserInfo();
        initUI();
        configureNavigationEvents();
        configureNavigationDrawer();
        configureToolbar();

        initFragments();
        loadFragment(Constants.KEY_ROUTES);
    }

    private void getUserInfo() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.INTENT_KEY_USER)) {
            try {
                userLogged = new Gson().fromJson(intent.getStringExtra(Constants.INTENT_KEY_USER),
                        UserLogged.class);
            } catch (Exception ex) {

            }
        }
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.toolbar);

        locationFloatingActionButton = findViewById(R.id.locationFloatingActionButton);
    }

    private void configureNavigationDrawer() {
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void configureNavigationEvents() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.routes:
                        loadFragment(Constants.KEY_ROUTES);
                        break;
                    case R.id.rules:
                        loadFragment(Constants.KEY_RULES);
                        break;
                    case R.id.news:
                        loadFragment(Constants.KEY_NEWS);
                        break;
                    default:
                        return true;
                }
                drawerLayout.closeDrawers();
                return true;

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    private void initFragments() {
        fragmentsMap.put(Constants.KEY_ROUTES, new MapFragment(userLogged));
        fragmentsMap.put(Constants.KEY_NEWS, new NewsFragment(userLogged));
        fragmentsMap.put(Constants.KEY_RULES, new RulesFragment(userLogged));
    }

    private void loadFragment(String key) {
        if (fragmentsMap.containsKey(key)) {

            //Show hide location button
            if (key.equals(Constants.KEY_ROUTES)) {
                if (userLogged != null && userLogged.getUserType().equals(UserType.REGULAR_USER)) {
                    locationFloatingActionButton.show();
                } else {
                    locationFloatingActionButton.hide();
                }
            } else {
                locationFloatingActionButton.hide();
            }

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.containerFrameLayout, fragmentsMap.get(key), key)
                    .commit();
        }
    }


    public void locationClick(View view) {
        if (fragmentsMap.containsKey(Constants.KEY_ROUTES)) {
            MapFragment mapFragment = (MapFragment) fragmentsMap.get(Constants.KEY_ROUTES);
            mapFragment.locationClick();
        }
    }
}
