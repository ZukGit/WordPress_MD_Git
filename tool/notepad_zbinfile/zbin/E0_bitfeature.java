import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class E0_bitfeature {

/** 1【0000001】0000 0000 0000 0000 0000 0000 0001 */ // Basic infrastructure mode  【 WIFI_FEATURE_INFRA 】 **/
/** 2【0000002】0000 0000 0000 0000 0000 0000 0010 */ // Support for 5 GHz Band    【 WIFI_FEATURE_INFRA_5G 】 **/
/** 3【0000004】0000 0000 0000 0000 0000 0000 0100 */ // WIFI_FEATURE_PASSPOINT   【 Support for GAS/ANQP  PASSPOINT 】 **/
/** 4【0000008】0000 0000 0000 0000 0000 0000 1000 */ // Support for 5 GHz Band    【 WIFI_FEATURE_INFRA_5G 】 **/

/** 5【0000010】0000 0000 0000 0000 0000 0001 0000 */ // WIFI_FEATURE_MOBILE_HOTSPOT    【 Soft AP 】 **/
/** 6【0000020】0000 0000 0000 0000 0000 0010 0000 */ // WIFI_FEATURE_SCANNER     【 WifiScanner APIs 】 **/
/** 7【0000040】0000 0000 0000 0000 0000 0100 0000 */ // WIFI_FEATURE_AWARE       【 Wi-Fi AWare networking】 **/
/** 8【0000080】0000 0000 0000 0000 0000 1000 0000 */ // WIFI_FEATURE_D2D_RTT     【 Device-to-device RTT 】 **/

/** 9【0000100】0000 0000 0000 0000 0001 0000 0000 */ // WIFI_FEATURE_D2AP_RTT    【 Device-to-AP RTT 】 **/
/**10【0000200】0000 0000 0000 0000 0010 0000 0000 */ // WIFI_FEATURE_BATCH_SCAN  【Batched Scan (deprecated) 】 **/
/**11【0000400】0000 0000 0000 0000 0100 0000 0000 */ // WIFI_FEATURE_PNO         【 Preferred network offload】 **/
/**12【0000800】0000 0000 0000 0000 1000 0000 0000 */ // WIFI_FEATURE_ADDITIONAL_STA 【 Support for two STAs 】 **/

/**13【0001000】0000 0000 0000 0001 0000 0000 0000 */ // WIFI_FEATURE_TDLS       【Tunnel directed link setup 】 **/
/**14【0002000】0000 0000 0000 0010 0000 0000 0000 */ // WIFI_FEATURE_EPR       【 Enhanced power reporting】 **/
/**15【0004000】0000 0000 0000 0100 0000 0000 0000 */ // WIFI_FEATURE_SCANNER     【 WifiScanner APIs 】 **/
/**16【0008000】0000 0000 0000 1000 0000 0000 0000 */ // WIFI_FEATURE_AP_STA     【 AP STA Concurrency 】 **/

/**17【0010000】0000 0000 0001 0000 0000 0000 0000 */ // WIFI_FEATURE_LINK_LAYER_STATS  【  Link layer stats collection】 **/
/**18【0020000】0000 0000 0010 0000 0000 0000 0000 */ // WIFI_FEATURE_LOGGER  【  WiFi Logger 】 **/
/**19【0040000】0000 0000 0100 0000 0000 0000 0000 */ // WIFI_FEATURE_HAL_EPNO  【  Enhanced PNO 】 **/
/**20【0080000】0000 0000 1000 0000 0000 0000 0000 */ // WIFI_FEATURE_RSSI_MONITOR  【 RSSI Monitor】 **/
/**21【0100000】0000 0001 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_MKEEP_ALIVE  【  mkeep_alive 】 **/
/**22【0200000】0000 0010 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_CONFIG_NDO  【 ND offload】 **/
/**23【0400000】0000 0100 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_TRANSMIT_POWER  【 Capture transmit power 】 **/
/**24【0800000】0000 1000 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_CONTROL_ROAMING  【  Control firmware roaming】 **/
/**25【1000000】0001 0000 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_IE_WHITELIST  【 Probe IE white listing】 **/
/**26【2000000】0010 0000 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_SCAN_RAND  【 Random MAC & Probe seq】 **/
/**27【4000000】0100 0000 0000 0000 0000 0000 0000 */ // WIFI_FEATURE_TX_POWER_LIMIT  【  Set Tx power limit】 **/
/**28【8000000】1000 0000 0000 0000 0000 0000 0000 */ // UNKNOW UNDEFINE  【  UNDEFINE 】 **/
/**........**/
    /**
     * 64【8000000】1000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000
     */ // UNKNOW UNDEFINE  【  UNDEFINE 】 **/


    static ArrayList<LongBitModel> wifiLongBitFeatureList = new ArrayList<LongBitModel>();

    static {

        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000001L, 0, "WIFI_FEATURE_INFRA", ""));  // 1组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000002L, 1, "WIFI_FEATURE_INFRA_5G", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000004L, 2, "WIFI_FEATURE_PASSPOINT", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000008L, 3, "WIFI_FEATURE_INFRA_5G", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000010L, 4, "WIFI_FEATURE_MOBILE_HOTSPOT", ""));  // 2组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000020L, 5, "WIFI_FEATURE_SCANNER", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000040L, 6, "WIFI_FEATURE_AWARE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000080L, 7, "WIFI_FEATURE_D2D_RTT", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000100L, 8, "WIFI_FEATURE_D2AP_RTT", ""));  // 3组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000200L, 9, "WIFI_FEATURE_BATCH_SCAN", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000400L, 10, "WIFI_FEATURE_PNO", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000000800L, 11, "WIFI_FEATURE_ADDITIONAL_STA", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000001000L, 12, "WIFI_FEATURE_TDLS", ""));  // 4组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000002000L, 13, "WIFI_FEATURE_EPR", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000004000L, 14, "WIFI_FEATURE_SCANNER", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000008000L, 15, "WIFI_FEATURE_AP_STA", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000010000L, 16, "WIFI_FEATURE_LINK_LAYER_STATS", ""));  // 5组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000020000L, 17, "WIFI_FEATURE_LOGGER", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000040000L, 18, "WIFI_FEATURE_HAL_EPNO", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000080000L, 19, "WIFI_FEATURE_RSSI_MONITOR", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000100000L, 20, "WIFI_FEATURE_MKEEP_ALIVE", ""));  // 6组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000200000L, 21, "WIFI_FEATURE_CONFIG_NDO", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000400000L, 22, "WIFI_FEATURE_TRANSMIT_POWER", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000000800000L, 23, "WIFI_FEATURE_CONTROL_ROAMING", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000001000000L, 24, "WIFI_FEATURE_IE_WHITELIST", "")); // 7组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000002000000L, 25, "WIFI_FEATURE_SCAN_RAND", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000004000000L, 26, "WIFI_FEATURE_TX_POWER_LIMIT", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000008000000L, 27, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000010000000L, 28, "UNDEFINE", "")); // 8 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000020000000L, 29, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000040000000L, 30, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000080000000L, 31, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000100000000L, 32, "UNDEFINE", ""));// 9 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000200000000L, 33, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000400000000L, 34, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000000800000000L, 35, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000001000000000L, 36, "UNDEFINE", "")); // 10 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000002000000000L, 37, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000004000000000L, 38, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000008000000000L, 39, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000010000000000L, 40, "UNDEFINE", ""));// 11 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000020000000000L, 41, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000040000000000L, 42, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000080000000000L, 43, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000100000000000L, 44, "UNDEFINE", ""));  // 12 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0000200000000000L, 45, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000400000000000L, 46, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0000800000000000L, 47, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0001000000000000L, 48, "UNDEFINE", ""));// 13 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0002000000000000L, 49, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0004000000000000L, 50, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0008000000000000L, 51, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0010000000000000L, 52, "UNDEFINE", "")); // 14 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0020000000000000L, 53, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0040000000000000L, 54, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0080000000000000L, 55, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0100000000000000L, 56, "UNDEFINE", "")); // 15 组
        wifiLongBitFeatureList.add(new LongBitModel(0x0200000000000000L, 57, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0400000000000000L, 58, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x0800000000000000L, 59, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x1000000000000000L, 60, "UNDEFINE", "")); // 16 组
        wifiLongBitFeatureList.add(new LongBitModel(0x2000000000000000L, 61, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x4000000000000000L, 62, "UNDEFINE", ""));
        wifiLongBitFeatureList.add(new LongBitModel(0x8000000000000000L, 63, "UNDEFINE", ""));

    }


