
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class A8 {

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
            keyword = "WifiTetherApBandPref: Band preference changed, updating band index to";
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

    static class Suppressing_Notification extends MeetPrintBase {   // 模板
        Suppressing_Notification() {
            keyword = "Suppressing notification";
            explain = "[ 丢弃 Notofication ]";
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
            if (lineContent == null || !lineContent.contains(keyword)) {
                return;
            }
            if (keywordArr != null && keywordArr.size() > 0) {
                boolean findMultiWord = true;
                for (String keyword : keywordArr) {
                    if (!lineContent.contains(keyword)) {
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

        for (File fileItem : curDirFile.listFiles()) {
            if (fileItem.isFile() && fileItem.getName().endsWith("txt")) {
                curFileList.add(fileItem);

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
