package com.and.zmain_life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.and.zmain_life.base.BaseRvAdapter;
import com.and.zmain_life.base.BaseRvViewHolder;
import com.and.zmain_life.R;
import com.and.zmain_life.bean.VideoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


public class GifAdapter extends BaseRvAdapter<VideoBean, GifAdapter.VideoViewHolder> {

    public GifAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(VideoViewHolder holder, VideoBean videoBean, int position) {
//        holder.controllerView.setVideoData(videoBean);

        holder.ivCover.setImageResource(videoBean.getCoverRes());

/*        holder.likeView.setOnLikeListener(() -> {
            if (!videoBean.isLiked()) {  //未点赞，会有点赞效果，否则无
//                holder.controllerView.like();
            }

        });*/
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gif, parent, false);
        return new VideoViewHolder(view);
    }

    public class VideoViewHolder extends BaseRvViewHolder {

/*        @BindView(R.id.likeview)
        LikeView likeView;*/

//        @BindView(R.id.controller)
//        ControllerView controllerView;

        @BindView(R.id.iv_cover_gif)
        GifImageView ivCover;

        public VideoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}