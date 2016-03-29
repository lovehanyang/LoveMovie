/**
 * Copyright (c) www.longdw.com
 */
package com.lanou.lovemovie.dock.listen.musicplayer.uimanager;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lanou.lovemovie.MusicInfo;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.dock.listen.musicplayer.service.ServiceManager;
import com.lanou.lovemovie.dock.listen.musicplayer.utils.MusicUtils;
import com.lanou.lovemovie.dock.listen.musicplayer.view.AlwaysMarqueeTextView;

/**
 * 我的音乐底部View控制
 *
 * @author longdw(longdawei1988@gmail.com)
 */
public class MainBottomUIManager implements View.OnClickListener {

    private Activity mActivity;
    private View mView;
    private ServiceManager mServiceManager;
    private AlwaysMarqueeTextView mMusicNameTv, mArtistTv;
    private TextView mPositionTv, mDurationTv;
    private ImageView mPlayBtn, mPauseBtn, mNextBtn, mPrevBtn;
    private ProgressBar mPlaybackProgress;
    public Handler mHandler;
    private Bitmap mDefaultAlbumIcon;
    private ImageView mHeadIcon;

    public MainBottomUIManager(Activity a, View view) {
        this.mView = view;
        this.mActivity = a;
        this.mServiceManager = AppApplication.mServiceManager;
        initView();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshSeekProgress(mServiceManager.position(),
                        mServiceManager.duration());
            }
        };
    }

    private void initView() {
        mMusicNameTv = (AlwaysMarqueeTextView) findViewById(R.id.musicname_tv2);
        mArtistTv = (AlwaysMarqueeTextView) findViewById(R.id.artist_tv2);
        mPositionTv = (TextView) findViewById(R.id.position_tv2);
        mDurationTv = (TextView) findViewById(R.id.duration_tv2);

        mPlayBtn = (ImageView) findViewById(R.id.btn_play2);
        mPauseBtn = (ImageView) findViewById(R.id.btn_pause2);
        mNextBtn = (ImageView) findViewById(R.id.btn_playNext2);
        mPrevBtn = (ImageView) findViewById(R.id.btn_play_prev);

        mPlayBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mPrevBtn.setOnClickListener(this);

        mPlaybackProgress = (ProgressBar) findViewById(R.id.playback_seekbar2);

        mDefaultAlbumIcon = BitmapFactory.decodeResource(
                mActivity.getResources(), R.drawable.img_album_background);

        mHeadIcon = (ImageView) findViewById(R.id.headicon_iv);
    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }

    public void refreshSeekProgress(int curTime, int totalTime) {

        curTime /= 1000;
        totalTime /= 1000;
        int curminute = curTime / 60;
        int cursecond = curTime % 60;

        String curTimeString = String.format("%02d:%02d", curminute, cursecond);
        mPositionTv.setText(curTimeString);

        int rate = 0;
        if (totalTime != 0) {
            rate = (int) ((float) curTime / totalTime * 100);
        }
        mPlaybackProgress.setProgress(rate);
    }

    public void refreshUI(int curTime, int totalTime, MusicInfo music) {

        int tempCurTime = curTime;
        int tempTotalTime = totalTime;

        totalTime /= 1000;
        int totalminute = totalTime / 60;
        int totalsecond = totalTime % 60;
        String totalTimeString = String.format("%02d:%02d", totalminute,
                totalsecond);

        mDurationTv.setText(totalTimeString);

        mMusicNameTv.setText(music.musicName);
        mArtistTv.setText(music.artist);

        Bitmap bitmap = MusicUtils.getCachedArtwork(mActivity, music.albumId,
                mDefaultAlbumIcon);

        mHeadIcon.setBackgroundDrawable(new BitmapDrawable(mActivity
                .getResources(), bitmap));
        refreshSeekProgress(tempCurTime, tempTotalTime);
    }

    public void showPlay(boolean flag) {
        if (flag) {
            mPlayBtn.setVisibility(View.VISIBLE);
            mPauseBtn.setVisibility(View.GONE);
        } else {
            mPlayBtn.setVisibility(View.GONE);
            mPauseBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play2:
                mServiceManager.rePlay();
                break;
            case R.id.btn_pause2:
                mServiceManager.pause();
                break;
            case R.id.btn_playNext2:
                mServiceManager.next();
                break;

            case R.id.btn_play_prev:
                mServiceManager.prev();
                break;

        }
    }

}
