
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


//
public class I4_ClipBoard_Monitor_PathTextProp  implements ClipboardOwner{


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 I4 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
    static String Cur_Bat_Name = "zclip_monitor_filepath_prop_I4";
    static int CUR_TYPE_INDEX = 1;   // 当前用户选中的 操作的类型  0-默认标识没有选中 1-标识选中默认规则1

/*******************修改属性列表 ------End *********************/



    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File I4_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+".properties");
    static InputStream I4_Properties_InputStream;
    static OutputStream I4_Properties_OutputStream;
    static Properties I4_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    static File I4_BEACH_MOVE_BAT = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "I4_beach_move"+".bat");
    static File I4_BEACH_MOVE_SH = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "I4_beach_move"+".sh");


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE ;   // 当前 CMDER的路径 File 文件


    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出



    static Rule CUR_Selected_Rule ;    // 当前默认选中的 操作规则 这里实现了具体的操作逻辑

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_3_ParamStrList = new ArrayList<>();


    // 固定7  当前保存 Rule的集合
    static ArrayList<Rule> CUR_RULE_LIST = new ArrayList<Rule>();  // 规则的集合



    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static  HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new  HashMap<String, ArrayList<File>>(); ;





//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";


    // PATH 环境变量值进行当前的保存处理
    static String EnvironmentValue=System.getProperties().getProperty("java.library.path");
    static String PathSeparator = System.getProperties().getProperty("path.separator");
    static String[] EnvironmentList = EnvironmentValue.split(PathSeparator);


    static boolean  isContainEnvironment(String program){
        boolean environmentExist = false;
        if(EnvironmentValue.contains(program)){
            environmentExist = true;
        }
        return environmentExist;
    }



    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\I4_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\I4_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
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
            if (!I4_Properties_File.exists()) {
                I4_Properties_File.createNewFile();
            }
            I4_Properties_InputStream = new BufferedInputStream(new FileInputStream(I4_Properties_File.getAbsolutePath()));
            I4_Properties.load(I4_Properties_InputStream);
            Iterator<String> it = I4_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + I4_Properties.getProperty(key));
                propKey2ValueList.put(key, I4_Properties.getProperty(key));
            }
            I4_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   static void initPropMap(){
       propFileListSize = 0;
        try {
            if (!I4_Properties_File.exists()) {
                I4_Properties_File.createNewFile();
            }
            I4_Properties_InputStream = new BufferedInputStream(new FileInputStream(I4_Properties_File.getAbsolutePath()));
            I4_Properties.load(I4_Properties_InputStream);
            Iterator<String> it = I4_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                File fileItem = new File(key);
                propFileListSize += fileItem.length();
                // System.out.println("key:" + key + " value: " + I4_Properties.getProperty(key));
                propKey2ValueList.put(key, I4_Properties.getProperty(key));
            }
            I4_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void setProperity() {
        try {
            I4_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(I4_Properties_File.getAbsolutePath()));
            I4_Properties.store(I4_Properties_OutputStream, "");
            I4_Properties_OutputStream.close();
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



    void InitRule(){

        //   加入类型一一对应的 那些 规则

        CUR_RULE_LIST.add( new MergeMP4_Rule_1());
//        CUR_RULE_LIST.add( new File_Name_Rule_2());
//        CUR_RULE_LIST.add( new Image2Jpeg_Rule_3());
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

    }






    class MergeMP4_Rule_1 extends  Basic_Rule{
        ArrayList<File> curInputFileList ;


        MergeMP4_Rule_1(){
            super(1);
            curInputFileList = new  ArrayList<File>();
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            ArrayList<File> curFliterList = new ArrayList<File>();

            if(otherParams == null || otherParams.size() ==0){
                errorMsg = "用户输入的文件参数为空";
                return false;
            }

            for (int i = 0; i <otherParams.size() ; i++) {
                String pre = "."+File.separator;
                String curStringItem = otherParams.get(i).toString();
                String curAbsPath = "";
                if(curStringItem.startsWith(pre)){
                    curStringItem = curStringItem.substring(2);
                }
                curAbsPath = shellDir.getAbsolutePath() + File.separator + curStringItem;
                File curFIle = new File(curAbsPath) ;
                if(curFIle.exists()){
                    curFliterList.add(curFIle);
                }
            }






            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }


    }




    class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex,int opera_type){
            this.file_type = ctype;
            this.rule_index = cindex;
            this.operation_type = opera_type;
            this.identify = this.file_type+""+this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
        }

        Basic_Rule(int ruleIndex){
            this.rule_index = ruleIndex;
            this.file_type = "";
            this.operation_type = 0;
            this.identify = this.file_type+""+this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
        }

        String applyStringOperationRule1(String origin){
            return origin;
        }

        File applyFileByteOperationRule2(File originFile){
            return originFile;
        }

        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap ){
            return subFileList;
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return curFileList;
        }


        void initParams4InputParam(String inputParam){}

        String simpleDesc(){
            return null;
        }

        void operationRule(ArrayList<String> inputParamsList){}


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return true;  // 默认返回通过   不检查参数   如果有检查的需求 那么就实现它
        }

        @Override
        void showWrongMessage() {
            System.out.println("当前 type 索引 "+rule_index +" 执行错误  可能是输入参数错误 请检查输入参数!");
            System.out.println("ErrorMsg:"+ errorMsg);
        }

        String ruleTip(String type,int index , String batName,OS_TYPE curType){
            String itemDesc = "";
            if(curType == OS_TYPE.Windows){
                itemDesc = batName.trim()+"  "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
            }else{
                itemDesc = batName.trim()+" "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
            }

            return itemDesc;
        }

        boolean tryReName(File curFile , String newName){
            String newFilePath = curFile.getParent() + File.separator + newName;
            String oldName = curFile.getName();
            File newFile = new File(newFilePath);
            if(newFile.exists() && newFilePath.equals(curFile.getAbsolutePath()) ){

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
                System.out.println("当前目录已存在重命名后的文件  文件名称:"+ curFile.getName());
                return false;    // 已经存在的文件不处理 直接返回

            }
            boolean flag =   curFile.renameTo(newFile);
            if(flag){
                System.out.println(oldName+" 转为 "+ newFilePath +" 成功！");
                curFixedFileList.add(curFile);
            }else{
                System.out.println(oldName+" 转为 "+ newFilePath +" 失败！");
            }
            return flag;
        }
    }

    abstract  class Rule{

        int rule_index;   //  rule_index  组成了最基础的唯一键 rule_index 就是唯一的序号  1 2 3 4 5 6 7

        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作

        int operation_type;  // 默认为0
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        String errorMsg;
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        ArrayList<File> inputFileList ;  // 从输入参数得到的文件的集合
        abstract    void operationRule(ArrayList<String> inputParamsList);
        abstract    String applyStringOperationRule1(String origin);
        abstract    File applyFileByteOperationRule2(File originFile);
        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
        abstract    ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList , HashMap<String, ArrayList<File>> subFileTypeMap , ArrayList<File> curDirList ,ArrayList<File> curRealFileList);
        abstract    void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串
        abstract    String ruleTip(String type,int index , String batName,OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况
        abstract    String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用
        abstract    boolean checkParamsOK(File shellDir,String type2Param,ArrayList<String> otherParams);
        abstract    void showWrongMessage();
    }

    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