/*    static ArrayList<IntBitModel>  xIntBitFeatureList = new ArrayList<IntBitModel>();

   static {
        xIntBitFeatureList.add(new IntBitModel(0x00000001,1,"",""));  // 1组
        xIntBitFeatureList.add(new IntBitModel(0x00000002,2,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000004,3,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000008,4,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000010,5,"",""));  // 2组
        xIntBitFeatureList.add(new IntBitModel(0x00000020,6,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000040,7,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000080,8,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000100,9,"",""));  // 3组
        xIntBitFeatureList.add(new IntBitModel(0x00000200,10,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000400,11,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00000800,12,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00001000,13,"",""));  // 4组
        xIntBitFeatureList.add(new IntBitModel(0x00002000,14,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00004000,15,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00008000,16,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00010000,17,"",""));  // 5组
        xIntBitFeatureList.add(new IntBitModel(0x00020000,18,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00040000,19,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00080000,20,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00100000,21,"",""));  // 6组
        xIntBitFeatureList.add(new IntBitModel(0x00200000,22,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00400000,23,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x00800000,24,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x01000000,25,"","")); // 7组
        xIntBitFeatureList.add(new IntBitModel(0x02000000,26,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x04000000,27,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x08000000,28,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x10000000,29,"","")); // 8 组
        xIntBitFeatureList.add(new IntBitModel(0x20000000,30,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x40000000,31,"",""));
        xIntBitFeatureList.add(new IntBitModel(0x80000000,32,"",""));


    }*/


    static void toCheck64BitFeature(long flag) {
        System.out.println();
        System.out.println();
        String OxHexString = getOxHexString(flag);
        byte[] flagByteArr = null ;
        if(OxHexString.startsWith("0x") && OxHexString.length() <= 4) {
        	String hexString = OxHexString.replace("0x", "");
            flagByteArr =	hexStringToByteArray(hexString);
        }
        String byte2Int_Tip = "";
        System.out.println("#######=============解析   flag=" + getOxHexString(flag) + "   Begin ===========####");
        if( flagByteArr != null ) {
//        System.out.println("(byte["+OxHexString+"] 转 int 10进制):" + Arrays.toString(flagByteArr));   	
        byte2Int_Tip = "(byte["+OxHexString+"]_转_int_10进制):" + Arrays.toString(flagByteArr);
        }
        System.out.println("当前解析能力标记位Long(       16进制):" + getOxHexString(flag));
        
        
        System.out.println("当前解析能力标记位Long( 有符号10进制):" + flag   +"                 (无符号10进制):"+Long.toUnsignedString(flag) + "          "+ byte2Int_Tip)  ;
        System.out.println("当前解析能力标记位Long( 未补位 2进制):" + Long.toBinaryString(flag));
        System.out.println("当前解析能力标记位Long( 补位   2进制):" + get64bitString(flag));
        System.out.println("=============解析能力如下===========");
        System.out.println("索引:all  bit位:" + get64bitString(flag));

        //     System.out.println("能力总成: xxxx"+ getFixedStringInt(0)+ "     能力bit位    "+get64bitString(flag));


        for (int i = 0; i < wifiLongBitFeatureList.size(); i++) {
            LongBitModel longMode = wifiLongBitFeatureList.get(i);
            if ((flag & longMode.flag) == longMode.flag) {
                System.out.println("索引:" + getFixedStringInt(longMode.index) + "  bit位:" + get64bitString(longMode.flag) + "  16进制:" + longMode.radixValue16  +"  10进制:"+longMode.radixValue10);
            } else {
                //  System.out.println("没有匹配到!=="+ longMode.index);
            }
        }
        System.out.println("=============解析能力结束===========");
        System.out.println("#######=============解析   flag=" + getOxHexString(flag) + "   End   ===========####");
        System.out.println();
        System.out.println();
    }


