import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.Key;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;

public class C0_JIEMA {

    private static String strDefaultKey = "zukgit12"; //    加密原始秘钥字符串

    private static String mingwenDir = File.separator + "mingwen"; //
    private static String miwenDir = File.separator + "miwen"; //
    /**
     * ECB 作为DES加密 操作模式的一种   明文与密文长度一致  但必须保证8字节长度整数倍的明文
     * CBC 作为DES加密 操作模式的一种   明文与密文长度不一致  明文长度不需要保证一定是 8字节整数倍 会多出一个 IV block padding
     */

    private static String curMIWEN_PATH =  System.getProperties().getProperty("user.dir") + File.separator + "mingwen" + File.separator;

    private static String curMINGWEN_PATH =  System.getProperties().getProperty("user.dir") + File.separator + "mingwen" + File.separator;



    static String curJPG_PATH =  System.getProperties().getProperty("user.dir") + File.separator + "mingwen" + File.separator + "i";
    static String curGIF_PATH =     System.getProperties().getProperty("user.dir") + File.separator + "mingwen" + File.separator + "g";
    static String curMP4_PATH =      System.getProperties().getProperty("user.dir") + File.separator + "mingwen" + File.separator + "v";
 // html-begin

    static String I_TAG = File.separator + "i"+File.separator ; //
    static String G_TAG = File.separator + "g"+File.separator ; //
    static String M_TAG = File.separator + "v"+File.separator ; //

    //================================ gif_begin===================
    //gif 1x1
    static File htmlFile_gif_1x1_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_1x1_flow_left.html");
    static File htmlFile_gif_1x1_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_1x1_flow_right.html");


    //gif 2x2
    static File htmlFile_gif_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2.html");
    static File htmlFile_gif_2x2_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_same.html");
    static File htmlFile_gif_2x2_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_same_colum.html");
    static File htmlFile_gif_2x2_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_same_delay.html");
    static File htmlFile_gif_2x2_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_same_row.html");
    static File htmlFile_gif_2x2_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_sequence.html");

    static File htmlFile_gif_2x2_flow_left  = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_flow_left.html");
    static File htmlFile_gif_2x2_flow_right  = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2_flow_right.html");




    //gif 3x3
    static File htmlFile_gif_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3.html");
    static File htmlFile_gif_3x3_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_same.html");
    static File htmlFile_gif_3x3_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_same_colum.html");
    static File htmlFile_gif_3x3_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_same_delay.html");
    static File htmlFile_gif_3x3_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_same_row.html");
    static File htmlFile_gif_3x3_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_sequence.html");

    static File htmlFile_gif_3x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_flow_left.html");
    static File htmlFile_gif_3x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3_flow_right.html");





    // gif 3x5
    static File htmlFile_gif_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5.html");
    static File htmlFile_gif_3x5_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5_same.html");
    static File htmlFile_gif_3x5_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5_same_colum.html");
    static File htmlFile_gif_3x5_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5_same_delay.html");
    static File htmlFile_gif_3x5_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5_same_row.html");
    static File htmlFile_gif_3x5_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5_sequence.html");



    //  gif  4x2
    static File htmlFile_gif_4x2_flow_left  = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x2_flow_left.html");
    static File htmlFile_gif_4x2_flow_right  = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x2_flow_right.html");


    //  gif  4x3
    static File htmlFile_gif_4x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x3_flow_left.html");
    static File htmlFile_gif_4x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x3_flow_right.html");


    //  gif  4x4
    static File htmlFile_gif_4x4_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x4_flow_left.html");
    static File htmlFile_gif_4x4_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_4x4_flow_right.html");



    //  gif  5x3
    static File htmlFile_gif_5x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_5x3_flow_left.html");
    static File htmlFile_gif_5x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_5x3_flow_right.html");

    //  gif  5x4
    static File htmlFile_gif_5x4_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_5x4_flow_left.html");
    static File htmlFile_gif_5x4_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_5x4_flow_right.html");


    // gif_3d
    static File htmlFile_gif__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif__3d.html");


    //================================ gif_end===================

    //================================ jpg_begin===================

    // jpg 1x1
    static File htmlFile_jpg_1x1_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_1x1_flow_left.html");
    static File htmlFile_jpg_1x1_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_1x1_flow_right.html");



    //jpg 2x2
    static File htmlFile_jpg_2x2_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_flow_left.html");
    static File htmlFile_jpg_2x2_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_flow_right.html");

    static File htmlFile_jpg_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2.html");
    static File htmlFile_jpg_2x2_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_same.html");
    static File htmlFile_jpg_2x2_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_same_colum.html");
    static File htmlFile_jpg_2x2_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_same_delay.html");
    static File htmlFile_jpg_2x2_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_same_row.html");
    static File htmlFile_jpg_2x2_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2_sequence.html");



    //jpg 3x3
    static File htmlFile_jpg_3x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_flow_left.html");
    static File htmlFile_jpg_3x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_flow_right.html");

    static File htmlFile_jpg_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3.html");
    static File htmlFile_jpg_3x3_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_same.html");
    static File htmlFile_jpg_3x3_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_same_colum.html");
    static File htmlFile_jpg_3x3_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_same_delay.html");
    static File htmlFile_jpg_3x3_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_same_row.html");
    static File htmlFile_jpg_3x3_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3_sequence.html");





    //jpg 3x5
    static File htmlFile_jpg_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5.html");
    static File htmlFile_jpg_3x5_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5_same.html");
    static File htmlFile_jpg_3x5_same_colum = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5_same_colum.html");
    static File htmlFile_jpg_3x5_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5_same_delay.html");
    static File htmlFile_jpg_3x5_same_row = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5_same_row.html");
    static File htmlFile_jpg_3x5_sequence = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5_sequence.html");


    //jpg 4x3
    static File htmlFile_jpg_4x2_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x2_flow_left.html");
    static File htmlFile_jpg_4x2_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x2_flow_right.html");



    //jpg 4x3
    static File htmlFile_jpg_4x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x3_flow_left.html");
    static File htmlFile_jpg_4x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x3_flow_right.html");


    //jpg 4x4
    static File htmlFile_jpg_4x4_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x4_flow_left.html");
    static File htmlFile_jpg_4x4_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_4x4_flow_right.html");


    //jpg 5x3
    static File htmlFile_jpg_5x3_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_5x3_flow_left.html");
    static File htmlFile_jpg_5x3_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_5x3_flow_right.html");

    //jpg 5x3
    static File htmlFile_jpg_5x4_flow_left = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_5x4_flow_left.html");
    static File htmlFile_jpg_5x4_flow_right = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_5x4_flow_right.html");





    //jpg 3xd
    static File htmlFile_jpg__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg__3d.html");

    //================================ jpg_end===================


    //================================ mp4_begin===================
    //mp4 2x2
    static File htmlFile_mp4_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_2x2.html");
    static File htmlFile_mp4_2x2_indivitual = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x5_indivitual.html");
    static File htmlFile_mp4_2x2_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_2x2_same.html");
    static File htmlFile_mp4_2x2_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_2x2_same_delay.html");


    //mp4 3x3
    static File htmlFile_mp4_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x3.html");
    static File htmlFile_mp4_3x3_indivitual = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x3_indivitual.html");
    static File htmlFile_mp4_3x3_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x3_same.html");
    static File htmlFile_mp4_3x3_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x3_same_delay.html");


    //mp4 3x5
    static File htmlFile_mp4_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x5.html");
    static File htmlFile_mp4_3x5_indivitual = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_2x2_indivitual.html");
    static File htmlFile_mp4_3x5_same = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x5_same.html");
    static File htmlFile_mp4_3x5_same_delay = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x5_same_delay.html");

    //mp4 3d
    static File htmlFile_mp4__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4__3d.html");
    static File htmlFile_mp4__3d_indivitual = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4__3d_indivitual.html");

    //================================ mp4_end===================

    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;
    static int BYTE_CONTENT_LENGTH = 1024 * 10 * 10;   // 读取文件Head字节数常数

    static int File_INDEX = 1;
    static int DIR_INDEX = 1;
    public static ArrayList<File> jpgFileList_miwen = new ArrayList<File>();
    public static ArrayList<File> mp4FileList_miwen = new ArrayList<File>();
    public static ArrayList<File> gifFileList_miwen = new ArrayList<File>();



    public static ArrayList<File> jpgFileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> gifFileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> wm_mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> ym_mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> am_mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> support_mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> up_mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> fakeface_mp4FileList_mingwen = new ArrayList<File>();


    public static ArrayList<File> allFile_miwen = new ArrayList<File>();
    public static ArrayList<File> alldir_miwen = new ArrayList<File>();
    public static ArrayList<File> allPointFile_miwen = new ArrayList<File>();


    public static ArrayList<File> alldir_mingwen = new ArrayList<File>();
    public static ArrayList<File> allPointFile_mingwen = new ArrayList<File>();


    public static Map<File, File> mingwen_miwen_Map = new HashMap<File, File>();


    //-----------------indivisual-------------------
    public static HashMap<String, ArrayList<File>> arrFileMap = new HashMap<String, ArrayList<File>>();
    public static ArrayList<File> mp4_0001bdyjy_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0002cjmyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0003wyzzz_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0004ymlxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0005zywxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0006gcyzx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0007julia_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0008dyfxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0009xqkjx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0010thyxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0011gyqyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0012xcnyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0013byyjx_FileList_mingwen = new ArrayList<File>();

    public static ArrayList<File> mp4_0014ymxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0015tlyxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0016mcyxx_FileList_mingwen = new ArrayList<File>();

    public static ArrayList<File> mp4_0017ssyyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0018lzllx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0019sdfxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0020tymyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0021lslmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0022ybsxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0023yzxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0024scanx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0025bxmhx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0026sgszx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0027cxmgx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0028mzlxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0029sxxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0030yyllx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0031zzmmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0032fbbxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0033zcmyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0034mnrcx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0035xxlmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0036xynmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0037zcbxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0038safhx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0039ajlyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0040aymlx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0041zzblx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0042qbycx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0043yblyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0044ccnxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0045qslcx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0046qlxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0047xcplx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0048yjlyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0049stklx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0050bsllx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0051fnlxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0052mlcxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0053sxccm_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0054xdnxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0055ccbxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0056mjzgx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0057tnmxy_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0058ytcyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0059sqxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0060bjlmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0061jlxsx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0062qxaml_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0063mrhql_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0064cxlxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0065yypnx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0066slmbx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0067qllnx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0068mmmyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0069xnnmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0070xxcxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0071acanx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0072wylsx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0073sthmx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0074ksxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0075mlyls_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0076hcsln_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0077byymx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0078eggxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0079ftyxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0080jcxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0081qzmyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0082rxbjn_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0083tghlx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0084xyyxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0085ycxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0086nmyxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0087cyxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0088stscx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0089cdzyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0090jgyla_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0091mhxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0092ryxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0093zyqxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0094aikax_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0095xcllh_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0096xnaxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0097yyzln_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0098yymyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0099wdxxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0100gqlxx_FileList_mingwen = new ArrayList<File>();

    public static ArrayList<File> mp4_0101lyfxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_8001suren_FileList_mingwen = new ArrayList<File>();

