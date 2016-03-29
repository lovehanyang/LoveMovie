package com.lanou.lovemovie.code;

import com.lanou.lovemovie.application.AppApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.MD5Util;

/**
 * Created by dllo on 15/8/27.
 */
public class StringUtils {


    protected static MessageDigest messagedigest = null;
    protected static char hexDigits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String getAes(int i, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j = UtilsTools.getRandomInt(0xf423f);
        stringbuffer.append(i << 5);
        stringbuffer.append(j >> 2);
        stringbuffer.append(s);
//        if (AppApplication.pInfo == null)
//        {
//            AppApplication.getInstance().initPhoneInfo();
//        }
        stringbuffer.append("867828024032993");
        stringbuffer.append("99OPJD2O");
        stringbuffer.append("34");
        stringbuffer.append("androidpad");
        stringbuffer.append("xiaomi");


        s = getMD5String(stringbuffer.toString());
        stringbuffer.setLength(0);
        stringbuffer.append(s);
        stringbuffer.append("&");
        stringbuffer.append(i);
        stringbuffer.append("&");
        stringbuffer.append(j);
        stringbuffer.append("&");
        stringbuffer.append("867828024032993");
        stringbuffer.append("&");
        stringbuffer.append("HM 2A");
        stringbuffer.append("&");
        stringbuffer.append("androidpad");
        stringbuffer.append("&");
        stringbuffer.append("xiaomi");
        stringbuffer.append("&");
        stringbuffer.append("99OPJD2O");
        stringbuffer.append("&");
        stringbuffer.append("34");
        return stringbuffer.toString();
    }

    public static boolean strIsEmpty(String s)
    {
        return s == null || s.trim().length() <= 0;
    }


    public static String getMD5String(String s)
    {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte abyte0[])
    {
        messagedigest.update(abyte0);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte abyte0[])
    {
        return bufferToHex(abyte0, 0, abyte0.length);
    }
    static
    {
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {

            nosuchalgorithmexception.printStackTrace();
        }
    }



    private static String bufferToHex(byte abyte0[], int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer(j * 2);
        int k = i;
        do
        {
            if (k >= i + j)
            {
                return stringbuffer.toString();
            }
            appendHexPair(abyte0[k], stringbuffer);
            k++;
        } while (true);
    }
    private static void appendHexPair(byte byte0, StringBuffer stringbuffer)
    {
        char c = hexDigits[(byte0 & 0xf0) >> 4];
        char c1 = hexDigits[byte0 & 0xf];
        stringbuffer.append(c);
        stringbuffer.append(c1);
    }
}
