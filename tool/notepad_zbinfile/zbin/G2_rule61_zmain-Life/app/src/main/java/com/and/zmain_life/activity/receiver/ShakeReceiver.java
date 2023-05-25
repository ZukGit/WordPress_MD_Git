package com.and.zmain_life.activity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShakeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("shake.detector")) {
              android.util.Log.i("zukgitXXX","摇了摇X1！！");
        }
        android.util.Log.i("zukgitXXX","摇了摇X2！！");

    }
}