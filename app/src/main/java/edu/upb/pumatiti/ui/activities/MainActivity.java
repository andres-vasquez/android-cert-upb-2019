package edu.upb.pumatiti.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import edu.upb.pumatiti.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        configureNavigationEvents();
        configureNavigationDrawer();
        configureCollapsingToolbatTitle();
        configureToolbar();
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
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
                        Toast.makeText(MainActivity.this, "Routes", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rules:
                        Toast.makeText(MainActivity.this, "Rules", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.news:
                        Toast.makeText(MainActivity.this, "News", Toast.LENGTH_SHORT).show();
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
}
