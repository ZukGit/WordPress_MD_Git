

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//
public class H6_ZColor {

    //  Alpha = 1 ;  //  透明度
    // ZColor    RGB  颜色  输入 所有 RGB颜色  255_255_255
// ZColor_H6  255_255_255-100x200   //输入一张颜色为 255_255_255的 大小为 100x200的照片
// ZColor_H6  255_255_255  //输入一张颜色为 255_255_255的 大小为默认的500x500的照片
// ZColor_H6 ALL    // 创建所有的颜色图片  大小为500x500  命名方式为 255_255_255.jpg RGB


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 H6 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zcolor_H6";


/*******************修改属性列表 ------End *********************/


    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File H6_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream H6_Properties_InputStream;
    static OutputStream H6_Properties_OutputStream;
    static Properties H6_Properties = new Properties();
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


    static int CUR_TYPE_INDEX = 2;   // 当前用户选中的 操作的类型  默认1-标识默认是2  坑爹!
    static Rule CUR_Selected_Rule;    // 当前默认选中的 操作规则 这里实现了具体的操作逻辑

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_3_ParamStrList = new ArrayList<>();


    // 固定7  当前保存 Rule的集合
    static ArrayList<Rule> CUR_RULE_LIST = new ArrayList<Rule>();  // 规则的集合


    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new HashMap<String, ArrayList<File>>();
    ;


//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";


    // PATH 环境变量值进行当前的保存处理
    static String EnvironmentValue = System.getProperties().getProperty("java.library.path");

    static int alpha = 255;   /* 透明度  0---255  255-默认的没有透明度 */


    public static void generalPicture(File currentImageFile, int r, int g, int b) {
        generalPicture(currentImageFile, r, g, b, 400, 400);
    }

