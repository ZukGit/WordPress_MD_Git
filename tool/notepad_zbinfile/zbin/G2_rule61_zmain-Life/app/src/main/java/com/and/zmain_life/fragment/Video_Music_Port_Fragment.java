package com.and.zmain_life.fragment;

import static com.and.zmain_life.bean.TimeUtil.print;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.adapter.VideoAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_MP4_Music_Port_VideoEvent;
import com.and.zmain_life.bean.Pause_MP4_Music_Port_VideoEvent;
import com.and.zmain_life.utils.EncryUtil;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.FullScreenVideoView;
import com.and.zmain_life.view.ScaleVideoView;
import com.and.zmain_life.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import butterknife.BindView;
import rx.functions.Action1;

/*import com.and.zvideo_and_dy.view.CommentDialog;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDialog;*/


public class Video_Music_Port_Fragment extends BaseFragment {
    String TAG = "Video_Music_Fragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private VideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = 0;
    //    private FullScreenVideoView videoView;
    private ScaleVideoView videoView;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private ImageView ivCurCover;

    MediaController  mediaControl;

    File Sdcard_File;
    File ZMain_File;
    volatile   boolean isVideoLoop;  // true---页面循环  false--页面下滑 默认为false


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {

        adapter = new VideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);
        isVideoLoop = false;
//        videoView = new FullScreenVideoView(getActivity());
        videoView  = new ScaleVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();


        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_MP4_Music_Port_VideoEvent.class)
                .subscribe((Action1<Pause_MP4_Music_Port_VideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.init B   ");
                        playCurVideo(DataHolder.Video_Music_Port_initPos);
                        videoView.start();
                        if(mediaControl != null)
                        mediaControl.setVisibility(View.INVISIBLE);
//                        mediaControl.setVisibility(View.VISIBLE);
//                        mediaControl.hide();

                    } else {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.init C   ");
                        videoView.pause();
                        if(mediaControl != null){
                            mediaControl.hide();
                        }

                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);

        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume  CA ");
            new CountDownTimer(200, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    videoView.seekTo(0);
                    android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume FF_1  onFinish          videoView.isPlaying()="+videoView.isPlaying());
                    mediaControl.show();

                    videoView.start();
                    android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume FF_2  onFinish          videoView.isPlaying()="+videoView.isPlaying());
                }
            }.start();
        }else {  // 当前 执行了 Resume 但还不是主界面

            android.util.Log.i("zukgit0509_1", " Video_Music_Fragment.onResume  非可见页面Resume    MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

            if(videoView != null ){
                boolean isPalying =  videoView.isPlaying();
                android.util.Log.i("zukgit0509_1", " Video_Music_Fragment.onResume  非可见页面Resume   isPalying="+isPalying+"  MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);
                videoView.stopPlayback();
                if(isPalying){
                    videoView.pause();
                }
            } else{
                android.util.Log.i("zukgit0509_1", " Video_Music_Fragment.onResume  非可见页面Resume videoView为空    MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

            }

        }
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onResume  D ");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Music_Fragment.onPause  ");
        videoView.pause();
        videoView.mScaleVideo_DoubleClick_Num=0;
        videoView.mScaleVideo_Num=1;
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    static int user_backup_num = 0;


    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(0);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                android.util.Log.i("zukgit", "Pause_Music_VideoEvent onInitComplete = " + curPlayPos + " position  = 1 A ");
//                playCurVideo(DataHolder.Video_Music_Port_initPos);
                if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {

                    recyclerView.scrollToPosition(DataHolder.Video_Music_Port_initPos);
                    playCurVideo(DataHolder.Video_Music_Port_initPos);
                }


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
                if(mediaControl != null)
                mediaControl.setVisibility(View.INVISIBLE);
                android.util.Log.i("zukgit", "X1 Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.Video_Music_Port_initPos = DataHolder.Video_Music_Port_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.Video_Music_Port_initPos = DataHolder.Video_Music_Port_initPos + 1;

                }


                if (DataHolder.Video_Music_Port_initPos < 0 || position == 0) {
                    DataHolder.Video_Music_Port_initPos = 0;
                }
                curPlayPos = DataHolder.Video_Music_Port_initPos;
                android.util.Log.i("zukgit", "X2  Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos + " user_backup_num = " + user_backup_num);

                videoView.mScaleVideo_DoubleClick_Num=0;
                videoView.mScaleVideo_Num=1;

                playCurVideo(DataHolder.Video_Music_Port_initPos);
            }
        });
    }

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



        autoPlayVideo(curPlayPos);
        android.util.Log.i("zukgit", "Video_Music G playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);

    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " dettachParentView  ");

        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        if(rootView != null && videoView != null ){
            videoView = new ScaleVideoView(getActivity());   // 重置  解决 上下滑动 闪屏 问题

            rootView.addView(videoView, 0);
        }
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
        while(getContext() == null){
            continue;
        }
        mediaControl = new MediaController(getContext());
        //  String fileName = getTimeStamp() + "_" + DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_random_indexlist.get(position)]);

        File    jiemiFile = DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get(position%DataHolder.Video_Music_Port_count)];


        Uri uri_item = Uri.fromFile(jiemiFile);


        videoView.setMediaController(mediaControl);
        mediaControl.setVisibility(View.INVISIBLE);
