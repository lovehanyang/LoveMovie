package com.lanou.lovemovie.bean;

/**
 * Created by dllo on 15/9/12.
 */
public class MovieReview {

    private int id;
    private String nickname;
    private String userImage;
    private String rating;
    private String title;
    private String summary;
    private RelatedObj relatedObj;

    public MovieReview(int id, String nickname, String rating, RelatedObj relatedObj, String summary, String title, String userImage) {
        this.id = id;
        this.nickname = nickname;
        this.rating = rating;
        this.relatedObj = relatedObj;
        this.summary = summary;
        this.title = title;
        this.userImage = userImage;
    }

    public MovieReview() {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public RelatedObj getRelatedObj() {
        return relatedObj;
    }

    public void setRelatedObj(RelatedObj relatedObj) {
        this.relatedObj = relatedObj;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
