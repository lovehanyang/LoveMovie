package com.lanou.lovemovie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.bean.CommingMovie;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * Created by dllo on 15/9/12.
 */
public class CommingSoonAdapter extends RecyclerView.Adapter<CommingSoonAdapter.ViewHolder> {

    List<CommingMovie.MoviecomingsEntity> moviecomings;
    private Context context;
    private DisplayImageOptions options = null;
    private ImageLoader imageLoader = null;

    public CommingSoonAdapter(Context c, List<CommingMovie.MoviecomingsEntity> d) {
        context = c;
        moviecomings = d;
        //获取imageLoader的实例
        imageLoader = ImageLoader.getInstance();
        //初始化imageloader
        imageLoader.init(ImageLoaderConfiguration.createDefault(AppApplication.getContext()));

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.no_signal)
                .showImageOnFail(R.mipmap.ic_error)
                .bitmapConfig(Bitmap.Config.RGB_565)
                        //.displayer(new RoundedBitmapDisplayer(10))//设置圆角图片
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheInMemory(false)
                .cacheOnDisc(true)
                .build();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.comming_soon_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.commingData.setText(moviecomings.get(position).getReleaseDate() + "");
        viewHolder.commingName.setText(moviecomings.get(position).getTitle() + "");

        imageLoader.displayImage(moviecomings.get(position).getImage(), viewHolder.commingPic, options);


    }


    @Override
    public int getItemCount() {
        return null == moviecomings ? 0 : 18;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView commingPic;
        TextView commingName;
        TextView commingData;

        public ViewHolder(View itemView) {
            super(itemView);


            commingData = (TextView) itemView.findViewById(R.id.comming_data);
            commingName = (TextView) itemView.findViewById(R.id.comming_movie_name);
            commingPic = (ImageView) itemView.findViewById(R.id.comming_movie_pic);
        }
    }


}
