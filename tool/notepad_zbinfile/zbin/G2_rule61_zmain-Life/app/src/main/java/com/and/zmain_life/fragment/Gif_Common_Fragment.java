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
import com.and.zmain_life.adapter.GifAdapter;
import com.and.zmain_life.adapter.GifNewAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;

import com.and.zmain_life.bean.Pause_Gif_Common_Event;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;
import rx.functions.Action1;

/*import com.and.zvideo_and_dy.view.CommentDialog;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDialog;*/


/*1.只有在显示的时候在进行 轮播  不显示 暂停轮播
2.在显示界面长按时 暂停轮播  再次长按时 启动轮播
3.用户上下滑动时 轮播不暂停*/


public class Gif_Common_Fragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    SharedPreferences top_gif_sp ;

    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = 0;

//    private GifAdapter adapter;

    private GifNewAdapter mGifNewAdapter;

//    private FullScreenVideoView videoView;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private GifImageView ivCurCover;




    @Override
    protected int setLayoutId() {
        return R.layout.fragment_gif;
    }

    void saveFileNameInSharedPreferences(String fileName){


        android.util.Log.i("zukgit", " 保存文件名称到 SharedPreferences Top_Gif fileName="+ fileName);
        SharedPreferences.Editor editor = top_gif_sp.edit();
        editor.putString(""+getTimeStamp(), fileName);
        // 4:提交
        editor.commit();

    }

    void ShowAllSharePreferences(){
        Map<String,String> sharePreferenceDataList = (HashMap<String,String>)top_gif_sp.getAll();
        Map.Entry<String , String> entryItem;
        int shareIndex = 1;
        if(sharePreferenceDataList != null){
            Iterator iterator = sharePreferenceDataList.entrySet().iterator();
            while( iterator.hasNext() ){
                entryItem = (Map.Entry<String , String>) iterator.next();
                String timeKeyStr = entryItem.getKey();   //Map的Key
                String fileNameStr = entryItem.getValue();  //Map的Value
                android.util.Log.i("SharedPreferences_Top_Gif["+shareIndex+"]: ",  fileNameStr);
                shareIndex++;
            }
        }

    }

    @Override
    protected void init() {


//        adapter = new GifAdapter(getActivity(), DataCreate.datas);

        mGifNewAdapter = new GifNewAdapter(getActivity(), DataHolder.Gif_random_indexlist,DataHolder.Gif_file_list);
        recyclerView.setAdapter(mGifNewAdapter);
        top_gif_sp = getActivity().getSharedPreferences("Top_Gif", getContext().MODE_PRIVATE);
        ShowAllSharePreferences();
//        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();

// s少时诵诗书所
        RxBus.getDefault().post(new Pause_Gif_Common_Event(false));
        //监听播放或暂停事件
        RxBus.getDefault().toObservable(Pause_Gif_Common_Event.class)
                .subscribe((Action1<Pause_Gif_Common_Event>) event -> {
                    if (event.isPlayOrPause()) {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.init B   ");
                        pause_gif(false);
                        gotoNextPage_loop(false);


                    } else {
                        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.init C   ");
                        pause_gif(true);
                    }
                });
        pause_gif(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " GifFragment.onResume  A   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
//        pause_gif(false);    // 进来就调用了
//         gotoNextPage_loop();


/*        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  B   MainActivity.curMainPage =" + MainActivity.curMainPage + "  MainFragment.curPage =" + MainFragment.curPage);
            android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onResume  CA ");
            videoView.start();
        }*/
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " GifFragment.onResume  CB ");
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " GifFragment.onResume  D ");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();
        android.util.Log.i("zukgit", "curPlayPos = " + curPlayPos + " RecommendFragment.onPause  ");
        pause_gif(true);