/*
    static void toCheck32Feature(int flag){
        System.out.println("当前解析能力标记位Int(      16进制):" + getOxHexString(flag));
        System.out.println("当前解析能力标记位Int(      十进制):" + flag);
        System.out.println("当前解析能力标记位Int(未补位 二进制):" + Integer.toBinaryString(flag));
        System.out.println("当前解析能力标记位Int( 补位  二进制):" + get32bitString(flag));
        System.out.println("=============解析能力如下===========");
        System.out.println("能力 总成:"+ getFixedStringInt(0)+ "     能力bit位    "+get32bitString(flag));
        for (int i = 0; i < xIntBitFeatureList.size(); i++) {
            IntBitModel intMode = xIntBitFeatureList.get(i);
            if((flag & intMode.flag) == intMode.flag){
                System.out.println("能力index:"+ getFixedStringInt(intMode.index)+ "     能力bit位    "+get32bitString(intMode.flag)+"     能力名称:"+intMode.keyName );
            }else{
                //  System.out.println("没有匹配到!=="+ longMode.index);
            }
        }
        System.out.println("=============解析能力结束===========");

    }*/


    static String get32bitString(int value) {
        String cur = Integer.toBinaryString(value);
        int initSize = cur.length();
        int fixZeroCount = 32 - initSize;
        String zeroStr = "";
        for (int i = 0; i < fixZeroCount; i++) {
            zeroStr = zeroStr + "0";
        }

        return zeroStr + cur;
    }


    static String get64bitString(long value) {
        String cur = Long.toBinaryString(value);
        int initSize = cur.length();
        int fixZeroCount = 64 - initSize;
        String zeroStr = "";
        for (int i = 0; i < fixZeroCount; i++) {
            zeroStr = zeroStr + "0";
        }

        return zeroStr + cur;
    }


    static String getOxHexString(long value) {
        String cur = Long.toHexString(value);
        if (cur.startsWith("0x")) {
            return cur;
        }
        cur = "0x" + cur;
        return cur;
    }

