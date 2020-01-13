

import java.io.*;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// 对于  文件类型_操作Index  执行对应的操作逻辑
public class G2_ApplyRuleFor_TypeFile {

    //  类型_索引  ，对当前类型的文件执行索引执行的操作     html1---对html类型的子文件执行 索引为1 的逻辑操作  String apply(String)
    static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();


    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();


    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }


    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        for (int i = 0; i < realTypeRuleList.size() ; i++) {
            Rule itemRule = realTypeRuleList.get(i);
           String type =  itemRule.file_type;
           int index =  itemRule.rule_index;
           String desc =  itemRule.ruleTip();
            String itemDesc = "";
           if(curOS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_G2.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G2 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           System.out.println(itemDesc);
            count++;
        }
    }


    static ArrayList<File> getAllSubFile(File dirFile ,String aospPath , String type) {
        if(aospPath == null || "".equals(aospPath)){
            return getAllSubFile(dirFile.getAbsolutePath(), "", type);
        }
        return getAllSubFile(dirFile.getAbsolutePath(), aospPath, type);

    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath, String type) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);
                    if (fileString.endsWith(type)) {
                    allFile.add(new File(fileString));
//                         System.out.println("file found at path: " + file.toAbsolutePath());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }



  static boolean  checkInputParamsOK(){
        boolean inputOk = true;

      for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {
          String curInputStr = Rule_Identify_TypeIndexList.get(i);
          if(!curInputStr.contains("_")){
              return false;
          }

         String index =  curInputStr.split("_")[1];
          if(!isNumeric(index)){  //  第二个参数不是 数字 那么 输入格式错误
              return false;
          }

      }

        return inputOk;
  }

    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {
                    curDirPath = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        G2_ApplyRuleFor_TypeFile  mG2_Object = new G2_ApplyRuleFor_TypeFile();
        mG2_Object.InitRule();

        if (mKeyWordName.size() == 0) {
            showTip();
            return;
        }

        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }


        File mCurDirFile = new File(curDirPath);
        if (!mCurDirFile.exists() || !mCurDirFile.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + curDirPath+"  不存在! ");
            return;
        }



        //  通过  shell中输入参数来进行操作
        //  Rule_Identify_TypeIndexList.add("html_1");  //  1.添加处理的类型文件  类型_该类型的处理逻辑索引      索引从1开始


        for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {  //  依据文件类型 去找到文件
          // html_1
            String applyRuleString = Rule_Identify_TypeIndexList.get(i);
            String curType = applyRuleString.split("_")[0];
            String curApplyRule =  applyRuleString.split("_")[1];

            ArrayList<File>  typeFileList =  getAllSubFile(mCurDirFile,null,curType);
            if(typeFileList.size() == 0){
                System.out.println("未能搜索到类型列表匹配的文件:  "+ Rule_Identify_TypeIndexList.get(i));
                continue;
            }

            for (int j = 0; j < typeFileList.size(); j++) {
                File itemFile =  typeFileList.get(j);
                String fileCOntent =  ReadFileContent(itemFile).trim();
                // 2.applyOperationRule  添加处理规则


                String resultStr = OriApplyOperationRule(curType,curApplyRule,fileCOntent).trim();

                int currentOperationType = 1;  // 默认操作类型是 读取字符串的内容 进行处理

                String identy = curType.trim()+curApplyRule.trim();
                Rule applayRule = getRuleByIdentify(identy);


                if(applayRule != null){
                    currentOperationType =  applayRule.operation_type;
                }else{
                    System.out.println("无法匹配到 对应的 identy="+ identy +"  对应的规则 Rule !   可能需要代码添加。");
                    return;
                }

                if(currentOperationType == 1){    // 对字符串进行逻辑处理的类型

                    if(!fileCOntent.equals(resultStr)){
                        writeContentToFile(itemFile,resultStr);
                        System.out.println("itemFile["+j+"] 符合规则(String-Content) 应用Rule成功 " + applyRuleString+ "  = " + itemFile.getAbsolutePath() );
                    }else{
                        System.out.println("itemFile["+j+"] 不符合规则(String-Content) = " + itemFile.getAbsolutePath() );
                    }

                }else if(currentOperationType == 2){

                   File resultFile = applayRule.applyFileByteOperationRule(itemFile);

                    if(resultFile != itemFile ){
                        System.out.println("itemFile["+j+"] 符合规则(File) 应用Rule成功 " + applyRuleString+ "  = " + itemFile.getAbsolutePath() );
                    }else{
                        System.out.println("itemFile["+j+"] 不符合规则(File) = " + itemFile.getAbsolutePath() );
                    }


                }

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
    return  applayRule.applyStringOperationRule(mOriContent);
 }

static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>();  // 规则的集合


  static  Rule getRuleByIdentify(String identify){
        for (int i = 0; i <realTypeRuleList.size() ; i++) {
            if(realTypeRuleList.get(i).identify.equals(identify)){
                return realTypeRuleList.get(i);
            }
        }
        return null;
    }


   void InitRule(){

       realTypeRuleList.add( new HTML_Rule_1());
    }





 // 指定什么类型的文件在当前使用什么样的规则

    // html1_
/*    1.读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面
 <script>
</script>
</body>
*/

    class HTML_Rule_1 extends Basic_Rule{

         HTML_Rule_1(){
             super("html",1,1);
         }
         String applyOperationRule(String origin){
            StringBuilder sb = new StringBuilder();
            if(origin.contains("<script>") &&
                    origin.contains("</script>") &&
                    origin.contains("</body>") &&
                    origin.indexOf("</body>") >  origin.indexOf("<script>") &&   // <script> </body>  // script 索引小于 </body>的索引
                    origin.indexOf("</script>") == origin.lastIndexOf("</script>")){  // 只包含一个  </script>
                int scriptBegin = origin.indexOf("<script>");
                int scriptEnd = origin.indexOf("</script>") + "</script>".length();
                int bodyEnd = origin.indexOf("</body>");

                String script = origin.substring(scriptBegin,scriptEnd);
                String result = origin.replace(script,"");
                result = result.replace("</body>","</body>"+"\n"+script);

                sb.append(result);
            }else{
                sb.append(origin);
            }
            return sb.toString();

        }

        String ruleTip(){
             return " 读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面";
        }

    }







     class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex,int opera_type){
            this.file_type = ctype;
            this.rule_index = cindex;
            this.operation_type = opera_type;
            this.identify = this.file_type+""+this.rule_index;
        }
        String applyStringOperationRule(String origin){
            return origin;
        }

         File applyFileByteOperationRule(File originFile){
             return originFile;
         }


         String ruleTip(){
            return null;
         }
    }

abstract  class Rule{
    // 操作类型     1--读取文件内容字符串 进行修改      2--对文件属性进行修改(文件名称)  对文件内容(字节)--进行修改
         int operation_type;
         String file_type;   // * 标识所有的文件类型
         String identify;
         int rule_index;   //  (type,index)   组成了最基础的唯一键
     abstract    String applyStringOperationRule(String origin);
     abstract    File applyFileByteOperationRule(File originFile);
     abstract   String ruleTip();
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



}
