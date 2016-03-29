package com.lanou.lovemovie.dock.listen.waterfall;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.media.ThumbnailUtils;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.lanou.lovemovie.R;


/**
 * Created by dllo on 15/8/28.
 */
public class PlayView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Context context;

    private Bitmap needleBmp;
    private Bitmap discBmp;
    private Bitmap discBgBmp;
    private Bitmap bgBmp;
    private Bitmap mLcBmp;

    private int mWidth;
    private int mHeight;


    //通过3*3矩阵进行变换
    private Matrix mDiscMatrix;
    private Matrix mLcMatrix;

    //设置间距
    private int interval = 10;


    private int cycleTime = 6000;
    //每次旋转的角度
    private float everyRotate = (360) / ((float) cycleTime / interval);

    private int mRotates;
    private boolean mRun = false;
    private boolean mIsPlay = false;


    private Thread mFrameThread;

    //线程操作经常用到wait和notify，用起来稍显繁琐，
    // 而Android给我们封装好了一个ConditionVariable类，
    // 用于线程同步。提供了三个方法block()、open()、close()。
    private ConditionVariable mVariable = new ConditionVariable();
    private int cx;
    private int cy;


    public PlayView(Context c) {
        super(c);

        context = c;
        initBmp();

        //设置背景资源
        //setBackgroundResource(R.drawable.ic_bg);

        getHolder().addCallback(this);

    }

    private void initBmp() {
        //这是那个操纵杆
        needleBmp = BitmapFactory.decodeResource(getResources(), R.drawable.icn_play_needle);
        //这个那个特别大的那个唱片
        discBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_play_disc);
        //这个那个小唱片的阴影效果
        discBgBmp = BitmapFactory.decodeResource(getResources(), R.drawable.play_disc_bg);

        //这个是可以设置背景文件
        //bgBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.background_blur);

        mLcBmp = BitmapFactory.decodeResource(getResources(),R.drawable.playing_cover);

        //设置唱片的阴影


    }

    //释放资源
    public void releaseBmp() {
        if (needleBmp != null) {
            needleBmp.recycle();
        }
        if (discBmp != null) {
            discBmp.recycle();
        }
        if (discBgBmp != null) {
            discBgBmp.recycle();
        }
        if (mLcBmp != null) {
            mLcBmp.recycle();
        }
    }

    //Canvas画布
    private void drawBmp(Canvas c, Bitmap b, int cx, int cy, Paint p) {
        c.drawBitmap(b, cx - b.getWidth() / 2, cy - b.getHeight() / 2, null);
    }

    private void doDraw(Canvas c) {

        // 去锯齿
        c.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));


        cx = mWidth / 2;
        cy = mHeight / 2;


        //经过测定 在红米2A上mWidth为720,mHeight为1230(因为底栏也占了一定的高度)
        cx = mWidth / 2;
        cy = mHeight / 2;

        Log.i("love",cx+"和" + cy+""+"大唱片的尺寸"+discBmp.getWidth()+"高"+discBmp.getHeight());


         //discBmp特别大的那个唱片,这个大唱片是从屏幕的左上角开始移动的,移动的距离必须减去半径!!!!!!!f我记得是保留小数的意思
        if (mDiscMatrix == null) {
            mDiscMatrix = new Matrix();
            mDiscMatrix.setTranslate(mWidth / 2 - discBmp.getWidth() / 2f,
                    mHeight / 2 - discBmp.getHeight() / 2f);
        }



        if (mLcMatrix == null) {
            mLcMatrix = new Matrix();
           /* mLcMatrix.setTranslate(mWidth / 2 - (discBmp.getWidth() - 60) / 2f,
                    mHeight / 2 - (discBmp.getHeight() - 60) / 2f);*/

            mLcMatrix.setTranslate(mWidth / 2 - mLcBmp.getWidth() / 2f,
                    mHeight / 2 - mLcBmp.getHeight() / 2f);

        }


        if (mIsPlay) {
            //如果累计转动大于360°则将角度置为0
            if (mRotates >= 360) {
                mRotates = 0;
                mDiscMatrix.reset();
                mLcMatrix.reset();

                mDiscMatrix.setTranslate(mWidth / 2 - discBmp.getWidth() / 2f,
                        mHeight / 2 - discBmp.getHeight() / 2f);


               /* mLcMatrix.setTranslate(mWidth / 2 - (discBmp.getWidth() - 60) / 2f,
                        mHeight / 2 - (discBmp.getHeight() - 60) / 2f);*/

                mLcMatrix.setTranslate(mWidth / 2 - mLcBmp.getWidth() / 2f,
                        mHeight / 2 - mLcBmp.getHeight() / 2f);

            }

            //设置围绕旋转的点
            //evertRotate每次转动的角度
            //这个是设置大唱片的参数
            mDiscMatrix.postRotate(everyRotate, cx, cy);

            //这个是设置那两条线以及小圆的转动参数
            mLcMatrix.postRotate(everyRotate, cx, cy);
            mRotates += everyRotate;
        }

        /*
        //中间那个转动的唱片
        if (mLcBmp == null) {
            int w = discBmp.getWidth() - 250;
            int h = discBmp.getHeight() - 250;

            mLcBmp = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_4444);

            //新建画布
            Canvas c2 = new Canvas(mLcBmp);
            //新建画笔
            Paint p = new Paint();

            c2.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            p.setColor(Color.LTGRAY);
            p.setStyle(Paint.Style.FILL);

            //画一个圆
            c2.drawCircle((discBmp.getWidth() - 60) / 2, (discBmp.getWidth() - 60) / 2, Math.min(w, h) / 2, p);

            p.setColor(Color.DKGRAY);
            p.setStrokeWidth(10f);

            //这个是那两条转动的线条(  起点:屏幕的宽度-小圆的半径-之前多偏移的距离=小圆的圆心-小圆的半径)
            c2.drawLine(  (discBmp.getWidth() - 60) / 2f - Math.min(w, h) / 2,
                    (discBmp.getHeight() - 60) / 2,
                    (discBmp.getWidth() - 60) / 2f + Math.min(w, h) / 2,
                    (discBmp.getHeight() - 60) / 2, p);

            c2.drawLine((discBmp.getWidth() - 60) / 2,
                    (discBmp.getWidth() - 60) / 2f - Math.min(w, h) / 2,
                    (discBmp.getWidth() - 60) / 2,
                    (discBmp.getWidth() - 60) / 2f + Math.min(w, h) / 2, p);

        }

*/
        //韩洋说:这个排序会影响叠放次序)_这是那个大唱片

        //背景图片
        //c.drawBitmap(bgBmp,0,0,null);

        //唱片
        c.drawBitmap(mLcBmp, mLcMatrix, null);


        //大唱片
        c.drawBitmap(discBmp, mDiscMatrix, null);


        //这是去画那个阴影~
        drawBmp(c, discBgBmp, cx, cy, null);

        //这是转动的小圆
        //c.drawBitmap(mLcBmp, mLcMatrix, null);


        //这个是操作那个杆~~~
        int left = mWidth / 3 - needleBmp.getWidth()-20;
        int top = 35;
        c.drawBitmap(needleBmp, left, top * 4, null);


    }

    public void play() {
        mIsPlay = true;
        mVariable.open();
    }

    public void pause() {
        mIsPlay = false;
    }

    public void stop() {
        if (mFrameThread != null) {
            mRun = false;
            mIsPlay = false;
            mFrameThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        mHeight = height;

        //bgBmp指的是那个背景图片
       /* if (bgBmp.getWidth() != mWidth || bgBmp.getHeight() != mHeight) {
            bgBmp = ThumbnailUtils.extractThumbnail(bgBmp, width, height);
        }*/


        if (mFrameThread == null) {
            mFrameThread = new Thread(this);
            mFrameThread.start();
        }
        mVariable.close();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stop();
    }

    @Override
    public void run() {
        Log.i("lovehanyang", "测试线程开启");
        mRun = true;
        Canvas c;
        while (mRun) {
            if (!mIsPlay) {
                pause();
            }
            c = getHolder().lockCanvas();
            if (c == null) {
                continue;
            }


            //这里是设置背景颜色
            c.drawColor(Color.rgb(215,216,213));

            //在这里去执行doDraw方法
            doDraw(c);
            getHolder().unlockCanvasAndPost(c);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}







