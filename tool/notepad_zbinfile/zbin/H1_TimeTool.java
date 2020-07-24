

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
// 4. 给出当前的时间戳

public class H1_TimeTool {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "ztime_H1";


    /*******************修改属性列表 ------End *********************/


    static int ShowCalYearInt = 2020;
    static int Now_YearInt = 2020;
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


    static int MONTH_LENGTH_MAX = 26;   //   调节 每个月份之间的间隔

    static String month_name_1 = "January_1";
    static String month_name_2 = "February_2";
    static String month_name_3 = "March_3";
    static String month_name_4 = "April_4";
    static String month_name_5 = "May_5";
    static String month_name_6 = "June_6";
    static String month_name_7 = "July_7";
    static String month_name_8 = "August_8";
    static String month_name_9 = "September_9";
    static String month_name_10 = "October_10";
    static String month_name_11 = "November_11";
    static String month_name_12 = "December_12";
    static  String[] Month_Name_Arr = {month_name_1 ,month_name_2 ,month_name_3 ,month_name_4 ,month_name_5 ,month_name_6 ,month_name_7 ,month_name_8 ,month_name_9 ,month_name_10 ,month_name_11 ,month_name_12  };
    static String week_name_desc = "Mo Tu We Th Fr Sa Su  ";

    static ArrayList<String> DayDescList = new ArrayList<String>();
    static {
        DayDescList.add(" 1");
        DayDescList.add(" 2");
        DayDescList.add(" 3");
        DayDescList.add(" 4");
        DayDescList.add(" 5");
        DayDescList.add(" 6");
        DayDescList.add(" 7");
        DayDescList.add(" 8");
        DayDescList.add(" 9");
        DayDescList.add("10");
        DayDescList.add("11");
        DayDescList.add("12");
        DayDescList.add("13");
        DayDescList.add("14");
        DayDescList.add("15");
        DayDescList.add("16");
        DayDescList.add("17");
        DayDescList.add("18");
        DayDescList.add("19");
        DayDescList.add("20");
        DayDescList.add("21");
        DayDescList.add("22");
        DayDescList.add("23");
        DayDescList.add("24");
        DayDescList.add("25");
        DayDescList.add("26");
        DayDescList.add("27");
        DayDescList.add("28");
        DayDescList.add("29");
        DayDescList.add("30");
        DayDescList.add("31");
    }


    static ArrayList<String> AnimalYearName = new ArrayList<String>();
    static{
        AnimalYearName.add(" 0申猴");
        AnimalYearName.add(" 1酉鸡");
        AnimalYearName.add(" 2戌狗");
        AnimalYearName.add(" 3亥猪");
        AnimalYearName.add(" 4子鼠");
        AnimalYearName.add(" 5丑牛");
        AnimalYearName.add(" 6寅虎");
        AnimalYearName.add(" 7卯兔");
        AnimalYearName.add(" 8辰龙");
        AnimalYearName.add(" 9巳蛇");
        AnimalYearName.add("10午马");
        AnimalYearName.add("11未羊");
    }


    // 甲、乙、丙、丁、戊、己、庚、辛、壬、癸
    public static final char[] skyBranch = new char[] { '甲','乙','丙','丁','戊','己','庚','辛','壬','癸'};

    // 子、丑、寅、卯、辰、巳、午、未、申、酉、戌、亥
    public static final char[] earthBranch = new char[] { '子','丑','寅','卯','辰','巳','午','未','申','酉','戌','亥' };

    static int YearStrLength =  14 ; // 当前一个年份的最大标识长度\

    static String calChineseYearName(int i) {
        if (i < 4) {
            throw new IllegalArgumentException("The starting year must be greater than 4");
        }
        int realYear = i - 4;

//        System.out.print( skyBranch[realYear % 10] +""+ earthBranch[realYear % 12] );
        return skyBranch[realYear % 10] +""+ earthBranch[realYear % 12];
    }


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



    static void showNoTypeTip(H1_TimeTool timetool) {
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

//        now.set(now.DAY_OF_WEEK, now.MONDAY);


        int year  = now.get(Calendar.YEAR);
        Now_YearInt = year;
        int month  = now.get(Calendar.MONTH) + 1;
        int day  = now.get(Calendar.DAY_OF_MONTH);
        int hour  = now.get(Calendar.HOUR);
        int mintus  = now.get(Calendar.MINUTE);
        int second =  now.get(Calendar.SECOND);
        int millsecond =  now.get(Calendar.MILLISECOND);
        int week =  now.get(Calendar.WEEK_OF_MONTH);
        int xinqi =  now.get(Calendar.DAY_OF_WEEK) - 1 ;  //
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

//        beginTody.set(beginTody.DAY_OF_WEEK, beginTody.MONDAY);

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

//        beginMonthCalendar.set(beginMonthCalendar.DAY_OF_WEEK, beginMonthCalendar.MONDAY);

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


//        System.out.println("一个月的第一个星期从第一个星期一开始!");
        String tip1 = "一个月的第一个星期从第一个星期一开始!";
        String blankStr = "      ";
        System.out.println("══════════════ "+year+"年"+month+"月共4"+"周第"+weekNum1+"周 ═ 7天 ═ 168小时 ═ 10080分钟 ═604800秒 ══════════════" + tip1);
        System.out.println("该日月份周数:   ║ 第 "+((week-1)+"")    +" 周           "+blankStr+"║");
//        System.out.println(calculXinQi2Chinese(month)+"月一日星期:   ║ "+padding20BlankStr(calculXinqiYiForMonthBegin())+"   ║");
        System.out.println("该月一日星期:   ║ "+padding20BlankStr(calculXinqiYiForMonthBegin())+"   ║");

        System.out.println("前个(星期一):   ║ "+padding20BlankStr(calculXinqiYi1())+blankStr+"║");
        System.out.println("后个(星期天):   ║ "+padding20BlankStr(calculXinqiYi7())+blankStr+"║");
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



        System.out.println("══════════════ "+year+"-"+padding2SizeStr(month)+"-"+padding2SizeStr(day)+""+" ═ "+xinqiStr+" ═ 1天 ═ 24小时 ═ 1440分钟 ═ 86400秒 ══════════════");
        System.out.println("今日时刻(t):    ║ 时刻: "+padding20BlankStr(padding2SizeStr(hourofday)+":"+padding2SizeStr(mintus)+":"+ padding2SizeStr(second)+"."+padding3SizeStr(millsecond)) +"║ 剩余: "+padding20BlankStr((dayHourWehaveStr+"h"))+"║");
        System.out.println("秒数级别(s):    ║ 已过: "+padding20BlankStr((todaySecondsUse)+"s")+"║ 剩余: "+padding20BlankStr((daySecondWehave)+"s")+"║");
        System.out.println("分钟级别(m):    ║ 已过: "+padding20BlankStr((todayMinutes)+"m")+"║ 剩余: "+padding20BlankStr((dayMintusWehaveStr)+"m")+"║");
        System.out.println("百分占比(%):    ║ 已过: "+padding20BlankStr((todaySecondsUsePercentStr)+"%")+"║ 剩余: "+padding20BlankStr((monthWeHavePersentPercentStr)+"%")+"║");




        System.out.println();
        System.out.println();
        showAnimalYearInfo();
        System.out.println();


        if(isInputYearParam){  //  不等于默认值  说明有赋值
            YearRecord record = timetool.new YearRecord(ShowCalYearInt);
            record.initRecord(allRecordInProp);
            record.showRecord(); //   如果用户手动 输入 那么显示 12个月的信息
        }else{   // 等于默认值  显示三个月的信息
            YearRecord record = timetool.new YearRecord(Now_YearInt);
            record.initRecord(allRecordInProp);
            record.showRecord(month); //      默认没有输入年份 那么 只显示 三个月的信息
        }
        System.out.println();
        System.out.println();
        showYearCal();
        System.out.println();
        // 显示今天的日期
        System.out.println();
        System.out.println("══════════════"+" 现在的时间戳(ms) = "+ now.getTimeInMillis()+" ══════════════");
        System.out.println("══════════════"+" 现在的时间戳(s)  = "+ (now.getTimeInMillis()/1000)+"    ══════════════");




    }

