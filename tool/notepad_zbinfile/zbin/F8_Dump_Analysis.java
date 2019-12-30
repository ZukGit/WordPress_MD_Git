import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class F8_Dump_Analysis {


    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    static String F8_Dump_DirPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8" + File.separator;

    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }

    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;
    static ArrayList<String> mKeyWordName = new ArrayList<>();

    static boolean isBugreportExist;  // 判断  当前目录下的 Bugreport 文件是否存在
    static String bugreportFileName = "bugreport.txt";
    static String bugreportFileABSPATH = "";
    static int CatagoryIndexNum = 1;

    // 如果 是去 读取 bugreport.txt , 那么这里存放 bugreport的所有内容
    static ArrayList<String> mBugReporList = null;
    static File mBugreport_ExistFile = null;  //

    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            curOS_TYPE = OS_TYPE.Windows;
        } else if (osName.contains("linux")) {
            curOS_TYPE = OS_TYPE.Linux;
        } else if (osName.contains("mac")) {
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }


    static String curDirPath = "";   // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径


    public static void main(String[] args) {
        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {
                    curDirPath = args[i];
                } else {
                    mKeyWordName.add(args[i]);
                }
            }
        }


        String bugrepot_ABSPATH = curDirPath + File.separator + bugreportFileName;
        bugrepot_ABSPATH = bugrepot_ABSPATH.replace(File.separator + File.separator, File.separator);
        File bugreportFile = new File(bugrepot_ABSPATH);
        bugreportFileABSPATH = bugreportFile.getAbsolutePath();
        isBugreportExist = bugreportFile.exists();

        if (isBugreportExist) {
            mBugreport_ExistFile = bugreportFile;
        } else {
            mBugreport_ExistFile = null;
        }


        initCategoryList(mBugreport_ExistFile);
        beginAnalysisItem();
        beginShowMatchInfo();
    }

    static void beginShowMatchInfo() {
        ArrayList<String> logList = new ArrayList<String>();

        for (int i = 0; i < categoryList.size(); i++) {
            CatagoryItem category = categoryList.get(i);
            ArrayList<AShowItem> showItemList = category.getmSameCategoryList();
            int catagorySize = category.getShowItemSize();
            int catagoryindex = category.mCatagoryIndex;
            System.out.println("═══════════════════" + "【" + category.mTitleName + "】【分类索引:" + catagoryindex + "】【显示长度:" + catagorySize + "】" + "═══════════════════");
            int itemIndex = 1;
            for (int j = 0; j < showItemList.size(); j++) {
                AShowItem showItem = showItemList.get(j);
                String showItemStr = showItem.showCurrentInfo();
                if (showItem.isAlwaysShow) {
                    System.out.println("【" + "[" + itemIndex + "]" + showItem.mUIPossition + "】");
                    System.out.println(showItemStr);
                }


                itemIndex++;
            }
        }

        int showCount = calculShowItemSize();
        System.out.println("\n\n当前记录 SHowItem 总数: 【" + showCount + "】");

    }


    static void beginAnalysisItem() {
        for (int i = 0; i < categoryList.size(); i++) {
            CatagoryItem category = categoryList.get(i);
            ArrayList<AShowItem> showItemList = category.getmSameCategoryList();
            for (int j = 0; j < showItemList.size(); j++) {
                AShowItem showItem = showItemList.get(j);
                showItem.ReadAndMatchSrcFile();
            }
        }
    }

    static void initCategoryList(File bugrepotFile) {
        if (bugrepotFile != null) {
            initFileWithReportFile(bugrepotFile);
        } else {
            initFileWithF8DumpDirPath(F8_Dump_DirPath);
        }
    }

    static void initFileWithF8DumpDirPath(String F8DumpPath) {
        for (int i = 0; i < categoryList.size(); i++) {
            CatagoryItem category = categoryList.get(i);
            ArrayList<AShowItem> showItemList = category.getmSameCategoryList();
            for (int j = 0; j < showItemList.size(); j++) {
                AShowItem showItem = showItemList.get(j);
                showItem.InitWithDumpPath(F8DumpPath);
            }
        }
    }


    static int calculShowItemSize() {
        int itemCount = 0;

        for (int i = 0; i < categoryList.size(); i++) {
            itemCount += categoryList.get(i).getShowItemSize();
        }

        return itemCount;

    }

    // 如果 bugreport.txt 文件 不为空  那么把 所有的 ShowItem 数据的来源 设置为这个文件
    static void initFileWithReportFile(File bugrepotFile) {
        for (int i = 0; i < categoryList.size(); i++) {
            CatagoryItem category = categoryList.get(i);
            ArrayList<AShowItem> showItemList = category.getmSameCategoryList();
            for (int j = 0; j < showItemList.size(); j++) {
                AShowItem showItem = showItemList.get(j);
                showItem.InitWithBugReportFile(bugrepotFile);
            }
        }
    }

    static ArrayList<CatagoryItem> categoryList = new ArrayList<CatagoryItem>();


    // WIFI 设置相关的 项
    static {
        CatagoryItem Wifi_Setting_Category = new CatagoryItem("WIFI-设置相关项");

        WIFI_Toogle_Setting_Open mWIFI_Toogle_Setting_Open = new WIFI_Toogle_Setting_Open();
        Wifi_Setting_Category.addAShowItem(mWIFI_Toogle_Setting_Open);

        Wifi_Score_Params m_Wifi_Score_Params = new Wifi_Score_Params();
        Wifi_Setting_Category.addAShowItem(m_Wifi_Score_Params);
        Hotspot_Config m_Hotspot_Config = new Hotspot_Config();
        Wifi_Setting_Category.addAShowItem(m_Hotspot_Config);

        Hotspot_Toogle_Setting_Open m_Hotspot_Toogle_Setting_Open = new Hotspot_Toogle_Setting_Open();
        Wifi_Setting_Category.addAShowItem(m_Hotspot_Toogle_Setting_Open);


        Wifi_WifiVerbose_Toogle_SettingItem m_Wifi_WifiVerbose_Toogle_SettingItem = new Wifi_WifiVerbose_Toogle_SettingItem();
        Wifi_Setting_Category.addAShowItem(m_Wifi_WifiVerbose_Toogle_SettingItem);

        Wifi_PasspointSim_Toogle_SettingItem m_Wifi_PasspointSim_Toogle_SettingItem = new Wifi_PasspointSim_Toogle_SettingItem();
        Wifi_Setting_Category.addAShowItem(m_Wifi_PasspointSim_Toogle_SettingItem);


        AirplaneMode_Toogle_SettingItem m_AirplaneMode_Toogle_SettingItem = new AirplaneMode_Toogle_SettingItem();
        Wifi_Setting_Category.addAShowItem(m_AirplaneMode_Toogle_SettingItem);
        categoryList.add(Wifi_Setting_Category);
    }


//  WIFI-设置相关项的实现类


    static class WIFI_Toogle_Setting_Open extends BasicShowItem {

        WIFI_Toogle_Setting_Open() {

            this.mBeginKeyWord = "Wi-Fi is enabled";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置WIFI开关";
            this.desc = "查看wifi开关设置属性: adb shell settings get global wifi_on \n" +
                    "设置wifi开关设置属性: adb shell settings put global wifi_on 0 (无效--未实时监听)\n" +
                    "打开wifi命令:  adb shell svc wifi enable\n" +
                    "关闭wifi命令:  adb shell svc wifi disable\n" +
                    "WIFI配置保存文件:  adb pull /data/misc/wifi/WifiConfigStore.xml . " +
                    "打开详情开关: \n" +
                    "Settings >System > About phone > tap \"Build number\" 4 times >Developer options\n" +
                    "Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open] ";

            this.showKey_Match = "WIFI开关: open打开状态  WifiEnabler.java onSwitchToggled(boolean isChecked)";
            this.showKey_NO_Match = "WIFI开关: close状态";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isJustMatch = true;  //  仅仅只是匹配
            this.isAlwaysShow = true;  // 总是显示
        }


    }


// softap.conf
    // 【  this.mUIPossition ="设置WIFI-Hotspot开关 WifiTetherSettings.java WifiTetherSwitchBarController.java  boolean onSwitchToggled(boolean isChecked)"; SoftApManager.java int startSoftAp(WifiConfiguration config) 】
//   mSwitchBar.setChecked(mWifiManager.getWifiApState() == WifiManager.WIFI_AP_STATE_ENABLED);
//  ./connectivity.txt:114:    2019-12-12T06:10:44.716 - SET master tether settings: ON    adb shell dumpsys connectivity > connectivity.txt
// ./bluetooth_manager.txt:243:  mTetherOn: false   adb shell dumpsys bluetooth_manager > bluetooth_manager.txt
    // ./connectivity.txt:67:    Type: TETHERING_WIFI, Value: empty

    /*  connectivity.txt     热点 打开 关闭的过程
        Line 111:     2019-12-12T06:10:44.716 - SET master tether settings: ON
        Line 140:     2019-12-12T06:10:46.412 - SET master tether settings: OFF
        Line 155:     2019-12-12T06:10:48.714 - SET master tether settings: ON
        Line 184:     2019-12-12T06:10:49.815 - SET master tether settings: OFF
        Line 199:     2019-12-12T06:10:50.726 - SET master tether settings: ON
        Line 227:     2019-12-12T06:12:41.845 - SET master tether settings: OFF
        Line 248:     2019-12-12T22:08:56.763 - SET master tether settings: ON
        Line 279:     2019-12-12T22:18:57.106 - SET master tether settings: OFF
        Line 294:     2019-12-12T22:19:10.612 - SET master tether settings: ON
        Line 323:     2019-12-12T22:19:31.539 - SET master tether settings: OFF
        Line 338:     2019-12-12T22:21:43.266 - SET master tether settings: ON
        Line 367:     2019-12-12T22:23:21.658 - SET master tether settings: OFF
        */
    static class Hotspot_Toogle_Setting_Open extends BasicShowItem {
        Hotspot_Toogle_Setting_Open() {
            this.mBeginKeyWord = "SET master tether settings:";
            this.mEndKeyWord = null;
            this.mUIPossition = " 【  this.mUIPossition =\"设置WIFI-Hotspot开关 WifiTetherSettings.java WifiTetherSwitchBarController.java  boolean onSwitchToggled(boolean isChecked)\"; SoftApManager.java int startSoftAp(WifiConfiguration config) 】";
            this.desc = "WIFI热点无设置Item  有文件保存该设置项的配置 文件位置:  adb pull /data/misc/wifi/softap.conf .    ";
            this.showKey_Match = "WIFI热点-打开状态";
            this.showKey_NO_Match = "WIFI热点-关闭记录 ";
            this.srcFileName = "connectivity.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = false;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            String lastStr = oriLogList.get(oriLogList.size() - 1);
            if (lastStr.contains("master tether settings: ON")) {
                this.isItemSearchSuccess = true;
            } else {
                this.isItemSearchSuccess = false;
            }
            return super.doFlutterList(oriLogList);
        }
    }


    static class Hotspot_Config extends BasicShowItem {
        Hotspot_Config() {
            this.mBeginKeyWord = "--Dump of SoftApManager--";
            this.mEndKeyWord = "";
            this.mUIPossition = " 【  this.mUIPossition =\"设置WIFI-Hotspot开关 WifiTetherSettings.java WifiTetherSwitchBarController.java  boolean onSwitchToggled(boolean isChecked)\"; SoftApManager.java int startSoftAp(WifiConfiguration config) 】";
            this.desc = "WIFI热点无设置Item  有文件保存该设置项的配置 文件位置:  adb pull /data/misc/wifi/softap.conf .    ";
            this.showKey_Match = "WIFI热点配置信息____匹配Ok__热点开启";
            this.showKey_NO_Match = "WIFI热点配置信息____匹配Failed__热点关闭";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = false;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            ArrayList<String> fixedLog = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);
                if (log.contains(".SSID")) {
                    log = log + "  【 当前热点名称 】";
                }
                if (log.contains("mMode: 1")) {
                    log = log + "  【 热点打开 】";
                }
                if (log.contains("mCountryCode:")) {
                    log = log + "  【 国家码 】";
                }
                // mApConfig.apBand: 1
                if (log.contains("mApConfig.apBand:")) {
                    log = log + "  【 热点信带选择 -1:Any随便   0:2G  1:5G   】";
                }

                if (log.contains("hiddenSSID")) {
                    log = log + "  【 是否隐藏SSID  】";
                }


                if (log.contains("mNumAssociatedStations")) {
                    log = log + "  【 连接客户端数量  】";
                }

                if (log.contains("mTimeoutEnabled")) {
                    log = log + "  【 true-打开没有STA超时关闭热点功能 false--关闭该功能  】";
                }

                if (log.contains("mReportedFrequency")) {
                    log = log + "  【 当前信道工作频段 】";
                }

                fixedLog.add(log);

                // hiddenSSID
            }

            return fixedLog;
        }
    }

    // adb shell settings get global wifi_verbose_logging_enabled
