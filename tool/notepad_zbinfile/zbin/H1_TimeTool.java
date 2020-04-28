

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 1.  给出 xxxxx ms  给出时间
// 2.  给出两个时间 算出差值
//  3.  给出一个时间点  算出改点是今天的多少分  多少秒  多少小时

public class H1_TimeTool {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "ztime_H1";


/*******************修改属性列表 ------End *********************/



    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File G8_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+".properties");
    static InputStream G8_Properties_InputStream;
    static OutputStream G8_Properties_OutputStream;
    static Properties G8_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE ;   // 当前 CMDER的路径 File 文件


    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_ParamStrList = new ArrayList<>();




    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static  HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new  HashMap<String, ArrayList<File>>(); ;





//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    // 获取 运行时 参数
//    static JavaRuntimeInfo JavaRuntimeInfoValue =  new JavaRuntimeInfo();

    // PATH 环境变量值进行当前的保存处理  获取 Path参数



    static String EnvironmentValue= System.getProperties().getProperty("java.library.path");




    static boolean  isContainEnvironment(String program){
        boolean environmentExist = false;
        if(EnvironmentValue.contains(program)){
            environmentExist = true;
        }
        return environmentExist;
    }



    // A1  ..... A2.
    static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name){
        String mCharNumber = "error";
        String curBat =mCur_Bat_Name;
        if(mCur_Bat_Name.contains(".sh")){
            curBat = curBat.replace(".sh","");
        }

        if(mCur_Bat_Name.contains(".bat")){
            curBat = curBat.replace(".bat","");
        }
        if(curBat.contains("_")){
            String[] arrNameList =    curBat.split("_");
            mCharNumber =   arrNameList[arrNameList.length-1];
        }else{
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




    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name+".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
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

    public static String ReadFileContent( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            //  System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        StringBuilder sb= new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine+"\n");
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


    // strValue  是字符串

    // padinglength 是需要的长度
    // oneStr 是填充的字符 的那个字符
    // dirPre true---前填充   false-后填充
    static  String  getPaddingIntString(String strValue , int padinglength , String oneStr , boolean dirPre){
        String result = ""+strValue;
        int length = (""+strValue).length();

        if(length < padinglength){
            int distance = padinglength  - length;
            for (int i = 0; i < distance; i++) {
                if(dirPre){
                    result = oneStr+result;
                }else{
                    result = result + oneStr;
                }

            }

        }
        return result;

    }









    static ArrayList<File> getAllSubFile(File dirFile ,String aospPath , ArrayList<String> typeList) {
        if(aospPath == null || "".equals(aospPath)){
            return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
        }
        return getAllSubFile(dirFile.getAbsolutePath(), aospPath, typeList);

    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath,  ArrayList<String>  typeList) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
                    for (int i = 0; i < typeList.size(); i++) {
                        String type =  typeList.get(i);
                        if("*".equals(type)){  // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
                            File curFile =    new File(fileString);
                            if(!curFile.isDirectory()){
                                allFile.add(curFile);
                                break;
                            }


                        }else {
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
       System.out.println("用户输入的参数为空  或者 参数不符合规格  请参考如下 重新输入:");
        System.out.println("提示1:  时间戳使用格式: 2020-04-26_16:16:00.550  16:16:16.16  16:16:16   16:18 ");  // 输入 提示  输入格式 提示
        System.out.println("提示2:  标识时间长度使用  1000s 40h1000s 100000s  35d15h12m500s  Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒");
        System.out.println();
        System.out.println("提示3:  "+Cur_Bat_Name+"  2020-04-26_16:16:00.550+7y66M35d15h12m500s   ## 时间戳+时间长度  ");
        System.out.println("提示4:  "+Cur_Bat_Name+"  2020-04-26_16:16:00.550-7y66M35d15h12m500s   ## 时间戳-时间长度   ");
        System.out.println();
        System.out.println("提示5:  "+Cur_Bat_Name+"  2020-04-26_16:16:00.550   ## 时间戳1   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  2020-04-26_16:16:00       ## 时间戳2   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  2020-04-26_16:16          ## 时间戳3   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  2020-04-26_16             ## 时间戳4   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  16:16:00.550              ## 时间戳5   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  16:16:00                  ## 时间戳6   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  16:16                     ## 时间戳7   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  +7y66M35d15h12m500s       ## 时间长度1   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  -7y66M35d15h12m500s       ## 时间长度2   ");
        System.out.println("提示5:  "+Cur_Bat_Name+"  1y1M1w1d1h1m1s            ## 时间长度3   ");

        System.out.println();
        System.out.println("额外提示信息:");
        Calendar now = Calendar.getInstance();

        now.set(now.DAY_OF_WEEK, now.MONDAY);


        int year  = now.get(Calendar.YEAR);
        int month  = now.get(Calendar.MONTH) + 1;
        int day  = now.get(Calendar.DAY_OF_MONTH);
        int hour  = now.get(Calendar.HOUR);
        int mintus  = now.get(Calendar.MINUTE);
        int second =  now.get(Calendar.SECOND);
        int millsecond =  now.get(Calendar.MILLISECOND);
        int week =  now.get(Calendar.WEEK_OF_MONTH);
        int xinqi =  now.get(Calendar.DAY_OF_WEEK);  // 1是星知期日道，7是星期版六。
        int weekNum =  now.get(Calendar.WEEK_OF_YEAR);
        int weekNum1 =  now.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int dayofYear =  now.get(Calendar.DAY_OF_YEAR);
        int hourofday =  now.get(Calendar.HOUR_OF_DAY);
        int date =  now.get(Calendar.DATE);
        int monthDays = getDayForMonth_Year(year,month);   // 计算该月的天数
        int monthHours = monthDays * 24;
        int monthMintues = monthHours * 60;
        int monthSecond = monthMintues * 60;

        // 计算当前今日过去的 秒数  分钟数
        Calendar beginTody = Calendar.getInstance();
        beginTody.set(beginTody.DAY_OF_WEEK, beginTody.MONDAY);
        beginTody.set(Calendar.HOUR_OF_DAY,0);
        beginTody.set(Calendar.HOUR,0);  //  时分秒 毫秒 都设置0
        beginTody.set(Calendar.MINUTE,0);  //  时分秒 毫秒 都设置0
        beginTody.set(Calendar.SECOND,0);  //  时分秒 毫秒 都设置0
        beginTody.set(Calendar.MILLISECOND,0);  //  时分秒 毫秒 都设置0
        beginTody.set(Calendar.HOUR_OF_DAY,0);
        long todaySecondsUse = (now.getTimeInMillis() - beginTody.getTimeInMillis())/1000;
        long todayMinutes = (todaySecondsUse/60);
        long second_minutes =(int)todaySecondsUse%60;
        int todayMinutes_SecondPercent = (int)((((double)second_minutes)/60)* 100);




        int daysForYear = calculRunYear(year); // 一年多少天
        boolean isBigYear = (daysForYear==366);  // 是否是闰年
        long   hourForYear  =  daysForYear * 24;
        long mintuesForYear = hourForYear * 60;
        long secondForYear = mintuesForYear * 60;
        String xinqiStr = calculXinQi(xinqi);

        long dayWeHave = daysForYear - dayofYear;
        float dayUsePersent = ((float)dayofYear/daysForYear) * 100;
        float dayWeHavePersent = ((float)dayWeHave/daysForYear)*100;
/*
        System.out.println("year = "+year);
        System.out.println("month ="+month);
        System.out.println("day = "+day);
        System.out.println("hour = "+ hour);
        System.out.println("mintus = "+mintus);
        System.out.println("second = "+second);
        System.out.println("millsecond = "+millsecond);
        System.out.println("week = "+week);
        System.out.println("xinqi = "+xinqi);
        System.out.println("WEEK_OF_YEAR = "+ weekNum);
        System.out.println("DAY_OF_WEEK_IN_MONTH = "+ weekNum1);
        System.out.println("DAY_OF_YEAR = "+dayofYear);
        System.out.println("HOUR_OF_DAY = "+hourofday);
        System.out.println("Calendar.DATE = "+date);

        year = 2020
        month =3
        day = 28
        hour = 11
        mintus = 37
        second = 21
        millsecond = 506
        week = 5   // 第五周
        xinqi = 3   // 周三
        WEEK_OF_YEAR = 18   // 一年的第18个周
        DAY_OF_WEEK_IN_MONTH = 4   //   在这个月已经过了几次 周X
        DAY_OF_YEAR = 119    // 一年的第几天

        HOUR_OF_DAY = 11   //  几点
        Calendar.DATE = 28  //  日期
                */


/*

══════════════ 2020润年 ═ 366天 ═ 8784小时 ═ 527040分钟 ═ 31622400秒 ══════════════
        秒数级别(s):    ║ 已过: 10281600s ║ 剩余: 21340800s ║
        分钟级别(m):    ║ 已过: 171360m   ║ 剩余: 355680m   ║
        小时级别(h):    ║ 已过: 2856h     ║ 剩余: 5928h     ║
        天数级别(d):    ║ 已过: 119d      ║ 剩余: 247d      ║
        百分占比(%):    ║ 已过: 92.213%   ║ 剩余: 67.486%   ║


══════════════ 2020.4月 ═ 30天 ═ 720小时 ═ 43200分钟 ═ 2592000秒 ══════════════
        秒数级别(s):    ║ 已过: 2390158s  ║ 剩余: 201842s   ║
        分钟级别(m):    ║ 已过: 39835.96m ║ 剩余: 3364.03m  ║
        小时级别(h):    ║ 已过: 663.93h   ║ 剩余: 56.07h    ║
        天数级别(d):    ║ 已过: 27.66d    ║ 剩余: 2.34d     ║
        百分占比(%):    ║ 已过: 92.213%   ║ 剩余: 7.787%    ║


══════════════ 2020年4月第5周 ═ 7天 ═ 168小时 ═ 10080分钟 ═604800秒 ══════════════
        该月份有周数:    ║  共 5 周    ║
        该周的周索引:    ║  第 5 周    ║
        周一(星期一):    ║  2020.04.15 ║
        周天(星期天):    ║  2020.04.22 ║
        今日(星期X ):    ║  2020.04.18 ║ 该周日(星期X)该月出现第X次

══════════════ 2020.04.28日 ═ 1天 ═ 24小时 ═ 1440分钟 ═ 86400秒 ══════════════
        今日时刻(t):    ║ 时刻: 16:19:40.967 ║                ║
        秒数级别(s):    ║ 已过: 57358s       ║ 剩余: 29042s   ║
        分钟级别(m):    ║ 已过: 955.96m      ║ 剩余: 484.03m  ║
        百分占比(%):    ║ 已过: 66.387%      ║ 剩余: 33.613%  ║
        */

int itemLength =  24;  //  一个框的长度


        System.out.println("══════════════ "+"2020"+(isBigYear?"润":"平")+"年 ═ "+daysForYear+"天 ═ "+hourForYear+"小时 ═ "+mintuesForYear+"分钟 ═ "+secondForYear+"秒 ══════════════");
//【1_1】        今年2020润年 : 366天 : 8784小时 : 527040分钟 : 31622400秒
//══════════════ 2020润年 ═ 366天 ═ 8784小时 ═ 527040分钟 ═ 31622400秒 ══════════════

 //【1_2】 今年已过 119 天:  剩余 247 天= 已过:2856小时  剩余:5928 小时 = 已过:171360分钟  剩余:355680 分钟 = 已过:10281600秒  剩余:21340800 秒 =    已使用:32.514% 剩余:67.486%
 // 秒数级别(s):    ║ 已过: 10281600s ║ 剩余: 21340800s ║
// 分钟级别(m):    ║ 已过: 171360m   ║ 剩余: 355680m   ║
// 小时级别(h):    ║ 已过: 2856h     ║ 剩余: 5928h     ║
//天数级别(d):    ║ 已过: 119d      ║ 剩余: 247d      ║
 //百分占比(%):    ║ 已过: 92.213%   ║ 剩余: 67.486%   ║
        String dayUsePersentStr = String.format("%.3f",dayUsePersent);
        String dayWeHavePersentStr = String.format("%.3f",dayWeHavePersent);

        System.out.println("秒数级别(s):    ║ 已过: "+padding20BlankStr((dayofYear*24*60*60)+"s")+"║ 剩余: "+padding20BlankStr((dayWeHave*24*60*60)+"s")+"║");
        System.out.println("分钟级别(m):    ║ 已过: "+padding20BlankStr((dayofYear*24*60)+"m")+"║ 剩余: "+padding20BlankStr((dayWeHave*24*60)+"m")+"║");
        System.out.println("小时级别(h):    ║ 已过: "+padding20BlankStr((dayofYear*24)+"h")+"║ 剩余: "+padding20BlankStr((dayWeHave*24)+"h")+"║");
        System.out.println("天数级别(d):    ║ 已过: "+padding20BlankStr((dayofYear)+"d")+"║ 剩余: "+padding20BlankStr((dayWeHave)+"d")+"║");
        System.out.println("百分占比(%):    ║ 已过: "+padding20BlankStr((dayUsePersentStr)+"%")+"║ 剩余: "+padding20BlankStr((dayWeHavePersentStr)+"%")+"║");


        System.out.println();

/*
        System.out.println("今年"+ year+ (isBigYear?"润":"平")+"年 : "+daysForYear+"天 : "+hourForYear+"小时 : "+mintuesForYear+"分钟 : "+secondForYear+"秒");

        System.out.println("今年已过 "+dayofYear+" 天:  剩余 "+dayWeHave+" 天"+"= "+
              "已过:"+  (dayofYear*24)+"小时  剩余:"+(dayWeHave*24)+" 小时"+" = "+
                "已过:"+  (dayofYear*24*60)+"分钟  剩余:"+(dayWeHave*24*60)+" 分钟"+" = "+
                "已过:"+  (dayofYear*24*60*60)+"秒  剩余:"+(dayWeHave*24*60*60)+" 秒"+" = "+
                "   已使用:"+dayUsePersentStr+"%"   + " 剩余:" +dayWeHavePersentStr+"%" );

        */
//        System.out.println("xxx月==xxx天===xxx小时===xxx分钟==xxx秒");


        // 这个月  已过了 多少百分比
        Calendar beginMonthCalendar = Calendar.getInstance();

        beginMonthCalendar.set(beginMonthCalendar.DAY_OF_WEEK, beginMonthCalendar.MONDAY);

        beginMonthCalendar.set(Calendar.DAY_OF_MONTH,1); // 设置成月初
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY,0);
        beginMonthCalendar.set(Calendar.HOUR,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MINUTE,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.SECOND,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MILLISECOND,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY,0);


        // 这个月 从月初到现在的时间 秒数
        long thisMonthSecondUse = (now.getTimeInMillis() - beginMonthCalendar.getTimeInMillis())/1000;


        // 这个月 总的时间秒数
        long thisMonthAllSecond = monthDays * 24 * 60 * 60;

        // 这个月剩余的秒数
        long thisMonthSecondWeHave = thisMonthAllSecond - thisMonthSecondUse;


        float thisMonthMintuesUse = (float)thisMonthSecondUse/60;
        float thisMonthMintusWeHave = (float)thisMonthSecondWeHave/60;


        float thisMonthHourUse = (float)thisMonthMintuesUse/60;
        float thisMonthHourWeHave = (float)thisMonthMintusWeHave/60;


        float thisMonthDayUse = (float)thisMonthHourUse/24;
        float thisMonthDayWeHave = (float)thisMonthHourWeHave/24;


        String thisMonthMintuesUseStr = String.format("%.2f",thisMonthMintuesUse);
        String thisMonthMintusWeHaveStr = String.format("%.2f",thisMonthMintusWeHave);

        String thisMonthHourUseStr = String.format("%.2f",thisMonthHourUse);
        String thisMonthHourWeHaveStr = String.format("%.2f",thisMonthHourWeHave);

        String thisMonthDayUseStr = String.format("%.2f",thisMonthDayUse);
        String thisMonthDayWeHaveStr = String.format("%.2f",thisMonthDayWeHave);


        float monthUsePersent = ((float)thisMonthDayUse/monthDays) * 100;
        float monthWeHavePersent = ((float)thisMonthDayWeHave/monthDays)*100;
        String monthUsePersentStr = String.format("%.3f",monthUsePersent);
        String monthWeHavePersentStr = String.format("%.3f",monthWeHavePersent);


/*        ══════════════ 2020.4月 ═ 30天 ═ 720小时 ═ 43200分钟 ═ 2592000秒 ══════════════
        秒数级别(s):    ║ 已过: 2390158s  ║ 剩余: 201842s   ║
        分钟级别(m):    ║ 已过: 39835.96m ║ 剩余: 3364.03m  ║
        小时级别(h):    ║ 已过: 663.93h   ║ 剩余: 56.07h    ║
        天数级别(d):    ║ 已过: 27.66d    ║ 剩余: 2.34d     ║
        百分占比(%):    ║ 已过: 92.213%   ║ 剩余: 7.787%    ║

        */

        System.out.println("══════════════ "+year+"."+padding2SizeStr(month)+"月 ═ "+monthDays+"天 ═ "+monthHours+"小时 ═ "+monthMintues+"分钟 ═ "+monthSecond+"秒 ══════════════");
        System.out.println("秒数级别(s):    ║ 已过: "+padding20BlankStr((thisMonthSecondUse)+"s")+"║ 剩余: "+padding20BlankStr((thisMonthSecondWeHave)+"s")+"║");
        System.out.println("分钟级别(m):    ║ 已过: "+padding20BlankStr((thisMonthMintuesUseStr)+"m")+"║ 剩余: "+padding20BlankStr((thisMonthMintusWeHaveStr)+"m")+"║");
        System.out.println("小时级别(h):    ║ 已过: "+padding20BlankStr((thisMonthHourUseStr)+"h")+"║ 剩余: "+padding20BlankStr((thisMonthHourWeHaveStr)+"h")+"║");
        System.out.println("天数级别(d):    ║ 已过: "+padding20BlankStr((thisMonthDayUseStr)+"d")+"║ 剩余: "+padding20BlankStr((thisMonthDayWeHaveStr)+"d")+"║");
        System.out.println("百分占比(%):    ║ 已过: "+padding20BlankStr((monthUsePersentStr)+"%")+"║ 剩余: "+padding20BlankStr((monthWeHavePersentStr)+"%")+"║");

        System.out.println();

/*
        System.out.println("今月"+month+"月 : "+monthDays+"天 : "+monthHours+"小时 : "+monthMintues+"分钟 : "+monthSecond+"秒");
        System.out.println("今月已使用: "+ thisMonthSecondUse +"秒" +" 剩余 "+ thisMonthSecondWeHave+"秒  ="+
                thisMonthMintuesUseStr +"分钟" +" 剩余 "+thisMonthMintusWeHaveStr+"分钟  = "   +
                thisMonthHourUseStr +"小时" +" 剩余 "+thisMonthHourWeHaveStr+"小时  = " +
                thisMonthDayUseStr +"天" +" 剩余 "+thisMonthDayWeHaveStr+"天 ="+
                "该月已使用占比:"+monthUsePersentStr+"%" + " 未使用占比:"+monthWeHavePersentStr+"%"
        );

        */

/*
══════════════ 2020年4月第5周 ═ 7天 ═ 168小时 ═ 10080分钟 ═604800秒 ══════════════
        该月份有周数:    ║  共 5 周    ║
        该周的周索引:    ║  第 5 周    ║
        周一(星期一):    ║  2020.04.15 ║
        周天(星期天):    ║  2020.04.22 ║
        今日(星期X ):    ║  2020.04.18 ║ 该周日(星期X)该月出现第X次
*/

//        System.out.println("一个月的第一个星期从第一个星期一开始!");
        String tip1 = "一个月的第一个星期从第一个星期一开始!";
String blankStr = "      ";
        System.out.println("══════════════ "+year+"年"+month+"月共"+week+"周第"+weekNum1+"周 ═ 7天 ═ 168小时 ═ 10080分钟 ═604800秒 ══════════════" + tip1);
        System.out.println("该月份有周数:   ║ 共 "+(week+"")    +" 周           "+blankStr+"║");
        System.out.println("周一(星期一):   ║ "+padding20BlankStr(calculXinqiYi1())+blankStr+"║");
        System.out.println("周天(星期天):   ║ "+padding20BlankStr(calculXinqiYi7())+blankStr+"║");
        System.out.println("今日("+xinqiStr+"):   ║ "+padding20BlankStr(padding2SizeStr(year)+"-"+padding2SizeStr(month)+"-"+padding2SizeStr(day))+ blankStr+"║");
        System.out.println("月过周"+calculXinQi2Chinese(xinqi)+"次数:   ║ 第 "+(weekNum1+"")+" 次           "+blankStr+"║");
//        System.out.println();

//        System.out.println("一个月的第一个星期从第一个星期一开始!");
        printWeeks();
        System.out.println();
/*

        System.out.println("今周 "+month+"月第"+week+"周"  + "  该月总共有"+week+"周" +"   1周 = 7天 = "+(24*7)+" 小时 =" + (24*7*60)+"分钟 = " + (24*7*60*60) +"秒"   );
        System.out.println("今日 "+month+"月"+day+"日 "+xinqiStr + " 该月第 "+weekNum1+"个"+xinqiStr  +"  1天 = "+(24*1)+" 小时 =" + (24*60)+"分钟 = " + (24*60*60) +"秒");

*/



//        String   todayMinutes_SecondPercentStr =  String.format("%2f.",todayMinutes_SecondPercent);


        long daySecondWehave = 86400 - todaySecondsUse;
        float dayMintusWehave = ((float)daySecondWehave/60) ;
        String dayMintusWehaveStr = String.format("%.2f",dayMintusWehave);

        float todaySecondsUsePercent = ((float)todaySecondsUse/86400) * 100;
        float monthWeHavePersentPercent = ((float)daySecondWehave/86400)*100;
        String todaySecondsUsePercentStr = String.format("%.3f",todaySecondsUsePercent);
        String monthWeHavePersentPercentStr = String.format("%.3f",monthWeHavePersentPercent);

        float dayHourWehave = ((float)dayMintusWehave/60) ;
        String dayHourWehaveStr = String.format("%.3f",dayHourWehave);
/*

══════════════ 2020.04.28日 ═ 1天 ═ 24小时 ═ 1440分钟 ═ 86400秒 ══════════════
        今日时刻(t):    ║ 时刻: 16:19:40.967 ║                ║
        秒数级别(s):    ║ 已过: 57358s       ║ 剩余: 29042s   ║
        分钟级别(m):    ║ 已过: 955.96m      ║ 剩余: 484.03m  ║
        百分占比(%):    ║ 已过: 66.387%      ║ 剩余: 33.613%  ║


*/


        System.out.println("══════════════ "+year+"-"+padding2SizeStr(month)+"-"+padding2SizeStr(day)+""+" ═ "+xinqiStr+" ═ 1天 ═ 24小时 ═ 1440分钟 ═ 86400秒 ══════════════");
        System.out.println("今日时刻(t):    ║ 时刻: "+padding20BlankStr(padding2SizeStr(hourofday)+":"+padding2SizeStr(mintus)+":"+ padding2SizeStr(second)+"."+padding3SizeStr(millsecond)) +"║ 剩余: "+padding20BlankStr((dayHourWehaveStr+"h"))+"║");
        System.out.println("秒数级别(s):    ║ 已过: "+padding20BlankStr((todaySecondsUse)+"s")+"║ 剩余: "+padding20BlankStr((daySecondWehave)+"s")+"║");
        System.out.println("分钟级别(m):    ║ 已过: "+padding20BlankStr((todayMinutes)+"m")+"║ 剩余: "+padding20BlankStr((dayMintusWehaveStr)+"m")+"║");
        System.out.println("百分占比(%):    ║ 已过: "+padding20BlankStr((todaySecondsUsePercentStr)+"%")+"║ 剩余: "+padding20BlankStr((monthWeHavePersentPercentStr)+"%")+"║");


        System.out.println();


/*        System.out.println("今时: "+padding2SizeStr(hourofday)+":"+padding2SizeStr(mintus)+":"+
                padding2SizeStr(second)+"."+padding3SizeStr(millsecond) +
                " 已过秒数="+ todaySecondsUse+"s" +
                        " 已过分钟数="+ todayMinutes+"."+padding2SizeStr(todayMinutes_SecondPercent)+"m  " +
                        todayMinutes+"分"+second_minutes+"秒" +" 剩余秒数:"+daySecondWehave+"秒"
                        + "  剩余分钟数: "+ dayMintusWehaveStr+" 分 " +
                " 今日已使用占比:" + todaySecondsUsePercentStr + "%" + "  未使用占比:"+monthWeHavePersentPercentStr+ "%");

        */
        // 显示今天的日期

    }


    public static void printWeeks() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Calendar calendar = Calendar.getInstance();

        int CurMonth_1  = calendar.get(Calendar.MONTH) + 1;

//        calendar.set(calendar.DAY_OF_WEEK, calendar.MONDAY);
        calendar.set(Calendar.DATE, 1);
        int month = calendar.get(Calendar.MONTH);
        int count = 0;
        while (calendar.get(Calendar.MONTH) == month) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                StringBuilder builder = new StringBuilder();
                builder.append(padding2SizeStr(CurMonth_1)+"月");
                builder.append("week");
                builder.append(++count);
                builder.append("   :   ║ ");
//                builder.append(" (");
                builder.append(format.format(calendar.getTime()));
                builder.append(" - ");
                calendar.add(Calendar.DATE, 6);
                builder.append(format.format(calendar.getTime()));
                builder.append(" ║");
                System.out.println(builder.toString());
            }
            calendar.add(Calendar.DATE, 1);
        }
    }


    static String calculXinqiYi1(){
        Calendar xinqi1 = Calendar.getInstance();  //  现在的日期
        xinqi1.set(xinqi1.DAY_OF_WEEK, xinqi1.MONDAY);
        int year =  xinqi1.get(Calendar.YEAR);
        int month =  xinqi1.get(Calendar.MONTH) + 1;
       int day =  xinqi1.get(Calendar.DAY_OF_MONTH);

        String dateStr = year+"-"+padding2SizeStr(month)+"-"+padding2SizeStr(day);
//        System.out.println("周一:" + dateStr);
        return dateStr;
    }

    static String calculXinqiYi7(){
        Calendar xinqi7 = Calendar.getInstance();  //  现在的日期
        xinqi7.set(xinqi7.DAY_OF_WEEK, xinqi7.MONDAY);
        xinqi7.set(Calendar.DATE, xinqi7.get(xinqi7.DATE) + 6);

        int year =  xinqi7.get(Calendar.YEAR);
        int month =  xinqi7.get(Calendar.MONTH) + 1;
        int day =  xinqi7.get(Calendar.DAY_OF_MONTH);
        String dateStr = year+"-"+padding2SizeStr(month)+"-"+padding2SizeStr(day);
//        System.out.println("周天:" + dateStr);
        return dateStr;
    }


    static String   calculXinQi2Chinese(int xiniq){
        String xinqiValue = "";
        switch(xiniq){
            case 1:
                xinqiValue = "一";
                break;
            case 2:
                xinqiValue = "二";
                break;
            case 3:
                xinqiValue = "三";
                break;
            case 4:
                xinqiValue = "四";
                break;
            case 5:
                xinqiValue = "五";
                break;
            case 6:
                xinqiValue = "六";
                break;
            case 7:
                xinqiValue = "天";
                break;
            default:
                xinqiValue = "一";  // 默认周一
        }

        return xinqiValue;

    }

  static String   calculXinQi(int xiniq){
        String xinqiValue = "";
//      System.out.println(" xiniq = "+ xiniq);
        switch(xiniq){
            case 1:
                xinqiValue = "星期一";
                break;
            case 2:
                xinqiValue = "星期二";
                break;
            case 3:
                xinqiValue = "星期三";
                break;
            case 4:
                xinqiValue = "星期四";
                break;
            case 5:
                xinqiValue = "星期五";
                break;
            case 6:
                xinqiValue = "星期六";
                break;
            case 7:
                xinqiValue = "星期天";
                break;
            default:
                xinqiValue = "星期一";  // 默认周一
        }

        return xinqiValue;
    }

    static int calculInputTypeIndex(String inputParams){
        if(inputParams == null){
            return 0;
        }
        if(isNumeric(inputParams)){
            return Integer.parseInt(inputParams);
        }
        if(inputParams.contains("_")){
            String[] mTypeParamArr = inputParams.split("_");
            if(mTypeParamArr.length == 0 ){
                return 0;
            }

            for (int i = 0; i < mTypeParamArr.length; i++) {
                String curPositionStr = mTypeParamArr[i];
                if(isNumeric(curPositionStr)){
                    return  Integer.parseInt(curPositionStr);
                }
            }
        }

        return 0;

    }



    static  String padding3SizeStr(int value){
        String valueStr =  (value+"").trim();

        if(valueStr.length() == 1){

            valueStr = "00"+  valueStr;
        }else if(valueStr.length() == 2){

            valueStr = "0"+  valueStr;
        }

        return valueStr;

    }

    static  String padding20BlankStr(String value){
        String valueStr =  (value+"").trim();
        return getPaddingIntString(valueStr,18," ",false) ;
    }



  static  String padding2SizeStr(int value){
        String valueStr =  (value+"").trim();

        if(valueStr.length() == 1){

            valueStr = "0"+  valueStr;
        }

        return valueStr;

    }


    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                }  else {
                    CUR_INPUT_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new  File(CUR_Dir_1_PATH);




        // 用户没有输入参数
        if ( CUR_INPUT_ParamStrList.size() == 0) {
            System.out.println("用户没有输入参数  提示如下:");
            showNoTypeTip();
            return;
        }




        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH+"  不存在! ");
            return;
        }


        // 开始执行逻辑
        //1.  对参数进行过滤判断
        //2.  拿到参数再具体进行逻辑操作

        TimeOperation timeOperation = new H1_TimeTool.TimeOperation();

        if(!timeOperation.checkInputParamListAndGetType(CUR_INPUT_ParamStrList) || timeOperation.inputType == 0){
            System.out.println("用户输入的 参数无法正确解析 请重新输入\n");
            showNoTypeTip();
              return;
        }

        if(timeOperation.initParamWithType(timeOperation.inputType ,CUR_INPUT_ParamStrList)){
            System.out.println("用户输入的 参数无法正确解析 请重新输入\n");
            showNoTypeTip();
            return;

        }

        setProperity();
    }

    //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
