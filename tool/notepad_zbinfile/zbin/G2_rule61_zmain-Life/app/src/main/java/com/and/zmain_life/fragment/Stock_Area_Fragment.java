package com.and.zmain_life.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.APP_In_BackGround_Event;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_StockArea_Event;
import com.and.zmain_life.bean.Pause_StockCommon_Event;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.stock_node.Area_MultiLevelPickerWindow;
import com.and.zmain_life.stock_node.Stock_MultiLevelPickerWindow;
import com.and.zmain_life.stock_node.Stock_NodeImpl;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import rx.functions.Action1;

// All { 衣食住行 }{  Item }
// 北京
//  上海
// 重庆
// 天津   { ALl , 衣食住行 }
//
public class Stock_Area_Fragment extends BaseFragment {
    String TAG = "Stock_Area_Fragment";



    Area_MultiLevelPickerWindow<Stock_NodeImpl> window;




    @BindView(R.id.stock_area_title)
    TextView stock_market_title;    // stock_market_title
    String stock_market_title_originStr;


    @BindView(R.id.area_layout)
    LinearLayout mAreaLayout;


    private ViewPagerLayoutManager viewPagerLayoutManager;

    /**
     * 当前播放视频位置
     */
    private int curPlayPos = -1;

    /*    private FullScreenVideoView videoView;*/

/*    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;*/


    Context mContext;

    // 关闭窗口切换fragment时  windows是否是显示的   如果是显示的 切换回时则显示
    boolean isAreaPageShowWindows_When_Dismiss = false;

    File Sdcard_File;
    File ZMain_File;

    private static boolean isFirst = true;

    static Stock_NodeImpl  Area_NodeImpl_Root;


/*
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }
*/






    public String getRandomColorString(){
        Random  rd = new Random();
        int a = rd.nextInt(256);
        int r =  rd.nextInt(256);
        int g =  rd.nextInt(256);
        int b =  rd.nextInt(256);
        String a_str = Integer.toHexString(a);
        String r_str = Integer.toHexString(r);
        String g_str = Integer.toHexString(g);
        String b_str = Integer.toHexString(b);
        if(a_str.length() == 1){
            a_str = "0"+a_str;
        }


        if(r_str.length() == 1){
            r_str = "0"+r_str;
        }
        if(g_str.length() == 1){
            g_str = "0"+g_str;
        }
        if(b_str.length() == 1){
            b_str = "0"+b_str;
        }

        return   "#"+a_str+r_str+g_str+b_str;

    }

    public void setTitleColor_Green(){
        if(stock_market_title != null){
//            String colorStr = getRandomColor().toUpperCase();
//            android.util.Log.i("zukgit","setTitleColor_Green  colorStr = "+ colorStr);
            stock_market_title.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    public void setTitleColor_Black(){
        if(stock_market_title != null){
            stock_market_title.setTextColor(Color.parseColor("#000000"));

        }
        android.util.Log.i("zukgit","setTitleColor_Black  #000000");
    }

    // @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public void background_color_changed(){

        if(mAreaLayout != null){

            DataHolder.MP3_Layout_color_Random = Color.parseColor(getRandomColorString());
            mAreaLayout.setBackgroundColor(DataHolder.MP3_Layout_color_Random);


            mAreaLayout.requestLayout();
        }
        android.util.Log.i("zukgit","background_color_changed  #xxxx");
    }








    @Override
    protected int setLayoutId() {
        return R.layout.stock_area_main;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.mlp_mp3_node_main);
//        stock_market_title = findViewById(R.id.tv);
//        stock_market_title.setOnClickListener(this::show);
//
//
//    }




    // @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void init() {


        setViewPagerLayoutManager();

//        setRefreshEvent();
        mContext = getContext();


        if(StockHolder.day_lastxlsx_timestamp != null){
//            stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
        }
        stock_market_title.setOnClickListener(this::show_click);  // 设置 title的 点击时间
        stock_market_title.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {


StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex = StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex + 1;

                if(window != null){
                    window.sort_node3();
                    window.update();

                }else{

                    show_click(v);
                }

                if(stock_market_title != null){

                    String titleText = stock_market_title.getText().toString();
                    if(titleText.contains("[")){
                        titleText =   titleText.substring(0,titleText.indexOf("["));
                    }

                    int step_index = StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex%StockHolder.mShowLevel3Area_In_AreaPage_ModeCount + 1;
                        stock_market_title.setText(titleText+"["+step_index+"_"+StockHolder.mShowLevel3Area_In_AreaPage_ModeCount+"]");
                }


                return false;
            }
        });
