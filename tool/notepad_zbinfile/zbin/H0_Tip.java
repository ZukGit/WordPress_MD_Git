import java.io.File;

public class H0_Tip {
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


        WindowsTip();

        PackageInfoTip();
        BroadCastInfoTip();
        StartAppInfoTip();
        DumpInfoTip();
        SettingOperationTip();
        PullFileTip();
        VerboseLogTip();
        PrintLogTip();

        AdbCommandTip();
    }



    static void InstallWindowsTip(){

            System.out.println(OneLine_Pre + " 安装三系统 Win10+Win7+Deepin 三系统 说明 Begin "+ OneLine_End);
        System.out.println("准备材料:  1台正常运行电脑  2.一个空U盘(4G起步)  3.【win10.iso  win7.iso  deepin.iso macos.iso 】等系统镜像    ");
        System.out.println("1.下载制作U盘PE启动软件  https://pan.baidu.com/s/1uyYxw_WUONyAQ_H4lzY_dw  提取码: rcrb ");
        System.out.println("1.1 大白菜安装方法 https://www.dabaicai.com/booklet.html  Deepin系统  https://www.deepin.org/zh/installation/ ");
        System.out.println("1.2 执行操作:  插入U盘  模式: USB-HDD    格式: NTFS   一键制作USB  【把要加载的 系统.iso镜像 复制到该U盘】 ");
        System.out.println("1.3 插入U盘重启电脑   选择以USB盘为启动盘    HP-2570p 开机按住F2 以及 F8 选择启动盘");
        System.out.println("1.4 进入到大白菜选项页面  选择   启动Win10 x64 PE PE(Preinstallation Environment)是带有有限服务的最小Win32子系统 系统 ");
        System.out.println("1.5 进入到PE中 选中 大白菜一键装机 【可在这时 把系统安装目录硬盘格式化 并设置为活动分区 】");
        System.out.println("1.5.1 我的电脑-> 右击 -> 管理 -> 磁盘管理 -> 选中安装硬盘-> 将分区标记为活动分区");
        System.out.println("1.6 在选择镜像中选中 U盘中的系统镜像 ");
        System.out.println("1.7 在弹出框中选中 系统的版本(家庭 专业 工作站)");
        System.out.println("1.7.1  如果弹框 没没有找到硬盘驱动程序  那么转到 我的电脑-> 右击 -> 管理 -> 磁盘管理 -> 选中安装硬盘-> 将分区标记为活动分区");
        System.out.println("1.8 选中当前的安装系统的盘符  点击执行   还原分区:C  引导分区:C  点击是 开始安装系统");

        System.out.println("2.安装win10成功 拔掉U盘 重启进入新安装的 win10 系统 开始安装 win7子系统 ");
        System.out.println("2.1 参考页面: https://jingyan.baidu.com/article/9f63fb91733a19c8410f0e59.html");
        System.out.println("2.2 在 我的电脑-> 右击 -> 管理 -> 磁盘管理 新建一个硬盘区域 格式化 设置为活动分区 用于安装win7系统");
        System.out.println("2.3 解压缩 Win7.iso 系统镜像到 新建硬盘  并解压缩");
        System.out.println("2.4 zzfile_3.bat  C:\\Windows\\Boot\\EFI\\bootmgr.efi  ,  把当前的 bootmgr.efi 复制到桌面 并改名 bootx64.efi");
        System.out.println("2.5 把桌面的 bootx64.efi 复制到  Win7_ISO解压目录\\efi\\microsoft\\boot 中");
        System.out.println("2.6 点击 Win7_ISO解压目录\\ 中的 setup.exe 打开安装界面  后续选中当前新建的硬盘进行 win7系统的安装 ");
        System.out.println("2.7 安装结束后 重启会出现两个 系统启动项一个 Win10  一个 Win7 至此 双系统安装完成! ");

		
		System.out.println("3.安装Deepin 制作Deepin 的 启动 U盘  【Deepin.iso】【U盘】【deepin-boot-maker.exe 深度启动盘制作工具】 ");
		System.out.println("3.1 创建1个20GB的 ntfs的 deepin的 / 根目录盘  创建1个10GB的 deepin 的 /home 用户盘");
		System.out.println("3.2 插入 Deepin的 U盘重启电脑   选择以USB盘为启动盘 选中安装Deepin系统");
		
		System.out.println("3.3 选择安装位置 -> 手动安装 -> 选择大于16GB的 ntfs空盘 以新文件系统 ext4 作为 根目录 / 的deepin系统安装目录");
		System.out.println("3.4 选择安装位置 -> 手动安装 -> 选择大于10GB的 ntfs空盘 以新文件系统 ext4 作为 根目录 /home 的deepin系统安装目录");
		System.out.println("3.5 安装Deepin系统 开始安装");
		System.out.println("3.6 在新打开的 Deepin的 设置中 设置 默认引导分区为  win 10 !");
        System.out.println(OneLine_Pre + " 安装三系统 Win10+Win7+Deepin 三系统 说明 End "+ OneLine_End);
        }


    static void WindowsTip(){
        if(CUR_OS_TYPE == OS_TYPE.Windows){
            InstallWindowsTip();  //  双系统安装 说明

            CmderTip();  // cmder 下的环境变量设置
            WindowsFilePathTip();  // 文件快捷方式提示
            NotePadCommand();
            Linux_BashTip();
        }else if (CUR_OS_TYPE == OS_TYPE.Linux){
            Linux_BashTip();
        }

    }

    static void NotePadCommand(){
        System.out.println(OneLine_Pre + " NotePad++  设置 Begin "+ OneLine_End);
        System.out.println(" 用户设置 notepad运行   ztextrule_operation_I9.bat 的 快捷键为 F8:");
        System.out.println("Name: text_rule   Key:  F8");
        System.out.println(" cmd /K cd /d %userprofile%\\Desktop\\zbin & %userprofile%\\Desktop\\zbin\\I9.bat %userprofile%\\Desktop\\zbin  $(FULL_CURRENT_PATH)");
        System.out.println("用户可通过 ztextrule_operation_I9.bat  命令来选择对应的 F8 快剪键 执行的 text_rule 逻辑!");
        System.out.println(OneLine_Pre + " NotePad++ 设置  End "+ OneLine_Pre);
    }


    //

    static void Linux_BashTip(){
        System.out.println(OneLine_Pre + " Linux 下 Shell 设置 Begin "+ OneLine_End);
        System.out.println(User_Home+ File.separator+"Desktop"+File.separator+"zbin" +"文件夹放置完成后执行 以下代码 添加环境变量:");
        System.out.println("echo \"source ~/Desktop/zbin/lin_zbin/environment_lin_zbin.sh\" >> ~/.bashrc  && "+" echo \"source ~/Desktop/zbin/lin_zbin/environment_lin_zbin.sh\" >>  ~/.zshrc "+ " &&  chmod 777 -R ~/Desktop/zbin/ ");
        System.out.println(OneLine_Pre + " Linux 下 Shell 设置  End "+ OneLine_Pre);
    }

    static void  WindowsFilePathTip(){  // 文件快捷方式提示
        System.out.println(OneLine_Pre + " Winddows下文件快捷方式 Begin "+ OneLine_End);
        System.out.println("Winddows下 Ubuntu子系统 路径:");
        System.out.println(" zzfile_3.bat "+ User_Home+ File.separator+"AppData\\Local\\Packages\\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\\LocalState\\rootfs        // Ubuntu子系统根目录 ");


        System.out.println("桌面壁纸相关");
        System.out.println(" zzfile_3.bat "+ User_Home+ File.separator+"AppData\\Roaming\\Microsoft\\Windows\\Themes        // 当前壁纸位置 ");
        System.out.println(" zzfile_3.bat "+"C:\\Windows\\Web\\Screen                                                      // 壁纸仓库   ");
        System.out.println(" zzfile_3.bat "+User_Home+File.separator+"AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets   // 锁屏壁纸1");
        System.out.println(" zzfile_3.bat "+User_Home+File.separator+"AppData\\Local\\Microsoft\\Windows\\Themes          // 锁屏壁纸2");
        System.out.println();
        System.out.println("网络配置Host");
        System.out.println(" zzfile_3.bat C:\\WINDOWS\\system32\\drivers\\etc\\hosts    // Hosts文件的做用是把网址域名与对应的IP地址建立一个关联 数据库 ");
        System.out.println(OneLine_Pre + " Winddows下文件快捷方式  End "+ OneLine_Pre);

    }
    static void CmderTip(){
        System.out.println(OneLine_Pre + " Winddows下Cmder设置 Begin "+ OneLine_End);
        System.out.println("添加CMDER.exe的Dir路径到 系统环境变量Path:   电脑(右击)》 属性 》高级系统设置(左面板) 》 环境变量 》 用户变量 》 Path( 添加cmder的dir )  ");
        System.out.println("Cmder.exe /REGISTER ALL       // 鼠标目录右击出现cmder选项   ");
        System.out.println("Cmder.exe /UNREGISTER ALL       // 鼠标目录右击删除cmder选项   ");
        System.out.println("Cmder 下载地址:"+"  https://pan.baidu.com/disk/home#/all?vmode=list&path=%2F%E7%A7%BB%E5%8A%A8%E7%A1%AC%E7%9B%98%2Fsoftware%2Fwin");
        System.out.println("设置环境变量  Setting -> StartUp -> Environment  输入如下的配置信息: ");
        System.out.println();
        System.out.println("set PATH=%ConEmuBaseDir%\\Scripts;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\win_zbin;%USERPROFILE%\\Desktop\\zbin\\python;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\lin_zbin;%USERPROFILE%\\Desktop\\zbin\\mac_zbin;%PATH%\n" +
                "\n" +
                "alias gits=git status\n" +
                "alias cdd=cd /D %USERPROFILE%\\Desktop\n" +
                "alias cdz=cd /D %USERPROFILE%\\Desktop\\zbin\n" +
                "\n" +
                "set PATH=D:\\software\\ffmpeg\\bin;%PATH%\n" +
                "set tessdata=C:\\Program Files\\Tesseract-OCR\\tessdata\n" +
                "set PATH=C:\\Program Files\\Tesseract-OCR;%PATH%\n" +
                "set PATH=%USERPROFILE%\\Desktop\\zbin\\win_soft\\Redis;%PATH%");
        System.out.println();
        System.out.println(OneLine_Pre + " Winddows下Cmder设置  End "+ OneLine_Pre);
    }

    static void DumpInfoTip(){
        System.out.println(OneLine_Pre + " Dump 相关信息查询 "+ OneLine_End);
        System.out.println("adb shell dumpsys media.camera                              【 查看camera的信息");
        System.out.println("adb shell dumpsys activity                                  【 查看ActvityManagerService 所有信息    ");
        System.out.println("adb shell dumpsys activity activities                       【 查看Activity组件信息                  ");
        System.out.println("adb shell dumpsys activity services                         【 查看Service组件信息                   ");
        System.out.println("adb shell dumpsys activity providers                        【 查看ContentProvider组件信息           ");
        System.out.println("adb shell dumpsys activity broadcasts                       【 查看BraodcastReceiver信息             ");
        System.out.println("adb shell dumpsys activity intents                          【 查看Intent信息                        ");
        System.out.println("adb shell dumpsys activity processes                        【 查看进程信息                          ");
        System.out.println("adb shell dumpsys activity provider com.android.settings    【 查看Settings相关的Provider信息        ");
        System.out.println("adb shell pm list features                                  【 查看安卓feature                       ");


    }
    static void BroadCastInfoTip(){
        System.out.println(OneLine_Pre + " 广播 BroadCast信息 查询 "+ OneLine_End);
        System.out.println(OneLine+"WIFI 状态变化广播 ");
        System.out.println("adb shell am broadcast -a com.Android.test --es<string> test_string \"this is test string\" —ei<int> test_int 100 —ez<boolean> test_boolean true\n" +
                "【 -a com.Android.test  包名.Action的形式 】\n" +
                "【 --es \"test_string\" \"this is test string\"    指定广播中携带字符串 字符串名称为 test_string 后面为值Value  】\n" +
                "【 --ei test_int 100    指定广播中携带int整形  int名称为 test_int 后面为值Value 为 100 】\n" +
                "【 --ez test_boolean true    指定广播中携带boolean变量   boolean名称为 test_boolean 后面为值Value 为 true 】\n" +
                "adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 0\n" +
                "adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 1 \n" +
                "adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 2 \n" +
                "adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 3 \n" +
                "adb shell am broadcast -a \"Android.net.wifi.WIFI_STATE_CHANGED\"  --ei \"wifi_state\" 4 \n" +
                "WifiManager.WIFI_STATE_DISABLED ==1\n" +
                "WifiManager.WIFI_STATE_DISABLING ==0\n" +
                "WifiManager.WIFI_STATE_ENABLED==3\n" +
                "WifiManager.WIFI_STATE_ENABLING==2\n" +
                "WifiManager.WIFI_STATE_UNKNOWN==4");




    }

    static void StartAppInfoTip(){
        System.out.println(OneLine_Pre + " 启动应用信息 am start 查询 "+ OneLine_End);
        System.out.println("adb shell am start -a android.settings.WIFI_SETTINGS     // 启动到WIFI设置界面");
    }
    static void PackageInfoTip(){

        System.out.println(OneLine_Pre + " 安装包 Package 查询 "+ OneLine_End);

        System.out.println("adb shell pm list packages                        【  查看所有安装包( 系统 + 三方  例如: package:com.miui.core )");
        System.out.println("adb shell pm list packages -s                     【 只输出系统自带包 ");
        System.out.println("adb shell pm list packages -3                     【 查看第三方应用 例如: package:com.sina.weibo " );
        System.out.println("adb shell pm list packages -f                     【 输出包和包相关联的文件 例: package:/data/app/com.taobao.trip-RQc5gu_cTANljJA8vQ3kyg==/base.apk=com.taobao.trip");

        System.out.println("adb shell pm list packages -i                     【 查看已安装应用信息和安装来源  例如: package:com.youdao.calculator  installer=null ");
        System.out.println("adb shell pm list packages -e  sina <包名字符串>  【  查询包含 sina 关键字的包 ");
        System.out.println("adb shell service list                            【  查询服务列表 ");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");

    }


    static void PrintLogTip(){

        System.out.println(OneLine_Pre + " 打印Log操作 "+ OneLine_End);
        System.out.println(" adb logcat | grep zukgit " );
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