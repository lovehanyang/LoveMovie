package com.lanou.lovemovie.code;

import android.content.Context;

import java.util.Random;

/**
 * Created by dllo on 15/8/27.
 */
public class UtilsTools {

    public static int getRandomInt(int i)
    {
        int j = 0;
        if (i != 0)
        {
            j = Math.abs((new Random()).nextInt()) % i + 1;
        }
        return j;
    }
    public static String getTagString(Context context, String s, String s1)
    {
        if (context == null)
        {
            return s1;
        } else
        {
            return context.getSharedPreferences("MOVIE", 0).getString(s, s1);
        }
    }
    public static void setTagString(Context context, String s, String s1)
    {
        context = (Context) context.getSharedPreferences("MOVIE",0).edit();
//        context.putString(s, s1);
//        context.commit();
    }



}
