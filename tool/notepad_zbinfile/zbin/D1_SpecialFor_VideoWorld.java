


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

//  把bat 放入目录  点击 执行  更改目录中文件的名称
//
//land1.mp4
//and1 - 副本.mp4

public class D1_SpecialFor_VideoWorld {
    static String UserDir =  System.getProperty("user.dir");
    static String CurrentDir =  "";
    public static final ArrayList<File> mp4FileList_All = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_NeedFix = new ArrayList<File>();
    public static final ArrayList<File> mp4FileList_Fixed = new ArrayList<File>();
    public static Map<String,ArrayList<File>> nameMap = new HashMap<String,ArrayList<File>>();

    static Map<String,String> categoryMap = new HashMap<String,String>();

    static boolean isOutterMdFile = false;
 //        mountain=====山
//        river   =====河
//        lake    =====湖
//        ocean   =====海
//        sun     =====日
//        moon    =====月
//        star    =====星
//        light   =====光
//        sky     ====天玄
//        land    ====地黄
    static {
     categoryMap.put("mountain","山");
     categoryMap.put("river","河");
     categoryMap.put("lake","湖");
     categoryMap.put("ocean","海");
     categoryMap.put("sun","日");
     categoryMap.put("star","星");
     categoryMap.put("light","光");
     categoryMap.put("sky","天玄");
     categoryMap.put("land","地黄");
 }
    public static void main(String[] args) {


        String mMP4DirPath = null;

        // 1.bat调用测试打开===begin
        if (args.length >= 1) {
            mMP4DirPath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        // 1.bat调用测试打开===end

//          mMP4DirPath  = UserDir;          // 2.本地测试打开


        File curMP4Dir;
        curMP4Dir = new File(mMP4DirPath);
        if (mMP4DirPath != null && !mMP4DirPath.isEmpty() && (curMP4Dir.exists()) && curMP4Dir.isDirectory()) {
            CurrentDir = curMP4Dir.getAbsolutePath();
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        // 获取 当前路径下的 所有 mp4 文件
        System.out.println("当前文件夹路径:" + curMP4Dir.getAbsolutePath());
        File[] curFileList = curMP4Dir.listFiles();


        // 获取 满足命名标准的mp4文件
        getRuledMP4InCurrentDir(curFileList);

      //   addfixedFileItemAndUpdate();
  //  开始分析当前的文件并产生 对应的数据结构  填充 ruledMP4Map
// //land_0111x0111_0002_0011.mp4;
// tofoAnalysis
          todoAnalysisFileNameFillMap();

        File localMDFile = new File(CurrentDir+File.separator+"D1_local_videoworld.md");
        File outterMDFile = new File(CurrentDir+File.separator+"02Video_World.md");
        String localMdStr = todoMarkdown();
        System.out.println("内部MD文件！\n"+ localMdStr);
        WriteToFile(localMDFile,localMdStr);

        isOutterMdFile = true;
        String outterMdStr = todoMarkdown();
        WriteToFile(outterMDFile,outterMdStr);
        System.out.println("外部MD文件！\n"+ outterMdStr);
        System.out.println("程序执行结束！");
    }

    static String todoMarkdown() {
// 两个map的 遍历  fuck
        StringBuilder markdownSB = new StringBuilder();


        Map.Entry<String , String> titleEntry;


        if(categoryMap != null){
            Iterator iterator = categoryMap.entrySet().iterator();


            while( iterator.hasNext() ){
                titleEntry = (Map.Entry<String , String>) iterator.next();
              String preType =   titleEntry.getKey();  //Map的Value
              String titleName =   titleEntry.getValue();  //Map的Value

              markdownSB.append("# "+ titleName+"\n");

                Map<String,ArrayList<File>> mapItem =  ruledMP4Map.get(preType);
                Map.Entry<String , ArrayList<File>> entryMapItem;
                if(mapItem != null){
                    Iterator iteratorMP4 = mapItem.entrySet().iterator();
                    ArrayList<File> currentFileList = null;
                    while( iteratorMP4.hasNext() ){
                        entryMapItem = (Map.Entry<String ,ArrayList<File>>) iteratorMP4.next();
                        String size_identify = entryMapItem.getKey();  //Map的Value  //  land_1020x1400
                        int width = getWidthFromIdentity(size_identify);
                        int high = getHeighFromIdentity(size_identify);
                        currentFileList = entryMapItem.getValue();


                        markdownSB.append(trydoAnalysisFromFileList(currentFileList, width, high));


                    }
                }

            }
        }
        return markdownSB.toString();

    }


    static String trydoAnalysisFromFileList(ArrayList<File> fileList,int width , int high){
        StringBuilder sb = new StringBuilder();
        ArrayList<File> everyOutFileList = new ArrayList<File>();
        if(fileList.size() == 0){
            return "";
        }
        String rate = checkRate(width,high);
        if(fileList.size() == 1){
            everyOutFileList.add(fileList.get(0));
            if(width > high){  // 50%
                sb.append(BuildMarkDownFromArrayList(everyOutFileList,rate));

            }else{   //  33.3%
                sb.append(BuildMarkDownFromArrayList(everyOutFileList,rate));
            }
            everyOutFileList.clear();
            return sb.toString();
        }
        // 当同尺寸的数量大于2 的情况

        for (int i = 0; i < fileList.size(); i++) {
            if(rate.equals("33.3%")){

                if(i%3 == 0){
                    sb.append(BuildMarkDownFromArrayList(everyOutFileList,rate));
                    everyOutFileList.clear();
                }
                everyOutFileList.add(fileList.get(i));

            } else if(rate.equals("50.0%")){

                if(i%2 == 0){
                    sb.append(BuildMarkDownFromArrayList(everyOutFileList,rate));
                    everyOutFileList.clear();
                }
                everyOutFileList.add(fileList.get(i));

            }
        }

        // 把列表里剩下的 也加载到 StringBuilder中
        sb.append(BuildMarkDownFromArrayList(everyOutFileList,rate));
        return sb.toString();
    }

    static String  checkRate(int width , int high){
        String rate = "33.3%";
        if(width > high){  // 50%
            rate="50.0%";

        }else{   //  33.3%
            rate="33.3%";
        }
        return rate;
    }

    // zimage/zvideo/02world/
static String BuildMarkDownFromArrayList(ArrayList<File> fileArray, String showRate){

        if(fileArray.size() == 0){
            return "";
        }

        StringBuilder resultStr = new StringBuilder();
    resultStr.append("<p  style=\"white-space:nowrap\" >\n");
    for (File sameSizeMP4File: fileArray) {
        resultStr.append("<video autoplay=\"true\" controls=\"controls\" width=\""+showRate+"\" hight=\""+showRate+"\" >\n");

// <source src="./zimage/zvideo/02world/sky5.mp4" type="video/mp4" />

        // 外部的md文件需要在这里产生
        if(isOutterMdFile){
            resultStr.append("<source src=\""+"."+File.separator+mdExistPath+sameSizeMP4File.getName()+"\" type=\"video/mp4\" />\n");

        }else{
            resultStr.append("<source src=\""+"."+File.separator+sameSizeMP4File.getName()+"\" type=\"video/mp4\" />\n");

        }
    // </video>

                resultStr.append(" </video>\n");
    }
    resultStr.append("</p>\n\n\n");
        return resultStr.toString();

}
// zimage/zvideo/zvideo/  生成的文件包含该路径
static String mdExistPath = "zimage"+File.separator+"zvideo"+File.separator+"02world"+File.separator;
    // 生成的文件包含空的文件
//static String mdExistPath ="";




    // land_1020x1400
   static int getWidthFromIdentity(String identify){
        int width = 0 ;
        if(identify.contains("_")){
            String[] arr = identify.split("_");
            if(arr[1].contains("x")){
                String width2High = arr[1].split("x")[0];
                width = Integer.parseInt(width2High);
            }
        }
        return width;
    }

    // land_1020x1400
    static int getHeighFromIdentity(String identify){
        int high =  0 ;
        if(identify.contains("_")){
            String[] arr = identify.split("_");
            if(arr[1].contains("x")){
                String width2High = arr[1].split("x")[1];
                high = Integer.parseInt(width2High);
            }
        }
        return high;
    }


    static void todoAnalysisFileNameFillMap() {
        if(mp4FileList_All.size() > 0){
            for (int i = 0; i < mp4FileList_All.size(); i++) {
                File itemFile = mp4FileList_All.get(i);
                String fileName = itemFile.getName();
                String[] mPropertyArr = fileName.split("_");
                if(categoryMap.containsKey(mPropertyArr[0])){ // 前缀是 10个之一
                   String preType = mPropertyArr[0];
                   String preType_SizeIdenty=preType+"_"+mPropertyArr[1];
                    Map<String,ArrayList<File>> categoryMap = ruledMP4Map.get(preType);

                    if(categoryMap == null){ // 当前Map中没有对应的 子Map
                        Map<String,ArrayList<File>> mMapItem = new  HashMap<String,ArrayList<File>>();
                        ArrayList<File> fileList = new  ArrayList<File>();
                        fileList.add(itemFile);
                        mMapItem.put(preType_SizeIdenty,fileList);
                        ruledMP4Map.put(preType,mMapItem);

                    }else{  // 分类的  categoryMap 不为空


                  ArrayList<File> fileList =  categoryMap.get(preType_SizeIdenty);
                  if(fileList == null){
                      ArrayList<File> fileListItem = new  ArrayList<File>();
                      fileListItem.add(itemFile);
                      categoryMap.put(preType_SizeIdenty,fileListItem);
                  }else{ // 拿到的 ArrayList不为空

                      fileList.add(itemFile);

                  }
                 }
                }
            } // for-end
        }
    }

    static Map<String,Map<String,ArrayList<File>>> ruledMP4Map = new HashMap<String,Map<String,ArrayList<File>>>();

    //
    static void getRuledMP4InCurrentDir(File[] fileList) {
        if(fileList == null || fileList.length ==0){
            System.out.println(" 当前路径的文件数量为0！ ");
            return;
        }


        for (File fileItem: fileList){
            String fileName = fileItem.getName();
            if(fileName.endsWith(".mp4")){ //  如果文件以 .mp4 结尾
              //  mp4FileList_All.add(fileItem);

                if(isRuleNameStyle(fileItem)){
                    mp4FileList_All.add(fileItem);
                }

            }
        }
    }


    //  中间的 002 标识 111x111这样的视频的数量
    //  最后的序号 11 标识在当前land视频中的序号
    //land_0111x0111_0002_0011.mp4;

    static boolean isRuleNameStyle(File mp4File) {
        String mp4FileName =  mp4File.getName();
        boolean isRuleFlag = false;
        String mNameNoType = mp4FileName.substring(0,mp4FileName.lastIndexOf("."));
        String[] strArr = mNameNoType.split("_");
        if(!mp4FileName.contains(" ")  && strArr != null && strArr.length == 4 && strArr[1].contains("x")
                && Integer.parseInt( strArr[strArr.length -1] ) > 0 && Integer.parseInt( strArr[strArr.length -2] ) > 0 ){
            isRuleFlag = true;
        }
        return isRuleFlag;
    }


 








    static void WriteToFile(File curFile , String classContent) {
        try {
            BufferedWriter strBuffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curFile), "utf-8"));
            strBuffWriter.write(classContent);
            strBuffWriter.flush();
            strBuffWriter.close();
        }catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

    }


}