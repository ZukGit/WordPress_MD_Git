package com.and.zmain_life.pure_node;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.DataHolder;



import java.util.List;

import utils.ZUtil;



/* package */


class Pure_MultiLevelItemAdapter<T extends Pure_Node> extends
        RecyclerView.Adapter<Pure_MultiLevelItemAdapter.VHolder> {



/*
    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        if (tree == null) return;
        List<? extends Pure_Node> children = tree.children();
        if (children == null) return;
        //noinspection unchecked
        T item = (T) children.get(position);
        int cur_node_id = (int)item.id();
        long cur_node_long_id = item.id();
        long cur_node_long_parentid = item.parentid();
        holder.tvName.setText(item.text());
        // 获取 当前的 item_id 的 名称     意味着 每个 item的 内容可能变化  但是 id 不变
        // 这个 歌曲的 名称 是变化的
//        String nextSongName = DataHolder.getSongNameWithID(cur_node_long_id);






        if (item.id() == tree.selectedChild()) {

            holder.tvName.setTextColor(ContextCompat.getColor(holder.tvName.getContext(),
                    R.color.mlp_item_selected_text));
            if (INDEX == 0) {

                if(textview_level1_selected_bgcolor_diss != -100 ){
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor_diss);
                    Log.i("zukgit","设置颜色X1_1 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);
                    item.setcolor(textview_level1_selected_bgcolor_diss);
                    textview_level1_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    Log.i("zukgit","设置颜色X1_2 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);

                }    else{
                    textview_level1_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor);
                    Log.i("zukgit","设置颜色X1_3 Level_1 textview_level1_selected_bgcolor = "+textview_level1_selected_bgcolor);
                }

//

            } else if (INDEX == 1) {

                if(textview_level2_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor_diss);
                    textview_level2_selected_bgcolor_diss = -100;
                    Log.i("zukgitXA","X112_XAA_3  第一种颜色 随机播放 播放完成回调  ");

                } else if(long_click_loop_level2_ID  != -100 && long_click_loop_level2_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    textview_level2_selected_bgcolor = R.color.color_india_red;
                    Log.i("zukgitXA","X112_XAA_3  第二种颜色 随机播放 播放完成回调  ");

                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    Log.i("zukgitXA","X112_XAA_3  第三种颜色 随机播放 播放完成回调  ");

                }  else{

                    Log.i("zukgitXA","X112_XAA_3  第四种颜色 随机播放 播放完成回调  ");

                    textview_level2_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor);



                }

                if(cur_node_id ==last_click_level2_ID ){
                    holder.tvName.setSelected(true);
                }

            } else if (INDEX == 2) {
                last_click_level3_ID_ViewPosition = position;

                Log.i("zukgitXA","节点3__0 设置颜色:  position"+position+" last_click_level3_ID_ViewPosition="+last_click_level3_ID_ViewPosition +" holder.tvName="+holder.tvName.getText().toString());

                if(textview_level3_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor_diss);
                    Log.i("zukgitXA","节点3__1 设置颜色:  textview_level3_selected_bgcolor_diss = "+textview_level3_selected_bgcolor_diss +"  holder.tvName="+holder.tvName.getText().toString());
                    textview_level3_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    Log.i("zukgitXA","节点3__2 设置颜色:  item.color() = "+ item.color());
                }   else{
                    textview_level3_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor);
                    Log.i("zukgitXA","节点3__3 设置颜色:  item.color() = "+ item.color() +"   textview_level3_selected_bgcolor="+textview_level3_selected_bgcolor +" holder.tvName="+holder.tvName.getText().toString());

                }



                if(cur_node_id ==last_click_level3_ID ){
                    holder.tvName.setSelected(true);
                    holder.tvName.setBackgroundResource(item.color());
                    Log.i("zukgitXA","节点3__4 设置颜色:  item.color() = "+ item.color());

                }
                if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

                }
                Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

            }
        } else {
            holder.tvName.setTextColor(ContextCompat.getColor(
                    holder.tvName.getContext(),
                    R.color.mlp_item_unselected_text
            ));
            holder.tvName.setBackgroundResource(R.color.transparent);

            if(cur_node_id ==last_click_level3_ID ){
                holder.tvName.setSelected(true);
            }
        }



        holder.tvName.setTag(item);

        if(item.level() == 1){   // 长按 第一项  改变颜色 背景颜色

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {    //  长按 第一项
//                    mMultiLevelPickerWindow.onBackgroundChanged();
                    return false;
                }

            });
        }

        if(item.level() == 2){   // 作者的 TextView  长按 选中它  说明 对于这个歌手 进行随机 播放

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level2_ID = cur_node_id;
                    long_click_loop_level2_ID_Name = holder.tvName.getText().toString();
                    if(long_click_loop_level2_ID_Name != null && long_click_loop_level2_ID_Name.contains("(")){
                        long_click_loop_level2_ID_Name=long_click_loop_level2_ID_Name.substring(0,long_click_loop_level2_ID_Name.lastIndexOf("("));
                    }
                    DataHolder.long_click_loop_level2_ID_Name = long_click_loop_level2_ID_Name;

                    Log.i("zukgitXA","X112_X_A1 随机播放 播放完成回调  ");


                    mMultiLevelPickerWindow.update();
                    return false;
                }
            });
        }


        if(item.level() == 3){

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level3_ID = cur_node_id;

                    return false;
                }
            });
        }


        holder.tvName.setOnClickListener(view -> {

            */
/*



            //noinspection unchecked
            T selectedChild = (T) view.getTag();

            if(mMultiLevelPickerWindow != null){
//                mMultiLevelPickerWindow.callbackSelected_Text(selectedChild);
            }

            if (mOnSelectListener != null) {
                notifyDataSetChanged();
                //noinspection unchecked
                mOnSelectListener.onSelect(tree, selectedChild);
            }
            String curText =  selectedChild.text();
            String mp3path =  selectedChild.mp3path();
            Log.i("zukgit","curText = "+curText +"   PATH="+mp3path);

            long cur_long_ID = selectedChild.id();
            long cur_long_ParentID = selectedChild.parentid();

            int curID = (int)selectedChild.id();
            int level = selectedChild.level();
            if(level == 1){
                textview_level1_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level1_selected_bgcolor);
                last_click_level1_ID = curID;

            }

            if(level == 2){
                textview_level2_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level2_selected_bgcolor);
                last_click_level2_ID = curID;

                // 如果 点击了  长按的level2_node  按钮  如果当前 mSc
                Log.i("zukgitNode"," Pure_Node2 的 点击事件 ");

                if(curID == long_click_loop_level2_ID && isScreenMediaPlayerPlaying){
                    currentMP3_Track_Position_ScreenOff =   offScreenMediaPlayer.getCurrentPosition();
                    currentMP3_Track_Position = currentMP3_Track_Position_ScreenOff;

                    pauseMusic(offScreenMediaPlayer);
                    isScreenMediaPlayerPlaying = false;
//                    playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                    Log.i("zukgitNode","playerMusic C13_mediaPlayer  随机随机随机随机随机播放  screenToPosition_Level3 播放完成回调 currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"   isRandomPlayMusic="+isRandomPlayMusic+" lastMp3Path="+lastMp3Path+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);

                }else{
                    Log.i("zukgitNode","不是 长按选项!! curID ="+curID+"   long_click_loop_level2_ID="+long_click_loop_level2_ID+"  offScreenMediaPlayer.isPlaying()="+offScreenMediaPlayer.isPlaying());
                }

            }
            if(level == 3){
                T selectedChild_level_3 = (T) view.getTag();
                Log.i("zukgitNode_Click"," ______________________________________________  Pure_Node 点击 Name["+holder.tvName.getText().toString()+"] 开始  ______________________________________________ ");
                origin_last_click_level3_ID = last_click_level3_ID;

                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_1   selectedChild_level_3="+selectedChild_level_3.toString());
                // 如果当前点击的不是
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_2   long_click_loop_level3_ID="+long_click_loop_level3_ID +"   cur_node_id="+cur_node_id);
                if(long_click_loop_level3_ID != cur_node_id){   // 取消单曲循环
                    isLevel3_Loop = false;
                    long_click_loop_level3_ID = -100;
                }
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_3   last_click_level3_ID="+last_click_level3_ID +"   curID="+curID +" last_click_level3_id="+last_click_level3_ID +"   lastMp3Path="+lastMp3Path  +"  last_click_level2_ID="+last_click_level2_ID +"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  long_click_loop_level2_ID_Name="+long_click_loop_level2_ID_Name);
                origin_last_click_level3_ID = last_click_level3_ID;
                if(last_click_level3_ID == selectedChild_level_3.id()){  // 两次点击相同的 text 那么 执行暂停 播放 循环操作
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();

                    if(isMediaPlayerPlaying){   // 如果当前是 播放 状态 那么暂停它
                        Log.i("zukgitXA","X2 暂停播放文件 ==  "+mp3path +"  暂停它");
                        // mMultiLevelPickerWindow.onMusicTitlePlay(false);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口15  ");
                        currentMP3_Track_Position = mediaPlayer.getCurrentPosition();
                        pauseMusic(mediaPlayer);

                        textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                        selectedChild_level_3.setcolor(textview_level3_selected_bgcolor);
                        holder.tvName.setTextColor(textview_level3_selected_bgcolor);

                    }else{  //  如果是暂停 状态 那么  播放它

                        textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                        selectedChild_level_3.setcolor(textview_level3_selected_bgcolor);
                        holder.tvName.setTextColor(textview_level3_selected_bgcolor);

                        Log.i("zukgitXA","X3 开始播放文件 ==  "+mp3path +"  重新播放它  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口16  ");
                        if(currentMP3_Track_Position > 0){
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                            // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口16_1   ");
                        }else  if(currentMP3_Track_Position_ScreenOff > 0 && currentMP3_Track_Position < 0 ){
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口17  ");

                            if(mp3path != null && lastMp3Path ==null){
                                playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position_ScreenOff);
                                // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口18  ");

                            }else{
                                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                                // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口19  ");

                            }
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口20  ");

                        }else if(currentMP3_Track_Position > 0){
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口21  ");

                            playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),currentMP3_Track_Position);
                            // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                        }

                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______A出口22  currentMP3_Track_Position ="+currentMP3_Track_Position +"   currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff +"  mediaPlayer.getCurrentPosition()="+mediaPlayer.getCurrentPosition());
                        playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),currentMP3_Track_Position);
                        // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    }

                    Log.i("zukgitNode_Click"," ______________________________________________  Pure_Node 点击 结束_0点  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return ;


                }
                // 是 Binder的Id     curID 是 按钮 绑定的 id

                //  如果点击了 不在 循环 内的 第三item 那么 变为 单曲顺序播放sa
                if(selectedChild.parentid() != long_click_loop_level2_ID  && long_click_loop_level2_ID != -100 ){
                    pauseMusic(mediaPlayer);
                    pauseMusic(offScreenMediaPlayer);
                    playerMusic(mediaPlayer,selectedChild.mp3path(),0);
                    long_click_loop_level2_ID = -100;  // 取消  作者循环

                    mMultiLevelPickerWindow.update();
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_3  ");
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                    Log.i("zukgitNode_Click"," ______________________________________________  Pure_Node 点击 结束_1点   Name["+holder.tvName.getText().toString()+"]  last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return;

                }

                long pre_last_click_level3_parentId = DataHolder.getParentId(last_click_level3_ID);
                textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_4   last_click_level3_ID="+last_click_level3_ID+"  pre_last_click_level3_parentId="+pre_last_click_level3_parentId+"  curID="+curID+"  cur_long_ParentID="+cur_long_ParentID);
                selectedChild.setcolor(textview_level3_selected_bgcolor);

                Pure_NodeImpl nextRandomNode_Default   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5   next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition +" selectedChild_level_3.id()="+selectedChild_level_3.id()+"   last_click_level3_ID="+last_click_level3_ID+"  ");

                if(nextRandomNode_Default != null ){

                    if(selectedChild_level_3.id() == last_click_level3_ID){
                        if(isMediaPlayerPlaying || isScreenMediaPlayerPlaying){
                            pauseMusic(mediaPlayer);
                            pauseMusic(offScreenMediaPlayer);
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_6 ");
                        }else{
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7 ");

                            if(last_click_level3_ID != origin_last_click_level3_ID && origin_last_click_level3_ID != - 100){
                                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7_1  last_click_level3_ID="+last_click_level3_ID+"  origin_last_click_level3_ID="+origin_last_click_level3_ID);

                                playerMusic(mediaPlayer,mp3path,0);
                            }else{
                                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_7_2  last_click_level3_ID="+last_click_level3_ID+"  origin_last_click_level3_ID="+origin_last_click_level3_ID);

                                playerMusic(mediaPlayer,mp3path,mediaPlayer.getCurrentPosition());
                            }

                        }
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_8 ");
                        last_click_level3_ID = (int)selectedChild_level_3.id();
                        return;
                    }else{
                        next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(nextRandomNode_Default.id(),nextRandomNode_Default.parentid);

                    }


                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5   next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition);


                }
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2  ");




                if(isRandomPlayMusic && next_click_level3_ID != -100 && last_click_level3_ParentID == pre_last_click_level3_parentId &&  (long_click_loop_level2_ID == last_click_level3_ParentID  && long_click_loop_level2_ID != -100)){
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口3  ");
                    Pure_NodeImpl nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    if(nextRandomNode.id() == cur_long_ID) {
                        nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    }
                    if(nextRandomNode.id() == cur_long_ID) {
                        nextRandomNode = DataHolder.getRandomNextSongId(cur_long_ID,cur_long_ParentID);
                    }
                    next_click_level3_ID = (int)nextRandomNode.id();
                    long parentId = nextRandomNode.parentid;
                    next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                    String Pure_NodeName = DataHolder.getSongNameWithID(next_click_level3_ID);
                    Log.i("zukgitNode","X112_A 随机随机随机随机随机播放  screenToPosition_Level3 播放完成回调 Pure_NodeName="+nodeName+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口4  ");
                    pauseMusic(mediaPlayer);
                    pauseMusic(offScreenMediaPlayer);
                    playerMusic(mediaPlayer,selectedChild.mp3path(),0);
                    // mMultiLevelPickerWindow.onMusicTitlePlay(true);

                }else if(cur_long_ParentID != pre_last_click_level3_parentId || long_click_loop_level2_ID != last_click_level3_ParentID  || long_click_loop_level2_ID == -100 ){ // 如果点击了 其他的  level_3 按钮的话
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口5  ");

                    Pure_NodeImpl nextRandomNode   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                    if(nextRandomNode != null){
                        next_click_level3_ID_ViewPosition =  DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                        next_click_level3_ID = (int)nextRandomNode.id();
                        Log.i("zukgitNode","nextRandomNode 不为空 顺序播放顺序播放  screenToPosition_Level3 播放完成回调 Pure_NodeName="+nextRandomNode.name+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);

                        pauseMusic(mediaPlayer);
                        pauseMusic(offScreenMediaPlayer);
                        playerMusic(mediaPlayer,selectedChild_level_3.mp3path(),0);
                        // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口6  ");
                        last_click_level3_ID = (int)selectedChild_level_3.id();
                        last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                        Log.i("zukgitNode_Click"," ______________________________________________  Pure_Node 点击 结束_2点  Name["+holder.tvName.getText().toString()+"]  last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

                        return;

                    }else{
                        Log.i("zukgitNode"," nextRandomNode为空 顺序播放顺序播放  screenToPosition_Level3 播放完成回调 Pure_NodeName=null "+"  isRandomPlayMusic="+isRandomPlayMusic+"   next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  last_click_level3_ParentID="+last_click_level3_ParentID);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口7  ");

                    }

                    // 是否 是 当前的 选中level2项的子项
                    if(cur_long_ParentID == long_click_loop_level2_ID && long_click_loop_level2_ID != -100){
                        Log.i("zukgitXA"," 播放完成回调 相同子项 long_click_loop_level2_ID"+long_click_loop_level2_ID);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口8  ");

                    }else{
                        if(cur_node_id != last_click_level3_ID){  //  如果是 相同的 id 点击
                            isRandomPlayMusic = false;

                            Pure_NodeImpl nextNode = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                            next_click_level3_ID = (int)nextNode.id();
                            next_click_level3_ID_ViewPosition = DataHolder.getPositionInList(nextRandomNode.id(),nextRandomNode.parentid);
                            long_click_loop_level2_ID = -100;
                            isLevel3_Loop = false;
                            Log.i("zukgitXA"," 取消循环  取消 播放完成回调 不同子项 level_2node 长按 last_click_level3_ParentID="+last_click_level3_ParentID+"   pre_last_click_level3_parentId="+pre_last_click_level3_parentId+"  cur_long_ParentID="+cur_long_ParentID+"  long_click_loop_level2_ID="+long_click_loop_level2_ID+" 不相同 !!");
//                    mMultiLevelPickerWindow.dismiss();
                            mMultiLevelPickerWindow.update();
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口9  ");

                        }
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口10  ");

                    }
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口11  ");


                } else {
                    Pure_NodeImpl nextNode = DataHolder.getNextSongId(selectedChild.id(),selectedChild.parentid());
                    next_click_level3_ID = (int)nextNode.id();
                    next_click_level3_ID_ViewPosition=  DataHolder.getPositionInList(nextNode.id(),nextNode.parentid);


                    String Pure_NodeName = DataHolder.getSongNameWithID(next_click_level3_ID);
                    Log.i("zukgitXA","X112_B 循环循环循环循环播放 播放完成回调  Pure_NodeName="+nodeName+"  isRandomPlayMusic="+isRandomPlayMusic+"  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口12  ");

                }

//                android.util.Log.i("zukgitXA","X112_C 播放完成回调 mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口13  ");

                if(last_click_level3_ID == -100){

                    playerMusic(mediaPlayer,mp3path,0);
                    // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    Log.i("zukgitXA","C5_mediaPlayer  X1 开始播放文件 ==  "+mp3path +"  播放它");
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口14  ");

                }else  if(last_click_level3_ID != curID){     //   如果 点击了 不同的 text 那么说明是 需要 另外 切换歌曲
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口23  ");


                    long_click_loop_level3_ID = -100;  // 切换了歌曲 就相当于 把 循环取消掉了
                    mediaPlayer.setLooping(false);

                    Log.i("zukgitXA","X0 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                    mMultiLevelPickerWindow.update();
                    int pos = ( currentMP3_Track_Position==currentMP3_Track_Position_ScreenOff && currentMP3_Track_Position_ScreenOff != -100)?currentMP3_Track_Position:0;

                    if(currentMP3_Track_Position < 0 && currentMP3_Track_Position_ScreenOff < 0){
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口24  ");

                        if(offScreenMediaPlayer.getCurrentPosition() > 0 && isScreenMediaPlayerBegin){
                            playerMusic(mediaPlayer,mp3path,offScreenMediaPlayer.getCurrentPosition());
                            pauseMusic(offScreenMediaPlayer);
                            Log.i("zukgitXA","C6_mediaPlayer click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口25  ");

                        }else{
                            playerMusic(mediaPlayer,mp3path,0);
                            pauseMusic(offScreenMediaPlayer);
                            Log.i("zukgitXA","C7_mediaPlayer click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口26  ");

                        }

                        Log.i("zukgitXA","X2 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口27  ");

                    }else if(currentMP3_Track_Position > currentMP3_Track_Position_ScreenOff && currentMP3_Track_Position_ScreenOff >= 0 ){
                        pauseMusic(offScreenMediaPlayer);
                        playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口28  ");

                        Log.i("zukgitXA","C8_mediaPlayer X1 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

                    }else{
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口29  ");

                        if(!isMediaPlayerPlaying){  // 没有播放
                            pauseMusic(offScreenMediaPlayer);
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position_ScreenOff);
                            Log.i("zukgitXA","C9*1 _mediaPlayer X3 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口30  ");

                        }else if(!isScreenMediaPlayerBegin){
                            pauseMusic(offScreenMediaPlayer);
                            playerMusic(mediaPlayer,mp3path,currentMP3_Track_Position);
                        }
                        Log.i("zukgitXA","C9_mediaPlayer X3 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff+"  cur_long_ParentID="+cur_long_ParentID+"  long_click_loop_level2_ID="+long_click_loop_level2_ID+"   DataHolder.isAPPInBackground="+DataHolder.isAPPInBackground);
                        Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口31  ");

                    }

                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口32  ");

                    Log.i("zukgitXA","X4 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  currentMP3_Track_Position="+currentMP3_Track_Position+"   pos="+pos+"  isScreenMediaPlayerBegin="+isScreenMediaPlayerBegin+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
                    // mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    currentMP3_Track_Position_ScreenOff = -100; //只使能一次

                }


                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口33  ");


                // zukgit  显示路径 MP3PATH  Toast
                //  Toast.makeText(mContext.getApplicationContext(),mp3path,Toast.LENGTH_SHORT).show();

                if(isScreenMediaPlayerPlaying){
                    pauseMusic(offScreenMediaPlayer);
                    Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口34  ");

                }
                last_click_level3_ID = (int)selectedChild_level_3.id();
                last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口35  last_click_level3_ID ="+last_click_level3_ID);
                Log.i("zukgitNode_Click"," ______________________________________________  Pure_Node_3 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

            }
            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口36  ");

            if(isScreenMediaPlayerPlaying){
                pauseMusic(offScreenMediaPlayer);
                Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口37  ");

            }
            Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口38  ");

            isScreenMediaPlayerBegin = false;

            Log.i("zukgitNode_Click"," ______________________________________________  All_Node 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");
*//*

        });

*/
/*  //          如果 当前的 id 就是 next_click_level3_ID
        if(isRandomPlayMusic && next_click_level3_ID != -100 && next_click_level3_ID == cur_node_long_id && holder.tvName != null   ){
            mNext_Level3_TextView = holder.tvName;
            next_click_level3_ID_ViewPosition = position;

            android.util.Log.i("zukgitXAsa_random_true"," 播放完成回调  isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());

        }else{

        }*//*


        if(last_click_level3_ID != -100 && last_click_level3_ID== cur_node_id && holder.tvName != null){

            if(!isLevel3_Loop){
                holder.tvName.setBackgroundResource(item.color());

            }
            Log.i("zukgitXAsa_random_true"," 1AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+"  next_click_level3_ID_ViewPosition="+""+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());
            Pure_NodeImpl selectedNode =  (Pure_NodeImpl)holder.tvName.getTag();

        }

        if(long_click_loop_level2_ID != -100 && long_click_loop_level2_ID== cur_node_id && holder.tvName != null){

            holder.tvName.setBackgroundResource(R.color.color_india_red);

        }

        if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
            holder.tvName.setBackgroundResource(R.color.color_india_red);

        }



        if(item.level() == 3 && item.id() != last_click_level3_ID){
            holder.tvName.setBackgroundResource(R.color.transparent);
            holder.tvName.setSelected(false);
            Log.i("zukgitXA"," BindColor___X0  holder.tvName="+ holder.tvName.getText().toString());

        }else  if(item.level() == 3 && item.id() == last_click_level3_ID){
            if(long_click_loop_level3_ID == last_click_level3_ID){  // 长按颜色
                holder.tvName.setBackgroundResource(R.color.color_india_red);
                Log.i("zukgitXA"," BindColor___X0_1  holder.tvName="+ holder.tvName.getText().toString());

            }else {   // 自身颜色
                Log.i("zukgitXA"," BindColor___X1  holder.tvName="+ holder.tvName.getText().toString());
                if(item.color() != -100 &&  item.color()  != 0){
                    Log.i("zukgitXA"," BindColor___X2  holder.tvName="+ holder.tvName.getText().toString() +"  item.color()="+item.color());
                    holder.tvName.setBackgroundResource(item.color());   // 滑动时 不变 使用
//                    holder.tvName.setBackgroundResource(ZUtil.getRainbowColor_random());  // 回前台 下一个 歌曲使用
//                    item.setcolor(ZUtil.getRainbowColor_random());

                }else{
                    Log.i("zukgitXA"," BindColor___X3    holder.tvName="+ holder.tvName.getText().toString() +"  last_click_level3_ID="+ last_click_level3_ID);
                    item.setcolor(ZUtil.getRainbowColor_random());
                    holder.tvName.setBackgroundResource(item.color());
                }

            }
            holder.tvName.setSelected(false);
        }

    }
*/




    private T tree;
    Context mContext;


    public static int last_click_level1_ID = -100;
    public static int last_click_level2_ID = -100;
    public static int last_click_level3_ID = -100;
    public static int last_click_level3_ParentID = -100;


    public static long origin_last_click_level3_ID = -100;


    public static int last_click_level3_ID_ViewPosition = -100;



    public static int long_click_loop_level1_ID = -100;
    public static int long_click_loop_level2_ID = -100;
    public static int long_click_loop_level3_ID = -100;
    public static String long_click_loop_level2_ID_Name = null ;  // 第二个长按的选项的名称



    public static boolean isLevel3_Loop = false;

    public static int textview_level1_selected_bgcolor = -100;
    public static int textview_level2_selected_bgcolor = -100;
    public static int textview_level3_selected_bgcolor = -100;

    public static int textview_level1_selected_bgcolor_diss = -100;
    public static int textview_level2_selected_bgcolor_diss = -100;
    public static int textview_level3_selected_bgcolor_diss = -100;


    public  void screenToLastClickNode3Position() {


    }


    public void  showStaticProp(String tag){
        Log.i("zukgit","════════════════════════════════"+tag+" Pure_MultiLevelItemAdapter 显示静态变量开始 "+"════════════════════════════════");
        Log.i("zukgit","10 last_click_level1_ID = "+ last_click_level1_ID);
        Log.i("zukgit","11 last_click_level2_ID = "+ last_click_level2_ID);
        Log.i("zukgit","12 last_click_level3_ID = "+ last_click_level3_ID);
        Log.i("zukgit","13 last_click_level3_ParentID = "+ last_click_level3_ParentID);
        Log.i("zukgit","14 last_click_level3_ID_ViewPosition = "+ last_click_level3_ID_ViewPosition);
        Log.i("zukgit","17 long_click_loop_level2_ID = "+ long_click_loop_level2_ID);
        Log.i("zukgit","18 long_click_loop_level2_ID_Name = "+ long_click_loop_level2_ID_Name);
        Log.i("zukgit","21 isLevel3_Loop = "+ isLevel3_Loop);
        Log.i("zukgit","22 long_click_loop_level3_ID = "+ long_click_loop_level3_ID);
        Log.i("zukgit","23 textview_level1_selected_bgcolor = "+ textview_level1_selected_bgcolor);
        Log.i("zukgit","24 textview_level2_selected_bgcolor = "+ textview_level2_selected_bgcolor);
        Log.i("zukgit","25 textview_level3_selected_bgcolor = "+ textview_level3_selected_bgcolor);
        Log.i("zukgit","26 textview_level1_selected_bgcolor_diss = "+ textview_level1_selected_bgcolor_diss);
        Log.i("zukgit","27 textview_level2_selected_bgcolor_diss = "+ textview_level2_selected_bgcolor_diss);
        Log.i("zukgit","28 textview_level3_selected_bgcolor_diss = "+ textview_level3_selected_bgcolor_diss);

        Log.i("zukgit","════════════════════════════════"+tag+" Pure_MultiLevelItemAdapter 显示静态变量结束 "+"════════════════════════════════");



    }


    TextView mFirst_Level3_TextView;  // 第一首一首歌曲时的 click 点击时 需要 使用

    private int INDEX;
    public Pure_MultiLevelPickerWindow mMultiLevelPickerWindow;

    void recoverySelectedTextViewColor(){
        if(textview_level1_selected_bgcolor != -100 ){
            textview_level1_selected_bgcolor_diss = textview_level1_selected_bgcolor;
        }

        if(textview_level2_selected_bgcolor != -100 ){
            textview_level2_selected_bgcolor_diss = textview_level2_selected_bgcolor;
        }

        if(textview_level3_selected_bgcolor != -100 ){
            textview_level3_selected_bgcolor_diss = textview_level3_selected_bgcolor;
        }

    }



    Pure_MultiLevelItemAdapter(T tree, int index,Context context , Pure_MultiLevelPickerWindow xMultiLevelPickerWindow) {
        this.tree = tree;
        this.INDEX = index;
        mContext = context;

        if(xMultiLevelPickerWindow != null){
            mMultiLevelPickerWindow =xMultiLevelPickerWindow;

        }


    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.mlp_item_chioce, parent, false);
        return new VHolder(view);
    }




    void recoveryMusic_ChangePage(){
     /*
        mediaPlayer.start();
        // mMultiLevelPickerWindow.onMusicTitlePlay(true);
//        playerMusic(mediaPlayer,lastMp3Path,DataHolder.currentMP3_Track_Position_ChangePage);
        Log.i("zukgit","recoveryMusic_ChangePage    DataHolder.currentMP3_Track_Position_ChangePage="+DataHolder.currentMP3_Track_Position_ChangePage);
//        mediaPlayer.start();

        android.util.Log.i("zukgit"," recoveryMusic  lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position="+currentMP3_Track_Position+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);
        if(mediaPlayer != null && lastMp3Path != null &&  (currentMP3_Track_Position != -100 || currentMP3_Track_Position_ScreenOff != -100)){

            if(isMediaPlayerPlaying && isScreenMediaPlayerPlaying){  // 同时播放 肯定 停止  offScreenMediaPlayer
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                currentMP3_Track_Position = currentMP3_Track_Position_ScreenOff;

                pauseMusic(offScreenMediaPlayer);
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);

                android.util.Log.i("zukgit","C2_mediaPlayer  recoveryMusic offScreenMediaPlayer.pause()  currentMP3_Track_Position_ScreenOff= "+currentMP3_Track_Position_ScreenOff+"  currentMP3_Track_Position="+currentMP3_Track_Position);
            }else if( isMediaPlayerPlaying){  //
                android.util.Log.i("zukgit","2 recoveryMusic  mediaPlayer 正在播放 ");


            }else if(isScreenMediaPlayerPlaying){

                pauseMusic(offScreenMediaPlayer);
                currentMP3_Track_Position_ScreenOff = offScreenMediaPlayer.getCurrentPosition();
                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position_ScreenOff);
                android.util.Log.i("zukgit","C3_mediaPlayer recoveryMusic  mediaPlayer 正在播放  ");
            }else if(!DataHolder.isAPPInBackground  ){  //亮屏   // 切换回前台

                playerMusic(mediaPlayer,lastMp3Path,currentMP3_Track_Position);
                android.util.Log.i("zukgit","C4_mediaPlayer  recoveryMusic  mediaPlayer 正在播放 currentMP3_Track_Position= "+currentMP3_Track_Position +" lastMp3Path="+lastMp3Path+"  currentMP3_Track_Position_ScreenOff="+currentMP3_Track_Position_ScreenOff);

            }else{
                android.util.Log.i("zukgit","5 recoveryMusic  mediaPlayer 正在播放 ");
//                mMultiLevelPickerWindow.screenToPosition_Level3(next_click_level3_ID_ViewPosition);
            }

            android.util.Log.i("zukgit","recoveryMusic  XXXX  ");
        }*/
    }




    @Override
    public int getItemCount() {
        if (tree == null) return 0;
        List<? extends Pure_Node> children = tree.children();
        return children == null ? 0 : children.size();
    }

    void setNewData(T data) {
        tree = data;
        notifyDataSetChanged();
    }

    class VHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        VHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    private OnItemPickerListener mOnSelectListener;

    void setOnSelectListener(OnItemPickerListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    interface OnItemPickerListener<T extends Pure_Node> {
        void onSelect(T parent, T selectedChild);
    }

    T getTree() {
        return tree;
    }



    @Override
    public void onBindViewHolder(@NonNull Pure_MultiLevelItemAdapter.VHolder holder, int position) {
        if (tree == null) return;
        List<? extends Pure_Node> children = tree.children();
        if (children == null) return;
        //noinspection unchecked
        T item = (T) children.get(position);
        int cur_node_id = (int)item.id();
        long cur_node_long_id = item.id();
        long cur_node_long_parentid = item.parentid();
        holder.tvName.setText(item.text());
        // 获取 当前的 item_id 的 名称     意味着 每个 item的 内容可能变化  但是 id 不变
        // 这个 歌曲的 名称 是变化的
//        String nextSongName = DataHolder.getSongNameWithID(cur_node_long_id);






        if (item.id() == tree.selectedChild()) {

            holder.tvName.setTextColor(ContextCompat.getColor(holder.tvName.getContext(),
                    R.color.mlp_item_selected_text));
            if (INDEX == 0) {

                if(textview_level1_selected_bgcolor_diss != -100 ){
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor_diss);
                    android.util.Log.i("zukgit","设置颜色X1_1 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);
                    item.setcolor(textview_level1_selected_bgcolor_diss);
                    textview_level1_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgit","设置颜色X1_2 Level_1 textview_level1_selected_bgcolor_diss = "+textview_level1_selected_bgcolor_diss);

                }    else{
                    textview_level1_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level1_selected_bgcolor);
                    android.util.Log.i("zukgit","设置颜色X1_3 Level_1 textview_level1_selected_bgcolor = "+textview_level1_selected_bgcolor);
                }

//

            } else if (INDEX == 1) {

                if(textview_level2_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor_diss);
                    textview_level2_selected_bgcolor_diss = -100;
                    android.util.Log.i("zukgitXA","X112_XAA_3  第一种颜色 随机播放 播放完成回调  ");

                } else if( long_click_loop_level2_ID  != -100 && long_click_loop_level2_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    textview_level2_selected_bgcolor = R.color.color_india_red;
                    android.util.Log.i("zukgitXA","X112_XAA_3  第二种颜色 随机播放 播放完成回调  ");

                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","X112_XAA_3  第三种颜色 随机播放 播放完成回调  ");

                }  else{

                    android.util.Log.i("zukgitXA","X112_XAA_3  第四种颜色 随机播放 播放完成回调  ");

                    textview_level2_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level2_selected_bgcolor);



                }

                if(cur_node_id ==last_click_level2_ID ){
                    holder.tvName.setSelected(true);
                }

            } else if (INDEX == 2) {
                last_click_level3_ID_ViewPosition = position;

                android.util.Log.i("zukgitXA","节点3__0 设置颜色:  position"+position+" last_click_level3_ID_ViewPosition="+last_click_level3_ID_ViewPosition +" holder.tvName="+holder.tvName.getText().toString());

                if(textview_level3_selected_bgcolor_diss != -100 ){

                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor_diss);
                    android.util.Log.i("zukgitXA","节点3__1 设置颜色:  textview_level3_selected_bgcolor_diss = "+textview_level3_selected_bgcolor_diss +"  holder.tvName="+holder.tvName.getText().toString());
                    textview_level3_selected_bgcolor_diss = -100;
                } else if(item.color() != -100 ){
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","节点3__2 设置颜色:  item.color() = "+ item.color());
                }   else{
                    textview_level3_selected_bgcolor = ZUtil.getRainbowColor_random();
                    holder.tvName.setBackgroundResource(textview_level3_selected_bgcolor);
                    android.util.Log.i("zukgitXA","节点3__3 设置颜色:  item.color() = "+ item.color() +"   textview_level3_selected_bgcolor="+textview_level3_selected_bgcolor +" holder.tvName="+holder.tvName.getText().toString());

                }



                if(cur_node_id ==last_click_level3_ID ){
                    holder.tvName.setSelected(true);
                    holder.tvName.setBackgroundResource(item.color());
                    android.util.Log.i("zukgitXA","节点3__4 设置颜色:  item.color() = "+ item.color());

                }
                if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
                    holder.tvName.setBackgroundResource(R.color.color_india_red);
                    android.util.Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

                }
                android.util.Log.i("zukgitXA","节点3__5 设置颜色:  item.color() = "+ item.color());

            }
        } else {
            holder.tvName.setTextColor(ContextCompat.getColor(
                    holder.tvName.getContext(),
                    R.color.mlp_item_unselected_text
            ));
            holder.tvName.setBackgroundResource(R.color.transparent);

            if(cur_node_id ==last_click_level3_ID ){
                holder.tvName.setSelected(true);
            }
        }



        holder.tvName.setTag(item);

        if(item.level() == 1){   // 长按 第一项  改变颜色 背景颜色

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mMultiLevelPickerWindow.onBackgroundChanged();
                    return false;
                }

            });
        }

        if(item.level() == 2){   // 作者的 TextView  长按 选中它  说明 对于这个歌手 进行随机 播放

            holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long_click_loop_level2_ID = cur_node_id;
                    long_click_loop_level2_ID_Name = holder.tvName.getText().toString();
                    if(long_click_loop_level2_ID_Name != null && long_click_loop_level2_ID_Name.contains("(")){
                        long_click_loop_level2_ID_Name=long_click_loop_level2_ID_Name.substring(0,long_click_loop_level2_ID_Name.lastIndexOf("("));
                    }
                    DataHolder.long_click_loop_level2_ID_Name = long_click_loop_level2_ID_Name;

                    android.util.Log.i("zukgitXA","X112_X_A1 随机播放 播放完成回调  ");


                    mMultiLevelPickerWindow.update();
                    return false;
                }
            });
        }


        if(item.level() == 3){

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

            if(mMultiLevelPickerWindow != null){
                mMultiLevelPickerWindow.callbackSelected_Text(selectedChild);
            }

            if (mOnSelectListener != null) {
                notifyDataSetChanged();
                //noinspection unchecked
                mOnSelectListener.onSelect(tree, selectedChild);
            }
            String curText =  selectedChild.text();
            String mp3path =  selectedChild.mp3path();
            android.util.Log.i("zukgit","curText = "+curText +"   PATH="+mp3path);

            long cur_long_ID = selectedChild.id();
            long cur_long_ParentID = selectedChild.parentid();

            int curID = (int)selectedChild.id();
            int level = selectedChild.level();
            if(level == 1){
                textview_level1_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level1_selected_bgcolor);
                last_click_level1_ID = curID;

            }

            if(level == 2){
                textview_level2_selected_bgcolor =   ZUtil.getRainbowColor_random();
                selectedChild.setcolor(textview_level2_selected_bgcolor);
                last_click_level2_ID = curID;

                // 如果 点击了  长按的level2_node  按钮  如果当前 mSc
                android.util.Log.i("zukgitNode"," Node2 的 点击事件 ");



            }
            if(level == 3){
                T selectedChild_level_3 = (T) view.getTag();
                android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 Name["+holder.tvName.getText().toString()+"] 开始  ______________________________________________ ");
                origin_last_click_level3_ID = last_click_level3_ID;

                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_1   selectedChild_level_3="+selectedChild_level_3.toString());
                // 如果当前点击的不是
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_2   long_click_loop_level3_ID="+long_click_loop_level3_ID +"   cur_node_id="+cur_node_id);
                if(long_click_loop_level3_ID != cur_node_id){   // 取消单曲循环
                    isLevel3_Loop = false;
                    long_click_loop_level3_ID = -100;
                }
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口1_3   last_click_level3_ID="+last_click_level3_ID +"   curID="+curID +" last_click_level3_id="+last_click_level3_ID +"   "  +"  last_click_level2_ID="+last_click_level2_ID +"  long_click_loop_level2_ID="+long_click_loop_level2_ID +"  long_click_loop_level2_ID_Name="+long_click_loop_level2_ID_Name);
                origin_last_click_level3_ID = last_click_level3_ID;
                if(last_click_level3_ID == selectedChild_level_3.id()){  // 两次点击相同的 text 那么 执行暂停 播放 循环操作
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();



                    android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 结束_0点  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return ;


                }
                // 是 Binder的Id     curID 是 按钮 绑定的 id

                //  如果点击了 不在 循环 内的 第三item 那么 变为 单曲顺序播放sa
                if(selectedChild.parentid() != long_click_loop_level2_ID  && long_click_loop_level2_ID != -100 ){

                    long_click_loop_level2_ID = -100;  // 取消  作者循环

                    mMultiLevelPickerWindow.update();
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_3  ");
                    last_click_level3_ID = (int)selectedChild_level_3.id();
                    last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                    android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node 点击 结束_1点   Name["+holder.tvName.getText().toString()+"]  last_click_level3_ID=["+last_click_level3_ID+"] ______________________________________________ ");

                    return;

                }

                long pre_last_click_level3_parentId = DataHolder.getParentId(last_click_level3_ID);
                textview_level3_selected_bgcolor =   ZUtil.getRainbowColor_random();
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_4   last_click_level3_ID="+last_click_level3_ID+"  pre_last_click_level3_parentId="+pre_last_click_level3_parentId+"  curID="+curID+"  cur_long_ParentID="+cur_long_ParentID);
                selectedChild.setcolor(textview_level3_selected_bgcolor);

//                Pure_NodeImpl nextRandomNode_Default   = DataHolder.getNextSongId(cur_long_ID,cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2_5   " +" selectedChild_level_3.id()="+selectedChild_level_3.id()+"   last_click_level3_ID="+last_click_level3_ID+"  ");

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
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口2  ");




//                android.util.Log.i("zukgitXA","X112_C 播放完成回调 mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition + "  cur_long_ID="+cur_long_ID+"   cur_long_ParentID="+cur_long_ParentID);
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口13  ");

                if(last_click_level3_ID == -100){


                    mMultiLevelPickerWindow.onMusicTitlePlay(true);
                    android.util.Log.i("zukgitXA","C5_mediaPlayer  X1 开始播放文件 ==  "+mp3path +"  播放它");
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口14  ");

                }else  if(last_click_level3_ID != curID){     //   如果 点击了 不同的 text 那么说明是 需要 另外 切换歌曲
                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口23  ");


                    long_click_loop_level3_ID = -100;  // 切换了歌曲 就相当于 把 循环取消掉了


                    android.util.Log.i("zukgitXA","X0 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  "+"  "+"  ");
                    mMultiLevelPickerWindow.update();



                    android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口32  ");

                    android.util.Log.i("zukgitXA","X4 click_recoveryMusic 开始播放文件 ==  "+mp3path +"  "+"   "+"  "+" ");
                    mMultiLevelPickerWindow.onMusicTitlePlay(true);


                }


                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口33  ");


                // zukgit  显示路径 MP3PATH  Toast
                //  Toast.makeText(mContext.getApplicationContext(),mp3path,Toast.LENGTH_SHORT).show();


                last_click_level3_ID = (int)selectedChild_level_3.id();
                last_click_level3_ParentID = (int)selectedChild_level_3.parentid();
                android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口35  last_click_level3_ID ="+last_click_level3_ID);
                android.util.Log.i("zukgitNode_Click"," ______________________________________________  Node_3 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

            }
            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口36  ");


            android.util.Log.i("zukgitNode_Click"," 【Node3 的 点击事件】_______出口38  ");



            android.util.Log.i("zukgitNode_Click"," ______________________________________________  All_Node 点击 结束_Over  Name["+holder.tvName.getText().toString()+"] last_click_level3_ID=["+last_click_level3_ID+"]______________________________________________ ");

        });

