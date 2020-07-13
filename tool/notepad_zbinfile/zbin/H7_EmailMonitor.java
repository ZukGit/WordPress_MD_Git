
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.swing.ScreenUtil;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IdleManager;


import java.net.URL;
import java.net.URLConnection;

import java.text.SimpleDateFormat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;


//1.自定义数字命令  【zukgitcommand1】 【zukgitcommand2】【zukgitcommand9】....【zukgitcommand99】
//2. 执行网络脚本命令 【zukgitcommand(https://xxxx.bat)】 【zukgitcommand(https://xxxx.sh)】
//3. 模拟PC按键  模拟鼠标 模拟点击 命令 【zukgitcommand(winkey_win+d_win+e_mousekey_200+133_winkey_win+r)】
//3.1 【未实现】 模拟 手机 按钮事件 坐标移动点击事件  【zukgitcommand(mobilekey_xxxxxx_mobilemouse_xxx+xxx)】
     // lock   power  volumdown volumup back home a-zA-Z0-9 movetop movebuttom   xx+yy+click xx+yy+longclick
//4. 自定义命令 【zukgitcommand(openmobilewifi)】
//5. 系统命令 【zukgitcommand(shutdown -p)】


//   等待 邮件到来 处理邮件
public class H7_EmailMonitor {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 H7 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zemail_monitor_H7";


/*******************修改属性列表 ------End *********************/


    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String H7_WorkPlace = zbinPath + File.separator + "H7_Email_WorkPlace";
    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File H7_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream H7_Properties_InputStream;
    static OutputStream H7_Properties_OutputStream;
    static Properties H7_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE;   // 当前 CMDER的路径 File 文件


    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出


    static int CUR_TYPE_INDEX = 0;   // 当前用户选中的 操作的类型  默认0-标识没有选中
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




/*
邮件的主题为 : 迅雷会员续费提醒
发件人地址为 : =?UTF-8?B?6L+F6Zu35Lya5ZGY5Lit5b+D?= <user@vip.xunlei.com>
邮件标识Flag :

=?UTF-8?B?5LiA5Y+35bqXNOWRqOW5tOW6hu+8jDEwMDDkuIfku7bng63plIA=?=
这个是邮件头的编码格式
B表示是Base64编码
UTF-8表示字符集的编码
所以取出来的内容为：
一号店4周年庆，1000万个热销
搞的我跟打广告似的

*/


// https://my.oschina.net/sunneverset/blog/536712?p={{totalPage}}


    public  static void schedule_email() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                schedule_email();
            }
        },1800000);   // 1800秒  半小时
        tryGmail_Idle();
    }


    public static void main(String[] args) {
        while (true) {
            try {
                schedule_email();

                System.out.println("连接gmail服务器 请先确保当前网络连接到 google ! 该程序才能运行!");
            }catch (Exception e1){
                try {
                    Thread.sleep(60000);
                }catch (Exception e2){
                    System.out.println("发生异常 sleep 60秒 重新执行!");
                }

            }

        }
    }

    public static void main2(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if (i == 1) {  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];
                    //zukgit1    计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    CUR_TYPE_INDEX = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                } else {
                    CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

        H7_EmailMonitor mH7_Object = new H7_EmailMonitor();
        mH7_Object.InitRule();


        // 用户没有输入参数
        if (CUR_TYPE_INDEX == 0 && CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }


        if (CUR_TYPE_INDEX == 0) {
            showNoTypeTip();
            return;
        }


        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则


        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null || !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE, CUR_TYPE_2_ParamsStr, CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }



/*
        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }
*/


        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
            return;
        }


        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理


        setProperity();
    }


    static boolean isContainEnvironment(String program) {
        boolean environmentExist = false;
        if (EnvironmentValue.contains(program)) {
            environmentExist = true;
        }
        return environmentExist;
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
            if (!H7_Properties_File.exists()) {
                H7_Properties_File.createNewFile();
            }
            H7_Properties_InputStream = new BufferedInputStream(new FileInputStream(H7_Properties_File.getAbsolutePath()));
            H7_Properties.load(H7_Properties_InputStream);
            Iterator<String> it = H7_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + H7_Properties.getProperty(key));
                propKey2ValueList.put(key, H7_Properties.getProperty(key));
            }
            H7_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            H7_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(H7_Properties_File.getAbsolutePath()));
            H7_Properties.store(H7_Properties_OutputStream, "");
            H7_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }


    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name + ".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
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


    class MergeMP4_Rule_1 extends Basic_Rule {
        ArrayList<File> curInputFileList;


        MergeMP4_Rule_1() {
            super(1);
            curInputFileList = new ArrayList<File>();
        }


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
    }


    class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex, int opera_type) {
            this.file_type = ctype;
            this.rule_index = cindex;
            this.operation_type = opera_type;
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
        }

        Basic_Rule(int ruleIndex) {
            this.rule_index = ruleIndex;
            this.file_type = "";
            this.operation_type = 0;
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
        }

        String applyStringOperationRule1(String origin) {
            return origin;
        }

        File applyFileByteOperationRule2(File originFile) {
            return originFile;
        }

        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            return subFileList;
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return curFileList;
        }


        void initParams4InputParam(String inputParam) {
        }

        String simpleDesc() {
            return null;
        }

        void operationRule(ArrayList<String> inputParamsList) {
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

        int operation_type;  // 默认为0
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        String errorMsg;
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        ArrayList<File> inputFileList;  // 从输入参数得到的文件的集合

        abstract void operationRule(ArrayList<String> inputParamsList);

        abstract String applyStringOperationRule1(String origin);

        abstract File applyFileByteOperationRule2(File originFile);

        abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap);

        abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList);

        abstract void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串

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


    static void showNoTypeTip() {

        System.out.println("当前用户输入的 操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name + " 的 第一个输入参数中的第一个int值 ");
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
                itemDesc = "zrule_apply_H7.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_H7 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
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


    static String USER = "zukgit382581427@gmail.com";
    static String PASSWORD = "loqkcvyihndqvqth";
    static String SMTP_HOST = "smtp.gmail.com";
    static String IMAP_HOST = "imap.gmail.com";

    public static void tryGmail_Idle() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = df.format(new Date());

        System.out.println("\nTesting monitor " + timeStamp + "\n");


//        String host = "imap.163.com";
//        String user = "zukgit@163.com";
//        String password = "UXLBBLORCRIOHKMK";


        String host = "imap.gmail.com";
        String user = "zukgit382581427@gmail.com";
        String password = "loqkcvyihndqvqth";  //？


        String mbox = "INBOX";
        String freqs = "15000";
        boolean isOpenidle = true;

        try {

            Properties props = new Properties();
/*
            props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址
        */


//            props.setProperty("mail.imaps.usesocketchannels","true");


            // 得到session对象
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            ExecutorService es = Executors.newCachedThreadPool();
            final IdleManager idleManager = new IdleManager(session, es);
            // 获取store对象
            Store store = session.getStore("imaps");

            // Connect
            store.connect(host, user, password);
//            store.connect(host, user, password);
            System.out.println("Connect End!");

            // Open a Folder
            Folder folder = store.getFolder(mbox);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder");
                System.exit(1);
            }

            folder.open(Folder.READ_WRITE);

            //https://github.com/liutian/java-mail/tree/master/example

            // Add messageCountListener to listen for new messages
            folder.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
                    System.out.println("Got " + msgs.length + " new messages");

                    // Just dump out the new messages
                    System.out.println("------ addMessageCountListener Begin ------");
                    for (int i = 0; i < msgs.length; i++) {
                        try {
                            Message message = msgs[i];
                            String mReceiveSubJectItem = msgs[i].getSubject();

                            System.out.println("mReceiveSubJectItem = " + mReceiveSubJectItem);
                            if (isSubjectZukgitCommand(mReceiveSubJectItem)) {
                                System.out.println("isSubjectZukgitCommand = true  for " + mReceiveSubJectItem);
                                taskList.add(new H7_EmailMonitor().new TaskThread(message, mReceiveSubJectItem));
                            } else {
                                System.out.println("isSubjectZukgitCommand = false  for " + mReceiveSubJectItem);

                            }

                            System.out.println("-----");
                            System.out.println("Message[" + i + "]->ID  = " + msgs[i].getMessageNumber() + ":");
                            System.out.println(msgs[i].getSubject());
                            showIDLEMessage(message);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("执行 Runnable 方法! ");
                                    for (int j = 0; j < taskList.size(); j++) {
                                        if (!taskList.get(j).isExecute) {
                                            System.out.println("执行[" + j + "] --- " + taskList.get(j).mReceiveSubJect);
                                            taskList.get(j).start();
                                        }
                                    }
                                }
                            }).start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    System.out.println("------ addMessageCountListener Begin End");
                }
            });

            // 检查是否支持imap idle,尝试使用idle
            int freq = Integer.parseInt(freqs);
            boolean supportsIdle = false;
            try {
                if (isOpenidle && folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    System.out.println("f.idle() -> 1 Begin");
                    f.idle();
                    System.out.println("f.idle() -> 1 End");
                    supportsIdle = true;
                    System.out.println(" supportsIdle = true ");
                }
            } catch (FolderClosedException fex) {
                throw fex;
            } catch (MessagingException mex) {
                supportsIdle = false;

//  执行  f.idle(); 出现了 异常
//  MessagingException = javax.mail.MessagingException: idle method not supported with SocketChannels

                System.out.println("MessagingException = " + mex);
                System.out.println(" supportsIdle = false ");
            }
            for (; ; ) {
                if (supportsIdle && folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    System.out.println("f.idle() -> 2 Begin ");
                    f.idle();
                    System.out.println("f.idle() -> 2 End ");
                } else {
                    System.out.println("idle不支持进入轮询");
                    Thread.sleep(freq); // sleep for freq milliseconds

                    // 注意。getMessageCount时会触发监听器
                    int count = folder.getMessageCount();
                    System.out.println("当前邮件数量:" + count);
                }
                System.out.println("for(;;) End!");
            }

        } catch (Exception ex) {
            System.out.println("Idle() 异常!");
            ex.printStackTrace();
        }
    }


    static void showIDLEMessage(Message messageEmail) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        try {


            System.out.println("___________________showIDLEMessage Begin");
            System.out.println("messageEmail = " + messageEmail.toString());
            // 是否被删除

            Date receiveDate = messageEmail.getReceivedDate();
            String receiveDateStr = "";
            String sendDateStr = "";
            if (receiveDate != null) {
                receiveDateStr = df.format(receiveDate);
            }

            System.out.println("ReceivedDate = " + receiveDateStr);
            Date sendDate = messageEmail.getSentDate();
            if (sendDate != null) {
                sendDateStr = df.format(sendDate);
            }

            // 发送日期sendDateStr20200528_143837    早于    接收日期ReceivedDate 20200528_143841
            System.out.println("SentDate = " + sendDateStr);

            String subject = messageEmail.getSubject();// 获得邮件主题
            System.out.println("Subject = " + subject);


            boolean isAlredyDelete = messageEmail.isExpunged();
            System.out.println("isExpunged = " + isAlredyDelete);


            // Returns the message number for this message within its folder.
            //在这个文件夹中返回此邮件的邮件编号。  唯一ID 编号?
            int mMessageNumber = messageEmail.getMessageNumber();
            System.out.println("mMessageNumber = " + mMessageNumber);

            int size = messageEmail.getSize();
            System.out.println("size = " + size);


//  System.out.println("Content = "+ messageEmail.getContent());//  无法显示 IDLE 的 Message的  getContent  内容

//  String contentType = messageEmail.getContentType();//  无法显示 IDLE 的 Message的  getContentType
//  System.out.println("contentType = "+ contentType);


//  String mdesc = messageEmail.getDescription();    //  无法显示 IDLE 的 Message的  getDescription
//  System.out.println("mdesc = "+ mdesc);

//  String mdisp = messageEmail.getDisposition();   //  无法显示 IDLE 的 Message的  getDisposition
//  System.out.println("mdisp = "+ mdisp);


//            int lineCout = messageEmail.getLineCount();  // // 在  IDLE  无法显示getLineCount
//            System.out.println("lineCout = " + lineCout);

//            String fileName = messageEmail.getFileName();// 在  IDLE  无法显示getFileName
//            System.out.println("fileName = "+ fileName);


        } catch (Exception e) {
            System.out.println("showMessage Exception!  = " + e);
        }


