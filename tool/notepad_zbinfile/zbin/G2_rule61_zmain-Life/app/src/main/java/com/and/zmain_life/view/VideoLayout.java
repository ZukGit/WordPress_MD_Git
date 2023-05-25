package com.and.zmain_life.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.VideoLayoutActivity;
import com.and.zmain_life.bean.DataHolder;

import java.io.IOException;

public class VideoLayout extends FrameLayout implements TextureView.SurfaceTextureListener {

    public String FILE_NAME;
    private int VIDEO_GRAVITY;
    public TextureView videoSurface;
    private float mVideoWidth;
    private float mVideoHeight;
    private String TAG = "VideoLayout";
    public MediaPlayer mMediaPlayer;
    private boolean isUrl;
    private boolean IS_LOOP;
    private boolean SOUND;
    private boolean ADJUSTVIEWBOUNDS = false;


    // <enum name="start" value="0" />
    // <enum name="end" value="1" />
    // <enum name="centerCrop" value="2" />
    // <enum name="fitXY" value="3" />
    // <enum name="centerInside" value="3" />

    public static enum VGravity {
        start,
        end,
        centerCrop,
        fitXY,
        centerInside;

        public int getValue() {
            switch (this) {
                case end:
                    return 1;
                case start:
                    return 0;
                case centerCrop:
                    return 2;
                case centerInside:
                    return 4;
                case fitXY:
                default:
                    return 3;
            }
        }
    }

    public VideoLayout(@NonNull Context context) {
        super(context);
    }

    //*you can also get mediaplayer and set it manually*/
    public void setSound(boolean sound) {
        this.SOUND = sound;
        if (mMediaPlayer != null)
            try {
                if (!sound) {
                    mMediaPlayer.setVolume(0f, 0f);
                } else {
                    mMediaPlayer.setVolume(.5f, .5f);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void setAdjustViewBounds(boolean b) {
        this.ADJUSTVIEWBOUNDS = b;
    }

    public VideoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        System.out.println("ZVideoLayout_C_1   VideoLayout begin ");
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VideoLayout, 0, 0);
        try {
            FILE_NAME = a.getString(R.styleable.VideoLayout_path_or_url);
            VIDEO_GRAVITY = a.getInteger(R.styleable.VideoLayout_video_gravity, 2);
            IS_LOOP = a.getBoolean(R.styleable.VideoLayout_loop, true);
            SOUND = a.getBoolean(R.styleable.VideoLayout_sound, false);
            ADJUSTVIEWBOUNDS = a.getBoolean(R.styleable.VideoLayout_adjustViewBounds, false);
        } finally {
            a.recycle();
        }
        System.out.println("ZVideoLayout_C_2   VideoLayout begin  FILE_NAME="+FILE_NAME);
        if(DataHolder.is_all_same_videolayout){
            if(DataHolder.same_url_videolayout == null){
                DataHolder.same_url_videolayout =  VideoLayoutActivity.getNextFileUrl();
            }
            FILE_NAME = DataHolder.same_url_videolayout;
        }else{
            FILE_NAME = VideoLayoutActivity.getNextFileUrl();
        }

        if (TextUtils.isEmpty(FILE_NAME)) {
            return;
        }

        isUrl = FILE_NAME.contains("http://") || FILE_NAME.contains("https://") || FILE_NAME.startsWith("/data/user/") || FILE_NAME.startsWith("/sdcard/");
        initViews();
        System.out.println("ZVideoLayout_C_3   VideoLayout begin  FILE_NAME="+FILE_NAME+"  isUrl="+isUrl);

        addView(videoSurface);
        setListeners();
    }


    private void initViews() {
        mMediaPlayer = new MediaPlayer();
        videoSurface = new TextureView(getContext());

    }

    private void setListeners() {
        videoSurface.setSurfaceTextureListener(this);
    }


    private void updateTextureViewSize(int viewWidth, int viewHeight) {
        float scaleX = 1.0f;
        float scaleY = 1.0f;

        int pivotPointX = (VIDEO_GRAVITY == 0) ? 0 : (VIDEO_GRAVITY == 1) ? viewWidth : viewWidth / 2;
        int pivotPointY = viewHeight / 2;

        Matrix matrix = new Matrix();

        if (VIDEO_GRAVITY == 4) {
            pivotPointX = 0;
            pivotPointY = viewHeight / 2;
            scaleY = (viewWidth / mVideoWidth) / (viewHeight / mVideoHeight);
            matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);
        } else {
            if (mVideoWidth > viewWidth && mVideoHeight > viewHeight) {
                scaleX = mVideoWidth / viewWidth;
                scaleY = mVideoHeight / viewHeight;
            } else if (mVideoWidth < viewWidth && mVideoHeight < viewHeight) {
                scaleY = viewWidth / mVideoWidth;
                scaleX = viewHeight / mVideoHeight;
            } else if (viewWidth > mVideoWidth) {
                scaleY = (viewWidth / mVideoWidth) / (viewHeight / mVideoHeight);
            } else if (viewHeight > mVideoHeight) {
                scaleX = (viewHeight / mVideoHeight) / (viewWidth / mVideoWidth);
            }

            matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);
        }


