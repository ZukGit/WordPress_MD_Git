package com.and.zmain_life.wireless;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;


public abstract  class WirelessBaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        init();
    }

    protected abstract int setLayoutId();

    protected abstract void init();


    /**
     * 去除状态栏
     */
    protected void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 保持不息屏
     */
    protected void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Activity退出动画
     */
    protected void setExitAnimation(int animId) {
        overridePendingTransition(0, animId);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
