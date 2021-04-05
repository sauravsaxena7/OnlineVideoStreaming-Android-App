package com.jlcsoftware.linkdisks.passCodeView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;
import com.jlcsoftware.linkdisks.MainActivity;
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
    private GoogleSignInClient mGoogleSignInClient;

    private List<UserOneResponse> userList =null;

    private MaterialCardView t1,t2,t3,t4,t5,t6,t7,t8,t9,t0,done,delete;
            private TextView email_tv,indicator_tv,error_content_tv,t_c1,t_c2,t_c3,t_c4,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv0;



    private Dialog loading_dialog;



    private String final_pass_code="";

    private String email,user_id;


    private int flag=0;




    @SuppressLint("SetTextI18n")
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

        indicator_tv = findViewById(R.id.indicator);
        email_tv = findViewById(R.id.email_tv);



        if(checkUser()){
            loading_dialog.dismiss();
            indicator_tv.setText("Enter Your PassCode..");
            flag=1;
        }else{
             loading_dialog.dismiss();
             indicator_tv.setText("Generate Your LinkDisk's PassCode");
        }


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

        done.setEnabled(false);
        done.setCardBackgroundColor(getResources().getColor(R.color.ripple));


        t_c1=findViewById(R.id.t_count_1);
        t_c2=findViewById(R.id.t_count_2);
        t_c3=findViewById(R.id.t_count_3);
        t_c4=findViewById(R.id.t_count_4);

        tv1=findViewById(R.id.text1);
        tv2=findViewById(R.id.text2);
        tv3=findViewById(R.id.text3);
        tv4=findViewById(R.id.text4);
        tv5=findViewById(R.id.text5);
        tv6=findViewById(R.id.text6);
        tv7=findViewById(R.id.text7);
        tv8=findViewById(R.id.text8);
        tv9=findViewById(R.id.text9);
        tv0=findViewById(R.id.text0);








        final int[] count = {0};

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv1.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count[0] <4){
                            final_pass_code=final_pass_code + tv2.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv3.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv4.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv5.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv6.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv7.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv8.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });

        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv9.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });

        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count[0] <4){
                    final_pass_code=final_pass_code + tv0.getText().toString();
                    count[0]++;
                    Toast.makeText(PassCode.this, ""+final_pass_code, Toast.LENGTH_SHORT).show();
                    ColorIndicator(count[0]);
                    checkDone(count[0]);
                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading_dialog.show();
                if(flag==0){
                    RegisterUser(user_id,email,final_pass_code);
                }else{
                    loginMethod(email,final_pass_code);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Indicator(count[0]);
                count[0]--;
                removeLastChar(final_pass_code);
                Toast.makeText(PassCode.this, ""+final_pass_code+" "+count[0], Toast.LENGTH_SHORT).show();
                checkDone(count[0]);

            }
        });



    }

    private void loginMethod(String email, String final_pass_code) {
    }



    private void RegisterUser(String user_id, String email, String final_pass_code) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("size","0KB");
        jsonObject.addProperty("email",email);
        jsonObject.addProperty("pass_code",final_pass_code);


        Call<RegisterResponse> call = NetworkClient
                .getInstance().getApi()
                .Register_body(jsonObject);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
//saurav
                RegisterResponse registerResponse = (RegisterResponse) response.body();

                if(response.isSuccessful()){
                    if(registerResponse.getError().equals("401")){

                        loginMethod(email,final_pass_code);

                    }else{
                        SharedPreferencesClass sharedPreferencesClass = new SharedPreferencesClass(PassCode.this);
                        sharedPreferencesClass.setValue_string("Token",registerResponse.getToken());
                        loading_dialog.dismiss();
                        startActivity(new Intent(PassCode.this, MainActivity.class));
                        finish();
                    }

                }

            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

                Toast.makeText(PassCode.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                checkUser();



            }
        });

    }

    private void ColorIndicator(int count){

        switch (count){
            case 1:
                t_c1.setBackground(getResources().getDrawable(R.drawable.rounded_textview));
                break;
            case 2:
                t_c2.setBackground(getResources().getDrawable(R.drawable.rounded_textview));
                break;

            case 3:
                t_c3.setBackground(getResources().getDrawable(R.drawable.rounded_textview));
                break;
            case 4:
                t_c4.setBackground(getResources().getDrawable(R.drawable.rounded_textview));
                break;

        }



    }


    private void Indicator(int count){

        switch (count){
            case 1:
                t_c1.setBackground(getResources().getDrawable(R.drawable.circle));
                break;
            case 2:
                t_c2.setBackground(getResources().getDrawable(R.drawable.circle));
                break;

            case 3:
                t_c3.setBackground(getResources().getDrawable(R.drawable.circle));
                break;
            case 4:
                t_c4.setBackground(getResources().getDrawable(R.drawable.circle));
                break;

        }



    }

    public void removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            final_pass_code=s;
        }else{
            final_pass_code=s.substring(0, s.length()-1);
        }


    }


    private void checkDone(int count){
        if(count==4){
            done.setEnabled(true);
            done.setBackgroundColor(getResources().getColor(R.color.color_main));

        }else{
            done.setEnabled(false);
            done.setBackgroundColor(getResources().getColor(R.color.ripple));
        }
    }


    private boolean registerUser(String personId, String personEmail) {


        final boolean[] check = {false};
        //https://medium.com/android-news/handmade-backend-for-android-app-using-python-flask-framework-b173ba2bb3aa

        //parsing json arrays https://dds861.medium.com/json-arrays-parsing-using-retrofit-and-recycleview-9b2d494cc24c

        //https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array

        //https://github.com/akshatapatel/Iris-Recognition


        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",personId);
        jsonObject.addProperty("size","0KB");
        jsonObject.addProperty("email",personEmail);
        jsonObject.addProperty("pass_code","*#/%");

        email_tv.setText(email);

        Call<RegisterResponse> call = NetworkClient
                .getInstance().getApi()
                .Register_body(jsonObject);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {

                RegisterResponse registerResponse = (RegisterResponse) response.body();

                if(response.isSuccessful()){
                    if(registerResponse.getError().equals("401")){
                        check[0] = true;
                    }else{
                        check[0] = false;
                    }

                }

            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

                Toast.makeText(PassCode.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                checkUser();



            }
        });

        return check[0];


    }

    private boolean checkUser(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            email= acct.getEmail();
            user_id = acct.getId();

            if(registerUser(user_id,email)){
                return true;
            }


        }
        return false;
    }

}