// 输入格式1. 加减
//    ztime_H1.bat  18:30:17.560+5938s    //1.没有年月日 默认今天   往后5938秒后的时间
//  2020-04-26_18:30:17.560-5938s           // 往前 5938秒的时间
//  2020-04-26 18:30:17.560+3.5H
//  2020-04-26 18:30:17.560-4.8H      往前往后  小时的时间

//  2020-04-26 18:30:17.560+3.5D
//  2020-04-26 18:30:17.560-4.8D      往前往后  天数的时间

    //  2020-04-26 18:30:17.560+3.5W
//  2020-04-26 18:30:17.560-4.8W      往前往后  周数的时间

    //  2020-04-26 18:30:17.560+3.5M
//  2020-04-26 18:30:17.560-4.8M      往前往后  月数的时间

    //  2020-04-26 18:30:17.560+3.5Y
//  2020-04-26 18:30:17.560-4.8Y      往前往后  年数的时间

    // ztime_H1.bat  2020-04-26 18:30:17.560+1Y1M1D1h1m1s  之后的1年1月1日1小时1分1秒之后的时间
    // ztime_H1.bat  2020-04-26 18:30:17.560-1Y1M1D1h1m1s  之前的1年1月1日1小时1分1秒之后的时间

// 输入格式2.  两个时间戳
    //   两个时间点相差 距离
