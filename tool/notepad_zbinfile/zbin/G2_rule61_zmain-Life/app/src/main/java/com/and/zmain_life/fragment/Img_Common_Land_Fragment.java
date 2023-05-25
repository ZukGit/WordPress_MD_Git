package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.adapter.ImgAdapter;
import com.and.zmain_life.adapter.JPGAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_Img_Common_Land_Event;
import com.and.zmain_life.bean.Pause_Wall_Land_ImgEvent;
import com.and.zmain_life.listener.RecyclerViewClickListener;
import com.and.zmain_life.utils.EncryUtil;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.utils.ZoomImageViewUtil;
import com.and.zmain_life.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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


public class Img_Common_Land_Fragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    SharedPreferences top_img_sp ;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = 0;

//    private ImgAdapter adapter;
    private JPGAdapter JpgAdapter;
//

//    private FullScreenVideoView videoView;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private ImageView ivCurCover;

    static int mDoubleTap_Count = 0 ;
    static int mDoubleTap_ScaleNum_Index = 0 ;
    private ImageView pictureImageView;

    void ShowAllSharePreferences(){
        Map<String,String> sharePreferenceDataList = (HashMap <String,String>)top_img_sp.getAll();
        Map.Entry<String , String> entryItem;
        int shareIndex = 1;
        if(sharePreferenceDataList != null){
            Iterator iterator = sharePreferenceDataList.entrySet().iterator();
            while( iterator.hasNext() ){
                entryItem = (Map.Entry<String , String>) iterator.next();
                String timeKeyStr = entryItem.getKey();   //Map的Key
                String fileNameStr = entryItem.getValue();  //Map的Value
                android.util.Log.i("SharedPreferences_Top_Jpg["+shareIndex+"]: ",  fileNameStr);
                shareIndex++;
            }
        }

    }

    void saveFileNameInSharedPreferences(String fileName){


        android.util.Log.i("zukgit", " 保存文件名称到 SharedPreferences Top_Jpg fileName="+ fileName);
        SharedPreferences.Editor editor = top_img_sp.edit();
        editor.putString(""+getTimeStamp(), fileName);
        // 4:提交
        editor.commit();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_img;
    }

    @Override
    protected void init() {

//        adapter = new ImgAdapter(getActivity(), DataCreate.datas);

        JpgAdapter = new JPGAdapter(getActivity(),DataHolder.Common_Img_Land_random_indexlist,DataHolder.Common_Img_Land_file_list);


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




        recyclerView.setAdapter(JpgAdapter);

        top_img_sp = getActivity().getSharedPreferences("Top_Jpg", getContext().MODE_PRIVATE);

        ShowAllSharePreferences();

//        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();

// s少时诵诗书所
        RxBus.getDefault().post(new Pause_Wall_Land_ImgEvent(false));
        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_Img_Common_Land_Event.class)
                .subscribe((Action1<Pause_Img_Common_Land_Event>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgCommonLandFragment.init B   ");
                        mDoubleTap_ScaleNum_Index = 0;
                        android.util.Log.i("zukgit", "AA toObservable  DataHolder.Common_Img_Land_initPos = " + DataHolder.Common_Img_Land_initPos + " pause_Img(true)   ");
                        if(recyclerView != null){
                            recyclerView.scrollToPosition(DataHolder.Common_Img_Land_initPos);
                            android.util.Log.i("zukgit", "AA toObservable  DataHolder.Common_Img_Land_initPos = " + DataHolder.Common_Img_Land_initPos + " pause_Img(true)   ");
                        }
                        pause_Img(false);
                        gotoNextPage_loop(false);


                    } else {
                        android.util.Log.i("zukgit", "BB toObservable  DataHolder.Common_Img_Land_initPos = " + DataHolder.Common_Img_Land_initPos + " pause_Img(true)   ");
                        pause_Img(true);
                    }
                });
        pause_Img(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgCommonLandFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
//        pause_Img(false);    // 进来就调用了
//         gotoNextPage_loop();


/*        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Img_Common_Fragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Img_Common_Fragment.onResume  CA ");
            videoView.start();
        }*/
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgCommonLandFragment.onResume  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " ImgCommonLandFragment.onResume  D ");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " Img_Common_Fragment.onPause  ");
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
        recyclerView.scrollToPosition(DataHolder.Common_Img_Land_initPos);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

                File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get(DataHolder.Common_Img_Land_initPos%DataHolder.Common_Img_Land_count)]);
                Uri uri_item = Uri.fromFile(jiemiFile);
                android.util.Log.i("zukgit", "onInitComplete ivCover.setImageURI    curPlayPos =" + curPlayPos + "DataHolder.Common_Img_Land_initPos ="+ DataHolder.Common_Img_Land_initPos + "uri_item = "+ uri_item.toString());

                playCurImg(DataHolder.Common_Img_Land_initPos);
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

                android.util.Log.i("zukgit", "X1 ImgFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Common_Img_Land_initPos  =   " + DataHolder.Common_Img_Land_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                mDoubleTap_Count = 0;
                mDoubleTap_ScaleNum_Index=0;
                if(pictureImageView != null){
                    pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.Common_Img_Land_initPos = DataHolder.Common_Img_Land_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.Common_Img_Land_initPos = DataHolder.Common_Img_Land_initPos + 1;

                }


                if (DataHolder.Common_Img_Land_initPos < 0 || position == 0) {
                    DataHolder.Common_Img_Land_initPos = 0;
                }
                DataHolder.Common_Img_Land_initPos = position;
                curPlayPos = DataHolder.Common_Img_Land_initPos;
                android.util.Log.i("zukgit", "X2  ImgFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Common_Img_Land_initPos  =   " + DataHolder.Common_Img_Land_initPos + " user_backup_num = " + user_backup_num);

                playCurImg(DataHolder.Common_Img_Land_initPos);
            }
        });
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
        android.util.Log.i("zukgit", "A playCurVideo curPlayPos = " + curPlayPos + " position  =" + position);

