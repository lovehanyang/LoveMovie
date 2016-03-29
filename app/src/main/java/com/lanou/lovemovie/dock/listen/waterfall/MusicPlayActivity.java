package com.lanou.lovemovie.dock.listen.waterfall;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.dock.TabHostHomeActivity;

/**
 * Created by dllo on 15/8/28.
 */
public class MusicPlayActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout mContainer;
    private ImageView mStartBtn;
    private ImageView volumeChange;
    private PlayView mPlayView;
    private ImageView backToList;
    private boolean mIsPlay = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        mContainer = (FrameLayout) findViewById(R.id.media_container);
        mStartBtn = (ImageView) findViewById(R.id.btn_start_play);
        volumeChange = (ImageView) findViewById(R.id.btn_volume);
        backToList = (ImageView) findViewById(R.id.iv_back_list);

        backToList.setOnClickListener(this);

        mPlayView = new PlayView(this);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mContainer.addView(mPlayView, lp);

        mStartBtn.setOnClickListener(this);
        volumeChange.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        if (mPlayView != null) {
            mPlayView.stop();
            mPlayView.releaseBmp();
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_list:

                finish();

                break;

            case R.id.btn_start_play:

                if (mIsPlay) {
                    mPlayView.play();
                    mIsPlay = true;
                } else {
                    mPlayView.pause();
                }
                break;
            case R.id.btn_volume:

                break;

        }
    }
}
