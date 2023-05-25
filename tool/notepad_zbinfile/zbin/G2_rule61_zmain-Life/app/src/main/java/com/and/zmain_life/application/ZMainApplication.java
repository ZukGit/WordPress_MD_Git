package com.and.zmain_life.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.and.zmain_life.bean.APP_In_BackGround_Event;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.utils.RxBus;

public class ZMainApplication  extends Application {

    public final String TAG = "ZMainApplication";
    public static ZMainApplication mApplcation;


    public int mCount = 0;
    public static boolean isQiantai;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplcation = new ZMainApplication();
       DataHolder.System_SDK_Version =  android.os.Build.VERSION.SDK_INT;
       android.util.Log.i("系统_zukgit","当前版本的SDK为  DataHolder.System_SDK_Version = "+DataHolder.System_SDK_Version);
        regain();

    }

    public static ZMainApplication getInstance() {
        if (null == mApplcation)
            mApplcation = new ZMainApplication();
        return mApplcation;
    }



    public void regain() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity1) {
                mCount++;
                //如果mCount==1，说明是从后台到前台
                if (mCount == 1) {
                    //执行app跳转到前台的逻辑
                    isQiantai = true;
                    DataHolder.isAPPInBackground = false;
                    RxBus.getDefault().post(new APP_In_BackGround_Event(false));
                    Log.i("zukgit","后台--->前台  当前是【前台】 isQiantai="+isQiantai);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                //如果mCount==0，说明是前台到后台
                if (mCount == 0) {
                    //执行应用切换到后台的逻辑
                    isQiantai = false;
                    DataHolder.isAPPInBackground = true;
                    RxBus.getDefault().post(new APP_In_BackGround_Event(true));   //  被后台杀死了~~~
                    Log.i("xda_qianhou","前台-->后台   当前是【后台】  isQiantai="+isQiantai);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


}


