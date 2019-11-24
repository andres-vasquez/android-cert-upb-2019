package edu.upb.pumatiti.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upb.pumatiti.R;
import edu.upb.pumatiti.ui.fragments.MapFragment;
import edu.upb.pumatiti.ui.fragments.NewsFragment;
import edu.upb.pumatiti.ui.fragments.RulesFragment;
import edu.upb.pumatiti.utils.Constants;
import edu.upb.pumatiti.viewmodel.LoginViewModel;
import edu.upb.pumatiti.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private FrameLayout containerFrameLayout;
    private Map<String, Fragment> fragmentsMap = new HashMap<>();

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initUI();
        configureNavigationEvents();
        configureNavigationDrawer();
        configureCollapsingToolbatTitle();
        configureToolbar();

        initFragments();
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);

        containerFrameLayout = findViewById(R.id.containerFrameLayout);
    }

    private void configureNavigationDrawer() {
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    private void configureCollapsingToolbatTitle() {
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));
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
        fragmentsMap.put(Constants.KEY_ROUTES, new MapFragment());
        fragmentsMap.put(Constants.KEY_NEWS, new NewsFragment());
        fragmentsMap.put(Constants.KEY_RULES, new RulesFragment());
    }

    private void loadFragment(String key) {

    }


    public void locationClick(View view) {

    }
}
