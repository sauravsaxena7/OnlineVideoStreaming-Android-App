package com.jlcsoftware.linkdisks.main_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.imagepicker.BSImagePicker;


public class AddFragment extends Fragment {


    private MaterialButton upload_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add, container, false);

        upload_image = view.findViewById(R.id.upload_image);



        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BSImagePicker.class));
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}