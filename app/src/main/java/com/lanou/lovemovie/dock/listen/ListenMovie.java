package com.lanou.lovemovie.dock.listen;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.adapter.FancyCoverAdapter;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.bean.HotPlayMovies;
import com.lanou.lovemovie.dock.TabHostHomeActivity;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.MainContentActivity;
import com.lanou.lovemovie.dock.listen.waterfall.MusicPlayActivity;
import com.lanou.lovemovie.dock.listen.waterfall.RecyclerviewAdapter;
import com.lanou.lovemovie.dock.listen.waterfall.StoreContent;
import com.lanou.lovemovie.http.GsonRequest;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by dllo on 15/8/27.
 */
public class ListenMovie extends BaseActivity implements View.OnClickListener {
    private ImageView playingNow;
    private ImageView playList;
    private List<StoreContent> datas;
    private RecyclerviewAdapter adapter;
    private Handler handler;// 定义一个handler对象

    private HotPlayMovies hotPlayMovies;
    private List<HotPlayMovies.MoviesEntity> newDatas;
    private RecyclerView recyclerView;
    private GifImageView gifLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        gifLoading = (GifImageView) findViewById(R.id.gifloading);


        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        playingNow = (ImageView) findViewById(R.id.iv_playing_now);
        playingNow.setOnClickListener(this);

        playList = (ImageView) findViewById(R.id.iv_playlist);
        playList.setOnClickListener(this);

        initNetworkdata();

        // /设置每个item之间的间距
        //SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        //recyclerView.addItemDecoration(decoration);


    }


    private void initNetworkdata() {

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                gifLoading.setVisibility(View.GONE);
                if (message.what == 100) {
                    hotPlayMovies = (HotPlayMovies) message.obj;
                    newDatas = hotPlayMovies.getMovies();

                    adapter = new RecyclerviewAdapter(ListenMovie.this, newDatas);
                    recyclerView.setAdapter(adapter);


                }
                return false;
            }
        });

        //将网络请求单独封装成了方法~~~
        GsonRequest.MovieList("http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=729", handler);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_playing_now:
                Intent intent = new Intent(this, MusicPlayActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_playlist:

                TabHostHomeActivity tabHostHomeActivity = (TabHostHomeActivity) getParent();
                tabHostHomeActivity.mTabHost.setCurrentTabByTag("musicplay");


            default:
                break;
        }
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }

    }


}



