
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 标记统计:
1.来自wlan0接收到的事件: 从下往上传输:    wpa_supplicant: nl80211: Drv Event
wpa_supplicant: nl80211: Drv Event 33 (NL80211_CMD_TRIGGER_SCAN) received for wlan0
wpa_supplicant: nl80211: Drv Event 103 (NL80211_CMD_VENDOR) received for wlan0
wpa_supplicant: nl80211: Drv Event 34 (NL80211_CMD_NEW_SCAN_RESULTS) received for wlan0
wpa_supplicant: nl80211: Drv Event 79 (NL80211_CMD_SET_REKEY_OFFLOAD) received for wlan0


2. ACS选择的情况            __wlan_hdd_cfg80211_do_acs:
__wlan_hdd_cfg80211_do_acs: 2608: ACS config PCL: len: 20
__wlan_hdd_cfg80211_do_acs: 2614: channel:64, weight:255
__wlan_hdd_cfg80211_do_acs: 2673: ACS channel list: len: 9
 */

/* 打开wifi verbose按钮
#1. open wifi verbose button to capture bug2go
tap 5 time at Settings > System > About pone > Build number
Setting > System > Advanced > Developer options >Enable WiFi Verbose Logging [toogle open]

#2. does it occur when you connect other wifi network without ELD_VISITANTES_SOCIAL ?


 */

/*  int 配置说明

#1. gindoor_channel_support
gindoor_channel_support=0    所有的5G channel  都会被ACS 自动信道选择功能 排除 ( 只能在2.4G信道中选择信道打开热点)
gindoor_channel_support=1    5G信道可用 5170-5250  但是不能使用 DFS信道

2. etsi13_srd_chan_in_master_mode
etsi13_srd_chan_in_master_mode=0    关闭在低功耗channel -14dm , 对于该channel的使用
etsi13_srd_chan_in_master_mode=1    使得设备可以使用低功耗channel -14dm
22:29:26.500544  [kworker/u16:4][0x258a02e2][21:29:26.487647]wlan: [245:D:SME] sme_set_etsi13_srd_ch_in_master_mode: 9080: srd_ch_support 0

 */

/* 缩写说明

1.SRD channels : 低功耗频率
SRD ( Short-Range-Device ) 短距离通信设备 [低功耗收发设备]


2.PCL List  policy list
  各个国家允许的信道列表

  3. captive portal detection   门户检测 国家探测
 */
public class A8_WIFI_Log_Search {

    //  public static final ArrayList<String> StringArr = new ArrayList<>();  // 测试使用

    // old:最终打印的分析的字符串集合  【应该分文件处理】  new: 当前每个文件分析的字符串集合
    public static final ArrayList<String> analysisStringArr = new ArrayList<>();

    // 所有文件的 字符串 分析集合
    public static final ArrayList<ArrayList<String>> allFileAnalysisStringArr = new ArrayList<ArrayList<String>>();

    public static final ArrayList<AbstractKeyEntry> keyEntryList = new ArrayList<>();