//            ---------Show IDLE Header[8]---------
//                    HeadIndex[8]  Begin
//            HeadName:Return-Path
//            HeadValue:<1062965952@qq.com>
//            header.toString() = javax.mail.Header@5c126b8a
//            HeadIndex[8]  End

        try {

/*

            String[]  replyEmailAddress =     messageEmail.getHeader("Return-Path");
            for (int i = 0; i < replyEmailAddress.length; i++) {
                System.out.println("ReplyEmail["+i+"]  = " +replyEmailAddress[i]);
            }

*/


            Enumeration<Header> enumeration = messageEmail.getAllHeaders();
            int headIndex = 1;
            while (enumeration.hasMoreElements()) {
                Header header = enumeration.nextElement();
                System.out.println("---------" + "Show IDLE Header[" + headIndex + "]" + "---------");
                ShowHeader(header, headIndex);
// Show IDLE Header Exception!  = javax.mail.FolderClosedException

                headIndex++;
            }
        } catch (Exception e) {
            System.out.println("Show IDLE Header Exception!  = " + e);
        }


        try {
            // 回复人地址
            // IDLE ReplyTo[0] = "only memory~~；" <1062965952@qq.com>
            Address[] replyTo = messageEmail.getReplyTo();
            for (int i = 0; i < replyTo.length; i++) {
                System.out.println("IDLE ReplyTo[" + i + "] = " + replyTo[i].toString());
            }
        } catch (Exception e) {
            System.out.println("Show IDLE getReplyTo  Address []  Exception!  = " + e);
        }


        try {
            // 收件人地址
            // IDLE AllRecipients[0] = "only memory~~；" <1062965952@qq.com>
            Address[] recipients = messageEmail.getAllRecipients();
            for (int i = 0; i < recipients.length; i++) {
                System.out.println("IDLE AllRecipients[" + i + "] = " + recipients[i].toString());
            }
        } catch (Exception e) {
            System.out.println("Show IDLE getAllRecipients  Address []  Exception!  = " + e);
        }


        try {
            // 收件人
            Address[] mFrom = messageEmail.getFrom();
            for (int i = 0; i < mFrom.length; i++) {
                System.out.println("IDLE AllRecipients[" + i + "] = " + mFrom[i].toString());
            }
        } catch (Exception e) {
            System.out.println("Show IDLE getFrom  Address []  Exception!  = " + e);
        }

        try {
            // 异常
            Flags flag = messageEmail.getFlags();
            System.out.println("getFlags = " + flag.toString());

        } catch (Exception e) {
            System.out.println("Show IDLE getFlags Exception!  = " + e);
        }


        try {
            Session session = messageEmail.getSession();
            System.out.println("session = " + session.toString());

        } catch (Exception e) {
            System.out.println("Show IDLE getSession Exception!  = " + e);
        }

        try {
            // 异常
            DataHandler dataHandler = messageEmail.getDataHandler();
            System.out.println("dataHandler = " + dataHandler.toString());
        } catch (Exception e) {
            System.out.println("Show IDLE getDataHandler Exception!  = " + e);
        }

        try {

//            folder = INBOX
//            folder 关闭:INBOX

            Folder folder = messageEmail.getFolder();
            System.out.println("folder = " + folder.toString());
            boolean isFolderOpen = folder.isOpen();
            if (isFolderOpen) {
                System.out.println("folder 开启:" + folder.getFullName());
            } else {
                System.out.println("folder 关闭:" + folder.getFullName());
            }
        } catch (Exception e) {
            System.out.println("Show IDLE getFolder().isOpen() Exception!  = " + e);
        }

        try {
            System.out.println(" -------  messageEmail.writeTo  邮件内容 Begin ------- ");
            messageEmail.writeTo(System.out);
            System.out.println(" -------  messageEmail.writeTo  邮件内容 End ------- ");

        } catch (Exception e) {
            System.out.println("Show IDLE getDataHandler Exception!  = " + e);
        }


        System.out.println("___________________showIDLEMessage  End");


    }

    static void showMessage(Message messageEmail) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        try {
            System.out.println("___________________showMessage Begin");
            System.out.println("messageEmail = " + messageEmail.toString());
            // 是否被删除

            Date receiveDate = messageEmail.getReceivedDate();
            String receiveDateStr = "";
            String sendDateStr = "";
            if (receiveDate != null) {
                receiveDateStr = df.format(receiveDate);
            }

            System.out.println("ReceivedDate = " + receiveDateStr);
            Date sendDate = messageEmail.getSentDate();
            if (sendDate != null) {
                sendDateStr = df.format(sendDate);
            }
            System.out.println("SentDate = " + sendDateStr);

            String subject = messageEmail.getSubject();// 获得邮件主题
            System.out.println("Subject = " + subject);

            //
            System.out.println("Content = " + messageEmail.getContent());


            boolean isAlredyDelete = messageEmail.isExpunged();
            System.out.println("isExpunged = " + isAlredyDelete);


            int mMessageNumber = messageEmail.getMessageNumber();
            System.out.println("mMessageNumber = " + mMessageNumber);

//            String contentType = messageEmail.getContentType();
//            System.out.println("contentType = "+ contentType);

            String mdesc = messageEmail.getDescription();
            System.out.println("mdesc = " + mdesc);


            String mdisp = messageEmail.getDisposition();
            System.out.println("mdisp = " + mdisp);


            String fileName = messageEmail.getFileName();
            System.out.println("fileName = " + fileName);

            int lineCout = messageEmail.getLineCount();
            System.out.println("lineCout = " + lineCout);

            int size = messageEmail.getSize();
            System.out.println("size = " + size);


            System.out.println("___________________showMessage End");
        } catch (Exception e) {
            System.out.println("showMessage Exception!  = " + e);
        }

    }

    static void showFolder(Folder folder) {

        try {
            System.out.println("___________________showFolder Begin");
            String folderName = folder.getName();
            String folderFullName = folder.getFullName();
            String folderURLName = folder.getURLName().toString();
            String folderStoreStr = folder.getStore().toString();
            int type = folder.getType();

            int mDeletedMessageCount = folder.getDeletedMessageCount();

// 两种模式           public static final int READ_ONLY = 1;
//            public static final int READ_WRITE = 2;
            int mode = folder.getMode();
            int newMessageCount = folder.getNewMessageCount();
            int mUnreadMessageCount = folder.getUnreadMessageCount();


            System.out.println("mDeletedMessageCount = " + mDeletedMessageCount);
            System.out.println("mode = " + mode);
            System.out.println("newMessageCount = " + newMessageCount);
            System.out.println("mUnreadMessageCount = " + mUnreadMessageCount);


            System.out.println("folderName = " + folderName);
            System.out.println("folderFullName = " + folderFullName);
            System.out.println("folderURLName = " + folderURLName);
            System.out.println("folderStoreStr = " + folderStoreStr);
            System.out.println("type = " + type);
            String folderString = folder.toString();
            System.out.println("folderString = " + folderString);
            Folder[] folderList = folder.list();
            System.out.println("folderList.length = " + folderList.length);

            System.out.println("___________________showFolder End");


        } catch (Exception e) {
            System.out.println("showFolder Exception!");
        }
    }

    static void ShowHeader(Header header, int index) {

        System.out.println("HeadIndex[" + index + "]  Begin ");
        System.out.println("HeadName:" + header.getName());
        System.out.println("HeadValue:" + header.getValue());
        System.out.println("header.toString() = " + header.toString());
        System.out.println("HeadIndex[" + index + "]  End ");
        System.out.println();

    }


    // https://www.ieayoio.com/2016/09/26/Java%E9%82%AE%E4%BB%B6%E5%8F%91%E9%80%81%E6%94%B6%E5%8F%96%E5%92%8C%E7%9B%91%E5%90%AC/

    // 163  邮箱  zukgit@163.com
    // IMAP/SMTP服务   FFGWTLDOYCLZTCID
    // POP3/SMTP服务   UXLBBLORCRIOHKMK
