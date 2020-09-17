
import com.google.common.collect.Maps;
import net.jimmc.jshortcut.JShellLink;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//  用于在 软件界面  安装 软件
// 1.
// 2.
// 3.


//start /wait /min apowerrec-bd.exe  /S /Q /D=E:\Temp_Install\apowerrec-bd
//start /wait /min apowerrec-bd.exe  /quiet /norestart INSTALLDIR="E:\Temp_Install\apowerrec-bd"

public class J1_InstallSoftware {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    //修改2. G2_Common_Template  改为当前类名称  A1 Z9
    //修改2.1  G2 改为 对应的 标识符
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
    static String Cur_Bat_Name = "zinstall_software_J1";
    // 当前用户选中的 操作的类型  0-默认标识没有选中打印帮助字符串    1-标识选中默认规则1
    static int CUR_TYPE_INDEX = 1;
    static boolean allowEmptyInputParam = true;    // 是否允许输入参数为空 执行 rule的apply方法

    static J1_InstallSoftware J1_Object;
/*******************修改属性列表 ------End *********************/


    /*******************固定属性列表 ------Begin *********************/

//  Pre_Install_Soft
//  Slient1_OneExe_Local_Install
//  Silent1_OneExe_System_Install
//  Slient2_OneMsi_System_Install
//  Green_OneExe
//  GreenSoft_Zip_Dir
//  Environment_Zip_Dir_Path
//  No_Slient_OneExe
//  No_Slient_Zip_Dir_Path
    static ArrayList<String> zwin_soft_key_list = new ArrayList<String>();

    static {
        zwin_soft_key_list.add("Pre_Install_Soft");
        zwin_soft_key_list.add("Slient1_OneExe_Local_Install");
        zwin_soft_key_list.add("Silent1_OneExe_System_Install");
        zwin_soft_key_list.add("Slient2_OneMsi_System_Install");
        zwin_soft_key_list.add("Green_OneExe");
        zwin_soft_key_list.add("GreenSoft_Zip_Dir");
        zwin_soft_key_list.add("Environment_Zip_Dir_Path");
        zwin_soft_key_list.add("No_Slient_OneExe");
        zwin_soft_key_list.add("No_Slient_Zip_Dir_Path");

    }


    // 需要添加到 PATH 中的 可执行文件

    static ArrayList<String> addPathExeFileNameList = new ArrayList<String>();

    static {

        addPathExeFileNameList.add("Cmder.exe");
        addPathExeFileNameList.add("notepad++.exe");
        addPathExeFileNameList.add("adb.exe");
        addPathExeFileNameList.add("chrome.exe");
        addPathExeFileNameList.add("javac.exe");
        addPathExeFileNameList.add("dot.exe");  // graphviz
        addPathExeFileNameList.add("tesseract.exe");  // tesseract OCR 图像文字识别

    }


    //  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String cur_os_zbinPath;
    static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "win_zbin";
    static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "lin_zbin";
    static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "mac_zbin";


    static String Desktop_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop";
    static File Desktop_File = new File(Desktop_Path);
    static ArrayList<File> DesktopIconFileList = new ArrayList<File>();
    static ArrayList<File> DesktopTargetFileList = new ArrayList<File>();
    static Map<String, String> DesktopIconMap = Maps.newConcurrentMap();

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File G8_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream G8_Properties_InputStream;
    static OutputStream G8_Properties_OutputStream;
    static Properties G8_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();


    // 当前Shell目录下的 文件类型列表  抽取出来  通用
    static HashMap<String, ArrayList<File>> CurDirFileTypeMap = new HashMap<String, ArrayList<File>>();
    ;


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
    static String BAT_OR_SH_Point;
    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE;   // 当前 CMDER的路径 File 文件
    static File First_Input_Dir;   // 用户第一次可能输入的文件夹

    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出
    static boolean isDefaultOperation = false;    //  是否是 默认的操作


    static Rule CUR_Selected_Rule;    // 当前默认选中的 操作规则 这里实现了具体的操作逻辑

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_3_ParamStrList = new ArrayList<>();


    // 固定7  当前保存 Rule的集合
    static ArrayList<Rule> CUR_RULE_LIST = new ArrayList<Rule>();  // 规则的集合


    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new HashMap<String, ArrayList<File>>();
    ;


//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";


    // PATH 环境变量值进行当前的保存处理
    static String EnvironmentValue = System.getProperties().getProperty("java.library.path");
    static String PathSeparator = System.getProperties().getProperty("path.separator");
    static String[] EnvironmentList = EnvironmentValue.split(PathSeparator);


    static boolean isContainEnvironment(String program) {
        boolean environmentExist = false;
        if (EnvironmentValue.contains(program)) {
            environmentExist = true;
        }
        return environmentExist;
    }


    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\G8_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
    static String getEnvironmentExePath(String program) {
        String exename = program.trim().toLowerCase();
        String executePath = null;
        for (int i = 0; i < EnvironmentList.length; i++) {
            String itemPath = EnvironmentList[i];
            String itemPathLower = itemPath.toLowerCase();

            if (itemPathLower.contains(exename)) {
                executePath = itemPath + File.separator + program + (CUR_OS_TYPE == OS_TYPE.Windows ? ".exe" : "");
                break;
            }
        }

        return executePath;
    }


    static String getEnvironmentDefinePath(String program) {
        String exename = program.trim().toLowerCase();
        String executePath = null;
        for (int i = 0; i < EnvironmentList.length; i++) {
            String itemPath = EnvironmentList[i];
            String itemPathLower = itemPath.toLowerCase();

            if (itemPathLower.contains(exename)) {

                return itemPath;
            }
        }

        return executePath;
    }


