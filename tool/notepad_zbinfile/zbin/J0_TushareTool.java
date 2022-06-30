
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//
public class J0_TushareTool {



    static ArrayList<String> TScode_List = new ArrayList<String>();

    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 J0 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    //修改2. J0_TushareTool  改为当前类名称  A1 Z9
    //修改2.1  J0 改为 对应的 标识符
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
    static String Cur_Bat_Name = "zstock_tushare_tool_J0";
    // 当前用户选中的 操作的类型  0-默认标识没有选中打印帮助字符串    1-标识选中默认规则1
    static int CUR_TYPE_INDEX = 1;
    static boolean allowEmptyInputParam = false;    // 是否允许输入参数为空 执行 rule的apply方法

/*******************修改属性列表 ------End *********************/


    /*******************固定属性列表 ------Begin *********************/


//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String J0_Dir_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_Data"+File.separator;
    static String J0_Python_Dir_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_Python"+File.separator;
    static String J0_DailyPython_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_DayPython"+File.separator;

    static String J0_TscodePython_Path = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_TscodePython"+File.separator;


    static String J0_call_all_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_all_python.bat";
    static String J0_call_select_rest_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_select_rest_python.bat";
    static String J0_call_select_single_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_select_single_python.bat";

    static String J0_call_day_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_day_python.bat";

    static String J0_call_rangeday_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_rangeday_python.bat";

    static String J0_call_fileday_python_bat = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator+"J0_0000_call_fileday_python.bat";

    
    
    static File J0_call_all_python_bat_File ;
    static File J0_call_rest_python_bat_File ;
    static File J0_call_single_python_bat_File ;
    static File J0_call_day_python_bat_File ;
    static File J0_call_rangeday_python_bat_File ;
    static File J0_call_fileday_python_bat_File ;

    static String J0_GuPiaoLieBiao_Path = zbinPath+File.separator+"J0_股票列表.xlsx";
    static String J0_JiaoYiRiQi_Path = zbinPath+File.separator+"J0_交易日历.xlsx";


    static String cur_os_zbinPath;
    static String win_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "win_zbin";
    static String lin_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "lin_zbin";
    static String mac_zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "mac_zbin";


//1. nodename_last_record_day=""  ;   // 当前记录的数据最大的时间
//2. nodename_begin_record_day="";  // 当前查询记录的起始的时间
//3. nodename_xxxxxx; // 记录当前node的 prop项  在 nodelist中自选中
    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   J0   G3 ... Z9
    static File J0_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream J0_Properties_InputStream;
    static OutputStream J0_Properties_OutputStream;
    static Properties J0_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();


    
    // zukgitA
    // zstock_tushare_tool_J0.bat  make_dynamic_batsh     
    static boolean is_Make_Dynamic_Bat_SH = false ;  // 输入的参数是否有 make_dynamic_bat  这个参数 如果 有 那么需要执行额外的逻辑
    
    // 
    
