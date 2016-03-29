package com.lanou.lovemovie.dock.listen.musicplayer.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import com.lanou.lovemovie.IMediaService;
import com.lanou.lovemovie.MusicInfo;
import com.lanou.lovemovie.dock.listen.musicplayer.IOnServiceConnectComplete;
import com.lanou.lovemovie.dock.listen.musicplayer.activity.IConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/9/1.
 */
public class ServiceManager implements IConstants {
    private Context mContext;
    private ServiceConnection mConn;
    public IMediaService mService;
    private IOnServiceConnectComplete mIOnServiceConnectComplete;


    public ServiceManager(Context context) {

        this.mContext = context;
        initConn();
    }

    private void initConn() {
        mConn = new ServiceConnection() {

            // 连接成功时的回调
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                Log.i("lovehanyang","-----------绑定成功");
                //获得一个接口
                mService = IMediaService.Stub.asInterface(service);

                Log.i("lovehanyang","mService的状态是"+mService);

                if (mService != null) {
                    mIOnServiceConnectComplete.onServiceConnectComplete(mService);
                }

            }

            // 断开连接时的回调
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i("lovehanyang","-------------绑定没有成功");

            }
        };
    }


    public void connectService() {
        Intent intent = new Intent("com.lanou.lovemovie.dock.listen.musicplayer.service");

        intent.setPackage("com.lanou.lovemovie");

        ComponentName a = mContext.startService(intent);

        Boolean b =  mContext.bindService(intent, mConn, Context.BIND_AUTO_CREATE);

        Log.i("lovehanyang", "-------------测试连接是否成功" + b);

    }

    public void disConnectService() {
        mContext.unbindService(mConn);
        mContext.stopService(new Intent("com.lanou.lovemovie.dock.listen.musicplayer.service"));
    }

    public void refreshMusicList(List<MusicInfo> musicList) {

        Log.i("lovehanyang","测试进入2"+musicList.toString()+"测试mService"+mService);

        if(musicList != null && mService != null) {
            try {
                mService.refreshMusicList(musicList);

                Log.i("lovehanyang", "ServiceManager"+musicList.toString());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }



    public List<MusicInfo> getMusicList() {
        List<MusicInfo> musicList = new ArrayList<MusicInfo>();
        try {
            if(mService != null) {
                mService.getMusicList(musicList);



            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return musicList;
    }

    public boolean play(int pos) {
        if(mService != null) {
            try {
                return mService.play(pos);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean playById(int id) {
        if(mService != null) {
            try {
                return mService.playById(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean rePlay() {
        if(mService != null) {
            try {
                return mService.rePlay();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean pause() {
        if(mService != null) {
            try {
                return mService.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean prev() {
        if(mService != null) {
            try {
                return mService.prev();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean next() {
        if(mService != null) {
            try {
                return mService.next();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean seekTo(int progress) {
        if(mService != null) {
            try {
                return mService.seekTo(progress);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int position() {
        if(mService != null) {
            try {
                return mService.position();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int duration() {
        if(mService != null) {
            try {
                return mService.duration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getPlayState() {
        if(mService != null) {
            try {
                int mode = mService.getPlayState();
                return mService.getPlayState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setPlayMode(int mode) {
        if(mService != null) {
            try {
                mService.setPlayMode(mode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPlayMode() {
        if(mService != null) {
            try {
                return mService.getPlayMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getCurMusicId() {
        if(mService != null) {
            try {
                return mService.getCurMusicId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public MusicInfo getCurMusic() {
        if(mService != null) {
            try {
                return mService.getCurMusic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void sendBroadcast() {
        if(mService != null) {
            try {
                mService.sendPlayStateBrocast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        if(mService != null) {
            try {
                mService.exit();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mContext.unbindService(mConn);
        mContext.stopService(new Intent(SERVICE_NAME));
    }

    public void updateNotification(Bitmap bitmap, String title, String name) {
        try {
            mService.updateNotification(bitmap, title, name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancelNotification() {
        try {
            mService.cancelNotification();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setOnServiceConnectComplete(
            IOnServiceConnectComplete IServiceConnect) {
        mIOnServiceConnectComplete = IServiceConnect;
    }



}
