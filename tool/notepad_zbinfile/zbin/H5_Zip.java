

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;

import com.github.junrar.rarfile.FileHeader;
import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;
import net.lingala.zip4j.exception.ZipException;
import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import net.lingala.zip4j.core.ZipFile;
import org.tukaani.xz.FilterOptions;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

//  在当前目录执行解压操作   可以输入多个密码尝试进行解压   并对判断当前目录哪些已经解压
// 把未解压的解压一遍 并排序
// 无法正确解压的改名为 UNZIP_  开头
// https://www.bbsmax.com/A/kmzLnmrl5G/
//https://www.cnblogs.com/chuckjam/p/9455498.html
// https://blog.csdn.net/zhyh1986/article/details/7724616
//https://github.com/chenchang95/android-decompression-2/

// 7z.exe  和  WinRaR  来 实现 解压 才能有速度     7z 能解压 zip 和 7z     不能解压 rar
// 7z.exe -p12345678  x big.7z -o./big
//
public class H5_Zip {


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 H5 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zzip_H5";

    static String UNZIP_PRE = "ZFAILED_UNZIP_";   //
    /*******************修改属性列表 ------End *********************/


    public enum FileType {
        // 未知
        UNKNOWN,
        // 压缩文件
        ZIP, RAR, _7Z, TAR, GZ, TAR_GZ, BZ2, TAR_BZ2,
        // 位图文件
        BMP, PNG, JPG, JPEG,
        // 矢量图文件
        SVG,
        // 影音文件
        AVI, MP4, MP3, AAR, OGG, WAV, WAVE
    }


    static ArrayList<String> zipFileTypeList = new ArrayList<String>();



    // 压缩的文件的 路径和 密码的 集合 Map
    static Map<String,String> ZIP_Path_Password_Map = new HashMap<String,String>();

    static {
        zipFileTypeList.add(".zip");
        zipFileTypeList.add(".7z");
        zipFileTypeList.add(".rar");
        zipFileTypeList.add(".gz");
        zipFileTypeList.add(".tar");
    }


    //  当前文件 格式  与 对应的 数组

    // key = type       value =  符合过滤文件规则的名称的文件的集合
    public static HashMap<String, ArrayList<File>> arrFileMap = new HashMap<String, ArrayList<File>>();

    public static ArrayList<File> fixCommonFileList = new ArrayList<File>();
    // 当 Mode 为 SEARCH_MODE_NAME 时 typeStrList 为空   那么 我们初始化的时候 就以 空字符串 "" 为key
    static void initArrFileMap(ArrayList<String> typeStrList) {
        if (typeStrList.size() == 0) {
            String key = "";
            ArrayList<File> fileList = new ArrayList<File>();
            arrFileMap.put(key, fileList);
        } else {
            for (int i = 0; i < typeStrList.size(); i++) {
                String typeItem = typeStrList.get(i);
                ArrayList<File> fileListItem = new ArrayList<File>();
                arrFileMap.put(typeItem, fileListItem);
            }
        }
    }


    static void addFileMapItemWithKey(String keyType, File curFile) {
        if (arrFileMap.containsKey(keyType)) {
            ArrayList<File> fileList = arrFileMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            arrFileMap.put(keyType, fileList);
        }
    }


    public static void beginFliterFile(File shellDirFile,boolean isAll_Search_Express) {
        ArrayList<File> allSingleFileList = new ArrayList<File>();
        if(isAll_Search_Express){   //  全局搜索

            allSingleFileList.addAll(getAllSubFile(shellDirFile));
            System.out.println("isAll_Search_Express = true  子 孙 曾孙 文件 !");

        }else{
            System.out.println("isAll_Search_Express = false  子文件 ");
            allSingleFileList.addAll(Arrays.asList(shellDirFile.listFiles()));

        }

        for (int i = 0; i < allSingleFileList.size(); i++) {
            File curFile = allSingleFileList.get(i);


            String curFileName = curFile.getName().toLowerCase();
            String matchTypeKey = CheckFileType(curFileName);
            if (matchTypeKey != null) {
                addItemFileToMap(matchTypeKey, curFile);
            }

        }




    }


    static void addItemFileToMap(String key, File fileItem) {
        ArrayList<File> fileArr = arrFileMap.get(key);
        if (fileArr == null) {
            addFileMapItemWithKey(key, fileItem);
            fileArr = arrFileMap.get(key);
        }
        if (!fileArr.contains(fileItem)) {
            fileArr.add(fileItem);
        }

    }

    static String CheckFileType(String curFileName) {
        String curTypeStr = null;
        if (!curFileName.contains(".")) {
// 当前的文件没有包含后缀  所以无法识别类型 返回 null
            return curTypeStr;
        }


//  获得 当前文件的  .后缀名    .jpg   .png
        String suffix = curFileName.substring(curFileName.lastIndexOf(".")).trim().toLowerCase();

        return suffix;
    }


    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File H5_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream H5_Properties_InputStream;
    static OutputStream H5_Properties_OutputStream;
    static Properties H5_Properties = new Properties();
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

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_ParamStrList = new ArrayList<>();

    static ArrayList<String> PasswordList = new ArrayList<String>();


    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new HashMap<String, ArrayList<File>>();
    ;


//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    // 获取 运行时 参数
//    static JavaRuntimeInfo JavaRuntimeInfoValue =  new JavaRuntimeInfo();

    // PATH 环境变量值进行当前的保存处理  获取 Path参数

    static Set<File> UnKnowFileList = new HashSet<>();

    static String EnvironmentValue = System.getProperties().getProperty("java.library.path");

    //  7z.exe 文件的
    static String WINEXE_Z7_PATH = zbinPath + File.separator+"H5_7z.exe ";
    //  7z.exe 文件的
    static String WINEXE_WINRAR_PATH = zbinPath + File.separator+"H5_WinRAR.exe ";

    // Mac下 7z 文件的路径

    static String Mac_Z7_PATH = zbinPath + File.separator+"H5_Mac_7z ";



    //  用于标记当前 用户输入的最后一个参数是 all 用于判断是否需要把当前目录的所有文件 子文件 孙文件 都解压缩
    static boolean  isAll_Search_Express = false;

    // 标记是否是在 J1.bat 文件中 被执行   以 J1_all 结尾
    //1.  如果是 J1.bat 文件中  执行   当前路径不是 ZWin_Software 那么什么也不执行 提示报错
    static boolean isExeCute_By_J1_Bat =  false;
    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else {
                    CUR_INPUT_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }
        if(CUR_INPUT_ParamStrList.size() > 0){
            String lastInputStr = CUR_INPUT_ParamStrList.get(CUR_INPUT_ParamStrList.size()-1);
            if("all".endsWith(lastInputStr)){
                isAll_Search_Express = true;
            }else if("J1_all".equals(lastInputStr)){
                isAll_Search_Express = true;
                isExeCute_By_J1_Bat = true;

            }
        }

        PasswordList.add(""); // 加入一个 默认的 密码  空的密码
        PasswordList.addAll(CUR_INPUT_ParamStrList);


        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);


        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
            return;
        }


        if(isExeCute_By_J1_Bat && !CUR_Dir_FILE.getName().equals("ZWin_Software")){
            System.out.println("只有在 ZWin_Software 目录下 才能 执行 zinstall_software_J1.bat 的 解压缩功能!");
            return;
        }

        // 用户没有输入参数
        if (CUR_INPUT_ParamStrList.size() == 0) {
            System.out.println("用户没有输入解压密码  默认当前 压缩文件无密码进行解压 ");
            System.out.println("【 Tip1: 最后一个输入参数为 all ( "+Cur_Bat_Name+"  all "+ " ) 时 表示以当前shell目录为根目录的 全局 子文件 孙文件 曾孙文件搜索 ! 】");
//            showNoTypeTip();
            System.out.println("isAll_Search_Express = "+ isAll_Search_Express +"   仅仅子文件" );

        } else {
            System.out.println("当前用户输入的密码如下,将使用该密码集合解压当前 加密的 .zip .rar .7z 文件!");
            System.out.println("【 Tip1: 最后一个输入参数为  all ( "+Cur_Bat_Name+"  all "+ " ) 时 表示以当前shell目录为根目录的 全局 子文件 孙文件 曾孙文件搜索 ! 】");
            System.out.println("isAll_Search_Express = "+isAll_Search_Express+  (isAll_Search_Express == true?" 孙文件 孙孙文件搜索 ":" 仅子文件搜索"));
            for (int i = 0; i < PasswordList.size(); i++) {
                String passwordItem = PasswordList.get(i);
                int count = i + 1;
                System.out.println("[密码 " + count + " ] : " + passwordItem);
            }

        }


        beginFliterFile(CUR_Dir_FILE,isAll_Search_Express);
        showMapSummaryData();
/*     //.代码的方式 对文件的进行的解压  太慢了
        tryJieYaOperationWithCode();


*/

        tryJieYaOperationWithExe();
        showPath_PasswordMap();

        // 解压成功的文件 ZFAILED_UNZIP_ 开头 文件改回来
        fixedFailedName(fixCommonFileList);

        //  解压 失败的文件改为 ZFAILED_UNZIP_ 开头
        showUnKnowFile(UnKnowFileList);
