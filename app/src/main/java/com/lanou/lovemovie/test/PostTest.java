package com.lanou.lovemovie.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.code.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/8/27.
 */
public class PostTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                post();
            }
        }).start();

    }

    //建立一个Post请求
    public static void post(){
        //构建一个httpclient客户端
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://ser3.graphmovie.com/gmspanel/interface/zh-cn/3.1/M_MvVolScript.php");

        String test = StringUtils.getAes(Integer.valueOf("6438").intValue(), "0");
        httpPost.addHeader("aes",test);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        httpPost.addHeader("Connection", "Keep-Alive");
        httpPost.addHeader("User-Agent", "Paros/3.2.13");

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("movieid","6438"));
        params.add(new BasicNameValuePair("pub_platform","androidpad"));
        params.add(new BasicNameValuePair("volid","0"));
        params.add(new BasicNameValuePair("ver","34"));
        params.add(new BasicNameValuePair("pub_channel","xiaomi"));
        params.add(new BasicNameValuePair("userid","99OPJD2O"));


        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
            httpPost.setEntity(entity);


            HttpResponse response = client.execute(httpPost);// 判断是否链接成功
            Log.i("lovehanyang", "测试运行" + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 200) {
                // 获得返回实体对象
                HttpEntity entity2 = response.getEntity();
                // 将httpEntity对象通过EntityUtils工具类转化成为String对象
                String result = EntityUtils.toString(entity2, "utf-8");

                Log.i("lovehanyang",result);

            }else {
                Log.i("lovehanyang","失败了");
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}