// ztime_H1.bat 18:30:17.560   18:22:03.465
// ztime_H1.bat 相差的 1.年数  2.月数   3.天数  4.小时数  5.分钟数   6.秒数

    // ztime_H1.bat  2020-04-26_18:30:17.560   2020-04-22_18:22:03.465     两个时间点 都是完整

    // ztime_H1.bat 18:30:17.560   2020-04-22_18:22:03.465     1.时间点不完整默认 今年今月今日今时


    // 输入格式3  只输入一个时间戳
// ztime_H1.bat  18:30:17.560     转为今日的 分钟数   秒数  毫秒数


class ZTimeStamp{  // 日历    不可能出现   61秒啊  10000毫秒   61分  33天  25小时的情况
        long  year;
        long month;
        long day;
        long hour;
        long mintus;
        long second;
        long microSecond;

    Calendar targetCalendar ;

        ZTimeStamp(){
            year    = 0 ;
            month   = 0 ;
            day     = 0 ;
            hour    = 0 ;
            mintus  = 0 ;
            second  = 0 ;
            microSecond = 0 ;
            targetCalendar = Calendar.getInstance();
        }


    @Override
    public String toString() {
        return "ZTimeStamp{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", mintus=" + mintus +
                ", second=" + second +
                ", microSecond=" + microSecond +
                ", targetCalendar=" + targetCalendar +
                '}';
    }
}

  //  ztime_H1.bat  18:30:17.560+5938s

    // 2020-11-21_18:30:16.142
    static ZTimeStamp  getZtimeStamp(String timeStr){


        ZTimeStamp ztimeStamp = new H1_TimeTool().new ZTimeStamp();

        Calendar calendar = Calendar.getInstance();
     long   year1 =     calendar.get(Calendar.YEAR);
     long   month1 =     calendar.get(Calendar.MONTH) +1 ;
     long   day1 =  calendar.get(Calendar.DATE);
     long   hour1 =  calendar.get(Calendar.HOUR_OF_DAY);
     long   minutes1 =  calendar.get(Calendar.MINUTE);
     long   seconds1 =  calendar.get(Calendar.SECOND);
     long   microSeconds1 =  calendar.get(Calendar.MILLISECOND);

        String dateStr = null;
        String hourStr = null;

if(timeStr.contains("_")){
    dateStr = timeStr.substring(0,timeStr.lastIndexOf("_"));
    hourStr = timeStr.substring(timeStr.lastIndexOf("_")+1);
}else {
    if(timeStr.contains("-")){
        dateStr = timeStr;
    }
    if(timeStr.contains(":")){
        hourStr = timeStr;
    }

}

        System.out.println("getZtimeStamp  -> dateStr = " + dateStr );
        System.out.println("getZtimeStamp  -> hourStr = " + hourStr );
if(dateStr == null ){
    ztimeStamp.year = year1;
    ztimeStamp.month = month1;
    ztimeStamp.day = day1;
}else{
    if(dateStr.contains("-")){
       String[]  dateStrArr  = dateStr.split("-");

       // 2020-11-21_18:30:16.142        11-12_18:30:16.142    12_18:30:16.142
        if(dateStrArr.length == 1){
            ztimeStamp.year = year1;
            ztimeStamp.month  = month1;
            String dayStr = dateStrArr[0];  //   12_18:30:16.142
            ztimeStamp.day  = Long.parseLong(dayStr);
        }

       if(dateStrArr.length == 2){   // 11-12_18:30:16.142
           ztimeStamp.year = year1;
           String monthStr = dateStrArr[0];
           String dayStr = dateStrArr[1];
           ztimeStamp.month  = Long.parseLong(monthStr);
           ztimeStamp.day  = Long.parseLong(dayStr);
       }

        if(dateStrArr.length == 3){
            String yearStr = dateStrArr[0];
            String monthStr = dateStrArr[1];
            String dayStr = dateStrArr[2];
            ztimeStamp.year =  Long.parseLong(yearStr);;
            ztimeStamp.month  = Long.parseLong(monthStr);
            ztimeStamp.day  = Long.parseLong(dayStr);
        }
    }else{   //  不包含-  只包含一个数值的话   那么就是   这个月的几号了
        ztimeStamp.year = year1;
        ztimeStamp.month  = month1;
        ztimeStamp.day  = Long.parseLong(dateStr);
    }
    // 2020-11-21


}


    if(hourStr ==null){  // 时分秒
        ztimeStamp.hour = hour1;
        ztimeStamp.mintus = minutes1;
        ztimeStamp.second = seconds1;
    }else{

        String timeValue = hourStr.toString();
//        18:30:16.142
         if(timeValue.contains(".")){  // 如果包含 . 点  毫秒
             String miscSecondStr = timeValue.substring(timeValue.lastIndexOf(".")+1);
             ztimeStamp.microSecond = Long.parseLong(miscSecondStr);
             timeValue = hourStr.substring(0,timeValue.lastIndexOf("."));
         }

        //        18:30:16

        String[] hourArr = timeValue.split(":");


         // 时   2020-11-21_18
         if(hourArr.length == 1){
             ztimeStamp.hour = Long.parseLong(hourArr[0]);
             ztimeStamp.mintus = 0;
             ztimeStamp.second = 0;

         }

         //时分   2020-11-21_18:30
        if(hourArr.length == 2){
            ztimeStamp.hour = Long.parseLong(hourArr[0]);
            ztimeStamp.mintus =  Long.parseLong(hourArr[1]);
            ztimeStamp.second = 0;
        }

        if(hourArr.length == 3){
            ztimeStamp.hour = Long.parseLong(hourArr[0]);
            ztimeStamp.mintus =  Long.parseLong(hourArr[1]);
            ztimeStamp.second = Long.parseLong(hourArr[2]);

        }

    }

     // 设置对应的 calendoar 日历
    ztimeStamp.targetCalendar.set((int)ztimeStamp.year,(int)ztimeStamp.month,(int)ztimeStamp.day);
    ztimeStamp.targetCalendar.set((int)ztimeStamp.year,(int)ztimeStamp.month,(int)ztimeStamp.day,(int)ztimeStamp.hour,(int)ztimeStamp.mintus,(int)ztimeStamp.second);
    ztimeStamp.targetCalendar.set(Calendar.MILLISECOND,(int)ztimeStamp.microSecond);

        return ztimeStamp;
    }

    // 11y13M33D65m88s
    static ZTimeLengthStamp  getZtimeLength(String timeStr,boolean directFlag){

        ZTimeLengthStamp ztimeLength = new H1_TimeTool().new ZTimeLengthStamp();

        // 一个一个遍历
        ztimeLength.directup = directFlag;
        char[] charArr = timeStr.toCharArray();
        String numValueStr = "";
        for (int i = 0; i < charArr.length; i++) {
            String charValue = charArr[i]+"";
            if(isNumeric(charValue)){  //
                numValueStr += charValue;
                continue;
            }else{
                //  如果遇到 y 说明是 年的信息
                if("Y".equals(charValue) || "y".equals(charValue) ){
                    ztimeLength.year_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }
                if("M".equals(charValue) ){
                    ztimeLength.month_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }

                if("D".equals(charValue) || "d".equals(charValue) ){
                    ztimeLength.day_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }

                if("H".equals(charValue) || "h".equals(charValue) ){
                    ztimeLength.hour_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }

                if("W".equals(charValue) || "w".equals(charValue) ){
                    ztimeLength.week_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }


                if("m".equals(charValue) ){
                    ztimeLength.mintus_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }

                if("S".equals(charValue) || "s".equals(charValue) ){
                    ztimeLength.second_length = Long.parseLong(numValueStr);
                    numValueStr = "";
                    continue;
                }



            }
        }


        return ztimeLength;
    }

    class Year_Month{
        int year ;  // 年
        int month ;  // 月
        Year_Month(int curYear , int curMonth){
            if(curMonth == 0){
                year = curYear - 1;
                month =  12;
            }else if(curMonth < 0){
                year = curYear - 1;
                month = curMonth + 12;
            } else{
                year = curYear + (curMonth/12);
                month = (curMonth%12)== 0 ? 12 :(curMonth%12);

            }

        }
    }

    static int calculYearDays(int curYear , int YearCount , boolean directUp){
        int yearDaysSum = 0 ;
        if(YearCount == 0 ){
            return yearDaysSum;
        }

        for (int i = 1; i < YearCount+1; i++) {
            int curYearX = directUp?curYear+i:curYear-i;
            int yearDay = calculRunYear(curYearX);
            yearDaysSum += yearDay;
            System.out.println(curYearX+"年 有 "+ yearDay +" 天 ！ ");
        }
        return yearDaysSum;
    }

  static int  calculRunYear(int year){
        int yearDay = 365;
       if( year%4==0&&year%100!=0||year%400==0){
          yearDay = 366;
       }

        return yearDay;
  }


