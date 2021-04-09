package com.jlcsoftware.linkdisks.video_streaming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlcsoftware.linkdisks.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context context;
    List<Video> mVideo;

    public VideoAdapter(Context context, List<Video> mVideo) {
        this.context = context;
        this.mVideo = mVideo;
    }



    @NonNull
    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.video_item,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyViewHolder holder, int position) {


        holder.title.setText(mVideo.get(position).getTitle());
        holder.video_image.setImageResource(mVideo.get(position).getImage());
        holder.description.setText(mVideo.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mVideo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title,description;

        ImageView video_image,video_menu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.video_title);
            description=itemView.findViewById(R.id.video_description);
            video_image=itemView.findViewById(R.id.video_item_image);
            video_menu=itemView.findViewById(R.id.video_menu);
        }
    }
}
