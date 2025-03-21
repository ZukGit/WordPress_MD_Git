import java.io.*;
import java.security.Key;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
public class C0_YAMA {

    private static String strDefaultKey = "zukgit12"; //    加密原始秘钥字符串

    private static String mingwenDir = File.separator+"mingwen"; //
    private static String miwenDir = File.separator+"miwen"; //
    /**
     * ECB 作为DES加密 操作模式的一种   明文与密文长度一致  但必须保证8字节长度整数倍的明文
     * CBC 作为DES加密 操作模式的一种   明文与密文长度不一致  明文长度不需要保证一定是 8字节整数倍 会多出一个 IV block padding
     */
    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;
    static int BYTE_CONTENT_LENGTH = 1024 * 10 * 10;   // 读取文件Head字节数常数

    static int File_INDEX = 1;
    static int DIR_INDEX = 1;
    public static ArrayList<File> jpgFileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> mp4FileList_mingwen = new ArrayList<File>();
    public static ArrayList<File> gifFileList_mingwen = new ArrayList<File>();

    public static ArrayList<File> allFile_mingwen = new ArrayList<File>();
    public static ArrayList<File> alldir_mingwen = new ArrayList<File>();
    public static ArrayList<File> allPointFile_mingwen = new ArrayList<File>();


    public static ArrayList<File> alldir_miwen = new ArrayList<File>();
    public static ArrayList<File> allPointFile_miwen = new ArrayList<File>();


    public static Map<File,File> mingwen_minwen_Map = new HashMap<File, File>();

    public static void main(String[] args) {
        if (isFileReady()) {
//            createEncryFile();   // 创建加密的文件
//            createDecryFile();  // 创建 解析 解密的文件之后的 解密的文件
//            show1024Byte();

        }

    }

