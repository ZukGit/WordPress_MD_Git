package com.and.zmain_life.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseActivity;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.utils.English_YinBiao_StartAssetsAudio;
import com.and.zmain_life.view.ScaleVideoView;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Video_FullScreen_Show_Activity extends BaseActivity {
    private Context mContext;
    private String mediaPath; // 播放文件的路径
    private File mideaFile;
    private VideoView fullVideoView ;
    private MediaController mediaControl;
    private LinearLayout   full_lineview ;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        hideStatusBar();
        setFullScreen();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        hideStatusBar();
        setFullScreen();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.video_fullscreen_show;
    }


    @Override
    protected void init() {
/*      // 打开 播放 视频的 文件
        Intent intent = new Intent(mContext,English_YinBiao_FaYinActivity.class);
        intent.putExtra("audioName",audioName);
        intent.putExtra("name",name);
        startActivity(intent);*/

        hideStatusBar();
        setFullScreen();
        mContext = this;

        mediaPath = getIntent().getStringExtra("path");
        Log.i("---zukgit---","mediaPath : " + mediaPath);

        mideaFile = new File(mediaPath);

        if(!mideaFile.exists()){
            Toast.makeText(this,"播放当前媒体文件不存在,请检查!\n"+mediaPath,Toast.LENGTH_SHORT);
            Log.i("---zukgit---","播放当前媒体文件不存在,请检查  mediaPath : " + mediaPath);
            finish();
        }

        full_lineview = (LinearLayout) findViewById(R.id.full_lineview);
        Uri uri_item = Uri.fromFile(mideaFile);
        mediaControl = new MediaController(this);
        ViewPagerLayoutManager  viewPagerLayoutManager = new ViewPagerLayoutManager(this);
        ViewGroup rootView = (ViewGroup) viewPagerLayoutManager.getChildAt(0);

        int childCount = viewPagerLayoutManager.getChildCount();
        Log.i("---zukgit---"," 添加到 RootView  childCount=" + childCount +"  ");

        for (int i = 0; i < childCount; i++) {
            View viewItem = viewPagerLayoutManager.getChildAt(i);
            System.out.println("View[ " + i + "] = " + viewItem.toString());
            Log.i("---zukgit---"," fullVideoView[ " + i + "] = " + viewItem.toString());

        }

//        fullVideoView = (ScaleVideoView)findViewById(R.id.full_videoview);
        fullVideoView  = new ScaleVideoView(this);

        if(full_lineview != null && fullVideoView != null){
            full_lineview.addView(fullVideoView, 0);
            Log.i("---zukgit---"," 添加到 full_lineview  : " + mediaPath +"  ");

        }

        fullVideoView.setMediaController(mediaControl);
        fullVideoView.setVisibility(View.VISIBLE);
         mediaControl.hide();

        fullVideoView.setVideoPath(uri_item.getPath());
        fullVideoView.setClickable(true);



        fullVideoView.setOnPreparedListener(mp -> {
                    //     mp.setLooping(true);  // 循环
                    mp.setLooping(true);

                });



        fullVideoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mediaControl != null){

                    mediaControl.setVisibility(View.VISIBLE);
                    android.util.Log.i("zukgit", " videoView.setOnClick ");


                }
            }
        });


        fullVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                fullVideoView.start();   // 重复 播放


            }
        });


        fullVideoView.start();



        Toast.makeText(this,"播放当前媒体文件:\n"+mideaFile.getAbsolutePath(),Toast.LENGTH_SHORT);
        Log.i("---zukgit---","播放当前媒体文件播放完成  mediaPath : " + mediaPath +"  OVER! ");

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.video_fullscreen_show);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}