static int calculMonthday(int year , int month , int count , boolean direct){
        int monthDay = 0;


    System.out.println(" 需额外计算月数为: "+  count);
    for (int i = 1; i < count +1 ; i++) {
        int curMonth  =  direct? month+i:month-i; //  增加   减少
        Year_Month curYearMonth = new H1_TimeTool().new Year_Month(year , curMonth);

        int curDayCount = getDayForMonth_Year(curYearMonth.year,curYearMonth.month);
        System.out.println(" 年 :"+ curYearMonth.year + "  月 :"+ curYearMonth.month +"  天数:"+ curDayCount);
        monthDay += curDayCount;
    }



    System.out.println(" 需额外计算月数对应天数为: "+  monthDay);
        return  monthDay;

}

static long MicSecond_2_Second = 1000; //  1000  毫秒转为 1秒
static long Second_2_Mintus = 60; //  60  秒转为 分钟
static long Mintus_2_Hour = 60;  //  60 分钟 转为 1小时
static long Hour_2_Day = 24;    // 24 小时转为1 天
static long Day_2_Week = 7;    //7天时一周
static long Day_2_Year_365 = 365;    //365天是一年
    static long Day_2_Year_366 = 366;    //366天是一年
// 一个月 有 几天 是 不确定的


    class ZTimeLengthStamp{  // 日历    可以出现   61秒啊  10000毫秒   61分  33天  25小时的情况
        long   year_length;
        long  month_length;
        long    day_length;
        long   hour_length;
        long mintus_length;
        long second_length;

        long   week_length;

        long microSecond_length;


        boolean directup;  // true ---就是增加      false--- 就是减少

        long sumMicroSecond;  //  总的毫秒数

        long calculAllMillSecond(ZTimeStamp zStamp){
            long mAllSecond  =  0;

            mAllSecond = mAllSecond + second_length * 1000;
            mAllSecond = mAllSecond + mintus_length * Second_2_Mintus * 1000;
            mAllSecond = mAllSecond + hour_length * Mintus_2_Hour * Second_2_Mintus * 1000;
            mAllSecond = mAllSecond + day_length  * Hour_2_Day * Mintus_2_Hour * Second_2_Mintus * 1000 ;
            mAllSecond = mAllSecond + week_length * Day_2_Week  * Hour_2_Day * Mintus_2_Hour * Second_2_Mintus * 1000 ;


            Calendar curentDate = Calendar.getInstance();

            int year2Month =  (int)(month_length/12);  //  把当前的总的月份转为以年为单位

            int monthCount =  (int)(month_length%12);  // 当前需要计算的月份的个数
            int curYear = curentDate.get(Calendar.YEAR);
            int curMonth =  curentDate.get(Calendar.MONTH) + 1;
            boolean direct = directup;

            if(month_length != 0)
            System.out.println(" month_length = "+ month_length +" 转为"+ year2Month +"年 "+ monthCount+" 个月");

            // 月份计算出的总的天数
          int monthDays =   calculMonthday(curYear,curMonth,monthCount,direct);

          if(monthDays > 0){

              // 月份计算
              mAllSecond = mAllSecond + monthDays  * Hour_2_Day * Mintus_2_Hour * Second_2_Mintus * 1000;

          }

            long curAllYear = year_length + year2Month;
            long AllYearDays = calculYearDays(curentDate.get(Calendar.YEAR),(int)curAllYear,directup);

            // 年份计算
            mAllSecond = mAllSecond + AllYearDays  * Hour_2_Day * Mintus_2_Hour * Second_2_Mintus * 1000;







            return  mAllSecond;



        }



       long getSumSecond(){
            return sumMicroSecond/1000;
        }


        ZTimeLengthStamp(){
                   year_length    = 0 ;
                  month_length   = 0 ;
                    day_length     = 0 ;
                   hour_length    = 0 ;
                 mintus_length  = 0 ;
                 second_length  = 0 ;
            microSecond_length = 0 ;
            directup = true;

        }

        @Override
        public String toString() {
            return "ZTimeLengthStamp{" +
                    "year_length=" + year_length +
                    ", month_length=" + month_length +
                    ", day_length=" + day_length +
                    ", hour_length=" + hour_length +
                    ", mintus_length=" + mintus_length +
                    ", second_length=" + second_length +
                    ", microSecond_length=" + microSecond_length +
                    ", sumMicroSecond=" + sumMicroSecond +
                    '}';
        }
    }


  static  int getDayForMonth_Year(int year , int month){
    /* if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
     System.out.println(year+"年"+month+"月"+"这个月是31天");
 }else if (month==4||month==6||month==9||month==11){
     System.out.println(year+"年"+month+"月"+"这个月是30天");
 }else if (month==2){
         if( year%4==0&&year%100!=0||year%400==0){
             System.out.println(year+"年"+month+"月"+"这个月是29天");
         }else {
             System.out.println(year+"年"+month+"月"+"这个月是28天");
         }
 }else {
     System.out.println("请输入正确的月份");
 }*/
    //优化方法days天数



    int days = 0;
    if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
        days = 31;
    }else if (month==4||month==6||month==9||month==11){
        days = 30;
    }else if (month==2){
        if( year%4==0&&year%100!=0||year%400==0){
            days = 29;
        }else {
            days = 28;
        }
    }else {
//        System.out.println("请输入正确的月份");
    }
