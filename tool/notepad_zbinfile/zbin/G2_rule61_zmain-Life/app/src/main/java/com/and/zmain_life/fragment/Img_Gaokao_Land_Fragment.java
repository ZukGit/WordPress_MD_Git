package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.activity.Video_FullScreen_Show_Activity;
import com.and.zmain_life.adapter.ImgAdapter;
import com.and.zmain_life.adapter.JPGAdapter;
import com.and.zmain_life.adapter.JPG_Wrap_Adapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_Home_Land_ImgEvent;
import com.and.zmain_life.listener.RecyclerViewClickListener;
import com.and.zmain_life.utils.EncryUtil;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.utils.ZoomImageViewUtil;
import com.and.zmain_life.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import rx.functions.Action1;

/*import com.and.zvideo_and_dy.view.CommentDialog;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDialog;*/


/*1.只有在显示的时候在进行 轮播  不显示 暂停轮播
2.在显示界面长按时 暂停轮播  再次长按时 启动轮播
3.用户上下滑动时 轮播不暂停*/


public class Img_Gaokao_Land_Fragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = 0;

//    private ImgAdapter adapter;
    private JPG_Wrap_Adapter JpgAdapter;


//    private FullScreenVideoView videoView;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private ImageView ivCurCover;

    Dialog cur_Gaokao_Land_Img_Dialog;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_img;
    }

    private ImageView pictureImageView;
    static int mDoubleTap_Count = 0 ;
    static int mDoubleTap_ScaleNum_Index = 0 ;

    @Override
    protected void init() {

//        adapter = new ImgAdapter(getActivity(), DataCreate.datas);
        JpgAdapter = new JPG_Wrap_Adapter(getActivity(),DataHolder.Gaokao_Land_Img_random_indexlist,DataHolder.Gaokao_Land_Img_file_list);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), recyclerView,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, MotionEvent e) {
                        mDoubleTap_Count++;
                        if(mDoubleTap_Count%2 == 0){
//                            Toast.makeText(getContext(), "FIT_XY_Click " +position +"x["+e.getX()+"]"+"y["+e.getY()+"]", Toast.LENGTH_SHORT).show();
                            if(pictureImageView != null){
                                pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }else{

                            if(pictureImageView != null){
                                //   每次放大的 系数 不一样  3 5 10 轮替放大

                                float scaleNum = 3f;
                                if(mDoubleTap_ScaleNum_Index%3 == 0){
                                    scaleNum =3f;
                                }else if(mDoubleTap_ScaleNum_Index%3 == 1){
                                    scaleNum = 5f;
                                }else if(mDoubleTap_ScaleNum_Index%3 == 2){
                                    scaleNum = 10f;
                                }

                                Log.i("zukgit", "curPlayPos = " + curPlayPos + "  scaleNum ="+scaleNum);
                                mDoubleTap_ScaleNum_Index++;
                                ZoomImageViewUtil.OperationImageView(pictureImageView,e,scaleNum);



                            }

                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position , MotionEvent e) {
//                        Toast.makeText(getContext(), "Long Click " +position +"x["+e.getX()+"]"+"y["+e.getY()+"]", Toast.LENGTH_SHORT).show();
                        pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }));

//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(JpgAdapter);
//        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();

// s少时诵诗书所
        RxBus.getDefault().post(new Pause_Home_Land_ImgEvent(false));
        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Img_Gaokao_Land_Fragment.class)
                .subscribe((Action1<Pause_Home_Land_ImgEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgFragment.init B   ");
                        mDoubleTap_ScaleNum_Index = 0;
                        Log.i("zukgit", "AA toObservable  DataHolder.Top_Land_Img_initPos = " + DataHolder.Gaokao_Land_Img_initPos + " pause_Img(true)   ");
                        if(recyclerView != null){
                            recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);
                            Log.i("zukgit", "AA toObservable  DataHolder.Top_Land_Img_initPos = " + DataHolder.Gaokao_Land_Img_initPos + " pause_Img(true)   ");
                        }
                        pause_Img(false);
                        gotoNextPage_loop(false);


                    } else {
                        Log.i("zukgit", "BB toObservable  DataHolder.Top_Land_Img_initPos = " + DataHolder.Gaokao_Land_Img_initPos + " pause_Img(true)   ");
                        pause_Img(true);
                    }
                });
        pause_Img(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgGaokaoPortFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
//        pause_Img(false);    // 进来就调用了
//         gotoNextPage_loop();


/*        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  CA ");
            videoView.start();
        }*/
        Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgGaokaoPortFragment.onResume  CB ");
        Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgGaokaoPortFragment.onResume  D ");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onPause  ");
        pause_Img(true);