//        mediaControl.hide();
//        File tempFile = new File(bgVideoPath);
        videoView.setVideoPath(uri_item.getPath());
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

                    isVideoLoop = !isVideoLoop;
                    mediaControl.setVisibility(View.VISIBLE);
                    if(!isVideoLoop){  // 立即进入下一个页面  并取消当前循环
                        DataHolder.Video_Music_Port_initPos++;
                        curPlayPos = DataHolder.Video_Music_Port_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                        android.util.Log.i("zukgit", "长按 X2 往下轮询  Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);

                        videoView.stopPlayback();
//                autoPlayVideo(curPlayPos, ivCover);
                        recyclerView.scrollToPosition(DataHolder.Video_Music_Port_initPos);
//                        autoPlayVideo(DataHolder.Video_Music_Port_initPos);
                        android.util.Log.i("zukgit", "长按 XX2 往下轮询  Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);

                    }else{
                        android.util.Log.i("zukgit", "长按 X3 单曲循环 Video_Music_Fragment setOnLongClickListener   Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);

                    }

                    android.util.Log.i("zukgit", "长按 X4 setOnLongClickListener   Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);
                    return true;


                }
                isVideoLoop = !isVideoLoop;
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
        });


//        videoView.requestFocus();

        int video_position = position ;
        File rawFile = jiemiFile;
        initTouchListener(videoView,rawFile,video_position);

//        android.util.Log.i("zukgit","jiemiFile.exist() = "+ jiemiFile.exists());

        android.util.Log.i("zukgit", "Pause_Music_VideoEvent X11 curPlayPos = " + curPlayPos + "   jiemiFile " + jiemiFile.getAbsolutePath());


//        android.util.Log.i("zukgit","curPlayPos = "+ curPlayPos + " bgVideoPath = "+ bgVideoPath + " tempFile = "+ tempFile.getAbsolutePath() + "  exist = "+ tempFile.exists() + " uri_item.getPath() =" + uri_item.getPath());


        videoView.start();


        videoView.setOnPreparedListener(mp -> {
            //     mp.setLooping(true);  // 循环
            if(isVideoLoop){
                mp.setLooping(true);
            }else{
                mp.setLooping(false);
            }

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
            mp.start();
        });
        android.util.Log.i("zukgit", "autoPlayVideo setOnPreparedListener  curPlayPos = " + curPlayPos);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if(isVideoLoop){
                    if(videoView != null){
                        videoView.start();
                    }

                    android.util.Log.i("zukgit", "X1 单曲循环  Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);

                }else{
                    DataHolder.Video_Music_Port_initPos++;
                    curPlayPos = DataHolder.Video_Music_Port_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                    android.util.Log.i("zukgit", "X2 往下轮询 Video_Music_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Music_Port_initPos  =   " + DataHolder.Video_Music_Port_initPos);

                    videoView.mScaleVideo_DoubleClick_Num=0;
                    videoView.mScaleVideo_Num=1;
                    videoView.stopPlayback();
//                autoPlayVideo(curPlayPos, ivCover);
                    recyclerView.scrollToPosition(DataHolder.Video_Music_Port_initPos);
//                autoPlayVideo(DataHolder.Video_initPos);

                }

            }
        });
        android.util.Log.i("zukgit", "autoPlayVideo END  curPlayPos = " + curPlayPos);
    }


    void initTouchListener(VideoView mVideoView , File rawFile , int videoIndex){


        mVideoView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v,MotionEvent event) {
                int actionMasked = event.getActionMasked();


                //处理 5种多点触控事件
                switch (actionMasked){

                    case MotionEvent.ACTION_DOWN :          //第一个手指按下
                        break;

                    case MotionEvent.ACTION_MOVE :          //手指移动
                        break;

                    case MotionEvent.ACTION_UP :            //最后一个手指抬起
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN :  //中间的手指按下 ( 已经有手指按下 )
                        if(event.getPointerCount() >= 3){
//                            Toast.makeText(getContext(),"VideoViewMotionEvent.ACTION_POINTER_DOWN "+event.getPointerCount()+"\n"+"FileName:"+rawFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                            Log.d("TAG","VideoView onTouch action="+event.getAction()+" pointers="+event.getPointerCount()+"\n"+"FileName:"+rawFile.getAbsolutePath());

                            //
                            showDeleteFileDialog(rawFile,videoIndex);

                        }
                        break;
//                        return true;


                    case MotionEvent.ACTION_POINTER_UP :    //中间手指抬起 ( 还有手指在触摸中 )
                        break;

                }

                return false;

            }
        });
    }

    void showDeleteFileDialog(File mFile , int fileIndex ){

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Video_Music_Port_count)+"]"+"["+DataHolder.Video_Music_Port_count+"]"+mFile.getAbsolutePath());//文字
//        alterDiaglog.setMessage("请确认删除以下文件:\n"+mFile.getAbsolutePath());//提示消息

        alterDiaglog.setPositiveButton("多窗口", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(curDialog != null){
                    curDialog.dismiss();
                }
                showMultiWindowsDialog();
            }
        });