/*        fixedFailedName(fixCommonFileList);
        showUnKnowFile(UnKnowFileList);*/
        // 开始执行逻辑
        //1.  对参数进行过滤判断
        //2.  拿到参数再具体进行逻辑操作

        setProperity();
        if(isAll_Search_Express && "ZWin_Software".equals(CUR_Dir_FILE.getName())){
            System.out.println("Tip: 当前目录已执行完成 解压缩 可以在当前目录 "+CUR_Dir_FILE.getName()+ " 执行如下命令    \n zinstall_software_J1.bat " );
        }
    }



    static boolean isEverFailedFile(File file) {
        String name = file.getName();
        if (name.startsWith(UNZIP_PRE)) {
            return true;
        }
        return false;

    }


    // 把 名称 回归 从ZFAILED_UNZIP_  去除文件
    static void fixedFailedName(ArrayList<File> backCommonNameList) {

        for (int i = 0; i < backCommonNameList.size(); i++) {
            File newNameFile = backCommonNameList.get(i);

            String name = newNameFile.getName();
            String newname = name.replace(UNZIP_PRE, "");  // 把名称还原
//            System.out.println("改名称\noldName:"+name+"\nnewName:"+newname);
            tryReName(newNameFile, newname);
        }


    }

    // 重命名为 原来的 文件名称  这里 windows 下 不用
    static File fixedFailedName_BackCommon(File file) {
        File newFile = file;
        if (isEverFailedFile(file)) {
            fixCommonFileList.add(file);
        }
        return newFile;
    }

    // 重命名为 原来的 文件名称  这里 windows 下 不用
    static File fixedFailedName(File file) {
        File newFile = file;
        if (isEverFailedFile(file)) {


            String name = file.getName();
            String newname = name.replace(UNZIP_PRE, "");  // 把名称还原
            tryReName(file, newname);
            newFile = new File(file.getParentFile().getAbsolutePath() + File.separator + newname);
        }
        return newFile;
    }


    @SuppressWarnings("unchecked")
    public static void showPath_PasswordMap() {
        Map.Entry<String, String> entry;
        ArrayList<String> pathList = new  ArrayList<String>();
        ArrayList<String> wordpasswordList = new  ArrayList<String>();
        int maxPathLength = 0;
        if (ZIP_Path_Password_Map != null) {
            Iterator iterator = ZIP_Path_Password_Map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String path = entry.getKey();  //Map的Value
                int pathLength = path.length();
                if(pathLength > maxPathLength){
                    maxPathLength = pathLength;
                }
                String password = entry.getValue();  //Map的Value
                pathList.add(path);
                wordpasswordList.add(password);
//                System.out.println("路径: " + path + "  密码: " + password);
            }
        }

        maxPathLength = maxPathLength + 4;
        System.out.println("══════════════"+" 解压密码说明 "+ "══════════════");
        for (int i = 0; i < pathList.size(); i++) {
            String pathItem = pathList.get(i);
            String password = wordpasswordList.get(i);
            int pathItemLength = pathItem.length();
            int distance = maxPathLength - pathItemLength;
            String sameLengthPath = pathItem + getPaddingSizeString(distance," ");
            System.out.println( sameLengthPath+ "  Password【 "+ password+ " 】");
        }
        System.out.println();
    }


    static void showUnKnowFile(Set<File> unknowPasswordFileList) {
        if(isAll_Search_Express){
           return;
        }
        System.out.println("*******" + " 当前目录无法解压的文件个数 : 【" + unknowPasswordFileList.size() + "】 将改为以" + "【" + UNZIP_PRE + "】开头" + " *******");
ArrayList<File> unknowList = new ArrayList<File>();
boolean  isContainBlankName = false;
        unknowList.addAll(unknowPasswordFileList);
        int indexCount = 0;
        for (int i = 0; i < unknowList.size(); i++) {
            indexCount = i + 1;
            File fileItem = unknowList.get(i);
            System.out.println("Index[ " + indexCount + " ] : " + fileItem.getAbsolutePath());
            String oldname = fileItem.getName();
            if (!oldname.startsWith(UNZIP_PRE)) { // 对于 没有以  ZFAILED_UNZIP_ 开头的文件 进行改名
                String newname = UNZIP_PRE + oldname;
                //  把名字 中的空格改为 下划线
                if(newname.contains(" ")){
                    isContainBlankName = true;
                }
                newname = newname.replace("   ","_");
                newname = newname.replace("  ","_");
                newname = newname.replace(" ","_");

                    tryReName(fileItem, newname);


            }

        }
        if(isContainBlankName){
            System.out.println();
            System.out.println("提示: 在解压失败文件中包含 空格文件名 导致解压缩失败！ \n现已清除空格 您可以重新再次执行解压操作");
        }


    }

    //    如果被重命名的文件已存在，那么renameTo()不会成功