//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        pause_Img(true);
    }

    static int user_backup_num = 0;



    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);


        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

                File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get(DataHolder.Gaokao_Land_Img_initPos%DataHolder.Gaokao_Land_Img_count)]);




                Uri uri_item = Uri.fromFile(jiemiFile);
                Log.i("zukgit", "onInitComplete ivCover.setImageURI    curPlayPos =" + curPlayPos + "DataHolder.Top_Land_Img_initPos ="+ DataHolder.Gaokao_Land_Img_initPos + "uri_item = "+ uri_item.toString());

                playCurImg(DataHolder.Gaokao_Land_Img_initPos);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (ivCurCover != null) {
                    ivCurCover.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                user_backup_num++;

                Log.i("zukgit", "X1 ImgFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Top_Land_Img_initPos  =   " + DataHolder.Gaokao_Land_Img_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                mDoubleTap_Count = 0;
                mDoubleTap_ScaleNum_Index=0;
                if(pictureImageView != null){
                    pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.Gaokao_Land_Img_initPos = DataHolder.Gaokao_Land_Img_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.Gaokao_Land_Img_initPos = DataHolder.Gaokao_Land_Img_initPos + 1;

                }


                if (DataHolder.Gaokao_Land_Img_initPos < 0 || position == 0) {
                    DataHolder.Gaokao_Land_Img_initPos = 0;
                }
                DataHolder.Gaokao_Land_Img_initPos = position;
                curPlayPos = DataHolder.Gaokao_Land_Img_initPos;
                Log.i("zukgit", "X2  ImgFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Top_Land_Img_initPos  =   " + DataHolder.Gaokao_Land_Img_initPos + " user_backup_num = " + user_backup_num);

                playCurImg(DataHolder.Gaokao_Land_Img_initPos);
            }
        });

        recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

    }

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

    private void playCurImg(int position) {
        Log.i("zukgit", "A playCurVideo curPlayPos = " + curPlayPos + " position  =" + position);

/*        if (position == curPlayPos) {
            android.util.Log.i("zukgit","A_1  playCurVideo curPlayPos = "+ curPlayPos + " position  ="+ position);
            return;
        }*/

        Log.i("zukgit", "B playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + "  viewPagerLayoutManager.getChildCount() = " + viewPagerLayoutManager.getChildCount());

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

        Log.i("zukgit", "D zvideo  playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + " DataCreate.datas.size = " + DataCreate.datas.size());


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

        ImageView ivCover = (ImageView)rootView.findViewById(R.id.iv_cover_img);
        pictureImageView = ivCover;
        mDoubleTap_ScaleNum_Index = 0;

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(new CurUserBean(DataCreate.datas.get(position).getUserBean()));
        Log.i("zukgit", "E playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        curPlayPos = position;

        //切换播放器位置
//        dettachParentView(rootView);
        Log.i("zukgit", "F playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        autoShowImg(curPlayPos, ivCover);
//        autoShowImg(curPlayPos,ivCover);

        Log.i("zukgit", "G playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);

    }

    /**
     * 移除videoview父view
     */
/*
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view

        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " dettachParentView  ");

        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);
    }
*/

    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    /**
     * 自动播放视频
     */
    private void autoShowImg(int position , ImageView ivCover) {

//        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
        MediaController mc = new MediaController(getContext());
//        String fileName = getTimeStamp() + "_" + Img_file_list[DataHolder.Top_Land_Img_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get(position)]);

        File jiemiFile = DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get(position%DataHolder.Gaokao_Land_Img_count)];


        Uri uri_item = Uri.fromFile(jiemiFile);
        Log.i("zukgit", "ivCover.setImageURI  position ="+ position + "   DataHodler.ImgiIndex="+DataHolder.Gaokao_Land_Img_initPos + "   Img Pre  =" + uri_item.toString());
//        ivCover.setImageURI(uri_item);
        Log.i("zukgit", "ivCover.setImageURI  " + " Img End  =" + uri_item.toString());
        ivCover.setLongClickable(true);
        File rawFile = jiemiFile;
        initTouchListener(ivCover,rawFile,position);

        ivCover.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Log.i("zukgit", "onLongClick  " + " isImgPause  =" + isImgPause);

                if(pictureImageView != null){
                    pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    mDoubleTap_Count = 0;
                }

                pause_Img(!isImgPause);
                gotoNextPage_loop(true);
                return false;
            }
        });
//        videoView.setMediaController(mc);
//        mc.setVisibility(View.VISIBLE);
//        File tempFile = new File(bgVideoPath);
//        videoView.setVideoPath(uri_item.getPath());

//        android.util.Log.i("zukgit","jiemiFile.exist() = "+ jiemiFile.exists());

        Log.i("zukgit", "X11 curPlayPos = " + curPlayPos + "   jiemiFile " + jiemiFile.getAbsolutePath());


//        android.util.Log.i("zukgit","curPlayPos = "+ curPlayPos + " bgVideoPath = "+ bgVideoPath + " tempFile = "+ tempFile.getAbsolutePath() + "  exist = "+ tempFile.exists() + " uri_item.getPath() =" + uri_item.getPath());


//        videoView.start();


        Log.i("zukgit", "autoShowImg setOnPreparedListener  curPlayPos = " + curPlayPos);

/*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                DataHolder.Top_Land_Img_initPos++;
                curPlayPos = DataHolder.Top_Land_Img_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                android.util.Log.i("zukgit", "X2  RecommendFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Top_Land_Img_initPos  =   " + DataHolder.Top_Land_Img_initPos);

                videoView.stopPlayback();
//                autoShowImg(curPlayPos, ivCover);
                autoShowImg(DataHolder.Top_Land_Img_initPos);
            }
        });*/


        //        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getActivity(),null);

//        RelativeLayout.LayoutParams layoutParams_wrap = new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//
//
//        RelativeLayout.LayoutParams layoutParams_fill = new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
//
//
//        layoutParams_wrap.addRule(RelativeLayout.CENTER_IN_PARENT);
//        pictureImageView.setLayoutParams(layoutParams_wrap);
//        pictureImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


        Log.i("zukgit", "autoShowImg END  curPlayPos = " + curPlayPos);
    }

    volatile   boolean isImgPause;  // true -- pasue    false---move
    void pause_Img( boolean flag ){
        isImgPause = flag;
        if(isImgPause && loopPlay_Timer != null){
            loopPlay_Timer.cancel();
        }
    }


/*    void wait_1second_operation(){
     if(isImgPause){
    new CountDownTimer(1000, 500) {
        @Override
        public void onTick(long millisUntilFinished) { }
        @Override
        public void onFinish() {
            if(curPlayPos == DataHolder.Top_Land_Img_initPos){
                pause_Img(false);
            }
        }
    }.start();
}
 }*/

    static int  onFinish_num = 0;
    static  CountDownTimer  loopPlay_Timer ;
    void gotoNextPage_loop(boolean imediate){

        if(pictureImageView != null){
            pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mDoubleTap_Count = 0;
        }

        if(!isImgPause){
            if(loopPlay_Timer != null){
                loopPlay_Timer.cancel();
            }

            if(imediate){
                DataHolder.Gaokao_Land_Img_initPos++;
                Log.i("zukgit", "AA DataHolder.Top_Land_Img_initPos   DataHolder.Top_Land_Img_initPos = " + DataHolder.Gaokao_Land_Img_initPos);
                recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);
            }
            if(loopPlay_Timer != null){
                loopPlay_Timer.cancel();
            }
            loopPlay_Timer =    new CountDownTimer(3500, 500) {
                @Override
                public void onTick(long millisUntilFinished) { }
                @Override
                public void onFinish() {

                    if(!isImgPause) {
                        DataHolder.Gaokao_Land_Img_initPos++;
                        onFinish_num++;
                        Log.i("zukgit", "【"+onFinish_num+ "】 = onFinish_num   DataHolder.Top_Land_Img_initPos   DataHolder.Top_Land_Img_initPos = " + DataHolder.Gaokao_Land_Img_initPos);
//                    playCurImg(DataHolder.Top_Land_Img_initPos);
//                        recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {

                                recyclerView.smoothScrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

                                recyclerView.requestLayout();
                            }
                        });

                        gotoNextPage_loop(false);
                    }

                }
            };
            loopPlay_Timer.start();

        }
    }


    void initTouchListener(ImageView mImageView ,  File rawFile   , int fileIndex ){


        mImageView.setOnTouchListener(new View.OnTouchListener() {

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
//                            Toast.makeText(getContext(),"ImageView_MotionEvent.ACTION_POINTER_DOWN "+event.getPointerCount()+"\n"+"FileName:"+rawFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                            Log.d("TAG","onTouch action="+event.getAction()+" pointers="+event.getPointerCount());
                            showDeleteFileDialog(rawFile,fileIndex);
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_UP :    //中间手指抬起 ( 还有手指在触摸中 )
                        break;

                }


                return false;
            }
        });
    }

    void print(String logStr){
        android.util.Log.i("Gaokao_land_Fratgment",logStr);

    }

    void showDeleteFileDialog(File mFile , int fileIndex ){
//        gaokao_customer_dialog.xml


        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.gaokao_customer_dialog, null);

        TextView cancel_bt = (TextView)view.findViewById(R.id.gaokao_dialog_cancel_text);
        TextView delete_bt = (TextView)view.findViewById(R.id.gaokao_dialog_delete_text);
        TextView play_bt = (TextView)view.findViewById(R.id.gaokao_dialog_play_text);
        TextView title_txt = (TextView)view.findViewById(R.id.gaokao_dialog_title_text);

        RadioGroup mRadioGroup =(RadioGroup)view.findViewById(R.id.gaokao_dialog_radiogroup);
        Spinner spinner =(Spinner)view.findViewById(R.id.gaokao_dialog_spinner);
        ScrollView scrollView =(ScrollView)view.findViewById(R.id.gaokao_dialog_scrollview);

        String  videomd5name = DataHolder.getExif_Copyright_videomd5name(mFile);
        String videofullPath = null;
        File  matchMp4File = null;
        boolean isMatchMp4Flag = false;
        if(videomd5name == null){
            videofullPath = "当前图片对应视频找不到,无法播放对应视频!["+videomd5name+"]";
            play_bt.setVisibility(View.INVISIBLE);
        }else{

            videofullPath = DataHolder.ZMain_File.getAbsolutePath()+File.separator+ "mp4_gaokao_land"+File.separator+videomd5name+".mp4";

            matchMp4File = new File(videofullPath);
            if(matchMp4File.exists()){
                isMatchMp4Flag = true;
                videofullPath = "当前图片对应视频找到, 可以播放对应视频!\n["+videomd5name+"]"+"\n"+"["+videofullPath+"]";
            }else{
                videofullPath = "当前图片对应视频找不到,无法播放对应视频!\n["+videomd5name+"]"+"\n"+"["+videofullPath+"]";
                play_bt.setVisibility(View.INVISIBLE);
            }
        }


        // 取消按钮 begin
        cancel_bt.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cur_Gaokao_Land_Img_Dialog != null){
                    cur_Gaokao_Land_Img_Dialog.dismiss();
                }

            }
        });
        // 取消按钮 end


        // 删除按钮 begin
        delete_bt.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"执行删除文件【"+mFile.getAbsolutePath()+"】"
                        ,Toast.LENGTH_SHORT).show();

                if(fileIndex == 0){

                    DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex)%DataHolder.Gaokao_Land_Img_count)] =      DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex+1)%DataHolder.Gaokao_Land_Img_count)];


                } else       if(fileIndex > 0 && fileIndex < DataHolder.Gaokao_Land_Img_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex)%DataHolder.Gaokao_Land_Img_count)] =      DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex-1)%DataHolder.Gaokao_Land_Img_count)];

                }

                mFile.delete();

            }
        });
        // 删除按钮 end



        File finalMatchMp4File = matchMp4File;
        // 播放按钮 begin
        play_bt.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"开始播放mp4文件【"+ finalMatchMp4File.getAbsolutePath()+"】"
                        ,Toast.LENGTH_SHORT).show();

