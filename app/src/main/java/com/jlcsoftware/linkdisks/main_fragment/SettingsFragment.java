package com.jlcsoftware.linkdisks.main_fragment;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.jlcsoftware.linkdisks.MainActivity;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.WholeApplication;

public class SettingsFragment extends Fragment {


    private MaterialToolbar toolbar;
    private MaterialButton theme_changer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings, container, false);



        theme_changer = view.findViewById(R.id.theme_changer);
        MainActivity mainActivity = new MainActivity();

        toolbar = view.findViewById(R.id.settingsTopAppBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);



        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        theme_changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WholeApplication wholeApplication = new WholeApplication();

                wholeApplication.showAlertDialog(alertDialog,getActivity());
            }
        });











        return view;
    }

}