    static {

        keyEntryList.add(new Shutting_down_VM_KeyEntry());   // Shutting down VM APP 奔溃
        keyEntryList.add(new IS_SCREEN_ON_TRUE_KeyEntry());  // Value:IS_SCREEN_ON:  true 亮屏
        keyEntryList.add(new IS_SCREEN_ON_FLASE_KeyEntry()); // Value:IS_SCREEN_ON: false 灭屏
        keyEntryList.add(new SetWifiEnabled_TRUE_KeyEntry()); // setWifiEnabled: true
        keyEntryList.add(new SetWifiEnabled_FALSE_KeyEntry()); // setWifiEnabled: false
        keyEntryList.add(new Updating_SSID_KeyEntry());  // WifiTetherSsidPref: Updating SSID  热点名称
        keyEntryList.add(new onStateChanged_state13_KeyEntry());
        keyEntryList.add(new onStateChanged_state11_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry());
        keyEntryList.add(new Launching_fragment_com_android_settings_wifi_WifiSettings());
        keyEntryList.add(new wlan0_CTRL_EVENT_CONNECTED());
        keyEntryList.add(new wlan0_CTRL_EVENT_DISCONNECTED());
        keyEntryList.add(new wlan0_Request_to_deauthenticate());
        keyEntryList.add(new WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent());
        keyEntryList.add(new Trying_to_associate_with_SSID());
        keyEntryList.add(new wlan0_Own_MAC_address());
        keyEntryList.add(new Set_Wifi_Switch_State_To_0());
        keyEntryList.add(new Set_Wifi_Switch_State_To_1());
        keyEntryList.add(new SemanticLocations_FeatureService_Wifi_SSID_info());
        keyEntryList.add(new HS20_ANQP_initiated_on());
        keyEntryList.add(new ContextImpl_Action_android_intent_action_REBOOT());
        keyEntryList.add(new passpoint_yes());
        keyEntryList.add(new WifiConfigManager_Adding_Updating_network());
        keyEntryList.add(new AP_STA_CONNECTED());
        keyEntryList.add(new AP_STA_DISCONNECTED());
        keyEntryList.add(new SND_NotifyWifiApStateChanged());
        keyEntryList.add(new Received_Deauth_Frame());
        keyEntryList.add(new NetworkController_WifiSignalController_To());
        keyEntryList.add(new Suppressing_Notification());
        keyEntryList.add(new DispatchingService_Ignoring_Intent());
        keyEntryList.add(new ShutdownThread_Rebooting());

/*        keyEntryList.add(new SimSelectNotification_Simstatus_ABSENT());
        keyEntryList.add(new SimSelectNotification_Simstatus_NOT_READY());
        keyEntryList.add(new SimSelectNotification_Simstatus_READY());
        keyEntryList.add(new SimSelectNotification_Simstatus_LOADED());
        keyEntryList.add(new SimSelectNotification_Simstatus_UNKNOWN());*/


        keyEntryList.add(new PhoneGlobals_ACTION_SIM_STATE_CHANGED_STATE());
        keyEntryList.add(new WifiTetherApBandPref_Updating_Band_Index());
        keyEntryList.add(new SoftApManager_Channel_Switched_Frequency());
        keyEntryList.add(new WifiVendorHal_Driver_Firmware());

        keyEntryList.add(new WifiService_Resetting_Networks_Because_SIM_Was_Removed());
        keyEntryList.add(new WifiService_Resetting_Networks_Because_SIM_Was_Loaded());
        keyEntryList.add(new IntentBroadcaster_Rebroadcast());
        keyEntryList.add(new IntentBroadcaster_Broadcasting_Intent());
        keyEntryList.add(new KeyguardViewMediator_handleNotifyScreenTurned());
        keyEntryList.add(new PasspointManager_Added_Updated_Passpoint_Configuration());
        keyEntryList.add(new KeyguardViewMediator_HandleNotifyStartedGoingToSleep());
        keyEntryList.add(new HDD_Hdd_send_association_event());
        keyEntryList.add(new Added_Drop_Box_Entry_IFI_CLASS_D_DIAGNOSTICS());
        keyEntryList.add(new Sap_Num_Connected_Sta());
        keyEntryList.add(new SoftApManager_Timeout_Message());
        keyEntryList.add(new Soft_AP_Is_Started());
        keyEntryList.add(new Soft_AP_Is_Stopped());
        keyEntryList.add(new BugReportService_onStartCommand());
        keyEntryList.add(new WifiCountryCode_CountryCode());
        keyEntryList.add(new COUNTRY_UPDATE_DISABLED());
        keyEntryList.add(new Updated_Country_Code());
        keyEntryList.add(new Hardware_Does_Dot_Support_Channel());

        keyEntryList.add(new WifiStateMachine_Leaving_Connected_State());



        // MeetPrintBase ---- Begin--------------------
        keyEntryList.add(new Found_Suppressed_Notifications_No_Longer_Available());
        keyEntryList.add(new RemoveNotification_For_Unknown_Key());


        keyEntryList.add(new Wifi_HAL_Start_Failed());
        keyEntryList.add(new Failed_To_Load_WiFi_Driver());
        keyEntryList.add(new IpReachabilityMonitor_FAILURE_LOST_PROVISIONING());
        keyEntryList.add(new Wlan0_CTRL_EVENT_ASSOC_REJECT());
        keyEntryList.add(new Wlan0_CTRL_EVENT_SSID_TEMP_DISABLED());
        keyEntryList.add(new Received_Assoc_Req());
        keyEntryList.add(new StatusLabel_No_Internet());
        keyEntryList.add(new OnStaConnected_Macaddr());
        keyEntryList.add(new Received_Heart_Beat_Failure());

        keyEntryList.add(new Kickout());
        keyEntryList.add(new AndroidRuntime_FATAL_EXCEPTION());

        keyEntryList.add(new AndroidRuntime_FATAL_EXCEPTION_Main());
        keyEntryList.add(new KeyMgmt_OWE_Protocols());

        keyEntryList.add(new AndroidRuntimeE());
        keyEntryList.add(new Watchdog_Warning());

        keyEntryList.add(new Validation_Failed());
        keyEntryList.add(new Validation_Passed());
        keyEntryList.add(new NETWORK_REVALIDATION_SUCCESS());
        keyEntryList.add(new NETWORK_VALIDATION_FAILED());
        keyEntryList.add(new Way_Handshake_Failed());
        keyEntryList.add(new SoftAP_Start_Failed());
        keyEntryList.add(new Hostapd_Invalid_SSID_Size());

        keyEntryList.add(new Wlan0_CTRL_EVENT_EAP_SUCCESS());
        keyEntryList.add(new Removing_All_Passpoint_Ephemeral_Configured_Networks());
        keyEntryList.add(new Removing_Passpoint_Network_Config());

        keyEntryList.add(new Set_Network_Candidate_Scan_Result());

        keyEntryList.add(new SetTargetBssid_Set_To());

        keyEntryList.add(new ActivityManager_ANR_In());

        keyEntryList.add(new Received_AUTH_Failure_Timeout());

        keyEntryList.add(new Nl80211_ACS_Results());

        keyEntryList.add(new Possibly_Channel_Configuration_Is_Invalid());

        keyEntryList.add(new Wlan0_ACS_COMPLETED());
        keyEntryList.add(new Received_Disassoc_Frame_For_Addr());

        keyEntryList.add(new Regulatory_Information_Country());


        keyEntryList.add(new Wlan0_DFS_CAC_COMPLETED());
        keyEntryList.add(new Wlan0_DFS_CAC_START());

        keyEntryList.add(new No_Internet_Access());

        keyEntryList.add(new Generate_204_Probe_Failed());


        keyEntryList.add(new StopTethering_Caller());

        keyEntryList.add(new Wlan_Hdd_Cfg80211_Stop_Ap());

        keyEntryList.add(new Process_Deauth_Req_On());
        keyEntryList.add(new Sme_Set_Etsi13_Srd_Ch_In_Master_Mode());

        keyEntryList.add(new No_ProbeRsp_From_AP());


        keyEntryList.add(new ACS_Possibly_Channel_Invalid());
        keyEntryList.add(new Hostapd_wlan0_AP_DISABLED());
        keyEntryList.add(new Hostapd_Interface_Initialization_Failed());
        keyEntryList.add(new Wlan0_ACS_FAILED());
        keyEntryList.add(new ACS_Picked_Unusable_Channels());
        keyEntryList.add(new Not_Allowed_For_AP_Mode());
		keyEntryList.add(new Updated_Max_Num_Sta());

        // 可注释
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131125());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131131());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131133());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131135());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131171());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131178());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131180());
        keyEntryList.add(new SendMessageSynchronously_Send_Message_131207());
        // MeetPrintBase ---- End--------------------



        //   DiffMeetPrintBase ---- Begin--------------------
        keyEntryList.add(new MD_NotificationData());

        //   DiffMeetPrintBase ---- End--------------------




        //   MultiKeyWordPrintBase ---- Begin-------------------- 一行包含了 独立 隔开的 多关键字
        keyEntryList.add(new Authentication_With_Time_Out());
        keyEntryList.add(new PROBE_DNS_Google());

        keyEntryList.add(new Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_ACS_Channel_List());
        keyEntryList.add(new Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL_Channel_Weight());
        keyEntryList.add(new Wlan_Hdd_Cfg80211_Do_Acs_ACS_Config_PCL());
        keyEntryList.add(new Wlansap_Pre_Start_Bss_Acs_Scan_Callback_Channel_Selected());

		
		        keyEntryList.add(new Lim_Send_Extended_Chan_Switch_Action_Frame_NewChannel());


       // 太多 可注释
       // keyEntryList.add(new Policy_Mgr_Pcl_Modification_For_Sap_Chan_Weight());
     //   keyEntryList.add(new Policy_Mgr_Pcl_Modification_For_Sap_Pcl_Len());

        keyEntryList.add(new Wpa_Supplicant_Nl80211_MBm()); // 可注释

        // injectWifiApInfo 和 ServingAccessPoint  Passpoint的 Log较多  可注释掉 可遇到对应问题手动打开
        //keyEntryList.add(new ServingAccessPoint1());
        //keyEntryList.add(new InjectWifiApInfo_Ssid());
        // keyEntryList.add(new PasspointManager_Matched());


        //   MultiKeyWordPrintBase ---- End--------------------

        //===================Verbose才能打印的Log============
        keyEntryList.add(new handleWifiApStateChange_currentState13_KeyEntry());  // 热点状态打开成功
        keyEntryList.add(new handleWifiApStateChange_currentState13_previousState12__KeyEntry());
        keyEntryList.add(new handleWifiApStateChange_currentState12_previousState11__KeyEntry());
    }

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



    // 05-14 21:09:55.091  7301  7301 D WifiTetherApBandPref: Updating band index to 1
    //05-14 21:09:55.091  7301  7301 D WifiTetherApBandPref: Updating band index to 0
    static class WifiTetherApBandPref_Updating_Band_Index extends FixLong_Diff_Pre_PrintBase {   // 模板

        WifiTetherApBandPref_Updating_Band_Index() {
            keyword = "WifiTetherApBandPref: Updating band index to";
            explain = "热点信道变更【0--2.4Ghz   1--5GHz】";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent.substring(lineContent.indexOf("WifiTetherApBandPref:"));
        }

        @Override
        public String toReturnFixString(String lineContent) {
            return lineContent;
        }


    }


    //05-06 22:35:55.514  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:READY reason:null phoneId:0
