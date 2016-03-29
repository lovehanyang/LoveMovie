package com.lanou.lovemovie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.bean.HotPlayMovies;
import com.lanou.lovemovie.dock.watch.WatchActivity.WatchMovie;
import com.lanou.lovemovie.view.BlurPic;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;
import utils.ScreenUtils;

/**
 * Created by dllo on 15/8/28.
 */
public class FancyCoverAdapter extends FancyCoverFlowAdapter {


    private Context context;
    private List<HotPlayMovies.MoviesEntity> datas;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions options = null;
    private DisplayImageOptions options2 = null;
    //储存已经虚化好的Bitmap图像
    //public Bitmap[] bitPics;
    public Drawable[] draPics;


    public FancyCoverAdapter(Context c, List<HotPlayMovies.MoviesEntity> datas) {
        context = c;
        this.datas = datas;

        //获取imageLoader的实例
        imageLoader = ImageLoader.getInstance();
        //初始化imageloader
        imageLoader.init(ImageLoaderConfiguration.createDefault(AppApplication.getContext()));


        //在这里可以设置缓存目录
        //File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");


        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.no_signal)
                .showImageOnFail(R.mipmap.ic_error)
                .bitmapConfig(Bitmap.Config.RGB_565)
                        //.displayer(new RoundedBitmapDisplayer(10))//设置圆角图片
                        //.cacheInMemory(false)

                        //.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置按比例缩放
                .cacheInMemory(false)
                .cacheOnDisc(true)
                        //.discCache(new UnlimitedDiscCache(cacheDir))
                .build();

        options2 = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.no_signal)
                .showImageOnFail(R.mipmap.ic_error)
                        //.displayer(new RoundedBitmapDisplayer(10))//设置圆角图片
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置按比例缩放
                        //.discCache(new UnlimitedDiscCache(cacheDir))
                .build();

        initBackground();


    }


    //进行背景虚化工作
    public void initBackground() {

        //bitPics = new Bitmap[datas.size()];
        draPics = new Drawable[datas.size()];

        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... voids) {

                for (int i = 0; i < datas.size(); i++) {

                    Bitmap moviePicBlur = imageLoader.loadImageSync(datas.get(i).getImg(), options2);
                    Bitmap newBackground = BlurPic.blurBitmap(moviePicBlur);

                    Drawable newBackground2 = new BitmapDrawable(newBackground);
                    draPics[i] = newBackground2;

                    //bitPics[i] = newBackground;
                    //newBackground.recycle();

                    moviePicBlur.recycle();

                }


                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                WatchMovie.setBackground(draPics[0]);
            }

        }.execute();

    }


    @Override
    public int getCount() {

        if (datas != null && datas.size() > 0) {
            return datas.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int i) {

        if (datas != null && datas.size() > 0) {
            return datas.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {
        CustomViewGroup customViewGroup = null;

        if (reusableView != null) {
            customViewGroup = (CustomViewGroup) reusableView;
        } else {
            customViewGroup = new CustomViewGroup(context);
            customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams((int) (ScreenUtils.getScreenWidth(context) / 1.3), ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        //customViewGroup.getImageView().setImageResource((Integer) this.getItem(position));

        ImageView imageView = customViewGroup.getImageView();
        imageLoader.displayImage(datas.get(position).getImg(), imageView, options);


        //设置背景
        if (draPics != null && draPics.length > 0) {
            WatchMovie.setBackground(draPics[position]);


        }

        // customViewGroup.getTextView().setText(String.format("Item %d", position));

        return customViewGroup;
    }


    private static class CustomViewGroup extends LinearLayout {

        private TextView textView;

        private ImageView imageView;

        private Button button;

        private CustomViewGroup(Context context) {
            super(context);

            this.setOrientation(VERTICAL);

            //this.textView = new TextView(context);
            this.imageView = new ImageView(context);
            //this.button = new Button(context);

            LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //this.textView.setLayoutParams(layoutParams);
            //this.imageView.setLayoutParams(layoutParams);
            this.imageView.setLayoutParams(new FancyCoverFlow.LayoutParams((int) (ScreenUtils.getScreenWidth(context) / 1.3), ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


            //this.button.setLayoutParams(layoutParams);


            //this.textView.setGravity(Gravity.CENTER);
            //保持宽高比
            this.imageView.setAdjustViewBounds(true);

            //this.button.setText("lovehanyang");
            /*this.button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://davidschreiber.github.com/FancyCoverFlow"));
                    view.getContext().startActivity(i);
                }
            });*/

            //this.addView(this.textView);
            this.addView(this.imageView);
            //this.addView(this.button);

        }

        private TextView getTextView() {
            return textView;
        }

        private ImageView getImageView() {
            return imageView;
        }

    }


}
