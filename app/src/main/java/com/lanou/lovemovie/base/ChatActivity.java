package com.lanou.lovemovie.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lanou.lovemovie.dock.chat.HXChat.controller.HXSDKHelper;

/**
 * Created by dllo on 15/9/15.
 */
public class ChatActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onresume时，取消notification显示
        HXSDKHelper.getInstance().getNotifier().reset();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