    static {
        arrFileMap.put("0001bdyjy", mp4_0001bdyjy_FileList_mingwen);
        arrFileMap.put("0002cjmyx", mp4_0002cjmyx_FileList_mingwen);
        arrFileMap.put("0003wyzzz", mp4_0003wyzzz_FileList_mingwen);
        arrFileMap.put("0004ymlxx", mp4_0004ymlxx_FileList_mingwen);
        arrFileMap.put("0005zywxx", mp4_0005zywxx_FileList_mingwen);
        arrFileMap.put("0006gcyzx", mp4_0006gcyzx_FileList_mingwen);
        arrFileMap.put("0007julia", mp4_0007julia_FileList_mingwen);
        arrFileMap.put("0008dyfxx", mp4_0008dyfxx_FileList_mingwen);
        arrFileMap.put("0009xqkjx", mp4_0009xqkjx_FileList_mingwen);
        arrFileMap.put("0010thyxx", mp4_0010thyxx_FileList_mingwen);
        arrFileMap.put("0011gyqyx", mp4_0011gyqyx_FileList_mingwen);
        arrFileMap.put("0012xcnyx", mp4_0012xcnyx_FileList_mingwen);
        arrFileMap.put("0013byyjx", mp4_0013byyjx_FileList_mingwen);
        arrFileMap.put("0014ymxxx", mp4_0014ymxxx_FileList_mingwen);
        arrFileMap.put("0015tlyxx", mp4_0015tlyxx_FileList_mingwen);
        arrFileMap.put("0016mcyxx", mp4_0016mcyxx_FileList_mingwen);
        arrFileMap.put("0017ssyyx", mp4_0017ssyyx_FileList_mingwen);
        arrFileMap.put("0018lzllx", mp4_0018lzllx_FileList_mingwen);
        arrFileMap.put("0019sdfxx", mp4_0019sdfxx_FileList_mingwen);
        arrFileMap.put("0020tymyx", mp4_0020tymyx_FileList_mingwen);
        arrFileMap.put("0021lslmx", mp4_0021lslmx_FileList_mingwen);
        arrFileMap.put("0022ybsxx", mp4_0022ybsxx_FileList_mingwen);
        arrFileMap.put("0023yzxxx", mp4_0023yzxxx_FileList_mingwen);
        arrFileMap.put("0024scanx", mp4_0024scanx_FileList_mingwen);
        arrFileMap.put("0025bxmhx", mp4_0025bxmhx_FileList_mingwen);
        arrFileMap.put("0026sgszx", mp4_0026sgszx_FileList_mingwen);
        arrFileMap.put("0027cxmgx", mp4_0027cxmgx_FileList_mingwen);
        arrFileMap.put("0028mzlxx", mp4_0028mzlxx_FileList_mingwen);
        arrFileMap.put("0029sxxxx", mp4_0029sxxxx_FileList_mingwen);
        arrFileMap.put("0030yyllx", mp4_0030yyllx_FileList_mingwen);
        arrFileMap.put("0031zzmmx", mp4_0031zzmmx_FileList_mingwen);
        arrFileMap.put("0032fbbxx", mp4_0032fbbxx_FileList_mingwen);
        arrFileMap.put("0033zcmyx", mp4_0033zcmyx_FileList_mingwen);
        arrFileMap.put("0034mnrcx", mp4_0034mnrcx_FileList_mingwen);
        arrFileMap.put("0035xxlmx", mp4_0035xxlmx_FileList_mingwen);
        arrFileMap.put("0036xynmx", mp4_0036xynmx_FileList_mingwen);
        arrFileMap.put("0037zcbxx", mp4_0037zcbxx_FileList_mingwen);
        arrFileMap.put("0038safhx", mp4_0038safhx_FileList_mingwen);
        arrFileMap.put("0039ajlyx", mp4_0039ajlyx_FileList_mingwen);
        arrFileMap.put("0040aymlx", mp4_0040aymlx_FileList_mingwen);
        arrFileMap.put("0041zzblx", mp4_0041zzblx_FileList_mingwen);
        arrFileMap.put("0042qbycx", mp4_0042qbycx_FileList_mingwen);
        arrFileMap.put("0043yblyx", mp4_0043yblyx_FileList_mingwen);
        arrFileMap.put("0044ccnxx", mp4_0044ccnxx_FileList_mingwen);
        arrFileMap.put("0045qslcx", mp4_0045qslcx_FileList_mingwen);
        arrFileMap.put("0046qlxxx", mp4_0046qlxxx_FileList_mingwen);
        arrFileMap.put("0047xcplx", mp4_0047xcplx_FileList_mingwen);
        arrFileMap.put("0048yjlyx", mp4_0048yjlyx_FileList_mingwen);
        arrFileMap.put("0049stklx", mp4_0049stklx_FileList_mingwen);
        arrFileMap.put("0050bsllx", mp4_0050bsllx_FileList_mingwen);
        arrFileMap.put("0051fnlxx", mp4_0051fnlxx_FileList_mingwen);
        arrFileMap.put("0052mlcxx", mp4_0052mlcxx_FileList_mingwen);
        arrFileMap.put("0053sxccm", mp4_0053sxccm_FileList_mingwen);
        arrFileMap.put("0054xdnxx", mp4_0054xdnxx_FileList_mingwen);
        arrFileMap.put("0055ccbxx", mp4_0055ccbxx_FileList_mingwen);
        arrFileMap.put("0056mjzgx", mp4_0056mjzgx_FileList_mingwen);
        arrFileMap.put("0057tnmxy", mp4_0057tnmxy_FileList_mingwen);
        arrFileMap.put("0058ytcyx", mp4_0058ytcyx_FileList_mingwen);
        arrFileMap.put("0059sqxxx", mp4_0059sqxxx_FileList_mingwen);
        arrFileMap.put("0060bjlmx", mp4_0060bjlmx_FileList_mingwen);
        arrFileMap.put("0061jlxsx", mp4_0061jlxsx_FileList_mingwen);
        arrFileMap.put("0062qxaml", mp4_0062qxaml_FileList_mingwen);
        arrFileMap.put("0063mrhql", mp4_0063mrhql_FileList_mingwen);
        arrFileMap.put("0064cxlxx", mp4_0064cxlxx_FileList_mingwen);
        arrFileMap.put("0065yypnx", mp4_0065yypnx_FileList_mingwen);
        arrFileMap.put("0066slmbx", mp4_0066slmbx_FileList_mingwen);
        arrFileMap.put("0067qllnx", mp4_0067qllnx_FileList_mingwen);
        arrFileMap.put("0068mmmyx", mp4_0068mmmyx_FileList_mingwen);
        arrFileMap.put("0069xnnmx", mp4_0069xnnmx_FileList_mingwen);
        arrFileMap.put("0070xxcxx", mp4_0070xxcxx_FileList_mingwen);
        arrFileMap.put("0071acanx", mp4_0071acanx_FileList_mingwen);
        arrFileMap.put("0072wylsx", mp4_0072wylsx_FileList_mingwen);
        arrFileMap.put("0073sthmx", mp4_0073sthmx_FileList_mingwen);
        arrFileMap.put("0074ksxxx", mp4_0074ksxxx_FileList_mingwen);
        arrFileMap.put("0075mlyls", mp4_0075mlyls_FileList_mingwen);
        arrFileMap.put("0076hcsln", mp4_0076hcsln_FileList_mingwen);
        arrFileMap.put("0077byymx", mp4_0077byymx_FileList_mingwen);
        arrFileMap.put("0078eggxx", mp4_0078eggxx_FileList_mingwen);
        arrFileMap.put("0079ftyxx", mp4_0079ftyxx_FileList_mingwen);
        arrFileMap.put("0080jcxxx", mp4_0080jcxxx_FileList_mingwen);
        arrFileMap.put("0081qzmyx", mp4_0081qzmyx_FileList_mingwen);
        arrFileMap.put("0082rxbjn", mp4_0082rxbjn_FileList_mingwen);
        arrFileMap.put("0083tghlx", mp4_0083tghlx_FileList_mingwen);
        arrFileMap.put("0084xyyxx", mp4_0084xyyxx_FileList_mingwen);
        arrFileMap.put("0085ycxxx", mp4_0085ycxxx_FileList_mingwen);
        arrFileMap.put("0086nmyxx", mp4_0086nmyxx_FileList_mingwen);
        arrFileMap.put("0087cyxxx", mp4_0087cyxxx_FileList_mingwen);
        arrFileMap.put("0088stscx", mp4_0088stscx_FileList_mingwen);
        arrFileMap.put("0089cdzyx", mp4_0089cdzyx_FileList_mingwen);
        arrFileMap.put("0090jgyla", mp4_0090jgyla_FileList_mingwen);
        arrFileMap.put("0091mhxxx", mp4_0091mhxxx_FileList_mingwen);
        arrFileMap.put("0092ryxxx", mp4_0092ryxxx_FileList_mingwen);
        arrFileMap.put("0093zyqxx", mp4_0093zyqxx_FileList_mingwen);
        arrFileMap.put("0094aikax", mp4_0094aikax_FileList_mingwen);
        arrFileMap.put("0095xcllh", mp4_0095xcllh_FileList_mingwen);
        arrFileMap.put("0096xnaxx", mp4_0096xnaxx_FileList_mingwen);
        arrFileMap.put("0097yyzln", mp4_0097yyzln_FileList_mingwen);
        arrFileMap.put("0098yymyx", mp4_0098yymyx_FileList_mingwen);
        arrFileMap.put("0099wdxxx", mp4_0099wdxxx_FileList_mingwen);
        arrFileMap.put("0100gqlxx", mp4_0100gqlxx_FileList_mingwen);
        arrFileMap.put("0101lyfxx", mp4_0101lyfxx_FileList_mingwen);
        arrFileMap.put("8001suren", mp4_8001suren_FileList_mingwen);


    }

    //-----------------indivisual-------------------

    // html-begin



    // 生成 Individual目录

