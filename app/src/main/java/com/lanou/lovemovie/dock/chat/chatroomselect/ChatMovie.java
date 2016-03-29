package com.lanou.lovemovie.dock.chat.chatroomselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.florent37.carpaccio.Carpaccio;
import com.lanou.lovemovie.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dllo on 15/8/27.
 */
public class ChatMovie extends AppCompatActivity implements View.OnClickListener {

    private ImageView Refresh;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.carpaccio)
    Carpaccio carpaccio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_select);
        ButterKnife.bind(this);


        Refresh = (ImageView) findViewById(R.id.refresh_view);
        Refresh.setOnClickListener(this);

        //setSupportActionBar(toolbar);
        //toolbar.setTitleTextColor(0xFFFFFFFF);

        //CarpaccioLogger.ENABLE_LOG = true;
        //List<SampleObject> objects = SampleObjectsFactory.generateList();

        initData();

    }


    private void initData() {


        List<SampleObject> objects = new ArrayList<SampleObject>();
        SampleObject item1 = new SampleObject("小清新", "http://www.51pinwei.com/uploads/allimg/141120/1336-141120154614438.jpg", "http://i1.sinaimg.cn/ent/r/m/2014-12-19/U12106P28T3D4260001F326DT20141219113406.jpg");
        SampleObject item2 = new SampleObject("欧美大片", "http://pic20.nipic.com/20120425/9407671_184539324000_2.jpg", "http://p9.qhimg.com/t0102064a7a8bd329c0.png");
        SampleObject item3 = new SampleObject("爱情片", "http://img.funshion.com/attachment/images3/30/jetty/2003725195345.1431438.jpg", "http://pic.baike.soso.com/p/20140221/20140221015302-1386514338.jpg");
        SampleObject item4 = new SampleObject("喜剧片", "http://www.aislife.cn/uploadfile/ETUpload/201505/11/et71020110908411.jpg", "http://www.cnr.cn/xjfw/2014xjfw/tyyl/20150728/W020150728344695471575.jpg");
        SampleObject item5 = new SampleObject("动作片", "http://img31.mtime.cn/pi/2012/12/07/110007.12285603.jpg", "http://www.cnr.cn/ent/list/201405/W020140513323759288534.jpg");


        objects.add(item1);
        objects.add(item2);
        objects.add(item3);
        objects.add(item4);
        objects.add(item5);

        carpaccio.mapList("object", objects);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh_view:
                //重建页面
                onCreate(Bundle.EMPTY);
                Toast.makeText(this, "韩洋正在帮您刷新...", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }
}
