package com.and.zmain_life.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 播放音频
 * Created by think on 2018/9/12.
 */

public class English_YinBiao_StartAssetsAudio {
    private static English_YinBiao_StartAssetsAudio ourInstance = null;
    private static MediaPlayer mediaPlayer = null;

    public static synchronized English_YinBiao_StartAssetsAudio getInstance() {
        if (ourInstance == null)
            ourInstance = new English_YinBiao_StartAssetsAudio();

        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();

        return ourInstance;
    }

    // 播放Assets中音频
    public static void playAssetsAudio(Context context, String audioName){
        if (mediaPlayer != null)//如果不为空  可能正在播放， 先释放
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();

        if(!TextUtils.isEmpty(audioName) && mediaPlayer != null){
            try {
                //播放 assets 音频文件
                AssetFileDescriptor fd = context.getAssets().openFd("audio/"+audioName+".mp3");
                mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void playHttpAudio(Context context, String audioUrl) {
        Log.i("---zukgit---","audioUrl : " + audioUrl);
        if (mediaPlayer != null)//如果不为空  可能正在播放， 先释放
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();    //准备
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("data/"+fileName+".json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
