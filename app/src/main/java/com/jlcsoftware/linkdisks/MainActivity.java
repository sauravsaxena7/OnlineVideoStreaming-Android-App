package com.jlcsoftware.linkdisks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jlcsoftware.linkdisks.main_fragment.AddFragment;
import com.jlcsoftware.linkdisks.main_fragment.DownloadsFragment;
import com.jlcsoftware.linkdisks.main_fragment.HomeFragment;
import com.jlcsoftware.linkdisks.main_fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {




    private SharedPreferencesClass getThemePreference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

       getSupportFragmentManager().beginTransaction()
               .replace(R.id.fragment_layout, new HomeFragment()).commit();



        getThemePreference = new SharedPreferencesClass(MainActivity.this);




        String value = getThemePreference.getValue_string("THEME");
        if (value.equals("night")){

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else if(value.equals("light")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }else{
            int nightModeFlags =
                    this.getResources().getConfiguration().uiMode &
                            Configuration.UI_MODE_NIGHT_MASK;
            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;

                case Configuration.UI_MODE_NIGHT_NO:

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;

                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    break;
            }
        }






    }







    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;
                    switch (item.getItemId()){

                        case R.id.home:

                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.settings:

                            selectedFragment = new SettingsFragment();
                            break;

                        case R.id.add:
                            selectedFragment = new AddFragment();
                            break;

                        case R.id.downloads:
                            selectedFragment = new DownloadsFragment();
                            break;


                    }


                    //begin Transaction
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout,selectedFragment).commit();



                    return true;
                }
            };


    @Override
    protected void onStart() {
        super.onStart();
    }
}