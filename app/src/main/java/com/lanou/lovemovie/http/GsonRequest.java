package com.lanou.lovemovie.http;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.lovemovie.bean.CommingMovie;
import com.lanou.lovemovie.bean.HotPlayMovies;
import com.lanou.lovemovie.bean.MovieDetails;
import com.lanou.lovemovie.bean.MovieReview;
import com.lanou.lovemovie.bean.Video;
import com.lanou.lovemovie.dock.TabHostHomeActivity;
import com.lanou.lovemovie.dock.watch.WatchActivity.CommingSoonMovie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/9/10.
 */
public class GsonRequest {

    public static void MovieList(String url, final Handler handler) {

        // 1.创建url链接对象
        String path = url;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null && s.length() > 2) {
                    Gson gson = new Gson();

                    HotPlayMovies hotPlayMovies = gson.fromJson(s, HotPlayMovies.class);
                    //List<HotPlayMovies.MoviesEntity> datas= hotPlayMovies.getMovies();
                    //Log.i("lovehanyang","测试数据:" + hotPlayMovies.toString()+"");

                    // 从handler身上获取消息对象
                    Message msg = handler.obtainMessage();
                    // 设置消息类型
                    msg.what = 100;

                    // 设置要传递的对象
                    msg.obj = hotPlayMovies;
                    handler.sendMessage(msg);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        TabHostHomeActivity.queue.add(request);


    }


    public static void MVList(String url, final Handler handler) {

        // 1.创建url链接对象
        String path = url;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null && s.length() > 2) {
                    Gson gson = new Gson();

                    Video video = gson.fromJson(s, Video.class);

                    // 从handler身上获取消息对象
                    Message msg = handler.obtainMessage();
                    // 设置消息类型
                    msg.what = 200;

                    // 设置要传递的对象
                    msg.obj = video;
                    handler.sendMessage(msg);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        TabHostHomeActivity.queue.add(request);


    }


    public static void MovieReview(String url, final Handler handler) {

        // 1.创建url链接对象
        String path = url;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null && s.length() > 2) {
                    Gson gson = new Gson();

                    Type type = new TypeToken<ArrayList<MovieReview>>() {
                    }.getType();

                    ArrayList<MovieReview> datas = gson.fromJson(s, type);


                    // 从handler身上获取消息对象
                    Message msg = handler.obtainMessage();
                    // 设置消息类型
                    msg.what = 300;

                    // 设置要传递的对象
                    msg.obj = datas;
                    handler.sendMessage(msg);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        TabHostHomeActivity.queue.add(request);


    }

    public static void CommingSoonMovie(String url, final Handler handler) {

        // 1.创建url链接对象
        String path = url;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null && s.length() > 2) {
                    Gson gson = new Gson();

                    CommingMovie datas = gson.fromJson(s, CommingMovie.class);

                    // 从handler身上获取消息对象
                    Message msg = handler.obtainMessage();
                    // 设置消息类型
                    msg.what = 400;

                    // 设置要传递的对象
                    msg.obj = datas;
                    handler.sendMessage(msg);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        TabHostHomeActivity.queue.add(request);

    }

    public static void MovieDetails(String url, final Handler handler) {
        // 1.创建url链接对象
        String path = url;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                if (s != null && s.length() > 2) {

                    Gson gson = new Gson();
                    MovieDetails datas = gson.fromJson(s, MovieDetails.class);
                    // 从handler身上获取消息对象
                    Message msg = handler.obtainMessage();
                    // 设置消息类型
                    msg.what = 500;

                    // 设置要传递的对象
                    msg.obj = datas;
                    handler.sendMessage(msg);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        TabHostHomeActivity.queue.add(request);
    }


}
