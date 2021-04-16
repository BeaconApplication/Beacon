package com.codepath.beacon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.action_home:
                    default:
                        fragment = new PinsFragment();
                        break;
                    case R.id.action_maps:
                        fragment = new MapsActivity();
                        break;
                    case R.id.action_addpin:
                        fragment = new CreatePinActivity();
                         break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}