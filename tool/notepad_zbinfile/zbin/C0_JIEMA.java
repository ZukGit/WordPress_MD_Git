import java.io.*;
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


    static File htmlFile_gif_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_2x2.html");
    static File htmlFile_gif_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x3.html");
    static File htmlFile_gif_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif_3x5.html");
    static File htmlFile_gif__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_gif__3d.html");
    static File htmlFile_mp4_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_2x2.html");
    static File htmlFile_mp4_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x3.html");
    static File htmlFile_mp4_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4_3x5.html");
    static File htmlFile_mp4__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_mp4__3d.html");
    static File htmlFile_jpg_2x2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_2x2.html");
    static File htmlFile_jpg_3x3 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x3.html");
    static File htmlFile_jpg_3x5 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg_3x5.html");
    static File htmlFile_jpg__3d = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "C0_jpg__3d.html");


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
    public static ArrayList<File> mp4_0017ssyyx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0018lzllx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0019sdfxx_FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4_0020tymyx_FileList_mingwen = new ArrayList<File>();
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
        arrFileMap.put("0017ssyyx", mp4_0017ssyyx_FileList_mingwen);
        arrFileMap.put("0018lzllx", mp4_0018lzllx_FileList_mingwen);
        arrFileMap.put("0019sdfxx", mp4_0019sdfxx_FileList_mingwen);
        arrFileMap.put("0020tymyx", mp4_0020tymyx_FileList_mingwen);

    }

    //-----------------indivisual-------------------

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
            String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "indivitual"+  File.separator +keyName+ File.separator + "mp4";
      //      String allPath = System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "wm" + File.separator + "mp4";
            File allFileDir = new File(allPath);

            if (!allFileDir.exists()) {
                allFileDir.mkdirs();
            }

            ArrayList<File> itemFileArr = arrFileMap.get(keyName);
            if(itemFileArr == null){
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

            addMP4HtmlFile(System.getProperties().getProperty("user.dir") + File.separator + mingwenDir + File.separator + "v" + File.separator + "indivitual"+  File.separator +keyName, itemFileArr.size());
        }



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
    }


    public static void addGIFHtmlFile(String addPath, int length) {
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

    // 检查对应的明文文件夹是存在
    public static boolean isFileReady() {   // 初始化静态变量 File1 原始mp3文件  File2-加密mp3文件   File3-解密mp3文件
        String usrDir = System.getProperties().getProperty("user.dir");
        File mingwenFileDir = new File(usrDir + mingwenDir);
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
        AnalysisDir(miwenFileDir);   // 1. 列出所有文件夹 文件 路径 名称
        InitMingweList();     // 解析 明文_明文的关系
        todoJIEMA();
        return true;
    }


    public static void todoJIEMA() {
        Map.Entry<File, File> entryFile;

        if (mingwen_miwen_Map != null && mingwen_miwen_Map.size() > 0) {
            Iterator iterator = mingwen_miwen_Map.entrySet().iterator();


            while (iterator.hasNext()) {
                entryFile = (Map.Entry<File, File>) iterator.next();
                File miwenFile = entryFile.getKey(); //Map的Key
                File mingwenFile = entryFile.getValue();  //Map的Value
                createDecryFile(miwenFile, mingwenFile);
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

        System.out.println("密文件夹+文件 总数 =  " + allFile_miwen.size());
        System.out.println("密文件夹 总数 =  " + alldir_miwen.size());
        System.out.println("密文件 总数 =  " + allPointFile_miwen.size());

        System.out.println("=======密文文件夹列表 Begin =======");
        for (int i = 0; i < alldir_miwen.size(); i++) {
            System.out.println("密文件夹" + (i + 1) + "  路径: " + alldir_miwen.get(i).getAbsolutePath());
            String miwenDir = alldir_miwen.get(i).getAbsolutePath();
            String mingwenDir = miwenDir.replace("miwen", "mingwen");
            File mingwen_dirFile = new File(mingwenDir);
            if (!mingwen_dirFile.exists()) {
                mingwen_dirFile.mkdirs();
            }
            alldir_mingwen.add(mingwen_dirFile);

        }
        System.out.println("=======密文文件夹列表 End =======");
        System.out.println("=======明文件夹列表 Begin =======");
        for (int i = 0; i < alldir_mingwen.size(); i++) {
            System.out.println("明文件夹" + (i + 1) + "  路径: " + alldir_mingwen.get(i).getAbsolutePath());
        }
        System.out.println("=======明文文件夹列表 End =======");


        System.out.println("=======密文件列表 Begin  文件总数:" + allPointFile_miwen.size() + " =======");

        System.out.println("*********** jpg文件列表Begin*********** ");
        for (int i = 0; i < jpgFileList_miwen.size(); i++) {
            System.out.println("密jpg文件 index:" + (i + 1) + "   路径: " + jpgFileList_miwen.get(i).getAbsolutePath());
            String miwen_jpgFile = jpgFileList_miwen.get(i).getAbsolutePath();
            String mingwenPointFile = miwen_jpgFile.replace("miwen", "mingwen");
            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".jpg";

            File mingwenFile = new File(mingwenPointFile_xxx);
            if (!mingwenFile.exists()) {
                try {
                    mingwenFile.createNewFile();
                    System.out.println("明文jpg文件 index:" + i + "    path:" + mingwenFile.getAbsolutePath());
                    jpgFileList_mingwen.add(mingwenFile);
                } catch (IOException e) {
                    System.out.println("明文 jpg创建失败！");
                }
            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(jpgFileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** jpg文件列表End*********** ");


        System.out.println("*********** mp4文件列表Begin*********** ");
        for (int i = 0; i < mp4FileList_miwen.size(); i++) {
            System.out.println("密mp4文件 index:" + (i + 1) + "   路径: " + mp4FileList_miwen.get(i).getAbsolutePath());
            String miwen_mp4File = mp4FileList_miwen.get(i).getAbsolutePath();
            String mingwenPointFile = miwen_mp4File.replace("miwen", "mingwen");
            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".mp4";

            File mingwenFile = new File(mingwenPointFile_xxx);
            if (!mingwenFile.exists()) {
                try {
                    mingwenFile.createNewFile();
                    System.out.println("明文mp4文件 index:" + i + "    path:" + mingwenFile.getAbsolutePath());
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
            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(mp4FileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** mp4文件列表End*********** ");


        System.out.println("*********** gif文件列表Begin*********** ");
        for (int i = 0; i < gifFileList_miwen.size(); i++) {
            System.out.println("密gif文件 index:" + (i + 1) + "   路径: " + gifFileList_miwen.get(i).getAbsolutePath());
            String miwen_gifFile = gifFileList_miwen.get(i).getAbsolutePath();
            String mingwenPointFile = miwen_gifFile.replace("miwen", "mingwen");
            String mingwenPointFile_xxx = mingwenPointFile.trim() + ".gif";

            File mingwenFile = new File(mingwenPointFile_xxx);
            if (!mingwenFile.exists()) {
                try {
                    mingwenFile.createNewFile();
                    System.out.println("明文gif文件 index:" + i + "    path:" + mingwenFile.getAbsolutePath());
                    gifFileList_mingwen.add(mingwenFile);
                } catch (IOException e) {
                    System.out.println("明文 gif 创建失败！");
                }
            }
            allPointFile_mingwen.add(mingwenFile);
            mingwen_miwen_Map.put(gifFileList_miwen.get(i), mingwenFile);

        }
        System.out.println("*********** gif文件列表End*********** ");


        System.out.println("=======文件列表 End  文件总数:" + allPointFile_miwen.size() + " =======");


        todoIndivitualFile(mp4FileList_mingwen);
    }


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