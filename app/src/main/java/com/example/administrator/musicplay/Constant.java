package com.example.administrator.musicplay;

//包装各种常量的常量类
public class Constant {
    //表示不同命令的常量
    public static final int COMMAND_PLAY=0;	//播放命令
    public static final int COMMAND_PAUSE=1; //暂停命令
    public static final int COMMAND_STOP=2;  //停止命令

    //表示不同状态的常量
    public static final int STATUS_PLAY=3;	//播放状态
    public static final int STATUS_PAUSE=4; //暂停状态
    public static final int STATUS_STOP=5;  //停止状态

    //表示更新进度状态的常量
    public static final int PROGRESS_GO=6;


    //表示不同功能Intent的字符串=========================================
    //表示控制命令的Action，如：播放、暂停、停止等
    public static final String MUSIC_CONTROL="TinyPlayer.ACTION_CONTROL";
    //表示更新界面的Action，如：播放、暂停、停止等
    public static final String UPDATE_STATUS="TinyPlayer.ACTION_UPDATE";
}
