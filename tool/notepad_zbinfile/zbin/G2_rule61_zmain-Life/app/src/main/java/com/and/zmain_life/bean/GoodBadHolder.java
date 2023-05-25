package com.and.zmain_life.bean;

import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.node.StockNodeShareInfo;
import com.and.zmain_life.stock_node.Stock_Node;
import com.and.zmain_life.stock_node.Stock_NodeImpl;
import com.and.zmain_life.stock_sheet_bean.DaZongJiaoYi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongMeiRiChengJiaoTongJi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.GeGuZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.GuDongZengJianChi;
import com.and.zmain_life.stock_sheet_bean.GuPiaoHuiGou;
import com.and.zmain_life.stock_sheet_bean.HuShenGangGuTongChiGuMingXi;
import com.and.zmain_life.stock_sheet_bean.HuShenGangTongZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.HuShenGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.LongHuBangJiGouMingXi;
import com.and.zmain_life.stock_sheet_bean.LongHuBangMeiRiMingXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiTingFuPaiXinXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhangDieTingJiaGe;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhiBiao;
import com.and.zmain_life.stock_sheet_bean.RiXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiHuiZong;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiMingXi;
import com.and.zmain_life.stock_sheet_bean.XianShouGuJieJin;
import com.and.zmain_life.stock_sheet_bean.XinGuLieBiaoIPO;
import com.and.zmain_life.stock_sheet_bean.YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi;
import com.and.zmain_life.stock_sheet_bean.ZhouXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.ZuiJinYueZhangDieTingTongJi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.security.Key;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;


public class GoodBadHolder {
    static int BYTE_CONTENT_LENGTH_Rule7 = 1024 * 10 * 10; // 读取文件Head字节数常数

    public static byte[] TEMP_Rule7 = new byte[BYTE_CONTENT_LENGTH_Rule7];
    public static String strDefaultKey_Rule7 = "zukgit12"; // 8-length



    public static Cipher encryptCipher = null;
    public static Cipher decryptCipher = null;

    public  static  void init(){
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
        byte[] arrB = new byte[8]; // 认默为0
        for (int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密匙
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }

    // 加密字节数组
    public static byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    // 密解字节数组
    public static byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }




    // 读取加密文件 对加密部分进行解密 然后生成解密之后的文件 decryptFile
    //  加密
    public static void create_GoodDecry_File(File encryptFile, File decryptFile) {

        FileOutputStream decryptileOutputStream = null;
        BufferedOutputStream decryptBufferedOutputStream = null;

        FileInputStream encryptileInputStream = null;
        BufferedInputStream encryptBufferedInputStream = null;

        try {
            if (!decryptFile.getParentFile().exists()) {
                decryptFile.getParentFile().mkdirs();
            }

            if (!decryptFile.exists()) {
                decryptFile.createNewFile();
            }
            encryptileInputStream = new FileInputStream(encryptFile);
            encryptBufferedInputStream = new BufferedInputStream(encryptileInputStream);

            decryptileOutputStream = new FileOutputStream(decryptFile);
            decryptBufferedOutputStream = new BufferedOutputStream(decryptileOutputStream);

            int encrypt_offset = 0;
            int encrypt_position = 0;
            while (encrypt_offset < BYTE_CONTENT_LENGTH_Rule7) { // 读取到加密文件的 加密字节部分 大小为 BYTE_CONTENT_LENGTH
                encrypt_position = encryptBufferedInputStream.read(TEMP_Rule7, encrypt_offset,
                        TEMP_Rule7.length - encrypt_offset);

                if (encrypt_position == -1) {
                    break;
                }
                encrypt_offset += encrypt_position;
                // byteTo16(TEMP, general_position); // 可以查看 指定 前 general_position 个在
                // TEMP数组中的字节数据 太多 注释掉
            }

            byte[] decrypt_bytes = decrypt(TEMP_Rule7); // 对加密文件的加密字节进行解密
            System.out.println("源文件:" + encryptFile.getAbsolutePath() + "    解密文件:" + decryptFile.getAbsolutePath()
                    + "  密文加密字节大小:" + TEMP_Rule7.length + "   解密密文之后的明文大小:" + decrypt_bytes.length);

            decryptBufferedOutputStream.write(decrypt_bytes);
            decryptBufferedOutputStream.flush();

            // 读取 encryptFile加密文件中正常的字节 BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到
            // 解密File(Head已经解密)文件中去
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



//  加密
    public static void create_BadEncry_File(File generalFile, File encryptFile) {

        int general_position = 0;
        int general_offset = 0;
        FileInputStream generalFileInputStream = null;
        BufferedInputStream generalBufferedInputStream = null;

        FileOutputStream encryptileOutputStream = null;
        BufferedOutputStream encryptBufferedOutputStream = null;

        try {
            if (!encryptFile.exists()) {
                encryptFile.createNewFile();
            }
            generalFileInputStream = new FileInputStream(generalFile);
            generalBufferedInputStream = new BufferedInputStream(generalFileInputStream);

            encryptileOutputStream = new FileOutputStream(encryptFile);
            encryptBufferedOutputStream = new BufferedOutputStream(encryptileOutputStream);

            if (encryptFile.getAbsolutePath().trim().endsWith("md")) {
                while ((general_position = generalBufferedInputStream.read(TEMP_Rule7, 0, TEMP_Rule7.length)) != -1) {
                    encryptBufferedOutputStream.write(TEMP_Rule7, 0, general_position);
                    encryptBufferedOutputStream.flush();
                }
                // 关闭流
                generalBufferedInputStream.close();
                encryptBufferedOutputStream.close();
                return;

            }

            // System.out.println("原始文件字节大小: " + generalBufferedInputStream.available());
            while (general_offset < BYTE_CONTENT_LENGTH_Rule7) { // 读取原始文件的头 BYTE_CONTENT_LENGTH 个字节数进行加密
                general_position = generalBufferedInputStream.read(TEMP_Rule7, general_offset,
                        TEMP_Rule7.length - general_offset);
                if (general_position == -1) {
                    break;
                }
                general_offset += general_position;
                // byteTo16(TEMP, general_position); // 可以查看 指定 前 general_position 个在
                // TEMP数组中的字节数据 太多 注释掉
            }

            // 对读取到的TEMP字节数组 BYTE_CONTENT_LENGTH 个字节进行 ECB模式加密 明文大小与密文大小一致

            byte[] encrypt_bytes = encrypt(TEMP_Rule7);

            System.out.println("加密原始文件:" + generalFile.getName() + "  加密前明文大小:" + TEMP_Rule7.length + "   加密后密文大小:"
                    + encrypt_bytes.length);

            // 加密后的密文 填充 encryptFile文件的头首部
            encryptBufferedOutputStream.write(encrypt_bytes, 0, encrypt_bytes.length);
            encryptBufferedOutputStream.flush();
            // 从正常的 general文件 读取 BYTE_CONTENT_LENGTH 字节数之后的所有字节写入到 加密File(Head已经加密)文件中去
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


}
