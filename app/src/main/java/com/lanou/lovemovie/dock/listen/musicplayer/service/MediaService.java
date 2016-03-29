package com.lanou.lovemovie.dock.listen.musicplayer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.lanou.lovemovie.IMediaService;
import com.lanou.lovemovie.MusicInfo;
import com.lanou.lovemovie.R;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.IConstants;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.MainContentActivity;
import com.lanou.lovemovie.dock.listen.musicplayer.storage.SPStorage;

import java.util.List;

/**
 * Created by dllo on 15/9/1.
 */
public class MediaService extends Service implements IConstants {

    private static final int PAUSE_FLAG = 0x1;
    private static final int NEXT_FLAG = 0x2;
    private static final int PRE_FLAG = 0x3;

    private static final String PAUSE_BROADCAST_NAME = "com.ldw.music.pause.broadcast";
    private static final String NEXT_BROADCAST_NAME = "com.ldw.music.next.broadcast";
    private static final String PRE_BROADCAST_NAME = "com.ldw.music.pre.broadcast";

    private MusicControl mMc;
    private NotificationManager mNotificationManager;
    private SPStorage mSp;

    private ControlBroadcast mConrolBroadcast;
    private MusicPlayBroadcast mPlayBroadcast;
    private int NOTIFICATION_ID = 0x1;
    private RemoteViews rv;

    private final IBinder mBinder = new ServerStub();
    /**
     * 当前是否正在播放
     */
    private boolean mIsPlaying;

    @Override
    public void onCreate() {
        super.onCreate();

        mMc = new MusicControl(this);
        mSp = new SPStorage(this);

        //韩洋说:这个是负责在通知栏控制音乐
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //动态注册广播接收器
        mConrolBroadcast = new ControlBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PAUSE_BROADCAST_NAME);
        filter.addAction(NEXT_BROADCAST_NAME);
        filter.addAction(PRE_BROADCAST_NAME);
        registerReceiver(mConrolBroadcast, filter);

        mPlayBroadcast = new MusicPlayBroadcast();

        mPlayBroadcast = new MusicPlayBroadcast();
        IntentFilter filter1 = new IntentFilter(BROADCAST_NAME);
        registerReceiver(mPlayBroadcast, filter1);


    }

    /**
     * 更新notification
     *
     * @param bitmap
     * @param title
     * @param name
     */
    private void updateNotification(Bitmap bitmap, String title, String name) {
        Intent intent = new Intent(getApplicationContext(), MainContentActivity.class);

        //跳转到的activity若已在栈中存在，则将其上的activity都销掉。
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //这个是通知栏播放视图
        rv = new RemoteViews(this.getPackageName(), R.layout.notification);
        Notification notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.tickerText = title;
        notification.contentIntent = pi;
        notification.contentView = rv;

        //FLAG_ONGOING_EVENT 通知放置在正在运行(a|=b的意思就是把a和b按位或然后赋值给a
        //   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b)
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        if (bitmap != null) {
            rv.setImageViewBitmap(R.id.image, bitmap);
        } else {
            rv.setImageViewResource(R.id.image, R.drawable.img_album_background);
        }
        rv.setTextViewText(R.id.title, title);
        rv.setTextViewText(R.id.text, name);
//		mNotificationManager.notify(NOTIFICATION_ID, mNotification);

        //此处action不能是一样的 如果一样的 接受的flag参数只是第一个设置的值
        Intent pauseIntent = new Intent(PAUSE_BROADCAST_NAME);
        pauseIntent.putExtra("FLAG", PAUSE_FLAG);
        PendingIntent pausePIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_pause, pausePIntent);


        Intent nextIntent = new Intent(NEXT_BROADCAST_NAME);
        nextIntent.putExtra("FLAG", NEXT_FLAG);
        PendingIntent nextPIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_next, nextPIntent);


        Intent preIntent = new Intent(PRE_BROADCAST_NAME);
        preIntent.putExtra("FLAG", PRE_FLAG);
        PendingIntent prePIntent = PendingIntent.getBroadcast(this, 0, preIntent, 0);
        rv.setOnClickPendingIntent(R.id.iv_previous, prePIntent);


        //通过startForeground让服务前台运行，
        //当stopservice的时候通过stopForeground去掉。
        //防止服务被杀掉
        startForeground(NOTIFICATION_ID, notification);
    }

    private void cancelNotification() {
        stopForeground(true);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private class ServerStub extends IMediaService.Stub {

        @Override
        public boolean play(int pos) throws RemoteException {
            return mMc.play(pos);
        }

        @Override
        public boolean playById(int id) throws RemoteException {
            return mMc.playById(id);
        }

        @Override
        public boolean rePlay() throws RemoteException {
            return mMc.replay();
        }

        @Override
        public boolean pause() throws RemoteException {
            return mMc.pause();
        }


        //上一曲
        @Override
        public boolean prev() throws RemoteException {
            return mMc.prev();
        }

        @Override
        public boolean next() throws RemoteException {
            return mMc.next();
        }

        @Override
        public int duration() throws RemoteException {
            return mMc.duration();
        }

        @Override
        public int position() throws RemoteException {
            return mMc.position();
        }

        @Override
        public boolean seekTo(int progress) throws RemoteException {
            return mMc.seekTo(progress);
        }

        @Override
        public void refreshMusicList(List<MusicInfo> musicList) throws RemoteException {


            mMc.refreshMusicList(musicList);
            Log.i("lovehanyang", "测试进入musicService" + musicList.toString());

        }

        @Override
        public void getMusicList(List<MusicInfo> musicList) throws RemoteException {
            List<MusicInfo> music = mMc.getMusicList();
            for (MusicInfo m : music) {
                musicList.add(m);
            }
        }

        @Override
        public int getPlayState() throws RemoteException {
            return mMc.getPlayState();
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return mMc.getPlayMode();
        }

        @Override
        public void setPlayMode(int mode) throws RemoteException {
            mMc.setPlayMode(mode);
        }

        @Override
        public void sendPlayStateBrocast() throws RemoteException {
            mMc.sendBroadCast();
        }

        @Override
        public void exit() throws RemoteException {
            cancelNotification();
            stopSelf();
            mMc.exit();
        }

        @Override
        public int getCurMusicId() throws RemoteException {
            return mMc.getCurMusicId();
        }

        @Override
        public void updateNotification(Bitmap bitmap, String title, String name) throws RemoteException {
            MediaService.this.updateNotification(bitmap, title, name);

        }

        @Override
        public void cancelNotification() throws RemoteException {
            MediaService.this.cancelNotification();

        }

        @Override
        public MusicInfo getCurMusic() throws RemoteException {
            return mMc.getCurMusic();
        }


    }

    private class ControlBroadcast extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            int flag = intent.getIntExtra("FLAG", -1);
            switch (flag) {
                case PAUSE_FLAG:
//				MediaService.this.stopForeground(true);
                    mMc.pause();
                    break;
                case NEXT_FLAG:
                    mMc.next();
                    break;
                case PRE_FLAG:
                    mMc.prev();
                    break;
            }
        }
    }

    private class MusicPlayBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(BROADCAST_NAME)) {

                int playState = intent.getIntExtra(PLAY_STATE_NAME, MPS_NOFILE);
                switch (playState) {
                    case MPS_PLAYING:
                        mIsPlaying = true;
                        break;
                    default:
                        mIsPlaying = false;
                }
            }


        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mConrolBroadcast!=null){
            unregisterReceiver(mConrolBroadcast);
        }
        if (mPlayBroadcast!=null){
            unregisterReceiver(mPlayBroadcast);
        }

    }
}