    //  J0_Dynamic_Bat.bat  J0_Dynamic_Bat.sh
    static File dynamic_Bat_SH_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "J0_Dynamic_Bat.bat");   // 动态 写入 执行命令的  bat 文件     J0_Dynamic_Bat.bat  J0_Dynamic_Bat.sh
    static ArrayList<String> dynamicBatShContentList = new ArrayList<>(); //  用于存放 动态执行内容的 数组
    
    // 当前Shell目录下的 文件类型列表  抽取出来  通用
    static HashMap<String, ArrayList<File>> CurDirFileTypeMap = new HashMap<String, ArrayList<File>>();
    ;


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;
    static String BAT_OR_SH_Point;
    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是主函数中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE;   // 当前 CMDER的路径 File 文件
    static File First_Input_Dir;   // 用户第一次可能输入的文件夹

    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出
    static String Origin_TYPE_2_ParamsStr;  // 最原始的  arg[1]
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


    static String TreeDataPath = zbinPath + File.separator + "J0_treedata.txt";
    static String Python_HeadTemplate_Path = zbinPath + File.separator +"J0_python_head_template.py";

    static J0_TushareTool J0_TushareTool;


    //  没有 ts_code 列的 并且
    static String Python_BodyTemplate_1 = zbinPath + File.separator +"J0_python_body_template_1.py";

    // 填充  ts_code 中文名称的模板  并且 持续追加到 一个 dataframe 中
    static String Python_BodyTemplate_2 = zbinPath + File.separator +"J0_python_body_template_2.py";

    // 填充  ts_code 中文名称的模板  并且有检测 小于 xlsx最大行数  1048576 的判断
    static String Python_BodyTemplate_3 = zbinPath + File.separator +"J0_python_body_template_3.py";

    // 有限制 每分钟 访问次数的 判断
    static String Python_BodyTemplate_4 = zbinPath + File.separator +"J0_python_body_template_4.py";

    // 依据时间月份分隔的 判断    以 每月为一个xlsx文件  每天为sheet 每天的交易数据为内容的  逻辑  日线行情
    static String Python_BodyTemplate_5 = zbinPath + File.separator +"J0_python_body_template_5.py";

    // 依据时间月份分隔的 判断    以一年中的每个周五为结点去测试 把一年的数据整合到一个xlsx 从2010年开始  周线行情
    static String Python_BodyTemplate_6 = zbinPath + File.separator +"J0_python_body_template_6.py";

    // 依据时间月份分隔的 判断    以一年中的每个月为sheet 把一年的数据整合到一个xlsx 从2010年开始 201001 201002  月线行情
    static String Python_BodyTemplate_7 = zbinPath + File.separator +"J0_python_body_template_7.py";


    //  从  开年第一天  到目前的天数  十大港股通  以每个月为sheet 每个月中的数据 追加到 一个 sheet 中 (有 日线的特点 也有月线的特点)
    static String Python_BodyTemplate_8 = zbinPath + File.separator +"J0_python_body_template_8.py";

    //  获取 资产负债表  1xts_code   追加 read_xlsx
    static String Python_BodyTemplate_9 = zbinPath + File.separator +"J0_python_body_template_9.py";




    // 链式 Node 结点   用于 记录 当前 父节点
    static ArrayList<TreeNode> AllNodeList = new ArrayList<TreeNode>();
    static ArrayList<TreeNode> AllRootNode = new ArrayList<TreeNode>();
    static ArrayList<TreeNode> AllLeafNode = new ArrayList<TreeNode>();



    // 记录当前最新的交易日  今天是工作日的话 那么就是今天  如果不是交易日 那么就是离他最近的交易日
    static int SH_Last_Trade_Day_Int = getTimeStamp_YYYYMMDD_IntFlag();
    static int  SH_Tomorrow_WorkTrade_Day_Int = SH_Last_Trade_Day_Int+1;


    // 20200101  20201231
    static int SH_Now_Year_BeginTradeDay_IntFlag = getTimeStamp_YYYYMMDD_BeginIntFlag();
    static int SH_Now_Year_EndTradeDay_IntFlag = getTimeStamp_YYYYMMDD_EndIntFlag();

    // 获取一周期
    //            last_trade_date
 static   int old_1_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-1);
 static   int old_7_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,-7);
 static   int old_30_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-31);
 static   int old_90_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-90);
 static   int future_1_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,1);
 static   int future_7_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,7);
 static   int future_30_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,31);
 static   int future_90_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,90);
 static   int old_yearbegin_day        =  getYearBeginIntFlag(SH_Last_Trade_Day_Int);  // 今年年初 那一天
 static   int old_preyearend_day      =  getPreYearBeginIntFlag(SH_Last_Trade_Day_Int); // 上一年年末 那一天
 static   int old_preyearbegin_day       =  getPreYearEndIntFlag(SH_Last_Trade_Day_Int); // 上一年年初 那一天
 static   int future_yearend_day      =  getYearEndIntFlag(SH_Last_Trade_Day_Int);    //  今年最后 那一天
 static   int next_yearbegin_day =  getNextYearBeginIntFlag(SH_Last_Trade_Day_Int); // 下一年的开始日期


    static boolean isContainEnvironment(String program) {
        boolean environmentExist = false;
        if (EnvironmentValue.contains(program)) {
            environmentExist = true;
        }
        return environmentExist;
    }


    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\J0_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\J0_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
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

            File J0_Dir_Path_File = new File(J0_Dir_Path);
            File J0_Python_Dir_Path_File = new File(J0_Python_Dir_Path);
            File J0_DailyPython_File = new File(J0_DailyPython_Path);

            File J0_TsCodePython_File = new File(J0_TscodePython_Path);

            File J0_GuPiaoLieBiao_Path_File  = new File( J0_GuPiaoLieBiao_Path);
            File J0_JiaoYiRiQi_Path_File  = new File( J0_JiaoYiRiQi_Path);





            if (!J0_Dir_Path_File.exists()) {
                J0_Dir_Path_File.mkdirs();
            }


            if (!J0_Python_Dir_Path_File.exists()) {
                J0_Python_Dir_Path_File.mkdirs();

            }

            if (!J0_DailyPython_File.exists()) {
                J0_DailyPython_File.mkdirs();

            }

            if (!J0_TsCodePython_File.exists()) {
                J0_TsCodePython_File.mkdirs();

            }



            File python_call_all_bat  = new File( J0_call_all_python_bat);
            File python_call_bat_windows  = new File( J0_Python_Dir_Path_File + File.separator + python_call_all_bat.getName());
            fileCopy(python_call_all_bat,python_call_bat_windows);  // 把 全局调用当前的 python文件的 .bat 文件复制到 当前 python 目录 方便 调试
            J0_call_all_python_bat_File = python_call_bat_windows;

            File python_call_rest_bat  = new File( J0_call_select_rest_python_bat);
            File python_call_rest_bat_windows  = new File( J0_Python_Dir_Path_File + File.separator + python_call_rest_bat.getName());
            fileCopy(python_call_rest_bat,python_call_rest_bat_windows);  // 把 全局调用当前的 python文件的 .bat 文件复制到 当前 python 目录 方便 调试
            J0_call_rest_python_bat_File = python_call_rest_bat_windows;


            File python_call_single_bat  = new File( J0_call_select_single_python_bat);
            File python_call_single_bat_windows  = new File( J0_Python_Dir_Path_File + File.separator + python_call_single_bat.getName());
            fileCopy(python_call_single_bat,python_call_single_bat_windows);  // 把 全局调用当前的 python文件的 .bat 文件复制到 当前 python 目录 方便 调试
            J0_call_single_python_bat_File =  python_call_single_bat_windows;


            File python_call_day_bat  = new File( J0_call_day_python_bat);
            File python_call_day_windows  = new File( J0_DailyPython_File + File.separator + python_call_day_bat.getName());
            fileCopy(python_call_day_bat,python_call_day_windows);  // J0_DayPython 中放入 文件  J0_0000_call_day_python.bat
            J0_call_day_python_bat_File = python_call_day_windows;


            File python_call_rangeday_bat  = new File( J0_call_rangeday_python_bat);
            File python_call_rangeday_windows  = new File( J0_DailyPython_File + File.separator + python_call_rangeday_bat.getName());
            fileCopy(python_call_rangeday_bat,python_call_rangeday_windows);  // J0_DayPython 中放入 文件  J0_0000_call_day_python.bat
            J0_call_rangeday_python_bat_File = python_call_rangeday_windows;

           
            
            		
          File python_call_fileday_bat  = new File( J0_call_fileday_python_bat);
           File python_call_fileday_windows  = new File( J0_DailyPython_File + File.separator + python_call_fileday_bat.getName());
          fileCopy(python_call_fileday_bat,python_call_fileday_windows);  // J0_DayPython 中放入 文件  J0_0000_call_day_python.bat
           J0_call_fileday_python_bat_File = python_call_fileday_windows;
                    

            if (J0_GuPiaoLieBiao_Path_File.exists() ) {
                File python_GuPiaoLieBiao_Path_File  = new File( J0_Python_Dir_Path_File + File.separator + J0_GuPiaoLieBiao_Path_File.getName());
                fileCopy(J0_GuPiaoLieBiao_Path_File,python_GuPiaoLieBiao_Path_File);  // 把 全局调用当前的 python文件的 .bat 文件复制到 当前 python 目录 方便 调试
            }

            if (J0_JiaoYiRiQi_Path_File.exists() ) {
                File python_JiaoYiRiQi_Path_File  = new File( J0_Python_Dir_Path_File + File.separator + J0_JiaoYiRiQi_Path_File.getName());
                fileCopy(J0_JiaoYiRiQi_Path_File,python_JiaoYiRiQi_Path_File);  // 把 全局调用当前的 python文件的 .bat 文件复制到 当前 python 目录 方便 调试
            }







            if (!J0_Properties_File.exists()) {
                J0_Properties_File.createNewFile();
            }
            J0_Properties_InputStream = new BufferedInputStream(new FileInputStream(J0_Properties_File.getAbsolutePath()));
            J0_Properties.load(J0_Properties_InputStream);
            Iterator<String> it = J0_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println("prokey:" + key + " propvalue: " + J0_Properties.getProperty(key));
                propKey2ValueList.put(key, J0_Properties.getProperty(key));
            }
            J0_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    static void showMap(Map<String, String> propMap ) {


        System.out.println(" ___________________ " +" Prop属性 "+"___________________ ");

            Map.Entry<String , String> entryItem;

            if(propMap != null && propMap.size() > 0){
                Iterator iterator = propMap.entrySet().iterator();
                while( iterator.hasNext() ){


                    entryItem = (Map.Entry<String , String>) iterator.next();
                    String propKey = entryItem.getKey();
                    String propValue = entryItem.getValue();

                    System.out.println("propKey = "+ propKey + "    propValue = "+ propValue);
                }
            }else{

                System.out.println("当前 Prop 文件 为 空！ ");

            }



    }

    @SuppressWarnings("unchecked")
    static void setProperity() {
        try {

            Map.Entry<String , String> entryItem;
            if(propKey2ValueList != null){
                Iterator iterator = propKey2ValueList.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , String>) iterator.next();
                    J0_Properties.put(entryItem.getKey(), entryItem.getValue());
                }
            }
            J0_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(J0_Properties_File.getAbsolutePath()));
            J0_Properties.store(J0_Properties_OutputStream, "");
            J0_Properties_OutputStream.close();
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
                
                if("make_dynamic_batsh".equals(args[i])) {
                	
                	is_Make_Dynamic_Bat_SH = true;   // 检测当前是否输入了 make_dynamic_batsh 来动态输出一个 bat sh 文件 动态执行
                	continue;
                }
                
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if (i == 1) {  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];
                    Origin_TYPE_2_ParamsStr =  args[i];

                   int pythonType =   getPythonOperationType(CUR_TYPE_2_ParamsStr);
                   PythonType_Input =   pythonType;
                    //   计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    int userSelectedIndex = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                    PythonItem_Index = userSelectedIndex;

                    System.out.println("userSelectedIndex = "+ userSelectedIndex);
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


    class QueryItem{
    	
    	 // 输入的 int 区分类型  default-0  all-1  rest-2  single-3    day-4    ts_code-5 
        int mItemPythonType_Input ; 
        String TsCode_Input;  // 输入的 ts_code

        // 外部 给了 一个 区间  例如 day_20200715-20210812   那么 这里就会放入 这个区间内 交易 时间的集合  
        ArrayList<Integer> day4TimeQueryTradeDayIntList;

        String inputPramsStr = "";
        String start_day = getTimeStamp_YYYYMMDD();
        String end_day = getTimeStamp_YYYYMMDD();
        String trade_day = getTimeStamp_YYYYMMDD();

        File logFile ;   //  输入的 以 file_xxx开头的文件  里面包含有  日期数据 



        QueryItem(int mPythonType_Input){
            mItemPythonType_Input = mPythonType_Input;
        }

        
        // 类型 输入 , 第二个是交易日期
        QueryItem(int mPythonType_Input , int mTradeDay){
            mItemPythonType_Input = mPythonType_Input;
            if(mPythonType_Input == 4) {
                trade_day = mTradeDay+"";
            }
   
        }

        
        
        void initDay4TimeQueryList(String mYYYMMDD1 , String mYYYMMDD2 ) {
        	day4TimeQueryTradeDayIntList = new ArrayList<Integer>();
        	int begin_data = Integer.parseInt(mYYYMMDD1);
        	int end_data = Integer.parseInt(mYYYMMDD2);
        	if(begin_data > end_data) {
        		int temp = begin_data;
        		begin_data = end_data;
        		end_data = temp;
        	}
        	
        	for (int i = 0; i < SH_TradeDayList.size(); i++) {
        		int tradeDayInt  = SH_TradeDayList.get(i);
        		if(tradeDayInt >= begin_data && tradeDayInt <= end_data) {
        			day4TimeQueryTradeDayIntList.add(tradeDayInt);
        		}
        		
				
			}
        	
        	int matchTradeDayCount = day4TimeQueryTradeDayIntList.size();
	System.out.println("__________day区间查询 【"+begin_data+"_____"+end_data+"】 begin __________"); 	
for (int i = 0; i < day4TimeQueryTradeDayIntList.size(); i++) {
	int matchTradeDay = day4TimeQueryTradeDayIntList.get(i);
   	System.out.println("Zindex["+i+":"+matchTradeDayCount+"]"+"___"+"【"+matchTradeDay+"】"+"___【"+begin_data+"_____"+end_data+"】");
}
System.out.println("__________day区间查询 【"+begin_data+"_____"+end_data+"】 end __________"); 	

        	
        }



      boolean     isTradeDate(){

                if(!SH_TradeDayList.contains(SH_Last_Trade_Day_Int)){
                    System.out.println("当前 依据条件环境 选中的 时间 : "+ SH_Last_Trade_Day_Int + " 不是交易日！！ 请重新输入交易日!");
                    return false;
                }
                return true;
    }
      
      
      ArrayList<Integer> readTradeDataLogFile(File logFile){
    	    ArrayList<Integer> tradeDateInLogFile_List = new      ArrayList<Integer> ();
    	    
    	ArrayList<String> rawLogList =     ReadFileContentAsList(logFile);
    	
    	if(rawLogList != null) {
    		
    		for (int i = 0; i < rawLogList.size(); i++) {
				String tradeDayStr = rawLogList.get(i).trim();
				if(isNumeric(tradeDayStr) && tradeDayStr.length() == 8) {
					
					tradeDateInLogFile_List.add(Integer.parseInt(tradeDayStr));
				}
			}
    		
    		
    	}
    	    
    	    
    	    return tradeDateInLogFile_List;
    	  
    	  
      }
      
       //  mPythonType_Input = 4 , mInputParamsStr = file_C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log

        QueryItem(int mPythonType_Input,String mInputParamsStr){
            mItemPythonType_Input = mPythonType_Input;
            inputPramsStr =  mInputParamsStr;

            System.out.println("XXmPythonType_Input = "+ mPythonType_Input+"   XXmInputParamsStr="+ mInputParamsStr);
            if(mPythonType_Input == 4){  //   包含 day
                System.out.println("mPythonType_Input == 4  mInputParamsStr = "+ mInputParamsStr);
            	if(mInputParamsStr.startsWith("file_")) {
            		String logFilePath = mInputParamsStr.substring("file_".length());
            		
            		logFile = new File(logFilePath);
            		if(logFile.exists()) {
            			System.out.println("当前 logFile 存在 = "+ logFile.getAbsolutePath());
            			day4TimeQueryTradeDayIntList = readTradeDataLogFile(logFile);
            			
            			if(day4TimeQueryTradeDayIntList != null && day4TimeQueryTradeDayIntList.size() > 0 ) {
            				System.out.println("当前 从 "+logFile.getAbsolutePath()+" 读取到tradeday数量为【"+day4TimeQueryTradeDayIntList.size()+"】");
            			}else {
            				System.out.println("当前 从 "+logFile.getAbsolutePath()+" 无法读取到tradeday 日期数据!");
	
            			}
            			
            		}else {
             			System.out.println("当前 logFile 不存在   logFilePath="+ logFilePath);	
            		}
            		
            	}else {
            		
                    if(mInputParamsStr.contains("-")){
                        String dayNum = inputPramsStr.substring(inputPramsStr.indexOf("-")+1);
                     //   zzzzzzzzzzzzzzzz
                        System.out.println("mPythonType_Input == 4  dayNum = "+ dayNum);
                        System.out.println("mPythonType_Input == 4  A  SH_Last_Trade_Day_Int = "+ SH_Last_Trade_Day_Int);

                        if(isNumeric(dayNum) && dayNum.length() != 8){  // 当天的日子减去多少天
                            int dayNumInt = Integer.parseInt(dayNum);
                            SH_Last_Trade_Day_Int = getFutureDayFlag(SH_Last_Trade_Day_Int,-(dayNumInt));
                        }else if(dayNum.length()== 8 && isYYYYMMDDStr(dayNum)){  //  day_20211201-21211231 如果当前的是日期   那么就是给了一段时间值
                          
                        	String time1 = mInputParamsStr.replace("day_", "").replace("-", "").replace(dayNum, "");
                        	System.out.println("ZZTime1:"+time1+"  Time2:"+dayNum);
                        	initDay4TimeQueryList(time1,dayNum);
                        	
                        }
                        System.out.println("mPythonType_Input == 4  B  SH_Last_Trade_Day_Int = "+ SH_Last_Trade_Day_Int);

                    }else if(mInputParamsStr.contains("_")){
                        String timeStamp = inputPramsStr.substring(inputPramsStr.indexOf("_")+1);
                        // 20100101   这样的字符串
                        System.out.println("mPythonType_Input == 4  timeStamp = "+ timeStamp);
                        if(isNumeric(timeStamp)){
                            SH_Last_Trade_Day_Int = Integer.parseInt(timeStamp);
                        }

                    }
            		
            		
            	}
         


                 start_day = SH_Last_Trade_Day_Int+"";
                 end_day = SH_Last_Trade_Day_Int+"";
                 trade_day = SH_Last_Trade_Day_Int+"";


            }
        }


        String   ZHoldPlace_J0_Dir_PATH(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = J0_Dir_Path;
            } else if(mItemPythonType_Input == 5){
                xlsxName = J0_Dir_Path;
            }
            return xlsxName;
        }




        String ZHoldPlace_day_date(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = "day_"+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = "tscode_"+trade_day;
            }
            return xlsxName;
        }





        String ZHoldPlace_OPERATION_DAY(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = "day_"+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = "tscode_"+getTimeStamp();
            }
            return xlsxName;
        }




        String mstart_date(){
            String dateStr = "";


            return dateStr;

        }


        String ZHoldPlace_Title(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
            	// xlsx 从 day_20211231.xlsx 转为 day_2021_1231.xlsx 转为    方便查看

                xlsxName = "day_"+getYYYY_MMDD_FromTimeStamp(trade_day);   
            	System.out.println("ZZtrade_day = "+ trade_day +" xlsxName = "+ xlsxName);
            } else if(mItemPythonType_Input == 5){
                xlsxName = "tscode_"+getTimeStamp();
            }
            return xlsxName;
        }
        

        boolean isYYYYMMDDStr(String mDateStr) {

        	if(mDateStr == null || mDateStr.length() != 8) {
        		return false;
        	}
        	
        	String yearStr = mDateStr.substring(0,4);
        	String mmStr = mDateStr.substring(4,6);
           	String ddStr = mDateStr.substring(6);
           	
           	if(!isNumeric(yearStr) ||  !isNumeric(mmStr)  || !isNumeric(ddStr)) {
           		return false;
           	}
           	
           	int mmInt = Integer.parseInt(mmStr);
           	int ddInt = Integer.parseInt(ddStr);
           	
           	if(mmInt > 12) {
           		return false;
           	}
           	
           	if(ddInt > 32) {
           		return false;
           	}
        	
        	
        	return true;
        	
        }
        
		
 String  getYYYY_MMDD_FromTimeStamp(String rawTimeStamp) {
 
        	if(rawTimeStamp == null || rawTimeStamp.length() != 8) {
        		return rawTimeStamp;
        	}
        	
        	String year = rawTimeStamp.substring(0,4);
        	String mmdd = rawTimeStamp.substring(4);
        	return year+"_"+mmdd;
//        	return rawTimeStamp;
        	
        }
        
        
        
        

        String ZHoldPlace_Year_Int(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = ""+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = ""+getTimeStamp();
            }
            return xlsxName;
        }


        String ZHoldPlace_Weekly_Day(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = ""+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = ""+getTimeStamp();
            }
            return xlsxName;
        }

        String ZHoldPlace_Year_Month(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = ""+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = ""+getTimeStamp();
            }
            return xlsxName;
        }

        String ZHoldPlace_Month_Index(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = ""+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = ""+getTimeStamp();
            }
            return xlsxName;
        }

        String ZHoldPlace_Mstart_date(){
            String xlsxName = "";
            if(mItemPythonType_Input == 4){
                xlsxName = ""+trade_day;
            } else if(mItemPythonType_Input == 5){
                xlsxName = ""+getTimeStamp();
            }
            return xlsxName;
        }




        File getOutPutFile(){
            File output_File = null;
            if(mItemPythonType_Input == 4){
                output_File = new File(J0_DailyPython_Path+File.separator+"day_"+trade_day+".py");
            } else if(mItemPythonType_Input == 5){
                output_File = new File(J0_DailyPython_Path+File.separator+"tscode_"+trade_day+".py");
            }

            return output_File;
        }





    }

  static  QueryItem mRootqueryItem ;

    static void   makePythonFileFromLeafNode(QueryItem curQueryItem ){

        File headFile = new File(Python_HeadTemplate_Path);
        if(!headFile.exists()){
            System.out.println("当前头部文件 "+Python_HeadTemplate_Path + " 不存在,请检查!");
            return;
        }


        File bodyFile = new File(Python_BodyTemplate_1);
        if(!bodyFile.exists()){
            System.out.println("当前Body部文件 "+Python_BodyTemplate_1 + " 不存在,请检查!");
            return;
        }




if(curQueryItem.mItemPythonType_Input == 1 ){

    for (int i = 0; i < AllLeafNode.size() ; i++) {
        TreeNode leafNode = AllLeafNode.get(i);
        ArrayList<String> pythonHeadStrList = ReadFileContentAsList(new File(getNodeHeadFile(leafNode.nodeName)));
        ArrayList<String> pythonBodyStrList = ReadFileContentAsList(new File(getNodeBodyFilePath(leafNode.nodeName)));
        System.out.println("Python-Item  = "+ leafNode.nodeName + "  "+((LeafNode)leafNode).leaf_chinese_title);
        ((LeafNode)leafNode).pyhtonTemplate(pythonHeadStrList,pythonBodyStrList,curQueryItem.mItemPythonType_Input,curQueryItem);
    }

}else if(curQueryItem.mItemPythonType_Input == 2 ){
    for (int i = 0; i < AllLeafNode.size() ; i++) {
        TreeNode leafNode = AllLeafNode.get(i);
        int curNodeIndex = leafNode.blockIndex;
        System.out.println("PythonType_Input = "+ PythonType_Input +   "           PythonItem_Index = "+ PythonItem_Index);

        if(curNodeIndex >= PythonItem_Index  ){
            ArrayList<String> pythonHeadStrList = ReadFileContentAsList(new File(getNodeHeadFile(leafNode.nodeName)));
            ArrayList<String> pythonBodyStrList = ReadFileContentAsList(new File(getNodeBodyFilePath(leafNode.nodeName)));
            System.out.println("Python-Item  = "+ leafNode.nodeName + "  "+((LeafNode)leafNode).leaf_chinese_title);
            ((LeafNode)leafNode).pyhtonTemplate(pythonHeadStrList,pythonBodyStrList,curQueryItem.mItemPythonType_Input,curQueryItem);
        }

    }
} else if(curQueryItem.mItemPythonType_Input == 3 ){
    System.out.println("curQueryItem.mItemPythonType_Input = "+ curQueryItem.mItemPythonType_Input +   "           PythonItem_Index = "+ PythonItem_Index);
    for (int i = 0; i < AllLeafNode.size() ; i++) {
        TreeNode leafNode = AllLeafNode.get(i);
        int curNodeIndex = leafNode.blockIndex;
        if(curNodeIndex == PythonItem_Index  ){

            ArrayList<String> pythonHeadStrList = ReadFileContentAsList(new File(getNodeHeadFile(leafNode.nodeName)));
            ArrayList<String> pythonBodyStrList = ReadFileContentAsList(new File(getNodeBodyFilePath(leafNode.nodeName)));
            System.out.println("Python-Item  = "+ leafNode.nodeName + "  "+((LeafNode)leafNode).leaf_chinese_title);
            ((LeafNode)leafNode).pyhtonTemplate(pythonHeadStrList,pythonBodyStrList,curQueryItem.mItemPythonType_Input,curQueryItem);
        }

    }


}else if(curQueryItem.mItemPythonType_Input == 4){


    ArrayList<String> allDailyCode = new  ArrayList<String>();
    ArrayList<String> allDailyBodyCode = new  ArrayList<String>();

    ArrayList<String> allOriginCode = new  ArrayList<String>();
    ArrayList<String> allOriginBodyCode = new  ArrayList<String>();

    ArrayList<TreeNode> dailyQuery_NodeList = getNodeSelectedNode(dailyQueryNodeNameList);
    if(dailyQuery_NodeList.size() == 0){
        System.out.println("daily 查询选中的 查询 TreeNode列表为空!    dailyQuery_NodeList.size() = 0");
        return;
    }

    ArrayList<String> pythonHeadStrList = null;
    for (int i = 0; i <dailyQuery_NodeList.size() ; i++) {
        TreeNode leafNode = dailyQuery_NodeList.get(i);
         ReadFileContentAsList(new File(getNodeHeadFile(leafNode.nodeName)));
        pythonHeadStrList = ReadFileContentAsList(new File(getNodeHeadFile(leafNode.nodeName)));
        ArrayList<String> pythonBodyStrList = ReadFileContentAsList(new File(getNodeBodyFilePath(leafNode.nodeName)));
        System.out.println("Python-Item  = "+ leafNode.nodeName + "  "+((LeafNode)leafNode).leaf_chinese_title);

        allOriginBodyCode.addAll(pythonBodyStrList);  //   对 获取到的 pythonBodyStrList 进行 一对一的 处理

        allDailyBodyCode.addAll(((LeafNode)leafNode).pyhtonDailyTemplate(pythonHeadStrList,pythonBodyStrList,curQueryItem));
    }

    if(pythonHeadStrList != null){
        allDailyCode.addAll(pythonHeadStrList);
        allOriginCode.addAll(pythonHeadStrList);
    }else{
        allDailyCode.addAll(ReadFileContentAsList(new File(getNodeHeadFile("gupiaoliebiao"))));
        allOriginCode.addAll(ReadFileContentAsList(new File(getNodeHeadFile("gupiaoliebiao"))));
    }

    allDailyCode.addAll(allDailyBodyCode);
File dailyPythonFile =     new File(J0_DailyPython_Path+File.separator+"day_"+curQueryItem.trade_day+".py");
File ori_dailyPythonFile = new File(J0_DailyPython_Path+File.separator+"day_"+curQueryItem.trade_day+"_oringin"+".py");

    allOriginCode.addAll(allOriginBodyCode);

writeContentToFile(dailyPythonFile,allDailyCode);
// writeContentToFile(ori_dailyPythonFile,allOriginCode);   // 打印 原始的 模板  方便分析

}




 }

    static ArrayList<TreeNode> getNodeSelectedNode(ArrayList<String> nodeNameList){
        ArrayList<TreeNode> selectedNodeList = new    ArrayList<TreeNode>();
        for (int i = 0; i < AllLeafNode.size(); i++) {
            TreeNode itemNode = AllLeafNode.get(i);
            String itemNodeName = itemNode.nodeName;
            if(nodeNameList.contains(itemNodeName)){
                selectedNodeList.add(itemNode);
            }
        }

        return selectedNodeList;

    }

    // 读取 J0_treedata.txt   形成树型Node
    static void initTreeData() {
        File treeDataFile = new File(TreeDataPath);

        if (!treeDataFile.exists()) {
            System.out.println(treeDataFile.getAbsolutePath() + " 剧本数据文件不存在");
        }

        ArrayList<String> contentList = ReadFileContentAsList(treeDataFile);
        System.out.println("打印 TreeData ！");

        for (int i = 0; i < contentList.size(); i++) {
            System.out.println(contentList.get(i));
        }

        System.out.println("分块 Block");
        ArrayList<ArrayList<String>> blockList = getTreeDataBlock(contentList);

        for (int i = 0; i < blockList.size(); i++) {
            ArrayList<String> block = blockList.get(i);
            System.out.println("════════" + " 第" + i + "块 " + "════════");
            for (int j = 0; j < block.size(); j++) {
                String lineStr = block.get(j);
                System.out.println(lineStr);
            }
            System.out.println("════════" + "块 Over " + "════════");
        }


        // 链式 Node 结点   用于 记录 当前 父节点
        ArrayList<TreeNode> rootNodeList = new ArrayList<TreeNode>();

        J0_TushareTool = new J0_TushareTool();
        TreeNode preNodeItem = null;
        for (int i = 0; i < blockList.size(); i++) {
            ArrayList<String> block = blockList.get(i);
            System.out.println("调用 Block[" + i + "]");
            System.out.println("------------------");
            for (int j = 0; j < block.size(); j++) {
                System.out.println(block.get(j));
            }
            System.out.println("------------------");
            TreeNode nodeItem = J0_TushareTool.CreateNode(block,i);
            TreeNode endNodeInList = null;
            int endNodeDeep = 0;
            if (rootNodeList.size() >= 1) {
                endNodeInList = rootNodeList.get(rootNodeList.size() - 1);
                endNodeDeep = endNodeInList.deepth;
            }
            if (nodeItem instanceof RootNode) {
                rootNodeList = new ArrayList<>();
                rootNodeList.add(nodeItem);
                nodeItem.parentLinkList = null;
            } else if (nodeItem instanceof BranchNode) {
                int nodeItemDeep = nodeItem.deepth;
                System.out.println("nodeItemDeep = " + nodeItemDeep + " endNodeDeep =  " + endNodeDeep);
                if (nodeItemDeep == endNodeDeep) {
                    System.out.println("结点深度等于 nodeItemDeep = "+ nodeItemDeep + "  endNodeDeep = "+ endNodeDeep + "      nodeName = "+ nodeItem.nodeName);
                    //  如果两个 枝杈结点 同等级 那么 去掉原有的最后一个   使得 父节点为最后一个结点
                    ArrayList<TreeNode> newNodeList = new ArrayList<TreeNode>();
                    for (int j = 0; j < rootNodeList.size() - 1; j++) {
                        newNodeList.add(rootNodeList.get(j));
                    }
                    rootNodeList = newNodeList;
//                    rootNodeList.add(nodeItem);
                } else if (nodeItemDeep > endNodeDeep) {
                    System.out.println("结点深度大于当前队列长度 nodeItemDeep = "+ nodeItemDeep + "  endNodeDeep = "+ endNodeDeep + "      nodeName = "+ nodeItem.nodeName);

                    //  如果新的结点 深度 大于 最后一个结点 那么直接 加入 新的 结点
                    ArrayList<TreeNode> newNodeList = new ArrayList<TreeNode>();
                    for (int j = 0; j < rootNodeList.size(); j++) {
                        newNodeList.add(rootNodeList.get(j));
                    }
                    if ( preNodeItem != null && preNodeItem instanceof  BranchNode && preNodeItem.deepth < nodeItemDeep ) {
                        if(!newNodeList.contains(preNodeItem)){
                            newNodeList.add(preNodeItem);
                        }

                    }
                    if(!newNodeList.contains(nodeItem)){
                        newNodeList.add(nodeItem);
                    }
                    rootNodeList = newNodeList;


                } else {

                    System.out.println("结点深度小于当前队列长度 nodeItemDeep = "+ nodeItemDeep + "  endNodeDeep = "+ endNodeDeep + "      nodeName = "+ nodeItem.nodeName);

                    //  如果新的结点 深度 小于 最后一个结点   那么 退出结点 直到 新节点深度
                    ArrayList<TreeNode> tempNodeList = new ArrayList<TreeNode>();

                    for (int j = 0; j < nodeItemDeep - 1; j++) {
                        tempNodeList.add(rootNodeList.get(j));
                    }
                    rootNodeList = tempNodeList;

                }


                nodeItem.parentLinkList = rootNodeList;
                if (nodeItem.parentLinkList.get(nodeItem.parentLinkList.size() - 1) == nodeItem) {
                    ArrayList<TreeNode> freshArr = new ArrayList<TreeNode>();
                    for (int j = 0; j < nodeItem.parentLinkList.size() - 1; j++) {
                        freshArr.add(nodeItem.parentLinkList.get(j));
                    }
                    nodeItem.parentLinkList = freshArr;

                }
            } else if (nodeItem instanceof LeafNode) {

                nodeItem.parentLinkList = rootNodeList;
            }

            preNodeItem = nodeItem;
        }

        System.out.println("════════" + " 打印所有的TreeNode " + "════════");
        out1:
        for (int i = 0; i < AllNodeList.size(); i++) {
            TreeNode nodeItem = AllNodeList.get(i);
            if (nodeItem.isRoot) {
                AllRootNode.add(nodeItem);
            }else if(nodeItem.isLeaf){
                AllLeafNode.add(nodeItem);
            }
            nodeItem.getAndInitDynamicDeep();
//            System.out.println("index [ "+ i+" ] = "+ " NodeName="+nodeItem.nodeName +  "   "+ nodeItem.getOrganizationIdentify() + "   " +"Deepth = "+ nodeItem.deepth);

            Set<TreeNode> childNodeList = new HashSet<>();

            in1:
            for (int j = 0; j < AllNodeList.size(); j++) {
                TreeNode nodecheck = AllNodeList.get(j);
                if (nodeItem == nodecheck) {
                    continue;
                }


                if (nodeItem.isRoot && !nodecheck.isRoot && nodecheck.getParentNode() == nodeItem) {

                    if (nodeItem.childArr == null) {
                        nodeItem.childArr = new ArrayList<TreeNode>();
                    }

                    if(!nodeItem.childArr.contains(nodecheck)){
                        nodeItem.childArr.add(nodecheck);
                    }

//                    nodeItem.getParentNode().childArr.addAll(childNodeList);
//                    System.out.println("nodeItem节点: " + nodeItem.nodeName + "nodeItem.childArr.size = " + nodeItem.childArr.size() + "  nodecheck =" + nodecheck.nodeName);
                    continue in1;
                }


                if ( nodeItem.getParentNode() == nodecheck) {

                    if (nodecheck.childArr == null) {
                        nodecheck.childArr = new ArrayList<TreeNode>();
                    }

                    if(!nodecheck.childArr.contains(nodeItem)){

                        nodecheck.childArr.add(nodeItem);
                    }
                    continue in1;
                }

                if (nodeItem.getParentNode() == nodecheck.getParentNode() && nodecheck.getParentNode() != null) {
                    childNodeList.add(nodeItem);
                    childNodeList.add(nodecheck);
                }
            }

            if (!nodeItem.isRoot && nodeItem.getParentNode() != null && !nodeItem.getParentNode().isRoot) {
                if (nodeItem.getParentNode().childArr == null) {
                    nodeItem.getParentNode().childArr = new ArrayList<TreeNode>();
                }

                HashSet<TreeNode> setNode = new  HashSet<TreeNode>();
                setNode.addAll(nodeItem.getParentNode().childArr);
                setNode.addAll(childNodeList);

                nodeItem.getParentNode().childArr.clear();
                nodeItem.getParentNode().childArr.addAll(setNode);
            }


        }


        System.out.println("════════" + " 打印所有的TreeNode的showNodeInfo() " + "════════");
        InitProp_TreeNode();
        for (int i = 0; i < AllNodeList.size(); i++) {

            TreeNode nodeItem = AllNodeList.get(i);
            System.out.println("══════" + "showNodeInfo Index【" + i + "】" + "══════");
            nodeItem.showNodeInfo();
        }

        for (int i = 0; i < AllRootNode.size(); i++) {
            TreeNode rootNodeItem = AllRootNode.get(i);
            System.out.println("══════" + "根节点 Index【" + i + "】" + "══════");
            rootNodeItem.showNodeInfo();
            System.out.println("══════" + "开始 TraceNode Index【" + i + "】" + " NodeName = " + rootNodeItem.nodeName + "══════");
            TrackNode(rootNodeItem);
        }
    }

    static void TrackNode(TreeNode curNode) {
        boolean isLeafNode = curNode instanceof LeafNode;
        String nodeTipA = isLeafNode?" 叶子结点 ":" 骨干结点 ";
        nodeTipA = nodeTipA + " isLeaf = "+ curNode.isLeaf+"  &&   isRoot = "+curNode.isRoot;
        System.out.println("node = " + curNode.nodeName + nodeTipA+  " Deepth = " + curNode.deepth + " Origanization = " + curNode.getOrganizationIdentify() +" blockIndex = "+ curNode.blockIndex);
        if (curNode.childArr == null || curNode.childArr.size() == 0) {
            return;
        }
        curNode.childArr.sort(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                if(o1.blockIndex == o2.blockIndex){
                    return 0;
                }
                return o1.blockIndex > o2.blockIndex ? 1: -1;
            }
        });
        for (int i = 0; i < curNode.childArr.size(); i++) {
            TreeNode itemNode = curNode.childArr.get(i);
            TrackNode(itemNode);
        }
    }

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");

        TimeZone.setDefault(timeZone);
        
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name + ".bat";
            BAT_OR_SH_Point = ".bat";
            cur_os_zbinPath = win_zbinPath;
             dynamic_Bat_SH_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "J0_Dynamic_Bat.bat");   // 动态 写入 执行命令的  bat 文件     J0_Dynamic_Bat.bat  J0_Dynamic_Bat.sh
            
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            BAT_OR_SH_Point = ".sh";
            cur_os_zbinPath = lin_zbinPath;
            dynamic_Bat_SH_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "J0_Dynamic_Bat.sh");   // 动态 写入 执行命令的  bat 文件     J0_Dynamic_Bat.bat  J0_Dynamic_Bat.sh

        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
            BAT_OR_SH_Point = ".sh";
            cur_os_zbinPath = mac_zbinPath;
            dynamic_Bat_SH_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "J0_Dynamic_Bat.sh");   // 动态 写入 执行命令的  bat 文件     J0_Dynamic_Bat.bat  J0_Dynamic_Bat.sh

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
        CUR_RULE_LIST.add(new Operation_tip_Rule_1());
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


    class Operation_tip_Rule_1 extends Basic_Rule {
        ArrayList<File> curInputFileList;
        


        Operation_tip_Rule_1(boolean mIsInputDirAsSearchPoint) {
            super(1);
            curInputFileList = new ArrayList<File>();
            isInputDirAsSearchPoint = mIsInputDirAsSearchPoint;
        }

        Operation_tip_Rule_1() {
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
                itemDesc =   buildTip();

            } else {
                itemDesc = batName.trim() + " " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            }

            return itemDesc;
        }


        String  buildTip(){
            StringBuilder sb = new StringBuilder();
            String LinePre = "\n ________________________________________________ ";
            String LineEnd = " ________________________________________________ \n";


            showMap(propKey2ValueList);



            sb.append(LinePre+"指定单个索引节点 仅执行一个"+LineEnd);
            StringBuilder sb_gupiaoliebiao = new StringBuilder();
            StringBuilder sb_jiaoyirili = new StringBuilder();
            
            
//            sb.append("描述: "+" 依据索引对该索引对应的python文件进行创建 并 执行该python文件    单个文件的更新执行!\n");
//            sb.append(Cur_Bat_Name+"  all &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath() );

            for (int i = 0; i < AllLeafNode.size(); i++) {
                TreeNode single_node = AllLeafNode.get(i);
                int nodeIndex = single_node.blockIndex;  // 1
                String paddingIndexStr= single_node.paddingBlockIndexStr;    // 0001
                String single_block= paddingIndexStr + "_single";
                String desc = getNodeDescWithName(single_node,paddingIndexStr,true);

                if(desc.contains("股票列表") && desc.contains("gupiaoliebiao")) {
             
                	sb_gupiaoliebiao.append(Cur_Bat_Name+ "  "+single_block+" &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr+" && "+" copy  "+ J0_Dir_Path+"股票列表.xlsx  "+zbinPath+File.separator+"J0_股票列表.xlsx  /y ");

                    
                    sb.append(desc+"\n");
                    sb.append(Cur_Bat_Name+ "  "+single_block+" &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr+" && "+" copy  "+ J0_Dir_Path+"股票列表.xlsx  "+zbinPath+File.separator+"J0_股票列表.xlsx /y "+"\n");
                    sb.append("\n");
                    
                }else if(desc.contains("交易日历") && desc.contains("jiaoyirili")) {
                	sb_jiaoyirili.append(Cur_Bat_Name+ "  "+single_block+" &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr +" && "+" copy  "+ J0_Dir_Path+"交易日历.xlsx  "+zbinPath+File.separator+"J0_交易日历.xlsx /y ");

        
                    sb.append(desc+"\n");
                    sb.append(Cur_Bat_Name+ "  "+single_block+" &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr +" && "+" copy  "+ J0_Dir_Path+"交易日历.xlsx  "+zbinPath+File.separator+"J0_交易日历.xlsx  /y "+"\n");
                    sb.append("\n");
                	
                }else {
                    sb.append(desc+"\n");
                    sb.append(Cur_Bat_Name+ "  "+single_block+" &&  " + " "+ J0_call_single_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr+"\n");
                    sb.append("\n");
                	
                }
                
                
                
            }


            sb.append("\n");


            sb.append(LinePre+"指定起始索引节点 数据集合操作"+LineEnd);

            for (int i = 0; i < AllLeafNode.size(); i++) {
                TreeNode single_node = AllLeafNode.get(i);
                int nodeIndex = single_node.blockIndex;  // 1
                String paddingIndexStr= single_node.paddingBlockIndexStr;    // 0001
                String single_block= paddingIndexStr + "_rest";
                String desc = getNodeDescWithName(single_node,paddingIndexStr,false);
                sb.append(desc+"\n");
                sb.append(Cur_Bat_Name+ "  "+single_block+"  &&  " + " "+ J0_call_rest_python_bat_File.getAbsolutePath()  +"  "+ paddingIndexStr+"\n");
                sb.append("\n");
            }


            sb.append("\n");
//
//            sb.append("描述: "+" 按照给定的索引为起始索引 生成 python代码 并 执行 该后续python 代码  多文件的更新执行 \n");
//            sb.append(Cur_Bat_Name+"  all &&  " + " "+ J0_call_rest_python_bat_File.getAbsolutePath() );


            sb.append("\n");

            sb.append(LinePre+"初始化所有数据集合操作"+LineEnd);
            sb.append("描述: "+" 初始化执行所有 python文件的更新 并 按照次序执行 更新后的 python 拉取数据的代码! \n");
            sb.append(Cur_Bat_Name+"  all &&  " + " "+ J0_call_all_python_bat_File.getAbsolutePath() );



            int nowInt =getTodayTradeDay();


//            for (int i = 0; i < SH_TradeDayList.size(); i++) {
//                System.out.println("交易所引:"+ i + "  值:"+  SH_TradeDayList.get(i));
//            }




            int tradeday_1_fromnow =  getPreDayTradeDay(nowInt);
            int tradeday_2_fromnow =  getPreDayTradeDay(tradeday_1_fromnow);
            int tradeday_3_fromnow =  getPreDayTradeDay(tradeday_2_fromnow);
            int tradeday_4_fromnow =  getPreDayTradeDay(tradeday_3_fromnow);
            int tradeday_5_fromnow =  getPreDayTradeDay(tradeday_4_fromnow);
            int tradeday_6_fromnow =  getPreDayTradeDay(tradeday_5_fromnow);
            int tradeday_7_fromnow =  getPreDayTradeDay(tradeday_6_fromnow);
            int tradeday_8_fromnow =  getPreDayTradeDay(tradeday_7_fromnow);
            int tradeday_9_fromnow = getPreDayTradeDay(tradeday_8_fromnow);
            int tradeday_10_fromnow = getPreDayTradeDay(tradeday_9_fromnow);
            int tradeday_11_fromnow = getPreDayTradeDay(tradeday_10_fromnow);
            int tradeday_12_fromnow = getPreDayTradeDay(tradeday_11_fromnow);
            int tradeday_13_fromnow = getPreDayTradeDay(tradeday_12_fromnow);
            int tradeday_14_fromnow = getPreDayTradeDay(tradeday_13_fromnow);
            int tradeday_15_fromnow = getPreDayTradeDay(tradeday_14_fromnow);

            int curYearBeginDay = getNow_YYYY0101();
            int curYearBeginEnd = getNow_YYYY1231();
            int year4int = getTimeStamp_YYYY_IntFlag();
            
            String  nowMonth_End = getTimeStamp_YYYYMMDD();
            String  nowMonth_Begin  = getTimeStamp_YYYYMM()+"01";
            
            String month01_begin = year4int+"0101";
            String month01_end = year4int+"0131";


            String month02_begin = year4int+"0201";
            String month02_end = year4int+"0229";
            
            String month03_begin = year4int+"0301";
            String month03_end = year4int+"0331";
            
            String month04_begin = year4int+"0401";
            String month04_end = year4int+"0431";
            
            
            String month05_begin = year4int+"0501";
            String month05_end = year4int+"0531";
            
            String month06_begin = year4int+"0601";
            String month06_end = year4int+"0631";
            
            
            String month07_begin = year4int+"0701";
            String month07_end = year4int+"0731";
            
            
            String month08_begin = year4int+"0801";
            String month08_end = year4int+"0831";
            
            
            String month09_begin = year4int+"0901";
            String month09_end = year4int+"0931";
            
            
            String month10_begin = year4int+"1001";
            String month10_end = year4int+"1031";
            
            
            String month11_begin = year4int+"1101";
            String month11_end = year4int+"1131";
            
            String month12_begin = year4int+"1201";
            String month12_end = year4int+"1231";
            


            sb.append(LinePre+"day_日期1-日期2 输入1日期范围的命令集合"+LineEnd);
 
            sb.append("\n【一月】"+"  day_"+month01_begin+"-"+month01_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month01_begin+"-"+month01_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month01_begin+"-"+month01_end);

            
            sb.append("\n【二月】"+"  day_"+month02_begin+"-"+month02_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month02_begin+"-"+month02_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month02_begin+"-"+month02_end);

            sb.append("\n【三月】"+"  day_"+month03_begin+"-"+month03_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month03_begin+"-"+month03_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month03_begin+"-"+month03_end);

            sb.append("\n【四月】"+"  day_"+month04_begin+"-"+month04_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month04_begin+"-"+month04_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month04_begin+"-"+month04_end);

            sb.append("\n【五月】"+"  day_"+month05_begin+"-"+month05_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month05_begin+"-"+month05_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month05_begin+"-"+month05_end);

            
            sb.append("\n【六月】"+"  day_"+month06_begin+"-"+month06_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month06_begin+"-"+month06_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month06_begin+"-"+month06_end);

            
            sb.append("\n【七月】"+"  day_"+month07_begin+"-"+month07_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month07_begin+"-"+month07_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month07_begin+"-"+month07_end);

            
            sb.append("\n【八月】"+"  day_"+month08_begin+"-"+month08_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month08_begin+"-"+month08_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month08_begin+"-"+month08_end);

            sb.append("\n【九月】"+"  day_"+month09_begin+"-"+month09_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month09_begin+"-"+month09_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month09_begin+"-"+month09_end);

            
            sb.append("\n【十月】"+"  day_"+month10_begin+"-"+month10_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month10_begin+"-"+month10_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month10_begin+"-"+month10_end);

            
            sb.append("\n【十一月】"+"  day_"+month11_begin+"-"+month11_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month11_begin+"-"+month11_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month11_begin+"-"+month11_end);

            
            sb.append("\n【十二月】"+"  day_"+month12_begin+"-"+month12_end+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+month12_begin+"-"+month12_end+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ month12_begin+"-"+month12_end);

            sb.append("\n【一整年】"+"  day_"+curYearBeginDay+"-"+curYearBeginEnd+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+curYearBeginDay+"-"+curYearBeginEnd+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ curYearBeginDay+"-"+curYearBeginEnd);

            
            sb.append("\n【今年年初("+curYearBeginDay+")到今日("+nowMonth_End+")】"+"  day_"+curYearBeginDay+"-"+nowMonth_End+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+curYearBeginDay+"-"+nowMonth_End+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ curYearBeginDay+"-"+nowMonth_End);

            
            sb.append("\n【今月月初("+nowMonth_Begin+")到今日("+nowMonth_End+")】"+"  day_"+nowMonth_Begin+"-"+nowMonth_End+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+nowMonth_Begin+"-"+nowMonth_End+"   &&  " + " "+ J0_call_rangeday_python_bat_File.getAbsolutePath() + " "+ nowMonth_Begin+"-"+nowMonth_End);

            
            sb.append("\n【日期文件查询 执行指定文件内标记的 tradeday 的执行条目 】"+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  file_"+J0_Dir_Path+getCurrentYear()+"_main_stock.log"+"   &&  " + " "+ J0_call_fileday_python_bat_File.getAbsolutePath() +" "+ J0_Dir_Path+""+getCurrentYear()+"_main_stock.log");

            
            sb.append(LinePre+LineEnd);
            sb.append(LinePre+"day 输入单个日期命令集合"+LineEnd);
            sb.append(LinePre+" 获取最近日期 day 数据 【 day_依赖日期 】"+LineEnd);
            sb.append("\n【上 十五 日】交易日 day query  "+tradeday_15_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_15_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_15_fromnow);


            sb.append("\n【上 十四 日】交易日 day query  "+tradeday_14_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_14_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_14_fromnow);


            sb.append("\n【上 十三 日】交易日 day query  "+tradeday_13_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_13_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_13_fromnow);


            sb.append("\n【上 十二 日】交易日 day query  "+tradeday_12_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_12_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_12_fromnow);


            sb.append("\n【上 十一 日】交易日 day query  "+tradeday_11_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_11_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_11_fromnow);


            sb.append("\n【上 十 日】交易日 day query  "+tradeday_10_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_10_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_10_fromnow);

            sb.append("\n【上 九 日 】交易日 day query "+tradeday_9_fromnow+" 查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_9_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_9_fromnow);

            sb.append("\n【上 八 日】交易日 day query  "+tradeday_8_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_8_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_8_fromnow);

            sb.append("\n【上 七 日】交易日 day query "+tradeday_7_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_7_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_9_fromnow);

            sb.append("\n【上 六 日】交易日 day query "+tradeday_6_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_6_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_6_fromnow);

            sb.append("\n【上 五 日】交易日 day query "+tradeday_5_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_5_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_5_fromnow);

            sb.append("\n【上 四 日】交易日 day query  "+tradeday_4_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_4_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_4_fromnow);

            sb.append("\n【上 三 日】交易日 day query  "+tradeday_3_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_3_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_3_fromnow);

            sb.append("\n【上 两 日】交易日 day query  "+tradeday_2_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_2_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_2_fromnow);


            sb.append("\n【上 一 日】交易日 day query  "+tradeday_1_fromnow+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+tradeday_1_fromnow+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() + "  "+ tradeday_1_fromnow);


            sb.append("\n【最新最近】交易日 day query  "+nowInt+ "查询如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+nowInt+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() +"  "+ nowInt);


            int lastTradeDay = getCurrentYYYYMMDDWith17Point();
            sb.append("\n【最新最近有数据交易日_当日数据当日下午5点更新】交易日 day query  "+lastTradeDay+ "查询并将生成的J0_Data目录文件导入手机命令如下: \n");
            sb.append(Cur_Bat_Name+"  day_"+lastTradeDay+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() +"  "+ lastTradeDay +" && zrule_apply_G2.bat #_38 "+  J0_Dir_Path +"  && "+" adb push  "+J0_Dir_Path+".  /sdcard/zmain/stock/ "+ " \n && "+ " adb push "+zbinPath+File.separator+"J0_股票列表.xlsx  /sdcard/zmain/stock/J0_股票列表.xlsx ");

            
            // 输入的参数有 make_dynamic_batsh 时  需要 动态生成 执行代码 到 目录
            if(is_Make_Dynamic_Bat_SH) {
            	
            	Try_Dynamic_BatSH_Operation( lastTradeDay);
            	
            }
            
            
         //  zrule_apply_G2.bat + J0_Dir_Path
         int now_yyyy0101 =    getNow_YYYY0101();
         if(now_yyyy0101 > nowInt) {
        	 sb.append("\n【请执行以下命令完成 股票列表 交易日丽 的数据更新】 \n");
        	 String refreshTip = sb_jiaoyirili.toString() +" && "+ sb_gupiaoliebiao.toString();
        	 
        	 sb.append(refreshTip.replace("&&", "&& \n "));
         }
//

            return     sb.toString();



        }
        
        void Try_Dynamic_BatSH_Operation(int lastTradeDay) {
//        	dynamicBatShContentList
        	if(CUR_OS_TYPE == OS_TYPE.Windows) {
        		// Bat 的动态写入   等待填充	
        		dynamicBatShContentList.add("@echo off");
        		dynamicBatShContentList.add("Setlocal ENABLEDELAYEDEXPANSION");    		
        		StringBuilder tempSB = new StringBuilder();
        		
        		
        		// 下载当天的 xlsx 文件 并 转为 json  
        		// C:\Users\zhuzj5\Desktop\zbin\win_zbin\zstock_tushare_tool_J0.bat  day_20220224 && 
        		// C:\Users\zhuzj5\Desktop\zbin\J0_DayPython\J0_0000_call_day_python.bat  20220224 && 
        		// C:\Users\zhuzj5\Desktop\zbin\win_zbin\zrule_apply_G2.bat _38  C:\Users\zhuzj5\Desktop\zbin\J0_Data\  

        		tempSB.append(win_zbinPath+File.separator+Cur_Bat_Name+"  day_"+lastTradeDay+" && ");
        		tempSB.append(J0_call_day_python_bat_File.getAbsolutePath()+ "  "+ lastTradeDay +" && ");
        		tempSB.append(win_zbinPath+File.separator+"zrule_apply_G2.bat"+ " _38  "+ J0_Dir_Path +"  && ");
       
        		
        		// zrule_apply_G2.bat #_38 "+  J0_Dir_Path
        		
        		
        		// sb.append(Cur_Bat_Name+"  day_"+lastTradeDay+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() +"  "+ lastTradeDay +" && zrule_apply_G2.bat #_38 "+  J0_Dir_Path +"  && "+" adb push  "+J0_Dir_Path+".  /sdcard/zmain/stock/ "+ " \n && "+ " adb push "+zbinPath+File.separator+"J0_股票列表.xlsx  /sdcard/zmain/stock/J0_股票列表.xlsx ");
// zstock_tushare_tool_J0.bat  day_20220224   &&   C:\Users\zhuzj5\Desktop\zbin\J0_DayPython\J0_0000_call_day_python.bat  20220224 && zrule_apply_G2.bat #_38 C:\Users\zhuzj5\Desktop\zbin\J0_Data\  &&  adb push  C:\Users\zhuzj5\Desktop\zbin\J0_Data\.  /sdcard/zmain/stock/  
//  &&  adb push C:\Users\zhuzj5\Desktop\zbin\J0_股票列表.xlsx  /sdcard/zmain/stock/J0_股票列表.xlsx 
      		  // C:\Users\zhuzj5\Desktop\zbin\win_zbin\zgupiao_analysis_J0.bat  #_1  yyyymmdd_20220224
	 
        		
        		
        		


        		 // 需要去掉 命令中的 #  井号   可能有歧义
        		//    依据当前的 json 文件 创建 对应的  2022_main_stock.xlsx  2023_main_stock.xlsx 文件
//         		StringBuilder createYearMainCommandSB = new StringBuilder();
                String Cur_J0_GuPiao_Analysis_Bat_Name = "zgupiao_analysis_J0"+BAT_OR_SH_Point;
                
         		// zgupiao_analysis_J0.bat  #_1  yyyymmdd_20220303
                tempSB.append(win_zbinPath+File.separator+Cur_J0_GuPiao_Analysis_Bat_Name+"  _1  yyyymmdd_"+lastTradeDay+" && ");

           		
                // 对生成的 day_2022_0630  之类的 文件  进行动态计算  往 json 中 添加 day3 day5  day10 day15 之类的数据 
                //  zdaily_stock_json_operation_J0.bat #_1 day_20220630
                String Cur_J0_Daily_Json_Operation_Bat_Name = "zdaily_stock_json_operation_J0"+BAT_OR_SH_Point;
                tempSB.append(win_zbinPath+File.separator+Cur_J0_Daily_Json_Operation_Bat_Name+"  _1  day_"+lastTradeDay+" ");

                
                
         		dynamicBatShContentList.add(tempSB.toString());
         		
           		
           		
        	}else {
        		// SH 的动态写入   等待填充
        		
        		
        	}
        	
        	System.out.println("写入 dynamic_Bat_SH_File "+dynamic_Bat_SH_File.getAbsolutePath() +"  dynamicBatShContentList.size()="+dynamicBatShContentList.size());
        	for (int i = 0; i < dynamicBatShContentList.size(); i++) {
				String lineItem = dynamicBatShContentList.get(i);
				System.out.println("Dynamic_Line["+i+"] = "+ lineItem);
			}
        	writeContentToFile(dynamic_Bat_SH_File, dynamicBatShContentList);
        	
        	//    sb.append(Cur_Bat_Name+"  day_"+lastTradeDay+"   &&  " + " "+ J0_call_day_python_bat_File.getAbsolutePath() +"  "+ lastTradeDay +" && zrule_apply_G2.bat #_38 "+  J0_Dir_Path +"  && "+" adb push  "+J0_Dir_Path+".  /sdcard/zmain/stock/ "+ " \n && "+ " adb push "+zbinPath+File.separator+"J0_股票列表.xlsx  /sdcard/zmain/stock/J0_股票列表.xlsx ");

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

    public static ArrayList<String> ReadFileContentAsList(File xFilePath) {

        if (xFilePath != null && xFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 " + xFilePath.getAbsolutePath());

            return null;
        }
        ArrayList<String> contentList = new ArrayList<String>();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(xFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null) {
                    continue;
                }

//                if( oldOneLine.trim().isEmpty())

//                sb.append(oldOneLine+"\n");
                contentList.add(oldOneLine);   //  空格也增加
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;

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
                itemDesc = "zrule_apply_J0.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_J0 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc + "\n");
            count++;
        }
        System.out.println("═══════════════════" + "使用方法列表 End " + "═══════════════════" + "\n");

    }



    static int getPythonOperationType(String inputParams) {
        if (inputParams == null) {
            return 0;
        }
//  XXinputParams = file_C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log
        System.out.println(" XXinputParams = "+ inputParams);
        if(inputParams.contains("all")){
            return 1;
        }else if(inputParams.contains("rest")){
            return 2;
        }else if(inputParams.contains("single")){
            return 3;
        }else if(inputParams.contains("day") || inputParams.startsWith("file_") ){
            return 4;
        }


        return 0;
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




    static  int     getNextYearBeginIntFlag(int dayFlagInt){
        String yearStr = (""+dayFlagInt).substring(0,4);
        int year = Integer.parseInt(yearStr) -1;
        String nextYearBegin = year+"0101";
        return Integer.parseInt(nextYearBegin);

    }


    static   int     getYearEndIntFlag(int dayFlagInt){
        String yearStr = (""+dayFlagInt).substring(0,4);
        String dayDesc = yearStr+"1231";
        return Integer.parseInt(dayDesc);

    }



    static   int     getPreYearEndIntFlag(int dayFlagInt){
        String yearStr = (""+dayFlagInt).substring(0,4);
        int Year = Integer.parseInt(yearStr) -1 ;
        String dayDesc = Year+"1231";
        return Integer.parseInt(dayDesc);

    }



    static  int     getPreYearBeginIntFlag(int dayFlagInt){
        String yearStr = (""+dayFlagInt).substring(0,4);
        int Year = Integer.parseInt(yearStr) -1 ;
        String dayDesc = Year+"0101";
        return Integer.parseInt(dayDesc);

    }



    static     int     getYearBeginIntFlag(int dayFlagInt){
        String yearStr = (""+dayFlagInt).substring(0,4);
        String dayDesc = yearStr+"0101";
        return Integer.parseInt(dayDesc);

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





    static int getTimeStamp_YYYYMMDD_BeginIntFlag() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String beginDayDesc = year+"0101";

        return Integer.parseInt(beginDayDesc);
    }


    static int getTimeStamp_YYYYMMDD_EndIntFlag() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String beginDayDesc = year+"1231";

        return Integer.parseInt(beginDayDesc);
    }

    
    static int getNow_YYYY0101() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String date = df.format(new Date());
        return Integer.parseInt(date+"0101");
    }
    

    static int getNow_YYYY1231() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String date = df.format(new Date());
        return Integer.parseInt(date+"1231");
    }
    

    static int getTimeStamp_YYYYMMDD_IntFlag() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(new Date());
        return Integer.parseInt(date);
    }

    static int getTimeStamp_YYYY_IntFlag() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String date = df.format(new Date());
        return Integer.parseInt(date);
    }
    

    static String getTimeStamp_YYYYMM() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static String getTimeStamp_YYYYMMDD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static String getTimeStamp_yyyyMMddHH() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static String getTimeStampLong() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    static void RestoreProp_TreeNode(){
        for (int i = 0; i < AllLeafNode.size(); i++) {
            TreeNode nodeItem = AllLeafNode.get(i);
            nodeItem.RestoreToProp(propKey2ValueList);
        }


    }

    static void InitProp_TreeNode(){
        for (int i = 0; i < AllLeafNode.size(); i++) {
            TreeNode nodeItem = AllLeafNode.get(i);
            System.out.println("InitProp_TreeNode ["+i+"] = "+ nodeItem.nodeName);
            nodeItem.initProp(propKey2ValueList);
        }

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


    // 输入的 PythonType_Input 操作类型
    // 0  默认值   直接打印 tip
    // 1 == all    // 标识当前 执行 所有的 数据操作
    // 2 == rest   //  标识当前 执行指定 索引 及其 后的 数据操作
    // 3== single 标识当前只是单一的索引操作
    //  4== day  或者  file_开头  记载有 日期数据的文件

   static int PythonType_Input = 0 ;



   
   
   
    // 当前 用户选择的 当前的 指定的 索引  默认为 0
    static int PythonItem_Index = 0;


    public static void main(String[] args) {

        initSystemInfo();


        initInputParams(args);




/*
       //  输出 当前股票列表
        for (int i = 0; i < TScode_List.size() ; i++) {
            System.out.println("tscode["+i+"] = "+TScode_List.get(i));
        }
*/


        initTreeData();

        System.out.println("Main_PythonItem_Index = "+ PythonItem_Index);
        System.out.println("Main_PythonType_Input = "+ PythonType_Input);




        if(PythonType_Input != 0){
            initTsCodeList();
        }
        initTradeDayList();  // 打印tip时   检查 交易日期的时候 用到

        SH_Tomorrow_WorkTrade_Day_Int = getTomorrowTradeDay(SH_Last_Trade_Day_Int);


//        J0_TushareTool mJ0_Object = new J0_TushareTool();
        J0_TushareTool.InitRule();

        // 用户没有输入参数
        System.out.println(" CUR_INPUT_3_ParamStrList.size() = "+ CUR_INPUT_3_ParamStrList.size()+"  PythonType_Input="+PythonType_Input);
        if (CUR_INPUT_3_ParamStrList.size() == 0 && PythonType_Input == 0) {
            showTip();
            return;
        }
   

 //   默认的索引同时也被修改  没有获得 当前 适配的规则索引
//        if (CUR_TYPE_INDEX <= 0 || CUR_TYPE_INDEX > CUR_RULE_LIST.size()) {
//            showNoTypeTip(CUR_TYPE_INDEX);
//            return;
//        }

        // 
        mRootqueryItem =  J0_TushareTool.new QueryItem(PythonType_Input,Origin_TYPE_2_ParamsStr);


        

if(mRootqueryItem.day4TimeQueryTradeDayIntList == null ) {   //   如果没有区间查询 那么执行下列 day_20211110-20211210 
    if(!mRootqueryItem.isTradeDate()){
        System.out.println("当前 依据条件环境 选中的 时间 : "+ SH_Last_Trade_Day_Int + " 不是交易日！！ 请重新输入交易日!");
        return;
    }

}



        // 没有参数的执行 那么就 出事啊 所有的 python 类 并给出 调用 所有的python的批处理bat
// 1. 可选 执行  哪一个项的 更新文件   项 是以 block_index 为依据
// 2. only all 分别表示
// only-只执行该次的python代码更新 其后面的python代码不运行
// all-标识 当前代码进行更新 其后代码不更新, 但 python后的代码要一起运行

if(mRootqueryItem.day4TimeQueryTradeDayIntList == null   ) {  
    if(PythonType_Input != 0){
        makePythonFileFromLeafNode(mRootqueryItem);    // 当前创建 python的代码 需要索引来 处理了
    }

}else {
	if(PythonType_Input == 4) {
		for (int i = 0; i < mRootqueryItem.day4TimeQueryTradeDayIntList.size(); i++) {
			int mTradeDayItem =  mRootqueryItem.day4TimeQueryTradeDayIntList.get(i);
			QueryItem 	mQueryItem = J0_TushareTool.new QueryItem(PythonType_Input,mTradeDayItem);
		     makePythonFileFromLeafNode(mQueryItem); 
		}	
		
	}

	
	
}
   





/*

        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则

        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null || !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE, CUR_TYPE_2_ParamsStr, CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }

*/

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


//        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理

        RestoreProp_TreeNode();
        setProperity();
        System.out.println("Main函数执行结束! ");
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


    static ArrayList<ArrayList<String>> getTreeDataBlock(ArrayList<String> fileContentList) {
        ArrayList<ArrayList<String>> blockList = new ArrayList<ArrayList<String>>();
        ArrayList<String> block = new ArrayList<String>();
        for (int i = 0; i < fileContentList.size(); i++) {
            String lineStr = fileContentList.get(i).trim();

            if (lineStr.startsWith("ZENDZ")) {  // ZENDZ 标识 读取 结束
                return blockList;
            } else if ("".equals(lineStr) && block.size() >= 1) {    // 如果读取到 空 行 那么 说明 是一个 Item 项  保存它 起始新的 项

                blockList.add(block);
                block = new ArrayList<String>();


            } else {
                if ("".equals(lineStr)) {
                    continue;
                }
                block.add(lineStr);   // 如果当前 不是空行  block.size() 为0  那么 添加它到 blockList

            }

        }
        return blockList;
    }




    // ArrayList<TreeNode> inputParentNodeList,
    @SuppressWarnings("unchecked")
    TreeNode CreateNode(ArrayList<String> mParamBlock ,int index) {
        TreeNode curNode = null;
        if (mParamBlock == null || mParamBlock.size() == 0) {
            System.out.println("以下输入参数块错误! 请检查");
            System.out.println(mParamBlock);
        } else {
            String firstLine = mParamBlock.get(0).trim();
            if (firstLine.contains("▲")) {
                firstLine = firstLine.substring(firstLine.indexOf("▲"));
                curNode = new RootNode();
                curNode.isRoot = true;
                curNode.isLeaf = false;
                curNode.parentLinkList = null; // 没有父类的集合
                curNode.deepth = 1;
                curNode.nodeName = firstLine.replace("▲", "").trim();
                curNode.inputOrganizationIdentify = curNode.nodeName;
                curNode.childArr = new ArrayList<TreeNode>();
            } else if (firstLine.startsWith("〓")) {
                int count = calculStrCount(firstLine, '〓');
                curNode = new BranchNode();
                curNode.deepth = count;
                curNode.isRoot = false;
                curNode.isLeaf = false;

                String nodeNameStr = firstLine.replace("〓", "").trim();
                while (nodeNameStr.endsWith("_")) {
                    nodeNameStr = nodeNameStr.substring(0, nodeNameStr.length() - 1);
                }
                System.out.println("nodeNameStr = " + nodeNameStr);

                if (nodeNameStr.contains("_")) {
                    curNode.nodeName = nodeNameStr.substring(nodeNameStr.lastIndexOf("_")).trim();
                } else {

                    curNode.nodeName = nodeNameStr;
                }


                curNode.childArr = new ArrayList<TreeNode>();
//                curNode.parentLinkList = inputParentNodeList;
//                System.out.println("BranchNode -> calculStrCount(firstLine,'〓') = " + curNode.deepth);
                curNode.inputOrganizationIdentify = mParamBlock.get(1).trim();
                while (curNode.inputOrganizationIdentify.endsWith("_")) {
                    curNode.inputOrganizationIdentify = curNode.inputOrganizationIdentify.substring(0, curNode.inputOrganizationIdentify.length() - 1);
                }
            } else if (firstLine.startsWith("〖")) {
                curNode = new LeafNode();

                curNode.isRoot = false;
                curNode.isLeaf = true;
                curNode.deepth = -1;   // -1 标识 当前还未识别当前深度
//                curNode.deepth =  parentLinkList.size();  // 深度 通过 后续 计算得到


                for (int i = 0; i < mParamBlock.size(); i++) {

                    String paramItem = mParamBlock.get(i);
                    if (paramItem.startsWith("〖")) {
                        if (paramItem.contains("https://")) {
                            String clearFirstStr = paramItem.replace("〖", "").trim();
                            String webSite = clearChinese(clearFirstStr).trim();
                            if (!webSite.startsWith("https:")) {
                                webSite = webSite.substring(webSite.indexOf("https:"));
                            }
                            String title = clearFirstStr.replace(webSite, "").trim();
                            ((LeafNode) curNode).leaf_web_sit = webSite;
                            ((LeafNode) curNode).leaf_chinese_title = title.replace(webSite, "").trim();

                        } else if (paramItem.indexOf("*") != paramItem.lastIndexOf("*")) {

                            if (((LeafNode) curNode).params_linkedHashMap == null) {
                                ((LeafNode) curNode).params_linkedHashMap = Maps.newLinkedHashMap();
                            }

                            String paramKey = getStrWithPairChar(paramItem, "*").trim();
                            String descValue = paramItem.replace("*", "").replace("〖", "").replace(paramKey, "").replace(" ", "").trim();
                            ((LeafNode) curNode).params_linkedHashMap.put(paramKey, descValue);

                        }


                    } else {   //  是 具体的 LeafNode 的 相关结点信息

                        String inputOrganizationIdent = paramItem.substring(0, paramItem.indexOf("【")).trim();

                        String filedStr = getStrWithPairChar(paramItem, "《", "》");
                        String[] fieldArr = filedStr.split("=");

                        ((LeafNode) curNode).fieldList = new ArrayList<String>();
                        ((LeafNode) curNode).fieldList.addAll(Arrays.asList(fieldArr));
                        curNode.inputOrganizationIdentify = inputOrganizationIdent;
                        curNode.nodeName = curNode.inputOrganizationIdentify.substring(curNode.inputOrganizationIdentify.lastIndexOf("_") + 1);

                        String limitScoreStr = getStrWithPairChar(paramItem, "【", "】");
                        if("".equals(limitScoreStr)){
                            limitScoreStr = "120";
                        }
                        ((LeafNode) curNode).score_permission_point = Integer.parseInt(limitScoreStr);
                        String limitCount = getStrWithPairChar(paramItem, "[", "]").trim();
                        if ("".equals(limitCount)) {
                            ((LeafNode) curNode).onceCallCount = Integer.MAX_VALUE;
                        } else {
                            ((LeafNode) curNode).onceCallCount = Integer.parseInt(limitCount);
                        }




                        String pythonMethod = getStrWithPairChar(paramItem, "<", ">");
                        ((LeafNode) curNode).pythonMethodName = pythonMethod;

                        if (((LeafNode) curNode).methodParams_linkedHashMap == null) {
                            ((LeafNode) curNode).methodParams_linkedHashMap = Maps.newLinkedHashMap();
                        }

                        String call_time_Str = getStrWithPairChar(paramItem, "├", "┤");
                        if ("".equals(call_time_Str)) {
                            ((LeafNode) curNode).callLimit_Num_OneMinutes = Integer.MAX_VALUE;
                        } else {
                            ((LeafNode) curNode).callLimit_Num_OneMinutes = Integer.parseInt(call_time_Str);
                        }



                        String paramMap = getStrWithPairChar(paramItem, "(", ")");
                        ArrayList<String> OneOptionArr;
                        String OneParamNameKey;
                        if (!paramMap.contains("#")) {  // 参数 () 不包含 #   说明只有一个参数
                            if (!paramMap.contains("{")) {
                                OneParamNameKey = paramMap;
                                OneOptionArr = new ArrayList<String>();
                            } else {
                                OneParamNameKey = paramMap.substring(0, paramMap.lastIndexOf("{")).trim();
                                String mPythonOptionArr = getStrWithPairChar(paramMap, "{", "}");
                                if (!mPythonOptionArr.contains(",")) {
                                    OneOptionArr = new ArrayList<String>();
                                    OneOptionArr.add(mPythonOptionArr);
                                } else {
                                    String[] optionValueArr = mPythonOptionArr.split(",");
                                    OneOptionArr = new ArrayList<String>();
                                    OneOptionArr.addAll(Arrays.asList(optionValueArr));

                                }


                            }

                            ((LeafNode) curNode).methodParams_linkedHashMap.put(OneParamNameKey, OneOptionArr);

                        } else {
                            String[] paramArr = paramMap.split("#");

                            for (int j = 0; j < paramArr.length; j++) {
                                String Aparam = paramArr[j];
                                String paramName = "";
                                ArrayList<String> optionArr;
                                if (!Aparam.contains("{")) {
                                    paramName = Aparam;
                                    optionArr = new ArrayList<String>();
                                } else {
                                    paramName = Aparam.substring(0, Aparam.lastIndexOf("{")).trim();
                                    String mPythonOptionArr = getStrWithPairChar(Aparam, "{", "}");
                                    if (!mPythonOptionArr.contains(",")) {
                                        optionArr = new ArrayList<String>();
                                        optionArr.add(mPythonOptionArr);
                                    } else {
                                        String[] optionValueArr = mPythonOptionArr.split(",");
                                        optionArr = new ArrayList<String>();
                                        optionArr.addAll(Arrays.asList(optionValueArr));
                                    }
                                }
                                ((LeafNode) curNode).methodParams_linkedHashMap.put(paramName, optionArr);

                            }

                        }


                    }


                }


            }


        }
        curNode.blockIndex = index;
        curNode.paddingBlockIndexStr =  getPaddingIntString(curNode.blockIndex,4,"0",true);
        AllNodeList.add(curNode);
        return curNode;
    }


    class RootNode extends Common_TreeNode {


    }

    class BranchNode extends Common_TreeNode {


    }


    class LeafNode extends Common_TreeNode {

        // https://tushare.pro/document/2?doc_id=25
        String leaf_web_sit;   // 叶子节点 对应的 网站的位置
        String leaf_chinese_title;  // 股票列表
        Map<String, String> params_linkedHashMap = Maps.newLinkedHashMap();  //  参数列表  key是英文参数 value是中文说明

        String inputIdentify;  // 从 treedata 读取的 标识

        int score_permission_point;  // 积分起始点
        int onceCallCount;  // 一次调用 返回的记录次数
        int callLimit_Num_OneMinutes;   //   每分钟调用的次数
        String pythonMethodName;  // python的函数名称

        // 函数参数  以及参数的可选值  key 是参数名称   value 是可选值范围 ArrayList<String>
        Map<String, ArrayList<String>> methodParams_linkedHashMap;

        ArrayList<String> fieldList;  // 参数的集合

        Map<String,String> python_holder_map;   //  静态的 holderplace



        //   从文件中读取到的循环调用的列表  key 为  Method_Call_Template_1  Method_Call_Template_2 Method_Call_Template_3
        // value 为这个 key 对应的  python 代码的集合   这里包含 循环调用的意味
        Map<String,ArrayList<String>> mMethod_Call_Template_Map;


        // python_holder_map  key 放置 要替换的key   固定 死的
        // 【ZHoldPlace_NodeName】   固定 死的
        // 【ZHoldPlace_pythonMethodName】  固定 死的
        // 【ZHoldPlace_fieldList】    固定 死的
        // 【ZHoldPlace_Now_YYYYMMDD】    固定 死的   程序运行时的 now日期
       // 【ZHoldPlace_J0_Dir_PATH】   J0 目录的Dir位置    固定 死的 J0_Dir_Path
        // 【ZHoldPlace_ZBin_PATH】   J0 目录的Dir位置    固定 死的
// 【ZHoldPlace_Node_Website】    当前的 叶子结点的  对应的网站


        // 【ZHoldPlace_start_date】  【ZHoldPlace_end_date】  动态变化的 调节的
        // 【ZHoldPlace_propKey2ValueList_Index】  每次调用 都不一样的 灵活的
        // 【ZHoldPlace_propKey2ValueList】 每次调用 都不一样的 灵活的
        // 【ZHoldPlace_call_LimitNum_OneMinutes】 当前每分钟调用的限制次数

        String  get_ZHoldPlace_Title(){
            return leaf_chinese_title;
        }

        String  get_ZHoldPlace_pythonMethodName(){
            return pythonMethodName;
        }

        String  get_ZHoldPlace_leaf_chinese_title(){
            return leaf_chinese_title;
        }
        String   get_ZHoldPlace_call_LimitNum_OneMinutes(){
            return ""+callLimit_Num_OneMinutes;
        }


        String  get_ZHoldPlace_fieldList(){
            String fieldResult ="";
            for (int i = 0; i < fieldList.size(); i++) {
                String fieldItem = fieldList.get(i);
                fieldResult = fieldResult + ","+fieldItem;
            }
            fieldResult = fieldResult.trim();
            while(fieldResult.startsWith(",")){
                fieldResult = fieldResult.substring(1);
            }
            return fieldResult;
        }



/*

        // 记录当前最新的交易日  今天是工作日的话 那么就是今天  如果不是交易日 那么就是离他最近的交易日
        static int SH_Last_Trade_Day_Int = getTimeStamp_YYYYMMDD_IntFlag();
        static int  SH_Tomorrow_WorkTrade_Day_Int = SH_Last_Trade_Day_Int+1;


        // 20200101  20201231
        static int SH_Now_Year_BeginTradeDay_IntFlag = getTimeStamp_YYYYMMDD_BeginIntFlag();
        static int SH_Now_Year_EndTradeDay_IntFlag = getTimeStamp_YYYYMMDD_EndIntFlag();

        // 获取一周期
        //            last_trade_date

*/

        //zukgitmap
        void fixed_MapForDaily(QueryItem queryItem){
      
//            SH_Last_Trade_Day_Int    变化 那么其余也变化
              int old_1_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-1);
              int old_7_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,-7);
              int old_30_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-31);
              int old_90_day_intflag    = getFutureDayFlag(SH_Last_Trade_Day_Int,-90);
              int future_1_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,1);
              int future_7_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,7);
              int future_30_day_intflag   = getFutureDayFlag(SH_Last_Trade_Day_Int,31);
              int future_90_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,90);
              int old_yearbegin_day        =  getYearBeginIntFlag(SH_Last_Trade_Day_Int);  // 今年年初 那一天
              int old_preyearend_day      =  getPreYearBeginIntFlag(SH_Last_Trade_Day_Int); // 上一年年末 那一天
              int old_preyearbegin_day       =  getPreYearEndIntFlag(SH_Last_Trade_Day_Int); // 上一年年初 那一天
              int future_yearend_day      =  getYearEndIntFlag(SH_Last_Trade_Day_Int);    //  今年最后 那一天
              int next_yearbegin_day =  getNextYearBeginIntFlag(SH_Last_Trade_Day_Int); // 下一年的开始日期

            python_holder_map.put("J0.properties","J0_Daily.properties");

            python_holder_map.put("【ZHoldPlace_J0_Dir_PATH】",fixedPythonFilePath(queryItem.ZHoldPlace_J0_Dir_PATH()));
            
            // 《Method_Call_Template_1》= 《createexcel('【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.xlsx')》
            //  createexcel('day_2021_0104.xlsx')   
            //  createexcel('day_2021_0104.xlsx')
            // createexcel('moneyflow_20210105.xlsx')
            python_holder_map.put(pythonMethodName+"_【ZHoldPlace_Mstart_date】",queryItem.ZHoldPlace_Title());
            python_holder_map.put(pythonMethodName+"_【ZHoldPlace_Year_Int】",queryItem.ZHoldPlace_Title());
            python_holder_map.put("【ZHoldPlace_Year_Int】",queryItem.ZHoldPlace_Year_Int());
            python_holder_map.put("【ZHoldPlace_Weekly_Day】",queryItem.ZHoldPlace_Weekly_Day());
            python_holder_map.put("【ZHoldPlace_Year_Month】",queryItem.ZHoldPlace_Year_Month());
            python_holder_map.put("_【ZHoldPlace_Month_Index】","_"+queryItem.ZHoldPlace_Month_Index());  // 作为 变量
            python_holder_map.put("'【ZHoldPlace_Month_Index】'"," '"+leaf_chinese_title+"'");  // 作为 sheet 名称
//            python_holder_map.put("'【ZHoldPlace_Month_Index】'"," '"+leaf_chinese_title+"'");  // 作为 sheet 名称


            python_holder_map.put("【ZHoldPlace_EndWorkDay_MonthDay】",leaf_chinese_title);

            python_holder_map.put("【ZHoldPlace_Mstart_date】",queryItem.ZHoldPlace_Mstart_date());
            python_holder_map.put("【ZHoldPlace_day_date】",queryItem.ZHoldPlace_day_date());
//             python_holder_map.put(pythonMethodName+"_"+SH_Last_Trade_Day_Int,queryItem.ZHoldPlace_Title());
       
            
            //   monthly_20210916.   ---->>  day_2021_0104.  这个错误了
//            python_holder_map.put("'"+pythonMethodName+"_"+SH_Last_Trade_Day_Int+".","'"+queryItem.ZHoldPlace_Title()+".");  //  weekly_20201118.columns.values.tolist():
//            python_holder_map.put("\\\\"+pythonMethodName+"_"+SH_Last_Trade_Day_Int+".","\\\\"+queryItem.ZHoldPlace_Title()+"."); //  load_workbook('C:\\Users\\zhuzj5\\Desktop\\zbin\\J0_Data\\monthly_20201118.xlsx')   day_20201118

            python_holder_map.put("'"+pythonMethodName+"_"+queryItem.trade_day+".","'"+queryItem.ZHoldPlace_Title()+".");  //  weekly_20201118.columns.values.tolist():
            python_holder_map.put("\\\\"+pythonMethodName+"_"+queryItem.trade_day+".","\\\\"+queryItem.ZHoldPlace_Title()+"."); //  load_workbook('C:\\Users\\zhuzj5\\Desktop\\zbin\\J0_Data\\monthly_20201118.xlsx')   day_20201118

            
            
            
            System.out.println(pythonMethodName+"_"+queryItem.trade_day+"." +"   ---->>  "+queryItem.ZHoldPlace_Title()+"." );

            python_holder_map.put("【ZHoldPlace_OPERATION_DAY】",queryItem.ZHoldPlace_OPERATION_DAY());
//            python_holder_map.put(pythonMethodName+"_"+getTimeStamp_yyyyMMddHH(),queryItem.ZHoldPlace_Title());
            python_holder_map.put("【ZHoldPlace_DayMonthIndex_Xinqi】",leaf_chinese_title);

            python_holder_map.put("【ZHoldPlace_WeekIndex_DayDesc】",leaf_chinese_title);

            python_holder_map.put("【ZHoldPlace_start_date】",queryItem.start_day);
            python_holder_map.put("【ZHoldPlace_end_date】",queryItem.end_day);
            python_holder_map.put("【ZHoldPlace_Title】",queryItem.ZHoldPlace_Title());

            python_holder_map.put("10ystart_date=''","start_date='"+old_yearbegin_day+"'");
            python_holder_map.put("10yend_date=''","end_date='"+future_yearend_day+"'");

            // start_date='202001017',end_date='20201218',trade_date='20201117' 会导致 读取 服务器数据 失败  for daily
            python_holder_map.put("start_date='',end_date='',trade_date=''",",start_date='',end_date='',trade_date='"+queryItem.start_day+"'");

            python_holder_map.put("trade_date=''","trade_date='"+ queryItem.trade_day+"'");  //    没有 trade_date for daily_basic 会调用失败 缺少必要参数


          if(queryItem.mItemPythonType_Input == 4  ) {
              python_holder_map.put("1mstart_date=''","start_date='"+"'");
              python_holder_map.put("1mend_date=''","end_date='"+"'");
      }else {
          python_holder_map.put("1mstart_date=''","start_date='"+old_30_day_intflag+"'");
          python_holder_map.put("1mend_date=''","end_date='"+future_30_day_intflag+"'");
      }
            



    
            python_holder_map.put("(wtrade_date=''","(trade_date='"+ getNearestFriday(Integer.parseInt(queryItem.trade_day))+"'");  // SH_Last_Trade_Day_Int 获取离当前时间最近的周五
            python_holder_map.put("mtrade_date=''","trade_date='"+getLastTradeDate_In_Month(Integer.parseInt(queryItem.trade_day))+"'");

            

            python_holder_map.put("yeartonow_trade_date=''","trade_date='"+queryItem.start_day+"'");

            
            python_holder_map.put("start_date='"+old_30_day_intflag+"'"+",end_date='"+future_30_day_intflag+"'"+",trade_date=''","start_date='',end_date='',trade_date='"+queryItem.trade_day+"'");
            
//          pro.daily(start_date='20210816',end_date='20211017',trade_date='20210104'
            python_holder_map.put("start_date='"+old_30_day_intflag+"'"+",end_date='"+future_30_day_intflag+"'"+",trade_date='"+queryItem.trade_day+"'","start_date='',end_date='',trade_date='"+queryItem.trade_day+"'");

            System.out.println("ZZstart_date='"+old_30_day_intflag+"'"+",end_date='"+future_30_day_intflag+"'"+",trade_date='"+queryItem.trade_day+"'"+" ===> "+"start_date='',end_date='',trade_date='"+queryItem.trade_day+"'");

//            if(queryItem.mItemPythonType_Input == 4 && queryItem.day4TimeQueryTradeDayIntList != null ) {
//                python_holder_map.put("1mstart_date=''","start_date='"+"'");
//                python_holder_map.put("1mend_date=''","end_date='"+"'");
//            }
            
    
        
        }


        void initHoldeMap(QueryItem queryItem){
            python_holder_map = new HashMap<String,String>();

            // 在  template.py 中的静态变量
            python_holder_map.put("【ZHoldPlace_Title】",get_ZHoldPlace_Title());
            python_holder_map.put("【ZHoldPlace_pythonMethodName】",get_ZHoldPlace_pythonMethodName());
            python_holder_map.put("【ZHoldPlace_fieldList】",get_ZHoldPlace_fieldList());
            python_holder_map.put("【ZHoldPlace_leaf_chinese_title】",get_ZHoldPlace_leaf_chinese_title());
            python_holder_map.put("【ZHoldPlace_call_LimitNum_OneMinutes】",get_ZHoldPlace_call_LimitNum_OneMinutes());
            python_holder_map.put("【ZHoldPlace_NodeName】",nodeName);
            python_holder_map.put("【ZHoldPlace_Now_YYYYMMDD】",SH_Last_Trade_Day_Int+"");
            python_holder_map.put("【ZHoldPlace_J0_Dir_PATH】",fixedPythonFilePath(J0_Dir_Path));
            python_holder_map.put("【ZHoldPlace_Node_Website】",leaf_web_sit);
            python_holder_map.put("【ZHoldPlace_TomorrowDay_YYYYMMDD】",SH_Tomorrow_WorkTrade_Day_Int+"");

            //在 treedata.txt 中的静态变量   固定的 指代当前最新的日期
            python_holder_map.put("last_trade_date=''","trade_date="+"'"+SH_Last_Trade_Day_Int+"'");
            python_holder_map.put("last_start_date=''","start_date="+"'"+SH_Last_Trade_Day_Int+"'");
            python_holder_map.put("last_end_date=''","end_date="+"'"+SH_Last_Trade_Day_Int+"'");

            //            last_trade_date
            int old_1_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,-1);
            int old_7_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,-7);
            int old_30_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,-31);
            int old_90_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,-90);

            int future_1_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,1);
            int future_7_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,7);
            int future_30_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,31);
            int future_90_day_intflag = getFutureDayFlag(SH_Last_Trade_Day_Int,90);


            int old_yearbegin_day =  getYearBeginIntFlag(SH_Last_Trade_Day_Int);  // 今年年初 那一天
            int old_preyearend_day =  getPreYearBeginIntFlag(SH_Last_Trade_Day_Int); // 上一年年末 那一天
            int old_preyearbegin_day =  getPreYearEndIntFlag(SH_Last_Trade_Day_Int); // 上一年年初 那一天
            int future_yearend_day =  getYearEndIntFlag(SH_Last_Trade_Day_Int);    //  今年最后 那一天

            int next_yearbegin_day =  getNextYearBeginIntFlag(SH_Last_Trade_Day_Int); // 下一年的开始日期


            System.out.println("today = "+ SH_Last_Trade_Day_Int + " -7="+old_7_day_intflag+"   -31="+old_30_day_intflag+" -90="+old_90_day_intflag+"  7="+future_7_day_intflag+"  31="+future_30_day_intflag+"   90="+future_90_day_intflag);

            //  当前日志 的  过去 1周  1个月 1个季度 以及未来的  1星期 1个月  1个季度
            python_holder_map.put("onefuture_start_date=''","start_date="+"'"+future_1_day_intflag+"'");
            python_holder_map.put("wfuture_start_date=''","start_date="+"'"+future_7_day_intflag+"'");
            python_holder_map.put("mfuture_start_date=''","start_date="+"'"+future_30_day_intflag+"'");
            python_holder_map.put("sfuture_start_date=''","start_date="+"'"+future_90_day_intflag+"'");
            python_holder_map.put("curyearbegin_start_date=''","start_date="+"'"+old_yearbegin_day+"'");
            python_holder_map.put("curyearend_start_date=''","start_date="+"'"+future_yearend_day+"'");
            python_holder_map.put("nextyearbegin_start_date=''","start_date="+"'"+next_yearbegin_day+"'");
            python_holder_map.put("oneago_start_date=''","start_date="+"'"+ old_1_day_intflag+"'");
            python_holder_map.put("wago_start_date=''","start_date="+"'"+old_7_day_intflag+"'");
            python_holder_map.put("mago_start_date=''","start_date="+"'"+old_30_day_intflag+"'");
            python_holder_map.put("sago_start_date=''","start_date="+"'"+old_90_day_intflag+"'");

            python_holder_map.put("curyearbegin_end_date=''","end_date="+"'"+old_yearbegin_day+"'");
            python_holder_map.put("curyearend_end_date=''","end_date="+"'"+future_yearend_day+"'");
            python_holder_map.put("nextyearbegin_end_date=''","end_date="+"'"+next_yearbegin_day+"'");
            python_holder_map.put("nefuture_end_date=''","end_date="+"'"+future_1_day_intflag+"'");
            python_holder_map.put("wfuture_end_date=''","end_date="+"'"+future_7_day_intflag+"'");
            python_holder_map.put("mfuture_end_date=''","end_date="+"'"+future_30_day_intflag+"'");
            python_holder_map.put("sfuture_end_date=''","end_date="+"'"+future_90_day_intflag+"'");
            python_holder_map.put("oneago_end_date=''","end_date="+"'"+old_1_day_intflag+"'");
            python_holder_map.put("wago_end_date=''","end_date="+"'"+old_7_day_intflag+"'");
            python_holder_map.put("mago_end_date=''","end_date="+"'"+old_30_day_intflag+"'");
            python_holder_map.put("sago_end_date=''","end_date="+"'"+old_90_day_intflag+"'");


            python_holder_map.put("curyearend_trade_date=''","trade_date="+"'"+future_yearend_day+"'");
            python_holder_map.put("curyearbegin_trade_date=''","trade_date="+"'"+old_yearbegin_day+"'");
            python_holder_map.put("nextyearbegin_trade_date=''","trade_date="+"'"+next_yearbegin_day+"'");
            python_holder_map.put("onefuture_trade_date =''","trade_date ="+"'"+future_1_day_intflag+"'");
            python_holder_map.put("wfuture_trade_date =''","trade_date ="+"'"+future_7_day_intflag+"'");
            python_holder_map.put("mfuture_trade_date =''","trade_date ="+"'"+future_30_day_intflag+"'");
            python_holder_map.put("sfuture_trade_date =''","trade_date ="+"'"+future_90_day_intflag+"'");
            python_holder_map.put("oneago_trade_date =''","trade_date ="+"'"+old_1_day_intflag+"'");
            python_holder_map.put("wago_trade_date =''","trade_date ="+"'"+old_7_day_intflag+"'");
            python_holder_map.put("mago_trade_date =''","trade_date ="+"'"+old_30_day_intflag+"'");
            python_holder_map.put("sago_trade_date =''","trade_date ="+"'"+old_90_day_intflag+"'");


            // 【ZHoldPlace_J0_Dir_PATH】   J0 目录的Dir位置    固定 死的 J0_Dir_Path



            // 【ZHoldPlace_propKey2ValueList_Index】  每次调用 都不一样的 灵活的
            // 【ZHoldPlace_propKey2ValueList】 每次调用 都不一样的 灵活的
        // 【ZHoldPlace_DayMonthIndex_Xinqi】    每个月的月   几号_星期  1_1   2_3
            // 【ZHoldPlace_DayIndex】    每个月的 好数
        }




     String   fixedPythonFilePath(String path){
            return path.replace(File.separator,File.separator+File.separator);
        }
        // 方法描述:void getKeyAndValue(Map<String,String> mMapParam) 迭代Map的Key和Value(通过 Iterator) //
        @SuppressWarnings("unchecked")
        String HolderReplaceOperation_Static(String codeStr){
            String curCode = codeStr;
            Map.Entry<String , String> entryItem;
            if(python_holder_map != null){
                Iterator iterator = python_holder_map.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , String>) iterator.next();
//                    System.out.println("entryItem.getKey() = "+ entryItem.getKey() + "  entryItem.getValue() = "+ entryItem.getValue() );
                    curCode =  curCode.replace(entryItem.getKey(),entryItem.getValue());
                }
            }
            return curCode;
        }




        String First_Holder_Tag = "First_Define_Template_";
        String Method_Holder_Tag = "Method_Call_Template_";
        String Tail_Holder_Tag = "Tail_Define_Template_";

        String getBlockValueFromTemplate(String str){
            String resultStr = str;
            if(!str.contains("=") || !str.contains("《")  ||  !str.contains("》")  ){
                System.out.println("当前的 模板语句 存在错误!  resultStr = "+ resultStr);
                return str;
            }
            String endStr = resultStr.substring(resultStr.indexOf("="));
            resultStr =  getStrWithPairChar(endStr,"《","》");
            return resultStr;
        }


        ArrayList<String>     getTemplateValue(ArrayList<String> templateStrList){
            ArrayList<String> valueList = new  ArrayList<String>();

            for (int i = 0; i < templateStrList.size() ; i++) {
                String oneLine = templateStrList.get(i);
                valueList.add(getBlockValueFromTemplate(oneLine));
            }

            return valueList;
        }


        Map<String,ArrayList<String>>   calculTemplateMap(ArrayList<String> headTemplateList ,ArrayList<String>   bodyTemplateList) {



            ArrayList<String> allCodeList = new   ArrayList<String>();
            if(headTemplateList != null){
                allCodeList.addAll(headTemplateList);
            }

            if(bodyTemplateList != null){
                allCodeList.addAll(bodyTemplateList);
            }

            Map<String,ArrayList<String>>  curTemplateMap =Maps.newLinkedHashMap();

            ArrayList<String> firstDefineList = new   ArrayList<String>();
            ArrayList<String> methodCallList = new   ArrayList<String>();
            ArrayList<String> tailDefineList = new   ArrayList<String>();

            for (int i = 0; i <allCodeList.size() ; i++) {
                String codeStr = allCodeList.get(i);
                System.out.println("codeStr [ "+i+"] = "+ codeStr);
                if(codeStr.contains(First_Holder_Tag)){
                    firstDefineList.add(codeStr);
                }else if(codeStr.contains(Method_Holder_Tag)){
                    methodCallList.add(codeStr);

                }else if(codeStr.contains(Tail_Holder_Tag)){
                    tailDefineList.add(codeStr);
                }
            }
            curTemplateMap.put(First_Holder_Tag,getTemplateValue(firstDefineList));
            curTemplateMap.put(Method_Holder_Tag,getTemplateValue(methodCallList));
            curTemplateMap.put(Tail_Holder_Tag,getTemplateValue(tailDefineList));
            initCallMap(methodCallList);

            return curTemplateMap;
        }

      void initCallMap( ArrayList<String> methodCallList ){

          mMethod_Call_Template_Map = new HashMap<String,ArrayList<String>>();

          for (int i = 0; i < methodCallList.size(); i++) {
              String cycleCallCodeItem = methodCallList.get(i);
              String mapFlagKey = calculMapKey(cycleCallCodeItem);
              String mapFlagValue = calculMapValue(cycleCallCodeItem);
              if(mMethod_Call_Template_Map.get(mapFlagKey) == null){
                  ArrayList<String>  keyList = new      ArrayList<String>();
                  mMethod_Call_Template_Map.put(mapFlagKey,keyList);
              }
              ArrayList<String> Key_Match_List = mMethod_Call_Template_Map.get(mapFlagKey);
              if(!Key_Match_List.contains(mapFlagValue)){
                  Key_Match_List.add(mapFlagValue);
              }
          }
          System.out.println("  ============   mMethod_Call_Template_Map "+nodeName+" ============ ");
          showCycleMEthodCallMap(mMethod_Call_Template_Map);

        }
        // 返回数值1 数值2  数值3 这样 就能区分了    没有解析出数值 那么返回 空字符串 作为 key
        String calculMapKey(String cycleCallCodeItem){
            // #  《Method_Call_Template_1》= 《creat
            String pre = cycleCallCodeItem.substring(0,cycleCallCodeItem.indexOf("="));
            String flagStr = getStrWithPairChar(pre,"《","》");
            flagStr = flagStr.replace(Method_Holder_Tag,"");
            if(isNumeric(flagStr)){
                return flagStr;
            }
            return "";
        }


        String calculMapValue(String cycleCallCodeItem){
            // #  《Method_Call_Template_1》= 《creat
            String pre = cycleCallCodeItem.substring(cycleCallCodeItem.indexOf("=")+1);
            String flagStr = getStrWithPairChar(pre,"《","》");
            return flagStr;
        }



        boolean isOperationType5_Mtrade_date(ArrayList<String> fieldParamList){
            boolean weekTrade = false;
            for (int i = 0; i < fieldParamList.size(); i++) {
                String itemStr =fieldParamList.get(i);
                if(itemStr.contains("mtrade_date")){
                    weekTrade = true;
                    return weekTrade;
                }
            }
            return weekTrade;

        }

