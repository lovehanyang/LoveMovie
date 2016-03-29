package com.lanou.lovemovie.dock;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.dock.chat.chatroomselect.ChatMovie;
import com.lanou.lovemovie.dock.listen.ListenMovie;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.MainContentActivity;
import com.lanou.lovemovie.dock.read.kmshack.newsstand.ReadMovie;
import com.lanou.lovemovie.dock.watch.WatchActivity.WatchMovie;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by dllo on 15/8/28.
 */
public class TabHostHomeActivity extends TabActivity {
    public TabHost mTabHost;
    private RadioGroup mRadioGroup;
    public static RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_tabhost);

        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);



            SystemBarTintManager tintManager = new SystemBarTintManager(this);

            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(Color.parseColor("#2E2E2E"));

            /*改变titlebar的高度
            int statusbarHeight = ScreenUtils.getStatusHeight(this);*/
        }

        //新建一个网络请求队列
        queue = Volley.newRequestQueue(this);
        init();
    }





    private void init() {



        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_botnav);

        mTabHost.addTab(mTabHost.newTabSpec("watch")
                .setIndicator("watch").setContent(new Intent(this, WatchMovie.class)));
        mTabHost.addTab(mTabHost.newTabSpec("listen")
                .setIndicator("listen").setContent(new Intent(this, ListenMovie.class)));
        mTabHost.addTab(mTabHost.newTabSpec("read")
                .setIndicator("read").setContent(new Intent(this, ReadMovie.class)));
        mTabHost.addTab(mTabHost.newTabSpec("chat")
                .setIndicator("chat").setContent(new Intent(this, ChatMovie.class)));

        mTabHost.addTab(mTabHost.newTabSpec("musicplay")
                .setIndicator("musicplay").setContent((new Intent(this, MainContentActivity.class))));


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.bn_watch:
                        mTabHost.setCurrentTabByTag("watch");
                        break;
                    case R.id.bn_listen:
                        mTabHost.setCurrentTabByTag("listen");
                        break;
                    case R.id.bn_read:
                        mTabHost.setCurrentTabByTag("read");
                        break;
                    case R.id.bn_chat:
                        mTabHost.setCurrentTabByTag("chat");
                        break;
                    default:
                        break;
                }
            }
        });

    }



    //这个实在不知道是在干啥
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_menu, menu);
        ActionBar actionBar = getActionBar();



        return true;
    }

    //UP 是松开按键事件，DOWN是按下按键事件
    //如果不加event.getAction() == KeyEvent.ACTION_DOWN则按键会相应两次
    //在tabactivity中无法监听到onKeyDown(int keyCode, KeyEvent event)方法
    private long mExitTime;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "亲爱的,再按一次退出呦~~~",
                            Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            return true;
        }

        //拦截MENU按钮点击事件，让他无任何操作
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(event.getKeyCode(), event);
    }



}
