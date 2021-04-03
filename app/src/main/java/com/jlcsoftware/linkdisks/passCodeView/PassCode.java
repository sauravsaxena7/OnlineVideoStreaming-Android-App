package com.jlcsoftware.linkdisks.passCodeView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.jlcsoftware.linkdisks.ModelResponse.RegisterResponse;
import com.jlcsoftware.linkdisks.ModelResponse.UserOneResponse;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;
import com.jlcsoftware.linkdisks.login_activity.Login;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;

import java.util.List;

public class PassCode extends AppCompatActivity {

    MaterialCardView back_space;
    ImageView  back_space_image;

    private List<UserOneResponse> userList =null;

    private MaterialCardView t1,t2,t3,t4,t5,t6,t7,t8,t9,t0,done,delete;
            private TextView email_tv,indicator_tv,error_content_tv;



    private Dialog loading_dialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code);


        loading_dialog=new Dialog(PassCode.this);
        loading_dialog.setContentView(R.layout.loading_dialog);
        error_content_tv=loading_dialog.findViewById(R.id.error_content_tv);


        loading_dialog.setCanceledOnTouchOutside(false);

        loading_dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;

            }
        });

        loading_dialog.show();


        t1 = findViewById(R.id.t_1);
        t2 = findViewById(R.id.t_2);

        t3 = findViewById(R.id.t_3);
        t4 = findViewById(R.id.t_4);
        t5 = findViewById(R.id.t_5);
        t6 = findViewById(R.id.t_6);
        t7 = findViewById(R.id.t_7);
        t8 = findViewById(R.id.t_8);
        t9 = findViewById(R.id.t_9);
        t0 = findViewById(R.id.t_0);
        done = findViewById(R.id.done_pass);
        delete = findViewById(R.id.delete_pass);


        indicator_tv = findViewById(R.id.indicator);
        email_tv = findViewById(R.id.email_tv);


        SharedPreferencesClass sharedPreferencesClass=new SharedPreferencesClass(PassCode.this);



        String Token = sharedPreferencesClass.getValue_string("token");
        Call<List<UserOneResponse>>call = NetworkClient
                .getInstance().getApi()
                .SendToken(Token);




        call.enqueue(new Callback<List<UserOneResponse>>() {
            @Override
            public void onResponse(Call<List<UserOneResponse>> call, Response<List<UserOneResponse>> response) {
                userList = (List<UserOneResponse>) response.body();
                indicator_tv.setText(userList.get(0).getPass_code());
            }

            @Override
            public void onFailure(Call<List<UserOneResponse>> call, Throwable t) {

                indicator_tv.setText(t.getMessage());
                loading_dialog.dismiss();
                Log.d("saurav",t.getMessage());

            }
        });





        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void indicator(int count){

    }
}