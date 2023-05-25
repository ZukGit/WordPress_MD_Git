package com.and.zmain_life.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.and.zmain_life.bean.Pause_StockCommon_Event;

import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.stock_node.Stock_MultiLevelPickerWindow;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import rx.functions.Action1;

/*import com.and.zvideo_and_dy.view.CommentDiaandroid.util.Log;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDiaandroid.util.Log;*/


public class Stock_Common_Fragment extends BaseFragment {
    String TAG = "Stock_Common_Fragment";



    Stock_MultiLevelPickerWindow<Stock_NodeImpl> window;






/*    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE||newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                DataHolder.System_IS_LAND = true;

            }else{
                DataHolder.System_IS_LAND = false;
            }

        }
    }*/

    public File curRecordJson_File ;  // 记录当前正在现实的 daily_json  文件

    @BindView(R.id.stock_market_title)
    TextView stock_market_title;    // stock_market_title

    String stock_market_title_originStr;  // 标题最初的显示文字


    @BindView(R.id.stock_layout)
    LinearLayout stockLayout;


    private ViewPagerLayoutManager viewPagerLayoutManager;

    /**
     * 当前播放视频位置
     */
    private int curPlayPos = -1;

    /*    private FullScreenVideoView videoView;*/

/*    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;*/


    Context mContext;



    File Sdcard_File;
    File ZMain_File;



//    static Stock_NodeImpl  Stock_NodeImpl_Root;


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

