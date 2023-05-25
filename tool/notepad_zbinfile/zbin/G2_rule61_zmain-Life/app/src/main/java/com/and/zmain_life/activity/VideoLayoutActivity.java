package com.and.zmain_life.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseActivity;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_Home_Port_ImgEvent;
import com.and.zmain_life.fragment.MainFragment;
import com.and.zmain_life.utils.EncryUtil;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.VideoLayout;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VideoLayoutActivity extends BaseActivity {

/*    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spoti_vertical_3x3)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        chronometer.start()
    }*/

    void ShowToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();

        for (int j = 0; j < allVideoLayoutList.size(); j++) {
            VideoLayout  mVideoLayout_Item_j = allVideoLayoutList.get(j);

            try {
                System.out.println("ZVideoLayout_onPause  DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout );
                mVideoLayout_Item_j.mMediaPlayer.pause();
                mVideoLayout_Item_j.mMediaPlayer.reset();
                mVideoLayout_Item_j.mMediaPlayer.release();

            } catch (Exception e) {
                System.out.println("ZVideoLayout DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+" exception__onClick "+ e);
                throw new RuntimeException(e);

            }

        }





        finish();




    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int j = 0; j < allVideoLayoutList.size(); j++) {
            VideoLayout  mVideoLayout_Item_j = allVideoLayoutList.get(j);

            try {
                System.out.println("ZVideoLayout_onPause  DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout );
                mVideoLayout_Item_j.mMediaPlayer.pause();

            } catch (Exception e) {
                System.out.println("ZVideoLayout DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+" exception__onClick "+ e);
                throw new RuntimeException(e);

            }

        }



    }

    ArrayList<VideoLayout> allVideoLayoutList ;

    void InitIntentValue(){

        String row_str = getIntent().getStringExtra("row");
        Log.i("---ZVideoLayout_1---","row : " + row_str);
        String column_str   = getIntent().getStringExtra("column");
        Log.i("---ZVideoLayout_2---","column : " + column_str);

        String issame_str   = getIntent().getStringExtra("issame");
        Log.i("---ZVideoLayout_3---","issame_str : " + issame_str);

        String srcfragmentindex_str   = getIntent().getStringExtra("srcfragmentindex");
        Log.i("---ZVideoLayout_4---","srcfragmentindex_str : " + srcfragmentindex_str);


        if(DataHolder.isNumeric(row_str)){
            DataHolder.row_count_videolayout = Integer.parseInt(row_str);
        }


        if(DataHolder.isNumeric(column_str)){
            DataHolder.column_count_videolayout =  Integer.parseInt(column_str);
        }

        if("true".equals(issame_str)){
            DataHolder.is_all_same_videolayout = true;
        } else if("false".equals(issame_str)){
            DataHolder.is_all_same_videolayout = false;
        }

        if(DataHolder.isNumeric(srcfragmentindex_str)){
            DataHolder.srcFragmentIndex_videolayout = Integer.parseInt(srcfragmentindex_str);;
            DataHolder.allMatchFileArr_videolayout =  MainFragment.getFragmentBaseFileArr( DataHolder.srcFragmentIndex_videolayout);
        }

        if(DataHolder.allMatchFileArr_videolayout == null){
            Log.i("---ZVideoLayout_5---","无法读取到对应的Fragment对应的 DataHolder.allMatchFileArr_videolayout   == null  srcfragmentindex_str=" + srcfragmentindex_str+"   ");
        } else{
            Log.i("---ZVideoLayout_C_0---","ZVideoLayout_C_0 DataHolder.allMatchFileArr_videolayout.length = "+ DataHolder.allMatchFileArr_videolayout.length);

        }


    }







    protected int setLayoutId() {


        System.out.println("ZVideoLayout_    DataHolder.row_count_videolayout = "+  DataHolder.row_count_videolayout +"    DataHolder.column_count_videolayout ="+  DataHolder.column_count_videolayout  +"    DataHolder.is_all_same_videolayout="+    DataHolder.is_all_same_videolayout);
         String layoutType = "22";
         if( DataHolder.row_count_videolayout  != 0 && DataHolder.column_count_videolayout != 0){
             layoutType = ( DataHolder.row_count_videolayout +"")+(""+DataHolder.column_count_videolayout);
         }

         switch (layoutType){
             case "11":
                 return R.layout.videolayout_1x1;

             case "12":
                 return R.layout.videolayout_1x2;

             case "13":
                 return R.layout.videolayout_1x3;

             case "14":
                 return R.layout.videolayout_1x4;

             case "21":
                 return R.layout.videolayout_2x1;

             case "22":
                 return R.layout.videolayout_2x2;

             case "23":
                 return R.layout.videolayout_2x3;

             case "24":
                 return R.layout.videolayout_2x4;

             case "31":
                 return R.layout.videolayout_3x1;

             case "32":
                 return R.layout.videolayout_3x2;

             case "33":
                 return R.layout.videolayout_3x3;

             case "34":
                 return R.layout.videolayout_3x4;

             case "41":
                 return R.layout.videolayout_4x1;

             case "42":
                 return R.layout.videolayout_4x2;

             case "43":
                 return R.layout.videolayout_4x3;

             case "44":
                 return R.layout.videolayout_4x4;

             default:

         }

        return R.layout.videolayout_2x2;
    }

    /**
     * @note 获取该activity所有view
     */

    public List getAllChildViews() {
        View view = this.getWindow().getDecorView();

        return getAllChildViews(view);

    }

    private List getAllChildViews(View view) {
        List allchildren = new ArrayList();

        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);

                allchildren.add(viewchild);

                allchildren.addAll(getAllChildViews(viewchild));

            }

        }

        return allchildren;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InitIntentValue();
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();

/*

        if(DataHolder.is_all_same_videolayout){

            String fileUrl_same = getNextFileUrl();
            for (int i = 0; i < allVideoLayoutList.size(); i++) {
                VideoLayout mVideoLayout_Item =     allVideoLayoutList.get(i);
                mVideoLayout_Item.videoSurface.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                    @Override
                    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {

                        mVideoLayout_Item.mMediaPlayer.reset();

                        mVideoLayout_Item.FILE_NAME = fileUrl_same;
                        mVideoLayout_Item.setPathOrUrl(fileUrl_same);
                        try {

                            mVideoLayout_Item.mMediaPlayer.setDataSource(fileUrl_same);
                            mVideoLayout_Item.mMediaPlayer.setSurface( new Surface(surface));
                            mVideoLayout_Item.mMediaPlayer.prepareAsync();
                            mVideoLayout_Item.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    System.out.println("A ZVideoLayout mp.start ");
                                    mp.start();
                                }
                            });
//                            mVideoLayout_Item.mMediaPlayer.start();
                            System.out.println("A ZVideoLayout Finish! ");
                        }catch (Exception e){
                            System.out.println("ZVideoLayout ExceptionA ="+ e);
                        }

                    }

                    @Override
                    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

                    }

                    @Override
                    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                        return false;
                    }

                    @Override
                    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

                    }
                });


            }
        } else {
            for (int i = 0; i < allVideoLayoutList.size(); i++) {
                VideoLayout mVideoLayout_Item =     allVideoLayoutList.get(i);

                mVideoLayout_Item.videoSurface.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                    @Override
                    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {

                    }

                    @Override
                    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

                        String fileUrl_notsame = getNextFileUrl();
                        mVideoLayout_Item.mMediaPlayer.reset();
                        mVideoLayout_Item.setPathOrUrl(fileUrl_notsame);

                        try{
                            mVideoLayout_Item.FILE_NAME = fileUrl_notsame;
                            mVideoLayout_Item.mMediaPlayer.setSurface(new Surface(surface));
                            mVideoLayout_Item.mMediaPlayer.setDataSource(fileUrl_notsame);
                            mVideoLayout_Item.mMediaPlayer.prepareAsync();
                            mVideoLayout_Item.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                    System.out.println("B ZVideoLayout mp.start ");
                                }
                            });
//                            mVideoLayout_Item.mMediaPlayer.start();
                            System.out.println("B ZVideoLayout Finish! ");
                        }catch (Exception e){
                            System.out.println("ZVideoLayout ExceptionB ="+ e);
                        }

                    }

                    @Override
                    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                        return false;
                    }

                    @Override
                    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

                    }
                });




            }
        }
*/

    }

    @Override
    protected void init() {
        allVideoLayoutList = new ArrayList<VideoLayout>();

        setFullScreen();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);




        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ViewPagerLayoutManager  viewPagerLayoutManager = new ViewPagerLayoutManager(this);
        List allChildViewList =  getAllChildViews();
        int childCount = allChildViewList.size();
        System.out.println("ZVideoLayout_childCount = "+childCount);
        for (int i = 0; i < childCount; i++) {
            View viewItem = (View)allChildViewList.get(i);
            System.out.println("ZVideoLayout[ " + i + "] = " +viewItem.getClass().getSimpleName()+"     toString()=" + viewItem.toString());

            if("VideoLayout".equals(viewItem.getClass().getSimpleName())){
                allVideoLayoutList.add((VideoLayout) allChildViewList.get(i));
            }
        }
        System.out.println("ZVideoLayout_allVideoLayoutList.size() = "+allVideoLayoutList.size());
        System.out.println("A_ZVideoLayout_    DataHolder.row_count_videolayout = "+  DataHolder.row_count_videolayout +"    DataHolder.column_count_videolayout ="+  DataHolder.column_count_videolayout  +"    DataHolder.is_all_same_videolayout="+    DataHolder.is_all_same_videolayout);

        for (int i = 0; i < allVideoLayoutList.size(); i++) {

            VideoLayout mVideoLayout_Item =     allVideoLayoutList.get(i);
            mVideoLayout_Item.setIsLoop(false);
            final int video_index = i ;
            mVideoLayout_Item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ShowToast("ZVideoLayout_onLongClick["+video_index+"_"+allVideoLayoutList.size()+"] _isSame["+DataHolder.is_all_same_videolayout+"]_【"+DataHolder.row_count_videolayout+"x"+DataHolder.column_count_videolayout+"】_FileLength["+DataHolder.allMatchFileArr_videolayout.length+"]=["+mVideoLayout_Item.getPathOrUrl()+"]");

                    return false;
                }
            });


            mVideoLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowToast("ZVideoLayout_onClick["+video_index+"_"+allVideoLayoutList.size()+"] _isSame["+DataHolder.is_all_same_videolayout+"]_【"+DataHolder.row_count_videolayout+"x"+DataHolder.column_count_videolayout+"】_FileLength["+DataHolder.allMatchFileArr_videolayout.length+"]=["+mVideoLayout_Item.getPathOrUrl()+"]");

                    if(DataHolder.is_all_same_videolayout){  //   点击一个  所有的 VideoLayout 都变化
                        String same_url =  getNextFileUrl();
                        for (int j = 0; j < allVideoLayoutList.size(); j++) {
                            VideoLayout  mVideoLayout_Item_j = allVideoLayoutList.get(j);

                            try {
                                System.out.println("ZVideoLayout_onCompletion  DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+"  videoLayout_index="+video_index+"   FILE_NAME="+mVideoLayout_Item.FILE_NAME );
                                mVideoLayout_Item_j.FILE_NAME =  same_url;
                                mVideoLayout_Item_j.mMediaPlayer.reset();
                                mVideoLayout_Item_j.mMediaPlayer.setDataSource(mVideoLayout_Item_j.FILE_NAME);
                                mVideoLayout_Item_j.mMediaPlayer.prepareAsync();
                                mVideoLayout_Item_j.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        mp.start();
                                    }
                                });

                            } catch (Exception e) {
                                System.out.println("ZVideoLayout DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+" exception__onClick "+ e);
                                throw new RuntimeException(e);

                            }

                        }


                    } else {  //   点击一个  只有一个 VideoLayout 变化

                        mVideoLayout_Item.FILE_NAME =  getNextFileUrl();


                        try {
                            System.out.println("ZVideoLayout_onCompletion videoLayout_index="+video_index+"   FILE_NAME="+mVideoLayout_Item.FILE_NAME );

                            mVideoLayout_Item.mMediaPlayer.reset();
                            mVideoLayout_Item.mMediaPlayer.setDataSource(mVideoLayout_Item.FILE_NAME);
                            mVideoLayout_Item.mMediaPlayer.prepareAsync();
                            mVideoLayout_Item.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });

                        } catch (Exception e) {
                            System.out.println("ZVideoLayout exception__onClick "+ e);
                            throw new RuntimeException(e);

                        }

                    }


                }
            });

            if(DataHolder.is_all_same_videolayout ){
                System.out.println("ZVideoLayout_C_4_issame   mVideoLayout_Item.FileName="+mVideoLayout_Item.FILE_NAME);

                if( i == 0 ){   // 相同的播放  只 监听 第一个窗口
                    addMediaCompileListener_For_All(mVideoLayout_Item);
                }
            } else {  // 每个窗口 都 监听
                System.out.println("ZVideoLayout_C_4_notsame   mVideoLayout_Item.FileName="+mVideoLayout_Item.FILE_NAME);

                addMediaCompileListener_For_EveryOne(mVideoLayout_Item);
            }
        }

        try{

        }catch (Exception e){
            System.out.println(" ExceptionA ="+ e);
        }


    }


    void addMediaCompileListener_For_All(VideoLayout mVideoLayout_Item){
        mVideoLayout_Item.mMediaPlayer.setOnCompletionListener(null);
        mVideoLayout_Item.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {


                if(DataHolder.is_all_same_videolayout){  //   点击一个  所有的 VideoLayout 都变化
                    String same_url =  getNextFileUrl();
                    DataHolder.same_url_videolayout = same_url;
                    for (int j = 0; j < allVideoLayoutList.size(); j++) {
                        VideoLayout  mVideoLayout_Item_j = allVideoLayoutList.get(j);

                        try {
                            System.out.println("ZVideoLayout_onCompletion  DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+"  videoLayout_index="+j  +"   FILE_NAME="+mVideoLayout_Item.FILE_NAME );
                            mVideoLayout_Item_j.FILE_NAME =  same_url;
                            mVideoLayout_Item_j.mMediaPlayer.reset();
                            mVideoLayout_Item_j.mMediaPlayer.setDataSource(mVideoLayout_Item_j.FILE_NAME);
                            mVideoLayout_Item_j.mMediaPlayer.prepareAsync();
                            mVideoLayout_Item_j.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });

                        } catch (Exception e) {
                            System.out.println("ZVideoLayout onCompletion DataHolder.is_all_same_videolayout="+DataHolder.is_all_same_videolayout+" exception__onClick "+ e);
                            throw new RuntimeException(e);

                        }

                    }


                }




            }
        });

    }


    void addMediaCompileListener_For_EveryOne(VideoLayout mVideoLayout_Item){
        mVideoLayout_Item.mMediaPlayer.setOnCompletionListener(null);
        mVideoLayout_Item.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                int allMatchFileArr_length = DataHolder.allMatchFileArr_videolayout.length;
                Random rd = new Random();

                int match_index = rd.nextInt(allMatchFileArr_length) ;

                File match_File = DataHolder.allMatchFileArr_videolayout[match_index];




                Uri uri_item = Uri.fromFile(match_File);

                mVideoLayout_Item.FILE_NAME = uri_item.getPath();

                try {
                    System.out.println("ZVideoLayout_onCompletion match_index="+match_index+"   uri_item="+uri_item.getPath());

                    mp.reset();
                    mp.setDataSource(uri_item.getPath());
                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                } catch (Exception e) {
                    System.out.println("exception__"+ e);
                    throw new RuntimeException(e);

                }

            }
        });

    }
   public static String getNextFileUrl(){

        int allMatchFileArr_length = DataHolder.allMatchFileArr_videolayout.length;
        Random rd = new Random();

        int match_index = rd.nextInt(allMatchFileArr_length) ;

        File match_File = DataHolder.allMatchFileArr_videolayout[match_index];

        Uri uri_item = Uri.fromFile(match_File);
        System.out.println("ZVideoLayout getNextFileUrl  uri_item="+uri_item.getPath());
        return uri_item.getPath();

    }


}