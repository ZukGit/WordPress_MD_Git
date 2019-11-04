import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
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

        AOSP_Version_ITEM android_9_0 = new AOSP_Version_ITEM(9.0, "PIE", 28, "9.0.0_r8", "2018.03");
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

/********************************************【 APP 】********************************************/
//WifiSettings.java
        FilePath_ITEM WifiSettings = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java");
        allFileList.add(WifiSettings);


        //WifiTetherPreferenceController.java
        FilePath_ITEM WifiTetherPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherPreferenceController.java");
        allFileList.add(WifiTetherPreferenceController);


        //WifiConfigController.java
        FilePath_ITEM WifiConfigController = new FilePath_ITEM(" /packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java");
        allFileList.add(WifiConfigController);


        //TetherService.java
        FilePath_ITEM TetherService = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/TetherService.java");
        allFileList.add(TetherService);


        //WifiTetherSoftApManager.java
        FilePath_ITEM WifiTetherSoftApManager = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSoftApManager.java");
        allFileList.add(WifiTetherSoftApManager);


        //WifiP2pPreferenceController.java
        FilePath_ITEM WifiP2pPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pPreferenceController.java");
        allFileList.add(WifiP2pPreferenceController);


        //WifiTetherSSIDPreferenceController.java
        FilePath_ITEM WifiTetherSSIDPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSSIDPreferenceController.java");
        allFileList.add(WifiTetherSSIDPreferenceController);


        //WifiTetherApBandPreferenceController.java
        FilePath_ITEM WifiTetherApBandPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherApBandPreferenceController.java");
        allFileList.add(WifiTetherApBandPreferenceController);


        //WifiInfoPreferenceController.java
        FilePath_ITEM WifiInfoPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiInfoPreferenceController.java");
        allFileList.add(WifiInfoPreferenceController);


        //WifiTetherSettings.java
        FilePath_ITEM WifiTetherSettings = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherSettings.java");
        allFileList.add(WifiTetherSettings);


        //WifiP2pSettings.java
        FilePath_ITEM WifiP2pSettings = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pSettings.java");
        allFileList.add(WifiP2pSettings);


        //WifiEnabler.java
        FilePath_ITEM WifiEnabler = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java");
        allFileList.add(WifiEnabler);


        //SavedAccessPointsWifiSettings.java
        FilePath_ITEM SavedAccessPointsWifiSettings = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java");
        allFileList.add(SavedAccessPointsWifiSettings);


        //WifiTetherPasswordPreferenceController.java
        FilePath_ITEM WifiTetherPasswordPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/tether/WifiTetherPasswordPreferenceController.java");
        allFileList.add(WifiTetherPasswordPreferenceController);


        //WifiSignalController.java
        FilePath_ITEM WifiSignalController = new FilePath_ITEM("/frameworks/base/packages/SystemUI/src/com/android/systemui/statusbar/policy/WifiSignalController.java");
        allFileList.add(WifiSignalController);


        //WifiDetailPreferenceController.java
        FilePath_ITEM WifiDetailPreferenceController = new FilePath_ITEM("/packages/apps/Settings/src/com/android/settings/wifi/details/WifiDetailPreferenceController.java");
        allFileList.add(WifiDetailPreferenceController);