    public static void showYearCal() {

        if(ShowCalYearInt != 2020){  //  如果不等于 默认值 那么就显示这个值
            ZYear year2020 = new ZYear(ShowCalYearInt);
            year2020.showYearInfo();
        }else{   // 等于默认值就 就显示现在的日期

            ZYear year2020 = new ZYear(Now_YearInt);
            year2020.showYearInfo();
        }


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




    static String  calculXinqiYiForMonthBegin(){
        //  计算月初 是星期几

        // 这个月  已过了 多少百分比
        Calendar beginMonthCalendar = Calendar.getInstance();

//        beginMonthCalendar.set(beginMonthCalendar.DAY_OF_WEEK, beginMonthCalendar.MONDAY);

        beginMonthCalendar.set(Calendar.DAY_OF_MONTH,1); // 设置成月初
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY,0);
        beginMonthCalendar.set(Calendar.HOUR,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MINUTE,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.SECOND,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MILLISECOND,0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY,0);

        int day = beginMonthCalendar.get(Calendar.DAY_OF_WEEK)  -1 ;

        String monthBegin = calculXinQi(day);

        return monthBegin;

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
//        System.out.println(" Bxiniq = "+ xiniq);
        // 0 ---> 周一
        // 1 ---> 周二
        // 2---> 周三
        //
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
                xinqiValue = "七";
                break;
            default:
                xinqiValue = "一";  // 默认周一
        }

