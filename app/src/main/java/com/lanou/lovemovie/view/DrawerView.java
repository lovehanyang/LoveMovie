package com.lanou.lovemovie.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.dock.buytickets.OrderDetail;
import com.lanou.lovemovie.dock.personalcenter.QRCodeScan;
import com.lanou.lovemovie.dock.personalcenter.login.LoginActivity;
import com.lanou.lovemovie.dock.personalcenter.login.SettingActivity;

/**
 * Created by dllo on 15/8/28.
 */
public class DrawerView implements View.OnClickListener {

    SlidingMenu localSlidingMenu;
    RelativeLayout header;
    LinearLayout myTickets;
    LinearLayout QRcode;
    LinearLayout Collection;
    LinearLayout Setting;


    private final Activity activity;

    public DrawerView(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu initSlidingMenu() {
        localSlidingMenu = new SlidingMenu(activity);
        localSlidingMenu.setMode(SlidingMenu.RIGHT);//设置左右滑菜单
        localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动，触碰屏幕的范围
        localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
        localSlidingMenu.setShadowDrawable(R.drawable.shadowright);//设置阴影图片
        localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
        localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
        localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//使SlidingMenu附加在Activity右边
        localSlidingMenu.setSecondaryMenu(R.layout.activity_my_home);

        localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            public void onOpened() {

            }
        });


        localSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {

            @Override
            public void onClosed() {
                // TODO Auto-generated method stub
            }
        });

        //对控件进行初始化
        initView();


        return localSlidingMenu;
    }


    public void initView() {
        header = (RelativeLayout) localSlidingMenu.findViewById(R.id.rl_pic);
        header.setOnClickListener(this);
        myTickets = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_my_movie_ticket);
        myTickets.setOnClickListener(this);
        QRcode = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_qr_code);
        QRcode.setOnClickListener(this);
        Collection = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_collection);
        Collection.setOnClickListener(this);
        Setting = (LinearLayout) localSlidingMenu.findViewById(R.id.ll_my_setting);
        Setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pic:
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;
            case R.id.ll_my_movie_ticket:
                Intent intent1 = new Intent(activity, OrderDetail.class);
                activity.startActivity(intent1);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ll_qr_code:
                Intent intent2 = new Intent(activity, QRCodeScan.class);
                activity.startActivity(intent2);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ll_collection:


                break;
            case R.id.ll_my_setting:
                Intent intent4 = new Intent(activity, SettingActivity.class);
                activity.startActivity(intent4);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


                break;

            default:
                break;
        }
    }
}