boolean isOperationType4_Wtrade_date(ArrayList<String> fieldParamList){
    boolean weekTrade = false;
    for (int i = 0; i < fieldParamList.size(); i++) {
        String itemStr =fieldParamList.get(i);
        if(itemStr.contains("wtrade_date")){
            weekTrade = true;
            return weekTrade;
        }
    }
    return weekTrade;

}

        //            ts_code='000001.SZ', start_date='', end_date=datestr
        @SuppressWarnings("unchecked")
        int  isOperationType3_Xmstart_date( ArrayList<String> fieldParamList  ){
            int resultSpaceMonth = 0;
            for (int i = 0; i < fieldParamList.size(); i++) {
                String itemStr =fieldParamList.get(i);
                if(itemStr.contains("mstart_date")){
                    String tempStr = itemStr.replace("mstart_date","");
                    if(isNumeric(tempStr)){
                        resultSpaceMonth = Math.abs(Integer.parseInt(tempStr));
                    }else{
                        resultSpaceMonth = 1;  //  默认当前 month 间距为 1
                    }
                }
            }
            return resultSpaceMonth;
        }



        @SuppressWarnings("unchecked")
        ArrayList<String>   pyhtonDailyTemplate(ArrayList<String> headTemplateList ,ArrayList<String>   bodyTemplateList ,QueryItem queryItem){
            ArrayList<String> pythonBodyCodeList = new   ArrayList<String>();
            initHoldeMap(queryItem);
            fixed_MapForDaily(queryItem);
            if(queryItem.mItemPythonType_Input == 4){  // daily_query


                Map<String,ArrayList<String>> mTemplate = calculTemplateMap(headTemplateList,bodyTemplateList);

                ArrayList<String> firstList  = mTemplate.get(First_Holder_Tag);
                System.out.println("firstList.size = "+ firstList.size());

                ArrayList<String> firstList_Code = new  ArrayList<String>();
                for (int i = 0; i < firstList.size() ; i++) {
                    String oneCode = firstList.get(i);
                    System.out.println("First_ori_Code["+i+"]  = "+ oneCode);
                    String fixedCode = HolderReplaceOperation_Static(oneCode);
                    System.out.println("First_ori_Code-> fixedCode ["+i+"]  = "+ fixedCode);
                    if(fixedCode.contains("【ZHoldPlace_")){
                        System.out.println("当前 字符串还存在没有替换的 占位符  fixedCode = "+ fixedCode + "  fixedCode = "+ fixedCode);
                    }
                    firstList_Code.add(fixedCode);
                }



                ArrayList<String> tailList  = mTemplate.get(Tail_Holder_Tag);
                System.out.println("tailList.size = "+ tailList.size());


                ArrayList<String> tailList_Code = new  ArrayList<String>();
                for (int i = 0; i < tailList.size() ; i++) {
                    String oneCode = tailList.get(i);
//                System.out.println("Tail_ori_Code["+i+"]  = "+ oneCode);
                    String fixedCode = HolderReplaceOperation_Static(oneCode);
                    if(fixedCode.contains("【ZHoldPlace_")){
                        System.out.println("当前 字符串还存在没有替换的 占位符  fixedCode = "+ fixedCode + "  fixedCode = "+ fixedCode);
                    }
                    tailList_Code.add(fixedCode);
                }


                ArrayList<String> fieldParamList =      calculInputParamList();


                ArrayList<String> callMethodTemplateList  = mTemplate.get(Method_Holder_Tag);
                System.out.println("callMethodTemplateList.size = "+ callMethodTemplateList.size());
                ArrayList<String> python_method_call_List_Code = new  ArrayList<String>();

                for (int i = 0; i < fieldParamList.size() ; i++) {
                    String fieldItem = fieldParamList.get(i);
                    for (int j = 0; j < callMethodTemplateList.size(); j++) {
                        String methodText = callMethodTemplateList.get(j);
                        String fillText =  HolderReplaceOperation_Static(methodText);
                        // get  定义接收变量的名称
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                            String defineVariable = pythonMethodName+"_item_"+i;
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                        }
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",fieldItem);
                        }

                        fillText =  HolderReplaceOperation_Static(fillText);
                        System.out.println("Body_ori_Code["+i+"]  = "+ fillText);
                        python_method_call_List_Code.add(fillText);

                    }
                    python_method_call_List_Code.add("\n");
                }

                return   showBodyPythonCode( null,firstList_Code,python_method_call_List_Code,tailList_Code);


            }


            return pythonBodyCodeList;
        }

        @SuppressWarnings("unchecked")
        ArrayList<String>   pyhtonTemplate(ArrayList<String> headTemplateList ,ArrayList<String>   bodyTemplateList ,int InputPythonType ,QueryItem curQueryItem){

            initHoldeMap(curQueryItem);
            ArrayList<String> pythonCodeList = new   ArrayList<String>();

            Map<String,ArrayList<String>> mTemplate = calculTemplateMap(headTemplateList,bodyTemplateList);


            ArrayList<String> firstList  = mTemplate.get(First_Holder_Tag);
            System.out.println("firstList.size = "+ firstList.size());

            ArrayList<String> firstList_Code = new  ArrayList<String>();
            for (int i = 0; i < firstList.size() ; i++) {
                String oneCode = firstList.get(i);
                System.out.println("First_ori_Code["+i+"]  = "+ oneCode);
                String fixedCode = HolderReplaceOperation_Static(oneCode);
                System.out.println("First_ori_Code-> fixedCode ["+i+"]  = "+ fixedCode);
                if(fixedCode.contains("【ZHoldPlace_")){
                    System.out.println("当前 字符串还存在没有替换的 占位符  fixedCode = "+ fixedCode + "  fixedCode = "+ fixedCode);
                }
                firstList_Code.add(fixedCode);
            }


            ArrayList<String> tailList  = mTemplate.get(Tail_Holder_Tag);
            System.out.println("tailList.size = "+ tailList.size());


            ArrayList<String> tailList_Code = new  ArrayList<String>();
            for (int i = 0; i < tailList.size() ; i++) {
                String oneCode = tailList.get(i);
//                System.out.println("Tail_ori_Code["+i+"]  = "+ oneCode);
                String fixedCode = HolderReplaceOperation_Static(oneCode);
                if(fixedCode.contains("【ZHoldPlace_")){
                    System.out.println("当前 字符串还存在没有替换的 占位符  fixedCode = "+ fixedCode + "  fixedCode = "+ fixedCode);
                }
                tailList_Code.add(fixedCode);
            }

            //  默认0  可选参数的所有组合形式
            //  1: 有一个 yts_code  通过 ts_code 多输入来查询  4000多个按x个为一组进行访问
            // 2:  有一个 ystart_date  10ystart_date 或者一个  yend_date   通过x 年份间隔 实现 按时间间隔查询
          // 3. 以 (1mstart_date#1mend_date#1dtrade_date) rixianhangqingtime  以每个月为  【日线行情】
          // 3.1  以月份为分隔  201001  201012  202008 这样的时间分类   把当前日期 每月中包含每天的sheet  sheet的内容是当天的日志信息
          // 4. 以(wtrade_date)   标识把当前的以 从 zhouxianhangqingtime_record_date=20101101 为起点 计算 每年 到现在的每个周五的集合
            // 5. 月线行情 (mtrade_date)
            //6. 最新复现因子  (last_trade_date)
            // 7. 沪深股通十大成交股 (yeartonow_trade_date)
           int  operation_type  = 0;    // zukgit    //  当前处理的类型分类


//            ts_code='000001.SZ', start_date='', end_date=datestr
            ArrayList<String> fieldParamList =      calculInputParamList();

            System.out.println("fieldParamList.size = "+ fieldParamList.size() + "fieldParamList.get(0) = "+ fieldParamList.get(0));
            String paramXcode = "";
            int x_tscode_num = x_tscode_num_default;  // 默认是 50
            int x_yearspace_num = default_space_year;

            int m_operatype_3_month_space = 0;  // type为3 时 标识的当前的时间 月份 间隔

            if(fieldParamList.size() ==1 && fieldParamList.get(0).contains("xts_code")   ){
                paramXcode =  fieldParamList.get(0);
                if(paramXcode.contains(",")){
                    String[] paramsArr =   paramXcode.split(",");
                    for (int i = 0; i < paramsArr.length; i++) {
                        if(paramsArr[i].contains("xts_code")){
                            String numStr = paramsArr[i].trim().replace("=","").replace("xtscode","").replace("'","");
                            if("".endsWith(numStr)){
                                x_tscode_num = x_tscode_num_default;
                            }else{

                                //  1xts_code='',start_date='20100101',curyearend_end_date=''
                                String xts_code_str = getXtsCode(numStr);
                                System.out.println("numStr = "+ xts_code_str);
                                x_tscode_num = Integer.parseInt(xts_code_str);
                            }
                            break;
                        }
                    }
                }else{
                    String numStr = paramXcode.replace("=","").replace("xts_code","").replace("'","");
                    if("".endsWith(numStr)){
                        x_tscode_num = x_tscode_num_default;
                    }else{

                        x_tscode_num = Integer.parseInt(numStr);
                    }

                }
                operation_type = 1;
            }else if(fieldParamList.size() ==1 && fieldParamList.get(0).contains("ystart_date")){
                paramXcode =  fieldParamList.get(0);
                if(paramXcode.contains(",")){
                    String[] paramsArr =   paramXcode.split(",");
                    for (int i = 0; i < paramsArr.length; i++) {
                        if(paramsArr[i].contains("ystart_date")){
                            String numStr = paramsArr[i].trim().replace("=","").replace("ystart_date","").replace("'","");
                            if("".endsWith(numStr)){
                                x_yearspace_num = default_space_year;
                            }else if(isNumeric(numStr)){
                                x_yearspace_num = Integer.parseInt(numStr);
                            }
                            break;
                        }
                    }
                }else{
                    String numStr = paramXcode.replace("=","").replace("ystart_date","").replace("'","");
                    if("".endsWith(numStr)){
                        x_yearspace_num = default_space_year;
                    }else if(isNumeric(numStr)){
                        x_yearspace_num = Integer.parseInt(numStr);
                    }
                }
                operation_type = 2;
            }else if(fieldParamList.size() ==1 && (m_operatype_3_month_space = isOperationType3_Xmstart_date(fieldParamList)) != 0){
                operation_type = 3;
            }else if(fieldParamList.size() ==1 && isOperationType4_Wtrade_date(fieldParamList)){  // 周线
                operation_type = 4;
            }else if(fieldParamList.size() ==1 && isOperationType5_Mtrade_date(fieldParamList)){ // 月线
                operation_type = 5;
                paramXcode =  fieldParamList.get(0);
            }else if(fieldParamList.size() ==1 && fieldParamList.get(0).contains("last_trade_date")){ // 复权因子
                operation_type = 6;
                paramXcode =  fieldParamList.get(0);
            }else if(fieldParamList.size() ==1 && fieldParamList.get(0).contains("yeartonow_trade_date")){ // 复权因子
                operation_type = 7;
                paramXcode =  fieldParamList.get(0);
            }

            // 50xtscode 处理 地方 在这里


            ArrayList<String> callMethodTemplateList  = mTemplate.get(Method_Holder_Tag);
            System.out.println("callMethodTemplateList.size = "+ callMethodTemplateList.size());
            ArrayList<String> python_method_call_List_Code = new  ArrayList<String>();


            System.out.println("operation_type = "+ operation_type);
            if(operation_type == 0){

                for (int i = 0; i < fieldParamList.size() ; i++) {
                    String fieldItem = fieldParamList.get(i);
                    for (int j = 0; j < callMethodTemplateList.size(); j++) {
                        String methodText = callMethodTemplateList.get(j);
                        String fillText =  HolderReplaceOperation_Static(methodText);
                        // get  定义接收变量的名称
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                            String defineVariable = pythonMethodName+"_item_"+i;
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                        }
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",fieldItem);
                        }

                        fillText =  HolderReplaceOperation_Static(fillText);
                        System.out.println("Body_ori_Code["+i+"]  = "+ fillText);
                        python_method_call_List_Code.add(fillText);

                    }
                    python_method_call_List_Code.add("\n");
                }


            } else if(operation_type == 1){
                System.out.println("x_tscode_num = "+ x_tscode_num);
                ArrayList<String> xcodeParams = calcul_XCode_List_Params(x_tscode_num);

                String cur_operation_tscode = getPropValue("cur_tscode");  // peop 中记录的record的日期 20200707
                if(cur_operation_tscode == null || "".equals(cur_operation_tscode)){
                    cur_operation_tscode = "";
                }

boolean isBegin = ( null == cur_operation_tscode || "".equals(cur_operation_tscode) )? true:false;
                for (int i = 0; i < xcodeParams.size(); i++) {
                    String paramValueItem = xcodeParams.get(i);
                    if("".equals(paramValueItem)){
                        continue;
                    }
                    if(!isBegin && paramValueItem.equals(cur_operation_tscode)){
                        isBegin = true;
                        continue;
                    }
                    if(!isBegin){
                        continue;
                    }
                    String paramKey =   paramXcode.replace(x_tscode_num+"xts_code","ts_code");
                    paramKey = paramKey.replace("xts_code","ts_code");
                    String fieldItem = paramKey.replace("ts_code=''","ts_code='"+paramValueItem+"'");

                    for (int j = 0; j < callMethodTemplateList.size(); j++) {
                        String methodText = callMethodTemplateList.get(j);
                        String fillText =  HolderReplaceOperation_Static(methodText);
                        // get  定义接收变量的名称
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                            String defineVariable = pythonMethodName+"_item_"+i;
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                        }
                        if(fillText.contains("【ZHoldPlace_CUR_TSCODE】")){
                            String defineVariable = paramValueItem;
                            fillText =     fillText.replace("【ZHoldPlace_CUR_TSCODE】",defineVariable);
                        }

                        if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",fieldItem);
                        }

                        if(fillText.contains(x_tscode_num+"xts_code=''")){
                            fillText =     fillText.replace(x_tscode_num+"xts_code=''","ts_code='"+fieldItem+"'");
                        }else if(fillText.contains("xts_code=''")){
                            fillText =     fillText.replace("ts_code=''","ts_code='"+fieldItem+"'");
                        }
                         fillText =  HolderReplaceOperation_Static(fillText);
                        python_method_call_List_Code.add(fillText);

                    }
                    python_method_call_List_Code.add("\n");

                }

            }else if(operation_type == 2){
                System.out.println("x_tscode_num x_yearspace_num = "+ x_yearspace_num);
                ArrayList<ArrayList<String>> dateArrList  = init_Year_Pair_List(x_yearspace_num);

                for (int i = 0; i < dateArrList.size(); i++) {
                    ArrayList<String> begin_end_List = dateArrList.get(i);
                    String beginDateStr = begin_end_List.get(0);
                    String endDateStr = begin_end_List.get(1);
                    System.out.println("Date[ "+i+"]  beginDateStr = "+ beginDateStr + "   endDateStr = "+ endDateStr);
                    String paramKey =   paramXcode.replace(x_yearspace_num+"ystart_date","start_date");
                    paramKey =   paramKey.replace(x_yearspace_num+"yend_date","end_date");
                    String fieldItem = paramKey.replace("start_date=''","start_date='"+beginDateStr+"'");
                    fieldItem = fieldItem.replace("end_date=''","end_date='"+endDateStr+"'");

                    for (int j = 0; j < callMethodTemplateList.size(); j++) {
                        String methodText = callMethodTemplateList.get(j);
                        String fillText =  HolderReplaceOperation_Static(methodText);
                        // get  定义接收变量的名称
                        if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                            String defineVariable = pythonMethodName+"_item_"+i;
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                        }

                        if(fillText.contains("【ZHoldPlace_start_date】")){
                            fillText =     fillText.replace("【ZHoldPlace_start_date】",beginDateStr);
                        }
                        if(fillText.contains("【ZHoldPlace_end_date】")){
                            fillText =     fillText.replace("【ZHoldPlace_end_date】",endDateStr);
                        }



                        if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                            fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",fieldItem);
                        }

                        if(fillText.contains(x_yearspace_num+"ystart_date=''")){
                            fillText =     fillText.replace(x_yearspace_num+"xts_code=''","start_date='"+beginDateStr+"'");
                        }else if(fillText.contains("ystart_date=''")){
                            fillText =     fillText.replace("ystart_date=''","start_date='"+beginDateStr+"'");
                        }

                        if(fillText.contains(x_yearspace_num+"yend_date=''")){
                            fillText =     fillText.replace(x_yearspace_num+"yend_date=''","end_date='"+endDateStr+"'");
                        }else if(fillText.contains("yend_date=''")){
                            fillText =     fillText.replace("yend_date=''","end_date='"+endDateStr+"'");
                        }
                        python_method_call_List_Code.add(fillText);
                    }
                    python_method_call_List_Code.add("\n");



                }


            }else if(operation_type == 3){

//                =======Method_Call_Template_1 Begin =======
//                key = Method_Call_Template_1   value[0] = createexcel('【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.xlsx')
//                key = Method_Call_Template_1   value[1] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_book = load_workbook('【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.xlsx')
//                key = Method_Call_Template_1   value[2] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_excel_writer = pd.ExcelWriter('【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.xlsx', engine='openpyxl')
//                key = Method_Call_Template_1   value[3] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_excel_writer.book = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_book
//                        key = Method_Call_Template_1   value[4] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_excel_writer.sheets = dict((ws.title, ws) for ws in 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_book.worksheets)
//=======Method_Call_Template_1 End =======
//=======Method_Call_Template_2 Begin =======
//                key = Method_Call_Template_2   value[0] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】 = pro.【ZHoldPlace_pythonMethodName】(【ZHoldPlace_propKey2ValueList】, fields='【ZHoldPlace_fieldList】')
//                key = Method_Call_Template_2   value[1] = print("【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】 返回数据 row 行数 = "+str(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.shape[0]))
//                key = Method_Call_Template_2   value[2] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】.to_excel(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_excel_writer,'【ZHoldPlace_day_date】',index=False)
//                key = Method_Call_Template_2   value[3] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Mstart_date】_excel_writer.save()
//                        =======Method_Call_Template_2 End =======

                int month_space = m_operatype_3_month_space;  //  从 j0_treedata.txt  获取到的月份间隔
                paramXcode =  fieldParamList.get(0);
                // 1mstart_date#1mend_date#1dtrade_date
                String propStartDay = getPropValue("start_date");   // prop 中设置的 起始日期 20010101
                String propRecordDay = getPropValue("record_date");  // peop 中记录的record的日期 20200707
                if(propRecordDay == null || "".equals(propRecordDay)){
                    propRecordDay = "20100101";
                }
                if(propStartDay == null || "".equals(propStartDay)){
                    propStartDay = "20100101";
                }

//                ArrayList<Integer> calculMonthArr = calculMonSpace(propRecordDay,month_space);
                //  计算从 record_date 到 现在这个时间点的所有月份 间隔为 month_space
                ArrayList<Integer> calculMonthArr = calculMonSpaceIntArr(propRecordDay,month_space);

                int nowIntFlag = Integer.parseInt(getTimeStamp_YYYYMMDD());
                for (int i = 0; i < calculMonthArr.size(); i++) {
                    int curYear_Month = calculMonthArr.get(i);
                    String yearStr = (""+curYear_Month).substring(0,4);
                    String monthStr = (""+curYear_Month).substring(4);
                    int yearInt = Integer.parseInt(yearStr);
                    int monthInt = Integer.parseInt(monthStr);
                    int days_month = getDayForMonth_Year(yearInt,monthInt);
                    System.out.println("============="+yearStr+"   "+monthStr+"   "+days_month);

                  ArrayList<String> code_template_1_List =   mMethod_Call_Template_Map.get("1");
                    ArrayList<String> code_template_2_List =   mMethod_Call_Template_Map.get("2");
                  ArrayList<String> headCodeList = new  ArrayList<String>();
                    for (int j = 0; j < code_template_1_List.size(); j++) {
                        String headCodeItem  = code_template_1_List.get(j);
                        String fixed_headCodeItem =  HolderReplaceOperation_Static(headCodeItem);
                        if(fixed_headCodeItem.contains("【ZHoldPlace_Mstart_date】")){
                            // fixed_headCodeItem
                            fixed_headCodeItem = fixed_headCodeItem.replace("【ZHoldPlace_Mstart_date】",""+curYear_Month);
                        }
                        python_method_call_List_Code.add(fixed_headCodeItem);
                    }

                    for (int j = 0; j < days_month; j++) {
                        int day_index = j+1;
                        String daystr =  day_index>=10?day_index+"":"0"+day_index;


//                        System.out.println("yearStr = "+yearStr+"  monthInt =  "+monthStr+"   day_index = "+day_index +" 星期:"+calculXinQi2Chinese(yearInt,monthInt,day_index));

                        String daydesc = ""+curYear_Month+daystr;
                        int itemDay = Integer.parseInt(daydesc);
                        // 当前的调用日期 超过 今天   或者调用日期是 非交易日  那么 就不调用这个 日期
                        if(itemDay > nowIntFlag || SH_No_TradeDayList.contains(itemDay)){
                            continue;
                        }
                        SH_TradeDayList_FromNow.add(itemDay);
                        if(itemDay >= SH_Now_Year_BeginTradeDay_IntFlag && itemDay <= SH_Now_Year_EndTradeDay_IntFlag){
                            SH_Now_Year_TradeDayList_FromNow.add(itemDay);
                        }
//                        System.out.println("days["+day_index+"] = "+daydesc);
                        for (int k = 0; k < code_template_2_List.size(); k++) {
                            String recyleCodeItem = code_template_2_List.get(k);
                            String fixed_recyleCodeItem =  HolderReplaceOperation_Static(recyleCodeItem);
                            if(fixed_recyleCodeItem.contains("【ZHoldPlace_Mstart_date】")){
                                // fixed_headCodeItem
                                fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Mstart_date】",""+curYear_Month);
                            }
                            if(fixed_recyleCodeItem.contains("【ZHoldPlace_day_date】")){
                                // fixed_headCodeItem
                                fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_day_date】",""+daydesc);
                            }

                            if(fixed_recyleCodeItem.contains("【ZHoldPlace_propKey2ValueList】")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("【ZHoldPlace_propKey2ValueList】",paramXcode);
                            }

                            if(fixed_recyleCodeItem.contains("【ZHoldPlace_DayIndex】")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("【ZHoldPlace_DayIndex】",day_index+"");
                            }

                            if(fixed_recyleCodeItem.contains("【ZHoldPlace_DayMonthIndex_Xinqi】")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("【ZHoldPlace_DayMonthIndex_Xinqi】",day_index+"_"+calculXinQi2Chinese(yearInt,monthInt,day_index)+"");
                                System.out.println("day_index_A2 = "+ day_index);
                            }


                            if(fixed_recyleCodeItem.contains(month_space+"mstart_date")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace(month_space+"mstart_date","start_date");
                            }
                            if(fixed_recyleCodeItem.contains(month_space+"mend_date")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace(month_space+"mend_date","end_date");
                            }

                            if(fixed_recyleCodeItem.contains("trade_date=''")){
                                fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("trade_date=''","trade_date='"+daydesc+"'");
                            }


                            python_method_call_List_Code.add(fixed_recyleCodeItem);

                            // 1mstart_date#1mend_date#1dtrade_date

                        }


                    }
                    python_method_call_List_Code.add("\n");
                    python_method_call_List_Code.add("\n");
                }


            }else if(operation_type == 4){  //  周线行情 处理
                paramXcode =  fieldParamList.get(0);
                // 1mstart_date#1mend_date#1dtrade_date
                String propStartDay = getPropValue("start_date");   // prop 中设置的 起始日期 20010101
                String propRecordDay = getPropValue("record_date");  // peop 中记录的record的日期 20200707
                if(propRecordDay == null || "".equals(propRecordDay)){
                    propRecordDay = "20100101";
                }
                if(propStartDay == null || "".equals(propStartDay)){
                    propStartDay = "20100101";
                }
                // 201001----201002-----201012
                ArrayList<Integer> calculMonthArr = calculMonSpaceIntArr(propRecordDay,1);

                int recordDayFlag = Integer.parseInt(propRecordDay);

                ArrayList<Integer> fridayIntList = new  ArrayList<Integer>();
                //  获取当前的时间戳
                int nowIntFlag = Integer.parseInt(getTimeStamp_YYYYMMDD());
                ArrayList<String> code_template_1_List =   mMethod_Call_Template_Map.get("1");
                ArrayList<String> code_template_2_List =   mMethod_Call_Template_Map.get("2");

                for (int i = 0; i < calculMonthArr.size(); i++) {
                    int curYear_Month = calculMonthArr.get(i);
                    String yearStr = ("" + curYear_Month).substring(0, 4);
                    String monthStr = ("" + curYear_Month).substring(4);
                    int yearInt = Integer.parseInt(yearStr);
                    int monthInt = Integer.parseInt(monthStr);
                    int days_month = getDayForMonth_Year(yearInt, monthInt);
                    for (int j = 0; j < days_month ; j++) {
                        int day_index = j+1;
                      String curIXinqiStr = calculXinQi2Chinese(yearInt,monthInt,day_index);
                        System.out.println("curIXinqiStr_A3_1 = "+ curIXinqiStr);

                      if("5".equals(curIXinqiStr)){
                          String daystr =  day_index>=10?day_index+"":"0"+day_index;
                          int cur_friday_int_flag = Integer.parseInt(yearStr+monthStr+daystr);
                          if(cur_friday_int_flag > nowIntFlag || cur_friday_int_flag < recordDayFlag ){  // 当大于当前日期 那么不加入到列表
                              continue;
                          }
                          System.out.println("curIXinqiStr_A3_2 = "+ curIXinqiStr);
                          fridayIntList.add(cur_friday_int_flag);
                      }
                        System.out.println("curIXinqiStr_A3_4 = "+ curIXinqiStr);

                    }
                }

                System.out.println("curIXinqiStr_A3_5_nowIntFlag = "+ nowIntFlag);

             Map<Integer,ArrayList<Integer>> year_firday_map = fenlei_for_allFridayArr(fridayIntList);

                System.out.println("curIXinqiStr_A3_6_nowIntFlag = "+ nowIntFlag);

                // 【ZHoldPlace_Year_Int】 替换为当前年份
                // 【ZHoldPlace_Weekly_Day】   替换为当前 执行的 查询日期
                // 【ZHoldPlace_WeekIndex_DayDesc】 WeekIndex 是固定的应该,而不是 arrayList的index
                Map.Entry<Integer , ArrayList<Integer>> entryItem;
                if(year_firday_map != null){
                    Iterator iterator = year_firday_map.entrySet().iterator();
                    while( iterator.hasNext() ){
                        entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();
                        Integer year =   entryItem.getKey();   //Map的Key  【ZHoldPlace_Year_Int】
                        ArrayList<Integer> year_day =  entryItem.getValue();  //Map的Value
                        for (int i = 0; i < code_template_1_List.size(); i++) {

                            String headCodeItem  = code_template_1_List.get(i);
                            String fixed_headCodeItem =  HolderReplaceOperation_Static(headCodeItem);
                            if(fixed_headCodeItem.contains("【ZHoldPlace_Year_Int】")){
                                // fixed_headCodeItem
                                fixed_headCodeItem = fixed_headCodeItem.replace("【ZHoldPlace_Year_Int】",""+year);
                            }
                            python_method_call_List_Code.add(fixed_headCodeItem);

                        }
//                        System.out.println("=========="+year + "  =========");
                        for (int i = 0; i <year_day.size() ; i++) {
//                            System.out.println("key = "+year + "  value["+i+"] = "+ year_day.get(i) );
                            int curDayInt = year_day.get(i);  //  【ZHoldPlace_Weekly_Day】
                            // 当前 星期5 对应的在该年的索引
                            int friday_index = calculFridayIndex2Year(curDayInt);

                            String sheet_name = friday_index+"_"+curDayInt;  // 【ZHoldPlace_WeekIndex_DayDesc】

                            for (int j = 0; j < code_template_2_List.size() ; j++) {


                                String recyleCodeItem = code_template_2_List.get(j);
                                String fixed_recyleCodeItem =  HolderReplaceOperation_Static(recyleCodeItem);
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Weekly_Day】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_WeekIndex_DayDesc】",""+sheet_name);
                                }

                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_propKey2ValueList】")){
                                    fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("【ZHoldPlace_propKey2ValueList】",paramXcode);
                                }
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Weekly_Day】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Weekly_Day】",""+curDayInt);
                                }
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Year_Int】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Year_Int】",""+year);
                                }


                                if(fixed_recyleCodeItem.contains("wtrade_date=''")){
                                    fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("wtrade_date=''","trade_date='"+curDayInt+"'");
                                }



                                python_method_call_List_Code.add(fixed_recyleCodeItem);
                            }



                        }
                    }
                }
            }else if(operation_type == 5){  //  月线行情 处理
                paramXcode =  fieldParamList.get(0);
                // 1mstart_date#1mend_date#1dtrade_date
                String propStartDay = getPropValue("start_date");   // prop 中设置的 起始日期 20010101
                String propRecordDay = getPropValue("record_date");  // peop 中记录的record的日期 20200707
                if(propRecordDay == null || "".equals(propRecordDay)){
                    propRecordDay = "20100101";
                }
                if(propStartDay == null || "".equals(propStartDay)){
                    propStartDay = "20100101";
                }
                // 201001----201002-----201012
                ArrayList<Integer> calculMonthArr = calculMonSpaceIntArr(propRecordDay,1);

                // 每个月的最后一个非 星期6 星期7 的日子的集合
                ArrayList<Integer> EndWorkDayInMonthList = new  ArrayList<Integer>();
                //  获取当前的时间戳
                int recordDayFlag = Integer.parseInt(propRecordDay);
                int nowIntFlag = Integer.parseInt(getTimeStamp_YYYYMMDD());
                ArrayList<String> code_template_1_List =   mMethod_Call_Template_Map.get("1");
                ArrayList<String> code_template_2_List =   mMethod_Call_Template_Map.get("2");

                for (int i = 0; i < calculMonthArr.size(); i++) {
                    int curYear_Month = calculMonthArr.get(i);
                    String yearStr = ("" + curYear_Month).substring(0, 4);
                    String monthStr = ("" + curYear_Month).substring(4);
                    int yearInt = Integer.parseInt(yearStr);
                    int monthInt = Integer.parseInt(monthStr);

                    // 每个月的最后一个工作日
                    int end_work_day_for_month = getEndWorkDayForMonth(yearInt, monthInt);
                    if(end_work_day_for_month < nowIntFlag && end_work_day_for_month >  recordDayFlag){
                        while(!SH_TradeDayList.contains(end_work_day_for_month)){
                            end_work_day_for_month = end_work_day_for_month - 1;
                        }
                        EndWorkDayInMonthList.add(end_work_day_for_month);
                        if(!SH_EndMonth_TradeDayList.contains(end_work_day_for_month)){
                            SH_EndMonth_TradeDayList.add(end_work_day_for_month);
                        }
                    }
                }

                Map<Integer,ArrayList<Integer>> year_firday_map = fenlei_for_allFridayArr(EndWorkDayInMonthList);

                // 【ZHoldPlace_Year_Int】 替换为当前年份
                // 【ZHoldPlace_Weekly_Day】   替换为当前 执行的 查询日期
                // 【ZHoldPlace_WeekIndex_DayDesc】 WeekIndex 是固定的应该,而不是 arrayList的index
                Map.Entry<Integer , ArrayList<Integer>> entryItem;
                if(year_firday_map != null){
                    Iterator iterator = year_firday_map.entrySet().iterator();
                    while( iterator.hasNext() ){
                        entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();
                        Integer year =   entryItem.getKey();   //Map的Key  【ZHoldPlace_Year_Int】
                        ArrayList<Integer> end_workday_in_month_list =  entryItem.getValue();  //Map的Value
                        for (int i = 0; i < code_template_1_List.size(); i++) {

                            String headCodeItem  = code_template_1_List.get(i);
                            String fixed_headCodeItem =  HolderReplaceOperation_Static(headCodeItem);
                            if(fixed_headCodeItem.contains("【ZHoldPlace_Year_Int】")){
                                // fixed_headCodeItem
                                fixed_headCodeItem = fixed_headCodeItem.replace("【ZHoldPlace_Year_Int】",""+year);
                            }
                            python_method_call_List_Code.add(fixed_headCodeItem);

                        }
//                        System.out.println("=========="+year + "  =========");
                        for (int i = 0; i <end_workday_in_month_list.size() ; i++) {
//                            System.out.println("key = "+year + "  value["+i+"] = "+ year_day.get(i) );
                            int endworkDay = end_workday_in_month_list.get(i);  //【ZHoldPlace_EndWorkDay_MonthDay】
                            String monthDesc = (""+endworkDay).substring(4,6);
                            int monthInt = Integer.parseInt(monthDesc);
                            int yearInt = Integer.parseInt((""+endworkDay).substring(0,4));
                            String sheet_name = monthInt+"";  // 【ZHoldPlace_Month_Index】
                            String year_month_str = yearInt+monthDesc+"01";  // 【ZHoldPlace_Year_Month】

                            // 【ZHoldPlace_Year_Month】

                            for (int j = 0; j < code_template_2_List.size() ; j++) {


                                String recyleCodeItem = code_template_2_List.get(j);
                                String fixed_recyleCodeItem =  HolderReplaceOperation_Static(recyleCodeItem);
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Year_Month】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Year_Month】",""+year_month_str);
                                }

                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_EndWorkDay_MonthDay】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_EndWorkDay_MonthDay】",""+endworkDay);
                                }



                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_propKey2ValueList】")){
                                    fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("【ZHoldPlace_propKey2ValueList】",paramXcode);
                                }
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Month_Index】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Month_Index】",""+sheet_name);
                                }
                                if(fixed_recyleCodeItem.contains("【ZHoldPlace_Year_Int】")){
                                    // fixed_headCodeItem
                                    fixed_recyleCodeItem = fixed_recyleCodeItem.replace("【ZHoldPlace_Year_Int】",""+year);
                                }



                                if(fixed_recyleCodeItem.contains("mtrade_date=''")){
                                    fixed_recyleCodeItem =     fixed_recyleCodeItem.replace("mtrade_date=''","trade_date='"+endworkDay+"'");
                                }



                                python_method_call_List_Code.add(fixed_recyleCodeItem);
                            }




                        }
                    }
                }





            }else if(operation_type == 6){  //  adj_factor  复权因子 月线行情 处理

//   zukgit
               paramXcode =  fieldParamList.get(0);
               int define_Index = 0;

                for (int i = 0; i < callMethodTemplateList.size(); i++) {
                    String methodText = callMethodTemplateList.get(i);
                    String fillText =  HolderReplaceOperation_Static(methodText);
                    // get  定义接收变量的名称

                    if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                        String defineVariable = pythonMethodName+"_item_"+define_Index;
                        fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                    }
                    if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                        fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",paramXcode);
                    }

                    fillText =  HolderReplaceOperation_Static(fillText);