// adb shell settings put global wifi_verbose_logging_enabled   0
// adb shell settings put global wifi_verbose_logging_enabled   1
//  _id:163 name:wifi_verbose_logging_enabled pkg:android value:1 default:1 defaultSystemSet:true
    static class Wifi_WifiVerbose_Toogle_SettingItem extends BasicShowItem {
        Wifi_WifiVerbose_Toogle_SettingItem() {
            this.mBeginKeyWord = "wifi_verbose_logging_enabled pkg:android value:1";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置WIFI-Verbose开关  WifiVerboseLoggingPreferenceController.java boolean onPreferenceChange(Preference preference, Object newValue)";
            this.desc = "查看wifi详细Log设置属性: adb shell settings get global wifi_verbose_logging_enabled      【0-WIFI扫描定位关闭】【1-WIFI扫描定位开启】\n" +
                    "设置wifi详细Log属性: adb shell settings put global wifi_verbose_logging_enabled   0    【 关闭WIFI详情 】\n" +
                    "设置wifi详细Log属性: adb shell settings put global wifi_verbose_logging_enabled   1    【 开启WIFI详情 】\n" +
                    "打开详情开关: \n" +
                    "Settings >System > About phone > tap \"Build number\" 4 times >Developer options\n" +
                    "Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging  [toogle open] ";
            this.showKey_Match = "WIFI-Verbose 开关 Open状态";
            this.showKey_NO_Match = "WIFI-Verbose 开关 Close状态 ";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }

    // adb shell setprop persist.wpaconfig.sim_switch DEBUG    【显示 auto-connect to SIM based Passpoint 选项】
    // adb shell getprop persist.wpaconfig.sim_switch          【查看 auto-connect to SIM based Passpoint 是否是DEBUG 可见】

    // adb shell settings get global  hs20_mncmcc_retail_saved_state
    // adb shell settings put global  hs20_mncmcc_retail_saved_state 0
    // adb shell settings put global  hs20_mncmcc_retail_saved_state 1

    // dumpres | grep  config_wifi_hs20_sim_mnc_mcc_retail

    //║ 资源定义名称-:sim_switch_summary                                                                                                    ║
//║ 资源定义值---: 总个数:0                                                                                                             ║
//║ 资源描述----- WpaDynamicPrefProvider  boolean isVisible(String key) 【key = sim_switch 】 判断是否可见                                                                                                                    ║
//║ Find and auto-connect to SIM based Passpoint? networks(查找并自动连接到基于 SIM 的 Passpoint? 网络)                                 ║
//║ 【(显示Item1因子.)persist.wpaconfig.sim_switch==DEBUG  [ adb shell setprop persist.wpaconfig.sim_switch DEBUG ] 】                  ║
//║ 【(显示Item因子2) (R.bool.config_wifi_hs20_sim_mnc_mcc_retail || R.array.carrier_list_hide_passpoint_option 决定)】                 ║
//║ 【Item的值由 getMNCMCCPasspointConnectState()(来源 boolean WifiConfigManager.ishs20MNCMCCRetailEnabled)】                           ║
//║ 【Item值最终来源 int mSettingsStore.getHS20MNCMCCRetailState()】                                                                    ║
//║ 【Settings.Global.HS20_MNCMCC_RETAIL_SAVED_STATE "hs20_mncmcc_retail_saved_state"】                                                 ║
//║ -------------:-------------
    // name:hs20_mncmcc_retail_saved_state pkg:android value:0 default:0 defaultSystemSet:true
    static class Wifi_PasspointSim_Toogle_SettingItem extends BasicShowItem {
        Wifi_PasspointSim_Toogle_SettingItem() {
            this.mBeginKeyWord = "name:hs20_mncmcc_retail_saved_state ";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置Passpoint-Sim开关 【 WpaDynamicPrefProvider.java boolean isVisible(String key) 】【 WpaDynamicPrefProvider.java boolean isEnabled(String key) 】";
            this.desc = "\nPasspoint_Sim开关显示逻辑1:  adb shell getprop persist.wpaconfig.sim_switch   【显示为 DEBUG 那么 Toggle开关可见 】\n" +
                    "Passpoint_SIm开关显示逻辑2:  getResources().getBoolean(com.android.internal.R.bool.config_wifi_hs20_sim_mnc_mcc_retail)  【true--Toggle开关可见  false--Toggle开关不可见 】\n" +
                    "查看Passpoint_Sim 开关设置属性: adb shell settings get global  hs20_mncmcc_retail_saved_state   【 1--passpoint_sim打开  0----passpoint_sim关闭】\n" +
                    "设置 adb shell settings put global  hs20_mncmcc_retail_saved_state 0  (无法生效--与运行时的值保持同步) \n" +
                    "设置 adb shell settings put global  hs20_mncmcc_retail_saved_state 1  (无法生效--与运行时的值保持同步) \n" +
                    "DumpRes生成的资源列表Q和P版本可能不一致  adb shell dumpres > dumpres.txt   &  cat   dumpres.txt | grep config_wifi_hs20_sim_mnc_mcc_retail "
            ;
            this.showKey_Match = "设置Passpoint-Sim开关 具体查看搜索结果";
            this.showKey_NO_Match = "设置Passpoint-Sim开关  无法搜索到该值  [默认为 false 不打开]  ";
            this.srcFileName = "settings.txt"; // 资源文件来源
//        this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }

    //  飞行模式开关
    // _id:224 name:airplane_mode_on pkg:com.android.settings value:0 default:0 defaultSystemSet:true
    // _id:224 name:airplane_mode_on pkg:com.android.settings value:1 default:0 defaultSystemSet:true
    static class AirplaneMode_Toogle_SettingItem extends BasicShowItem {
        AirplaneMode_Toogle_SettingItem() {
            this.mBeginKeyWord = "name:airplane_mode_on";
            this.mEndKeyWord = null;
            this.mUIPossition = "飞行模式开关  ";
            this.desc = "adb shell settings get global airplane_mode_on       【0-关闭飞行模式】【1-开启飞行模式】\n" +
                    "设置飞行模式开关属性: adb shell settings put global airplane_mode_on   0    【 关闭飞行模式 】\n" +
                    "设置飞行模式开关属性: adb shell settings put global airplane_mode_on   1    【 开启飞行模式 】";
            this.showKey_Match = "飞行模式: open打开状态";
            this.showKey_NO_Match = "飞行模式: close状态";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = false;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            String lastStr = oriLogList.get(oriLogList.size() - 1);
            if (lastStr.contains("value:1")) {
                this.isItemSearchSuccess = true;
            } else {
                this.isItemSearchSuccess = false;
            }
            return super.doFlutterList(oriLogList);
        }
    }

    //  ScoringParams: settings put global wifi_score_params rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57,pps=0:1:100,horizon=15,nud=8,expid=0
    static class Wifi_Score_Params extends BasicShowItem {
        Wifi_Score_Params() {
            this.mBeginKeyWord = "global wifi_score_params";
            this.mEndKeyWord = null;
            this.mUIPossition = "2.4G 5G wifi信号程度好坏标准  ";
            this.desc = "adb shell settings get global wifi_score_params       【rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57】\n" +
                    "adb shell settings put global wifi_score_params  rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57,pps=0:1:100,horizon=15,nud=8,expid=0    【 设置信号标准 】\n";
            this.showKey_Match = "wifi信号程度好坏标准  ---匹配OK";
            this.showKey_NO_Match = "wifi信号程度好坏标准  ---匹配Failed";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }


    // Mobile_Netwrok 设置相关的 项
    static {
        CatagoryItem MobileNetWork_Setting_Category = new CatagoryItem("移动网络-设置相关项");
        MobileNetworkData_Toogle_SettingItem m_MobileNetworkData_Toogle_SettingItem = new MobileNetworkData_Toogle_SettingItem();
        MobileNetWork_Setting_Category.addAShowItem(m_MobileNetworkData_Toogle_SettingItem);

        categoryList.add(MobileNetWork_Setting_Category);
    }


    //  移动数据开关
//  _id:16 name:mobile_data pkg:android value:1 default:1 defaultSystemSet:true
    static class MobileNetworkData_Toogle_SettingItem extends BasicShowItem {
        MobileNetworkData_Toogle_SettingItem() {
            this.mBeginKeyWord = "name:mobile_data pkg:android value:1";
            this.mEndKeyWord = null;
            this.mUIPossition = "移动网络开关  【 BluetoothEnabler.java boolean onSwitchToggled(boolean isChecked) 】";
            this.desc = "adb shell settings get global mobile_data       【0-移动网络开关关闭】【1-移动网络开关开启】\n" +
                    "设置移动网络开关属性: adb shell settings put global mobile_data   0    【 关闭移动网络开关 】\n" +
                    "设置移动网络开关属性: adb shell settings put global mobile_data   1    【 开启移动网络开关 】";
            this.showKey_Match = "移动网络开关: open打开状态";
            this.showKey_NO_Match = "移动网络开关: close状态";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = false;  // 显示过滤的信息
        }

    }


    // Blue 设置相关的 项
    static {
        CatagoryItem Blue_Setting_Category = new CatagoryItem("BlueTooth-设置相关项");
        Bluetooth_Toogle_SettingItem m_Bluetooth_Toogle_SettingItem = new Bluetooth_Toogle_SettingItem();
        Blue_Setting_Category.addAShowItem(m_Bluetooth_Toogle_SettingItem);

        categoryList.add(Blue_Setting_Category);
    }


    //  BlueTooth开关
//  _id:16 name:bluetooth_on pkg:android value:1 default:1 defaultSystemSet:true
    static class Bluetooth_Toogle_SettingItem extends BasicShowItem {
        Bluetooth_Toogle_SettingItem() {
            this.mBeginKeyWord = "name:bluetooth_on pkg:android value:1";
            this.mEndKeyWord = null;
            this.mUIPossition = "Bluetooth蓝牙开关  【 BluetoothEnabler.java boolean onSwitchToggled(boolean isChecked) 】";
            this.desc = "adb shell settings get global bluetooth_on       【0-Bluetooth蓝牙开关关闭】【1-Bluetooth蓝牙开关开启】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global bluetooth_on   0    【 关闭Bluetooth蓝牙开关 】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global bluetooth_on   1    【 开启Bluetooth蓝牙开关 】";
            this.showKey_Match = "Bluetooth蓝牙开关: open打开状态";
            this.showKey_NO_Match = "Bluetooth蓝牙开关: close状态";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");  // defaultSystemSet
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }


    // GPS 设置相关的 项
    static {
        CatagoryItem GPS_Setting_Category = new CatagoryItem("GPS-设置相关项");

        GPS_Toogle_Setting_Open m_GPS_Toogle_Setting_Open = new GPS_Toogle_Setting_Open();
        GPS_Setting_Category.addAShowItem(m_GPS_Toogle_Setting_Open);

        GPS_WifiScan_Toogle_SettingItem m_GPS_WifiScan_Toogle_SettingItem = new GPS_WifiScan_Toogle_SettingItem();
        GPS_Setting_Category.addAShowItem(m_GPS_WifiScan_Toogle_SettingItem);

        GPS_BlueToothScan_Toogle_SettingItem m_GPS_BlueToothScan_Toogle_SettingItem = new GPS_BlueToothScan_Toogle_SettingItem();
        GPS_Setting_Category.addAShowItem(m_GPS_BlueToothScan_Toogle_SettingItem);
        categoryList.add(GPS_Setting_Category);
    }

    static class GPS_Toogle_Setting_Open extends BasicShowItem {
        GPS_Toogle_Setting_Open() {
            this.mBeginKeyWord = "Location mode: true";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置GPS开关  【 LocationEnabler.java void setLocationEnabled(boolean enabled) 】";
            this.desc = "查看GPS开关设置属性: adb shell settings get secure location_mode \n" +
                    "设置GPS开关属性: adb shell settings put secure location_mode 0 (关闭GPS开关)\n" +
                    "设置GPS开关属性: adb shell settings put secure location_mode 1 (打开GPS开关 仅使用设备(GPS)定位 LOCATION_MODE_SENSORS_ONLY)\n" +
                    "设置GPS开关属性: adb shell settings put secure location_mode 2 (关闭GPS开关 低功耗定位模式，仅使用网络定位(WiFi和基站定位) LOCATION_MODE_BATTERY_SAVING )\n" +
                    "设置GPS开关属性: adb shell settings put secure location_mode 3 (关闭GPS开关 【默认】高精度定位模式，GPS与网络综合定位 LOCATION_MODE_HIGH_ACCURACY )\n";
            this.showKey_Match = "GPS开关: open打开状态";
            this.showKey_NO_Match = "GPS开关: close状态";
            this.srcFileName = "location.txt"; // 资源文件来源
            this.isJustMatch = true;  //  仅仅只是匹配
            this.isAlwaysShow = true;  // 总是显示
        }
    }

    //  基于WIFI的定位Location功能
// _id:157 name:wifi_scan_always_enabled pkg:root value:0 default:1 defaultSystemSet:true
    static class GPS_WifiScan_Toogle_SettingItem extends BasicShowItem {
        GPS_WifiScan_Toogle_SettingItem() {
            this.mBeginKeyWord = "name:wifi_scan_always_enabled pkg:root value:1";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置GPS_WIFI扫描开关";
            this.desc = "adb shell settings get global wifi_scan_always_enabled       【0-WIFI扫描定位关闭】【1-WIFI扫描定位开启】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global wifi_scan_always_enabled   0    【 关闭WIFI扫描定位 】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global wifi_scan_always_enabled   1    【 开启WIFI扫描定位 】";
            this.showKey_Match = "GPS_WIFI扫描开关: open打开状态";
            this.showKey_NO_Match = "GPS_WIFI扫描开关: close状态";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }


    //  基于Blue的定位Location功能
    // _id:156 name:ble_scan_always_enabled pkg:root value:0 default:1 defaultSystemSet:true
    static class GPS_BlueToothScan_Toogle_SettingItem extends BasicShowItem {
        GPS_BlueToothScan_Toogle_SettingItem() {
            this.mBeginKeyWord = " name:ble_scan_always_enabled pkg:root value:1";
            this.mEndKeyWord = null;
            this.mUIPossition = "设置GPS_Bluetooth扫描开关";
            this.desc = "蓝牙BLE扫描定位:  adb shell settings get ble_scan_always_enabled   【0-蓝牙扫描定位关闭】【1-蓝牙扫描定位开启】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global ble_scan_always_enabled   0    【 关闭蓝牙扫描定位 】\n" +
                    "设置GPS_WIFI扫描开关属性: adb shell settings put global ble_scan_always_enabled   1    【 开启蓝牙扫描定位 】";
            this.showKey_Match = "GPS_BLE扫描开关: open打开状态";
            this.showKey_NO_Match = "GPS_BLE扫描开关: close状态";
            this.srcFileName = "settings.txt"; // 资源文件来源
            this.keyWordList_Must.add("defaultSystemSet:");
            this.isAlwaysShow = true;  // 总是显示
            this.isJustMatch = true;  // 匹配一行
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

    }


    // System相关 设置相关的 项
    static {
        CatagoryItem System_Meta_Setting_Category = new CatagoryItem("系统信息(版本-时间)相关项");
        FW_Version m_FW_Version = new FW_Version();
        System_Meta_Setting_Category.addAShowItem(m_FW_Version);

        Acount_UID m_Acount_UID = new Acount_UID();
        System_Meta_Setting_Category.addAShowItem(m_Acount_UID);
        //   当前系统安装 APP的  UID 编号

        Shortcut_UID m_Shortcut_UID = new Shortcut_UID();
        System_Meta_Setting_Category.addAShowItem(m_Shortcut_UID);

        Activity_UID m_Activity_UID = new Activity_UID();
        System_Meta_Setting_Category.addAShowItem(m_Activity_UID);


        Memory_UID_PID m_Memory_UID_PID = new Memory_UID_PID();
        System_Meta_Setting_Category.addAShowItem(m_Memory_UID_PID);

        categoryList.add(System_Meta_Setting_Category);
    }


    static ArrayList<String> mPackageNameList = new ArrayList<String>();
    static ArrayList<String> mPackageUIDList = new ArrayList<String>();
    static Map<String, String> mUID_PackageName_Map = new HashMap<String, String>();

    static String get15UIDpadding(String uid) {

        String result = "【 UID:" + uid + "】";
        int length = result.length();
        int distance = 13 - length;
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }
        if ("".equals(uid)) {
            result = "               ";
        } else {
            result = "【 UID:" + uid + padding + "】";
        }

        return result;
    }


    static String get3size(String uid) {
        int length = uid.length();

        if (length > 3) {
            return uid;
        }
        int distance = 3 - uid.length();
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }

        return uid + padding;
    }

    static String get4size(String uid) {
        int length = uid.length();

        if (length > 4) {
            return uid;
        }
        int distance = 4 - uid.length();
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }

        return uid + padding;
    }

    static String get5size(String uid) {
        int length = uid.length();

        if (length > 5) {
            return uid;
        }
        int distance = 5 - uid.length();
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }

        return uid + padding;
    }


    static String get7size(String uid) {
        int length = uid.length();

        if (length > 7) {
            return uid;
        }
        int distance = 7 - uid.length();
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }


        return uid + padding;
    }


    static String get6size(String uid) {
        int length = uid.length();

        if (length > 6) {
            return uid;
        }
        int distance = 6 - uid.length();
        String padding = "";
        for (int i = 0; i < distance; i++) {
            padding = " " + padding;
        }


        return uid + padding;
    }


    @SuppressWarnings("unchecked")
    static String checkPackageName2Uid(String logItem) {
        String uid = "";
        boolean matchFlag = false;   //    是否包含 包名
        String matchPackageName = null;
        for (int i = 0; i < mPackageNameList.size(); i++) {
            String curPackageName = mPackageNameList.get(i);
//         System.out.println("logItem = "+ logItem);
//         System.out.println("curPackageName = "+ curPackageName);

            if (logItem.contains(curPackageName)) {


                matchPackageName = curPackageName;
                matchFlag = true;
                break;

            }
        }


        if (!matchFlag) {
            return uid;
        }


        Map.Entry<String, String> entry;


        if (mUID_PackageName_Map != null) {
            Iterator iterator = mUID_PackageName_Map.entrySet().iterator();


            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String uid_match = entry.getKey();  //Map的Value
                String packageList = entry.getValue();  //Map的Value
                if (packageList.contains(matchPackageName)) {
//              System.out.println("logItem2 = "+ logItem);
//              System.out.println("curPackageName2 = "+ matchPackageName);
//              System.out.println("uid_match2 = "+ uid_match);
//              System.out.println("packageList2 = "+ packageList);

                    uid = uid_match;
                    return uid_match;
                }

            }
        }


        return uid;
    }

    static class Acount_UID extends BasicShowItem {
        Acount_UID() {
            this.mBeginKeyWord = "ServiceInfo:";
            this.mEndKeyWord = null;

            this.mUIPossition = "当前系统APP程序 UID";
            this.desc = " 当前系统APP程序 UID ";
            this.showKey_Match = "当前系统APP程序 UID---匹配OK  ";
            this.showKey_NO_Match = "当前系统APP程序 UID---匹配Failed  ";
            this.srcFileName = "account.txt"; // 资源文件来源
            this.isAlwaysShow = false;  // 总是显示
            this.isJustMatch = true;
            this.isShowFlutterList = false;  // 显示过滤的信息
        }


        // ServiceInfo: AuthenticatorDescription {type=com.google.android.gm.pop3},
        // //ComponentInfo{com.google.android.gm/com.android.email.service.Pop3AuthenticatorService}, uid 10172

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            for (int i = 0; i < oriLogList.size(); i++) {


                String logItem = oriLogList.get(i);

                if (logItem.contains("ComponentInfo{") && logItem.contains(" uid ")) {

                    String sub1 = logItem.substring(logItem.indexOf("ComponentInfo{") + "ComponentInfo{".length());
                    // com.google.android.gm/com.android.email.service.Pop3AuthenticatorService}, uid 10172
                    String packageName = sub1.substring(0, sub1.indexOf("/")).trim();  // com.google.android.gm
                    String uid = logItem.substring(logItem.indexOf("uid") + "uid".length()).trim();

                    if (packageName != null && !"".equals(packageName) && uid != null && !"".equals(uid)) {

//                        System.out.println("packageName = "+ packageName);
//                        System.out.println("uid = "+ uid);
                        if (!mPackageUIDList.contains(uid) && !mPackageNameList.contains(packageName)) {
                            mUID_PackageName_Map.put(uid, packageName);
                            mPackageNameList.add(packageName);
                            mPackageUIDList.add(uid);
                        }


                    }

                }
            }
            isItemSearchSuccess = false;
            isAlwaysShow = false;
            return super.doFlutterList(oriLogList);
        }
    }


    static class Shortcut_UID extends BasicShowItem {
        Shortcut_UID() {
            this.mBeginKeyWord = "Package:";
            this.mEndKeyWord = null;

            this.mUIPossition = "当前系统APP程序 UID";
            this.desc = " 当前系统APP程序 UID ";
            this.showKey_Match = "当前系统APP程序 UID---匹配OK  ";
            this.showKey_NO_Match = "当前系统APP程序 UID---匹配Failed  ";
            this.srcFileName = "shortcut.txt"; // 资源文件来源
            this.isAlwaysShow = false;  // 总是显示
            this.isJustMatch = true;
            this.isShowFlutterList = false;  // 显示过滤的信息
        }


        //       Package: com.google.android.apps.maps  UID: 10202
        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            for (int i = 0; i < oriLogList.size(); i++) {


                String logItem = oriLogList.get(i);

                if (logItem.contains("UID:")) {


                    String packageName = logItem.substring(0, logItem.indexOf("UID:")).trim();
                    packageName = packageName.replace("Package:", "").trim();

                    String uid = logItem.substring(logItem.indexOf("UID:") + "UID:".length()).trim();

                    if (packageName != null && !"".equals(packageName) && uid != null && !"".equals(uid)) {

//                        System.out.println("packageName1 = "+ packageName);
//                        System.out.println("uid1 = "+ uid);
                        if (!mPackageUIDList.contains(uid) && !mPackageNameList.contains(packageName)) {
                            mUID_PackageName_Map.put(uid, packageName);
                            mPackageNameList.add(packageName);
                            mPackageUIDList.add(uid);
                        }


                    }

                }
            }
            isItemSearchSuccess = false;
            isAlwaysShow = false;

            return super.doFlutterList(oriLogList);
        }

    }


    // ./activity.txt:1768:    UID 10081, binder count = 6, package(s)= com.motorola.dolby.dolbyui;
    static class Activity_UID extends BasicShowItem {
        Activity_UID() {
            this.mBeginKeyWord = "binder count =";
            this.mEndKeyWord = null;

            this.mUIPossition = "当前系统APP程序 UID";
            this.desc = " 当前系统APP程序 UID ";
            this.showKey_Match = "当前系统APP程序 UID---匹配OK  ";
            this.showKey_NO_Match = "当前系统APP程序 UID---匹配Failed  ";
            this.srcFileName = "activity.txt"; // 资源文件来源
            this.isAlwaysShow = false;  // 总是显示
            this.isJustMatch = true;
            this.isShowFlutterList = false;  // 显示过滤的信息
        }


        //   UID 1002, binder count = 43, package(s)= com.android.bluetooth;
        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (logItem.contains("UID") && logItem.contains("package") && logItem.contains("binder count")) {


                    String uid = logItem.substring(0, logItem.indexOf(" binder count")).trim();

                    uid = uid.replace("UID", "").trim();
                    uid = uid.replace(",", "").trim();


                    // UID 10060, binder count = 42, package(s)= com.motorola.ccc.notification; com.motorola.ccc.devicemanagement;
                    String packageNameList = logItem.substring(logItem.indexOf("package(s)=") + "package(s)=".length()).trim();

                    String[] packageNameArr = packageNameList.split(";");
                    if (packageNameArr == null) {
                        continue;
                    }

                    for (int j = 0; j < packageNameArr.length; j++) {
                        String packageName = packageNameArr[j].trim();

                        if (packageName != null && !"".equals(packageName) && uid != null && !"".equals(uid)) {

//                            System.out.println("packageName3 = "+ packageName);
//                            System.out.println("uid3 = "+ uid);

                            if (!mPackageUIDList.contains(uid)) {  //  如果不包含UID  直接加入
                                mUID_PackageName_Map.put(uid, packageName);
                                mPackageNameList.add(packageName);
                                mPackageUIDList.add(uid);

                            } else if (mPackageUIDList.contains(uid) && mPackageNameList.contains(packageName)) {
                                //  如果包含当前uid  但是不包含当前的 packagename  那么需要 更新 MAP
                                mPackageNameList.add(packageName);
                                String curPakages = mUID_PackageName_Map.get(uid).trim();
                                if (!curPakages.equals(packageName)) {
                                    mUID_PackageName_Map.put(uid, curPakages + ";" + packageName);
                                }

                            }
                        }
                    }
                }
            }
            isItemSearchSuccess = false;
            isAlwaysShow = false;
