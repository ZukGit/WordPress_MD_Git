import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.*;
import java.text.Format;
import java.util.*;
import java.util.concurrent.*;

public class B8 {


    static ArrayList<ZClass> ZClassList = new  ArrayList<ZClass>();
    static String curProjectPath  = System.getProperty("user.dir");

    static String TAG = "zukgit";

   /*
   static {  
       String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/WifiSettings.java";
     //  String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
       ZAndroidAPPClass WifiEnabler_java = new ZAndroidAPPClass(ZAndroidAPPClass_WifiEnabler_java_path);
       ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
       // 多个参数  逗号前有值   逗号后为空格
	   // void showDialog(AccessPoint accessPoint, int dialogMode)
       methodList.add(new ZMethod(WifiEnabler_java,"void","showDialog","AccessPoint accessPoint, int dialogMode"));
     
	 // void onWifiStateChanged(int state)
	 methodList.add(new ZMethod(WifiEnabler_java,"void","onWifiStateChanged","int state "));

       WifiEnabler_java.addZMethod(methodList);
       ZClassList.add(WifiEnabler_java);
    }  
	*/

    static {
        String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/WifiStateMachine.java";
        //  String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
        ZAndroidFrameworkClass WifiEnabler_java = new ZAndroidFrameworkClass(ZAndroidAPPClass_WifiEnabler_java_path);
        ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
        // 多个参数  逗号前有值   逗号后为空格
// boolean setRandomMacOui()
methodList.add(new ZMethod(WifiEnabler_java,"boolean","setRandomMacOui",""));

// boolean connectToUserSelectNetwork(int netId, int uid, boolean forceReconnect) 
methodList.add(new ZMethod(WifiEnabler_java,"boolean","connectToUserSelectNetwork","int netId, int uid, boolean forceReconnect"));

// int lookupFrameworkNetworkId(int supplicantNetworkId)
 methodList.add(new ZMethod(WifiEnabler_java,"int","lookupFrameworkNetworkId","int supplicantNetworkId"));

// boolean setEnableAutoJoinWhenAssociated(boolean enabled)
methodList.add(new ZMethod(WifiEnabler_java,"boolean","setEnableAutoJoinWhenAssociated","boolean enabled"));

//  void enableVerboseLogging(int verbose)
methodList.add(new ZMethod(WifiEnabler_java,"void","enableVerboseLogging","int verbose"));

//  void setSupplicantLogLevel()
methodList.add(new ZMethod(WifiEnabler_java,"void","setSupplicantLogLevel",""));

        WifiEnabler_java.addZMethod(methodList);
        ZClassList.add(WifiEnabler_java);
    }
	
	
/*   static {  // packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java
       String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/WifiEnabler.java";
     //  String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
       ZAndroidFrameworkClass WifiEnabler_java = new ZAndroidFrameworkClass(ZAndroidAPPClass_WifiEnabler_java_path);
       ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
       // 多个参数  逗号前有值   逗号后为空格
       //boolean  onSwitchToggled(boolean isChecked , int int1 , String str1 , ArrayList<String> strArr , String[] strArr1 )
       methodList.add(new ZMethod(WifiEnabler_java,"boolean","onSwitchToggled","boolean isChecked , int int1 , String str1 , ArrayList<String> strArr  , String[] strArr1 ,   int[] arrInt ,Map<String,Object> mapValue"));

       WifiEnabler_java.addZMethod(methodList);
       ZClassList.add(WifiEnabler_java);
    }*/

/* 
    static {
        String testClassA = curProjectPath+"/B8_Test.java";
        //  String ZAndroidAPPClass_WifiEnabler_java_path = curProjectPath+"/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java";
        ZIDEClass WifiEnabler_java = new ZIDEClass(testClassA);
        ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
        // 多个参数  逗号前有值   逗号后为空格
        //boolean  onSwitchToggled(boolean isChecked , int int1 , String str1 , ArrayList<String> strArr , String[] strArr1 )
        methodList.add(new ZMethod(WifiEnabler_java,"void","method1","int value"));
        methodList.add(new ZMethod(WifiEnabler_java,"void","method2","int[] value"));
        methodList.add(new ZMethod(WifiEnabler_java,"void","method3","String[]  strArr"));
        methodList.add(new ZMethod(WifiEnabler_java,"void","method4","ArrayList<Date> dateList"));

        WifiEnabler_java.addZMethod(methodList);
        ZClassList.add(WifiEnabler_java);
    }
*/



//    static {  // Main.java
//
//        String zIDEClass_Main = curProjectPath+"/Main.java";
//        ZIDEClass WifiEnabler_java = new ZIDEClass(zIDEClass_Main);
//        ArrayList<ZMethod> methodList = new  ArrayList<ZMethod>();
//        //void main(String[] args)
//        methodList.add(new ZMethod(WifiEnabler_java,"void","main","String[] args"));
//
//        WifiEnabler_java.addZMethod(methodList);
//        ZClassList.add(WifiEnabler_java);
//    }


    public static void main(String[] args) throws IOException {
        int classSize =  ZClassList.size();
        System.out.println(" 总共需要解析文件的个数  classSize = "+ classSize);

        String newClassCode = "" ;  //
        String originClassCode = "";
        String formatNewClassCode = "";
        for (int i = 0; i < classSize; i++) {
            System.out.println(" 开始解析第  "+ i +"个文件    文件名为: " + ZClassList.get(i).path);
            newClassCode =  ZClassList.get(i).toNewClassCode();
            System.out.println("￥￥￥￥￥￥￥￥ 新构建的类文件的内容 如下: newClassCode :" + newClassCode);  // 构建新的方法的内容
            formatNewClassCode = JavaParser.parse(newClassCode).toString();
            System.out.println("$$$$$$$$$$$$$格式化文件 如下: formatNewClassCode :" + formatNewClassCode);
            // 把当前的新构建的文件重新写入 .java 文件
            WriteToFile(ZClassList.get(i).classFile,formatNewClassCode);
        }



    }
    //class ZMethod
//class ZClass
//class ZProcess
//  1. 方法包含在类中    类包含在流程中
// 2. 多个流程会包含 同一个类
// 3. 以类为单位进行划分   流程指定自身需要的该类的哪个ZMethod(指定 methodIdentify)


