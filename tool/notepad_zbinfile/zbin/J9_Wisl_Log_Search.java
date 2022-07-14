
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//
public class J9_Wisl_Log_Search {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 J9 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    //修改2. J9_Wisl_Log_Search  改为当前类名称  A1 Z9
    //修改2.1  J9 改为 对应的 标识符
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
    static String Cur_Bat_Name = "zwisl_log_search_J9.bat";
    // 当前用户选中的 操作的类型  0-默认标识没有选中打印帮助字符串    1-标识选中默认规则1
    static int CUR_TYPE_INDEX = 1;
    static boolean allowEmptyInputParam = false;    // 是否允许输入参数为空 执行 rule的apply方法

/*******************修改属性列表 ------End *********************/


    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String cur_os_zbinPath;
    static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "win_zbin";
    static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "lin_zbin";
    static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "mac_zbin";


    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   J9   G3 ... Z9
    static File J9_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream J9_Properties_InputStream;
    static OutputStream J9_Properties_OutputStream;
    static Properties J9_Properties = new Properties();
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


    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\J9_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\J9_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
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
            if (!J9_Properties_File.exists()) {
                J9_Properties_File.createNewFile();
            }
            J9_Properties_InputStream = new BufferedInputStream(new FileInputStream(J9_Properties_File.getAbsolutePath()));
            J9_Properties.load(J9_Properties_InputStream);
            Iterator<String> it = J9_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + J9_Properties.getProperty(key));
                propKey2ValueList.put(key, J9_Properties.getProperty(key));
            }
            J9_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            J9_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(J9_Properties_File.getAbsolutePath()));
            J9_Properties.store(J9_Properties_OutputStream, "");
            J9_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }


    //  当前的第一个参数  是一个文件的目录
    static boolean initInputParams2(String[] args) {

        //   当前解析的文件  将把解析结果放入到当前的文件 Begin
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return false;
        }
        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
            if (curFile.isFile()) {
                Log_Operation_Type = 1;
                notepad_command_File = curFile;
                notepad_command_dir = notepad_command_File.getParentFile();
            } else if (curFile.isDirectory()) {
                Log_Operation_Type = 2;
                notepad_command_dir = curFile;
                notepad_command_File = new File(notepad_command_dir.getAbsolutePath() + File.separator + "Z_" + getTimeStamp() + ".txt");
//                notepad_command_File = new File(notepad_command_dir.getAbsolutePath() + File.separator + "Z_" + "analysis" + ".txt");

            }

        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return false;
        }
        //   当前解析的文件  将把解析结果放入到当前的文件 Begin
        return true;
    }


    //  初始化  从 bat sh   传输而来的参数
    static void initInputParams(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录 或者当前
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
        CUR_RULE_LIST.add(new MergeMP4_Rule_1());
//        CUR_RULE_LIST.add( new File_Name_Rule_2());
//        CUR_RULE_LIST.add( new Image2Jpeg_Rule_3());
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

    }


    // boolean  isInputDirAsSearchPoint(默认为false) = true  First_Input_Dir存在时