//                   Intent intent = new Intent();
//                   intent.setAction(Intent.ACTION_VIEW);
//                   String type = "video/*";
//                   Uri uri = Uri.fromFile(finalMatchMp4File);
//                   intent.setDataAndType(uri,type);
//                   startActivity(intent);

                // 系统 播放  视频
//                String url = finalMatchMp4File.getAbsolutePath();
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                String type = "video/mp4";
//                Uri uri = Uri.parse(url);
//                intent.setDataAndType(uri,type);
//                startActivity(intent);


                // 本地的 播放器 播放视频
                Intent local_activity_intent = new Intent(getActivity(), Video_FullScreen_Show_Activity.class);
                local_activity_intent.putExtra("path", finalMatchMp4File.getAbsolutePath());
                getActivity().startActivity(local_activity_intent);



            }
        });
        // 播放按钮 end



        // Spinner----Begin
        ArrayList  spinnerTipArr = new ArrayList<String>();
        spinnerTipArr.clear();

        spinnerTipArr.addAll(DataHolder.Gaokao_Land_SubDir_NameList);


        title_txt.setText("删除&&播放_文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Gaokao_Land_Img_count)+"]"+"["+DataHolder.Gaokao_Land_Img_count+"]"+mFile.getAbsolutePath());//文字

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, spinnerTipArr);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(DataHolder.Gaokao_Land_SubDir_NameList.indexOf(DataHolder.Gaokao_Land_RootDir_Key));
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerItem =   DataHolder.Gaokao_Land_SubDir_NameList.get(position);
//                Toast.makeText(getActivity(),String.valueOf("Spinner["+position+"] = "+spinnerItem),Toast.LENGTH_SHORT).show();
                // root--Gaokao_Land_Img_Category_FileArr_Map        數學2考研---<文件名1第一題,ArrayList<File>只包含這一道題>

                DataHolder.Gaokao_Land_RootDir_Key = spinnerItem;
                refreshRadioButton(mRadioGroup,scrollView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // Spinner----End

        // RadioGroup----Begin
        RadioButton selectedButton =    refreshRadioButton(mRadioGroup,scrollView);
        // RadioGroup----End








        cur_Gaokao_Land_Img_Dialog =   builder.create();
        cur_Gaokao_Land_Img_Dialog.show();
        cur_Gaokao_Land_Img_Dialog.getWindow().setContentView(view);

        scrollView.post(new Runnable(){
            @Override
            public void run(){
                //滚动到指定位置（滚动要跳过的控件的高度的距离）
                if(selectedButton != null ){
                    // 滑动到指定的 screen
                    String matchIndexStr =  (String)selectedButton.getTag(R.id.gaokao_radiobutton_index);
                    int matchIndex = 0 ;
                    if(DataHolder.isNumeric(matchIndexStr)){
                        matchIndex = Integer.parseInt(matchIndexStr) - 1;
                        if(matchIndex <= 0 ){
                            matchIndex = 0;
                        }
                    }
                    float  x_point=   selectedButton.getX();
                    float y_point=    selectedButton.getY();
                    float y_top=  selectedButton.getTop();

                    int height =  selectedButton.getMeasuredHeight();
                    int width =   selectedButton.getMeasuredWidth();
                    int real_y  = height*matchIndex ;
//                    scrollView.scrollTo(0,(int)y_top);
//                    mRadioGroup.scrollTo(0,(int)y_top);

                    print("x_point["+x_point+"]"+" y_top["+y_top+"] y_point["+y_point+"] width["+width+"] height["+height+"] real_y["+real_y+"] matchIndex="+matchIndex);
                    scrollView.scrollTo(0,(int)y_point);
                }

                //如果要平滑滚动，可以这样写
                //scrollView.smoothScrollTo(0, llNeedToSkip.getMeasuredHeight());
            }
        });
    }


    RadioButton refreshRadioButton(  RadioGroup mRadioGroup , ScrollView mScrollView){

        HashMap<String,ArrayList<File>> showFileArrMap =    DataHolder.Gaokao_Land_DirName_2_Category_FileArr_Map.get(DataHolder.Gaokao_Land_RootDir_Key);
        ArrayList<String> radioTextList =  DataHolder.Gaokao_Land_DirName_RadioTextList_Map.get(DataHolder.Gaokao_Land_RootDir_Key);
        DataHolder.Gaokao_Land_Img_KeyNameList = radioTextList;
        DataHolder.Gaokao_Land_Img_Category_FileArr_Map = showFileArrMap;

        mRadioGroup.removeAllViews();
        ArrayList<String> singleListAr = new  ArrayList<String>();

        for (int i = 0; i < DataHolder.Gaokao_Land_Img_KeyNameList.size() ; i++) {
            String ssKey = DataHolder.Gaokao_Land_Img_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("TopFragment  Tokkey["+i+"]["+DataHolder.Gaokao_Land_Img_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

                singleListAr.add(ssKey+"_"+matchFileArr.size());
            }else{

                print("TopFragment  SSkey["+i+"]["+DataHolder.Gaokao_Land_Img_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
            }

        }

        if(DataHolder.Gaokao_Land_RootDir_Key.equals("root")){
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
                            return  o1.compareTo(o2) ;
                        } else if(num1 > num2){
                            return  -1 ;
                        }
                        return  1 ;
                    }
                    return o1.compareTo(o2);
                }
            });


        }else{

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
                            return  o1.compareTo(o2) ;
                        } else if(num1 > num2){
                            return  1 ;
                        }
                        return  -1 ;
                    }
                    return o1.compareTo(o2);
                }
            });
        }





        final  CharSequence[] items = new  CharSequence[singleListAr.size()];
        int matchedCategory = -1;
        RadioButton  selectedButton =  null;

        for (int i = 0; i < singleListAr.size(); i++) {
            int index_num = i+1;
            String little_index_str = DataHolder.calculLittleDigital(index_num+"");
            String categoryKey = singleListAr.get(i);


            items[i] = little_index_str+singleListAr.get(i);

            RadioButton radioButton = (RadioButton)LayoutInflater.from(getContext()).inflate(R.layout.gaokao_customer_dialog_radiobtn, null);

//            RadioButton radioButton = new RadioButton(getActivity());
            if(categoryKey.startsWith(DataHolder.Gaokao_Land_Img_MapKey)){
                matchedCategory = i ;
                radioButton.setChecked(true);
                selectedButton = radioButton;
                selectedButton.setTag(R.id.gaokao_radiobutton_index,matchedCategory+"");


            }
            radioButton.setTextSize(16);
            radioButton.setText(items[i]);
            print("SSFragment  Topkey items["+i+"] = "+items[i]  );
            String radioBtnText = items[i]+"";

            radioButton.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CharSequence categoryName = DataHolder.clearLittleDigital(radioBtnText+"");
                    DataHolder.Gaokao_Land_Img_MapKey = categoryName+"";  //all_7
                    String keyName = DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map.get(categoryName);  //all

                    ArrayList<File> matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(keyName);
                    if(matchFileArr ==null){
                        String categoryName_clearLengthStr = DataHolder.Gaokao_Land_Img_MapKey.substring(0,DataHolder.Gaokao_Land_Img_MapKey.lastIndexOf("_"));
                        matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(categoryName_clearLengthStr);
                        Toast.makeText(getContext(),"categoryName_clearLength 【"+categoryName_clearLengthStr+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+radioBtnText+"  无法选择!",Toast.LENGTH_SHORT).show();

                    }
                    if(matchFileArr == null){
                        Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+radioBtnText+"  无法选择!",Toast.LENGTH_SHORT).show();
                        cur_Gaokao_Land_Img_Dialog.dismiss();
                        DataHolder.showStringMap(DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map);
                        return;

                    }
                    Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr.size()+"]",Toast.LENGTH_SHORT).show();

                    DataHolder.refresh_Gaokao_Land_Img_List(matchFileArr);
                    JpgAdapter.refreshWithDataHolder(DataHolder.Gaokao_Land_Img_random_indexlist,DataHolder.Gaokao_Land_Img_file_list);

                    recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

                    // 依据 这个  ArrayList<File> 重新生成数据
                    if(cur_Gaokao_Land_Img_Dialog != null){
                        cur_Gaokao_Land_Img_Dialog.dismiss();
                    }

                }
            });

            mRadioGroup.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        }



        return selectedButton;