////05-06 22:35:55.544  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:READY reason:null phoneId:0
////05-06 22:35:55.597  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:ABSENT reason:null phoneId:1
////05-06 19:36:04.732  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:LOADED reason:null phoneId:0
////05-09 12:51:08.451  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:UNKNOWN reason:null phoneId:1
////05-09 12:51:08.473  6770  6770 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:UNKNOWN reason:null phoneId:0
////05-09 12:51:51.161  2748  2748 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:READY reason:null phoneId:0
////05-09 12:51:51.259  2748  2748 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:ABSENT reason:null phoneId:1
////05-09 12:51:52.885  2748  2748 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:LOADED reason:null phoneId:0
////05-09 12:51:54.466  2748  2748 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:LOADED reason:null phoneId:0
////05-09 12:51:54.501  2748  2748 D PhoneGlobals: ACTION_SIM_STATE_CHANGED state:ABSENT reason:null phoneId:1


    static class PhoneGlobals_ACTION_SIM_STATE_CHANGED_STATE extends MeetPrintBase {   // 模板
        PhoneGlobals_ACTION_SIM_STATE_CHANGED_STATE() {
            keyword = "PhoneGlobals: ACTION_SIM_STATE_CHANGED state";
            explain = "【ABSENT[SIM卡缺失],NOT_READY[SIM卡已加载未准备就绪],READY[准备就就绪],LOADED[加载完成],UNKNOWN[未知错误] phoneId[对应卡槽]】";
            codePath = null;
        }
    }

/*

    static class SimSelectNotification_Simstatus_ABSENT extends MeetPrintBase {   // 模板
        SimSelectNotification_Simstatus_ABSENT() {
            keyword = "SimSelectNotification: simstatus = ABSENT";
            explain = "SIM卡缺失";
            codePath = null;
        }
    }


    static class SimSelectNotification_Simstatus_NOT_READY extends MeetPrintBase {   // 模板
        SimSelectNotification_Simstatus_NOT_READY() {
            keyword = "SimSelectNotification: simstatus = NOT_READY";
            explain = "SIM卡已插入但还没有初始化完成";
            codePath = null;
        }
    }



    static class SimSelectNotification_Simstatus_READY extends MeetPrintBase {   // 模板
        SimSelectNotification_Simstatus_READY() {
            keyword = "SimSelectNotification: simstatus = READY";
            explain = "SIM卡已经准备完成";
            codePath = null;
        }
    }


    static class SimSelectNotification_Simstatus_LOADED extends MeetPrintBase {   // 模板
        SimSelectNotification_Simstatus_LOADED() {
            keyword = "SimSelectNotification: simstatus = LOADED";
            explain = "SIM卡已经可以完全正常使用";
            codePath = null;
        }
    }



    static class SimSelectNotification_Simstatus_UNKNOWN extends MeetPrintBase {   // 模板
        SimSelectNotification_Simstatus_UNKNOWN() {
            keyword = "SimSelectNotification: simstatus = UNKNOWN";
            explain = "SIM卡状态未知";
            codePath = null;
        }
    }
*/






    static class ShutdownThread_Rebooting extends MeetPrintBase {   // 模板
        ShutdownThread_Rebooting() {
            keyword = "ShutdownThread: Rebooting,";
            explain = "设备执行重启操作";
            codePath = null;
        }
    }




    static class DispatchingService_Ignoring_Intent extends MeetPrintBase {   // 模板
        DispatchingService_Ignoring_Intent() {
            keyword = "DispatchingService ignoring Intent";
            explain = "[ 丢弃 Intent ]";
            codePath = null;
        }
    }

    // Line 127089: 11-06 01:53:17.418  3256  3256 I Pixelmigrate: [NotificationConsolidatorService] Suppressing notification [[0|android|17303299|null|1000] progress=0, max=-1, text=[seu CESAR],
    static class Suppressing_Notification extends MeetPrintBase {   // 模板
        Suppressing_Notification() {
            keyword = "Suppressing notification";
            explain = "[ 丢弃 Notofication ]";
            codePath = null;
        }
    }

    // Line 123241: 11-06 01:53:08.283  3256  3256 I Pixelmigrate: [NotificationConsolidatorService] Found suppressed notifications no longer available = [0|android|17303299|null|1000]
    static class Found_Suppressed_Notifications_No_Longer_Available extends MeetPrintBase {   // 模板
        Found_Suppressed_Notifications_No_Longer_Available() {
            keyword = "Found suppressed notifications no longer available";
            explain = "[ Notofication已无效 ]";
            codePath = null;
        }
    }


    // Line 127107: 11-06 01:53:17.464  2385  2385 W NotificationEntryMgr: removeNotification for unknown key: 0|android|17303299|null|1000
    static class RemoveNotification_For_Unknown_Key extends MeetPrintBase {   // 模板
        RemoveNotification_For_Unknown_Key() {
            keyword = "removeNotification for unknown key";
            explain = "[ 移除 Notofication ]";
            codePath = null;
        }
    }

//12-13 11:21:12.545  1711  1791 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131125
//12-13 11:21:12.545  1711  2036 D WifiStateMachine:  ConnectedState !CMD_REMOVE_NETWORK uid=10101 rt=56365/56365 -1 0
//12-13 11:21:12.545  1711  2036 D WifiStateMachine:  L2ConnectedState !CMD_REMOVE_NETWORK uid=10101 rt=56366/56366 -1 0
//12-13 11:21:12.545  1711  2036 D WifiStateMachine:  ConnectModeState !CMD_REMOVE_NETWORK uid=10101 rt=56366/56366 -1 0
static class SendMessageSynchronously_Send_Message_131125 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131125() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131125";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_REMOVE_NETWORK  移除网络]";
        codePath = null;
    }
}



//12-25 11:33:23.811  1938  5897 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131131
//12-25 11:33:23.811  1938  2929 D WifiStateMachine:  ConnectedState !CMD_GET_CONFIGURED_NETWORKS uid=1000 rt=46163554/67773714 1000 0 num=3
//12-25 11:33:23.811  1938  2929 D WifiStateMachine:  L2ConnectedState !CMD_GET_CONFIGURED_NETWORKS uid=1000 rt=46163554/67773714 1000 0 num=3
//12-25 11:33:23.811  1938  2929 D WifiStateMachine:  ConnectModeState !CMD_GET_CONFIGURED_NETWORKS uid=1000 rt=46163554/67773714 1000 0 num=3
//12-25 11:33:23.811  1938  2929 D WifiStateMachine:  DefaultState !CMD_GET_CONFIGURED_NETWORKS uid=1000 rt=46163554/67773714 1000 0 num=3
static class SendMessageSynchronously_Send_Message_131131 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131131() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131131";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_GET_CONFIGURED_NETWORKS ]";
        codePath = null;
    }
}



 // WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131133
//12-25 11:33:21.441  1938  2929 D WifiStateMachine:  ConnectedState !CMD_GET_SUPPORTED_FEATURES uid=1000 rt=46161184/67771344 0 0
//12-25 11:33:21.441  1938  2929 D WifiStateMachine:  L2ConnectedState !CMD_GET_SUPPORTED_FEATURES uid=1000 rt=46161184/67771344 0 0
//12-25 11:33:21.441  1938  2929 D WifiStateMachine:  ConnectModeState !CMD_GET_SUPPORTED_FEATURES uid=1000 rt=46161184/67771344 0 0
//12-25 11:33:21.441  1938  2929 D WifiStateMachine:  DefaultState !CMD_GET_SUPPORTED_FEATURES uid=1000 rt=46161184/67771344 0 0
    static class SendMessageSynchronously_Send_Message_131133 extends MeetPrintBase {   // 模板
        SendMessageSynchronously_Send_Message_131133() {
            keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131133";
            explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_GET_SUPPORTED_FEATURES ]";
            codePath = null;
        }
    }

