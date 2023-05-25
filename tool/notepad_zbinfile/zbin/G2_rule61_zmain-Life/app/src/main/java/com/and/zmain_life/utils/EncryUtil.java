package com.and.zmain_life.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;

public class EncryUtil {


    static int BYTE_CONTENT_LENGTH_Rule7= 1024 * 10 * 10;   // 读取文件Head字节数常数
    public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];
    static String strDefaultKey_Rule7 = "zukgit12"; //  8-length


    private static Cipher decryptCipher = null;

    static {
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strDefaultKey_Rule7.getBytes());
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

    //密解字节数组
    public static byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }


    public static String getMD5Name(File pathFile) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = pathFile;
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }

    public static String getMD5Name(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }


    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static File createDecryByteData(File encryptFile) {


        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;
//        File decryptFile = new File(encryptFile.getParentFile().getAbsolutePath()+File.separator+getTimeStamp()+"_"+encryptFile.getName());

        File decryptFile = null;
        String md5Name = "";
        File md5File = null;
        try {
//            if(!decryptFile.exists()){
//                decryptFile.creals
//                teNewFile();
//            }

             md5Name =  getMD5Name(encryptFile);
            decryptFile = File.createTempFile(md5Name,"");

            md5File = new File(decryptFile.getParentFile().getAbsolutePath()+File.separator+md5Name);
            android.util.Log.i("zukgit","0000 encryptFile.path = "+encryptFile.getAbsolutePath() + " md5 = "+ md5Name +"  md5File="+md5File.exists() +" md5Path="+md5File.getAbsolutePath()  +"  decryptFile="+decryptFile.getAbsolutePath() +" ");

            if(md5File.exists()){
                decryptFile.delete();
                return md5File;
            }


           boolean  rename_flag = decryptFile.renameTo(md5File);

            android.util.Log.i("zukgit","AAAA encryptFile.path = "+encryptFile.getAbsolutePath() + " md5 = "+ md5Name +"  md5File="+md5File.exists() +" md5Path="+md5File.getAbsolutePath()  +"  decryptFile="+decryptFile.getAbsolutePath() +" rename_flag="+ rename_flag);

            if(rename_flag){
                decryptFile.delete();
                decryptFile = md5File;
            }


            android.util.Log.i("zukgit","BBBB encryptFile.path = "+encryptFile.getAbsolutePath() + " md5 = "+ md5Name +"  md5File="+md5File.exists() +" md5Path="+md5File.getAbsolutePath()  +"  decryptFile="+decryptFile.getAbsolutePath() +" rename_flag="+ rename_flag);

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
           e.fillInStackTrace();
            e.printStackTrace();
            android.util.Log.i("zukgit","createDecryByteData  Failed ! ");

        }


        return decryptFile;


    }


    static String getTimeStamp(){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }




    // 读取加密文件  对加密部分进行解密 然后生成解密之后的文件 decryptFile
    public static void  createDecryFile(File encryptFile,File decryptFile) {


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


    public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