// 标识当前是以 输入参数构造的路径为上搜索起点路径  而不再以 shell目录为 搜索目录
    class MergeMP4_Rule_1 extends Basic_Rule {
        ArrayList<File> curInputFileList;

        MergeMP4_Rule_1(boolean mIsInputDirAsSearchPoint) {
            super(1);
            curInputFileList = new ArrayList<File>();
            isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
        }

        MergeMP4_Rule_1() {
            super(1);
            curInputFileList = new ArrayList<File>();
        }


        // 1. 完成参数的 自我客制化  实现  checkParamsOK 方法
        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            ArrayList<File> curFliterList = new ArrayList<File>();

            if (otherParams == null || otherParams.size() == 0) {
                errorMsg = "用户输入的文件参数为空";
                return false;
            }

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

    @SuppressWarnings("unchecked")
    static ArrayList<File> getAllSubFile(File dirFile, String typeStr) {
        ArrayList typeList = new ArrayList<String>();
        if (typeStr != null) {
            typeList.add(typeStr);
        }

        return getAllSubFile(dirFile, "", typeList);

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
                itemDesc = "zrule_apply_J9.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_J9 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
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
                    allDirFile.add(dir.toFile());
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


    @SuppressWarnings("unchecked")
    static void analysis_with_time() {

        int fileIndex = 1;
        for (File logFile : allTxtFileList) {
            if (logFile.getName().toLowerCase().trim().startsWith("z_")) {
                // 过滤掉 自己生成的 那些 LOG 数据
                continue;
            }
            tryAnalysis_Time(logFile, fileIndex);
            fileIndex++;

            allFileAnalysisStringArr.add(new ArrayList<String>(analysisStringArr));
            analysisStringArr.clear();  // 对于下一个文件  先 处理  analysisStringArr 的数据  然后 迎接下一个文件的处理
        }


    }


    @SuppressWarnings("unchecked")
    static void analysis_with_type() {


        for (int i = 0; i < EntryKey_List.size(); i++) {
            String sheetKey = EntryKey_List.get(i);
            ArrayList<AbstractKeyEntry> curKeyEntryList = EntryList_Map.get(sheetKey);


//=============开始对 Log文件[1]: data-usage.txt 进行分析！
//══════════════════════ 【COMMON】 Type Search Begin ══════════════════════

            analysisStringArr.add(0, "\n\n\n══════════════════════ 【" + sheetKey + "】 Type Search Begin ══════════════════════");


            int fileIndex = 1;
            for (File logFile : allTxtFileList) {
                if (logFile.getName().toLowerCase().trim().startsWith("z_")) {
                    // 过滤掉 自己生成的 那些 LOG 数据
                    continue;
                }
                tryAnalysis_type(curKeyEntryList, logFile, fileIndex);
                fileIndex++;

                allFileAnalysisStringArr.add(new ArrayList<String>(analysisStringArr));
                analysisStringArr.clear();  // 对于下一个文件  先 处理  analysisStringArr 的数据  然后 迎接下一个文件的处理
            }

        }


    }

    @SuppressWarnings("unchecked")
    static void showEntryMap() {
// Map<String, ArrayList<AbstractKeyEntry>> EntryList_Map
        Map.Entry<String, ArrayList<AbstractKeyEntry>> entryItem;

        Iterator iterator = EntryList_Map.entrySet().iterator();
        while (iterator.hasNext()) {
            entryItem = (Map.Entry<String, ArrayList<AbstractKeyEntry>>) iterator.next();
            String sheetName = entryItem.getKey();   //Map的Key    Sheet名称 String name
            ArrayList<AbstractKeyEntry> entryObjectList = entryItem.getValue();  //Map的Value   Sheet 内容
            System.out.println("══════════ EntryList_Map【" + sheetName + "(" + entryObjectList.size() + ")】Begin══════════ ");
            for (int i = 0; i < entryObjectList.size(); i++) {
                AbstractKeyEntry keyEntry = entryObjectList.get(i);

                System.out.println("Index【" + (i + 1) + "】 KeyWord【" + keyEntry.keyword.replace("\n", "") + "】 class【" + keyEntry.getClass().getName() + "】");
            }
//            System.out.println("══════════ EntryList_Map【"+sheetName+"】End══════════ ");

        }


    }


    @SuppressWarnings("unchecked")
    static void showSheetMap() {

        Map.Entry<String, List<Map<String, String>>> entryItem;

        Iterator iterator = sheetMap.entrySet().iterator();
        while (iterator.hasNext()) {
            entryItem = (Map.Entry<String, List<Map<String, String>>>) iterator.next();
            String sheetName = entryItem.getKey();   //Map的Key    Sheet名称 String name
            List<Map<String, String>> sheetValueList = entryItem.getValue();  //Map的Value   Sheet 内容
            System.out.println("══════════ Sheet【" + sheetName + "】Begin══════════ ");
            for (int i = 0; i < sheetValueList.size(); i++) {
                Map<String, String> rowContentMap = sheetValueList.get(i);
                getKeyAndValue_Title_Row(rowContentMap, sheetName, i + 1);
            }
 //            System.out.println("══════════ Sheet【"+sheetName+"】End══════════ ");

        }


    }

    @SuppressWarnings("unchecked")
    static void initEntryFromMap() {

        Map.Entry<String, List<Map<String, String>>> entryItem;

        //  GPS BT WIFI NFC 的 desc 都写在 同一个 txt 文件中
        // J9_I9_Dynamic_ClearDesc.txt   // 实现在 ztextrule_operation_I9.bat 中每次都要手动去除描述的烦恼
        ArrayList<String> matchhDynamicDescStrList = new  ArrayList<String>();
        
        
        Iterator iterator = sheetMap.entrySet().iterator();
        while (iterator.hasNext()) {
            entryItem = (Map.Entry<String, List<Map<String, String>>>) iterator.next();
            
          //Map的Key    Sheet名称 String name COMMON   WIFI GPS BT NFC
            String sheetName = entryItem.getKey();   
            ArrayList<AbstractKeyEntry> curKeyEntryList = new ArrayList<AbstractKeyEntry>();  // 值

            // J9_K2_Dynamic_KeyWord_Common.txt     // 动态创建 这样的 Txt 文件写入每行关键字
            // J9_K2_Dynamic_KeyWord_GPS.txt
            // J9_K2_Dynamic_KeyWord_WIFI.txt
            // J9_K2_Dynamic_KeyWord_BT.txt
            // J9_K2_Dynamic_KeyWord_NFC.txt    // 实现 改动 xlsx 文件 后 J9 和 znotepad_tip_K2.bat 同时改变
            ArrayList<String> matchDynamicKeyList = new  ArrayList<String>();
            
            
      
            List<Map<String, String>> sheetValueList = entryItem.getValue();  //Map的Value   Sheet 内容


            for (int i = 0; i < sheetValueList.size(); i++) {
                Map<String, String> rowContentMap = sheetValueList.get(i);


                // 把 每一行的 数据转为 对象
                Set<String> keySet = rowContentMap.keySet();
                if (keySet == null || keySet.size() == 0) {
                    continue;
                }
                ArrayList<String> keyList = new ArrayList<String>();
                keyList.addAll(keySet);

                // xlsx 中定义的头部
                String keyWord_1 = null;  // 1.关键词   可能包含 多关键词
                String descStr_2 = null;   //2. 说明  最好只 包含一行  否则 影响 整体 样式
                String isTopStr_3 = null; //3. 是否置顶
                String isPrintOnceStr_4 = null; //4. 是否只打印一次
                String isIgnoreRepeatStr_5 = null; //5. 是否过滤上下重复
                String AospPathStr = null;    // AOSP 提示 路径
                String isClipBoard_6 = null; //6. 剪切板  // 默认是true , 只有N 时才是false

                for (int j = 0; j < keyList.size(); j++) {
                    String key = keyList.get(j).trim();
                    switch (key) {
                        case "关键词":
                            keyWord_1 = rowContentMap.get(key);
                            break;

                        case "说明":
                            descStr_2 = rowContentMap.get(key);
                            break;

                        case "是否置顶":
                            isTopStr_3 = rowContentMap.get(key);
                            break;

                        case "是否只打印一次":
                            isPrintOnceStr_4 = rowContentMap.get(key);
                            break;

                        case "是否过滤上下重复":
                            isIgnoreRepeatStr_5 = rowContentMap.get(key);
                            break;

                        case "代码位置":
                            AospPathStr = rowContentMap.get(key);
                            break;

                        case "剪切板":
                        	isClipBoard_6 = rowContentMap.get(key);
                            break;
                            
                        default:
                            System.out.println("当前没有匹配到 Head--》" + key);
                    }
                }
                
                if (isStrEmpty(descStr_2)) {
                    System.out.println("当前 sheetName【" + sheetName + "】 KeyWord【" + keyWord_1 + "】 说明为空!  请补充! ");
                }else {
                	
                	if(!matchhDynamicDescStrList.contains(descStr_2)) {
                		  matchhDynamicDescStrList.add(descStr_2);
                	}
                  
                }

                if (isStrEmpty(keyWord_1)) {   //  关键词为 空  那么  无法创建  识别对象
                    continue;
                }

                
              
          
                
                boolean isMultiKeyWord = keyWord_1.contains("\n");
                boolean isCopyToDynamicText =  true;
                if(isClipBoard_6 == null) {
                	isCopyToDynamicText =  true;
                }else {
                    if(  "N".equals(isClipBoard_6) || "n".equals(isClipBoard_6)) {
                    	isCopyToDynamicText = false;
                    }
                	
                }

                if(isMultiKeyWord) {
                	String oneLineWord = getPre_N(keyWord_1);
                	
                	if(!matchDynamicKeyList.contains(oneLineWord) && isCopyToDynamicText) {
              		  matchDynamicKeyList.add(oneLineWord);
              	}
                	
                }else {
                	if(!matchDynamicKeyList.contains(keyWord_1)  && isCopyToDynamicText) {
                		  matchDynamicKeyList.add(keyWord_1);
                	}
                
                }
              
                
                boolean isTop_Flag = !isBooleanEmpty(isTopStr_3);
                boolean isPrintOnce_Flag = !isBooleanEmpty(isPrintOnceStr_4);
                boolean isIgnoreRepeat_Flag = !isBooleanEmpty(isIgnoreRepeatStr_5);
                AbstractKeyEntry EntryBase = null;

//                    System.out.println("当前 sheetName【"+sheetName+"】  isMultiKeyWord【"+isMultiKeyWord+"】  isTop【"+isTop_Flag+"】  isPrintOnce【"+isPrintOnce_Flag+"】  isIgnoreRepeat【"+isIgnoreRepeat_Flag+"】 keyWord【"+keyWord_1+"】 desc【"+descStr_2+"】");


                //  如果 3 个 flag 都是 false  那么 创建 MeetEntry 这个 类
                if (!isTop_Flag && !isPrintOnce_Flag && !isIgnoreRepeat_Flag) {

                    if (isMultiKeyWord) {
//                            String[] keyArr = keyWord_1.split("\n");
                        EntryBase = new MultiKeyWordPrintBase(keyWord_1, descStr_2, AospPathStr);
                    } else {
                        EntryBase = new MeetPrintBase(keyWord_1, descStr_2, AospPathStr);
                    }
                } else if (isTop_Flag && !isPrintOnce_Flag && !isIgnoreRepeat_Flag) {
                    EntryBase = new CollectEndPrintBase(keyWord_1, descStr_2, AospPathStr);
                } else if (isPrintOnce_Flag && !isTop_Flag && !isIgnoreRepeat_Flag) {
                    EntryBase = new OncePrintBase(keyWord_1, descStr_2, AospPathStr);

                } else if (isIgnoreRepeat_Flag && !isTop_Flag && !isPrintOnce_Flag) {
                    EntryBase = new DiffMeetPrintBase(keyWord_1, descStr_2, AospPathStr);

                }
                curKeyEntryList.add(EntryBase);

            }
            EntryList_Map.put(sheetName, curKeyEntryList);

            Dynamic_Make_Wisl_K2_TxtKey(sheetName,matchDynamicKeyList);

        }

//        System.out.println("EntryList_Map.size = "+ EntryList_Map.size());

        Dynamic_Make_Wisl_I9_DescTxtList(matchhDynamicDescStrList);

    }

    
    
	// 获取字符串 \n 前的字符串
	public static String getPre_N(String mStr) {
		if(!mStr.contains("\n")) {
			return  mStr;
		}
		
		int N_index = mStr.indexOf("\n");
		String preN_Str =mStr.substring(0,N_index).trim();
		
		return preN_Str;
		
		
	}
	
    //  GPS BT WIFI NFC 的 desc 都写在 同一个 txt 文件中
    // J9_I9_Dynamic_ClearDesc.txt   // 实现在 ztextrule_operation_I9.bat 中每次都要手动去除描述的烦恼
    static void Dynamic_Make_Wisl_I9_DescTxtList(ArrayList<String> mDescList) {
    	if(mDescList == null || mDescList.size() == 0 ) {
    		System.out.println("Dynamic_Make_Wisl_I9_DescTxtList 当前 mDescList="+mDescList+"  为空!");
    		return;
    	}
    	
    	for (int i = 0; i < mDescList.size(); i++) {
    		String DescItem = mDescList.get(i);
    		System.out.println(" Dynamic_Make_Wisl_I9_DescTxtList  DescItem["+i+"] = "+DescItem);
			
		}
    	
    	File J9_I9_Dynamic_DescTxt_File = new File(zbinPath+File.separator+"J9_I9_Dynamic_ClearDesc.txt");
    	writeContentToFile(J9_I9_Dynamic_DescTxt_File, mDescList);
    	System.out.println("写入 "+J9_I9_Dynamic_DescTxt_File.getAbsolutePath()+"文件成功!");
   
    }
    
    // J9_K2_Dynamic_KeyWord_Common.txt     // 动态创建 这样的 Txt 文件写入每行关键字
    // J9_K2_Dynamic_KeyWord_GPS.txt
    // J9_K2_Dynamic_KeyWord_WIFI.txt
    // J9_K2_Dynamic_KeyWord_BT.txt
    // J9_K2_Dynamic_KeyWord_NFC.txt  
    // 动态 创建 提供给  znotepad_tip_K2.bat 使用的 关键字 副产品
    static void Dynamic_Make_Wisl_K2_TxtKey(String sheetName,ArrayList<String> mKeyList) {
    	if(isStrEmpty(sheetName) ) {
    		System.out.println("Dynamic_Make_Wisl_K2_TxtKey 当前 sheetName="+sheetName+"  为空!");
    		return;
    	}
    	
    	// COMMON_LOG  WIFI_Log 这样的sheet名称 没有包含关键字 只是对 Log 的说明 过滤
    	if(sheetName.toLowerCase().endsWith("_log")) {
    		System.out.println("Dynamic_Make_Wisl_K2_TxtKey 当前 sheetName="+sheetName+"  为空!");
    		return;
    	}
    	
    	
    	
    	if(mKeyList == null || mKeyList.size() == 0 ) {
    		System.out.println("Dynamic_Make_Wisl_K2_TxtKey 当前 mKeyList="+mKeyList+"  为空!");
    		return;
    	}
    	
    	for (int i = 0; i < mKeyList.size(); i++) {
    		String keyItem = mKeyList.get(i);
    		System.out.println("sheetName["+sheetName+"]key["+i+"] = "+keyItem);
			
		}
    	
    	File J9_K2_Dynamic_Txt_File = new File(zbinPath+File.separator+"J9_K2_Dynamic_KeyWord_"+sheetName+".txt");
    	writeContentToFile(J9_K2_Dynamic_Txt_File, mKeyList);
    	System.out.println("写入 "+J9_K2_Dynamic_Txt_File.getAbsolutePath()+"文件成功!");
    	
    }

    
    
    
    static boolean isStrEmpty(String tableStr) {
        if (tableStr == null || "".equals(tableStr.trim())) {
            return true;
        }
        return false;
    }


    static boolean isBooleanEmpty(String tableStr) {
        if (tableStr == null || "".equals(tableStr.trim())) {
            return true;
        }
        if (tableStr.toLowerCase().trim().equals("n")) {
            return true;
        }
        if (tableStr.toLowerCase().trim().equals("y")) {
            return false;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    public static void getKeyAndValue_Title_Row(Map<String, String> mMapParam, String title, int row) {
        Map.Entry<String, String> entryItem;
        int indexNum = 1;
        if (mMapParam != null) {
            Iterator iterator = mMapParam.entrySet().iterator();
            while (iterator.hasNext()) {
                entryItem = (Map.Entry<String, String>) iterator.next();
                String keyStr = entryItem.getKey();   //Map的Key
                String ValueStr = entryItem.getValue();  //Map的Value
                System.out.println("title[" + title + "]  row[" + row + "]  column[" + indexNum + "]  head[" + keyStr.replace("\n", "") + "]  value=【" + ValueStr + "】");
                indexNum++;
            }
            System.out.println();
        }
    }

    @SuppressWarnings("unchecked")
    public static void getKeyAndValue(Map<String, String> mMapParam) {
        Map.Entry<String, String> entryItem;
        int indexNum = 1;
        if (mMapParam != null) {
            Iterator iterator = mMapParam.entrySet().iterator();
            while (iterator.hasNext()) {
                entryItem = (Map.Entry<String, String>) iterator.next();
                String keyStr = entryItem.getKey();   //Map的Key
                String ValueStr = entryItem.getValue();  //Map的Value
                System.out.println("index=" + indexNum + "  head[" + keyStr + "]  value=【" + ValueStr + "】");
                indexNum++;
            }
            System.out.println();
        }
    }


    static void readKeyItemFromXlsx(String xlsxPath) {

        // key---sheet名称    value: 每个sheet的每列的集合  其中Map<String,String> 是 key列头名称  value是列对应的内容


        Sheet sheetItem = null;
        Row row = null;
        List<Map<String, String>> sheetRow_List = null;
        String cellData = null;

//        String columns[] = {"exchange","cal_date","is_open","pretrade_date"};

        Workbook workBook = readExcel(xlsxPath);
        int sheetNum = workBook.getNumberOfSheets();


        if (workBook != null) {
            System.out.println("xlsxPath  = " + xlsxPath + "   AllSheetIndex=" + sheetNum + "  ");

            for (int i = 0; i < sheetNum; i++) {
                String sheetName = workBook.getSheetName(i);
                sheetRow_List = new ArrayList<Map<String, String>>();
                if (sheetName.toLowerCase().contains("log")) {
                    continue;   // 去除 那些  sheet 名称包含 log的 sheet  这些sheet 用于 记录 Log载体
                }
                System.out.println("xlsxPath  = " + xlsxPath + "   curSheetNum=" + i + "  sheetName=" + sheetName);


                sheetItem = workBook.getSheetAt(i);
                int row_Max = sheetItem.getPhysicalNumberOfRows();    //获取最大行数
                Row firstRow = sheetItem.getRow(0);  //获取第一行
                if (firstRow == null) {  // 如果为空 那么 continue
                    System.out.println("sheet[" + i + "] == " + "NULL --> " + "xlsxPath  = " + xlsxPath + "   sheetNum=" + sheetNum + "  sheetName=" + sheetName);
                    continue;
                }
                int column_Max = firstRow.getPhysicalNumberOfCells();     //获取最大列数


                String[] tableHeadList = new String[column_Max];

                for (int j = 0; j < row_Max; j++) {
                    Map<String, String> map = Maps.newLinkedHashMap();

                    row = sheetItem.getRow(j);
                    if (row != null) {
                        if (j == 0) {  // 第一行内容
                            for (int k = 0; k < column_Max; k++) {
                                cellData = (String) getCellFormatValue(row.getCell(k));
                                tableHeadList[k] = cellData;
                            }


                        } else {

                            System.out.println("----------------");
                            for (int k = 0; k < column_Max; k++) {
                                cellData = (String) getCellFormatValue(row.getCell(k));

/*                            if(cellData.contains("\n")){
                                System.out.println("多行关键词 [i="+i+"][j="+j+"][k="+k+"] cellData = "+ cellData);
                            }else{
                                System.out.println("单行关键词 [i="+i+"][j="+j+"][k="+k+"] cellData = "+ cellData);
                            }*/
                                System.out.println("key[" + tableHeadList[k] + "]  value[" + cellData + "]");
                                map.put(tableHeadList[k], cellData);
                            }
                            System.out.println("----------------");
                            sheetRow_List.add(map);
                        }

                    } else {
                        break;
                    }

                }

                sheetMap.put(sheetName, sheetRow_List);
            }

        }

/*
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
//            sheet = wb.getSheet("股票列表");
//            sheet = wb.getSheetAt(0);
            sheet = wb.getSheet("交易日历");
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }*/

    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


    static int Log_Operation_Type = 0;    // 默认1:输入当前文件的绝对路径   2:在shell下执行 获得到的是当前文件夹路径

    static File notepad_command_File = null;
    static File notepad_command_dir = null;
    public static ArrayList<File> allTxtFileList = new ArrayList<File>();


    // old:最终打印的分析的字符串集合  【应该分文件处理】  new: 当前每个文件分析的字符串集合
    public static final ArrayList<String> analysisStringArr = new ArrayList<>();

    // 所有文件的 字符串 分析集合
    public static final ArrayList<ArrayList<String>> allFileAnalysisStringArr = new ArrayList<ArrayList<String>>();

    public static final ArrayList<AbstractKeyEntry> keyEntryList = new ArrayList<AbstractKeyEntry>();

    // 从 xlsx 中 读取的 关键字的 组成的 AbstractKeyEntry 的 集合
    public static final Map<String, ArrayList<AbstractKeyEntry>> EntryList_Map = Maps.newLinkedHashMap();
    public static ArrayList<String> EntryKey_List = new ArrayList<String>();

    //    static class MeetPrintBase extends CommonBase2AbstractKeyEntry {
    //    static class OncePrintBase extends CommonBase2AbstractKeyEntry {
    //    static abstract class CollectEndPrintBase extends CommonBase2AbstractKeyEntry {
    //    static abstract class MultiKeyWordPrintBase extends CommonBase2AbstractKeyEntry {
    //     static abstract class FixLongPrintBase extends CommonBase2AbstractKeyEntry {
    //   static abstract class FixLong_Diff_Pre_PrintBase extends CommonBase2AbstractKeyEntry {
    //  static abstract class DiffMeetPrintBase extends CommonBase2AbstractKeyEntry {

    // J9_Log.xlsx
    static String J9_Log_Path = zbinPath + File.separator + "J9_Log.xlsx";

    static Map<String, List<Map<String, String>>> sheetMap = Maps.newLinkedHashMap();


    public static void main_test(String[] args) {
        initSystemInfo();
        // 读取 xlsx 文件 开始对
        readKeyItemFromXlsx(J9_Log_Path);
        showSheetMap();
        initEntryFromMap();
        showEntryMap();
    }

    public static void main(String[] args) {

        initSystemInfo();


        if (!initInputParams2(args) && Log_Operation_Type == 0) {
            System.out.println("当前输入参数无法找到 对应的");
            return;
        }

        if (notepad_command_dir == null || !notepad_command_dir.exists() || !notepad_command_dir.isDirectory()) {
            return;
        }

        // 获取当前文件夹下 所有的 .txt 文件后缀
        allTxtFileList = getAllSubFile(notepad_command_dir, ".txt");


        // 读取 xlsx 文件 开始对
        readKeyItemFromXlsx(J9_Log_Path);
        showSheetMap();
        initEntryFromMap();
        showEntryMap();


        EntryKey_List.addAll(EntryList_Map.keySet());


        ArrayList<String> appendLogArr = new ArrayList<String>();
        appendLogArr.add("════════════════Begin ════════════════  全文件LOG 搜索 ");


        analysis_with_type();    //  以 类型区分
        analysisStringArr.add("\n\n\n\n════════════════【Time-Together Analysis Begin 】════════════════");
        analysis_with_time();    //  以 时间 区分


        int length = allFileAnalysisStringArr.size();
        for (int index = 0; index < length; index++) {
            ArrayList<String> arritem = allFileAnalysisStringArr.get(index);
            System.out.println("开始分析第 index = " + index + "个文件！");

            for (int i = 0; i < arritem.size(); i++) {
                appendLogArr.add(arritem.get(i));

            }
        }
        appendLogArr.add("════════════════ End   全文件LOG 搜索 ════════════════  \n\n\n\n");
        System.out.println("OK !");

        appendToFile(notepad_command_File, appendLogArr);

        NotePadOpenTargetFile(notepad_command_File.getAbsolutePath());

        setProperity();

        if (true) {
            return;
        }
        J9_Wisl_Log_Search mJ9_Object = new J9_Wisl_Log_Search();
        mJ9_Object.InitRule();


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


    public static void tryAnalysis_Time(File mainFile, int fileIndex) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mainFile), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {

                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }


                for (int i = 0; i < EntryKey_List.size(); i++) {
                    String sheetKey = EntryKey_List.get(i);
                    ArrayList<AbstractKeyEntry> curKeyEntryList = EntryList_Map.get(sheetKey);
                    for (AbstractKeyEntry keyEntry : curKeyEntryList) {
                        keyEntry.analysisLineContent(lineContent);
                    }

                }


                //

            }

            for (int i = 0; i < EntryKey_List.size(); i++) {
                String sheetKey = EntryKey_List.get(i);
                ArrayList<AbstractKeyEntry> curKeyEntryList = EntryList_Map.get(sheetKey);
                // 开始对每行开始分析

                for (AbstractKeyEntry keyEntry : curKeyEntryList) {
                    keyEntry.doAfterReadOverFile();
                }

            }

            if (analysisStringArr.size() > 0) {
                analysisStringArr.add(1, "=============开始对 Log文件[" + fileIndex + "]: " + mainFile.getName() + " 进行 Time-Together 分析！");

            } else {

                analysisStringArr.add(0, "=============开始对 Log文件[" + fileIndex + "]: " + mainFile.getName() + " 进行 Time-Together 分析！");

            }



            curBR.close();

        } catch (Exception e) {
            StackTraceElement[] stackElements = e.getStackTrace();
            if (stackElements != null) {
                for (int i = 0; i < stackElements.length; i++) {
                    System.out.print("ClassName : " + stackElements[i].getClassName() + " , ");
                    System.out.print("FileName : " + stackElements[i].getFileName() + " , ");
                    System.out.print("Number : " + stackElements[i].getLineNumber() + " , ");
                    System.out.println("Method : " + stackElements[i].getMethodName());
                }
            }

        }
    }


    public static void tryAnalysis_type(ArrayList<AbstractKeyEntry> mEntryList, File mainFile, int fileIndex) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mainFile), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {

                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }


                for (AbstractKeyEntry keyEntry : mEntryList) {
                    keyEntry.analysisLineContent(lineContent);
                }
/*               for (int i = 0; i < EntryKey_List.size() ; i++) {
                   String sheetKey = EntryKey_List.get(i);
                   ArrayList<AbstractKeyEntry> curKeyEntryList =   EntryList_Map.get(sheetKey);
                   for (AbstractKeyEntry keyEntry : curKeyEntryList) {
                       keyEntry.analysisLineContent(lineContent);
                   }

               }*/


                //

            }

