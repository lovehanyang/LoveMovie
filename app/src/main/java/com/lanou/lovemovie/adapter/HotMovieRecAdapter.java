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
import com.lanou.lovemovie.bean.HotPlayMovies;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * Created by dllo on 15/9/14.
 */
public class HotMovieRecAdapter extends RecyclerView.Adapter<HotMovieRecAdapter.ViewHolder> {

    private List<HotPlayMovies.MoviesEntity> datas;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions options = null;
    private Context context;


    public HotMovieRecAdapter(Context c, List<HotPlayMovies.MoviesEntity> d) {

        context = c;
        datas = d;
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

        View view = inflater.inflate(R.layout.hot_movie_list, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.hotName.setText(datas.get(position).getTitleCn() + "");
        holder.hotData.setText(datas.get(position).getRMonth() + "月" + datas.get(position).getRDay() + "日");

        imageLoader.displayImage(datas.get(position).getImg(), holder.hotPic, options);
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hotPic;
        TextView hotName;
        TextView hotData;

        public ViewHolder(View itemView) {
            super(itemView);

            hotPic = (ImageView) itemView.findViewById(R.id.hot_movie_pic);
            hotName = (TextView) itemView.findViewById(R.id.hot_movie_name);
            hotData = (TextView) itemView.findViewById(R.id.hot_data);

        }


    }
}