//                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

                BufferedWriter curBW = null;
                if(isContainChinese(strParam)){
                     curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));

                }else{
                     curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                }

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


    static  String  getPaddingIntString(int index , int padinglength , String oneStr , boolean dirPre){
        String result = ""+index;
        int length = (""+index).length();
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

        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");

        System.out.println("系统的剪切板进行监听 对那些满足 条件的 video文件 保存 收藏路径到 Prop文件中 等待 bat 或者 sh 文件处理 ");

        System.out.println(" "+ Cur_Bat_Name);
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");
        showMapPathInfo();



    }




    static void showTip() {
        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");

        System.out.println("系统的剪切板进行监听 对那些满足 条件的 video文件 保存 收藏路径到 Prop文件中 等待 bat 或者 sh 文件处理 ");

        System.out.println(" "+ Cur_Bat_Name);
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");
        showMapPathInfo();
    }



    @SuppressWarnings("unchecked")
    public static void showMapPathInfoWhenBegin(){
        propFileListSize = 0;
        System.out.println("══════"+"当前Prop已保存操作集合 Begin"+"══════");
        Map.Entry<String , String> entry;
        StringBuilder sb=new StringBuilder();
        if(propKey2ValueList != null){
            Iterator iterator = propKey2ValueList.entrySet().iterator();

            int index = 0 ;
            while( iterator.hasNext() ){
                entry = (Map.Entry<String , String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                File fileItem = new File(key);
                propFileListSize += fileItem.length();
                String value = entry.getValue();  //Map的Value
                System.out.println("index["+index+"] = "+"move  "+key+" "+value);
                index ++;
            }
        }
        System.out.println("══════"+"当前Prop已保存操作集合 End"+"══════");

    }



    @SuppressWarnings("unchecked")
    public static void showMapPathInfo(){
        Map.Entry<String , String> entry;
        StringBuilder sb=new StringBuilder();


        if(propKey2ValueList != null){
            Iterator iterator = propKey2ValueList.entrySet().iterator();

            if(CUR_OS_TYPE == OS_TYPE.Windows){//bat 中 要使用 /r/n
                sb.append("@echo off\r\n");
                sb.append("Setlocal ENABLEDELAYEDEXPANSION"+"\r\n");
            }

            int index = 0 ;
            while( iterator.hasNext() ){
                entry = (Map.Entry<String , String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                System.out.println("index["+index+"] = "+"move  "+key+" "+value);
                if(CUR_OS_TYPE == OS_TYPE.Windows){
                    String key_value_str = key+value;
                    sb.append("echo \"index = "+index +"  ---> "+key+"\""+"\r\n");
                    if(isContainChinese(key_value_str)){
                        sb.append("move  "+"\""+key+"\"    "+"\""+value+"\""+"\r\n");
                    }else{
                        sb.append("move  "+key+"   "+value+"\r\n");
                    }
                    sb.append("echo. "+"\r\n");
                }
                index++;

            }
        }
        if(CUR_OS_TYPE == OS_TYPE.Windows) {
            writeContentToFile(I4_BEACH_MOVE_BAT, sb.toString());
            System.out.println("════════ 请执行以下命令 以完成 文件 剪切操作!════════");
            System.out.println(I4_BEACH_MOVE_BAT.getAbsolutePath()  + "         ## ★ 开始剪切文件 批操作 处理开始");
            System.out.println();
            System.out.println(Cur_Bat_Name+"  clear"   +"              ## ★ 该命令清除当前已保存的所有 Prop操作集合 ！");
            System.out.println();
            System.out.println(Cur_Bat_Name+" "   +"                    ## ★ 重新执行监听 系统剪切板 fuction  ！");
            System.out.println();
            System.out.println(I4_BEACH_MOVE_BAT.getAbsolutePath()  + "         ## ★ 开始剪切文件 批操作 处理开始");
            System.out.println("════════════════════════════════");
        }

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



    static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    static JFrame jframe = null;

    I4_ClipBoard_Monitor_PathTextProp(){
        //如果剪贴板中有文本，则将它的ClipboardOwner设为自己
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
            clipboard.setContents(clipboard.getContents(null), this);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // 如果不暂停一下，经常会抛出IllegalStateException
        // 猜测是操作系统正在使用系统剪切板，故暂时无法访问
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 取出文本并进行一次文本处理
        String text = null;
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
            try {
                text = (String)clipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        String clearedText = Text.handle(text); // 自定义的处理方法
//        // 存入剪贴板，并注册自己为所有者
//        // 用以监控下一次剪贴板内容变化
//        StringSelection tmp = new StringSelection(clearedText);
//        clipboard.setContents(tmp, this);
        Operation(text);
        I4_ClipBoard_Monitor_PathTextProp temp = new I4_ClipBoard_Monitor_PathTextProp();
        new JFrame().setVisible(true); // 软件窗口
        setProperity();
    }

    static long propFileListSize = 0L;

    /************
     * 测试代码 *
     * **********
     */
    public static void main(String[] args) {
        initSystemInfo();
        showMapPathInfoWhenBegin();
        if (args != null) {

            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                }
                if(args[i].toLowerCase().contains("clear") ){
                    clearAllProp();
                    initPropMap();
//                    showMapPathInfoWhenBegin();
                    System.out.println("══════当前Prop已保存操作集合 Begin══════");
                    System.out.println("══════当前Prop已保存操作集合 End══════");


                    System.out.println("已清空 原有数据  请重新执行 "+ Cur_Bat_Name + " 重新加载剪切板数据!");
                    return;
                }
            }
        }


        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);

        if(!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()){
            System.out.println("当前shell-dir路径为空 无法执行程序!");
            return;
        }
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

        I4_ClipBoard_Monitor_PathTextProp temp = new I4_ClipBoard_Monitor_PathTextProp();
        if(jframe != null){
            jframe.setVisible(true); // 软件窗口
        }else{
            jframe = new JFrame();
            jframe.setVisible(true); // 软件窗口
        }
        System.out.println("════════ 开始监听系统剪切板 Video文件路径 收藏到 Prop文件中 ════════");
        registerShutDownLister();
    }

    /**********************************************
     * 如果剪贴板的内容改变，则系统自动调用此方法 *
     **********************************************
     */



    static void  clearAllProp(){

        I4_Properties.clear();
        setProperity();
        writeContentToFile(I4_BEACH_MOVE_BAT,"");
        System.out.println("当前已经对 Prop 操作集合 执行 clear 情况命令 !");
    }


    static void registerShutDownLister(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    System.out.println("════════ shouting down info as below ════════");
                    System.out.println("════════"+"清除当前 pro记录操作如下"+"════════");
                    System.out.println();
                    System.out.println(I4_BEACH_MOVE_BAT.getAbsolutePath()  + "       ## ★ 开始剪切文件 批操作 处理开始");
                    System.out.println();
                    System.out.println(Cur_Bat_Name+"  clear"   +"   ## ★ 该命令清除当前已保存的所有 Prop操作集合 ！");
                    System.out.println();
                    System.out.println(Cur_Bat_Name+" "   +"   ## ★ 重新执行监听 系统剪切板 fuction  ！");
                    System.out.println();
                    System.out.println("════════"+"════════");
                    //some cleaning up code...
                    initPropMap();
                    showMapPathInfo();
                    setProperity();


                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }
static String[] operationTip = {"⊙","▲","〓","※","¤","☆","★","●","◎","◆"};
    static int operationIndex = 0;
    static void Operation(String clipText){
        System.out.println();
        System.out.println();
        operationIndex++;
        int curPropSIze = I4_Properties.size();
        String diffTipStr = operationTip[operationIndex%10];
        System.out.println(diffTipStr+"════════"+clipText+"Begin════════");
        System.out.println(diffTipStr+" time = "+getTimeStamp()  +"   当前保存文件个数:"+ curPropSIze  + "  总文件大小:"+getFileSize(propFileListSize));
//        System.out.println("clip_text = "+ clipText);

        File originMP4File = new File(clipText);
        if(!originMP4File.exists()){
            System.out.println(diffTipStr+"【1】 当前剪切板文件 不存在! ");
            System.out.println(diffTipStr+"════════"+clipText+" End Failed════════");
            return;
        }
        System.out.println(diffTipStr+"【1】 当前剪切板文件存在!  Path -> "+ originMP4File.getAbsolutePath());

        if(!isVideoFile(originMP4File)){
            System.out.println(diffTipStr+"【2】 当前剪切板文件存在,但不是MP4文件  Path -> "+ originMP4File.getAbsolutePath());
            System.out.println(diffTipStr+"════════"+clipText+" End Failed════════");
            return;
        }
        System.out.println(diffTipStr+"【2】 当前剪切板文件存在! 并且是MP4文件!  Path -> "+ originMP4File.getAbsolutePath());


        long fileSize = originMP4File.length();

        if(fileSize < 1024L*100){  // 小于 100K
            System.out.println(diffTipStr+"【3】 MP4文件文件过小 小于1MB (不屑于保存)!  FileSize -> "+ getFileSize(originMP4File));
            System.out.println(diffTipStr+"════════"+clipText+" End Failed════════");
            return;
        }
        System.out.println(diffTipStr+"【3】 MP4文件文件小于正常 (能复制)!  FileSize -> "+ getFileSize(originMP4File));


        String fileName = originMP4File.getName();
        File newShellFile = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+fileName);

        if  (newShellFile.exists() ){
//            System.out.println("【4】 当前目标shell目录已存在同名文件 并有空间大小 所以不执行复制操作！  shellFileSize = "+getFileSize(newShellFile));
//            System.out.println("════════"+clipText+" End Failed════════");
            newShellFile = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+getTimeStamp()+fileName);
        }