        return xinqiValue;

    }

    static String   calculXinQi(int xiniq){
        String xinqiValue = "";
//      System.out.println(" Axiniq = "+ xiniq);
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

    static boolean isAddRecordOperation = false;
    static boolean isInputYearParam = false;

    static ArrayList<Record> allRecordInProp = new  ArrayList<Record>();

    public static void main(String[] args) {
H1_TimeTool timeTool = new H1_TimeTool();
             initSystemInfo();
        timeTool.initPropRecord();

        Locale.setDefault(Locale.CHINESE);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                }  else {

                    // 以 day  month 开头的数据 那么执行 记录操作 把放到  H1.properties
                    if(args[i].startsWith("day") || args[i].startsWith("month") ){
                        isAddRecordOperation = true;
                        CUR_INPUT_ParamStrList.add(args[i]);
                        continue;
                    }
                    String item = args[i] ;
                    if(isNumeric(item)){
                        ShowCalYearInt = Integer.parseInt(item);
                        isInputYearParam = true;
                        continue;   // 如果是 2020 那么 直接 continue
                    }
                    CUR_INPUT_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        if(isAddRecordOperation){   // 对  record数据 填充到 prop
            // day_dalknkalglnma   month_faklfnalkfl
            timeTool.operationRecord(timeTool);
        }
        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new  File(CUR_Dir_1_PATH);




        // 用户没有输入参数
        if ( CUR_INPUT_ParamStrList.size() == 0) {
            System.out.println("用户没有输入参数  提示如下:");
            showNoTypeTip(timeTool);
            return;
        }




        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH+"  不存在! ");
            return;
        }


        // 开始执行逻辑
        //1.  对参数进行过滤判断
        //2.  拿到参数再具体进行逻辑操作

        TimeOperation timeOperation = new TimeOperation();

        if(!timeOperation.checkInputParamListAndGetType(CUR_INPUT_ParamStrList) || timeOperation.inputType == 0){
            System.out.println("用户输入的 参数无法正确解析 请重新输入\n");
            showNoTypeTip(timeTool);
            return;
        }

        if(timeOperation.initParamWithType(timeOperation.inputType ,CUR_INPUT_ParamStrList)){
            System.out.println("用户输入的 参数无法正确解析 请重新输入\n");
            showNoTypeTip(timeTool);
            return;

        }

        setProperity();
    }

    void operationRecord(H1_TimeTool timeTool){

        ArrayList<Record> inputRecordList = new ArrayList<Record>();
        for (int i = 0; i < CUR_INPUT_ParamStrList.size(); i++) {
            String inputItem = CUR_INPUT_ParamStrList.get(i);
            long timestamp = new Date().getTime();
            Calendar now = Calendar.getInstance();
            int curMonth  = now.get(Calendar.MONTH) + 1;
            int curDay  = now.get(Calendar.DAY_OF_MONTH);

            if(inputItem.toLowerCase().startsWith("day") && inputItem.contains("_")){
                if(inputItem.toLowerCase().startsWith("day_")){
                    String dayDesc = inputItem.split("_")[1];
                    Record r = timeTool.new Record(curMonth,curDay,"",dayDesc,false,timestamp);
                    r.timeStamp = timestamp;
                    inputRecordList.add(r);
                    continue;
                }
            }else if(inputItem.toLowerCase().startsWith("month") && inputItem.contains("_")){
                String MonthDesc = inputItem.split("_")[1];
                if(inputItem.toLowerCase().startsWith("month_")){
                    String monthDesc = inputItem.split("_")[1];
                    Record r = timeTool.new Record(curMonth,curDay,monthDesc,"",true,timestamp);
                    inputRecordList.add(r);
                    continue;
                }

            }

        }
        for (int i = 0; i < inputRecordList.size(); i++) {
            Record recordItem = inputRecordList.get(i);
            G8_Properties.put(recordItem.getProperityKey(),recordItem.timeStamp+"");
        }

        timeTool.initPropRecord();

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


    void initPropRecord(){
          Set<Object> recordSet = G8_Properties.keySet();
          ArrayList<Object> propKeyList = new  ArrayList<Object>();
       propKeyList.addAll(recordSet);
       // allRecordInProp
       for (int i = 0; i < propKeyList.size(); i++) {
           allRecordInProp.add(new Record((String)propKeyList.get(i)));
       }
    }
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
                    ztimeStamp.year =  Long.parseLong(yearStr);
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





    static class ZYear {
        int year;
        ZSession[] sessions;

        ZYear(int xyear) {
            sessions = new ZSession[4];
            year = xyear;
            initSessionData(xyear);
        }

        void initSessionData(int xyear) {
            sessions[0] = new ZSession(xyear,1);
            sessions[1] = new ZSession(xyear,2);
            sessions[2] = new ZSession(xyear,3);
            sessions[3] = new ZSession(xyear,4);
        }

        void showYearInfo(){
            System.out.println(PaddingEndStr(" "," ",MONTH_LENGTH_MAX+1)+"【公元 " +year+"["+getAnimalNameForYear(year)+" "+ (year>4?calChineseYearName(year):"")+" ]"+" 年】");
            System.out.println("══════════════════════════╦══════════════════════════════╦══════════════════════════════╗");
            for (int i = 0; i < sessions.length; i++) {
                sessions[i].showSessionInfo();
                if(i != sessions.length - 1 ){
                    System.out.println("══════════════════════════╬══════════════════════════════╬══════════════════════════════╣");
                }
            }
            System.out.println("══════════════════════════╩══════════════════════════════╩══════════════════════════════╝");
        }
    }
    // 季度
    static  class ZSession {
        int year;
        int session_num;  // 第几个季度  1 , 2  ,  3  , 4 ,
        MonthData[] sessionDatas ;

        ZSession(int xyear , int xSession_num){
            this.year = xyear;
            this.session_num = xSession_num;
            sessionDatas = new  MonthData[3];
            initMonthData(xyear,session_num);
        }

        void initMonthData(int year , int sessionNum){
            sessionDatas[0] = new MonthData(year,sessionNum*3-2);
            sessionDatas[1] = new MonthData(year,sessionNum*3-1);
            sessionDatas[2] = new MonthData(year,sessionNum*3);
        }

        void showSessionInfo(){
            ArrayList<String> sessionInfoList  = new  ArrayList<String>();
            ArrayList<ArrayList<String>> curSessionMonthList = new  ArrayList<ArrayList<String>>();
            int monthContentLine = 0 ;
            for (int i = 0; i < sessionDatas.length; i++) {
                MonthData monthDataItem = sessionDatas[i];
                ArrayList<String> monthInfoList = monthDataItem.showMonthInfo();
                monthContentLine = monthInfoList.size();
                curSessionMonthList.add(monthInfoList);
            }


            for (int i = 0; i < monthContentLine; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < curSessionMonthList.size(); j++) {
                    ArrayList<String> lineContent = curSessionMonthList.get(j);
                    String curLine = lineContent.get(i);
                    sb.append(curLine);
                }
                sessionInfoList.add(sb.toString());
            }

            for (int i = 0; i < sessionInfoList.size(); i++) {
                String lineDate = sessionInfoList.get(i);
                System.out.println(lineDate);
            }

        }

    }


    //   月份
    static class MonthData{
        int month_day_content_length = 20; // 14 15 16 17 18 19 20  月日数最大长度
        int month_day_content_max_length = MONTH_LENGTH_MAX; // 14 15 16 17 18 19 20  加 后面两个空格  最大长度
        int year;   //  该年
        int month;  // 该月
        int month_days_num;  // 该月有几日
        int begin_week_day;    //   该月开始的星期
        String month_name;
        ArrayList<String> monthDayDesc;

        MonthData(int year,int month){
            this.year = year;
            this.month = month;
            this.month_days_num = getDayForMonth_Year(year,month);
            this.begin_week_day = calculXinqiYiForMonthBegin(year,month);
            month_name = getfixedMonthName(Month_Name_Arr[month -1]);  //  当前月数
            month_day_content_length = 20;
            month_day_content_max_length = MONTH_LENGTH_MAX;
            monthDayDesc = getFixedMonthDaysDescList(month_days_num);
        }


        ArrayList<String> getFixedMonthDaysDescList(int month_days_num){
            ArrayList<String> dayDescList = new    ArrayList<String>();
            for (int i = 0; i <month_days_num ; i++) {
//                System.out.println("month_days_num = "+ month_days_num + "DayDescList.length ="+ DayDescList.size());
                dayDescList.add(DayDescList.get(i));
            }
            return dayDescList;
        }


        String getfixedMonthName(String commonMonthName){
            return "      "+commonMonthName.trim()+"         ";
        }


        ArrayList<String>  showMonthInfo(){
            ArrayList<String> fixed21Length = new  ArrayList<String>();
            ArrayList<String>   cur8LineStrList = getShowMonthList_8_line();

            for (int i = 0; i < cur8LineStrList.size(); i++) {
                String itemStr = cur8LineStrList.get(i);
                int paddingLength = month_day_content_max_length - itemStr.length();
                String fixedStr = PaddingEndStr(itemStr," ",paddingLength);
                fixed21Length.add(fixedStr+"║    ");
            }

            return fixed21Length;
        }

        ArrayList<String>  getShowMonthList_8_line(){
            ArrayList<String> curMonthDescList = new  ArrayList<String>();


            String blankStr = calculFirstBlankStr(begin_week_day);
            int skipNum = blankStr.length()/3;
            int content_1_indexEnd = 7 - skipNum;
            int content_1_indexBegin =1;
            String String1 = blankStr+calDayDescLine(content_1_indexBegin,content_1_indexEnd);

//          System.out.println("content_1_indexBegin = "+ content_1_indexBegin + "  content_1_indexEnd = "+ content_1_indexEnd);
//          System.out.println("String1 = "+ String1 );
//          System.out.println("-------------");


            int content_2_indexBegin =content_1_indexEnd + 1;
            int content_2_indexEnd =content_2_indexBegin + 6;
            String String2 = calDayDescLine(content_2_indexBegin,content_2_indexEnd);

//          System.out.println("content_2_indexBegin = "+ content_2_indexBegin + "  content_2_indexEnd = "+ content_2_indexEnd);
//          System.out.println("String2 = "+ String2 );
//          System.out.println("-------------");

            int content_3_indexBegin =content_2_indexEnd + 1;
            int content_3_indexEnd =content_3_indexBegin + 6;
            String String3 = calDayDescLine(content_3_indexBegin,content_3_indexEnd);

//          System.out.println("content_3_indexBegin = "+ content_3_indexBegin + "  content_3_indexEnd = "+ content_3_indexEnd);
//          System.out.println("String3 = "+ String3 );
//          System.out.println("-------------");

            int content_4_indexBegin =content_3_indexEnd + 1;
            int content_4_indexEnd =content_4_indexBegin + 6;
            String String4 = calDayDescLine(content_4_indexBegin,content_4_indexEnd);

//          System.out.println("content_4_indexBegin = "+ content_4_indexBegin + "  content_4_indexEnd = "+ content_4_indexEnd);
//          System.out.println("String4 = "+ String4 );
//          System.out.println("-------------");

            int content_5_indexBegin =content_4_indexEnd + 1;
            int content_5_indexEnd =content_5_indexBegin + 6;
            String String5 = calDayDescLine(content_5_indexBegin,content_5_indexEnd);

//          System.out.println("content_5_indexBegin = "+ content_5_indexBegin + "  content_5_indexEnd = "+ content_5_indexEnd);
//          System.out.println("String5 = "+ String5 );
//          System.out.println("-------------");


            int content_6_indexBegin =content_5_indexEnd + 1;
            int content_6_indexEnd =content_6_indexBegin + 6;
            String String6 = calDayDescLine(content_6_indexBegin,content_6_indexEnd);

//          System.out.println("content_6_indexBegin = "+ content_6_indexBegin + "  content_6_indexEnd = "+ content_6_indexEnd);
//          System.out.println("String6 = "+ String4 );
//          System.out.println("-------------");



/*
          String dayDescLine = calDayDescLine();
          String curAllContent = blankStr + dayDescLine;
          int allContentLength = curAllContent.length();
          System.out.println("curAllContent = "+ curAllContent);
          System.out.println("allContentLength = "+ allContentLength);
          int paddingLength = 120 - allContentLength;
          String fix_120_curMonDesc = PaddingEndStr(curAllContent," ",paddingLength);
          int fixed_content_length = fix_120_curMonDesc.length();
          String String1 = fix_120_curMonDesc.substring(0,20);
          String String2 = fix_120_curMonDesc.substring(20,40);
          String String3 = fix_120_curMonDesc.substring(40,60);
          String String4 = fix_120_curMonDesc.substring(60,80);
          String String5 = fix_120_curMonDesc.substring(80,100);
          String String6 = fix_120_curMonDesc.substring(100,120);
*/

//          System.out.println("String0 = "+month_name);
//          System.out.println("String0 = "+week_name_desc);
//          System.out.println("String1 = "+ String1);
//          System.out.println("String2 = "+ String2);
//          System.out.println("String3 = "+ String3);
//          System.out.println("String4 = "+ String4);
//          System.out.println("String5 = "+ String5);
//          System.out.println("String6 = "+ String6);

            curMonthDescList.add(month_name);   //  月份名称
            curMonthDescList.add(week_name_desc);  //  周数名称
            curMonthDescList.add(String1);  //
            curMonthDescList.add(String2);  //
            curMonthDescList.add(String3);  //
            curMonthDescList.add(String4);  //
            curMonthDescList.add(String5);  //
            curMonthDescList.add(String6);  //
            return curMonthDescList;
        }



        String calDayDescLine(int beginIndex , int EndIndex){
            StringBuilder sb = new StringBuilder();
            for (int i = beginIndex - 1   ; i < EndIndex; i++) {
                String itemStr = "  ";
                if(i < monthDayDesc.size()){
                    itemStr =  monthDayDesc.get(i);
                }
                if(itemStr == null){
                    itemStr = "  ";
                }
                if(i != EndIndex -1){
                    sb.append(itemStr+" ");
                }else{
                    sb.append(itemStr);
                }
            }
//            System.out.println("calDayDescLine("+beginIndex+","+EndIndex+") = "+  sb.toString());
            return sb.toString();
        }

        String calDayDescLine(){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < monthDayDesc.size(); i++) {
                if(i != monthDayDesc.size() -1){
                    sb.append(monthDayDesc.get(i)+" ");
                }else{
                    sb.append(monthDayDesc.get(i));
                }
            }
            System.out.println("calDayDescLine() = "+  sb.toString());
            return sb.toString();
        }

        ArrayList<String>    calculMonthDayDesc(String blankStr,int contentLength , int monthdays){
            ArrayList<String> monthDescList = new  ArrayList<String>();
            int index = 1;
            int endIndex = monthdays;


            return monthDescList;
        }
        String  calculFirstBlankStr(int begin_week_day){
            String blankStr = "";
            //0-周天  1-周一    6-周六    7-周天
            //  0  和 7 都表示 周天
            if(begin_week_day == 1){
                blankStr =  "";
            } else if(begin_week_day == 2){
                blankStr =  "   "; // 3 个 空格
            } else if(begin_week_day == 3){
                blankStr =  "      "; // 6 个 空格
            } else if(begin_week_day == 4){
                blankStr =  "         "; // 9 个 空格
            } else if(begin_week_day == 5){
                blankStr =  "            ";   // 12 个 空格
            } else if(begin_week_day == 6){
                blankStr =  "               ";// 15 个 空格
            } else if(begin_week_day == 7 || begin_week_day == 0){
                blankStr =  "                  ";     // 18 个 空格
            }
//          System.out.println("begin_week_day = "+begin_week_day + "   blankStr ="+blankStr+"=");
            return blankStr;
        }


    }

    static  String PaddingEndStr(String rawContent , String flagStr , int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(flagStr);
        }
        return rawContent+sb.toString();
    }





    static int AnimalYear_Begin = 1900;
    static int AnimalYear_End =  Calendar.getInstance().get(Calendar.YEAR);


    public static void showAnimalYearInfo() {

        Z_AnimalYear animalYear = new   Z_AnimalYear(AnimalYear_Begin,AnimalYear_End);
        animalYear.showAnimalYearInfo();

    }

    static String getAnimalNameForYear(int year){
        return AnimalYearName.get(year%12);
    }



    static class Z_AnimalYear {
        int  animalYear_begin;
        int  animalYear_end;
        int oneYearLineNum_Row ;   // 当前年份最多的那个生效的年的个数  用于对齐
        int CurLineCount;
        Z_AnimalData[] zsessions1;   // 0申猴  1酉鸡 2戌狗
        Z_AnimalData[] zsessions2;   // 3亥猪  4子鼠 5丑牛
        Z_AnimalData[] zsessions3;   // 6寅虎  7卯兔 8辰龙
        Z_AnimalData[] zsessions4;   // 9巳蛇  10午马 11未羊

        ArrayList<Z_AnimalData[]>  animalInfoList = new  ArrayList<Z_AnimalData[]>();
        Z_AnimalYear(int beginYear,int endYear) {
            zsessions1 = new Z_AnimalData[3];
            zsessions2 = new Z_AnimalData[3];
            zsessions3 = new Z_AnimalData[3];
            zsessions4 = new Z_AnimalData[3];
            animalYear_begin = beginYear;
            animalYear_end = endYear;
            initAnimalData(animalYear_begin ,animalYear_end );
        }

        void initAnimalData(int beginYear , int endYear) {
            zsessions1[0] = new Z_AnimalData(0,beginYear,endYear);
            zsessions1[1] = new Z_AnimalData(1,beginYear,endYear);
            zsessions1[2] = new Z_AnimalData(2,beginYear,endYear);
            zsessions2[0] = new Z_AnimalData(3,beginYear,endYear);
            zsessions2[1] = new Z_AnimalData(4,beginYear,endYear);
            zsessions2[2] = new Z_AnimalData(5,beginYear,endYear);
            zsessions3[0] = new Z_AnimalData(6,beginYear,endYear);
            zsessions3[1] = new Z_AnimalData(7,beginYear,endYear);
            zsessions3[2] = new Z_AnimalData(8,beginYear,endYear);
            zsessions4[0] = new Z_AnimalData(9,beginYear,endYear);
            zsessions4[1] = new Z_AnimalData(10,beginYear,endYear);
            zsessions4[2] = new Z_AnimalData(11,beginYear,endYear);

            animalInfoList.add(zsessions1);
            animalInfoList.add(zsessions2);
            animalInfoList.add(zsessions3);
            animalInfoList.add(zsessions4);
            oneYearLineNum_Row = calculAndSetMaxAnimalYearSize();
            CurLineCount = (oneYearLineNum_Row+1)/2;
        }

        int  calculAndSetMaxAnimalYearSize(){
            int maxSize = 0;

            for (int i = 0; i < animalInfoList.size(); i++) {
                Z_AnimalData[] animalDataArr =   animalInfoList.get(i);

                for (int j = 0; j < animalDataArr.length; j++) {
                    Z_AnimalData animalYearData = animalDataArr[j];

                    if(maxSize < animalYearData.OneAnimalYearSize){
                        maxSize = animalYearData.OneAnimalYearSize;
                    }
                }
            }

            for (int i = 0; i < animalInfoList.size(); i++) {
                Z_AnimalData[] animalDataArr =   animalInfoList.get(i);

                for (int j = 0; j < animalDataArr.length; j++) {
                    Z_AnimalData animalYearData = animalDataArr[j];
                    animalYearData.setCurrentLineNum((maxSize+1)/2);
                }
            }

            return maxSize;
        }

        void  showAnimalYearInfo(){
            System.out.println("                                     "+"【公元 "+animalYear_end +" ["+getAnimalNameForYear(animalYear_end)+" "+calChineseYearName(animalYear_end)+" ]"+" 年】");

            System.out.println("══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╗");
            for (int i = 0; i < animalInfoList.size(); i++) {
                ArrayList<String>  logArr = new ArrayList<String>();
                Z_AnimalData[] animalDataArr =   animalInfoList.get(i);
                ArrayList<ArrayList<String>>  mSessionLogList = new ArrayList<ArrayList<String>>();
                for (int j = 0; j < animalDataArr.length; j++) {
                    Z_AnimalData animalYearData = animalDataArr[j];
                    ArrayList<String>  animalYearDataInfoList = animalYearData.getAnimalInfoList();
//                  System.out.println("animalYearDataInfoList.size = "+ animalYearDataInfoList.size());
                    mSessionLogList.add(animalYearDataInfoList);
                }

                for (int j = 0; j < (CurLineCount+1); j++) {   //  每行 每行读取
                    StringBuilder mLineSB = new StringBuilder();

                    for (int k = 0; k < mSessionLogList.size(); k++) {
                        ArrayList<String>  oneAnimalInfo = mSessionLogList.get(k);
//                      System.out.println("CurLineCount = "+ CurLineCount);
                        String oneLineStr = oneAnimalInfo.get(j)+"║";
                        mLineSB.append(oneLineStr);
                    }
                    logArr.add(mLineSB.toString());
                }

                for (int j = 0; j < logArr.size(); j++) {
                    System.out.println(logArr.get(j));

                }

                if(i != animalInfoList.size() -1 ){
                    System.out.println("══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╣");
                }

            }


            System.out.println("══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╝");

        }

    }



    static class Z_AnimalData {
        int animalIndex ;
        String animalName;
        ArrayList<Integer> currentAnimalYearList;
        int OneAnimalYearSize ; // 当前生效 有多少个 年份的数据
        int currentLineNum;   // 当前数据总的行数    不包括 标题
        int curYearStrLength ;   // 当前一个年份的最大标识长度
        Z_AnimalData(int pAnimalIndex , int beginYear , int endYear){
            animalIndex = pAnimalIndex;
            currentAnimalYearList = new  ArrayList<Integer>();
            animalName = getAnimalNameForYear(animalIndex);
            OneAnimalYearSize = 0;
            currentLineNum = 0;
            curYearStrLength = YearStrLength;
            initAnimalYearData(beginYear ,endYear );
        }

        public void setCurrentLineNum(int currentLineNum) {
            this.currentLineNum = currentLineNum;
        }

        void initAnimalYearData(int beginYear , int endYear){
            for (int i = beginYear; i <= endYear ; i++) {
                if(i % 12  == animalIndex ){     //  选中的年份
                    currentAnimalYearList.add(i);
                }
            }
            OneAnimalYearSize = currentAnimalYearList.size();
        }

        ArrayList<String> getAnimalInfoList(){
            ArrayList<String> infoList = new   ArrayList<String>();
            //1. 生肖名称
            infoList.add(PaddingEndStr(""," ",curYearStrLength)+animalName+PaddingEndStr(""," ",curYearStrLength-animalName.length()+4));

            ArrayList<YearPair>  yearPairList = new ArrayList<YearPair>();
            YearPair yearPair = new YearPair();
            for (int i = currentAnimalYearList.size()-1; i >= 0; i--) {
                int curYear = currentAnimalYearList.get(i);
                if(!yearPair.isStep1_Init()){
                    yearPair.setPreYear(curYear);
                    continue;
                }
                if(!yearPair.isStep2_Init()){
                    yearPair.setEndYear(curYear);
                    yearPairList.add(yearPair);
                    yearPair = new YearPair();
                    continue;
                }

            }
            if(!yearPairList.contains(yearPair)){
                yearPairList.add(yearPair);
            }

            while(yearPairList.size() < currentLineNum ){
                yearPairList.add(new YearPair() );
            }


            for (int i = 0; i < yearPairList.size(); i++) {
                YearPair mYearPairItem = yearPairList.get(i);
//                 System.out.println("Index:"+i+"===="+mYearPairItem+"====");
                infoList.add(mYearPairItem.toString());
            }
//            System.out.println("infoList.size = "+ infoList.size());
            return infoList;
        }




    }




    static class YearPair{
        Integer OneYearSize ;

        Integer preYear;
        Integer endYear;

        YearPair(){
            preYear = null;
            endYear = null;
            OneYearSize = YearStrLength;
        }

        YearPair(int xpreYear ){
            preYear = xpreYear;
            endYear = 0;
            OneYearSize = YearStrLength;
        }

        public void setEndYear(Integer endYear) {
            this.endYear = endYear;
        }

        public void setPreYear(Integer preYear) {
            this.preYear = preYear;
        }

        boolean isStep1_Init(){
            return this.preYear != null && this.preYear != 0;
        }


        boolean isStep2_Init(){
            return this.preYear != null  && this.preYear != 0 && this.endYear != null && this.endYear != 0;
        }

        @Override
        public String toString() {
//           return "【" +preYear+"年  -- "  +  endYear+"年】";
            if(isStep1_Init() && isStep2_Init()){
                return makeOneYearStr(preYear) +makeOneYearStr(endYear);
            }
            if(!isStep1_Init()){
                return makeOneYearStr(preYear)+"  " +makeOneYearStr(endYear)+"  ";
            } else if(!isStep2_Init()){
                return makeOneYearStr(preYear)+"  " +makeOneYearStr(endYear);
            }
            return makeOneYearStr(preYear) +makeOneYearStr(endYear);
        }

        // 2016(  4岁)
        String makeOneYearStr(Integer curYear){
            if(curYear == null){
                return PaddingEndStr(" "," ",OneYearSize);
            }
            String animalYearStr = "";
            int age = Now_YearInt - curYear;
            int ageStrLength = (""+age).length();
            String ageStr = "("+PaddingPreStr(""+age," ",3-ageStrLength)+"岁)";

            String year_age_str = " "+curYear+"_"+calChineseYearName(curYear)+ageStr;
            int length_YearAgeStr = year_age_str.length();
            int paddingLenth = OneYearSize - length_YearAgeStr;

            animalYearStr = year_age_str + PaddingEndStr(""," ",paddingLenth);

            return animalYearStr;

        }
    }

    public static void main3(String[] args) {
        Z_AnimalData data = new Z_AnimalData(4,1900,2020);
        data.setCurrentLineNum((data.currentAnimalYearList.size()+1)/2);
        for (int i = 0; i < data.currentAnimalYearList.size(); i++) {
            int curYear = data.currentAnimalYearList.get(i);
//            System.out.println(curYear +"年 = 鼠年");
        }


        ArrayList<String> log =  data.getAnimalInfoList();


        for (int i = 0; i < log.size(); i++) {
//            System.out.println(log.get(i));
        }




    }



    static  String PaddingPreStr(String rawContent , String flagStr , int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(flagStr);
        }
        return sb.toString()+rawContent;
    }


    class YearRecord {

        int year;  // 对应的年份

        ArrayList<MonthRecord> monthRecordList;

        YearRecord(int mYear) {
            year = mYear;
            monthRecordList = new ArrayList<MonthRecord>();
            for (int i = 1; i <= 12; i++) {
                monthRecordList.add(new MonthRecord(mYear, i));
            }
        }

        void showRecord() {
            for (int i = 0; i < monthRecordList.size(); i++) {
                monthRecordList.get(i).showRecord();

            }
        }

        void showRecord(int month) {
            for (int i = 0; i < monthRecordList.size(); i++) {
                monthRecordList.get(i).showRecord(month);

            }
        }

        void initRecord(ArrayList<Record> allRecord) {
            for (int i = 0; i < monthRecordList.size(); i++) {
                monthRecordList.get(i).initRecord(allRecord);

            }
        }

    }


    /*    July_7
        Mo Tu We Th Fr Sa Su
           1  2  3  4  5
        6  7  8  9 10 11 12
        13 14 15 16 17 18 19
        20 21 22 23 24 25 26
        27 28 29 30 31
        */
    static int Default_MonthDescLength = 168 + 6;
    static int Default_DayDescLength = 28 + 6;

    class MonthRecord {
        int monthDescLength;   //  月志的 长度
        int dayDescLength;   //  日志的长度

        int maxDayRecordCountLine;  // 在Map 中 的 Value的最大的长度  用于计算 日志显示的最大长度
        int year;
        int month;
        int mDaySum;  // 一个月的天数
        int beginDayOfWeek;  // 该月1日星期几   1 2 3 4 5 6 7
        int firstWeekCount;   // 第1周的天数
        ArrayList<ArrayList<Integer>> weekKeyList;  //  // index0:  { 1 ,2 ,3 ,4 ,5} // index1:  {6  7  8  9 10 11 12}

        ArrayList<Record> OneMonthRecordList;    // 所有记录
        ArrayList<Record> OneImportantRecordList;  // 重要记录
        ArrayList<Record> OneCommonRecordList;  // 一般记录

        Map<ArrayList<Integer>, ArrayList<Record>> weeksInMonthMap;  // 周的数据   对应周数的记录

        void showRecord(int month) {

            int preMonth = month - 1 ;
            int stepMonth =  month + 1 ;

            int  mPreMonth =  preMonth%12==0?12:preMonth%12;
            int mCurMonth = month%12==0?12:month%12;
            int  mStepMonth =  stepMonth%12==0?12:stepMonth%12;
//            System.out.println("温顾");
            showRecordWithMonth(mPreMonth,-1);
//            System.out.println("正当时");
            showRecordWithMonth(mCurMonth,0);
//            System.out.println("知新");
            showRecordWithMonth(mStepMonth,1);
        }

        void showRecordWithMonth(int monthParam,int index) {
            if(month != monthParam){
               return;
            }
            System.out.println();
            String tip = "";
            if(index == -1){
                tip = "温故";
            }else if(index == 0){
                tip = "正当时";
            }else if(index == 1){
                tip = "知新";
            }
            String title = "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗";
            String tail = "╚══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╝";
//【202007月 活动记事日历】
            String monthStr = month > 9 ? "" + month : "0" + month;
            System.out.println("                                                                          " + "【" + year + "年" + monthStr + "《"+tip+"》"+ "月" + " 活动记事日历】");
            // 1. 打印 title
            System.out.println(title);

            // 2. 打印 monthDesc Record        getPaddingString
            if(OneImportantRecordList.size() > 0){
                for (int i = 0; i < OneImportantRecordList.size(); i++) {
                    Record recordItem = OneImportantRecordList.get(i);
                    String recordStr = recordItem.monthStr + recordItem.dayStr + ": " + recordItem.monthDesc84Chinese;
                    System.out.println("║" + getPaddingString(recordStr, monthDescLength, " ", false) + "║");
                }
            }else if(OneImportantRecordList.size() == 0){

                System.out.println("║                                                                                                                                                                              ║");
            }

            System.out.println("╠══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╣");

            StringBuilder lineOne = new StringBuilder();
            StringBuilder lineTwo = new StringBuilder();
            for (int i = 0; i < weekKeyList.size(); i++) {
                ArrayList<Integer> weekDays = weekKeyList.get(i);
                int days = weekDays.size();
                int weekNum = i + 1;
                String descItem = weekNum + "w(" + days + "d)"+nowSelectTip(month,weekDays);
                descItem = getPaddingString(descItem, 14 + descItem.length(), " ", true);
                descItem = getPaddingString(descItem, 14 + descItem.length(), " ", false);
                descItem = descItem + "║";
                descItem = descItem.replace("〓  ","〓");
//                System.out.println(descItem);
                lineOne.append(descItem);

                int beginDay = weekDays.get(0);
                int endDay = weekDays.get(weekDays.size() - 1);

                String beginDayStr = beginDay > 9 ? "" + beginDay : "0" + beginDay;
                String endDayStr = endDay > 9 ? "" + endDay : "0" + endDay;
                String dayDesc = monthStr + "." + beginDayStr + "══" + monthStr + "." + endDayStr;

                dayDesc = getPaddingString(dayDesc, 11 + dayDesc.length(), " ", true);
                dayDesc = getPaddingString(dayDesc, 11 + dayDesc.length(), " ", false);
                dayDesc = dayDesc + "║";
                lineTwo.append(dayDesc);

            }
            System.out.println("║" + lineOne);
            System.out.println("║" + lineTwo);
            System.out.println("╠══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╣");

            for (int i = 0; i < maxDayRecordCountLine; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < weekKeyList.size(); j++) {
                    ArrayList<Integer> weekKey = weekKeyList.get(j);
                    ArrayList<Record>  recordList = weeksInMonthMap.get(weekKey);
                    if(recordList == null){
                        sb.append("                                  ║");
                        continue;
                    }
                    Record recordItem = recordList.get(i);
                    if(recordItem == null){
                        sb.append("                                  ║");
                    }else if(recordItem.isMonthDesc){
                        sb.append("                                  ║");
                    }
                    else {

                        sb.append(getPaddingString(recordItem.monthStr+recordItem.dayStr+": "+recordItem.dayDesc14Chinese,dayDescLength," ",false)+"║");
                    }

                }
                System.out.println("║"+sb);

            }
            System.out.println(tail);

        }

        String nowSelectTip(int month ,ArrayList<Integer> weekDays ){
            String tip = "";
            Calendar now = Calendar.getInstance();
            int now_month = now.get(Calendar.MONTH)+ 1;
            int now_day = now.get(Calendar.DAY_OF_MONTH);

            if(month == now_month &&  weekDays.contains(now_day)){
                return "〓";
            }

            return tip;

        }
        void showRecord() {
            System.out.println();
            String title = "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗";
            String tail = "╚══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╩══════════════════════════════════╝";
//【202007月 活动记事日历】
            String monthStr = month > 9 ? "" + month : "0" + month;
            System.out.println("                                                                          " + "【" + year + "年" + monthStr + "月" + " 活动记事日历】");
            // 1. 打印 title
            System.out.println(title);

            // 2. 打印 monthDesc Record        getPaddingString
            if(OneImportantRecordList.size() > 0){
                for (int i = 0; i < OneImportantRecordList.size(); i++) {
                    Record recordItem = OneImportantRecordList.get(i);
                    String recordStr = recordItem.monthStr + recordItem.dayStr + ": " + recordItem.monthDesc84Chinese;
                    System.out.println("║" + getPaddingString(recordStr, monthDescLength, " ", false) + "║");
                }
            }else if(OneImportantRecordList.size() == 0){

                System.out.println("║                                                                                                                                                                              ║");
            }

            System.out.println("╠══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╦══════════════════════════════════╣");

            StringBuilder lineOne = new StringBuilder();
            StringBuilder lineTwo = new StringBuilder();
            for (int i = 0; i < weekKeyList.size(); i++) {
                ArrayList<Integer> weekDays = weekKeyList.get(i);
                int days = weekDays.size();
                int weekNum = i + 1;
                String descItem = weekNum + "w(" + days + "d)";
                descItem = getPaddingString(descItem, 14 + descItem.length(), " ", true);
                descItem = getPaddingString(descItem, 14 + descItem.length(), " ", false);
                descItem = descItem + "║";
//                System.out.println(descItem);
                lineOne.append(descItem);

                int beginDay = weekDays.get(0);
                int endDay = weekDays.get(weekDays.size() - 1);

                String beginDayStr = beginDay > 9 ? "" + beginDay : "0" + beginDay;
                String endDayStr = endDay > 9 ? "" + endDay : "0" + endDay;
                String dayDesc = monthStr + "." + beginDayStr + "══" + monthStr + "." + endDayStr;

                dayDesc = getPaddingString(dayDesc, 11 + dayDesc.length(), " ", true);
                dayDesc = getPaddingString(dayDesc, 11 + dayDesc.length(), " ", false);
                dayDesc = dayDesc + "║";
                lineTwo.append(dayDesc);

            }
            System.out.println("║" + lineOne);
            System.out.println("║" + lineTwo);
            System.out.println("╠══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╬══════════════════════════════════╣");

            for (int i = 0; i < maxDayRecordCountLine; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < weekKeyList.size(); j++) {
                    ArrayList<Integer> weekKey = weekKeyList.get(j);
                    ArrayList<Record>  recordList = weeksInMonthMap.get(weekKey);
                    if(recordList == null){
                        sb.append("                                  ║");
                        continue;
                    }
                    Record recordItem = recordList.get(i);
                    if(recordItem == null){
                        sb.append("                                  ║");
                    }else if(recordItem.isMonthDesc){
                        sb.append("                                  ║");
                    }
                    else {

                        sb.append(getPaddingString(recordItem.monthStr+recordItem.dayStr+": "+recordItem.dayDesc14Chinese,dayDescLength," ",false)+"║");
                    }

                }
                System.out.println("║"+sb);

            }
            System.out.println(tail);

        }


        void initRecord(ArrayList<Record> mRecord_Origin_DataList) {
            OneMonthRecordList = new ArrayList<Record>();
            OneImportantRecordList = new ArrayList<Record>();
            OneCommonRecordList = new ArrayList<Record>();


            for (int i = 0; i < mRecord_Origin_DataList.size(); i++) {
                if (mRecord_Origin_DataList.get(i).month == month) {
                    if (mRecord_Origin_DataList.get(i).isMonthDesc) {
                        OneImportantRecordList.add(mRecord_Origin_DataList.get(i));
                    } else {
                        OneCommonRecordList.add(mRecord_Origin_DataList.get(i));
                    }
                    OneMonthRecordList.add(mRecord_Origin_DataList.get(i));
                }
            }

            //           Map<ArrayList<Integer>,ArrayList<Record>> weeksInMonthMap;  // 周的数据   对应周数的记录
            weeksInMonthMap = new HashMap<ArrayList<Integer>, ArrayList<Record>>();
            for (int j = 0; j < OneMonthRecordList.size(); j++) {
                Record recordItem = OneMonthRecordList.get(j);
                int recordDay = recordItem.day;
                int weekKeyIndex = calWeekForDay(weekKeyList, recordDay);
                ArrayList<Integer> weekdayListkey = weekKeyList.get(weekKeyIndex);
                ArrayList<Record> monthRecordList = weeksInMonthMap.get(weekdayListkey);
                if (monthRecordList == null) {
                    monthRecordList = new ArrayList<Record>();
                    monthRecordList.add(recordItem);
                    weeksInMonthMap.put(weekdayListkey, monthRecordList);
                } else {
                    monthRecordList.add(recordItem);
                }
            }


            maxDayRecordCountLine = 0;
            for (int i = 0; i < weekKeyList.size(); i++) {
                ArrayList<Integer> weekKey = weekKeyList.get(i);
                ArrayList<Record> recordList = weeksInMonthMap.get(weekKey);
                if(recordList == null){continue;}
                int currentDayRecordSize = calDayRecordSize(recordList);
                if (maxDayRecordCountLine < currentDayRecordSize) {
                    maxDayRecordCountLine = currentDayRecordSize;
                }
            }


        }

        int calDayRecordSize(ArrayList<Record> recordList){
            int count  = 0;
            for (int i = 0; i < recordList.size(); i++) {
                if(!recordList.get(i).isMonthDesc){
                    count++;
                }
            }
            return count;
        }

        int calWeekForDay(ArrayList<ArrayList<Integer>> weekDayList, int day) {
            int index = 0;
            for (int i = 0; i < weekDayList.size(); i++) {
                ArrayList<Integer> intList = weekDayList.get(i);
                if (intList.contains(day)) {
                    index = i;
                    return index;
                }
            }
            return index;
        }

        MonthRecord(int mYear, int mMonth) {
            monthDescLength = Default_MonthDescLength;
            dayDescLength = Default_DayDescLength;

            year = mYear;
            month = mMonth;
            mDaySum = calculMonthDayCount(mYear, mMonth);
            beginDayOfWeek = calculXinqiYiForMonthBegin(mYear, mMonth);
            firstWeekCount = 7 - beginDayOfWeek + 1;
            weekKeyList = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> firstWeek = new ArrayList<Integer>();

            ArrayList<Integer> otherWeek = new ArrayList<Integer>();
//            System.out.println("Year = "+ year + "    Month = "+ month + "  mDaySum = "+ mDaySum + "    beginDayOfWeek="+ beginDayOfWeek +"   firstWeekCount = "+ firstWeekCount );
            for (int i = 1; i <= mDaySum; i++) {
                if (i < firstWeekCount) {
                    firstWeek.add(i);
                    continue;
                }
                if (i == firstWeekCount) {
                    firstWeek.add(i);
                    weekKeyList.add(firstWeek);
                    continue;
                }
                otherWeek.add(i);
                if (otherWeek.size() == 7) {
                    weekKeyList.add(otherWeek);
                    otherWeek = new ArrayList<Integer>();
                }
            }
            if (weekKeyList.size() == 4) {
                weekKeyList.add(otherWeek);
            } else if (weekKeyList.size() == 5) {
                weekKeyList.get(weekKeyList.size() - 1).addAll(otherWeek);
            }
/*

            if(weekKeyList.size() != 5 ){
                System.out.println("weekKeyList.size = "+ weekKeyList.size() +"  tip: 只能为5");
            }

            for (int i = 0; i < weekKeyList.size(); i++) {
                ArrayList<Integer> weekdays = weekKeyList.get(i);
                System.out.println("week["+i+"] = "+ Arrays.toString(weekdays.toArray()));

            }

*/


//        initRecord();

        }
