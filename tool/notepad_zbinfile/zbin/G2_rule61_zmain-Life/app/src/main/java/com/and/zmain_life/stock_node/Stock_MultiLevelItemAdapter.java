package com.and.zmain_life.stock_node;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.node.NodeImpl;


import java.util.Comparator;
import java.util.List;

import utils.ZUtil;



/* package */


class Stock_MultiLevelItemAdapter<T extends Stock_Node> extends
        RecyclerView.Adapter<Stock_MultiLevelItemAdapter.VHolder> {

    private T tree;
    Context mContext;
    int mCurrentLevel;


    public static String last_click_level1_Name = "";
    public static int last_click_level1_ID = -100;
    public static int last_click_level2_ID = -100;
    public static int last_click_level3_ID = -100;
    public static int last_click_level3_ParentID = -100;


    public static long origin_last_click_level3_ID = -100;


    public static int last_click_level3_ID_ViewPosition = -100;


    public static int long_click_loop_level1_ID = -100;
    public static int long_click_loop_level2_ID = -100;
    public static int long_click_loop_level3_ID = -100;
    public static String long_click_loop_level2_ID_Name = null;  // 第二个长按的选项的名称


    public static boolean isLevel3_Loop = false;

    public static int textview_level1_selected_bgcolor = -100;
    public static int textview_level2_selected_bgcolor = -100;
    public static int textview_level3_selected_bgcolor = -100;

    public static int textview_level1_selected_bgcolor_diss = -100;
    public static int textview_level2_selected_bgcolor_diss = -100;
    public static int textview_level3_selected_bgcolor_diss = -100;


    public void screenToLastClickNode3Position() {


    }


    public void showStaticProp(String tag) {
        Log.i("zukgit", "════════════════════════════════" + tag + " Stock_MultiLevelItemAdapter 显示静态变量开始 " + "════════════════════════════════");
        Log.i("zukgit", "10 last_click_level1_ID = " + last_click_level1_ID);
        Log.i("zukgit", "11 last_click_level2_ID = " + last_click_level2_ID);
        Log.i("zukgit", "12 last_click_level3_ID = " + last_click_level3_ID);
        Log.i("zukgit", "13 last_click_level3_ParentID = " + last_click_level3_ParentID);
        Log.i("zukgit", "14 last_click_level3_ID_ViewPosition = " + last_click_level3_ID_ViewPosition);
        Log.i("zukgit", "17 long_click_loop_level2_ID = " + long_click_loop_level2_ID);
        Log.i("zukgit", "18 long_click_loop_level2_ID_Name = " + long_click_loop_level2_ID_Name);
        Log.i("zukgit", "21 isLevel3_Loop = " + isLevel3_Loop);
        Log.i("zukgit", "22 long_click_loop_level3_ID = " + long_click_loop_level3_ID);
        Log.i("zukgit", "23 textview_level1_selected_bgcolor = " + textview_level1_selected_bgcolor);
        Log.i("zukgit", "24 textview_level2_selected_bgcolor = " + textview_level2_selected_bgcolor);
        Log.i("zukgit", "25 textview_level3_selected_bgcolor = " + textview_level3_selected_bgcolor);
        Log.i("zukgit", "26 textview_level1_selected_bgcolor_diss = " + textview_level1_selected_bgcolor_diss);
        Log.i("zukgit", "27 textview_level2_selected_bgcolor_diss = " + textview_level2_selected_bgcolor_diss);
        Log.i("zukgit", "28 textview_level3_selected_bgcolor_diss = " + textview_level3_selected_bgcolor_diss);

        Log.i("zukgit", "════════════════════════════════" + tag + " Stock_MultiLevelItemAdapter 显示静态变量结束 " + "════════════════════════════════");


    }


    private int INDEX;
    public Stock_MultiLevelPickerWindow mMultiLevelPickerWindow;

    void recoverySelectedTextViewColor() {
        if (textview_level1_selected_bgcolor != -100) {
            textview_level1_selected_bgcolor_diss = textview_level1_selected_bgcolor;
        }

        if (textview_level2_selected_bgcolor != -100) {
            textview_level2_selected_bgcolor_diss = textview_level2_selected_bgcolor;
        }

        if (textview_level3_selected_bgcolor != -100) {
            textview_level3_selected_bgcolor_diss = textview_level3_selected_bgcolor;
        }

    }


    Stock_MultiLevelItemAdapter(T tree, int index, Context context, Stock_MultiLevelPickerWindow xMultiLevelPickerWindow, int level) {
        this.tree = tree;
        this.INDEX = index;
        mContext = context;
        mCurrentLevel = level;
        if (xMultiLevelPickerWindow != null) {
            mMultiLevelPickerWindow = xMultiLevelPickerWindow;

        }


    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (mCurrentLevel == 3) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.stock_item_chioce_level3, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.stock_item_chioce, parent, false);
        }

        return new VHolder(view);
    }


    @Override
    public int getItemCount() {
        if (tree == null) return 0;
        List<? extends Stock_Node> children = tree.children();
        return children == null ? 0 : children.size();
    }


    void setNewData_WithNode(T data) {
        tree = (T) data;
        notifyDataSetChanged();
    }





    void setNewData(T data) {
        tree = data;
        notifyDataSetChanged();
    }

    class VHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        VHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.stock_title);
        }
    }

    private OnItemPickerListener mOnSelectListener;


    void setOnSelectListener(OnItemPickerListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    interface OnItemPickerListener<T extends Stock_Node> {
        void onSelect(T parent, T selectedChild);
    }

    T getTree() {
        return tree;
    }


    @Override
    public void onBindViewHolder(@NonNull Stock_MultiLevelItemAdapter.VHolder holder, int position) {
        if (tree == null) return;
        List<? extends Stock_Node> children = tree.children();
        if (children == null) return;
        //noinspection unchecked
        T item = (T) children.get(position);
        int cur_node_id = (int) item.id();
        long cur_node_long_id = item.id();
        if (item.level() == 2) {
            item.setapapterposition(position);


            // 设置第二个listview的显示内容
            holder.tvName.setText(item.text(position + 1, last_click_level1_Name));
//            holder.tvName.setText(calIndexPaddingText((position+1),item.text(position+1,last_click_level1_Name)));


        } else if (item.level() == 3) {
            holder.tvName.setText(item.text(position));   //  level3 显示的文字
            holder.tvName.setGravity(Gravity.CENTER);

        } else {
            holder.tvName.setText(item.text());   //  level3 显示的文字
            holder.tvName.setGravity(Gravity.CENTER);
        }

        // 获取 当前的 item_id 的 名称     意味着 每个 item的 内容可能变化  但是 id 不变
        // 这个 歌曲的 名称 是变化的
//        String nextSongName = DataHolder.getSongNameWithID(cur_node_long_id);


        if (item.id() == tree.selectedChild()) {

            holder.tvName.setTextColor(ContextCompat.getColor(holder.tvName.getContext(),
                    R.color.mlp_item_selected_text));
            if (INDEX == 0) {

                if (textview_level1_selected_bgcolor_diss != -100) {
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor_diss);
                    android.util.Log.i("zukgit", "设置颜色X1_1 Level_1 textview_level1_selected_bgcolor_diss = " + textview_level1_selected_bgcolor_diss);
                    item.setcolor(textview_level1_selected_bgcolor_diss);
                    textview_level1_selected_bgcolor_diss = -100;
                } else if (item.color() != -100) {
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgit", "设置颜色X1_2 Level_1 textview_level1_selected_bgcolor_diss = " + textview_level1_selected_bgcolor_diss);

                } else {
                    textview_level1_selected_bgcolor = ZUtil.getSelectedColor();
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor);
                    android.util.Log.i("zukgit", "设置颜色X1_3 Level_1 textview_level1_selected_bgcolor = " + textview_level1_selected_bgcolor);
                }

//

            } else if (INDEX == 1) {

                if (textview_level2_selected_bgcolor_diss != -100) {

                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor_diss);
                    textview_level2_selected_bgcolor_diss = -100;
                    android.util.Log.i("zukgitXA", "X112_XAA_3  第一种颜色 随机播放 播放完成回调  ");

                } else if (long_click_loop_level2_ID != -100 && long_click_loop_level2_ID == cur_node_id) {
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    textview_level2_selected_bgcolor = R.color.color_india_red;
                    android.util.Log.i("zukgitXA", "X112_XAA_3  第二种颜色 随机播放 播放完成回调  ");

                } else if (item.color() != -100) {
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA", "X112_XAA_3  第三种颜色 随机播放 播放完成回调  ");

                } else {

                    android.util.Log.i("zukgitXA", "X112_XAA_3  第四种颜色 随机播放 播放完成回调  ");

                    textview_level2_selected_bgcolor = ZUtil.getSelectedColor();
                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor);


                }

                if (cur_node_id == last_click_level2_ID) {
                    holder.tvName.setSelected(true);
                }

            } else if (INDEX == 2) {
                last_click_level3_ID_ViewPosition = position;

                android.util.Log.i("zukgitXA", "节点3__0 设置颜色:  position" + position + " last_click_level3_ID_ViewPosition=" + last_click_level3_ID_ViewPosition + " holder.tvName=" + holder.tvName.getText().toString());

                if (textview_level3_selected_bgcolor_diss != -100) {

                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor_diss);
                    android.util.Log.i("zukgitXA", "节点3__1 设置颜色:  textview_level3_selected_bgcolor_diss = " + textview_level3_selected_bgcolor_diss + "  holder.tvName=" + holder.tvName.getText().toString());
                    textview_level3_selected_bgcolor_diss = -100;
                } else if (item.color() != -100) {
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA", "节点3__2 设置颜色:  item.color() = " + item.color());
                } else {
                    textview_level3_selected_bgcolor = ZUtil.getSelectedColor();
                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor);
                    android.util.Log.i("zukgitXA", "节点3__3 设置颜色:  item.color() = " + item.color() + "   textview_level3_selected_bgcolor=" + textview_level3_selected_bgcolor + " holder.tvName=" + holder.tvName.getText().toString());

                }


                if (cur_node_id == last_click_level3_ID) {
                    holder.tvName.setSelected(true);
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA", "节点3__4 设置颜色:  item.color() = " + item.color());

                }
                if (long_click_loop_level3_ID != -100 && long_click_loop_level3_ID == cur_node_id) {
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    android.util.Log.i("zukgitXA", "节点3__5 设置颜色:  item.color() = " + item.color());

                }
                android.util.Log.i("zukgitXA", "节点3__5 设置颜色:  item.color() = " + item.color());

            }
        } else {
            holder.tvName.setTextColor(ContextCompat.getColor(
                    holder.tvName.getContext(),
                    R.color.mlp_item_unselected_text
            ));
            holder.tvName.setBackgroundResource(R.color.transparent);

            if (cur_node_id == last_click_level3_ID) {
                holder.tvName.setSelected(true);
            }
        }


        holder.tvName.setTag(item);
        String text_name =  holder.tvName.getText().toString();

        if (item.level() == 1) {   // 长按 第一项  改变颜色 背景颜色

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(text_name != null && text_name.startsWith("收藏板")){

                        show_delete_attention_stock_Dialog();

                        mMultiLevelPickerWindow.update();
                    }else{
                        mMultiLevelPickerWindow.onBackgroundChanged();

                    }

                    return false;
                }

            });
        }

        if (item.level() == 2) {   // 作者的 TextView  长按 选中它  说明 对于这个歌手 进行随机 播放

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level2_ID = cur_node_id;
                    long_click_loop_level2_ID_Name = holder.tvName.getText().toString();
                    if (long_click_loop_level2_ID_Name != null && long_click_loop_level2_ID_Name.contains("(")) {
                        long_click_loop_level2_ID_Name = long_click_loop_level2_ID_Name.substring(0, long_click_loop_level2_ID_Name.lastIndexOf("("));
                    }
                    DataHolder.long_click_loop_level2_ID_Name = long_click_loop_level2_ID_Name;

                    android.util.Log.i("zukgitXA", "X112_X_A1 随机播放 播放完成回调  ");

                    // 创建 Dialog 提示是否加入当前 NoteImpl 到 收藏列表里

                    show_attention_stock_Dialog(item, last_click_level1_Name);


                    mMultiLevelPickerWindow.update();


                    return false;
                }
            });
        }


        if (item.level() == 3) {

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level3_ID = cur_node_id;


                    return false;
                }
            });
        }


        holder.tvName.setOnClickListener(view -> {
            //noinspection unchecked
            T selectedChild = (T) view.getTag();


            if (mMultiLevelPickerWindow != null) {
                mMultiLevelPickerWindow.callbackSelected_Text(selectedChild);
            }

            if (mOnSelectListener != null) {
                notifyDataSetChanged();
                //noinspection unchecked
                mOnSelectListener.onSelect(tree, selectedChild);
            }
            String curText = selectedChild.text();
            int level = selectedChild.level();

            long cur_long_ID = selectedChild.id();
            long cur_long_ParentID = selectedChild.parentid();

            int curID = (int) selectedChild.id();

            android.util.Log.i("zukgit", "curText = " + curText + "   level=" + level + "");


            if (level == 1) {
                textview_level1_selected_bgcolor = ZUtil.getSelectedColor();
                selectedChild.setcolor(textview_level1_selected_bgcolor);
                last_click_level1_ID = curID;
                last_click_level1_Name = selectedChild.name();


            } else if (level == 2) {
                textview_level2_selected_bgcolor = ZUtil.getSelectedColor();
                selectedChild.setcolor(textview_level2_selected_bgcolor);
                last_click_level2_ID = curID;

                // 如果 点击了  长按的level2_node  按钮  如果当前 mSc
                android.util.Log.i("zukgitNode", " Node2 的 点击事件 ");

                if (selectedChild.children() == null || selectedChild.children().size() == 0) {

                    StockHolder.level2_Init_level3Node_Operation(selectedChild);

                    android.util.Log.i("zukgitNode_node2click", "BBB selectedChild.size()=" + selectedChild.children().size());

                }

                android.util.Log.i("zukgitNode_node2click", "AAA selectedChild.size()=" + selectedChild.children().size());

/*

                    String level2Json =   stockImpl.json();
                    Stock_NodeImpl mStock_NodeImpl = JSONObject.parseObject(level2Json,Stock_NodeImpl.class);
                    android.util.Log.i("refreshLevel3Adapter","mStock_NodeImpl ="+mStock_NodeImpl.toString());
                    android.util.Log.i("refreshLevel3Adapter",level2Json);
                    ArrayList<Stock_NodeImpl> node3List =   StockHolder.level2_Init_level3Node_Operation(mStock_NodeImpl);
*/

                // mMultiLevelPickerWindow   更新
                mMultiLevelPickerWindow.refreshLevel3Adapter(selectedChild);


            } else if (level == 3) {
                T selectedChild_level_3 = (T) view.getTag();
                android.util.Log.i("level3click", " selectedChild_level_3=" + selectedChild_level_3.toString());
                android.util.Log.i("zukgit 【Node3 的 点击事件】", " ______________________________________________  Node 点击 Name[" + holder.tvName.getText().toString() + "] 开始  ______________________________________________ ");
                last_click_level3_ID = (int) selectedChild_level_3.id();

                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口1_1   selectedChild_level_3=" + selectedChild_level_3.toString());
                // 如果当前点击的不是
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口1_2   long_click_loop_level3_ID=" + long_click_loop_level3_ID + "   cur_node_id=" + cur_node_id);
                if (long_click_loop_level3_ID != cur_node_id) {   // 取消单曲循环
                    isLevel3_Loop = false;
                    long_click_loop_level3_ID = -100;
                }
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口1_3   last_click_level3_ID=" + last_click_level3_ID + "   curID=" + curID + " last_click_level3_id=" + last_click_level3_ID + "   " + "  last_click_level2_ID=" + last_click_level2_ID + "  long_click_loop_level2_ID=" + long_click_loop_level2_ID + "  long_click_loop_level2_ID_Name=" + long_click_loop_level2_ID_Name);
                origin_last_click_level3_ID = last_click_level3_ID;
                if (last_click_level3_ID == selectedChild_level_3.id()) {  // 两次点击相同的 text 那么 执行暂停 播放 循环操作
                    last_click_level3_ID = (int) selectedChild_level_3.id();
                    last_click_level3_ParentID = (int) selectedChild_level_3.parentid();


                    android.util.Log.i("zukgitNode_Click", " ______________________________________________  Node 点击 结束_0点  Name[" + holder.tvName.getText().toString() + "] last_click_level3_ID=[" + last_click_level3_ID + "] ______________________________________________ ");

                    return;


                }
                // 是 Binder的Id     curID 是 按钮 绑定的 id

                //  如果点击了 不在 循环 内的 第三item 那么 变为 单曲顺序播放sa
                if (selectedChild.parentid() != long_click_loop_level2_ID && long_click_loop_level2_ID != -100) {

                    long_click_loop_level2_ID = -100;  // 取消  作者循环

                    mMultiLevelPickerWindow.update();
                    android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口2_3  ");
                    last_click_level3_ID = (int) selectedChild_level_3.id();
                    last_click_level3_ParentID = (int) selectedChild_level_3.parentid();
                    android.util.Log.i("zukgitNode_Click", " ______________________________________________  Node 点击 结束_1点   Name[" + holder.tvName.getText().toString() + "]  last_click_level3_ID=[" + last_click_level3_ID + "] ______________________________________________ ");

                    return;

                }

                long pre_last_click_level3_parentId = DataHolder.getParentId(last_click_level3_ID);
                textview_level3_selected_bgcolor = ZUtil.getSelectedColor();
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口2_4   last_click_level3_ID=" + last_click_level3_ID + "  pre_last_click_level3_parentId=" + pre_last_click_level3_parentId + "  curID=" + curID + "  cur_long_ParentID=" + cur_long_ParentID);
                selectedChild.setcolor(textview_level3_selected_bgcolor);

//                Stock_NodeImpl nextRandomNode_Default   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口2_5   " + " selectedChild_level_3.id()=" + selectedChild_level_3.id() + "   last_click_level3_ID=" + last_click_level3_ID + "  ");

/*                if(nextRandomNode_Default != null ){

                    if(selectedChild_level_3.id() == last_click_level3_ID){

                        android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_8 ");
                        last_click_level3_ID = (int)selectedChild_level_3.id();
                        return;
                    }else{
//                        next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(nextRandomNode_Default.id(),nextRandomNode_Default.parentid);

                    }


                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5   next_click_level3_ID_ViewPosition=");


                }*/
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口2  ");


//                android.util.Log.i("zukgitXA","X112_C 播放完成回调 mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口13  ");

                if (last_click_level3_ID == -100) {


                    mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    android.util.Log.i("zukgitXA", "C5_mediaPlayer  X1 开始播放文件 ==  " + "" + "  播放它");
                    android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口14  ");

                } else if (last_click_level3_ID != curID) {     //   如果 点击了 不同的 text 那么说明是 需要 另外 切换歌曲
                    android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口23  ");


                    long_click_loop_level3_ID = -100;  // 切换了歌曲 就相当于 把 循环取消掉了


                    android.util.Log.i("zukgitXA", "X0 click_recoveryMusic 开始播放文件 ==  " + "" + "  " + "  " + "  ");
                    mMultiLevelPickerWindow.update();


                    android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口32  ");

                    android.util.Log.i("zukgitXA", "X4 click_recoveryMusic 开始播放文件 ==  " + "" + "  " + "   " + "  " + " ");
                    mMultiLevelPickerWindow.onMusicTitlePlay(true);


                }


                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口33  ");


                // zukgit  显示路径 ""  Toast
                //  Toast.makeText(mContext.getApplicationContext(),"",Toast.LENGTH_SHORT).show();


                last_click_level3_ID = (int) selectedChild_level_3.id();
                last_click_level3_ParentID = (int) selectedChild_level_3.parentid();
                android.util.Log.i("zukgitNode_Click", " 【Node3 的 点击事件】_______出口35  last_click_level3_ID =" + last_click_level3_ID);
                android.util.Log.i("zukgitNode_Click", " ______________________________________________  Node_3 点击 结束_Over  Name[" + holder.tvName.getText().toString() + "] last_click_level3_ID=[" + last_click_level3_ID + "]______________________________________________ ");

            }
            android.util.Log.i("zukgitNode_Click", " 【Node 的 点击事件】_______出口36  ");


            android.util.Log.i("zukgitNode_Click", " 【Node 的 点击事件】_______出口38  ");


            android.util.Log.i("zukgitNode_Click", " ______________________________________________  All_Node 点击 结束_Over  Name[" + holder.tvName.getText().toString() + "]  level=" + level + "  last_click_level3_ID=[" + last_click_level3_ID + "] cur_node_id[" + cur_node_id + "]______________________________________________ ");

        });

