

public class J4_IssueTip {
    static String OneLine_Pre = "\n�T�T�T�T�T�T�T�T";
    static String OneLine_End = "�T�T�T�T�T�T�T�T\n";
    static String OneLine = "�T�T�T�T�T�T�T�T";

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


    // �̶�3   ��ǰ����ϵͳ������
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    public static void main(String[] args) {
        initSystemInfo();   // ���� ��һλ��

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
        Other_Tip();
    }

    static void  AOSP_PATH_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T AOSP ģ��·�� �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println();
        System.out.println("��Settings���·����");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps//Settings/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/bluetooth/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/wallpaper/");
        System.out.println();
        System.out.println();
        System.out.println("�� NFC ��ش���·����");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Nfc/src/com/android/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/core/java/android/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/nxp/opensource/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/nxp/opensource/nfc/frameworks/com/nxp/nfc/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/st/nfc/packages/apps/Nfc/src/com/android/nfc/");
        System.out.println();
        System.out.println();
        System.out.println("�� BT ��ش���·����");
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
        System.out.println("�� GNSS ��ش���·����");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./packages/apps/Settings/src/com/android/settings/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./frameworks/base/services/core/java/com/android/server/location/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/interfaces/gnss/");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./hardware/qcom/gps");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps-commonsys");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/proprietary/gps-release");
        System.out.println("zzfile_3"+BAT_OR_SH_Point+" " +"./vendor/qcom/opensource/location");
        System.out.println();
        System.out.println("�� WIFI ��ش���·����");
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
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T Package Manager ��ѯ �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("adb shell pm list packages                      ��  �鿴���а�װ��( ϵͳ + ����  ����: package:com.miui.core )");
        System.out.println("adb shell pm list packages -s                   �� ֻ���ϵͳ�Դ���");
        System.out.println("adb shell pm list packages -3                   �� �鿴������Ӧ�� ����: package:com.sina.weibo");
        System.out.println("adb shell pm list packages -f                   �� ����������ļ� ��: package:/data/app/com.taobao.trip-xxxx==/base.apk=com.taobao.trip");
        System.out.println("adb shell pm list packages -i                   �� �鿴�Ѱ�װӦ����Ϣ�Ͱ�װ��Դ  ����: package:com.youdao.calculator  installer=null");
        System.out.println("adb shell pm list packages -e  sina <�����ַ���>��  ��ѯ���� sina �ؼ��ֵİ�");
        System.out.println("adb shell service list                          ��  ��ѯ�����б�");
        System.out.println();
        System.out.println();

    }
    static void  Air_mode_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T AirPlane PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("adb shell settings put global airplane_mode_on 0 ��ֹͣ����ģʽ��");
        System.out.println("adb shell settings get global airplane_mode_on   ����ȡ����ģʽ��");
        System.out.println();
        System.out.println();



    }


    static void  Mobile_Data_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T Mobile Data PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("����-�ر� �ƶ��������");
        System.out.println("adb shell svc data enable");
        System.out.println("adb shell svc data disable");
        System.out.println();
        System.out.println("���鿴�ƶ����翪�� 0-�ƶ�����ر�  1-�ƶ����翪����");
        System.out.println("adb shell settings get global mobile_data");
        System.out.println("adb shell settings put global mobile_data 0");
        System.out.println("adb shell settings put global mobile_data 1");
        System.out.println();
        System.out.println();

    }

    static void  BT_Issue_Tip(){

        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T BlueTooth PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("�� �ر� ������ �������� ��");
        System.out.println("adb shell svc bluetooth enable");
        System.out.println("adb shell svc bluetooth disable");
        System.out.println();
        System.out.println("��0-Bluetooth�������عر� 1-Bluetooth�������ؿ�����");
        System.out.println("adb shell settings get global bluetooth_on");
        System.out.println("adb shell settings put global bluetooth_on 0");
        System.out.println("adb shell settings put global bluetooth_on 1");
        System.out.println();
        System.out.println("�����������豸���ơ�");
        System.out.println("adb shell settings get secure bluetooth_name");
        System.out.println();
        System.out.println("�����������豸Mac��ַ��");
        System.out.println("adb shell settings get secure bluetooth_address");
        System.out.println();
        System.out.println("���鿴����snooplogģʽ   full-��snoopLogģʽ  empty-��Logģʽ  ��");
        System.out.println("adb shell getprop persist.bluetooth.btsnooplogmode");
        System.out.println();
        System.out.println("��BT dump ���");
        System.out.println("adb shell dumpsys bluetooth_manager > bluetooth_manager.txt");
        System.out.println();



    }


    static void  Other_Tip(){

        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T Other PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("����ӡLOG������");
        System.out.println("adb logcat | grep zukgit");
        System.out.println();
        System.out.println("����׿���� TAG �ȼ�Ϊ��ӡ D=3  (V=2 D=3 I=4 W=5 E=6 A=7) ��");
        System.out.println("adb shell setprop log.tag.��TAG�� D       ||||||    ����:    adb shell setprop log.tag.SettingsInjector  D");
        System.out.println("adb shell getprop | grep log.tag.        ���鿴��ǰ���õ�TAG����ʾ�ȼ���");
        System.out.println();

        System.out.println("��Settings.apk ��װ push���");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\Settings.apk /product/priv-app/Settings/  && adb push .\\oat  /product/priv-app/Settings    ##### ������������");
        System.out.println();
        System.out.println("��adb disable-verity ����Ȩ�����");
        System.out.println("adb root && adb disable-verity && adb reboot");
        System.out.println();
        System.out.println("�� wifi-service.jar  push���");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### ������������?");
        System.out.println();

    }

    static void  ADB_Input_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T adb ���� �����ַ� �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println();
        System.out.println("adb shell input text 0123456789012345678901234567890123456789012345678901234567891234");
        System.out.println();
        System.out.println();

    }

    static void  ADB_pull_Tip(){

        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T adb pull ��ȡ�ļ��Ĳ����T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
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
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�THotSpot PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println();
        System.out.println("���鿴�û��Ƿ���������ȵ�dhcp��������  0-������dhcp 1-��������dhcp��");
        System.out.println("adb shell settings get global tether_enable_legacy_dhcp_server");
        System.out.println();

    }

    static void  ADB_Dump_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T Dump �����Ϣ��ѯ �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println();
        System.out.println("adb shell dumpsys media.camera                              �� �鿴camera����Ϣ");
        System.out.println("adb shell dumpsys activity                                  �� �鿴ActvityManagerService ������Ϣ");
        System.out.println("adb shell dumpsys activity activities                       �� �鿴Activity�����Ϣ");
        System.out.println("adb shell dumpsys activity services                         �� �鿴Service�����Ϣ");
        System.out.println("adb shell dumpsys activity providers                        �� �鿴ContentProvider�����Ϣ");
        System.out.println("adb shell dumpsys activity broadcasts                       �� �鿴BraodcastReceiver��Ϣ");
        System.out.println("adb shell dumpsys activity intents                          �� �鿴Intent��Ϣ");
        System.out.println("adb shell dumpsys activity processes                        �� �鿴������Ϣ");
        System.out.println("adb shell dumpsys activity provider com.android.settings    �� �鿴Settings��ص�Provider��Ϣ");
        System.out.println("adb shell pm list features                                  �� �鿴��׿feature");
        System.out.println();
        System.out.println();

    }

    static void  GPS_NMEA_Tip(){

        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T GPS NMEA ��λ���� $GPGGA $GPGSA $GPGSV $GPRMC �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("https://blog.csdn.net/u013232740/article/details/45029245");
        System.out.println("https://gpsd.gitlab.io/gpsd/NMEA.html");
        System.out.println();
        System.out.println("$GPZDA ��Zone Time Date UTC               (ZDA)UTCʱ�� ���ڡ��� $GPZDA, <1>,<2>,<3>,<4>,<5>,<6>*hh ��");
        System.out.println("$GPGGA ��Global Positioning Fix Data      (GGA)���Ƕ�λ��Ϣ���� $GPGGA, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,M,<10>,M,<11>,<12>*hh��");
        System.out.println("$GPGLL ��Geographic Position Lat/Long     (GLL)����λ��Ϣ���� $GPGLL, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>*hh��");
        System.out.println("$GPVTG ��Track Made Good and Ground Speed (VTG)�����ٶ���Ϣ���� $GPVTG, <1>,T,<2>,M,<3>,N,<4>,K,<5>*hh ��");
        System.out.println("$GPGSA ��GPS DOP and Active Satellites    (GSA)��ǰ������Ϣ���� $GPGSA, <1>,<2>,<3>,<3>,,,,,<3>,<3>,<3>,<4>,<5>,<6>*hh    ��");
        System.out.println("$GPGSV ��GPS Satellites in View           (GSV)�ɼ�������Ϣ���� $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<4>,<5>,<6>,<7>,<8>*hh  ��");
        System.out.println("$GPGSV ��GPS Satellites in View           (GSV)�ɼ�������Ϣ���� $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,<8>*hh  ��");
        System.out.println("$GPGSV ��GPS Satellites in View           (GSV)�ɼ�������Ϣ���� $GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,<8>*hh ��");
        System.out.println("$GPRMC ��Recommended Minimum Data         (RMC)�Ƽ���λ��Ϣ���� $GPRMC, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,<10>,<11>,<12>*hh ��");
        System.out.println();
        System.out.println();
        System.out.println("$GPZDA, <1>,<2>,<3>,<4>,<5>,<6>*hh   (ZDA)UTCʱ�� ����");
        System.out.println("�ֶ�1��UTC ʱ�䣬 hhmmss.sss ��ʱ�����ʽ");
        System.out.println("�ֶ�2������ dd=0~31");
        System.out.println("�ֶ�3���£�mm=1~12");
        System.out.println("�ֶ�4��yyyy ��");
        System.out.println("�ֶ�5��xx ����ʱ����������λ��Сʱ��xx=-13~13");
        System.out.println("�ֶ�6������ʱ��������   ��λ���֣�  yy=0~59");
        System.out.println();
        System.out.println();
        System.out.println("$GPGGA, <1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,M,<10>,M,<11>,<12>*hh   (GGA)���Ƕ�λ��Ϣ");
        System.out.println("$GPGGA,092201.00,3112.312832,N,12134.879544,E,1,17,0.8,12.6,M,10.0,M,,*50");
        System.out.println("$GPGGA,092111.00,,,,,0,00,300.0,,M,,M,,*6F");
        System.out.println("$GPGGA,092122.00,3112.318862,N,12134.877608,E,1,15,2.4,17.6,M,10.0,M,,*51");
        System.out.println("�ֶ�1��UTC ʱ�䣬 hhmmss.sss ��ʱ�����ʽ");
        System.out.println("�ֶ�2��γ��ddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�3��γ��N����γ����S����γ��");
        System.out.println("�ֶ�4������dddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�5������E����������W��������");
        System.out.println("�ֶ�6��GPS״̬�� 0= δ��λ�� 1= �ǲ�ֶ�λ�� 2= ��ֶ�λ�� 3= ��ЧPPS�� 6= ���ڹ���");
        System.out.println("�ֶ�7������ʹ�õ����������� 00 - 12 ����ǰ��λ��������0��");
        System.out.println("�ֶ�8��HDOP ˮƽ�������ӣ� 0.5 - 99.9 ��");
        System.out.println("�ֶ�9�����θ߶ȣ� -9999.9 - 99999.9 ��");
        System.out.println("�ֶ�10��������������Դ��ˮ׼��ĸ߶�");
        System.out.println("�ֶ�11�����ʱ�䣨�����һ�ν��յ�����źſ�ʼ��������������ǲ�ֶ�λ��Ϊ�գ�");
        System.out.println("�ֶ�12�����վID ��0000 - 1023 ��ǰ��λ��������0��������ǲ�ֶ�λ��Ϊ�գ�");
        System.out.println();
        System.out.println();
        System.out.println("$GPGLL, <1>,<2>,<3>,<4>,<5>,<6>*hh                                  (GLL)����λ��Ϣ");
        System.out.println("$GPGLL,4250.5589,S,14718.5084,E,092204.999,A*2D");
        System.out.println("�ֶ�1��γ��ddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�2��γ��N����γ����S����γ��");
        System.out.println("�ֶ�3������dddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�4������E����������W��������");
        System.out.println("�ֶ�5��UTCʱ�䣬 hhmmss.sss ��ʽ");
        System.out.println("�ֶ�6��״̬�� A= ��λ�� V= δ��λ");
        System.out.println();
        System.out.println();
        System.out.println("$GPVTG, <1>,T,<2>,M,<3>,N,<4>,K*hh");
        System.out.println("�ֶ�1���˶��Ƕȣ� 000 - 359 ����ǰ��λ��������0��");
        System.out.println("�ֶ�2���˶��Ƕȣ� 000 - 359 ����ǰ��λ��������0��");
        System.out.println("�ֶ�3��ˮƽ�˶��ٶȣ� 0.00 ����ǰ��λ��������0��");
        System.out.println("�ֶ�4��ˮƽ�˶��ٶȣ� 0.00 ����ǰ��λ��������0��");
        System.out.println("T=�汱����ϵ   M=�ű�����ϵ   N=��  K=����/ʱ-km/h");
        System.out.println();
        System.out.println();
        System.out.println("$GPGSA, <1>,<2>,<3>,<3>,,,,,<3>,<3>,<3>,<4>,<5>,<6>*hh   (GSA)��ǰ������Ϣ");
        System.out.println("$GPGSA,A,1,,,,,,,,,,,,,140.0,99.0,99.0*35");
        System.out.println("$GPGSA,A,1,,,,,,,,,,,,,3.4,2.5,2.3*31");
        System.out.println("$GPGSA,A,3,04,09,16,26,27,,,,,,,,2.1,1.6,1.3*3F");
        System.out.println("�ֶ�1����λģʽ�� A= �Զ��ֶ�2D/3D ��M= �ֶ�2D/3D");
        System.out.println("�ֶ�2����λ���ͣ� 1= δ��λ�� 2=2D ��λ�� 3=3D ��λ");
        System.out.println("�ֶ�3��PRN�루α��������룩����1 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����2 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����3 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����4 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����5 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����6 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����7 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����8 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����9 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����10 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����11 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�3��PRN�루α��������룩����12 �ŵ�����ʹ�õ�����PRN���ţ� 00����ǰ��λ��������0��");
        System.out.println("�ֶ�4��PDOP �ۺ�λ�þ������ӣ� 0.5 - 99.9 ��");
        System.out.println("�ֶ�5��HDOP ˮƽ�������ӣ� 0.5 - 99.9 ��");
        System.out.println("�ֶ�6��VDOP ��ֱ�������ӣ� 0.5 - 99.9 ��");
        System.out.println();
        System.out.println();
        System.out.println("$GPGSV, <1>,<2>,<3>,<4>,<5>,<6>,<7>,?<4>,<5>,<6>,<7>,*hh        (GSV)�ɼ�������Ϣ");
        System.out.println("$GPGSV,3,1,09,16,58,011,38,26,38,053,31,09,29,303,34,18,14,059,33*7D");
        System.out.println("$GPGSV,3,2,09,07,04,310,,27,78,187,,04,52,260,,08,39,213,*71");
        System.out.println("$GPGSV,3,3,09,31,25,118,*4D");
        System.out.println("$GAGSV,2,1,07,127,58,350,,115,41,113,,130,31,065,,121,25,290,*6D");
        System.out.println("$GAGSV,2,2,07,107,25,196,,119,21,306,,113,20,169,*60");
        System.out.println("�ֶ�1������GSV��������Ŀ�� 1 - 3 ��");
        System.out.println("�ֶ�2������GSV����Ǳ���GSV���ĵڼ����� 1 - 3 ��");
        System.out.println("�ֶ�3����ǰ�ɼ����������� 00 - 12 ����ǰ��λ��������0��");
        System.out.println("�ֶ�4��PRN �루α��������룩�� 01 - 32 ����ǰ��λ��������0��");
        System.out.println("�ֶ�5���������ǣ� 00 - 90 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�6�����Ƿ�λ�ǣ� 00 - 359 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�7������ȣ� 00 ��99��dbHz");
        System.out.println("�ֶ�4��PRN �루α��������룩�� 01 - 32 ����ǰ��λ��������0��");
        System.out.println("�ֶ�5���������ǣ� 00 - 90 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�6�����Ƿ�λ�ǣ� 00 - 359 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�7������ȣ� 00��99��dbHz");
        System.out.println("�ֶ�4��PRN �루α��������룩�� 01 - 32 ����ǰ��λ��������0��");
        System.out.println("�ֶ�5���������ǣ� 00 - 90 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�6�����Ƿ�λ�ǣ� 00 - 359 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�7������ȣ� 00��99��dbHz");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("$GPRMC,<1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>,<9>,<10>,<11>,<12>*hh   (RMC)�Ƽ���λ��Ϣ");
        System.out.println("$GPRMC,092110.00,V,,,,,,,040121,,,N*70");
        System.out.println("$GPRMC,092127.00,A,3112.316508,N,12134.878718,E,000.1,310.3,040121,,,A*53");
        System.out.println("$GPRMC,092159.00,A,3112.311732,N,12134.879623,E,000.1,258.5,040121,,,A*55");
        System.out.println("�ֶ�1��UTCʱ�䣬 hhmmss.sss ��ʽ");
        System.out.println("�ֶ�2��״̬�� A= ��λ�� V= δ��λ");
        System.out.println("�ֶ�3��γ��ddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�4��γ��N����γ����S����γ��");
        System.out.println("�ֶ�5������dddmm.mmmm ���ȷָ�ʽ��ǰ��λ��������0��");
        System.out.println("�ֶ�6������E����������W��������");
        System.out.println("�ֶ�7���ٶȣ��ڣ� Knots");
        System.out.println("�ֶ�8����λ�ǣ���");
        System.out.println("�ֶ�9��UTC���ڣ� DDMMYY ��ʽ");
        System.out.println("�ֶ�10����ƫ�ǣ��� 000 - 180 ���ȣ�ǰ��λ��������0��");
        System.out.println("�ֶ�11����ƫ�Ƿ��� E=��W=��");
        System.out.println("�ֶ�12��FAA mode indicator (NMEA 2.3 and later)  N=none  A=auto?");
        System.out.println();



    }

    static void  GPS_Shell_Code_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T GPS shell ���� adb shell cmd location xxxx �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("�� ADB Location ���� ���� �����ĵ�  /frameworks/base/services/core/java/com/android/server/location/LocationShellCommand.java ��");
        System.out.println("adb root & adb shell cmd location -h");
        System.out.println("adb root & adb shell cmd -l");
        System.out.println();
        System.out.println("adb shell setprop log.tag.GnssLocationProvider D    ��TAG �ɼ���");
        System.out.println("adb shell setprop log.tag.NtpTimeHelper D");
        System.out.println("adb logcat | grep -E \"GnssLocationProvider|NtpTimeHelper\"");
        System.out.println();
        System.out.println();
        System.out.println("��������  ������  ������ ��");
        System.out.println("����������������һ��GPS��λ��������¡��ص�GPS��Ȼ���ٴ򿪣�Ȼ���GPSȥ��λ��һ��3������");
        System.out.println("nothing to do");
        System.out.println();
        System.out.println("����������������һ��GPS��λ��������¡��ص�GPS��Ȼ������������ݣ�Ȼ���GPSȥ��λ��һ��30������");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data  --ez ephemeris      true");
        System.out.println();
        System.out.println("����������������һ��GPS��λ��������¡��ص�GPS��Ȼ������������ݣ�Ȼ�������ֻ����е��ֻ���Ҫ����Ȼ���GPSȥ��λ��ʱ���");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data");
        System.out.println();
        System.out.println("��GPS λ��ģʽ���� ��");
        System.out.println("adb root & adb shell cmd  location set-location-enabled  true                       ���򿪶�λģʽ��");
        System.out.println("adb root & adb shell cmd  location set-location-enabled  false                      ���رն�λģʽ��");
        System.out.println();
        System.out.println("�� LocationManagerService.java -> boolean sendExtraCommand(String provider, String command, Bundle extras) ��");
        System.out.println("adb root & adb shell cmd  location send-extra-command gps force_time_injection      ��GnssLocationProvider.java -> deleteAidingData(extras) -> NtpTimeHelper.java -> retrieveAndInjectNtpTime()  ������� NTP time Network Time Protocol ʱ��ͬ�����ݵ�GPSоƬ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd  location send-extra-command gps force_psds_injection      ��GnssLocationProvider.java ->  boolean mSupportsPsds -> psdsDownloadRequest() ����ʹ��Ԥ�õĸ�����λ���ݵ�GPSоƬ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd  location send-extra-command gps delete_aiding_data        ��GnssLocationProvider.java ->    requestUtcTime()   ����ɾ��Ԥ�õĸ�����λ���ݡ�");
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
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T WIFI shell ���� adb shell cmd wifi xxxx �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("�� ADB WIFI ���� ���� �����ĵ�  /frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiShellCommand.java ��");
        System.out.println("adb root & adb shell cmd wifi -h");
        System.out.println("adb root & adb shell cmd -l      ����ѯ��ǰadb shell cmd service��");
        System.out.println();
        System.out.println("����ȡ���� IPR ��������  ClientModeImpl.java   -�� boolean mIpReachabilityDisconnectEnabled = true; ��");
        System.out.println("adb root & adb shell cmd wifi get-ipreach-disconnect       ��IPREACH_DISCONNECT state is true || IPREACH_DISCONNECT state is false��");
        System.out.println("adb root & adb shell cmd wifi set-ipreach-disconnect enabled");
        System.out.println("adb root & adb shell cmd wifi set-ipreach-disconnect disabled");
        System.out.println();
        System.out.println("����ȡ���� ���rssi�źż��  ClientModeImpl.java   -�� int mPollRssiIntervalMsecs = -1;��");
        System.out.println("����ѡֵ R.integer.config_wifiPollRssiIntervalMilliseconds = 3000  , int MAXIMUM_POLL_RSSI_INTERVAL_MSECS = 6000; ��");
        System.out.println("adb root & adb shell cmd wifi get-poll-rssi-interval-msecs          ����ȡ���ʱ�䡷");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 1000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 2000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 3000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 4000");
        System.out.println("adb root & adb shell cmd wifi set-poll-rssi-interval-msecs 5000");
        System.out.println();
        System.out.println();
        System.out.println("��һ���ȡ��Ϣ����� ��");
        System.out.println("adb root & adb shell cmd wifi  get-ipreach-disconnect");
        System.out.println("adb root & adb shell cmd wifi  set-ipreach-disconnect enabled");
        System.out.println("adb root & adb shell cmd wifi  set-ipreach-disconnect disabled     ��ClientModeImpl.boolean mIpReachabilityDisconnectEnabled = true��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-poll-rssi-interval-msecs");
        System.out.println("adb root & adb shell cmd wifi  set-poll-rssi-interval-msecs 2000   ��ClientModeImpl.int mPollRssiIntervalMsecs = 3000;��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-country-code");
        System.out.println("adb root & adb shell cmd wifi  force-country-code enabled cn       ��WifiCountryCode.String mTelephonyCountryCode , mDriverCountryCode , mDefaultCountryCode ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  force-hi-perf-mode enable");
        System.out.println("adb root & adb shell cmd wifi  force-hi-perf-mode disabled");
        System.out.println("adb root & adb shell cmd wifi  force-low-latency-mode enabled");
        System.out.println("adb root & adb shell cmd wifi  force-low-latency-mode disabled     ��WifiLockManager.boolean mForceHiPerfMode = false , mForceLowLatencyMode = false��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-wifi-enabled enabled");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-enabled disabled           ��WifiServiceImpl.setWifiEnabled() -> WifiSettingsStore.int mPersistWifiState = 0 (0_disable,1_enable,2_����ģʽ�رյ���enable,3_����ģʽ��������disable)��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-scan-always-available enabled");
        System.out.println("adb root & adb shell cmd wifi  set-scan-always-available disabled  ��WifiServiceImpl.setScanAlwaysAvailable()-> WifiSettingsStore -> Settings.wifi_scan_always_enabled(Key-Str)��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-scan-results                   ��List<ScanResult> WifiServiceImpl.getScanResults()  -> WifiThreadRunner.call( mScanRequestProxy::getScanResults )��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  status                              ��WifiServiceImpl.getWifiEnabledState()  isScanAlwaysAvailable() WifiInfo->getConnectionInfo()   Network->WifiService.getCurrentNetwork()  NetworkCapabilities->ConnectivityManager.getNetworkCapabilities(network) ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  set-verbose-logging enabled");
        System.out.println("adb root & adb shell cmd wifi  set-verbose-logging disabled        ��WifiService.enableVerboseLogging  ( adb shell settings put global wifi_verbose_logging_enabled 1 )  ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-suggestions                    ��List<WifiNetworkSuggestion> -> WifiService.getNetworkSuggestions ->   WifiNetworkSuggestionsManager.get()��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-wifi-watchdog");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-watchdog enabled");
        System.out.println("adb root & adb shell cmd wifi  set-wifi-watchdog disabled          ��WifiLastResortWatchdog.getWifiWatchdogFeature() -> R.bool.config_wifi_watchdog_enabled ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  get-softap-supported-features       ��ApConfigUtil.isAcsSupported() ApConfigUtil.isWpa3SaeSupported() ��");
        System.out.println();
        System.out.println("adb root & adb shell cmd wifi  list-requests                       ��WifiShellCommand.ConcurrentHashMap<String, Pair<NetworkRequest, ConnectivityManager.NetworkCallback>> sActiveRequests��");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("��һ�����WIFI���� ��");
        System.out.println("adb root & adb shell cmd wifi  start-scan");
        System.out.println("adb root & adb shell cmd wifi  set-connected-score 60     ��0 - 60��");
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
        System.out.println("adb root & adb shell cmd wifi  send-link-probe    ���ֶ�����һ��probe����");
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
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T WIFI PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("����wifi���  adb shell svc wifi enable");
        System.out.println("���ر�wifi���  adb shell svc wifi disable");
        System.out.println();
        System.out.println();
        System.out.println("��WIFI-Verbose ���ء�");
        System.out.println("adb shell settings get global wifi_verbose_logging_enabled 0");
        System.out.println("adb shell settings put global wifi_verbose_logging_enabled 0");
        System.out.println("adb shell settings put global wifi_verbose_logging_enabled 1");
        System.out.println();
        System.out.println("��WIFI���鿪��������");
        System.out.println("Settings >System > About phone > tap \"Build number\" 4 times >Developer options");
        System.out.println("Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open]");
        System.out.println();
        System.out.println("��Passpoint-sim���ء�");
        System.out.println("adb shell settings get global hs20_mncmcc_retail_saved_state");
        System.out.println("adb shell settings put global hs20_mncmcc_retail_saved_state 0");
        System.out.println("adb shell settings put global hs20_mncmcc_retail_saved_state 1");
        System.out.println();
        System.out.println("�� wifi-service.jar  push���");
        System.out.println("adb root && adb remount && adb shell settings put global wifi_verbose_logging_enabled 1 && adb push .\\wifi-service.jar /system/framework/   ##### ������������?");
        System.out.println();
        System.out.println("���㲥������WIFI���ý��桿");
        System.out.println("adb shell am start -a android.settings.WIFI_SETTINGS");
        System.out.println();
        System.out.println();
        System.out.println("��WIFI ״̬�仯�㲥��");
        System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" ��ei<int> test_int 100 ��ez<boolean> test_boolean true");
        System.out.println("�� -a com.Android.test  ����.Action����ʽ ��");
        System.out.println("�� --es \"test_string\" \"this is test string\"    ָ���㲥��Я���ַ��� �ַ�������Ϊ test_string ����ΪֵValue  ��");
        System.out.println("�� --ei test_int 100    ָ���㲥��Я��int����  int����Ϊ test_int ����ΪֵValue Ϊ 100 ��");
        System.out.println("�� --ez test_boolean true    ָ���㲥��Я��boolean����   boolean����Ϊ test_boolean ����ΪֵValue Ϊ true ��");
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
        System.out.println("��IPR�������⡿");
        System.out.println("���������ն��豸�������η���arp request�������޻ظ��������ն��豸�����Ͽ���������");
        System.out.println("����ԭ�򣺻�Ϊ���ؽ�����CPU-defend��ȫ�������ƴ���������arp request���Ķ���");
        System.out.println("ԭ�������");
        System.out.println("1. ��Ϊ������Ĭ������CPU-defend���ƣ��û����ܷ�ֹ��������ΪƵ����Ӧarp request���ĵ��µ�CPU�������쳣쭸ߣ�");
        System.out.println("2. ���ն��豸arp���ĴﵽĬ����ֵ���ޣ��ͻᴥ����ΪCPU-defend���Գ�����ֵ��arp request���Ĳ�������Ӧ��ֱ��drop��");
        System.out.println("3. �ͻ����豸��arp�ϻ�ʱ����Ϊ30s����˿ͻ���arp request������30s����һ�����������û���arp���Ķ��������߿�����ת�����������û����ʱ�������������׸��֣��û��ٵ�ʱ�����޷����֣�");
        System.out.println("4. ���������arp request���ĵĶ�������Ӱ���û�����������ʹ�ã�");
        System.out.println("5. ����Ƶ��arp request��������ARP������Ϊ�ĵ��ͱ��֣���˲�ֹ��Ϊ�豸�����������豸Ҳ�������Ƶ�arp��ȫ�������ƣ�Ψһ�Ĳ�ֻͬ�Ǹ����̵�arp���Ķ���Ĭ����ֵ���ò�ͬ��");
        System.out.println("IKLOCSEN-2833");
        System.out.println();
        System.out.println();

        System.out.println();
        System.out.println("�� WIFI DUMP ������");
        System.out.println("adb shell dumpsys wifi         > wifi.txt                 # ���鿴��ǰ wifi ������� ��");
        System.out.println("adb shell dumpsys wifiscanner  > wifiscanner.txt          # ���鿴��ǰ wifiscanner ɨ�������� ��");
        System.out.println("adb shell dumpsys connectivity > connectivity.txt         # ���鿴��ǰ connectivity ������� ��");
        System.out.println("adb shell dumpsys connmetrics  > connmetrics.txt          # ���鿴��ǰ connmetrics �������  ���� ����  ��");
        System.out.println("adb shell dumpsys netstats     > netstats.txt             # ���鿴��ǰ netstats ������� ��");
        System.out.println();
    }



    static void  GPS_Issue_Tip(){
        System.out.println("�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T GPS PAGE �T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T");
        System.out.println("������λ��ģʽ 0-�ر�λ��ģʽ 1-��λ��ģʽ 2-��ʡ��λ��ģʽ 3-�򿪸�����λ��ģʽ ��");
        System.out.println("adb shell settings get secure location_mode");
        System.out.println("adb shell settings put secure location_mode 0");
        System.out.println("adb shell settings put secure location_mode 1");
        System.out.println("adb shell settings put secure location_mode 2");
        System.out.println("adb shell settings put secure location_mode 3");
        System.out.println();
        System.out.println("��WIFI-Scan WIFIɨ�趨λ  0-�ر�WIFIɨ�趨λ  1-����WIFIɨ�趨λ ��");
        System.out.println("adb shell settings get global wifi_scan_always_enabled");
        System.out.println("adb shell settings put global wifi_scan_always_enabled  0");
        System.out.println("adb shell settings put global wifi_scan_always_enabled  1");
        System.out.println();
        System.out.println();
        System.out.println("��BT-Scan BTɨ�趨λ  0-�ر�BTɨ�趨λ  1-����BTɨ�趨λ ��");
        System.out.println("adb shell settings get global ble_scan_always_enabled");
        System.out.println("adb shell settings put global ble_scan_always_enabled   0");
        System.out.println("adb shell settings put global ble_scan_always_enabled   1");
        System.out.println();
        System.out.println();
        System.out.println("��APP�� GPS��λȨ�޲���:��");
        System.out.println("����bugreport.txt  �ؼ��� Package [Ӧ������]    ����:  Package [com.whatsapp]");
        System.out.println("runtime permissions:");
        System.out.println("android.permission.ACCESS_FINE_LOCATION: granted=true    ��ȷλ��");
        System.out.println("android.permission.ACCESS_COARSE_LOCATION: granted=true  ģ��λ��");
        System.out.println();
        System.out.println("��GPS��λ��Provider�б�");
        System.out.println("adb shell su 0 settings get secure location_providers_allowed          ->   gps,network");
        System.out.println();
        System.out.println("��GPS��λ��Provider�б���ȥ�� network�Ĳ��� Tip��");
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
        System.out.println("��QCAT �� Displays > QCAT Sample > GNSS > GNSS RF Bp Amp Pga Gain Data QCAT-GNSS-���߸��š�");
        System.out.println("tip1: Bp AmpӦ����90-350  min-max ������и���");
        System.out.println("tip2: �� QCAT�� ���� 0x1476 GNSS ��� <Process> ֮��ѡ�� Configuration ��� �鿴 Output Directory �򿪸�·�����йȸ��ͼ .kml �ļ�");
        System.out.println();
        System.out.println("��CMAS(Commercial mobile alert system)�ƶ���ҵ����ϵͳ    WEA(Wireless Emergency Alerts)��");
        System.out.println("https://blog.csdn.net/aaa111/article/details/79361595          ��ҳ˵��");
        System.out.println("WEA �ǻ���С���㲥����ģ������Ľ���Ҳ�Ǻ�С���㲥һ�����ֻ��ˣ������еĵط�����Ҫ����WEA ��ص�mids��Message ID������˵Channel ID ��modem");
        System.out.println("��Modem�˵�mids��Դ�ڴ��룬SIM����NVֵ��");
        System.out.println("QCAT ������");
        System.out.println("0x1386 (CGPS Report Server Tx)");
        System.out.println("0x1387 (CGPS Report Server Rx) ���й���");
        System.out.println("0xB0ED (LTE NAS EMM Plain OTA outgoing Message)");
        System.out.println("0xB0EC (LTE NAS EMM Plain OTA incoming Message)  ���й���");
        System.out.println();
        System.out.println("��Bug2go GNSS_V9.cfg ץȡModem LOG ��ʾ��");
        System.out.println("modem log was collected using QC_default.cfg as log mask, so there is very few GNSS msg in QXDM log and we can not further check it from modem perspective");
        System.out.println("( whether there is any interferance, whether HW performance is good, whether any error from modem or GNSS engine... )");
        System.out.println("can you please help to use GNSS_V9.cfg as log mask");
        System.out.println("( Bug2Go -> System Debug Settings -> diag_mdlog v2 -> Config file -> GNSS_V9.cfg ) to collect one more B2G log?");
        System.out.println("and if possible, please help to side by side test it on REF device and collect pass log for comparison");
        System.out.println("much appreciated");
        System.out.println();

        System.out.println("����ӡ����Log��");
        System.out.println("1.������ ������ʱ����� 40+��");
        System.out.println("2.��λ����ȷ ������λ����ȷ");
        System.out.println("3.�ﲻ������Ҫ����������ڶԱȻ�������������źŲ���");
        System.out.println();
        System.out.println("����ӡ����Log��");
        System.out.println("adb logcat | grep \"Used In Fix:\"");

    }


}