/********************************************【 Framework/base 】********************************************/
        //WifiManager.java
        FilePath_ITEM WifiManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiManager.java");
        allFileList.add(WifiManager);


        //WifiScanner.java
        FilePath_ITEM WifiScanner = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiScanner.java");
        allFileList.add(WifiScanner);

        //IWifiRttManager.aidl
        FilePath_ITEM IWifiRttManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/rtt/IWifiRttManager.aidl");
        allFileList.add(IWifiRttManager);

        //WifiRttManager.java
        FilePath_ITEM WifiRttManager = new FilePath_ITEM(" /frameworks/base/wifi/java/android/net/wifi/rtt/WifiRttManager.java");
        allFileList.add(WifiRttManager);


        //WifiP2pConfig.java
        FilePath_ITEM WifiP2pConfig = new FilePath_ITEM(" /frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pConfig.java");
        allFileList.add(WifiP2pConfig);


        //WifiP2pDevice.java
        FilePath_ITEM WifiP2pDevice = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pDevice.java");
        allFileList.add(WifiP2pDevice);


        //WifiP2pGroup.java
        FilePath_ITEM WifiP2pGroup = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pGroup.java");
        allFileList.add(WifiP2pGroup);


        //WifiP2pInfo.java
        FilePath_ITEM WifiP2pInfo = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pInfo.java");
        allFileList.add(WifiP2pInfo);


        //WifiP2pManager.java
        FilePath_ITEM WifiP2pManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/p2p/WifiP2pManager.java");
        allFileList.add(WifiP2pManager);


        //IWifiAwareManager.aidl
        FilePath_ITEM IWifiAwareManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/IWifiAwareManager.aidl");
        allFileList.add(IWifiAwareManager);


        //WifiAwareManager.java
        FilePath_ITEM WifiAwareManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/aware/WifiAwareManager.java");
        allFileList.add(WifiAwareManager);


        //PasspointConfiguration.java
        FilePath_ITEM PasspointConfiguration = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/hotspot2/PasspointConfiguration.java");
        allFileList.add(PasspointConfiguration);

        //WifiConfiguration.java
        FilePath_ITEM WifiConfiguration = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiConfiguration.java");
        allFileList.add(WifiConfiguration);


        //WifiEnterpriseConfig.java
        FilePath_ITEM WifiEnterpriseConfig = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiEnterpriseConfig.java");
        allFileList.add(WifiEnterpriseConfig);


        //IWifiManager.aidl
        FilePath_ITEM IWifiManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/IWifiManager.aidl");
        allFileList.add(IWifiManager);


        //RttManager.java
        FilePath_ITEM RttManager = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/RttManager.java");
        allFileList.add(RttManager);


        //ScanResult.java
        FilePath_ITEM ScanResult = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/ScanResult.java");
        allFileList.add(ScanResult);


        //NetworkAgentInfo.java
        FilePath_ITEM NetworkAgentInfo = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/connectivity/NetworkAgentInfo.java");
        allFileList.add(NetworkAgentInfo);


        //NetworkMonitor.java
        FilePath_ITEM NetworkMonitor = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/connectivity/NetworkMonitor.java");
        allFileList.add(NetworkMonitor);


        //Tethering.java
        FilePath_ITEM Tethering = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/connectivity/Tethering.java");
        allFileList.add(Tethering);


        //WifiDisplayAdapter.java
        FilePath_ITEM WifiDisplayAdapter = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/display/WifiDisplayAdapter.java");
        allFileList.add(WifiDisplayAdapter);


        //WifiDisplayController.java
        FilePath_ITEM WifiDisplayController = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/display/WifiDisplayController.java");
        allFileList.add(WifiDisplayController);


        //SystemServer.java
        FilePath_ITEM SystemServer = new FilePath_ITEM("/frameworks/base/services/java/com/android/server/SystemServer.java");
        allFileList.add(SystemServer);


        //ConnectivityService.java
        FilePath_ITEM ConnectivityService = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/ConnectivityService.java");
        allFileList.add(ConnectivityService);


        //NetworkManagementService.java
        FilePath_ITEM NetworkManagementService = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/NetworkManagementService.java");
        allFileList.add(NetworkManagementService);

        //NetworkScoreService.java
        FilePath_ITEM NetworkScoreService = new FilePath_ITEM("/frameworks/base/services/core/java/com/android/server/NetworkScoreService.java");
        allFileList.add(NetworkScoreService);


        //AccessPoint.java
        FilePath_ITEM AccessPoint = new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java");
        allFileList.add(AccessPoint);


        //AccessPointPreference.java
        FilePath_ITEM AccessPointPreference = new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java");
        allFileList.add(AccessPointPreference);


        //WifiTracker.java
        FilePath_ITEM WifiTracker = new FilePath_ITEM("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiTracker.java");
        allFileList.add(WifiTracker);


        //SettingsProvider.java
        FilePath_ITEM SettingsProvider = new FilePath_ITEM(" /frameworks/base/packages/SettingsProvider/src/com/android/providers/settings/SettingsProvider.java");
        allFileList.add(SettingsProvider);


        //ConnectivityManager.java
        FilePath_ITEM ConnectivityManager = new FilePath_ITEM(" /frameworks/base/core/java/android/net/ConnectivityManager.java");
        allFileList.add(ConnectivityManager);


        //NetworkAgent.java
        FilePath_ITEM NetworkAgent = new FilePath_ITEM(" /frameworks/base/core/java/android/net/NetworkAgent.java");
        allFileList.add(NetworkAgent);


        //NetworkCapabilities.java
        FilePath_ITEM NetworkCapabilities = new FilePath_ITEM(" /frameworks/base/core/java/android/net/NetworkCapabilities.java");
        allFileList.add(NetworkCapabilities);


        //NetworkIdentity.java
        FilePath_ITEM NetworkIdentity = new FilePath_ITEM(" /frameworks/base/core/java/android/net/NetworkIdentity.java");
        allFileList.add(NetworkIdentity);


        //NetworkScoreManager.java
        FilePath_ITEM NetworkScoreManager = new FilePath_ITEM("/frameworks/base/core/java/android/net/NetworkScoreManager.java");
        allFileList.add(NetworkScoreManager);


        //VpnService.java
        FilePath_ITEM VpnService = new FilePath_ITEM("/frameworks/base/core/java/android/net/VpnService.java");
        allFileList.add(VpnService);


        //NfcAdapter.java
        FilePath_ITEM NfcAdapter = new FilePath_ITEM(" /frameworks/base/core/java/android/nfc/NfcAdapter.java");
        allFileList.add(NfcAdapter);

        //Intent.java
        FilePath_ITEM Intent = new FilePath_ITEM("/frameworks/base/core/java/android/content/Intent.java");
        allFileList.add(Intent);


        //Context.java
        FilePath_ITEM Context = new FilePath_ITEM("/frameworks/base/core/java/android/content/Context.java");
        allFileList.add(Context);


        //PackageManager.java
        FilePath_ITEM PackageManager = new FilePath_ITEM("/frameworks/base/core/java/android/content/pm/PackageManager.java");
        allFileList.add(PackageManager);


        //WifiDisplay.java
        FilePath_ITEM WifiDisplay = new FilePath_ITEM("/frameworks/base/core/java/android/hardware/display/WifiDisplay.java");
        allFileList.add(WifiDisplay);


