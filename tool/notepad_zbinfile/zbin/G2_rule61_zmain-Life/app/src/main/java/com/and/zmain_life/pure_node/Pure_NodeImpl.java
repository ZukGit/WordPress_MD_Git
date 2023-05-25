package com.and.zmain_life.pure_node;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.and.zmain_life.bean.DataHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author relish <a href="mailto:relish.wang@gmail.com">Contact me.</a>
 * @since 20190802
 */
public class Pure_NodeImpl implements Pure_Node, Serializable {

    private static final long serialVersionUID = 1L;

    public int color_int;
    public long id;
    public String name;


    public String artist;

    private int count;
    public int index4parent;  // 在 父节点中的位置
    public int level;
    public String mp3path;
    public List<Pure_NodeImpl> children = new ArrayList<>();
    private transient long selectedChildId;

    public long parentid;
    static public final transient Pure_NodeImpl EMPTY = new Pure_NodeImpl(0,-1, "全部", 0,0,null);


    public Pure_NodeImpl(long id, long xparentid , String name, int count,int level , String xmp3Path) {
        this.id = id;
        this.parentid =  xparentid;
        this.name = name;
        this.count = count;
        this.level = level;
        this.mp3path = xmp3Path;
        children = new ArrayList<>();
        this.color_int = -100;
        index4parent = -1;
    }

    @Override
    public long parentid() {
        return parentid;
    }

    @Override
    public long id() {
        return id;
    }
    @Override
    public void setindex4parent(int parentindex) {
        index4parent = parentindex;
    }



    public String artist() {
        return artist;
    }



    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    @NonNull
    @Override
    public String text() {
        if(level == 3){
            if(DataHolder.isShowLevel3Node_Position){
                return name+"_"+index4parent;
            }else if(DataHolder.isShowLevel3Node_Artist){
                //对于 国语  粤语  A_ALL 这类的  node2项  需要找到真正的作者的  所以只有以 名字作为参数来判断
                return name+"_"+DataHolder.getOriginNode2NameWithName(name);
            }else{
                return name;
            }

        }
        return name + "(" + count + ")";
    }

    @Override
    public long selectedChild() {
        return selectedChildId;
    }

    @Override
    public void setSelectedChild(long id) {
        selectedChildId = id;
    }

    @Nullable
    @Override
    public List<? extends Pure_Node> children() {
        return children;
    }

    public void setChildren(List<Pure_NodeImpl> children) {
        this.children = children;
    }


    @Override
    public String json() {
        count = children.size();
        System.out.println("___________【"+name+ "】 Node_Level_"+level+"节点情况 Begin ___________");
        for (int i = 0; i < children.size(); i++) {
            System.out.println(children.get(i).toString());
        }
        System.out.println("___________【"+name+ "】Node_Level_"+level+"节点情况 End ___________");
        System.out.println();

        StringBuilder sb_json = new StringBuilder();
        sb_json.append("{\n");
        sb_json.append("\"id\": "+id+",\n");
        sb_json.append("\"parentid\": "+parentid+",\n");
        sb_json.append("\"index4parent\": "+index4parent+",\n");


        sb_json.append("\"artist\": "+"\""+artist+"\""+",\n");
        sb_json.append("\"name\": "+"\""+name+"\""+",\n");
        sb_json.append("\"count\": "+count+",\n");
        sb_json.append("\"level\": "+level+",\n");

        if(mp3path != null) {
            sb_json.append("\"mp3path\": "+"\""+(mp3path.replace("\\", "\\\\"))+"\""+",\n");
        }else {
            sb_json.append("\"mp3path\": \"null\""+",\n");
        }

        if(children == null || children.size() == 0) {
            sb_json.append("\"children\": "+"[]\n");
        }else {
            sb_json.append("\"children\": "+"[\n");
            for (int i = 0; i < children.size(); i++) {
                Pure_NodeImpl childNode = children.get(i);
                if(childNode != null && childNode.children() != null && childNode.children().size() > 0) {
                    System.out.println("childNode.name = "+ childNode.name + "  childNode.level="+childNode.level+"  childNode.id="+childNode.id + "  childNode.count="+childNode.children().size());

                    // 子节点 之间 使用 , 逗号 分隔
                    if(i == children.size()-1 ) {
                        sb_json.append(childNode.json()+"\n");
                    }else {
                        sb_json.append(childNode.json()+",\n");
                    }


                }else {
                    System.out.println("结点 的 childNode 为空！！！ 为叶子节点");
                    if(childNode != null) {
                        if(i == children.size()-1 ) {
                            sb_json.append(childNode.json()+"\n");
                        }else {
                            sb_json.append(childNode.json()+",\n");
                        }

                    }

                }

            }
            sb_json.append("]\n");
        }

        sb_json.append("}\n");
        return sb_json.toString();
    }

    @Override
    public void setmp3path(String mp3Path) {
        this.mp3path = mp3Path;

    }


    public void setparentid(long mparentid) {
        this.parentid = mparentid;

    }


    @Override
    public void setlevel(int level) {
        // TODO Auto-generated method stub
        this.level = level;
    }




    @Override
    public int level() {
        // TODO Auto-generated method stub
        return level;
    }

    @Override
    public int color() {
        return color_int;
    }

    @Override
    public void setcolor(int xcolor) {
         color_int = xcolor;
    }

    @Override
    public String mp3path() {
        // TODO Auto-generated method stub
        return mp3path;
    }


    @Override
    public Pure_Node getSelectedChild() {
        int i = getSelectedChildPosition();
        if (i == -1) return null;
        // in case of IndexOutOfBoundsException
        if (i >= 0 && i < children.size()) {
            return children.get(i);
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  "text=["+text()+"] " + "  id=[" + id + "] " +" parentid=["+parentid+"]" +"  level=[" + level() + "]"+" index4parent["+index4parent+"]"  + "  count=[" + children.size() + "]" + " mp3path=["+mp3path()+"]  selectedChildId=["+selectedChildId+"]" +" color_int=["+color_int+"]"  ;
    }





    public void addChildren(Pure_NodeImpl oneChildren) {
        this.children.add(oneChildren);
    }


    private int getSelectedChildPosition() {
        if (children == null) return -1;
        for (int i = 0; i < children.size(); i++) {
            Pure_NodeImpl nodeImpl = children.get(i);
            if (nodeImpl == null) continue;
            if (nodeImpl.id == selectedChildId) {
                return i;
            }
        }
        return -1;
    }
}
