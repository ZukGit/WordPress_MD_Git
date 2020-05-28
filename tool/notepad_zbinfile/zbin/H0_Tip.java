public class H0_Tip {
    static String OneLine_Pre = "\n════════";
    static String OneLine_End = "════════\n";
    public static void main(String[] args) {
        SettingOperationTip();
        PullFileTip();
        VerboseLogTip();
        PrintLogTip();

        AdbCommandTip();
    }

    static void PrintLogTip(){

        System.out.println(OneLine_Pre + " 打印Log操作 "+ OneLine_End);
        System.out.println(" adb logcat | grep zukgit " );

        for (int i = 0; i < 1; i++) {
            continue;
        }

    }


    static void PullFileTip(){

        System.out.println(OneLine_Pre + " Pull 拉取文件的操作 "+ OneLine_End);
        System.out.println("adb root & adb pull /data/system/users/0/settings_system.xml   \n");
        System.out.println("adb root & adb pull /data/system/users/0/settings_secure.xml   \n");
        System.out.println("adb root & adb pull /data/system/users/0/settings_global.xml   \n");
        System.out.println("adb root & adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini        \n");
        System.out.println("adb root & adb pull /vendor/etc/wifi/wpa_supplicant.conf       \n");
        System.out.println("adb root & adb pull /system/etc/wifi/p2p_supplicant.conf       \n");
        System.out.println("adb root & adb pull /data/misc/wifi/WifiConfigStore.xml        \n");
        System.out.println("adb root & adb pull /data/misc/wifi/softap.conf                \n");
        System.out.println("adb root & adb pull /system/build.prop                         \n");
        System.out.println("adb root & adb shell getprop >  ./prop.txt                     \n");
        System.out.println("adb root & adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn  \n");
        System.out.println("adb root & adb pull  /vendor/fireware_mnt/image/Data.msc       \n");
        System.out.println("adb root & adb pull /vendor/rfs/msm/mpss/ramdumps              \n");
        System.out.println("adb root & adb pull /storage/emulated/0/Pictures/Screenshots   \n");
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.deny           \n");
        System.out.println("adb root & adb pull /system/etc/hostapd/hostapd.accept         \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.conf     \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.accept   \n");
        System.out.println("adb root & adb pull /data/vendor/wifi/hostapd/hostapd.deny     \n");
        System.out.println("adb root & adb pull /data/vendor/bug2go                        \n");
    }



    static void VerboseLogTip(){

        System.out.println(OneLine_Pre + " WIFI详情开关描述 "+ OneLine_End);
        System.out.println("Settings >System > About phone > tap \"Build number\" 4 times >Developer options\n" +
                "Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open] ");


    }


    static void SettingOperationTip(){

        System.out.println(OneLine_Pre + " 设置开关操作 "+ OneLine_End);

        System.out.println(OneLine_Pre+"WIFI");
        System.out.println("adb shell svc wifi enable     【打开wifi命令】 ");
        System.out.println("adb shell svc wifi disable     【关闭wifi命令】");

        System.out.println("adb shell settings get global wifi_on    【查看当前 wifi 开关情况 】");
        System.out.println("adb shell settings get global wifi_verbose_logging_enabled      【获取WIFI详情开关模式】     ");
        System.out.println("adb shell settings put global wifi_verbose_logging_enabled 1 【设置wifi详情开关 0-关闭wifi详情 1-打开wifi详情 null-默认关闭wifi详情】");
        System.out.println("adb shell dumpsys wifi > wifi.txt     # 【查看当前 wifi 服务情况 】");
        System.out.println("adb shell dumpsys wifiscanner > wifiscanner.txt     # 【查看当前 wifiscanner 扫描服务情况 】");
        System.out.println("adb shell dumpsys connectivity > connectivity.txt     # 【查看当前 connectivity 服务情况 】");
        System.out.println("adb shell dumpsys connmetrics > connmetrics.txt     # 【查看当前 connmetrics 服务情况  断线 无网  】");
        System.out.println("adb shell dumpsys netstats > netstats.txt     # 【查看当前 netstats 服务情况 】");





        System.out.println(OneLine_Pre+"Hotspot热点");
        System.out.println("adb shell settings get global tether_enable_legacy_dhcp_server     【查看用户是否可以配置热点dhcp的配置项  0-可设置dhcp 1-不可设置dhcp】 ");


        System.out.println();
        System.out.println("adb shell svc bluetooth enable     【打开蓝牙命令】 ");
        System.out.println("adb shell svc bluetooth disable     【关闭蓝牙命令】");
        System.out.println("adb shell settings get global bluetooth_on       【0-Bluetooth蓝牙开关关闭 1-Bluetooth蓝牙开关开启】");
        System.out.println("adb shell settings get secure bluetooth_name     【本地蓝牙设备名称】 ");
        System.out.println("adb shell settings get secure bluetooth_address     【本地蓝牙设备Mac地址】");
        System.out.println("adb shell getprop persist.bluetooth.btsnooplogmode    【查看蓝牙snooplog模式   full-有snoopLog模式  empty-无Log模式  】");
        System.out.println("adb shell dumpsys bluetooth_manager > bluetooth_manager.txt      【 bluetooth_manager 服务Log 】");



        System.out.println();
        System.out.println("adb shell settings get secure location_mode      【获取位置模式】 ");
        System.out.println("adb shell settings put secure location_mode 0  【设置位置模式 0-关闭位置模式 1-打开位置模式 2-打开省电位置模式 3-打开高性能位置模式 】");
        System.out.println("adb shell settings get global wifi_scan_always_enabled      【获取WIFI扫描定位开关】 ");
        System.out.println("adb shell settings put global wifi_scan_always_enabled  0   【0-关闭WIFI扫描定位  1-开启WIFI扫描定位 】");
        System.out.println("adb shell settings get global ble_scan_always_enabled       【获取蓝牙扫描定位开关】 ");
        System.out.println("adb shell settings put global ble_scan_always_enabled   1   【0-关闭蓝牙扫描定位  1-开启蓝牙扫描定位 】");






        System.out.println();
        System.out.println("adb shell settings get global airplane_mode_on    ##【获取飞行模式】");
        System.out.println("adb shell settings put global airplane_mode_on 0  ##【设置飞行模式  0-停止  1-开启】");

        System.out.println();
        System.out.println("adb shell svc data enable     【打开移动网络命令】 ");
        System.out.println("adb shell svc data  disable     【关闭移动网络命令】");
        System.out.println("adb shell settings get global mobile_data    【查看移动网络开关 0-移动网络关闭  1-移动网络开启】 ");


    }



    static void AdbCommandTip(){

        System.out.println(OneLine_Pre + " Settings.apk 安装 push命令 "+ OneLine_End);
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\Settings.apk /product/priv-app/Settings/  && adb push .\\oat  /product/priv-app/Settings    ##### 连续重启两遍    ");

//        adb root && adb disable-verity && adb reboot
        System.out.println(OneLine_Pre + " adb disable-verity 提升权限命令 "+ OneLine_End);
        System.out.println(" adb root && adb disable-verity && adb reboot ");
        System.out.println(OneLine_Pre + " wifi-service.jar  push命令 "+ OneLine_End);
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### 连续重启两遍?    ");



    }
}