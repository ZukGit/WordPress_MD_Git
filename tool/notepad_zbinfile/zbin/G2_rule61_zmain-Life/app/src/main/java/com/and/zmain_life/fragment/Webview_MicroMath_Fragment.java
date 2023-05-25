package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.DataHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;




/*1.显示 github.zukgit.io 的 webview */


public class Webview_MicroMath_Fragment extends BaseFragment {


    @BindView(R.id.webview_item)
    WebView webView;

    /**
     * 当前url 的 位置
     */
    public static int curUrlPos = 0;


    static ArrayList<String> mDescList ;
    static   HashMap<String,String> mDesc_Url_HashMap ;
    Dialog curDialog ;

//    private FullScreenVideoView videoView;

/*    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;*/



    @Override
    protected int setLayoutId() {
        return R.layout.fragment_webview;
    }

   void  init_url_map(){

        mDescList = new ArrayList<String>();
       mDesc_Url_HashMap  = new HashMap<String,String>();

       initMapItem("Rust圣经","https://course.rs/about-book.html");
       initMapItem("微积分计算器","https://mathsolver.microsoft.com/zh/calculus-calculator");
       initMapItem("万年历","https://wannianrili.bmcx.com/");
       initMapItem("上海天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E4%B8%8A%E6%B5%B7%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("浦东天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E6%B5%A6%E4%B8%9C%E5%A4%A9%E6%B0%94&srcid=4982");

       initMapItem("大理天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E5%A4%A7%E7%90%86%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("漾濞天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E6%BC%BE%E6%BF%9E%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("杭州天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E6%9D%AD%E5%B7%9E%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("拱墅天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E6%8B%B1%E5%A2%85%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("玉山天气查询","https://weathernew.pae.baidu.com/weathernew/pc?query=%E7%8E%89%E5%B1%B1%E5%A4%A9%E6%B0%94&srcid=4982");
       initMapItem("Zukgit_Git","https://github.com/ZukGit/WordPress_MD_Git");
       initMapItem("百度脑图","https://naotu.baidu.com/home");

       initMapItem("百度翻译","https://fanyi.baidu.com/?aldtype=16047#en/zh");
       initMapItem("脑洞特效","https://guciek.github.io/");
       initMapItem("菜鸟C语言教程","https://www.runoob.com/cprogramming/c-tutorial.html");
       initMapItem("二维码实时生成","http://codeid.xmesm.cn/");
       initMapItem("日期计算器","http://bjtime.cn/riqi/");
       initMapItem("阿里巴巴工程师博客","https://www.cnblogs.com/LittleHann/");
       initMapItem("李乾坤博客","https://qiankunli.github.io/");
       initMapItem("瑝琦博客","https://knightyun.github.io/");
       initMapItem("DoubleLi博客园","https://www.cnblogs.com/lidabo");
       initMapItem("GBK内码查询","http://www.mytju.com/classcode/tools/encode_gb2312.asp");

       initMapItem("Windows_CMD命令解析","https://ss64.com/nt/");

       initMapItem("RGB_16进制转换","https://www.sioe.cn/yingyong/yanse-rgb-16/");

       initMapItem("Base64编码","https://devtool.tech/base64");

       initMapItem("ASCII码解析","https://devtool.tech/ascii");

       initMapItem("贷款计算器","https://www.fangdaijisuanqi.net/");
       initMapItem("对比贷款计算器","https://www.whsgjj.com/dkjsq/index.jhtml");
       initMapItem("常用计算工具网站","https://tool.520101.com/changyong/");



       initMapItem("Git命令协助","https://gitexplorer.com/");

       initMapItem("ffmpeg命令协助","https://evanhahn.github.io/ffmpeg-buddy/");

       initMapItem("crontab定时命令解析","https://crontab.guru/examples.html");

       initMapItem("自由鲸","https://www.freewhale.me/user/shop");

       initMapItem("乔布斯全本中文广播","https://boxueio.com/podcasts/steve-jobs");

       initMapItem("英语学习","https://jgsrty.github.io/english/introduction.html");


       initMapItem("网页斗地主","https://www.douzero.org/");

       initMapItem("点金数学头条","https://www.ixigua.com/home/1345425429761373/?source=pgc_author_name&list_entrance=anyVideo");

       initMapItem("网易公开课","https://open.163.com/");




    }

    void initMapItem(String mapKey , String mapValue){
        mDescList.add(mapKey);
        mDesc_Url_HashMap.put(mapKey,mapValue);



    }




    void showSelectedDialog(){

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
        alterDiaglog.setTitle("选择url["+(curUrlPos+1)+"]["+mDescList.size()+"]");//文字
//        alterDiaglog.setMessage("请确认删除以下文件:\n"+mFile.getAbsolutePath());//提示消息

        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        if(webView != null && webView.canGoForward()){
            alterDiaglog.setNeutralButton("前进", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(webView != null && webView.canGoForward()){
                        webView.goForward();
                    }
                }
            });

        }


        if(webView != null && webView.canGoBack()){
            alterDiaglog.setNegativeButton("后退", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(webView != null && webView.canGoBack()){
                        webView.goBack();
                    }

                }
            });


        }




