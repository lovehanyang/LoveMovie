package com.lanou.lovemovie.code;

/**
 * Created by dllo on 15/8/27.
 */
public class V3PhoneInfo {

    public String age;
    public int android_ver;
    public MenuItem appwall;
    public String avatar;
    public String avr;
    public String baiduyts;
    public MenuItem bbs;
    public String befollow;
    public String belike;
    public String channel;
    public String comment_new;
    public String email;
    public String follow;
    public MenuItem friend;
    public MenuItem gift;
    public MenuItem guess;
    public MenuItem guesslike;
    public MenuItem home;
    public String imei;
    public String intro;
    public String name;
    public String news;
    public String package_name;
    public String phone_type;
    public String platfrom;
    public String qq;
    public MenuItem ranking;
    public String sex;
    public String sinawb;
    public MenuItem topic;
    public String userid;
    public String version_code;
    public String version_name;
    public String works;

    public V3PhoneInfo()
    {
        imei = "unknow";
        channel = "unknow";
        version_code = "1";
        version_name = "1.0";
        package_name = "unknow";
        userid = "0";
        phone_type = "unknow";
        platfrom = "androidpad";
        name = "\u65AD\u7F51\u533F\u540D\u541B";
        intro = "\u8FD9\u5BB6\u4F19\u5F88\u61D2\uFF0C\u5565\u90FD\u6CA1\u7559\u4E0B";
        sex = "0";
        android_ver = android.os.Build.VERSION.SDK_INT;
        avatar = "";
        email = "";
        age = "0";
        baiduyts = "0";
        qq = "0";
        sinawb = "0";
        avr = "0";
        works = "0";
        follow = "0";
        befollow = "0";
        news = "0";
        belike = "0";
        comment_new = "0";
    }
}