/*            for (int i = 0; i < EntryKey_List.size() ; i++) {
                String sheetKey = EntryKey_List.get(i);
                ArrayList<AbstractKeyEntry> curKeyEntryList =   EntryList_Map.get(sheetKey);
                // 开始对每行开始分析

                for (AbstractKeyEntry keyEntry : curKeyEntryList) {
                    keyEntry.doAfterReadOverFile();
                }

            }*/

            for (AbstractKeyEntry keyEntry : mEntryList) {
                keyEntry.doAfterReadOverFile();
            }

            if (analysisStringArr.size() > 0) {
                analysisStringArr.add(1, "=============开始对 Log文件[" + fileIndex + "]: " + mainFile.getName() + " 进行分析！");

            } else {

                analysisStringArr.add(0, "=============开始对 Log文件[" + fileIndex + "]: " + mainFile.getName() + " 进行分析！");

            }
//            analysisStringArr.add(0, "=============开始对 Log文件["+fileIndex+"]: " + mainFile.getName() + " 进行分析！");
            //  analysisStringArr.add("=============开始对 Log文件: " + mainFile.getName() + " 进行分析！");
            curBR.close();

        } catch (Exception e) {
            StackTraceElement[] stackElements = e.getStackTrace();
            if (stackElements != null) {
                for (int i = 0; i < stackElements.length; i++) {
                    System.out.print("ClassName : " + stackElements[i].getClassName() + " , ");
                    System.out.print("FileName : " + stackElements[i].getFileName() + " , ");
                    System.out.print("Number : " + stackElements[i].getLineNumber() + " , ");
                    System.out.println("Method : " + stackElements[i].getMethodName());
                }
            }

        }
    }


    public static void appendToFile(File file, ArrayList<String> logList) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }


            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(file, "rwd");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);


            for (int i = 0; i < logList.size(); i++) {
                String logItem = logList.get(i);
                randomFile.write((logItem + "\n").getBytes("utf-8"));

            }