//12-25 11:33:21.469  1938  2009 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131135
//12-25 11:33:21.469  1938  2929 D WifiStateMachine:  ConnectedState !CMD_GET_LINK_LAYER_STATS uid=1000 rt=46161213/67771372 0 0
//12-25 11:33:21.470  1938  2929 D WifiStateMachine:  L2ConnectedState !CMD_GET_LINK_LAYER_STATS uid=1000 rt=46161213/67771373 0 0
//12-25 11:33:21.470  1938  2929 D WifiStateMachine:  ConnectModeState !CMD_GET_LINK_LAYER_STATS uid=1000 rt=46161213/67771373 0 0
static class SendMessageSynchronously_Send_Message_131135 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131135() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131135";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_GET_LINK_LAYER_STATS ]";
        codePath = null;
    }
}



//04-08 12:55:22.452  2513  5391 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131171
//04-08 12:55:22.453  2513  4657 D WifiStateMachine:  ConnectedState !CMD_GET_MATCHING_CONFIG uid=1000 rt=364813/364813 0 0
//04-08 12:55:22.453  2513  4657 D WifiStateMachine:  L2ConnectedState !CMD_GET_MATCHING_CONFIG uid=1000 rt=364813/364813 0 0
//04-08 12:55:22.453  2513  4657 D WifiStateMachine:  ConnectModeState !CMD_GET_MATCHING_CONFIG uid=1000 rt=364813/364813 0 0
static class SendMessageSynchronously_Send_Message_131171 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131171() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131171";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_GET_MATCHING_CONFIG ]";
        codePath = null;
    }
}

//05-14 00:40:35.636  1258  6827 I WifiService: addorUpdatePasspointConfiguration uid=10066
//05-14 00:40:35.637  1258  6827 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131178
//05-14 00:40:35.637  1258  6440 D WifiStateMachine:  DisconnectedState !CMD_ADD_OR_UPDATE_PASSPOINT_CONFIG uid=10066 rt=573348/573348 10066 0
//05-14 00:40:35.637  1258  6440 D WifiStateMachine:  ConnectModeState !CMD_ADD_OR_UPDATE_PASSPOINT_CONFIG uid=10066 rt=573349/573349 10066 0
static class SendMessageSynchronously_Send_Message_131178 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131178() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131178";
        explain = "[ WifiSateMechine状态栈 处理事件命令 更新passpoint配置 CMD_ADD_OR_UPDATE_PASSPOINT_CONFIG ]";
        codePath = null;
    }
}






//12-25 11:33:23.859  1938  4360 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131180
//12-25 11:33:23.859  1938  2929 D WifiStateMachine:  ConnectedState !CMD_GET_PASSPOINT_CONFIGS uid=1000 rt=46163602/67773762 0 0
//12-25 11:33:23.861  1938  2929 D WifiStateMachine:  L2ConnectedState !CMD_GET_PASSPOINT_CONFIGS uid=1000 rt=46163604/67773764 0 0
//12-25 11:33:23.861  1938  2929 D WifiStateMachine:  ConnectModeState !CMD_GET_PASSPOINT_CONFIGS uid=1000 rt=46163604/67773764 0 0
//12-25 11:33:23.861  1938  2929 D WifiStateMachine:  DefaultState !CMD_GET_PASSPOINT_CONFIGS uid=1000 rt=46163604/67773764 0 0
static class SendMessageSynchronously_Send_Message_131180 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131180() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131180";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_GET_PASSPOINT_CONFIGS ]";
        codePath = null;
    }
}


//03-13 00:51:07.203  1449  1491 E ActivityManager: ANR in com.android.settings
//03-13 00:51:07.203  1449  1491 E ActivityManager: PID: 29037
//03-13 00:51:07.203  1449  1491 E ActivityManager: Reason: Broadcast of Intent { act=android.intent.action.SCREEN_OFF flg=0x50200010 }
//03-13 00:51:07.203  1449  1491 E ActivityManager: Load: 0.38 / 0.16 / 0.23
//03-13 00:51:07.203  1449  1491 E ActivityManager: CPU usage from 0ms to 7962ms later (2019-03-13 00:50:59.163 to 2019-03-13 00:51:07.125):
//03-13 00:51:07.203  1449  1491 E ActivityManager: 19% TOTAL: 8.9% user + 8% kernel + 1.3% iowait + 0.8% irq + 0.5% softirq
static class ActivityManager_ANR_In extends MeetPrintBase {   // 模板
    ActivityManager_ANR_In() {
        keyword = "ActivityManager: ANR in";
        explain = "【 发生ARN 程序无响应！ 】";
        codePath = null;
    }
}