//    renameTo()成功后，原文件会被删除

    static boolean tryReName(File curFile, String newName) {
        String newFilePath = curFile.getParent() + File.separator + newName;
        String oldName = curFile.getName();
        File newFile = new File(newFilePath);
        if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
//            System.out.println("当前目录已存在重命名后的文件  文件名称:"+ curFile.getName());
            return false;    // 已经存在的文件不处理 直接返回

        }

        boolean flag = curFile.renameTo(newFile);
        if (flag) {
            System.out.println(oldName + " 转为 " + newFilePath + " 成功！");
        } else {
            System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
        }
        return flag;
    }



    static void JieYaRar_MacOS(ArrayList<File> rarList) {
        String rar_JiaYa_EndFlag="_rar";  // 解压的文件夹 是以 _rar 为标识
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 rar 文件
        Set<File> existUnknowrarList = new HashSet<File>();
        if (rarList == null) {
            return;
        }

        System.out.println("══════════════" + ".rar 文件大小:" + rarList.size() + "══════════════");

        for (int i = 0; i < rarList.size(); i++) {
            File rarFile = rarList.get(i);
            String rarAbsPath = rarFile.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            rarFile = fixedFailedName(rarFile);   // xxx.rar 文件
            int indexCount = i + 1;
//            boolean isJiaMi = isRarEncrypted(rarFile);
            boolean isJiaMi = true;  // 默认的 jima 都是 true的
            String rarTargetFilePath = rarFile.getParent() + File.separator + getFileNameNoPoint(rarFile.getName())+rar_JiaYa_EndFlag;

            if(isAll_Search_Express){
                rarTargetFilePath = rarFile.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(rarFile.getName());
            }
            System.out.println(" ZZZrarTargetFilePath = "+ rarTargetFilePath);

            //  把 ZFAILED_UNrar_  去除 再检查  是否存在 目标文件
            rarTargetFilePath = rarTargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(rarTargetFilePath);  // 解压的文件夹

            // 目标文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");

            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);

            }
            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压rar文件:" + rarFile.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
                out:
                for (int j = 0; j < PasswordList.size(); j++) {
                    String password = PasswordList.get(j);
                    // 7z.exe -y -p111  x 111_pw.rar -o./111_pw_rar       解压rar 密码
                    //
                    // WINEXE_Z7_PATH(7z执行文件) rarTargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                    // password(密码)     rarAbsPath( rar 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNrar_111_pw.rar  -o./zfailed_unrar_111_pw_rar

                    // WinRAR_H5.exe -p111 -y x 111_pw.rar  .\111_pw_rar\

                    String commandRar = "";
// 无密码解压
//  C:\Users\zhuzj5\Desktop\zbin\H5_WinRAR.exe   -y  x C:\Users\zhuzj5\Desktop\H5\111\3\111_no.rar  .\111_no_rar\

// C:\Users\zhuzj5\Desktop\zbin\H5_WinRAR.exe  -y  x C:\Users\zhuzj5\Desktop\H5\111\3\test1\111_pw.rar  .\111_pw_rar\
// 使用无密码  解压有密码的 rar文件
                    if(!"".equals(password.trim())){
                        commandRar = Mac_Z7_PATH  + " -p\"" + password.trim() + "\" -y  x \"" + rarAbsPath + "\"  \".\\" + targetDirName+"\\"+"\"";

                    }else{
                        commandRar = Mac_Z7_PATH +  "-p00 -y  x \"" + rarAbsPath + "\"  \".\\" + targetDirName+"\\"+"\"";

                    }

                    // Mac 下 使用 7z 去尝试解析 rar文件
                    commandRar = Mac_Z7_PATH + " -y -p\"" + password.trim() + "\"  x  \"" + rarAbsPath + "\"  -o\"./" + targetDirName+"\"";


// 正确  Users/zhuzhengjie/Desktop/zbin/H5_Mac_7z  -y -p"2257"  x  "/Users/zhuzhengjie/Desktop/zip_rar_7z/7z/7z_jpg_mimaB.7z"  -o"./7z_jpg_mimaB_7z"
// 错误 Users/zhuzhengjie/Desktop/zbin/H5_Mac_7z -p00 -y  x "/Users/zhuzhengjie/Desktop/zip_rar_7z/rar/dahua.rar"  ".\dahua_rar\"
                    System.out.println("目标解压路径 rarTargetFilePath = " + rarTargetFilePath);
                    System.out.println("目标解压路径 targetDirName = " + targetDirName);
                    System.out.println("isJiaMi = "+ isJiaMi +"   password = "+password+"  command = \n" + commandRar);

//目标解压路径 zipTargetFilePath = D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\cmder
//目标解压路径 targetDirName = cmder

// command = C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p""  x "D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\ZFAILED_UNZIP_cmder.zip"  -o"./cmder"

                    String commandResult = execCMD_Mac(commandRar);
                    System.out.println("══════════════Begin ExE ");
                    System.out.println(commandResult);
                    System.out.println("══════════════End ExE ");
//               targetPassword = password;


                    // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                    if (isTargetExist_BigBytes(targetFile)) {
                        isOK = true;
                        targetPassword = password;
                        if(!"".equals(targetPassword)){  // 如果 密码 不为 空  那么  JiaMi的 就是 true
                            isJiaMi = true;
                        }else{
                            isJiaMi = false;
                        }
                        //  如果成功 解压  当前 rar文件 含有 ZFailed_Unrar字样 那么重命名
                        rarFile = fixedFailedName_BackCommon(rarFile);
                        String fixAbsPath = rarFile.getAbsolutePath().replace(UNZIP_PRE,"");
                        ZIP_Path_Password_Map.put(fixAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                        System.out.println("══》 【 解压成功 】 rar 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + rarFile.getName() + " 】");
                        break out;
                    } else {
                        // 如果解压  失败 那么 把 解压出来的 目录 删掉
                        System.out.println("command 执行失败  密码错误!");
                        isJiaMi = false;
                        deleteDirectory(targetFile.getAbsolutePath());
                    }


                }
            }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(rarFile.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(rarFile.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 rar 文件 :" + rarFile.getName());

                if (!existUnknowrarList.contains(rarFile) && !isTargetDirExist)
                    existUnknowrarList.add(rarFile);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 rar 的 文件
            }
            System.out.println("══════════════" + " 解压rar文件:" + rarFile.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowrarList);
        }

    }


    static void JieYaRar_Windows(ArrayList<File> rarList) {
        String rar_JiaYa_EndFlag="_rar";  // 解压的文件夹 是以 _rar 为标识
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 rar 文件
        Set<File> existUnknowrarList = new HashSet<File>();
        if (rarList == null) {
            return;
        }

        System.out.println("══════════════" + ".rar 文件大小:" + rarList.size() + "══════════════");

        for (int i = 0; i < rarList.size(); i++) {
            File rarFile = rarList.get(i);
            String rarAbsPath = rarFile.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            rarFile = fixedFailedName(rarFile);   // xxx.rar 文件
            int indexCount = i + 1;
//            boolean isJiaMi = isRarEncrypted(rarFile);
            boolean isJiaMi = true;  // 默认的 jima 都是 true的
            String rarTargetFilePath = rarFile.getParent() + File.separator + getFileNameNoPoint(rarFile.getName())+rar_JiaYa_EndFlag;

            if(isAll_Search_Express){
                rarTargetFilePath = rarFile.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(rarFile.getName());
            }
            System.out.println(" ZZZrarTargetFilePath = "+ rarTargetFilePath);

            //  把 ZFAILED_UNrar_  去除 再检查  是否存在 目标文件
            rarTargetFilePath = rarTargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(rarTargetFilePath);  // 解压的文件夹

            // 目标文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");

            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);

            }
            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压rar文件:" + rarFile.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
                out:
                for (int j = 0; j < PasswordList.size(); j++) {
                    String password = PasswordList.get(j);
                    // 7z.exe -y -p111  x 111_pw.rar -o./111_pw_rar       解压rar 密码
                    //
                    // WINEXE_Z7_PATH(7z执行文件) rarTargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                    // password(密码)     rarAbsPath( rar 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNrar_111_pw.rar  -o./zfailed_unrar_111_pw_rar

 // WinRAR_H5.exe -p111 -y x 111_pw.rar  .\111_pw_rar\

                    String commandRar = "";
// 无密码解压
//  C:\Users\zhuzj5\Desktop\zbin\H5_WinRAR.exe   -y  x C:\Users\zhuzj5\Desktop\H5\111\3\111_no.rar  .\111_no_rar\

// C:\Users\zhuzj5\Desktop\zbin\H5_WinRAR.exe  -y  x C:\Users\zhuzj5\Desktop\H5\111\3\test1\111_pw.rar  .\111_pw_rar\
// 使用无密码  解压有密码的 rar文件
                    if(!"".equals(password.trim())){
                        commandRar = WINEXE_WINRAR_PATH + " -p\"" + password.trim() + "\" -y  x \"" + rarAbsPath + "\"  \".\\" + targetDirName+"\\"+"\"";

                    }else{
                        commandRar = WINEXE_WINRAR_PATH +  "-p00 -y  x \"" + rarAbsPath + "\"  \".\\" + targetDirName+"\\"+"\"";

                    }
                    System.out.println("目标解压路径 rarTargetFilePath = " + rarTargetFilePath);
                    System.out.println("目标解压路径 targetDirName = " + targetDirName);
                    System.out.println("isJiaMi = "+ isJiaMi +"   password = "+password+"  command = \n" + commandRar);

//目标解压路径 zipTargetFilePath = D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\cmder
//目标解压路径 targetDirName = cmder

// command = C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p""  x "D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\ZFAILED_UNZIP_cmder.zip"  -o"./cmder"

                    String commandResult = execCMD(commandRar);
                    System.out.println("══════════════Begin ExE ");
                    System.out.println(commandResult);
                    System.out.println("══════════════End ExE ");
//               targetPassword = password;


                    // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                    if (isTargetExist_BigBytes(targetFile)) {
                        isOK = true;
                        targetPassword = password;
if(!"".equals(targetPassword)){  // 如果 密码 不为 空  那么  JiaMi的 就是 true
    isJiaMi = true;
}else{
    isJiaMi = false;
}
                        //  如果成功 解压  当前 rar文件 含有 ZFailed_Unrar字样 那么重命名
                        rarFile = fixedFailedName_BackCommon(rarFile);
String fixAbsPath = rarFile.getAbsolutePath().replace(UNZIP_PRE,"");
                        ZIP_Path_Password_Map.put(fixAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                        System.out.println("══》 【 解压成功 】 rar 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + rarFile.getName() + " 】");
                        break out;
                    } else {
                        // 如果解压  失败 那么 把 解压出来的 目录 删掉
                        System.out.println("command 执行失败  密码错误!");
                        isJiaMi = false;
                        deleteDirectory(targetFile.getAbsolutePath());
                    }


                }
            }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(rarFile.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(rarFile.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 rar 文件 :" + rarFile.getName());

                if (!existUnknowrarList.contains(rarFile) && !isTargetDirExist)
                    existUnknowrarList.add(rarFile);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 rar 的 文件
            }
            System.out.println("══════════════" + " 解压rar文件:" + rarFile.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowrarList);
        }

    }

    static void JieYaRar(ArrayList<File> rarList) {
        ArrayList<File> existUnknow_RarList = new ArrayList<File>();
        if (rarList == null) {
            return;
        }
        System.out.println("══════════════" + ".rar 文件大小:" + rarList.size() + "══════════════");

        for (int i = 0; i < rarList.size(); i++) {
            File rarFile = rarList.get(i);
            rarFile = fixedFailedName(rarFile);
            int indexCount = i + 1;
            boolean isJiami = isRarEncrypted(rarFile);
            String rarTargetFilePath = rarFile.getParent() + File.separator + getFileNameNoPoint(rarFile.getName());
            File targetFile = new File(rarTargetFilePath);
            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压 .rar 文件:" + rarFile.getName() + "  Begin" + "══════════════");
            out:
            for (int j = 0; j < PasswordList.size(); j++) {
                String password = PasswordList.get(j);
//               targetPassword = password;
                if (decompressRar(password, rarFile)) {
                    isOK = true;
                    targetPassword = password;
                    System.out.println("══》 【 解压成功 】 .rar 文件   " + (isJiami ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【 无密码锁】") + "    Path【 " + rarFile.getName() + " 】");
                    break out;
                }


            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 rar 文件 :" + rarFile.getName());
                if (!existUnknow_RarList.contains(rarFile))
                    existUnknow_RarList.add(rarFile);  //   保存那些 不知道密码的 rar 的 文件
            }
            System.out.println("══════════════" + " 解压rar文件:" + rarFile.getName() + "  End" + isOkTip(isOK) + (isJiami ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknow_RarList);
        }
    }



    static void JieYa7Z_MacOS(ArrayList<File> z7List,String type) {
        String z7_JiaYa_EndFlag="_"+type.replace(".","");  // 解压的文件夹 是以 _z7 为标识

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 z7 文件
        Set<File> existUnknowz7List = new HashSet<>();
        if (z7List == null) {
            return;
        }

        System.out.println("══════════════" + ".7z 文件个数:" + z7List.size() + "══════════════");

        for (int i = 0; i < z7List.size(); i++) {
            File z7File = z7List.get(i);
            String z7AbsPath = z7File.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            z7File = fixedFailedName(z7File);   // xxx.z7 文件
            int indexCount = i + 1;
            boolean isJiaMi = is7zEncrypted(z7File);
            String z7TargetFilePath = z7File.getParent() + File.separator + getFileNameNoPoint(z7File.getName())+z7_JiaYa_EndFlag;
            if(isAll_Search_Express){
                z7TargetFilePath = z7File.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(z7File.getName());
            }

            System.out.println("ZZZz7TargetFilePath = "+ z7TargetFilePath);
            //  把 ZFAILED_UNZIP_  去除 再检查  是否存在 目标文件
            z7TargetFilePath = z7TargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(z7TargetFilePath);  // 解压的文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");

            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);

            }

            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压 7z文件:" + z7File.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
                out:
                for (int j = 0; j < PasswordList.size(); j++) {
                    String password = PasswordList.get(j);
                    // 7z.exe -y -p111  x 111_pw.z7 -o./111_pw_z7       解压z7 密码
                    //
                    // WINEXE_Z7_PATH(7z执行文件) z7TargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                    // password(密码)     z7AbsPath( z7 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNz7_111_pw.z7  -o./zfailed_unz7_111_pw_z7

                    String command7z = Mac_Z7_PATH + " -y -p\"" + password.trim() + "\"  x  \"" + z7AbsPath + "\"  -o\"./" + targetDirName+"\"";
                    System.out.println("目标解压路径 7ZTargetFilePath = " + z7TargetFilePath);
                    System.out.println("目标解压路径 targetDirName = " + targetDirName);
                    System.out.println("command = \n" + command7z);

                    String commandResult = execCMD_Mac(command7z);
                    System.out.println("══════════════Begin ExE ");
                    System.out.println(commandResult);
                    System.out.println("══════════════End ExE ");
//               targetPassword = password;
                    try {
                        Thread.sleep(1000);   // 歇息1秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                    if (isTargetExist_BigBytes(targetFile)) {
                        isOK = true;
                        targetPassword = password;
                        //  如果成功 解压  当前 z7文件 含有 ZFailed_Unz7字样 那么重命名
                        z7File = fixedFailedName_BackCommon(z7File);
                        String fixedAbsPath =     z7File.getAbsolutePath().replace(UNZIP_PRE,"");
                        ZIP_Path_Password_Map.put(fixedAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                        System.out.println("══》 【 解压成功 】 7z 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + z7File.getName() + " 】");
                        break out;
                    } else {
                        // 如果解压  失败 那么 把 解压出来的 目录 删掉
                        System.out.println("command 执行失败  密码错误!");
                        deleteDirectory(targetFile.getAbsolutePath());
                    }


                }
            }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(z7File.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(z7File.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 7z 文件 :" + z7File.getName());

                if (!existUnknowz7List.contains(z7File) && !isTargetDirExist)
                    existUnknowz7List.add(z7File);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 z7 的 文件
            }
            System.out.println("══════════════" + " 解压7z文件:" + z7File.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowz7List);
        }

    }



    static void JieYa7Z_Windows(ArrayList<File> z7List,String type) {
        String z7_JiaYa_EndFlag="_"+type.replace(".","");  // 解压的文件夹 是以 _z7 为标识

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 z7 文件
        Set<File> existUnknowz7List = new HashSet<>();
        if (z7List == null) {
            return;
        }

        System.out.println("══════════════" + ".7z 文件个数:" + z7List.size() + "══════════════");

        for (int i = 0; i < z7List.size(); i++) {
            File z7File = z7List.get(i);
            String z7AbsPath = z7File.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            z7File = fixedFailedName(z7File);   // xxx.z7 文件
            int indexCount = i + 1;
            boolean isJiaMi = is7zEncrypted(z7File);
            String z7TargetFilePath = z7File.getParent() + File.separator + getFileNameNoPoint(z7File.getName())+z7_JiaYa_EndFlag;
           if(isAll_Search_Express){
               z7TargetFilePath = z7File.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(z7File.getName());
            }

            System.out.println("ZZZz7TargetFilePath = "+ z7TargetFilePath);
            //  把 ZFAILED_UNZIP_  去除 再检查  是否存在 目标文件
            z7TargetFilePath = z7TargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(z7TargetFilePath);  // 解压的文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");

            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);

            }

            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压 7z文件:" + z7File.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
                out:
                for (int j = 0; j < PasswordList.size(); j++) {
                    String password = PasswordList.get(j);
                    // 7z.exe -y -p111  x 111_pw.z7 -o./111_pw_z7       解压z7 密码
                    //
                    // WINEXE_Z7_PATH(7z执行文件) z7TargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                    // password(密码)     z7AbsPath( z7 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNz7_111_pw.z7  -o./zfailed_unz7_111_pw_z7

                    String command7z = WINEXE_Z7_PATH + " -y -p\"" + password.trim() + "\"  x  \"" + z7AbsPath + "\"  -o\"./" + targetDirName+"\"";
                    System.out.println("目标解压路径 7ZTargetFilePath = " + z7TargetFilePath);
                    System.out.println("目标解压路径 targetDirName = " + targetDirName);
                    System.out.println("command = \n" + command7z);

                    String commandResult = execCMD(command7z);
                    System.out.println("══════════════Begin ExE ");
                    System.out.println(commandResult);
                    System.out.println("══════════════End ExE ");
//               targetPassword = password;
                    try {
                        Thread.sleep(1000);   // 歇息1秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                    if (isTargetExist_BigBytes(targetFile)) {
                        isOK = true;
                        targetPassword = password;
                        //  如果成功 解压  当前 z7文件 含有 ZFailed_Unz7字样 那么重命名
                        z7File = fixedFailedName_BackCommon(z7File);
                    String fixedAbsPath =     z7File.getAbsolutePath().replace(UNZIP_PRE,"");
                        ZIP_Path_Password_Map.put(fixedAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                        System.out.println("══》 【 解压成功 】 7z 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + z7File.getName() + " 】");
                        break out;
                    } else {
                        // 如果解压  失败 那么 把 解压出来的 目录 删掉
                        System.out.println("command 执行失败  密码错误!");
                        deleteDirectory(targetFile.getAbsolutePath());
                    }


                }
            }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(z7File.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(z7File.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 7z 文件 :" + z7File.getName());

                if (!existUnknowz7List.contains(z7File) && !isTargetDirExist)
                    existUnknowz7List.add(z7File);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 z7 的 文件
            }
            System.out.println("══════════════" + " 解压7z文件:" + z7File.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowz7List);
        }

    }


    static void JieYa7Z(ArrayList<File> _7zList) {

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 zip 文件
        ArrayList<File> existUnknow_7zList = new ArrayList<File>();
        if (_7zList == null) {
            return;
        }

        System.out.println("══════════════" + ".7Z 文件大小:" + _7zList.size() + "══════════════");


        for (int i = 0; i < _7zList.size(); i++) {
            File _7zFile = _7zList.get(i);
            _7zFile = fixedFailedName(_7zFile);
            int indexCount = i + 1;
            boolean isJiami = is7zEncrypted(_7zFile);
            String zipTargetFilePath = _7zFile.getParent() + File.separator + getFileNameNoPoint(_7zFile.getName());
            File targetFile = new File(zipTargetFilePath);
            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压 .7z 文件:" + _7zFile.getName() + "  Begin" + "══════════════");
            out:
            for (int j = 0; j < PasswordList.size(); j++) {
                String password = PasswordList.get(j);
//               targetPassword = password;
                if (decompress7Z(password, _7zFile)) {
                    isOK = true;
                    targetPassword = password;
                    System.out.println("══》 【 解压成功 】 .7z 文件   " + (isJiami ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【 无密码锁】") + "    Path【 " + _7zFile.getName() + " 】");
                    break out;
                }


            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 zip 文件 :" + _7zFile.getName());
                if (!existUnknow_7zList.contains(_7zFile))
                    existUnknow_7zList.add(_7zFile);  //   保存那些 不知道密码的 zip 的 文件
            }
            System.out.println("══════════════" + " 解压zip文件:" + _7zFile.getName() + "  End" + isOkTip(isOK) + (isJiami ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknow_7zList);
        }

    }



    static void JieYaZip_Windows(ArrayList<File> zipList) {
        String zip_JiaYa_EndFlag="_zip";  // 解压的文件夹 是以 _zip 为标识

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 zip 文件
        Set<File> existUnknowZipList = new HashSet<File>();
        if (zipList == null) {
            return;
        }

        System.out.println("══════════════" + ".zip 文件大小:" + zipList.size() + "══════════════");

        for (int i = 0; i < zipList.size(); i++) {
            File zipFile = zipList.get(i);
            String zipAbsPath = zipFile.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            zipFile = fixedFailedName(zipFile);   // xxx.zip 文件
            int indexCount = i + 1;
//            boolean isJiaMi = isZipFileEncrypted(zipFile);
            boolean isJiaMi = true;
            String zipTargetFilePath = getFileNameNoPoint(zipFile.getName())+zip_JiaYa_EndFlag;
            if(isAll_Search_Express){
                zipTargetFilePath = zipFile.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(zipFile.getName());
            }
            System.out.println("ZZZzipTargetFilePath = "+ zipTargetFilePath);
         //  把 ZFAILED_UNZIP_  去除 再检查  是否存在 目标文件
            zipTargetFilePath = zipTargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(zipTargetFilePath);  // 解压的文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");
            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);
            }


            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压zip文件:" + zipFile.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
               out:
               for (int j = 0; j < PasswordList.size(); j++) {
                   String password = PasswordList.get(j);
                   // 7z.exe -y -p111  x 111_pw.zip -o./111_pw_zip       解压zip 密码
                   //
                   // WINEXE_Z7_PATH(7z执行文件) zipTargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                   // password(密码)     zipAbsPath( zip 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNZIP_111_pw.zip  -o./zfailed_unzip_111_pw_zip

                   String command7z = WINEXE_Z7_PATH + " -y -p\"" + password.trim() + "\"  x \"" + zipAbsPath + "\"  -o\"./" + targetDirName+"\"";
                   System.out.println("目标解压路径 zipTargetFilePath = " + zipTargetFilePath);
                   System.out.println("目标解压路径 targetDirName = " + targetDirName);

//目标解压路径 zipTargetFilePath = D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\cmder
//目标解压路径 targetDirName = cmder

                   System.out.println("command = \n" + command7z);

                   String commandResult = execCMD(command7z);
                   System.out.println("══════════════Begin ExE ");
                   System.out.println(commandResult);
                   System.out.println("══════════════End ExE ");
//               targetPassword = password;


                   // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                   if (isTargetExist_BigBytes(targetFile)) {
                       isOK = true;
                       targetPassword = password;
                       if(!"".equals(password)){
                           isJiaMi = true;
                       }else{
                           isJiaMi = false;
                       }

                       //  如果成功 解压  当前 zip文件 含有 ZFailed_UnZIP字样 那么重命名
                       zipFile = fixedFailedName_BackCommon(zipFile);
                       String fixedAbsPath =     zipFile.getAbsolutePath().replace(UNZIP_PRE,"");
                       ZIP_Path_Password_Map.put(fixedAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                       System.out.println("══》 【 解压成功 】 zip 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + zipFile.getName() + " 】");
                       break out;
                   } else {
                       isJiaMi = true;
                       // 如果解压  失败 那么 把 解压出来的 目录 删掉
                       System.out.println("command 执行失败  密码错误!");
                       System.out.println("删除 文件夹: "+ targetFile.getAbsolutePath());
                       deleteDirectory(targetFile.getAbsolutePath());
                   }


               }
           }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(zipFile.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(zipFile.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 zip 文件 :" + zipFile.getName());

                if (!existUnknowZipList.contains(zipFile) && !isTargetDirExist)
                    existUnknowZipList.add(zipFile);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 zip 的 文件
            }
            System.out.println("══════════════" + " 解压zip文件:" + zipFile.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowZipList);
        }

    }



    static void JieYaZip_MacOS(ArrayList<File> zipList) {
        String zip_JiaYa_EndFlag="_zip";  // 解压的文件夹 是以 _zip 为标识

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 zip 文件
        Set<File> existUnknowZipList = new HashSet<File>();
        if (zipList == null) {
            return;
        }

        System.out.println("══════════════" + ".zip 文件大小:" + zipList.size() + "══════════════");

        for (int i = 0; i < zipList.size(); i++) {
            File zipFile = zipList.get(i);
            String zipAbsPath = zipFile.getAbsolutePath();
            // windows  7z.exe  不需要 把 文件名 重置
//            zipFile = fixedFailedName(zipFile);   // xxx.zip 文件
            int indexCount = i + 1;
//            boolean isJiaMi = isZipFileEncrypted(zipFile);
            boolean isJiaMi = true;
            String zipTargetFilePath = getFileNameNoPoint(zipFile.getName())+zip_JiaYa_EndFlag;
            if(isAll_Search_Express){
                zipTargetFilePath = zipFile.getParentFile().getAbsolutePath() + File.separator + getFileNameNoPoint(zipFile.getName());
            }
            System.out.println("ZZZzipTargetFilePath = "+ zipTargetFilePath);
            //  把 ZFAILED_UNZIP_  去除 再检查  是否存在 目标文件
            zipTargetFilePath = zipTargetFilePath.replace(UNZIP_PRE,"");
            File targetFile = new File(zipTargetFilePath);  // 解压的文件夹
            String targetDirName = targetFile.getName().replace(UNZIP_PRE,"");
            if(isAll_Search_Express){
                String zipDirAbsPath = targetFile.getAbsolutePath();
                targetDirName = zipDirAbsPath.replace(CUR_Dir_FILE.getAbsolutePath(),"");
                targetDirName = targetDirName.replace(targetFile.getName(),"");
                targetDirName = targetDirName.replace(File.separator+File.separator,File.separator);
            }


            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压zip文件:" + zipFile.getName() + "  Begin" + "══════════════");
            System.out.println("目标目录是否存在:" + isTargetDirExist);
            if(!isTargetDirExist) {
                out:
                for (int j = 0; j < PasswordList.size(); j++) {
                    String password = PasswordList.get(j);
                    // 7z.exe -y -p111  x 111_pw.zip -o./111_pw_zip       解压zip 密码
                    //
                    // WINEXE_Z7_PATH(7z执行文件) zipTargetFilePath(目标文件夹_绝对路径) targetDirName(目标文件夹名称)
                    // password(密码)     zipAbsPath( zip 文件的绝对路径)

//                C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p111  x C:\Users\zhuzj5\Desktop\H5\111\3\ZFAILED_UNZIP_111_pw.zip  -o./zfailed_unzip_111_pw_zip

                    String command7z = Mac_Z7_PATH + " -y -p\"" + password.trim() + "\"  x \"" + zipAbsPath + "\"  -o\"./" + targetDirName+"\"";
                    System.out.println("目标解压路径 zipTargetFilePath = " + zipTargetFilePath);
                    System.out.println("目标解压路径 targetDirName = " + targetDirName);

//目标解压路径 zipTargetFilePath = D:\Temp\ZWin_SoftWare_20200911\ZWin_Software\A0_Pre_Install_Soft\cmder
//目标解压路径 targetDirName = cmder

                    System.out.println("command = \n" + command7z);

                    String commandResult = execCMD_Mac(command7z);
                    System.out.println("══════════════Begin ExE ");
                    System.out.println(commandResult);
                    System.out.println("══════════════End ExE ");
//               targetPassword = password;


                    // 解压完成后  查看当前目标目录是否存在  大小是否大于 10 字节
                    if (isTargetExist_BigBytes(targetFile)) {
                        isOK = true;
                        targetPassword = password;
                        if(!"".equals(password)){
                            isJiaMi = true;
                        }else{
                            isJiaMi = false;
                        }

                        //  如果成功 解压  当前 zip文件 含有 ZFailed_UnZIP字样 那么重命名
                        zipFile = fixedFailedName_BackCommon(zipFile);
                        String fixedAbsPath =     zipFile.getAbsolutePath().replace(UNZIP_PRE,"");
                        ZIP_Path_Password_Map.put(fixedAbsPath,"".equals(targetPassword.trim())?"无密码":targetPassword);
                        System.out.println("══》 【 解压成功 】 zip 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + zipFile.getName() + " 】");
                        break out;
                    } else {
                        isJiaMi = true;
                        // 如果解压  失败 那么 把 解压出来的 目录 删掉
                        System.out.println("command 执行失败  密码错误!");
                        System.out.println("删除 文件夹: "+ targetFile.getAbsolutePath());
                        deleteDirectory(targetFile.getAbsolutePath());
                    }


                }
            }else{
                if(isJiaMi){
                    ZIP_Path_Password_Map.put(zipFile.getAbsolutePath(),"已有解压文件夹-不知密码");
                }else{
                    ZIP_Path_Password_Map.put(zipFile.getAbsolutePath(),"无密码");
                }
            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 zip 文件 :" + zipFile.getName());

                if (!existUnknowZipList.contains(zipFile) && !isTargetDirExist)
                    existUnknowZipList.add(zipFile);  //   保存那些 不知道密码的  并且没有对应解压缩文件夹的 zip 的 文件
            }
            System.out.println("══════════════" + " 解压zip文件:" + zipFile.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowZipList);
        }

    }



    static void JieYaZip(ArrayList<File> zipList) {

        // 保存那些  无法依据  输入的 密码 还是无法保存的那些 zip 文件
        ArrayList<File> existUnknowZipList = new ArrayList<File>();
        if (zipList == null) {
            return;
        }

        System.out.println("══════════════" + ".zip 文件大小:" + zipList.size() + "══════════════");

        for (int i = 0; i < zipList.size(); i++) {
            File zipFile = zipList.get(i);
            zipFile = fixedFailedName(zipFile);
            int indexCount = i + 1;
            boolean isJiaMi = isZipFileEncrypted(zipFile);
            String zipTargetFilePath = zipFile.getParent() + File.separator + getFileNameNoPoint(zipFile.getName());
            File targetFile = new File(zipTargetFilePath);
            boolean isTargetDirExist = isTargetExist_BigBytes(targetFile);
            boolean isOK = false;
            String targetPassword = "";
            System.out.println(indexCount + " <<<<<<<<<<<<<<<<<<< " + " 解压zip文件:" + zipFile.getName() + "  Begin" + "══════════════");
            out:
            for (int j = 0; j < PasswordList.size(); j++) {
                String password = PasswordList.get(j);
//               targetPassword = password;
                if (decompressZIP(password, zipFile)) {
                    isOK = true;
                    targetPassword = password;
                    System.out.println("══》 【 解压成功 】 zip 文件  Password【 " + (isJiaMi ? targetPassword : "") + " 】   Path【 " + zipFile.getName() + " 】");
                    break out;
                }


            }
            if (!isOK) {
                System.out.println("══》【 所有密码解压失败 】 zip 文件 :" + zipFile.getName());
                if (!existUnknowZipList.contains(zipFile))
                    existUnknowZipList.add(zipFile);  //   保存那些 不知道密码的 zip 的 文件
            }
            System.out.println("══════════════" + " 解压zip文件:" + zipFile.getName() + "  End" + isOkTip(isOK) + (isJiaMi ? (isTargetDirExist ? "password【加密文件 但已解压 不知密码】" : showPassword(targetPassword)) : " password【无密码锁】") + "══════════════");
            System.out.println();
            UnKnowFileList.addAll(existUnknowZipList);
        }

    }

    static String showPassword(String pass) {
        String result = "  password【 " + ("".equals(pass) ? "密码错误" : pass) + " 】";

        return result;
    }


    static String isOkTip(boolean isOk) {
        String result = "";
        if (isOk) {
            result = "【 解压成功 】";
        } else {
            result = "【 解压失败 】";
        }
        return result;
    }


    // 使用 三方的 exe 执行文件 去解析  rar zip  7z
    public static void tryJieYaOperationWithExe() {
        if(CUR_OS_TYPE ==OS_TYPE.Windows){

            tryJieYaOperation_Windows();
        } else if(CUR_OS_TYPE ==OS_TYPE.Linux) {
//  Linux 的  解压方式  未实现
            System.out.println("未实现 Linux的 解压方法!");

        } else if(CUR_OS_TYPE ==OS_TYPE.MacOS){
//  MacOS 的  解压方式  未实现
            System.out.println("未实现 MacOS的 解压方法!");
            tryJieYaOperation_MacOS();
        }

    }



    public static void tryJieYaOperation_MacOS() {
        for (int i = 0; i < zipFileTypeList.size(); i++) {
            String mFileType = zipFileTypeList.get(i);

            if (".zip".equals(mFileType)) {
                ArrayList<File> zipList = arrFileMap.get(mFileType);
//                JieYaZip_Windows(zipList);
                JieYaZip_MacOS(zipList);

            } else if (".7z".equals(mFileType) || ".gz".equals(mFileType) || ".tar".equals(mFileType)) {
                ArrayList<File> z7_List = arrFileMap.get(mFileType);
                JieYa7Z_MacOS(z7_List,mFileType);

            } else if (".rar".equals(mFileType)) {
                ArrayList<File> rarList = arrFileMap.get(mFileType);
//                System.out.println("RAR 文件个数: "+ rarList.size());
                JieYaRar_MacOS(rarList);
            }
            System.out.println("index["+i+"]   mFileType  =" + mFileType);

        }


    }


    public static void tryJieYaOperation_Windows() {
        for (int i = 0; i < zipFileTypeList.size(); i++) {
            String mFileType = zipFileTypeList.get(i);

            if (".zip".equals(mFileType)) {
                ArrayList<File> zipList = arrFileMap.get(mFileType);
                JieYaZip_Windows(zipList);
            } else if (".7z".equals(mFileType) || ".gz".equals(mFileType) || ".tar".equals(mFileType)) {
                ArrayList<File> z7_List = arrFileMap.get(mFileType);
              JieYa7Z_Windows(z7_List,mFileType);
            } else if (".rar".equals(mFileType)) {
                ArrayList<File> rarList = arrFileMap.get(mFileType);
//                System.out.println("RAR 文件个数: "+ rarList.size());
                JieYaRar_Windows(rarList);
            }
//            System.out.println(" mFileType  =" + mFileType);

        }


    }


    //   使用 代码 解压 zip   7z

    public static void tryJieYaOperationWithCode() {
        for (int i = 0; i < zipFileTypeList.size(); i++) {
            String mFileType = zipFileTypeList.get(i);

            if (".zip".equals(mFileType)) {
                ArrayList<File> zipList = arrFileMap.get(mFileType);
                JieYaZip(zipList);
            } else if (".7z".equals(mFileType)) {
                ArrayList<File> z7_List = arrFileMap.get(mFileType);
                JieYa7Z(z7_List);
            }

      // 暂时不处理 rar 文件

            else if (".rar".equals(mFileType)) {
                ArrayList<File> rarList = arrFileMap.get(mFileType);
                JieYaRar(rarList);
            }

        }

    }

    @SuppressWarnings("unchecked")
    public static void showMapSummaryData() {
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                System.out.println("文件类型:" + getFixedType(typeStr) + "  匹配文件个数:" + fileArr.size());
            }
        }
    }

    static String getFixedType(String type) {
        // type 最长保留10位
        String fixedType = "";
        int curLength = type.length();
        if (curLength < 6) {
            int blankIndex = 6 - curLength;
            for (int i = 0; i < blankIndex; i++) {
                fixedType = " " + fixedType;
            }

        }

        return type + fixedType;
    }

    /**
     * 获取文件真实类型
     *
     * @param file 要获取类型的文件。
     * @return 文件类型枚举。
     */
    private static FileType getFileType(File file) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileType.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileType.ZIP;
                case 0x776f7264:
                    return FileType.TAR;
                case -0x51:
                    return FileType._7Z;
                case 0x425a6839:
                    return FileType.BZ2;
                case -0x74f7f8:
                    return FileType.GZ;
                case 0x52617221:
                    return FileType.RAR;
                default:
                    return FileType.UNKNOWN;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return FileType.UNKNOWN;
    }


    /**
     * 构建目录
     *
     * @param outputDir 输出目录
     * @param subDir    子目录
     */
    private static void createDirectory(String outputDir, String subDir) {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    /**
     * 解压缩tar文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static void decompressTar(File file, String targetPath, boolean delete) {
        FileInputStream fis = null;
        OutputStream fos = null;
        TarInputStream tarInputStream = null;
        try {
            fis = new FileInputStream(file);

            tarInputStream = new TarInputStream(fis, 1024 * 2);
            // 创建输出目录

            createDirectory(targetPath, null);

            TarEntry entry = null;
            while (true) {
                entry = tarInputStream.getNextEntry();
                if (entry == null) {
                    break;
                }
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int count;
                    byte data[] = new byte[2048];
                    while ((count = tarInputStream.read(data)) != -1) {
                        fos.write(data, 0, count);
                    }
                    fos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (tarInputStream != null) {
                    tarInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解压缩tar.bz2文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    public static void decompressTarBz2(File file, String targetPath, boolean delete) {
        FileInputStream fis = null;
        OutputStream fos = null;
        BZip2CompressorInputStream bis = null;
        TarInputStream tis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BZip2CompressorInputStream(fis);
            tis = new TarInputStream(bis, 1024 * 2);
            // 创建输出目录
            createDirectory(targetPath, null);
            TarEntry entry;
            while ((entry = tis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int count;
                    byte data[] = new byte[2048];
                    while ((count = tis.read(data)) != -1) {
                        fos.write(data, 0, count);
                    }
                    fos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (tis != null) {
                    tis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解压缩tar.gz文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static void decompressTarGz(File file, String targetPath, boolean delete) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        GZIPInputStream gzipIn = null;
        TarInputStream tarIn = null;
        OutputStream out = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            gzipIn = new GZIPInputStream(bufferedInputStream);
            tarIn = new TarInputStream(gzipIn, 1024 * 2);

            // 创建输出目录
            createDirectory(targetPath, null);

            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {
                if (entry.isDirectory()) { // 是目录
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else { // 是文件
                    File tempFIle = new File(targetPath + File.separator + entry.getName());
                    createDirectory(tempFIle.getParent() + File.separator, null);
                    out = new FileOutputStream(tempFIle);
                    int len = 0;
                    byte[] b = new byte[2048];

                    while ((len = tarIn.read(b)) != -1) {
                        out.write(b, 0, len);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (tarIn != null) {
                    tarIn.close();
                }
                if (gzipIn != null) {
                    gzipIn.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解压缩gz文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static void decompressGz(File file, String targetPath, boolean delete) {
        FileInputStream fileInputStream = null;
        GZIPInputStream gzipIn = null;
        OutputStream out = null;
        String suffix = ".gz";
        try {
            fileInputStream = new FileInputStream(file);
            gzipIn = new GZIPInputStream(fileInputStream);
            // 创建输出目录
            createDirectory(targetPath, null);

            File tempFile = new File(targetPath + File.separator + file.getName().replace(suffix, ""));
            out = new FileOutputStream(tempFile);
            int count;
            byte data[] = new byte[2048];
            while ((count = gzipIn.read(data)) != -1) {
                out.write(data, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (gzipIn != null) {
                    gzipIn.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

// WinRAR.exe   x -p888888 -y 888888.rar ./888888
    // https://www.cnblogs.com/zhang90030/p/9686127.html
    //   使用 命令 解压 RAR 比较合适
    public static boolean isRarEncrypted(File rarFile) {
        boolean flag = false;
        try {
            Archive archive = new Archive(rarFile);
            boolean isOldFormat = archive.isOldFormat();
            System.out.println(" isOldFormat = "+isOldFormat);

            if(archive.getMainHeader() == null ){
                System.out.println("isRarEncrypted  中 getMainHeader() == null");
                return false;
            }
            System.out.println(" isOldFormatXX2 = "+isOldFormat);
            flag = archive.isEncrypted();
        }catch (RarException e){
            System.out.println("RarException  e.type="+ e.getType());
            System.out.println(" RarException  flag= "+flag);
            if(e.getType() == RarException.RarExceptionType.rarEncryptedException){
                flag = true;  //  抛出  RarException.RarExceptionType.rarEncryptedException 异常 那么 就是 有密码的
            }


        }catch (IOException e){
            System.out.println("isRarEncrypted  IOException ");
        }
        System.out.println("Path:"+rarFile.getAbsolutePath()+"  isJiamia = "+flag);
        return flag;
    }

    public static boolean decompressRar(String password, File RarFile) {
        boolean executeSuccess = false;
        String resultStr = "";
        String targetPath = "";
        File targetFile = null;
        boolean isAlredyExist = false;   //  是否已经存在 解压后的文件
        try {


//            Archive  archive = new Archive(RarFile);


            targetPath = RarFile.getParent() + File.separator + getFileNameNoPoint(RarFile.getName());
            targetFile = new File(targetPath);

            // 目标文件夹 是否存在 SHELL 目录下
            boolean isExistTargetDir = checkExistDir(targetFile);

            long targetByte = isExistTargetDir ? getTotalSizeOfFilesInDir(targetFile) : 0L;

            if (isExistTargetDir && targetByte > 10) {
                isAlredyExist = true;
            }
            if (isAlredyExist) {
                System.out.println("当前Rar文件 加压文件夹 已经存在 不需要解压!  Path:" + RarFile.getAbsolutePath());
                return true;
            }

            // 检查当前
            if (isRarEncrypted(RarFile)) {//需要密码
//                zf.setPassword(password);
                System.out.println("使用密码Rar文件: " + password + "   解压文件:" + RarFile.getAbsolutePath());

                resultStr = decompressRAR_Password(RarFile.getAbsolutePath(), targetPath, password);

                if ("解压成功".equals(resultStr)) {
                    executeSuccess = true;
                }


            } else {
                System.out.println("非密码Rar文件  解压文件:" + RarFile.getAbsolutePath());
                executeSuccess = decompressRAR_NoPassword(RarFile, targetPath, false);

            }

            if (executeSuccess) {

                executeSuccess = true;
            } else {
                targetFile = new File(targetPath);
                if (targetFile.exists()) {
                    DeleteFolder(targetFile.getAbsolutePath());
                    System.out.println("decompressRar 解压失败 删除解压出目录! ");
                }

            }


        } catch (Exception e) {
            System.out.println("Rar 文件执行异常!");
            return false;
        }

        return executeSuccess;

    }


    public static boolean isZipFileEncrypted(File zipFile) {
        boolean flag = false;
        try {
            flag = new ZipFile(zipFile).isEncrypted();

        } catch (ZipException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean isPasswordRight(File zipFile,String password) {
        boolean flag = false;
        try {
            ZipFile mZipFile =    new ZipFile(zipFile);
            mZipFile.setPassword(password);

            flag = mZipFile.isEncrypted();
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean decompress7Z(String password, File _7ZFile) {
        boolean executeSuccess = false;
        String resultStr = "";
        String targetPath = "";
        File targetFile = null;
        boolean isAlredyExist = false;   //  是否已经存在 解压后的文件
        try {

/*   7z  7Z   不能相互切换
            7ZFile zf = new 7ZFile(7ZFile);

            if(!zf.isValid7ZFile()){
                System.out.println("该7Z文件已损坏!   PATH:"+ 7ZFile.getAbsolutePath());
                return false;
            }

            */
            targetPath = _7ZFile.getParent() + File.separator + getFileNameNoPoint(_7ZFile.getName());
            targetFile = new File(targetPath);

            // 目标文件夹 是否存在
            boolean isExistTargetDir = checkExistDir(targetFile);
            long targetByte = isExistTargetDir ? getTotalSizeOfFilesInDir(targetFile) : 0L;

            if (isExistTargetDir && targetByte > 10) {
                isAlredyExist = true;
            }
            if (isAlredyExist) {
                System.out.println("当前7Z文件 加压文件夹 已经存在 不需要解压!  Path:" + _7ZFile.getAbsolutePath());
                return true;
            }

            // 检查当前
            if (is7zEncrypted(_7ZFile)) {//需要密码
//                zf.setPassword(password);
                System.out.println("使用密码解压7Z文件:  密码: " + password + "   解压文件:" + _7ZFile.getAbsolutePath());

                executeSuccess = decompress7Z_Password(_7ZFile.getAbsolutePath(), targetPath, password);

            } else {
                System.out.println("非密码加压7Z文件  解压文件:" + _7ZFile.getAbsolutePath());
                executeSuccess = decompress7Z_NoPassword(_7ZFile, targetPath, false);

            }

            if (executeSuccess) {

                executeSuccess = true;
            } else {
                targetFile = new File(targetPath);
                if (targetFile.exists()) {
                    DeleteFolder(targetFile.getAbsolutePath());
                    System.out.println("decompress7Z 解压失败 删除解压出目录! ");
                }

            }


        } catch (Exception e) {
            System.out.println("7Z 文件执行异常! ");
            e.printStackTrace();
            return false;
        }

        return executeSuccess;

    }

    static boolean is7zEncrypted(File _7zFile) {
        boolean is7zEncrypted = false;

        boolean isOk = false;
        IInArchive archive = null;
        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(_7zFile, "r");
//            SevenZip.initSevenZipFromPlatformJAR();
            SevenZip.initSevenZipFromPlatformJAR();
//            SevenZip.initSevenZipFromPlatformJAR(zbinPath+File.separator+"H5_sevenzipjbinding-9.20.jar");


            archive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile), "");
            ISimpleInArchive simpleInArchive = archive.getSimpleInterface();
            for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
//            item.isEncrypted();  // 是否加密
                is7zEncrypted = is7zEncrypted || item.isEncrypted();
            }
