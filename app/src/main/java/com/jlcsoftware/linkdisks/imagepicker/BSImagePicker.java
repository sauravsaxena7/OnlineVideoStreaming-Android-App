package com.jlcsoftware.linkdisks.imagepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jlcsoftware.linkdisks.R;

import java.util.List;

public class BSImagePicker extends AppCompatActivity implements com.asksira.bsimagepicker.BSImagePicker.OnSingleImageSelectedListener,
        com.asksira.bsimagepicker.BSImagePicker.OnMultiImageSelectedListener, com.asksira.bsimagepicker.BSImagePicker.ImageLoaderDelegate,
        com.asksira.bsimagepicker.BSImagePicker.OnSelectImageCancelledListener{


    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;


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
                        .setMinimumMultiSelectCount(3)
                        .setMaximumMultiSelectCount(6)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });




    }




    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(BSImagePicker.this).load(uri).into(ivImage2);
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
}