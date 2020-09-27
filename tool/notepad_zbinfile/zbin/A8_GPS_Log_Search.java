
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class A8_GPS_Log_Search {

    //  public static final ArrayList<String> StringArr = new ArrayList<>();  // 测试使用

    // old:最终打印的分析的字符串集合  【应该分文件处理】  new: 当前每个文件分析的字符串集合
    public static final ArrayList<String> analysisStringArr = new ArrayList<>();

    // 所有文件的 字符串 分析集合
    public static final ArrayList<ArrayList<String>> allFileAnalysisStringArr = new ArrayList<ArrayList<String>>();

    public static final ArrayList<AbstractKeyEntry> keyEntryList = new ArrayList<>();


    static {


//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SV count: 12 Used In Fix: 0
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: PRN  17, 19,  2,  5,  6, 12, 13, 15, 24, 25, 29, 15,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SNR  27, 30,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: ELE   3, 22, 63,  4, 42, 40, 10, 17, 68,  9, 13, 40,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: AZM 133,142, 43, 19,119,205,357,326,240,237,298, 73,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: FRQ 1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1602000000,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: FLG  A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F,

        keyEntryList.add(new BugReportService_onStartCommand());  // bug2go 信息


        keyEntryList.add(new WifiVendorHal_Driver_Firmware());  // 固件信息

        keyEntryList.add(new KeyguardViewMediator_HandleNotifyStartedGoingToSleep());  // 设备睡觉信息

        keyEntryList.add(new KeyguardViewMediator_handleNotifyScreenTurned());  // 设备醒来


        keyEntryList.add(new GnssLocationProvider_SV());
        keyEntryList.add(new GnssLocationProvider_PRN());
        keyEntryList.add(new GnssLocationProvider_SNR());
        keyEntryList.add(new GnssLocationProvider_ELE());
        keyEntryList.add(new GnssLocationProvider_AZM());
        keyEntryList.add(new GnssLocationProvider_FRQ());
        keyEntryList.add(new GnssLocationProvider_FLG());

        keyEntryList.add(new GnssLocationProvider_GPS_STATUS_ENGINE_ON());
        keyEntryList.add(new GnssLocationProvider_GPS_STATUS_ENGINE_OFF());
        keyEntryList.add(new GnssLocationProvider_GPS_STATUS_SESSION_BEGIN());
        keyEntryList.add(new GnssLocationProvider_GPS_STATUS_SESSION_END());
        keyEntryList.add(new GnssLocationProvider_TTFF());

        keyEntryList.add(new LocationManagerService_Incoming_Location());

        keyEntryList.add(new Location_Mode_False());
        keyEntryList.add(new Location_Mode_True());

        keyEntryList.add(new GnssLocationProvider_SIM_Available());
        keyEntryList.add(new GnssLocationProvider_GnssNameCb());
        keyEntryList.add(new GnssLocationProvider_SCREEN_ON());
        keyEntryList.add(new GnssLocationProvider_SCREEN_OFF());

        keyEntryList.add(new GnssLocationProvider_StartNavigating());
        keyEntryList.add(new GnssLocationProvider_ProviderRequest_ON());
        keyEntryList.add(new GnssLocationProvider_ReportStatus());
        keyEntryList.add(new LocationManagerService_Sending_Location());



        keyEntryList.add(new LocationManagerService_Started_Location());
        keyEntryList.add(new LocationManagerService_Stopped_Location());
    }
   /*
  }

*/  //  zukgit-WIFI-END
//	    abstract static class AbstractKeyEntry {
//        public String keyword; // 关键字
//        public String explain; // 说明
//        public String codePath;  // 代码 该Log打印的代码路径
//       public String curLogLineContent;  // 当前记录的那行Log  从Log起始点 开始
//       public ArrayList<String> logArray;  // 当前记录的有些不一样的记录行的 集合
//       public boolean shouldPrint = true; // 是否应该打印
//      public boolean shouldFixLog = false; // 是否需要对当前的Log 进行一些Log的处理 比如太长截取
//		}
// MeetPrintBase   遇到就打印的Log的类
// OncePrintBase   只需要打印一次的类   打印的是原始Log  没得选
// DiffMeetPrintBase  有差异才会打印的类  需要实现返回差异部分字符串的抽象函数
// FixLong_Diff_Pre_PrintBase   需要修改  与上一次有差异的函数
// FixLongPrintBase    需要对当前Log  进行 修改 并打印修改后的字符串  需要实现抽象函数
// CollectEndPrintBase    先统一收集关键字符串  并在文件读取完成后 统一打印的类   需要实现抽象函数
// MultiKeyWordPrintBase    包含多个关键字的处理类   需要返回 关键字的字符串集合



//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SV count: 12 Used In Fix: 0
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: PRN  17, 19,  2,  5,  6, 12, 13, 15, 24, 25, 29, 15,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SNR  27, 30,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: ELE   3, 22, 63,  4, 42, 40, 10, 17, 68,  9, 13, 40,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: AZM 133,142, 43, 19,119,205,357,326,240,237,298, 73,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: FRQ 1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1602000000,
//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: FLG  A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F,


    // 11-25 10:17:30.009  2052  2371 D GnssLocationProvider: receive broadcast intent, action: android.intent.action.SCREEN_ON

    static class GnssLocationProvider_SCREEN_ON extends MeetPrintBase {
        GnssLocationProvider_SCREEN_ON() {
            keyword = "GnssLocationProvider: receive broadcast intent, action: android.intent.action.SCREEN_ON";
            explain = " 检测到 亮屏 事件   ";
            codePath = null;
        }

    }



    //  11-25 10:17:34.284  2052  2371 D GnssLocationProvider: receive broadcast intent, action: android.intent.action.SCREEN_OFF
    static class GnssLocationProvider_SCREEN_OFF extends MeetPrintBase {
        GnssLocationProvider_SCREEN_OFF() {
            keyword = "GnssLocationProvider: receive broadcast intent, action: android.intent.action.SCREEN_OFF";
            explain = " 检测到 灭屏 事件   ";
            codePath = null;
        }

    }





    //11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SV count: 12 Used In Fix: 0
    static class GnssLocationProvider_SV extends MeetPrintBase {
        GnssLocationProvider_SV() {
            keyword = "GnssLocationProvider: SV";
            explain = "// libgnss.so上报 , GnssLocationProvider.java 中  方法 void handleReportSvStatus(SvStatusInfo info) 中的 int SvStatusInfo.mSvCount【SV count:卫星个数 】 || 【 卫星可用个数 】 usedInFixCount  是int[] mSvidWithFlags 数组中 比特位 0x4 第2位 不为0 的个数 (【 int[] 】info.mSvidWithFlags[i] & GnssStatus.GNSS_SV_FLAGS_USED_IN_FIX【0x4 2^2 第2个比特位】) != 0  ";
            codePath = null;
        }

    }

    //11-25 11:25:19.029  2159  2252 D GnssLocationProvider: PRN  17, 19,  2,  5,  6, 12, 13, 15, 24, 25, 29, 15,
    static class GnssLocationProvider_PRN extends MeetPrintBase {
        GnssLocationProvider_PRN() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> int[] mSvidWithFlags ,  int数组  32 位
            keyword = "GnssLocationProvider: PRN";
            explain = "GnssLocationProvider.java 中  方法 void handleReportSvStatus(SvStatusInfo info) 【PRN-是伪随机噪声码（pseudo random noise code）的缩写，不是卫星卫星的编号】 【 SvStatusInfo.int[] mSvidWithFlags】;  mSvidWithFlags[i] >> GnssStatus.SVID_SHIFT_WIDTH【8,往右移动八位的值】的集合 || " +
                    "【 GPS卫星所发送的信号包括载波信号、P码、C/A码和数据码(或称D码)等多种信号分量  每一卫星有一个特殊的PRN码《可用来作为卫星的 identify 》 码，所以用户接收机可以通过生成并匹配相同的码样解调一个特别的卫星信号】";
            codePath = null;
        }

    }

    //11-25 11:25:19.029  2159  2252 D GnssLocationProvider: SNR  27, 30,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
    static class GnssLocationProvider_SNR extends MeetPrintBase {
        GnssLocationProvider_SNR() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> int[] mSvidWithFlags ,  int数组  32 位
            keyword = "GnssLocationProvider: SNR";
            explain = "GnssLocationProvider.java 中  方法 void handleReportSvStatus(SvStatusInfo info) 【 SNR 信噪比  大于20一般认为信号有效 】 【 SvStatusInfo.float[] mCn0s 】; ";

           codePath = null;
        }

    }


    //11-25 11:25:19.029  2159  2252 D GnssLocationProvider: ELE   3, 22, 63,  4, 42, 40, 10, 17, 68,  9, 13, 40,
    static class GnssLocationProvider_ELE extends MeetPrintBase {
        GnssLocationProvider_ELE() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> int[] mSvidWithFlags ,  int数组  32 位
            keyword = "GnssLocationProvider: ELE";
            explain = "【 ELE是仰角 Elevation , 人与卫星 连接 与 水平面之间的夹角 仰角就是这颗卫星相对地平线的夹角  卫星在头顶是仰角 90度  仰角将从0°增加到90°，然后再减小到0° 】 【 SvStatusInfo.float[] mSvElevations; 】; ";

            codePath = null;
        }

    }


    //11-25 11:25:19.029  2159  2252 D GnssLocationProvider: AZM 133,142, 43, 19,119,205,357,326,240,237,298, 73,
    static class GnssLocationProvider_AZM extends MeetPrintBase {
        GnssLocationProvider_AZM() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: AZM";
            explain = "【 Azm是方位角，Azimuth  表示这颗卫星在哪个方向，东北，西北。。。具体多少度 ，俯视图 大地平面 人为圆心  正北为0度 卫星垂直映射到水平面 与原点之间的夹角  】 【 SvStatusInfo.float[] mSvAzimuths;; 】; ";

            codePath = null;
        }

    }