//        System.out.println("【4】 MP4文件文件小于100MB  正常 (能复制)!  FileSize -> "+ getFileSize(originMP4File));

     if(I4_Properties.keySet().contains(originMP4File.getAbsolutePath())){
         System.out.println(diffTipStr+"【4】 当前 Prop 已存储了当前文件路径 再加入失败  Path -> "+ originMP4File.getAbsolutePath());
        System.out.println(diffTipStr+"════════"+clipText+" End Failed════════");
         return;
     }
        System.out.println(diffTipStr+"【4】 当前 Prop 没有该文件路径 加入成功  Path -> "+ originMP4File.getAbsolutePath());


        System.out.println(diffTipStr+"【5】 把需要转移的文件完整路径 保存到 Prop文件中  目标文件为当前 shell目录! ");
        System.out.println(diffTipStr+" 原始文件路径: "+ originMP4File.getAbsolutePath());
        System.out.println(diffTipStr+" 目标文件路径: "+ newShellFile.getAbsolutePath());

        I4_Properties.put(originMP4File.getAbsolutePath(),newShellFile.getAbsolutePath());
        propFileListSize+= originMP4File.length();
        setProperity();
//        fileCopy(originMP4File,newShellFile);
        int curPropSIze2 = I4_Properties.size();
        String allSizeStr  = getFileSize(propFileListSize);
        System.out.println(diffTipStr+"【索引: "+curPropSIze+"】"+"单文件大小● "+getJustFileSize(originMP4File)+"●" +" 当前个数〓"+curPropSIze2+"〓"+"  当前总大小▲ "+allSizeStr+"▲"+"═══"+clipText+" End Success════════");
    }

    static boolean isVideoFile(File file){
        boolean isVideoFlag = false;
        String fileName = file.getName().toLowerCase();
        if(fileName.endsWith(".mp4") || fileName.endsWith(".flv") || fileName.endsWith(".rmvb")
        ||   fileName.endsWith(".mkv")   ||   fileName.endsWith(".3gp")  ){
            isVideoFlag = true;

        }

        return isVideoFlag;
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
            System.out.println("【6】 复制成功!!");
        } catch (Exception e) {
            System.out.println("【5】 复制失败!");
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

    static String getFileSize(long fileSize){
        String sizeStr = "";
        if(fileSize > 1024L &&  fileSize <= 1024L*1024L ){
            sizeStr = fileSize/ (1024L) + "KB ";
        } else if(fileSize > 1024L*1024L &&  fileSize <= 1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L) + "MB ";
        }else if(fileSize > 1024L*1024L*1024L &&  fileSize <= 1024L*1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L*1024L) + "GB ";
        }
        if("".endsWith(sizeStr)){
            sizeStr = fileSize+ "B";
        }
        return  sizeStr;
    }

    static String getFileSize(File file){
        long fileSize = file.length();
        String name = file.getName();
        String sizeStr = "";
        if(fileSize > 1024L &&  fileSize <= 1024L*1024L ){
            sizeStr = fileSize/ (1024L) + "KB ";
        } else if(fileSize > 1024L*1024L &&  fileSize <= 1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L) + "MB ";
        }else if(fileSize > 1024L*1024L*1024L &&  fileSize <= 1024L*1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L*1024L) + "GB ";
        }
        if("".endsWith(sizeStr)){
            sizeStr = fileSize+ "B";
        }
        return name+ " "+ sizeStr;
    }

    static String getJustFileSize(File file){
        long fileSize = file.length();
        String name = file.getName();
        String sizeStr = "";
        if(fileSize > 1024L &&  fileSize <= 1024L*1024L ){
            sizeStr = fileSize/ (1024L) + "KB ";
        } else if(fileSize > 1024L*1024L &&  fileSize <= 1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L) + "MB ";
        }else if(fileSize > 1024L*1024L*1024L &&  fileSize <= 1024L*1024L*1024L*1024L){
            sizeStr = fileSize/ (1024L*1024L*1024L) + "GB ";
        }
        if("".endsWith(sizeStr)){
            sizeStr = fileSize+ "B";
        }
        return  sizeStr;
    }

    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

    public static void main2(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if(i == 1){  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];
                    //zukgit1    计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    CUR_TYPE_INDEX = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                }else {
                    CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new  File(CUR_Dir_1_PATH);

        I4_ClipBoard_Monitor_PathTextProp mI4_Object = new I4_ClipBoard_Monitor_PathTextProp();
        mI4_Object.InitRule();


        // 用户没有输入参数
        if (CUR_TYPE_INDEX == 0 && CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }



        if(CUR_TYPE_INDEX == 0){
            showNoTypeTip();
            return;
        }




        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则


        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null  ||  !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE,CUR_TYPE_2_ParamsStr,CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }



/*
        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }
*/



        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH+"  不存在! ");
            return;
        }



        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理



        setProperity();
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

    static String OriApplyOperationRule(String mType ,String index  , String mOriContent){
        String identy = mType.trim()+index.trim();
        Rule applayRule = getRuleByIdentify(identy);
        if(applayRule == null){
            System.out.println("没有查询到 identy ="+ identy+"对应的处理规则");
            return mOriContent;
        }
        return  applayRule.applyStringOperationRule1(mOriContent);
    }




    static Rule getRuleByIndex(int index){
        for (int i = 0; i <CUR_RULE_LIST.size() ; i++) {
            if(CUR_RULE_LIST.get(i).rule_index == index){
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }



    static  Rule getRuleByIdentify(String identify){
        for (int i = 0; i <CUR_RULE_LIST.size() ; i++) {
            if(CUR_RULE_LIST.get(i).identify.equals(identify)){
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }
}
