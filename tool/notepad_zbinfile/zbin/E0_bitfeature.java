import java.io.File;
import java.util.ArrayList;

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
        System.out.println("#######=============解析   flag=" + getOxHexString(flag) + "   Begin ===========####");
        System.out.println("当前解析能力标记位Long(       16进制):" + getOxHexString(flag));
        System.out.println("当前解析能力标记位Long( 有符号10进制):" + flag   +"                 (无符号10进制):"+Long.toUnsignedString(flag) )  ;
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
        toCheck64BitFeature(0x16);
        toCheck64BitFeature(0x196);

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
            if (paramItem.startsWith("0x")) {
                String longValue = paramItem.substring(2);
                if(longValue == null || longValue.length() > 16){
                    System.out.println("用户输入的参数太大 已经大于8字节 64 bit数据 请检查输入！ ");
                    return;
                }
                Long longItem = Long.parseUnsignedLong(longValue, 16);
                toCheck64BitFeature(longItem);


            } else {

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

                toCheck64BitFeature(Long.parseUnsignedLong(paramItem));
            }
        }

    }


}