//11-25 11:25:19.029  2159  2252 D GnssLocationProvider: FRQ 1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1575420032,1602000000,

    static class GnssLocationProvider_FRQ extends MeetPrintBase {
        GnssLocationProvider_FRQ() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: FRQ";
            explain = "GnssLocationProvider.java 中  方法 void handleReportSvStatus(SvStatusInfo info) 【 FRQ 频率?  】 【 SvStatusInfo.float[] mSvCarrierFreqs;;; 】; ";

            codePath = null;
        }

    }


//    GnssLocationProvider: FLG  A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F, A F,
static class GnssLocationProvider_FLG extends MeetPrintBase {
    GnssLocationProvider_FLG() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "GnssLocationProvider: FLG";
        explain = "【 E=0位=含有星历数据  A=第1位=含有历书数据   U=第2位=卫星是否可用 F=第3位=是否含有频率信息】 【 SvStatusInfo.int[] mSvidWithFlags 中最低四位bit位的lag  】 【 SvStatusInfo.float[] mSvCarrierFreqs;;; 】; ";

        codePath = null;
    }

}


//  11-25 11:22:59.347  2159  2573 D GnssLocationProvider: GPS_STATUS_ENGINE_ON
static class GnssLocationProvider_GPS_STATUS_ENGINE_ON extends MeetPrintBase {
    GnssLocationProvider_GPS_STATUS_ENGINE_ON() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "GnssLocationProvider: GPS_STATUS_ENGINE_ON";
        explain = " GPS 获取卫星数据引擎 打开 ";

        codePath = null;
    }

}

// 11-25 11:22:59.499  2159  2573 D GnssLocationProvider: GPS_STATUS_ENGINE_OFF
static class GnssLocationProvider_GPS_STATUS_ENGINE_OFF extends MeetPrintBase {
    GnssLocationProvider_GPS_STATUS_ENGINE_OFF() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "GnssLocationProvider: GPS_STATUS_ENGINE_OFF";
        explain = " GPS 获取卫星数据引擎 关闭 ";

        codePath = null;
    }

}


// 11-25 12:26:45.124  2159  2573 D GnssLocationProvider: GPS_STATUS_SESSION_BEGIN
static class GnssLocationProvider_GPS_STATUS_SESSION_BEGIN extends MeetPrintBase {
    GnssLocationProvider_GPS_STATUS_SESSION_BEGIN() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "GnssLocationProvider: GPS_STATUS_SESSION_BEGIN";
        explain = " GPS 获取卫星数据Session 会话 开始  ";
        codePath = null;
    }

}

// 11-25 12:27:22.490  2159  2570 D GnssLocationProvider: GPS_STATUS_SESSION_END
static class GnssLocationProvider_GPS_STATUS_SESSION_END extends MeetPrintBase {
    GnssLocationProvider_GPS_STATUS_SESSION_END() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "GnssLocationProvider: GPS_STATUS_SESSION_END";
        explain = " GPS 获取卫星数据Session 会话结束 一般已经得到了一些位置卫星数据  ";
        codePath = null;
    }

}


// 11-25 12:27:40.939  2159  2252 D GnssLocationProvider: TTFF: 18367

    static class GnssLocationProvider_TTFF extends MeetPrintBase {
        GnssLocationProvider_TTFF() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: TTFF";
            explain = "冷启动完成时间  (毫秒 )// ttff的意思是Time to First Fix，就是到了一个完全陌生的地方，GPS里面没有存任何信息，" +
                    "\t// 时间也不对的情况下，从开机到得到坐标信息和时间信息的时间。(也叫冷启动时间）" +
                    "\t// 这个理想值是18秒。一般现代的GPS都可以在30秒定位。  冷启动完成时间 ";
            codePath = null;
        }

    }


//    Line 179915: 11-25 10:52:30.793  2159  2252 D LocationManagerService: incoming location: Location[network -22,814542,-47,059241 hAcc=18 et=+30m41s50ms alt=653.7999877929688 vAcc=2 sAcc=??? bAcc=??? {Bundle[{networkLocationType=wifi, noGPSLocation=Location[network -22,814542,-47,059241 hAcc=18 et=+30m41s50ms alt=653.7999877929688 vAcc=2 sAcc=??? bAcc=??? {Bundle[{networkLocationType=wifi}]}]}]}]


    static class LocationManagerService_Incoming_Location extends MeetPrintBase {
        LocationManagerService_Incoming_Location() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "LocationManagerService: incoming location: Location";
            explain = " 接收到的位置数据信息 ";
            codePath = null;
        }

    }


    //    Location mode: true
    static class Location_Mode_True extends CollectEndPrintBase {

        Location_Mode_True() {
            keyword = "Location mode: true";
            explain = "用户的GPS 开关打开的 ";
            codePath = null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }
    }


    //    Location mode: true
    static class Location_Mode_False extends CollectEndPrintBase {

        Location_Mode_False() {
            keyword = "Location mode: false";
            explain = "用户的GPS 关闭打开的 ";
            codePath = null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }
    }

//11-25 09:39:39.578  2159  2252 D GnssLocationProvider: gnssNameCb: name=qcom;MPSS.AT.4.3.1-00319-NICOBAR_GEN_PACK-2;
static class GnssLocationProvider_GnssNameCb extends CollectEndPrintBase {

    GnssLocationProvider_GnssNameCb() {
        keyword = "GnssLocationProvider: gnssNameCb:";
        explain = "GPS 的 固件信息 ";
        codePath = null;
    }

    @Override
    public String toReturnFixString(String lineContent) {
        return lineContent;
    }

    @Override
    public String toGetDiffSubString(String lineContent) {
        return lineContent;
    }
}


// 11-25 10:24:30.532  2159  2252 D GnssLocationProvider: SIM MCC/MNC is available: 72403

    static class GnssLocationProvider_SIM_Available extends MeetPrintBase {
        GnssLocationProvider_SIM_Available() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: SIM MCC/MNC is available:";
            explain = " 插入有效的 sim卡 读取到了 mcc/mnc  ";
            codePath = null;
        }

    }


//11-25 11:25:11.096  2159  2252 D GnssLocationProvider: setRequest ProviderRequest[ON interval=0]
//11-25 11:25:11.096  2159  2252 D GnssLocationProvider: startNavigating
//11-25 11:25:11.096  2159  2252 D GnssLocationProvider: setting position_mode to standalone

    static class GnssLocationProvider_ProviderRequest_ON extends MeetPrintBase {
        GnssLocationProvider_ProviderRequest_ON() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: setRequest ProviderRequest[ON";
            explain = " 发送位置请求  ";
            codePath = null;
        }

    }

    static class GnssLocationProvider_StartNavigating extends MeetPrintBase {
        GnssLocationProvider_StartNavigating() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: startNavigating";
            explain = " 开始处理位置导航 获取位置信息  ";
            codePath = null;
        }

    }

//
//// these need to match GnssStatusValue enum in IGnssCallback.hal
//private static final int GPS_STATUS_NONE = 0;
//    private static final int GPS_STATUS_SESSION_BEGIN = 1;
//    private static final int GPS_STATUS_SESSION_END = 2;
//    private static final int GPS_STATUS_ENGINE_ON = 3;
//    private static final int GPS_STATUS_ENGINE_OFF = 4;


