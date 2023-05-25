package com.and.zmain_life.activity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.and.zmain_life.bean.DataHolder;

import com.and.zmain_life.bean.ScreenOn_Off_Event;
import com.and.zmain_life.utils.RxBus;


public class ScreenStatusReceiver extends BroadcastReceiver {

    String SCREEN_ON = "android.intent.action.SCREEN_ON";
    String SCREEN_OFF = "android.intent.action.SCREEN_OFF";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (SCREEN_ON.equals(intent.getAction())) {


            DataHolder.isScreenOff = false;
            android.util.Log.i("zukgitXXX","亮屏了！！！  DataHolder.isScreenOff ="+DataHolder.isScreenOff);
            RxBus.getDefault().post(new ScreenOn_Off_Event(false));

        }  else if (SCREEN_OFF.equals(intent.getAction())) {


            DataHolder.isScreenOff = true;
            android.util.Log.i("zukgitXXX","灭屏了！！！  DataHolder.isScreenOff ="+DataHolder.isScreenOff);
            RxBus.getDefault().post(new ScreenOn_Off_Event(true));
        }
    }
}