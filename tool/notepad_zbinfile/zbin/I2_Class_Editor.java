
import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Manifest  中   Main-Class: com.dwipal.DwSnmpMibTreeGUI

// javac   -cp ./;./lib/log4j-1.2.9.jar;./lib/SNMP4J.jar; .\src\com\dwipal\*.java
// // jar cvf zukgit.jar    .\src\com\dwipal\*.class
// jar cvfm zukgit.jar   Manifest .\src\com\dwipal\*.class

//   jar cvfm I1_jmibbrowser.jar   Manifest.MF  .\com\dwipal\*.class          //  需要在 src 下执行 jar
//   jar cvfm I1_jmibbrowser.jar   MANIFEST.MF  .\com\dwipal\*.class
// java -jar    I1_jmibbrowser.jar      //   I1_jmibbrowser.jar 中没有主清单属性
//  主动修改 .jar 文件中的 /META-INF/MANIFEST.MF   加入 Main-Class: com.dwipal.DwSnmpMibTreeGUI 奇怪

//  MANIFEST.MF



public class I2_Class_Editor {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 I1 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
// 修改3.  当前是否有默认的规则   如果有默认的规则那么设置 CUR_TYPE_INDEX为对应index , 没有默认规则那么设置为默认的1
    static String Cur_Bat_Name = "zclass_editor_I1";
  static int Default_Type_Index = 1;
/*******************修改属性列表 ------End *********************/



    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static int CUR_TYPE_INDEX = Default_Type_Index;   // 当前用户选中的 操作的类型  0-默认标识没有选中 1-标识选中默认规则1


    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File I1_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name)+".properties");
    static InputStream I1_Properties_InputStream;
    static OutputStream I1_Properties_OutputStream;
    static Properties I1_Properties = new Properties();
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



    // ffmpeg  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\I1_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output2.mp4
    // D:\software\ffmpeg\bin
    // D:\software\ffmpeg\bin\ffmpeg.exe  -f concat -safe 0 -i C:\Users\zhuzj5\Desktop\zbin\I1_1_MergedRule.txt -c copy C:\Users\zhuzj5\Desktop\output3.mp4
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
            if (!I1_Properties_File.exists()) {
                I1_Properties_File.createNewFile();
            }
            I1_Properties_InputStream = new BufferedInputStream(new FileInputStream(I1_Properties_File.getAbsolutePath()));
            I1_Properties.load(I1_Properties_InputStream);
            Iterator<String> it = I1_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + I1_Properties.getProperty(key));
                propKey2ValueList.put(key, I1_Properties.getProperty(key));
            }
            I1_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            I1_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(I1_Properties_File.getAbsolutePath()));
            I1_Properties.store(I1_Properties_OutputStream, "");
            I1_Properties_OutputStream.close();
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

        CUR_RULE_LIST.add( new ClassEditor_Rule_1());