//    System.out.println(year+"年"+month+"月"+"这个月有"+days+"天");
    return days;
}

    // 输入格式4  只输入一个时间的长度
// ztime_H1.bat  8191s     转为  xx年xx月xx日xx小时xx分钟xx秒

    // 输入格式5  只输入一个年月日长度
    //  ztime_H1.bat 11h23m41S    转为今日的 分钟数   秒数  毫秒数

    //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
  static  class TimeOperation{
// 输入格式1. 加减  ztime_H1.bat  18:30:17.560+5938s   2020-04-26_18:30:17.560-5938s  //1.没有年月日 默认今天   往后5938秒后的时间
// 输入格式2.  两个时间戳 //   两个时间点相差 距离 // ztime_H1.bat 18:30:17.560   18:22:03.465
// 输入格式3. 只输入一个时间戳 ztime_H1.bat  18:30:17.560     转为今日的 分钟数   秒数  毫秒数   另一个默认和现在时间比较
// 输入格式4  只输入一个时间的长度        ztime_H1.bat  100H8191s     转为  xx年xx月xx日xx小时xx分钟xx秒
// 输入格式5(同 格式3) 忽略 只输入一个年月日长度  ztime_H1.bat    11h23m41S    转为今日的 分钟数   秒数  毫秒数
//  ztime_H1.bat    11h23m41S  ===  ztime_H1.bat  11:23:41
        //  怎么区分是一个 时间长度 100H8191s    还是一个 一天内的时间点
        int inputType = 0;


        long year1;
        long month1;
        long day1;
        long hour1;
        long minutes1;  // 分钟
        long seconds1;  // 秒
        long microSeconds1;  // 毫秒


         long year2;
         long month2;
         long day2;
         long hour2;
         long minutes2;  // 分钟
         long seconds2;  // 秒
         long microSeconds2;  // 毫秒
        //     //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
        boolean checkType1(String param){
            boolean flag = false;

            // 以  时间 参数为结尾
         boolean  endflag =    param.endsWith("Y") || param.endsWith("y") ||
                    param.endsWith("M") || param.endsWith("m") ||
                    param.endsWith("D") || param.endsWith("d") ||
                    param.endsWith("H") || param.endsWith("h") ||
                 param.endsWith("W") || param.endsWith("w") ||
                    param.endsWith("S") || param.endsWith("s");

// 输入格式1. 加减  ztime_H1.bat  18:30:17.560+5938s   2020-04-26_18:30:17.560-5938s  //1.没有年月日 默认今天   往后5938秒后的时间

            //  最后的 + - 索引 大于 : 好的索引
         boolean addfalg =    param.contains(":") &&   param.lastIndexOf("+") >  param.lastIndexOf(":");
         boolean downfalg =    param.contains(":") &&   param.lastIndexOf("-") >  param.lastIndexOf(":");
         boolean dotfalg = addfalg || downfalg;
         //  必须包含  时间结尾   必须包含 +- 号
            return endflag && dotfalg;
        }


        //     //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
        // 输入格式3. 只输入一个时间戳 ztime_H1.bat  18:30:17.560     转为今日的 分钟数   秒数  毫秒数   另一个默认和现在时间比较
        boolean checkType3(String param){
            boolean flag = false;

            // 以  时间 参数为结尾
            boolean  endflag =    param.endsWith("Y") || param.endsWith("y") ||
                    param.endsWith("M") || param.endsWith("m") ||
                    param.endsWith("D") || param.endsWith("d") ||
                    param.endsWith("H") || param.endsWith("h") ||
                    param.endsWith("W") || param.endsWith("w") ||
                    param.endsWith("S") || param.endsWith("s");

            //  去除  -_.: 四种符号后 剩下的是数字组成的字符串
            String onlyNum =  clear4dot(param);
            boolean isOnlyNum = isNumeric(onlyNum);  //

            //  1.不能以 字母为结尾    2.去除  -_.: 四种符号后 剩下的是数字组成的字符串
            flag = !endflag && isOnlyNum;
            //  必须包含  时间结尾   必须包含 +- 号
            return flag;
        }

        //     //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
        // 输入格式5  只输入一个带有正负号 开头的时间长度     ztime_H1.bat  -8191s     转为  xx年xx月xx日xx小时xx分钟xx秒
        // ztime_H1.bat  -1y5820m8191s
        boolean checkType5(String param){
            boolean flag = false;

            // 1. 以  时间 参数为结尾
            boolean  endflag =    param.endsWith("Y") || param.endsWith("y") ||
                    param.endsWith("M") || param.endsWith("m") ||
                    param.endsWith("D") || param.endsWith("d") ||
                    param.endsWith("H") || param.endsWith("h") ||
                    param.endsWith("W") || param.endsWith("w") ||
                    param.endsWith("S") || param.endsWith("s");

            boolean isMathBegin = param.trim().startsWith("+") || param.trim().startsWith("-");
            //2. 去除时间参数后 剩下的 是一个数数字字符串
            String numStr =  clearTimeChar(param);
            numStr = numStr.replace("+","");
            numStr = numStr.replace("-","");
            boolean isOnlyNum = isNumeric(numStr);  //

            //  1.以 字母为结尾    2.去除时间参数后  +- 剩下的 是一个数数字字符串  3. 以+- 开头
            flag = endflag && isOnlyNum && isMathBegin ;

            return flag;
        }


        //     //Y-年   M-月  W-星期  D-天  H-小时 m-分钟 s-秒
        // 输入格式4  只输入一个时间的长度        ztime_H1.bat  8191s     转为  xx年xx月xx日xx小时xx分钟xx秒
        boolean checkType4(String param){
            boolean flag = false;

            // 1. 以  时间 参数为结尾
            boolean  endflag =    param.endsWith("Y") || param.endsWith("y") ||
                    param.endsWith("M") || param.endsWith("m") ||
                    param.endsWith("D") || param.endsWith("d") ||
                    param.endsWith("H") || param.endsWith("h") ||
                    param.endsWith("W") || param.endsWith("w") ||
                    param.endsWith("S") || param.endsWith("s");

         //2. 去除时间参数后 剩下的 是一个数数字字符串
            String numStr =  clearTimeChar(param);
            boolean isOnlyNum = isNumeric(numStr);  //

            //  1.以 字母为结尾    2.去除时间参数后 剩下的 是一个数数字字符串
            flag = endflag && isOnlyNum ;

            return flag;
        }

        String clear4dot(String params){
            return params.replace(".","")
                    .replace("_","")
                    .replace("-","")
                    .replace(":","");
        }


        String clearTimeChar(String params){
            return params.replace("Y","")
                    .replace("y","")
                    .replace("M","")
                    .replace("m","")
                    .replace("D","")
                    .replace("d","")
                    .replace("H","")
                    .replace("h","")
                    .replace("W","")
                    .replace("w","")
                    .replace("S","")
                    .replace("s","");
        }


// 输入格式1. 加减  ztime_H1.bat  18:30:17.560+5938s   2020-04-26_18:30:17.560-5938s  //1.没有年月日 默认今天   往后5938秒后的时间
// 输入格式2.  两个时间戳 //   两个时间点相差 距离 // ztime_H1.bat 18:30:17.560   18:22:03.465
// 输入格式3. 只输入一个时间戳 ztime_H1.bat  18:30:17.560     转为今日的 分钟数   秒数  毫秒数   另一个默认和现在时间比较
// 输入格式4  只输入一个时间的长度        ztime_H1.bat  100H8191s     转为  xx年xx月xx日xx小时xx分钟xx秒


        boolean  initParamWithType(int inputType , ArrayList<String> inputParamList){
            boolean initFlag = false;
            switch (inputType){
                case 1:
                    String paramsStr = inputParamList.get(0);
                    boolean directUp = paramsStr.contains("+");  // true 为 加+   false为 减
                    int diffIndex = 0;
                    if(directUp){
                        diffIndex =  paramsStr.lastIndexOf("+");
                    }else{
                        diffIndex =  paramsStr.lastIndexOf("-");
                    }

                    String timeStamp1 = paramsStr.substring(0,diffIndex);
                    String timeLengthStr =  paramsStr.substring(diffIndex+1);

                    // 从 timeStamp1 中获取得到  年月日 时分秒 毫秒的数据
                    ZTimeStamp  zTimeStamp = getZtimeStamp(timeStamp1);
                    // 从 timeLengthStr 得到 毫秒的数据  全部转为 毫秒
                    ZTimeLengthStamp  zTimeLength = getZtimeLength(timeLengthStr,directUp);
                    long secondDistance = zTimeLength.calculAllMillSecond(zTimeStamp);

                    long calculDate = directUp?zTimeStamp.targetCalendar.getTimeInMillis() + secondDistance:zTimeStamp.targetCalendar.getTimeInMillis() - secondDistance;
                 Calendar calculCalendar = Calendar.getInstance();
                 calculCalendar.setTimeInMillis(calculDate);

                    System.out.println(zTimeStamp);
                    System.out.println(zTimeLength);
                    System.out.println(" 时间长度 毫秒数:"+secondDistance +" 秒数:"+(secondDistance/1000));

                    System.out.println("\n 计算出的时间点: \n"+calculCalendar.get(Calendar.YEAR)+"年"+
                            (calculCalendar.get(Calendar.MONTH) ==0 ? 12:calculCalendar.get(Calendar.MONTH))+"月"+
                            calculCalendar.get(Calendar.DAY_OF_MONTH)+"日"+
                            calculCalendar.get(Calendar.HOUR_OF_DAY)+"时"+
                                    calculCalendar.get(Calendar.MINUTE)+"分"+
                                    calculCalendar.get(Calendar.SECOND)+"秒" );

                    System.out.println("\n"
                            +calculCalendar.get(Calendar.YEAR)+"-"+
                            padding2SizeStr((calculCalendar.get(Calendar.MONTH) ==0 ? 12:calculCalendar.get(Calendar.MONTH)))+"-"+
                            padding2SizeStr(calculCalendar.get(Calendar.DAY_OF_MONTH))+"_"+
                            padding2SizeStr(calculCalendar.get(Calendar.HOUR_OF_DAY))+":"+
                            padding2SizeStr(calculCalendar.get(Calendar.MINUTE))+":"+
                            padding2SizeStr(calculCalendar.get(Calendar.SECOND))+"."+
                            padding3SizeStr(calculCalendar.get(Calendar.MILLISECOND)) );
                    break;


                case 2:
                    String time1 = inputParamList.get(0);   // 第一个时间戳
                    String time2 = inputParamList.get(1);   // 第二个时间戳

                    ZTimeStamp  zTimeStampA = getZtimeStamp(time1);
                    ZTimeStamp  zTimeStampB = getZtimeStamp(time2);
                    long millionSecondsA =  zTimeStampA.targetCalendar.getTimeInMillis();
                    long millionSecondsB =  zTimeStampB.targetCalendar.getTimeInMillis();

                    long distanceValueMillion = Math.abs(millionSecondsA - millionSecondsB);
                    long distanceSecond = distanceValueMillion/1000;
                    long distanceMintus = distanceValueMillion/(1000 * 60);
                    long distanceHour =  distanceValueMillion/(1000 * 60 * 60);
                    long distanceDay =  distanceValueMillion/(1000 * 60 * 60 * 24);
                    long distanceWeek =  distanceValueMillion/(1000 * 60 * 60 * 24 * 7);
                    long distanceMonth =  distanceValueMillion/(1000L * 60L * 60 * 24 * 30);
                    long distanceYear =  distanceValueMillion/(1000L * 60 * 60 * 24 * 365);

                    System.out.println("\n时间点A -> " + time1 );
                    System.out.println("时间点B -> " + time2);
                    System.out.println("\n相差时间距离:\n");
                    System.out.println("毫秒      : " + distanceValueMillion+"ms");
                    System.out.println("秒        : " + (distanceValueMillion/1000)+"s");
                    System.out.println("分钟      : "+(distanceValueMillion/(1000 * 60) +"分钟"+ (distanceSecond%60)+"秒" ));
                    System.out.println("小时      : "+(distanceValueMillion/(1000 * 60 * 60) +"小时" + (distanceMintus%60)+"分钟" ) + (distanceSecond%60)+"秒" );
                    System.out.println("天        : "+distanceDay +"天" + (distanceHour%24)+"小时" +(distanceMintus%60)+"分钟" + (distanceSecond%60)+"秒" );
                    System.out.println("周        : "+distanceWeek+"周"+(distanceDay%7) +"天" + (distanceHour%24)+"小时" +(distanceMintus%60)+"分钟" + (distanceSecond%60)+"秒" );
                    System.out.println("月(30天)  : "+distanceMonth+"月"+(distanceDay%30) +"天" + (distanceHour%24)+"小时" +(distanceMintus%60)+"分钟" + (distanceSecond%60)+"秒" );
                    System.out.println("年(365天) : "+distanceYear+"年"+(distanceMonth%12)+"月"+(distanceDay%365) +"天" + (distanceHour%24)+"小时" +(distanceMintus%60)+"分钟" + (distanceSecond%60)+"秒" );


                    break;

                case 3:
                    // 输入格式3. 只输入一个时间戳 ztime_H1.bat  18:30:17.560     转为今日的 分钟数   秒数  毫秒数   另一个默认和现在时间比较
                    String timeStampValue = inputParamList.get(0);   // 第一个时间戳
                    ZTimeStamp  zTimeStampForType3 = getZtimeStamp(timeStampValue);
                    Calendar todayCalendar = Calendar.getInstance();
                    int xMonth = todayCalendar.get(Calendar.MONTH) + 1;
                    int xDay = todayCalendar.get(Calendar.DAY_OF_MONTH);
                    todayCalendar.set(Calendar.DAY_OF_MONTH,xDay);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.MONDAY,xMonth);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.HOUR,0);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.MINUTE,0);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.SECOND,0);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.MILLISECOND,0);  //  时分秒 毫秒 都设置0
                    todayCalendar.set(Calendar.HOUR_OF_DAY,0);



                    long todayMillSecond = todayCalendar.getTimeInMillis();
                    long millSecondForType3 = zTimeStampForType3.targetCalendar.getTimeInMillis();

                    long CdistanceValueMillion = Math.abs(millSecondForType3 - todayMillSecond);
                    long CdistanceSecond = CdistanceValueMillion/1000;
                    long CdistanceMintus = CdistanceValueMillion/(1000 * 60);
                    long CdistanceHour =  CdistanceValueMillion/(1000 * 60 * 60);
                    long CdistanceDay =  CdistanceValueMillion/(1000 * 60 * 60 * 24);


                    System.out.println("\n时间点A           -> " + showCalendar(zTimeStampForType3.targetCalendar) );
                    System.out.println("时间点B(今日凌晨) -> " + showCalendar(todayCalendar));
                    System.out.println("\n该时间点今日时长:\n");
                    System.out.println("毫秒      : " + CdistanceValueMillion+"ms");
                    System.out.println("秒        : " + (CdistanceValueMillion/1000)+"s");
                    System.out.println("分钟      : "+(CdistanceValueMillion/(1000 * 60) +"分钟"+ (CdistanceSecond%60)+"秒" ));
                    System.out.println("小时      : "+(CdistanceValueMillion/(1000 * 60 * 60) +"小时" + (CdistanceMintus%60)+"分钟" ) + (CdistanceSecond%60)+"秒" );



                    break;


                case 4:
                    // 输入格式4  只输入一个时间的长度        ztime_H1.bat  100H8191s     转为  xx年xx月xx日xx小时xx分钟xx秒
                String params4 = inputParamList.get(0);
                    ZTimeLengthStamp  zTimeLengthforType4 = getZtimeLength(params4,true);
                   long millSecondForType4 =  zTimeLengthforType4.calculAllMillSecond(null);

                    long N4_distanceSecond = millSecondForType4/1000;
                    long N4_distanceMintus = millSecondForType4/(1000 * 60);
                    long N4_distanceHour =  millSecondForType4/(1000 * 60 * 60);
                    long N4_distanceDay =  millSecondForType4/(1000 * 60 * 60 * 24);
                    long N4_distanceWeek =  millSecondForType4/(1000 * 60 * 60 * 24 * 7);
                    long N4_distanceMonth =  millSecondForType4/(1000L * 60L * 60 * 24 * 30);
                    long N4_distanceYear =  millSecondForType4/(1000L * 60 * 60 * 24 * 365);

