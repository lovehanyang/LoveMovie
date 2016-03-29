package com.lanou.lovemovie.bean;

import java.util.List;

/**
 * Created by dllo on 15/9/10.
 */
public class MovieDetails {

    private int id;
    private String nickname;
    private String topImgUrl;
    private String title;
    private String time;
    private String content;
    private String summaryInfo;

    public MovieDetails(String content, int id, String nickname, String summaryInfo, String time, String title, String topImgUrl) {
        this.content = content;
        this.id = id;
        this.nickname = nickname;
        this.summaryInfo = summaryInfo;
        this.time = time;
        this.title = title;
        this.topImgUrl = topImgUrl;
    }


    public MovieDetails() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(String summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopImgUrl() {
        return topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }
}
