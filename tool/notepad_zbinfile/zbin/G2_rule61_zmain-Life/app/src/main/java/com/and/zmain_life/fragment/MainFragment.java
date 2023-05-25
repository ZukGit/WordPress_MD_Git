package com.and.zmain_life.fragment;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.and.zmain_life.bean.DataHolder;

import com.and.zmain_life.bean.GoodBadHolder;
import com.and.zmain_life.bean.Pause_Base_Event;
import com.and.zmain_life.bean.Pause_EnglishYinBiao_Event;
import com.and.zmain_life.bean.Pause_Gif_Common_Event;
import com.and.zmain_life.bean.Pause_Img_Common_Land_Event;
import com.and.zmain_life.bean.Pause_Img_Common_Port_Event;
import com.and.zmain_life.bean.Pause_Img_Gaokao_Land_Event;
import com.and.zmain_life.bean.Pause_Img_Kaoyan_Land_Event;
import com.and.zmain_life.bean.Pause_MP3_Event;
import com.and.zmain_life.bean.Pause_MP4_Common_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Common_Port_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_English_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Gaokao_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Home_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Kaoyan_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Music_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Scene_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Scene_Port_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Music_Port_VideoEvent;
import com.and.zmain_life.bean.Pause_Gif_Land_Event;
import com.and.zmain_life.bean.Pause_Home_Land_ImgEvent;
import com.and.zmain_life.bean.Pause_Gif_Port_Event;
import com.and.zmain_life.bean.Pause_Home_Port_ImgEvent;
import com.and.zmain_life.bean.Pause_MP4_Zhexue_Land_VideoEvent;
import com.and.zmain_life.bean.Pause_StockArea_Event;
import com.and.zmain_life.bean.Pause_StockCommon_Event;
import com.and.zmain_life.bean.Pause_Stock_Simulate_Event;
import com.and.zmain_life.bean.Pause_Wall_Land_ImgEvent;
import com.and.zmain_life.bean.Pause_Wall_Port_ImgEvent;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.bean.StockSimulate_BankBean;
import com.androidkun.xtablayout.XTabLayout;
import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.base.CommPagerAdapter;
import com.and.zmain_life.bean.Pause_MP4_Home_Port_VideoEvent ;

import com.and.zmain_life.utils.RxBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;


public class MainFragment extends BaseFragment {

    private  English_YinBiao_Fragment mEnglish_YinBiao_Fragment;  // 音标Fragment

    private  Video_English_Land_Fragment  video_english_land_Fragment;  //  隐喻学习 fragment

    private Video_Zhexue_Land_Fragment video_zhexue_land_Fragment;   //  哲学学习


    private Video_Home_Port_Fragment video_home_port_Fragment; //  -- mp4_home_port
    private Video_Home_Land_Fragment video_home_land_Fragment; //  -- mp4_home_land

    private Video_Music_Port_Fragment video_music_port_Fragment; // hua--mp4_music_port
    private Video_Music_Land_Fragment video_music_land_Fragment; // hua--mp4_music_land


    private Video_Scene_Port_Fragment video_scene_port_Fragment;  // raw----mp4_scene_port
    private Video_Scene_Land_Fragment video_scene_land_Fragment;  // raw----mp4_scene_land

    private Video_Common_Port_Fragment video_common_port_Fragment;  // mp4_port
    private Video_Common_Land_Fragment video_common_land_Fragment;  // mp4_land


    private Video_Kaoyan_Land_Fragment video_kaoyan_land_Fragment; //  -- mp4_kaoyan_land
    private Video_Gaokao_Land_Fragment video_gaokao_land_Fragment; //  -- mp4_gaokao_land


    private Img_Gaokao_Land_Fragment img_gaokao_Land_Fragment;   // jpg_port
    private Img_Kaoyan_Land_Fragment img_kaoyan_Land_Fragment;   // jpg_port


    private MP3_Common_Fragment mp3_common_Fragment;  // mp3

    private Gif_Common_Fragment gif_common_Fragment;   // gif
    private Gif_Land_Fragment gif_Land_Fragment;      //   gif_land
    private Gif_Port_Fragment gif_Port_Fragment;   //        gif_port

    private Img_Common_Port_Fragment img_Common_Port_Fragment;   // jpg_port
    private Img_Common_Land_Fragment img_Common_Land_Fragment;   // jpg_land

