package com.jlcsoftware.linkdisks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jlcsoftware.linkdisks.login_activity.Login;
import com.jlcsoftware.linkdisks.passCodeView.PassCode;

public class SplashActivity extends AppCompatActivity {


    private TextView dont_worry,yuorsecur;
    private RelativeLayout relativeLayout;


    Animation  textAnimation ,layout_animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        textAnimation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fail_down);
        layout_animation=AnimationUtils.loadAnimation(SplashActivity.this,R.anim.bottom_to_top);


        dont_worry=findViewById(R.id.dont_worry);
        yuorsecur=findViewById(R.id.yordisk);
        relativeLayout=findViewById(R.id.main_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layout_animation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        yuorsecur.setVisibility(View.VISIBLE);
                        dont_worry.setVisibility(View.VISIBLE);
                        yuorsecur.setAnimation(textAnimation);
                        dont_worry.setAnimation(textAnimation);
                    }
                },500);

            }



        },1000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SplashActivity.this);

                if(acct!=null){
                    startActivity(new Intent(SplashActivity.this, Login.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this, Login.class));
                    finish();
                }



            }
        },5000);



    }
}