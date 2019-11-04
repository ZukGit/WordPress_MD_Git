

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class C3_Zanalyze {
    static String curProjectPath = System.getProperty("user.dir");


    static int mSumDirNum = 0;
    static int mSumSingleFileNum = 0;
    static ArrayList<Zanalyze> mZanalyzeList = new ArrayList<>();  // 待分析类的集合



    //=======================WIFI-BEGIN=================
    // WIFI 相关目录
    //wifi-1. 	/frameworks/opt/net/wifi/service/java/com/android/server/wifi/
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/frameworks/opt/net/wifi/service/java/com/android/server/wifi");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }

    //wifi-2.     /frameworks/base/wifi/java/android/net/wifi/
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/frameworks/base/wifi/java/android/net/wifi");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }




    //wifi-3.    /motorola/packages/apps/WpaConfig/src/com/motorola/config/wifi/
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/motorola/packages/apps/WpaConfig/src/com/motorola/config/wifi");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }



    // wifi-4.    /frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }

    // wifi-5. /frameworks/base/packages/SystemUI/src/com/android/systemui/statusbar/policy
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/frameworks/base/packages/SystemUI/src/com/android/systemui/statusbar/policy");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add("Wifi");
        filterList.add("Ethernet");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }


// wifi-6    /packages/apps/Settings/src/com/android/settings/wifi/

    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "/packages/apps/Settings/src/com/android/settings/wifi");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }

