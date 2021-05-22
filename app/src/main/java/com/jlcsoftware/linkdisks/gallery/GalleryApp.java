package com.jlcsoftware.linkdisks.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.JsonObject;
import com.jlcsoftware.linkdisks.ModelResponse.ImageModelResponse;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.SplashActivity;
import com.jlcsoftware.linkdisks.client.NetworkClient;
import com.jlcsoftware.linkdisks.passCodeView.PassCode;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;

import java.util.ArrayList;

public class GalleryApp extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ImageListAdapter imageListAdapter;

    private ArrayList<Imagess> imagessArrayList;

    private int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_app);



        recyclerView=findViewById(R.id.image_recyclerView);

        progressBar=findViewById(R.id.image_progressBar);

        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setHasFixedSize(true);











        getAllImage();





    }

    private void getAllImage() {

        imagessArrayList=new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);



        SharedPreferencesClass sharedPreferencesClass = new SharedPreferencesClass(GalleryApp.this);
        String Token = sharedPreferencesClass.getValue_string("Token");

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(GalleryApp.this);




        //for real image








        //for real image id


        Call<ImageModelResponse> call = NetworkClient
                .getInstance().getApi()
                .image_List(Token);




        call.enqueue(new Callback<ImageModelResponse>() {
            @Override
            public void onResponse(Call<ImageModelResponse> call, Response<ImageModelResponse> response) {
                if(response.isSuccessful()){


                    Log.d("sss3",""+response.body().getImages().size());
                    size=response.body().getImages().size();
                    final int[] count = {0};



                    for(int i=1;i<response.body().getImages().size();i++){

                        JsonObject jsonObject=new JsonObject();
                        jsonObject.addProperty("files_id",response.body().getImages().get(i).getFiles_id());
                        Log.d("sss"+i,response.body().getImages().get(i).getFiles_id());






                        retrofit2.Call<ResponseBody> call2= NetworkClient
                                .getInstance().getApi()
                                .retrieveMultipleFiles(Token,jsonObject);






                        call2.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){


                                    Imagess imagess = new Imagess(response.body());
                                    imagessArrayList.add(imagess);
                                    count[0] = count[0] +1;

                                    if(count[0]==(size-1)){

                                        imageListAdapter = new ImageListAdapter(imagessArrayList,GalleryApp.this);

                                        recyclerView.setAdapter(imageListAdapter);
                                        Toast.makeText(GalleryApp.this,
                                                "All image fetched", Toast.LENGTH_SHORT).show();

                                        imageListAdapter.notifyDataSetChanged();
                                        Log.d("sss4","success respnse");

                                    }








                                }else{
                                    Toast.makeText(GalleryApp.this, "i don't know", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Toast.makeText(GalleryApp.this, "i don't know"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("sss1",t.getMessage());
                                progressBar.setVisibility(View.GONE);



                                

                            }
                        });






                    }


                    progressBar.setVisibility(View.GONE);







                }else{
                    Toast.makeText(GalleryApp.this, ""+response.body().getImages().size(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ImageModelResponse> call, Throwable t) {

                Log.d("sss",t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });







    }


}