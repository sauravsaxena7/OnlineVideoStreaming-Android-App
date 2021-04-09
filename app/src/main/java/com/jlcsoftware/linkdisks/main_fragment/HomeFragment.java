package com.jlcsoftware.linkdisks.main_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.SplashActivity;
import com.jlcsoftware.linkdisks.imagepicker.BSImagePicker;
import com.jlcsoftware.linkdisks.login_activity.Login;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;
import com.jlcsoftware.linkdisks.video_streaming.VideoTemplate;


public class HomeFragment extends Fragment {


    private MaterialCardView imageCardView,videosCardView,contactsCardView,fileCardView,designCardView,diaryCardView;
    private MaterialButton upgrade_button;

    private CircleImageView profile_image;
    private TextView profile_tv,email_tv;

    private GoogleSignInClient mGoogleSignInClient;


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


        profile_image=view.findViewById(R.id.profile_image);
        profile_tv=view.findViewById(R.id.profile_name);
        email_tv=view.findViewById(R.id.profile_email);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(acct!=null){

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_account_circle_white_24dp);
            Glide.with(getActivity()).setDefaultRequestOptions(requestOptions).load(acct.getPhotoUrl()).into(profile_image);
            profile_tv.setText(acct.getDisplayName());
            email_tv.setText(acct.getEmail());

        }

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
               startActivity(new Intent(getActivity(), VideoTemplate.class));
            }
        });

        contactsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
            }
        });


        upgrade_button=view.findViewById(R.id.upgrade_btn);
        upgrade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Configure sign-in to request the user's ID, email address, and basic
                // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();



                // Build a GoogleSignInClient with the options specified by gso.
                mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        SharedPreferencesClass sharedPreferencesClass = new SharedPreferencesClass(getActivity());
                        sharedPreferencesClass.setValue_string("Token","");
                        startActivity(new Intent(getActivity(), Login.class));
                        getActivity().finish();
                    }
                });


            }
        });





        return view;
    }
}