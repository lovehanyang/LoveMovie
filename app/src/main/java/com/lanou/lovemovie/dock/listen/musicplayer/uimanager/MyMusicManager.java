/**
 * Copyright (c) www.longdw.com
 */
package com.lanou.lovemovie.dock.listen.musicplayer.uimanager;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lanou.lovemovie.MusicInfo;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.application.AppApplication;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.IConstants;
import com.lanou.lovemovie.dock.listen.musicplayer.adapter.MyAdapter;
import com.lanou.lovemovie.dock.listen.musicplayer.model.AlbumInfo;
import com.lanou.lovemovie.dock.listen.musicplayer.model.ArtistInfo;
import com.lanou.lovemovie.dock.listen.musicplayer.model.FolderInfo;
import com.lanou.lovemovie.dock.listen.musicplayer.service.ServiceManager;
import com.lanou.lovemovie.dock.listen.musicplayer.storage.SPStorage;
import com.lanou.lovemovie.dock.listen.musicplayer.utils.MusicTimer;
import com.lanou.lovemovie.dock.listen.musicplayer.utils.MusicUtils;


/**
 * 我的音乐
 * @author longdw(longdawei1988@gmail.com)
 *
 */
public class MyMusicManager extends MainUIManager implements IConstants,
		OnTouchListener {

	private LayoutInflater mInflater;
	private Activity mActivity;

	private String TAG = MyMusicManager.class.getSimpleName();
	private MyAdapter mAdapter;
	private ListView mListView;
	private ServiceManager mServiceManager = null;
	private MyMusicUIManager mUIm;
	private MusicPlayBroadcast mPlayBroadcast;
	private MusicTimer mMusicTimer;

	private int mFrom;
	private Object mObj;

	private RelativeLayout mBottomLayout, mMainLayout;
	private Bitmap defaultArtwork;

	private UIManager mUIManager;

	public MyMusicManager(Activity activity, UIManager manager) {
		this.mActivity = activity;
		mInflater = LayoutInflater.from(activity);
		this.mUIManager = manager;
	}

	public View getView(int from) {
		return getView(from, null);
	}

	public View getView(int from, Object object) {
		View contentView = mInflater.inflate(R.layout.mymusic, null);
		mFrom = from;
		mObj = object;
		initBg(contentView);
		initView(contentView);

		return contentView;
	}

	private void initView(View view) {
		defaultArtwork = BitmapFactory.decodeResource(mActivity.getResources(),
				R.drawable.img_album_background);
		mServiceManager = AppApplication.mServiceManager;

		mBottomLayout = (RelativeLayout) view.findViewById(R.id.bottomLayout);

		mListView = (ListView) view.findViewById(R.id.music_listview);

		mActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		mPlayBroadcast = new MusicPlayBroadcast();
		IntentFilter filter = new IntentFilter(BROADCAST_NAME);

		filter.addAction(BROADCAST_NAME);
		filter.addAction(BROADCAST_QUERY_COMPLETE_NAME);
		mActivity.registerReceiver(mPlayBroadcast, filter);

		mUIm = new MyMusicUIManager(mActivity, mServiceManager, view,
				mUIManager);

		mMusicTimer = new MusicTimer(mUIm.mHandler);

		initListView();

		initListViewStatus();
	}

	//设置北京
	private void initBg(View view) {
		mMainLayout = (RelativeLayout) view
				.findViewById(R.id.main_mymusic_layout);
		mMainLayout.setOnTouchListener(this);
		SPStorage mSp = new SPStorage(mActivity);
		String mDefaultBgPath = mSp.getPath();
		Bitmap bitmap = mUIManager.getBitmapByPath(mDefaultBgPath);
		if (bitmap != null) {
			mMainLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
					.getResources(), bitmap));
		} else {
			//mMainLayout.setBackgroundResource(R.drawable.bg);
		}
	}

	private void initListViewStatus() {
		try {
			//mSdm.setListViewAdapter(mAdapter);
			int playState = mServiceManager.getPlayState();
			if (playState == MPS_NOFILE || playState == MPS_INVALID) {
				return;
			}
			if (playState == MPS_PLAYING) {
				mMusicTimer.startTimer();
			}
			List<MusicInfo> musicList = mAdapter.getData();
			int playingSongPosition = MusicUtils.seekPosInListById(musicList,
					mServiceManager.getCurMusicId());
			mAdapter.setPlayState(playState, playingSongPosition);
			MusicInfo music = mServiceManager.getCurMusic();

			mUIm.refreshUI(mServiceManager.position(), music.duration, music);
			mUIm.showPlay(false);

		} catch (Exception e) {
			Log.d(TAG, "", e);
		}
	}

	private void initListView() {
		mAdapter = new MyAdapter(mActivity, mServiceManager);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mAdapter.refreshPlayingList();
				mServiceManager.playById(mAdapter.getData().get(position).songId);

			}
		});



		StringBuffer select = new StringBuffer();

		switch (mFrom) {
		case START_FROM_ARTIST:
			ArtistInfo artistInfo = (ArtistInfo) mObj;
			mAdapter.setData(MusicUtils.queryMusic(mActivity,
					select.toString(), artistInfo.artist_name,
					START_FROM_ARTIST));
			break;
		case START_FROM_ALBUM:
			AlbumInfo albumInfo = (AlbumInfo) mObj;
			mAdapter.setData(MusicUtils.queryMusic(mActivity,
					select.toString(), albumInfo.album_id + "",
					START_FROM_ALBUM));
			break;
		case START_FROM_FOLDER:
			FolderInfo folderInfo = (FolderInfo) mObj;
			mAdapter.setData(MusicUtils.queryMusic(mActivity,
					select.toString(), folderInfo.folder_path,
					START_FROM_FOLDER));
			break;
		case START_FROM_FAVORITE:
			mAdapter.setData(MusicUtils.queryFavorite(mActivity),
					START_FROM_FAVORITE);
			break;
		default:
			mAdapter.setData(MusicUtils.queryMusic(mActivity, START_FROM_LOCAL));
			break;
		}
	}

	private class MusicPlayBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(BROADCAST_NAME)) {
				MusicInfo music = new MusicInfo();
				int playState = intent.getIntExtra(PLAY_STATE_NAME, MPS_NOFILE);
				int curPlayIndex = intent.getIntExtra(PLAY_MUSIC_INDEX, -1);
				Bundle bundle = intent.getBundleExtra(MusicInfo.KEY_MUSIC);
				if (bundle != null) {
					music = bundle.getParcelable(MusicInfo.KEY_MUSIC);
				}
				mAdapter.setPlayState(playState, curPlayIndex);
				switch (playState) {
				case MPS_INVALID:// 考虑后面加上如果文件不可播放直接跳到下一首

					mMusicTimer.stopTimer();

					mUIm.refreshUI(0, music.duration, music);
					mUIm.showPlay(true);
					mServiceManager.next();
					break;
				case MPS_PAUSE:

					mMusicTimer.stopTimer();
					mUIm.refreshUI(mServiceManager.position(), music.duration,
							music);
					mUIm.showPlay(true);

					mServiceManager.cancelNotification();
					break;
				case MPS_PLAYING:

					mMusicTimer.startTimer();
					mUIm.refreshUI(mServiceManager.position(), music.duration,
							music);
					mUIm.showPlay(false);

					Bitmap bitmap = MusicUtils.getCachedArtwork(mActivity,
							music.albumId, defaultArtwork);
					mServiceManager.updateNotification(bitmap, music.musicName,
							music.artist);

					break;
				case MPS_PREPARE:

					mMusicTimer.stopTimer();
					mUIm.refreshUI(0, music.duration, music);
					mUIm.showPlay(true);

					// 读取歌词文件
					//mSdm.loadLyric(music);
					break;
				}
			}
		}
	}

	int oldY = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int bottomTop = mBottomLayout.getTop();
		System.out.println(bottomTop);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			oldY = (int) event.getY();
			if (oldY > bottomTop) {
				//mSdm.open();
			}
		}
		return true;
	}

	@Override
	protected void setBgByPath(String path) {
		Bitmap bitmap = mUIManager.getBitmapByPath(path);
		if (bitmap != null) {
			mMainLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
					.getResources(), bitmap));
		}
	}

	@Override
	public View getView() {
		return null;
	}

}