    private Img_Wall_Land_Fragment img_Wall_Land_Fragment;    // jpg_wall_port
    private Img_Wall_Port_Fragment img_Wall_Port_Fragment;    // jpg_wall_land
    private Img_Home_Land_Fragment img_Home_Land_Fragment;      // jpg_home_land
    private Img_Home_Port_Fragment img_Home_Port_Fragment;   // jpg_home_port

    private Webview_ZGit_Fragment webview_ZGit_Fragment;
    private  Webview_ZTime_Fragment webview_ZTime_Fragment;

    private  Webview_MicroMath_Fragment  webview_MicoMath_Fragment;


    private  Stock_Common_Fragment stock_common_Fragment;     // stock
    private  Stock_Area_Fragment stock_area_Fragment;     // stock


    private  Txt_TxtEdit_Fragment txt_txtEdit_fragment;  //  /sdcard/1/1.txt  这个文件的 逻辑操作

    Loan_Calculate_Fragment loan_Calculate_Fragment ; // 计算器的逻辑

    private  Stock_Simulate_Fragment stock_simulate_fragment;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_title)
    XTabLayout tabTitle;
    @BindView(R.id.tab_mainmenu)
    XTabLayout tabMainMenu;
    public static  ArrayList<BaseFragment> fragments = new ArrayList<>();
    public static ArrayList<String> fragmentNameList =   new ArrayList<String>();
    public CommPagerAdapter pagerAdapter;
    /** 默认显示第一页推荐页 */
    public static int curPage = 13;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    private Executor singleThreadExecutor ;


    public static File[] getFragmentBaseFileArr(int fragmentIndex){  // 通过反射 拿到 DataHolder.对应的属性类型 File[] 这样就不写死 而拿到属性
        File[] fileArrItem = null ;

        System.out.println("fragments.size() = "+ fragments.size() +"  fragmentNameList.size()="+fragmentNameList.size()+"   srcfragmentindex_str="+fragmentIndex);
        for (int i = 0; i < fragments.size(); i++) {
            if(fragments.get(i).curPageIndex  == fragmentIndex){
                String fragment_name = fragments.get(i).getClass().getSimpleName();
                System.out.println("fragment_name_A = "+fragment_name +"  srcfragmentindex_str="+fragment_name);
                File[] matchArr = DataHolder.invokeGetFileListFromFragmentName(fragment_name);
                if(matchArr != null){
                    return matchArr;
                }
                return fragments.get(i).mBaseFileArr;
            }
        }

        return fileArrItem;

    }

    public static BaseFragment getBaseFragmentWithIndex(int fragmentIndex){  // 通过反射 拿到 DataHolder.对应的属性类型 File[] 这样就不写死 而拿到属性
        BaseFragment  mFragment  = null ;

        for (int i = 0; i < fragments.size(); i++) {
            if(fragments.get(i).curPageIndex  == fragmentIndex){
                BaseFragment mBasefragment = fragments.get(i);

                return mBasefragment;

            }
        }
        return mFragment;


    }


    public static BaseFragment CloseOtherFragmentEvent(int fragmentIndex){  // 通过反射 拿到 DataHolder.对应的属性类型 File[] 这样就不写死 而拿到属性
        BaseFragment  mFragment  = null ;

        for (int i = 0; i < fragments.size(); i++) {
            BaseFragment mBasefragment = fragments.get(i);
            if(mBasefragment.curPageIndex  == fragmentIndex){


                continue;

            }
if(mBasefragment.curPauseEvent != null){
    RxBus.getDefault().post(mBasefragment.curPauseEvent.Copy(false));
}

        }
        return mFragment;

    }


    @Override
    protected void init() {

//        DataHolder.init_Top_Land_Gif();
//        DataHolder.init_Top_Port_Gif();



        DataHolder.getOuttableSDcardStoragePath(getContext());
        DataHolder.init_Mp4_Home_Port();
        DataHolder.init_Mp4_Home_Land();

        DataHolder.init_Mp4_Music_Port();
        DataHolder.init_Mp4_Music_Land();



        DataHolder.init_Mp4_Scene_Port();
        DataHolder.init_Mp4_Scene_Land();

        DataHolder.init_Mp4_English_Land();  //  mp4_english_land   学习英语的视频
        DataHolder.init_Mp4_Zhexue_Land();


        DataHolder.init_Mp4_Common_Port();
        DataHolder.init_Mp4_Common_Land();
        DataHolder.init_Mp4_Kaoyan_Land();
        DataHolder.init_Mp4_Gaokao_Land();
        DataHolder.init_Common_Img_Port();
        DataHolder.init_Common_Img_Land();

        DataHolder.init_Wall_Port_Img();
        DataHolder.init_Wall_Land_Img();
        DataHolder.init_Home_Port_Img();
        DataHolder.init_Home_Land_Img();

        DataHolder.init_Kaoyan_Land_Img();
        DataHolder.init_Gaokao_Land_Img();

        DataHolder.init_MP3();
        DataHolder.init_Top_Port_Gif();
        DataHolder.init_Top_Land_Gif();
        DataHolder.init_Gif();
        DataHolder.init_Sdcard_Txt();
        DataHolder.init_Sdcard_Main_Loan_Txt(); //  /sdcard/zmain/loan.txt
        GoodBadHolder.init();


        singleThreadExecutor = Executors.newSingleThreadExecutor();
//
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {

                if(StockHolder.mBankBean  == null){
//                    StockHolder.mBankBean = new StockSimulate_BankBean();
//                    StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());
                    // 初始化  模拟股票数据的 JSON 文件
                    // 从 图片 读取 Stock 出来
                    StockHolder.initSimulateStockWithJson();
                    StockHolder.initSimulateStockWithImage();
                    StockHolder.init_ZhiShu(getContext());
                    Log.i("RefreshX","MainFragemtn  init() B2");
                    StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),null);



                }

                StockHolder.init_stock(true);

            }
            });


        setFragments();
        initPageSizeForFragment();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        setMainMenu();

    }

    public  void initPageSizeForFragment(){
        for (int i = 0; i < fragments.size(); i++) {
            BaseFragment curFragment = fragments.get(i);
            curFragment.allPageCount = fragments.size();

        }

    }


    //  fragment对应的 具体实现的 fragemnt
    // fragment对应 的 base_event
   //  该 fragment 是否 需要 file[] 作为数据源  false 为 不需要
    //  如果  ignoreFileList 为 false , 的话 那么 必须 传递一个 File[] 出来  否则 就不 添加到 FragmentManager中
    // 是为了 动态 避免 一个 文件夹中 没有 文件而导致的 crash的情况
    public  void addFragmentOperation(BaseFragment fragment  , Pause_Base_Event base_event ,
                                      boolean ignoreFileList , File[] fileArr){


        if(ignoreFileList){  // 忽略 file[]   不使用 file[] 作为 数据源的fragment
            fragment.curPageIndex = fragmentNameList.size();
            fragment.curPauseEvent = base_event;
            fragmentNameList.add(fragment.getClass().getName());

            fragments.add(fragment);
            fragment.mBaseFileArr = fileArr;
        }else{ // 使用 file[] 作为 数据源的fragment

            // 如果当前对应的 file[] 为空 或者 length=0 那么 就不往 fragmentNameList 添加 该 fragment 避免 crash
            if(fileArr == null || fileArr.length == 0 ){
                return;
            }

            fragment.curPageIndex = fragmentNameList.size();
            fragment.curPauseEvent = base_event;
            fragmentNameList.add(fragment.getClass().getName());

            fragments.add(fragment);
            fragment.mBaseFileArr = fileArr;

        }

        System.out.println("srcfragmentindex_strAAA fragments.size() = "+fragments.size());
    }

    private void setFragments() {

        mEnglish_YinBiao_Fragment = new English_YinBiao_Fragment();

        video_english_land_Fragment = new Video_English_Land_Fragment();
        video_zhexue_land_Fragment  = new Video_Zhexue_Land_Fragment();

        gif_common_Fragment = new Gif_Common_Fragment();  //0
        gif_Land_Fragment = new Gif_Land_Fragment();  // 1
        gif_Port_Fragment = new Gif_Port_Fragment();  //2
        mp3_common_Fragment =  new MP3_Common_Fragment();  // 3

        video_common_land_Fragment= new Video_Common_Land_Fragment();  // 4
        video_common_port_Fragment= new Video_Common_Port_Fragment();  // 5
        video_scene_land_Fragment= new Video_Scene_Land_Fragment();  //6
        video_scene_port_Fragment= new Video_Scene_Port_Fragment();  //7
        video_music_land_Fragment =  new Video_Music_Land_Fragment();  // 8
        video_music_port_Fragment = new Video_Music_Port_Fragment();  // 9
        video_home_land_Fragment = new Video_Home_Land_Fragment(); //10
        video_home_port_Fragment = new Video_Home_Port_Fragment(); // 11
        img_Home_Port_Fragment = new Img_Home_Port_Fragment(); // 12  【默认首页】
        img_Home_Land_Fragment = new Img_Home_Land_Fragment(); // 13
        img_Wall_Port_Fragment = new Img_Wall_Port_Fragment(); //14
        img_Wall_Land_Fragment = new Img_Wall_Land_Fragment(); //15

        img_Common_Port_Fragment = new Img_Common_Port_Fragment() ; //16
        img_Common_Land_Fragment = new Img_Common_Land_Fragment() ; //17

        stock_common_Fragment = new Stock_Common_Fragment();   //  18
        stock_area_Fragment = new Stock_Area_Fragment();   //  18_1
        txt_txtEdit_fragment = new Txt_TxtEdit_Fragment();  //  19
        loan_Calculate_Fragment = new Loan_Calculate_Fragment(); //19_1
        webview_ZGit_Fragment = new Webview_ZGit_Fragment();  // 20 webView 的页面

        webview_MicoMath_Fragment  = new Webview_MicroMath_Fragment(); // 21   micro-math 微积分计算


        webview_ZTime_Fragment = new Webview_ZTime_Fragment();  // 21   timestamp-webview




        video_kaoyan_land_Fragment = new Video_Kaoyan_Land_Fragment();
        video_gaokao_land_Fragment = new Video_Gaokao_Land_Fragment();

        img_gaokao_Land_Fragment = new Img_Gaokao_Land_Fragment();
        img_kaoyan_Land_Fragment = new Img_Kaoyan_Land_Fragment();


        stock_simulate_fragment = new Stock_Simulate_Fragment();
//
//        private Video_Kaoyan_Land_Fragment video_kaoyan_land_Fragment; //  -- mp4_kaoyan_land
//        private Video_Gaokao_Land_Fragment video_gaokao_land_Fragment; //  -- mp4_gaokao_land
//
//
//        private Img_Gaokao_Port_Fragment img_gaokao_Port_Fragment;   // jpg_port
//        private Img_Kaoyan_Port_Fragment img_kaoyan_Port_Fragment;   // jpg_port
//

  // Home Port主页    两边Video   再两边 Gif  最后图片   文件在最后


addFragmentOperation(mEnglish_YinBiao_Fragment, new Pause_EnglishYinBiao_Event(true) , true,null);


addFragmentOperation(video_english_land_Fragment, new Pause_MP4_English_Land_VideoEvent(true) , false,DataHolder.Video_English_Land_file_list);
addFragmentOperation(video_zhexue_land_Fragment, new Pause_MP4_Zhexue_Land_VideoEvent(true) , false,DataHolder.Video_Zhexue_Land_file_list);
addFragmentOperation(video_gaokao_land_Fragment, new Pause_MP4_Gaokao_Land_VideoEvent(true),false,DataHolder.Video_Gaokao_Land_file_list);
        addFragmentOperation(img_gaokao_Land_Fragment,new Pause_Img_Gaokao_Land_Event(true),false,DataHolder.Gaokao_Land_Img_file_list);
        addFragmentOperation(video_kaoyan_land_Fragment, new Pause_MP4_Kaoyan_Land_VideoEvent(true),false,DataHolder.Video_Kaoyan_Land_file_list);
        addFragmentOperation(img_kaoyan_Land_Fragment,new Pause_Img_Kaoyan_Land_Event(true),false,DataHolder.Kaoyan_Land_Img_file_list);


        addFragmentOperation(img_Common_Land_Fragment,new Pause_Img_Common_Land_Event(true),false,DataHolder.Common_Img_Land_file_list);
        addFragmentOperation(img_Wall_Land_Fragment,new Pause_Wall_Land_ImgEvent(true),false,DataHolder.Wall_Land_Img_file_list);


        addFragmentOperation(gif_common_Fragment, new Pause_Gif_Common_Event(true),false,DataHolder.Gif_file_list);
        addFragmentOperation(gif_Land_Fragment,new Pause_Gif_Land_Event(true),false,DataHolder.Top_Land_Gif_file_list);



        addFragmentOperation(video_common_land_Fragment, new Pause_MP4_Common_Land_VideoEvent(true),false,DataHolder.Video_Common_Land_file_list);
        addFragmentOperation(video_music_land_Fragment, new Pause_MP4_Music_Land_VideoEvent(true),false,DataHolder.Video_Music_Land_file_list);
        addFragmentOperation(video_scene_land_Fragment, new Pause_MP4_Scene_Land_VideoEvent(true),false,DataHolder.Video_Scene_Land_file_list);

        addFragmentOperation(img_Home_Land_Fragment,new Pause_Home_Land_ImgEvent(true),false,DataHolder.Home_Land_Img_file_list);
        addFragmentOperation(video_home_land_Fragment, new Pause_MP4_Home_Land_VideoEvent(true),false,DataHolder.Video_Home_Land_file_list);

          //  MAIN_PAGE
        addFragmentOperation(img_Home_Port_Fragment, new Pause_Home_Port_ImgEvent(true),false,DataHolder.Home_Port_Img_file_list);
        //  MAIN_PAGE


        addFragmentOperation(video_home_port_Fragment, new Pause_MP4_Home_Port_VideoEvent(true),false,DataHolder.Video_Home_Port_file_list);
        addFragmentOperation(video_scene_port_Fragment,new Pause_MP4_Scene_Port_VideoEvent(true),false,DataHolder.Video_Scene_Port_file_list);
        addFragmentOperation(video_music_port_Fragment, new Pause_MP4_Music_Port_VideoEvent(true),false,DataHolder.Video_Music_Port_file_list);
        addFragmentOperation(video_common_port_Fragment, new Pause_MP4_Common_Port_VideoEvent(true),false,DataHolder.Video_Common_Port_file_list);
        addFragmentOperation(gif_Port_Fragment, new Pause_Gif_Port_Event(true),false,DataHolder.Top_Port_Gif_file_list);


        addFragmentOperation(img_Wall_Port_Fragment,new Pause_Wall_Port_ImgEvent(true),false,DataHolder.Wall_Port_Img_file_list);
        addFragmentOperation(img_Common_Port_Fragment,new Pause_Img_Common_Port_Event(true),false,DataHolder.Common_Img_Port_file_list);




        addFragmentOperation(mp3_common_Fragment, new Pause_MP3_Event(true),false,DataHolder.MP3_file_list);
        addFragmentOperation(txt_txtEdit_fragment, null,true,null);
        addFragmentOperation(loan_Calculate_Fragment, null,true,null);
        addFragmentOperation(stock_common_Fragment,new Pause_StockCommon_Event(true),true,null);
        addFragmentOperation(stock_area_Fragment,new Pause_StockArea_Event (true),true,null);
        addFragmentOperation(stock_simulate_fragment,new Pause_Stock_Simulate_Event(true),true,null);
        addFragmentOperation(webview_ZGit_Fragment,null,true,null);
        addFragmentOperation(webview_MicoMath_Fragment,null,true,null);


        addFragmentOperation(webview_ZTime_Fragment,null,true,null);






        //         tabTitle.getTabAt(12).select();    //  设置默认页面


/*
// PageNUm 从0  开始
        fragments.add(gif_common_Fragment    );  // 0
        fragments.add(gif_Land_Fragment      );  // 1
        fragments.add(gif_Port_Fragment      );  // 2
        fragments.add(mp3_common_Fragment    );  // 3
        fragments.add(video_common_land_Fragment  );  // 4
        fragments.add(video_common_port_Fragment  );   // 5
        fragments.add(video_scene_land_Fragment   );   // 6
        fragments.add(video_scene_port_Fragment   );   // 7
        fragments.add(video_music_land_Fragment);    // 8
        fragments.add(video_music_port_Fragment);  // 9
        fragments.add(video_home_land_Fragment    );   // 10
        fragments.add(video_home_port_Fragment    );  // 11
        fragments.add(img_Home_Port_Fragment );   // 12
        fragments.add(img_Home_Land_Fragment );  // 13
        fragments.add(img_Wall_Port_Fragment );  // 14
        fragments.add(img_Wall_Land_Fragment );  // 15
        fragments.add(img_Common_Port_Fragment    );  // 16
        fragments.add(img_Common_Land_Fragment    );  // 17
        fragments.add(stock_common_Fragment    );  // // 18  放到边上
        fragments.add(txt_txtEdit_fragment    );  //  // 19  放到  stock 后面
        fragments.add(webview_ZGit_Fragment    );  // 20
        fragments.add(webview_ZTime_Fragment    );  // 21
*/



//
//        fragments.add(Top_Land_gif_Fragment);
//        fragments.add(Top_Port_gif_Fragment);
//        fragments.add(gif_Fragment);
//        fragments.add(video_raw_Fragment);
//        fragments.add(recommendFragment);
//        fragments.add(video_hua_Fragment);
//        fragments.add(img_Fragment);
//        fragments.add(Top_Port_Img_Fragment);
//        fragments.add(Top_Land_ImgFragment);


        for (int i = 0; i < fragmentNameList.size(); i++) {
            String tabNameItem = fragmentNameList.get(i);
            tabTitle.addTab(tabTitle.newTab().setText(tabNameItem));
        }


/*        tabTitle.addTab(tabTitle.newTab().setText("gifcommon"));
        tabTitle.addTab(tabTitle.newTab().setText("gifLand"));
        tabTitle.addTab(tabTitle.newTab().setText("gifPort"));
        tabTitle.addTab(tabTitle.newTab().setText("mp3common"));
        tabTitle.addTab(tabTitle.newTab().setText("videocommonland"));
        tabTitle.addTab(tabTitle.newTab().setText("videocommonport"));
        tabTitle.addTab(tabTitle.newTab().setText("videosceneland"));
        tabTitle.addTab(tabTitle.newTab().setText("videosceneport"));
        tabTitle.addTab(tabTitle.newTab().setText("videomusicland"));
        tabTitle.addTab(tabTitle.newTab().setText("videomusicport"));
        tabTitle.addTab(tabTitle.newTab().setText("videohome_land"));
        tabTitle.addTab(tabTitle.newTab().setText("videohome_port"));
        tabTitle.addTab(tabTitle.newTab().setText("imgHomePort"));
        tabTitle.addTab(tabTitle.newTab().setText("imgHomeLand"));
        tabTitle.addTab(tabTitle.newTab().setText("imgWallPort"));
        tabTitle.addTab(tabTitle.newTab().setText("imgWallLand"));
        tabTitle.addTab(tabTitle.newTab().setText("imgCommonPort"));
        tabTitle.addTab(tabTitle.newTab().setText("imgCommonLand"));
        tabTitle.addTab(tabTitle.newTab().setText("stock"));
        tabTitle.addTab(tabTitle.newTab().setText("txt_operation"));
        tabTitle.addTab(tabTitle.newTab().setText("webview_zgit"));
        tabTitle.addTab(tabTitle.newTab().setText("webview_ztime"));*/


        String[] pageAdapterStringArr = new String[fragmentNameList.size()];
        for (int i = 0; i < fragmentNameList.size() ; i++) {
            String fragmentNameStr = fragmentNameList.get(i);
            pageAdapterStringArr[i]=fragmentNameStr;
        }


        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments,pageAdapterStringArr );