//        color_ID_Random = ZUtil.getRainbowColor_random();
//        randomColor =  Color.parseColor(getRandomColor())
//        color_ID_Random = randomColor.toArgb();  // 随机


//        stockLayout.setBackgroundColor(color_ID_Random);

        mAreaLayout.requestLayout();


        RxBus.getDefault().toObservable(Pause_StockArea_Event.class).subscribe((Action1<Pause_StockArea_Event>) event -> {



            if (event.isPlayOrPause()) {  // 离开 Stock 页面
                android.util.Log.i("zukgit", "Pause_StockArea_Event  event.isPlayOrPause()="+event.isPlayOrPause());
                StockHolder.isAreaPage_ChangeTo_AnotherPage=false;

                if(stock_market_title != null && isAreaPageShowWindows_When_Dismiss){
                    show(stock_market_title);
                    isAreaPageShowWindows_When_Dismiss = false;
                    android.util.Log.i("zukgit", "Pause_Stock_Event_1  isAreaPageShowWindows_When_Dismiss = false");
                }

                if(stock_market_title != null){

                    String titleText = stock_market_title.getText().toString();
                    if(titleText.contains("请稍等")){


                        int step_index = StockHolder.mShowLevel3Area_In_AreaPage_ModeIndex%StockHolder.mShowLevel3Area_In_AreaPage_ModeCount + 1;
                        stock_market_title.setText(stock_market_title_originStr+"["+step_index+"_"+StockHolder.mShowLevel3Area_In_AreaPage_ModeCount+"]");
                    }

                }


            } else {
                android.util.Log.i("zukgit", "Pause_StockArea_Event  event.isPlayOrPause()="+event.isPlayOrPause());

                StockHolder.isAreaPage_ChangeTo_AnotherPage=true;

                if(window != null){


                    if(window.isShowing()){
                        isAreaPageShowWindows_When_Dismiss =  window.isShowing();
                        android.util.Log.i("zukgit", "Pause_Stock_Event_2  isAreaPageShowWindows_When_Dismiss = "+isAreaPageShowWindows_When_Dismiss);
                    }


                    android.util.Log.i("zukgit", "获取A isAreaPageShowWindows_When_Dismiss = " + isAreaPageShowWindows_When_Dismiss + "   ");

                    window.dismiss();

                }

            }

        });


        RxBus.getDefault().toObservable(APP_In_BackGround_Event.class).subscribe((Action1<APP_In_BackGround_Event>) event -> {
            if (event.isPlayOrPause()) {  // true  后台
                android.util.Log.i("zukgit", "APP_In_BackGround_Event=后台事件 ZXXZ");


                if(window != null){
                    isAreaPageShowWindows_When_Dismiss =      window.isShowing();
                    android.util.Log.i("zukgit", "APP_In_BackGround_Event_1  isAreaPageShowWindows_When_Dismiss ="+ isAreaPageShowWindows_When_Dismiss);
                }

            }else{   // 前台


                if(window != null && stock_market_title != null){
                    if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
                        window.show(stock_market_title,isAreaPageShowWindows_When_Dismiss);
                        isAreaPageShowWindows_When_Dismiss =      true;
                        android.util.Log.i("zukgit", "APP_In_BackGround_Event_2  AreaFragment curPageIndex="+curPageIndex+"  isAreaPageShowWindows_When_Dismiss ="+ isAreaPageShowWindows_When_Dismiss);


                    }else{
                        if(window != null){
                            window.dismiss();
                        }

                    }



                }

                android.util.Log.i("zukgit", "APP_In_BackGround_Event=前台事件 ZXXZ ");
            }
        });


    }





    private void TitleLongClick(View v) {
        android.util.Log.i("xxzukgdait", " 文字点击");
        if (window == null) {
            window = new Area_MultiLevelPickerWindow<>(mContext, DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit", "AA_1 文字点击");

        }
    }


    private void show_click(View v) {
        android.util.Log.i("xxzukgit"," 文字点击");
        if (window == null) {
            window = new Area_MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit","AA_1 文字点击");

        }
        window.setOnSelectListener(new Area_MultiLevelPickerWindow.OnSelectListener<Stock_NodeImpl>() {

            @Override
            public void onMusicPlay(boolean isMusicPlay) {
                if(isMusicPlay){
                    setTitleColor_Green();
                }else{
                    setTitleColor_Black();
                }

            }


            @Override
            public void onSelect(int level, Stock_NodeImpl Stock_NodeImpl, boolean isOffMediaPlayer) {
                android.util.Log.i("dadadxxzukgit","BB_1 文字点击");
                if (Stock_NodeImpl == null) {
                    stock_market_title.setText("");
                    return;
                }
                if(Stock_NodeImpl.level == 1){

                }

                if(Stock_NodeImpl.level == 2){

                }

                if(Stock_NodeImpl.level == 3){
//                    mPart3_SongName = Stock_NodeImpl.name;

                    if(isOffMediaPlayer){  // 后台播放的情况
//                        mPartTitle = current_mPart1_Alphabet+"¹"+current_mPart2_Artist+"²"+mPart3_SongName+"³";

                    }else{
//                        mPartTitle = mPart1_Alphabet+"¹"+mPart2_Artist+"²"+mPart3_SongName+"³";
//                        current_mPart1_Alphabet = mPart1_Alphabet;
//                        current_mPart2_Artist  = mPart2_Artist;
                    }
//                    stock_market_title.setText(mPartTitle);
                }


            }

            @Override
            public void onBackgroundColorChanged() {
                background_color_changed();
            }

            @Override
            public void onDownGraded(int selectLevel, Stock_NodeImpl Stock_NodeImpl) {
                if (selectLevel <= 0) {
                    Toast.makeText(mContext.getApplicationContext(), "降级到: " + selectLevel, Toast.LENGTH_SHORT).show();
                    android.util.Log.i("xxzukgit","CC_1 ");
                    return;
                }
                Toast.makeText(mContext.getApplicationContext(), String.format(Locale.ENGLISH, "降级到%s级: %s", selectLevel, Stock_NodeImpl.toString()), Toast.LENGTH_SHORT).show();
                android.util.Log.d("onDownGraded",
                        String.format(
                                Locale.ENGLISH,
                                "降级到%d级: %s",
                                selectLevel,
                                Stock_NodeImpl.toString()
                        )
                );
            }

            @Override
            public void onShow() {
                android.util.Log.d("onDownGraded", "show");
                android.util.Log.i("xxzukgit","DD_1 ");
            }

            @Override
            public void onDismiss() {
                android.util.Log.d("onDownGraded", "dismiss");
                android.util.Log.i("xxzukgit","DD_1 ");
            }
        });

        // 填充数据 使用 自带的  raw_json
//        window.updateData(generate_withJsonFile(mContext));

        // 使用系统动态计算的JSON

        synchronized (this){
            if(Area_NodeImpl_Root != null){

                window.updateData(Area_NodeImpl_Root);
            }else{
                Area_NodeImpl_Root =generate_gupiaoliebiao_withZMainStockDir(mContext);
                window.updateData(Area_NodeImpl_Root);
            }

        }

        window.show(stock_market_title);



    }


    private void show(View v) {
        android.util.Log.i("xxzukgit"," 文字点击");
        if (window == null) {
            window = new Area_MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit","AA_1 文字点击");

        }
        window.setOnSelectListener(new Area_MultiLevelPickerWindow.OnSelectListener<Stock_NodeImpl>() {

            @Override
            public void onMusicPlay(boolean isMusicPlay) {
                if(isMusicPlay){
                    setTitleColor_Green();
                }else{
                    setTitleColor_Black();
                }

            }


            @Override
            public void onSelect(int level, Stock_NodeImpl Stock_NodeImpl, boolean isOffMediaPlayer) {
                android.util.Log.i("dadadxxzukgit","BB_1 文字点击");
                if (Stock_NodeImpl == null) {
                    stock_market_title.setText("");
                    return;
                }
                if(Stock_NodeImpl.level == 1){

                }

                if(Stock_NodeImpl.level == 2){

                }

                if(Stock_NodeImpl.level == 3){
//                    mPart3_SongName = Stock_NodeImpl.name;

                    if(isOffMediaPlayer){  // 后台播放的情况
//                        mPartTitle = current_mPart1_Alphabet+"¹"+current_mPart2_Artist+"²"+mPart3_SongName+"³";

                    }else{
//                        mPartTitle = mPart1_Alphabet+"¹"+mPart2_Artist+"²"+mPart3_SongName+"³";
//                        current_mPart1_Alphabet = mPart1_Alphabet;
//                        current_mPart2_Artist  = mPart2_Artist;
                    }
//                    stock_market_title.setText(mPartTitle);
                }


            }

            @Override
            public void onBackgroundColorChanged() {
                background_color_changed();
            }

            @Override
            public void onDownGraded(int selectLevel, Stock_NodeImpl Stock_NodeImpl) {
                if (selectLevel <= 0) {
                    Toast.makeText(mContext.getApplicationContext(), "降级到: " + selectLevel, Toast.LENGTH_SHORT).show();
                    android.util.Log.i("xxzukgit","CC_1 ");
                    return;
                }
                Toast.makeText(mContext.getApplicationContext(), String.format(Locale.ENGLISH, "降级到%s级: %s", selectLevel, Stock_NodeImpl.toString()), Toast.LENGTH_SHORT).show();
                android.util.Log.d("onDownGraded",
                        String.format(
                                Locale.ENGLISH,
                                "降级到%d级: %s",
                                selectLevel,
                                Stock_NodeImpl.toString()
                        )
                );
            }

            @Override
            public void onShow() {
                android.util.Log.d("onDownGraded", "show");
                android.util.Log.i("xxzukgit","DD_1 ");
            }

            @Override
            public void onDismiss() {
                android.util.Log.d("onDownGraded", "dismiss");
                android.util.Log.i("xxzukgit","DD_1 ");
            }
        });

        // 填充数据 使用 自带的  raw_json
//        window.updateData(generate_withJsonFile(mContext));

        // 使用系统动态计算的JSON

        synchronized (this){
            if(Area_NodeImpl_Root != null){

                window.updateData(Area_NodeImpl_Root);
            }else{
                Area_NodeImpl_Root =generate_gupiaoliebiao_withZMainStockDir(mContext);
                window.updateData(Area_NodeImpl_Root);
            }

        }

        window.show(stock_market_title,isAreaPageShowWindows_When_Dismiss);



    }



    static void LogPrint(String bigLogStr,String tag){


        if(bigLogStr.length() > 4000) {
            for(int i=0;i<bigLogStr.length();i+=4000){
                if(i+4000<bigLogStr.length())
                    android.util.Log.i(tag,bigLogStr.substring(i, i+4000));
                else
                    android.util.Log.i(tag,bigLogStr.substring(i, bigLogStr.length()));
            }
        } else
            android.util.Log.i(tag,bigLogStr);
    }

    //  在 /sdcard/zmain/J0_股票列表.xlsx 转为的 json 文件  转为 一个 Stock_NodeImpl
    private  synchronized  Stock_NodeImpl generate_gupiaoliebiao_withZMainStockDir(Context context) {

        try {

            String json_result = StockHolder.calAreaIndustryJson();
            if(json_result == null || "".equals(json_result)){
                if(stock_market_title != null){

                    String titleText = stock_market_title.getText().toString();
                    if(titleText.contains("Info")){
                        stock_market_title_originStr = titleText;
                    }
                    stock_market_title.setText("请稍等,初始化数据中......");
                }
            }
//            String json_result = DataHolder.getZmainMp3Json();
            Stock_NodeImpl tree = new Gson().fromJson(json_result, Stock_NodeImpl.class);
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
//            android.util.Log.i("json_result",json_result);
//            LogPrint(json_result,"json_result");
//            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
            if (isFirst) {
                isFirst = false;


                return tree;
            } else {
                //noinspection ConstantConditions
                android.util.Log.i("xxzukgit","FF_1 ");
//                tree.children().remove(0);
                return tree;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stock_NodeImpl.EMPTY;
    }


    private static Stock_NodeImpl generate_withJsonFile(Context context) {
        android.util.Log.i("xxzukgit","EE_1 ");
        try {
            InputStream is = context.getAssets().open("tree.json");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            Stock_NodeImpl tree = new Gson().fromJson(result.toString(), Stock_NodeImpl.class);
            if (isFirst) {
                isFirst = false;
                return tree;
            } else {
                //noinspection ConstantConditions
                android.util.Log.i("xxzukgit","FF_1 ");
//                tree.children().remove(0);
                return tree;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Stock_NodeImpl.EMPTY;
    }



/*    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private VideoAdapter adapter;
    */




    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "════════════════════════════ Stock_Area_Fragment_______onResume Begin════════════════════════════");
        android.util.Log.i("zukgit", "【Stock_Area_Fragment__onResume()】____起点1 ");

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
        android.util.Log.i("zukgit", "Stock_Area_Fragment  onResume() 是否全屏变化  MainActivity.curMainPage="+MainActivity.curMainPage);
        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onResume  CA ");
//            videoView.start();
            android.util.Log.i("zukgit", "【Stock_Area_Fragment__onResume()】____起点3 ");

            android.util.Log.i("zukgit", "【Stock_Area_Fragment__onResume()】____起点6   stock_market_title != null【"+(stock_market_title != null)+"】  isAreaPageShowWindows_When_Dismiss="+isAreaPageShowWindows_When_Dismiss);

            android.util.Log.i("zukgit", "恢复A onResume()  = " + "  MainFragment.curPage=");

            if(stock_market_title_originStr != null && stock_market_title_originStr.contains("Info")){
                stock_market_title.setText(stock_market_title_originStr);
            }


            if(stock_market_title != null && isAreaPageShowWindows_When_Dismiss){
                show(stock_market_title);

//                window.showLastNode3Impl();   // 显示正确的 那个
                isAreaPageShowWindows_When_Dismiss = true;
                android.util.Log.i("zukgit", "【Stock_Area_Fragment__onResume()】____起点5  isAreaPageShowWindows_When_Dismiss="+isAreaPageShowWindows_When_Dismiss);
            }


        }



        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onResume  CB  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onResume  D  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);


        android.util.Log.i("zukgit", "════════════════════════════ Stock_Area_Fragment_______onResume End════════════════════════════");


    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }



    public void  dismiss(){
        if(window != null ){
            window.dismiss();
        }



    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        android.util.Log.i("zukgit", "Stock_Area_Fragment  onConfigurationChanged() 是否全屏变化 ");
        super.onConfigurationChanged(newConfig);

        if(window != null &&  stock_market_title != null && window.isShowing()){
            window.dismiss();
            isAreaPageShowWindows_When_Dismiss = true;
            window.show(stock_market_title,isAreaPageShowWindows_When_Dismiss);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        android.util.Log.i("zukgit", "Stock_Area_Fragment  onSaveInstanceState() 是否全屏变化 ");


        super.onSaveInstanceState(outState);
    }



    @Override
    public void onPause() {
        android.util.Log.i("zukgit", "Stock_Area_Fragment  onPause() 是否全屏变化 ");
        super.onPause();
        if(window != null){



            if(window.isShowing()){
                isAreaPageShowWindows_When_Dismiss =  window.isShowing();
                android.util.Log.i("zukgit", "获取A isAreaPageShowWindows_When_Dismiss = " + isAreaPageShowWindows_When_Dismiss);
            }



//            window.dismiss();
        }

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Area_Fragment.onPause  ");
//        videoView.pause();
    }

    @Override
    public void onStop() {
        android.util.Log.i("zukgit", "Stock_Area_Fragment  onPause() 是否全屏变化 ");
        super.onStop();

    }

    static int user_backup_num = 0;


    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
    }
/*    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());

        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(0);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                android.util.Log.i("zukgit", "onInitComplete = " + curPlayPos + " position  = 1 A ");
//                playCurVideo(DataHolder.MP3_initPos);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (ivCurCover != null) {
//                    ivCurCover.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                user_backup_num++;
                Action_UP_Pretime = 0;
                mediaControl.setVisibility(View.INVISIBLE);
                android.util.Log.i("zukgit", "X1 Stock_Area_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.MP3_initPos = DataHolder.MP3_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.MP3_initPos = DataHolder.MP3_initPos + 1;

                }


                if (DataHolder.MP3_initPos < 0 || position == 0) {
                    DataHolder.MP3_initPos = 0;
                }
                curPlayPos = DataHolder.MP3_initPos;
                android.util.Log.i("zukgit", "X2  Stock_Area_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " user_backup_num = " + user_backup_num);

                playCurVideo(DataHolder.MP3_initPos);
            }
        });
    }
*/

/*
    private void setRefreshEvent() {
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                android.util.Log.i("zukgit", "refreshLayout  onTick  millisUntilFinished = " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }
*/


    private void playCurVideo(int position) {
        android.util.Log.i("zukgit", "Video_Music A playCurVideo curPlayPos = " + curPlayPos + " position  =" + position);

/*        if (position == curPlayPos) {
            android.util.Log.i("zukgit","A_1  playCurVideo curPlayPos = "+ curPlayPos + " position  ="+ position);
            return;
        }*/

        android.util.Log.i("zukgit", "Video_Music B playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + "  viewPagerLayoutManager.getChildCount() = " + viewPagerLayoutManager.getChildCount());

//        View itemView = viewPagerLayoutManager.findViewByPosition(position);

        int childCount = viewPagerLayoutManager.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View viewItem = viewPagerLayoutManager.getChildAt(i);
            System.out.println("View[ " + i + "] = " + viewItem.toString());
        }

//        if (itemView == null) {
//            android.util.Log.i("zukgit","C playCurVideo  curPlayPos = "+ curPlayPos + " position  ="+ position);
//            return;
//        }

        android.util.Log.i("zukgit", "Video_Music D playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + " DataCreate.datas.size = " + DataCreate.datas.size());


        ViewGroup rootView = (ViewGroup) viewPagerLayoutManager.getChildAt(0);
/*
        LikeView likeView = rootView.findViewById(R.id.likeview);
//        ControllerView controllerView = rootView.findViewById(R.id.controller);
        ImageView ivPlay = rootView.findViewById(R.id.iv_play);
        ImageView ivCover = rootView.findViewById(R.id.iv_cover);
        ivPlay.setAlpha(0.4f);

        //播放暂停事件
        likeView.setOnPlayPauseListener(() -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                ivPlay.setVisibility(View.VISIBLE);
            } else {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
            }
        });*/

        //评论点赞事件
//        likeShareEvent(controllerView);

//        ImageView ivCover = rootView.findViewById(R.id.iv_cover);

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(new CurUserBean(DataCreate.datas.get(position).getUserBean()));
        android.util.Log.i("zukgit", "Video_Music E playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        curPlayPos = position;

        //切换播放器位置
        dettachParentView(rootView);
        android.util.Log.i("zukgit", "Video_Music F playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
//        autoPlayVideo(curPlayPos, ivCover);
//        autoPlayVideo(curPlayPos,ivCover);



//        autoPlayVideo(curPlayPos);
        android.util.Log.i("zukgit", "Video_Music G playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);

    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " dettachParentView  ");

/*        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);*/
    }

    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    /**
     * 自动播放视频
     */
    static long Action_UP_Pretime;
    static int time_space = 1500;

    private void autoPlayVideo(int position) {
//        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
//        mediaControl = new MediaController(getContext());
        String fileName = getTimeStamp() + "_" + DataHolder.MP3_file_list[DataHolder.MP3_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.MP3_file_list[DataHolder.MP3_random_indexlist.get(position)]);

        File    jiemiFile = DataHolder.MP3_file_list[DataHolder.MP3_random_indexlist.get(position)];


        Uri uri_item = Uri.fromFile(jiemiFile);


//        videoView.setMediaController(mediaControl);

//        mediaControl.hide();
//        File tempFile = new File(bgVideoPath);
/*        videoView.setVideoPath(uri_item.getPath());
        videoView.setClickable(true);


        videoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mediaControl != null){

                    mediaControl.setVisibility(View.VISIBLE);
                    android.util.Log.i("zukgit", " videoView.setOnClick ");


                }
            }
        });
        videoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mediaControl != null){

                    mediaControl.setVisibility(View.VISIBLE);
                    android.util.Log.i("zukgit", " videoView.setOnLongClickListener ");
                    return true;


                }
                return false;
            }
        });


        videoView.setOnTouchListener( new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                android.util.Log.i("zukgit", " Pause_Music_VideoEvent setOnTouchListener  motionEvent ="+ motionEvent.getAction() + "_Pretime  ="+ Action_UP_Pretime);
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:  // 0
                        long now = System.currentTimeMillis();
                        if(Action_UP_Pretime == 0){
                            Action_UP_Pretime = System.currentTimeMillis();
                            return false;
                        }
                        long distance = now - Action_UP_Pretime;
                        if(distance < time_space){
                            android.util.Log.i("zukgit", "Pause_Music_VideoEvent A setOnTouchListener  motionEvent ="+ motionEvent.getAction() + "  Pretime"+ Action_UP_Pretime +  "   distance = "+ distance);

                            Action_UP_Pretime = System.currentTimeMillis();
                            if(mediaControl !=null ){
                                if(mediaControl.getVisibility() != View.VISIBLE){
                                    mediaControl.setVisibility(View.VISIBLE);
                                    android.util.Log.i("zukgit", "Pause_Music_VideoEvent B setOnTouchListener  motionEvent ="+ motionEvent.getAction() + "mediaControl.setVisibility(View.VISIBLE)  Pretime"+ Action_UP_Pretime +  "   distance = "+ distance);

                                }

                            }
                        }else{
                            Action_UP_Pretime = System.currentTimeMillis();
                        }
                        android.util.Log.i("zukgit", "Pause_Music_VideoEvent C setOnTouchListener  motionEvent ="+ motionEvent.getAction() + " Pretime="+ Action_UP_Pretime +  "   distance = "+ distance);

                        ///how to grab the second action_down????
                        break;
                }
                return false;
            }
        });*/


//        videoView.requestFocus();

//        android.util.Log.i("zukgit","jiemiFile.exist() = "+ jiemiFile.exists());

        android.util.Log.i("zukgit", "Pause_Music_VideoEvent X11 curPlayPos = " + curPlayPos + "   jiemiFile " + jiemiFile.getAbsolutePath());


//        android.util.Log.i("zukgit","curPlayPos = "+ curPlayPos + " bgVideoPath = "+ bgVideoPath + " tempFile = "+ tempFile.getAbsolutePath() + "  exist = "+ tempFile.exists() + " uri_item.getPath() =" + uri_item.getPath());


//        videoView.start();


/*        videoView.setOnPreparedListener(mp -> {
            //     mp.setLooping(true);  // 循环
            mp.setLooping(false);

            //延迟取消封面，避免加载视频黑屏
            new CountDownTimer(200, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
//                    ivCover.setVisibility(View.GONE);
//                    ivCurCover = ivCover;
                }
            }.start();
        });*/
        android.util.Log.i("zukgit", "autoPlayVideo setOnPreparedListener  curPlayPos = " + curPlayPos);

/*        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                DataHolder.MP3_initPos++;
                curPlayPos = DataHolder.MP3_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                android.util.Log.i("zukgit", "X2  Stock_Area_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos);

                videoView.stopPlayback();
//                autoPlayVideo(curPlayPos, ivCover);
                recyclerView.scrollToPosition(DataHolder.MP3_initPos);
//                autoPlayVideo(DataHolder.Video_initPos);
            }
        });*/
        android.util.Log.i("zukgit", "autoPlayVideo END  curPlayPos = " + curPlayPos);
    }

    /**
     * 用户操作事件
     */
/*
    private void likeShareEvent(ControllerView controllerView) {
        controllerView.setListener(new OnVideoControllerListener() {
            @Override
            public void onHeadClick() {
                RxBus.getDefault().post(new MainPageChangeEvent(1));
            }

            @Override
            public void onLikeClick() {

            }

            @Override
            public void onCommentClick() {
                CommentDiaandroid.util.Log commentDiaandroid.util.Log = new CommentDiaandroid.util.Log();
                commentDiaandroid.util.Log.show(getChildFragmentManager(), "");
            }

            @Override
            public void onShareClick() {
                new ShareDiaandroid.util.Log().show(getChildFragmentManager(), "");
            }
        });
    }
*/

}