//12-13 11:20:34.795  1711  1711 D WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131207
//12-13 11:20:34.796  1711  2036 D WifiStateMachine:  DefaultState !CMD_INITIALIZE uid=1000 rt=19253/19253 0 0
static class SendMessageSynchronously_Send_Message_131207 extends MeetPrintBase {   // 模板
    SendMessageSynchronously_Send_Message_131207() {
        keyword = "WifiAsyncChannel.WifiService: sendMessageSynchronously sendMessageSynchronously.send message=131207";
        explain = "[ WifiSateMechine状态栈 处理事件命令 CMD_INITIALIZE  初始化 ]";
        codePath = null;
    }
}

    static class NetworkController_WifiSignalController_To extends FixLong_Diff_Pre_PrintBase {

//        05-02 04:19:17.045  2478  2636 D NetworkController.WifiSignalController:
//        to: connected=false,enabled=true,level=4,inetCondition=1,iconGroup=IconGroup(Wi-Fi Icons),
//        activityIn=true,activityOut=true,rssi=-34,lastModified=05-02 03:49:00,ssid=null,
//        isTransient=false,statusLabel=null,epdgState=false

        // 05-09 12:51:49.543  2517  2698 D NetworkController.WifiSignalController: 	to: Empty WifiState
        NetworkController_WifiSignalController_To() {
            keyword = "NetworkController.WifiSignalController: 	to:";
            explain = "connected[是否连接wifi],enabled=[wifi是否打开],level=[信号格数],inetCondition=[0当前网络无联网,1正常],ssid=[热点名]";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String item) {
            if(item== null || item.isEmpty()){
                return null;
            }
            String lineContent = new String(item);
            if(lineContent.contains("connected=") && lineContent.contains(",iconGroup")  &&
                    lineContent.contains(",ssid=")   && lineContent.contains(",isTransient") ){
                String str1 = lineContent.substring(lineContent.indexOf("connected="),lineContent.indexOf(",iconGroup"));
                String str2 = lineContent.substring(lineContent.indexOf(",ssid="),lineContent.indexOf(",isTransient"));
                return str1+str2;
            }
            return null;
        }

        public String toReturnFixString(String item) {
            if(item== null || item.isEmpty()){
                return null;
            }
            String lineContent = new String(item);

            if(lineContent.contains("connected=") && lineContent.contains(",iconGroup")  &&
                    lineContent.contains(",ssid=")   && lineContent.contains(",isTransient")  &&
                    lineContent.contains(",rssi=")){
                String str0 = lineContent.substring(0,lineContent.indexOf("to:"));
                str0 = str0.replaceAll("NetworkController.","");
                str0 = str0 + "to【";
                String str1 = lineContent.substring(lineContent.indexOf("connected="),lineContent.indexOf(",iconGroup"));
                String str2 = lineContent.substring(lineContent.indexOf(",ssid="),lineContent.indexOf(",isTransient"));
                String str3 = lineContent.substring(lineContent.indexOf(",rssi="),lineContent.indexOf(",ssid="));
                return str0+str1+str2+str3;


            }
            return null;

        }
    }




    static class Received_Deauth_Frame extends MeetPrintBase {   // 模板
        Received_Deauth_Frame() {
            keyword = "received Deauth frame";
            explain = "设备接收到来自热点的 断开 Deauth帧 ";
            codePath = null;
        }
    }



    static class AP_STA_CONNECTED extends MeetPrintBase {   // 模板
        AP_STA_CONNECTED() {
            keyword = "wlan0: AP-STA-CONNECTED";
            explain = "设备连接到当前手机热点";
            codePath = null;
        }
    }

    static class AP_STA_DISCONNECTED extends MeetPrintBase {   // 模板
        AP_STA_DISCONNECTED() {
            keyword = "wlan0: AP-STA-DISCONNECTED";
            explain = "设备离开到当前手机热点";
            codePath = null;
        }
    }




    static class WifiConfigManager_Adding_Updating_network extends MeetPrintBase {
        WifiConfigManager_Adding_Updating_network() {
            keyword = "WifiConfigManager: Adding/Updating network";
            explain = "更新添加网络";
            codePath = null;
        }
    }




    static class HS20_ANQP_initiated_on extends MeetPrintBase {
        HS20_ANQP_initiated_on() {
            keyword = "ANQP initiated on";
            explain = "向该SSID热点进行ANQP 网络查询 ";
            codePath = null;
        }
    }


    static class ContextImpl_Action_android_intent_action_REBOOT extends MeetPrintBase {
        ContextImpl_Action_android_intent_action_REBOOT() {
            keyword = "\"android.intent.action.REBOOT\"";
            explain = "设备重启完成";
            codePath = null;
        }
    }



    static class SemanticLocations_FeatureService_Wifi_SSID_info extends CollectManyFromOneEndPrintBase {   // 当前SSID的集合
        SemanticLocations_FeatureService_Wifi_SSID_info() {
            keyword = "SemanticLocations-FeatureService: Wifi SSID info: CONNECTED_SSID:";
            explain = "";
            codePath = null;
        }

        @Override
        public ArrayList<String> getManyInfoFromOneLineMethod_Imple(String lineContent) {
            ArrayList<String> ssid_bssidArr = new ArrayList<String>();
            // keyword = "SemanticLocations-FeatureService: Wifi SSID info: CONNECTED_SSID:";
            // String key ="SemanticLocations-FeatureService: Wifi SSID info: CONNECTED_SSID:";
            if (lineContent.contains(keyword)) {
                String[] ssidArr = lineContent.split("/SSID:");
                int ssidLength = ssidArr.length;
                int ssidMaxLength = getMaxSSIDLength(ssidArr);
                for (int i = 1; i < ssidLength; i++) {
                    String ssid_bssid_item = ssidArr[i];
                    if (ssid_bssid_item.trim().startsWith(",")) { // 说明当前项的SSID为空 那么 不操作
                        continue;
                    }

                    if (ssid_bssid_item.contains(",SIGNAL_STRENGTH")) { // 如果包含这句话 那么截取 这句话之前的内容
                        String result = ssid_bssid_item.substring(0, ssid_bssid_item.indexOf(",SIGNAL_STRENGTH"));
                        if (result != null) {
                            result = "SSID:【" + getSpace(result, ssidMaxLength) + result;
                            if (result.contains(",")) {
                                result = result.replaceAll(",", " 】");
                            }
                            if (result.contains("BSSID:")) {
                                result = result.replaceAll("BSSID:", "BSSID:【 ");
                                result = result + " 】";
                            }
                            ssid_bssidArr.add(result);
                        }
                        System.out.println("================ index :" + i + " result=" + result);
                    }
                }

            }
            return ssid_bssidArr;
        }
    }


    static class Set_Wifi_Switch_State_To_1 extends MeetPrintBase {
        Set_Wifi_Switch_State_To_1() {
            keyword = "set wifi switch state to 1";
            explain = "WIFI_button打开";
            codePath = null;
        }
    }

    static class Set_Wifi_Switch_State_To_0 extends MeetPrintBase {
        Set_Wifi_Switch_State_To_0() {
            keyword = "set wifi switch state to 0";
            explain = "WIFI_button关闭";
            codePath = null;
        }
    }


    static class wlan0_Own_MAC_address extends DiffMeetPrintBase {
        wlan0_Own_MAC_address() {
            keyword = "wpa_supplicant: wlan0: Own MAC address:";
            explain = "设备 Mac地址 ";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent.substring(lineContent.indexOf("wpa_supplicant: wlan0: Own MAC address:"), lineContent.length());
        }
    }



    // Line 54472: 01-21 08:35:48.282  7213  7213 D MD.NotificationData:  mText.length: 9 mTitle: Connect to open Wi‑Fi network mSubText.length: null mDisplayName:  mMsgList.size: 0 mVisibility: 0 mPriority: 0 mTime: null mCategory: null
    //Line 60605: 01-21 17:35:57.282  7213  7213 D MD.NotificationData:  mText.length: 16 mTitle: Connect to open Wi‑Fi network mSubText.length: null mDisplayName:  mMsgList.size: 0 mVisibility: 0 mPriority: 0 mTime: null mCategory: null
    static class MD_NotificationData extends DiffMeetPrintBase {
        MD_NotificationData() {
            keyword = "MD.NotificationData:";
            explain = "【提示通知-通知notification数据】 ";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent.substring(lineContent.indexOf("MD.NotificationData:"), lineContent.length());
        }
    }

    // 01-13 17:10:08.048  1906  2662 D WifiStateMachine: WifiStateMachine: Leaving Connected state
    static class WifiStateMachine_Leaving_Connected_State extends MeetPrintBase {
        WifiStateMachine_Leaving_Connected_State() {
            keyword = "WifiStateMachine: Leaving Connected state";
            explain = "【 WIFI状态机 离开连接状态】";
            codePath = null;
        }
    }

    // Line 302079: 01-17 19:03:58.203 16833 16833 I wpa_supplicant: wlan0: CTRL-EVENT-SSID-TEMP-DISABLED id=0 ssid="kangzh" auth_failures=1 duration=10 reason=CONN_FAILED
    static class Wlan0_CTRL_EVENT_SSID_TEMP_DISABLED extends MeetPrintBase {
        Wlan0_CTRL_EVENT_SSID_TEMP_DISABLED() {
            keyword = "wlan0: CTRL-EVENT-SSID-TEMP-DISABLED";
            explain = "【 暂时屏蔽热点事件 】";
            codePath = null;
        }
    }

// lim_tear_down_link_with_ap: 377: No ProbeRsp from AP after HB failure. Tearing down link
static class No_ProbeRsp_From_AP extends MeetPrintBase {
    No_ProbeRsp_From_AP() {
        keyword = "No ProbeRsp from AP";
        explain = "【 没有从热点AP得到 Probe回馈帧   】";
        codePath = null;
    }
}

    // scheduler_threa][0x2a5cadb694][10:04:14.875409] wlan: [2264:E:PE] lim_ps_offload_handle_missed_beacon_ind: 2006: Received Heart Beat Failure
    static class Received_Heart_Beat_Failure extends MeetPrintBase {
        Received_Heart_Beat_Failure() {
            keyword = "Received Heart Beat Failure";
            explain = "【 手机检测心跳帧Probe失败  】";
            codePath = null;
        }
    }



    //  E AndroidRuntime: java.lang.IllegalArgumentException:

    static class AndroidRuntimeE extends MeetPrintBase {
        AndroidRuntimeE() {
            keyword = "E AndroidRuntime: ";
            explain = "【 安卓异常记录 栈调用情况  】";
            codePath = null;
        }
    }


