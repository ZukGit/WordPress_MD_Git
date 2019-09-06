import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class E5_FileMake {
    // public static final ArrayList<String> StringArr = new ArrayList<>();
    /*paramString 替换规则:
    1.【z_z = 空格】
    2.【z# = =等号】
    3.【z[ = <】
    4.【z] = >】
    5. 【z`= 引号"】
        */

    //  当前接收    filez#"html"numz#""
// filez#"html_jpg_png_mp4_java_cpp_avi_txt_...."numz#"2141";  // 创建文件的个数
    // 如果没有指定格式  就对所有格式 执行
    // 后缀不可以替换   【 创建新的操作文件去实现】




    public static void main(String[] args) {
        //===============VS-test===============
        String mShellPath = null;
        File  curDirFile = null;
        String typeStr = null;
        String numStr = null;


        if (args.length >= 2) {
            mShellPath = args[0];
            String paramString = args[1]; //  filez#"txt_md"numz#"10"
            System.out.println("mShellPath =" + mShellPath + "  paramString = " + paramString);
            curDirFile = new File(mShellPath);
            if(!curDirFile.exists() || !curDirFile.isDirectory()){

                System.out.println("mShellPath =" + mShellPath +"当前调用路径错误 不是文件夹路径 ");
                System.out.println("参数格式提示:  filez#\"txt_md\"numz#\"10\"#\"");


                return;
            }

            if (paramString != null && paramString.contains("numz#")  && paramString.contains("filez#")) {


                //  获取  各个字段   1. 首先获取需要过滤的文件   filez#"txt_md"numz#"yyyy"#"xxxx"
                typeStr = getFileTypeFromParam(paramString).toLowerCase();  //  获取过滤文件类型  txt_jpg  转为 txt  jpg 转到 ArrayTypeList中
                numStr = getnumStrFromParam(paramString);

                System.out.println(" typeStr ="+ typeStr + "        numStr= "+ numStr);


            } else {
                System.out.println("input argument is empty ! retry input again! 参数输入错误  请确认参数格式!  filez#\"txt_md\"numz#\"10\"");
                return;
            }
//===============local-test===============
//        String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";
//        String preString = "<audio> <source src=\"";
//        String endString = "\" /><audio>";


            // 名字命名中 可能把  DST 设置为  "" 的情况
            if ((numStr != null && numStr.trim().isEmpty()) || (typeStr != null && typeStr.trim().isEmpty()) ) {
                System.out.println("输入的参数格式错误");
                System.out.println("参数格式提示:  filez#\"txt_md\"numz#\"yyyy\"");

                return;
            } else {
                staticnumStr = acceptRule(numStr).trim();   // 有些特殊字符的转换
                // staticDstStr = acceptRule(dstStr);
              //  staticDstStr = "*";
                staticNumInt =  Integer.parseInt(staticnumStr);

                if(staticNumInt <= 0){
                    System.out.println("输入的numz参数格式错误  改参数必须是数值");
                    System.out.println("参数格式提示:  filez#\"txt_md\"numz#\"10\"");
                    return;
                }
            }


            System.out.println(" filez =" + typeStr + "   numz =  " + numStr );



            // 获得 当前目录下所有文件的对应参数的文件
            if(fileTypeStrList.size() > 0 ){
  // 创建文件的操作

                for (int i = 0; i < fileTypeStrList.size(); i++) {

String mFileType = fileTypeStrList.get(i);
                    if(!isSupportType(mFileType)){
                        System.out.println("不支持当前格式 : "+ mFileType+" , 将输出以"+mFileType+"为后缀的空文档");
                    }
ArrayList<File> mTypeFileList = getTypeFileList( curDirFile.getAbsolutePath(),  mFileType , staticNumInt);
//  获得对应文件的实体
 tryDoFillFile(mTypeFileList , mFileType);





                }
                /*
                initDirFileSet(curDirFile);
                initSimpleFileSet();
                toGetTargetFileSet( fileTypeStrList, allSimpleFileSet );
                for ( File targetFile: allDstFileSet) {
                    RenameFileOperation(targetFile);  // 改名的操作
                    // operaFile(targetFile);
                }

                */

            }else{
                System.out.println(" 当前文件过滤失败  不知道过滤什么格式的文件  请检查输入参数!");
                System.out.println("输入参数有误  参数格式提示:  filez#\"txt_md\"numz#\"yyyy\"");
                return ;

            }

        }else{
            System.out.println("输入参数有误  参数格式提示:  filez#\"txt_md\"numz#\"yyyy\"");
        }
    }

    public static boolean isSupportType( String type){
        boolean flag = false ;
        if("jpg".equals(type) || "jpeg".equals(type)  || "bmp".equals(type)  || "wbmp".equals(type) || "png".equals(type)  || "gif".equals(type)  || "bmp".equals(type )
        || ("txt".equals(type)) || ("md".equals(type))  || ("java".equals(type)) || ("cpp".equals(type))
         || ("c".equals(type))  || ("xml".equals(type)) || ("json".equals(type)) || ("bat".equals(type))
                || ("sh".equals(type))   || ("zip".equals(type)) || ("rar".equals(type))
                || ("xlsx".equals(type))  || ("xls".equals(type))  || ("html".equals(type))
                || ("db".equals(type))  || ("js".equals(type))  || ("dll".equals(type)) || ("exe".equals(type))
                || ("gitignore".equals(type))   || ("h".equals(type))    || ("jar".equals(type))
                || ("py".equals(type))  || ("pkt".equals(type))  || ("7z".equals(type)) || ("pdf".equals(type))
                || ("doc".equals(type))  || ("apk".equals(type))  || ("pcapng".equals(type))
                || ("conf".equals(type))   || ("properties".equals(type))  || ("css".equals(type))
                || ("so".equals(type))     || ("bin".equals(type)) || ("raw".equals(type))
        ){
            flag = true;
        }

        return flag;

    }

    public static void tryDoFillFile(ArrayList<File> curFile , String type){
File curTempFile = null;

//        Readers: [JPG, jpg, tiff, pcx, PCX, bmp, BMP, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
//        Writers: [JPG, jpg, tiff, bmp, BMP, pcx, PCX, gif, GIF, WBMP, png, PNG, raw, RAW, JPEG, pnm, PNM, tif, TIF, TIFF, wbmp, jpeg]
        if("jpg".equals(type) || "png".equals(type) || "jpeg".equals(type) || "bmp".equals(type)
                || "wbmp".equals(type) || "raw".equals(type)  ){  // 动态创建文件  文件的内容是数值

            generalPicture(curFile , type );

        } else if (isSupportType(type)){  // 从 E5  目录读取模板 然后输入输出 完成文件的创建


        }else{ // 其他格式 创建空的 后缀文件



        }





    }

    public static void generalPicture(ArrayList<File> picFileList , String type)  {


        for (int i = 0; i < picFileList.size(); i++) {
            int mCurIndex = i ;
            File mCurFile = picFileList.get(i);
            int width = 1200;
            int heigh = 1200;
            BufferedImage bi = new BufferedImage(width,heigh, BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150
//得到它的绘制环境(这张图片的笔)
            Graphics2D g2 = (Graphics2D) bi.getGraphics();
            g2.fillRect(0,0,width,heigh);//填充一个矩形 左上角坐标(0,0),宽500,高500;填充整张图片
            g2.setColor(Color.WHITE);//设置颜色
            g2.fillRect(0,0,width,heigh);//填充整张图片(其实就是设置背景颜色)
            int frontSize = 550;
            int centerx = width/2;
            int centery = heigh/2;
            int showIndex = i + 1;
            g2.setColor(Color.RED);
            Font f =  new Font("宋体",Font.BOLD,frontSize);
            g2.setFont(f); //设置字体:字体、字号、大小
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(showIndex+"", context);
            g2.drawString(showIndex+"",(float)(centerx-bounds.getCenterX()),(float)(centery-bounds.getCenterY())); //向图片上写字符串
            try {
                mCurFile.createNewFile();
                ImageIO.write(bi,type,new FileOutputStream(mCurFile));//保存图片 JPEG表示保存格式
            }catch (Exception e){
                System.out.println("复制图片格式出现异常！");
            }

        }

    }


    public static ArrayList<File> getTypeFileList(String dirAbsPath , String typeName , int fileCount) {
        ArrayList<File> curFileList = new ArrayList<File>();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String typeDir =  dirAbsPath + File.separator + typeName+"_"+date;
        File typeDirFile = new File(typeDir);
        typeDirFile.mkdirs();
        for (int i = 1; i < fileCount + 1; i++) {
            String itemFileStr = typeDirFile.getAbsolutePath()+File.separator+i+"."+typeName;
            File itemFile = new File(itemFileStr);
            curFileList.add(itemFile);
        }
         return curFileList;

    }
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
        String mDstStr = null;
        if (param.indexOf("filez#") < param.indexOf("numz#") && param.indexOf("numz#") < param.indexOf("#")) {   // numz 在 前面
            // filez#"txt_md"numz#"yyyy"#"xxxx"

            String dstTempStr = param.substring(param.indexOf("#"));
            mDstStr = dstTempStr.replaceAll("#", "");

        }  else if (param.indexOf("numz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("#")) {
            // numz#"yyyy"filez#"txt_md"#"xxxx"
            String dstTempStr = param.substring(param.indexOf("#"));
            mDstStr = dstTempStr.replaceAll("#", "");


        } else if (param.indexOf("filez#") > param.indexOf("numz#") && param.indexOf("numz#") > param.indexOf("#")) {
            // #"xxxx"numz#"yyyy"filez#"txt_md"
            String dstTempStr = param.substring(0, param.indexOf("numz#"));
            mDstStr = dstTempStr.replaceAll("#", "");

        } else if (param.indexOf("#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("numz#")) {
            // #"xxxx"filez#"txt_md"numz#"yyyy"
            String dstTempStr = param.substring(0, param.indexOf("filez#"));
            mDstStr = dstTempStr.replaceAll("#", "");

        } else {
            // 名称命名中可能出现 空  意思为 去某个特定字符串名字去掉
            if(param.indexOf("#") > 0 && param.indexOf("filez#")>0 && param.indexOf("numz#") > 0 && "".equals(mDstStr.trim())  ){
                mDstStr = "";
            }else{
                System.out.println("当前输出的字符串参数非法参数格式3: ");
                System.out.println("标准格式如下3: \n" + "filez#\"txt_md\"numz#\"yyyy\"");

            }

        }
        mDstStr = mDstStr.trim();

        return mDstStr;

    }



    static String getnumStrFromParam(String param) {
        String mnumStr = "";
        if (param.indexOf("filez#") < param.indexOf("numz#") && param.indexOf("numz#") < param.indexOf("#")) {   // numz 在 前面
            // filez#"txt_md"numz#"yyyy"#"xxxx"
            String srcTempStr = param.substring(0, param.indexOf("#"));
            String numz = srcTempStr.substring(param.indexOf("numz#"));
            mnumStr = numz.replaceAll("numz#", "");

        } else if (param.indexOf("filez#") > param.indexOf("#") && param.indexOf("#") > param.indexOf("numz#")) {
            // numz#"yyyy"#"xxxx"filez#"txt_md"
//            String filez = param.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

            String srcTempStr = param.substring(0, param.indexOf("#"));
            mnumStr = srcTempStr.replaceAll("numz#", "");


        } else if (param.indexOf("numz#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("#")) {
            // numz#"yyyy"filez#"txt_md"#"xxxx"
//            String fileTempStr = param.substring(0, param.indexOf("#"));   // numz#"yyyy"filez#"txt_md"
//            String filez = fileTempStr.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");


            String srcTempStr = param.substring(0, param.indexOf("filez#"));
            mnumStr = srcTempStr.replaceAll("numz#", "");

        } else if (param.indexOf("filez#") < param.indexOf("#") && param.indexOf("#") < param.indexOf("numz#")) {
            // filez#"txt_md"#"xxxx"numz#"yyyy"
//            String filez = param.substring(0, param.indexOf("#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

            String srcTempStr = param.substring(param.indexOf("numz#"));
            mnumStr = srcTempStr.replaceAll("numz#", "");


        } else if (param.indexOf("filez#") > param.indexOf("numz#") && param.indexOf("numz#") > param.indexOf("#")) {
            // #"xxxx"numz#"yyyy"filez#"txt_md"
//            String filez = param.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");


            String srcTempStr = param.substring(0, param.indexOf("filez#"));
            String numz = srcTempStr.substring(param.indexOf("numz#"));
            mnumStr = numz.replaceAll("numz#", "");

        } else if (param.indexOf("#") < param.indexOf("filez#") && param.indexOf("filez#") < param.indexOf("numz#")) {
            // #"xxxx"filez#"txt_md"numz#"yyyy"
//            String fileTempStr = param.substring(0, param.indexOf("numz#"));   // numz#"yyyy"filez#"txt_md"
//            String filez = fileTempStr.substring(param.indexOf("filez#"));
//            fileTypeStr = filez.replaceAll("filez#", "");

            String srcTempStr = param.substring(param.indexOf("numz#"));
            mnumStr = srcTempStr.replaceAll("numz#", "");


        } else {
            System.out.println("当前输出的字符串参数非法参数格式2: ");
            System.out.println("标准格式如下2: \n" + "filez#\"txt_md\"numz#\"yyyy\"#\"");
        }
        mnumStr = mnumStr.trim();

        return mnumStr;

    }



    static String getFileTypeFromParam(String param) {
        String fileTypeStr = "";
        if (param.indexOf("filez#") < param.indexOf("numz#") ) {   // numz 在 前面
            // filez#"txt_md"numz#"yyyy"#"xxxx"
            String filez = param.substring(0, param.indexOf("numz#"));
            fileTypeStr = filez.replaceAll("filez#", "");
        } else if (param.indexOf("filez#") > param.indexOf("numz#")) {
            // numz#"yyyy"#"xxxx"filez#"txt_md"
            String filez = param.substring(param.indexOf("filez#"));
            fileTypeStr = filez.replaceAll("filez#", "");
        }  else {
            System.out.println("当前输出的字符串参数非法参数格式: ");
            System.out.println("标准格式如下: \n" + "filez#\"txt_md\"numz#\"yyyy\"#\"");
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

        }else if("*".equals(fileTypeStr)){  // 所有的格式都加入
            fileTypeStrList.add(fileTypeStr);

        }

        return fileTypeStr;


    }


    // filez#"txt_md"numz#"yyyy"#"xxxx"
    //  对 当前目录下的 以及 所有子目录下的 txt  md 文件中的 字符串 yyyy   替换为  xxxx

    static String staticnumStr = "";
    static int  staticNumInt = 0;
   // static String staticDstStr = "";

    static ArrayList<File> typeFileList = new  ArrayList<File>();


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
                    if("*".equals(mFileTypeList.get(i))){
                        allDstFileSet.add(itemFile);
                        continue;
                    }
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
                //    System.out.println("Z2");
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


    public  static String getFileNameNoPointOrigin(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(0,fileName.lastIndexOf(".") ).trim();
        }else{
            name = new String(fileName);
        }
        return name.trim();
    }


    public  static String getFileNameNoPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(0,fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = new String(fileName);
        }
        return name.toLowerCase().trim();
    }

    public  static String getFileTypeWithPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(fileName.lastIndexOf(".") ).trim().toLowerCase();
        }else{
            name = "";
        }
        return name.toLowerCase().trim();
    }


    public  static String getFileTypeNoPoint(String fileName){
        String name = "";
        if(fileName.contains(".")){
            name = fileName.substring(fileName.lastIndexOf(".") + 1 ).trim().toLowerCase();
        }else{
            name = "";
        }
        return name.toLowerCase().trim();
    }


    static void RenameFileOperation(File curFile){
        String curFileName = getFileNameNoPointOrigin(curFile.getName());
        String newName = new String(curFileName);
        String filetypeWithPoint = getFileTypeWithPoint(curFile.getName());
        if("".equals(curFileName)){
            System.out.println("当前文件名为空");
            return;
        }

        String srcNameOri = new String(staticnumStr.trim());
        String dstNameOri = new String("AAA".trim());

        ArrayList<String> tempGroupStrList =  calculSortedGroup(srcNameOri);

        if(tempGroupStrList != null && tempGroupStrList.size() > 0){

            for (int i = 0; i < tempGroupStrList.size(); i++) {
                String curTemplateStr = tempGroupStrList.get(i);

                newName = newName.replaceAll(curTemplateStr.trim(),dstNameOri).trim();

            }


        }


        if( newName != null && !"".equals(newName) && !newName.equals(curFileName) ){  // 完成替换

            tryReName(curFile,newName+getFileTypeWithPoint(curFile.getName()));
        }else{
            //  System.out.println("当前的文件名为空!");
            return;
        }


    }


    public static ArrayList<String> calculSortedGroup(String value){
        ArrayList<String> resut = calculGroup(value);
        resut.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1); // 小写在前
            }
        });
        return resut;
    }

    public static  ArrayList<String> calculGroup(String value){
        ArrayList<String> tempArr = new ArrayList<String>();
        if(value.length() == 1){
            String uperStr = value.toUpperCase();
            String lowerStr =  value.toLowerCase();
            tempArr.add(uperStr);
            tempArr.add(lowerStr);
        }else{

            String otherStr = value.substring(1);
            String firstStr = value.substring(0,1);
            String lowerStr = firstStr.toLowerCase();
            String upStringStr = firstStr.toUpperCase();
            ArrayList<String> NextArr = calculGroup(otherStr);
            for (int i = 0; i < NextArr.size(); i++) {
                tempArr.add(lowerStr+ NextArr.get(i) );
                tempArr.add(upStringStr+ NextArr.get(i) );
            }


        }

        return tempArr;
    }


//    如果被重命名的文件已存在，那么renameTo()不会成功
//    renameTo()成功后，原文件会被删除

    static void tryReName(File curFile , String newName){
        String newFilePath = curFile.getParent() + File.separator + newName;
        String oldName = curFile.getName();
        File newFile = new File(newFilePath);
        if(newFile.exists() && newFilePath.equals(curFile.getAbsolutePath()) ){

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
            System.out.println("当前目录已存在重命名后的文件  文件名称:"+ curFile.getName());
            return ;    // 已经存在的文件不处理 直接返回

        }

        boolean flag =   curFile.renameTo(newFile);
        if(flag){
            System.out.println(oldName+" 转为 "+ newFilePath +" 成功！");
        }else{
            System.out.println(oldName+" 转为 "+ newFilePath +" 失败！");
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
                    newOneLine =  oldOneLine.replaceAll(staticnumStr,"");

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