    // A1  ..... A2.
    static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name) {
        String mCharNumber = "error";
        String curBat = mCur_Bat_Name;
        if (mCur_Bat_Name.contains(".sh")) {
            curBat = curBat.replace(".sh", "");
        }

        if (mCur_Bat_Name.contains(".bat")) {
            curBat = curBat.replace(".bat", "");
        }
        if (curBat.contains("_")) {
            String[] arrNameList = curBat.split("_");
            mCharNumber = arrNameList[arrNameList.length - 1];
        } else {
            mCharNumber = curBat;
        }

        return mCharNumber;
    }


    static {
        try {
            if (!G8_Properties_File.exists()) {
                G8_Properties_File.createNewFile();
            }
            G8_Properties_InputStream = new BufferedInputStream(new FileInputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.load(G8_Properties_InputStream);
            Iterator<String> it = G8_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + G8_Properties.getProperty(key));
                propKey2ValueList.put(key, G8_Properties.getProperty(key));
            }
            G8_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            G8_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.store(G8_Properties_OutputStream, "");
            G8_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }


    //  初始化  从 bat sh   传输而来的参数
    static void initInputParams(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if (i == 1) {  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];
                    //zukgit1    计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    int userSelectedIndex = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                    if (userSelectedIndex != 0 && userSelectedIndex != CUR_TYPE_INDEX) {
                        // 如果 当前 的操作规则 不是 0   并且 操作索引 和当前 索引 不一样  那么就寻找赋值给  CUR_TYPE_INDEX
                        CUR_TYPE_INDEX = userSelectedIndex;
                        isDefaultOperation = false;
                    } else if (userSelectedIndex == CUR_TYPE_INDEX) {
                        // 显式的输入默认值
                        isDefaultOperation = true;   //  默认的操作
                        CUR_TYPE_2_ParamsStr = CUR_TYPE_INDEX + "";
                    } else {
                        isDefaultOperation = true;   //  默认的操作
                        // 默认的操作没有index 选项 所以 index1 就是参数
                        CUR_INPUT_3_ParamStrList.add(args[i]);
                        CUR_TYPE_2_ParamsStr = CUR_TYPE_INDEX + "";  //  默认参数 模拟的第二个参数
                    }
                } else {
                    CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

    }


    // 创建快捷方式
    static boolean makeShellLink(File targetFile, File iconFile) {
        boolean isOK = false;
        String targetFilePath = targetFile.getAbsolutePath();
        JShellLink link = new JShellLink();
        if (!iconFile.exists()) {

/*            try {
                iconFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        try {
            String parentAbsPath = iconFile.getParentFile().getAbsolutePath();
            link.setFolder(parentAbsPath);
            String iconName = iconFile.getName();
            link.setName(iconName);
            link.setPath(targetFilePath);
            link.save();
            if (isKuaiJieIcon(iconFile)) {
                isOK = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOK;


    }

    static boolean isKuaiJieIcon(File kuaijieFile) {
        String absPath = kuaijieFile.getAbsolutePath();
        String parentAbsPath = kuaijieFile.getParentFile().getAbsolutePath();
        String fileName = kuaijieFile.getName();
        JShellLink linkFile = new JShellLink(parentAbsPath, fileName);
        String linkedPath = "";
        try {
            linkFile.load();
            linkedPath = linkFile.getPath();
        } catch (Exception e) {
            System.out.println(e + " APath:" + kuaijieFile);
        }

        if (absPath.equals(linkedPath)) {
            return false;
        }
        return true;
    }

    static String getIconTargetFile(File kuaijieFile) {
        String absPath = kuaijieFile.getAbsolutePath();
        String parentAbsPath = kuaijieFile.getParentFile().getAbsolutePath();
        String fileName = kuaijieFile.getName();
        JShellLink linkFile = new JShellLink(parentAbsPath, fileName);
        try {
            linkFile.load();
        } catch (Exception e) {
           System.out.println(e + " BPath:" + kuaijieFile);
            return null;
        }

        String linkedPath = linkFile.getPath();
        return linkedPath;
    }


    static void initDesktopIcon() {

        File[] desktopList = Desktop_File.listFiles();
        for (int i = 0; i < desktopList.length; i++) {
            File desktopItem = desktopList[i];
            String fileName = desktopItem.getName();
            if (fileName.endsWith(".lnk")) {
                DesktopIconFileList.add(desktopItem);
                String targetPAth = getIconTargetFile(desktopItem);
                if (targetPAth != null) {
                    File targetFile = new File(targetPAth);
                    if (targetFile.exists()) {
                        DesktopTargetFileList.add(targetFile);
                        DesktopIconMap.put(fileName, targetPAth);
                    }
                }

            }
        }

        showMap_String_String(DesktopIconMap, "桌面快捷图标列表");


    }

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name + ".bat";
            BAT_OR_SH_Point = ".bat";
            cur_os_zbinPath = win_zbinPath;
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            BAT_OR_SH_Point = ".sh";
            cur_os_zbinPath = lin_zbinPath;
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            BAT_OR_SH_Point = ".sh";
            cur_os_zbinPath = mac_zbinPath;
        }
    }


    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    void InitRule() {

        //   加入类型一一对应的 那些 规则
        CUR_RULE_LIST.add(new Default_Rule_0());
        CUR_RULE_LIST.add(new Install_All_Exe_Msi_Software_Rule1());
//        CUR_RULE_LIST.add( new File_Name_Rule_2());
//        CUR_RULE_LIST.add( new Image2Jpeg_Rule_3());
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

    }


// 1_green_software_cmder                     绿色软件 直接搜索 .exe 生成桌面快捷方式
// 2_Environment_Path_adb                   // 生成 setx PATh 来添加路径
// 3_slient_install_type1_SQD               可以在安装在本地  静态安装的软件
// 4_slient_install_type2_QUIET_INSTALLDIR  可以在安装在本地  静态安装的软件
// 5_No_Slient_Manual_Install               //  必须手动安装 并没有静态安装命令的软件

    //  当前目录下的所有的 zip文件
    ArrayList<File> software_allZipFileList;

    //  当前目录下的所有的 exe文件
    ArrayList<File> software_allExeFileList;

    //  当前目录下的所有的 msi文件
    ArrayList<File> software_allMsiFileList;


    class ZWin_Software {

        // 安装的类型
        // 1---绿色安装   把 targetExeFile 创建 icon 放到桌面  名称前缀 + z_
        // 2--- 来添加路径  需要添加到环境变量中的软件 无法直接打开
        // 3--- 静默安装软件   可静默安装在本地的软件  使用   /S /Q /D 来执行操作
        // 4--- 静默安装软件 可静默安装在本地的软件  使用    /quiet /norestart INSTALLDIR  来执行操作
        // 5--- 必须手动执行安装软件软件   无法静默执行的软件
        int install_type;

        String category_key;  //  当前的一级目录 A0_xxxx 去掉 A0  B0_XXXX 去掉 B0的样子
        // 整个安装目录的根目录  1.如果是 exe msi文件 单个文件  2.如果是文件夹 那么它就是最大的目录
        File rootFile;
        ArrayList<File> subFileList; //  当前如果是一个目录  那么这就是当前目录的所有子文件
        ArrayList<File> allSubFileList;  //  当前如果是一个目录  那么这就是当前目录的所有子文件 孙文件
        // 需要添加到 环境变量的文件   添加的是它的父目录的路径
        ArrayList<File> addPathFileList;
        ArrayList<File> exeAllFile;

        //   相对于  当前 shell 目录的 相对路径
        String target_relative_path;

        File targetExeFile;  // 一定是一个 单个文件  安装命令  或者  绿色软件的图标就靠它


        File Desktop_Icon_File;

        File Single_install_Dir;   //  当前文件的安装目录


      public  String  toString(){
          System.out.println();
          System.out.println(category_key+" ****************************************" + rootFile);
          System.out.println(" rootFile = "+ rootFile.getAbsolutePath());
          System.out.println(" category_key = "+ category_key);
          System.out.println(" targetExeFile = "+ targetExeFile);

          System.out.println(" Single_install_Dir = "+ Single_install_Dir);
          System.out.println(" target_relative_path = "+ target_relative_path);

          System.out.println(" Desktop_Icon_File = "+ Desktop_Icon_File);

         if(rootFile.isDirectory()){
             System.out.println(" subFileList.size() = "+ subFileList.size());
             System.out.println(" allSubFileList.size() = "+ allSubFileList.size());
             System.out.println(" addPathFileList.size() = "+ addPathFileList.size());
             System.out.println(" exeAllFile.size() = "+ exeAllFile.size());
             System.out.println(" addPathFileList.size() = "+ addPathFileList.size());

         }
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

              return "";
          }

        ZWin_Software(String key, File crootFile) {
            category_key = key;
            rootFile = crootFile;

            if (rootFile.isDirectory()) {
                exeAllFile = new ArrayList<File>();
                subFileList = new ArrayList<File>();
                allSubFileList = new ArrayList<File>();
                addPathFileList = new ArrayList<File>();
                subFileList.addAll(Arrays.asList(rootFile.listFiles()));
                allSubFileList.addAll(getAllSubDirFile(rootFile));
                exeAllFile.addAll(getTypeFileList(allSubFileList, ".exe"));
                addPathFileList.addAll(getPathFileList(exeAllFile, addPathExeFileNameList));

                //  如果有 加入  环境变量的 exe 文件  那么 这个 exe 文件 就是执行文件
                //  如果 没有这样的文件  那么如果 exe 列表中 只有一个文件  那么就是这个文件
                if (addPathFileList.size() > 0) {
                    targetExeFile = addPathFileList.get(0);
                } else {
                    if (exeAllFile.size() == 0) {
                        System.out.println("当前目录没有可执行文件! rootFile = " + rootFile.getAbsolutePath());
                    } else if (exeAllFile.size() == 1) {
                        targetExeFile = exeAllFile.get(0);
                    } else {
                        System.out.println("当前目录存在多个 exe 文件夹  而且 无法判断 哪个是主文件夹 默认选中大小最大那个");
                        targetExeFile = getMaxSizeFile(exeAllFile);
                    }
                }

            } else {
//   更目录  本生 就是 单个文件  那个  目标文件就是这个 target 文件
                targetExeFile = rootFile;

            }
            String parentAbsPath = rootFile.getParentFile().getAbsolutePath();
            String fileName = getFileNameNoPoint(rootFile.getName());
            Single_install_Dir = new File(parentAbsPath + File.separator + fileName);

            if (targetExeFile != null) {
                String absTargetPath = targetExeFile.getAbsolutePath();
                String DesktopIconName = targetExeFile.getName();
                target_relative_path = absTargetPath.replace(CUR_Dir_FILE.getAbsolutePath(), "");
                Desktop_Icon_File = new File(Desktop_Path + File.separator + DesktopIconName);
            }

            toString();
        }

        File getMaxSizeFile(ArrayList<File> fileList) {
            File targetFile = null;

            for (int i = 0; i < fileList.size(); i++) {
                File fileItem = fileList.get(i);
                if (targetFile == null) {
                    targetFile = fileItem;
                }
                long fileSizeTarget = fileItem.getTotalSpace();
                long targetFileSizeTarget = targetFile.getTotalSpace();
                if (targetFileSizeTarget < fileSizeTarget) {
                    targetFile = fileItem;
                }
            }
            return targetFile;
        }

        ArrayList<File> getPathFileList(ArrayList<File> allFile, ArrayList<String> pathExeList) {
            ArrayList<File> pathFileList = new ArrayList<File>();
            first1:
            for (int i = 0; i < allFile.size(); i++) {
                File fileItem = allFile.get(i);
                String fileName = fileItem.getName().toLowerCase();

                second2:
                for (int j = 0; j < pathExeList.size(); j++) {
                    String pathItem = pathExeList.get(j);
                    if (fileName.equals(pathItem)) {
                        pathFileList.add(fileItem);
                        break second2;
                    }
                }

            }
            return pathFileList;
        }

        ArrayList<File> getTypeFileList(ArrayList<File> allFile, String type) {

            ArrayList<File> arrFile = new ArrayList<File>();
            for (int i = 0; i < allFile.size(); i++) {
                File fileItem = allFile.get(i);
                String fileName = fileItem.getName();
                System.out.println("fileName = "+fileName.toLowerCase() +"     type ="+ type.toLowerCase());
                if (fileName.toLowerCase().endsWith(type.toLowerCase())) {
                    arrFile.add(fileItem);
                }
            }
            System.out.println("allFile.size = "+ allFile.size() + "     exeFileSize="+arrFile.size());
            return arrFile;
        }


//start /wait /min apowerrec-bd.exe  /S /Q /D=E:\Temp_Install\apowerrec-bd
//start /wait /min apowerrec-bd.exe  /quiet /norestart INSTALLDIR="E:\Temp_Install\apowerrec-bd"


        // 需要添加到 PATH 中的 可执行文件


        // 需要 指定的 核心 可执行文件   一堆exe中 起作用的那个 ()


    }


    // boolean  isInputDirAsSearchPoint(默认为false) = true  First_Input_Dir存在时
// 标识当前是以 输入参数构造的路径为上搜索起点路径  而不再以 shell目录为 搜索目录
    class Install_All_Exe_Msi_Software_Rule1 extends Basic_Rule {
        ArrayList<File> curInputFileList;


        Install_All_Exe_Msi_Software_Rule1(boolean mIsInputDirAsSearchPoint) {
            super(1, mIsInputDirAsSearchPoint);
            curInputFileList = new ArrayList<File>();
            isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
        }

        Install_All_Exe_Msi_Software_Rule1() {
            super(1, true);
            curInputFileList = new ArrayList<File>();
        }


        // 1. 完成参数的 自我客制化  实现  checkParamsOK 方法
        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            ArrayList<File> curFliterList = new ArrayList<File>();

/*            System.out.println( "用户输入的文件参数为空 A");
            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                return false;
            }*/

            for (int i = 0; i < otherParams.size(); i++) {
                String pre = "." + File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if (curStringItem.startsWith(pre)) {
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath);
                if (curFIle.exists()) {
                    curFliterList.add(curFIle);
                }
            }
            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }

        // 2. 对应的逻辑方法  实现方法  applyOperationRule


        @Override
        ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            showMap(subFileTypeMap);

            ArrayList<File> expressFile = new ArrayList<File>();
            ArrayList<File> exeFileList = subFileTypeMap.get(".exe");
            ArrayList<File> msiFileList = subFileTypeMap.get(".msi");
            if (subFileTypeMap.get(".zip") != null) {
                expressFile.addAll(subFileTypeMap.get(".zip"));
            }

            if (subFileTypeMap.get(".rar") != null) {
                expressFile.addAll(subFileTypeMap.get(".rar"));
            }

            if (subFileTypeMap.get(".7z") != null) {
                expressFile.addAll(subFileTypeMap.get(".7z"));
            }
            showArrFile(exeFileList, "exe执行文件");
            showArrFile(msiFileList, "msi安装文件");
            showArrFile(expressFile, "7z rar zip 压缩文件");

            return super.applyOperationRule(curFileList, subFileTypeMap, curDirList, curRealFileList);
        }

        //3. 如果当前 执行 错误  checkParams 返回 false   那么 将 打印这个函数 说明错误的可能原因
        @Override
        void showWrongMessage() {
            System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
            System.out.println(" errorMsg = " + errorMsg);
        }

        //4.  当前 rule的 说明  将会打印在  用户输入为空时的 提示语句！
        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            String itemDesc = "";
            if (curType == OS_TYPE.Windows) {
                itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            } else {
                itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            }

            return itemDesc;
        }
    }


    class Default_Rule_0 extends Basic_Rule {
        Default_Rule_0() {
            super(0);
        }


    }

    class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex, int opera_type) {
            this.file_type = ctype;
            this.rule_index = cindex;
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            needAllFileFlag = true;

        }

        Basic_Rule(int ruleIndex) {
            this.rule_index = ruleIndex;
            this.file_type = "*";   // 文件的处理类型  默认是 *
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
            needAllFileFlag = true;
        }

        Basic_Rule(int ruleIndex, boolean mNeedAllFile) {
            this.rule_index = ruleIndex;
            this.file_type = "*";   // 文件的处理类型  默认是 *
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
            needAllFileFlag = mNeedAllFile;
        }

        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            ArrayList<File> shellFileList = new ArrayList<File>();
            ArrayList<File> subDirList = new ArrayList<File>();
            ArrayList<File> realFileList = new ArrayList<File>();
            ArrayList<File> allFileList = new ArrayList<File>();
            // 当前 shell 目录下的所有文件
            shellFileList.addAll(Arrays.asList(CUR_Dir_FILE.listFiles()));
            if (needAllFileFlag) {
                if (isInputDirAsSearchPoint && inputDirFile != null) {
                    allFileList = getAllSubFile(inputDirFile, null, null);
                    initFileTypeMap(allFileList);
                } else {
                    allFileList = getAllSubFile(CUR_Dir_FILE, null, null);
                    initFileTypeMap(allFileList);
                }

            }

            for (int j = 0; j < allFileList.size(); j++) {
                File curFile = allFileList.get(j);
                if (curFile.isDirectory()) {
                    subDirList.add(curFile);
                } else {
                    realFileList.add(curFile);
                }
            }
            applyOperationRule(shellFileList, CUR_Dir_FILETypeMap, subDirList, realFileList);

        }

        @Override
        ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return null;
        }


        String simpleDesc() {
            return null;
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return true;  // 默认返回通过   不检查参数   如果有检查的需求 那么就实现它
        }

        @Override
        void showWrongMessage() {
            System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
            System.out.println("ErrorMsg:" + errorMsg);
        }

        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            String itemDesc = "";
            if (curType == OS_TYPE.Windows) {
                itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            } else {
                itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            }

            return itemDesc;
        }

        boolean tryReName(File curFile, String newName) {
            String newFilePath = curFile.getParent() + File.separator + newName;
            String oldName = curFile.getName();
            File newFile = new File(newFilePath);
            if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
                System.out.println("当前目录已存在重命名后的文件  文件名称:" + curFile.getName());
                return false;    // 已经存在的文件不处理 直接返回

            }
            boolean flag = curFile.renameTo(newFile);
            if (flag) {
                System.out.println(oldName + " 转为 " + newFilePath + " 成功！");
                curFixedFileList.add(curFile);
            } else {
                System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
            }
            return flag;
        }
    }

    abstract class Rule {

        int rule_index;   //  rule_index  组成了最基础的唯一键 rule_index 就是唯一的序号  1 2 3 4 5 6 7

        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作

        Rule() {
            inputDirFile = First_Input_Dir;
            if (inputDirFile == null) {
                isInputDirAsSearchPoint = false;
            }
        }

        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        String errorMsg;
        boolean needAllFileFlag;

        File inputDirFile; // 操作文件 目录
        boolean isInputDirAsSearchPoint;  // 是否以 输入的 文件夹作为 全局搜索的 起点

        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        ArrayList<File> inputFileList;  // 从输入参数得到的文件的集合

        abstract void operationRule(ArrayList<String> inputParamsList);

        //        abstract    String applyStringOperationRule1(String origin);   //  不要这样的方法了  只保留 最有用的 那个 applyOperationRule
//        abstract    File applyFileByteOperationRule2(File originFile);
//        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
        // applyFileListRule4
        abstract ArrayList<File> applyOperationRule(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList);

        //        abstract    void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串
        abstract String ruleTip(String type, int index, String batName, OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况

        abstract String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用

        abstract boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams);

        abstract void showWrongMessage();
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
                //    System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void NotePadOpenTargetFile(String absPath) {
        String commandNotead = "";
        if (CUR_OS_TYPE == OS_TYPE.Windows) {
            commandNotead = "cmd.exe /c start   Notepad++.exe " + absPath;
        } else if (CUR_OS_TYPE == OS_TYPE.Linux) {
            commandNotead = " gedit " + absPath;
        } else if (CUR_OS_TYPE == OS_TYPE.MacOS) {
            commandNotead = " gedit " + absPath;
        }
        execCMD(commandNotead);
    }


    public static ArrayList<String> ReadFileContentAsList(File mFilePath) {

        if (mFilePath != null && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

            return null;
        }
        ArrayList<String> contentList = new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null) {
                    continue;
                }

                contentList.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;

            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;

    }


    public static String ReadFileContent(File mFilePath) {

        if (mFilePath != null && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

            return null;
        }
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine + "\n");
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i) + "\n");
        }
        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(sb.toString());
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


    static String getPaddingIntString(int index, int padinglength, String oneStr, boolean dirPre) {
        String result = "" + index;
        int length = ("" + index).length();

        if (length < padinglength) {
            int distance = padinglength - length;
            for (int i = 0; i < distance; i++) {
                if (dirPre) {
                    result = oneStr + result;
                } else {
                    result = result + oneStr;
                }

            }

        }
        return result;

    }

    static ArrayList<File> getAllSubFile(File dirFile, String aospPath, ArrayList<String> typeList) {
        if (typeList == null) {
            typeList = new ArrayList<String>();
            typeList.add("*");
        }
        if (aospPath == null || "".equals(aospPath)) {
            return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
        }

        return getAllSubFile(dirFile.getAbsolutePath(), aospPath, typeList);

    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath, ArrayList<String> typeList) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);


        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
                    for (int i = 0; i < typeList.size(); i++) {
                        String type = typeList.get(i);
                        if ("*".equals(type)) {  // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
                            File curFile = new File(fileString);
                            if (!curFile.isDirectory()) {
                                allFile.add(curFile);
                                break;
                            }


                        } else {
                            if (fileString.endsWith(type)) {
                                allFile.add(new File(fileString));
                                break;
//                         System.out.println("file found at path: " + file.toAbsolutePath());
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }


    static void showNoTypeTip(int ruleIndex) {

        System.out.println("当前用户输入的 ruleIndex = " + ruleIndex + "  操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name + " 的 第一个输入参数中的第一个int值 ");
        System.out.println("请检查输入参数后重新执行命令!");

    }

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            Rule itemRule = CUR_RULE_LIST.get(i);
            String type = itemRule.file_type;
            int index = itemRule.rule_index;
            String desc = itemRule.ruleTip(type, index, Cur_Bat_Name, CUR_OS_TYPE);

/*
            String itemDesc = "";
           if(CUR_OS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_G8.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G8 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc + "\n");
            count++;
        }
        System.out.println("═══════════════════" + "使用方法列表 End " + "═══════════════════" + "\n");

    }


    static int calculInputTypeIndex(String inputParams) {
        if (inputParams == null) {
            return 0;
        }

        String absFilePath = CUR_Dir_1_PATH + File.separator + inputParams;
        File absFile = new File(absFilePath);
        if (absFile.exists() && absFile.isDirectory()) {
            First_Input_Dir = absFile;
            return 0;   //  如果输入的参数  和  shell目录 组成一个 存在的文件的话  那么说明 参数不是 选择 rule的参数
        }

        if (isNumeric(inputParams)) {
            return Integer.parseInt(inputParams);
        }

        if (inputParams.contains("_")) {
            String[] mTypeParamArr = inputParams.split("_");
            if (mTypeParamArr.length == 0) {
                return 0;
            }

            for (int i = 0; i < mTypeParamArr.length; i++) {
                String curPositionStr = mTypeParamArr[i];
                if (isNumeric(curPositionStr)) {
                    return Integer.parseInt(curPositionStr);
                }
            }
        }

        return 0;

    }


    static void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CUR_Dir_FILETypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CUR_Dir_FILETypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CUR_Dir_FILETypeMap.put(keyType, fileList);
        }
    }

    static void initFileTypeMap(ArrayList<File> subFileList) {
        for (File curFile : subFileList) {
            String fileName = curFile.getName();
            if (!fileName.contains(".")) {
                addCurFileTypeMapItemWithKey("unknow", curFile);
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                addCurFileTypeMapItemWithKey(suffix, curFile);
            }
        }


    }


    static Rule getRuleByIndex(int index) {
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            if (CUR_RULE_LIST.get(i).rule_index == index) {
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }


    static Rule getRuleByIdentify(String identify) {
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            if (CUR_RULE_LIST.get(i).identify.equals(identify)) {
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }


    // ArrayPrint ==============================Begin

    static int MAX_COUNT_CHAR_IN_ROW = 140;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 140;

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

    public static int getItemMaxLength_Chinese(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            int curSize = getFramePaddingChineseLength(mStrList.get(i));
            if (curSize > itemLength) {
                itemLength = curSize;
            }

        }
        return itemLength;
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
        String beginChar = "│ ";
        String endChar = "│";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getFrameChineseCount(oriStr);
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

    public static int getFrameChineseCount(String oriStr) {
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
            boolean isChinese = isFrameContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isFrameContainChinese(String str) {
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
        int chineseCount = getFrameChineseCount(title);


        String beginRow = "┌──────────────────" + title + "──────────────────┐";
        String endRow = "└──────────────────┘";
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
        String templateString = "┐";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "─" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "┐");
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
        int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("─", distance + 3);
        curBeginStr = curBeginStr.replace("┘", paddingString + "┘");
        return curBeginStr;
    }

    public static int getFramePaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getFrameChineseCount(oriStr);   // 所有中文的个数
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
        int curPaddingLength = getFramePaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("─", distance + 3);
        curBeginStr = curBeginStr.replace("┐", paddingString + "┐");
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

    public static String getPaddingEmptyString_empty(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += " ";
        }
        return str;
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
                if (isFrameContainChinese(curItem)) {
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
            int preLength = getFramePaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getFrameChineseCount(pre);
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
                int paddingSize = getFramePaddingChineseLength(maoString);
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


    public static String execCMD(String command) {
//        System.out.println("══════════════Begin ExE ");
        StringBuilder sb = new StringBuilder();
        StringBuilder errorSb = new StringBuilder();
        try {

//            Process process = Runtime.getRuntime().exec("CMD.exe /c start  " + command);
            Process process = Runtime.getRuntime().exec("CMD.exe /c start /B " + command);

            InputStreamReader inputReader = new InputStreamReader(process.getInputStream(), "GBK");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line;
            int waitFor = process.waitFor();
//            Stream<String> lines = bufferedReader.lines();
//            lines.iterator();
//            System.out.println("line Count = "+lines.count());

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");

            }


            boolean isAlive = process.isAlive();
            int errorSteamCode = process.getErrorStream().read();

            String errorStream = process.getErrorStream().toString();
            int exitValue = process.exitValue();
//            process.getErrorStream().
            //杀掉进程
//            System.out.println("exitValue ="+ exitValue);
            sb.append("\nexitValue = " + exitValue +
                    "\nisAlive = " + isAlive +
                    "\nerrorStream = " + errorStream +
                    "\nerrorSteamCode = " + errorSteamCode +
                    "\nwaitFor = " + waitFor);
//            process.destroy();

        } catch (Exception e) {
            System.out.println("execCMD 出现异常! ");
            sb.append("execCMD 出现异常! ");
            return sb.toString();
        }

//        System.out.println("sb.toString() = "+ sb.toString());
//        System.out.println("══════════════End ExE ");
        return sb.toString();
    }

    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStampLong() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static File getDirNewestFileWithPointTypeList(File dirFile, ArrayList<String> typeList) {
        File newImageFile = null;

        long max_time = 0;
        File[] allSubFileList = dirFile.listFiles();
        for (int i = 0; i < allSubFileList.length; i++) {
            File fileItem = allSubFileList[i];
            String type = getFileTypeWithPoint(fileItem).toLowerCase();
            if (typeList == null || typeList.contains(type)) {
                long mFileTimeStamp = getFileCreateTime(fileItem.getAbsolutePath());
                if (mFileTimeStamp > max_time) {
                    max_time = mFileTimeStamp;
                    newImageFile = fileItem;
                }
            }
        }

        return newImageFile;
    }

    static Long getFileCreateTime(File fileItem) {
        if (fileItem == null || !fileItem.exists()) {
            return 0L;
        }
        try {
            Path path = Paths.get(fileItem.getAbsolutePath());
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return attr.creationTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return fileItem.lastModified();
        }
    }


    static Long getFileModifyTime(File fileItem) {
        try {
            Path path = Paths.get(fileItem.getAbsolutePath());
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return attr.lastModifiedTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return fileItem.lastModified();
        }
    }

    static Long getFileCreateTime(String filePath) {
        File file = new File(filePath);
        try {
            Path path = Paths.get(filePath);
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return attr.creationTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return file.lastModified();
        }
    }

    public static String getFileNameNoPoint(File file) {
        return getFileNameNoPoint(file.getAbsolutePath());
    }

    public static String getFileTypeWithPoint(File file) {

        return getFileTypeWithPoint(file.getAbsolutePath());
    }

    public static String getFileNameNoPoint(String fileName) {
        String name = "";
        if (fileName.contains(".")) {
            name = fileName.substring(0, fileName.lastIndexOf(".")).trim().toLowerCase();
        } else {
            name = new String(fileName);
        }
        return name.toLowerCase().trim();
    }

    public static String getFileTypeWithPoint(String fileName) {
        String name = "";
        if (fileName.contains(".")) {
            name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
        } else {
            name = "";
        }
        return name.toLowerCase().trim();
    }

    public static void openImageFile(File imageFile) {
        if (CUR_OS_TYPE == OS_TYPE.Windows) {
            try {
                Runtime.getRuntime().exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + imageFile.getAbsolutePath());

            } catch (Exception e) {

            }
        }


    }


    public static void fileCopy(File origin, File target) {
        InputStream input = null;
        OutputStream output = null;
        int lengthSize;
        // 创建输入输出流对象
        try {
            input = new FileInputStream(origin);
            output = new FileOutputStream(target);
            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                byte[] buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件
                output.write(buffer);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null && output != null) {
                try {
                    input.close(); // 关闭流
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static ArrayList<File> getAllSubDirFile(File rootPath) {
        ArrayList<File> allDirFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath.getAbsolutePath() + File.separator);
        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    allDirFile.add(new File(dir.toFile().getAbsolutePath()));
                    return super.postVisitDirectory(dir, exc);
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDirFile;
    }

    public static boolean isValidUrl(String urlString) {
        boolean flag = false;
        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co = url.openConnection();
            co.setConnectTimeout(5000);
            co.connect();
            flag = true;
            System.out.println("连接可用");
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
            flag = false;
        }

        return flag;
    }


    public static void main(String[] args) {

        initSystemInfo();
        initInputParams(args);

        initDesktopIcon();

        J1_InstallSoftware mG8_Object = new J1_InstallSoftware();
        mG8_Object.InitRule();
        J1_Object = mG8_Object;

        // 用户没有输入参数
        if (CUR_INPUT_3_ParamStrList.size() == 0 && !allowEmptyInputParam) {
            showTip();
            return;
        }

        //   默认的索引同时也被修改  没有获得 当前 适配的规则索引
        if (CUR_TYPE_INDEX <= 0 || CUR_TYPE_INDEX > CUR_RULE_LIST.size()) {
            showNoTypeTip(CUR_TYPE_INDEX);
            return;
        }


        if (CUR_Dir_FILE != null && !isZWinSoft(CUR_Dir_FILE)) {
            System.out.println("当前目录不在 ZWin_SoftWare 不是指定 软件保存安装 目录 ! ");
            System.out.println("百度网盘 ZWin_SoftWared 地址:  " + "https://pan.baidu.com/disk/home#/all?vmode=list&path=%2F%E7%A7%BB%E5%8A%A8%E7%A1%AC%E7%9B%98%2Fsoftware%2FZWin_Software");
            return;
        }
        showWinSoftMap(zwin_soft_dir_Map);

        if (zwin_soft_unzip_FileList.size() > 0) {
            System.out.println("════════════════════" + "未解压文件列表" + "════════════════════");
            for (int i = 0; i < zwin_soft_unzip_FileList.size(); i++) {
                File unzipFile = zwin_soft_unzip_FileList.get(i);
                System.out.println("unzip[" + (i + 1) + "] " + unzipFile.getAbsolutePath());
            }
            System.out.println();
            System.out.println("【Tip】 请在 ZWin_Software 执行  ▲【   zzip_H5.bat  all   】▲ 来解压当前文件 !  之后再运行安装软件命令: " + Cur_Bat_Name);
            return;
        } else {
            System.out.println(" >>>>> 当前 压缩文件 已全部解压缩  开始安装软件程序!!!");
        }


        // 开始执行

        TryInstallSoftware(zwin_soft_dir_Map);


        System.out.println("══════════════════" + " 安装命令 " + "══════════════════");
        for (int i = 0; i < ZWinSoft_Install_CommandList.size(); i++) {
            String commandItem = ZWinSoft_Install_CommandList.get(i);
            System.out.println(commandItem);
        }

        if (true) {
            return;
        }


        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则


        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null || !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE, CUR_TYPE_2_ParamsStr, CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }


        if (!CUR_Dir_FILE.exists() || !CUR_Dir_FILE.isDirectory()) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
            return;
        }


/*
        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }
*/


        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理

        setProperity();
    }

    static Map<File, ArrayList<File>> zwin_soft_dir_Map = Maps.newConcurrentMap();
    static ArrayList<File> zwin_soft_zip_FileList = new ArrayList<File>();
    static ArrayList<File> zwin_soft_unzip_FileList = new ArrayList<File>();

    public static boolean isZWinSoft(File ShellDir) {
        boolean flag = false;
        File[] subFileList = ShellDir.listFiles();


        for (int i = 0; i < subFileList.length; i++) {
            File itemFile = subFileList[i];
            String dirName = itemFile.getName();
            String targetKey = calZWinDir(dirName, zwin_soft_key_list);
            if (targetKey != null) {
                flag = true;
                ArrayList<File> softwareList = new ArrayList<File>();
                File[] softfileItem = itemFile.listFiles();
                for (int j = 0; j < softfileItem.length; j++) {
                    File softFile = softfileItem[j];
                    if (softFile.getName().endsWith(".zip") || softFile.getName().endsWith(".rar")
                            || softFile.getName().endsWith(".7z")) {
                        zwin_soft_zip_FileList.add(softFile);
                        continue;
                    }

                    if (softFile.getName().endsWith(".exe") || softFile.getName().endsWith(".msi")) {
                        softwareList.add(softFile);
                        continue;
                    }
                    if (softFile.isDirectory()) {
                        softwareList.add(softFile);
                    }
                }

                zwin_soft_dir_Map.put(itemFile, softwareList);
            }

        }

        // zwin_soft_key_list

        return flag;

    }

    public static String calZWinDir(String dirName, ArrayList<String> dirKeyList) {
        String key = null;

        for (int i = 0; i < dirKeyList.size(); i++) {
            String keyItem = dirKeyList.get(i);
            if (dirName.contains(keyItem)) {
                key = keyItem;
                return key;
            }
        }

        return key;

    }

    public static int getImageHigh(File picture) {
        int high = 0;
        ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
        high = imageIcon.getIconHeight();

        return high;
    }


    void showArrFile(ArrayList<File> arrFile, String title) {
        if (arrFile == null || arrFile.size() == 0) {
            System.out.println("当前无 " + title + "  打印 ArrayList<File>  信息 ");
            return;
        }
        System.out.println("════════ " + "【title:" + title + "】" + getFileTypeWithPoint(arrFile.get(0)) + " 【大小:" + arrFile.size() + "】" + "════════ ");
        for (int i = 0; i < arrFile.size(); i++) {
            File fileItem = arrFile.get(i);
            System.out.println(fileItem.getAbsolutePath());
        }


    }

    @SuppressWarnings("unchecked")
    static void TryInstallSoftware(Map<File, ArrayList<File>> arrFileMap) {

        Map.Entry<File, ArrayList<File>> entry;
        int fileOrderIndex = 0;
        int typeIndex = 0;
// zwin_soft_key_list
//A0_Pre_Install_Soft/
//B0_Slient1_OneExe_Local_Install/
//B1_Silent1_OneExe_System_Install/
//B2_Slient2_OneMsi_System_Install/
//C0_Green_OneExe/
//C1_GreenSoft_Zip_Dir/
//D0_Environment_Zip_Dir_Path/
//F0_No_Slient_OneExe/
//F1_No_Slient_Zip_Dir_Path/

        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<File, ArrayList<File>>) iterator.next();
                File typeStr = entry.getKey();  //Map的Value
                String dirNameStr = typeStr.getName();
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                toDoInstall(dirNameStr, fileArr);

            }
        }

    }

    static String calculFileOringinKey(String dirName, ArrayList<String> keyList) {
        String keyStr = null;
        for (int i = 0; i < keyList.size(); i++) {
            String keiItem = keyList.get(i).toLowerCase();
            if (dirName.toLowerCase().contains(keiItem)) {
                return keyList.get(i);
            }
        }
        return keyStr;
    }

    static ArrayList<String> ZWinSoft_Install_CommandList = new ArrayList<String>();