//  ______________  Video_Music_Port   SingleChoiceItems Dialog Begin ______________
        ArrayList<String> singleListAr = new ArrayList<String>();

        for (int i = 0; i < DataHolder.Video_Music_Port_KeyNameList.size() ; i++) {
            String ssKey = DataHolder.Video_Music_Port_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    DataHolder.Video_Music_Port_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("Video_Music_Port_Fragment  Tokkey["+i+"]["+DataHolder.Video_Music_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                String realName = DataHolder.dirname_to_realname(ssKey);
//                singleListAr.add(realName+"_"+matchFileArr.size());
                singleListAr.add(ssKey+"_"+matchFileArr.size());
            }else{

                print("Video_Music_Port_Fragment  SSkey["+i+"]["+DataHolder.Video_Music_Port_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }

        singleListAr.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String num1Str =  o1.substring(o1.lastIndexOf("_")+1);
                String num2Str =  o2.substring(o2.lastIndexOf("_")+1);
//                print("SSFragment SSkey num1Str="+num1Str+"    num2Str="+num2Str);
                if(DataHolder.isNumeric(num1Str) && DataHolder.isNumeric(num2Str)){
                    int num1 = Integer.parseInt(num1Str);
                    int num2 = Integer.parseInt(num2Str);
                    if(num1 == num2 ){
                        return  0 ;
                    } else if(num1 > num2){
                        return  -1 ;
                    }
                    return  1 ;
                }
                return o1.compareTo(o2);
            }
        });

        final  CharSequence[] items = new  CharSequence[singleListAr.size()];
        int matchedCategory = -1;
        for (int i = 0; i < singleListAr.size(); i++) {
            int index_num = i+1;
            String little_index_str = DataHolder.calculLittleDigital(index_num+"");
            String categoryKey = singleListAr.get(i);
            if(categoryKey.startsWith(DataHolder.Video_Music_Port_MapKey)){
                matchedCategory = i ;
            }
            items[i] = little_index_str+singleListAr.get(i);
            print("Video_Music_Port_Fragment  Topkey items["+i+"] = "+items[i]  );
        }


        alterDiaglog.setSingleChoiceItems(items,matchedCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
                DataHolder.Video_Music_Port_MapKey = categoryName+"";  //all_7
                String keyName = DataHolder.Video_Music_Port_KeyName_KeyNameSize_Map.get(categoryName);  //all
                ArrayList<File> matchFileArr =    DataHolder.Video_Music_Port_Category_FileArr_Map.get(keyName);
                if(matchFileArr == null){
                    Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+items[which]+"  无法选择!",Toast.LENGTH_SHORT).show();
                    curDialog.dismiss();
                    DataHolder.showStringMap(DataHolder.Video_Music_Port_KeyName_KeyNameSize_Map);
                    return;

                }
                Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr.size()+"]",Toast.LENGTH_SHORT).show();

                DataHolder.refresh_Video_Music_Port_List(matchFileArr);
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(DataHolder.Video_Music_Port_initPos);

                // 依据 这个  ArrayList<File> 重新生成数据
                if(curDialog != null){
                    curDialog.dismiss();
                }

                if(videoView != null){
                    videoView.mScaleVideo_DoubleClick_Num=0;
                    videoView.mScaleVideo_Num=1;
                }


            }
        });
        //中立的选择

//  ______________  Video_Music_Port   SingleChoiceItems Dialog End ______________
        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"执行删除文件【"+mFile.getAbsolutePath()+"】"
                        ,Toast.LENGTH_SHORT).show();

                if(fileIndex == 0){

                    DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get((fileIndex)%DataHolder.Video_Music_Port_count)] =      DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get((fileIndex+1)%DataHolder.Video_Music_Port_count)];


                } else     if(fileIndex > 0 && fileIndex < DataHolder.Video_Music_Port_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get((fileIndex)%DataHolder.Video_Music_Port_count)] =      DataHolder.Video_Music_Port_file_list[DataHolder.Video_Music_Port_random_indexlist.get((fileIndex-1)%DataHolder.Video_Music_Port_count)];

                }

                mFile.delete();

            }
        });

        //显示
        curDialog = alterDiaglog.show();


    }
    Dialog curDialog ;

}