//                    System.out.println("operation_type == 6 -> fillText = "+ fillText);
                    python_method_call_List_Code.add(fillText);

                }
                define_Index++;

                python_method_call_List_Code.add("\n");

                }else if(operation_type == 7){   //  港股通 十大成交股

                paramXcode =  fieldParamList.get(0);
                // 1mstart_date#1mend_date#1dtrade_date
                String propStartDay = getPropValue("start_date");   // prop 中设置的 起始日期 20010101
                String propRecordDay = getPropValue("record_date");  // peop 中记录的record的日期 20200707
                if(propRecordDay == null || "".equals(propRecordDay)){
                    propRecordDay = "20200101";
                }
                if(propStartDay == null || "".equals(propStartDay)){
                    propStartDay = "20200101";
                }


                int nowFlag = getTimeStamp_YYYYMMDD_IntFlag();
                int recordRecord = Integer.parseInt(propRecordDay);
                String recordYearStr = propStartDay.substring(0,4);

                // 在 今年的工作列表中 找到 比当前记录的大的那些列表 组成一个Map MAP的头为  月份数据 1 2 3 4

                if(SH_Now_Year_TradeDayList_FromNow.size() == 0){

                    initTradeDayList(); // zukgit xxx
                }

                Map<Integer,ArrayList<Integer>> monthMapList = guolv_Month_TradeDay_Map(SH_Now_Year_TradeDayList_FromNow,recordRecord);

                System.out.println("operation_type == 7 -> monthMapList "+ monthMapList.size() );
                System.out.println("operation_type == 7 -> SH_Now_Year_TradeDayList_FromNow.size =  "+ SH_Now_Year_TradeDayList_FromNow.size()   +"   recordRecord = " + recordRecord );

                ArrayList<String> code_template_1_List =   mMethod_Call_Template_Map.get("1");
                ArrayList<String> code_template_2_List =   mMethod_Call_Template_Map.get("2");
                ArrayList<String> code_template_3_List =   mMethod_Call_Template_Map.get("3");
                ArrayList<String> code_template_4_List =   mMethod_Call_Template_Map.get("4");



                System.out.println("operation_type == 7 ->  code_template_1_List.size = "+ code_template_1_List.size() );
                System.out.println("operation_type == 7 ->  code_template_2_List.size = "+ code_template_2_List.size() );

                System.out.println("operation_type == 7 ->  code_template_3_List.size = "+ code_template_3_List.size() );

                System.out.println("operation_type == 7 ->  code_template_4_List.size = "+ code_template_4_List.size() );



                for (int i = 0; i < code_template_1_List.size() ; i++) {
                    String methodText_1 = code_template_1_List.get(i);
                    String fillText =  HolderReplaceOperation_Static(methodText_1);
                    if(fillText.contains("【ZHoldPlace_Year_Int】")){
                        fillText =     fillText.replace("【ZHoldPlace_Year_Int】",recordYearStr);
                    }
                    fillText =  HolderReplaceOperation_Static(fillText);
//                    System.out.println("operation_type == 6 -> fillText = "+ fillText);
                    python_method_call_List_Code.add(fillText);
                }


                Map.Entry<Integer , ArrayList<Integer>> entryItem;
                if(monthMapList != null){
                    System.out.println("operation_type == 7 ->  monthMapList != null  monthMapList.size = "+ monthMapList.size());
                    Iterator iterator = monthMapList.entrySet().iterator();
                    while( iterator.hasNext() ){
                        entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();
                        Integer MonthIndex =   entryItem.getKey();   //Map的Key    月份
                        ArrayList<Integer> month_tradedayList =  entryItem.getValue();  //Map的Value   工作日
                        System.out.println("operation_type == 7 ->  monthMapList != null  month_tradedayList. size = "+ month_tradedayList.size());
                        for (int i = 0; i < code_template_2_List.size(); i++) {
                            String methodText_2 = code_template_2_List.get(i);
                            String fillText =  HolderReplaceOperation_Static(methodText_2);
                            if(fillText.contains("【ZHoldPlace_Year_Int】")){
                                fillText =     fillText.replace("【ZHoldPlace_Year_Int】",recordYearStr);
                            }
                            if(fillText.contains("【ZHoldPlace_Month_Index】")){
                                fillText =     fillText.replace("【ZHoldPlace_Month_Index】",""+MonthIndex);
                            }
                            fillText =  HolderReplaceOperation_Static(fillText);
//                    System.out.println("operation_type == 6 -> fillText = "+ fillText);
                            python_method_call_List_Code.add(fillText);
                        }


//                        System.out.println("=========="+year + "  =========");
                        for (int i = 0; i <month_tradedayList.size() ; i++) {
                            int month_tradeday = month_tradedayList.get(i);


                            for (int j = 0; j < code_template_3_List.size(); j++) {

                                String methodText_3 = code_template_3_List.get(j);
                                String fillText =  HolderReplaceOperation_Static(methodText_3);
                                if(fillText.contains("【ZHoldPlace_Year_Int】")){
                                    fillText =     fillText.replace("【ZHoldPlace_Year_Int】",recordYearStr);
                                }
                                if(fillText.contains("【ZHoldPlace_Month_Index】")){
                                    fillText =     fillText.replace("【ZHoldPlace_Month_Index】",""+MonthIndex);
                                }

                                if(fillText.contains("【ZHoldPlace_OPERATION_DAY】")){
                                    fillText =     fillText.replace("【ZHoldPlace_OPERATION_DAY】",""+month_tradeday);
                                }

                                if(fillText.contains("【ZHoldPlace_propKey2ValueList_Index】")){
                                    String defineVariable = pythonMethodName+"_item_"+i;
                                    fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList_Index】",defineVariable);
                                }
                                if(fillText.contains("【ZHoldPlace_propKey2ValueList】")){
                                    fillText =     fillText.replace("【ZHoldPlace_propKey2ValueList】",paramXcode);
                                }

                                if(fillText.contains("yeartonow_trade_date=''")){
                                    fillText =     fillText.replace("yeartonow_trade_date=''","trade_date="+"'"+month_tradeday+"'");
                                }





                                fillText =  HolderReplaceOperation_Static(fillText);
//                    System.out.println("operation_type == 6 -> fillText = "+ fillText);
                                python_method_call_List_Code.add(fillText);
                            }
//                            System.out.println("key = "+year + "  value["+i+"] = "+ year_day.get(i) );




                        }

                        for (int i = 0; i < code_template_4_List.size(); i++) {
                            String methodText_4 = code_template_4_List.get(i);
                            String fillText =  HolderReplaceOperation_Static(methodText_4);
                            if(fillText.contains("【ZHoldPlace_Year_Int】")){
                                fillText =     fillText.replace("【ZHoldPlace_Year_Int】",recordYearStr);
                            }
                            if(fillText.contains("【ZHoldPlace_Month_Index】")){
                                fillText =     fillText.replace("【ZHoldPlace_Month_Index】",""+MonthIndex);
                            }

                            fillText =  HolderReplaceOperation_Static(fillText);
//                    System.out.println("operation_type == 6 -> fillText = "+ fillText);
                            python_method_call_List_Code.add(fillText);

                        }
                    }
                }

            }

            if(InputPythonType == 4){  // daily  query  那么 就把 所有的 ts_code 返回回去

                return showBodyPythonCode(headTemplateList,firstList_Code,python_method_call_List_Code,tailList_Code);
            }


         return   showItemPythonCode( headTemplateList,firstList_Code,python_method_call_List_Code,tailList_Code);
            // 函数参数  以及参数的可选值  key 是参数名称   value 是可选值范围 ArrayList<String>   Map<String, ArrayList<String>>


