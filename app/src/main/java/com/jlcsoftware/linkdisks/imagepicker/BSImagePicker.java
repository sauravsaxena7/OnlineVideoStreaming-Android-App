package com.jlcsoftware.linkdisks.imagepicker;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jlcsoftware.linkdisks.R;
import com.jlcsoftware.linkdisks.client.NetworkClient;
import com.jlcsoftware.linkdisks.client.UploadApis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BSImagePicker extends AppCompatActivity implements com.asksira.bsimagepicker.BSImagePicker.OnSingleImageSelectedListener,
        com.asksira.bsimagepicker.BSImagePicker.OnMultiImageSelectedListener, com.asksira.bsimagepicker.BSImagePicker.ImageLoaderDelegate,
        com.asksira.bsimagepicker.BSImagePicker.OnSelectImageCancelledListener{


    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;

    private TextView link;


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



//        File file = new File(uri.getPath());
//
//        Retrofit retrofit = NetworkClient.getRetrofit();
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part parts = MultipartBody.Part.createFormData("newimage", file.getName(), requestBody);
//
//
//
//
//        UploadApis uploadApis = retrofit.create(UploadApis.class);
//
//        Call call = (Call) uploadApis.uploadImage(parts,"sauravsrivastava121@gmail.com","sauravsrivastava121@gmail.com");
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                call.cancel();
//
//                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(BSImagePicker.this, "Failed to Connect to Server", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            link.setText(response.body().string());
//                            Toast.makeText(BSImagePicker.this, ""+link.getText().toString(), Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//            }
//
//
//        });

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





    void postRequest(String post_url,RequestBody postbody){

        OkHttpClient client = new OkHttpClient();





        Request request = new Request.Builder()
                .url(post_url)
                .post(postbody)
                .addHeader("content-type", "application/json")
                .addHeader("x-access-token", "")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BSImagePicker.this, "Failed to Connect to Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            link.setText(response.body().string());
                            Toast.makeText(BSImagePicker.this, ""+link.getText().toString(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });



    }


    // Implementation of the getPath() method and all its requirements is taken from the StackOverflow Paul Burke's answer: https://stackoverflow.com/a/20559175/5426539
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}