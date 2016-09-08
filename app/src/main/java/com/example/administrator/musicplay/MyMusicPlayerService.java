package com.example.administrator.musicplay;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

//后台播放的Service类
public class MyMusicPlayerService extends Service {
    CommandReceiver cr;//命令Intent接收者对象引用

    @Override
    public IBinder onBind(Intent intent) {//因为本例用不到Bind功能，因此直接返回null
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //创建命令Intent接收者对象
        cr = new CommandReceiver();
        //创建媒体播放器对象
        cr.mp= new MediaPlayer();
        //初始状态为停止状态
        cr.status = Constant.STATUS_STOP;

        //动态注册接收播放、暂停、停止命令Intent的CommandReceiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.MUSIC_CONTROL);
        this.registerReceiver(cr, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册接收播放、暂停、停止命令Intent的CommandReceiver
        this.unregisterReceiver(cr);
        //释放播放器对象
        cr.mp.release();
    }

    @Override
    public void onStart(Intent intent, int id) {
        //更新界面状态
        cr.updateUI(this.getApplicationContext());
    }
}