// 0xffffffff
    static String getHexString64(long value) {
        String cur = Long.toHexString(value);
        int size = cur.length();
        int paddingSize = 16 - size;
        for (int i = 0; i < paddingSize; i++) {
            cur = "0"+cur;
        }

        if (cur.startsWith("0x")) {
            return cur;
        }
        cur = "0x" + cur;
        return cur;
    }


    static String getFixedStringInt(int value) {
        String retStr = value + "";
        if (value > 100) {
            return retStr;
        }
        if (value < 100 && value >= 10) {
            retStr = "0" + retStr;
        }
        if (value < 10 && value >= 0) {
            retStr = "00" + retStr;
        }
        return retStr;
    }


    static class LongBitModel {
        long flag;
        int index;
        String radixValue10;
        String radixValue16;
        String keyName;
        String explain;

        LongBitModel(long flag, int index, String keyName, String explain) {
            this.flag = flag;
            this.index = index;
            this.keyName = keyName;
            this.explain = explain;
            radixValue16 = getHexString64(this.flag);
            radixValue10 = ""+Long.toUnsignedString(this.flag);
        }
    }

    static class IntBitModel {
        int flag;
        int index;
        String keyName;
        String explain;

        IntBitModel(int bitValue, int index, String keyName, String explain) {
            this.flag = bitValue;
            this.index = index;
            this.keyName = keyName;
            this.explain = explain;

        }
    }

    static void showUsualFlagInfo() {
        toCheck64BitFeature(0x1);
        toCheck64BitFeature(0x6);
        toCheck64BitFeature(0x192);
        toCheck64BitFeature(0x168);
        toCheck64BitFeature(0x1024);
        toCheck64BitFeature(1024);
        toCheck64BitFeature(0);
        toCheck64BitFeature(1);
        toCheck64BitFeature(-1);
        toCheck64BitFeature(192);
        toCheck64BitFeature(168);
        
        ShowUseTip();
       
   
    }
    // 0b111100011 这样的数据
    static void ShowUseTip() {
    
    	System.out.println(" zbitfeature_E0   111111                ## 十进制 转为64位long值输出");
     	System.out.println(" zbitfeature_E0   101010                ## 十进制 转为64位long值输出");
     	System.out.println(" zbitfeature_E0   -22222                ## 十进制 转为64位long值输出");

    	System.out.println(" zbitfeature_E0   0xACDE12AEC           ## 十六进制 转为64位long值输出");
     	System.out.println(" zbitfeature_E0   0xFEDCBA74ABC         ## 十六进制 转为64位long值输出");
     	
    	System.out.println(" zbitfeature_E0   0b111111              ## 二进制 转为64位long值输出");
     	System.out.println(" zbitfeature_E0   0b10101010101010101   ## 二进制 转为64位long值输出");
     	
     	
    	System.out.println(" zbitfeature_E0   0b111111              ## 二进制 转为64位long值输出");
     	System.out.println(" zbitfeature_E0   0b10101010101010101   ## 二进制 转为64位long值输出");
     	
       	System.out.println(" zbitfeature_E0   byte98000000          ## 字节数组 制转为64位long值输出");
     	System.out.println(" zbitfeature_E0   byteA0000000          ## 字节数组 转为64位long值输出");
     	
       	System.out.println(" zbitfeature_E0   timebyte9800000098000000          ## 字节数组 把字节转为时间输出");

     	
     	
    }


    // 名称中的关键词
    static ArrayList<String> mKeyWordName = new ArrayList<>();


    public static void main(String[] args) {

        String typeStr = null;
        String mShellPath = null;
        File curDirFile = null;
        String dstDirFilePathStr = null;
//args[0] =  0x1231f
//args[1] =  12314
//args[2] = 0xsa     //
//args[2] = 0b111111110000011111000011110000111100001111    // 输入是二进制时 也把他转为 16进制 10进制输出
// zbitfeature   0xaff  21313  // 对给出的参数进行bit位分析然后打印

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }


        if (mKeyWordName.size() == 0) {

            showUsualFlagInfo();
            System.out.println("用户输入的参数为空,已打印出常用bit位数据情况!");
            return;
        }
        for (int i = 0; i < mKeyWordName.size(); i++) {
            String paramItem = mKeyWordName.get(i);
            
            
            print_byte(paramItem);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("——————————————————————————————————————"+" 数值字节处理开始 "+"——————————————————————————————————————");
            
            
            if (paramItem.startsWith("0x") || paramItem.startsWith("ox")) {  // 0xfffff
                String longValue = paramItem.substring(2);
                if(longValue == null || longValue.length() > 16){
                    System.out.println("用户输入的参数太大 已经大于8字节 64 bit数据 请检查输入！ ");
                    return;
                }
                Long longItem = Long.parseUnsignedLong(longValue, 16);
                toCheck64BitFeature(longItem);


            }else if(paramItem.startsWith("0b") || paramItem.startsWith("ob") ) {  // 0b101010101
            	String binary_num = paramItem.substring(2);
            	
               if( binary_num == null){
                 System.out.println("用户输入的二进制参数太长 无法获取 请检查!");
                    return;
                 }
                           
            	
                if( binary_num.length() > 64){
                    System.out.println("用户输入的二进制参数太长 已经大于8字节 64 bit数据 请检查输入！ binary_num.length()="+binary_num.length());
                    return;
                }
                
                String empty_check_str = binary_num.replace("0", "").replace("1", "").trim();
                
                if(!"".equals(empty_check_str)) {
                	System.out.println("用户输入的 ob开头的二进制参数 不仅仅包含0和1 还包含"+empty_check_str+" 请检查!");
                	return;
                }
                
                // 二进制转为 16进制
                Long longItem = Long.parseUnsignedLong(binary_num.trim(), 2);
                toCheck64BitFeature(longItem);    
            	
            }  else if(paramItem.toLowerCase().startsWith("byte")){      // byte98000000
               	String byteArr_Str = paramItem.toLowerCase().replace("byte", "");  // 98000000
               	
               	if(byteArr_Str.length() > 16) {
               		// 
               		System.out.println("当前输入的 byte数组 转为 long 最多为 64位bit 16个十六进制字符!  byteArr_Str="+byteArr_Str);
               		return; 
               	}
               	
               	
               	// 16进制 字符串  转为 byte 数组
              byte[] longByteArr = 	hexStringToByteArray(byteArr_Str);
               	
              int byteInt = bytesToInt(longByteArr);
              long byteLong = (long)(byteInt);
              
              toCheck64BitFeature(readUnsignedLong(byteLong).longValue());
            	
            	
            	
            }else if(paramItem.toLowerCase().startsWith("timebyte")){
               	String timebyteArr_Str = paramItem.toLowerCase().replace("timebyte", "");  // 98000000
               	
               	if(timebyteArr_Str.length() > 16) {
               		// 
               		System.out.println("当前输入的 timebyte数组 转为 long 最多为 64位bit 16个十六进制字符!  timebyteArr_Str="+timebyteArr_Str);
               		return; 
               	}
            	
                ByteOrder bo = ByteOrder.BIG_ENDIAN;
  
                byte[] longByteArr =  	hexStringToByteArray(timebyteArr_Str);
                
                if(longByteArr == null) {
                	
                	System.out.println(" longByteArr 字节数组 为空 ");
                	return;
                }
                
                if(longByteArr.length != 8) {
                	
                	System.out.println("byte[] longByteArr 字节数组长度不等于 8 longByteArr.length="+longByteArr.length);
                	return;
                }
                
                byte[] time_S = {longByteArr[0],longByteArr[1],longByteArr[2],longByteArr[3]};

                byte[] time_Ms = {longByteArr[4],longByteArr[5],longByteArr[6],longByteArr[7]};
                
                System.out.println("输入字节数组: "+"0x"+timebyteArr_Str);
                String up4Byte_str = timebyteArr_Str.substring(0,8);
                String down4Byte_str = timebyteArr_Str.substring(8);

                String up4byte_style_1_str = toHexString(longByteArr[0])+" "+toHexString(longByteArr[1])+" "+toHexString(longByteArr[2])+" "+toHexString(longByteArr[3])+" ";
           
                String down4byte_style_1_str = toHexString(longByteArr[4])+" "+toHexString(longByteArr[5])+" "+toHexString(longByteArr[6])+" "+toHexString(longByteArr[7])+" ";
                

                String timeS = dateToString((long)(bytesToInt(time_S) * 1000L));
         		int timeMs = bytesToInt(time_Ms);
                System.out.println("方式1_高4字节: "+"0x"+up4Byte_str +"    "+"字节情况:"+up4byte_style_1_str+" ");
                System.out.println("方式1_低4字节: "+"0x"+down4Byte_str+"    "+"字节情况:"+down4byte_style_1_str+" ");
                
         		System.out.println("解析方式1_(pcap方式): "+"高字节32位timeS ="+timeS+"   低字节32位timeMs="+timeMs);
         				
         		System.out.println();
          		System.out.println();
                //  4C F6 04 00【Timestamp (High) 4个字节】
                //  F5 CB A4 E4 【Timestamp (Lower) 4个字节】
                //  输入的字节是 0x4CF60400【4->1】F5CBA4E4
                // 组成的字节应该是 0x0004F64CE4A4CBF5 =1396710020795381毫秒=1396710020秒
                
//         		toUnsignedString
         		byte[] long_time_ms = {longByteArr[3],longByteArr[2],longByteArr[1],longByteArr[0],longByteArr[7],longByteArr[6],longByteArr[5],longByteArr[4]};	        
         		
         		
                String up4byte_style_2_str = toHexString(longByteArr[3])+" "+toHexString(longByteArr[2])+" "+toHexString(longByteArr[1])+" "+toHexString(longByteArr[0])+" ";
                
                String down4byte_style_2_str = toHexString(longByteArr[7])+" "+toHexString(longByteArr[6])+" "+toHexString(longByteArr[5])+" "+toHexString(longByteArr[4])+" ";
                

                String style2_byte_arr_str = "0x"+(up4byte_style_2_str+down4byte_style_2_str).replace(" ", "");

                
             
                System.out.println("方式2 原始的字节: "+"0x"+timebyteArr_Str.toUpperCase());
                System.out.println("方式2 重组的字节: "+style2_byte_arr_str);
                System.out.println("方式2_高4字节: "+"0x"+up4byte_style_2_str +"    "+"字节情况:"+up4byte_style_2_str+" ");
                System.out.println("方式2_低4字节: "+"0x"+down4byte_style_2_str+"    "+"字节情况:"+down4byte_style_2_str+" ");
                
                //  long=1396710020795381  微妙
                //  long=1560950936695086634
                //  以 10(-6)次方  微妙 为单位 
         		long long_time_stamp =   bytesToLong(long_time_ms,0,false);
         		String long_time_stamp_test = ""+long_time_stamp;
         		
         		long long_time_stamp_ws = 0;
         		long long_time_stamp_ms = 0 ;
         		String tip ="微秒10[-6]为单位";
         		
         		if(long_time_stamp_test.length() >= "1560950936695086634".length() ) {
         			//  以  纳秒为单位
         			 tip ="纳秒10[-9]为单位";
         	    	 long_time_stamp_ws =(long) (long_time_stamp % 1000000000L);
         	   	  long_time_stamp_ms = ( long_time_stamp - long_time_stamp_ws ) / 1000000;
         			
         		}else {  //  以 微秒为单位
         			
         			
                	 long_time_stamp_ws =(long) (long_time_stamp % 1000000L);
                    
                	//  long=1396710020795381   ws=795381    转为 毫秒 需要再 除以 1000
                	 long_time_stamp_ms = ( long_time_stamp - long_time_stamp_ws ) / 1000;
         		}

            

           

                Calendar stampCalendar=Calendar.getInstance();
                stampCalendar.setTimeInMillis(long_time_stamp_ms);

                int year_8 =stampCalendar.get(Calendar.YEAR);
               	String year_8_str 	= year_8+"";
                
                int month_8 = (stampCalendar.get(Calendar.MONTH) == 0 ? 12 : (stampCalendar.get(Calendar.MONTH)+1));
             
             	String month_8_str 	= (month_8>9?month_8+"":"0"+month_8);
             		   
                int day_8 = stampCalendar.get(Calendar.DAY_OF_MONTH);
                String day_8_str 	=day_8+"";
                if(day_8 < 10) {
             	   day_8_str = "0"+day_8;
                } 
            	
                
                int hour_8 = stampCalendar.get(Calendar.HOUR_OF_DAY);
                String hour_8_str 	=hour_8+"";
                if(hour_8 < 10) {
             	   hour_8_str = "0"+hour_8;
                } 
                
                int minute_8 = stampCalendar.get(Calendar.MINUTE);
                
                String minute_8_str 	=minute_8+"";
                if(minute_8 < 10) {
             	   minute_8_str = "0"+minute_8;
                } 
                
                int second_8 = stampCalendar.get(Calendar.SECOND);
                
                String second_8_str 	=second_8+"";
                if(second_8 < 10) {
             	   second_8_str = "0"+second_8;
                } 
                
                String time_stamp_style2_str =" "+year_8_str+"-"+month_8_str+"-"+day_8_str+"_"+hour_8_str+":"+minute_8_str+":"+second_8_str;
                
         
                
         		System.out.println("解析方式2_(pcapng方式): "+"时间戳:"+time_stamp_style2_str+"  秒="+(long_time_stamp/1000000L)+"   微妙"+long_time_stamp_ws+"  long="+long_time_stamp+" "+tip);

            	
            }else {

                if(paramItem == null || paramItem.length() > 20 ){
                    System.out.println("用户输入的参数太大 已经大于8字节 64 bit数据 请检查输入！ ");
                    return;
                }

                if(paramItem == null || paramItem.length() > 16 ){

                    // 18446744073709551615
                    long curParam = 0;
                    try {
                         curParam = Long.parseUnsignedLong(paramItem);
                    }catch (Exception e){
                        System.out.println("用户输入的参数太大 已经大于8字节 64 bit数据 请检查输入！ ");
                        return;
                    }

                    if( curParam > 0xffffffffffffffffL){
                        System.out.println("用户输入的参数太大 已经大于8字节 64 bit数据 请检查输入！ ");
                        return;
                    }
                    
                    

                }


                if(paramItem.trim().startsWith("-") && isNumericChar(paramItem.trim())){  //  以负号开头的数字

                    long curValue = Long.parseLong(paramItem);
                    long unsignedValue = curValue & Long.MAX_VALUE ;
                  //  System.out.println("curValue1 = "+ curValue);
                 //   System.out.println("curValue2 = "+ unsignedValue);

                   // toCheck64BitFeature(Long.parseUnsignedLong(unsignedValue+""));


                    toCheck64BitFeature(readUnsignedLong(curValue).longValue());
                }else if(isNumericChar(paramItem.trim())) {

                    toCheck64BitFeature(Long.parseUnsignedLong(paramItem));
                } else {
                	// 打印 这个字符串 字面值的  字节数组
                	
//                	String rawInputStr = paramItem.trim();
//                	byte[] str_bytes = rawInputStr.getBytes();
//                	
//                	String byte_tipstr = bytesToHexString(str_bytes);
//                	String byte_char_tipstr = bytesToHexCharString(str_bytes,rawInputStr);
//                	
//                	
//                  	String byte_10int_str = bytesToIntString(str_bytes);
//                  	
//                	String byte_10int_tipstr = bytesToIntCharString(str_bytes,rawInputStr);
//                	
//                	
//                	
//                	
//                 	System.out.println("");
//                	System.out.println("————————————————————————"+"输入字符串字面量值:【"+rawInputStr+"】"+"————————————————————————");
//                	System.out.println("字符串十十进制字节:【 [ "+ byte_10int_str+"] 】");
//                	System.out.println("字符串十十进制字节:【 [ "+ byte_10int_tipstr+"] 】");
//                	
//                	System.out.println("字符串十六进制字节:【 [ "+ byte_tipstr+" ] 】");
//                  	System.out.println("字符串十六进制字节:【 [ "+ byte_char_tipstr+"] 】");
                }


            }
            

          	
        }
        


    }
    
    
	static void print_byte(String  paramItem ) {
		
        //    每个  输入的  字符 都打印 出 字节
        
    	String rawInputStr = paramItem.trim();
    	byte[] str_bytes = rawInputStr.getBytes();
    	
    	String byte_tipstr = bytesToHexString(str_bytes);
    	String byte_char_tipstr = bytesToHexCharString(str_bytes,rawInputStr);
    	
    	
      	String byte_10int_str = bytesToIntString(str_bytes);
      	
    	String byte_10int_tipstr = bytesToIntCharString(str_bytes,rawInputStr);
    	
    	
    	
    	
     	System.out.println("");
    	System.out.println("————————————————————————"+"输入字符串字面量值:【"+rawInputStr+"】"+"————————————————————————");
    	System.out.println("字符串十十进制字节:【 [ "+ byte_10int_str+"] 】");
    	System.out.println("字符串十十进制字节:【 [ "+ byte_10int_tipstr+"] 】");
    	
    	System.out.println("字符串十六进制字节:【 [ "+ byte_tipstr+" ] 】");
      	System.out.println("字符串十六进制字节:【 [ "+ byte_char_tipstr+"] 】");
		
	}
    
    
    
	static String bytesToIntString(byte[] src ) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;

		int byteIndex = 0 ;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toString(aSrc & 0xFF).toUpperCase();
			int value = Integer.parseInt(hv);
			
			int padding_blank = 2;
			String blank_pandding_str = "  ";
			if( value >= 100) {
				padding_blank = 1;
				blank_pandding_str = " ";
			}
			if (hv.length() < 2) {
				builder.append(0);
			}
			
			
			if(byteIndex == src.length -1) {
				builder.append(blank_pandding_str+hv+"  ");
			}else {
				builder.append(blank_pandding_str+hv+"   "+","+" ");
			}
		
			byteIndex++;
			
		}