//            System.out.println("mPackageNameList.size = "+ mPackageNameList.size() );
//
//            System.out.println("mPackageNameList = "+ mPackageNameList );
//            System.out.println("mPackageUIDList.size = "+ mPackageUIDList.size() );
//            System.out.println("mPackageUIDList = "+ mPackageUIDList );

            return super.doFlutterList(oriLogList);
        }

    }


    static class Memory_UID_PID extends BasicShowItem {
        Memory_UID_PID() {
            this.mBeginKeyWord = "Total PSS by process:";
            this.mEndKeyWord = "";

            this.mUIPossition = "当前系统APP程序 内存情况 包名 PID UID";
            this.desc = " 当前系统APP程序 内存情况 包名 PID UID ";
            this.showKey_Match = "当前系统APP程序 内存情况 包名 PID UID---匹配OK  ";
            this.showKey_NO_Match = "当前系统APP程序 内存情况 包名 PID UID---匹配Failed  ";
            this.srcFileName = "meminfo.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> fixedList = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (!logItem.contains("pid")) {
                    fixedList.add(logItem);
                    continue;
                }

                String uid = checkPackageName2Uid(logItem);
                logItem = get15UIDpadding(("".equals(uid) ? "" : uid + "")) + logItem;
                //    System.out.println("real_logItem"+logItem);
                fixedList.add(logItem);

            }


            fixedList.add("使用相同UID的 APP列表详情  Package包个数:" + mPackageNameList.size() + "  UID统计个数:" + mPackageUIDList.size());
            mPackageUIDList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Integer i1 = Integer.parseInt(o1);
                    Integer i2 = Integer.parseInt(o2);
                    if (i1 == i2) {
                        return 0;
                    } else if (i1 < i2) {
                        return -1;
                    } else {
                        return 1;
                    }

                }
            });

            for (int i = 0; i < mPackageUIDList.size(); i++) {
                String curuid = mPackageUIDList.get(i);
                String packageList = mUID_PackageName_Map.get(curuid);
                fixedList.add("UID=" + get6size(curuid) + "   相同UID包列表= " + packageList);
            }

            return fixedList;
        }
    }


    static class FW_Version extends BasicShowItem {
        FW_Version() {
            this.mBeginKeyWord = "Chipset information :";
            this.mEndKeyWord = "system time =";

            this.mUIPossition = "当前手机系统时间-Fw固件版本";
            this.desc = " 当前手机系统时间-Fw固件版本 ";
            this.showKey_Match = "当前手机系统时间-Fw固件版本---匹配OK  ";
            this.showKey_NO_Match = "当前手机系统时间-Fw固件版本---匹配Failed  ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }
    }


    // WIFI 设置相关的 项
    static {
        CatagoryItem WIFI_Log_Category = new CatagoryItem("WIFI相关Log");

        WifiInfo_SettingItem m_WifiInfo_SettingItem = new WifiInfo_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiInfo_SettingItem);


        ConnectionEvents m_ConnectionEvents = new ConnectionEvents();
        WIFI_Log_Category.addAShowItem(m_ConnectionEvents);

        StaEventList m_StaEventList = new StaEventList();
        WIFI_Log_Category.addAShowItem(m_StaEventList);

        WifiScoreReport m_WifiScoreReport = new WifiScoreReport();
        WIFI_Log_Category.addAShowItem(m_WifiScoreReport);

        WifiConfigManager_WifiConfiguration_SettingItem m_WifiConfigManager_WifiConfiguration_SettingItem = new WifiConfigManager_WifiConfiguration_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiConfigManager_WifiConfiguration_SettingItem);

        WifiConfigManager_Dump_SettingItem m_WifiConfigManager_Dump_SettingItem = new WifiConfigManager_Dump_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiConfigManager_Dump_SettingItem);
        WifiController_StateMechine_SettingItem m_WifiController_StateMechine_SettingItem = new WifiController_StateMechine_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiController_StateMechine_SettingItem);


        WifiClientModeImpl_StateMechine_SettingItem m_WifiClientModeImpl_StateMechine_SettingItem = new WifiClientModeImpl_StateMechine_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiClientModeImpl_StateMechine_SettingItem);

        SupplicantStateTracker_StateMechine_SettingItem m_SupplicantStateTracker_StateMechine_SettingItem = new SupplicantStateTracker_StateMechine_SettingItem();
        WIFI_Log_Category.addAShowItem(m_SupplicantStateTracker_StateMechine_SettingItem);


        WifiConnectivityManager_SettingItem m_WifiConnectivityManager_SettingItem = new WifiConnectivityManager_SettingItem();
        WIFI_Log_Category.addAShowItem(m_WifiConnectivityManager_SettingItem);
        categoryList.add(WIFI_Log_Category);
    }


    // mWifiInfo SSID: xxx-guest, BSSID: 18:64:72:21:86:54, MAC: c6:f3:c7:ae:a9:86, Supplicant state: COMPLETED, RSSI: -45, Link speed: 200Mbps, Tx Link speed: 200Mbps, Rx Link speed: 200Mbps, Frequency: 5785MHz, Net ID: 1, Metered hint: false, score: 60
    //mWifiInfo SSID: D-Link_DIR-816%, BSSID: 1c:5f:2b:5e:d5:53, MAC: de:2f:79:0f:f6:32, Supplicant state: COMPLETED, RSSI: -35, Link speed: 72Mbps, Tx Link speed: 72Mbps, Rx Link speed: 65Mbps, Frequency: 2412MHz, Net ID: 0, Metered hint: false, score: 60
    // 打开wifi未连接  mWifiInfo SSID: <unknown ssid>, BSSID: <none>, MAC: 02:00:00:00:00:00, Supplicant state: DISCONNECTED, RSSI: -127, Link speed: -1Mbps, Tx Link speed: -1Mbps, Rx Link speed: -1Mbps, Frequency: -1MHz, Net ID: -1, Metered hint: false, score: 0
    // 关闭            mWifiInfo SSID: <unknown ssid>, BSSID: <none>, MAC: 02:00:00:00:00:00, Supplicant state: DISCONNECTED, RSSI: -127, Link speed: -1Mbps, Tx Link speed: -1Mbps, Rx Link speed: -1Mbps, Frequency: -1MHz, Net ID: -1, Metered hint: false, score: 0


