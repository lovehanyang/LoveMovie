package com.lanou.lovemovie.dock.watch.VideoPlayer;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.bean.Video;

/**
 * Created by hanyang on 2015/9/11 0011.
 */
public class MVPlayer extends BaseActivity {


    VideoView videoView;
    MediaController mController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_videoplayer);

        //这句话使窗口支持透明度然后就可以用setAlpha,drawColor等函数来设置窗口透明程度
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        videoView = (VideoView) findViewById(R.id.video);

        mController = new MediaController(this);


        initData();


    }




    private void initData(){


        Intent intent = getIntent();

        String url = intent.getStringExtra("url");
        Uri newUri = Uri.parse(url);
        videoView.setVideoURI(newUri);

        videoView.setMediaController(mController);
        mController.setMediaPlayer(videoView);

        videoView.requestFocus();

        if(!videoView.isPlaying()){
            videoView.start();
        }


    }

}
