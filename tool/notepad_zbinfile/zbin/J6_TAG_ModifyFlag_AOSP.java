import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//if()       这种情况 只有一句代码  没有  括号 将导致 问题
//else if()
//else()

// toNewClassCode  839---1020 行
// tryAnalysisExpressNodeString   883 被调用  zprint
// fixnewMethodCode  970被调用      this_is_indexLog_index

//Node fatherNode = node.getParentNode().get();
//if(fatherNode != null && fatherNode.getMetaModel().getType() == com.github.javaparser.ast.stmt.IfStmt.class && fatherNode.getChildNodes().size() == 2)
//


// 方法的头部有多行注释

public class J6_TAG_ModifyFlag_AOSP {
    static ArrayList<ZClass> ZClassList = new ArrayList<ZClass>();
    static String curProjectPath = System.getProperty("user.dir");
    static String TAG = "zukgit";



    static  String WifiEnabler_PATH = "/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";

    static ArrayList<String> Path_StrList = new  ArrayList<String>();

    //  添加 对应 AOSP   初始化 TAG的 路径
    static {
        Path_StrList.add(WifiEnabler_PATH);


    }

//════════════════════════════════════=== IDEClass Begin ════════════════════════════════════=
/*    static {    //   测试使用
        String zClassName = curProjectPath+"/B8_Test.java";
        ZIDEClass zClassObject = new ZIDEClass(zClassName);
        ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
        methodList.add(new ZMethod(zClassObject,"void","method1","int value"));
        methodList.add(new ZMethod(zClassObject,"void","method2","int[] value"));
        methodList.add(new ZMethod(zClassObject,"void","method3","String[]  strArr"));
        methodList.add(new ZMethod(zClassObject,"void","method4","ArrayList<Date> dateList"));
        methodList.add(new ZMethod(zClassObject,"void","method5","Map<String,String> stringMap"));
        zClassObject.addZMethod(methodList);
        ZClassList.add(zClassObject);
    }*/
//════════════════════════════════════=== IDEClass End ════════════════════════════════════=

//════════════════════════════════════=== APPClass Begin ════════════════════════════════════=

    // ##/packages/apps/Settings
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓ /packages/apps/Settings Begin ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    static void WifiEnabler() {
        String mZAndroidAPPClass_Path = curProjectPath + "/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
        ZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(mZAndroidAPPClass_Path);
        ArrayList<ZMethod> methodList = new ArrayList<ZMethod>();
        //boolean  onSwitchToggled(boolean isChecked)
        methodList.add(new ZMethod(mZAndroidAPPClass, "boolean", "onSwitchToggled", "boolean isChecked "));
        mZAndroidAPPClass.addZMethod(methodList);
        ZClassList.add(mZAndroidAPPClass);

    }


    static void WifiConfigInfo() {
        String mZAndroidAPPClass_Path = curProjectPath + "/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigInfo.java";
        ZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(mZAndroidAPPClass_Path);
        ArrayList<ZMethod> methodList = new ArrayList<ZMethod>();
        // void onResume()     void onCreate(Bundle savedInstanceState)
        methodList.add(new ZMethod(mZAndroidAPPClass, "void", "onResume", " "));
        methodList.add(new ZMethod(mZAndroidAPPClass, "void", "onCreate", " Bundle savedInstanceState "));
        mZAndroidAPPClass.addZMethod(methodList);
        ZClassList.add(mZAndroidAPPClass);

    }


    static void WifiSettings() {
        String mZAndroidAPPClass_Path = curProjectPath + "/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java";
        ZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(mZAndroidAPPClass_Path);
        ArrayList<ZMethod> methodList = new ArrayList<ZMethod>();
        // void showDialog(AccessPoint accessPoint, int dialogMode)
        methodList.add(new ZMethod(mZAndroidAPPClass, "void", "showDialog", "AccessPoint accessPoint, int dialogMode"));
        // boolean onPreferenceTreeClick(Preference preference)
        methodList.add(new ZMethod(mZAndroidAPPClass, "boolean", "onPreferenceTreeClick", "Preference preference "));
        // void onWifiStateChanged(int state)
        methodList.add(new ZMethod(mZAndroidAPPClass, "void", "onWifiStateChanged", "int state "));
        // void updateAccessPointsDelayed()
        methodList.add(new ZMethod(mZAndroidAPPClass, "void", "updateAccessPointsDelayed", " "));
        //  Dialog onCreateDialog(int dialogId)
        methodList.add(new ZMethod(mZAndroidAPPClass, "Dialog", "onCreateDialog", " int dialogId "));
        mZAndroidAPPClass.addZMethod(methodList);
        ZClassList.add(mZAndroidAPPClass);

    }


//↑ ↑↑↑↑↑↑↑↑↑↑↑↑/packages/apps/Settings End ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


//════════════════════════════════════=== APPClass End ════════════════════════════════════===


//════════════════════════════════════=== FrameworkClass Begin ════════════════════════════════════=
// ##/frameworks/base/core
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓ /frameworks/base/core Begin ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

/*
static {
    String mZFrameworkClass_Path = curProjectPath+"/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
    ZAndroidFrameworkClass mFrameworkClass = new ZAndroidFrameworkClass(mZFrameworkClass_Path);
    ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
    //boolean  onSwitchToggled(boolean isChecked)
    methodList.add(new ZMethod(mFrameworkClass,"boolean","onSwitchToggled","boolean isChecked "));
    mFrameworkClass.addZMethod(methodList);
    ZClassList.add(mFrameworkClass);
}
*/