//        CUR_RULE_LIST.add( new File_Name_Rule_2());
//        CUR_RULE_LIST.add( new Image2Jpeg_Rule_3());
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

    }






    class ClassEditor_Rule_1 extends  Basic_Rule{
        ArrayList<File> curInputFileList ;
        String className;     // 编辑的 class 文件名称
        File classFile;      // 编辑的class文件的 FIle 对象
        int editLineNum;   // 编辑的行数
        String inputReplaceStr;   // 输入的替换规则   使用_分割   原字符串_后字符串
        String originStr;   //  原始字符串
        String replaceStr;  // 上场的字符串
        File newFile;



        ClassEditor_Rule_1(){
            super(1);
            curInputFileList = new  ArrayList<File>();
        }

        @Override
        void operationRule(ArrayList<String> inputParamsList) {

            try {

                FileInputStream fis = new FileInputStream(classFile.getAbsolutePath());
                DataInput di = new DataInputStream(fis);
                ClassFile cf = new ClassFile();
                cf.read(di);
                CPInfo[] infos = cf.getConstantPool();

                int count = infos.length;
                for (int i = 0; i < count; i++) {
                    if (infos[i] != null) {
                        System.out.print(i);
//                        System.out.print(" = ");
                        System.out.print(infos[i].getVerbose());
//                        System.out.print(" = ");
                        System.out.println(infos[i].getTagVerbose());
  if (i == editLineNum) {//刚刚找到的是21位置
      System.out.println("════════"+"修改点 Begin"+"════════");

                            ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[i]; //刚刚那里是CONSTANT_Utf-8_info所以这里要用这个
                            replaceStr = StringEscape.unescapeJava(replaceStr);
                            uInfo.setBytes(replaceStr.getBytes());
                            infos[i] = uInfo;

                            System.out.print(i);
                            System.out.print(" = ");
                            System.out.print(infos[i].getVerbose());
                            System.out.print(" = ");
                            System.out.println(infos[i].getTagVerbose());
      System.out.println("════════"+"修改点 End"+"════════");

  }

                    }
                }
                //这种方式也可以，一样的
/*		if(infos[count] != null) {
			ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[i]; //刚刚那里是CONSTANT_Utf-8_info所以这里要用这个
			uInfo.setBytes("baidu".getBytes());
			infos[count] = uInfo;
		}*/

                cf.setConstantPool(infos);
                fis.close();

                ClassFileWriter.writeToFile(newFile, cf);



            }catch (Exception e){
                System.out.println("异常 = "+ e.toString());
            }



            super.operationRule(inputParamsList);
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {

            String mClassName = getInputClassName(otherParams);
            int mLineNum = getInputLineNum(otherParams);
            String mRuleStr = getInputReplaceRule(otherParams);
            String firstStr = mRuleStr.substring(0,mRuleStr.indexOf("_"));
            String secondStr = mRuleStr.substring(mRuleStr.indexOf("_")+1);

            className = mClassName;
            editLineNum = mLineNum;
            inputReplaceStr = mRuleStr;
            originStr = firstStr;
            replaceStr =  StringEscape.unescapeJava(secondStr);
            classFile = new File(CUR_Dir_FILE.getAbsolutePath()+File.separator+className);

            System.out.println("1.className = "+ mClassName);
            System.out.println("1.1 classFile = "+ classFile.getAbsolutePath());
            System.out.println("2.editLineNum = "+ editLineNum);
            System.out.println("3.replaceStr = "+ replaceStr);
            System.out.println("3.1 originStr = "+ originStr);
            System.out.println("3.2 inputReplaceStr = "+ inputReplaceStr);

            if(!classFile.exists()){
                System.out.println("当前输入 class 文件路径不存在 请排查输入!  className =" + className );
                System.out.println("classFile = "+ classFile.getAbsolutePath());
                return false;
            }

            if(editLineNum == 0){
                System.out.println("当前 class 修改字符串的行数为 默认的 0  请重新输入!  editLineNum ="+ editLineNum);
                return false;
            }


            if(replaceStr == null || "".equals(replaceStr)){
                System.out.println("当前 class 修改字符串规则为空 请检查输入  replaceStr ="+ replaceStr);
                return false;
            }

            String className_NoPoint =getFileNameNoPoint(classFile.getName());

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timeStamp = df.format(new Date());
            String newFileName = className_NoPoint+"_"+timeStamp+".class";
            newFile = new File(classFile.getParentFile().getAbsolutePath()+File.separator+newFileName);


            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }

//        shell 当前路径  DwSnmpMibTreeGUI.class   430  /mibs/_/I1_mibs/
     String    getInputClassName( ArrayList<String>  params){
            String className = null;
         for (int i = 0; i < params.size(); i++) {
             String  paramItem = params.get(i);
             if(paramItem.endsWith(".class")){
                 className = paramItem;
                 return className;
             }
         }
            return className;
        }


        //        shell 当前路径  DwSnmpMibTreeGUI.class   430  /mibs/_/I1_mibs/
        int    getInputLineNum( ArrayList<String>  params){
            int inputLine = 0 ;
            for (int i = 0; i < params.size(); i++) {
                String  paramItem = params.get(i);
                if(isNumeric(paramItem) && !paramItem.contains("_")){
                    inputLine = Integer.parseInt(paramItem);
                    return inputLine;
                }
            }
            return inputLine;
        }


        //        shell 当前路径  DwSnmpMibTreeGUI.class   430  /mibs/_/I1_mibs/
        String    getInputReplaceRule( ArrayList<String>  params){
            String strRule = "" ;
            for (int i = 0; i < params.size(); i++) {
                String  paramItem = params.get(i);
                if(!isNumeric(paramItem) && paramItem.contains("_")  && !paramItem.endsWith(".class")){
                    strRule = paramItem;
                    return paramItem;
                }
            }
            return strRule;
        }

        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return "\n" + Cur_Bat_Name + "  xxxxx.class【class文件】  12【bytecodeViwer中查询行数】 google_baidu【字符串goole替换为baidu】         ####  把当前指定的class文件的指定行数line 替换为指定的字符串 原字符串_目标字符串 最后生成新的带时间戳的class \n";
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

        System.out.println("当前用户输入的 操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name+" 的 第一个输入参数中的第一个int值 ");
        System.out.println("请检查输入参数后重新执行命令!");

    }
    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");
        for (int i = 0; i < CUR_RULE_LIST.size() ; i++) {
            Rule itemRule = CUR_RULE_LIST.get(i);
            String type =  itemRule.file_type;
            int index =  itemRule.rule_index;
            String desc =  itemRule.ruleTip(type , index ,Cur_Bat_Name,CUR_OS_TYPE);

/*
            String itemDesc = "";
           if(CUR_OS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_I1.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_I1 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc+"\n");
            count++;
        }
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");

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

    //   shell 当前路径  DwSnmpMibTreeGUI.class   430  /mibs/_/I1_mibs/
    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else {
                    CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new  File(CUR_Dir_1_PATH);

        I2_Class_Editor mI1_Object = new I2_Class_Editor();
        mI1_Object.InitRule();


        // 用户没有输入参数
        if (CUR_TYPE_INDEX == Default_Type_Index && CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }

        if(CUR_TYPE_INDEX == 0){
            showTip();
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

    static String getFileNameNoPoint(String fileName) {
        String name = "";
        if (fileName.contains(".")) {
            name = fileName.substring(0, fileName.lastIndexOf(".")).trim();
        } else {
            name = new String(fileName);
        }
        return name.trim();
    }


    static  class StringEscape {
        static final String[][] JAVA_CTRL_CHARS_UNESCAPE = new String[][]{{"\\b", "\b"}, {"\\n", "\n"}, {"\\t", "\t"}, {"\\f", "\f"}, {"\\r", "\r"}};
        static final String[][] JAVA_CTRL_CHARS_ESCAPE = new String[][]{{"\b", "\\b"}, {"\n", "\\n"}, {"\t", "\\t"}, {"\f", "\\f"}, {"\r", "\\r"}};
        static final StringEscape.CharSequenceTranslator ESCAPE_JAVA;
        static final StringEscape.CharSequenceTranslator UNESCAPE_JAVA;


        private StringEscape() {
        }

        public static final String escapeJava(final String input) {
            return ESCAPE_JAVA.translate(input);
        }

        public static String unescapeJava(final String input) {
            return UNESCAPE_JAVA.translate(input);
        }

        static {
            ESCAPE_JAVA = new StringEscape.AggregateTranslator(new StringEscape.CharSequenceTranslator[]{new StringEscape.LookupTranslator(new String[][]{{"\"", "\\\""}, {"\\", "\\\\"}}), new StringEscape.LookupTranslator((CharSequence[][])JAVA_CTRL_CHARS_ESCAPE.clone())});
            UNESCAPE_JAVA = new StringEscape.AggregateTranslator(new StringEscape.CharSequenceTranslator[]{new StringEscape.OctalUnescaper(), new StringEscape.UnicodeUnescaper(), new StringEscape.LookupTranslator((CharSequence[][])JAVA_CTRL_CHARS_UNESCAPE.clone()), new StringEscape.LookupTranslator(new String[][]{{"\\\\", "\\"}, {"\\\"", "\""}, {"\\'", "'"}, {"\\", ""}})});
        }

        private static class UnicodeUnescaper extends StringEscape.CharSequenceTranslator {
            private UnicodeUnescaper() {
                super();
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
                    int i;
                    for(i = 2; index + i < input.length() && input.charAt(index + i) == 'u'; ++i) {
                    }

                    if (index + i < input.length() && input.charAt(index + i) == '+') {
                        ++i;
                    }

                    if (index + i + 4 <= input.length()) {
                        CharSequence unicode = input.subSequence(index + i, index + i + 4);

                        try {
                            int value = Integer.parseInt(unicode.toString(), 16);
                            out.write((char)value);
                        } catch (NumberFormatException var7) {
                            throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, var7);
                        }

                        return i + 4;
                    } else {
                        throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
                    }
                } else {
                    return 0;
                }
            }
        }

        private static class OctalUnescaper extends StringEscape.CharSequenceTranslator {
            private OctalUnescaper() {
//            super(null);
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                int remaining = input.length() - index - 1;
                StringBuilder builder = new StringBuilder();
                if (input.charAt(index) == '\\' && remaining > 0 && this.isOctalDigit(input.charAt(index + 1))) {
                    int next = index + 1;
                    int next2 = index + 2;
                    int next3 = index + 3;
                    builder.append(input.charAt(next));
                    if (remaining > 1 && this.isOctalDigit(input.charAt(next2))) {
                        builder.append(input.charAt(next2));
                        if (remaining > 2 && this.isZeroToThree(input.charAt(next)) && this.isOctalDigit(input.charAt(next3))) {
                            builder.append(input.charAt(next3));
                        }
                    }

                    out.write(Integer.parseInt(builder.toString(), 8));
                    return 1 + builder.length();
                } else {
                    return 0;
                }
            }

            private boolean isOctalDigit(final char ch) {
                return ch >= '0' && ch <= '7';
            }

            private boolean isZeroToThree(final char ch) {
                return ch >= '0' && ch <= '3';
            }
        }

        private static class AggregateTranslator extends StringEscape.CharSequenceTranslator {
            private final StringEscape.CharSequenceTranslator[] translators;

            public AggregateTranslator(final StringEscape.CharSequenceTranslator... translators) {
//            super(null);
                this.translators = translators == null ? null : (StringEscape.CharSequenceTranslator[])translators.clone();
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                StringEscape.CharSequenceTranslator[] var4 = this.translators;
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    StringEscape.CharSequenceTranslator translator = var4[var6];
                    int consumed = translator.translate(input, index, out);
                    if (consumed != 0) {
                        return consumed;
                    }
                }

                return 0;
            }
        }

        private static class LookupTranslator extends StringEscape.CharSequenceTranslator {
            private final HashMap<String, String> lookupMap = new HashMap();
            private final HashSet<Character> prefixSet = new HashSet();
            private final int shortest;
            private final int longest;

            public LookupTranslator(final CharSequence[]... lookup) {
//            super(null);
                int _shortest = 2147483647;
                int _longest = 0;
                if (lookup != null) {
                    CharSequence[][] var4 = lookup;
                    int var5 = lookup.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        CharSequence[] seq = var4[var6];
                        this.lookupMap.put(seq[0].toString(), seq[1].toString());
                        this.prefixSet.add(seq[0].charAt(0));
                        int sz = seq[0].length();
                        if (sz < _shortest) {
                            _shortest = sz;
                        }

                        if (sz > _longest) {
                            _longest = sz;
                        }
                    }
                }

                this.shortest = _shortest;
                this.longest = _longest;
            }

            public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
                if (this.prefixSet.contains(input.charAt(index))) {
                    int max = this.longest;
                    if (index + this.longest > input.length()) {
                        max = input.length() - index;
                    }

                    for(int i = max; i >= this.shortest; --i) {
                        CharSequence subSeq = input.subSequence(index, index + i);
                        String result = (String)this.lookupMap.get(subSeq.toString());
                        if (result != null) {
                            out.write(result);
                            return i;
                        }
                    }
                }

                return 0;
            }
        }

        private abstract static class CharSequenceTranslator {
            private CharSequenceTranslator() {
            }

            public abstract int translate(CharSequence input, int index, Writer out) throws IOException;

            public final String translate(final CharSequence input) {
                if (input == null) {
                    return null;
                } else {
                    try {
                        StringWriter writer = new StringWriter(input.length() * 2);
                        this.translate(input, writer);
                        return writer.toString();
                    } catch (IOException var3) {
                        throw new RuntimeException(var3);
                    }
                }
            }

            public final void translate(final CharSequence input, final Writer out) throws IOException {
                if (out == null) {
                    throw new IllegalArgumentException("The Writer must not be null");
                } else if (input != null) {
                    int pos = 0;
                    int len = input.length();

                    while(true) {
                        while(pos < len) {
                            int consumed = this.translate(input, pos, out);
                            if (consumed == 0) {
                                char c1 = input.charAt(pos);
                                out.write(c1);
                                ++pos;
                                if (Character.isHighSurrogate(c1) && pos < len) {
                                    char c2 = input.charAt(pos);
                                    if (Character.isLowSurrogate(c2)) {
                                        out.write(c2);
                                        ++pos;
                                    }
                                }
                            } else {
                                for(int pt = 0; pt < consumed; ++pt) {
                                    pos += Character.charCount(Character.codePointAt(input, pos));
                                }
                            }
                        }

                        return;
                    }
                }
            }

            public final StringEscape.CharSequenceTranslator with(final StringEscape.CharSequenceTranslator... translators) {
                StringEscape.CharSequenceTranslator[] newArray = new StringEscape.CharSequenceTranslator[translators.length + 1];
                newArray[0] = this;
                System.arraycopy(translators, 0, newArray, 1, translators.length);
                return new StringEscape.AggregateTranslator(newArray);
            }
        }

    }
}
