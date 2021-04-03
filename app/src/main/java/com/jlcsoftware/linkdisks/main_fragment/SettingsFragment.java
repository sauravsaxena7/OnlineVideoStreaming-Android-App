package com.jlcsoftware.linkdisks.main_fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.jlcsoftware.linkdisks.MainActivity;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;

public class SettingsFragment extends Fragment {


    private MaterialToolbar toolbar;
    private MaterialButton theme_changer;
    private SharedPreferencesClass getThemePreference;

    private TextView theme,manage_access,storage,theme_;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings, container, false);


        theme=view.findViewById(R.id.theme);
        theme_=view.findViewById(R.id.theme_);

        storage=view.findViewById(R.id.storage);

        manage_access = view.findViewById(R.id.manage_access);

        theme_changer = view.findViewById(R.id.theme_changer);
        MainActivity mainActivity = new MainActivity();

        toolbar = view.findViewById(R.id.settingsTopAppBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);




        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        getThemePreference = new SharedPreferencesClass(getActivity());

        String value = getThemePreference.getValue_string("THEME");
        if (value.equals("night")){

            theme.setTextColor(getResources().getColor(R.color.color_white));
            theme_.setText("Dark");
            storage.setTextColor(getResources().getColor(R.color.color_white));
            manage_access.setTextColor(getResources().getColor(R.color.color_white));
            toolbar.setBackgroundColor(getResources().getColor(R.color.light_color));
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        }else if(value.equals("light")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            theme.setTextColor(getResources().getColor(R.color.light_color));
            theme_.setText("Light");
            storage.setTextColor(getResources().getColor(R.color.light_color));
            manage_access.setTextColor(getResources().getColor(R.color.light_color));
            toolbar.setBackgroundColor(getResources().getColor(R.color.white));
            toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        }


        theme_changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mainActivity.showAlertDialog(alertDialog,getActivity());





            }
        });











        return view;
    }

}