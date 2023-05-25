package com.and.zmain_life.node;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.DataHolder;

import java.util.List;

import utils.ZUtil;


/**
 * @param <T> 实现node接口的数据源
 *
 * @author relish
 * @since 20190802
 */
/* package */ class MultiLevelItemAdapter<T extends Node> extends
        RecyclerView.Adapter<MultiLevelItemAdapter.VHolder> {

    private T tree;
    Context mContext;


    public static MediaPlayer mediaPlayer ;
    public static boolean isMediaPlayerPlaying = false;
    public static boolean isMediaPlayerLoop = false;

    public static MediaPlayer offScreenMediaPlayer ;
    public static boolean isScreenMediaPlayerBegin = false;
    public static boolean isScreenMediaPlayerPlaying = false;

    public static String lastMp3Path;
    public transient static int currentMP3_Track_Position = -100 ;

    public transient  static int currentMP3_Track_Position_ScreenOff = -100 ;
    public static int last_click_level1_ID = -100;
    public static int last_click_level2_ID = -100;
    public static int last_click_level3_ID = -100;
    public static int last_click_level3_ParentID = -100;


    public static long origin_last_click_level3_ID = -100;


    public static int last_click_level3_ID_ViewPosition = -100;

    public static int next_click_level3_ID = -100;   //  下一首歌曲的 0id
    public static int next_click_level3_ID_ViewPosition = -1;  // 下一首歌曲的位置
    public static TextView mNext_Level3_TextView;  // 下一首歌曲时的 click 点击时 需要 使用


    public static int long_click_loop_level2_ID = -100;
    public static String long_click_loop_level2_ID_Name = null ;  // 第二个长按的选项的名称

    public static  volatile boolean isRandomPlayMusic ;

    public static boolean isLevel3_Loop = false;
    public static int long_click_loop_level3_ID = -100;
    public static int textview_level1_selected_bgcolor = -100;
    public static int textview_level2_selected_bgcolor = -100;
    public static int textview_level3_selected_bgcolor = -100;

    public static int textview_level1_selected_bgcolor_diss = -100;
    public static int textview_level2_selected_bgcolor_diss = -100;
    public static int textview_level3_selected_bgcolor_diss = -100;


    public  void screenToLastClickNode3Position() {
        int lastPosition = DataHolder.getPositionInList(last_click_level3_ID,last_click_level2_ID);

        if(lastPosition != -1){
            mMultiLevelPickerWindow.rv3.smoothScrollToPosition(lastPosition);
        }
    }


    public void  showStaticProp(String tag){
        Log.i("zukgit","════════════════════════════════"+tag+" MultiLevelItemAdapter 显示静态变量开始 "+"════════════════════════════════");
        Log.i("zukgit","1 mediaPlayer = "+mediaPlayer.hashCode() +"   mediaPlayer="+mediaPlayer +" mediaPlayer.isLoop()="+mediaPlayer.isLooping() + "   mediaPlayer.isPlaying()="+mediaPlayer.isPlaying()+"   mediaPlayer.getCurrentPosition()="+mediaPlayer.getCurrentPosition());
        Log.i("zukgit","2 isMediaPlayerPlaying = "+ isMediaPlayerPlaying);
        Log.i("zukgit","3 isMediaPlayerLoop = "+ isMediaPlayerLoop);
        Log.i("zukgit","4 offScreenMediaPlayer = "+offScreenMediaPlayer.hashCode() +"   offScreenMediaPlayer="+offScreenMediaPlayer +" offScreenMediaPlayer.isLoop()="+offScreenMediaPlayer.isLooping() + "   offScreenMediaPlayer.isPlaying()="+offScreenMediaPlayer.isPlaying()+"   offScreenMediaPlayer.getCurrentPosition()="+offScreenMediaPlayer.getCurrentPosition());
        Log.i("zukgit","5 isScreenMediaPlayerBegin = "+ isScreenMediaPlayerBegin);
        Log.i("zukgit","6 isScreenMediaPlayerPlaying = "+ isScreenMediaPlayerPlaying);
        Log.i("zukgit","7 lastMp3Path = "+ lastMp3Path);
        Log.i("zukgit","8 currentMP3_Track_Position = "+ currentMP3_Track_Position);
        Log.i("zukgit","9 currentMP3_Track_Position_ScreenOff = "+ currentMP3_Track_Position_ScreenOff);
        Log.i("zukgit","10 last_click_level1_ID = "+ last_click_level1_ID);
        Log.i("zukgit","11 last_click_level2_ID = "+ last_click_level2_ID);
        Log.i("zukgit","12 last_click_level3_ID = "+ last_click_level3_ID);
        Log.i("zukgit","13 last_click_level3_ParentID = "+ last_click_level3_ParentID);
        Log.i("zukgit","14 last_click_level3_ID_ViewPosition = "+ last_click_level3_ID_ViewPosition);
        Log.i("zukgit","15 next_click_level3_ID = "+ next_click_level3_ID);
        Log.i("zukgit","16 next_click_level3_ID_ViewPosition = "+ next_click_level3_ID_ViewPosition);
        Log.i("zukgit","17 long_click_loop_level2_ID = "+ long_click_loop_level2_ID);
        Log.i("zukgit","18 long_click_loop_level2_ID_Name = "+ long_click_loop_level2_ID_Name);
        Log.i("zukgit","19 isMediaPlayerLoop = "+ isMediaPlayerLoop);
        Log.i("zukgit","20 isRandomPlayMusic = "+ isRandomPlayMusic);
        Log.i("zukgit","21 isLevel3_Loop = "+ isLevel3_Loop);
        Log.i("zukgit","22 long_click_loop_level3_ID = "+ long_click_loop_level3_ID);
        Log.i("zukgit","23 textview_level1_selected_bgcolor = "+ textview_level1_selected_bgcolor);
        Log.i("zukgit","24 textview_level2_selected_bgcolor = "+ textview_level2_selected_bgcolor);
        Log.i("zukgit","25 textview_level3_selected_bgcolor = "+ textview_level3_selected_bgcolor);
        Log.i("zukgit","26 textview_level1_selected_bgcolor_diss = "+ textview_level1_selected_bgcolor_diss);
        Log.i("zukgit","27 textview_level2_selected_bgcolor_diss = "+ textview_level2_selected_bgcolor_diss);
        Log.i("zukgit","28 textview_level3_selected_bgcolor_diss = "+ textview_level3_selected_bgcolor_diss);

        Log.i("zukgit","════════════════════════════════"+tag+" MultiLevelItemAdapter 显示静态变量结束 "+"════════════════════════════════");



    }


    TextView mFirst_Level3_TextView;  // 第一首一首歌曲时的 click 点击时 需要 使用

    private int INDEX;
    public MultiLevelPickerWindow mMultiLevelPickerWindow;

    void recoverySelectedTextViewColor(){
        if(textview_level1_selected_bgcolor != -100 ){
            textview_level1_selected_bgcolor_diss = textview_level1_selected_bgcolor;
        }

        if(textview_level2_selected_bgcolor != -100 ){
            textview_level2_selected_bgcolor_diss = textview_level2_selected_bgcolor;
        }

        if(textview_level3_selected_bgcolor != -100 ){
            textview_level3_selected_bgcolor_diss = textview_level3_selected_bgcolor;
        }

    }


    public boolean isPalyingMusic(){
        boolean isPlayMusic = false;

        try{
            isPlayMusic =   isMediaPlayerPlaying;
            boolean isOffPlay = isScreenMediaPlayerPlaying;
            isPlayMusic = isPlayMusic || isOffPlay;
        }catch ( Exception e){
            isPlayMusic = false;
        }

        return  isPlayMusic;

    }


    MultiLevelItemAdapter(T tree, int index,Context context , MultiLevelPickerWindow xMultiLevelPickerWindow) {
        this.tree = tree;
        this.INDEX = index;
        mContext = context;
        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }
        if(offScreenMediaPlayer == null){
            offScreenMediaPlayer = new  MediaPlayer();
        }

        if(xMultiLevelPickerWindow != null){
            mMultiLevelPickerWindow =xMultiLevelPickerWindow;

        }


    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.mlp_item_chioce, parent, false);
        return new VHolder(view);
    }

    public void ForcePlayScreenOffMusic(){
        //  如果 灭屏时  手机本身的 mediaplayer 没有播放   那么   ScreenOffMusic 也不播放
        android.util.Log.i("zukgit","---------------- Force_playScreenOffMusic_1----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"   isScreenMediaPlayerPlaying="+isScreenMediaPlayerPlaying);


        android.util.Log.i("zukgit","---------------- Force_playScreenOffMusic_3----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

        currentMP3_Track_Position_ScreenOff = mediaPlayer.getCurrentPosition();
        currentMP3_Track_Position = mediaPlayer.getCurrentPosition();
        pauseMusic(mediaPlayer);
        pauseMusic(offScreenMediaPlayer);
        playerMusic(offScreenMediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
        android.util.Log.i("zukgit"," 停止 前台mediaPlayer  播放后台offScreenMediaPlayer   C1_offScreenMediaPlayer playScreenOffMusic  暂停A1offScreenMediaPlayer 事件 currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);


        android.util.Log.i("zukgit","---------------- Force_playScreenOffMusic_7----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);


    }


    void playScreenOffMusic(){
//  如果 灭屏时  手机本身的 mediaplayer 没有播放   那么   ScreenOffMusic 也不播放
        android.util.Log.i("zukgit","---------------- playScreenOffMusic_1----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"   isScreenMediaPlayerPlaying="+isScreenMediaPlayerPlaying);

        if(offScreenMediaPlayer != null && lastMp3Path != null &&  currentMP3_Track_Position_ScreenOff != -100){
            android.util.Log.i("zukgit","---------------- playScreenOffMusic_2----------------  ");
            if(mediaPlayer != null && isMediaPlayerPlaying){
                android.util.Log.i("zukgit","---------------- playScreenOffMusic_3----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

                currentMP3_Track_Position_ScreenOff = mediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = mediaPlayer.getCurrentPosition();
                pauseMusic(mediaPlayer);
                playerMusic(offScreenMediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                android.util.Log.i("zukgit"," 停止 前台mediaPlayer  播放后台offScreenMediaPlayer   C1_offScreenMediaPlayer playScreenOffMusic  暂停A1offScreenMediaPlayer 事件 currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

            }else  if(isScreenMediaPlayerPlaying){
                android.util.Log.i("zukgit","playScreenOffMusic  offScreenMediaPlayer 事件 正在播放着");
                android.util.Log.i("zukgit","---------------- playScreenOffMusic_4----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

            } else{
                android.util.Log.i("zukgit","playScreenOffMusic offScreenMediaPlayer 事件 在暂停");
                android.util.Log.i("zukgit","---------------- playScreenOffMusic_5----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

            }
            android.util.Log.i("zukgit","---------------- playScreenOffMusic_6----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

        }
        android.util.Log.i("zukgit","---------------- playScreenOffMusic_7----------------  lastMp3Path="+lastMp3Path+"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

    }



    void pauseScreenOffMusic(){


        if(offScreenMediaPlayer != null ){
            if(isScreenMediaPlayerPlaying){
                currentMP3_Track_Position_ScreenOff  = offScreenMediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = offScreenMediaPlayer.getCurrentPosition();
                android.util.Log.i("zukgit"," pauseScreenOffMusic 暂停A1offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

            }else if(isMediaPlayerPlaying){
                currentMP3_Track_Position_ScreenOff  = mediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = mediaPlayer.getCurrentPosition();
                android.util.Log.i("zukgit"," pauseScreenOffMusic 暂停A2 offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

            }else{
                currentMP3_Track_Position_ScreenOff  = offScreenMediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = offScreenMediaPlayer.getCurrentPosition();
                android.util.Log.i("zukgit"," pauseScreenOffMusic 暂停A44 A1offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

            }
            if(isScreenMediaPlayerPlaying){
                pauseMusic(offScreenMediaPlayer);
                pauseMusic(mediaPlayer);
                offScreenMediaPlayer.stop();
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                android.util.Log.i("zukgit","暂停offScreenMediaPlayer 播放 MediaPlayer  tion_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

            }

            android.util.Log.i("zukgit"," pauseScreenOffMusic 暂停A3 offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
            isScreenMediaPlayerBegin = false;

        }
        android.util.Log.i("zukgit"," pauseScreenOffMusic 暂停 offScreenMediaPlayer");
    }



    void recoveryMusic(){
        android.util.Log.i("zukgit","recoveryMusic_ChangePage    DataHolder.currentMP3_Track_Position_ChangePage="+DataHolder.currentMP3_Track_Position_ChangePage);



        android.util.Log.i("zukgit"," recoveryMusic  lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position="+currentMP3_Track_Position+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
        if(mediaPlayer != null && lastMp3Path != null &&  (currentMP3_Track_Position != -100 || currentMP3_Track_Position_ScreenOff != -100)){

            if(isMediaPlayerPlaying && isScreenMediaPlayerPlaying){  // 同时播放 肯定 停止  offScreenMediaPlayer
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = currentMP3_Track_Position_ScreenOff;

                pauseMusic(offScreenMediaPlayer);
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);

                android.util.Log.i("zukgit","C2_mediaPlayer  recoveryMusic offScreenMediaPlayer.pause()  currentMP3_Track_Position_ScreenOff= "+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
            }else if( isMediaPlayerPlaying){  //
                android.util.Log.i("zukgit","2 recoveryMusic  mediaPlayer 正在播放 ");


            }else if(isScreenMediaPlayerPlaying){

                pauseMusic(offScreenMediaPlayer);
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                android.util.Log.i("zukgit","C3_mediaPlayer recoveryMusic  mediaPlayer 正在播放  ");
            }else if(!DataHolder.isAPPInBackground  ){  //亮屏   // 切换回前台

                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position);
                android.util.Log.i("zukgit","C4_mediaPlayer  recoveryMusic  mediaPlayer 正在播放 currentMP3_Track_Position= "+currentMP3_Track_Position +" lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

            }else{
                android.util.Log.i("zukgit","5 recoveryMusic  mediaPlayer 正在播放 ");
//                mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition);
            }

            android.util.Log.i("zukgit","recoveryMusic  XXXX  ");
        }
    }


    synchronized  void  pauseMusic_ChangePage(){

        if(mediaPlayer != null ){
            long durationTime =   mediaPlayer.getDuration();
            if(DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse){
                currentMP3_Track_Position  = mediaPlayer.getCurrentPosition();
                DataHolder.currentMP3_Track_Position_ChangePage = mediaPlayer.getCurrentPosition();

            }


            android.util.Log.i("zukgit","recoveryMusic_ChangePage    DataHolder.currentMP3_Track_Position_ChangePage="+DataHolder.currentMP3_Track_Position_ChangePage +"   durationTime="+durationTime +"   currentMP3_Track_Position="+currentMP3_Track_Position);

            android.util.Log.i("zukgit"," pauseMusic  暂停A1offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
            mediaPlayer.pause();
//            pauseMusic(mediaPlayer);

            android.util.Log.i("zukgit"," pauseMusic  暂停A1offScreenMediaPlayer currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);

        }
    }

    void recoveryMusic_ChangePage(){
        mediaPlayer.start();
        mMultiLevelPickerWindow.onMusicTitlePlay(true);
//        playerMusic(mediaPlayer,lastMp3Path,DataHolder.currentMP3_Track_Position_ChangePage);
        android.util.Log.i("zukgit","recoveryMusic_ChangePage    DataHolder.currentMP3_Track_Position_ChangePage="+DataHolder.currentMP3_Track_Position_ChangePage);
//        mediaPlayer.start();

/*        android.util.Log.i("zukgit"," recoveryMusic  lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position="+currentMP3_Track_Position+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
        if(mediaPlayer != null && lastMp3Path != null &&  (currentMP3_Track_Position != -100 || currentMP3_Track_Position_ScreenOff != -100)){

            if(isMediaPlayerPlaying && isScreenMediaPlayerPlaying){  // 同时播放 肯定 停止  offScreenMediaPlayer
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = currentMP3_Track_Position_ScreenOff;

                pauseMusic(offScreenMediaPlayer);
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);

                android.util.Log.i("zukgit","C2_mediaPlayer  recoveryMusic offScreenMediaPlayer.pause()  currentMP3_Track_Position_ScreenOff= "+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
            }else if( isMediaPlayerPlaying){  //
                android.util.Log.i("zukgit","2 recoveryMusic  mediaPlayer 正在播放 ");


            }else if(isScreenMediaPlayerPlaying){

                pauseMusic(offScreenMediaPlayer);
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                android.util.Log.i("zukgit","C3_mediaPlayer recoveryMusic  mediaPlayer 正在播放  ");
            }else if(!DataHolder.isAPPInBackground  ){  //亮屏   // 切换回前台

                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position);
                android.util.Log.i("zukgit","C4_mediaPlayer  recoveryMusic  mediaPlayer 正在播放 currentMP3_Track_Position= "+currentMP3_Track_Position +" lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

            }else{
                android.util.Log.i("zukgit","5 recoveryMusic  mediaPlayer 正在播放 ");
//                mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition);
            }

            android.util.Log.i("zukgit","recoveryMusic  XXXX  ");
        }*/
    }




    MediaPlayer.OnCompletionListener OffScreen_onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            android.util.Log.i("zukgitXA","—————————————————————————————————————— [灭屏下播放完成回调] OffScreen_onCompletionListener 事件开始—————————————————————————————————————— ");


            if(mp.equals(mediaPlayer)){  // 当前正在播放完成的是 MediaPlayer
                android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口1 ");
                android.util.Log.i("zukgitXA__OffScreenMediaPlayer【playerMusic】 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  last_click_level3_ID="+last_click_level3_ID+"  last_click_level2_ID="+last_click_level2_ID+   " isMediaPlayerPlaying="+isMediaPlayerPlaying);
                if (isMediaPlayerPlaying){  // 当 MediaPlayer正在播放
                    NodeImpl nextNode = null;
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口1_1 ");
                    if(isRandomPlayMusic){
                        nextNode = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level2_ID);
                        android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口2  随机nextNode="+nextNode.toString());

                    } else {
                        nextNode = DataHolder.getNextSongId(last_click_level3_ID,last_click_level2_ID);
                        android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口3   顺序nextNode= "+nextNode.toString());

                    }
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口4  ");

                    if(nextNode != null){  //    不播放音乐的原因

                        pauseMusic(mediaPlayer);
                        pauseMusic(offScreenMediaPlayer);
                        offScreenMediaPlayer.setOnCompletionListener(OffScreen_onCompletionListener);
                        playerMusic(offScreenMediaPlayer,nextNode.mp3path,0);
                        last_click_level3_ID = (int)nextNode.id();
                        android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口5  last_click_level3_ID="+last_click_level3_ID+"  node.name="+nextNode.toString());
                        mMultiLevelPickerWindow.mListener.onSelect(3,nextNode,true);

                    }
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口6  isRandomPlayMusic="+isRandomPlayMusic+"  last_click_level2_ID="+last_click_level2_ID+" last_click_level3_ID="+last_click_level3_ID+" nextNode="+nextNode);
                }
                android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口7 ");

            }else  if(mp.equals( offScreenMediaPlayer)){
                NodeImpl nextNode = null;
                android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口8 ");

                if(isRandomPlayMusic){
                    nextNode = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level2_ID);
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口9  随机nextNode="+nextNode.toString());

                } else {
                    nextNode = DataHolder.getNextSongId(last_click_level3_ID,last_click_level2_ID);
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口10   顺序nextNode= "+nextNode.toString());

                }
                android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口11 ");
                if(nextNode != null){

                    pauseMusic(mediaPlayer);
                    pauseMusic(offScreenMediaPlayer);

                    if(DataHolder.isAPPInBackground){  // 在 前台
                        mediaPlayer.setOnCompletionListener(onCompletionListener);
                        playerMusic(mediaPlayer,nextNode.mp3path,0);
                    }else{
                        offScreenMediaPlayer.setOnCompletionListener(OffScreen_onCompletionListener);
                        playerMusic(offScreenMediaPlayer,nextNode.mp3path,0);
                    }

                            last_click_level3_ID = (int)nextNode.id();
                    // 更新 Title
                    mMultiLevelPickerWindow.mListener.onSelect(3,nextNode,true);
                    android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口12  last_click_level3_ID="+last_click_level3_ID+"  node.name="+nextNode.toString());

                }
                android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口13 ");
            }
            android.util.Log.i("zukgitXA","【 OffScreenCompletionListener】_____入口14 ");
            android.util.Log.i("zukgitXA","—————————————————————————————————————— [灭屏下播放完成回调] OffScreen_onCompletionListener 事件开始—————————————————————————————————————— ");

        }
    };


    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            android.util.Log.i("zukgitXA","—————————————————————————————————————— OnCompletionListener 事件开始 mp=["+mp.hashCode()+"]—————————————————————————————————————— ");

            if(isLevel3_Loop){   // 如果单曲 循环  那么 重新播放
                playerMusic(mediaPlayer,lastMp3Path,0);
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点1  isLevel3_Loop="+isLevel3_Loop);
                android.util.Log.i("zukgitXA","—————————————————————————————————————— OnCompletionListener 事件结束_1 —————————————————————————————————————— ");

                return;
            }
            android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点2  ");
            if(mNext_Level3_TextView != null){
                android.util.Log.i("zukgitXA","A screenToPosition_Level3  播放完成回调AA mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点3  ");

            }

            int number_of_node3_SameParent = 1;
            NodeImpl level3_selected_node  = null;
            android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点3  next_click_level3_ID_ViewPosition= "+next_click_level3_ID_ViewPosition);

            if(next_click_level3_ID_ViewPosition != -1 ){  //随机 播放

                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点4  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);

                // 后台下的 处理  后台时的处理  以及当前 level2 重复项 以及 选中的 level2 不同时  交给 offScreenMediaPlayer 播放
                if( (DataHolder.isAPPInBackground ) || (long_click_loop_level2_ID != last_click_level2_ID && long_click_loop_level2_ID != -100) ){

                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点5  ");

                    if(isRandomPlayMusic ){
                        level3_selected_node  = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level3_ParentID);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点6  后台随机  level3_selected_node="+level3_selected_node.toString());

                        next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);
                        next_click_level3_ID = (int)level3_selected_node.id();
                        last_click_level3_ID = (int)level3_selected_node.id();
                        last_click_level3_ParentID = (int)level3_selected_node.parentid;
                        lastMp3Path = level3_selected_node.mp3path;
                        String nodeName = level3_selected_node.name;
                        if(mMultiLevelPickerWindow.mListener != null){
                            android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点7  ");

                            mMultiLevelPickerWindow.mListener.onSelect(3,level3_selected_node,true);
                        }
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点8  level3_selected_node.mp3path="+level3_selected_node.mp3path);

                        playerMusic(offScreenMediaPlayer,level3_selected_node.mp3path,0);
                        android.util.Log.i("zukgitXA","C10_offScreenMediaPlayer click_recoveryMusic 开始播放文件 ==  level3_selected_node.mp3path"+level3_selected_node.mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+0+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff +"后台随机node ="+level3_selected_node.toString());

                    }else{
                        level3_selected_node     = DataHolder.getNextSongId(last_click_level3_ID,last_click_level3_ParentID);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点9  后台顺序 level3_selected_node"+level3_selected_node.toString());

                        next_click_level3_ID = (int)level3_selected_node.id();
                        next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);
                        last_click_level3_ID = (int)level3_selected_node.id();
                        last_click_level3_ParentID = (int)level3_selected_node.parentid;
                        lastMp3Path = level3_selected_node.mp3path;
                        String nodeName = level3_selected_node.name;



                        if(mMultiLevelPickerWindow.mListener != null){
                            android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点10  ");

                            mMultiLevelPickerWindow.mListener.onSelect(3,level3_selected_node,true);
                        }
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点11  level3_selected_node.mp3path="+level3_selected_node.mp3path);

                        playerMusic(offScreenMediaPlayer,level3_selected_node.mp3path,0);
                        android.util.Log.i("zukgitXA","C11_offScreenMediaPlayer click_recoveryMusic 开始播放文件 ==  level3_selected_node.mp3path"+level3_selected_node.mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+0+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+" 后台顺序node ="+level3_selected_node.toString());

                    }
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点12  ");

                    isScreenMediaPlayerBegin = true;
//                    mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition);
                }else if(!DataHolder.isAPPInBackground && !isRandomPlayMusic){   // 前台下

                    level3_selected_node     = DataHolder.getNextSongId(last_click_level3_ID,last_click_level3_ParentID);
                    next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);

                    number_of_node3_SameParent = DataHolder.getNode3NumberWithSameParent(level3_selected_node.id(),level3_selected_node.parentid);

                    android.util.Log.i("zukgitXA","前台顺序 node level3_selected_node="+level3_selected_node.toString());
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点13 前台顺序 level3_selected_node="+level3_selected_node);

                    if(isLevel3_Loop){
                        playerMusic(mediaPlayer,lastMp3Path,0);
                        android.util.Log.i("zukgitXA","前台顺序 循环   lastMp3Path="+lastMp3Path +" isLevel3_Loop="+isLevel3_Loop);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点14  isLevel3_Loop="+isLevel3_Loop);
                        android.util.Log.i("zukgitXA","—————————————————————————————————————— OnCompletionListener 事件结束_2 —————————————————————————————————————— ");

                        return;

                    } else if(level3_selected_node.parentid == last_click_level2_ID ){

                        if(number_of_node3_SameParent == 1){  //  如果只有  1首歌曲  那么  播放完成暂停它 暂停它
                            pauseMusic(mediaPlayer);
                            pauseMusic(offScreenMediaPlayer);
                            mMultiLevelPickerWindow.onMusicTitlePlay(false);
                        }else{
                            mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition,level3_selected_node);

                        }
// 前台顺序 node level3_selected_node=text=[ueiuei]   id=[42]  parentid=[428]  level=[3] index4parent[0]  count=[0] mp3path=[/storage/emulated/0/zmain/mp3/ueiuei.mp3]  selectedChildId=[0] color_int=[-100]
                        // 【onCompletion 结束事件】___________点13 前台顺序 level3_selected_node=text=[ueiuei]   id=[42]  parentid=[428]  level=[3] index4parent[0]  count=[0] mp3path=[/storage/emulated/0/zmain/mp3/ueiuei.mp3]  selectedChildId=[0] color_int=[-100]
//   【onCompletion 结束事件】___________点14  last_click_level2_ID=428 level3_selected_node.parentid= 428
                        android.util.Log.i("zukgitXA","前台顺序 循环3    lastMp3Path="+lastMp3Path +" isLevel3_Loop="+isLevel3_Loop+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点14  last_click_level2_ID="+last_click_level2_ID+" level3_selected_node.parentid= "+level3_selected_node.parentid);

                    }  else  if(level3_selected_node.parentid != last_click_level2_ID ){
                        //   如果是单击的话  并且 用户已经切换了 level2 的项 那么就 不播放音乐  把文字颜色 设置成黑色的
                        mMultiLevelPickerWindow.onMusicTitlePlay(false);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点15  last_click_level2_ID= "+last_click_level2_ID+"   level3_selected_node.parentid="+level3_selected_node.parentid);

                        android.util.Log.i("zukgitXA","前台顺序 循环4  screenToPosition_Level3  如果是单击的话  并且 用户已经切换了 level2 的项 那么就 不播放音乐  把文字颜色 设置成黑色的 ="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView=");

                    }
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点16  ");
                    android.util.Log.i("zukgitXA","前台顺序 循环5  last_click_level2_ID="+last_click_level2_ID+"   level3_selected_node.parentid="+level3_selected_node.parentid);

                } else {
                    level3_selected_node  = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level3_ParentID);
                    next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点17  前台随机  level3_selected_node="+level3_selected_node.toString());

                    android.util.Log.i("zukgitXA","前台随机 node level3_selected_node="+level3_selected_node.toString());
                    if(mMultiLevelPickerWindow.mListener != null){
                        mMultiLevelPickerWindow.mListener.onSelect(3,level3_selected_node,false);
                        android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点18  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);
                    }
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点18_1  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);

//                    playerMusic(mediaPlayer,level3_selected_node.mp3path,0);
                    mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition,level3_selected_node);
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点19  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);

                }
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点20  ");


//                next_click_level3_ID_ViewPosition = -1;
                if(mNext_Level3_TextView != null){
                    android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点20_1  mNext_Level3_TextView="+mNext_Level3_TextView.getText().toString());
                }
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点21  ");

            } else if(mNext_Level3_TextView != null){
                android.util.Log.i("zukgitXA","C screenToPosition_Level3  播放完成回调CC_A mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());
                mNext_Level3_TextView.performClick();
                android.util.Log.i("zukgitXA"," 播放完成回调 mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点22  ");

            }else{
                android.util.Log.i("zukgitXA","D screenToPosition_Level3  播放完成回调CC_B mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView= null ");
                android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点23  ");

            }
            android.util.Log.i("zukgitXA","【onCompletion 结束事件】___________点24  ");

            android.util.Log.i("zukgitXA","Over screenToPosition_Level3  播放完成回调CC_C mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   mNext_Level3_TextView= null ");
            android.util.Log.i("zukgitXA","—————————————————————————————————————— OnCompletionListener 事件开始_Over—————————————————————————————————————— ");

        }


/*                XLogUtils.e("播放完成");
                mSurfaceView.setVisibility(View.GONE);
                if (currentVideoIndex == videoUrls.size() - 1) return;
                currentVideoIndex++;
                videoUrl = videoUrls.get(currentVideoIndex);
                XLogUtils.d("播放下一个");
                playVideo();*/

    };

    public void PlayNextMusic(){
        if(mediaPlayer != null){
            mediaPlayer.setLooping(false);

        }
        if(isRandomPlayMusic ){
            NodeImpl level3_selected_node     = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level3_ParentID);

            next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);

            if(mMultiLevelPickerWindow != null){
                mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition,level3_selected_node);
            }

        }else{
            NodeImpl level3_selected_node     = DataHolder.getNextSongId(last_click_level3_ID,last_click_level3_ParentID);
            next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(level3_selected_node.id(),level3_selected_node.parentid);
            if(mMultiLevelPickerWindow != null){
                mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition,level3_selected_node);
            }
        }





    }



    void pauseMusic(MediaPlayer xMediaPlayer){





        if(xMediaPlayer.equals(offScreenMediaPlayer)){
            isScreenMediaPlayerPlaying = false;

            if(DataHolder.isMP3Page_ChangeTo_AnotherPage){
                currentMP3_Track_Position_ScreenOff = xMediaPlayer.getCurrentPosition();
            }
            android.util.Log.i("zukgitXA__OffScreenMediaPlayer【pauseMusic__"+xMediaPlayer.hashCode()+"】  "," 停止播放文件 ==  lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);


        }

        if(xMediaPlayer.equals(mediaPlayer)){
            isMediaPlayerPlaying = false;
            if(DataHolder.isMP3Page_ChangeTo_AnotherPage){
                currentMP3_Track_Position =  xMediaPlayer.getCurrentPosition();
            }
            android.util.Log.i("zukgitXA__MediaPlayer【pauseMusic__"+xMediaPlayer.hashCode()+"】  "," 停止播放文件 ==  lastMp3Path="+lastMp3Path +" currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

        }
        xMediaPlayer.setOnCompletionListener(null);
        xMediaPlayer.pause();
        xMediaPlayer.stop();
        xMediaPlayer.reset();

    }



    void playerMusic(MediaPlayer xmediaPlayer , String path , int position){
        android.util.Log.i("zukgit","═════════════════════ " + "播放音乐  Mediad["+xmediaPlayer.hashCode()+"] Begin ═════════════════════ "+"path="+path+"  position = "+position );
        xmediaPlayer.stop();
        xmediaPlayer.reset();


        xmediaPlayer.setOnCompletionListener(onCompletionListener);

        android.util.Log.i("【playerMusic___"+xmediaPlayer.hashCode()+"】___起点1 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position);

        if(xmediaPlayer.equals(offScreenMediaPlayer)){
            android.util.Log.i("zukgitXA__OffScreenMediaPlayer【playerMusic___"+xmediaPlayer.hashCode()+"】___起点2 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position);

            isScreenMediaPlayerPlaying = true;
        }

        if(xmediaPlayer.equals(mediaPlayer)){
            isMediaPlayerPlaying = true;
            android.util.Log.i("zukgitXA__MediaPlayer【playerMusic___"+xmediaPlayer.hashCode()+"】___起点3 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position);

        }

        android.util.Log.i("zukgit","【playerMusic___"+xmediaPlayer.hashCode()+"】___起点4_1 recoveryMusic  playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position );

        try{
            lastMp3Path = path;
            xmediaPlayer.setDataSource(path);
            xmediaPlayer.prepareAsync();

            android.util.Log.i("zukgit【playerMusic___"+xmediaPlayer.hashCode()+"】___起点4_2 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position );

//            if(position > curDuration ){
//                position_result = 0;
//            }
            android.util.Log.i("zukgit【playerMusic___"+xmediaPlayer.hashCode()+"】___起点5 recoveryMusic ","playerMusic__currentMP3_Track_Position_ScreenOff 开始播放文件 ==  path="+path+"   position="+position  );

            xmediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕回调

                    mp.setLooping(isLevel3_Loop);
//                    int curDuration = mp.getDuration();
/*                   int position_result = 0;
                    android.util.Log.i("zukgit","【playerMusic___】___起点6  curDuration="+curDuration + " position = "+position);
                    if(position > curDuration ){
                        position_result = 0;
                        android.util.Log.i("zukgit","【playerMusic___】___起点7  curDuration="+curDuration + " position = "+position +"  position_result="+position_result);

                    }else{
                        position_result =  position ;
                        android.util.Log.i("zukgit","【playerMusic___】___起点8  curDuration="+curDuration + " position = "+position +"  position_result="+position_result);

                    }*/


                    int duration = mp.getDuration();
                    android.util.Log.i("zukgit","zukgit【playerMusic___】___起点8_0  duration ="+duration);
                    if(position > duration ){
//                    if(position > duration || (origin_last_click_level3_ID != last_click_level3_ID && origin_last_click_level3_ID != -100)){
                        mp.seekTo(0);
                        android.util.Log.i("zukgit","zukgit【playerMusic___】___起点8_0  seek(0)");

                    }else{
                        mp.seekTo(position);
                        android.util.Log.i("zukgit","zukgit【playerMusic___】___起点8_1  position="+position);
                    }


                    android.util.Log.i("zukgit","zukgit【playerMusic___】___起点9   position = "+position  +"   duration = "+ duration);

                    mp.start();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            android.util.Log.i("zukgit","当前 Item的 路径不对  "+path);
            Toast.makeText(mContext.getApplicationContext(),"当前 Item的 路径不对  "+path,Toast.LENGTH_SHORT).show();
            android.util.Log.i("zukgit","【playerMusic___】___起点5   有异常！！   position="+position );

        }
        android.util.Log.i("zukgit","═════════════════════ " + "播放音乐 Mediad["+xmediaPlayer.hashCode()+"] End ═════════════════════ "+"path="+path+"  position = "+position );
    }
    @Override
    public int getItemCount() {
        if (tree == null) return 0;
        List<? extends Node> children = tree.children();
        return children == null ? 0 : children.size();
    }

    void setNewData(T data) {
        tree = data;
        notifyDataSetChanged();
    }

    class VHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        VHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    private OnItemPickerListener mOnSelectListener;

    void setOnSelectListener(OnItemPickerListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    interface OnItemPickerListener<T extends Node> {
        void onSelect(T parent, T selectedChild);
    }

    T getTree() {
        return tree;
    }


    @Override
    public void onBindViewHolder(@NonNull MultiLevelItemAdapter.VHolder holder, int position) {
        if (tree == null) return;
        List<? extends Node> children = tree.children();
        if (children == null) return;
        //noinspection unchecked
        T item = (T) children.get(position);
        int cur_node_id = (int)item.id();
        long cur_node_long_id = item.id();
        long cur_node_long_parentid = item.parentid();
        holder.tvName.setText(item.text());
        // 获取 当前的 item_id 的 名称     意味着 每个 item的 内容可能变化  但是 id 不变
        // 这个 歌曲的 名称 是变化的
//        String nextSongName = DataHolder.getSongNameWithID(cur_node_long_id);






        if (item.id() == tree.selectedChild()) {

            holder.tvName.setTextColor(ContextCompat.getColor(holder.tvName.getContext(),
                    R.color.mlp_item_selected_text));
            if (INDEX == 0) {

                if(textview_level1_selected_bgcolor_diss != -100 ){
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor_diss);
                    android.util.Log.i("zukgit","设置颜色X1_1 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);
                    item.setcolor(textview_level1_selected_bgcolor_diss);
                    textview_level1_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgit","设置颜色X1_2 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);

                }    else{
                    textview_level1_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor);
                    android.util.Log.i("zukgit","设置颜色X1_3 Level_1 textview_level1_selected_bgcolor = "+textview_level1_selected_bgcolor);
                }

//

            } else if (INDEX == 1) {

                if(textview_level2_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor_diss);
                    textview_level2_selected_bgcolor_diss = -100;
                    android.util.Log.i("zukgitXA","X112_XAA_3  第一种颜色 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                } else if(isRandomPlayMusic && long_click_loop_level2_ID  != -100 && long_click_loop_level2_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    textview_level2_selected_bgcolor = R.color.color_india_red;
                    android.util.Log.i("zukgitXA","X112_XAA_3  第二种颜色 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","X112_XAA_3  第三种颜色 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                }  else{

                    android.util.Log.i("zukgitXA","X112_XAA_3  第四种颜色 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                    textview_level2_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor);



                }

                if(cur_node_id ==last_click_level2_ID ){
                    holder.tvName.setSelected(true);
                }

            } else if (INDEX == 2) {
                last_click_level3_ID_ViewPosition = position;

                android.util.Log.i("zukgitXA","节点3__0 设置颜色:  position"+position+" last_click_level3_ID_ViewPosition="+last_click_level3_ID_ViewPosition +" holder.tvName="+holder.tvName.getText().toString());

                if(textview_level3_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor_diss);
                    android.util.Log.i("zukgitXA","节点3__1 设置颜色:  textview_level3_selected_bgcolor_diss = "+textview_level3_selected_bgcolor_diss +"  holder.tvName="+holder.tvName.getText().toString());
                    textview_level3_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","节点3__2 设置颜色:  item.color() = "+ item.color());
                }   else{
                    textview_level3_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor);
                    android.util.Log.i("zukgitXA","节点3__3 设置颜色:  item.color() = "+ item.color() +"   textview_level3_selected_bgcolor="+textview_level3_selected_bgcolor +" holder.tvName="+holder.tvName.getText().toString());

                }



                if(cur_node_id ==last_click_level3_ID ){
                    holder.tvName.setSelected(true);
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","节点3__4 设置颜色:  item.color() = "+ item.color());

                }
                if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    android.util.Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

                }
                android.util.Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

            }
        } else {
            holder.tvName.setTextColor(ContextCompat.getColor(
                    holder.tvName.getContext(),
                    R.color.mlp_item_unselected_text
            ));
            holder.tvName.setBackgroundResource(R.color.transparent);

            if(cur_node_id ==last_click_level3_ID ){
                holder.tvName.setSelected(true);
            }
        }



        holder.tvName.setTag(item);

        if(item.level() == 1){   // 长按 第一项  改变颜色 背景颜色

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mMultiLevelPickerWindow.onBackgroundChanged();
                    return false;
                }

            });
        }

        if(item.level() == 2){   // 作者的 TextView  长按 选中它  说明 对于这个歌手 进行随机 播放

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level2_ID = cur_node_id;
                    long_click_loop_level2_ID_Name = holder.tvName.getText().toString();
                    if(long_click_loop_level2_ID_Name != null && long_click_loop_level2_ID_Name.contains("(")){
                        long_click_loop_level2_ID_Name=long_click_loop_level2_ID_Name.substring(0,long_click_loop_level2_ID_Name.lastIndexOf("("));
                    }
                    DataHolder.long_click_loop_level2_ID_Name = long_click_loop_level2_ID_Name;

                    android.util.Log.i("zukgitXA","X112_X_A1 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                    isRandomPlayMusic = !isRandomPlayMusic;
                    android.util.Log.i("zukgitXA","X112_X_A2 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                    if(mediaPlayer != null && isRandomPlayMusic ){  // 开始 作者随机播放

                        android.util.Log.i("zukgitXA","X112_XA 随机播放 播放完成回调  isRandomPlayMusi="+isRandomPlayMusic);

                        if(mediaPlayer.isLooping()){  // 取消 循环  正在循环
                            mediaPlayer.setLooping(false);
                            isMediaPlayerLoop = false;
                        }

                        NodeImpl nextRandomNode = DataHolder.getRandomNextSongId(last_click_level3_ID,last_click_level3_ParentID);
                        if(nextRandomNode != null){
                            next_click_level3_ID = (int)nextRandomNode.id();
                            next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                            String nodeName = nextRandomNode.name;
                            android.util.Log.i("zukgitXA","next_click_level3_ID 随机播放 播放完成回调  isRandomPlayMusic="+isRandomPlayMusic + "   next_click_level3_ID="+next_click_level3_ID + "    nodeName="+nodeName );

                        }



                    }else if(!isRandomPlayMusic && mediaPlayer != null){

                        if(mediaPlayer.isLooping()){  // 取消 循环  正在循环
                            mediaPlayer.setLooping(false);
                            isMediaPlayerLoop = false;
                        }

                        NodeImpl nextRandomNode = DataHolder.getNextSongId(last_click_level3_ID,last_click_level3_ParentID);

                        if(nextRandomNode != null){
                            next_click_level3_ID = (int)nextRandomNode.id();
                            next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                            String nodeName = nextRandomNode.name;
                            android.util.Log.i("zukgitXA","next_click_level3_ID 随机播放 播放完成回调  isRandomPlayMusic="+isRandomPlayMusic + "   next_click_level3_ID="+next_click_level3_ID + "    nodeName="+nodeName );

                        }

                    }
                    mMultiLevelPickerWindow.update();
                    return false;
                }
            });
        }


        if(item.level() == 3){

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level3_ID = cur_node_id;


                    if(mediaPlayer != null){

                        if(isLevel3_Loop){  // 取消 循环  正在循环
                            mediaPlayer.setLooping(false);
                            long_click_loop_level3_ID = -100;
                            isLevel3_Loop = false;
                            android.util.Log.i("zukgitACX","level3 -- 长按  取消循环");
                        }else{  // 开始循环
                            long_click_loop_level3_ID =  cur_node_id;
                            mediaPlayer.setLooping(true);
                            isLevel3_Loop = true;
                            android.util.Log.i("zukgitACX","level3 -- 长按  设置循环");


                        }
                    }
                    return false;
                }
            });
        }


        holder.tvName.setOnClickListener(view -> {
            //noinspection unchecked
            T selectedChild = (T) view.getTag();

            if(mMultiLevelPickerWindow != null){
                mMultiLevelPickerWindow.callbackSelected_Text(selectedChild);
            }

            if (mOnSelectListener != null) {
                notifyDataSetChanged();
                //noinspection unchecked
                mOnSelectListener.onSelect(tree, selectedChild);
            }
            String curText =  selectedChild.text();
            String mp3path =  selectedChild.mp3path();
            android.util.Log.i("zukgit","curText = "+curText +"   PATH="+mp3path);

            long cur_long_ID = selectedChild.id();
            long cur_long_ParentID = selectedChild.parentid();

            int curID = (int)selectedChild.id();
            int level = selectedChild.level();
            if(level == 1){
                textview_level1_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level1_selected_bgcolor);
                last_click_level1_ID = curID;

            }

            if(level == 2){
                textview_level2_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level2_selected_bgcolor);
                last_click_level2_ID = curID;

                // 如果 点击了  长按的level2_node  按钮  如果当前 mSc
                android.util.Log.i("zukgitNode"," Node2 的 点击事件 ");

                if(curID == long_click_loop_level2_ID && isScreenMediaPlayerPlaying){
                    currentMP3_Track_Position_ScreenOff =   offScreenMediaPlayer.getCurrentPosition();
                    currentMP3_Track_Position = currentMP3_Track_Position_ScreenOff;

                    pauseMusic(offScreenMediaPlayer);
                    isScreenMediaPlayerPlaying = false;
//                    playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                    android.util.Log.i("zukgitNode","playerMusic C13_mediaPlayer  随机随机随机随机随机播放  screenToPosition_Level3 播放完成回调 currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"   isRandomPlayMusic="+isRandomPlayMusic+" lastMp3Path="+lastMp3Path+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);

                }else{
                    android.util.Log.i("zukgitNode","不是 长按选项!! curID ="+curID+"   long_click_loop_level2_ID="+long_click_loop_level2_ID+"  offScreenMediaPlayer.isPlaying()="+offScreenMediaPlayer.isPlaying());
                }

            }
            if(level == 3){
                T selectedChild_level_3 = (T) view.getTag();
                android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 Name["+holder.tvName.getText().toString()+"] 开始  ______________________________________________ ");
                origin_last_click_level3_ID = last_click_level3_ID;

                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_1   selectedChild_level_3="+selectedChild_level_3.toString());
                // 如果当前点击的不是
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_2   long_click_loop_level3_ID="+long_click_loop_level3_ID +"   cur_node_id="+cur_node_id);
                if(long_click_loop_level3_ID != cur_node_id){   // 取消单曲循环
                    isLevel3_Loop = false;
                    long_click_loop_level3_ID = -100;
                }
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_3   last_click_level3_ID="+last_click_level3_ID +"   curID="+curID +" last_click_level3_id="+last_click_level3_ID +"   lastMp3Path="+lastMp3Path  +"  last_click_level2_ID="+last_click_level2_ID +"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  long_click_loop_level2_ID_Name="+long_click_loop_level2_ID_Name);
                origin_last_click_level3_ID = last_click_level3_ID;
                if(last_click_level3_ID == selectedChild_level_3.id()){  // 两次点击相同的 text 那么 执行暂停 播放 循环操作
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();

                    if(isMediaPlayerPlaying){   // 如果当前是 播放 状态 那么暂停它
                        android.util.Log.i("zukgitXA","X2 暂停播放文件 ==  "+mp3path +"  暂停它");
                        mMultiLevelPickerWindow.onMusicTitlePlay(false);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口15  ");
                        currentMP3_Track_Position = mediaPlayer.getCurrentPosition();
                        pauseMusic(mediaPlayer);

                        textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                        selectedChild_level_3.setcolor(textview_level3_selected_bgcolor);
                        holder.tvName.setTextColor(textview_level3_selected_bgcolor);

                    }else{  //  如果是暂停 状态 那么  播放它

                        textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                        selectedChild_level_3.setcolor(textview_level3_selected_bgcolor);
                        holder.tvName.setTextColor(textview_level3_selected_bgcolor);

                        android.util.Log.i("zukgitXA","X3 开始播放文件 ==  "+mp3path +"  重新播放它  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口16  ");
                        if(currentMP3_Track_Position > 0){
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                            mMultiLevelPickerWindow.onMusicTitlePlay(true);
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口16_1   ");
                        }else  if(currentMP3_Track_Position_ScreenOff > 0 && currentMP3_Track_Position < 0 ){
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口17  ");

                            if(mp3path != null && lastMp3Path ==null){
                                playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position_ScreenOff);
                                mMultiLevelPickerWindow.onMusicTitlePlay(true);
                                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口18  ");

                            }else{
                                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                                mMultiLevelPickerWindow.onMusicTitlePlay(true);
                                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口19  ");

                            }
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口20  ");

                        }else if(currentMP3_Track_Position > 0){
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口21  ");

                            playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),currentMP3_Track_Position);
                            mMultiLevelPickerWindow.onMusicTitlePlay(true);
                        }

                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口22  currentMP3_Track_Position ="+currentMP3_Track_Position +"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff +"  mediaPlayer.getCurrentPosition()="+mediaPlayer.getCurrentPosition());
                        playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),currentMP3_Track_Position);
                        mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    }

                    android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 结束_0点  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return ;


                }
                // 是 Binder的Id     curID 是 按钮 绑定的 id

                //  如果点击了 不在 循环 内的 第三item 那么 变为 单曲顺序播放sa
                if(selectedChild.parentid() != long_click_loop_level2_ID  && long_click_loop_level2_ID != -100 ){
                    pauseMusic(mediaPlayer);
                    pauseMusic(offScreenMediaPlayer);
                    playerMusic(mediaPlayer,selectedChild.mp3path(),0);
                    long_click_loop_level2_ID = -100;  // 取消  作者循环

                    mMultiLevelPickerWindow.update();
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_3  ");
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                    android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 结束_1点   Name["+holder.tvName.getText().toString()+"]  last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return;

                }

                long pre_last_click_level3_parentId = DataHolder.getParentId(last_click_level3_ID);
                textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_4   last_click_level3_ID="+last_click_level3_ID+"  pre_last_click_level3_parentId="+pre_last_click_level3_parentId+"  curID="+curID+"  cur_long_ParentID="+cur_long_ParentID);
                selectedChild.setcolor(textview_level3_selected_bgcolor);

                NodeImpl nextRandomNode_Default   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5  cur_long_ID="+cur_long_ID+"  cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition +" selectedChild_level_3.id()="+selectedChild_level_3.id()+"   last_click_level3_ID="+last_click_level3_ID+"   nextRandomNode_Default="+nextRandomNode_Default);

                if(nextRandomNode_Default != null ){

                    if(selectedChild_level_3.id() == last_click_level3_ID){
                        if(isMediaPlayerPlaying || isScreenMediaPlayerPlaying){
                            pauseMusic(mediaPlayer);
                            pauseMusic(offScreenMediaPlayer);
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_6 ");
                        }else{
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7 ");

                            if(last_click_level3_ID != origin_last_click_level3_ID && origin_last_click_level3_ID != - 100){
                                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7_1  last_click_level3_ID="+last_click_level3_ID+"  origin_last_click_level3_ID="+origin_last_click_level3_ID);

                                playerMusic(mediaPlayer,mp3path,0);
                            }else{
                                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7_2  last_click_level3_ID="+last_click_level3_ID+"  origin_last_click_level3_ID="+origin_last_click_level3_ID);

                                playerMusic(mediaPlayer,mp3path,mediaPlayer.getCurrentPosition());
                            }

                        }
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_8 ");
                        last_click_level3_ID = (int)selectedChild_level_3.id();
                        return;
                    }else{
                        next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(nextRandomNode_Default.id(),nextRandomNode_Default.parentid);

                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5_1    next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  nextRandomNode_Default.id()="+nextRandomNode_Default.id()+"  nextRandomNode_Default.parentid="+nextRandomNode_Default.parentid);

                    }


                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5   next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);


                }
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2  ");




                if(isRandomPlayMusic && next_click_level3_ID != -100 && last_click_level3_ParentID == pre_last_click_level3_parentId &&  (long_click_loop_level2_ID == last_click_level3_ParentID  && long_click_loop_level2_ID != -100)){
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口3  ");
                    NodeImpl nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    if(nextRandomNode.id() == cur_long_ID) {
                        nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    }
                    if(nextRandomNode.id() == cur_long_ID) {
                        nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    }
                    next_click_level3_ID = (int)nextRandomNode.id();
                    long parentId = nextRandomNode.parentid;
                    next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                    String nodeName = DataHolder.getSongNameWithID(next_click_level3_ID);
                    android.util.Log.i("zukgitNode","X112_A 随机随机随机随机随机播放  screenToPosition_Level3 播放完成回调 nodeName="+nodeName+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口4  ");
                    pauseMusic(mediaPlayer);
                    pauseMusic(offScreenMediaPlayer);
                    playerMusic(mediaPlayer,selectedChild.mp3path(),0);
                    mMultiLevelPickerWindow.onMusicTitlePlay(true);

                }else if(cur_long_ParentID != pre_last_click_level3_parentId || long_click_loop_level2_ID != last_click_level3_ParentID  || long_click_loop_level2_ID == -100 ){ // 如果点击了 其他的  level_3 按钮的话
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口5  ");

                    NodeImpl nextRandomNode   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                    if(nextRandomNode != null){
                        next_click_level3_ID_ViewPosition =  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                        next_click_level3_ID = (int)nextRandomNode.id();
                        android.util.Log.i("zukgitNode","nextRandomNode 不为空 顺序播放顺序播放  screenToPosition_Level3 播放完成回调 nodeName="+nextRandomNode.name+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);

                        pauseMusic(mediaPlayer);
                        pauseMusic(offScreenMediaPlayer);
                        playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),0);
                        mMultiLevelPickerWindow.onMusicTitlePlay(true);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口6  ");
                        last_click_level3_ID = (int)selectedChild_level_3.id();
                        last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                        android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 结束_2点  Name["+holder.tvName.getText().toString()+"]  last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

                        return;

                    }else{
                        android.util.Log.i("zukgitNode"," nextRandomNode为空 顺序播放顺序播放  screenToPosition_Level3 播放完成回调 nodeName=null "+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口7  ");

                    }

                    // 是否 是 当前的 选中level2项的子项
                    if(cur_long_ParentID == long_click_loop_level2_ID && long_click_loop_level2_ID != -100){
                        android.util.Log.i("zukgitXA"," 播放完成回调 相同子项 long_click_loop_level2_ID"+long_click_loop_level2_ID);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口8  ");

                    }else{
                        if(cur_node_id != last_click_level3_ID){  //  如果是 相同的 id 点击
                            isRandomPlayMusic = false;

                            NodeImpl nextNode = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                            if(nextNode != null){
                                next_click_level3_ID = (int)nextNode.id();
                                next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                            }

                            long_click_loop_level2_ID = -100;
                            isLevel3_Loop = false;
                            android.util.Log.i("zukgitXA"," 取消循环  取消 播放完成回调 不同子项 level_2node 长按 last_click_level3_ParentID="+last_click_level3_ParentID+"   pre_last_click_level3_parentId="+pre_last_click_level3_parentId+"  cur_long_ParentID="+cur_long_ParentID+"  long_click_loop_level2_ID="+long_click_loop_level2_ID+" 不相同 !!");
//                    mMultiLevelPickerWindow.dismiss();
                            mMultiLevelPickerWindow.update();
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口9  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  nextNode="+nextNode);

                        }
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口10  ");

                    }
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口11  ");


                } else {
                    NodeImpl nextNode = DataHolder.getNextSongId(selectedChild.id(),selectedChild.parentid());
                    next_click_level3_ID = (int)nextNode.id();
                    next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextNode.id(),nextNode.parentid);


                    String nodeName = DataHolder.getSongNameWithID(next_click_level3_ID);
                    android.util.Log.i("zukgitXA","X112_B 循环循环循环循环播放 播放完成回调  nodeName="+nodeName+"  isRandomPlayMusic="+isRandomPlayMusic+"  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口12  ");

                }

