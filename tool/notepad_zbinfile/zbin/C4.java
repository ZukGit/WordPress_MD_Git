
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class C4 {
    static  ArrayList<String> replaceTags = new ArrayList<>();

//    Wscript.exe  /x %userprofile%\Desktop\zbin\C4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【C4 vbs】
//    cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A9.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【C4 bat】



    static {
        replaceTags.add("icon_152.png");
        replaceTags.add("bootstrap.min.css");
        replaceTags.add("bootstrap.min.css");
        replaceTags.add("font-awesome.min.css");
        replaceTags.add("font-awesome.min.css");
        replaceTags.add("push.js");
        replaceTags.add("jquery.min.js");
        replaceTags.add("bootstrap.min.js");
        replaceTags.add("jquery.ztree.all-3.5.min.js");
        replaceTags.add("toc_conf.js");
        replaceTags.add("ztree_toc.js");
        replaceTags.add("windows_load.js");
        replaceTags.add("nav_back.css");
        replaceTags.add("nav_plus.css");
        replaceTags.add("style.css");
        replaceTags.add("AliPay.png");
        replaceTags.add("WeixinPay.png");
        replaceTags.add("skel.min.js");
        replaceTags.add("util.min.js");
        replaceTags.add("nav.js");
        replaceTags.add("comment-reply.min.js");

        replaceTags.add("zTreeStyle.css");
        replaceTags.add("nav_opt.js");
    }



    static String readStringFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return sb.toString();
    }




    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        //System.out.println(ToFirstChar("ABC  汉字转换为拼音CBA").toUpperCase()); //转为首字母大写
        // System.out.println(ToPinyinWithLine("A周 B杰 C伦"));
        // System.out.println(ToPinyinWithLine("ABC汉字转换为拼音CBA"));

        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


        //===============local-test begin===============
        //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        String currentFileName = curFile.getName();
        String localHtml_Content = readStringFromFile(curFile);

        String simpleFile = currentFileName.substring(0,currentFileName.lastIndexOf("."));
        String currentHtml_path = "./"+simpleFile.trim()+"_files/";

        HashMap<String,String> replaceTags_old_map = new HashMap<String,String>();
        for (String item: replaceTags) {

            System.out.println("item = "+ item);
            replaceTags_old_map.put(item,currentHtml_path+item);
        }

        HashMap<String,String> replaceTags_map = new HashMap<String,String>();


        for (int i = 0; i < replaceTags.size(); i++) {
            String item = replaceTags.get(i);
            String item_fix =  replaceTags_old_map.get(item);
            if(item.endsWith(".png")){
                replaceTags_map.put(item_fix,"https://raw.githack.com/ZukGit/ZHtml/gh-pages/themes/StyleTheme3/images/"+item);
            }else if(item.endsWith(".js")){
                replaceTags_map.put(item_fix,"https://raw.githack.com/ZukGit/ZHtml/gh-pages/themes/StyleTheme3/js/"+item);
            }else if(item.endsWith(".css")){
                replaceTags_map.put(item_fix,"https://raw.githack.com/ZukGit/ZHtml/gh-pages/themes/StyleTheme3/css/"+item);
            }
        }



        Map.Entry<String , String> entry;
        if(replaceTags_map != null){
            Iterator iterator = replaceTags_map.entrySet().iterator();
            while( iterator.hasNext() ){
                entry = (Map.Entry<String , String>) iterator.next();
                String oldStr =   entry.getKey();  //Map的Value
                String newStr =    entry.getValue();  //Map的Value
                System.out.println("old = "+ oldStr + "         newStr ="+ newStr);


                localHtml_Content = localHtml_Content.replace(oldStr,newStr);
                localHtml_Content = localHtml_Content.replace(newStr+".下载",newStr);
            }
            System.out.println("  to next !");
        } else{

            System.out.println("replaceTags_map  == null");
        }



// 把 localHtml_Content  写入 新的文件中


        String outfileName = "C4_networkable.html";
        String parentPath = curFile.getParent();
        System.out.println("parentPath =  "+ parentPath);
        System.out.println("outfile =  "+ parentPath+File.separator+outfileName);
        File outputFile = new File(parentPath+File.separator+outfileName);
        if(!outputFile.exists()){
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("begin  write！ ");
        writeContentToFile(outputFile,localHtml_Content);  // 把当前文件重新写入  上网化
        System.out.println("end ! ");
    }
}