//    mLinkProperties {InterfaceName: wlan0 LinkAddresses: [ fe80::dc2f:79ff:fe0f:f632/64,192.168.0.26/24 ] DnsAddresses: [ /192.168.0.1 ] Domains: null MTU: 0 TcpBufferSizes: 524288,2097152,4194304,262144,524288,1048576 Routes: [ fe80::/64 -> :: wlan0,192.168.0.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.0.1 wlan0 ]}
//    mWifiInfo SSID: D-Link_DIR-816%, BSSID: 1c:5f:2b:5e:d5:53, MAC: de:2f:79:0f:f6:32, Supplicant state: COMPLETED, RSSI: -22, Link speed: 72Mbps, Tx Link speed: 72Mbps, Rx Link speed: 65Mbps, Frequency: 2412MHz, Net ID: 3, Metered hint: false, score: 60
//    mDhcpResults android.net.DhcpResults@e527f80 DHCP server /192.168.0.1 Vendor info null lease 86400 seconds Servername
//    mNetworkInfo [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]
//    mLastSignalLevel 4
//    mLastBssid 1c:5f:2b:5e:d5:53
//    mLastNetworkId 3
//    mOperationalMode 1
//    mUserWantsSuspendOpt true
//    mSuspendOptNeedsDisabled 4
//    mRevertCountryCodeOnCellularLoss: false
//    mDefaultCountryCode: cn
//    mDriverCountryCode: CN
//    mTelephonyCountryCode: CN
//    mTelephonyCountryTimestamp: 12-13 00:50:44.008
//    mDriverCountryTimestamp: 12-13 02:22:58.590
//    mReadyTimestamp: 12-13 02:22:58.727

    static class WifiInfo_SettingItem extends BasicShowItem {
        WifiInfo_SettingItem() {
            this.mBeginKeyWord = "mLinkProperties ";
            this.mEndKeyWord = "";

            this.mUIPossition = "当前连接的 WifiInfo 信息";
            this.desc = " 只有连接上WIFI( 包括portal )  WifiInfo 才有意义有值 ";
            this.showKey_Match = "WifiInfo 信息----当前已连接上WIFI ";
            this.showKey_NO_Match = "WifiInfo 信息 ---【null】 当前未打开Wifi开关 或 打开开关未能连接  ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
            this.isStart_BeginKey = true;
            this.isStart_EndKey = true;
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);

                if (log.contains("mWifiInfo")) {

                    if (log.contains("COMPLETED")) {
                        this.isItemSearchSuccess = true;
                        String name = log.substring(0, log.indexOf(", BSSID"));
                        name = name.replace("mWifiInfo", "").trim();
                        showKey_Match = showKey_Match + "【" + name + "】";
                    } else {
                        this.isItemSearchSuccess = false;
                    }
                }


            }

            return super.doFlutterList(oriLogList);
        }
    }


    // WifiConfigManager - Log Begin ----
    // WifiConfigManager - Log End ----


    // setNetworkSelectionStatus: configKey    更新该网络的状态
    //2019-12-12T05:19:01.318 - setNetworkSelectionStatus: configKey="D-Link_DIR-816%"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=12-12 05:19:01.318


    // added/updated config   添加或者更新 wificonfiguration
    // 2019-12-13T14:23:14.273 - addOrUpdateNetworkInternal: added/updated config. netId=0 configKey="D-Link_DIR-816%"NONE uid=1000 name=android.uid.system:1000

    //  removeNetworkInternal  移除网络配置WifiConfiguration
    // 2019-12-13T14:59:56.030 - removeNetworkInternal: removed config. netId=0 configKey="D-Link_DIR-816%"NONE uid=1000 name=android.uid.system:1000
    static class WifiConfigManager_Dump_SettingItem extends BasicShowItem {
        WifiConfigManager_Dump_SettingItem() {
            this.mBeginKeyWord = "WifiConfigManager - Log Begin";
            this.mEndKeyWord = "WifiConfigManager - Log End";
            this.mUIPossition = "WifiConfigManager WIFI配置情况日志记录";
            this.desc = " WifiConfigManager 设置网络状态  \n" +
                    "NetworkState disableReason 不可用原因列表\n" +
                    "int NETWORK_SELECTION_ENABLE = 0; // 标识当前网络可以被主动连接\n" +
                    "int NETWORK_SELECTION_DISABLED_BAD_LINK = 1;  //  This network is disabled because higher layer (>2) network is bad\n" +
                    "int NETWORK_SELECTION_DISABLED_ASSOCIATION_REJECTION = 2;  //多次拒绝关联  This network is disabled because multiple association rejects\n" +
                    "int NETWORK_SELECTION_DISABLED_AUTHENTICATION_FAILURE = 3;  // 多次认证失败\n" +
                    "int NETWORK_SELECTION_DISABLED_DHCP_FAILURE = 4;   //  多次 DHCP 获取失败 This network is disabled because multiple DHCP failure\n" +
                    "int NETWORK_SELECTION_DISABLED_DNS_FAILURE = 5;  //  由于网络安全原因禁止  This network is disabled because of security network but no credentials \n" +
                    "int NETWORK_SELECTION_DISABLED_NO_INTERNET_TEMPORARY = 6;  //由于当前网络当前没有网络 不能上网 所以被禁止  This network is temporarily disabled because it has no Internet access.\n" +
                    "int NETWORK_SELECTION_DISABLED_WPS_START = 7;   // 由于WPS联网开始  所以其他网络暂时Disable   This network is disabled because we started WPS\n" +
                    "int NETWORK_SELECTION_DISABLED_TLS_VERSION = 8;  // EAP-TLS failure  认证失败 所以被禁止  This network is disabled because EAP-TLS failure \n" +
                    "int NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_CREDENTIALS = 9;  //  由于缺少用户凭证所以当前网络被禁止  This network is disabled due to absence of user credentials\n" +
                    "int NETWORK_SELECTION_DISABLED_NO_INTERNET_PERMANENT = 10; //  此网络由于没有网络 用户不想再呆在这个网络 主动连接其他网络  This network is permanently disabled because it has no Internet access and user does not want to stay connected. \n" +
                    "int NETWORK_SELECTION_DISABLED_BY_WIFI_MANAGER = 11;  // 被 wifimanager禁用了该网络  This network is disabled due to WifiManager disable it explicitly\n" +
                    "int NETWORK_SELECTION_DISABLED_BY_USER_SWITCH = 12;   //由于用户进行了切换网络 此网络被暂时禁用  This network is disabled due to user switching\n" +
                    "int NETWORK_SELECTION_DISABLED_BY_WRONG_PASSWORD = 13;  // 由于错误的密码 此网络被禁用  This network is disabled due to wrong password\n" +
                    "int NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_SUBSCRIPTION = 14; // 由于没有订阅服务 此网络被禁用  This network is disabled because service is not subscribed\n";
            this.showKey_Match = "WifiConfigManager 网络状态的日志记录---搜索成功  ";
            this.showKey_NO_Match = "WifiConfigManager 网络状态的日志记录----搜索失败  ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> arrLog = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (logItem.contains("removeNetworkInternal")) {
                    logItem = logItem.replace("removeNetworkInternal", "removeNetworkInternal 【移除网络】");
                    logItem = logItem.replace("netId", "【netId】");
                }
                if (logItem.contains("addOrUpdateNetworkInternal")) {
                    logItem = logItem.replace("addOrUpdateNetworkInternal", "addOrUpdateNetworkInternal 【新增或更新网络】");
                    logItem = logItem.replace("netId", "【netId】");
                }
                //

                if (logItem.contains("networkStatus") && logItem.contains("disableReason")) {
                    int index1 = logItem.indexOf("networkStatus");
                    int index2 = logItem.indexOf("disableReason");
                    String statusStr = logItem.substring(index1, index2);
                    logItem = logItem.replace(statusStr, "");

                }
                // networkStatus=NETWORK_SELECTION_ENABLED disableReason=

                arrLog.add(logItem);
            }
            return arrLog;
        }
    }


// WifiController:
//    WifiController:
//    total records=52
//    rec[0]: time=12-12 18:18:52.450 processed=StaDisabledState org=StaDisabledState dest=<null> what=155656(0x26008)
//    rec[1]: time=12-12 18:18:52.957 processed=StaDisabledState org=StaDisabledState dest=<null> what=155659(0x2600b)
//    rec[16]: time=12-12 19:00:48.931 processed=StaEnabledState org=StaEnabledState dest=StaDisabledState what=155656(0x26008)


    static class WifiController_StateMechine_SettingItem extends BasicShowItem {
        WifiController_StateMechine_SettingItem() {
            this.mBeginKeyWord = "WifiController:";
            this.mEndKeyWord = "curState=";
            this.mUIPossition = "WifiController 状态机处理事件 Event记录 ";
            this.desc = "状态机:\n" +
                    "WifiController extends StateMachine\n" +
                    "  _-----=> StaEnabledState\n" +
                    " / _----=> StaDisabledState\n" +
                    "| / _---=> StaDisabledWithScanState\n" +
                    "|| / _--=> EcmState\n" +
                    "||| /     \n" +
                    "||||    \n" +
                    "||||      \n" +
                    "DefaultState";

            this.showKey_Match = "WifiController extends StateMachine 状态机事件处理模型 ";
            this.showKey_NO_Match = "WifiController extends StateMachine 状态机事件处理模型 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = false;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logList = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (!logItem.contains("dest=<null>")) {

                    // org=StaDisabledState dest=StaEnabledState
                    if (logItem.contains("org=StaDisabledState dest=StaDisabledState")) {
                        logItem = logItem + " 【关闭状态保持】状态保持不变";
                    } else if (logItem.contains("dest=StaDisabledState")) {
                        logItem = " 【关闭-Wifi】状态变化 " + logItem;
                    }

                    if (logItem.contains("dest=StaEnabledState")) {
                        logItem = " 【可用-Wifi】状态变化 " + logItem;
                    }

                    // dest=StaDisabledState
                    logList.add(logItem);
                }

            }
            return logList;
        }
    }


