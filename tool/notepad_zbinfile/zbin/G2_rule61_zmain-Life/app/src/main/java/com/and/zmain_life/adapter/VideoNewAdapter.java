package com.and.zmain_life.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseRvViewHolder;
import com.and.zmain_life.utils.EncryUtil;
import com.and.zmain_life.view.ScaleVideoView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> mRandomIndexList;
    private File[] mMp4FileArr;
    private  Context  mContext ;



    public VideoNewAdapter(Context curCotext, ArrayList<Integer> indexList , File[] mFileArr){
        mContext = curCotext;
        mRandomIndexList = indexList;
        mMp4FileArr = mFileArr;
    }





    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_video, parent, false);
        return new VideoViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof VideoViewHolder) {

//           File jiemiFile = EncryUtil.createDecryByteData(mMp4FileArr[mRandomIndexList.get(position%mRandomIndexList.size())]);
            File jiemiFile =  mMp4FileArr[mRandomIndexList.get(position%mRandomIndexList.size())];
            Uri uri_item = Uri.fromFile(jiemiFile);
            ((VideoViewHolder) holder).mScaleVideoView.setVideoPath(uri_item.getPath());
            ((VideoViewHolder) holder).mScaleVideoView.start();
        }
    }


    @Override
    public int getItemCount() {
        return mRandomIndexList.size()*3;
    }

    public class VideoViewHolder extends BaseRvViewHolder {

/*        @BindView(R.id.likeview)
        LikeView likeView;*/

//        @BindView(R.id.controller)
//        ControllerView controllerView;

        @BindView(R.id.rl_videoview)
        ScaleVideoView mScaleVideoView;

        public VideoViewHolder(View itemView) {
            super(itemView);
//            mScaleVideoView = itemView.findViewById(R.id.rl_videoview);

            ButterKnife.bind(this, itemView);
        }
    }
}
