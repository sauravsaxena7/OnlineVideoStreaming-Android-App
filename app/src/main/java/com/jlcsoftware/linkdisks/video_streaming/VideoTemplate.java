package com.jlcsoftware.linkdisks.video_streaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.jlcsoftware.linkdisks.R;

import java.util.ArrayList;
import java.util.List;

public class VideoTemplate extends AppCompatActivity {

    private List<Slide> listSlide;

    private ViewPager slidePager;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_template);



        slidePager = findViewById(R.id.template_viewPager);

        recyclerView = findViewById(R.id.video_recycler_view);

        //prepare of a list of slides
        listSlide = new ArrayList<>();

        listSlide.add(new Slide(R.drawable.aquaman_hd_zack_snyders_justice_league,"Justice League"));
        listSlide.add(new Slide(R.drawable.harleen_quinzel_harley_quinn_margot_robbie_4k_hd_the_suicide_squad,"Suicide Squad"));


        listSlide.add(new Slide(R.drawable.alastor_moody_brendan_gleeson_daniel_radcliffe_hd_harry_potter,"Harry Potter"));


        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(VideoTemplate.this,listSlide);

        slidePager.setAdapter(sliderPagerAdapter);

        //recyclerview setup

        List<Video> mVideo = new ArrayList<>();
        mVideo.add(new Video("Harry Potter","Best Movie Ever",R.drawable.alastor_moody_brendan_gleeson_daniel_radcliffe_hd_harry_potter));
        mVideo.add(new Video("Suicide Squad","OH fuck this is amazing",R.drawable.harleen_quinzel_harley_quinn_margot_robbie_4k_hd_the_suicide_squad));
        mVideo.add(new Video("Justice League","THIS IS SYNIDER CUT",R.drawable.aquaman_hd_zack_snyders_justice_league));




        VideoAdapter videoAdapter = new VideoAdapter(VideoTemplate.this,mVideo);

        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));






    }


}