    // 检查对应的明文文件夹是存在
    public static boolean isFileReady() {   // 初始化静态变量 File1 原始mp3文件  File2-加密mp3文件   File3-解密mp3文件
        String usrDir = System.getProperties().getProperty("user.dir");
        File mingwenFileDir = new File(usrDir+mingwenDir);
        File miwenFileDir = new File(usrDir+miwenDir);
        if(! mingwenFileDir.exists()){
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
        AnalysisDir(mingwenFileDir);   // 1. 列出所有文件夹 文件 路径 名称
        InitMiweList();     // 解析 明文_明文的关系
        todoYAMA();
        return true;
    }

    public static void  todoYAMA() {
        Map.Entry<File , File> entryFile;

        if(mingwen_minwen_Map != null && mingwen_minwen_Map.size() > 0){
            Iterator iterator = mingwen_minwen_Map.entrySet().iterator();


            while( iterator.hasNext() ){
                entryFile = (Map.Entry<File , File>) iterator.next();
                File mingweFile =  entryFile.getKey(); //Map的Key
                File miwenFile =  entryFile.getValue();  //Map的Value
                createEncryFile(mingweFile,miwenFile);
            }
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



//    public static ArrayList<File> jpgFileList_mingwen = new ArrayList<File>();
//    public static ArrayList<File> mp4FileList_mingwen = new ArrayList<File>();
//    public static ArrayList<File> allFile_mingwen = new ArrayList<File>();
//    public static ArrayList<File> alldir_mingwen = new ArrayList<File>();
//    public static ArrayList<File> allPointFile_mingwen = new ArrayList<File>();


    public static void  InitMiweList() {

        System.out.println("文件夹+文件 总数 =  " + allFile_mingwen.size());
        System.out.println("文件夹 总数 =  " + alldir_mingwen.size());
        System.out.println("文件 总数 =  " + allPointFile_mingwen.size());

        System.out.println("=======明文文件夹列表 Begin =======" );
        for (int i = 0; i < alldir_mingwen.size(); i++) {
            System.out.println("明文件夹" + (i+1)+"  路径: "+ alldir_mingwen.get(i).getAbsolutePath());
            String mingwenDir = alldir_mingwen.get(i).getAbsolutePath();
            String miwenDir = mingwenDir.replace("mingwen","miwen");
            File miwen_dirFile = new File(miwenDir);
            if(!miwen_dirFile.exists()){
                miwen_dirFile.mkdirs();
            }
            alldir_miwen.add(miwen_dirFile);

        }
        System.out.println("=======明文文件夹列表 End =======" );
        System.out.println("=======密文件夹列表 Begin =======" );
        for (int i = 0; i < alldir_miwen.size(); i++) {
            System.out.println("密文件夹" + (i+1)+"  路径: "+ alldir_miwen.get(i).getAbsolutePath());
        }
        System.out.println("=======密文文件夹列表 End =======" );



        System.out.println("=======文件列表 Begin  文件总数:"+ allPointFile_mingwen.size()+" =======" );

        System.out.println("*********** jpg文件列表Begin*********** " );
        for (int i = 0; i < jpgFileList_mingwen.size(); i++) {
            System.out.println("mp4文件 index:" +(i+1)+"   路径: "+ jpgFileList_mingwen.get(i).getAbsolutePath());
        }
        System.out.println("*********** jpg文件列表End*********** " );


        System.out.println("*********** mp4文件列表Begin*********** " );
        for (int i = 0; i < mp4FileList_mingwen.size(); i++) {
            System.out.println("mp4文件 index:" + (i+1)+"   路径: "+ mp4FileList_mingwen.get(i).getAbsolutePath());
        }
        System.out.println("*********** mp4文件列表End*********** " );


        System.out.println("*********** gif文件列表Begin*********** " );
        for (int i = 0; i < gifFileList_mingwen.size(); i++) {
            System.out.println("gif文件 index:" + (i+1)+"   路径: "+ gifFileList_mingwen.get(i).getAbsolutePath());
        }
        System.out.println("*********** gif文件列表End*********** " );


        System.out.println("=======文件列表 End  文件总数:"+ allPointFile_mingwen.size()+" =======" );


        for (int i = 0; i < allPointFile_mingwen.size(); i++) {
            String mingwenPointFile = allPointFile_mingwen.get(i).getAbsolutePath();
            String miwenPointFile_xxx = mingwenPointFile.replace("mingwen","miwen");
            String miwenPointFile = "";
            if(! miwenPointFile_xxx.trim().endsWith("md")){
                miwenPointFile = miwenPointFile_xxx.trim().substring(0,miwenPointFile_xxx.length()-4);
            } else{
                miwenPointFile = miwenPointFile_xxx;
            }

            File miwenFile =  new File(miwenPointFile);
            if(! miwenFile.exists()){
                try {
                    miwenFile.createNewFile();
                    System.out.println("密文文件 index:"+i+"    path:"+ miwenFile.getAbsolutePath());
                } catch (IOException e){
                    System.out.println("密文创建失败！");
                }
            }
            allPointFile_miwen.add(miwenFile);
            mingwen_minwen_Map.put(allPointFile_mingwen.get(i),miwenFile);
        }
    }



    public static void  AnalysisDir(File file) {
        allFile_mingwen.add(file);
        if(file.isFile()){
            allPointFile_mingwen.add(file);
            System.out.println("文件编号"+File_INDEX+"  FilePath="+file.getAbsolutePath() +"  文件名:"+file.getName());
            File_INDEX++;
            if(file.getAbsolutePath().contains(File.separator+"v"+File.separator)){

                mp4FileList_mingwen.add(file);
            } else if(file.getAbsolutePath().contains(File.separator+"g"+File.separator)){
                gifFileList_mingwen.add(file);
            } else if(file.getAbsolutePath().contains(File.separator+"i"+File.separator)){

                jpgFileList_mingwen.add(file);
            }

            return ;
        } else{
            System.out.println("【文件夹"+DIR_INDEX+"】"+"DirPath="+file.getAbsolutePath() +"  文件夹名:"+file.getName());
            DIR_INDEX++;
            alldir_mingwen.add(file);
            File[] fileList =  file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                AnalysisDir(fileList[i]);
            }
        }

    }

    public static void createEncryFile(File generalFile , File encryptFile) {

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

            if(encryptFile.getAbsolutePath().trim().endsWith("md")){
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

            byte[]    encrypt_bytes = encrypt(TEMP);

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

    public static void show1024Byte(){

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
            byteTo16(TEMP,1024);



            common_offset = 0 ;
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
            byteTo16(TEMP,1024);


            common_offset = 0 ;
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
            byteTo16(TEMP,1024);



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


}