//                    System.out.println("\n时间点A -> " + time1 );
//                    System.out.println("时间点B -> " + time2);

                    System.out.println("\n当前输入的时间长度字符串为: "+params4+ " 换算时间长度如下:\n");
                    System.out.println("毫秒      : " + millSecondForType4+"ms");
                    System.out.println("秒        : " + (millSecondForType4/1000)+"s");
                    System.out.println("分钟      : "+(millSecondForType4/(1000 * 60) +"分钟"+ (N4_distanceSecond%60)+"秒" ));
                    System.out.println("小时      : "+(millSecondForType4/(1000 * 60 * 60) +"小时" + (N4_distanceMintus%60)+"分钟" ) + (N4_distanceSecond%60)+"秒" );
                    System.out.println("天        : "+N4_distanceDay +"天" + (N4_distanceHour%24)+"小时" +(N4_distanceMintus%60)+"分钟" + (N4_distanceSecond%60)+"秒" );
                    System.out.println("周        : "+N4_distanceWeek+"周"+(N4_distanceDay%7) +"天" + (N4_distanceHour%24)+"小时" +(N4_distanceMintus%60)+"分钟" + (N4_distanceSecond%60)+"秒" );
                    System.out.println("月(30天)  : "+N4_distanceMonth+"月"+(N4_distanceDay%30) +"天" + (N4_distanceHour%24)+"小时" +(N4_distanceMintus%60)+"分钟" + (N4_distanceSecond%60)+"秒" );
                    System.out.println("年(365天) : "+N4_distanceYear+"年"+(N4_distanceMonth%12)+"月"+(N4_distanceDay%365) +"天" + (N4_distanceHour%24)+"小时" +(N4_distanceMintus%60)+"分钟" + (N4_distanceSecond%60)+"秒" );





                    break;

                case 5:   //  ztime   -4728h3131y
                    initFlag = false;
                    String params5 = inputParamList.get(0);
                    boolean direct  = "+".equals(params5.substring(0,1));
                    params5 = params5.replace("+","");
                    params5 = params5.replace("-","");
                    ZTimeLengthStamp  zTimeLength5 = getZtimeLength(params5,direct);

                    // 从 timeLengthStr 得到 毫秒的数据  全部转为 毫秒
                    long secondDistance5 = zTimeLength5.calculAllMillSecond(null);
                    Calendar now5 = Calendar.getInstance();


                    long calculDate5 = direct?now5.getTimeInMillis() + secondDistance5:now5.getTimeInMillis() - secondDistance5;
                    Calendar mA_CalculCalendar = Calendar.getInstance();
                    mA_CalculCalendar.setTimeInMillis(calculDate5);

                    System.out.println(" 起始时间(now) : "+showCalendar(now5));
                    System.out.println(" 时间长度字符  : "+params5);
                    System.out.println(" 时间长度 毫秒数:"+secondDistance5 +" 秒数:"+(secondDistance5/1000));


                    System.out.println("\n 计算出的时间点: \n"+mA_CalculCalendar.get(Calendar.YEAR)+"年"+
                            (mA_CalculCalendar.get(Calendar.MONTH) ==0 ? 12:mA_CalculCalendar.get(Calendar.MONTH))+"月"+
                            mA_CalculCalendar.get(Calendar.DAY_OF_MONTH)+"日"+
                            mA_CalculCalendar.get(Calendar.HOUR_OF_DAY)+"时"+
                            mA_CalculCalendar.get(Calendar.MINUTE)+"分"+
                            mA_CalculCalendar.get(Calendar.SECOND)+"秒" );

                    System.out.println("\n"
                            +mA_CalculCalendar.get(Calendar.YEAR)+"-"+
                            padding2SizeStr((mA_CalculCalendar.get(Calendar.MONTH) ==0 ? 12:mA_CalculCalendar.get(Calendar.MONTH)))+"-"+
                            padding2SizeStr(mA_CalculCalendar.get(Calendar.DAY_OF_MONTH))+"_"+
                            padding2SizeStr(mA_CalculCalendar.get(Calendar.HOUR_OF_DAY))+":"+
                            padding2SizeStr(mA_CalculCalendar.get(Calendar.MINUTE))+":"+
                            padding2SizeStr(mA_CalculCalendar.get(Calendar.SECOND))+"."+
                            padding3SizeStr(mA_CalculCalendar.get(Calendar.MILLISECOND)) );


                    break;
                    default:
                        initFlag = false;
            }


       return initFlag;



}