    public static void todoIndivitualFile(ArrayList<File> mp4FileList_mingwen) {
        if (mp4FileList_mingwen == null) {
            return;
        }
        ArrayList<String> nameKeyStr = new ArrayList<String>();
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                nameKeyStr.add(entry.getKey());
            }
        }

        for (File mp4FileItem : mp4FileList_mingwen) {
            String fileName = mp4FileItem.getName();


            // 从 name  以及 ArrayList<String>  获取 key；
            String strKey = toGetKeyFromName(nameKeyStr, fileName);
            System.out.println("step4A: "+"strKey = "+ strKey + "   fileName = "+fileName);
            if (strKey == null || "".equals(strKey)) {
                // 没有找到对应的Key  所以对应的这个mp4FileItem 不是需要收集的
                continue;
            }

            ArrayList<File> arrFileValue = arrFileMap.get(strKey);
            arrFileValue.add(mp4FileItem);
        }
    }

    static String toGetKeyFromName(ArrayList<String> keyList, String fileName) {
        String keyStr = "";
        if (keyList == null) {
            return null;
        }

        for (int i = 0; i < keyList.size(); i++) {
            String keyItem = keyList.get(i);
            if (fileName.contains(keyItem)) {
                return keyItem;
            }
        }
        return keyStr;
    }

    public static void main(String[] args) {
        if (isFileReady()) {
//            createEncryFile();   // 创建加密的文件
//            createDecryFile();  // 创建 解析 解密的文件之后的 解密的文件
//            show1024Byte();
            collectAllI();
            collectAllG();
            collectAllV();
            collectAllFileV_SubCategory();
            collectIndivitual();
        }

    }

    public static void collectIndivitual() {

        ArrayList<String> nameKeyStr = new ArrayList<String>();
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                nameKeyStr.add(entry.getKey());
            }
        }

        for (int j = 0; j < nameKeyStr.size(); j++) {
            String keyName = nameKeyStr.get(j);
            // 开始创建文件夹

//  indivitual/00001bdyjy/mp4
//  indivitual/00002xxxxx/mp4
            String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "indivitual" + File.separator + keyName + File.separator + "mp4";
            //      String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "wm" + File.separator + "mp4";
            File allFileDir = new File(allPath);

            if (!allFileDir.exists()) {
                allFileDir.mkdirs();
            }

            ArrayList<File> itemFileArr = arrFileMap.get(keyName);
            if (itemFileArr == null) {
                continue;
            }

            for (int i = 0; i < itemFileArr.size(); i++) {
                File fileItem = (File) itemFileArr.get(i);
                String name = fileItem.getName();
                int nameIndex = i + 1;
                String nameIdnex = nameIndex + ".mp4";
                //  File allFileItem = new File(allPath +File.separator+name);
                File allFileItem = new File(allPath + File.separator + nameIdnex);
                fileCopy(fileItem, allFileItem);

            }

            addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "indivitual" + File.separator + keyName, itemFileArr.size());
        }

        todoIndivitualHtml();  //  操作 arrFileMap   map

    }


    public static void collectAllFileV_SubCategory() {
        collectAllV_WM();
        collectAllV_YM();
        collectAllV_UP();
        collectAllV_AM();
        collectAllV_SUPPORT();
        collectAllV_FAKEFACE();
    }


    public static void collectAllV_FAKEFACE() {
        // mingwen/v/all
        // mingwen/v/support/mp4
        //   String allPath = System.getProperties().getProperty("user.dir")+ File.separator +mingwenDir + File.separator + "v" + File.separator + "support_all";

        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "fakeface" + File.separator + "mp4";

        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < fakeface_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) fakeface_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "fakeface", fakeface_mp4FileList_mingwen.size());

    }


    public static void collectAllFile() {
        // mingwen/all
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "all";
        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < allPointFile_mingwen.size(); i++) {
            File fileItem = (File) allPointFile_mingwen.get(i);
            String name = fileItem.getName();
            File allFileItem = new File(allPath + File.separator + name);
            fileCopy(fileItem, allFileItem);

        }
    }

    public static void collectAllV() {
        // mingwen/v/all
        // mingwen/v/mp4
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "mp4";
        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v", mp4FileList_mingwen.size());

    }


    public static void collectAllV_WM() {
        // mingwen/v/all
        // mingwen/v/wm/mp4
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "wm" + File.separator + "mp4";
        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < wm_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) wm_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "wm", wm_mp4FileList_mingwen.size());

    }

    public static void collectAllV_YM() {
        // mingwen/v/all
        // mingwen/v/ym/mp4
        // String allPath = System.getProperties().getProperty("user.dir")+ File.separator +mingwenDir + File.separator + "v" + File.separator + "ym_all";

        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "ym" + File.separator + "mp4";

        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < ym_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) ym_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "ym", ym_mp4FileList_mingwen.size());

    }


    public static void collectAllV_UP() {
        // mingwen/v/all
        // mingwen/v/up/mp4
        //      String allPath = System.getProperties().getProperty("user.dir")+ File.separator +mingwenDir + File.separator + "v" + File.separator + "up_all";


        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "up" + File.separator + "mp4";

        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < up_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) up_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "up", up_mp4FileList_mingwen.size());

    }


    public static void collectAllV_SUPPORT() {
        // mingwen/v/all
        // mingwen/v/support/mp4
        //   String allPath = System.getProperties().getProperty("user.dir")+ File.separator +mingwenDir + File.separator + "v" + File.separator + "support_all";

        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "support" + File.separator + "mp4";

        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < support_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) support_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "support", support_mp4FileList_mingwen.size());

    }

    public static void collectAllV_AM() {
        // mingwen/v/all
        // mingwen/v/am/mp4
        //  String allPath = System.getProperties().getProperty("user.dir")+ File.separator +mingwenDir + File.separator + "v" + File.separator + "mp4";
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "am" + File.separator + "mp4";

        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < am_mp4FileList_mingwen.size(); i++) {
            File fileItem = (File) am_mp4FileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".mp4";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);
        }

        addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "am", am_mp4FileList_mingwen.size());

    }


    public static void collectAllI() {
        // mingwen/i/all
        //  mingwen/i/jpg
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "i" + File.separator + "jpg";
        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < jpgFileList_mingwen.size(); i++) {
            File fileItem = (File) jpgFileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".jpg";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }
        addJPGHtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "i", jpgFileList_mingwen.size());
    }

    public static void addJPGHtmlFile(String addPath, int length) {


        // zhtmlFile_jpg_1x1_flow_left
        String zhtmlFile_jpg_1x1_flow_left = readStringFromFile(htmlFile_jpg_1x1_flow_left);
        if (zhtmlFile_jpg_1x1_flow_left != null && zhtmlFile_jpg_1x1_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_1x1_flow_left = zhtmlFile_jpg_1x1_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_1x1_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_1x1_flow_left.getName());
        if (!zhtmlFile_jpg_1x1_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_1x1_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_1x1_flow_left_File, zhtmlFile_jpg_1x1_flow_left);



        // zhtmlFile_jpg_1x1_flow_right
        String zhtmlFile_jpg_1x1_flow_right = readStringFromFile(htmlFile_jpg_1x1_flow_right);
        if (zhtmlFile_jpg_1x1_flow_right != null && zhtmlFile_jpg_1x1_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_1x1_flow_right = zhtmlFile_jpg_1x1_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_1x1_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_1x1_flow_right.getName());
        if (!zhtmlFile_jpg_1x1_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_1x1_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_1x1_flow_right_File, zhtmlFile_jpg_1x1_flow_right);




        // zhtmlFile_jpg_2x2_flow_left
        String zhtmlFile_jpg_2x2_flow_left = readStringFromFile(htmlFile_jpg_2x2_flow_left);
        if (zhtmlFile_jpg_2x2_flow_left != null && zhtmlFile_jpg_2x2_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_2x2_flow_left = zhtmlFile_jpg_2x2_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_2x2_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_2x2_flow_left.getName());
        if (!zhtmlFile_jpg_2x2_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_2x2_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_2x2_flow_left_File, zhtmlFile_jpg_2x2_flow_left);



        // zhtmlFile_jpg_2x2_flow_right
        String zhtmlFile_jpg_2x2_flow_right = readStringFromFile(htmlFile_jpg_2x2_flow_right);
        if (zhtmlFile_jpg_2x2_flow_right != null && zhtmlFile_jpg_2x2_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_2x2_flow_right = zhtmlFile_jpg_2x2_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_2x2_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_2x2_flow_right.getName());
        if (!zhtmlFile_jpg_2x2_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_2x2_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_2x2_flow_right_File, zhtmlFile_jpg_2x2_flow_right);








        // jpg_2x2
        String htmlcode2x2 = readStringFromFile(htmlFile_jpg_2x2);
        if (htmlcode2x2 != null && htmlcode2x2.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2 = htmlcode2x2.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_File = new File(addPath + File.separator + htmlFile_jpg_2x2.getName());
        if (!htmlcode2x2_File.exists()) {
            try {
                htmlcode2x2_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_File, htmlcode2x2);

// jpg_2x2_same
        String htmlcode2x2_same = readStringFromFile(htmlFile_jpg_2x2_same);
        if (htmlcode2x2_same != null && htmlcode2x2_same.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same = htmlcode2x2_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_File = new File(addPath + File.separator + htmlFile_jpg_2x2_same.getName());
        if (!htmlcode2x2_same_File.exists()) {
            try {
                htmlcode2x2_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_File, htmlcode2x2_same);

// jpg_2x2_same_colum
        String htmlcode2x2_same_colum = readStringFromFile(htmlFile_jpg_2x2_same_colum);
        if (htmlcode2x2_same_colum != null && htmlcode2x2_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_colum = htmlcode2x2_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_colum_File = new File(addPath + File.separator + htmlFile_jpg_2x2_same_colum.getName());
        if (!htmlcode2x2_same_colum_File.exists()) {
            try {
                htmlcode2x2_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_colum_File, htmlcode2x2_same_colum);


// jpg_2x2_same_delay
        String htmlcode2x2_same_delay = readStringFromFile(htmlFile_jpg_2x2_same_delay);
        if (htmlcode2x2_same_delay != null && htmlcode2x2_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_delay = htmlcode2x2_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_delay_File = new File(addPath + File.separator + htmlFile_jpg_2x2_same_delay.getName());
        if (!htmlcode2x2_same_delay_File.exists()) {
            try {
                htmlcode2x2_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_delay_File, htmlcode2x2_same_delay);


// jpg_2x2_same_row
        String htmlcode2x2_same_row = readStringFromFile(htmlFile_jpg_2x2_same_row);
        if (htmlcode2x2_same_row != null && htmlcode2x2_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_row = htmlcode2x2_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_row_File = new File(addPath + File.separator + htmlFile_jpg_2x2_same_row.getName());
        if (!htmlcode2x2_same_row_File.exists()) {
            try {
                htmlcode2x2_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_row_File, htmlcode2x2_same_row);


// jpg_2x2_sequence
        String htmlcode2x2_sequence = readStringFromFile(htmlFile_jpg_2x2_sequence);
        if (htmlcode2x2_sequence != null && htmlcode2x2_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_sequence = htmlcode2x2_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_sequence_File = new File(addPath + File.separator + htmlFile_jpg_2x2_sequence.getName());
        if (!htmlcode2x2_sequence_File.exists()) {
            try {
                htmlcode2x2_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_sequence_File, htmlcode2x2_sequence);




        // zhtmlFile_jpg_3x3_flow_left
        String zhtmlFile_jpg_3x3_flow_left = readStringFromFile(htmlFile_jpg_3x3_flow_left);
        if (zhtmlFile_jpg_3x3_flow_left != null && zhtmlFile_jpg_3x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_3x3_flow_left = zhtmlFile_jpg_3x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_3x3_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_3x3_flow_left.getName());
        if (!zhtmlFile_jpg_3x3_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_3x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_3x3_flow_left_File, zhtmlFile_jpg_3x3_flow_left);



        // zhtmlFile_jpg_3x3_flow_right
        String zhtmlFile_jpg_3x3_flow_right = readStringFromFile(htmlFile_jpg_3x3_flow_right);
        if (zhtmlFile_jpg_3x3_flow_right != null && zhtmlFile_jpg_3x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_3x3_flow_right = zhtmlFile_jpg_3x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_3x3_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_3x3_flow_right.getName());
        if (!zhtmlFile_jpg_3x3_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_3x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_3x3_flow_right_File, zhtmlFile_jpg_3x3_flow_right);



        String htmlcode3x3 = readStringFromFile(htmlFile_jpg_3x3);
        if (htmlcode3x3 != null && htmlcode3x3.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3 = htmlcode3x3.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_File = new File(addPath + File.separator + htmlFile_jpg_3x3.getName());
        if (!htmlcode3x3_File.exists()) {
            try {
                htmlcode3x3_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_File, htmlcode3x3);

        // jpg_3x3_same
        String htmlcode3x3_same = readStringFromFile(htmlFile_jpg_3x3_same);
        if (htmlcode3x3_same != null && htmlcode3x3_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same = htmlcode3x3_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_File = new File(addPath + File.separator + htmlFile_jpg_3x3_same.getName());
        if (!htmlcode3x3_same_File.exists()) {
            try {
                htmlcode3x3_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_File, htmlcode3x3_same);

// jpg_3x3_same_colum
        String htmlcode3x3_same_colum = readStringFromFile(htmlFile_jpg_3x3_same_colum);
        if (htmlcode3x3_same_colum != null && htmlcode3x3_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_colum = htmlcode3x3_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_colum_File = new File(addPath + File.separator + htmlFile_jpg_3x3_same_colum.getName());
        if (!htmlcode3x3_same_colum_File.exists()) {
            try {
                htmlcode3x3_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_colum_File, htmlcode3x3_same_colum);


// jpg_3x3_same_delay
        String htmlcode3x3_same_delay = readStringFromFile(htmlFile_jpg_3x3_same_delay);
        if (htmlcode3x3_same_delay != null && htmlcode3x3_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_delay = htmlcode3x3_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_delay_File = new File(addPath + File.separator + htmlFile_jpg_3x3_same_delay.getName());
        if (!htmlcode3x3_same_delay_File.exists()) {
            try {
                htmlcode3x3_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_delay_File, htmlcode3x3_same_delay);


// jpg_3x3_same_row
        String htmlcode3x3_same_row = readStringFromFile(htmlFile_jpg_3x3_same_row);
        if (htmlcode3x3_same_row != null && htmlcode3x3_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_row = htmlcode3x3_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_row_File = new File(addPath + File.separator + htmlFile_jpg_3x3_same_row.getName());
        if (!htmlcode3x3_same_row_File.exists()) {
            try {
                htmlcode3x3_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_row_File, htmlcode3x3_same_row);


// jpg_3x3_sequence
        String htmlcode3x3_sequence = readStringFromFile(htmlFile_jpg_3x3_sequence);
        if (htmlcode3x3_sequence != null && htmlcode3x3_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_sequence = htmlcode3x3_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_sequence_File = new File(addPath + File.separator + htmlFile_jpg_3x3_sequence.getName());
        if (!htmlcode3x3_sequence_File.exists()) {
            try {
                htmlcode3x3_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_sequence_File, htmlcode3x3_sequence);

        String htmlcode3x5 = readStringFromFile(htmlFile_jpg_3x5);
        if (htmlcode3x5 != null && htmlcode3x5.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5 = htmlcode3x5.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_File = new File(addPath + File.separator + htmlFile_jpg_3x5.getName());
        if (!htmlcode3x5_File.exists()) {
            try {
                htmlcode3x5_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_File, htmlcode3x5);

        // jpg_3x5_same
        String htmlcode3x5_same = readStringFromFile(htmlFile_jpg_3x5_same);
        if (htmlcode3x5_same != null && htmlcode3x5_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same = htmlcode3x5_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_File = new File(addPath + File.separator + htmlFile_jpg_3x5_same.getName());
        if (!htmlcode3x5_same_File.exists()) {
            try {
                htmlcode3x5_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_File, htmlcode3x5_same);

// jpg_3x5_same_colum
        String htmlcode3x5_same_colum = readStringFromFile(htmlFile_jpg_3x5_same_colum);
        if (htmlcode3x5_same_colum != null && htmlcode3x5_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_colum = htmlcode3x5_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_colum_File = new File(addPath + File.separator + htmlFile_jpg_3x5_same_colum.getName());
        if (!htmlcode3x5_same_colum_File.exists()) {
            try {
                htmlcode3x5_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_colum_File, htmlcode3x5_same_colum);


// jpg_3x5_same_delay
        String htmlcode3x5_same_delay = readStringFromFile(htmlFile_jpg_3x5_same_delay);
        if (htmlcode3x5_same_delay != null && htmlcode3x5_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_delay = htmlcode3x5_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_delay_File = new File(addPath + File.separator + htmlFile_jpg_3x5_same_delay.getName());
        if (!htmlcode3x5_same_delay_File.exists()) {
            try {
                htmlcode3x5_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_delay_File, htmlcode3x5_same_delay);


// jpg_3x5_same_row
        String htmlcode3x5_same_row = readStringFromFile(htmlFile_jpg_3x5_same_row);
        if (htmlcode3x5_same_row != null && htmlcode3x5_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_row = htmlcode3x5_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_row_File = new File(addPath + File.separator + htmlFile_jpg_3x5_same_row.getName());
        if (!htmlcode3x5_same_row_File.exists()) {
            try {
                htmlcode3x5_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_row_File, htmlcode3x5_same_row);


// jpg_3x5_sequence
        String htmlcode3x5_sequence = readStringFromFile(htmlFile_jpg_3x5_sequence);
        if (htmlcode3x5_sequence != null && htmlcode3x5_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_sequence = htmlcode3x5_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_sequence_File = new File(addPath + File.separator + htmlFile_jpg_3x5_sequence.getName());
        if (!htmlcode3x5_sequence_File.exists()) {
            try {
                htmlcode3x5_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_sequence_File, htmlcode3x5_sequence);

        String htmlcode3d = readStringFromFile(htmlFile_jpg__3d);
        if (htmlcode3d != null && htmlcode3d.contains("zukgitPlaceHolderindex")) {
            htmlcode3d = htmlcode3d.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3d_File = new File(addPath + File.separator + htmlFile_jpg__3d.getName());
        if (!htmlcode3d_File.exists()) {
            try {
                htmlcode3d_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3d_File, htmlcode3d);


        // zhtmlFile_jpg_4x2_flow_left
        String zhtmlFile_jpg_4x2_flow_left = readStringFromFile(htmlFile_jpg_4x2_flow_left);
        if (zhtmlFile_jpg_4x2_flow_left != null && zhtmlFile_jpg_4x2_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x2_flow_left = zhtmlFile_jpg_4x2_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x2_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_4x2_flow_left.getName());
        if (!zhtmlFile_jpg_4x2_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_4x2_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x2_flow_left_File, zhtmlFile_jpg_4x2_flow_left);


        // zhtmlFile_jpg_4x2_flow_right
        String zhtmlFile_jpg_4x2_flow_right = readStringFromFile(htmlFile_jpg_4x2_flow_right);
        if (zhtmlFile_jpg_4x2_flow_right != null && zhtmlFile_jpg_4x2_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x2_flow_right = zhtmlFile_jpg_4x2_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x2_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_4x2_flow_right.getName());
        if (!zhtmlFile_jpg_4x2_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_4x2_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x2_flow_right_File, zhtmlFile_jpg_4x2_flow_right);



        // zhtmlFile_jpg_4x3_flow_left
        String zhtmlFile_jpg_4x3_flow_left = readStringFromFile(htmlFile_jpg_4x3_flow_left);
        if (zhtmlFile_jpg_4x3_flow_left != null && zhtmlFile_jpg_4x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x3_flow_left = zhtmlFile_jpg_4x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x3_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_4x3_flow_left.getName());
        if (!zhtmlFile_jpg_4x3_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_4x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x3_flow_left_File, zhtmlFile_jpg_4x3_flow_left);



        // zhtmlFile_jpg_4x3_flow_right
        String zhtmlFile_jpg_4x3_flow_right = readStringFromFile(htmlFile_jpg_4x3_flow_right);
        if (zhtmlFile_jpg_4x3_flow_right != null && zhtmlFile_jpg_4x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x3_flow_right = zhtmlFile_jpg_4x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x3_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_4x3_flow_right.getName());
        if (!zhtmlFile_jpg_4x3_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_4x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x3_flow_right_File, zhtmlFile_jpg_4x3_flow_right);


        // zhtmlFile_jpg_4x4_flow_left
        String zhtmlFile_jpg_4x4_flow_left = readStringFromFile(htmlFile_jpg_4x4_flow_left);
        if (zhtmlFile_jpg_4x4_flow_left != null && zhtmlFile_jpg_4x4_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x4_flow_left = zhtmlFile_jpg_4x4_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x4_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_4x4_flow_left.getName());
        if (!zhtmlFile_jpg_4x4_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_4x4_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x4_flow_left_File, zhtmlFile_jpg_4x4_flow_left);


        // zhtmlFile_jpg_4x4_flow_right
        String zhtmlFile_jpg_4x4_flow_right = readStringFromFile(htmlFile_jpg_4x4_flow_right);
        if (zhtmlFile_jpg_4x4_flow_right != null && zhtmlFile_jpg_4x4_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_4x4_flow_right = zhtmlFile_jpg_4x4_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_4x4_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_4x4_flow_right.getName());
        if (!zhtmlFile_jpg_4x4_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_4x4_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_4x4_flow_right_File, zhtmlFile_jpg_4x4_flow_right);



        // zhtmlFile_jpg_5x3_flow_left
        String zhtmlFile_jpg_5x3_flow_left = readStringFromFile(htmlFile_jpg_5x3_flow_left);
        if (zhtmlFile_jpg_5x3_flow_left != null && zhtmlFile_jpg_5x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_5x3_flow_left = zhtmlFile_jpg_5x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_5x3_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_5x3_flow_left.getName());
        if (!zhtmlFile_jpg_5x3_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_5x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_5x3_flow_left_File, zhtmlFile_jpg_5x3_flow_left);



        // zhtmlFile_jpg_5x3_flow_right
        String zhtmlFile_jpg_5x3_flow_right = readStringFromFile(htmlFile_jpg_5x3_flow_right);
        if (zhtmlFile_jpg_5x3_flow_right != null && zhtmlFile_jpg_5x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_5x3_flow_right = zhtmlFile_jpg_5x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_5x3_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_5x3_flow_right.getName());
        if (!zhtmlFile_jpg_5x3_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_5x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_5x3_flow_right_File, zhtmlFile_jpg_5x3_flow_right);


        // zhtmlFile_jpg_5x4_flow_left
        String zhtmlFile_jpg_5x4_flow_left = readStringFromFile(htmlFile_jpg_5x4_flow_left);
        if (zhtmlFile_jpg_5x4_flow_left != null && zhtmlFile_jpg_5x4_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_5x4_flow_left = zhtmlFile_jpg_5x4_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_5x4_flow_left_File = new File(addPath + File.separator + htmlFile_jpg_5x4_flow_left.getName());
        if (!zhtmlFile_jpg_5x4_flow_left_File.exists()) {
            try {
                zhtmlFile_jpg_5x4_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_5x4_flow_left_File, zhtmlFile_jpg_5x4_flow_left);



        // zhtmlFile_jpg_5x4_flow_right
        String zhtmlFile_jpg_5x4_flow_right = readStringFromFile(htmlFile_jpg_5x4_flow_right);
        if (zhtmlFile_jpg_5x4_flow_right != null && zhtmlFile_jpg_5x4_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_jpg_5x4_flow_right = zhtmlFile_jpg_5x4_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_jpg_5x4_flow_right_File = new File(addPath + File.separator + htmlFile_jpg_5x4_flow_right.getName());
        if (!zhtmlFile_jpg_5x4_flow_right_File.exists()) {
            try {
                zhtmlFile_jpg_5x4_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_jpg_5x4_flow_right_File, zhtmlFile_jpg_5x4_flow_right);



    }


    public static void addGIFHtmlFile(String addPath, int length) {

        // zhtmlFile_gif_1x1_flow_left
        String zhtmlFile_gif_1x1_flow_left = readStringFromFile(htmlFile_gif_1x1_flow_left);
        if (zhtmlFile_gif_1x1_flow_left != null && zhtmlFile_gif_1x1_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_1x1_flow_left = zhtmlFile_gif_1x1_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_1x1_flow_left_File = new File(addPath + File.separator + htmlFile_gif_1x1_flow_left.getName());
        if (!zhtmlFile_gif_1x1_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_1x1_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_1x1_flow_left_File, zhtmlFile_gif_1x1_flow_left);


        // zhtmlFile_gif_1x1_flow_right
        String zhtmlFile_gif_1x1_flow_right = readStringFromFile(htmlFile_gif_1x1_flow_right);
        if (zhtmlFile_gif_1x1_flow_right != null && zhtmlFile_gif_1x1_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_1x1_flow_right = zhtmlFile_gif_1x1_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_1x1_flow_right_File = new File(addPath + File.separator + htmlFile_gif_1x1_flow_right.getName());
        if (!zhtmlFile_gif_1x1_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_1x1_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_1x1_flow_right_File, zhtmlFile_gif_1x1_flow_right);

        // zhtmlFile_gif_2x2_flow_left
        String zhtmlFile_gif_2x2_flow_left = readStringFromFile(htmlFile_gif_2x2_flow_left);
        if (zhtmlFile_gif_2x2_flow_left != null && zhtmlFile_gif_2x2_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_2x2_flow_left = zhtmlFile_gif_2x2_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_2x2_flow_left_File = new File(addPath + File.separator + htmlFile_gif_2x2_flow_left.getName());
        if (!zhtmlFile_gif_2x2_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_2x2_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_2x2_flow_left_File, zhtmlFile_gif_2x2_flow_left);


        // zhtmlFile_gif_2x2_flow_right
        String zhtmlFile_gif_2x2_flow_right = readStringFromFile(htmlFile_gif_2x2_flow_right);
        if (zhtmlFile_gif_2x2_flow_right != null && zhtmlFile_gif_2x2_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_2x2_flow_right = zhtmlFile_gif_2x2_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_2x2_flow_right_File = new File(addPath + File.separator + htmlFile_gif_2x2_flow_right.getName());
        if (!zhtmlFile_gif_2x2_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_2x2_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_2x2_flow_right_File, zhtmlFile_gif_2x2_flow_right);

        // gif_2x2
        String htmlcode2x2 = readStringFromFile(htmlFile_gif_2x2);
        if (htmlcode2x2 != null && htmlcode2x2.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2 = htmlcode2x2.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_File = new File(addPath + File.separator + htmlFile_gif_2x2.getName());
        if (!htmlcode2x2_File.exists()) {
            try {
                htmlcode2x2_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_File, htmlcode2x2);

// gif_2x2_same
        String htmlcode2x2_same = readStringFromFile(htmlFile_gif_2x2_same);
        if (htmlcode2x2_same != null && htmlcode2x2_same.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same = htmlcode2x2_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_File = new File(addPath + File.separator + htmlFile_gif_2x2_same.getName());
        if (!htmlcode2x2_same_File.exists()) {
            try {
                htmlcode2x2_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_File, htmlcode2x2_same);

// gif_2x2_same_colum
        String htmlcode2x2_same_colum = readStringFromFile(htmlFile_gif_2x2_same_colum);
        if (htmlcode2x2_same_colum != null && htmlcode2x2_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_colum = htmlcode2x2_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_colum_File = new File(addPath + File.separator + htmlFile_gif_2x2_same_colum.getName());
        if (!htmlcode2x2_same_colum_File.exists()) {
            try {
                htmlcode2x2_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_colum_File, htmlcode2x2_same_colum);


// gif_2x2_same_delay
        String htmlcode2x2_same_delay = readStringFromFile(htmlFile_gif_2x2_same_delay);
        if (htmlcode2x2_same_delay != null && htmlcode2x2_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_delay = htmlcode2x2_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_delay_File = new File(addPath + File.separator + htmlFile_gif_2x2_same_delay.getName());
        if (!htmlcode2x2_same_delay_File.exists()) {
            try {
                htmlcode2x2_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_delay_File, htmlcode2x2_same_delay);


// gif_2x2_same_row
        String htmlcode2x2_same_row = readStringFromFile(htmlFile_gif_2x2_same_row);
        if (htmlcode2x2_same_row != null && htmlcode2x2_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_row = htmlcode2x2_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_row_File = new File(addPath + File.separator + htmlFile_gif_2x2_same_row.getName());
        if (!htmlcode2x2_same_row_File.exists()) {
            try {
                htmlcode2x2_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_row_File, htmlcode2x2_same_row);


// gif_2x2_sequence
        String htmlcode2x2_sequence = readStringFromFile(htmlFile_gif_2x2_sequence);
        if (htmlcode2x2_sequence != null && htmlcode2x2_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_sequence = htmlcode2x2_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_sequence_File = new File(addPath + File.separator + htmlFile_gif_2x2_sequence.getName());
        if (!htmlcode2x2_sequence_File.exists()) {
            try {
                htmlcode2x2_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_sequence_File, htmlcode2x2_sequence);


        // zhtmlFile_gif_3x3_flow_left   gif 3x3
        String zhtmlFile_gif_3x3_flow_left = readStringFromFile(htmlFile_gif_3x3_flow_left);
        if (zhtmlFile_gif_3x3_flow_left != null && zhtmlFile_gif_3x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_3x3_flow_left = zhtmlFile_gif_3x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_3x3_flow_left_File = new File(addPath + File.separator + htmlFile_gif_3x3_flow_left.getName());
        if (!zhtmlFile_gif_3x3_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_3x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_3x3_flow_left_File, zhtmlFile_gif_3x3_flow_left);


        // zhtmlFile_gif_3x3_flow_right
        String zhtmlFile_gif_3x3_flow_right = readStringFromFile(htmlFile_gif_3x3_flow_right);
        if (zhtmlFile_gif_3x3_flow_right != null && zhtmlFile_gif_3x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_3x3_flow_right = zhtmlFile_gif_3x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_3x3_flow_right_File = new File(addPath + File.separator + htmlFile_gif_3x3_flow_right.getName());
        if (!zhtmlFile_gif_3x3_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_3x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_3x3_flow_right_File, zhtmlFile_gif_3x3_flow_right);


        String htmlcode3x3 = readStringFromFile(htmlFile_gif_3x3);
        if (htmlcode3x3 != null && htmlcode3x3.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3 = htmlcode3x3.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_File = new File(addPath + File.separator + htmlFile_gif_3x3.getName());
        if (!htmlcode3x3_File.exists()) {
            try {
                htmlcode3x3_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_File, htmlcode3x3);

        // gif_3x3_same
        String htmlcode3x3_same = readStringFromFile(htmlFile_gif_3x3_same);
        if (htmlcode3x3_same != null && htmlcode3x3_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same = htmlcode3x3_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_File = new File(addPath + File.separator + htmlFile_gif_3x3_same.getName());
        if (!htmlcode3x3_same_File.exists()) {
            try {
                htmlcode3x3_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_File, htmlcode3x3_same);

// gif_3x3_same_colum
        String htmlcode3x3_same_colum = readStringFromFile(htmlFile_gif_3x3_same_colum);
        if (htmlcode3x3_same_colum != null && htmlcode3x3_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_colum = htmlcode3x3_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_colum_File = new File(addPath + File.separator + htmlFile_gif_3x3_same_colum.getName());
        if (!htmlcode3x3_same_colum_File.exists()) {
            try {
                htmlcode3x3_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_colum_File, htmlcode3x3_same_colum);


// gif_3x3_same_delay
        String htmlcode3x3_same_delay = readStringFromFile(htmlFile_gif_3x3_same_delay);
        if (htmlcode3x3_same_delay != null && htmlcode3x3_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_delay = htmlcode3x3_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_delay_File = new File(addPath + File.separator + htmlFile_gif_3x3_same_delay.getName());
        if (!htmlcode3x3_same_delay_File.exists()) {
            try {
                htmlcode3x3_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_delay_File, htmlcode3x3_same_delay);


// gif_3x3_same_row
        String htmlcode3x3_same_row = readStringFromFile(htmlFile_gif_3x3_same_row);
        if (htmlcode3x3_same_row != null && htmlcode3x3_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_row = htmlcode3x3_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_row_File = new File(addPath + File.separator + htmlFile_gif_3x3_same_row.getName());
        if (!htmlcode3x3_same_row_File.exists()) {
            try {
                htmlcode3x3_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_row_File, htmlcode3x3_same_row);


// gif_3x3_sequence
        String htmlcode3x3_sequence = readStringFromFile(htmlFile_gif_3x3_sequence);
        if (htmlcode3x3_sequence != null && htmlcode3x3_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_sequence = htmlcode3x3_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_sequence_File = new File(addPath + File.separator + htmlFile_gif_3x3_sequence.getName());
        if (!htmlcode3x3_sequence_File.exists()) {
            try {
                htmlcode3x3_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_sequence_File, htmlcode3x3_sequence);

        String htmlcode3x5 = readStringFromFile(htmlFile_gif_3x5);
        if (htmlcode3x5 != null && htmlcode3x5.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5 = htmlcode3x5.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_File = new File(addPath + File.separator + htmlFile_gif_3x5.getName());
        if (!htmlcode3x5_File.exists()) {
            try {
                htmlcode3x5_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_File, htmlcode3x5);

        // gif_3x5_same
        String htmlcode3x5_same = readStringFromFile(htmlFile_gif_3x5_same);
        if (htmlcode3x5_same != null && htmlcode3x5_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same = htmlcode3x5_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_File = new File(addPath + File.separator + htmlFile_gif_3x5_same.getName());
        if (!htmlcode3x5_same_File.exists()) {
            try {
                htmlcode3x5_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_File, htmlcode3x5_same);

// gif_3x5_same_colum
        String htmlcode3x5_same_colum = readStringFromFile(htmlFile_gif_3x5_same_colum);
        if (htmlcode3x5_same_colum != null && htmlcode3x5_same_colum.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_colum = htmlcode3x5_same_colum.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_colum_File = new File(addPath + File.separator + htmlFile_gif_3x5_same_colum.getName());
        if (!htmlcode3x5_same_colum_File.exists()) {
            try {
                htmlcode3x5_same_colum_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_colum_File, htmlcode3x5_same_colum);


// gif_3x5_same_delay
        String htmlcode3x5_same_delay = readStringFromFile(htmlFile_gif_3x5_same_delay);
        if (htmlcode3x5_same_delay != null && htmlcode3x5_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_delay = htmlcode3x5_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_delay_File = new File(addPath + File.separator + htmlFile_gif_3x5_same_delay.getName());
        if (!htmlcode3x5_same_delay_File.exists()) {
            try {
                htmlcode3x5_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_delay_File, htmlcode3x5_same_delay);


// gif_3x5_same_row
        String htmlcode3x5_same_row = readStringFromFile(htmlFile_gif_3x5_same_row);
        if (htmlcode3x5_same_row != null && htmlcode3x5_same_row.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_row = htmlcode3x5_same_row.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_row_File = new File(addPath + File.separator + htmlFile_gif_3x5_same_row.getName());
        if (!htmlcode3x5_same_row_File.exists()) {
            try {
                htmlcode3x5_same_row_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_row_File, htmlcode3x5_same_row);


// gif_3x5_sequence
        String htmlcode3x5_sequence = readStringFromFile(htmlFile_gif_3x5_sequence);
        if (htmlcode3x5_sequence != null && htmlcode3x5_sequence.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_sequence = htmlcode3x5_sequence.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_sequence_File = new File(addPath + File.separator + htmlFile_gif_3x5_sequence.getName());
        if (!htmlcode3x5_sequence_File.exists()) {
            try {
                htmlcode3x5_sequence_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_sequence_File, htmlcode3x5_sequence);

        // gif 4x2
        // zhtmlFile_gif_4x2_flow_left  gif 4x2
        String zhtmlFile_gif_4x2_flow_left = readStringFromFile(htmlFile_gif_4x2_flow_left);
        if (zhtmlFile_gif_4x2_flow_left != null && zhtmlFile_gif_4x2_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x2_flow_left = zhtmlFile_gif_4x2_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x2_flow_left_File = new File(addPath + File.separator + htmlFile_gif_4x2_flow_left.getName());
        if (!zhtmlFile_gif_4x2_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_4x2_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x2_flow_left_File, zhtmlFile_gif_4x2_flow_left);


        // zhtmlFile_gif_4x2_flow_right
        String zhtmlFile_gif_4x2_flow_right = readStringFromFile(htmlFile_gif_4x2_flow_right);
        if (zhtmlFile_gif_4x2_flow_right != null && zhtmlFile_gif_4x2_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x2_flow_right = zhtmlFile_gif_4x2_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x2_flow_right_File = new File(addPath + File.separator + htmlFile_gif_4x2_flow_right.getName());
        if (!zhtmlFile_gif_4x2_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_4x2_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x2_flow_right_File, zhtmlFile_gif_4x2_flow_right);


        // gif 4x3
        // zhtmlFile_gif_4x3_flow_left
        String zhtmlFile_gif_4x3_flow_left = readStringFromFile(htmlFile_gif_4x3_flow_left);
        if (zhtmlFile_gif_4x3_flow_left != null && zhtmlFile_gif_4x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x3_flow_left = zhtmlFile_gif_4x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x3_flow_left_File = new File(addPath + File.separator + htmlFile_gif_4x3_flow_left.getName());
        if (!zhtmlFile_gif_4x3_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_4x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x3_flow_left_File, zhtmlFile_gif_4x3_flow_left);


        // zhtmlFile_gif_4x3_flow_right
        String zhtmlFile_gif_4x3_flow_right = readStringFromFile(htmlFile_gif_4x3_flow_right);
        if (zhtmlFile_gif_4x3_flow_right != null && zhtmlFile_gif_4x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x3_flow_right = zhtmlFile_gif_4x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x3_flow_right_File = new File(addPath + File.separator + htmlFile_gif_4x3_flow_right.getName());
        if (!zhtmlFile_gif_4x3_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_4x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x3_flow_right_File, zhtmlFile_gif_4x3_flow_right);



        // zhtmlFile_gif_4x4_flow_left
        String zhtmlFile_gif_4x4_flow_left = readStringFromFile(htmlFile_gif_4x4_flow_left);
        if (zhtmlFile_gif_4x4_flow_left != null && zhtmlFile_gif_4x4_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x4_flow_left = zhtmlFile_gif_4x4_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x4_flow_left_File = new File(addPath + File.separator + htmlFile_gif_4x4_flow_left.getName());
        if (!zhtmlFile_gif_4x4_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_4x4_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x4_flow_left_File, zhtmlFile_gif_4x4_flow_left);



        // zhtmlFile_gif_4x4_flow_right
        String zhtmlFile_gif_4x4_flow_right = readStringFromFile(htmlFile_gif_4x4_flow_right);
        if (zhtmlFile_gif_4x4_flow_right != null && zhtmlFile_gif_4x4_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_4x4_flow_right = zhtmlFile_gif_4x4_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_4x4_flow_right_File = new File(addPath + File.separator + htmlFile_gif_4x4_flow_right.getName());
        if (!zhtmlFile_gif_4x4_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_4x4_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_4x4_flow_right_File, zhtmlFile_gif_4x4_flow_right);



        // zhtmlFile_gif_5x3_flow_left
        String zhtmlFile_gif_5x3_flow_left = readStringFromFile(htmlFile_gif_5x3_flow_left);
        if (zhtmlFile_gif_5x3_flow_left != null && zhtmlFile_gif_5x3_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_5x3_flow_left = zhtmlFile_gif_5x3_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_5x3_flow_left_File = new File(addPath + File.separator + htmlFile_gif_5x3_flow_left.getName());
        if (!zhtmlFile_gif_5x3_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_5x3_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_5x3_flow_left_File, zhtmlFile_gif_5x3_flow_left);



        // zhtmlFile_gif_5x3_flow_right
        String zhtmlFile_gif_5x3_flow_right = readStringFromFile(htmlFile_gif_5x3_flow_right);
        if (zhtmlFile_gif_5x3_flow_right != null && zhtmlFile_gif_5x3_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_5x3_flow_right = zhtmlFile_gif_5x3_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_5x3_flow_right_File = new File(addPath + File.separator + htmlFile_gif_5x3_flow_right.getName());
        if (!zhtmlFile_gif_5x3_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_5x3_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_5x3_flow_right_File, zhtmlFile_gif_5x3_flow_right);



        // zhtmlFile_gif_5x4_flow_left
        String zhtmlFile_gif_5x4_flow_left = readStringFromFile(htmlFile_gif_5x4_flow_left);
        if (zhtmlFile_gif_5x4_flow_left != null && zhtmlFile_gif_5x4_flow_left.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_5x4_flow_left = zhtmlFile_gif_5x4_flow_left.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_5x4_flow_left_File = new File(addPath + File.separator + htmlFile_gif_5x4_flow_left.getName());
        if (!zhtmlFile_gif_5x4_flow_left_File.exists()) {
            try {
                zhtmlFile_gif_5x4_flow_left_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_5x4_flow_left_File, zhtmlFile_gif_5x4_flow_left);



        // zhtmlFile_gif_5x4_flow_right
        String zhtmlFile_gif_5x4_flow_right = readStringFromFile(htmlFile_gif_5x4_flow_right);
        if (zhtmlFile_gif_5x4_flow_right != null && zhtmlFile_gif_5x4_flow_right.contains("zukgitPlaceHolderindex")) {
            zhtmlFile_gif_5x4_flow_right = zhtmlFile_gif_5x4_flow_right.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File zhtmlFile_gif_5x4_flow_right_File = new File(addPath + File.separator + htmlFile_gif_5x4_flow_right.getName());
        if (!zhtmlFile_gif_5x4_flow_right_File.exists()) {
            try {
                zhtmlFile_gif_5x4_flow_right_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(zhtmlFile_gif_5x4_flow_right_File, zhtmlFile_gif_5x4_flow_right);



        String htmlcode3d = readStringFromFile(htmlFile_gif__3d);
        if (htmlcode3d != null && htmlcode3d.contains("zukgitPlaceHolderindex")) {
            htmlcode3d = htmlcode3d.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3d_File = new File(addPath + File.separator + htmlFile_gif__3d.getName());
        if (!htmlcode3d_File.exists()) {
            try {
                htmlcode3d_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3d_File, htmlcode3d);
    }


    public static void addMP4HtmlFile(String addPath, int length) {
        String htmlcode2x2 = readStringFromFile(htmlFile_mp4_2x2);
        if (htmlcode2x2 != null && htmlcode2x2.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2 = htmlcode2x2.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_File = new File(addPath + File.separator + htmlFile_mp4_2x2.getName());
        if (!htmlcode2x2_File.exists()) {
            try {
                htmlcode2x2_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_File, htmlcode2x2);


        String htmlcode2x2_same = readStringFromFile(htmlFile_mp4_2x2_same);
        if (htmlcode2x2_same != null && htmlcode2x2_same.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same = htmlcode2x2_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_File = new File(addPath + File.separator + htmlFile_mp4_2x2_same.getName());
        if (!htmlcode2x2_same_File.exists()) {
            try {
                htmlcode2x2_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_File, htmlcode2x2_same);


        String htmlcode2x2_same_delay = readStringFromFile(htmlFile_mp4_2x2_same_delay);
        if (htmlcode2x2_same_delay != null && htmlcode2x2_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode2x2_same_delay = htmlcode2x2_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode2x2_same_delay_File = new File(addPath + File.separator + htmlFile_mp4_2x2_same_delay.getName());
        if (!htmlcode2x2_same_delay_File.exists()) {
            try {
                htmlcode2x2_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode2x2_same_delay_File, htmlcode2x2_same_delay);



        String htmlcode3x3 = readStringFromFile(htmlFile_mp4_3x3);
        if (htmlcode3x3 != null && htmlcode3x3.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3 = htmlcode3x3.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_File = new File(addPath + File.separator + htmlFile_mp4_3x3.getName());
        if (!htmlcode3x3_File.exists()) {
            try {
                htmlcode3x3_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_File, htmlcode3x3);


        String htmlcode3x3_same = readStringFromFile(htmlFile_mp4_3x3_same);
        if (htmlcode3x3_same != null && htmlcode3x3_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same = htmlcode3x3_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_File = new File(addPath + File.separator + htmlFile_mp4_3x3_same.getName());
        if (!htmlcode3x3_same_File.exists()) {
            try {
                htmlcode3x3_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_File, htmlcode3x3_same);



        String htmlcode3x3_same_delay = readStringFromFile(htmlFile_mp4_3x3_same_delay);
        if (htmlcode3x3_same_delay != null && htmlcode3x3_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x3_same_delay = htmlcode3x3_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x3_same_delay_File = new File(addPath + File.separator + htmlFile_mp4_3x3_same_delay.getName());
        if (!htmlcode3x3_same_delay_File.exists()) {
            try {
                htmlcode3x3_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x3_same_delay_File, htmlcode3x3_same_delay);



        String htmlcode3x5 = readStringFromFile(htmlFile_mp4_3x5);
        if (htmlcode3x5 != null && htmlcode3x5.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5 = htmlcode3x5.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_File = new File(addPath + File.separator + htmlFile_mp4_3x5.getName());
        if (!htmlcode3x5_File.exists()) {
            try {
                htmlcode3x5_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_File, htmlcode3x5);


        String htmlcode3x5_same = readStringFromFile(htmlFile_mp4_3x5_same);
        if (htmlcode3x5_same != null && htmlcode3x5_same.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same = htmlcode3x5_same.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_File = new File(addPath + File.separator + htmlFile_mp4_3x5_same.getName());
        if (!htmlcode3x5_same_File.exists()) {
            try {
                htmlcode3x5_same_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_File, htmlcode3x5_same);



        String htmlcode3x5_same_delay = readStringFromFile(htmlFile_mp4_3x5_same_delay);
        if (htmlcode3x5_same_delay != null && htmlcode3x5_same_delay.contains("zukgitPlaceHolderindex")) {
            htmlcode3x5_same_delay = htmlcode3x5_same_delay.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3x5_same_delay_File = new File(addPath + File.separator + htmlFile_mp4_3x5_same_delay.getName());
        if (!htmlcode3x5_same_delay_File.exists()) {
            try {
                htmlcode3x5_same_delay_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3x5_same_delay_File, htmlcode3x5_same_delay);


        String htmlcode3d = readStringFromFile(htmlFile_mp4__3d);
        if (htmlcode3d != null && htmlcode3d.contains("zukgitPlaceHolderindex")) {
            htmlcode3d = htmlcode3d.replaceAll("zukgitPlaceHolderindex", length + "");
        }
        File htmlcode3d_File = new File(addPath + File.separator + htmlFile_mp4__3d.getName());
        if (!htmlcode3d_File.exists()) {
            try {
                htmlcode3d_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode3d_File, htmlcode3d);


    }


    // zzz
    public static void addIndivitualMP4HtmlFile(String addPath, String defineString , String addString) {


        String htmlcode_mp4_2x2_indivitual = readStringFromFile(htmlFile_mp4_2x2_indivitual);
        if (htmlcode_mp4_2x2_indivitual != null && htmlcode_mp4_2x2_indivitual.contains("zukgitPlaceHolderArrayDefine")
                && htmlcode_mp4_2x2_indivitual.contains("zukgitPlaceHolderArrayAdd") ) {
            htmlcode_mp4_2x2_indivitual = htmlcode_mp4_2x2_indivitual.replaceAll("zukgitPlaceHolderArrayDefine", defineString);
            htmlcode_mp4_2x2_indivitual = htmlcode_mp4_2x2_indivitual.replaceAll("zukgitPlaceHolderArrayAdd", addString);
        }
        File htmlcode_mp4_2x2_indivitual_File = new File(addPath + File.separator + htmlFile_mp4_2x2_indivitual.getName());
        if (!htmlcode_mp4_2x2_indivitual_File.exists()) {
            try {
                htmlcode_mp4_2x2_indivitual_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode_mp4_2x2_indivitual_File, htmlcode_mp4_2x2_indivitual);


        String htmlcode_mp4_3x3_indivitual = readStringFromFile(htmlFile_mp4_3x3_indivitual);
        if (htmlcode_mp4_3x3_indivitual != null && htmlcode_mp4_3x3_indivitual.contains("zukgitPlaceHolderArrayDefine")
                && htmlcode_mp4_3x3_indivitual.contains("zukgitPlaceHolderArrayAdd") ) {
            htmlcode_mp4_3x3_indivitual = htmlcode_mp4_3x3_indivitual.replaceAll("zukgitPlaceHolderArrayDefine", defineString);
            htmlcode_mp4_3x3_indivitual = htmlcode_mp4_3x3_indivitual.replaceAll("zukgitPlaceHolderArrayAdd", addString);
        }
        File htmlcode_mp4_3x3_indivitual_File = new File(addPath + File.separator + htmlFile_mp4_3x3_indivitual.getName());
        if (!htmlcode_mp4_3x3_indivitual_File.exists()) {
            try {
                htmlcode_mp4_3x3_indivitual_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode_mp4_3x3_indivitual_File, htmlcode_mp4_3x3_indivitual);


        String htmlcode_mp4_3x5_indivitual = readStringFromFile(htmlFile_mp4_3x5_indivitual);
        if (htmlcode_mp4_3x5_indivitual != null && htmlcode_mp4_3x5_indivitual.contains("zukgitPlaceHolderArrayDefine")
                && htmlcode_mp4_3x5_indivitual.contains("zukgitPlaceHolderArrayAdd") ) {
            htmlcode_mp4_3x5_indivitual = htmlcode_mp4_3x5_indivitual.replaceAll("zukgitPlaceHolderArrayDefine", defineString);
            htmlcode_mp4_3x5_indivitual = htmlcode_mp4_3x5_indivitual.replaceAll("zukgitPlaceHolderArrayAdd", addString);
        }
        File htmlcode_mp4_3x5_indivitual_File = new File(addPath + File.separator + htmlFile_mp4_3x5_indivitual.getName());
        if (!htmlcode_mp4_3x5_indivitual_File.exists()) {
            try {
                htmlcode_mp4_3x5_indivitual_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode_mp4_3x5_indivitual_File, htmlcode_mp4_3x5_indivitual);


        String htmlcode_mp4__3d_indivitual = readStringFromFile(htmlFile_mp4__3d_indivitual);
        if (htmlcode_mp4__3d_indivitual != null && htmlcode_mp4__3d_indivitual.contains("zukgitPlaceHolderArrayDefine")
                && htmlcode_mp4__3d_indivitual.contains("zukgitPlaceHolderArrayAdd") ) {
            htmlcode_mp4__3d_indivitual = htmlcode_mp4__3d_indivitual.replaceAll("zukgitPlaceHolderArrayDefine", defineString);
            htmlcode_mp4__3d_indivitual = htmlcode_mp4__3d_indivitual.replaceAll("zukgitPlaceHolderArrayAdd", addString);
        }
        File htmlcode_mp4__3d_indivitual_File = new File(addPath + File.separator + htmlFile_mp4__3d_indivitual.getName());
        if (!htmlcode_mp4__3d_indivitual_File.exists()) {
            try {
                htmlcode_mp4__3d_indivitual_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContentToFile(htmlcode_mp4__3d_indivitual_File, htmlcode_mp4__3d_indivitual);

    }

    public static void collectAllG() {
        // mingwen/g/gif
        String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "g" + File.separator + "gif";
        File allFileDir = new File(allPath);

        if (!allFileDir.exists()) {
            allFileDir.mkdirs();
        }

        for (int i = 0; i < gifFileList_mingwen.size(); i++) {
            File fileItem = (File) gifFileList_mingwen.get(i);
            String name = fileItem.getName();
            int nameIndex = i + 1;
            String nameIdnex = nameIndex + ".gif";
            //  File allFileItem = new File(allPath +File.separator+name);
            File allFileItem = new File(allPath + File.separator + nameIdnex);
            fileCopy(fileItem, allFileItem);

        }

        addGIFHtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "g", gifFileList_mingwen.size());
    }



    static int copyIndex  = 1;

    public static void fileCopy(File origin, File target) {
        System.out.println("复制中（当前索引:"+copyIndex+"  总长:"+allPointFile_mingwen.size()+"）:  当前路径:" + origin.getAbsolutePath());
        copyIndex++;
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

    // 检查对应的明文文件夹是存在
    public static boolean isFileReady() {   // 初始化静态变量 File1 原始mp3文件  File2-加密mp3文件   File3-解密mp3文件
        String usrDir = System.getProperties().getProperty("user.dir");

        // /mingwen 是否存在
        File mingwenFileDir = new File(usrDir + mingwenDir);

        // /miwen 是否存在
        File miwenFileDir = new File(usrDir + miwenDir);
        if (!miwenFileDir.exists()) {
            mingwenFileDir.mkdirs();
            miwenFileDir.mkdirs();
            System.out.println("#1 加密: 请在mingwen路径下放置原始明文文件！后重新执行");
            System.out.println("#2 解密: 请在miwen路径下放置加密文件！后重新执行");
            return false;
        }

        //  已经放置好 明文文件后

//        File[] decodeFilelist =  decodeDir.listFiles();
//        for (File itemFile: decodeFilelist){
//            System.out.println("-------------");
//            System.out.println(itemFile.getName());
//        }
        // AnalysisDir(miwenFileDir);   // 1. 列出所有文件夹 文件 路径 名称   循环遍历
        getAllSubFile(miwenFileDir);   //  nio 遍历
        System.out.println("密文 minwen路径:"+ miwenFileDir.getAbsolutePath());
        InitMingweList();     // 解析 明文_明文的关系
        todoJIEMA();
        return true;
    }


    public static void todoJIEMA() {
        Map.Entry<File, File> entryFile;

        int mapSize = mingwen_miwen_Map.size();
        int curIndex = 1 ;
        if (mingwen_miwen_Map != null && mingwen_miwen_Map.size() > 0) {
            Iterator iterator = mingwen_miwen_Map.entrySet().iterator();


            while (iterator.hasNext()) {
                entryFile = (Map.Entry<File, File>) iterator.next();
                File miwenFile = entryFile.getKey(); //Map的Key
                File mingwenFile = entryFile.getValue();  //Map的Value
                System.out.println("当前解密索引:"+curIndex+"  总长度:"+mapSize+"  解密文件:"+mingwenFile.getAbsolutePath());
                createDecryFile(miwenFile, mingwenFile);
                curIndex++;
            }
        }

    }

    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static void createDecryFile(File encryptFile, File decryptFile) {


        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;


        try {
            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);


            decryptileOutputStream = new FileOutputStream(decryptFile);
            decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);


            int encrypt_offset = 0;
            int encrypt_position = 0;
            while (encrypt_offset < BYTE_CONTENT_LENGTH) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                encrypt_position = encryptBufferedInputStream.read(TEMP, encrypt_offset, TEMP.length - encrypt_offset);

                if (encrypt_position == -1) {
                    break;
                }
                encrypt_offset += encrypt_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }

            byte[] decrypt_bytes = decrypt(TEMP);  // 对加密文件的加密字节进行解密
            System.out.println("文件大小:"+encryptFile.length()+"   密文加密字节大小:" + TEMP.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

            decryptBufferedOutputStream.write(decrypt_bytes);
            decryptBufferedOutputStream.flush();


            // 读取 encryptFile加密文件中正常的字节    BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 解密File(Head已经解密)文件中去
            while ((encrypt_offset = encryptBufferedInputStream.read(TEMP, 0, TEMP.length)) != -1) {
                decryptBufferedOutputStream.write(TEMP, 0, encrypt_offset);
                decryptBufferedOutputStream.flush();
            }

            encryptBufferedInputStream.close();
            decryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    static {
        try {
/*
            // 明文与密文大小不一致  明文大小可任意长度
            // 明文 1  -------- 密文 8
            // 明文 8  -------- 密文 16
            // 明文 1024  -------- 密文 1032
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
*/



/*            // 同默认的 DES 加密方式
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey.getBytes());
            IvParameterSpec iv = new IvParameterSpec(strDefaultKey.getBytes());
            AlgorithmParameterSpec  ap = iv;
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, ap);
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key,ap);
*/


            // 明文与密文大小一致  明文大小必须为8字节的整数倍 否则报错
            // 报错类型为 javax.crypto.IllegalBlockSizeException: Input length not multiple of 8 bytes
            // 明文 8  -------- 密文 8
            // 明文 16  -------- 密文 16
            // 明文 1024  -------- 密文 1024
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey.getBytes());
            encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {

        }

    }


    /**
     * 从指定字符串生成密匙，密匙所需的字节数组度长为8位，缺乏8位时，面后补0，超越8位时，只取后面8位
     *
     * @param arrBTmp 成构字符串的字节数组
     * @return 生成的密匙
     */
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


    public static File generalFile;
    public static File encryptFile;
    public static File decryptFile;
    public static byte[] TEMP = new byte[BYTE_CONTENT_LENGTH];


//    public static ArrayList<File> jpgFileList_miwen = new ArrayList<File>();
//    public static ArrayList<File> mp4FileList_miwen = new ArrayList<File>();
//    public static ArrayList<File> allFile_miwen = new ArrayList<File>();
//    public static ArrayList<File> alldir_miwen = new ArrayList<File>();
//    public static ArrayList<File> allPointFile_miwen = new ArrayList<File>();


    public static void InitMingweList() {

        System.out.println("step2A: 密文件夹+文件 总数 =  " + allFile_miwen.size());
        System.out.println("step2B: 密文件夹 总数 =  " + alldir_miwen.size());
        System.out.println("step2C: 密文件 总数 =  " + allPointFile_miwen.size());
        System.out.println("step2C: 密文件 JPG总数 =  " + jpgFileList_miwen.size());
        System.out.println("step2C: 密文件 GIF总数 =  " + gifFileList_miwen.size());
        System.out.println("step2C: 密文件 MP4总数 =  " + mp4FileList_miwen.size());
        System.out.println("=======密文文件夹列表 Begin   alldir_miwen.size()="+alldir_miwen.size()+" =======");



        //  废弃--begin
        // 获取 miwen 文件夹  然后把  miwen  替换为mingwen   当前 密文文件夹为0  所以 不执行 这里
        for (int i = 0; i < alldir_miwen.size(); i++) {
            System.out.println("step2D: 密文件夹" + (i + 1) + "  路径: " + alldir_miwen.get(i).getAbsolutePath());
            String miwenDir = alldir_miwen.get(i).getAbsolutePath();
            String mingwenDir = miwenDir.replace("miwen", "mingwen");
            File mingwen_dirFile = new File(mingwenDir);   // 创建所有的 mingwen dir
            if (!mingwen_dirFile.exists()) {
                mingwen_dirFile.mkdirs();
            }
            alldir_mingwen.add(mingwen_dirFile);
        }
        //  废弃--end

        File curJPGDirFile = new File(curJPG_PATH);
        File curMP4DirFile = new File(curMP4_PATH);
        File curGIFDirFile = new File(curGIF_PATH);

        if(!curMP4DirFile.exists()){
            curMP4DirFile.mkdirs();
        }


        if(!curJPGDirFile.exists()){
            curJPGDirFile.mkdirs();
        }

        if(!curGIFDirFile.exists()){
            curGIFDirFile.mkdirs();
        }




        System.out.println("=======密文文件夹列表 End =======");
        System.out.println("=======明文件夹列表 Begin 长度:  alldir_mingwen.size() "+alldir_mingwen.size() +" =======");

        for (int i = 0; i < alldir_mingwen.size(); i++) {
            System.out.println("step2E: 明文件夹" + (i + 1) + "  密文路径: " + alldir_mingwen.get(i).getAbsolutePath());
        }
        System.out.println("=======明文文件夹列表 End =======");


        System.out.println("=======密文件列表 Begin  文件总数:" + allPointFile_miwen.size() + " =======");

        System.out.println("*********** jpg文件列表Begin:"+jpgFileList_miwen.size()+ "*********** ");
        for (int i = 0; i < jpgFileList_miwen.size(); i++) {
            System.out.println("step2F: 密jpg文件 index:" + (i + 1) + "   密文路径: " + jpgFileList_miwen.get(i).getAbsolutePath());
            String miwen_jpgFile = jpgFileList_miwen.get(i).getAbsolutePath();

            //  应该是获取到  /i/的路径 然后 拿到它的剩余的名称的字符串
            String endName = miwen_jpgFile.substring(miwen_jpgFile.indexOf(I_TAG)+I_TAG.length());
            System.out.println("step2F_1 jpg :  miwen_jpgFile = "+miwen_jpgFile);
            System.out.println("step2F_1  jpg:  endName = "+endName);
            String mingwenPointFile = curJPGDirFile + File.separator+endName;


            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".jpg";

            File mingwenFile = new File(mingwenPointFile_xxx);
            System.out.println("step2G_0: 明文jpg文件 index:" + i + "    明文path:" + mingwenFile.getAbsolutePath());

            if (!mingwenFile.exists()) {
                try {
//                    mingwenFile.mkdirs();
                    mingwenFile.getParentFile().mkdirs();
                    mingwenFile.createNewFile();
                    System.out.println("step2G: 明文jpg文件 index:" + i + "    明文path:" + mingwenFile.getAbsolutePath());
                    jpgFileList_mingwen.add(mingwenFile);
                } catch (IOException e) {
                    System.out.println("step2G_1 :明文 jpg创建失败！"+ " 明文path:" + mingwenFile.getAbsolutePath());
                }
            }else{
                jpgFileList_mingwen.add(mingwenFile);
            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(jpgFileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** jpg文件列表End*********** ");


        System.out.println("*********** mp4文件列表Begin:"+mp4FileList_miwen.size()+"*********** ");
        for (int i = 0; i < mp4FileList_miwen.size(); i++) {
            System.out.println("step2H: 密mp4文件 index:" + (i + 1) + "   密文path: " + mp4FileList_miwen.get(i).getAbsolutePath());

          //E:\2020_GIT\miwen\Z_VI1\v\am\bei\am_0005zywxx014_bei_001
            String miwen_mp4File = mp4FileList_miwen.get(i).getAbsolutePath();
//            String mingwenPointFile = miwen_mp4File.replace("miwen", "mingwen");


            // endName = \2020_GIT\miwen\Z_VI1\v\am\bei\am_0005zywxx014_bei_001
            String endName = miwen_mp4File.substring(miwen_mp4File.indexOf(M_TAG)+M_TAG.length());
            String mingwenPointFile = curMP4DirFile + File.separator+endName;

            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".mp4";

            System.out.println("step2F_1 mp4 :  miwen_mp4File = "+mingwenPointFile);
            System.out.println("step2F_1  mp4:  endName = "+endName);

            File mingwenFile = new File(mingwenPointFile_xxx);
            if (!mingwenFile.exists()) {
                try {
                    mingwenFile.getParentFile().mkdirs();
                    mingwenFile.createNewFile();
                    System.out.println("step2I: 明文mp4文件 index:" + i + "    明文path:" + mingwenFile.getAbsolutePath());
                    mp4FileList_mingwen.add(mingwenFile);
                    String name = mingwenFile.getName();
                    if (name.startsWith("wm")) {
                        wm_mp4FileList_mingwen.add(mingwenFile);
                    } else if (name.startsWith("ym")) {
                        ym_mp4FileList_mingwen.add(mingwenFile);

                    } else if (name.startsWith("up")) {
                        up_mp4FileList_mingwen.add(mingwenFile);
                    } else if (name.startsWith("support")) {
                        support_mp4FileList_mingwen.add(mingwenFile);
                    } else if (name.startsWith("am")) {
                        am_mp4FileList_mingwen.add(mingwenFile);
                    } else if (name.startsWith("fakeface")) {
                        fakeface_mp4FileList_mingwen.add(mingwenFile);
                    }


                } catch (IOException e) {
                    System.out.println("明文 mp4 创建失败！");
                }
            }else{
                mp4FileList_mingwen.add(mingwenFile);
            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(mp4FileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** mp4文件列表End*********** ");


        System.out.println("*********** gif文件列表Begin:"+ gifFileList_miwen.size()+"*********** ");
        for (int i = 0; i < gifFileList_miwen.size(); i++) {
            System.out.println("step2J: 密gif文件 index:" + (i + 1) + "   路径: " + gifFileList_miwen.get(i).getAbsolutePath());
            String miwen_gifFile = gifFileList_miwen.get(i).getAbsolutePath();
//            String mingwenPointFile = miwen_gifFile.replace("miwen", "mingwen");
            String endName = miwen_gifFile.substring(miwen_gifFile.indexOf(G_TAG)+G_TAG.length());
            String mingwenPointFile = curGIFDirFile + File.separator+endName;


            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".gif";

            File mingwenFile = new File(mingwenPointFile_xxx);
            if (!mingwenFile.exists()) {
                try {
                    mingwenFile.getParentFile().mkdirs();
                    mingwenFile.createNewFile();
                    System.out.println("step2K:  明文gif文件 index:" + i + "    明文path:" + mingwenFile.getAbsolutePath());
                    gifFileList_mingwen.add(mingwenFile);
                } catch (IOException e) {
                    System.out.println("明文 gif 创建失败！");
                }
            }else{

                gifFileList_mingwen.add(mingwenFile);

            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(gifFileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** gif文件列表End*********** ");


        System.out.println("step2K: =======文件列表 End  文件总数:" + allPointFile_miwen.size() + " =======");


        todoIndivitualFile(mp4FileList_mingwen);

    }


    public static void todoIndivitualHtml() {

        ArrayList<String> defineArrWord = new ArrayList<String>();
        ArrayList<String> defineAdd = new ArrayList<String>();
        int curIndex = 1;
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String name = entry.getKey();
                int length  = entry.getValue().size();
                String people = "person"+curIndex;
                String defineItem = people+" = { index:"+curIndex+" , path:\"./"+name+"/mp4/\",length:"+length+",};\n";
                defineArrWord.add(defineItem);
                defineAdd.add(people+",");
                curIndex++;
            }
        }

        StringBuilder defineSB = new StringBuilder();
        for (int i = 0; i < defineArrWord.size(); i++) {
            defineSB.append(defineArrWord.get(i));
        }
        String defineSBString = defineSB.toString().trim();

        StringBuilder addSB = new StringBuilder();
        for (int i = 0; i < defineAdd.size(); i++) {
            addSB.append(defineAdd.get(i));
        }

        String addSBString = addSB.toString().trim();
        while(addSBString.endsWith(",")){
            addSBString = addSBString.substring(0,addSBString.length()-1);
        }

        addIndivitualMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "indivitual",defineSBString,addSBString);
// zzz


    }



    static ArrayList<File> getAllSubFile(File dirFile) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(dirFile.getAbsolutePath());

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path pathFile, BasicFileAttributes attrs) throws IOException {
                    String fileString = pathFile.toAbsolutePath().toString();

                    if(fileString.contains(File.separator+".git"+File.separator) || fileString.contains(".")){
                        return FileVisitResult.CONTINUE;
                    }
                    File file = pathFile.toFile();
                    allFile_miwen.add(file);
                    if (file.isFile()) {
                        allPointFile_miwen.add(file);
                        System.out.println("stet1A: 密文件编号" + File_INDEX + "  FilePath=" + file.getAbsolutePath() + "  文件名:" + file.getName());
                        File_INDEX++;

                        //  包含 /v/ 这样的标识
                        if (file.getAbsolutePath().contains(File.separator + "v" + File.separator)) {
                            mp4FileList_miwen.add(file);
                        } else if (file.getAbsolutePath().contains(File.separator + "i" + File.separator)) {
                            //  包含 /i/ 这样的标识
                            jpgFileList_miwen.add(file);
                        } else if (file.getAbsolutePath().contains(File.separator + "g" + File.separator)) {
                            //  包含 /g/ 这样的标识
                            gifFileList_miwen.add(file);
                        }
                    } else {
                        System.out.println("stet1B:【密文件夹" + DIR_INDEX + "】" + "DirPath=" + file.getAbsolutePath() + "  文件夹名:" + file.getName());
                        DIR_INDEX++;
                        alldir_miwen.add(file);
                        File[] fileList = file.listFiles();
                        for (int i = 0; i < fileList.length; i++) {
                            AnalysisDir(fileList[i]);
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


    //   循环遍历


    public static void AnalysisDir(File file) {
        allFile_miwen.add(file);
        if (file.isFile()) {
            allPointFile_miwen.add(file);
            System.out.println("密文件编号" + File_INDEX + "  FilePath=" + file.getAbsolutePath() + "  文件名:" + file.getName());
            File_INDEX++;
            if (file.getAbsolutePath().contains(File.separator + "v" + File.separator)) {

                mp4FileList_miwen.add(file);
            } else if (file.getAbsolutePath().contains(File.separator + "i" + File.separator)) {

                jpgFileList_miwen.add(file);
            } else if (file.getAbsolutePath().contains(File.separator + "g" + File.separator)) {
                gifFileList_miwen.add(file);
            }

            return;
        } else {
            System.out.println("【密文件夹" + DIR_INDEX + "】" + "DirPath=" + file.getAbsolutePath() + "  文件夹名:" + file.getName());
            DIR_INDEX++;
            alldir_miwen.add(file);
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                AnalysisDir(fileList[i]);
            }
        }

    }

    public static void createEncryFile(File generalFile, File encryptFile) {

        int general_position = 0;
        int general_offset = 0;
        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;


        FileOutputStream encryptileOutputStream = null;
        BufferedOutputStream encryptBufferedOutputStream = null;

        try {
            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);


            encryptileOutputStream = new FileOutputStream(encryptFile);
            encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);

            if (encryptFile.getAbsolutePath().trim().endsWith("md")) {
                while ((general_position = generalBufferedInputStream.read(TEMP, 0, TEMP.length)) != -1) {
                    encryptBufferedOutputStream.write(TEMP, 0, general_position);
                    encryptBufferedOutputStream.flush();
                }
                // 关闭流
                generalBufferedInputStream.close();
                encryptBufferedOutputStream.close();
                return;

            }


            System.out.println("原始文件字节大小:  " + generalBufferedInputStream.available());
            while (general_offset < BYTE_CONTENT_LENGTH) {   // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
                general_position = generalBufferedInputStream.read(TEMP, general_offset, TEMP.length - general_offset);
                if (general_position == -1) {
                    break;
                }
                general_offset += general_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }


            // 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致

            byte[] encrypt_bytes = encrypt(TEMP);

            System.out.println("加密前明文大小:" + TEMP.length + "   加密后密文大小:" + encrypt_bytes.length);

            //加密后的密文 填充   encryptFile文件的头首部
            encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
            encryptBufferedOutputStream.flush();
            // 从正常的 general文件 读取  BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
            while ((general_position = generalBufferedInputStream.read(TEMP, 0, TEMP.length)) != -1) {
                encryptBufferedOutputStream.write(TEMP, 0, general_position);
                encryptBufferedOutputStream.flush();
            }
            // 关闭流
            generalBufferedInputStream.close();
            encryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }


    public static void createEncryFile() {

        int general_position = 0;
        int general_offset = 0;
        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;


        FileOutputStream encryptileOutputStream = null;
        BufferedOutputStream encryptBufferedOutputStream = null;

        try {
            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);


            encryptileOutputStream = new FileOutputStream(encryptFile);
            encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);


            System.out.println("原始文件字节大小:  " + generalBufferedInputStream.available());

            while (general_offset < BYTE_CONTENT_LENGTH) {   // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
                general_position = generalBufferedInputStream.read(TEMP, general_offset, TEMP.length - general_offset);
                if (general_position == -1) {
                    break;
                }
                general_offset += general_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }


            // 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致
            byte[] encrypt_bytes = encrypt(TEMP);
            System.out.println("加密前明文大小:" + TEMP.length + "   加密后密文大小:" + encrypt_bytes.length);

            //加密后的密文 填充   encryptFile文件的头首部
            encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
            encryptBufferedOutputStream.flush();

            // 从正常的 general文件 读取  BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
            while ((general_position = generalBufferedInputStream.read(TEMP, 0, TEMP.length)) != -1) {
                encryptBufferedOutputStream.write(TEMP, 0, general_position);
                encryptBufferedOutputStream.flush();
            }
            // 关闭流
            generalBufferedInputStream.close();
            encryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static void createDecryFile() {


        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;


        try {
            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);


            decryptileOutputStream = new FileOutputStream(decryptFile);
            decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);


            int encrypt_offset = 0;
            int encrypt_position = 0;
            while (encrypt_offset < BYTE_CONTENT_LENGTH) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                encrypt_position = encryptBufferedInputStream.read(TEMP, encrypt_offset, TEMP.length - encrypt_offset);

                if (encrypt_position == -1) {
                    break;
                }
                encrypt_offset += encrypt_position;
                // byteTo16(TEMP, general_position);   // 可以查看 指定 前 general_position 个在 TEMP数组中的字节数据 太多 注释掉
            }

            byte[] decrypt_bytes = decrypt(TEMP);  // 对加密文件的加密字节进行解密
            System.out.println("密文加密字节大小:" + TEMP.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

            decryptBufferedOutputStream.write(decrypt_bytes);
            decryptBufferedOutputStream.flush();


            // 读取 encryptFile加密文件中正常的字节    BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 解密File(Head已经解密)文件中去
            while ((encrypt_offset = encryptBufferedInputStream.read(TEMP, 0, TEMP.length)) != -1) {
                decryptBufferedOutputStream.write(TEMP, 0, encrypt_offset);
                decryptBufferedOutputStream.flush();
            }

            encryptBufferedInputStream.close();
            decryptBufferedOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());

        }
    }

    public static void show1024Byte() {

        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;


        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;


        FileInputStream decryptileInputStream = null;
        BufferedInputStream decryptBufferedInputStream = null;

        try {

            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);


            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);


            decryptileInputStream = new FileInputStream(decryptFile);
            decryptBufferedInputStream = new BufferedInputStream(decryptileInputStream);


            int common_offset = 0;
            int common_position = 0;
            while (common_offset < BYTE_CONTENT_LENGTH) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                common_position = generalBufferedInputStream.read(TEMP, common_offset, TEMP.length - common_offset);

                if (common_position == -1) {
                    break;
                }
                common_offset += common_position;
            }


            System.out.println("\n\n\n");
            System.out.println("GeneralFile的前 1024 个字节的内容如下: ");
            byteTo16(TEMP, 1024);


            common_offset = 0;
            common_position = 0;
            while (common_offset < BYTE_CONTENT_LENGTH) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                common_position = encryptBufferedInputStream.read(TEMP, common_offset, TEMP.length - common_offset);

                if (common_position == -1) {
                    break;
                }
                common_offset += common_position;
            }
            System.out.println("\n\n\n");
            System.out.println("encryptFile 加密文件的前 1024 个字节的内容如下: ");
            byteTo16(TEMP, 1024);


            common_offset = 0;
            common_position = 0;
            while (common_offset < BYTE_CONTENT_LENGTH) {    // 读取到加密文件的  加密字节部分 大小为 BYTE_CONTENT_LENGTH
                common_position = decryptBufferedInputStream.read(TEMP, common_offset, TEMP.length - common_offset);

                if (common_position == -1) {
                    break;
                }
                common_offset += common_position;
            }
            System.out.println("\n\n\n");
            System.out.println("decryptFile 解密文件的前 1024 个字节的内容如下: ");
            byteTo16(TEMP, 1024);


            generalBufferedInputStream.close();
            encryptBufferedInputStream.close();
            decryptBufferedInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String byteTo16(byte bt) {
        String[] strHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String resStr = "";
        int low = (bt & 15);
        int high = bt >> 4 & 15;
        resStr = strHex[high] + strHex[low];
        return resStr;
    }

    public static String byteTo16(byte[] btArr) {
        String resStr = "";
        int index = 0;
        for (byte bt : btArr) {

            String[] strHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
            int low = (bt & 15);
            int high = bt >> 4 & 15;
            resStr = strHex[high] + strHex[low];

            if (index % 16 == 0) {

                System.out.println();
                //int numLine = index/16;
                String pre = "";
                if (index < 10) {
                    pre = "0000000000" + index;
                } else if (index < 100) {
                    pre = "000000000" + index;
                } else if (index < 1000) {
                    pre = "00000000" + index;
                } else if (index < 10000) {
                    pre = "0000000" + index;
                } else if (index < 100000) {
                    pre = "000000" + index;
                } else if (index < 1000000) {
                    pre = "00000" + index;
                } else if (index < 10000000) {
                    pre = "0000" + index;
                } else if (index < 100000000) {
                    pre = "000" + index;
                } else if (index < 1000000000) {
                    pre = "00" + index;
                }
                System.out.print(pre + "字节---" + toRightHexString(Integer.toHexString(index)) + "h :");
            }
            System.out.print(resStr + " ");
            index++;

        }


        return resStr;
    }

    public static String byteTo16(byte[] btArr, int position) {
        String resStr = "";
        int index = 0;
        for (byte bt : btArr) {

            String[] strHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
            int low = (bt & 15);
            int high = bt >> 4 & 15;
            resStr = strHex[high] + strHex[low];

            if (index % 16 == 0) {

                System.out.println();
                //int numLine = index/16;
                String pre = "";
                if (index < 10) {
                    pre = "0000000000" + index;
                } else if (index < 100) {
                    pre = "000000000" + index;
                } else if (index < 1000) {
                    pre = "00000000" + index;
                } else if (index < 10000) {
                    pre = "0000000" + index;
                } else if (index < 100000) {
                    pre = "000000" + index;
                } else if (index < 1000000) {
                    pre = "00000" + index;
                } else if (index < 10000000) {
                    pre = "0000" + index;
                } else if (index < 100000000) {
                    pre = "000" + index;
                } else if (index < 1000000000) {
                    pre = "00" + index;
                }
                System.out.print(pre + "字节---" + toRightHexString(Integer.toHexString(index)) + "h :");
            }
            System.out.print(resStr + " ");
            index++;
            if (index == position) {
                break;
            }

        }


        return resStr;
    }

    public static String toRightHexString(String hexStr) {  // 以 00000 -- 99999 格式输出 字节  00000字节 --- 01008字节
        if (hexStr.length() == 1) {
            hexStr = "000000000" + hexStr;
        } else if (hexStr.length() == 2) {
            hexStr = "00000000" + hexStr;
        } else if (hexStr.length() == 3) {
            hexStr = "0000000" + hexStr;
        } else if (hexStr.length() == 4) {
            hexStr = "000000" + hexStr;
        } else if (hexStr.length() == 5) {
            hexStr = "00000" + hexStr;
        } else if (hexStr.length() == 6) {
            hexStr = "0000" + hexStr;
        } else if (hexStr.length() == 7) {
            hexStr = "000" + hexStr;
        } else if (hexStr.length() == 8) {
            hexStr = "00" + hexStr;
        } else if (hexStr.length() == 9) {
            hexStr = "0" + hexStr;
        }

        return hexStr;
    }


    static void writeContentToFile(File curFile, String classContent) {
        try {
            BufferedWriter strBuffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(curFile), "utf-8"));
            strBuffWriter.write(classContent);
            strBuffWriter.flush();
            strBuffWriter.close();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }

    }

    static String readStringFromFile(File fileItem) {
        if (fileItem == null || !fileItem.exists()) {

            System.out.println("当前读取的文件不存在!");
            return null;
        } else {
            System.out.println("当前读取的文件 = " + fileItem.getAbsolutePath());

        }
        StringBuilder sb = new StringBuilder();

        try {

            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String oldOneLine = "";
            String newOneLine = "";
            int indexLine = 0;

            while (oldOneLine != null) {
                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }
                sb.append(oldOneLine + "\n");
                indexLine++;
            }
            curBR.close();
        } catch (Exception e) {

            System.out.println("Exception e = " + e.toString());
        }
        return sb.toString();
    }

}