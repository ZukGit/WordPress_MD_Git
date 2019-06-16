import java.io.*;
import java.util.ArrayList;

public class C0_DirCreate {
    static ArrayList<String> patList_i = new ArrayList<String>();
    static ArrayList<String> patList_g = new ArrayList<String>();
    static ArrayList<String> patList_v = new ArrayList<String>();

    static {
        patList_i.add("i");
        patList_i.add("i" + File.separator + "temp");
        patList_i.add("i" + File.separator + "whole");
        patList_i.add("i" + File.separator + "back");
        patList_i.add("i" + File.separator + "down" + File.separator + "white");
        patList_i.add("i" + File.separator + "down" + File.separator + "black");
        patList_i.add("i" + File.separator + "down" + File.separator + "near");
        patList_i.add("i" + File.separator + "for");
        patList_i.add("i" + File.separator + "normal");
        patList_i.add("i" + File.separator + "cartoon");
        patList_i.add("i" + File.separator + "tech");
        patList_i.add("i" + File.separator + "zygote");
        patList_i.add("i" + File.separator + "normal");
        patList_i.add("i" + File.separator + "ownerview");
        patList_i.add("i" + File.separator + "up");
        patList_i.add("i" + File.separator + "up" + File.separator + "a_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "b_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "c_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "d_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "e_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "f_face");
        patList_i.add("i" + File.separator + "up" + File.separator + "a_only");
        patList_i.add("i" + File.separator + "up" + File.separator + "b_only");
        patList_i.add("i" + File.separator + "up" + File.separator + "c_only");
        patList_i.add("i" + File.separator + "up" + File.separator + "d_only");
        patList_i.add("i" + File.separator + "up" + File.separator + "e_only");
        patList_i.add("i" + File.separator + "up" + File.separator + "f_only");

        patList_g.add("g");
        patList_g.add("g" + File.separator + "temp");
        patList_g.add("g" + File.separator + "crime");
        patList_g.add("g" + File.separator + "up");
        patList_g.add("g" + File.separator + "inout");
        patList_g.add("g" + File.separator + "ownerview");

        patList_v.add("v");
        patList_v.add("v");
        patList_v.add("v" + File.separator + "all");
        patList_v.add("v" + File.separator + "up");
        patList_v.add("v" + File.separator + "am");
        patList_v.add("v" + File.separator + "am"+ File.separator + "bei");
        patList_v.add("v" + File.separator + "am"+ File.separator + "datui");
        patList_v.add("v" + File.separator + "am"+ File.separator + "foottojb");
        patList_v.add("v" + File.separator + "am"+ File.separator + "jiao");
        patList_v.add("v" + File.separator + "am"+ File.separator + "shoubi");
        patList_v.add("v" + File.separator + "am"+ File.separator + "tiannai");
        patList_v.add("v" + File.separator + "am"+ File.separator + "xiongqian");
        patList_v.add("v" + File.separator + "am"+ File.separator + "yinyangtiehe");
        patList_v.add("v" + File.separator + "am"+ File.separator + "monai");
        patList_v.add("v" + File.separator + "am"+ File.separator + "rujiao");
        patList_v.add("v" + File.separator + "am"+ File.separator + "koubi");

        patList_v.add("v" + File.separator + "ym");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "beauyscene");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "beddown");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "beddown" +File.separator +"01cetaitui");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "beddown" +File.separator +"02gaotaitui");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "justbody");

        patList_v.add("v" + File.separator + "ym"+ File.separator + "ownerview");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun"+ File.separator +"01taijiao");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun"+ File.separator +"02fantai");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun"+ File.separator +"03qujiao");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun"+ File.separator +"04quanbao");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standdun"+ File.separator +"05fanquanbao");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standup");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standup"+ File.separator +"01facetofacegaojiao");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "standup"+ File.separator +"02facetobacktuiche");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "special");

        patList_v.add("v" + File.separator + "ym"+ File.separator + "wangbatai");
        patList_v.add("v" + File.separator + "ym"+ File.separator + "wangbatai"+ File.separator +"01tuizhi");


        patList_v.add("v" + File.separator + "support");
        patList_v.add("v" + File.separator + "support"+ File.separator + "01swim");
        patList_v.add("v" + File.separator + "support"+ File.separator + "02runspace");
        patList_v.add("v" + File.separator + "support"+ File.separator + "03yanshe");
        patList_v.add("v" + File.separator + "wm");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beauyscene");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "shuangdong");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "gangjiao");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"01fuxi");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"02backview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"03cetang");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"04cenearview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"05stacknearview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "beddown"+ File.separator +"06gaotaitui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"01normal");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"02nearview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"03gui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"04guibackview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"05taicetui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "chuanjiao"+ File.separator +"06lowtuiche");


        patList_v.add("v" + File.separator + "wm"+ File.separator + "downnear");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "headdown");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview"+ File.separator +"01chui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview"+ File.separator +"02backinout");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview"+ File.separator +"03rujiao");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview"+ File.separator +"04inout");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "ownerview"+ File.separator +"05moxiong");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "seatdown");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "seatdown"+ File.separator +"01facetoback");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "seatdown"+ File.separator +"02facebacknearview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"01taidui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"02datui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"03backviewgui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"04frontviewgui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"05frontviewstand");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standback"+ File.separator +"06lowviewstand");

        patList_v.add("v" + File.separator + "wm"+ File.separator + "standface");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standface"+ File.separator +"01baotui");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standface"+ File.separator +"02baotuinearview");
        patList_v.add("v" + File.separator + "wm"+ File.separator + "standface"+ File.separator +"03quanbao");

    }

    public static void main(String[] args) {
        String usrDir = System.getProperties().getProperty("user.dir");
        String templateDir = usrDir+File.separator+"templatedir";
        File dirFile = new File(templateDir);
        dirFile.mkdir();
        for (int i = 0 ; i < patList_i.size(); i ++ ){
            String path =  patList_i.get(i);
            File file = new File(templateDir+File.separator+path);
            file.mkdirs();
        }

        for (int i = 0 ; i < patList_g.size(); i ++ ){
            String path =  patList_g.get(i);
            File file = new File(templateDir+File.separator+path);
            file.mkdirs();
        }

        for (int i = 0 ; i < patList_v.size(); i ++ ){
            String path =  patList_v.get(i);
            File file = new File(templateDir+File.separator+path);
            file.mkdirs();
        }

    }
}

