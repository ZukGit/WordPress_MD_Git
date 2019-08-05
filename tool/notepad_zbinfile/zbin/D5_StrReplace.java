import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class D5_StrReplace {
   // public static final ArrayList<String> StringArr = new ArrayList<>();
    /*paramString 替换规则:
    1.【z_z = 空格】
    2.【z# = =等号】
    3.【z[ = <】
    4.【z] = >】
    5. 【z`= 引号"】
        */

    // filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
    //  对 当前目录下的 以及 所有子目录下的 txt  md 文件中的 字符串 yyyy   替换为  xxxx


    // filez#"txt_md"srcz#"zhuzhengjie"dstz#"guohuixing" ========== shell  中传递的参数
    // paramString = filez#txt_mdsrcz#zhuzhengjiedstz#guohuixing   ========= Java获取到的参数  把双引号都去掉了

    public static String acceptRule(String param) {
        if (param == null) {
            return null;
        }
        if (param.contains("z_z")) {
            param = param.replaceAll("z_z", " ");
        }
        if (param.contains("z#")) {
            param = param.replaceAll("z#", "=");
        }
        while (param.contains("z[")) {
            param = param.replace("z[", "<");
        }

        if (param.contains("z]")) {
            param = param.replaceAll("z]", ">");

        }
        if (param.contains("z`")) {
            param = param.replaceAll("z`", "\"");
        }
        // accept rule param = pre=<audio><source src#end=/><audio>
        System.out.println("accept rule param = " + param);
        return param;
    }

    static ArrayList<String> fileTypeStrList = new ArrayList<String>();



    static String getDstStrFromParam(String param) {
        String mDstStr = "";
        if (param.indexOf("filez#") < param.indexOf("srcz#") && param.indexOf("srcz#") < param.indexOf("dstz#")) {   // srcz 在 前面
            // filez#"txt_md"srcz#"yyyy"dstz#"xxxx"

            String dstTempStr = param.substring(param.indexOf("dstz#"));
            mDstStr = dstTempStr.replaceAll("dstz#", "");

        } else if (param.indexOf("filez#") > param.indexOf("dstz#") && param.indexOf("dstz#") > param.indexOf("srcz#")) {
            // srcz#"yyyy"dstz#"xxxx"filez#"txt_md"
            String dstTempStr = param.substring(0, param.indexOf("filez#"));
            String dstz = dstTempStr.substring(param.indexOf("dstz#"));
            mDstStr = dstz.replaceAll("dstz#", "");

        } else if (param.indexOf("srcz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("dstz#")) {
            // srcz#"yyyy"filez#"txt_md"dstz#"xxxx"
            String dstTempStr = param.substring(param.indexOf("dstz#"));
            mDstStr = dstTempStr.replaceAll("dstz#", "");


        } else if (param.indexOf("filez#") < param.indexOf("dstz#") && param.indexOf("dstz#") < param.indexOf("srcz#")) {
            // filez#"txt_md"dstz#"xxxx"srcz#"yyyy"

            String dstTempStr = param.substring(0, param.indexOf("srcz#"));
            String dstz = dstTempStr.substring(param.indexOf("dstz#"));
            mDstStr = dstz.replaceAll("dstz#", "");


        } else if (param.indexOf("filez#") > param.indexOf("srcz#") && param.indexOf("srcz#") > param.indexOf("dstz#")) {
            // dstz#"xxxx"srcz#"yyyy"filez#"txt_md"
           String dstTempStr = param.substring(0, param.indexOf("srcz#"));
            mDstStr = dstTempStr.replaceAll("dstz#", "");

        } else if (param.indexOf("dstz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("srcz#")) {
            // dstz#"xxxx"filez#"txt_md"srcz#"yyyy"
            String dstTempStr = param.substring(0, param.indexOf("filez#"));
            mDstStr = dstTempStr.replaceAll("dstz#", "");

        } else {
            System.out.println("当前输出的字符串参数非法参数格式3: ");
            System.out.println("标准格式如下3: \n" + "filez#\"txt_md\"srcz#\"yyyy\"dstz#\"xxxx\"");
        }
        mDstStr = mDstStr.trim();

        return mDstStr;

    }



    static String getSrcStrFromParam(String param) {
        String mSrcStr = "";
        if (param.indexOf("filez#") < param.indexOf("srcz#") && param.indexOf("srcz#") < param.indexOf("dstz#")) {   // srcz 在 前面
            // filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
            String srcTempStr = param.substring(0, param.indexOf("dstz#"));
            String srcz = srcTempStr.substring(param.indexOf("srcz#"));
            mSrcStr = srcz.replaceAll("srcz#", "");

        } else if (param.indexOf("filez#") > param.indexOf("dstz#") && param.indexOf("dstz#") > param.indexOf("srcz#")) {
            // srcz#"yyyy"dstz#"xxxx"filez#"txt_md"
//            String filez = param.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

          String srcTempStr = param.substring(0, param.indexOf("dstz#"));
            mSrcStr = srcTempStr.replaceAll("srcz#", "");


        } else if (param.indexOf("srcz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("dstz#")) {
            // srcz#"yyyy"filez#"txt_md"dstz#"xxxx"
//            String fileTempStr = param.substring(0, param.indexOf("dstz#"));   // srcz#"yyyy"filez#"txt_md"
//            String filez = fileTempStr.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");


            String srcTempStr = param.substring(0, param.indexOf("filez#"));
            mSrcStr = srcTempStr.replaceAll("srcz#", "");

        } else if (param.indexOf("filez#") < param.indexOf("dstz#") && param.indexOf("dstz#") < param.indexOf("srcz#")) {
            // filez#"txt_md"dstz#"xxxx"srcz#"yyyy"
//            String filez = param.substring(0, param.indexOf("dstz#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

            String srcTempStr = param.substring(param.indexOf("srcz#"));
            mSrcStr = srcTempStr.replaceAll("srcz#", "");


        } else if (param.indexOf("filez#") > param.indexOf("srcz#") && param.indexOf("srcz#") > param.indexOf("dstz#")) {
            // dstz#"xxxx"srcz#"yyyy"filez#"txt_md"
//            String filez = param.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");


            String srcTempStr = param.substring(0, param.indexOf("filez#"));
            String srcz = srcTempStr.substring(param.indexOf("srcz#"));
            mSrcStr = srcz.replaceAll("srcz#", "");

        } else if (param.indexOf("dstz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("srcz#")) {
            // dstz#"xxxx"filez#"txt_md"srcz#"yyyy"
//            String fileTempStr = param.substring(0, param.indexOf("srcz#"));   // srcz#"yyyy"filez#"txt_md"
//            String filez = fileTempStr.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

            String srcTempStr = param.substring(param.indexOf("srcz#"));
            mSrcStr = srcTempStr.replaceAll("srcz#", "");


        } else {
            System.out.println("当前输出的字符串参数非法参数格式2: ");
            System.out.println("标准格式如下2: \n" + "filez#\"txt_md\"srcz#\"yyyy\"dstz#\"xxxx\"");
        }
        mSrcStr = mSrcStr.trim();

        return mSrcStr;

    }



    static String getFileTypeFromParam(String param) {
        String fileTypeStr = "";
        if (param.indexOf("filez#") < param.indexOf("srcz#") && param.indexOf("srcz#") < param.indexOf("dstz#")) {   // srcz 在 前面
            // filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
            String filez = param.substring(0, param.indexOf("srcz#"));
            fileTypeStr = filez.replaceAll("filez#", "");
        } else if (param.indexOf("filez#") > param.indexOf("dstz#") && param.indexOf("dstz#") > param.indexOf("srcz#")) {
            // srcz#"yyyy"dstz#"xxxx"filez#"txt_md"
            String filez = param.substring(param.indexOf("filez#"));
            fileTypeStr = filez.replaceAll("filez#", "");
        } else if (param.indexOf("srcz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("dstz#")) {
            // srcz#"yyyy"filez#"txt_md"dstz#"xxxx"
            String fileTempStr = param.substring(0, param.indexOf("dstz#"));   // srcz#"yyyy"filez#"txt_md"
            String filez = fileTempStr.substring(param.indexOf("filez#"));
            fileTypeStr = filez.replaceAll("filez#", "");

        } else if (param.indexOf("filez#") < param.indexOf("dstz#") && param.indexOf("dstz#") < param.indexOf("srcz#")) {
            // filez#"txt_md"dstz#"xxxx"srcz#"yyyy"
            String filez = param.substring(0, param.indexOf("dstz#"));
            fileTypeStr = filez.replaceAll("filez#", "");


        } else if (param.indexOf("filez#") > param.indexOf("srcz#") && param.indexOf("srcz#") > param.indexOf("dstz#")) {
            // dstz#"xxxx"srcz#"yyyy"filez#"txt_md"
            String filez = param.substring(param.indexOf("filez#"));
            fileTypeStr = filez.replaceAll("filez#", "");

        } else if (param.indexOf("dstz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("srcz#")) {
            // dstz#"xxxx"filez#"txt_md"srcz#"yyyy"
            String fileTempStr = param.substring(0, param.indexOf("srcz#"));   // srcz#"yyyy"filez#"txt_md"
            String filez = fileTempStr.substring(param.indexOf("filez#"));
            fileTypeStr = filez.replaceAll("filez#", "");
        } else {
            System.out.println("当前输出的字符串参数非法参数格式: ");
            System.out.println("标准格式如下: \n" + "filez#\"txt_md\"srcz#\"yyyy\"dstz#\"xxxx\"");
        }
        fileTypeStr = fileTypeStr.trim();


        if(!"".equals(fileTypeStr) && fileTypeStr.contains("_")){
            fileTypeStr = fileTypeStr.replaceAll("___","_");  // 容错
            fileTypeStr = fileTypeStr.replaceAll("__","_");  // 容错
            String[] fileType = fileTypeStr.split("_");
            if(fileType != null && fileType.length > 0){
                for (int i = 0; i < fileType.length; i++) {
                    fileTypeStrList.add(fileType[i].trim().toLowerCase());
                }
              }
        }else if(!"".equals(fileTypeStr) && fileTypeStr.length() > 0  && !fileTypeStr.contains("_")) { // 只过滤一种文件  并不包含 _分隔符
            fileTypeStrList.add(fileTypeStr);

        }

        return fileTypeStr;


    }


    // filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
    //  对 当前目录下的 以及 所有子目录下的 txt  md 文件中的 字符串 yyyy   替换为  xxxx

    static String staticSrcStr = "";
   static String staticDstStr = "";

   static ArrayList<File> typeFileList = new  ArrayList<File>();

    public static void main(String[] args) {
        //===============VS-test===============
        String mShellPath = null;
File  curDirFile = null;
        String typeStr = null;
        String srcStr = null;
        String dstStr = null;

        if (args.length >= 2) {
            mShellPath = args[0];
            String paramString = args[1]; //  filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
            System.out.println("mShellPath =" + mShellPath + "  paramString = " + paramString);
            curDirFile = new File(mShellPath);
            if(!curDirFile.exists() || !curDirFile.isDirectory()){

                System.out.println("mShellPath =" + mShellPath +"当前调用路径错误 不是文件夹路径 ");
                return;
            }

            if (paramString != null && paramString.contains("srcz#") && paramString.contains("stz#") && paramString.contains("filez#")) {


                //  获取  各个字段   1. 首先获取需要过滤的文件   filez#"txt_md"srcz#"yyyy"dstz#"xxxx"
                typeStr = getFileTypeFromParam(paramString);
                srcStr = getSrcStrFromParam(paramString);
                dstStr = getDstStrFromParam(paramString);


            } else {
                System.out.println("input argument is empty ! retry input again! 参数输入错误  请确认参数格式!  filez#\"txt_md\"srcz#\"yyyy\"dstz#\"xxxx\"");
                return;
            }
//===============local-test===============
//        String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";


            if ((srcStr != null && srcStr.trim().isEmpty()) || (dstStr != null && dstStr.trim().isEmpty()) || (typeStr != null && typeStr.trim().isEmpty())) {
                System.out.println("Src Or Dst both empty ");
                return;
            } else {
                staticSrcStr = acceptRule(srcStr);   // 有些特殊字符的转换
                staticDstStr = acceptRule(dstStr);
            }


            System.out.println(" typeStr =" + typeStr + "   srcStr =  " + srcStr + "   dstStr =" + dstStr);
            System.out.println(" staticSrcStr =" + staticSrcStr + "   staticDstStr =  " + staticDstStr );




            // 获得 当前目录下所有文件的对应参数的文件
            if(fileTypeStrList.size() > 0){

                initDirFileSet(curDirFile);
                initSimpleFileSet();
                toGetTargetFileSet( fileTypeStrList, allSimpleFileSet );


                for ( File targetFile: allDstFileSet) {
                      operaFile(targetFile);
                }

            }else{
                System.out.println(" 当前文件过滤失败  不知道过滤什么格式的文件  请检查输入参数!");
                return ;

            }

        }
    }

    static ArrayList<File> mFilePathList = new ArrayList<>();  //   工程文件路径
    static Set<File> allDirFileSet = new HashSet<>();  // 工程下所有的 文件夹文件
    static Set<File> allSimpleFileSet = new HashSet<>();   // 当前工程下所有非文件夹的 文件
    static Set<File> allDstFileSet = new HashSet<>();   // 经过过滤 filetype 剩下来的 文件列表
    static int mSumDirNum = 0;

    static void toGetTargetFileSet( ArrayList<String> mFileTypeList , Set<File> mSetFile) {

        for (File itemFile: mSetFile) {
            String fileName = itemFile.getName();
            if(fileName.lastIndexOf(".") > 0 ){

                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).trim().toLowerCase();

                if(suffix == null){
                    continue;
                }

                for (int i = 0; i < mFileTypeList.size(); i++) {
                    if(suffix.equals(mFileTypeList.get(i))){
                        allDstFileSet.add(itemFile);
                    }
                }
            }
        }
    }

    static void initDirFileSet( File  shellDirFile) {
        if (shellDirFile != null && shellDirFile.exists() && shellDirFile.isDirectory()) {
            if (mFilePathList == null) {
                mFilePathList = new ArrayList<File>();
            }

                mFilePathList.add(shellDirFile);
        }
        if (mFilePathList != null && mFilePathList.size() > 0) {
           int sumDirFile = 0;
            for (File itemFile : mFilePathList) {
                if (itemFile != null && !itemFile.exists() && !itemFile.isDirectory()) {
                    System.out.println("Z1");
                    continue;
                }  // 过滤掉非文件夹

                sumDirFile = addAllFileDir(itemFile);
                System.out.println("Z2");
            }
            sumDirFile = mSumDirNum;

        }


    }


  static  int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象
        if (dirFile != null && dirFile.isDirectory()) {
            allDirFileSet.add(dirFile);
            mSumDirNum++;
        }
        if (isEmptyDirFile(dirFile)) {  // 如果是空的文件夹  返回它的内部文件夹数量是0
            return 0;
        }
        ArrayList<File> childDirFile = getChildDirFileMethod(dirFile);
        if (childDirFile != null && childDirFile.size() > 0) {

            for (File dirFileItem : childDirFile) {
                addAllFileDir(dirFileItem);
            }
        }
        return mSumDirNum;
    }



    static boolean isEmptyDirFile(File dirFile) {
        boolean flag = true;
        if (dirFile == null) {
            return true;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                return false;
            }
        }
        return flag;
    }

    static ArrayList<File> getChildDirFileMethod(File dirFile) {
        ArrayList<File> dirFileList = null;  // new   ArrayList<File>();
        if (dirFile == null) {
            return null;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                if (dirFileList == null) {
                    dirFileList = new ArrayList<File>();
                }
                dirFileList.add(childChildList[i]);
            }
        }
        return dirFileList;
    }

   static void initSimpleFileSet() {
        int fileIndex = 1;
        for (File dirFile : allDirFileSet) {
            System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory()) {
                        allSimpleFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }


    static void operaFile(File curFile){


     ArrayList<String> curStringArr = new ArrayList<>();

        if (curFile != null &&  curFile.exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        if (curFile != null) {

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);

                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(curFile), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {
                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        curStringArr.add("");  //  读取到空  那么 添加换行符
                        continue;
                    }
                    indexLine++;
                   // newOneLine = preString + oldOneLine.trim() + endString;
                    newOneLine =  oldOneLine.replaceAll(staticSrcStr,staticDstStr);

                    curStringArr.add(newOneLine);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curFile), "utf-8"));

                for (int i = 0; i < curStringArr.size(); i++) {
                    curBW.write(curStringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !   curFile = "+ curFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                curStringArr.clear();
            }
        } else {
            System.out.println("Failed !");
            curStringArr.clear();
        }

    }
    }