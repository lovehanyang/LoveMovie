package com.lanou.lovemovie.dock.watch.WatchActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.adapter.FancyCoverAdapter;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.bean.HotPlayMovies;
import com.lanou.lovemovie.http.GsonRequest;
import com.lanou.lovemovie.view.DrawerView;
import com.lanou.lovemovie.welcome.selectlocation.citylist.CityList;
import com.lanou.lovemovie.welcome.selectlocation.storage.Setting;

import java.util.List;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

/**
 * Created by dllo on 15/8/28.
 */
public class WatchMovie extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private static RelativeLayout blurBackground;
    private Handler handler;// 定义一个handler对象


    private ImageView personalCenter;
    private SlidingMenu side_drawer;
    private FancyCoverFlow fancyCoverFlow;
    private TextView Location;
    private ImageView changeLocation;

    private HotPlayMovies hotPlayMovies;
    private List<HotPlayMovies.MoviesEntity> datas;
    private TextView movieName;
    private TextView movieDescription;
    private TextView movieScore;
    private LinearLayout movieList;
    private LinearLayout releaseMovie;
    private LinearLayout commingSoonMovie;


    //正在热映
    private TextView totalHotMovie;
    //即将上映
    private TextView totalComingMovie;

    //如果之前定过位置了 就不用每次都定位了
    private SharedPreferences pref;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fancy_cover_flow);


        initView();
        initBottomView();

//      fancyCoverFlow.setReflectionEnabled(true);
//      fancyCoverFlow.setReflectionRatio(0.3f);
//      fancyCoverFlow.setReflectionGap(0);

        initNetworkdata();

        //初始化侧边栏
        initSlidingMenu();
        fancyCoverFlow.setOnItemSelectedListener(this);
        fancyCoverFlow.setOnItemClickListener(this);


        // 实例化一个缓存对象,并且要求数据只供本程序使用,外部不可访问
        //pref = getSharedPreferences("lanou", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();

        String location = Setting.LoadFromSharedPreferences(this, "city");
        if (location != null && location.length() > 0) {

            Location.setText(location);
        }
    }


    private void initView() {
        fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);

        blurBackground = (RelativeLayout) findViewById(R.id.blur_background);
        personalCenter = (ImageView) findViewById(R.id.personal_center);
        Location = (TextView) findViewById(R.id.location);
        changeLocation = (ImageView) findViewById(R.id.change_location);


        movieName = (TextView) findViewById(R.id.tv_hot_movie_name);
        movieDescription = (TextView) findViewById(R.id.tv_hot_movie_desc);
        movieScore = (TextView) findViewById(R.id.tv_hot_movie_score);
        totalHotMovie = (TextView) findViewById(R.id.tv_release_count);
        totalComingMovie = (TextView) findViewById(R.id.tv_will_count);


        Location.setOnClickListener(this);
        changeLocation.setOnClickListener(this);
        personalCenter.setOnClickListener(this);

        fancyCoverFlow.setSpacing(-250);
    }


    private void initBottomView() {


        //这是正在热映和即将上映
        movieList = (LinearLayout) findViewById(R.id.bottom_ll);
        movieList.requestFocus();

        releaseMovie = (LinearLayout) findViewById(R.id.ly_release);
        commingSoonMovie = (LinearLayout) findViewById(R.id.ly_will);

        releaseMovie.setOnClickListener(this);
        commingSoonMovie.setOnClickListener(this);


    }


    protected void initSlidingMenu() {
        side_drawer = new DrawerView(this).initSlidingMenu();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


        //blurBackground.setBackground(new BitmapDrawable(bitPics[position]));
        movieName.setText(datas.get(position).getTitleCn());
        movieDescription.setText(datas.get(position).getCommonSpecial() + "");
        movieScore.setText(datas.get(position).getRatingFinal() + "");

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_center:
                if (side_drawer.isMenuShowing()) {
                    side_drawer.showContent();
                } else {
                    side_drawer.showMenu();
                }
                break;

            case R.id.change_location:
                Intent intent = new Intent(this, CityList.class);
                startActivityForResult(intent, 1);

                break;

            case R.id.location:
                Intent intent2 = new Intent(this, CityList.class);
                startActivityForResult(intent2, 1);

            case R.id.ly_release:
                Intent intent3 = new Intent(this, HotMovie.class);
                startActivity(intent3);
                break;

            case R.id.ly_will:
                Intent intent1 = new Intent(this, CommingSoonMovie.class);
                startActivity(intent1);

                break;

            default:
                break;
        }
    }

    //点击跳转到详情页!!!!!
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("MovieId", datas.get(position).getMovieId());
        intent.putExtra("Location", "729");
        intent.putExtra("titleCn", datas.get(position).getTitleCn());
        intent.putExtra("img", datas.get(position).getImg());
        intent.putExtra("type", datas.get(position).getType());
        intent.putExtra("date", datas.get(position).getRMonth() + "月" + datas.get(position).getRDay() + "日");
        intent.putExtra("directorName", datas.get(position).getDirectorName());
        intent.putExtra("actorName1", datas.get(position).getActorName1());

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            switch (resultCode) {
                case 2:
                    Location.setText(data.getStringExtra("city"));
                    break;

                default:
                    break;
            }
    }

    private void initNetworkdata() {

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                if (message.what == 100) {
                    hotPlayMovies = (HotPlayMovies) message.obj;
                    datas = hotPlayMovies.getMovies();
                    fancyCoverFlow.setAdapter(new FancyCoverAdapter(WatchMovie.this, datas));

                    totalHotMovie.setText(hotPlayMovies.getTotalHotMovie() + "");
                    totalComingMovie.setText(hotPlayMovies.getTotalComingMovie() + "");

                }
                return false;
            }
        });

        //将网络请求单独封装成了方法~~~
        GsonRequest.MovieList("http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=729", handler);


    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(Drawable drawable) {
        blurBackground.setBackground(drawable);


    }

}
