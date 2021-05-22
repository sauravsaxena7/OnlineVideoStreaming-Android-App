package com.jlcsoftware.linkdisks.gallery;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jlcsoftware.linkdisks.ModelResponse.GalleryResponse;
import com.jlcsoftware.linkdisks.ModelResponse.ImageModelResponse;
import com.jlcsoftware.linkdisks.ModelResponse.UploadResult;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {


    private ImageView imageView;
    private Button button;
    private TextView textView;

    //saurav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView = findViewById(R.id.imageView_idd);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.result_tv);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {









//                Call<ImageModelResponse> call = NetworkClient
//                       .getInstance().getApi()
//                      .image_List();
//
//
//
//
//                call.enqueue(new Callback<ImageModelResponse>() {
//                    @Override
//                    public void onResponse(Call<ImageModelResponse> call, Response<ImageModelResponse> response) {
//                        if(response.isSuccessful()){
//
//
//                            Toast.makeText(GalleryActivity.this, ""+response.body().getImages().size(), Toast.LENGTH_SHORT).show();
//                            textView.setText(response.body().getImages().get(0).getFiles_id());
//
//
//
//
//
//                        }else{
//                            textView.setText("nothing");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ImageModelResponse> call, Throwable t) {
//
//                        textView.setText(t.getMessage());
//                        Log.d("sss",t.getMessage());
//                    }
//                });



            }
        });





    }


}

