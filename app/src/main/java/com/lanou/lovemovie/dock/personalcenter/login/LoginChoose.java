package com.lanou.lovemovie.dock.personalcenter.login;

import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;

import utils.ScreenUtils;

/**
 * Created by dllo on 15/9/14.
 */
public class LoginChoose extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login_choose);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏显示

        WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
        lp.gravity = Gravity.BOTTOM;
        lp.width = ScreenUtils.getScreenWidth(this);
        getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框

    }



}
