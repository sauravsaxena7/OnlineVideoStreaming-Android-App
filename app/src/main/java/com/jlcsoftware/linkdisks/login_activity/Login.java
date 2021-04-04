package com.jlcsoftware.linkdisks.login_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.jlcsoftware.linkdisks.ModelResponse.RegisterResponse;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;
import com.jlcsoftware.linkdisks.passCodeView.PassCode;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;

import org.json.JSONObject;

import java.util.HashMap;


//1003587981898-173j078jbfk5eqip1828r4jauojqo70b.apps.googleusercontent.com

//FZjemPaNcDDppCBt0hZu_zGo


public class Login extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;

    private static int RC_SIGN_IN=1024;

    private TextView sometext;

    private Dialog loading_dialog;
    private TextView error_content_tv;

    LottieAnimationView google_sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sometext=findViewById(R.id.sometext);


        loading_dialog=new Dialog(Login.this);
        loading_dialog.setContentView(R.layout.loading_dialog);
        error_content_tv=loading_dialog.findViewById(R.id.error_content_tv);


        loading_dialog.setCanceledOnTouchOutside(true);

        loading_dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;

            }
        });






        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();



        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });


        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);




        google_sign_in=findViewById(R.id.google_sign);
        google_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error_content_tv.setText("Please Wait....");
                loading_dialog.show();
                signIn();
            }
        });


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                registerUser(personId,personEmail);



            }


            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("saurav", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void registerUser(String personId, String personEmail) {


        //https://medium.com/android-news/handmade-backend-for-android-app-using-python-flask-framework-b173ba2bb3aa

        //parsing json arrays https://dds861.medium.com/json-arrays-parsing-using-retrofit-and-recycleview-9b2d494cc24c

        //https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array

        //https://github.com/akshatapatel/Iris-Recognition


        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user_id",personId);
        jsonObject.addProperty("size","0KB");
        jsonObject.addProperty("email",personEmail);
        jsonObject.addProperty("pass_code","*#/%");


        Call<RegisterResponse> call = NetworkClient
                .getInstance().getApi()
                .Register_body(jsonObject);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {

                RegisterResponse registerResponse = (RegisterResponse) response.body();

                if(response.isSuccessful()){


                    SharedPreferencesClass sharedPreferencesClass = new SharedPreferencesClass(Login.this);

                    sharedPreferencesClass.setValue_string("token",registerResponse.getToken());

                    startActivity(new Intent(Login.this,PassCode.class));
                    finish();


                }

            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

                Toast.makeText(Login.this, ""+t.getMessage()+" please Try again! ", Toast.LENGTH_SHORT).show();
                signIn();


            }
        });






    }


}