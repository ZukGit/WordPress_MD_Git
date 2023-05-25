package com.and.zmain_life.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
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
import com.and.zmain_life.bean.Pause_MP3_Event;
import com.and.zmain_life.bean.ScreenOn_Off_Event;
import com.and.zmain_life.node.MultiLevelPickerWindow;
import com.and.zmain_life.node.NodeImpl;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import rx.functions.Action1;

/*import com.and.zvideo_and_dy.view.CommentDiaandroid.util.Log;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDiaandroid.util.Log;*/


public class MP3_Common_Fragment extends BaseFragment {
    String TAG = "MP3_Common_Fragment";
    public SensorManager sensorManager;
    public Vibrator vibrator;

    public static final int SENSOR_SHAKE=10;
    public static long lastVibrator_TimeStamp = -1 ;


    MultiLevelPickerWindow<NodeImpl> window;


    // 当前显示的 mPart1 部门  由于 可能 用户 点击了 第一部分 但是歌曲还是 原来的那部分 所以
    // 与 mPart1_Alphabet  存在 不同的 情形
    String current_mPart1_Alphabet;


    // 与 mPart2_Artist  存在 不同的 情形
    String current_mPart2_Artist;


    String mPart1_Alphabet;  // 字母
    String mPart2_Artist;  // 作者
    String mPart3_SongName;  // 歌曲
    String mPartTitle;  // 显示的 标题名称
    String mPart2_Artist_longClick; // 长按作者的名称




    Color randomColor;

    // 关闭窗口切换fragment时  windows是否是显示的   如果是显示的 切换回时则显示
    boolean isShowWindows_When_Dismiss = false;

    // 关闭窗口切换fragment时   歌曲是否正在 播放   如果播放 下次切换回来时播放 否则不播放
    boolean isPlayMusic_When_Dissmiss = false;

    @BindView(R.id.tv)
    TextView tvText;

    @BindView(R.id.mp3_layout)
    LinearLayout mp3Layout;
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
        if(tvText != null){
//            String colorStr = getRandomColor().toUpperCase();
//            android.util.Log.i("zukgit","setTitleColor_Green  colorStr = "+ colorStr);
            tvText.setTextColor(Color.parseColor("#FFFFFF"));
            DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse = true;
        }

    }

    public void setTitleColor_Black(){
        if(tvText != null){
            tvText.setTextColor(Color.parseColor("#000000"));
            DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse = false;

        }
        android.util.Log.i("zukgit","setTitleColor_Black  #000000");
    }

    // @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public void background_color_changed(){
//        DataHolder.MP3_Layout_color_Random = ZUtil.getRainbowColor_random();

//        color_ID_Random = randomColor.toArgb();  // 随机
        if(mp3Layout != null){
//            mp3Layout.setBackgroundColor(getResources().getColor(color_ID_Random));
//            color_ID_Random = Color.parseColor(getRandomColorString());
            DataHolder.MP3_Layout_color_Random = Color.parseColor(getRandomColorString());
            mp3Layout.setBackgroundColor(DataHolder.MP3_Layout_color_Random);
//        mp3Layout.setBackgroundColor(color_ID_Random);

            mp3Layout.requestLayout();
        }
        android.util.Log.i("zukgit","background_color_changed  #xxxx");
    }






    @Override
    protected int setLayoutId() {
        return R.layout.mlp_mp3_node_main;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.mlp_mp3_node_main);
//        tvText = findViewById(R.id.tv);
//        tvText.setOnClickListener(this::show);
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
        mediaControl = new MediaController(getContext());
        sensorManager =  (SensorManager) getContext().getSystemService(getContext().SENSOR_SERVICE);
        vibrator= (Vibrator) getContext().getSystemService(getContext().VIBRATOR_SERVICE);
        tvText.setOnClickListener(this::show);
        tvText.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {

                DataHolder.mTitle_Long_Click_Num++;
                if(DataHolder.mTitle_Long_Click_Num%3 == 0){
                    DataHolder.isShowLevel3Node_Artist  = false;
                    DataHolder.isShowLevel3Node_Position = false;
                }else if(DataHolder.mTitle_Long_Click_Num%3 == 1){
                    DataHolder.isShowLevel3Node_Position = true;
                    DataHolder.isShowLevel3Node_Artist  = false;
                }else if(DataHolder.mTitle_Long_Click_Num%3 == 2){
                    DataHolder.isShowLevel3Node_Position = false;
                    DataHolder.isShowLevel3Node_Artist  = true;
                }

                window.update();
                return false;
            }
        });
