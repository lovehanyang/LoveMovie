package com.lanou.lovemovie.dock.watch.VideoPlayer;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.adapter.VideoRecyclerAdapter;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.bean.Video;
import com.lanou.lovemovie.http.GsonRequest;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by dllo on 15/9/11.
 */
public class VideoList extends BaseActivity {

    private VideoRecyclerAdapter adapter;
    private Handler handler;// 定义一个handler对象
    private int MovieId;
    private List<Video.VideoListEntity> datas;
    private RecyclerView recyclerView;
    private GifImageView gifLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view2);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);


        gifLoading = (GifImageView) findViewById(R.id.gifloading2);


        initNetWorkData();


        // /设置每个item之间的间距
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private void initNetWorkData() {


        Intent intent = getIntent();
        MovieId = intent.getIntExtra("MovieId",0);


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                gifLoading.setVisibility(View.GONE);
                if (message.what == 200) {

                    Video video = (Video) message.obj;
                    datas = video.getVideoList();

                    adapter = new VideoRecyclerAdapter(VideoList.this,datas);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new VideoRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, Video.VideoListEntity itemData) {
                            Intent intent = new Intent(VideoList.this, MVPlayer.class);
                            intent.putExtra("url", itemData.getUrl());
                            startActivity(intent);

                        }
                    });
                }
                return false;
            }
        });

        GsonRequest.MVList("http://api.m.mtime.cn/Movie/Video.api?movieId=" + MovieId + "&pageIndex=1", handler);

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
