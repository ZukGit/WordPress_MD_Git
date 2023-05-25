package com.and.zmain_life.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.and.zmain_life.R;

import java.util.List;

/**
 * Created by think on 2018/9/11.
 */

public class English_YinBiao_GridAdapter extends BaseAdapter {
    private List<English_YinBiao_Bean> list;
    private LayoutInflater layoutInflater;

    public English_YinBiao_GridAdapter(List<English_YinBiao_Bean> list, LayoutInflater layoutInflater) {
        this.list = list;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public English_YinBiao_Bean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        English_YinBiao_Bean bean = list.get(position);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.english_yinbiao_grid_item_layout, parent, false);
            holder = new ViewHolder();
            assert convertView != null;
            holder.textview = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textview.setText(bean.name);

        return convertView;
    }

    class ViewHolder{
        TextView textview;
    }
}