// monthly_data = pro.monthly(ts_code='000001.SZ', start_date='', end_date=datestr)
// monthly_data = pro.monthly(ts_code='000002.SZ', start_date='', end_date=datestr)







        }


        ArrayList<String>  showBodyPythonCode(ArrayList<String>  headList, ArrayList<String>  firstList , ArrayList<String>  bodyList , ArrayList<String> tailList){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("######## 打印Python 代码 Item = "+ leaf_chinese_title+"  网页:   "+ leaf_web_sit);
            ArrayList<String> codeList = new    ArrayList<String>();

//            for (int i = 0; i < headList.size(); i++) {
//                String headItem = headList.get(i);
//                codeList.add(headItem);
//                System.out.println(headItem);
//            }

            for (int i = 0; i < firstList.size(); i++) {
                String firstListItem = firstList.get(i);
                codeList.add(firstListItem);
                System.out.println(firstListItem);

            }

            for (int i = 0; i < bodyList.size(); i++) {
                String bodyItem = bodyList.get(i);
                codeList.add(bodyItem);
                System.out.println(bodyItem);

            }

            for (int i = 0; i < tailList.size(); i++) {
                String tailItem = tailList.get(i);
                codeList.add(tailItem);
                System.out.println(tailItem);

            }
            System.out.println();
            System.out.println();
            System.out.println();

/*            File targetPythonCodeFile = getNodePythonFile(this);

            writeContentToFile(targetPythonCodeFile,codeList);
            // xxxxxxxxxxxxxxxxxx
            File subCopyFile = new File(J0_Python_Dir_Path + targetPythonCodeFile.getName());
            fileCopy(targetPythonCodeFile,subCopyFile);*/
            //  执行 python 程序的 操作
            return codeList;
        }

        ArrayList<String>  showItemPythonCode(ArrayList<String>  headList, ArrayList<String>  firstList , ArrayList<String>  bodyList , ArrayList<String> tailList){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("######## 打印Python 代码 Item = "+ leaf_chinese_title+"  网页:   "+ leaf_web_sit);
            ArrayList<String> codeList = new    ArrayList<String>();
            for (int i = 0; i < headList.size(); i++) {
                String headItem = headList.get(i);
                codeList.add(headItem);
                System.out.println(headItem);

            }

            for (int i = 0; i < firstList.size(); i++) {
                String firstListItem = firstList.get(i);
                codeList.add(firstListItem);
                System.out.println(firstListItem);

            }

            for (int i = 0; i < bodyList.size(); i++) {
                String bodyItem = bodyList.get(i);
                codeList.add(bodyItem);
                System.out.println(bodyItem);

            }

            for (int i = 0; i < tailList.size(); i++) {
                String tailItem = tailList.get(i);
                codeList.add(tailItem);
                System.out.println(tailItem);

            }
            System.out.println();
            System.out.println();
            System.out.println();

            File targetPythonCodeFile = getNodePythonFile(this);

            writeContentToFile(targetPythonCodeFile,codeList);
            // xxxxxxxxxxxxxxxxxx
            File subCopyFile = new File(J0_Python_Dir_Path + targetPythonCodeFile.getName());
            fileCopy(targetPythonCodeFile,subCopyFile);
            //  执行 python 程序的 操作
            return codeList;
        }




        // ts_code='000001.SZ', start_date='', end_date=datestr
        // ts_code='【tscode】',start_date='【start_date】',end_date='【end_date】'
        @SuppressWarnings("unchecked")
        ArrayList<String> calculInputParamList(){
            ArrayList<String> inputParamTemplateList = new ArrayList<String>();
            ArrayList<ArrayList<String>> optionValueList_List = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> muchOption_ValueList_List = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> oneOption_ValueList_List = new ArrayList<ArrayList<String>>();



            ArrayList<String>  paramNameList  = new   ArrayList<String>();

            ArrayList<String>  onParamKeyName  = new   ArrayList<String>();



            Map.Entry<String , ArrayList<String>> entryItem;
            if(methodParams_linkedHashMap != null){
                Iterator iterator = methodParams_linkedHashMap.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , ArrayList<String>>) iterator.next();
                    String keyName = entryItem.getKey();   //Map的Key

                    ArrayList<String> valueOptionList = entryItem.getValue();  //Map的Value


                    paramNameList.add(keyName);
                    optionValueList_List.add(valueOptionList);
                    if(valueOptionList.size() <= 1){
                        oneOption_ValueList_List.add(valueOptionList);
                        onParamKeyName.add(keyName);
                    }else{
                        muchOption_ValueList_List.add(valueOptionList);
                    }
                }
            }

            // 创建 参数列表的 模板  paramTemplate
            // ts_code='【tscode】',start_date='【start_date】',end_date='【end_date】'
            String paramTemplate = calculOneLine_InputParams(paramNameList);