//11-25 12:28:12.040  2159  2573 V GnssLocationProvider: reportStatus status: 2

    static class GnssLocationProvider_ReportStatus extends MeetPrintBase {
        GnssLocationProvider_ReportStatus() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "GnssLocationProvider: reportStatus status:";
            explain = " GPS导航状态 [ 1=会话开始  2=会话结束  3=GPS定位引擎打开  4=GPS定位引擎关闭 ]  ";
            codePath = null;
        }

    }

// 11-25 12:27:57.941  2159  2252 I LocationManagerService: Sending Location to com.google.android.apps.maps, Provider: gps, Lat: -22.82576322, Long: -47.07181234, Bearing: 0.0, Accuracy: 73.67326, Lprovider: gps, fine: yes
static class LocationManagerService_Sending_Location extends MeetPrintBase {
    LocationManagerService_Sending_Location() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "LocationManagerService: Sending Location to";
        explain = " 给对应的 应用 发送 Location 数据   ";
        codePath = null;
    }

}

// 11-25 10:17:06.663  2052  7048 I LocationManagerService: Started, Location UpdateRecord for com.schibsted.bomnegocio.androidApp, Provider: network, mInterval: 10000, mFastestInterval: 10000, mSmallestDisplacement: 1.0, mNumUpdates: 2147483647, Receiver: Reciever[f43dead listener monitoring location: false]
//

    static class LocationManagerService_Started_Location extends MeetPrintBase {
        LocationManagerService_Started_Location() {
            //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
            // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
            keyword = "LocationManagerService: Started, Location UpdateRecord for";
            explain = " 应用开始位置请求   ";
            codePath = null;
        }

    }

// 11-25 09:40:49.814  2159  3134 I LocationManagerService: Stopped, Location UpdateRecord for com.btg.pactual.digital.mobile, Provider: gps, mInterval: 10000, mFastestInterval: 10000, mSmallestDisplacement: 1.0, mNumUpdates: 2147483647, Receiver: Reciever[c04a469 listener UpdateRecord[gps com.btg.pactual.digital.mobile(10216 background) Request[ACCURACY_FINE gps requested=+10s0ms fastest=+10s0ms] null] UpdateRecord[network com.btg.pactual.digital.mobile(10216 background) Request[POWER_LOW network requested=+10s0ms fastest=+10s0ms] null] monitoring location: false]
static class LocationManagerService_Stopped_Location extends MeetPrintBase {
    LocationManagerService_Stopped_Location() {
        //   【index|8|8|8--PRN伪噪声码|76543【第3位是否有载波频率】2【第2,该卫星是否可用】1【第1位:是否有历书数据】0【第0位:是否有星历数据】--】
        // SvStatusInfo=> float[] mSvAzimuths,  float数组  32 位
        keyword = "LocationManagerService: Stopped, Location UpdateRecord for";
        explain = " 应用 停止了 位置请求    ";
        codePath = null;
    }

}




// 06-15 13:54:13.430  1376  2305 D WifiStateMachine: connectToUserSelectNetwork netId 37, uid 1000, forceReconnect = true

    static class Authentication_With_Time_Out extends MultiKeyWordPrintBase {

        Authentication_With_Time_Out() {
            keyword = "wlan0: Authentication with";
            explain = "[ 手机请求认证超时 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("wlan0: Authentication with");
            keyList.add("timed out");
            return keyList;
        }
    }

	//[scheduler_threa][0x156797e41ce][07:50:11.351480]wlan: [13162:D:PE] lim_send_extended_chan_switch_action_frame: 3752: Send Ext channel Switch to :44:78:3e:43:89:66 with swcount 10, swmode 1 , newchannel 169 newops 125

	    static class Lim_Send_Extended_Chan_Switch_Action_Frame_NewChannel extends MultiKeyWordPrintBase {

        Lim_Send_Extended_Chan_Switch_Action_Frame_NewChannel() {
            keyword = "lim_send_extended_chan_switch_action_frame";
            explain = "[ 发送给设备通知当前热点的信道已经变更 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("lim_send_extended_chan_switch_action_frame");
            keyList.add("Send Ext channel Switch to");
			keyList.add("newchannel");
            return keyList;
        }
    }





    // //  19:14:08.950573  [scheduler_threa][0x38481ac4795][19:14:08.946892]wlan: [2926:IH:SAP] wlansap_pre_start_bss_acs_scan_callback: 256: Channel selected = 56
    static class Wlansap_Pre_Start_Bss_Acs_Scan_Callback_Channel_Selected extends MultiKeyWordPrintBase {

        Wlansap_Pre_Start_Bss_Acs_Scan_Callback_Channel_Selected() {
            keyword = "wlansap_pre_start_bss_acs_scan_callback";
            explain = "[ 当前最终ACS选择的信道列表编号 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("wlansap_pre_start_bss_acs_scan_callback");
            keyList.add("Channel selected =");
            return keyList;
        }
    }



    // __wlan_hdd_cfg80211_do_acs
    //   19:09:46.279738  [hostapd][0x38355118ca2][19:09:46.274720]wlan: [8190:D:HDD] __wlan_hdd_cfg80211_do_acs: 2608: ACS config PCL: len: 20

    static class Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL extends MultiKeyWordPrintBase {

        Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL() {
            keyword = "__wlan_hdd_cfg80211_do_acs";
            explain = "[ ACS 自动信道选择中 PCL 选择的信道列表 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("__wlan_hdd_cfg80211_do_acs");
            keyList.add("ACS config PCL:");
            return keyList;
        }
    }


// __wlan_hdd_cfg80211_do_acs: 2614: channel:56, weight:255
    static class Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_Channel_Weight extends MultiKeyWordPrintBase {

        Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_Channel_Weight() {
            keyword = "__wlan_hdd_cfg80211_do_acs";
            explain = "[ ACS 时 PCL 列表项  信道选项值 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("__wlan_hdd_cfg80211_do_acs");
            keyList.add("channel");
            keyList.add("weight");
            return keyList;
        }
    }


    // __wlan_hdd_cfg80211_do_acs: 2673: ACS channel list: len:
    static class Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_ACS_Channel_List extends MultiKeyWordPrintBase {

        Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_ACS_Channel_List() {
            keyword = "__wlan_hdd_cfg80211_do_acs";
            explain = "[ ACS 可选信道的长度  1.2.3.4..12 ..36..40.149..153..157 具体值到对应文件 ACS channel list: len: 查找 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("__wlan_hdd_cfg80211_do_acs");
            keyList.add("ACS channel list:");
            return keyList;
        }
    }


//19:15:17.374677  [hostapd][0x384cff84c97][19:15:17.363819]wlan: [8927:D:POLICY_MGR] policy_mgr_pcl_modification_for_sap: 534: nol modified pcl len:20

    static class Policy_Mgr_Pcl_Modification_For_Sap_Pcl_Len extends MultiKeyWordPrintBase {

        Policy_Mgr_Pcl_Modification_For_Sap_Pcl_Len() {
            keyword = "policy_mgr_pcl_modification_for_sap";
            explain = "[ 手机请求认证超时 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("policy_mgr_pcl_modification_for_sap");
            keyList.add("modified pcl len:");
            return keyList;
        }
    }

