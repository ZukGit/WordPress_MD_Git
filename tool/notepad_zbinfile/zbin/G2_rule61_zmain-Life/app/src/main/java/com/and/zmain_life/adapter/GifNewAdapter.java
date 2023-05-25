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

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


public class GifNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> mRandomIndexList;
    private File[] mGifFileArr;
    private  Context  mContext ;



    public GifNewAdapter(Context curCotext, ArrayList<Integer> indexList , File[] mFileArr){
        mContext = curCotext;
        mRandomIndexList = indexList;
        mGifFileArr = mFileArr;
    }





    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gif, parent, false);
        return new GifViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof GifViewHolder) {

//           File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Home_Port_Img_file_list[DataHolder.Home_Port_Img_random_indexlist.get(DataHolder.Home_Port_Img_initPos&DataHolder.Home_Port_Img_count)]);
            File jiemiFile =  mGifFileArr[mRandomIndexList.get(position%mRandomIndexList.size())];
            Uri uri_item = Uri.fromFile(jiemiFile);
            ((GifViewHolder) holder).ivCover.setImageURI(uri_item);
        }
    }


    @Override
    public int getItemCount() {
        return mRandomIndexList.size()*3;
    }

    public class GifViewHolder extends BaseRvViewHolder {

/*        @BindView(R.id.likeview)
        LikeView likeView;*/

//        @BindView(R.id.controller)
//        ControllerView controllerView;

        @BindView(R.id.iv_cover_gif)
        GifImageView ivCover;

        public GifViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