        videoSurface.setTransform(matrix);
        videoSurface.setLayoutParams(new LayoutParams(viewWidth, viewHeight));
    }


    private void surfaceSetup() {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        if (ADJUSTVIEWBOUNDS) {

            if (mVideoHeight < screenHeight && mVideoWidth < screenWidth) {
                updateTextureViewSize((int) mVideoWidth, (int) mVideoHeight);
            } else {
                float height = (screenWidth / mVideoWidth) * mVideoHeight;
                updateTextureViewSize(screenWidth, (int) height);
            }

        } else updateTextureViewSize(screenWidth, screenHeight);


    }

    private void surfaceAvailableWorkers(SurfaceTexture surfaceTexture) {


        System.out.println("ZVideoLayout_C_5   VideoLayout begin  FILE_NAME="+FILE_NAME+"  isUrl="+isUrl);
        Surface surface = new Surface(surfaceTexture);

        try {

            mMediaPlayer.setDataSource(FILE_NAME);

            if (!SOUND)
                mMediaPlayer.setVolume(0f, 0f);


            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(IS_LOOP);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(mp -> {
                mp.start();
                mVideoHeight = mp.getVideoHeight();
                mVideoWidth = mp.getVideoWidth();
                if (VIDEO_GRAVITY != 3) {
                    surfaceSetup();
                }
            });


        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException ignored) {

        }
    }

    private void changeVideo() {
        try {
            onDestroyVideoLayout();
            mMediaPlayer = new MediaPlayer();

                mMediaPlayer.setDataSource(FILE_NAME);
                System.out.println("ZVideoLayout_C_4.1  changeVideo FILE_NAME="+FILE_NAME);


            if (!SOUND)
                mMediaPlayer.setVolume(0f, 0f);

            mMediaPlayer.setLooping(IS_LOOP);
            mMediaPlayer.setSurface(new Surface(videoSurface.getSurfaceTexture()));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(MediaPlayer::start);

        } catch (IllegalArgumentException | IOException | IllegalStateException | SecurityException ignored) {

        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        surfaceAvailableWorkers(surface);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }


    public void onDestroyVideoLayout() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            } catch (IllegalStateException e) {
            }

        }
    }

    public void onResumeVideoLayout() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.start();
            } catch (IllegalStateException ignored) {

            }
    }

    public void onPauseVideoLayout() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.pause();
            } catch (IllegalStateException ignored) {

            }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public TextureView getVideoSurface() {
        return videoSurface;
    }

    public String getPathOrUrl( ) {
        return     this.FILE_NAME;

    }
    public void setPathOrUrl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;

        isUrl = FILE_NAME.contains("http://") || FILE_NAME.contains("https://") || FILE_NAME.startsWith("/data/user/")  || FILE_NAME.startsWith("/sdcard/");


        if (videoSurface == null) {
            initViews();
            addView(videoSurface);
            setListeners();
        }

        if (videoSurface != null) {
            changeVideo();
        }
    }

    public void setIsLoop(boolean IS_LOOP) {
        this.IS_LOOP = IS_LOOP;
    }

    public void setGravity(VGravity gravity) {
        VIDEO_GRAVITY = gravity.getValue();
    }
}