//19:15:17.374685  [hostapd][0x384cff84ce8][19:15:17.363823]wlan: [8927:D:POLICY_MGR] policy_mgr_pcl_modification_for_sap: 537: chan:56 weight:255
//19:15:17.374693  [hostapd][0x384cff84d33][19:15:17.363827]wlan: [8927:D:POLICY_MGR] policy_mgr_pcl_modification_for_sap: 537: chan:60 weight:255
//19:15:17.374701  [hostapd][0x384cff84d84][19:15:17.363831]wlan: [8927:D:POLICY_MGR] policy_mgr_pcl_modification_for_sap: 537: chan:64 weight:255

    static class Policy_Mgr_Pcl_Modification_For_Sap_Chan_Weight extends MultiKeyWordPrintBase {

        Policy_Mgr_Pcl_Modification_For_Sap_Chan_Weight() {
            keyword = "policy_mgr_pcl_modification_for_sap";
            explain = "[ 手机请求认证超时 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("policy_mgr_pcl_modification_for_sap");
            keyList.add("chan");
            keyList.add("weight");
            return keyList;
        }
    }




    //  WifiCountryCode: Succeeded to set country code to: US
    //  WifiCountryCode: Succeeded to set country code to: BR
    static class WifiCountryCode_CountryCode extends FixLong_Diff_Pre_PrintBase {
        WifiCountryCode_CountryCode() {
            keyword = "Succeeded to set country code to:";
            explain = "设置wifi中的countryCode [可能影响频谱 DFS等] ";
            codePath = "http://host810096584.s248.pppf.com.cn/tool/182/";
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            if(lineContent != null && lineContent.indexOf("Succeeded to set country code to:") > 0){
                return lineContent.substring(lineContent.indexOf("Succeeded to set country code to:"));
            }
            return null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

    }






    // Bug2Go-BugReportService: onStartCommand - action: motorola.intent.action.BUG2GO.BUGREPORT.START
    static class BugReportService_onStartCommand extends CollectEndPrintBase {

        BugReportService_onStartCommand() {
            keyword = "Bug2Go-BugReportService: onStartCommand - action: motorola.intent.action.BUG2GO.BUGREPORT.START";
            explain = "Bug2Go 启动上报Log时间点 ";
            codePath = null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }
    }


    //  06-12 12:27:07.722  9370  9370 I hostapd : wlan0: IEEE 802.11 Hardware does not support configured channel
    static class Hardware_Does_Dot_Support_Channel extends MeetPrintBase {   // 模板
        Hardware_Does_Dot_Support_Channel() {
            keyword = "IEEE 802.11 Hardware does not support configured channel";
            explain = "当前硬件没有可支持的信道 无法工作 查看国家码信道";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-shm2017/kernel/net/wireless/db.txt";
        }
    }


    //  06-12 12:27:07.671  9370  9370 D QCSDK   : Updated:country_code=IE
    static class Updated_Country_Code extends MeetPrintBase {   // 模板
        Updated_Country_Code() {
            keyword = "Updated:country_code=";
            explain = "设置国家码";
            codePath = "https://wenku.baidu.com/view/88021e4cc850ad02de8041ac.html";
        }
    }


    //  06-12 12:27:07.723  9370  9370 I hostapd : wlan0: interface state COUNTRY_UPDATE->DISABLED
    static class COUNTRY_UPDATE_DISABLED extends MeetPrintBase {   // 模板
        COUNTRY_UPDATE_DISABLED() {
            keyword = "interface state COUNTRY_UPDATE->DISABLED";
            explain = "手机设置的国家码 可能没有可用5G信道 造成无法打开";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-shm2017/kernel/net/wireless/db.txt";
        }
    }



    //  05-21 18:00:24.879  1227  2217 D SoftApManager: Soft AP is stopped
    static class Soft_AP_Is_Stopped extends MeetPrintBase {   // 模板
        Soft_AP_Is_Stopped() {
            keyword = "SoftApManager: Soft AP is stopped";
            explain = "手机热点 停止工作 ";
            codePath = null;
        }
    }


    // 05-21 17:38:04.935  1227  2217 D SoftApManager: Soft AP is started
    static class Soft_AP_Is_Started extends MeetPrintBase {   // 模板
        Soft_AP_Is_Started() {
            keyword = "Soft AP is started";
            explain = "手机热点 开始工作 ";
            codePath = null;
        }
    }

    // 05-21 17:50:24.380     0     0 I [scheduler_threa][0x200831f9e2e][17:50:24.833005] wlan: [2199:I:IPA] __wlan_ipa_wlan_evt: 1876: sap_num_connected_sta=0
    static class Sap_Num_Connected_Sta extends MeetPrintBase {   // 模板
        Sap_Num_Connected_Sta() {
            keyword = "sap_num_connected_sta=";
            explain = "手机热点连接的客户端数量 ";
            codePath = null;
        }
    }


    //   05-21 18:00:24.518  1227  2217 I SoftApManager: Timeout message received. Stopping soft AP.
    static class SoftApManager_Timeout_Message extends MeetPrintBase {   // 模板
        SoftApManager_Timeout_Message() {
            keyword = "SoftApManager: Timeout message received. Stopping soft AP";
            explain = "手机热点由于10分钟没有客户端连接 自动关闭 ";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-sh2019/frameworks/opt/net/wifi/service/java/com/android/server/wifi/SoftApManager.java#837";
        }
    }



    //08:04:45.221650  [scheduler_threa][0x5d51dba174f][08:04:45.215536]wlan: [5079:I:HDD] hdd_send_association_event: 1388: wlan: 90:73:5a:cd:ac:0e connected to 88:b1:e1:28:91:c0    可能是断开事件
//08:05:15.341571  [scheduler_threa][0x5d540317dd2][08:05:15.332745]wlan: [5079:I:HDD] hdd_send_association_event: 1463: wlan: disconnected
    static class HDD_Hdd_send_association_event extends CollectEndPrintBase {   // 模板
        HDD_Hdd_send_association_event() {
            keyword = "I:HDD] hdd_send_association_event";
            explain = "";
            codePath = null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            if(!lineContent.contains("wlan:") && !lineContent.contains("[:")){
                return null;
            }
            String str1 = lineContent.substring(0,lineContent.indexOf("["));
            String str2 = lineContent.substring(lineContent.indexOf("wlan:")+ "wlan:".length());
            String str3 = "";
            if(lineContent.contains("disconnected")){
                str3 = "  断开事件";
            } else if(lineContent.contains("connected")){
                str3 = "  连接事件";
            }
            String result = str1 + str2 + str3;
            if(result.contains("   0     0 I ")){
                result =   result.replaceAll("   0     0 I ","");
            }
            return result;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent;
        }
    }


    // 06-21 12:10:50.843  1493  3070 D NetworkMonitor/NetworkAgentInfo [WIFI () - 115]: PROBE_DNS www.google.com 5280ms OK 172.217.28.4
    static class PROBE_DNS_Google extends MultiKeyWordPrintBase {

        PROBE_DNS_Google() {
            keyword = "PROBE_DNS www.google.com";
            explain = "[ 从谷歌得到回馈 证明有网络能力 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("PROBE_DNS www.google.com");
            keyList.add(" OK ");
            return keyList;
        }
    }


    // 06-21 12:11:00.867  1493  3070 D NetworkMonitor/NetworkAgentInfo [WIFI () - 115]: PROBE_HTTPS https://www.google.com/generate_204 Probe failed with exception java.net.SocketTimeoutException: failed to connect to /172.217.28.4 (port 443) from /192.168.0.249 (port 39400) after 10000ms
    static class Generate_204_Probe_Failed extends MeetPrintBase {   // 模板
        Generate_204_Probe_Failed() {
            keyword = "generate_204 Probe failed";
            explain = "【从谷歌204网站检测网络连接失败】";
            codePath = null;
        }
    }




// 06-21 12:11:00.873  1493  2227 I WifiStateMachine: Temporarily disabling network because ofno-internet access
    static class No_Internet_Access extends MeetPrintBase {   // 模板
        No_Internet_Access() {
            keyword = "no-internet access";
            explain = "【当前没有网络能力】";
            codePath = null;
        }
    }


// 07-10 07:42:42.160 25447 25447 D QCSDK   : Updated:max_num_sta=10
    static class Updated_Max_Num_Sta extends MeetPrintBase {   // 模板
        Updated_Max_Num_Sta() {
            keyword = "Updated:max_num_sta=";
            explain = "【更新当前热点最多连接设备的个数】";
            codePath = null;
        }
    }



    static class Added_Drop_Box_Entry_IFI_CLASS_D_DIAGNOSTICS extends MeetPrintBase {   // 模板
        Added_Drop_Box_Entry_IFI_CLASS_D_DIAGNOSTICS() {
            keyword = "Added Drop box entry - WIFI_CLASS_D_DIAGNOSTICS";
            explain = "引起WIFI_CLASS_D_DIAGNOSTICS异常(reasonCode 不为0,3 断开时rssi较好情况)";
            codePath = null;
        }
    }


    static class Trying_to_associate_with_SSID extends MeetPrintBase {   // 模板
        Trying_to_associate_with_SSID() {
            keyword = "wpa_supplicant: wlan0: Trying to associate with SSID";
            explain = "尝试连接该网络";
            codePath = null;
        }
    }


    // 03-22 12:09:49.441  2478  4564 D WifiStateMachine: setTargetBssid set to f0:7f:06:d8:7e:9d key=ShawMobileHotspotWPA_EAP-0
    static class SetTargetBssid_Set_To extends MeetPrintBase {   // 模板
        SetTargetBssid_Set_To() {
            keyword = "setTargetBssid set to";
            explain = "【 WifiStateMachine  选取评估的目标网络 】";
            codePath = null;
        }
    }

    // 03-11 12:24:45.708  1017  2843 V LocSvc_LBSApiV02:  measurementAge[7] = 0 servingAccessPoint[7] = 1
    static class ServingAccessPoint1 extends MultiKeyWordPrintBase {

        ServingAccessPoint1() {
            keyword = "servingAccessPoint";
            explain = "[ 上面的passpoint网络可用 ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("servingAccessPoint");
            keyList.add("] = 1 ");
            return keyList;
        }
    }

    // 03-20 12:36:35.474  1022  4907 V LocSvc_LBSApiV02: injectWifiApInfo:560] ssid[10] is ShawMobileHotspot

    static class InjectWifiApInfo_Ssid extends MultiKeyWordPrintBase {

        InjectWifiApInfo_Ssid() {
            keyword = "injectWifiApInfo";
            explain = "[ 注入 wifi 配置信息  搜素 【空格servingAccessPoint】  ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("injectWifiApInfo");
            keyList.add("ssid");
            return keyList;
        }
    }



// 03-11 12:24:45.708  1861  2470 D PasspointManager: Matched ShawMobileHotspot to 302490FQDN-AKA.com as Roaming Provider
static class PasspointManager_Matched extends MeetPrintBase {   // 模板
    PasspointManager_Matched() {
        keyword = "PasspointManager: Matched";
        explain = "【 匹配到Passpoint网络 】";
        codePath = null;
    }
}





    // 03-22 12:07:35.532  2478  4564 V WifiConfigManager: Set network candidate scan result SSID: ShawMobileHotspot
static class Set_Network_Candidate_Scan_Result extends MeetPrintBase {   // 模板
    Set_Network_Candidate_Scan_Result() {
        keyword = "Set network candidate scan result";
        explain = "【 WifiConfigManager 选取评估的候选网络 】";
        codePath = null;
    }
}


    //03-22 12:07:35.014  2478  4564 D WifiConfigManager: Removing passpoint network config ShawMobileHotspotWPA_EAP-0
    static class Removing_Passpoint_Network_Config extends MeetPrintBase {   // 模板
        Removing_Passpoint_Network_Config() {
            keyword = "Removing passpoint network config";
            explain = "【 移除所有 passpoint网络  】";
            codePath = null;
        }
    }



//03-22 12:07:35.013  2478  4564 V WifiConfigManager: Removing all passpoint or ephemeral configured networks  【移除所有passpoint网络】
    static class Removing_All_Passpoint_Ephemeral_Configured_Networks extends MeetPrintBase {   // 模板
        Removing_All_Passpoint_Ephemeral_Configured_Networks() {
            keyword = "Removing all passpoint or ephemeral configured networks";
            explain = "【 移除所有 passpoint网络 和 临时网络 】";
            codePath = null;
        }
    }



    //  05-17 08:23:40.393  7200  7220 I WifiTracker: Fetched scan results: [SSID: Google Mesh, BSSID: e4:f0 ........
    static class passpoint_yes extends FixLong_Diff_Pre_PrintBase {
        passpoint_yes() {
            keyword = "passpoint: yes";
            explain = " 发现 passpoint 为 yes的热点  passpoint: yes ";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            if(lineContent != null && lineContent.indexOf("WifiTracker: Fetched scan results:") > 0){
                return lineContent.substring(lineContent.indexOf("WifiTracker: Fetched scan results:"));
            }
            return null;
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }

    }




    // 05-14 00:40:35.610  1258  6440 D PasspointManager: Added/updated Passpoint configuration: 001010FQDN-AKA.com by 10066
    // 05-14 00:40:35.628  1258  6440 D PasspointManager: Added/updated Passpoint configuration: 001010FQDN-SIM.com by 10066
    static class PasspointManager_Added_Updated_Passpoint_Configuration extends FixLong_Diff_Pre_PrintBase {   // 模板

        PasspointManager_Added_Updated_Passpoint_Configuration() {
            keyword = "PasspointManager: Added/updated Passpoint configuration:";
            explain = "【 添加或更新passpoint配置到 WifiConfigStore.xml 配置文件中 】";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent.substring(lineContent.indexOf("Passpoint configuration:"));
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }


    }


    // 06-05 06:47:04.977  6500  6500 D wpa_supplicant: nl80211: Regulatory information - country=US (DFS-FCC)
    static class Regulatory_Information_Country extends MeetPrintBase {   // 模板
        Regulatory_Information_Country() {
            keyword = "Regulatory information - country";
            explain = "【 设置国家对应的国家信道范围 DFS信道 等 】";
            codePath = null;
        }
    }

//06-05 06:47:04.977  6500  6500 D wpa_supplicant: nl80211: 5170-5250 @ 80 MHz 24 mBm
//06-05 06:47:04.977  6500  6500 D wpa_supplicant: nl80211: 5250-5330 @ 80 MHz 24 mBm (DFS)
//06-05 06:47:04.977  6500  6500 D wpa_supplicant: nl80211: 5490-5730 @ 160 MHz 24 mBm (DFS)
    static class Wpa_Supplicant_Nl80211_MBm extends MultiKeyWordPrintBase {

    Wpa_Supplicant_Nl80211_MBm() {
            keyword = "wpa_supplicant: nl80211:";
            explain = "[ 国家允许使用的信道范围  DFS  ]";
            codePath = null;
        }

        @Override
        public ArrayList<String> getKeyWordArrayList() {
            ArrayList<String> keyList = new  ArrayList<String>();
            keyList.add("wpa_supplicant: nl80211:");
            keyList.add(" mBm");
            return keyList;
        }
    }



    // 21:51:57.957978  [scheduler_threa][0x52f47be1da6][21:51:57.950154]wlan: [2194:D:PE] lim_process_disassoc_frame: 154: Received Disassoc frame for Addr: 90:73:5a:ea:8e:98(mlm state=eLIM_MLM_BSS_STARTED_STATE, sme state=18 RSSI=-49),with reason code 8 [eSIR_MAC_DISASSOC_LEAVING_BSS_REASON] from dc:72:9b:43:95:0d
    static class Received_Disassoc_Frame_For_Addr extends MeetPrintBase {   // 模板
        Received_Disassoc_Frame_For_Addr() {
            keyword = "Received Disassoc frame for Addr";
            explain = "【 手机接收到 断开关联帧  将断开与别的设备的热点连接 】";
            codePath = null;
        }
    }




    // 05-10 13:18:59.349  2622  2622 D IntentBroadcaster: Broadcasting intent ACTION_SIM_STATE_CHANGED for mCardIndex: 0
    static class IntentBroadcaster_Broadcasting_Intent extends MeetPrintBase {   // 模板
        IntentBroadcaster_Broadcasting_Intent() {
            keyword = "IntentBroadcaster: Broadcasting intent";
            explain = "程序发送广播[  可能是重发广播rebroadcast 有些广播会在屏幕解锁后再次发送一次广播 这类广播 包含  EXTRA_REBROADCAST_ON_UNLOCK 等 ]";
            codePath = null;
        }
    }

    // 05-10 13:12:30.463  2606  2782 D IntentBroadcaster: Broadcasting and adding intent for rebroadcast: android.intent.action.SIM_STATE_CHANGED
    static class IntentBroadcaster_Rebroadcast extends MeetPrintBase {   // 模板
        IntentBroadcaster_Rebroadcast() {
            keyword = "IntentBroadcaster: Broadcasting and adding intent for rebroadcast";
            explain = "【 发送广播,并且会在设备解锁之后 重新再发送一次广播  重发广播包含 EXTRA_REBROADCAST_ON_UNLOCK 】";
            codePath = null;
        }
    }




    // 05-10 13:18:51.620  1800  1800 D WifiService: resetting networks because SIM was removed
    static class WifiService_Resetting_Networks_Because_SIM_Was_Removed extends MeetPrintBase {   // 模板
        WifiService_Resetting_Networks_Because_SIM_Was_Removed() {
            keyword = "WifiService: resetting networks because SIM was removed";
            explain = "接收到SIM变化广播 SIM卡被拔去[与Sim卡相关的passpoint网络将无法连接]";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-sh2019/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java#558";
        }
    }

    // 05-10 13:18:52.791  1800  1800 D WifiService: resetting networks because SIM was loaded
    static class WifiService_Resetting_Networks_Because_SIM_Was_Loaded extends MeetPrintBase {   // 模板
        WifiService_Resetting_Networks_Because_SIM_Was_Loaded() {
            keyword = "WifiService: resetting networks because SIM was loaded";
            explain = "接收到SIM变化广播 SIM卡已插入卡槽[与Sim卡相关的可用passpoint网络将进行ANPQ查询后自动候选 Set network candidate ]";
            codePath = "https://gerrit.mot.com/#/c/1352267/";
        }
    }

    // 05-14 21:09:54.466  1553  2238 I WifiVendorHal: Driver: 5.2.03.7K Firmware: FW:3.0.1.559.0 HW:HW_VERSION=40670000.
    static class WifiVendorHal_Driver_Firmware extends OncePrintBase {   // 模板
        WifiVendorHal_Driver_Firmware() {
            keyword = "WifiVendorHal: Driver:";
            explain = "当前固件Fireware版本";
            codePath = null;
        }

//        @Override
//        public ArrayList<String> getKeyWordArrayList() {
//            ArrayList<String>  multiKeyList = new ArrayList<String>();
//            multiKeyList.add("WifiVendorHal: Driver:");
//            multiKeyList.add("HW:HW_VERSION");
//            return multiKeyList;
//        }
    }




    // 05-14 21:08:44.498  1553  2238 D SoftApManager: Channel switched. Frequency: 2452 Bandwidth: 2
    static class SoftApManager_Channel_Switched_Frequency extends MeetPrintBase {   // 模板
        SoftApManager_Channel_Switched_Frequency() {
            keyword = "SoftApManager: Channel switched. Frequency:";
            explain = "热点的工作信道变更 ";
            codePath = null;
        }
    }


    // 05-14 21:11:52.730  7914  7914 E hostapd : ACS: Possibly channel configuration is invalid
    static class Possibly_Channel_Configuration_Is_Invalid extends MeetPrintBase {   // 模板
        Possibly_Channel_Configuration_Is_Invalid() {
            keyword = "Possibly channel configuration is invalid";
            explain = "【 配置的信道channel不可用 】 ";
            codePath = null;
        }
    }



// 05-30 10:34:37.252 20161 20161 I hostapd : wlan0: DFS-CAC-START freq=5660 chan=132 chan_offset=0 width=0 seg0=0 seg1=0 cac_time=60s
static class Wlan0_DFS_CAC_START extends MeetPrintBase {   // 模板
    Wlan0_DFS_CAC_START() {
        keyword = "wlan0: DFS-CAC-START";
        explain = "【 DFS 信道检测开始  】 ";
        codePath = null;
    }
}


// 05-30 10:35:38.538 20161 20161 I hostapd : wlan0: DFS-CAC-COMPLETED success=1 freq=5660 ht_enabled=0 chan_offset=0 chan_width=0 cf1=0 cf2=0
static class Wlan0_DFS_CAC_COMPLETED extends MeetPrintBase {   // 模板
    Wlan0_DFS_CAC_COMPLETED() {
        keyword = "wlan0: DFS-CAC-COMPLETED";
        explain = "【 DFS 信道检测结束  】 ";
        codePath = null;
    }
}



// 05-14 21:12:36.578  8012  8012 I hostapd : wlan0: ACS-COMPLETED freq=2457 channel=10
static class Wlan0_ACS_COMPLETED extends MeetPrintBase {   // 模板
    Wlan0_ACS_COMPLETED() {
        keyword = "wlan0: ACS-COMPLETED";
        explain = "【 ACS 自动信道选择完成工作 】 ";
        codePath = null;
    }
}




    //05-14 21:12:36.578  8012  8012 I hostapd : nl80211: ACS Results: PCH: 10 SCH: 0 BW: 20 VHT0: 0 VHT1: 0 HW_MODE: 1
static class Nl80211_ACS_Results extends MeetPrintBase {   // 模板
    Nl80211_ACS_Results() {
        keyword = "nl80211: ACS Results:";
        explain = "【 Auto Channel Select 自动信道选择结果 】 ";
        codePath = null;
    }
}





//07-06 19:09:53.328  8354  8354 I hostapd : Channel 56 (primary) not allowed for AP mode
static class Not_Allowed_For_AP_Mode extends MeetPrintBase {   // 模板
    Not_Allowed_For_AP_Mode() {
        keyword = "not allowed for AP mode";
        explain = "【 当前信道并不能被热点AP使用 】 ";
        codePath = null;
    }
}


//07-06 19:09:53.328  8354  8354 E hostapd : ACS picked unusable channels
static class ACS_Picked_Unusable_Channels extends MeetPrintBase {   // 模板
    ACS_Picked_Unusable_Channels() {
        keyword = "ACS picked unusable channels";
        explain = "【 ACS自动信道选择 选中了一个不可用的信道 】 ";
        codePath = null;
    }
}

//07-06 19:09:53.328  8354  8354 I hostapd : wlan0: ACS-FAILED
static class Wlan0_ACS_FAILED extends MeetPrintBase {   // 模板
    Wlan0_ACS_FAILED() {
        keyword = "wlan0: ACS-FAILED";
        explain = "【 ACS自动信道选择 执行失败 】 ";
        codePath = null;
    }
}


//07-06 19:09:53.328  8354  8354 E hostapd : Interface initialization failed
static class Hostapd_Interface_Initialization_Failed extends MeetPrintBase {   // 模板
    Hostapd_Interface_Initialization_Failed() {
        keyword = "hostapd : Interface initialization failed";
        explain = "【 热点接口初始化失败  热点不可用  】 ";
        codePath = null;
    }
}


//07-06 19:09:53.328  8354  8354 I hostapd : wlan0: interface state COUNTRY_UPDATE->DISABLED


//07-06 19:09:53.328  8354  8354 I hostapd : wlan0: AP-DISABLED
static class Hostapd_wlan0_AP_DISABLED extends MeetPrintBase {   // 模板
    Hostapd_wlan0_AP_DISABLED() {
        keyword = "hostapd : wlan0: AP-DISABLED";
        explain = "【 热点不可用事件  】 ";
        codePath = null;
    }
}

//07-06 19:09:53.328  8354  8354 E hostapd : ACS: Possibly channel configuration is invalid
static class ACS_Possibly_Channel_Invalid extends MeetPrintBase {   // 模板
    ACS_Possibly_Channel_Invalid() {
        keyword = "ACS: Possibly channel configuration is invalid";
        explain = "【  可选的ACS 信道 不可用  】 ";
        codePath = null;
    }
}




//05-10 13:16:09.349  2424  2424 D KeyguardViewMediator: handleNotifyScreenTurnedOff
//05-10 13:16:10.088  2424  2424 D KeyguardViewMediator: handleNotifyScreenTurnedOn

    static class KeyguardViewMediator_HandleNotifyStartedGoingToSleep extends MeetPrintBase {

        KeyguardViewMediator_HandleNotifyStartedGoingToSleep() {
            keyword = "KeyguardViewMediator: handleNotifyStartedGoingToSleep";
            explain = "【 设备 开始进入睡眠 】";
            codePath = null;
        }

    }



//05-10 13:16:09.349  2424  2424 D KeyguardViewMediator: handleNotifyScreenTurnedOff
//05-10 13:16:10.088  2424  2424 D KeyguardViewMediator: handleNotifyScreenTurnedOn

    static class KeyguardViewMediator_handleNotifyScreenTurned extends MeetPrintBase {

        KeyguardViewMediator_handleNotifyScreenTurned() {
            keyword = "KeyguardViewMediator: notifyStartedWakingUp";
            explain = "【 设备解锁 开始醒来】";
            codePath = null;
        }

    }




    // =============================Base 类开始====================================

    static class MeetPrintBase extends CommonBase2AbstractKeyEntry {  // 默认的Log
        MeetPrintBase() {

        }
    }


    static class OncePrintBase extends CommonBase2AbstractKeyEntry {
        OncePrintBase() {
            justPrintOnce = true;
        }
    }


    static abstract class MultiKeyWordPrintBase extends CommonBase2AbstractKeyEntry {
        MultiKeyWordPrintBase() {
            keywordArr = new ArrayList<String>();
            keywordArr.addAll(getKeyWordArrayList());
        }

        abstract public ArrayList<String> getKeyWordArrayList();  // 用于返回包含关键字的List数组
    }


    static abstract class CollectEndPrintBase extends CommonBase2AbstractKeyEntry {
        CollectEndPrintBase() {
            logArrayPrintEnd = new ArrayList<String>();
            shouldFixLog = true;     //  最后添加到一起的Log  也需要对当前的 Log 进行过滤 以取到 核心信息

        }


        abstract public String toReturnFixString(String lineContent);  // 用于返回那些 需要打印在最后的Log的模样

        abstract public String toGetDiffSubString(String lineContent);  // 用于判断是否包含Log , 包含不打印  不包含打印

        @Override
        public String fixCurrentLog(String lineContent) {
            return toReturnFixString(lineContent);
        }


        @Override
        public void doAfterReadOverFile() {
            insertArrayToHead(analysisStringArr, toRemoveSame(logArrayPrintEnd));
            logArrayPrintEnd.clear();
        }

        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }
    }


    static abstract class CollectManyFromOneEndPrintBase extends CommonBase2AbstractKeyEntry {
        CollectManyFromOneEndPrintBase() {
            logArrayPrintEnd = new ArrayList<String>();
            shouldFixLog = true;     //  最后添加到一起的Log  也需要对当前的 Log 进行过滤 以取到 核心信息
            getManyInfoFromOneLine = true;
        }


        abstract public ArrayList<String> getManyInfoFromOneLineMethod_Imple(String lineContent);  // 用于判断是否包含Log , 包含不打印  不包含打印

        @Override
        public ArrayList<String> getManyInfoFromOneLineMethod(String lineContent) {
            return getManyInfoFromOneLineMethod_Imple(lineContent);
        }

        @Override
        public void doAfterReadOverFile() {
            insertArrayToHead(analysisStringArr, toRemoveSame(logArrayPrintEnd));
            logArrayPrintEnd.clear();
        }
    }

    //set集合去重，不改变原有的顺序
    public static ArrayList<String> toRemoveSame(ArrayList<String> list){
        //System.out.println("list = [" + list.toString() + "]");
        ArrayList<String> listNew=new ArrayList<String>();
        Set set=new HashSet();
        for (String str:list) {
            if(set.add(str)){
                listNew.add(str);
            }
        }
        return listNew;
    }


    static abstract class FixLongPrintBase extends CommonBase2AbstractKeyEntry {
        FixLongPrintBase() {
            shouldFixLog = true;
        }

        @Override
        public String fixCurrentLog(String lineContent) {
            return toReturnFixString(lineContent);
        }

        abstract public String toReturnFixString(String lineContent);

    }

    static abstract class FixLong_Diff_Pre_PrintBase extends CommonBase2AbstractKeyEntry {
        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串

        abstract public String toGetDiffSubString(String lineContent);
        abstract public String toReturnFixString(String lineContent);


        FixLong_Diff_Pre_PrintBase() {
            shouldFixLog = true;
        }
        @Override
        public String fixCurrentLog(String lineContent) {
            String returnStr = toReturnFixString(lineContent);
            //System.out.println("FixLong_Diff_Pre_PrintBase - fixCurrentLog   returnStr =" + returnStr);
            return returnStr;
        }


        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);

            if(subLogLine == null){
                shouldPrint = false;
            }
            String lastItem = "";
            if(logArray.size() == 0){
                logArray.add(subLogLine);

            } else if(logArray.size() >= 1){
                lastItem = logArray.get(logArray.size()-1);
            }
            //System.out.println("FixLong_Diff_Pre_PrintBase - lastItem =" + lastItem + " logArray.size() = "+ logArray.size());
            //  System.out.println("FixLong_Diff_Pre_PrintBase - lineContent =" + lineContent);
            //System.out.println("FixLong_Diff_Pre_PrintBase - subLogLine =" + subLogLine);
            //  if (!logArray.contains(subLogLine)) {
            // 只有与最后一个值  不一样的情况下才打印这个关键字
            if(lastItem !=null && subLogLine !=null &&
                    !lastItem.isEmpty() && !subLogLine.isEmpty() && !subLogLine.equals(lastItem)){
                logArray.add(subLogLine);
                shouldPrint = true;

            } else if(lastItem != null && lastItem.isEmpty() && subLogLine != null ){  // 第一次加载的时候 lastItem 为空  那么就需要把第一次遇见的加进去log数组
                logArray.add(subLogLine);
                shouldPrint = true;
            } else if(lastItem == null && subLogLine != null){
                logArray.add(subLogLine);
                shouldPrint = true;
            }else  {
                shouldPrint = false;
            }
        }
    }



    static abstract class DiffMeetPrintBase extends CommonBase2AbstractKeyEntry {  // 只打印不同Log的那些Case
        // 该函数用户返回需要保存在 ArrayList<String> logArray 用于区别是否需要打印的字符串
        abstract public String toGetDiffSubString(String lineContent);

        public void preCheck(String lineContent) {
            if (lineContent == null || lineContent.trim().isEmpty()) {
                return;
            }
            //  String subLogLine = lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());

            String subLogLine = toGetDiffSubString(lineContent);
            if (!logArray.contains(subLogLine)) {
                logArray.add(subLogLine);
                shouldPrint = true;

            } else {
                shouldPrint = false;
            }
        }

    }


    static class CommonBase2AbstractKeyEntry extends AbstractKeyEntry {
        CommonBase2AbstractKeyEntry() {
        }

        public void preCheck(String lineContent) {
        }

        public void afterCheck(String lineContent) {
        }

        public String fixCurrentLog(String lineContent) {
            return "";
        }

        public void doAfterReadOverFile() {
            logArray.clear();  // 所有的 当前文件产生的 ArrayLog 清楚掉   以迎接下一个文件的分析
        }

        @Override
        public ArrayList<String> getManyInfoFromOneLineMethod(String lineContent) {
            return null;
        }
    }


    abstract static class AbstractKeyEntry {
        public String keyword; // 关键字
        public String explain; // 说明
        public String codePath;  // 代码 该Log打印的代码路径
        public String curLogLineContent;  // 当前记录的那行Log  从Log起始点 开始

        public boolean shouldPrint = true; // MeetPrintBase  是否应该打印  Common的属性
        public ArrayList<String> logArray;  // DiffMeetPrintBase 使用 当前记录的有些不一样的记录行的 集合
        public ArrayList<String> logArrayPrintEnd;  // CollectEndPrintBase   持续收集 最后打印到到文件开头的Log
        public boolean getManyInfoFromOneLine = false; // 只有 CollectManyFromOneEndPrintBase 下为 true 是否会从一条Log 中得到 很多的信息

        public boolean shouldFixLog = false; // FixLongPrintBase 是否需要对当前的Log 进行一些Log的处理 比如太长截取

        public ArrayList<String> keywordArr;  // MultiKeyWordPrintBase 使用 当前记录的有些不一样的记录行的 集合

        public boolean justPrintOnce = false; //  false 表示  关闭该功能    true 表示打开功能   OncePrintBase   是否只打印一次
        public boolean justPrintOnce_work = false;    // 当 justPrintOnce= true 时 起作用 , 当执行完一次 打印时  该值为 true


        AbstractKeyEntry() {
            logArray = new ArrayList<String>();
//            keywordArr = new ArrayList<String>();
//            logArrayPrintEnd = new ArrayList<String>();
        }

        abstract public void preCheck(String lineContent);         //  在执行每个实例类的 analysisLineContent函数 之前执行的函数

        abstract public void afterCheck(String lineContent);       //  在执行每个实例类的 analysisLineContent函数 之后执行的函数

        abstract public String fixCurrentLog(String lineContent);  //  fixTooLong()     FixLongPrintBase  需要执行的方法

        abstract public void doAfterReadOverFile();                  // CollectEndPrintBase 文件读取完成后执行的方法

        abstract public ArrayList<String> getManyInfoFromOneLineMethod(String lineContent);  // 从一行获取 多个信息的函数   区别于 CollectEndPrintBase_ 一行获取一个信息

        public void analysisLineContent(String lineContent) {
            if (lineContent == null || keyword == null || !lineContent.contains(keyword)) {
                return;
            }
            if (keywordArr != null && keywordArr.size() > 0) {
                boolean findMultiWord = true;
                for (String keyword : keywordArr) {
                    if (!(lineContent+" ").contains(keyword)) {
                        findMultiWord = false;
                        break;
                    }
                }
                if (!findMultiWord) {  // 没有在该行中同时找到 多关键字
                    return;
                }
            }
            preCheck(lineContent);
            String checkItem;
            if (lineContent.contains(keyword)) {
                checkItem = new String(lineContent);   //  要打印的原始的Log行
                ArrayList<String> result = null;
                if (shouldFixLog && !getManyInfoFromOneLine) {  //  是否原始的Log行   tooLong  太长 需要修改  // 如果需要的话 执行 fixCurrentLog(checkItem)
                    checkItem = fixCurrentLog(checkItem);
                } else if (shouldFixLog && getManyInfoFromOneLine) {  // 从一行打印很多的数据
                    result = getManyInfoFromOneLineMethod(checkItem);  //    CollectManyFromOneEndPrintBase 才会执行
                }
                if(checkItem == null || checkItem.isEmpty()){
                    return ;      // 如果当前的 checkItem 为空 那么返回
                }
                checkItem = checkItem + "    " + explain;  //  添加 该行代码的解释
                if (codePath != null) {    //  如果 代码有路径  接着打印代码
                    checkItem = checkItem + "   【Code路径: " + codePath + "  】";
                }
                if (shouldPrint) {  // 依据  shouldPrint 标识 决定是否添加到  analysisStringArr

                    if (justPrintOnce) {
                        if (!justPrintOnce_work) {
                            analysisStringArr.add(checkItem);
                            justPrintOnce_work = true;   //  关闭 当前 打印  OncePrintBase的处理
                        }
                    } else {
                        if (logArrayPrintEnd == null) {  // 对于 CollectEndPrintBase  最后才打印的那些Log 在这里 就 不打印了 而是添加到自己的logArrayPrintEnd中
                            analysisStringArr.add(checkItem);
                        } else {
                            if (!getManyInfoFromOneLine) {
                                logArrayPrintEnd.add(checkItem);
                            } else {

                                if (result != null && result.size() > 0) {
                                    logArrayPrintEnd.addAll(result);
                                }
                            }

                        }

                    }

                }

            }
            afterCheck(lineContent);
            return;
        }
    }

    public static void insertArrayToHead(ArrayList<String> src, ArrayList<String> dst) {
        if (src != null && dst != null) {
            ArrayList tempList = new ArrayList<String>();
            tempList.addAll(dst);
            tempList.addAll(src);
            src.clear();
            src.addAll(tempList);
        }
    }

    public static void tryAnalysis(File mainFile) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mainFile), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {

                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                // 开始对每行开始分析
                for (AbstractKeyEntry keyEntry : keyEntryList) {
                    keyEntry.analysisLineContent(lineContent);
                }

                //

            }

            //  文件读取完成后  执行 抽象方法
            for (AbstractKeyEntry keyEntry : keyEntryList) {
                keyEntry.doAfterReadOverFile();
            }

            analysisStringArr.add(0, "=============开始对 Log文件: " + mainFile.getName() + " 进行分析！");
            //  analysisStringArr.add("=============开始对 Log文件: " + mainFile.getName() + " 进行分析！");
            curBR.close();

        } catch (Exception e) {
            StackTraceElement[] stackElements = e.getStackTrace();
            if(stackElements != null){
                for (int i = 0; i < stackElements.length; i++) {
                    System.out.print("ClassName : "+stackElements[i].getClassName()+" , ");
                    System.out.print("FileName : "+stackElements[i].getFileName()+" , ");
                    System.out.print("Number : "+stackElements[i].getLineNumber()+" , ");
                    System.out.println("Method : "+stackElements[i].getMethodName());
                }
            }

        }
    }

    public static File curDirFile;
    public static String curDirPath;
    public static ArrayList<File> curFileList = new ArrayList<File>();


    static String getSpace(String result, int num) {
        // pilsenpalace,BSSID:e4:f4:c6:14:fe:45,
        if (result == null && result.startsWith(",") && !result.contains(",")) {

            return "";
        }
        String ssid = result.trim().substring(0, result.trim().indexOf(","));
        if (ssid == null || ssid.trim().isEmpty()) {
            return "";
        }
        int ssidLength = ssid.length();
        int needSpace = num - ssidLength;
        String str = "";
        if (needSpace > 0) {

            for (int i = 0; i < needSpace; i++) {
                str = str + " ";
            }

        }
        return str;
    }

    static int getMaxSSIDLength(String[] arr) {
        int maxLength = 0;
        if (arr != null) {
            int arrlength = arr.length;

            for (int i = 0; i < arrlength; i++) {
                String item = arr[i];

                if (!item.trim().startsWith(",") && item.contains(",BSSID")) {
                    String ssid = item.substring(0, item.indexOf(",BSSID"));
                    int curssidLength = ssid.length();
                    if (curssidLength > maxLength) {
                        maxLength = curssidLength;
                    }
                }
            }

        }
        return maxLength;
    }

    static File notepad_command_File = null;
    public static void main(String[] args) {
        //===============real-test begin===============
        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        //===============real-test end===============


        //===============local-test begin===============
//        String mFilePath = System.getProperty("user.dir") + File.separator + "aplogcat-main.txt";
////        String preString = "<audio> <source src=\"";
////        String endString = "\" /><audio>";
        //===============local-test end===============


        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
            notepad_command_File = curFile;
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }


        // mFilePath =  C:\Users\Administrator\Desktop\test\test.txt
        System.out.println("input argument success mFilePath = " + mFilePath);
        curDirPath = mFilePath.substring(0, mFilePath.lastIndexOf("\\"));
        curDirFile = new File(curDirPath);
        if (curDirFile == null || !curDirFile.exists() || !curDirFile.isDirectory()) {
            return;
        }
