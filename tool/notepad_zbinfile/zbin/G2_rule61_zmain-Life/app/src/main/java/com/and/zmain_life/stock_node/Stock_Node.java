package com.and.zmain_life.stock_node;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author
 * @since 20190802
 */
public interface Stock_Node {

    String name();

    String json();   // 
    // 当前的层级
    int level();

    String area_json();

    int color();

    void setapapterposition(int position);

    void setcolor(int xcolor);


    void setlevel(int level);


    float total_mv();


    float price();

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
    String text(int position , String node1Type);

    @NonNull
    String text(int node3position );

    @NonNull
    String text();

    String ts_code_number();


    @NonNull
    String area_text(int nodePosition);

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
    List<? extends Stock_Node> children();

    @Nullable
    void setChildren(List<? extends Stock_Node> children);

    /**
     * 返回被选中的child
     */
    Stock_Node getSelectedChild();

}