//    Line 17346: 02-27 19:18:20.733  1475  6108 W Watchdog: *** WATCHDOG KILLING SYSTEM PROCESS: Blocked in handler on main thread (main)
//    Line 17349: 02-27 19:18:20.740  1475  6108 W Watchdog: main annotated stack trace:
//    Line 17350: 02-27 19:18:20.740  1475  6108 W Watchdog:     at com.android.server.wifi.WifiCountryCode.getCountryCodeSentToDriver(Unknown Source:0)
//    Line 17351: 02-27 19:18:20.741  1475  6108 W Watchdog:     - waiting to lock <0x080d0ba0> (a com.android.server.wifi.WifiCountryCode)
//    Line 17352: 02-27 19:18:20.741  1475  6108 W Watchdog:     at com.android.server.wifi.WifiCountryMonitorFcc$1.onCountryDetected(WifiCountryMonitorFcc.java:41)
//    Line 17353: 02-27 19:18:20.741  1475  6108 W Watchdog:     at android.location.CountryDetector$ListenerTransport$1.run(CountryDetector.java:74)
//    Line 17354: 02-27 19:18:20.741  1475  6108 W Watchdog:     at android.os.Handler.handleCallback(Handler.java:873)
//    Line 17355: 02-27 19:18:20.741  1475  6108 W Watchdog:     at android.os.Handler.dispatchMessage(Handler.java:99)
//    Line 17356: 02-27 19:18:20.741  1475  6108 W Watchdog:     at android.os.Looper.loop(Looper.java:215)
//    Line 17357: 02-27 19:18:20.741  1475  6108 W Watchdog:     at com.android.server.SystemServer.run(SystemServer.java:476)
//    Line 17358: 02-27 19:18:20.741  1475  6108 W Watchdog:     at com.android.server.SystemServer.main(SystemServer.java:305)
//    Line 17359: 02-27 19:18:20.741  1475  6108 W Watchdog:     at java.lang.reflect.Method.invoke(Native Method)
//    Line 17360: 02-27 19:18:20.741  1475  6108 W Watchdog:     at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
//    Line 17361: 02-27 19:18:20.741  1475  6108 W Watchdog:     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:850)
//    Line 17362: 02-27 19:18:20.742  1475  6108 W Watchdog: *** GOODBYE!
static class Watchdog_Warning extends MeetPrintBase {
    Watchdog_Warning() {
        keyword = "W Watchdog: ";
        explain = "【 死锁发生  】";
        codePath = null;
    }
}



// 03-11 12:23:55.547 12593 12593 I wpa_supplicant: wlan0: CTRL-EVENT-EAP-SUCCESS eap_proxy: EAP authentication completed successfully
static class Wlan0_CTRL_EVENT_EAP_SUCCESS extends MeetPrintBase {
    Wlan0_CTRL_EVENT_EAP_SUCCESS() {
        keyword = "wlan0: CTRL-EVENT-EAP-SUCCESS";
        explain = "【 EAP 认证成功 】";
        codePath = null;
    }
}



// 23515      23515      23515      03-07 08:46:47.612  wifi 17657 17657 E hostapd : Invalid SSID size: 36
static class Hostapd_Invalid_SSID_Size extends MeetPrintBase {
    Hostapd_Invalid_SSID_Size() {
        keyword = "hostapd : Invalid SSID size";
        explain = "【 热点名称长度过长导致打开热点失败 】";
        codePath = null;
    }
}



    // 03-02 13:38:45.104  1414  1414 E WifiController: SoftAP start failed
static class SoftAP_Start_Failed extends MeetPrintBase {
    SoftAP_Start_Failed() {
        keyword = "SoftAP start failed";
        explain = "【 热点开启失败 】";
        codePath = null;
    }
}




    // 02-28 17:33:07.654 19250 19250 I wpa_supplicant: wlan0: WPA: 4-Way Handshake failed - pre-shared key may be incorrect
static class Way_Handshake_Failed extends MeetPrintBase {
    Way_Handshake_Failed() {
        keyword = "4-Way Handshake failed";
        explain = "【 四次握手失败 可能是错误密码  】";
        codePath = null;
    }
}





    // ConnectivityMetricsEvent(16:35:39.397, netId=100, WIFI): NetworkEvent(NETWORK_VALIDATION_FAILED, 0ms)
static class NETWORK_VALIDATION_FAILED extends MeetPrintBase {
    NETWORK_VALIDATION_FAILED() {
        keyword = "NetworkEvent(NETWORK_VALIDATION_FAILED ";
        explain = "【 认证失败  】";
        codePath = null;
    }
}


// ConnectivityMetricsEvent(16:13:16.057, netId=100, WIFI): NetworkEvent(NETWORK_REVALIDATION_SUCCESS, 149ms)
static class NETWORK_REVALIDATION_SUCCESS extends MeetPrintBase {
    NETWORK_REVALIDATION_SUCCESS() {
        keyword = "NetworkEvent(NETWORK_REVALIDATION_SUCCESS";
        explain = "【 认证成功  】";
        codePath = null;
    }
}


// 02-28 16:35:39.398  1000  1400  1936 D ConnectivityService: NetworkAgentInfo [WIFI () - 100] validation failed

    static class Validation_Failed extends MeetPrintBase {
        Validation_Failed() {
            keyword = "validation failed ";
            explain = "【 认证失败  】";
            codePath = null;
        }
    }

// 02-28 16:38:38.262  1000  1400  1936 D ConnectivityService: NetworkAgentInfo [WIFI () - 103] validation passed
static class Validation_Passed extends MeetPrintBase {
    Validation_Passed() {
        keyword = "validation passed ";
        explain = "【 认证成功  】";
        codePath = null;
    }
}




    // 02-27 08:33:05.597 13457 13457 E AndroidRuntime: FATAL EXCEPTION: main
static class AndroidRuntime_FATAL_EXCEPTION_Main extends MeetPrintBase {
    AndroidRuntime_FATAL_EXCEPTION_Main() {
        keyword = "AndroidRuntime: FATAL EXCEPTION: main";
        explain = "【 安卓致命运行时异常 奔溃  】";
        codePath = null;
    }
}





 //   02-18 16:42:48.670  1000  1482  6330 E AndroidRuntime: *** FATAL EXCEPTION IN SYSTEM PROCESS: WifiStateMachine
 static class AndroidRuntime_FATAL_EXCEPTION extends MeetPrintBase {
     AndroidRuntime_FATAL_EXCEPTION() {
         keyword = "AndroidRuntime: *** FATAL EXCEPTION IN SYSTEM PROCESS";
         explain = "【 安卓致命运行时异常 奔溃  】";
         codePath = null;
     }
 }


 // 02-18 16:42:48.519  1482  6329 D WifiService:  KeyMgmt: OWE Protocols:
 static class KeyMgmt_OWE_Protocols extends MeetPrintBase {
     KeyMgmt_OWE_Protocols() {
         keyword = "KeyMgmt: OWE Protocols";
         explain = "【  802.11ax 的 WPA3 的认证方式 Protocols  】";
         codePath = null;
     }
 }