ArrayList<File> dirFileList = new ArrayList<File>();

        for (File fileItem : curDirFile.listFiles()) {  // 把当前目录中的txt 文件放入 curFileList

            if(fileItem.isDirectory()){
                dirFileList.add(fileItem);
            }
            if (fileItem.isFile() && fileItem.getName().endsWith("txt")) {
                curFileList.add(fileItem);

            }
        }

        //
        for (int i = 0; i < dirFileList.size(); i++) {
            File dirFileItem = dirFileList.get(i);
            if(dirFileItem.listFiles() != null && dirFileItem.listFiles().length > 0){
                File[] files = dirFileItem.listFiles();

                for (File  subFile: files) {
                    if (subFile.isFile() && subFile.getName().endsWith("txt")) {
                        curFileList.add(subFile);   // 把子文件夹内的txt也加入到分析列表
                    }
                }
            }
        }

        curFileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if(o1.getName().contains("bugreport") && !o2.getName().contains("bugreport")){
                    return -1;
                }
                return 0;
            }
        });

        for (File logFile : curFileList) {
            tryAnalysis(logFile);
            allFileAnalysisStringArr.add(new ArrayList<String>(analysisStringArr));
            analysisStringArr.clear();  // 对于下一个文件  先 处理  analysisStringArr 的数据  然后 迎接下一个文件的处理
        }


            ArrayList<String> appendLogArr = new   ArrayList<String>();
            appendLogArr.add("════════════════Begin  GPS 全文件LOG搜索  ");


   int length = allFileAnalysisStringArr.size();
            for (int index = 0; index < length; index++) {
                ArrayList<String> arritem = allFileAnalysisStringArr.get(index);
                System.out.println("开始分析第 index = " + index + "个文件！");
                for (int i = 0; i < arritem.size(); i++) {
                    appendLogArr.add(arritem.get(i));

                }
            }
        appendLogArr.add("════════════════End  GPS 全文件LOG搜索  \n\n\n\n");

            System.out.println("OK !");


        appendToFile(notepad_command_File,appendLogArr);

