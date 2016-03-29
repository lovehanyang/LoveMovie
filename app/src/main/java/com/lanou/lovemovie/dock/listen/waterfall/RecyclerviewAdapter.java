package com.lanou.lovemovie.dock.listen.waterfall;

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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by dllo on 15/9/1.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> implements View.OnClickListener {

    private List<HotPlayMovies.MoviesEntity> data;//填充数据的集合
    private Context context;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions options = null;


    public RecyclerviewAdapter(Context c, List<HotPlayMovies.MoviesEntity> d) {
        context = c;
        data = d;

        //获取imageLoader的实例
        imageLoader = ImageLoader.getInstance();
        //初始化imageloader
        imageLoader.init(ImageLoaderConfiguration.createDefault(AppApplication.getContext()));

        options = new DisplayImageOptions.Builder()
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
    }

    public void setData(List<HotPlayMovies.MoviesEntity> data) {
        this.data = data;
    }


    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_view, viewGroup, false);

        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    //设置数据
    @Override
    public void onBindViewHolder(RecyclerviewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_recycler.setText(data.get(i).getTitleCn()+"原声带");
        imageLoader.displayImage(data.get(i).getImg(),viewHolder.iv_recycler,options);
    }

    @Override
    public int getItemCount() {

        return null == data ? 0 : data.size();
    }

    @Override
    public void onClick(View view) {


    }


    //创建一个viewholder类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_recycler;
        TextView tv_recycler;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_recycler = (CircleImageView) itemView.findViewById(R.id.recycler_iv);
            tv_recycler = (TextView) itemView.findViewById(R.id.recycler_tv);

        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;




    /**
     * 内部接口回调方法
     */
    public  interface OnRecyclerViewItemClickListener {
        void onClick(View view, StoreContent itemData);
    }

    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }




    //listview中更新数据，通过的方法是notifyDataSetChanged()
    //RecycleView中，可以只更新一条数据
    //在adapter类中，定义如下方法；可以在其他地方调用

    public void changeData(int position) {
        notifyItemChanged(position);
    }

    public void addData(int position,HotPlayMovies.MoviesEntity item) {
        data.add(position,item);
        notifyItemInserted(position);
    }

    public void removeData(int position,HotPlayMovies.MoviesEntity item) {
        data.remove(position);
        notifyItemRemoved(position);
    }


}
