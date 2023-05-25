package com.and.zmain_life.pure_node;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author
 * @since 20190802
 */
public interface Pure_Node {

    String json();
    // 当前的层级
    int level();

    int color();

    void setcolor(int xcolor);
    // 当前的对应的 MP3 文件的 全路径
    String mp3path();

    String artist();

    void setmp3path(String mp3Path);

    void setlevel(int level);
    void setindex4parent(int parentindex);
    /**
     * 标记唯一性
     *
     * @return -1 when it is root
     */
    long id();

    long parentid();

    public void setparentid(long parentid);
    /**
     * 显示文案
     */
    @NonNull
    String text();

    /**
     * 被选中的Id
     *
     * @return -1 when {@link #children()} is null
     */
    long selectedChild();

    void setSelectedChild(long id);

    /**
     * 子树(为空表示是叶节点)
     *
     * @return null when it is a leaf
     */
    @Nullable
    List<? extends Pure_Node> children();

    /**
     * 返回被选中的child
     */
    Pure_Node getSelectedChild();

}