//            muchOption_ValueList_List =  getMuchOptionArr(optionValueList_List);
//            oneOption_ValueList_List =  getOneOptionArr(optionValueList_List);

            // 把只有一个 参数可选值的 option 设置出来 到  paramTemplate

            paramTemplate = calculOneOptionParams(paramTemplate,onParamKeyName,oneOption_ValueList_List);

            ArrayList<ArrayList<String>> allRankList = getAllRankRange(muchOption_ValueList_List);

            for (int i = 0; i < allRankList.size(); i++) {
                ArrayList<String>  resultItem =    allRankList.get(i);

                String paramsItem =   toReplaceHolderPlace(resultItem,paramTemplate);
                System.out.println("索引 [ "+i+" ] = "+ showResultItem(resultItem)+" 模板:"+ paramTemplate+"   替换后 paramsItem :  "+ paramsItem);
                inputParamTemplateList.add(paramsItem);
            }

            return inputParamTemplateList;
        }

        String toReplaceHolderPlace(ArrayList<String> paramList , String template ){
            String resultStr = "";
            String operationStr = new String(template);
            int charCount =  getCharCount(template,"【");
            if(charCount != paramList.size()){
                System.out.println("参数个数 paramList.size() = "+ paramList.size() + "  占位符个数不一致   charCount = "+ charCount);
            }

            for (int i = 0; i < paramList.size(); i++) {
                String paramValue = paramList.get(i);
                String contentItem = getStrWithPairChar(operationStr,"【","】");
                contentItem = "【"+contentItem+"】";
                operationStr = operationStr.replace(contentItem,paramValue);
            }
            resultStr = operationStr;
            if(resultStr.contains("【")){
                System.out.println(" resultStr = "+ resultStr + " 还包括深括号 可能出错!");
            }

            return resultStr;
        }


        ArrayList<ArrayList<String>>  getOneOptionArr( ArrayList<ArrayList<String>>  arrList_List){
            ArrayList<ArrayList<String>>  subB = new      ArrayList<ArrayList<String>>();

            for (int i = 0; i < arrList_List.size(); i++) {
                ArrayList<String> arrItem = arrList_List.get(i);
                if(arrItem == null || arrItem.size() ==0 || arrItem.size() ==1){
                    subB.add(arrItem);
                }

            }
            return subB;

        }


        ArrayList<ArrayList<String>>  getMuchOptionArr( ArrayList<ArrayList<String>>  arrList_List){
            ArrayList<ArrayList<String>>  subA = new      ArrayList<ArrayList<String>>();

            for (int i = 0; i < arrList_List.size(); i++) {
                ArrayList<String> arrItem = arrList_List.get(i);
                if(arrItem == null || arrItem.size() ==0 || arrItem.size() ==1){
                    continue;
                }
                subA.add(arrItem);
            }
            return subA;

        }

        // ts_code='000001.SZ', start_date='', end_date=datestr
        // ts_code='【tscode】',start_date='【start_date】',end_date='【end_date】'
        @SuppressWarnings("unchecked")
        String calculOneLine_InputParams(ArrayList<String> paramNameList){
            String paramTemplate = "";

            for (int i = 0; i < paramNameList.size(); i++) {
                String oneParam = paramNameList.get(i);
                String tempParam = oneParam+"="+"'【"+oneParam+"】',";
                paramTemplate = paramTemplate + tempParam;
            }

            while(paramTemplate.endsWith(",")){
                paramTemplate =  paramTemplate.substring(0,paramTemplate.length()-1);
            }
            return paramTemplate;

        }

        @Override
        @SuppressWarnings("unchecked")
        void initProp( Map<String, String>  propKey2ValueList){
            System.out.println("执行 initProp ! ");
            if(properity_holder_map == null){
                properity_holder_map = new HashMap<String, String>();
            }
            Map.Entry<String , String> entryItem;
            if(propKey2ValueList != null){
                Iterator iterator = propKey2ValueList.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , String>) iterator.next();
                    String keyStr = entryItem.getKey();   //Map的Key
                    String valueStr = entryItem.getValue();  //Map的Value
                    System.out.println("执行 initProp  nodeName = "+ nodeName + "  keyStr ="+ keyStr);
                    if(keyStr.startsWith(nodeName)){
                        System.out.println("中奖！");
                        properity_holder_map.put(keyStr,valueStr);
                    }

                }
            }
        }


        LeafNode() {

        }

        @Override
        void showNodeInfo() {
            super.showNodeInfo();
            System.out.println("leaf_web_sit = " + leaf_web_sit);
            System.out.println("leaf_chinese_title = " + leaf_chinese_title);
            System.out.println("score_permission_point = " + score_permission_point);
            System.out.println("onceCallCount = " + onceCallCount);
            System.out.println("pythonMethodName = " + pythonMethodName);
            System.out.println("callLimit_Num_OneMinutes = " + callLimit_Num_OneMinutes);
            System.out.println("fieldList = " + Arrays.toString(fieldList.toArray()));
            showParamMap(params_linkedHashMap);
            showOptionMap(methodParams_linkedHashMap);
            if(properity_holder_map != null && properity_holder_map.size() > 0 ){
                showPropKeyAndValue(properity_holder_map);
            }
        }

        @SuppressWarnings("unchecked")
        public void showParamMap(Map<String, String> mMapParam) {
            Map.Entry<String, String> entryItem;
            if (mMapParam != null) {
                Iterator iterator = mMapParam.entrySet().iterator();
                while (iterator.hasNext()) {
                    entryItem = (Map.Entry<String, String>) iterator.next();
                    String key = entryItem.getKey();   //Map的Key
                    String value = entryItem.getValue();  //Map的Value
                    System.out.println("key = " + key + " value = " + key);
                }
            }
        }


        @SuppressWarnings("unchecked")
        public void showCycleMEthodCallMap(Map<String, ArrayList<String>> mMapParam) {
            Map.Entry<String, ArrayList<String>> entryItem;
            if (mMapParam != null) {
                Iterator iterator = mMapParam.entrySet().iterator();
                while (iterator.hasNext()) {
                    entryItem = (Map.Entry<String, ArrayList<String>>) iterator.next();
                    String key = entryItem.getKey();   //Map的Key
                    ArrayList<String> valueList = entryItem.getValue();  //Map的Value
                    String keyDec = "Method_Call_Template_"+key;
                    System.out.println("=======" + keyDec+" Begin "+ "=======" );
                    for (int i = 0; i < valueList.size(); i++) {
                        String itemStr = valueList.get(i);
                        System.out.println("key = "+keyDec +"   value["+i+"] = "+ itemStr);
                    }
                    System.out.println("=======" + keyDec+" End "+ "=======" );
               }
            }
        }


        @SuppressWarnings("unchecked")
        public void showOptionMap(Map<String, ArrayList<String>> mMapParam) {
            Map.Entry<String, ArrayList<String>> entryItem;
            if (mMapParam != null) {
                Iterator iterator = mMapParam.entrySet().iterator();
                while (iterator.hasNext()) {
                    entryItem = (Map.Entry<String, ArrayList<String>>) iterator.next();
                    String key = entryItem.getKey();   //Map的Key
                    ArrayList<String> valueList = entryItem.getValue();  //Map的Value
                    System.out.println("参数 = " + key + " 参数可选范围 = " + Arrays.toString(valueList.toArray()));
                }
            }
        }




    }


    static File getNodePythonFile(TreeNode node){
        String node_python_file = zbinPath +File.separator+"J0_"+getPaddingIntString(node.blockIndex,4,"0",true)+"_"+node.nodeName+".py";
//        String node_python_file = zbinPath +File.separator+"J0_"+getPaddingIntString(blockIndex,4,"0",true)+"_"+nodeName+".py";
        return new File(node_python_file);
    }

    class Common_TreeNode extends TreeNode {

        // 把 Prop 数据  过滤到 到  各个 TreeNode 中去
        void initProp( Map<String, String>  propKey2ValueList){

        }

        String   getPropValue(String name){
            if(properity_holder_map == null){
                return null;
            }
            String fixed_key = nodeName+"_"+name;
            String value = properity_holder_map.get(fixed_key);
            if(value == null){
                return null;
            }

            return value;

        }
        // 把 TreeNode 中的 Prop数据 覆盖到 系统的 Prop中
        @SuppressWarnings("unchecked")
        void RestoreToProp( Map<String, String>  propKey2ValueList){
            if(properity_holder_map == null || properity_holder_map.size() == 0){
                return ;
            }
            Map.Entry<String , String> entryItem;
            if(properity_holder_map != null){
                Iterator iterator = properity_holder_map.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , String>) iterator.next();
                    propKey2ValueList.put(entryItem.getKey(),entryItem.getValue());
                }
            }


        }

    }

    abstract class TreeNode {
        int blockIndex;  // 块索引   用于排序
        String paddingBlockIndexStr;
        boolean isRoot;  // 是否是根节点
        boolean isLeaf;
        String nodeName;  // 结点名称
        int deepth;   // 深度
        Map<String, String>  properity_holder_map;  // prop 文件包含的当前 node 数据集合

        abstract void initProp( Map<String, String>  propKey2ValueList);

        abstract void RestoreToProp( Map<String, String>  propKey2ValueList);

        abstract String   getPropValue(String name);

        void showNodeInfo() {
            System.out.println("NodeName = " + nodeName);
            System.out.println("isRoot = " + isRoot);
            System.out.println("isLeaf = " + isLeaf);
            System.out.println("deep = " + deepth);
            System.out.println("getOrganizationIdentify() = " + getOrganizationIdentify());
            System.out.println("parentLinkList size = " + (isRoot ? 0 : parentLinkList.size()));
            System.out.println("childArr size = " + (isLeaf ? 0 : childArr.size()));

        }

        @SuppressWarnings("unchecked")
        public  void showPropKeyAndValue(Map<String,String> mMapParam){
            Map.Entry<String , String> entryItem;
            int index_position = 0;
            if(mMapParam != null){
                Iterator iterator = mMapParam.entrySet().iterator();
                while( iterator.hasNext() ){
                    entryItem = (Map.Entry<String , String>) iterator.next();
                    System.out.println("Prop["+index_position+"] = "+"Prop-Key: "+ entryItem.getKey() + "  Prop-Value:"+entryItem.getValue());
                    index_position++;
                }
            }
        }

        ArrayList<TreeNode> parentLinkList;  // 当前结点的父类结点 第一个是根节点  最后是父亲节点 中间祖父 曾祖父
        ArrayList<TreeNode> childArr;  // 当前结点的子结点的集合  // 叶子节点没有子节点

        String inputOrganizationIdentify;  // 从 treedata 获得的 组织名称



        public int getAndInitDynamicDeep() {
            if (getParentNode() != null) {
                if (!isRoot) {
                    int curDynamicDeep = getParentNode().deepth + 1;
                    if (curDynamicDeep != deepth && deepth != 0 && deepth != -1) {
                        System.out.println("当前动态计算到的深度 和 用户通过 〓 描述的深度不一致 请注意!   nodename = " + nodeName + "   deepth = " + deepth + "   curDynamicDeep = " + curDynamicDeep);
                    }
                    deepth = getParentNode().deepth + 1;
                }
                return getParentNode().deepth + 1;
            }
            return -1;
        }

        public TreeNode getParentNode() {
            if (parentLinkList == null || parentLinkList.size() == 0) {
                return null;
            } else {
                return parentLinkList.get(parentLinkList.size() - 1);
            }

        }

        public String getOrganizationIdentify() {
            StringBuilder sb = new StringBuilder();
            if (parentLinkList != null) {

                for (int i = 0; i < parentLinkList.size(); i++) {
                    TreeNode item = parentLinkList.get(i);
                    sb.append(item.nodeName + "_");
                }
            }
            return sb.toString() + nodeName;
        }
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

    static String getStrWithPairChar(String inputStr, String pairStrA, String pairStrB) {
        String resultStr = "";
        if (inputStr.lastIndexOf(pairStrA) != -1 && inputStr.lastIndexOf(pairStrB) != -1) {

            int firstIndex = inputStr.indexOf(pairStrA) + pairStrA.length();
            String otherStr = inputStr.substring(firstIndex);
            int secondIndex = otherStr.indexOf(pairStrB) + firstIndex;

//            System.out.println("firstIndex = "+ firstIndex + "    secondIndex="+ secondIndex);
            resultStr = inputStr.substring(firstIndex, secondIndex);
            return resultStr;
        }
        return resultStr;
    }


    static String getStrWithPairChar(String inputStr, String pairStr) {
        String resultStr = "";
        if (inputStr.lastIndexOf(pairStr) != -1 &&
                inputStr.indexOf(pairStr) != inputStr.lastIndexOf(pairStr)) {

            int firstIndex = inputStr.indexOf(pairStr) + pairStr.length();
            String otherStr = inputStr.substring(firstIndex);
            int secondIndex = otherStr.indexOf(pairStr) + firstIndex;

//            System.out.println("firstIndex = "+ firstIndex + "    secondIndex="+ secondIndex);
            resultStr = inputStr.substring(firstIndex, secondIndex);
            return resultStr;
        }
        return resultStr;
    }





    static String  showResultItem(ArrayList<String>  logList){
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < logList.size(); i++) {
            sb.append(logList.get(i)+",");
        }

        String result = sb.toString();
        while(result.endsWith(",")){
            result = result.substring(0,result.length()-1);

        }
        return result;
    }


    static String  calculOneOptionParams (String template , ArrayList<String>  keyNameList, ArrayList<ArrayList<String>> oneOptionArr_Arr){
        String fillOneOptionStr = new String(template);

        for (int i = 0; i < keyNameList.size(); i++) {
            ArrayList<String> oneParam =  oneOptionArr_Arr.get(i);
            String oneKeyName = keyNameList.get(i);
            String keyValue = "";
            if(oneParam != null && oneParam.size() == 1){
                keyValue =oneParam.get(0);
            }
            fillOneOptionStr = fillOneOptionStr.replace("【"+oneKeyName+"】",keyValue);
        }
        return fillOneOptionStr;

    }

    static ArrayList<ArrayList<String>> getAllRankRange(ArrayList<ArrayList<String>> arr_arr){
        ArrayList<ArrayList<String>> newRankList = new  ArrayList<ArrayList<String>>();
        ArrayList<int[]>  allSubIntList =     getAllRank(arr_arr);
        for (int i = 0; i < allSubIntList.size(); i++) {
            int[] intList =  allSubIntList.get(i);
            ArrayList<String> onItemList = new  ArrayList<String>();
            for (int j = 0; j < intList.length; j++) {
                onItemList.add(arr_arr.get(j).get(intList[j]));
            }
            newRankList.add(onItemList);
        }
        return newRankList;
    }


    static ArrayList<int[]>  getAllRank(ArrayList<ArrayList<String>> arr_arr){
        ArrayList<Integer> maxSizeList =  getMaxSizeInArr(arr_arr);
        ArrayList<int[]> allRankList = new ArrayList<int[]>();
        int minIndex  = 0 ;
        int maxIndex = getMaxIndexValue(arr_arr);

//        for (int i = 0; i < arr_arr.size(); i++) {
//            int[] onrArr = new int[arr_arr.size()];
//            allRankList.add(onrArr);
//        }


        first:   for (int i = minIndex; i <= maxIndex; i++) {
            int curIndex = i;

//            System.out.println("═════════════ i ="+ i);
            int[] onrArr = new int[arr_arr.size()];
            second:      for (int j = 0; j < arr_arr.size(); j++) {
                int cur_jinzhi = getJinZhi(j,maxSizeList); // 获取当前索引的进制
                int cur_Min_danyuan = getMin_DanYuanValue_InArr(j,arr_arr);

//                System.out.println(" 当前 [ "+ i+" ] = "+ i +"  [j]="+j+"  cur_jinzhi="+cur_jinzhi+"   cur_Min_danyuan="+ cur_Min_danyuan );

                // Index 【12】 = [0, 3, 0]       正确为 Index 【12】 = [0, 1, 1]

//          ═════════════ i =12
//          当前 [ 12 ] = 12  [j]=0  cur_jinzhi=3   cur_Min_danyuan=1
//          当前 [ 12 ] = 12  [j]=1  cur_jinzhi=9   cur_Min_danyuan=3
//          当前 [ 12 ] = 12  [j]=2  cur_jinzhi=27   cur_Min_danyuan=9

                if(curIndex < cur_jinzhi){
                    int positionValue = curIndex/cur_Min_danyuan ;
                    onrArr[j] = positionValue;
//                    curIndex = curIndex - positionValue*cur_jinzhi;
                    break second;
                }else if(curIndex == cur_jinzhi){
                    onrArr[j+1] = onrArr[j+1] + 1;
                    break second;
                }else if (curIndex > cur_jinzhi){
                    int positionValue = curIndex%cur_jinzhi;
//                    System.out.println("positionValueA = "+ positionValue);

                    if(positionValue >= cur_Min_danyuan){
                        positionValue = positionValue/cur_Min_danyuan;
                    }

//                    System.out.println("positionValueB = "+ positionValue);
                    onrArr[j] = positionValue;
                    curIndex = curIndex - positionValue*cur_Min_danyuan;
//                    System.out.println("curIndex = "+ curIndex);
                }



            }
            allRankList.add(onrArr);
        }

        for (int i = 0; i <allRankList.size() ; i++) {
            int[] intArr = allRankList.get(i);
            System.out.println("Index 【" +i+"】 = "  +Arrays.toString(intArr));
//            for (int j = 0; j <intArr.length ; j++) {
//                System.out.println(" index = "+ j+"  "+(intArr[j]);
//            }
        }
        return allRankList;
    }

    static  int getJinZhi(int ArrIndex ,  ArrayList<Integer> maxSizeList ){
        int jinzhi = 1 ;
        if(ArrIndex == 0){
            jinzhi = maxSizeList.get(0);
        }else{
            return maxSizeList.get(ArrIndex) * getJinZhi(ArrIndex-1,maxSizeList);
        }
        return jinzhi;
    }

    //  size X size X size    最大的索引编号  每个队列相乘 得到总的数量  总的数量-1 就是最大索引
    static int getMaxIndexValue( ArrayList<ArrayList<String>>  list_list_arr){
        int max_index = 1 ;

        for (int i = 0; i < list_list_arr.size() ; i++) {
            if(list_list_arr.get(i).size() == 0){
                continue;
            }
            max_index = max_index *  list_list_arr.get(i).size();
        }


        return max_index - 1;


    }



    static   int   getMin_DanYuanValue_InArr( int index , ArrayList<ArrayList<String>>  arrList_List) {
        int min_danyuan_value = 1;
        if (index == 0) {
            return 1;
        } else {
            ArrayList<Integer> masxSizeArr =       getMaxSizeInArr(arrList_List);
            return  getJinZhi (index - 1, masxSizeArr );

        }
    }



    static   ArrayList<Integer>   getMaxSizeInArr( ArrayList<ArrayList<String>>  arrList_List){
        ArrayList<Integer> intList = new      ArrayList<Integer>();

        for (int i = 0; i < arrList_List.size(); i++) {
            ArrayList<String> arrItem = arrList_List.get(i);
            int curIndex = arrItem.size();
            intList.add(curIndex);
        }
        return intList;
    }


    static  ArrayList<Integer>   getMaxIndexInArr( ArrayList<ArrayList<String>>  arrList_List){
        ArrayList<Integer> intList = new      ArrayList<Integer>();

        for (int i = 0; i < arrList_List.size(); i++) {
            ArrayList<String> arrItem = arrList_List.get(i);
            int curIndex = arrItem.size() -1;
            intList.add(curIndex);
        }
        return intList;
    }


    static int  getCharCount(String originStr , String charStr){
        int count  = 0 ;
        if(originStr == null || charStr == null){

            return count;
        }
        for (int i = 0; i < originStr.length(); i++) {
            String charOne = originStr.substring(i,i+1);
            if(charStr.equals(charOne)){
                count++;
            }
        }
        return count;

    }

    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }






    static   ArrayList<Integer> HK_TradeDayList = new   ArrayList<Integer>();  // 香港股市交易日列表

    static   ArrayList<Integer> HK_No_TradeDayList = new   ArrayList<Integer>();  // 香港股市 非 股市交易日列表

    static   ArrayList<Integer> SH_TradeDayList = new   ArrayList<Integer>(); // 大陆 非交易日列表
    static   ArrayList<Integer> SH_No_TradeDayList = new   ArrayList<Integer>(); // 大陆 股市非交易日列表

    //  截止到目前的 交易日期
    static   ArrayList<Integer> SH_TradeDayList_FromNow = new   ArrayList<Integer>(); // 大陆 交易日列表 截止到目前的

   // SH_Now_Year_TradeDayList_FromNow

    //    从今年年初 到现在的  交易的日集合
    static   ArrayList<Integer> SH_Now_Year_TradeDayList_FromNow = new   ArrayList<Integer>(); // 大陆 交易日列表 截止到目前的



    // 从2010.01.01 每周最后面那个交易日
    static   ArrayList<Integer> SH_EndWeek_TradeDayList = new   ArrayList<Integer>(); // 大陆 股市非交易日列表
    // 从2010.01.01 每月最后面那个交易日
    static   ArrayList<Integer> SH_EndMonth_TradeDayList = new   ArrayList<Integer>(); // 大陆 股市非交易日列表




    static void  initTradeDayList(){
        File tradeDayFile = new File(J0_JiaoYiRiQi_Path);
        if(!tradeDayFile.exists()){
            System.out.println("当前 没有 基础数据文件【交易日历.xlsx】( 请添置该文件 ) J0_JiaoYiRiQi_Path ="+ J0_JiaoYiRiQi_Path);
            return;
        }

        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = tradeDayFile.getAbsolutePath();
        System.out.println("J0_JiaoYiRiQi_Path xlsx Path = "+ filePath);
        String columns[] = {"exchange","cal_date","is_open","pretrade_date"};
        wb = readExcel(filePath);

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
        }

        String SH_TAG = "SSE";
        String HK_TAG = "XHKG";
        //遍历解析出来的list
      first:  for (Map<String,String> map : list) {
        second:     for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");

                if("exchange".equals(entry.getKey()) && SH_TAG.equals(entry.getValue()) ){
                    addSHTradeDay(map);
                     break ;
                }else if("exchange".equals(entry.getKey()) && HK_TAG.equals(entry.getValue())  ){
                    addHKTradeDay(map);
                    break ;
                }
                //    System.out.println(" entry.getKey() = "+entry.getKey() + "   entry.getValue() = "+ entry.getValue()+"【Over】");
            }