// 23:41:18.380301  R0: FWMSG: [50a47dc97] pdev=0,vdev=0,peer=0xb038e268,tid=0xb03aeb88 (flags=0x100085),consecutive failure=1,kickout thresh=512   太多
    // 02-06 06:03:37.561     0     0 I [scheduler_threa][0x11c7983fd21][06:03:37.567624] wlan: [2021:F:WMA] wma_peer_sta_kickout_event_handler: PEER:[f8:59:71:68:fc:09], ADDR:[0xfffffff4ee7c8000], INTERFACE:0, peer_id:2, reason:0
    static class Kickout extends MeetPrintBase {
        Kickout() {
            keyword = "wma_peer_sta_kickout_event_handler";
            explain = "【 被AP-kickout 踢出 悲剧  】";
            codePath = null;
        }
    }



//int配置项 2. etsi13_srd_chan_in_master_mode
//etsi13_srd_chan_in_master_mode=0    关闭在低功耗channel -14dm , 对于该channel的使用
//etsi13_srd_chan_in_master_mode=1    使得设备可以使用低功耗channel -14dm
//22:29:26.500544  [kworker/u16:4][0x258a02e2][21:29:26.487647]wlan: [245:D:SME] sme_set_etsi13_srd_ch_in_master_mode: 9080: srd_ch_support 0
static class Sme_Set_Etsi13_Srd_Ch_In_Master_Mode extends MultiKeyWordPrintBase {

    Sme_Set_Etsi13_Srd_Ch_In_Master_Mode() {
        keyword = "sme_set_etsi13_srd_ch_in_master_mode";
        explain = "[ ini配置 srd_ch_support=0 关闭在低功耗5G channel -14dm ,  srd_ch_support=1 开启在低功耗5G channel]";
        codePath = null;
    }

    @Override
    public ArrayList<String> getKeyWordArrayList() {
        ArrayList<String> keyList = new  ArrayList<String>();
        keyList.add("sme_set_etsi13_srd_ch_in_master_mode");
        keyList.add("srd_ch_support");
        return keyList;
    }
}



    //05:55:53.371634  [scheduler_threa][0x9e8b93162f][05:55:53.367410]wlan: [1923:D:PE] lim_process_mlm_deauth_req: 1921: Process Deauth Req on sessionID 0 from: 14:ab:c5:54:eb:06
//05:55:53.371654  [scheduler_threa][0x9e8b9317ec][05:55:53.367433]wlan: [1923:D:PE] lim_process_mlm_deauth_req_ntf: 1675: Process Deauth Req on sessionID 0 Systemrole 1mlmstate 6 from: 14:ab:c5:54:eb:06
    static class Process_Deauth_Req_On extends MeetPrintBase {
        Process_Deauth_Req_On() {
            keyword = "Process Deauth Req on";
            explain = "【 执行解认证请求 断开请求  】";
            codePath = null;
        }
    }



    // 05:55:53.368816  [kworker/1:4][0x9e8b92b75e][05:55:53.366147]wlan: [11002:D:HDD] __wlan_hdd_cfg80211_stop_ap: 8629: enter
static class Wlan_Hdd_Cfg80211_Stop_Ap extends MeetPrintBase {
    Wlan_Hdd_Cfg80211_Stop_Ap() {
        keyword = "__wlan_hdd_cfg80211_stop_ap";
        explain = "【 热点关闭  】";
        codePath = null;
    }
}




    // 	Line 547279: 01-29 13:15:56.265  1078  1886 D WifiService: onStaConnected Macaddr:  with num of active client:0
    //	Line 558111: 01-29 13:25:12.632  1078  1886 D WifiService: onStaConnected Macaddr: 38:80:df:b2:4b:3e with num of active client:1
    static class OnStaConnected_Macaddr extends MeetPrintBase {
        OnStaConnected_Macaddr() {
            keyword = "onStaConnected Macaddr:";
            explain = "【 手机作为热点的连接情况 】";
            codePath = null;
        }
    }




    //01-28 13:46:09.695  4746  4746 D NetworkController.WifiSignalController: 	to: connected=true,enabled=true,level=4,inetCondition=0,iconGroup=IconGroup(Wi-Fi Icons),activityIn=true,activityOut=true,rssi=-25,lastModified=01-28 13:46:08,ssid="moto g(7) play 4086",isTransient=false,statusLabel=No internet,epdgState=false
    static class StatusLabel_No_Internet extends MeetPrintBase {
        StatusLabel_No_Internet() {
            keyword = "statusLabel=No internet";
            explain = "【 手机连接的WIFI无网络能力  显示wifi感叹号】";
            codePath = null;
        }
    }



    // 18:46:47.106132  [18:46:47.102093] [0000000D3E49F5F2] [VosMC] wlan: [E :PE ] limProcessAssocReqFrame: 1088: Received Assoc Req  successful from e4:70:b8:51:7a:f7
    static class Received_Assoc_Req extends MeetPrintBase {
        Received_Assoc_Req() {
            keyword = "Received Assoc Req";
            explain = "【 手机接收到关联请求( 手机作为热点AP ) 】";
            codePath = null;
        }
    }


    // wpa_supplicant: wlan0: CTRL-EVENT-ASSOC-REJECT bssid=00:23:cd:1b:2a:e8 status_code=1 timeout
    static class Wlan0_CTRL_EVENT_ASSOC_REJECT extends MeetPrintBase {
        Wlan0_CTRL_EVENT_ASSOC_REJECT() {
            keyword = "wlan0: CTRL-EVENT-ASSOC-REJECT";
            explain = "【 热点拒绝关联事件发生 】";
            codePath = null;
        }
    }

// 06-25 14:03:50.431 11986 11986 I ConnectivityManager: stopTethering caller:com.android.settings
static class StopTethering_Caller extends MeetPrintBase {
    StopTethering_Caller() {
        keyword = "stopTethering caller:";
        explain = "【 关闭热点  由该应用关闭 】";
        codePath = null;
    }
}



    // Line 394671: 01-13 17:12:14.121  1906 12743 W IpReachabilityMonitor: FAILURE: LOST_PROVISIONING, NeighborEvent{@349174847,RTM_NEWNEIGH,if=75,192.168.100.1,NUD_FAILED,[null]}
    static class IpReachabilityMonitor_FAILURE_LOST_PROVISIONING extends MeetPrintBase {
        IpReachabilityMonitor_FAILURE_LOST_PROVISIONING() {
            keyword = "IpReachabilityMonitor: FAILURE: LOST_PROVISIONING, NeighborEvent";
            explain = "IPR 丢失邻居事件发生(可能导致断线)";
            codePath = null;
        }
    }



    static class WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent extends MeetPrintBase {
        WifiStateMachine_FAILURE_LOST_PROVISIONING_NeighborEvent() {
            keyword = "WifiStateMachine: FAILURE: LOST_PROVISIONING, NeighborEvent";
            explain = "IPR 丢失邻居事件发生(可能导致断线)";
            codePath = null;
        }
    }

    static class wlan0_Request_to_deauthenticate extends MeetPrintBase {
        wlan0_Request_to_deauthenticate() {
            keyword = "wlan0: Request to deauthenticate";
            explain = "设备 主动发送 断开帧 断开当前网络！ ";
            codePath = null;
        }
    }

    // limProcessAuthFailureTimeout: 4569: received AUTH failure timeout in
    static class Received_AUTH_Failure_Timeout extends MeetPrintBase {
        Received_AUTH_Failure_Timeout() {
            keyword = "received AUTH failure timeout";
            explain = " 手机认证超时 不能连接网络！ ";
            codePath = null;
        }
    }




    static class wlan0_CTRL_EVENT_DISCONNECTED extends MeetPrintBase {
        wlan0_CTRL_EVENT_DISCONNECTED() {
            keyword = "wlan0: CTRL-EVENT-DISCONNECTED";
            explain = "WIFI 断开事件发生 ";
            codePath = "【 断开原因列表 https://sse.am.mot.com/p_source/xref/mp-r-sh2019/vendor/qcom/opensource/wlan/qcacld-3.0/core/mac/inc/sir_mac_prot_def.h#745  】";
        }
    }


    static class wlan0_CTRL_EVENT_CONNECTED extends MeetPrintBase {
        wlan0_CTRL_EVENT_CONNECTED() {
            keyword = "wlan0: CTRL-EVENT-CONNECTED";
            explain = "WIFI 连接成功事件发生 ";
            codePath = null;
        }
    }


    static class Launching_fragment_com_android_settings_wifi_WifiSettings extends MeetPrintBase {
        Launching_fragment_com_android_settings_wifi_WifiSettings() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.WifiSettings";
            explain = "用户进入WIFI设置界面";
            codePath = null;
        }
    }

    static class Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry extends MeetPrintBase {
        Launching_fragment_com_android_settings_wifi_tether_WifiTetherSettings_KeyEntry() {
            keyword = "SubSettings: Launching fragment com.android.settings.wifi.tether.WifiTetherSettings";
            explain = "用户进入热点设置界面";
            codePath = null;
        }
    }


    static class onStateChanged_state11_KeyEntry extends MeetPrintBase {
        onStateChanged_state11_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=11";
            explain = "关闭热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }
    }


    //  WIFI打开时 打开 热点
