import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class  E6_Android_Res {


    static File dumpsys_res_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "8_1_DumpRes.txt");


    public static void main(String[] args) {
        try {

            getWifiDumpRes();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    static void getWifiDumpRes(){

        if(checkFileExist(dumpsys_res_File)){
       readDumpResInfoFromFile(dumpsys_res_File); // detail



            for (int i = 0; i < allWifiDumpResList.size(); i++) {
                ArrayList<String> dumpResInfoStrList = new  ArrayList<String>();
                ArrayList<DumpResItem>  dumpGroupItem = allWifiDumpResList.get(i);
                String searchText = dumpGroupItem.get(0).searchText;
                for (int j = 0; j < dumpGroupItem.size(); j++) {
                    DumpResItem dumpRes = dumpGroupItem.get(j);
                    String keyLog = dumpRes.getKeyLog();
                    String valueLog = dumpRes.getValueLog();
                    String descLog = dumpRes.geDescLog();
                    String line = "-------------";
                    dumpResInfoStrList.add(keyLog);
                    dumpResInfoStrList.add(valueLog);
                    dumpResInfoStrList.add(descLog);
                    dumpResInfoStrList.add(line);
                }
                ArrayPrint(dumpResInfoStrList ,"关键字:"+searchText );
            }




        }

    }

    static   void  readDumpResInfoFromFile(File fileItem) {
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            String newItemFlag = "android:";  // zukgit
            boolean isNewItem = false;
            String head = "";
            ArrayList<String> body = new ArrayList<String>();

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                lineContent = lineContent.trim();
                if("\n".equals(lineContent)   || "\r".equals(lineContent) || "\t".equals(lineContent) || "\n\r".equals(lineContent) || "\r\n".equals(lineContent) ){
                    continue;
                }
                if(lineContent.startsWith(newItemFlag)){
                    if(!isNewItem && !"".equals(head)){  // 上次读取到的是 配置的内容
                        toFillterDumpRes(head , body);
                        // 执行完之后 把把数据置空
                    }
                    isNewItem =  true;
                    head = lineContent;
                    body.clear();
                }else{
                    isNewItem = false;
                    body.add(lineContent.trim());
                }

            }
            curBR.close();
        } catch (Exception e) {
            System.out.println(" DumpRes 异常:"+e.fillInStackTrace());
            e.printStackTrace();
        }
    }

    public static void   toFillterDumpRes(String head , ArrayList<String> body){
       // System.out.println("toFillterDumpRes ");
        for (int i = 0; i < allWifiDumpResList.size(); i++) {
ArrayList<DumpResItem>  dumpGroupItem = allWifiDumpResList.get(i);
            for (int j = 0; j < dumpGroupItem.size(); j++) {
                DumpResItem dumpRes = dumpGroupItem.get(j);
                if(  head.contains(dumpRes.key+":")){
                    for (int k = 0; k < body.size(); k++) {
                        String valueItem = body.get(k);
                        if(!"".equals(valueItem)){
                         //   System.out.println("xx1="+valueItem);
                           // System.out.println("xx2="+dumpRes.key);
                            if(valueItem.length() < MAX_COUNT_CHAR_IN_ROW){
                                dumpRes.value.add(valueItem.trim());
                             //   System.out.println("dumpRes.value.add ="+body.get(k));
                            }else{
                                valueItem = valueItem.substring(0,MAX_COUNT_CHAR_IN_ROW-20);
                                dumpRes.value.add(valueItem.trim());
                            }

                        }

                    }
                }

            }
        }
    }


    public static boolean checkFileExist(File curFile){
        if(!curFile.exists()){
            System.out.println(" 当前文件不存在:"+curFile.getAbsolutePath());
            return false;
        }
        return true;
    }

    static String zch_string_frame_base_path = "/AndroidSDK/platforms/android-20/data/res/values-zh-rCN/strings.xml";



    static ArrayList<ArrayList<DumpResItem>>  allWifiDumpResList= new  ArrayList<ArrayList<DumpResItem>>();
    static  ArrayList<DumpResItem> WifiDumpResList = new  ArrayList<DumpResItem>();
    static  ArrayList<DumpResItem> PasspointDumpResList = new  ArrayList<DumpResItem>();



    static{
        allWifiDumpResList.add(WifiDumpResList);
        allWifiDumpResList.add(PasspointDumpResList);
    }


    static{
        // PasspointDumpResList.add(new DumpResItem("","") );   index = 2
        PasspointDumpResList.add(new DumpResItem("config_wifi_framework_PASSPOINT_SECURITY_AWARD","未知(passpoint相关)",2) ); //
        PasspointDumpResList.add(new DumpResItem("config_wifi_hs20_sim_mnc_mcc_retail","passpoint-显示开关",2));



        PasspointDumpResList.add(new DumpResItem("passpoint_switch_summary","Seamlessly connect to Passpoint&#8482; Wi-Fi without needing a password(无缝连接到 Passpoint™ WLAN，无需密码) 【(显示Item因子1.) persist.wpaconfig.hs2_switch==DEBUG [ adb shell setprop persist.wpaconfig.hs2_switch DEBUG ] 或 】 【(显示Item因子2.)( R.bool.config_show_passpoint_switch || R.array.carrier_list_hide_passpoint_option 决定)】【Item的值由 getPasspointEnableState() 决定】 【Item值最终来源 int Settings.Global.HS20_SAVED_STATE 】 【 HS20_SAVED_STATE =  \"hs20_saved_state\" 】",2));
        PasspointDumpResList.add(new DumpResItem("att_switch_summary","Automatically connect to an available AT&amp;T Wi-Fi Hotspot(自动连接到可用的 ATT WLAN 热点)【(显示Item因子1) persist.wpaconfig.att_switch==DEBUG  [ adb shell setprop persist.wpaconfig.att_switch DEBUG ]  】 【 (显示Item因子2) R.bool.config_show_att_switch 决定】 【Item的值由 WIFI开关决定】",2));
        PasspointDumpResList.add(new DumpResItem("sim_switch_summary","Find and auto-connect to SIM based Passpoint™ networks(查找并自动连接到基于 SIM 的 Passpoint™ 网络)【(显示Item1因子.)persist.wpaconfig.sim_switch==DEBUG  [ adb shell setprop persist.wpaconfig.sim_switch DEBUG ] 】【(显示Item因子2) (R.bool.config_wifi_hs20_sim_mnc_mcc_retail || R.array.carrier_list_hide_passpoint_option 决定)】 【Item的值由 getMNCMCCPasspointConnectState()(来源 boolean WifiConfigManager.ishs20MNCMCCRetailEnabled)】【Item值最终来源 int mSettingsStore.getHS20MNCMCCRetailState()】  【Settings.Global.HS20_MNCMCC_RETAIL_SAVED_STATE \"hs20_mncmcc_retail_saved_state\"】",2));

    }

    static{

        // wifi-search   index = 1
        // 【android:string】
//        WifiDumpResList.add(new DumpResItem("","") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_sap_2G_channel_list","2.4G热点信道列表") );
        WifiDumpResList.add(new DumpResItem("config_wifi_p2p_device_type","未知") ); // android:string/config_wifi_random_mac_oui: DA-A1-19
        WifiDumpResList.add(new DumpResItem("config_wifi_random_mac_oui","生成随机Mac地址的前三个前缀","http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/res/res/values/config.xml#597") ); // android:string/config_wifi_random_mac_oui: DA-A1-19
        WifiDumpResList.add(new DumpResItem("config_wifi_softap_acs_supported_channel_list","热点auto channel select功能支持的信道列表") );
        WifiDumpResList.add(new DumpResItem("config_wifi_sar_sensor_type","SAR相关(Specific Absorption Rate) 电磁波吸收比值 电磁波吸收比值吸收率") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_tcp_buffers","wifi传输缓存区域大小") ); //android:string/config_wifi_tcp_buffers: 524288,2097152,4194304,262144,524288,1048576
        WifiDumpResList.add(new DumpResItem("data_usage_wifi_limit_snoozed_title","(已超出 WLAN 数据流量上限)",zch_string_frame_base_path )); //
        WifiDumpResList.add(new DumpResItem("data_usage_wifi_limit_title","(已达到 WLAN 流量上限)",zch_string_frame_base_path )); //
        WifiDumpResList.add(new DumpResItem("permdesc_accessWifiState","允许该应用查看WLAN网络的相关信息，例如是否启用了WLAN以及连接的WLAN设备的名称",zch_string_frame_base_path) ); //
        WifiDumpResList.add(new DumpResItem("permdesc_changeWifiMulticastState","允许该应用使用多播地址接收发送到WLAN网络上所有设备的数据包",zch_string_frame_base_path) ); //
        WifiDumpResList.add(new DumpResItem("permdesc_changeWifiState","允许该应用与WLAN接入点建立和断开连接，以及更改WLAN网络的设备配置。",zch_string_frame_base_path) ); //
        WifiDumpResList.add(new DumpResItem("permlab_accessWifiState","查看WLAN连接",zch_string_frame_base_path) ); //
        WifiDumpResList.add(new DumpResItem("wfc_mode_wifi_only_summary","只能连接wifi") ); //
        WifiDumpResList.add(new DumpResItem("wfc_mode_wifi_preferred_summary","Call over Wi-Fi") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_carrier_network_title","连接到运营商wifi") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_content_failed_to_connect","点按即可查看所有网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_sign_in","登录到WLAN网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_title","连接到开放的 WLAN 网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_title_connected","已连接到 WLAN 网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_title_connecting","正在连接到开放的 WLAN 网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_available_title_failed_to_connect","无法连接到 WLAN 网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_connect_alert_message","%1$s”应用想要连接到 WLAN 网络“%2$s”") ); //
        WifiDumpResList.add(new DumpResItem("wifi_connect_alert_title","要允许连接吗？") ); //
        WifiDumpResList.add(new DumpResItem("wifi_eap_No_SIM_text","连接当前eap网络需要插入sim卡认证") ); //
        WifiDumpResList.add(new DumpResItem("wifi_localhotspot_configure_ssid_default","本地热点默认SSID名称(最原始)") ); //
        WifiDumpResList.add(new DumpResItem("wifi_no_internet","此 WLAN 网络无法访问互联网") ); //
        WifiDumpResList.add(new DumpResItem("wifi_no_internet_detailed","点按即可查看相关选项(no_internet)") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_dialog_title","WLAN 直连") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_disconnect_and_reconnect","wifi direct直连可用是否去连接") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_enabled_notification_title","已启用WLAN直连") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_enter_pin_message","输入所需的PIN码：") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_failed_message","无法启动WLAN直连。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_frequency_conflict_message","手机连接到 %1$s 时会暂时断开与WLAN的连接。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_from_message","发件人：") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_invitation_sent_title","已发出邀请") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_invitation_to_connect_title","连接邀请") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_show_pin_message","PIN 码：") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_to_message","收件人：") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_turnon_message","启动WLAN直连。此操作将会关闭WLAN客户端/热点。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_p2p_warning","警告") ); //


        WifiDumpResList.add(new DumpResItem("wifi_softap_config_change","您的热点设置已变更") ); //
        WifiDumpResList.add(new DumpResItem("wifi_softap_config_change_detailed","此设备不支持您的偏好设置（仅限 5GHz），而且会在 5GHz 频段可用时使用该频段。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_softap_config_change_summary","您的热点频段已变更。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_suggestion_content","Suggested by %s。") ); //

        WifiDumpResList.add(new DumpResItem("wifi_suggestion_title","(连接到网络)Connect to Wi‑Fi networks?") ); //

        WifiDumpResList.add(new DumpResItem("wifi_tether_configure_ssid_default","(原始热点名称)AndroidAP") ); //
        WifiDumpResList.add(new DumpResItem("wifi_wakeup_enabled_content","您位于已保存的网络 xx 信号范围内") ); //

        WifiDumpResList.add(new DumpResItem("wifi_wakeup_enabled_title","已自动开启 WLAN 网络") ); //
        WifiDumpResList.add(new DumpResItem("wifi_wakeup_onboarding_action_disable","不要重新开启") ); //


        WifiDumpResList.add(new DumpResItem("wifi_wakeup_onboarding_subtext","当您位于已保存的高品质网络信号范围内时") ); //
        WifiDumpResList.add(new DumpResItem("wifi_wakeup_onboarding_title","WLAN 将自动开启") ); //
        WifiDumpResList.add(new DumpResItem("wifi_watchdog_network_disabled","无法连接到WLAN") ); //

        WifiDumpResList.add(new DumpResItem("wifi_watchdog_network_disabled_detailed","互联网连接状况不佳。") ); //

        WifiDumpResList.add(new DumpResItem("wifi_enable_bt_warning_message","打开 WLAN 将会关闭蓝牙共享。") ); //
        WifiDumpResList.add(new DumpResItem("wifi_enable_generic_warning_message","移动热点和/或网络共享已激活。打开 WLAN 将会关闭移动热点和/或网络共享") ); //
        WifiDumpResList.add(new DumpResItem("wifi_enable_hotspot_warning_message","打开 WLAN 将会关闭移动热点。") ); //

        WifiDumpResList.add(new DumpResItem("wifi_enable_usb_warning_message","打开 WLAN 将会关闭 USB 共享。") ); //


        // 【android:integer】
        WifiDumpResList.add(new DumpResItem("config_networkAvoidBadWifi","避免差wifi设置选项") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_associated_short_scan_interval","wifi关联扫描间隔") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_disconnected_short_scan_interval","wifi断连扫描间隔") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_5GHz_preference_boost_factor","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_5GHz_preference_boost_threshold","未知") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_5GHz_preference_penalty_threshold","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_LAST_SELECTION_AWARD","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_PASSPOINT_SECURITY_AWARD","未知(passpoint相关)") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_RSSI_SCORE_OFFSET","未知") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_RSSI_SCORE_SLOPE","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_SAME_BSSID_AWARD","未知") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_SECURITY_AWARD","未知") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_full_scan_backoff","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_full_scan_max_interval","未知") ); //
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_full_scan_max_total_dwell_time","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_full_scan_rx_packet_threshold","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_full_scan_tx_packet_threshold","未知") ); //

        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_partial_scan_max_num_active_channels","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_partial_scan_rx_packet_threshold","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_associated_partial_scan_tx_packet_threshold","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_current_network_boost","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_exponential_backoff_scan_base_interval","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_max_rx_rate_for_full_scan","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_max_tx_rate_for_full_scan","未知") );
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_min_rx_rate_for_staying_on_network","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_min_tx_rate_for_staying_on_network","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_network_switch_rx_packet_threshold","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_network_switch_tx_packet_threshold","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_recovery_timeout_delay","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_sar_free_space_event_id","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_sar_near_body_event_id","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_sar_near_hand_event_id","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_sar_near_head_event_id","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_scan_result_rssi_level_patchup_value","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_soft_ap_timeout_delay","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_bad_rssi_threshold_24GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_bad_rssi_threshold_5GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_entry_rssi_threshold_24GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_entry_rssi_threshold_5GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_good_rssi_threshold_24GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_good_rssi_threshold_5GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_low_rssi_threshold_24GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_wifi_score_low_rssi_threshold_5GHz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_logger_ring_buffer_default_size_limit_kb","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_logger_ring_buffer_verbose_size_limit_kb","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_max_ap_interfaces","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_network_switching_blacklist_time","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_scan_interval_p2p_connected","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_supplicant_scan_interval","未知"));


        WifiDumpResList.add(new DumpResItem("config_enableWifiDisplay","未知"));
        WifiDumpResList.add(new DumpResItem("config_sip_wifi_only","未知"));
        WifiDumpResList.add(new DumpResItem("config_turn_wifi_off_while_bt_usb_tethering","未知"));
        WifiDumpResList.add(new DumpResItem("config_warn_user_enabling_wifi","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifiDisplaySupportsProtectedBuffers","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_background_scan_support","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_batched_scan_supported","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_connected_mac_randomization_supported","是否支持Mac地址随机化"));
        WifiDumpResList.add(new DumpResItem("config_wifi_convert_apband_5ghz_to_any","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_dual_band_support","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_enable_disconnection_debounce","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_fast_bss_transition_enabled","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_cellular_handover_enable_user_triggered_adjustment","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_enable_associated_network_selection","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_enable_body_proximity_sar_tx_power_limit","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_enable_sar_tx_power_limit","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_enable_soft_ap_sar_tx_power_limit","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_framework_use_single_radio_chain_scan_results_network_selection","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_hs20_sim_mnc_mcc_retail","passpoint-显示开关"));
        WifiDumpResList.add(new DumpResItem("config_wifi_link_probing_supported","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_local_only_hotspot_5ghz","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_only_link_same_credential_configurations","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_p2p_mac_randomization_supported","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_revert_country_code_on_cellular_loss","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_softap_acs_supported","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_softap_ieee80211ac_supported","未知"));
        WifiDumpResList.add(new DumpResItem("config_wifi_turn_off_during_emergency_call","未知"));
        WifiDumpResList.add(new DumpResItem("feature_4397_vzw_unsecure_wifi_connectivity_warning","未知"));
        WifiDumpResList.add(new DumpResItem("wifi_trigger_disconnect_bug2go","wifi断连是否触发bug2go开关"));


        // 【android:boolean】 开关




    }



    static String DUMP_RES_BEGIN_TAG = "android:";
    static class DumpResItem{
        String key="";
        String searchText= "";
        ArrayList<String> value;
        ArrayList<String> descStrList;
        String desc;  //  描述简介 功能描述
        // android:string    字符串
        // android:array   数组
        // android:drawable  图片
        String type; // res 资源的类型

        String uiPath; // res 起作用的路径

        String openGroupPath ;   //  定义的文件路径  opengroup


       String getKeyLog(){
            return "资源定义名称:"+key;
        }

        String geDescLog(){
            return "资源描述:"+desc;
        }

        String getValueLog(){
           String valueStr= "资源定义值: 总个数:"+this.value.size()+" ";
           String loopStr= "";
            for (int i = 0; i < this.value.size() ; i++) {
                String tip = "【 值索引:"+i+" value:"+this.value.get(i)+"】 ";
                loopStr = loopStr + tip;
            }

            String result = (valueStr + loopStr).trim();
            if(result.length() > MAX_COUNT_CHAR_IN_ROW){
                result = result.substring(0,MAX_COUNT_CHAR_IN_ROW- 40);
            }
           return result;

        }
// wifi 是默认的搜搜关键字

        DumpResItem(){
            searchText = "wifi";
          this.value = new ArrayList<String>();
          this.descStrList = new ArrayList<String>();
        }

        DumpResItem(int msearchText){
            this.searchText = calculSearchText(msearchText);
            this.value = new ArrayList<String>();
            this.descStrList = new ArrayList<String>();
        }

        DumpResItem(String mkey , String mdesc){
            this.key = mkey;
            this.desc = mdesc;
            this.value = new ArrayList<String>();
            this.searchText = "wifi";
            this.descStrList = new ArrayList<String>();
        }


        DumpResItem(String mkey , String mdesc , int searchText){
            this.key = mkey;
            this.desc = mdesc;
            this.value = new ArrayList<String>();
            this.searchText =  calculSearchText(searchText);
            this.descStrList = new ArrayList<String>();
        }



        DumpResItem(String mkey , String mdesc , String mOpenGroupPath){
            this.key = mkey;
            this.desc = mdesc;
            this.openGroupPath = mOpenGroupPath;
            this.value = new ArrayList<String>();
            this.searchText = "wifi";
            this.descStrList = new ArrayList<String>();
        }


        DumpResItem(String mkey , String mdesc , String mOpenGroupPath , int searchText){
            this.key = mkey;
            this.desc = mdesc;
            this.openGroupPath = mOpenGroupPath;
            this.value = new ArrayList<String>();
            this.searchText = calculSearchText(searchText);
            this.descStrList = new ArrayList<String>();
        }

      String   calculSearchText(int index){
           if(index == 1){
               return "wifi";
           }else if( index == 2){
               return "passpoint";

           }
           return "null";
        }


    }



    // ArrayPrint ==============================Begin
    static public void printArrObject(Object[] objArr, String title) {
        ArrayList<String> curPropStrArr = new ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if ("".equals(objArr[i].toString())) {
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
        ArrayPrint(curPropStrArr, title);
    }


    static int MAX_COUNT_CHAR_IN_ROW = 132;
    static int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 132;

    public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
        boolean flag = false;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
                //   System.out.println("index["+i+"]  size= "+mStrList.get(i).length()+"     Value:" + mStrList.get(i) );
                return true;
            }
        }
        return flag;

    }


    public static ArrayList<String> makeStringGroup(String code, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        String oriStr = code.trim();
        while (oriStr.length() > maxcount) {
            String str1 = oriStr.substring(0, maxcount);
            fixArr.add(str1);
            oriStr = oriStr.substring(maxcount);
        }


        return fixArr;
    }


    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains("【") && strArr[i].contains("】") ){
                    subArrA = toSqlitWithhardBlock(strArr[i] );
                }else if (strArr[i].contains(";")) {
                    subArrA = sqlitString(strArr[i], ";");

                } else if (strArr[i].contains("。")) {
                    subArrA = sqlitString(strArr[i], "。");

                } else if (strArr[i].contains(":")) {
                    subArrA = sqlitString(strArr[i], ":");
                } else if (strArr[i].contains(".")) {
                    subArrA = sqlitString(strArr[i], ".");
                } else if (strArr[i].contains(" ")) {
                    subArrA = sqlitString(strArr[i], " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixArr.add(tempArr.get(j));
                    }

                }

                if (subArrA != null && isItemLengthOver100(subArrA)) {
                    String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixArr.add(fixSub);
                } else {
                    if (subArrA != null) {
                        for (int j = 0; j < subArrA.size(); j++) {
                            fixArr.add(subArrA.get(j));
                        }

                    }
                }

            } else {
                fixArr.add(strArr[i]);
            }
        }
        return fixArr;
    }



    public static ArrayList<String> toSqlitWithhardBlock(String mStrList) {
        ArrayList<String> resultList = new  ArrayList<String>();
       //【】  【】,
        String mStr = mStrList.trim();

        String pre = mStr.substring(0,mStr.indexOf("【"));
        mStr =mStr.substring(mStr.indexOf("【"));
        resultList.add(pre);
        String end = "";
        if(mStr.endsWith("】")){
             end = "";
        }else{
             end =  mStr.substring(mStr.lastIndexOf("】")+1);
        }

        mStr =mStr.substring(0,mStr.lastIndexOf("】")+1);

        while(mStr.contains("】") && mStr.contains("【")){
            String firstStr = mStr.substring(mStr.indexOf("【"),mStr.indexOf("】")+1);
            resultList.add(firstStr);
            mStr = mStr.substring(mStr.indexOf("】")+1);
        }

        if(!"".equals(mStr.trim())){
            resultList.add(mStr.trim());
        }

        if(!"".equals(end)){
            resultList.add(end);
        }


//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("xxx："+i+"  ="+resultList.get(i) +"   mStr="+mStr);
//        }
return resultList;
    }


    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains("【") && curMaxStr.contains("】") ){
                    fixA = toSqlitWithhardBlock(curMaxStr );
                } if (curMaxStr.contains(";")) {
                    fixA = sqlitString(curMaxStr, ";");
                } else if (curMaxStr.contains("。")) {
                    fixA = sqlitString(curMaxStr, "。");
                } else if (curMaxStr.contains(":")) {
                    fixA = sqlitString(curMaxStr, ":");
                } else if (curMaxStr.contains(".")) {
                    fixA = sqlitString(curMaxStr, ".");
                } else if (curMaxStr.contains(" ")) {
                    fixA = sqlitString(curMaxStr, " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixLengthArr.add(tempArr.get(j));
                    }
                }

                if (fixA != null) {
                    //   System.out.println(" fixA.size="+ fixA.size());
                    for (int j = 0; j < fixA.size(); j++) {
                        // System.out.println(" fixA.size="+ fixA.size() + " i="+j+"   value:"+fixA.get(j));
                    }
                } else {
                    //  System.out.println(" fixA.size= null!");
                }
                if (isItemLengthOver100(fixA)) {
                    String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixLengthArr.add(fixSub);
                } else {
                    if (fixA != null) {
                        for (int j = 0; j < fixA.size(); j++) {
                            fixLengthArr.add(fixA.get(j));
                        }
                    }
                }


            }
        }

        return fixLengthArr;
    }


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
    }

    public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curStr = mStrList.get(i);
            String fixCurStr = getFixLengthNewStr(curStr, maxcount);
            fixArr.add(fixCurStr);
        }

        return fixArr;
    }


    public static String getFixLengthNewStr(String oriStr, int maxLength) {
        String fixStr = "";
        String beginChar = "║ ";
        String endChar = "║";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getChineseCount(oriStr);
        paddingLength = paddingLength - chineseCount;
        if (paddingLength < 0) {
            // return "curString:" + oriStr + "  length more than" + maxLength;
            return "";
        }

        for (int i = 0; i < paddingLength; i++) {
            oriStrTrim += " ";
        }
        oriStrTrim = beginChar + oriStrTrim + endChar;
        //  oriStrTrim = beginChar + oriStrTrim ;
        fixStr = oriStrTrim;
        return fixStr;
    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
            ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
        int maxLength = getItemMaxLength(mStrList);
        ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
        int chineseCount = getChineseCount(title);


        String beginRow = "╔════════════════════════════════════════════════" + title + "═════════════════════════════════════════════════════╗";
        String endRow = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝";
        int fixLength = 0;
        int oriLength = title.length();
        if (chineseCount == 0) { // 不包含汉字
            fixLength = oriLength;

        } else {
            if (chineseCount == oriLength) { // 全部包含汉字
                fixLength = 2 * oriLength;
            } else { // 一部分汉字  一部分英语

                fixLength = oriLength - chineseCount + (2 * chineseCount);
            }

        }
        String templateString = "╗";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "═" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "╗");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow = resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow = resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

    static String resetEndRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╝", paddingString + "╝");
        return curBeginStr;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    static String getRepeatString(String repeatSrc, int repeatCount) {
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    static String resetBeginRowToDefaultSize(String beginRow) {
        String curBeginStr = new String(beginRow);
        int curPaddingLength = getPaddingChineseLength(curBeginStr);
        int distance = 0;
        if (curPaddingLength < MAX_COUNT_CHAR_IN_ROW) {
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═", distance + 3);
        curBeginStr = curBeginStr.replace("╗", paddingString + "╗");
        return curBeginStr;
    }

    public static void ArrayPrint(ArrayList<String> mStrList, String title) {

        ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
        // 对mStrList 进行 对其处理  重新转换为 对其的  ArrayList<String> new
        // 1. 判断所有字符串中 第一次出现冒号的位置   查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
        // 2.  重新排序的规则是  小字符串需要在: 之后添加  xMaxLengh - self().length 的空格 并重新加入新的数组
        ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
        boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

        if (isOver100) {
            //     System.out.println("当前的字符串Item 存在大于 100字符的！");
            ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
            showTableLogCommon100(newLessList, title);  //  每一行都小于100个字的打印
        } else { //
            //   System.out.println("当前的字符串Item 不 存在大于 100字符的！");
            showTableLogCommon100(firstFixedStringArrA, title);  //  每一行都小于100个字的打印


        }
    }

    public static String getPaddingEmptyString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "-";
        }
        return str;
    }

    // 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
    // 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
    public static String addMaoChinese(String oriStr) {
        String resultStr = "";
        int chinesePosition = getFirstChinesePosition(oriStr);
        resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
        return resultStr;
    }


    public static int getFirstChinesePosition(String str) {
        int position = 0;
        boolean getFirstChinese = false;
        char[] newChar = str.toCharArray();  //转为单个字符
        for (int i = 0; i < newChar.length; i++) {
            char curChar = newChar[i];
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(curChar + "");
            if (m.find()) {
                getFirstChinese = true;
                position = i;
            } else {
                if (getFirstChinese == true) {
                    return i;
                }
            }

        }
        return position;
    }

    public static String addMaoBlank(String oriStr) {
        String resultStr = "";
        int blankPosition = oriStr.indexOf(" ");
        resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
        return resultStr;
    }

    public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            if (isCommonMao(curItem)) {
                fixedArr.add(curItem);
            } else {
                // 对于那些没有冒号的  字段的处理
                //1.如果包含汉字 那么就在 汉字的最后添加一个冒号
                // 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
                if (isContainChinese(curItem)) {
                    String addMaoStr = addMaoChinese(curItem);
                    fixedArr.add(addMaoStr);
                } else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
                    String addMaoStr = addMaoBlank(curItem);
                    fixedArr.add(addMaoStr);
                } else {  // 如果以上条件都不满足   那么就在字符串最前面添加一个冒号

                    fixedArr.add(":" + curItem);

                }

            }

        }
        return fixedArr;
    }


    // 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度  返回true
    public static boolean isCommonMao(String oriStr) {
        boolean flag = false;
        if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
            flag = true;  // 只有一个冒号
        } else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\") && oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
            flag = true; // 多个冒号 并且  第一个冒号  :   在 :\ 之前
        } else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
            flag = true;   // 多个冒号
        }
        return flag;
    }

    public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        int maxMaoPosition = getMaxMaoPosition(mStrList);
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            int curMaoPosition = curItem.indexOf(":");
            String pre = curItem.substring(0, curMaoPosition);
            String end = curItem.substring(curMaoPosition); // 去掉:
            int preLength = getPaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
            String fixedItem = pre + padding + end;
            fixedArr.add(fixedItem);


        }
        return fixedArr;
    }

    public static int getMaxMaoPosition(ArrayList<String> mStrList) {
        int maoPosition = 0;
        String maxString = "";
        for (int i = 0; i < mStrList.size(); i++) {
            if ((mStrList.get(i).contains(":"))) {
                int curMaoPosition = mStrList.get(i).indexOf(":");
                String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
                int paddingSize = getPaddingChineseLength(maoString);
                if (paddingSize > maoPosition) {
                    maoPosition = paddingSize;
                    maxString = mStrList.get(i);
                }
            }

        }
        //  System.out.println("最长的冒号位置: maoPosition="+maoPosition+"   string="+maxString);
        return maoPosition;
    }
    // ArrayPrint ==============================End

}

