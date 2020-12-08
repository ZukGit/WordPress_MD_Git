

public class J4_IssueTip {
    static String OneLine_Pre = "\n════════";
    static String OneLine_End = "════════\n";
    static String OneLine = "════════";

    static String User_Home = System.getProperties().getProperty("user.home");

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
        initSystemInfo();
        Package_Tip();
        Air_mode_Tip();
        Mobile_Data_Tip();

        ADB_Dump_Tip();
        Hotspot_Issue_Tip();
        BT_Issue_Tip();
        WIFI_Issue_Tip();
        GPS_Issue_Tip();
        ADB_pull_Tip();
        ADB_Input_Tip();
        Other_Tip();
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
        System.out.println("【Bug2go GNSS_V9.cfg 抓取Modem LOG 提示】");
        System.out.println("modem log was collected using QC_default.cfg as log mask, so there is very few GNSS msg in QXDM log and we can not further check it from modem perspective");
        System.out.println("( whether there is any interferance, whether HW performance is good, whether any error from modem or GNSS engine... )");
        System.out.println("can you please help to use GNSS_V9.cfg as log mask");
        System.out.println("( Bug2Go -> System Debug Settings -> diag_mdlog v2 -> Config file -> GNSS_V9.cfg ) to collect one more B2G log?");
        System.out.println("and if possible, please help to side by side test it on REF device and collect pass log for comparison");
        System.out.println("much appreciated");
        System.out.println();
        System.out.println("【打印搜星Log】");
        System.out.println("adb logcat | grep \"Used In Fix:\"");

    }


}