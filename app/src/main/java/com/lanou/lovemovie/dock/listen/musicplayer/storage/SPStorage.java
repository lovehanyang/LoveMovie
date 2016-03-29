package com.lanou.lovemovie.dock.listen.musicplayer.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.lanou.lovemovie.dock.listen.musicplayer.activity.IConstants;

/**
 * Created by dllo on 15/9/3.
 */
public class SPStorage implements IConstants {


    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;

    public SPStorage(Context context) {
        mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mEditor = mSp.edit();
    }



    //保存背景图片的地址
    public void savePath(String path){
        mEditor.putString(SP_BG_PATH,path);
        mEditor.commit();
    }

    //获取背景图片的地址
    public String getPath(){
        return mSp.getString(SP_BG_PATH,null);
    }

    //保存是否开启摇一摇
    public void saveShake(boolean shake){
        mEditor.putBoolean(SP_SHAKE_CHANGE_SONG,shake);
        mEditor.commit();
    }

    //读取保存的摇一摇设置
    public  boolean getShake(){
        return mSp.getBoolean(SP_SHAKE_CHANGE_SONG,false);
    }

    //保存是否自动下载歌词
    public void saveAutoLyric(boolean auto){
        mEditor.putBoolean(SP_AUTO_DOWNLOAD_LYRIC,auto);
        mEditor.commit();
    }

    //读取是否自动下载歌词的设置
    public boolean getAutoLyric(){
        return mSp.getBoolean(SP_AUTO_DOWNLOAD_LYRIC,false);
    }


    public void saveFilterSize(boolean size) {
        mEditor.putBoolean(SP_FILTER_SIZE, size);
        mEditor.commit();
    }

    public boolean getFilterSize() {
        return mSp.getBoolean(SP_FILTER_SIZE, false);
    }

    public void saveFilterTime(boolean time) {
        mEditor.putBoolean(SP_FILTER_TIME, time);
        mEditor.commit();
    }

    public boolean getFilterTime() {
        return mSp.getBoolean(SP_FILTER_TIME, false);
    }





}
