package com.example.administrator.musicplay;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.app.Activity;

public class MainActivity extends Activity {
    String play = "/sdcard/Adele - Rolling in the Deep.mp3";
    UIUpdateReceiver uiur;
    private ImageButton play_pause;
    private ImageButton stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_pause=(ImageButton)findViewById(R.id.pause);
        stop=(ImageButton)findViewById(R.id.stop);
        uiur=new UIUpdateReceiver(this);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Constant.MUSIC_CONTROL);
                intent.putExtra("cmd",Constant.COMMAND_STOP);
                MainActivity.this.sendBroadcast(intent);

            }
        });
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uiur.status==Constant.STATUS_PLAY){
                    Intent intent=new Intent(Constant.MUSIC_CONTROL);
                    intent.putExtra("cmd",Constant.COMMAND_PAUSE);
                    MainActivity.this.sendBroadcast(intent);
                }
                else if(uiur.status==Constant.STATUS_STOP){
                    Intent intent=new Intent(Constant.MUSIC_CONTROL);
                    intent.putExtra("cmd",Constant.COMMAND_PLAY);
                    intent.putExtra("path",play);
                    MainActivity.this.sendBroadcast(intent);
                }else{
                    Intent intent=new Intent(Constant.MUSIC_CONTROL);
                    intent.putExtra("cmd",Constant.COMMAND_PLAY);
                    MainActivity.this.sendBroadcast(intent);
                }
            }
        });
        IntentFilter filter=new IntentFilter(); //动态接收状态更新intent的UIUpdateReceiver
        filter.addAction(Constant.UPDATE_STATUS);
        this.registerReceiver(uiur,filter);
        this.startService(new Intent(this,MyMusicPlayerService.class));
    }
    public void  onStart(){
        super.onStart();
        try{
            NotificationManager nm=(NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            Intent intent=new Intent(this,MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);//封装
            Notification nt=new Notification();
            nt.icon=R.drawable.notilogo;
            nt.vibrate=new long[]{200,300};
            nt.notify();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(uiur);

    }
}
