import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F2_DownFile_AOSP {
// tree /f  查看所有目录
    static File F2File_AOSP_DIR = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F2_AOSP_File");


    static File F2_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F2.properties");
    static InputStream F2_Properties_InputStream;
    static OutputStream F2_Properties_OutputStream;
    static Properties F2_Properties = new Properties();
    static int downloadFileCount = 0;

    static {
        try {
            F2_Properties_InputStream = new BufferedInputStream(new FileInputStream(F2_Properties_File.getAbsolutePath()));
            F2_Properties.load(F2_Properties_InputStream);
            Iterator<String> it = F2_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                //   System.out.println("key:" + key + " value: " + F2_Properties.getProperty(key));
            }
            F2_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setProperity() {
        try {
            F2_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(F2_Properties_File.getAbsolutePath()));
            F2_Properties.store(F2_Properties_OutputStream, "");
            F2_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setProperityInt(int value, String key) {
        int fileCount_prop = 0;
        String localFileCountStr = F2_Properties.getProperty(key);
        if (localFileCountStr == null || "".equals(localFileCountStr) || "null".equals(localFileCountStr)) {
            fileCount_prop = 0;
        } else {
            fileCount_prop = Integer.parseInt(localFileCountStr);
        }
        if (value != fileCount_prop) {
            F2_Properties.setProperty(key, "" + value);
        }
    }

    static String AOSP_FILE_SITE4_1 = "https://www.androidos.net.cn/android/";  //  第一部分  网址
    static String Prefix4_3 = "/download";  // 下载路径前缀 第三部分
    static String Prefix4_3_Visited = "/xref";  // 浏览路径 前缀 第三部分

    static int MAX_COUNT_CHAR_IN_ROW = 168;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 168;
    static NumberFormat nf = new DecimalFormat("0.00");
    // 输入的参数列表
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    //  curl https://www.androidos.net.cn/android/9.0.0_r8/download/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
/*    https://www.androidos.net.cn/android/    【第一部分  网址】
      9.0.0_r8                                  【第二部分  版本TAGNUM】
     /download/                                     【第三部分  前缀 】
    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java   【第四部分 文件的AOSP路径】
*/

    static class AOSP_Version_ITEM {
        double AndroidVersion;  // 7  8  9 10 11
        String Android_NickName;  // O  P Q  R  S  T
        char Android_CharName;
        int apiVersion;  // API 版本  27  26   25  24   23
        String tagNum;     //  tag 名称 9.0.0_r8   8.0.0_r4
        String aospSite;  //  默认 AOSP_FILE_SITE
        String preSite4_3; // 默认 Prefix4_3
        String preSite4_3_Visited; // 默认 Prefix4_3  /xref   网络浏览路径
        String releaseTime;  // 2019.03  这样的时间戳字符串
        ArrayList<String> versionDesc;  // 版本特点的描述


        public String getDownLoadPre() {
            String downloadUrlPre = "";
            downloadUrlPre = this.aospSite + this.tagNum + this.preSite4_3;
            return downloadUrlPre;

        }

        public String getVisitedPre() {
            String visitedUrlPre = "";
            visitedUrlPre = this.aospSite + this.tagNum + this.preSite4_3_Visited;
            return visitedUrlPre;

        }


        AOSP_Version_ITEM(double version, String androidNick, int apiVersion, String tagNum, String releaseTime) {
            this.Android_NickName = androidNick;
            this.Android_CharName = androidNick.toUpperCase().charAt(0);
            this.AndroidVersion = version;
            this.tagNum = tagNum;
            this.apiVersion = apiVersion;
            this.aospSite = AOSP_FILE_SITE4_1;
            this.preSite4_3 = Prefix4_3;
            this.preSite4_3_Visited = Prefix4_3_Visited;
            this.releaseTime = releaseTime;

        }

        public double getAndroidVersion() {
            return AndroidVersion;
        }

        public void setAndroidVersion(double androidVersion) {
            AndroidVersion = androidVersion;
        }

        public String getAndroid_NickName() {
            return Android_NickName;
        }

        public void setAndroid_NickName(String android_NickName) {
            Android_NickName = android_NickName;
        }

        public char getAndroid_CharName() {
            return Android_CharName;
        }

        public void setAndroid_CharName(char android_CharName) {
            Android_CharName = android_CharName;
        }

        public int getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(int apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getTagNum() {
            return tagNum;
        }

        public void setTagNum(String tagNum) {
            this.tagNum = tagNum;
        }

        public String getAospSite() {
            return aospSite;
        }

        public void setAospSite(String aospSite) {
            this.aospSite = aospSite;
        }

        public String getPreSite4_3() {
            return preSite4_3;
        }

        public void setPreSite4_3(String preSite4_3) {
            this.preSite4_3 = preSite4_3;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public ArrayList<String> getVersionDesc() {
            return versionDesc;
        }

        public void setVersionDesc(ArrayList<String> versionDesc) {
            this.versionDesc = versionDesc;
        }
    }

    static ArrayList<AOSP_Version_ITEM> aospList = new ArrayList<AOSP_Version_ITEM>();


    static int getMaxVersionOnRecord() {
        int maxVersion = 0;
        for (int i = 0; i < aospList.size(); i++) {

            if (aospList.get(i).getAndroidVersion() > maxVersion) {
                maxVersion = (int) aospList.get(i).getAndroidVersion();
            }

        }
        return maxVersion;
    }

    static char getMaxVersionTagOnRecord() {
        int version = getMaxVersionOnRecord();
        return getAndroidTagWithVersion(version);
    }


    static int getMinVersionOnRecord() {
        int minVersion = 0;
        for (int i = 0; i < aospList.size(); i++) {

            if (minVersion == 0) {
                minVersion = (int) aospList.get(i).getAndroidVersion();
            }
            if (aospList.get(i).getAndroidVersion() < minVersion) {
                minVersion = (int) aospList.get(i).getAndroidVersion();
            }
        }
        return minVersion;
    }

    static double getAndroidVersionWithTag(char mAndroidTag) {
        double version = 0;
        for (int i = 0; i < aospList.size(); i++) {
            if (mAndroidTag == aospList.get(i).Android_CharName) {
                version = aospList.get(i).AndroidVersion;
                break;
            }
        }
        return version;

    }

    static char getAndroidTagWithVersion(int androidVersion) {
        char versionTag = ' ';
        for (int i = 0; i < aospList.size(); i++) {
            if (androidVersion == (int) aospList.get(i).AndroidVersion) {
                versionTag = aospList.get(i).Android_CharName;
                break;
            }
        }
        return versionTag;

    }


    static boolean isAndroidTagRight(char mAndroidTag) {
        boolean isTagOK = false;
        char upTagchar = ("" + mAndroidTag).toUpperCase().charAt(0);


        for (int i = 0; i < aospList.size(); i++) {
            if (upTagchar == aospList.get(i).Android_CharName) {
                isTagOK = true;
                break;
            }
        }

        return isTagOK;

    }





    static {
        AOSP_Version_ITEM android_5_0 = new AOSP_Version_ITEM(5.0, "LOLLIPOP", 21, "5.0.1_r1", "2014.11");
        aospList.add(android_5_0);

        AOSP_Version_ITEM android_5_1 = new AOSP_Version_ITEM(5.1, "LOLLIPOP", 22, "5.1.0_r3", "2015.02");
        aospList.add(android_5_1);

        AOSP_Version_ITEM android_6_0 = new AOSP_Version_ITEM(6.0, "MARSHMALLOW", 23, "6.0.1_r16", "2015.05");
        aospList.add(android_6_0);

        AOSP_Version_ITEM android_7_0 = new AOSP_Version_ITEM(7.0, "NOUGAT", 24, "7.0.0_r31", "2016.03");
        aospList.add(android_7_0);

        AOSP_Version_ITEM android_7_1 = new AOSP_Version_ITEM(7.1, "NOUGAT", 25, "7.1.1_r28", "2016.08");
        aospList.add(android_7_1);

        AOSP_Version_ITEM android_8_0 = new AOSP_Version_ITEM(8.0, "OREO", 26, "8.0.0_r4", "2017.03");
        aospList.add(android_8_0);

        AOSP_Version_ITEM android_9_0 = new AOSP_Version_ITEM(9.0, "PIE", 27, "9.0.0_r8", "2018.03");
        aospList.add(android_9_0);

        AOSP_Version_ITEM android_10_0 = new AOSP_Version_ITEM(10.0, "Q", 29, "10.0.0_r6", "2019.06");
        aospList.add(android_10_0);


    }


    public static void main(String[] args) {

        long timestamp1 = System.currentTimeMillis();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        if (!F2File_AOSP_DIR.exists()) {
            F2File_AOSP_DIR.mkdirs();
        }


        if (mKeyWordName.size() == 0) {
            //  System.out.println("用户输入的英文缩写参数为空-将打印所有保存的字典信息");
            showAllVersionInfo();
            showTip();

            addAllFileDir(F2File_AOSP_DIR);
            initSimpleFileSet();
            int fileCount_calc = allSimpleFileSet.size(); // 文件中文件数量


            setProperityInt(fileCount_calc, "localFileCount");
            setProperity();
            //   System.out.println("PATH:"+System.getProperties().getProperty("java.library.path"));
            //   String command = "cmd.exe /c start   Notepad++.exe "  +" C:/Users/zhuzj5/Desktop/zbin/F2_AOSP_File/accesspoint/9_0_AccessPoint_P.java";
            //   System.out.println("程序执行:"+ execCMD(command));
            return;
        }


        String firstParam = mKeyWordName.get(0);
        if (!checkInputParam(firstParam)) {
            return;
        }


        DoFileOperation(inputFileName, inputAndroidVersion);


        setProperity();

    }

    public static void DoFileOperation(String fileName, int inputAndroidVersion) {
        JavaFile_Item targetJavaFile = null;
        ArrayList<JavaFile_Item>  matchJavaFileList = new  ArrayList<JavaFile_Item>();

        for (int i = 0; i < allFileList.size(); i++) {
            FilePath_ITEM filePathItem = allFileList.get(i);
            if (filePathItem.mfileName_lower.startsWith(fileName) || filePathItem.mfileName_lower.contains(fileName) ) {  // 文件名开头
                ArrayList<JavaFile_Item> javaFileList = filePathItem.allJavaFileList;

                for (int j = 0; j < javaFileList.size(); j++) {

                    if (inputAndroidVersion == (int) javaFileList.get(j).androidVersion) {
                        matchJavaFileList.add(javaFileList.get(j));
                        break;
                    }

                }


            }
        }

        targetJavaFile = choiceBestJaveTarget(matchJavaFileList ,  fileName);

        if (targetJavaFile != null) {

            DoCommandOperation(targetJavaFile);
        }else{
            showTip();
            System.out.println("当前搜索的关键字无法匹配到文件,请重新查询!");
        }

    }

    public static  JavaFile_Item choiceBestJaveTarget(ArrayList<JavaFile_Item> candicateList , String fileName){
        JavaFile_Item targetJavaFile = null;

        if(candicateList.size() == 0 ){
            return null;
        }
        if(candicateList.size() == 1 ){
            return candicateList.get(0);
        }
        int length = fileName.length();
        double rate1 = 0;
        int selectIndex = 0;
        for (int i = 0; i < candicateList.size(); i++) {

            int itemNameLength =  candicateList.get(i).targetJavaFile.getName().length();
            printJavaFileInfo(candicateList.get(i),"匹配【"+i+"】【"+candicateList.get(i).targetJavaFile.getName()+"】");
            double  rateTemp =(double) length/itemNameLength;
            if(rateTemp > rate1){
                rate1 = rateTemp;
                selectIndex = i;
            }
        }
        targetJavaFile = candicateList.get(selectIndex);
        return targetJavaFile;
    }


    static JavaFile_Item getCompareVersionFileItem(String parentAbsPath ,int targetVersion) {
        JavaFile_Item compareItem = null;
        int compareVersion = 0;
        int maxVersion = getMaxVersionOnRecord();
        int minVersion = getMinVersionOnRecord();

        if(targetVersion == maxVersion){
            compareVersion = maxVersion - 1;
        }else if(targetVersion == minVersion){
            compareVersion = minVersion + 1;
        }else if( targetVersion < maxVersion && targetVersion > minVersion){
            compareVersion =  targetVersion + 1;
        }
        if(compareVersion == 0){
            return null; // 如果 没找到  那么 说明可能 参数出问题 需要返回 空
        }
        for (int i = 0; i < allFileList.size(); i++) {
            FilePath_ITEM filePathItem = allFileList.get(i);
            for (int j = 0; j < filePathItem.allJavaFileList.size(); j++) {
                JavaFile_Item javaItem = filePathItem.allJavaFileList.get(j);


                if(javaItem.parrentFileDir.getAbsolutePath().equals(parentAbsPath) && (int)javaItem.androidVersion == compareVersion ){
                    compareItem = javaItem;
                    return compareItem;
                }
            }
        }

        return compareItem;

    }



    public static  void showTip(){
        ArrayList<String> tipLog = new  ArrayList<String>();
        tipLog.add("tip1:  // 首次执行 zaosp_file_F2.bat 下载对应版本文件到本地,并会打印当前记录的Log");
        tipLog.add("tip2:  // zaosp_file_F2.bat wifiseting_q    // 打开Q版本的 匹配的 wifiseting 文件");
        tipLog.add("tip3:  // zaosp_file_F2.bat wifiseting_Q    // 打开Q版本的 匹配的 wifiseting 文件");
        tipLog.add("tip4:  // zaosp_file_F2.bat wifiseting_7    // 打开 android7 版本的 匹配的 wifiseting 文件");
        tipLog.add("tip5:  // zaosp_file_F2.bat wifiseting_22    // 22 超过最大版本号 (默认最大【安卓"+getMaxVersionOnRecord()+"】) 匹配的 wifiseting 文件");
        tipLog.add("tip6:  // 默认打开 1.当前文件夹  2.notepad++打开当前匹配文件 3.chrome打开该文件的网络地址 4.显示该文件信息info");
        ArrayPrint(tipLog,"提示信息");
    }







    public static void DoCommandOperation(JavaFile_Item mtargetJavaFile) {


        printSelectedJavaFileInfo(mtargetJavaFile,"择优选中文件"+mtargetJavaFile.targetJavaFile.getName()+"信息");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("后台打开操作1: explore文件夹打开目录:  " + mtargetJavaFile.parrentFileDir.getAbsolutePath());

        String comandFileExplore1 = "cmd.exe /c start   explorer  " + mtargetJavaFile.parrentFileDir.getAbsolutePath();
        execCMD(comandFileExplore1);


        if (mtargetJavaFile.isfile_exit_innet) {  // 网络端存在的路径 才能打开
            String commandChrome2 = "cmd.exe /C start chrome  " + mtargetJavaFile.mVisitedUrl;   // 打开Chrome浏览器  visited
            System.out.println("后台打开操作2: Chrome 打开文件 " + mtargetJavaFile.targetJavaFile.getName()+" 的网络地址:");
            execCMD(commandChrome2);
        } else {
            System.out.println("当前搜索的文件:" + mtargetJavaFile.targetJavaFile.getName() + "在当前版本【" + mtargetJavaFile.androidVersion + "】不存在,请重新输入参数！");
            return;
        }


        //   String command = "cmd.exe /c start   Notepad++.exe "  +" C:/Users/zhuzj5/Desktop/zbin/F2_AOSP_File/accesspoint/9_0_AccessPoint_P.java";



        String commandNotead3 = "cmd.exe /c start   Notepad++.exe " + mtargetJavaFile.targetJavaFilePath;
        System.out.println("后台打开操作3: notepad++ 打开本地文件 " + mtargetJavaFile.targetJavaFilePath);

        execCMD(commandNotead3);



        // 获取到对比文件
        int currentVersion = (int)mtargetJavaFile.androidVersion;
        JavaFile_Item compareJavaFile =   getCompareVersionFileItem(mtargetJavaFile.parrentFileDir.getAbsolutePath(),currentVersion);
        // 打开对比文件进行 BCompany  对比
        if(compareJavaFile != null){
            int compareVersion = (int)compareJavaFile.androidVersion;
            String commandCompare4 ="";
            if(currentVersion > compareVersion){
                commandCompare4 =  "cmd.exe /c start   BCompare.exe " + mtargetJavaFile.targetJavaFilePath +"  "+compareJavaFile.targetJavaFilePath;
                System.out.println("后台打开操作4. Beyond Compare 【 "+ mtargetJavaFile.targetJavaFile.getName()+" 对比 " + compareJavaFile.targetJavaFile.getName()+" 】");
            }else{
                commandCompare4 =  "cmd.exe /c start   BCompare.exe "+   compareJavaFile.targetJavaFilePath  +"  "+ mtargetJavaFile.targetJavaFilePath;
                System.out.println("后台打开操作4: Beyond Compare 【 "+ compareJavaFile.targetJavaFile.getName()+" 对比 " + mtargetJavaFile.targetJavaFile.getName()+" 】");


            }

            execCMD(commandCompare4);
        }


        // 只读取第一个餐数

        //wifiservice     打开当前最新版本的 wifiservice文件
        // wifiservice_L     打开L版本的 wifiservice文件
        // wifiservice_Q     打开Q版本的 wifiservice文件
        //wifiservice_K  没有版本时的处理
        // wifiservice_JKFS
        //aaaaa_a   输入参数的处理
        //  如果 没有查询到文件 怎么处理?
        // 只有一个输入参数时   1.notepad打开该对应的文件   C:\Program Files\Notepad++.exe  notepad++  xxxxx
        // 2.Chrome 打开访问的路径  chrome xxxxx
        //4. 自动打开该文件对应的本地文件夹   explorer C:\
        // 4.在当前shell目录中显示 该文件对应的一些信息  函数名称列表  属性成员列表
//  打开图片  String command7 = "rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + pngFile.getAbsolutePath();


    }

    static void printSelectedJavaFileInfo(JavaFile_Item mtargetJavaFile  , String title ){
        ArrayList<String> groupLogInfoList = getJavaFIleGroupInfoList(mtargetJavaFile.originFileName);
        ArrayPrint(groupLogInfoList, mtargetJavaFile.originFileName+"文件各版本信息");

        MAX_COUNT_CHAR_IN_ROW = 132;
        ArrayList<String> fileInfo = new ArrayList<String>();
        fileInfo.add("文件名:" + mtargetJavaFile.targetJavaFile.getName());
        fileInfo.add("文件版本:" + mtargetJavaFile.androidVersion);
        fileInfo.add("文件版本号:" + mtargetJavaFile.androidNickChar);
        fileInfo.add("文件api:" + mtargetJavaFile.apiVersion);
        fileInfo.add("文件代码行数:" + getLineNum(mtargetJavaFile.targetJavaFile));
//        fileInfo.add("文件代码函数:" + "xxxxxxxx");
//        fileInfo.add("文件代码成员:" + "xxxxxxxx");
        ArrayList<String> memberList =  analysisJavaFile_Member(mtargetJavaFile.targetJavaFile , mtargetJavaFile.originFileName);
        // System.out.println("memberList.size()="+ memberList.size() );
        fileInfo.addAll(memberList);

        ArrayList<String> methodList =  analysisJavaFile_Method(mtargetJavaFile.targetJavaFile , mtargetJavaFile.originFileName);
        fileInfo.addAll(methodList);
        ArrayPrint(fileInfo, title);
        MAX_COUNT_CHAR_IN_ROW = MAX_COUNT_CHAR_IN_ROW_DEFAULT;
    }

    static void printJavaFileInfo(JavaFile_Item mtargetJavaFile  , String title ){

        MAX_COUNT_CHAR_IN_ROW = 132;
        ArrayList<String> fileInfo = new ArrayList<String>();
        fileInfo.add("文件名:" + mtargetJavaFile.targetJavaFile.getName());
        fileInfo.add("文件版本:" + mtargetJavaFile.androidVersion);
        fileInfo.add("文件版本号:" + mtargetJavaFile.androidNickChar);
        fileInfo.add("文件api:" + mtargetJavaFile.apiVersion);
        fileInfo.add("文件代码行数:" + getLineNum(mtargetJavaFile.targetJavaFile));
//        fileInfo.add("文件代码函数:" + "xxxxxxxx");
//        fileInfo.add("文件代码成员:" + "xxxxxxxx");
        ArrayPrint(fileInfo, title);
        MAX_COUNT_CHAR_IN_ROW = MAX_COUNT_CHAR_IN_ROW_DEFAULT;
    }

    static String inputFileName = "";
    static int inputAndroidVersion = 0;

    public static boolean checkInputParam(String firstParam) {
        boolean isParamOK = true;
        String mfixParam = new String(firstParam).trim();

        char firstChar = mfixParam.trim().charAt(0);
        while (Character.isDigit(firstChar)) {
            System.out.println("用户输入的参数首字符为数字【" + firstChar + "】,将自动去除！");
            mfixParam = mfixParam.substring(1, mfixParam.length());
            firstChar = mfixParam.trim().charAt(0);
        }

        mfixParam = clearSpcialChar(mfixParam);

        if (mfixParam.contains("_")) {  // wifisetting_11    wifisetting_q
            String fileName = mfixParam.substring(0, mfixParam.lastIndexOf("_")).toLowerCase().trim();
            String endTag = mfixParam.substring(mfixParam.lastIndexOf("_") + 1).trim().toUpperCase();
            inputFileName = fileName;
            if (isNumeric(endTag)) { // xxx_10     xxx_11     xxx_9
                int androidVersion = Integer.parseInt(endTag);
                int currentMaxVersion = getMaxVersionOnRecord();
                if (androidVersion > currentMaxVersion || androidVersion < getMinVersionOnRecord()) {
                    System.out.println("用户输入的安卓版本【" + endTag + "】无效将默认设置为最大记录版本【安卓" + currentMaxVersion + "】");
                    inputAndroidVersion = currentMaxVersion;
                } else {
                    inputAndroidVersion = androidVersion;    // 用户输入 符合要求
                }
            } else {    //xxxx_Q   xxxx_L   xxxx_N   xxxx_QL   //  只取第一个字母
                String firstCharStr = endTag;
                if (endTag.length() > 1) {
                    firstCharStr = endTag.charAt(0) + "";
                }

                char inputAndroidChar = firstCharStr.charAt(0);
                if (!isAndroidTagRight(inputAndroidChar)) {
                    inputAndroidVersion = getMaxVersionOnRecord();    // 用户输入 符合要求
                    System.out.println("用户输入的安卓版本编号【" + endTag + "】无效将默认设置为最大记录版本【安卓" + getMaxVersionTagOnRecord() + "-" + inputAndroidVersion + "  】");
                } else {  // 读取到字母  _ L  正确的字母
                    inputAndroidVersion = (int) getAndroidVersionWithTag(inputAndroidChar);
                }
            }


        } else {    // wifisetting   //  默认记录的最大值
            inputFileName = mfixParam.trim().toLowerCase();
            inputAndroidVersion = getMaxVersionOnRecord();  //
        }

        if (inputAndroidVersion == 0 || "".equals(inputFileName)) {
            System.out.println("用户的输入参数: 【" + firstParam + " 】 【filename=" + inputFileName + "】【version=" + inputAndroidVersion + "】无法解析 重重新输入!");
            isParamOK = false;
        } else {

            System.out.println("用户的输入参数解析成功: 【" + firstParam + " 】 【filename=" + inputFileName + "】【version=" + inputAndroidVersion + "】");
        }
        return isParamOK;
    }

    public static String clearSpcialChar(String mParam) {
        // 去除异常符号
        String mfixParam = new String(mParam).trim();

        mfixParam = mfixParam.replace("*", "");
        mfixParam = mfixParam.replace("~", "");
        mfixParam = mfixParam.replace(":", "");
        mfixParam = mfixParam.replace(".", "");
        mfixParam = mfixParam.replace(";", "");
        mfixParam = mfixParam.replace("-", "");
        mfixParam = mfixParam.replace("!", "");
        mfixParam = mfixParam.replace("@", "");
        mfixParam = mfixParam.replace("#", "");
        mfixParam = mfixParam.replace("(", "");
        mfixParam = mfixParam.replace("（", "");
        mfixParam = mfixParam.replace(")", "");
        mfixParam = mfixParam.replace("）", "");
        mfixParam = mfixParam.replace("【", "");
        mfixParam = mfixParam.replace("】", "");
        mfixParam = mfixParam.replace("、", "");
        mfixParam = mfixParam.replace("/", "");
        mfixParam = mfixParam.replace("\\", "");
        mfixParam = mfixParam.replace("[", "");
        mfixParam = mfixParam.replace("]", "");
        mfixParam = mfixParam.replace("{", "");
        mfixParam = mfixParam.replace("}", "");
        mfixParam = mfixParam.replace("|", "");
        mfixParam = mfixParam.replace(",", "");
        mfixParam = mfixParam.replace("<", "");
        mfixParam = mfixParam.replace(">", "");
        mfixParam = mfixParam.replace("￥", "");
        mfixParam = mfixParam.replace("=", "");
        mfixParam = mfixParam.replace("$", "");
        mfixParam = mfixParam.replace("%", "");
        mfixParam = mfixParam.replace("^", "");
        mfixParam = mfixParam.replace("&", "");
        mfixParam = mfixParam.replace("+", "");
        mfixParam = mfixParam.replace("`", "");
        return mfixParam;
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static String execCMD(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    static void showAllVersionInfo() {

        ArrayList<String> showVersionList = new ArrayList<String>();

        for (int i = 0; i < aospList.size(); i++) {
            AOSP_Version_ITEM androidItem = aospList.get(i);
            String verionNum = androidItem.AndroidVersion + "";
            String nickChar = androidItem.Android_CharName + "";
            String apinum = androidItem.apiVersion + "";
            String releaseTime = androidItem.releaseTime;
            showVersionList.add("当前记录Android版本:" + verionNum + "  代号:" + nickChar + "   API等级:" + apinum + "   发布时间:" + releaseTime);
        }
        ArrayPrint(showVersionList, "已保存Android版本记录表");


        String countFileNumStr = F2_Properties.getProperty("downloadcount");
        if (countFileNumStr == null || "".equals(countFileNumStr) || "null".equals(countFileNumStr)) {
            downloadFileCount = 0;
        } else {
            downloadFileCount = Integer.parseInt(countFileNumStr);
        }
        int currentFileCount = downloadFileCount;
        System.out.println("================================保存文件列表================================");
        for (int i = 0; i < allFileList.size(); i++) {
            ArrayList<String> fileItemListLog = new ArrayList<String>();
            FilePath_ITEM fileItem = allFileList.get(i);
            fileItemListLog.add("AOSP路径:" + fileItem.mAOSP_Path);
            for (int j = 0; j < fileItem.allJavaFileList.size(); j++) {
                JavaFile_Item javaItem = fileItem.allJavaFileList.get(j);
                String androidVersion = javaItem.androidVersion + "";
                fileItemListLog.add(androidVersion + "file:" + javaItem.targetJavaFilePath);
                fileItemListLog.add("url1:" + javaItem.mdownloadUrl);
                fileItemListLog.add("url2:" + javaItem.mVisitedUrl);
                if(javaItem.targetJavaFile.exists()){
                    fileItemListLog.add("行数:" + getLineNum(javaItem.targetJavaFile));
                }else{
                    fileItemListLog.add("行数:" + "该版本【"+javaItem.androidVersion+"】下该文件不存在");
                }
                fileItemListLog.add("flag:" + " local-exist-flag:【" + javaItem.isfile_exist_local + "】" + " net-exist-flag:【" + javaItem.isfile_exit_innet + "】");
                fileItemListLog.add(":===============================================================================");
                if (!javaItem.isfile_exist_local && javaItem.isfile_exit_innet) {
                    System.out.println("首次无参数执行 将会下载对应文件并打印全局Log,请等待下载完成 正在下载:" + javaItem.targetJavaFile.getName() + "........");
                    if (downLoadWithJavaItemFile(javaItem.mdownloadUrl, javaItem)) {
                        currentFileCount++;
                    }
                }

            }
            ArrayPrint(fileItemListLog, fileItem.mfileName_common + "." + fileItem.mFileType + "文件信息");
        }

        F2_Properties.setProperty("downloadcount", "" + currentFileCount);


    }


    static ArrayList<String >getJavaFIleGroupInfoList(String filenamelower){
        ArrayList<String> fileItemListLog = new ArrayList<String>();
        for (int i = 0; i < allFileList.size(); i++) {

            FilePath_ITEM fileItem = allFileList.get(i);

            if(fileItem.mOriginalName.equals(filenamelower)){
                fileItemListLog.add("AOSP路径:" + fileItem.mAOSP_Path);
                for (int j = 0; j < fileItem.allJavaFileList.size(); j++) {
                    JavaFile_Item javaItem = fileItem.allJavaFileList.get(j);
                    String androidVersion = javaItem.androidVersion + "";
                    fileItemListLog.add(androidVersion + "file:" + javaItem.targetJavaFilePath);
                    fileItemListLog.add("url1:" + javaItem.mdownloadUrl);
                    fileItemListLog.add("url2:" + javaItem.mVisitedUrl);
                    if(javaItem.targetJavaFile.exists()){
                        fileItemListLog.add("行数:" + getLineNum(javaItem.targetJavaFile));
                    }else{
                        fileItemListLog.add("行数:" + "该版本【"+javaItem.androidVersion+"】下该文件不存在");
                    }
                    fileItemListLog.add("flag:" + " local-exist-flag:【" + javaItem.isfile_exist_local + "】" + " net-exist-flag:【" + javaItem.isfile_exit_innet + "】");
                    fileItemListLog.add("============================================================");
                }
            }
        }
        return fileItemListLog;
    }






    static ArrayList<FilePath_ITEM> allFileList = new ArrayList<FilePath_ITEM>();

    static {

        FilePath_ITEM  CivicLocation =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/CivicLocation.java");
        allFileList.add(CivicLocation);


        FilePath_ITEM  EncryptedData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/EncryptedData.java");
        allFileList.add(EncryptedData);


        FilePath_ITEM  WifiP2pConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pConfig.java");
        allFileList.add(WifiP2pConfig);


        FilePath_ITEM  ClientModeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ClientModeManager.java");
        allFileList.add(ClientModeManager);


        FilePath_ITEM  ApConfigUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/ApConfigUtil.java");
        allFileList.add(ApConfigUtil);


        FilePath_ITEM  WifiP2pServiceImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pServiceImpl.java");
        allFileList.add(WifiP2pServiceImpl);


        FilePath_ITEM  WifiDetailAutoJoinPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiDetailAutoJoinPreferenceController.java");
        allFileList.add(WifiDetailAutoJoinPreferenceController);


        FilePath_ITEM  VenueNameElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/VenueNameElement.java");
        allFileList.add(VenueNameElement);


        FilePath_ITEM  NAIRealmElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/NAIRealmElement.java");
        allFileList.add(NAIRealmElement);


        FilePath_ITEM  WifiDetailPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiDetailPreference.java");
        allFileList.add(WifiDetailPreference);


        FilePath_ITEM  WifiCandidates =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiCandidates.java");
        allFileList.add(WifiCandidates);


        FilePath_ITEM  RadioChainInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/RadioChainInfo.java");
        allFileList.add(RadioChainInfo);


        FilePath_ITEM  PublishConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/PublishConfig.java");
        allFileList.add(PublishConfig);


        FilePath_ITEM  WifiBackupDataV1Parser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiBackupDataV1Parser.java");
        allFileList.add(WifiBackupDataV1Parser);


        FilePath_ITEM  NetworkRequestStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkRequestStoreData.java");
        allFileList.add(NetworkRequestStoreData);


        FilePath_ITEM  WifiDppQrCodeScannerFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppQrCodeScannerFragment.java");
        allFileList.add(WifiDppQrCodeScannerFragment);


        FilePath_ITEM  ANQPNetworkKey =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/ANQPNetworkKey.java");
        allFileList.add(ANQPNetworkKey);


        FilePath_ITEM  MoSerializer =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/omadm/MoSerializer.java");
        allFileList.add(MoSerializer);


        FilePath_ITEM  ResponderConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/ResponderConfig.java");
        allFileList.add(ResponderConfig);


        FilePath_ITEM  DomainNameElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/DomainNameElement.java");
        allFileList.add(DomainNameElement);


        FilePath_ITEM  WifiMonitor =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiMonitor.java");
        allFileList.add(WifiMonitor);


        FilePath_ITEM  ANQPRequestManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/ANQPRequestManager.java");
        allFileList.add(ANQPRequestManager);


        FilePath_ITEM  RangingResultCallback =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/RangingResultCallback.java");
        allFileList.add(RangingResultCallback);


        FilePath_ITEM  WifiAwareNativeApi =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareNativeApi.java");
        allFileList.add(WifiAwareNativeApi);


        FilePath_ITEM  WifiQrCode =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiQrCode.java");
        allFileList.add(WifiQrCode);


        FilePath_ITEM  ConnectToNetworkNotificationBuilder =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ConnectToNetworkNotificationBuilder.java");
        allFileList.add(ConnectToNetworkNotificationBuilder);


        FilePath_ITEM  WifiNetworkFactory =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNetworkFactory.java");
        allFileList.add(WifiNetworkFactory);


        FilePath_ITEM  EasyConnectStatusCallback =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/EasyConnectStatusCallback.java");
        allFileList.add(EasyConnectStatusCallback);


        FilePath_ITEM  WifiTrackerFactory =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiTrackerFactory.java");
        allFileList.add(WifiTrackerFactory);


        FilePath_ITEM  Credential =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/pps/Credential.java");
        allFileList.add(Credential);


        FilePath_ITEM  LinkProbeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/LinkProbeManager.java");
        allFileList.add(LinkProbeManager);


        FilePath_ITEM  PasspointMatch =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointMatch.java");
        allFileList.add(PasspointMatch);


        FilePath_ITEM  TestAccessPointBuilder =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/TestAccessPointBuilder.java");
        allFileList.add(TestAccessPointBuilder);


        FilePath_ITEM  RedirectListener =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/RedirectListener.java");
        allFileList.add(RedirectListener);


        FilePath_ITEM  WifiP2pManager =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pManager.java");
        allFileList.add(WifiP2pManager);


        FilePath_ITEM  SoftApModeConfiguration =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SoftApModeConfiguration.java");
        allFileList.add(SoftApModeConfiguration);


        FilePath_ITEM  RttMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttMetrics.java");
        allFileList.add(RttMetrics);


        FilePath_ITEM  NativeUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/NativeUtil.java");
        allFileList.add(NativeUtil);


        FilePath_ITEM  WifiConnectivityHelper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConnectivityHelper.java");
        allFileList.add(WifiConnectivityHelper);


        FilePath_ITEM  WifiTetherBasePreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherBasePreferenceController.java");
        allFileList.add(WifiTetherBasePreferenceController);


        FilePath_ITEM  Constants =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/Constants.java");
        allFileList.add(Constants);


        FilePath_ITEM  Matrix =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/Matrix.java");
        allFileList.add(Matrix);


        FilePath_ITEM  WifiP2pProvDiscEvent =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pProvDiscEvent.java");
        allFileList.add(WifiP2pProvDiscEvent);


        FilePath_ITEM  CredentialType =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/CredentialType.java");
        allFileList.add(CredentialType);


        FilePath_ITEM  ScanDetail =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanDetail.java");
        allFileList.add(ScanDetail);


        FilePath_ITEM  WifiConfigStoreLegacy =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigStoreLegacy.java");
        allFileList.add(WifiConfigStoreLegacy);


        FilePath_ITEM  AnqpEvent =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/AnqpEvent.java");
        allFileList.add(AnqpEvent);


        FilePath_ITEM  ActiveModeWarden =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ActiveModeWarden.java");
        allFileList.add(ActiveModeWarden);


        FilePath_ITEM  RttServiceImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttServiceImpl.java");
        allFileList.add(RttServiceImpl);


        FilePath_ITEM  WifiDppChooseSavedWifiNetworkFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppChooseSavedWifiNetworkFragment.java");
        allFileList.add(WifiDppChooseSavedWifiNetworkFragment);


        FilePath_ITEM  WifiNetworkHistory =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNetworkHistory.java");
        allFileList.add(WifiNetworkHistory);


        FilePath_ITEM  SavedAccessPointsPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/savedaccesspoints/SavedAccessPointsPreferenceController.java");
        allFileList.add(SavedAccessPointsPreferenceController);


        FilePath_ITEM  NetworkListStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkListStoreData.java");
        allFileList.add(NetworkListStoreData);


        FilePath_ITEM  NativeScanResult =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/NativeScanResult.java");
        allFileList.add(NativeScanResult);


        FilePath_ITEM  WifiScoreReport =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiScoreReport.java");
        allFileList.add(WifiScoreReport);


        FilePath_ITEM  MetricsUtils =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/MetricsUtils.java");
        allFileList.add(MetricsUtils);


        FilePath_ITEM  GeneralUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/GeneralUtil.java");
        allFileList.add(GeneralUtil);


        FilePath_ITEM  WifiAwareNativeCallback =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareNativeCallback.java");
        allFileList.add(WifiAwareNativeCallback);


        FilePath_ITEM  NetworkListSharedStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkListSharedStoreData.java");
        allFileList.add(NetworkListSharedStoreData);


        FilePath_ITEM  WakeupController =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupController.java");
        allFileList.add(WakeupController);


        FilePath_ITEM  LogcatLog =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/LogcatLog.java");
        allFileList.add(LogcatLog);


        FilePath_ITEM  VendorSpecificAuth =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/VendorSpecificAuth.java");
        allFileList.add(VendorSpecificAuth);


        FilePath_ITEM  RttNative =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttNative.java");
        allFileList.add(RttNative);


        FilePath_ITEM  WifiTetherSoftApManager =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSoftApManager.java");
        allFileList.add(WifiTetherSoftApManager);


        FilePath_ITEM  WifiCountryCode =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiCountryCode.java");
        allFileList.add(WifiCountryCode);


        FilePath_ITEM  WifiDppAddDeviceFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppAddDeviceFragment.java");
        allFileList.add(WifiDppAddDeviceFragment);


        FilePath_ITEM  HSOsuProvidersElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/HSOsuProvidersElement.java");
        allFileList.add(HSOsuProvidersElement);


        FilePath_ITEM  ScoredNetworkEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScoredNetworkEvaluator.java");
        allFileList.add(ScoredNetworkEvaluator);


        FilePath_ITEM  WifiNetworkSuggestion =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiNetworkSuggestion.java");
        allFileList.add(WifiNetworkSuggestion);


        FilePath_ITEM  HotspotOffReceiver =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/HotspotOffReceiver.java");
        allFileList.add(HotspotOffReceiver);


        FilePath_ITEM  SarManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SarManager.java");
        allFileList.add(SarManager);


        FilePath_ITEM  OffloadWifiApSelector =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/OffloadWifiApSelector.java");
        allFileList.add(OffloadWifiApSelector);


        FilePath_ITEM  TlvBufferUtils =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/TlvBufferUtils.java");
        allFileList.add(TlvBufferUtils);


        FilePath_ITEM  GenericBlobElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/GenericBlobElement.java");
        allFileList.add(GenericBlobElement);


        FilePath_ITEM  InnerAuthEAP =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/InnerAuthEAP.java");
        allFileList.add(InnerAuthEAP);


        FilePath_ITEM  WifiStatusTracker =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiStatusTracker.java");
        allFileList.add(WifiStatusTracker);


        FilePath_ITEM  WifiLinkLayerStats =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLinkLayerStats.java");
        allFileList.add(WifiLinkLayerStats);


        FilePath_ITEM  WifiConfigController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java");
        allFileList.add(WifiConfigController);


        FilePath_ITEM  TimestampedScoredNetwork =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/TimestampedScoredNetwork.java");
        allFileList.add(TimestampedScoredNetwork);


        FilePath_ITEM  WifiInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiInfo.java");
        allFileList.add(WifiInfo);


        FilePath_ITEM  OsuProvider =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/OsuProvider.java");
        allFileList.add(OsuProvider);


        FilePath_ITEM  WifiPrivacyPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiPrivacyPreferenceController.java");
        allFileList.add(WifiPrivacyPreferenceController);


        FilePath_ITEM  WificondScannerImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WificondScannerImpl.java");
        allFileList.add(WificondScannerImpl);


        FilePath_ITEM  OsuNetworkConnection =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/OsuNetworkConnection.java");
        allFileList.add(OsuNetworkConnection);


        FilePath_ITEM  ScanResult =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/ScanResult.java");
        allFileList.add(ScanResult);


        FilePath_ITEM  ProvisioningCallback =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/ProvisioningCallback.java");
        allFileList.add(ProvisioningCallback);


        FilePath_ITEM  SupplicantStateTracker =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantStateTracker.java");
        allFileList.add(SupplicantStateTracker);


        FilePath_ITEM  AccessPoint =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java");
        allFileList.add(AccessPoint);


        FilePath_ITEM  ConfigurationMap =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ConfigurationMap.java");
        allFileList.add(ConfigurationMap);


        FilePath_ITEM  ExtendedWifiInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ExtendedWifiInfo.java");
        allFileList.add(ExtendedWifiInfo);


        FilePath_ITEM  HSFriendlyNameElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/HSFriendlyNameElement.java");
        allFileList.add(HSFriendlyNameElement);


        FilePath_ITEM  IMSIParameter =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/IMSIParameter.java");
        allFileList.add(IMSIParameter);


        FilePath_ITEM  PasspointManagementObjectDefinition =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/PasspointManagementObjectDefinition.java");
        allFileList.add(PasspointManagementObjectDefinition);


        FilePath_ITEM  WifiSummaryUpdater =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiSummaryUpdater.java");
        allFileList.add(WifiSummaryUpdater);





        FilePath_ITEM  CellularLinkLayerStats =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CellularLinkLayerStats.java");
        allFileList.add(CellularLinkLayerStats);


        FilePath_ITEM  WifiSlice =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/slice/WifiSlice.java");
        allFileList.add(WifiSlice);


        FilePath_ITEM  BuildProperties =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/BuildProperties.java");
        allFileList.add(BuildProperties);


        FilePath_ITEM  WakeupOnboarding =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupOnboarding.java");
        allFileList.add(WakeupOnboarding);


        FilePath_ITEM  SupplicantMotP2pIfaceCallback =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/SupplicantMotP2pIfaceCallback.java");
        allFileList.add(SupplicantMotP2pIfaceCallback);


        FilePath_ITEM  WifiSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java");
        allFileList.add(WifiSettings);


        FilePath_ITEM  RssiPacketCountInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/RssiPacketCountInfo.java");
        allFileList.add(RssiPacketCountInfo);


        FilePath_ITEM  BackupManagerProxy =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/BackupManagerProxy.java");
        allFileList.add(BackupManagerProxy);


        FilePath_ITEM  WifiScanningService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WifiScanningService.java");
        allFileList.add(WifiScanningService);


        FilePath_ITEM  IntCounter =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/IntCounter.java");
        allFileList.add(IntCounter);





        FilePath_ITEM  InformationElementUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/InformationElementUtil.java");
        allFileList.add(InformationElementUtil);


        FilePath_ITEM  RequestToggleWiFiActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/RequestToggleWiFiActivity.java");
        allFileList.add(RequestToggleWiFiActivity);


        FilePath_ITEM  HalDeviceManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/HalDeviceManager.java");
        allFileList.add(HalDeviceManager);


        FilePath_ITEM  SavedNetworkEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SavedNetworkEvaluator.java");
        allFileList.add(SavedNetworkEvaluator);


        FilePath_ITEM  WnmData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/WnmData.java");
        allFileList.add(WnmData);


        FilePath_ITEM  Utils =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/Utils.java");
        allFileList.add(Utils);


        FilePath_ITEM  WifiDppEnrolleeActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppEnrolleeActivity.java");
        allFileList.add(WifiDppEnrolleeActivity);


        FilePath_ITEM  BroadcastSsidInvisibleDialogFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/BroadcastSsidInvisibleDialogFragment.java");
        allFileList.add(BroadcastSsidInvisibleDialogFragment);


        FilePath_ITEM  WifiCallingSettingsForSub =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/WifiCallingSettingsForSub.java");
        allFileList.add(WifiCallingSettingsForSub);


        FilePath_ITEM  WifiDiagnostics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiDiagnostics.java");
        allFileList.add(WifiDiagnostics);


        FilePath_ITEM  Clock =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/Clock.java");
        allFileList.add(Clock);


        FilePath_ITEM  NetworkRequestDialogActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/NetworkRequestDialogActivity.java");
        allFileList.add(NetworkRequestDialogActivity);


        FilePath_ITEM  WfaCertBuilder =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/WfaCertBuilder.java");
        allFileList.add(WfaCertBuilder);


        FilePath_ITEM  WifiScanWorker =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/slice/WifiScanWorker.java");
        allFileList.add(WifiScanWorker);


        FilePath_ITEM  WakeupEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupEvaluator.java");
        allFileList.add(WakeupEvaluator);


        FilePath_ITEM  WifiMulticastLockManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiMulticastLockManager.java");
        allFileList.add(WifiMulticastLockManager);


        FilePath_ITEM  WifiStateMachine =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java");
        allFileList.add(WifiStateMachine);


        FilePath_ITEM  WifiDeviceNameTextValidator =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiDeviceNameTextValidator.java");
        allFileList.add(WifiDeviceNameTextValidator);


        FilePath_ITEM  SavedAccessPointsWifiSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/savedaccesspoints/SavedAccessPointsWifiSettings.java");
        allFileList.add(SavedAccessPointsWifiSettings);


        FilePath_ITEM  WifiDppUtils =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppUtils.java");
        allFileList.add(WifiDppUtils);


        FilePath_ITEM  StringUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/StringUtil.java");
        allFileList.add(StringUtil);


        FilePath_ITEM  BitMask =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/BitMask.java");
        allFileList.add(BitMask);


        FilePath_ITEM  QrCamera =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/qrcode/QrCamera.java");
        allFileList.add(QrCamera);


        FilePath_ITEM  WifiEnterpriseConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiEnterpriseConfig.java");
        allFileList.add(WifiEnterpriseConfig);


        FilePath_ITEM  WifiWakeReasonAndCounts =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiWakeReasonAndCounts.java");
        allFileList.add(WifiWakeReasonAndCounts);


        FilePath_ITEM  WifiP2pPersistentGroup =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pPersistentGroup.java");
        allFileList.add(WifiP2pPersistentGroup);


        FilePath_ITEM  TelephonyUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/TelephonyUtil.java");
        allFileList.add(TelephonyUtil);


        FilePath_ITEM  WifiApConfigStore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiApConfigStore.java");
        allFileList.add(WifiApConfigStore);


        FilePath_ITEM  WifiConnectListener =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiConnectListener.java");
        allFileList.add(WifiConnectListener);


        FilePath_ITEM  SupplicantStaNetworkHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantStaNetworkHal.java");
        allFileList.add(SupplicantStaNetworkHal);


        FilePath_ITEM  PeerHandle =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/PeerHandle.java");
        allFileList.add(PeerHandle);


        FilePath_ITEM  WifiUsabilityStatsEntry =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiUsabilityStatsEntry.java");
        allFileList.add(WifiUsabilityStatsEntry);


        FilePath_ITEM  SppCommand =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/command/SppCommand.java");
        allFileList.add(SppCommand);


        FilePath_ITEM  NetworkListUserStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkListUserStoreData.java");
        allFileList.add(NetworkListUserStoreData);


        FilePath_ITEM  ByteBufferReader =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ByteBufferReader.java");
        allFileList.add(ByteBufferReader);


        FilePath_ITEM  WifiScannerImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WifiScannerImpl.java");
        allFileList.add(WifiScannerImpl);


        FilePath_ITEM  RangingRequest =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/RangingRequest.java");
        allFileList.add(RangingRequest);


        FilePath_ITEM  IdentityChangedListener =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/IdentityChangedListener.java");
        allFileList.add(IdentityChangedListener);


        FilePath_ITEM  WifiLoggerHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLoggerHal.java");
        allFileList.add(WifiLoggerHal);


        FilePath_ITEM  SupplicantState =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/SupplicantState.java");
        allFileList.add(SupplicantState);


        FilePath_ITEM  WpsP2pDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WpsP2pDialog.java");
        allFileList.add(WpsP2pDialog);


        FilePath_ITEM  ExternalCallbackTracker =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/ExternalCallbackTracker.java");
        allFileList.add(ExternalCallbackTracker);


        FilePath_ITEM  WifiAwareService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareService.java");
        allFileList.add(WifiAwareService);


        FilePath_ITEM  WifiP2pWfdInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pWfdInfo.java");
        allFileList.add(WifiP2pWfdInfo);


        FilePath_ITEM  OpenSecurityDialogFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/OpenSecurityDialogFragment.java");
        allFileList.add(OpenSecurityDialogFragment);


        FilePath_ITEM  WifiP2pNative =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pNative.java");
        allFileList.add(WifiP2pNative);


        FilePath_ITEM  Capabilities =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/Capabilities.java");
        allFileList.add(Capabilities);


        FilePath_ITEM  WifiAsyncChannel =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/WifiAsyncChannel.java");
        allFileList.add(WifiAsyncChannel);


        FilePath_ITEM  WifiP2pGroupList =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pGroupList.java");
        allFileList.add(WifiP2pGroupList);


        FilePath_ITEM  AuthParam =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/AuthParam.java");
        allFileList.add(AuthParam);


        FilePath_ITEM  PresetKnownBandsChannelHelper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/PresetKnownBandsChannelHelper.java");
        allFileList.add(PresetKnownBandsChannelHelper);


        FilePath_ITEM  WifiBackupRestore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiBackupRestore.java");
        allFileList.add(WifiBackupRestore);


        FilePath_ITEM  PasspointNetworkScore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointNetworkScore.java");
        allFileList.add(PasspointNetworkScore);


        FilePath_ITEM  SupplicantP2pIfaceHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/SupplicantP2pIfaceHal.java");
        allFileList.add(SupplicantP2pIfaceHal);


        FilePath_ITEM  IconEvent =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/IconEvent.java");
        allFileList.add(IconEvent);


        FilePath_ITEM  ExchangeCompleteMessage =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/ExchangeCompleteMessage.java");
        allFileList.add(ExchangeCompleteMessage);


        FilePath_ITEM  WifiSliceBuilder =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiSliceBuilder.java");
        allFileList.add(WifiSliceBuilder);


        FilePath_ITEM  WifiPickerActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiPickerActivity.java");
        allFileList.add(WifiPickerActivity);


        FilePath_ITEM  NetworkSuggestionEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkSuggestionEvaluator.java");
        allFileList.add(NetworkSuggestionEvaluator);


        FilePath_ITEM  WifiTracker =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiTracker.java");
        allFileList.add(WifiTracker);


        FilePath_ITEM  HttpsTransport =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/HttpsTransport.java");
        allFileList.add(HttpsTransport);


        FilePath_ITEM  ConnectToWifiHandler =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/slice/ConnectToWifiHandler.java");
        allFileList.add(ConnectToWifiHandler);


        FilePath_ITEM  LinkablePreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/LinkablePreference.java");
        allFileList.add(LinkablePreference);


        FilePath_ITEM  WifiPermissionsWrapper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/WifiPermissionsWrapper.java");
        allFileList.add(WifiPermissionsWrapper);


        FilePath_ITEM  BackgroundScanScheduler =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/BackgroundScanScheduler.java");
        allFileList.add(BackgroundScanScheduler);


        FilePath_ITEM  WifiP2pUpnpServiceRequest =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pUpnpServiceRequest.java");
        allFileList.add(WifiP2pUpnpServiceRequest);


        FilePath_ITEM  SubscribeDiscoverySession =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/SubscribeDiscoverySession.java");
        allFileList.add(SubscribeDiscoverySession);


        FilePath_ITEM  LegacyPasspointConfig =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/LegacyPasspointConfig.java");
        allFileList.add(LegacyPasspointConfig);


        FilePath_ITEM  WifiNative =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNative.java");
        allFileList.add(WifiNative);


        FilePath_ITEM  EmergencyCallLimitationDisclaimer =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/EmergencyCallLimitationDisclaimer.java");
        allFileList.add(EmergencyCallLimitationDisclaimer);


        FilePath_ITEM  SelfRecovery =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SelfRecovery.java");
        allFileList.add(SelfRecovery);


        FilePath_ITEM  WifiActivityEnergyInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiActivityEnergyInfo.java");
        allFileList.add(WifiActivityEnergyInfo);


        FilePath_ITEM  WifiSavedConfigUtils =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiSavedConfigUtils.java");
        allFileList.add(WifiSavedConfigUtils);


        FilePath_ITEM  WifiDataStall =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiDataStall.java");
        allFileList.add(WifiDataStall);


        FilePath_ITEM  P2pPersistentCategoryPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/P2pPersistentCategoryPreferenceController.java");
        allFileList.add(P2pPersistentCategoryPreferenceController);


        FilePath_ITEM  WifiTetherDHCPPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherDHCPPreferenceController.java");
        allFileList.add(WifiTetherDHCPPreferenceController);


        FilePath_ITEM  CivicLocationKeys =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/CivicLocationKeys.java");
        allFileList.add(CivicLocationKeys);


        FilePath_ITEM  WifiEnabler =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java");
        allFileList.add(WifiEnabler);


        FilePath_ITEM  AttachCallback =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/AttachCallback.java");
        allFileList.add(AttachCallback);


        FilePath_ITEM  WifiP2pUpnpServiceInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pUpnpServiceInfo.java");
        allFileList.add(WifiP2pUpnpServiceInfo);


        FilePath_ITEM  AnqpCache =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/AnqpCache.java");
        allFileList.add(AnqpCache);


        FilePath_ITEM  WifiConfiguration =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiConfiguration.java");
        allFileList.add(WifiConfiguration);


        FilePath_ITEM  WifiMasterSwitchPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiMasterSwitchPreferenceController.java");
        allFileList.add(WifiMasterSwitchPreferenceController);


        FilePath_ITEM  WpsPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WpsPreferenceController.java");
        allFileList.add(WpsPreferenceController);


        FilePath_ITEM  WpsInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WpsInfo.java");
        allFileList.add(WpsInfo);


        FilePath_ITEM  BubbleFunScorer =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/BubbleFunScorer.java");
        allFileList.add(BubbleFunScorer);


        FilePath_ITEM  WifiSettingsStore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiSettingsStore.java");
        allFileList.add(WifiSettingsStore);


        FilePath_ITEM  AddWifiNetworkPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/AddWifiNetworkPreference.java");
        allFileList.add(AddWifiNetworkPreference);


        FilePath_ITEM  WifiSsid =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiSsid.java");
        allFileList.add(WifiSsid);


        FilePath_ITEM  RttService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttService.java");
        allFileList.add(RttService);


        FilePath_ITEM  WrongPasswordNotifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WrongPasswordNotifier.java");
        allFileList.add(WrongPasswordNotifier);


        FilePath_ITEM  WifiTetherApBandPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherApBandPreferenceController.java");
        allFileList.add(WifiTetherApBandPreferenceController);


        FilePath_ITEM  StateChangeResult =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/StateChangeResult.java");
        allFileList.add(StateChangeResult);


        FilePath_ITEM  CarrierNetworkConfig =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CarrierNetworkConfig.java");
        allFileList.add(CarrierNetworkConfig);


        FilePath_ITEM  IntHistogram =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/IntHistogram.java");
        allFileList.add(IntHistogram);


        FilePath_ITEM  WifiVendorHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiVendorHal.java");
        allFileList.add(WifiVendorHal);


        FilePath_ITEM  DummyLogMessage =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/DummyLogMessage.java");
        allFileList.add(DummyLogMessage);


        FilePath_ITEM  WifiP2pSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pSettings.java");
        allFileList.add(WifiP2pSettings);


        FilePath_ITEM  WriteWifiConfigToNfcDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WriteWifiConfigToNfcDialog.java");
        allFileList.add(WriteWifiConfigToNfcDialog);


        FilePath_ITEM  DevInfoMo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/omadm/DevInfoMo.java");
        allFileList.add(DevInfoMo);


        FilePath_ITEM  WifiTetherSwitchBarController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSwitchBarController.java");
        allFileList.add(WifiTetherSwitchBarController);


        FilePath_ITEM  SppResponseMessage =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/SppResponseMessage.java");
        allFileList.add(SppResponseMessage);


        FilePath_ITEM  WifiNetworkAgentSpecifier =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiNetworkAgentSpecifier.java");
        allFileList.add(WifiNetworkAgentSpecifier);


        FilePath_ITEM  ScoreCardBasedScorer =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScoreCardBasedScorer.java");
        allFileList.add(ScoreCardBasedScorer);


        FilePath_ITEM  WifiP2pPeer =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pPeer.java");
        allFileList.add(WifiP2pPeer);


        FilePath_ITEM  PublishDiscoverySession =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/PublishDiscoverySession.java");
        allFileList.add(PublishDiscoverySession);


        FilePath_ITEM  WifiAwareDataPathStateManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareDataPathStateManager.java");
        allFileList.add(WifiAwareDataPathStateManager);


        FilePath_ITEM  WifiWakeupPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiWakeupPreferenceController.java");
        allFileList.add(WifiWakeupPreferenceController);


        FilePath_ITEM  WifiDppInitiatorViewModel =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppInitiatorViewModel.java");
        allFileList.add(WifiDppInitiatorViewModel);


        FilePath_ITEM  WifiNetworkSuggestionsManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNetworkSuggestionsManager.java");
        allFileList.add(WifiNetworkSuggestionsManager);


        FilePath_ITEM  SystemBuildProperties =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SystemBuildProperties.java");
        allFileList.add(SystemBuildProperties);


        FilePath_ITEM  WifiP2pDevice =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pDevice.java");
        allFileList.add(WifiP2pDevice);


        FilePath_ITEM  WifiNetworkSelector =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNetworkSelector.java");
        allFileList.add(WifiNetworkSelector);


        FilePath_ITEM  WifiUtils =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiUtils.java");
        allFileList.add(WifiUtils);


        FilePath_ITEM  WifiConnectivityManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConnectivityManager.java");
        allFileList.add(WifiConnectivityManager);


        FilePath_ITEM  WifiAwareMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareMetrics.java");
        allFileList.add(WifiAwareMetrics);


        FilePath_ITEM  UntrustedWifiNetworkFactory =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/UntrustedWifiNetworkFactory.java");
        allFileList.add(UntrustedWifiNetworkFactory);


        FilePath_ITEM  ObjectCounter =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/ObjectCounter.java");
        allFileList.add(ObjectCounter);


        FilePath_ITEM  PasspointConfiguration =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/PasspointConfiguration.java");
        allFileList.add(PasspointConfiguration);


        FilePath_ITEM  UseOpenWifiPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/UseOpenWifiPreferenceController.java");
        allFileList.add(UseOpenWifiPreferenceController);


        FilePath_ITEM  WifiDetailPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiDetailPreferenceController.java");
        allFileList.add(WifiDetailPreferenceController);


        FilePath_ITEM  WifiDppQrCodeGeneratorFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppQrCodeGeneratorFragment.java");
        allFileList.add(WifiDppQrCodeGeneratorFragment);


        FilePath_ITEM  PasspointConfigUserStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointConfigUserStoreData.java");
        allFileList.add(PasspointConfigUserStoreData);


        FilePath_ITEM  WifiStateTracker =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateTracker.java");
        allFileList.add(WifiStateTracker);


        FilePath_ITEM  WifiTetherAutoOffPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherAutoOffPreferenceController.java");
        allFileList.add(WifiTetherAutoOffPreferenceController);


        FilePath_ITEM  WifiP2pCreateGroupDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pCreateGroupDialog.java");
        allFileList.add(WifiP2pCreateGroupDialog);


        FilePath_ITEM  P2pPeerCategoryPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/P2pPeerCategoryPreferenceController.java");
        allFileList.add(P2pPeerCategoryPreferenceController);


        FilePath_ITEM  WifiScanningServiceImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WifiScanningServiceImpl.java");
        allFileList.add(WifiScanningServiceImpl);


        FilePath_ITEM  WakeupLock =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupLock.java");
        allFileList.add(WakeupLock);


        FilePath_ITEM  WifiDppQrCodeBaseFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppQrCodeBaseFragment.java");
        allFileList.add(WifiDppQrCodeBaseFragment);


        FilePath_ITEM  WifiAwareAgentNetworkSpecifier =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareAgentNetworkSpecifier.java");
        allFileList.add(WifiAwareAgentNetworkSpecifier);


        FilePath_ITEM  WifiTetherHiddenSSIDPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherHiddenSSIDPreferenceController.java");
        allFileList.add(WifiTetherHiddenSSIDPreferenceController);


        FilePath_ITEM  SystemPropertyService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SystemPropertyService.java");
        allFileList.add(SystemPropertyService);


        FilePath_ITEM  CertificateVerifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/CertificateVerifier.java");
        allFileList.add(CertificateVerifier);


        FilePath_ITEM  WifiAwareNativeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareNativeManager.java");
        allFileList.add(WifiAwareNativeManager);


        FilePath_ITEM  IconInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/IconInfo.java");
        allFileList.add(IconInfo);


        FilePath_ITEM  HSConnectionCapabilityElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/HSConnectionCapabilityElement.java");
        allFileList.add(HSConnectionCapabilityElement);


        FilePath_ITEM  WifiShellCommand =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiShellCommand.java");
        allFileList.add(WifiShellCommand);


        FilePath_ITEM  HSIconFileElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/HSIconFileElement.java");
        allFileList.add(HSIconFileElement);


        FilePath_ITEM  P2pCategoryPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/P2pCategoryPreferenceController.java");
        allFileList.add(P2pCategoryPreferenceController);


        FilePath_ITEM  DevDetailMo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/omadm/DevDetailMo.java");
        allFileList.add(DevDetailMo);


        FilePath_ITEM  QrYuvLuminanceSource =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/qrcode/QrYuvLuminanceSource.java");
        allFileList.add(QrYuvLuminanceSource);


        FilePath_ITEM  WifiNetworkConnectionStatistics =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiNetworkConnectionStatistics.java");
        allFileList.add(WifiNetworkConnectionStatistics);


        FilePath_ITEM  WifiP2pInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pInfo.java");
        allFileList.add(WifiP2pInfo);


        FilePath_ITEM  SubscribeConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/SubscribeConfig.java");
        allFileList.add(SubscribeConfig);


        FilePath_ITEM  WificondChannelHelper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WificondChannelHelper.java");
        allFileList.add(WificondChannelHelper);


        FilePath_ITEM  AnqpInformationElement =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/AnqpInformationElement.java");
        allFileList.add(AnqpInformationElement);


        FilePath_ITEM  NotifyOpenNetworksPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/NotifyOpenNetworksPreferenceController.java");
        allFileList.add(NotifyOpenNetworksPreferenceController);


        FilePath_ITEM  WifiStateMachinePrime =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachinePrime.java");
        allFileList.add(WifiStateMachinePrime);


        FilePath_ITEM  ANQPData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/ANQPData.java");
        allFileList.add(ANQPData);


        FilePath_ITEM  ScanResultUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/ScanResultUtil.java");
        allFileList.add(ScanResultUtil);


        FilePath_ITEM  WifiCountryMonitorFcc =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiCountryMonitorFcc.java");
        allFileList.add(WifiCountryMonitorFcc);


        FilePath_ITEM  ClientModeImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ClientModeImpl.java");
        allFileList.add(ClientModeImpl);


        FilePath_ITEM  ConfigRequest =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/ConfigRequest.java");
        allFileList.add(ConfigRequest);


        FilePath_ITEM  BaseWifiDiagnostics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/BaseWifiDiagnostics.java");
        allFileList.add(BaseWifiDiagnostics);


        FilePath_ITEM  WifiNetworkScoreCache =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiNetworkScoreCache.java");
        allFileList.add(WifiNetworkScoreCache);


        FilePath_ITEM  WifiManager =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiManager.java");
        allFileList.add(WifiManager);


        FilePath_ITEM  WifiP2pService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pService.java");
        allFileList.add(WifiP2pService);


        FilePath_ITEM  WifiMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiMetrics.java");
        allFileList.add(WifiMetrics);


        FilePath_ITEM  WifiService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiService.java");
        allFileList.add(WifiService);


        FilePath_ITEM  WifiConfigInfo =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigInfo.java");
        allFileList.add(WifiConfigInfo);


        FilePath_ITEM  ContextualWifiSlice =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/slice/ContextualWifiSlice.java");
        allFileList.add(ContextualWifiSlice);


        FilePath_ITEM  PropertyService =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/PropertyService.java");
        allFileList.add(PropertyService);


        FilePath_ITEM  WifiP2pMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pMetrics.java");
        allFileList.add(WifiP2pMetrics);


        FilePath_ITEM  ExpandedEAPMethod =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/ExpandedEAPMethod.java");
        allFileList.add(ExpandedEAPMethod);


        FilePath_ITEM  WifiCallingSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/WifiCallingSettings.java");
        allFileList.add(WifiCallingSettings);


        FilePath_ITEM  ByteArrayRingBuffer =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/ByteArrayRingBuffer.java");
        allFileList.add(ByteArrayRingBuffer);


        FilePath_ITEM  P2pThisDevicePreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/P2pThisDevicePreferenceController.java");
        allFileList.add(P2pThisDevicePreferenceController);


        FilePath_ITEM  WifiDialogActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiDialogActivity.java");
        allFileList.add(WifiDialogActivity);


        FilePath_ITEM  WifiP2pServiceResponse =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pServiceResponse.java");
        allFileList.add(WifiP2pServiceResponse);


        FilePath_ITEM  UpdateParameter =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/pps/UpdateParameter.java");
        allFileList.add(UpdateParameter);


        FilePath_ITEM  HalWifiScannerImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/HalWifiScannerImpl.java");
        allFileList.add(HalWifiScannerImpl);


        FilePath_ITEM  SupplicantMotStaNetworkHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantMotStaNetworkHal.java");
        allFileList.add(SupplicantMotStaNetworkHal);


        FilePath_ITEM  SIMAccessor =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SIMAccessor.java");
        allFileList.add(SIMAccessor);


        FilePath_ITEM  FakeWifiLog =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/FakeWifiLog.java");
        allFileList.add(FakeWifiLog);


        FilePath_ITEM  PostDevDataMessage =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/PostDevDataMessage.java");
        allFileList.add(PostDevDataMessage);


        FilePath_ITEM  NetworkDetail =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/NetworkDetail.java");
        allFileList.add(NetworkDetail);


        FilePath_ITEM  DppMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/DppMetrics.java");
        allFileList.add(DppMetrics);


        FilePath_ITEM  HttpsServiceConnection =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/HttpsServiceConnection.java");
        allFileList.add(HttpsServiceConnection);


        FilePath_ITEM  RawByteElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/RawByteElement.java");
        allFileList.add(RawByteElement);


        FilePath_ITEM  ContextualWifiScanWorker =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/slice/ContextualWifiScanWorker.java");
        allFileList.add(ContextualWifiScanWorker);


        FilePath_ITEM  ANQPParser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/ANQPParser.java");
        allFileList.add(ANQPParser);


        FilePath_ITEM  WifiP2pGroup =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pGroup.java");
        allFileList.add(WifiP2pGroup);


        FilePath_ITEM  WifiServiceStub =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceStub.java");
        allFileList.add(WifiServiceStub);


        FilePath_ITEM  WifiAwareNetworkSpecifier =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareNetworkSpecifier.java");
        allFileList.add(WifiAwareNetworkSpecifier);


        FilePath_ITEM  BatchedScanResult =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/BatchedScanResult.java");
        allFileList.add(BatchedScanResult);


        FilePath_ITEM  WifiInfoPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiInfoPreferenceController.java");
        allFileList.add(WifiInfoPreferenceController);


        FilePath_ITEM  QrCodeGenerator =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/qrcode/QrCodeGenerator.java");
        allFileList.add(QrCodeGenerator);


        FilePath_ITEM  NetworkRequestErrorDialogFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/NetworkRequestErrorDialogFragment.java");
        allFileList.add(NetworkRequestErrorDialogFragment);


        FilePath_ITEM  ScanRequestProxy =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanRequestProxy.java");
        allFileList.add(ScanRequestProxy);


        FilePath_ITEM  WifiP2pDeviceConfig =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pDeviceConfig.java");
        allFileList.add(WifiP2pDeviceConfig);


        FilePath_ITEM  WifiAwareUtils =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareUtils.java");
        allFileList.add(WifiAwareUtils);


        FilePath_ITEM  EAPConstants =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/EAPConstants.java");
        allFileList.add(EAPConstants);


        FilePath_ITEM  WifiStatusTest =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiStatusTest.java");
        allFileList.add(WifiStatusTest);


        FilePath_ITEM  SoftApManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SoftApManager.java");
        allFileList.add(SoftApManager);


        FilePath_ITEM  SarInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SarInfo.java");
        allFileList.add(SarInfo);


        FilePath_ITEM  ConfigureWifiSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/ConfigureWifiSettings.java");
        allFileList.add(ConfigureWifiSettings);


        FilePath_ITEM  WifiAwareShellCommand =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareShellCommand.java");
        allFileList.add(WifiAwareShellCommand);


        FilePath_ITEM  PasspointNetworkEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointNetworkEvaluator.java");
        allFileList.add(PasspointNetworkEvaluator);


        FilePath_ITEM  ResponderLocation =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/ResponderLocation.java");
        allFileList.add(ResponderLocation);


        FilePath_ITEM  WifiAwareManager =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareManager.java");
        allFileList.add(WifiAwareManager);


        FilePath_ITEM  WifiNetworkConfig =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiNetworkConfig.java");
        allFileList.add(WifiNetworkConfig);


        FilePath_ITEM  WifiP2pServiceRequest =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pServiceRequest.java");
        allFileList.add(WifiP2pServiceRequest);


        FilePath_ITEM  WifiAwareStateManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareStateManager.java");
        allFileList.add(WifiAwareStateManager);


        FilePath_ITEM  PpsMoData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/command/PpsMoData.java");
        allFileList.add(PpsMoData);


        FilePath_ITEM  BaseWifiService =  new FilePath_ITEM("/frameworks/base/wifi/java/com/android/server/wifi/BaseWifiService.java");
        allFileList.add(BaseWifiService);


        FilePath_ITEM  IPAddressTypeAvailabilityElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/IPAddressTypeAvailabilityElement.java");
        allFileList.add(IPAddressTypeAvailabilityElement);


        FilePath_ITEM  AggressiveConnectedScore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/AggressiveConnectedScore.java");
        allFileList.add(AggressiveConnectedScore);


        FilePath_ITEM  SupplicantStaIfaceHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantStaIfaceHal.java");
        allFileList.add(SupplicantStaIfaceHal);


        FilePath_ITEM  NoOpOnStartTetheringCallback =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/NoOpOnStartTetheringCallback.java");
        allFileList.add(NoOpOnStartTetheringCallback);


        FilePath_ITEM  VelocityBasedConnectedScore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/VelocityBasedConnectedScore.java");
        allFileList.add(VelocityBasedConnectedScore);


        FilePath_ITEM  NonEAPInnerAuth =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/NonEAPInnerAuth.java");
        allFileList.add(NonEAPInnerAuth);


        FilePath_ITEM  PasspointObjectFactory =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointObjectFactory.java");
        allFileList.add(PasspointObjectFactory);


        FilePath_ITEM  DisclaimerItemListAdapter =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/DisclaimerItemListAdapter.java");
        allFileList.add(DisclaimerItemListAdapter);


        FilePath_ITEM  WifiWakeMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiWakeMetrics.java");
        allFileList.add(WifiWakeMetrics);


        FilePath_ITEM  WifiP2pPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pPreferenceController.java");
        allFileList.add(WifiP2pPreferenceController);


        FilePath_ITEM  ActiveModeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ActiveModeManager.java");
        allFileList.add(ActiveModeManager);


        FilePath_ITEM  LocationPolicyDisclaimer =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/LocationPolicyDisclaimer.java");
        allFileList.add(LocationPolicyDisclaimer);


        FilePath_ITEM  SystemInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/SystemInfo.java");
        allFileList.add(SystemInfo);


        FilePath_ITEM  WifiPowerMetrics =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiPowerMetrics.java");
        allFileList.add(WifiPowerMetrics);


        FilePath_ITEM  NetworkUpdateResult =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkUpdateResult.java");
        allFileList.add(NetworkUpdateResult);


        FilePath_ITEM  WifiP2pDeviceList =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pDeviceList.java");
        allFileList.add(WifiP2pDeviceList);


        FilePath_ITEM  WifiScanningRequiredFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiScanningRequiredFragment.java");
        allFileList.add(WifiScanningRequiredFragment);


        FilePath_ITEM  ChannelHelper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/ChannelHelper.java");
        allFileList.add(ChannelHelper);


        FilePath_ITEM  WifiAwareNetworkInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareNetworkInfo.java");
        allFileList.add(WifiAwareNetworkInfo);


        FilePath_ITEM  NAIRealmData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/NAIRealmData.java");
        allFileList.add(NAIRealmData);


        FilePath_ITEM  DisclaimerItemFactory =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/DisclaimerItemFactory.java");
        allFileList.add(DisclaimerItemFactory);


        FilePath_ITEM  ChangeWifiStateDetails =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/ChangeWifiStateDetails.java");
        allFileList.add(ChangeWifiStateDetails);


        FilePath_ITEM  SppConstants =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/SppConstants.java");
        allFileList.add(SppConstants);


        FilePath_ITEM  WifiTetherSecurityPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSecurityPreferenceController.java");
        allFileList.add(WifiTetherSecurityPreferenceController);


        FilePath_ITEM  WifiTetherPasswordPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherPasswordPreferenceController.java");
        allFileList.add(WifiTetherPasswordPreferenceController);


        FilePath_ITEM  BrowserUri =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/command/BrowserUri.java");
        allFileList.add(BrowserUri);


        FilePath_ITEM  ANQPElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/ANQPElement.java");
        allFileList.add(ANQPElement);


        FilePath_ITEM  WifiLastResortWatchdog =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLastResortWatchdog.java");
        allFileList.add(WifiLastResortWatchdog);


        FilePath_ITEM  RangingResult =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/RangingResult.java");
        allFileList.add(RangingResult);


        FilePath_ITEM  WifiScanner =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiScanner.java");
        allFileList.add(WifiScanner);


        FilePath_ITEM  WfaKeyStore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/WfaKeyStore.java");
        allFileList.add(WfaKeyStore);


        FilePath_ITEM  WifiDppConfiguratorActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiDppConfiguratorActivity.java");
        allFileList.add(WifiDppConfiguratorActivity);


        FilePath_ITEM  PnoSettings =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/PnoSettings.java");
        allFileList.add(PnoSettings);


        FilePath_ITEM  WifiNetworkListFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/dpp/WifiNetworkListFragment.java");
        allFileList.add(WifiNetworkListFragment);


        FilePath_ITEM  XmlUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/XmlUtil.java");
        allFileList.add(XmlUtil);


        FilePath_ITEM  ScoringParams =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScoringParams.java");
        allFileList.add(ScoringParams);


        FilePath_ITEM  ParcelablePeerHandle =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/ParcelablePeerHandle.java");
        allFileList.add(ParcelablePeerHandle);


        FilePath_ITEM  NetworkRequestDialogFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/NetworkRequestDialogFragment.java");
        allFileList.add(NetworkRequestDialogFragment);


        FilePath_ITEM  PasspointProvider =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointProvider.java");
        allFileList.add(PasspointProvider);


        FilePath_ITEM  AppStateChangeWifiStateBridge =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/AppStateChangeWifiStateBridge.java");
        allFileList.add(AppStateChangeWifiStateBridge);


        FilePath_ITEM  WifiMeteredPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiMeteredPreferenceController.java");
        allFileList.add(WifiMeteredPreferenceController);


        FilePath_ITEM  HiddenNetwork =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/HiddenNetwork.java");
        allFileList.add(HiddenNetwork);


        FilePath_ITEM  WifiConfigStore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigStore.java");
        allFileList.add(WifiConfigStore);


        FilePath_ITEM  DiscoverySessionCallback =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/DiscoverySessionCallback.java");
        allFileList.add(DiscoverySessionCallback);


        FilePath_ITEM  EAPMethod =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/eap/EAPMethod.java");
        allFileList.add(EAPMethod);


        FilePath_ITEM  ANQPMatcher =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/ANQPMatcher.java");
        allFileList.add(ANQPMatcher);


        FilePath_ITEM  DeletedEphemeralSsidsStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/DeletedEphemeralSsidsStoreData.java");
        allFileList.add(DeletedEphemeralSsidsStoreData);


        FilePath_ITEM  PasspointConfigSharedStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointConfigSharedStoreData.java");
        allFileList.add(PasspointConfigSharedStoreData);


        FilePath_ITEM  WifiAPITest =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiAPITest.java");
        allFileList.add(WifiAPITest);


        FilePath_ITEM  WifiConfigurationUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigurationUtil.java");
        allFileList.add(WifiConfigurationUtil);


        FilePath_ITEM  HSWanMetricsElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/HSWanMetricsElement.java");
        allFileList.add(HSWanMetricsElement);


        FilePath_ITEM  OpenNetworkNotifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/OpenNetworkNotifier.java");
        allFileList.add(OpenNetworkNotifier);


        FilePath_ITEM  ConnectedAccessPointPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/ConnectedAccessPointPreference.java");
        allFileList.add(ConnectedAccessPointPreference);


        FilePath_ITEM  ScanOnlyModeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanOnlyModeManager.java");
        allFileList.add(ScanOnlyModeManager);


        FilePath_ITEM  WifiP2pServiceInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pServiceInfo.java");
        allFileList.add(WifiP2pServiceInfo);


        FilePath_ITEM  WifiCallingDisclaimerFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/WifiCallingDisclaimerFragment.java");
        allFileList.add(WifiCallingDisclaimerFragment);


        FilePath_ITEM  SingleScanSettings =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/SingleScanSettings.java");
        allFileList.add(SingleScanSettings);


        FilePath_ITEM  SsidSetStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SsidSetStoreData.java");
        allFileList.add(SsidSetStoreData);


        FilePath_ITEM  WifiHandler =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/WifiHandler.java");
        allFileList.add(WifiHandler);


        FilePath_ITEM  WifiAwareDiscoverySessionState =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareDiscoverySessionState.java");
        allFileList.add(WifiAwareDiscoverySessionState);


        FilePath_ITEM  SubscribedAccessPointsPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/savedaccesspoints/SubscribedAccessPointsPreferenceController.java");
        allFileList.add(SubscribedAccessPointsPreferenceController);


        FilePath_ITEM  WifiLog =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLog.java");
        allFileList.add(WifiLog);


        FilePath_ITEM  DataIntegrityChecker =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/DataIntegrityChecker.java");
        allFileList.add(DataIntegrityChecker);


        FilePath_ITEM  WifiAwareServiceImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareServiceImpl.java");
        allFileList.add(WifiAwareServiceImpl);


        FilePath_ITEM  SoapParser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/SoapParser.java");
        allFileList.add(SoapParser);


        FilePath_ITEM  NetworkSuggestionStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/NetworkSuggestionStoreData.java");
        allFileList.add(NetworkSuggestionStoreData);


        FilePath_ITEM  WpsDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WpsDialog.java");
        allFileList.add(WpsDialog);


        FilePath_ITEM  WifiP2pDnsSdServiceInfo =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pDnsSdServiceInfo.java");
        allFileList.add(WifiP2pDnsSdServiceInfo);


        FilePath_ITEM  FrameworkFacade =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/FrameworkFacade.java");
        allFileList.add(FrameworkFacade);





        FilePath_ITEM  CellularNetwork =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/CellularNetwork.java");
        allFileList.add(CellularNetwork);


        FilePath_ITEM  LastMileLogger =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/LastMileLogger.java");
        allFileList.add(LastMileLogger);


        FilePath_ITEM  SupplicantP2pIfaceCallback =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/SupplicantP2pIfaceCallback.java");
        allFileList.add(SupplicantP2pIfaceCallback);


        FilePath_ITEM  ConfigParser =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/ConfigParser.java");
        allFileList.add(ConfigParser);


        FilePath_ITEM  RoamingConsortiumElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/RoamingConsortiumElement.java");
        allFileList.add(RoamingConsortiumElement);


        FilePath_ITEM  WifiP2pConfigurationDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pConfigurationDialog.java");
        allFileList.add(WifiP2pConfigurationDialog);


        FilePath_ITEM  HomeSp =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/pps/HomeSp.java");
        allFileList.add(HomeSp);


        FilePath_ITEM  UpdateResponseMessage =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/UpdateResponseMessage.java");
        allFileList.add(UpdateResponseMessage);


        FilePath_ITEM  WifiServiceImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java");
        allFileList.add(WifiServiceImpl);


        FilePath_ITEM  AuthMatch =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/AuthMatch.java");
        allFileList.add(AuthMatch);


        FilePath_ITEM  AvailableNetworkNotifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/AvailableNetworkNotifier.java");
        allFileList.add(AvailableNetworkNotifier);


        FilePath_ITEM  ServiceProviderVerifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/ServiceProviderVerifier.java");
        allFileList.add(ServiceProviderVerifier);


        FilePath_ITEM  WifiAwareClientState =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareClientState.java");
        allFileList.add(WifiAwareClientState);


        FilePath_ITEM  WifiConfigManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigManager.java");
        allFileList.add(WifiConfigManager);


        FilePath_ITEM  PasspointConfigStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointConfigStoreData.java");
        allFileList.add(PasspointConfigStoreData);


        FilePath_ITEM  CellularLinkLayerStatsCollector =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CellularLinkLayerStatsCollector.java");
        allFileList.add(CellularLinkLayerStatsCollector);


        FilePath_ITEM  MemoryStoreImpl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/MemoryStoreImpl.java");
        allFileList.add(MemoryStoreImpl);


        FilePath_ITEM  DefaultModeManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/DefaultModeManager.java");
        allFileList.add(DefaultModeManager);


        FilePath_ITEM  PasspointEventHandler =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointEventHandler.java");
        allFileList.add(PasspointEventHandler);


        FilePath_ITEM  LocalOnlyHotspotRequestInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/LocalOnlyHotspotRequestInfo.java");
        allFileList.add(LocalOnlyHotspotRequestInfo);


        FilePath_ITEM  WifiLockManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLockManager.java");
        allFileList.add(WifiLockManager);


        FilePath_ITEM  WifiNetworkDetailsFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiNetworkDetailsFragment.java");
        allFileList.add(WifiNetworkDetailsFragment);


        FilePath_ITEM  ListWithEntrySummaryPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/ListWithEntrySummaryPreference.java");
        allFileList.add(ListWithEntrySummaryPreference);


        FilePath_ITEM  ParcelUtil =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/ParcelUtil.java");
        allFileList.add(ParcelUtil);


        FilePath_ITEM  ThreeGPPNetworkElement =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/ThreeGPPNetworkElement.java");
        allFileList.add(ThreeGPPNetworkElement);


        FilePath_ITEM  WifiCallingSuggestionActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/WifiCallingSuggestionActivity.java");
        allFileList.add(WifiCallingSuggestionActivity);


        FilePath_ITEM  KalmanFilter =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/KalmanFilter.java");
        allFileList.add(KalmanFilter);


        FilePath_ITEM  WifiP2pMonitor =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pMonitor.java");
        allFileList.add(WifiP2pMonitor);


        FilePath_ITEM  WifiP2pUpnpServiceResponse =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pUpnpServiceResponse.java");
        allFileList.add(WifiP2pUpnpServiceResponse);


        FilePath_ITEM  WifiCallingSliceHelper =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/WifiCallingSliceHelper.java");
        allFileList.add(WifiCallingSliceHelper);


        FilePath_ITEM  QrDecorateView =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/qrcode/QrDecorateView.java");
        allFileList.add(QrDecorateView);


        FilePath_ITEM  PasspointXmlUtils =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointXmlUtils.java");
        allFileList.add(PasspointXmlUtils);


        FilePath_ITEM  WakeupNotificationFactory =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupNotificationFactory.java");
        allFileList.add(WakeupNotificationFactory);


        FilePath_ITEM  CellularFallbackPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/CellularFallbackPreferenceController.java");
        allFileList.add(CellularFallbackPreferenceController);


        FilePath_ITEM  WakeupConfigStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WakeupConfigStoreData.java");
        allFileList.add(WakeupConfigStoreData);


        FilePath_ITEM  ConnectedScore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ConnectedScore.java");
        allFileList.add(ConnectedScore);


        FilePath_ITEM  CarrierNetworkEvaluator =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CarrierNetworkEvaluator.java");
        allFileList.add(CarrierNetworkEvaluator);


        FilePath_ITEM  ChannelSettings =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/ChannelSettings.java");
        allFileList.add(ChannelSettings);


        FilePath_ITEM  DomainMatcher =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/DomainMatcher.java");
        allFileList.add(DomainMatcher);


        FilePath_ITEM  PnoNetwork =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/wificond/PnoNetwork.java");
        allFileList.add(PnoNetwork);


        FilePath_ITEM  AccessPointPreference =  new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java");
        allFileList.add(AccessPointPreference);


        FilePath_ITEM  WifiP2pDnsSdServiceResponse =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pDnsSdServiceResponse.java");
        allFileList.add(WifiP2pDnsSdServiceResponse);


        FilePath_ITEM  Policy =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/pps/Policy.java");
        allFileList.add(Policy);


        FilePath_ITEM  PasspointManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointManager.java");
        allFileList.add(PasspointManager);


        FilePath_ITEM  KnownBandsChannelHelper =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/KnownBandsChannelHelper.java");
        allFileList.add(KnownBandsChannelHelper);


        FilePath_ITEM  CompatibilityScorer =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CompatibilityScorer.java");
        allFileList.add(CompatibilityScorer);


        FilePath_ITEM  DppManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/DppManager.java");
        allFileList.add(DppManager);


        FilePath_ITEM  OsuProviderInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/OsuProviderInfo.java");
        allFileList.add(OsuProviderInfo);


        FilePath_ITEM  WifiTrafficPoller =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiTrafficPoller.java");
        allFileList.add(WifiTrafficPoller);


        FilePath_ITEM  RttManager =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/RttManager.java");
        allFileList.add(RttManager);


        FilePath_ITEM  XMLParser =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/omadm/XMLParser.java");
        allFileList.add(XMLParser);


        FilePath_ITEM  WlanWakeReasonAndCounts =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WlanWakeReasonAndCounts.java");
        allFileList.add(WlanWakeReasonAndCounts);


        FilePath_ITEM  DisclaimerItem =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/calling/DisclaimerItem.java");
        allFileList.add(DisclaimerItem);


        FilePath_ITEM  WifiKeyStore =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiKeyStore.java");
        allFileList.add(WifiKeyStore);


        FilePath_ITEM  PassPointMNCMCCRetail =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/PassPointMNCMCCRetail.java");
        allFileList.add(PassPointMNCMCCRetail);


        FilePath_ITEM  DiscoverySession =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/DiscoverySession.java");
        allFileList.add(DiscoverySession);


        FilePath_ITEM  XMLNode =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/omadm/XMLNode.java");
        allFileList.add(XMLNode);


        FilePath_ITEM  AddDevicePreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/AddDevicePreferenceController.java");
        allFileList.add(AddDevicePreferenceController);


        FilePath_ITEM  WifiConfigUiBase =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigUiBase.java");
        allFileList.add(WifiConfigUiBase);


        FilePath_ITEM  LongPressAccessPointPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java");
        allFileList.add(LongPressAccessPointPreference);


        FilePath_ITEM  CaptivePortalNetworkCallback =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/CaptivePortalNetworkCallback.java");
        allFileList.add(CaptivePortalNetworkCallback);


        FilePath_ITEM  WifiScanModeActivity =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiScanModeActivity.java");
        allFileList.add(WifiScanModeActivity);


        FilePath_ITEM  PostDevDataResponse =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/soap/PostDevDataResponse.java");
        allFileList.add(PostDevDataResponse);


        FilePath_ITEM  ScanResultMatchInfo =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanResultMatchInfo.java");
        allFileList.add(ScanResultMatchInfo);


        FilePath_ITEM  ScanDetailCache =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanDetailCache.java");
        allFileList.add(ScanDetailCache);


        FilePath_ITEM  WifiNoInternetDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiNoInternetDialog.java");
        allFileList.add(WifiNoInternetDialog);


        FilePath_ITEM  ProtocolPortTuple =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/ProtocolPortTuple.java");
        allFileList.add(ProtocolPortTuple);


        FilePath_ITEM  HostapdHal =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/HostapdHal.java");
        allFileList.add(HostapdHal);


        FilePath_ITEM  WifiTetherSsidPreference =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSsidPreference.java");
        allFileList.add(WifiTetherSsidPreference);


        FilePath_ITEM  WpsResult =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WpsResult.java");
        allFileList.add(WpsResult);


        FilePath_ITEM  OsuServerConnection =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/OsuServerConnection.java");
        allFileList.add(OsuServerConnection);


        FilePath_ITEM  CarrierNetworkNotifier =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/CarrierNetworkNotifier.java");
        allFileList.add(CarrierNetworkNotifier);


        FilePath_ITEM  WifiTetherSSIDPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSSIDPreferenceController.java");
        allFileList.add(WifiTetherSSIDPreferenceController);


        FilePath_ITEM  TimedQuotaManager =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/TimedQuotaManager.java");
        allFileList.add(TimedQuotaManager);


        FilePath_ITEM  FrameParser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/FrameParser.java");
        allFileList.add(FrameParser);


        FilePath_ITEM  WifiTetherSettings =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSettings.java");
        allFileList.add(WifiTetherSettings);


        FilePath_ITEM  ScanScheduleUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/ScanScheduleUtil.java");
        allFileList.add(ScanScheduleUtil);


        FilePath_ITEM  WifiConnectionPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiConnectionPreferenceController.java");
        allFileList.add(WifiConnectionPreferenceController);


        FilePath_ITEM  Characteristics =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/Characteristics.java");
        allFileList.add(Characteristics);


        FilePath_ITEM  WifiPermissionsUtil =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/util/WifiPermissionsUtil.java");
        allFileList.add(WifiPermissionsUtil);


        FilePath_ITEM  WifiBackupDataParser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiBackupDataParser.java");
        allFileList.add(WifiBackupDataParser);


        FilePath_ITEM  WifiTetherPreferenceController =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherPreferenceController.java");
        allFileList.add(WifiTetherPreferenceController);


        FilePath_ITEM  PasspointProvisioner =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointProvisioner.java");
        allFileList.add(PasspointProvisioner);


        FilePath_ITEM  WifiRttManager =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/WifiRttManager.java");
        allFileList.add(WifiRttManager);


        FilePath_ITEM  OffloadWifiApSelectorFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/OffloadWifiApSelectorFragment.java");
        allFileList.add(OffloadWifiApSelectorFragment);


        FilePath_ITEM  LegacyPasspointConfigParser =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/LegacyPasspointConfigParser.java");
        allFileList.add(LegacyPasspointConfigParser);


        FilePath_ITEM  RandomizedMacStoreData =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/RandomizedMacStoreData.java");
        allFileList.add(RandomizedMacStoreData);


        FilePath_ITEM  AddNetworkFragment =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/AddNetworkFragment.java");
        allFileList.add(AddNetworkFragment);


        FilePath_ITEM  WifiDialog =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java");
        allFileList.add(WifiDialog);


        FilePath_ITEM  SavedNetworkComparator =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/savedaccesspoints/SavedNetworkComparator.java");
        allFileList.add(SavedNetworkComparator);


        FilePath_ITEM  WifiP2pDnsSdServiceRequest =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/nsd/WifiP2pDnsSdServiceRequest.java");
        allFileList.add(WifiP2pDnsSdServiceRequest);


        FilePath_ITEM  WifiScoreCard =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiScoreCard.java");
        allFileList.add(WifiScoreCard);


        FilePath_ITEM  WifiNetworkSpecifier =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiNetworkSpecifier.java");
        allFileList.add(WifiNetworkSpecifier);


        FilePath_ITEM  WificondControl =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WificondControl.java");
        allFileList.add(WificondControl);


        FilePath_ITEM  I18Name =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/anqp/I18Name.java");
        allFileList.add(I18Name);


        FilePath_ITEM  TetherService =  new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/TetherService.java");
        allFileList.add(TetherService);


        FilePath_ITEM  WifiController =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiController.java");
        allFileList.add(WifiController);


        FilePath_ITEM  WifiAwareSession =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareSession.java");
        allFileList.add(WifiAwareSession);


        FilePath_ITEM  PpsMoParser =  new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/omadm/PpsMoParser.java");
        allFileList.add(PpsMoParser);


        FilePath_ITEM  WifiInjector =  new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiInjector.java");
        allFileList.add(WifiInjector);



    }

    static String POINT_STR = ".";

    static class FilePath_ITEM {
        String mfileName_common; //  文件名全称  不包含后缀  wifisettings
        String mfileName_lower;   // 文件名全称的小写    不包含后缀
        String mFileType;  //   文件的类型   没有小数点
        String mAOSP_Path;   // AOSP 中文件的全局路径
        String mOriginalName;   // 最原始的
        ArrayList<JavaFile_Item> allJavaFileList;    // 所有版本的文件  文件放在 ~/Desktop/zbin/F2/

        FilePath_ITEM(String AOSPPath) {
            this.mAOSP_Path = AOSPPath.trim();
            this.mFileType = getAbsFileType(AOSPPath.trim());
            this.mfileName_common = getAbsFileName(AOSPPath.trim());
            this.mOriginalName = this.mfileName_common;
            this.mfileName_lower = this.mfileName_common.toLowerCase();
            allJavaFileList = new ArrayList<JavaFile_Item>();
            for (int i = 0; i < aospList.size(); i++) {
                int api = aospList.get(i).apiVersion;
                double androidVersion = aospList.get(i).AndroidVersion;
                char versionTagChar = aospList.get(i).getAndroid_CharName();
                String downloadPath = aospList.get(i).getDownLoadPre().trim() + AOSPPath.trim();
                String visitedUrl = aospList.get(i).getVisitedPre().trim() + AOSPPath.trim();
                // 9.0 转为 9_0   ,   8.1 转为 8_1
                String mAndroidVersionFixed = (androidVersion + "").replace(".", "_") + "_";
                String mAndroidNameTagFixed = "_" + versionTagChar;

                String parentPath = F2File_AOSP_DIR.getAbsolutePath() + File.separator + this.mfileName_lower;
                // 文件命名规则  /zbin/F2_AOSP_File/wifisettings/9_0_WIFISettings_L.java
                String absPath = F2File_AOSP_DIR.getAbsolutePath() + File.separator + this.mfileName_lower + File.separator + mAndroidVersionFixed + this.mfileName_common + mAndroidNameTagFixed + "." + this.mFileType;

                JavaFile_Item javaFile = new JavaFile_Item(absPath,this.mfileName_common, parentPath, downloadPath, versionTagChar, api, androidVersion, visitedUrl);
                allJavaFileList.add(javaFile);
            }
        }

    }

    static String getAbsFileType(String absPath) {
        String path = "";
        if (absPath.contains(".")) {
            path = absPath.substring(absPath.lastIndexOf(".") + 1);
        }
        return path;
    }

    static String getAbsFileName(String absPath) {
        String name = "";
        if (absPath.contains("/")) {
            name = absPath.substring(absPath.lastIndexOf("/") + 1);
        }

        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf(".")).trim();
        }

        return name;
    }


    public static int getLineNum(File targetFile) {
        long lineCount = 0 ;
        try {

            BufferedReader txtBR  = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile),"utf-8"));


            lineCount = txtBR.lines().count();  // 当前输入 1.txt的行数
            txtBR.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        return (int)lineCount;
    }


    static class JavaFile_Item {
        File targetJavaFile;   //  对应版本的目标java 文件
        File parrentFileDir;
        String originFileName;
        boolean isfile_exist_local;
        boolean isfile_exit_innet; // 是否在 网络上存在
        String targetJavaFilePath;  // 目标java 文件的绝对路径
        String mdownloadUrl;  // 该版本下的目标下载路径
        String mVisitedUrl;  // 该文件的网络浏览路径
        int apiVersion;
        double androidVersion;
        char androidNickChar;
        int fileLineNum;  // 文件的行数
        int methodNum;   // 文件中的函数方法个数
        ArrayList<String> methodList;  // 方法名列表
        ArrayList<String> memberList;   // 成员属性列表

        JavaFile_Item(String absPath, String originalName , String parentPath, String downloadUrl, char versionNickChar, int api, double version, String visitedUrl) {
            this.targetJavaFilePath = absPath;
            this.mdownloadUrl = downloadUrl;
            this.parrentFileDir = new File(parentPath);
            if (!this.parrentFileDir.exists()) {
                this.parrentFileDir.mkdirs();
            }
            this.targetJavaFile = new File(absPath);
            this.originFileName = originalName;
            //targetJavaFile.listFiles()
            this.isfile_exist_local = targetJavaFile.exists();
            // 把  targetJavaFile的名称当做存放是否存在Tag Key
            String isNetExistStr = F2_Properties.getProperty(targetJavaFile.getName() + "_Online");
            if (isNetExistStr == null || "".equals(isNetExistStr) || "null".equals(isNetExistStr)) {
                this.isfile_exit_innet = true;   // 默认为 true 存在
            } else {
                this.isfile_exit_innet = Boolean.parseBoolean(isNetExistStr); // false 不存在 才从 Pro读取
            }

            this.androidNickChar = versionNickChar;
            this.apiVersion = api;
            this.androidVersion = version;
            this.mVisitedUrl = visitedUrl;
        }
    }

    // FileCount===============================Begin
/*
    addAllFileDir(dirFile);
    initSimpleFileSet();
    allSimpleFileSet.size(); // 文件中文件数量
    allDirFileSet.size();   // 文件夹数量
    */
    static int mSumDirNum = 0;
    static int mSumSingleFileNum = 0;
    static Set<File> allDirFileSet = new HashSet<>();  // 【1】工程下所有的 文件夹文件
    static Set<File> allSimpleFileSet = new HashSet<>();   // 【2】当前工程下所有非文件夹的 文件

    static int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象     【1】 添加根目录
        if (dirFile != null && dirFile.isDirectory()) {
            allDirFileSet.add(dirFile);
            mSumDirNum++;
        }

        if (isEmptyDirFile(dirFile)) {  // 如果是空的文件夹  返回它的内部文件夹数量是0
            return 0;
        }
        ArrayList<File> childDirFile = getChildDirFileMethod(dirFile);
        if (childDirFile != null && childDirFile.size() > 0) {

            for (File dirFileItem : childDirFile) {
                addAllFileDir(dirFileItem);
            }
        }
        return mSumDirNum;
    }

    static void initSimpleFileSet() {  // 【2】 得到没有文件
        int fileIndex = 1;
        for (File dirFile : allDirFileSet) {
            //  System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory()) {
                        allSimpleFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
        mSumSingleFileNum = fileIndex;
    }


    static ArrayList<File> getChildDirFileMethod(File dirFile) {
        ArrayList<File> dirFileList = null;  // new   ArrayList<File>();
        if (dirFile == null) {
            return null;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                if (dirFileList == null) {
                    dirFileList = new ArrayList<File>();
                }
                dirFileList.add(childChildList[i]);
            }
        }
        return dirFileList;
    }


    static boolean isEmptyDirFile(File dirFile) {
        boolean flag = true;
        if (dirFile == null) {
            return true;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                return false;
            }
        }
        return flag;
    }

// FileCount===============================End

    // ArrayPrint ==============================Begin
    static public void printArrObject(Object[] objArr, String title) {
        ArrayList<String> curPropStrArr = new ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if ("".equals(objArr[i].toString())) {
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr, title);
    }


    public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
        boolean flag = false;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
                //   System.out.println("index["+i+"]  size= "+mStrList.get(i).length()+"     Value:" + mStrList.get(i) );
                return true;
            }
        }
        return flag;

    }


    public static ArrayList<String> makeStringGroup(String code, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        String oriStr = code.trim();
        while (oriStr.length() > maxcount) {
            String str1 = oriStr.substring(0, maxcount);
            fixArr.add(str1);
            oriStr = oriStr.substring(maxcount);
        }


        return fixArr;
    }


    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains("【") && strArr[i].contains("】")) {
                    subArrA = toSqlitWithhardBlock(strArr[i]);
                } else if (strArr[i].contains(";")) {
                    subArrA = sqlitString(strArr[i], ";");

                } else if (strArr[i].contains("。")) {
                    subArrA = sqlitString(strArr[i], "。");

                } else if (strArr[i].contains(":")) {
                    subArrA = sqlitString(strArr[i], ":");
                } else if (strArr[i].contains(".")) {
                    subArrA = sqlitString(strArr[i], ".");
                } else if (strArr[i].contains(" ")) {
                    subArrA = sqlitString(strArr[i], " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixArr.add(tempArr.get(j));
                    }

                }

                if (subArrA != null && isItemLengthOver100(subArrA)) {
                    String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixArr.add(fixSub);
                } else {
                    if (subArrA != null) {
                        for (int j = 0; j < subArrA.size(); j++) {
                            fixArr.add(subArrA.get(j));
                        }

                    }
                }

            } else {
                fixArr.add(strArr[i]);
            }
        }
        return fixArr;
    }


    public static ArrayList<String> toSqlitWithhardBlock(String mStrList) {
        ArrayList<String> resultList = new ArrayList<String>();
        //【】  【】,
        String mStr = mStrList.trim();

        String pre = mStr.substring(0, mStr.indexOf("【"));
        mStr = mStr.substring(mStr.indexOf("【"));
        resultList.add(pre);
        String end = "";
        if (mStr.endsWith("】")) {
            end = "";
        } else {
            end = mStr.substring(mStr.lastIndexOf("】") + 1);
        }

        mStr = mStr.substring(0, mStr.lastIndexOf("】") + 1);

        while (mStr.contains("】") && mStr.contains("【")) {
            String firstStr = mStr.substring(mStr.indexOf("【"), mStr.indexOf("】") + 1);
            resultList.add(firstStr);
            mStr = mStr.substring(mStr.indexOf("】") + 1);
        }

        if (!"".equals(mStr.trim())) {
            resultList.add(mStr.trim());
        }

        if (!"".equals(end)) {
            resultList.add(end);
        }


//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("xxx："+i+"  ="+resultList.get(i) +"   mStr="+mStr);
//        }
        return resultList;
    }


    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains("【") && curMaxStr.contains("】")) {
                    fixA = toSqlitWithhardBlock(curMaxStr);
                } else if (curMaxStr.contains(";")) {
                    fixA = sqlitString(curMaxStr, ";");
                } else if (curMaxStr.contains("。")) {
                    fixA = sqlitString(curMaxStr, "。");
                } else if (curMaxStr.contains(":")) {
                    fixA = sqlitString(curMaxStr, ":");
                } else if (curMaxStr.contains(".")) {
                    fixA = sqlitString(curMaxStr, ".");
                } else if (curMaxStr.contains(" ")) {
                    fixA = sqlitString(curMaxStr, " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixLengthArr.add(tempArr.get(j));
                    }
                }

                if (fixA != null) {
                    //   System.out.println(" fixA.size="+ fixA.size());
                    for (int j = 0; j < fixA.size(); j++) {
                        // System.out.println(" fixA.size="+ fixA.size() + " i="+j+"   value:"+fixA.get(j));
                    }
                } else {
                    //  System.out.println(" fixA.size= null!");
                }
                if (isItemLengthOver100(fixA)) {
                    String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixLengthArr.add(fixSub);
                } else {
                    if (fixA != null) {
                        for (int j = 0; j < fixA.size(); j++) {
                            fixLengthArr.add(fixA.get(j));
                        }
                    }
                }


            }
        }

        return fixLengthArr;
    }


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
    }

    public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curStr = mStrList.get(i);
            String fixCurStr = getFixLengthNewStr(curStr, maxcount);
            fixArr.add(fixCurStr);
        }

        return fixArr;
    }


    public static String getFixLengthNewStr(String oriStr, int maxLength) {
        String fixStr = "";
        String beginChar = "║ ";
        String endChar = "║";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getChineseCount(oriStr);
        paddingLength = paddingLength - chineseCount;
        if (paddingLength < 0) {
            // return "curString:" + oriStr + "  length more than" + maxLength;
            return "";
        }

        for (int i = 0; i < paddingLength; i++) {
            oriStrTrim += " ";
        }
        oriStrTrim = beginChar + oriStrTrim + endChar;
        //  oriStrTrim = beginChar + oriStrTrim ;
        fixStr = oriStrTrim;
        return fixStr;
    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
            ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
        int maxLength = getItemMaxLength(mStrList);
        ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
        int chineseCount = getChineseCount(title);


        String beginRow = "╔════════════════════════════════════════════════" + title + "═════════════════════════════════════════════════════╗";
        String endRow = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝";
        int fixLength = 0;
        int oriLength = title.length();
        if (chineseCount == 0) { // 不包含汉字
            fixLength = oriLength;

        } else {
            if (chineseCount == oriLength) { // 全部包含汉字
                fixLength = 2 * oriLength;
            } else { // 一部分汉字  一部分英语

                fixLength = oriLength - chineseCount + (2 * chineseCount);
            }

        }
        String templateString = "╗";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "═" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "╗");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow = resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow = resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╝", paddingString + "╝");
        return curBeginStr;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    static String resetBeginRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╗", paddingString + "╗");
        return curBeginStr;
    }

    public static void ArrayPrint(ArrayList<String> mStrList, String title) {

        ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
        // 对mStrList 进行 对其处理  重新转换为 对其的  ArrayList<String> new
        // 1. 判断所有字符串中 第一次出现冒号的位置   查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
        // 2.  重新排序的规则是  小字符串需要在: 之后添加  xMaxLengh - self().length 的空格 并重新加入新的数组
        ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
        boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

        if (isOver100) {
            //     System.out.println("当前的字符串Item 存在大于 100字符的！");
            ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
            showTableLogCommon100(newLessList, title);  //  每一行都小于100个字的打印
        } else { //
            //   System.out.println("当前的字符串Item 不 存在大于 100字符的！");
            showTableLogCommon100(firstFixedStringArrA, title);  //  每一行都小于100个字的打印


        }
    }

    public static String getPaddingEmptyString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "-";
        }
        return str;
    }

    // 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
    // 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
    public static String addMaoChinese(String oriStr) {
        String resultStr = "";
        int chinesePosition = getFirstChinesePosition(oriStr);
        resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
        return resultStr;
    }


    public static int getFirstChinesePosition(String str) {
        int position = 0;
        boolean getFirstChinese = false;
        char[] newChar = str.toCharArray();  //转为单个字符
        for (int i = 0; i < newChar.length; i++) {
            char curChar = newChar[i];
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(curChar + "");
            if (m.find()) {
                getFirstChinese = true;
                position = i;
            } else {
                if (getFirstChinese == true) {
                    return i;
                }
            }

        }
        return position;
    }

    public static String addMaoBlank(String oriStr) {
        String resultStr = "";
        int blankPosition = oriStr.indexOf(" ");
        resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
        return resultStr;
    }

    public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            if (isCommonMao(curItem)) {
                fixedArr.add(curItem);
            } else {
                // 对于那些没有冒号的  字段的处理
                //1.如果包含汉字 那么就在 汉字的最后添加一个冒号
                // 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
                if (isContainChinese(curItem)) {
                    String addMaoStr = addMaoChinese(curItem);
                    fixedArr.add(addMaoStr);
                } else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
                    String addMaoStr = addMaoBlank(curItem);
                    fixedArr.add(addMaoStr);
                } else {  // 如果以上条件都不满足   那么就在字符串最前面添加一个冒号

                    fixedArr.add(":" + curItem);

                }

            }

        }
        return fixedArr;
    }


    // 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度  返回true
    public static boolean isCommonMao(String oriStr) {
        boolean flag = false;
        if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
            flag = true;  // 只有一个冒号
        } else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\") && oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
            flag = true; // 多个冒号 并且  第一个冒号  :   在 :\ 之前
        } else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
            flag = true;   // 多个冒号
        }
        return flag;
    }

    public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        int maxMaoPosition = getMaxMaoPosition(mStrList);
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            int curMaoPosition = curItem.indexOf(":");
            String pre = curItem.substring(0, curMaoPosition);
            String end = curItem.substring(curMaoPosition); // 去掉:
            int preLength = getPaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
            String fixedItem = pre + padding + end;
            fixedArr.add(fixedItem);


        }
        return fixedArr;
    }

    public static int getMaxMaoPosition(ArrayList<String> mStrList) {
        int maoPosition = 0;
        String maxString = "";
        for (int i = 0; i < mStrList.size(); i++) {
            if ((mStrList.get(i).contains(":"))) {
                int curMaoPosition = mStrList.get(i).indexOf(":");
                String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
                int paddingSize = getPaddingChineseLength(maoString);
                if (paddingSize > maoPosition) {
                    maoPosition = paddingSize;
                    maxString = mStrList.get(i);
                }
            }

        }
        //  System.out.println("最长的冒号位置: maoPosition="+maoPosition+"   string="+maxString);
        return maoPosition;
    }
    // ArrayPrint ==============================End


    public static int testWsdlConnection(String address) throws Exception {
        int status = 404;
        try {
            URL urlObj = new URL(address);
            HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
            oc.setUseCaches(false);
            oc.setConnectTimeout(3000); // 设置超时时间
            status = oc.getResponseCode();// 请求状态
            if (200 == status) {
                // 200是请求地址顺利连通。。
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return status;

    }


    public static boolean downLoadWithJavaItemFile(String url, JavaFile_Item targetJavaFile) {
        boolean isOk = false;
        Connection.Response document = null;
        try {

            if (testWsdlConnection(url) == 404) { // 对于返回404 代表该文档不存在网络上
                targetJavaFile.isfile_exit_innet = false;
                F2_Properties.setProperty(targetJavaFile.targetJavaFile.getName() + "_Online", "" + targetJavaFile.isfile_exit_innet);
                return isOk;
            }


            document = Jsoup.connect(url).timeout(0).ignoreContentType(true).maxBodySize(30000000).timeout(10000).execute();

            BufferedInputStream stream = document.bodyStream();

            File outfile = targetJavaFile.targetJavaFile;
            if (!outfile.exists()) {
                outfile.createNewFile();
            }

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(outfile));

            byte[] bt = new byte[1024];
            int n = 0;
            while ((n = stream.read(bt)) != -1) {
                bout.write(bt, 0, n);

            }
            bout.flush();
            stream.close();
            bout.close();
            isOk = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOk;

    }


    public static boolean downLoadWithFile(String url, File targetFile) {
        boolean isOk = false;
        Connection.Response document = null;
        try {

            if (testWsdlConnection(url) == 404) { // 对于返回404 代表该文档不存在网络上
                return isOk;
            }


            document = Jsoup.connect(url).timeout(0).ignoreContentType(true).maxBodySize(30000000).timeout(10000).execute();

            BufferedInputStream stream = document.bodyStream();

            File outfile = targetFile;
            if (!outfile.exists()) {
                outfile.createNewFile();
            }

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(outfile));

            byte[] bt = new byte[1024];
            int n = 0;
            while ((n = stream.read(bt)) != -1) {
                bout.write(bt, 0, n);

            }
            bout.flush();
            stream.close();
            bout.close();
            isOk = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOk;

    }


    public static void downLoad(String url, String fileName, String savePath) {


        Connection.Response document = null;
        try {
            document = Jsoup.connect(url).timeout(0).ignoreContentType(true).maxBodySize(30000000).timeout(10000).execute();
            BufferedInputStream stream = document.bodyStream();
            File outfile = new File(savePath + File.separator + fileName);

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(outfile));

            byte[] bt = new byte[1024];
            int n = 0;
            while ((n = stream.read(bt)) != -1) {
                bout.write(bt, 0, n);

            }
            bout.flush();
            stream.close();
            bout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @SuppressWarnings("unchecked")
    static ArrayList<String>  analysisJavaFile_Method(File javaFile , String originalName){
        ArrayList<String> arrMethodList = new   ArrayList<String>();
        if(!javaFile.getName().endsWith(".java")){
            return arrMethodList;   // 如果不为 .java 文件结尾的 那么 返回空
        }

        String javaName = originalName;
        if (javaName.endsWith(".java")) {
            javaName = javaName.substring(0, javaName.length() - ".java".length());
        }



        //   System.out.println("1 javaName ="+ javaName + " originalName ="+ originalName);

        try {
            CompilationUnit mCompilationUnit = JavaParser.parse(javaFile);
            Optional<ClassOrInterfaceDeclaration> class_opt = mCompilationUnit.getClassByName(originalName);
            ClassOrInterfaceDeclaration mClassDeclaration = null;
            if (class_opt.isPresent()) {
                mClassDeclaration = class_opt.get();
            }

            if (mClassDeclaration != null) {



                List<MethodDeclaration> methodList = mClassDeclaration.getMethods();

                ArrayList<MethodDeclaration> methodDecList_arr = new ArrayList<MethodDeclaration>();
                for (MethodDeclaration mMethodDec : methodList) {
                    methodDecList_arr.add(mMethodDec);
                }

                methodDecList_arr.sort(FieldMethod_Compare);

                int index = 0;
                for (MethodDeclaration methodDec : methodDecList_arr) {


                    boolean isStaticMethod = methodDec.isStatic();
                    boolean isNativeMethod = methodDec.isNative();
                    boolean isSyncMethod = methodDec.isSynchronized();
                    boolean isAbstractMethod = methodDec.isAbstract();
                    boolean isFinalMethod = methodDec.isFinal();
                    String modifyWord = "";
                    if (isStaticMethod) {
                        modifyWord = modifyWord + " static ";
                    }
                    if (isFinalMethod) {
                        modifyWord = modifyWord + " final ";
                    }

                    if (isSyncMethod) {
                        modifyWord = modifyWord + " sync ";
                    }

                    if (isNativeMethod) {
                        modifyWord = modifyWord + " native ";
                    }

                    if (isAbstractMethod) {
                        modifyWord = modifyWord + " abstract ";
                    }

                    modifyWord = modifyWord.trim();

                    methodDec.isThrown("Exception");


                    String methodName = methodDec.getNameAsString(); // 函数名称
                    NodeList<Parameter> methodParamList = methodDec.getParameters();
                    String paramStr = "";    // 函数的参数


                    String methodDecStr = methodDec.getDeclarationAsString(false, false, false);
                    int returnIndex = methodDecStr.indexOf(methodName);
                    String returnString = "";
                    if (returnIndex > 0) {
                        returnString = methodDecStr.substring(0, returnIndex);
                    }
                    //  System.out.println("Signature =  "+  methodDec.getSignature().asString());
                    //        System.out.println("getDeclarationAsString =  "+  methodDec.getDeclarationAsString());
                    //       System.out.println("getDeclarationAsString2 =  "+  methodDec.getDeclarationAsString(false,false,false));
                    for (Parameter param : methodParamList) {
                        //  System.out.println("param = "+ param.toString());
                        paramStr = paramStr + "," + param.toString();
                    }
                    paramStr = paramStr.trim();
                    while (paramStr.startsWith(",")) {

                        paramStr = paramStr.substring(1);
                    }

                    while (paramStr.endsWith(",")) {
                        paramStr = paramStr.substring(1, paramStr.length());
                    }

                    //  System.out.println("returnString = " + returnString + "         methodName = " + methodName + "  paramStr = " + paramStr);
                    //  methodDec.g
                    // 继续点
                    String resultStr = "方法索引"+index+": "+returnString+" "+methodName+"("+paramStr+")";

                    if(resultStr.length() > MAX_COUNT_CHAR_IN_ROW){
                        resultStr = "方法索引"+index+": "+returnString.trim()+" "+methodName+"(....."+")";
                    }
                    arrMethodList.add(resultStr);
                    index ++ ;
                }



            }

        }catch (FileNotFoundException e) {
            //     System.out.println("X Exception ");

            e.printStackTrace();
        }

        return arrMethodList;
    }




    @SuppressWarnings("unchecked")
    static ArrayList<String>  analysisJavaFile_Member(File javaFile , String originalName){
        ArrayList<String> arrMemberList = new   ArrayList<String>();
        if(!javaFile.getName().endsWith(".java")){
            return arrMemberList;   // 如果不为 .java 文件结尾的 那么 返回空
        }

        String javaName = originalName;
        if (javaName.endsWith(".java")) {
            javaName = javaName.substring(0, javaName.length() - ".java".length());
        }



        //   System.out.println("1 javaName ="+ javaName + " originalName ="+ originalName);

        try {
            CompilationUnit mCompilationUnit = JavaParser.parse(javaFile);

            Optional<ClassOrInterfaceDeclaration> class_opt = mCompilationUnit.getClassByName(originalName);
            ClassOrInterfaceDeclaration mClassDeclaration = null;
            if (class_opt.isPresent()) {
                mClassDeclaration = class_opt.get();
            }

            if (mClassDeclaration != null) {


                List<FieldDeclaration> fieldDecList = mClassDeclaration.getFields();

                //    System.out.println("2 fieldDecList ="+ fieldDecList.size());
                ArrayList<FieldDeclaration> fieldDecList_arr = new ArrayList<FieldDeclaration>();

                for (FieldDeclaration mfield : fieldDecList) {
                    fieldDecList_arr.add(mfield);
                }
                fieldDecList_arr.sort(FieldDeclaration_Compare);

                for (int i = 0; i < fieldDecList_arr.size(); i++) {
                    FieldDeclaration fieldItem = fieldDecList_arr.get(i);
                    fieldItem.isTransient();


                    boolean isStaticField = fieldItem.isStatic();
                    boolean isFinalField = fieldItem.isFinal();
                    boolean isVolatileField = fieldItem.isVolatile();
                    fieldItem.isPrivate();
                    fieldItem.isProtected();
                    fieldItem.isPublic();

                    String fieldModifyStr = "";

                    if (isStaticField) {
                        fieldModifyStr = fieldModifyStr + " static ";
                    }

                    if (isFinalField) {
                        fieldModifyStr = fieldModifyStr + " final ";
                    }

                    if (isVolatileField) {
                        fieldModifyStr = fieldModifyStr + " volatile ";
                    }
                    fieldModifyStr = fieldModifyStr.trim();

                    String metaModelFieldName = fieldItem.getMetaModel().getMetaModelFieldName();
                    String mQualifiedClassName = fieldItem.getMetaModel().getQualifiedClassName();
                    String mTypeNam = fieldItem.getMetaModel().getTypeName();
                    String mTypeNameGenerified = fieldItem.getMetaModel().getTypeNameGenerified();
                    String getMetaModelString = fieldItem.getMetaModel().toString();


                    PrettyPrinterConfiguration mPrintConfig = new PrettyPrinterConfiguration();
                    mPrintConfig.setPrintComments(false);
                    fieldItem.toString(mPrintConfig);
                    String mTypeAndName = fieldItem.toString();


                    // 去掉注释
                    while (mTypeAndName.contains("/*") && mTypeAndName.trim().startsWith("/*") && mTypeAndName.contains("*/")) {
                        mTypeAndName = mTypeAndName.substring(mTypeAndName.indexOf("*/") + 2).trim();
                    }

                    // 去掉解析出来的 静态 = 等于后面的详细内容
                    if (mTypeAndName.trim().startsWith("//")) {
                        mTypeAndName = mTypeAndName.substring( mTypeAndName.indexOf("\n")+1).trim();
                    }
                    // 去掉前面的空格 以及 换行符号
                    while (mTypeAndName.contains("\n")) {
                        mTypeAndName = mTypeAndName.substring(mTypeAndName.indexOf("\n") + 1).trim();
                    }
                    //   System.out.println("属性索引"+i+":"+" "+mTypeAndName);


                    if(mTypeAndName.length() > MAX_COUNT_CHAR_IN_ROW){
                        mTypeAndName = mTypeAndName.substring(0,MAX_COUNT_CHAR_IN_ROW-30);
                    }
                    arrMemberList.add("属性索引"+i+":"+" "+mTypeAndName);

                }




            }




        } catch (FileNotFoundException e) {
            //     System.out.println("X Exception ");

            e.printStackTrace();
        }

        //  System.out.println("X end  X");

        return arrMemberList;
    }

    @SuppressWarnings("unchecked")
    static Comparator FieldDeclaration_Compare =  new Comparator<FieldDeclaration>() {
        @Override
        public int compare(FieldDeclaration o1, FieldDeclaration o2) {

            if (o1 == o2) {
                return 0;
            }
            boolean isStaticField1 = o1.isStatic();
            boolean isFinalField1 = o1.isFinal();
            boolean isVolatileField1 = o1.isVolatile();

            boolean isStaticField2 = o2.isStatic();
            boolean isFinalField2 = o2.isFinal();
            boolean isVolatileField2 = o2.isVolatile();

            if (isStaticField1 != isStaticField2) {
                if (isStaticField1) {
                    return 1;
                }
                if (isStaticField2) {
                    return -1;
                }
            }


            if (isFinalField1 != isFinalField2) {
                if (isFinalField1) {
                    return 1;
                }
                if (isFinalField2) {
                    return -1;
                }
            }


            if (isVolatileField1 != isVolatileField2) {
                if (isVolatileField1) {
                    return 1;
                }
                if (isVolatileField2) {
                    return -1;
                }
            }

            return 0;

        }
    };

    @SuppressWarnings("unchecked")
    static Comparator FieldMethod_Compare =  new Comparator<MethodDeclaration>() {
        @Override
        public int compare(MethodDeclaration o1, MethodDeclaration o2) {


            if (o1 == o2) {
                return 0;
            }

            boolean isStaticMethod1 = o1.isStatic();
            boolean isNativeMethod1 = o1.isNative();
            boolean isSyncMethod1 = o1.isSynchronized();
            boolean isAbstractMethod1 = o1.isAbstract();
            boolean isFinalMethod1 = o1.isFinal();


            boolean isStaticMethod2 = o2.isStatic();
            boolean isNativeMethod2 = o2.isNative();
            boolean isSyncMethod2 = o2.isSynchronized();
            boolean isAbstractMethod2 = o2.isAbstract();
            boolean isFinalMethod2 = o2.isFinal();

            if (isStaticMethod1 != isStaticMethod2) {
                if (isStaticMethod1) {
                    return 1;
                }
                if (isStaticMethod2) {
                    return -1;
                }
            }


            if (isNativeMethod1 != isNativeMethod2) {
                if (isNativeMethod1) {
                    return 1;
                }
                if (isNativeMethod2) {
                    return -1;
                }
            }

            if (isSyncMethod1 != isSyncMethod2) {
                if (isSyncMethod1) {
                    return 1;
                }
                if (isSyncMethod2) {
                    return -1;
                }
            }


            if (isAbstractMethod1 != isAbstractMethod2) {
                if (isAbstractMethod1) {
                    return 1;
                }
                if (isAbstractMethod2) {
                    return -1;
                }
            }


            if (isFinalMethod1 != isFinalMethod2) {
                if (isFinalMethod1) {
                    return 1;
                }
                if (isFinalMethod2) {
                    return -1;
                }
            }

            return 0;
        }
    };
}