//
//    POP3服务器: pop.163.com
//    SMTP服务器: smtp.163.com
//    IMAP服务器: imap.163.com
// pop.163.com

    static ArrayList<TaskThread> taskList = new ArrayList<>();


    static String CommandFlagStr = "zukgitcommand";

    // 【zukgitcommand41】
    // 【zukgitcommand(shutdown -p)】
    static boolean isSubjectZukgitCommand(String subJect) {
        boolean flag = false;
        System.out.println("isSubjectZukgitCommand  1 ");
        if (subJect == null || "".equals(subJect)) {
            System.out.println("isSubjectZukgitCommand  3 ");
            return flag;
        }
        System.out.println("isSubjectZukgitCommand  2 ");
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {

            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();
            System.out.println("isSubjectZukgitCommand 2_1  clearFlagStr = " + clearFlagStr);
            if (isNumeric(clearFlagStr)) { // 去除 flag 标识 如果是 数值  那么返回true
                // 【zukgitcommand41】
                System.out.println("isSubjectZukgitCommand 3  clearFlagStr = " + clearFlagStr);

                return true;
            } else {
                if (clearFlagStr.contains("(") && clearFlagStr.contains(")") &&
                        clearFlagStr.indexOf("(") == clearFlagStr.lastIndexOf("(") &&
                        clearFlagStr.indexOf(")") == clearFlagStr.lastIndexOf(")")) {
                    // 确保 只有一个小括号
                    // 【zukgitcommand(shutdown -p)】
                    System.out.println("isSubjectZukgitCommand 5  clearFlagStr = " + clearFlagStr);
                    return true;
                }
                System.out.println("isSubjectZukgitCommand 6  clearFlagStr = " + clearFlagStr);
            }
        }
        System.out.println("isSubjectZukgitCommand   End  flag = " + flag);
        return flag;
    }


    // winkey win+D win+E ctrl+shift win+E+PATH mousekey

    static boolean isSubjectWinKey_MouseKey_URL(String subJect) {
        boolean flag = false;
        if (subJect == null || "".equals(subJect)) {
            return flag;
        }
/*        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {

            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();

            // https://raw.githubusercontent.com/ZukGit/WordPress_MD_Git/master/tool/C9_mdRevert.bat

            //    // winkey win+D win+E ctrl+shift win+E+PATH mousekey

            // winkey_win
            if(clearFlagStr.startsWith("winkey") || clearFlagStr.startsWith("mousekey")){
                flag = true;
            }


        }*/

        if(subJect.startsWith("winkey") || subJect.startsWith("mousekey")){
            flag = true;
        }

        return flag;
    }


    // 【zukgitcommand41】
    // // 是否是数字类型的 命令
    static boolean isSubjectZukgitCommand_Url(String subJect) {
        boolean flag = false;
        if (subJect == null || "".equals(subJect)) {
            return flag;
        }
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {

            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();

            // https://raw.githubusercontent.com/ZukGit/WordPress_MD_Git/master/tool/C9_mdRevert.bat

            boolean isBatUrl = clearFlagStr.endsWith(".bat");
            boolean isShUrl = clearFlagStr.endsWith(".sh");
            boolean isValidUrl = isValidUrl(clearFlagStr);


            //  如果当前的路径是URL 并且能ping 同
            if (isBatUrl && isValidUrl) { // 去除 flag 标识 如果是 数值  那么返回true
                // 【zukgitcommand41】
                System.out.println(".bat  WIndows下使用");
                return true;
            }
            if (isShUrl && isValidUrl) {
                System.out.println(".sh Linux MacOS 下使用");
                return true;
            }

        }
        return flag;
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


    // 【zukgitcommand41】
    // // 是否是数字类型的 命令
    static boolean isSubjectZukgitCommand_Number(String subJect) {
        boolean flag = false;
        if (subJect == null || "".equals(subJect)) {
            return flag;
        }
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {

            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();
            if (isNumeric(clearFlagStr)) { // 去除 flag 标识 如果是 数值  那么返回true
                // 【zukgitcommand41】
                return true;
            }
        }
        return flag;
    }


    static String getSubjectZukgitCommand_Number(String subJect) {
        if (subJect == null || "".equals(subJect)) {
            return null;
        }
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {

            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();
            if (isNumeric(clearFlagStr)) { // 去除 flag 标识 如果是 数值  那么返回true
                // 【zukgitcommand41】
                return clearFlagStr;
            }
        }
        return null;
    }


    // 【zukgitcommand(shutdown -p)】   // 是否是可执行命令的 类型
    static boolean isSubjectZukgitCommand_Execute(String subJect) {
        boolean flag = false;

        if (subJect == null || "".equals(subJect)) {
            return flag;
        }
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {
            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();
            if (clearFlagStr.contains("(") && clearFlagStr.contains(")") &&
                    clearFlagStr.indexOf("(") == clearFlagStr.lastIndexOf("(") &&
                    clearFlagStr.indexOf(")") == clearFlagStr.lastIndexOf(")")) {
                // 确保 只有一个小括号
                // 【zukgitcommand(shutdown -p)】
                return true;
            }
        }
        return flag;
    }

    // 【zukgitcommand(shutdown -p)】   // 是否是可执行命令的 类型
    static String getSubjectZukgitCommand_Execute(String subJect) {

        if (subJect == null || "".equals(subJect)) {
            return null;
        }
        if (subJect.contains("【") && subJect.contains("】") && subJect.contains(CommandFlagStr)) {
            String clearFlagStr = subJect.replace("【", "");
            clearFlagStr = clearFlagStr.replace("】", "");
            clearFlagStr = clearFlagStr.replace(CommandFlagStr, "");
            clearFlagStr = clearFlagStr.trim();
            if (clearFlagStr.contains("(") && clearFlagStr.contains(")") &&
                    clearFlagStr.indexOf("(") == clearFlagStr.lastIndexOf("(") &&
                    clearFlagStr.indexOf(")") == clearFlagStr.lastIndexOf(")")) {
                // 确保 只有一个小括号
                // 【zukgitcommand(shutdown -p)】
                clearFlagStr = clearFlagStr.replace("(", "");
                clearFlagStr = clearFlagStr.replace(")", "");
                return clearFlagStr;
            }
        }
        return null;
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    // 【zukgitcommand(winkey_win+d_win+e_mousekey_200+133_winkey_win+r)】 数字实现方式
    static void operationWinKey_MouseKey(String command) {

        ArrayList<ArrayList<Integer>>  keycodeList = new ArrayList<ArrayList<Integer>>();

        boolean isWinKey = true;   // true---winkey   false----nmousekey

        if(command.startsWith("winkey")){
            isWinKey = true;
        }else if(command.startsWith("mousekey")){
            isWinKey = false;
        }
        String curKeyStr = command;
        String curOperaionItem = "";  // win+d
        while(curKeyStr != null && curOperaionItem != null){  // 把 curKeyStr一直往前截取

            System.out.println(" curOperaionItem = "+ curOperaionItem + "  curKeyStr="+curKeyStr);
            if(isWinKey){   // winkey
                curOperaionItem = getNextWinKeyItem(curKeyStr);  //  获取第一个_下划线之前的内容
                System.out.println("curOperaionItem = "+curOperaionItem);
                if(curOperaionItem == null){
                    continue;
                }

                if("winkey".equals(curOperaionItem)){
                    isWinKey = true;
                    curKeyStr =    getNextWinKey(curKeyStr);
                    continue;
                }else if("mousekey".equals(curOperaionItem)){
                    isWinKey = false;
                    curKeyStr =    getNextWinKey(curKeyStr);
                    continue;
                }

                //  检查当前的输入的字符串是否包含 +
                //  winkey_f_f_f   winkey_ffff    效果是一样的
                // winkey_win+e;

                // 当前就是 WInKeyAA
                String[] operaionKey = curOperaionItem.split("\\+");
                //
                ArrayList<Integer> curItemOperationList = new   ArrayList<Integer>();
                // win+e  win 和 e
                //  fffff  这里就只是一个 fffff了 它又无法解析为一个完成的拼音
                // winkey_enter_fffff_enter_hhhhh
                for (int i = 0; i < operaionKey.length; i++) {  // win+e  win 和 e
                    String keyCode = operaionKey[i];
                    System.out.println("keyCode = "+keyCode);
                    ArrayList<Integer> curOperationCode =  calCharKeyCode(keyCode);

                    // 如果解析的是空的话 并且 keyCode 的长度大于1 的话 那么认为是输入字符串
                    if(curOperationCode == null && keyCode.length() > 1){
                        curOperationCode = new ArrayList<Integer>();
                        for (int j = 0; j < keyCode.length(); j++) {
                            String oneStr = keyCode.substring(j,j+1);
                            ArrayList<Integer> oneStrOperationCode =  calCharKeyCode(oneStr);
                            curOperationCode.addAll(oneStrOperationCode);
                        }
                    }
                    keycodeList.add(curOperationCode);
                    curItemOperationList.addAll(curOperationCode);
                    //keyPress
//keyPressWithAlt
//keyPressWithWin
//keyPressWithShift
                }

                for (int i = 0; i < curItemOperationList.size(); i++) {
                    System.out.println("curItemOperationList ["+i+"] = "+ curItemOperationList.get(i));
                }
                OperationWinKey(curItemOperationList);   // 执行 win+d 这样的操作

                System.out.println("当前的字符串: curKeyStr = "+ curKeyStr);
                curKeyStr =    getNextWinKey(curKeyStr);  // 走往下一步
                System.out.println("下一步的字符串: curKeyStr = "+ curKeyStr);
            }else{  // mousekey_300+233_400+322

                curOperaionItem = getNextWinKeyItem(curKeyStr);  //  获取第一个_下划线之前的内容
                System.out.println("curOperaionItem = "+curOperaionItem);
                if(curOperaionItem == null){
                    continue;
                }

                if("winkey".equals(curOperaionItem)){
                    isWinKey = true;
                    curKeyStr =    getNextWinKey(curKeyStr);
                    continue;
                }else if("mousekey".equals(curOperaionItem)){
                    isWinKey = false;
                    curKeyStr =    getNextWinKey(curKeyStr);
                    continue;
                }

                String[] operaionKey = curOperaionItem.split("\\+");
                if(operaionKey.length != 2){
                    curKeyStr =    getNextWinKey(curKeyStr);  // 走往下一步
                    continue;
                }

                String xStr = operaionKey[0];
                String yStr = operaionKey[1];

                if(!isNumeric(xStr) || !isNumeric(yStr)){
                    curKeyStr =    getNextWinKey(curKeyStr);  // 走往下一步
                    continue;
                }
                int x_position = Integer.parseInt(xStr);
                int y_position = Integer.parseInt(yStr);

                OperationMouseKey(x_position,y_position );   // 执行 win+d 这样的操作

//                OperationWinKey(curItemOperationList);   // 执行 win+d 这样的操作
                curKeyStr =    getNextWinKey(curKeyStr);  // 走往下一步

            }




        }




    }

//keyPress
//keyPressWithAlt
//keyPressWithWin
//keyPressWithShift
// keyPressWithCtrl


    static  void OperationMouseKey(int x , int y){
        try {
            mouseMove(new Robot(),x,y);
        }catch (Exception e){
            System.out.println("OperationMouseKey Exception = "+e.toString());
        }
    }


    static  void OperationWinKey(ArrayList<Integer> winKeyList){
        int firstKeyCode = winKeyList.get(0);
        ArrayList<Integer> restCodeList = new   ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append(firstKeyCode+"+");
        for (int i = 1; i < winKeyList.size() ; i++) {
            restCodeList.add(winKeyList.get(i));
            sb.append(winKeyList.get(i)+"+");
        }
        try {
            System.out.println("firstKeyCode = "+ firstKeyCode);
            for (int i = 0; i < restCodeList.size(); i++) {
                System.out.println("restCodeList["+i+"] = "+ restCodeList.get(i));
            }
            if(firstKeyCode == KeyEvent.VK_CONTROL){
                System.out.println("KeyEvent.VK_CONTROL = "+ KeyEvent.VK_CONTROL);
                keyPressWithCtrl(new Robot(),restCodeList);
            }else if(firstKeyCode == KeyEvent.VK_WINDOWS){
                keyPressWithWin(new Robot(),restCodeList);
                System.out.println("KeyEvent.VK_WINDOWS = "+ KeyEvent.VK_WINDOWS);
            }else if(firstKeyCode == KeyEvent.VK_ALT){
                keyPressWithAlt(new Robot(),restCodeList);
            }else if(firstKeyCode == KeyEvent.VK_SHIFT){
                keyPressWithShift(new Robot(),restCodeList);
                System.out.println("KeyEvent.VK_SHIFT = "+ KeyEvent.VK_SHIFT);
            }else{
                ArrayList<Integer> pressCommonKeyList = new ArrayList<Integer>();
                pressCommonKeyList.add(firstKeyCode);
                pressCommonKeyList.addAll(restCodeList);

                //  每次模拟 点击输入字符串 那么都模拟 enter 一下
//               pressCommonKeyList.add(KeyEvent.VK_ENTER);
                keyPress(new Robot(),pressCommonKeyList);
                System.out.println("KeyEvent.pressCommonKeyList = pressCommonKeyList ");
            }
            System.out.println("OperationWinKey End!");
        } catch (Exception e){
            System.out.println("OperationWinKey 函数异常! ");
        }
        System.out.println(" 当前 OperationWinKey 操作的命令为:" + sb.toString());
    }

    // winkey_win+d_win+e_mousekey_200+133_winkey_win+r 变为 win+d_win+e_mousekey_200+133_winkey_win+r
    static String getNextWinKey(String curKeyStr){
        if(curKeyStr == null){
            return null;
        }
        //  如果命令不包含 _ 了 那么返回 null 代表结束
        if(!curKeyStr.contains("_")){
            return null;
        }
        int firstIndex = curKeyStr.indexOf("_");
        String nextKeyStr = curKeyStr.substring(firstIndex+1);
        return nextKeyStr;
    }
    //  获取第一个_下划线之前的内容
    // 【zukgitcommand(winkey_win+d_win+e_mousekey_200+133_winkey_win+r)】 数字实现方式
    static String getNextWinKeyItem(String commandStr){
        if(commandStr == null){
            return null;
        }

        if(!commandStr.contains("_")){  //  如果没有下划线了 那么返回全部自身
            return commandStr;
        }
        int firstIndex = commandStr.indexOf("_");
        String operationItem = commandStr.substring(0,firstIndex);
        return operationItem;
    }

    // 是否似乎自定义的命令
    static boolean isZukgitCustomMethod(String command) {
        boolean customMethod = false;
        switch (command) {
            case "openmobilewifi":
                customMethod = true;
                openMobileWifi();
                break;

            default:
                customMethod = false;
        }


        return customMethod;
    }

    class TaskThread extends Thread {
        Message mReceivedMessage;
        String mReceiveSubJect;  // 接收到的主题字符串
        Date mThreadDate;    // 接收到消息的日期
        Date mReplyDate;  //  对邮件进行回复的日期
        boolean isOnlyNumber;   // 是否是数值类型的命令
        String numberOfCommandStr;  // 如果是数字类型的命令 那么这个保存为 String 值
        int numberOfCommand;  // 如果是数字类型的命令 那么这个保存为 int 值
        InternetAddress[] replyEmailArr;

        String executeFileName; // 可执行文件的名称   // mail_win_bin 下的文件名称 1对1
        // Desktop/bin/mail_win_bin/xxxx11.sh
        String executeFilePath; // 可执行文件的目录  // Desktop/bin/mail_win_bin/xxxx11.bat

        String executeOfCommandStr; //  isOnlyNumber 为false时  保存的在 主题中的 可执行命令

        boolean isExecute;  // 当前的任务是否有执行
        boolean isExecuteOk;  // 当前的任务是否执行成功


        TaskThread(Message xReceivedMessage, String subJect) {
            this.mReceivedMessage = xReceivedMessage;
            mReceiveSubJect = subJect;
            isExecute = false;
            isExecuteOk = false;
            String replyEmailAddressStr = "";
            try {
                String[] replyEmailAddress = xReceivedMessage.getHeader("Return-Path");

                if (replyEmailAddress != null && replyEmailAddress.length > 0) {
                    replyEmailAddressStr = replyEmailAddress[0];
                }

                if (replyEmailAddressStr != null && !"".equals(replyEmailAddressStr)) {
                    replyEmailArr = InternetAddress.parse(replyEmailAddressStr);
                }

            } catch (Exception e) {
                System.out.println("TaskThread->TaskThread  Exception = " + e.toString());
            }


            mThreadDate = new Date();
            isOnlyNumber = isSubjectZukgitCommand_Number(mReceiveSubJect);
            if (isOnlyNumber) {
                numberOfCommandStr = getSubjectZukgitCommand_Number(mReceiveSubJect);
                numberOfCommand = Integer.parseInt(numberOfCommandStr);
            } else {
                if (isSubjectZukgitCommand_Execute(mReceiveSubJect)) {
                    executeOfCommandStr = getSubjectZukgitCommand_Execute(mReceiveSubJect);
                }

            }
            System.out.println("replyEmailAddressStr = " + replyEmailAddressStr);
        }

        TaskThread(String subJect) {
            mReceiveSubJect = subJect;
            isExecute = false;
            isExecuteOk = false;

            mThreadDate = new Date();
            isOnlyNumber = isSubjectZukgitCommand_Number(mReceiveSubJect);
            if (isOnlyNumber) {
                numberOfCommandStr = getSubjectZukgitCommand_Number(mReceiveSubJect);
                numberOfCommand = Integer.parseInt(numberOfCommandStr);
            } else {
                if (isSubjectZukgitCommand_Execute(mReceiveSubJect)) {
                    executeOfCommandStr = getSubjectZukgitCommand_Execute(mReceiveSubJect);
                }
            }
        }

        @Override
        public void run() {
            System.out.println("TaskThread Begin ！");
            isExecute = true;
            String result = "";

//1.自定义数字命令  【zukgitcommand1】 【zukgitcommand2】【zukgitcommand9】....【zukgitcommand99】
//2. 执行网络脚本命令 【zukgitcommand(https://xxxx.bat)】 【zukgitcommand(https://xxxx.sh)】
//3. 模拟按键  模拟鼠标 模拟点击 命令 【zukgitcommand(winkey_win+d_win+e_mousekey_200+133_winkey_win+r)】
//4. 自定义命令 【zukgitcommand(openmobilewifi)】
//5. 系统命令 【zukgitcommand(shutdown -p)】


            if (isOnlyNumber) {
                System.out.println("数字形式的命令还没实现!");
                // 【zukgitcommand1】 数字实现方式    还未实现
            } else if (isSubjectZukgitCommand_Url(executeOfCommandStr)) {
                // 把 远程的 .bat 或者 .sh 下载到本地然后执行
                // 【zukgitcommand(https://raw.githubusercontent.com/ZukGit/WordPress_MD_Git/master/tool/C9_mdRevert.bat)】 数字实现方式

            } else if(isSubjectWinKey_MouseKey_URL(executeOfCommandStr)){
                //  鼠标 按钮 的 一些操作  winkey_按钮组合  mousekey_坐标组合
                // 【zukgitcommand(winkey_win+d_win+e_mousekey_200+133_winkey_win+r)】 数字实现方式
                System.out.println("winkey_win 命令: "+executeOfCommandStr);
                operationWinKey_MouseKey(executeOfCommandStr);

            } else {
                // 本地的一些命令
                System.out.println("执行主题命令: " + executeOfCommandStr);

                // 执行 自定义一些命令  如  openmobilewifi
                if (!isZukgitCustomMethod(executeOfCommandStr)) {  // 自定义命令 openmobilewifi

                    // 系统能识别的命令  // calc mspaint shutdown -p
                    result = execCMD(executeOfCommandStr);
                } else {

                    result = "执行自定义命令" + executeOfCommandStr + "--结果未知";
                }
                System.out.println("执行结果 result = " + result);
                if (!result.contains("异常")) {
                    isExecuteOk = true;
                }
                if (isExecuteOk) {
                    System.out.println("执行命令 " + executeOfCommandStr + " 成功!");
                } else {
                    System.out.println("执行命令 " + executeOfCommandStr + " 失败!");
                }
            }

            // 执行结果反馈
            executeReplyInfo(mReceivedMessage, result);
            System.out.println("TaskThread End ！");
            super.run();
        }


        File getPCScreenFile(String dirDateStampName) {

            // H7_Email_WorkPlace
            //   把截图放到 C:\Users\zhuzj5\Desktop\zbin\H7_Email_WorkPlace\yyMMdd_hhmmss 文件夹中

            String H7_WorkPlace_TaskDir = H7_WorkPlace + File.separator + dirDateStampName;

            File taskDirFile = new File(H7_WorkPlace_TaskDir);
            if (!taskDirFile.exists()) {
                taskDirFile.mkdirs();
            }

            File imageFIle = null;
            String imageName = "pc_" + dirDateStampName + ".png";
            String imageAbsPath = H7_WorkPlace_TaskDir + File.separator + imageName;

            imageFIle = new File(imageAbsPath);

            if (!imageFIle.exists()) {
                try {
                    imageFIle.createNewFile();
                } catch (Exception e) {

                }
            }

            //----------------Image Begin---------
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int height = gd.getDisplayMode().getHeight();
            int width = gd.getDisplayMode().getWidth();
            Rectangle screenRect = new Rectangle(0,0,width,height);
            ScreenUtil.captureScreen(screenRect,imageFIle);
//----------------Image End---------

            System.out.println("PC截图路径: " + imageAbsPath);
            if (!imageFIle.exists() || imageFIle.length() < 100) {
                return null;
            }
            File newImage_XY_File =   paintXYFromImageFile(imageFIle);
            // adb shell screencap -p /sdcard/Pictures/H7_%CURRENT_DATE_TIME_STAMP%.png
            // adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
            return newImage_XY_File;
        }

        File getMobileScreenFile(String dirDateStampName) {

            // H7_Email_WorkPlace
            //   把截图放到 C:\Users\zhuzj5\Desktop\zbin\H7_Email_WorkPlace\yyMMdd_hhmmss 文件夹中

            String H7_WorkPlace_TaskDir = H7_WorkPlace + File.separator + dirDateStampName;
            File taskDirFile = new File(H7_WorkPlace_TaskDir);
            if (!taskDirFile.exists()) {
                taskDirFile.mkdirs();
            }

            File imageFIle = null;
            String imageName = "mobile_" + dirDateStampName + ".png";
            String imageAbsPath = H7_WorkPlace_TaskDir + File.separator + imageName;


            // 亮屏 并解锁
            execCMD("adb kill-server");
            execCMD("adb shell svc power stayon true ");
            execCMD(" adb shell input swipe 200 1500 200 100");

            String takePictureCommad1 = "adb shell screencap -p /sdcard/Pictures/" + imageName;


            String pullPictureCommand2 = "adb pull /sdcard/Pictures/" + imageName + " " + imageAbsPath;

            execCMD(takePictureCommad1);

            execCMD(pullPictureCommand2);


            System.out.println(" Mobile-命令: " + takePictureCommad1 + " && " + pullPictureCommand2);
            imageFIle = new File(imageAbsPath);
            if (!imageFIle.exists()) {
                return null;
            }

            File newImage_XY_File =   paintXYFromImageFile(imageFIle);  // 对当前图像进行 x y 坐标的绘制
            // adb shell screencap -p /sdcard/Pictures/H7_%CURRENT_DATE_TIME_STAMP%.png
            // adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
            return newImage_XY_File;
        }


        boolean isImageFile(File imageFIle){
            boolean  flag = false;
            if(imageFIle.getName().endsWith(".jpg") ||
                    imageFIle.getName().endsWith(".Jpg") ||
                    imageFIle.getName().endsWith(".JPG") ||
                    imageFIle.getName().endsWith(".png") ||
                    imageFIle.getName().endsWith(".Png") ||
                    imageFIle.getName().endsWith(".PNG")
            ){
                flag = true;
            }
            return flag;
        }



        File paintXYFromImageFile(File imageFile){
            if(!imageFile.exists() || imageFile.length() < 10 || !isImageFile(imageFile)){
                return null ;
            }

            String originName = getFileNameNoPoint(imageFile.getName());
            String imageType = getFileTypeNoPoint(imageFile.getName());
            String newImageFileNameStr = originName+"_xy"+"."+imageType;
            File newImageFile = new File(imageFile.getParentFile().getAbsolutePath()+File.separator+newImageFileNameStr);



            try {
                if(!newImageFile.exists()){
                    newImageFile.createNewFile();
                }
                BufferedImage bi =    ImageIO.read(imageFile);
                int height = bi.getHeight();
                int width = bi.getWidth();

                int up_line_num = width/10; // 竖线的数量
                int down_line_num = height/10;  // 横线的数量

                Graphics2D g2 = (Graphics2D) bi.getGraphics();
                g2.setColor(Color.RED);//设置颜色
                g2.setStroke(new BasicStroke(1.5f));  // 直线粗细
                Font f =  new Font("宋体",Font.BOLD,20);
                g2.setFont(f); //设置字体:字体、字号、大小
                for (int i = 0; i <down_line_num ; i++) { // 横线的数量
                    int down_origin_x_item = 0;
                    int down_origin_y_item =  100 * i + 100;
                    int down_new_x_item = width;
                    int down_new_y_item = down_origin_y_item;

                    int text_X = down_origin_x_item + 10;  //
                    int text_y = down_origin_y_item - 10;  //
                    g2.drawString(down_origin_y_item+"",text_X,text_y);
                    g2.drawLine(down_origin_x_item,down_origin_y_item,down_new_x_item,down_new_y_item);
                }


                for (int i = 0; i <up_line_num ; i++) { // 竖线的数量

                    int down_origin_x_item = 100 * i + 100;
                    int down_origin_y_item =  0;
                    int down_new_x_item = down_origin_x_item;
                    int down_new_y_item = height;
                    int text_X = down_origin_x_item - 50;  //
                    int text_y = down_origin_y_item + 20;
                    g2.drawString(down_origin_x_item+"",text_X,text_y);
                    g2.drawLine(down_origin_x_item,down_origin_y_item,down_new_x_item,down_new_y_item);
                }
                ImageIO.write(bi,imageType,new FileOutputStream(newImageFile));
                System.out.println("程序执行结束!");
            } catch (IOException e) {

                e.printStackTrace();
                System.out.println("异常="+e);
                return imageFile;  //  出现异常 那么就不操作 x y 坐标了
            }
            return newImageFile;
        }





        // 对接收到的邮件进行反馈
        void executeReplyInfo(Message receiveMessage, String exeResult) {
            System.out.println("发送回馈邮件 Begin ！");
            mReplyDate = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");

            String replyDateStr = df.format(mReplyDate); // 回复邮件日期
            String receiveDateStr = df.format(mThreadDate);  // 接收到邮件的日期

            // 回复主题
            String replySubject = mReceiveSubJect + "[recvDate:" + receiveDateStr + "]" + "[sendDate:" + replyDateStr + "]";

            // 截图   手机的截图  以及  电脑屏幕的截图   把 发送的日期 作为 文件夹的名称

            ArrayList<File> imageList = new ArrayList<File>();
            File mobileImageFile = getMobileScreenFile(replyDateStr);
            if (mobileImageFile != null) {
                imageList.add(mobileImageFile);
            }

            File pcImageFile = getPCScreenFile(replyDateStr);
            if (pcImageFile != null) {
                imageList.add(pcImageFile);
            }


            //  replyEmailArr   回复人的邮箱
            ReplyEmail repEmail = new ReplyEmail(replyEmailArr, replySubject, exeResult, imageList, null);
            repEmail.send();   // 发送邮件

            System.out.println("发送回馈邮件 End ！");
            // 回复的地址:


            // 1.对电脑进行截图
            //  2. 对手机屏幕进行截图
            //  3. 对 exe执行的结果进行回馈
            //  4. ReceiveData时间 [mThreadDate] 和 发送这个邮件的时间戳[mReplyDate]


        }

        // keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行   单键
        //    keyPressWithAlt(robot, KeyEvent.VK_SPACE);  //按下 alt+空格    双键
        //  keyPressWithCtrl(robot,KeyEvent.VK_A); //按下 ctrl+A 全选  双键
        // keyPressWithShift(Robot r, int key)  shift +  键
        // keyPressString(robot, "大家好，我是一个小机器人，我有很多本领呢 ！");    焦点光标输入字符串
        //



    }

    // winkey win+D win+E ctrl+shift win+E+PATH
    // startchrome  // 打开谷歌浏览器


/*
    public void type(CharSequence characters) {
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }
    }
*/

//    mousekey_331+324
//            robot.mouseMove(x,y);   //  鼠标移动 事件
//            robot.delay(1000);

//            robot.mousePress(InputEvent.BUTTON1_MASK);   // 鼠标点击事件
//            robot.delay(300);
//            robot.mouseRelease(InputEvent.BUTTON1_MASK);


    // 鼠标滚动事件 通过 pgup  pgdn  home  end  上下左右实现



    //   按键事件 和  鼠标事件 在同一个 一句中


    // winkey_win+d_win+e_winkey_f_win+f_mousekey_331+324_winkey_ffff_ctrl+sa


    //  输入 win+e    那么分别获得 win键 和 e 键的 keycode 然后执行它
    static ArrayList<Integer> calCharKeyCode(String character) {
        switch (character) {
            case "backspace": return checkCodeList(KeyEvent.VK_BACK_SPACE);
            case "capslock": return checkCodeList(KeyEvent.VK_CAPS_LOCK);
            case "pageup": return checkCodeList(KeyEvent.VK_PAGE_UP);
            case "pagedown": return checkCodeList(KeyEvent.VK_PAGE_DOWN);
            case "delete": return checkCodeList(KeyEvent.VK_DELETE);
            case "insert": return checkCodeList(KeyEvent.VK_INSERT);
            case "blank": return checkCodeList(KeyEvent.VK_SPACE);
            case "enter": return checkCodeList(KeyEvent.VK_ENTER);
            case "prtsc": return checkCodeList( KeyEvent.VK_PRINTSCREEN);
            case "shift": return checkCodeList(KeyEvent.VK_SHIFT);
            case "ctrl": return checkCodeList(KeyEvent.VK_CONTROL);
            case "home": return checkCodeList(KeyEvent.VK_HOME);
            case "pgup": return checkCodeList(KeyEvent.VK_PAGE_UP);
            case "pgdn": return checkCodeList(KeyEvent.VK_PAGE_DOWN);
            case "end": return checkCodeList(KeyEvent.VK_END);
            case "tab": return checkCodeList(KeyEvent.VK_TAB);
            case "ctr": return checkCodeList(KeyEvent.VK_CONTROL);
            case "win": return checkCodeList(KeyEvent.VK_WINDOWS);
            case "Alt": return checkCodeList(KeyEvent.VK_ALT);
            case "alt": return checkCodeList(KeyEvent.VK_ALT);
            case "esc": return checkCodeList(KeyEvent.VK_ESCAPE);
            case "f1": return checkCodeList(KeyEvent.VK_F1);
            case "f2": return checkCodeList(KeyEvent.VK_F2);
            case "f3": return checkCodeList(KeyEvent.VK_F3);
            case "f4": return checkCodeList(KeyEvent.VK_F4);
            case "f5": return checkCodeList(KeyEvent.VK_F5);
            case "f6": return checkCodeList(KeyEvent.VK_F6);
            case "f7": return checkCodeList(KeyEvent.VK_F7);
            case "f8": return checkCodeList(KeyEvent.VK_F8);
            case "f9": return checkCodeList(KeyEvent.VK_F9);
            case "f10": return checkCodeList(KeyEvent.VK_F10);
            case "f11": return checkCodeList(KeyEvent.VK_F11);
            case "f12": return checkCodeList(KeyEvent.VK_F12);
            case "F1": return checkCodeList(KeyEvent.VK_F1);
            case "F2": return checkCodeList(KeyEvent.VK_F2);
            case "F3": return checkCodeList(KeyEvent.VK_F3);
            case "F4": return checkCodeList(KeyEvent.VK_F4);
            case "F5": return checkCodeList(KeyEvent.VK_F5);
            case "F6": return checkCodeList(KeyEvent.VK_F6);
            case "F7": return checkCodeList(KeyEvent.VK_F7);
            case "F8": return checkCodeList(KeyEvent.VK_F8);
            case "F9": return checkCodeList(KeyEvent.VK_F9);
            case "F10": return checkCodeList(KeyEvent.VK_F10);
            case "F11": return checkCodeList(KeyEvent.VK_F11);
            case "F12": return checkCodeList(KeyEvent.VK_F12);
            case "↑": return checkCodeList(KeyEvent.VK_UP);
            case "↓": return checkCodeList(KeyEvent.VK_DOWN);
            case "←": return checkCodeList(KeyEvent.VK_LEFT);
            case "→": return checkCodeList(KeyEvent.VK_RIGHT);
            case "a": return checkCodeList(KeyEvent.VK_A);
            case "b": return checkCodeList(KeyEvent.VK_B);
            case "c": return checkCodeList(KeyEvent.VK_C);
            case "d": return checkCodeList(KeyEvent.VK_D);
            case "e": return checkCodeList(KeyEvent.VK_E);
            case "f": return checkCodeList(KeyEvent.VK_F);
            case "g": return checkCodeList(KeyEvent.VK_G);
            case "h": return checkCodeList(KeyEvent.VK_H);
            case "i": return checkCodeList(KeyEvent.VK_I);
            case "j": return checkCodeList(KeyEvent.VK_J);
            case "k": return checkCodeList(KeyEvent.VK_K);
            case "l": return checkCodeList(KeyEvent.VK_L);
            case "m": return checkCodeList(KeyEvent.VK_M);
            case "n": return checkCodeList(KeyEvent.VK_N);
            case "o": return checkCodeList(KeyEvent.VK_O);
            case "p": return checkCodeList(KeyEvent.VK_P);
            case "q": return checkCodeList(KeyEvent.VK_Q);
            case "r": return checkCodeList(KeyEvent.VK_R);
            case "s": return checkCodeList(KeyEvent.VK_S);
            case "t": return checkCodeList(KeyEvent.VK_T);
            case "u": return checkCodeList(KeyEvent.VK_U);
            case "v": return checkCodeList(KeyEvent.VK_V);
            case "w": return checkCodeList(KeyEvent.VK_W);
            case "x": return checkCodeList(KeyEvent.VK_X);
            case "y": return checkCodeList(KeyEvent.VK_Y);
            case "z": return checkCodeList(KeyEvent.VK_Z);
            case "A": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
            case "B": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
            case "C": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
            case "D": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
            case "E": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
            case "F": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
            case "G": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
            case "H": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
            case "I": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
            case "J": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
            case "K": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_K);
            case "L": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_L);
            case "M": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_M);
            case "N": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_N);
            case "O": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
            case "P": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_P);
            case "Q": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_Q);
            case "R": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
            case "S": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_S);
            case "T": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_T);
            case "U": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
            case "V": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_V);
            case "W": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_W);
            case "X": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_X);
            case "Y": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_Y);
            case "Z": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_Z);
            case "`": return checkCodeList(KeyEvent.VK_BACK_QUOTE);
            case "0": return checkCodeList(KeyEvent.VK_0);
            case "1": return checkCodeList(KeyEvent.VK_1);
            case "2": return checkCodeList(KeyEvent.VK_2);
            case "3": return checkCodeList(KeyEvent.VK_3);
            case "4": return checkCodeList(KeyEvent.VK_4);
            case "5": return checkCodeList(KeyEvent.VK_5);
            case "6": return checkCodeList(KeyEvent.VK_6);
            case "7": return checkCodeList(KeyEvent.VK_7);
            case "8": return checkCodeList(KeyEvent.VK_8);
            case "9": return checkCodeList(KeyEvent.VK_9);
            case "-": return checkCodeList(KeyEvent.VK_MINUS);
            case "=": return checkCodeList(KeyEvent.VK_EQUALS);
            case "~": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE);
            case "!": return checkCodeList(KeyEvent.VK_EXCLAMATION_MARK);
            case "@": return checkCodeList(KeyEvent.VK_AT);
            case "#": return checkCodeList(KeyEvent.VK_NUMBER_SIGN);
            case "$": return checkCodeList(KeyEvent.VK_DOLLAR);
            case "%": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
            case "^": return checkCodeList(KeyEvent.VK_CIRCUMFLEX);
            case "&": return checkCodeList(KeyEvent.VK_AMPERSAND);
            case "*": return checkCodeList(KeyEvent.VK_ASTERISK);
            case "(": return checkCodeList(KeyEvent.VK_LEFT_PARENTHESIS);
            case ")": return checkCodeList(KeyEvent.VK_RIGHT_PARENTHESIS);
            case "_": return checkCodeList(KeyEvent.VK_UNDERSCORE);
            case "+": return checkCodeList(KeyEvent.VK_PLUS);
            case "\t": return checkCodeList(KeyEvent.VK_TAB);
            case "\n": return checkCodeList(KeyEvent.VK_ENTER);
            case "[": return checkCodeList(KeyEvent.VK_OPEN_BRACKET);
            case "]": return checkCodeList(KeyEvent.VK_CLOSE_BRACKET);
            case "\\": return checkCodeList(KeyEvent.VK_BACK_SLASH);
            case "{": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
            case "}": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
            case "|": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
            case ";": return checkCodeList(KeyEvent.VK_SEMICOLON);
            case ":": return checkCodeList(KeyEvent.VK_COLON);
            case "\'": return checkCodeList(KeyEvent.VK_QUOTE);  // 单引号
            case "\"": return checkCodeList(KeyEvent.VK_QUOTEDBL);  // 双引号
            case ",": return checkCodeList(KeyEvent.VK_COMMA);
            case "<": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA);
            case ".": return checkCodeList(KeyEvent.VK_PERIOD);
            case ">": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD);
            case "/": return checkCodeList(KeyEvent.VK_SLASH);
            case "?": return checkCodeList(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH);

            case " ": return checkCodeList(KeyEvent.VK_SPACE);
            default:
                System.out.println("Cannot type character " + character);
        }
        return null;

    }

    static ArrayList<Integer> checkCodeList(int codeA){
        ArrayList<Integer>  keyCodeList = new ArrayList<Integer>();
        keyCodeList.add(codeA);
        return keyCodeList;

    }


    static ArrayList<Integer> checkCodeList(int codeA , int codeB){
        ArrayList<Integer>  keyCodeList = new ArrayList<Integer>();
        keyCodeList.add(codeA);
        keyCodeList.add(codeB);
        return keyCodeList;
    }



