package com.lanou.lovemovie.dock.watch.WatchActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.adapter.CommingSoonAdapter;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.bean.CommingMovie;
import com.lanou.lovemovie.http.GsonRequest;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by dllo on 15/9/12.
 */
public class CommingSoonMovie extends BaseActivity implements View.OnClickListener {

    private ImageView BackButton;
    private ImageView RefreshButton;
    private RecyclerView recyclerView;
    private Handler handler;// 定义一个handler对象
    private CommingSoonAdapter adapter;
    private GifImageView gifLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_commingsoon);
        BackButton = (ImageView) findViewById(R.id.btn_back_new);
        RefreshButton = (ImageView) findViewById(R.id.refresh_view_2);
        gifLoading = (GifImageView) findViewById(R.id.gifloading3);
        BackButton.setOnClickListener(this);
        RefreshButton.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.commingsoon_recycler_view);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initNetworkdata();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_new:
                finish();

                break;
            case R.id.refresh_view_2:
                initNetworkdata();
                Toast.makeText(this,"韩洋正在帮您刷新",Toast.LENGTH_SHORT).show();

                break;
            default:
                break;


        }
    }

    private void initNetworkdata() {

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                gifLoading.setVisibility(View.GONE);
                if (message.what == 400) {
                    CommingMovie datas = (CommingMovie) message.obj;

                    List<CommingMovie.MoviecomingsEntity> moviecomings = datas.getMoviecomings();
                    adapter = new CommingSoonAdapter(CommingSoonMovie.this, moviecomings);
                    recyclerView.setAdapter(adapter);
                }

                return false;
            }
        });

        //将网络请求单独封装成了方法~~~
        GsonRequest.CommingSoonMovie("http://api.m.mtime.cn/Movie/MovieComingNew.api?locationId=729", handler);

    }

}
