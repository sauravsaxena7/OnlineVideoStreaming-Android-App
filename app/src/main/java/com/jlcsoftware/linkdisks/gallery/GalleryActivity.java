package com.jlcsoftware.linkdisks.gallery;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jlcsoftware.linkdisks.ModelResponse.GalleryResponse;
import com.jlcsoftware.linkdisks.ModelResponse.UploadResult;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {


    private ImageView imageView;
    private Button button;
    private TextView textView;


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

                retrofit2.Call<ResponseBody> call= NetworkClient
                        .getInstance().getApi()
                        .retrieveMultipleFiles();

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                            imageView.setImageBitmap(bmp);

                            textView.setText("success"+response.body().contentType());


                        }else{
                            Toast.makeText(GalleryActivity.this, "i don't know", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(GalleryActivity.this, "i don't know"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        textView.setText(t.getMessage());

                    }
                });

            }
        });





    }


}