/*        if (position == curPlayPos) {
            android.util.Log.i("zukgit","A_1  playCurVideo curPlayPos = "+ curPlayPos + " position  ="+ position);
            return;
        }*/

        android.util.Log.i("zukgit", "B playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + "  viewPagerLayoutManager.getChildCount() = " + viewPagerLayoutManager.getChildCount());

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

        android.util.Log.i("zukgit", "D playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position + " DataCreate.datas.size = " + DataCreate.datas.size());


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
        android.util.Log.i("zukgit", "E playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        curPlayPos = position;

        //切换播放器位置
//        dettachParentView(rootView);
        android.util.Log.i("zukgit", "F playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        autoShowImg(curPlayPos, ivCover);
//        autoShowImg(curPlayPos,ivCover);

        android.util.Log.i("zukgit", "G playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);

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
//        String fileName = getTimeStamp() + "_" + Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get(position)]);
        File jiemiFile = DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get(position%DataHolder.Common_Img_Land_count)];



        Uri uri_item = Uri.fromFile(jiemiFile);
        android.util.Log.i("zukgit", "ivCover.setImageURI  position ="+ position + "   DataHodler.ImgiIndex="+DataHolder.Common_Img_Land_initPos + "   Img Pre  =" + uri_item.toString());
//        ivCover.setImageURI(uri_item);
        android.util.Log.i("zukgit", "ivCover.setImageURI  " + " Img End  =" + uri_item.toString());
        ivCover.setLongClickable(true);

        File rawFile = jiemiFile;
        initTouchListener(ivCover,rawFile,position);



        ivCover.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                android.util.Log.i("zukgit", "onLongClick  " + " isImgPause  =" + isImgPause);

                if(pictureImageView != null){
                    pictureImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    mDoubleTap_Count = 0;
                }

                pause_Img(!isImgPause);
                if(isImgPause){  // 如果暂停 记住 当前文件的名称 保存在 sharepreference 中
                    String selectedFileName =  jiemiFile.getName();
                    saveFileNameInSharedPreferences(selectedFileName);
                }
                gotoNextPage_loop(true);
                return false;
            }
        });
//        videoView.setMediaController(mc);
//        mc.setVisibility(View.VISIBLE);
//        File tempFile = new File(bgVideoPath);
//        videoView.setVideoPath(uri_item.getPath());

//        android.util.Log.i("zukgit","jiemiFile.exist() = "+ jiemiFile.exists());

        android.util.Log.i("zukgit", "X11 curPlayPos = " + curPlayPos + "   jiemiFile " + jiemiFile.getAbsolutePath());


//        android.util.Log.i("zukgit","curPlayPos = "+ curPlayPos + " bgVideoPath = "+ bgVideoPath + " tempFile = "+ tempFile.getAbsolutePath() + "  exist = "+ tempFile.exists() + " uri_item.getPath() =" + uri_item.getPath());


//        videoView.start();


        android.util.Log.i("zukgit", "autoShowImg setOnPreparedListener  curPlayPos = " + curPlayPos);

/*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                DataHolder.Common_Img_Land_initPos++;
                curPlayPos = DataHolder.Common_Img_Land_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                android.util.Log.i("zukgit", "X2  Img_Common_Fragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Common_Img_Land_initPos  =   " + DataHolder.Common_Img_Land_initPos);

                videoView.stopPlayback();
//                autoShowImg(curPlayPos, ivCover);
                autoShowImg(DataHolder.Common_Img_Land_initPos);
            }
        });*/


        android.util.Log.i("zukgit", "autoShowImg END  curPlayPos = " + curPlayPos);
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
            if(curPlayPos == DataHolder.Common_Img_Land_initPos){
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
                DataHolder.Common_Img_Land_initPos++;
                android.util.Log.i("zukgit", "AA DataHolder.Common_Img_Land_initPos   DataHolder.Common_Img_Land_initPos = " + DataHolder.Common_Img_Land_initPos);
                recyclerView.scrollToPosition(DataHolder.Common_Img_Land_initPos);
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
                        DataHolder.Common_Img_Land_initPos++;
                        onFinish_num++;
                        android.util.Log.i("zukgit", "【"+onFinish_num+ "】 = onFinish_num   DataHolder.Common_Img_Land_initPos   DataHolder.Common_Img_Land_initPos = " + DataHolder.Common_Img_Land_initPos);
//                    playCurImg(DataHolder.Common_Img_Land_initPos);
//                        recyclerView.scrollToPosition(DataHolder.Common_Img_Land_initPos);

                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {

                                recyclerView.smoothScrollToPosition(DataHolder.Common_Img_Land_initPos);

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
                            showDeleteFileDialog(rawFile ,  fileIndex);
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_UP :    //中间手指抬起 ( 还有手指在触摸中 )
                        break;

                }


                return false;
            }
        });
    }

    void showDeleteFileDialog(File mFile , int fileIndex ){

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Common_Img_Land_count)+"]"+"["+DataHolder.Common_Img_Land_count+"]");//文字
        alterDiaglog.setMessage("请确认删除以下文件:\n"+mFile.getAbsolutePath());//提示消息

        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"执行删除文件【"+mFile.getAbsolutePath()+"】",Toast.LENGTH_SHORT).show();

                if(fileIndex == 0){

                    DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get((fileIndex)%DataHolder.Common_Img_Land_count)] =      DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get((fileIndex+1)%DataHolder.Common_Img_Land_count)];


                }else if(fileIndex > 0 && fileIndex < DataHolder.Common_Img_Land_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get((fileIndex)%DataHolder.Common_Img_Land_count)] =      DataHolder.Common_Img_Land_file_list[DataHolder.Common_Img_Land_random_indexlist.get((fileIndex-1)%DataHolder.Common_Img_Land_count)];

                }

                mFile.delete();

            }
        });

        //显示
        alterDiaglog.show();


    }






}