//            randomFile.write(("index = "+index+"============开机打印【"+DateUtil.now()+ "】========== \n").getBytes("utf-8"));


            //  randomFile.write("\n".getBytes());  // 换行
            randomFile.close();
        } catch (Exception e) {


        }
    }

    public static int getImageHigh(File picture) {
        int high = 0;
        ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
        high = imageIcon.getIconHeight();

        return high;
    }


    public static int getImageWidth(File picture) {
        int width = 0;
        ImageIcon imageIcon = new ImageIcon(picture.getAbsolutePath());
        width = imageIcon.getIconWidth();
        return width;
    }


    @SuppressWarnings("unchecked")
    public static void insertArrayToHead(ArrayList<String> src, ArrayList<String> dst) {
        if (src != null && dst != null) {
            ArrayList tempList = new ArrayList<String>();
            tempList.addAll(dst);
            tempList.addAll(src);
            src.clear();
            src.addAll(tempList);
        }
    }

    //set集合去重，不改变原有的顺序
    @SuppressWarnings("unchecked")
    public static ArrayList<String> toRemoveSame(ArrayList<String> list) {
        //System.out.println("list = [" + list.toString() + "]");
        ArrayList<String> listNew = new ArrayList<String>();
        Set set = new HashSet();
        for (String str : list) {
            if (set.add(str)) {
                listNew.add(str);
            }
        }
        return listNew;
    }


    static class CollectEndPrintBase extends CommonBase2AbstractKeyEntry {
        CollectEndPrintBase(String keyWordStr, String desc, String path) {
            keyword = keyWordStr;
            explain = desc;
            codePath = path;
            logArrayPrintEnd = new ArrayList<String>();
            shouldFixLog = true;     //  最后添加到一起的Log  也需要对当前的 Log 进行过滤 以取到 核心信息
        }


        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

        ;  // 用于返回那些 需要打印在最后的Log的模样

        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }

        ; // 用于判断是否包含Log , 包含不打印  不包含打印

        @Override
        public String fixCurrentLog(String lineContent) {
            return toReturnFixString(lineContent);
        }


        @Override
        public void doAfterReadOverFile() {
            insertArrayToHead(analysisStringArr, toRemoveSame(logArrayPrintEnd));
            logArrayPrintEnd.clear();
        }

        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);
            if (!logArray.contains(subLogLine)) {

                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }
    }


    static class MultiKeyWordPrintBase extends CommonBase2AbstractKeyEntry {
        MultiKeyWordPrintBase(String keyStr, String desc, String path) {
            explain = desc;
            codePath = path;
//            keyword = keyStr;
            String[] strArr = keyStr.split("\n");
            keywordArr = new ArrayList<String>();
            for (int i = 0; i < strArr.length; i++) {
                if (i == 0) {
                    keyword = strArr[i];
                }
                keywordArr.add(strArr[i]);
            }
        }


        public ArrayList<String> getKeyWordArrayList() {
            return keywordArr;
        }
    }


    static class MeetPrintBase extends CommonBase2AbstractKeyEntry {  // 默认的Log
        MeetPrintBase(String keyWord, String desc, String path) {
            keyword = keyWord;
            explain = desc;
            codePath = path;


        }
    }


    static class OncePrintBase extends CommonBase2AbstractKeyEntry {
        OncePrintBase(String keyWord, String desc, String path) {
            keyword = keyWord;
            explain = desc;
            codePath = path;
            justPrintOnce = true;
        }
    }


    static abstract class FixLongPrintBase extends CommonBase2AbstractKeyEntry {
        FixLongPrintBase() {
            shouldFixLog = true;
        }

        @Override
        public String fixCurrentLog(String lineContent) {
            return toReturnFixString(lineContent);
        }

        abstract public String toReturnFixString(String lineContent);
    }

    static abstract class FixLong_Diff_Pre_PrintBase extends CommonBase2AbstractKeyEntry {
        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串

        abstract public String toGetDiffSubString(String lineContent);

        abstract public String toReturnFixString(String lineContent);


        FixLong_Diff_Pre_PrintBase() {
            shouldFixLog = true;
        }

        @Override
        public String fixCurrentLog(String lineContent) {
            String returnStr = toReturnFixString(lineContent);
            //System.out.println("FixLong_Diff_Pre_PrintBase - fixCurrentLog   returnStr =" + returnStr);
            return returnStr;
        }


        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);

            if (subLogLine == null) {
                shouldPrint = false;
            }
            String lastItem = "";
            if (logArray.size() == 0) {
                logArray.add(subLogLine);

            } else if (logArray.size() >= 1) {
                lastItem = logArray.get(logArray.size() - 1);
            }
            //System.out.println("FixLong_Diff_Pre_PrintBase - lastItem =" + lastItem + " logArray.size() = "+ logArray.size());
            //  System.out.println("FixLong_Diff_Pre_PrintBase - lineContent =" + lineContent);
            //System.out.println("FixLong_Diff_Pre_PrintBase - subLogLine =" + subLogLine);
            //  if (!logArray.contains(subLogLine)) {
            // 只有与最后一个值  不一样的情况下才打印这个关键字
            if (lastItem != null && subLogLine != null &&
                    !lastItem.isEmpty() && !subLogLine.isEmpty() && !subLogLine.equals(lastItem)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else if (lastItem != null && lastItem.isEmpty() && subLogLine != null) {  // 第一次加载的时候 lastItem 为空  那么就需要把第一次遇见的加进去log数组
                logArray.add(subLogLine);
                shouldPrint = true;
            } else if (lastItem == null && subLogLine != null) {
                logArray.add(subLogLine);
                shouldPrint = true;
            } else {
                shouldPrint = false;
            }
        }
    }


    static class DiffMeetPrintBase extends CommonBase2AbstractKeyEntry {  // 只打印不同Log的那些Case
        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串
        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }

        ;


        DiffMeetPrintBase(String keyWord, String desc, String path) {
            keyword = keyWord;
            explain = desc;
            codePath = path;

        }

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

    }


    static class CommonBase2AbstractKeyEntry extends AbstractKeyEntry {
        CommonBase2AbstractKeyEntry() {
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }

        public String fixCurrentLog(String lineContent) {
            return "";
        }

        public void doAfterReadOverFile() {
            logArray.clear();  // 所有的 当前文件产生的 ArrayLog 清楚掉   以迎接下一个文件的分析
        }

        @Override
        public ArrayList<String> getManyInfoFromOneLineMethod(String lineContent) {
            return null;
        }
    }


    abstract static class AbstractKeyEntry {
        public String keyword; // 关键字
        public String explain; // 说明
        public String codePath;  // 代码 该Log打印的代码路径
        public String curLogLineContent;  // 当前记录的那行Log  从Log起始点 开始

        public boolean shouldPrint = true; // MeetPrintBase  是否应该打印  Common的属性
        public ArrayList<String> logArray;  // DiffMeetPrintBase 使用 当前记录的有些不一样的记录行的 集合
        public ArrayList<String> logArrayPrintEnd;  // CollectEndPrintBase   持续收集 最后打印到到文件开头的Log
        public boolean getManyInfoFromOneLine = false; // 只有 CollectManyFromOneEndPrintBase 下为 true 是否会从一条Log 中得到 很多的信息

        public boolean shouldFixLog = false; // FixLongPrintBase 是否需要对当前的Log 进行一些Log的处理 比如太长截取

        public ArrayList<String> keywordArr;  // MultiKeyWordPrintBase 使用 当前记录的有些不一样的记录行的 集合

        public boolean justPrintOnce = false; //  false 表示  关闭该功能    true 表示打开功能   OncePrintBase   是否只打印一次
        public boolean justPrintOnce_work = false;    // 当 justPrintOnce= true 时 起作用 , 当执行完一次 打印时  该值为 true


        AbstractKeyEntry() {
            logArray = new ArrayList<String>();
//            keywordArr = new ArrayList<String>();
//            logArrayPrintEnd = new ArrayList<String>();
        }

        abstract public void preCheck(String lineContent);         //  在执行每个实例类的 analysisLineContent函数 之前执行的函数

        abstract public void afterCheck(String lineContent);       //  在执行每个实例类的 analysisLineContent函数 之后执行的函数

        abstract public String fixCurrentLog(String lineContent);  //  fixTooLong()     FixLongPrintBase  需要执行的方法

        abstract public void doAfterReadOverFile();                  // CollectEndPrintBase 文件读取完成后执行的方法

        abstract public ArrayList<String> getManyInfoFromOneLineMethod(String lineContent);  // 从一行获取 多个信息的函数   区别于 CollectEndPrintBase_ 一行获取一个信息

        public void analysisLineContent(String lineContent) {
            if (lineContent == null || keyword == null || !lineContent.contains(keyword)) {
                return;
            }
            if (keywordArr != null && keywordArr.size() > 0) {
                AtomicBoolean findMultiWord = new AtomicBoolean(true);
                for (String keyword : keywordArr) {
                    System.out.println("findMultiWord1="+findMultiWord+"  keyword = "+ keyword + "   ====  【lineContent】"+lineContent);
                    if (!(lineContent + " ").contains(keyword)) {

                        findMultiWord.set(false);
                        System.out.println("findMultiWord2 = "+ findMultiWord);
                        break;
                    }
                }
                if (!findMultiWord.get()) {  // 没有在该行中同时找到 多关键字
                    System.out.println("findMultiWord3 = "+ findMultiWord);
                    return;
                }
            }
            preCheck(lineContent);
            String checkItem;
            if (lineContent.contains(keyword)) {
                checkItem = new String(lineContent);   //  要打印的原始的Log行
                ArrayList<String> result = null;
                if (shouldFixLog && !getManyInfoFromOneLine) {  //  是否原始的Log行   tooLong  太长 需要修改  // 如果需要的话 执行 fixCurrentLog(checkItem)
                    checkItem = fixCurrentLog(checkItem);
                } else if (shouldFixLog && getManyInfoFromOneLine) {  // 从一行打印很多的数据
                    result = getManyInfoFromOneLineMethod(checkItem);  //    CollectManyFromOneEndPrintBase 才会执行
                }
                if (checkItem == null || checkItem.isEmpty()) {
                    return;      // 如果当前的 checkItem 为空 那么返回
                }
                checkItem = checkItem + "    " + explain;  //  添加 该行代码的解释
                if (codePath != null) {    //  如果 代码有路径  接着打印代码
                    checkItem = checkItem + "   【Code路径: " + codePath + "  】";
                }
                if (shouldPrint) {  // 依据  shouldPrint 标识 决定是否添加到  analysisStringArr

                    if (justPrintOnce) {
                        if (!justPrintOnce_work) {
                            analysisStringArr.add(checkItem);
                            justPrintOnce_work = true;   //  关闭 当前 打印  OncePrintBase的处理
                        }
                    } else {
                        if (logArrayPrintEnd == null) {  // 对于 CollectEndPrintBase  最后才打印的那些Log 在这里 就 不打印了 而是添加到自己的logArrayPrintEnd中
                            analysisStringArr.add(checkItem);
                        } else {
                            if (!getManyInfoFromOneLine) {
                                logArrayPrintEnd.add(checkItem);
                            } else {

                                if (result != null && result.size() > 0) {
                                    logArrayPrintEnd.addAll(result);
                                }
                            }

                        }

                    }

                }

            }
            afterCheck(lineContent);
            return;
        }
    }


}