String showCalendar(Calendar calendar){
            String desc = "";
    int myear = calendar.get(Calendar.YEAR);
    int mmonth = calendar.get(Calendar.MONTH);
    int mday = calendar.get(Calendar.DAY_OF_MONTH);
    int mhour = calendar.get(Calendar.HOUR_OF_DAY);
    int mmintues = calendar.get(Calendar.MINUTE);
    int msecond = calendar.get(Calendar.SECOND);
    int mMillSecond = calendar.get(Calendar.MILLISECOND);
    desc = myear+"-"+
            padding2SizeStr(mmonth) +"-"+
            padding2SizeStr(mday)+"_"+
            padding2SizeStr(mhour)+":"+
            padding2SizeStr(mmintues)+":"+
            padding2SizeStr(msecond) +"."+
            padding3SizeStr(mMillSecond);
            return desc;


}


        //  false:  当前须发解析参数      true:当前能正确解析参数
    boolean     checkInputParamListAndGetType(ArrayList<String> inputParamList){
        boolean flag = false;
        int paramSize = inputParamList.size();
        if(paramSize == 2){
            inputType = 2;
            flag = true;
        }else if(paramSize == 1){
            String onlyOneParam = inputParamList.get(paramSize -1);

            if(checkType1(onlyOneParam)){
                inputType = 1;
                flag = true;
            }else if(checkType3(onlyOneParam)){
                inputType = 3;
                flag = true;
            }else if(checkType4(onlyOneParam)){
                inputType = 4;
                flag = true;
            }else if(checkType5(onlyOneParam)){
                inputType = 5;
                flag = true;
            }else {    // 其余的情况  输入类型为 0 标识无法识别
                inputType = 0;
                flag = false;
            }

        }else{
            System.out.println("当前输入的参数长度超过2个 ! ");
            inputType = 0;
            flag = false;
        }

        System.out.println("checkInputParamListAndGetType -> inputType = "+ inputType + "   flag = "+ flag);
        return flag;



        }


        // 默认构造器 把 时间都设置为现在的时间点
        TimeOperation(){
            Calendar calendar = Calendar.getInstance();
            year1 =     calendar.get(Calendar.YEAR);
            month1 =     calendar.get(Calendar.MONTH) +1 ;
            day1 =  calendar.get(Calendar.DATE);
            hour1 =  calendar.get(Calendar.HOUR_OF_DAY);
            minutes1 =  calendar.get(Calendar.MINUTE);
            seconds1 =  calendar.get(Calendar.SECOND);
            microSeconds1 =  calendar.get(Calendar.MILLISECOND);

            year2 =     calendar.get(Calendar.YEAR);
           month2 =     calendar.get(Calendar.MONTH) +1 ;
             day2 =  calendar.get(Calendar.DATE);
            hour2 =  calendar.get(Calendar.HOUR_OF_DAY);
         minutes2 =  calendar.get(Calendar.MINUTE);
         seconds2 =  calendar.get(Calendar.SECOND);
         microSeconds2 =  calendar.get(Calendar.MILLISECOND);
        }



    }






    static  void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CUR_Dir_FILETypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CUR_Dir_FILETypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CUR_Dir_FILETypeMap.put(keyType, fileList);
        }
    }

    static void  initFileTypeMap(ArrayList<File> subFileList){
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





}