//05-05 14:32:41.563  4795  4888 I QCNEJ   : |QCNEJ/CndHalConnector| -> SND notifyWifiApStateChanged(12, 11)
//05-05 14:32:43.588  4795  4888 I QCNEJ   : |QCNEJ/CndHalConnector| -> SND notifyWifiApStateChanged(13, 12)

// 关闭热点
//05-05 14:34:17.194  4795  4888 I QCNEJ   : |QCNEJ/CndHalConnector| -> SND notifyWifiApStateChanged(10, 13)
//05-05 14:34:18.722  4795  4888 I QCNEJ   : |QCNEJ/CndHalConnector| -> SND notifyWifiApStateChanged(11, 10)

    static class SND_NotifyWifiApStateChanged extends MeetPrintBase {
        SND_NotifyWifiApStateChanged() {
            keyword = "SND notifyWifiApStateChange";
            explain = "热点状态变化 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-sh2019/frameworks/base/wifi/java/android/net/wifi/WifiManager.java#469";
        }
    }


    static class onStateChanged_state13_KeyEntry extends MeetPrintBase {
        onStateChanged_state13_KeyEntry() {
            keyword = "SoftApCallbackProxy: onStateChanged: state=13";
            explain = "打开热点成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = "https://sse.am.mot.com/p_source/xref/mp-r-sh2019/frameworks/base/wifi/java/android/net/wifi/WifiManager.java#469";
        }
    }


    static class Updating_SSID_KeyEntry extends DiffMeetPrintBase {
        Updating_SSID_KeyEntry() {
            keyword = "WifiTetherSsidPref: Updating SSID";
            explain = "热点名称";
            codePath = null;
        }

        @Override
        public String toGetDiffSubString(String lineContent) {
            return lineContent.substring(lineContent.indexOf("WifiTetherSsidPref: Updating SSID"), lineContent.length());
        }
    }

// 12-28 10:58:41.700  wifi   984   984 E android.hardware.wifi@1.0-service: Failed to load WiFi driver
static class Failed_To_Load_WiFi_Driver extends MeetPrintBase {
    Failed_To_Load_WiFi_Driver() {
        keyword = "Failed to load WiFi driver";
        explain = "【 加载 WIFI 驱动失败 】";
        codePath = null;
    }
}


// 12-28 10:58:41.700  wifi   984   984 E android.hardware.wifi@1.0-service: Wifi HAL start failed
static class Wifi_HAL_Start_Failed extends MeetPrintBase {
    Wifi_HAL_Start_Failed() {
        keyword = "Wifi HAL start failed";
        explain = "【 Wifi HAL 启动失败 】";
        codePath = null;
    }
}




    static class SetWifiEnabled_TRUE_KeyEntry extends MeetPrintBase {
        SetWifiEnabled_TRUE_KeyEntry() {
            keyword = "setWifiEnabled: true";
            explain = "打开 WIFI 开关";
            codePath = null;
        }
    }

    static class SetWifiEnabled_FALSE_KeyEntry extends MeetPrintBase {
        SetWifiEnabled_FALSE_KeyEntry() {
            keyword = "setWifiEnabled: false";
            explain = "关闭 WIFI 开关";
            codePath = null;
        }
    }

    static class IS_SCREEN_ON_TRUE_KeyEntry extends MeetPrintBase {
        IS_SCREEN_ON_TRUE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: true";
            explain = "开始亮屏";
            codePath = null;
        }
    }

    static class IS_SCREEN_ON_FLASE_KeyEntry extends MeetPrintBase {
        IS_SCREEN_ON_FLASE_KeyEntry() {
            keyword = "Value:IS_SCREEN_ON: false";
            explain = "开始灭屏";
            codePath = null;
        }
    }


    static class Shutting_down_VM_KeyEntry extends MeetPrintBase {
        Shutting_down_VM_KeyEntry() {
            keyword = "Shutting down VM";
            explain = "APP崩溃";
            codePath = null;
        }

    }

    // =============================Verbose解析类=================
    static class handleWifiApStateChange_currentState13_KeyEntry extends MeetPrintBase {
        handleWifiApStateChange_currentState13_KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13";
            explain = "[verbose]打开热点状态成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }
    }


    static class handleWifiApStateChange_currentState12_previousState11__KeyEntry extends MeetPrintBase {
        handleWifiApStateChange_currentState12_previousState11__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=12 previousState=11";
            explain = "[verbose]热点正在打开中 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
            codePath = null;
        }

    }


    static class handleWifiApStateChange_currentState13_previousState12__KeyEntry extends MeetPrintBase {
        handleWifiApStateChange_currentState13_previousState12__KeyEntry() {
            keyword = "WifiService: handleWifiApStateChange: currentState=13 previousState=12";
            explain = "[verbose]热点打开成功 10(正在关闭) 11(关闭成功) 12(正在打开) 13(打开成功) 14(打开失败)";
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


        for (File logFile : curFileList) {
            tryAnalysis(logFile);
            allFileAnalysisStringArr.add(new ArrayList<String>(analysisStringArr));
            analysisStringArr.clear();  // 对于下一个文件  先 处理  analysisStringArr 的数据  然后 迎接下一个文件的处理
        }

        try {

            BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mFilePath)), "utf-8"));
            int length = allFileAnalysisStringArr.size();
            for (int index = 0; index < length; index++) {
                ArrayList<String> arritem = allFileAnalysisStringArr.get(index);
                System.out.println("开始分析第 index = " + index + "个文件！");
                for (int i = 0; i < arritem.size(); i++) {

                    curBW.write(arritem.get(i));
                    curBW.newLine();
                }
            }
            curBW.close();
            System.out.println("OK !");

        } catch (Exception e) {


        }




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
}