/*
        CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
        DataHolder.Gaokao_Land_Img_MapKey = categoryName+"";  //all_7
        String keyName = DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map.get(categoryName);  //all
        ArrayList<File> matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(keyName);
        if(matchFileArr == null){
            Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+items[which]+"  无法选择!",Toast.LENGTH_SHORT).show();
            cur_Gaokao_Land_Img_Dialog.dismiss();
            DataHolder.showStringMap(DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map);
            return;

        }
        Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr.size()+"]",Toast.LENGTH_SHORT).show();

        DataHolder.refresh_Gaokao_Land_Img_List(matchFileArr);
        JpgAdapter.refreshWithDataHolder(DataHolder.Gaokao_Land_Img_random_indexlist,DataHolder.Gaokao_Land_Img_file_list);

        recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

        // 依据 这个  ArrayList<File> 重新生成数据
        if(cur_Gaokao_Land_Img_Dialog != null){
            cur_Gaokao_Land_Img_Dialog.dismiss();
        }
*/



    }

    void showDeleteFileDialog_OLD(File mFile , int fileIndex ){

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());

        String  videomd5name = DataHolder.getExif_Copyright_videomd5name(mFile);
        String videofullPath = null;
        File  matchMp4File = null;
        boolean isMatchMp4Flag = false;
        if(videomd5name == null){
            videofullPath = "当前图片对应视频找不到,无法播放对应视频!["+videomd5name+"]";
        }else{

            videofullPath = DataHolder.ZMain_File.getAbsolutePath()+File.separator+ "mp4_gaokao_land"+File.separator+videomd5name+".mp4";

            matchMp4File = new File(videofullPath);
            if(matchMp4File.exists()){
                isMatchMp4Flag = true;

            }else{
                videofullPath = "当前图片对应视频找不到,无法播放对应视频!\n["+videomd5name+"]"+"\n"+"["+videofullPath+"]";
            }
        }

        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Gaokao_Land_Img_count)+"]"+"["+DataHolder.Gaokao_Land_Img_count+"]"+mFile.getAbsolutePath());//文字