//                android.util.Log.i("zukgitXA","X112_C 播放完成回调 mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口13  ");

                if(last_click_level3_ID == -100){

                    playerMusic(mediaPlayer,mp3path,0);
                    mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    android.util.Log.i("zukgitXA","C5_mediaPlayer  X1 开始播放文件 ==  "+mp3path +"  播放它");
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口14  ");

                }else  if(last_click_level3_ID != curID){     //   如果 点击了 不同的 text 那么说明是 需要 另外 切换歌曲
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口23  ");


                    long_click_loop_level3_ID = -100;  // 切换了歌曲 就相当于 把 循环取消掉了
                    mediaPlayer.setLooping(false);

                    android.util.Log.i("zukgitXA","X0 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                    mMultiLevelPickerWindow.update();
                    int pos = ( currentMP3_Track_Position==currentMP3_Track_Position_ScreenOff && currentMP3_Track_Position_ScreenOff != -100)?currentMP3_Track_Position:0;

                    if(currentMP3_Track_Position < 0 && currentMP3_Track_Position_ScreenOff < 0){
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口24  ");

                        if(offScreenMediaPlayer.getCurrentPosition() > 0 && isScreenMediaPlayerBegin){
                            playerMusic(mediaPlayer,mp3path,offScreenMediaPlayer.getCurrentPosition());
                            pauseMusic(offScreenMediaPlayer);
                            android.util.Log.i("zukgitXA","C6_mediaPlayer click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口25  ");

                        }else{
                            playerMusic(mediaPlayer,mp3path,0);
                            pauseMusic(offScreenMediaPlayer);
                            android.util.Log.i("zukgitXA","C7_mediaPlayer click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口26  ");

                        }

                        android.util.Log.i("zukgitXA","X2 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口27  ");

                    }else if(currentMP3_Track_Position > currentMP3_Track_Position_ScreenOff && currentMP3_Track_Position_ScreenOff >= 0 ){
                        pauseMusic(offScreenMediaPlayer);
                        playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口28  ");

                        android.util.Log.i("zukgitXA","C8_mediaPlayer X1 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

                    }else{
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口29  ");

                        if(!isMediaPlayerPlaying){  // 没有播放
                            pauseMusic(offScreenMediaPlayer);
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position_ScreenOff);
                            android.util.Log.i("zukgitXA","C9*1 _mediaPlayer X3 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口30  ");

                        }else if(!isScreenMediaPlayerBegin){
                            pauseMusic(offScreenMediaPlayer);
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                        }
                        android.util.Log.i("zukgitXA","C9_mediaPlayer X3 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  cur_long_ParentID="+cur_long_ParentID+"  long_click_loop_level2_ID="+long_click_loop_level2_ID+"   DataHolder.isAPPInBackground="+DataHolder.isAPPInBackground);
                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口31  ");

                    }

                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口32  ");

                    android.util.Log.i("zukgitXA","X4 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                    mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    currentMP3_Track_Position_ScreenOff = -100; //只使能一次

                }


                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口33  ");


                // zukgit  显示路径 MP3PATH  Toast
                //  Toast.makeText(mContext.getApplicationContext(),mp3path,Toast.LENGTH_SHORT).show();

                if(isScreenMediaPlayerPlaying){
                    pauseMusic(offScreenMediaPlayer);
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口34  ");

                }
                last_click_level3_ID = (int)selectedChild_level_3.id();
                last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口35  last_click_level3_ID ="+last_click_level3_ID);
                android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node_3 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

            }
            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口36  ");

            if(isScreenMediaPlayerPlaying){
                pauseMusic(offScreenMediaPlayer);
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口37  ");

            }
            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口38  ");

            isScreenMediaPlayerBegin = false;

            android.util.Log.i("zukgitNode_Click"," ______________________________________________  All_Node 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

        });

