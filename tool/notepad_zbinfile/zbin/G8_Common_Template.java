
import javax.crypto.Cipher;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.Key;
import java.security.Security;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//
public class G8_Common_Template {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zmpeg_ffmpeg_G8";


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


    static int CUR_TYPE_INDEX = 0;   // 当前用户选中的 操作的类型  默认0-标识没有选中
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
                itemDesc = batName.trim()+".bat  "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
            }else{
                itemDesc = batName.trim()+".sh "+type+"_"+index + "    [索引 "+index+"]  描述:"+simpleDesc();
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
                itemDesc = "zrule_apply_G8.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G8 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
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
    public static void main(String[] args) {

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

        G8_Common_Template mG8_Object = new G8_Common_Template();
        mG8_Object.InitRule();


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