    static void InitZClass() {
        WifiEnabler();
        WifiConfigInfo();
        WifiSettings();
    }
//↑ ↑↑↑↑↑↑↑↑↑↑↑↑/frameworks/base/core  End ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//════════════════════════════════════=== FrameworkClass End ════════════════════════════════════===

    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static String BAT_SH_EndType ;
    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
            BAT_SH_EndType = ".bat";
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
            BAT_SH_EndType = ".sh";
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
            BAT_SH_EndType = ".sh";
        }
    }

    static ArrayList<String> mKeyWordName = new ArrayList<String>();

    static ArrayList<ZClass> ZParamClassList = new ArrayList<ZClass>();

    @SuppressWarnings("unchecked")
    static void NodeMapPrint(Map<String, ArrayList<Node>> sameNodeMap) {
        Map.Entry<String, ArrayList<Node>> entry;
        if (sameNodeMap != null) {
            Iterator iterator = sameNodeMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<Node>>) iterator.next();
                String key = entry.getKey();  //Map的Value
                ArrayList<Node> sameNodeList = entry.getValue();  //Map的Value
                System.out.println("════════Same Node Begin════════");
                for (int i = 0; i < sameNodeList.size(); i++) {

                    Node sameNodeitem = sameNodeList.get(i);
                    String indexIdentify = calculIndexFlag(sameNodeitem);
                    System.out.println(" 相同Node 索引:" + i + " Content = " + sameNodeitem + "indexIdentify = " + indexIdentify + "   FatherType = " + sameNodeitem.getParentNode().get().getMetaModel().getType());

                }
                System.out.println("════════Same Node End════════");
            }
        }


    }


    static String calculIndexFlag(Node curNode) {
        String indexFlag = "";
        if (curNode != null && curNode.getMetaModel().getType() == MethodDeclaration.class) {
            return "[0]";
        } else {  // 把自己在
            List<Node> curChildList = curNode.getParentNode().get().getChildNodes();
            for (int i = 0; i < curChildList.size(); i++) {
                if (curNode == curChildList.get(i)) {
                    indexFlag = "[" + i + "]";
                    break;
                }
            }
        }
        indexFlag = calculIndexFlag(curNode.getParentNode().get()) + indexFlag;
        return indexFlag;
    }
    static String  CUR_Dir_1_PATH ;

    static File AOSP_Project_Root_DirFile;


    static  File initAOSPRootPath(String path){
        File curDir = new File(path);
        if(!curDir.exists()){
            return null;
        }
        while(curDir != null && curDir.exists()){

            String curDirName = curDir.getName().toLowerCase();
            System.out.println("curDirName  = "+ curDirName);
            if(curDirName.startsWith("code")){
                AOSP_Project_Root_DirFile = curDir;
                break;
            }
            curDir = curDir.getParentFile();
            if(curDir == null){
                break;
            }

        }
        if(AOSP_Project_Root_DirFile == null){
            System.out.println("当前没有搜索到 代码工程 根 目录  CodeXXX  请查查路径!  CUR_Dir_1_PATH = "+ CUR_Dir_1_PATH  );
        }
        return AOSP_Project_Root_DirFile;
    }




    public static void main(String[] args) throws IOException {
        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);

                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                }
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        // zlogadd_B8.sh   xxxxxx.java
        // zlogadd_B8.sh   /xxxxxx/dir/
        // zlogadd_B8.sh   xxxxxxA.java  /xxxxxx/dirA/  /xxxxxx/dirB/ xxxxxxB.java
        File AOSP_Project =  initAOSPRootPath(CUR_Dir_1_PATH);

        if (AOSP_Project_Root_DirFile != null && AOSP_Project != null) {   //  如果当前路径是AOSP路径的话
            ArrayList<File> javaFilePathParamList = new ArrayList<File>();
//                ArrayList<File> javaDirPathParamList = new ArrayList<File>();

//                javaFilePathParamList.addAll(initJavaFileFromParam(mKeyWordName));
//                javaDirPathParamList.addAll(initJavaDirFromParam(mKeyWordName));

            for (int i = 0; i < Path_StrList.size(); i++) {
                String relativepath = Path_StrList.get(i);
                File absFile = new File(AOSP_Project_Root_DirFile.getAbsolutePath()+File.separator+relativepath);
                if(absFile.exists()){
                    javaFilePathParamList.add(absFile);
                }
            }
            System.out.println("参数 Java文件个数:" + javaFilePathParamList.size());
            for (int i = 0; i < javaFilePathParamList.size() ; i++) {
                System.out.println("JavaFile["+i+"]  = " + javaFilePathParamList.get(i).getAbsolutePath() );
            }



//                System.out.println("参数 Java文件夹个数:" + javaDirPathParamList.size());

            for (int i = 0; i < javaFilePathParamList.size(); i++) {
                // 尝试添加 开始操作 .java 文件
                File javaFile = javaFilePathParamList.get(i);
                System.out.println();
                System.out.println("文件索引" + i + ":Begin══════════════════════════");
                System.out.println("文件PATH:" + javaFile.getAbsolutePath());
                ZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(javaFile.getAbsolutePath());
                mZAndroidAPPClass.InitZmethodFromJavaFile(javaFile, mZAndroidAPPClass);
                System.out.println("文件索引" + i + ":  End══════════════════════════");
                System.out.println();
                if(!mZAndroidAPPClass.isInterfaceFile){
                    ZParamClassList.add(mZAndroidAPPClass);
                }

            }
/*

                for (int i = 0; i < javaDirPathParamList.size(); i++) {
                    // 对当前文件夹下的所有 java 文件进行搜索  并添加到 一个集合

                    initDirFileSet(javaDirPathParamList.get(i));  //
                }
                initSimpleFileSetDetail();*/

/*                ArrayList<File> allJavaFileInDirList = new ArrayList<File>();
//                allJavaFileInDirList.addAll(allSimpleJavaFileSet);

                System.out.println("搜索出的java文件总数: " + allJavaFileInDirList.size());
                showJavaFile(allJavaFileInDirList);
                int javaFileCount = allJavaFileInDirList.size();
                for (int i = 0; i < allJavaFileInDirList.size(); i++) {
                    // 尝试添加 开始操作 .java 文件
                    File javaFile = allJavaFileInDirList.get(i);
                    System.out.println();
                    System.out.println("文件创建 ZCLASS 索引 " + i + "- 总长度:" + javaFileCount + " Begin══════════════════════════");
                    System.out.println("文件PATH:" + javaFile.getAbsolutePath());
                    ZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(javaFile.getAbsolutePath());
                    mZAndroidAPPClass.InitZmethodFromJavaFile(javaFile, mZAndroidAPPClass);
                    System.out.println("文件创建 ZCLASS 索引 " + i + "- 总长度:" + javaFileCount + "   End══════════════════════════");
                    System.out.println();
                    if(!mZAndroidAPPClass.isInterfaceFile){
                        ZParamClassList.add(mZAndroidAPPClass);
                    }

                }*/

            tryAddLogFlag();





        }


    }

    static void showJavaFile(ArrayList<File> javaFileList) {

        for (int i = 0; i < javaFileList.size(); i++) {
            File itemFile = javaFileList.get(i);
            System.out.println("第 [ " + i + " ] 个java文件:  " + itemFile.getAbsolutePath());
        }

    }


    static void tryAddLogFlag() {

        int classSize = ZParamClassList.size();
        System.out.println(" 总共需要解析文件的个数  classSize = " + classSize);

        String newClassCode = "";  //
        String originClassCode = "";
        String formatNewClassCode = "";
        for (int i = 0; i < classSize; i++) {
            System.out.println("══════ 开始解析第" + i + "个文件 总文件:" + classSize + "     文件名为: " + ZParamClassList.get(i).className + "══════");
            newClassCode = ZParamClassList.get(i).toNewClassCode();  //  10037
            if(newClassCode == null || "".equals(newClassCode)){
                continue;
            }
            // zukgit1_log  zukgitlog_1 zukgit_log_1
            System.out.println("══════ 新构建的类文件的内容 如下: newClassCode : \n" + newClassCode);  // 构建新的方法的内容

            if (JavaParser.parse(newClassCode).getClassByName(ZParamClassList.get(i).className) == null ||
                    !JavaParser.parse(newClassCode).getClassByName(ZParamClassList.get(i).className).isPresent()) {
                System.out.println("当前的文件不是一个Class 可能是public enum , public interface  接口或枚举!");
                System.out.println(ZParamClassList.get(i).classFile.getAbsolutePath());

                continue;
            } else {
                System.out.println(" ["+i+"] = " + ZParamClassList.get(i).classFile.getAbsolutePath());
            }


            //  在newClassCode中加入新的Log方法   找到最后一个 { 的位置
            int lastBlock = newClassCode.lastIndexOf("}");
            //       System.out.println(" 最后{ 的位置 " +lastBlock );
            String newClassCodeFix = "";

            if (!ZParamClassList.get(i).alreadyAddPrintMethod) {
                if (ZParamClassList.get(i).isIDEClass) {
                    //  newClassCodeFix = newClassCode.substring(0,lastBlock)+ "    " +IDEA_PRINT_METHOD + "}";
                    newClassCodeFix = newClassCode.substring(0, lastBlock) + "    " + ZParamClassList.get(i).GetRealPrintMethod() + "}";
                } else if (ZParamClassList.get(i).isAPP) {
                    //      newClassCodeFix = newClassCode.substring(0,lastBlock) + "    " +APP_PRINT_METHOD + "}";
                    newClassCodeFix = newClassCode.substring(0, lastBlock) + "    " + ZParamClassList.get(i).GetRealPrintMethod() + "}";
                } else if (ZParamClassList.get(i).isFramework) {
                    //  newClassCodeFix = newClassCode.substring(0,lastBlock) + "    " +Framework_PRINT_METHOD + "}";
                    newClassCodeFix = newClassCode.substring(0, lastBlock) + "    " + ZParamClassList.get(i).GetRealPrintMethod() + "}";
                }

            } else {  // 已经 添加过 MethodPrint的 类
                newClassCodeFix = newClassCode;
                newClassCodeFix = newClassCodeFix.replaceAll("timeStamp=","timeStamp="+getTimeStamp()+"   ");
            }


            //    System.out.println("  添加 打印Log 的 字符串 newClassCodeFix  \n " +newClassCodeFix );
            formatNewClassCode = JavaParser.parse(newClassCodeFix).toString();
            //  System.out.println("═══════  格式化文件 如下: formatNewClassCode :" + formatNewClassCode);
            // 把当前的新构建的文件重新写入 .java 文件
            WriteToFile(ZParamClassList.get(i).classFile, formatNewClassCode);
            System.out.println(" zzfile_3"+BAT_SH_EndType+"  " + ZParamClassList.get(i).classFile.getAbsolutePath());

        }
    }

    static void initSimpleFileSetDetail() {
        int fileIndex = 1;
        System.out.println();
        System.out.println();
        for (File dirFile : allDirFileSet) {
            System.out.println("文件夹索引index=" + fileIndex + "   PATH: " + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory() && childFileList[i].getName().endsWith(".java")) {
                        allSimpleJavaFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }

//class ZMethod
//class ZClass
//class ZProcess
//  1. 方法包含在类中    类包含在流程中
// 2. 多个流程会包含 同一个类
// 3. 以类为单位进行划分   流程指定自身需要的该类的哪个ZMethod(指定 methodIdentify)

    static Set<File> allDirFileSet = new HashSet<File>();  // 工程下所有的 文件夹文件
    static Set<File> allSimpleJavaFileSet = new HashSet<File>();   // 当前工程下所有非文件夹的 Java文件


    static void initDirFileSet(File shellDirFile) {
        addAllFileDir(shellDirFile);
    }

    static int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象
        int mSumDirNum = 0;
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
        if (childChildList == null) {
            return true;
        }
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                return false;
            }
        }
        return flag;
    }


    static void initSimpleFileSet() {
        int fileIndex = 1;
        for (File dirFile : allDirFileSet) {
//              System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory() && childFileList[i].getName().endsWith(".java")) {
                        allSimpleJavaFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }

    static ArrayList<File> initJavaFileFromParam(ArrayList<String> keyList) {
        ArrayList<File> curJavaFileList = new ArrayList<File>();

        //  如果当前的文件是以./ 文件开头的话 那么说明 传入的参数是 相对路径  需要转为绝对路径
        for (int i = 0; i < keyList.size(); i++) {
            String itemStr = keyList.get(i);
            String absItemStr = "";
            if (itemStr.startsWith("./") || itemStr.startsWith(".\\")) {
                itemStr = itemStr.replace("./", "");
                itemStr = itemStr.replace(".\\", "");
                absItemStr = curProjectPath + File.separator + itemStr;
            } else {
                absItemStr = itemStr;    // 传递过来的就是绝对路径
            }


            File cuAabsItemStrFile = new File(absItemStr);
            if (cuAabsItemStrFile.exists() && cuAabsItemStrFile.getName().endsWith(".java")) {
                // 文件存在 并且是以  .java 文件为结尾的 文件
                curJavaFileList.add(cuAabsItemStrFile);
            }
        }


        return curJavaFileList;

    }

    static ArrayList<File> initJavaDirFromParam(ArrayList<String> keyList) {
        ArrayList<File> curJavaDirList = new ArrayList<File>();

        for (int i = 0; i < keyList.size(); i++) {
            String itemStr = keyList.get(i);
            String absItemStr = "";
            if (itemStr.startsWith("./") || itemStr.startsWith(".\\")) {
                itemStr = itemStr.replace("./", "");
                itemStr = itemStr.replace(".\\", "");
                absItemStr = curProjectPath + File.separator + itemStr;
            } else {
                absItemStr = itemStr;    // 传递过来的就是绝对路径
            }

            File cuAabsItemStrFile = new File(absItemStr);
            if (cuAabsItemStrFile.exists() && cuAabsItemStrFile.isDirectory()) {
                // 文件存在 并且是以  当前文件是文件夹
                curJavaDirList.add(cuAabsItemStrFile);
            }
        }

        return curJavaDirList;

    }


    static boolean isAOSPPath(String curDirPath) {
        boolean flag = false;
        String path = curDirPath.toLowerCase().toLowerCase();
        File dirFile = new File(curDirPath);
        String dirName = dirFile.getName().toLowerCase();
        if (dirFile.exists() && path.contains("to_code") && path.indexOf("code") < path.lastIndexOf("code")) {
// 满足当前文件夹存在   当前文件夹名称包含 code  当前路径包含to_code 当前包含多个 code字符串
            flag = true;
        }


        return flag;

    }

    static void WriteToFile(File curFile, String classContent) {
        try {
            BufferedWriter strBuffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curFile), "utf-8"));
            strBuffWriter.write(classContent);
            strBuffWriter.flush();
            strBuffWriter.close();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }

    }


    static class ZAndroidFrameworkClass extends ZAndroidClass {
        ZAndroidFrameworkClass(String path, ArrayList<ZMethod> methodList) {
            super(path, methodList);
            isFramework = true;
        }

        ZAndroidFrameworkClass(String path) {
            super(path);
            isFramework = true;
        }


    }


    static class ZAndroidAPPClass extends ZAndroidClass {
        ZAndroidAPPClass(String path, ArrayList<ZMethod> methodList) {
            super(path, methodList);
            isAPP = true;
        }

        ZAndroidAPPClass(String path) {
            super(path);
            isAPP = true;
        }


    }


    static class ZAndroidClass extends ZCommonClass {
        ZAndroidClass(String path, ArrayList<ZMethod> methodList) {
            super(path, methodList);
            isIDEClass = false;
            isAndroidClass = true;
        }

        ZAndroidClass(String path) {
            super(path);
            isIDEClass = false;
            isAndroidClass = true;
        }


    }

    static class ZIDEClass extends ZCommonClass {
        ZIDEClass(String path, ArrayList<ZMethod> methodList) {
            super(path, methodList);
            isIDEClass = true;
            isAndroidClass = false;
        }

        ZIDEClass(String path) {
            super(path);
            isIDEClass = true;
            isAndroidClass = false;
        }

    }


    static class ZCommonClass extends ZClass {
        ZCommonClass(String path, ArrayList<ZMethod> methodList) {
            super(path, methodList);
        }

        ZCommonClass(String path) {
            super(path);
        }

    }

    static abstract class ZClass {
        CompilationUnit zCompilationUnit;
        ClassOrInterfaceDeclaration zClassOrInterfaceDeclaration;
        ArrayList<ZMethod> zmethodList;
        String originClassContent;  // 类的完整代码
        String newClassContent;    // 经过修改后的代码
        String className;
        String path;    // 类的路径
        File classFile;   // 类对应的文件
        boolean alreadyAddPrintMethod;
        boolean isAPP;
        boolean isFramework;
        boolean isAndroidClass;     //  是否是安卓项目的类
        boolean isIDEClass;       // 是否是IDE 项目的 类
        boolean isInterfaceFile;   // 当前的文件是一个java 接口文件

        ZClass() {

        }

        ZClass(String path) {
            this.path = path;
            if (this.path == null || this.path.equals("") || this.path.equals(".java")) {
                System.out.println(" 解析文件出错！ 文件为空 或者不是Java文件   文件 = " + this.path);
                return;
            }
            String javaName = this.path.substring(this.path.lastIndexOf(File.separator) + 1, this.path.lastIndexOf(".java"));
            if (javaName == null) {
                System.out.println(" 文件名解析出错！");
                return;
            }
            this.className = javaName;
            this.classFile = new File(path);
            alreadyAddPrintMethod = false;
            // zukgitlog_3
            //   System.out.println("ZClass(String path) = "+ classFile.getAbsolutePath() +"   className="+className);  //  接口 public interface

            if (this.classFile.exists()) {
                try {
                    zCompilationUnit = JavaParser.parse(this.classFile);
                    this.originClassContent = zCompilationUnit.toString();

                    Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration_OPT = zCompilationUnit.getClassByName(this.className);

//                    System.out.println(" this.className = "+ this.className);  // C:\Users\zhuzj5\IdeaProjects\JavaParser\src\Main
                    if (classOrInterfaceDeclaration_OPT != null && classOrInterfaceDeclaration_OPT.isPresent()) {
                        zClassOrInterfaceDeclaration = classOrInterfaceDeclaration_OPT.get();
                    } else {
//                        System.out.println(" 解析 ClassOrInterfaceDeclaration 出错 ！");
                        System.out.println("══════════════════════════");
                        System.out.println("PATH接口Interface文件:" + "    无法通过 javaparser解析 ！");
                        System.out.println("Name=" + this.className + "   Path:" + path);
                        System.out.println("══════════════════════════");
                        this.isInterfaceFile = true;
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(" 解析文件出错！ 可能是Lambda表达式  文件 = " + this.path);
                    this.isInterfaceFile = true;  // 标记为interface文件  不解析

                }
            } else {
                System.out.println(" 文件  " + this.path + " 不存在无法解析！ ");
            }
        }


        void InitZmethodFromJavaFile(File javaFIle, ZClass zCurZClass) {
            this.zmethodList = new ArrayList<ZMethod>();
            if(this.isInterfaceFile){
                return;
            }
            ArrayList<ZMethod> curMethod = ParserJavaMethod(javaFIle, zCurZClass);
            this.zmethodList.addAll(curMethod);

        }

        ArrayList<ZMethod> ParserJavaMethod(File javaFile, ZClass zCurZClass) {
            ArrayList<ZMethod> zMethodList = new ArrayList<ZMethod>();

            String javaName = javaFile.getName();
            if (javaName.endsWith(".java")) {
                javaName = javaName.substring(0, javaName.length() - ".java".length());
            }


            try {
                CompilationUnit mCompilationUnit = JavaParser.parse(javaFile);
                Optional<ClassOrInterfaceDeclaration> class_opt = mCompilationUnit.getClassByName(javaName);
                ClassOrInterfaceDeclaration mClassDeclaration = null;
                if (class_opt.isPresent()) {
                    mClassDeclaration = class_opt.get();
                }

                if (mClassDeclaration != null) {


                    List<MethodDeclaration> CurMethodList = mClassDeclaration.getMethods();

                    ArrayList<MethodDeclaration> methodDecList_arr = new ArrayList<MethodDeclaration>();
                    for (MethodDeclaration mMethodDec : CurMethodList) {
                        methodDecList_arr.add(mMethodDec);
                    }

//                    methodDecList_arr.sort(FieldMethod_Compare);

                    int index = 0;
                    for (MethodDeclaration methodDec : methodDecList_arr) {


                        boolean isStaticMethod = methodDec.isStatic();
                        boolean isNativeMethod = methodDec.isNative();
                        boolean isSyncMethod = methodDec.isSynchronized();
                        boolean isAbstractMethod = methodDec.isAbstract();
                        boolean isFinalMethod = methodDec.isFinal();
                        String modifyWord = "";
                        if (isNativeMethod || isAbstractMethod) {   // 过滤 native方法 和  abstract 抽象方法
                            continue;
                        }
                        modifyWord = modifyWord.trim();

//                        methodDec.isThrown("Exception");


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
                        String resultStr = "方法索引" + index + ": " + returnString + " " + methodName + "(" + paramStr + ")";

//                        arrMethodList.add(resultStr);
                        ZMethod curZMtthod = new ZMethod(zCurZClass, returnString.trim(), methodName.trim(), paramStr.trim());
//                        new ZMethod(mZAndroidAPPClass,"boolean","onSwitchToggled","boolean isChecked ")
                        zMethodList.add(curZMtthod);
                        index++;

                    }


                }

            } catch (Exception e) {
                System.out.println("X Exception ");
                System.out.println("解析文件失败! 可能是接口 可能是 lamdaba表达式");
                e.printStackTrace();
            }

            return zMethodList;
        }

        ZClass(String path, ArrayList<ZMethod> methodList) {
            this.path = path;
            this.zmethodList = methodList;
            this.classFile = new File(path);
            if (this.classFile.exists()) {
                try {
                    zCompilationUnit = JavaParser.parse(this.classFile);
                    this.originClassContent = zCompilationUnit.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(" 解析文件出错！  文件 = " + this.path);
                }
            } else {
                System.out.println(" 文件  " + this.path + " 不存在无法解析！ ");
            }

        }


        //计算字符串在给定字符串出现的次数
        public static int calculSrcCount(String src, String des) {
            int index = 0;
            int count = 0;
            while ((index = src.indexOf(des, index)) != -1) {
                count++;
                index = index + des.length();
            }
            System.out.println(des + "出现了 " + count + " 次");
            return count;
        }


        public static int getCharacterPosition(String originStr, String src, int orderIndex) {
//这里是获取"/"符号的位置
            int resultValue = -1;
            try {
                Matcher slashMatcher = Pattern.compile(src).matcher(originStr);
                int mIdx = 0;
                while (slashMatcher.find()) {
                    mIdx++;
                    if (mIdx == orderIndex) {
                        break;
                    }
                }
                resultValue =  slashMatcher.start();

                return resultValue;
            } catch (Exception e){
                System.out.println("getCharacterPosition 检索 "+ src +"第"+orderIndex+"次出现位置失败!");
            }finally {
                return resultValue;
            }

        }


        static String replaceWithIndex(String oriStr, String src, String dest, int index) {
            String curResultStr = oriStr;
            if (!curResultStr.contains(src)) {   //  如果没有包含src  那么 原样返回
                System.out.println("原样返回1  不包含！src");
                return oriStr;
            }
            int order = index + 1;

            int srcCount = calculSrcCount(oriStr, src);
            if (order <= 0 || order > srcCount) {  //  如果给出的索引为负数 或者 索引+1 > 大于 字符串个数 那么返回原样
                System.out.println("原样返回2   order=" + order + "  srcCount= " + srcCount);
                return oriStr;
            }

            int curPlaceIndex = getCharacterPosition(oriStr, src, order);
            if (curPlaceIndex < 0) {
                System.out.println("原样返回3   curPlaceIndex=" + curPlaceIndex);

                return oriStr;
            }

            int beginIndex = oriStr.indexOf(src, curPlaceIndex);
            int length = src.length();
            int endIndex = beginIndex + length;
            if (beginIndex < 0) {
                System.out.println("原样返回4   beginIndex=" + beginIndex);

                return oriStr;
            }

            if (endIndex > oriStr.length()) {
                System.out.println("原样返回5   endIndex=" + endIndex + "  oriStr.length()=" + oriStr.length());

                return oriStr;
            }
            curResultStr = oriStr.substring(0, beginIndex) + dest + oriStr.substring(endIndex);

//            curResultStr.indexOf()


            return curResultStr;
        }

        // 把原有的Class代码转为新替换的Class的代码
        String toNewClassCode() {
            String originClassCode = "";
            String newClassCode = "";
            String originMethodCode = "";
            String newMethodCode = "";

            if (this.originClassContent == null || this.originClassContent.equals("")) {
                System.out.println("解析文件出现错误: originClassContent 为空");
                return null;
            }
            // zukgitlog_4  原始类的内容
            //  System.out.println("═══════ originClassContent ═══════\n"+this.originClassContent);
            originClassCode = new String(this.originClassContent);

            if (originClassCode.contains("zukgit")) {
                System.out.println(" 该类已经添加了Log 不再添加Log的打印方法 MetodPrint!");
                this.alreadyAddPrintMethod = true;
            }

            newClassCode = new String(originClassCode);
            int mMethodIndex = 1;
            for (ZMethod zmethod : this.zmethodList) {
                //System.out.println("  当前解析Method: = "+ zmethod.methodName);
                //  curClassCode.replaceAll(zmethod.originCodeString,)
                int allNodeSize = zmethod.checkAllNode(zmethod.methodDeclaration);   //  获取总共Method 中多少个结点
                if (allNodeSize == 0) {
                    continue;
                }

                if (zmethod.originCodeString.contains("@Test")) {
                    continue;
                }

                // 去掉方法的注释   //  //  //
                zmethod.logOnlyParam_originCodeString = tryToClearHeadCommandB(zmethod.originCodeString);

                // 如果方法中包含 @Test   那么也不解析 zukgit_log zukgit1_log zukgitlog_1
                //  System.out.println("zmethod.originCodeString 去除方法头部注释后 = \n"+ zmethod.logOnlyParam_originCodeString);

                int expressNodeSize = zmethod.expressNodeList.size();    //  每个表达式 前面 添加一些 log 以此 来观察 程序 运行
                // zukgitlog_5
                //      System.out.println(" allNodeSize = "+ allNodeSize +"       表达式结点 expressNodeSize = "+ expressNodeSize);
                originMethodCode = zmethod.originCodeString;
                //       System.out.println("第" + mMethodIndex+" 个方法开始解析 methodName =:" +zmethod.methodName);
                mMethodIndex++;
                if (zmethod.originCodeString.contains("zukgit")) {  // 对已经添加过Log的方法不再处理
                    //  System.out.println(" 该方法已经添加了Log 不再添加Log 添加log!"+zmethod.methodName);
                    zmethod.belongClass.alreadyAddPrintMethod = true;
                    continue;
                }
                newMethodCode = new String(zmethod.logOnlyParam_originCodeString);

                NodeMapPrint(zmethod.sameExpressNodeMap);
                Object[] sameSetKey = zmethod.sameExpressNodeMap.keySet().toArray();
                for (int i = 0; i < sameSetKey.length; i++) {
                    ArrayList<Node> sameNodeList = zmethod.sameExpressNodeMap.get(sameSetKey[i]);
                    for (int j = 0; j < sameNodeList.size(); j++) {
                        int nodeIndex = j;
                        Node curNode = sameNodeList.get(j);
                        boolean needBlock = curNode.getParentNode().get().getMetaModel().getType() == IfStmt.class;

                        String code1 = zmethod.fixExpressNodeString(curNode.toString());  //  需要对这里进行fix  去除空格与注释
                        String[] analysysCode = zmethod.tryAnalysisExpressNodeString(curNode.toString());

                        //  如果父类不为空  并且父类是 com.github.javaparser.ast.stmt.IfStmt   并且父类的子项只有两个
                        String log = zmethod.buildIndexLog();
                        String codePre1 = "";
                        String codeBack1 = "";
                        if (analysysCode != null && analysysCode.length == 2) {
                            codePre1 = analysysCode[0];
                            codeBack1 = analysysCode[1];
                        }

                        if (checkExceptionFlag(codePre1)) {
                            codePre1 = "";
                        }
                        if (checkExceptionFlag(codeBack1)) {
                            codeBack1 = "";
                        }
                        String code2 = log + codePre1 + "  " + code1 + codeBack1;

                        Node fatherNode = curNode.getParentNode().get();

                        if (needBlock) {
                            code2 = "{" + code2 + "}";
                        }
                        System.out.println("═══════════map-same-code-begin══════════");
                        System.out.println("index: " + j + "  sameCode oldMethodCode = " + newMethodCode);
                        String oldMethodCode = new String(newMethodCode);
                        // 原始的已经变化了  之前的替换已经被 "  test1 = 11;" 已经被替换为 "test1 = 11;" 所以 需要减1
                        newMethodCode = replaceWithIndex(oldMethodCode, "  " + code1, code2, nodeIndex);
                        System.out.println("index:" + j + "  sameCode code1 = " + "  " + code1);
                        System.out.println("index:" + j + "  sameCode code2 = " + code2);
                        System.out.println("index: " + j + "  sameCode newMethodCode = " + newMethodCode);
                        System.out.println("old == new  " + oldMethodCode.equals(newMethodCode));
                        System.out.println("═══════════map-same-code-end══════════");
                        System.out.println();
                    }


                }

                for (int j = 0; j < expressNodeSize; j++) {
                    //  Node node =  zmethod.expressNodeList.get(j);
                    Node node = (Node) zmethod.expressNodeList.toArray()[j];
                    String code1 = zmethod.fixExpressNodeString(node.toString());  //  需要对这里进行fix  去除空格与注释
                    String[] analysysCode = zmethod.tryAnalysisExpressNodeString(node.toString());  // 对参数进行分析  String[2]  === type和typename
//Line 695: ══════=== fixStr = Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
//Line 700: ══════=== fixStr = mSwitchWidget.setChecked(false);
//Line 705: ══════=== fixStr = mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_ON);
//Line 710: ══════=== fixStr = mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());
//Line 715: ══════=== fixStr = mSwitchWidget.setEnabled(true);
//Line 720: ══════=== fixStr = Toast.makeText(mContext, R.string.wifi_error, Toast.LENGTH_SHORT).show();

                    //  如果父类不为空  并且父类是 com.github.javaparser.ast.stmt.IfStmt   并且父类的子项只有两个
                    String log = zmethod.buildIndexLog();
                    String codePre1 = "";
                    String codeBack1 = "";
                    if (analysysCode != null && analysysCode.length == 2) {
                        codePre1 = analysysCode[0];
                        codeBack1 = analysysCode[1];
                    }
//                    codeBackXXX =   报错的
//                            zPrintLog(>);
                    //   System.out.println(" codePreXXX   = "+ codePre1);
                    //   System.out.println(" codeBackXXX = "+ codeBack1);

//                    expected one of "!=" "%" "%=" "&" "&&" "&=" "(" ")" "*" "*=" "+" "+=" "," "-" "-=" "->"
//                    "/" "/=" "::" "<" "<<=" "<=" "=" "==" ">" ">=" ">>=" ">>>=" "?" "^" "^="
//                    "instanceof" "|" "|=" "||"

                    if (checkExceptionFlag(codePre1)) {
                        codePre1 = "";
                    }


                    if (checkExceptionFlag(codeBack1)) {
                        codeBack1 = "";
                    }
                    //  System.out.println(" codePreXXX_B   = "+ codePre1);
                    // System.out.println(" codeBackXXX_B = "+ codeBack1);


                    String code2 = log + codePre1 + code1 + codeBack1;

                    Node fatherNode = node.getParentNode().get();

                    if (fatherNode != null && fatherNode.getMetaModel().getType() == IfStmt.class) {
                        code2 = "{" + code2 + "}";   //  对于  if()   int = aa  ;    // 这样的表达式处理
                        //System.out.println(" fatherNode.getChildNodes().size = "+ fatherNode.getChildNodes().size());
                    }
/*                    else if(fatherNode != null && fatherNode.getMetaModel().getType() == com.github.javaparser.ast.stmt.BlockStmt.class && fatherNode.getChildNodes().size() == 1){
                        code2 = "{" + code2 + "}" ;   //  对于  if()   int = aa  ;    // 这样的表达式处理

                    }*/


                    System.out.println();
                    System.out.println();
//                    System.out.println("═════════════════════════Begin════════════════════");
                    Node grandFatherNode = fatherNode.getParentNode().get();
                    System.out.println("code2 = " + code2);


//                    System.out.println("node.getMetaModel().getType() = "+ node.getMetaModel().getType());
//                         System.out.println("node.tostring = "+node.toString());
//                         for (int i = 0; i < node.getChildNodes().size(); i++) {
//                            System.out.println("curNode-child["+i+"] = "+ node.getChildNodes().get(i).getMetaModel().getType() );
//                         }
//                    System.out.println("fatherNode.tostring = "+fatherNode.toString());
//                        System.out.println("fatherNode.getMetaModel().getType() = "+ fatherNode.getMetaModel().getType());
//                    System.out.println("father-child-size="+fatherNode.getChildNodes().size());
//                    for (int i = 0; i < fatherNode.getChildNodes().size(); i++) {
//                        System.out.println("child["+i+"] = "+ fatherNode.getChildNodes().get(i).getMetaModel().getType() );
//                    }
//
//                        if(grandFatherNode != null){
//                            System.out.println("grandFatherNode.getMetaModel().getType() = "+ grandFatherNode.getMetaModel().getType());
//                            System.out.println("grandFatherNode = "+ grandFatherNode.toString());
//                        }
//                    System.out.println("═════════════════════════End════════════════════");


                    System.out.println();
                    System.out.println();

                    //System.out.println("############## index="+j+"     原有的Log node.toString() = "+code1);
                    //System.out.println("############## index="+j+"     现有的Log code2 = "+code2);

                    //  当前的 code1 是否是  某个 expressNode 的 子集
                    //  判断 当前的 code1 是否含有多个子项
                    newMethodCode = newMethodCode.replace("  " + code1, code2);     //  继续点  这里因为空格的原因无法匹配到
// ############## index=0     原有的Log node.toString() = Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
//############## index=0     现有的Log code2 =
// android.util.Log.i("zukgit_onSwitchToggled","this is indexLog index= 0");
// Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
                    //System.out.println("############## index="+j+"     现有的  newMethodCode  = "+newMethodCode);

                }

                //System.out.println("！！！！！！！！！！新构建的方法 Method的方法 如下: 原始方法 :" + zmethod.originCodeString);  // 构建新的方法的内容
                //System.out.println("！！！！！！！！！！新构建的方法 Method的方法 如下: newMethodCode :" + newMethodCode);  // 构建新的方法的内容
                String addBlankOriginCodeString = "";
                if (zmethod.originCodeString.startsWith("/*")) {
                    String originCodeStringWithOutZhuShi = zmethod.originCodeString.substring(zmethod.originCodeString.indexOf("*/") + 2).trim();
                    addBlankOriginCodeString = originCodeStringWithOutZhuShi.replaceAll("\n", "\n    ");
                    //System.out.println("Method 开头包含注释 /***/ ！！");
                } else {
                    addBlankOriginCodeString = zmethod.originCodeString.replaceAll("\n", "\n    ");
                    //System.out.println("Method 开头不包含注释 /***/ ！！");
                }


                if (addBlankOriginCodeString.startsWith("@Override")) {
                    addBlankOriginCodeString = "    " + addBlankOriginCodeString;
                }
                addBlankOriginCodeString = addBlankOriginCodeString.replace("    * ", "* ");
                addBlankOriginCodeString = addBlankOriginCodeString.replace("    */", "*/");

                if (zmethod.buildIndexLogList != null) {

                    for (int i = 0; i < zmethod.buildIndexLogList.size(); i++) {
                        String index = zmethod.buildIndexLogList.get(i);
                        //System.out.println("序号 : "+ i  + "   出现的序号: +" + (index) );
                    }

                }

                newMethodCode = zmethod.fixnewMethodCode(newMethodCode); // 把  this_is_indexLog_index=0  依次设置序号
                // System.out.println("═════════=   newMethodCode  Begin═════════==");
                //  System.out.println(newMethodCode);
                //  System.out.println("═════════=   newMethodCode  End═════════==");
                //  System.out.println("═════════=   newClassCode  Begin═════════==");

                newClassCode = newClassCode.replace(addBlankOriginCodeString, newMethodCode); // 构建新类文件内容

                if (!newClassCode.contains(newMethodCode)) {
                    //System.out.println("第一次匹配失败 在 addBlankOriginCodeString 和 newClassCode中的方法 格式问题不对称");
                    //System.out.println("1.newClassCode 中的格式无法查询  需要查看Log ");
                    //System.out.println("2.当前去匹配去适应的method字符串是:\n"+ addBlankOriginCodeString);

                    String classCodeTemplate = "class ZTemplate {" + addBlankOriginCodeString + " }";

                    CompilationUnit compileUtil = JavaParser.parse(classCodeTemplate);

                    String strCode1 = compileUtil.toString();
                    //System.out.println(strCode1);
                    String sreCode2 = strCode1.substring("class ZTemplate {".length());
                    String strCode3 = sreCode2.substring(0, sreCode2.lastIndexOf("}"));
                    String firstCode = "";
                    firstCode = strCode3.substring(0, 1);
                    //    System.out.println("firstCode1 = "+ firstCode);
                    while (!strCode3.startsWith(" ")) {
                        firstCode = strCode3.substring(0, 1);
                        //System.out.println("firstCode2 = "+ firstCode);
                        strCode3 = strCode3.substring(1);
                    }
                    if (newClassCode.contains(strCode3)) {
                        // System.out.println("第二次匹配成功 在 strCode3 和 newClassCode中的方法 格式问题对称");
                        newClassCode = newClassCode.replace(strCode3, newMethodCode);

                    } else {
                        // System.out.println("第二次匹配失败 在 strCode3 和 newClassCode中的方法 格式问题不对称");
                    }
                } else {
                    //System.out.println("第一次匹配成功 在 addBlankOriginCodeString 和 newClassCode中的方法 格式对称");

                }
                // System.out.println(newClassCode);
                //  System.out.println("═════════=   newClassCode  End═════════==");
                //   System.out.println("═════════=   addBlankOriginCodeString  Begin═════════==");
                //  System.out.println(addBlankOriginCodeString);
                //  System.out.println("═════════=   addBlankOriginCodeString  End═════════==");
                newMethodCode = null;
            }
            // System.out.println("！！！！！！！！！！  原始类的内容 :" + originClassCode);  // 构建新的方法的内容
            //  System.out.println("！！！！！！！！！！新类的内容 :" + newClassCode);  // 构建新的方法的内容
            return newClassCode;
        }

        void addZMethod(ArrayList<ZMethod> zmethodList) {
            this.zmethodList = zmethodList;
        }
        /*fafa*/


        static String tryToClearHeadCommandB(String originMethodContent) {
            String methodOnly = originMethodContent.trim();
            String resultCode = originMethodContent;
            if (!methodOnly.startsWith("//")) {
                return originMethodContent;
            } else {
                String[] codeArr = originMethodContent.split("\n");
                StringBuilder sb = new StringBuilder();
                int beginIndex = 0;
                for (int i = 0; i < codeArr.length; i++) {
                    if (!codeArr[i].trim().startsWith("//")) {
                        beginIndex = i;
//                        System.out.println("头部注释:  i"+i+"    " + codeArr[i]);
                        break;
                    }
                }

                for (int i = 0; i < codeArr.length; i++) {
//                    System.out.println("所有分割情况 i="+i+ "  value = "+codeArr[i]);
                }
                for (int j = beginIndex; j < codeArr.length; j++) {
                    sb.append(codeArr[j] + "\n");

                }

                resultCode = sb.toString();
//                System.out.println("去除Head注释的方法字符:"+ resultCode );
            }


            return resultCode;

        }


        static boolean checkExceptionFlag(String codeBack1) {
            boolean flag = false;
            if (codeBack1.contains("(>)") || codeBack1.contains("(>>)") || codeBack1.contains("(>>>)") || codeBack1.contains("(<)")
                    || codeBack1.contains("(=)") || codeBack1.contains("(|)") || codeBack1.contains("(<<)") || codeBack1.contains("(<<<)")
                    || codeBack1.contains("(&)") || codeBack1.contains("(+)") || codeBack1.contains("(-)") || codeBack1.contains("(!=)") ||
                    codeBack1.contains("(/)") || codeBack1.contains("(!)") || codeBack1.contains("(*)") || codeBack1.contains("(^)") ||
                    codeBack1.contains("(%)") || codeBack1.contains("(:)") || codeBack1.contains("(*=)") || codeBack1.contains("(%=)") ||
                    codeBack1.contains("(&&)") || codeBack1.contains("(&=)") || codeBack1.contains("()") || codeBack1.contains("(+=)") ||
                    codeBack1.contains("(-=)") || codeBack1.contains("(->)") || codeBack1.contains("(/=)") || codeBack1.contains("(::)") ||
                    codeBack1.contains("(<<=)") || codeBack1.contains("(==)") || codeBack1.contains("(>=)") || codeBack1.contains("(>>=)") ||
                    codeBack1.contains("(>>>=)") || codeBack1.contains("(^=)") || codeBack1.contains("(||)")
            ) {

                flag = true;
            }

            return flag;

//                    expected one of "!=" "%" "%=" "&" "&&" "&=" "(" ")" "*" "*=" "+" "+=" "," "-" "-=" "->"
//                    "/" "/=" "::" "<" "<<=" "<=" "=" "==" ">" ">=" ">>=" ">>>=" "?" "^" "^="
//                    "instanceof" "|" "|=" "||"

        }

        String GetRealPrintMethod() {
            String methodPrint = new String(COMMON_PRINT_METHOD);
            String holdStr = "ZukgitHoldPlace";
            String MethodStr = "";
            if (isIDEClass) {
                MethodStr = "System.out.println(";
            } else if (isAPP) {
                MethodStr = "android.util.Log.i(\"" + TAG + "_" + className + "\",";
            } else if (isFramework) {
                MethodStr = "android.util.SLog.i(\"" + TAG + "_" + className + "\",";
            } else {
                MethodStr = "System.out.println(";
            }

            String returnStr = methodPrint.replaceAll(holdStr, MethodStr);


//            boolean isAPP;
//            boolean isFramework;
//            boolean isAndroidClass;     //  是否是安卓项目的类
//            boolean isIDEClass;       // 是否是IDE 项目的 类


            return returnStr;
        }
    }

    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static class ZMethod {
        MethodDeclaration methodDeclaration;
        NodeList<Parameter> methodParamList;
        String paramLogString;   //  参数的打印字符串
        ZClass belongClass;
        Set<Node> expressNodeList;   //  表达式结点的集合( 不重复 )
        Map<String, ArrayList<Node>> sameExpressNodeMap; // 表达式 相同的结点 但是父节点不同  比如 { int a =3  ; a=2; if(xxx) a=2;}
        String methodIdentify;   // 方法的唯一标识
        String returnString;   // 方法返回值
        String methodName;  // 方法名称
        String paramString;  // 方法参数字符串
        String ownerClassName;   // 方法拥有类名称
        String originCodeString;  // 方法的字符串集合
        String logOnlyParam_originCodeString;  //
        int methodLogIndex;
        String newCodeString;    // 已替换方法的集合
        ArrayList<String> buildIndexLogList;
        String timeStamp ;


        String fixnewMethodCode(String newMethodContent) {
            String flagStr = "this_is_indexLog_index=0";
            if (!newMethodContent.contains(flagStr)) {
                return newMethodContent;
            }
            String curMethodContent = newMethodContent;
            int addIntValue = 1;
            int flagIndex = newMethodContent.indexOf(flagStr);
            while (flagIndex > 0) {

                String currentLog = "this_is_indexLog_index=" + addIntValue+"  timeStamp="+ timeStamp;
                curMethodContent = curMethodContent.substring(0, flagIndex) + currentLog + curMethodContent.substring(flagIndex + flagStr.length());
                flagIndex = curMethodContent.indexOf(flagStr);
                addIntValue++;
                //System.out.println(" addIntValue = "+ addIntValue +  "   flagIndex = "+ flagIndex);

//                addIntValue = 905   flagIndex = 1398
//                addIntValue = 906   flagIndex = 1398
//                addIntValue = 907   flagIndex = 1398

            }


            return curMethodContent;
        }


        String[] tryAnalysisExpressNodeString(String nodeString) {
            String checkCode = "";
//            System.out.println(" 表达式分析 返回分析代码 " );
//            System.out.println(" 表达式:  "+  nodeString);
            // 表达式 以 // 开头 那么 就不解析
            if (nodeString.trim().startsWith("//")) {
                return null;
            }


            //   System.out.println('=');
            //   intValue = -10;  (处理)
            //   int xxx = 10;
            //  System.out.println("method1   intValue =  ge" + intValue);
            // 对于那些事赋值语句的句子 进行 代码的分析
            // 有些表达式 内的 字符串 里面 包含 等号 这些表达式 不处理
            // 1.判断是否包含等号  2.以等号分割的长度 必须等于2
            // 3. 等号前面的数值不能包含空格 不能包含双引号  单引号“ ‘
//  Map<String, String> map = new HashMap<String, String>();
            if (nodeString.contains("=")) {
                String strArr[] = nodeString.split("=");
                if (strArr.length != 2) {
                    return null;
                }  // 确保只分出两个 子subString
                //   wifiConfig = Method.xxxxx();      type = 1
                //  WifiGraturation wifiConfig = Method.xxxxx();    typ2 = 2
                // final int wifiApState = mWifiManager.getWifiApState();  type3 =3
                String variableName = "";

                String str1 = strArr[0].trim();
                String str2 = strArr[1].trim();
                variableName = str1;
                // 继续点
                if (str1.contains("\"") || str1.contains("\'")) {
                    //  不能包含双引号  单引号“ ‘
                    return null;
                }  // intValue = -10;
                int type = 1;

                if (str1.contains(" ")) {
                    String[] arrStrTest = str1.split(" ");
                    if (arrStrTest != null && arrStrTest.length == 2) {
                        type = 2;
                        variableName = arrStrTest[1].trim();
                    } else if (arrStrTest != null && arrStrTest.length == 3 && str1.contains(",") && !arrStrTest[2].contains("<") && !arrStrTest[2].contains(">")) {
// 第二种类型   //  Map<String, String> map
                        // final int wifiApState
                        type = 2;
                        variableName = arrStrTest[2].trim();

                    } else if (arrStrTest != null && arrStrTest.length == 3 && arrStrTest[0].trim().equals("final")) {
                        //   // final int wifiApState = mWifiManager.getWifiApState();
                        type = 2;
                        variableName = arrStrTest[2].trim();
                    } else {

                        return null;
                    }

                }
                //  泛华的打印Log的方法
                // variableName  指向变量名称   type标识当前的表达形式 1 2

                //  0 是打印前的Log
                // 1  是 执行后的Log
                String strShuZu[] = new String[2];

                if (type == 1) {  //  wifiConfig = Method.xxxxx();      type = 1
                    String code1 = "\nzPrintLog(" + variableName + ");\n";
                    String code2 = "\nzPrintLog(" + variableName + ");\n";
                    strShuZu[0] = code1;
                    strShuZu[1] = code2;
                    // 是否有对称的() 的 括号
                    if (!hasDoubleBlock(code1.trim()) || !hasDoubleBlock(code2.trim()) ||
                            !hasDoubleMiddleBlock(code1.trim()) || !hasDoubleMiddleBlock(code2.trim())) {
                        return null;
                    }

                } else if (type == 2) {   //  WifiGraturation wifiConfig = Method.xxxxx();    typ2 = 2
                    strShuZu[0] = "\n";
                    String code1 = "\nzPrintLog(" + variableName + ");\n";
                    strShuZu[1] = code1;

                    // 是否有对称的() 的 括号
                    if (!hasDoubleBlock(code1.trim()) || !hasDoubleMiddleBlock(code1.trim())) {
                        return null;
                    }
                }

                return strShuZu;


            } else {
                return null;
            }


        }

        static boolean hasDoubleMiddleBlock(String express) {
            boolean flag = false;
            int getStrCharNumA = calculStrCount(express, '[');
            int getStrCharNumB = calculStrCount(express, ']');

            if (getStrCharNumA == getStrCharNumB) {
                flag = true;
            }

            return flag;

        }

        static boolean hasDoubleBlock(String express) {
            boolean flag = false;
            int getStrCharNumA = calculStrCount(express, '(');
            int getStrCharNumB = calculStrCount(express, ')');

            if (getStrCharNumA == getStrCharNumB) {
                flag = true;
            }

            return flag;

        }

        static int calculStrCount(String src, char charTarget) {
            int count = 0;
            String curFixStr = src.trim();

            for (int i = 0; i < curFixStr.length(); i++) {
                char charitem = curFixStr.charAt(i);

                if (charitem == charTarget) {
                    count++;
                }
            }

            return count;
        }

        String fixExpressNodeString(String nodeString) {
            String fixStr = "";
            if (nodeString.contains("\n")) {

                String[] strArr = nodeString.split("\n");
                for (int i = 0; i < strArr.length; i++) {
                    if (!strArr[i].trim().startsWith("//")) {
                        fixStr = fixStr + strArr[i].trim();
                    }
                }

            } else {
                fixStr = nodeString;
            }

// Log if user was connected at the time of switching off.
//mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());


            // Log if user was connected at the time of switching off.
            // mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());
            // System.out.println("══════=== fixStr = "+ fixStr);
            return fixStr;
        }

        int getNodeLength(Node node) {
            int nodelength = 0;
            if (node.getChildNodes().size() == 0) {
//                System.out.println(" 叶子结点的类型: "+  node.getMetaModel().getType() + " 内容为: "+ node.toString());

                return 0;
            } else {
//                System.out.println(" 非叶子结点的类型: "+  node.getMetaModel().getType() + " 内容为: "+ node.toString()  );
                if (node.getMetaModel().getType() == ExpressionStmt.class) {
//                    System.out.println("═════════=表达式类型 ExpressionStmt :"+  node.getMetaModel().getType() + " 内容为: "+ node.toString()+"═════════=");

                    Node fatherNode = node.getParentNode().get();
                    int fatherNodeChildSize = 0;
                    String fatherType = "";
                    Node yeyeNode;
                    String yeyeType = "";
                    System.out.println("═══════════════════开始═════════════════════");
                    if (fatherNode != null) {


                        System.out.println("当前表达式:" + node.toString() + "   type = " + node.getMetaModel().getType());
                        System.out.println("当前表达式的子表达式大小:" + node.getChildNodes().size());
                        fatherType = fatherNode.getMetaModel().getType().toString();
                        fatherNodeChildSize = fatherNode.getChildNodes() != null ? fatherNode.getChildNodes().size() : 0;
                        System.out.println("父类的表达式: " + fatherNode.toString() + " type = " + fatherNode.getMetaModel().getType());
                        System.out.println("父类的Child-Size = " + fatherNode.getChildNodes().size());
                        for (int i = 0; i < fatherNode.getChildNodes().size(); i++) {
                            System.out.println("child[" + i + "]  str = " + fatherNode.getChildNodes().get(i).toString());
                            System.out.println("child[" + i + "]  type = " + fatherNode.getChildNodes().get(i).getMetaModel().getType());
                        }
                        yeyeNode = fatherNode.getParentNode().get();
                        if (yeyeNode != null) {
                            yeyeType = yeyeNode.getMetaModel().getType().toString();
                            System.out.println("爷爷类的表达式: " + yeyeNode.toString() + "   type = " + yeyeType);
                        }

                    }

                    System.out.println("═══════════════════结束═════════════════════");
                    int index = expressNodeList.size();
                    //   System.out.println("索引:"+index+"   *************表达式的父类表达式类型为 FatherExpressionStmt : fatherType="+fatherType+"     yeyeType="+yeyeType   + "  fatherNodeChildSize = "+ fatherNodeChildSize  );

                    // 表达式的node相同   字符串也相同的情况的处理
                    // 表达式相同  但是上下文件不同   如果在   if  表达式 ; else  表达的情况下 那么可能会造成 语法错误

                    // 对于表达式相同的node 通过ArrayList<Node> 来进行保存  通过判断父节点的类型来 得到是否添加{}
                    // 由于添加Log 是字符串匹配   对于node相同的表达式  字符串都会去匹配 replace
                    // 对于 不需要{}的 表达式  添加了 {} 导致上下文语法不通过
                    // 对于 需要{} 的 表达式  只有添加了 {} 才能使得语法通过

                    //  如果当前的表达式  不包含 这个 node  并且这个Node也包含在 相同Map 的Key中 那么 添加这个node
                    // 如果这个表达式也是 IF 下的单独表达式 那么 也需要替换
                    if (!expressNodeList.contains(node) && !sameExpressNodeMap.keySet().contains(node.toString()) &&
                            node.getParentNode().get().getMetaModel().getType() != IfStmt.class) {   // 如果有两个相同的sameExpress 那么它们仍然保存在 ArrayList<Node>无法区分
                        expressNodeList.add(node);
                    } else {

                        // 检测当前ArrayList是否为空 为空话 就创建这样的Map
                        ArrayList<Node> nodeList = sameExpressNodeMap.get(node.toString());
                        if (nodeList == null) {
                            nodeList = new ArrayList<Node>();
                        }

                        Node preSameNode = null;
                        if (expressNodeList.contains(node)) {   //  如果是第一次检测到相同  那么需要拿到之前的保存在Set的Node
                            Iterator<Node> it = expressNodeList.iterator();
                            while (it.hasNext()) {   // 拿到之前  SET 中保存的那个 表达式Node
                                Node setNode = it.next();
                                if (setNode != null && node.toString().equals(setNode.toString())) {
                                    preSameNode = setNode;
                                    break;
                                }

                            }//判断是否有下一个
                            expressNodeList.remove(node);  // 拿到之前的Node  再从Set中删除它
                        }

                        if (preSameNode != null) {   //  如果之前的Node 不为空 那么 把它取出来 放到List
                            nodeList.add(preSameNode);
                        }

                        nodeList.add(node);   //  填充当前的Node
                        sameExpressNodeMap.put(node.toString(), nodeList);

                        //  如果已经包含了这个node  那么 检查这个node是否是 if xxx ; else  xxx ; 的 情况
                        //  如果这个 node 满足 if  ; else 的 情况 需要给它 过滤 去除
                    }
                }
                List<Node> listNode = node.getChildNodes();
                nodelength = listNode.size();
                for (Node itemNode : listNode) {
                    nodelength = nodelength + getNodeLength(itemNode);

                }

            }
            return nodelength;

        }

        int checkAllNode(MethodDeclaration method) {
            if (method == null) {
                return 0;
            }
            int num = 0;
            List<Node> curList = method.getChildNodes();
            if (curList == null) {
                return 0;
            }
            num = curList.size();
            if (num == 0) {
                return 0;
            }

            for (Node itemNode : curList) {
                num = num + getNodeLength(itemNode);

            }
            return num;
        }


        synchronized String buildIndexLog() {
            String codeStr =
                    "";
            if (buildIndexLogList == null) {
                buildIndexLogList = new ArrayList<String>();
            }

            codeStr = CommonCodePrint("\"this_is_indexLog_index=" + 0  + "\"");
            // codeStr = CommonCodePrint("this_is_indexLog_index=0");
            methodLogIndex++;

            buildIndexLogList.add("this is indexLog index=" + 0);
            return codeStr;
        }


        ZMethod(ZClass parentClass, String returnString, String methodName, String paramString) {
            this.belongClass = parentClass;
            this.returnString = returnString;
            this.methodName = methodName;
            this.paramString = paramString;
            if (paramString.trim().endsWith(")") && paramString.trim().startsWith("(")) {
                this.paramString = paramString;
            } else {
                this.paramString = "(" + paramString.trim() + ")";
            }
            methodLogIndex = 0;
            this.paramString = tryFixParamFormat(this.paramString);
            this.methodIdentify = (this.returnString + " " + this.methodName + this.paramString).trim();
            this.ownerClassName = this.belongClass.className;

            this.methodDeclaration = getMethodDeclarationFromCompilationUnit(this.belongClass.zClassOrInterfaceDeclaration, methodIdentify);
            if (this.methodDeclaration == null) {
                System.out.println("解析Method为空! 程序退出");
                return;
            }
            this.methodParamList = this.methodDeclaration.getParameters();
            this.originCodeString = this.methodDeclaration.toString();
            timeStamp = getTimeStamp();
            if (this.methodParamList != null) {
                paramLogString = makeMethodParamLog(this.methodParamList);
//                System.out.println("依据参数生成的打印Log的代码如下:\n"+ paramLogString);
                // @Override public boolean onSwitchToggled(boolean isChecked, int int1, String str1, ArrayList<String> strArr, String[] strArr1, int[] arrInt)


//  @Override public boolean onSwitchToggled()  获取到这样的字符串  没有考虑到 方法头包含 /**/的情况
                String preCode = "";
                if (originCodeString.trim().startsWith("/*") && originCodeString.trim().contains("*/")) {  // 如果包含注释的话
                    String fixOriginCodeString = originCodeString.substring(originCodeString.indexOf("*/") + 2);
                    preCode = fixOriginCodeString.substring(0, fixOriginCodeString.indexOf("{") + 1).trim();
//                    System.out.println("包含 包头部含注释 preCode  :\n"+ preCode);
                } else {

                    preCode = originCodeString.substring(0, originCodeString.indexOf("{") + 1);
//                    System.out.println("不包头部含注释 preCode  :\n"+ preCode);
                }
                //  public void enableVerboseLogging(int verbose) {
                //  包含注释
                logOnlyParam_originCodeString = originCodeString.replace(preCode, preCode + "\n" + paramLogString);  //  添加了对 参数的打印的代码

                //  把包含的 注释 /***/  去掉
                if (logOnlyParam_originCodeString.trim().startsWith("/*") && logOnlyParam_originCodeString.trim().contains("*/")) {
                    logOnlyParam_originCodeString = logOnlyParam_originCodeString.substring(logOnlyParam_originCodeString.indexOf("*/") + 2);
                }

            }
//            System.out.println("方法的原始字符串:\n"+ originCodeString);
//            System.out.println("加入参数Log的字符串:\n"+ logOnlyParam_originCodeString);

            expressNodeList = new HashSet<Node>();
            //  开始处理表达式的打印
            sameExpressNodeMap = new HashMap<String, ArrayList<Node>>();
        }

        static String tryFixParamFormat(String param) {
            if (!param.contains(",")) {
                while (param.contains("  ")) {
                    param = param.replace("  ", " ");
                }
                return param.trim();
            }
            String fixParam = "";
            String[] strArr = param.split(",");
            int size = strArr.length;

            for (int i = 0; i < size; i++) {
                String curStr = strArr[i].trim();
                while (curStr.contains("  ")) {
                    curStr = curStr.replace("  ", " ");
                }
                fixParam = fixParam + curStr + ", ";
            }
            fixParam = fixParam.trim();
            while (fixParam.endsWith(",")) {
                fixParam = fixParam.substring(0, fixParam.length() - 1);

            }


//            System.out.println("fixParam = "+ fixParam);
            return fixParam;

        }


        // 1 判断基本的数据类型    基本的数据类型不用盘空  直接打印
// 2. 判断是否是基本类型的数组(该数组有多少层级)
// 3 判断如果为 对象类型  那么  需要判空  并打印
// 4.判断是否为对象的集合 (该集合内部是否还包含有集合  集合的层级) 并打印


        String makeMethodParamLog(NodeList<Parameter> paramList) {
            // 决定选择哪一种打印Log的代码
            String paramLogCode = "";
            String itemCode = "";
            for (Parameter paramItem : paramList) {
                //paramItem = boolean isChecked
                //paramItem = int int1

                if (isBasicType(paramItem.toString())) {  // 基本的 boolean  byte char  short int  long float double  8种类型

                    String Code1 = BaseTypeCode(paramItem.toString());
                    itemCode = Code1;
                    //      System.out.println(" 当前的参数是基本数据类型 boolean  byte char  short int  long float double      paramItem = "+ paramItem.toString() +"代码："+ Code1);
                } else if (isBasicShuZu(paramItem.toString())) {

                    String Code2 = BaseTypeListCode(paramItem.toString());
                    itemCode = Code2;
                    //       System.out.println(" 当前的参数是基本数据类型数组[]    paramItem = "+ paramItem.toString() +"代码："+ Code2);
                } else if (isSingleObject(paramItem.toString())) {  //  String xx  Object XX  Integer XXX
                    String Code3 = SingleObjectPrintCode(paramItem.toString());
                    itemCode = Code3;
                    // System.out.println(" 当前的参数是单个类Object类型   paramItem ="+ paramItem.toString() +"代码："+ Code3);
                } else if (isObjectArr(paramItem.toString())) {    //  String[]
                    String Code4 = ArrObjectPrintCode(paramItem.toString());
                    itemCode = Code4;
                    //     System.out.println(" 当前的参数是类的集合 类似于ArrayList<Object> Object[]   paramItem ="+ paramItem.toString()+"代码："+ Code4);
                }

                paramLogCode = paramLogCode + itemCode;

            }
            while (paramLogCode.contains("\n ")) {
                paramLogCode = paramLogCode.replaceAll("\n ", "\n");
            }
            return CommonRuntimeExceptionCodePrint() + CommonProcessAndThreadCodePrint() + paramLogCode;  // 添加所有Log的地方
        }


        static Set<String> ListTypeList = new HashSet<String>();

        {

            ListTypeList.add("Collection");
            ListTypeList.add("List");
            ListTypeList.add("Vector");
            ListTypeList.add("Stack");
            ListTypeList.add("Deque");
            ListTypeList.add("LinkedBlockingDeque");
            ListTypeList.add("Queue");
            ListTypeList.add("ArrayDeque");
            ListTypeList.add("BlockingQueue");
            ListTypeList.add("LinkedTransferQueue");
            ListTypeList.add("LinkedBlockingQueue");
            ListTypeList.add("PriorityQueue");
            ListTypeList.add("ArrayList");
            ListTypeList.add("LinkedList");
            ListTypeList.add("SortedList");
            ListTypeList.add("Map");
            ListTypeList.add("HashMap");
            ListTypeList.add("TreeMap");
            ListTypeList.add("LinkedHashMap");
            ListTypeList.add("SortedMap");
            ListTypeList.add("WeakHashMap");
            ListTypeList.add("ConcurrentHashMap");
            ListTypeList.add("Set");
            ListTypeList.add("EnumSet");
            ListTypeList.add("SortedSet");
            ListTypeList.add("HashSet");
            ListTypeList.add("TreeSet");
            ListTypeList.add("LinkedHashSet");
            ListTypeList.add("CopyOnWriteArraySet");
            ListTypeList.add("Hashtable");
            ListTypeList.add("Iterator");
            ListTypeList.add("Iterable");


            //  Iterable<String>  itable;
            //   Iterator<String> itStr;
            //     ConcurrentHashMap cMap;
            //  BlockingQueue blockque;
            //      CopyOnWriteArraySet arrayset;
//            LinkedHashMap<String,String>  linkMap;
//            LinkedBlockingDeque<String> lk;
//            LinkedTransferQueue<String> lt;
            // Hashtable<String,String> strtable;
            // WeakHashMap<String,String> weekMap;
            //   SortedMap<String,String> sortedMap ;
            //  SortedSet<String> sortedSet ;
            // EnumSet<String> enumSet;
            //    Queue<String> q ;
            //    Vector<String> vec;
            //   Stack<String> stack;
            //   Deque<String> deque;
            //  ArrayDeque<String> arrDeq;
            //  PriorityQueue<String> queue;
        }


        static boolean isObjectArr(String params) {
            boolean isObjectArr = false;
            if (!params.contains(" ")) { // 并不包含空格说明这个参数的格式错误
                //  System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！ params="+ params);
                return false;

            }
            String[] paramArr = params.split(" ");
            if (paramArr.length == 2 && !params.contains(",")) {  // ArrList<Object> arr   ArrayList<List<XX>> map;
                isObjectArr = isArrObjectTypeString(paramArr[0]);           //   paramItem =Map<String, Object> mapValue
            } else if (params.contains(",") && params.contains("<") && params.contains(">")) { // param = Map<String, Object> mapValue

                isObjectArr = isArrObjectTypeString(params.substring(0, params.lastIndexOf(">") + 1).replace(" ", ""));
            } else {

                //   System.out.println(" ═══==当前参数字符串 !  解析错误！══════==");
            }


            return isObjectArr;

        }


        static boolean isArrObjectTypeString(String type) {  //     String xx  Object XX  Integer XXX
            boolean isArrObjectTypeString = false;
            if (!basicTypeList.contains(type.trim()) || type.contains("[]")) {   // ArrayList
                isArrObjectTypeString = true;
                return isArrObjectTypeString;
            }

            for (String curListType : ListTypeList) {
                if (type.contains(curListType)) {
                    isArrObjectTypeString = true;
                    break;
                }
            }
            return isArrObjectTypeString;
        }


        static boolean isSingleObject(String params) {
            boolean isSingleObject = false;
            if (!params.contains(" ")) { // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if (paramArr.length != 2) {
                //  System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isSingleObject = isSingleObjectTypeString(paramArr[0]);

            return isSingleObject;

        }

        static boolean isSingleObjectTypeString(String type) {  //     String xx  Object XX  Integer XXX
            boolean isSingleObjectTypeString = true;
            if (!basicTypeList.contains(type.trim()) && !type.contains("[]")) {   // ArrayList
                for (String curListType : ListTypeList) {
                    if (type.contains(curListType)) {
                        isSingleObjectTypeString = false;
                        break;
                    }
                }
            } else if (type.contains("[]")) {
                isSingleObjectTypeString = false;  // 对象数组   不是单独的对象
            }
            return isSingleObjectTypeString;
        }


        static boolean isBasicShuZu(String params) {
            boolean isBasic = false;
            if (!params.contains(" ")) { // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if (paramArr.length != 2) {
                //    System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isBasic = isBasicShuzuTypeString(paramArr[0]);

            return isBasic;

        }

        static boolean isBasicShuzuTypeString(String type) {  // byte[] int[]  long[]
            boolean isBasicShuZu = false;
            String curType = new String(type);
            curType = curType.replace("[]", "").trim();  // byte[] ->  byte
            if (basicTypeList.contains(curType.trim()) && type.contains("[]")) {
                isBasicShuZu = true;
            }
            return isBasicShuZu;
        }


        static boolean isBasicType(String params) {
            boolean isBasic = false;
            if (!params.contains(" ")) { // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if (paramArr.length != 2) {
                //   System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isBasic = isBasicTypeString(paramArr[0]);

            return isBasic;
        }

        static Set<String> basicTypeList = new HashSet<String>();

        {
            basicTypeList.add("boolean");
            basicTypeList.add("byte");
            basicTypeList.add("char");
            basicTypeList.add("short");
            basicTypeList.add("int");
            basicTypeList.add("long");
            basicTypeList.add("float");
            basicTypeList.add("double");
        }

        static boolean isBasicTypeString(String type) {
            boolean isBasic = false;
            if (basicTypeList.contains(type.trim())) {
                isBasic = true;
            }
            return isBasic;
        }


        String ArrObjectPrintCode(String paramString) {
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
//            String[] strArr = {"A","B"};
//            int zBaseListSize = strArr.length;
//            for (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){
//                System.out.println("zindex "+zindex +" : " + strArr[zindex].toString());
//            }

            if (baseType.contains("[]")) {  // String[]  Object[]

                //String code1 = "\nint zBaseListSize = " + bsseTypeName+".length;";
                // String code2 = "\nfor (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){";
                String code1 = "\n  if( " + bsseTypeName + " != null ){";
                String code2 = "\nfor (int zindex = 0 ; zindex < " + bsseTypeName + ".length" + " ; zindex++ ){";
                String code3 = "\"" + paramString + "  zindex = \" + zindex +\"   :  value= \" + " + bsseTypeName + "[zindex].toString()";      //  " zindex "+zindex +" : " + intArr[zindex]
                String code3_code = CommonCodePrint(code3);
                String code4 = "}  \n";
                String code5 = "}  \nelse { ";
                String code6 = CommonCodePrint("\"" + bsseTypeName + " is null !\"");
                String code7 = "} \n";

                return code1 + code2 + code3_code + code4 + code5 + code6 + code7;
            }
            String codeStr = "";
            if (paramString.contains("<") && paramString.contains(">") && !paramString.contains(",") &&
                    !paramString.contains("map") && !paramString.contains("Map")) {    // List 类型
                codeStr = getCodePrintForCollect(baseType, bsseTypeName);
            } else if (paramString.contains("<") && paramString.contains(">") && paramString.contains(",")) {
                //  map的类型
                // paramString  MAP类型 = Map<String, Object> mapValue
                //   System.out.println("paramString  MAP类型 = "+ paramString );
                String type = paramString.substring(0, paramString.lastIndexOf(">") + 1).replace(" ", "");
                String typeName = paramString.substring(paramString.lastIndexOf(">") + 1);
                codeStr = getCodePrintForCollect(type, typeName);
            }


            //  String codeStr = CommonCodePrint(bsseTypeName+".toString()");
            return codeStr;
        }

        String getCodePrintForCollect(String param1, String param2) {
            String paramName = param1.trim() + " " + param2.trim();
            String baseType = param1;
            String baseTypeName = param2;
//            System.out.println(" baseType ="+ baseType + "    bsseTypeName="+ baseTypeName );

// HashMap 例子
//HashMap map =new HashMap<>();
//map.entrySet().iterator();

// TreeMap 例子
//TreeMap map =new TreeMap<>();
//map.entrySet().iterator();

// LinkedHashMap 例子
//LinkedHashMap map =new LinkedHashMap<>();
//map.entrySet().iterator();

// SortedMap 例子  SortedMap 是抽象类
//SortedMap map  = null;
//map.entrySet().iterator();


// WeakHashMap 例子
//WeakHashMap map =new WeakHashMap<>();
//map.entrySet().iterator();


// ConcurrentHashMap 例子
//ConcurrentHashMap map =new ConcurrentHashMap<>();
//map.entrySet().iterator();


// Hashtable 例子
//Hashtable linkQueue = null ;
//Iterator iterator =    linkQueue.entrySet().iterator();
//iterator.hasNext();
//Map.Entry<String , String> entrya = (Map.Entry<String , String>) iterator.next();
//entrya.getKey();
//entrya.getValue();
            String paramType = "";
            String keyStringType = "";
            String valueStringType = "";
            if (param1.contains("<") && param1.contains(">") && param1.contains(",")) {  //  Map 包含 <String, String>
                paramType = param1.substring(0, param1.indexOf("<"));
                if (param1.contains("Map") || param1.contains("map")) {
                    keyStringType = param1.substring(param1.indexOf("<") + 1, param1.indexOf(","));
                    valueStringType = param1.substring(param1.indexOf(",") + 1, param1.lastIndexOf(">"));
                } else {
                    // 是List的 情况
                    valueStringType = param1.substring(param1.indexOf("<") + 1, param1.indexOf(">"));
                }

            } else if (param1.contains("<") && param1.contains(">") && !param1.contains(",")) {  //   paramItem =ArrayList<String> strArr   param1=ArrayList<String>
                paramType = param1.substring(0, param1.indexOf("<"));
                keyStringType = "";
                valueStringType = param1.substring(param1.indexOf("<") + 1, param1.indexOf(">"));
            } else {
                paramType = param1.trim();
                valueStringType = baseTypeName;
            }

            String code0 = "\n  if( " + baseTypeName + " != null ){";
            if (paramType.endsWith("Map") || "Hashtable".equals(paramType)) {   // 当前 类型是 Map的类型
                String code1 = "";
                if (!keyStringType.isEmpty() && !valueStringType.isEmpty()) {
                    code1 = "\nMap.Entry<" + keyStringType + " , " + valueStringType + "> entry = null;";
                } else {
                    System.out.println("获取到的Map中的key 和 value 为空  出现错误 将退出！  param1 = " + param1);
                    return null;
                }
                //   String code2 = "\n if("+bsseTypeName+" != null){";
                String code3 = "\n java.util.Iterator iterator = " + baseTypeName + ".entrySet().iterator();";
                String code3_1 = "\n int mZindex = 0 ;";
                String code4 = "\n while( iterator.hasNext() ){";

                String code5 = "\n entry = (Map.Entry<" + keyStringType + "," + valueStringType + ">) iterator.next();";
                String code6 = "\"" + baseTypeName + "    index =" + "\"+ mZindex + \"   " + "  entry.Key= \"" + "+ entry.getKey() +" + " \" entry.Value=\"" + "+ entry.getValue()";  // 继续点
                String code6_fix = CommonCodePrint(code6);
                String code6_1 = " mZindex ++ ;";
                String code7 = " }";
                // String code8 = "\n }";

                String code9 = "}  \nelse { ";
                String code10 = CommonCodePrint("\"" + baseTypeName + " is null !\"");
                String code11 = "} \n";

//  Map.Entry<String , ArrayList<String> > entry;
// Map 的 例子
//            Map<String,String> map = null;
//            Map.Entry<String , String> entry;
//            if(map != null){
//                Iterator iterator = map.entrySet().iterator();
//                while( iterator.hasNext() ){
//                    entry = (Map.Entry<String , String>) iterator.next();
//                    entry.getKey();  //Map的Value
//                    entry.getValue();  //Map的Value
//                    System.out.println("zukgit0725"+"entry.getKey()= "+entry.getKey() +" ----entry.getValue()="+entry.getValue());
//                }
//            }

                return code0 + code1 + code3 + code3_1 + code4 + code5 + code6_fix + code6_1 + code7 + code9 + code10 + code11;

            } else if (paramType.endsWith("Set") || paramType.endsWith("List") || paramType.endsWith("Queue") || paramType.endsWith("Deque") ||
                    paramType.endsWith("Stack") || paramType.endsWith("Vector") || paramType.endsWith("Collection")) {
// 当前的是一个List<>   valueStringType 代表当前《》 中的内容
// CopyOnWriteArraySet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}
                String code0_list = "\n  if( " + baseTypeName + " != null ){";
                String code1 = "\n Object[] objectList  =" + baseTypeName + ".toArray();";
                String code2 = "\n  for(int zindex =0 ; zindex < " + baseTypeName + ".size(); zindex ++){";
                String code3 = " \"" + paramName + "  index =\"+zindex +" + "\" value :\" + " + "objectList[zindex].toString()";
                String code3_fix = CommonCodePrint(code3);
                String code4 = " }";
                String code5 = "\n}  \nelse { ";
                String code6 = CommonCodePrint("\"" + paramName + " is null !\"");
                String code7 = "} \n";


                return code0_list + code1 + code2 + code3_fix + code4 + code5 + code6 + code7;  // 继续点
            } else {
                // 其余以外的数组关键字  直接  toString 了
                // paramType=Map<String,  valueStringType =Object>
                //    System.out.println("无法判断集合的类型  paramType="+paramType.toString() +"  valueStringType ="+ valueStringType);

                if (checkValueStringType(valueStringType.trim())) {
                    //  包含 .>
                    return CommonCodePrint("\"" + baseTypeName + "=\"+" + baseTypeName + ".toString()");
                }
                // PemessionState>
                return CommonCodePrint(valueStringType.trim() + ".toString()");
            }

            //  Map 需要获取 Key 和 Value的 类型  否则无法转换
            // ═══════════════=   通用 ════════════===

            //    return "";
        }

        static boolean checkValueStringType(String typeStr) {
            boolean flag = true;

            if (typeStr.contains(".>") || typeStr.contains(">.")) {

                flag = false;
            }

            return flag;

        }

// CopyOnWriteArraySet 例子
// CopyOnWriteArraySet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedHashSet 例子
// LinkedHashSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// TreeSet 例子
// TreeSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// HashSet 例子
// HashSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// SortedSet 例子
// SortedSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


        // EnumSet 例子
// EnumSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// SortedList 例子
// SortedList linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedList 例子
// LinkedList linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// PriorityQueue 例子
// PriorityQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedBlockingQueue 例子
// LinkedBlockingQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


        // LinkedTransferQueue 例子
// LinkedTransferQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// BlockingQueue 例子
// BlockingQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// ArrayDeque 例子
// ArrayDeque linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// Queue 例子
// Queue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedBlockingDeque 例子
//LinkedBlockingDeque linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

        // Stack  例子
//Stack stackA = null;
//Object[] objectList  = stackA.toArray();
//for(int zindex =0 ; zindex < stackA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


//  Vector 例子
//Vector vectA = null;
//Object[] objectList  = vectA.toArray();
//for(int zindex =0 ; zindex < vectA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

// List 的例子
//List listA = null;
//Object[] objectList  = listA.toArray();
//for(int zindex =0 ; zindex < listA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// Collection 的例子
//Collection collect1 = null ;
//Object[] objectList  = collect1.toArray();
//for(int zindex =0 ; zindex < collect1.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// ArrayList 的例子
//ArrayList<String> strlist = new ArrayList<String>();
//Object[] objectList =  strlist.toArray();
//for(int zindex =0 ; zindex < strlist.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

// Set 的例子
//Set<String> strlist = new HashSet<String>();
//Object[] objList = strlist.toArray();
//int zObjectListSize = strlist.size();
//for(int zindex =0 ; zindex < zObjectListSize; zindex ++){
//
//    System.out.println("zindex "+zindex +" : " + objList[zindex].toString());
//}


        String SingleObjectPrintCode(String paramString) {
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            String code1 = "\n if(" + bsseTypeName + " != null ) {";
            String code2 = CommonCodePrint("\"" + paramString + " = \" + " + bsseTypeName + ".toString()");
            String code3 = " } \nelse { ";
            String code4 = CommonCodePrint("\"" + paramString + " ==  null\"  ");
            String code5 = "}\n";
            return code1 + code2 + code3 + code4 + code5;
        }

        @SuppressWarnings("unchecked")
        public static void getKeyAndValue(Map<String, String> map) {
            Map.Entry<String, String> entry;
            if (map != null) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, String>) iterator.next();
                    entry.getKey();  //Map的Value
                    entry.getValue();  //Map的Value
                    //  System.out.println("zukgit0725"+"entry.getKey()= "+entry.getKey() +" ----entry.getValue()="+entry.getValue());
                }
            }
        }

        String BaseTypeListCode(String paramString) {
//            int[] intArr = {1,2,4};
//            int zBaseListSize = intArr.length;
//            for (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){
//                System.out.println("zindex "+zindex +" : " + intArr[zindex]);
//            }


            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            //  String code1 = "\nint zBaseListSize = " + bsseTypeName+".length;";
            String code1 = "";
            String code2 = "\nfor (int zindex = 0 ; zindex < " + bsseTypeName + ".length" + " ; zindex++ ){";
            String code3 = "\"" + paramString + "----index = \" + zindex +\" : \" +  \"  value = \" + " + bsseTypeName + "[zindex]";      //  " zindex "+zindex +" : " + intArr[zindex]
            String code3_code = CommonCodePrint(code3);
            String code4 = "}\n";

            return code1 + code2 + code3_code + code4;
        }


        String BaseTypeCode(String paramString) {
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            String code = "\"" + paramString + " = \" + " + bsseTypeName;
            return CommonCodePrint(code);
        }


        String getRuntimeExceptionCode() {
            String excepCode = "";
            return excepCode;

        }

        String CommonCodePrint(String paramString) {    //  boolean  falg |  String like  | ArrayList List  | XXX xClass
            String realPrintCode = "";
            //  String tagMethod = TAG+"_"+this.methodName;
            String tagMethod = TAG + "_" + this.belongClass.className + "_" + this.methodName;
            if (this.belongClass.isIDEClass) {
                realPrintCode = "\nSystem.out.println( " + "\" " + tagMethod + "   \" + " + paramString + ");\n";
            } else if (this.belongClass.isAndroidClass && this.belongClass.isAPP) {
                // android.util.Log.i
                realPrintCode = "\nandroid.util.Log.i(\"" + tagMethod + "\"," + paramString + ");\n";
            } else if (this.belongClass.isAndroidClass && this.belongClass.isFramework) {
                realPrintCode = "\nandroid.util.Slog.i(\"" + tagMethod + "\"," + paramString + ");\n";
            }

            return realPrintCode;
        }


        // IDEA 打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        re.printStackTrace();

        //  APP层面 安卓打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        android.util.Log.v("zukgit", "RuntimeException", re);

        //  Framework层面 安卓打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        android.util.SLog.v("zukgit", "RuntimeException", re);

        String CommonProcessAndThreadCodePrint() {
            String strCode1 = "";
            String strCode2 = "";
            String strCode3 = "";

            String tagMethod = TAG + "_" + this.belongClass.className + "_" + this.methodName;
            if (this.belongClass.isIDEClass) {
                String re_Name = "re" + System.currentTimeMillis();
                strCode1 = "\nRuntimeException " + re_Name + " = new RuntimeException();";
                strCode2 = "\n" + re_Name + ".fillInStackTrace();";
                String tag = "\nSystem.out.println( " + "\" " + tagMethod + "   RuntimeException.fillInStackTrace() \" );";
                strCode3 = tag + "\n" + re_Name + ".printStackTrace();\n";

            } else if (this.belongClass.isAndroidClass && this.belongClass.isAPP) {
                // android.util.Log.i
                String re_Name = "re" + System.currentTimeMillis();
                strCode1 = "\nRuntimeException " + re_Name + " = new RuntimeException();";
                strCode2 = "\n" + re_Name + ".fillInStackTrace();";
                strCode3 = "\nandroid.util.Log.i(\"" + tagMethod + "\", \"RuntimeException\", " + re_Name + " );\n";
            } else if (this.belongClass.isAndroidClass && this.belongClass.isFramework) {
                String re_Name = "re" + System.currentTimeMillis();
                strCode1 = "\nRuntimeException " + re_Name + " = new RuntimeException();";
                strCode2 = "\n" + re_Name + ".fillInStackTrace();";
                strCode3 = "\nandroid.util.SLog.i(\"" + tagMethod + "\", \"RuntimeException\", " + re_Name + " );\n";
            }

            return strCode1 + strCode2 + strCode3;
        }


        String CommonRuntimeExceptionCodePrint() {    //  boolean  falg |  String like  | ArrayList List  | XXX xClass
            String tagMethod = TAG + "_" + this.belongClass.className + "_" + this.methodName;
            String stackName = "stacks" + System.currentTimeMillis();
            String strCode1 = "\njava.util.Map<Thread, StackTraceElement[]> " + stackName + " = Thread.getAllStackTraces();";
            String threadSet_Name = "threadSet" + System.currentTimeMillis();
            String strCode2 = "\njava.util.Set<Thread> " + threadSet_Name + " = " + stackName + ".keySet();";
            String strCode3 = "";
            String getProcessStr = "";
            String threadNum_Name = "threadNum" + System.currentTimeMillis();
            String strCode4 = "\nint " + threadNum_Name + " = 1;";
            String strCode5 = "\nfor (Thread key : " + threadSet_Name + ") {";

            String strCode6 = "\nStackTraceElement[] stackTraceElements = " + stackName + ".get(key);";
            String strCode6_fix = "";
            String strCode7 = "\nfor (StackTraceElement st : stackTraceElements) {";
            String strCode7_fix = "";
            String strCode8 = "\n}";
            String strCode8_fix = "";
            String strCode9 = "\n}";


//                for (Thread key : threadSet) {
//                    StackTraceElement[] stackTraceElements = stacks.get(key);
//                    System.out.println("\n════════════ 线程名称threadName:【 " + key.getName() + "】  进程ID-ProcessID:【"+mProcessId + "】  线程ID-ThreadId:【"+key.getId()+ "】  线程序号-indexId: 【"+ (threadNum++)+ "】 start ══════════");
//                    for (StackTraceElement st : stackTraceElements) {
//                        System.out.println( "StackTraceElement: " + st.toString());
//                    }
//                    System.out.println("════════════ 线程名称threadName: 【" + key.getName() + "】 end ════════════\n");
//                }


            if (this.belongClass.isIDEClass) {
                String runtimeMXBean_Name = "runtimeMXBean" + System.currentTimeMillis();
                getProcessStr = "\njava.lang.management.RuntimeMXBean  " + runtimeMXBean_Name + " = java.lang.management.ManagementFactory.getRuntimeMXBean();";
                String mProcessId_Name = "mProcessId" + System.currentTimeMillis();
                String pCodeStr1 = "\nint " + mProcessId_Name + "  =  Integer.valueOf(" + runtimeMXBean_Name + ".getName().split(\"@\")[0]).intValue();";
                getProcessStr = getProcessStr + pCodeStr1;
                // System.out.println("\n════════════ 线程名称threadName:【 " + key.getName() + "】  进程ID-ProcessID:【"+mProcessId + "】  线程ID-ThreadId:【"+key.getId()+ "】  线程序号-indexId: 【"+ (threadNum++)+ "】 start ══════════");
                //    双引号包住愿有的引号
                strCode6_fix = "\nSystem.out.println( " + "\" " + tagMethod + " \" +" + "\" \\n  线程索引序号下标 【 \"" + " + (" + threadNum_Name + ") +" + "\" 】════════════ 线程名称threadName:【\"" + " + key.getName() + " + " \"  】  进程ID-ProcessID:【 \"" + "+ " + mProcessId_Name + " +  " + " \" 】  线程ID-ThreadId:【 \" " + "+key.getId()+ " + " \" 】 \" );";
                strCode7_fix = "\nSystem.out.println( st.toString() );";
                strCode8_fix = "\nSystem.out.println( " + "\" " + tagMethod + " \" + " + "\" \\n  线程索引序号下标 【 \"" + " + (" + threadNum_Name + "++)+ " + "\"  】════════════\" );";
            } else if (this.belongClass.isAndroidClass && this.belongClass.isAPP) {
                // android.util.Log.i  int mProcessId = android.os.Process.myPid();
                String mProcessId_Name = "mProcessId" + System.currentTimeMillis();
                getProcessStr = "\n int " + mProcessId_Name + " = android.os.Process.myPid();";
                String strCode6_fix_1 = "\" " + tagMethod + " \" +" + "\" \\n  线程索引序号下标 【 \"" + " +(" + threadNum_Name + ") +" + "\" 】════════════ 线程名称threadName:【\"" + " + key.getName() + " + " \"  】  进程ID-ProcessID:【 \"" + "+ " + mProcessId_Name + " +  " + " \" 】  线程ID-ThreadId:【 \" " + "+key.getId()+ " + " \" 】 \" ";
                strCode6_fix = "\nandroid.util.Log.i(\"" + tagMethod + "\"," + strCode6_fix_1 + ");\n";
                strCode7_fix = "\nandroid.util.Log.i(\"" + tagMethod + "\"," + " \"StackTraceElement:\"  + " + strCode6_fix_1 + ");\n";
                String strCode8_fix_1 = "\" " + tagMethod + " \" +" + "\" \\n  线程索引序号下标 【 \"" + "+ (" + threadNum_Name + "++) +" + "\" 】════════════打印结束\" ";
                strCode8_fix = "\nandroid.util.Log.i(\"" + tagMethod + "\"," + " \"StackTraceElement:\"  + " + strCode8_fix_1 + ");\n";
            } else if (this.belongClass.isAndroidClass && this.belongClass.isFramework) {
                // android.util.SLog.i
                String mProcessId_Name = "mProcessId" + System.currentTimeMillis();
                getProcessStr = "\n int " + mProcessId_Name + " = android.os.Process.myPid();";
                String strCode6_fix_1 = "\" " + tagMethod + " \" + " + "\" \\n  线程索引序号下标 【 \"" + " +(" + threadNum_Name + ")+ " + "\" 】════════════ 线程名称threadName:【\"" + " + key.getName() + " + " \"  】  进程ID-ProcessID:【 \"" + "+ " + mProcessId_Name + " +  " + " \" 】  线程ID-ThreadId:【 \" " + "+key.getId()+ " + " \" 】 \" ";
                strCode6_fix = "\nandroid.util.SLog.i(\"" + tagMethod + "\"," + strCode6_fix_1 + ");\n";
                strCode7_fix = "\nandroid.util.Sog.i(\"" + tagMethod + "\"," + " \"StackTraceElement:\"  + " + strCode6_fix_1 + ");\n";
                String strCode8_fix_1 = "\" " + tagMethod + " \" + " + "\" + \\n  线程索引序号下标 【 \"" + " +(" + threadNum_Name + "++)+ " + "\" 】════════════打印结束\" ";
                strCode8_fix = "\nandroid.util.Sog.i(\"" + tagMethod + "\"," + " \"StackTraceElement:\"  + " + strCode8_fix_1 + ");\n";
            }
            return strCode1 + strCode2 + strCode3 + getProcessStr + strCode4 + strCode5 + strCode6 + strCode6_fix + strCode7 + strCode7_fix + strCode8 + strCode8_fix + strCode9;
        }


        static MethodDeclaration getMethodDeclarationFromCompilationUnit(ClassOrInterfaceDeclaration mClassOrInterfaceDeclaration, String methodIdentify) {
            MethodDeclaration returnMethod = null;
            List<MethodDeclaration> methodList = mClassOrInterfaceDeclaration.getMethods();
            int methodSize = methodList.size();
            String getDeclarationAsString = "";
            for (int i = 0; i < methodSize; i++) {
//                System.out.println("═════════第 "+ i +"个Method方法开始解析═══════════════");
                MethodDeclaration curMethodDeclaration = methodList.get(i);


//                String getName = curMethodDeclaration.getName().toString();
//                System.out.println("MethodDeclaration.getName().toString() = "+ getName);
//
//                String getNameAsString = curMethodDeclaration.getNameAsString();
//                System.out.println("MethodDeclaration.getNameAsString() = "+ getNameAsString);
//
//                String getIdentifier =  curMethodDeclaration.getName().getIdentifier();
//                System.out.println("MethodDeclaration.getName().getIdentifier() = "+ getIdentifier);
//
//                String getDeclarationAsString2=     curMethodDeclaration.getDeclarationAsString();
//                System.out.println("MethodDeclaration.getDeclarationAsString() = "+ getDeclarationAsString2);

                // String MethodDeclaration_toString =  curMethodDeclaration.toString();
                // System.out.println("MethodDeclaration_toString = "+ MethodDeclaration_toString);

                getDeclarationAsString = curMethodDeclaration.getDeclarationAsString(false, false, true);
//                System.out.println("MethodDeclaration.getDeclarationAsString(false,false,true) = "+ getDeclarationAsString);
//                System.out.println("methodIdentify = "+ methodIdentify);

                // 匹配方法   检索当前的方法与解析的方法是否相同   Zukgit1
//                System.out.println("索引:"+i+"    methodIdentify 返回 MethodDeclaration  methodIdentify= " +methodIdentify);
//                System.out.println("索引:"+i+"    methodIdentify 返回 MethodDeclaration  getDeclarationAsString= " + getDeclarationAsString );
//                System.out.println("--------------------------------------------");

                if (methodIdentify.trim().toLowerCase().equals(getDeclarationAsString.trim().toLowerCase())) {
                    NodeList<Parameter> methodParamList = curMethodDeclaration.getParameters();
//                    System.out.println("═════════参数列表Begin═══════════════");
                    for (Parameter param : methodParamList) {
//                        System.out.println("param = "+ param.toString());
                    }
//                    System.out.println("═════════参数列表End═══════════════");

                    returnMethod = curMethodDeclaration;
                    //  System.out.println("索引:"+i+"相等 将返回!  methodIdentify "+ methodIdentify + " getDeclarationAsString ="+ getDeclarationAsString);
                    return returnMethod;
                }
//                System.out.println("══════════════第 "+ i +"个Method方法解析结束══════════════");
            }


            System.out.println(" 没匹配到 methodIdentify 返回null");

            System.out.println("methodIdentify         =: " + methodIdentify + "   methodSize=" + methodSize);
            System.out.println("getDeclarationAsString =: " + getDeclarationAsString + "   methodSize=" + methodSize);
            System.out.println("══════════════");

            return returnMethod;
        }
    }


    static String COMMON_PRINT_METHOD = "    public final static <T> boolean isListType(T t) {        if (t == null) {            return false;        }        String typeInfo = t.getClass().getName();        ZukgitHoldPlace\"isListType  typeInfo =  \" + typeInfo);        String[] zListTypeArr = {\"java.util.Set\",                \"java.util.ArrayList\",                \"java.util.Collection\",                \"java.util.List\",                \"java.util.Vector\",                \"java.util.Stack\",                \"java.util.LinkedList\",                \"javafx.collections.transformation.SortedList\"        };        for (String curStr : zListTypeArr) {                        if (curStr.equals(typeInfo)) {                return true;            }        }        return false;    }    public final static <T> boolean isSetType(T t) {        if (t == null) {            return false;        }        String typeInfo = t.getClass().getName();        ZukgitHoldPlace\"isMapType  typeInfo =  \" + typeInfo);        String[] zSetTypeArr = {\"java.util.EnumSet\",                \"java.util.SortedSet\",                \"java.util.concurrent.CopyOnWriteArraySet\",                \"java.util.LinkedHashSet\",                \"java.util.HashSet\",                \"java.util.TreeSet\",                \"android.util.SparseArray\",                \"android.util.ArraySet\"        };        for (String curStr : zSetTypeArr) {            if (curStr.equals(typeInfo)) {                return true;            }        }        return false;    }    public final static <T> boolean iQueueType(T t) {        if (t == null) {            return false;        }        String typeInfo = t.getClass().getName();        ZukgitHoldPlace\"isMapType  typeInfo =  \" + typeInfo);        String[] zQueueTypeArr = {                \"java.util.concurrent.LinkedBlockingDeque\",                \"java.util.Queue\",                \"java.util.ArrayDeque\",                \"java.util.concurrent.BlockingQueue\",                \"java.util.concurrent.LinkedTransferQueue\",                \"java.util.PriorityQueue\",                \"java.util.concurrent.LinkedBlockingQueue\",        };        for (String curStr : zQueueTypeArr) {            if (curStr.equals(typeInfo)) {                return true;            }        }        return false;    }    public final static <T> boolean isShuZuType(T t) {        if (t == null) {            return false;        }        String typeInfo = t.getClass().getName();        ZukgitHoldPlace\"isMapType  typeInfo =  \" + typeInfo);        String[] zMapTypeArr = {\"[Ljava.lang.String;\",                  \"[Ljava/lang/Object;\",                  \"[I\",                 \"[B\",                  \"[C\",                 \"[S\",                 \"[J\",                 \"[F\",                \"[D\"            };        if(typeInfo.startsWith(\"[L\")){            return true;        }        for (String curStr : zMapTypeArr) {            if (curStr.equals(typeInfo)) {                return true;            }        }        return false;    }    public final static <T> boolean isMapType(T t) {        if (t == null) {            return false;        }        String typeInfo = t.getClass().getName();        ZukgitHoldPlace\"isMapType  typeInfo =  \" + typeInfo);        String[] zMapTypeArr = {\"java.util.HashMap\",                \"java.util.TreeMap\",                \"java.util.LinkedHashMap\",                \"java.util.WeakHashMap\",                \"java.util.concurrent.ConcurrentHashMap\",                \"java.util.Hashtable\",                \"android.util.ArrayMap\"};        for (String curStr : zMapTypeArr) {            if (curStr.equals(typeInfo)) {                return true;            }        }        return false;    }    public final static <T> void zPrintLog(T t) {      if( t ==null){return ;}        ZukgitHoldPlace\" 当前类型:   \" + t.getClass().getName());        if (isListType(t)) {            ZukgitHoldPlace\"List数据类型类型   \");            if (t != null) {                Object[] objectList = ((java.util.List) t).toArray();                for (int zindex = 0; zindex < objectList.length; zindex++) {                    ZukgitHoldPlace\" List   \" + \"ArrayList<Date> dateList  index =\" + zindex + \" value :\" + objectList[zindex].toString());                }            } else {                ZukgitHoldPlace\" List   \" + \"ArrayList<Date> dateList is null !\");            }        } else if (isSetType(t)) {            ZukgitHoldPlace\"Set数据类型类型   \");            if (t != null) {                Object[] objectList = ((java.util.Set) t).toArray();                for (int zindex = 0; zindex < objectList.length; zindex++) {                    ZukgitHoldPlace\" Set   \" + \"ArrayList<Date> dateList  index =\" + zindex + \" value :\" + objectList[zindex].toString());                }            } else {                ZukgitHoldPlace\" Set   \" + \"ArrayList<Date> dateList is null !\");            }        } else if (iQueueType(t)) {            if (t != null) {                Object[] objectList = ((java.util.Queue) t).toArray();                for (int zindex = 0; zindex < objectList.length; zindex++) {                    ZukgitHoldPlace\" Queue :   \" + \"ArrayList<Date> dateList  index =\" + zindex + \" value :\" + objectList[zindex].toString());                }            } else {                ZukgitHoldPlace\" Queue   \" + \"ArrayList<Date> dateList is null !\");            }        } else if (isMapType(t)) {            ZukgitHoldPlace\"Map数据类型类型  \");            if (t != null) {                java.util.Map.Entry<String, String> entry = null;                java.util.Iterator iterator = ((java.util.Map) t).entrySet().iterator();                int mZindex = 0;                while (iterator.hasNext()) {                    entry = ( java.util.Map.Entry<String, String>) iterator.next();                    ZukgitHoldPlace\" Map   \" + \" stringMap    index =\" + mZindex + \"     entry.Key= \" + entry.getKey() + \" entry.Value=\" + entry.getValue());                    mZindex++;                }            } else {                ZukgitHoldPlace\" Map   \" + \" t is null !\");            }        } else if(isShuZuType(t)){            ZukgitHoldPlace\"[] 数组类型格式  \");            if (t != null) {                String Arrtype = t.getClass().getName();                java.util.ArrayList<Object>  valueList= new  java.util.ArrayList();                int[] intArr=null;                byte[] byteArr=null;                char[] charArr=null;                short[] shortArr=null;                long[] longArr=null;                float[] floatArr=null;                double[] doubleArr= null;                boolean[] booleanArr = null;                Object[]  ObjectArr =null;                if(Arrtype.equals(\"[I\")){                       intArr = (int[])t ;                    valueList.add( java.util.Arrays.toString(intArr));                } else if(Arrtype.equals(\"[J\")){                      longArr = (long[])t ;                    valueList.add( java.util.Arrays.toString(longArr));                }else if(Arrtype.equals(\"[F\")){                    floatArr = (float[])t ;                    valueList.add( java.util.Arrays.toString(floatArr));                }else if(Arrtype.equals(\"[D\")){                    doubleArr = (double[])t ;                    valueList.add( java.util.Arrays.toString(doubleArr));                }else if(Arrtype.equals(\"[S\")){                    shortArr = (short[])t ;                    valueList.add( java.util.Arrays.toString(shortArr));                }else if(Arrtype.equals(\"[C\")){                    charArr = (char[])t ;                    valueList.add( java.util.Arrays.toString(charArr));                }else if(Arrtype.equals(\"[B\")){                    byteArr = (byte[])t ;                    valueList.add( java.util.Arrays.toString(byteArr));                }else if(Arrtype.equals(\"[Z\")){                    booleanArr = (boolean[])t ;                    valueList.add( java.util.Arrays.toString(booleanArr));                }else{                    ObjectArr = (Object[]) t ;                    for (Object curObject:ObjectArr ) {                        valueList.add(curObject);                    }                }                for (int zindex = 0; zindex < valueList.size(); zindex++) {                    ZukgitHoldPlace\" shuzu[]   \" + \"XXXX[]   zindex = \" + zindex + \"   :  value= \" + valueList.get(zindex).toString());                }            } else {                ZukgitHoldPlace\" zukgit_B8_Test_method3   \" + \"strArr is null !\");            }        }else {            ZukgitHoldPlace\"    baseObject = true   基本Object对象类型   toString()=\" + t);        }    }";

//System.out.println("")
//ZukgitHoldPlace
//System.out.println(
//android.uti.Log.i("XXX",
//android.Suti.Log.i("XXX",

}