//        System.out.println(builder.toString());
		return builder.toString();
	}
	
	
    
	static String bytesToIntCharString(byte[] src,String rawstr) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;

		int byteIndex = 0 ;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toString(aSrc & 0xFF).toUpperCase();
			int value = Integer.parseInt(hv);
			
			int padding_blank = 2;
			String blank_pandding_str = "  ";
			if( value >= 100) {
				padding_blank = 1;
				blank_pandding_str = " ";
			}
			if (hv.length() < 2) {
				builder.append(0);
			}
			
			
			if(byteIndex == src.length -1) {
				builder.append(blank_pandding_str+hv+"_"+rawstr.charAt(byteIndex)+"");
			}else {
				builder.append(blank_pandding_str+hv+"_"+rawstr.charAt(byteIndex)+" "+","+" ");
			}
		
			byteIndex++;
			
		}

//        System.out.println(builder.toString());
		return builder.toString();
	}
	
	
	
    
    // 【0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x74】
    // 【0x31_x,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x74】
	static String bytesToHexCharString(byte[] src,String rawstr) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;

		int byteIndex = 0 ;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			if(byteIndex == src.length -1) {
				builder.append("0x"+hv+"_"+rawstr.charAt(byteIndex)+"");
			}else {
				builder.append("0x"+hv+"_"+rawstr.charAt(byteIndex)+" "+","+" ");
			}
		
			byteIndex++;
			
		}