//        try {
//        BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));
//
//        curBW.write(arritem.get(i));
//        curBW.newLine();
//        curBW.close();
//
//        } catch (Exception e) {
//        }


/*        if (curFile != null) {

            tryAnalysis(curFile);

            FileReader curReader;
            FileWriter curWriter;
            try {

                curReader = new FileReader(curFile);


                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mFilePath)), "utf-8"));
                String oldOneLine = "";
                String newOneLine = "";
                int indexLine = 0;

                while (oldOneLine != null) {

                    oldOneLine = curBR.readLine();
                    if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                        continue;
                    }
                    indexLine++;
                    newOneLine = indexLine + "      " + oldOneLine;
                    StringArr.add(newOneLine);
                }
                curBR.close();


                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));

                for (int i = 0; i < StringArr.size(); i++) {
                    curBW.write(StringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }*/


    }

    public static void appendToFile(File file , ArrayList<String> logList) {
        try {
            if(!file.exists()){
                file.createNewFile();
            }



            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(file, "rwd");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);


            for (int i = 0; i < logList.size(); i++) {
                String logItem = logList.get(i);
                randomFile.write((logItem+"\n").getBytes("utf-8"));
            }

//            randomFile.write(("index = "+index+"============开机打印【"+DateUtil.now()+ "】========== \n").getBytes("utf-8"));


            //  randomFile.write("\n".getBytes());  // 换行
            randomFile.close();
        } catch( Exception e ){


        }
    }
}