        if(stockLayout != null){

            DataHolder.MP3_Layout_color_Random = Color.parseColor(getRandomColorString());
            stockLayout.setBackgroundColor(DataHolder.MP3_Layout_color_Random);


            stockLayout.requestLayout();
        }
        android.util.Log.i("zukgit","background_color_changed  #xxxx");
    }








    @Override
    protected int setLayoutId() {
        return R.layout.stock_node_main;
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

        String order_tip = StockHolder.Stock_NodeImpl_ComparatorTip_List.get(StockHolder.Stock_NodeImpl_Comparator_Selected_Index).replace(".","");

        if(StockHolder.day_lastxlsx_timestamp != null && stock_market_title != null){
//            stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
            stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-"+order_tip);
        }
        stock_market_title.setOnClickListener(this::title_click);  // 设置 title的 点击时间
        stock_market_title.setOnLongClickListener(this::titleLongClickMethod);

/*        stock_market_title.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {


                if(window != null){
                    window.update();
                }
                Toast.makeText(mContext,"Stock_Common-Long-CLick",Toast.LENGTH_SHORT).show();;

                return false;
            }
        });*/
//        color_ID_Random = ZUtil.getRainbowColor_random();
//        randomColor =  Color.parseColor(getRandomColor())
//        color_ID_Random = randomColor.toArgb();  // 随机


//        stockLayout.setBackgroundColor(color_ID_Random);

        stockLayout.requestLayout();


        RxBus.getDefault().toObservable(Pause_StockCommon_Event.class).subscribe((Action1<Pause_StockCommon_Event>) event -> {



            if (event.isPlayOrPause()) {  // 离开 Stock 页面
                android.util.Log.i("zukgit", "Pause_StockCommon_Event=  event.isPlayOrPause() ="+event.isPlayOrPause());
                StockHolder.isStockPage_ChangeTo_AnotherPage=false;

                if(stock_market_title != null && StockHolder.isShowWindows_For_CommonFragment_When_Dismiss){
                    resume_call_method(stock_market_title);
                    StockHolder.isShowWindows_For_CommonFragment_When_Dismiss = false;
                    android.util.Log.i("zukgit", "Pause_Stock_Event_1  isShowWindows_When_Dismiss = false");
                }

                if(stock_market_title != null){

                    String titleText = stock_market_title.getText().toString();
                    if(titleText.contains("请稍等")){
                        stock_market_title.setText(stock_market_title_originStr);
                    }

                }


            } else {
                android.util.Log.i("zukgit", "Pause_StockCommon_Event=  event.isPlayOrPause() ="+event.isPlayOrPause());

                StockHolder.isStockPage_ChangeTo_AnotherPage=true;

                if(window != null){


                    if(window.isShowing()){
                        StockHolder.isShowWindows_For_CommonFragment_When_Dismiss =  window.isShowing();
                        android.util.Log.i("zukgit", "Pause_Stock_Event_2  isShowWindows_When_Dismiss = "+ StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
                    }


                    android.util.Log.i("zukgit", "获取A isShowWindows_When_Dismiss = " + StockHolder.isShowWindows_For_CommonFragment_When_Dismiss + "   ");

                    window.dismiss();

                }

            }

        });


        RxBus.getDefault().toObservable(APP_In_BackGround_Event.class).subscribe((Action1<APP_In_BackGround_Event>) event -> {
            if (event.isPlayOrPause()) {  // true  后台
                android.util.Log.i("zukgit", "APP_In_BackGround_Event=后台事件 ZXXZ");


                if(window != null){
                    StockHolder.isShowWindows_For_CommonFragment_When_Dismiss =      window.isShowing();
                    android.util.Log.i("zukgit", "APP_In_BackGround_Event_1  isShowWindows_When_Dismiss ="+ StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
                }

            }else{   // 前台


                if(window != null && stock_market_title != null){
                    if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
                        window.show(stock_market_title, StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
                        StockHolder.isShowWindows_For_CommonFragment_When_Dismiss =      true;
                        android.util.Log.i("zukgit", "APP_In_BackGround_Event_2 CommonFragment   curPageIndex="+curPageIndex+"   isShowWindows_When_Dismiss ="+ StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);


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







    private Dialog CommonSimulat_Title_Dialog;
    // 标题长按  事件 处理 打开 一个  Dialog  用来选择  显示排列次序  价格↓ 价格↑  涨幅↓ 涨幅↑  3涨幅↓  3涨幅↓  15涨幅↓  15涨幅↓
    private boolean titleLongClickMethod (View view) {


        final AlertDialog.Builder jsonSelectedDiaglog = new AlertDialog.Builder(getContext());

        // 排序方法的种类
        int sort_list_length =   StockHolder.Stock_NodeImpl_ComparatorTip_List.size();

        jsonSelectedDiaglog.setTitle("排序方式选择框【"+sort_list_length+"】:");//文字



        jsonSelectedDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        jsonSelectedDiaglog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });



        ArrayList<String> spinnerTipArr = new ArrayList<String>();
        spinnerTipArr.clear();

        for (int i = 0; i < StockHolder.Stock_NodeImpl_ComparatorTip_List.size(); i++) {
            String  order_tip_str =  StockHolder.Stock_NodeImpl_ComparatorTip_List.get(i);
            spinnerTipArr.add(order_tip_str);
        }


        final  CharSequence[] items = new  CharSequence[spinnerTipArr.size()];
        int matchedIndex = -1;
        for (int i = 0; i < spinnerTipArr.size(); i++) {
            int index_num = i+1;
            String little_index_str = DataHolder.calculLittleDigital(index_num+"");
            String categoryKey = spinnerTipArr.get(i);

//          String curLastJsonFileIntStr =   StockHolder.day_last_jsonFile.getName().replace(".json","").replace("day","").replace("_","").trim();

            if(i == StockHolder.Stock_NodeImpl_Comparator_Selected_Index  ){
                matchedIndex = i ;
                print("Common_Stock_Fragment  selected_index=["+i+"] = "+spinnerTipArr.get(i)  );
            }
            items[i] = little_index_str+spinnerTipArr.get(i)+"";

        }



        jsonSelectedDiaglog.setSingleChoiceItems(items,matchedIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"  index="+which);



                // 更新 json 数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // 依据  新的 排序规格更新列表
                        StockHolder.Stock_NodeImpl_Comparator_With_Type(which);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(window != null){

                                    window.dismiss();
                                    window.removeSelectListener();

                                    StockHolder.Stock_NodeImpl_Root =StockHolder.generate_gupiaoliebiao_with_OrderIndex(mContext,which);

                                    StockHolder.AttentionRank_Level1_Stock_NodeImpl  = StockHolder.getAttentionStock_From_RootStock(StockHolder.Stock_NodeImpl_Root);
                                            // 这里拿到新的

//                                    StockHolder.initAreaFragmentData();  // 重新更新  area_stock 数据

                                    // 重新赋值
                                    window = new Stock_MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
                                    window.click_level_1(StockHolder.Stock_NodeImpl_Root);

                                    long[] storage = new long[]{-1L/* 全部 */, -1L, -1L};
                                    window.updateData(StockHolder.Stock_NodeImpl_Root);
                                    window.show(stock_market_title);

                                }
                            }
                        });

                    }
                }).start();


                String order_tip = StockHolder.Stock_NodeImpl_ComparatorTip_List.get(which).replace(".","");



                if(stock_market_title != null){
                    stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-"+order_tip);
                }

//                Toast.makeText(getContext(), "用户选中的 Order方式 索引:"+which+"  Tip:"+StockHolder.Stock_NodeImpl_ComparatorTip_List.get(which)  , Toast.LENGTH_SHORT).show();

                StockHolder.Stock_NodeImpl_Comparator_Selected_Index =   which ;


                // 依据 这个  ArrayList<File> 重新生成数据
                if(CommonSimulat_Title_Dialog != null){
                    CommonSimulat_Title_Dialog.dismiss();
                }

            }
        });


        CommonSimulat_Title_Dialog =   jsonSelectedDiaglog.show();



        return false;

    }


    private void print(String log) {
        android.util.Log.i("Stock_Common_Fragment",log);

    }





        private void title_click(View v) {
        android.util.Log.i("xxzukgit"," 文字点击");
        if (window == null) {
            window = new Stock_MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit","AA_1 文字点击");

        }
        window.setOnSelectListener(new Stock_MultiLevelPickerWindow.OnSelectListener<Stock_NodeImpl>() {

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
//                    stock_market_title.setText("");
                    if(StockHolder.day_lastxlsx_timestamp != null && stock_market_title != null){
//                        stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
                    }

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
        // 1. StockHolder.day_last_jsonFile 和  StockHolder.day_alway_last_json_File 相等的情况
        // 2.  StockHolder.day_last_jsonFile 和  StockHolder.day_alway_last_json_File 不相等的情况
        // 3.  再次选择相同的一个  比如
        synchronized (this){
            if(StockHolder.Stock_NodeImpl_Root != null
                    && StockHolder.day_last_jsonFile != null
                    && curRecordJson_File == StockHolder.day_last_jsonFile ){
                android.util.Log.i("Zcommon_stock","zukgit Zshow_click_1  updateData ");
                curRecordJson_File = StockHolder.day_last_jsonFile;
//                window.updateData(StockHolder.Stock_NodeImpl_Root);    // 当用户没有选中json 文件     刷新 收藏榜就要注释掉     更新排序 又要不能注释掉
//                window.update();
            }else if(curRecordJson_File != null && curRecordJson_File != StockHolder.day_last_jsonFile){
                android.util.Log.i("Zcommon_stock","zukgit Zshow_click_2  updateData ");
                curRecordJson_File = StockHolder.day_last_jsonFile;
                StockHolder.Stock_NodeImpl_Root =StockHolder.generate_gupiaoliebiao_withZMainStockDir(mContext);
                window.updateData(StockHolder.Stock_NodeImpl_Root);
//                window.update();
            } else {
                android.util.Log.i("Zcommon_stock","zukgit Zshow_click_3  updateData ");

                curRecordJson_File =  StockHolder.day_last_jsonFile;
                StockHolder.Stock_NodeImpl_Root =StockHolder.generate_gupiaoliebiao_withZMainStockDir(mContext);
                window.updateData(StockHolder.Stock_NodeImpl_Root);
//                window.update();
            }

            if(StockHolder.Stock_NodeImpl_Root == null ||  StockHolder.GuPiaoLieBiao_NodeJson_Str == null){
                if(stock_market_title != null){

                    String titleText = stock_market_title.getText().toString();
                    stock_market_title_originStr = titleText;

                    stock_market_title.setText("请稍等,初始化数据中......");
                }
            }

        }
        StockHolder.isShowWindows_For_CommonFragment_When_Dismiss = !StockHolder.isShowWindows_For_CommonFragment_When_Dismiss;

        window.show(stock_market_title);



    }


    private void resume_call_method(View v) {

        if (window == null) {
            window = new Stock_MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit","AA_1 文字点击");

        }
        window.setOnSelectListener(new Stock_MultiLevelPickerWindow.OnSelectListener<Stock_NodeImpl>() {

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
//                    stock_market_title.setText("");
                    if(StockHolder.day_lastxlsx_timestamp != null && stock_market_title != null){
//                        stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
                    }
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

                if(StockHolder.day_lastxlsx_timestamp != null && stock_market_title != null){
//                    stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
                }

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
            if(StockHolder.Stock_NodeImpl_Root != null
                    && StockHolder.day_last_jsonFile != null
                    && StockHolder.GuPiaoLieBiao_NodeJson_Str != null ){
                android.util.Log.i("Zcommon_stock","Zshow_1  updateData curRecordJson_File="+curRecordJson_File+"  StockHolder.day_last_jsonFile="+StockHolder.day_last_jsonFile);

                curRecordJson_File = StockHolder.day_last_jsonFile;
//                window.updateData(StockHolder.Stock_NodeImpl_Root);
            }else{

                android.util.Log.i("Zcommon_stock","Zshow_2  updateData curRecordJson_File="+curRecordJson_File+"  StockHolder.day_last_jsonFile="+StockHolder.day_last_jsonFile);

                curRecordJson_File = StockHolder.day_last_jsonFile;
                StockHolder.Stock_NodeImpl_Root =StockHolder.generate_gupiaoliebiao_withZMainStockDir(mContext);
                window.updateData(StockHolder.Stock_NodeImpl_Root);
            }

        }

        if(StockHolder.Stock_NodeImpl_Root == null || StockHolder.GuPiaoLieBiao_NodeJson_Str == null){
            if(stock_market_title != null){

                String titleText = stock_market_title.getText().toString();
                stock_market_title_originStr = titleText;

                stock_market_title.setText("请稍等,初始化数据中......");
            }
        }

        window.show(stock_market_title, StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);



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






    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "════════════════════════════ Stock_Common_Fragment_______onResume Begin════════════════════════════");
        android.util.Log.i("zukgit", "【Stock_Common_Fragment__onResume()】____起点1 ");

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
        android.util.Log.i("zukgit", "Stock_Common_Fragment  onResume() 是否全屏变化  MainActivity.curMainPage="+MainActivity.curMainPage);
        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onResume  CA ");
//            videoView.start();
            android.util.Log.i("zukgit", "【Stock_Common_Fragment__onResume()】____起点3 ");

            android.util.Log.i("zukgit", "【Stock_Common_Fragment__onResume()】____起点6   stock_market_title != null【"+(stock_market_title != null)+"】  isShowWindows_When_Dismiss="+ StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);

            android.util.Log.i("zukgit", "恢复A onResume()  = " + "  MainFragment.curPage=");



            if(StockHolder.day_last_jsonFile != null && StockHolder.day_lastjson_timestamp != null && stock_market_title != null){
//                stock_market_title.setText(StockHolder.day_lastjson_timestamp+"-Stock-Info");
            } else {

                if(stock_market_title_originStr != null ){
                    stock_market_title.setText(stock_market_title_originStr);
                }
            }




            if(stock_market_title != null && StockHolder.isShowWindows_For_CommonFragment_When_Dismiss){
                resume_call_method(stock_market_title);

//                window.showLastNode3Impl();   // 显示正确的 那个
                StockHolder.isShowWindows_For_CommonFragment_When_Dismiss = true;
                android.util.Log.i("zukgit", "【Stock_Common_Fragment__onResume()】____起点5  isShowWindows_When_Dismiss="+ StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
            }




        }



        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onResume  CB  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onResume  D  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);


        android.util.Log.i("zukgit", "════════════════════════════ Stock_Common_Fragment_______onResume End════════════════════════════");


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
        android.util.Log.i("zukgit", "Stock_Common_Fragment  onConfigurationChanged() 是否全屏变化 ");
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE||newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                DataHolder.System_IS_LAND = true;
                if(window != null){
                    window.update();
                }


            }else{
                DataHolder.System_IS_LAND = false;
                if(window != null){
                    window.update();
                }

            }

        }

        if(window != null &&  stock_market_title != null && window.isShowing()){
            window.dismiss();
            StockHolder.isShowWindows_For_CommonFragment_When_Dismiss = true;
            window.show(stock_market_title, StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        android.util.Log.i("zukgit", "Stock_Common_Fragment  onSaveInstanceState() 是否全屏变化 ");


        super.onSaveInstanceState(outState);
    }



    @Override
    public void onPause() {
        android.util.Log.i("zukgit", "Stock_Common_Fragment  onPause() 是否全屏变化 ");
        super.onPause();
        if(window != null){



            if(window.isShowing()){
                StockHolder.isShowWindows_For_CommonFragment_When_Dismiss =  window.isShowing();
                android.util.Log.i("zukgit", "获取A isShowWindows_When_Dismiss = " + StockHolder.isShowWindows_For_CommonFragment_When_Dismiss);
            }



//            window.dismiss();
        }

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Stock_Common_Fragment.onPause  ");
//        videoView.pause();
    }

    @Override
    public void onStop() {
        android.util.Log.i("zukgit", "Stock_Common_Fragment  onPause() 是否全屏变化 ");
        super.onStop();

    }


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
                android.util.Log.i("zukgit", "X1 Stock_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " isBottom = " + isBottom);
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
                android.util.Log.i("zukgit", "X2  Stock_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " user_backup_num = " + user_backup_num);

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
                android.util.Log.i("zukgit", "X2  Stock_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos);

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
