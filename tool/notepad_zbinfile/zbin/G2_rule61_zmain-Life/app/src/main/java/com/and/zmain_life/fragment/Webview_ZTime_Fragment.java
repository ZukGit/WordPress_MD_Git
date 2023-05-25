package com.and.zmain_life.fragment;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.DataHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/*import com.and.zvideo_and_dy.view.CommentDialog;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDialog;*/


/*1.显示 github.zukgit.io 的 webview */


public class Webview_ZTime_Fragment extends BaseFragment {


    @BindView(R.id.webview_item)
    WebView webView;

    /**
     * 当前播放视频位置
     */
    private int curPlayPos = 0;


//    private FullScreenVideoView videoView;

/*    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;*/



    @Override
    protected int setLayoutId() {
        return R.layout.fragment_webview;
    }



    @Override
    protected void init() {

        android.util.Log.i("zukgit", " webview_init() curPlayPos = " + curPlayPos + " WebZtimeFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);

if(!DataHolder.isZTimePage_Already_Loaded){

    webView.loadUrl("https://zukgit.github.io/ZHtml/");
//把打开的网页展示到 当前app中 而不是使用手机自带的浏览器打开
    webView.setWebViewClient(new WebViewClient());

//获取设置的对象
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);//设置支持javaScript
    settings.setJavaScriptCanOpenWindowsAutomatically(true);
    DataHolder.isZTimePage_Already_Loaded = true;
}








//        setViewPagerLayoutManager();

//        setRefreshEvent();

/* 诵诗所

        RxBus.getDefault().post(new Pause_Home_Port_ImgEvent(false));
        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_Home_Port_ImgEvent.class)
                .subscribe((Action1<Pause_Home_Port_ImgEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgFragment.init B   ");
                        mDoubleTap_ScaleNum_Index = 0;
                        android.util.Log.i("zukgit", "AA toObservable  DataHolder.Home_Land_Img_initPos = " + DataHolder.Home_Port_Img_initPos + " pause_Img(true)   ");
                        if(recyclerView != null){
                            recyclerView.scrollToPosition(DataHolder.Home_Port_Img_initPos);
                            android.util.Log.i("zukgit", "AA toObservable  DataHolder.Home_Land_Img_initPos = " + DataHolder.Home_Port_Img_initPos + " pause_Img(true)   ");
                        }
                        pause_Img(false);
                        gotoNextPage_loop(false);


                    } else {
                        android.util.Log.i("zukgit", "BB toObservable  DataHolder.Home_Land_Img_initPos = " + DataHolder.Home_Port_Img_initPos + " pause_Img(true)   ");
                        pause_Img(true);
                    }
                });

*/



    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        DataHolder.isZTimePage_Already_Loaded = false;
        android.util.Log.i("zukgit", "webview  onDestroy() DataHolder.isZTimePage_Already_Loaded = false");

    }


    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "webView.reload() curPlayPos = " + curPlayPos + " WebZtimeFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
//        pause_Img(false);    // 进来就调用了
//         gotoNextPage_loop();



/*        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  CA ");
            videoView.start();
        }*/
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgFragment.WebZtimeFragment  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgFragment.WebZtimeFragment  D ");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        DataHolder.ztimeBundle = outState;
        webView.saveState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();

//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    static int user_backup_num = 0;



    private void setViewPagerLayoutManager() {

    }

/*
    private void setRefreshEvent() {
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }
*/




    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }





}
