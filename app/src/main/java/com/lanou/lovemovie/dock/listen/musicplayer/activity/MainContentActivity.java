package com.lanou.lovemovie.dock.listen.musicplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.dock.listen.musicplayer.db.MusicInfoDao;
import com.lanou.lovemovie.dock.listen.musicplayer.fragment.MainFragment;
import com.lanou.lovemovie.dock.listen.musicplayer.utils.MusicUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/9/1.
 */
public class MainContentActivity extends FragmentActivity implements IConstants {
    public MainFragment mMainFragment;

    private Handler mHandler;
    private MusicInfoDao mMusicDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSDCard();
        setContentView(R.layout.frame_main);

        mMusicDao = new MusicInfoDao(this);

        getData();

        mMainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, mMainFragment).commit();


    }

    private void initSDCard() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1000);// 设置最高优先级
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);// sd卡被插入，且已经挂载
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// sd卡存在，但还没有挂载
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);// sd卡被移除
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// sd卡作为USB大容量存储被共享，挂载被解除
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);// sd卡已经从sd卡插槽拔出，但是挂载点还没解除
        intentFilter.addDataScheme("file");
        registerReceiver(sdCardReceiver, intentFilter);// 注册监听函数
    }

    private final BroadcastReceiver sdCardReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.MEDIA_REMOVED")// 各种未挂载状态
                    || action.equals("android.intent.action.MEDIA_UNMOUNTED")
                    || action.equals("android.intent.action.MEDIA_BAD_REMOVAL")
                    || action.equals("android.intent.action.MEDIA_SHARED")) {
                finish();
                Toast.makeText(MainContentActivity.this, "SD卡以外拔出，本地数据无法初始化!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void getData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mMusicDao.hasData()) {
                 //mHandler.sendMessageDelayed(mHandler.obtainMessage(), 3000);
                } else {
                    MusicUtils.queryMusic(MainContentActivity.this,
                            START_FROM_LOCAL);
                    MusicUtils.queryAlbums(MainContentActivity.this);
                    MusicUtils.queryArtist(MainContentActivity.this);
                    MusicUtils.queryFolder(MainContentActivity.this);
                   // mHandler.sendEmptyMessage(1);
                }
            }
        }).start();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sdCardReceiver);
        AppApplication.mServiceManager.exit();
        AppApplication.mServiceManager = null;

        MusicUtils.clearCache();
        System.exit(0);


    }

}
