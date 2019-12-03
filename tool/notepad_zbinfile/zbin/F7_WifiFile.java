import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class F7_WifiFile {

    // 1. 以安卓 10.0 为基准  记录WifiManager 以及相关 wifi类的 函数  函数说明  成员说明   新增函数  废弃函数 并打印
    // 1.1  并查看相关的具体函数被哪个类哪个方法调用的位置
    // 2. 以安卓 10.0 为基准   wifi 相关开关  以及对应的配置文件路径
    // 3. 以安卓 10.0为基准   保存相关的 路径下中文-英文的字符串 配置文件 到 一份文件中
    // 4. 以安卓 10.0为基准  保存相关的 aidl  hidl 文件 以及 cfg80211 相关的 命令到同一个文件
    // 5. 以安卓 10.0为基准  输入AOSP的根目录 获得以上信息
    // 6. 增加 connectmanager 相关的学习

//    /build/core/version_defaults.mk     当前的文件中保存有当前分析AOSP的版本
//    PLATFORM_VERSION.【 QP1A := 10 】 【PPR1 := 9】【OPM1 := 8.1.0】


    //

    static ArrayList<String> mKeyWordName = new ArrayList<String>();


    static ArrayList<String> wifiJavaFileMainPath = new ArrayList<String>();    // F7/8.0/dadada.java
    static ArrayList<F7_FileItem> JavaFileItemList = new ArrayList<F7_FileItem>();

    static ArrayList<String> wifiJavaAIDLPath = new ArrayList<String>();  // F7/8.0/aidl/xxxx.aidl
    static ArrayList<F7_FileItem> mAidlFileList = new ArrayList<F7_FileItem>();
//    static String AIDLDirPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator + "F7"+File.separator+"aidl";


    static ArrayList<String> wifiHIDLPath = new ArrayList<String>();           //F7/8.0/hal/xxxx.hal
    static ArrayList<F7_FileItem> mHalFileList = new ArrayList<F7_FileItem>();
//    static String HALDirPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator + "F7"+File.separator+"hal";



    static ArrayList<String> wifiXMLRESPath = new ArrayList<String>();   //F7/8.0/xml/Setings_xxxx.xml
    static ArrayList<F7_FileItem> mXmlFileList = new ArrayList<F7_FileItem>();
//    static String XMLDirPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator + "F7"+File.separator+"xml";


    // 保存Prop名称的Prop
    static File F7_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F7_All_Properties.properties");
    static InputStream F7_Properties_InputStream;
    static OutputStream F7_Properties_OutputStream;
    static Set<String> existVersionSet = new HashSet<String>();
    static Properties F7_Properties = new Properties();
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String F7DirPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin"+File.separator + "F7";
    static File F7DirFile = new File(F7DirPath);

    // key 为  8.1.0     重复的话 就不再执行  提示已经有了对应版本的信息了
    // value 为当前进行查询的时间  或者当前AOSP的名称
    ArrayList<Properties> AOSPFileProperties = new ArrayList<Properties>();
    static String CurrentAndroidVersionTag = "";
    static String CurrentPropertiesFileName = "";


    static {
        try {
            if (!F7_Properties_File.exists()) {
                F7_Properties_File.createNewFile();
            }
            if(!F7DirFile.exists()){
                F7DirFile.mkdirs();
            }

            F7_Properties_InputStream = new BufferedInputStream(new FileInputStream(F7_Properties_File.getAbsolutePath()));
            F7_Properties.load(F7_Properties_InputStream);
            Iterator<String> it = F7_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                existVersionSet.add(key);
                //   System.out.println("key:" + key + " value: " + F7_Properties.getProperty(key));
            }
            F7_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setF7Properity() {
        try {
            F7_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(F7_Properties_File.getAbsolutePath()));
            F7_Properties.store(F7_Properties_OutputStream, "");
            F7_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void addOneAosp2F7Properity(String key, String value) {
        existVersionSet.add(key);
        if (key != null && !F7_Properties.contains(key)) {
            F7_Properties.setProperty(key, value);
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;

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



    static {
        // 1.Wifi-Setting的wifi路径
        wifiJavaFileMainPath.add(getSystemPath("/packages/apps/Settings/src/com/android/settings/wifi/"));
        // 2.Wifi-frameworks/base/wifi 路径
        wifiJavaFileMainPath.add(getSystemPath("/frameworks/base/wifi/java/"));
        //3. Wifi-Frameworks-base-packages/SettingsLib
        wifiJavaFileMainPath.add(getSystemPath("/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/"));
        //4. wifi-framwork-opt
        wifiJavaFileMainPath.add(getSystemPath("/frameworks/opt/net/wifi/service/java/com/android/server/wifi/"));

    }

    static {
        wifiJavaAIDLPath.add(getSystemPath("/system/connectivity/wificond/aidl/android/net/wifi/"));
        wifiJavaAIDLPath.add(getSystemPath("/system/connectivity/wificond/aidl/com/android/server/wifi/wificond/"));
        wifiJavaAIDLPath.add(getSystemPath("/frameworks/base/wifi/java/android/net/wifi/"));
        // wifiJavaAIDLPath.add(getSystemPath("/frameworks/base/wifi/java/android/net/wifi/aware/"));
    }

    static {
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/1.0/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/1.1/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/1.2/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/1.3/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/supplicant/1.0/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/supplicant/1.1/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/supplicant/1.2/"));

        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/hostapd/1.0/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/hostapd/1.1/"));
        wifiHIDLPath.add(getSystemPath("/hardware/interfaces/wifi/offload/1.0/"));

    }

    static {
        wifiXMLRESPath.add(getSystemPath("/packages/apps/Settings/res/values/strings.xml"));
        wifiXMLRESPath.add(getSystemPath("/packages/apps/Settings/res/values-zh-rCN/strings.xml"));
        wifiXMLRESPath.add(getSystemPath("frameworks/base/core/res/res/values/strings.xml"));
        wifiXMLRESPath.add(getSystemPath("frameworks/base/core/res/res/values/config.xml"));
        wifiXMLRESPath.add(getSystemPath("frameworks/base/core/res/res/values-zh-rCN/strings.xml"));
    }






    static class F7_FileItem {
        String aospPath;  // identify
        String fileName;
        String fileNameNoType;
        String fileType;
        String fileTypeNoPoint;
        //    int  fileLine;
        String mTagPath;
        File mCurFile;
        String absolutePath;
        String mLocalPath;
        File mLocalFile;



        F7_FileItem(String cTagPath, String cabsPath) {
            this.mTagPath = cTagPath;
            this.absolutePath = cabsPath;
            this.aospPath = calculAospPath(cTagPath, cabsPath);
            this.mCurFile = new File(cabsPath);
            this.fileName = mCurFile.getName();
            this.fileType = calculFileType(this.fileName);
            this.fileTypeNoPoint = this.fileType.replace(".","");
            this.fileNameNoType = fileName.replace(fileType, "");
            this.fileNameNoType = fileNameNoType.replace(".", "");
            //  F7/8.0/wifimanager.java


            if("java".equals(fileType)){
                this.mLocalPath = F7DirPath+File.separator+CurrentAndroidVersionTag+File.separator+this.fileName;
                this.mLocalFile = new File(mLocalPath);
            } else if("aidl".equals(fileType)){

                this.mLocalPath = F7DirPath+File.separator+CurrentAndroidVersionTag+File.separator+"aidl"+File.separator+this.fileName;
                this.mLocalFile = new File(mLocalPath);

            }else if("hal".equals(fileType)){
                String curName  = aospPath.replace(File.separator,"_");
                curName = curName.replace("hardware_interfaces_","");
                curName = curName.replace("_hardware_interfaces_","");
                curName = curName.replace("_wifi_","wifi_");
                curName = curName.replace("__","");
                this.mLocalPath = F7DirPath+File.separator+CurrentAndroidVersionTag+File.separator+"hal"+File.separator+curName;
                this.mLocalFile = new File(mLocalPath);

            }else if("xml".equals(fileType)){
                String curName  = aospPath.replace(File.separator,"_");
                this.mLocalPath = F7DirPath+File.separator+CurrentAndroidVersionTag+File.separator+"xml"+File.separator+curName;
                this.mLocalFile = new File(mLocalPath);
            }else{
                this.mLocalPath = null;
                this.mLocalFile = null;
            }


            bodyDecNum = 0;
            shuxingNum = 0;
            methoidNum = 0;
            nodeNum = 0;
            constructNum = 0;
            isInterface = false;
            ImplementInterfaceStr = "null";
            extendsFatherStr = "null";
            charSumNum = 0 ;

        }

        static String calculAospPath(String tagPath, String absPath) {
            int index = absPath.indexOf(tagPath);
            return absPath.substring(index);
        }

       void analysisJava() {
           System.out.println("开始Java分析!");
           CompilationUnit mCompilationUnit = null;
           try {
               mCompilationUnit = JavaParser.parse(this.mCurFile);
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
           Optional<ClassOrInterfaceDeclaration> class_opt = mCompilationUnit.getClassByName(this.fileNameNoType);
           ClassOrInterfaceDeclaration mClassDeclaration = null;
           if (class_opt.isPresent() ) {
               mClassDeclaration = class_opt.get();
           }
           System.out.println(" this.fileNameNoType = "+ this.fileNameNoType);
           System.out.println(" class_opt.isPresent() = "+ class_opt.isPresent());
           if (mClassDeclaration != null ) {
               System.out.println("!Java分析!");
               // 成员
               NodeList<BodyDeclaration<?>> listA =      mClassDeclaration.getMembers();
               List<FieldDeclaration>   listB = mClassDeclaration.getFields();
               List<MethodDeclaration> listC = mClassDeclaration.getMethods();
               List<Node> listD =  mClassDeclaration.getChildNodes();
               List<ConstructorDeclaration>  lisD =   mClassDeclaration.getConstructors();
               NodeList<ClassOrInterfaceType>  listE =   mClassDeclaration.getImplementedTypes(); //  实现接口的类型
               NodeList<ClassOrInterfaceType>  listF =    mClassDeclaration.getExtendedTypes();  // 继承的类型
               List<NodeList<?>>  listG =  mClassDeclaration.getNodeLists();   // 节点结合
               EnumSet<Modifier>  setH =   mClassDeclaration.getModifiers();  // 类的修饰此组合
//               boolean isInterface =  mClassDeclaration.isInterface();  // 是否是接口
               NodeList<TypeParameter> setI =    mClassDeclaration.getTypeParameters();  // 类型信息泛型
               bodyDecNum = listA.size();
               shuxingNum = listB.size();
               methoidNum = listC.size();
               nodeNum = listD.size();
               constructNum = lisD.size();

               charSumNum = mClassDeclaration.toString().replace(" ","").replace("  ","").replace(" ","").length();

               this.isInterface = mClassDeclaration.isInterface();

               if(listE != null  && listE.size() > 0){
                   ImplementInterfaceStr = "";
                   for (int i = 0; i < listE.size(); i++) {
                       String name = listE.get(i).getNameAsString();
                       ImplementInterfaceStr =  ImplementInterfaceStr+"-"+name;
                       System.out.println("实现接口: " + i +"   name:"+name);

                   }
                   while(ImplementInterfaceStr.startsWith("-")){
                       ImplementInterfaceStr = ImplementInterfaceStr.substring(1);
                   }
                   while(extendsFatherStr.endsWith("-")){
                       ImplementInterfaceStr = ImplementInterfaceStr.substring(0,ImplementInterfaceStr.length()-1);
                   }

               }

               if(listF != null  && listF.size() > 0){
                   extendsFatherStr = "";
                   for (int i = 0; i < listF.size(); i++) {
                       String name = listF.get(i).getNameAsString();
                       extendsFatherStr =  extendsFatherStr+"-"+name;
                       System.out.println("继承父类: " + i +"   name:"+name);

                   }
                   while(extendsFatherStr.startsWith("-")){
                       extendsFatherStr = extendsFatherStr.substring(1);
                   }
                   while(extendsFatherStr.endsWith("-")){
                       extendsFatherStr = extendsFatherStr.substring(0,extendsFatherStr.length()-1);
                   }
               }

           }else{
               System.out.println("mClassDeclaration == null!");
           }
           System.out.println("结束Java分析!");
       }

        int charSumNum; // 去除空格后的函数
        int shuxingNum;
        int   methoidNum;
        int bodyDecNum;
        int nodeNum;
        int constructNum;
        boolean isInterface;
        String ImplementInterfaceStr;
        String extendsFatherStr;

        static String calculFileType(String mFileName) {
            String type = "";
            if(mFileName.contains(".")){
                int index = mFileName.lastIndexOf(".")+1;
                type = mFileName.substring(index);
                return type;
            }

            return type;

        }


    }


    // 为 F2AOSP 输出  它的输入   读取每一个Properies的key 然后生成对应集合 放到 Set中
   static void Show_F2_AOSP_INPUT(){
//       FilePath_ITEM WifiConfiguration = new FilePath_ITEM("/frameworks/base/wifi/java/android/net/wifi/WifiConfiguration.java");
//       allFileList.add(WifiConfiguration);

               ArrayList<String> propList = new ArrayList<String>();
       propList.addAll(existVersionSet);
       Set<Object> aospSet = new HashSet<Object>();

       for (int i = 0; i < propList.size(); i++) {
           String propName = propList.get(i);

           Properties curProp = new Properties();
           loadedProperiesFile(curProp,propName);
//           System.out.println("propName =  "+ propName + "  Size = "+curProp.keySet().size());
           aospSet.addAll(curProp.keySet());
       }

       ArrayList<Object> aospList = new  ArrayList<Object>();
       aospList.addAll(aospSet);


       ArrayList<String> codeList = new ArrayList<String>();
       String pre1 = "FilePath_ITEM  ";
       String pre3        = "  = new FilePath_ITEM(\"";
       String pre5 = "\");";

       String end1 = "allFileList.add(";
       String  end3 = ");";

       ArrayList<String> nameList = new   ArrayList<String>();
       for (int i = 0; i < aospList.size(); i++) {
           String aospPathItem = aospList.get(i)+"";
           String name = aospPathItem.substring(aospPathItem.lastIndexOf(File.separator)+1);
           name = name.replace(".java","");

           if(!nameList.contains(name)){
               nameList.add(name);
           }else{ //  已经 包含了 name 的那么
               name = name+""+System.currentTimeMillis();
           }
           String aospPath = aospPathItem;
           String pre2 = name;
           String pre4 = aospPath;
           String code1 =  pre1 + pre2 + pre3 + pre4 + pre5 ;
           codeList.add(code1);

           String end2 = name;
           String code2 = end1+end2+end3;
           codeList.add(code2);
           codeList.add("\n");
       }


/*
       System.out.println("existVersionSet.size() = "+ propList.size());
       System.out.println("propList.size() = "+ propList.size());
       System.out.println("aospList.size() = "+ aospList.size());
       System.out.println("══════════════"+"F2输入Begin"+"══════════════");
       for (int i = 0; i < codeList.size() ; i++) {
           System.out.println(codeList.get(i));
       }
       System.out.println("══════════════"+"F2输入End"+"══════════════");
*/

       File F2_InputFIle = new File(zbinPath+File.separator+"F7_F2_Input.txt");
       writeContent2File(F2_InputFIle,codeList);

    }

    public static void writeContent2File(File targetFile, ArrayList<String> strList) {

        BufferedWriter curBW = null;
        try {
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }

            curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "utf-8"));

            for (int i = 0; i < strList.size(); i++) {
                curBW.write(strList.get(i));
                curBW.newLine();
            }
            curBW.close();
            System.out.println("OK !");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }


    //    从外部 读取当前项目的API版本
    public static void main(String[] args) {
        initSystemInfo();
        //

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }


        if (mKeyWordName.size() == 0) {
            // 没有输入AOSP的路径   将导致程序直接退出 无法收集文件信息
            System.out.println("没有输入AOSP的路径   将导致程序直接退出 无法收集文件信息");
            return;
            //
        }

        String curAOSPPATH = mKeyWordName.get(0);
        File curAOSPFile = new File(curAOSPPATH);
        if (!curAOSPFile.exists() || !curAOSPFile.isDirectory()) {
            System.out.println("当前路径并不存在  程序退出!");
            return;
        }


        if (!checkAOSPVersion(curAOSPFile.getAbsolutePath())) {
            System.out.println("无法检测或以包含当前的AOSP版本 将打印最新两个版本之间的对比信息! ");
            System.out.println("═══════════" + "版本对比信息" + "═══════════");
            // 打印那些 对比信息
            getVersionDiffInfo();
            return;
        }

        DoJavaFileOperation(curAOSPFile);
        DoAidlFileOperation(curAOSPFile);
        DoHalFileOperation(curAOSPFile);
        DoXMLFileOperation(curAOSPFile);
        setF7Properity();
        Show_F2_AOSP_INPUT();
    }

    static void   DoXMLFileOperation(File curAOSPFile){
        AddXmlFilePath(curAOSPFile.getAbsolutePath());
        if(mXmlFileList.size() > 0){
            for (int i = 0; i < mXmlFileList.size(); i++) {
                F7_FileItem item = mXmlFileList.get(i);
                nioCopy(item.mCurFile,item.mLocalFile);
            }
        }
    }


    static void   DoAidlFileOperation(File curAOSPFile){
        AddAidlFilePath(curAOSPFile.getAbsolutePath());
        if(mHalFileList.size() > 0){
            for (int i = 0; i < mHalFileList.size(); i++) {

                F7_FileItem item = mHalFileList.get(i);
                nioCopy(item.mCurFile,item.mLocalFile);
            }
        }
    }

    static void   DoHalFileOperation(File curAOSPFile){
        AddHalFilePath(curAOSPFile.getAbsolutePath());
        if(mHalFileList.size() > 0){
            for (int i = 0; i < mHalFileList.size(); i++) {

                F7_FileItem item = mHalFileList.get(i);
                nioCopy(item.mCurFile,item.mLocalFile);
            }
        }
    }



    static void AddXmlFilePath(String aospPath) {
        for (int i = 0; i < wifiXMLRESPath.size(); i++) {
            String aospItemPath = wifiXMLRESPath.get(i);
            ArrayList<File> curAllJavaFile = getAllSubFile(aospPath, aospItemPath, ".xml");
            for (int j = 0; j < curAllJavaFile.size(); j++) {
                File fileitem = curAllJavaFile.get(j);
                mXmlFileList.add(new F7_FileItem(aospItemPath, fileitem.getAbsolutePath()));
            }

        }
        printFileItemListInfo(mXmlFileList);
    }

    static void AddHalFilePath(String aospPath) {
        for (int i = 0; i < wifiHIDLPath.size(); i++) {
            String aospItemPath = wifiHIDLPath.get(i);
            ArrayList<File> curAllJavaFile = getAllSubFile(aospPath, aospItemPath, ".hal");
            for (int j = 0; j < curAllJavaFile.size(); j++) {
                File fileitem = curAllJavaFile.get(j);
                mHalFileList.add(new F7_FileItem(aospItemPath, fileitem.getAbsolutePath()));
            }

        }
        printFileItemListInfo(mHalFileList);
    }


    static void AddAidlFilePath(String aospPath) {
        for (int i = 0; i < wifiJavaAIDLPath.size(); i++) {
            String aospItemPath = wifiJavaAIDLPath.get(i);
            ArrayList<File> curAllJavaFile = getAllSubFile(aospPath, aospItemPath, ".aidl");
            for (int j = 0; j < curAllJavaFile.size(); j++) {
                File fileitem = curAllJavaFile.get(j);
                mHalFileList.add(new F7_FileItem(aospItemPath, fileitem.getAbsolutePath()));
            }

        }
        printFileItemListInfo(mHalFileList);
    }

    static void DoJavaFileOperation(File curAOSPFile){
        CheckAndAddWifiFilePath(curAOSPFile.getAbsolutePath());

        if (JavaFileItemList.size() != 0) {
            Properties curProperties = new Properties();
            // 创建并移动目录
            String curVersionDir =  F7DirPath + File.separator + CurrentAndroidVersionTag;
            File curVersionDirFile = new File(curVersionDir);
            curVersionDirFile.mkdirs();
            for (int i = 0; i < JavaFileItemList.size(); i++) {
                F7_FileItem item = JavaFileItemList.get(i);
                nioCopy(item.mCurFile,item.mLocalFile);

                int lineCount = getLineNum(item.mCurFile);
                // CurrentAndroidVersionTag_xxxxx.java_文件行数_文件属性个数_文件方法数_文件去除空格的字符数量_xxxfather.java
                // CurrentAndroidVersionTag_xxxxx.java_文件行数_文件属性个数_文件方法数_body个数_接口名称_父类名称_文件去除空格的字符数量_null
                if(item.fileName.endsWith(".java")){
                    item.analysisJava();
                }

                curProperties.put(item.aospPath, CurrentAndroidVersionTag+"_"+item.fileName+"_"+lineCount+"_"+item.shuxingNum+"_"+item.methoidNum+"_"+item.bodyDecNum+"_"+item.ImplementInterfaceStr+"_"+item.extendsFatherStr+"_"+item.charSumNum+"_"+item.isInterface);    //   往 properties 放置数据文件
            }
            createProperiesFile(curProperties, CurrentPropertiesFileName);

            System.out.println("═══════════" + "已完成 " + CurrentPropertiesFileName + " 的保存" + "═══════════");

            // 加载 记录的时间
            addOneAosp2F7Properity(CurrentPropertiesFileName, "" + System.currentTimeMillis());

        }
    }



    public static int getLineNum(File targetFile) {
        long lineCount = 0 ;
        try {

            BufferedReader txtBR  = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile),"utf-8"));


            lineCount = txtBR.lines().count();  // 当前输入 1.txt的行数
            txtBR.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        return (int)lineCount;
    }


    public static void nioCopy(File Copyfile, File newfile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inFiC = null;
        FileChannel outFoC = null;
        int length = 2097152;
        try {
            if(!newfile.getParentFile().exists()){
                newfile.getParentFile().mkdirs();
            }
            if(!newfile.exists()){
                newfile.createNewFile();
            }
            fis = new FileInputStream(Copyfile);
            fos = new FileOutputStream(newfile);
            inFiC = fis.getChannel();
            outFoC = fos.getChannel();
            long startTime = System.currentTimeMillis();
            while (inFiC.position() != inFiC.size()) {
                if ((inFiC.size() - inFiC.position()) < length) {
                    length = (int) (inFiC.size() - inFiC.position());// 读取剩下的
                }
                inFiC.transferTo(inFiC.position(), length, outFoC);
                inFiC.position(inFiC.position() + length);
            }
            long EndTime = System.currentTimeMillis();
            System.out.print("nioCopy, originFile:"+Copyfile.getAbsolutePath());
            System.out.print("nioCopy, originFile:"+newfile.getAbsolutePath());
            System.out.print("用了毫秒总数：" + (EndTime - startTime)+" 毫秒");
            System.out.println();
            if (outFoC != null) {
                outFoC.close();
            }
            if (inFiC != null) {
                inFiC.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (fis != null) {
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }



    static void getVersionDiffInfo() {

        int propSize = existVersionSet.size();
        ArrayList<String> propNameList = new ArrayList<String>();
        propNameList.addAll(existVersionSet);
        if (propSize == 0) {
            System.out.println("当前没有保存文件数据信息，无法对比，请添加");
            return;
        } else if (propSize == 1) {
            String propName = propNameList.get(0);
            String VersionStr = propName.replace("F7_", "");
            System.out.println("当前只保存了一个:" + VersionStr + "版本的文件数据，无法进行对比 请添加另一个版本的数据!");
            return;
        }

        String preVersionPropName = propNameList.get(propNameList.size() - 2);
        String preVersion = preVersionPropName.replace("F7_", "");
        String endVersionPropName = propNameList.get(propNameList.size() - 1);
        String endVersion = endVersionPropName.replace("F7_", "");
        System.out.println("对比版本 【" + preVersion + "】: 【" + endVersion + "】");
//   对比版本的信息数据保存在 prop的 value 中  但是 保存什么呢?   继续点
        getPropVersionDiff(preVersionPropName, endVersionPropName);
    }

    static void getPropVersionDiff(String prePropName, String endPropName) {
        String prePropPath = zbinPath + File.separator + prePropName;
        String endPropPath = zbinPath + File.separator + endPropName;
        Properties prePro = new Properties();
        Properties endPro = new Properties();
        createProperiesFile(prePro, prePropPath);
        createProperiesFile(endPro, endPropPath);
        ArrayList<String> preValueList = new ArrayList<String>();
        preValueList.addAll(getPropertiesValueList(prePro));

        ArrayList<String> endValueList = new ArrayList<String>();
        endValueList.addAll(getPropertiesValueList(endPro));

//  两个 ArrayList<String> 的 对比   转为 一个 F7_FileItem的对比
        // xxxxx.java_文件版本号_文件行数_文件属性个数_文件方法数_文件去除空格的字符数量_xxxfather.java
        // xxxxx.java_文件版本号_文件行数_文件属性个数_文件方法数_文件去除空格的字符数量_null

        // 1. pre 中存在   end不存在的集合
        // 2. pre 和  end 都存在的集合
        // 3.pre 不存在   end 存在的集合
        // 通过 xxxxx.java_文件版本号  可以找到对应的文件
        // 是否应该把文件复制到本地来  /F7/9.0/WifiStatenjafan.java
        //  通过jar库 java-diff-utils 来零时进行对比


    }

    static ArrayList<String> getPropertiesValueList(Properties curProp) {
        ArrayList<String> valueList = new ArrayList<String>();
        ArrayList<Object> keyList = new ArrayList<Object>();
        keyList.addAll(curProp.keySet());
        for (int i = 0; i < keyList.size(); i++) {
            valueList.add(curProp.getProperty(keyList.get(i) + ""));
        }

        return valueList;
    }

    static void loadedProperiesFile(Properties curProp, String properName) {
        String propFilePath = zbinPath + File.separator + properName;
        File propFile = new File(propFilePath);
        try {
            if (!propFile.exists()) {
                propFile.createNewFile();
            }

            BufferedInputStream buffInput = new BufferedInputStream(new FileInputStream(propFile.getAbsolutePath()));
            curProp.load(buffInput);
            buffInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void createProperiesFile(Properties curProp, String properName) {
        String propFilePath = zbinPath + File.separator + properName;
        File propFile = new File(propFilePath);
        try {
            if (!propFile.exists()) {
                propFile.createNewFile();
            }

            BufferedOutputStream properties_OutputStream = new BufferedOutputStream(new FileOutputStream(propFile.getAbsolutePath()));
            curProp.store(properties_OutputStream, "");
            properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 读取Prop
    static Properties ReadProperiesContent(String properName) {
        String propFilePath = zbinPath + File.separator + properName;
        Properties curProp = new Properties();
        File curPropFile = new File(propFilePath);
        try {
            if (!curPropFile.exists()) {
                curPropFile.createNewFile();
            }
            BufferedInputStream curBuffIn = new BufferedInputStream(new FileInputStream(curPropFile.getAbsolutePath()));
            curProp.load(curBuffIn);
            Iterator<String> it = curProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                //   System.out.println("key:" + keyArrayList<KeyWordItem> + " value: " + F7_Properties.getProperty(key));
            }
            curBuffIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return curProp;
    }


    static String getSystemPath(String path) {
        String curpath = path;
        curpath = curpath.replace("/", File.separator);
        return curpath.trim();
    }

    static boolean checkAOSPVersion(String aospPath) {
        boolean flag = false;
        File version_defaults = new File(aospPath + getSystemPath("/build/core/version_defaults.mk"));
//        /build/core/version_defaults.mk
//        PLATFORM_VERSION.

        if (!version_defaults.exists()) {
            return false;
        }

        CurrentAndroidVersionTag = getSystemVersionFromFile(version_defaults);
        if ("".equals(CurrentAndroidVersionTag)) {
            return false;
        } else {
            flag = true;
            System.out.println(" 当前AOSP路径安卓版本: " + CurrentAndroidVersionTag + "   路径:" + aospPath);
        }
        CurrentPropertiesFileName = "F7_" + CurrentAndroidVersionTag + ".properties";

        if (existVersionSet.contains(CurrentPropertiesFileName)) {
            System.out.println(" 当前AOSP路径安卓版本: " + CurrentAndroidVersionTag + "  对应文件:" + CurrentPropertiesFileName + " 已经包含在文件: " + F7_Properties_File.getName());
            return false;
        }


        return flag;
    }

    static String getSystemVersionFromFile(File versionFile) {
        String versionTag = "";

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(versionFile)));
            String lineContent = "";

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                String content = lineContent.trim();
                if (content.startsWith("PLATFORM_VERSION.")
                        && content.contains(":=")
                        && Character.isDigit(content.charAt(content.length() - 1))) {  // 最后一个字母为数字
                    curBR.close();
                    content = content.substring(content.indexOf(":=") + ":=".length()).trim();
                    return content;
                }
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println(" AndroidVersion 读取 异常:" + e.fillInStackTrace());
            e.printStackTrace();
        }
        return versionTag;
    }


    static void CheckAndAddWifiFilePath(String aospPath) {
        for (int i = 0; i < wifiJavaFileMainPath.size(); i++) {
            String aospItemPath = wifiJavaFileMainPath.get(i);
            ArrayList<File> curAllAidlFile = getAllSubFile(aospPath, aospItemPath, ".java");
            for (int j = 0; j < curAllAidlFile.size(); j++) {
                File fileitem = curAllAidlFile.get(j);
                JavaFileItemList.add(new F7_FileItem(aospItemPath, fileitem.getAbsolutePath()));
            }

        }
        printFileItemListInfo(JavaFileItemList);
    }


    static void printFileItemListInfo(ArrayList<F7_FileItem> fileList) {
        System.out.println("═════════════" + "当前AOSP版本【" + CurrentAndroidVersionTag + "】  文件长度【" + fileList.size() + "】═════════════");

        for (int i = 0; i < fileList.size(); i++) {
            F7_FileItem itemF7 = fileList.get(i);
            int index = i + 1;
            System.out.println("索引:" + index + "  文件AOSP路径:" + itemF7.aospPath);
        }
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
                        // System.out.println("file found at path: " + file.toAbsolutePath());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }
}