//            System.out.println();
        }

        //    HK_TradeDayList =
//    SH_TradeDayList =
//    HK_No_TradeDayList
//    SH_No_TradeDayList

//        showTradeDayList(SH_TradeDayList,"上证交易日");
//        showTradeDayList(SH_No_TradeDayList,"上证非交易日");
//        showTradeDayList(HK_TradeDayList,"港市交易日");
//        showTradeDayList(HK_No_TradeDayList,"港市非交易日");


      while(!SH_TradeDayList.contains(SH_Last_Trade_Day_Int)){
          SH_Last_Trade_Day_Int = SH_Last_Trade_Day_Int - 1;
      }


    }

    static void showTradeDayList(ArrayList<Integer> tradeList ,String title){
        System.out.println("═══════交易市场 "+title+"═════");
        for (int i = 0; i < tradeList.size(); i++) {
            int dayIntLag = tradeList.get(i);
            System.out.println("交易日索引[ "+i+" ] = "+ dayIntLag + "   tip:"+title);
        }
    }

    static void   addHKTradeDay(Map<String,String> map ){
        String isopen = map.get("is_open").trim();
        String cal_date =  map.get("cal_date").trim();
        float isOpenInt = Float.parseFloat(isopen);
        int dayIntFlag = Integer.parseInt(cal_date);
        if(isOpenInt == 0){
            HK_No_TradeDayList.add(dayIntFlag);
        }else{
            HK_TradeDayList.add(dayIntFlag);
        }

    }
//    HK_TradeDayList =
//    SH_TradeDayList =
//    HK_No_TradeDayList
//    SH_No_TradeDayList
    static void   addSHTradeDay(Map<String,String> map ){
        // addSHTradeDay  isopen = 1.0     cal_date = 19910418
        // 1.0 转为 int  报错
        String isopen = map.get("is_open").trim();
        float isOpenInt = Float.parseFloat(isopen);
        String cal_date =  map.get("cal_date").trim();
     //   System.out.println("addSHTradeDay  isopen = "+isopen + "   isOpenInt ="+isOpenInt+  "     cal_date = "+ cal_date);
        int dayIntFlag = Integer.parseInt(cal_date);
        if(isOpenInt == 0){
            SH_No_TradeDayList.add(dayIntFlag);
        }else{
            SH_TradeDayList.add(dayIntFlag);
            Add_SH_Now_TradeDayList_FromNow(dayIntFlag);

        }

    }


    // 从年初 到 今天的 日期的集合
    static void Add_SH_Now_TradeDayList_FromNow(int dayIntFlag){
if(dayIntFlag >= SH_Now_Year_BeginTradeDay_IntFlag && dayIntFlag <=  SH_Tomorrow_WorkTrade_Day_Int){
    SH_Now_Year_TradeDayList_FromNow.add(dayIntFlag);
}

    }


    static void  initTsCodeList(){
// TScode_List
        File ts_code_File = new File(J0_GuPiaoLieBiao_Path);
        if(!ts_code_File.exists()){
            System.out.println("当前 没有 基础数据文件【股票列表.xlsx】( 请添置该文件 ) J0_GuPiaoLieBiao_Path ="+ J0_GuPiaoLieBiao_Path);
            return;
        }


        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = ts_code_File.getAbsolutePath();
        System.out.println("xlsx Path = "+ filePath);
        String columns[] = {"ts_code","symbol","name","area","industry","fullname","enname","market","exchange","curr_type","list_status","list_date","delist_date","is_hs"};
        wb = readExcel(filePath);

        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
//            sheet = wb.getSheet("股票列表");
//            sheet = wb.getSheetAt(0);
            sheet = wb.getSheet("股票列表");
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
        }
        //遍历解析出来的list


        for (Map<String,String> map : list) {
            for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");

                if("ts_code".equals(entry.getKey())){
                    TScode_List.add(entry.getValue());
                }


                //    System.out.println(" entry.getKey() = "+entry.getKey() + "   entry.getValue() = "+ entry.getValue()+"【Over】");
            }
//            System.out.println();
        }


        TScode_List.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });


    }


    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    static ArrayList<String> calcul_XCode_List_Params(int count){
        if(count <= 0){
            count = x_tscode_num_default; // 默认值
        }
        ArrayList<String> xcodeParams = new   ArrayList<String>();
        int numCount = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TScode_List.size(); i++) {
            if(numCount%count == 0){
                String xcode_item = sb.toString();
                while(xcode_item.endsWith(",")){
                    xcode_item = xcode_item.substring(0,xcode_item.length()-1);
                }
                xcodeParams.add(xcode_item);
                sb = new StringBuilder();
            }
            sb.append(TScode_List.get(i)+",");
            numCount++;
        }
        String xCode_End = sb.toString();
        while(xCode_End.endsWith(",")){
            xCode_End = xCode_End.substring(0,xCode_End.length()-1);
        }
        if(!xcodeParams.contains(xCode_End)){
            xcodeParams.add(xCode_End);
        }

        return xcodeParams;
    }




//  获取 从 1990年 到 现在年份的 20x9 年 之间 相隔 year_space 的时间组合 索引0 -beginStr 索引1-endStr
//## 1990.01.01   --- 1999.12.31 Space=10
// ArrayList<ArrayList<String>> init_Year_Pair_List(int year_space)  Begin
//## 2000.01.01   ---- 2009.12.31
//## 2010.01.01 -----2019.12.31
//## 2020.01.01 ---- 2029.12.31
//## 2030.01.01 ---- 2039.12.31

