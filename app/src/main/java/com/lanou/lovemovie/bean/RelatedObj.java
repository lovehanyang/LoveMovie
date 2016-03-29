package com.lanou.lovemovie.bean;

/**
 * Created by dllo on 15/9/12.
 */
public class RelatedObj {

    private int type;
    private int id;
    private String title;
    private String rating;
    private String image;


    public RelatedObj() {
    }

    public RelatedObj(int id, String image, String rating, String title, int type) {
        this.id = id;
        this.image = image;
        this.rating = rating;
        this.title = title;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
