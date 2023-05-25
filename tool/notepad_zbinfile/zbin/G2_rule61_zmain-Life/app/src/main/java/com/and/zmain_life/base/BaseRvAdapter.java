package com.and.zmain_life.base;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class BaseRvAdapter<T,VH extends BaseRvViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context context;
    protected List<T> mDatas;
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public BaseRvAdapter(Context context, List<T> datas) {
        this.context = context;
        mDatas = datas;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        //item点击事件获取
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                onItemClickListener.onItemClick(v, position);
            });
        }
        onBindData(holder,mDatas.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    protected abstract void onBindData(VH holder, T data, int position);

    public void addData(T data) {
        mDatas.add(data);
        notifyDataSetChanged();
    }

    public void addDataToPostion(T data,int position) {
        mDatas.add(position,data);
        notifyItemInserted(position);
    }

    public void addDatas(List<T> datas) {
        int oldCount  = mDatas.size();
        mDatas.addAll(datas);
        notifyItemRangeInserted(oldCount, datas.size());
    }

    public void removeDataFromPosition(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void onlyRemoveItem(int position) {
        mDatas.remove(position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void clearDatas() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