//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        pause_gif(true);
    }

    static int user_backup_num = 0;



    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(DataHolder.Gif_initPos);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                android.util.Log.i("zukgit", "onInitComplete = " + curPlayPos + " position  = 1 A ");
                playCurGif(DataHolder.Gif_initPos);
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

                android.util.Log.i("zukgit", "X1 GifFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Gif_initPos  =   " + DataHolder.Gif_initPos + " isBottom = " + isBottom);
                boolean up = curPlayPos > position;

                if (up) {
//                    int step = curPlayPos - position;
                    DataHolder.Gif_initPos = DataHolder.Gif_initPos - 1;

                } else {
//                    int step = position - curPlayPos;
                    DataHolder.Gif_initPos = DataHolder.Gif_initPos + 1;

                }


                if (DataHolder.Gif_initPos < 0 || position == 0) {
                    DataHolder.Gif_initPos = 0;
                }
                DataHolder.Gif_initPos = position;
                curPlayPos = DataHolder.Gif_initPos;
                android.util.Log.i("zukgit", "X2  GifFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Gif_initPos  =   " + DataHolder.Gif_initPos + " user_backup_num = " + user_backup_num);

                playCurGif(DataHolder.Gif_initPos);
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

    private void playCurGif(int position) {
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

        GifImageView ivCover = (GifImageView)rootView.findViewById(R.id.iv_cover_gif);

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(new CurUserBean(DataCreate.datas.get(position).getUserBean()));
        android.util.Log.i("zukgit", "E playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        curPlayPos = position;

        //切换播放器位置
//        dettachParentView(rootView);
        android.util.Log.i("zukgit", "F playCurVideo  curPlayPos = " + curPlayPos + " position  =" + position);
        autoShowGif(curPlayPos, ivCover);
//        autoShowGif(curPlayPos,ivCover);

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
    private void autoShowGif(int position , GifImageView ivCover) {

//        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
        MediaController mc = new MediaController(getContext());
//        String fileName = getTimeStamp() + "_" + DataHolder.gif_file_list[DataHolder.Gif_random_indexlist.get(position)].getName();
//        File jiemiFile = new File(video_file_list[0].getParentFile().getAbsolutePath()+File.separator+fileName);
//        File jiemiFile = EncryUtil.createDecryByteData(DataHolder.gif_file_list[DataHolder.Gif_random_indexlist.get(position)]);

        File jiemiFile = DataHolder.Gif_file_list[DataHolder.Gif_random_indexlist.get(position%DataHolder.Gif_count)];

        Uri uri_item = Uri.fromFile(jiemiFile);

//        ivCover.setImageURI(uri_item);


        File rawFile = jiemiFile;
        initTouchListener(ivCover,rawFile,position);

        ivCover.setLongClickable(true);
        ivCover.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                android.util.Log.i("zukgit", "onLongClick  " + " isGifPause  =" + isGifPause);


                pause_gif(!isGifPause);
                if(isGifPause){  // 如果暂停 记住 当前文件的名称 保存在 sharepreference 中
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


        android.util.Log.i("zukgit", "autoShowGif setOnPreparedListener  curPlayPos = " + curPlayPos);

/*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                DataHolder.Gif_initPos++;
                curPlayPos = DataHolder.Gif_initPos;
//                android.util.Log.i("zukgit","B curPlayPos = "+ curPlayPos);
                android.util.Log.i("zukgit", "X2  RecommendFragment.onPageSelected.curPlayPos = " + curPlayPos + " position  =   " + position + " DataHolder.Gif_initPos  =   " + DataHolder.Gif_initPos);

                videoView.stopPlayback();
//                autoShowGif(curPlayPos, ivCover);
                autoShowGif(DataHolder.Gif_initPos);
            }
        });*/


        android.util.Log.i("zukgit", "autoShowGif END  curPlayPos = " + curPlayPos);
    }

  volatile   boolean isGifPause;  // true -- pasue    false---move
    void pause_gif( boolean flag ){
        isGifPause = flag;
        if(isGifPause && loopPlay_Timer != null){
            loopPlay_Timer.cancel();
        }
    }


/*    void wait_1second_operation(){
     if(isGifPause){
    new CountDownTimer(1000, 500) {
        @Override
        public void onTick(long millisUntilFinished) { }
        @Override
        public void onFinish() {
            if(curPlayPos == DataHolder.Gif_initPos){
                pause_gif(false);
            }
        }
    }.start();
}
 }*/

  static  CountDownTimer  loopPlay_Timer ;
    void gotoNextPage_loop(boolean imediate){
        if(!isGifPause){
            if(loopPlay_Timer != null){
                loopPlay_Timer.cancel();
            }

            if(imediate){
                DataHolder.Gif_initPos++;
                recyclerView.scrollToPosition(DataHolder.Gif_initPos);
            }
            loopPlay_Timer =    new CountDownTimer(6000, 500) {
            @Override
            public void onTick(long millisUntilFinished) { }
            @Override
            public void onFinish() {

                if(isGifPause || curPlayPos == DataHolder.Gif_initPos) {
                    DataHolder.Gif_initPos++;
//                    playCurGif(DataHolder.Gif_initPos);
//                    recyclerView.scrollToPosition(DataHolder.Gif_initPos);

                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {

                            recyclerView.scrollToPosition(DataHolder.Gif_initPos);

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


    void initTouchListener(ImageView mImageView , File rawFile   , int fileIndex ){


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
        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Gif_count)+"]"+"["+DataHolder.Gif_count+"]");//文字
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

                    DataHolder.Gif_file_list[DataHolder.Gif_random_indexlist.get((fileIndex)%DataHolder.Gif_count)] =      DataHolder.Gif_file_list[DataHolder.Gif_random_indexlist.get((fileIndex+1)%DataHolder.Gif_count)];


                } else if(fileIndex > 0 && fileIndex < DataHolder.Gif_file_list.length){

                    // 我擦  这个 等式  尼玛 看着 受不了...
                    DataHolder.Gif_file_list[DataHolder.Gif_random_indexlist.get((fileIndex)%DataHolder.Gif_count)] =      DataHolder.Gif_file_list[DataHolder.Gif_random_indexlist.get((fileIndex-1)%DataHolder.Gif_count)];

                }

                mFile.delete();

            }
        });

        //显示
        alterDiaglog.show();


    }



}
