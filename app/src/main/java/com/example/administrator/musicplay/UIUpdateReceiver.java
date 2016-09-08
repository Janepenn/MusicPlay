package com.example.administrator.musicplay;

import android.content.BroadcastReceiver;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UIUpdateReceiver extends BroadcastReceiver{
    int status=Constant.STATUS_STOP;//初始状态为停止状态
    Activity ac;//此Receiver对应的Activity
    public UIUpdateReceiver(Activity ac)
    {
        this.ac=ac;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //从接收的Intent中获取状态值
        int tempStatus=intent.getIntExtra("status", -1);
        //获取播放/暂停按钮的引用
        ImageButton ib=(ImageButton)ac.findViewById(R.id.pause);

        switch(tempStatus)
        {
            case Constant.STATUS_PLAY://若更新到播放状态则将图片换为按下后暂停的提示图片
                ib.setImageResource(R.drawable.pause);
                status=tempStatus;
                break;
            case Constant.STATUS_STOP:  //若更新到停止、暂停状态则将图片换为按下后播放的提示图片
            case Constant.STATUS_PAUSE:
                ib.setImageResource(R.drawable.play);
                status=tempStatus;
                break;
            case Constant.PROGRESS_GO://更新进度条及已播放时间与总时间
                //更新进度条
                ProgressBar pb=(ProgressBar)ac.findViewById(R.id.pb01);
                int duration=intent.getIntExtra("duration", 0);
                int current=intent.getIntExtra("current", 0);
                pb.setMax(duration);
                pb.setProgress(current);
                //更新已播放时间与总时间
                TextView tv=(TextView)ac.findViewById(R.id.tv01);
                tv.setText(fromMsToMinuteStr(current)+"/"+fromMsToMinuteStr(duration));
                break;
        }
    }

    //将毫秒转换成  mi:ss格式的时间字符串
    public String fromMsToMinuteStr(int ms)
    {
        ms=ms/1000;
        int minute=ms/60;
        int second=ms%60;
        return minute+":"+((second>9)?second:"0"+second);
    }

}