/*  //          如果 当前的 id 就是 next_click_level3_ID
        if(isRandomPlayMusic && next_click_level3_ID != -100 && next_click_level3_ID == cur_node_long_id && holder.tvName != null   ){
            mNext_Level3_TextView = holder.tvName;
            next_click_level3_ID_ViewPosition = position;

            android.util.Log.i("zukgitXAsa_random_true"," 播放完成回调  isRandomPlayMusic ="+isRandomPlayMusic+" mNext_Level3_TextView.performClick()  next_click_level3_ID="+next_click_level3_ID+"  next_click_level3_ID_ViewPosition="+next_click_level3_ID_ViewPosition+"   cur_node_long_id="+cur_node_long_id+"   mNext_Level3_TextView="+mNext_Level3_TextView.getText());

        }else{

        }*/

        if(last_click_level3_ID != -100 && last_click_level3_ID== cur_node_id && holder.tvName != null){

            if(!isLevel3_Loop){
                holder.tvName.setBackgroundResource(item.color());

            }
            android.util.Log.i("zukgitXAsa_random_true"," 1AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+" mNext_Level3_TextView.performClick()  "+" "+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());
            Pure_NodeImpl selectedNode =  (Pure_NodeImpl)holder.tvName.getTag();

        }

        if( long_click_loop_level2_ID != -100 && long_click_loop_level2_ID== cur_node_id && holder.tvName != null){

            holder.tvName.setBackgroundResource(R.color.color_india_red);
//            android.util.Log.i("zukgitXAsa_random_true_2"," 2AAA long_click_loop_level3_ID ="+long_click_loop_level3_ID+  "    isRandomPlayMusic ="+" mNext_Level3_TextView.performClick()  "+"  "+"   cur_node_long_id="+cur_node_long_id+"   holder.tvName="+holder.tvName.getText());

        }


        if(long_click_loop_level3_ID  != -100 && long_click_loop_level3_ID== cur_node_id){
            holder.tvName.setBackgroundResource(R.color.color_india_red);

        }



        if(item.level() == 3 && item.id() != last_click_level3_ID){
            holder.tvName.setBackgroundResource(R.color.transparent);
            holder.tvName.setSelected(false);
            android.util.Log.i("zukgitXA"," BindColor___X0  holder.tvName="+ holder.tvName.getText().toString());

        }else  if(item.level() == 3 && item.id() == last_click_level3_ID){
            if(long_click_loop_level3_ID == last_click_level3_ID){  // 长按颜色
                holder.tvName.setBackgroundResource(R.color.color_india_red);
                android.util.Log.i("zukgitXA"," BindColor___X0_1  holder.tvName="+ holder.tvName.getText().toString());

            }else {   // 自身颜色
                android.util.Log.i("zukgitXA"," BindColor___X1  holder.tvName="+ holder.tvName.getText().toString());
                if(item.color() != -100 &&  item.color()  != 0){
                    android.util.Log.i("zukgitXA"," BindColor___X2  holder.tvName="+ holder.tvName.getText().toString() +"  item.color()="+item.color());
                    holder.tvName.setBackgroundResource(item.color());   // 滑动时 不变 使用
//                    holder.tvName.setBackgroundResource(ZUtil.getRainbowColor_random());  // 回前台 下一个 歌曲使用
//                    item.setcolor(ZUtil.getRainbowColor_random());

                }else{
                    android.util.Log.i("zukgitXA"," BindColor___X3    holder.tvName="+ holder.tvName.getText().toString() +"  last_click_level3_ID="+ last_click_level3_ID);
                    item.setcolor(ZUtil.getRainbowColor_random());
                    holder.tvName.setBackgroundResource(item.color());
                }

            }
            holder.tvName.setSelected(false);
        }

    }








}