//keyPress
//keyPressWithAlt
//keyPressWithWin
//keyPressWithShift

    static void keyPressWithShift(Robot r, ArrayList<Integer> keyList) {
        r.keyPress(KeyEvent.VK_SHIFT);
        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            r.keyPress(key);
//            r.delay(100);
        }

        for (int i = keyList.size()-1; i < 0; i--) {
            int key = keyList.get(i);
            r.keyRelease(key);
//            r.delay(100);
        }

        r.delay(100);
        r.keyRelease(KeyEvent.VK_SHIFT);
        r.delay(100);
    }


    //    mousekey_331+324
//            robot.mouseMove(x,y);   //  鼠标移动 事件
//            robot.delay(1000);

    //            robot.mousePress(InputEvent.BUTTON1_MASK);   // 鼠标点击事件
//            robot.delay(300);
//            robot.mouseRelease(InputEvent.BUTTON1_MASK);

    public static void mouseMove( Robot robot,  int x , int y ) {
        // http://www.voidcn.com/article/p-uhttzeub-buk.html
        int mouseRunCount = 10;  // mouseMove 总是不能一次性运行到正确的位置 放到for循环中 会好一点
        while(mouseRunCount > 0){
            robot.mouseMove(x,y);   //  鼠标移动 事件
            mouseRunCount--;
        }

        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);   // 鼠标点击事件
        robot.delay(300);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        System.out.println("移动到新位置: x="+x + "y = "+y);
    }

    public static void keyPressWithWin(Robot r,  ArrayList<Integer> keyList) {
        r.keyPress(KeyEvent.VK_WINDOWS);
        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            r.keyPress(key);
//            r.delay(100);
        }

        for (int i = keyList.size()-1; i < 0; i--) {
            int key = keyList.get(i);
            r.keyRelease(key);
//            r.delay(100);
        }

        r.delay(100);
        r.keyRelease(KeyEvent.VK_WINDOWS);
        r.delay(100);
    }

    public static void keyPressWithCtrl(Robot r,  ArrayList<Integer> keyList) {
        r.keyPress(KeyEvent.VK_CONTROL);
        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            r.keyPress(key);
//            r.delay(100);
        }

        for (int i = keyList.size()-1; i < 0; i--) {
            int key = keyList.get(i);
            r.keyRelease(key);
//            r.delay(100);
        }
        r.delay(100);

        r.keyRelease(KeyEvent.VK_CONTROL);
        r.delay(100);
    }


    public static void keyPressWithAlt(Robot r,  ArrayList<Integer> keyList) {
        r.keyPress(KeyEvent.VK_ALT);
        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            r.keyPress(key);
//            r.delay(100);
        }

        for (int i = keyList.size()-1; i < 0; i--) {
            int key = keyList.get(i);
            r.keyRelease(key);
//            r.delay(100);
        }
        r.delay(100);

        r.keyRelease(KeyEvent.VK_ALT);
        r.delay(100);
    }

    public static void keyPress(Robot r,  ArrayList<Integer> keyList) {

        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            r.keyPress(key);
//            r.delay(100);
        }

        for (int i = keyList.size()-1; i < 0; i--) {
            int key = keyList.get(i);
            r.keyRelease(key);
//            r.delay(100);
        }
        r.delay(100);
        r.delay(100);
    }



    public static void keyPressString(Robot r, String str) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
        Transferable tText = new StringSelection(str);
        clip.setContents(tText, null); //设置剪切板内容
        ArrayList<Integer> keyCodeList = new        ArrayList<Integer>();
        keyCodeList.add(KeyEvent.VK_V);
        keyPressWithCtrl(r, keyCodeList);//粘贴
        r.delay(100);
    }


    class ReplyEmail {

        ReplyEmail(InternetAddress[] replyEmailList, String mReceiveSubJectText, String cEmailText, ArrayList<File> mImageList, ArrayList<File> mAttatchFileList) {

            to_receiverList = replyEmailList;
            cc_receiverList = null;
            bcc_receiverList = null;
            username = USER;
            password = PASSWORD;
            smtphost = SMTP_HOST;
            mReceiveSubJect = mReceiveSubJectText;
            emailText = cEmailText;
            imageFileList = mImageList;
            attachFileList = mAttatchFileList;
            hostPort = 465;

//            executeResult = cExecuteResult;
//            emailText = emailText+"\nExecuteResult:\n" + executeResult+"\n";

            properties = System.getProperties();//获得系统属性对象
            properties.put("mail.smtp.host", smtphost);//设置smtp主机
            properties.put("mail.smtp.auth", "true");
            gmailssl(properties);
            //获取邮件会话对象
            session = Session.getDefaultInstance(properties, null);
            //创建Mime邮件对象
            mimeMessage = new MimeMessage(session);
            multipart = new MimeMultipart();
            BodyPart bodyPart = new MimeBodyPart();

            StringBuilder imageStringBuilder = new StringBuilder();
            try {

                if (imageFileList != null && imageFileList.size() > 0) {
                    for (int i = 0; i < imageFileList.size(); i++) {
                        File mImageFile = imageFileList.get(i);
                        BodyPart img = new MimeBodyPart();
                        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(new FileInputStream(mImageFile.getAbsolutePath()), "application/octet-stream");
                        DataHandler imgDataHandler = new DataHandler(byteArrayDataSource);
                        img.setDataHandler(imgDataHandler);
                        String headerValue = "<" + mImageFile.getName() + ">";
                        img.setHeader("Content-ID", headerValue);
                        img.setFileName(mImageFile.getName());

                        BufferedImage bi =    ImageIO.read(mImageFile);
                        int height = bi.getHeight();
                        int width = bi.getWidth();
                        imageStringBuilder.append("<img src='cid:" + mImageFile.getName() + "' alt='picture' width='"+width+"px' height='"+height+"px' />");
                        multipart.addBodyPart(img);
                    }
                    emailText = emailText + " " + imageStringBuilder.toString();
                }


                if (mAttatchFileList != null && mAttatchFileList.size() > 0) {
                    for (int i = 0; i < mAttatchFileList.size(); i++) {
                        File attatchFileItem = mAttatchFileList.get(i);
                        FileDataSource fileDataSource = new FileDataSource(attatchFileItem);
                        BodyPart attatchBodyPart = new MimeBodyPart();
                        attatchBodyPart.setDataHandler(new DataHandler(fileDataSource));
                        attatchBodyPart.setFileName(fileDataSource.getName());
                        multipart.addBodyPart(attatchBodyPart);
                    }

                }


                bodyPart.setContent("" + emailText, "text/html;charset=utf-8");


                mimeMessage.setFrom(new InternetAddress(USER));
/*
                Address[] replyArr = new Address[replyEmailList.size()];
                for (int i = 0; i < replyEmailList.size(); i++) {
                    InternetAddress intAddress = replyEmailList.get(i);
                    replyArr[i] = intAddress;
                }
                */
                mimeMessage.setRecipients(Message.RecipientType.TO, replyEmailList);
//                mimeMessage.setRecipients(Message.RecipientType.CC,null);
//                mimeMessage.setRecipients(Message.RecipientType.BCC,null);
                mimeMessage.setSubject(mReceiveSubJect);


//                multipart.addBodyPart(img);
//                System.out.println("imgPart = "+ img.toString());


                multipart.addBodyPart(bodyPart);

                System.out.println("bodyPart = " + bodyPart.toString());
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("MessagingException at ReplyEmail() ");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception at ReplyEmail() ");
            }

        }


        public boolean send() {
            try {
                System.out.println("multipart= " + multipart);
                System.out.println("multipart.getCount() = " + multipart.getCount());
                //multipart放入message
                mimeMessage.setContent(multipart);

                mimeMessage.saveChanges();
                Session mailSession = Session.getInstance(properties, null);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(properties.getProperty("mail.smtp.host"), username, password);
                transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
//                if (cc_receiverList!=null){
//                    transport.sendMessage(mimeMessage,mimeMessage.getRecipients(Message.RecipientType.CC));
//                }

                System.out.println("邮件发送成功");
                transport.close();
                return true;
            } catch (MessagingException e) {
                System.out.println("邮件发送失败");
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 邮件添加附件
         *
         * @param file
         * @return
         */
        public boolean addFileAffix(String file) {
            String[] fileArray = file.split(",");

            for (int i = 0; i < fileArray.length; i++) {
                FileDataSource fileDataSource = new FileDataSource(fileArray[i]);
                try {
                    BodyPart bodyPart = new MimeBodyPart();
                    bodyPart.setDataHandler(new DataHandler(fileDataSource));
                    bodyPart.setFileName(fileDataSource.getName());
                    multipart.addBodyPart(bodyPart);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        /**
         * 邮件正文（带图片的）
         *
         * @param mailBody
         * @param imgFile
         * @return
         */
        public boolean setBodyWithImg(String mailBody, String imgFile) {
            BodyPart content = new MimeBodyPart();
            BodyPart img = new MimeBodyPart();
            try {
                multipart.addBodyPart(content);
                multipart.addBodyPart(img);

                ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(new FileInputStream(imgFile), "application/octet-stream");
//            DataHandler imgDataHandler = new DataHandler(new FileDataSource(imgFile));
                DataHandler imgDataHandler = new DataHandler(byteArrayDataSource);
                img.setDataHandler(imgDataHandler);
//            img.setContent
                String imgFilename = imgFile.substring(imgFile.lastIndexOf("/") + 1);//图片文件名
                //注意：Content-ID的属性值一定要加上<>，不能直接写文件名
                String headerValue = "<" + imgFilename + ">";
                img.setHeader("Content-ID", headerValue);
                //为图片设置文件名，有的邮箱会把html内嵌的图片也当成附件
                img.setFileName(imgFilename);
                //在html代码中要想显示刚才的图片名 src里不能直接写Content-ID的值，要用cid:这种方式
                mailBody += "<img src='cid:" + imgFilename + "' alt='picture' width='100px' height='100px' />,骚吗?";
                content.setContent("" + mailBody, "text/html;charset=utf-8");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

// 1. host 地址
// 2. host 端口
// 3. username  mail邮箱账号
//4. pwd  邮箱密码
//5. sender: 发送人邮箱  【 只能一个 】
//6.  to_receiver:   收件人    【 ArrayList<> 】 一个集合
// 7.  cc_receiver:  抄送收件人  【 ArrayList<> 】 一个集合
// 8. bcc_receiver:  密送收件人  【 ArrayList<> 】 一个集合
// 9.   主题字样显示  mReceiveSubJectText  ___ 原有主题+[ReceiveDate:][SendDate:]
// 10. String emailText ;  // 邮件内容  ArrayList<Stirng> 每句话的集合
//11. 邮件图片显示      ArrayList<File>   imageList ;  // 显示图片的集合
//12. attatch 附件文件的集合      ArrayList<File>   attatchFileList ;  // 附件文件的集合


        MimeMessage mimeMessage;//Mime邮件对象
        Session session;//邮件会话对象

        boolean needAuth = false;//smtp是否需要认证

        Multipart multipart;//Multipart对象 邮件内容 标题 附件等内容添加到这里面 然后生成MimeMessage对象
        Properties properties;//系统属性

        //        String executeResult;  // 邮件执行的结果字符串  就是回复的内容个所以 注释掉
        //  用户定义的属性
        //smtp认证的用户名和密码
        String username;
        String password;
        String smtphost;  // gamil 服务器
        int hostPort;  // gmail服务器端口 默认 465
        InternetAddress[] to_receiverList;  //  收件人列表
        ArrayList<InternetAddress> cc_receiverList;  //  抄送收件人列表
        ArrayList<InternetAddress> bcc_receiverList;  //  密送收件人列表
        String mReceiveSubJect;  // 主题
        String emailText;  // 邮件内容
        ArrayList<File> imageFileList;  // 图片文件内容
        ArrayList<File> attachFileList;  // 附件内容


// 1. host 地址
// 2. host 端口
// 3. username  mail邮箱账号
//4. pwd  邮箱密码
//5. sender: 发送人邮箱  【 只能一个 】
//6.  to_receiver:   收件人    【 ArrayList<> 】 一个集合
// 7.  cc_receiver:  抄送收件人  【 ArrayList<> 】 一个集合
// 8. bcc_receiver:  密送收件人  【 ArrayList<> 】 一个集合
// 9.   主题字样显示  mReceiveSubJectText  ___ 原有主题+[ReceiveDate:][SendDate:]
// 10. String emailText ;  // 邮件内容  ArrayList<Stirng> 每句话的集合
//11. 邮件图片显示      ArrayList<File>   imageList ;  // 显示图片的集合
//12. attatch 附件文件的集合      ArrayList<File>   attatchFileList ;  // 附件文件的集合


        /**
         * 构造方法
         *
         * @param smtp
         */
        public ReplyEmail(String smtp) {
            setSmtpHost(smtp);
            createMimeMessage();
        }


        /**
         * 创建MimeMessage邮件对象
         *
         * @return
         */
        public boolean createMimeMessage() {
            //获取邮件会话对象
            session = Session.getDefaultInstance(properties, null);
            //创建Mime邮件对象
            mimeMessage = new MimeMessage(session);
            multipart = new MimeMultipart();
            return true;
        }

        /**
         * 设置邮件发送服务器
         *
         * @param hostName
         */
        public void setSmtpHost(String hostName) {
            if (properties == null) {
                properties = System.getProperties();//获得系统属性对象
            }
            properties.put("mail.smtp.host", hostName);//设置smtp主机
        }

        /**
         * 设置smtp是否需要认证
         *
         * @param need
         */
        public void setNeedAuth(boolean need) {
            if (properties == null) {
                properties = System.getProperties();
            }
            if (need) {
                properties.put("mail.smtp.auth", "true");
            } else {
                properties.put("mail.smtp.auth", "false");
            }
        }

        /**
         * 发件人的用户名和密码 163的用户名就是邮箱的前缀
         *
         * @param username
         * @param password
         */
        public void setNamePassword(String username, String password) {
            this.username = username;
            this.password = password;
        }

        /**
         * 邮件主题
         *
         * @param subject
         * @return
         */
        public boolean setSubject(String subject) {
            try {
                mimeMessage.setSubject(subject);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 邮件正文
         *
         * @param mailBody
         * @return
         */
        public boolean setBody(String mailBody) {
            BodyPart bodyPart = new MimeBodyPart();
            try {
                bodyPart.setContent("" + mailBody, "text/html;charset=utf-8");
                multipart.addBodyPart(bodyPart);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 发件人邮箱
         *
         * @param from
         * @return
         */
        public boolean setFromSender(String from) {
            try {
                mimeMessage.setFrom(new InternetAddress(from));
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 收件人邮箱
         *
         * @param to
         * @return
         */
        public boolean setTo(String to) {
            if (to == null)
                return false;
            try {
                //电子邮件可以有三种类型的收件人，分别to、cc（carbon copy）和bcc（blind carbon copy），分别是收件人、抄送、密送
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 抄送人邮箱 字符串中逗号分开   CC 抄送
         *
         * @param copyto
         * @return
         */
        public boolean setCopyTo(String copyto) {
            if (copyto == null)
                return false;
            try {
                mimeMessage.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 密送人邮箱 字符串中逗号分开   BC 密送
         *
         * @param bccTo
         * @return
         */
        public boolean setBccTo(String bccTo) {
            if (bccTo == null)
                return false;
            try {
                mimeMessage.setRecipients(Message.RecipientType.BCC, (Address[]) InternetAddress.parse(bccTo));
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 发送
         *
         * @param copyto
         * @return
         */
        public boolean sendOut(String copyto) {
            try {
                //multipart放入message
                mimeMessage.setContent(multipart);
                mimeMessage.saveChanges();
                Session mailSession = Session.getInstance(properties, null);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(properties.getProperty("mail.smtp.host"), username, password);
                transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
                if (copyto != null) {
                    transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.CC));
                }
                System.out.println("邮件发送成功");
                transport.close();
                return true;
            } catch (MessagingException e) {
                System.out.println("邮件发送失败");
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 该方法调用上边定义的方法 选择性的组合 完成邮件发送
         * 普普通通的一对一发送
         *
         * @param smtp
         * @param from
         * @param to
         * @param subject
         * @param content
         * @param username
         * @param password
         * @return
         */
        public boolean send(String smtp, String from, String to, String subject,
                            String content, String username, String password) {
            ReplyEmail mail = new ReplyEmail(smtp);
            mail.setNeedAuth(true);//需要认证
            if (!mail.setSubject(subject))
                return false;
            if (!mail.setBody(content))
                return false;
            if (!mail.setFromSender(from))
                return false;
            if (!mail.setTo(to)) {
                return false;
            }
            mail.setNamePassword(username, password);
            if (!mail.sendOut(null))
                return false;
            return true;
        }

        /**
         * 带附件的正文有图片的带有抄送的邮件
         *
         * @return
         */
        boolean sendAndCcWithFile(String smtp, String from, String to, String subject,
                                  String content, String imageFile, String username, String password, String copyto, String filename) {
            ReplyEmail mail = new ReplyEmail(smtp);
            mail.setNeedAuth(true);//需要认证
            if (!mail.setSubject(subject)) {
                return false;
            }
            if (!mail.setBodyWithImg(content, imageFile)) {
                return false;
            }
            if (!mail.addFileAffix(filename))
                return false;
            if (!mail.setFromSender(from)) {
                return false;
            }
            if (!mail.setTo(to)) {
                return false;
            }
            if (!mail.setCopyTo(copyto)) {
                return false;
            }
            mail.setNamePassword(username, password);
            if (!mail.sendOut(copyto))
                return false;
            return true;
        }

// 1. host 地址
// 2. host 端口
// 3. username  mail邮箱账号
        //4. pwd  邮箱密码
        //5. sender: 发送人邮箱  【 只能一个 】
//6.  to_receiver:   收件人    【 ArrayList<> 】 一个集合
// 7.  cc_receiver:  抄送收件人  【 ArrayList<> 】 一个集合
// 8. bcc_receiver:  密送收件人  【 ArrayList<> 】 一个集合
// 9.   主题字样显示  mReceiveSubJectText  ___ 原有主题+[ReceiveDate:][SendDate:]
// 10. String emailText ;  // 邮件内容  ArrayList<Stirng> 每句话的集合
//11. 邮件图片显示      ArrayList<File>   imageList ;  // 显示图片的集合
//12. attatch 附件文件的集合      ArrayList<File>   attatchFileList ;  // 附件文件的集合

        void gmailssl(Properties props) {
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.put("mail.debug", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
        }


    }


    static class ImapIdleNewMsgThread extends Thread {
        private IMAPFolder fold;

        public ImapIdleNewMsgThread(Folder folder) {
            if (folder instanceof IMAPFolder) {
                fold = (IMAPFolder) folder;
            }
        }

        public void run() {
            try {
                monitorNewMail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Uses the IMAP IDLE Extension and monitors for new mail.
         *
         * @throws Exception
         */
        public void monitorNewMail() throws Exception {
            // Add messageCountListener to listen for new messages
            fold.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
                    System.out.println("Got " + msgs.length + " new messages in folder" + fold.getFullName());

                    // Just dump out the new messages
                    for (int i = 0; i < msgs.length; i++) {
                        try {
                            System.out.println("-----");
                            System.out.println("Message " + msgs[i].getMessageNumber() + ":");
                            msgs[i].writeTo(System.out);
                            showMessage(msgs[i]);
                        } catch (IOException ioex) {
                            ioex.printStackTrace();
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                        }
                    }
                }
            });

            // Check mail once in "freq" MILLIseconds
            try {
                int freq = 5000;
                boolean supportsIdle = false;
                try {
                    if (fold instanceof IMAPFolder) {
                        IMAPFolder f = (IMAPFolder) fold;
                        f.idle();
                        supportsIdle = true;
                    }
                } catch (FolderClosedException fex) {
                    throw fex;
                } catch (MessagingException mex) {
                    supportsIdle = false;
                }
                for (; ; ) {
                    if (supportsIdle && fold instanceof IMAPFolder) {
                        IMAPFolder f = (IMAPFolder) fold;
                        f.idle();
                        System.out.println("IDLE done");
                    } else {
                        Thread.sleep(freq); // sleep for freq milliseconds
                        // This is to force the IMAP server to send us
                        // EXISTS notifications.
                        fold.getMessageCount();
                    }
                }
            } catch (FolderClosedException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

// shutdown -p    立即关机
//

    public static void openMobileWifi() {

        String result0 = execCMD("adb kill-server");

        String result1 = execCMD("adb shell svc power stayon true ");
        String result2 = execCMD(" adb shell input swipe 200 1500 200 100");
        //adb shell am start -a android.settings.WIFI_SETTINGS
        String result3 = execCMD("adb shell am start -a android.settings.WIFI_SETTINGS");
        String result4 = execCMD("adb shell svc wifi enable");
        System.out.println("result0 = " + result0);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
    }

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





    /*
    DEBUG: setDebug: JavaMail version 1.6.2
    DEBUG: getProvider() returning javax.mail.Provider[STORE,imap,com.sun.mail.imap.IMAPStore,Oracle]
    DEBUG IMAP: mail.imap.fetchsize: 16384
    DEBUG IMAP: mail.imap.ignorebodystructuresize: false
    DEBUG IMAP: mail.imap.statuscachetimeout: 1000
    DEBUG IMAP: mail.imap.appendbuffersize: -1
    DEBUG IMAP: mail.imap.minidletime: 10
    DEBUG IMAP: closeFoldersOnStoreFailure
    DEBUG IMAP: trying to connect to host "imap.qq.com", port 143, isSSL false
     OK [CAPABILITY IMAP4 IMAP4rev1 ID AUTH=LOGIN NAMESPACE] QQMail XMIMAP4Server ready
    DEBUG IMAP: AUTH: LOGIN
    DEBUG IMAP: protocolConnect login, host=imap.qq.com, user=382581427@qq.com, password=<non-null>
    DEBUG IMAP: mechanism PLAIN not supported by server
    DEBUG IMAP: AUTHENTICATE LOGIN command trace suppressed
    DEBUG IMAP: AUTHENTICATE LOGIN command result: A0 OK Success login ok
    A1 CAPABILITY
     CAPABILITY IMAP4 IMAP4rev1 XLIST MOVE IDLE XAPPLEPUSHSERVICE NAMESPACE CHILDREN ID UIDPLUS
    A1 OK CAPABILITY Completed
    A2 LIST "" inbox
    LIST (\HasNoChildren) "/" "INBOX"
    A2 OK LIST completed
    DEBUG IMAP: connection available -- size: 1
    A3 SELECT INBOX
    8510 EXISTS
     0 RECENT
    OK [UNSEEN 5290]
    OK [UIDVALIDITY 1367677924] UID validity status
     OK [UIDNEXT 9236] Predicted next UID
    FLAGS (\Answered \Flagged \Deleted \Draft \Seen)
    OK [PERMANENTFLAGS (\* \Answered \Flagged \Deleted \Draft \Seen)] Permanent flags
    A3 OK [READ-WRITE] SELECT complete
    f.idle()---1
    A4 IDLE
    + idling
    DEBUG IMAP: startIdle: set to IDLE
    DEBUG IMAP: startIdle: return true
     */



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

    static String OriApplyOperationRule(String mType, String index, String mOriContent) {
        String identy = mType.trim() + index.trim();
        Rule applayRule = getRuleByIdentify(identy);
        if (applayRule == null) {
            System.out.println("没有查询到 identy =" + identy + "对应的处理规则");
            return mOriContent;
        }
        return applayRule.applyStringOperationRule1(mOriContent);
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

    public  static String getFileNameNoPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(0,fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = new String(fileName);
        }
        return name.toLowerCase().trim();
    }


    public  static String getFileTypeNoPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(fileName.lastIndexOf(".") + 1 ).trim().toLowerCase();
        }else{
            name = "";
        }
        return name.toLowerCase().trim();
    }


    public  static String getFileTypeWithPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = "";
        }
        return name.toLowerCase().trim();
    }
}
