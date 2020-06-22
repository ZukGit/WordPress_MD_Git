import com.luciad.imageio.webp.WebPReadParam;
import net.jimmc.jshortcut.JShellLink;

import java.awt.image.BufferedImage;
import java.io.*;

import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import java.security.Key;
import java.security.Security;


// 对于  文件类型_操作Index  执行对应的操作逻辑
public class G2_ApplyRuleFor_TypeFile {



    //  类型_索引  ，对当前类型的文件执行索引执行的操作     html1---对html类型的子文件执行 索引为1 的逻辑操作  String apply(String)
    static ArrayList<String> Rule_Identify_TypeIndexList = new ArrayList<String>();


    static String G2_Bat_Name = "zrule_apply_G2";
    static String Cur_Bat_Name = "zrule_apply_G2";
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";
    static String G2_File_Path = zbinPath+File.separator+"G2";

    static File G2_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "G2.properties");
    static InputStream G2_Properties_InputStream;
    static OutputStream G2_Properties_OutputStream;
    static Properties G2_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();


    static int BYTE_CONTENT_LENGTH_Rule7= 1024 * 10 * 10;   // 读取文件Head字节数常数
    static String strDefaultKey_Rule7 = "zukgit12"; //  8-length
    public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];


    static {
        try {
            if (!G2_Properties_File.exists()) {
                G2_Properties_File.createNewFile();
            }
            G2_Properties_InputStream = new BufferedInputStream(new FileInputStream(G2_Properties_File.getAbsolutePath()));
            G2_Properties.load(G2_Properties_InputStream);
            Iterator<String> it = G2_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + G2_Properties.getProperty(key));
                propKey2ValueList.put(key, G2_Properties.getProperty(key));
            }
            G2_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            G2_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(G2_Properties_File.getAbsolutePath()));
            G2_Properties.store(G2_Properties_OutputStream, "");
            G2_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    // JDK 的路径
    static String JDK_BIN_PATH = "";

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    // 当前Shell目录下的 文件类型列表  抽取出来  通用
    static  HashMap<String, ArrayList<File>> CurDirFileTypeMap = new  HashMap<String, ArrayList<File>>(); ;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        String curLibraryPath =  System.getProperties().getProperty("java.library.path");
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name+".bat";
            initJDKPath_Windows(curLibraryPath);
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
            initJDKPath_Linux_MacOS(curLibraryPath);
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name+".sh";
            initJDKPath_Linux_MacOS(curLibraryPath);
        }
    }

    static void initJDKPath_Linux_MacOS(String environmentPath){
        String[] environmentArr = environmentPath.split(":");
        for (int i = 0; i < environmentArr.length; i++) {
            String pathItem = environmentArr[i];
            if(pathItem.contains("jdk")&&pathItem.contains("bin")){
                JDK_BIN_PATH = pathItem;
            }
        }
    }



    static void initJDKPath_Windows(String environmentPath){
        String[] environmentArr = environmentPath.split(";");
        for (int i = 0; i < environmentArr.length; i++) {
            String pathItem = environmentArr[i];
            if(pathItem.contains("jdk")&&pathItem.contains("bin")){
                JDK_BIN_PATH = pathItem;
            }
        }
    }



    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static File curDirFile ;



    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }



    void InitRule(){

        realTypeRuleList.add( new HTML_Rule_1());
        realTypeRuleList.add( new File_Name_Rule_2());
        realTypeRuleList.add( new Image2Jpeg_Rule_3());
        realTypeRuleList.add( new Image2Png_Rule_4());
        realTypeRuleList.add( new AVI_Rule_5());
        realTypeRuleList.add( new SubDirRename_Rule_6());
        realTypeRuleList.add( new Encropty_Rule_7());
        realTypeRuleList.add( new ClearChineseType_8());
        realTypeRuleList.add( new FileType_Rule_9());
        realTypeRuleList.add( new DirOperation_Rule_10());
        realTypeRuleList.add( new AllDirSubFile_Order_Rule_11());

        realTypeRuleList.add( new CalCulMediaHtml_Rule_12());
        realTypeRuleList.add( new CalMP4_DIR_HTML_Rule_13());
        realTypeRuleList.add( new CreateIconFile_KuaiJieFangShi_Rule_14());
        realTypeRuleList.add( new Webp_To_Jpg_Gif_Rule_15());
    }



    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作


 class Webp_To_Jpg_Gif_Rule_15 extends Basic_Rule{
ArrayList<File> webpFileList ;
ArrayList<File> gif_webpFileList ;
String G2_webp2gif_exe_path = "";

        Webp_To_Jpg_Gif_Rule_15() {
            super("*", 15, 4);
            webpFileList = new ArrayList<File>();
            gif_webpFileList = new ArrayList<File>();
            PushFile2JDKBIN();
            if(curOS_TYPE == OS_TYPE.Windows ){
                G2_webp2gif_exe_path = zbinPath + File.separator + "G2_webp2gif.exe";
            }

        }


         void   PushFile2JDKBIN(){
            if("".equals(JDK_BIN_PATH)){
				 System.out.println("当前 库文件 JDK_BIN_PATH ="+JDK_BIN_PATH);
                return;
            }
            String webpLibraryFilePath = null;
            String G2_LibraryPath = null;
            // G2_File_Path
            if(curOS_TYPE == OS_TYPE.Windows){
                webpLibraryFilePath = JDK_BIN_PATH + File.separator+"webp-imageio.dll";
                G2_LibraryPath =  G2_File_Path +File.separator+"webp-imageio.dll";
            }else if(curOS_TYPE == OS_TYPE.MacOS) {
                webpLibraryFilePath = JDK_BIN_PATH + File.separator+"libwebp-imageio.dylib";
                G2_LibraryPath =  G2_File_Path +File.separator+"libwebp-imageio.dylib";
            }else if(curOS_TYPE == OS_TYPE.Linux){
                webpLibraryFilePath = JDK_BIN_PATH + File.separator+"libwebp-imageio.so";
                G2_LibraryPath =  G2_File_Path +File.separator+"libwebp-imageio.so";
            }

            File webpLibraryFile = new File(webpLibraryFilePath);
            File G2_LibraryFile= new File (G2_LibraryPath);
            if(!G2_LibraryFile.exists()){
                System.out.println("本地 库文件 "+G2_LibraryPath +"不存在 请重新填充 zbin/G2/.so .dll 文件!");
                return;
            }
            if(webpLibraryFile.exists() && webpLibraryFile.length() > 100){
                System.out.println("当前 库文件 "+webpLibraryFilePath +"已经加载到 jre/bin 路径下!");
                return;
            }
		 System.out.println("当前 库文件 G2_LibraryFile ="+G2_LibraryFile +"   webpLibraryFile="+webpLibraryFile +" 如果报错 请手动复制 ");
            fileCopy(G2_LibraryFile,webpLibraryFile);
        }


     @Override
     ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

         ArrayList<File>  webpFile =    subFileTypeMap.get(".webp");
         if(webpFile == null){
             System.out.println("当前文件夹中不存在 webp文件的格式");
             return null;
         }
         webpFileList.addAll(webpFile);
         String stampStr = getTimeStamp();
         for (int i = 0; i < webpFileList.size(); i++) {

             File webpFileItem = webpFileList.get(i);
             System.out.println("当前 webp索引["+i+"] = "+ webpFileItem.getAbsolutePath());
             String newFilePath = webpFileItem.getAbsolutePath().replace(".webp",               "_"+stampStr+".jpg");
             File jpgFileItem = new File(newFilePath);
             revertWebp2Jpg(webpFileItem,jpgFileItem);

         }

         for (int i = 0; i < gif_webpFileList.size(); i++) {
             File gif_webpFileItem = gif_webpFileList.get(i);
             String originName = gif_webpFileItem.getName();
             String curParentPath = gif_webpFileItem.getParent();
             boolean needRename = false;
             String absPath = gif_webpFileItem.getAbsolutePath();
             String gif_absPath = absPath.replace(".webp",".gif");
              File  gif_absPath_File = new File(gif_absPath);
             String fileName = gif_webpFileItem.getName();

             // 如果 加载后的gif 存在 那么 需要 添加时间戳  以免覆盖
//             if(gif_absPath_File.exists()){

                 fileName = fileName.replace(".webp","_"+stampStr+".webp");
                 tryReName(gif_webpFileItem,fileName);
                 needRename = true;
//              }

             System.out.println("动图 索引["+i+"] = "+ fileName);
             System.out.println("执行动图转为 gif的命令! ");
             if("".equals(G2_webp2gif_exe_path)){
              System.out.println("当前 webp2gif 为空 请检查!  可能当前系统 Linux MacOS 还没实现该功能!");
              return null;
             }
             String  command = G2_webp2gif_exe_path + " "+fileName;
             execCMD(command);
             if(needRename){
                 tryReName(new File(curParentPath+File.separator+fileName),originName);
             }

         }

            return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
     }



     void   revertWebp2Jpg(File webpFile , File jpgFile){
         // webp  动态图 会报错  Decode returned code VP8_STATUS_UNSUPPORTED_FEATURE
         // Obtain a WebP ImageReader instance
         ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();

         // Configure decoding parameters
         WebPReadParam readParam = new WebPReadParam();
         readParam.setBypassFiltering(true);
         BufferedImage image = null;
         try{

             // Configure the input on the ImageReader
             reader.setInput(new FileImageInputStream(webpFile));

             // Decode the image
             image = reader.read(0, readParam);
         }catch (IOException e){

             System.out.println("解析失败   可能是webp动图!   放入 ArrayList<File> gifList 列表中!");
            gif_webpFileList.add(webpFile);
         }


   try {
             ImageIO.write(image, "png", jpgFile);
         }catch (Exception e){
             System.out.println("写入文件 "+jpgFile.getAbsolutePath()+" 失败");
         }

        }
        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ " webp_15            ### 对当前目录下的 webp文件进行转换  静态图-> jpg   动态图-> gif \n";}

    }





    // 创建快捷方式
    static boolean makeShellLink(File targetFile,File iconFile){
        boolean isOK = false;
        String targetFilePath = targetFile.getAbsolutePath();
        JShellLink link = new JShellLink();
        if(!iconFile.exists()){
/*            try {
                iconFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        String parentAbsPath = iconFile.getParentFile().getAbsolutePath();
        link.setFolder(parentAbsPath);
        String iconName = iconFile.getName();
        link.setName(iconName);
        link.setPath(targetFilePath);
        link.save();
        if(isKuaiJieIcon(iconFile)){
            isOK = true;
        }

        return isOK;


    }

    static String getTargetFilePath(File iconFile){
        String targetFilePath = "";
        if(!isKuaiJieIcon(iconFile)){
            return null;  // 不是 快捷方式 那么 返回 "" 或者 null
        }
        String parentAbsPath = iconFile.getParentFile().getAbsolutePath();
        String fileName = iconFile.getName();
        JShellLink linkFile = new JShellLink(parentAbsPath,fileName);
        linkFile.load();
        targetFilePath = linkFile.getPath();
        return targetFilePath;
    }

    static boolean isKuaiJieIcon(File kuaijieFile){
        String absPath = kuaijieFile.getAbsolutePath();
        String parentAbsPath = kuaijieFile.getParentFile().getAbsolutePath();
        String fileName = kuaijieFile.getName();
        JShellLink linkFile = new JShellLink(parentAbsPath,fileName);
        linkFile.load();
        String linkedPath = linkFile.getPath();

        if(absPath.equals(linkedPath)){
            return  false;
        }
        return true;
    }


    class CreateIconFile_KuaiJieFangShi_Rule_14 extends Basic_Rule{

        ArrayList<String> inputTypeList ;
        // zrule_apply_G2.bat  *_14  jpg   把当前所有的jpg格式文件生成快捷方式到 jpg_时间戳 文件夹内


        CreateIconFile_KuaiJieFangShi_Rule_14() {
            super("*", 14, 3);
            inputTypeList = new ArrayList<String>();
        }

        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {
            for (int i = 0; i <inputParamList.size() ; i++) {
                String strInput = inputParamList.get(i);
                if(strInput.equals(firstInputIndexStr)){
                    continue;
                }
                if(!strInput.startsWith(".")){
                    inputTypeList.add("."+strInput.trim());
                }else{
                    inputTypeList.add(strInput.trim());
                }
            }
            return super.initParamsWithInputList(inputParamList);
        }




        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

            SimpleDateFormat df = new SimpleDateFormat("MMdd_HHmmss");//设置日期格式
//            SimpleDateFormat df_hms = new SimpleDateFormat("HHmmss");//设置日期格式

            Date curDate =    new Date();
            String date = df.format(curDate);
//            String preHMS = df.format(df_hms);
            for (int i = 0; i < inputTypeList.size(); i++) {
                String type = inputTypeList.get(i);

                ArrayList<File> targetFileList = fileTypeMap.get(type) ;

                if(targetFileList == null || targetFileList.size() == 0){
                    System.out.println(" 当前路径 "+curDirPath+" 不存在类型 "+type +"的文件!");
                }


             int fileCount =    targetFileList.size() ;
                // 创建文件夹  大小
//                String dirName = preHMS+"_"+type.replace(".","").toUpperCase().trim()+"_"+date;
                String dirName = date+"_"+type.replace(".","").toUpperCase().trim()+"["+fileCount+"]";
                // MP4_4232414141
                File iconDirFile = new File(curDirPath+File.separator+dirName);
                iconDirFile.mkdirs();


                System.out.println("════════"+"文件类型"+type+"创建快捷方式 Begin"+"════════");
                for (int j = 0; j < targetFileList.size(); j++) {
                    File   targetTypeFile = targetFileList.get(j);
                    String targetName = targetTypeFile.getName();
                    int IconIndex = j + 1;
                    String targetOrderName = IconIndex+"_"+targetName;
                   if( tryReName(targetTypeFile,targetOrderName)){
                       targetTypeFile = new File(targetTypeFile.getParentFile().getAbsolutePath()+File.separator+targetOrderName);
                   }

                    String iconName = IconIndex+"_"+targetName;
                    File iconFile = new File(iconDirFile.getAbsolutePath()+ File.separator+iconName);
                    if(makeShellLink(targetTypeFile,iconFile)){

                        System.out.println("Index["+IconIndex+"]目标文件:"+targetTypeFile.getAbsolutePath()+" 创建快捷方式成功:"+"./"+dirName+File.separator+iconName);
                    }else{
                        System.out.println("Index["+IconIndex+"]目标文件:"+targetTypeFile.getAbsolutePath()+" 创建快捷方式失败:"+"./"+dirName+File.separator+iconName);
                    }
                }
                System.out.println("════════"+"文件类型"+type+"创建快捷方式 End"+"════════");

            }


            return super.applyFileListRule3(subFileList, fileTypeMap);
        }

        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ " *_14  mp4          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .mp4         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .gif         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的gif文件 并在当前目录生成 GIF_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  png          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的png文件 并在当前目录生成 PNG_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  zip  7z      ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .zip .7z     ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  jpg          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
                    ;}
    }



    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作




    class CalMP4_DIR_HTML_Rule_13 extends Basic_Rule{
        String Type_DIR_NAME = "";
        ArrayList<File> inputDirList;
        ArrayList<File> htmlModelList;
        // G2_Rule13_mp4_3x5.html
        File mp4_3x5_File ;

        //        G2_Rule13_mp4__3d.html
        File mp4_3d_File ;

        //        G2_Rule13_mp4_2x2.html
        File mp4_2x2_File ;
        //        G2_Rule13_mp4_3x3.html
        File mp4_3x3_File ;



        String newReplaceName;  // G2_Rule13_mp4_3x5   期中  G2_Rule13 替换的名称


        CalMP4_DIR_HTML_Rule_13() {
            super("#", 13, 4);
            inputDirList = new   ArrayList<File>();
            htmlModelList = new   ArrayList<File>();
            mp4_3x5_File = new File(zbinPath+File.separator+"G2_Rule13_mp4_3x5.html");
            mp4_3d_File = new File(zbinPath+File.separator+"G2_Rule13_mp4__3d.html");
            mp4_2x2_File= new File(zbinPath+File.separator+"G2_Rule13_mp4_2x2.html");
            mp4_3x3_File= new File(zbinPath+File.separator+"G2_Rule13_mp4_3x3.html");

            newReplaceName="";
            htmlModelList.add(mp4_3x5_File);
            htmlModelList.add(mp4_3d_File);
            htmlModelList.add(mp4_2x2_File);
            htmlModelList.add(mp4_3x3_File);

        }


        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {

            for (int i = 0; i < inputParamList.size(); i++) {
                String paramItem = inputParamList.get(i);
                // 检查是否有 paramItem 名称的文件夹
                System.out.println("paramItem = "+ paramItem);
                File curDir = checkType2Dir(curDirFile,paramItem);
                if(curDir != null && curDir.isDirectory()){
                    inputDirList.add(curDir);  //
                }

            }

            for (int i = 0; i < inputDirList.size(); i++) {
                String dirName =  inputDirList.get(i).getName();
                newReplaceName = newReplaceName+"_"+dirName;
            }
            while(newReplaceName.endsWith("_")){
                newReplaceName =newReplaceName.substring(0,newReplaceName.length()-1);
            }

            while(newReplaceName.startsWith("_")){
                newReplaceName =newReplaceName.substring(1,newReplaceName.length());
            }


            if("".equals(newReplaceName)){
                newReplaceName = ""+curDirFile.getName();

            }


            return super.initParamsWithInputList(inputParamList);
        }

        @Override
        boolean initParams4InputParam(String inputParam) {
            if(!(inputParam.contains("jpg") || inputParam.contains("mp4")  || inputParam.contains("gif") )){
                System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
                return false;
            }

            String[] params= inputParam.split("_");
            if(params == null){
                System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
                return false;
            }
            String TypeDir =  params[params.length -1];

            if(!(("jpg").equals(TypeDir) || ("mp4").equals(TypeDir)  || ("gif").equals(TypeDir)) ){
                System.out.println("当前输入参数不包含 jpg || mp4 || gif  请重新输入");
                return false;
            }
            Type_DIR_NAME = TypeDir.toLowerCase().trim();
            return super.initParams4InputParam(inputParam);
        }

        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ "  #_13_mp4    ### 动态计算当前文件夹中所有子文件中的mp4文件夹中的 mp4文件个数  并在当前目录生成html文件 \n"+
                    Cur_Bat_Name+ "  #_13_jpg    ### 动态计算当前文件夹中所有子文件中的jpg文件夹中的 jpg文件个数 并在当前目录生成html文件\n"+
                    Cur_Bat_Name+ "  #_13_gif    ### 动态计算当前文件夹中所有子文件中的gif文件夹中的 gif文件个数 并在当前目录生成html文件\n" +
                    Cur_Bat_Name+ "  #_13_mp4  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的mp4文件夹中的 mp4文件个数 并在当前目录生成html文件\n"+
                    Cur_Bat_Name+ "  #_13_jpg  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的jpg文件夹中的 jpg文件个数 并在当前目录生成html文件\n"+
                    Cur_Bat_Name+ "  #_13_gif  <单个子件夹参数>  ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的gif文件夹中的 gif文件个数 并在当前目录生成html文件\n"+
                    Cur_Bat_Name+ "  #_13_mp4  <子文件夹参数1> <子文件夹参数2> ....<子文件夹参数N>  ### 多输入参数 动态计算给定路径下的文件夹中所有子文件中的mp4文件夹中的 gif文件个数 并在当前目录生成html文件\n"+
                    Cur_Bat_Name+ "  #_13_jpg <子文件夹参数1> <子文件夹参数2> ....<子文件夹参数N>   ### 同没有参数(但shell路径不同) 动态计算当前文件夹中所有子文件中的mp4文件夹中的 gif文件个数 并在当前目录生成html文件\n";
        }


        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            ArrayList<File> operaDirList = new   ArrayList<File>();
            boolean isMultiDirInput = false;
            String curBasePath = "";


            if(inputDirList.size() == 0){ // 如果没有输入Dir参数 那么 就在当前目录操作
                operaDirList.addAll(curDirList);
                curBasePath = curDirFile.getAbsolutePath();
            }else if(inputDirList.size() == 1){  // 如果只有一个参数   那么operaDirList 放入 当前参数的子目录
                File curInputDir =   inputDirList.get(0);
                curBasePath = curInputDir.getAbsolutePath();
                operaDirList.addAll(getCurrentSubDirFile(curInputDir));
                System.out.println(" curInputDir = "+ curInputDir);
            }else{
                for (int i = 0; i < inputDirList.size(); i++) {
                    operaDirList.addAll(getCurrentSubDirFile(inputDirList.get(i)));
                }
                isMultiDirInput = true;
                curBasePath = curDirFile.getAbsolutePath();
            }
            System.out.println(" inputDirList.size = "+ inputDirList.size());
            System.out.println(" curBasePath = "+ curBasePath);


//// hoderplace -begin
//zukgitPlaceHolderArrayDefine
//var objectArr = [ zukgitPlaceHolderArrayAdd ];
//// hoderplace -end


            StringBuilder defineArrWord  = new StringBuilder();
            StringBuilder defineAdd  = new StringBuilder();

            //  如果有参数  那么 当前的 curDirList
            int index = 0;
            for (int i = 0; i < operaDirList.size(); i++) {
                File cur1DirFileItem = operaDirList.get(i);
                File mTypeDirFile  = checkType2Dir(cur1DirFileItem,Type_DIR_NAME);
                int typeFileNum = 0;
                if(mTypeDirFile != null &&  0 != (typeFileNum=checkType3File(mTypeDirFile,Type_DIR_NAME))){
                    // 检测到了 对应的 type 文件
                    // 1.获取当前 第一层目录名称
                    String dir1DirName = cur1DirFileItem.getName();
                    // 2. 获取对应命令的文件
                    String dir2TypeDieName = dir1DirName + File.separator+Type_DIR_NAME;
                    dir2TypeDieName = dir2TypeDieName.replace("\\","/");
                    // 3. typeFileNum 对应的当前 孙子目录中的文件的个数
                    int length  =typeFileNum;
                    String people = "person"+index;

//                    person0 = { index:0 , path:"./7001/mp4/",length:22,};
//                    person0 = { index:0 , path:"./7001\mp4,length:22,};
                    String defineItem = "";
                    if(!isMultiDirInput){ // 如果是单独的 文件
                        defineItem = people+" = { index:"+index+" , path:\"./"+dir2TypeDieName+"/\",length:"+length+",};\n";
                    }else{  // 如果是两个 量入的文件  那么 path就要加入对应的  当前目录的路径
                        String targetDirName = calculBeginDir(mTypeDirFile.getAbsolutePath());
                        if(!"".equals(targetDirName)){
                            targetDirName = targetDirName +"/";
                        }
                        defineItem = people+" = { index:"+index+" , path:\"./"+targetDirName+dir2TypeDieName+"/\",length:"+length+",};\n";
                    }
                    defineArrWord.append(defineItem);
                    defineAdd.append(people+",");
                    index++;
                }

            }

            // 定义people
            String defineArrWordStr = defineArrWord.toString().trim();
            while(defineArrWordStr.endsWith(",")){
                defineArrWordStr = defineArrWordStr.substring(0,defineArrWordStr.length()-1);
            }

            // 把 people 编为 数组 array
            String defineAddStr = defineAdd.toString();


            for (int i = 0; i < htmlModelList.size(); i++) {
                //  获取 html文件的内容
                File htmlModelFile = htmlModelList.get(i);

                // G2_Rule13_mp4_3x5
                String html_old_name = htmlModelFile.getName();
                String readHtmlContent = ReadFileContent(htmlModelFile);

//            String readHtmlContent = "";
                System.out.println("defineAddStr  = "+ defineAddStr);
                System.out.println("defineArrWordStr  = "+ defineArrWordStr);
                readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayAdd",defineAddStr);
                readHtmlContent = readHtmlContent.replace("zukgitPlaceHolderArrayDefine",defineArrWordStr);



                //  把文件写入  对应的目录
                // 当前 文件名称
                String newName = html_old_name.replace("G2_Rule13",newReplaceName);

                File curHtmlTargetFile =new File( curBasePath + File.separator+newName);

                // 写入哪个文件夹

                //1. 无参数   写入当前的 shell 路径下
                //2. 一个参数的情况

                writeContentToFile(curHtmlTargetFile,readHtmlContent);
                System.out.println("输出文件:"+curHtmlTargetFile.getAbsolutePath());
            }

            return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
        }


        String calculBeginDir(String mediaPath){
            String inputDirStr = "";
            for (int i = 0; i < inputDirList.size(); i++) {
                File inputDir =  inputDirList.get(i);
                String inputDirPath = inputDir.getAbsolutePath();
                if(mediaPath.startsWith(inputDirPath)){
                    inputDirStr = inputDir.getName();
                    break;
                }

            }

            return inputDirStr;

        }
        // 检测当前的 dirFile 目录中是否存在 第二个参数名称相同的文件名
        File checkType2Dir(File dirFile , String typeName){
            String dirNameA = typeName;
            while(dirNameA.endsWith("\\")){
                dirNameA = dirNameA.substring(0,dirNameA.length() -1);
            }
            File typeDirFile = null;
            File[] fileList = dirFile.listFiles();
            if(fileList == null){
                return typeDirFile;
            }
            for (int i = 0; i < fileList.length; i++) {
                File dirFileItem = fileList[i];
                String dirName = dirFileItem.getName();
                if(dirNameA.equals(dirName)){
                    typeDirFile = dirFileItem;
                    break;
                }
            }
            return typeDirFile;
        }


        // 检查当前目录下是否存在对应类型typeName 的具体的文件 的文件名称的个数
        int checkType3File(File dirFile , String typeName){
            int existNum = 0;

            File[] fileList = dirFile.listFiles();
            if(fileList == null){
                return existNum;
            }
            for (int i = 0; i < fileList.length; i++) {
                File dirFileItem = fileList[i];
                String dirName = dirFileItem.getName();
                // 当前文件不是文件夹  并且当前文件名称的后缀 是 .【type】 例如 .gif  .jpg  .mp4
                if(!dirFileItem.isDirectory() && dirFileItem.getName().endsWith("."+typeName)){
                    existNum ++;
                }

            }
            return existNum;
        }

    }
    // //  zrule_apply_G2.bat  12_mp4   <目标文件夹目录>   ### 把当前目录mp4文件生成 html 播放文件
    // //  zrule_apply_G2.bat  12_jpg   <目标文件夹目录>   ### 把没有类型的文件名称修改为 jpg格式名称
    // //  zrule_apply_G2.bat  12_gif   <目标文件夹目录>   ### 把没有类型的文件名称修改为 jpg格式名称

    class CalCulMediaHtml_Rule_12 extends Basic_Rule{

        ArrayList<File>  operaDirFileList; // 当前从参数获得的目录文件集合
        int operaType;  // 0-unknow  1--mp4   2--jpg    3--gif

        ArrayList<File> mp4HtmlTemplate_FileList ;
        ArrayList<File> jpgHtmlTemplate_FileList ;
        ArrayList<File> gifHtmlTemplate_FileList ;

        File Mp4_2x2_Html_TemplateFile ;
        File Mp4_3x3_Html_TemplateFile ;
        File Mp4_3x5_Html_TemplateFile ;
        File Mp4_3d_Html_TemplateFile ;
        File Mp4_2x2_Html_SameTempFile ;
        File Mp4_3x3_Html_SameTempFile ;
        File Mp4_3x5_Html_SameTempFile ;


        File Gif_3d_Html_TemplateFile ;
        File Gif_1x1_Html_TemplateFile_Left ;
        File Gif_1x1_Html_TemplateFile_Right ;
        File Gif_2x2_Html_TemplateFile;
        File Gif_2x2_Html_TemplateFile_Left;
        File Gif_2x2_Html_TemplateFile_Right;
        File Gif_2x2_Html_SameTempFile;
        File Gif_3x3_Html_TemplateFile ;
        File Gif_3x3_Html_TemplateFile_Left ;
        File Gif_3x3_Html_TemplateFile_Right ;
        File Gif_3x3_Html_SameTempFile;
        File Gif_3x5_Html_TemplateFile ;
        File Gif_3x5_Html_SameTempFile;
        File Gif_2x4_Html_TemplateFile_Left ;
        File Gif_2x4_Html_TemplateFile_Right;
        File Gif_4x3_Html_TemplateFile_Left ;
        File Gif_4x3_Html_TemplateFile_Right;
        File Gif_4x4_Html_TemplateFile_Left ;
        File Gif_4x4_Html_TemplateFile_Right;
        File Gif_3x5_Html_TemplateFile_Left ;
        File Gif_3x5_Html_TemplateFile_Right;
        File Gif_4x5_Html_TemplateFile_Left ;
        File Gif_4x5_Html_TemplateFile_Right;

        File Jpg_3d_Html_TemplateFile ;
        File Jpg_4x3_Html_TemplateFile_Left ;
        File Jpg_4x3_Html_TemplateFile_Right;
        File Jpg_1x1_Html_TemplateFile_Left ;
        File Jpg_1x1_Html_TemplateFile_Right ;
        File Jpg_2x2_Html_TemplateFile;
        File Jpg_2x2_Html_TemplateFile_Left;
        File Jpg_2x2_Html_TemplateFile_Right;
        File Jpg_2x2_Html_SameTempFile;
        File Jpg_3x3_Html_TemplateFile ;
        File Jpg_3x3_Html_TemplateFile_Left ;
        File Jpg_3x3_Html_TemplateFile_Right ;
        File Jpg_3x3_Html_SameTempFile;
        File Jpg_3x5_Html_TemplateFile ;
        File Jpg_3x5_Html_SameTempFile;
        File Jpg_2x4_Html_TemplateFile_Left ;
        File Jpg_2x4_Html_TemplateFile_Right;
        File Jpg_4x4_Html_TemplateFile_Left ;
        File Jpg_4x4_Html_TemplateFile_Right;
        File Jpg_3x5_Html_TemplateFile_Left ;
        File Jpg_3x5_Html_TemplateFile_Right;
        File Jpg_4x5_Html_TemplateFile_Left ;
        File Jpg_4x5_Html_TemplateFile_Right;


        CalCulMediaHtml_Rule_12() {

            super("#", 12, 4);
            operaType = 0;
            operaDirFileList = new  ArrayList<File>();
            mp4HtmlTemplate_FileList  = new  ArrayList<File>();
            jpgHtmlTemplate_FileList  = new  ArrayList<File>();
            gifHtmlTemplate_FileList  = new  ArrayList<File>();

            Mp4_2x2_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_2x2.html");
            Mp4_3x3_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_3x3.html");
            Mp4_3x5_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_3x5.html");
            Mp4_2x2_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_2x2_same.html");
            Mp4_3x3_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_3x3_same.html");
            Mp4_3x5_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_mp4_3x5_same.html");
            Mp4_3d_Html_TemplateFile  = new File(zbinPath+File.separator+"G2_Rule12_mp4__3d.html");
            mp4HtmlTemplate_FileList.add(Mp4_2x2_Html_TemplateFile);
            mp4HtmlTemplate_FileList.add(Mp4_3x3_Html_TemplateFile);
            mp4HtmlTemplate_FileList.add(Mp4_3x5_Html_TemplateFile);
            mp4HtmlTemplate_FileList.add(Mp4_2x2_Html_SameTempFile);
            mp4HtmlTemplate_FileList.add(Mp4_3x3_Html_SameTempFile);
            mp4HtmlTemplate_FileList.add(Mp4_3x5_Html_SameTempFile);
            mp4HtmlTemplate_FileList.add(Mp4_3d_Html_TemplateFile);


//-----------------------------JPG--------------------------------------

            Jpg_3d_Html_TemplateFile  = new File(zbinPath+File.separator+"G2_Rule12_jpg__3d.html");
            Jpg_1x1_Html_TemplateFile_Left      = new File(zbinPath+File.separator+"G2_Rule12_jpg_1x1_flow_left.html");
            Jpg_1x1_Html_TemplateFile_Right= new File(zbinPath+File.separator+"G2_Rule12_jpg_1x1_flow_right.html");
            Jpg_2x2_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_2x2.html");
            Jpg_2x2_Html_TemplateFile_Left= new File(zbinPath+File.separator+"G2_Rule12_jpg_2x2_flow_left.html");
            Jpg_2x2_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_2x2_flow_right.html");
            Jpg_2x2_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_2x2_same.html");
            Jpg_3x3_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_3x3.html");
            Jpg_3x3_Html_TemplateFile_Left= new File(zbinPath+File.separator+"G2_Rule12_jpg_3x3_flow_left.html");
            Jpg_3x3_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_3x3_flow_right.html");
            Jpg_3x3_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_3x3_same.html");
            Jpg_3x5_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_3x5.html");
            Jpg_3x5_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_jpg_3x5_same.html");
            Jpg_2x4_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x2_flow_left.html");
            Jpg_2x4_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x2_flow_right.html");
            Jpg_4x3_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x3_flow_left.html");
            Jpg_4x3_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x3_flow_right.html");

            Jpg_4x4_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x4_flow_left.html");
            Jpg_4x4_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_4x4_flow_right.html");
            Jpg_3x5_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_jpg_5x3_flow_left.html");
            Jpg_3x5_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_5x3_flow_right.html");
            Jpg_4x5_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_jpg_5x4_flow_right.html");
            Jpg_4x5_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_jpg_5x4_flow_left.html");

            jpgHtmlTemplate_FileList.add(Jpg_3d_Html_TemplateFile);
            jpgHtmlTemplate_FileList.add(Jpg_1x1_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_1x1_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile        );
            jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_2x2_Html_SameTempFile        );
            jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile        );
            jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_3x3_Html_SameTempFile        );
            jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile        );
            jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_SameTempFile        );
            jpgHtmlTemplate_FileList.add(Jpg_2x4_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_2x4_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_4x3_Html_TemplateFile_Left);
            jpgHtmlTemplate_FileList.add(Jpg_4x3_Html_TemplateFile_Right);
            jpgHtmlTemplate_FileList.add(Jpg_4x4_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_4x4_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_3x5_Html_TemplateFile_Right  );
            jpgHtmlTemplate_FileList.add(Jpg_4x5_Html_TemplateFile_Left   );
            jpgHtmlTemplate_FileList.add(Jpg_4x5_Html_TemplateFile_Right  );

            //--------------------GIF--------------------------



            Gif_3d_Html_TemplateFile  = new File(zbinPath+File.separator+"G2_Rule12_gif__3d.html");
            Gif_1x1_Html_TemplateFile_Left      = new File(zbinPath+File.separator+"G2_Rule12_gif_1x1_flow_left.html");
            Gif_1x1_Html_TemplateFile_Right= new File(zbinPath+File.separator+"G2_Rule12_gif_1x1_flow_right.html");
            Gif_2x2_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_gif_2x2.html");
            Gif_2x2_Html_TemplateFile_Left= new File(zbinPath+File.separator+"G2_Rule12_gif_2x2_flow_left.html");
            Gif_2x2_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_2x2_flow_right.html");
            Gif_2x2_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_gif_2x2_same.html");
            Gif_3x3_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_gif_3x3.html");
            Gif_3x3_Html_TemplateFile_Left= new File(zbinPath+File.separator+"G2_Rule12_gif_3x3_flow_left.html");
            Gif_3x3_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_3x3_flow_right.html");
            Gif_3x3_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_gif_3x3_same.html");
            Gif_3x5_Html_TemplateFile = new File(zbinPath+File.separator+"G2_Rule12_gif_3x5.html");
            Gif_3x5_Html_SameTempFile = new File(zbinPath+File.separator+"G2_Rule12_gif_3x5_same.html");
            Gif_2x4_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_gif_4x2_flow_left.html");
            Gif_2x4_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_4x2_flow_right.html");
            Gif_4x3_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_gif_4x3_flow_left.html");
            Gif_4x3_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_4x3_flow_right.html");
            Gif_4x4_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_gif_4x4_flow_left.html");
            Gif_4x4_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_4x4_flow_right.html");
            Gif_3x5_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_gif_5x3_flow_left.html");
            Gif_3x5_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_5x3_flow_right.html");
            Gif_4x5_Html_TemplateFile_Left = new File(zbinPath+File.separator+"G2_Rule12_gif_5x4_flow_right.html");
            Gif_4x5_Html_TemplateFile_Right = new File(zbinPath+File.separator+"G2_Rule12_gif_5x4_flow_left.html");



            gifHtmlTemplate_FileList.add(Gif_3d_Html_TemplateFile   );
            gifHtmlTemplate_FileList.add(Gif_1x1_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_1x1_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile        );
            gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_2x2_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_2x2_Html_SameTempFile        );
            gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile        );
            gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_3x3_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_3x3_Html_SameTempFile        );
            gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile        );
            gifHtmlTemplate_FileList.add(Gif_3x5_Html_SameTempFile        );
            gifHtmlTemplate_FileList.add(Gif_2x4_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_2x4_Html_TemplateFile_Right  );

            gifHtmlTemplate_FileList.add(Gif_4x3_Html_TemplateFile_Left  );
            gifHtmlTemplate_FileList.add(Gif_4x3_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_4x4_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_4x4_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_3x5_Html_TemplateFile_Right  );
            gifHtmlTemplate_FileList.add(Gif_4x5_Html_TemplateFile_Left   );
            gifHtmlTemplate_FileList.add(Gif_4x5_Html_TemplateFile_Right  );

        }

        @Override
        boolean initParams4InputParam(String inputParam) {
            if(inputParam.contains("_mp4")){
                operaType = 1;
            } else if(inputParam.contains("_jpg")){
                operaType = 2;
            } else if(inputParam.contains("_gif")){
                operaType = 3;
            }

            return super.initParams4InputParam(inputParam);
        }

        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {
            System.out.println("Rule12 inputDirPath [ length ] = " + inputParamList.size() );
            for (int i = 0; i < inputParamList.size(); i++) {
                String inputDirPath = inputParamList.get(i);
                System.out.println("Rule12  inputDirPath [ "+ i+" ] = " + inputDirPath  +"  curDirFile = "+ curDirFile);
                if(inputDirPath.endsWith("\\")){
                    inputDirPath = inputDirPath.replace("\\","");
                }



                File inputDir = new File(curDirFile.getAbsoluteFile()+File.separator+inputDirPath);
                if(inputDir != null &&  inputDir.exists() && inputDir.isDirectory()){
                    operaDirFileList.add(inputDir);
                }
                System.out.println(" inputDir  = "+ inputDir.getAbsolutePath());
            }
            if(operaDirFileList.size() == 0){
                System.out.println("当前用户没有输入执行的目录名称,请重新输入!");
                return false;
            }

            return super.initParamsWithInputList(inputParamList);
        }



        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            if(operaDirFileList.size() == 0){
                System.out.println("当前用户没有输入执行的目录名称,请重新输入!");
                return null;
            }
            for (int i = 0; i < operaDirFileList.size(); i++) {
                File operaDirFile = operaDirFileList.get(i);
                OperationHtmlMedia(operaDirFile);
            }

            return super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
        }

        void OperationHtmlMedia (File xdirFile){
            switch (operaType){
                case 1:   //  mp4
                    ArrayList<File> mp4_mediaFileList =  getSubTypeFileWithPoint(xdirFile,".mp4");
                    tryMediaFileRenameOperation(mp4_mediaFileList,".mp4");
                    tryMP4HtmlOperation(xdirFile,mp4_mediaFileList.size());
                    break;
                case 2:    // jpg
                    ArrayList<File> jpg_mediaFileList =  getSubTypeFileWithPoint(xdirFile,".jpg");
                    tryMediaFileRenameOperation(jpg_mediaFileList,".jpg");
                    tryJPGHtmlOperation(xdirFile,jpg_mediaFileList.size());
                    break;
                case 3:   //gif
                    ArrayList<File> gif_mediaFileList =  getSubTypeFileWithPoint(xdirFile,".gif");
                    tryMediaFileRenameOperation(gif_mediaFileList,".gif");
                    tryGIFHtmlOperation(xdirFile,gif_mediaFileList.size());
                    break;
                default:
            }

        }

        void tryMediaFileRenameOperation(ArrayList<File>  mp4FileList,String fileTypeWithPoint){
            int index = 0;
            ArrayList<File> tempFileList1 = new ArrayList<File>();

            for (int i = 0; i < mp4FileList.size(); i++) {
                index = i + 1;
                String timeStamp = "";
                String newName1 = index+"_"+getTimeStamp()+fileTypeWithPoint;
                String newName2 = index+fileTypeWithPoint;
                File curFile = mp4FileList.get(i);
                String parrentFilePath = curFile.getParentFile().getAbsolutePath();
                tryReName(curFile,newName1);   // 第一次改名   避免重复
                File file1 = new File(parrentFilePath+File.separator+newName1);
                tempFileList1.add(file1);
            }
            for (int i = 0; i < tempFileList1.size(); i++) {
                index = i + 1;
                File curFile = tempFileList1.get(i);
                String newName = index+fileTypeWithPoint;
                tryReName(curFile,newName);   // 第二次改名   实现顺序 1.xx  2.xx  3.xx  4.xx
            }

        }


        void tryMP4HtmlOperation(File curDirFile , int num){
// 把当前的html文件 中的  对应的 占位符 以 num 进行 替换
// 把  html文件中 mp4/  转换为   当前目录名称  90890/
// 把当前的 html  文件 放入到当前的 shell的 根 目录    html命令为   参数目录_原有名称
            String curDirName = curDirFile.getName();


            for (int i = 0; i < mp4HtmlTemplate_FileList.size(); i++) {
                File HtmlFile = mp4HtmlTemplate_FileList.get(i);
                if(!HtmlFile.exists()){
                    System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
                    continue;
                }
                String htmlname = HtmlFile.getName();
                htmlname = htmlname.replace("G2_Rule12",curDirName);

                String htmlContent = ReadFileContent(HtmlFile);
                htmlContent =    htmlContent.replace("zukgitPlaceHolderindex",num+"");
                htmlContent =    htmlContent.replace("mp4/",curDirName+"/");
                File curShellHtmlFile = new File(curDirFile.getParentFile().getAbsolutePath()+File.separator+""+htmlname);
                writeContentToFile(curShellHtmlFile,htmlContent);
            }
        }

        void tryJPGHtmlOperation(File curDirFile , int num){
            String curDirName = curDirFile.getName();


            for (int i = 0; i < jpgHtmlTemplate_FileList.size(); i++) {
                File HtmlFile = jpgHtmlTemplate_FileList.get(i);
                if(!HtmlFile.exists()){
                    System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
                    continue;
                }
                String htmlname = HtmlFile.getName();
                htmlname = htmlname.replace("G2_Rule12",curDirName);

                String htmlContent = ReadFileContent(HtmlFile);
                htmlContent =    htmlContent.replace("zukgitPlaceHolderindex",num+"");
                htmlContent =    htmlContent.replace("jpg/",curDirName+"/");
                File curShellHtmlFile = new File(curDirFile.getParentFile().getAbsolutePath()+File.separator+""+htmlname);
                writeContentToFile(curShellHtmlFile,htmlContent);
            }

        }


        void tryGIFHtmlOperation(File curDirFile , int num){
            String curDirName = curDirFile.getName();
            String curParentDirName = curDirFile.getParentFile().getName();
            System.out.println("curDirFile = "+ curDirFile.getAbsolutePath());
            System.out.println("ParentFile = "+ curDirFile.getParentFile().getAbsolutePath());

            for (int i = 0; i < gifHtmlTemplate_FileList.size(); i++) {
                File HtmlFile = gifHtmlTemplate_FileList.get(i);
                if(!HtmlFile.exists()){
                    System.out.println("注意当前Html文件不存在!  PATH:  " + HtmlFile.getAbsolutePath());
                    continue;
                }
                String htmlname = HtmlFile.getName();
                htmlname = htmlname.replace("G2_Rule12",curDirName);

                String htmlContent = ReadFileContent(HtmlFile);
                htmlContent =    htmlContent.replace("zukgitPlaceHolderindex",num+"");
                htmlContent =    htmlContent.replace("gif/",curDirName+"/");
                File curShellHtmlFile = new File(curDirFile.getParentFile().getAbsolutePath()+File.separator+""+htmlname);
                writeContentToFile(curShellHtmlFile,htmlContent);
            }

        }


        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ "  #_12_mp4   <目标文件夹目录>   ### 把当前目录mp4文件生成 html 播放文件  \n" +
                    Cur_Bat_Name+ "  #_12_gif   <目标文件夹目录>   ### 把当前目录gif文件生成 html 播放文件  \n" +
                    Cur_Bat_Name+ "  #_12_jpg   <目标文件夹目录>   ### 把当前目录jpg文件生成 html 播放文件  \n" ;
        }




    }


    class AllDirSubFile_Order_Rule_11 extends Basic_Rule{

        AllDirSubFile_Order_Rule_11() {
            super("*", 11, 5);
        }



        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ "  *_11    ## (清除原有名称，序列从1开始)把当前所有子目录的文件 当前目录 下的实体文件依次按顺序按类型重新命名!  \n" ;
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList, ArrayList<File> allSubRealFileList) {

            System.out.println("allSubDirFileList = "+ allSubDirFileList.size());
            System.out.println("allSubRealFileList = "+ allSubRealFileList.size());
            if(!allSubDirFileList.contains(curDirFile)){
                allSubDirFileList.add(curDirFile);
            }


            for (int i = 0; i < allSubDirFileList.size(); i++) {
                File dirFileItem = allSubDirFileList.get(i);
                // 获取当前文件夹下的所有依据 文件类型为 .jpg .png .mp4 为key 进行的
                Map<String , ArrayList<File>>  curDirSubRealFile = getCurSubFileMap(dirFileItem);

                // 对文件依次重命名


                Map.Entry<String, ArrayList<File>> entry;
                // 不同的类型文件怎么处理?

                if (curDirSubRealFile != null) {
                    Iterator iterator = curDirSubRealFile.entrySet().iterator();
                    while (iterator.hasNext()) {
                        entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                        String typeStr = entry.getKey();  //Map的Value
                        String typeWithOutPot = typeStr.replace(".","");

                        ArrayList<File> fileArr = entry.getValue();  //Map的Value

                        // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
                        ArrayList<File> curRenamePlace = new    ArrayList<File>();
                        for (int m = 0; m < fileArr.size(); m++) {
                            File curFile = fileArr.get(m);
                            String oldName = curFile.getName();
                            //String curFileName = curFile.getName();

                            System.out.println("═════════════ m="+m+"═════════════");
                            // 占位符  使得  所有文件都命名成功   避免那些已经有该名称了的文件
                            String newName1 = "_ZHolder_"+(m+1)+("".equals(typeWithOutPot)?"":"."+typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
                            if(tryReName(curFile,newName1)){
                                System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                            }else{
                                System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                            }
                            File fileItem2 = new File(curFile.getParentFile().getAbsolutePath()+File.separator+newName1);
                            if(fileItem2.exists()){
                                curRenamePlace.add(fileItem2);


/*                                System.out.println(fileItem2+ " fileItem2.exists() = "+ fileItem2.exists());
                                String newName2 = newName1.replace("_ZHolder_","");

                                if(tryReName(fileItem2,newName2)){
                                    System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                                }else{
                                    System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                                }  */

                            }


                        }
                        System.out.println("════════════════════════════════════════════════════");

                        for (int n = 0; n < curRenamePlace.size(); n++) {
                            System.out.println("═════════════ n="+n+"═════════════");

                            File fileItem2 = curRenamePlace.get(n);
                            String newName2 = fileItem2.getName().replace("_ZHolder_","");
                            if(tryReName(fileItem2,newName2)){
                                System.out.println("成功 Index ="+n+"  命名( "+fileItem2.getName()+" => "+ newName2+")  => "+fileItem2.getAbsolutePath());
                            }else{
                                System.out.println("失败 Index ="+n+"  命名( "+fileItem2.getName()+" => "+ newName2+")  => "+fileItem2.getAbsolutePath());
                            }
                        }
                        curRenamePlace.clear();


                    }
                }


            }
            return super.applyDir_SubFileListRule5(allSubDirFileList, allSubRealFileList);
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            if(!curDirList.contains(curDirFile)){
                curDirList.add(curDirFile);
            }


            for (int i = 0; i < curDirList.size(); i++) {
                File dirFileItem = curDirList.get(i);
                // 获取当前文件夹下的所有依据 文件类型为 .jpg .png .mp4 为key 进行的
                Map<String , ArrayList<File>>  curDirSubRealFile = getCurSubFileMap(dirFileItem);

                // 对文件依次重命名


                Map.Entry<String, ArrayList<File>> entry;
                // 不同的类型文件怎么处理?

                if (curDirSubRealFile != null) {
                    Iterator iterator = curDirSubRealFile.entrySet().iterator();
                    while (iterator.hasNext()) {
                        entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                        String typeStr = entry.getKey();  //Map的Value
                        String typeWithOutPot = typeStr.replace(".","");

                        ArrayList<File> fileArr = entry.getValue();  //Map的Value

                        // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
                        ArrayList<File> curRenamePlace = new    ArrayList<File>();
                        for (int m = 0; m < fileArr.size(); m++) {
                            File curFile = fileArr.get(m);
                            String oldName = curFile.getName();
                            //String curFileName = curFile.getName();

                            System.out.println("═════════════ m="+m+"═════════════");
                            // 占位符  使得  所有文件都命名成功   避免那些已经有该名称了的文件
                            String newName1 = "_ZHolder_"+m+("".equals(typeWithOutPot)?"":"."+typeWithOutPot);
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
                            if(tryReName(curFile,newName1)){
                                System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                            }else{
                                System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                            }
                            File fileItem2 = new File(curFile.getParentFile().getAbsolutePath()+File.separator+newName1);
                            if(fileItem2.exists()){
                                curRenamePlace.add(fileItem2);


/*                                System.out.println(fileItem2+ " fileItem2.exists() = "+ fileItem2.exists());
                                String newName2 = newName1.replace("_ZHolder_","");

                                if(tryReName(fileItem2,newName2)){
                                    System.out.println("成功 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                                }else{
                                    System.out.println("失败 Index ="+m+"  命名( "+oldName+" => "+ newName1+")  => "+curFile.getAbsolutePath());
                                }  */

                            }


                        }
                        System.out.println("════════════════════════════════════════════════════");

                        for (int n = 0; n < curRenamePlace.size(); n++) {
                            System.out.println("═════════════ n="+n+"═════════════");

                            File fileItem2 = curRenamePlace.get(n);
                            String newName2 = fileItem2.getName().replace("_ZHolder_","");
                            if(tryReName(fileItem2,newName2)){
                                System.out.println("成功 Index ="+n+"  命名( "+fileItem2.getName()+" => "+ newName2+")  => "+fileItem2.getAbsolutePath());
                            }else{
                                System.out.println("失败 Index ="+n+"  命名( "+fileItem2.getName()+" => "+ newName2+")  => "+fileItem2.getAbsolutePath());
                            }
                        }
                        curRenamePlace.clear();


                    }
                }


            }

            return curDirList;
        }
    }




    // //  zrule_apply_G2.bat  #_10_append  2001   往当前文件夹后缀增加 2001
    // //  zrule_apply_G2.bat  #_10_prefix  2001   往当前文件夹前缀增加 2001
    // //  zrule_apply_G2.bat  #_10_create  1_100   创建一个序列号从1到100的100个文件夹
    // //  zrule_apply_G2.bat  #_10_create  temp_ 1_100   创建一个序列号从temp1到temp100的100个文件夹
    // //  zrule_apply_G2.bat  #_10_create  _temp 1_100   创建一个序列号从1temp到100temp的100个文件夹
    // //  zrule_apply_G2.bat  #_10_create  i_temp 1_100   创建一个序列号从i1temp到i100temp100的100个文件夹

    // //  zrule_apply_G2.bat  #_10_create  7000_7100  创建一个序列号从7000开始的到7100结束的文件夹
    // //  zrule_apply_G2.bat  #_10_replace  abc_DEF  创建一个序列号从7000开始的到7100结束的文件夹



    class DirOperation_Rule_10 extends Basic_Rule{

        String firstParamStr;  //  第一个参数

        int DIR_OPERA_TYPE_APPEND = 1;  // 后缀增加
        String appendStr_1 ;
        int DIR_OPERA_TYPE_PREFIX = 2;  // 前缀增加
        String prefixStr_2;

        int DIR_OPERA_TYPE_CREATE = 3;  // 创建文件
        int beginIndex_3;
        int endIndex_3;
        String prefixStr_3;
        String appendStr_3;

        int DIR_OPERA_TYPE_REPLACE = 4;  // 替换文件夹名称
        String replacedStr_4;
        String newNameStr_4;



        // 识别当前用户 指定的操作类型   1后缀增加  2前缀增加 3创建文件  4替换文件夹名称
        int currentOperaType = 0;




        DirOperation_Rule_10() {
            super("#", 10, 4);
            prefixStr_3="";
            appendStr_3="";
        }

        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {
            boolean falg = true;
            if(currentOperaType == 1){
                appendStr_1 = inputParamList.get(inputParamList.size()-1);
            }else  if(currentOperaType == 2){
                prefixStr_2 = inputParamList.get(inputParamList.size()-1);
            }else if(currentOperaType == 4 ){
                String inputStr = inputParamList.get(inputParamList.size()-1);
                if(!inputStr.contains("_")){
                    falg = false;
                }

                String[] inputArr = inputStr.split("_");

                if(inputArr.length >= 2){

                    replacedStr_4 = inputArr[0];
                    newNameStr_4 = inputArr[inputArr.length-1];
                }else{
                    falg = false;
                }
            }else if(currentOperaType == 3 ){

                for (int i = 0; i < inputParamList.size(); i++) {

                    String paramItem = inputParamList.get(i);
                    if(paramItem != null && paramItem.equals(firstParamStr)){
                        continue;   // 第一个参数不操作
                    }

                    if(!paramItem.contains("_")){
                        falg = false;
                        continue;
                    }
                    String fixedParam = paramItem.replace("_","");

                    if(isNumeric(fixedParam)){  // 如果是 字母 说明是起始的那个参数
                        String[] IndexArr = paramItem.split("_");

                        if(IndexArr.length >= 2){

                            String    beginIndex_3_Str = IndexArr[0];
                            String    endIndex_3_Str = IndexArr[IndexArr.length-1];
                            if(isNumeric(beginIndex_3_Str)){
                                beginIndex_3 = Integer.parseInt(beginIndex_3_Str);

                            }else{
                                falg = false;
                            }

                            if(isNumeric(endIndex_3_Str)){
                                endIndex_3 = Integer.parseInt(endIndex_3_Str);
                            }else{
                                falg = false;
                            }

                        }else{
                            falg = false;
                        }
                    }else{  // 名称的参数
                        if(paramItem.endsWith("_")){
                            appendStr_3 = "";
                            String[] NamePreArr = paramItem.split("_");
                            prefixStr_3= NamePreArr[0];
                            System.out.println("appendStr_3="+appendStr_3+"   prefixStr_3="+prefixStr_3 );


                        }else{
                            String[] NamePreArr = paramItem.split("_");
                            if(NamePreArr.length >= 2){
                                prefixStr_3= NamePreArr[0];
                                appendStr_3= NamePreArr[1];
                                System.out.println("appendStr_3="+appendStr_3+"   prefixStr_3="+prefixStr_3 );

                            }

                        }



                    }




                }


            }


            return super.initParamsWithInputList(inputParamList) || falg;
        }


        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            switch ( currentOperaType){

                case 1:
                    for (int i = 0; i < curDirList.size(); i++) {
                        File dirFile = curDirList.get(i);
                        String dirName = dirFile.getName();
                        String newName = dirName+appendStr_1;
                        tryReName(dirFile,newName);
                    }
                    break;

                case 2:
                    for (int i = 0; i < curDirList.size(); i++) {
                        File dirFile = curDirList.get(i);
                        String dirName = dirFile.getName();
                        String newName = prefixStr_2 + dirName;
                        tryReName(dirFile,newName);
                    }
                    break;

                case 3:
                    for (int i = beginIndex_3; i < endIndex_3 + 1; i++) {
                        String absDirPath = curDirFile.getAbsolutePath();
                        String newDir = absDirPath + File.separator+prefixStr_3+i+appendStr_3;
                        File curDirFileItem =  new File(newDir);
                        curDirFileItem.mkdirs();
                    }
                    break;

                case 4:

                    for (int i = 0; i < curDirList.size(); i++) {
                        File dirFile = curDirList.get(i);
                        String dirName = dirFile.getName();
                        String newName = dirName.replace(replacedStr_4,newNameStr_4);
                        tryReName(dirFile,newName);
                    }

                    break;

                default:
                    System.out.println("当前 currentOperaType = "+ currentOperaType+"  没有找到合适的操作类型去处理 ");
            }



            return curDirList;
        }

        @Override
        boolean initParams4InputParam(String inputParam) {

            firstParamStr = inputParam;
            if(inputParam.contains("append")){
                currentOperaType = 1;
            }else if(inputParam.contains("prefix")){
                currentOperaType = 2;

            }else if(inputParam.contains("replace")){
                currentOperaType = 4;


            }else if(inputParam.contains("create")){
                currentOperaType = 3;

            }

            return super.initParams4InputParam(inputParam);
        }

        @Override
        String simpleDesc() {
            return  "\n"+Cur_Bat_Name+ "  #_10_append  _over   往当前文件夹后缀增加 _over \n" +
                    Cur_Bat_Name+ "  #_10_prefix  temp   往当前文件夹前缀增加 temp \n" +
                    Cur_Bat_Name + " #_10_create  1_100   创建一个序列号从1到100的100个文件夹   \n" +
                    Cur_Bat_Name + " #_10_create   temp_  1_100   创建一个序列号从temp1到temp100的100个文件夹 \n "+
                    Cur_Bat_Name + " #_10_create   _temp  1_100   创建一个序列号从1temp到100temp的100个文件夹 \n "+
                    Cur_Bat_Name + " #_10_create   j_temp  1_100   创建一个序列号从 j_1_temp 到100temp的 j_100_temp 个文件夹 \n "+
                    Cur_Bat_Name + " #_10_create  7000_7100  创建一个序列号从7000开始的到7100结束的文件夹  \n " +
                    Cur_Bat_Name + " #_10_replace  abc_DEF  创建一个序列号从7000开始的到7100结束的文件夹 \n "  ;
        }



    }


    // //  zrule_apply_G2.bat  #_9  _jpg   把没有类型的文件名称修改为 jpg格式名称
    // //  zrule_apply_G2.bat  #_9  jpg_   去除当前jpg的格式 使得其文件格式未知


    // 把 当前目录下子文件 进行格式的转换
    // //  zrule_apply_G2.bat  #_9  _jpg   把没有类型的文件名称修改为 jpg格式名称
    // //  zrule_apply_G2.bat  #_9  jpg_   去除当前jpg的格式 使得其文件格式未知
    //    zrule_apply_G2.bat   #_9  jpg_png  把  jpg的格式转为png的格式
    //    zrule_apply_G2.bat   #_9  png_jpg  把  jpg的格式转为png的格式
    // //  zrule_apply_G2.bat  #_9  gif_   去除当前gif的格式 使得其文件格式未知
    // //  zrule_apply_G2.bat  #_9  _gif   把没有类型的文件名称修改为 jpg格式名称
    // //  zrule_apply_G2.bat  #_9  mp4_   去除当前mp4的格式 使得其文件格式未知
    // //  zrule_apply_G2.bat  #_9  _mp4   把没有类型的文件名称修改为 mp4格式名称
    // //  zrule_apply_G2.bat  #_9  原类型_目标类型   把没有类型的文件名称修改为 jpg格式名称
    class FileType_Rule_9 extends Basic_Rule{
        String originType;
        String targetType;

        FileType_Rule_9() {
            super("#", 9, 3);
        }



        @Override
        String simpleDesc() {
            return  Cur_Bat_Name+ "  #_9  _jpg   把没有类型的文件名称修改为 jpg格式名称\n" +
                    Cur_Bat_Name+ "  #_9  jpg_   去除当前jpg的格式 使得其文件格式未知 \n" +
                    Cur_Bat_Name + " #_9  jpg_png  把  jpg的格式转为png的格式  \n" +
                    Cur_Bat_Name + " #_9  png_jpg  把  jpg的格式转为png的格式 \n "+
                    Cur_Bat_Name + " #_9  gif_   去除当前gif的格式 使得其文件格式未知  \n " +
                    Cur_Bat_Name + " #_9  _gif   把没有类型的文件名称修改为 jpg格式名称  \n " +
                    Cur_Bat_Name + " #_9  png_jpg  把  jpg的格式转为png的格式 \n " +
                    Cur_Bat_Name + " #_9  mp4_   去除当前mp4的格式 使得其文件格式未知 \n " +
                    Cur_Bat_Name + " #_9  _mp4   把没有类型的文件名称修改为 mp4格式名称 \n " +
                    Cur_Bat_Name + " #_9  原类型_目标类型   把没有类型的文件名称【原类型】->【目标类型】 \n " ;
        }

        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {
            boolean Flag = true;

            // 获取到装换的类型
            String inputFileTypeParams = inputParamList.get(inputParamList.size()-1);

            if(!inputFileTypeParams.contains("_")){
                Flag = false;
                System.out.println("无法检测到当前 第9 Rule   原始类型_目标类型参数   请检查后重新执行");
            }else{

                if(inputFileTypeParams.endsWith("_")){
                    String target = "";
                    String[] parmas =   inputFileTypeParams.split("_");
                    String origin = parmas[0];
                    System.out.println("item="+inputFileTypeParams+"   origin="+origin +"     target="+target);
                    originType = origin;
                    targetType = target;

                }else{
                    String[] parmas =   inputFileTypeParams.split("_");
                    System.out.println("item="+inputFileTypeParams+  "   origin="+parmas[0] +"     target="+parmas[1]);
                    originType = parmas[0] ;
                    targetType = parmas[1];
                }

                Flag = true;

            }
            curFilterFileTypeList.add(originType);

            return super.initParamsWithInputList(inputParamList) && Flag;
        }



        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {

            for (int i = 0; i < subFileList.size(); i++) {
                File curFIle = subFileList.get(i);
                String originName = curFIle.getName();
                // 执行 修改文件类型的操作

                // 1. 如果当前文件 过滤类型是 空 那么 可能就是没有任何的类型了
                // 如果当前过滤的类型是  originType 是"" 空的话  那么就会过滤出所有的文件 那么只操作 不包含.的那些文件
                if("".equals(originType)){
                    if(originName.contains(".")){
                        continue; //  包含了 . 说明有类型 那么 不操作
                    }
                    String newName = originName + "."+targetType;
                    tryReName(curFIle,newName);
                }else{
                    // 有具体的 过滤的文件
                    String oldType = "."+originType;
                    String newType = "."+targetType;
                    if("".equals(targetType)){
                        newType = "";
                    }

                    if(originName.contains(oldType)){
                        String newName =  originName.replace(oldType,newType);
                        tryReName(curFIle,newName);
                    }


                }

            }

            return subFileList;
        }
    }

    // 把文件后缀中的中文给去除掉  不包含文件夹   不包含孙文件
    class ClearChineseType_8 extends Basic_Rule{


        ClearChineseType_8() {
            super("#", 8, 4);
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            System.out.println("Rule8_ClearChineseType_8   搜索到的实体文件个数:" + curRealFileList.size());

            for (int i = 0; i < curRealFileList.size() ; i++) {
                File curFile = curRealFileList.get(i);
                String currentFileName =  curFile.getName();
                if(currentFileName.contains(".")){
                    String typeStr = currentFileName.substring(currentFileName.lastIndexOf("."));
                    if(isContainChinese(typeStr)){
                        //        //清除中文  清除 空格
                        String newType =  clearChinese(typeStr).replace(" ","");
                        String newName = currentFileName.replace(typeStr,newType);  // 新名称
                        System.out.println("newType = "+newType + "    newName="+newName);
                        tryReName(curFile,newName);
                    }
                }


            }


            return curRealFileList;
        }

        @Override
        String simpleDesc() {
            return "把当前命令的文件包含.的文件的 后缀名称中的中文清除掉  例如 1.7啊z -> 1.7z   2.你zip -> 2.zip \n"+
                    Cur_Bat_Name + " 8     [索引8]   // 把当前目录下文件 后缀中文去除  \n";
        }
    }

    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作


    // 对当前目录的文件进行加密 解密
    class Encropty_Rule_7 extends Basic_Rule{
        boolean mEncroptyDirect = true;  //  true---加密      false--解密
        boolean isAllFileOperation = false;
        Encropty_Rule_7() {
            super("#", 7, 4);
            isAllFileOperation = false;

        }

        @Override
        boolean initParams4InputParam(String inputParam) {
            if(inputParam.contains("good")){
                mEncroptyDirect = false;
            }else {
                mEncroptyDirect = true;
            }

            if(inputParam.contains("*")){
                isAllFileOperation = true;
            }else {
                isAllFileOperation = false;
            }

            return  super.initParams4InputParam(inputParam);
        }

        @Override
        String simpleDesc() {
            return "   默认bad(加密) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad/解密good\n" +
                    Cur_Bat_Name+ " #_7_bad   (默认--加密文件)  把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 加密bad\n" +
                    Cur_Bat_Name+ " #_7_good   (加密文件) 把当前目录下的所有文件(不包含文件夹  不包含孙文件)进行 解密good\n" +
                    Cur_Bat_Name + " jpg_7_bad  [索引7]   // 把当前目录下的 jpg文件 加密 \n" +
                    Cur_Bat_Name + " jpg_7_good  [索引7]   // 把当前目录下的 jpg文件 解密 \n" +
                    Cur_Bat_Name + " *_7_bad  [索引7]   // 把当前目录所有文件进行加密  加密文件在新的 时间戳文件夹中 \n" +
                    Cur_Bat_Name + " *_7_good  [索引7]   // 把当前目录所有文件进行解密  解密文件在新的 时间戳文件夹中 \n" ;

        }

//                    return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" +
//        Cur_Bat_Name + "  jgm_5_recovery  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" +
//        Cur_Bat_Name + "  jgm_5_nextstep  [索引5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4+" ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";


        void jiamiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            // 1.创建一个时间戳文件夹
            // 2.在当前文件夹的基础上

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
            String date = df.format(new Date());
            String CurBadDirName = "bad_AllFile_"+ date;
            File curBadDirFile = new File(curDirFile.getAbsolutePath()+ File.separator+CurBadDirName);
            curBadDirFile.mkdirs();
            String oldBasePath = curDirFile.getAbsolutePath();
            String newBasePath = curBadDirFile.getAbsolutePath();
            System.out.println("执行当前所有文件 加密操作  ");

            if(!curDirList.contains(curDirFile)){
                curDirList.add(curDirFile);
            }

            for (int i = 0; i < curDirList.size(); i++) {
                File oldDirFile = curDirList.get(i);
                String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
                File newDirFile = new File(newDirFilePath);
                newDirFile.mkdirs();


                for (int j = 0; j < oldDirFile.listFiles().length; j++) {

                    File oldRealFile = oldDirFile.listFiles()[j];
                    if(oldRealFile.isDirectory()){
                        continue;
                    }

                    String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
                    File newRealFile = new File(newRealFilePath);
                    // 加密操作
                    createEncryFile(oldRealFile,newRealFile);
                }


            }

/*
           for (int i = 0; i < curRealFileList.size(); i++) {
               File oldRealFile = curRealFileList.get(i);
               String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
               File newRealFile = new File(newRealFilePath);
               // 加密操作
               createEncryFile(oldRealFile,newRealFile);
           }
*/



        }

        void jiemiAllDir(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            // 1.创建一个时间戳文件夹
            // 2.在当前文件夹的基础上

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
            String date = df.format(new Date());
            String CurBadDirName = "good_AllFile_"+ date;
            File curBadDirFile = new File(curDirFile.getAbsolutePath()+ File.separator+CurBadDirName);
            curBadDirFile.mkdirs();
            String oldBasePath = curDirFile.getAbsolutePath();
            String newBasePath = curBadDirFile.getAbsolutePath();
            if(!curDirList.contains(curDirFile)){
                curDirList.add(curDirFile);
            }
            System.out.println("执行当前所有文件 解密操作 ");

            for (int i = 0; i < curDirList.size(); i++) {
                File oldDirFile = curDirList.get(i);
                String newDirFilePath = oldDirFile.getAbsolutePath().replace(oldBasePath, newBasePath);
                File newDirFile = new File(newDirFilePath);
                newDirFile.mkdirs();

                for (int j = 0; j < oldDirFile.listFiles().length; j++) {

                    File oldRealFile = oldDirFile.listFiles()[j];
                    if(oldRealFile.isDirectory()){
                        continue;
                    }

                    String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
                    File newRealFile = new File(newRealFilePath);
                    // 解密操作
                    createDecryFile(oldRealFile,newRealFile);
                }
            }


//            for (int i = 0; i < curRealFileList.size(); i++) {
//                File oldRealFile = curRealFileList.get(i);
//                String newRealFilePath = oldRealFile.getAbsolutePath().replace(oldBasePath, newBasePath);
//                File newRealFile = new File(newRealFilePath);
//                // 加密操作
//                createDecryFile(oldRealFile,newRealFile);
//            }

        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            System.out.println("Rule7 搜索到的实体文件个数:  curRealFileList.size() =" + curRealFileList.size());
            if(isAllFileOperation){
                if(mEncroptyDirect){
                    // 加密所有文件夹

                    jiamiAllDir(curFileList,subFileTypeMap,getAllSubDirFile(curDirFile),curRealFileList);
                }else{
                    // 解密当前所有文件夹
                    jiemiAllDir(curFileList,subFileTypeMap,getAllSubDirFile(curDirFile),curRealFileList);

                }
                return null;
            }
            boolean containUserType = curFilterFileTypeList.contains("#");  // 是否包含用户选中的了文件类型  没有包含 那么就把所有实体realty 加密

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            String curNewDirName = date;
            if(mEncroptyDirect){
                curNewDirName += "_bad";
            }else{
                curNewDirName += "_good";
            }

            if(!containUserType){
                curNewDirName += "_"+curFilterFileTypeList.get(0);  //  1.如果所有文件都加密  那么没有后缀 如果某一个文件类型解密 那么添加后缀
            }

            File tempDirFile = new File(curDirFile.getAbsolutePath()+File.separator+curNewDirName);
            tempDirFile.mkdirs();  // 创建文件夹
            String tempDirPath = tempDirFile.getAbsolutePath();
            System.out.println("Rule7 搜索到的实体文件个数:" + curRealFileList.size());

            for (int i = 0; i < curRealFileList.size() ; i++) {
                File curFile = curRealFileList.get(i);
                String currentFileName = File.separator+curFile.getName();


//                System.out.println("currentFileName = "+ currentFileName);
                if(mEncroptyDirect){   // 加密时  如果是 以   i_temp 开头   并且 以 .jpg 为结尾时   加密的类型去掉
                    if(currentFileName.contains(".jpg") && currentFileName.contains("i_temp")  ){
                        currentFileName = currentFileName.replace(".jpg","");
                    }else  if(currentFileName.contains(".mp4") && currentFileName.contains("v_temp")){
                        currentFileName = currentFileName.replace(".mp4","");
                    }else  if(currentFileName.contains(".gif") && currentFileName.contains("g_temp")){
                        currentFileName = currentFileName.replace(".gif","");
                    }

                    File badFile = new File(tempDirPath+File.separator+currentFileName);
                    createEncryFile(curFile,badFile);
                }else{  // 解密    如果当前文件 不包含 .
                    if(!currentFileName.contains(".") && currentFileName.contains("i_temp")){
                        currentFileName = currentFileName + ".jpg";
                    }else  if(!currentFileName.contains(".") && currentFileName.contains("v_temp")){
                        currentFileName = currentFileName + ".mp4";
                    }else  if(!currentFileName.contains(".") && currentFileName.contains("g_temp")){
                        currentFileName = currentFileName + ".gif";
                    }
                    File goodFile = new File(tempDirPath+File.separator+currentFileName);
                    createDecryFile(curFile,goodFile);
                }
            }

            return null;
        }
    }
    class SubDirRename_Rule_6 extends Basic_Rule{


        SubDirRename_Rule_6() {
            super("#", 6, 4);
        }

        @Override
        String simpleDesc() {

            return  Cur_Bat_Name +" #_6    // 修改当前的一级子目录下的文件夹 以及文件  按顺序命令 【序号_原名称.类型】  (不操作 孙文件 孙文件夹 )  \n" +
                    Cur_Bat_Name + " png_6    // 修改当前的一级子目录下的文件夹下的 png格式文件  按顺序命令 【序号_原名称.类型】  (不操作 孙文件 孙文件夹 ) \n";
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {

            boolean executeFlag = false;
            boolean isFixedAllSubFlag = curFilterFileTypeList.contains("#");
            if(isFixedAllSubFlag){  //  只有包含  #_6 才对 文件夹进行操作    png_6  那么就只对 当前文件夹下的 png文件进行操作
                for (int i = 0; i < curDirList.size(); i++) {
                    File dir = curDirList.get(i);
                    String dirName = dir.getName();
                    String new_dirName = i+"_"+dirName;
                    tryReName(dir,new_dirName);
                }
            }

            Map.Entry<String, ArrayList<File>> entry;
            // 不同的类型文件怎么处理?

            if (subFileTypeMap != null) {
                Iterator iterator = subFileTypeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    String typeWithOutPot = typeStr.replace(".","");

                    if(!isFixedAllSubFlag &&  !curFilterFileTypeList.contains(typeWithOutPot)){
                        //   如果   当前操作不是操作所有文件    并且这个类型不在匹配列表中   那么 不执行  返回next
                        //   如果是 全 操作    那么 往下执行
                        // 如果不是全操作     当前类型包含   那么往下执行
                        continue;
                    }

                    ArrayList<File> fileArr = entry.getValue();  //Map的Value

                    // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;
                    for (int i = 0; i < fileArr.size(); i++) {
                        File curFile = fileArr.get(i);
                        //String curFileName = curFile.getName();
                        String newName = i+"_"+curFile.getName();
//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;
                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
                    }
                }
            }
            return executeFlag?curRealFileList:super.applySubFileListRule4(curFileList, subFileTypeMap, curDirList, curRealFileList);
        }
    }

    // 把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式
    class AVI_Rule_5 extends Basic_Rule{
        String tempTag = "temp";
        boolean isTemp;  // 是否是零时起的编号
        int mTempBeginIndex = 0; //  零时编号的默认起始地址

        boolean isRecovrty = false; //  当前是否是  读取当前目录  计算 ProPerities的值的操作
        boolean isEnable = true;  // 当存在增量的时候  不起作用    不执行 记录的操作
        boolean isExistAddPart = false;  // 是否存在增量
        boolean executeNextStep = false;  // 当用户输入的 输入参数 包含 nextstep 时 执行 增量的 重置0操作 添加到index的操作


        int jpgBeginIndex = 0;
        int fixed_jpg_BeginIndex = 0;
        String jpgtag = "i";
        int jpgDirTempIndex = 0;
        int jpgEndIndex = 1;
        int nextStepCountJPG = 0 ; //  当前 JPG的 增量


        int gifBeginIndex = 0;
        String giftag = "g";
        int gifDirTempIndex = 0;
        int fixed_gif_BeginIndex = 0;
        int gifEndIndex = 1;
        int nextStepCountGIF = 0 ; //  当前 GIF的 增量

        int mp4BeginIndex = 0;   // 从 Propertities 中读取到的值
        String mp4tag = "v";    // mp4的前缀
        int mp4DirTempIndex = 0;   //  依据 mp4BeginIndex 计算出的 temp1 temp2 .... temp100
        int fixed_mp4_BeginIndex = 0;   // 在当前 tempx 中的索引   大小为 mp4BeginIndex%1000
        int mp4EndIndex = 1;   // 最后保存到 Propertities 中的 值
        int nextStepCountMP4 = 0 ; //  当前 MP4 的 增量


        AVI_Rule_5() {
            super("jgm", 5, 3);
            curFilterFileTypeList.add("jpg");
            curFilterFileTypeList.add("gif");
            curFilterFileTypeList.add("mp4");
            // 从 Proprietary 拿到当前的总的索引  值
            //  jpgBeginIndex =
            // gifBeginIndex =
            // mp4BeginIndex =
            String strJPGBegin = G2_Properties.getProperty("jpgBeginIndex");
            if(strJPGBegin == null){
                strJPGBegin = "0";
                G2_Properties.put("jpgBeginIndex","0");
            }
            jpgBeginIndex = Integer.parseInt(strJPGBegin);

            String strGIFBegin = G2_Properties.getProperty("gifBeginIndex");
            if(strGIFBegin == null){
                strGIFBegin = "0";
                G2_Properties.put("gifBeginIndex","0");
            }
            gifBeginIndex = Integer.parseInt(strGIFBegin);


            String strMP4Begin = G2_Properties.getProperty("mp4BeginIndex");
            if(strMP4Begin == null){
                strMP4Begin = "0";
                G2_Properties.put("mp4BeginIndex","0");
            }
            mp4BeginIndex = Integer.parseInt(strMP4Begin);


            String strNextStepJPG = G2_Properties.getProperty("nextStepCountJPG");
            if(strNextStepJPG == null){
                strNextStepJPG = "0";
                G2_Properties.put("nextStepCountJPG","0");
            }
            nextStepCountJPG = Integer.parseInt(strNextStepJPG);


            String strNextStepGIF = G2_Properties.getProperty("nextStepCountGIF");
            if(strNextStepGIF == null){
                strNextStepGIF = "0";
                G2_Properties.put("nextStepCountGIF","0");
            }
            nextStepCountGIF = Integer.parseInt(strNextStepGIF);


            String strNextStepMP4 = G2_Properties.getProperty("nextStepCountMP4");
            if(strNextStepMP4 == null){
                strNextStepMP4 = "0";
                G2_Properties.put("nextStepCountMP4","0");
            }
            nextStepCountMP4 = Integer.parseInt(strNextStepMP4);

            if( nextStepCountMP4 != 0 || nextStepCountGIF != 0 || nextStepCountJPG != 0 ){
                isExistAddPart = true;
            }

            jpgDirTempIndex = jpgBeginIndex/1000 ;
            fixed_jpg_BeginIndex = jpgBeginIndex%1000;

            gifDirTempIndex = gifBeginIndex/1000 ;
            fixed_gif_BeginIndex = gifBeginIndex%1000;

            mp4DirTempIndex = mp4BeginIndex/1000 ;
            fixed_mp4_BeginIndex = mp4BeginIndex%1000;

        }

        @Override
        String simpleDesc() {
            return "把 当前目录下所有的 jpg  mp4 gif  都转为 i_temp1_1.jpg    v_temp2_1.mp4   g_temp3_1.gif 的文件格式\n" +
                    Cur_Bat_Name + "  jgm_5_temp0      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为0   \n" +
                    Cur_Bat_Name + "  jgm_5_temp99      [索引5]   // 零时把当前gif jpg mp4 类型 起始位置设置为99   \n" +
                    Cur_Bat_Name + "  jgm_5_recovery  [索引5]   // 在当前 Z_VI 根目录 计算 当前的 JPG GIF MP4的起始值 \n" +
                    Cur_Bat_Name + "  jgm_5_nextstep  [索引5]   //  JPG="+jpgBeginIndex+ " GIF="+gifBeginIndex+" MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4+" ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ";
        }


        @Override
        boolean initParams4InputParam(String inputParam) {

            if(inputParam.contains("temp")){
                int index = inputParam.indexOf("temp")+"temp".length() ;
                String tempIndexStr = inputParam.substring(index);
                if(isNumeric(tempIndexStr)){
                    mTempBeginIndex = Integer.parseInt(tempIndexStr);
                }else{
                    if(tempIndexStr.contains("_")){
                        String blankIndex = tempIndexStr.substring(0,tempIndexStr.indexOf("_"));
                        if(isNumeric(blankIndex)){
                            mTempBeginIndex = Integer.parseInt(blankIndex);
                        }
                    }else{
                        mTempBeginIndex = 0 ;  //  默认为0
                    }
                }

                isTemp = true;
            }
            if(inputParam.contains("nextstep")){
                executeNextStep = true;
            }

            if(inputParam.contains("_recovery")){
                isRecovrty = true;
                isEnable = false;
                curFilterFileTypeList.add("*");  // 把当前所有文件都加入到列表中
            }
            System.out.println("OLD记录的Properties信息:(OLD)   "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4 );

            if( executeNextStep){  // 如果存在增量 当前不执行   并且用户是输入的 nextstep的时候  执行 step的更新
                jpgBeginIndex = jpgBeginIndex + nextStepCountJPG;
                gifBeginIndex = gifBeginIndex + nextStepCountGIF;
                mp4BeginIndex = mp4BeginIndex + nextStepCountMP4;
                G2_Properties.setProperty("jpgBeginIndex",""+jpgBeginIndex);
                G2_Properties.setProperty("gifBeginIndex",""+gifBeginIndex);
                G2_Properties.setProperty("mp4BeginIndex",""+mp4BeginIndex);
                G2_Properties.setProperty("nextStepCountJPG",""+0);
                G2_Properties.setProperty("nextStepCountGIF",""+0);
                G2_Properties.setProperty("nextStepCountMP4",""+0);
                isEnable = false;
            }

            return super.initParams4InputParam(inputParam);
        }

        void  tryDynamicCalCulateBeginIndex(ArrayList<File> subFileList ){

            String jpg_pre = "i_temp";
            ArrayList<File> jpgTempList = new ArrayList<File>();

            String gif_pre = "g_temp";
            ArrayList<File> gifTempList = new ArrayList<File>();

            String mp4_pre = "v_temp";
            ArrayList<File> mp4TempList = new ArrayList<File>();



            for (int i = 0; i < subFileList.size(); i++) {
                File curFile = subFileList.get(i);
                if(curFile.getAbsolutePath().contains("Z_VI")){
                    if(curFile.getName().startsWith(jpg_pre)){
                        jpgTempList.add(curFile);
                    }else if(curFile.getName().startsWith(gif_pre)){
                        gifTempList.add(curFile);
                    }else if(curFile.getName().startsWith(mp4_pre)){
                        mp4TempList.add(curFile);
                    }
                }

            }

            if(jpgTempList.size() == 0 && gifTempList.size() == 0 && mp4TempList.size() == 0  ){
                System.out.println("当前执行目录不在 Z_VI的根目录 Git_Dir , 请重新执行 "+Cur_Bat_Name);
                return;
            }

            //  通过 搜索 计算得到的 type 文件的 长度  Count
            //  通过 计算 文件最后的名字得到的  index = Count - 1
            int jpgDynimicCount = jpgTempList.size() ;
            int gifDynimicCount = gifTempList.size();
            int mp4DynimicCount = mp4TempList.size();

            jpgTempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });

            gifTempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });

            //          Comparable VICompare = new Comparable()
            mp4TempList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int o1Index =   calculIndexFromName(o1.getName());

                    int o2Index =       calculIndexFromName(o2.getName());
                    if(o1Index < o2Index ){
                        return -1;
                    }
                    if(o1Index == o2Index){
                        return 0;
                    }
                    return 1;
                }
            });
            File lastJPGFile = null;
            File lastGIFFile = null;
            File lastMP4File = null;

            if(jpgTempList.size() > 0){
                lastJPGFile = jpgTempList.get(jpgTempList.size()-1);
            }

            if(gifTempList.size() > 0){
                lastGIFFile = gifTempList.get(gifTempList.size()-1);
            }

            if(mp4TempList.size() > 0){
                lastMP4File = mp4TempList.get(mp4TempList.size()-1);
            }

            int jpgLastIndex = 0;
            int gifLastIndex = 0;
            int mp4LastIndex = 0;
            if(lastJPGFile != null){
                jpgLastIndex =  calculIndexFromName(lastJPGFile.getName());
            }
            if(lastGIFFile != null){
                gifLastIndex =  calculIndexFromName(lastGIFFile.getName());

            }
            if(lastMP4File != null){
                mp4LastIndex =  calculIndexFromName(lastMP4File.getName());
            }



            if(jpgDynimicCount != jpgBeginIndex || (jpgLastIndex+1) != jpgDynimicCount){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < jpgTempList.size() ; i++) {
                    File jpgFile = jpgTempList.get(i);
                    String jpgFileName = "i"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(jpgFile,jpgFileName);
                }
            }

            if(gifDynimicCount != gifBeginIndex || (gifLastIndex+1) != gifDynimicCount){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < gifTempList.size() ; i++) {
                    File gifFile = gifTempList.get(i);
                    String gifFileName = "g"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(gifFile,gifFileName);
                }
            }


            if(mp4DynimicCount != mp4BeginIndex || (mp4LastIndex+1) != mp4DynimicCount ){  // 大小 和 记录的起始点 不一致 那么需要 重新该名称
                for (int i = 0; i < mp4TempList.size() ; i++) {
                    File mp4File = mp4TempList.get(i);
                    String mp4FileName = "v"+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,0,i,3,"0",true);
                    tryReName(mp4File,mp4FileName);
                }
            }



            System.out.println("recovery 搜索到的文件 数量:" + subFileList.size()  );
            if(lastJPGFile != null){
                jpgLastIndex =  calculIndexFromName(lastJPGFile.getName());
                System.out.println("最后一个 JPG 文件的名称为:"+ lastJPGFile.getName() + "  索引:"+jpgLastIndex + (jpgLastIndex !=(jpgBeginIndex -1) ? " 匹配不成功(改名操作)":"匹配成功"));

            }
            if(lastGIFFile != null){
                gifLastIndex =  calculIndexFromName(lastGIFFile.getName());
                System.out.println("最后一个 GIF 文件的名称为:"+ lastGIFFile.getName()+ "  索引:"+ gifLastIndex+ (gifLastIndex !=(gifBeginIndex-1)  ? " 匹配不成功(改名操作)":"匹配成功"));

            }
            if(lastMP4File != null){
                mp4LastIndex =  calculIndexFromName(lastMP4File.getName());
                System.out.println("最后一个 MP4 文件的名称为:"+ lastMP4File.getName() + "  索引:"+ mp4LastIndex+ (mp4LastIndex !=(mp4BeginIndex-1) ? " 匹配不成功(改名操作)":"匹配成功"));
            }
            System.out.println("jpgDynimicIndex(JPG动态计算文件数量)=" + getXsizeString(jpgDynimicCount,7) + "   (最后一个JPG文件名称索引+1)"+getXsizeString(jpgLastIndex+1,7)+" ||    Pro记录 jpgBeginIndex 为:"+ jpgBeginIndex );
            System.out.println("gifDynimicIndex(GIF动态计算文件数量)=" + getXsizeString(gifDynimicCount,7) + "   (最后一个GIF文件名称索引+1)"+getXsizeString(gifLastIndex+1,7)+" ||    Pro记录 gifBeginIndex 为:"+ gifBeginIndex );
            System.out.println("mp4DynimicIndex(MP4动态计算文件数量)=" + getXsizeString(mp4DynimicCount,7) + "   (最后一个MP4文件名称索引+1)"+getXsizeString(mp4LastIndex+1,7)+" ||    Pro记录 mp4BeginIndex 为:"+ mp4BeginIndex );

            recoveryProperities(jpgDynimicCount,gifDynimicCount,mp4DynimicCount);
            System.out.println();
        }

        int calculIndexFromName(String viName){

            String valueA =   viName.replace("_","");
            valueA =  valueA.replace("gif","");
            valueA =  valueA.replace("jpg","");
            valueA =  valueA.replace("mp4","");
            valueA =  valueA.replace("mp3","");
            valueA =  valueA.replace("png","");
            valueA =  valueA.replace("temp","");
            valueA =  valueA.replace("\"","");
            valueA =  valueA.replace(".","");
            valueA =  valueA.replace("(","");
            valueA =  valueA.replace(")","");
            valueA =  valueA.replace("）","");
            valueA =  valueA.replace("（","");

            valueA =  valueA.replace("a","");
            valueA =  valueA.replace("b","");
            valueA =  valueA.replace("c","");
            valueA =  valueA.replace("d","");
            valueA =  valueA.replace("e","");
            valueA =  valueA.replace("f","");
            valueA =  valueA.replace("g","");
            valueA =  valueA.replace("h","");
            valueA =  valueA.replace("i","");
            valueA =  valueA.replace("j","");
            valueA =  valueA.replace("k","");
            valueA =  valueA.replace("l","");
            valueA =  valueA.replace("m","");
            valueA =  valueA.replace("n","");
            valueA =  valueA.replace("o","");
            valueA =  valueA.replace("p","");
            valueA =  valueA.replace("q","");
            valueA =  valueA.replace("r","");
            valueA =  valueA.replace("s","");
            valueA =  valueA.replace("t","");
            valueA =  valueA.replace("u","");
            valueA =  valueA.replace("v","");
            valueA =  valueA.replace("w","");
            valueA =  valueA.replace("x","");
            valueA =  valueA.replace("y","");
            valueA =  valueA.replace("z","");
            valueA =  valueA.replace(" ","").trim();
            int resultIndex = 0;
            try{
                resultIndex = Integer.parseInt(valueA) ;

            }   catch(Exception e){
                resultIndex = 0;
            }

            return resultIndex;
        }
        void  recoveryProperities(int jpg , int gif , int mp4){
            jpgBeginIndex = jpg;
            gifBeginIndex = gif;
            mp4BeginIndex = mp4;
            G2_Properties.setProperty("jpgBeginIndex",""+jpg);
            G2_Properties.setProperty("gifBeginIndex",""+gif);
            G2_Properties.setProperty("mp4BeginIndex",""+mp4);
            G2_Properties.setProperty("nextStepCountJPG",""+0);
            G2_Properties.setProperty("nextStepCountGIF",""+0);
            G2_Properties.setProperty("nextStepCountMP4",""+0);


            System.out.println(" Z_VI(Git_Dir)恢复Pro数:(New)    JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量=0    GIF增量=0   MP4增量=0");
        }

        @SuppressWarnings("unchecked")
        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean executeFlag = false;

            if(isRecovrty){  //  如果是要恢复的的话
                tryDynamicCalCulateBeginIndex(subFileList);
                return null;
            }

            String oldAddPart = "OLD 记录的Properties增量:(OLD)   "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG +"    GIF增量="+nextStepCountGIF + "   MP4增量="+nextStepCountMP4;
            if(!isEnable){
                System.out.println("当前 Rule5 规则上的增量已经置0  增量已得到确认  请开始累计新的资源! " );
                System.out.println("当前记录到Prop的增量信息:(New)  "+" JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+0 +"    GIF增量="+0 + "   MP4增量="+0 );

                return null;
            }
            if(isExistAddPart){
                System.out.println("当前 Rule5 规则存在上次还未确认的增量 请执行如下命令来确认增量 使得NextStep完成\n" +
                        Cur_Bat_Name + " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
            }
            Map.Entry<String, ArrayList<File>> entry;
            int nextStepCountJPG_new = 0 ;
            int nextStepCountGIF_new = 0 ;
            int nextStepCountMP4_new = 0 ;
            if (fileTypeMap != null) {
                Iterator iterator = fileTypeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileArr = entry.getValue();  //Map的Value
                    String typeTag = jpgtag;
                    String dirTempIndex = tempTag+jpgDirTempIndex;
                    int tempIndex = 1;
                    int fixedFileIndex = 0 ;
                    if(".jpg".equals(typeStr)){
                        typeTag = jpgtag;
                        dirTempIndex = tempTag+jpgDirTempIndex;
                        fixedFileIndex =  jpgBeginIndex;
                        tempIndex = jpgDirTempIndex;
                        nextStepCountJPG =  fileArr.size();
                        nextStepCountJPG_new =  fileArr.size();
                        if(!isTemp)   G2_Properties.setProperty("nextStepCountJPG",""+nextStepCountJPG);
                        jpgEndIndex = jpgBeginIndex + fileArr.size();
                        System.out.println("当前JPG起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                    } else if (".mp4".equals(typeStr)){
                        typeTag = mp4tag;
                        dirTempIndex = tempTag+mp4DirTempIndex;
                        fixedFileIndex =  mp4BeginIndex;
                        tempIndex = mp4DirTempIndex;
                        nextStepCountMP4 =  fileArr.size();
                        nextStepCountMP4_new =  fileArr.size();
                        if(!isTemp)  G2_Properties.setProperty("nextStepCountMP4",""+nextStepCountMP4);

                        mp4EndIndex = mp4BeginIndex + fileArr.size();
                        System.out.println("当前MP4起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                    } else if(".gif".equals(typeStr)) {
                        typeTag = giftag;
                        dirTempIndex = tempTag+gifDirTempIndex;
                        fixedFileIndex =  gifBeginIndex;
                        tempIndex = gifDirTempIndex;
                        nextStepCountGIF =  fileArr.size();
                        System.out.println("当前GIF起始值:"+fixedFileIndex+"    当前GIF的文件长度:"+ fileArr.size() );
                        nextStepCountGIF_new =  fileArr.size();
                        if(!isTemp)  G2_Properties.setProperty("nextStepCountGIF",""+nextStepCountGIF);
                        gifEndIndex = gifBeginIndex + fileArr.size();

                    }else{
                        continue;
                    }

                    if(isTemp){
                        fixedFileIndex = mTempBeginIndex ;  // 如果是 temp 那么 默认 就把  temp转为 index
                        nextStepCountJPG_new = 0 ;
                        nextStepCountGIF_new = 0 ;
                        nextStepCountMP4_new = 0 ;
                    }
                    // 从 000 开始
//                    fixedFileIndex = fixedFileIndex ;


                    for (int i = 0; i < fileArr.size(); i++) {

                        File curFile = fileArr.get(i);
                        //String curFileName = curFile.getName();
                        String newName = typeTag+"_"+getPaddingIntStringWithDirIndexFileNameWithIndex(tempTag,gifDirTempIndex,fixedFileIndex,i,3,"0",true)+typeStr;

//                        String newName = typeTag+"_"+dirTempIndex+"_"+getPaddingIntString(fixedFileIndex,3,"0",true)+typeStr;

                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
//                        fixedFileIndex++;
                    }
                }
            }

            String NewAddPart = "New 记录的Properties增量:(New)   "+ " JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+"  JPG增量="+nextStepCountJPG_new +"    GIF增量="+nextStepCountGIF_new + "   MP4增量="+nextStepCountMP4_new;

            System.out.println("══════════确认增量信息 Begin══════════");
            if(isExistAddPart){
                //     System.out.println("Rule5 上次的增量情况:");
                System.out.println(oldAddPart);
            }else{
                System.out.println("OLD     上次的不存在增量:(OLD)    JPG="+jpgBeginIndex + "   GIF="+gifBeginIndex +"   MP4="+mp4BeginIndex+" JPG增量=0     GIF增量=0     MP4增量=0");
            }
            //    System.out.println("\nRule5 现在的增量情况: ");
            System.out.println(NewAddPart);

            System.out.println("New 现在使用如下命令把 New 当前的增量进行确认! \n" +
                    Cur_Bat_Name + " jgm_5_nextstep      // ▲【 把jpg gif png的增量添加到 beginIndex 然后增量置0 】 \n ");
            System.out.println("══════════确认增量信息 End══════════");
            if(executeFlag){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }


        // 从 起始的地址  beginIndex 开始计算
        String getPaddingIntStringWithDirIndexFileNameWithIndex(String cTempTag , int CurrentTempIndex,int beginIndex , int index , int padinglength , String oneStr , boolean dirPre){

            int indexIdentify = beginIndex + index ;
            int tempIndexResult =  (indexIdentify/1000) ;
            String result = ""+getXsizeString((indexIdentify%1000),oneStr,padinglength,dirPre);
            return cTempTag + tempIndexResult+"_"+ result;

        }


        //  不从起始的地址 计算    从0，1,2,3.... 开始计算
        String  getPaddingIntStringWithDirIndexFileName(String cTempTag , int CurrentTempIndex,int index , int padinglength , String oneStr , boolean dirPre){

            int tempIndexA =  (index/1000);
            int tempIndexResult = CurrentTempIndex + tempIndexA;

            String result = ""+getXsizeString((index%1000),oneStr,padinglength,dirPre);

 /*           int length = (""+index).length();

            if(length < padinglength){
                int distance = padinglength  - length;
                for (int i = 0; i < distance; i++) {
                    if(dirPre){
                        result = oneStr+result;
                    }else{
                        result = result + oneStr;
                    }

                }

            }*/

            return cTempTag + tempIndexResult+"_"+ result;

        }


        String  getXsizeString(int index , int paddingSize ){

            return   getXsizeString(index," ",paddingSize,true);

        }

        String  getXsizeString(int index , String charOne , int paddingSize ,boolean directPre ){
            String result = (""+index);
            int length = (""+index).length();
            if(length < paddingSize){
                int distance = paddingSize  - length;
                for (int i = 0; i < distance; i++) {
                    if(directPre){
                        result = charOne+result;
                    }else{
                        result = result+charOne;
                    }

                }
            }
            return result;
        }

    }

    // 把 当前目录下所有的 png jpeg 都转为 jpg的格式
    class Image2Png_Rule_4 extends Basic_Rule{
        String targetFileType = ".png";
        Image2Png_Rule_4() {
            super("png", 4, 3);
            curFilterFileTypeList.add(".jpeg");
            curFilterFileTypeList.add(".JPEG");
            curFilterFileTypeList.add(".jpg");
            curFilterFileTypeList.add(".JPG");
            curFilterFileTypeList.add(".PNG");
            targetFileType = ".png";
        }

        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean falg = false;
            for (int i = 0; i < subFileList.size(); i++) {
                File imageFile = subFileList.get(i);
                String fileName = imageFile.getName();
                String newName =  fileName.replace(".jpeg",targetFileType);
                newName =  newName.replace(".jpg",targetFileType);
                newName =  newName.replace(".JPEG",targetFileType);
                newName =  newName.replace(".JPG",targetFileType);
                newName =  newName.replace(".PNG",targetFileType);
                if(tryReName(imageFile,newName)){
                    falg = true;
                }
            }

            if(falg){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }

        @Override
        String simpleDesc() {
            return " 把当前目录(包含子目录)所有的 .jpg .jpeg 的文件后缀改为 .png 的文件后缀";
        }
    }


    // 把 当前目录下所有的 png jpeg 都转为 jpg的格式
    class Image2Jpeg_Rule_3 extends Basic_Rule{
        String targetFileType = ".jpg";
        Image2Jpeg_Rule_3() {
            super("jpg", 3, 3);
            curFilterFileTypeList.add(".jpeg");
            curFilterFileTypeList.add(".JPEG");
            curFilterFileTypeList.add(".JPG");
            curFilterFileTypeList.add(".png");
            curFilterFileTypeList.add(".PNG");
            targetFileType = ".jpg";
        }

        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            boolean falg = false;
            for (int i = 0; i < subFileList.size(); i++) {
                File imageFile = subFileList.get(i);
                String fileName = imageFile.getName();
                String newName =  fileName.replace(".jpeg",targetFileType);
                newName =  newName.replace(".png",targetFileType);
                newName =  newName.replace(".JPEG",targetFileType);
                newName =  newName.replace(".PNG",targetFileType);
                newName =  newName.replace(".JPG",targetFileType);
                if(tryReName(imageFile,newName)){
                    falg = true;
                }
            }

            if(falg){
                return curFixedFileList;
            }
            return super.applyFileListRule3(subFileList, fileTypeMap);
        }

        @Override
        String simpleDesc() {
            return " 把当前目录(包含子目录)所有的 .png .jpeg 的文件后缀改为 .jpg 的文件后缀";
        }
    }



    // 指定什么类型的文件在当前使用什么样的规则
    // operation_type  操作类型
    // 1--读取文件内容字符串 进行修改        String applyOperationRule(String origin)
    // 2--对单个文件属性进行修改(文件名称)  对文件内容(字节)--进行修改 File applyFileByteOperationRule(File originFile)
    // 3--对集合文件属性进行修改(文件名称)  对所有子文件--进行修改 ArrayList<File> applyFileByteOperationRule(ArrayList<File> subFileList)
    // index  唯一指定的一种 rule规则

    // file_name_2   *_2   对当前目录下的所有文件进行 文件名称的重新命名 命名规则  在头部添加序号

    class File_Name_Rule_2 extends Basic_Rule{

        // key = type       value =  符合过滤文件规则的名称的文件的集合
        //   HashMap<String, ArrayList<File>> arrFileMap;
        boolean keepOriginalName = true;
        int inputBeginIndex = 0;

        // 是否是按 1.jpg 2,jpg  3.png  4.png 依次命名 而不是  1.jpg 2,jpg  1.png  2.png 类型来命名
        boolean isOrder = false;
        File_Name_Rule_2() {
            super("*", 2, 3);  //
        }




        @SuppressWarnings("unchecked")
        boolean   tryReNameOperation( HashMap<String, ArrayList<File>> arrFileMap ){
            boolean executeFlag = false;
            Map.Entry<String, ArrayList<File>> entry;
            int fileOrderIndex = 0;
//            System.out.println("1 fileOrderIndex = "+ fileOrderIndex);
            if(fileOrderIndex != inputBeginIndex &&  inputBeginIndex != 0){
                fileOrderIndex = inputBeginIndex - 1 ;
            }
//            System.out.println("2 fileOrderIndex = "+ fileOrderIndex);
//            System.out.println("3 inputBeginIndex = "+ inputBeginIndex);
            if (arrFileMap != null) {
                Iterator iterator = arrFileMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                    String typeStr = entry.getKey();  //Map的Value
                    ArrayList<File> fileArr = entry.getValue();  //Map的Value

                    for (int i = 0; i < fileArr.size(); i++) {
                        fileOrderIndex++;
                        int index = i + 1;
                        String newNamePre = index+"_";
                        File curFile = fileArr.get(i);
                        String curFileName = curFile.getName();
                        String newName = "";
                        if( keepOriginalName ){
                            if(isOrder){  // 按顺序依次  不按 type了  一直走
                                newName = fileOrderIndex+"_"+curFileName;
                            }else{
                                newName = newNamePre + curFileName;
                            }
                        }else{
                            // 如果不保留名称  那么没有类型的文件 将只有 序号  没有类型
                            if("unknow".equals(typeStr)){
                                newName = index+"";
                            }else{
                                if(isOrder){  // 按顺序依次  不按 type了  一直走
                                    newName = fileOrderIndex+typeStr;
                                }else{
                                    newName = index+typeStr;
                                }

                            }
                        }
                        if(tryReName(curFile,newName)){
                            executeFlag = true;
                        }
                    }

                }
            }

            return executeFlag;
        }






        @Override
        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap ){

            if(tryReNameOperation(fileTypeMap)){
                return curFixedFileList;
            }

            return super.applyFileListRule3(subFileList , fileTypeMap );
        }

        boolean  initParams4InputParam(String inputParams){
            if(inputParams.contains("_false")){
                keepOriginalName = false;
            }else{
                keepOriginalName = true;
            }

            if(inputParams.contains("_order")){
                isOrder = true;
            }else{
                isOrder = false;
            }

            if(inputParams.contains("_")){
                String[] inputParamArr = inputParams.split("_");
                if(inputParamArr.length > 0 && isNumeric(inputParamArr[inputParamArr.length-1].trim())){
                    inputBeginIndex = Integer.parseInt(inputParamArr[inputParamArr.length-1].trim());
//                    System.out.println(" 0 inputBeginIndex = "+ inputBeginIndex);
                }
            }

            return super.initParams4InputParam(inputParams);




        }

        String ruleTip(String type,int index , String batName,OS_TYPE curType){
            String itemDesc = "";
            String desc_true = " (保留原名称) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型】的形式 例如 hello.jpg =》 1_hello.jpg  xx.jpg-》2_xx.jpg    001/4.jpg -> 001/3_4.jpg(不同文件夹)   保留原有名称 相同类型文件不同文件夹 使用同一个序列号   ";
            String desc_true_1 = " (保留原名称_按类型依次从1开始 order ) 把当前的所有子文件(非目录)重命名为 【序号_原始名称.类型 走到底】的形式 例如 hello.jpg =》 1_hello.jpg  xx.jpg-》2_xx.jpg   aa.png -> 3_aa.png  | 001/4.zip ->  001/4_4.zip  保留原有名称 不相同类型文件不同文件夹 使用同一个序列号 ";
            String desc_true_2 = " (保留原名称_依照输入索引为起始 order ) 把当前的所有子文件(非目录)重命名为 【自定义序号_原始名称.类型 走到底】的形式 例如 *_2_false_order_50  hello.jpg =》 50_hello.jpg  xx.jpg-》51_xx.jpg   aa.png -> 52_aa.png 保留原有名称 不相同类型文件不同文件夹 使用同一个序列号(序号自定义) ";

            String desc_false = "(清除原名称) 把当前的所有子文件(非目录)重命名为 【序号.类型】的形式 例如 hello.jpg =》 1.jpg  xx.png-》1.jpg   不保留原有名称 相同类型文件不同文件夹 使用同一个序列号";
            String desc_false_1 = "(清除原名称_按类型依次 order ) 把当前的所有子文件(非目录)重命名为 【序号.类型 走到底 】的形式 例如 hello.jpg =》 1.jpg  xx.jpg-》2_xx.jpg  xx.png-》3.png  xx.png-》4.png  不保留原有名称 不相同类型文件不同文件夹 使用同一个序列号 ";
            String desc_false_2 = "(清除原名称_按类型 依照输入索引为起始 order ) 把当前的所有子文件(非目录)重命名为 【输入Begin序号.类型 走到底 】的形式 例如   *_2_false_order_10  hello.jpg =》 10.jpg  xx.jpg-》11_xx.jpg  xx.png-》12.png  xx.png-》13.png  不保留原有名称 不相同类型文件不同文件夹 使用同一个序列号(序号自定义) ";

            if(curType == OS_TYPE.Windows){
                itemDesc = batName.trim()+".bat  "+type+"_"+index+"_true" + "    [索引 "+index+"]  描述: "+ desc_true +"\n";
                itemDesc += "\n"+batName.trim()+".bat  "+type+"_"+index+"_true_order" + "    [索引 "+index+"]  描述: "+ desc_true_1+"\n";
                itemDesc += "\n"+batName.trim()+".bat  "+type+"_"+index+"_true_order_20" + "    [指定开始索引 "+index+"]  描述: "+ desc_true_2+"\n";
                itemDesc +="\n"+ batName.trim()+".bat  "+type+"_"+index+"_false" + "    [索引 "+index+"]  描述:" + desc_false+"\n";
                itemDesc +="\n"+ batName.trim()+".bat  "+type+"_"+index+"_false_order" + "    [索引 "+index+"]  描述:" + desc_false_1+"\n";
                itemDesc +="\n"+ batName.trim()+".bat  "+type+"_"+index+"_false_order_10" + "    [指定开始索引 "+index+"]  描述:" + desc_false_2+"\n";

            }else{
                itemDesc = batName.trim()+".sh "+type+"_"+index+"_true" + "    [索引 "+index+"]  描述:"+ desc_true;
                itemDesc += "\n"+batName.trim()+".bat  "+type+"_"+index+"_true_order" + "    [索引 "+index+"]  描述: "+ desc_true_1;
                itemDesc +="\n"+ batName.trim()+".sh  "+type+"_"+index+"_false" + "    [索引 "+index+"]  描述:"+ desc_false;
                itemDesc +="\n"+ batName.trim()+".bat  "+type+"_"+index+"_false_order" + "    [索引 "+index+"]  描述:" + desc_false_1;
            }

            return itemDesc;
        }




    }

    // html_1
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

        String simpleDesc(){
            return " 读取当前的 html文件  然后把所有的 html文件的 <script> </script>  重新放到 </body> 后面";
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
            firstInputIndexStr="";
        }

        String applyStringOperationRule1(String origin){
            return origin;
        }

        File applyFileByteOperationRule2(File originFile){
            return originFile;
        }

        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap ){
            return null;
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return curFileList;
        }


        @Override
        ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList , ArrayList<File> allSubRealFileList){

            return null;
        }

        boolean initParams4InputParam(String inputParam){ firstInputIndexStr =inputParam ; return true ; }

        @Override
        boolean initParamsWithInputList(ArrayList<String> inputParamList) {
            return true;
        }

        String simpleDesc(){
            return null;
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
        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作
String firstInputIndexStr ;
        int operation_type;
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        int rule_index;   //  (type,index)   组成了最基础的唯一键
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        abstract    String applyStringOperationRule1(String origin);
        abstract    File applyFileByteOperationRule2(File originFile);
        abstract    ArrayList<File> applyFileListRule3(ArrayList<File> subFileList , HashMap<String, ArrayList<File>> fileTypeMap);
        abstract    ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList , HashMap<String, ArrayList<File>> subFileTypeMap , ArrayList<File> curDirList ,ArrayList<File> curRealFileList);
        abstract    ArrayList<File> applyDir_SubFileListRule5(ArrayList<File> allSubDirFileList , ArrayList<File> allSubRealFileList);

        abstract    boolean initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串
        abstract    boolean initParamsWithInputList(ArrayList<String> inputParamList);
        abstract   String ruleTip(String type,int index , String batName,OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况
        abstract   String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用

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




    public static void createEncryFile(File generalFile , File encryptFile) {

        int general_position = 0;
        int general_offset = 0;
        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;


        FileOutputStream encryptileOutputStream = null;
        BufferedOutputStream encryptBufferedOutputStream = null;

        try {
            if(!encryptFile.exists()){
                encryptFile.createNewFile();
            }
            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);


            encryptileOutputStream = new FileOutputStream(encryptFile);
            encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);

            if(encryptFile.getAbsolutePath().trim().endsWith("md")){
                while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                    encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
                    encryptBufferedOutputStream.flush();
                }
                // 关闭流
                generalBufferedInputStream.close();
                encryptBufferedOutputStream.close();
                return;

            }


            //  System.out.println("原始文件字节大小:  " + generalBufferedInputStream.available());
            while (general_offset < BYTE_CONTENT_LENGTH_Rule7) {   // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
                general_position = generalBufferedInputStream.read(TEMP_Rule7, general_offset, TEMP_Rule7.length - general_offset);
                if (general_position == -1) {
                    break;
                }
                general_offset += general_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }


            // 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致

            byte[]    encrypt_bytes = encrypt(TEMP_Rule7);

            System.out.println("加密原始文件:"+generalFile.getName()+"  加密前明文大小:" + TEMP_Rule7.length + "   加密后密文大小:" + encrypt_bytes.length);

            //加密后的密文 填充   encryptFile文件的头首部
            encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
            encryptBufferedOutputStream.flush();
            // 从正常的 general文件 读取  BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
            while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
                encryptBufferedOutputStream.flush();
            }
            // 关闭流
            generalBufferedInputStream.close();
            encryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }


    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;

    static {
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey_Rule7.getBytes());
            encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {

        }

    }

    private static Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8]; //认默为0
        for (int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }
        //生成密匙
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }


    // 加密字节数组
    public static byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }


    //密解字节数组
    public static byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }


    static ArrayList<File> getAllSubFile(File dirFile ) {
        ArrayList<String> typeList = new   ArrayList<String>();
        typeList.add("*");
        return  getAllSubFile( dirFile ,null , typeList);
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

    static ArrayList<File> getCurrentSubDirFile(File  rootPath) {
        ArrayList<File> allDirFile = new ArrayList<File>();
        File[] files = rootPath.listFiles();
        for (int i = 0; i < files.length; i++) {
            File fileItem = files[i];
            if(fileItem.isDirectory()){
                allDirFile.add(fileItem);
            }
        }
        return allDirFile;

    }

    static ArrayList<File> getAllSubDirFile(File  rootPath) {
        ArrayList<File> allDirFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath.getAbsolutePath() + File.separator );

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


    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static void createDecryFile(File encryptFile, File decryptFile) {


        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;


        try {
            if(!decryptFile.exists()){
                decryptFile.createNewFile();
            }
            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);


            decryptileOutputStream = new FileOutputStream(decryptFile);
            decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);


            int encrypt_offset = 0;
            int encrypt_position = 0;
            while (encrypt_offset < BYTE_CONTENT_LENGTH_Rule7) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                encrypt_position = encryptBufferedInputStream.read(TEMP_Rule7, encrypt_offset, TEMP_Rule7.length - encrypt_offset);

                if (encrypt_position == -1) {
                    break;
                }
                encrypt_offset += encrypt_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }

            byte[] decrypt_bytes = decrypt(TEMP_Rule7);  // 对加密文件的加密字节进行解密
            System.out.println("解密文件:"+decryptFile.getName()+"  密文加密字节大小:" + TEMP_Rule7.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

            decryptBufferedOutputStream.write(decrypt_bytes);
            decryptBufferedOutputStream.flush();


            // 读取 encryptFile加密文件中正常的字节    BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 解密File(Head已经解密)文件中去
            while ((encrypt_offset = encryptBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                decryptBufferedOutputStream.write(TEMP_Rule7, 0, encrypt_offset);
                decryptBufferedOutputStream.flush();
            }

            encryptBufferedInputStream.close();
            decryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════"+"使用方法列表 Begin"+"═══════════════════"+"\n");
        for (int i = 0; i < realTypeRuleList.size() ; i++) {
            Rule itemRule = realTypeRuleList.get(i);
            String type =  itemRule.file_type;
            int index =  itemRule.rule_index;
            String desc =  itemRule.ruleTip(type , index ,G2_Bat_Name,curOS_TYPE);

/*
            String itemDesc = "";
           if(curOS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_G2.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_G2 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc+"\n");
            count++;
        }
        System.out.println("═══════════════════"+"使用方法列表 End "+"═══════════════════"+"\n");

    }





    static boolean  checkInputParamsOK(){
        boolean inputOk = true;

        for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {
            String curInputStr = Rule_Identify_TypeIndexList.get(i);
            if(!curInputStr.contains("_")){
                return false;
            }


            String[] paramsArr =    curInputStr.split("_");
            if(paramsArr.length < 2){
                continue;
            }
            String type =  paramsArr[0];
            String index =  paramsArr[1];

//          initParams4InputParam
            if(!isNumeric(index)){  //  第二个参数不是 数字 那么 输入格式错误
                return false;
            }
            Rule matchRule = getRuleByIndex(Integer.parseInt(index));
            if(matchRule != null){
                inputOk =     matchRule.initParams4InputParam(curInputStr) &&  matchRule.initParamsWithInputList(Rule_Identify_TypeIndexList);
                return inputOk;
            }

        }

        return inputOk;
    }

    static Rule CurSelectedRule ;
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

        File mCurDirFile = new File(curDirPath);
        curDirFile = new  File(curDirPath);

        if (mKeyWordName.size() == 0) {
            showTip();
            return;
        }

        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }



        if (curDirFile == null || !mCurDirFile.exists() || !mCurDirFile.isDirectory() ) {
            System.out.println("当前执行替换逻辑的文件路径:" + curDirPath+"  不存在! ");
            return;
        }



        //  通过  shell中输入参数来进行操作
        //  Rule_Identify_TypeIndexList.add("html_1");  //  1.添加处理的类型文件  类型_该类型的处理逻辑索引      索引从1开始


        for (int i = 0; i < Rule_Identify_TypeIndexList.size(); i++) {  //  依据文件类型 去找到文件
            // html_1
            String applyRuleString = Rule_Identify_TypeIndexList.get(i);
            String paramsArr[] = applyRuleString.split("_");
            if(paramsArr.length <2){
                continue;
            }
            String curType = paramsArr[0];
            String curApplyRule =  paramsArr[1];
            if(!isNumeric(curApplyRule)){
                continue;
            }
            int ruleIndex = Integer.parseInt(curApplyRule);


            Rule curApplayRule = getRuleByIndex(ruleIndex);
            if(curApplayRule != null){
                CurSelectedRule = curApplayRule;
            }
            if(curApplayRule == null && CurSelectedRule == null ){
                System.out.println("无法匹配到 对应的 index="+ ruleIndex +"  对应的规则 Rule !   可能需要代码添加。");
                continue;   // 继续下一个循环
            }
            if(curApplayRule == null && CurSelectedRule != null){
                return;
            }
            if(curApplayRule.curFilterFileTypeList.size() == 0){
                curApplayRule.curFilterFileTypeList.add(curType);
            }



            ArrayList<File>  typeFileList = new  ArrayList<File>();

            if(curApplayRule.operation_type == 4){  // 对于 类型是 4 的操作  只获取当前 shell 下的文件
                typeFileList.addAll(Arrays.asList(mCurDirFile.listFiles()));
                System.out.println("operation_type == 4 子目录大小: "+typeFileList.size());
            }else{
                typeFileList =  getAllSubFile(mCurDirFile,null,curApplayRule.curFilterFileTypeList);
            }

            if(typeFileList.size() == 0){
                System.out.println("未能搜索到类型列表匹配的文件:  "+ Rule_Identify_TypeIndexList.get(i));
                continue;
            }
            initFileTypeMap(typeFileList);



            if(curApplayRule.operation_type == 4){  // 只对 当前的 子 文件(目录 文件)操作
                //  对当前文件进行整理
                ArrayList<File> subDirList = new  ArrayList<File>();
                ArrayList<File> realFileList = new  ArrayList<File>();

                outCycle: for (int j = 0; j < typeFileList.size(); j++) {
                    File curFile = typeFileList.get(j);
                    if(curFile.isDirectory()){
                        subDirList.add(curFile);
                    }else{

                        if(curApplayRule.curFilterFileTypeList.contains("#")){
                            realFileList.add(curFile);
                        }else{

                            inCycle:  for (int k = 0; k < curApplayRule.curFilterFileTypeList.size(); k++) {
                                String curMatchType =  curApplayRule.curFilterFileTypeList.get(k);
//                                System.out.println("FileName:"+curFile.getName()+"  curMatchType="+curMatchType);
                                if(curFile.getName().endsWith(curMatchType)){
                                    realFileList.add(curFile);
                                    break inCycle;
                                }
                            }

                        }

                    }
                }

                ArrayList<File> resultFileList =   curApplayRule.applySubFileListRule4(typeFileList,CurDirFileTypeMap,subDirList,realFileList);
                if(resultFileList != typeFileList){
                    System.out.println("应用规则:  "+ applyRuleString +" 成功!");
                }else{
                    System.out.println("应用规则:  "+ applyRuleString +" 失败!");
                }

            }
            else if(curApplayRule.operation_type == 3){  // 对所有文件进行的 统一处理的 类型

                ArrayList<File> resultFileList =   curApplayRule.applyFileListRule3(typeFileList,CurDirFileTypeMap);
                if(resultFileList != typeFileList){

                    System.out.println("应用规则:  "+ applyRuleString +" 成功!");
                }else{
                    System.out.println("应用规则:  "+ applyRuleString +" 失败!");
                }


            } else if(curApplayRule.operation_type == 5){  // 对所有文件夹  所有子文件 孙文件 所有 子文件夹 孙文件夹

                ArrayList<File> curAllDirFile =   getAllSubDirFile(curDirFile);  // 获取所有的 文件夹列表   包含 孙子 子文件夹
                ArrayList<File> curAllRealFile =   getAllSubFile(curDirFile);   // 获取所有的 文件 列表 包含 孙子 子文件
                //     FileChannel
//  zukgit operation_type == 5
                System.out.println(" curDirFile = "+ curDirFile.toString());
                System.out.println(" curAllDirFile = "+ curAllDirFile.size());
                System.out.println(" curAllRealFile = "+ curAllRealFile.size());
                curApplayRule.applyDir_SubFileListRule5(curAllDirFile,curAllRealFile);
            }else{

                for (int j = 0; j < typeFileList.size(); j++) {
                    File itemFile =  typeFileList.get(j);
                    String fileCOntent =  ReadFileContent(itemFile).trim();
                    // 2.applyOperationRule  添加处理规则


                    String resultStr = OriApplyOperationRule(curType,curApplyRule,fileCOntent).trim();

                    int currentOperationType = 1;  // 默认操作类型是 读取字符串的内容 进行处理

                    String identy = curType.trim()+curApplyRule.trim();
//                Rule applayRule2Identify = getRuleByIdentify(identy);

                    Rule applayRule4Index = getRuleByIndex(ruleIndex);
//                如果对应相同的 index的 Rule *_2    出现了    MP3_2 的情况  那么就需要把当前的 所有的*的文件 过滤为 mp3的文件
//                if("*".equals(applayRule2Identify.file_type) && !curType.equals(applayRule2Identify.file_type)){
//
//                }


                    if(applayRule4Index != null){
                        currentOperationType =  applayRule4Index.operation_type;
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

                        File resultFile = applayRule4Index.applyFileByteOperationRule2(itemFile);

                        if(resultFile != itemFile ){
                            System.out.println("itemFile["+j+"] 符合规则(File) 应用Rule成功 " + applyRuleString+ "  = " + itemFile.getAbsolutePath() );
                        }else{
                            System.out.println("itemFile["+j+"] 不符合规则(File) = " + itemFile.getAbsolutePath() );
                        }


                    }

                }

            }

        }

        setProperity();
    }


    static  void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CurDirFileTypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CurDirFileTypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CurDirFileTypeMap.put(keyType, fileList);
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

    static Map<String , ArrayList<File>>   getCurSubFileMap(File mDirFile){
        HashMap<String, ArrayList<File>> realFileListMap = new  HashMap<String, ArrayList<File>>(); ;

        for (File curFile : mDirFile.listFiles()) {
            if(curFile.isDirectory()){
                continue;
            }
            String fileName = curFile.getName();

            if (!fileName.contains(".")) {
                String type ="";   //  unknow  没有后缀名的文件
                if (realFileListMap.containsKey(type)) {
                    ArrayList<File> fileList = realFileListMap.get(type);
                    fileList.add(curFile);
                } else {
                    ArrayList<File> fileList = new ArrayList<File>();
                    fileList.add(curFile);
                    realFileListMap.put(type, fileList);
                }
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();

                if (realFileListMap.containsKey(suffix)) {
                    ArrayList<File> fileList = realFileListMap.get(suffix);
                    fileList.add(curFile);
                } else {
                    ArrayList<File> fileList = new ArrayList<File>();
                    fileList.add(curFile);
                    realFileListMap.put(suffix, fileList);
                }
            }
        }

        return realFileListMap;
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

    static ArrayList<Rule> realTypeRuleList = new ArrayList<Rule>();  // 规则的集合


    static Rule getRuleByIndex(int index){
        for (int i = 0; i <realTypeRuleList.size() ; i++) {
            if(realTypeRuleList.get(i).rule_index == index){
                return realTypeRuleList.get(i);
            }
        }
        return null;
    }



    ArrayList<File>  getSubTypeFileWithPoint(File dirFile , String pointType){
        ArrayList<File>  targetFileList = new   ArrayList<File>();
        String fillterFileStr = ""+pointType.toLowerCase();
        if(!dirFile.isDirectory()){
            return targetFileList;
        }
        File[] allSubFileList = dirFile.listFiles();
        for (File curFile: allSubFileList ) {
            String fileName = curFile.getName().toLowerCase();
            if(fileName.endsWith(fillterFileStr)){
                targetFileList.add(curFile) ;
            }
        }

        return targetFileList;
    }



    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }
    static  Rule getRuleByIdentify(String identify){
        for (int i = 0; i <realTypeRuleList.size() ; i++) {
            if(realTypeRuleList.get(i).identify.equals(identify)){
                return realTypeRuleList.get(i);
            }
        }
        return null;
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