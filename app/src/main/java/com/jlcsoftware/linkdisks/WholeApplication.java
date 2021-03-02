package com.jlcsoftware.linkdisks;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentActivity;

public class WholeApplication extends Application {

    private SharedPreferencesClass getThemePreference;
    SharedPreferences.Editor editor;

    public void onCreate() {
        super.onCreate();



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
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                        getThemePreference.setValue_string("THEME","night");


                        break;
                    case 1:
                        Toast.makeText(activity, "Light", Toast.LENGTH_LONG).show();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        getThemePreference.setValue_string("THEME","light");
                        break;

                    case 2:
                        Toast.makeText(activity, "Default", Toast.LENGTH_LONG).show();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        getThemePreference.setValue_string("THEME","light");
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


}
