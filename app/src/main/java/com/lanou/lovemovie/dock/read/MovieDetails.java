package com.lanou.lovemovie.dock.read;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.http.GsonRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by dllo on 15/9/14.
 */
public class MovieDetails extends BaseActivity {

    TextView movieName;
    TextView movieScore;
    TextView detailTitle;
    TextView articleDate;
    TextView Author;
    WebView webView;
    int id;
    private Handler handler;// 定义一个handler对象
    private GifImageView gifLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.read_movie_details);
        init();
        initData();
    }

    private void init() {
        movieName = (TextView) findViewById(R.id.movie_name);
        detailTitle = (TextView) findViewById(R.id.news_detals_title);
        articleDate = (TextView) findViewById(R.id.news_detals_date);
        Author = (TextView) findViewById(R.id.author);
        movieScore = (TextView) findViewById(R.id.details_score);
        webView = (WebView) findViewById(R.id.news_webView);
        gifLoading = (GifImageView) findViewById(R.id.gifloading_details);

    }

    private void initData() {

        Intent intent = getIntent();
        movieName.setText(intent.getStringExtra("movieName"));
        detailTitle.setText(intent.getStringExtra("detailTitle"));
        Author.setText(intent.getStringExtra("Author"));
        movieScore.setText(intent.getStringExtra("movieScore"));

        id = intent.getIntExtra("id", 0);

        initNewworkData();
    }

    private void initNewworkData() {

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                gifLoading.setVisibility(View.GONE);
                if (message.what == 500) {
                    com.lanou.lovemovie.bean.MovieDetails movieDetails = (com.lanou.lovemovie.bean.MovieDetails) message.obj;
                    String data = movieDetails.getContent();
                    Document doc_Dis = Jsoup.parse(data);
                    Elements ele_Img = doc_Dis.getElementsByTag("img");
                    if (ele_Img.size() != 0) {
                        for (Element e_Img : ele_Img) {
                            e_Img.attr("style", "width:100%");
                        }
                    }
                    String newHtmlContent = doc_Dis.toString();

                    webView.loadDataWithBaseURL("about:blank", newHtmlContent, "text/html", "utf-8", null);


                }
                return false;
            }
        });

        GsonRequest.MovieDetails("http://api.m.mtime.cn/Review/Detail.api?reviewId=" + id, handler);
    }


}
