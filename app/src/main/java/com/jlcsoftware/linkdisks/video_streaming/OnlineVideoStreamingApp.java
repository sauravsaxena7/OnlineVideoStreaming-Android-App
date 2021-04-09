package com.jlcsoftware.linkdisks.video_streaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jlcsoftware.linkdisks.R;

import java.util.ArrayList;
import java.util.List;

public class OnlineVideoStreamingApp extends AppCompatActivity {


    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static final  String Video_test_url="http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       setFullScreen();
        
        setContentView(R.layout.activity_online_video_streaming_app);

        initExoPlayer();

    }

    private void setFullScreen() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void initExoPlayer() {

        playerView=findViewById(R.id.video_player);
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"appname"));

        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(Video_test_url));

        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}