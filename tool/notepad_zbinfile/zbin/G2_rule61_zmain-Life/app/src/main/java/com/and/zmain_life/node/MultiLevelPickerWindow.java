package com.and.zmain_life.node;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.DataHolder;

import java.util.List;
import utils.ZUtil;

public class MultiLevelPickerWindow<T extends Node> extends PopupWindow {

    public RecyclerView rv1, rv2, rv3;
    int mBGColorId;

    static String randomSongText;
    static long randomSong_level3Node_ID = -1;

    static int CurViewPosition;

    private int selectedLevel = -1;
    private T selectedItem = null;
    static NodeImpl selected_level3_NodeImpl;

    public MultiLevelItemAdapter<T> mAdapter1, mAdapter2, mAdapter3;

    /**
     * 储存已选择的数据
     */
    private long[] storage = new long[]{-1L/* 全部 */, -1L, -1L};


    public void showAdapterNode3StaticInfo(String tag) {
        if(mAdapter3 != null){
            mAdapter3.showStaticProp(tag);
        }

    }
    public void Refresh_Level3_Adapter() {
        MultiLevelItemAdapter  mAdapter3_Next = new MultiLevelItemAdapter<>(null, 2, mcontext, this);

       MultiLevelItemAdapter_Recovery_StaticData(mAdapter3,mAdapter3_Next);
        mAdapter3 = mAdapter3_Next;
        rv3.setAdapter(mAdapter3);
        mAdapter3.setOnSelectListener((parent, selectedChild) -> {
            // 1 数据记录工作
            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 2;
            storage[2] = selectedChild.id();

            // 2 刷新父节点
            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter3.setNewData((T) parent);
        });
    }


    public void  MultiLevelItemAdapter_Recovery_StaticData(MultiLevelItemAdapter<T> oldAdapter , MultiLevelItemAdapter<T> newAdapter ){
        oldAdapter.showStaticProp("oldAdapter-【旧Adapter】");
        newAdapter.showStaticProp("newAdapter-【新Adapter】");
        newAdapter.last_click_level1_ID = oldAdapter.last_click_level1_ID;
        newAdapter.last_click_level2_ID = oldAdapter.last_click_level2_ID;
        newAdapter.last_click_level3_ID = oldAdapter.last_click_level3_ID;
        newAdapter.lastMp3Path = oldAdapter.lastMp3Path;
        newAdapter.last_click_level3_ParentID = oldAdapter.last_click_level3_ParentID;
        newAdapter.last_click_level3_ID_ViewPosition = oldAdapter.last_click_level3_ID_ViewPosition;
        newAdapter.next_click_level3_ID = oldAdapter.next_click_level3_ID;
        newAdapter.next_click_level3_ID_ViewPosition = oldAdapter.next_click_level3_ID_ViewPosition;
        newAdapter.long_click_loop_level2_ID = oldAdapter.long_click_loop_level2_ID;
        newAdapter.long_click_loop_level2_ID_Name = oldAdapter.long_click_loop_level2_ID_Name;

        newAdapter.isLevel3_Loop = oldAdapter.isLevel3_Loop;
        newAdapter.long_click_loop_level3_ID = oldAdapter.long_click_loop_level3_ID;
        newAdapter.isRandomPlayMusic = oldAdapter.isRandomPlayMusic;
        newAdapter.isMediaPlayerLoop = oldAdapter.isMediaPlayerLoop;
        newAdapter.isMediaPlayerPlaying = oldAdapter.isMediaPlayerPlaying;

        newAdapter.isScreenMediaPlayerBegin = oldAdapter.isScreenMediaPlayerBegin;
        if(newAdapter.mediaPlayer != null){
            newAdapter.mediaPlayer = oldAdapter.mediaPlayer;
        }

        if(newAdapter.offScreenMediaPlayer != null){
            newAdapter.offScreenMediaPlayer = oldAdapter.offScreenMediaPlayer;
        }

    }

/*    public void  showStaticProp(String tag){
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



    }*/


    public void unRegisterScreenOff_OnCompleteListener(){
        android.util.Log.i("zukgitXA","—————————————————————————————————————— unregisterScreenLister_level3 事件开始AA—————————————————————————————————————— ");
        mAdapter3.mediaPlayer.setOnCompletionListener(mAdapter3.onCompletionListener);
        if(mAdapter3.isScreenMediaPlayerPlaying){
           int curOffPosition =  mAdapter3.offScreenMediaPlayer.getCurrentPosition();
            mAdapter3.pauseMusic(mAdapter3.offScreenMediaPlayer);
            mAdapter3.pauseMusic(mAdapter3.mediaPlayer);
            mAdapter3.playerMusic(mAdapter3.mediaPlayer,mAdapter3.lastMp3Path,curOffPosition);
            android.util.Log.i("zukgitXA","【 unregisterScreenLister_level3 事件】___起点1  curOffPosition="+curOffPosition+"  mAdapter3.lastMp3Path="+mAdapter3.lastMp3Path);

        }
//        mAdapter3.offScreenMediaPlayer.setOnCompletionListener(mAdapter3.OffScreen_onCompletionListener);
        android.util.Log.i("zukgitXA","—————————————————————————————————————— unregisterScreenLister_level3 事件结束—————————————————————————————————————— ");

    }

