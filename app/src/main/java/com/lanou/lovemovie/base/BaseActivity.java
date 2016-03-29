package com.lanou.lovemovie.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by dllo on 15/8/27.
 */
public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