        ArrayList<String> singleListAr = new ArrayList<String>();

        for (int i = 0; i < mDescList.size() ; i++) {

            singleListAr.add(mDescList.get(i));

        }



        final  CharSequence[] items = new  CharSequence[singleListAr.size()];

        int matchedCategory = -1;

        for (int i = 0; i < singleListAr.size(); i++) {
            int index_num = i+1;
            String little_index_str = DataHolder.calculLittleDigital(index_num+"");

            items[i] = little_index_str+singleListAr.get(i);

            if(curUrlPos == i){
                matchedCategory = i ;
            }
        }


        alterDiaglog.setSingleChoiceItems(items,matchedCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
                String keyName = categoryName+"";

                String urlItem = mDesc_Url_HashMap.get(keyName);

                if(urlItem != null){

                    curUrlPos = which;
                    initWebViewWithUrl(webView,mDesc_Url_HashMap.get(mDescList.get(curUrlPos)));
                    Toast.makeText(getContext(),"需要联网请耐心等待\nWebview显示Url:\n"+urlItem,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"需要联网请耐心等待\nWebview显示 Url==空",Toast.LENGTH_SHORT).show();
                }
                // 依据 这个  ArrayList<File> 重新生成数据
                if(curDialog != null){
                    curDialog.dismiss();
                }

            }
        });
        //中立的选择

        //显示
        curDialog =   alterDiaglog.show();

    }



    @Override
    protected void init() {

        android.util.Log.i("zukgit", " webview_init() curPlayPos = " + curUrlPos + " WebZtimeFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);


        init_url_map();

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showSelectedDialog();

               String urlItem =  mDesc_Url_HashMap.get(mDescList.get(curUrlPos));   // 把 url 复制到剪切板

                if(urlItem != null){

                    ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    if (manager != null) {
                        Log.i("WebView "," urlItem = "+urlItem+"  setPrimaryClip ");
                        manager.setPrimaryClip(ClipData.newPlainText(urlItem,urlItem));
                    }
                }

                return true;
            }
        });


if(!DataHolder.isMicroMathPage_Already_Loaded){

    initWebViewWithUrl(webView,mDesc_Url_HashMap.get(mDescList.get(curUrlPos)));


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


    void initWebViewWithUrl(WebView webView  , String urlItem){


        //把打开的网页展示到 当前app中 而不是使用手机自带的浏览器打开
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
//获取设置的对象
        WebSettings webSettings = webView.getSettings();

//        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");

        String agent = webSettings.getUserAgentString();

//        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.101 Mobile Safari/537.36");
        webSettings.setUserAgentString(agent);
        android.util.Log.i("WebView_Z","Zukgit_Agent agent["+agent+"]  url=["+urlItem+"]");

        //  "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.101 Mobile Safari/537.36"
        // webSettings.getUserAgentString() =   webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36");

//        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36"

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

/*        //隐藏缩放控件
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        //禁止缩放--false    true--- 支持缩放
        webSettings.setSupportZoom(true);*/


        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webSettings.setJavaScriptEnabled(true);//设置支持javaScript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        DataHolder.isMicroMathPage_Already_Loaded = true;
        webView.requestFocus();
        webView.loadUrl(urlItem);

        webView.requestFocus();


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        DataHolder.isMicroMathPage_Already_Loaded = false;
        android.util.Log.i("zukgit", "webview  onDestroy() DataHolder.isZTimePage_Already_Loaded = false");

    }


    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "webView.reload() curPlayPos = " + curUrlPos + " WebZtimeFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
//        pause_Img(false);    // 进来就调用了
//         gotoNextPage_loop();



/*        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  CA ");
            videoView.start();
        }*/
        android.util.Log.i("zukgit", "curPlayPos = " + curUrlPos + " ImgFragment.WebZtimeFragment  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curUrlPos + " ImgFragment.WebZtimeFragment  D ");

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