//            simpleInArchive.close();
        } catch (SevenZipException exception){

            System.out.println("is7zEncrypted   发生SevenZipException异常!  默认是加密 is7zEncrypted = true ");
            is7zEncrypted = true;   //  发生了 异常   那么假设它似乎加密的
            try {
                randomAccessFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Exception  is7zEncrypted 发生异常! ");
            e.printStackTrace();
        } finally {
            try {

                randomAccessFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return is7zEncrypted;
    }

    public static boolean decompressZIP(String password, File zipFile) {
        boolean executeSuccess = false;
        String resultStr = "";
        String targetPath = "";
        File targetFile = null;
        boolean isAlredyExist = false;   //  是否已经存在 解压后的文件
        try {
            ZipFile zf = new ZipFile(zipFile);

            if (!zf.isValidZipFile()) {
                System.out.println("该zip文件已损坏!   PATH:" + zipFile.getAbsolutePath());
                return false;
            }

            targetPath = zipFile.getParent() + File.separator + getFileNameNoPoint(zipFile.getName());
            targetFile = new File(targetPath);

            // 目标文件夹 是否存在
            boolean isExistTargetDir = checkExistDir(targetFile);
            long targetByte = isExistTargetDir ? getTotalSizeOfFilesInDir(targetFile) : 0L;

            if (isExistTargetDir && targetByte > 10) {
                isAlredyExist = true;
            }
            if (isAlredyExist) {
                System.out.println("当前zip文件 加压文件夹 已经存在 不需要解压!  Path:" + zipFile.getAbsolutePath());
                return true;
            }

            // 检查当前
            if (zf.isEncrypted()) {//需要密码
//                zf.setPassword(password);
                System.out.println("使用密码: " + password + "   解压文件:" + zipFile.getAbsolutePath());

                resultStr = decompressZIP_Password(zipFile.getAbsolutePath(), targetPath, false, password);

            } else {
                System.out.println("非密码zip文件  解压文件:" + zipFile.getAbsolutePath());
                resultStr = decompressZIP_EmptyPassword(zipFile.getAbsolutePath(), targetPath, false);

            }

            if ("解压成功".equals(resultStr)) {

                executeSuccess = true;
            } else {
                targetFile = new File(targetPath);
                if (targetFile.exists()) {
                    DeleteFolder(targetFile.getAbsolutePath());
                    System.out.println("decompressZIP 解压失败 删除解压出目录! ");
                }

            }


        } catch (Exception e) {
            System.out.println("zip 文件执行异常!");
            return false;
        }


        return executeSuccess;

    }


    static boolean isTargetExist_BigBytes(File targetFile) {
        boolean flag = false;
        // 目标文件夹 是否存在
        boolean isExistTargetDir = checkExistDir(targetFile);
        if(isAll_Search_Express){
            isExistTargetDir = targetFile.exists();
        }
        long targetByte = isExistTargetDir ? getTotalSizeOfFilesInDir(targetFile) : 0L;

        if (isExistTargetDir && targetByte > 3) {
            System.out.println("isExistTargetDir"+isExistTargetDir+"  isTargetExist_BigBytes = true "+"targetFile = "+ targetFile.getAbsolutePath() + "  size = "+ targetByte);
            flag = true;
        }
        System.out.println("isExistTargetDir"+isExistTargetDir+"  isTargetExist_BigBytes = false "+"targetFile = "+ targetFile.getAbsolutePath() + "  size = "+ targetByte);

        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
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

    public static String getFileTypeWithPoint(String fileName) {
        String name = "";
        if (fileName.contains(".")) {
            name = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
        } else {
            name = "";
        }
        return name.toLowerCase().trim();
    }

    public static String decompressZIP_EmptyPassword(String srcPath, String destPath, boolean delete) {
        File zipFile = new File(srcPath);
        try {
            ZipFile zf = new ZipFile(zipFile);
            zf.setFileNameCharset("GBK");
            if (!zf.isValidZipFile()) {
                return "该zip文件已损坏";
            }
            if (zf.isEncrypted()) {//需要密码
//                zf.setPassword(password);
                return "该文件是加密zip文件 需要使用密码解密!";
            }
            zf.extractAll(destPath);
            return "解压成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 【1】
    // .rar 文件解压缩
    // void decompressRAR(File file, String targetPath,  boolean delete)   解压缩RAR文件
    //  boolean delete 解压后是否删除原压缩包文件
    //  file 压缩包文件
    //  String targetPath 目标文件夹

    // 【2】
    // .7z 文件解压缩
    // void decompressRAR(File file, String targetPath,  boolean delete)   解压缩RAR文件
    //  boolean delete 解压后是否删除原压缩包文件
    //  file 压缩包文件
    //  String targetPath 目标文件夹
    // 【3】
    // .zip 文件解压缩
    // void decompressRAR(File file, String targetPath,  boolean delete)   解压缩RAR文件
    //  boolean delete 解压后是否删除原压缩包文件
    //  file 压缩包文件
    //  String targetPath 目标文件夹


    // 递归方式 计算文件的大小  当大于100时  就直接返回 11  避免大文件 的耗时计算
    static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();

        long total = 0;
        if (children != null)
            for (final File child : children){
                total += getTotalSizeOfFilesInDir(child);
                if(total > 10 ){
                    return 11;
                }
            }

        return total;

    }


    //  检查 shell 目录下是否有 对应 解压缩的的文件
    static boolean checkExistDir(File targetDir) {
        boolean isExist = false;
        String targetDirName = targetDir.getName();
        File[] shellDir = CUR_Dir_FILE.listFiles();
        for (int i = 0; i < shellDir.length; i++) {
            File fileItem = shellDir[i];
            if (fileItem.isDirectory()) {
                String dirName = fileItem.getName();
                if (dirName.equals(targetDirName)) {
                    isExist = true;
                    return isExist;
                }
            }
        }

        return isExist;

    }

    public static String decompressZIP_Password(String srcPath, String destPath, boolean delete, String password) {
        File zipFile = new File(srcPath);
        File destFileDir = new File(destPath);
        try {
            ZipFile zf = new ZipFile(zipFile);
            FilterOptions a = null;
            zf.setFileNameCharset("GBK");
            if (!zf.isValidZipFile()) {
                return "该zip文件已损坏";
            }
            if (zf.isEncrypted()) {//需要密码
                zf.setPassword(password);
            }
            zf.extractAll(destPath);
            return "解压成功";
        } catch (Exception e) {
//            System.out.println("decompressZIP_Password 解压失败! ");
            return e.getMessage();
        }
    }

    // 【1】
    // .rar 文件解压缩
    // void decompressRAR(File file, String targetPath,  boolean delete)   解压缩RAR文件
    //  boolean delete 解压后是否删除原压缩包文件
    //  file 压缩包文件
    //  String targetPath 目标文件夹

    // 【2】
    // .7z 文件解压缩
    // void decompressRAR(File file, String targetPath,  boolean delete)   解压缩RAR文件
    //  boolean delete 解压后是否删除原压缩包文件
    //  file 压缩包文件
    //  String targetPath 目标文件夹

    /**
     * 解压缩7z文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static boolean decompress7Z_NoPassword(File file, String targetPath, boolean delete) {
        SevenZFile sevenZFile = null;
        boolean isOk = false;
        OutputStream outputStream = null;
        try {
            sevenZFile = new SevenZFile(file);
            // 创建输出目录
            createDirectory(targetPath, null);
            SevenZArchiveEntry entry;

            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    createDirectory(targetPath, entry.getName()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
                    int len = 0;
                    byte[] b = new byte[2048];
                    while ((len = sevenZFile.read(b)) != -1) {
                        outputStream.write(b, 0, len);
                    }
                    outputStream.flush();
                }
            }
            isOk = true;
        } catch (IOException e) {
            e.printStackTrace();
            isOk = false;
        } finally {
            try {
                if (sevenZFile != null) {
                    sevenZFile.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isOk;
    }

// 7z  rar   zip  三种格式的 普通解压    密码解压

    public static boolean decompress7Z_Password(String file7zPath, final String outPutPath, String passWord)  {
        boolean isOk = false;
        StringBuilder sbInfo = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            IInArchive archive;

            randomAccessFile = new RandomAccessFile(file7zPath, "r");
            archive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile), passWord);
            int numberOfItems = archive.getNumberOfItems();
            String targetDirName = getFileNameNoPoint(file7zPath);


            ISimpleInArchive simpleInArchive = archive.getSimpleInterface();
//        System.out.println("targetDirNameaA = "+ targetDirName);
            for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
//            item.isEncrypted();  // 是否加密
//            System.out.println("outPutPath = "+ outPutPath);

//  压缩文件的前缀
//            System.out.println("item.getPath() = "+ item.getPath());
//            System.out.println("targetDirNameB = "+ targetDirName);

                final int[] hash = new int[]{0};
                if (!item.isFolder()) {
                    ExtractOperationResult result;
                    final long[] sizeArray = new long[1];

                    result = item.extractSlow(new ISequentialOutStream() {
                        public int write(byte[] data) throws SevenZipException {
                            System.out.println("byte[] data = 字节数量 "+data.length);
                            FileOutputStream outputStream = null;
                            try {

                                // dir_shell 目录

                                File z7File = new File(targetDirName + File.separator + item.getPath());
                                if (!z7File.exists()) {
                                    z7File.getParentFile().mkdirs();
//                                z7File.createNewFile();
                                }

                                outputStream = new FileOutputStream(z7File, true);
                                outputStream.write(data);
//                                outputStream.close();
                            } catch (Exception e) {
                                System.out.println("decompress7Z_Password FileOutputStream异常1!");
                                e.printStackTrace();
                            }finally {
//                                System.out.println("decompress7Z_Password FileOutputStream异常2!");
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            hash[0] ^= Arrays.hashCode(data); // Consume data
                            sizeArray[0] += data.length;

                            return data.length; // Return amount of consumed
                        }
                    }, passWord);
                    if (result == ExtractOperationResult.OK) {
                        isOk = isOk || true;
                    System.out.println("解压成功: " +String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                        //     logger.error("解压成功...." +String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
// LogUtil.getLog().debug(String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                    } else {
//                    System.out.println("解压失败: "+result);
//                    System.out.println("result:"+result);
                        sbInfo.append("result=" + result + "\n");
                        isOk = isOk || false;
                        // logger.error("解压失败：密码错误或者其他错误...." +result);
// LogUtil.getLog().debug("Error extracting item: " + result);
                    }
                }

            }
            archive.close();
            randomAccessFile.close();
        } catch (Exception e){
            System.out.println("decompress7Z_Password  异常 密码错误!");

            isOk = false;

        } finally {
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

//        System.out.println("simpleInArchive.close     archive.close   randomAccessFile.close ");
        if (isOk) {
            System.out.println("解压成功 ");
        } else {
            System.out.println("解压失败:  result=" + sbInfo);
        }

        return isOk;
    }


    /**
     * 解压rar
     */
    public static String decompressRAR_Password(String srcPath, String destPath, String password) {
        if (!destPath.endsWith("/")) {
            destPath = destPath + "/";
        }
        File srcFile = new File(srcPath);
        FileOutputStream fileOut = null;
        de.innosystec.unrar.Archive fileArchive = null;
        try {
            fileArchive = new de.innosystec.unrar.Archive(srcFile, password, false);
            int total = fileArchive.getFileHeaders().size();
            if (total == 0) return "该压缩包为rar5，暂不支持";
            for (int i = 0; i < total; i++) {
                de.innosystec.unrar.rarfile.FileHeader fh = fileArchive.getFileHeaders().get(i);
                String entryPath = "";
                if (fh.isUnicode()) {//解決中文乱码
                    entryPath = fh.getFileNameW().trim();
                } else {
                    entryPath = fh.getFileNameString().trim();
                }
                entryPath = entryPath.replaceAll("\\\\", "/");
                File file = new File(destPath + entryPath);
                if (fh.isDirectory()) {
                    file.mkdirs();
                } else {
                    File parent = file.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }
                    fileOut = new FileOutputStream(file);
                    fileArchive.extractFile(fh, fileOut);
                    fileOut.close();
                    fileOut = null;
                }
            }
            return "解压成功";
        } catch (Exception e) {
            return "解压失败";
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
                if (fileArchive != null) {
                    fileArchive.close();
                }
            } catch (IOException e) {
                System.out.println("msg: " + e.getMessage());
            }
        }
    }


    /**
     * 解压缩RAR文件
     *
     * @param file       压缩包文件
     * @param targetPath 目标文件夹
     * @param delete     解压后是否删除原压缩包文件
     */
    private static boolean decompressRAR_NoPassword(File file, String targetPath, boolean delete) {
        boolean flag = false;
        Archive archive = null;
        OutputStream outputStream = null;
        try {
            archive = new Archive(file);
            FileHeader fileHeader;
            // 创建输出目录
            createDirectory(targetPath, null);
            while ((fileHeader = archive.nextFileHeader()) != null) {
                if (fileHeader.isDirectory()) {
                    createDirectory(targetPath, fileHeader.getFileNameString().trim()); // 创建子目录
                } else {
                    outputStream = new FileOutputStream(new File(targetPath + File.separator + fileHeader.getFileNameString().trim()));
                    archive.extractFile(fileHeader, outputStream);
                    flag = flag || true;
                }
            }
        } catch (RarException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (archive != null) {
                    archive.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
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
            if (!H5_Properties_File.exists()) {
                H5_Properties_File.createNewFile();
            }
            H5_Properties_InputStream = new BufferedInputStream(new FileInputStream(H5_Properties_File.getAbsolutePath()));
            H5_Properties.load(H5_Properties_InputStream);
            Iterator<String> it = H5_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + H5_Properties.getProperty(key));
                propKey2ValueList.put(key, H5_Properties.getProperty(key));
            }
            H5_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            H5_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(H5_Properties_File.getAbsolutePath()));
            H5_Properties.store(H5_Properties_OutputStream, "");
            H5_Properties_OutputStream.close();
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


    static String getPaddingSizeString( int padinglength, String oneStr) {
        String result = ""  ;
            for (int i = 0; i < padinglength; i++) {
                    result = result + oneStr;
                }
        return result;

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


    static ArrayList<File> getAllSubFile(File rootPath) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath.getAbsolutePath());

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();

                      allFile.add(new File(fileString));


                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


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
        System.out.println("用户输入的参数为空  或者 参数不符合规格  请参考如下 重新输入:");
        System.out.println();  // 输入 提示  输入格式 提示
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
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





    /**
     * 执行 mac(unix) 脚本命令~
     * @param command
     * @return
     */
    public static String execCMD_Mac(String command) {
        String[] cmd = {"/bin/bash"};
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = rt.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打开流
        OutputStream os = proc.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        try {
            bw.write(command);

            bw.flush();
            bw.close();

            /** 真奇怪，把控制台的输出打印一遍之后竟然能正常终止了~ */
//            readConsole(proc);

            /** waitFor() 的作用在于 java 程序是否等待 Terminal 执行脚本完毕~ */
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int retCode = proc.exitValue();
        if (retCode != 0) {
            System.out.println("unix script retCode = " + retCode);

//            System.out.println(readConsole(proc));
            System.out.println("UnixScriptUil.execute 出错了!!");
        }
        return retCode+"";
    }

    public static String execCMD(String command) {
//        System.out.println("══════════════Begin ExE ");
        StringBuilder sb =new StringBuilder();
        StringBuilder errorSb =new StringBuilder();
        try {

            Process process=Runtime.getRuntime().exec("CMD.exe /c start /B "+command);

            InputStreamReader inputReader =  new InputStreamReader(process.getInputStream(),"GBK");
            BufferedReader bufferedReader=new BufferedReader(inputReader);
            String line;
            int waitFor =   process.waitFor();
//            Stream<String> lines = bufferedReader.lines();
//            lines.iterator();
//            System.out.println("line Count = "+lines.count());

            while((line=bufferedReader.readLine())!=null  )
            {
                sb.append(line+"\n");

            }



          boolean isAlive =   process.isAlive();
           int errorSteamCode =  process.getErrorStream().read();

         String errorStream =    process.getErrorStream().toString();
         int exitValue =    process.exitValue();
//            process.getErrorStream().
            //杀掉进程
//            System.out.println("exitValue ="+ exitValue);
            sb.append("\nexitValue = "+ exitValue+
                    "\nisAlive = "+ isAlive+
                    "\nerrorStream = "+ errorStream+
                    "\nerrorSteamCode = "+ errorSteamCode+
                    "\nwaitFor = "+waitFor);
//            process.destroy();

        } catch (Exception e) {
            System.out.println("execCMD 出现异常! ");
            return e.toString();
        }

//        System.out.println("sb.toString() = "+ sb.toString());
//        System.out.println("══════════════End ExE ");
        return sb.toString();
    }

}



/*

7-Zip (a) 18.05 (x86) : Copyright (c) 1999-2018 Igor Pavlov : 2018-04-30

Usage: 7za <command> [<switches>...] <archive_name> [<file_names>...]

<Commands>
  a : Add files to archive
  b : Benchmark
  d : Delete files from archive
  e : Extract files from archive (without using directory names)
  h : Calculate hash values for files
  i : Show information about supported formats
  l : List contents of archive
  rn : Rename files in archive
  t : Test integrity of archive
  u : Update files to archive
  x : eXtract files with full paths

<Switches>
  -- : Stop switches parsing
  @listfile : set path to listfile that contains file names
  -ai[r[-|0]]{@listfile|!wildcard} : Include archives
  -ax[r[-|0]]{@listfile|!wildcard} : eXclude archives
  -ao{a|s|t|u} : set Overwrite mode
  -an : disable archive_name field
  -bb[0-3] : set output log level
  -bd : disable progress indicator
  -bs{o|e|p}{0|1|2} : set output stream for output/error/progress line
  -bt : show execution time statistics
  -i[r[-|0]]{@listfile|!wildcard} : Include filenames
  -m{Parameters} : set compression Method
    -mmt[N] : set number of CPU threads
    -mx[N] : set compression level: -mx1 (fastest) ... -mx9 (ultra)
  -o{Directory} : set Output directory
  -p{Password} : set Password
  -r[-|0] : Recurse subdirectories
  -sa{a|e|s} : set Archive name mode
  -scc{UTF-8|WIN|DOS} : set charset for for console input/output
  -scs{UTF-8|UTF-16LE|UTF-16BE|WIN|DOS|{id}} : set charset for list files
  -scrc[CRC32|CRC64|SHA1|SHA256|*] : set hash function for x, e, h commands
  -sdel : delete files after compression
  -seml[.] : send archive by email
  -sfx[{name}] : Create SFX archive
  -si[{name}] : read data from stdin
  -slp : set Large Pages mode
  -slt : show technical information for l (List) command
  -snh : store hard links as links
  -snl : store symbolic links as links
  -sni : store NT security information
  -sns[-] : store NTFS alternate streams
  -so : write data to stdout
  -spd : disable wildcard matching for file names
  -spe : eliminate duplication of root folder for extract command
  -spf : use fully qualified file paths
  -ssc[-] : set sensitive case mode
  -sse : stop archive creating, if it can't open some input file
  -ssw : compress shared files
  -stl : set archive timestamp from the most recently modified file
  -stm{HexMask} : set CPU thread affinity mask (hexadecimal number)
  -stx{Type} : exclude archive type
  -t{Type} : Set type of archive
  -u[-][p#][q#][r#][x#][y#][z#][!newArchiveName] : Update options
  -v{Size}[b|k|m|g] : Create volumes
  -w[{path}] : assign Work directory. Empty path means a temporary directory
  -x[r[-|0]]{@listfile|!wildcard} : eXclude filenames
  -y : assume Yes on all queries



 */


/*
7z.exe -p12345678  x big.7z -o./big

7z.exe  -y x 111_no.7z -o./111_no_7z            解压7Z 非密码
7z.exe -p111 -y x 111_pw.7z -o./111_pw_7z       解压7Z 密码

7z.exe -y  x 111_no.zip -o./111_no_zip           解压zip 非密码
7z.exe -y -p111  x 111_pw.zip -o./111_pw_zip       解压zip 密码


WinRAR 的 使用方法
WinRAR_H5.exe  -y x "111_no.rar"   ".\111_no_rar\"           解压RAR 非密码   //  -y  默认 都是yes  不询问
WinRAR_H5.exe -p111 -y x "111_pw.rar"  ".\111_pw_rar\"             解压RAR 密码   //

7z.exe  -y x 111_no.7z -o./111_no_7z  & 7z.exe -p111 -y x 111_pw.7z -o./111_pw_7z & 7z.exe -y  x 111_no.zip -o./111_no_zip  & 7z.exe -y -p111  x 111_pw.zip -o./111_pw_zip   & WinRAR_H5.exe  -y x 111_no.rar   .\111_no_rar\   &  WinRAR_H5.exe -p111 -y x 111_pw.rar  .\111_pw_rar\
https://www.cnblogs.com/fetty/p/4769279.html


7z.exe -y -p222  x 111_pw.zip -o./111_pw_zip            // 会有这个文件夹 但是是空的

C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p  x "D:\A\failed\A1\ZFAILED_UNZIP_Wifi issues_20191127.7z"  -o"./Wifi issues_20191127_7z"


C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p  x "D:\Version\LIMA\1.tar.gz"  -o"./1"

"D:\Version\LIMA\1.tar_gz\test\ZFAILED_U NZIP_sparse-formats.tar"  -o"./sparse-formats_tar"

C:\Users\zhuzj5\Desktop\zbin\H5_WinRAR.exe  -y x "D:\Version\LIMA\1.tar_gz\test\sparse-formats.t ar"  ".\sparse-formats_tar\"

C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p""  x  "D:\Version\LIMA\1.tar_gz\test\sparse-formats.tar"  -o"./sparse-formats_tar"

 */