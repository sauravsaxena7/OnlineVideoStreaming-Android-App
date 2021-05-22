package com.jlcsoftware.linkdisks.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.jlcsoftware.linkdisks.R;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {


    ArrayList<Imagess> imagessArrayList;
    Context context;

    public ImageListAdapter(ArrayList<Imagess> imagessArrayList, Context context) {
        this.imagessArrayList = imagessArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);




        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            }
        });

        return myViewHolder;



    }




    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.MyViewHolder holder, int position) {
       Bitmap bmp = BitmapFactory.decodeStream(imagessArrayList.get(position).getResponseBody().byteStream());

        //Glide.with(context).load(Uri.parse(String.valueOf(bmp))).into(holder.imageView);
        int rotation ;


        holder.imageView.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return imagessArrayList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.row_gallery_imageView);
        }
    }

}