/*        alterDiaglog.setMessage("请确认删除以下文件:\n"+mFile.getAbsolutePath()+"\n"+
                "\n"+"若播放对应考研视频请点击中间按钮"+"\n"+"["+videofullPath+"]"
        );//提示消息*/


        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });



        ArrayList<String> singleListAr = new  ArrayList<String>();

        for (int i = 0; i < DataHolder.Gaokao_Land_Img_KeyNameList.size() ; i++) {
            String ssKey = DataHolder.Gaokao_Land_Img_KeyNameList.get(i);
            ArrayList<File> matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(ssKey);
            if(matchFileArr != null && matchFileArr.size() > 0){
                print("TopFragment  Tokkey["+i+"]["+DataHolder.Gaokao_Land_Img_KeyNameList.size()+"] = "+ ssKey+" ArrSize="+matchFileArr.size());

                singleListAr.add(ssKey+"_"+matchFileArr.size());
            }else{

                print("TopFragment  SSkey["+i+"]["+DataHolder.Gaokao_Land_Img_KeyNameList.size()+"] = "+ ssKey+" ArrSize=0");
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
            if(categoryKey.startsWith(DataHolder.Gaokao_Land_Img_MapKey)){
                matchedCategory = i ;
            }
            items[i] = little_index_str+singleListAr.get(i);
            print("SSFragment  Topkey items["+i+"] = "+items[i]  );
        }


        alterDiaglog.setSingleChoiceItems(items,matchedCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
                DataHolder.Gaokao_Land_Img_MapKey = categoryName+"";  //all_7
                String keyName = DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map.get(categoryName);  //all
                ArrayList<File> matchFileArr =    DataHolder.Gaokao_Land_Img_Category_FileArr_Map.get(keyName);
                if(matchFileArr == null){
                    Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr+"]为空!!  items[which]="+items[which]+"  无法选择!",Toast.LENGTH_SHORT).show();
                    cur_Gaokao_Land_Img_Dialog.dismiss();
                    DataHolder.showStringMap(DataHolder.Gaokao_Land_Img_KeyName_KeyNameSize_Map);
                    return;

                }
                Toast.makeText(getContext(),"ZTopCategoryName【"+categoryName+"】 keyName["+keyName+"] Size["+matchFileArr.size()+"]",Toast.LENGTH_SHORT).show();

                DataHolder.refresh_Gaokao_Land_Img_List(matchFileArr);
                JpgAdapter.refreshWithDataHolder(DataHolder.Gaokao_Land_Img_random_indexlist,DataHolder.Gaokao_Land_Img_file_list);

                recyclerView.scrollToPosition(DataHolder.Gaokao_Land_Img_initPos);

                // 依据 这个  ArrayList<File> 重新生成数据
                if(cur_Gaokao_Land_Img_Dialog != null){
                    cur_Gaokao_Land_Img_Dialog.dismiss();
                }


            }
        });
        //中立的选择



        if(isMatchMp4Flag){

            File finalMatchMp4File = matchMp4File;
            alterDiaglog.setNegativeButton("播放", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(),"开始播放mp4文件【"+ finalMatchMp4File.getAbsolutePath()+"】"
                            ,Toast.LENGTH_SHORT).show();

//                   Intent intent = new Intent();
//                   intent.setAction(Intent.ACTION_VIEW);
//                   String type = "video/*";
//                   Uri uri = Uri.fromFile(finalMatchMp4File);
//                   intent.setDataAndType(uri,type);
//                   startActivity(intent);

                    String url = finalMatchMp4File.getAbsolutePath();

                    Intent intent = new Intent();

                    intent.setAction(Intent.ACTION_VIEW);

                    String type = "video/mp4";

                    Uri uri = Uri.parse(url);


                    intent.setDataAndType(uri,type);

                    startActivity(intent);


                }
            });


        }

        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"执行删除文件【"+mFile.getAbsolutePath()+"】"
                        ,Toast.LENGTH_SHORT).show();

                if(fileIndex == 0){

                    DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex)%DataHolder.Gaokao_Land_Img_count)] =      DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex+1)%DataHolder.Gaokao_Land_Img_count)];


                } else       if(fileIndex > 0 && fileIndex < DataHolder.Gaokao_Land_Img_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex)%DataHolder.Gaokao_Land_Img_count)] =      DataHolder.Gaokao_Land_Img_file_list[DataHolder.Gaokao_Land_Img_random_indexlist.get((fileIndex-1)%DataHolder.Gaokao_Land_Img_count)];

                }

                mFile.delete();

            }
        });

        //显示
        cur_Gaokao_Land_Img_Dialog =   alterDiaglog.show();


    }





}