/*    static{
        ZWinSoft_Install_CommandList.add("@echo off");
        ZWinSoft_Install_CommandList.add("Setlocal ENABLEDELAYEDEXPANSION");
    }*/

    static int toDoIndex = 1;

    static void toDoInstall(String dirName, ArrayList<File> softwareList) {
        String key = calculFileOringinKey(dirName, zwin_soft_key_list);
        if (key == null) {
            System.out.println("当前 dirName =" + dirName + " 没有在  zwin_soft_key_list  找到对应的Key");
            return;
        }

        ArrayList<ZWin_Software> allZWInSoftList = new ArrayList<ZWin_Software>();

        for (int i = 0; i < softwareList.size(); i++) {
            File itemFile = softwareList.get(i);
            ZWin_Software winSoftItem = J1_Object.new ZWin_Software(key, itemFile);
            allZWInSoftList.add(winSoftItem);
        }

        // 把  转为 更为 方面操作的 ZWin_Soft 类去执行   key   和  File
        ZWinSoft_Install_CommandList.add("");
        ZWinSoft_Install_CommandList.add("rem ############################ index[" + toDoIndex + "]  key = " + key);
        toDoIndex++;

        for (int i = 0; i < allZWInSoftList.size(); i++) {
            ZWin_Software winSoftItem = allZWInSoftList.get(i);
            String switchKey = winSoftItem.category_key;
            File targetFile = winSoftItem.targetExeFile;
            File rootFile = winSoftItem.rootFile;
            File single_install_Dir = winSoftItem.Single_install_Dir;
            File desktop_icon_file = winSoftItem.Desktop_Icon_File;
            String relativePath = winSoftItem.target_relative_path;
            if (targetFile == null) {
                System.out.println("当前 root目录 " + rootFile.getAbsolutePath() + "的 targetFile为空!");
                continue;
            }

            String targetAbsPath = targetFile.getAbsolutePath();
            String clearChineseABsPath = clearChinese(targetAbsPath);
            String curCommand = "";

            switch (switchKey) {
                case "Pre_Install_Soft":
                    curCommand = "rem  " + clearChineseABsPath;
                    break;

                case "Slient1_OneExe_Local_Install":
                    curCommand = "start /wait /min  ." + relativePath + " /S /Q /D=" + single_install_Dir;
                    break;

                case "Silent1_OneExe_System_Install":
                    curCommand = "start /wait /min  ." + relativePath + " /S /Q /D=" + single_install_Dir;
                    break;

                case "Slient2_OneMsi_System_Install":
                    curCommand = "start /wait /min  ." + relativePath + "  /quiet /norestart INSTALLDIR=\"" + single_install_Dir + "\"";
                    break;
                case "Green_OneExe":
                    makeShellLink(targetFile, desktop_icon_file);
                    curCommand = "rem  Green_OneExe [" + i + "] create icon_desktop for " + clearChineseABsPath;
                    break;
                case "GreenSoft_Zip_Dir":
                    makeShellLink(targetFile, desktop_icon_file);
                    curCommand = "rem  GreenSoft_Zip_Dir [" + i + "] create icon_desktop for " + clearChineseABsPath;

                    break;
                case "Environment_Zip_Dir_Path":
                    curCommand = "rem  AddPath[" + i + "]  setx  PATH   " + clearChineseABsPath + "%PATH%";

                    break;
                case "No_Slient_OneExe":
                    curCommand = "rem No_Slient_OneExe[" + i + "]  " + clearChineseABsPath;
                    break;
                case "No_Slient_Zip_Dir_Path":
                    curCommand = "rem No_Slient_Zip_Dir_Path[" + i + "]  " + clearChineseABsPath;
                    break;

                default:
                    System.out.println("toDoInstall  default DO-Nothing !");

            }
            ZWinSoft_Install_CommandList.add(curCommand);


        }


    }

    static void showWinSoftMap(Map<File, ArrayList<File>> arrFileMap) {
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("════════ 当前目录软件 Map Begin ════════ ");
        showMap_FileKey(arrFileMap);
        System.out.println("════════ 当前目录软件 Map End ════════ ");
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════");


        ArrayList<File> unExpressFileList = new ArrayList<File>();
        ArrayList<File> unzipFileList = new ArrayList<File>();
        ArrayList<File> unrarFileList = new ArrayList<File>();
        ArrayList<File> unz7FileList = new ArrayList<File>();


        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════");
        if (zwin_soft_zip_FileList.size() > 0) {
            System.out.println("════════ 解压 7z rar zip 文件列表 Begin ════════ ");
            ArrayList<File> zipFile = new ArrayList<File>();
            ArrayList<File> rarFile = new ArrayList<File>();
            ArrayList<File> z7File = new ArrayList<File>();


            for (int i = 0; i < zwin_soft_zip_FileList.size(); i++) {
                File itemFile = zwin_soft_zip_FileList.get(i);
                String fileName = itemFile.getName();
                if (fileName.endsWith(".zip")) {
                    zipFile.add(itemFile);
                } else if (fileName.endsWith(".rar")) {
                    rarFile.add(itemFile);
                } else if (fileName.endsWith(".7z")) {
                    z7File.add(itemFile);
                }
            }

            for (int i = 0; i < zipFile.size(); i++) {
                File zipItemFile = zipFile.get(i);
                boolean isAlreadyJieYa = isAlredyJieYa(zipItemFile);

                System.out.println(" Jieya[" + (isAlreadyJieYa ? "true " : "false") + "]    " + "zip[" + i + "] = " + zipItemFile.getAbsolutePath());
                if (!isAlreadyJieYa) {
                    unExpressFileList.add(zipItemFile);
                    unzipFileList.add(zipItemFile);

                }
            }


            for (int i = 0; i < rarFile.size(); i++) {
                File rarItemFile = rarFile.get(i);
                boolean isAlreadyJieYa = isAlredyJieYa(rarItemFile);
                System.out.println(" Jieya[" + (isAlreadyJieYa ? "true " : "false") + "]    " + "rar[" + i + "] = " + rarFile.get(i).getAbsolutePath());
                if (!isAlreadyJieYa) {
                    unExpressFileList.add(rarItemFile);
                    unrarFileList.add(rarItemFile);

                }
            }

            for (int i = 0; i < z7File.size(); i++) {
                File _7zItemFile = z7File.get(i);
                boolean isAlreadyJieYa = isAlredyJieYa(_7zItemFile);
                System.out.println(" Jieya[" + (isAlreadyJieYa ? "true " : "false") + "]    " + "7z[" + i + "] = " + z7File.get(i).getAbsolutePath());
                if (!isAlreadyJieYa) {
                    unExpressFileList.add(_7zItemFile);
                    unz7FileList.add(_7zItemFile);

                }
            }
            System.out.println("════════ 解压 7z rar zip 文件列表 End ════════ ");
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════");

        } else {
            System.out.println("当前目录没有 压缩文件进行解压缩 分析 ! ");
        }
        zwin_soft_unzip_FileList.addAll(unExpressFileList);
    }


    static boolean isAlredyJieYa(File zipFile) {
        boolean jieyaFlag = false;
//        System.out.println("****** isAlredyJieYa  zipFile = "+ zipFile.getAbsolutePath());
        File parentFile = zipFile.getParentFile();
        File[] fileArr = parentFile.listFiles();
        if (fileArr == null) {
            return false;
        }
        // 1. 如果是文件  直接 continue
        // 2. 如果是当前文件 直接 continue
        // 3.  获取当前的文件夹的名称    本生zip的名称
        for (int i = 0; i < fileArr.length; i++) {
            File fileItem = fileArr[i];
            if (fileItem.isFile()) {
                continue;
            }
            if (zipFile.equals(fileItem)) {
                continue;
            }
            String dirName = fileItem.getName().toLowerCase();
            String zipName_NoType = getFileNameNoPoint(zipFile.getName());

            // 1. 如果 当前的文件夹包名 对比相同
            if (dirName.equals(zipName_NoType)) {
                jieyaFlag = true;
//                System.out.println("true  ---> "+"dirName = "+ dirName + "  zipName_NoType = "+ zipName_NoType );
                return jieyaFlag;
            }

            // 2. 如果 zip名字 和 目录 名称  有包含的 关系 那么  也可认为是 已经存在解压缩包
            if (dirName.startsWith(zipName_NoType) || zipName_NoType.startsWith(dirName)) {
                jieyaFlag = true;
//                System.out.println("true  ---> "+"dirName = "+ dirName + "  zipName_NoType = "+ zipName_NoType );
                return jieyaFlag;
            }
        }

//        System.out.println("isAlredyJieYa = false  ---> " + zipFile.getAbsolutePath() );

        // 1. 是否有同名
        return jieyaFlag;
    }


    @SuppressWarnings("unchecked")
    static void showMap_FileKey(Map<File, ArrayList<File>> arrFileMap) {

        Map.Entry<File, ArrayList<File>> entry;
        int fileOrderIndex = 0;
        int typeIndex = 0;

        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<File, ArrayList<File>>) iterator.next();
                File typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                int curSize = fileArr.size();
                typeIndex++;
                System.out.println();
                System.out.println();
                System.out.println("════════ 【" + typeIndex + "】" + "【" + typeStr.getName() + "】" + "【大小:" + curSize + "】" + "════════ ");
                ArrayList<String> paddingArrLog = new ArrayList<String>();
                for (int i = 0; i < fileArr.size(); i++) {
                    String absPath = fileArr.get(i).getAbsolutePath();
                    paddingArrLog.add(absPath);
                }
                int maxSize = getItemMaxLength_Chinese(paddingArrLog);