    public void registerScreenOff_OnCompleteListener(){
        android.util.Log.i("zukgitXA","—————————————————————————————————————— unregisterScreenLister_level3 事件开始AA—————————————————————————————————————— ");
        mAdapter3.mediaPlayer.setOnCompletionListener(mAdapter3.OffScreen_onCompletionListener);
//        mAdapter3.offScreenMediaPlayer.setOnCompletionListener(mAdapter3.OffScreen_onCompletionListener);

        android.util.Log.i("zukgitXA","—————————————————————————————————————— unregisterScreenLister_level3 事件结束—————————————————————————————————————— ");

    }


    public MultiLevelPickerWindow(Context context , int colorId) {
        mBGColorId = colorId;
        init(context);
    }

    private View mRootView;
    Context mcontext;

    public void   screenToPosition_Level3( ){

    }

    public synchronized void  screenToPosition_Level3(int position ,NodeImpl selectedNode ){
        android.util.Log.i("zukgitXA","—————————————————————————————————————— screenToPosition_Level3 事件开始—————————————————————————————————————— ");
        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_1  position="+position+"  selectedNode="+selectedNode.toString());
        selected_level3_NodeImpl = selectedNode;
        NodeImpl parentNode = DataHolder.get3level_parentlevel2(selectedNode.id());
        if(parentNode != null){
            parentNode.setSelectedChild(selectedNode.id());
            update();
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_2");
        }

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv3.getLayoutManager();
        int firstPosition =   linearLayoutManager.findFirstVisibleItemPosition();
        int secondPosition  =   linearLayoutManager.findLastVisibleItemPosition();
        int first_position_computer  = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int second_position_computer  = linearLayoutManager.findLastCompletelyVisibleItemPosition();


        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_2x1   DataHolder.isAPPInBackground="+DataHolder.isAPPInBackground+"   mAdapter2.long_click_loop_level2_ID="+mAdapter2.long_click_loop_level2_ID+"  mAdapter2.long_click_loop_level2_ID="+mAdapter2.long_click_loop_level2_ID);
        CurViewPosition = position;
        randomSong_level3Node_ID = -1;
        randomSongText = null;
        rv3.setOnScrollChangeListener(null);
        if(!DataHolder.isAPPInBackground ){  // 前台
            rv3.setOnScrollChangeListener(null);
            rv3.setOnScrollChangeListener(mScrollListener);
        }

        Log.i("zukgit","【screenToPosition_Level3】---> position="+position+"  selectedNode="+selectedNode.toString() + " secondPosition ="+secondPosition+"  firstPosition="+firstPosition);
        // 如果当前的 循环 页面 在 后台  那么就不滑动了    或者 在可见 区域 就不滑动了
        if(!DataHolder.isAPPInBackground ||  (mAdapter2.long_click_loop_level2_ID != mAdapter2.last_click_level2_ID && mAdapter2.long_click_loop_level2_ID != -100) ){
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_2 DataHolder.isAPPInBackground="+DataHolder.isAPPInBackground +" mAdapter2.long_click_loop_level2_ID= "+mAdapter2.long_click_loop_level2_ID+"  mAdapter2.last_click_level2_ID="+mAdapter2.last_click_level2_ID);
            rv3.postDelayed(new Runnable() {

                @Override

                public void run() {

                    if(!DataHolder.isAPPInBackground ){
                        rv3.smoothScrollToPosition(position);
                        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_3A_runui   position="+position);

                    }

                    android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_3B_runui   position="+position);

                    rv3.getAdapter().notifyDataSetChanged();

                }

            }, 1000);

        }




        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_4 ");

        android.util.Log.i("zukgit_screenToPosition_Level3"," 播放完成回调执行 position = "+position);



        if(position >= firstPosition && position <=secondPosition && !mAdapter3.isScreenMediaPlayerBegin ){
            android.util.Log.i("zukgit_screenToPosition_Level3","  播放完成回调执行 mAdapter3.offScreenMediaPlayer.release()  可见区域");
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_5   position="+position+"   firstPosition="+firstPosition+" mAdapter3.isScreenMediaPlayerBegin ="+mAdapter3.isScreenMediaPlayerBegin);
            View viewitem  = rv3.getChildAt(position );
            MultiLevelItemAdapter.VHolder vholder =  (MultiLevelItemAdapter.VHolder)rv3.findViewHolderForAdapterPosition(position);
            int curCalPosition = position - firstPosition+1 ;
            if(vholder == null){
                android.util.Log.i("zukgit_clickWithPosition","XX11- vholder为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition);
                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_6 ");
            }else{
                android.util.Log.i("zukgit_clickWithPosition","CCCXXX XX11- vholder不为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition+" vholder.tvName.toString() ="+ vholder.tvName.getText());
                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_7 ");

                if(    vholder.tvName != null ){
                    T selectedChild = (T) vholder.tvName.getTag();
                    String songName =  vholder.tvName.getText().toString();
                    android.util.Log.i("zukgit_clickWithPosition","CCCXXX XX11- vholder不为空 进入循环   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition+" vholder.tvName.toString() ="+ vholder.tvName.getText());


                        randomSongText = vholder.tvName.getText().toString();
                        // 如果 后台 能播放   那么就和 mScreenMediaPlayer 重合了
                    NodeImpl curSelectedNode =    (NodeImpl)vholder.tvName.getTag();
                    Log.i("zukgit","【TagNodeImpl】--->  curSelectedNode="+curSelectedNode.toString());
                    android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_8    tagNodeImple="+curSelectedNode.toString());
                    if(!mAdapter3.isLevel3_Loop){  // 如果 是 循环 那么 不动它
                            Log.i("zukgit","  mAdapter3.isLevel3_Loop="+mAdapter3.isLevel3_Loop);
                            randomSong_level3Node_ID = curSelectedNode.id();
                        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_9   mAdapter3.isLevel3_Loop="+mAdapter3.isLevel3_Loop+"   randomSong_level3Node_ID="+randomSong_level3Node_ID);

                        if(!DataHolder.isAPPInBackground && selected_level3_NodeImpl.id() == selectedChild.id() && mAdapter3.last_click_level3_ID != selectedChild.id()){



                            // 对 点击 惊醒 保护
                            if(System.currentTimeMillis() - Screen_Click_TimeStamp > ScreenDistance_Click_Time ){
                                vholder.tvName.performClick();
                                Screen_Click_TimeStamp = System.currentTimeMillis();
                                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_9_1  主动点击事件  vholder.tvName.performClick(); ");
                            }



                            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_10  主动点击事件  vholder.tvName="+ vholder.tvName.getText().toString());

                          }
                        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_11 ");
                        }



                    android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_12 ");



                        randomSong_level3Node_ID= selectedChild.id();
                        android.util.Log.i("zukgit_clickWithPosition"," 执行滑动点击 歌曲="+randomSongText+" 可见区域   mAdapter3.offScreenMediaPlayer.release()  可见区域");

                    android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_13 ");


                    rv3.clearOnScrollListeners();

                }
                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_14 ");


            }
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_15 ");

            if(viewitem == null ){
                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_16 ");

                android.util.Log.i("zukgit_clickWithPosition","XX11- viewitem为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition);
                android.util.Log.i("zukgitXA","—————————————————————————————————————— screenToPosition_Level3 事件结束_End_1 —————————————————————————————————————— ");

                return;

            }else{
                android.util.Log.i("zukgit_clickWithPosition","XX11  viewitem不为空  滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition);
                android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_17 ");

            }

            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_18 ");


            android.util.Log.i("zukgitCCC","XX13 screenToPosition_Level3 ");
            rv3.clearOnScrollListeners();
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_19 ");

        }
        else {
            android.util.Log.i("zukgit_clickWithPosition"," 回调函数   不可见区域");
            android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_20 ");

            rv3.clearOnScrollListeners();


        }

        android.util.Log.i("zukgitXA","【 screenToPosition_Level3 】 ______入口_21 ");




        android.util.Log.i("zukgitXA","—————————————————————————————————————— screenToPosition_Level3 完整_Over —————————————————————————————————————— ");


    }

    static long ScreenDistance_Click_Time  = 10000;
    static long Screen_Click_TimeStamp  ;



    View.OnScrollChangeListener mScrollListener =   new View.OnScrollChangeListener() {
        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    android.util.Log.i("zukgit_clickWithPosition","scrollX = "+ scrollX + "  scrollY="+scrollY+"  oldScrollX="+oldScrollX+"  oldScrollY="+oldScrollY);

            android.util.Log.i("zukgit_clickWithPosition","滑动点击事件AABB  randomSong_level3Node_ID="+randomSong_level3Node_ID+"  CurViewPosition = "+ CurViewPosition);
            android.util.Log.i("zukgitXA","—————————————————————————————————————— OnScrollChangeListener 监听滑动函数 Begin —————————————————————————————————————— ");
            android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点1  randomSong_level3Node_ID="+randomSong_level3Node_ID +"  selected_level3_NodeImpl="+selected_level3_NodeImpl);
/*            if(randomSong_level3Node_ID != -1 ){
                android.util.Log.i("zukgitXA","—————————————————————————————————————— OnScrollChangeListener 监听滑动函数 End(1) —————————————————————————————————————— ");

                return ;
            }*/
            android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点2");
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv3.getLayoutManager();
            int firstPosition =   linearLayoutManager.findFirstVisibleItemPosition();
            int secondPosition  =   linearLayoutManager.findLastVisibleItemPosition();
            int first_position_computer  = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            int second_position_computer  = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            android.util.Log.i("zukgit_clickWithPosition","滑动点击事件AA  randomSong_level3Node_ID="+randomSong_level3Node_ID+"  CurViewPosition = "+ CurViewPosition +" firstPosition ="+firstPosition+"   secondPosition="+secondPosition);



//                    android.util.Log.i("zukgit_clickWithPosition","XX4  screenToPosition_Level3  firstPosition="+firstPosition+"  secondPosition="+secondPosition+" position= "+position +"  first_position_computer ="+first_position_computer+"  second_position_computer="+second_position_computer);


            if(CurViewPosition >= firstPosition && CurViewPosition <=secondPosition && CurViewPosition >= 0 ){
                View viewitem  = rv3.getChildAt(CurViewPosition );
                android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点3  CurViewPosition="+CurViewPosition+"  firstPosition="+firstPosition+"   secondPosition="+secondPosition);
                MultiLevelItemAdapter.VHolder vholder =  (MultiLevelItemAdapter.VHolder)rv3.findViewHolderForAdapterPosition(CurViewPosition);
                int curCalPosition = CurViewPosition - firstPosition+1 ;
                if(vholder == null){
                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点4  mAdapter3.last_click_level2_ID");

                    android.util.Log.i("zukgit_clickWithPosition","XX11- vholder为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition  +"  mAdapter3.long_click_loop_level2_ID="+mAdapter3.long_click_loop_level2_ID+"   mAdapter3.last_click_level2_ID="+mAdapter3.last_click_level2_ID);

                }else{  // 只有 adapter 相同 才会执行 click 否则  找不到 item     不循环 走不到这里
                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点4_1    滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition  +"  mAdapter3.long_click_loop_level2_ID="+mAdapter3.long_click_loop_level2_ID+"   mAdapter3.last_click_level2_ID="+mAdapter3.last_click_level2_ID  +"   mAdapter3.last_click_level2_ID="+mAdapter3.last_click_level2_ID+"   mAdapter3.last_click_level3_ID="+mAdapter3.last_click_level3_ID+"    DataHolder.get3level_parentlevel2(mAdapter3.last_click_level3_ID)="+ DataHolder.get3level_parentlevel2(mAdapter3.last_click_level3_ID));

                    NodeImpl level2_node_selected =   DataHolder.get3level_parentlevel2(mAdapter3.last_click_level3_ID);
                    if(vholder.tvName != null && (mAdapter3.long_click_loop_level2_ID == mAdapter3.last_click_level2_ID || ((level2_node_selected != null ? level2_node_selected.id() : -1)==  mAdapter3.last_click_level2_ID) )){  // 随机循环时 进入
                        T selectedChild = (T) vholder.tvName.getTag();
                        android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点5");

                        String songName =  vholder.tvName.getText().toString();
                        android.util.Log.i("zukgit_clickWithPosition","CCBBB  XX11- vholder不为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition+" vholder.tvName.toString() ="+ vholder.tvName.getText());

                            randomSongText = vholder.tvName.getText().toString();
                            String tempMp3PATH = selectedChild.mp3path();
                            randomSong_level3Node_ID =   selectedChild.id();
                        android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点6    selectedChild="+selectedChild.toString() +"  mAdapter3.isScreenMediaPlayerPlaying="+mAdapter3.isScreenMediaPlayerPlaying);
                          // 如果 已经 出发 了 自己 那么就不再 点击 了
                            if(!DataHolder.isAPPInBackground && selected_level3_NodeImpl != null && selected_level3_NodeImpl.id() == selectedChild.id() && mAdapter3.last_click_level3_ID != selectedChild.id() && !mAdapter3.isScreenMediaPlayerPlaying){

                                // 对 点击 惊醒 保护   对于 来回 切换的 需要播放的 xxxxxxxx
                                if(System.currentTimeMillis() - Screen_Click_TimeStamp > ScreenDistance_Click_Time  ){
                                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点6_1 主动滑动点击  vholder.tvName="+vholder.tvName.getTag().toString() +"  mAdapter3.isScreenMediaPlayerPlaying="+mAdapter3.isScreenMediaPlayerPlaying);
                                    vholder.tvName.performClick();
                                    Screen_Click_TimeStamp = System.currentTimeMillis();
                                }

                                rv3.setOnScrollChangeListener(null);
                                randomSong_level3Node_ID = 1;
                                Log.i("zukgit"," 滑动主动点击事件BBBB  selectedChild.id()="+selectedChild.id());
                                android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点7  主动点击事件  vholder.tvName="+vholder.tvName.getText().toString());


                                android.util.Log.i("zukgitXA","—————————————————————————————————————— OnScrollChangeListener 监听滑动函数 End(2) —————————————————————————————————————— ");

                                return;

                        }
                        android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点8_1");
                        rv3.setOnScrollChangeListener(null);

                    }else if( selected_level3_NodeImpl != null && selected_level3_NodeImpl.index4parent == 0 && mAdapter3.long_click_loop_level2_ID < 0){
                        android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点8_2  作者歌曲循环结束了 !!");
                        if(vholder.tvName != null){
                            rv3.setOnScrollChangeListener(null);
                            vholder.tvName.performClick();
                            Screen_Click_TimeStamp = System.currentTimeMillis();
                            android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点8_3  作者歌曲循环结束了 退出方法  播放第一首歌 !!");
                            return;
                        }

                    }
                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点9");


                }
                android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点10");

                if(viewitem == null ){
                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点11");

                    android.util.Log.i("zukgit_clickWithPosition","XX11- viewitem为空   滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition);
                    android.util.Log.i("zukgitXA","—————————————————————————————————————— OnScrollChangeListener 监听滑动函数 End(3) —————————————————————————————————————— ");

                    return;
                }else{
                    android.util.Log.i("zukgit_clickWithPosition","XX11  viewitem不为空  滑动监听 screenToPosition_Level3 curCalPosition="+curCalPosition);
                    android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点12");

                }
                android.util.Log.i("zukgit_clickWithPosition","XX11 取消 滑动监听 screenToPosition_Level3 ");
                android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点13");


                android.util.Log.i("zukgitCCC","XX13 screenToPosition_Level3 ");
                rv3.clearOnScrollListeners();
            }
            android.util.Log.i("zukgitXA","【 OnScrollChangeListener 滑动监听事件】 ________点14");

            android.util.Log.i("zukgitXA","—————————————————————————————————————— OnScrollChangeListener 监听滑动函数 End_Over —————————————————————————————————————— ");

        }

    };

  void  postSelectedIdFromNextMusic(long level1 , long level2 , long level3){

    }

    private void init(final Context context) {
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______init() Begin════════════════════════════");

        mRootView = LayoutInflater.from(context).inflate(
                R.layout.mlp_window_picker, null);
        mcontext = context;
        buildView(mRootView);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点A ");
        this.setOnDismissListener(() -> {
            if (mListener != null) {
                mListener.onDismiss();
            }
        });

        this.setContentView(mRootView);
        this.setWidth(context.getResources().getDisplayMetrics().widthPixels);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点B ");

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点C ");

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(android.R.color.transparent));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismissListener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点D ");

        this.setAnimationStyle(R.style.popupAnimation);
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______init() End════════════════════════════");

    }



    public void showLastNode3Impl(){
        if(mAdapter3 != null){
            mAdapter3.notifyDataSetChanged();
            mAdapter3.screenToLastClickNode3Position();
            android.util.Log.i("zukgit", "mAdapter3.notifyDataSetChanged()   mAdapter3.last_click_level3_ID = "+mAdapter3.last_click_level3_ID);

        }

    }

    public void recoveryMusic_Widows_ChangePage(){
        if(mAdapter3 != null){
            mAdapter3.recoveryMusic_ChangePage();
        }

    }

    public void recoveryMusic_Widows(){
        if(mAdapter3 != null){
            mAdapter3.recoveryMusic();
        }

    }


    public void playScreenOffMusic(){
        if(mAdapter3 != null){
            mAdapter3.playScreenOffMusic();
        }

    }

    public void   pauseAndReleaseMusic(){
        if(mAdapter3 != null){
            mAdapter3.mediaPlayer.pause();
            mAdapter3.offScreenMediaPlayer.pause();
        }
    }
    public void   setCurrentClickNode3Id( int node3_id ){
        if(mAdapter3 != null){
                mAdapter3.last_click_level3_ID =node3_id;
        }
    }



    public int   getCurrentClickNode3Id(){
        if(mAdapter3 != null){
        return    mAdapter3.last_click_level3_ID;
        }
        return -100;
    }

    public void pauseScreenOffMusic(){
        if(mAdapter3 != null){
            mAdapter3.pauseScreenOffMusic();
        }

    }

    public void pauseMusic_Widows(){
        if(mAdapter3 != null){
            mAdapter3.pauseMusic_ChangePage();
        }

    }

    public void PlayOrPauseMusic(){
       if(isPalyingMusic()){
           pauseMusic_Widows();
       }else{
           recoveryMusic_Widows();
       }
    }


    public void PlayNextMusic(){
        if(mAdapter3 != null){
            mAdapter3.PlayNextMusic();
        }
    }





    public boolean isPalyingMusic(){
        boolean isPlayMusic = false;
        if(mAdapter3 != null){
            isPlayMusic =    mAdapter3.isPalyingMusic();
        }

//        return isPlayMusic;
       return    DataHolder.isMP3Page_TitleMusic_Play_whiteTrue_blackFalse ;

    }

    private void buildView(View view) {
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______buildView() Begin════════════════════════════");

        rv1 = view.findViewById(R.id.rv_1);
        rv2 = view.findViewById(R.id.rv_2);
        rv3 = view.findViewById(R.id.rv_3);

         rv3.setOnScrollChangeListener(mScrollListener);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点1 ");
/*        rv1.setBackgroundColor(mBGColorId);
        rv2.setBackgroundColor(mBGColorId);
        rv3.setBackgroundColor(mBGColorId);*/
        mAdapter1 = new MultiLevelItemAdapter<>(null, 0,mcontext,this);
        rv1.setAdapter(mAdapter1);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点2 ");

        mAdapter1.setOnSelectListener((parent, selectedChild) -> {

            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 0;
            storage[0] = selectedChild.id();
            storage[1] = -1;
            storage[2] = -1;


            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter1.setNewData((T) parent);

            if (selectedChild.id() != mDefaultRootId) {//是全部的话 后面2级不展示啦
                selectedChild.setSelectedChild(-1);
                //noinspection unchecked
                mAdapter2.setNewData((T) selectedChild);
            } else {
                mAdapter2.setNewData(null);
            }
            mAdapter3.setNewData(null);
        });

        mAdapter2 = new MultiLevelItemAdapter<>(null, 1,mcontext,this);
        rv2.setAdapter(mAdapter2);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点3 ");

        mAdapter2.setOnSelectListener((parent, selectedChild) -> {
            // 1 数据记录工作
            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 1;
            storage[1] = selectedChild.id();
            storage[2] = -1;

            // 2 刷新父节点
            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter2.setNewData((T) parent);

            // 3 展示子树
            selectedChild.setSelectedChild(-1);
            //noinspection unchecked
            mAdapter3.setNewData((T) selectedChild);
        });

        mAdapter3 = new MultiLevelItemAdapter<>(null, 2,mcontext,this);
        rv3.setAdapter(mAdapter3);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点4 ");

        mAdapter3.setOnSelectListener((parent, selectedChild) -> {
            // 1 数据记录工作
            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 2;
            storage[2] = selectedChild.id();

            // 2 刷新父节点
            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter3.setNewData((T) parent);
        });
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点5 ");


/*        view.findViewById(R.id.btnclose).setOnClickListener(v -> dismiss());
        view.findViewById(R.id.btndo).setOnClickListener(v -> {
            callbackSelected(false);
            MultiLevelPickerWindow.this.dismiss();
        });*/
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______buildView() End════════════════════════════");

    }

    /**
     * 更新节点数据, 并采用降级策略
     * 降级策略:
     * 被选择级别节点被删除则选中它的上一级选中节点, 一级节点丢失, 则选中"全部"(即id为0的那项)
     * 比如:
     * 1 被选择的三级节点被删除(或三级菜单丢失),选中二级菜单
     * 2 被选择的二级节点被删除(或二级菜单丢失),选中一级菜单
     * 2 被选择的一级节点被删除(或二级菜单丢失),选中一级菜单的"全部"(要是全部节点也没有? 就恢复无选择状态)
     *
     * @return 是否发生节点变更(节点丢失, backup策略启动)
     */
    public boolean updateData(T t) {
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() Begin════════════════════════════");

        android.util.Log.i("zukgit","   selectedLevel3  A20 =");
        boolean isDownGraded = false;
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点1 ");

        if (t == null) {
            mAdapter1.setNewData(null);
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A18 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点2 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_1 ════════════════════════════");

            return false;
        }
        /* ***************** 1级 ***************** */
        // 1级数据监测与校准
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点3 ");

        if (storage[0] < 0) {
            // 第一次进来 或 没选择过
            mAdapter1.setNewData(t);
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A19 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点4 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_2 ════════════════════════════");

            return false;
        }
        // 从内存中读取上次选中的一级菜单ID
        t.setSelectedChild(storage[0]);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点5 ");

        // 尝试获取上次选中的一级目录节点
        //noinspection unchecked
        @Nullable T selectedLevel1 = (T) t.getSelectedChild();
        //noinspection StatementWithEmptyBody
        if (selectedLevel1 != null) { // 找到了上次选中的节点
            // as normal. do nothing. 不用给selectedItem赋值
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点6 ");

        } else { // 没找到上次选中的一级节点
            isDownGraded = true;
            storage[0] = mDefaultRootId; // 切到"全部"
            t.setSelectedChild(storage[0]);
            // 重点: 当原来选择的一级目录被删除后, 选择"全部"
            //noinspection unchecked
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点7 ");

            T all = (T) t.getSelectedChild();
            if (all != null) { // 找到了全部
                selectedItem = all;
                selectedLevel = 0;
                callbackSelected(true);
                storage[0] = all.id();
                storage[1] = -1;
                storage[2] = -1;
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点8 ");

            } else { // 没找到全部, 降级到未选择状态
                selectedItem = null;
                selectedLevel = -1;
                callbackSelected(true);
                storage[0] = -1;
                storage[1] = -1;
                storage[2] = -1;
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点9 ");

            }
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点10 ");

        }
        mAdapter1.setNewData(t);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点11 ");

        if (isDownGraded) {

            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A17 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点12 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_3 ════════════════════════════");

            return true;
        }
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点13 ");

        /* ***************** 2级 ***************** */
        //noinspection unchecked
        List<T> secondList = (List<T>) t.children();
        if (secondList == null) {
            //noinspection StatementWithEmptyBody
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点14 ");

            if (selectedLevel > 0) {// 说明之前选中的二或三级目录被删除了
                isDownGraded = true; // useless, but for good reading.
                selectedItem = selectedLevel1;
                selectedLevel = 0;
                callbackSelected(true);
                storage[0] = selectedItem.id();
                storage[1] = -1;
                storage[2] = -1;
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点15 ");

            } else {
                // 之前选中的二级目录没被删, 本来就是选的一级目录，都不需要通知外界
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点15 ");

            }
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A16 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点16 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_4 ════════════════════════════");

            return isDownGraded;
        }
        if (storage[1] < 0) { // 表示本来就没选择二级菜单
         //    mAdapter2.setNewData(null);   // 返回时  第二级 菜单  仍然显示操作
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A15 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点17 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_5 ════════════════════════════");

            return false;
        }
        //noinspection UnnecessaryLocalVariable
        T second = selectedLevel1; // 二级菜单的父节点
        second.setSelectedChild(storage[1]);
        //noinspection unchecked
        T selectedLevel2 = (T) second.getSelectedChild(); // 尝试获取上次选择的二级菜单节点
        //noinspection StatementWithEmptyBody
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点18 ");

        if (selectedLevel2 != null) {
            // 之前选中的二级目录没被删, 且找到了.(有可能新增了菜单项, 且index也变了)
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点19 ");

        } else {
            // 之前的二级目录被删除
            isDownGraded = true; // useless, but for good reading.
            selectedItem = selectedLevel1;
            selectedLevel = 0;
            callbackSelected(true);
            storage[0] = selectedItem.id();
            storage[1] = -1;
            storage[2] = -1;
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit","   selectedLevel3  A14 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点20 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_6 ════════════════════════════");

            return true;
        }
        mAdapter2.setNewData(second);
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点21 ");

        /* ***************** 3级 ***************** */
        //noinspection unchecked
        List<T> thirdList = (List<T>) second.children();
        if (thirdList == null) {
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点22 ");

            //noinspection StatementWithEmptyBody
            if (selectedLevel > 1) {// 说明之前选中的三级目录被删除了
                isDownGraded = true; // useless, but for good reading.
                selectedItem = selectedLevel2;
                selectedLevel = 1;
                callbackSelected(true);
                storage[1] = selectedItem.id();
                storage[2] = -1;
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点23 ");

            } else {
                android.util.Log.i("zukgit","   selectedLevel3  A13 =");
                android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点24 ");

                // 说明本来就没来选三级菜单
            }
            android.util.Log.i("zukgit","   selectedLevel3  A12 =");
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点25 ");

            mAdapter3.setNewData(null);

            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_7 ════════════════════════════");

            return isDownGraded;
        }
        android.util.Log.i("zukgit","   selectedLevel3  A11 =");
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点26 ");

        if (storage[2] < 0) { // 表示本来就没选择三级菜单
//            mAdapter3.setNewData(null);    // 返回时  第三级 菜单  仍然显示操作
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点27 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_8 ════════════════════════════");

            return false;
        }
        //noinspection UnnecessaryLocalVariable
        T third = selectedLevel2; // 三级菜单的父节点
        // 其实到了这里用selectedItem/selectedLevel和storage[2]已经没有区别
        third.setSelectedChild(storage[2]);
        //noinspection unchecked
        T selectedLevel3 = (T) third.getSelectedChild();// 尝试获取上次选择的三级菜单节点
        //noinspection StatementWithEmptyBody
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点28 ");

        if (selectedLevel3 != null) {
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点29 selectedLevel3 ="+selectedLevel3.text());

            mAdapter3.setNewData(third);
            // 之前选中的三级目录没被删, 且找到了.(有可能新增了菜单项, 且index也变了)
            android.util.Log.i("zukgit","   selectedLevel3 ="+selectedLevel3.text());
        } else {
            // 之前选择的三级菜单被删除了
            isDownGraded = true; // useless, but for good reading.
            selectedItem = selectedLevel2;
            selectedLevel = 1;
            callbackSelected(true);
            storage[1] = selectedItem.id();
            storage[2] = -1;
            mAdapter3.setNewData(null);
            android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点30 ");
            android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_9 ════════════════════════════");

            return true;
        }
        android.util.Log.i("zukgit","  selectedLevel3  mAdapter3.setNewData(third) =");
        android.util.Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点31 ");
        android.util.Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_OVer════════════════════════════");

        return false;
    }

    public void callbackSelected_Text(T selectedItem) {
        if (mListener != null) {
            //noinspection unchecked
            android.util.Log.i("zzzz","CC 回调方法  selectedLevel3 ！！！selectedItem.level()=  "+selectedItem.level());
//            mListener.onSelect(3, selectedItem);
            mListener.onSelect(selectedItem.level(), selectedItem,false);
        }

    }


    public void callbackSelected(boolean isDownGraded) {
        if (isDownGraded) {
            updateSelection();
            if (mListener != null) {
                //noinspection unchecked
                mListener.onDownGraded(selectedLevel, selectedItem);
            }
            if (mListener != null) {
                //noinspection unchecked
                android.util.Log.i("zzzz","AA 回调方法  selectedLevel3 ！！！");
                mListener.onSelect(selectedLevel, selectedItem,false);
            }
            return;
        }
        if (mListener != null) {
            //noinspection unchecked
            android.util.Log.i("zzzz","BB 回调方法  selectedLevel3 ！！！");
            mListener.onSelect(selectedLevel, selectedItem,false);
        }
    }

    /**
     * 尝试刷新selectedLevel和selectedItem
     * (其实可以把这部分工作放在updateData中, 但逻辑过于臃肿)
     */
    private void updateSelection() {
        // 更新数据以确保selectedItem的其他值(除id以外的值变更)变更,比如修改了名字, 数量变化等
        T root = mAdapter1.getTree();
        if (root == null) return;// never occur
        if (storage[0] < 0) return; // never occur
        root.setSelectedChild(storage[0]);
        //noinspection unchecked
        T selectedLevel1 = (T) root.getSelectedChild();// 获取被选择的一级节点
        if (selectedLevel1 == null) {
            // 选择了一级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略
            android.util.Log.i("zzzz","A_1 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的一级节点
        if (storage[1] < 0) { // 说明只选了一级节点
            selectedLevel = 0;
            selectedItem = selectedLevel1;
            // 下面三行代码其实可以不用执行, how to say, insurance.
            storage[0] = selectedLevel1.id();
            storage[1] = -1;
            storage[2] = -1;
            android.util.Log.i("zzzz","A_2 updateSelection  selectedLevel3 ！！！");

            return;
        }
        selectedLevel1.setSelectedChild(storage[1]);
        // 代码执行到这句注释时, 说明选择了不止一级菜单(二级、三级 or more)
        //noinspection unchecked
        T selectedLevel2 = (T) selectedLevel1.getSelectedChild();// 获取被选择的二级节点
        if (selectedLevel2 == null) {
            // 选择了二级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略

            android.util.Log.i("zzzz","A_3 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的二级节点
        if (storage[2] < 0) { // 说明只选到了二级节点
            selectedLevel = 1;
            selectedItem = selectedLevel2;
            // 下面两行代码其实可以不用执行, how to say, insurance.
            storage[1] = selectedLevel2.id();
            storage[2] = -1;
            android.util.Log.i("zzzz","A_4 updateSelection  selectedLevel3 ！！！");

            return;
        }
        selectedLevel2.setSelectedChild(storage[2]);
        // 代码执行到这句注释时, 说明选择了不止二级菜单(三级 or more)
        //noinspection unchecked
        T selectedLevel3 = (T) selectedLevel2.getSelectedChild();// 获取被选择的三级节点
        if (selectedLevel3 == null) {
            // 选择了三级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略
            android.util.Log.i("zzzz","A_5 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的三级节点
        selectedLevel = 2;
        selectedItem = selectedLevel3;
        // 下面这行代码其实可以不用执行, how to say, insurance.
        storage[2] = selectedLevel3.id();
        android.util.Log.i("zzzz","A_6 updateSelection  selectedLevel3 ！！！");

    }

    public void update() {
        super.update();

        if(mAdapter1 != null){
            mAdapter1.notifyDataSetChanged();
        }

        if(mAdapter2 != null)
            mAdapter2.notifyDataSetChanged();

        if(mAdapter3 != null)
            mAdapter3.notifyDataSetChanged();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mAdapter1 != null)
        mAdapter1.recoverySelectedTextViewColor();

        if(mAdapter2 != null)
        mAdapter2.recoverySelectedTextViewColor();

        if(mAdapter3 != null)
        mAdapter3.recoverySelectedTextViewColor();
    }

    public void show(View view) {
        if (!this.isShowing()) {
//            PopupWindowCompat.showAsDropDown(this, view, 0, 0, Gravity.END);

//            this.setHeight(ScreenUtil.getScreenHeight(context));//屏幕的高



            int device_height = ZUtil.getRealScreenHeight();
            int device_width = ZUtil.getRealScreenWidth();
            int view_height = view.getHeight();
            int pop_height = device_height - view_height;

            android.util.Log.i("zukigt","  pop_height="+pop_height +" device_height = "+ device_height);
            this.setHeight(pop_height);//屏幕的高
            this.setWidth(device_width);//屏幕的宽

            this.setClippingEnabled(false);

            PopupWindowCompat.showAsDropDown(this, view, 0, 0, Gravity.FILL);

            if (mListener != null)
                mListener.onShow();
        } else {
            this.dismiss();
        }
    }


    public OnSelectListener mListener;

    public void setOnSelectListener(OnSelectListener l) {
        setOnSelectListener(0L, l);
    }

    private long mDefaultRootId = -1;

    public void setOnSelectListener(long defaultRootId, OnSelectListener l) {
        mListener = l;
        mDefaultRootId = defaultRootId;
    }

    public void onBackgroundChanged( ) {
        if (mListener != null) {
            mListener.onBackgroundColorChanged();
        }
    }

    public void onMusicTitlePlay( boolean isPlay ) {
        if (mListener != null) {
            mListener.onMusicPlay(isPlay);
        }
    }

    public void removeSelectListener() {
        if (mListener != null) {
            mListener = null;
        }
    }

    /**
     * 选择性实现方法用的适配器
     */
    public abstract class OnSelectAdapter implements OnSelectListener<T> {

        @Override
        public void onDownGraded(int selectLevel, @Nullable T data) {

        }

        @Override
        public void onShow() {

        }

        @Override
        public void onDismiss() {

        }
    }

    public interface OnSelectListener<T> {
        /**
         * @param selectLevel 被选择的菜单节点所处层级
         * @param data        数据
         */
        void onSelect(int selectLevel, @Nullable T data, boolean isOffScreenMediaPlayer);

        void onBackgroundColorChanged ();

        void onMusicPlay(boolean isPlay);
        /**
         * 当执行了降级策略时
         *
         * @param selectLevel -1 表示降级到了未选择状态
         * @param data        当selectLevel为-1时, data为null
         */
        void onDownGraded(int selectLevel, @Nullable T data);

        /**
         * 展示时
         */
        void onShow();

        /**
         * 消失时
         */
        void onDismiss();
    }
}