    static void WriteToFile(File curFile , String classContent) {
        try {
            BufferedWriter strBuffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curFile), "utf-8"));
            strBuffWriter.write(classContent);
            strBuffWriter.flush();
            strBuffWriter.close();
        }catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }

    }


    static class ZAndroidFrameworkClass extends ZAndroidClass{
        ZAndroidFrameworkClass(String path,ArrayList<ZMethod> methodList){
            super(path,methodList);
            isFramework = true;
        }

        ZAndroidFrameworkClass(String path){
            super(path);
            isFramework = true;
        }


    }


    static class ZAndroidAPPClass extends ZAndroidClass{
        ZAndroidAPPClass(String path,ArrayList<ZMethod> methodList){
            super(path,methodList);
            isAPP = true;
        }

        ZAndroidAPPClass(String path){
            super(path);
            isAPP = true;
        }


    }


    static class ZAndroidClass extends ZCommonClass{
        ZAndroidClass(String path,ArrayList<ZMethod> methodList){
            super(path,methodList);
            isIDEClass = false;
            isAndroidClass = true;
        }

        ZAndroidClass(String path){
            super(path);
            isIDEClass = false;
            isAndroidClass = true;
        }


    }

    static class ZIDEClass extends ZCommonClass{
        ZIDEClass(String path,ArrayList<ZMethod> methodList){
            super(path,methodList);
            isIDEClass = true;
            isAndroidClass = false;
        }

        ZIDEClass(String path){
            super(path);
            isIDEClass = true;
            isAndroidClass = false;
        }

    }



    static  class ZCommonClass  extends ZClass{
        ZCommonClass(String path,ArrayList<ZMethod> methodList){
            super(path,methodList);
        }

        ZCommonClass(String path){
            super(path);
        }

    }

    static abstract class ZClass{
        CompilationUnit zCompilationUnit;
        ClassOrInterfaceDeclaration zClassOrInterfaceDeclaration;
        ArrayList<ZMethod> zmethodList ;
        String originClassContent ;  // 类的完整代码
        String newClassContent ;    // 经过修改后的代码
        String className ;
        String path ;    // 类的路径
        File classFile;   // 类对应的文件
        boolean isAPP;
        boolean isFramework;
        boolean isAndroidClass;     //  是否是安卓项目的类
        boolean isIDEClass;       // 是否是IDE 项目的 类

        ZClass(){

        }

        ZClass(String path){
            this.path = path;
            if(this.path == null  || this.path.equals("") ||  this.path.equals(".java")){
                System.out.println(" 解析文件出错！ 文件为空 或者不是Java文件   文件 = "+this.path);
                return;
            }
            String javaName =  this.path.substring(this.path.lastIndexOf("/")+1,this.path.lastIndexOf(".java"));
            if(javaName == null){
                System.out.println(" 文件名解析出错！");
                return;
            }
            this.className = javaName;
            this.classFile = new File(path);
            if(this.classFile.exists()){
                try {
                    zCompilationUnit = JavaParser.parse(this.classFile);
                    this.originClassContent =  zCompilationUnit.toString();
                    Optional<ClassOrInterfaceDeclaration>  classOrInterfaceDeclaration_OPT =  zCompilationUnit.getClassByName(this.className);
                    System.out.println(" this.className = "+ this.className);  // C:\Users\zhuzj5\IdeaProjects\JavaParser\src\Main
                    if(classOrInterfaceDeclaration_OPT != null){
                        zClassOrInterfaceDeclaration  = classOrInterfaceDeclaration_OPT.get();
                    }else{
                        System.out.println(" 解析 ClassOrInterfaceDeclaration 出错 ！");
                        return;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(" 解析文件出错！  文件 = "+this.path);
                }
            } else{
                System.out.println(" 文件  "+this.path +" 不存在无法解析！ ");
            }
        }


        ZClass(String path,ArrayList<ZMethod> methodList){
            this.path = path;
            this.zmethodList =methodList;
            this.classFile = new File(path);
            if(this.classFile.exists()){
                try {
                    zCompilationUnit = JavaParser.parse(this.classFile);
                    this.originClassContent =  zCompilationUnit.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(" 解析文件出错！  文件 = "+this.path);
                }
            } else{
                System.out.println(" 文件  "+this.path +" 不存在无法解析！ ");
            }

        }
        // 把原有的Class代码转为新替换的Class的代码
        String toNewClassCode(){
            String originClassCode = "";
            String newClassCode = "";
            String originMethodCode = "";
            String newMethodCode = "";

            if(this.originClassContent == null || this.originClassContent.equals("")){
                System.out.println("解析文件出现错误: originClassContent 为空");
                return null;
            }
            System.out.println("=========== originClassContent ==========\n"+this.originClassContent);
            originClassCode = new String(this.originClassContent);
            newClassCode = new String(originClassCode);
			int mMethodIndex = 1;
            for (ZMethod  zmethod:this.zmethodList) {
                //  curClassCode.replaceAll(zmethod.originCodeString,)
                int allNodeSize = zmethod.checkAllNode(zmethod.methodDeclaration);   //  获取总共Method 中多少个结点
                int expressNodeSize =  zmethod.expressNodeList.size();    //  每个表达式 前面 添加一些 log 以此 来观察 程序 运行
                System.out.println(" allNodeSize = "+ allNodeSize +"       表达式结点 expressNodeSize = "+ expressNodeSize);
                originMethodCode = zmethod.originCodeString;
				  System.out.println("第" + mMethodIndex+" 个方法开始解析 methodName =:" +zmethod.methodName);
				mMethodIndex++;
                if(zmethod.originCodeString.contains("zukgit")){  // 对已经添加过Log的方法不再处理
                    System.out.println(" 该方法已添加了Log 不再添加!"+zmethod.methodName);
                    continue;
                }
                newMethodCode = new String(zmethod.logOnlyParam_originCodeString);
                for (int j = 0; j < expressNodeSize; j++) {
                    Node node =  zmethod.expressNodeList.get(j);
                    String code1 = zmethod.fixExpressNodeString(node.toString());  //  需要对这里进行fix  去除空格与注释

//Line 695: =========== fixStr = Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
//Line 700: =========== fixStr = mSwitchWidget.setChecked(false);
//Line 705: =========== fixStr = mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_ON);
//Line 710: =========== fixStr = mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());
//Line 715: =========== fixStr = mSwitchWidget.setEnabled(true);
//Line 720: =========== fixStr = Toast.makeText(mContext, R.string.wifi_error, Toast.LENGTH_SHORT).show();


                    String log  = zmethod.buildIndexLog();
                    String code2 = log + code1;
                    System.out.println("############## index="+j+"     原有的Log node.toString() = "+code1);
                    System.out.println("############## index="+j+"     现有的Log code2 = "+code2);
                    newMethodCode =  newMethodCode.replace(code1,code2);     //  继续点  这里因为空格的原因无法匹配到
// ############## index=0     原有的Log node.toString() = Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
//############## index=0     现有的Log code2 =
// android.util.Log.i("zukgit_onSwitchToggled","this is indexLog index= 0");
// Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
                    System.out.println("############## index="+j+"     现有的  newMethodCode  = "+newMethodCode);

                }

                System.out.println("！！！！！！！！！！新构建的方法 Method的方法 如下: 原始方法 :" + zmethod.originCodeString);  // 构建新的方法的内容
                System.out.println("！！！！！！！！！！新构建的方法 Method的方法 如下: newMethodCode :" + newMethodCode);  // 构建新的方法的内容
                String addBlankOriginCodeString = "";
                if(zmethod.originCodeString.startsWith("/*")){
                    String originCodeStringWithOutZhuShi = zmethod.originCodeString.substring(zmethod.originCodeString.indexOf("*/")+2).trim();
                    addBlankOriginCodeString = originCodeStringWithOutZhuShi.replaceAll("\n","\n    ");
                    System.out.println("Method 开头包含注释 /***/ ！！");
                }else{
                    addBlankOriginCodeString = zmethod.originCodeString.replaceAll("\n","\n    ");
                    System.out.println("Method 开头不包含注释 /***/ ！！");
                }

                newClassCode = newClassCode.replace(addBlankOriginCodeString,newMethodCode) ; // 构建新类文件内容
                newMethodCode = null;
            }
            System.out.println("！！！！！！！！！！  原始类的内容 :" + originClassCode);  // 构建新的方法的内容
            System.out.println("！！！！！！！！！！新类的内容 :" + newClassCode);  // 构建新的方法的内容
            return newClassCode;
        }

        void addZMethod( ArrayList<ZMethod> zmethodList ){
            this.zmethodList = zmethodList;
        }
    }

    static class ZMethod{
        MethodDeclaration methodDeclaration ;
        NodeList<Parameter>  methodParamList;
        String paramLogString;   //  参数的打印字符串
        ZClass  belongClass;
        ArrayList<Node> expressNodeList;   //  表达式结点的集合( 不重复 )
        String methodIdentify ;   // 方法的唯一标识
        String returnString;   // 方法返回值
        String methodName;  // 方法名称
        String paramString;  // 方法参数字符串
        String ownerClassName;   // 方法拥有类名称
        String originCodeString;  // 方法的字符串集合
        String logOnlyParam_originCodeString;  //
        int methodLogIndex;
        String newCodeString;    // 已替换方法的集合


        String fixExpressNodeString(String nodeString){
            String fixStr = "";
            if(nodeString.contains("\n")){

                String[] strArr = nodeString.split("\n");
                for (int i = 0; i < strArr.length; i++) {
                    if(!strArr[i].trim().startsWith("//")){
                        fixStr = fixStr+strArr[i].trim();
                    }
                }

            }else{
                fixStr =  nodeString;
            }

// Log if user was connected at the time of switching off.
//mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());


            // Log if user was connected at the time of switching off.
            // mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF, mConnected.get());
            System.out.println("=========== fixStr = "+ fixStr);
            return fixStr;
        }
        int  getNodeLength(Node node){
            int nodelength = 0 ;
            if(node.getChildNodes().size() == 0){
                System.out.println(" 叶子结点的类型: "+  node.getMetaModel().getType() + " 内容为: "+ node.toString());
                return 0;
            } else{

                System.out.println(" 非叶子结点的类型: "+  node.getMetaModel().getType() + " 内容为: "+ node.toString());
                if(node.getMetaModel().getType() == com.github.javaparser.ast.stmt.ExpressionStmt.class){
                    System.out.println("=============表达式类型 ExpressionStmt :"+  node.getMetaModel().getType() + " 内容为: "+ node.toString()+"=============");

                    Node fatherNode = node.getParentNode().get();
                    String fatherType = "";
                    Node yeyeNode;
                    String yeyeType = "";
                    if(fatherNode != null){
                        fatherType = fatherNode.getMetaModel().getType().toString();
                        //      System.out.println("父类的表达式: " + fatherNode.toString() );
                        yeyeNode = fatherNode.getParentNode().get();
                        if(yeyeNode != null){
                            yeyeType = yeyeNode.getMetaModel().getType().toString();
                            //  System.out.println("爷爷类的表达式: " + yeyeNode.toString() );
                        }
                    }
                    int index = expressNodeList.size();
                    System.out.println("索引:"+index+"   *************表达式的父类表达式类型为 FatherExpressionStmt : fatherType="+fatherType+"     yeyeType="+yeyeType   );

                    expressNodeList.add(node);
                }
                List<Node> listNode = node.getChildNodes();
                nodelength = listNode.size();
                for (Node itemNode : listNode){
                    nodelength = nodelength + getNodeLength(itemNode);

                }

            }
            return nodelength;

        }

        int  checkAllNode(MethodDeclaration method){
            int num = 0;
            List<Node> curList = method.getChildNodes();
            num = curList.size();
            if(num == 0){
                return 0;
            }

            for (Node itemNode : curList){
                num = num + getNodeLength(itemNode);

            }
            return num;
        }


        String buildIndexLog(){
            String codeStr = CommonCodePrint("\"this is indexLog index= "+ methodLogIndex +"\"");
            methodLogIndex++;
            return codeStr;
        }


        ZMethod(ZClass parentClass ,String returnString, String methodName ,String paramString){
            this.belongClass = parentClass;
            this.returnString = returnString;
            this.methodName = methodName;
            this.paramString = paramString;
            if(paramString.trim().endsWith(")") && paramString.trim().startsWith("(")){
                this.paramString = paramString;
            }else{
                this.paramString = "("+paramString.trim()+")";
            }
            methodLogIndex = 0;
            this.paramString = tryFixParamFormat(this.paramString);
            this.methodIdentify = (this.returnString+" "+this.methodName+this.paramString).trim();
            this.ownerClassName = this.belongClass.className;
            this.methodDeclaration = getMethodDeclarationFromCompilationUnit(this.belongClass.zClassOrInterfaceDeclaration,methodIdentify);
            if(this.methodDeclaration == null){
                System.out.println("解析Method为空! 程序退出");
                return;
            }
            this.methodParamList = this.methodDeclaration.getParameters();
            this.originCodeString = this.methodDeclaration.toString();
            if(this.methodParamList != null){
                paramLogString = makeMethodParamLog(this.methodParamList);
                System.out.println("依据参数生成的打印Log的代码如下:\n"+ paramLogString);
                // @Override public boolean onSwitchToggled(boolean isChecked, int int1, String str1, ArrayList<String> strArr, String[] strArr1, int[] arrInt)


//  @Override public boolean onSwitchToggled()  获取到这样的字符串  没有考虑到 方法头包含 /**/的情况
                String preCode = "";
                if(originCodeString.trim().startsWith("/*") && originCodeString.trim().contains("*/") ){  // 如果包含注释的话
                    String fixOriginCodeString = originCodeString.substring(originCodeString.indexOf("*/")+2);
                    preCode = fixOriginCodeString.substring(0,fixOriginCodeString.indexOf("{")+1).trim();
                    System.out.println("包含 包头部含注释 preCode  :\n"+ preCode);
                } else{

                    preCode = originCodeString.substring(0,originCodeString.indexOf("{")+1);
                    System.out.println("不包头部含注释 preCode  :\n"+ preCode);
                }
                //  public void enableVerboseLogging(int verbose) {
                //  包含注释 
                logOnlyParam_originCodeString = originCodeString.replace(preCode,preCode+"\n"+paramLogString);  //  添加了对 参数的打印的代码

                //  把包含的 注释 /***/  去掉
                if(logOnlyParam_originCodeString.trim().startsWith("/*") && logOnlyParam_originCodeString.trim().contains("*/") ){
                    logOnlyParam_originCodeString =  logOnlyParam_originCodeString.substring(logOnlyParam_originCodeString.indexOf("*/")+2);
                }

            }
            System.out.println("方法的原始字符串:\n"+ originCodeString);
            System.out.println("加入参数Log的字符串:\n"+ logOnlyParam_originCodeString);

            expressNodeList = new ArrayList<Node>();
            //  开始处理表达式的打印
        }

        static  String tryFixParamFormat(String param){
            if(!param.contains(",")){
                while(param.contains("  ")){
                    param = param.replace("  "," ");
                }
                return param.trim();
            }
            String fixParam = "";
            String[] strArr = param.split(",");
            int size = strArr.length;

            for (int i = 0; i < size; i++) {
                String curStr = strArr[i].trim();
                while(curStr.contains("  ")){
                    curStr = curStr.replace("  "," ");
                }
                fixParam = fixParam+curStr+", ";
            }
            fixParam = fixParam.trim();
            while(fixParam.endsWith(",")){
                fixParam = fixParam.substring(0,fixParam.length()-1);

            }


            System.out.println("fixParam = "+ fixParam);
            return fixParam;

        }


        // 1 判断基本的数据类型    基本的数据类型不用盘空  直接打印
// 2. 判断是否是基本类型的数组(该数组有多少层级)
// 3 判断如果为 对象类型  那么  需要判空  并打印
// 4.判断是否为对象的集合 (该集合内部是否还包含有集合  集合的层级) 并打印


        String makeMethodParamLog(NodeList<Parameter> paramList ){
            // 决定选择哪一种打印Log的代码
            String paramLogCode  = "";
            String itemCode = "";
            for (Parameter paramItem: paramList) {
                //paramItem = boolean isChecked
                //paramItem = int int1

                if(isBasicType(paramItem.toString())){  // 基本的 boolean  byte char  short int  long float double  8种类型

                    String Code1 =  BaseTypeCode(paramItem.toString());
                    itemCode = Code1;
                    System.out.println(" 当前的参数是基本数据类型 boolean  byte char  short int  long float double      paramItem = "+ paramItem.toString() +"代码："+ Code1);
                } else if( isBasicShuZu(paramItem.toString())){

                    String Code2 =  BaseTypeListCode(paramItem.toString());
                    itemCode = Code2;
                    System.out.println(" 当前的参数是基本数据类型数组[]    paramItem = "+ paramItem.toString() +"代码："+ Code2);
                } else if( isSingleObject(paramItem.toString())){  //  String xx  Object XX  Integer XXX
                    String Code3 =  SingleObjectPrintCode(paramItem.toString());
                    itemCode = Code3;
                    System.out.println(" 当前的参数是单个类Object类型   paramItem ="+ paramItem.toString() +"代码："+ Code3);
                } else if (isObjectArr(paramItem.toString())){    //  String[]
                    String Code4 =  ArrObjectPrintCode(paramItem.toString());
                    itemCode = Code4;
                    System.out.println(" 当前的参数是类的集合 类似于ArrayList<Object> Object[]   paramItem ="+ paramItem.toString()+"代码："+ Code4);
                }

                paramLogCode = paramLogCode + itemCode;

            }
            while(paramLogCode.contains("\n ")){
                paramLogCode =  paramLogCode.replaceAll("\n ","\n");
            }
            return CommonRuntimeExceptionCodePrint()+CommonProcessAndThreadCodePrint()+paramLogCode;  // 添加所有Log的地方
        }


        static  Set<String> ListTypeList= new HashSet<String>();
        {

            ListTypeList.add("Collection");
            ListTypeList.add("List");
            ListTypeList.add("Vector");
            ListTypeList.add("Stack");
            ListTypeList.add("Deque");
            ListTypeList.add("LinkedBlockingDeque");
            ListTypeList.add("Queue");
            ListTypeList.add("ArrayDeque");
            ListTypeList.add("BlockingQueue");
            ListTypeList.add("LinkedTransferQueue");
            ListTypeList.add("LinkedBlockingQueue");
            ListTypeList.add("PriorityQueue");
            ListTypeList.add("ArrayList");
            ListTypeList.add("LinkedList");
            ListTypeList.add("SortedList");
            ListTypeList.add("Map");
            ListTypeList.add("HashMap");
            ListTypeList.add("TreeMap");
            ListTypeList.add("LinkedHashMap");
            ListTypeList.add("SortedMap");
            ListTypeList.add("WeakHashMap");
            ListTypeList.add("ConcurrentHashMap");
            ListTypeList.add("Set");
            ListTypeList.add("EnumSet");
            ListTypeList.add("SortedSet");
            ListTypeList.add("HashSet");
            ListTypeList.add("TreeSet");
            ListTypeList.add("LinkedHashSet");
            ListTypeList.add("CopyOnWriteArraySet");
            ListTypeList.add("Hashtable");
            ListTypeList.add("Iterator");
            ListTypeList.add("Iterable");


            //  Iterable<String>  itable;
            //   Iterator<String> itStr;
            //     ConcurrentHashMap cMap;
            //  BlockingQueue blockque;
            //      CopyOnWriteArraySet arrayset;
//            LinkedHashMap<String,String>  linkMap;
//            LinkedBlockingDeque<String> lk;
//            LinkedTransferQueue<String> lt;
            // Hashtable<String,String> strtable;
            // WeakHashMap<String,String> weekMap;
            //   SortedMap<String,String> sortedMap ;
            //  SortedSet<String> sortedSet ;
            // EnumSet<String> enumSet;
            //    Queue<String> q ;
            //    Vector<String> vec;
            //   Stack<String> stack;
            //   Deque<String> deque;
            //  ArrayDeque<String> arrDeq;
            //  PriorityQueue<String> queue;
        }




        static   boolean isObjectArr(String params){
            boolean isObjectArr = false;
            if(!params.contains(" ")){ // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！ params="+ params);
                return false;

            }
            String[] paramArr = params.split(" ");
            if(paramArr.length == 2 && !params.contains(",")){  // ArrList<Object> arr   ArrayList<List<XX>> map;
                isObjectArr = isArrObjectTypeString(paramArr[0]);           //   paramItem =Map<String, Object> mapValue
            }else if(params.contains(",") && params.contains("<")  && params.contains(">") ){ // param = Map<String, Object> mapValue

                isObjectArr = isArrObjectTypeString(params.substring(0,params.lastIndexOf(">")+1).replace(" ",""));
            } else{

                System.out.println(" ======当前参数字符串 !  解析错误！==========");
            }



            return isObjectArr;

        }


        static    boolean   isArrObjectTypeString(String type){  //     String xx  Object XX  Integer XXX
            boolean isArrObjectTypeString = false;
            if(!basicTypeList.contains(type.trim())  || type.contains("[]")  ){   // ArrayList
                isArrObjectTypeString = true;
                return isArrObjectTypeString;
            }

            for (String curListType: ListTypeList) {
                if(type.contains(curListType)){
                    isArrObjectTypeString = true;
                    break;
                }
            }
            return isArrObjectTypeString;
        }


        static   boolean isSingleObject(String params){
            boolean isSingleObject = false;
            if(!params.contains(" ")){ // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if(paramArr.length != 2){
                //  System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isSingleObject = isSingleObjectTypeString(paramArr[0]);

            return isSingleObject;

        }

        static    boolean   isSingleObjectTypeString(String type){  //     String xx  Object XX  Integer XXX
            boolean isSingleObjectTypeString = true;
            if(!basicTypeList.contains(type.trim()) && !type.contains("[]")  ){   // ArrayList
                for (String curListType: ListTypeList) {
                    if(type.contains(curListType)){
                        isSingleObjectTypeString = false;
                        break;
                    }
                }
            } else if(type.contains("[]")){
                isSingleObjectTypeString = false ;  // 对象数组   不是单独的对象
            }
            return isSingleObjectTypeString;
        }



        static   boolean isBasicShuZu(String params){
            boolean isBasic = false;
            if(!params.contains(" ")){ // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if(paramArr.length != 2){
                //    System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isBasic = isBasicShuzuTypeString(paramArr[0]);

            return isBasic;

        }

        static    boolean   isBasicShuzuTypeString(String type){  // byte[] int[]  long[]
            boolean isBasicShuZu = false;
            String curType  = new String(type);
            curType = curType.replace("[]","").trim();  // byte[] ->  byte
            if(basicTypeList.contains(curType.trim()) && type.contains("[]") ){
                isBasicShuZu = true;
            }
            return isBasicShuZu;
        }



        static   boolean isBasicType(String params){
            boolean isBasic = false;
            if(!params.contains(" ")){ // 并不包含空格说明这个参数的格式错误
                System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式 !  解析错误！");
                return false;

            }
            String[] paramArr = params.split(" ");
            if(paramArr.length != 2){
                //   System.out.println(" 当前参数字符串不符合   参数类型_空格_参数名  的格式  解析的长度不为2 !  解析错误！");
                return false;
            }

            isBasic = isBasicTypeString(paramArr[0]);

            return isBasic;
        }

        static  Set<String> basicTypeList= new HashSet<String>();
        {
            basicTypeList.add("boolean");
            basicTypeList.add("byte");
            basicTypeList.add("char");
            basicTypeList.add("short");
            basicTypeList.add("int");
            basicTypeList.add("long");
            basicTypeList.add("float");
            basicTypeList.add("double");
        }
        static    boolean   isBasicTypeString(String type){
            boolean isBasic = false;
            if(basicTypeList.contains(type.trim())){
                isBasic = true;
            }
            return isBasic;
        }







        String  ArrObjectPrintCode( String paramString){
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
//            String[] strArr = {"A","B"};
//            int zBaseListSize = strArr.length;
//            for (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){
//                System.out.println("zindex "+zindex +" : " + strArr[zindex].toString());
//            }

            if(baseType.contains("[]")){  // String[]  Object[]

                //String code1 = "\nint zBaseListSize = " + bsseTypeName+".length;";
                // String code2 = "\nfor (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){";
                String code1 = "\n  if( " + bsseTypeName+" != null ){";
                String code2 = "\nfor (int zindex = 0 ; zindex < "+ bsseTypeName+".length"+" ; zindex++ ){";
                String code3 = "\""+ paramString +"  zindex = \" + zindex +\"   :  value= \" + "+ bsseTypeName+"[zindex].toString()";      //  " zindex "+zindex +" : " + intArr[zindex]
                String code3_code = CommonCodePrint(code3);
                String code4 = "}  \n";
                String code5 = "}  \nelse { ";
                String code6 = CommonCodePrint("\""+bsseTypeName + " is null !\"" );
                String code7 = "} \n";

                return  code1+code2+code3_code+code4+code5+code6+code7;
            }
            String codeStr ="";
            if(paramString.contains("<") && paramString.contains(">") && !paramString.contains(",") &&
                    !paramString.contains("map") && !paramString.contains("Map") ){    // List 类型
                codeStr =    getCodePrintForCollect(baseType,bsseTypeName);
            } else if(paramString.contains("<") && paramString.contains(">") && paramString.contains(",") ){
                //  map的类型
                // paramString  MAP类型 = Map<String, Object> mapValue
                System.out.println("paramString  MAP类型 = "+ paramString );
                String type = paramString.substring(0,paramString.lastIndexOf(">")+1).replace(" ","");
                String typeName = paramString.substring(paramString.lastIndexOf(">")+1);
                codeStr =    getCodePrintForCollect(type,typeName);
            }




            //  String codeStr = CommonCodePrint(bsseTypeName+".toString()");
            return  codeStr;
        }

        String  getCodePrintForCollect(String param1,String param2){
            String paramName = param1.trim()+" "+param2.trim();
            String baseType =param1;
            String bsseTypeName = param2;
            System.out.println(" baseType ="+ baseType + "    bsseTypeName="+ bsseTypeName );

// HashMap 例子
//HashMap map =new HashMap<>();
//map.entrySet().iterator();

// TreeMap 例子
//TreeMap map =new TreeMap<>();
//map.entrySet().iterator();

// LinkedHashMap 例子
//LinkedHashMap map =new LinkedHashMap<>();
//map.entrySet().iterator();

// SortedMap 例子  SortedMap 是抽象类
//SortedMap map  = null;
//map.entrySet().iterator();


// WeakHashMap 例子
//WeakHashMap map =new WeakHashMap<>();
//map.entrySet().iterator();


// ConcurrentHashMap 例子
//ConcurrentHashMap map =new ConcurrentHashMap<>();
//map.entrySet().iterator();


// Hashtable 例子
//Hashtable linkQueue = null ;
//Iterator iterator =    linkQueue.entrySet().iterator();
//iterator.hasNext();
//Map.Entry<String , String> entrya = (Map.Entry<String , String>) iterator.next();
//entrya.getKey();
//entrya.getValue();
            String paramType = "";
            String keyStringType = "";
            String valueStringType = "";
            if(param1.contains("<") && param1.contains(">") && param1.contains(",") ){  //  Map 包含 <String, String>
                paramType =param1.substring(0,param1.indexOf("<"));
                if(param1.contains("Map") || param1.contains("map")){
                    keyStringType = param1.substring(param1.indexOf("<")+1,param1.indexOf(","));
                    valueStringType = param1.substring(param1.indexOf(",")+1,param1.lastIndexOf(">"));
                } else {
                    // 是List的 情况
                    valueStringType = param1.substring(param1.indexOf("<")+1,param1.indexOf(">"));
                }

            }else if(param1.contains("<") && param1.contains(">") && !param1.contains(",") ) {  //   paramItem =ArrayList<String> strArr   param1=ArrayList<String>
                paramType =param1.substring(0,param1.indexOf("<"));
                keyStringType = "";
                valueStringType = param1.substring(param1.indexOf("<")+1,param1.indexOf(">"));
            }else{
                paramType = param1.trim();
                valueStringType = bsseTypeName;
            }

            String code0 ="\n  if( " + bsseTypeName+" != null ){";
            if(paramType.endsWith("Map") || "Hashtable".equals(paramType)){   // 当前 类型是 Map的类型
                String code1 = "";
                if(!keyStringType.isEmpty() && !valueStringType.isEmpty()){
                    code1 = "\nMap.Entry<"+keyStringType +" , "+  valueStringType +"> entry = null;";
                } else{
                    System.out.println("获取到的Map中的key 和 value 为空  出现错误 将退出！  param1 = "+ param1 );
                    return null;
                }
                //   String code2 = "\n if("+bsseTypeName+" != null){";
                String code3 = "\n java.util.Iterator iterator = "+ bsseTypeName+".entrySet().iterator();";
                String code3_1 = "\n int mZindex = 0 ;";
                String code4 = "\n while( iterator.hasNext() ){";

                String code5 = "\n entry = (Map.Entry<"+keyStringType +","+ valueStringType+">) iterator.next();";
                String code6 = "\""+bsseTypeName + "    index ="+   "\"+ mZindex + \"   "+"  entry.Key= \""+ "+ entry.getKey() +" +" \" entry.Value=\""+"+ entry.getValue()";  // 继续点
                String code6_fix =  CommonCodePrint(code6);
                String code6_1 = " mZindex ++ ;";
                String code7 = " }";
                // String code8 = "\n }";

                String code9 = "}  \nelse { ";
                String code10 = CommonCodePrint("\""+bsseTypeName + " is null !\"" );
                String code11 = "} \n";

//  Map.Entry<String , ArrayList<String> > entry;
// Map 的 例子
//            Map<String,String> map = null;
//            Map.Entry<String , String> entry;
//            if(map != null){
//                Iterator iterator = map.entrySet().iterator();
//                while( iterator.hasNext() ){
//                    entry = (Map.Entry<String , String>) iterator.next();
//                    entry.getKey();  //Map的Value
//                    entry.getValue();  //Map的Value
//                    System.out.println("zukgit0725"+"entry.getKey()= "+entry.getKey() +" ----entry.getValue()="+entry.getValue());
//                }
//            }

                return code0+code1+code3+code3_1+code4+code5+code6_fix+code6_1+code7+code9+code10+code11;

            } else if(paramType.endsWith("Set") || paramType.endsWith("List") || paramType.endsWith("Queue") ||   paramType.endsWith("Deque")  ||
                    paramType.endsWith("Stack") ||    paramType.endsWith("Vector")  ||  paramType.endsWith("Collection")    ){
// 当前的是一个List<>   valueStringType 代表当前《》 中的内容
// CopyOnWriteArraySet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}
                String code0_list = "\n  if( " + bsseTypeName+" != null ){";
                String code1 = "\n Object[] objectList  ="+ bsseTypeName+".toArray();";
                String code2 = "\n  for(int zindex =0 ; zindex < "+bsseTypeName+".size(); zindex ++){";
                String code3 =    " \""+ paramName +"  index =\"+zindex +" +"\" value :\" + "  + "objectList[zindex].toString()";
                String code3_fix = CommonCodePrint(code3);
                String code4 = " }";
                String code5 = "\n}  \nelse { ";
                String code6 = CommonCodePrint("\""+paramName + " is null !\"" );
                String code7 = "} \n";


                return code0_list+code1+code2+code3_fix+code4+code5+code6+code7;  // 继续点
            }  else {
                // 其余以外的数组关键字  直接  toString 了
                // paramType=Map<String,  valueStringType =Object>
                System.out.println("无法判断集合的类型  paramType="+paramType.toString() +"  valueStringType ="+ valueStringType);
                return CommonCodePrint(valueStringType.trim()+".toString()");
            }

            //  Map 需要获取 Key 和 Value的 类型  否则无法转换
            // =====================   通用 ===================




// CopyOnWriteArraySet 例子
// CopyOnWriteArraySet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



// LinkedHashSet 例子
// LinkedHashSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// TreeSet 例子
// TreeSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// HashSet 例子
// HashSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



// SortedSet 例子
// SortedSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



            // EnumSet 例子
// EnumSet linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// SortedList 例子
// SortedList linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedList 例子
// LinkedList linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// PriorityQueue 例子
// PriorityQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



// LinkedBlockingQueue 例子
// LinkedBlockingQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



            // LinkedTransferQueue 例子
// LinkedTransferQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// BlockingQueue 例子
// BlockingQueue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// ArrayDeque 例子
// ArrayDeque linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



// Queue 例子
// Queue linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// LinkedBlockingDeque 例子
//LinkedBlockingDeque linkQueue = null ;
//Object[] objectList  = linkQueue.toArray();
//for(int zindex =0 ; zindex < linkQueue.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

            // Stack  例子
//Stack stackA = null;
//Object[] objectList  = stackA.toArray();
//for(int zindex =0 ; zindex < stackA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}



//  Vector 例子
//Vector vectA = null;
//Object[] objectList  = vectA.toArray();
//for(int zindex =0 ; zindex < vectA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

// List 的例子
//List listA = null;
//Object[] objectList  = listA.toArray();
//for(int zindex =0 ; zindex < listA.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// Collection 的例子
//Collection collect1 = null ;
//Object[] objectList  = collect1.toArray();
//for(int zindex =0 ; zindex < collect1.size(); zindex ++){
//
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}


// ArrayList 的例子
//ArrayList<String> strlist = new ArrayList<String>();
//Object[] objectList =  strlist.toArray();
//for(int zindex =0 ; zindex < strlist.size(); zindex ++){
//System.out.println("zindex "+zindex +" : " + objectList[zindex].toString());
//}

// Set 的例子
//Set<String> strlist = new HashSet<String>();
//Object[] objList = strlist.toArray();
//int zObjectListSize = strlist.size();
//for(int zindex =0 ; zindex < zObjectListSize; zindex ++){
//
//    System.out.println("zindex "+zindex +" : " + objList[zindex].toString());
//}





            //    return "";
        }


        String  SingleObjectPrintCode( String paramString){
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            String code1 = "\n if("+ bsseTypeName+" != null ) {";
            String code2 = CommonCodePrint("\""+paramString +" = \" + "+bsseTypeName+".toString()");
            String code3 = " } \nelse { ";
            String code4 = CommonCodePrint("\""+paramString +" ==  null\"  ");
            String code5 = "}\n";
            return  code1+code2+code3+code4+code5;
        }


        public static void getKeyAndValue(Map<String,String> map){
            Map.Entry<String , String> entry;
            if(map != null){
                Iterator iterator = map.entrySet().iterator();
                while( iterator.hasNext() ){
                    entry = (Map.Entry<String , String>) iterator.next();
                    entry.getKey();  //Map的Value
                    entry.getValue();  //Map的Value
                    System.out.println("zukgit0725"+"entry.getKey()= "+entry.getKey() +" ----entry.getValue()="+entry.getValue());
                }
            }
        }
        String  BaseTypeListCode( String paramString){
//            int[] intArr = {1,2,4};
//            int zBaseListSize = intArr.length;
//            for (int zindex = 0 ; zindex < zBaseListSize ; zindex++ ){
//                System.out.println("zindex "+zindex +" : " + intArr[zindex]);
//            }


            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            //  String code1 = "\nint zBaseListSize = " + bsseTypeName+".length;";
            String code1="";
            String code2 = "\nfor (int zindex = 0 ; zindex < "+ bsseTypeName+".length"+" ; zindex++ ){";
            String code3 = "\""+ paramString +"----index = \" + zindex +\" : \" +  \"  value = \" + "+ bsseTypeName+"[zindex]";      //  " zindex "+zindex +" : " + intArr[zindex]
            String code3_code = CommonCodePrint(code3);
            String code4 = "}\n";

            return  code1+code2+code3_code+code4;
        }


        String  BaseTypeCode( String paramString){
            String[] param = paramString.split(" ");
            String baseType = param[0];
            String bsseTypeName = param[1];
            String code ="\"" + paramString + " = \" + "+bsseTypeName;
            return  CommonCodePrint(code);
        }


        String getRuntimeExceptionCode(){
            String excepCode = "";
            return excepCode;

        }

        String CommonCodePrint(String paramString) {    //  boolean  falg |  String like  | ArrayList List  | XXX xClass
            String realPrintCode = "";
            //  String tagMethod = TAG+"_"+this.methodName;
            String tagMethod = TAG+"_"+ this.belongClass.className+"_"+this.methodName;
            if(this.belongClass.isIDEClass){
                realPrintCode = "\nSystem.out.println( "  + "\" "+ tagMethod +"   \" + "+ paramString + ");\n";
            }else if( this.belongClass.isAndroidClass && this.belongClass.isAPP ){
                // android.util.Log.i
                realPrintCode = "\nandroid.util.Log.i(\""+tagMethod +"\","+ paramString+");\n";
            } else if( this.belongClass.isAndroidClass && this.belongClass.isFramework ){
                realPrintCode = "\nandroid.util.Slog.i(\""+tagMethod +"\","+ paramString+");\n";
            }

            return realPrintCode;
        }



        // IDEA 打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        re.printStackTrace();

        //  APP层面 安卓打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        android.util.Log.v("zukgit", "RuntimeException", re);

        //  Framework层面 安卓打印
//        RuntimeException re = new RuntimeException();
//        re.fillInStackTrace();
//        android.util.SLog.v("zukgit", "RuntimeException", re);

        String CommonProcessAndThreadCodePrint() {
            String strCode1 = "";
            String strCode2 = "";
            String strCode3 = "";

            String tagMethod = TAG+"_"+ this.belongClass.className+"_"+this.methodName;
            if(this.belongClass.isIDEClass){
                String re_Name = "re"+System.currentTimeMillis();
                strCode1 = "\nRuntimeException "+ re_Name+" = new RuntimeException();";
                strCode2 = "\n"+re_Name+".fillInStackTrace();";
                String tag ="\nSystem.out.println( "  + "\" "+ tagMethod +"   RuntimeException.fillInStackTrace() \" );";
                strCode3 = tag + "\n"+re_Name+".printStackTrace();\n";

            }else if( this.belongClass.isAndroidClass && this.belongClass.isAPP ){
                // android.util.Log.i
                String re_Name = "re"+System.currentTimeMillis();
                strCode1 = "\nRuntimeException "+re_Name+" = new RuntimeException();";
                strCode2 = "\n"+re_Name+".fillInStackTrace();";
                strCode3 = "\nandroid.util.Log.i(\""+tagMethod +"\", \"RuntimeException\", "+re_Name+" );\n";
            } else if( this.belongClass.isAndroidClass && this.belongClass.isFramework ){
                String re_Name = "re"+System.currentTimeMillis();
                strCode1 = "\nRuntimeException "+re_Name+" = new RuntimeException();";
                strCode2 = "\n"+re_Name+".fillInStackTrace();";
                strCode3 = "\nandroid.util.SLog.i(\""+tagMethod +"\", \"RuntimeException\", "+re_Name+" );\n";
            }

            return strCode1+strCode2+strCode3;
        }


        String CommonRuntimeExceptionCodePrint() {    //  boolean  falg |  String like  | ArrayList List  | XXX xClass
            String tagMethod = TAG+"_"+ this.belongClass.className+"_"+this.methodName;
            String stackName = "stacks"+System.currentTimeMillis();
            String strCode1 = "\njava.util.Map<Thread, StackTraceElement[]> " + stackName +" = Thread.getAllStackTraces();";
            String threadSet_Name = "threadSet"+System.currentTimeMillis();
            String strCode2 = "\njava.util.Set<Thread> "+threadSet_Name+" = " + stackName +".keySet();";
            String strCode3 = "";
            String getProcessStr = "";
            String threadNum_Name = "threadNum"+System.currentTimeMillis();
            String strCode4 = "\nint "+threadNum_Name+" = 1;";
            String strCode5 = "\nfor (Thread key : "+threadSet_Name+") {";

            String strCode6 = "\nStackTraceElement[] stackTraceElements = "+stackName+".get(key);";
            String strCode6_fix = "";
            String strCode7 = "\nfor (StackTraceElement st : stackTraceElements) {";
            String strCode7_fix = "";
            String strCode8 = "\n}";
            String strCode8_fix = "";
            String strCode9 = "\n}";


//                for (Thread key : threadSet) {
//                    StackTraceElement[] stackTraceElements = stacks.get(key);
//                    System.out.println("\n∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【 " + key.getName() + "】  进程ID-ProcessID:【"+mProcessId + "】  线程ID-ThreadId:【"+key.getId()+ "】  线程序号-indexId: 【"+ (threadNum++)+ "】 start ∧∧∧∧∧∧∧∧∧∧");
//                    for (StackTraceElement st : stackTraceElements) {
//                        System.out.println( "StackTraceElement: " + st.toString());
//                    }
//                    System.out.println("∨∨∨∨∨∨∨∨∨∨∨∨ 线程名称threadName: 【" + key.getName() + "】 end ∨∨∨∨∨∨∨∨∨∨∨∨\n");
//                }


            if(this.belongClass.isIDEClass){
                String runtimeMXBean_Name = "runtimeMXBean"+System.currentTimeMillis();
                getProcessStr="\njava.lang.management.RuntimeMXBean  "+runtimeMXBean_Name+" = java.lang.management.ManagementFactory.getRuntimeMXBean();";
                String mProcessId_Name = "mProcessId"+System.currentTimeMillis();
                String pCodeStr1 = "\nint "+mProcessId_Name+"  =  Integer.valueOf("+runtimeMXBean_Name+".getName().split(\"@\")[0]).intValue();";
                getProcessStr = getProcessStr + pCodeStr1;
                // System.out.println("\n∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【 " + key.getName() + "】  进程ID-ProcessID:【"+mProcessId + "】  线程ID-ThreadId:【"+key.getId()+ "】  线程序号-indexId: 【"+ (threadNum++)+ "】 start ∧∧∧∧∧∧∧∧∧∧");
                //    双引号包住愿有的引号
                strCode6_fix ="\nSystem.out.println( "  + "\" "+ tagMethod +" \" +"  +     "\" \\n  线程索引序号下标 【 \"" + " + ("+threadNum_Name+") +"+ "\" 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【\""+" + key.getName() + "+" \"  】  进程ID-ProcessID:【 \""+ "+ "+mProcessId_Name+" +  "+ " \" 】  线程ID-ThreadId:【 \" "+ "+key.getId()+ "+ " \" 】 \" );";
                strCode7_fix = "\nSystem.out.println( st.toString() );";
                strCode8_fix = "\nSystem.out.println( "  + "\" "+ tagMethod +" \" + "  +     "\" \\n  线程索引序号下标 【 \"" + " + ("+threadNum_Name+"++)+ "+ "\"  】∨∨∨∨∨∨∨∨∨∨∨∨\" );";
            }else if( this.belongClass.isAndroidClass && this.belongClass.isAPP ){
                // android.util.Log.i  int mProcessId = android.os.Process.myPid();
                String mProcessId_Name = "mProcessId"+System.currentTimeMillis();
                getProcessStr="\n int "+ mProcessId_Name+" = android.os.Process.myPid();";
                String strCode6_fix_1 = "\" "+ tagMethod +" \" +"  +     "\" \\n  线程索引序号下标 【 \"" + " +("+threadNum_Name+") +"+ "\" 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【\""+" + key.getName() + "+" \"  】  进程ID-ProcessID:【 \""+ "+ " + mProcessId_Name+" +  "+ " \" 】  线程ID-ThreadId:【 \" "+ "+key.getId()+ "+ " \" 】 \" ";
                strCode6_fix = "\nandroid.util.Log.i(\""+tagMethod +"\","+ strCode6_fix_1  +");\n";
                strCode7_fix = "\nandroid.util.Log.i(\""+tagMethod +"\","+" \"StackTraceElement:\"  + "+ strCode6_fix_1  +");\n";
                String strCode8_fix_1 =  "\" "+ tagMethod +" \" +"  +     "\" \\n  线程索引序号下标 【 \"" + "+ ("+threadNum_Name+"++) +"+ "\" 】∨∨∨∨∨∨∨∨∨∨∨∨打印结束\" ";
                strCode8_fix = "\nandroid.util.Log.i(\""+tagMethod +"\","+" \"StackTraceElement:\"  + "+ strCode8_fix_1  +");\n";
            } else if( this.belongClass.isAndroidClass && this.belongClass.isFramework ){
                // android.util.SLog.i
                String mProcessId_Name = "mProcessId"+System.currentTimeMillis();
                getProcessStr="\n int "+ mProcessId_Name+" = android.os.Process.myPid();";
                String strCode6_fix_1 = "\" "+ tagMethod +" \" + "  +     "\" \\n  线程索引序号下标 【 \"" + " +("+threadNum_Name+")+ "+ "\" 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【\""+" + key.getName() + "+" \"  】  进程ID-ProcessID:【 \""+ "+ "+mProcessId_Name+" +  "+ " \" 】  线程ID-ThreadId:【 \" "+ "+key.getId()+ "+ " \" 】 \" ";
                strCode6_fix = "\nandroid.util.SLog.i(\""+tagMethod +"\","+ strCode6_fix_1  +");\n";
                strCode7_fix = "\nandroid.util.Sog.i(\""+tagMethod +"\","+" \"StackTraceElement:\"  + "+ strCode6_fix_1  +");\n";
                String strCode8_fix_1 =  "\" "+ tagMethod +" \" + "  +     "\" + \\n  线程索引序号下标 【 \"" + " +("+threadNum_Name+"++)+ "+ "\" 】∨∨∨∨∨∨∨∨∨∨∨∨打印结束\" ";
                strCode8_fix = "\nandroid.util.Sog.i(\""+tagMethod +"\","+" \"StackTraceElement:\"  + "+ strCode8_fix_1  +");\n";
            }
            return strCode1+strCode2+strCode3+getProcessStr+strCode4+strCode5+strCode6+strCode6_fix+strCode7+strCode7_fix+strCode8+strCode8_fix+strCode9;
        }


        static MethodDeclaration getMethodDeclarationFromCompilationUnit(ClassOrInterfaceDeclaration mClassOrInterfaceDeclaration,String methodIdentify){
            MethodDeclaration returnMethod = null;
            List<MethodDeclaration> methodList = mClassOrInterfaceDeclaration.getMethods();
            int methodSize = methodList.size();

            for (int i = 0; i < methodSize; i++) {
                System.out.println("=============第 "+ i +"个Method方法开始解析=====================");
                MethodDeclaration curMethodDeclaration = methodList.get(i);


//                String getName = curMethodDeclaration.getName().toString();
//                System.out.println("MethodDeclaration.getName().toString() = "+ getName);
//
//                String getNameAsString = curMethodDeclaration.getNameAsString();
//                System.out.println("MethodDeclaration.getNameAsString() = "+ getNameAsString);
//
//                String getIdentifier =  curMethodDeclaration.getName().getIdentifier();
//                System.out.println("MethodDeclaration.getName().getIdentifier() = "+ getIdentifier);
//
//                String getDeclarationAsString2=     curMethodDeclaration.getDeclarationAsString();
//                System.out.println("MethodDeclaration.getDeclarationAsString() = "+ getDeclarationAsString2);

                // String MethodDeclaration_toString =  curMethodDeclaration.toString();
                // System.out.println("MethodDeclaration_toString = "+ MethodDeclaration_toString);

                String getDeclarationAsString =     curMethodDeclaration.getDeclarationAsString(false,false,true);
                System.out.println("MethodDeclaration.getDeclarationAsString(false,false,true) = "+ getDeclarationAsString);
                System.out.println("methodIdentify = "+ methodIdentify);

                if(methodIdentify.equals(getDeclarationAsString)){
                    NodeList<Parameter>  methodParamList = curMethodDeclaration.getParameters();
                    System.out.println("=============参数列表Begin=====================");
                    for (Parameter param:methodParamList) {
                        System.out.println("param = "+ param.toString());
                    }
                    System.out.println("=============参数列表End=====================");

                    returnMethod = curMethodDeclaration;
                    System.out.println(" 匹配到 methodIdentify 返回 MethodDeclaration");
                    return returnMethod;
                }
                System.out.println("=============第 "+ i +"个Method方法解析结束=====================");
            }


            System.out.println(" 没匹配到 methodIdentify 返回null");
            return returnMethod;
        }
    }






}