//    ArrayList<ArrayList<String>>  yearPair =  init_Year_Pair_List(default_space_year);
//        for (int i = 0; i < yearPair.size() ; i++) {
//        ArrayList<String> pair_date = yearPair.get(i);
//        System.out.println("Date[ "+ i+ "]   BeginDate= "+ pair_date.get(0)+"   EndDate = "+ pair_date.get(1));
//    }



    static ArrayList<ArrayList<String>> init_Year_Pair_List(int year_space){
        Calendar curCalendar = Calendar.getInstance();
        int beginYear = 1990;
        int endYear = curCalendar.get(Calendar.YEAR)+10;

        ArrayList<ArrayList<Calendar>>  tenYear_Calendar_List =   calcul_Ten_Year_DatePair(beginYear,endYear);
        return calculDate_Begin_End_List(tenYear_Calendar_List ,year_space );
    }


    static ArrayList<ArrayList<String>> calculDate_Begin_End_List( ArrayList<ArrayList<Calendar>> tenYearList , int yearSpace ){
        if(yearSpace == 10){
            return calDateListWithCalendar(tenYearList);
        }

        int mBeginYear = tenYearList.get(0).get(0).get(Calendar.YEAR);
        int mEndYear = tenYearList.get(tenYearList.size()-1).get(1).get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        int nextYear = cal.get(Calendar.YEAR)+1;
        int future_less_year = mEndYear>nextYear?nextYear:mEndYear;
        return calDateListWithCalendar(calcul_Space_Year(mBeginYear,future_less_year,yearSpace));

    }

    static    ArrayList<ArrayList<String>>  calDateListWithCalendar(ArrayList<ArrayList<Calendar>> calendarList){
        ArrayList<ArrayList<String>> date_list_list = new  ArrayList<ArrayList<String>>();

        SimpleDateFormat format=new SimpleDateFormat("YYYYMMdd");
        for (int i = 0; i < calendarList.size(); i++) {
            ArrayList<Calendar> calendarsList = calendarList.get(i);
            if(calendarsList.size() != 2){
                System.out.println("当前的时间配对 存在错误: calendarsList.size() = "+ calendarsList.size() + " 内容:"+Arrays.toString(calendarsList.toArray()));
                continue;
            }

            ArrayList<String> dateList = new    ArrayList<String>();
            Calendar beginCalendar = calendarsList.get(0);
            Calendar endCalendar = calendarsList.get(1);
            String beginDateStr = format.format(beginCalendar.getTime());
            String endDateStr = format.format(endCalendar.getTime());
            dateList.add(beginDateStr);
            dateList.add(endDateStr);
            date_list_list.add(dateList);
        }

        return date_list_list;

    }

    static ArrayList<ArrayList<Calendar>> calcul_Space_Year(int beginYear , int endYear , int year_step){
        ArrayList<ArrayList<Calendar>> Date_Pair_List =new ArrayList<ArrayList<Calendar>>();
        int curBeginYear = beginYear;
        int curEndYear = endYear;
        int index = 0 ;
        SimpleDateFormat format=new SimpleDateFormat("YYYYMMdd");
        while(curBeginYear < endYear){
            ArrayList<Calendar> dateList = new ArrayList<Calendar>();
            String beginStr = curBeginYear+"0101";
            String endStr = getNextSpaceYear(curBeginYear,year_step)+"1231";
            Calendar begincalendar= null;
            Calendar endcalendar= null;
            try {
                Date beginDate=format.parse(beginStr);
                Date endDate=format.parse(endStr);

                begincalendar=Calendar.getInstance();
                begincalendar.setTime(beginDate);

                endcalendar=Calendar.getInstance();
                endcalendar.setTime(endDate);

            } catch (Exception e) {
                e.printStackTrace();
            }

            curBeginYear =  getNext_BeginYear(curBeginYear,year_step);
            dateList.add(begincalendar);
            dateList.add(endcalendar);
//            System.out.println("Date["+index+"] beginStr = "+ beginStr + " endStr  =" + endStr);
            Date_Pair_List.add(dateList);
            index++;
        }

        return Date_Pair_List;


    }

    static int getNext_BeginYear(int year ,int year_step ){

        return year+year_step;
    }

    static int getNextSpaceYear(int year ,int year_step ){

        return year+year_step-1;
    }

    static ArrayList<ArrayList<Calendar>> calcul_Ten_Year_DatePair(int beginYear , int endYear ){
        ArrayList<ArrayList<Calendar>> Date_Pair_List =new ArrayList<ArrayList<Calendar>>();
        int curBeginYear = beginYear;
        int curEndYear = endYear;
        int index = 0 ;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        while(curBeginYear < endYear){
            ArrayList<Calendar> dateList = new ArrayList<Calendar>();
            String beginStr = curBeginYear+"-01-01";
            String endStr = getNextNightYear(curBeginYear)+"-12-31";
            Calendar begincalendar= null;
            Calendar endcalendar= null;

            try {
                Date beginDate=format.parse(beginStr);
                Date endDate=format.parse(endStr);

                begincalendar=Calendar.getInstance();
                begincalendar.setTime(beginDate);

                endcalendar=Calendar.getInstance();
                endcalendar.setTime(endDate);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println("beginStr = "+beginStr + " begincalendar = "+ format.format(begincalendar.getTime())+"endStr = "+endStr + " endcalendar = "+ format.format(endcalendar.getTime()));
            curBeginYear =  getTenYear(curBeginYear);
            dateList.add(begincalendar);
            dateList.add(endcalendar);
//            System.out.println("Date["+index+"] beginStr = "+ beginStr + " endStr  =" + endStr);
            Date_Pair_List.add(dateList);
            index++;
        }

        return Date_Pair_List;
    }

    static int getTenYear(int year){
        int one_year_num = year%10;
        int result_year  = year;
        if(one_year_num == 0){
            return year+10;
        }else{
            int step_year =  10 - one_year_num ;
            for (int i = 0; i < step_year; i++) {
                result_year++;
            }
        }
        return result_year;
    }

    static int getNextNightYear(int year){
        int one_year_num = year%10;
        int result_year = year;
        int step_year =  9 - one_year_num ;
        for (int i = 0; i < step_year; i++) {
            result_year++;
        }
        return result_year;
    }



    static int getEndWorkDayForMonth(int year, int month) {
        int sumDays = getDayForMonth_Year(year,month);

  while("6".equals(calculXinQi2Chinese(year,month,sumDays)) || "7".equals(calculXinQi2Chinese(year,month,sumDays))){
    sumDays = sumDays - 1;
      System.out.println("sumDays_A1 = "+ sumDays);
}

        String monthDesc =  month>=10?month+"":"0"+month;
        int endDayIntFlag = Integer.parseInt(""+year+monthDesc+sumDays);

        return endDayIntFlag;
    }


    static int getDayForMonth_Year(int year, int month) {
        int days = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else if (month == 2) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        } else {
//        System.out.println("请输入正确的月份");
        }
//    System.out.println(year+"年"+month+"月"+"这个月有"+days+"天");
        return days;
    }


    // 得到 记录日志 之后     当去日期之前的 那些   月份交易数据
    static Map<Integer,ArrayList<Integer>>  guolv_Month_TradeDay_Map(  ArrayList<Integer> yearWorkDayIntList , int compareIntFlag ){
        Map<Integer,ArrayList<Integer>> cur_month_tradeday_map = Maps.newLinkedHashMap();

        int nowFlag = getTimeStamp_YYYYMMDD_IntFlag();
        yearWorkDayIntList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2){
                    return 0;
                }
                return o1 < o2 ? -1 : 1;
            }
        });
        for (int i = 0; i < yearWorkDayIntList.size(); i++) {


            int dayItem = yearWorkDayIntList.get(i);
            if(dayItem < compareIntFlag || dayItem > nowFlag){
                continue;
            }
            int monthDescInt = Integer.parseInt((""+dayItem).substring(4,6));
            if(cur_month_tradeday_map.get(monthDescInt) == null){
                ArrayList<Integer> dayList = new ArrayList<Integer>();
                cur_month_tradeday_map.put(monthDescInt,dayList);
            }
            ArrayList<Integer> curDayList =    cur_month_tradeday_map.get(monthDescInt);
            if(!curDayList.contains(dayItem)){
                curDayList.add(dayItem);
            }
        }

        return cur_month_tradeday_map;

    }

    // 周五归类
    static Map<Integer,ArrayList<Integer>>  fenlei_for_allFridayArr(  ArrayList<Integer> fridayIntList){

        Map<Integer,ArrayList<Integer>> year_friday_map = Maps.newLinkedHashMap();
        for (int i = 0; i < fridayIntList.size(); i++) {
            int day = fridayIntList.get(i);
              while(!SH_TradeDayList.contains(day)){   // 20201001 ---- 20201000 20200999  20200930
                  day = day - 1;
                  System.out.println("SH_TradeDayList.size = "+ SH_TradeDayList.size());
                  System.out.println("day = "+ day + "   " + getTimeStamp());
              }

            int year = Integer.parseInt((""+day).substring(0,4));
            if(year_friday_map.get(year) == null){
                ArrayList<Integer> dayList = new ArrayList<Integer>();
                year_friday_map.put(year,dayList);
            }
            ArrayList<Integer> curDayList =    year_friday_map.get(year);
            if(!curDayList.contains(day)){
                curDayList.add(day);
                if(!SH_EndWeek_TradeDayList.contains(day)){
                    SH_EndWeek_TradeDayList.add(day);
                }
            }

        }
        return year_friday_map;
    }

    static ArrayList<Integer>  calculMonSpaceIntArr(String beginDayStr ,int monthSpace){
        ArrayList<Integer> arrMonthArr = new  ArrayList<Integer>();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        Calendar now_calitem =Calendar.getInstance();
        int nowYear = now_calitem.get(Calendar.YEAR);
        int nowMonth = now_calitem.get(Calendar.MONTH)+monthSpace;
        int nowDay= now_calitem.get(Calendar.DAY_OF_MONTH);
        now_calitem.set(Calendar.YEAR,nowYear);
        now_calitem.set(Calendar.MONTH,nowMonth);
        now_calitem.set(Calendar.DAY_OF_MONTH,nowDay);
        nowYear = now_calitem.get(Calendar.YEAR);
        nowMonth = now_calitem.get(Calendar.MONTH)+1;
        nowDay= now_calitem.get(Calendar.DAY_OF_MONTH);

        String nowMonthStr = nowMonth>=10?nowMonth+"":"0"+nowMonth;


//       String nowDayStr = nowDay>=10?nowDay+"":"0"+nowDay;

        int CurDateInt = Integer.parseInt(nowYear+nowMonthStr);
        String beginDayStrItem = new String(beginDayStr);
        int beginDayInt = 0;
        Date beginDate= null;
        Calendar calItem = null;
        while(beginDayInt < CurDateInt ){
            try {

                if (beginDate == null) {
                    beginDate =  format.parse(beginDayStrItem);
                    calItem =Calendar.getInstance();
                    calItem.setTime(beginDate);
                    System.out.println("beginDate = "+ beginDate);

                    int xYear = calItem.get(Calendar.YEAR);
                    int xMonth = calItem.get(Calendar.MONTH)+1;
                    String xMonthStr = xMonth>=10?xMonth+"":"0"+xMonth;
                    beginDayInt = Integer.parseInt(xYear+""+xMonthStr);
                    arrMonthArr.add(beginDayInt);
                }


                int curMonth = calItem.get(Calendar.MONTH);
                curMonth = curMonth + monthSpace;
                calItem.set(Calendar.MONTH,curMonth);

                int xYear = calItem.get(Calendar.YEAR);
                int xMonth = calItem.get(Calendar.MONTH)+1;
                String xMonthStr = xMonth>=10?xMonth+"":"0"+xMonth;
                beginDayInt = Integer.parseInt(xYear+""+xMonthStr);
                arrMonthArr.add(beginDayInt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return arrMonthArr;
    }



    static Calendar  calendar_object = Calendar.getInstance();

    static String calculXinQi2Chinese(int year , int month ,int day) {
        String xinqiValue = "";
        calendar_object.set(Calendar.YEAR,year);
        calendar_object.set(Calendar.MONTH,month-1);
        calendar_object.set(Calendar.DAY_OF_MONTH,day);
        int xinqi = calendar_object.get(Calendar.DAY_OF_WEEK) - 1;  //
        System.out.println(" Bxiniq = "+ xinqi);
        // 0 ---> 周一
        // 1 ---> 周二
        // 2---> 周三
        //
        switch (xinqi) {
            case 0:
                xinqiValue = "7";
                break;
            case 1:
                xinqiValue = "1";
                break;
            case 2:
                xinqiValue = "2";
                break;
            case 3:
                xinqiValue = "3";
                break;
            case 4:
                xinqiValue = "4";
                break;
            case 5:
                xinqiValue = "5";
                break;
            case 6:
                xinqiValue = "6";
                break;
            case 7:
                xinqiValue = "7";
                break;
            default:
                xinqiValue = "1";  // 默认周一
        }
        return xinqiValue;
    }

    static int getDayForYear(int year) {
        int year_days = 365;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            year_days = 366;
        } else {
            year_days = 365;
        }
        return year_days;
    }



    //  获取当前 月份的最后一个交易日
    static int  getLastTradeDate_In_Month(int day){

        String YearStr = (""+day).substring(0,4);
        String MonthStr = (""+day).substring(4,6);
        String DayStr = "31";
        int xyear = Integer.parseInt(YearStr);
        int xmonth = Integer.parseInt(MonthStr) ;
        int xday =  Integer.parseInt(DayStr);

        int preMonth = xmonth -1;
        if(preMonth == 0){
            preMonth = 12;
            xyear = xyear - 1;
        }
        String newMonthDec = preMonth > 10?""+preMonth:"0"+preMonth;
        YearStr = ""+xyear;
        int newDay = Integer.parseInt(YearStr+""+newMonthDec+DayStr);
        int tradeDay_lastday_inmonth = newDay;
        while(!SH_TradeDayList.contains(newDay)){
            newDay = getFutureDayFlag(newDay,-1);
            tradeDay_lastday_inmonth = newDay;
            System.out.println("tradeDay_lastday_inmonth = "+ tradeDay_lastday_inmonth);
        }

        return tradeDay_lastday_inmonth;

    }


    static int  getNearestFriday(int day){

        String YearStr = (""+day).substring(0,4);
        String MonthStr = (""+day).substring(4,6);
        String DayStr = (""+day).substring(6);
        int index = 1;
        int xyear = Integer.parseInt(YearStr);
        int xmonth = Integer.parseInt(MonthStr);
        int xday =  Integer.parseInt(DayStr);
        int xinqi = Integer.parseInt(calculXinQi2Chinese(xyear,xmonth,xday));
        if(xinqi == 5 ){
            return day;
        }else{

            int preday = getFutureDayFlag(day,-1);
            System.out.println("preday = "+ preday);

            return getNearestFriday(preday);
        }


    }

    // 1. day 和 xinqi 必须配对    2.拥挤计算当前xinqi(1.2.3.4.5.6.7) 对应的日子 day 在 今年的第几次出现
    static int calculFridayIndex2Year( int day){
        String YearStr = (""+day).substring(0,4);
        String MonthStr = (""+day).substring(4,6);
        String DayStr = (""+day).substring(6);
        int index = 1;
        int xyear = Integer.parseInt(YearStr);
        int xmonth = Integer.parseInt(MonthStr);
        int xday =  Integer.parseInt(DayStr);
        int xinqi = Integer.parseInt(calculXinQi2Chinese(xyear,xmonth,xday));
        System.out.println("xinqi_A4  =  "+xinqi);
        int days_year = getDayForYear(xyear);

        Calendar temp_calendar = Calendar.getInstance();
        temp_calendar.set(Calendar.YEAR,xyear);
        for (int i = 0; i < days_year ; i++) {
            int day_index = i+1;
            temp_calendar.set(Calendar.DAY_OF_YEAR,day_index);
            int tempYear = temp_calendar.get(Calendar.YEAR);
            int tempMonth  = temp_calendar.get(Calendar.MONTH)+1;
            int temp_day_month =  temp_calendar.get(Calendar.DAY_OF_MONTH);

            int temp_xiniq = temp_calendar.get(Calendar.DAY_OF_WEEK) - 1;  //
            if(temp_xiniq != xinqi){
                continue;   // 不是该星期那么 继续下一次循环
            }

            String temp_day_month_str =  temp_day_month>=10?temp_day_month+"":"0"+temp_day_month;
            String tempMonth_str =  tempMonth>=10?tempMonth+"":"0"+tempMonth;
            String dayIntFlag = tempYear+tempMonth_str+temp_day_month_str;
            int temp_day_int_flag = Integer.parseInt(dayIntFlag);
            //  System.out.println("tempYear = "+ tempYear + " tempMonth="+tempMonth+"   "+  "temp_day_int_flag = "+ temp_day_int_flag +"  temp_xiniq = "+ temp_xiniq   + "   day = "+ day);
            if(temp_day_int_flag == day){
                return  index;
            }
            index++;


        }
        return index;


    }



    static int  getPreDayTradeDay(int dayFlagInt ){
        int step = -1;
        int preday = getFutureDayFlag(dayFlagInt,step);
        int firstInputDay = preday;
        while(!SH_TradeDayList.contains(preday)){
//            System.out.println("~~~~~~ preday = "+ preday);

            preday = getFutureDayFlag(preday,step);
        }
//        System.out.println("getPreDayTradeDay  inputDay = "+dayFlagInt +"   outputDay = "+ preday  +"  firstInputDay = "+ firstInputDay);

        return preday;
    }



    static int  getTodayTradeDay( ){
        int step = -1;
        int now_int = getTimeStamp_YYYYMMDD_IntFlag();
        while(!SH_TradeDayList.contains(now_int)){

            now_int = getFutureDayFlag(now_int,step);
        }

        return now_int;
    }



    static int  getTomorrowTradeDay(int dayFlagInt ){
        int step = 1;
        int tomorrow = getFutureDayFlag(dayFlagInt,step);
        while(!SH_TradeDayList.contains(tomorrow)){

            tomorrow = getFutureDayFlag(tomorrow,step);
        }

        return tomorrow;
    }


   static int getFutureDayFlag(int dayFlagInt , int DaySpace){
        int curDay =  dayFlagInt;
        int futureDay = dayFlagInt;
        String daysDesc = dayFlagInt+"";
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        try {
            Date nowDate =    simple.parse(daysDesc);
            Calendar    curCalendar =Calendar.getInstance();
            curCalendar.setTime(nowDate);
            int curDayYear =   curCalendar.get(Calendar.DAY_OF_YEAR);
            int newDay2Year = curDayYear + DaySpace;
            curCalendar.set(Calendar.DAY_OF_YEAR,newDay2Year);

            int year = curCalendar.get(Calendar.YEAR);
            int month = curCalendar.get(Calendar.MONTH)+1;
            int day2month = curCalendar.get(Calendar.DAY_OF_MONTH);
            String monthDesc = month>=10?month+"":"0"+month;
            String dayDesc = day2month>=10?day2month+"":"0"+day2month;

            String dayIntFalg = year+""+monthDesc+dayDesc;
            futureDay = Integer.parseInt(dayIntFalg);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return futureDay;
    }


    @SuppressWarnings("unchecked")
     static void getKeyAndValue(Map<Integer,ArrayList<Integer>> mMapParam){
        Map.Entry<Integer , ArrayList<Integer>> entryItem;
        if(mMapParam != null){
            Iterator iterator = mMapParam.entrySet().iterator();
            while( iterator.hasNext() ){
                entryItem = (Map.Entry<Integer , ArrayList<Integer>>) iterator.next();
                Integer year =   entryItem.getKey();   //Map的Key
                ArrayList<Integer> year_day =  entryItem.getValue();  //Map的Value
                System.out.println("=========="+year + "  =========");
                for (int i = 0; i <year_day.size() ; i++) {
                    System.out.println("key = "+year + "  value["+i+"] = "+ year_day.get(i) );
                }
            }
        }
    }

    //## 1990.01.01   --- 1999.12.31 Space=10   End
// ArrayList<ArrayList<String>> init_Year_Pair_List(int year_space)  End

    //  1xts_code='',start_date='20100101',curyearend_end_date=''

 static String  getXtsCode(String codePrams){
        String resultStr = ""+x_tscode_num_default;
if(codePrams.contains("xts_code") && !codePrams.contains(",")){
    return  codePrams.replace("xts_code","");
}

String[] arrStr = codePrams.split(",");

      for (int i = 0; i < arrStr.length ; i++) {
          if(arrStr[i].contains("xts_code") &&  !arrStr[i].contains(",")){
              resultStr =  arrStr[i];
              break;
          }
      }

        return resultStr.replace("xts_code","");
    }

    static  String getNodeBodyFilePath(String nodeName){
        System.out.println("getNodeBodyFilePath nodeName = "+ nodeName);
        String bodyPath = Python_BodyTemplate_1;
        switch (nodeName){

            //  把 所有的 数据 写入一个 sheet 采用 追加的 read_excel 的方式得到 dataframe

            case "gudongrenshu":
            case "guquanzhiyamingxi":
//            case "guquanzhiyatongjishuju":
            case "qianshidaliutonggudong":
            case "qianshidagudong":
            case "caibaopiluriqibiao":   // : Read timed out.    只调用到一次 !  财报披露计划
            case "zhuyingyewugoucheng":    // : Read timed out.    一次都没调用到 !  主营业务构成
            case "caiwushenjiyijian":    //  每分钟调用2次?   Read timed out  财务审计意见
            case "caiwuzhibiaoshuju":
            case "fenhongsonggushuju":
            case "yejikuaibao":
            case "yejiyugao":
            case "zichanfuzhaibiao":
            case "xianjinliuliangbiao":
                bodyPath = Python_BodyTemplate_9;
                break;


            case "dazongjiaoyi":
            case "longhubangmeirimingxi":
            case "longhubangjigoumingxi":
            case "rongzirongquanjiaoyimingxi":
            case "rongzirongquanjiaoyihuizong":
            case "ganggutongshidachengjiaogu":    //  港股通十大成交股
            case "hushengutongchigumingxi":
            case "hushengutongshidachengjiaogu":    //   沪股通 十大成交股
                bodyPath = Python_BodyTemplate_8;
                break;

            case "yuexianhangqingtime":
                bodyPath = Python_BodyTemplate_7;
                break;


            case "zhouxianhangqingtime":
                bodyPath = Python_BodyTemplate_6;
                break;



            case "geguzijinliuxiang":
            case "meirizhibiao":
            case "rixianhangqingtime":
                bodyPath = Python_BodyTemplate_5;
                break;



            // Exception: 抱歉，您每分钟最多访问该接口10次，权限的具体详情访问
            case "IPOxingushangshi":
                bodyPath = Python_BodyTemplate_4;
                break;



            //ValueError: This sheet is too large! Your sheet size is: 1166415, 8 Max sheet size is: 1048576, 1638
            // 超过100万行  无法保存在一个 sheet
            case "guquanzhiyatongjishuju":
//            case "guquanzhiyamingxi":
            case "guanlicengxinchouhechigu":
                bodyPath = Python_BodyTemplate_3;
                break;


            //  没有中文的 cname 所以添加额外的代码 添加 cname


            case "gudongzengjianchi":
            case "xianshougujiejin":
            case "gupiaohuigou":
            case "lirunbiao":
            case "meirizhangdietingtongji":
            case "meirizhangdietingjiage":
            case "meiritingfupaixinxi":
            case "fuquanyinzi":     //   ##  添加 ts_code 列
            case "shangshigongsiguanliceystart_dateng":
            case "shangshigongsijibenxinxi":
            case "hushengutongchengfengu":
                bodyPath = Python_BodyTemplate_2;
                break;

                //  没有  ts_code  列的 返回 dataframe
            case "ganggutongmeirichengjiaotongji":
            case "hushengangtongzijinliuxiang":
                bodyPath = Python_BodyTemplate_1;
                break;




            default:
                bodyPath = Python_BodyTemplate_1;    //  没有添加 ts_code 列
        }
        return bodyPath;
    }


    static  String getLeafNodeTableName(LeafNode leafNode ){

        String tableName =leafNode.leaf_chinese_title;

        switch (leafNode.nodeName){

            case "rixianhangqingtime":
                tableName="daily_YYYYMM (日线行情保存在年月份xlsx中)";
                break;

            case "zhouxianhangqingtime":
                tableName="weekly_YYYY (周线行情保存在 年份 xlsx中)";
                break;

            case "yuexianhangqingtime":
                tableName="monthly_YYYY (月线行情保存在 年份 xlsx中)";
                break;


            case "meirizhibiao":
                tableName="daily_basic_YYYYMM (每日指标数据保存在 年月份 xlsx中)";
                break;


            case "ganggutongshidachengjiaogu":
                tableName="ggt_top10__YYYYMM (港股通十大成交股 年份保存 月份为sheet 内容为每日前十成交股)";
                break;

            case "rongzirongquanjiaoyihuizong":
                tableName="margin__YYYYMM (融资融券交易汇总 年份保存 月份为sheet 内容为每日融资融券汇总信息)";
                break;


            case "rongzirongquanjiaoyimingxi":
                tableName="margin_Detail_YYYYMM (融资融券交易明细 年份保存 月份为sheet 内容为每日融资融券交易信息)";
                break;

            case "longhubangmeirimingxi":
                tableName="top_list_YYYY (龙虎榜每日信息 年份保存 月份为sheet 内容为每日龙虎榜每日信息 )";
                break;


            case "longhubangjigoumingxi":
                tableName="top_inst_YYYY (龙虎榜机构信息 年份保存 月份为sheet 内容为每日龙虎榜机构信息息 )";
                break;

            case "dazongjiaoyi":
                tableName="block_trade_YYYY (大宗交易 年份保存 月份为sheet 内容为每日大宗交易信息 )";
                break;



            default:
                tableName =leafNode.leaf_chinese_title;
        }
        return tableName;
    }


    //  获取 数据的 mCaptureDataType 类型
    // 0 ----  默认值  缺省值
    // 0x01 ----  一次就就能调用成功的类型  与具体的   时间 和 具体的 ts_code  无关
   // 0x02 ---- 需要依据 TS_CODE  但是能一次多输入 TS_CODE  能访问的类型   与 ts_code 有关
 // 0x04  需要使用到起始时间 和结束时间  并且  只需要调用一次的 类型  简单的类型
 // 0x08   以 每天时间标识为 唯一输入 进行一次请求  每天一次  输出以年月排序
//  0x16   以 每天时间标识为 唯一输入 进行一次请求    输出以 年排序
//  0x32   以 每天时间标识为 唯一输入 进行一次请求   直接把输出 以中文名称 显示 无需 按时间存储  需要累加
// 0x0064 对 每一个 tscode (4000)个 轮流调用！
    int  ONE_CALL_TYPE = 0x0001;   // 0002 股票列表  0003交易日历   0004股票曾用名  0005沪深股通成份股   0006上市公司基本信息    0046概念股分类  0047概念股列表        0048限售股解禁
    int  TS_CODE_X_INPUT_ONE_TYPE = 0x0002; //   0007上市公司管理层    0008管理层薪酬和持股

    //  0009新股列表IPO【IPOxingushangshi】     0015每日停复牌信息【meiritingfupaixinxi】   0020沪深股通资金流向【hushengangtongzijinliuxiang】
    //  0023港股通每日成交统计【ganggutongmeirichengjiaotongji】    0045股票回购【gupiaohuigou】   0048限售股解禁【xianshougujiejin】   0051股东增减持【gudongzengjianchi】
    int  START_END_TIME_Call_ONCE_INPUT_ONE_TYPE = 0x0004 ;


    //   0011每日行情【rixianhangqingtime】     0016每日指标【meirizhibiao】  0017个股资金流向【geguzijinliuxiang】
    int  TRADE_DAY_ONE_INPUT_YYYYMM_OUTPUT_TYPE = 0x0008;

    //   0012 周线行情【zhouxianhangqingtime】    0013月线行情【yuexianhangqingtime】    0021沪深股通十大成交股【hushengutongshidachengjiaogu】
    //   0022沪深股通持股明细 【hushengutongchigumingxi】   0036港股通十大成交股【ganggutongshidachengjiaogu】  0037融资融券交易汇总【rongzirongquanjiaoyihuizong】
    // 0038 融资融券交易明细【rongzirongquanjiaoyimingxi】     0041龙虎榜每日明细【longhubangmeirimingxi】   0042龙虎榜机构明细【longhubangjigoumingxi】    0049大宗交易【dazongjiaoyi】
    int  TRADE_DAY_ONE_INPUT_YYYY_OUTPUT_TYPE = 0x0016;

    //  0018每日涨跌停价格【meirizhangdietingjiage】    0019每日涨跌停统计【meirizhangdietingtongji】
    int  TRADE_DAY_ONE_INPUT_Collect_OUTPUT_TYPE = 0x0032;

    // 0025利润表  0026资产负债表   0027资产负债表  0028业绩预告  0029业绩快报  0030分红送股  0031财务指标数据  0032财务审计意见
    int TS_CODE_ONE_Input_4000Call_Type =  0x0064;  // 0033主营业务构成   0034财报披露计划    0039前十大股东  0040前十大流通股通  0043股权质押统计数据  0044股权质押明细  0050股东人数


    // 获取当前日期的信息 如归当前没数据 那么获取昨天的数据 对数据进行一些分析输出  daily
   //   START_END_TIME_Call_ONCE_INPUT_ONE_TYPE = 0x0004 ;
    //  TRADE_DAY_ONE_INPUT_YYYYMM_OUTPUT_TYPE  = 0x0008;
    //  TRADE_DAY_ONE_INPUT_YYYY_OUTPUT_TYPE = 0x0016;  0019每日涨跌停统计
   static  ArrayList<String> dailyQueryNodeNameList = new   ArrayList<String>();

   static{
       dailyQueryNodeNameList.add("IPOxingushangshi");  //   0009新股列表IPO【IPOxingushangshi】
       dailyQueryNodeNameList.add("meiritingfupaixinxi");  //   0015每日停复牌信息【meiritingfupaixinxi】
       dailyQueryNodeNameList.add("hushengangtongzijinliuxiang"); //    0020沪深股通资金流向【hushengangtongzijinliuxiang】
       dailyQueryNodeNameList.add("ganggutongmeirichengjiaotongji");  //  0023港股通每日成交统计【ganggutongmeirichengjiaotongji】
       dailyQueryNodeNameList.add("gupiaohuigou");   //   0045股票回购【gupiaohuigou】
       dailyQueryNodeNameList.add("xianshougujiejin");  //   0048限售股解禁【xianshougujiejin】
       dailyQueryNodeNameList.add("gudongzengjianchi");  //   0051股东增减持【gudongzengjianchi】
       dailyQueryNodeNameList.add("rixianhangqingtime");  // 0011每日行情【rixianhangqingtime】
       dailyQueryNodeNameList.add("meirizhibiao");  //   0016每日指标【meirizhibiao】
       dailyQueryNodeNameList.add("geguzijinliuxiang");  //  0017个股资金流向【geguzijinliuxiang】
       dailyQueryNodeNameList.add("zhouxianhangqingtime");  // 0012 周线行情【zhouxianhangqingtime】
       dailyQueryNodeNameList.add("yuexianhangqingtime");  //  0013月线行情【yuexianhangqingtime】
       dailyQueryNodeNameList.add("hushengutongshidachengjiaogu");  //  0021沪深股通十大成交股【hushengutongshidachengjiaogu】
       dailyQueryNodeNameList.add("hushengutongchigumingxi");  //   //   0022沪深股通持股明细 【hushengutongchigumingxi】
       dailyQueryNodeNameList.add("ganggutongshidachengjiaogu");  // 0036港股通十大成交股【ganggutongshidachengjiaogu】
       dailyQueryNodeNameList.add("rongzirongquanjiaoyihuizong");  // 0037融资融券交易汇总【rongzirongquanjiaoyihuizong】
       dailyQueryNodeNameList.add("rongzirongquanjiaoyimingxi");  //0038 融资融券交易明细【rongzirongquanjiaoyimingxi】
       dailyQueryNodeNameList.add("longhubangmeirimingxi");  // 0041龙虎榜每日明细【longhubangmeirimingxi】
       dailyQueryNodeNameList.add("longhubangjigoumingxi");  //  0042龙虎榜机构明细【longhubangjigoumingxi】
       dailyQueryNodeNameList.add("dazongjiaoyi");  //  0049大宗交易【dazongjiaoyi】
       dailyQueryNodeNameList.add("meirizhangdietingjiage");  //  0018每日涨跌停价格【meirizhangdietingjiage】
       dailyQueryNodeNameList.add("meirizhangdietingtongji");  //   0019每日涨跌停统计【meirizhangdietingtongji】
   }
    // 手动输入比 tscode 值 来进行查询

    // 数据完全下载之后的更新操作

    // 数据完整后的 查询操作
    //  持续更新数据操作
    // 天更新数据操作
    //周更新数据操作
    // 季度更新数据操作
   // 年更新数据操作

    // prop 文件数值的操作  清空 以及恢复

    static  int getNodeDataCaptureType(TreeNode treeNode){
        int mCaptureDataType = 0;

        return mCaptureDataType;
    }
    static  String getNodeDescWithName(TreeNode treeNode ,String paddinggIndex , boolean isSingle){
        StringBuilder  mDescSB = new StringBuilder();
        mDescSB.append("名称: 【");

        String descType = isSingle?"【对指定索引 单一 single-python进行更新执行!】":"【把索引作为起始点 执行之后所有的代码更新及操作】";

        switch (treeNode.nodeName){

            //  把 所有的 数据 写入一个 sheet 采用 追加的 read_excel 的方式得到 dataframe




            case "gupiaoliebiao":
                mDescSB.append("股票列表");
                break;



            case "jiaoyirili":
                mDescSB.append("交易日历");
                break;


            case "gupiaocengyongming":
                mDescSB.append("股票曾用名");
                break;

            case "gainiangufenlei":
                mDescSB.append("概念股分类");
                break;
            case "gainianguliebiao":
                mDescSB.append("概念股列表");
                break;

                //     Default Over



            case "gudongrenshu":
                mDescSB.append("股东人数");
             break;

            case "guquanzhiyamingxi":
                mDescSB.append("股权质押明细");
                break;

            case "guquanzhiyatongjishuju":
                mDescSB.append("股权质押统计明细");
                break;

            case "qianshidaliutonggudong":
                mDescSB.append("前十大流通股");
                break;

            case "qianshidagudong":
                mDescSB.append("前十大股东");
                break;
            case "caibaopiluriqibiao":   // : Read timed out.    只调用到一次 !  财报披露计划
                mDescSB.append("财报披露日期标");
                break;


            case "zhuyingyewugoucheng":    // : Read timed out.    一次都没调用到 !  主营业务构成
                mDescSB.append("主营业务构成");
                break;

            case "caiwushenjiyijian":    //  每分钟调用2次?   Read timed out  财务审计意见
                mDescSB.append("财务审计意见");
                break;

            case "caiwuzhibiaoshuju":
                mDescSB.append("财务指标数据");
                break;

            case "fenhongsonggushuju":
                mDescSB.append("分红送股数据");
                break;

            case "yejikuaibao":
                mDescSB.append("分红送股数据");
                break;

            case "yejiyugao":
                mDescSB.append("业绩快报");
                break;

            case "zichanfuzhaibiao":
                mDescSB.append("资产负债表");
                break;

            case "xianjinliuliangbiao":
                mDescSB.append("现金流量表");
                break;

            case "dazongjiaoyi":
                mDescSB.append("大宗交易");
                break;

            case "longhubangmeirimingxi":
                mDescSB.append("龙虎榜每日信息");
                break;

            case "longhubangjigoumingxi":
                mDescSB.append("龙虎榜机构信息");
                break;

            case "rongzirongquanjiaoyimingxi":
                mDescSB.append("融资融券交易信息");
                break;

            case "rongzirongquanjiaoyihuizong":
                mDescSB.append("融资融券交易汇总");
                break;

            case "ganggutongshidachengjiaogu":    //  港股通十大成交股
                mDescSB.append("港股通十大成交股");
                break;

            case "hushengutongchigumingxi":
                mDescSB.append("沪股通持股明细");
                break;

            case "hushengutongshidachengjiaogu":    //   沪股通 十大成交股
                mDescSB.append("沪深通十大成交股");
                break;

            case "yuexianhangqingtime":
                mDescSB.append("月线行情v时间");
                break;



            case "zhouxianhangqingtime":
                mDescSB.append("周线行情v时间");
                break;

            case "rixianhangqingtime":
                mDescSB.append("日线行情v时间");
                break;

            case "geguzijinliuxiang":
                mDescSB.append("个股资金流量");
                break;

            case "meirizhibiao":
                mDescSB.append("每日指标");
                break;


            // Exception: 抱歉，您每分钟最多访问该接口10次，权限的具体详情访问
            case "IPOxingushangshi":
                mDescSB.append("IPO 新股上市");
                break;



            //ValueError: This sheet is too large! Your sheet size is: 1166415, 8 Max sheet size is: 1048576, 1638
            case "guanlicengxinchouhechigu":
                mDescSB.append("管理层薪酬和持股");
                break;


            //  没有中文的 cname 所以添加额外的代码 添加 cname


            case "gudongzengjianchi":
                mDescSB.append("股东增减持");
                break;

            case "xianshougujiejin":
                mDescSB.append("限售股解禁");
                break;

            case "gupiaohuigou":
                mDescSB.append("股票回购");
                break;


            case "lirunbiao":
                mDescSB.append("利润表");
                break;

            case "meirizhangdietingtongji":
                mDescSB.append("每日涨跌停统计");
                break;

            case "meirizhangdietingjiage":
                mDescSB.append("每日涨跌停价格");
                break;

            case "meiritingfupaixinxi":
                mDescSB.append("每日停复牌信息");
                break;

            case "fuquanyinzi":     //   ##  添加 ts_code 列
                mDescSB.append("复权因子");
                break;

            case "shangshigongsiguanliceng":
                mDescSB.append("上市公司管理层");
                break;

            case "shangshigongsijibenxinxi":
                mDescSB.append("上市公司基本信息");
                break;

            case "hushengutongchengfengu":
                mDescSB.append("沪深股通成分股");
                break;

            //  没有  ts_code  列的 返回 dataframe
            case "ganggutongmeirichengjiaotongji":
                mDescSB.append("港股通每日成交统计");
                break;

            case "hushengangtongzijinliuxiang":
                mDescSB.append("沪深港通资金流向");
                break;


            default:

        }

        if(treeNode instanceof  LeafNode){
            LeafNode leafNode = (LeafNode)treeNode;

            mDescSB.append(":"+treeNode.nodeName+"】【Python-函数:"+leafNode.pythonMethodName+"】 【xlsx中文表名: "+getLeafNodeTableName(leafNode)+"】 索引值:"+paddinggIndex+"   "+descType);
        }else{
            // pythonNode
            mDescSB.append(":"+treeNode.nodeName+"】 索引值:"+paddinggIndex+"   "+descType);
        }


        return mDescSB.toString();
    }



    static  String getNodeHeadFile(String nodeName){
        System.out.println("getNodeHeadFile nodeName = "+ nodeName);
        String headPath = Python_HeadTemplate_Path;
        switch (nodeName){

            case "?":
                headPath = "?";
                break;

            default:
                headPath = Python_HeadTemplate_Path;
        }
        return headPath;
    }
    static int  x_tscode_num_default = 50;   // xcode ts_code 的默认的个数
    static int default_space_year = 10;   // 11ystart_date  11yend_date  时间间隔的长度 单位年 y  10ystart_date

    
    
    
	// 只有过了 17 点 才会有 今天的 交易数据  所以 这里 判断 如果 过了 17点 那么返回今天的日期  不到17点 那么返回昨天的日期
	static int getCurrentYYYYMMDDWith17Point() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		int refrashDay  = getCurrentYYYYMMDD() ;


	    long today17Long =   getToday17PointTimeLong();
	    
	    long nowLong  = Calendar.getInstance().getTimeInMillis();
	    if(nowLong > today17Long) {    // 晚上过来17点了  可以得到今天数据
	    	refrashDay = getCurrentYYYYMMDD() ;
	    }else {
	    	 // 还没到今天17点  只可以得到昨天数据
	    	refrashDay  = 	getYesterTradeDayIntFlag(getCurrentYYYYMMDD());

	    }
	    
		return refrashDay;

	}
	
	
	static int  getYesterTradeDayIntFlag(int dayFlagInt ){
	    int step = -1;
	    int tomorrow = getFutureDayFlag(dayFlagInt,step);
	if(SH_TradeDayList.size() == 0) {
		initTradeDayList();
	}
		
	while(!SH_TradeDayList.contains(tomorrow)) {  //  如果 不包含今日  那么 往后 计算 
			
		 tomorrow = getFutureDayFlag(tomorrow,step);
		}

	    return tomorrow;
	}
	
	
	public static Long getToday17PointTimeLong(){
	    Calendar c1 = Calendar.getInstance();
//	    c1.add(Calendar.DATE,-1);
	    c1.set(Calendar.HOUR_OF_DAY,17);
	    c1.set(Calendar.MINUTE,0);
	    c1.set(Calendar.SECOND,0);
	    c1.set(Calendar.MILLISECOND,0);
	    //下面两句可以省略
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    System.out.print("getToday17PointTimeLong:"+simpleDateFormat.format(c1.getTime()));
	    return c1.getTimeInMillis();
	}
	
	static int getCurrentYYYYMMDD() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		return Integer.parseInt(df.format(new Date()));

	}
	
    
    static int getCurrentYear() {

    	SimpleDateFormat df = new SimpleDateFormat("YYYY");

    	return Integer.parseInt(df.format(new Date()));

    }
    
}





// 管理层薪酬和持股 异常   J0_0008_guanlicengxinchouhechigu   Python_BodyTemplate_3     长度超过100w条 那么 另起别的sheet保存
// Traceback (most recent call last):
//   File "C:\Users\zhuzj5\Desktop\zbin\J0_Python\J0_0008_guanlicengxinchouhechigu.py", line 12889, in <module>
//     stk_rewards_data_List.to_excel(stk_rewards_excel_writer, '管理层薪酬和持股')
//   File "C:\Users\zhuzj5\AppData\Local\Programs\Python\Python37\lib\site-packages\pandas\core\generic.py", line 2256, in to_excel
//     engine=engine,
//   File "C:\Users\zhuzj5\AppData\Local\Programs\Python\Python37\lib\site-packages\pandas\io\formats\excel.py", line 724, in write
//     + "Max sheet size is: {}, {}".format(self.max_rows, self.max_cols)
// ValueError: This sheet is too large! Your sheet size is: 1160717, 8 Max sheet size is: 1048576, 16384


//    Traceback (most recent call last):
//        File "C:\Users\zhuzj5\Desktop\zbin\J0_Python\J0_0008_guanlicengxinchouhechigu.py", line 10490, in <module>
//    stk_rewards_data_List.save()
//            File "C:\Users\zhuzj5\AppData\Local\Programs\Python\Python37\lib\site-packages\pandas\core\generic.py", line 5179, in __getattr__
//            return object.__getattribute__(self, name)
//            AttributeError: 'DataFrame' object has no attribute 'save'