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
import com.and.zmain_life.bean.Pause_MP4_Home_Land_VideoEvent;
import com.and.zmain_life.utils.RxBus;
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


public class Video_Home_Land_Fragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private VideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = -1;
    //    private FullScreenVideoView videoView;
    private ScaleVideoView  videoView;

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
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.init     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

        adapter = new VideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);
        isVideoLoop = false;
//        videoView = new FullScreenVideoView(getActivity());
        videoView  = new ScaleVideoView(getActivity());
        setViewPagerLayoutManager();

        setRefreshEvent();


        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_MP4_Home_Land_VideoEvent.class)
                .subscribe((Action1<Pause_MP4_Home_Land_VideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.init BXXXX   ");
                        playCurVideo(DataHolder.Video_Home_Land_initPos);
                        videoView.start();
                        if(mediaControl != null)
                        mediaControl.setVisibility(View.INVISIBLE);
//                        mediaControl.setVisibility(View.VISIBLE);
//                        mediaControl.hide();

                    } else {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.init C   ");
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
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onResume     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);
        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"  curPageIndex="+curPageIndex);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume  CA          videoView.isPlaying()="+videoView.isPlaying());

            new CountDownTimer(400, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    videoView.seekTo(0);
                    android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume FF_1  onFinish          videoView.isPlaying()="+videoView.isPlaying());
                    mediaControl.show();

                    videoView.start();
                    android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume FF_2  onFinish          videoView.isPlaying()="+videoView.isPlaying());
                }
            }.start();

        } else {  // 当前 执行了 Resume 但还不是主界面

            new CountDownTimer(200, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if(videoView != null ){
                        videoView.seekTo(0);

                        boolean isPalying =  videoView.isPlaying();
                        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onResume  非可见页面Resume   isPalying="+isPalying+"  MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);
                        videoView.stopPlayback();
                            videoView.pause();

                    }else {
                        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onResume  非可见页面Resume videoView为空    MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

                    }

                    android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume FF_2  onFinish          videoView.isPlaying()="+videoView.isPlaying());
                }
            }.start();
        }


        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Video_Home_Land_Fragment.onResume  D ");

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onSaveInstanceState     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

    }


    @Override
    public void onPause() {
        super.onPause();
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onPause     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);
        videoView.mScaleVideo_DoubleClick_Num=0;
        videoView.mScaleVideo_Num=1;

        videoView.pause();
    }

    @Override
    public void onStop() {
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.onStop     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);
        videoView.mScaleVideo_DoubleClick_Num=0;
        videoView.mScaleVideo_Num=1;

        videoView.pause();
        super.onStop();

    }

    static int user_backup_num = 0;


    private void setViewPagerLayoutManager() {
        android.util.Log.i("zukgit0509_1", " Video_Home_Land_Fragment.setViewPagerLayoutManager     MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage +"   curPageIndex="+curPageIndex);

        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(0);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                android.util.Log.i("zukgit0509_1", "Video_Home_Land_Fragment  onInitComplete   MainActivity.curMainPage= " + MainActivity.curMainPage + " curPageIndex=【"+curPageIndex+"】 MainFragment.curPage="+MainFragment.curPage);
                if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
                    android.util.Log.i("zukgit0509_1", "Video_Home_Land_Fragment.setOnViewPagerListener onInitComplete = " + curPlayPos + " position  = 1 A " + " MainActivity.curMainPage ="+MainActivity.curMainPage + "    MainFragment.curPage="+ MainFragment.curPage);
                    playCurVideo(DataHolder.Video_Home_Land_initPos);    //  切到 video_raw 时候 就执行 resume 了 然后 执行到这里

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
                android.util.Log.i("zukgit0509_1", "Video_Home_Land_Fragment.onPageSelected     position=" + curPlayPos + " position   ");

                user_backup_num++;
                Action_UP_Pretime = 0;
                if(mediaControl != null)
                mediaControl.setVisibility(View.INVISIBLE);
                android.util.Log.i("zukgit", "X1 Video_Home_Land_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_initPos  =   " + DataHolder.Video_Home_Land_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.Video_Home_Land_initPos = DataHolder.Video_Home_Land_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.Video_Home_Land_initPos = DataHolder.Video_Home_Land_initPos + 1;

                }


                if (DataHolder.Video_Home_Land_initPos < 0 || position == 0) {
                    DataHolder.Video_Home_Land_initPos = 0;
                }
                curPlayPos = DataHolder.Video_Home_Land_initPos;
                android.util.Log.i("zukgit", "X2  Video_Home_Land_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_initPos  =   " + DataHolder.Video_Home_Land_initPos + " user_backup_num = " + user_backup_num);


                videoView.mScaleVideo_DoubleClick_Num=0;
                videoView.mScaleVideo_Num=1;

                playCurVideo(DataHolder.Video_Home_Land_initPos);
            }
        });
    }

    private void setRefreshEvent() {
        android.util.Log.i("zukgit0509_1", "Video_Home_Land_Fragment.setRefreshEvent     position=" + curPlayPos + " position   ");

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

        android.util.Log.i("zukgit0509_1", "Video_Home_Land A playCurVideo curPlayPos = " + curPlayPos + " position  =" + position);

/*        if (position == curPlayPos) {
            android.util.Log.i("zukgit","A_1  playCurVideo curPlayPos = "+ curPlayPos + " position  ="+ position);
            return;
        }*/

        android.util.Log.i("zukgit", "Video_Home_Land B playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + "  viewPagerLayoutManager.getChildCount() = " + viewPagerLayoutManager.getChildCount());

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

        android.util.Log.i("zukgit", "Video_Home_Land D playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + " DataCreate.datas.size = " + DataCreate.datas.size());


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
        android.util.Log.i("zukgit", "Video_Home_Land E playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        curPlayPos = position;

        //切换播放器位置
        dettachParentView(rootView);
        android.util.Log.i("zukgit", "Video_Home_Land F playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
//        autoPlayVideo(curPlayPos, ivCover);
//        autoPlayVideo(curPlayPos,ivCover);



        autoPlayVideo(curPlayPos);
        android.util.Log.i("zukgit", "Video_Home_Land G playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);

    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        android.util.Log.i("zukgit0509_1", "Video_Home_Land.dettachParentView curPlayPos = " + curPlayPos + " curPageIndex  =" + curPageIndex);

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
        android.util.Log.i("zukgit0509_1", "Video_Home_Land.autoPlayVideo  curPlayPos = " + curPlayPos + " curPageIndex  =" + curPageIndex);

//        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
        if(getContext() == null){
            return;
        }
        mediaControl = new MediaController(getContext());
//        String fileName = getTimeStamp() + "_" + DataHolder.video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.video_Home_file_list[DataHolder.Video_Home_random_indexlist.get(position)]);

        File jiemiFile = DataHolder.Video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get(position%DataHolder.Video_Home_Land_count)];
        Uri uri_item = Uri.fromFile(jiemiFile);


        videoView.setMediaController(mediaControl);
        videoView.refreshDrawableState();
        videoView.stopPlayback();



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
                        DataHolder.Video_Home_Land_initPos++;
                        curPlayPos = DataHolder.Video_Home_Land_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                        android.util.Log.i("zukgit", "X2 往下轮询  Video_Home_Land_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Home_initPos  =   " + DataHolder.Video_Home_Land_initPos);

                        videoView.stopPlayback();
//                autoPlayVideo(curPlayPos, ivCover);
                        recyclerView.scrollToPosition(DataHolder.Video_Home_Land_initPos);

                    }
                    android.util.Log.i("zukgit", "长按 A Video_Scene_Fragment Pause_Scene_VideoEvent videoView.setOnLongClickListener isVideoLoop="+isVideoLoop);
                    return true;
                }
                isVideoLoop = !isVideoLoop;
                return false;
            }
        });


        videoView.setOnTouchListener( new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                android.util.Log.i("zukgit0509_1", "Video_Home_Land.onTouch  curPlayPos = " + curPlayPos + " curPageIndex  =" + curPageIndex);
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:  // 0
                        long now = System.currentTimeMillis();
                        if(Action_UP_Pretime == 0){
                            Action_UP_Pretime = System.currentTimeMillis();
                            return false;
                        }
                        long distance = now - Action_UP_Pretime;
                        if(distance < time_space){
                            android.util.Log.i("zukgit", "Home_Video_Fragment setOnTouchListener  motionEvent ="+ motionEvent.getAction() + "  Pretime"+ Action_UP_Pretime +  "   distance = "+ distance);

                            Action_UP_Pretime = System.currentTimeMillis();
                            if(mediaControl !=null ){
                                if(mediaControl.getVisibility() != View.VISIBLE){
                                    mediaControl.setVisibility(View.VISIBLE);
                                    android.util.Log.i("zukgit", "Home_Video_Fragment setOnTouchListener  motionEvent ="+ motionEvent.getAction() + "mediaControl.setVisibility(View.VISIBLE)  Pretime"+ Action_UP_Pretime +  "   distance = "+ distance);

                                }

                            }
                        }else{
                            Action_UP_Pretime = System.currentTimeMillis();
                        }
                        android.util.Log.i("zukgit", "Home_Video_Fragment setOnTouchListener  motionEvent ="+ motionEvent.getAction() + " Pretime="+ Action_UP_Pretime +  "   distance = "+ distance);

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

        android.util.Log.i("zukgit", "Video_Home_Land_Fragment X11 curPlayPos = " + curPlayPos + "   jiemiFile " + jiemiFile.getAbsolutePath());


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
//                     mp.start();
                }
            }.start();
            android.util.Log.i("zukgit", "Video_Home_Land_Fragment mp.start curPlayPos = " + curPlayPos);
            mp.start();
        });
        android.util.Log.i("zukgit", "Video_Home_Land_Fragment autoPlayVideo setOnPreparedListener  curPlayPos = " + curPlayPos);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                android.util.Log.i("zukgit0509_1", "Video_Home_Land.setOnCompletionListener   curPlayPos = " + curPlayPos + " curPageIndex  =" + curPageIndex);

                if(isVideoLoop) {   // 单曲循环
                    if(videoView != null){
                        videoView.start();
                    }

                    android.util.Log.i("zukgit", "X1 单曲循环  Video_Home_Land_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Home_initPos  =   " + DataHolder.Video_Home_Land_initPos);

                }else{

                    DataHolder.Video_Home_Land_initPos++;
                    curPlayPos = DataHolder.Video_Home_Land_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                    android.util.Log.i("zukgit", " X2 往下轮询  Video_Home_Land_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Video_Home_initPos  =   " + DataHolder.Video_Home_Land_initPos);

                    videoView.mScaleVideo_DoubleClick_Num=0;
                    videoView.mScaleVideo_Num=1;

                    videoView.stopPlayback();
//                autoPlayVideo(curPlayPos, ivCover);
                    recyclerView.scrollToPosition(DataHolder.Video_Home_Land_initPos);
//                autoPlayVideo(DataHolder.Video_initPos);
                }

            }
        });
        android.util.Log.i("zukgit", "Video_Home_Land_Fragment autoPlayVideo END  curPlayPos = " + curPlayPos);
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
        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Video_Home_Land_count)+"]"+"["+DataHolder.Video_Home_Land_count+"]"+mFile.getAbsolutePath());//文字
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

        //  ______________  Video_Home_Land   SingleChoiceItems Dialog Begin ______________
        ArrayList<String> singleListAr = new ArrayList<String>();

        for (int i = 0; i < DataHolder.Video_Home_Land_KeyNameList.size() ; i++) {
            String ssKey = DataHolder.Video_Home_Land_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    DataHolder.Video_Home_Land_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("TopFragment  Tokkey["+i+"]["+DataHolder.Video_Home_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

//                String realName = DataHolder.dirname_to_realname(ssKey);
//                singleListAr.add(realName+"_"+matchFileArr.size());
                singleListAr.add(ssKey+"_"+matchFileArr.size());
            }else{

                print("TopFragment  SSkey["+i+"]["+DataHolder.Video_Home_Land_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
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
            if(categoryKey.startsWith(DataHolder.Video_Home_Land_MapKey)){
                matchedCategory = i ;
            }
            items[i] = little_index_str+singleListAr.get(i);
            print("SSFragment  Topkey items["+i+"] = "+items[i]  );
        }


        alterDiaglog.setSingleChoiceItems(items,matchedCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
                DataHolder.Video_Home_Land_MapKey = categoryName+"";  //all_7
                String keyName = DataHolder.Video_Home_Land_KeyName_KeyNameSize_Map.get(categoryName);  //all
                ArrayList<File> matchFileArr =    DataHolder.Video_Home_Land_Category_FileArr_Map.get(keyName);
                if(matchFileArr == null){
                    Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+items[which]+"  无法选择!",Toast.LENGTH_SHORT).show();
                    curDialog.dismiss();
                    DataHolder.showStringMap(DataHolder.Video_Home_Land_KeyName_KeyNameSize_Map);
                    return;

                }
                Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr.size()+"]",Toast.LENGTH_SHORT).show();

                DataHolder.refresh_Video_Home_Land_List(matchFileArr);
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(DataHolder.Video_Home_Land_initPos);

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

//  ______________  Video_Home_Land   SingleChoiceItems Dialog End ______________

        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"执行删除文件【"+mFile.getAbsolutePath()+"】"
                        ,Toast.LENGTH_SHORT).show();

                if(fileIndex == 0){

                    DataHolder.Video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get((fileIndex)%DataHolder.Video_Home_Land_count)] =      DataHolder.Video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get((fileIndex+1)%DataHolder.Video_Home_Land_count)];


                }   else    if(fileIndex > 0 && fileIndex < DataHolder.Video_Home_Land_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get((fileIndex)%DataHolder.Video_Home_Land_count)] =      DataHolder.Video_Home_Land_file_list[DataHolder.Video_Home_Land_random_indexlist.get((fileIndex-1)%DataHolder.Video_Home_Land_count)];

                }

                mFile.delete();

            }
        });

        //显示
        curDialog =    alterDiaglog.show();


    }
    Dialog curDialog ;

}
