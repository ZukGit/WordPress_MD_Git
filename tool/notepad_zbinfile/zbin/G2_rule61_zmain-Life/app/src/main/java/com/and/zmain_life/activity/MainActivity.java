package com.and.zmain_life.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;



import java.util.ArrayList;

import butterknife.BindView;
import rx.functions.Action1;
import utils.ZUtil;


import com.and.zmain_life.activity.receiver.ScreenStatusReceiver;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.fragment.MainFragment;
import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseActivity;
import com.and.zmain_life.base.CommPagerAdapter;
import com.and.zmain_life.bean.MainPageChangeEvent;
import com.and.zmain_life.utils.RxBus;


public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static int curMainPage;
    private MainFragment mainFragment = new MainFragment();
    /** 上次点击返回键时间 */
    private long lastTime;
    /** 连续按返回键退出时间 */
    private final int EXIT_TIME = 2000;

    public ScreenStatusReceiver mScreenStatusReceiver;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }


    private void registSreenStatusReceiver() {
        mScreenStatusReceiver = new ScreenStatusReceiver();
        IntentFilter screenStatusIF = new IntentFilter();
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON);
        screenStatusIF.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStatusReceiver, screenStatusIF);
    }

    public boolean isScreenOriatationPortrait() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }



    @Override
    protected void init() {
        DataHolder.System_IS_LAND = !isScreenOriatationPortrait();
        DataHolder.System_IS_PAD = DataHolder.isPad(getApplicationContext());
        android.util.Log.e("zukgit_pad"," DataHolder.System_IS_LAND = "+ DataHolder.System_IS_LAND);
        android.util.Log.e("zukgit_pad"," DataHolder.System_IS_PAD = "+ DataHolder.System_IS_PAD);
        setFullScreen();


        fragments.add(mainFragment);
//        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"",""});
        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{""});
        viewPager.setAdapter(pagerAdapter);
        ZUtil.initContext(this);

        registSreenStatusReceiver();
        //点击头像切换页面
        RxBus.getDefault().toObservable(MainPageChangeEvent.class)
                .subscribe((Action1<MainPageChangeEvent>) event -> {
                    if (viewPager != null) {
                        viewPager.setCurrentItem(event.getPage());
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curMainPage = position;
                android.util.Log.i("zukgit", " MainActivity  onPageSelected  position = " + position);
/*                if (position == 0) {
                    android.util.Log.i("zukgit", "A  MainActivity  onPageSelected  position = " + position);
                    RxBus.getDefault().post(new Pause_MP4_Home_VideoEvent(true));
                } else if (position == 1){
                    android.util.Log.i("zukgit", "B  MainActivity  onPageSelected  position = " + position);

                    RxBus.getDefault().post(new Pause_MP4_Home_VideoEvent(false));
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScreenStatusReceiver);
    }

    @Override
    public void onBackPressed() {
        //双击返回退出App
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            if (viewPager.getCurrentItem() == 1) {
                viewPager.setCurrentItem(0);
            } else{
                Toast.makeText(getApplicationContext(), " 再按一次退出", Toast.LENGTH_SHORT).show();
                if(mainFragment != null){
                    mainFragment.dissmiss();
                }
                lastTime = System.currentTimeMillis();
            }
        } else {
            super.onBackPressed();
        }
    }
}
