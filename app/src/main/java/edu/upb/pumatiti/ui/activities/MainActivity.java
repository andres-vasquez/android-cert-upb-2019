package edu.upb.pumatiti.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.models.ui.UserLogged;
import edu.upb.pumatiti.ui.fragments.BaseFragment;
import edu.upb.pumatiti.ui.fragments.MapFragment;
import edu.upb.pumatiti.ui.fragments.NewsFragment;
import edu.upb.pumatiti.ui.fragments.RulesFragment;
import edu.upb.pumatiti.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private UserLogged userLogged;

    private DrawerLayout drawerLayout;
    private CoordinatorLayout parentCoordinatorLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private Map<String, Fragment> mapFragments = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        getUserInfo();
        configureNavigationEvents();
        configureNavigationDrawer();
        configureToolbar();
        initFragments();
        loadFragment(Constants.KEY_FRAGMENT_MAP);
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.main_drawer_layout);
        parentCoordinatorLayout = findViewById(R.id.parentCoordinatorLayout);
        appBarLayout = findViewById(R.id.appBarLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void configureNavigationEvents() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.routes:
                        loadFragment(Constants.KEY_FRAGMENT_MAP);
                        break;
                    case R.id.rules:
                        loadFragment(Constants.KEY_FRAGMENT_RULES);
                        break;
                    case R.id.news:
                        loadFragment(Constants.KEY_FRAGMENT_NEWS);
                        break;
                    default:
                        return true;
                }
                drawerLayout.closeDrawers();
                return true;

            }
        });
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

    private void initFragments() {
        mapFragments.put(Constants.KEY_FRAGMENT_MAP, new MapFragment(userLogged));
        mapFragments.put(Constants.KEY_FRAGMENT_RULES, new RulesFragment(userLogged));
        mapFragments.put(Constants.KEY_FRAGMENT_NEWS, new NewsFragment(userLogged));
    }

    private void loadFragment(String key) {
        if (mapFragments.containsKey(key)) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.containerFrameLayout, mapFragments.get(key), key)
                    .commit();
        }
    }

}