//        color_ID_Random = ZUtil.getRainbowColor_random();
//        randomColor =  Color.parseColor(getRandomColor())
//        color_ID_Random = randomColor.toArgb();  // 随机
        if(DataHolder.MP3_Layout_color_Random == R.color.rainbow_blue){
            DataHolder.MP3_Layout_color_Random =Color.parseColor(getRandomColorString());
            mp3Layout.setBackgroundColor(DataHolder.MP3_Layout_color_Random);
        }else {
            mp3Layout.setBackgroundColor(DataHolder.MP3_Layout_color_Random);
        }

//        mp3Layout.setBackgroundColor(color_ID_Random);

        mp3Layout.requestLayout();

        android.util.Log.i("zukgit", "MP3_Common_Fragment  init() 是否全屏变化 颜色 MP3_Layout_color_Random="+DataHolder.MP3_Layout_color_Random);

        RxBus.getDefault().toObservable(ScreenOn_Off_Event.class).subscribe((Action1<ScreenOn_Off_Event>) event -> {
/*                    if (event.isPlayOrPause()) {  // true  灭屏
                        android.util.Log.i("zukgit", "ScreenOn_Off_Event=灭屏事件 ");
                        window.playScreenOffMusic();
                    }else{   // 亮屏
                     window.pauseScreenOffMusic();

                        android.util.Log.i("zukgit", "ScreenOn_Off_Event=亮屏事件 ");
                    }*/

             if (event.isPlayOrPause()) {  // true  灭屏
                        android.util.Log.i("zukgit", "ScreenOn_Off_Event=灭屏事件 ");
                    }else{   // 亮屏
                        android.util.Log.i("zukgit", "ScreenOn_Off_Event=亮屏事件 ");
                    }

        });

        RxBus.getDefault().toObservable(APP_In_BackGround_Event.class).subscribe((Action1<APP_In_BackGround_Event>) event -> {
            if (event.isPlayOrPause()) {  // true  后台
                android.util.Log.i("zukgit", "APP_In_BackGround_Event=后台事件 ");

//                window.playScreenOffMusic();   // 后台
if(window != null){
    window.registerScreenOff_OnCompleteListener();
    if(window.isPalyingMusic()){
        isPlayMusic_When_Dissmiss = window.isPalyingMusic();
    }
}


            }else{   // 前台


                if(window != null){



if(isPlayMusic_When_Dissmiss){
    window.Refresh_Level3_Adapter();
    window.unRegisterScreenOff_OnCompleteListener();
    window.recoveryMusic_Widows();

    window.update();

}

                    if( getActivity() != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                        android.util.Log.i("zukgit", " hideSoftInputFromWindow CC ");
                    }


                }





                android.util.Log.i("zukgit", "APP_In_BackGround_Event=前台事件 ");
            }


            if( getActivity() != null){
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                android.util.Log.i("zukgit", " hideSoftInputFromWindow MP3CommonFragment BA ");

            }

        });

        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_MP3_Event.class)
                .subscribe((Action1<Pause_MP3_Event>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.init B   ");
                        playCurVideo(DataHolder.MP3_initPos);
//                        videoView.start();
                        if(window != null && isPlayMusic_When_Dissmiss && DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse){
                            window.recoveryMusic_Widows_ChangePage();
                            isPlayMusic_When_Dissmiss = false;
                        }
                        if(tvText != null && isShowWindows_When_Dismiss){
                            show(tvText);
                            isShowWindows_When_Dismiss = false;
                        }
                        android.util.Log.i("zukgit", "决定 isShowWindows_When_Dismiss = " + isShowWindows_When_Dismiss + "   isPlayMusic_When_Dissmiss="+isPlayMusic_When_Dissmiss);

                        mediaControl.setVisibility(View.INVISIBLE);
//                        mediaControl.setVisibility(View.VISIBLE);
//                        mediaControl.hide();

                        DataHolder.isMP3Page_ChangeTo_AnotherPage=false;
                    } else {
                        DataHolder.isMP3Page_ChangeTo_AnotherPage=true;
                        if(window != null){


                            if(window.isShowing()){
                                isShowWindows_When_Dismiss =  window.isShowing();

                            }
                            if(window.isPalyingMusic()){
                                isPlayMusic_When_Dissmiss = window.isPalyingMusic() && DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse ;
                            }

                            android.util.Log.i("zukgit", "获取A isShowWindows_When_Dismiss = " + isShowWindows_When_Dismiss + "   isPlayMusic_When_Dissmiss="+isPlayMusic_When_Dissmiss);

                            window.dismiss();
                            window.pauseMusic_Widows();
                        }

                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.init C   ");
//                        videoView.pause();
                        if(mediaControl != null){
                            mediaControl.hide();
                        }

                    }

                });


        if(DataHolder.mp3Json == null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DataHolder.getZmainMp3Json();
                }
            }).start();

        }


    }


