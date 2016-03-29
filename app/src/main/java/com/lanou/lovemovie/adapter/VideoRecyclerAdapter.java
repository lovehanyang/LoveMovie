package com.lanou.lovemovie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.bean.Video;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/9/11.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private List<Video.VideoListEntity> datas;
    private Context context;
    private DisplayImageOptions options = null;
    private ImageLoader imageLoader = null;

    public VideoRecyclerAdapter(Context c, List<Video.VideoListEntity> d) {
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

    public void setData(List<Video.VideoListEntity> data) {
        datas = data;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_videolist, viewGroup, false);

        view.setOnClickListener(this);


        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.tv_mv_time.setText(datas.get(position).getLength() + "秒");
        viewHolder.tv_mv_name.setText(datas.get(position).getTitle());
        imageLoader.displayImage(datas.get(position).getImage(), viewHolder.iv_mv_pic, options);

        /*Picasso
                .with(AppApplication.getContext())
                .load(datas.get(position).getImage())
                .into(viewHolder.iv_mv_pic)
                ;*/

        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Video.VideoListEntity) v.getTag());
        }
    }


    //创建一个viewholder类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_mv_pic;
        TextView tv_mv_name;
        TextView tv_mv_time;


        public ViewHolder(View itemView) {
            super(itemView);

            iv_mv_pic = (ImageView) itemView.findViewById(R.id.pictureIv);
            tv_mv_name = (TextView) itemView.findViewById(R.id.mv_name);
            tv_mv_time = (TextView) itemView.findViewById(R.id.mv_time);

        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    /**
     * 内部接口回调方法
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Video.VideoListEntity itemData);
    }

    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
