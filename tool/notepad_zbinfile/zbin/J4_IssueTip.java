import java.io.File;
import java.util.Arrays;

public class J4_IssueTip {
    static String OneLine_Pre = "\n════════";
    static String OneLine_End = "════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");

    static String Desktop_Path = System.getProperties().getProperty("user.home")+File.separator+"Desktop";
    
    static String Zbin_Path = Desktop_Path+File.separator+"zbin";
    
    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static String BAT_OR_SH_Point ;

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            BAT_OR_SH_Point = ".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            BAT_OR_SH_Point = ".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            BAT_OR_SH_Point = ".sh";
        }
    }


    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    public static void main(String[] args) {
        initSystemInfo();   // 必须 第一位置

        AOSP_PATH_Tip();

        Package_Tip();
        Air_mode_Tip();
        Mobile_Data_Tip();

        ADB_Dump_Tip();
        Hotspot_Issue_Tip();
        BT_Issue_Tip();
        WIFI_Issue_Tip();
        WIFI_Shell_Code_Tip();
        GPS_Issue_Tip();
        GPS_Shell_Code_Tip();
        GPS_NMEA_Tip();
        ADB_pull_Tip();
        ADB_Input_Tip();
        WIFI_SAR_Tip();
        
        Other_Tip();
  
        Pass_Tip();
        zwisl_log_search_J9();
    }
    

    static void  Pass_Tip(){
        System.out.println("════════════════════════ Vendor Tip  ════════════════════════════════════════════════");

        System.out.println("TAIWAN: \n7487900017");
    }
    
    static void  zwisl_log_search_J9(){
        System.out.println("════════════════════════ zwisl_log_search_J9.bat J9_Log.xlsx  ════════════════════════════════════════════════");

        System.out.println(Zbin_Path+File.separator+"J9_Log.xlsx");
    }
    
    static void  AOSP_PATH_Tip(){
        System.out.println("════════════════════════ AOSP 模块路径 ════════════════════════════════════════════════");
        System.out.println();
        System.out.println("【Settings相关路径】");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps//Settings/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/wallpaper/");
        System.out.println();
        System.out.println();
        System.out.println("【 NFC 相关代码路径】");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Nfc/src/com/android/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/core/java/android/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/nxp/opensource/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/nxp/opensource/nfc/frameworks/com/nxp/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/st/nfc/packages/apps/Nfc/src/com/android/nfc/");
        System.out.println();
        System.out.println();
        System.out.println("【 BT 相关代码路径】");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/packages/SettingsLib/src/com/android/settingslib/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/core/java/android/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./system/bt");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./kernel/msm-5.4/drivers/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./kernel/xxxxxxx/drivers/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./kernel/msm-5.4/net/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/opensource/commonsys/system/bt/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/bluetooth");
        System.out.println();
        System.out.println();
        System.out.println("【 GNSS 相关代码路径】");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/services/core/java/com/android/server/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/gnss/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/qcom/gps");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps-commonsys");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps-release");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/opensource/location");
        System.out.println();
        System.out.println("【 WIFI 相关代码路径】");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/wifi/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/core/java/android/net/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/wifi/Java/android/net/wifi/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/opt/net/wifi/service/java/com/android/server/wifi");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/wifi/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./external/wpa_supplicant_8/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./kernel/msm-5.4/net/wireless/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./kernel/xxxxxxx/net/wireless");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/opensource/wlan/prima");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/opensource/wlan/qcacld-3.0");
        System.out.println();

    }

    static void  Package_Tip(){
        System.out.println("════════════════════════ Package Manager 查询 ════════════════════════════════════════════════");
        System.out.println("adb shell pm list packages                      【  查看所有安装包( 系统 + 三方  例如: package:com.miui.core )");
        System.out.println("adb shell pm list packages -s                   【 只输出系统自带包");
        System.out.println("adb shell pm list packages -3                   【 查看第三方应用 例如: package:com.sina.weibo");
        System.out.println("adb shell pm list packages -f                   【 包相关联的文件 例: package:/data/app/com.taobao.trip-xxxx==/base.apk=com.taobao.trip");
        System.out.println("adb shell pm list packages -i                   【 查看已安装应用信息和安装来源  例如: package:com.youdao.calculator  installer=null");
        System.out.println("adb shell pm list packages -e  sina <包名字符串>【  查询包含 sina 关键字的包");
        System.out.println("adb shell service list                          【  查询服务列表");
        System.out.println();
        System.out.println();

    }
    static void  Air_mode_Tip(){
        System.out.println("════════════════════════ AirPlane PAGE ════════════════════════════════════════════════");
        System.out.println("adb shell settings put global airplane_mode_on 0 【停止飞行模式】");
        System.out.println("adb shell settings get global airplane_mode_on   【获取飞行模式】");
        System.out.println();
        System.out.println();



    }


    static void  Mobile_Data_Tip(){
        System.out.println("════════════════════════ Mobile Data PAGE ════════════════════════════════════════════════");
        System.out.println("【打开-关闭 移动网络命令】");
        System.out.println("adb shell svc data enable");
        System.out.println("adb shell svc data disable");
        System.out.println();
        System.out.println("【查看移动网络开关 0-移动网络关闭  1-移动网络开启】");
        System.out.println("adb shell settings get global mobile_data");
        System.out.println("adb shell settings put global mobile_data 0");
        System.out.println("adb shell settings put global mobile_data 1");
        System.out.println();
        System.out.println();

    }

    static void  BT_Issue_Tip(){

        System.out.println("════════════════════════ BlueTooth PAGE ════════════════════════════════════════════════");
        System.out.println("【 关闭 打开蓝牙 操作命令 】");
        System.out.println("adb shell svc bluetooth enable");
        System.out.println("adb shell svc bluetooth disable");
        System.out.println();
        System.out.println("【0-Bluetooth蓝牙开关关闭 1-Bluetooth蓝牙开关开启】");
        System.out.println("adb shell settings get global bluetooth_on");
        System.out.println("adb shell settings put global bluetooth_on 0");
        System.out.println("adb shell settings put global bluetooth_on 1");
        System.out.println();
        System.out.println("【本地蓝牙设备名称】");
        System.out.println("adb shell settings get secure bluetooth_name");
        System.out.println();
        System.out.println("【本地蓝牙设备Mac地址】");
        System.out.println("adb shell settings get secure bluetooth_address");
        System.out.println();
        System.out.println("【查看蓝牙snooplog模式   full-有snoopLog模式  empty-无Log模式  】");
        System.out.println("adb shell getprop persist.bluetooth.btsnooplogmode");
        System.out.println();
        System.out.println("【BT dump 命令】");
        System.out.println("adb shell dumpsys bluetooth_manager > bluetooth_manager.txt");
        System.out.println();



    }

    static void  GPS_Log_isLoggable(){
    	  System.out.println("__________________ 【GPS】 Log.isLoggable(TAG,Log.VERBOSE) ____________________");
    	System.out.println("adb shell setprop log.tag.AltitudeReceiver D &&   adb shell setprop log.tag.DataPerPackageAndUser D &&  adb shell setprop log.tag.DebugReportService D &&  adb shell setprop log.tag.EsStatusReceiver D &&  adb shell setprop log.tag.FlpServiceProvider D &&  adb shell setprop log.tag.FusedLocationService D &&  adb shell setprop log.tag.GeofenceServiceProvider D &&  adb shell setprop log.tag.GnssConfigService D &&  adb shell setprop log.tag.GpsNetInitiatedHandler D &&  adb shell setprop log.tag.GTPClientHelper D &&  adb shell setprop log.tag.IzatConfig D &&  adb shell setprop log.tag.IzatProvider D &&  adb shell setprop log.tag.IzatService D &&  adb shell setprop log.tag.IZatServiceContext D &&  adb shell setprop log.tag.LocationService D &&  adb shell setprop log.tag.LocationServiceReceiver D &&  adb shell setprop log.tag.NetInitiatedActivity D &&  adb shell setprop log.tag.NetworkLocationService D &&  adb shell setprop log.tag.NpProxy D &&  adb shell setprop log.tag.OsAgent D &&  adb shell setprop log.tag.RilInfoMonitor D &&  adb shell setprop log.tag.WiFiDBProvider D &&  adb shell setprop log.tag.WiFiDBReceiver D &&  adb shell setprop log.tag.WWANDBProvider D &&  adb shell setprop log.tag.WWANDBReceiver D");
    	System.out.println();

    	
    }

    static void  Other_Tip(){

        System.out.println("════════════════════════ Other PAGE ════════════════════════════════════════════════");
        System.out.println("【打印LOG操作】");
        System.out.println("adb logcat | grep zukgit");
        System.out.println();
        System.out.println("【安卓设置 TAG 等级为打印 D=3  (V=2 D=3 I=4 W=5 E=6 A=7) 】");
        System.out.println("adb shell setprop log.tag.【TAG】 D       ||||||    例如:    adb shell setprop log.tag.SettingsInjector  D");
        System.out.println("adb shell getprop | grep log.tag.        【查看当前配置的TAG的显示等级】");
        System.out.println();

        System.out.println("【Settings.apk 安装 push命令】");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\Settings.apk /product/priv-app/Settings/  && adb push .\\oat  /product/priv-app/Settings    ##### 连续重启两遍");
        System.out.println();
        System.out.println("【adb disable-verity 提升权限命令】");
        System.out.println("adb root && adb disable-verity && adb reboot");
        System.out.println();
        System.out.println("【 wifi-service.jar  push命令】");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### 连续重启两遍?");
        System.out.println();
        GPS_Log_isLoggable();
      
    }
    
    
    
    
    static void  WIFI_SAR_Tip(){
        System.out.println("════════════════════════ WIFI_SAR 相关命令  ════════════════════════════════════════════════");
        System.out.println("___打开_WIFI_SAR的Log开关");
        System.out.println("adb root && adb remount && adb shell setprop ro.debuggable 1 && adb shell setprop persist.radio.ctbk_log 5 && adb shell setprop persist.vendor.radio.ctbk_log 5 && adb shell setprop persist.radio.adb_log_on 1 ​ &&  adb reboot");
        System.out.println();
        System.out.println("___打印_WIFI_SAR的Log");
        System.out.println("adb logcat | grep -E \"SARCTRL|MDMCTBK\"");
    }

    
    

    static void  ADB_Input_Tip(){
        System.out.println("════════════════════════ adb 命令 输入字符 ════════════════════════════════════════════════");
        System.out.println();
        System.out.println("adb shell input text 0123456789012345678901234567890123456789012345678901234567891234");
        System.out.println();
        System.out.println();

    }

    static void  ADB_pull_Tip(){

        System.out.println("════════════════════════ adb pull 拉取文件的操作════════════════════════════════════════════════");
        System.out.println("adb root & adb pull /data/system/users/0/settings_system.xml");
        System.out.println();
        System.out.println("adb root & adb pull /data/system/users/0/settings_secure.xml");
        System.out.println();
        System.out.println("adb root & adb pull /data/system/users/0/settings_global.xml");
        System.out.println();
        System.out.println("adb root & adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini");
        System.out.println();
        System.out.println("adb root & adb pull /vendor/etc/wifi/wpa_supplicant.conf");
        System.out.println();
        System.out.println("adb root & adb pull /system/etc/wifi/p2p_supplicant.conf");
        System.out.println();
        System.out.println("adb root & adb pull /data/misc/wifi/WifiConfigStore.xml");
        System.out.println();
        System.out.println("adb root & adb pull /data/misc/wifi/softap.conf");
        System.out.println();
        System.out.println("adb root & adb pull /system/build.prop");
        System.out.println();
        System.out.println("adb root & adb shell getprop >  ./prop.txt");
        System.out.println();
        System.out.println("adb root & adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn");
        System.out.println();
        System.out.println("adb root & adb pull  /vendor/fireware_mnt/image/Data.msc");
        System.out.println();
        System.out.println("adb root & adb pull /vendor/rfs/msm/mpss/ramdumps");
        System.out.println();
        System.out.println("adb root & adb pull /storage/emulated/0/Pictures/Screenshots");
        System.out.println();
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.deny");
        System.out.println();
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.accept");
        System.out.println();
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.conf");
        System.out.println();
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.accept");
        System.out.println();
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.deny");
        System.out.println();
        System.out.println("adb root & adb pull /data/vendor/bug2go");
        System.out.println();
        System.out.println("adb root & adb pull  /data/vendor/diag_mdlog  ");
        System.out.println();
    }

    static void  Hotspot_Issue_Tip(){
        System.out.println("════════════════════════HotSpot PAGE ════════════════════════════════════════════════");
        System.out.println();
        System.out.println("【查看用户是否可以配置热点dhcp的配置项  0-可设置dhcp 1-不可设置dhcp】");
        System.out.println("adb shell settings get global tether_enable_legacy_dhcp_server");
        System.out.println();

    }

    static void  ADB_Dump_Tip(){
        System.out.println("════════════════════════ Dump 相关信息查询 ════════════════════════════════════════════════");
        System.out.println();
        System.out.println("adb shell dumpsys media.camera                              【 查看camera的信息");
        System.out.println("adb shell dumpsys activity                                  【 查看ActvityManagerService 所有信息");
        System.out.println("adb shell dumpsys activity activities                       【 查看Activity组件信息");
        System.out.println("adb shell dumpsys activity services                         【 查看Service组件信息");
        System.out.println("adb shell dumpsys activity providers                        【 查看ContentProvider组件信息");
        System.out.println("adb shell dumpsys activity broadcasts                       【 查看BraodcastReceiver信息");
        System.out.println("adb shell dumpsys activity intents                          【 查看Intent信息");
        System.out.println("adb shell dumpsys activity processes                        【 查看进程信息");
        System.out.println("adb shell dumpsys activity provider com.android.settings    【 查看Settings相关的Provider信息");
        System.out.println("adb shell pm list features                                  【 查看安卓feature");
        System.out.println();
        System.out.println();

    }

    static void  GPS_NMEA_Tip(){

        System.out.println("════════════════════════ GPS NMEA 定位命令 $GPGGA $GPGSA $GPGSV $GPRMC ══════════════════════════════════════════════");
        System.out.println("https://blog.csdn.net/u013232740/article/details/45029245");
        System.out.println("https://gpsd.gitlab.io/gpsd/NMEA.html");
        System.out.println();
        System.out.println("$GPZDA 【Zone Time Date UTC               (ZDA)UTC时间 日期】【 $GPZDA, <1>,<2>,<3>,<4>,<5>,<6>*hh 】");
        System.out.println("$GPGGA 【Global Positioning Fix Data      (GGA)卫星定位信息】【 $GPGGA, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,M,<10>,M,<11>,<12>*hh】");
        System.out.println("$GPGLL 【Geographic Position Lat/Long     (GLL)地理定位信息】【 $GPGLL, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>*hh】");
        System.out.println("$GPVTG 【Track Made Good and Ground Speed (VTG)地面速度信息】【 $GPVTG, <1>,T,<2>,M,<3>,N,<4>,K,<5>*hh 】");
        System.out.println("$GPGSA 【GPS DOP and Active Satellites    (GSA)当前卫星信息】【 $GPGSA, <1>,<2>,<3>,<3>,,,,,<3>,<3>,<3>,<4>,<5>,<6>*hh    】");
        System.out.println("$GPGSV 【GPS Satellites in View           (GSV)可见卫星信息】【 $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<4>,<5>,<6>,<7>,<8>*hh  】");
        System.out.println("$GPGSV 【GPS Satellites in View           (GSV)可见卫星信息】【 $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,<8>*hh  】");
        System.out.println("$GPGSV 【GPS Satellites in View           (GSV)可见卫星信息】【 $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,<8>*hh 】");
        System.out.println("$GPRMC 【Recommended Minimum Data         (RMC)推荐定位信息】【 $GPRMC, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,<10>,<11>,<12>*hh 】");
        System.out.println();
        System.out.println();
        System.out.println("$GPZDA, <1>,<2>,<3>,<4>,<5>,<6>*hh   (ZDA)UTC时间 日期");
        System.out.println("字段1：UTC 时间， hhmmss.sss ，时分秒格式");
        System.out.println("字段2：日期 dd=0~31");
        System.out.println("字段3：月，mm=1~12");
        System.out.println("字段4：yyyy 年");
        System.out.println("字段5：xx 当地时域描述，单位：小时，xx=-13~13");
        System.out.println("字段6：当地时域描述，   单位：分，  yy=0~59");
        System.out.println();
        System.out.println();
        System.out.println("$GPGGA, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,M,<10>,M,<11>,<12>*hh   (GGA)卫星定位信息");
        System.out.println("$GPGGA,092201.00,3112.312832,N,12134.879544,E,1,17,0.8,12.6,M,10.0,M,,*50");
        System.out.println("$GPGGA,092111.00,,,,,0,00,300.0,,M,,M,,*6F");
        System.out.println("$GPGGA,092122.00,3112.318862,N,12134.877608,E,1,15,2.4,17.6,M,10.0,M,,*51");
        System.out.println("字段1：UTC 时间， hhmmss.sss ，时分秒格式");
        System.out.println("字段2：纬度ddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段3：纬度N（北纬）或S（南纬）");
        System.out.println("字段4：经度dddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段5：经度E（东经）或W（西经）");
        System.out.println("字段6：GPS状态， 0= 未定位， 1= 非差分定位， 2= 差分定位， 3= 无效PPS， 6= 正在估算");
        System.out.println("字段7：正在使用的卫星数量（ 00 - 12 ）（前导位数不足则补0）");
        System.out.println("字段8：HDOP 水平精度因子（ 0.5 - 99.9 ）");
        System.out.println("字段9：海拔高度（ -9999.9 - 99999.9 ）");
        System.out.println("字段10：地球椭球面相对大地水准面的高度");
        System.out.println("字段11：差分时间（从最近一次接收到差分信号开始的秒数，如果不是差分定位将为空）");
        System.out.println("字段12：差分站ID 号0000 - 1023 （前导位数不足则补0，如果不是差分定位将为空）");
        System.out.println();
        System.out.println();
        System.out.println("$GPGLL, <1>,<2>,<3>,<4>,<5>,<6>*hh                                  (GLL)地理定位信息");
        System.out.println("$GPGLL,4250.5589,S,14718.5084,E,092204.999,A*2D");
        System.out.println("字段1：纬度ddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段2：纬度N（北纬）或S（南纬）");
        System.out.println("字段3：经度dddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段4：经度E（东经）或W（西经）");
        System.out.println("字段5：UTC时间， hhmmss.sss 格式");
        System.out.println("字段6：状态， A= 定位， V= 未定位");
        System.out.println();
        System.out.println();
        System.out.println("$GPVTG, <1>,T,<2>,M,<3>,N,<4>,K*hh");
        System.out.println("字段1：运动角度， 000 - 359 ，（前导位数不足则补0）");
        System.out.println("字段2：运动角度， 000 - 359 ，（前导位数不足则补0）");
        System.out.println("字段3：水平运动速度（ 0.00 ）（前导位数不足则补0）");
        System.out.println("字段4：水平运动速度（ 0.00 ）（前导位数不足则补0）");
        System.out.println("T=真北参照系   M=磁北参照系   N=节  K=公里/时-km/h");
        System.out.println();
        System.out.println();
        System.out.println("$GPGSA, <1>,<2>,<3>,<3>,,,,,<3>,<3>,<3>,<4>,<5>,<6>*hh   (GSA)当前卫星信息");
        System.out.println("$GPGSA,A,1,,,,,,,,,,,,,140.0,99.0,99.0*35");
        System.out.println("$GPGSA,A,1,,,,,,,,,,,,,3.4,2.5,2.3*31");
        System.out.println("$GPGSA,A,3,04,09,16,26,27,,,,,,,,2.1,1.6,1.3*3F");
        System.out.println("字段1：定位模式， A= 自动手动2D/3D ，M= 手动2D/3D");
        System.out.println("字段2：定位类型， 1= 未定位， 2=2D 定位， 3=3D 定位");
        System.out.println("字段3：PRN码（伪随机噪声码），第1 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第2 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第3 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第4 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第5 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第6 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第7 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第8 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第9 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第10 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第11 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段3：PRN码（伪随机噪声码），第12 信道正在使用的卫星PRN码编号（ 00）（前导位数不足则补0）");
        System.out.println("字段4：PDOP 综合位置精度因子（ 0.5 - 99.9 ）");
        System.out.println("字段5：HDOP 水平精度因子（ 0.5 - 99.9 ）");
        System.out.println("字段6：VDOP 垂直精度因子（ 0.5 - 99.9 ）");
        System.out.println();
        System.out.println();
        System.out.println("$GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,*hh        (GSV)可见卫星信息");
        System.out.println("$GPGSV,3,1,09,16,58,011,38,26,38,053,31,09,29,303,34,18,14,059,33*7D");
        System.out.println("$GPGSV,3,2,09,07,04,310,,27,78,187,,04,52,260,,08,39,213,*71");
        System.out.println("$GPGSV,3,3,09,31,25,118,*4D");
        System.out.println("$GAGSV,2,1,07,127,58,350,,115,41,113,,130,31,065,,121,25,290,*6D");
        System.out.println("$GAGSV,2,2,07,107,25,196,,119,21,306,,113,20,169,*60");
        System.out.println("字段1：本次GSV语句的总数目（ 1 - 3 ）");
        System.out.println("字段2：本条GSV语句是本次GSV语句的第几条（ 1 - 3 ）");
        System.out.println("字段3：当前可见卫星总数（ 00 - 12 ）（前导位数不足则补0）");
        System.out.println("字段4：PRN 码（伪随机噪声码）（ 01 - 32 ）（前导位数不足则补0）");
        System.out.println("字段5：卫星仰角（ 00 - 90 ）度（前导位数不足则补0）");
        System.out.println("字段6：卫星方位角（ 00 - 359 ）度（前导位数不足则补0）");
        System.out.println("字段7：信噪比（ 00 －99）dbHz");
        System.out.println("字段4：PRN 码（伪随机噪声码）（ 01 - 32 ）（前导位数不足则补0）");
        System.out.println("字段5：卫星仰角（ 00 - 90 ）度（前导位数不足则补0）");
        System.out.println("字段6：卫星方位角（ 00 - 359 ）度（前导位数不足则补0）");
        System.out.println("字段7：信噪比（ 00－99）dbHz");
        System.out.println("字段4：PRN 码（伪随机噪声码）（ 01 - 32 ）（前导位数不足则补0）");
        System.out.println("字段5：卫星仰角（ 00 - 90 ）度（前导位数不足则补0）");
        System.out.println("字段6：卫星方位角（ 00 - 359 ）度（前导位数不足则补0）");
        System.out.println("字段7：信噪比（ 00－99）dbHz");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("$GPRMC,<1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,<10>,<11>,<12>*hh   (RMC)推荐定位信息");
        System.out.println("$GPRMC,092110.00,V,,,,,,,040121,,,N*70");
        System.out.println("$GPRMC,092127.00,A,3112.316508,N,12134.878718,E,000.1,310.3,040121,,,A*53");
        System.out.println("$GPRMC,092159.00,A,3112.311732,N,12134.879623,E,000.1,258.5,040121,,,A*55");
        System.out.println("字段1：UTC时间， hhmmss.sss 格式");
        System.out.println("字段2：状态， A= 定位， V= 未定位");
        System.out.println("字段3：纬度ddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段4：纬度N（北纬）或S（南纬）");
        System.out.println("字段5：经度dddmm.mmmm ，度分格式（前导位数不足则补0）");
        System.out.println("字段6：经度E（东经）或W（西经）");
        System.out.println("字段7：速度，节， Knots");
        System.out.println("字段8：方位角，度");
        System.out.println("字段9：UTC日期， DDMMYY 格式");
        System.out.println("字段10：磁偏角，（ 000 - 180 ）度（前导位数不足则补0）");
        System.out.println("字段11：磁偏角方向， E=东W=西");
        System.out.println("字段12：FAA mode indicator (NMEA 2.3 and later)  N=none  A=auto?");
        System.out.println();



    }

    static void  GPS_Shell_Code_Tip(){
        System.out.println("════════════════════════ GPS shell 命令 adb shell cmd location xxxx ════════════════════════════════════════════════");
        System.out.println("【 ADB Location 独立 命令 帮助文档  /frameworks/base/services/core/java/com/android/server/location/LocationShellCommand.java 】");
        System.out.println("adb root & adb shell cmd location -h");
        System.out.println("adb root & adb shell cmd -l");
        System.out.println();
        System.out.println("adb shell setprop log.tag.GnssLocationProvider D    《TAG 可见》");
        System.out.println("adb shell setprop log.tag.NtpTimeHelper D");
        System.out.println("adb logcat | grep -E \"GnssLocationProvider|NtpTimeHelper\"");
        System.out.println();
        System.out.println();
        System.out.println("【冷启动  温启动  热启动 】");
        System.out.println("热启动：就是在上一次GPS定位到的情况下。关掉GPS，然后再打开，然后打开GPS去定位。一般3秒左右");
        System.out.println("nothing to do");
        System.out.println();
        System.out.println("温启动：就是在上一次GPS定位到的情况下。关掉GPS，然后清除星历数据，然后打开GPS去定位。一般30秒左右");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez ephemeris      true");
        System.out.println();
        System.out.println("冷启动：就是在上一次GPS定位到的情况下。关掉GPS，然后清除所有数据，然后重启手机（有的手机需要），然后打开GPS去定位。时间最长");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data");
        System.out.println();
        System.out.println("【GPS 位置模式开关 】");
        System.out.println("adb root & adb shell cmd  location set-location-enabled  true                       《打开定位模式》");
        System.out.println("adb root & adb shell cmd  location set-location-enabled  false                      《关闭定位模式》");
        System.out.println();
        System.out.println("【 LocationManagerService.java -> boolean sendExtraCommand(String provider, String command, Bundle extras) 】");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps force_time_injection      《GnssLocationProvider.java -> deleteAidingData(extras) -> NtpTimeHelper.java -> retrieveAndInjectNtpTime()  请求更新 NTP time Network Time Protocol 时间同步数据到GPS芯片》");
        System.out.println();
        System.out.println("adb root & adb shell cmd  location send-extra-command gps force_psds_injection      《GnssLocationProvider.java ->  boolean mSupportsPsds -> psdsDownloadRequest() 请求使用预置的辅助定位数据到GPS芯片》");
        System.out.println();
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data        《GnssLocationProvider.java ->    requestUtcTime()   请求删除预置的辅助定位数据》");
        System.out.println();
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez ephemeris      true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez almanac        true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez position       true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez time           true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez iono           true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez utc            true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez health         true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez svdir          true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez svsteer        true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez sadata         true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez rti            true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez celldb-info    true");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez all            true");
        System.out.println();
        System.out.println();




    }
    static void  WIFI_Shell_Code_Tip(){
        System.out.println("════════════════════════ WIFI shell 命令 adb shell cmd wifi xxxx ════════════════════════════════════════════════");
        System.out.println("【 ADB WIFI 独立 命令 帮助文档  /frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiShellCommand.java 】");
        System.out.println("adb root & adb shell cmd wifi -h");
        System.out.println("adb root & adb shell cmd -l      《查询当前adb shell cmd service》");
        System.out.println();
        System.out.println("【获取设置 IPR 断连开关  ClientModeImpl.java   -》 boolean mIpReachabilityDisconnectEnabled = true; 】");
        System.out.println("adb root & adb shell cmd wifi get-ipreach-disconnect       《IPREACH_DISCONNECT state is true || IPREACH_DISCONNECT state is false》");
        System.out.println("adb root & adb shell cmd wifi set-ipreach-disconnect enabled");
        System.out.println("adb root & adb shell cmd wifi set-ipreach-disconnect disabled");
        System.out.println();
        System.out.println("【获取设置 检测rssi信号间隔  ClientModeImpl.java   -》 int mPollRssiIntervalMsecs = -1;】");
        System.out.println("【可选值 R.integer.config_wifiPollRssiIntervalMilliseconds = 3000  , int MAXIMUM_POLL_RSSI_INTERVAL_MSECS = 6000; 】");
        System.out.println("adb root & adb shell cmd wifi get-poll-rssi-interval-msecs          《获取间隔时间》");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 1000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 2000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 3000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 4000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 5000");
        System.out.println();
        System.out.println();
        System.out.println("【一般获取信息命令集合 】");
        System.out.println("adb root & adb shell cmd wifi  get-ipreach-disconnect");
        System.out.println("adb root & adb shell cmd wifi  set-ipreach-disconnect enabled");
        System.out.println("adb root & adb shell cmd wifi  set-ipreach-disconnect disabled     《ClientModeImpl.boolean mIpReachabilityDisconnectEnabled = true》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-poll-rssi-interval-msecs");
        System.out.println("adb root & adb shell cmd wifi  set-poll-rssi-interval-msecs 2000   《ClientModeImpl.int mPollRssiIntervalMsecs = 3000;》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-country-code");
        System.out.println("adb root & adb shell cmd wifi  force-country-code enabled cn       《WifiCountryCode.String mTelephonyCountryCode , mDriverCountryCode , mDefaultCountryCode 》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  force-hi-perf-mode enable");
        System.out.println("adb root & adb shell cmd wifi  force-hi-perf-mode disabled");
        System.out.println("adb root & adb shell cmd wifi  force-low-latency-mode enabled");
        System.out.println("adb root & adb shell cmd wifi  force-low-latency-mode disabled     《WifiLockManager.boolean mForceHiPerfMode = false , mForceLowLatencyMode = false》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-wifi-enabled enabled");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-enabled disabled           《WifiServiceImpl.setWifiEnabled() -> WifiSettingsStore.int mPersistWifiState = 0 (0_disable,1_enable,2_飞行模式关闭导致enable,3_飞行模式开启导致disable)》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-scan-always-available enabled");
        System.out.println("adb root & adb shell cmd wifi  set-scan-always-available disabled  《WifiServiceImpl.setScanAlwaysAvailable()-> WifiSettingsStore -> Settings.wifi_scan_always_enabled(Key-Str)》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-scan-results                   《List<ScanResult> WifiServiceImpl.getScanResults()  -> WifiThreadRunner.call( mScanRequestProxy::getScanResults )》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  status                              《WifiServiceImpl.getWifiEnabledState()  isScanAlwaysAvailable() WifiInfo->getConnectionInfo()   Network->WifiService.getCurrentNetwork()  NetworkCapabilities->ConnectivityManager.getNetworkCapabilities(network) 》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-verbose-logging enabled");
        System.out.println("adb root & adb shell cmd wifi  set-verbose-logging disabled        《WifiService.enableVerboseLogging  ( adb shell settings put global wifi_verbose_logging_enabled 1 )  》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-suggestions                    《List<WifiNetworkSuggestion> -> WifiService.getNetworkSuggestions ->   WifiNetworkSuggestionsManager.get()》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-wifi-watchdog");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-watchdog enabled");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-watchdog disabled          《WifiLastResortWatchdog.getWifiWatchdogFeature() -> R.bool.config_wifi_watchdog_enabled 》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-softap-supported-features       《ApConfigUtil.isAcsSupported() ApConfigUtil.isWpa3SaeSupported() 》");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-requests                       《WifiShellCommand.ConcurrentHashMap<String, Pair<NetworkRequest, ConnectivityManager.NetworkCallback>> sActiveRequests》");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("【一般操作WIFI集合 】");
        System.out.println("adb root & adb shell cmd wifi  start-scan");
        System.out.println("adb root & adb shell cmd wifi  set-connected-score 60     《0 - 60》");
        System.out.println("adb root & adb shell cmd wifi  connect-network   <ssid> open|owe|wpa2|wpa3 [<passphrase>] [-m] [-d] [-b <bssid>]");
        System.out.println("adb root & adb shell cmd wifi  add-network       <ssid> open|owe|wpa2|wpa3 [<passphrase>] [-m] [-d] [-b <bssid>]");
        System.out.println("adb root & adb shell cmd wifi  forget-network    <networkId>");
        System.out.println("adb root & adb shell cmd wifi  add-suggestion    <ssid> open|owe|wpa2|wpa3 [<passphrase>] [-u] [-m] [-s] [-d][-b <bssid>]");
        System.out.println("adb root & adb shell cmd wifi  start-softap      <ssid> (open|wpa2) <passphrase> [-b 2|5|6|any]");
        System.out.println("adb root & adb shell cmd wifi  remove-suggestion <ssid>");
        System.out.println("adb root & adb shell cmd wifi  remove-all-suggestions");
        System.out.println("adb root & adb shell cmd wifi  reset-connected-score");
        System.out.println("adb root & adb shell cmd wifi  stop-softap");
        System.out.println("adb root & adb shell cmd wifi  network-suggestions-set-user-approved <package name> yes");
        System.out.println("adb root & adb shell cmd wifi  network-suggestions-set-user-approved <package name> no");
        System.out.println("adb root & adb shell cmd wifi  network-suggestions-has-user-approved <package name>");
        System.out.println("adb root & adb shell cmd wifi  imsi-protection-exemption-set-user-approved-for-carrier   <carrier id> yes");
        System.out.println("adb root & adb shell cmd wifi  imsi-protection-exemption-set-user-approved-for-carrier   <carrier id> no");
        System.out.println("adb root & adb shell cmd wifi  imsi-protection-exemption-has-user-approved-for-carrier   <carrier id>");
        System.out.println("adb root & adb shell cmd wifi  imsi-protection-exemption-clear-user-approved-for-carrier <carrier id>");
        System.out.println("adb root & adb shell cmd wifi  network-requests-remove-user-approved-access-points <package name>");
        System.out.println("adb root & adb shell cmd wifi  clear-user-disabled-networks");
        System.out.println("adb root & adb shell cmd wifi  send-link-probe    《手动发送一个probe请求》");
        System.out.println("adb root & adb shell cmd wifi  force-softap-channel enabled <int>");
        System.out.println("adb root & adb shell cmd wifi  force-softap-channel enabled 2412");
        System.out.println("adb root & adb shell cmd wifi  force-softap-channel disabled <int>");
        System.out.println("adb root & adb shell cmd wifi  force-softap-channel disabled 5785");
        System.out.println("adb root & adb shell cmd wifi  force-country-code enabled <two-letter code>");
        System.out.println("adb root & adb shell cmd wifi  force-country-code enabled br");
        System.out.println("adb root & adb shell cmd wifi  force-country-code enabled us");
        System.out.println("adb root & adb shell cmd wifi  force-country-code disabled <two-letter code>");
        System.out.println("adb root & adb shell cmd wifi  settings-reset");
        System.out.println("adb root & adb shell cmd wifi  add-request <ssid> open|owe|wpa2|wpa3 [<passphrase>] [-b <bssid>]");
        System.out.println("adb root & adb shell cmd wifi  remove-request <ssid>");
        System.out.println("adb root & adb shell cmd wifi  remove-all-requests");
        System.out.println("adb root & adb shell cmd wifi  network-requests-set-user-approved <package name> yes");
        System.out.println("adb root & adb shell cmd wifi  network-requests-set-user-approved <package name> no");
        System.out.println("adb root & adb shell cmd wifi  network-requests-has-user-approved <package name>");
        System.out.println();
        System.out.println();



    }
    static void  WIFI_Issue_Tip(){
        System.out.println("════════════════════════ WIFI PAGE ════════════════════════════════════════════════");
        System.out.println("【打开wifi命令】  adb shell svc wifi enable");
        System.out.println("【关闭wifi命令】  adb shell svc wifi disable");
        System.out.println();
        System.out.println();
        System.out.println("【WIFI-Verbose 开关】");
        System.out.println("adb shell settings get global wifi_verbose_logging_enabled 0");
        System.out.println("adb shell settings put global wifi_verbose_logging_enabled 0");
        System.out.println("adb shell settings put global wifi_verbose_logging_enabled 1");
        System.out.println();
        System.out.println("【WIFI详情开关描述】");
        System.out.println("Settings >System > About phone > tap \"Build number\" 4 times >Developer options");
        System.out.println("Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open]");
        System.out.println();
        System.out.println("【Passpoint-sim开关】");
        System.out.println("adb shell settings get global hs20_mncmcc_retail_saved_state");
        System.out.println("adb shell settings put global hs20_mncmcc_retail_saved_state 0");
        System.out.println("adb shell settings put global hs20_mncmcc_retail_saved_state 1");
        System.out.println();
        System.out.println("【 wifi-service.jar  push命令】");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### 连续重启两遍?");
        System.out.println();
        System.out.println("【广播启动到WIFI设置界面】");
        System.out.println("adb shell am start -a android.settings.WIFI_SETTINGS");
        System.out.println();
        System.out.println();
        System.out.println("【WIFI 状态变化广播】");
        System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" —ei<int> test_int 100 —ez<boolean> test_boolean true");
        System.out.println("【 -a com.Android.test  包名.Action的形式 】");
        System.out.println("【 --es \"test_string\" \"this is test string\"    指定广播中携带字符串 字符串名称为 test_string 后面为值Value  】");
        System.out.println("【 --ei test_int 100    指定广播中携带int整形  int名称为 test_int 后面为值Value 为 100 】");
        System.out.println("【 --ez test_boolean true    指定广播中携带boolean变量   boolean名称为 test_boolean 后面为值Value 为 true 】");
        System.out.println("adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 0");
        System.out.println("adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 1");
        System.out.println("adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 2");
        System.out.println("adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 3");
        System.out.println("adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 4");
        System.out.println("WifiManager.WIFI_STATE_DISABLED ==1");
        System.out.println("WifiManager.WIFI_STATE_DISABLING ==0");
        System.out.println("WifiManager.WIFI_STATE_ENABLED==3");
        System.out.println("WifiManager.WIFI_STATE_ENABLING==2");
        System.out.println("WifiManager.WIFI_STATE_UNKNOWN==4");
        System.out.println();
        System.out.println();
        System.out.println("【IPR断线问题】");
        System.out.println("问题现象：终端设备连续三次发送arp request而网关无回复，触发终端设备主动断开无线连接");
        System.out.println("根本原因：华为网关交换机CPU-defend安全防护机制触发而导致arp request报文丢弃");
        System.out.println("原因分析：");
        System.out.println("1. 华为交换机默认启用CPU-defend机制，该机制能防止交换机因为频繁响应arp request报文导致的CPU利用率异常飙高；");
        System.out.println("2. 当终端设备arp报文达到默认阈值上限，就会触发华为CPU-defend，对超出阈值的arp request报文不进行响应，直接drop；");
        System.out.println("3. 客户端设备的arp老化时间是为30s，因此客户端arp request报文是30s发送一个，而无线用户的arp报文都是由无线控制器转发，所以在用户多的时候掉线问题很容易复现，用户少的时候则无法复现；");
        System.out.println("4. 正常情况下arp request报文的丢弃并不影响用户的正常网络使用；");
        System.out.println("5. 由于频繁arp request是网络中ARP攻击行为的典型表现，因此不止华为设备，其他厂商设备也会有类似的arp安全防护机制，唯一的不同只是各厂商的arp报文丢弃默认阈值设置不同。");
        System.out.println("IKLOCSEN-2833");
        System.out.println();
        System.out.println();

        System.out.println();
        System.out.println("【 WIFI DUMP 相关命令】");
        System.out.println("adb shell dumpsys wifi         > wifi.txt                 # 【查看当前 wifi 服务情况 】");
        System.out.println("adb shell dumpsys wifiscanner  > wifiscanner.txt          # 【查看当前 wifiscanner 扫描服务情况 】");
        System.out.println("adb shell dumpsys connectivity > connectivity.txt         # 【查看当前 connectivity 服务情况 】");
        System.out.println("adb shell dumpsys connmetrics  > connmetrics.txt          # 【查看当前 connmetrics 服务情况  断线 无网  】");
        System.out.println("adb shell dumpsys netstats     > netstats.txt             # 【查看当前 netstats 服务情况 】");
        System.out.println();
    }



    static void  GPS_Issue_Tip(){
        System.out.println("═══════════════════════ GPS PAGE ════════════════════════════════════════════════");
        System.out.println("【设置位置模式 0-关闭位置模式 1-打开位置模式 2-打开省电位置模式 3-打开高性能位置模式 】");
        System.out.println("adb shell settings get secure location_mode");
        System.out.println("adb shell settings put secure location_mode 0");
        System.out.println("adb shell settings put secure location_mode 1");
        System.out.println("adb shell settings put secure location_mode 2");
        System.out.println("adb shell settings put secure location_mode 3");
        System.out.println();
        System.out.println("【WIFI-Scan WIFI扫描定位  0-关闭WIFI扫描定位  1-开启WIFI扫描定位 】");
        System.out.println("adb shell settings get global wifi_scan_always_enabled");
        System.out.println("adb shell settings put global wifi_scan_always_enabled  0");
        System.out.println("adb shell settings put global wifi_scan_always_enabled  1");
        System.out.println();
        System.out.println();
        System.out.println("【BT-Scan BT扫描定位  0-关闭BT扫描定位  1-开启BT扫描定位 】");
        System.out.println("adb shell settings get global ble_scan_always_enabled");
        System.out.println("adb shell settings put global ble_scan_always_enabled   0");
        System.out.println("adb shell settings put global ble_scan_always_enabled   1");
        System.out.println();
        System.out.println();
        System.out.println("【APP的 GPS定位权限查找:】");
        System.out.println("搜索bugreport.txt  关键字 Package [应用名称]    例如:  Package [com.whatsapp]");
        System.out.println("runtime permissions:");
        System.out.println("android.permission.ACCESS_FINE_LOCATION: granted=true    精确位置");
        System.out.println("android.permission.ACCESS_COARSE_LOCATION: granted=true  模糊位置");
        System.out.println();
        System.out.println("【GPS定位的Provider列表】");
        System.out.println("adb shell su 0 settings get secure location_providers_allowed          ->   gps,network");
        System.out.println();
        System.out.println("【GPS定位的Provider列表中去除 network的操作 Tip】");
        System.out.println("In order to get location details in simulated environment, please disable Network Location so test continues on GPS. Below are the steps:");
        System.out.println("(1) Go to settings->Location->Advanced->Google Location Accuracy and change it from ON to OFF");
        System.out.println("(2) adb reboot (or power cycle the device)");
        System.out.println("(3) make sure settings->Location->Advanced->Google Location Accuracy->OFF");
        System.out.println("(4) settings->location->advanced->Carrier Location -> must be enabled");
        System.out.println("(5) After that check only GPS location provider enabled by inputing below command in adb shell");
        System.out.println("adb root");
        System.out.println("adb shell su 0 settings get secure location_providers_allowed");
        System.out.println("and it should output gps");
        System.out.println();
        System.out.println();
        System.out.println("【QCAT 中 Displays > QCAT Sample > GNSS > GNSS RF Bp Amp Pga Gain Data QCAT-GNSS-天线干扰】");
        System.out.println("tip1: Bp Amp应该在90-350  min-max 否则就有干扰");
        System.out.println("tip2: 在 QCAT中 搜索 0x1476 GNSS 点击 <Process> 之后选中 Configuration 面板 查看 Output Directory 打开该路径就有谷歌地图 .kml 文件");
        System.out.println();
        System.out.println("【CMAS(Commercial mobile alert system)移动商业报警系统    WEA(Wireless Emergency Alerts)】");
        System.out.println("https://blog.csdn.net/aaa111/article/details/79361595          网页说明");
        System.out.println("WEA 是基于小区广播服务的，那它的接收也是和小区广播一样（手机端）它独有的地方在于要配置WEA 相关的mids（Message ID）或者说Channel ID 到modem");
        System.out.println("而Modem端的mids来源于代码，SIM卡，NV值等");
        System.out.println("QCAT 中搜搜");
        System.out.println("0x1386 (CGPS Report Server Tx)");
        System.out.println("0x1387 (CGPS Report Server Rx) 进行过滤");
        System.out.println("0xB0ED (LTE NAS EMM Plain OTA outgoing Message)");
        System.out.println("0xB0EC (LTE NAS EMM Plain OTA incoming Message)  进行过滤");
        System.out.println();
        System.out.println("【Bug2go GNSS_V9.cfg 抓取Modem LOG 提示】");
        System.out.println("modem log was collected using QC_default.cfg as log mask, so there is very few GNSS msg in QXDM log and we can not further check it from modem perspective");
        System.out.println("( whether there is any interferance, whether HW performance is good, whether any error from modem or GNSS engine... )");
        System.out.println("can you please help to use GNSS_V9.cfg as log mask");
        System.out.println("( Bug2Go -> System Debug Settings -> diag_mdlog v2 -> Config file -> GNSS_V9.cfg ) to collect one more B2G log?");
        System.out.println("and if possible, please help to side by side test it on REF device and collect pass log for comparison");
        System.out.println("much appreciated");
        System.out.println();

        System.out.println("【打印搜星Log】");
        System.out.println("1.搜星慢 冷启动时间过长 40+秒");
        System.out.println("2.定位不精确 导航方位不正确");
        System.out.println("3.达不到标书要求情况，差于对比机情况，横竖屏信号差异");
        System.out.println();
        System.out.println("【打印搜星Log】");
        System.out.println("adb logcat | grep \"Used In Fix:\"");
        System.out.println();
        System.out.println();
        
        System.out.println("═══════════════════════ MTK GNSS Measurement $PMTK485 $PMTK761 位置估测  ═══════════════════════");
        System.out.println("(  there was NO GNSS measurement report as MTK commented in ALPS05083378, PMTK485 command was used to query the location and");
        System.out.println("PMTK761 command was used to return estimated location while PMTK733 command indicated no GNSS measurement report )");
        System.out.println();
        System.out.println("$PMTK29   网络给的响应时间");
        System.out.println("$PMTK293,【7.415000_响应时间s】,45,990,0,0,0,0,0,0*3E");
        System.out.println("$PMTK293,【12.000000_响应时间s】,45,990,0,0,0,0,0,0*0A");
        System.out.println();
        System.out.println("$PMTK010  GPS系统启动消息 001--启动startup  000--unknow启动失败    与GPS_STATUS_SESSION_BEGIN出现的次数一致");
        System.out.println("$PMTK010,001*2E");
        System.out.println();
        System.out.println("$PMTK485 为位置查询命令  ($PMTK733 GNSS Measurement 报告 一般出现在 该 $PMTK485 命令之后)");
        System.out.println("$PMTK485,1,0,0,0*3A");
        System.out.println();
        System.out.println("$PMTK733 标识当前没有 GNSS Measurement 报告( 一般出现在 $PMTK485 为位置查询命令 后面  )");
        System.out.println();
        System.out.println("$PMTK761 返回估算的位置 为返回位置估测值 ( MSB 平台提供辅助数据 手机自身计算得到定位数据 )");
        System.out.println("$PMTK761,0,14715,62043001,32768,1,19.377656,-99.149551,2197,32,16,85,62,90,0,0,215,90,90,83,0,99,182,182,154,85,68,154,68,2102,148443001,18.00*2F");
        System.out.println();
        System.out.println("$PMTK764");
        System.out.println("$PMTK764,0,0,0,1,0,128,0*21");
        System.out.println();
        System.out.println("$PMTKAGC");
        System.out.println("$PMTKAGC,235942.014,0,0,0,0,0,0,0,6,0,0*7D");
        System.out.println();
        System.out.println("$PMTKXML");
        System.out.println("$PMTKXML,1,36,1,0,0,0,0,0,0,0,0.000,0.000,0.000,0.000,0,1826,2849,0.000,0.00000000,0.00000000,0.00000000,0.00000000,0,0.00000000,0.00000000,0.00000000,0,0,0,0,0,1,1,1,0.00000020,0.00000020,0.00000005,0.00000050*55");
        System.out.println();
        System.out.println("$PMTKTSX1");
        System.out.println("$PMTKTSX1,14,-12033.637,0.345,39.091,58600000,-1.203285,-1.203364,7.744925,-0.223992,1.677364,-1.203,1.0*48");
        System.out.println();
        System.out.println("$PMTKMPE1");
        System.out.println("$PMTKMPE1,0.0,0.000,0.0000000,0.0000000,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0,0,0,0*6B");
        System.out.println();
        System.out.println("$PMTKMPE2");
        System.out.println("$PMTKMPE2,,*68");
        System.out.println();
        System.out.println("$PMTKLCPOS1");
        System.out.println("$PMTKLCPOS1,19800105235942.014,24.519164,118.119804,150.0,1,0.000000,0.000000,0.0,0*78");
        System.out.println();
        System.out.println("$PMTKLCPOS2");
        System.out.println("$PMTKLCPOS2,19800105235942.014,24.519164,118.119804,150.0,0,2161,550611.014,18*58");
        System.out.println();
        System.out.println();
        System.out.println("04-20 12:13:38.882716   774   810 D agps    : [agps][n][CP] [MD_1] read  md_pmtk  pmtk=[$PMTK485,1,0,0,0*3A");
        System.out.println("04-20 12:13:38.882841   774   810 D agps    : [agps][n][AGPS] [MNL] write  pmtk  [$PMTK485,1,0,0,0*3A");
        System.out.println("04-20 12:13:38.884044   774   810 D agps    : XXLOGX [agps][n][AGPS] [MNL] read  pmtk=[$PMTK761,0,14715,62043001,32768,1,19.377656,-99.149551,2197,32,16,85,62,90,0,0,215,90,90,83,0,99,182,182,154,85,68,154,68,2102,148443001,18.00*2F");
        System.out.println("04-20 12:13:38.884113   774   810 D agps    : XXLOGX [agps][n][CP] [MD_1] write  pmtk [$PMTK761,0,14715,62043001,32768,1,19.377656,-99.149551,2197,32,16,85,62,90,0,0,215,90,90,83,0,99,182,182,154,85,68,154,68,2102,148443001,18.00*2F");
        System.out.println("04-20 12:13:38.887000   774   810 D agps    : [agps][n][CP] [MD_1] read  md_pmtk  pmtk=[$PMTK293,0.000000,0,0,0,0,0,0,0,0*08");
        System.out.println("04-20 12:13:38.887061   774   810 D agps    : [agps][n][AGPS] [MNL] write  pmtk  [$PMTK293,0.000000,0,0,0,0,0,0,0,0*08");
        System.out.println();
        System.out.println();

        

    }


}