// key
// index0:  { 1 ,2 ,3 ,4 ,5}
// index1:  {6  7  8  9 10 11 12}
// index1:  {13 14 15 16 17 18 19}
// index1:  {20 21 22 23 24 25 26}
// index1:  {27 28 29 30 31}

// value  就是 对应的需要显示在这个week下的记录了

    }

    class Record {

        int month;    //  记录的 月份
        int day;     //  记录的 日份    0 标识为    该月  覆盖整月
        boolean isMonthDesc;   // 是否在 月份栏输出
        String dayDesc14Chinese;  //  最多14个汉字的 周栏记录
        String monthDesc84Chinese; // 月份记录 最多 84个汉字 isImportant 为 true 时  输出
        String dayStr;
        String monthStr;
        long timeStamp;  //  写入的时间  也是 prop的 value的值

        String getProperityKey(){
            String monthDesc84ChineseWithOutBelowLine = monthDesc84Chinese.replaceAll("_","-");
            String dayDesc14ChineseWithOutBelowLine = dayDesc14Chinese.replaceAll("_","-");

            String key = month+"_"+day+"_"+monthDesc84ChineseWithOutBelowLine+"_"+dayDesc14ChineseWithOutBelowLine+"_"+isMonthDesc+"_"+timeStamp;

            return key;
        }

        Record(String proKey){
            if(!proKey.contains("_")){
                System.out.println("当前 proKey "+proKey+"  无法解析为 Record");
                return;
            }
            String[] params = proKey.split("_");

            if(params.length !=  6){
                System.out.println("当前 proKey "+proKey+"  正常解析为 5个部分!");
                return;
            }

            String str0 = params[0];
            String str1 = params[1];
            String str2 = params[2];
            String str3 = params[3];
            String str4 = params[4];
            String str5 = params[5];
            if(isNumeric(str0)){
                month = Integer.parseInt(str0);
                 monthStr = month > 9 ? "" + month : "0" + month;
            }

            if(isNumeric(str1)){
                day = Integer.parseInt(str1);
                dayStr = day > 9 ? "" + day : "0" + day;
            }
            if(isNumeric(str5)){
                timeStamp = Long.parseLong(str5);
            }


            monthDesc84Chinese = str2;
            dayDesc14Chinese = str3;

            if("true".equals(str4.trim()) || "false".equals(str4.trim())){
                isMonthDesc =  Boolean.parseBoolean(str4);
            }



        }

        Record(int mMonth, int mDay, String monthDesc, String dayDesc, boolean mIsMonthDesc,long mTimeStamp) {
            month = mMonth;
            day = mDay;
            isMonthDesc = mIsMonthDesc;
            monthDesc84Chinese = monthDesc;
            dayDesc14Chinese = dayDesc;
            dayStr = day + "";
            if (dayStr.length() < 2) {
                dayStr = "0" + dayStr;
            }

            monthStr = mMonth + "";
            if (monthStr.length() < 2) {
                monthStr = "0" + monthStr;
            }

            timeStamp =  mTimeStamp;
        }


        Record(int mMonth, int mDay, String monthDesc, String dayDesc, boolean mIsMonthDesc) {
            month = mMonth;
            day = mDay;
            isMonthDesc = mIsMonthDesc;
            monthDesc84Chinese = monthDesc;
            dayDesc14Chinese = dayDesc;
            dayStr = day + "";
            if (dayStr.length() < 2) {
                dayStr = "0" + dayStr;
            }

            monthStr = mMonth + "";
            if (monthStr.length() < 2) {
                monthStr = "0" + monthStr;
            }

        }

    }


    static int calculXinqiYiForMonthBegin(int year, int month) {
        //  计算月初 是星期几

        // 这个月  已过了 多少百分比
        Calendar beginMonthCalendar = Calendar.getInstance();

//        beginMonthCalendar.set(beginMonthCalendar.DAY_OF_WEEK, beginMonthCalendar.MONDAY);
        beginMonthCalendar.set(Calendar.YEAR, year); // 设置成年
        beginMonthCalendar.set(Calendar.MONTH, month - 1); // 设置成年
        beginMonthCalendar.set(Calendar.DAY_OF_MONTH, 1); // 设置成月初
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY, 0);
        beginMonthCalendar.set(Calendar.HOUR, 0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MINUTE, 0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.SECOND, 0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.MILLISECOND, 0);  //  时分秒 毫秒 都设置0
        beginMonthCalendar.set(Calendar.HOUR_OF_DAY, 0);

        //0-周天  1-周一    6-周六
        int day = beginMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (day == 0) {   // 周0转为 周七  以中国人的方式显示
            day = 7;
        }
        return day;
    }


    static int calculMonthDayCount(int year, int month) {
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
        }
        return days;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);

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

    static String getPaddingString(String originStr, int padinglength, String oneStr, boolean dirPre) {
        String result = "" + originStr;
//        int length = ("" + originStr).length();
        int length = getPaddingChineseLength("" + originStr);

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

}
