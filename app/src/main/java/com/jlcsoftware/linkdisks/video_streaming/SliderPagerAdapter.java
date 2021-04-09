package com.jlcsoftware.linkdisks.video_streaming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlcsoftware.linkdisks.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderPagerAdapter extends PagerAdapter {


    private Context mContext;

    private List<Slide> mList;

    public SliderPagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View slideLayout = inflater.inflate(R.layout.video_slide_item,null);



        ImageView slideImage = slideLayout.findViewById(R.id.slide_imageView);

        TextView slideTextView = slideLayout.findViewById(R.id.slide_title);

        slideImage.setImageResource(mList.get(position).getImage());
        slideTextView.setText(mList.get(position).getTitle());



        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext,OnlineVideoStreamingApp.class);
                mContext.startActivity(intent);
            }
        });

        container.addView(slideLayout);

        return slideLayout;



    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((View)object);


    }

}