/*  //          如果 当前的 id 就是 next_click_level3_ID
        if(isRandomPlayMusic && next_click_level3_ID != -100 && next_click_level3_ID == cur_node_long_id && holder.tvName != null   ){
            mNext_Level3_TextView = holder.tvName;
            next_click_level3_ID_ViewPosition = position;

            android.util.Log.i("zukgitXAsa_random_true"," 播放完成回调  isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());

        }else{

        }*/

        if(last_click_level3_ID != -100 && last_click_level3_ID== cur_node_id && holder.tvName != null){

            if(!isLevel3_Loop){
                holder.tvName.setBackgroundResource(item.color());

            }
            android.util.Log.i("zukgitXAsa_random_true"," 1AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());
            NodeImpl selectedNode =  (NodeImpl)holder.tvName.getTag();

        }

        if(isRandomPlayMusic && long_click_loop_level2_ID != -100 && long_click_loop_level2_ID== cur_node_id && holder.tvName != null){

            holder.tvName.setBackgroundResource(R.color.color_india_red);
            android.util.Log.i("zukgitXAsa_random_true_2"," 2AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());

        }

        if(isRandomPlayMusic && next_click_level3_ID_ViewPosition != -1 && next_click_level3_ID_ViewPosition == position && holder != null  && holder.tvName != null){
            holder.tvName.setSelected(true);

        }
        if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
            holder.tvName.setBackgroundResource(R.color.color_india_red);

        }



        if(item.level() == 3 && item.id() != last_click_level3_ID){
            holder.tvName.setBackgroundResource(R.color.transparent);
            holder.tvName.setSelected(false);
            android.util.Log.i("zukgitXA"," BindColor___X0  holder.tvName="+ holder.tvName.getText().toString());

        }else  if(item.level() == 3 && item.id() == last_click_level3_ID){
            if(long_click_loop_level3_ID == last_click_level3_ID){  // 长按颜色
                holder.tvName.setBackgroundResource(R.color.color_india_red);
                android.util.Log.i("zukgitXA"," BindColor___X0_1  holder.tvName="+ holder.tvName.getText().toString());

            }else {   // 自身颜色
                android.util.Log.i("zukgitXA"," BindColor___X1  holder.tvName="+ holder.tvName.getText().toString());
                if(item.color() != -100 &&  item.color()  != 0){
                    android.util.Log.i("zukgitXA"," BindColor___X2  holder.tvName="+ holder.tvName.getText().toString() +"  item.color()="+item.color());
                    holder.tvName.setBackgroundResource(item.color());   // 滑动时 不变 使用
//                    holder.tvName.setBackgroundResource(ZUtil.getRainbowColor_random());  // 回前台 下一个 歌曲使用
//                    item.setcolor(ZUtil.getRainbowColor_random());

                }else{
                    android.util.Log.i("zukgitXA"," BindColor___X3    holder.tvName="+ holder.tvName.getText().toString() +"  last_click_level3_ID="+ last_click_level3_ID);
                    item.setcolor(ZUtil.getRainbowColor_random());
                    holder.tvName.setBackgroundResource(item.color());
                }

            }
            holder.tvName.setSelected(false);
        }

    }


}