//    SupplicantStateTracker:
//    total records=75
//    rec[0]: time=12-13 14:44:54.996 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)


    static class SupplicantStateTracker_StateMechine_SettingItem extends BasicShowItem {
        SupplicantStateTracker_StateMechine_SettingItem() {
            this.mBeginKeyWord = "SupplicantStateTracker:";
            this.mEndKeyWord = "curState=";
            this.mUIPossition = "SupplicantStateTracker 状态机处理事件 Event记录 ";
            this.desc = "状态机:\n" +
                    "                 _-----=>   DormantState\n" +
                    "                / _----=>  CompletedState\n" +
                    "                | / _---=> HandshakeState \n" +
                    "                || / _--=> ScanState \n" +
                    "                ||| /\n" +
                    "                ||||\n" +
                    "                ||||\n" +
                    "  _-----=> ConnectionActiveState\n" +
                    " / _----=> DisconnectedState \n" +
                    "| / _---=> InactiveState\n" +
                    "|| / _--=> UninitializedState\n" +
                    "||| /\n" +
                    "||||\n" +
                    "||||\n" +
                    "DefaultState";

            this.showKey_Match = "SupplicantStateTracker extends StateMachine 状态机事件处理模型 ";
            this.showKey_NO_Match = "SupplicantStateTracker extends StateMachine 状态机事件处理模型 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = false;  // 总是显示
            this.isStart_BeginKey = true;  //  需要以 关键词 为 起始
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logList = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (logItem.contains("dest=CompletedState")) {
                    logItem = logItem.replace("dest", "【进入连接状态】 dest");
                }

                if (logItem.contains("org=CompletedState") && !logItem.contains("dest=CompletedState")) {
                    logItem = logItem.replace("dest", "【离开连接状态】 dest");
                }

                if (logItem.contains("dest=HandshakeState")) {
                    logItem = logItem.replace("dest", "【尝试连接-握手状态】 dest");
                }
                //


                if (logItem.contains("org=HandshakeState") && !logItem.contains("dest=CompletedState")) {
                    logItem = logItem.replace("dest", "【尝试连接失败-返回 Disconn转态】 dest");
                }

                //   org=HandshakeState dest=DisconnectedState


                // org=CompletedState dest=
                logList.add(logItem);
            }
            return logList;
        }
    }


    static class WifiClientModeImpl_StateMechine_SettingItem extends BasicShowItem {
        WifiClientModeImpl_StateMechine_SettingItem() {
            this.mBeginKeyWord = "WifiClientModeImpl:";
            this.mEndKeyWord = "curState=";
            this.mUIPossition = "WifiClientModeImpl 状态机处理事件 Event记录 ";
            this.desc = "状态机:\n" +
                    "ClientModeImpl extends StateMachine {}\n" +
                    "                  _----=> RoamingState\n" +
                    "                 / _---=> ConnectedState\n" +
                    "                | / _--=> ObtainingIpState\n" +
                    "                || /   \n" +
                    "           L2ConnectedState\n" +
                    "                ||    \n" +
                    "  _-----=> ConnectModeState\n" +
                    " / _----=> DisconnectingState\n" +
                    "| / _---=> DisconnectedState\n" +
                    "|| / _--=> WpsRunningState\n" +
                    "||| /\n" +
                    "||||\n" +
                    "||||\n" +
                    "DefaultState";
            this.showKey_Match = "WifiClientModeImpl extends StateMachine 状态机事件处理模型 ";
            this.showKey_NO_Match = "WifiClientModeImpl extends StateMachine 状态机事件处理模型 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logList = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String logItem = oriLogList.get(i);
                if (!logItem.contains("dest=<null>")) {

                    // dest=ConnectedState
                    String itemlog = "";

                    //  org=ConnectedState dest=<null>
                    if (logItem.contains("org=ConnectedState dest=") && !logItem.contains("dest=<null>")) {
                        // ConnectedState 【状态变化】dest=DefaultState
                        itemlog = logItem.replace("dest=", "【离开连接状态】" + "dest=");
                    } else if (logItem.contains("dest=ConnectedState")) {

                        itemlog = logItem.replace("dest=", "【连接成功状态】 " + "dest=");
                    } else if (logItem.contains("dest=ObtainingIpState")) {

                        itemlog = logItem.replace("dest=", "【正在获取IP地址状态】 " + "dest=");
                    } else {
                        itemlog = logItem.replace("dest=", "【状态变化】 " + "dest=");
                    }


                    // dest=StaDisabledState
                    logList.add(itemlog);
                } else {
                    // dest=<null>
                    logList.add(logItem);
                }

            }
            return logList;
        }
    }