/*

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] { "gifcommon",
                "gifLand",
                "gifPort",
                "mp3common",
                "videocommon_land",
                "videocommon_port",
                "videoscene_land",
                "videoscene_port",
                "videomusic_land",
                "videomusic_port",
                "videohome_land",
                "videohome_port",
                "imgHomePort",
                "imgHomeLand",
                "imgWallPort",
                "imgWallLand",
                "imgCommon_port",
                "imgCommon_land",
                "stock",
                "txtOperation",
                "webview_zgit",
                "webview_ztime",
});
*/


//        tabTitle.addTab(tabTitle.newTab().setText("GIF"));
//        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"IMG", "MP4", "GIF"});
//        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"GIF_L","GIF_P","GIF","MP4_RAW","MP4","MP4_HUA","JPG","JPG_P","JPG_L"});
//        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"MP4"});
        viewPager.setAdapter(pagerAdapter);
        tabTitle.setupWithViewPager(viewPager);

        if(img_Home_Port_Fragment.curPageIndex > 0){  // 有 Home_port 那么 它当主页  没有 home_port 那么 music 当主页
            tabTitle.getTabAt(img_Home_Port_Fragment.curPageIndex).select();// 默认的 Tab 起始页
        }else{
            tabTitle.getTabAt(mp3_common_Fragment.curPageIndex).select();
        }


        tabTitle.setVisibility(View.GONE);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                android.util.Log.i("zukgit"," addOnPageChangeListener  position="+ position + "   positionOffset="+positionOffset+"    positionOffsetPixels="+ positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {


                curPage = position;
//                DataHolder.Video_initPos = curPage;

//                fragments


                System.out.println("srcfragmentindex_str  onPageSelected  position="+position+" fragments.size()="+fragments.size());
                for (int i = 0; i < fragments.size(); i++) {
                    BaseFragment curFragment = fragments.get(i);
                    int curFragmentPageIndex = curFragment.curPageIndex;
                    int curPosition = position;
                    if(curFragmentPageIndex == curPosition){
                           if(curFragment.curPauseEvent != null && getContext() != null ){
                               RxBus.getDefault().post(curFragment.curPauseEvent.Copy(true));
                           }
                    }else{
                        if(curFragment.curPauseEvent != null && getContext() != null ){
                            RxBus.getDefault().post(curFragment.curPauseEvent.Copy(false));
                        }

                    }
                }


/*

                android.util.Log.i("zukgit","onPageSelected  position="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Gif_initPos="+ DataHolder.Top_Land_Gif_initPos+"    DataHolder.Top_Port_Gif_initPos="+ DataHolder.Top_Port_Gif_initPos+ "    DataHolder.Gif_initPos="+ DataHolder.Gif_initPos+"    DataHolder.Video_initPos="+ DataHolder.Video_Home_Port_initPos +"    DataHolder.Img_initPos="+ DataHolder.Common_Img_Port_initPos+"    DataHolder.Top_Port_Img_initPos="+ DataHolder.Wall_Port_Img_initPos +"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);

                //  GIF_Common    页面
                if (position == 0) {

                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Gif_initPos="+ DataHolder.Top_Land_Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Common_Event(true));
                } else {

                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Gif_initPos="+ DataHolder.Top_Land_Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Common_Event(false));
                }



                if (position == 1) {          //  GIF_Land    页面
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Gif_initPos="+ DataHolder.Top_Port_Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Land_Event(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Gif_initPos="+ DataHolder.Top_Port_Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Land_Event(false));
                }




                if (position == 2) { //  GIF_Port    页面
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Gif_initPos="+ DataHolder.Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Port_Event(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Gif_initPos="+ DataHolder.Gif_initPos);
                    RxBus.getDefault().post(new Pause_Gif_Port_Event(false));
                }

                // MP3 页面    Pause_MP3_Event
                if (position == 3) {
                    //继续播放
                    android.util.Log.i("zukgit","D Video_Raw_initPos  播放="+ position + "   curPage="+curPage+"    DataHolder.Video_Raw_Port_initPos="+ DataHolder.Video_Scene_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP3_Event(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  Video_Raw_initPos  暂停="+ position + "   curPage="+curPage+"    DataHolder.Video_Raw_Port_initPos="+ DataHolder.Video_Scene_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP3_Event(false));
                }


                // Video 页面    Video  Pause_MP4_Common_VideoEvent

                if (position == 4) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.initPos="+ DataHolder.Video_Home_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Common_Land_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.initPos="+ DataHolder.Video_Home_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Common_Land_VideoEvent(false));
                }

                if (position == 5) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.initPos="+ DataHolder.Video_Home_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Common_Port_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.initPos="+ DataHolder.Video_Home_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Common_Port_VideoEvent(false));
                }

                // Video 页面    Video  Pause_MP4_Scene_VideoEvent
                if (position == 6) {
                    //继续播放
                    android.util.Log.i("zukgit","D Pause_Hua_VideoEvent  播放="+ position + "   curPage="+curPage+"    DataHolder.Video_Raw_Port_initPos="+ DataHolder.Video_Scene_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Scene_Land_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  Pause_Hua_VideoEvent  暂停="+ position + "   curPage="+curPage+"    DataHolder.Video_Hua_Port_initPos="+ DataHolder.Video_Music_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Scene_Land_VideoEvent(false));
                }



                // Video 页面    Video  Pause_MP4_Scene_VideoEvent
                if (position == 7) {
                    //继续播放
                    android.util.Log.i("zukgit","D Pause_Hua_VideoEvent  播放="+ position + "   curPage="+curPage+"    DataHolder.Video_Raw_Port_initPos="+ DataHolder.Video_Scene_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Scene_Port_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  Pause_Hua_VideoEvent  暂停="+ position + "   curPage="+curPage+"    DataHolder.Video_Hua_Port_initPos="+ DataHolder.Video_Music_Port_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Scene_Port_VideoEvent(false));
                }

                //  Video    页面 Pause_MP4_Music_VideoEvent
                if (position == 8) {  // Pause_MP4_Music_VideoEvent
                    //继续播放
                    RxBus.getDefault().post(new Pause_MP4_Music_Land_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    RxBus.getDefault().post(new Pause_MP4_Music_Land_VideoEvent(false));
                }



//  Video    页面 Pause_MP4_Music_VideoEvent
                if (position == 9) {  // Pause_MP4_Music_VideoEvent
                    //继续播放
                    RxBus.getDefault().post(new Pause_MP4_Music_Port_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    RxBus.getDefault().post(new Pause_MP4_Music_Port_VideoEvent(false));
                }

                //  Video    页面 Pause_MP4_Home_VideoEvent
                if (position == 10) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Home_Land_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Home_Land_VideoEvent(false));
                }


//  Video    页面 Pause_MP4_Home_VideoEvent
                if (position == 11) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Home_Port_VideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Port_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_MP4_Home_Port_VideoEvent(false));
                }

                //  Img    页面 Pause_Home_Port_ImgEvent
                if (position == 12) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Home_Port_ImgEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Home_Port_ImgEvent(false));
                }


                //  Img    页面 Pause_Home_Port_ImgEvent
                if (position == 13) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Home_Land_ImgEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Home_Land_ImgEvent(false));
                }

                //  Img    页面 Pause_Home_Land_ImgEvent
                if (position == 14) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Wall_Port_ImgEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Wall_Port_ImgEvent(false));
                }



                //  Img    页面 Pause_Wall_Port_ImgEvent
                if (position == 15) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_Wall_Land_ImgEvent(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Port_Img_initPos);
                    RxBus.getDefault().post(new Pause_Wall_Land_ImgEvent(false));
                }


                //  Img    页面 Pause_Img_Common_Event
                if (position == 16) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Img_Common_Port_Event(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Img_Common_Port_Event(false));
                }


                //  Img    页面 Pause_Img_Common_Event
                if (position == 17) {
                    //继续播放
                    android.util.Log.i("zukgit","D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Img_Common_Land_Event(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit","E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Img_Common_Land_Event(false));
                }


                if (position == 18) {  // stock 页面
                    //继续播放
                    android.util.Log.i("zukgit_stock_fragment"," true D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(true));
                    RxBus.getDefault().post(new Pause_Stock_Event(true));

                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit_stock_fragment"," false E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                    RxBus.getDefault().post(new Pause_Stock_Event(false));
                }


                if (position == 19) {  // txt_operation  页面
                    //继续播放
                    android.util.Log.i("txt_operation_fragment"," true D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(true));
                 //   RxBus.getDefault().post(new Pause_Stock_Event(true));

                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("txt_operation_fragment"," false E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
                  //  RxBus.getDefault().post(new Pause_Stock_Event(false));
                }




                if (position == 20) {  // webview 页面
                    //继续播放
                    android.util.Log.i("zukgit webview_zgit"," true D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit webview_zgit"," false E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(false));
                }

                if (position == 21) {  // webview 页面
                    //继续播放
                    android.util.Log.i("zukgit webview_ztime"," true D  播放="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(true));
                } else {
                    //切换到其他页面，需要暂停
                    android.util.Log.i("zukgit webview_ztime"," false E  暂停="+ position + "   curPage="+curPage+"    DataHolder.Top_Land_Img_initPos="+ DataHolder.Wall_Land_Img_initPos);
//                    RxBus.getDefault().post(new Pause_Img_Common_Event(false));
                }


*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

   public void dissmiss () {

       for (int i = 0; i < fragments.size(); i++) {
           Fragment curFragment = fragments.get(i);
           if(curFragment instanceof  MP3_Common_Fragment){
               android.util.Log.i("zukgit","D  mp3fragment.dismiss()");

               MP3_Common_Fragment mp3fragment = (MP3_Common_Fragment)curFragment;
               mp3fragment.dismiss();
           } else if(curFragment instanceof  Stock_Common_Fragment){
               android.util.Log.i("zukgit","D  Stock_Common_Fragment.dismiss()");

               Stock_Common_Fragment stockfragment = (Stock_Common_Fragment)curFragment;
               stockfragment.dismiss();
           }

       }


    }

    private void setMainMenu() {
        tabMainMenu.addTab(tabMainMenu.newTab().setText("首页"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("好友"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText(""));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("消息"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("我"));
    }

}