//                int maxSize = getItemMaxLength(paddingArrLog);
                for (int i = 0; i < paddingArrLog.size(); i++) {
                    String logItem = paddingArrLog.get(i);
                    int index = i + 1;
                    fileOrderIndex++;
                    int paddingNum = maxSize - getFramePaddingChineseLength(logItem) + 4;
                    String paddingStr = getPaddingEmptyString_empty(paddingNum);
                    System.out.println(logItem + paddingStr + "[" + index + "] all[" + fileOrderIndex + "]");
                }

            }
        }

    }

    @SuppressWarnings("unchecked")
    static void showMap_String_String(Map<String, String> arrFileMap, String title) {

        Map.Entry<String, String> entry;
        int map_index = 0;

        System.out.println("══════════════════" + title + "══════════════════");

        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String keyStr = entry.getKey();  //Map的Value
                String valueStr = entry.getValue();  //Map的Value
                map_index++;
                System.out.println("【" + map_index + "】" + "【键:" + keyStr + "】" + "【值:" + valueStr + "】" + "");


            }
        }

    }


    @SuppressWarnings("unchecked")
    static void showMap(HashMap<String, ArrayList<File>> arrFileMap) {

        Map.Entry<String, ArrayList<File>> entry;
        int fileOrderIndex = 0;
        int typeIndex = 0;

        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                int curSize = fileArr.size();
                typeIndex++;
                System.out.println("════════ 【" + typeIndex + "】" + "【" + typeStr + "】" + "【大小:" + curSize + "】" + "════════ ");
                for (int i = 0; i < fileArr.size(); i++) {
                    fileOrderIndex++;
                    int index = i + 1;
                    String absPath = fileArr.get(i).getAbsolutePath();
                    System.out.println(absPath + "  ═══  " + typeStr + "[" + index + "] all[" + fileOrderIndex + "]");
                }

            }
        }

    }


    public static int getImageWidth(File picture) {
        int width = 0;
        ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
        width = imageIcon.getIconWidth();
        return width;
    }
}