//    Line 262: WifiConfigManager - Configured networks Begin ----
//    Line 298: WifiConfigManager - Configured networks End ----

    static class WifiConfigManager_WifiConfiguration_SettingItem extends BasicShowItem {
        WifiConfigManager_WifiConfiguration_SettingItem() {
            this.mBeginKeyWord = "WifiConfigManager - Configured networks Begin";
            this.mEndKeyWord = "WifiConfigManager - Configured networks End";
            this.mUIPossition = "打印当前的 WifiConfiguration 可能非常长 ";
            this.desc = "打印当前保存的 WifiConfiguration ";
            this.showKey_Match = "WifiConfiguration wifi配置  ";
            this.showKey_NO_Match = "WifiConfiguration wifi配置 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();
            int configrationIndex = 0;
            String padd = "************************";

            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);
                if (mLogItem.contains("ID:") && mLogItem.contains("SSID:") && mLogItem.contains("BSSID:")) {
                    logArr.add(padd + " WifiConfigIndex:" + configrationIndex + padd);
                    configrationIndex++;
                }
                logArr.add(mLogItem);
            }
            //   ID: 3 SSID: "D-Link_DIR-816%" PROVIDER-NAME: null BSSID:
            return logArr;
        }
    }


    static class WifiConnectivityManager_SettingItem extends BasicShowItem {
        WifiConnectivityManager_SettingItem() {
            this.mBeginKeyWord = "WifiConnectivityManager - Log Begin";
            this.mEndKeyWord = "WifiConnectivityManager - Log End";

            this.mUIPossition = "当前网络管理类 WifiConnectivityManager  (网络连接 自动连接网络) ";
            this.desc = "NetworkEvaluator接口 对应的实现类: 用于对网络进行评估\n" +
                    "WifiNetworkSelector.java 包含 List<NetworkEvaluator> mEvaluators = new ArrayList<>(3);  用于评估网络的接口 NetworkEvaluator 实现类集合   \n" +
                    "Interface WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class ScoredNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class SavedNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class NetworkSuggestionEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "class PasspointNetworkEvaluator implements WifiNetworkSelector.NetworkEvaluator {}\n" +
                    "NetworkEvaluator 接口主要实现函数: WifiConfiguration selectNetwork(List<ScanDetail> scanDetails,HashSet<String> bssidBlacklist, WifiInfo wifiInfo, boolean connected, boolean disconnected, boolean untrustedNetworkAllowed) \n" +
                    "\n" +
                    "\n" +
                    "WifiNetworkSelector.java 包含(对候选网络进行估分)  Map<String, WifiCandidates.CandidateScorer> mCandidateScorers = new ArrayMap<>(); 【 \n" +
                    "class ScoreCardBasedScorer implements WifiCandidates.CandidateScorer {} 三种计分方式 \n" +
                    "class CompatibilityScorer implements WifiCandidates.CandidateScorer {}  // 性能计分\n" +
                    "class BubbleFunScorer implements WifiCandidates.CandidateScorer  {} // 冒泡分数\n" +
                    "CandidateScorer 接口主要在 WifiCandidates.java 的函数 【 ScoredCandidate choose(CandidateScorer candidateScorer) 中的参数接口】\n" +
                    "实现函数 ScoredCandidate scoreCandidates( Collection<Candidate> group) 】";
            this.showKey_Match = "对WIFI网络进行选择 管理 评估 候选网络的逻辑实现类 WifiConnectivityManager  ---匹配 OK ";
            this.showKey_NO_Match = "对WIFI网络进行选择 管理 评估 候选网络的逻辑实现类 WifiConnectivityManager  ---匹配 Failed";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {

            ArrayList<String> fixedLog = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);

                // Current connected network is not sufficient
                if (log.contains("Current connected network is not sufficient")) {
                    log = log + " 【 当前连接网络欠佳 ";
                }

                // Disabled configured networks:
                if (log.contains("Disabled configured networks:")) {
                    log = log + " 【 暂时禁用网络  ";
                }

                // About to run PasspointNetworkEvaluator
                if (log.contains("About to run PasspointNetworkEvaluator")) {
                    log = log + " 【 开始进行Passpoint网络评估  PasspointNetworkEvaluator 【WifiConfiguration selectNetwork(List<ScanDetail>...】  ";
                }

                // Set WiFi disabled
                if (log.contains("Set WiFi disabled")) {
                    log = log + " 【 WIFI关闭  ";
                }

                // Set WiFi enabled
                if (log.contains("Set WiFi enabled")) {
                    log = log + " 【 WIFI打开  ";
                }


                fixedLog.add(log);
            }

            return fixedLog;
        }
    }


    static class ConnectionEvents extends BasicShowItem {
        ConnectionEvents() {
            this.mBeginKeyWord = "mConnectionEvents:";
            this.mEndKeyWord = "mWifiLogProto.numSavedNetworks";

            this.mUIPossition = "当前手机尝试连接网络事件的统计 ";
            this.showKey_Match = "当前手机尝试连接网络事件的统计  ---匹配 OK ";
            this.showKey_NO_Match = "当前手机尝试连接网络事件的统计 ---匹配 Failed";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> fixedLog = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);
                // connectionResult=1
                if (!log.contains("startTime")) {
                    continue;
                }
                String tipLog = "";

                if (log.contains("screenOn=true")) {
                    tipLog = tipLog + "亮屏下:";
                } else {
                    tipLog = tipLog + "灭屏下:";
                }
                // connectionNominator=NOMINATOR_MANUAL
                // connectionNominator=NOMINATOR_SAVED
                if (log.contains("connectionNominator=NOMINATOR_MANUAL")) {
                    tipLog = tipLog + "手动操作连接:";
                } else if (log.contains("connectionNominator=NOMINATOR_SAVED")) {
                    tipLog = tipLog + "保存网络连接:";
                } else if (log.contains("connectionNominator=NOMINATOR_SAVED_USER_CONNECT_CHOICE")) {
                    tipLog = tipLog + "手动操作覆盖:";
                    // connectionNominator=NOMINATOR_SAVED_USER_CONNECT_CHOICE
                }


                if (log.contains("connectionResult=1")) {
                    tipLog = tipLog + "连接成功:";
                } else {
                    tipLog = tipLog + "连接失败:";
                }

                log = tipLog + log;

                fixedLog.add(log);

            }

            return fixedLog;
        }
    }


    static class StaEventList extends BasicShowItem {
        StaEventList() {
            this.mBeginKeyWord = "StaEventList:";
            this.mEndKeyWord = "mWifiLogProto";

            this.mUIPossition = "STA事件列表统计 ";
            this.showKey_Match = "STA事件列表  ---匹配 OK ";
            this.showKey_NO_Match = "STA事件列表 ---匹配 Failed";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> fixedLog = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);
                // connectionResult=1
                if (log.contains("mWifiLogProto")) {
                    break;
                }
                String tipLog = "";

/*
                if(log.contains("screenOn=true")){
                    tipLog = tipLog + "亮屏下:" ;
                }else{
                    tipLog = tipLog+"灭屏下:";
                }
*/

                log = tipLog + log;

                fixedLog.add(log);

            }

            return fixedLog;
        }
    }


    // WifiScoreReport:

    static DecimalFormat df = new DecimalFormat("#.00");

    static class WifiScoreReport extends BasicShowItem {
        WifiScoreReport() {
            this.mBeginKeyWord = "WifiScoreReport:";
            this.mEndKeyWord = "";

            this.mUIPossition = "连接WIFI每隔3秒的记录(灭屏或断连 记录中断) ";
            this.showKey_Match = "连接WIFI每隔3秒的记录  ---匹配 OK ";
            this.showKey_NO_Match = "连接WIFI每隔3秒的记录 ---匹配 Failed";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> fixedLog = new ArrayList<String>();

            String mPreTime = null;      // 之前记录的时间
            String mPreFreq = null;   // 之前的 记录的频率
            String mPreRssi = null;   // 之前的 记录的信号强度
            String mPreSpeed = null;  // 之前的速率
            String mPreNudrq = null; //  之前  接收到 NA 邻居通知的个数
            String mPreNuds = null;     // 本设备发送NS  邻居请求的报文的个数

            for (int i = 0; i < oriLogList.size(); i++) {
                String log = oriLogList.get(i);


                String[] strArr = log.split(",");


                if (strArr == null || strArr.length != 17) {
                    continue;
                }
                if ("time".equals(strArr[0].trim())) {
                    fixedLog.add(log);
                    continue;
                }

//    time,session,netid,rssi,filtered_rssi,rssi_threshold,freq,linkspeed,tx_good,tx_retry,tx_bad,rx_pps,nudrq,nuds,s1,s2,score
//    12-16 16:34:01.364,0,100,-43.0,-43.0,-80.0,5785,13,0.00,0.00,0.00,0.00,0,1,77,87,60


/*
                System.out.println("log = "+ log);
                System.out.println("WifiScoreReport strArr.size = "+ strArr.length);
                for (int j = 0; j < strArr.length; j++) {
                    System.out.println("index:"+j+"     value:"+strArr[j]);
                }
*/


/*

  0.time,
  1.session,
  2. netid,
  3rssi,
  4.filtered_rssi,
  5.rssi_threshold,
  6.freq,
  7.linkspeed,
  8.tx_good,
  9.tx_retry,
  10.tx_bad,
  11.rx_pps,
  12.nudrq,
  13.nuds,
  14.s1,
  15.s2,
  16.score

        */
                // index:0     value:12-16 18:03:26.408
                String time = strArr[0];
//                System.out.println("time = "+ time);

                String session = strArr[1];
                // 102,   用于避免  rssi 重复替换
                String netid = strArr[2];
                // index:3     value:-23.0
                // [rssi =-44.0]
                String rssi = strArr[3];

                String filtered_rssi = strArr[4];

                String rssi_threshold = strArr[5];
                // index:6     value:2447
                String freq = strArr[6];

                // linkspeed  index:7     value:72
                //[ linkspeed = 58 Mb/s]
                String speed = strArr[7];


                //  发送包的数量   [tx_good_package= 872.26 ]
                String tx_good = strArr[8];

                String tx_retry = strArr[9];

                if(!isNumeric(tx_retry) || !isNumeric(tx_good)){
                    continue;
                }


                double tx_good_value = Double.parseDouble(tx_good);
                double tx_retry_value = Double.parseDouble(tx_retry);
                double retryRate = (tx_retry_value / (tx_retry_value + tx_good_value)) * 100;
                String retryRate_Str = String.format("%.2f", retryRate);

                String tx_bad = strArr[10];
                //  接收到的数据的速率  [ rx_speed= 5.23 package/s]
                String rx_pps = strArr[11];
                String nudrq = strArr[12];
                String nuds = strArr[13];
                String s1 = strArr[14];
                String s2 = strArr[15];
                String score = strArr[16];

                // NDP（Neighbor Discovery Protocol，邻居发现协议
                // NS（Neighbor Solicitationh，领居请求） ICMPv6类型  Type=135  【 组播 】
                //NA（Neighbor Advertisement，邻居公告） ICMPv6类型  Type=136 【 单播 】

                // NUD（Neighbor Unreachable Detection，邻居不可达检测）是节点确定邻居可达性的过程
                //邻居不可达检测机制通过邻居可达性状态机来描述邻居的可达性。
// https://blog.csdn.net/lianliange85/article/details/45511393


                //  IEEE 802.3里面已经明确说明了只要第48bit是1就表示组播地址，所以无论MAC地址第1字节是0x01、0xC1或者是0x33都表示这个MAC地址是组播地址
                // 组播地址  mac源地址中 第 48位为0【就是第一个Mac位置】  就是组播地址      B0:39:56:72:7E:FE = 6*8 = 12 * 4 = 48个比特
                // 组播大多数情况下  组播地址：第一个字节最低位为1 , eg:  01e0.fc00.0006
                // index:12     value:0
                // nudrq【邻居不可达检测请求次数-13】,
                // 局部链接多播地址范围在 【IPv4 】224.0.0.0~224.0.0.255这是为路由协议和其它用途保留的地址，路由器并不转发属于此范围的IP包；
                // IPv6组播地址的最明显特征就是最高的8位固定为1111 1111。IPv6地址很容易区分组播地址,因为它总是以FF开始的

                // NS 单播报文:( Probe状态 )    结点主动发送的单播 给 目的IP结点 用于通知自身 IP地址 Mac地址 ， 以及 请求这个结点 的 IP地址 Mac地址
                // NS 组播报文:    结点主动发送的组播 用于通知自身 IP地址 Mac地址
// NA 单播报文:     结点在接收到 组播后  每个人都要=向发送NS组播地址的那个结点 发送自身的 IP地址  Mac地址
// NA 组播地址:    当自身的IP地址发生变化时 , 向 组播地址发送 NA组播地址通知 其他结点 我这个点结点的IP地址变化 (不需要得到回复)

// 当一个节点的链路层地址发生改变时，将以所有节点组播地址FF02::1为目的地址发送NA报文，通知链路上的其他节点更新邻居缓存表项。

                //     mNudYes, mNudCount,
                // int mNudYes = 0;    // Counts when we voted for a NUD      其他的设备发送NA，本设备进行反馈的次数
//                String nudrq = strArr[12];

                // int mNudCount = 0;  // Counts when we were told a NUD was sent  本设备发送NS进行请求的次数

                // index:13     value:21
                // nuds【检测到NUD(Neighbor Unreachable Detection,邻居不可达检测)的次数-14】
                // NS  （Neighbor Solicitationh，领居请求）  主动发送的邻居请求报文
//                String nuds = strArr[13];
                if (mPreTime == null) {
                    if (mPreTime == null) {  //  第一次初始化   // 之前记录的时间
                        mPreTime = time;
                    }
                    if (mPreRssi == null) {  //  第一次初始化  // 之前的 记录的频率
                        mPreRssi = rssi;
                    }

                    if (mPreFreq == null) {  //  第一次初始化  // 之前的 记录的信号强度
                        mPreFreq = freq;
                    }

                    if (mPreSpeed == null) {  //  第一次初始化  // 之前的速率
                        mPreSpeed = speed;
                    }

                    if (mPreNudrq == null) {  //  第一次初始化 //  之前  接收到 NA 邻居通知的个数
                        mPreNudrq = nudrq;
                    }

                    if (mPreNuds == null) {  //  第一次初始化  // 本设备发送NS  邻居请求的报文的个数
                        mPreNuds = nuds;
                    }
                    fixedLog.add(log);
                    continue;
                }

                String tipLog = "";

                double timeDistance = big5second(mPreTime, time);
                if (!time.equals(mPreTime) && timeDistance > 0) {
                    tipLog = tipLog + " 【记录中断" + timeDistance + "秒】 ";
                } else {
                    tipLog = tipLog + "";
                }
                mPreTime = time;  // 记录当前的记录


                if (!freq.equals(mPreFreq)) {
                    tipLog = tipLog + " 【频段切换】 ";
                } else {
                    tipLog = tipLog + "";
                }
                mPreFreq = freq;

                if (!nudrq.equals(mPreNudrq)) {
                    tipLog = tipLog + " 【接收到NA邻居通知】 ";
                } else {
                    tipLog = tipLog + "";
                }
                mPreNudrq = nudrq;

                if (!nuds.equals(mPreNuds)) {
                    tipLog = tipLog + " 【发送NS领居请求广播】 ";
                } else {
                    tipLog = tipLog + "";
                }
                mPreNuds = nuds;


                // log  对Log进行  修饰
                // index:3     value:-23.0
                // [rssi =-44.0]
//                String rssi = strArr[3];

                // linkspeed  index:7     value:72
                //[ linkspeed = 58 Mb/s]
//                String speed = strArr[7];

/*
                log = log.replace(netid+","+rssi+",",netid+","+"[rssi= "+rssi+"],");
                log = log.replace(","+speed+",",","+"[linkspeed= "+get3size(speed.trim())+" Mb/s],");
                log = log.replace(","+tx_good+",",","+"[tx_good= "+get7size(tx_good)+" package],");
                log = log.replace(","+rx_pps+",",","+"[rx_speed= "+get7size(rx_pps)+" package/s],");

            */
                //  发送包的数量   [tx_good= 872.26 package]
//                String tx_good =  strArr[8];

//                //  接收到的数据的速率  [ rx_speed= 5.23 package/s]
//                String  rx_pps  =  strArr[11];


                String logFiexed1 = time + "," + session + "," + netid + ",[rssi= " + rssi + "]," + filtered_rssi + "," + rssi_threshold + "," + freq + ",[linkspeed= " + get3size(speed) + "Mb/s]";
                String logFiexed2 = ",[tx_good=" + get7size(tx_good) + " pac],[tx_retry=" + get7size(tx_retry) + " pac ]" + ",[ retry_rate=" + get6size(retryRate_Str) + "% ] ,[tx_bad=" + get7size(tx_bad) + "],[ rx_speed=" + get7size(rx_pps) + " pkg/s]," + nudrq + "," + nuds + "," + s1 + "," + s2 + "," + score;
                log = logFiexed1 + logFiexed2;
                log = tipLog + ("".equals(tipLog) ? "" : "\n") + log;

                fixedLog.add(log);

            }

            return fixedLog;
        }
    }

    static String strDateFormat = "MM-dd HH:mm:ss.SSS";
    static SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

    static double big5second(String time_old, String time_new) {
        double flag = 0;
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(time_old);
            date2 = sdf.parse(time_new);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long stamp1 = date1.getTime();
        long stamp2 = date2.getTime();
        double distance = (stamp2 - stamp1) / 1000; // 秒
        if (distance > 5) {  // 以 差距5秒作为依据
            flag = distance;
        } else {
            flag = 0;
        }

        return flag;

    }

    // Passpoint Log 相关的 项
    static {
        CatagoryItem PasspointNetWork_Log_Category = new CatagoryItem("PassPoint 网络 相关Log项");
        Passpoint_Provider_Item m_Passpoint_Provider_Item = new Passpoint_Provider_Item();
        PasspointNetWork_Log_Category.addAShowItem(m_Passpoint_Provider_Item);

        categoryList.add(PasspointNetWork_Log_Category);
    }


    static class Passpoint_Provider_Item extends BasicShowItem {
        Passpoint_Provider_Item() {
            this.mBeginKeyWord = "PasspointManager - Providers Begin";
            this.mEndKeyWord = "PasspointManager - Providers End";
            this.mUIPossition = "打印当前的 Passpoint-Providers  ";
            this.desc = "打印当前保存的 PasspointProvider  ";
            this.showKey_Match = "PasspointProvider  标识passpoint网络____搜索成功  ";
            this.showKey_NO_Match = "PasspointProvider 标识passpoint网络____搜索失败 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();
            int configrationIndex = 1;
            String padd = "************************";

            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);
                if (mLogItem.contains("ProviderId:")) {
                    logArr.add(padd + " 第 " + configrationIndex + " 个 PasspointProvider " + padd);
                    configrationIndex++;
                }
                logArr.add(mLogItem);
            }
            //   ID: 3 SSID: "D-Link_DIR-816%" PROVIDER-NAME: null BSSID:
            return logArr;
        }
    }


    // WifiScan扫描 Log 相关的 项
    static {
        CatagoryItem WifiScan_Log_Category = new CatagoryItem("WIFI 扫描 网络 相关Log项");
        WifiScannerDetail m_WifiScannerDetail = new WifiScannerDetail();
        WifiScan_Log_Category.addAShowItem(m_WifiScannerDetail);

        WifiScannerPNODetail m_WifiScannerPNODetail = new WifiScannerPNODetail();
        WifiScan_Log_Category.addAShowItem(m_WifiScannerPNODetail);

        WifiScannerLog m_WifiScannerLog = new WifiScannerLog();
        WifiScan_Log_Category.addAShowItem(m_WifiScannerLog);

        categoryList.add(WifiScan_Log_Category);
    }


    static class WifiScannerDetail extends BasicShowItem {
        WifiScannerDetail() {
            this.mBeginKeyWord = "Latest scan results:";
            this.mEndKeyWord = "Latest native pno scan results:";
            this.mUIPossition = "当前WIFI扫描结果以及Log信息";
            this.desc = "FullScan-所有扫描结果都回调的请求  SingleScan只回调一次后终止的扫描请求";
            this.showKey_Match = "WIFI扫描结果 ____搜索成功  ";
            this.showKey_NO_Match = "WIFI扫描结果 ____搜索失败 ";
            this.srcFileName = "wifiscanner.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }


        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();


            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);

                if (mLogItem.contains("Latest scan results:")) {
                    mLogItem = mLogItem + "  【 mSingleScanStateMachine.getCachedScanResultsAsList() 】";
                } else if (mLogItem.contains("Latest native scan results:")) {
                    mLogItem = mLogItem + "  【 ArrayList<ScanDetail> mNativeScanResults = WifiNative.getScanResults(mIfaceName) 】 ";
                }
                logArr.add(mLogItem);

            }

            return logArr;

        }
    }

    static class WifiScannerPNODetail extends BasicShowItem {
        WifiScannerPNODetail() {
            this.mBeginKeyWord = "Latest native pno scan results:";
            this.mEndKeyWord = "===";
            this.mUIPossition = "当前 WIFI_PNO 扫描结果以及Log信息";
            this.desc = "FullScan-所有扫描结果都回调的请求  SingleScan只回调一次后终止的扫描请求";
            this.showKey_Match = "WIFI_PNO扫描结果 ____搜索成功  ";
            this.showKey_NO_Match = "WIFI_PNO扫描结果 ____搜索失败 ";
            this.srcFileName = "wifiscanner.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

}
    static class WifiScannerLog extends BasicShowItem {
        WifiScannerLog() {
            this.mBeginKeyWord = "WifiScanningService - Log End ----";
            this.mEndKeyWord = "Latest scan results:";
            this.mUIPossition = "当前WIFI扫描结果以及Log信息";
            this.desc = "FullScan-所有扫描结果都回调的请求  SingleScan只回调一次后终止的扫描请求";
            this.showKey_Match = "WIFI扫描结果 ____搜索成功  ";
            this.showKey_NO_Match = "WIFI扫描结果 ____搜索失败 ";
            this.srcFileName = "wifiscanner.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();


            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);



                String pnoState = "\nSingleScanState\n" +
                        "   ||\n" +
                        "HwPnoScanState\n" +
                        "   || \n" +
                        "StartedState\n" +
                        "   || \n" +
                        "DefaultState\n";

                String singleState = "\n _-----=> ScanningState\n" +
                        "/  _----=> IdleState\n" +
                        "| / \n" +
                        "|| \n" +
                        "DriverStartedState\n" +
                        "|| \n" +
                        "DefaultState\n";

                String backState = "\n  _-----=> StartedState\n" +
                        " / _----=> PausedState\n" +
                        "| /\n" +
                        "|| \n" +
                        "DefaultState\n" +
                        "WifiBackgroundScanStateMachine 后台扫描状态机\n";

                if(mLogItem.contains("WifiPnoScanStateMachine:")){
                    mLogItem = pnoState + mLogItem;
                }else if(mLogItem.contains("WifiSingleScanStateMachine:")){
                    mLogItem = singleState + mLogItem;
                } else if(mLogItem.contains("listeners:")){
                    mLogItem = backState + mLogItem;
                }


// mSingleScanStateMachine.getCachedScanResultsAsList()



                logArr.add(mLogItem);
            }

            return logArr;
        }
    }



        // AP 热点 Log 相关的 项
    static {
        CatagoryItem Hotspot_Log_Category = new CatagoryItem("Hotspot热点 相关Log项");
        SoftApTetheredEvents m_SoftApTetheredEvents = new SoftApTetheredEvents();
        Hotspot_Log_Category.addAShowItem(m_SoftApTetheredEvents);

        Tethering m_Tethering = new Tethering();
        Hotspot_Log_Category.addAShowItem(m_Tethering);
        categoryList.add(Hotspot_Log_Category);
    }

    static class SoftApTetheredEvents extends BasicShowItem {
        SoftApTetheredEvents() {
            this.mBeginKeyWord = "mSoftApTetheredEvents:";
            this.mEndKeyWord = "mSoftApLocalOnlyEvents:";
            this.mUIPossition = "当前当前热点的相关事件信息(  0-打开热点  1-关闭热点   2-连接客户端数量变化   )";
            this.desc = "// event_type == 0-(SOFT_AP_UP,热点打开可以使用)  1-(SOFT_AP_DOWN,热点关闭)  2-(连接的热点的客户端数量变化)\n" +
                    "// channel_bandwidth == 0-(无效的带宽) 1-(非高速20Mhz--Not Very High)  2-(20MHz)  3(40MHz) 4-(80Mhz) 5-(80Mhz+80Mhz两个载波)  6-(180MHz)\n" +
                    "// time_stamp_millis 开机时间戳";
            this.showKey_Match = "当前当前热点的相关事件信息 SoftApTetheredEvents ____搜索成功  ";
            this.showKey_NO_Match = "当前当前热点的相关事件信息 SoftApTetheredEvents ____搜索失败 ";
            this.srcFileName = "wifi.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();

            for (int i = 0; i < oriLogList.size(); i++) {

                String mLogItem = oriLogList.get(i);

                // event_type=2,time_stamp_millis=113997,num_connected_clients=2
                String tipLog = "";

                if (mLogItem.contains("event_type=0")) {
                    tipLog = tipLog + " 【热点打开】 ";
                } else if (mLogItem.contains("event_type=1")) {
                    tipLog = tipLog + " 【热点关闭】 ";
                } else if (mLogItem.contains("event_type=2")) {
                    tipLog = tipLog + " 【连接变化】 ";
                }

                if (mLogItem.contains("time_stamp_millis")) {
                    String[] strArr = mLogItem.split(",");
                    if (strArr.length == 5) {
                        String stamp1 = strArr[1];
                        String stamp2 = stamp1.replace("time_stamp_millis=", "").trim();
                        // 7829616 转为 时分秒
                        long stamp2Long = Long.parseLong(stamp2);

                        Date date = new Date(stamp2Long);
                        long hour = stamp2Long / (60 * 60 * 1000);
                        long minute = (stamp2Long - hour * 60 * 60 * 1000) / (60 * 1000);
                        long second = (stamp2Long - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
                        String secondStr = ("" + second).length() == 1 ? "0" + ("" + second) : ("" + second);
                        String minuteStr = ("" + minute).length() == 1 ? "0" + ("" + minute) : ("" + minute);
                        String time = "开机" + hour + "时" + minuteStr + "分" + secondStr + "秒";
                        tipLog = tipLog + " 【" + time + "】 ";
//                        mLogItem = mLogItem.replace(stamp1,time);
                    }


                }
                // time_stamp_millis=300126,
                mLogItem = tipLog + mLogItem;
                logArr.add(mLogItem);
            }


            return logArr;
        }
    }

    // Tethering:

    static class Tethering extends BasicShowItem {
        Tethering() {
            this.mBeginKeyWord = "Tethering:";
            this.mEndKeyWord = "";
            this.mUIPossition = "热点相关配置与日志";
            this.desc = "connectivity 中记录了当前 热点 Tethering 的相关配置信息 ";
            this.showKey_Match = "Tethering 热点配置信息 ____搜索成功  ";
            this.showKey_NO_Match = "Tethering 热点配置信息 ____搜索失败 ";
            this.srcFileName = "connectivity.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();

            for (int i = 0; i < oriLogList.size(); i++) {

                String mLogItem = oriLogList.get(i);
                String tipLog = "";

                if (mLogItem.contains("enableLegacyDhcpServer: false")) {
                    tipLog = tipLog + "【 DHCP服务未打开--Dhcp_Start_Address无法使用】 \n" +
                            "【 adb shell settings get global tether_enable_legacy_dhcp_server   // 查看DHCP地址服务打开情况】\n" +
                            "【 adb shell settings put global tether_enable_legacy_dhcp_server 0  // 关闭DHCP地址服务 】 \n" +
                            "【 adb shell settings put global tether_enable_legacy_dhcp_server 1  // 开启DHCP地址服务  adb reboot 】";
                } else if (mLogItem.contains("enableLegacyDhcpServer: true")) {
                    tipLog = tipLog + "【 enableLegacyDhcpServer 标识  DHCP服务未打开--Dhcp_Start_Address可以使用】  \n" +
                            "【 adb shell settings get global tether_enable_legacy_dhcp_server   // 查看DHCP地址服务打开情况】\n" +
                            "【 adb shell settings put global tether_enable_legacy_dhcp_server 0  // 关闭DHCP地址服务 】 \n" +
                            "【 adb shell settings put global tether_enable_legacy_dhcp_server 1  // 开启DHCP地址服务  adb reboot 】";
                }

                if (mLogItem.contains("legacyDhcpRanges:")) {
                    tipLog = tipLog + "【 adb shell settings get global tether_enable_legacy_dhcp_server   // 查看DHCP地址服务打开情况】\n" +
                            "【 DHCP分配地址范围,《Rang1={0-当前最小地址  1-当前最大地址}》《Rang2={2-当前最小地址  3-当前最大地址}》】";
                }


                mLogItem = tipLog + ("".equals(tipLog) ? "" : "\n") + mLogItem;

                logArr.add(mLogItem);
            }


            return logArr;
        }
    }


    // GPS 定位相关Log项
    static {
        CatagoryItem GPS_Log_Category = new CatagoryItem("GPS 定位相关Log项");
        Location_Listener m_Location_Listener = new Location_Listener();
        GPS_Log_Category.addAShowItem(m_Location_Listener);

        Active_GnssStatus_Listeners m_Active_GnssStatus_Listeners = new Active_GnssStatus_Listeners();
        GPS_Log_Category.addAShowItem(m_Active_GnssStatus_Listeners);


        GNSS_KPI_START m_GNSS_KPI_START = new GNSS_KPI_START();
        GPS_Log_Category.addAShowItem(m_GNSS_KPI_START);
        categoryList.add(GPS_Log_Category);

    }

    static class Location_Listener extends BasicShowItem {
        Location_Listener() {
            this.mBeginKeyWord = "Current Location Manager state:";
            this.mEndKeyWord = "Active Records by Provider:";
            this.mUIPossition = "正在进行位置请求的APP列表";
            this.showKey_Match = "正在进行位置请求的APP列表 ____搜索成功  ";
            this.showKey_NO_Match = "正在进行位置请求的APP列表 ____搜索失败 ";
            this.srcFileName = "location.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();

            for (int i = 0; i < oriLogList.size(); i++) {

                String mLogItem = oriLogList.get(i);
                String tipLog = "";

                if (mLogItem.contains("Current System Time")) {
                    tipLog = tipLog + "【 系统时间 】 【 开机时间 】";
                }

                if (mLogItem.contains("location: true")) {
                    if (mLogItem.contains("com.baidu.BaiduMap")) {
                        tipLog = tipLog + "【 百度地图_正监听 】";
                    } else if (mLogItem.contains("com.google.android.apps.maps")) {
                        tipLog = tipLog + "【 谷歌地图_正监听 】";
                    } else if (mLogItem.contains("com.endomondo.android")) {
                        tipLog = tipLog + "【 endomondo_正监听 】";
                    } else if (mLogItem.contains("com.autonavi.")) {
                        tipLog = tipLog + "【 高德地图_正监听 】";
                    } else if (mLogItem.contains("com.android.networkstack.inprocess")) {
                        tipLog = tipLog + "【 系统流量_正监听 】";
                    } else if (mLogItem.contains(" android(")) {
                        tipLog = tipLog + "【 系统应用_正监听 】";
                    } else {
                        tipLog = tipLog + "【 该APP_正监听 】";
                    }


                    // background
                    if (mLogItem.contains("background)")) {
                        tipLog = tipLog + "【 后台请求 】";
                    } else if (mLogItem.contains("foreground)")) {
                        tipLog = tipLog + "【 前台请求 】";
                    }


                    if (mLogItem.contains("[gps ")) {  // gps
                        tipLog = tipLog + "【 GPS主动请求 】";
                    } else if (mLogItem.contains("[passive ")) {
                        tipLog = tipLog + "【 被动接收请求 】";
                    }

                    if (mLogItem.contains("fastest=+")) {  // fastest=
                        String sub1 = mLogItem.substring(mLogItem.indexOf("fastest=+") + "fastest=+".length());
                        String sub2 = sub1.substring(0, sub1.indexOf("]"));
//                        System.out.println("sub2 = " + sub2);
                        // 12h0m0s0ms
                        sub2 = sub2.replace("h", "小时");
                        sub2 = sub2.replace("ms", "毫秒");
                        sub2 = sub2.replace("m", "分钟");
                        sub2 = sub2.replace("s", "秒");
                        tipLog = tipLog + "【 间隔时间: " + sub2 + " 】";
                    }
                    //
                    //     Reciever[8a945 listener UpdateRecord[passive com.baidu.BaiduMap(10209 background) Request[POWER_NONE passive fastest=+9s0ms] null] monitoring location: true]

                    // com.google.android.apps.maps
                    // com.endomondo.android
                }

                mLogItem = tipLog + ("".equals(tipLog) ? "" : "\n") + mLogItem;
                logArr.add(mLogItem);

            }

            return logArr;
        }
    }

/*    Active GnssStatus Listeners:
            25838 10209 com.baidu.BaiduMap: false
            21850 10159 com.google.android.gms: true
            15353 10206 com.autonavi.minimap: false
    Historical Records by Provider:
    */


    static class Active_GnssStatus_Listeners extends BasicShowItem {
        Active_GnssStatus_Listeners() {
            this.mBeginKeyWord = "Active GnssStatus Listeners:";
            this.mEndKeyWord = "Historical Records by Provider:";
            this.mUIPossition = "当前实现监听接口的APP列表 IGnssStatusListener.aidl 的 APP, 能通过回调得到 GPS状态数据 ";
            this.showKey_Match = "当前实现监听接口的APP列表 ____搜索成功  ";
            this.showKey_NO_Match = "当前实现监听接口的APP列表 ____搜索失败 ";
            this.srcFileName = "location.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();
            logArr.add("1.PID-进程ID    2.UID-APP的用户ID     3.APP_NAME-包名    4.该APP是否包含在位置白名单APP列表中(是否权限高)");
            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);

                if (mLogItem.contains("true") || mLogItem.contains("false") || mLogItem.contains("GnssStatus")) {
                    logArr.add(mLogItem);
                }


            }

            return logArr;
        }
    }


    static class GNSS_KPI_START extends BasicShowItem {
        GNSS_KPI_START() {
            this.mBeginKeyWord = "GNSS_KPI_START";
            this.mEndKeyWord = "GNSS_KPI_END";
            this.mUIPossition = "GPS 表现的性能指标  ";
            this.showKey_Match = "GPS 表现的性能指标____搜索成功  ";
            this.showKey_NO_Match = "GPS 表现的性能指标____搜索失败 ";
            this.srcFileName = "location.txt"; // 资源文件来源
            this.isAlwaysShow = true;  // 总是显示
            this.isShowFlutterList = true;  // 显示过滤的信息
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            ArrayList<String> logArr = new ArrayList<String>();
            for (int i = 0; i < oriLogList.size(); i++) {
                String mLogItem = oriLogList.get(i);

                String tipLog = "";

                if (mLogItem.contains("Number of TTFF reports:")) {
                    tipLog = tipLog + "【  冷启动 定位报告的记录:  Time to First Fix，就是到了一个完全陌生的地方，GPS里面没有存任何信息  】";
                }

                if (mLogItem.contains("Number of CN0 reports")) {
                    tipLog = tipLog + "【 CN0 有效载噪比 卫星 个次 】";
                }

                if (mLogItem.contains("constellation types")) {
                    tipLog = tipLog + "\n【 0=\"UNKNOWN\"  \n" +
                            "【 1=\"GPS\"=GPS-美国全球定位系统 = 全球定位系统(Global Positioning System，GPS)\n" +
                            "【 2=\"SBAS\"= SBAS（Satellite-Based Augmentation System），即星基增强系统 =美国WAAS(Wide Area Augmentation System)\n" +
                            "【 3=\"GLONASS\"=格洛纳斯-俄罗斯实现的国家导航系统 \n" +
                            "【 4=\"QZSS\"=准天顶卫星系统 Quasi-Zenith Satellite System；缩写：QZSS = 日本发展的国家定位系统 \n" +
                            "【 5=\"BEIDOU\"=中国北斗卫星导航系统（英文名称：BeiDou Navigation Satellite System，简称BDS）\n" +
                            "【 6=\"GALILEO\"= 欧盟实现的 伽利略卫星导航系统（Galileo satellite navigation system）\n" +
                            "【 7=\"IRNSS\" = 印度区域导航卫星系统（英语：Indian Regional Navigation Satellite System (IRNSS)、NAVIC）";
                }

                mLogItem = mLogItem + tipLog;
                logArr.add(mLogItem);


            }

            return logArr;
        }
    }

    //  zukgit

    static class CatagoryItem {
        int mCatagoryIndex;
        String mTitleName;
        ArrayList<AShowItem> mSameCategoryList;

        CatagoryItem(String name) {
            mTitleName = name;
            mSameCategoryList = new ArrayList<AShowItem>();
            mCatagoryIndex = CatagoryIndexNum;
            CatagoryIndexNum++;
        }

        public int getShowItemSize() {
            return this.mSameCategoryList.size();
        }


        public void addAShowItem(AShowItem mShowItem) {

            this.mSameCategoryList.add(mShowItem);
        }


        public String getmTitleName() {
            return mTitleName;
        }

        public ArrayList<AShowItem> getmSameCategoryList() {
            return mSameCategoryList;
        }

    }


    static class BasicShowItem extends AShowItem {

        @Override
        ArrayList<String> getOriginInfosFromSrcFile(File srcFile) {
            return null;
        }

        @Override
        ArrayList<String> doFlutterList(ArrayList<String> oriLogList) {
            return oriLogList;   //  默认不进行任何处理  交给 具体的实现类  去进行过滤的操作
        }

        @Override
        String showCurrentInfo() {
            StringBuilder sb = new StringBuilder();
            if (isAlwaysShow) {  // 是否 总是显示
                sb.append("title:" + getKeyTitle() + "\n");
                sb.append("匹配结果:" + this.getKeyWordStr() + "\n");
                sb.append("Dump命令: " + this.getDumpCommand() + "\n");
                sb.append("desc: " + this.desc + "\n");


                if (isAlwaysShow || isItemSearchSuccess) {  // 是否匹配成功 搜索到    如果总是显示  那么把搜索到的log也打印
                    if (isShowFlutterList) {
                        sb.append("搜索结果: " + "\n");
                        for (int i = 0; i < this.fluterInfos.size(); i++) {
                            sb.append(this.fluterInfos.get(i) + "\n");
                        }

                    }

                }
            }
            return sb.toString();
        }

        @Override
        void InitWithBugReportFile(File bugReportFile) {
            if (bugReportFile != null) {
                this.srcFile = bugReportFile;
                this.originDumpFileName = srcFileName;
                srcFileName = bugReportFile.getName();
                isSrcBugReportFile = true;
            }
        }

        @Override
        void InitWithDumpPath(String DumpPath) {
            this.originDumpFileName = this.srcFileName;
            String absPath = DumpPath + this.srcFileName;
            isSrcBugReportFile = false;
            this.srcFile = new File(absPath);
        }
    }


    static abstract class AShowItem {
        File srcFile;  // 数据来源的文件
        String srcFileName;   // 数据来源文件名称
        String originDumpFileName;
        String desc;  //  说明
        boolean isSrcBugReportFile;  // 是否数据来源于 bugreport.txt    true  ， fasle 表示来源于 dump

        AShowItem() {
            this.originInfos = new ArrayList<String>();
            this.fluterInfos = new ArrayList<String>();
            this.keyWordList_Must = new ArrayList<String>();
            this.keyWordList_JustOne = new ArrayList<String>();
            isStart_BeginKey = false;
            isStart_EndKey = false;
        }

        String showKey_Match;  // 如果匹配到关键词 显示的内容
        String showKey_NO_Match;  // 如果匹配到关键词 显示的内容


        int matchIndex;  // 匹配到的次数:


        //  如果 showKey_Match 和 showKey_NO_Match 匹配到的一个整体  检测是否包含 keyWordList 里的关键字
        ArrayList<String> keyWordList_Must;  // 有些整体里 必须都包含的 关键词
        ArrayList<String> keyWordList_JustOne;  // 有些整体里 只要包含一个 就可以

        ArrayList<String> originInfos;  // 从 资源文件 获取到的 原始Log
        ArrayList<String> fluterInfos;  // 对 原始Log 进行过滤 得到的Log

        String mBeginKeyWord;  // 文件中 开始的 关键词
        String mEndKeyWord;   // 文件中 结束的关键词
        boolean isStart_BeginKey;   // 是否 以 开始关键词 为 起始  true  XXX.start   , false  就是  contain
        boolean isStart_EndKey;     // 是否以  结束关键词 为 起始  true  XXX.start   , false  就是  contain

        String mUIPossition;   // 该Item 在UI上的位置显示情况
        boolean isShowFlutterList;  // 是否 需要显示  过滤到的Log 集合
        // 有些  只需要你匹配到 就能说明  设置选项

        //  如果只是匹配 就不需要 对原始Log 进行收集   也不需要对 mEndKeyWord 进行匹配 就满足条件
        boolean isJustMatch;  // 是否需要从 文件中 得到 原始的Log集合  是否只是匹配 ，

        // 是否文件 搜索成功  从 对应的 文件 搜索成功  有些 没有 搜索成功 就说明 没有搜索到
        boolean isItemSearchSuccess;

        boolean isAlwaysShow;  // 不管 是否 匹配到  都需要显示的项


        // 从文件 获取 对应的 需要的Log
        abstract ArrayList<String> getOriginInfosFromSrcFile(File srcFile);

        // 对Log 文件 进行 需要的过滤 留下有用信息
        abstract ArrayList<String> doFlutterList(ArrayList<String> oriLogList);

        // 对当前文件的展示信息
        abstract String showCurrentInfo();

        // 以 bugreport.txt 文件进行原始数据的过滤来源
        abstract void InitWithBugReportFile(File bugReportFile);

        // 以 dumpsys  xxxx   f8/xxxxx.txt  文件进行原始数据的过滤来源
        abstract void InitWithDumpPath(String DumpPath);


        String getDumpCommand() {
            String name = this.originDumpFileName;
            if (name.contains(".")) {
                name = name.substring(0, name.indexOf("."));
            }

            String dumpCommand = "adb shell dumpsys " + name + " >" + " " + name + ".txt";


            return dumpCommand;
        }


        String getKeyWordStr() {
            String keyWordStr = "Begin关键词:【 " + this.mBeginKeyWord + "】   End关键词: 【 " + this.mEndKeyWord + " 】";
            String isMatch = isItemSearchSuccess ? "【 匹配OK " + "文件:" + this.srcFileName + " 】" : "【 匹配Failed " + "文件:" + this.srcFileName + "】";


            return isMatch + ":" + keyWordStr;
        }

        String getKeyTitle() {
            if (isItemSearchSuccess) {
                return showKey_Match;
            }
            return showKey_NO_Match;
        }


        boolean checkJustOneKeyWord(String content) {  //  只要包含一项 那么就可以 保存到 LogList 中
            boolean flag = false;
            if (this.keyWordList_JustOne == null) {  //  只有
                return true;
            }
            //  如果没有可选关键词  那么就返回 true  加入 Log列表   如果   必须all 大于 0   和  justone 等于 0
            // 说明  没有匹配到 must  , 下来匹配 justone  这时  justone 为空  那么就说明  不能加入到 loglist
            if (this.keyWordList_Must.size() > 0 && this.keyWordList_JustOne.size() == 0) {
                return false;
            }
            //  如果  必须all  和  justone 都为 空 那么 返回 true  标识没有检索的关键词  需要加入 loglist
            if (this.keyWordList_Must.size() == 0 && this.keyWordList_JustOne.size() == 0) {
                return true;
            }


            for (int i = 0; i < this.keyWordList_JustOne.size(); i++) {
                String keyStrItem = keyWordList_JustOne.get(i);
                if (content.contains(keyStrItem)) {  //  如果 字符串 中 包含 可选关键词  那么 直接返回 false  不保存到Log
                    flag = true;
                    break;
                }
            }
            return flag;

        }


        boolean checkMustKey(String content) {  //  如果没有必须的关键词  那么就返回 true  加入 Log列表
            boolean flag = true;
            if (this.keyWordList_Must == null) {
                return true;
            }
            //  如果没有必须的关键词  那么就返回 true  加入 Log列表
            if (this.keyWordList_Must.size() == 0) {
                return true;
            }

            for (int i = 0; i < this.keyWordList_Must.size(); i++) {
                String keyStrItem = keyWordList_Must.get(i);
                if (!content.contains(keyStrItem)) {  //  如果 字符串 中 不包含 关键词  那么 直接返回 false  不保存到Log
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        void ReadAndMatchSrcFile() {

            //  如果
            ArrayList<String> curLogList;
            if (isBugreportExist && mBugreport_ExistFile != null) {
                if (mBugReporList == null) {
                    mBugReporList = readArrStringFromFile(mBugreport_ExistFile);
                }
                curLogList = mBugReporList;
                // 从 mBugReporList 去 获取 匹配到的信息
            } else {  // 从Dump 去获取 指定的信息
                curLogList = readArrStringFromFile(this.srcFile);
            }

            if (this.isJustMatch) {  //  如果 仅仅 只是 匹配  一行

                for (int i = 0; i < curLogList.size(); i++) {
                    String lineContent = curLogList.get(i);
                    if (isStart_BeginKey ? lineContent.startsWith(this.mBeginKeyWord) : lineContent.contains(this.mBeginKeyWord)) {
                        this.isItemSearchSuccess = true;
                        this.isAlwaysShow = true;

                        if (checkMustKey(lineContent)) {
                            this.originInfos.add(lineContent);

                        } else if (checkJustOneKeyWord(lineContent)) {  //   如果不包含 必须都有的关键词 ， 检测 可能有的关键词  如果为空
                            this.originInfos.add(lineContent);

                        }
//  1. Must-failed

                    }
                }

            } else {  //   需要 上下文匹配的      多行当做一个整体
                boolean isBegin = false;

                StringBuilder sb = new StringBuilder();
                ArrayList<String> tempLogList = new ArrayList<String>();
                for (int i = 0; i < curLogList.size(); i++) {
                    String lineContent = curLogList.get(i);


                    if (!isBegin && (isStart_BeginKey ? lineContent.startsWith(this.mBeginKeyWord) : lineContent.contains(this.mBeginKeyWord))) {
                        this.isAlwaysShow = true;
                        this.isItemSearchSuccess = true;
                        isBegin = true;
                        matchIndex++;


                        sb = new StringBuilder();
                        tempLogList = new ArrayList<String>();
                        sb.append(lineContent);        // isBegin = true 在最后面 添加到了 数据里了
                        tempLogList.add(lineContent);
                        continue;   //  不往下走  下一次循环
                    }


                    if (isBegin && this.mEndKeyWord.length() != 0 && (isStart_EndKey ? lineContent.startsWith(this.mEndKeyWord) : lineContent.contains(this.mEndKeyWord))) {
                        isBegin = false;
                        sb.append(lineContent);
                        tempLogList.add(lineContent);

                        // 检测 是否包含 那些 关键词   //  如果包含  那么 加入
                        if (checkMustKey(sb.toString())) {
//                            String str = "匹配Index:"+ matchIndex;
//                            this.originInfos.add(str);
                            this.originInfos.addAll(tempLogList);

                        } else if (checkJustOneKeyWord(sb.toString())) {  //   如果不包含 必须都有的关键词 ， 检测 可能有的关键词
//                            String str = "匹配Index:"+ matchIndex;
//                            this.originInfos.add(str);
                            this.originInfos.addAll(tempLogList);
                        }

                    }


                    if (isBegin) {


/*

                        if(this.mBeginKeyWord.contains("mLinkProperties")){

                            System.out.println("xxxxx1-isBegin " + isBegin  );
                            System.out.println("xxxxx1 " + this.mEndKeyWord  );
                            System.out.println("xxxxx2 " + this.mEndKeyWord.length() );
                            System.out.println("xxxxx3 " + lineContent );
                            System.out.println("xxxxx4  lineContent.length() " + lineContent.length() );
                            System.out.println("bbbbb "+ Arrays.toString(lineContent.getBytes()));
                            System.out.println("xxxxx-ascii " + ascii2Native(lineContent) );
                            System.out.println();
                        }

*/


                        if ("".equals(this.mEndKeyWord.trim()) && lineContent.length() == 0) {  //  如果是 以 "" 标识  末尾标识是一个空格
                            isBegin = false;

                            sb.append(lineContent);
                            tempLogList.add(lineContent);
                            if (checkMustKey(sb.toString())) {
                                this.originInfos.addAll(tempLogList);

                            } else if (checkJustOneKeyWord(sb.toString())) {  //   如果不包含 必须都有的关键词 ， 检测 可能有的关键词

                                this.originInfos.addAll(tempLogList);
                            }

                        } else {
                            sb.append(lineContent);
                            tempLogList.add(lineContent);
                        }


                        // this.originInfos.add(lineContent);
                    }
                }

            }

            if (this.originInfos.size() > 0) {
                this.fluterInfos = doFlutterList(this.originInfos);  // 对从文件得到原始的数据 进行 过滤
            }


        }

    }

    static ArrayList<String> readArrStringFromFile(File fileItem) {
        ArrayList<String> fileStrArr = new ArrayList<String>();
        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null) {
                    continue;
                }
                fileStrArr.add(lineContent.trim());
            }
            curBR.close();
        } catch (Exception e) {
        }
        return fileStrArr;
    }

    static String PREFIX = "\\u";

    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX);
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!PREFIX.equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }


    public static boolean isNumeric(String str_param){
            String str =str_param.replace(".","");
            for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


}