/*  //          如果 当前的 id 就是 next_click_level3_ID
        if(isRandomPlayMusic && next_click_level3_ID != -100 && next_click_level3_ID == cur_node_long_id && holder.tvName != null   ){
            mNext_Level3_TextView = holder.tvName;
            next_click_level3_ID_ViewPosition = position;

            android.util.Log.i("zukgitXAsa_random_true"," 播放完成回调  isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());

        }else{

        }*/

        if (last_click_level3_ID != -100 && last_click_level3_ID == cur_node_id && holder.tvName != null) {

            if (!isLevel3_Loop) {
                holder.tvName.setBackgroundResource(item.color());

            }
            android.util.Log.i("zukgitXAsa_random_true", " 1AAA long_click_loop_level3_ID =" + long_click_loop_level3_ID + "    isRandomPlayMusic =" + " mNext_Level3_TextView.performClick()  " + " " + "   cur_node_long_id=" + cur_node_long_id + "   holder.tvName=" + holder.tvName.getText());
            Stock_NodeImpl selectedNode = (Stock_NodeImpl) holder.tvName.getTag();

        }

        if (long_click_loop_level2_ID != -100 && long_click_loop_level2_ID == cur_node_id && holder.tvName != null) {

            holder.tvName.setBackgroundResource(R.color.color_india_red);
//            android.util.Log.i("zukgitXAsa_random_true_2"," 2AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+" mNext_Level3_TextView.performClick()  "+"  "+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());

        }


        if (long_click_loop_level3_ID != -100 && long_click_loop_level3_ID == cur_node_id) {
            holder.tvName.setBackgroundResource(R.color.color_india_red);

        }


        if (item.level() == 3 && item.id() != last_click_level3_ID) {
            holder.tvName.setBackgroundResource(R.color.transparent);
            holder.tvName.setSelected(false);
            android.util.Log.i("zukgitXA", " BindColor___X0  holder.tvName=" + holder.tvName.getText().toString());

        } else if (item.level() == 3 && item.id() == last_click_level3_ID) {
            if (long_click_loop_level3_ID == last_click_level3_ID) {  // 长按颜色
                holder.tvName.setBackgroundResource(R.color.color_india_red);
                android.util.Log.i("zukgitXA", " BindColor___X0_1  holder.tvName=" + holder.tvName.getText().toString());

            } else {   // 自身颜色
                android.util.Log.i("zukgitXA", " BindColor___X1  holder.tvName=" + holder.tvName.getText().toString());
                if (item.color() != -100 && item.color() != 0) {
                    android.util.Log.i("zukgitXA", " BindColor___X2  holder.tvName=" + holder.tvName.getText().toString() + "  item.color()=" + item.color());
                    holder.tvName.setBackgroundResource(item.color());   // 滑动时 不变 使用
//                    holder.tvName.setBackgroundResource(ZUtil.getSelectedColor());  // 回前台 下一个 歌曲使用
//                    item.setcolor(ZUtil.getSelectedColor());

                } else {
                    android.util.Log.i("zukgitXA", " BindColor___X3    holder.tvName=" + holder.tvName.getText().toString() + "  last_click_level3_ID=" + last_click_level3_ID);
                    item.setcolor(ZUtil.getSelectedColor());
                    holder.tvName.setBackgroundResource(item.color());
                }

            }
            holder.tvName.setSelected(false);
        }

    }


    void show_delete_attention_stock_Dialog() {

        final AlertDialog.Builder deleteDiaglog = new AlertDialog.Builder(mContext);

        deleteDiaglog.setTitle("清空收藏板 [" + StockHolder.mAttentionStockImpl_List.size()+"]  ");//文字



        deleteDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        deleteDiaglog.setNeutralButton("清空", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int old_attention_count= StockHolder.mAttentionStockImpl_List.size() ;

             boolean flag =    StockHolder.clearup_attention_stock_operation();

                int new_attention_count= StockHolder.mAttentionStockImpl_List.size() ;

             if(flag){


                 mMultiLevelPickerWindow.mAdapter1.setNewData(StockHolder.Root_StockNodeImpl);
                 if ("收藏板".equals(last_click_level1_Name)) {
                     StockHolder.AttentionRank_Level1_Stock_NodeImpl.children.sort(StockHolder.pricedown_StockComparator);
                     mMultiLevelPickerWindow.mAdapter2.setNewData(StockHolder.AttentionRank_Level1_Stock_NodeImpl);
                 }
                 Toast.makeText(mContext, "执行收藏股票清空操作【"+old_attention_count+"】为空 剩余【"+new_attention_count+"】" + "_执行成功!"
                         , Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(mContext, "执行收藏股票清空操作【"+old_attention_count+"】为空 剩余【"+new_attention_count+"】" + "_执行失败!"
                         , Toast.LENGTH_SHORT).show();

             }


            }
        });


        deleteDiaglog.show();

    }


    void show_attention_stock_Dialog(Stock_Node item, String level1_click_name) {

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);
//        alterDiaglog.setTitle("删除文件:"+getPageTip()+"["+((fileIndex)%DataHolder.Common_Img_Port_count)+"]"+"["+DataHolder.Common_Img_Port_count+"]");//文字


        if ("收藏板".equals(level1_click_name)) {   // 收藏的板 是 删除的操作

            alterDiaglog.setTitle("从收藏板删除\n [" + item.ts_code_number() + "][" + item.name() + "]  :");//文字

            alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            alterDiaglog.setNeutralButton("取消收藏", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    boolean delete_flag = StockHolder.attention_stock_operation(item.ts_code_number(), false);  // false 标识 删除收藏


                    if (delete_flag) {
                        Toast.makeText(mContext, "执行取消收藏 股票【" + item.ts_code_number() + "】【" + item.name() + "】" + "操作成功"
                                , Toast.LENGTH_SHORT).show();


                        mMultiLevelPickerWindow.mAdapter1.setNewData(StockHolder.Root_StockNodeImpl);

                        if ("收藏板".equals(last_click_level1_Name)) {
                            StockHolder.AttentionRank_Level1_Stock_NodeImpl.children.sort(StockHolder.pricedown_StockComparator);
                            mMultiLevelPickerWindow.mAdapter2.setNewData(StockHolder.AttentionRank_Level1_Stock_NodeImpl);
                        }

                        mMultiLevelPickerWindow.update();


                    } else {

                        Toast.makeText(mContext, "执行取消收藏 股票【" + item.ts_code_number() + "】【" + item.name() + "】" + "操作失败\n(ts_code可能为空)"
                                , Toast.LENGTH_SHORT).show();

                    }


                }
            });


        } else {   //  其余的板 是 新增的操作


            alterDiaglog.setTitle("添加[" + item.ts_code_number() + "][" + item.name() + "] 到收藏板:");//文字

            alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            alterDiaglog.setNeutralButton("收藏", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    boolean add_flag = StockHolder.attention_stock_operation(item.ts_code_number(), true); // true 标识 添加收藏

                    if (add_flag) {
                        Toast.makeText(mContext, "执行收藏 股票【" + item.ts_code_number() + "】【" + item.name() + "】" + "操作成功"
                                , Toast.LENGTH_SHORT).show();


                        mMultiLevelPickerWindow.mAdapter1.setNewData(StockHolder.Root_StockNodeImpl);

                        if ("收藏板".equals(last_click_level1_Name) && StockHolder.AttentionRank_Level1_Stock_NodeImpl != null) {
                            StockHolder.AttentionRank_Level1_Stock_NodeImpl.children.sort(StockHolder.pricedown_StockComparator);
                            mMultiLevelPickerWindow.mAdapter2.setNewData(StockHolder.AttentionRank_Level1_Stock_NodeImpl); //

                        }

                        mMultiLevelPickerWindow.update();

                    } else {
                        Toast.makeText(mContext, "执行收藏 股票【" + item.ts_code_number() + "】【" + item.name() + "】" + "操作失败!\n(ts_code可能为空)"
                                , Toast.LENGTH_SHORT).show();


                    }


                }
            });


        }


        //显示
        alterDiaglog.show();

    }


}