/*
    @Override
    protected void init() {

        adapter = new VideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);

        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();
        mContext = getContext();
        mediaControl = new MediaController(getContext());

        tvText.setOnClickListener(this::show);

        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_MP4_Music_VideoEvent.class)
                .subscribe((Action1<Pause_MP4_Music_VideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.init B   ");
                        playCurVideo(DataHolder.MP3_initPos);
//                        videoView.start();
                        mediaControl.setVisibility(View.INVISIBLE);
//                        mediaControl.setVisibility(View.VISIBLE);
//                        mediaControl.hide();

                    } else {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.init C   ");
                        videoView.pause();
                        if(mediaControl != null){
                            mediaControl.hide();
                        }

                    }
                });

    }*/


        private void TitleLongClick(View v) {
            android.util.Log.i("xxzukgdait", " 文字点击");
            if (window == null) {
                window = new MultiLevelPickerWindow<>(mContext, DataHolder.MP3_Layout_color_Random);
                android.util.Log.i("xxzukgit", "AA_1 文字点击");

            }
        }


    private void show(View v) {
        android.util.Log.i("xxzukgit"," 文字点击");
        if (window == null) {
            window = new MultiLevelPickerWindow<>(mContext,DataHolder.MP3_Layout_color_Random);
            android.util.Log.i("xxzukgit","AA_1 文字点击");

        }
        window.setOnSelectListener(new MultiLevelPickerWindow.OnSelectListener<NodeImpl>() {

            @Override
            public void onMusicPlay(boolean isMusicPlay) {
                if(isMusicPlay){
                    setTitleColor_Green();
                }else{
                    setTitleColor_Black();
                }

            }


            @Override
            public void onSelect(int level, NodeImpl nodeImpl, boolean isOffMediaPlayer) {
                android.util.Log.i("dadadxxzukgit","BB_1 文字点击");
                if (nodeImpl == null) {
                    tvText.setText("");
                    return;
                }
                if(nodeImpl.level == 1){
                    mPart1_Alphabet = nodeImpl.name;
                }

                if(nodeImpl.level == 2){
                    mPart2_Artist = nodeImpl.name;
                }

                if(nodeImpl.level == 3){
                    mPart3_SongName = nodeImpl.name;

                    if(isOffMediaPlayer){  // 后台播放的情况
                        mPartTitle = current_mPart1_Alphabet+"¹"+current_mPart2_Artist+"²"+mPart3_SongName+"³";

                    }else{
                        mPartTitle = mPart1_Alphabet+"¹"+mPart2_Artist+"²"+mPart3_SongName+"³";
                        current_mPart1_Alphabet = mPart1_Alphabet;
                        current_mPart2_Artist  = mPart2_Artist;
                    }
                    tvText.setText(mPartTitle);
                }


            }

            @Override
            public void onBackgroundColorChanged() {
                background_color_changed();
            }

            @Override
            public void onDownGraded(int selectLevel, NodeImpl nodeImpl) {
                if (selectLevel <= 0) {
                    Toast.makeText(mContext.getApplicationContext(), "降级到: " + selectLevel, Toast.LENGTH_SHORT).show();
                    android.util.Log.i("xxzukgit","CC_1 ");
                    return;
                }
                Toast.makeText(mContext.getApplicationContext(), String.format(Locale.ENGLISH, "降级到%s级: %s", selectLevel, nodeImpl.toString()), Toast.LENGTH_SHORT).show();
                android.util.Log.d("onDownGraded",
                        String.format(
                                Locale.ENGLISH,
                                "降级到%d级: %s",
                                selectLevel,
                                nodeImpl.toString()
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
            if(NodeImpl_Root != null){

                window.updateData(NodeImpl_Root);
            }else{
                NodeImpl_Root =generate_withZMainMp3File(mContext);
                window.updateData(NodeImpl_Root);
            }

        }

        window.show(tvText);
    }

    private static boolean isFirst = true;

    static NodeImpl  NodeImpl_Root;

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

    private static void init_mp3_node_list(NodeImpl rootNode){

        if(   DataHolder.mArtist_NodeImpl_Map == null ){
            DataHolder.mArtist_NodeImpl_Map = new LinkedHashMap<String, ArrayList<NodeImpl>>();

        }

        if(   DataHolder.mUserDefine_Category_Level2_NodeImpl_Map == null ){
            DataHolder.mUserDefine_Category_Level2_NodeImpl_Map = new LinkedHashMap<String, ArrayList<NodeImpl>>();

        }




        if (DataHolder.level_1_alphabet_nodeList.size() == 0   && DataHolder.level_2_artistism_nodeList.size() == 0  && DataHolder.level_3_song_nodeList_quchong.size() == 0  ) {

            for (int i = 0; i < rootNode.children().size(); i++) {
                NodeImpl level1 = rootNode.children.get(i);
                DataHolder.level_1_alphabet_nodeList.add(level1);

                for (int j = 0; j < level1.children.size(); j++) {
                    NodeImpl level2 = level1.children.get(j);
                    DataHolder.level_2_artistism_nodeList.add(level2);

                    if(level2.artist != null  && !DataHolder.MP3_Node2_UserDefine_NameList.contains(level2.name) && !level2.name.startsWith("A_All")){

                        ArrayList<NodeImpl> level3_childlist= new  ArrayList<NodeImpl>();
                        level3_childlist.addAll(level2.children);
                        DataHolder.mArtist_NodeImpl_Map.put(level2.name,level3_childlist);
                    }else{
                        ArrayList<NodeImpl> level3_childlist= new  ArrayList<NodeImpl>();
                        level3_childlist.addAll(level2.children);
                        DataHolder.mUserDefine_Category_Level2_NodeImpl_Map.put(level2.name,level3_childlist);
                    }

                    for (int k = 0; k < level2.children.size() ; k++) {
                        NodeImpl level3 = level2.children.get(k);
                        DataHolder.level_3_song_nodeList.add(level3);
                        DataHolder.level_3_song_nodeList_quchong.add(level3);

                    }
                }

            }

        }




    }
    private static synchronized  NodeImpl generate_withZMainMp3File(Context context) {

        try {


            String json_result = DataHolder.getZmainMp3Json();
            NodeImpl tree = new Gson().fromJson(json_result, NodeImpl.class);
            init_mp3_node_list(tree);
            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
            android.util.Log.i("json_result",json_result);
           //    LogPrint(json_result,"json_result");
            android.util.Log.i("json_result","ZZZZZZZZZZZZZZZZZZZZZ");
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
        return NodeImpl.EMPTY;
    }


    private static NodeImpl generate_withJsonFile(Context context) {
        android.util.Log.i("xxzukgit","EE_1 ");
        try {
            InputStream is = context.getAssets().open("tree.json");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            NodeImpl tree = new Gson().fromJson(result.toString(), NodeImpl.class);
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
        return NodeImpl.EMPTY;
    }



/*    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private VideoAdapter adapter;
    */

    private ViewPagerLayoutManager viewPagerLayoutManager;

    /**
     * 当前播放视频位置
     */
    private int curPlayPos = -1;

/*    private FullScreenVideoView videoView;*/

/*    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;*/

    private ImageView ivCurCover;

    Context mContext;

    MediaController  mediaControl;

    File Sdcard_File;
    File ZMain_File;



    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "════════════════════════════ MP3_Common_Fragment_______onResume Begin════════════════════════════");
        android.util.Log.i("zukgit", "【MP3_Common_Fragment__onResume()】____起点1 ");

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
        android.util.Log.i("zukgit", "MP3_Common_Fragment  onResume() 是否全屏变化 ");
        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onResume  CA ");
//            videoView.start();
            android.util.Log.i("zukgit", "【MP3_Common_Fragment__onResume()】____起点3 ");

            if(window != null && isPlayMusic_When_Dissmiss){
                window.recoveryMusic_Widows();
                isPlayMusic_When_Dissmiss = false;
                android.util.Log.i("zukgit", "【MP3_Common_Fragment__onResume()】____起点4 ");

            }
            if(tvText != null && isShowWindows_When_Dismiss){
                show(tvText);
                android.util.Log.i("zukgit", "【MP3_Common_Fragment__onResume()】____起点5 ");
                 window.showLastNode3Impl();   // 显示正确的 那个
                isShowWindows_When_Dismiss = false;
            }
            android.util.Log.i("zukgit", "【MP3_Common_Fragment__onResume()】____起点6 ");

            android.util.Log.i("zukgit", "恢复A onResume() isShowWindows_When_Dismiss = " + isShowWindows_When_Dismiss + "   isPlayMusic_When_Dissmiss="+isPlayMusic_When_Dissmiss +"  MainFragment.curPage=");

            if(sensorManager!=null){//注册监听器
                sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
            }
            android.util.Log.i("zukgit", " hideSoftInputFromWindow A" );

            if( getActivity() != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                android.util.Log.i("zukgit", " hideSoftInputFromWindow B ");
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


            }

        }






        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onResume  CB  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onResume  D  MainActivity.curMainPage="+MainActivity.curMainPage+"    MainFragment.curPage="+MainFragment.curPage);

        if(mPartTitle != null && tvText != null){
            tvText.setText(mPartTitle);
        }

        android.util.Log.i("zukgit", "════════════════════════════ MP3_Common_Fragment_______onResume End════════════════════════════");


        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        android.util.Log.i("zukgit", " hideSoftInputFromWindow BA ");

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    /**
     * 重力感应监听
     * */
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器信息改变时执行该方法
            float[] values=event.values;
            float x=values[0];    // x轴方向的重力加速度，向右为正
            float y=values[1];    // y轴方向的重力加速度，向前为正
            float z=values[2];    // z轴方向的重力加速度，向上为正
//            android.util.Log.i(TAG,"x轴方向的重力加速度"+x+";y轴方向的重力加速度"+y+";z轴方向重力加速度"+z);
            //一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态
            int medumValue =15;//如果不铭感请自行调低该数值，低于10的话就不行了，因为Z轴上的加速度本身就达到了10
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {

                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    /**动作执行*/
    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case SENSOR_SHAKE:
                    android.util.Log.i(TAG,"检测到摇晃，执行操作");
                    long currentSystemTime =System.currentTimeMillis();
                    if(lastVibrator_TimeStamp == -1 ){
                        lastVibrator_TimeStamp = System.currentTimeMillis();
                        if(window != null){
                            window.PlayNextMusic();
                        }
                        return;
                    }
                    // 在 5 秒内摇晃 一下
                    lastVibrator_TimeStamp = currentSystemTime;
                    if(window != null){
                        window.PlayNextMusic();
                    }

/*                    if(currentSystemTime - lastVibrator_TimeStamp > 5000){  //  摇晃间隔大于 下一首处理
                        if(window != null){
                            window.PlayNextMusic();
                        }
                    } else if(currentSystemTime - lastVibrator_TimeStamp < 5000){ //  摇晃间隔小于 5秒 那么暂停 播放 处理
                        lastVibrator_TimeStamp = currentSystemTime;
                        if(window != null){
                            window.PlayOrPauseMusic();
                        }

                    }*/
                    break;
            }
        }
    };

    public void  dismiss(){
        if(window != null ){
            window.dismiss();
        }

}

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        android.util.Log.i("zukgit", "MP3_Common_Fragment  onConfigurationChanged() 是否全屏变化 ");
        super.onConfigurationChanged(newConfig);

        if(window != null &&  tvText != null && window.isShowing()){
            window.dismiss();

            window.show(tvText);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        android.util.Log.i("zukgit", "MP3_Common_Fragment  onSaveInstanceState() 是否全屏变化 ");


        super.onSaveInstanceState(outState);
    }




    @Override
    public void onPause() {
        android.util.Log.i("zukgit", "MP3_Common_Fragment  onPause() 是否全屏变化 ");
        super.onPause();
        if(window != null){

            if(window.isShowing()){
                isShowWindows_When_Dismiss =  window.isShowing();

            }
            if(window.isPalyingMusic()){
                isPlayMusic_When_Dissmiss = window.isPalyingMusic();
            }
            android.util.Log.i("zukgit", "获取A onPause() isShowWindows_When_Dismiss = " + isShowWindows_When_Dismiss + "   isPlayMusic_When_Dissmiss="+isPlayMusic_When_Dissmiss);

            window.dismiss();
        }

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " MP3_Common_Fragment.onPause  ");
//        videoView.pause();
    }

    @Override
    public void onStop() {
        android.util.Log.i("zukgit", "MP3_Common_Fragment  onPause() 是否全屏变化 ");
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
                android.util.Log.i("zukgit", "X1 MP3_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " isBottom = " + isBottom);
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
                android.util.Log.i("zukgit", "X2  MP3_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos + " user_backup_num = " + user_backup_num);

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
        mediaControl.setVisibility(View.INVISIBLE);
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
                android.util.Log.i("zukgit", "X2  MP3_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.MP3_initPos  =   " + DataHolder.MP3_initPos);

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