//=======================WIFI-END=================




	/*
    // 把 Settings下的java文件都打包成一个文件  输出 html 页面  以及 md文件
    //  文件名称为 packages-apps-Settings_java_Analysis.html
    //  文件名称为 packages-apps-Settings_java_Analysis.md
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "\\packages\\apps\\Settings");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add(".java");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }


    // 把  /packages/apps  和 frameworks/base 下的包含 values-zh-rCN/string.xml  的文件打包成一个文件
    //  文件名称为  packages-apps-frameworks-base-values-zh-rCN-_Analysis.txt
    static {
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(curProjectPath + "\\packages\\apps");
        pathList.add(curProjectPath + "\\frameworks\\base");
        ArrayList<String> filterList = new ArrayList<String>();
        filterList.add("\\values-zh-rCN\\");
        Zanalyze mZanalyze = new Zanalyze(pathList, filterList);
        mZanalyzeList.add(mZanalyze);
    }
	*/

    static File current_juwuba_template_file = new File(System.getProperty("user.dir") + File.separator + "C3_juwuba_template.html");
    static String current_juwuba_html_template_content = "";


    public static void main(String[] args) {

        if (current_juwuba_template_file != null && current_juwuba_template_file.exists()) {
            current_juwuba_html_template_content = readTemplateStringFromFile(current_juwuba_template_file).toString();
            System.out.println("================================ current_juwuba_template_content   begin =================================");
            System.out.println(" current_juwuba_template_content  = " + current_juwuba_html_template_content);
            System.out.println("================================ current_juwuba_template_content   end =================================");
        } else {
            System.out.println(" current_juwuba_template_content  = null ");
        }

        for (Zanalyze mZanalyze : mZanalyzeList) {
            mZanalyze.toShowDetailInfo();
            mZanalyze.todoAnalysis();
        }

    }


    static class Zanalyze {
        ArrayList<String> mProjectPathList; // 当前要分析的工程路径
        String zCurProject;
        ArrayList<File> mFilePathList = new ArrayList<>();
        Set<File> allDirFileSet = new HashSet<>();  // 工程下所有的 文件夹文件
        Set<File> allSimpleFileSet = new HashSet<>();   // 当前工程下所有非文件夹的 文件
        int sumDirFile = 0;
        int sumSimpleFile = 0;

        // 以过滤词 定义输出文件    String.xml
        ArrayList<String> mfilterTypeList; //  路径的过滤器    比如 .java    /values-zh-rCN/   等制定后缀 或者制定路径关键词
        Map<String, ArrayList<File>> dataMap;   //  【.java,ArrayList<javaFile>】  【values-zh-rCN, ArrayList<values-zh-rCN>】

        Zanalyze() {

        }

        Zanalyze(ArrayList<String> mProjectPathList_param, ArrayList<String> filterTypeList_param) {
            mProjectPathList = mProjectPathList_param;
            mfilterTypeList = filterTypeList_param;
            sumDirFile = 0;
            sumSimpleFile = 0;
            zCurProject = getProjectPath(mProjectPathList);
            initDirFileSet();
            initSimpleFileSet();
            initFilterMap();
        }

        String getProjectPath(ArrayList<String> mProjectPathList_param) {
            String path = "";
            if (mProjectPathList_param != null && mProjectPathList_param.size() > 0) {
                for (int i = 0; i < mProjectPathList_param.size(); i++) {
                    String curPath = mProjectPathList_param.get(i);
                    path = path + "_" + curPath;

                }
            }
            path = path.replace(curProjectPath, "");
            while (path.startsWith("_")) {
                path = path.substring(1);
            }
            return path;
        }

        void toShowShortInfo() {
            System.out.println("================================ toShowShortInfo Begin =================================");
            if (mProjectPathList != null) {
                System.out.println("当前分析路径:" + Arrays.toString(mProjectPathList.toArray()));
            } else {
                System.out.println("mProjectPathList 当前分析路径为空！");
            }

            if (allDirFileSet != null) {
                System.out.println("当前文件夹数量:" + allDirFileSet.size());
            } else {
                System.out.println("allDirFileSet 当前文件夹数量为 0 !");
            }

            if (allSimpleFileSet != null) {
                System.out.println("当前单一文件数量:" + allSimpleFileSet.size());
            } else {
                System.out.println("allSimpleFileSet 当前单一文件数量为 0 !");
            }

            if (dataMap != null) {
                Map.Entry<String, ArrayList<File>> entry;
                Iterator iterator = dataMap.entrySet().iterator();
                int filterIndex = 1;
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String filterStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileList = entry.getValue();  //Map的Value
                    System.out.println("过滤索引:" + filterIndex + "    当前过滤条件:" + filterStr + "   过滤到的文件数量:" + fileList.size());
                    filterIndex++;
                }
            } else {
                System.out.println("dataMap 当前过滤文件数量为0！");
            }
            System.out.println("================================ toShowShortInfo End =================================");
        }


        void toShowDetailInfo() {
            System.out.println("================================ toShowDetailInfo Begin =================================");
            if (mProjectPathList != null) {
                System.out.println("当前分析路径:" + Arrays.toString(mProjectPathList.toArray()));
            } else {
                System.out.println("mProjectPathList 当前分析路径为空！");
            }

            if (allDirFileSet != null) {
                System.out.println("当前文件夹数量:" + allDirFileSet.size());
                int dirFileIndex = 1;
                for (File dirFile : allDirFileSet) {
                    System.out.println("当前文件夹Dir索引:" + dirFileIndex + "  文件路径: " + dirFile.getAbsolutePath());
                    dirFileIndex++;
                }
            } else {
                System.out.println("allDirFileSet 当前文件夹数量为 0 !");
            }

            if (allSimpleFileSet != null) {
                System.out.println("当前单一文件数量:" + allSimpleFileSet.size());

                int simpleFileIndex = 1;
                for (File simpleFile : allSimpleFileSet) {
                    System.out.println("当前文件SimpleFile索引:" + simpleFileIndex + "  SimpleFile文件路径: " + simpleFile.getAbsolutePath());
                    simpleFileIndex++;
                }

            } else {
                System.out.println("allSimpleFileSet 当前单一文件数量为 0 !");
            }

            if (dataMap != null) {
                Map.Entry<String, ArrayList<File>> entry;
                Iterator iterator = dataMap.entrySet().iterator();
                int filterIndex = 1;
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String filterStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileList = entry.getValue();  //Map的Value
                    System.out.println("========过滤索引:" + filterIndex + "    当前过滤条件:" + filterStr + "   过滤到的文件数量:" + fileList.size() + " ===========");

                    for (int i = 0; i < fileList.size(); i++) {
                        System.out.println("文件索引:" + i + "文件名称: " + fileList.get(i).getAbsolutePath());
                    }

                    filterIndex++;
                }
            } else {
                System.out.println("dataMap 当前过滤文件数量为0！");
            }
            System.out.println("================================ toShowDetailInfo End =================================");
        }


        void initFilterMap() {
            if (dataMap == null) {
                dataMap = new HashMap<String, ArrayList<File>>();
            }
            if (mfilterTypeList != null && mfilterTypeList.size() > 0) {
                for (int i = 0; i < mfilterTypeList.size(); i++) {
                    ArrayList<File> tempSimpleFileList = matchFilterString(allSimpleFileSet, mfilterTypeList.get(i));
                    dataMap.put(mfilterTypeList.get(i), tempSimpleFileList);
                }
            }
        }

        ArrayList<File> matchFilterString(Set<File> simpleFileSet, String filterStr) {
            if (simpleFileSet == null || simpleFileSet.size() < 1 || filterStr == null || "".equals(filterStr.trim())) {
                return null;   // 参数不对的情况下返回为null
            }
            ArrayList<File> simpleFileList = new ArrayList<File>();

            int simpleFileIndex = 1;
            for (File simpleFile : allSimpleFileSet) {
                String absolutePath = simpleFile.getAbsolutePath();
                System.out.println("simpleFileIndex=" + simpleFileIndex + "   PATH:" + absolutePath);

                // 去除 test/目录下的 java文件  这部分文件不分析
                if (filterStr.toLowerCase().endsWith(".java")) {  // 对于Java文件的过滤   去除掉  tests  测试的Java文件
                    if (absolutePath != null && absolutePath.contains(filterStr) && !absolutePath.contains("\\tests\\")) {
                        simpleFileList.add(simpleFile);
                    }
                } else {
                    if (absolutePath != null && absolutePath.contains(filterStr)) {
                        simpleFileList.add(simpleFile);
                    }
                }
                simpleFileIndex++;
            }

            simpleFileList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    int length1 = name1.length();
                    int length2 = name2.length();
                    // 文件名包含的情况 短的文件在前
                    if (length1 >= length2 && name1.contains(name2)) {
                        return -1;
                    }
                    if (length2 >= length1 && name2.contains(name1)) {
                        return 1;
                    }

                    // 当两个文件的名称一致时返回 0
                    if (name1.equals(name2)) {
                        return 0;
                    }

                    int minLength = length1 > length2 ? length2 : length1;

                    String name1_fix = name1.substring(0, minLength);
                    String name2_fix = name2.substring(0, minLength);
                    for (int i = 0; i < minLength; i++) {
//                    System.out.println("charIndex = "+i);
//                    System.out.println("name1_fix.toUpperCase() = "+name1_fix.toUpperCase() + "FilePath="+ o1.getAbsolutePath() +"    name1="+name1);
//                    System.out.println("name2_fix.toUpperCase() = "+name2_fix.toUpperCase()+ "FilePath="+ o2.getAbsolutePath()+  "    name2="+name2);
                        char charName1 = name1_fix.toUpperCase().charAt(i);
                        char charName2 = name2_fix.toUpperCase().charAt(i);

                        if (charName1 == charName2) {
                            continue;
                        }
                        // ASCII码   A-65    Z-90  A 在前面
                        if (charName1 > charName2) {
                            return 1;  // Z 在后面
                        }
                        if (charName1 < charName2) {
                            return -1;
                        }
                    }

                    return 0;
                }
            });

            return simpleFileList;
        }


        void initDirFileSet() {
            if (mProjectPathList != null && mProjectPathList.size() > 0) {
                if (mFilePathList == null) {
                    mFilePathList = new ArrayList<File>();
                }
                for (int i = 0; i < mProjectPathList.size(); i++) {
                    mFilePathList.add(new File(mProjectPathList.get(i)));
                }

            }
            if (mFilePathList != null && mFilePathList.size() > 0) {
                sumDirFile = 0;
                for (File itemFile : mFilePathList) {
                    System.out.println("Z0");
                    if (itemFile != null && !itemFile.exists() && !itemFile.isDirectory()) {
                        System.out.println("Z1");
                        continue;
                    }  // 过滤掉非文件夹

                    sumDirFile = addAllFileDir(itemFile);
                    System.out.println("Z2");
                }
                sumDirFile = mSumDirNum;
                mSumDirNum = 0;   // 把计数的全局变量回归为0
            }


        }


        int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象
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


        boolean isEmptyDirFile(File dirFile) {
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

        ArrayList<File> getChildDirFileMethod(File dirFile) {
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

        void initSimpleFileSet() {
            int fileIndex = 1;
            for (File dirFile : allDirFileSet) {
                System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
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


        void todoAnalysis() {
            if (dataMap != null) {
                Map.Entry<String, ArrayList<File>> entry;
                Iterator iterator = dataMap.entrySet().iterator();
                int filterIndex = 1;

                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    System.out.println(" curfilterIndexTime = " + filterIndex);
                    String filterStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileList = entry.getValue();  //Map的Value
                    String OutFileName = getOutFileName(filterStr);
                    int fileSize = fileList.size();
                    if (filterStr.toLowerCase().endsWith(".java")) {   // 对java 文件 进行 Parser的处理
                        StringBuilder sb1 = new StringBuilder();
                        int simpleSortFileIndex_Java = 1;
                        for (File simpleSortListFile_Java : fileList) {
                            String absolutePath = simpleSortListFile_Java.getAbsolutePath();
                            System.out.println("fileSize = " + fileSize + "  simpleFile_Java=" + simpleSortFileIndex_Java + "   PATH:" + absolutePath);

                            try {
                                System.out.println("fileSize = " + fileSize + "================================javaParser解析" + absolutePath + " 开始===================================");
                                CompilationUnit mCompilationUnit = JavaParser.parse(simpleSortListFile_Java);
                                sb1.append("文件索引:" + simpleSortFileIndex_Java + "  当前文件路径: \n" + simpleSortListFile_Java.getAbsolutePath() + "\n" + "文件夹路径:\n" + simpleSortListFile_Java.getParent() + "\n" + mCompilationUnit.toString());

                                //    System.out.println(mCompilationUnit);
                                System.out.println("fileSize  = " + fileSize + " ================================javaParser解析" + absolutePath + " 结束===================================");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            simpleSortFileIndex_Java++;
                        }
                        writeContentToFile(new File(OutFileName), sb1.toString());  // java 文件内容 都放到 一个文件中
                        StringBuilder sb2 = new StringBuilder();      //  生成 md 文件的 StringBuilder

                        int simpleSortFileIndex_Java2 = 1;
                        String out_md = getOutFileMDName(filterStr);
                        // String category1 = "# "+zCurProject+ " \n";   // md
                        String category1 = "<h1> " + zCurProject + " </h1>\n";   // html

                        sb2.append(category1);
                        for (File simpleSortListFile_Java : fileList) {

                            String absPath = simpleSortListFile_Java.getAbsolutePath();
                            String tempPath = absPath.trim().substring(curProjectPath.trim().length());
                            tempPath = tempPath.replace("\\", "/");
//                            if(absPath.indexOf(curProjectPath) > 0){
//                                tempPath = absPath.substring(absPath.indexOf(curProjectPath)+curProjectPath.length());
//                            }

                            System.out.println("tempPath = " + tempPath + "    curProjectPath = " + curProjectPath + "    absPath=" + absPath);
                            boolean isFramework = absPath.contains("frameworks");
                            boolean isAndroid = absPath.contains("packages");
                            //  # 包
                            // ## 类
                            // ### 类方法


//tempPath = /packages/apps/Settings/src/com/android/settings/dashboard/conditional/AbnormalRingerConditionBase.java
//curProjectPath = C:\Users\zhuzj5\IdeaProjects\J1
//absPath=C:\Users\zhuzj5\IdeaProjects\J1\packages\apps\Settings\src\com\android\settings\dashboard\conditional\AbnormalRingerConditionBase.java


                            // String category2 = "\n## "+simpleSortListFile_Java.getName()+"\n";  // md
                            String category2 = "\n<h2> " + simpleSortListFile_Java.getName() + "</h2>\n";  // html
// <a href="http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/src/com/android/settings/dashboard/conditional/AbnormalRingerConditionBase.java">AbnormalRingerConditionBase.java</a>
                            // String openGroup1 = "<a   target=\"_blank\"  href=\"http://androidxref.com/9.0.0_r3/xref";

                            String openGroup1 = "<a   target=\"_blank\"  href=\"https://www.androidos.net.cn/android/9.0.0_r8/xref";
                            String openGroup2 = tempPath;   // 这里获取得到 AOSP 中的相对位置
                            String openGroup3 = "\">";
                            String openGroup4 = simpleSortListFile_Java.getName();
                            String openGroup5 = "</a>";
                            String linka = "\n" + openGroup1 + openGroup2 + openGroup3 + openGroup4 + openGroup5 + "\n";


                            //    String strBlock1 = "\n```\n";     //  md
                            String strBlock1 = "\n<pre><code class=\"\">\n";     //  html


                            String codeStr1 = "static {\n";
                            String codeStr2 = "";
                            String codeStr3 = "";
                            String codeStr4 = "\nArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();";
                            String codeStr5 = "";
                            String codeStr6 = "";
                            String codeStr7 = "";
                            String codeStr8 = "\n}";
                            if (isFramework) {  // mZFrameworkClass_Path
                                codeStr2 = "String mZFrameworkClass_Path = curProjectPath+ \"" + tempPath + "\";\n";
                                codeStr3 = "\nZAndroidFrameworkClass mFrameworkClass = new ZAndroidFrameworkClass(mZFrameworkClass_Path);\n";
                                codeStr5 = "\n /* methodList.add(new ZMethod(mFrameworkClass,\"boolean\",\"onSwitchToggled\",\"boolean isChecked \")); */\n";
                                codeStr6 = "mFrameworkClass.addZMethod(methodList);\n";
                                codeStr7 = "ZClassList.add(mFrameworkClass);";
                            } else {
                                codeStr2 = "String mZAndroidAPPClass_Path = curProjectPath+ \"" + tempPath + "\";\n";
                                codeStr3 = "\nZAndroidAPPClass mZAndroidAPPClass = new ZAndroidAPPClass(mZAndroidAPPClass_Path);";
                                codeStr5 = "\n// methodList.add(new ZMethod(mZAndroidAPPClass,\"boolean\",\"onSwitchToggled\",\"boolean isChecked \"));\n";
                                codeStr6 = "\nmZAndroidAPPClass.addZMethod(methodList);";
                                codeStr7 = "\nZClassList.add(mZAndroidAPPClass);";
                            }
                            //    String strBlock2 = "\n```\n";    // md
                            String strBlock2 = "\n</code></pre>\n";    // md

                            sb2.append(category2);
                            if (isAndroid || isFramework) {
                                sb2.append(linka);
                            }

                            String preCode = codeStr1 + codeStr2 + codeStr3 + codeStr4 + codeStr5 + codeStr6 + codeStr7 + codeStr8;
                            preCode = preCode.replace("<", "&lt;");
                            preCode = preCode.replace(">", "&gt;");

                            String codeAll = strBlock1 + preCode + strBlock2;
                            String contentMD = readMethodFromJavaFile(simpleSortListFile_Java);
                            sb2.append(codeAll + contentMD);
                        }

                        writeContentToFile(new File(out_md), sb2.toString());


                        String html_template_str = new String(current_juwuba_html_template_content);  // 生成 html 文件的  StringBuilder  先放入 html模板
                        String outfile_html_name = getOutFileHTMLName(filterStr);
                        // tag 标题
                        html_template_str = html_template_str.replace("ZukgitHeadTitleHoldPlace_1", outfile_html_name);
                        html_template_str = html_template_str.replace("ZukgitContentTitleHoldPlace_2", zCurProject);
                        Date day = new Date();
                        SimpleDateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        html_template_str = html_template_str.replace("ZukgitTimeStampHoldPlace_3", simpleDf.format(day));
                        html_template_str = html_template_str.replace("ZukgitMDContentHoldPlace_4", sb2.toString());

                        writeContentToFile(new File(outfile_html_name), html_template_str.toString());

                    } else {    //  对于其他文件进行   IO的处理


                        StringBuilder sb1 = new StringBuilder();
                        StringBuilder sb2_html = new StringBuilder();
                        int simpleSortFileIndex_other = 1;
                        for (File simpleFile : fileList) {
                            String absolutePath = simpleFile.getAbsolutePath();
                            String absolutePath_fixpre = absolutePath.replace(curProjectPath, "");
                            System.out.println("absolutePath_fixpre = " + absolutePath_fixpre + "    simpleFile_Java=" + simpleSortFileIndex_other + "   PATH:" + absolutePath);

                            try {
                                System.out.println("================================IO文件解析" + absolutePath + " 开始===================================");
                                sb1.append("文件索引:" + simpleSortFileIndex_other + "  当前文件路径: \n" + simpleFile.getAbsolutePath() + "\n" + "文件夹路径:\n" + simpleFile.getParent() + "\n" + readStringFromFile(simpleFile).toString());

                                String codeh1 = "<h1>  【" + simpleSortFileIndex_other + "】" + absolutePath_fixpre + "</h1>";  // xxxx.zrsh.string.xml
                                //    System.out.println(mCompilationUnit);
                                String strBlock1 = "\n<pre><code class=\"\">\n";     //  html
                                String codePreCode = readStringFromFile(simpleFile).toString();
                                String codePreCode_fix = codePreCode.replace("<", "&lt;");
                                codePreCode_fix = codePreCode_fix.replace(">", "&gt;");
                                String strBlock2 = "\n</code></pre>\n";    //
                                sb2_html.append(codeh1 + strBlock1 + codePreCode_fix + strBlock2);

                                System.out.println("================================IO文件解析" + absolutePath + " 结束===================================");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            simpleSortFileIndex_other++;
                        }


                        writeContentToFile(new File(OutFileName), sb1.toString());

                        String html_template_str = new String(current_juwuba_html_template_content);  // 生成 html 文件的  StringBuilder  先放入 html模板
                        String outfile_html_name = getOutFileHTMLName(filterStr);
                        String htmlTitle = outfile_html_name.replace(".html", "");
                        // tag 标题
                        html_template_str = html_template_str.replace("ZukgitHeadTitleHoldPlace_1", outfile_html_name);
                        html_template_str = html_template_str.replace("ZukgitContentTitleHoldPlace_2", htmlTitle + "文件集合");
                        Date day = new Date();
                        SimpleDateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        html_template_str = html_template_str.replace("ZukgitTimeStampHoldPlace_3", simpleDf.format(day));
                        html_template_str = html_template_str.replace("ZukgitMDContentHoldPlace_4", sb2_html.toString());
                        writeContentToFile(new File(outfile_html_name), html_template_str);
                    }
                    filterIndex++;
                }
            } else {
                System.out.println("dataMap 当前过滤文件数量为0！ ");
            }


        }


        //  # 包
        // ## 类
        // ### 类方法
        String readMethodFromJavaFile(File javaFile) {
            StringBuilder sb1 = new StringBuilder();
            String javaName = javaFile.getName();
            String absPath = javaFile.getAbsolutePath();
            boolean isFramework = false;
            if (absPath.contains("frameworks")) {

                isFramework = true;
            }
            if (javaName.endsWith(".java")) {
                javaName = javaName.substring(0, javaName.length() - ".java".length());
            }
            try {
                CompilationUnit mCompilationUnit = JavaParser.parse(javaFile);
                List<Node> childNodeList = mCompilationUnit.getChildNodes();
                //  ClassOrInterfaceDeclaration mClassOrInterfaceDeclaration =
                Optional<ClassOrInterfaceDeclaration> class_opt = mCompilationUnit.getClassByName(javaName);
                ClassOrInterfaceDeclaration mClassDeclaration = null;
                if (class_opt.isPresent()) {
                    mClassDeclaration = class_opt.get();

                }

                if (mClassDeclaration != null) {


                    List<FieldDeclaration> fieldDecList = mClassDeclaration.getFields();

                    ArrayList<FieldDeclaration> fieldDecList_arr = new ArrayList<FieldDeclaration>();

                    for (FieldDeclaration mfield : fieldDecList) {
                        fieldDecList_arr.add(mfield);
                    }
                    fieldDecList_arr.sort(new Comparator<FieldDeclaration>() {
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
                    });


                    if (fieldDecList_arr != null) {

                        String strFieldh3 = "\n<h3> Field属性列表 " + javaName + "</h3>\n";  // html
                        sb1.append(strFieldh3);

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


// 继续点
                            String metaModelFieldName = fieldItem.getMetaModel().getMetaModelFieldName();
                            String mQualifiedClassName = fieldItem.getMetaModel().getQualifiedClassName();
                            String mTypeNam = fieldItem.getMetaModel().getTypeName();
                            String mTypeNameGenerified = fieldItem.getMetaModel().getTypeNameGenerified();
                            String getMetaModelString = fieldItem.getMetaModel().toString();


                            PrettyPrinterConfiguration mPrintConfig = new PrettyPrinterConfiguration();
                            mPrintConfig.setPrintComments(false);
                            fieldItem.toString(mPrintConfig);
                            String mTypeAndName = fieldItem.toString();

                            while (mTypeAndName.contains("/*") && mTypeAndName.trim().startsWith("/*") && mTypeAndName.contains("*/")) {
                                mTypeAndName = mTypeAndName.substring(mTypeAndName.indexOf("*/") + 2).trim();
                            }

                            if (mTypeAndName.contains("=")) {
                                mTypeAndName = mTypeAndName.substring(0, mTypeAndName.indexOf("=")).trim();
                            }


                            if (mTypeAndName.contains("\n")) {
                                mTypeAndName = mTypeAndName.substring(mTypeAndName.indexOf("\n") + 1).trim();
                            }

                            if (mTypeAndName.contains("<Object>")) {
                                mTypeAndName = mTypeAndName.replace("<Object>", "<ZObject>");
                            }


                            mTypeAndName = " " + mTypeAndName;
                            while (mTypeAndName.contains(" public ") || mTypeAndName.contains(" protected ") || mTypeAndName.contains(" private ")
                                    || mTypeAndName.contains(" transient ")
                                    || mTypeAndName.contains(" static ")
                                    || mTypeAndName.contains(" final ") || mTypeAndName.contains(" volatile ")) {

                                mTypeAndName = mTypeAndName.replaceAll(" public ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" protected ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" private ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" transient ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" static ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" final ", " ");
                                mTypeAndName = mTypeAndName.replaceAll(" volatile ", " ");
                                mTypeAndName = " " + mTypeAndName;
                            }
//   public || protected || private
// transient
// static
// final   volatile

                            System.out.println("属性索引:" + i + "     属性字符:" + fieldItem.toString());
                            System.out.println("属性索引:" + i + "     修改属性字符:" + mTypeAndName);
                            System.out.println("属性索引:" + i + "     metaModelFieldName: " + metaModelFieldName);
                            System.out.println("属性索引:" + i + "     mQualifiedClassName: " + mQualifiedClassName);
                            System.out.println("属性索引:" + i + "     mTypeNam: " + mTypeNam);
                            System.out.println("属性索引:" + i + "     mTypeNameGenerified: " + mTypeNameGenerified);
                            System.out.println("属性索引:" + i + "     getMetaModelString: " + getMetaModelString);


                            String str0 = "\n<h4> " + fieldModifyStr + " " + mTypeAndName + " </h4>\n";  // html
                            sb1.append(str0);

                            String mdBlockStr1 = "\n<pre><code class=\"\">\n";  // html

                            String fieldCode = fieldItem.toString();


                            fieldCode = fieldCode.replace("<", "&lt;");
                            fieldCode = fieldCode.replace(">", "&gt;");

                            if (fieldCode.contains("<Object>")) {
                                fieldCode = fieldCode.replace("<Object>", "<ZObject>");
                            }

                            String fielddetail = fieldCode;
                            String mdBlockStr2 = "\n</code></pre>\n"; // html

                            sb1.append(mdBlockStr1 + fielddetail + mdBlockStr2);
                            // 在 这里操作属性


                        }


                    }


                    List<MethodDeclaration> methodList = mClassDeclaration.getMethods();

                    ArrayList<MethodDeclaration> methodDecList_arr = new ArrayList<MethodDeclaration>();
                    for (MethodDeclaration mMethodDec : methodList) {
                        methodDecList_arr.add(mMethodDec);
                    }


                    methodDecList_arr.sort(new Comparator<MethodDeclaration>() {
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
                    });


                    String strMethodh3 = "\n<h3>Method方法列表 " + javaName + "</h3>\n";  // html
                    sb1.append(strMethodh3);

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

                        System.out.println("returnString = " + returnString + "         methodName = " + methodName + "  paramStr = " + paramStr);
                        //  methodDec.g
                        // 继续点


                        String codeStr1 = "";
                        if (isFramework) {
//   methodList.add(new ZMethod(mFrameworkClass,"boolean","onSwitchToggled","boolean isChecked "));
                            codeStr1 = "methodList.add(new ZMethod(mFrameworkClass,\"" + returnString.trim() + " \" ,\" " + methodName.trim() + "\" , \"" + paramStr.trim() + "\")); ";
                        } else {
                            //    methodList.add(new ZMethod(mZAndroidAPPClass,"boolean","onSwitchToggled","boolean isChecked "));
                            codeStr1 = "methodList.add(new ZMethod(mZAndroidAPPClass,\"" + returnString.trim() + "\" ,\"" + methodName.trim() + "\", \"" + paramStr.trim() + "\")); ";
                        }

                        //    String str0 = "\n### "+ modifyWord+" "+methodName + "(" + paramStr + ")\n";  // md
                        String str0 = "\n<h4> " + modifyWord + " " + methodName + "(" + paramStr + ") </h4>\n";  // html

                        //     String mdBlockStr1 = "\n```\n";  // md
                        String mdBlockStr1 = "\n<pre><code class=\"\">\n";  // html
                        String str1 = methodDec.getDeclarationAsString(true, true, true) + "\n\n";
                        String str2 = codeStr1 + "\n";

                        //    String mdBlockStr2 = "\n```\n"; // md
                        String mdBlockStr2 = "\n</code></pre>\n"; // html

                        String inprecodeStr = str1 + str2;


                        inprecodeStr = inprecodeStr.replace("<", "&lt;");
                        inprecodeStr = inprecodeStr.replace(">", "&gt;");

                        String methodResultStr = str0 + mdBlockStr1 + inprecodeStr + mdBlockStr2;
                        sb1.append(methodResultStr);


                    }


                }

                //    System.out.println(mCompilationUnit);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return sb1.toString();
        }


        String getOutFileMDName(String filterStr) {
            String name = filterStr;
            String pathName = "";
            if (mProjectPathList != null) {
                for (String pathItem : mProjectPathList) {
                    pathName = pathName.trim() + "_" + pathItem.trim();
                }
            }

            pathName = pathName.replace(curProjectPath, "");


            pathName = pathName + "_" + filterStr;
            while (pathName.contains("\\")) {
                pathName = pathName.replace("\\", "-");
            }
            while (pathName.contains("/")) {
                pathName = pathName.replace("/", "-");
            }


            while (pathName.contains(".")) {
                pathName = pathName.replace(".", "_");
            }

            // C:\Users\zhuzj5\IdeaProjects\J1\packages-apps-Settings_-values-zh-rCN-_Analysis.txt
            pathName = pathName.replace("_-", "-");
            pathName = pathName.replace("-_", "-");
            pathName = pathName + "_Analysis.md";

            // _-packages-apps-Settings__java_Analysis.txt
            while (pathName.startsWith("-") || pathName.startsWith("_") || pathName.startsWith(".")) {
                pathName = pathName.substring(1);
            }

            pathName = pathName.replace("__", "_");
            return pathName;
        }


        String getOutFileHTMLName(String filterStr) {
            String name = filterStr;
            String pathName = "";
            if (mProjectPathList != null) {
                for (String pathItem : mProjectPathList) {
                    pathName = pathName.trim() + "_" + pathItem.trim();
                }
            }

            pathName = pathName.replace(curProjectPath, "");


            pathName = pathName + "_" + filterStr;
            while (pathName.contains("\\")) {
                pathName = pathName.replace("\\", "-");
            }
            while (pathName.contains("/")) {
                pathName = pathName.replace("/", "-");
            }


            while (pathName.contains(".")) {
                pathName = pathName.replace(".", "_");
            }

            // C:\Users\zhuzj5\IdeaProjects\J1\packages-apps-Settings_-values-zh-rCN-_Analysis.txt
            pathName = pathName.replace("_-", "-");
            pathName = pathName.replace("-_", "-");
            pathName = pathName + "_Analysis.html";

            // _-packages-apps-Settings__java_Analysis.txt
            while (pathName.startsWith("-") || pathName.startsWith("_") || pathName.startsWith(".")) {
                pathName = pathName.substring(1);
            }

            pathName = pathName.replace("__", "_");
            return pathName;
        }


        String getOutFileName(String filterStr) {
            String name = filterStr;
            String pathName = "";
            if (mProjectPathList != null) {
                for (String pathItem : mProjectPathList) {
                    pathName = pathName.trim() + "_" + pathItem.trim();
                }
            }

            pathName = pathName.replace(curProjectPath, "");


            pathName = pathName + "_" + filterStr;
            while (pathName.contains("\\")) {
                pathName = pathName.replace("\\", "-");
            }
            while (pathName.contains("/")) {
                pathName = pathName.replace("/", "-");
            }


            while (pathName.contains(".")) {
                pathName = pathName.replace(".", "_");
            }

            // C:\Users\zhuzj5\IdeaProjects\J1\packages-apps-Settings_-values-zh-rCN-_Analysis.txt
            pathName = pathName.replace("_-", "-");
            pathName = pathName.replace("-_", "-");
            pathName = pathName + "_Analysis.txt";

            // _-packages-apps-Settings__java_Analysis.txt
            while (pathName.startsWith("-") || pathName.startsWith("_") || pathName.startsWith(".")) {
                pathName = pathName.substring(1);
            }

            pathName = pathName.replace("__", "_");
            return pathName;
        }


        void writeContentToFile(File file, String strParam) {

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


        StringBuilder readStringFromFile(File fileItem) {
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
            return sb;
        }

    }


    static StringBuilder readTemplateStringFromFile(File fileItem) {
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
        return sb;
    }

}
