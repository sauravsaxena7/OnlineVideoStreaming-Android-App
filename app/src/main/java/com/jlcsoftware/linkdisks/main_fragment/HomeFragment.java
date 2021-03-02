package com.jlcsoftware.linkdisks.main_fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.jlcsoftware.linkdisks.R;


public class HomeFragment extends Fragment {


    private MaterialCardView imageCardView,videosCardView,contactsCardView,fileCardView,designCardView,diaryCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        imageCardView=view.findViewById(R.id.images);
        designCardView=view.findViewById(R.id.design);
        videosCardView=view.findViewById(R.id.videos);

        contactsCardView = view.findViewById(R.id.contacts);
        diaryCardView=view.findViewById(R.id.diary);

        fileCardView = view.findViewById(R.id.document);



        imageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });

        designCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });

        diaryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });


        fileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });


        videosCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });

        contactsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });






        return view;
    }
}