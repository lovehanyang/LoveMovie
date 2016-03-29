package com.lanou.lovemovie.dock.personalcenter.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;
import com.lanou.lovemovie.dock.personalcenter.setting.AboutUs;

/**
 * Created by dllo on 15/9/14.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout aboutMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        aboutMe = (LinearLayout) findViewById(R.id.ll_my_about_we);
        aboutMe.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_about_we:
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                break;




            default:
                break;

        }
    }
}
