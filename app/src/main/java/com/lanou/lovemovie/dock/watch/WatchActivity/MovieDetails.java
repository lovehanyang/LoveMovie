package com.lanou.lovemovie.dock.watch.WatchActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.dock.watch.VideoPlayer.VideoList;
import com.lanou.lovemovie.selectposition.activity.ChooesSeatActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by dllo on 15/8/31.
 */
public class MovieDetails extends BaseActivity implements View.OnClickListener {

    private ImageView moviePosterPic;
    private TextView publishTime;
    private TextView Type;
    private LinearLayout buyTickets;
    private TextView Director;
    private TextView Actor;
    private TextView hotComment;
    private TextView filmName;
    private ImageButton playMovie;


    private int MovieId;
    private String Location;
    private String titleCn;
    private String img;
    private String type;
    private String date;
    private String directorName;
    private String actorName1;
    private ImageLoader imageLoader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.film_detail_comment_list_header);


        init();
        initData();
        initUI();

    }

    private void init() {


        moviePosterPic = (ImageView) findViewById(R.id.film_poster_img);
        publishTime = (TextView) findViewById(R.id.data);
        Type = (TextView) findViewById(R.id.film_type);
        Director = (TextView) findViewById(R.id.film_detail_director_captain);
        Actor = (TextView) findViewById(R.id.film_detail_actor_captain);
        hotComment = (TextView) findViewById(R.id.film_detail_story_captain);
        filmName = (TextView) findViewById(R.id.film_detail_poster_film_name);
        playMovie = (ImageButton) findViewById(R.id.play_movie);

        buyTickets = (LinearLayout) findViewById(R.id.buy_tickets);
        buyTickets.setOnClickListener(this);
        playMovie.setOnClickListener(this);

    }


    public void initData() {


        Intent intent = getIntent();

        MovieId = intent.getIntExtra("MovieId",0);


        Location = intent.getStringExtra("Location");
        titleCn = intent.getStringExtra("titleCn");
        img = intent.getStringExtra("img");
        type = intent.getStringExtra("type");
        date = intent.getStringExtra("date");
        directorName = intent.getStringExtra("directorName");
        actorName1 = intent.getStringExtra("actorName1");


        //获取imageLoader的实例
        imageLoader = ImageLoader.getInstance();
        //初始化imageloader
        imageLoader.init(ImageLoaderConfiguration.createDefault(AppApplication.getContext()));
    }


    private void initUI() {
        filmName.setText(titleCn + "");
        Type.setText("类型:  " + type + "");
        publishTime.setText(date + "上映");
        Director.setText("导演:  " + directorName + "");
        Actor.setText("主演:  " + actorName1 + "");

        imageLoader.displayImage(img, moviePosterPic);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_tickets:
                Intent intent = new Intent(this, ChooesSeatActivity.class);
                startActivity(intent);
                break;
            case R.id.play_movie:
                Intent intent1 = new Intent(this, VideoList.class);
                intent1.putExtra("MovieId",MovieId);


                startActivity(intent1);

                break;
            default:
                break;


        }
    }
}
