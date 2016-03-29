package com.lanou.lovemovie.dock.listen.waterfall;

/**
 * Created by dllo on 15/9/1.
 */
public class StoreContent {
    private int imgId;
    private String musicDetail;

    public StoreContent(int imgId, String musicDetail) {
        this.imgId = imgId;
        this.musicDetail = musicDetail;
    }

    public StoreContent() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getMusicDetail() {
        return musicDetail;
    }

    public void setMusicDetail(String musicDetail) {
        this.musicDetail = musicDetail;
    }
}
