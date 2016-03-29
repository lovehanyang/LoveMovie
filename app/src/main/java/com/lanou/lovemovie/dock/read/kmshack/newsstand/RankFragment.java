package com.lanou.lovemovie.dock.read.kmshack.newsstand;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.bean.MovieReview;
import com.lanou.lovemovie.dock.read.MovieDetails;
import com.lanou.lovemovie.http.GsonRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by dllo on 15/9/1.
 */
public class RankFragment extends ScrollTabHolderFragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private static final String ARG_POSITION = "position";
    private int mPosition;
    private ListView mListView;
    private MyAdapter myAdapter;
    private Handler handler;// 定义一个handler对象
    ArrayList<MovieReview> datas;

    public static Fragment newInstance(int position) {
        RankFragment f = new RankFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPosition = getArguments().getInt(ARG_POSITION);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        mListView = (ListView) v.findViewById(R.id.listView);

        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);

        initNetData();


    }


    private void initNetData() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 300) {
                    datas = (ArrayList<MovieReview>) message.obj;


                    myAdapter = new MyAdapter();
                    mListView.setAdapter(myAdapter);

                }
                return false;
            }
        });


        GsonRequest.MovieReview("http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false", handler);


    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null)
            mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, mPosition);
    }


    @Override
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
            return;
        }

        mListView.setSelectionFromTop(1, scrollHeight);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent = new Intent(getActivity(), MovieDetails.class);



        MovieReview movieReview = (MovieReview) adapterView.getItemAtPosition(position);

        intent.putExtra("detailTitle",movieReview.getTitle());
        intent.putExtra("id",movieReview.getId());
        intent.putExtra("movieScore",movieReview.getRelatedObj().getRating());
        intent.putExtra("movieName",movieReview.getRelatedObj().getTitle());
        intent.putExtra("Author",movieReview.getNickname());

        startActivity(intent);

    }


    public class MyAdapter extends BaseAdapter {

        private DisplayImageOptions options = null;
        private ImageLoader imageLoader = null;

        public MyAdapter() {

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
        public int getCount() {

            if (datas != null && datas.size() > 0)
                return 10;
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
        public View getView(int i, View view, ViewGroup viewGroup) {


            ViewHolder viewHolder = null;
            if (view == null) {
                // 加载行布局文件
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                view = inflater.inflate(R.layout.item_film_list, null);


                viewHolder = new ViewHolder();
                viewHolder.Title = (TextView) view.findViewById(R.id.tv_comment_title);
                viewHolder.Description = (TextView) view.findViewById(R.id.tv_sub_title);
                viewHolder.People = (TextView) view.findViewById(R.id.tv_actor_name);
                viewHolder.movieName = (TextView) view.findViewById(R.id.tv_date);
                viewHolder.moviePic = (ImageView) view.findViewById(R.id.iv_film_preview);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            MovieReview item = (MovieReview) getItem(i);

            if (datas != null) {
                viewHolder.Title.setText(item.getTitle() + "");
                viewHolder.movieName.setText("评论影片:" + item.getRelatedObj().getTitle() + "");
                viewHolder.Description.setText(item.getSummary() + "");
                viewHolder.People.setText("评论员:" + item.getNickname() + "");

                imageLoader.displayImage(item.getRelatedObj().getImage(), viewHolder.moviePic, options);

                //Picasso.with(AppApplication.getContext()).load(item.getRelatedObj().getImage()).into(viewHolder.moviePic);
                Log.i("lovehanyang", "图片" + item.getRelatedObj().getImage());
            }

            return view;
        }

        public class ViewHolder {
            TextView Title;
            TextView Description;
            TextView People;
            TextView movieName;
            ImageView moviePic;


        }


    }


}
