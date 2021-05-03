 package com.jlcsoftware.linkdisks.imagepicker;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;
import com.jlcsoftware.linkdisks.sharedPreferencesss.SharedPreferencesClass;
import com.jlcsoftware.linkdisks.ModelResponse.UploadResult;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BSImagePicker extends AppCompatActivity implements com.asksira.bsimagepicker.BSImagePicker.OnSingleImageSelectedListener,
        com.asksira.bsimagepicker.BSImagePicker.OnMultiImageSelectedListener, com.asksira.bsimagepicker.BSImagePicker.ImageLoaderDelegate,
        com.asksira.bsimagepicker.BSImagePicker.OnSelectImageCancelledListener{


    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;

    private TextView link,error_content_tv;

    private List<Uri> imageList;

    private Dialog loading_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_image_picker);

        ivImage1 = findViewById(R.id.iv_image1);
        ivImage2 = findViewById(R.id.iv_image2);
        ivImage3 = findViewById(R.id.iv_image3);
        ivImage4 = findViewById(R.id.iv_image4);
        ivImage5 = findViewById(R.id.iv_image5);
        ivImage6 = findViewById(R.id.iv_image6);

        imageList = new ArrayList<>();
        link = findViewById(R.id.link);



        loading_dialog=new Dialog(BSImagePicker.this);
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




        findViewById(R.id.tv_single_selection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.asksira.bsimagepicker.BSImagePicker pickerDialog = new

                        com.asksira.bsimagepicker.BSImagePicker.Builder("com.jlcsoftware.linkdisks.fileprovider")
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });


        findViewById(R.id.tv_multi_selection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.asksira.bsimagepicker.BSImagePicker pickerDialog = new

                        com.asksira.bsimagepicker.BSImagePicker.Builder("com.jlcsoftware.linkdisks.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .isMultiSelect()
                        .setMinimumMultiSelectCount(2)
                        .setMaximumMultiSelectCount(5)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });


    }




    @Override
    public void onSingleImageSelected(Uri uri, String tag) {


        Glide.with(BSImagePicker.this).load(uri).into(ivImage2);
        imageList.clear();
        imageList.add(uri);
        error_content_tv.setText("Uploading...");
        loading_dialog.show();
        uploadFiles();






    }


    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {
        for (int i=0; i < uriList.size(); i++) {
            if (i >= 6) return;
            ImageView iv;
            switch (i) {
                case 0:
                    iv = ivImage1;
                    break;
                case 1:
                    iv = ivImage2;
                    break;
                case 2:
                    iv = ivImage3;
                    break;
                case 3:
                    iv = ivImage4;
                    break;
                case 4:
                    iv = ivImage5;
                    break;
                case 5:
                default:
                    iv = ivImage6;
            }
            Glide.with(this).load(uriList.get(i)).into(iv);

            imageList.clear();
            for (int j=0;j<uriList.size();j++){
                imageList.add(uriList.get(i));
            }

            error_content_tv.setText("Uploading...");
            loading_dialog.show();

            uploadFiles();


        }
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Glide.with(BSImagePicker.this).load(imageUri).into(ivImage);

    }

    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {
        Toast.makeText(this, "Selection is cancelled, Multi-selection is " + isMultiSelecting, Toast.LENGTH_SHORT).show();
    }


    public void uploadFiles() {


        if(imageList.size() == 0) {
            Toast.makeText(BSImagePicker.this, "Can't choose pictures", Toast.LENGTH_SHORT).show();
            return;
        }


        Map<String, RequestBody> files = new HashMap<>();


        for (int i = 0; i < imageList.size(); i++) {

            File file= new File(getRealPathFromURI(imageList.get(i)));



            Glide.with(this).load(Uri.fromFile(file)).into(ivImage4);

            link.setText(file.getName());
            files.put("file" + i + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse(getContentResolver().getType(imageList.get(i))), file));




        }

        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        SharedPreferencesClass sharedPreferencesClass=new SharedPreferencesClass(BSImagePicker.this);
        String Token = sharedPreferencesClass.getValue_string("Token");

        retrofit2.Call<UploadResult> call= NetworkClient
                .getInstance().getApi()
                .uploadMultipleFiles(Token,files);


        call.enqueue(new Callback<UploadResult>() {
            @Override
            public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {


                if (response.isSuccessful()) {

                    loading_dialog.dismiss();

                    Toast.makeText(BSImagePicker.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    link.setText(response.body().getMessage());



                }else{
                    loading_dialog.dismiss();
                    link.setText("Token expired");
                }
            }

            @Override
            public void onFailure(Call<UploadResult> call, Throwable t) {

                Toast.makeText(BSImagePicker.this, "upload failed", Toast.LENGTH_SHORT).show();
                loading_dialog.dismiss();
                link.setText(t.getMessage());
                Log.d("sss",t.getMessage()+" "+imageList.get(0).toString());

            }
        });






    }



    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }









}