//        System.out.println(builder.toString());
		return builder.toString();
	}
	
	
	static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;

		int byteIndex = 0 ;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			if(byteIndex == src.length -1) {
				builder.append("0x"+hv+" ");
			}else {
				builder.append("0x"+hv+"   "+","+" ");
			}
		
			byteIndex++;
			
		}

//        System.out.println(builder.toString());
		return builder.toString();
	}
	
    
	// 判断是否是数字
	static boolean isNumericChar(String str) {
		
		for (int i = 0; i <str.length(); i++) {
			char charItem = str.charAt(i);
			if ((i== 0) && (charItem  == '-' || charItem  == '+')){
				continue;
			}
			
			if(!Character.isDigit(charItem)) {
				   return false;
			}
		}
	   return true;
	}
	
    /**
     * 利用 {@link java.nio.ByteBuffer}实现byte[]转long
     * @param input
     * @param offset 
     * @param littleEndian 输入数组是否小端模式
     * @return
     */
    public static long bytesToLong(byte[] input, int offset, boolean littleEndian) { 
        if(offset <0 || offset+8>input.length)
            throw new IllegalArgumentException(String.format("less than 8 bytes from index %d  is insufficient for long",offset));
        ByteBuffer buffer = ByteBuffer.wrap(input,offset,8);
        if(littleEndian){
            // ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式 
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();  
    } 
    
    public static  int bytesToInt(byte[] a){
        int ans=0;
        for(int i=0;i<4;i++){
            ans<<=8;
            ans|=(a[3-i]&0xff);
            /* 这种写法会看起来更加清楚一些：
            int tmp=a[3-i];
            tmp=tmp&0x000000ff;
            ans|=tmp;*/
           // intPrint(ans);
        }
        return ans;
    }
    
    
    public static  long bytesToLong(byte[] a){
        long ans=0;
        for(int i=0;i<8;i++){
            ans<<=8;
            ans|=(a[7-i]&0xff);
            /* 这种写法会看起来更加清楚一些：
            int tmp=a[3-i];
            tmp=tmp&0x000000ff;
            ans|=tmp;*/
           // intPrint(ans);
        }
        return ans;
    }
    
	public static String toHexString(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append(toHexString(toByteArray(i)));
		sb.append(toTenString(i));

		return sb.toString();
	}

	public static String toHexStringNoTen(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append(toHexString(toByteArray(i)));
		return "0x" + sb.toString().trim();
	}

	public static String toHexString(byte b) {
		return toHexString(toByteArray(b));
	}

	public static String toHexString(byte[] array) {
		return toHexString(array, 0, array.length, true);
	}

	public static String toHexString(byte[] array, boolean upperCase) {
		return toHexString(array, 0, array.length, upperCase);
	}

	public static String toHexString(byte[] array, int offset, int length) {
		return toHexString(array, offset, length, true);
	}

	public static String toHexString(byte[] array, int offset, int length, boolean upperCase) {
		char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
		char[] buf = new char[length * 2];

		int bufIndex = 0;
		for (int i = offset; i < offset + length; i++) {
			byte b = array[i];
			buf[bufIndex++] = digits[(b >>> 4) & 0x0F];
			buf[bufIndex++] = digits[b & 0x0F];
		}

		return new String(buf);
	}

	public static byte[] toByteArray(byte b) {
		byte[] array = new byte[1];
		array[0] = b;
		return array;
	}

	public static byte[] toByteArray(int i) {
		byte[] array = new byte[4];

		array[3] = (byte) (i & 0xFF);
		array[2] = (byte) ((i >> 8) & 0xFF);
		array[1] = (byte) ((i >> 16) & 0xFF);
		array[0] = (byte) ((i >> 24) & 0xFF);

		return array;
	}

	private static int toByte(char c) {
		if (c >= '0' && c <= '9')
			return (c - '0');
		if (c >= 'A' && c <= 'F')
			return (c - 'A' + 10);
		if (c >= 'a' && c <= 'f')
			return (c - 'a' + 10);

		throw new RuntimeException("Invalid hex char '" + c + "'");
	}

	public static byte[] hexStringToByteArray(String hexString) {
		if(hexString.length()% 2 != 0 ) {
			hexString = "0"+hexString;
		}
		int length = hexString.length();
		byte[] buffer = new byte[length / 2];

		for (int i = 0; i < length; i += 2) {
			buffer[i / 2] = (byte) ((toByte(hexString.charAt(i)) << 4) | toByte(hexString.charAt(i + 1)));
		}

		return buffer;
	}

	 final static char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };
	 final static char[] HEX_LOWER_CASE_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };
	
	public static StringBuilder appendByteAsHex(StringBuilder sb, byte b, boolean upperCase) {
		char[] digits = upperCase ? HEX_DIGITS : HEX_LOWER_CASE_DIGITS;
		sb.append(digits[(b >> 4) & 0xf]);
		sb.append(digits[b & 0xf]);
		return sb;
	}

	public static String toTenString(int i) {
		String str = i + "";
		int length = str.length();
		if (length < 10) {
			int paddingSize = 10 - length;

			for (int j = 0; j < paddingSize; j++) {
				str = "0" + str;
			}
		}
		str = " " + str;
		// System.out.println(" 10进制 = "+ str);
		return str;
	}



    public static final BigDecimal readUnsignedLong(long value)  {
        if (value >= 0)
            return new BigDecimal(value);
        long lowValue = value & 0x7fffffffffffffffL;
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

	// yyyy-MM-dd HH:mm:ss
    public static String dateToString(long timeStamp) {
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = f.format(timeStamp);
        return now;

    }
    

    
}