/********************************************【 Framework/opt 】********************************************/

        //WifiService.java
        FilePath_ITEM WifiService = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiService.java");
        allFileList.add(WifiService);


        //WifiController.java
        FilePath_ITEM WifiController = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiController.java");
        allFileList.add(WifiController);


        //AvailableNetworkNotifier.java
        FilePath_ITEM AvailableNetworkNotifier = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/AvailableNetworkNotifier.java");
        allFileList.add(AvailableNetworkNotifier);


        //SoftApManager.java
        FilePath_ITEM SoftApManager = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SoftApManager.java");
        allFileList.add(SoftApManager);


        //WifiP2pService.java
        FilePath_ITEM WifiP2pService = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pService.java");
        allFileList.add(WifiP2pService);

        //PasspointNetworkEvaluator.java
        FilePath_ITEM PasspointNetworkEvaluator = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointNetworkEvaluator.java");
        allFileList.add(PasspointNetworkEvaluator);


        //PasspointNetworkEvaluator.java
        FilePath_ITEM PasspointProvider = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointProvider.java");
        allFileList.add(PasspointProvider);


        //WifiAwareService.java
        FilePath_ITEM WifiAwareService = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareService.java");
        allFileList.add(WifiAwareService);


        //SupplicantP2pIfaceCallback.java
        FilePath_ITEM SupplicantP2pIfaceCallback = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/SupplicantP2pIfaceCallback.java");
        allFileList.add(SupplicantP2pIfaceCallback);

        //WificondControl.java
        FilePath_ITEM WificondControl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WificondControl.java");
        allFileList.add(WificondControl);


        //WifiLockManager.java
        FilePath_ITEM WifiLockManager = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiLockManager.java");
        allFileList.add(WifiLockManager);


        //SupplicantStaIfaceHal.java
        FilePath_ITEM SupplicantStaIfaceHal = new FilePath_ITEM(" /frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantStaIfaceHal.java");
        allFileList.add(SupplicantStaIfaceHal);


        //WifiMonitor.java
        FilePath_ITEM WifiMonitor = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiMonitor.java");
        allFileList.add(WifiMonitor);


        //WificondScannerImpl.java
        FilePath_ITEM WificondScannerImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WificondScannerImpl.java");
        allFileList.add(WificondScannerImpl);


        //WifiServiceImpl.java
        FilePath_ITEM WifiServiceImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java");
        allFileList.add(WifiServiceImpl);


        //WifiP2pMonitor.java
        FilePath_ITEM WifiP2pMonitor = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pMonitor.java");
        allFileList.add(WifiP2pMonitor);


        //WifiP2pNative.java
        FilePath_ITEM WifiP2pNative = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pNative.java");
        allFileList.add(WifiP2pNative);


        //WifiP2pServiceImpl.java
        FilePath_ITEM WifiP2pServiceImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/p2p/WifiP2pServiceImpl.java");
        allFileList.add(WifiP2pServiceImpl);


        //RttMetrics.java
        FilePath_ITEM RttMetrics = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttMetrics.java");
        allFileList.add(RttMetrics);


        //RttNative.java
        FilePath_ITEM RttNative = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttNative.java");
        allFileList.add(RttNative);


        //RttService.java
        FilePath_ITEM RttService = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttService.java");
        allFileList.add(RttService);


        //RttServiceImpl.java
        FilePath_ITEM RttServiceImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/rtt/RttServiceImpl.java");
        allFileList.add(RttServiceImpl);


        //BackgroundScanScheduler.java
        FilePath_ITEM BackgroundScanScheduler = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/BackgroundScanScheduler.java");
        allFileList.add(BackgroundScanScheduler);


        //WifiScannerImpl.java
        FilePath_ITEM WifiScannerImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WifiScannerImpl.java");
        allFileList.add(WifiScannerImpl);


        //WifiScanningServiceImpl.java
        FilePath_ITEM WifiScanningServiceImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/scanner/WifiScanningServiceImpl.java");
        allFileList.add(WifiScanningServiceImpl);


        //NetworkDetail.java
        FilePath_ITEM NetworkDetail = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/NetworkDetail.java");
        allFileList.add(NetworkDetail);


        //PasspointManager.java
        FilePath_ITEM PasspointManager = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/hotspot2/PasspointManager.java");
        allFileList.add(PasspointManager);


        //WrongPasswordNotifier.java
        FilePath_ITEM WrongPasswordNotifier = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WrongPasswordNotifier.java");
        allFileList.add(WrongPasswordNotifier);


        //WifiAwareServiceImpl.java
        FilePath_ITEM WifiAwareServiceImpl = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareServiceImpl.java");
        allFileList.add(WifiAwareServiceImpl);


        //WifiAwareStateManager.java
        FilePath_ITEM WifiAwareStateManager = new FilePath_ITEM(" /frameworks/opt/net/wifi/service/java/com/android/server/wifi/aware/WifiAwareStateManager.java");
        allFileList.add(WifiAwareStateManager);


        //WifiCountryCode.java
        FilePath_ITEM WifiCountryCode = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiCountryCode.java");
        allFileList.add(WifiCountryCode);


        //WifiInjector.java
        FilePath_ITEM WifiInjector = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiInjector.java");
        allFileList.add(WifiInjector);


        //WifiNetworkSelector.java
        FilePath_ITEM WifiNetworkSelector = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNetworkSelector.java");
        allFileList.add(WifiNetworkSelector);


        //WifiNetworkSelector.java
        FilePath_ITEM WifiSettingsStore = new FilePath_ITEM(" /frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiSettingsStore.java");
        allFileList.add(WifiSettingsStore);


        //WifiStateMachine.java
        FilePath_ITEM WifiStateMachine = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java");
        allFileList.add(WifiStateMachine);


        //WifiStateMachinePrime.java
        FilePath_ITEM WifiStateMachinePrime = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachinePrime.java");
        allFileList.add(WifiStateMachinePrime);


        //SupplicantStaNetworkHal.java
        FilePath_ITEM SupplicantStaNetworkHal = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SupplicantStaNetworkHal.java");
        allFileList.add(SupplicantStaNetworkHal);


        //WifiApConfigStore.java
        FilePath_ITEM WifiApConfigStore = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiApConfigStore.java");
        allFileList.add(WifiApConfigStore);


        //WifiConfigManager.java
        FilePath_ITEM WifiConfigManager = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigManager.java");
        allFileList.add(WifiConfigManager);


        //WifiConfigStore.java
        FilePath_ITEM WifiConfigStore = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConfigStore.java");
        allFileList.add(WifiConfigStore);


        //WifiConnectivityManager.java
        FilePath_ITEM WifiConnectivityManager = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiConnectivityManager.java");
        allFileList.add(WifiConnectivityManager);


        //HostapdHal.java
        FilePath_ITEM HostapdHal = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/HostapdHal.java");
        allFileList.add(HostapdHal);


        //ScanDetail.java
        FilePath_ITEM ScanDetail = new FilePath_ITEM(" /frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanDetail.java");
        allFileList.add(ScanDetail);

        //ScanResultMatchInfo.java
        FilePath_ITEM ScanResultMatchInfo = new FilePath_ITEM("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ScanResultMatchInfo.java");
        allFileList.add(ScanResultMatchInfo);


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

