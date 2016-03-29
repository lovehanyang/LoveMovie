package com.lanou.lovemovie.dock.personalcenter.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.base.BaseActivity;

/**
 * Created by dllo on 15/9/14.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    Button RegisteBtn;
    Button LoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);


        RegisteBtn = (Button) findViewById(R.id.bt_register);
        RegisteBtn.setOnClickListener(this);
        LoginBtn = (Button) findViewById(R.id.bt_login);
        LoginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login:

                Intent intent1 = new Intent(this, LoginChoose.class);
                startActivity(intent1);
                //添加开启activity的动画
                overridePendingTransition(R.anim.dialog_phone_enter, 0);
                break;

            default:
                break;
        }
    }
}
