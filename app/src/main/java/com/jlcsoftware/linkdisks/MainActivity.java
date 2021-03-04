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



//saurav suman


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        getThemePreference = new SharedPreferencesClass(MainActivity.this);

        int priority = getThemePreference.getValue_int("fragment_priority");


        if(priority==1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, new SettingsFragment()).commit();
            getThemePreference.setValue_int("fragment_priority",0);

        }else if(priority==0){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, new HomeFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, new HomeFragment()).commit();
        }









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



    public void showAlertDialog(AlertDialog.Builder alertDialog, FragmentActivity activity) {


        getThemePreference = new SharedPreferencesClass(activity);


        alertDialog.setTitle("Choose Theme");

        String[] items = {"Dark mode","Light mode","Default"};
        int checkedItem;



        String value = getThemePreference.getValue_string("THEME");

        if (value.equals("night")){
            checkedItem=0;
        }else if(value.equals("light")){
            checkedItem=1;
        }else{
            checkedItem=2;
        }


        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(activity, "Dark", Toast.LENGTH_LONG).show();
                        getThemePreference.setValue_int("fragment_priority",1);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                        getThemePreference.setValue_string("THEME","night");



                        break;
                    case 1:
                        Toast.makeText(activity, "Light", Toast.LENGTH_LONG).show();
                        getThemePreference.setValue_int("fragment_priority",1);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        getThemePreference.setValue_string("THEME","light");
                        break;

                    case 2:
                        Toast.makeText(activity, "Default", Toast.LENGTH_LONG).show();
                        getThemePreference.setValue_int("fragment_priority",1);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        getThemePreference.setValue_string("THEME","light");
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
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

// saurav suman
    //how are you
    @Override
    protected void onStart() {
        super.onStart();
    }
}