    public static void generalPicture(File currentImageFile, int r, int g, int b, int p_width, int p_heigh) {

        File mCurFile = currentImageFile;
        int width = p_width;
        int heigh = p_heigh;
        Color currentColor = new Color(r, g, b);
/*

        BufferedImage bi = new BufferedImage(width,heigh, BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150
//得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        int frontSize = 550;

        g2.setBackground(currentColor);
        g2.fillRect(0,0,width,heigh);//填充一个矩形 左上角坐标(0,0),宽500,高500;填充整张图片
        g2.fillRect(0,0,width,heigh);//填充整张图片(其实就是设置背景颜色)
         g2.setColor(currentColor);

         */

        BufferedImage img = new BufferedImage(width, heigh, BufferedImage.TYPE_INT_RGB);
        Graphics curGraphic = img.getGraphics();
        //设置颜色
        curGraphic.setColor(currentColor);
        //填充
        curGraphic.fillRect(0, 0, img.getWidth(), img.getHeight());


        try {
            mCurFile.createNewFile();
            ImageIO.write(img, "jpg", new FileOutputStream(mCurFile));//保存图片 JPEG表示保存格式
//            System.out.println("创建 RGB "+"R="+r+"  G="+g+"  B="+b+" 图片成功！");

        } catch (Exception e) {
            System.out.println("创建 RGB " + "R=" + r + "  G=" + g + "  B=" + b + " 图片格式出现异常！");
        }

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
            if (!H6_Properties_File.exists()) {
                H6_Properties_File.createNewFile();
            }
            H6_Properties_InputStream = new BufferedInputStream(new FileInputStream(H6_Properties_File.getAbsolutePath()));
            H6_Properties.load(H6_Properties_InputStream);
            Iterator<String> it = H6_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // System.out.println("key:" + key + " value: " + H6_Properties.getProperty(key));
                propKey2ValueList.put(key, H6_Properties.getProperty(key));
            }
            H6_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            H6_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(H6_Properties_File.getAbsolutePath()));
            H6_Properties.store(H6_Properties_OutputStream, "");
            H6_Properties_OutputStream.close();
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


    void InitRule() {

        //   加入类型一一对应的 那些 规则

        CUR_RULE_LIST.add(new CreatAllImage_Rule1());
        CUR_RULE_LIST.add(new CreatColorImage_Rule2());
        CUR_RULE_LIST.add(new CreateColorImage_High_Width_Rule3());


//        CUR_RULE_LIST.add( new File_Name_Rule_2());
//        CUR_RULE_LIST.add( new Image2Jpeg_Rule_3());
//        CUR_RULE_LIST.add( new Image2Png_Rule_4());
//        CUR_RULE_LIST.add( new AVI_Rule_5());
//        CUR_RULE_LIST.add( new SubDirRename_Rule_6());
//        CUR_RULE_LIST.add( new Encropty_Rule_7());
//        CUR_RULE_LIST.add( new ClearChineseType_8());

    }


    // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
    //     // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) 5. 从shell 中获取到的路径 去对某一个文件进行操作

    //    zcolor_H6.bat *_2 255_255_255  FF_FF_FF  99_99_99  0_0_0
    class CreateColorImage_High_Width_Rule3 extends Basic_Rule {
        ArrayList<File> curInputFileList;
        ArrayList<Color_Width_High> colorList;

        CreateColorImage_High_Width_Rule3() {
            super("#", 3, 4);

            curInputFileList = new ArrayList<File>();
            colorList = new ArrayList<Color_Width_High>();
        }

        @Override
        String simpleDesc() {
            return "\n" + Cur_Bat_Name + " #_3  255_0_0_1080_2340  FF_00_FF_1080_1960            ### 创建 R_G_B_W_H 对应的颜色的文件 可接受10进制 以及 16进制  W-标识宽  H-标识高 尺寸\n"
     /*               "\n"+Cur_Bat_Name+ " *_14  .mp4         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .gif         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的gif文件 并在当前目录生成 GIF_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  png          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的png文件 并在当前目录生成 PNG_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  zip  7z      ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .zip .7z     ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  jpg          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
                 */;
        }

        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return super.ruleTip(type, index, batName, curType);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            for (int i = 0; i < inputParamsList.size(); i++) {
                String iputItemStr = inputParamsList.get(i);
                Color color = new Color(0, 0, 0);
                String[] rgb_arr = iputItemStr.split("_");
                if (rgb_arr == null || rgb_arr.length == 0) {
                    continue;
                }

                String strTemp1 = iputItemStr.replace("_", "");
                boolean isTenStep = isNumeric(strTemp1);

                ArrayList<Integer> rgb_list = calculInputColor_width_high(rgb_arr, isTenStep);

                int red_value_int = rgb_list.get(0);
                int green_value_int = rgb_list.get(1);
                int blue_value_int = rgb_list.get(2);
   
                int width_value_int   =  Integer.parseInt(rgb_arr[rgb_arr.length-2]);
                int high_value_int = Integer.parseInt(rgb_arr[rgb_arr.length-1]);
                System.out.println("red_value_int = "+ red_value_int);
                System.out.println("green_value_int = "+ green_value_int);
                System.out.println("blue_value_int = "+ blue_value_int);
                System.out.println("width_value_int = "+ width_value_int);
                System.out.println("high_value_int = "+ high_value_int);

                color = new Color(red_value_int, green_value_int, blue_value_int);

                colorList.add(new Color_Width_High(color, width_value_int, high_value_int));
            }

//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
//            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

            String jpgDirPath = CUR_Dir_FILE.getAbsoluteFile().getAbsolutePath();

            File jpgDir = new File(jpgDirPath);
            String jpsParentPath = jpgDir.getAbsolutePath();
            for (int i = 0; i < colorList.size(); i++) {
                Color_Width_High Color_Width_High_Item = colorList.get(i);
                Color colorItem = Color_Width_High_Item.color;
                int r = colorItem.getRed();
                int g = colorItem.getGreen();
                int b = colorItem.getBlue();
                String r_hex = Integer.toHexString(r);
                String g_hex = Integer.toHexString(g);
                String b_hex = Integer.toHexString(b);
                int width = Color_Width_High_Item.width;
                int high = Color_Width_High_Item.high;
                String jpgName = r + "_" + g + "_" + b + "_" + width + "_" + high + ".jpg";
                File curRGBFile = new File(jpsParentPath + File.separator + jpgName);
                generalPicture(curRGBFile, r, g, b, width, high);
                System.out.println("════════════════════════");
                System.out.println("创建第" + (i + 1) + "个文件" + "剩余文件个数:" + (colorList.size() - i + 1) + "  R=" + r + "  G=" + g + "  B=" + b + " W=" + width + " H=" + high + "  名称: " + jpgName + " 图片成功! ");
                int r_1 = r - 256 / 2;
                int g_1 = g - 256 / 2;
                int b_1 = b - 256 / 2;

                System.out.println("R_G_B_W_H = " + r + "_" + g + "_" + b + "_" + width + "_" + high + " (0≡256) = " + r_1 + "_" + g_1 + "_" + b_1 + "_" + width + "_" + high + " (-128≡127)" + " = " + r_hex + "_" + g_hex + "_" + b_hex + "_" + width + "_" + high + "(00≡FF)");
                if (i == 0) {
                    openImageFile(curRGBFile);
                }
            }
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }

        class Color_Width_High {

            Color_Width_High(Color xColot, int xwidth, int xHeigh) {
                color = xColot;
                width = xwidth;
                high = xHeigh;
            }

            Color color;
            int width;
            int high;
        }

    }


    //    zcolor_H6.bat *_2 255_255_255  FF_FF_FF  99_99_99  0_0_0
    class CreatColorImage_Rule2 extends Basic_Rule {
        ArrayList<File> curInputFileList;
        ArrayList<Color> colorList;

        CreatColorImage_Rule2() {
            super("#", 2, 4);

            curInputFileList = new ArrayList<File>();
            colorList = new ArrayList<Color>();
        }

        @Override
        String simpleDesc() {
            return "\n" + Cur_Bat_Name + " #_2  255_255_255  FF_FF_FF  99_99_99  0_0_0          ### 创建 R_G_B 对应的颜色的文件 可接受10进制 以及 16进制  默认 1020*1960 尺寸\n"
     /*               "\n"+Cur_Bat_Name+ " *_14  .mp4         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .gif         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的gif文件 并在当前目录生成 GIF_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  png          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的png文件 并在当前目录生成 PNG_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  zip  7z      ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .zip .7z     ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  jpg          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
                 */;
        }

        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return super.ruleTip(type, index, batName, curType);
        }


        @Override
        void operationRule(ArrayList<String> inputParamsList) {


            for (int i = 0; i < inputParamsList.size(); i++) {
                String iputItemStr = inputParamsList.get(i);
                Color color = new Color(0, 0, 0);
                String[] rgb_arr = iputItemStr.split("_");
                if (rgb_arr == null || rgb_arr.length == 0) {
                    continue;
                }
                String strTemp1 = iputItemStr.replace("_", "");
                boolean isTenStep = isNumeric(strTemp1);

                ArrayList<Integer> rgb_list = calculInputColor(rgb_arr, isTenStep);

                int red_value_int = rgb_list.get(0);
                int green_value_int = rgb_list.get(1);
                int blue_value_int = rgb_list.get(2);
                color = new Color(red_value_int, green_value_int, blue_value_int);
                colorList.add(color);
            }

//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
//            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

            String jpgDirPath = CUR_Dir_FILE.getAbsoluteFile().getAbsolutePath();

            File jpgDir = new File(jpgDirPath);
            String jpsParentPath = jpgDir.getAbsolutePath();
            for (int i = 0; i < colorList.size(); i++) {
                Color colorItem = colorList.get(i);
                int r = colorItem.getRed();
                int g = colorItem.getGreen();
                int b = colorItem.getBlue();
                String r_hex = Integer.toHexString(r);
                String g_hex = Integer.toHexString(g);
                String b_hex = Integer.toHexString(b);

                String jpgName = r + "_" + g + "_" + b + ".jpg";
                File curRGBFile = new File(jpsParentPath + File.separator + jpgName);
                generalPicture(curRGBFile, r, g, b, 1020, 1960);
                System.out.println("════════════════════════");
                System.out.println("创建第" + (i + 1) + "个文件" + "剩余文件个数:" + (colorList.size() - i + 1) + "  R=" + r + "  G=" + g + "  B=" + b + "  名称: " + jpgName + " 图片成功! ");

                int r_1 = r - 256 / 2;
                int g_1 = g - 256 / 2;
                int b_1 = b - 256 / 2;

                System.out.println("R_G_B = " + r + "_" + g + "_" + b + " (0≡256)" + " = " + r_1 + "_" + g_1 + "_" + b_1 + " (-128≡127)" + " = " + r_hex + "_" + g_hex + "_" + b_hex + " (00≡FF)");
                if (i == 0) {
                    openImageFile(curRGBFile);
                }

            }
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }

    }


    static ArrayList<Integer> calculInputColor_width_high(String[] inputStrArr, boolean tenStep) {
        ArrayList<Integer> rgb_list = new ArrayList<Integer>();

        for (int i = 0; i < inputStrArr.length; i++) {
            int color_value_int = 0;
            String item = inputStrArr[i];
            String color_value = item.trim().toUpperCase();


            if (isNumeric(color_value) && tenStep) {   //  10 进制
                color_value_int = Integer.parseInt(color_value);

            } else if (isNumeric(color_value) && !tenStep) {  // 16进制
                BigInteger bigint = new BigInteger(color_value, 16);
                int numb = bigint.intValue();
                color_value_int = numb;
            } else if (isHexNumberRex(color_value)) {
                BigInteger bigint = new BigInteger(color_value, 16);
                int numb = bigint.intValue();
                color_value_int = numb;
            } else {
                color_value_int = 0;
            }
            rgb_list.add(color_value_int);
        }

        while (rgb_list.size() < 3) {
            rgb_list.add(0);
        }
        while (rgb_list.size() < 5) {
            rgb_list.add(1020);  // width
            rgb_list.add(1960);
        }


        while (rgb_list.size() > 5) {
            rgb_list.remove(rgb_list.size() - 1);
        }
        return rgb_list;
    }


    static ArrayList<Integer> calculInputColor(String[] inputStrArr, boolean tenStep) {
        ArrayList<Integer> rgb_list = new ArrayList<Integer>();

        for (int i = 0; i < inputStrArr.length; i++) {
            int color_value_int = 0;
            String item = inputStrArr[i];
            String color_value = item.trim().toUpperCase();


            if (isNumeric(color_value) && tenStep) {   //  10 进制
                color_value_int = Integer.parseInt(color_value);

            } else if (isNumeric(color_value) && !tenStep) {  // 16 进制
                BigInteger bigint = new BigInteger(color_value, 16);
                int numb = bigint.intValue();
                color_value_int = numb;

            } else if (isHexNumberRex(color_value)) {
                BigInteger bigint = new BigInteger(color_value, 16);
                int numb = bigint.intValue();
                color_value_int = numb;
            } else {
                color_value_int = 0;
            }
            rgb_list.add(color_value_int);
        }

        while (rgb_list.size() < 3) {
            rgb_list.add(0);
        }
        while (rgb_list.size() > 3) {
            rgb_list.remove(rgb_list.size() - 1);
        }
        return rgb_list;
    }

    class CreatAllImage_Rule1 extends Basic_Rule {
        ArrayList<File> curInputFileList;
        boolean isCreateAll = false;


        CreatAllImage_Rule1() {
            super("*", 1, 4);
            isCreateAll = false;
            curInputFileList = new ArrayList<File>();
        }

        @Override
        String simpleDesc() {
            return "\n" + Cur_Bat_Name + " *_1  ALL          ### 创建 RGB 256*256*256=16777216个文件到 当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
     /*               "\n"+Cur_Bat_Name+ " *_14  .mp4         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的mp4文件 并在当前目录生成 MP4_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .gif         ### 源文件被按顺序重命名 1_ 2_动态计算当前文件夹中所有子文件中的gif文件 并在当前目录生成 GIF_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  png          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的png文件 并在当前目录生成 PNG_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  zip  7z      ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  .zip .7z     ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的 文件夹中的 7z zip文件   并在当前目录生成 7Z_20200522_154600  ZIP_20200522_154600 字样的文件夹 \n"+
                    "\n"+Cur_Bat_Name+ " *_14  jpg          ### 源文件被按顺序重命名 1_ 2_ 动态计算当前文件夹中所有子文件中的JPG文件 并在当前目录生成 JPG_20200522_154600 字样的文件夹 \n"
                 */;
        }

        @Override
        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            return super.ruleTip(type, index, batName, curType);
        }

        @Override
        void operationRule(ArrayList<String> inputParamsList) {
//            System.out.println("operationRule 1 ");
            if (!isCreateAll) {
                return;
            }

//            System.out.println("operationRule 2 ");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

            String jpgDirPath = CUR_Dir_FILE.getAbsoluteFile() + File.separator + "JPG_" + date;

            File jpgDir = new File(jpgDirPath);
            jpgDir.mkdirs();
            String jpsParentPath = jpgDir.getAbsolutePath();
            int fileIndex = 0;
            long allCount = 256 * 256 * 256;  // 100_77_234
            for (int r = 101; r < 256; r++) {   //101_0_0

                for (int g = 0; g < 256; g++) {  //

                    for (int b = 0; b < 256; b++) {  //
                        fileIndex++;
                        String jpgName = r + "_" + g + "_" + b + ".jpg";
                        File curRGBFile = new File(jpsParentPath + File.separator + jpgName);
                        generalPicture(curRGBFile, r, g, b);
                        System.out.println("创建第" + fileIndex + "个文件" + "剩余文件个数:" + (allCount - fileIndex) + "  R=" + r + "  G=" + g + "  B=" + b + "  名称: " + jpgName + " 图片成功! ");
                    }
                }
            }

            return;
        }

        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            if (type2Param.toLowerCase().contains("ALL".toLowerCase())) {
                isCreateAll = true;
                return isCreateAll;
            }

            for (int i = 0; i < otherParams.size(); i++) {
                String strItem = otherParams.get(i);

                if (strItem.toLowerCase().contains("ALL".toLowerCase())) {
                    isCreateAll = true;
                    return isCreateAll;
                }

            }

            return super.checkParamsOK(shellDir, type2Param, otherParams);
        }

    }


    class Basic_Rule extends Rule {
        Basic_Rule(String ctype, int cindex, int opera_type) {
            this.file_type = ctype;
            this.rule_index = cindex;
            this.operation_type = opera_type;
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
        }

        Basic_Rule(int ruleIndex) {
            this.rule_index = ruleIndex;
            this.file_type = "";
            this.operation_type = 0;
            this.identify = this.file_type + "" + this.rule_index;
            curFilterFileTypeList = new ArrayList<String>();
            curFixedFileList = new ArrayList<File>();
            errorMsg = "";
        }

        String applyStringOperationRule1(String origin) {
            return origin;
        }

        File applyFileByteOperationRule2(File originFile) {
            return originFile;
        }

        ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap) {
            return subFileList;
        }

        @Override
        ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList) {
            return curFileList;
        }


        void initParams4InputParam(String inputParam) {
        }

        String simpleDesc() {
            return null;
        }

        void operationRule(ArrayList<String> inputParamsList) {
        }


        @Override
        boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams) {
            return true;  // 默认返回通过   不检查参数   如果有检查的需求 那么就实现它
        }

        @Override
        void showWrongMessage() {
            System.out.println("当前 type 索引 " + rule_index + " 执行错误  可能是输入参数错误 请检查输入参数!");
            System.out.println("ErrorMsg:" + errorMsg);
        }

        String ruleTip(String type, int index, String batName, OS_TYPE curType) {
            String itemDesc = "";
            if (curType == OS_TYPE.Windows) {
                itemDesc = batName.trim() + "  " + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            } else {
                itemDesc = batName.trim() + "" + type + "_" + index + "    [索引 " + index + "]  描述:" + simpleDesc();
            }

            return itemDesc;
        }

        boolean tryReName(File curFile, String newName) {
            String newFilePath = curFile.getParent() + File.separator + newName;
            String oldName = curFile.getName();
            File newFile = new File(newFilePath);
            if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
                System.out.println("当前目录已存在重命名后的文件  文件名称:" + curFile.getName());
                return false;    // 已经存在的文件不处理 直接返回

            }
            boolean flag = curFile.renameTo(newFile);
            if (flag) {
                System.out.println(oldName + " 转为 " + newFilePath + " 成功！");
                curFixedFileList.add(curFile);
            } else {
                System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
            }
            return flag;
        }
    }

    abstract class Rule {

        int rule_index;   //  rule_index  组成了最基础的唯一键 rule_index 就是唯一的序号  1 2 3 4 5 6 7

        // operation_type  操作类型     1--读取文件内容字符串 进行修改      2--对文件对文件内容(字节)--进行修改    3.对全体子文件进行的随性的操作 属性进行修改(文件名称)
        // 4.对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件)   // 5. 从shell 中获取到的路径 去对某一个文件进行操作

        int operation_type;  // 默认为0
        String file_type;   // * 标识所有的文件类型   以及当前操作类型文件  或者 单独的文件过滤类型
        String identify;
        String errorMsg;
        ArrayList<String> curFilterFileTypeList;  //  当前的文件过滤类型   多种文件过滤类型  例如把 多种格式 jpeg png 转为 jpg 时 使用到
        ArrayList<File> curFixedFileList;  // 当前修改操作成功的集合
        ArrayList<File> inputFileList;  // 从输入参数得到的文件的集合

        abstract void operationRule(ArrayList<String> inputParamsList);

        abstract String applyStringOperationRule1(String origin);

        abstract File applyFileByteOperationRule2(File originFile);

        abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList, HashMap<String, ArrayList<File>> fileTypeMap);

        abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList, HashMap<String, ArrayList<File>> subFileTypeMap, ArrayList<File> curDirList, ArrayList<File> curRealFileList);

        abstract void initParams4InputParam(String inputParam);  // 初始化Rule的参数 依据输入的字符串

        abstract String ruleTip(String type, int index, String batName, OS_TYPE curType);  // 使用说明列表  如果覆盖 那么就不使用默认的说明 , 默认就一种情况

        abstract String simpleDesc();  // 使用的简单描述  中文的该 rule的使用情况  默认会在 ruleTip 被调用

        abstract boolean checkParamsOK(File shellDir, String type2Param, ArrayList<String> otherParams);

        abstract void showWrongMessage();
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


    public static boolean isHexNumberRex(String inputstr) {
        String regex = "^[A-Fa-f0-9]+$";

        if (inputstr.matches(regex)) {

            return true;
        } else {

            return false;
        }

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

        System.out.println("当前用户输入的 操作type无法检测到-它是一个int值  \n type 必须是当前 " + Cur_Bat_Name + " 的 第一个输入参数中的第一个int值 ");
        System.out.println("请检查输入参数后重新执行命令!");

    }

    static void showTip() {
        System.out.println("对Type文件内容 进行 Index 规则的处理  identy=【 Type_Index 】【 文件后缀_当前操作逻辑索引】\n");
        System.out.println("当前已实现的替换逻辑如下:\n");

        int count = 1;
        System.out.println("═══════════════════" + "使用方法列表 Begin" + "═══════════════════" + "\n");
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            Rule itemRule = CUR_RULE_LIST.get(i);
            String type = itemRule.file_type;
            int index = itemRule.rule_index;
            String desc = itemRule.ruleTip(type, index, Cur_Bat_Name, CUR_OS_TYPE);

/*
            String itemDesc = "";
           if(CUR_OS_TYPE == OS_TYPE.Windows){
                itemDesc = "zrule_apply_H6.bat  "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }else{
               itemDesc = "zrule_apply_H6 "+type+"_"+index + "    [索引 "+count+"]  描述:"+desc;
           }
           */
            System.out.println(desc + "\n");
            count++;
        }
        System.out.println("═══════════════════" + "使用方法列表 End " + "═══════════════════" + "\n");

    }


    static int calculInputTypeIndex(String inputParams) {
        if (inputParams == null) {
            return 0;
        }
        inputParams =  inputParams.replace("_","");
        inputParams =  inputParams.replace("#","");
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

    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else if (i == 1) {  // 第二个参数是用来 对 当前功能进行分类使用的
                    CUR_TYPE_2_ParamsStr = args[i];

                    if (!CUR_TYPE_2_ParamsStr.contains("*") && !CUR_TYPE_2_ParamsStr.contains("#")) {
                        // CUR_TYPE_2_ParamsStr = #_3
                        System.out.println("CUR_TYPE_2_ParamsStr = "+ CUR_TYPE_2_ParamsStr);
                        CUR_INPUT_3_ParamStrList.add(args[i]);  //  如果第一个参数不包含 * # 那么可能是一个餐胡 默认参数
                        continue;
                    }

                    //zukgit1    计算得到 当前 索引的列表   首先遇到的第一个数字类型  1_2112  那就是索引1  附带参数 2112   temp_2_
                    CUR_TYPE_INDEX = calculInputTypeIndex(CUR_TYPE_2_ParamsStr);
                    System.out.println("CUR_TYPE_INDEX = "+ CUR_TYPE_INDEX);
                } else {
                    CUR_INPUT_3_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_3_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);

        H6_ZColor mH6_Object = new H6_ZColor();
        mH6_Object.InitRule();


        // 用户没有输入参数    默认情况
        if (CUR_TYPE_INDEX == 2 && CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }

        if (CUR_TYPE_INDEX == 0 && CUR_INPUT_3_ParamStrList.size() == 0) {
            showTip();
            return;
        }


        System.out.println("Over？ CUR_TYPE_INDEX = " + CUR_TYPE_INDEX);
        for (int i = 0; i < CUR_INPUT_3_ParamStrList.size(); i++) {
            System.out.println("XXX params[" + i + "] = " + CUR_INPUT_3_ParamStrList.get(i));
        }


        CUR_Selected_Rule = getRuleByIndex(CUR_TYPE_INDEX);  //  获取用户选中的 规则


        // 让各个规则自己去检测 自己需要的参数是否得到满足 并自己提示  给出 1.当前cmd路径下的文件  2.typeIndex 字符串   3.之后的输入参数
        if (CUR_Selected_Rule == null || !CUR_Selected_Rule.checkParamsOK(CUR_Dir_FILE, CUR_TYPE_2_ParamsStr, CUR_INPUT_3_ParamStrList)) {
            CUR_Selected_Rule.showWrongMessage();   // 提示当前规则的错误信息
            return;
        }



/*
        if(!checkInputParamsOK()){
            System.out.println("当前用户输入的格式错误   input=【类型_索引】  例如    html_1   html_2    html_3  ");
            return;
        }
*/


        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()) {
            System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
            return;
        }


        CUR_Selected_Rule.operationRule(CUR_INPUT_3_ParamStrList);  // 传递参数列表 进行处理


        setProperity();
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

    static String OriApplyOperationRule(String mType, String index, String mOriContent) {
        String identy = mType.trim() + index.trim();
        Rule applayRule = getRuleByIdentify(identy);
        if (applayRule == null) {
            System.out.println("没有查询到 identy =" + identy + "对应的处理规则");
            return mOriContent;
        }
        return applayRule.applyStringOperationRule1(mOriContent);
    }


    static Rule getRuleByIndex(int index) {
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            if (CUR_RULE_LIST.get(i).rule_index == index) {
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }


    static Rule getRuleByIdentify(String identify) {
        for (int i = 0; i < CUR_RULE_LIST.size(); i++) {
            if (CUR_RULE_LIST.get(i).identify.equals(identify)) {
                return CUR_RULE_LIST.get(i);
            }
        }
        return null;
    }

    public static void openImageFile(File imageFile) {
        if (CUR_OS_TYPE == OS_TYPE.Windows) {
            try {
                Runtime.getRuntime().exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + imageFile.getAbsolutePath());

            } catch (Exception e) {

            }
        }


    }
}
