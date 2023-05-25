package com.and.zmain_life.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseRvAdapter;
import com.and.zmain_life.base.BaseRvViewHolder;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.StockSimulate_BankBean;
import com.and.zmain_life.bean.VideoBean;
import com.and.zmain_life.utils.EncryUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JPGAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> mRandomIndexList;
    private File[] mJpgFileArr;
    private  Context  mContext ;



    public  JPGAdapter(Context curCotext, ArrayList<Integer> indexList , File[] mFileArr){
        mContext = curCotext;
        mRandomIndexList = indexList;
        mJpgFileArr = mFileArr;
    }


    public void  refreshWithDataHolder( ArrayList<Integer> indexList , File[] mFileArr){
        mRandomIndexList = indexList;
        mJpgFileArr = mFileArr;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public JPGViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img, parent, false);
        return new JPGViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof JPGViewHolder) {

//           File jiemiFile = EncryUtil.createDecryByteData(DataHolder.Home_Port_Img_file_list[DataHolder.Home_Port_Img_random_indexlist.get(DataHolder.Home_Port_Img_initPos&DataHolder.Home_Port_Img_count)]);
            File jiemiFile =  mJpgFileArr[mRandomIndexList.get(position%mRandomIndexList.size())];
            Uri uri_item = Uri.fromFile(jiemiFile);
            ((JPGViewHolder) holder).ivCover.setImageURI(uri_item);

            Bitmap bitmap =((BitmapDrawable) ((ImageView) ((JPGViewHolder) holder).ivCover).getDrawable()).getBitmap();
            long byteCount =  bitmap.getByteCount();
            long max_byte_count =  100 * 1024 * 1024;   // 打鱼这个数值 会导致 crash 的
            // java.lang.RuntimeException: Canvas: trying to draw too large(228816000bytes) bitmap.   228816000
            if(byteCount > max_byte_count){

                File jiemiFile_2 = mJpgFileArr[mRandomIndexList.get((position+1)%mRandomIndexList.size())];

                uri_item = Uri.fromFile(jiemiFile_2);
                ((JPGViewHolder) holder).ivCover.setImageURI(uri_item);

            }

        }
    }


    @Override
    public int getItemCount() {
        if(mRandomIndexList == null ){
         return 0;
        }

        return    mRandomIndexList.size()*3;

    }

    public class JPGViewHolder extends BaseRvViewHolder {

/*        @BindView(R.id.likeview)
        LikeView likeView;*/

//        @BindView(R.id.controller)
//        ControllerView controllerView;

        @BindView(R.id.iv_cover_img)
        ImageView ivCover;

        public JPGViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
