# 字符串统计
```
1. frameworks/base/packages/SettingsLib
2. frameworks/base/packages/SystemUI
3. frameworks/base/core/res/               frameworks/base/core/res/res/values/strings.xml
4. packages/apps/Settings/
5. packages/apps/Launch3/
6. packages/apps/Bluetooth/
7. /packages/apps/Nfc/
```





# /packages/apps/Settings/

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/res/values-zh-rCN/strings.xml
packages_apps_Settings_res_strings_cn.xml

```
<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="yes" >"是"</string>
    <string name="no" >"否"</string>
    <string name="create" >"创建"</string>
    <string name="allow" >"允许"</string>
    <string name="deny" >"拒绝"</string>
    <string name="dlg_close" >"关闭"</string>
    <string name="dlg_switch" >"切换"</string>
    <string name="device_info_default" >"未知"</string>
    <plurals name="show_dev_countdown" formatted="false" >
      <item quantity="other">现在只需再执行 <xliff:g id="STEP_COUNT_1">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
      <item quantity="one">现在只需再执行 <xliff:g id="STEP_COUNT_0">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
    </plurals>
    <string name="show_dev_on" >"您现在处于开发者模式！"</string>
    <string name="show_dev_already" >"您已处于开发者模式，无需进行此操作。"</string>
    <string name="dev_settings_disabled_warning" >"请先启用开发者选项。"</string>
    <string name="header_category_wireless_networks" >"无线和网络"</string>
    <string name="header_category_connections" >"无线和网络"</string>
    <string name="header_category_device" >"设备"</string>
    <string name="header_category_personal" >"个人"</string>
    <string name="header_category_access" >"帐号和权限"</string>
    <string name="header_category_system" >"系统"</string>
    <string name="radio_info_data_connection_enable" >"启用数据网络连接"</string>
    <string name="radio_info_data_connection_disable" >"停用数据网络连接"</string>
    <string name="volte_provisioned_switch_string" >"已配置 VoLTE"</string>
    <string name="vt_provisioned_switch_string" >"已配置视频通话"</string>
    <string name="wfc_provisioned_switch_string" >"已配置 WLAN 通话"</string>
    <string name="eab_provisioned_switch_string" >"已配置 EAB/Presence"</string>
    <string name="radio_info_radio_power" >"移动无线装置电源"</string>
    <string name="radioInfo_menu_viewADN" >"查看 SIM 卡通讯录"</string>
    <string name="radioInfo_menu_viewFDN" >"查看固定拨号号码"</string>
    <string name="radioInfo_menu_viewSDN" >"查看服务拨号"</string>
    <string name="radioInfo_menu_getIMS" >"IMS 服务状态"</string>
    <string name="radio_info_ims_reg_status_title" >"IMS 状态"</string>
    <string name="radio_info_ims_reg_status_registered" >"已注册"</string>
    <string name="radio_info_ims_reg_status_not_registered" >"未注册"</string>
    <string name="radio_info_ims_feature_status_available" >"可供使用"</string>
    <string name="radio_info_ims_feature_status_unavailable" >"无法使用"</string>
    <string name="radio_info_ims_reg_status" >"IMS 注册：<xliff:g id="STATUS">%1$s</xliff:g>\nLTE 语音通话：<xliff:g id="AVAILABILITY_0">%2$s</xliff:g>\nWLAN 语音通话：<xliff:g id="AVAILABILITY_1">%3$s</xliff:g>\n视频通话：<xliff:g id="AVAILABILITY_2">%4$s</xliff:g>\nUT 接口：<xliff:g id="AVAILABILITY_3">%5$s</xliff:g>"</string>
    <string name="radioInfo_service_in" >"服务中"</string>
    <string name="radioInfo_service_out" >"不在服务区"</string>
    <string name="radioInfo_service_emergency" >"只能拨打紧急呼救电话"</string>
    <string name="radioInfo_service_off" >"无线装置已关闭"</string>
    <string name="radioInfo_roaming_in" >"漫游"</string>
    <string name="radioInfo_roaming_not" >"未使用漫游服务"</string>
    <string name="radioInfo_phone_idle" >"空闲"</string>
    <string name="radioInfo_phone_ringing" >"响铃"</string>
    <string name="radioInfo_phone_offhook" >"正在通话"</string>
    <string name="radioInfo_data_disconnected" >"已断开连接"</string>
    <string name="radioInfo_data_connecting" >"正在连接"</string>
    <string name="radioInfo_data_connected" >"已连接"</string>
    <string name="radioInfo_data_suspended" >"已暂停"</string>
    <string name="radioInfo_unknown" >"未知"</string>
    <string name="radioInfo_display_packets" >"pkts"</string>
    <string name="radioInfo_display_bytes" >"字节"</string>
    <string name="radioInfo_display_dbm" >"dBm"</string>
    <string name="radioInfo_display_asu" >"asu"</string>
    <string name="radioInfo_lac" >"LAC"</string>
    <string name="radioInfo_cid" >"CID"</string>
    <string name="sdcard_unmount" product="nosdcard" >"卸载USB存储设备"</string>
    <string name="sdcard_unmount" product="default" >"卸载SD卡"</string>
    <string name="sdcard_format" product="nosdcard" >"格式化USB存储设备"</string>
    <string name="sdcard_format" product="default" >"格式化SD卡"</string>
    <string name="preview_pager_content_description" >"预览"</string>
    <string name="preview_page_indicator_content_description" >"预览第 <xliff:g id="CURRENT_PAGE">%1$d</xliff:g> 页（共 <xliff:g id="NUM_PAGES">%2$d</xliff:g> 页）"</string>
    <string name="font_size_summary" >"缩小或放大屏幕上的文字。"</string>
    <string name="font_size_make_smaller_desc" >"缩小"</string>
    <string name="font_size_make_larger_desc" >"放大"</string>
    <!-- no translation found for font_size_preview_text (4818424565068376732) -->
    <skip />
    <string name="font_size_preview_text_headline" >"示例文本"</string>
    <string name="font_size_preview_text_title" >"绿野仙踪"</string>
    <string name="font_size_preview_text_subtitle" >"第 11 章：奥兹国神奇的翡翠城"</string>
    <string name="font_size_preview_text_body" >"尽管有绿色眼镜保护着眼睛，桃乐丝和她的朋友们在刚看到这座奇妙的城市时，还是被它耀眼的光芒照得眼花缭乱。街道两旁耸立着绿色大理石砌成的美丽房屋，到处镶嵌着闪闪发光的翡翠。他们走过的人行道同样是用绿色大理石铺砌而成，石块接合处嵌有一排排紧密相连的翡翠，在阳光的照耀下闪闪发亮。房屋的窗户镶嵌着绿色的玻璃，城市上空有淡淡的绿晕，就连阳光也散发着绿色光芒。\n\n街道上有很多行人，无论男女老少全都穿着绿色衣物，连皮肤都泛着绿色。他们都用惊异的眼光注视着桃乐丝和她这群外貌迥异的伙伴们，孩子们一看到狮子都拔腿跑了，躲到他们母亲身后；没有人想开口跟桃乐丝他们说话。街上有许多商店，桃乐丝看见店里的每一件商品都是绿色的，有绿色的糖果，绿色的爆米花，还有各种各样的绿鞋子、绿帽子和绿衣服。有位小贩在路上卖绿色的柠檬水，当孩子们去买汽水时，桃乐丝发现他们付钱时所用的硬币竟然也是绿色的。\n\n城里似乎没有马，也没有其他任何动物。有人用绿色小推车来回运载物品。每个人看起来都是那么快乐、满足，一切都显得安定繁荣。"</string>
    <string name="font_size_save" >"确定"</string>
    <string name="sdcard_setting" product="nosdcard" >"USB存储设备"</string>
    <string name="sdcard_setting" product="default" >"SD卡"</string>
    <string name="bluetooth" >"蓝牙"</string>
    <string name="bluetooth_is_discoverable" >"附近所有蓝牙设备均可检测到此设备（<xliff:g id="DISCOVERABLE_TIME_PERIOD">%1$s</xliff:g>）"</string>
    <string name="bluetooth_is_discoverable_always" >"附近所有蓝牙设备均可检测到此设备"</string>
    <string name="bluetooth_not_visible_to_other_devices" >"其他蓝牙设备检测不到此设备"</string>
    <string name="bluetooth_only_visible_to_paired_devices" >"已配对的设备可检测到此设备"</string>
    <string name="bluetooth_visibility_timeout" >"检测超时设置"</string>
    <string name="bluetooth_lock_voice_dialing" >"锁定语音拨号"</string>
    <string name="bluetooth_lock_voice_dialing_summary" >"屏幕锁定时停止使用蓝牙拨号器"</string>
    <string name="bluetooth_devices" >"蓝牙设备"</string>
    <string name="bluetooth_device_name" >"设备名称"</string>
    <string name="bluetooth_device_details" >"设备设置"</string>
    <string name="bluetooth_profile_details" >"配置文件设置"</string>
    <string name="bluetooth_name_not_set" >"未设置名称，使用帐号名"</string>
    <string name="bluetooth_scan_for_devices" >"扫描设备"</string>
    <string name="bluetooth_rename_device" >"重命名此设备"</string>
    <string name="bluetooth_rename_button" >"重命名"</string>
    <string name="bluetooth_disconnect_title" >"要断开与该设备的连接吗？"</string>
    <string name="bluetooth_disconnect_all_profiles" product="default" >"您的手机将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_all_profiles" product="tablet" >"您的平板电脑将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_all_profiles" product="device" >"您的设备将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_dialog_ok" >"断开连接"</string>
    <string name="bluetooth_empty_list_user_restricted" >"您无权更改蓝牙设置。"</string>
    <string name="bluetooth_pairing_pref_title" >"与新设备配对"</string>
    <string name="bluetooth_is_visible_message" >"在 <xliff:g id="DEVICE_NAME">%1$s</xliff:g> 上开启蓝牙设置后，附近的设备将可以检测到该设备。"</string>
    <string name="bluetooth_footer_mac_message" product="default" >"手机的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_footer_mac_message" product="tablet" >"平板电脑的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_footer_mac_message" product="device" >"设备的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_is_disconnect_question" >"要断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接吗？"</string>
    <string name="bluetooth_broadcasting" >"广播"</string>
    <string name="bluetooth_disable_profile_title" >"要停用配置文件吗？"</string>
    <string name="bluetooth_disable_profile_message" >"此操作将停用：&lt;br&gt;&lt;b&gt;<xliff:g id="PROFILE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;来自：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%2$s</xliff:g>&lt;/b&gt;"</string>
    <string name="bluetooth_unknown" ></string>
    <string name="bluetooth_device" >"未命名的蓝牙设备"</string>
    <string name="progress_scanning" >"正在搜索"</string>
    <string name="bluetooth_no_devices_found" >"未在附近找到蓝牙设备。"</string>
    <string name="bluetooth_notif_ticker" >"蓝牙配对请求"</string>
    <string name="bluetooth_notif_title" >"配对请求"</string>
    <string name="bluetooth_notif_message" >"点按即可与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”配对。"</string>
    <string name="bluetooth_show_received_files" >"收到的文件"</string>
    <string name="device_picker" >"选择蓝牙设备"</string>
    <string name="bluetooth_ask_enablement" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙"</string>
    <string name="bluetooth_ask_disablement" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求关闭蓝牙"</string>
    <string name="bluetooth_ask_enablement_no_name" >"某个应用请求开启蓝牙"</string>
    <string name="bluetooth_ask_disablement_no_name" >"某个应用请求关闭蓝牙"</string>
    <string name="bluetooth_ask_discovery" product="tablet" >"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_discovery" product="default" >"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_discovery_no_name" product="tablet" >"某个应用想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_discovery_no_name" product="default" >"某个应用想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_lasting_discovery" product="tablet" >"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery" product="default" >"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery_no_name" product="tablet" >"某个应用想让其他蓝牙设备检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery_no_name" product="default" >"某个应用想让其他蓝牙设备检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_discovery" product="tablet" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_enablement_and_discovery" product="default" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_enablement_and_discovery_no_name" product="tablet" >"某个应用请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_enablement_and_discovery_no_name" product="default" >"某个应用请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery" product="tablet" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备可检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery" product="default" >"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备可检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery_no_name" product="tablet" >"某个应用请求开启蓝牙，以便其他设备可检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery_no_name" product="default" >"某个应用请求开启蓝牙，以便其他设备可检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_turning_on" >"正在打开蓝牙..."</string>
    <string name="bluetooth_turning_off" >"正在关闭蓝牙…"</string>
    <string name="bluetooth_auto_connect" >"自动连接"</string>
    <string name="bluetooth_connection_permission_request" >"蓝牙连接请求"</string>
    <string name="bluetooth_connection_notif_message" >"点按即可连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”。"</string>
    <string name="bluetooth_connection_dialog_text" >"要连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”吗？"</string>
    <string name="bluetooth_phonebook_request" >"电话簿权限申请"</string>
    <string name="bluetooth_pb_acceptance_dialog_text" >"<xliff:g id="DEVICE_NAME_0">%1$s</xliff:g> 想访问您的通讯录和通话记录。要向 <xliff:g id="DEVICE_NAME_1">%2$s</xliff:g> 授予访问权限吗？"</string>
    <string name="bluetooth_remember_choice" >"不要再询问"</string>
    <string name="bluetooth_pb_remember_choice" >"下次不再询问"</string>
    <string name="bluetooth_map_request" >"消息权限申请"</string>
    <string name="bluetooth_map_acceptance_dialog_text" >"“%1$s”想要查看您的消息。要向“%2$s”授予权限吗？"</string>
    <string name="bluetooth_sap_request" >"SIM 访问权限请求"</string>
    <string name="bluetooth_sap_acceptance_dialog_text" >"<xliff:g id="DEVICE_NAME_0">%1$s</xliff:g>想要访问您的 SIM 卡。如果授权该设备访问 SIM 卡，您设备的数据连接功能在蓝牙连接期间将停用。将访问权限授予<xliff:g id="DEVICE_NAME_1">%2$s?</xliff:g>"</string>
    <string name="bluetooth_device_name_summary" >"向其他设备显示为“<xliff:g id="DEVICE_NAME">^1</xliff:g>”"</string>
    <string name="bluetooth_off_footer" >"开启蓝牙即可连接到其他设备。"</string>
    <string name="bluetooth_paired_device_title" >"您的设备"</string>
    <string name="bluetooth_pairing_page_title" >"与新设备配对"</string>
    <string name="bluetooth_pref_summary" product="tablet" >"允许您的平板电脑与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_pref_summary" product="device" >"允许您的设备与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_pref_summary" product="default" >"允许您的手机与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_disable_a2dp_hw_offload" >"停用蓝牙 A2DP 硬件卸载"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_title" >"要重启设备吗？"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_message" >"您需要重启设备才能让这项设置更改生效。"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_confirm" >"重启"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_cancel" >"取消"</string>
    <string name="connected_device_available_media_title" >"可用的媒体设备"</string>
    <string name="connected_device_available_call_title" >"可用的通话设备"</string>
    <string name="connected_device_connected_title" >"当前已连接"</string>
    <string name="connected_device_saved_title" >"保存的设备"</string>
    <string name="connected_device_add_device_title" >"添加设备"</string>
    <string name="connected_device_add_device_summary" >"系统将开启蓝牙以进行配对"</string>
    <string name="connected_device_connections_title" >"连接偏好设置"</string>
    <string name="connected_device_previously_connected_title" >"之前连接的设备"</string>
    <string name="date_and_time" >"日期和时间"</string>
    <string name="choose_timezone" >"选择时区"</string>
    <!-- no translation found for intent_sender_data_label (6332324780477289261) -->
    <skip />
    <string name="intent_sender_sendbroadcast_text" >"发送<xliff:g id="BROADCAST">broadcast</xliff:g>"</string>
    <string name="intent_sender_action_label" >"<xliff:g id="ACTION">Action</xliff:g>:"</string>
    <string name="intent_sender_startactivity_text" >"启动<xliff:g id="ACTIVITY">activity</xliff:g>"</string>
    <string name="intent_sender_resource_label" >"<xliff:g id="RESOURCE">Resource</xliff:g>:"</string>
    <string name="intent_sender_account_label" >"帐号:"</string>
    <string name="proxy_settings_title" >"代理"</string>
    <string name="proxy_clear_text" >"清除"</string>
    <string name="proxy_port_label" >"代理服务器端口"</string>
    <string name="proxy_exclusionlist_label" >"对以下网址不使用代理"</string>
    <string name="proxy_defaultView_text" >"恢复默认设置"</string>
    <string name="proxy_action_text" >"完成"</string>
    <string name="proxy_hostname_label" >"代理服务器主机名"</string>
    <string name="proxy_error" >"注意"</string>
    <string name="proxy_error_dismiss" >"确定"</string>
    <string name="proxy_error_invalid_host" >"您键入的主机名无效。"</string>
    <string name="proxy_error_invalid_exclusion_list" >"您输入的排除列表格式有误。请输入以逗号分隔的排除网域列表。"</string>
    <string name="proxy_error_empty_port" >"您需要填写端口字段。"</string>
    <string name="proxy_error_empty_host_set_port" >"如果主机字段为空，则端口字段必须为空。"</string>
    <string name="proxy_error_invalid_port" >"您键入的端口无效。"</string>
    <string name="proxy_warning_limited_support" >"浏览器会使用 HTTP 代理，但其他应用可能不会使用。"</string>
    <string name="proxy_url_title" >"PAC网址： "</string>
    <string name="radio_info_dl_kbps" >"DL 带宽 (kbps)："</string>
    <string name="radio_info_ul_kbps" >"UL 带宽 (kbps)："</string>
    <string name="radio_info_signal_location_label" >"移动网络位置信息（已弃用）："</string>
    <string name="radio_info_neighboring_location_label" >"邻域移动网络信息（已弃用）："</string>
    <string name="radio_info_phy_chan_config" >"LTE 物理信道配置："</string>
    <string name="radio_info_cell_info_refresh_rate" >"移动网络信息刷新频率："</string>
    <string name="radio_info_cellinfo_label" >"所有移动网络测量信息："</string>
    <string name="radio_info_dcrtinfo_label" >"数据网络连接实时信息："</string>
    <string name="radio_info_gprs_service_label" >"数据服务："</string>
    <string name="radio_info_roaming_label" >"漫游："</string>
    <string name="radio_info_imei_label" >"移动通信国际识别码："</string>
    <string name="radio_info_call_redirect_label" >"来电转接："</string>
    <string name="radio_info_ppp_resets_label" >"启动后重置 PPP 的次数："</string>
    <string name="radio_info_current_network_label" >"当前网络："</string>
    <string name="radio_info_ppp_received_label" >"已接收的数据量："</string>
    <string name="radio_info_gsm_service_label" >"语音服务："</string>
    <string name="radio_info_signal_strength_label" >"信号强度："</string>
    <string name="radio_info_call_status_label" >"语音通话状态："</string>
    <string name="radio_info_ppp_sent_label" >"已发送的数据量："</string>
    <string name="radio_info_message_waiting_label" >"消息等待："</string>
    <string name="radio_info_phone_number_label" >"电话号码："</string>
    <string name="radio_info_band_mode_label" >"选择无线装置频道"</string>
    <string name="radio_info_voice_network_type_label" >"语音网络类型："</string>
    <string name="radio_info_data_network_type_label" >"数据网络类型："</string>
    <string name="radio_info_set_perferred_label" >"设置首选网络类型："</string>
    <string name="radio_info_ping_hostname_v4" >"ping 主机名(www.google.com) IPv4："</string>
    <string name="radio_info_ping_hostname_v6" >"ping 主机名(www.google.com) IPv6："</string>
    <string name="radio_info_http_client_test" >"HTTP 客户端测试："</string>
    <string name="ping_test_label" >"运行 ping 测试"</string>
    <string name="radio_info_smsc_label" >"SMSC："</string>
    <string name="radio_info_smsc_update_label" >"更新"</string>
    <string name="radio_info_smsc_refresh_label" >"刷新"</string>
    <string name="radio_info_toggle_dns_check_label" >"切换 DNS 检查"</string>
    <string name="oem_radio_info_label" >"特定 OEM 的信息/设置"</string>
    <string name="band_mode_title" >"设置无线装置频道模式"</string>
    <string name="band_mode_loading" >"正在加载频道列表…"</string>
    <string name="band_mode_set" >"设置"</string>
    <string name="band_mode_failed" >"失败"</string>
    <string name="band_mode_succeeded" >"成功"</string>
    <string name="sdcard_changes_instructions" >"重新连接USB线后更改就会生效。"</string>
    <string name="sdcard_settings_screen_mass_storage_text" >"启用USB大容量存储设备"</string>
    <string name="sdcard_settings_total_bytes_label" >"总字节数："</string>
    <string name="sdcard_settings_not_present_status" product="nosdcard" >"未装载USB存储设备。"</string>
    <string name="sdcard_settings_not_present_status" product="default" >"无SD卡。"</string>
    <string name="sdcard_settings_available_bytes_label" >"可用的字节数："</string>
    <string name="sdcard_settings_mass_storage_status" product="nosdcard" >"计算机正将USB存储设备用作大容量存储设备。"</string>
    <string name="sdcard_settings_mass_storage_status" product="default" >"计算机正将SD卡用作大容量存储设备。"</string>
    <string name="sdcard_settings_unmounted_status" product="nosdcard" >"现在可以安全地移除该USB存储设备了。"</string>
    <string name="sdcard_settings_unmounted_status" product="default" >"现在可以安全地移除SD卡了。"</string>
    <string name="sdcard_settings_bad_removal_status" product="nosdcard" >"USB存储设备仍在使用中便被移除！"</string>
    <string name="sdcard_settings_bad_removal_status" product="default" >"SD卡仍在使用中便被移除！"</string>
    <string name="sdcard_settings_used_bytes_label" >"已使用的字节数："</string>
    <string name="sdcard_settings_scanning_status" product="nosdcard" >"正在扫描USB存储设备中的媒体..."</string>
    <string name="sdcard_settings_scanning_status" product="default" >"正从SD卡扫描媒体..."</string>
    <string name="sdcard_settings_read_only_status" product="nosdcard" >"已将USB存储设备装载为只读设备。"</string>
    <string name="sdcard_settings_read_only_status" product="default" >"已将SD卡装载为只读设备。"</string>
    <string name="skip_label" >"跳过"</string>
    <string name="next_label" >"下一步"</string>
    <string name="language_picker_title" >"语言"</string>
    <string name="pref_title_lang_selection" >"语言偏好设置"</string>
    <string name="locale_remove_menu" >"移除"</string>
    <string name="add_a_language" >"添加语言"</string>
    <plurals name="dlg_remove_locales_title" formatted="false" >
      <item quantity="other">要移除所选语言吗？</item>
      <item quantity="one">要移除所选语言吗？</item>
    </plurals>
    <string name="dlg_remove_locales_message" >"系统将以其他语言显示文字。"</string>
    <string name="dlg_remove_locales_error_title" >"无法移除所有语言"</string>
    <string name="dlg_remove_locales_error_message" >"请保留至少一种首选语言"</string>
    <string name="locale_not_translated" >"某些应用可能无法以该语言显示"</string>
    <string name="action_drag_label_move_up" >"上移"</string>
    <string name="action_drag_label_move_down" >"下移"</string>
    <string name="action_drag_label_move_top" >"移至顶部"</string>
    <string name="action_drag_label_move_bottom" >"移至底部"</string>
    <string name="action_drag_label_remove" >"移除语言"</string>
    <string name="activity_picker_label" >"选择活动"</string>
    <string name="device_info_label" >"设备信息"</string>
    <string name="display_label" >"屏幕"</string>
    <string name="phone_info_label" product="tablet" >"平板电脑信息"</string>
    <string name="phone_info_label" product="default" >"手机信息"</string>
    <string name="sd_card_settings_label" product="nosdcard" >"USB存储设备"</string>
    <string name="sd_card_settings_label" product="default" >"SD卡"</string>
    <string name="proxy_settings_label" >"代理设置"</string>
    <string name="cancel" >"取消"</string>
    <string name="okay" >"确定"</string>
    <string name="forget" >"取消保存"</string>
    <string name="save" >"保存"</string>
    <string name="done" >"完成"</string>
    <string name="apply" >"应用"</string>
    <string name="settings_label" >"设置"</string>
    <string name="settings_label_launcher" >"设置"</string>
    <string name="settings_shortcut" >"设置快捷方式"</string>
    <string name="activity_list_empty" >"未找到匹配的活动。"</string>
    <string name="airplane_mode" >"飞行模式"</string>
    <string name="radio_controls_title" >"更多"</string>
    <string name="wireless_networks_settings_title" >"无线和网络"</string>
    <string name="radio_controls_summary" >"管理WLAN、蓝牙、飞行模式、移动网络和VPN"</string>
    <string name="cellular_data_title" >"移动数据"</string>
    <string name="calls_title" >"通话"</string>
    <string name="sms_messages_title" >"短信"</string>
    <string name="cellular_data_summary" >"允许使用移动数据网络"</string>
    <string name="allow_data_usage_title" >"允许漫游时使用数据流量"</string>
    <string name="roaming" >"移动数据网络漫游"</string>
    <string name="roaming_enable" >"漫游时连接到移动数据网络服务"</string>
    <string name="roaming_disable" >"漫游时连接到移动数据网络服务"</string>
    <string name="roaming_reenable_message" >"移动数据网络连接已断开，因为您已离开本地网络并关闭了移动数据网络漫游功能。"</string>
    <string name="roaming_turn_it_on_button" >"将其启用"</string>
    <string name="roaming_warning" >"这可能会产生高额费用。"</string>
    <string name="roaming_warning_multiuser" product="tablet" >"如果允许数据漫游，您可能需要支付高昂的漫游费用！\n\n此设置会影响这部平板电脑上的所有用户。"</string>
    <string name="roaming_warning_multiuser" product="default" >"如果允许数据漫游，您可能需要支付高昂的漫游费用！\n\n此设置会影响这部手机上的所有用户。"</string>
    <string name="roaming_reenable_title" >"允许移动数据网络漫游吗？"</string>
    <string name="networks" >"运营商选择"</string>
    <string name="sum_carrier_select" >"选择网络运营商"</string>
    <string name="date_and_time_settings_title" >"日期和时间"</string>
    <string name="date_and_time_settings_title_setup_wizard" >"设置日期和时间"</string>
    <string name="date_and_time_settings_summary" >"设置日期、时间、时区和格式"</string>
    <string name="date_time_auto" >"自动确定日期和时间"</string>
    <string name="date_time_auto_summaryOn" >"使用网络提供的时间"</string>
    <string name="date_time_auto_summaryOff" >"使用网络提供的时间"</string>
    <string name="zone_auto" >"自动确定时区"</string>
    <string name="zone_auto_summaryOn" >"使用网络提供的时区"</string>
    <string name="zone_auto_summaryOff" >"使用网络提供的时区"</string>
    <string name="date_time_24hour_auto" >"自动使用 24 小时制"</string>
    <string name="date_time_24hour_auto_summary" >"使用默认语言区域"</string>
    <string name="date_time_24hour_title" >"24小时制"</string>
    <string name="date_time_24hour" >"使用 24 小时制"</string>
    <string name="date_time_set_time_title" >"时间"</string>
    <string name="date_time_set_time" >"设置时间"</string>
    <string name="date_time_set_timezone_title" >"时区"</string>
    <string name="date_time_set_timezone" >"选择时区"</string>
    <string name="date_time_set_date_title" >"日期"</string>
    <string name="date_time_set_date" >"设置日期"</string>
    <string name="date_time_search_region" >"搜索区域"</string>
    <string name="date_time_select_region" >"区域"</string>
    <string name="date_time_select_zone" >"时区"</string>
    <string name="date_time_set_timezone_in_region" >"<xliff:g id="REGION">%1$s</xliff:g>的时区"</string>
    <string name="date_time_select_fixed_offset_time_zones" >"选择世界协调时间 (UTC) 偏移量"</string>
    <string name="zone_list_menu_sort_alphabetically" >"按字母顺序排序"</string>
    <string name="zone_list_menu_sort_by_timezone" >"按时区排序"</string>
    <string name="zone_change_to_from_dst" >"<xliff:g id="TIME_TYPE">%1$s</xliff:g>开始于 <xliff:g id="TRANSITION_DATE">%2$s</xliff:g>。"</string>
    <string name="zone_info_exemplar_location_and_offset" >"<xliff:g id="EXEMPLAR_LOCATION">%1$s</xliff:g> (<xliff:g id="OFFSET">%2$s</xliff:g>)"</string>
    <string name="zone_info_offset_and_name" >"<xliff:g id="TIME_TYPE">%2$s</xliff:g> (<xliff:g id="OFFSET">%1$s</xliff:g>)"</string>
    <string name="zone_info_footer" >"采用<xliff:g id="OFFSET_AND_NAME">%1$s</xliff:g>。<xliff:g id="DST_TIME_TYPE">%2$s</xliff:g>从 <xliff:g id="TRANSITION_DATE">%3$s</xliff:g>开始。"</string>
    <string name="zone_info_footer_no_dst" >"采用<xliff:g id="OFFSET_AND_NAME">%1$s</xliff:g>。没有夏令时。"</string>
    <string name="zone_time_type_dst" >"夏令时"</string>
    <string name="zone_time_type_standard" >"标准时间"</string>
    <string name="zone_menu_by_region" >"按区域选择"</string>
    <string name="zone_menu_by_offset" >"按世界协调时间 (UTC) 偏移量选择"</string>
    <string name="date_picker_title" >"日期"</string>
    <string name="time_picker_title" >"时间"</string>
    <string name="lock_after_timeout" >"自动锁定"</string>
    <string name="lock_after_timeout_summary" >"休眠<xliff:g id="TIMEOUT_STRING">%1$s</xliff:g>后"</string>
    <string name="lock_immediately_summary_with_exception" >"休眠后立即锁定屏幕（<xliff:g id="TRUST_AGENT_NAME">%1$s</xliff:g>让屏幕保持解锁状态时除外）"</string>
    <string name="lock_after_timeout_summary_with_exception" >"休眠<xliff:g id="TIMEOUT_STRING">%1$s</xliff:g>后（<xliff:g id="TRUST_AGENT_NAME">%2$s</xliff:g>让屏幕保持解锁状态时除外）"</string>
    <string name="show_owner_info_on_lockscreen_label" >"在锁定屏幕上显示机主信息"</string>
    <string name="owner_info_settings_title" >"锁定屏幕消息"</string>
    <string name="security_enable_widgets_title" >"启用微件"</string>
    <string name="security_enable_widgets_disabled_summary" >"已被管理员停用"</string>
    <string name="lockdown_settings_title" >"显示锁定选项"</string>
    <string name="lockdown_settings_summary" >"显示可在锁定屏幕上关闭 Smart Lock、指纹解锁和通知功能的电源按钮选项"</string>
    <string name="owner_info_settings_summary" >"无"</string>
    <string name="owner_info_settings_status" >"<xliff:g id="COUNT_0">%1$d</xliff:g> / <xliff:g id="COUNT_1">%2$d</xliff:g>"</string>
    <string name="owner_info_settings_edit_text_hint" >"例如，小明的 Android 设备。"</string>
    <string name="user_info_settings_title" >"用户信息"</string>
    <string name="show_profile_info_on_lockscreen_label" >"在锁定屏幕上显示个人资料信息"</string>
    <string name="profile_info_settings_title" >"个人资料信息"</string>
    <string name="Accounts_settings_title" >"帐号"</string>
    <string name="location_settings_title" >"位置信息"</string>
    <string name="location_settings_master_switch_title" >"使用位置信息"</string>
    <string name="account_settings_title" >"帐号"</string>
    <string name="security_settings_title" >"安全性和位置信息"</string>
    <string name="encryption_and_credential_settings_title" >"加密与凭据"</string>
    <string name="encryption_and_credential_settings_summary" product="default" >"手机已加密"</string>
    <string name="decryption_settings_summary" product="default" >"手机未加密"</string>
    <string name="encryption_and_credential_settings_summary" product="tablet" >"设备已加密"</string>
    <string name="decryption_settings_summary" product="tablet" >"设备未加密"</string>
    <string name="lockscreen_settings_title" >"锁屏时的偏好设置"</string>
    <string name="security_settings_summary" >"设置我的位置、屏幕解锁、SIM 卡锁定和凭据存储锁定"</string>
    <string name="cdma_security_settings_summary" >"设置我的位置、屏幕解锁和凭据存储锁定"</string>
    <string name="security_passwords_title" >"隐私设置"</string>
    <string name="disabled_by_administrator_summary" >"已被管理员停用"</string>
    <string name="security_status_title" >"安全状态"</string>
    <string name="security_dashboard_summary" >"屏幕锁定、指纹"</string>
    <string name="security_dashboard_summary_no_fingerprint" >"屏幕锁定"</string>
    <string name="security_settings_fingerprint_preference_title" >"指纹"</string>
    <string name="fingerprint_manage_category_title" >"管理指纹"</string>
    <string name="fingerprint_usage_category_title" >"将指纹用于以下用途："</string>
    <string name="fingerprint_add_title" >"添加指纹"</string>
    <string name="fingerprint_enable_keyguard_toggle_title" >"屏幕锁定"</string>
    <plurals name="security_settings_fingerprint_preference_summary" formatted="false" >
      <item quantity="other">已设置 <xliff:g id="COUNT_1">%1$d</xliff:g> 个指纹</item>
      <item quantity="one">已设置 <xliff:g id="COUNT_0">%1$d</xliff:g> 个指纹</item>
    </plurals>
    <string name="security_settings_fingerprint_preference_summary_none" ></string>
    <string name="security_settings_fingerprint_enroll_introduction_title" >"使用指纹解锁"</string>
    <string name="security_settings_fingerprint_enroll_introduction_title_unlock_disabled" >"使用指纹"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message" >"只需触摸指纹传感器即可解锁您的手机、对购买交易进行授权或登录应用。请务必谨慎添加指纹，因为添加的任何指纹都能够用来执行上述操作。\n\n请注意：指纹识别的安全性可能不及安全系数高的图案或 PIN 码。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message_unlock_disabled" >"使用指纹解锁手机或批准购买交易。\n\n请注意：您无法使用指纹来解锁此设备。要了解详情，请与贵单位的管理员联系。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message_setup" >"使用指纹解锁手机或批准购买交易。\n\n请注意：指纹的安全性可能不及安全系数高的图案或 PIN 码。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_cancel" >"取消"</string>
    <string name="security_settings_fingerprint_enroll_introduction_continue" >"继续"</string>
    <string name="security_settings_fingerprint_enroll_introduction_cancel_setup" >"跳过"</string>
    <string name="security_settings_fingerprint_enroll_introduction_continue_setup" >"下一步"</string>
    <string name="setup_fingerprint_enroll_skip_title" >"要跳过指纹设置？"</string>
    <string name="setup_fingerprint_enroll_skip_after_adding_lock_text" >"指纹设置只需片刻时间。如果您跳过此设置，之后可以在设置中添加您的指纹。"</string>
    <string name="lock_screen_intro_skip_title" >"要跳过屏幕锁定吗？"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="tablet" >"系统将不会启用设备保护功能。如果您的平板电脑丢失、被盗或被重置，您将无法防止他人使用此平板电脑。"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="device" >"系统将不会启用设备保护功能。如果您的设备丢失、被盗或被重置，您将无法防止他人使用此设备。"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="default" >"系统将不会启用设备保护功能。如果您的手机丢失、被盗或被重置，您将无法防止他人使用此手机。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="tablet" >"系统将不会启用设备保护功能。如果您的平板电脑丢失或被盗，您将无法防止他人使用此平板电脑。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="device" >"系统将不会启用设备保护功能。如果您的设备丢失或被盗，您将无法防止他人使用此设备。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="default" >"系统将不会启用设备保护功能。如果您的手机丢失或被盗，您将无法防止他人使用此手机。"</string>
    <string name="skip_anyway_button_label" >"仍然跳过"</string>
    <string name="go_back_button_label" >"返回"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_title" >"触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_message" >"指纹传感器位于您手机的背面。请用食指触摸传感器。"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_content_description" >"关于设备和指纹传感器位置的图示说明"</string>
    <string name="security_settings_fingerprint_enroll_dialog_name_label" >"名称"</string>
    <string name="security_settings_fingerprint_enroll_dialog_ok" >"确定"</string>
    <string name="security_settings_fingerprint_enroll_dialog_delete" >"删除"</string>
    <string name="security_settings_fingerprint_enroll_start_title" >"触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_start_message" >"将您的手指放在指纹传感器上，感觉到振动后移开手指"</string>
    <string name="security_settings_fingerprint_enroll_repeat_title" >"移开手指，然后再次触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_repeat_message" >"不时地移开手指，以便传感器更完整地记录下您的指纹"</string>
    <string name="security_settings_fingerprint_enroll_finish_title" >"指纹已添加"</string>
    <string name="security_settings_fingerprint_enroll_finish_message" >"当您看见此图标时，请使用您的指纹进行身份验证或批准购买交易"</string>
    <string name="security_settings_fingerprint_enroll_enrolling_skip" >"以后再说"</string>
    <string name="setup_fingerprint_enroll_enrolling_skip_title" >"要跳过指纹设置？"</string>
    <string name="setup_fingerprint_enroll_enrolling_skip_message" >"您已选择使用指纹作为解锁手机的方式之一。如果您现在跳过这项设置，则以后还需要再进行设置。设置过程只需大约一分钟的时间。"</string>
    <string name="security_settings_fingerprint_enroll_setup_screen_lock" >"设置屏幕锁定"</string>
    <string name="security_settings_fingerprint_enroll_done" >"完成"</string>
    <string name="security_settings_fingerprint_enroll_touch_dialog_title" >"糟糕，这不是传感器"</string>
    <string name="security_settings_fingerprint_enroll_touch_dialog_message" >"请用您的食指触摸位于手机背面的传感器。"</string>
    <string name="security_settings_fingerprint_enroll_error_dialog_title" >"未完成注册"</string>
    <string name="security_settings_fingerprint_enroll_error_timeout_dialog_message" >"指纹注册操作超时，请重试。"</string>
    <string name="security_settings_fingerprint_enroll_error_generic_dialog_message" >"无法注册指纹。请重试或使用其他手指。"</string>
    <string name="fingerprint_enroll_button_add" >"再添加一个"</string>
    <string name="fingerprint_enroll_button_next" >"下一步"</string>
    <string name="security_settings_fingerprint_enroll_disclaimer" >"除了将手机解锁，您还可以在购买物品时和登录应用时使用自己的指纹来验证身份。"<annotation id="url">"了解详情"</annotation></string>
    <string name="security_settings_fingerprint_enroll_disclaimer_lockscreen_disabled" >" 屏幕锁定选项已停用。要了解详情，请与贵单位的管理员联系。"<annotation id="admin_details">"更多详情"</annotation>\n\n"您仍然可以使用指纹对购买交易进行授权以及登录应用。"<annotation id="url">"了解详情"</annotation></string>
    <string name="security_settings_fingerprint_enroll_lift_touch_again" >"移开手指，然后再次触摸传感器"</string>
    <string name="fingerprint_add_max" >"您最多可以添加 <xliff:g id="COUNT">%d</xliff:g> 个指纹"</string>
    <string name="fingerprint_intro_error_max" >"您添加的指纹数量已达到上限"</string>
    <string name="fingerprint_intro_error_unknown" >"无法添加更多的指纹"</string>
    <string name="fingerprint_last_delete_title" >"要移除所有指纹吗？"</string>
    <string name="fingerprint_delete_title" >"移除“<xliff:g id="FINGERPRINT_ID">%1$s</xliff:g>”"</string>
    <string name="fingerprint_delete_message" >"要删除此指纹吗？"</string>
    <string name="fingerprint_last_delete_message" >"您将无法使用指纹来解锁手机、对购买交易进行授权或登录应用"</string>
    <string name="fingerprint_last_delete_message_profile_challenge" >"您将无法使用指纹解锁您的工作资料、对购买交易进行授权或登录工作应用"</string>
    <string name="fingerprint_last_delete_confirm" >"是，移除"</string>
    <string name="confirm_fingerprint_icon_content_description" >"请使用您的指纹以继续。"</string>
    <string name="crypt_keeper_settings_title" >"加密"</string>
    <string name="crypt_keeper_encrypt_title" product="tablet" >"加密平板电脑"</string>
    <string name="crypt_keeper_encrypt_title" product="default" >"加密手机"</string>
    <string name="crypt_keeper_encrypted_summary" >"已加密"</string>
    <string name="crypt_keeper_desc" product="tablet" >"您可以对自己的帐号、设置、下载的应用及其数据、媒体和其他文件进行加密。加密平板电脑后，如果您设置了屏幕锁定（即图案、数字PIN码或密码），那么您必须在每次开机时解锁屏幕，才能将平板电脑解密。除此之外，唯一的解密方法就是恢复出厂设置，但这会清除您所有的数据。\n\n加密过程至少需要1个小时的时间。在开始加密之前，您必须先将电池充满电，并让平板电脑在整个加密过程中保持电源接通状态。如果加密过程出现中断，您将丢失部分或所有数据。"</string>
    <string name="crypt_keeper_desc" product="default" >"您可以对自己的帐号、设置、下载的应用及其数据、媒体和其他文件进行加密。加密手机后，如果您设置了屏幕锁定（即图案、数字PIN码或密码），那么您必须在每次开机时解锁屏幕，才能将手机解密。除此之外，唯一的解密方法就是恢复出厂设置，但这会清除您所有的数据。\n\n加密过程至少需要1个小时的时间。在开始加密之前，您必须先将电池充满电，并让手机在整个加密过程中保持电源接通状态。如果加密过程出现中断，您将丢失部分或所有数据。"</string>
    <string name="crypt_keeper_button_text" product="tablet" >"加密平板电脑"</string>
    <string name="crypt_keeper_button_text" product="default" >"加密手机"</string>
    <string name="crypt_keeper_low_charge_text" >"请为电池充电，然后重试。"</string>
    <string name="crypt_keeper_unplugged_text" >"请插上充电器，然后重试。"</string>
    <string name="crypt_keeper_dialog_need_password_title" >"没有锁屏PIN码或密码"</string>
    <string name="crypt_keeper_dialog_need_password_message" >"您需要先设置锁屏 PIN 码或密码，才能开始加密。"</string>
    <string name="crypt_keeper_confirm_title" >"要加密吗？"</string>
    <string name="crypt_keeper_final_desc" product="tablet" >"加密操作无法撤消，如果您中断该过程，则会丢失数据。加密过程需要1小时或更长时间，在此期间，平板电脑会重新启动数次。"</string>
    <string name="crypt_keeper_final_desc" product="default" >"加密操作无法撤消，如果您中断该过程，则会丢失数据。加密过程需要1小时或更长时间，在此期间，手机会重新启动数次。"</string>
    <string name="crypt_keeper_setup_title" >"正在加密"</string>
    <string name="crypt_keeper_setup_description" product="tablet" >"正在加密您的平板电脑（已完成 <xliff:g id="PERCENT">^1</xliff:g>%），请耐心等待。"</string>
    <string name="crypt_keeper_setup_description" product="default" >"正在加密您的手机（已完成 <xliff:g id="PERCENT">^1</xliff:g>%），请耐心等待。"</string>
    <string name="crypt_keeper_setup_time_remaining" product="tablet" >"您的平板电脑正在加密，请耐心等待。剩余时间：<xliff:g id="DURATION">^1</xliff:g>"</string>
    <string name="crypt_keeper_setup_time_remaining" product="default" >"您的手机正在加密，请耐心等待。剩余时间：<xliff:g id="DURATION">^1</xliff:g>"</string>
    <string name="crypt_keeper_force_power_cycle" product="tablet" >"要解锁您的平板电脑，请将其关机然后再重启。"</string>
    <string name="crypt_keeper_force_power_cycle" product="default" >"要解锁您的手机，请将其关机然后再重启。"</string>
    <string name="crypt_keeper_warn_wipe" >"警告：如果解锁尝试再失败<xliff:g id="COUNT">^1</xliff:g>次，您的设备将被清空！"</string>
    <string name="crypt_keeper_enter_password" >"键入密码"</string>
    <string name="crypt_keeper_failed_title" >"加密失败"</string>
    <string name="crypt_keeper_failed_summary" product="tablet" >"加密过程中断，无法完成。因此，您已无法再使用平板电脑上的数据。\n\n要继续使用您的平板电脑，您需要恢复出厂设置。恢复出厂设置后对平板电脑进行设置时，您可以将之前备份到Google帐号的所有数据恢复到平板电脑中。"</string>
    <string name="crypt_keeper_failed_summary" product="default" >"加密过程中断，无法完成。因此，您已无法再使用手机上的数据。\n\n要继续使用您的手机，您需要恢复出厂设置。恢复出厂设置后对手机进行设置时，您可以将之前备份到Google帐号的所有数据恢复到手机中。"</string>
    <string name="crypt_keeper_data_corrupt_title" >"解密失败"</string>
    <string name="crypt_keeper_data_corrupt_summary" product="tablet" >"您输入的密码正确无误，但遗憾的是，您的数据已损坏。\n\n要继续使用您的平板电脑，您需要将其恢复出厂设置。对恢复出厂设置后的平板电脑进行设置时，您可以恢复之前备份到 Google 帐号的任何数据。"</string>
    <string name="crypt_keeper_data_corrupt_summary" product="default" >"您输入的密码正确无误，但遗憾的是，您的数据已损坏。\n\n要继续使用您的手机，您需要将其恢复出厂设置。对恢复出厂设置后的手机进行设置时，您可以恢复之前备份到 Google 帐号的任何数据。"</string>
    <string name="crypt_keeper_switch_input_method" >"切换输入法"</string>
    <string name="suggested_lock_settings_title" >"保护手机的安全"</string>
    <string name="suggested_lock_settings_summary" product="tablet" >"设置屏幕锁定方式以保护平板电脑"</string>
    <string name="suggested_lock_settings_summary" product="device" >"设置屏幕锁定方式以保护设备"</string>
    <string name="suggested_lock_settings_summary" product="default" >"设置屏幕锁定方式以保护手机"</string>
    <string name="suggested_fingerprint_lock_settings_title" >"添加用于解锁的指纹"</string>
    <string name="suggested_fingerprint_lock_settings_summary" product="tablet" ></string>
    <string name="suggested_fingerprint_lock_settings_summary" product="device" ></string>
    <string name="suggested_fingerprint_lock_settings_summary" product="default" ></string>
    <string name="lock_settings_picker_title" >"选择屏幕锁定方式"</string>
    <string name="lock_settings_picker_title_profile" >"选择工作资料锁屏方式"</string>
    <string name="setup_lock_settings_picker_title" product="tablet" >"保护您的平板电脑"</string>
    <string name="setup_lock_settings_picker_title" product="device" >"保护您的设备"</string>
    <string name="setup_lock_settings_picker_title" product="default" >"为手机启用保护功能"</string>
    <string name="lock_settings_picker_fingerprint_added_security_message" >"为提升安全性，请设置备用屏幕锁定方式"</string>
    <string name="setup_lock_settings_picker_message" product="tablet" >"启用设备保护功能可防止他人在未经您允许的情况下使用此平板电脑。请选择您要使用的屏幕锁定方式。"</string>
    <string name="setup_lock_settings_picker_message" product="device" >"启用设备保护功能可防止他人在未经您允许的情况下使用此设备。请选择您要使用的屏幕锁定方式。"</string>
    <string name="setup_lock_settings_picker_message" product="default" >"启用设备保护功能可防止他人在未经您允许的情况下使用此手机。请选择您要使用的屏幕锁定方式。"</string>
    <string name="lock_settings_picker_fingerprint_message" >"选择您的备用屏幕锁定方式"</string>
    <string name="setup_lock_settings_options_button_label" >"屏幕锁定选项"</string>
    <string name="setup_lock_settings_options_dialog_title" >"屏幕锁定选项"</string>
    <string name="unlock_set_unlock_launch_picker_title" >"屏幕锁定"</string>
    <string name="unlock_set_unlock_launch_picker_summary_lock_immediately" >"<xliff:g id="UNLOCK_METHOD">%1$s</xliff:g> / 休眠后立即启动"</string>
    <string name="unlock_set_unlock_launch_picker_summary_lock_after_timeout" >"<xliff:g id="UNLOCK_METHOD">%1$s</xliff:g> / 休眠 <xliff:g id="TIMEOUT_STRING">%2$s</xliff:g>后启动"</string>
    <string name="unlock_set_unlock_launch_picker_title_profile" >"工作资料屏幕锁定方式"</string>
    <string name="unlock_set_unlock_launch_picker_change_title" >"更改屏幕锁定方式"</string>
    <string name="unlock_set_unlock_launch_picker_change_summary" >"更改或关闭图案、PIN码或密码保护"</string>
    <string name="unlock_set_unlock_launch_picker_enable_summary" >"选择屏幕锁定方式"</string>
    <string name="unlock_set_unlock_off_title" >"无"</string>
    <string name="unlock_set_unlock_off_summary" ></string>
    <string name="unlock_set_unlock_none_title" >"滑动"</string>
    <string name="unlock_set_unlock_none_summary" >"无安全措施"</string>
    <string name="unlock_set_unlock_pattern_title" >"图案"</string>
    <string name="unlock_set_unlock_pattern_summary" >"中等安全性"</string>
    <string name="unlock_set_unlock_pin_title" >"PIN 码"</string>
    <string name="unlock_set_unlock_pin_summary" >"中高等安全性"</string>
    <string name="unlock_set_unlock_password_title" >"密码"</string>
    <string name="unlock_set_unlock_password_summary" >"高安全性"</string>
    <string name="unlock_set_do_later_title" >"以后再说"</string>
    <string name="current_screen_lock" >"当前屏幕锁定设置"</string>
    <string name="fingerprint_unlock_set_unlock_pattern" >"指纹 + 图案"</string>
    <string name="fingerprint_unlock_set_unlock_pin" >"指纹 + PIN 码"</string>
    <string name="fingerprint_unlock_set_unlock_password" >"指纹 + 密码"</string>
    <string name="fingerprint_unlock_skip_fingerprint" >"不设置指纹并继续"</string>
    <string name="fingerprint_unlock_title" >"您可以使用自己的指纹将手机解锁。为了安全起见，要使用此选项，您必须设置备用屏幕锁定方式。"</string>
    <string name="unlock_set_unlock_disabled_summary" >"由于管理员、加密策略或凭据存储的要求，您无法使用此选项"</string>
    <string name="unlock_set_unlock_mode_off" >"无"</string>
    <string name="unlock_set_unlock_mode_none" >"滑动"</string>
    <string name="unlock_set_unlock_mode_pattern" >"图案"</string>
    <string name="unlock_set_unlock_mode_pin" >"PIN 码"</string>
    <string name="unlock_set_unlock_mode_password" >"密码"</string>
    <string name="unlock_setup_wizard_fingerprint_details" >"设置了屏幕锁定后，您还可以在“设置”&gt;“安全”中设置指纹。"</string>
    <string name="unlock_disable_lock_title" >"关闭屏幕锁定"</string>
    <string name="unlock_disable_frp_warning_title" >"要移除设备保护功能吗？"</string>
    <string name="unlock_disable_frp_warning_title_profile" >"要移除个人资料保护功能吗？"</string>
    <string name="unlock_disable_frp_warning_content_pattern" >"移除您的解锁图案后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_fingerprint" >"移除您的解锁图案后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pin" >"移除您的 PIN 码后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_fingerprint" >"移除您的 PIN 码后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_password" >"移除您的密码后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_password_fingerprint" >"移除您的密码后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown" >"移除您的屏幕锁定方式后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_fingerprint" >"移除您的屏幕锁定方式后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_profile" >"移除您的解锁图案后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_fingerprint_profile" >"移除您的解锁图案后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_profile" >"移除您的 PIN 码后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_fingerprint_profile" >"移除您的 PIN 码后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_password_profile" >"移除您的密码后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_password_fingerprint_profile" >"移除您的密码后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁您的资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_profile" >"移除您的屏幕锁定方式后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_fingerprint_profile" >"移除您的屏幕锁定方式后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_ok" >"是，移除"</string>
    <string name="unlock_change_lock_pattern_title" >"更改解锁图案"</string>
    <string name="unlock_change_lock_pin_title" >"更改解锁PIN码"</string>
    <string name="unlock_change_lock_password_title" >"更改解锁密码"</string>
    <string name="lock_failed_attempts_before_wipe" >"请重试。您目前已尝试 <xliff:g id="CURRENT_ATTEMPTS">%1$d</xliff:g> 次，最多可尝试 <xliff:g id="TOTAL_ATTEMPTS">%2$d</xliff:g> 次。"</string>
    <string name="lock_last_attempt_before_wipe_warning_title" >"您的数据将遭到删除"</string>
    <string name="lock_last_pattern_attempt_before_wipe_device" >"如果您下次绘制的解锁图案仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_pin_attempt_before_wipe_device" >"如果您下次输入的 PIN 码仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_password_attempt_before_wipe_device" >"如果您下次输入的密码仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_pattern_attempt_before_wipe_user" >"如果您下次绘制的解锁图案仍然有误，系统将删除此用户"</string>
    <string name="lock_last_pin_attempt_before_wipe_user" >"如果您下次输入的 PIN 码仍然有误，系统将删除此用户"</string>
    <string name="lock_last_password_attempt_before_wipe_user" >"如果您下次输入的密码仍然有误，系统将删除此用户"</string>
    <string name="lock_last_pattern_attempt_before_wipe_profile" >"如果您下次绘制的解锁图案仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_last_pin_attempt_before_wipe_profile" >"如果您下次输入的 PIN 码仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_last_password_attempt_before_wipe_profile" >"如果您下次输入的密码仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_failed_attempts_now_wiping_device" >"错误次数过多。系统将删除此设备上的数据。"</string>
    <string name="lock_failed_attempts_now_wiping_user" >"错误次数过多。系统将删除此用户。"</string>
    <string name="lock_failed_attempts_now_wiping_profile" >"错误次数过多。系统将删除此工作资料和相关数据。"</string>
    <string name="lock_failed_attempts_now_wiping_dialog_dismiss" >"关闭"</string>
    <string name="lockpassword_password_too_short" >"必须至少包含 <xliff:g id="COUNT">%d</xliff:g> 个字符"</string>
    <string name="lockpassword_pin_too_short" >"PIN 码必须至少为 <xliff:g id="COUNT">%d</xliff:g> 位数"</string>
    <string name="lockpassword_continue_label" >"继续"</string>
    <string name="lockpassword_password_too_long" >"必须少于 <xliff:g id="NUMBER">%d</xliff:g> 个字符"</string>
    <string name="lockpassword_pin_too_long" >"必须少于 <xliff:g id="NUMBER">%d</xliff:g> 位数"</string>
    <string name="lockpassword_pin_contains_non_digits" >"只能包含 0-9 的数字"</string>
    <string name="lockpassword_pin_recently_used" >"设备管理员不允许使用最近用过的 PIN 码"</string>
    <string name="lockpassword_pin_blacklisted_by_admin" >"常用 PIN 码已被您的 IT 管理员屏蔽。请尝试一个不同的 PIN 码。"</string>
    <string name="lockpassword_illegal_character" >"密码不得包含无效字符"</string>
    <string name="lockpassword_password_requires_alpha" >"必须包含至少 1 个字母"</string>
    <string name="lockpassword_password_requires_digit" >"必须包含至少 1 个数字"</string>
    <string name="lockpassword_password_requires_symbol" >"必须包含至少 1 个符号"</string>
    <plurals name="lockpassword_password_requires_letters" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个字母</item>
      <item quantity="one">必须包含至少 1 个字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_lowercase" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个小写字母</item>
      <item quantity="one">必须包含至少 1 个小写字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_uppercase" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个大写字母</item>
      <item quantity="one">必须包含至少 1 个大写字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_numeric" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个数字</item>
      <item quantity="one">必须包含至少 1 个数字</item>
    </plurals>
    <plurals name="lockpassword_password_requires_symbols" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个特殊符号</item>
      <item quantity="one">必须包含至少 1 个特殊符号</item>
    </plurals>
    <plurals name="lockpassword_password_requires_nonletter" formatted="false" >
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个非字母字符</item>
      <item quantity="one">必须包含至少 1 个非字母字符</item>
    </plurals>
    <string name="lockpassword_password_recently_used" >"设备管理员不允许使用最近用过的密码"</string>
    <string name="lockpassword_password_blacklisted_by_admin" >"常用密码已被您的 IT 管理员屏蔽。请尝试一个不同的密码。"</string>
    <string name="lockpassword_pin_no_sequential_digits" >"不允许使用以升序、降序或重复序列进行排列的一串数字"</string>
    <string name="lockpassword_confirm_label" >"确认"</string>
    <string name="lockpassword_cancel_label" >"取消"</string>
    <string name="lockpassword_clear_label" >"清除"</string>
    <string name="lockpattern_tutorial_cancel_label" >"取消"</string>
    <string name="lockpattern_tutorial_continue_label" >"下一步"</string>
    <string name="lock_setup" >"设置完毕。"</string>
    <string name="manage_device_admin" >"设备管理应用"</string>
    <string name="number_of_device_admins_none" >"没有运行中的应用"</string>
    <plurals name="number_of_device_admins" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个有效应用</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个有效应用</item>
    </plurals>
    <string name="manage_trust_agents" >"可信代理"</string>
    <string name="disabled_because_no_backup_security" >"要开始使用，请先设置屏幕锁定方式"</string>
    <string name="manage_trust_agents_summary" >"无"</string>
    <plurals name="manage_trust_agents_summary_on" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个可信代理处于有效状态</item>
      <item quantity="one">1 个可信代理处于有效状态</item>
    </plurals>
    <string name="bluetooth_quick_toggle_title" >"蓝牙"</string>
    <string name="bluetooth_quick_toggle_summary" >"打开蓝牙"</string>
    <string name="bluetooth_settings" >"蓝牙"</string>
    <string name="bluetooth_settings_title" >"蓝牙"</string>
    <string name="bluetooth_settings_summary" >"管理连接、设置设备名称和可检测性"</string>
    <string name="bluetooth_pairing_request" >"要与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对吗？"</string>
    <string name="bluetooth_pairing_key_msg" >"蓝牙配对码"</string>
    <string name="bluetooth_enter_passkey_msg" >"输入配对码，然后按回车键"</string>
    <string name="bluetooth_enable_alphanumeric_pin" >"PIN码由字母或符号组成"</string>
    <string name="bluetooth_pin_values_hint" >"通常为 0000 或 1234"</string>
    <string name="bluetooth_pin_values_hint_16_digits" >"必须为 16 位数字"</string>
    <string name="bluetooth_enter_pin_other_device" >"您可能还需要在另一设备上输入此PIN码。"</string>
    <string name="bluetooth_enter_passkey_other_device" >"您可能还需要在另一台设备上输入此密钥。"</string>
    <string name="bluetooth_confirm_passkey_msg" >"要与以下设备配对：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;请确保其显示的配对密钥为：&lt;br&gt;&lt;b&gt;<xliff:g id="PASSKEY">%2$s</xliff:g>&lt;/b&gt;"</string>
    <string name="bluetooth_incoming_pairing_msg" >"来自：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;要与此设备配对吗？"</string>
    <string name="bluetooth_display_passkey_pin_msg" >"要与 <xliff:g id="BOLD1_0">&lt;br&gt;&lt;b&gt;</xliff:g><xliff:g id="DEVICE_NAME">%1$s</xliff:g><xliff:g id="END_BOLD1">&lt;/b&gt;&lt;br&gt;&lt;br&gt;</xliff:g> 设备配对，请在该设备上键入：<xliff:g id="BOLD2_1">&lt;br&gt;&lt;b&gt;</xliff:g><xliff:g id="PASSKEY">%2$s</xliff:g><xliff:g id="END_BOLD2">&lt;/b&gt;</xliff:g>，然后按回车键。"</string>
    <string name="bluetooth_pairing_shares_phonebook" >"允许访问您的通讯录和通话记录"</string>
    <string name="bluetooth_error_title" ></string>
    <string name="bluetooth_connecting_error_message" >"无法连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>。"</string>
    <string name="bluetooth_preference_scan_title" >"扫描设备"</string>
    <string name="bluetooth_search_for_devices" >"刷新"</string>
    <string name="bluetooth_searching_for_devices" >"正在搜索..."</string>
    <string name="bluetooth_preference_device_settings" >"设备设置"</string>
    <string name="bluetooth_preference_paired_dialog_title" >"已配对的设备"</string>
    <string name="bluetooth_preference_paired_dialog_name_label" >"名称"</string>
    <string name="bluetooth_preference_paired_dialog_internet_option" >"互联网连接"</string>
    <string name="bluetooth_preference_paired_dialog_keyboard_option" >"键盘"</string>
    <string name="bluetooth_preference_paired_dialog_contacts_option" >"通讯录和通话记录"</string>
    <string name="bluetooth_pairing_dialog_title" >"要与此设备配对吗？"</string>
    <string name="bluetooth_pairing_dialog_sharing_phonebook_title" >"要共享电话簿吗？"</string>
    <string name="bluetooth_pairing_dialog_contants_request" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>想要使用您的通讯录和通话记录。"</string>
    <string name="bluetooth_pairing_dialog_paring_request" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>想通过蓝牙与您的设备配对。连接后，此设备将可以使用您的通讯录和通话记录。"</string>
    <string name="bluetooth_preference_paired_devices" >"已配对的设备"</string>
    <string name="bluetooth_preference_found_media_devices" >"可用的设备"</string>
    <string name="bluetooth_preference_no_found_devices" >"未找到任何设备"</string>
    <string name="bluetooth_device_context_connect" >"连接"</string>
    <string name="bluetooth_device_context_disconnect" >"断开连接"</string>
    <string name="bluetooth_device_context_pair_connect" >"配对和连接"</string>
    <string name="bluetooth_device_context_unpair" >"取消配对"</string>
    <string name="bluetooth_device_context_disconnect_unpair" >"断开连接和取消配对"</string>
    <string name="bluetooth_device_context_connect_advanced" >"选项…"</string>
    <string name="bluetooth_menu_advanced" >"高级"</string>
    <string name="bluetooth_advanced_titlebar" >"高级蓝牙设置"</string>
    <string name="bluetooth_empty_list_bluetooth_off" >"开启蓝牙后，您的设备可以与附近的其他蓝牙设备通信。"</string>
    <string name="bluetooth_scanning_on_info_message" >"开启蓝牙后，您的设备可以与附近的其他蓝牙设备通信。\n\n为了提升设备的使用体验，应用和服务仍然可以随时扫描附近的设备（即使蓝牙已关闭）。这可用于改进基于位置的功能和服务（举例来说）。您可以在"<annotation id="link">"扫描设置"</annotation>"中更改此设置。"</string>
    <string name="ble_scan_notify_text" >"为了提高位置信息的精确度，系统应用和服务仍然会检测蓝牙设备。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>
    <string name="bluetooth_connect_failed" >"无法连接，请重试。"</string>
    <string name="device_details_title" >"设备详细信息"</string>
    <string name="bluetooth_device_mac_address" >"设备的蓝牙地址：<xliff:g id="ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_unpair_dialog_title" >"要与该设备取消配对吗？"</string>
    <string name="bluetooth_unpair_dialog_body" product="default" >"您的手机将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_body" product="tablet" >"您的平板电脑将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_body" product="device" >"您的设备将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_forget_confirm_button" >"与设备取消配对"</string>
    <string name="bluetooth_connect_specific_profiles_title" >"连接到..."</string>
    <string name="bluetooth_disconnect_a2dp_profile" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与媒体音频断开连接。"</string>
    <string name="bluetooth_disconnect_headset_profile" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与免提音频断开连接。"</string>
    <string name="bluetooth_disconnect_hid_profile" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与输入设备断开连接。"</string>
    <string name="bluetooth_disconnect_pan_user_profile" >"经由<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的互联网连接将会断开。"</string>
    <string name="bluetooth_disconnect_pan_nap_profile" product="tablet" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将断开而不再共享该平板电脑的互联网连接。"</string>
    <string name="bluetooth_disconnect_pan_nap_profile" product="default" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将断开而不再共享该手机的互联网连接。"</string>
    <string name="bluetooth_device_advanced_title" >"已配对的蓝牙设备"</string>
    <string name="bluetooth_device_advanced_online_mode_title" >"连接"</string>
    <string name="bluetooth_device_advanced_online_mode_summary" >"连接到蓝牙设备"</string>
    <string name="bluetooth_device_advanced_profile_header_title" >"用于"</string>
    <string name="bluetooth_device_advanced_rename_device" >"重命名"</string>
    <string name="bluetooth_device_advanced_enable_opp_title" >"允许传入文件"</string>
    <string name="bluetooth_pan_user_profile_summary_connected" >"经由其他设备连接到互联网"</string>
    <string name="bluetooth_pan_nap_profile_summary_connected" >"与其他设备共享该设备的互联网连接"</string>
    <string name="bluetooth_dock_settings" >"基座设置"</string>
    <string name="bluetooth_dock_settings_title" >"将基座用于音频"</string>
    <string name="bluetooth_dock_settings_headset" >"将基座用作免提电话"</string>
    <string name="bluetooth_dock_settings_a2dp" >"用于音乐和媒体"</string>
    <string name="bluetooth_dock_settings_remember" >"记住设置"</string>
    <string name="bluetooth_max_connected_audio_devices_string" >"已连接蓝牙音频设备的数量上限"</string>
    <string name="bluetooth_max_connected_audio_devices_dialog_title" >"选择已连接蓝牙音频设备的数量上限"</string>
    <string name="wifi_display_settings_title" >"投射"</string>
    <string name="wifi_display_enable_menu_item" >"开启无线显示"</string>
    <string name="wifi_display_no_devices_found" >"未在附近找到设备。"</string>
    <string name="wifi_display_status_connecting" >"正在连接"</string>
    <string name="wifi_display_status_connected" >"已连接"</string>
    <string name="wifi_display_status_in_use" >"正在使用中"</string>
    <string name="wifi_display_status_not_available" >"无法使用"</string>
    <string name="wifi_display_details" >"显示设备设置"</string>
    <string name="wifi_display_options_title" >"无线显示选项"</string>
    <string name="wifi_display_options_forget" >"取消保存"</string>
    <string name="wifi_display_options_done" >"完成"</string>
    <string name="wifi_display_options_name" >"名称"</string>
    <string name="wifi_band_24ghz" >"2.4 GHz"</string>
    <string name="wifi_band_5ghz" >"5 GHz"</string>
    <string name="wifi_sign_in_button_text" >"登录"</string>
    <string name="wifi_tap_to_sign_in" >"点按此处即可登录网络"</string>
    <string name="link_speed" >"%1$d Mbps"</string>
    <string name="wifi_ask_enable" >"<xliff:g id="REQUESTER">%s</xliff:g>请求开启 WLAN"</string>
    <string name="wifi_ask_disable" >"<xliff:g id="REQUESTER">%s</xliff:g>请求关闭 WLAN"</string>
    <string name="nfc_quick_toggle_title" >"NFC"</string>
    <string name="nfc_quick_toggle_summary" product="tablet" >"允许平板电脑在接触其他设备时交换数据"</string>
    <string name="nfc_quick_toggle_summary" product="default" >"允许手机在接触其他设备时交换数据"</string>
    <string name="nfc_disclaimer_title" >"开启 NFC"</string>
    <string name="nfc_disclaimer_content" >"NFC 可让此设备和附近的其他设备或目标（例如付款终端、门禁读卡器和互动式广告或标签）交换数据。"</string>
    <string name="android_beam_settings_title" >"Android Beam"</string>
    <string name="android_beam_on_summary" >"已准备好通过 NFC 传输应用内容"</string>
    <string name="android_beam_off_summary" >"关闭"</string>
    <string name="android_beam_disabled_summary" >"无法使用，因为NFC已关闭"</string>
    <string name="android_beam_label" >"Android Beam"</string>
    <string name="android_beam_explained" >"开启此功能后，只需将本设备与另一台支持 NFC 的设备靠在一起，即可将应用内容传输过去。例如，您可以传输浏览器网页、YouTube 视频、联系人信息等等。\n\n您只需将两台设备靠在一起（通常是背靠背），然后点按屏幕确认，应用就会确定传输哪些内容。"</string>
    <string name="wifi_quick_toggle_title" >"WLAN"</string>
    <string name="wifi_quick_toggle_summary" >"打开WLAN"</string>
    <string name="wifi_settings" >"WLAN"</string>
    <string name="wifi_settings_master_switch_title" >"使用 WLAN"</string>
    <string name="wifi_settings_category" >"WLAN设置"</string>
    <string name="wifi_settings_title" >"WLAN"</string>
    <string name="wifi_settings_summary" >"设置和管理无线接入点"</string>
    <string name="wifi_select_network" >"选择WLAN网络"</string>
    <string name="wifi_starting" >"正在打开WLAN…"</string>
    <string name="wifi_stopping" >"正在关闭WLAN…"</string>
    <string name="wifi_error" >"出错"</string>
    <string name="wifi_sap_no_channel_error" >"在此国家/地区无法使用 5 GHz 频段"</string>
    <string name="wifi_in_airplane_mode" >"正处于飞行模式下"</string>
    <string name="wifi_notify_open_networks" >"打开网络通知"</string>
    <string name="wifi_notify_open_networks_summary" >"有可用的高品质公共网络时通知我"</string>
    <string name="wifi_wakeup" >"自动开启 WLAN"</string>
    <string name="wifi_wakeup_summary" >"位于已保存的高品质网络（例如您的家庭网络）附近时自动重新开启 WLAN"</string>
    <string name="wifi_wakeup_summary_no_location" >"无法使用，因为位置信息服务已关闭。请开启"<annotation id="link">"位置信息服务"</annotation>"。"</string>
    <string name="wifi_wakeup_summary_scanning_disabled" >"无法使用，因为 WLAN 搜索功能已关闭"</string>
    <string name="wifi_wakeup_summary_scoring_disabled" >"要使用该功能，请选择一个网络评分服务提供方"</string>
    <string name="wifi_poor_network_detection" >"避开状况不佳的网络"</string>
    <string name="wifi_poor_network_detection_summary" >"仅在 WLAN 互联网连接状况良好时使用 WLAN 网络"</string>
    <string name="wifi_avoid_poor_network_detection_summary" >"仅使用互联网连接状况良好的网络"</string>
    <string name="use_open_wifi_automatically_title" >"连接到开放网络"</string>
    <string name="use_open_wifi_automatically_summary" >"自动连接到高品质的公共网络"</string>
    <string name="use_open_wifi_automatically_summary_scoring_disabled" >"要使用该功能，请选择一个网络评分服务提供方"</string>
    <string name="use_open_wifi_automatically_summary_scorer_unsupported_disabled" >"要使用该功能，请选择一个兼容的网络评分服务提供方"</string>
    <string name="wifi_install_credentials" >"安装证书"</string>
    <string name="wifi_scan_notify_text" >"为了提高位置信息的精确度，应用和服务仍然可以随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>
    <string name="wifi_scan_notify_text_scanning_off" >"要提高位置信息的精确度，请在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中开启 WLAN 扫描功能。"</string>
    <string name="wifi_scan_notify_remember_choice" >"不再显示"</string>
    <string name="wifi_setting_sleep_policy_title" >"在休眠状态下保持WLAN网络连接"</string>
    <string name="wifi_setting_on_during_sleep_title" >"在休眠状态下保持WLAN连接"</string>
    <string name="wifi_setting_sleep_policy_error" >"更改设置时出现问题"</string>
    <string name="wifi_suspend_efficiency_title" >"提高能效"</string>
    <string name="wifi_suspend_optimizations" >"WLAN优化"</string>
    <string name="wifi_suspend_optimizations_summary" >"开启WLAN时尽可能节约电池用量"</string>
    <string name="wifi_limit_optimizations_summary" >"限制WLAN耗电量"</string>
    <string name="wifi_switch_away_when_unvalidated" >"在 WLAN 网络无法连接到互联网时切换到移动数据网络。"</string>
    <string name="wifi_cellular_data_fallback_title" >"自动切换到移动数据网络"</string>
    <string name="wifi_cellular_data_fallback_summary" >"在 WLAN 网络无法连接到互联网时切换到移动数据网络（可能会消耗移动数据流量）。"</string>
    <string name="wifi_add_network" >"添加网络"</string>
    <string name="wifi_configure_settings_preference_title" >"WLAN 偏好设置"</string>
    <string name="wifi_configure_settings_preference_summary_wakeup_on" >"自动重新开启 WLAN"</string>
    <string name="wifi_configure_settings_preference_summary_wakeup_off" >"不自动重新开启 WLAN"</string>
    <string name="wifi_access_points" >"WLAN网络"</string>
    <string name="wifi_menu_more_options" >"更多选项"</string>
    <string name="wifi_menu_p2p" >"WLAN 直连"</string>
    <string name="wifi_menu_scan" >"扫描"</string>
    <string name="wifi_menu_advanced" >"高级"</string>
    <string name="wifi_menu_configure" >"配置"</string>
    <string name="wifi_menu_connect" >"连接到网络"</string>
    <string name="wifi_menu_remember" >"记住网络"</string>
    <string name="wifi_menu_forget" >"取消保存网络"</string>
    <string name="wifi_menu_modify" >"修改网络"</string>
    <string name="wifi_menu_write_to_nfc" >"写入NFC标签"</string>
    <string name="wifi_empty_list_wifi_off" >"要查看可用网络，请打开 WLAN。"</string>
    <string name="wifi_empty_list_wifi_on" >"正在搜索WLAN网络…"</string>
    <string name="wifi_empty_list_user_restricted" >"您无权更改WLAN网络。"</string>
    <string name="wifi_more" >"更多"</string>
    <string name="wifi_setup_wps" >"自动设置 (WPS)"</string>
    <string name="wifi_settings_scanning_required_title" >"要开启 WLAN 扫描功能吗？"</string>
    <string name="wifi_settings_scanning_required_summary" >"要自动开启 WLAN，您必须先开启 WLAN 扫描功能。"</string>
    <string name="wifi_settings_scanning_required_info" >"WLAN 扫描功能可让应用和服务随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="wifi_settings_scanning_required_turn_on" >"开启"</string>
    <string name="wifi_settings_scanning_required_enabled" >"WLAN 扫描功能已开启"</string>
    <string name="wifi_show_advanced" >"高级选项"</string>
    <string name="wifi_advanced_toggle_description_expanded" >"高级选项下拉列表。点按两次即可收起。"</string>
    <string name="wifi_advanced_toggle_description_collapsed" >"高级选项下拉列表。点按两次即可展开。"</string>
    <string name="wifi_ssid" >"网络名称"</string>
    <string name="wifi_ssid_hint" >"输入SSID"</string>
    <string name="wifi_security" >"安全性"</string>
    <string name="wifi_hidden_network" >"隐藏的网络"</string>
    <string name="wifi_hidden_network_warning" >"如果您的路由器没有广播某个网络 ID，但是您想在以后连接到该网络，则可以将该网络设置为隐藏。\n\n此设置可能会带来安全风险，因为您的手机会定期广播其信号以查找该网络。\n\n将网络设置为隐藏将不会更改您的路由器设置。"</string>
    <string name="wifi_signal" >"信号强度"</string>
    <string name="wifi_status" >"状态信息"</string>
    <string name="wifi_speed" >"连接速度"</string>
    <string name="wifi_frequency" >"频率"</string>
    <string name="wifi_ip_address" >"IP 地址"</string>
    <string name="passpoint_label" >"保存方式："</string>
    <string name="passpoint_content" >"<xliff:g id="NAME">%1$s</xliff:g>凭据"</string>
    <string name="wifi_eap_method" >"EAP方法"</string>
    <string name="please_select_phase2" >"阶段2身份验证"</string>
    <string name="wifi_eap_ca_cert" >"CA证书"</string>
    <string name="wifi_eap_domain" >"域名"</string>
    <string name="wifi_eap_user_cert" >"用户证书"</string>
    <string name="wifi_eap_identity" >"身份"</string>
    <string name="wifi_eap_anonymous" >"匿名身份"</string>
    <string name="wifi_password" >"密码"</string>
    <string name="wifi_show_password" >"显示密码"</string>
    <string name="wifi_ap_band_config" >"选择 AP 频段"</string>
    <string name="wifi_ap_choose_auto" >"自动"</string>
    <string name="wifi_ap_choose_2G" >"2.4 GHz 频段"</string>
    <string name="wifi_ap_choose_5G" >"5.0 GHz 频段"</string>
    <string name="wifi_ap_2G" >"2.4 GHz"</string>
    <string name="wifi_ap_5G" >"5.0 GHz"</string>
    <string name="wifi_ap_band_select_one" >"请为 WLAN 热点至少选择一个频段："</string>
    <string name="wifi_ip_settings" >"IP 设置"</string>
    <string name="wifi_shared" >"与其他设备用户共享"</string>
    <string name="wifi_unchanged" >"（未更改）"</string>
    <string name="wifi_unspecified" >"请选择"</string>
    <string name="wifi_multiple_cert_added" >"（已添加多份证书）"</string>
    <string name="wifi_use_system_certs" >"使用系统证书"</string>
    <string name="wifi_do_not_provide_eap_user_cert" >"不提供"</string>
    <string name="wifi_do_not_validate_eap_server" >"不验证"</string>
    <string name="wifi_do_not_validate_eap_server_warning" >"未指定任何证书。您的网络连接将不是私密连接。"</string>
    <string name="wifi_ssid_too_long" >"网络名称太长。"</string>
    <string name="wifi_no_domain_warning" >"必须指定域名。"</string>
    <string name="wifi_wps_available_first_item" >"可使用 WPS"</string>
    <string name="wifi_wps_available_second_item" >" （可使用 WPS）"</string>
    <string name="wifi_wps_nfc_enter_password" >"请输入您的网络密码"</string>
    <string name="wifi_carrier_connect" >"运营商 WLAN 网络"</string>
    <string name="wifi_carrier_content" >"通过<xliff:g id="NAME">%1$s</xliff:g>连接"</string>
    <string name="wifi_scan_always_turnon_message" >"为了提高位置信息精确度以及其他目的，“<xliff:g id="APP_NAME">%1$s</xliff:g>”请求启用网络扫描功能（在关闭了WLAN时也可进行扫描）。\n\n是否对所有需要进行扫描的应用批准这项请求？"</string>
    <string name="wifi_scan_always_turnoff_message" >"要关闭此功能，请转到菜单下的“高级”。"</string>
    <string name="wifi_scan_always_confirm_allow" >"允许"</string>
    <string name="wifi_scan_always_confirm_deny" >"拒绝"</string>
    <string name="wifi_hotspot_title" >"要登录以便连接到网络吗？"</string>
    <string name="wifi_hotspot_message" >"<xliff:g id="APP_NAME">%1$s</xliff:g>要求您必须先在线登录热点，然后才能连接到网络。"</string>
    <string name="wifi_hotspot_connect" >"连接"</string>
    <string name="no_internet_access_text" >"使用此网络将无法访问互联网。要继续保持连接吗？"</string>
    <string name="no_internet_access_remember" >"对于此网络不再询问"</string>
    <string name="lost_internet_access_title" >"此 WLAN 网络无法连接到互联网"</string>
    <string name="lost_internet_access_text" >"当 WLAN 网络连接状况不佳时，您可以切换到移动网络（可能会产生移动数据流量费用）。"</string>
    <string name="lost_internet_access_switch" >"切换到移动网络"</string>
    <string name="lost_internet_access_cancel" >"让 WLAN 保持开启状态"</string>
    <string name="lost_internet_access_persist" >"不再显示"</string>
    <string name="wifi_connect" >"连接"</string>
    <string name="wifi_failed_connect_message" >"无法连接网络"</string>
    <string name="wifi_forget" >"取消保存"</string>
    <string name="wifi_modify" >"修改"</string>
    <string name="wifi_failed_forget_message" >"无法取消保存网络"</string>
    <string name="wifi_save" >"保存"</string>
    <string name="wifi_failed_save_message" >"无法保存网络"</string>
    <string name="wifi_cancel" >"取消"</string>
    <string name="wifi_forget_dialog_title" >"要取消保存网络吗？"</string>
    <string name="wifi_forget_dialog_message" >"系统将删除此网络的所有密码"</string>
    <string name="wifi_saved_access_points_titlebar" >"已保存的网络"</string>
    <plurals name="wifi_saved_access_points_summary" formatted="false" >
      <item quantity="other">%d 个网络</item>
      <item quantity="one">1 个网络</item>
    </plurals>
    <string name="wifi_advanced_titlebar" >"高级WLAN"</string>
    <string name="wifi_advanced_mac_address_title" >"MAC 地址"</string>
    <string name="wifi_advanced_ip_address_title" >"IP 地址"</string>
    <string name="wifi_details_title" >"网络详情"</string>
    <string name="wifi_details_subnet_mask" >"子网掩码"</string>
    <string name="wifi_details_dns" >"DNS"</string>
    <string name="wifi_details_ipv6_address_header" >"IPv6 地址"</string>
    <string name="wifi_saved_access_points_label" >"已保存的网络"</string>
    <string name="wifi_advanced_settings_label" >"IP 设置"</string>
    <string name="wifi_advanced_not_available" >"此用户无法查看或修改 WLAN 网络高级设置"</string>
    <string name="wifi_ip_settings_menu_save" >"保存"</string>
    <string name="wifi_ip_settings_menu_cancel" >"取消"</string>
    <string name="wifi_ip_settings_invalid_ip_address" >"请输入有效的IP地址。"</string>
    <string name="wifi_ip_settings_invalid_gateway" >"请键入有效的网关地址。"</string>
    <string name="wifi_ip_settings_invalid_dns" >"请输入有效的DNS地址。"</string>
    <string name="wifi_ip_settings_invalid_network_prefix_length" >"键入长度在 0 到 32 之间的网络前缀。"</string>
    <string name="wifi_dns1" >"DNS 1"</string>
    <string name="wifi_dns2" >"DNS 2"</string>
    <string name="wifi_gateway" >"网关"</string>
    <string name="wifi_network_prefix_length" >"网络前缀长度"</string>
    <string name="wifi_p2p_settings_title" >"WLAN 直连"</string>
    <string name="wifi_p2p_device_info" >"设备信息"</string>
    <string name="wifi_p2p_persist_network" >"记住该连接"</string>
    <string name="wifi_p2p_menu_search" >"搜索设备"</string>
    <string name="wifi_p2p_menu_searching" >"正在搜索…"</string>
    <string name="wifi_p2p_menu_rename" >"重命名设备"</string>
    <string name="wifi_p2p_peer_devices" >"对等设备"</string>
    <string name="wifi_p2p_remembered_groups" >"已保存的群组"</string>
    <string name="wifi_p2p_failed_connect_message" >"无法连接。"</string>
    <string name="wifi_p2p_failed_rename_message" >"无法重命名设备。"</string>
    <string name="wifi_p2p_disconnect_title" >"要断开连接吗？"</string>
    <string name="wifi_p2p_disconnect_message" >"如果断开连接，您与<xliff:g id="PEER_NAME">%1$s</xliff:g>的连接将中断。"</string>
    <string name="wifi_p2p_disconnect_multiple_message" >"如果断开连接，您与<xliff:g id="PEER_NAME">%1$s</xliff:g>和另外 <xliff:g id="PEER_COUNT">%2$s</xliff:g> 台设备的连接将中断。"</string>
    <string name="wifi_p2p_cancel_connect_title" >"取消邀请？"</string>
    <string name="wifi_p2p_cancel_connect_message" >"要取消连接<xliff:g id="PEER_NAME">%1$s</xliff:g>的邀请吗？"</string>
    <string name="wifi_p2p_delete_group_message" >"取消保存此群组？"</string>
    <string name="wifi_hotspot_checkbox_text" >"WLAN 热点"</string>
    <string name="wifi_hotspot_off_subtext" >"目前没有与其他设备共享互联网连接或内容"</string>
    <string name="wifi_hotspot_tethering_on_subtext" product="tablet" >"正在通过热点共享此平板电脑的互联网连接"</string>
    <string name="wifi_hotspot_tethering_on_subtext" product="default" >"正在通过热点共享此手机的互联网连接"</string>
    <string name="wifi_hotspot_on_local_only_subtext" >"应用正在共享内容。要共享互联网连接，请先关闭热点，然后重新打开"</string>
    <string name="wifi_hotspot_no_password_subtext" >"未设置密码"</string>
    <string name="wifi_hotspot_name_title" >"热点名称"</string>
    <string name="wifi_hotspot_name_summary_connecting" >"正在打开<xliff:g id="WIFI_HOTSPOT_NAME">%1$s</xliff:g>…"</string>
    <string name="wifi_hotspot_name_summary_connected" >"其他设备可以连接到<xliff:g id="WIFI_HOTSPOT_NAME">%1$s</xliff:g>"</string>
    <string name="wifi_hotspot_password_title" >"热点密码"</string>
    <string name="wifi_hotspot_ap_band_title" >"AP 频段"</string>
    <string name="wifi_hotspot_footer_info_regular" >"使用热点创建 WLAN 网络供其他设备使用。热点会使用您的移动数据连接提供互联网连接。这可能会产生额外的移动数据流量费用。"</string>
    <string name="wifi_hotspot_footer_info_local_only" >"应用可以通过创建热点，与附近的设备共享内容。"</string>
    <string name="wifi_hotspot_auto_off_title" >"自动关闭热点"</string>
    <string name="wifi_hotspot_auto_off_summary" >"如果未连接任何设备，WLAN 热点将关闭"</string>
    <string name="wifi_tether_starting" >"正在打开热点..."</string>
    <string name="wifi_tether_stopping" >"正在关闭热点..."</string>
    <string name="wifi_tether_enabled_subtext" >"<xliff:g id="NETWORK_SSID">%1$s</xliff:g> 已连接"</string>
    <string name="wifi_tether_failed_subtext" >"便携式WLAN热点错误"</string>
    <string name="wifi_tether_configure_ap_text" >"设置WLAN热点"</string>
    <string name="wifi_hotspot_configure_ap_text" >"WLAN热点设置"</string>
    <string name="wifi_hotspot_configure_ap_text_summary" >"AndroidAP WPA2 PSK 热点"</string>
    <string name="wifi_tether_configure_ssid_default" >"Android热点"</string>
    <string name="wifi_tether_disabled_by_airplane" >"无法使用，因为已开启飞行模式"</string>
    <string name="wifi_calling_settings_title" >"WLAN 通话"</string>
    <string name="wifi_calling_suggestion_title" >"通过 WLAN 扩大通话覆盖范围"</string>
    <string name="wifi_calling_suggestion_summary" >"开启 WLAN 通话功能以扩大通话覆盖范围"</string>
    <string name="wifi_calling_mode_title" >"通话偏好设置"</string>
    <string name="wifi_calling_mode_dialog_title" >"WLAN 通话模式"</string>
    <string name="wifi_calling_roaming_mode_title" >"漫游偏好设置"</string>
    <!-- no translation found for wifi_calling_roaming_mode_summary (8642014873060687717) -->
    <skip />
    <string name="wifi_calling_roaming_mode_dialog_title" >"漫游偏好设置"</string>
  <string-array name="wifi_calling_mode_choices">
    <item >"首选 WLAN"</item>
    <item >"首选移动数据网络"</item>
    <item >"仅限 WLAN"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_v2">
    <item >"WLAN"</item>
    <item >"移动数据"</item>
    <item >"仅限 WLAN"</item>
  </string-array>
  <string-array name="wifi_calling_mode_values">
    <item >"2"</item>
    <item >"1"</item>
    <item >"0"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_without_wifi_only">
    <item >"首选 WLAN"</item>
    <item >"首选移动数据网络"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_v2_without_wifi_only">
    <item >"WLAN"</item>
    <item >"移动数据"</item>
  </string-array>
  <string-array name="wifi_calling_mode_values_without_wifi_only">
    <item >"2"</item>
    <item >"1"</item>
  </string-array>
    <string name="wifi_calling_off_explanation" >"开启“WLAN 通话”功能后，您的手机可根据您的偏好设置，通过 WLAN 网络或您的运营商网络通话（具体取决于哪个网络信号较强）。开启此功能之前，请先向您的运营商咨询收费情况及其他详情。"</string>
    <string name="wifi_calling_off_explanation_2" ></string>
    <string name="emergency_address_title" >"紧急联系地址"</string>
    <string name="emergency_address_summary" >"当您通过 WLAN 网络拨打紧急呼救电话时，系统会判定您位于这个位置"</string>
    <string name="private_dns_help_message" ><annotation id="url">"详细了解"</annotation>"私人 DNS 功能"</string>
    <string name="wifi_calling_pref_managed_by_carrier" >"这项设置由运营商管理"</string>
    <string name="wifi_calling_settings_activation_instructions" >"请启用 WLAN 通话功能"</string>
    <string name="wifi_calling_turn_on" >"请开启 WLAN 通话功能"</string>
    <string name="wifi_calling_not_supported" >"%1$s不支持 WLAN 通话功能"</string>
    <string name="carrier" >"运营商"</string>
    <string name="display_settings_title" >"显示"</string>
    <string name="sound_settings" >"声音"</string>
    <string name="all_volume_title" >"音量"</string>
    <string name="musicfx_title" >"音乐效果"</string>
    <string name="ring_volume_title" >"铃声音量"</string>
    <string name="vibrate_in_silent_title" >"静音时振动"</string>
    <string name="notification_sound_title" >"默认通知提示音"</string>
    <string name="incoming_call_volume_title" >"铃声"</string>
    <string name="notification_volume_title" >"通知"</string>
    <string name="checkbox_notification_same_as_incoming_call" >"将来电音量用作通知音量"</string>
    <string name="home_work_profile_not_supported" >"不支持工作资料"</string>
    <string name="notification_sound_dialog_title" >"默认通知提示音"</string>
    <string name="media_volume_title" >"媒体"</string>
    <string name="media_volume_summary" >"设置音乐和视频的音量"</string>
    <string name="alarm_volume_title" >"闹钟"</string>
    <string name="dock_settings_summary" >"附加基座的音频设置"</string>
    <string name="dtmf_tone_enable_title" >"拨号键盘触摸音效"</string>
    <string name="sound_effects_enable_title" >"点按提示音"</string>
    <string name="lock_sounds_enable_title" >"锁屏提示音"</string>
    <string name="haptic_feedback_enable_title" >"点按时振动"</string>
    <string name="audio_record_proc_title" >"降噪"</string>
    <string name="volume_media_description" >"音乐、视频、游戏和其他媒体"</string>
    <string name="volume_ring_description" >"铃声和通知"</string>
    <string name="volume_notification_description" >"通知"</string>
    <string name="volume_alarm_description" >"闹钟"</string>
    <string name="volume_ring_mute" >"使铃声和通知静音"</string>
    <string name="volume_media_mute" >"使音乐和其他媒体静音"</string>
    <string name="volume_notification_mute" >"使通知静音"</string>
    <string name="volume_alarm_mute" >"使闹钟静音"</string>
    <string name="dock_settings" >"基座"</string>
    <string name="dock_settings_title" >"基座设置"</string>
    <string name="dock_audio_settings_title" >"音频"</string>
    <string name="dock_audio_summary_desk" >"附加桌面基座的设置"</string>
    <string name="dock_audio_summary_car" >"附加车载基座的设置"</string>
    <string name="dock_audio_summary_none" product="tablet" >"平板电脑未插入基座"</string>
    <string name="dock_audio_summary_none" product="default" >"手机未插入基座"</string>
    <string name="dock_audio_summary_unknown" >"附加基座的设置"</string>
    <string name="dock_not_found_title" >"未找到基座"</string>
    <string name="dock_not_found_text" product="tablet" >"您需要先将平板电脑插入基座，才能设置基座音频。"</string>
    <string name="dock_not_found_text" product="default" >"您需要先将手机插入基座，才能设置基座音频。"</string>
    <string name="dock_sounds_enable_title" >"插入基座提示音"</string>
    <string name="dock_sounds_enable_summary_on" product="tablet" >"平板电脑插入基座或拔出时发出声音"</string>
    <string name="dock_sounds_enable_summary_on" product="default" >"手机插入基座或拔出时发出声音"</string>
    <string name="dock_sounds_enable_summary_off" product="tablet" >"平板电脑插入基座或拔出时不发出声音"</string>
    <string name="dock_sounds_enable_summary_off" product="default" >"手机插入基座或拔出时不发出声音"</string>
    <string name="account_settings" >"帐号"</string>
    <string name="accessibility_category_work" >"工作资料帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="accessibility_category_personal" >"个人资料帐号"</string>
    <string name="accessibility_work_account_title" >"工作帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="accessibility_personal_account_title" >"个人帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="search_settings" >"搜索"</string>
    <string name="display_settings" >"显示"</string>
    <string name="accelerometer_title" >"自动旋转屏幕"</string>
    <string name="color_mode_title" >"颜色"</string>
    <string name="color_mode_option_natural" >"自然色"</string>
    <string name="color_mode_option_boosted" >"效果增强"</string>
    <string name="color_mode_option_saturated" >"饱和色"</string>
    <string name="color_mode_option_automatic" >"自动"</string>
    <string name="color_mode_summary_natural" >"仅使用准确色彩"</string>
    <string name="color_mode_summary_automatic" >"在鲜明和准确色彩之间进行调整"</string>
    <string name="accelerometer_summary_on" product="tablet" >"旋转平板电脑时自动改变浏览模式"</string>
    <string name="accelerometer_summary_on" product="default" >"旋转手机时自动改变显示方向"</string>
    <string name="accelerometer_summary_off" product="tablet" >"旋转平板电脑时自动切换浏览模式"</string>
    <string name="accelerometer_summary_off" product="default" >"旋转手机时自动改变显示方向"</string>
    <string name="brightness" >"亮度"</string>
    <string name="brightness_title" >"亮度"</string>
    <string name="brightness_summary" >"调整屏幕亮度"</string>
    <string name="auto_brightness_title" >"自动调节亮度"</string>
    <string name="auto_brightness_summary" >"根据环境光线情况优化亮度"</string>
    <string name="auto_brightness_summary_off" >"关闭"</string>
    <string name="auto_brightness_summary_very_low" >"偏好的亮度为“很低”"</string>
    <string name="auto_brightness_summary_low" >"偏好的亮度为“低”"</string>
    <string name="auto_brightness_summary_default" >"默认使用偏好的亮度"</string>
    <string name="auto_brightness_summary_high" >"偏好的亮度为“高”"</string>
    <string name="auto_brightness_summary_very_high" >"偏好的亮度为“很高”"</string>
    <string name="auto_brightness_off_title" >"关闭"</string>
    <string name="auto_brightness_very_low_title" >"很低"</string>
    <string name="auto_brightness_low_title" >"低"</string>
    <string name="auto_brightness_default_title" >"默认"</string>
    <string name="auto_brightness_high_title" >"高"</string>
    <string name="auto_brightness_very_high_title" >"很高"</string>
    <string name="auto_brightness_subtitle" >"您偏好的亮度"</string>
    <string name="auto_brightness_off_summary" >"不根据环境光线情况调整亮度"</string>
    <string name="auto_brightness_very_high_summary" >"耗电量更高"</string>
    <string name="auto_brightness_disclaimer" >"根据环境光线情况优化亮度。开启此功能后，您仍然可以暂时调整亮度。"</string>
    <string name="auto_brightness_description" >"您的屏幕亮度将根据您的环境和活动自动调节。您可以手动移动滑块，以帮助“自动调节亮度”功能了解您偏好的亮度。"</string>
    <string name="night_display_title" >"夜间模式"</string>
    <string name="night_display_text" >"夜间模式会将您的屏幕色调调为琥珀色，可让您在光线昏暗的环境下更舒适地查看屏幕或阅读文字，并有助您入睡。"</string>
    <string name="night_display_auto_mode_title" >"排定时间"</string>
    <string name="night_display_auto_mode_never" >"无"</string>
    <string name="night_display_auto_mode_custom" >"在设定的时间开启"</string>
    <string name="night_display_auto_mode_twilight" >"在日落到日出期间开启"</string>
    <string name="night_display_start_time_title" >"开始时间"</string>
    <string name="night_display_end_time_title" >"结束时间"</string>
    <string name="night_display_status_title" >"状态"</string>
    <string name="night_display_temperature_title" >"浓度"</string>
    <string name="night_display_summary_off" >"关闭 / <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_summary_off_auto_mode_never" >"一律不自动开启"</string>
    <string name="night_display_summary_off_auto_mode_custom" >"将在<xliff:g id="ID_1">%1$s</xliff:g>自动开启"</string>
    <string name="night_display_summary_off_auto_mode_twilight" >"将在日落时自动开启"</string>
    <string name="night_display_summary_on" >"开启 / <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_summary_on_auto_mode_never" >"一律不自动关闭"</string>
    <string name="night_display_summary_on_auto_mode_custom" >"将在<xliff:g id="ID_1">%1$s</xliff:g>自动关闭"</string>
    <string name="night_display_summary_on_auto_mode_twilight" >"将在日出时自动关闭"</string>
    <string name="night_display_activation_on_manual" >"立即开启"</string>
    <string name="night_display_activation_off_manual" >"立即关闭"</string>
    <string name="night_display_activation_on_twilight" >"保持开启状态，直到日出"</string>
    <string name="night_display_activation_off_twilight" >"保持关闭状态，直到日落"</string>
    <string name="night_display_activation_on_custom" >"保持开启状态，直到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_activation_off_custom" >"保持关闭状态，直到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="screen_timeout" >"休眠"</string>
    <string name="screen_timeout_title" >"屏幕关闭"</string>
    <string name="screen_timeout_summary" >"无操作<xliff:g id="TIMEOUT_DESCRIPTION">%1$s</xliff:g>后"</string>
    <string name="wallpaper_settings_title" >"壁纸"</string>
    <string name="wallpaper_settings_summary_default" >"默认"</string>
    <string name="wallpaper_settings_summary_custom" >"自定义"</string>
    <string name="wallpaper_suggestion_title" >"更换壁纸"</string>
    <string name="wallpaper_suggestion_summary" >"对屏幕进行个性化设置"</string>
    <string name="wallpaper_settings_fragment_title" >"选择壁纸来源"</string>
    <string name="screensaver_settings_title" >"屏保"</string>
    <string name="screensaver_settings_summary_either_long" >"充电或插入基座时"</string>
    <string name="screensaver_settings_summary_either_short" >"以上任一情况"</string>
    <string name="screensaver_settings_summary_sleep" >"充电时"</string>
    <string name="screensaver_settings_summary_dock" >"插入基座时"</string>
    <string name="screensaver_settings_summary_never" >"一律不"</string>
    <string name="screensaver_settings_summary_off" >"关闭"</string>
    <string name="screensaver_settings_disabled_prompt" >"要控制手机在插入基座时和/或休眠状态下的行为，请开启屏保功能。"</string>
    <string name="screensaver_settings_when_to_dream" >"启用时间"</string>
    <string name="screensaver_settings_current" >"当前的屏保"</string>
    <string name="screensaver_settings_dream_start" >"立即启动"</string>
    <string name="screensaver_settings_button" >"设置"</string>
    <string name="automatic_brightness" >"自动调整亮度"</string>
    <string name="lift_to_wake_title" >"拿起设备时唤醒"</string>
    <string name="ambient_display_screen_title" >"主动显示"</string>
    <string name="ambient_display_screen_summary_always_on" >"一律开启/耗电量更高"</string>
    <string name="ambient_display_screen_summary_notifications" >"新通知"</string>
    <string name="ambient_display_category_triggers" >"何时显示"</string>
    <string name="doze_title" >"新通知"</string>
    <string name="doze_summary" >"收到通知时唤醒屏幕"</string>
    <string name="doze_always_on_title" >"始终开启"</string>
    <string name="doze_always_on_summary" >"显示时间、通知图标及其他信息。耗电量更高。"</string>
    <string name="title_font_size" >"字体大小"</string>
    <string name="short_summary_font_size" >"放大或缩小文字"</string>
    <string name="sim_lock_settings" >"SIM 卡锁定设置"</string>
    <string name="sim_lock_settings_category" >"SIM 卡锁定"</string>
    <string name="sim_lock_settings_summary_off" >"关闭"</string>
    <string name="sim_lock_settings_summary_on" >"已锁定"</string>
    <string name="sim_lock_settings_title" >"SIM 卡锁定方式"</string>
    <string name="sim_pin_toggle" >"锁定 SIM 卡"</string>
    <string name="sim_lock_on" product="tablet" >"需要输入PIN码才能使用平板电脑"</string>
    <string name="sim_lock_on" product="default" >"需要输入 PIN 码才能使用手机"</string>
    <string name="sim_lock_off" product="tablet" >"需要输入PIN码才能使用平板电脑"</string>
    <string name="sim_lock_off" product="default" >"需要输入 PIN 码才能使用手机"</string>
    <string name="sim_pin_change" >"更改 SIM 卡 PIN 码"</string>
    <string name="sim_enter_pin" >"SIM 卡 PIN 码"</string>
    <string name="sim_enable_sim_lock" >"锁定 SIM 卡"</string>
    <string name="sim_disable_sim_lock" >"解锁SIM卡"</string>
    <string name="sim_enter_old" >"旧 SIM 卡 PIN 码"</string>
    <string name="sim_enter_new" >"新 SIM 卡 PIN 码"</string>
    <string name="sim_reenter_new" >"重新输入新的PIN码"</string>
    <string name="sim_change_pin" >"SIM 卡 PIN 码"</string>
    <string name="sim_bad_pin" >"PIN 码不正确"</string>
    <string name="sim_pins_dont_match" >"PIN码不匹配"</string>
    <string name="sim_change_failed" >"无法更改PIN码。\n输入的PIN码可能不正确。"</string>
    <string name="sim_change_succeeded" >"已成功更改 SIM 卡 PIN 码"</string>
    <string name="sim_lock_failed" >"无法更改 SIM 卡锁定状态。\nPIN 码可能不正确。"</string>
    <string name="sim_enter_ok" >"确定"</string>
    <string name="sim_enter_cancel" >"取消"</string>
    <string name="sim_multi_sims_title" >"找到多张SIM卡"</string>
    <string name="sim_multi_sims_summary" >"选择您想用于移动数据网络的 SIM 卡。"</string>
    <string name="sim_change_data_title" >"要更改用于数据网络的 SIM 卡吗？"</string>
    <string name="sim_change_data_message" >"要将用于移动数据网络的 SIM 卡从“<xliff:g id="OLD_SIM">%2$s</xliff:g>”改为“<xliff:g id="NEW_SIM">%1$s</xliff:g>”吗？"</string>
    <string name="sim_preferred_title" >"要更新首选 SIM 卡吗？"</string>
    <string name="sim_preferred_message" >"“<xliff:g id="NEW_SIM">%1$s</xliff:g>”是此设备上唯一的 SIM 卡。您要将此 SIM 卡用于移动数据网络、通话和收发短信吗？"</string>
    <string name="wrong_pin_code_pukked" >"SIM 卡 PIN 码不正确，您现在必须联系运营商为您解锁设备。"</string>
    <plurals name="wrong_pin_code" formatted="false" >
      <item quantity="other">SIM 卡 PIN 码不正确，您还可尝试 <xliff:g id="NUMBER_1">%d</xliff:g> 次。</item>
      <item quantity="one">SIM 卡 PIN 码不正确，您还可尝试 <xliff:g id="NUMBER_0">%d</xliff:g> 次。如果仍不正确，则需要联系运营商帮您解锁设备。</item>
    </plurals>
    <string name="pin_failed" >"SIM 卡 PIN 码操作失败！"</string>
    <string name="device_info_settings" product="tablet" >"平板电脑状态"</string>
    <string name="device_info_settings" product="default" >"手机状态"</string>
    <string name="system_update_settings_list_item_title" >"系统更新"</string>
    <string name="system_update_settings_list_item_summary" ></string>
    <string name="firmware_version" >"Android 版本"</string>
    <string name="firmware_title" >"Android"</string>
    <string name="security_patch" >"Android 安全补丁程序级别"</string>
    <string name="model_info" >"型号"</string>
    <string name="model_summary" >"型号：%1$s"</string>
    <string name="hardware_info" >"型号和硬件"</string>
    <string name="hardware_revision" >"硬件版本"</string>
    <string name="fcc_equipment_id" >"设备 ID"</string>
    <string name="baseband_version" >"基带版本"</string>
    <string name="kernel_version" >"内核版本"</string>
    <string name="build_number" >"版本号"</string>
    <string name="device_info_not_available" >"无法获取"</string>
    <string name="device_status_activity_title" >"状态信息"</string>
    <string name="device_status" >"状态信息"</string>
    <string name="device_status_summary" product="tablet" >"电池状态、网络状态和其他信息"</string>
    <string name="device_status_summary" product="default" >"电话号码、信号等"</string>
    <string name="storage_settings" >"存储"</string>
    <string name="storage_usb_settings" >"存储"</string>
    <string name="storage_settings_title" >"存储设置"</string>
    <string name="storage_settings_summary" product="nosdcard" >"卸载USB存储设备，查看可用存储设备"</string>
    <string name="storage_settings_summary" product="default" >"卸载SD卡，查看可用存储设备"</string>
    <string name="imei_multi_sim" >"IMEI（SIM 卡插槽 %1$d）"</string>
    <string name="status_number" product="tablet" >"移动目录编号 (MDN)"</string>
    <string name="status_number" product="default" >"手机号码"</string>
    <string name="status_number_sim_slot" product="tablet" >"MDN（SIM 卡插槽 %1$d）"</string>
    <string name="status_number_sim_slot" product="default" >"电话号码（SIM 卡插槽 %1$d）"</string>
    <string name="status_number_sim_status" product="tablet" >"SIM 卡上的 MDN"</string>
    <string name="status_number_sim_status" product="default" >"SIM 卡上的电话号码"</string>
    <string name="status_min_number" >"MIN"</string>
    <string name="status_msid_number" >"MSID"</string>
    <string name="status_prl_version" >"PRL 版本"</string>
    <string name="meid_multi_sim" >"MEID（SIM 卡插槽 %1$d）"</string>
    <string name="status_meid_number" >"MEID"</string>
    <string name="status_icc_id" >"ICCID"</string>
    <string name="status_data_network_type" >"移动数据网络类型"</string>
    <string name="status_voice_network_type" >"移动语音网络类型"</string>
    <string name="status_latest_area_info" >"运营商信息"</string>
    <string name="status_data_state" >"移动网络状态"</string>
    <string name="status_esim_id" >"EID"</string>
    <string name="status_service_state" >"服务状态"</string>
    <string name="status_signal_strength" >"信号强度"</string>
    <string name="status_roaming" >"漫游"</string>
    <string name="status_operator" >"网络"</string>
    <string name="status_wifi_mac_address" >"WLAN MAC 地址"</string>
    <string name="status_bt_address" >"蓝牙地址"</string>
    <string name="status_serial_number" >"序列号"</string>
    <string name="status_up_time" >"已开机时间"</string>
    <string name="status_awake_time" >"唤醒时间"</string>
    <string name="internal_memory" >"内部存储空间"</string>
    <string name="sd_memory" product="nosdcard" >"USB存储设备"</string>
    <string name="sd_memory" product="default" >"SD卡"</string>
    <string name="memory_available" >"可用空间"</string>
    <string name="memory_available_read_only" >"可用空间（只读）"</string>
    <string name="memory_size" >"总容量"</string>
    <string name="memory_calculating_size" >"正在计算..."</string>
    <string name="memory_apps_usage" >"应用和应用数据"</string>
    <string name="memory_media_usage" >"媒体"</string>
    <string name="memory_downloads_usage" >"下载内容"</string>
    <string name="memory_dcim_usage" >"图片、视频"</string>
    <string name="memory_music_usage" >"音频（音乐、铃声、播客等）"</string>
    <string name="memory_media_misc_usage" >"其他文件"</string>
    <string name="memory_media_cache_usage" >"缓存数据"</string>
    <string name="sd_eject" product="nosdcard" >"卸载共享存储设备"</string>
    <string name="sd_eject" product="default" >"卸载SD卡"</string>
    <string name="sd_eject_summary" product="nosdcard" >"卸载内部USB存储设备"</string>
    <string name="sd_eject_summary" product="default" >"需要先卸载SD卡，然后才能将其安全移除"</string>
    <string name="sd_insert_summary" product="nosdcard" >"插入要装载的USB存储设备"</string>
    <string name="sd_insert_summary" product="default" >"插入SD卡进行安装"</string>
    <string name="sd_mount" product="nosdcard" >"装载USB存储设备"</string>
    <string name="sd_mount" product="default" >"安装SD卡"</string>
    <string name="sd_mount_summary" product="nosdcard" ></string>
    <string name="sd_mount_summary" product="default" ></string>
    <string name="sd_format" product="nosdcard" >"格式化USB存储设备"</string>
    <string name="sd_format" product="default" >"格式化SD卡"</string>
    <string name="sd_format_summary" product="nosdcard" >"清除内部USB存储设备中的全部数据，例如音乐和照片"</string>
    <string name="sd_format_summary" product="default" >"清除SD卡中的全部数据，例如音乐和照片"</string>
    <string name="memory_clear_cache_title" >"是否清除缓存数据？"</string>
    <string name="memory_clear_cache_message" >"此操作会清除所有应用的缓存数据。"</string>
    <string name="mtp_ptp_mode_summary" >"已开启MTP或PTP功能"</string>
    <string name="dlg_confirm_unmount_title" product="nosdcard" >"要卸载USB存储设备吗？"</string>
    <string name="dlg_confirm_unmount_title" product="default" >"要卸载SD卡吗？"</string>
    <string name="dlg_confirm_unmount_text" product="nosdcard" >"如果卸载该USB存储设备，您当前使用的某些应用会停止运行，并且在您重新装载该设备前可能都无法使用。"</string>
    <string name="dlg_confirm_unmount_text" product="default" >"如果卸载该SD卡，您当前使用的某些应用会停止，并且在您重新装载该SD卡前，这些应用可能都无法使用。"</string>
    <string name="dlg_error_unmount_title" product="nosdcard" ></string>
    <string name="dlg_error_unmount_title" product="default" ></string>
    <string name="dlg_error_unmount_text" product="nosdcard" >"无法卸载USB存储设备，请稍后重试。"</string>
    <string name="dlg_error_unmount_text" product="default" >"无法卸载SD卡，请稍后重试。"</string>
    <string name="unmount_inform_text" product="nosdcard" >"系统将会卸载USB存储设备。"</string>
    <string name="unmount_inform_text" product="default" >"系统将会卸载SD卡。"</string>
    <string name="sd_ejecting_title" >"正在卸载"</string>
    <string name="sd_ejecting_summary" >"正在卸载"</string>
    <string name="storage_low_title" >"存储空间不足"</string>
    <string name="storage_low_summary" >"某些系统功能（如同步）可能无法正常使用。请尝试删除或取消在本地保存某些内容（如应用数据或媒体内容），以便释放存储空间。"</string>
    <string name="storage_menu_rename" >"重命名"</string>
    <string name="storage_menu_mount" >"装载"</string>
    <string name="storage_menu_unmount" >"弹出"</string>
    <string name="storage_menu_format" >"格式化"</string>
    <string name="storage_menu_format_public" >"格式化为便携式存储设备"</string>
    <string name="storage_menu_format_private" >"格式化为内部存储设备"</string>
    <string name="storage_menu_migrate" >"迁移数据"</string>
    <string name="storage_menu_forget" >"取消保存"</string>
    <string name="storage_menu_set_up" >"设置"</string>
    <string name="storage_menu_explore" >"浏览"</string>
    <string name="storage_menu_free" >"释放空间"</string>
    <string name="storage_menu_manage" >"管理存储空间"</string>
    <string name="storage_title_usb" >"USB计算机连接"</string>
    <string name="usb_connection_category" >"连接方式"</string>
    <string name="usb_mtp_title" >"媒体设备(MTP)"</string>
    <string name="usb_mtp_summary" >"让您可以在Windows上传输媒体文件，或在Mac上使用Android文件传输应用来传输文件（请参见www.android.com/filetransfer）"</string>
    <string name="usb_ptp_title" >"相机(PTP)"</string>
    <string name="usb_ptp_summary" >"可让您使用相机软件传输照片，并在不支持MTP的计算机上传输任何文件"</string>
    <string name="usb_midi_title" >"MIDI"</string>
    <string name="usb_midi_summary" >"让支持 MIDI 的应用通过 USB 与您计算机上的 MIDI 软件搭配使用。"</string>
    <string name="storage_other_users" >"其他用户"</string>
    <string name="storage_internal_title" >"内部存储设备"</string>
    <string name="storage_external_title" >"便携式存储设备"</string>
    <string name="storage_volume_summary" >"已使用 <xliff:g id="USED">%1$s</xliff:g>（共 <xliff:g id="TOTAL">%2$s</xliff:g>）"</string>
    <string name="storage_size_large" >"<xliff:g id="NUMBER">^1</xliff:g>"<small><small>" <xliff:g id="UNIT">^2</xliff:g>"</small></small>""</string>
    <string name="storage_volume_used" >"（共 <xliff:g id="TOTAL">%1$s</xliff:g>）"</string>
    <string name="storage_volume_used_total" >"共使用 <xliff:g id="TOTAL">%1$s</xliff:g>"</string>
    <string name="storage_mount_success" >"<xliff:g id="NAME">%1$s</xliff:g>已装载"</string>
    <string name="storage_mount_failure" >"无法装载<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_unmount_success" >"<xliff:g id="NAME">%1$s</xliff:g>已安全弹出"</string>
    <string name="storage_unmount_failure" >"无法安全弹出<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_format_success" >"已将<xliff:g id="NAME">%1$s</xliff:g>格式化"</string>
    <string name="storage_format_failure" >"无法格式化<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_rename_title" >"重命名存储设备"</string>
    <string name="storage_dialog_unmounted" >"此<xliff:g id="NAME_0">^1</xliff:g>已安全弹出，但仍在卡槽内。\n\n要使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行装载。"</string>
    <string name="storage_dialog_unmountable" >"此<xliff:g id="NAME_0">^1</xliff:g>已损坏。\n\n要使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行设置。"</string>
    <string name="storage_dialog_unsupported" >"此设备不支持该<xliff:g id="NAME_0">^1</xliff:g>。\n\n要在此设备上使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行设置。"</string>
    <string name="storage_internal_format_details" >"格式化之后，您就可以在其他设备上使用此<xliff:g id="NAME_0">^1</xliff:g>了。\n\n此<xliff:g id="NAME_1">^1</xliff:g>上的所有数据都将被清空，建议您先将数据备份。\n\n"<b>"备份照片和其他媒体文件"</b>\n"将您的媒体文件移动到此设备上的备用存储设备，或使用 USB 线传输到计算机上。\n\n"<b>"备份应用"</b>\n"存储在此<xliff:g id="NAME_6">^1</xliff:g>上的所有应用都将被卸载，且这些应用的数据也将一并被清空。要保留这些应用，请将它们移到此设备上的备用存储设备。"</string>
    <string name="storage_internal_unmount_details" ><b>"当您弹出此<xliff:g id="NAME_0">^1</xliff:g>后，存储在其中的应用将会停止运行，而且存储在其中的媒体文件也将无法使用，除非您重新插入设备。"</b>\n\n"此<xliff:g id="NAME_1">^1</xliff:g>已经过格式化，只能在这台设备上使用，因此在任何其他设备上均将无法使用。"</string>
    <string name="storage_internal_forget_details" >"要使用此<xliff:g id="NAME">^1</xliff:g>中包含的应用、照片或数据，请将其重新插入。\n\n另外，如果该存储设备无法使用，您可以选择取消保存此设备。\n\n如果您选择取消保存，那么该设备中包含的所有数据均将永久丢失。\n\n尽管您以后可以重新安装这些应用，但存储在此设备上的这些应用数据将会丢失。"</string>
    <string name="storage_internal_forget_confirm_title" >"要取消保存<xliff:g id="NAME">^1</xliff:g>吗？"</string>
    <string name="storage_internal_forget_confirm" >"此<xliff:g id="NAME">^1</xliff:g>上存储的所有应用、照片和数据将会永久丢失。"</string>
    <string name="storage_detail_apps" >"应用"</string>
    <string name="storage_detail_images" >"图片"</string>
    <string name="storage_detail_videos" >"视频"</string>
    <string name="storage_detail_audio" >"音频"</string>
    <string name="storage_detail_cached" >"缓存数据"</string>
    <string name="storage_detail_other" >"其他"</string>
    <string name="storage_detail_system" >"系统"</string>
    <string name="storage_detail_explore" >"浏览<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_detail_dialog_other" >"“其他”包括应用所保存的共享文件、通过互联网或蓝牙下载的文件、Android 文件等。\n\n要查看此<xliff:g id="NAME">^1</xliff:g>中的可见内容，请点按“浏览”。"</string>
    <string name="storage_detail_dialog_system" >"“系统”中包含用于运行 Android <xliff:g id="VERSION">%s</xliff:g> 的文件"</string>
    <string name="storage_detail_dialog_user" >"<xliff:g id="USER_0">^1</xliff:g>可能保存了照片、音乐、应用或其他数据（占用了 <xliff:g id="SIZE">^2</xliff:g> 的存储空间）。\n\n要查看详情，请切换至<xliff:g id="USER_1">^1</xliff:g>。"</string>
    <string name="storage_wizard_init_title" >"设置您的<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_init_external_title" >"用作便携式存储设备"</string>
    <string name="storage_wizard_init_external_summary" >"用于在设备之间移动照片和其他媒体文件。"</string>
    <string name="storage_wizard_init_internal_title" >"用作内部存储设备"</string>
    <string name="storage_wizard_init_internal_summary" >"仅用于存储这台设备上的内容，包括应用和照片。为了防止此存储设备在其他设备上使用，您需要先对其进行格式化。"</string>
    <string name="storage_wizard_format_confirm_title" >"格式化为内部存储设备"</string>
    <string name="storage_wizard_format_confirm_body" >"<xliff:g id="NAME_0">^1</xliff:g>必须进行格式化，以确保安全。\n\n格式化之后，此<xliff:g id="NAME_1">^1</xliff:g>将只能在这台设备上使用。\n\n"<b>"格式化操作会清空当前存储在<xliff:g id="NAME_2">^1</xliff:g>上的所有数据。"</b>"为避免丢失数据，建议您先将数据备份。"</string>
    <string name="storage_wizard_format_confirm_public_title" >"格式化为便携式存储设备"</string>
    <string name="storage_wizard_format_confirm_public_body" >"<xliff:g id="NAME_0">^1</xliff:g>必须进行格式化。\n\n"<b>"格式化操作会清空当前存储在<xliff:g id="NAME_1">^1</xliff:g>上的所有数据。"</b>"为避免丢失数据，建议您先将数据备份。"</string>
    <string name="storage_wizard_format_confirm_next" >"清空并格式化"</string>
    <string name="storage_wizard_format_progress_title" >"正在格式化<xliff:g id="NAME">^1</xliff:g>…"</string>
    <string name="storage_wizard_format_progress_body" >"请勿在<xliff:g id="NAME">^1</xliff:g>进行格式化时将其移除。"</string>
    <string name="storage_wizard_migrate_title" >"将数据移动到新的存储设备"</string>
    <string name="storage_wizard_migrate_body" >"您可以将自己的照片、文件和部分应用转移到这个新<xliff:g id="NAME">^1</xliff:g>上。\n\n转移操作大约需要 <xliff:g id="TIME">^2</xliff:g>，完成后将腾出 <xliff:g id="SIZE">^3</xliff:g> 的内部存储空间。在转移过程中，部分应用将无法正常运行。"</string>
    <string name="storage_wizard_migrate_now" >"立即移动"</string>
    <string name="storage_wizard_migrate_later" >"稍后移动"</string>
    <string name="storage_wizard_migrate_confirm_title" >"立即移动数据"</string>
    <string name="storage_wizard_migrate_confirm_body" ><b>"该移动操作需要花费大约 <xliff:g id="TIME">^1</xliff:g>，完成后将在<xliff:g id="NAME">^3</xliff:g>上腾出 <xliff:g id="SIZE">^2</xliff:g> 的空间。"</b></string>
    <string name="storage_wizard_migrate_confirm_next" >"移动"</string>
    <string name="storage_wizard_migrate_progress_title" >"正在移动数据…"</string>
    <string name="storage_wizard_migrate_details" >"在移动数据的过程中：\n• 请勿移除相应的<xliff:g id="NAME">^1</xliff:g>。\n• 某些应用将无法正常运行。\n• 需确保设备电量充足。"</string>
    <string name="storage_wizard_ready_title" >"您的<xliff:g id="NAME">^1</xliff:g>已经可以使用了"</string>
    <string name="storage_wizard_ready_external_body" >"您的<xliff:g id="NAME">^1</xliff:g>已设置完毕，可用于存储照片和其他媒体文件了。"</string>
    <string name="storage_wizard_ready_internal_body" >"新的<xliff:g id="NAME">^1</xliff:g>可以使用了。\n\n要将照片、文件和应用数据移动到此设备，请转到“设置”&gt;“存储”。"</string>
    <string name="storage_wizard_move_confirm_title" >"移动<xliff:g id="APP">^1</xliff:g>"</string>
    <string name="storage_wizard_move_confirm_body" >"将<xliff:g id="APP">^1</xliff:g>及其相关数据移动到<xliff:g id="NAME_0">^2</xliff:g>仅需几分钟时间。在移动操作完成前，您将无法使用该应用。\n\n请勿在移动过程中移除该<xliff:g id="NAME_1">^2</xliff:g>。"</string>
    <string name="storage_wizard_move_unlock" >"您必须解锁用户“<xliff:g id="APP">^1</xliff:g>”，才能移动数据。"</string>
    <string name="storage_wizard_move_progress_title" >"正在移动<xliff:g id="APP">^1</xliff:g>…"</string>
    <string name="storage_wizard_move_progress_body" >"请勿在移动过程中移除该<xliff:g id="NAME">^1</xliff:g>。\n\n在移动操作完成前，您将无法使用此设备上的<xliff:g id="APP">^2</xliff:g>应用。"</string>
    <string name="storage_wizard_move_progress_cancel" >"取消移动"</string>
    <string name="storage_wizard_slow_body" >"此<xliff:g id="NAME_0">^1</xliff:g>似乎运行缓慢。\n\n您可以继续使用，但应用移动到此处后可能无法流畅运行，而且数据传输可能会花费很长时间。\n\n建议您使用运行速度更快的<xliff:g id="NAME_1">^1</xliff:g>来提升性能。"</string>
    <string name="storage_wizard_init_v2_title" >"您要如何使用此<xliff:g id="NAME">^1</xliff:g>？"</string>
    <string name="storage_wizard_init_v2_internal_title" product="tablet" >"用作额外的平板电脑存储空间"</string>
    <string name="storage_wizard_init_v2_internal_summary" product="tablet" >"仅限此平板电脑上的应用、文件和媒体"</string>
    <string name="storage_wizard_init_v2_internal_action" product="tablet" >"平板电脑存储空间"</string>
    <string name="storage_wizard_init_v2_internal_title" product="default" >"用作额外的手机存储空间"</string>
    <string name="storage_wizard_init_v2_internal_summary" product="default" >"仅限此手机上的应用、文件和媒体"</string>
    <string name="storage_wizard_init_v2_internal_action" product="default" >"手机存储空间"</string>
    <string name="storage_wizard_init_v2_or" >"或"</string>
    <string name="storage_wizard_init_v2_external_title" >"用作便携式存储设备"</string>
    <string name="storage_wizard_init_v2_external_summary" >"可用于在设备之间转移文件和媒体"</string>
    <string name="storage_wizard_init_v2_external_action" >"便携式存储设备"</string>
    <string name="storage_wizard_init_v2_later" >"稍后设置"</string>
    <string name="storage_wizard_format_confirm_v2_title" >"要将此<xliff:g id="NAME">^1</xliff:g>格式化吗？"</string>
    <string name="storage_wizard_format_confirm_v2_body" >"此<xliff:g id="NAME_0">^1</xliff:g>需要进行格式化才能存储应用、文件和媒体。\n\n格式化操作将会清空<xliff:g id="NAME_1">^2</xliff:g>上的现有内容。为避免内容丢失，请将内容备份到另一个<xliff:g id="NAME_2">^3</xliff:g>或设备中。"</string>
    <string name="storage_wizard_format_confirm_v2_action" >"将<xliff:g id="NAME">^1</xliff:g>格式化"</string>
    <string name="storage_wizard_migrate_v2_title" >"要将内容移至<xliff:g id="NAME">^1</xliff:g>吗？"</string>
    <string name="storage_wizard_migrate_v2_body" product="tablet" >"您可以将文件、媒体和特定应用移至此<xliff:g id="NAME">^1</xliff:g>。\n\n这项移动操作将释放 <xliff:g id="SIZE">^2</xliff:g> 的平板电脑存储空间，大约需要 <xliff:g id="DURATION">^3</xliff:g>的时间完成。"</string>
    <string name="storage_wizard_migrate_v2_body" product="default" >"您可以将文件、媒体和特定应用移至此<xliff:g id="NAME">^1</xliff:g>。\n\n这项移动操作将释放 <xliff:g id="SIZE">^2</xliff:g> 的手机存储空间，大约需要 <xliff:g id="DURATION">^3</xliff:g>的时间完成。"</string>
    <string name="storage_wizard_migrate_v2_checklist" >"移动期间："</string>
    <string name="storage_wizard_migrate_v2_checklist_media" >"请勿移除<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_migrate_v2_checklist_apps" >"部分应用将无法运行"</string>
    <string name="storage_wizard_migrate_v2_checklist_battery" product="tablet" >"请将此平板电脑保持在充电状态"</string>
    <string name="storage_wizard_migrate_v2_checklist_battery" product="default" >"请将此手机保持在充电状态"</string>
    <string name="storage_wizard_migrate_v2_now" >"移动内容"</string>
    <string name="storage_wizard_migrate_v2_later" >"稍后再移动内容"</string>
    <string name="storage_wizard_migrate_progress_v2_title" >"正在移动内容…"</string>
    <string name="storage_wizard_slow_v2_title" >"<xliff:g id="NAME">^1</xliff:g>运行缓慢"</string>
    <string name="storage_wizard_slow_v2_body" >"您仍可使用此<xliff:g id="NAME_0">^1</xliff:g>，但它的运行速度可能会很慢。\n\n此<xliff:g id="NAME_1">^2</xliff:g>上存储的应用可能无法正常运行，而且内容转移操作可能会花费很长时间。\n\n建议您使用运行速度更快的<xliff:g id="NAME_2">^3</xliff:g>，或者使用此<xliff:g id="NAME_3">^4</xliff:g>作为便携式存储设备。"</string>
    <string name="storage_wizard_slow_v2_start_over" >"重新开始"</string>
    <string name="storage_wizard_slow_v2_continue" >"继续"</string>
    <string name="storage_wizard_ready_v2_external_body" >"您可以将内容移至<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_ready_v2_internal_body" >"要将内容移至<xliff:g id="NAME">^1</xliff:g>，请依次转到"<b>"“设置”&gt;“存储”"</b></string>
    <string name="storage_wizard_ready_v2_internal_moved_body" >"您的内容已移至<xliff:g id="NAME_0">^1</xliff:g>。\n\n要管理此<xliff:g id="NAME_1">^2</xliff:g>，请依次转到"<b>"“设置”&gt;“存储”"</b>"。"</string>
    <string name="battery_status_title" >"电池状态"</string>
    <string name="battery_level_title" >"电池电量"</string>
    <string name="apn_settings" >"APN"</string>
    <string name="apn_edit" >"修改接入点"</string>
    <string name="apn_not_set" >"未设置"</string>
    <string name="apn_name" >"名称"</string>
    <string name="apn_apn" >"APN"</string>
    <string name="apn_http_proxy" >"代理"</string>
    <string name="apn_http_port" >"端口"</string>
    <string name="apn_user" >"用户名"</string>
    <string name="apn_password" >"密码"</string>
    <string name="apn_server" >"服务器"</string>
    <string name="apn_mmsc" >"MMSC"</string>
    <string name="apn_mms_proxy" >"彩信代理"</string>
    <string name="apn_mms_port" >"彩信端口"</string>
    <string name="apn_mcc" >"MCC"</string>
    <string name="apn_mnc" >"MNC"</string>
    <string name="apn_auth_type" >"身份验证类型"</string>
    <string name="apn_auth_type_none" >"无"</string>
    <string name="apn_auth_type_pap" >"PAP"</string>
    <string name="apn_auth_type_chap" >"CHAP"</string>
    <string name="apn_auth_type_pap_chap" >"PAP 或 CHAP"</string>
    <string name="apn_type" >"APN 类型"</string>
    <string name="apn_protocol" >"APN 协议"</string>
    <string name="apn_roaming_protocol" >"APN 漫游协议"</string>
    <string name="carrier_enabled" >"APN 启用/停用"</string>
    <string name="carrier_enabled_summaryOn" >"APN 已启用"</string>
    <string name="carrier_enabled_summaryOff" >"未启用 APN"</string>
    <string name="bearer" >"承载系统"</string>
    <string name="mvno_type" >"MVNO 类型"</string>
    <string name="mvno_match_data" >"MVNO 值"</string>
    <string name="menu_delete" >"删除 APN"</string>
    <string name="menu_new" >"新建 APN"</string>
    <string name="menu_save" >"保存"</string>
    <string name="menu_cancel" >"放弃"</string>
    <string name="error_title" ></string>
    <string name="error_name_empty" >"“名称”字段不能为空。"</string>
    <string name="error_apn_empty" >"APN 不能为空。"</string>
    <string name="error_mcc_not3" >"MCC 字段必须为 3 位数。"</string>
    <string name="error_mnc_not23" >"MNC 字段必须为 2 位数或 3 位数。"</string>
    <string name="error_adding_apn_type" >"运营商不允许添加“%s”类型的 APN。"</string>
    <string name="restore_default_apn" >"正在恢复默认 APN 设置。"</string>
    <string name="menu_restore" >"重置为默认设置"</string>
    <string name="restore_default_apn_completed" >"已重置默认APN设置。"</string>
    <string name="reset_dashboard_title" >"重置选项"</string>
    <string name="reset_dashboard_summary" >"网络、应用或设备可以重置"</string>
    <string name="reset_network_title" >"重置 WLAN、移动数据网络和蓝牙设置"</string>
    <string name="reset_network_desc" >"此操作会重置所有网络设置，包括：\n\n"<li>"WLAN"</li>\n<li>"移动数据网络"</li>\n<li>"蓝牙"</li></string>
    <string name="reset_esim_title" >"一并重置 eSIM 卡"</string>
    <string name="reset_esim_desc" >"清空手机上的所有 eSIM 卡。您必须与运营商联系才能重新下载 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="reset_network_button_text" >"重置设置"</string>
    <string name="reset_network_final_desc" >"要重置所有网络设置吗？此操作无法撤消！"</string>
    <string name="reset_network_final_button_text" >"重置设置"</string>
    <string name="reset_network_confirm_title" >"要重置网络设置吗？"</string>
    <string name="network_reset_not_available" >"此用户无权重置网络设置"</string>
    <string name="reset_network_complete_toast" >"网络设置已重置"</string>
    <string name="reset_esim_error_title" >"无法重置 eSIM 卡"</string>
    <string name="reset_esim_error_msg" >"出现错误，无法重置 eSIM 卡。"</string>
    <string name="master_clear_title" >"清除所有数据（恢复出厂设置）"</string>
    <string name="master_clear_short_title" >"清除所有数据（恢复出厂设置）"</string>
    <string name="master_clear_desc" product="tablet" >"此操作会清除您平板电脑"<b>"内部存储设备"</b>"中的所有数据，包括：\n\n"<li>"您的Google帐号"</li>\n<li>"系统和应用的数据和设置"</li>\n<li>"下载的应用"</li></string>
    <string name="master_clear_desc" product="default" >"此操作会清除您手机"<b>"内部存储空间"</b>"中的所有数据，包括：\n\n"<li>"您的 Google 帐号"</li>\n<li>"系统和应用的数据和设置"</li>\n<li>"已下载的应用"</li></string>
    <string name="master_clear_accounts" product="default" >\n\n"目前，您已登录以下帐号：\n"</string>
    <string name="master_clear_other_users_present" product="default" >\n\n"此设备上目前还有其他用户。\n"</string>
    <string name="master_clear_desc_also_erases_external" ><li>"音乐"</li>\n<li>"照片"</li>\n<li>"其他的用户数据"</li></string>
    <string name="master_clear_desc_also_erases_esim" ><li>"eSIM 卡"</li></string>
    <string name="master_clear_desc_no_cancel_mobile_plan" >\n\n"这样并不会取消您的移动服务套餐。"</string>
    <string name="master_clear_desc_erase_external_storage" product="nosdcard" >\n\n"要清除音乐、照片和其他用户数据，请清空该 "<b>"USB存储设备"</b>"。"</string>
    <string name="master_clear_desc_erase_external_storage" product="default" >\n\n"要清除音乐、图片和其他用户数据，您需要清空该 "<b>"SD卡"</b>"。"</string>
    <string name="erase_external_storage" product="nosdcard" >"格式化USB存储设备"</string>
    <string name="erase_external_storage" product="default" >"格式化SD卡"</string>
    <string name="erase_external_storage_description" product="nosdcard" >"清除该内部USB存储设备中的全部数据，例如音乐或照片"</string>
    <string name="erase_external_storage_description" product="default" >"清除SD卡中的全部数据，例如音乐或照片"</string>
    <string name="erase_esim_storage" >"清空 eSIM 卡"</string>
    <string name="erase_esim_storage_description" product="default" >"清空手机上的所有 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="erase_esim_storage_description" product="tablet" >"清空平板电脑上的所有 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="master_clear_button_text" product="tablet" >"恢复平板电脑出厂设置"</string>
    <string name="master_clear_button_text" product="default" >"恢复手机出厂设置"</string>
    <string name="master_clear_final_desc" >"要清空您的所有个人信息和下载的应用吗？此操作无法撤消！"</string>
    <string name="master_clear_final_button_text" >"清除全部内容"</string>
    <string name="master_clear_failed" >"“系统清除”服务不可用，因此未执行恢复出厂设置操作。"</string>
    <string name="master_clear_confirm_title" >"要恢复出厂设置吗？"</string>
    <string name="master_clear_not_available" >"此用户无权恢复出厂设置"</string>
    <string name="master_clear_progress_title" >"正在清除"</string>
    <string name="master_clear_progress_text" >"请稍候…"</string>
    <string name="call_settings_title" >"通话设置"</string>
    <string name="call_settings_summary" >"设置语音信箱、来电转接、来电等待和本机号码显示"</string>
    <string name="tether_settings_title_usb" >"USB 网络共享"</string>
    <string name="tether_settings_title_wifi" >"便携式热点"</string>
    <string name="tether_settings_title_bluetooth" >"蓝牙网络共享"</string>
    <string name="tether_settings_title_usb_bluetooth" >"网络共享"</string>
    <string name="tether_settings_title_all" >"热点和网络共享"</string>
    <string name="tether_settings_summary_hotspot_on_tether_on" >"已开启热点、网络共享"</string>
    <string name="tether_settings_summary_hotspot_on_tether_off" >"已开启热点"</string>
    <string name="tether_settings_summary_hotspot_off_tether_on" >"网络共享"</string>
    <string name="tether_settings_disabled_on_data_saver" >"当流量节省程序开启时，无法使用网络共享功能或便携式热点"</string>
    <string name="usb_title" >"USB"</string>
    <string name="usb_tethering_button_text" >"USB 网络共享"</string>
    <string name="usb_tethering_subtext" product="default" >"通过 USB 共享手机的互联网连接"</string>
    <string name="usb_tethering_subtext" product="tablet" >"通过 USB 共享平板电脑的互联网连接"</string>
    <string name="bluetooth_tether_checkbox_text" >"蓝牙网络共享"</string>
    <string name="bluetooth_tethering_subtext" product="tablet" >"通过蓝牙共享平板电脑的互联网连接"</string>
    <string name="bluetooth_tethering_subtext" product="default" >"通过蓝牙共享手机的互联网连接"</string>
    <string name="bluetooth_tethering_off_subtext_config" >"通过蓝牙共享该<xliff:g id="DEVICE_NAME">%1$d</xliff:g>的互联网连接"</string>
    <string name="bluetooth_tethering_overflow_error" >"无法与 <xliff:g id="MAXCONNECTION">%1$d</xliff:g> 台以上的设备共享网络。"</string>
    <string name="bluetooth_untether_blank" >"即将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的网络共享。"</string>
    <string name="tethering_footer_info" >"使用热点和网络共享功能，让其他设备能通过您的移动数据网络连接到互联网。应用还可以通过创建热点，与附近的设备共享内容。"</string>
    <string name="tethering_help_button_text" >"帮助"</string>
    <string name="network_settings_title" >"移动网络"</string>
    <string name="manage_mobile_plan_title" >"手机套餐"</string>
    <string name="sms_application_title" >"短信应用"</string>
    <string name="sms_change_default_dialog_title" >"要更改短信应用吗？"</string>
    <string name="sms_change_default_dialog_text" >"要使用“<xliff:g id="NEW_APP">%1$s</xliff:g>”取代“<xliff:g id="CURRENT_APP">%2$s</xliff:g>”作为您的短信应用吗？"</string>
    <string name="sms_change_default_no_previous_dialog_text" >"要使用“<xliff:g id="NEW_APP">%s</xliff:g>”作为您的短信应用吗？"</string>
    <string name="network_scorer_picker_title" >"网络评分服务提供方"</string>
    <string name="network_scorer_picker_none_preference" >"无"</string>
    <string name="network_scorer_change_active_dialog_title" >"要更改WLAN助手吗？"</string>
    <string name="network_scorer_change_active_dialog_text" >"不再使用<xliff:g id="CURRENT_APP">%2$s</xliff:g>，而改用<xliff:g id="NEW_APP">%1$s</xliff:g>来管理您的网络连接吗？"</string>
    <string name="network_scorer_change_active_no_previous_dialog_text" >"要使用<xliff:g id="NEW_APP">%s</xliff:g>管理您的网络连接吗？"</string>
    <string name="mobile_unknown_sim_operator" >"未知的SIM卡运营商"</string>
    <string name="mobile_no_provisioning_url" >"<xliff:g id="OPERATOR">%1$s</xliff:g>没有任何已知的配置网站"</string>
    <string name="mobile_insert_sim_card" >"请插入SIM卡，然后重新启动"</string>
    <string name="mobile_connect_to_internet" >"请连接到互联网"</string>
    <string name="location_title" >"我的位置"</string>
    <string name="managed_profile_location_switch_title" >"工作资料位置信息"</string>
    <string name="location_app_level_permissions" >"应用级权限"</string>
    <string name="location_category_recent_location_requests" >"最近的位置信息请求"</string>
    <string name="location_recent_location_requests_see_all" >"查看全部"</string>
    <string name="location_no_recent_apps" >"最近没有任何应用申请使用位置信息"</string>
    <string name="location_category_location_services" >"位置信息服务"</string>
    <string name="location_high_battery_use" >"高电耗"</string>
    <string name="location_low_battery_use" >"低电耗"</string>
    <string name="location_scanning_screen_title" >"扫描"</string>
    <string name="location_scanning_wifi_always_scanning_title" >"WLAN 扫描"</string>
    <string name="location_scanning_wifi_always_scanning_description" >"允许应用和服务随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="location_scanning_bluetooth_always_scanning_title" >"蓝牙扫描"</string>
    <string name="location_scanning_bluetooth_always_scanning_description" >"允许应用和服务随时扫描附近的设备（即使蓝牙已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="location_network_based" >"WLAN和移动网络位置信息"</string>
    <string name="location_neighborhood_level" >"允许应用使用Google位置信息服务更快地大致了解您所在的位置。系统将收集匿名位置数据并将其发送给Google。"</string>
    <string name="location_neighborhood_level_wifi" >"通过WLAN确定位置"</string>
    <string name="location_gps" >"GPS卫星定位"</string>
    <string name="location_street_level" product="tablet" >"允许应用使用您平板电脑上的GPS定位您的位置"</string>
    <string name="location_street_level" product="default" >"允许应用使用您手机上的GPS定位您的位置"</string>
    <string name="assisted_gps" >"使用增强型GPS"</string>
    <string name="assisted_gps_enabled" >"使用服务器来辅助GPS（取消选中可降低网络使用率）"</string>
    <string name="assisted_gps_disabled" >"使用服务器来辅助GPS（取消选中可提高GPS性能）"</string>
    <string name="use_location_title" >"位置信息和Google搜索"</string>
    <string name="use_location_summary" >"允许Google使用您的位置信息改善搜索结果和其他服务"</string>
    <string name="location_access_title" >"允许使用我的位置信息"</string>
    <string name="location_access_summary" >"允许得到您许可的应用使用您的位置信息"</string>
    <string name="location_sources_heading" >"位置信息来源"</string>
    <string name="about_settings" product="tablet" >"关于平板电脑"</string>
    <string name="about_settings" product="default" >"关于手机"</string>
    <string name="about_settings" product="device" >"关于设备"</string>
    <string name="about_settings" product="emulator" >"关于模拟设备"</string>
    <string name="about_settings_summary" >"查看法律信息、状态和软件版本"</string>
    <string name="legal_information" >"法律信息"</string>
    <string name="contributors_title" >"活动提供商"</string>
    <string name="manual" >"手册"</string>
    <string name="regulatory_labels" >"监管标签"</string>
    <string name="safety_and_regulatory_info" >"安全和监管手册"</string>
    <string name="copyright_title" >"版权"</string>
    <string name="license_title" >"许可"</string>
    <string name="terms_title" >"条款"</string>
    <string name="webview_license_title" >"系统 WebView 许可"</string>
    <string name="wallpaper_attributions" >"壁纸"</string>
    <string name="wallpaper_attributions_values" >"卫星图像提供商：\n©2014 CNES / Astrium, DigitalGlobe, Bluesky"</string>
    <string name="settings_manual_activity_title" >"手册"</string>
    <string name="settings_manual_activity_unavailable" >"加载手册时出现问题。"</string>
    <string name="settings_license_activity_title" >"第三方许可"</string>
    <string name="settings_license_activity_unavailable" >"加载许可时出现问题。"</string>
    <string name="settings_license_activity_loading" >"正在加载..."</string>
    <string name="settings_safetylegal_title" >"安全信息"</string>
    <string name="settings_safetylegal_activity_title" >"安全信息"</string>
    <string name="settings_safetylegal_activity_unreachable" >"您没有数据连接。要立即查看此信息，请使用任何联网的计算机访问%s。"</string>
    <string name="settings_safetylegal_activity_loading" >"正在加载..."</string>
    <string name="lockpassword_choose_your_screen_lock_header" >"设置屏幕锁定"</string>
    <string name="lockpassword_choose_your_password_message" >"为了安全起见，请设置密码"</string>
    <string name="lockpassword_choose_your_password_header_for_fingerprint" >"要使用指纹，请设置密码"</string>
    <string name="lockpassword_choose_your_pattern_header_for_fingerprint" >"要使用指纹，请设置解锁图案"</string>
    <string name="lockpassword_choose_your_pin_message" >"为了安全起见，请设置 PIN 码"</string>
    <string name="lockpassword_choose_your_pin_header_for_fingerprint" >"要使用指纹，请设置 PIN 码"</string>
    <string name="lockpassword_choose_your_pattern_message" >"为了安全起见，请设置解锁图案"</string>
    <string name="lockpassword_confirm_your_password_header" >"重新输入密码"</string>
    <string name="lockpassword_confirm_your_pattern_header" >"确认您的图案"</string>
    <string name="lockpassword_confirm_your_pin_header" >"重新输入 PIN 码"</string>
    <string name="lockpassword_confirm_passwords_dont_match" >"密码不匹配"</string>
    <string name="lockpassword_confirm_pins_dont_match" >"PIN码不匹配！"</string>
    <string name="lockpassword_draw_your_pattern_again_header" >"再次绘制图案"</string>
    <string name="lockpassword_choose_lock_generic_header" >"选择解锁方式"</string>
    <string name="lockpassword_password_set_toast" >"密码已设置"</string>
    <string name="lockpassword_pin_set_toast" >"已设置PIN码"</string>
    <string name="lockpassword_pattern_set_toast" >"图案已设置"</string>
    <string name="lockpassword_confirm_your_pattern_generic" >"请绘制您的设备解锁图案以继续"</string>
    <string name="lockpassword_confirm_your_pin_generic" >"请输入您的设备 PIN 码以继续"</string>
    <string name="lockpassword_confirm_your_password_generic" >"请输入您的设备密码以继续"</string>
    <string name="lockpassword_confirm_your_pattern_generic_profile" >"请绘制您的工作解锁图案以继续"</string>
    <string name="lockpassword_confirm_your_pin_generic_profile" >"请输入您的工作 PIN 码以继续"</string>
    <string name="lockpassword_confirm_your_password_generic_profile" >"请输入您的工作密码以继续"</string>
    <string name="lockpassword_strong_auth_required_device_pattern" >"为了提升安全性，请绘制您的设备解锁图案"</string>
    <string name="lockpassword_strong_auth_required_device_pin" >"为了提升安全性，请输入您的设备 PIN 码"</string>
    <string name="lockpassword_strong_auth_required_device_password" >"为了提升安全性，请输入您的设备密码"</string>
    <string name="lockpassword_strong_auth_required_work_pattern" >"为了提升安全性，请绘制您的工作资料解锁图案"</string>
    <string name="lockpassword_strong_auth_required_work_pin" >"为了提升安全性，请输入您的工作资料 PIN 码"</string>
    <string name="lockpassword_strong_auth_required_work_password" >"为了提升安全性，请输入您的工作资料密码"</string>
    <string name="lockpassword_confirm_your_pattern_details_frp" >"您的手机已恢复出厂设置。要使用此手机，请输入您以前的解锁图案。"</string>
    <string name="lockpassword_confirm_your_pin_details_frp" >"您的手机已恢复出厂设置。要使用此手机，请输入您以前的 PIN 码。"</string>
    <string name="lockpassword_confirm_your_password_details_frp" >"您的手机已恢复出厂设置。要使用此手机，请输入您以前的密码。"</string>
    <string name="lockpassword_confirm_your_pattern_header_frp" >"验证图案"</string>
    <string name="lockpassword_confirm_your_pin_header_frp" >"验证 PIN 码"</string>
    <string name="lockpassword_confirm_your_password_header_frp" >"验证密码"</string>
    <string name="lockpassword_invalid_pin" >"PIN 码错误"</string>
    <string name="lockpassword_invalid_password" >"密码错误"</string>
    <string name="lockpattern_need_to_unlock_wrong" >"图案错误"</string>
    <string name="lock_settings_title" >"设备安全性"</string>
    <string name="lockpattern_change_lock_pattern_label" >"更改解锁图案"</string>
    <string name="lockpattern_change_lock_pin_label" >"更改解锁PIN码"</string>
    <string name="lockpattern_recording_intro_header" >"绘制解锁图案"</string>
    <string name="lockpattern_recording_intro_footer" >"按 MENU 获得帮助。"</string>
    <string name="lockpattern_recording_inprogress" >"完成后松开手指"</string>
    <string name="lockpattern_recording_incorrect_too_short" >"至少需连接<xliff:g id="NUMBER">%d</xliff:g>个点，请重试。"</string>
    <string name="lockpattern_pattern_entered_header" >"图案已记录"</string>
    <string name="lockpattern_need_to_confirm" >"再次绘制图案进行确认"</string>
    <string name="lockpattern_pattern_confirmed_header" >"您的新解锁图案"</string>
    <string name="lockpattern_confirm_button_text" >"确认"</string>
    <string name="lockpattern_restart_button_text" >"重新绘制"</string>
    <string name="lockpattern_retry_button_text" >"清除"</string>
    <string name="lockpattern_continue_button_text" >"继续"</string>
    <string name="lockpattern_settings_title" >"解锁图案"</string>
    <string name="lockpattern_settings_enable_title" >"需要解锁图案"</string>
    <string name="lockpattern_settings_enable_summary" >"必须绘制图案才能解锁屏幕"</string>
    <string name="lockpattern_settings_enable_visible_pattern_title" >"显示图案"</string>
    <string name="lockpattern_settings_enable_visible_pattern_title_profile" >"显示资料解锁图案"</string>
    <string name="lockpattern_settings_enable_tactile_feedback_title" >"点按时振动"</string>
    <string name="lockpattern_settings_enable_power_button_instantly_locks" >"电源按钮即时锁定"</string>
    <string name="lockpattern_settings_power_button_instantly_locks_summary" >"<xliff:g id="TRUST_AGENT_NAME">%1$s</xliff:g>让屏幕保持解锁状态时除外"</string>
    <string name="lockpattern_settings_choose_lock_pattern" >"设置解锁图案"</string>
    <string name="lockpattern_settings_change_lock_pattern" >"更改解锁图案"</string>
    <string name="lockpattern_settings_help_how_to_record" >"如何绘制解锁图案"</string>
    <string name="lockpattern_too_many_failed_confirmation_attempts" >"尝试解锁失败次数过多。请在 <xliff:g id="NUMBER">%d</xliff:g> 秒后重试。"</string>
    <string name="activity_not_found" >"您的手机上未安装相应应用。"</string>
    <string name="lock_settings_profile_title" >"工作资料安全"</string>
    <string name="lock_settings_profile_screen_lock_title" >"工作资料屏幕锁定"</string>
    <string name="lock_settings_profile_unification_title" >"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_summary" >"让工作资料和设备屏幕使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_dialog_title" >"要使用同一种锁定方式吗？"</string>
    <string name="lock_settings_profile_unification_dialog_body" >"您的设备将使用工作资料屏幕锁定方式。这两种锁定方式都需要符合相关工作规范。"</string>
    <string name="lock_settings_profile_unification_dialog_uncompliant_body" >"您的工作资料锁定方式不符合贵单位的安全要求。您可以为自己的设备屏幕和工作资料使用同一种锁定方式，但是必须遵守所有相关的工作屏幕锁定规范。"</string>
    <string name="lock_settings_profile_unification_dialog_confirm" >"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_dialog_uncompliant_confirm" >"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unified_summary" >"与设备的屏幕锁定方式相同"</string>
    <string name="manageapplications_settings_title" >"管理应用"</string>
    <string name="manageapplications_settings_summary" >"管理和删除已安装的应用"</string>
    <string name="applications_settings" >"应用信息"</string>
    <string name="applications_settings_summary" >"管理应用、设置快速启动快捷方式"</string>
    <string name="applications_settings_header" >"应用设置"</string>
    <string name="install_applications" >"未知来源"</string>
    <string name="install_applications_title" >"允许所有应用来源"</string>
    <string name="recent_app_category_title" >"最近打开的应用"</string>
    <string name="see_all_apps_title" >"查看全部 <xliff:g id="COUNT">%1$d</xliff:g> 个应用"</string>
    <string name="install_all_warning" product="tablet" >"您的平板电脑和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的平板电脑损坏或数据丢失承担责任。"</string>
    <string name="install_all_warning" product="default" >"您的手机和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的手机损坏或数据丢失承担责任。"</string>
    <string name="install_all_warning" product="device" >"您的设备和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的设备损坏或数据丢失问题承担责任。"</string>
    <string name="advanced_settings" >"高级设置"</string>
    <string name="advanced_settings_summary" >"启用更多设置选项"</string>
    <string name="application_info_label" >"应用信息"</string>
    <string name="storage_label" >"存储"</string>
    <string name="auto_launch_label" >"默认打开"</string>
    <string name="auto_launch_label_generic" >"默认操作"</string>
    <string name="screen_compatibility_label" >"屏幕兼容性"</string>
    <string name="permissions_label" >"权限"</string>
    <string name="cache_header_label" >"缓存"</string>
    <string name="clear_cache_btn_text" >"清除缓存"</string>
    <string name="cache_size_label" >"缓存"</string>
    <plurals name="uri_permissions_text" formatted="false" >
      <item quantity="other">%d 项</item>
      <item quantity="one">1 项</item>
    </plurals>
    <string name="clear_uri_btn_text" >"取消访问权限"</string>
    <string name="controls_label" >"控件"</string>
    <string name="force_stop" >"强行停止"</string>
    <string name="total_size_label" >"总计"</string>
    <string name="application_size_label" >"应用大小"</string>
    <string name="external_code_size_label" >"USB存储（应用）"</string>
    <string name="data_size_label" >"用户数据"</string>
    <string name="external_data_size_label" product="nosdcard" >"USB存储（数据）"</string>
    <string name="external_data_size_label" product="default" >"SD卡"</string>
    <string name="uninstall_text" >"卸载"</string>
    <string name="uninstall_all_users_text" >"为所有用户卸载"</string>
    <string name="install_text" >"安装"</string>
    <string name="disable_text" >"停用"</string>
    <string name="enable_text" >"启用"</string>
    <string name="clear_user_data_text" >"清除存储空间"</string>
    <string name="app_factory_reset" >"卸载更新"</string>
    <string name="auto_launch_enable_text" >"您已选择默认使用此应用处理某些操作。"</string>
    <string name="always_allow_bind_appwidgets_text" >"您已选择允许该应用创建微件并查看其数据。"</string>
    <string name="auto_launch_disable_text" >"没有默认操作。"</string>
    <string name="clear_activities" >"清除默认操作"</string>
    <string name="screen_compatibility_text" >"此应用可能不是针对您的屏幕设计的。您可以在此处调整其显示尺寸/比例，让它适合您的屏幕。"</string>
    <string name="ask_compatibility" >"启动时确认"</string>
    <string name="enable_compatibility" >"调整应用的显示尺寸/比例"</string>
    <string name="unknown" >"未知"</string>
    <string name="sort_order_alpha" >"按名称排序"</string>
    <string name="sort_order_size" >"按大小排序"</string>
    <string name="sort_order_recent_notification" >"最新"</string>
    <string name="sort_order_frequent_notification" >"最频繁"</string>
    <string name="show_running_services" >"显示当前运行的服务"</string>
    <string name="show_background_processes" >"显示已缓存的进程"</string>
    <string name="default_emergency_app" >"紧急警报应用"</string>
    <string name="reset_app_preferences" >"重置应用偏好设置"</string>
    <string name="reset_app_preferences_title" >"要重置应用偏好设置吗？"</string>
    <string name="reset_app_preferences_desc" >"这将重置以下所有偏好设置：\n\n "<li>"已停用的应用"</li>\n" "<li>"已停用的应用通知"</li>\n" "<li>"用于执行操作的默认应用"</li>\n" "<li>"应用的后台流量限制"</li>\n" "<li>"所有权限限制"</li>\n\n" 您将不会丢失任何应用数据。"</string>
    <string name="reset_app_preferences_button" >"重置应用"</string>
    <string name="manage_space_text" >"管理空间"</string>
    <string name="filter" >"过滤"</string>
    <string name="filter_dlg_title" >"选择过滤选项"</string>
    <string name="filter_apps_all" >"所有应用"</string>
    <string name="filter_apps_disabled" >"已停用的应用"</string>
    <string name="filter_apps_third_party" >"已下载"</string>
    <string name="filter_apps_running" >"正在运行"</string>
    <string name="filter_apps_onsdcard" product="nosdcard" >"USB存储设备"</string>
    <string name="filter_apps_onsdcard" product="default" >"SD卡中"</string>
    <string name="not_installed" >"未针对此用户安装"</string>
    <string name="installed" >"已安装"</string>
    <string name="no_applications" >"无应用。"</string>
    <string name="internal_storage" >"内部存储空间"</string>
    <string name="internal_storage_sentence" >"内部存储设备"</string>
    <string name="sd_card_storage" product="nosdcard" >"USB存储设备"</string>
    <string name="sd_card_storage" product="default" >"SD卡存储设备"</string>
    <string name="recompute_size" >"正在重新计算大小..."</string>
    <string name="clear_data_dlg_title" >"要删除应用数据吗？"</string>
    <string name="clear_data_dlg_text" >"系统会永久删除此应用的所有数据。删除的内容包括所有文件、设置、帐号、数据库等。"</string>
    <string name="dlg_ok" >"确定"</string>
    <string name="dlg_cancel" >"取消"</string>
    <string name="app_not_found_dlg_title" ></string>
    <string name="app_not_found_dlg_text" >"在已安装应用的列表中找不到该应用。"</string>
    <string name="clear_failed_dlg_text" >"无法清除应用的存储空间。"</string>
    <string name="security_settings_desc" product="tablet" >"此应用拥有以下权限："</string>
    <string name="security_settings_desc" product="default" >"此应用拥有以下权限："</string>
    <string name="security_settings_desc_multi" product="tablet" >"此应用可访问您平板电脑上的以下内容。为了提高性能和减少内存使用量，<xliff:g id="BASE_APP_NAME">%1$s</xliff:g>可获得其中的部分权限，因为它与<xliff:g id="ADDITIONAL_APPS_LIST">%2$s</xliff:g>运行在同一进程中。"</string>
    <string name="security_settings_desc_multi" product="default" >"此应用可访问您手机上的以下内容。为了提高性能和减少内存使用量，<xliff:g id="BASE_APP_NAME">%1$s</xliff:g>可获得其中的部分权限，因为它与<xliff:g id="ADDITIONAL_APPS_LIST">%2$s</xliff:g>运行在同一进程中。"</string>
    <string name="join_two_items" >"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>和<xliff:g id="SECOND_ITEM">%2$s</xliff:g>"</string>
    <string name="join_two_unrelated_items" >"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>、<xliff:g id="SECOND_ITEM">%2$s</xliff:g>"</string>
    <string name="join_many_items_last" >"<xliff:g id="ALL_BUT_LAST_ITEM">%1$s</xliff:g>和<xliff:g id="LAST_ITEM_0">%2$s</xliff:g>"</string>
    <string name="join_many_items_first" >"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>、<xliff:g id="ALL_BUT_FIRST_AND_LAST_ITEM">%2$s</xliff:g>"</string>
    <string name="join_many_items_middle" >"<xliff:g id="ADDED_ITEM">%1$s</xliff:g>、<xliff:g id="REST_OF_ITEMS">%2$s</xliff:g>"</string>
    <string name="security_settings_billing_desc" >"使用此应用可能会产生费用："</string>
    <string name="security_settings_premium_sms_desc" >"发送付费短信"</string>
    <string name="computing_size" >"正在计算..."</string>
    <string name="invalid_size_value" >"无法计算软件包的大小。"</string>
    <string name="empty_list_msg" >"未安装任何第三方应用。"</string>
    <string name="version_text" >"版本 <xliff:g id="VERSION_NUM">%1$s</xliff:g>"</string>
    <string name="move_app" >"移动"</string>
    <string name="move_app_to_internal" product="tablet" >"移至平板电脑"</string>
    <string name="move_app_to_internal" product="default" >"移至手机"</string>
    <string name="move_app_to_sdcard" product="nosdcard" >"移至USB存储设备"</string>
    <string name="move_app_to_sdcard" product="default" >"移至SD卡"</string>
    <string name="moving" >"正在移动"</string>
    <string name="another_migration_already_in_progress" >"系统目前正在执行另一项迁移操作。"</string>
    <string name="insufficient_storage" >"存储空间不足。"</string>
    <string name="does_not_exist" >"应用不存在。"</string>
    <string name="app_forward_locked" >"应用受版权保护。"</string>
    <string name="invalid_location" >"安装位置无效。"</string>
    <string name="system_package" >"无法在外部介质上安装系统更新。"</string>
    <string name="move_error_device_admin" >"无法在外部媒体上安装设备管理应用"</string>
    <string name="force_stop_dlg_title" >"要强行停止吗？"</string>
    <string name="force_stop_dlg_text" >"强行停止某个应用可能会导致其出现异常。"</string>
    <string name="move_app_failed_dlg_title" ></string>
    <string name="move_app_failed_dlg_text" >"无法移动应用。<xliff:g id="REASON">%1$s</xliff:g>"</string>
    <string name="app_install_location_title" >"首选安装位置"</string>
    <string name="app_install_location_summary" >"更改安装新应用时使用的首选安装位置"</string>
    <string name="app_disable_dlg_title" >"要停用内置应用吗？"</string>
    <string name="app_disable_dlg_positive" >"停用应用"</string>
    <string name="app_disable_dlg_text" >"如果您停用此应用，Android 和其他应用可能会无法正常运行。"</string>
    <string name="app_special_disable_dlg_title" >"确定要删除数据并停用应用吗？"</string>
    <string name="app_special_disable_dlg_text" >"如果您停用此应用，Android 和其他应用可能会无法正常运行。您的数据也将会遭到删除。"</string>
    <string name="app_disable_notifications_dlg_title" >"要关闭通知吗？"</string>
    <string name="app_disable_notifications_dlg_text" >"如果关闭此应用的通知，您可能会错过重要提醒和最新动态信息。"</string>
    <string name="app_install_details_group_title" >"商店"</string>
    <string name="app_install_details_title" >"应用详情"</string>
    <string name="app_install_details_summary" >"通过<xliff:g id="APP_STORE">%1$s</xliff:g>安装的应用"</string>
    <string name="instant_app_details_summary" >"前往 <xliff:g id="APP_STORE">%1$s</xliff:g> 查看详细信息"</string>
    <string name="app_ops_running" >"正在运行"</string>
    <string name="app_ops_never_used" >"（从未使用）"</string>
    <string name="no_default_apps" >"没有任何默认应用。"</string>
    <string name="storageuse_settings_title" >"存储空间使用情况"</string>
    <string name="storageuse_settings_summary" >"查看应用使用的存储空间"</string>
    <string name="service_restarting" >"正在重新启动"</string>
    <string name="cached" >"缓存的后台进程"</string>
    <string name="no_running_services" >"当前未运行任何服务。"</string>
    <string name="service_started_by_app" >"由应用启动。"</string>
    <!-- no translation found for service_client_name (4037193625611815517) -->
    <skip />
    <string name="service_background_processes" >"可用：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="service_foreground_processes" >"已用：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory" >"内存"</string>
    <!-- no translation found for service_process_name (4098932168654826656) -->
    <skip />
    <string name="running_process_item_user_label" >"用户：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="running_process_item_removed_user_label" >"已删除用户"</string>
    <string name="running_processes_item_description_s_s" >"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_s_p" >"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_p_s" >"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_p_p" >"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_header_title" >"设备内存"</string>
    <string name="running_processes_header_footer" >"应用内存使用情况"</string>
    <string name="running_processes_header_system_prefix" >"系统"</string>
    <string name="running_processes_header_apps_prefix" >"应用"</string>
    <string name="running_processes_header_free_prefix" >"可用"</string>
    <string name="running_processes_header_used_prefix" >"已用"</string>
    <string name="running_processes_header_cached_prefix" >"缓存"</string>
    <string name="running_processes_header_ram" >"<xliff:g id="RAM_0">%1$s</xliff:g>"</string>
    <string name="runningservicedetails_settings_title" >"正在运行的应用"</string>
    <string name="no_services" >"无活动服务"</string>
    <string name="runningservicedetails_services_title" >"服务"</string>
    <string name="runningservicedetails_processes_title" >"进程"</string>
    <string name="service_stop" >"停止"</string>
    <string name="service_manage" >"设置"</string>
    <string name="service_stop_description" >"此服务由其应用启动。停止服务可能会导致应用无法运行。"</string>
    <string name="heavy_weight_stop_description" >"无法安全地停止该应用。如果强行停止，可能会导致您目前的部分工作内容丢失。"</string>
    <string name="background_process_stop_description" >"这是旧应用进程，仍在运行，以备不时之需。通常不建议停止。"</string>
    <string name="service_manage_description" >"<xliff:g id="CLIENT_NAME">%1$s</xliff:g>：当前正在使用中。点按“设置”即可对其进行管理。"</string>
    <string name="main_running_process_description" >"正在使用的主要进程。"</string>
    <string name="process_service_in_use_description" >"正在使用服务<xliff:g id="COMP_NAME">%1$s</xliff:g>。"</string>
    <string name="process_provider_in_use_description" >"正在使用提供商<xliff:g id="COMP_NAME">%1$s</xliff:g>。"</string>
    <string name="runningservicedetails_stop_dlg_title" >"要停止系统服务吗？"</string>
    <string name="runningservicedetails_stop_dlg_text" product="tablet" >"如果停止此服务，您平板电脑上的某些功能也将随之停止工作，并在您将平板电脑关机然后再重新打开后才能使用。"</string>
    <string name="runningservicedetails_stop_dlg_text" product="default" >"如果停止此服务，您手机上的某些功能也将随之停止工作，并在您将手机关机然后再重新打开后才能使用。"</string>
    <string name="language_input_gesture_title" >"语言、输入法和手势"</string>
    <string name="language_input_gesture_summary_on_with_assist" ></string>
    <string name="language_input_gesture_summary_on_non_assist" ></string>
    <string name="language_input_gesture_summary_off" ></string>
    <string name="language_settings" >"语言和输入法"</string>
    <string name="language_empty_list_user_restricted" >"您无权更改设备语言。"</string>
    <string name="language_keyboard_settings_title" >"语言和输入法"</string>
    <string name="input_assistance" >"输入帮助"</string>
    <string name="keyboard_settings_category" >"键盘和输入法"</string>
    <string name="phone_language" >"语言"</string>
    <string name="phone_language_summary" ></string>
    <string name="auto_replace" >"自动替换"</string>
    <string name="auto_replace_summary" >"更正错误输入的字词"</string>
    <string name="auto_caps" >"自动大写"</string>
    <string name="auto_caps_summary" >"将句首字母大写"</string>
    <string name="auto_punctuate" >"自动加标点"</string>
    <string name="hardkeyboard_category" >"物理键盘设置"</string>
    <string name="auto_punctuate_summary" >"按空格键两次可插入句号"</string>
    <string name="show_password" >"显示密码"</string>
    <string name="show_password_summary" >"输入时短暂显示这些字符"</string>
    <string name="spellchecker_security_warning" >"此拼写检查工具可能会收集您键入的所有文字，包括密码和信用卡号等个人数据。它源自应用“<xliff:g id="SPELLCHECKER_APPLICATION_NAME">%1$s</xliff:g>”。要使用此拼写检查工具吗？"</string>
    <string name="spellchecker_quick_settings" >"设置"</string>
    <string name="spellchecker_language" >"语言"</string>
    <string name="keyboard_and_input_methods_category" >"键盘和输入法"</string>
    <string name="virtual_keyboard_category" >"虚拟键盘"</string>
    <string name="available_virtual_keyboard_category" >"可用虚拟键盘"</string>
    <string name="add_virtual_keyboard" >"管理键盘"</string>
    <string name="keyboard_assistance_category" >"键盘辅助功能"</string>
    <string name="physical_keyboard_title" >"实体键盘"</string>
    <string name="show_ime" >"显示虚拟键盘"</string>
    <string name="show_ime_summary" >"开启后，连接到实体键盘时，它会一直显示在屏幕上"</string>
    <string name="keyboard_shortcuts_helper" >"键盘快捷键帮助程序"</string>
    <string name="keyboard_shortcuts_helper_summary" >"显示可用的快捷键"</string>
    <string name="default_keyboard_layout" >"默认"</string>
    <string name="pointer_speed" >"指针速度"</string>
    <string name="game_controller_settings_category" >"游戏控制器"</string>
    <string name="vibrate_input_devices" >"重定向振动"</string>
    <string name="vibrate_input_devices_summary" >"连接后将振动传到游戏控制器"</string>
    <string name="keyboard_layout_dialog_title" >"选择键盘布局"</string>
    <string name="keyboard_layout_dialog_setup_button" >"设置键盘布局"</string>
    <string name="keyboard_layout_dialog_switch_hint" >"要切换，请按 Ctrl+空格键"</string>
    <string name="keyboard_layout_default_label" >"默认"</string>
    <string name="keyboard_layout_picker_title" >"键盘布局"</string>
    <string name="user_dict_settings_title" >"个人字典"</string>
    <string name="user_dict_settings_summary" ></string>
    <string name="user_dict_settings_add_menu_title" >"添加"</string>
    <string name="user_dict_settings_add_dialog_title" >"添加到字典"</string>
    <string name="user_dict_settings_add_screen_title" >"词组"</string>
    <string name="user_dict_settings_add_dialog_more_options" >"显示更多选项"</string>
    <string name="user_dict_settings_add_dialog_less_options" >"隐藏部分选项"</string>
    <string name="user_dict_settings_add_dialog_confirm" >"确定"</string>
    <string name="user_dict_settings_add_word_option_name" >"字词："</string>
    <string name="user_dict_settings_add_shortcut_option_name" >"快捷键："</string>
    <string name="user_dict_settings_add_locale_option_name" >"语言："</string>
    <string name="user_dict_settings_add_word_hint" >"输入字词"</string>
    <string name="user_dict_settings_add_shortcut_hint" >"快捷键（选填）"</string>
    <string name="user_dict_settings_edit_dialog_title" >"编辑字词"</string>
    <string name="user_dict_settings_context_menu_edit_title" >"编辑"</string>
    <string name="user_dict_settings_context_menu_delete_title" >"删除"</string>
    <string name="user_dict_settings_empty_text" >"用户字典中没有任何字词。要添加字词，请点按“添加”(+) 按钮。"</string>
    <string name="user_dict_settings_all_languages" >"所有语言"</string>
    <string name="user_dict_settings_more_languages" >"更多语言..."</string>
    <string name="testing" >"测试"</string>
    <string name="testing_phone_info" product="tablet" >"平板电脑信息"</string>
    <string name="testing_phone_info" product="default" >"手机信息"</string>
    <string name="input_methods_settings_title" >"文字输入"</string>
    <string name="input_method" >"输入法"</string>
    <string name="current_input_method" >"当前输入法"</string>
    <string name="input_method_selector" >"选择输入法"</string>
    <string name="input_method_selector_show_automatically_title" >"自动"</string>
    <string name="input_method_selector_always_show_title" >"总是显示"</string>
    <string name="input_method_selector_always_hide_title" >"始终隐藏"</string>
    <string name="configure_input_method" >"设置输入法"</string>
    <string name="input_method_settings" >"设置"</string>
    <string name="input_method_settings_button" >"设置"</string>
    <string name="input_methods_settings_label_format" >"<xliff:g id="IME_NAME">%1$s</xliff:g> 设置"</string>
    <string name="input_methods_and_subtype_enabler_title" >"选择有效的输入法"</string>
    <string name="onscreen_keyboard_settings_summary" >"屏幕键盘设置"</string>
    <string name="builtin_keyboard_settings_title" >"物理键盘"</string>
    <string name="builtin_keyboard_settings_summary" >"物理键盘设置"</string>
    <string name="gadget_picker_title" >"选择小工具"</string>
    <string name="widget_picker_title" >"选择微件"</string>
    <string name="allow_bind_app_widget_activity_allow_bind_title" >"是否允许该应用创建微件并查看其数据？"</string>
    <string name="allow_bind_app_widget_activity_allow_bind" >"当您创建微件后，“<xliff:g id="WIDGET_HOST_NAME">%1$s</xliff:g>”将能查看其显示的所有数据。"</string>
    <string name="allow_bind_app_widget_activity_always_allow_bind" >"始终允许“<xliff:g id="WIDGET_HOST_NAME">%1$s</xliff:g>”创建微件并查看其数据"</string>
    <string name="usage_stats_label" >"使用情况统计数据"</string>
    <string name="testing_usage_stats" >"使用情况统计数据"</string>
    <string name="display_order_text" >"排序方式："</string>
    <string name="app_name_label" >"应用"</string>
    <string name="last_time_used_label" >"上次使用时间"</string>
    <string name="usage_time_label" >"使用时间"</string>
    <string name="accessibility_settings" >"无障碍"</string>
    <string name="accessibility_settings_title" >"无障碍设置"</string>
    <string name="accessibility_settings_summary" >"屏幕阅读器、显示、互动控件"</string>
    <string name="vision_settings_title" >"阅读辅助设置"</string>
    <string name="vision_settings_description" >"您可以根据自己的需求对此设备进行自定义。以后，您可以在“设置”中更改这些辅助功能设置。"</string>
    <string name="vision_settings_suggestion_title" >"更改字体大小"</string>
    <string name="screen_reader_category_title" >"屏幕阅读器"</string>
    <string name="audio_and_captions_category_title" >"音频和屏幕上的文字"</string>
    <string name="display_category_title" >"显示"</string>
    <string name="interaction_control_category_title" >"互动控件"</string>
    <string name="user_installed_services_category_title" >"已下载的服务"</string>
    <string name="experimental_category_title" >"实验性功能"</string>
    <string name="talkback_title" >"TalkBack"</string>
    <string name="talkback_summary" >"屏幕阅读器主要适用于盲人和视力不佳的人士"</string>
    <string name="select_to_speak_summary" >"点按屏幕上的内容即可让系统大声朗读出来"</string>
    <string name="accessibility_captioning_title" >"字幕"</string>
    <string name="accessibility_screen_magnification_title" >"放大功能"</string>
    <string name="accessibility_screen_magnification_gestures_title" >"点按屏幕三次进行放大"</string>
    <string name="accessibility_screen_magnification_navbar_title" >"使用按钮进行放大"</string>
    <string name="accessibility_screen_magnification_state_navbar_gesture" >"使用按钮/点按屏幕三次进行放大"</string>
    <string name="accessibility_preference_magnification_summary" >"在屏幕上放大"</string>
    <string name="accessibility_screen_magnification_short_summary" >"点按三次即可放大屏幕"</string>
    <string name="accessibility_screen_magnification_navbar_short_summary" >"点按按钮即可放大"</string>
    <string name="accessibility_screen_magnification_summary" ><b>"要放大"</b>"，在屏幕上快速点按屏幕三次即可。\n"<ul><li>"用双指或多指在屏幕上拖动即可进行滚动"</li>\n<li>"张合双指或多指即可调整缩放级别"</li></ul>\n\n<b>"要暂时性地放大"</b>"，请快速在屏幕上点按三次，并在最后一次点按时按住手指不放。\n"<ul><li>"拖动手指即可在屏幕上四处移动"</li>\n<li>"松开手指即可缩小回原来的状态"</li></ul>\n\n"您不能在键盘和导航栏中使用放大功能。"</string>
    <string name="accessibility_screen_magnification_navbar_summary" >"启用放大功能后，使用屏幕底部的“无障碍”按钮可以快速放大。\n\n"<b>"要放大"</b>"，请点按“无障碍”按钮，然后点按屏幕上的任意位置。\n"<ul><li>"拖动双指或多指即可进行滚动"</li>\n<li>"张合双指或多指即可调整缩放级别"</li></ul>\n\n<b>"要暂时性地放大"</b>"，请点按“无障碍”按钮，然后触摸并按住屏幕上的任意位置。\n"<ul><li>"拖动手指即可在屏幕上四处移动"</li>\n<li>"松开手指即可缩小回原来的状态"</li></ul>\n\n"您不能在键盘和导航栏中使用放大功能。"</string>
    <string name="accessibility_screen_magnification_navbar_configuration_warning" >"“无障碍”按钮已设为“<xliff:g id="SERVICE">%1$s</xliff:g>”。要使用放大功能，请触摸并按住“无障碍”按钮，然后选择“放大功能”。"</string>
    <string name="accessibility_global_gesture_preference_title" >"音量键快捷方式"</string>
    <string name="accessibility_shortcut_service_title" >"快捷方式服务"</string>
    <string name="accessibility_shortcut_service_on_lock_screen_title" >"屏幕锁定时也可以用"</string>
    <string name="accessibility_shortcut_description" >"启用这项快捷方式后，同时按下两个音量键 3 秒钟即可启动无障碍功能。"</string>
    <string name="accessibility_toggle_high_text_contrast_preference_title" >"高对比度文字"</string>
    <string name="accessibility_toggle_screen_magnification_auto_update_preference_title" >"自动更新屏幕放大状态"</string>
    <string name="accessibility_toggle_screen_magnification_auto_update_preference_summary" >"在应用转换时更新屏幕放大状态"</string>
    <string name="accessibility_power_button_ends_call_prerefence_title" >"按电源按钮结束通话"</string>
    <string name="accessibility_toggle_large_pointer_icon_title" >"大号鼠标指针"</string>
    <string name="accessibility_disable_animations" >"移除动画"</string>
    <string name="accessibility_toggle_master_mono_title" >"单声道音频"</string>
    <string name="accessibility_toggle_master_mono_summary" >"播放音频时合并声道"</string>
    <string name="accessibility_long_press_timeout_preference_title" >"触摸和按住延迟"</string>
    <string name="accessibility_display_inversion_preference_title" >"颜色反转"</string>
    <string name="accessibility_display_inversion_preference_subtitle" >"可能会影响性能"</string>
    <string name="accessibility_autoclick_preference_title" >"停留时间"</string>
    <string name="accessibility_autoclick_description" >"如果您使用鼠标，则可以将光标设置为停止移动一段时间后自动点击。"</string>
    <string name="accessibility_autoclick_delay_preference_title" >"点击前延迟"</string>
    <string name="accessibility_vibration_settings_title" >"振动"</string>
    <string name="accessibility_notification_vibration_title" >"响铃和通知振动"</string>
    <string name="accessibility_touch_vibration_title" >"触摸振动"</string>
    <string name="accessibility_service_master_switch_title" >"使用服务"</string>
    <string name="accessibility_daltonizer_master_switch_title" >"使用色彩校正"</string>
    <string name="accessibility_caption_master_switch_title" >"使用字幕"</string>
    <string name="accessibility_hearingaid_title" >"助听器"</string>
    <string name="accessibility_hearingaid_not_connected_summary" >"未连接任何助听器"</string>
    <string name="accessibility_hearingaid_adding_summary" >"添加助听器"</string>
    <string name="accessibility_hearingaid_pair_instructions_first_message" >"要为助听器配对，请在下一个屏幕上查找并点按您的设备。"</string>
    <string name="accessibility_hearingaid_pair_instructions_second_message" >"请确保您的助听器已处于配对模式。"</string>
    <string name="accessibility_hearingaid_active_device_summary" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g> 目前正在使用中"</string>
    <plurals name="show_number_hearingaid_count" formatted="false" >
      <item quantity="other">已保存 <xliff:g id="NUMBER_DEVICE_COUNT_1">%1$d</xliff:g> 台助听器</item>
      <item quantity="one">已保存 <xliff:g id="NUMBER_DEVICE_COUNT_0">%1$d</xliff:g> 台助听器</item>
    </plurals>
    <string name="accessibility_summary_state_enabled" >"开启"</string>
    <string name="accessibility_summary_state_disabled" >"关闭"</string>
    <string name="accessibility_summary_state_stopped" >"无法运行。点按即可查看相关信息。"</string>
    <string name="accessibility_description_state_stopped" >"此服务出现故障。"</string>
    <string name="enable_quick_setting" >"在“快捷设置”中显示"</string>
    <string name="daltonizer_type" >"校正模式"</string>
    <plurals name="accessibilty_autoclick_preference_subtitle_extremely_short_delay" formatted="false" >
      <item quantity="other">延迟时间极短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间极短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_very_short_delay" formatted="false" >
      <item quantity="other">延迟时间很短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间很短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_short_delay" formatted="false" >
      <item quantity="other">延迟时间短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_long_delay" formatted="false" >
      <item quantity="other">延迟时间长（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间长（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_very_long_delay" formatted="false" >
      <item quantity="other">延迟时间很长（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间很长（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <string name="accessibility_vibration_summary" >"铃声：<xliff:g id="SUMMARY_RING">%1$s</xliff:g>，触摸强度：<xliff:g id="SUMMARY_TOUCH">%2$s</xliff:g>"</string>
    <string name="accessibility_vibration_summary_off" >"将响铃和通知振动设为关闭"</string>
    <string name="accessibility_vibration_summary_low" >"将响铃和通知振动设为低"</string>
    <string name="accessibility_vibration_summary_medium" >"将响铃和通知振动设为中"</string>
    <string name="accessibility_vibration_summary_high" >"将响铃和通知振动设为高"</string>
    <string name="accessibility_vibration_intensity_off" >"关闭"</string>
    <string name="accessibility_vibration_intensity_low" >"低"</string>
    <string name="accessibility_vibration_intensity_medium" >"中"</string>
    <string name="accessibility_vibration_intensity_high" >"高"</string>
    <string name="accessibility_menu_item_settings" >"设置"</string>
    <string name="accessibility_feature_state_on" >"开启"</string>
    <string name="accessibility_feature_state_off" >"关闭"</string>
    <string name="captioning_preview_title" >"预览"</string>
    <string name="captioning_standard_options_title" >"标准选项"</string>
    <string name="captioning_locale" >"语言"</string>
    <string name="captioning_text_size" >"文字大小"</string>
    <string name="captioning_preset" >"字幕样式"</string>
    <string name="captioning_custom_options_title" >"自定义选项"</string>
    <string name="captioning_background_color" >"背景颜色"</string>
    <string name="captioning_background_opacity" >"背景不透明度"</string>
    <string name="captioning_window_color" >"字幕窗口颜色"</string>
    <string name="captioning_window_opacity" >"字幕窗口不透明度"</string>
    <string name="captioning_foreground_color" >"文字颜色"</string>
    <string name="captioning_foreground_opacity" >"文字不透明度"</string>
    <string name="captioning_edge_color" >"边缘颜色"</string>
    <string name="captioning_edge_type" >"边缘类型"</string>
    <string name="captioning_typeface" >"字体系列"</string>
    <string name="captioning_preview_text" >"字幕外观示例"</string>
    <string name="captioning_preview_characters" >"Aa"</string>
    <string name="locale_default" >"默认"</string>
    <string name="color_title" >"颜色"</string>
    <string name="color_unspecified" >"默认"</string>
    <string name="color_none" >"无"</string>
    <string name="color_white" >"白色"</string>
    <string name="color_gray" >"灰色"</string>
    <string name="color_black" >"黑色"</string>
    <string name="color_red" >"红色"</string>
    <string name="color_green" >"绿色"</string>
    <string name="color_blue" >"蓝色"</string>
    <string name="color_cyan" >"青色"</string>
    <string name="color_yellow" >"黄色"</string>
    <string name="color_magenta" >"紫红色"</string>
    <string name="enable_service_title" >"要使用“<xliff:g id="SERVICE">%1$s</xliff:g>”吗？"</string>
    <string name="capabilities_list_title" >"“<xliff:g id="SERVICE">%1$s</xliff:g>”需要："</string>
    <string name="touch_filtered_warning" >"由于某个应用遮挡了权限请求界面，因此“设置”应用无法验证您的回应。"</string>
    <string name="enable_service_encryption_warning" >"如果您开启 <xliff:g id="SERVICE">%1$s</xliff:g>，您的设备将无法使用屏幕锁定来增强数据加密。"</string>
    <string name="secure_lock_encryption_warning" >"由于您已开启无障碍服务，因此您的设备将无法使用屏幕锁定来增强数据加密。"</string>
    <string name="enable_service_pattern_reason" >"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的解锁图案。"</string>
    <string name="enable_service_pin_reason" >"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的 PIN 码。"</string>
    <string name="enable_service_password_reason" >"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的密码。"</string>
    <string name="capability_title_receiveAccessibilityEvents" >"监测您的操作"</string>
    <string name="capability_desc_receiveAccessibilityEvents" >"在您与应用互动时接收通知。"</string>
    <string name="disable_service_title" >"要停用“<xliff:g id="SERVICE">%1$s</xliff:g>”吗？"</string>
    <string name="disable_service_message" >"点按“确定”将会停用“<xliff:g id="SERVICE">%1$s</xliff:g>”。"</string>
    <string name="accessibility_no_services_installed" >"未安装任何服务"</string>
    <string name="accessibility_no_service_selected" >"未选择任何服务"</string>
    <string name="accessibility_service_default_description" >"没有提供说明。"</string>
    <string name="settings_button" >"设置"</string>
    <string name="print_settings" >"打印"</string>
    <string name="print_settings_summary_no_service" >"关闭"</string>
    <plurals name="print_settings_summary" formatted="false" >
      <item quantity="other">已开启 <xliff:g id="COUNT">%1$d</xliff:g> 项打印服务</item>
      <item quantity="one">已开启 1 项打印服务</item>
    </plurals>
    <plurals name="print_jobs_summary" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 项打印任务</item>
      <item quantity="one">1 项打印任务</item>
    </plurals>
    <string name="print_settings_title" >"打印服务"</string>
    <string name="print_no_services_installed" >"未安装任何服务"</string>
    <string name="print_no_printers_found" >"找不到打印机"</string>
    <string name="print_menu_item_settings" >"设置"</string>
    <string name="print_menu_item_add_printers" >"添加打印机"</string>
    <string name="print_feature_state_on" >"开启"</string>
    <string name="print_feature_state_off" >"关闭"</string>
    <string name="print_menu_item_add_service" >"添加服务"</string>
    <string name="print_menu_item_add_printer" >"添加打印机"</string>
    <string name="print_menu_item_search" >"搜索"</string>
    <string name="print_searching_for_printers" >"正在搜索打印机"</string>
    <string name="print_service_disabled" >"服务已停用"</string>
    <string name="print_print_jobs" >"打印作业"</string>
    <string name="print_print_job" >"打印作业"</string>
    <string name="print_restart" >"重新开始"</string>
    <string name="print_cancel" >"取消"</string>
    <string name="print_job_summary" >"<xliff:g id="PRINTER">%1$s</xliff:g>\n<xliff:g id="TIME">%2$s</xliff:g>"</string>
    <string name="print_configuring_state_title_template" >"正在配置“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_printing_state_title_template" >"正在打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_cancelling_state_title_template" >"正在取消打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_failed_state_title_template" >"打印机在打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”时出错"</string>
    <string name="print_blocked_state_title_template" >"打印机拒绝打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_search_box_shown_utterance" >"搜索框已显示"</string>
    <string name="print_search_box_hidden_utterance" >"搜索框已隐藏"</string>
    <string name="printer_info_desc" >"此打印机的详细信息"</string>
    <string name="power_usage_summary_title" >"电池"</string>
    <string name="power_usage_summary" >"耗电情况"</string>
    <string name="power_usage_not_available" >"没有电池使用数据。"</string>
    <string name="power_usage_level_and_status" >"<xliff:g id="LEVEL">%1$s</xliff:g> - <xliff:g id="STATUS">%2$s</xliff:g>"</string>
    <string name="power_discharge_remaining" >"还可用：<xliff:g id="REMAIN">%1$s</xliff:g>"</string>
    <string name="power_charge_remaining" >"充电剩余时间：<xliff:g id="UNTIL_CHARGED">%1$s</xliff:g>"</string>
    <string name="background_activity_title" >"后台限制"</string>
    <string name="background_activity_summary" >"允许应用在后台运行"</string>
    <string name="background_activity_summary_disabled" >"不允许应用在后台运行"</string>
    <string name="background_activity_summary_whitelisted" >"无法限制后台使用"</string>
    <string name="background_activity_warning_dialog_title" >"要限制后台活动吗？"</string>
    <string name="background_activity_warning_dialog_text" >"如果您限制某个应用的后台活动，可能会导致该应用出现异常"</string>
    <string name="background_activity_disabled_dialog_text" >"由于此应用未设置为优化电池用量，因此您无法对其加以限制。\n\n要限制该应用，请先开启电池优化功能。"</string>
    <string name="device_screen_usage" >"上次充满电后的屏幕用电量"</string>
    <string name="device_screen_consumption" >"屏幕耗电量"</string>
    <string name="device_cellular_network" >"移动网络扫描"</string>
    <string name="power_usage_list_summary" >"充满电后的电池用量"</string>
    <string name="screen_usage_summary" >"充满电后的屏幕使用时间"</string>
    <string name="device_usage_list_summary" >"充满电后的设备用电量"</string>
    <string name="battery_since_unplugged" >"拔下电源后的电量消耗情况"</string>
    <string name="battery_since_reset" >"重置后的电量消耗情况"</string>
    <string name="battery_stats_on_battery" >"电池已用时间：<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="battery_stats_duration" >"拔下电源后已过 <xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="battery_stats_charging_label" >"充电"</string>
    <string name="battery_stats_screen_on_label" >"屏幕开启"</string>
    <string name="battery_stats_gps_on_label" >"GPS 开启"</string>
    <string name="battery_stats_camera_on_label" >"相机开启"</string>
    <string name="battery_stats_flashlight_on_label" >"手电筒开启"</string>
    <string name="battery_stats_wifi_running_label" >"WLAN"</string>
    <string name="battery_stats_wake_lock_label" >"唤醒"</string>
    <string name="battery_stats_phone_signal_label" >"移动网络信号"</string>
    <!-- no translation found for battery_stats_last_duration (1535831453827905957) -->
    <skip />
    <string name="awake" >"设备唤醒时间"</string>
    <string name="wifi_on_time" >"WLAN开启时间"</string>
    <string name="bluetooth_on_time" >"WLAN开启时间"</string>
    <string name="advanced_battery_title" >"电池用量"</string>
    <string name="history_details_title" >"详细电量使用记录"</string>
    <string name="battery_details_title" >"电池用量"</string>
    <string name="details_subtitle" >"详细使用情况"</string>
    <string name="controls_subtitle" >"省电提示"</string>
    <string name="packages_subtitle" >"包含的软件包"</string>
    <string name="battery_abnormal_details_title" >"多个应用正大量耗电"</string>
    <string name="battery_abnormal_wakelock_summary" >"使设备保持唤醒状态"</string>
    <string name="battery_abnormal_wakeup_alarm_summary" >"在后台唤醒设备"</string>
    <string name="battery_abnormal_location_summary" >"请求获取位置信息的频率过高"</string>
    <string name="battery_abnormal_apps_summary" >"<xliff:g id="NUMBER">%1$d</xliff:g> 个应用出现异常"</string>
    <string name="battery_tip_summary_title" >"应用正常运行中"</string>
    <string name="battery_tip_summary_summary" product="default" >"手机的后台耗电量正常"</string>
    <string name="battery_tip_summary_summary" product="tablet" >"平板电脑的后台耗电量正常"</string>
    <string name="battery_tip_summary_summary" product="device" >"设备的后台耗电量正常"</string>
    <string name="battery_tip_low_battery_title" >"电池电量不足"</string>
    <string name="battery_tip_low_battery_summary" >"电池的续航时间不理想"</string>
    <string name="battery_tip_smart_battery_title" product="default" >"延长手机的电池续航时间"</string>
    <string name="battery_tip_smart_battery_title" product="tablet" >"延长平板电脑的电池续航时间"</string>
    <string name="battery_tip_smart_battery_title" product="device" >"延长设备的电池续航时间"</string>
    <string name="battery_tip_smart_battery_summary" >"开启电池管理器"</string>
    <string name="battery_tip_early_heads_up_title" >"开启省电模式"</string>
    <string name="battery_tip_early_heads_up_summary" >"电池电量可能会比平时更早耗尽"</string>
    <string name="battery_tip_early_heads_up_done_title" >"省电模式已开启"</string>
    <string name="battery_tip_early_heads_up_done_summary" >"部分功能可能会受到限制"</string>
    <string name="battery_tip_high_usage_title" product="default" >"手机的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_title" product="tablet" >"平板电脑的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_title" product="device" >"设备的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_summary" >"电池电量即将耗尽"</string>
    <string name="battery_tip_dialog_message" product="default" >"手机的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <string name="battery_tip_dialog_message" product="tablet" >"平板电脑的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <string name="battery_tip_dialog_message" product="device" >"设备的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <plurals name="battery_tip_restrict_title" formatted="false" >
      <item quantity="other">限制 %1$d 个应用</item>
      <item quantity="one">限制 %1$d 个应用</item>
    </plurals>
    <plurals name="battery_tip_restrict_handled_title" formatted="false" >
      <item quantity="other">%2$d 个应用最近受到限制</item>
      <item quantity="one">%1$s 个应用最近受到限制</item>
    </plurals>
    <plurals name="battery_tip_restrict_summary" formatted="false" >
      <item quantity="other">%2$d 个应用的后台耗电量很高</item>
      <item quantity="one">%1$s 个应用的后台耗电量很高</item>
    </plurals>
    <plurals name="battery_tip_restrict_handled_summary" formatted="false" >
      <item quantity="other">这些应用无法在后台运行</item>
      <item quantity="one">此应用无法在后台运行</item>
    </plurals>
    <plurals name="battery_tip_restrict_app_dialog_title" formatted="false" >
      <item quantity="other">要限制 %1$d 个应用吗？</item>
      <item quantity="one">要限制应用吗？</item>
    </plurals>
    <string name="battery_tip_restrict_app_dialog_message" >"为了节省电量，请将<xliff:g id="APP">%1$s</xliff:g>设置为停止在后台使用电量。该应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="battery_tip_restrict_apps_less_than_5_dialog_message" >"为了节省电量，请将这些应用设置为停止在后台使用电量。受限应用可能无法正常运行，相应通知也可能会有所延迟。\n\n应用："</string>
    <string name="battery_tip_restrict_apps_more_than_5_dialog_message" >"为了节省电量，请将这些应用设置为停止在后台使用电量。受限应用可能无法正常运行，相应通知也可能会有所延迟。\n\n应用：\n<xliff:g id="APP_LIST">%1$s</xliff:g>。"</string>
    <string name="battery_tip_restrict_app_dialog_ok" >"限制"</string>
    <string name="battery_tip_unrestrict_app_dialog_title" >"要移除限制吗？"</string>
    <string name="battery_tip_unrestrict_app_dialog_message" >"此应用将能够在后台消耗电量。这可能会导致电量耗尽速度比预期更快。"</string>
    <string name="battery_tip_unrestrict_app_dialog_ok" >"移除"</string>
    <string name="battery_tip_unrestrict_app_dialog_cancel" >"取消"</string>
    <string name="battery_tip_dialog_summary_message" product="default" >"您的应用目前耗电量正常。如果应用耗电量过高，您的手机会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="battery_tip_dialog_summary_message" product="tablet" >"您的应用目前耗电量正常。如果应用耗电量过高，您的平板电脑会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="battery_tip_dialog_summary_message" product="device" >"您的应用目前耗电量正常。如果应用耗电量过高，您的设备会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="smart_battery_manager_title" >"电池管理器"</string>
    <string name="smart_battery_title" >"自动管理应用"</string>
    <string name="smart_battery_summary" >"限制不常用的应用的用电量"</string>
    <string name="smart_battery_footer" product="default" >"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="smart_battery_footer" product="tablet" >"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="smart_battery_footer" product="device" >"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="restricted_app_title" >"受限应用"</string>
    <plurals name="restricted_app_summary" formatted="false" >
      <item quantity="other">正在限制 %1$d 个应用的电池用量</item>
      <item quantity="one">正在限制 %1$d 个应用的电池用量</item>
    </plurals>
    <string name="restricted_app_detail_footer" >"这些应用一直在后台消耗电量。受限应用可能无法正常运行，且相关通知可能也会有所延迟。"</string>
    <string name="battery_auto_restriction_title" >"使用电池管理器"</string>
    <string name="battery_auto_restriction_summary" >"检测应用何时使用电量"</string>
    <string name="battery_manager_on" >"已开启/正在检测应用何时使用电量"</string>
    <string name="battery_manager_off" >"已关闭"</string>
    <plurals name="battery_manager_app_restricted" formatted="false" >
      <item quantity="other">%1$d 个应用受到限制</item>
      <item quantity="one">%1$d 个应用受到限制</item>
    </plurals>
    <string name="dialog_stop_title" >"要停止该应用吗？"</string>
    <string name="dialog_stop_message" product="default" >"由于<xliff:g id="APP">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message" product="tablet" >"由于<xliff:g id="APP">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message" product="device" >"由于<xliff:g id="APP">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="default" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="tablet" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="device" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_ok" >"停止应用"</string>
    <string name="dialog_background_check_title" >"要关闭后台使用功能并停止应用吗？"</string>
    <string name="dialog_background_check_message" product="default" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_message" product="tablet" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_message" product="device" >"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_ok" >"关闭"</string>
    <string name="dialog_location_title" >"要禁止获取位置信息吗？"</string>
    <string name="dialog_location_message" product="default" >"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的手机无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_message" product="tablet" >"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的平板电脑无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_message" product="device" >"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的设备无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_ok" >"关闭"</string>
    <string name="power_screen" >"屏幕"</string>
    <string name="power_flashlight" >"手电筒"</string>
    <string name="power_camera" >"相机"</string>
    <string name="power_wifi" >"WLAN"</string>
    <string name="power_bluetooth" >"蓝牙"</string>
    <string name="power_cell" >"移动网络待机"</string>
    <string name="power_phone" >"语音通话"</string>
    <string name="power_idle" product="tablet" >"平板电脑待机"</string>
    <string name="power_idle" product="default" >"手机待机"</string>
    <string name="power_unaccounted" >"其他"</string>
    <string name="power_overcounted" >"多算了的"</string>
    <string name="usage_type_cpu" >"CPU总使用时间"</string>
    <string name="usage_type_cpu_foreground" >"CPU（前台）"</string>
    <string name="usage_type_wake_lock" >"保持唤醒状态"</string>
    <string name="usage_type_gps" >"GPS"</string>
    <string name="usage_type_wifi_running" >"WLAN运行时间"</string>
    <string name="usage_type_phone" product="tablet" >"平板电脑"</string>
    <string name="usage_type_phone" product="default" >"通话"</string>
    <string name="usage_type_data_send" >"发送的移动数据包"</string>
    <string name="usage_type_data_recv" >"接收的移动数据包"</string>
    <string name="usage_type_radio_active" >"移动无线装置运行时间"</string>
    <string name="usage_type_data_wifi_send" >"发送的WLAN数据包"</string>
    <string name="usage_type_data_wifi_recv" >"接收的WLAN数据包"</string>
    <string name="usage_type_audio" >"音频"</string>
    <string name="usage_type_video" >"视频"</string>
    <string name="usage_type_camera" >"相机"</string>
    <string name="usage_type_flashlight" >"手电筒"</string>
    <string name="usage_type_on_time" >"运行时间"</string>
    <string name="usage_type_no_coverage" >"无信号时间"</string>
    <string name="usage_type_total_battery_capacity" >"电池总容量"</string>
    <string name="usage_type_computed_power" >"计算出的耗电量"</string>
    <string name="usage_type_actual_power" >"观察到的耗电量"</string>
    <string name="battery_action_stop" >"强行停止"</string>
    <string name="battery_action_app_details" >"应用信息"</string>
    <string name="battery_action_app_settings" >"应用设置"</string>
    <string name="battery_action_display" >"屏幕设置"</string>
    <string name="battery_action_wifi" >"WLAN设置"</string>
    <string name="battery_action_bluetooth" >"蓝牙设置"</string>
    <string name="battery_desc_voice" >"语音通话所耗的电量"</string>
    <string name="battery_desc_standby" product="tablet" >"平板电脑待机所耗的电量"</string>
    <string name="battery_desc_standby" product="default" >"手机待机所耗的电量"</string>
    <string name="battery_desc_radio" >"移动网络所耗的电量"</string>
    <string name="battery_sugg_radio" >"不在信号覆盖范围内时切换到飞行模式，以节约电量"</string>
    <string name="battery_desc_flashlight" >"手电筒所耗的电量"</string>
    <string name="battery_desc_camera" >"相机所耗的电量"</string>
    <string name="battery_desc_display" >"显示屏和背光所耗的电量"</string>
    <string name="battery_sugg_display" >"降低屏幕亮度，缩短休眠延时"</string>
    <string name="battery_desc_wifi" >"WLAN网络所耗的电量"</string>
    <string name="battery_sugg_wifi" >"未使用或无法使用WLAN网络时将WLAN关闭"</string>
    <string name="battery_desc_bluetooth" >"蓝牙所耗的电量"</string>
    <string name="battery_sugg_bluetooth_basic" >"请在不使用蓝牙功能时将其关闭"</string>
    <string name="battery_sugg_bluetooth_headset" >"尝试连接另一蓝牙设备"</string>
    <string name="battery_desc_apps" >"该应用所耗的电量"</string>
    <string name="battery_sugg_apps_info" >"停止或卸载应用"</string>
    <string name="battery_sugg_apps_gps" >"选择“节电”模式"</string>
    <string name="battery_sugg_apps_settings" >"该应用中可能提供了用于减少耗电量的设置"</string>
    <string name="battery_desc_users" >"用户所耗的电量"</string>
    <string name="battery_desc_unaccounted" >"其他耗电量"</string>
    <string name="battery_msg_unaccounted" >"耗电量显示的是大致用电量，并不包括所有耗电来源所消耗的电量。其他耗电量显示的是系统计算出的大致耗电量与实际观察到的耗电量之间的差异量。"</string>
    <string name="battery_desc_overcounted" >"多算了的用电量"</string>
    <string name="mah" >"<xliff:g id="NUMBER">%d</xliff:g>毫安时"</string>
    <string name="battery_used_for" >"使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_active_for" >"使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_screen_usage" >"屏幕使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_used_by" >"<xliff:g id="APP">%2$s</xliff:g>用电量占 <xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_overall_usage" >"整体用电量的 <xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_detail_since_full_charge" >"自上次充满电后的用电明细"</string>
    <string name="battery_last_full_charge" >"上次充满电"</string>
    <string name="battery_full_charge_last" >"电池在充满电后的预估使用时间"</string>
    <string name="battery_footer_summary" >"电池消耗量为大致值，可能会因使用情形而变化"</string>
    <string name="battery_detail_foreground" >"在前台运行时"</string>
    <string name="battery_detail_background" >"在后台运行时"</string>
    <string name="battery_detail_power_usage" >"电池用量"</string>
    <string name="battery_detail_info_title" >"自充满电后"</string>
    <string name="battery_detail_manage_title" >"管理电池用量"</string>
    <string name="advanced_battery_graph_subtext" >"系统会根据设备使用情况估算电池的剩余电量"</string>
    <string name="estimated_time_left" >"预计剩余时间"</string>
    <string name="estimated_charging_time_left" >"剩余充电时间"</string>
    <string name="estimated_time_description" >"估算时间可能会因使用情形而有所改变"</string>
    <string name="menu_stats_unplugged" >"拔下电源后已过 <xliff:g id="UNPLUGGED">%1$s</xliff:g>"</string>
    <string name="menu_stats_last_unplugged" >"上次拔下电源 <xliff:g id="UNPLUGGED">%1$s</xliff:g> 时"</string>
    <string name="menu_stats_total" >"总使用量"</string>
    <string name="menu_stats_refresh" >"刷新"</string>
    <string name="process_kernel_label" >"Android 操作系统"</string>
    <string name="process_mediaserver_label" >"媒体服务器"</string>
    <string name="process_dex2oat_label" >"应用优化"</string>
    <string name="battery_saver" >"省电模式"</string>
    <string name="battery_saver_auto_title" >"自动开启"</string>
    <string name="battery_saver_seekbar_title" >"<xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_saver_seekbar_title_placeholder" >"开启"</string>
    <string name="battery_saver_master_switch_title" >"使用省电模式"</string>
    <string name="battery_saver_turn_on_automatically_title" >"自动开启"</string>
    <string name="battery_saver_turn_on_automatically_never" >"一律不"</string>
    <string name="battery_saver_turn_on_automatically_pct" >"电量剩余 <xliff:g id="PERCENT">%1$s</xliff:g> 时"</string>
    <string name="battery_percentage" >"电池电量百分比"</string>
    <string name="battery_percentage_description" >"在状态栏中显示电池电量百分比"</string>
    <string name="process_stats_summary_title" >"进程统计信息"</string>
    <string name="process_stats_summary" >"运行中进程的相关技术统计信息"</string>
    <string name="app_memory_use" >"内存用量"</string>
    <string name="process_stats_total_duration" >"过去 <xliff:g id="TIMEDURATION">%3$s</xliff:g>内使用了 <xliff:g id="USEDRAM">%1$s</xliff:g>（共 <xliff:g id="TOTALRAM">%2$s</xliff:g>）"</string>
    <string name="process_stats_total_duration_percentage" >"<xliff:g id="TIMEDURATION">%2$s</xliff:g>内使用了 <xliff:g id="PERCENT">%1$s</xliff:g> 的内存"</string>
    <string name="process_stats_type_background" >"后台"</string>
    <string name="process_stats_type_foreground" >"前台"</string>
    <string name="process_stats_type_cached" >"缓存"</string>
    <string name="process_stats_os_label" >"Android 操作系统"</string>
    <string name="process_stats_os_native" >"本地"</string>
    <string name="process_stats_os_kernel" >"内核"</string>
    <string name="process_stats_os_zram" >"Z-RAM"</string>
    <string name="process_stats_os_cache" >"缓存"</string>
    <string name="process_stats_ram_use" >"内存使用量"</string>
    <string name="process_stats_bg_ram_use" >"内存使用量（后台）"</string>
    <string name="process_stats_run_time" >"运行时间"</string>
    <string name="processes_subtitle" >"进程"</string>
    <string name="services_subtitle" >"服务"</string>
    <string name="menu_proc_stats_duration" >"时间长度"</string>
    <string name="mem_details_title" >"内存详情"</string>
    <string name="menu_duration_3h" >"3小时"</string>
    <string name="menu_duration_6h" >"6小时"</string>
    <string name="menu_duration_12h" >"12小时"</string>
    <string name="menu_duration_1d" >"1天"</string>
    <string name="menu_show_system" >"显示系统进程"</string>
    <string name="menu_hide_system" >"隐藏系统进程"</string>
    <string name="menu_show_percentage" >"显示百分比"</string>
    <string name="menu_use_uss" >"使用 USS"</string>
    <string name="menu_proc_stats_type" >"统计信息类型"</string>
    <string name="menu_proc_stats_type_background" >"后台"</string>
    <string name="menu_proc_stats_type_foreground" >"前台"</string>
    <string name="menu_proc_stats_type_cached" >"缓存"</string>
    <string name="voice_input_output_settings" >"语音输入与输出"</string>
    <string name="voice_input_output_settings_title" >"语音输入与输出设置"</string>
    <string name="voice_search_settings_title" >"语音搜索"</string>
    <string name="keyboard_settings_title" >"Android键盘"</string>
    <string name="voice_input_settings" >"语音输入设置"</string>
    <string name="voice_input_settings_title" >"语音输入"</string>
    <string name="voice_service_preference_section_title" >"语音输入服务"</string>
    <string name="voice_interactor_preference_summary" >"全语音启动指令和互动"</string>
    <string name="voice_recognizer_preference_summary" >"简单的语音转文字"</string>
    <string name="voice_interaction_security_warning" >"此语音输入服务能够始终进行语音监测，并能替您控制所有支持语音功能的应用。该服务由“<xliff:g id="VOICE_INPUT_SERVICE_APP_NAME">%s</xliff:g>”提供。要启用此服务吗？"</string>
    <string name="tts_engine_preference_title" >"首选引擎"</string>
    <string name="tts_engine_settings_title" >"引擎设置"</string>
    <string name="tts_sliders_title" >"语速和音调"</string>
    <string name="tts_engine_section_title" >"引擎"</string>
    <string name="tts_install_voice_title" >"语音"</string>
    <string name="tts_spoken_language" >"使用的语言"</string>
    <string name="tts_install_voices_title" >"安装语音"</string>
    <string name="tts_install_voices_text" >"继续使用<xliff:g id="TTS_APP_NAME">%s</xliff:g>应用安装语音"</string>
    <string name="tts_install_voices_open" >"打开应用"</string>
    <string name="tts_install_voices_cancel" >"取消"</string>
    <string name="tts_reset" >"重置"</string>
    <string name="tts_play" >"播放"</string>
    <string name="gadget_title" >"电量控制"</string>
    <string name="gadget_toggle_wifi" >"正在更新WLAN设置"</string>
    <string name="gadget_toggle_bluetooth" >"正在更新蓝牙设置"</string>
    <string name="gadget_state_template" >"<xliff:g id="ID_1">%1$s</xliff:g><xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="gadget_state_on" >"开启"</string>
    <string name="gadget_state_off" >"关闭"</string>
    <string name="gadget_state_turning_on" >"正在开启"</string>
    <string name="gadget_state_turning_off" >"正在关闭"</string>
    <string name="gadget_wifi" >"WLAN"</string>
    <string name="gadget_bluetooth" >"蓝牙"</string>
    <string name="gadget_location" >"位置信息"</string>
    <string name="gadget_sync" >"同步"</string>
    <string name="gadget_brightness_template" >"亮度 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="gadget_brightness_state_auto" >"自动"</string>
    <string name="gadget_brightness_state_full" >"最亮"</string>
    <string name="gadget_brightness_state_half" >"一半"</string>
    <string name="gadget_brightness_state_off" >"关闭"</string>
    <string name="vpn_settings_title" >"VPN"</string>
    <string name="credentials_title" >"凭据存储空间"</string>
    <string name="credentials_install" product="nosdcard" >"从存储设备安装"</string>
    <string name="credentials_install" product="default" >"从SD卡安装"</string>
    <string name="credentials_install_summary" product="nosdcard" >"从存储设备安装证书"</string>
    <string name="credentials_install_summary" product="default" >"从SD卡安装证书"</string>
    <string name="credentials_reset" >"清除凭据"</string>
    <string name="credentials_reset_summary" >"删除所有证书"</string>
    <string name="trusted_credentials" >"信任的凭据"</string>
    <string name="trusted_credentials_summary" >"显示信任的CA证书"</string>
    <string name="user_credentials" >"用户凭据"</string>
    <string name="user_credentials_summary" >"查看和修改存储的凭据"</string>
    <string name="advanced_security_title" >"高级"</string>
    <string name="credential_storage_type" >"存储类型"</string>
    <string name="credential_storage_type_hardware" >"硬件支持"</string>
    <string name="credential_storage_type_software" >"仅限软件"</string>
    <string name="credentials_settings_not_available" >"此用户无法查看或修改凭据"</string>
    <string name="credential_for_vpn_and_apps" >"已安装（用于 VPN 和应用）"</string>
    <string name="credential_for_wifi" >"已安装（用于 WLAN）"</string>
    <string name="credentials_unlock" ></string>
    <string name="credentials_unlock_hint" >"键入凭据存储的密码。"</string>
    <string name="credentials_old_password" >"当前密码："</string>
    <string name="credentials_reset_hint" >"要移除所有内容吗？"</string>
    <string name="credentials_wrong_password" >"密码不正确。"</string>
    <string name="credentials_reset_warning" >"密码不正确。您还可以重试一次，如果输入的密码仍不正确，系统将删除凭据存储。"</string>
    <string name="credentials_reset_warning_plural" >"密码不正确。您还可以重试 <xliff:g id="NUMBER">%1$d</xliff:g> 次，如果输入的密码仍不正确，系统将删除凭据存储。"</string>
    <string name="credentials_erased" >"凭据存储空间已清空。"</string>
    <string name="credentials_not_erased" >"系统无法清除凭据存储。"</string>
    <string name="credentials_enabled" >"凭证存储已启用。"</string>
    <string name="credentials_configure_lock_screen_hint" >"您的设备必须启用安全锁屏功能，才能使用凭据存储"</string>
    <string name="credentials_configure_lock_screen_button" >"设置锁定方式"</string>
    <string name="usage_access_title" >"有权查看使用情况的应用"</string>
    <string name="emergency_tone_title" >"紧急拨号信号"</string>
    <string name="emergency_tone_summary" >"设置进行紧急呼救时的行为"</string>
    <string name="privacy_settings_title" >"备份"</string>
    <string name="backup_section_title" >"备份和还原"</string>
    <string name="personal_data_section_title" >"个人数据"</string>
    <string name="backup_data_title" >"备份我的数据"</string>
    <string name="backup_data_summary" >"将应用数据、WLAN密码和其他设置备份到Google服务器"</string>
    <string name="backup_configure_account_title" >"备份帐号"</string>
    <string name="include_app_data_title" >"包括应用数据"</string>
    <string name="auto_restore_title" >"自动还原"</string>
    <string name="auto_restore_summary" >"重新安装某个应用后，系统会还原已经备份的设置和数据"</string>
    <string name="backup_inactive_title" >"备份服务未启用"</string>
    <string name="backup_configure_account_default_summary" >"目前没有帐号存储备份数据"</string>
    <string name="backup_erase_dialog_title" ></string>
    <string name="backup_erase_dialog_message" >"要停止备份您的WLAN密码、书签、其他设置和应用数据，并清除Google服务器上的所有副本吗？"</string>
    <string name="fullbackup_erase_dialog_message" >"要停止备份设备数据（例如 WLAN 密码和通话记录）和应用数据（例如应用存储的设置和文件），并清除远程服务器上的所有副本吗？"</string>
    <string name="fullbackup_data_summary" >"自动远程备份设备数据（例如 WLAN 密码和通话记录）和应用数据（例如应用存储的设置和文件）。\n\n启用自动备份功能后，系统会定期远程保存设备和应用数据。应用数据可以是应用根据开发者设置保存的任何数据，包括可能比较敏感的数据（例如通讯录、邮件和照片）。"</string>
    <string name="device_admin_settings_title" >"设备管理设置"</string>
    <string name="active_device_admin_msg" >"设备管理应用"</string>
    <string name="remove_device_admin" >"停用此设备管理应用"</string>
    <string name="uninstall_device_admin" >"卸载应用"</string>
    <string name="remove_and_uninstall_device_admin" >"停用并卸载"</string>
    <string name="select_device_admin_msg" >"设备管理应用"</string>
    <string name="no_device_admins" >"没有可用的设备管理应用"</string>
    <string name="personal_device_admin_title" >"个人"</string>
    <string name="managed_device_admin_title" >"工作"</string>
    <string name="no_trust_agents" >"没有可信代理"</string>
    <string name="add_device_admin_msg" >"要启用设备管理应用吗？"</string>
    <string name="add_device_admin" >"启用此设备管理应用"</string>
    <string name="device_admin_add_title" >"设备管理"</string>
    <string name="device_admin_warning" >"启用此管理应用将允许应用“<xliff:g id="APP_NAME">%1$s</xliff:g>”执行以下操作："</string>
    <string name="device_admin_status" >"此管理应用已启用，且允许应用“<xliff:g id="APP_NAME">%1$s</xliff:g>”执行以下操作："</string>
    <string name="profile_owner_add_title" >"要激活个人资料管理器吗？"</string>
    <string name="adding_profile_owner_warning" >"如果继续操作，您所设的用户将由您的管理员进行管理。除了您的个人数据之外，管理员可能还会存储其他相关数据。\n\n您的管理员能够监控和管理与此用户相关的设置、权限、应用以及数据（其中包括网络活动和您设备的位置信息）。"</string>
    <string name="admin_disabled_other_options" >"其他选项已被您的管理员停用"</string>
    <string name="admin_more_details" >"更多详情"</string>
    <string name="sound_category_sound_title" >"常规"</string>
    <string name="notification_log_title" >"通知日志"</string>
    <string name="sound_category_call_ringtone_vibrate_title" >"来电铃声和振动"</string>
    <string name="sound_category_system_title" >"系统"</string>
    <string name="wifi_setup_title" >"WLAN设置"</string>
    <string name="wifi_setup_title_editing_network" >"连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”"</string>
    <string name="wifi_setup_title_connecting_network" >"正在连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”…"</string>
    <string name="wifi_setup_title_connected_network" >"已连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”"</string>
    <string name="wifi_setup_title_add_network" >"添加网络"</string>
    <string name="wifi_setup_not_connected" >"未连接"</string>
    <string name="wifi_setup_add_network" >"添加网络"</string>
    <string name="wifi_setup_refresh_list" >"刷新列表"</string>
    <string name="wifi_setup_skip" >"跳过"</string>
    <string name="wifi_setup_next" >"下一步"</string>
    <string name="wifi_setup_back" >"上一步"</string>
    <string name="wifi_setup_detail" >"网络详情"</string>
    <string name="wifi_setup_connect" >"连接"</string>
    <string name="wifi_setup_forget" >"取消保存"</string>
    <string name="wifi_setup_save" >"保存"</string>
    <string name="wifi_setup_cancel" >"取消"</string>
    <string name="wifi_setup_status_scanning" >"正在扫描网络..."</string>
    <string name="wifi_setup_status_select_network" >"点按某个网络即可与其建立连接"</string>
    <string name="wifi_setup_status_existing_network" >"连接到现有网络"</string>
    <string name="wifi_setup_status_unsecured_network" >"连接到不受保护的网络"</string>
    <string name="wifi_setup_status_edit_network" >"键入网络配置"</string>
    <string name="wifi_setup_status_new_network" >"连接到新的网络"</string>
    <string name="wifi_setup_status_connecting" >"正在连接..."</string>
    <string name="wifi_setup_status_proceed_to_next" >"转至下一步"</string>
    <string name="wifi_setup_status_eap_not_supported" >"不支持EAP。"</string>
    <string name="wifi_setup_eap_not_supported" >"您无法在初次设置过程中配置EAP WLAN连接。初次设置完毕后，您可以在“设置”&gt;“无线和网络”中进行配置。"</string>
    <string name="wifi_setup_description_connecting" >"建立连接可能需要几分钟时间..."</string>
    <string name="wifi_setup_description_connected" >"点按"<b>"下一步"</b>"即可继续设置。\n\n点按"<b>"返回"</b>"可连接到其他 WLAN 网络。"</string>
    <string name="accessibility_sync_enabled" >"同步功能已启用"</string>
    <string name="accessibility_sync_disabled" >"未启用同步功能"</string>
    <string name="accessibility_sync_in_progress" >"正在同步"</string>
    <string name="accessibility_sync_error" >"同步错误。"</string>
    <string name="sync_failed" >"同步失败"</string>
    <string name="sync_active" >"正在同步"</string>
    <string name="account_sync_settings_title" >"同步"</string>
    <string name="sync_is_failing" >"同步操作当前遇到了一些问题，很快便可恢复。"</string>
    <string name="add_account_label" >"添加帐号"</string>
    <string name="managed_profile_not_available_label" >"工作资料尚不可用"</string>
    <string name="work_mode_label" >"工作资料"</string>
    <string name="work_mode_on_summary" >"由贵单位管理"</string>
    <string name="work_mode_off_summary" >"应用和通知均已关闭"</string>
    <string name="remove_managed_profile_label" >"移除工作资料"</string>
    <string name="background_data" >"后台流量"</string>
    <string name="background_data_summary" >"应用可以随时同步、发送和接收数据"</string>
    <string name="background_data_dialog_title" >"要关闭后台流量吗？"</string>
    <string name="background_data_dialog_message" >"停用后台流量可延长电池使用时间并减少流量耗用，但某些应用可能仍会使用后台数据网络。"</string>
    <string name="sync_automatically" >"自动同步应用数据"</string>
    <string name="sync_enabled" >"同步功能已开启"</string>
    <string name="sync_disabled" >"同步功能已关闭"</string>
    <string name="sync_error" >"同步出错"</string>
    <string name="last_synced" >"上次同步时间：<xliff:g id="LAST_SYNC_TIME">%1$s</xliff:g>"</string>
    <string name="sync_in_progress" >"正在同步…"</string>
    <string name="settings_backup" >"备份设置"</string>
    <string name="settings_backup_summary" >"备份我的设置"</string>
    <string name="sync_menu_sync_now" >"立即同步"</string>
    <string name="sync_menu_sync_cancel" >"取消同步"</string>
    <string name="sync_one_time_sync" >"点按即可立即同步<xliff:g id="LAST_SYNC_TIME">
%1$s</xliff:g>"</string>
    <string name="sync_gmail" >"Gmail"</string>
    <string name="sync_calendar" >"日历"</string>
    <string name="sync_contacts" >"联系人"</string>
    <string name="sync_plug" ><font fgcolor="#ffffffff">"欢迎使用 Google Sync！"</font>" \n这款数据同步工具可让您随时随地查看您的联系人、约会以及更多信息。"</string>
    <string name="header_application_sync_settings" >"应用同步设置"</string>
    <string name="header_data_and_synchronization" >"数据与同步"</string>
    <string name="preference_change_password_title" >"更改密码"</string>
    <string name="header_account_settings" >"帐号设置"</string>
    <string name="remove_account_label" >"移除帐号"</string>
    <string name="header_add_an_account" >"添加帐号"</string>
    <string name="finish_button_label" >"完成"</string>
    <string name="really_remove_account_title" >"要移除帐号吗？"</string>
    <string name="really_remove_account_message" product="tablet" >"移除该帐号会从平板电脑中删除所有相关的邮件、联系人以及其他数据。"</string>
    <string name="really_remove_account_message" product="default" >"移除该帐号会从手机中删除所有相关的邮件、联系人以及其它数据。"</string>
    <string name="really_remove_account_message" product="device" >"移除该帐号后，设备上的相关消息、联系人和其他数据也将全部删除！"</string>
    <string name="remove_account_failed" >"您的管理员不允许进行这项更改"</string>
    <string name="cant_sync_dialog_title" >"无法手动同步"</string>
    <string name="cant_sync_dialog_message" >"此项内容的同步功能目前未开启。要更改此设置，请暂时开启后台流量和自动同步功能。"</string>
    <string name="enter_password" >"要启动Android，请输入您的密码"</string>
    <string name="enter_pin" >"要启动Android，请输入您的PIN码"</string>
    <string name="enter_pattern" >"要启动Android，请绘制您的解锁图案"</string>
    <string name="cryptkeeper_wrong_pattern" >"图案错误"</string>
    <string name="cryptkeeper_wrong_password" >"密码错误"</string>
    <string name="cryptkeeper_wrong_pin" >"PIN 码错误"</string>
    <string name="checking_decryption" >"正在检查…"</string>
    <string name="starting_android" >"正在启动 Android…"</string>
    <string name="delete" >"删除"</string>
    <string name="misc_files" >"其他文件"</string>
    <string name="misc_files_selected_count" >"已选择 <xliff:g id="NUMBER">%1$d</xliff:g> 个，共 <xliff:g id="TOTAL">%2$d</xliff:g> 个"</string>
    <string name="misc_files_selected_count_bytes" >"<xliff:g id="NUMBER">%1$s</xliff:g>，共 <xliff:g id="TOTAL">%2$s</xliff:g>"</string>
    <string name="select_all" >"全选"</string>
    <string name="data_usage_summary_title" >"流量使用情况"</string>
    <string name="data_usage_app_summary_title" >"应用的流量使用情况"</string>
    <string name="data_usage_accounting" >"运营商的流量计算方式可能与您设备的计算方式不同。"</string>
    <string name="data_usage_app" >"应用使用情况"</string>
    <string name="data_usage_app_info_label" >"应用信息"</string>
    <string name="data_usage_cellular_data" >"移动数据"</string>
    <string name="data_usage_data_limit" >"设置流量上限"</string>
    <string name="data_usage_cycle" >"流量消耗重置周期"</string>
    <string name="data_usage_app_items_header_text" >"应用数据流量"</string>
    <string name="data_usage_menu_roaming" >"移动数据网络漫游"</string>
    <string name="data_usage_menu_restrict_background" >"限制后台流量"</string>
    <string name="data_usage_menu_allow_background" >"允许使用后台流量"</string>
    <string name="data_usage_menu_split_4g" >"单独显示4G流量"</string>
    <string name="data_usage_menu_show_wifi" >"显示WLAN流量"</string>
    <string name="data_usage_menu_hide_wifi" >"隐藏WLAN流量"</string>
    <string name="data_usage_menu_show_ethernet" >"显示有线网络流量"</string>
    <string name="data_usage_menu_hide_ethernet" >"隐藏有线网络流量"</string>
    <string name="data_usage_menu_metered" >"网络限制"</string>
    <string name="data_usage_menu_auto_sync" >"自动同步数据"</string>
    <string name="data_usage_menu_sim_cards" >"SIM 卡"</string>
    <string name="data_usage_menu_cellular_networks" >"移动网络"</string>
    <string name="data_usage_cellular_data_summary" >"已暂停（达到数据流量上限）"</string>
    <string name="account_settings_menu_auto_sync" >"自动同步数据"</string>
    <string name="account_settings_menu_auto_sync_personal" >"自动同步个人帐号数据"</string>
    <string name="account_settings_menu_auto_sync_work" >"自动同步工作帐号数据"</string>
    <string name="data_usage_change_cycle" >"更改周期..."</string>
    <string name="data_usage_pick_cycle_day" >"每月流量消耗重计日期："</string>
    <string name="data_usage_empty" >"没有任何应用在此期间产生过数据流量。"</string>
    <string name="data_usage_label_foreground" >"前台"</string>
    <string name="data_usage_label_background" >"后台"</string>
    <string name="data_usage_app_restricted" >"受限"</string>
    <string name="data_usage_disable_mobile" >"要关闭移动数据网络吗？"</string>
    <string name="data_usage_disable_mobile_limit" >"设置移动数据流量上限"</string>
    <string name="data_usage_disable_4g_limit" >"设置 4G 数据流量上限"</string>
    <string name="data_usage_disable_3g_limit" >"设置 2G-3G 数据流量上限"</string>
    <string name="data_usage_disable_wifi_limit" >"设置 WLAN 流量上限"</string>
    <string name="data_usage_tab_wifi" >"WLAN"</string>
    <string name="data_usage_tab_ethernet" >"有线网络"</string>
    <string name="data_usage_tab_mobile" >"移动"</string>
    <string name="data_usage_tab_4g" >"4G"</string>
    <string name="data_usage_tab_3g" >"2G-3G"</string>
    <string name="data_usage_list_mobile" >"移动网络"</string>
    <string name="data_usage_list_none" >"无"</string>
    <string name="data_usage_enable_mobile" >"移动数据"</string>
    <string name="data_usage_enable_3g" >"2G-3G 数据"</string>
    <string name="data_usage_enable_4g" >"4G 数据"</string>
    <string name="data_roaming_enable_mobile" >"漫游"</string>
    <string name="data_usage_forground_label" >"前台："</string>
    <string name="data_usage_background_label" >"后台："</string>
    <string name="data_usage_app_settings" >"应用设置"</string>
    <string name="data_usage_app_restrict_background" >"后台数据"</string>
    <string name="data_usage_app_restrict_background_summary" >"允许在后台使用移动数据"</string>
    <string name="data_usage_app_restrict_background_summary_disabled" >"要限制此应用使用后台流量，请先设置移动数据流量上限。"</string>
    <string name="data_usage_app_restrict_dialog_title" >"要限制后台流量吗？"</string>
    <string name="data_usage_app_restrict_dialog" >"只能连接到移动网络时，此功能可能会导致需要使用后台流量的应用无法正常运行。\n\n您可以在相关应用的设置中为其设定更合适的数据流量控制选项。"</string>
    <string name="data_usage_restrict_denied_dialog" >"您必须先设置移动数据流量上限，才能限制后台数据流量。"</string>
    <string name="data_usage_auto_sync_on_dialog_title" >"要打开自动同步数据功能吗？"</string>
    <string name="data_usage_auto_sync_on_dialog" product="tablet" >"您在网络上对自己的帐号进行的所有更改都会自动同步到您的平板电脑。\n\n有些帐号还可以将您在平板电脑上进行的所有更改自动同步到网络上。Google 帐号就支持此类双向同步。"</string>
    <string name="data_usage_auto_sync_on_dialog" product="default" >"您在网络上对自己的帐号进行的所有更改都会自动同步到您的手机。\n\n有些帐号还可以将您在手机上进行的所有更改自动同步到网络上。Google 帐号就支持此类双向同步。"</string>
    <string name="data_usage_auto_sync_off_dialog_title" >"要关闭自动同步数据功能吗？"</string>
    <string name="data_usage_auto_sync_off_dialog" >"这样可以节省数据流量和电池电量，但您需要手动同步每个帐号才能获得最新信息，并且在有更新时不会收到通知。"</string>
    <string name="data_usage_cycle_editor_title" >"流量消耗重置日期"</string>
    <string name="data_usage_cycle_editor_subtitle" >"日期："</string>
    <string name="data_usage_cycle_editor_positive" >"设置"</string>
    <string name="data_usage_warning_editor_title" >"设置流量用量警告"</string>
    <string name="data_usage_limit_editor_title" >"设置流量使用上限"</string>
    <string name="data_usage_limit_dialog_title" >"限制流量用量"</string>
    <string name="data_usage_limit_dialog_mobile" product="tablet" >"当移动数据流量的使用量达到您设置的上限时，您的平板电脑将关闭移动数据网络。\n\n由于流量用量是由您的平板电脑计算的，而您的运营商对流量用量的计算方式可能有所不同，因此建议您设置一个保守的上限值。"</string>
    <string name="data_usage_limit_dialog_mobile" product="default" >"当移动数据流量的使用量达到您设置的上限时，您的手机将关闭移动数据网络。\n\n由于流量用量是由您的手机计算的，而您的运营商对流量用量的计算方式可能有所不同，因此建议您设置一个保守的上限值。"</string>
    <string name="data_usage_restrict_background_title" >"要限制后台流量吗？"</string>
    <string name="data_usage_restrict_background" >"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。"</string>
    <string name="data_usage_restrict_background_multiuser" product="tablet" >"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。\n\n此设置会影响这部平板电脑上的所有用户。"</string>
    <string name="data_usage_restrict_background_multiuser" product="default" >"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。\n\n此设置会影响这部手机上的所有用户。"</string>
    <string name="data_usage_sweep_warning" ><font size="18">"<xliff:g id="NUMBER">^1</xliff:g>"</font>" "<font size="9">"<xliff:g id="UNIT">^2</xliff:g>"</font>\n<font size="12">"警告"</font></string>
    <string name="data_usage_sweep_limit" ><font size="18">"<xliff:g id="NUMBER">^1</xliff:g>"</font>" "<font size="9">"<xliff:g id="UNIT">^2</xliff:g>"</font>\n<font size="12">"上限"</font></string>
    <string name="data_usage_uninstalled_apps" >"已删除的应用"</string>
    <string name="data_usage_uninstalled_apps_users" >"已删除的应用和用户"</string>
    <string name="data_usage_received_sent" >"已接收<xliff:g id="RECEIVED">%1$s</xliff:g>，已发送<xliff:g id="SENT">%2$s</xliff:g>"</string>
    <string name="data_usage_total_during_range" >"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>。"</string>
    <string name="data_usage_total_during_range_mobile" product="tablet" >"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>，由您的平板电脑计算得出。您的运营商对于流量的计算方法可能有所不同。"</string>
    <string name="data_usage_total_during_range_mobile" product="default" >"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>，由您的手机计算得出。您的运营商对于流量的计算方法可能有所不同。"</string>
    <string name="data_usage_metered_title" >"网络限制"</string>
    <string name="data_usage_metered_body" >"当后台数据流量受到限制时，系统会将按流量计费的网络视为移动网络。在使用这类网络下载大量数据前，应用可能会发出警告消息。"</string>
    <string name="data_usage_metered_mobile" >"移动网络"</string>
    <string name="data_usage_metered_wifi" >"按流量计费的WLAN网络"</string>
    <string name="data_usage_metered_wifi_disabled" >"要选择按流量计费的网络，请开启WLAN网络。"</string>
    <string name="data_usage_metered_auto" >"自动"</string>
    <string name="data_usage_metered_yes" >"按流量计费"</string>
    <string name="data_usage_metered_no" >"不按流量计费"</string>
    <string name="data_usage_disclaimer" >"运营商的流量计算方式可能与您设备的计算方式不同。"</string>
    <string name="cryptkeeper_emergency_call" >"紧急呼救"</string>
    <string name="cryptkeeper_return_to_call" >"返回通话"</string>
    <string name="vpn_name" >"名称"</string>
    <string name="vpn_type" >"类型"</string>
    <string name="vpn_server" >"服务器地址"</string>
    <string name="vpn_mppe" >"PPP 加密 (MPPE)"</string>
    <string name="vpn_l2tp_secret" >"L2TP 密钥"</string>
    <string name="vpn_ipsec_identifier" >"IPSec 标识符"</string>
    <string name="vpn_ipsec_secret" >"IPSec 预共享密钥"</string>
    <string name="vpn_ipsec_user_cert" >"IPSec 用户证书"</string>
    <string name="vpn_ipsec_ca_cert" >"IPSec CA证书"</string>
    <string name="vpn_ipsec_server_cert" >"IPSec 服务器证书"</string>
    <string name="vpn_show_options" >"显示高级选项"</string>
    <string name="vpn_search_domains" >"DNS搜索网域"</string>
    <string name="vpn_dns_servers" >"DNS服务器（例如8.8.8.8）"</string>
    <string name="vpn_routes" >"转发路线（例如10.0.0.0/8）"</string>
    <string name="vpn_username" >"用户名"</string>
    <string name="vpn_password" >"密码"</string>
    <string name="vpn_save_login" >"保存帐号信息"</string>
    <string name="vpn_not_used" >"（未使用）"</string>
    <string name="vpn_no_ca_cert" >"（不验证服务器）"</string>
    <string name="vpn_no_server_cert" >"（来自服务器）"</string>
    <string name="vpn_always_on_invalid_reason_type" >"此 VPN 类型无法随时保持连接"</string>
    <string name="vpn_always_on_invalid_reason_server" >"始终开启的 VPN 仅支持数字格式的服务器地址"</string>
    <string name="vpn_always_on_invalid_reason_no_dns" >"必须为始终开启的 VPN 指定 DNS 服务器"</string>
    <string name="vpn_always_on_invalid_reason_dns" >"DNS 服务器地址必须为数字才能使用始终开启的 VPN"</string>
    <string name="vpn_always_on_invalid_reason_other" >"输入的信息不支持始终开启的 VPN"</string>
    <string name="vpn_cancel" >"取消"</string>
    <string name="vpn_done" >"关闭"</string>
    <string name="vpn_save" >"保存"</string>
    <string name="vpn_connect" >"连接"</string>
    <string name="vpn_replace" >"替换"</string>
    <string name="vpn_edit" >"编辑 VPN 配置文件"</string>
    <string name="vpn_forget" >"取消保存"</string>
    <string name="vpn_connect_to" >"连接到<xliff:g id="PROFILE">%s</xliff:g>"</string>
    <string name="vpn_disconnect_confirm" >"要断开与此 VPN 的连接吗？"</string>
    <string name="vpn_disconnect" >"断开连接"</string>
    <string name="vpn_version" >"版本 <xliff:g id="VERSION">%s</xliff:g>"</string>
    <string name="vpn_forget_long" >"取消保存 VPN"</string>
    <string name="vpn_replace_vpn_title" >"要替换现有 VPN 吗？"</string>
    <string name="vpn_set_vpn_title" >"要设置始终开启的 VPN 吗？"</string>
    <string name="vpn_first_always_on_vpn_message" >"开启这项设置后，在 VPN 成功连接之前，您将无法连接到互联网"</string>
    <string name="vpn_replace_always_on_vpn_enable_message" >"系统将替换现有 VPN，而且在 VPN 成功连接之前，您将无法连接到互联网"</string>
    <string name="vpn_replace_always_on_vpn_disable_message" >"您已连接到某个始终开启的 VPN。如果您要连接到其他 VPN，则系统将替换现有 VPN，并关闭始终开启模式。"</string>
    <string name="vpn_replace_vpn_message" >"您已连接到 VPN。如果您要连接到其他 VPN，则系统将替换现有 VPN。"</string>
    <string name="vpn_turn_on" >"开启"</string>
    <string name="vpn_cant_connect_title" >"无法连接到<xliff:g id="VPN_NAME">%1$s</xliff:g>"</string>
    <string name="vpn_cant_connect_message" >"此应用不支持始终开启的 VPN"</string>
    <string name="vpn_title" >"VPN"</string>
    <string name="vpn_create" >"添加VPN配置文件"</string>
    <string name="vpn_menu_edit" >"修改配置文件"</string>
    <string name="vpn_menu_delete" >"删除配置文件"</string>
    <string name="vpn_menu_lockdown" >"始终开启的 VPN"</string>
    <string name="vpn_no_vpns_added" >"尚未添加任何 VPN"</string>
    <string name="vpn_always_on_summary" >"随时和 VPN 保持连接"</string>
    <string name="vpn_always_on_summary_not_supported" >"不受此应用支持"</string>
    <string name="vpn_always_on_summary_active" >"已启用“始终开启”模式"</string>
    <string name="vpn_require_connection" >"屏蔽未使用 VPN 的所有连接"</string>
    <string name="vpn_require_connection_title" >"需要连接 VPN？"</string>
    <string name="vpn_lockdown_summary" >"选择要始终保持连接的VPN配置文件。只有在连接到此VPN之后才可以使用网络。"</string>
    <string name="vpn_lockdown_none" >"无"</string>
    <string name="vpn_lockdown_config_error" >"始终开启的 VPN 需要服务器和 DNS 的 IP 地址。"</string>
    <string name="vpn_no_network" >"目前没有网络连接。请稍后重试。"</string>
    <string name="vpn_disconnected" >"VPN 连接已断开"</string>
    <string name="vpn_disconnected_summary" >"无"</string>
    <string name="vpn_missing_cert" >"证书缺失。请尝试修改个人资料。"</string>
    <string name="trusted_credentials_system_tab" >"系统"</string>
    <string name="trusted_credentials_user_tab" >"用户"</string>
    <string name="trusted_credentials_disable_label" >"停用"</string>
    <string name="trusted_credentials_enable_label" >"启用"</string>
    <string name="trusted_credentials_remove_label" >"删除"</string>
    <string name="trusted_credentials_trust_label" >"信任"</string>
    <string name="trusted_credentials_enable_confirmation" >"要启用该系统CA证书吗？"</string>
    <string name="trusted_credentials_disable_confirmation" >"要停用该系统CA证书吗？"</string>
    <string name="trusted_credentials_remove_confirmation" >"要永久移除该用户CA证书吗？"</string>
    <string name="credential_contains" >"此条目包含："</string>
    <string name="one_userkey" >"一个用户密钥"</string>
    <string name="one_usercrt" >"一个用户证书"</string>
    <string name="one_cacrt" >"1 个 CA 证书"</string>
    <string name="n_cacrts" >"%d 个 CA 证书"</string>
    <string name="user_credential_title" >"凭据详情"</string>
    <string name="user_credential_removed" >"已移除以下凭据：<xliff:g id="CREDENTIAL_NAME">%s</xliff:g>"</string>
    <string name="user_credential_none_installed" >"未安装任何用户凭据"</string>
    <string name="spellcheckers_settings_title" >"拼写检查工具"</string>
    <string name="current_backup_pw_prompt" >"在此键入您当前的完整备份密码"</string>
    <string name="new_backup_pw_prompt" >"在此键入新的完整备份密码"</string>
    <string name="confirm_new_backup_pw_prompt" >"在此重新键入新的完整备份密码"</string>
    <string name="backup_pw_set_button_text" >"设置备份密码"</string>
    <string name="backup_pw_cancel_button_text" >"取消"</string>
    <string name="additional_system_update_settings_list_item_title" >"其他系统更新"</string>
    <string name="ssl_ca_cert_warning" >"网络可能会受到监控"</string>
    <string name="done_button" >"完成"</string>
    <plurals name="ssl_ca_cert_dialog_title" formatted="false" >
      <item quantity="other">信任或移除证书</item>
      <item quantity="one">信任或移除证书</item>
    </plurals>
    <plurals name="ssl_ca_cert_info_message_device_owner" formatted="false" >
      <item quantity="other"><xliff:g id="MANAGING_DOMAIN_1">%s</xliff:g> 已在您的设备上安装多个证书授权中心，它们可用于监控您的设备网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解这些证书，请与您的管理员联系。</item>
      <item quantity="one"><xliff:g id="MANAGING_DOMAIN_0">%s</xliff:g> 已在您的设备上安装一个证书授权中心，它可用于监控您的设备网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解此证书，请与您的管理员联系。</item>
    </plurals>
    <plurals name="ssl_ca_cert_info_message" formatted="false" >
      <item quantity="other"><xliff:g id="MANAGING_DOMAIN_1">%s</xliff:g> 已为您的工作资料安装多个证书授权中心，它们可用于监控您的工作网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解这些证书，请与您的管理员联系。</item>
      <item quantity="one"><xliff:g id="MANAGING_DOMAIN_0">%s</xliff:g> 已为您的工作资料安装一个证书授权中心，它可用于监控您的工作网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解此证书，请与您的管理员联系。</item>
    </plurals>
    <string name="ssl_ca_cert_warning_message" >"第三方可以监控您的网络活动，包括收发电子邮件、使用应用和浏览安全网站。\n\n出现这种情况的原因是您在设备上安装了信任的凭据。"</string>
    <plurals name="ssl_ca_cert_settings_button" formatted="false" >
      <item quantity="other">检查证书</item>
      <item quantity="one">检查证书</item>
    </plurals>
    <string name="user_settings_title" >"多用户"</string>
    <string name="user_list_title" >"用户和个人资料"</string>
    <string name="user_add_user_or_profile_menu" >"添加用户或个人资料"</string>
    <string name="user_add_user_menu" >"添加用户"</string>
    <string name="user_summary_restricted_profile" >"受限个人资料"</string>
    <string name="user_need_lock_message" >"您需要先设置锁定屏幕来保护您的应用和个人数据，然后才可以创建受限个人资料。"</string>
    <string name="user_set_lock_button" >"设置屏幕锁定方式"</string>
    <string name="user_summary_not_set_up" >"未设置"</string>
    <string name="user_summary_restricted_not_set_up" >"未设置 - 受限个人资料"</string>
    <string name="user_summary_managed_profile_not_set_up" >"未设置 - 工作资料"</string>
    <string name="user_admin" >"管理员"</string>
    <string name="user_you" >"您（<xliff:g id="NAME">%s</xliff:g>）"</string>
    <string name="user_nickname" >"昵称"</string>
    <string name="user_add_user_type_title" >"添加"</string>
    <string name="user_add_max_count" >"您最多可添加 <xliff:g id="USER_COUNT">%1$d</xliff:g> 位用户"</string>
    <string name="user_add_user_item_summary" >"用户拥有个人专属的应用和内容"</string>
    <string name="user_add_profile_item_summary" >"您可以限制其他人使用来自您的帐号的应用和内容"</string>
    <string name="user_add_user_item_title" >"用户"</string>
    <string name="user_add_profile_item_title" >"受限个人资料"</string>
    <string name="user_add_user_title" >"要添加新用户吗？"</string>
    <string name="user_add_user_message_long" >"创建新用户后，您就能够与其他人共用此设备。每位用户都有自己的专属空间，而且在自己的个人空间内还可以自行安装自己想要的应用，设置壁纸等。此外，用户还可以调整会影响所有用户的设备设置（例如 WLAN 设置）。\n\n当您添加新用户后，该用户需要自行设置个人空间。\n\n任何用户都可以为所有其他用户更新应用。无障碍功能设置和服务可能无法转移给新用户。"</string>
    <string name="user_add_user_message_short" >"您添加新用户后，该用户必须设置自己的空间。\n\n任何用户均可为其他所有用户更新应用。"</string>
    <string name="user_setup_dialog_title" >"要现在设置该用户吗？"</string>
    <string name="user_setup_dialog_message" >"请让相应用户操作设备并设置他们自己的空间。"</string>
    <string name="user_setup_profile_dialog_message" >"要立即设置个人资料吗？"</string>
    <string name="user_setup_button_setup_now" >"立即设置"</string>
    <string name="user_setup_button_setup_later" >"以后再说"</string>
    <string name="user_cannot_manage_message" product="tablet" >"只有平板电脑的机主可以管理用户。"</string>
    <string name="user_cannot_manage_message" product="default" >"只有手机的机主可以管理用户。"</string>
    <string name="user_cannot_add_accounts_message" >"受限个人资料无法添加帐号"</string>
    <string name="user_remove_user_menu" >"将<xliff:g id="USER_NAME">%1$s</xliff:g>从此设备中删除"</string>
    <string name="user_lockscreen_settings" >"锁定屏幕设置"</string>
    <string name="user_add_on_lockscreen_menu" >"允许屏幕处于锁定状态时添加用户"</string>
    <string name="user_new_user_name" >"新用户"</string>
    <string name="user_new_profile_name" >"新的个人资料"</string>
    <string name="user_confirm_remove_self_title" >"是否删除自己？"</string>
    <string name="user_confirm_remove_title" >"要移除此用户吗？"</string>
    <string name="user_profile_confirm_remove_title" >"要移除此个人资料吗？"</string>
    <string name="work_profile_confirm_remove_title" >"要移除工作资料吗？"</string>
    <string name="user_confirm_remove_self_message" product="tablet" >"您将丢失自己在这台平板电脑上的空间和数据，此操作无法撤消。"</string>
    <string name="user_confirm_remove_self_message" product="default" >"您将丢失自己在这部手机上的空间和数据，此操作无法撤消。"</string>
    <string name="user_confirm_remove_message" >"该用户名下的所有应用和数据都将随之删除。"</string>
    <string name="work_profile_confirm_remove_message" >"如果继续，则此工作资料中的所有应用和数据都会被删除。"</string>
    <string name="user_profile_confirm_remove_message" >"该用户名下的所有应用和数据都将随之删除。"</string>
    <string name="user_adding_new_user" >"正在添加新用户…"</string>
    <string name="user_delete_user_description" >"删除用户"</string>
    <string name="user_delete_button" >"删除"</string>
    <string name="user_guest" >"访客"</string>
    <string name="user_exit_guest_title" >"移除访客"</string>
    <string name="user_exit_guest_confirm_title" >"要移除访客吗？"</string>
    <string name="user_exit_guest_confirm_message" >"此会话中的所有应用和数据都将被删除。"</string>
    <string name="user_exit_guest_dialog_remove" >"移除"</string>
    <string name="user_enable_calling" >"开启通话功能"</string>
    <string name="user_enable_calling_sms" >"开启通话和短信功能"</string>
    <string name="user_remove_user" >"移除用户"</string>
    <string name="user_enable_calling_confirm_title" >"要开启通话功能吗？"</string>
    <string name="user_enable_calling_confirm_message" >"将与此用户共享通话记录。"</string>
    <string name="user_enable_calling_and_sms_confirm_title" >"要开启通话和短信功能吗？"</string>
    <string name="user_enable_calling_and_sms_confirm_message" >"将与此用户共享通话记录和短信记录。"</string>
    <string name="emergency_info_title" >"急救信息"</string>
    <string name="emergency_info_summary" >"<xliff:g id="USER_NAME">%1$s</xliff:g>的相关信息和联系人信息"</string>
    <string name="application_restrictions" >"允许应用和内容"</string>
    <string name="apps_with_restrictions_header" >"受限应用"</string>
    <string name="apps_with_restrictions_settings_button" >"展开应用设置"</string>
    <string name="nfc_payment_settings_title" >"触碰付款"</string>
    <string name="nfc_payment_how_it_works" >"工作原理"</string>
    <string name="nfc_payment_no_apps" >"使用手机在商店内付款"</string>
    <string name="nfc_payment_default" >"默认付款应用"</string>
    <string name="nfc_payment_default_not_set" >"未设置"</string>
    <string name="nfc_payment_app_and_desc" >"<xliff:g id="APP">%1$s</xliff:g> - <xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="nfc_payment_use_default" >"使用默认应用"</string>
    <string name="nfc_payment_favor_default" >"始终"</string>
    <string name="nfc_payment_favor_open" >"但其他付款应用开启时除外"</string>
    <string name="nfc_payment_pay_with" >"通过触碰付款终端进行付款时使用以下应用："</string>
    <string name="nfc_how_it_works_title" >"通过支付终端付款"</string>
    <string name="nfc_how_it_works_content" >"设置付款应用，然后只需将手机背面靠近任何带有非接触标志的终端即可。"</string>
    <string name="nfc_how_it_works_got_it" >"知道了"</string>
    <string name="nfc_more_title" >"更多…"</string>
    <string name="nfc_payment_set_default_label" >"要设为您的偏好设置吗？"</string>
    <string name="nfc_payment_set_default" >"触碰付款时一律使用<xliff:g id="APP">%1$s</xliff:g>吗？"</string>
    <string name="nfc_payment_set_default_instead_of" >"触碰付款时一律使用<xliff:g id="APP_0">%1$s</xliff:g>（而非<xliff:g id="APP_1">%2$s</xliff:g>）吗？"</string>
    <string name="restriction_settings_title" >"限制"</string>
    <string name="restriction_menu_reset" >"取消限制"</string>
    <string name="restriction_menu_change_pin" >"更改PIN码"</string>
    <string name="app_notifications_switch_label" >"显示通知"</string>
    <string name="help_label" >"帮助和反馈"</string>
    <string name="support_summary" >"帮助文章、电话与聊天支持、使用入门"</string>
    <string name="user_account_title" >"内容帐号"</string>
    <string name="user_picture_title" >"照片 ID"</string>
    <string name="extreme_threats_title" >"极端威胁"</string>
    <string name="extreme_threats_summary" >"接收有关生命和财产极端威胁的警报"</string>
    <string name="severe_threats_title" >"严重威胁"</string>
    <string name="severe_threats_summary" >"接收有关生命和财产严重威胁的警报"</string>
    <string name="amber_alerts_title" >"安珀警报"</string>
    <string name="amber_alerts_summary" >"接收有关儿童被绑架/拐骗的公告"</string>
    <string name="repeat_title" >"重复"</string>
    <string name="call_manager_enable_title" >"启用通话管理器"</string>
    <string name="call_manager_enable_summary" >"允许此服务管理您的通话。"</string>
    <string name="call_manager_title" >"通话管理器"</string>
    <!-- no translation found for call_manager_summary (5918261959486952674) -->
    <skip />
    <string name="cell_broadcast_settings" >"紧急警报"</string>
    <string name="network_operators_settings" >"网络运营商"</string>
    <string name="access_point_names" >"接入点名称(APN)"</string>
    <string name="enhanced_4g_lte_mode_title" >"增强型4G LTE模式"</string>
    <string name="enhanced_4g_lte_mode_summary" >"使用 LTE 数据网络提升语音和通信质量（推荐）"</string>
    <string name="preferred_network_type_title" >"首选网络类型"</string>
    <string name="preferred_network_type_summary" >"LTE（推荐）"</string>
    <string name="work_sim_title" >"工作用SIM卡"</string>
    <string name="user_restrictions_title" >"应用和内容使用权"</string>
    <string name="user_rename" >"重命名"</string>
    <string name="app_restrictions_custom_label" >"设置应用限制"</string>
    <string name="user_restrictions_controlled_by" >"受“<xliff:g id="APP">%1$s</xliff:g>”控制"</string>
    <string name="app_sees_restricted_accounts" >"此应用可使用您的帐号"</string>
    <string name="app_sees_restricted_accounts_and_controlled_by" >"此应用由“<xliff:g id="APP">%1$s</xliff:g>”控制，可使用您的帐号"</string>
    <string name="restriction_wifi_config_title" >"WLAN和移动网络"</string>
    <string name="restriction_wifi_config_summary" >"允许修改WLAN和移动网络设置"</string>
    <string name="restriction_bluetooth_config_title" >"蓝牙"</string>
    <string name="restriction_bluetooth_config_summary" >"允许修改蓝牙配对和设置"</string>
    <string name="restriction_nfc_enable_title" >"NFC"</string>
    <string name="restriction_nfc_enable_summary_config" >"允许在此<xliff:g id="DEVICE_NAME">%1$s</xliff:g>与其他NFC设备触碰时交换数据"</string>
    <string name="restriction_nfc_enable_summary" product="tablet" >"允许在平板电脑与其他设备触碰时交换数据"</string>
    <string name="restriction_nfc_enable_summary" product="default" >"允许在手机与其他设备触碰时交换数据"</string>
    <string name="restriction_location_enable_title" >"位置信息"</string>
    <string name="restriction_location_enable_summary" >"允许应用使用您的位置信息"</string>
    <string name="wizard_back" >"返回"</string>
    <string name="wizard_next" >"下一步"</string>
    <string name="wizard_finish" >"完成"</string>
    <string name="user_image_take_photo" >"拍照"</string>
    <string name="user_image_choose_photo" >"从图库中选择照片"</string>
    <string name="user_image_photo_selector" >"选择照片"</string>
    <string name="regulatory_info_text" ></string>
    <string name="sim_setup_wizard_title" >"SIM 卡"</string>
    <string name="sim_settings_title" >"SIM 卡"</string>
    <string name="sim_settings_summary" >"<xliff:g id="SIM_NAME">%1$s</xliff:g> - <xliff:g id="SIM_NUMBER">%2$s</xliff:g>"</string>
    <string name="sim_cards_changed_message" >"SIM 卡已更改"</string>
    <string name="sim_cards_changed_message_summary" >"点按即可设置活动"</string>
    <string name="sim_cellular_data_unavailable" >"无法使用移动数据网络"</string>
    <string name="sim_cellular_data_unavailable_summary" >"点按即可选择上网用的 SIM 卡"</string>
    <string name="sim_calls_always_use" >"一律使用这张卡进行通话"</string>
    <string name="select_sim_for_data" >"选择用于数据网络的 SIM 卡"</string>
    <string name="data_switch_started" >"正在切换用于连接数据网络的 SIM 卡，这最多可能需要 1 分钟的时间…"</string>
    <string name="select_sim_for_calls" >"选择用于通话的 SIM 卡"</string>
    <string name="sim_select_card" >"选择 SIM 卡"</string>
    <string name="sim_card_number_title" >"SIM 卡 <xliff:g id="CARD_NUMBER">%1$d</xliff:g>"</string>
    <string name="sim_slot_empty" >"未插入SIM卡"</string>
    <string name="sim_editor_name" >"SIM 卡名称"</string>
    <string name="sim_name_hint" >"输入 SIM 卡名称"</string>
    <string name="sim_editor_title" >"SIM 卡插槽 %1$d"</string>
    <string name="sim_editor_carrier" >"运营商"</string>
    <string name="sim_editor_number" >"号码"</string>
    <string name="sim_editor_color" >"SIM卡颜色"</string>
    <string name="sim_card_select_title" >"选择 SIM 卡"</string>
    <string name="color_orange" >"橙色"</string>
    <string name="color_purple" >"紫色"</string>
    <string name="sim_no_inserted_msg" >"尚未插入SIM卡"</string>
    <string name="sim_status_title" >"SIM 卡状态"</string>
    <string name="sim_status_title_sim_slot" >"SIM 卡状态（SIM 卡插槽 %1$d）"</string>
    <string name="sim_call_back_title" >"使用默认SIM卡回电"</string>
    <string name="sim_outgoing_call_title" >"用于外拨电话的 SIM 卡"</string>
    <string name="sim_other_call_settings" >"其他通话设置"</string>
    <string name="preferred_network_offload_title" >"首选分流网络"</string>
    <string name="preferred_network_offload_header" >"关闭网络名称广播"</string>
    <string name="preferred_network_offload_footer" >"关闭网络名称广播可防止第三方获取您的网络信息。"</string>
    <string name="preferred_network_offload_popup" >"关闭网络名称广播可防止设备自动连接到隐藏的网络。"</string>
    <string name="sim_signal_strength" >"<xliff:g id="DBM">%1$d</xliff:g> dBm <xliff:g id="ASU">%2$d</xliff:g> asu"</string>
    <string name="sim_notification_title" >"SIM 卡已更改。"</string>
    <string name="sim_notification_summary" >"点按即可进行设置"</string>
    <string name="sim_pref_divider" >"首选 SIM 卡"</string>
    <string name="sim_calls_ask_first_prefs_title" >"每次都询问"</string>
    <string name="sim_selection_required_pref" >"必须选择"</string>
    <string name="sim_selection_channel_title" >"SIM 卡选择"</string>
    <string name="dashboard_title" >"设置"</string>
    <plurals name="settings_suggestion_header_summary_hidden_items" formatted="false" >
      <item quantity="other">显示 %d 项隐藏内容</item>
      <item quantity="one">显示 %d 项隐藏内容</item>
    </plurals>
    <string name="dashboard_suggestion_condition_footer_content_description" >"收起"</string>
    <string name="network_dashboard_title" >"网络和互联网"</string>
    <string name="network_dashboard_summary_mobile" >"移动网络"</string>
    <string name="network_dashboard_summary_data_usage" >"流量使用"</string>
    <string name="network_dashboard_summary_hotspot" >"热点"</string>
    <string name="connected_devices_dashboard_title" >"已连接的设备"</string>
    <string name="connected_devices_dashboard_summary" >"蓝牙、驾车模式、NFC"</string>
    <string name="connected_devices_dashboard_no_nfc_summary" >"蓝牙、驾车模式"</string>
    <string name="connected_devices_dashboard_no_driving_mode_summary" >"蓝牙、NFC"</string>
    <string name="connected_devices_dashboard_no_driving_mode_no_nfc_summary" >"蓝牙"</string>
    <string name="app_and_notification_dashboard_title" >"应用和通知"</string>
    <string name="app_and_notification_dashboard_summary" >"权限、默认应用"</string>
    <string name="account_dashboard_title" >"帐号"</string>
    <string name="account_dashboard_default_summary" >"未添加任何帐号"</string>
    <string name="app_default_dashboard_title" >"默认应用"</string>
    <string name="system_dashboard_summary" >"语言、时间、备份、更新"</string>
    <string name="search_results_title" >"设置"</string>
    <string name="search_menu" >"在设置中搜索"</string>
    <string name="keywords_wifi" >"wifi, WLAN, 网络连接, 互联网, 无线, 数据, WLAN 网络"</string>
    <string name="keywords_change_wifi_state" >"WLAN, wlan, 切换, 控制"</string>
    <string name="keywords_more_default_sms_app" >"短信, 发短信, 消息, 发消息, 默认"</string>
    <string name="keywords_more_mobile_networks" >"移动网络, 移动, 手机运营商, 无线, 数据, 4G, 3G, 2G, LTE"</string>
    <string name="keywords_wifi_calling" >"Wi-Fi, WLAN, 呼叫, 通话"</string>
    <string name="keywords_home" >"启动器, 默认, 应用"</string>
    <string name="keywords_display" >"屏幕, 触摸屏"</string>
    <string name="keywords_display_brightness_level" >"屏幕变暗, 调暗屏幕, 触摸屏, 电池, 明亮"</string>
    <string name="keywords_display_auto_brightness" >"屏幕变暗, 调暗屏幕, 触摸屏, 电池"</string>
    <string name="keywords_display_night_display" >"屏幕变暗, 调暗屏幕, 夜间, 色调, night shift, 夜视模式, 亮度, 屏幕颜色, 颜色, 色彩"</string>
    <string name="keywords_display_wallpaper" >"背景, 墙纸, 个性化, 自定义, 显示, 显示屏"</string>
    <string name="keywords_display_font_size" >"字体大小"</string>
    <string name="keywords_display_cast_screen" >"投影, 投射"</string>
    <string name="keywords_storage" >"空间, 磁盘, 硬盘, 设备, 使用量, 使用情况"</string>
    <string name="keywords_battery" >"耗电量, 充电"</string>
    <string name="keywords_spell_checker" >"拼写, 字典, 词典, 拼写检查, 自动更正"</string>
    <string name="keywords_voice_input" >"识别程序, 输入, 语音, 说出, 语言, 免触摸, 免提, 识别, 令人反感, 字词, 音频, 记录, 蓝牙耳机"</string>
    <string name="keywords_text_to_speech_output" >"语速, 语言, 默认, 说出, 语音, TTS, 无障碍, 屏幕阅读器, 盲人"</string>
    <string name="keywords_date_and_time" >"时钟, 军用"</string>
    <string name="keywords_network_reset" >"重置, 恢复, 出厂设置"</string>
    <string name="keywords_factory_data_reset" >"擦除, 删除, 恢复, 清除, 移除, 恢复出厂设置"</string>
    <string name="keywords_printing" >"打印机"</string>
    <string name="keywords_sounds" >"扬声器提示音, 扬声器, 音量, 静音, 无声, 音频, 音乐"</string>
    <string name="keywords_sounds_and_notifications_interruptions" >"勿扰, 请勿打扰, 打扰, 打断"</string>
    <string name="keywords_app" >"RAM 内存"</string>
    <string name="keywords_location" >"附近, 位置信息, 位置, 记录, 历史记录, 报告"</string>
    <string name="keywords_accounts" >"帐号"</string>
    <string name="keywords_users" >"限制, 限定, 受限"</string>
    <string name="keywords_keyboard_and_ime" >"文字, 文本, 更正, 声音, 提示音, 振动, 自动, 语言, 手势, 推荐, 建议, 主题, 主题背景, 令人反感, 字词, 输入, 表情符号, 国际"</string>
    <string name="keywords_reset_apps" >"重置, 偏好设置, 默认"</string>
    <string name="keywords_emergency_app" >"紧急, 在紧急情况下使用, 应用, 默认"</string>
    <string name="keywords_default_phone_app" >"电话, 拨号器, 默认"</string>
    <string name="keywords_all_apps" >"应用, 下载, 应用程序, 系统"</string>
    <string name="keywords_app_permissions" >"应用, 权限, 安全"</string>
    <string name="keywords_default_apps" >"应用, 默认"</string>
    <string name="keywords_ignore_optimizations" >"忽略优化, 低电耗模式, 应用待机模式"</string>
    <string name="keywords_color_mode" >"鲜亮, RGB, SRGB, 颜色, 自然, 标准"</string>
    <string name="keywords_color_temperature" >"颜色, 温度, D65, D73, 白色, 黄色, 蓝色, 暖色, 冷色"</string>
    <string name="keywords_lockscreen" >"滑动解锁, 密码, 图案, PIN 码"</string>
    <string name="keywords_profile_challenge" >"工作验证, 工作, 资料"</string>
    <string name="keywords_unification" >"工作资料, 托管资料, 汇整, 统一, 工作, 资料"</string>
    <string name="keywords_gesture" >"手势"</string>
    <string name="keywords_payment_settings" >"支付、点按、付款"</string>
    <string name="keywords_backup" >"备份内容, 备份"</string>
    <string name="keywords_assist_gesture_launch" >"手势"</string>
    <string name="keywords_imei_info" >"IMEI, MEID, MIN, PRL 版本, IMEI SV"</string>
    <string name="keywords_sim_status" >"网络, 移动网络状态, 服务状态, 信号强度, 移动网络类型, 漫游, ICCID"</string>
    <string name="keywords_model_and_hardware" >"序列号, 硬件版本"</string>
    <string name="keywords_android_version" >"Android 安全补丁程序级别, 基带版本, 内核版本"</string>
    <string name="keywords_ambient_display_screen" >"主动显示, 锁定屏幕显示"</string>
    <string name="keywords_fingerprint_settings" >"指纹"</string>
    <string name="keywords_auto_rotate" >"旋转, 翻转, 纵向, 横向, 屏幕方向, 垂直, 水平"</string>
    <string name="keywords_system_update_settings" >"升级, android"</string>
    <string name="keywords_zen_mode_settings" >"勿扰, 时间表, 通知, 屏蔽, 设为静音, 振动, 休眠, 工作, 焦点, 声音, 静音, 日, 工作日, 周末, 工作日晚上, 活动"</string>
    <string name="keywords_screen_timeout" >"屏幕, 锁定时间, 超时, 锁屏"</string>
    <string name="keywords_storage_settings" >"存储设备, 数据, 删除, 清除, 免费, 空间"</string>
    <string name="keywords_bluetooth_settings" >"已连接, 设备, 头戴式耳机, 耳机, 扬声器, 无线, 配对, 入耳式耳机, 音乐, 媒体"</string>
    <string name="keywords_wallpaper" >"背景, 屏幕, 锁屏, 主题背景"</string>
    <string name="keywords_assist_input" >"默认, 智能助理"</string>
    <string name="keywords_default_browser" >"默认, 默认浏览器"</string>
    <string name="keywords_default_payment_app" >"付款, 默认"</string>
    <string name="keywords_default_links" >"默认"</string>
    <string name="keywords_ambient_display" >"收到的通知"</string>
    <string name="keywords_hotspot_tethering" >"USB 网络共享, 蓝牙网络共享, WLAN 热点"</string>
    <string name="keywords_touch_vibration" >"触觉, 振动, 屏幕, 灵敏度"</string>
    <string name="keywords_ring_vibration" >"触觉, 振动, 手机, 通话, 灵敏度"</string>
    <string name="setup_wifi_nfc_tag" >"设置WLAN NFC标签"</string>
    <string name="write_tag" >"写入"</string>
    <string name="status_awaiting_tap" >"点按标签即可写入…"</string>
    <string name="status_invalid_password" >"密码无效，请重试。"</string>
    <string name="status_write_success" >"成功！"</string>
    <string name="status_failed_to_write" >"无法将数据写入NFC标签。如果该问题一直存在，请尝试使用其他标签"</string>
    <string name="status_tag_not_writable" >"无法将数据写入NFC标签，请使用其他标签。"</string>
    <string name="default_sound" >"默认铃声"</string>
    <string name="sound_settings_summary" >"铃声音量为 <xliff:g id="PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="sound_dashboard_summary" >"音量、振动、勿扰"</string>
    <string name="sound_settings_summary_vibrate" >"振铃器已设为振动"</string>
    <string name="sound_settings_summary_silent" >"振铃器已设为静音"</string>
    <string name="sound_settings_example_summary" >"铃声音量为 80%"</string>
    <string name="media_volume_option_title" >"媒体音量"</string>
    <string name="call_volume_option_title" >"通话音量"</string>
    <string name="alarm_volume_option_title" >"闹钟音量"</string>
    <string name="ring_volume_option_title" >"铃声音量"</string>
    <string name="notification_volume_option_title" >"通知音量"</string>
    <string name="ringtone_title" >"手机铃声"</string>
    <string name="notification_ringtone_title" >"默认通知提示音"</string>
    <string name="notification_unknown_sound_title" >"应用提供的提示音"</string>
    <string name="notification_sound_default" >"默认通知提示音"</string>
    <string name="alarm_ringtone_title" >"默认闹钟提示音"</string>
    <string name="vibrate_when_ringing_title" >"有来电时响铃并振动"</string>
    <string name="other_sound_settings" >"其他提示音"</string>
    <string name="dial_pad_tones_title" >"拨号键盘提示音"</string>
    <string name="screen_locking_sounds_title" >"屏幕锁定提示音"</string>
    <string name="charging_sounds_title" >"充电提示音"</string>
    <string name="docking_sounds_title" >"基座提示音"</string>
    <string name="touch_sounds_title" >"触摸提示音"</string>
    <string name="vibrate_on_touch_title" >"触摸振动"</string>
    <string name="vibrate_on_touch_summary" >"点按、按键等操作的触感"</string>
    <string name="dock_audio_media_title" >"基座扬声器播放的音频"</string>
    <string name="dock_audio_media_disabled" >"所有音频"</string>
    <string name="dock_audio_media_enabled" >"仅限媒体音频"</string>
    <string name="emergency_tone_silent" >"静音"</string>
    <string name="emergency_tone_alert" >"提示音"</string>
    <string name="emergency_tone_vibrate" >"振动"</string>
    <string name="boot_sounds_title" >"开机音效"</string>
    <string name="zen_mode_settings_summary_off" >"永不"</string>
    <plurals name="zen_mode_settings_summary_on" formatted="false" >
      <item quantity="other"><xliff:g id="ON_COUNT">%d</xliff:g> 条规则</item>
      <item quantity="one">1 条规则</item>
    </plurals>
    <string name="zen_mode_settings_title" >"勿扰模式"</string>
    <string name="zen_mode_settings_turn_on_dialog_title" >"开启“勿扰”模式"</string>
    <string name="zen_mode_behavior_settings_title" >"例外情况"</string>
    <string name="zen_mode_duration_settings_title" >"持续时间"</string>
    <string name="zen_mode_behavior_allow_title" >"允许以下类型的提示音和振动："</string>
    <string name="zen_mode_behavior_no_sound" >"不发出提示音"</string>
    <string name="zen_mode_behavior_total_silence" >"完全静音"</string>
    <string name="zen_mode_behavior_no_sound_except" >"不发出提示音（<xliff:g id="CATEGORIES">%1$s</xliff:g>除外）"</string>
    <string name="zen_mode_behavior_alarms_only" >"静音（闹钟和媒体音频除外）"</string>
    <string name="zen_mode_automation_settings_title" >"自动开启"</string>
    <string name="zen_mode_automation_settings_page_title" >"自动规则"</string>
    <string name="zen_mode_automatic_rule_settings_page_title" >"自动规则"</string>
    <string name="zen_mode_automation_suggestion_title" >"在特定的时间将手机设为静音"</string>
    <string name="zen_mode_automation_suggestion_summary" >"设置“勿扰”规则"</string>
    <string name="zen_mode_use_automatic_rule" >"使用规则"</string>
    <string name="zen_mode_option_important_interruptions" >"仅限优先事项"</string>
    <string name="zen_mode_option_alarms" >"仅限闹钟"</string>
    <string name="zen_mode_option_no_interruptions" >"完全阻止"</string>
    <string name="zen_mode_summary_combination" >"<xliff:g id="MODE">%1$s</xliff:g>：<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_mode_visual_interruptions_settings_title" >"屏蔽视觉打扰"</string>
    <string name="zen_mode_visual_signals_settings_subtitle" >"允许视觉信号"</string>
    <string name="zen_mode_settings_category" >"开启勿扰模式时"</string>
    <string name="zen_mode_restrict_notifications_title" >"通知"</string>
    <string name="zen_mode_restrict_notifications_mute" >"不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_mute_summary" >"您会在屏幕上看到通知"</string>
    <string name="zen_mode_restrict_notifications_mute_footer" >"手机在收到通知时既不会发出提示音也不会振动。"</string>
    <string name="zen_mode_restrict_notifications_hide" >"不显示通知，也不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_hide_summary" >"您将不会看到通知或听到通知提示音"</string>
    <string name="zen_mode_restrict_notifications_hide_footer" >"您的手机不会显示任何新通知和现有通知，并且既不会发出提示音也不会振动。当您从屏幕顶部向下滑动时，系统不会显示通知。\n\n请注意，系统仍会显示手机活动和状态的重要通知。"</string>
    <string name="zen_mode_restrict_notifications_custom" >"自定义"</string>
    <string name="zen_mode_restrict_notifications_enable_custom" >"启用自定义设置"</string>
    <string name="zen_mode_restrict_notifications_disable_custom" >"移除自定义设置"</string>
    <string name="zen_mode_restrict_notifications_summary_muted" >"不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_summary_custom" >"隐藏部分通知"</string>
    <string name="zen_mode_restrict_notifications_summary_hidden" >"不显示通知，也不发出通知提示音"</string>
    <string name="zen_mode_what_to_block_title" >"自定义限制"</string>
    <string name="zen_mode_block_effects_screen_on" >"屏幕开启时"</string>
    <string name="zen_mode_block_effects_screen_off" >"屏幕关闭时"</string>
    <string name="zen_mode_block_effect_sound" >"关闭提示音和振动"</string>
    <string name="zen_mode_block_effect_intent" >"不开启屏幕"</string>
    <string name="zen_mode_block_effect_light" >"不闪烁指示灯"</string>
    <string name="zen_mode_block_effect_peek" >"不在屏幕上弹出通知"</string>
    <string name="zen_mode_block_effect_status" >"隐藏状态栏图标"</string>
    <string name="zen_mode_block_effect_badge" >"隐藏通知圆点"</string>
    <string name="zen_mode_block_effect_ambient" >"不要在收到通知时唤醒设备"</string>
    <string name="zen_mode_block_effect_list" >"不在通知列表中显示"</string>
    <string name="zen_mode_block_effect_summary_none" >"永不"</string>
    <string name="zen_mode_block_effect_summary_screen_off" >"屏幕关闭时"</string>
    <string name="zen_mode_block_effect_summary_screen_on" >"屏幕开启时"</string>
    <string name="zen_mode_block_effect_summary_sound" >"提示音和振动"</string>
    <string name="zen_mode_block_effect_summary_some" >"通知的声音、振动和部分视觉符号"</string>
    <string name="zen_mode_block_effect_summary_all" >"通知的声音、振动和视觉符号"</string>
    <string name="zen_mode_blocked_effects_footer" >"基本手机活动和状态所需的通知一律不隐藏"</string>
    <string name="zen_mode_no_exceptions" >"无"</string>
    <string name="zen_mode_other_options" >"其他选项"</string>
    <string name="zen_mode_add" >"添加"</string>
    <string name="zen_mode_enable_dialog_turn_on" >"开启"</string>
    <string name="zen_mode_button_turn_on" >"立即开启"</string>
    <string name="zen_mode_button_turn_off" >"立即关闭"</string>
    <string name="zen_mode_settings_dnd_manual_end_time" >"勿扰模式结束时间：<xliff:g id="FORMATTED_TIME">%s</xliff:g>"</string>
    <string name="zen_mode_settings_dnd_manual_indefinite" >"勿扰模式将一直开启，直到您将其关闭"</string>
    <string name="zen_mode_settings_dnd_automatic_rule" >"某个规则（<xliff:g id="RULE_NAME">%s</xliff:g>）已自动开启勿扰模式"</string>
    <string name="zen_mode_settings_dnd_automatic_rule_app" >"某个应用（<xliff:g id="APP_NAME">%s</xliff:g>）已自动开启勿扰模式"</string>
    <string name="zen_interruption_level_priority" >"仅限优先事项"</string>
    <string name="zen_mode_and_condition" >"<xliff:g id="ZEN_MODE">%1$s</xliff:g>。<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_on_with_info" >"开启/<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_off_with_info" >"关闭/<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_off" >"关闭"</string>
    <string name="zen_mode_sound_summary_on" >"开启"</string>
    <string name="zen_mode_duration_summary_always_prompt" >"每次都询问（除非自动开启）"</string>
    <string name="zen_mode_duration_summary_forever" >"直到您关闭（除非自动开启）"</string>
    <plurals name="zen_mode_duration_summary_time_hours" formatted="false" >
      <item quantity="other"><xliff:g id="NUM_HOURS">%d</xliff:g> 小时（除非自动开启）</item>
      <item quantity="one">1 小时（除非自动开启）</item>
    </plurals>
    <string name="zen_mode_duration_summary_time_minutes" >"<xliff:g id="NUM_MINUTES">%d</xliff:g> 分钟（除非自动开启）"</string>
    <plurals name="zen_mode_sound_summary_summary_off_info" formatted="false" >
      <item quantity="other"><xliff:g id="ON_COUNT">%d</xliff:g> 个规则可以自动开启</item>
      <item quantity="one">1 个规则可以自动开启</item>
    </plurals>
    <string name="zen_category_behavior" >"行为"</string>
    <string name="zen_category_exceptions" >"例外情况"</string>
    <string name="zen_category_schedule" >"日程"</string>
    <string name="zen_sound_title" >"提示音和振动"</string>
    <string name="zen_sound_footer" >"开启勿扰模式后，系统既不会发出提示音也不会振动（您在上方允许的项目除外）。"</string>
    <string name="zen_sound_category_title" >"全部静音，除了"</string>
    <string name="zen_sound_all_muted" >"已设为静音"</string>
    <string name="zen_sound_none_muted" >"未设为静音"</string>
    <string name="zen_sound_one_allowed" >"已设为静音（但<xliff:g id="SOUND_TYPE">%1$s</xliff:g>除外）"</string>
    <string name="zen_sound_two_allowed" >"已设为静音（但<xliff:g id="SOUND_TYPE_0">%1$s</xliff:g>和<xliff:g id="SOUND_TYPE_1">%2$s</xliff:g>除外）"</string>
    <string name="zen_sound_three_allowed" >"已设为静音（但<xliff:g id="SOUND_TYPE_0">%1$s</xliff:g>、<xliff:g id="SOUND_TYPE_1">%2$s</xliff:g>和<xliff:g id="SOUND_TYPE_2">%3$s</xliff:g>除外）"</string>
    <string name="zen_msg_event_reminder_title" >"信息、活动和提醒"</string>
    <string name="zen_msg_event_reminder_footer" >"开启勿扰模式后，系统会忽略信息、提醒和活动。您可以调整相应信息设置（您在上访允许的项目除外），以便允许您的好友、家人或其他联系人与您联系。"</string>
    <string name="zen_onboarding_ok" >"完成"</string>
    <string name="zen_onboarding_settings" >"设置"</string>
    <string name="zen_onboarding_new_setting_title" >"不显示通知，也不发出通知提示音"</string>
    <string name="zen_onboarding_current_setting_title" >"不发出通知提示音"</string>
    <string name="zen_onboarding_new_setting_summary" >"您将不会看到通知或听到通知提示音。允许已加星标的联系人和重复来电者的来电。"</string>
    <string name="zen_onboarding_current_setting_summary" >"（当前设置）"</string>
    <string name="zen_onboarding_dnd_visual_disturbances_header" >"要更改勿扰模式的通知设置吗？"</string>
    <string name="sound_work_settings" >"工作资料提示音"</string>
    <string name="work_use_personal_sounds_title" >"使用个人资料提示音"</string>
    <string name="work_use_personal_sounds_summary" >"工作资料和个人资料会使用相同的提示音"</string>
    <string name="work_ringtone_title" >"工作电话铃声"</string>
    <string name="work_notification_ringtone_title" >"默认工作通知提示音"</string>
    <string name="work_alarm_ringtone_title" >"默认工作闹钟提示音"</string>
    <string name="work_sound_same_as_personal" >"与个人资料相同"</string>
    <string name="work_sync_dialog_title" >"要替换提示音吗？"</string>
    <string name="work_sync_dialog_yes" >"替换"</string>
    <string name="work_sync_dialog_message" >"系统将为您的工作资料使用个人资料提示音。"</string>
    <string name="ringtones_install_custom_sound_title" >"要添加自定义提示音吗？"</string>
    <string name="ringtones_install_custom_sound_content" >"此文件将复制到“<xliff:g id="FOLDER_NAME">%s</xliff:g>”文件夹"</string>
    <string name="ringtones_category_preference_title" >"铃声"</string>
    <string name="other_sound_category_preference_title" >"其他提示音和振动"</string>
    <string name="configure_notification_settings" >"通知"</string>
    <string name="recent_notifications" >"最近发送"</string>
    <string name="recent_notifications_see_all_title" >"查看过去 7 天的所有应用"</string>
    <string name="advanced_section_header" >"高级"</string>
    <string name="profile_section_header" >"工作通知"</string>
    <string name="notification_badging_title" >"允许使用通知圆点"</string>
    <string name="notification_pulse_title" >"闪烁指示灯"</string>
    <string name="lock_screen_notifications_title" >"在锁定屏幕上"</string>
    <string name="locked_work_profile_notification_title" >"当工作资料遭到锁定时"</string>
    <string name="lock_screen_notifications_summary_show" >"显示所有通知内容"</string>
    <string name="lock_screen_notifications_summary_hide" >"隐藏敏感内容"</string>
    <string name="lock_screen_notifications_summary_disable" >"完全不显示通知"</string>
    <string name="lock_screen_notifications_interstitial_message" >"在设备锁定时，您希望通知如何显示？"</string>
    <string name="lock_screen_notifications_interstitial_title" >"通知"</string>
    <string name="lock_screen_notifications_summary_show_profile" >"显示所有工作通知内容"</string>
    <string name="lock_screen_notifications_summary_hide_profile" >"隐藏敏感工作内容"</string>
    <string name="lock_screen_notifications_interstitial_message_profile" >"在设备锁定时，您希望个人资料通知如何显示？"</string>
    <string name="lock_screen_notifications_interstitial_title_profile" >"个人资料通知"</string>
    <string name="notifications_title" >"通知"</string>
    <string name="app_notifications_title" >"应用通知"</string>
    <string name="notification_channel_title" >"通知类别"</string>
    <string name="notification_group_title" >"通知类别组"</string>
    <string name="notification_importance_title" >"行为"</string>
    <string name="notification_importance_unspecified" >"允许发出提示音"</string>
    <string name="notification_importance_blocked" >"一律不显示通知"</string>
    <string name="notification_importance_min" >"无声显示并将重要性级别最小化"</string>
    <string name="notification_importance_low" >"显示通知但不发出提示音"</string>
    <string name="notification_importance_default" >"发出提示音"</string>
    <string name="notification_importance_high" >"发出提示音并在屏幕上弹出通知"</string>
    <string name="notification_importance_high_silent" >"在屏幕上弹出"</string>
    <string name="notification_importance_min_title" >"低"</string>
    <string name="notification_importance_low_title" >"中"</string>
    <string name="notification_importance_default_title" >"高"</string>
    <string name="notification_importance_high_title" >"紧急"</string>
    <string name="allow_interruption" >"允许打扰"</string>
    <string name="allow_interruption_summary" >"允许应用发出提示音、振动，以及/或在屏幕上弹出通知"</string>
    <string name="notification_channel_summary_min" >"重要性：低"</string>
    <string name="notification_channel_summary_low" >"重要性：中等"</string>
    <string name="notification_channel_summary_default" >"重要性：高"</string>
    <string name="notification_channel_summary_high" >"重要性：紧急"</string>
    <string name="notification_switch_label" >"显示通知"</string>
    <string name="default_notification_assistant" >"通知助手"</string>
    <string name="notifications_sent_daily" >"每天大约 <xliff:g id="NUMBER">%1$s</xliff:g> 条"</string>
    <string name="notifications_sent_weekly" >"每周大约 <xliff:g id="NUMBER">%1$s</xliff:g> 条"</string>
    <string name="notifications_sent_never" >"永不"</string>
    <string name="manage_notification_access_title" >"通知使用权"</string>
    <string name="work_profile_notification_access_blocked_summary" >"已禁止访问工作资料通知"</string>
    <string name="manage_notification_access_summary_zero" >"应用无法读取通知"</string>
    <plurals name="manage_notification_access_summary_nonzero" formatted="false" >
      <item quantity="other">%d 个应用可以读取通知</item>
      <item quantity="one">%d 个应用可以读取通知</item>
    </plurals>
    <string name="no_notification_listeners" >"没有任何已安装的应用请求通知访问权限。"</string>
    <string name="notification_listener_security_warning_title" >"要允许<xliff:g id="SERVICE">%1$s</xliff:g>获取通知访问权限吗？"</string>
    <string name="notification_listener_security_warning_summary" >"<xliff:g id="NOTIFICATION_LISTENER_NAME">%1$s</xliff:g>将可读取所有通知（包括联系人姓名和您收到的消息正文等个人信息），而且还能关闭通知或触发通知中的操作按钮。\n\n另外，此应用还将能开启或关闭勿扰模式，以及更改相关设置。"</string>
    <string name="notification_listener_disable_warning_summary" >"如果您停用<xliff:g id="NOTIFICATION_LISTENER_NAME">%1$s</xliff:g>的通知访问权限，勿扰模式的访问权限可能也会遭到停用。"</string>
    <string name="notification_listener_disable_warning_confirm" >"停用"</string>
    <string name="notification_listener_disable_warning_cancel" >"取消"</string>
    <string name="vr_listeners_title" >"VR 助手服务"</string>
    <string name="no_vr_listeners" >"没有任何已安装的应用请求以 VR 助手服务的形式运行。"</string>
    <string name="vr_listener_security_warning_title" >"允许<xliff:g id="SERVICE">%1$s</xliff:g>访问 VR 服务吗？"</string>
    <string name="vr_listener_security_warning_summary" >"只有当您在虚拟实境模式下使用应用时，才能运行<xliff:g id="VR_LISTENER_NAME">%1$s</xliff:g>。"</string>
    <string name="display_vr_pref_title" >"当设备处于 VR 模式时"</string>
    <string name="display_vr_pref_low_persistence" >"减少模糊（建议）"</string>
    <string name="display_vr_pref_off" >"减少闪烁"</string>
    <string name="picture_in_picture_title" >"画中画"</string>
    <string name="picture_in_picture_empty_text" >"已安装的应用均不支持画中画"</string>
    <string name="picture_in_picture_keywords" >"画中画, 画中, pip, picture in"</string>
    <string name="picture_in_picture_app_detail_title" >"画中画"</string>
    <string name="picture_in_picture_app_detail_switch" >"允许进入画中画模式"</string>
    <string name="picture_in_picture_app_detail_summary" >"允许此应用在您打开应用时或您离开应用后（例如继续观看视频）创建画中画窗口。这类窗口会显示在您当前使用的其他应用的上层。"</string>
    <string name="manage_zen_access_title" >"“勿扰”权限"</string>
    <string name="zen_access_empty_text" >"没有任何已安装应用申请“勿扰”权限"</string>
    <string name="loading_notification_apps" >"正在加载应用…"</string>
    <string name="app_notifications_off_desc" >"根据您的要求，Android 会阻止此应用的通知显示在此设备上"</string>
    <string name="channel_notifications_off_desc" >"根据您的要求，Android 会阻止这类通知显示在此设备上"</string>
    <string name="channel_group_notifications_off_desc" >"根据您的要求，Android 会阻止这组通知显示在此设备上"</string>
    <string name="notification_channels" >"类别"</string>
    <string name="notification_channels_other" >"其他"</string>
    <plurals name="notification_group_summary" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <string name="no_channels" >"此应用未发布任何通知"</string>
    <string name="app_settings_link" >"应用中的其他设置"</string>
    <string name="app_notification_listing_summary_zero" >"目前设为接收所有应用的通知"</string>
    <plurals name="app_notification_listing_summary_others" formatted="false" >
      <item quantity="other">已关闭 <xliff:g id="COUNT_1">%d</xliff:g> 个应用的通知功能</item>
      <item quantity="one">已关闭 <xliff:g id="COUNT_0">%d</xliff:g> 个应用的通知功能</item>
    </plurals>
    <plurals name="deleted_channels" formatted="false" >
      <item quantity="other">已删除 <xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one">已删除 <xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <string name="notification_toggle_on" >"开启"</string>
    <string name="notification_toggle_off" >"关闭"</string>
    <string name="app_notification_block_title" >"全部屏蔽"</string>
    <string name="app_notification_block_summary" >"一律不显示这类通知"</string>
    <string name="notification_content_block_title" >"显示通知"</string>
    <string name="notification_content_block_summary" >"一律不在通知栏或外围设备上显示通知"</string>
    <string name="notification_badge_title" >"允许使用通知圆点"</string>
    <string name="notification_channel_badge_title" >"显示通知圆点"</string>
    <string name="app_notification_override_dnd_title" >"覆盖“勿扰”设置"</string>
    <string name="app_notification_override_dnd_summary" >"开启勿扰模式时允许继续接收这类通知"</string>
    <string name="app_notification_visibility_override_title" >"在锁定屏幕上"</string>
    <string name="app_notification_row_banned" >"屏蔽"</string>
    <string name="app_notification_row_priority" >"优先"</string>
    <string name="app_notification_row_sensitive" >"敏感"</string>
    <string name="app_notifications_dialog_done" >"完成"</string>
    <string name="app_notification_importance_title" >"重要程度"</string>
    <string name="notification_show_lights_title" >"闪烁指示灯"</string>
    <string name="notification_vibrate_title" >"振动"</string>
    <string name="notification_channel_sound_title" >"提示音"</string>
    <string name="zen_mode_rule_delete_button" >"删除"</string>
    <string name="zen_mode_rule_rename_button" >"重命名"</string>
    <string name="zen_mode_rule_name" >"规则名称"</string>
    <string name="zen_mode_rule_name_hint" >"输入规则名称"</string>
    <string name="zen_mode_rule_name_warning" >"规则名称已被使用"</string>
    <string name="zen_mode_add_rule" >"添加规则"</string>
    <string name="zen_mode_add_event_rule" >"添加活动规则"</string>
    <string name="zen_mode_add_time_rule" >"添加时间规则"</string>
    <string name="zen_mode_delete_rule" >"删除规则"</string>
    <string name="zen_mode_choose_rule_type" >"选择规则类型"</string>
    <string name="zen_mode_delete_rule_confirmation" >"要删除“<xliff:g id="RULE">%1$s</xliff:g>”规则吗？"</string>
    <string name="zen_mode_delete_rule_button" >"删除"</string>
    <string name="zen_mode_rule_type_unknown" >"未知"</string>
    <string name="zen_mode_app_set_behavior" >"目前无法更改这些设置。应用（<xliff:g id="APP_NAME">%1$s</xliff:g>）已通过自定义行为自动开启勿扰模式。"</string>
    <string name="zen_mode_unknown_app_set_behavior" >"目前无法更改这些设置。某个应用已通过自定义行为自动开启勿扰模式。"</string>
    <string name="zen_mode_qs_set_behavior" >"目前无法更改这些设置。用户已通过自定义行为手动开启勿扰模式。"</string>
    <string name="zen_schedule_rule_type_name" >"时间"</string>
    <string name="zen_schedule_rule_enabled_toast" >"将自动规则设置为在指定时间段内开启勿扰模式"</string>
    <string name="zen_event_rule_type_name" >"活动"</string>
    <string name="zen_event_rule_enabled_toast" >"将自动规则设置为发生指定事件时开启勿扰模式"</string>
    <string name="zen_mode_event_rule_calendar" >"在以下日历活动期间："</string>
    <string name="zen_mode_event_rule_summary_calendar_template" >"在<xliff:g id="CALENDAR">%1$s</xliff:g>的活动期间"</string>
    <string name="zen_mode_event_rule_summary_any_calendar" >"所有日历"</string>
    <string name="zen_mode_event_rule_summary_reply_template" >"回复为<xliff:g id="REPLY">%1$s</xliff:g>"</string>
    <string name="zen_mode_event_rule_calendar_any" >"所有日历"</string>
    <string name="zen_mode_event_rule_reply" >"回复为"</string>
    <string name="zen_mode_event_rule_reply_any_except_no" >"“参加”、“可能参加”或“未回复”"</string>
    <string name="zen_mode_event_rule_reply_yes_or_maybe" >"“参加”或“可能参加”"</string>
    <string name="zen_mode_event_rule_reply_yes" >"参加"</string>
    <string name="zen_mode_rule_not_found_text" >"找不到规则。"</string>
    <string name="zen_mode_rule_summary_enabled_combination" >"开启/<xliff:g id="MODE">%1$s</xliff:g>"</string>
    <string name="zen_mode_rule_summary_provider_combination" >"<xliff:g id="PACKAGE">%1$s</xliff:g>\n<xliff:g id="SUMMARY">%2$s</xliff:g>"</string>
    <string name="zen_mode_schedule_rule_days" >"星期几"</string>
    <string name="zen_mode_schedule_rule_days_none" >"无"</string>
    <string name="zen_mode_schedule_rule_days_all" >"每天"</string>
    <string name="zen_mode_schedule_alarm_title" >"闹钟设置优先于结束时间设置"</string>
    <string name="zen_mode_schedule_alarm_summary" >"在所设结束时间或闹钟下一次响铃时（两者取其先）退出此模式"</string>
    <string name="summary_divider_text" >"， "</string>
    <string name="summary_range_symbol_combination" >"<xliff:g id="START">%1$s</xliff:g> - <xliff:g id="END">%2$s</xliff:g>"</string>
    <string name="summary_range_verbal_combination" >"<xliff:g id="START">%1$s</xliff:g>到<xliff:g id="END">%2$s</xliff:g>"</string>
    <string name="zen_mode_calls" >"来电"</string>
    <string name="zen_mode_calls_title" >"允许进行通话"</string>
    <string name="zen_mode_calls_footer" >"开启勿扰模式后，系统会屏蔽来电。您可以调整相应设置，以便允许您的好友、家人或其他联系人与您联系。"</string>
    <string name="zen_mode_starred_contacts_title" >"已加星标的联系人"</string>
    <plurals name="zen_mode_starred_contacts_summary_additional_contacts" formatted="false" >
      <item quantity="other">另外 <xliff:g id="NUM_PEOPLE">%d</xliff:g> 人</item>
      <item quantity="one">另外 1 人</item>
    </plurals>
    <string name="zen_mode_messages" >"消息"</string>
    <string name="zen_mode_messages_title" >"允许显示消息"</string>
    <string name="zen_mode_all_messages" >"信息"</string>
    <string name="zen_mode_selected_messages" >"部分信息"</string>
    <string name="zen_mode_from_anyone" >"来自任何人"</string>
    <string name="zen_mode_from_contacts" >"仅限来自联系人"</string>
    <string name="zen_mode_from_starred" >"仅限来自收藏的联系人"</string>
    <string name="zen_calls_summary_starred_repeat" >"来自已加星标的联系人和重复来电者"</string>
    <string name="zen_calls_summary_contacts_repeat" >"来自联系人和重复来电者"</string>
    <string name="zen_calls_summary_repeat_only" >"仅限来自重复来电者"</string>
    <string name="zen_mode_from_none" >"无"</string>
    <string name="zen_mode_alarms" >"闹钟"</string>
    <string name="zen_mode_media" >"媒体"</string>
    <string name="zen_mode_system" >"触摸提示音"</string>
    <string name="zen_mode_reminders" >"提醒"</string>
    <string name="zen_mode_reminders_title" >"允许显示提醒"</string>
    <string name="zen_mode_events" >"活动"</string>
    <string name="zen_mode_events_title" >"允许显示活动"</string>
    <string name="zen_mode_all_callers" >"任何人"</string>
    <string name="zen_mode_contacts_callers" >"联系人"</string>
    <string name="zen_mode_starred_callers" >"已加星标的联系人"</string>
    <string name="zen_mode_repeat_callers" >"重复来电者"</string>
    <string name="zen_mode_repeat_callers_title" >"允许显示重复来电者"</string>
    <string name="zen_mode_calls_summary_one" >"来自<xliff:g id="CALLER_TYPE">%1$s</xliff:g>"</string>
    <string name="zen_mode_calls_summary_two" >"来自<xliff:g id="CALLER_TYPE">%1$s</xliff:g>和<xliff:g id="CALLERT_TPYE">%2$s</xliff:g>"</string>
    <string name="zen_mode_repeat_callers_summary" >"如果同一个人在 <xliff:g id="MINUTES">%d</xliff:g> 分钟内第二次来电"</string>
    <string name="zen_mode_behavior_summary_custom" >"自定义"</string>
    <string name="zen_mode_when" >"自动开启"</string>
    <string name="zen_mode_when_never" >"永不"</string>
    <string name="zen_mode_when_every_night" >"每晚"</string>
    <string name="zen_mode_when_weeknights" >"周一至周五夜间"</string>
    <string name="zen_mode_start_time" >"开始时间"</string>
    <string name="zen_mode_end_time" >"结束时间"</string>
    <string name="zen_mode_end_time_next_day_summary_format" >"次日<xliff:g id="FORMATTED_TIME">%s</xliff:g>"</string>
    <string name="zen_mode_summary_alarms_only_indefinite" >"更改为无限期仅限闹钟"</string>
    <plurals name="zen_mode_summary_alarms_only_by_minute" formatted="false" >
      <item quantity="other">更改为仅限闹钟，持续 <xliff:g id="DURATION">%1$d</xliff:g> 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">更改为仅限闹钟，持续 1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_summary_alarms_only_by_hour" formatted="false" >
      <item quantity="other">更改为仅限闹钟，持续 <xliff:g id="DURATION">%1$d</xliff:g> 小时（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">更改为仅限闹钟，持续 1 小时（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <string name="zen_mode_summary_alarms_only_by_time" >"更改为仅限闹钟（到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>）"</string>
    <string name="zen_mode_summary_always" >"更改为一律允许打扰"</string>
    <string name="zen_mode_screen_on" >"当屏幕开启时"</string>
    <string name="zen_mode_screen_on_summary" >"允许在“勿扰”模式下被静音的通知在屏幕上弹出，并显示状态栏图标"</string>
    <string name="zen_mode_screen_off" >"当屏幕关闭时"</string>
    <string name="zen_mode_screen_off_summary" >"允许在“勿扰”模式下被静音的通知开启屏幕并闪烁指示灯"</string>
    <string name="zen_mode_screen_off_summary_no_led" >"允许在“勿扰”模式下被静音的通知开启屏幕"</string>
    <string name="notification_app_settings_button" >"通知设置"</string>
    <string name="suggestion_button_text" >"确定"</string>
    <string name="device_feedback" >"发送有关此设备的反馈"</string>
    <string name="restr_pin_enter_admin_pin" >"输入管理员 PIN 码"</string>
    <string name="switch_on_text" >"开启"</string>
    <string name="switch_off_text" >"关闭"</string>
    <string name="screen_pinning_title" >"屏幕固定"</string>
    <string name="screen_pinning_description" >"开启此设置后，您可以使用固定屏幕功能来固定显示当前的屏幕，直到您取消固定为止。\n\n要使用固定屏幕功能，请执行以下操作：\n\n1. 确保固定屏幕功能已开启\n\n2. 打开“概览”\n\n3. 点按屏幕顶部的应用图标，然后点按“固定”"</string>
    <string name="screen_pinning_unlock_pattern" >"取消固定屏幕前要求绘制解锁图案"</string>
    <string name="screen_pinning_unlock_pin" >"取消固定屏幕前要求输入 PIN 码"</string>
    <string name="screen_pinning_unlock_password" >"取消固定屏幕前要求输入密码"</string>
    <string name="screen_pinning_unlock_none" >"取消固定屏幕时锁定设备"</string>
    <string name="opening_paragraph_delete_profile_unknown_company" >"此工作资料由以下应用管理："</string>
    <string name="managing_admin" >"由<xliff:g id="ADMIN_APP_LABEL">%s</xliff:g>管理"</string>
    <string name="experimental_preference" >"（实验性）"</string>
    <string name="encryption_interstitial_header" >"安全启动"</string>
    <string name="encryption_continue_button" >"继续"</string>
    <string name="encryption_interstitial_message_pin" >"为了进一步保护此设备的安全，您可以将设备设为需要输入 PIN 码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入 PIN 码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pattern" >"为了进一步保护此设备的安全，您可以将设备设为需要绘制解锁图案才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要绘制解锁图案才能启动吗？"</string>
    <string name="encryption_interstitial_message_password" >"为了进一步保护此设备的安全，您可以将设备设为需要输入密码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入密码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pin_for_fingerprint" >"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要输入 PIN 码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入 PIN 码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pattern_for_fingerprint" >"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要绘制解锁图案才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要绘制解锁图案才能启动吗？"</string>
    <string name="encryption_interstitial_message_password_for_fingerprint" >"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要输入密码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入密码才能启动吗？"</string>
    <string name="encryption_interstitial_yes" >"是"</string>
    <string name="encryption_interstitial_no" >"否"</string>
    <string name="restricted_true_label" >"受限"</string>
    <string name="restricted_false_label" >"应用可以在后台使用电量"</string>
    <string name="encrypt_talkback_dialog_require_pin" >"要求输入 PIN 码吗？"</string>
    <string name="encrypt_talkback_dialog_require_pattern" >"要求绘制图案吗？"</string>
    <string name="encrypt_talkback_dialog_require_password" >"要求输入密码吗？"</string>
    <string name="encrypt_talkback_dialog_message_pin" >"当您输入 PIN 码以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="encrypt_talkback_dialog_message_pattern" >"当您绘制解锁图案以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="encrypt_talkback_dialog_message_password" >"当您输入密码以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="direct_boot_unaware_dialog_message" >"注意：重新启动后，您必须将手机解锁才能运行此应用"</string>
    <string name="imei_information_title" >"IMEI 信息"</string>
    <string name="imei_information_summary" >"IMEI相关信息"</string>
    <string name="slot_number" >"（插槽<xliff:g id="SLOT_NUM">%1$d</xliff:g>）"</string>
    <string name="launch_by_default" >"默认打开"</string>
    <string name="app_launch_domain_links_title" >"打开链接"</string>
    <string name="app_launch_open_domain_urls_title" >"打开支持的链接"</string>
    <string name="app_launch_open_domain_urls_summary" >"无需询问即可打开"</string>
    <string name="app_launch_supported_domain_urls_title" >"支持的链接"</string>
    <string name="app_launch_other_defaults_title" >"其他默认设置"</string>
    <string name="storage_summary_format" >"<xliff:g id="STORAGE_TYPE">%2$s</xliff:g>已使用 <xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="storage_type_internal" >"内部存储空间"</string>
    <string name="storage_type_external" >"外部存储空间"</string>
    <string name="app_data_usage" >"应用的流量使用情况"</string>
    <string name="data_summary_format" >"自 <xliff:g id="DATE">%2$s</xliff:g>以来已使用 <xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="storage_used" >"已使用的存储空间"</string>
    <string name="change" >"更改"</string>
    <string name="change_storage" >"更改存储空间"</string>
    <string name="notifications_label" >"通知"</string>
    <string name="notifications_enabled" >"开启"</string>
    <string name="notifications_enabled_with_info" >"开启/<xliff:g id="NOTIFICATIONS_CATEGORIES_OFF">%1$s</xliff:g>"</string>
    <string name="notifications_disabled" >"关闭"</string>
    <string name="notifications_partly_blocked" >"已关闭 <xliff:g id="COUNT_0">%1$d</xliff:g> 个（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）类别的通知"</string>
    <string name="notifications_silenced" >"不发出提示音"</string>
    <string name="notifications_redacted" >"屏幕锁定时不显示敏感内容"</string>
    <string name="notifications_hidden" >"屏幕锁定时不显示"</string>
    <string name="notifications_priority" >"覆盖“勿扰”设置"</string>
    <string name="notifications_summary_divider" >" / "</string>
    <string name="notification_summary_level" >"%d 级"</string>
    <string name="notification_summary_channel" >"<xliff:g id="CHANNEL_NAME">%1$s</xliff:g> • <xliff:g id="GROUP_NAME">%2$s</xliff:g>"</string>
    <plurals name="notifications_categories_off" formatted="false" >
      <item quantity="other">已关闭 <xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one">已关闭 <xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <plurals name="permissions_summary" formatted="false" >
      <item quantity="other">已授予 <xliff:g id="COUNT_1">%d</xliff:g> 项权限</item>
      <item quantity="one">已授予 <xliff:g id="COUNT_0">%d</xliff:g> 项权限</item>
    </plurals>
    <plurals name="runtime_permissions_summary" formatted="false" >
      <item quantity="other">已授予 <xliff:g id="COUNT_2">%d</xliff:g> 项权限（共 <xliff:g id="COUNT_3">%d</xliff:g> 项）</item>
      <item quantity="one">已授予 <xliff:g id="COUNT_0">%d</xliff:g> 项权限（共 <xliff:g id="COUNT_1">%d</xliff:g> 项）</item>
    </plurals>
    <plurals name="runtime_permissions_additional_count" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 项其他权限</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 项其他权限</item>
    </plurals>
    <string name="runtime_permissions_summary_no_permissions_granted" >"未授予任何权限"</string>
    <string name="runtime_permissions_summary_no_permissions_requested" >"未请求任何权限"</string>
    <string name="filter_all_apps" >"所有应用"</string>
    <string name="filter_enabled_apps" >"已安装的应用"</string>
    <string name="filter_instant_apps" >"免安装应用"</string>
    <string name="filter_personal_apps" >"个人应用"</string>
    <string name="filter_work_apps" >"工作应用"</string>
    <string name="filter_notif_all_apps" >"应用：全部"</string>
    <string name="filter_notif_blocked_apps" >"已屏蔽的应用"</string>
    <string name="filter_notif_urgent_channels" >"类别：重要性 - 紧急"</string>
    <string name="filter_notif_low_channels" >"类别：重要性 - 低"</string>
    <string name="filter_notif_blocked_channels" >"类别：已关闭"</string>
    <string name="filter_notif_dnd_channels" >"类别：覆盖“勿扰”设置"</string>
    <string name="advanced_apps" >"高级"</string>
    <string name="configure_apps" >"配置应用"</string>
    <string name="unknown_app" >"未知应用"</string>
    <string name="app_permissions" >"应用权限"</string>
    <string name="app_permissions_summary" >"目前使用<xliff:g id="APPS">%1$s</xliff:g>的应用"</string>
    <string name="tap_to_wake" >"点按唤醒"</string>
    <string name="tap_to_wake_summary" >"在屏幕上的任意位置点按两次即可唤醒设备"</string>
    <string name="domain_urls_title" >"打开链接"</string>
    <string name="domain_urls_summary_none" >"不打开支持的链接"</string>
    <string name="domain_urls_summary_one" >"打开 <xliff:g id="DOMAIN">%s</xliff:g>"</string>
    <string name="domain_urls_summary_some" >"打开 <xliff:g id="DOMAIN">%s</xliff:g> 和其他网址"</string>
    <string name="domain_urls_apps_summary_off" >"没有应用可打开支持的链接"</string>
    <plurals name="domain_urls_apps_summary_on" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个应用可打开支持的链接</item>
      <item quantity="one">1 个应用可打开支持的链接</item>
    </plurals>
    <string name="app_link_open_always" >"在此应用中打开"</string>
    <string name="app_link_open_ask" >"每次都询问"</string>
    <string name="app_link_open_never" >"不要在此应用中打开"</string>
    <string name="fingerprint_not_recognized" >"无法识别"</string>
    <string name="default_apps_title" >"默认应用"</string>
    <string name="default_for_work" >"默认工作应用"</string>
    <string name="assist_and_voice_input_title" >"助手和语音输入"</string>
    <string name="default_assist_title" >"助手应用"</string>
    <string name="assistant_security_warning_title" >"要将<xliff:g id="ASSISTANT_APP_NAME">%s</xliff:g>设为您的辅助应用吗？"</string>
    <string name="assistant_security_warning" >"辅助应用将可读取您系统中使用的应用的相关信息，其中包括您屏幕上显示的信息或应用中可使用的信息。"</string>
    <string name="assistant_security_warning_agree" >"同意"</string>
    <string name="assistant_security_warning_disagree" >"不同意"</string>
    <string name="choose_voice_input_title" >"选择语音输入"</string>
    <string name="default_browser_title" >"浏览器应用"</string>
    <string name="default_browser_title_none" >"没有默认浏览器"</string>
    <string name="default_phone_title" >"电话应用"</string>
    <string name="default_app" >"（默认）"</string>
    <string name="system_app" >"（系统）"</string>
    <string name="system_default_app" >"（系统默认）"</string>
    <string name="apps_storage" >"应用所占存储空间"</string>
    <string name="usage_access" >"使用情况访问权限"</string>
    <string name="permit_usage_access" >"允许查看使用情况"</string>
    <string name="app_usage_preference" >"应用使用偏好设置"</string>
    <string name="time_spent_in_app_pref_title" >"应用使用时间"</string>
    <string name="usage_access_description" >"给应用授予“使用情况访问权限”后，该应用就能跟踪您正在使用的其他应用和使用频率，以及您的运营商、语言设置及其他详细信息。"</string>
    <string name="memory_settings_title" >"内存"</string>
    <string name="memory_details_title" >"内存详情"</string>
    <string name="always_running" >"始终运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="sometimes_running" >"偶尔运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="rarely_running" >"极少运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="memory_max_use" >"最高内存使用量"</string>
    <string name="memory_avg_use" >"平均内存使用量"</string>
    <string name="memory_max_desc" >"最高内存使用量：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory_avg_desc" >"平均内存使用量：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory_use_running_format" >"<xliff:g id="MEMORY">%1$s</xliff:g> / <xliff:g id="RUNNING">%2$s</xliff:g>"</string>
    <string name="process_format" >"<xliff:g id="APP_NAME">%1$s</xliff:g> (<xliff:g id="COUNT">%2$d</xliff:g>)"</string>
    <string name="high_power_apps" >"电池优化"</string>
    <string name="additional_battery_info" >"电池用量提醒"</string>
    <string name="show_all_apps" >"显示完整的设备用电量"</string>
    <string name="hide_extra_apps" >"显示应用的耗电情况"</string>
    <string name="power_high_usage_title" >"耗电量高"</string>
    <plurals name="power_high_usage_summary" formatted="false" >
      <item quantity="other"><xliff:g id="NUMBER">%2$d</xliff:g> 个应用的行为异常</item>
      <item quantity="one"><xliff:g id="APP">%1$s</xliff:g>的行为异常</item>
    </plurals>
    <plurals name="power_high_usage_title" formatted="false" >
      <item quantity="other">多个应用正在消耗大量电池电量</item>
      <item quantity="one"><xliff:g id="APP">%1$s</xliff:g>正在消耗大量电池电量</item>
    </plurals>
    <string name="high_power_filter_on" >"未优化"</string>
    <string name="high_power_on" >"未优化"</string>
    <string name="high_power_off" >"优化电池使用"</string>
    <string name="high_power_system" >"没有电池优化设置"</string>
    <string name="high_power_desc" >"不应用电池优化设置，但电量的消耗速度可能会更快。"</string>
    <string name="high_power_prompt_title" >"要允许应用始终在后台运行吗？"</string>
    <string name="high_power_prompt_body" >"允许“<xliff:g id="APP_NAME">%1$s</xliff:g>”始终在后台运行可能会缩短电池的续航时间。\n\n您以后可以在“设置”&gt;“应用和通知”中更改此设置。"</string>
    <string name="battery_summary" >"自上次充满电后已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="battery_power_management" >"电源管理"</string>
    <string name="no_battery_summary" >"自上次充满电后未消耗任何电量"</string>
    <string name="app_notification_preferences" >"应用设置"</string>
    <string name="system_ui_settings" >"显示系统界面调节工具"</string>
    <string name="additional_permissions" >"其他权限"</string>
    <string name="additional_permissions_more" >"另外 <xliff:g id="COUNT">%1$d</xliff:g> 项"</string>
    <string name="share_remote_bugreport_dialog_title" >"要分享错误报告吗？"</string>
    <string name="share_remote_bugreport_dialog_message_finished" >"您的 IT 管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据。"</string>
    <string name="share_remote_bugreport_dialog_message" >"您的 IT 管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据，设备运行速度也可能会因此而暂时减慢。"</string>
    <string name="sharing_remote_bugreport_dialog_message" >"正在与您的 IT 管理员分享这份错误报告。要了解详情，请与对方联系。"</string>
    <string name="share_remote_bugreport_action" >"分享"</string>
    <string name="decline_remote_bugreport_action" >"拒绝"</string>
    <string name="usb_use_charging_only" >"不进行数据传输"</string>
    <string name="usb_use_charging_only_desc" >"仅为此设备充电"</string>
    <string name="usb_use_power_only" >"为连接的设备充电"</string>
    <string name="usb_use_file_transfers" >"文件传输"</string>
    <string name="usb_use_file_transfers_desc" >"将文件传输至其他设备"</string>
    <string name="usb_use_photo_transfers" >"PTP"</string>
    <string name="usb_use_photo_transfers_desc" >"如果 MTP 不受支持，则传输照片或文件 (PTP)"</string>
    <string name="usb_use_tethering" >"USB 网络共享"</string>
    <string name="usb_use_MIDI" >"MIDI"</string>
    <string name="usb_use_MIDI_desc" >"将此设备用作 MIDI 设备"</string>
    <string name="usb_use" >"USB 的用途"</string>
    <string name="usb_default_label" >"默认 USB 配置"</string>
    <string name="usb_default_info" >"连接到其他设备且您的手机处于解锁状态时，系统就会应用这些设置。请只连接到可信设备。"</string>
    <string name="usb_pref" >"USB"</string>
    <string name="usb_preference" >"USB 偏好设置"</string>
    <string name="usb_control_title" >"USB 受控于："</string>
    <string name="usb_control_host" >"连接的设备"</string>
    <string name="usb_control_device" >"此设备"</string>
    <string name="usb_switching" >"正在切换…"</string>
    <string name="usb_switching_failed" >"无法切换"</string>
    <string name="usb_summary_charging_only" >"为此设备充电"</string>
    <string name="usb_summary_power_only" >"为连接的设备充电"</string>
    <string name="usb_summary_file_transfers" >"文件传输"</string>
    <string name="usb_summary_tether" >"USB 网络共享"</string>
    <string name="usb_summary_photo_transfers" >"PTP"</string>
    <string name="usb_summary_MIDI" >"MIDI"</string>
    <string name="usb_summary_file_transfers_power" >"开启文件传输模式并为其他设备充电"</string>
    <string name="usb_summary_tether_power" >"开启 USB 网络共享模式并为其他设备充电"</string>
    <string name="usb_summary_photo_transfers_power" >"开启 PTP 模式并为其他设备充电"</string>
    <string name="usb_summary_MIDI_power" >"开启 MIDI 模式并为其他设备充电"</string>
    <string name="background_check_pref" >"后台检查"</string>
    <string name="background_check_title" >"完整的后台访问权限"</string>
    <string name="assist_access_context_title" >"使用屏幕上的文字内容"</string>
    <string name="assist_access_context_summary" >"允许助手应用使用屏幕上的文字内容"</string>
    <string name="assist_access_screenshot_title" >"使用屏幕截图"</string>
    <string name="assist_access_screenshot_summary" >"允许助手应用使用屏幕截图"</string>
    <string name="assist_flash_title" >"闪烁屏幕"</string>
    <string name="assist_flash_summary" >"当助手应用访问屏幕/屏幕截图上的文字时，让屏幕边缘闪烁"</string>
    <string name="assist_footer" >"助手应用可根据您当前浏览的屏幕上的内容为您提供帮助。某些应用同时支持启动器和语音输入服务，可为您提供更全面的协助。"</string>
    <string name="average_memory_use" >"平均内存使用量"</string>
    <string name="maximum_memory_use" >"最高内存使用量"</string>
    <string name="memory_usage" >"内存使用量"</string>
    <string name="app_list_memory_use" >"应用的内存使用量"</string>
    <string name="memory_details" >"详细信息"</string>
    <string name="memory_use_summary" >"过去 3 小时内平均使用了 <xliff:g id="SIZE">%1$s</xliff:g> 内存"</string>
    <string name="no_memory_use_summary" >"过去 3 小时内未使用任何内存"</string>
    <string name="sort_avg_use" >"按平均使用量排序"</string>
    <string name="sort_max_use" >"按最高使用量排序"</string>
    <string name="memory_performance" >"性能"</string>
    <string name="total_memory" >"总内存"</string>
    <string name="average_used" >"平均使用量 (%)"</string>
    <string name="free_memory" >"可用"</string>
    <string name="memory_usage_apps" >"各个应用使用的内存"</string>
    <plurals name="memory_usage_apps_summary" formatted="false" >
      <item quantity="other">过去 <xliff:g id="DURATION_1">%2$s</xliff:g>内有 <xliff:g id="COUNT">%1$d</xliff:g> 个应用使用了内存</item>
      <item quantity="one">过去 <xliff:g id="DURATION_0">%2$s</xliff:g>内有 1 个应用使用了内存</item>
    </plurals>
    <string name="running_frequency" >"频率"</string>
    <string name="memory_maximum_usage" >"最高使用量"</string>
    <string name="no_data_usage" >"未使用任何数据流量"</string>
    <string name="zen_access_warning_dialog_title" >"要允许<xliff:g id="APP">%1$s</xliff:g>获取“勿扰”模式的使用权限吗？"</string>
    <string name="zen_access_warning_dialog_summary" >"此应用将可开启/关闭“勿扰”模式以及更改相关设置。"</string>
    <string name="zen_access_disabled_package_warning" >"必须保持启用状态，因为通知访问权限已开启"</string>
    <string name="zen_access_revoke_warning_dialog_title" >"要撤消<xliff:g id="APP">%1$s</xliff:g>对勿扰模式的使用权限吗？"</string>
    <string name="zen_access_revoke_warning_dialog_summary" >"系统将移除此应用创建的所有勿扰规则。"</string>
    <string name="ignore_optimizations_on" >"不优化"</string>
    <string name="ignore_optimizations_off" >"优化"</string>
    <string name="ignore_optimizations_on_desc" >"电池电量可能会更快耗尽。系统将不再限制应用在后台使用电量。"</string>
    <string name="ignore_optimizations_off_desc" >"建议选择此项以延长电池续航时间"</string>
    <string name="ignore_optimizations_title" >"要允许<xliff:g id="APP">%s</xliff:g>忽略电池优化吗？"</string>
    <string name="app_list_preference_none" >"无"</string>
    <string name="work_profile_usage_access_warning" >"即使关闭此应用的使用情况访问权限，也无法阻止您的管理员跟踪您工作资料中应用的数据用量"</string>
    <string name="accessibility_lock_screen_progress" >"已输入 <xliff:g id="COUNT_0">%1$d</xliff:g> 个字符（共可输入 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）"</string>
    <string name="draw_overlay" >"显示在其他应用的上层"</string>
    <string name="system_alert_window_settings" >"显示在其他应用的上层"</string>
    <string name="system_alert_window_apps_title" >"应用"</string>
    <string name="system_alert_window_access_title" >"显示在其他应用的上层"</string>
    <string name="permit_draw_overlay" >"允许显示在其他应用的上层"</string>
    <string name="allow_overlay_description" >"允许此应用显示在您当前使用的其他应用的上层。这可能会干扰您使用相关应用，或变更这类应用的显示或运行方式。"</string>
    <string name="keywords_vr_listener" >"vr 虚拟实境 监听器 立体 助手服务"</string>
    <string name="keywords_system_alert_window" >"系统 提醒 窗口 对话框 显示 上层 其他应用"</string>
    <string name="overlay_settings" >"显示在其他应用的上层"</string>
    <string name="system_alert_window_summary" >"<xliff:g id="COUNT_0">%1$d</xliff:g> 个（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）应用可以显示在其他应用的上层"</string>
    <string name="filter_overlay_apps" >"具有该权限的应用"</string>
    <string name="app_permission_summary_allowed" >"允许"</string>
    <string name="app_permission_summary_not_allowed" >"不允许"</string>
    <string name="keywords_install_other_apps" >"安装应用 未知来源"</string>
    <string name="write_settings" >"修改系统设置"</string>
    <string name="keywords_write_settings" >"写入 修改 系统 设置"</string>
    <string name="write_settings_summary" >"已允许 <xliff:g id="COUNT_0">%1$d</xliff:g> 个应用（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）修改系统设置"</string>
    <string name="filter_install_sources_apps" >"可以安装其他应用"</string>
    <string name="filter_write_settings_apps" >"可修改系统设置"</string>
    <string name="write_settings_title" >"可修改系统设置"</string>
    <string name="write_system_settings" >"修改系统设置"</string>
    <string name="permit_write_settings" >"允许修改系统设置"</string>
    <string name="write_settings_description" >"此权限允许应用修改系统设置。"</string>
    <string name="write_settings_on" >"允许"</string>
    <string name="write_settings_off" >"不允许"</string>
    <string name="external_source_switch_title" >"允许来自此来源的应用"</string>
    <string name="camera_gesture_title" >"扭转两次即可打开相机"</string>
    <string name="camera_gesture_desc" >"扭转手腕两次即可打开相机应用"</string>
    <string name="camera_double_tap_power_gesture_title" >"按电源按钮两次即可打开相机"</string>
    <string name="camera_double_tap_power_gesture_desc" >"在不解锁屏幕的情况下快速打开相机"</string>
    <string name="screen_zoom_title" >"显示大小"</string>
    <string name="screen_zoom_short_summary" >"放大或缩小屏幕上的内容"</string>
    <string name="screen_zoom_keywords" >"显示密度, 屏幕缩放, 比例, 调整比例"</string>
    <string name="screen_zoom_summary" >"缩小或放大屏幕上的内容。屏幕上部分应用的位置可能会有变动。"</string>
    <string name="screen_zoom_preview_title" >"预览"</string>
    <string name="screen_zoom_make_smaller_desc" >"缩小"</string>
    <string name="screen_zoom_make_larger_desc" >"放大"</string>
    <string name="screen_zoom_conversation_icon_alex" >"A"</string>
    <string name="screen_zoom_conversation_icon_pete" >"P"</string>
    <string name="screen_zoom_conversation_message_1" >"嗨，皮皮！"</string>
    <string name="screen_zoom_conversation_message_2" >"嗨，今天一起去喝杯咖啡聚聚，怎么样？"</string>
    <string name="screen_zoom_conversation_message_3" >"好啊！我知道这附近有一家不错的咖啡馆。"</string>
    <string name="screen_zoom_conversation_message_4" >"太棒了！"</string>
    <string name="screen_zoom_conversation_timestamp_1" >"周二下午 6:00"</string>
    <string name="screen_zoom_conversation_timestamp_2" >"周二下午 6:01"</string>
    <string name="screen_zoom_conversation_timestamp_3" >"周二下午 6:02"</string>
    <string name="screen_zoom_conversation_timestamp_4" >"周二下午 6:03"</string>
    <string name="disconnected" >"未连接"</string>
    <string name="data_usage_summary_format" >"已使用 <xliff:g id="AMOUNT">%1$s</xliff:g>"</string>
    <string name="data_usage_wifi_format" >"已使用 <xliff:g id="AMOUNT">^1</xliff:g>（通过 WLAN）"</string>
    <plurals name="notification_summary" formatted="false" >
      <item quantity="other">已屏蔽 <xliff:g id="COUNT">%d</xliff:g> 个应用的通知</item>
      <item quantity="one">已屏蔽 1 个应用的通知</item>
    </plurals>
    <string name="notification_summary_none" >"目前设为接收所有应用的通知"</string>
    <string name="apps_summary" >"已安装 <xliff:g id="COUNT">%1$d</xliff:g> 个应用"</string>
    <string name="apps_summary_example" >"已安装 24 个应用"</string>
    <string name="storage_summary" >"已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g> - 还剩 <xliff:g id="FREE_SPACE">%2$s</xliff:g>"</string>
    <string name="storage_summary_with_sdcard" >"内部存储空间：已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g>，还剩 <xliff:g id="FREE_SPACE">%2$s</xliff:g>"</string>
    <string name="display_summary" >"闲置 <xliff:g id="TIMEOUT_DESCRIPTION">%1$s</xliff:g>后进入休眠状态"</string>
    <string name="display_dashboard_summary" >"壁纸、休眠、字体大小"</string>
    <string name="display_summary_example" >"闲置 10 分钟后会进入休眠状态"</string>
    <string name="memory_summary" >"平均内存用量为 <xliff:g id="USED_MEMORY">%1$s</xliff:g>，共 <xliff:g id="TOTAL_MEMORY">%2$s</xliff:g>"</string>
    <string name="users_summary" >"目前登录的用户为：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="payment_summary" >"默认使用<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="location_on_summary" >"开启"</string>
    <string name="location_off_summary" >"关闭"</string>
    <string name="backup_disabled" >"备份功能已停用"</string>
    <string name="android_version_summary" >"已更新至 Android <xliff:g id="VERSION">%1$s</xliff:g>"</string>
    <string name="android_version_pending_update_summary" >"有新版本可用"</string>
    <string name="disabled_by_policy_title" >"不允许执行此操作"</string>
    <string name="disabled_by_policy_title_adjust_volume" >"无法调节音量"</string>
    <string name="disabled_by_policy_title_outgoing_calls" >"不允许使用通话功能"</string>
    <string name="disabled_by_policy_title_sms" >"不允许使用短信功能"</string>
    <string name="disabled_by_policy_title_camera" >"不允许使用相机"</string>
    <string name="disabled_by_policy_title_screen_capture" >"不允许使用屏幕截图功能"</string>
    <string name="disabled_by_policy_title_turn_off_backups" >"无法关闭备份功能"</string>
    <string name="disabled_by_policy_title_suspend_packages" >"无法打开此应用"</string>
    <string name="default_admin_support_msg" >"如有任何问题，请与您的 IT 管理员联系"</string>
    <string name="admin_support_more_info" >"更多详情"</string>
    <string name="admin_profile_owner_message" >"您的管理员可以监控和管理与您的工作资料相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="admin_profile_owner_user_message" >"您的管理员可以监控和管理与此用户相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="admin_device_owner_message" >"您的管理员可以监控和管理与此设备相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="condition_turn_off" >"关闭"</string>
    <string name="condition_turn_on" >"开启"</string>
    <string name="condition_expand_show" >"显示"</string>
    <string name="condition_expand_hide" >"隐藏"</string>
    <string name="condition_hotspot_title" >"热点已开启"</string>
    <string name="condition_hotspot_summary" >"便携式 WLAN 热点“<xliff:g id="ID_1">%1$s</xliff:g>”已开启，因此系统关闭了该设备的 WLAN。"</string>
    <string name="condition_airplane_title" >"已开启飞行模式"</string>
    <string name="condition_airplane_summary" >"当飞行模式开启时，WLAN、蓝牙和移动网络都将关闭。您可以视需要重新开启 WLAN 和蓝牙。"</string>
    <string name="condition_zen_title" >"“勿扰”模式已开启"</string>
    <string name="condition_battery_title" >"省电模式已开启"</string>
    <string name="condition_battery_summary" >"省电模式会关闭部分设备功能并限制应用"</string>
    <string name="condition_cellular_title" >"移动数据网络已关闭"</string>
    <string name="condition_cellular_summary" >"您只能通过 WLAN 网络连接到互联网"</string>
    <string name="condition_bg_data_title" >"流量节省程序已开启"</string>
    <string name="condition_bg_data_summary" >"您必须连接 WLAN 网络才能使用后台数据。如果无法连接 WLAN 网络，则部分应用或服务可能会受影响。"</string>
    <string name="condition_work_title" >"工作资料已关闭"</string>
    <string name="condition_work_summary" >"与您的工作资料相关的应用、后台同步功能和其他功能均已关闭。"</string>
    <string name="condition_device_muted_action_turn_on_sound" >"开启音效"</string>
    <string name="condition_device_muted_title" product="tablet" >"设备已设为静音"</string>
    <string name="condition_device_muted_title" product="default" >"手机已设为静音"</string>
    <string name="condition_device_muted_summary" >"有来电和通知时不会发出铃声"</string>
    <string name="condition_device_vibrate_title" product="tablet" >"设备已设为振动"</string>
    <string name="condition_device_vibrate_title" product="default" >"手机已设为振动"</string>
    <string name="condition_device_vibrate_summary" product="tablet" >"设备在有来电和通知时会振动"</string>
    <string name="condition_device_vibrate_summary" product="default" >"手机在有来电和通知时会振动"</string>
    <string name="night_display_suggestion_title" >"设置“夜间模式”时间安排"</string>
    <string name="night_display_suggestion_summary" >"每晚自动调节屏幕色调"</string>
    <string name="condition_night_display_title" >"“夜间模式”已开启"</string>
    <string name="condition_night_display_summary" >"屏幕已变成琥珀色，这可能有助于您安然入睡。"</string>
    <string name="suggestions_title_v2" >"为您推荐"</string>
    <string name="suggestions_title" >"建议"</string>
    <string name="suggestions_summary" >"+<xliff:g id="ID_1">%1$d</xliff:g>"</string>
    <string name="suggestions_more_title" >"另外 <xliff:g id="ID_1">%1$d</xliff:g> 条"</string>
    <plurals name="suggestions_collapsed_title" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 条建议</item>
      <item quantity="one">1 条建议</item>
    </plurals>
    <plurals name="suggestions_collapsed_summary" formatted="false" >
      <item quantity="other">另外 <xliff:g id="COUNT">%1$d</xliff:g> 条建议</item>
      <item quantity="one">另外 1 条建议</item>
    </plurals>
    <string name="suggestion_remove" >"移除"</string>
    <string name="color_temperature" >"冷色温"</string>
    <string name="color_temperature_desc" >"使用较冷的显示颜色"</string>
    <string name="color_temperature_toast" >"要应用颜色更改，请关闭屏幕"</string>
    <string name="camera_laser_sensor_switch" >"相机激光传感器"</string>
    <string name="ota_disable_automatic_update" >"系统自动更新"</string>
    <string name="ota_disable_automatic_update_summary" >"重启设备时应用更新"</string>
    <string name="usage" >"流量消耗"</string>
    <string name="cellular_data_usage" >"移动数据用量"</string>
    <string name="app_cellular_data_usage" >"应用的流量使用情况"</string>
    <string name="wifi_data_usage" >"WLAN 流量用量"</string>
    <string name="ethernet_data_usage" >"以太网流量用量"</string>
    <string name="wifi" >"WLAN"</string>
    <string name="ethernet" >"以太网"</string>
    <string name="cell_data_template" >"<xliff:g id="AMOUNT">^1</xliff:g>（移动数据流量）"</string>
    <string name="wifi_data_template" >"<xliff:g id="AMOUNT">^1</xliff:g> WLAN 流量"</string>
    <string name="ethernet_data_template" >"<xliff:g id="AMOUNT">^1</xliff:g> 以太网流量"</string>
    <string name="cell_warning_only" >"数据流量警告：<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="cell_warning_and_limit" >"数据流量警告：<xliff:g id="ID_1">%1$s</xliff:g>/数据流量上限：<xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="billing_cycle" >"数据流量警告和上限"</string>
    <string name="app_usage_cycle" >"应用流量消耗重计日期"</string>
    <string name="cell_data_warning" >"数据流量警告：<xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="cell_data_limit" >"数据流量上限：<xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="cell_data_warning_and_limit" >"数据流量警告：<xliff:g id="ID_1">^1</xliff:g>/数据流量上限：<xliff:g id="ID_2">^2</xliff:g>"</string>
    <string name="billing_cycle_fragment_summary" >"每个月的 <xliff:g id="ID_1">%1$s</xliff:g> 号"</string>
    <string name="network_restrictions" >"网络流量限制"</string>
    <plurals name="network_restrictions_summary" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 项限制</item>
      <item quantity="one">1 项限制</item>
    </plurals>
    <string name="operator_warning" >"运营商的流量计算方式可能与您设备的计算方式不同"</string>
    <string name="data_used_template" >"已使用 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="set_data_warning" >"设置数据流量警告"</string>
    <string name="data_warning" >"数据流量警告"</string>
    <string name="data_warning_footnote" >"您的设备会自行衡量数据流量警告和数据流量上限。这可能与运营商的数据不同。"</string>
    <string name="set_data_limit" >"设置数据流量上限"</string>
    <string name="data_limit" >"数据流量上限"</string>
    <string name="data_usage_template" >"<xliff:g id="ID_2">%2$s</xliff:g>期间已使用 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="configure" >"配置"</string>
    <string name="data_usage_other_apps" >"其他消耗流量的应用"</string>
    <plurals name="data_saver_unrestricted_summary" formatted="false" >
      <item quantity="other">已允许 <xliff:g id="COUNT">%1$d</xliff:g> 个应用在流量节省程序开启时无限量使用数据流量。</item>
      <item quantity="one">已允许 1 个应用在流量节省程序开启时无限量使用数据流量。</item>
    </plurals>
    <string name="data_usage_title" >"主要数据"</string>
    <string name="data_usage_wifi_title" >"WLAN 数据"</string>
    <string name="data_used" >"已使用 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="data_used_formatted" >"已使用 <xliff:g id="ID_1">^1</xliff:g> <xliff:g id="ID_2">^2</xliff:g>"</string>
    <string name="data_overusage" >"超过 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="data_remaining" >"还剩 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <plurals name="billing_cycle_days_left" formatted="false" >
      <item quantity="other">还剩 %d 天</item>
      <item quantity="one">还剩 %d 天</item>
    </plurals>
    <string name="billing_cycle_none_left" >"已没有剩余时间"</string>
    <string name="billing_cycle_less_than_one_day_left" >"还剩不到 1 天"</string>
    <string name="carrier_and_update_text" >"<xliff:g id="ID_1">^1</xliff:g>已在 <xliff:g id="ID_2">^2</xliff:g>前更新"</string>
    <string name="no_carrier_update_text" >"已在 <xliff:g id="ID_1">^2</xliff:g>前更新"</string>
    <string name="carrier_and_update_now_text" >"刚刚由<xliff:g id="ID_1">^1</xliff:g>更新"</string>
    <string name="no_carrier_update_now_text" >"刚刚更新"</string>
    <string name="launch_mdp_app_text" >"查看流量套餐"</string>
    <string name="launch_wifi_text" >"查看详情"</string>
    <string name="data_saver_title" >"流量节省程序"</string>
    <string name="unrestricted_data_saver" >"不受流量限制"</string>
    <string name="restrict_background_blacklisted" >"后台数据已关闭"</string>
    <string name="data_saver_on" >"开启"</string>
    <string name="data_saver_off" >"关闭"</string>
    <string name="data_saver_switch_title" >"使用流量节省程序"</string>
    <string name="unrestricted_app_title" >"不限制数据流量用量"</string>
    <string name="unrestricted_app_summary" >"允许在流量节省程序开启时无限量使用数据流量"</string>
    <string name="home_app" >"主屏幕应用"</string>
    <string name="no_default_home" >"没有默认主屏幕"</string>
    <string name="lockpattern_settings_require_cred_before_startup" >"安全启动"</string>
    <string name="lockpattern_settings_require_pattern_before_startup_summary" >"必须绘制解锁图案才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="lockpattern_settings_require_pin_before_startup_summary" >"必须输入 PIN 码才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="lockpattern_settings_require_password_before_startup_summary" >"必须输入密码才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="suggestion_additional_fingerprints" >"添加其他指纹"</string>
    <string name="suggestion_additional_fingerprints_summary" >"使用其他指纹解锁"</string>
    <string name="battery_saver_on_summary" >"开启"</string>
    <string name="battery_saver_off_scheduled_summary" >"在电量降到 <xliff:g id="BATTERY_PERCENTAGE">%1$s</xliff:g> 时开启"</string>
    <string name="battery_saver_off_summary" >"关闭"</string>
    <string name="battery_saver_button_turn_on" >"立即开启"</string>
    <string name="battery_saver_button_turn_off" >"立即关闭"</string>
    <string name="not_battery_optimizing" >"未使用电池优化设置"</string>
    <string name="lockscreen_remote_input" >"设备处于锁定状态时，禁止在通知中输入回复内容或其他文字"</string>
    <string name="default_spell_checker" >"默认拼写检查工具"</string>
    <string name="choose_spell_checker" >"选择拼写检查工具"</string>
    <string name="spell_checker_master_switch_title" >"使用拼写检查工具"</string>
    <string name="spell_checker_not_selected" >"未选择"</string>
    <string name="notification_log_no_title" >"（无）"</string>
    <string name="notification_log_details_delimiter" >"： "</string>
    <string name="notification_log_details_package" >"pkg"</string>
    <string name="notification_log_details_key" >"键"</string>
    <string name="notification_log_details_group" >"群组"</string>
    <string name="notification_log_details_group_summary" >"（摘要）"</string>
    <string name="notification_log_details_visibility" >"公开范围"</string>
    <string name="notification_log_details_public_version" >"公开版本"</string>
    <string name="notification_log_details_priority" >"优先级"</string>
    <string name="notification_log_details_importance" >"重要程度"</string>
    <string name="notification_log_details_explanation" >"说明"</string>
    <string name="notification_log_details_badge" >"可显示状态标记"</string>
    <string name="notification_log_details_content_intent" >"intent"</string>
    <string name="notification_log_details_delete_intent" >"删除 intent"</string>
    <string name="notification_log_details_full_screen_intent" >"全屏 intent"</string>
    <string name="notification_log_details_actions" >"操作"</string>
    <string name="notification_log_details_title" >"标题"</string>
    <string name="notification_log_details_remoteinput" >"远程输入"</string>
    <string name="notification_log_details_content_view" >"自定义视图"</string>
    <string name="notification_log_details_extras" >"其他"</string>
    <string name="notification_log_details_icon" >"图标"</string>
    <string name="notification_log_details_parcel" >"parcel 大小"</string>
    <string name="notification_log_details_ashmem" >"ashmem"</string>
    <string name="notification_log_details_sound" >"提示音"</string>
    <string name="notification_log_details_vibrate" >"振动"</string>
    <string name="notification_log_details_default" >"默认"</string>
    <string name="notification_log_details_none" >"无"</string>
    <string name="notification_log_details_ranking_null" >"缺少排名对象。"</string>
    <string name="notification_log_details_ranking_none" >"排名对象不包含此键。"</string>
    <string name="display_cutout_emulation" >"模拟“刘海屏”"</string>
    <string name="display_cutout_emulation_keywords" >"显示屏凹口, 凹口"</string>
    <string name="display_cutout_emulation_none" >"无"</string>
    <string name="special_access" >"特殊应用权限"</string>
    <plurals name="special_access_summary" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个应用可以无限量使用流量</item>
      <item quantity="one">1 个应用可以无限量使用流量</item>
    </plurals>
    <string name="confirm_convert_to_fbe_warning" >"您真的要清除用户数据并转换为文件加密吗？"</string>
    <string name="button_confirm_convert_fbe" >"清除并转换"</string>
    <string name="reset_shortcut_manager_throttling" >"重置 ShortcutManager 调用频率限制"</string>
    <string name="reset_shortcut_manager_throttling_complete" >"已重置 ShortcutManager 调用频率限制"</string>
    <string name="notification_suggestion_title" >"控制锁定屏幕上显示的信息"</string>
    <string name="notification_suggestion_summary" >"显示或隐藏通知内容"</string>
    <string name="page_tab_title_summary" >"全部"</string>
    <string name="page_tab_title_support" >"提示和支持"</string>
    <string name="developer_smallest_width" >"最小宽度"</string>
    <string name="premium_sms_none" >"没有任何已安装的应用申请付费短信权限"</string>
    <string name="premium_sms_warning" >"“付费短信”可能会产生费用，而且相关费用将计入您的运营商帐单。如果您为某个应用启用该权限，那么您将能够使用该应用发送付费短信。"</string>
    <string name="premium_sms_access" >"付费短信权限"</string>
    <string name="bluetooth_disabled" >"关闭"</string>
    <string name="bluetooth_connected_summary" >"已连接到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_multiple_devices_summary" >"已连接到多部设备"</string>
    <string name="demo_mode" >"系统界面演示模式"</string>
    <string name="dark_ui_mode" >"夜间模式"</string>
    <string name="dark_ui_mode_title" >"设置夜间模式"</string>
    <string name="quick_settings_developer_tiles" >"快捷设置开发者图块"</string>
    <string name="winscope_trace_quick_settings_title" >"Winscope 跟踪"</string>
    <string name="support_country_format" >"<xliff:g id="COUNTRY">%1$s</xliff:g> - <xliff:g id="LANGUAGE">%2$s</xliff:g>"</string>
    <string name="managed_profile_settings_title" >"工作资料设置"</string>
    <string name="managed_profile_contact_search_title" >"联系人搜索"</string>
    <string name="managed_profile_contact_search_summary" >"允许您的单位搜索联系人，以便识别来电者和联系人的身份"</string>
    <plurals name="hours" formatted="false" >
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <plurals name="minutes" formatted="false" >
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="seconds" formatted="false" >
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 秒</item>
      <item quantity="one">1 秒</item>
    </plurals>
    <string name="automatic_storage_manager_settings" >"管理存储空间"</string>
    <string name="automatic_storage_manager_text" >"存储空间管理器会从您的设备中移除已备份的照片和视频，从而释放存储空间。"</string>
    <string name="automatic_storage_manager_days_title" >"移除照片和视频"</string>
    <string name="automatic_storage_manager_preference_title" >"存储空间管理器"</string>
    <string name="automatic_storage_manager_master_switch_title" >"使用存储空间管理器"</string>
    <string name="deletion_helper_automatic_title" >"自动"</string>
    <string name="deletion_helper_manual_title" >"手动"</string>
    <string name="deletion_helper_preference_title" >"立即释放空间"</string>
    <string name="gesture_preference_title" >"手势"</string>
    <string name="gesture_preference_summary" product="default" >"通过简单手势控制手机"</string>
    <string name="gesture_preference_summary" product="tablet" >"通过简单手势控制平板电脑"</string>
    <string name="gesture_preference_summary" product="device" >"通过简单手势控制设备"</string>
    <string name="double_tap_power_for_camera_title" >"快速打开相机"</string>
    <string name="double_tap_power_for_camera_summary" >"不论当前显示的是何屏幕画面，只需按两次电源按钮，即可快速打开相机。"</string>
    <string name="double_tap_power_for_camera_suggestion_title" >"快速打开相机"</string>
    <string name="double_twist_for_camera_mode_title" >"切换相机模式"</string>
    <string name="double_twist_for_camera_mode_summary" ></string>
    <string name="double_twist_for_camera_suggestion_title" >"自拍更便捷"</string>
    <string name="swipe_up_to_switch_apps_title" >"在主屏幕按钮上向上滑动"</string>
    <string name="swipe_up_to_switch_apps_summary" >"要切换应用，请在主屏幕按钮上向上滑动，再次向上滑动即可看到所有应用（适用于任何屏幕）。您屏幕的右下方将不再显示“概览”按钮。"</string>
    <string name="swipe_up_to_switch_apps_suggestion_title" >"试用新版主屏幕按钮"</string>
    <string name="swipe_up_to_switch_apps_suggestion_summary" >"开启新手势即可切换应用"</string>
    <string name="ambient_display_title" product="default" >"点按两次即可查看手机"</string>
    <string name="ambient_display_title" product="tablet" >"点按两次即可查看平板电脑"</string>
    <string name="ambient_display_title" product="device" >"点按两次即可查看设备"</string>
    <string name="ambient_display_summary" >"点按两次屏幕即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_title" product="default" >"拿起手机即显示"</string>
    <string name="ambient_display_pickup_title" product="tablet" >"拿起平板电脑即可查看"</string>
    <string name="ambient_display_pickup_title" product="device" >"拿起设备即可查看"</string>
    <string name="ambient_display_pickup_summary" product="default" >"拿起您的手机即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_summary" product="tablet" >"拿起您的平板电脑即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_summary" product="device" >"要查看时间、通知图标和其他信息，请拿起您的设备。"</string>
    <string name="fingerprint_swipe_for_notifications_title" >"滑动指纹即可查看通知"</string>
    <string name="fingerprint_gesture_screen_title" >"滑动指纹"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="default" >"要查看通知，请在手机背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="tablet" >"要查看通知，请在平板电脑背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="device" >"要查看通知，请在设备背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_suggestion_title" >"快速查看通知"</string>
    <string name="gesture_setting_on" >"开启"</string>
    <string name="gesture_setting_off" >"关闭"</string>
    <string name="oem_unlock_enable_disabled_summary_bootloader_unlocked" >"引导加载程序已解锁"</string>
    <string name="oem_unlock_enable_disabled_summary_connectivity" >"请先连接到互联网"</string>
    <string name="oem_unlock_enable_disabled_summary_connectivity_or_locked" >"请连接到互联网或与您的运营商联系"</string>
    <string name="oem_unlock_enable_disabled_summary_sim_locked_device" >"在与运营商绑定的设备上无法使用"</string>
    <string name="oem_lock_info_message" >"请重启设备以启用设备保护功能。"</string>
    <string name="automatic_storage_manager_freed_bytes" >"总共释放空间：<xliff:g id="SIZE">%1$s</xliff:g>\n\n上次执行日期：<xliff:g id="DATE">%2$s</xliff:g>"</string>
    <string name="web_action_enable_title" >"免安装应用"</string>
    <string name="web_action_enable_summary" >"在应用中打开链接（即使未安装相关应用也无妨）"</string>
    <string name="web_action_section_title" >"免安装应用"</string>
    <string name="instant_apps_settings" >"免安装应用偏好设置"</string>
    <string name="domain_url_section_title" >"已安装的应用"</string>
    <string name="automatic_storage_manager_activation_warning" >"您的存储空间目前是由存储空间管理器管理"</string>
    <string name="account_for_section_header" >"<xliff:g id="USER_NAME">%1$s</xliff:g>的帐号"</string>
    <string name="configure_section_header" >"配置"</string>
    <string name="auto_sync_account_title" >"自动同步数据"</string>
    <string name="auto_sync_personal_account_title" >"自动同步个人数据"</string>
    <string name="auto_sync_work_account_title" >"自动同步工作数据"</string>
    <string name="auto_sync_account_summary" >"让应用自动刷新数据"</string>
    <string name="account_sync_title" >"帐号同步"</string>
    <string name="account_sync_summary_some_on" >"已开启 <xliff:g id="ID_1">%1$d</xliff:g> 项（共 <xliff:g id="ID_2">%2$d</xliff:g> 项）的同步功能"</string>
    <string name="account_sync_summary_all_on" >"已开启所有项的同步功能"</string>
    <string name="account_sync_summary_all_off" >"已关闭所有项的同步功能"</string>
    <string name="enterprise_privacy_settings" >"受管理设备的信息"</string>
    <string name="enterprise_privacy_settings_summary_generic" >"由贵单位管理的更改和设置"</string>
    <string name="enterprise_privacy_settings_summary_with_name" >"由“<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>”管理的更改和设置"</string>
    <string name="enterprise_privacy_header" >"为了让您能够访问工作数据，贵单位可能会更改相应的设置并在您的设备上安装软件。\n\n要了解详情，请与贵单位的管理员联系。"</string>
    <string name="enterprise_privacy_exposure_category" >"贵单位可查看的信息类型"</string>
    <string name="enterprise_privacy_exposure_changes_category" >"贵单位的管理员所做的更改"</string>
    <string name="enterprise_privacy_device_access_category" >"您对此设备的访问权限"</string>
    <string name="enterprise_privacy_enterprise_data" >"与您的工作帐号关联的数据（例如电子邮件和日历）"</string>
    <string name="enterprise_privacy_installed_packages" >"您设备上的应用列表"</string>
    <string name="enterprise_privacy_usage_stats" >"每个应用的使用时长和数据用量"</string>
    <string name="enterprise_privacy_network_logs" >"最新的网络流量日志"</string>
    <string name="enterprise_privacy_bug_reports" >"最新的错误报告"</string>
    <string name="enterprise_privacy_security_logs" >"最新的安全日志"</string>
    <string name="enterprise_privacy_none" >"无"</string>
    <string name="enterprise_privacy_enterprise_installed_packages" >"已安装的应用"</string>
    <string name="enterprise_privacy_apps_count_estimation_info" >"应用数量是估算值，其中可能不包括从 Play 商店以外的来源安装的应用。"</string>
    <plurals name="enterprise_privacy_number_packages_lower_bound" formatted="false" >
      <item quantity="other">至少 <xliff:g id="COUNT_1">%d</xliff:g> 个应用</item>
      <item quantity="one">至少 <xliff:g id="COUNT_0">%d</xliff:g> 个应用</item>
    </plurals>
    <string name="enterprise_privacy_location_access" >"位置权限"</string>
    <string name="enterprise_privacy_microphone_access" >"麦克风使用权限"</string>
    <string name="enterprise_privacy_camera_access" >"相机使用权"</string>
    <string name="enterprise_privacy_enterprise_set_default_apps" >"默认应用"</string>
    <plurals name="enterprise_privacy_number_packages" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个应用</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个应用</item>
    </plurals>
    <string name="enterprise_privacy_input_method" >"默认键盘"</string>
    <string name="enterprise_privacy_input_method_name" >"已设为<xliff:g id="APP_LABEL">%s</xliff:g>"</string>
    <string name="enterprise_privacy_always_on_vpn_device" >"已开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_always_on_vpn_personal" >"已在您的个人资料中开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_always_on_vpn_work" >"已在您的工作资料中开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_global_http_proxy" >"已设置全局 HTTP 代理"</string>
    <string name="enterprise_privacy_ca_certs_device" >"可信凭据"</string>
    <string name="enterprise_privacy_ca_certs_personal" >"您个人资料中的可信凭据"</string>
    <string name="enterprise_privacy_ca_certs_work" >"您工作资料中的可信凭据"</string>
    <plurals name="enterprise_privacy_number_ca_certs" formatted="false" >
      <item quantity="other">至少 <xliff:g id="COUNT_1">%d</xliff:g> 个 CA 证书</item>
      <item quantity="one">至少 <xliff:g id="COUNT_0">%d</xliff:g> 个 CA 证书</item>
    </plurals>
    <string name="enterprise_privacy_lock_device" >"管理员可以锁定设备及重置密码"</string>
    <string name="enterprise_privacy_wipe_device" >"管理员可以删除所有设备数据"</string>
    <string name="enterprise_privacy_failed_password_wipe_device" >"输入错误密码的尝试次数上限（超过此上限将删除所有设备数据）"</string>
    <string name="enterprise_privacy_failed_password_wipe_work" >"输入错误密码的尝试次数上限（超过此上限将删除工作资料数据）"</string>
    <plurals name="enterprise_privacy_number_failed_password_wipe" formatted="false" >
      <item quantity="other">已尝试 <xliff:g id="COUNT_1">%d</xliff:g> 次</item>
      <item quantity="one">已尝试 <xliff:g id="COUNT_0">%d</xliff:g> 次</item>
    </plurals>
    <string name="enterprise_privacy_backups_enabled" >"系统正在备份此设备的数据"</string>
    <string name="do_disclosure_generic" >"此设备由贵单位管理。"</string>
    <string name="do_disclosure_with_name" >"此设备由“<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>”管理。"</string>
    <string name="do_disclosure_learn_more_separator" >" "</string>
    <string name="learn_more" >"了解详情"</string>
    <plurals name="default_camera_app_title" formatted="false" >
      <item quantity="other">相机应用</item>
      <item quantity="one">相机应用</item>
    </plurals>
    <string name="default_calendar_app_title" >"日历应用"</string>
    <string name="default_contacts_app_title" >"通讯录应用"</string>
    <plurals name="default_email_app_title" formatted="false" >
      <item quantity="other">电子邮件客户端应用</item>
      <item quantity="one">电子邮件客户端应用</item>
    </plurals>
    <string name="default_map_app_title" >"地图应用"</string>
    <plurals name="default_phone_app_title" formatted="false" >
      <item quantity="other">电话应用</item>
      <item quantity="one">电话应用</item>
    </plurals>
    <string name="app_names_concatenation_template_2" >"<xliff:g id="FIRST_APP_NAME">%1$s</xliff:g>、<xliff:g id="SECOND_APP_NAME">%2$s</xliff:g>"</string>
    <string name="app_names_concatenation_template_3" >"<xliff:g id="FIRST_APP_NAME">%1$s</xliff:g>、<xliff:g id="SECOND_APP_NAME">%2$s</xliff:g>、<xliff:g id="THIRD_APP_NAME">%3$s</xliff:g>"</string>
    <string name="storage_photos_videos" >"照片和视频"</string>
    <string name="storage_music_audio" >"音乐和音频"</string>
    <string name="storage_games" >"游戏"</string>
    <string name="storage_other_apps" >"其他应用"</string>
    <string name="storage_files" >"文件"</string>
    <string name="storage_size_large_alternate" >"<xliff:g id="NUMBER">^1</xliff:g>"<small>" "<font size="20">"<xliff:g id="UNIT">^2</xliff:g>"</font></small>""</string>
    <string name="storage_volume_total" >"已用空间（总共 <xliff:g id="TOTAL">%1$s</xliff:g>）"</string>
    <string name="storage_percent_full" >"已使用"</string>
    <string name="clear_instant_app_data" >"清除应用"</string>
    <string name="clear_instant_app_confirmation" >"要移除这个免安装应用吗？"</string>
    <string name="launch_instant_app" >"打开"</string>
    <string name="game_storage_settings" >"游戏"</string>
    <string name="audio_files_title" >"音频文件"</string>
    <string name="app_info_storage_title" >"已用空间"</string>
    <string name="webview_uninstalled_for_user" >"（已为用户<xliff:g id="USER">%s</xliff:g>卸载）"</string>
    <string name="webview_disabled_for_user" >"（已为用户<xliff:g id="USER">%s</xliff:g>停用）"</string>
    <string name="autofill_app" >"自动填充服务"</string>
    <string name="autofill_keywords" >"自动, 填充, 自动填充, auto, fill, autofill"</string>
    <string name="autofill_confirmation_message" >"&lt;b&gt;请确认这是您信任的应用&lt;/b&gt; &lt;br/&gt; &lt;br/&gt; &lt;xliff:g id=app_name example=Google Autofill&gt;%1$s&lt;/xliff:g&gt;会根据您的屏幕内容判断可自动填充哪些内容。"</string>
    <string name="color_theme" >"色调"</string>
    <string name="default_theme" >"默认"</string>
    <string name="device_theme" >"设备主题背景"</string>
    <string name="systemui_theme_wallpaper" >"自动（根据壁纸）"</string>
    <string name="systemui_theme_light" >"浅色"</string>
    <string name="systemui_theme_dark" >"深色"</string>
    <string name="show_operator_name_title" >"网络名称"</string>
    <string name="show_operator_name_summary" >"在状态栏中显示网络名称"</string>
    <string name="storage_manager_indicator" >"存储空间管理器：<xliff:g id="STATUS">^1</xliff:g>"</string>
    <string name="storage_manager_indicator_off" >"关闭"</string>
    <string name="storage_manager_indicator_on" >"开启"</string>
    <string name="install_type_instant" >"免安装应用"</string>
    <string name="automatic_storage_manager_deactivation_warning" >"要关闭存储空间管理器吗？"</string>
    <string name="storage_movies_tv" >"影视应用"</string>
    <string name="carrier_provisioning" >"运营商配置信息"</string>
    <string name="trigger_carrier_provisioning" >"触发运营商配置"</string>
    <string name="zen_suggestion_title" >"更新勿扰模式"</string>
    <string name="zen_suggestion_summary" >"暂停通知即可保持专注"</string>
    <string name="new_device_suggestion_title" >"新增了哪些精彩功能？"</string>
    <string name="new_device_suggestion_summary" product="default" >"新手机功能导览"</string>
    <string name="new_device_suggestion_summary" product="tablet" >"新平板电脑功能导览"</string>
    <string name="new_device_suggestion_summary" product="device" >"新设备功能导览"</string>
    <string name="disabled_low_ram_device" >"该设备不支持此功能"</string>
    <string name="enable_gnss_raw_meas_full_tracking" >"强制启用 GNSS 测量结果全面跟踪"</string>
    <string name="enable_gnss_raw_meas_full_tracking_summary" >"在停用工作周期的情况下跟踪所有 GNSS 星座和频率"</string>
    <string name="show_first_crash_dialog" >"一律显示崩溃对话框"</string>
    <string name="show_first_crash_dialog_summary" >"在每次应用崩溃时显示对话框"</string>
    <string name="directory_access" >"目录访问权限"</string>
    <string name="keywords_directory_access" >"目录访问权限"</string>
    <string name="directory_on_volume" >"<xliff:g id="VOLUME">%1$s</xliff:g>（<xliff:g id="DIRECTORY">%2$s</xliff:g>）"</string>
    <string name="unsupported_setting_summary" product="default" >"此手机不支持这项设置"</string>
    <string name="unsupported_setting_summary" product="tablet" >"此平板电脑不支持这项设置"</string>
    <string name="unsupported_setting_summary" product="device" >"此设备不支持这项设置"</string>
    <string name="disabled_for_user_setting_summary" >"当前用户无法更改设置"</string>
    <string name="disabled_dependent_setting_summary" >"必须一并更改其他设置"</string>
    <string name="unknown_unavailability_setting_summary" >"无法更改设置"</string>
    <string name="my_device_info_account_preference_title" >"帐号"</string>
    <string name="my_device_info_device_name_preference_title" >"设备名称"</string>
    <string name="bluetooth_on_while_driving_pref" >"开车时使用蓝牙"</string>
    <string name="bluetooth_on_while_driving_summary" >"开车时自动开启蓝牙"</string>
    <string name="change_wifi_state_title" >"WLAN 控制"</string>
    <string name="change_wifi_state_app_detail_switch" >"允许应用控制 WLAN"</string>
    <string name="change_wifi_state_app_detail_summary" >"允许此应用开启或关闭 WLAN、扫描及连接到 WLAN 网络、添加或移除网络，或启动仅限本地的热点"</string>
    <string name="media_output_title" >"媒体播放到"</string>
    <string name="media_output_default_summary" >"此设备"</string>
    <string name="media_output_summary" product="default" >"手机"</string>
    <string name="media_output_summary" product="tablet" >"平板电脑"</string>
    <string name="media_output_summary" product="device" >"设备"</string>
    <string name="media_out_summary_ongoing_call_state" >"通话期间无法使用"</string>
    <string name="media_output_summary_unavailable" >"无法使用"</string>
    <string name="take_call_on_title" >"接听来电的设备："</string>
    <string name="battery_suggestion_title" product="tablet" >"延长平板电脑的电池续航时间"</string>
    <string name="battery_suggestion_title" product="device" >"延长设备的电池续航时间"</string>
    <string name="battery_suggestion_title" product="default" >"延长手机的电池续航时间"</string>
    <string name="battery_suggestion_summary" ></string>
    <string name="gesture_prevent_ringing_screen_title" >"阻止响铃"</string>
    <string name="gesture_prevent_ringing_title" >"同时按电源和音量调高按钮"</string>
    <string name="gesture_prevent_ringing_sound_title" >"阻止响铃的快捷方式"</string>
    <string name="prevent_ringing_option_vibrate" >"振动"</string>
    <string name="prevent_ringing_option_mute" >"静音"</string>
    <string name="prevent_ringing_option_none" >"不发出任何铃声"</string>
    <string name="prevent_ringing_option_vibrate_summary" >"开启（振动）"</string>
    <string name="prevent_ringing_option_mute_summary" >"开启（静音）"</string>
    <string name="prevent_ringing_option_none_summary" >"关闭"</string>
    <string name="pref_title_network_details" >"网络详情"</string>
    <string name="about_phone_device_name_warning" >"您的设备名称会显示在手机上的应用中。此外，当您连接到蓝牙设备或设置 WLAN 热点时，其他人可能也会看到您的设备名称。"</string>
    <string name="devices_title" >"设备"</string>
</resources>

```

### arrays_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/res/values-zh-rCN/arrays.xml
packages_apps_Settings_res_arrays_cn.xml

```



<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
  <string-array name="timezone_filters">
    <item >"美洲"</item>
    <item >"欧洲"</item>
    <item >"非洲"</item>
    <item >"亚洲"</item>
    <item >"澳大利亚"</item>
    <item >"太平洋"</item>
    <item >"全部"</item>
  </string-array>
  <string-array name="screen_timeout_entries">
    <item >"15秒"</item>
    <item >"30秒"</item>
    <item >"1分钟"</item>
    <item >"2分钟"</item>
    <item >"5分钟"</item>
    <item >"10分钟"</item>
    <item >"30分钟"</item>
  </string-array>
  <string-array name="dream_timeout_entries">
    <item >"永不"</item>
    <item >"15秒"</item>
    <item >"30秒"</item>
    <item >"1分钟"</item>
    <item >"2分钟"</item>
    <item >"5分钟"</item>
    <item >"10分钟"</item>
    <item >"30分钟"</item>
  </string-array>
  <string-array name="lock_after_timeout_entries">
    <item >"立即"</item>
    <item >"5秒"</item>
    <item >"15秒"</item>
    <item >"30秒"</item>
    <item >"1分钟"</item>
    <item >"2分钟"</item>
    <item >"5分钟"</item>
    <item >"10分钟"</item>
    <item >"30分钟"</item>
  </string-array>
  <string-array name="entries_font_size">
    <item >"小"</item>
    <item >"默认"</item>
    <item >"大"</item>
    <item >"最大"</item>
  </string-array>
  <string-array name="wifi_status">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接..."</item>
    <item >"正在验证身份…"</item>
    <item >"正在获取IP地址..."</item>
    <item >"已连接"</item>
    <item >"已暂停"</item>
    <item >"正在断开连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="wifi_status_with_ssid">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item >"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item >"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item >"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item >"已暂停"</item>
    <item >"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>
    <!-- no translation found for wifi_security:0 (8491993170197127709) -->
    <!-- no translation found for wifi_security:1 (6524315248437318854) -->
    <!-- no translation found for wifi_security:2 (1532568756571457140) -->
    <!-- no translation found for wifi_security:3 (3620707702811709779) -->
    <!-- no translation found for wifi_security_no_eap:0 (2084555984818107151) -->
    <!-- no translation found for wifi_security_no_eap:1 (397579322683471524) -->
    <!-- no translation found for wifi_security_no_eap:2 (1968820975358150484) -->
    <!-- no translation found for wifi_tether_security:0 (1748357338693290598) -->
    <!-- no translation found for wifi_tether_security:1 (4760482622566629462) -->
  <string-array name="wifi_tether_security_values">
    <item >"4"</item>
    <item >"0"</item>
  </string-array>
  <string-array name="wifi_eap_method">
    <item >"PEAP"</item>
    <item >"TLS"</item>
    <item >"TTLS"</item>
    <item >"PWD"</item>
    <item >"SIM"</item>
    <item >"AKA"</item>
    <item >"AKA\'"</item>
  </string-array>
  <string-array name="eap_method_without_sim_auth">
    <item >"PEAP"</item>
    <item >"TLS"</item>
    <item >"TTLS"</item>
    <item >"PWD"</item>
  </string-array>
    <!-- no translation found for wifi_ap_band_config_full:0 (1085243288162893079) -->
    <!-- no translation found for wifi_ap_band_config_full:1 (5531376834915607202) -->
    <!-- no translation found for wifi_ap_band_summary_full:0 (7176872102094020362) -->
    <!-- no translation found for wifi_ap_band_summary_full:1 (311895158827229479) -->
    <!-- no translation found for wifi_ap_band_config_2G_only:0 (7006771583217001015) -->
    <!-- no translation found for wifi_ap_band_config_2G_only:1 (8904289885593822837) -->
  <string-array name="wifi_p2p_wps_setup">
    <item >"按钮"</item>
    <item >"从对等设备获取的 PIN 码"</item>
    <item >"此设备生成的 PIN 码"</item>
  </string-array>
  <string-array name="wifi_p2p_status">
    <item >"已连接"</item>
    <item >"已邀请"</item>
    <item >"失败"</item>
    <item >"可用"</item>
    <item >"超出范围"</item>
  </string-array>
  <string-array name="bluetooth_visibility_timeout_entries">
    <item >"2分钟"</item>
    <item >"5分钟"</item>
    <item >"1小时"</item>
    <item >"永不超时"</item>
  </string-array>
  <string-array name="bluetooth_max_connected_audio_devices">
    <item >"使用系统默认值：<xliff:g id="DEFAULT_BLUETOOTH_MAX_CONNECTED_AUDIO_DEVICES">%1$d</xliff:g>"</item>
    <item >"1"</item>
    <item >"2"</item>
    <item >"3"</item>
    <item >"4"</item>
    <item >"5"</item>
  </string-array>
  <string-array name="wifi_signal">
    <item >"弱"</item>
    <item >"微弱"</item>
    <item >"一般"</item>
    <item >"良好"</item>
    <item >"极佳"</item>
  </string-array>
  <string-array name="data_usage_data_range">
    <item >"过去30天"</item>
    <item >"设置流量周期…"</item>
  </string-array>
  <string-array name="usage_stats_display_order_types">
    <item >"使用时间"</item>
    <item >"上次使用时间"</item>
    <item >"应用名称"</item>
  </string-array>
  <string-array name="wifi_eap_entries">
    <item >"PEAP"</item>
    <item >"TLS"</item>
    <item >"TTLS"</item>
    <item >"PWD"</item>
  </string-array>
  <string-array name="wifi_peap_phase2_entries">
    <item >"无"</item>
    <item >"MSCHAPV2"</item>
    <item >"GTC"</item>
  </string-array>
  <string-array name="wifi_peap_phase2_entries_with_sim_auth">
    <item >"无"</item>
    <item >"MSCHAPV2"</item>
    <item >"GTC"</item>
    <item >"SIM"</item>
    <item >"AKA"</item>
    <item >"AKA\'"</item>
  </string-array>
  <string-array name="wifi_phase2_entries">
    <item >"无"</item>
    <item >"PAP"</item>
    <item >"MSCHAP"</item>
    <item >"MSCHAPV2"</item>
    <item >"GTC"</item>
  </string-array>
  <string-array name="wifi_ip_settings">
    <item >"DHCP"</item>
    <item >"静态"</item>
  </string-array>
  <string-array name="wifi_proxy_settings">
    <item >"无"</item>
    <item >"手动"</item>
    <item >"代理自动配置"</item>
  </string-array>
  <string-array name="apn_auth_entries">
    <item >"无"</item>
    <item >"PAP"</item>
    <item >"CHAP"</item>
    <item >"PAP 或 CHAP"</item>
  </string-array>
  <string-array name="apn_protocol_entries">
    <item >"IPv4"</item>
    <item >"IPv6"</item>
    <item >"IPv4/IPv6"</item>
  </string-array>
  <string-array name="bearer_entries">
    <item >"未指定"</item>
    <item >"LTE"</item>
    <item >"HSPAP"</item>
    <item >"HSPA"</item>
    <item >"HSUPA"</item>
    <item >"HSDPA"</item>
    <item >"UMTS"</item>
    <item >"EDGE"</item>
    <item >"GPRS"</item>
    <item >"eHRPD"</item>
    <item >"EVDO_B"</item>
    <item >"EVDO_A"</item>
    <item >"EVDO_0"</item>
    <item >"1xRTT"</item>
    <item >"IS95B"</item>
    <item >"IS95A"</item>
  </string-array>
  <string-array name="mvno_type_entries">
    <item >"无"</item>
    <item >"SPN"</item>
    <item >"IMSI"</item>
    <item >"GID"</item>
  </string-array>
  <string-array name="app_install_location_entries">
    <item >"内部存储设备"</item>
    <item >"可卸载的SD卡"</item>
    <item >"由系统确定"</item>
  </string-array>
  <string-array name="app_ops_categories">
    <item >"位置"</item>
    <item >"个人"</item>
    <item >"短信"</item>
    <item >"媒体"</item>
    <item >"设备"</item>
  </string-array>
  <string-array name="app_ops_summaries">
    <item >"粗略位置"</item>
    <item >"精确位置"</item>
    <item >"GPS"</item>
    <item >"振动"</item>
    <item >"读取联系人"</item>
    <item >"修改联系人"</item>
    <item >"读取通话记录"</item>
    <item >"修改通话记录"</item>
    <item >"读取日历"</item>
    <item >"修改日历"</item>
    <item >"WLAN扫描"</item>
    <item >"通知"</item>
    <item >"手机网络扫描"</item>
    <item >"拨打电话"</item>
    <item >"读取短信"</item>
    <item >"编写短信"</item>
    <item >"接收短信"</item>
    <item >"接收紧急短信"</item>
    <item >"接收彩信"</item>
    <item >"接收 WAP PUSH 消息"</item>
    <item >"发送短信"</item>
    <item >"读取 ICC 短信"</item>
    <item >"写入 ICC 短信"</item>
    <item >"修改设置"</item>
    <item >"在最上层显示内容"</item>
    <item >"访问通知"</item>
    <item >"相机"</item>
    <item >"录制音频"</item>
    <item >"播放音频"</item>
    <item >"读取剪贴板内容"</item>
    <item >"修改剪贴板内容"</item>
    <item >"媒体按钮"</item>
    <item >"音频焦点"</item>
    <item >"主音量"</item>
    <item >"语音音量"</item>
    <item >"铃声音量"</item>
    <item >"媒体音量"</item>
    <item >"闹钟音量"</item>
    <item >"通知音量"</item>
    <item >"蓝牙音量"</item>
    <item >"保持唤醒状态"</item>
    <item >"监测位置"</item>
    <item >"监控高电耗位置信息服务"</item>
    <item >"获取使用情况统计信息"</item>
    <item >"将麦克风静音或取消静音"</item>
    <item >"显示问候语"</item>
    <item >"投影媒体"</item>
    <item >"激活 VPN"</item>
    <item >"写入壁纸"</item>
    <item >"辅助结构"</item>
    <item >"辅助屏幕截图"</item>
    <item >"读取手机状态"</item>
    <item >"添加语音邮件"</item>
    <item >"使用 SIP"</item>
    <item >"处理拨出电话"</item>
    <item >"指纹"</item>
    <item >"身体传感器"</item>
    <item >"读取小区广播"</item>
    <item >"模拟位置"</item>
    <item >"读取存储空间"</item>
    <item >"写入存储空间"</item>
    <item >"开启屏幕"</item>
    <item >"获取帐号"</item>
    <item >"在后台运行"</item>
    <item >"无障碍功能音量"</item>
  </string-array>
  <string-array name="app_ops_labels">
    <item >"位置"</item>
    <item >"位置"</item>
    <item >"位置"</item>
    <item >"振动"</item>
    <item >"读取联系人"</item>
    <item >"修改联系人"</item>
    <item >"读取通话记录"</item>
    <item >"修改通话记录"</item>
    <item >"读取日历"</item>
    <item >"修改日历"</item>
    <item >"位置"</item>
    <item >"发布通知"</item>
    <item >"位置"</item>
    <item >"拨打电话"</item>
    <item >"读取短信/彩信"</item>
    <item >"编写短信/彩信"</item>
    <item >"接收短信/彩信"</item>
    <item >"接收短信/彩信"</item>
    <item >"接收短信/彩信"</item>
    <item >"接收短信/彩信"</item>
    <item >"发送短信/彩信"</item>
    <item >"读取短信/彩信"</item>
    <item >"编写短信/彩信"</item>
    <item >"修改设置"</item>
    <item >"在最上层显示内容"</item>
    <item >"访问通知"</item>
    <item >"相机"</item>
    <item >"录制音频"</item>
    <item >"播放音频"</item>
    <item >"读取剪贴板内容"</item>
    <item >"修改剪贴板内容"</item>
    <item >"媒体按钮"</item>
    <item >"音频焦点"</item>
    <item >"主音量"</item>
    <item >"语音音量"</item>
    <item >"铃声音量"</item>
    <item >"媒体音量"</item>
    <item >"闹钟音量"</item>
    <item >"通知音量"</item>
    <item >"蓝牙音量"</item>
    <item >"保持唤醒状态"</item>
    <item >"位置"</item>
    <item >"位置信息"</item>
    <item >"获取使用情况统计信息"</item>
    <item >"将麦克风静音或取消静音"</item>
    <item >"显示问候语"</item>
    <item >"投影媒体"</item>
    <item >"激活 VPN"</item>
    <item >"写入壁纸"</item>
    <item >"辅助结构"</item>
    <item >"辅助屏幕截图"</item>
    <item >"读取手机状态"</item>
    <item >"添加语音邮件"</item>
    <item >"使用 SIP"</item>
    <item >"处理拨出电话"</item>
    <item >"指纹"</item>
    <item >"身体传感器"</item>
    <item >"读取小区广播"</item>
    <item >"模拟位置"</item>
    <item >"读取存储空间"</item>
    <item >"写入存储空间"</item>
    <item >"开启屏幕"</item>
    <item >"获取帐号"</item>
    <item >"在后台运行"</item>
    <item >"无障碍功能音量"</item>
  </string-array>
  <string-array name="long_press_timeout_selector_titles">
    <item >"短"</item>
    <item >"中"</item>
    <item >"长"</item>
  </string-array>
  <string-array name="captioning_typeface_selector_titles">
    <item >"默认"</item>
    <item >"Sans-serif"</item>
    <item >"Sans-serif condensed"</item>
    <item >"Sans-serif monospace"</item>
    <item >"Serif"</item>
    <item >"Serif monospace"</item>
    <item >"Casual"</item>
    <item >"Cursive"</item>
    <item >"Small capitals"</item>
  </string-array>
  <string-array name="captioning_font_size_selector_titles">
    <item >"超小"</item>
    <item >"小"</item>
    <item >"正常"</item>
    <item >"大"</item>
    <item >"超大"</item>
  </string-array>
  <string-array name="captioning_edge_type_selector_titles">
    <item >"默认"</item>
    <item >"无"</item>
    <item >"轮廓"</item>
    <item >"阴影"</item>
    <item >"凸起"</item>
    <item >"凹陷"</item>
  </string-array>
  <string-array name="captioning_opacity_selector_titles">
    <item >"25%"</item>
    <item >"50%"</item>
    <item >"75%"</item>
    <item >"100%"</item>
  </string-array>
  <string-array name="captioning_preset_selector_titles">
    <item >"使用应用默认样式"</item>
    <item >"黑底白字"</item>
    <item >"白底黑字"</item>
    <item >"黑底黄字"</item>
    <item >"蓝底黄字"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="vpn_types_long">
    <item >"PPTP VPN"</item>
    <item >"具有预共享密钥的 L2TP/IPSec VPN"</item>
    <item >"具有证书的 L2TP/IPSec VPN"</item>
    <item >"具有预共享密钥且进行 Xauth 身份验证的 IPSec VPN"</item>
    <item >"具有证书且进行 Xauth 身份验证的 IPSec VPN"</item>
    <item >"具有证书且进行混合身份验证的 IPSec VPN"</item>
  </string-array>
  <string-array name="vpn_states">
    <item >"连接已断开"</item>
    <item >"正在初始化..."</item>
    <item >"正在连接..."</item>
    <item >"已连接"</item>
    <item >"超时"</item>
    <item >"失败"</item>
  </string-array>
  <string-array name="security_settings_premium_sms_values">
    <item >"询问"</item>
    <item >"永不允许"</item>
    <item >"始终允许"</item>
  </string-array>
  <string-array name="ram_states">
    <item >"正常"</item>
    <item >"中等"</item>
    <item >"不足"</item>
    <item >"严重不足"</item>
    <item >"未知"</item>
  </string-array>
  <string-array name="proc_stats_memory_states">
    <item >"正常"</item>
    <item >"中等"</item>
    <item >"不足"</item>
    <item >"严重不足"</item>
  </string-array>
  <string-array name="proc_stats_process_states">
    <item >"常驻"</item>
    <item >"顶层 Activity"</item>
    <item >"重要（前台）"</item>
    <item >"重要（后台）"</item>
    <item >"备份"</item>
    <item >"重量级"</item>
    <item >"服务（运行中）"</item>
    <item >"服务（重新启动中）"</item>
    <item >"接收器"</item>
    <item >"主屏幕"</item>
    <item >"前一个 Activity"</item>
    <item >"已缓存 (Activity)"</item>
    <item >"已缓存（Activity 客户端）"</item>
    <item >"已缓存（空）"</item>
  </string-array>
  <string-array name="color_picker">
    <item >"青色"</item>
    <item >"蓝色"</item>
    <item >"靛青色"</item>
    <item >"紫色"</item>
    <item >"粉红色"</item>
    <item >"红色"</item>
  </string-array>
  <string-array name="automatic_storage_management_days">
    <item >"超过 30 天"</item>
    <item >"超过 60 天"</item>
    <item >"超过 90 天"</item>
  </string-array>
  <string-array name="wifi_metered_entries">
    <item >"自动检测"</item>
    <item >"视为按流量计费"</item>
    <item >"视为不按流量计费"</item>
  </string-array>
  <string-array name="wifi_hidden_entries">
    <item >"否"</item>
    <item >"是"</item>
  </string-array>
  <string-array name="dark_ui_mode_entries">
    <item >"自动（根据一天中的时段）"</item>
    <item >"始终开启"</item>
    <item >"始终关闭"</item>
  </string-array>
    <!-- no translation found for systemui_theme_entries:0 (2470122177508109711) -->
    <!-- no translation found for systemui_theme_entries:1 (5595198131199979987) -->
    <!-- no translation found for systemui_theme_entries:2 (883155044873038544) -->
</resources>


```



# /frameworks/base/core/res

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/res/res/values-zh-rCN/strings.xml
frameworks_base_core_res_strings_cn.xml

```


<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="byteShort" >"B"</string>
    <string name="kilobyteShort" >"kB"</string>
    <string name="megabyteShort" >"MB"</string>
    <string name="gigabyteShort" >"GB"</string>
    <string name="terabyteShort" >"TB"</string>
    <string name="petabyteShort" >"PB"</string>
    <string name="fileSizeSuffix" >"<xliff:g id="NUMBER">%1$s</xliff:g> <xliff:g id="UNIT">%2$s</xliff:g>"</string>
    <string name="untitled" >"&lt;未命名&gt;"</string>
    <string name="emptyPhoneNumber" >"（无电话号码）"</string>
    <string name="unknownName" >"未知"</string>
    <string name="defaultVoiceMailAlphaTag" >"语音信箱"</string>
    <string name="defaultMsisdnAlphaTag" >"MSISDN1"</string>
    <string name="mmiError" >"出现连接问题或 MMI 码无效。"</string>
    <string name="mmiFdnError" >"只能对固定拨号号码执行此类操作。"</string>
    <string name="mmiErrorWhileRoaming" >"漫游时无法通过您的手机来更改来电转接设置。"</string>
    <string name="serviceEnabled" >"已启用服务。"</string>
    <string name="serviceEnabledFor" >"已针对以下内容启用了服务："</string>
    <string name="serviceDisabled" >"已停用服务。"</string>
    <string name="serviceRegistered" >"注册成功。"</string>
    <string name="serviceErased" >"清除成功。"</string>
    <string name="passwordIncorrect" >"密码不正确。"</string>
    <string name="mmiComplete" >"MMI 码已完成。"</string>
    <string name="badPin" >"您输入的旧PIN码不正确。"</string>
    <string name="badPuk" >"您输入的PUK码不正确。"</string>
    <string name="mismatchPin" >"您输入的PIN码不一致。"</string>
    <string name="invalidPin" >"输入一个4至8位数的PIN码。"</string>
    <string name="invalidPuk" >"请输入至少8位数字的PUK码。"</string>
    <string name="needPuk" >"您的 SIM 卡已用 PUK 码锁定。请输入 PUK 码将其解锁。"</string>
    <string name="needPuk2" >"输入PUK2码以解锁SIM卡。"</string>
    <string name="enablePin" >"失败，请开启 SIM/RUIM 卡锁定设置。"</string>
    <plurals name="pinpuk_attempts" formatted="false" >
      <item quantity="other">您还可尝试 <xliff:g id="NUMBER_1">%d</xliff:g> 次。如果仍不正确，SIM 卡将被锁定。</item>
      <item quantity="one">您还可尝试 <xliff:g id="NUMBER_0">%d</xliff:g> 次。如果仍不正确，SIM 卡将被锁定。</item>
    </plurals>
    <string name="imei" >"IMEI"</string>
    <string name="meid" >"MEID"</string>
    <string name="ClipMmi" >"来电显示"</string>
    <string name="ClirMmi" >"本机号码"</string>
    <string name="ColpMmi" >"连接的线路ID"</string>
    <string name="ColrMmi" >"连接的线路ID限制"</string>
    <string name="CfMmi" >"来电转接"</string>
    <string name="CwMmi" >"来电等待"</string>
    <string name="BaMmi" >"通话限制"</string>
    <string name="PwdMmi" >"密码更改"</string>
    <string name="PinMmi" >"PIN码更改"</string>
    <string name="CnipMmi" >"显示号码"</string>
    <string name="CnirMmi" >"来电显示受限制"</string>
    <string name="ThreeWCMmi" >"三方通话"</string>
    <string name="RuacMmi" >"拒绝不想接听的骚扰电话"</string>
    <string name="CndMmi" >"主叫号码传送"</string>
    <string name="DndMmi" >"勿扰"</string>
    <string name="CLIRDefaultOnNextCallOn" >"默认不显示本机号码，在下一次通话中也不显示"</string>
    <string name="CLIRDefaultOnNextCallOff" >"默认不显示本机号码，但在下一次通话中显示"</string>
    <string name="CLIRDefaultOffNextCallOn" >"默认显示本机号码，但在下一次通话中不显示"</string>
    <string name="CLIRDefaultOffNextCallOff" >"默认显示本机号码，在下一次通话中也显示"</string>
    <string name="serviceNotProvisioned" >"未提供服务。"</string>
    <string name="CLIRPermanent" >"您无法更改来电显示设置。"</string>
    <string name="RestrictedOnDataTitle" >"无法使用移动数据服务"</string>
    <string name="RestrictedOnEmergencyTitle" >"无法使用紧急呼救服务"</string>
    <string name="RestrictedOnNormalTitle" >"无法使用语音通话服务"</string>
    <string name="RestrictedOnAllVoiceTitle" >"无法使用语音服务或紧急呼救服务"</string>
    <string name="RestrictedStateContent" >"已由运营商暂时关闭"</string>
    <string name="RestrictedStateContentMsimTemplate" >"SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g> 已由运营商暂时关闭"</string>
    <string name="NetworkPreferenceSwitchTitle" >"无法连接到移动网络"</string>
    <string name="NetworkPreferenceSwitchSummary" >"请尝试更改首选网络。点按即可更改。"</string>
    <string name="EmergencyCallWarningTitle" >"无法使用紧急呼救服务"</string>
    <string name="EmergencyCallWarningSummary" >"无法通过 WLAN 拨打紧急呼救电话"</string>
    <string name="notification_channel_network_alert" >"提醒"</string>
    <string name="notification_channel_call_forward" >"来电转接"</string>
    <string name="notification_channel_emergency_callback" >"紧急回拨模式"</string>
    <string name="notification_channel_mobile_data_status" >"移动数据状态"</string>
    <string name="notification_channel_sms" >"短信"</string>
    <string name="notification_channel_voice_mail" >"语音邮件"</string>
    <string name="notification_channel_wfc" >"WLAN 通话"</string>
    <string name="notification_channel_sim" >"SIM 卡状态"</string>
    <string name="peerTtyModeFull" >"对方请求使用“TTY 完整”模式"</string>
    <string name="peerTtyModeHco" >"对方请求使用“TTY HCO”模式"</string>
    <string name="peerTtyModeVco" >"对方请求使用“TTY VCO”模式"</string>
    <string name="peerTtyModeOff" >"对方请求使用“TTY 关闭”模式"</string>
    <string name="serviceClassVoice" >"语音"</string>
    <string name="serviceClassData" >"数据"</string>
    <string name="serviceClassFAX" >"传真"</string>
    <string name="serviceClassSMS" >"短信"</string>
    <string name="serviceClassDataAsync" >"异步"</string>
    <string name="serviceClassDataSync" >"同步"</string>
    <string name="serviceClassPacket" >"包"</string>
    <string name="serviceClassPAD" >"PAD"</string>
    <string name="roamingText0" >"启用漫游指示符"</string>
    <string name="roamingText1" >"禁用漫游指示符"</string>
    <string name="roamingText2" >"漫游指示符正在闪烁"</string>
    <string name="roamingText3" >"不在附近"</string>
    <string name="roamingText4" >"室外"</string>
    <string name="roamingText5" >"漫游 - 首选系统"</string>
    <string name="roamingText6" >"漫游 - 可用系统"</string>
    <string name="roamingText7" >"漫游 - 联盟合作伙伴"</string>
    <string name="roamingText8" >"漫游 - 高级合作伙伴"</string>
    <string name="roamingText9" >"漫游 - 全部服务功能"</string>
    <string name="roamingText10" >"漫游 - 服务功能不全"</string>
    <string name="roamingText11" >"启用漫游横幅"</string>
    <string name="roamingText12" >"禁用漫游横幅"</string>
    <string name="roamingTextSearching" >"正在搜索服务"</string>
    <string name="wfcRegErrorTitle" >"无法设置 WLAN 通话"</string>
  <string-array name="wfcOperatorErrorAlertMessages">
    <item >"要通过 WLAN 打电话和发信息，请先让您的运营商开通此服务，然后再到“设置”中重新开启 WLAN 通话功能（错误代码：<xliff:g id="CODE">%1$s</xliff:g>）。"</item>
  </string-array>
  <string-array name="wfcOperatorErrorNotificationMessages">
    <item >"向您的运营商注册 WLAN 通话时遇到问题：<xliff:g id="CODE">%1$s</xliff:g>"</item>
  </string-array>
  <string-array name="wfcSpnFormats">
    <item >"%s"</item>
    <item >"%s WLAN 通话功能"</item>
  </string-array>
    <string name="wifi_calling_off_summary" >"关闭"</string>
    <string name="wfc_mode_wifi_preferred_summary" >"首选 WLAN"</string>
    <string name="wfc_mode_cellular_preferred_summary" >"首选移动数据网络"</string>
    <string name="wfc_mode_wifi_only_summary" >"仅限 WLAN"</string>
    <string name="cfTemplateNotForwarded" >"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="cfTemplateForwarded" >"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：<xliff:g id="DIALING_NUMBER">{1}</xliff:g>"</string>
    <string name="cfTemplateForwardedTime" >"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：<xliff:g id="TIME_DELAY">{2}</xliff:g>秒后<xliff:g id="DIALING_NUMBER">{1}</xliff:g>"</string>
    <string name="cfTemplateRegistered" >"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="cfTemplateRegisteredTime" >"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="fcComplete" >"功能代码已拨完。"</string>
    <string name="fcError" >"出现连接问题或功能代码无效。"</string>
    <string name="httpErrorOk" >"确定"</string>
    <string name="httpError" >"发生了网络错误。"</string>
    <string name="httpErrorLookup" >"找不到该网址。"</string>
    <string name="httpErrorUnsupportedAuthScheme" >"不支持此网站身份验证方案。"</string>
    <string name="httpErrorAuth" >"无法通过身份验证。"</string>
    <string name="httpErrorProxyAuth" >"通过代理服务器进行身份验证失败。"</string>
    <string name="httpErrorConnect" >"无法连接到服务器。"</string>
    <string name="httpErrorIO" >"无法与服务器进行通信，请稍后再试。"</string>
    <string name="httpErrorTimeout" >"与服务器的连接超时。"</string>
    <string name="httpErrorRedirectLoop" >"该页面包含太多服务器重定向。"</string>
    <string name="httpErrorUnsupportedScheme" >"不支持该协议。"</string>
    <string name="httpErrorFailedSslHandshake" >"无法建立安全连接。"</string>
    <string name="httpErrorBadUrl" >"无法打开网页，因为该网址是无效的。"</string>
    <string name="httpErrorFile" >"无法使用该文件。"</string>
    <string name="httpErrorFileNotFound" >"找不到请求的文件。"</string>
    <string name="httpErrorTooManyRequests" >"正在处理的请求太多，请稍后重试。"</string>
    <string name="notification_title" >"登录 <xliff:g id="ACCOUNT">%1$s</xliff:g> 时出错"</string>
    <string name="contentServiceSync" >"同步"</string>
    <string name="contentServiceSyncNotificationTitle" >"无法同步"</string>
    <string name="contentServiceTooManyDeletesNotificationDesc" >"尝试删除的<xliff:g id="CONTENT_TYPE">%s</xliff:g>数量太多。"</string>
    <string name="low_memory" product="tablet" >"平板电脑存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="watch" >"手表存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="tv" >"电视存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="default" >"手机存储空间已满。请删除一些文件以腾出空间。"</string>
    <plurals name="ssl_ca_cert_warning" formatted="false" >
      <item quantity="other">已安装证书授权中心</item>
      <item quantity="one">已安装证书授权中心</item>
    </plurals>
    <string name="ssl_ca_cert_noti_by_unknown" >"受到不明第三方的监控"</string>
    <string name="ssl_ca_cert_noti_by_administrator" >"由您的工作资料管理员监控"</string>
    <string name="ssl_ca_cert_noti_managed" >"受到 <xliff:g id="MANAGING_DOMAIN">%s</xliff:g> 监控"</string>
    <string name="work_profile_deleted" >"工作资料已删除"</string>
    <string name="work_profile_deleted_details" >"工作资料管理应用缺失或损坏，因此系统已删除您的工作资料及相关数据。如需帮助，请与您的管理员联系。"</string>
    <string name="work_profile_deleted_description_dpm_wipe" >"您的工作资料已不在此设备上"</string>
    <string name="work_profile_deleted_reason_maximum_password_failure" >"密码尝试次数过多"</string>
    <string name="network_logging_notification_title" >"设备为受管理设备"</string>
    <string name="network_logging_notification_text" >"贵单位会管理该设备，且可能会监控网络流量。点按即可了解详情。"</string>
    <string name="factory_reset_warning" >"系统将清空您的设备"</string>
    <string name="factory_reset_message" >"无法使用管理应用，系统现在将清空您的设备。\n\n如有疑问，请与您所在单位的管理员联系。"</string>
    <string name="printing_disabled_by" >"“<xliff:g id="OWNER_APP">%s</xliff:g>”已停用打印功能。"</string>
    <string name="me" >"我"</string>
    <string name="power_dialog" product="tablet" >"平板电脑选项"</string>
    <string name="power_dialog" product="tv" >"电视选项"</string>
    <string name="power_dialog" product="default" >"手机选项"</string>
    <string name="silent_mode" >"静音模式"</string>
    <string name="turn_on_radio" >"打开无线电"</string>
    <string name="turn_off_radio" >"关闭无线电"</string>
    <string name="screen_lock" >"屏幕锁定"</string>
    <string name="power_off" >"关机"</string>
    <string name="silent_mode_silent" >"振铃器关闭"</string>
    <string name="silent_mode_vibrate" >"振铃器振动"</string>
    <string name="silent_mode_ring" >"振铃器开启"</string>
    <string name="reboot_to_update_title" >"Android 系统更新"</string>
    <string name="reboot_to_update_prepare" >"正在准备更新…"</string>
    <string name="reboot_to_update_package" >"正在处理更新软件包…"</string>
    <string name="reboot_to_update_reboot" >"正在重新启动…"</string>
    <string name="reboot_to_reset_title" >"恢复出厂设置"</string>
    <string name="reboot_to_reset_message" >"正在重新启动…"</string>
    <string name="shutdown_progress" >"正在关机..."</string>
    <string name="shutdown_confirm" product="tablet" >"您的平板电脑会关闭。"</string>
    <string name="shutdown_confirm" product="tv" >"您的电视即将关闭。"</string>
    <string name="shutdown_confirm" product="watch" >"您的手表即将关机。"</string>
    <string name="shutdown_confirm" product="default" >"您的手机将会关机。"</string>
    <string name="shutdown_confirm_question" >"您要关机吗？"</string>
    <string name="reboot_safemode_title" >"重新启动并进入安全模式"</string>
    <string name="reboot_safemode_confirm" >"您要重新启动并进入安全模式吗？这样会停用您已安装的所有第三方应用。再次重新启动将恢复这些应用。"</string>
    <string name="recent_tasks_title" >"近期任务"</string>
    <string name="no_recent_tasks" >"最近没有运行任何应用"</string>
    <string name="global_actions" product="tablet" >"平板电脑选项"</string>
    <string name="global_actions" product="tv" >"电视选项"</string>
    <string name="global_actions" product="default" >"手机选项"</string>
    <string name="global_action_lock" >"屏幕锁定"</string>
    <string name="global_action_power_off" >"关机"</string>
    <string name="global_action_emergency" >"紧急呼救"</string>
    <string name="global_action_bug_report" >"错误报告"</string>
    <string name="global_action_logout" >"结束会话"</string>
    <string name="global_action_screenshot" >"屏幕截图"</string>
    <string name="bugreport_title" >"提交错误报告"</string>
    <string name="bugreport_message" >"这会收集有关当前设备状态的信息，并以电子邮件的形式进行发送。从开始生成错误报告到准备好发送需要一点时间，请耐心等待。"</string>
    <string name="bugreport_option_interactive_title" >"互动式报告"</string>
    <string name="bugreport_option_interactive_summary" >"在大多数情况下，建议您使用此选项，以便追踪报告的生成进度，输入与相应问题相关的更多详细信息，以及截取屏幕截图。系统可能会省略掉一些不常用的区段，从而缩短生成报告的时间。"</string>
    <string name="bugreport_option_full_title" >"完整报告"</string>
    <string name="bugreport_option_full_summary" >"如果您的设备无响应或运行速度缓慢，或者您需要查看所有区段的报告信息，则建议您使用此选项将系统干扰程度降到最低。系统不支持您输入更多详细信息，也不会截取其他屏幕截图。"</string>
    <plurals name="bugreport_countdown" formatted="false" >
      <item quantity="other">系统将在 <xliff:g id="NUMBER_1">%d</xliff:g> 秒后对错误报告进行截屏。</item>
      <item quantity="one">系统将在 <xliff:g id="NUMBER_0">%d</xliff:g> 秒后对错误报告进行截屏。</item>
    </plurals>
    <string name="global_action_toggle_silent_mode" >"静音模式"</string>
    <string name="global_action_silent_mode_on_status" >"声音已关闭"</string>
    <string name="global_action_silent_mode_off_status" >"声音已开启"</string>
    <string name="global_actions_toggle_airplane_mode" >"飞行模式"</string>
    <string name="global_actions_airplane_mode_on_status" >"已开启飞行模式"</string>
    <string name="global_actions_airplane_mode_off_status" >"未开启飞行模式"</string>
    <string name="global_action_toggle_battery_saver" >"省电模式"</string>
    <string name="global_action_battery_saver_on_status" >"省电模式已关闭"</string>
    <string name="global_action_battery_saver_off_status" >"省电模式已开启"</string>
    <string name="global_action_settings" >"设置"</string>
    <string name="global_action_assist" >"助手应用"</string>
    <string name="global_action_voice_assist" >"语音助理"</string>
    <string name="global_action_lockdown" >"锁定"</string>
    <string name="status_bar_notification_info_overflow" >"999+"</string>
    <string name="notification_hidden_text" >"新通知"</string>
    <string name="notification_channel_virtual_keyboard" >"虚拟键盘"</string>
    <string name="notification_channel_physical_keyboard" >"实体键盘"</string>
    <string name="notification_channel_security" >"安全性"</string>
    <string name="notification_channel_car_mode" >"车载模式"</string>
    <string name="notification_channel_account" >"帐号状态"</string>
    <string name="notification_channel_developer" >"开发者消息"</string>
    <string name="notification_channel_updates" >"更新"</string>
    <string name="notification_channel_network_status" >"网络状态"</string>
    <string name="notification_channel_network_alerts" >"网络提醒"</string>
    <string name="notification_channel_network_available" >"有可用的网络"</string>
    <string name="notification_channel_vpn" >"VPN 状态"</string>
    <string name="notification_channel_device_admin" >"设备管理"</string>
    <string name="notification_channel_alerts" >"提醒"</string>
    <string name="notification_channel_retail_mode" >"零售演示模式"</string>
    <string name="notification_channel_usb" >"USB 连接"</string>
    <string name="notification_channel_heavy_weight_app" >"应用正在运行中"</string>
    <string name="notification_channel_foreground_service" >"消耗电量的应用"</string>
    <string name="foreground_service_app_in_background" >"<xliff:g id="APP_NAME">%1$s</xliff:g>正在消耗电量"</string>
    <string name="foreground_service_apps_in_background" >"<xliff:g id="NUMBER">%1$d</xliff:g> 个应用正在消耗电量"</string>
    <string name="foreground_service_tap_for_details" >"点按即可详细了解电量和流量消耗情况"</string>
    <string name="foreground_service_multiple_separator" >"<xliff:g id="LEFT_SIDE">%1$s</xliff:g>、<xliff:g id="RIGHT_SIDE">%2$s</xliff:g>"</string>
    <string name="safeMode" >"安全模式"</string>
    <string name="android_system_label" >"Android 系统"</string>
    <string name="user_owner_label" >"切换到个人资料"</string>
    <string name="managed_profile_label" >"切换到工作资料"</string>
    <string name="permgrouplab_contacts" >"通讯录"</string>
    <string name="permgroupdesc_contacts" >"访问您的通讯录"</string>
    <string name="permgrouprequest_contacts" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的通讯录吗？"</string>
    <string name="permgrouplab_location" >"位置信息"</string>
    <string name="permgroupdesc_location" >"获取此设备的位置信息"</string>
    <string name="permgrouprequest_location" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;获取此设备的位置信息吗？"</string>
    <string name="permgrouplab_calendar" >"日历"</string>
    <string name="permgroupdesc_calendar" >"访问您的日历"</string>
    <string name="permgrouprequest_calendar" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的日历吗？"</string>
    <string name="permgrouplab_sms" >"短信"</string>
    <string name="permgroupdesc_sms" >"发送和查看短信"</string>
    <string name="permgrouprequest_sms" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;发送和查看短信吗？"</string>
    <string name="permgrouplab_storage" >"存储空间"</string>
    <string name="permgroupdesc_storage" >"访问您设备上的照片、媒体内容和文件"</string>
    <string name="permgrouprequest_storage" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您设备上的照片、媒体内容和文件吗？"</string>
    <string name="permgrouplab_microphone" >"麦克风"</string>
    <string name="permgroupdesc_microphone" >"录制音频"</string>
    <string name="permgrouprequest_microphone" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;录音吗？"</string>
    <string name="permgrouplab_camera" >"相机"</string>
    <string name="permgroupdesc_camera" >"拍摄照片和录制视频"</string>
    <string name="permgrouprequest_camera" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;拍摄照片和录制视频吗？"</string>
    <string name="permgrouplab_calllog" >"通话记录"</string>
    <string name="permgroupdesc_calllog" >"读取和写入手机通话记录"</string>
    <string name="permgrouprequest_calllog" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的手机通话记录吗？"</string>
    <string name="permgrouplab_phone" >"电话"</string>
    <string name="permgroupdesc_phone" >"拨打电话和管理通话"</string>
    <string name="permgrouprequest_phone" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;拨打电话和管理通话吗？"</string>
    <string name="permgrouplab_sensors" >"身体传感器"</string>
    <string name="permgroupdesc_sensors" >"访问与您的生命体征相关的传感器数据"</string>
    <string name="permgrouprequest_sensors" >"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问与您的生命体征相关的传感器数据吗？"</string>
    <string name="capability_title_canRetrieveWindowContent" >"检索窗口内容"</string>
    <string name="capability_desc_canRetrieveWindowContent" >"检测您正访问的窗口的内容。"</string>
    <string name="capability_title_canRequestTouchExploration" >"启用触摸浏览"</string>
    <string name="capability_desc_canRequestTouchExploration" >"设备将大声读出您点按的内容，同时您可以通过手势来浏览屏幕。"</string>
    <string name="capability_title_canRequestFilterKeyEvents" >"监测您输入的文字"</string>
    <string name="capability_desc_canRequestFilterKeyEvents" >"包含个人数据，例如信用卡号和密码。"</string>
    <string name="capability_title_canControlMagnification" >"控制显示内容放大功能"</string>
    <string name="capability_desc_canControlMagnification" >"控制显示内容的缩放级别和位置。"</string>
    <string name="capability_title_canPerformGestures" >"执行手势"</string>
    <string name="capability_desc_canPerformGestures" >"可执行点按、滑动、双指张合等手势。"</string>
    <string name="capability_title_canCaptureFingerprintGestures" >"指纹手势"</string>
    <string name="capability_desc_canCaptureFingerprintGestures" >"可以捕获在设备指纹传感器上执行的手势。"</string>
    <string name="permlab_statusBar" >"停用或修改状态栏"</string>
    <string name="permdesc_statusBar" >"允许应用停用状态栏或者增删系统图标。"</string>
    <string name="permlab_statusBarService" >"用作状态栏"</string>
    <string name="permdesc_statusBarService" >"允许以状态栏形式显示应用。"</string>
    <string name="permlab_expandStatusBar" >"展开/收拢状态栏"</string>
    <string name="permdesc_expandStatusBar" >"允许应用展开或收起状态栏。"</string>
    <string name="permlab_install_shortcut" >"安装快捷方式"</string>
    <string name="permdesc_install_shortcut" >"允许应用自行添加主屏幕快捷方式。"</string>
    <string name="permlab_uninstall_shortcut" >"卸载快捷方式"</string>
    <string name="permdesc_uninstall_shortcut" >"允许应用自行删除主屏幕快捷方式。"</string>
    <string name="permlab_processOutgoingCalls" >"重新设置外拨电话的路径"</string>
    <string name="permdesc_processOutgoingCalls" >"允许应用在拨出电话时查看拨打的电话号码，并选择改为拨打其他号码或完全中止通话。"</string>
    <string name="permlab_answerPhoneCalls" >"接听来电"</string>
    <string name="permdesc_answerPhoneCalls" >"允许该应用接听来电。"</string>
    <string name="permlab_receiveSms" >"接收讯息（短信）"</string>
    <string name="permdesc_receiveSms" >"允许该应用接收和处理短信。这就意味着，该应用可能会监视发送到您设备的短信，或删除发送到您设备的短信而不向您显示。"</string>
    <string name="permlab_receiveMms" >"接收讯息（彩信）"</string>
    <string name="permdesc_receiveMms" >"允许该应用接收和处理彩信。这就意味着，该应用可能会监视发送到您设备的彩信，或删除发送到您设备的彩信而不向您显示。"</string>
    <string name="permlab_readCellBroadcasts" >"读取小区广播消息"</string>
    <string name="permdesc_readCellBroadcasts" >"允许应用读取您的设备收到的小区广播消息。小区广播消息是在某些地区发送的、用于发布紧急情况警告的提醒信息。恶意应用可能会在您收到小区紧急广播时干扰您设备的性能或操作。"</string>
    <string name="permlab_subscribedFeedsRead" >"读取订阅的供稿"</string>
    <string name="permdesc_subscribedFeedsRead" >"允许应用获取有关当前同步的 Feed 的详情。"</string>
    <string name="permlab_sendSms" >"发送短信"</string>
    <string name="permdesc_sendSms" >"允许该应用发送短信。此权限可能会导致意外收费。恶意应用可能会未经您的确认而发送短信，由此产生相关费用。"</string>
    <string name="permlab_readSms" >"读取短信"</string>
    <string name="permdesc_readSms" product="tablet" >"此应用可读取您平板电脑上存储的所有短信。"</string>
    <string name="permdesc_readSms" product="tv" >"此应用可读取您电视上存储的所有短信。"</string>
    <string name="permdesc_readSms" product="default" >"此应用可读取您手机上存储的所有短信。"</string>
    <string name="permlab_receiveWapPush" >"接收讯息 (WAP)"</string>
    <string name="permdesc_receiveWapPush" >"允许该应用接收和处理 WAP 消息。此权限包括监视发送给您的消息或删除发送给您的消息而不向您显示的功能。"</string>
    <string name="permlab_getTasks" >"检索正在运行的应用"</string>
    <string name="permdesc_getTasks" >"允许该应用检索近期运行的和当前正在运行的任务的相关信息。此权限可让该应用了解设备上使用了哪些应用。"</string>
    <string name="permlab_manageProfileAndDeviceOwners" >"管理个人资料和设备所有者"</string>
    <string name="permdesc_manageProfileAndDeviceOwners" >"允许应用设置个人资料所有者和设备所有者。"</string>
    <string name="permlab_reorderTasks" >"对正在运行的应用重新排序"</string>
    <string name="permdesc_reorderTasks" >"允许该应用将任务移动到前台和后台。该应用可能不经您的命令自行执行此操作。"</string>
    <string name="permlab_enableCarMode" >"启用车载模式"</string>
    <string name="permdesc_enableCarMode" >"允许应用启用车载模式。"</string>
    <string name="permlab_killBackgroundProcesses" >"关闭其他应用"</string>
    <string name="permdesc_killBackgroundProcesses" >"允许该应用结束其他应用的后台进程。此权限可导致其他应用停止运行。"</string>
    <string name="permlab_systemAlertWindow" >"此应用可显示在其他应用上方"</string>
    <string name="permdesc_systemAlertWindow" >"此应用可显示在其他应用上方或屏幕的其他部分。这可能会妨碍您正常地使用应用，且其他应用的显示方式可能会受到影响。"</string>
    <string name="permlab_runInBackground" >"在后台运行"</string>
    <string name="permdesc_runInBackground" >"此应用可在后台运行，这样可能会加快耗电速度。"</string>
    <string name="permlab_useDataInBackground" >"在后台使用数据"</string>
    <string name="permdesc_useDataInBackground" >"此应用可在后台使用数据，这样可能会增加流量消耗。"</string>
    <string name="permlab_persistentActivity" >"让应用始终运行"</string>
    <string name="permdesc_persistentActivity" product="tablet" >"允许该应用在内存中持续保留其自身的某些组件。这会限制其他应用可用的内存，从而减缓平板电脑运行速度。"</string>
    <string name="permdesc_persistentActivity" product="tv" >"允许应用在内存中持续保留其自身的部分组件。此权限可能会限制其他应用可用的内存，从而减缓电视运行速度。"</string>
    <string name="permdesc_persistentActivity" product="default" >"允许该应用在内存中持续保留其自身的某些组件。这会限制其他应用可用的内存，从而减缓手机运行速度。"</string>
    <string name="permlab_foregroundService" >"运行前台服务"</string>
    <string name="permdesc_foregroundService" >"允许该应用使用前台服务。"</string>
    <string name="permlab_getPackageSize" >"计算应用存储空间"</string>
    <string name="permdesc_getPackageSize" >"允许应用检索其代码、数据和缓存大小"</string>
    <string name="permlab_writeSettings" >"修改系统设置"</string>
    <string name="permdesc_writeSettings" >"允许应用修改系统的设置数据。恶意应用可能会破坏您的系统配置。"</string>
    <string name="permlab_receiveBootCompleted" >"开机启动"</string>
    <string name="permdesc_receiveBootCompleted" product="tablet" >"允许应用在系统完成引导后立即自动启动。这样可能会延长平板电脑的启动时间，并允许应用始终运行，从而导致平板电脑总体运行速度减慢。"</string>
    <string name="permdesc_receiveBootCompleted" product="tv" >"允许应用在系统启动完毕后立即自行启动。此权限可能会延长电视的启动时间，而且会因为系统一直运行该应用而导致电视的整体运行速度变慢。"</string>
    <string name="permdesc_receiveBootCompleted" product="default" >"允许应用在系统完成引导后立即自动启动。这样可能会延长手机的启动时间，并允许应用始终运行，从而导致手机总体运行速度减慢。"</string>
    <string name="permlab_broadcastSticky" >"发送持久广播"</string>
    <string name="permdesc_broadcastSticky" product="tablet" >"允许该应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致平板电脑使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permdesc_broadcastSticky" product="tv" >"允许应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致电视使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permdesc_broadcastSticky" product="default" >"允许该应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致手机使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permlab_readContacts" >"读取联系人"</string>
    <string name="permdesc_readContacts" product="tablet" >"允许该应用读取您平板电脑上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定个人通信的频率。此权限可让应用保存您的联系人数据，而恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permdesc_readContacts" product="tv" >"允许应用读取您的电视上存储的联系人相关数据，包括您与特定联系人通话、发送电子邮件或通过其他方式进行通信的频率。此权限可让应用保存您的联系人数据，而且恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permdesc_readContacts" product="default" >"允许该应用读取您手机上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定个人通信的频率。此权限可让应用保存您的联系人数据，而恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permlab_writeContacts" >"修改您的通讯录"</string>
    <string name="permdesc_writeContacts" product="tablet" >"允许该应用修改您平板电脑上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定联系人通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permdesc_writeContacts" product="tv" >"允许应用修改您的电视上存储的联系人相关数据，包括您与特定联系人通话、发送电子邮件或通过其他方式进行通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permdesc_writeContacts" product="default" >"允许该应用修改您手机上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定联系人通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permlab_readCallLog" >"读取通话记录"</string>
    <string name="permdesc_readCallLog" >"此应用可读取您的通话记录。"</string>
    <string name="permlab_writeCallLog" >"新建/修改/删除通话记录"</string>
    <string name="permdesc_writeCallLog" product="tablet" >"允许该应用修改平板电脑的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permdesc_writeCallLog" product="tv" >"允许应用修改电视的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permdesc_writeCallLog" product="default" >"允许该应用修改手机的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permlab_bodySensors" >"访问身体传感器（如心率监测器）"</string>
    <string name="permdesc_bodySensors" product="default" >"允许该应用存取监测您身体状况的传感器所收集的数据，例如您的心率。"</string>
    <string name="permlab_readCalendar" >"读取日历活动和详情"</string>
    <string name="permdesc_readCalendar" product="tablet" >"此应用可读取您平板电脑上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permdesc_readCalendar" product="tv" >"此应用可读取您电视上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permdesc_readCalendar" product="default" >"此应用可读取您手机上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permlab_writeCalendar" >"添加或修改日历活动，并在所有者不知情的情况下向邀请对象发送电子邮件"</string>
    <string name="permdesc_writeCalendar" product="tablet" >"此应用可在平板电脑上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permdesc_writeCalendar" product="tv" >"此应用可在电视上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permdesc_writeCalendar" product="default" >"此应用可在手机上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permlab_accessLocationExtraCommands" >"获取额外的位置信息提供程序命令"</string>
    <string name="permdesc_accessLocationExtraCommands" >"允许该应用使用其他的位置信息提供程序命令。此权限使该应用可以干扰GPS或其他位置信息源的运作。"</string>
    <string name="permlab_accessFineLocation" >"访问确切位置信息（以 GPS 和网络为依据）"</string>
    <string name="permdesc_accessFineLocation" >"此应用可根据 GPS 或网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的手机必须支持并开启这些位置信息服务，此应用才能使用这些服务。这可能会增加耗电量。"</string>
    <string name="permlab_accessCoarseLocation" >"访问大致位置信息（以网络为依据）"</string>
    <string name="permdesc_accessCoarseLocation" product="tablet" >"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的平板电脑必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permdesc_accessCoarseLocation" product="tv" >"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的电视必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permdesc_accessCoarseLocation" product="default" >"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的手机必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permlab_modifyAudioSettings" >"更改您的音频设置"</string>
    <string name="permdesc_modifyAudioSettings" >"允许该应用修改全局音频设置，例如音量和用于输出的扬声器。"</string>
    <string name="permlab_recordAudio" >"录音"</string>
    <string name="permdesc_recordAudio" >"此应用可随时使用麦克风进行录音。"</string>
    <string name="permlab_sim_communication" >"向 SIM 卡发送命令"</string>
    <string name="permdesc_sim_communication" >"允许应用向SIM卡发送命令（此权限具有很高的危险性）。"</string>
    <string name="permlab_camera" >"拍摄照片和视频"</string>
    <string name="permdesc_camera" >"此应用可随时使用相机拍摄照片和录制视频。"</string>
    <string name="permlab_vibrate" >"控制振动"</string>
    <string name="permdesc_vibrate" >"允许应用控制振动器。"</string>
    <string name="permlab_callPhone" >"拨打电话"</string>
    <string name="permdesc_callPhone" >"允许该应用在您未执行操作的情况下拨打电话号码。此权限可能会导致意外收费或呼叫。请注意，此权限不允许该应用拨打紧急电话号码。恶意应用可通过拨打电话产生相关费用，而无需您的确认。"</string>
    <string name="permlab_accessImsCallService" >"使用即时通讯通话服务"</string>
    <string name="permdesc_accessImsCallService" >"允许应用自行使用即时通讯服务拨打电话。"</string>
    <string name="permlab_readPhoneState" >"读取手机状态和身份"</string>
    <string name="permdesc_readPhoneState" >"允许该应用访问设备的电话功能。此权限可让该应用确定本机号码和设备 ID、是否正处于通话状态以及拨打的号码。"</string>
    <string name="permlab_manageOwnCalls" >"通过系统转接来电"</string>
    <string name="permdesc_manageOwnCalls" >"允许该应用通过系统转接来电，以改善通话体验。"</string>
    <string name="permlab_acceptHandover" >"继续进行来自其他应用的通话"</string>
    <string name="permdesc_acceptHandovers" >"允许该应用继续进行在其他应用中发起的通话。"</string>
    <string name="permlab_readPhoneNumbers" >"读取电话号码"</string>
    <string name="permdesc_readPhoneNumbers" >"允许该应用访问设备上的电话号码。"</string>
    <string name="permlab_wakeLock" product="tablet" >"阻止平板电脑进入休眠状态"</string>
    <string name="permlab_wakeLock" product="tv" >"阻止电视进入休眠状态"</string>
    <string name="permlab_wakeLock" product="default" >"防止手机休眠"</string>
    <string name="permdesc_wakeLock" product="tablet" >"允许应用阻止平板电脑进入休眠状态。"</string>
    <string name="permdesc_wakeLock" product="tv" >"允许应用阻止电视进入休眠状态。"</string>
    <string name="permdesc_wakeLock" product="default" >"允许应用阻止手机进入休眠状态。"</string>
    <string name="permlab_transmitIr" >"发射红外线"</string>
    <string name="permdesc_transmitIr" product="tablet" >"允许应用使用平板电脑的红外线发射器。"</string>
    <string name="permdesc_transmitIr" product="tv" >"允许应用使用电视的红外线发射器。"</string>
    <string name="permdesc_transmitIr" product="default" >"允许应用使用手机的红外线发射器。"</string>
    <string name="permlab_setWallpaper" >"设置壁纸"</string>
    <string name="permdesc_setWallpaper" >"允许应用设置系统壁纸。"</string>
    <string name="permlab_setWallpaperHints" >"调整您的壁纸大小"</string>
    <string name="permdesc_setWallpaperHints" >"允许应用设置有关系统壁纸大小的提示。"</string>
    <string name="permlab_setTimeZone" >"设置时区"</string>
    <string name="permdesc_setTimeZone" product="tablet" >"允许应用更改平板电脑的时区。"</string>
    <string name="permdesc_setTimeZone" product="tv" >"允许应用更改电视的时区。"</string>
    <string name="permdesc_setTimeZone" product="default" >"允许应用更改手机的时区。"</string>
    <string name="permlab_getAccounts" >"查找设备上的帐号"</string>
    <string name="permdesc_getAccounts" product="tablet" >"允许该应用获取平板电脑已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permdesc_getAccounts" product="tv" >"允许应用获取电视已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permdesc_getAccounts" product="default" >"允许该应用获取手机已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permlab_accessNetworkState" >"查看网络连接"</string>
    <string name="permdesc_accessNetworkState" >"允许该应用查看网络连接的相关信息，例如存在和连接的网络。"</string>
    <string name="permlab_createNetworkSockets" >"拥有完全的网络访问权限"</string>
    <string name="permdesc_createNetworkSockets" >"允许该应用创建网络套接字和使用自定义网络协议。浏览器和其他某些应用提供了向互联网发送数据的途径，因此应用无需该权限即可向互联网发送数据。"</string>
    <string name="permlab_changeNetworkState" >"更改网络连接性"</string>
    <string name="permdesc_changeNetworkState" >"允许应用更改网络连接的状态。"</string>
    <string name="permlab_changeTetherState" >"更改网络共享连接"</string>
    <string name="permdesc_changeTetherState" >"允许应用更改绑定网络连接的状态。"</string>
    <string name="permlab_accessWifiState" >"查看WLAN连接"</string>
    <string name="permdesc_accessWifiState" >"允许该应用查看WLAN网络的相关信息，例如是否启用了WLAN以及连接的WLAN设备的名称。"</string>
    <string name="permlab_changeWifiState" >"连接WLAN网络和断开连接"</string>
    <string name="permdesc_changeWifiState" >"允许该应用与WLAN接入点建立和断开连接，以及更改WLAN网络的设备配置。"</string>
    <string name="permlab_changeWifiMulticastState" >"允许接收WLAN多播"</string>
    <string name="permdesc_changeWifiMulticastState" product="tablet" >"允许该应用使用多播地址接收发送到WLAN网络上所有设备（而不仅仅是您的平板电脑）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permdesc_changeWifiMulticastState" product="tv" >"允许应用使用多播地址接收发送到 WLAN 网络中所有设备（而不仅仅是您的电视）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permdesc_changeWifiMulticastState" product="default" >"允许该应用使用多播地址接收发送到WLAN网络上所有设备（而不仅仅是您的手机）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permlab_bluetoothAdmin" >"访问蓝牙设置"</string>
    <string name="permdesc_bluetoothAdmin" product="tablet" >"允许应用配置本地蓝牙平板电脑，并允许其查找远程设备且与之配对。"</string>
    <string name="permdesc_bluetoothAdmin" product="tv" >"允许应用配置本地蓝牙电视，并允许其查找远程设备且与之配对。"</string>
    <string name="permdesc_bluetoothAdmin" product="default" >"允许应用配置本地蓝牙手机，并允许其查找远程设备且与之配对。"</string>
    <string name="permlab_accessWimaxState" >"建立或中断 WiMAX 网络连接"</string>
    <string name="permdesc_accessWimaxState" >"允许该应用确定是否启用了 WiMAX 以及连接的任何 WiMAX 网络的相关信息。"</string>
    <string name="permlab_changeWimaxState" >"更改 WiMAX 状态"</string>
    <string name="permdesc_changeWimaxState" product="tablet" >"允许该应用建立和断开平板电脑与 WiMAX 网络之间的连接。"</string>
    <string name="permdesc_changeWimaxState" product="tv" >"允许应用建立和断开电视与 WiMAX 网络之间的连接。"</string>
    <string name="permdesc_changeWimaxState" product="default" >"允许该应用建立和断开手机与 WiMAX 网络之间的连接。"</string>
    <string name="permlab_bluetooth" >"与蓝牙设备配对"</string>
    <string name="permdesc_bluetooth" product="tablet" >"允许该应用查看平板电脑上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permdesc_bluetooth" product="tv" >"允许应用查看电视上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permdesc_bluetooth" product="default" >"允许该应用查看手机上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permlab_nfc" >"控制近距离通信"</string>
    <string name="permdesc_nfc" >"允许应用与近距离无线通信(NFC)标签、卡和读取器通信。"</string>
    <string name="permlab_disableKeyguard" >"停用屏幕锁定"</string>
    <string name="permdesc_disableKeyguard" >"允许该应用停用键锁以及任何关联的密码安全措施。例如，让手机在接听来电时停用键锁，在通话结束后重新启用键锁。"</string>
    <string name="permlab_useBiometric" >"使用生物特征硬件"</string>
    <string name="permdesc_useBiometric" >"允许该应用使用生物特征硬件进行身份验证"</string>
    <string name="permlab_manageFingerprint" >"管理指纹硬件"</string>
    <string name="permdesc_manageFingerprint" >"允许该应用调用方法来添加和删除可用的指纹模板。"</string>
    <string name="permlab_useFingerprint" >"使用指纹硬件"</string>
    <string name="permdesc_useFingerprint" >"允许该应用使用指纹硬件进行身份验证"</string>
    <string name="fingerprint_acquired_partial" >"仅检测到部分指纹，请重试。"</string>
    <string name="fingerprint_acquired_insufficient" >"无法处理指纹，请重试。"</string>
    <string name="fingerprint_acquired_imager_dirty" >"指纹传感器有脏污。请擦拭干净，然后重试。"</string>
    <string name="fingerprint_acquired_too_fast" >"手指移动太快，请重试。"</string>
    <string name="fingerprint_acquired_too_slow" >"手指移动太慢，请重试。"</string>
  <string-array name="fingerprint_acquired_vendor">
  </string-array>
    <string name="fingerprint_not_recognized" >"无法识别"</string>
    <string name="fingerprint_authenticated" >"已验证指纹"</string>
    <string name="fingerprint_error_hw_not_available" >"指纹硬件无法使用。"</string>
    <string name="fingerprint_error_no_space" >"无法存储指纹。请移除一个现有的指纹。"</string>
    <string name="fingerprint_error_timeout" >"指纹录入操作超时，请重试。"</string>
    <string name="fingerprint_error_canceled" >"指纹操作已取消。"</string>
    <string name="fingerprint_error_user_canceled" >"用户取消了指纹操作。"</string>
    <string name="fingerprint_error_lockout" >"尝试次数过多，请稍后重试。"</string>
    <string name="fingerprint_error_lockout_permanent" >"尝试次数过多。指纹传感器已停用。"</string>
    <string name="fingerprint_error_unable_to_process" >"请重试。"</string>
    <string name="fingerprint_error_no_fingerprints" >"未注册任何指纹。"</string>
    <string name="fingerprint_error_hw_not_present" >"此设备没有指纹传感器"</string>
    <string name="fingerprint_name_template" >"手指 <xliff:g id="FINGERID">%d</xliff:g>"</string>
  <string-array name="fingerprint_error_vendor">
  </string-array>
    <string name="fingerprint_icon_content_description" >"指纹图标"</string>
    <string name="permlab_readSyncSettings" >"读取同步设置"</string>
    <string name="permdesc_readSyncSettings" >"允许该应用读取某个帐号的同步设置。例如，此权限可确定“联系人”应用是否与某个帐号同步。"</string>
    <string name="permlab_writeSyncSettings" >"启用和停用同步"</string>
    <string name="permdesc_writeSyncSettings" >"允许该应用修改某个帐号的同步设置。例如，此权限可用于在“联系人”应用与某个帐号之间启用同步。"</string>
    <string name="permlab_readSyncStats" >"读取同步统计信息"</string>
    <string name="permdesc_readSyncStats" >"允许该应用读取某个帐号的同步统计信息，包括同步活动历史记录和同步数据量。"</string>
    <string name="permlab_sdcardRead" product="nosdcard" >"读取您的USB存储设备中的内容"</string>
    <string name="permlab_sdcardRead" product="default" >"读取您的SD卡中的内容"</string>
    <string name="permdesc_sdcardRead" product="nosdcard" >"允许应用读取您USB存储设备中的内容。"</string>
    <string name="permdesc_sdcardRead" product="default" >"允许应用读取您SD卡的内容。"</string>
    <string name="permlab_sdcardWrite" product="nosdcard" >"修改或删除您的USB存储设备中的内容"</string>
    <string name="permlab_sdcardWrite" product="default" >"修改或删除您的SD卡中的内容"</string>
    <string name="permdesc_sdcardWrite" product="nosdcard" >"允许应用写入USB存储设备。"</string>
    <string name="permdesc_sdcardWrite" product="default" >"允许应用写入SD卡。"</string>
    <string name="permlab_use_sip" >"拨打/接听SIP电话"</string>
    <string name="permdesc_use_sip" >"允许该应用拨打和接听SIP电话。"</string>
    <string name="permlab_register_sim_subscription" >"注册新的电信 SIM 卡连接"</string>
    <string name="permdesc_register_sim_subscription" >"允许该应用注册新的电信 SIM 卡连接。"</string>
    <string name="permlab_register_call_provider" >"注册新的电信网络连接"</string>
    <string name="permdesc_register_call_provider" >"允许该应用注册新的电信网络连接。"</string>
    <string name="permlab_connection_manager" >"管理电信网络连接"</string>
    <string name="permdesc_connection_manager" >"允许该应用管理电信网络连接。"</string>
    <string name="permlab_bind_incall_service" >"与通话屏幕互动"</string>
    <string name="permdesc_bind_incall_service" >"允许应用控制用户看到通话屏幕的时机和方式。"</string>
    <string name="permlab_bind_connection_service" >"与电话服务交互"</string>
    <string name="permdesc_bind_connection_service" >"允许该应用与电话服务交互以便接打电话。"</string>
    <string name="permlab_control_incall_experience" >"向用户提供通话体验"</string>
    <string name="permdesc_control_incall_experience" >"允许应用向用户提供通话体验。"</string>
    <string name="permlab_readNetworkUsageHistory" >"读取网络使用情况历史记录"</string>
    <string name="permdesc_readNetworkUsageHistory" >"允许应用读取特定网络和应用的网络使用情况历史记录。"</string>
    <string name="permlab_manageNetworkPolicy" >"管理网络政策"</string>
    <string name="permdesc_manageNetworkPolicy" >"允许应用管理网络规范和定义专门针对应用的规则。"</string>
    <string name="permlab_modifyNetworkAccounting" >"修改网络使用情况记录方式"</string>
    <string name="permdesc_modifyNetworkAccounting" >"允许该应用修改对于各应用的网络使用情况的统计方式。普通应用不应使用此权限。"</string>
    <string name="permlab_accessNotifications" >"访问通知"</string>
    <string name="permdesc_accessNotifications" >"允许该应用检索、检查并清除通知，包括其他应用发布的通知。"</string>
    <string name="permlab_bindNotificationListenerService" >"绑定到通知侦听器服务"</string>
    <string name="permdesc_bindNotificationListenerService" >"允许应用绑定到通知侦听器服务的顶级接口（普通应用绝不需要此权限）。"</string>
    <string name="permlab_bindConditionProviderService" >"绑定到条件提供程序服务"</string>
    <string name="permdesc_bindConditionProviderService" >"允许应用绑定到条件提供程序服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindDreamService" >"绑定到互动屏保服务"</string>
    <string name="permdesc_bindDreamService" >"允许应用绑定到互动屏保服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_invokeCarrierSetup" >"调用运营商提供的配置应用"</string>
    <string name="permdesc_invokeCarrierSetup" >"允许应用调用运营商提供的配置应用。普通应用绝不需要此权限。"</string>
    <string name="permlab_accessNetworkConditions" >"监听网络状况的观测信息"</string>
    <string name="permdesc_accessNetworkConditions" >"允许应用监听网络状况的观测信息。普通应用绝不需要此权限。"</string>
    <string name="permlab_setInputCalibration" >"更改输入设备校准设置"</string>
    <string name="permdesc_setInputCalibration" >"允许应用修改触摸屏的校准参数。普通应用绝不需要此权限。"</string>
    <string name="permlab_accessDrmCertificates" >"访问DRM证书"</string>
    <string name="permdesc_accessDrmCertificates" >"允许应用配置和使用DRM证书。普通应用绝不需要此权限。"</string>
    <string name="permlab_handoverStatus" >"接收 Android Beam 的传输状态"</string>
    <string name="permdesc_handoverStatus" >"允许此应用接收Android Beam当前传输内容的相关信息"</string>
    <string name="permlab_removeDrmCertificates" >"移除DRM证书"</string>
    <string name="permdesc_removeDrmCertificates" >"允许应用移除DRM证书。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindCarrierMessagingService" >"绑定到运营商消息传递服务"</string>
    <string name="permdesc_bindCarrierMessagingService" >"允许应用绑定到运营商消息传递服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindCarrierServices" >"绑定到运营商服务"</string>
    <string name="permdesc_bindCarrierServices" >"允许应用绑定到运营商服务。普通应用绝不需要此权限。"</string>
    <string name="permlab_access_notification_policy" >"“勿扰”模式使用权限"</string>
    <string name="permdesc_access_notification_policy" >"允许此应用读取和写入“勿扰”模式配置。"</string>
    <string name="policylab_limitPassword" >"设置密码规则"</string>
    <string name="policydesc_limitPassword" >"控制锁屏密码和 PIN 码所允许的长度和字符。"</string>
    <string name="policylab_watchLogin" >"监控屏幕解锁尝试次数"</string>
    <string name="policydesc_watchLogin" product="tablet" >"监视在解锁屏幕时输错密码的次数，如果输错次数过多，则锁定平板电脑或清除其所有数据。"</string>
    <string name="policydesc_watchLogin" product="TV" >"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定电视或清除电视上的所有数据。"</string>
    <string name="policydesc_watchLogin" product="default" >"监视在解锁屏幕时输错密码的次数，如果输错次数过多，则锁定手机或清除其所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="tablet" >"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定平板电脑或清空此用户的所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="TV" >"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定电视或清空此用户的所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="default" >"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定手机或清空此用户的所有数据。"</string>
    <string name="policylab_resetPassword" >"更改锁屏密码"</string>
    <string name="policydesc_resetPassword" >"更改锁屏密码。"</string>
    <string name="policylab_forceLock" >"锁定屏幕"</string>
    <string name="policydesc_forceLock" >"控制屏幕锁定的方式和时间。"</string>
    <string name="policylab_wipeData" >"清除所有数据"</string>
    <string name="policydesc_wipeData" product="tablet" >"恢复出厂设置时，系统会在不发出警告的情况下清除平板电脑上的数据。"</string>
    <string name="policydesc_wipeData" product="tv" >"恢复出厂设置时，不发出警告就直接清除电视上的数据。"</string>
    <string name="policydesc_wipeData" product="default" >"恢复出厂设置时，系统会在不发出警告的情况下清除手机上的数据。"</string>
    <string name="policylab_wipeData_secondaryUser" >"清空用户数据"</string>
    <string name="policydesc_wipeData_secondaryUser" product="tablet" >"清空此用户在这台平板电脑上的数据，而不事先发出警告。"</string>
    <string name="policydesc_wipeData_secondaryUser" product="tv" >"清空此用户在这台电视上的数据，而不事先发出警告。"</string>
    <string name="policydesc_wipeData_secondaryUser" product="default" >"清空此用户在这部手机上的数据，而不事先发出警告。"</string>
    <string name="policylab_setGlobalProxy" >"设置设备全局代理"</string>
    <string name="policydesc_setGlobalProxy" >"设置在规范启用时要使用的设备全局代理。只有设备所有者才能设置全局代理。"</string>
    <string name="policylab_expirePassword" >"设置锁屏密码的有效期"</string>
    <string name="policydesc_expirePassword" >"调整系统强制用户更改锁屏密码、PIN 码或解锁图案的频率。"</string>
    <string name="policylab_encryptedStorage" >"设置存储设备加密"</string>
    <string name="policydesc_encryptedStorage" >"要求对存储的应用数据进行加密。"</string>
    <string name="policylab_disableCamera" >"停用相机"</string>
    <string name="policydesc_disableCamera" >"禁止使用所有设备摄像头。"</string>
    <string name="policylab_disableKeyguardFeatures" >"停用屏幕锁定的部分功能"</string>
    <string name="policydesc_disableKeyguardFeatures" >"禁止使用屏幕锁定的部分功能。"</string>
  <string-array name="phoneTypes">
    <item >"住宅"</item>
    <item >"手机"</item>
    <item >"单位"</item>
    <item >"单位传真"</item>
    <item >"住宅传真"</item>
    <item >"寻呼机"</item>
    <item >"其他"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="emailAddressTypes">
    <item >"个人"</item>
    <item >"工作"</item>
    <item >"其他"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="postalAddressTypes">
    <item >"住宅"</item>
    <item >"单位"</item>
    <item >"其他"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="imAddressTypes">
    <item >"住宅"</item>
    <item >"工作"</item>
    <item >"其他"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="organizationTypes">
    <item >"单位"</item>
    <item >"其他"</item>
    <item >"自定义"</item>
  </string-array>
  <string-array name="imProtocols">
    <item >"AIM"</item>
    <item >"Windows Live"</item>
    <item >"中国雅虎"</item>
    <item >"Skype"</item>
    <item >"QQ"</item>
    <item >"Google Talk"</item>
    <item >"ICQ"</item>
    <item >"Jabber"</item>
  </string-array>
    <string name="phoneTypeCustom" >"自定义"</string>
    <string name="phoneTypeHome" >"住宅"</string>
    <string name="phoneTypeMobile" >"手机"</string>
    <string name="phoneTypeWork" >"单位"</string>
    <string name="phoneTypeFaxWork" >"单位传真"</string>
    <string name="phoneTypeFaxHome" >"住宅传真"</string>
    <string name="phoneTypePager" >"寻呼机"</string>
    <string name="phoneTypeOther" >"其他"</string>
    <string name="phoneTypeCallback" >"回拨号码"</string>
    <string name="phoneTypeCar" >"车载电话"</string>
    <string name="phoneTypeCompanyMain" >"公司总机"</string>
    <string name="phoneTypeIsdn" >"ISDN"</string>
    <string name="phoneTypeMain" >"总机"</string>
    <string name="phoneTypeOtherFax" >"其他传真"</string>
    <string name="phoneTypeRadio" >"无线装置"</string>
    <string name="phoneTypeTelex" >"电报"</string>
    <string name="phoneTypeTtyTdd" >"TTY TDD"</string>
    <string name="phoneTypeWorkMobile" >"单位手机"</string>
    <string name="phoneTypeWorkPager" >"单位寻呼机"</string>
    <string name="phoneTypeAssistant" >"助理"</string>
    <string name="phoneTypeMms" >"彩信"</string>
    <string name="eventTypeCustom" >"自定义"</string>
    <string name="eventTypeBirthday" >"生日"</string>
    <string name="eventTypeAnniversary" >"周年纪念日"</string>
    <string name="eventTypeOther" >"其他"</string>
    <string name="emailTypeCustom" >"自定义"</string>
    <string name="emailTypeHome" >"个人"</string>
    <string name="emailTypeWork" >"工作"</string>
    <string name="emailTypeOther" >"其他"</string>
    <string name="emailTypeMobile" >"手机"</string>
    <string name="postalTypeCustom" >"自定义"</string>
    <string name="postalTypeHome" >"住宅"</string>
    <string name="postalTypeWork" >"单位"</string>
    <string name="postalTypeOther" >"其他"</string>
    <string name="imTypeCustom" >"自定义"</string>
    <string name="imTypeHome" >"住宅"</string>
    <string name="imTypeWork" >"工作"</string>
    <string name="imTypeOther" >"其他"</string>
    <string name="imProtocolCustom" >"自定义"</string>
    <string name="imProtocolAim" >"AIM"</string>
    <string name="imProtocolMsn" >"Windows Live"</string>
    <string name="imProtocolYahoo" >"雅虎"</string>
    <string name="imProtocolSkype" >"Skype"</string>
    <string name="imProtocolQq" >"QQ"</string>
    <string name="imProtocolGoogleTalk" >"环聊"</string>
    <string name="imProtocolIcq" >"ICQ"</string>
    <string name="imProtocolJabber" >"Jabber"</string>
    <string name="imProtocolNetMeeting" >"NetMeeting"</string>
    <string name="orgTypeWork" >"公司"</string>
    <string name="orgTypeOther" >"其他"</string>
    <string name="orgTypeCustom" >"自定义"</string>
    <string name="relationTypeCustom" >"自定义"</string>
    <string name="relationTypeAssistant" >"助理"</string>
    <string name="relationTypeBrother" >"兄弟"</string>
    <string name="relationTypeChild" >"子女"</string>
    <string name="relationTypeDomesticPartner" >"同居伴侣"</string>
    <string name="relationTypeFather" >"父亲"</string>
    <string name="relationTypeFriend" >"朋友"</string>
    <string name="relationTypeManager" >"经理"</string>
    <string name="relationTypeMother" >"母亲"</string>
    <string name="relationTypeParent" >"父母"</string>
    <string name="relationTypePartner" >"合作伙伴"</string>
    <string name="relationTypeReferredBy" >"介绍人"</string>
    <string name="relationTypeRelative" >"亲属"</string>
    <string name="relationTypeSister" >"姐妹"</string>
    <string name="relationTypeSpouse" >"配偶"</string>
    <string name="sipAddressTypeCustom" >"自定义"</string>
    <string name="sipAddressTypeHome" >"住宅"</string>
    <string name="sipAddressTypeWork" >"单位"</string>
    <string name="sipAddressTypeOther" >"其他"</string>
    <string name="quick_contacts_not_available" >"找不到可用来查看此联系人的应用。"</string>
    <string name="keyguard_password_enter_pin_code" >"输入PIN码"</string>
    <string name="keyguard_password_enter_puk_code" >"请输入PUK码和新的PIN码"</string>
    <string name="keyguard_password_enter_puk_prompt" >"PUK码"</string>
    <string name="keyguard_password_enter_pin_prompt" >"新的PIN码"</string>
    <string name="keyguard_password_entry_touch_hint" ><font size="17">"点按即可输入密码"</font></string>
    <string name="keyguard_password_enter_password_code" >"输入密码以解锁"</string>
    <string name="keyguard_password_enter_pin_password_code" >"输入PIN码进行解锁"</string>
    <string name="keyguard_password_wrong_pin_code" >"PIN码有误。"</string>
    <string name="keyguard_label_text" >"要解锁，请先按 MENU 再按 0。"</string>
    <string name="emergency_call_dialog_number_for_display" >"急救或报警电话"</string>
    <string name="lockscreen_carrier_default" >"没有服务"</string>
    <string name="lockscreen_screen_locked" >"屏幕已锁定。"</string>
    <string name="lockscreen_instructions_when_pattern_enabled" >"按 Menu 解锁或进行紧急呼救。"</string>
    <string name="lockscreen_instructions_when_pattern_disabled" >"按 MENU 解锁。"</string>
    <string name="lockscreen_pattern_instructions" >"绘制解锁图案"</string>
    <string name="lockscreen_emergency_call" >"紧急呼救"</string>
    <string name="lockscreen_return_to_call" >"返回通话"</string>
    <string name="lockscreen_pattern_correct" >"正确！"</string>
    <string name="lockscreen_pattern_wrong" >"重试"</string>
    <string name="lockscreen_password_wrong" >"重试"</string>
    <string name="lockscreen_storage_locked" >"解锁即可使用所有功能和数据"</string>
    <string name="faceunlock_multiple_failures" >"已超过“人脸解锁”尝试次数上限"</string>
    <string name="lockscreen_missing_sim_message_short" >"没有 SIM 卡"</string>
    <string name="lockscreen_missing_sim_message" product="tablet" >"平板电脑中没有SIM卡。"</string>
    <string name="lockscreen_missing_sim_message" product="tv" >"电视中没有 SIM 卡。"</string>
    <string name="lockscreen_missing_sim_message" product="default" >"手机中无SIM卡"</string>
    <string name="lockscreen_missing_sim_instructions" >"请插入SIM卡"</string>
    <string name="lockscreen_missing_sim_instructions_long" >"SIM卡缺失或无法读取。请插入SIM卡。"</string>
    <string name="lockscreen_permanent_disabled_sim_message_short" >"SIM卡无法使用。"</string>
    <string name="lockscreen_permanent_disabled_sim_instructions" >"您的SIM卡已永久停用。\n请与您的无线服务提供商联系，以便重新获取一张SIM卡。"</string>
    <string name="lockscreen_transport_prev_description" >"上一曲"</string>
    <string name="lockscreen_transport_next_description" >"下一曲"</string>
    <string name="lockscreen_transport_pause_description" >"暂停"</string>
    <string name="lockscreen_transport_play_description" >"播放"</string>
    <string name="lockscreen_transport_stop_description" >"停止"</string>
    <string name="lockscreen_transport_rew_description" >"快退"</string>
    <string name="lockscreen_transport_ffw_description" >"快进"</string>
    <string name="emergency_calls_only" >"只能拨打紧急呼救电话"</string>
    <string name="lockscreen_network_locked_message" >"网络已锁定"</string>
    <string name="lockscreen_sim_puk_locked_message" >"SIM 卡已用 PUK 码锁定。"</string>
    <string name="lockscreen_sim_puk_locked_instructions" >"请参阅《用户指南》或与客服人员联系。"</string>
    <string name="lockscreen_sim_locked_message" >"SIM 卡已被锁定。"</string>
    <string name="lockscreen_sim_unlock_progress_dialog_message" >"正在解锁SIM卡..."</string>
    <string name="lockscreen_too_many_failed_attempts_dialog_message" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_too_many_failed_password_attempts_dialog_message" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次输错密码。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_too_many_failed_pin_attempts_dialog_message" >"您已经<xliff:g id="NUMBER_0">%1$d</xliff:g>次输错了PIN码。\n\n请在<xliff:g id="NUMBER_1">%2$d</xliff:g>秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="tablet" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁平板电脑。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="tv" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁电视。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="default" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁手机。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="tablet" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁平板电脑。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，平板电脑将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="tv" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁电视。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，电视将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="default" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁手机。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，手机将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="tablet" >"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁平板电脑。平板电脑现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="tv" >"您已经 <xliff:g id="NUMBER">%d</xliff:g> 次错误地尝试解锁电视。电视现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="default" >"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁手机。手机现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_too_many_failed_attempts_countdown" >"<xliff:g id="NUMBER">%d</xliff:g>秒后重试。"</string>
    <string name="lockscreen_forgot_pattern_button_text" >"忘记了图案？"</string>
    <string name="lockscreen_glogin_forgot_pattern" >"帐号解锁"</string>
    <string name="lockscreen_glogin_too_many_attempts" >"图案尝试次数过多"</string>
    <string name="lockscreen_glogin_instructions" >"要解除锁定，请使用您的Google帐号登录。"</string>
    <string name="lockscreen_glogin_username_hint" >"用户名（电子邮件）"</string>
    <string name="lockscreen_glogin_password_hint" >"密码"</string>
    <string name="lockscreen_glogin_submit_button" >"登录"</string>
    <string name="lockscreen_glogin_invalid_input" >"用户名或密码无效。"</string>
    <string name="lockscreen_glogin_account_recovery_hint" >"忘记了用户名或密码？\n请访问"<b>"google.com/accounts/recovery"</b>"。"</string>
    <string name="lockscreen_glogin_checking_password" >"正在检查..."</string>
    <string name="lockscreen_unlock_label" >"解锁"</string>
    <string name="lockscreen_sound_on_label" >"打开声音"</string>
    <string name="lockscreen_sound_off_label" >"关闭声音"</string>
    <string name="lockscreen_access_pattern_start" >"开始绘制图案"</string>
    <string name="lockscreen_access_pattern_cleared" >"图案已清除"</string>
    <string name="lockscreen_access_pattern_cell_added" >"已添加单元格"</string>
    <string name="lockscreen_access_pattern_cell_added_verbose" >"已添加圆点 <xliff:g id="CELL_INDEX">%1$s</xliff:g>"</string>
    <string name="lockscreen_access_pattern_detected" >"图案绘制完成"</string>
    <string name="lockscreen_access_pattern_area" >"图案区域。"</string>
    <string name="keyguard_accessibility_widget_changed" >"%1$s。%3$d的微件%2$d。"</string>
    <string name="keyguard_accessibility_add_widget" >"添加微件。"</string>
    <string name="keyguard_accessibility_widget_empty_slot" >"空白"</string>
    <string name="keyguard_accessibility_unlock_area_expanded" >"已展开解锁区域。"</string>
    <string name="keyguard_accessibility_unlock_area_collapsed" >"已收起解锁区域。"</string>
    <string name="keyguard_accessibility_widget" >"<xliff:g id="WIDGET_INDEX">%1$s</xliff:g>微件。"</string>
    <string name="keyguard_accessibility_user_selector" >"用户选择器"</string>
    <string name="keyguard_accessibility_status" >"状态"</string>
    <string name="keyguard_accessibility_camera" >"相机"</string>
    <string name="keygaurd_accessibility_media_controls" >"媒体控制"</string>
    <string name="keyguard_accessibility_widget_reorder_start" >"已开始将微件重新排序。"</string>
    <string name="keyguard_accessibility_widget_reorder_end" >"已完成微件重新排序。"</string>
    <string name="keyguard_accessibility_widget_deleted" >"已删除微件<xliff:g id="WIDGET_INDEX">%1$s</xliff:g>。"</string>
    <string name="keyguard_accessibility_expand_lock_area" >"展开解锁区域。"</string>
    <string name="keyguard_accessibility_slide_unlock" >"滑动解锁。"</string>
    <string name="keyguard_accessibility_pattern_unlock" >"图案解锁。"</string>
    <string name="keyguard_accessibility_face_unlock" >"人脸解锁。"</string>
    <string name="keyguard_accessibility_pin_unlock" >"PIN码解锁。"</string>
    <string name="keyguard_accessibility_sim_pin_unlock" >"SIM 卡 PIN 码解锁。"</string>
    <string name="keyguard_accessibility_sim_puk_unlock" >"SIM 卡 PUK 码解锁。"</string>
    <string name="keyguard_accessibility_password_unlock" >"密码解锁。"</string>
    <string name="keyguard_accessibility_pattern_area" >"图案区域。"</string>
    <string name="keyguard_accessibility_slide_area" >"滑动区域。"</string>
    <string name="password_keyboard_label_symbol_key" >"?123"</string>
    <string name="password_keyboard_label_alpha_key" >"ABC"</string>
    <string name="password_keyboard_label_alt_key" >"ALT"</string>
    <string name="granularity_label_character" >"字符"</string>
    <string name="granularity_label_word" >"字"</string>
    <string name="granularity_label_link" >"链接"</string>
    <string name="granularity_label_line" >"行"</string>
    <string name="factorytest_failed" >"出厂测试失败"</string>
    <string name="factorytest_not_system" >"只有安装在/system/app中的软件包支持FACTORY_TEST操作。"</string>
    <string name="factorytest_no_action" >"找不到提供FACTORY_TEST操作的软件包。"</string>
    <string name="factorytest_reboot" >"重新启动"</string>
    <string name="js_dialog_title" >"网址为“<xliff:g id="TITLE">%s</xliff:g>”的网页显示："</string>
    <string name="js_dialog_title_default" >"JavaScript"</string>
    <string name="js_dialog_before_unload_title" >"确认离开"</string>
    <string name="js_dialog_before_unload_positive_button" >"离开此页"</string>
    <string name="js_dialog_before_unload_negative_button" >"留在此页"</string>
    <string name="js_dialog_before_unload" >"<xliff:g id="MESSAGE">%s</xliff:g>\n\n您确定要离开此页面吗？"</string>
    <string name="save_password_label" >"确认"</string>
    <string name="double_tap_toast" >"提示：点按两次可放大或缩小。"</string>
    <string name="autofill_this_form" >"自动填充"</string>
    <string name="setup_autofill" >"设置自动填充"</string>
    <string name="autofill_window_title" >"<xliff:g id="SERVICENAME">%1$s</xliff:g>的自动填充功能"</string>
    <string name="autofill_address_name_separator" >" "</string>
    <string name="autofill_address_summary_name_format" >"$1$2$3"</string>
    <string name="autofill_address_summary_separator" >"， "</string>
    <string name="autofill_address_summary_format" >"$1$2$3"</string>
    <string name="autofill_province" >"省/直辖市/自治区"</string>
    <string name="autofill_postal_code" >"邮编"</string>
    <string name="autofill_state" >"州"</string>
    <string name="autofill_zip_code" >"邮编"</string>
    <string name="autofill_county" >"郡"</string>
    <string name="autofill_island" >"岛"</string>
    <string name="autofill_district" >"地区"</string>
    <string name="autofill_department" >"省"</string>
    <string name="autofill_prefecture" >"县/府/都/道"</string>
    <string name="autofill_parish" >"行政区"</string>
    <string name="autofill_area" >"区域"</string>
    <string name="autofill_emirate" >"酋长国"</string>
    <string name="permlab_readHistoryBookmarks" >"读取您的网络书签和历史记录"</string>
    <string name="permdesc_readHistoryBookmarks" >"允许该应用读取浏览器访问过的所有网址记录以及浏览器的所有书签。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permlab_writeHistoryBookmarks" >"写入网络书签和历史记录"</string>
    <string name="permdesc_writeHistoryBookmarks" product="tablet" >"允许该应用修改您平板电脑上存储的浏览器历史记录或浏览器书签。此权限可让该应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permdesc_writeHistoryBookmarks" product="tv" >"允许应用修改您的电视上存储的浏览器历史记录或书签。此权限可让应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permdesc_writeHistoryBookmarks" product="default" >"允许该应用修改您手机上存储的浏览器历史记录或浏览器书签。此权限可让该应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permlab_setAlarm" >"设置闹钟"</string>
    <string name="permdesc_setAlarm" >"允许应用在已安装的闹钟应用中设置闹钟。有些闹钟应用可能无法实现此功能。"</string>
    <string name="permlab_addVoicemail" >"添加语音邮件"</string>
    <string name="permdesc_addVoicemail" >"允许应用在您的语音信箱中留言。"</string>
    <string name="permlab_writeGeolocationPermissions" >"修改“浏览器”地理位置的权限"</string>
    <string name="permdesc_writeGeolocationPermissions" >"允许应用修改“浏览器”的地理位置权限。恶意应用可能借此向任意网站发送位置信息。"</string>
    <string name="save_password_message" >"是否希望浏览器记住此密码？"</string>
    <string name="save_password_notnow" >"暂不保存"</string>
    <string name="save_password_remember" >"记住"</string>
    <string name="save_password_never" >"永不"</string>
    <string name="open_permission_deny" >"您无权打开此网页。"</string>
    <string name="text_copied" >"文本已复制到剪贴板。"</string>
    <string name="more_item_label" >"更多"</string>
    <string name="prepend_shortcut_label" >"MENU+"</string>
    <string name="menu_meta_shortcut_label" >"Meta+"</string>
    <string name="menu_ctrl_shortcut_label" >"Ctrl+"</string>
    <string name="menu_alt_shortcut_label" >"Alt+"</string>
    <string name="menu_shift_shortcut_label" >"Shift+"</string>
    <string name="menu_sym_shortcut_label" >"Sym+"</string>
    <string name="menu_function_shortcut_label" >"Fn+"</string>
    <string name="menu_space_shortcut_label" >"空格"</string>
    <string name="menu_enter_shortcut_label" >"Enter 键"</string>
    <string name="menu_delete_shortcut_label" >"删除"</string>
    <string name="search_go" >"搜索"</string>
    <string name="search_hint" >"搜索…"</string>
    <string name="searchview_description_search" >"搜索"</string>
    <string name="searchview_description_query" >"搜索查询"</string>
    <string name="searchview_description_clear" >"清除查询"</string>
    <string name="searchview_description_submit" >"提交查询"</string>
    <string name="searchview_description_voice" >"语音搜索"</string>
    <string name="enable_explore_by_touch_warning_title" >"是否启用“触摸浏览”？"</string>
    <string name="enable_explore_by_touch_warning_message" product="tablet" >"<xliff:g id="ACCESSIBILITY_SERVICE_NAME">%1$s</xliff:g>想要启用“触摸浏览”。“触摸浏览”启用后，您可以听到或看到所触摸内容的说明，还可以通过手势操作与手机互动。"</string>
    <string name="enable_explore_by_touch_warning_message" product="default" >"<xliff:g id="ACCESSIBILITY_SERVICE_NAME">%1$s</xliff:g>想要启用“触摸浏览”。“触摸浏览”启用后，您可以听到或看到所触摸内容的说明，还可以通过手势操作与手机互动。"</string>
    <string name="oneMonthDurationPast" >"1 个月前"</string>
    <string name="beforeOneMonthDurationPast" >"1 个月前"</string>
    <plurals name="last_num_days" formatted="false" >
      <item quantity="other">过去 <xliff:g id="COUNT_1">%d</xliff:g> 天</item>
      <item quantity="one">过去 <xliff:g id="COUNT_0">%d</xliff:g> 天</item>
    </plurals>
    <string name="last_month" >"上个月"</string>
    <string name="older" >"往前"</string>
    <string name="preposition_for_date" >"<xliff:g id="DATE">%s</xliff:g>"</string>
    <string name="preposition_for_time" >"<xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="preposition_for_year" >"年份：<xliff:g id="YEAR">%s</xliff:g>"</string>
    <string name="day" >"天"</string>
    <string name="days" >"天"</string>
    <string name="hour" >"点"</string>
    <string name="hours" >"小时"</string>
    <string name="minute" >"分钟"</string>
    <string name="minutes" >"分钟"</string>
    <string name="second" >"秒"</string>
    <string name="seconds" >"秒"</string>
    <string name="week" >"周"</string>
    <string name="weeks" >"周"</string>
    <string name="year" >"年"</string>
    <string name="years" >"年"</string>
    <string name="now_string_shortest" >"现在"</string>
    <plurals name="duration_minutes_shortest" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟</item>
    </plurals>
    <plurals name="duration_hours_shortest" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时</item>
    </plurals>
    <plurals name="duration_days_shortest" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天</item>
    </plurals>
    <plurals name="duration_years_shortest" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年</item>
    </plurals>
    <plurals name="duration_minutes_shortest_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟后</item>
    </plurals>
    <plurals name="duration_hours_shortest_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时后</item>
    </plurals>
    <plurals name="duration_days_shortest_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天后</item>
    </plurals>
    <plurals name="duration_years_shortest_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年后</item>
    </plurals>
    <plurals name="duration_minutes_relative" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟前</item>
    </plurals>
    <plurals name="duration_hours_relative" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时前</item>
    </plurals>
    <plurals name="duration_days_relative" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天前</item>
    </plurals>
    <plurals name="duration_years_relative" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年前</item>
    </plurals>
    <plurals name="duration_minutes_relative_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟后</item>
    </plurals>
    <plurals name="duration_hours_relative_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时后</item>
    </plurals>
    <plurals name="duration_days_relative_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天后</item>
    </plurals>
    <plurals name="duration_years_relative_future" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年后</item>
    </plurals>
    <string name="VideoView_error_title" >"视频问题"</string>
    <string name="VideoView_error_text_invalid_progressive_playback" >"抱歉，该视频不适合在此设备上播放。"</string>
    <string name="VideoView_error_text_unknown" >"无法播放此视频。"</string>
    <string name="VideoView_error_button" >"确定"</string>
    <string name="relative_time" >"<xliff:g id="DATE">%1$s</xliff:g>，<xliff:g id="TIME">%2$s</xliff:g>"</string>
    <string name="noon" >"中午"</string>
    <string name="Noon" >"中午"</string>
    <string name="midnight" >"午夜"</string>
    <string name="Midnight" >"午夜"</string>
    <string name="elapsed_time_short_format_mm_ss" >"<xliff:g id="MINUTES">%1$02d</xliff:g>:<xliff:g id="SECONDS">%2$02d</xliff:g>"</string>
    <string name="elapsed_time_short_format_h_mm_ss" >"<xliff:g id="HOURS">%1$d</xliff:g>:<xliff:g id="MINUTES">%2$02d</xliff:g>:<xliff:g id="SECONDS">%3$02d</xliff:g>"</string>
    <string name="selectAll" >"全选"</string>
    <string name="cut" >"剪切"</string>
    <string name="copy" >"复制"</string>
    <string name="failed_to_copy_to_clipboard" >"无法复制到剪贴板"</string>
    <string name="paste" >"粘贴"</string>
    <string name="paste_as_plain_text" >"以纯文本形式粘贴"</string>
    <string name="replace" >"替换..."</string>
    <string name="delete" >"删除"</string>
    <string name="copyUrl" >"复制网址"</string>
    <string name="selectTextMode" >"选择文字"</string>
    <string name="undo" >"撤消"</string>
    <string name="redo" >"重做"</string>
    <string name="autofill" >"自动填充"</string>
    <string name="textSelectionCABTitle" >"文字选择"</string>
    <string name="addToDictionary" >"添加到字典"</string>
    <string name="deleteText" >"删除"</string>
    <string name="inputMethod" >"输入法"</string>
    <string name="editTextMenuTitle" >"文字操作"</string>
    <string name="email" >"电子邮件"</string>
    <string name="email_desc" >"将电子邮件发送至所选地址"</string>
    <string name="dial" >"拨打电话"</string>
    <string name="dial_desc" >"拨打所选电话号码"</string>
    <string name="map" >"地图"</string>
    <string name="map_desc" >"找到所选地址"</string>
    <string name="browse" >"打开"</string>
    <string name="browse_desc" >"打开所选网址"</string>
    <string name="sms" >"发短信"</string>
    <string name="sms_desc" >"将短信发送至所选电话号码"</string>
    <string name="add_contact" >"添加"</string>
    <string name="add_contact_desc" >"添加到通讯录"</string>
    <string name="view_calendar" >"查看"</string>
    <string name="view_calendar_desc" >"在日历中查看所选时间"</string>
    <string name="add_calendar_event" >"排定时间"</string>
    <string name="add_calendar_event_desc" >"将活动安排在所选时间"</string>
    <string name="view_flight" >"跟踪"</string>
    <string name="view_flight_desc" >"跟踪所选航班"</string>
    <string name="low_internal_storage_view_title" >"存储空间不足"</string>
    <string name="low_internal_storage_view_text" >"某些系统功能可能无法正常使用"</string>
    <string name="low_internal_storage_view_text_no_boot" >"系统存储空间不足。请确保您有250MB的可用空间，然后重新启动。"</string>
    <string name="app_running_notification_title" >"“<xliff:g id="APP_NAME">%1$s</xliff:g>”正在运行"</string>
    <string name="app_running_notification_text" >"点按即可了解详情或停止应用。"</string>
    <string name="ok" >"确定"</string>
    <string name="cancel" >"取消"</string>
    <string name="yes" >"确定"</string>
    <string name="no" >"取消"</string>
    <string name="dialog_alert_title" >"注意"</string>
    <string name="loading" >"正在加载..."</string>
    <string name="capital_on" >"开启"</string>
    <string name="capital_off" >"关闭"</string>
    <string name="whichApplication" >"选择要使用的应用："</string>
    <string name="whichApplicationNamed" >"使用%1$s完成操作"</string>
    <string name="whichApplicationLabel" >"完成操作"</string>
    <string name="whichViewApplication" >"打开方式"</string>
    <string name="whichViewApplicationNamed" >"使用%1$s打开"</string>
    <string name="whichViewApplicationLabel" >"打开"</string>
    <string name="whichEditApplication" >"编辑方式"</string>
    <string name="whichEditApplicationNamed" >"使用%1$s编辑"</string>
    <string name="whichEditApplicationLabel" >"编辑"</string>
    <string name="whichSendApplication" >"分享方式"</string>
    <string name="whichSendApplicationNamed" >"使用%1$s分享"</string>
    <string name="whichSendApplicationLabel" >"分享"</string>
    <string name="whichSendToApplication" >"通过以下应用发送"</string>
    <string name="whichSendToApplicationNamed" >"通过%1$s发送"</string>
    <string name="whichSendToApplicationLabel" >"发送"</string>
    <string name="whichHomeApplication" >"选择主屏幕应用"</string>
    <string name="whichHomeApplicationNamed" >"将“%1$s”设为主屏幕应用"</string>
    <string name="whichHomeApplicationLabel" >"截图"</string>
    <string name="whichImageCaptureApplication" >"使用以下应用截图"</string>
    <string name="whichImageCaptureApplicationNamed" >"使用%1$s截图"</string>
    <string name="whichImageCaptureApplicationLabel" >"截图"</string>
    <string name="alwaysUse" >"设为默认选项。"</string>
    <string name="use_a_different_app" >"使用其他应用"</string>
    <string name="clearDefaultHintMsg" >"在“系统设置”&gt;“应用”&gt;“已下载”中清除默认设置。"</string>
    <string name="chooseActivity" >"选择操作"</string>
    <string name="chooseUsbActivity" >"为USB设备选择一个应用"</string>
    <string name="noApplications" >"没有应用可执行此操作。"</string>
    <string name="aerr_application" >"<xliff:g id="APPLICATION">%1$s</xliff:g>已停止运行"</string>
    <string name="aerr_process" >"<xliff:g id="PROCESS">%1$s</xliff:g>已停止运行"</string>
    <string name="aerr_application_repeated" >"<xliff:g id="APPLICATION">%1$s</xliff:g>屡次停止运行"</string>
    <string name="aerr_process_repeated" >"<xliff:g id="PROCESS">%1$s</xliff:g>屡次停止运行"</string>
    <string name="aerr_restart" >"重新打开应用"</string>
    <string name="aerr_report" >"发送反馈"</string>
    <string name="aerr_close" >"关闭"</string>
    <string name="aerr_mute" >"忽略（直到设备重启）"</string>
    <string name="aerr_wait" >"等待"</string>
    <string name="aerr_close_app" >"关闭应用"</string>
    <string name="anr_title" ></string>
    <string name="anr_activity_application" >"<xliff:g id="APPLICATION">%2$s</xliff:g>没有响应"</string>
    <string name="anr_activity_process" >"<xliff:g id="ACTIVITY">%1$s</xliff:g>没有响应"</string>
    <string name="anr_application_process" >"<xliff:g id="APPLICATION">%1$s</xliff:g>没有响应"</string>
    <string name="anr_process" >"进程“<xliff:g id="PROCESS">%1$s</xliff:g>”没有响应"</string>
    <string name="force_close" >"确定"</string>
    <string name="report" >"报告"</string>
    <string name="wait" >"等待"</string>
    <string name="webpage_unresponsive" >"该网页无响应。\n\n要将其关闭吗？"</string>
    <string name="launch_warning_title" >"应用已重定向"</string>
    <string name="launch_warning_replace" >"<xliff:g id="APP_NAME">%1$s</xliff:g>目前正在运行。"</string>
    <string name="launch_warning_original" >"<xliff:g id="APP_NAME">%1$s</xliff:g>已启动。"</string>
    <string name="screen_compat_mode_scale" >"缩放"</string>
    <string name="screen_compat_mode_show" >"始终显示"</string>
    <string name="screen_compat_mode_hint" >"在“系统设置”&gt;“应用”&gt;“已下载”中重新启用此模式。"</string>
    <string name="unsupported_display_size_message" >"<xliff:g id="APP_NAME">%1$s</xliff:g>不支持当前的显示大小设置，因此可能无法正常显示。"</string>
    <string name="unsupported_display_size_show" >"一律显示"</string>
    <string name="unsupported_compile_sdk_message" >"<xliff:g id="APP_NAME">%1$s</xliff:g>是专为不兼容的 Android 操作系统版本所打造的应用，因此可能会出现意外行为。您可以使用该应用的更新版本。"</string>
    <string name="unsupported_compile_sdk_show" >"一律显示"</string>
    <string name="unsupported_compile_sdk_check_update" >"检查更新"</string>
    <string name="smv_application" >"“<xliff:g id="APPLICATION">%1$s</xliff:g>”应用（<xliff:g id="PROCESS">%2$s</xliff:g> 进程）违反了自我强制执行的严格模式 (StrictMode) 政策。"</string>
    <string name="smv_process" >"进程 <xliff:g id="PROCESS">%1$s</xliff:g> 违反了自我强制执行的严格模式 (StrictMode) 政策。"</string>
    <string name="android_upgrading_title" product="default" >"手机正在更新…"</string>
    <string name="android_upgrading_title" product="tablet" >"平板电脑正在更新…"</string>
    <string name="android_upgrading_title" product="device" >"设备正在更新…"</string>
    <string name="android_start_title" product="default" >"手机正在启动…"</string>
    <string name="android_start_title" product="tablet" >"平板电脑正在启动…"</string>
    <string name="android_start_title" product="device" >"设备正在启动…"</string>
    <string name="android_upgrading_fstrim" >"正在优化存储空间。"</string>
    <string name="android_upgrading_notification_title" product="default" >"正在完成系统更新…"</string>
    <string name="app_upgrading_toast" >"正在升级<xliff:g id="APPLICATION">%1$s</xliff:g>…"</string>
    <string name="android_upgrading_apk" >"正在优化第<xliff:g id="NUMBER_0">%1$d</xliff:g>个应用（共<xliff:g id="NUMBER_1">%2$d</xliff:g>个）。"</string>
    <string name="android_preparing_apk" >"正在准备升级<xliff:g id="APPNAME">%1$s</xliff:g>。"</string>
    <string name="android_upgrading_starting_apps" >"正在启动应用。"</string>
    <string name="android_upgrading_complete" >"即将完成启动。"</string>
    <string name="heavy_weight_notification" >"<xliff:g id="APP">%1$s</xliff:g>正在运行"</string>
    <string name="heavy_weight_notification_detail" >"点按即可返回游戏"</string>
    <string name="heavy_weight_switcher_title" >"选择游戏"</string>
    <string name="heavy_weight_switcher_text" >"为了提升性能，一次只能打开其中一个游戏。"</string>
    <string name="old_app_action" >"返回<xliff:g id="OLD_APP">%1$s</xliff:g>"</string>
    <string name="new_app_action" >"打开<xliff:g id="NEW_APP">%1$s</xliff:g>"</string>
    <string name="new_app_description" >"<xliff:g id="OLD_APP">%1$s</xliff:g>将会在不保存的情况下关闭"</string>
    <string name="dump_heap_notification" >"<xliff:g id="PROC">%1$s</xliff:g>占用的内存已超出限制"</string>
    <string name="dump_heap_notification_detail" >"已收集堆转储数据。点按即可分享。"</string>
    <string name="dump_heap_title" >"要共享堆转储数据吗？"</string>
    <string name="dump_heap_text" >"<xliff:g id="PROC">%1$s</xliff:g>进程占用的内存已超出限制 (<xliff:g id="SIZE">%2$s</xliff:g>)。您可以将收集的堆转储数据共享给相应的开发者。请注意：此数据中可能包含该应用有权存取的您的个人信息。"</string>
    <string name="sendText" >"选择要对文字执行的操作"</string>
    <string name="volume_ringtone" >"铃声音量"</string>
    <string name="volume_music" >"媒体音量"</string>
    <string name="volume_music_hint_playing_through_bluetooth" >"正通过蓝牙播放"</string>
    <string name="volume_music_hint_silent_ringtone_selected" >"已设置静音铃声"</string>
    <string name="volume_call" >"通话音量"</string>
    <string name="volume_bluetooth_call" >"使用蓝牙时的通话音量"</string>
    <string name="volume_alarm" >"闹钟音量"</string>
    <string name="volume_notification" >"通知音量"</string>
    <string name="volume_unknown" >"音量"</string>
    <string name="volume_icon_description_bluetooth" >"蓝牙音量"</string>
    <string name="volume_icon_description_ringer" >"铃声音量"</string>
    <string name="volume_icon_description_incall" >"通话音量"</string>
    <string name="volume_icon_description_media" >"媒体音量"</string>
    <string name="volume_icon_description_notification" >"通知音量"</string>
    <string name="ringtone_default" >"默认铃声"</string>
    <string name="ringtone_default_with_actual" >"默认铃声（<xliff:g id="ACTUAL_RINGTONE">%1$s</xliff:g>）"</string>
    <string name="ringtone_silent" >"无"</string>
    <string name="ringtone_picker_title" >"铃声"</string>
    <string name="ringtone_picker_title_alarm" >"闹钟提示音"</string>
    <string name="ringtone_picker_title_notification" >"通知提示音"</string>
    <string name="ringtone_unknown" >"未知"</string>
    <plurals name="wifi_available" formatted="false" >
      <item quantity="other">有可用的 WLAN 网络</item>
      <item quantity="one">有可用的 WLAN 网络</item>
    </plurals>
    <plurals name="wifi_available_detailed" formatted="false" >
      <item quantity="other">有可用的开放 WLAN 网络</item>
      <item quantity="one">有可用的开放 WLAN 网络</item>
    </plurals>
    <string name="wifi_available_title" >"连接到开放的 WLAN 网络"</string>
    <string name="wifi_available_carrier_network_title" >"连接到运营商 WLAN 网络"</string>
    <string name="wifi_available_title_connecting" >"正在连接到 WLAN 网络"</string>
    <string name="wifi_available_title_connected" >"已连接到 WLAN 网络"</string>
    <string name="wifi_available_title_failed_to_connect" >"无法连接到 WLAN 网络"</string>
    <string name="wifi_available_content_failed_to_connect" >"点按即可查看所有网络"</string>
    <string name="wifi_available_action_connect" >"连接"</string>
    <string name="wifi_available_action_all_networks" >"所有网络"</string>
    <string name="wifi_wakeup_onboarding_title" >"WLAN 将自动开启"</string>
    <string name="wifi_wakeup_onboarding_subtext" >"当您位于已保存的高品质网络信号范围内时"</string>
    <string name="wifi_wakeup_onboarding_action_disable" >"不要重新开启"</string>
    <string name="wifi_wakeup_enabled_title" >"已自动开启 WLAN 网络"</string>
    <string name="wifi_wakeup_enabled_content" >"您位于已保存的网络 (<xliff:g id="NETWORK_SSID">%1$s</xliff:g>) 信号范围内"</string>
    <string name="wifi_available_sign_in" >"登录到WLAN网络"</string>
    <string name="network_available_sign_in" >"登录到网络"</string>
    <!-- no translation found for network_available_sign_in_detailed (8000081941447976118) -->
    <skip />
    <string name="wifi_no_internet" >"此 WLAN 网络无法访问互联网"</string>
    <string name="wifi_no_internet_detailed" >"点按即可查看相关选项"</string>
    <string name="network_switch_metered" >"已切换至<xliff:g id="NETWORK_TYPE">%1$s</xliff:g>"</string>
    <string name="network_switch_metered_detail" >"设备会在<xliff:g id="PREVIOUS_NETWORK">%2$s</xliff:g>无法访问互联网时使用<xliff:g id="NEW_NETWORK">%1$s</xliff:g>（可能需要支付相应的费用）。"</string>
    <string name="network_switch_metered_toast" >"已从<xliff:g id="PREVIOUS_NETWORK">%1$s</xliff:g>切换至<xliff:g id="NEW_NETWORK">%2$s</xliff:g>"</string>
  <string-array name="network_switch_type_name">
    <item >"移动数据"</item>
    <item >"WLAN"</item>
    <item >"蓝牙"</item>
    <item >"以太网"</item>
    <item >"VPN"</item>
  </string-array>
    <string name="network_switch_type_name_unknown" >"未知网络类型"</string>
    <string name="wifi_watchdog_network_disabled" >"无法连接到WLAN"</string>
    <string name="wifi_watchdog_network_disabled_detailed" >" 互联网连接状况不佳。"</string>
    <string name="wifi_connect_alert_title" >"要允许连接吗？"</string>
    <string name="wifi_connect_alert_message" >"“%1$s”应用想要连接到 WLAN 网络“%2$s”"</string>
    <string name="wifi_connect_default_application" >"一款应用"</string>
    <string name="wifi_p2p_dialog_title" >"WLAN 直连"</string>
    <string name="wifi_p2p_turnon_message" >"启动WLAN直连。此操作将会关闭WLAN客户端/热点。"</string>
    <string name="wifi_p2p_failed_message" >"无法启动WLAN直连。"</string>
    <string name="wifi_p2p_enabled_notification_title" >"已启用WLAN直连"</string>
    <string name="wifi_p2p_enabled_notification_message" >"点按即可查看相关设置"</string>
    <string name="accept" >"接受"</string>
    <string name="decline" >"拒绝"</string>
    <string name="wifi_p2p_invitation_sent_title" >"已发出邀请"</string>
    <string name="wifi_p2p_invitation_to_connect_title" >"连接邀请"</string>
    <string name="wifi_p2p_from_message" >"发件人："</string>
    <string name="wifi_p2p_to_message" >"收件人："</string>
    <string name="wifi_p2p_enter_pin_message" >"输入所需的PIN码："</string>
    <string name="wifi_p2p_show_pin_message" >"PIN 码："</string>
    <string name="wifi_p2p_frequency_conflict_message" product="tablet" >"平板电脑连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”时会暂时断开与WLAN的连接"</string>
    <string name="wifi_p2p_frequency_conflict_message" product="tv" >"电视连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>时会暂时断开与 WLAN 的连接"</string>
    <string name="wifi_p2p_frequency_conflict_message" product="default" >"手机连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>时会暂时断开与WLAN的连接。"</string>
    <string name="select_character" >"插入字符"</string>
    <string name="sms_control_title" >"正在发送短信"</string>
    <string name="sms_control_message" >"&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;在发送大量短信。是否允许该应用继续发送短信？"</string>
    <string name="sms_control_yes" >"允许"</string>
    <string name="sms_control_no" >"拒绝"</string>
    <string name="sms_short_code_confirm_message" >"&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;想要向 &lt;b&gt;<xliff:g id="DEST_ADDRESS">%2$s</xliff:g>&lt;/b&gt; 发送一条短信。"</string>
    <string name="sms_short_code_details" ><b>"这可能会导致您的手机号产生费用。"</b></string>
    <string name="sms_premium_short_code_details" ><b>"这会导致您的手机号产生费用。"</b></string>
    <string name="sms_short_code_confirm_allow" >"发送"</string>
    <string name="sms_short_code_confirm_deny" >"取消"</string>
    <string name="sms_short_code_remember_choice" >"记住我的选择"</string>
    <string name="sms_short_code_remember_undo_instruction" >"之后，您可以在“设置”&gt;“应用”中更改此设置"</string>
    <string name="sms_short_code_confirm_always_allow" >"始终允许"</string>
    <string name="sms_short_code_confirm_never_allow" >"永不允许"</string>
    <string name="sim_removed_title" >"已移除SIM卡"</string>
    <string name="sim_removed_message" >"移动网络不可用。请插入有效的SIM卡并重新启动。"</string>
    <string name="sim_done_button" >"完成"</string>
    <string name="sim_added_title" >"已添加SIM卡"</string>
    <string name="sim_added_message" >"请重新启动您的设备，以便访问移动网络。"</string>
    <string name="sim_restart_button" >"重新启动"</string>
    <string name="install_carrier_app_notification_title" >"激活移动网络服务"</string>
    <string name="install_carrier_app_notification_text" >"下载运营商应用即可激活您的新 SIM 卡"</string>
    <string name="install_carrier_app_notification_text_app_name" >"下载<xliff:g id="APP_NAME">%1$s</xliff:g>应用即可激活您的新 SIM 卡"</string>
    <string name="install_carrier_app_notification_button" >"下载应用"</string>
    <string name="carrier_app_notification_title" >"已插入新 SIM 卡"</string>
    <string name="carrier_app_notification_text" >"点按即可进行设置"</string>
    <string name="time_picker_dialog_title" >"设置时间"</string>
    <string name="date_picker_dialog_title" >"设置日期"</string>
    <string name="date_time_set" >"设置"</string>
    <string name="date_time_done" >"完成"</string>
    <string name="perms_new_perm_prefix" ><font size="12" fgcolor="#ff33b5e5">"新增："</font></string>
    <string name="perms_description_app" >"由“<xliff:g id="APP_NAME">%1$s</xliff:g>”提供。"</string>
    <string name="no_permissions" >"不需要任何权限"</string>
    <string name="perm_costs_money" >"这可能会产生费用"</string>
    <string name="dlg_ok" >"确定"</string>
    <string name="usb_charging_notification_title" >"正在通过 USB 为此设备充电"</string>
    <string name="usb_supplying_notification_title" >"正在通过 USB 为连接的设备充电"</string>
    <string name="usb_mtp_notification_title" >"已开启 USB 文件传输模式"</string>
    <string name="usb_ptp_notification_title" >"已开启 USB PTP 模式"</string>
    <string name="usb_tether_notification_title" >"已开启 USB 网络共享模式"</string>
    <string name="usb_midi_notification_title" >"已开启 USB MIDI 模式"</string>
    <string name="usb_accessory_notification_title" >"USB 配件已连接"</string>
    <string name="usb_notification_message" >"点按即可查看更多选项。"</string>
    <string name="usb_power_notification_message" >"正在为连接的设备充电。点按即可查看更多选项。"</string>
    <string name="usb_unsupported_audio_accessory_title" >"检测到模拟音频配件"</string>
    <string name="usb_unsupported_audio_accessory_message" >"连接的设备与此手机不兼容。点按即可了解详情。"</string>
    <string name="adb_active_notification_title" >"已连接到 USB 调试"</string>
    <string name="adb_active_notification_message" >"点按即可关闭 USB 调试"</string>
    <string name="adb_active_notification_message" product="tv" >"选择即可停用 USB 调试功能。"</string>
    <string name="taking_remote_bugreport_notification_title" >"正在生成错误报告…"</string>
    <string name="share_remote_bugreport_notification_title" >"要分享错误报告吗？"</string>
    <string name="sharing_remote_bugreport_notification_title" >"正在分享错误报告…"</string>
    <string name="share_remote_bugreport_notification_message_finished" >"您的管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据。"</string>
    <string name="share_remote_bugreport_action" >"分享"</string>
    <string name="decline_remote_bugreport_action" >"拒绝"</string>
    <string name="select_input_method" >"更改键盘"</string>
    <string name="show_ime" >"开启后，连接到实体键盘时，它会一直显示在屏幕上"</string>
    <string name="hardware" >"显示虚拟键盘"</string>
    <string name="select_keyboard_layout_notification_title" >"配置实体键盘"</string>
    <string name="select_keyboard_layout_notification_message" >"点按即可选择语言和布局"</string>
    <string name="fast_scroll_alphabet" >" ABCDEFGHIJKLMNOPQRSTUVWXYZ"</string>
    <string name="fast_scroll_numeric_alphabet" >" 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"</string>
    <string name="alert_windows_notification_channel_group_name" >"显示在其他应用的上层"</string>
    <string name="alert_windows_notification_channel_name" >"“<xliff:g id="NAME">%s</xliff:g>”正在其他应用的上层显示内容"</string>
    <string name="alert_windows_notification_title" >"“<xliff:g id="NAME">%s</xliff:g>”正在其他应用的上层显示内容"</string>
    <string name="alert_windows_notification_message" >"如果您不想让“<xliff:g id="NAME">%s</xliff:g>”使用此功能，请点按以打开设置，然后关闭此功能。"</string>
    <string name="alert_windows_notification_turn_off_action" >"关闭"</string>
    <string name="ext_media_checking_notification_title" >"正在检查<xliff:g id="NAME">%s</xliff:g>…"</string>
    <string name="ext_media_checking_notification_message" >"正在检查当前内容"</string>
    <string name="ext_media_new_notification_title" >"新的<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_new_notification_message" >"点按即可进行设置"</string>
    <string name="ext_media_ready_notification_message" >"可用于传输照片和媒体文件"</string>
    <string name="ext_media_unmountable_notification_title" >"<xliff:g id="NAME">%s</xliff:g>出现问题"</string>
    <string name="ext_media_unmountable_notification_message" >"点按即可修正问题"</string>
    <string name="ext_media_unmountable_notification_message" product="tv" >"<xliff:g id="NAME">%s</xliff:g>已损坏。选择即可进行修正。"</string>
    <string name="ext_media_unsupported_notification_title" >"<xliff:g id="NAME">%s</xliff:g>不受支持"</string>
    <string name="ext_media_unsupported_notification_message" >"该设备不支持此<xliff:g id="NAME">%s</xliff:g>。点按即可使用支持的格式进行设置。"</string>
    <string name="ext_media_unsupported_notification_message" product="tv" >"此设备不支持该<xliff:g id="NAME">%s</xliff:g>。选择即可使用支持的格式进行设置。"</string>
    <string name="ext_media_badremoval_notification_title" >"<xliff:g id="NAME">%s</xliff:g>已意外移除"</string>
    <string name="ext_media_badremoval_notification_message" >"请先弹出媒体，再将其移除，以防内容丢失"</string>
    <string name="ext_media_nomedia_notification_title" >"<xliff:g id="NAME">%s</xliff:g>已被移除"</string>
    <string name="ext_media_nomedia_notification_message" >"部分功能可能无法正常运行。请插入新的存储设备。"</string>
    <string name="ext_media_unmounting_notification_title" >"正在弹出<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_unmounting_notification_message" >"请勿移除"</string>
    <string name="ext_media_init_action" >"设置"</string>
    <string name="ext_media_unmount_action" >"弹出"</string>
    <string name="ext_media_browse_action" >"浏览"</string>
    <string name="ext_media_missing_title" >"缺少<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_missing_message" >"请再次插入设备"</string>
    <string name="ext_media_move_specific_title" >"正在移动<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_move_title" >"正在移动数据"</string>
    <string name="ext_media_move_success_title" >"内容转移操作已完成"</string>
    <string name="ext_media_move_success_message" >"已将内容移至<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_move_failure_title" >"无法移动内容"</string>
    <string name="ext_media_move_failure_message" >"请再次尝试移动内容"</string>
    <string name="ext_media_status_removed" >"已移除"</string>
    <string name="ext_media_status_unmounted" >"已弹出"</string>
    <string name="ext_media_status_checking" >"正在检查…"</string>
    <string name="ext_media_status_mounted" >"就绪"</string>
    <string name="ext_media_status_mounted_ro" >"只读"</string>
    <string name="ext_media_status_bad_removal" >"未安全移除"</string>
    <string name="ext_media_status_unmountable" >"已损坏"</string>
    <string name="ext_media_status_unsupported" >"不支持"</string>
    <string name="ext_media_status_ejecting" >"正在弹出…"</string>
    <string name="ext_media_status_formatting" >"正在格式化…"</string>
    <string name="ext_media_status_missing" >"未插入"</string>
    <string name="activity_list_empty" >"未找到匹配的活动。"</string>
    <string name="permlab_route_media_output" >"更改媒体输出线路"</string>
    <string name="permdesc_route_media_output" >"允许该应用将媒体输出线路更改到其他外部设备。"</string>
    <string name="permlab_readInstallSessions" >"读取安装会话"</string>
    <string name="permdesc_readInstallSessions" >"允许应用读取安装会话。这样，应用将可以查看有关当前软件包安装的详情。"</string>
    <string name="permlab_requestInstallPackages" >"请求安装文件包"</string>
    <string name="permdesc_requestInstallPackages" >"允许应用请求安装文件包。"</string>
    <string name="permlab_requestDeletePackages" >"请求删除文件包"</string>
    <string name="permdesc_requestDeletePackages" >"允许应用请求删除文件包。"</string>
    <string name="permlab_requestIgnoreBatteryOptimizations" >"请求忽略电池优化"</string>
    <string name="permdesc_requestIgnoreBatteryOptimizations" >"允许应用请求相应的权限，以便忽略针对该应用的电池优化。"</string>
    <string name="tutorial_double_tap_to_zoom_message_short" >"双击可以进行缩放控制"</string>
    <string name="gadget_host_error_inflating" >"无法添加微件。"</string>
    <string name="ime_action_go" >"开始"</string>
    <string name="ime_action_search" >"搜索"</string>
    <string name="ime_action_send" >"发送"</string>
    <string name="ime_action_next" >"下一步"</string>
    <string name="ime_action_done" >"完成"</string>
    <string name="ime_action_previous" >"上一页"</string>
    <string name="ime_action_default" >"执行"</string>
    <string name="dial_number_using" >"拨打电话\n<xliff:g id="NUMBER">%s</xliff:g>"</string>
    <string name="create_contact_using" >"创建电话号码为\n<xliff:g id="NUMBER">%s</xliff:g> 的联系人"</string>
    <string name="grant_credentials_permission_message_header" >"以下一个或多个应用请求获得相应权限，以便在当前和以后访问您的帐号。"</string>
    <string name="grant_credentials_permission_message_footer" >"您是否同意此请求？"</string>
    <string name="grant_permissions_header_text" >"访问权限请求"</string>
    <string name="allow" >"允许"</string>
    <string name="deny" >"拒绝"</string>
    <string name="permission_request_notification_title" >"权限请求"</string>
    <string name="permission_request_notification_with_subtitle" >"应用对帐号 <xliff:g id="ACCOUNT">%s</xliff:g>\n 提出权限请求。"</string>
    <string name="forward_intent_to_owner" >"您目前是在工作资料之外使用此应用"</string>
    <string name="forward_intent_to_work" >"您目前是在工作资料内使用此应用"</string>
    <string name="input_method_binding_label" >"输入法"</string>
    <string name="sync_binding_label" >"同步"</string>
    <string name="accessibility_binding_label" >"无障碍"</string>
    <string name="wallpaper_binding_label" >"壁纸"</string>
    <string name="chooser_wallpaper" >"更改壁纸"</string>
    <string name="notification_listener_binding_label" >"通知侦听器"</string>
    <string name="vr_listener_binding_label" >"VR 监听器"</string>
    <string name="condition_provider_service_binding_label" >"条件提供程序"</string>
    <string name="notification_ranker_binding_label" >"通知重要程度排序服务"</string>
    <string name="vpn_title" >"已激活VPN"</string>
    <string name="vpn_title_long" >"<xliff:g id="APP">%s</xliff:g>已激活VPN"</string>
    <string name="vpn_text" >"点按即可管理网络。"</string>
    <string name="vpn_text_long" >"已连接到<xliff:g id="SESSION">%s</xliff:g>。点按即可管理网络。"</string>
    <string name="vpn_lockdown_connecting" >"正在连接到始终开启的 VPN…"</string>
    <string name="vpn_lockdown_connected" >"已连接到始终开启的 VPN"</string>
    <string name="vpn_lockdown_disconnected" >"始终开启的 VPN 已断开连接"</string>
    <string name="vpn_lockdown_error" >"无法连接到始终开启的 VPN"</string>
    <string name="vpn_lockdown_config" >"更改网络或 VPN 设置"</string>
    <string name="upload_file" >"选择文件"</string>
    <string name="no_file_chosen" >"未选定任何文件"</string>
    <string name="reset" >"重置"</string>
    <string name="submit" >"提交"</string>
    <string name="car_mode_disable_notification_title" >"驾驶应用正在运行"</string>
    <string name="car_mode_disable_notification_message" >"点按即可退出驾驶应用。"</string>
    <string name="tethered_notification_title" >"网络共享或热点已启用"</string>
    <string name="tethered_notification_message" >"点按即可进行设置。"</string>
    <string name="disable_tether_notification_title" >"网络共享已停用"</string>
    <string name="disable_tether_notification_message" >"请与您的管理员联系以了解详情"</string>
    <string name="back_button_label" >"上一步"</string>
    <string name="next_button_label" >"下一步"</string>
    <string name="skip_button_label" >"跳过"</string>
    <string name="no_matches" >"无匹配项"</string>
    <string name="find_on_page" >"在网页上查找"</string>
    <plurals name="matches_found" formatted="false" >
      <item quantity="other">第 <xliff:g id="INDEX">%d</xliff:g> 条结果（共 <xliff:g id="TOTAL">%d</xliff:g> 条）</item>
      <item quantity="one">1 条结果</item>
    </plurals>
    <string name="action_mode_done" >"完成"</string>
    <string name="progress_erasing" product="nosdcard" >"正在清除USB存储设备的数据..."</string>
    <string name="progress_erasing" product="default" >"正在清除SD卡的数据..."</string>
    <string name="share" >"分享"</string>
    <string name="find" >"查找"</string>
    <string name="websearch" >"网页搜索"</string>
    <string name="find_next" >"下一条结果"</string>
    <string name="find_previous" >"上一条结果"</string>
    <string name="gpsNotifTicker" >"来自<xliff:g id="NAME">%s</xliff:g>的定位请求"</string>
    <string name="gpsNotifTitle" >"定位请求"</string>
    <string name="gpsNotifMessage" >"请求人：<xliff:g id="NAME">%1$s</xliff:g>（<xliff:g id="SERVICE">%2$s</xliff:g>）"</string>
    <string name="gpsVerifYes" >"是"</string>
    <string name="gpsVerifNo" >"否"</string>
    <string name="sync_too_many_deletes" >"超出删除限制"</string>
    <string name="sync_too_many_deletes_desc" >"帐号 <xliff:g id="ACCOUNT_NAME">%3$s</xliff:g> 在进行“<xliff:g id="TYPE_OF_SYNC">%2$s</xliff:g>”同步时删除了 <xliff:g id="NUMBER_OF_DELETED_ITEMS">%1$d</xliff:g> 项内容。您要如何处理这些删除的内容？"</string>
    <string name="sync_really_delete" >"删除这些内容"</string>
    <string name="sync_undo_deletes" >"撤消删除"</string>
    <string name="sync_do_nothing" >"目前不进行任何操作"</string>
    <string name="choose_account_label" >"选择帐号"</string>
    <string name="add_account_label" >"添加帐号"</string>
    <string name="add_account_button_label" >"添加帐号"</string>
    <string name="number_picker_increment_button" >"增大"</string>
    <string name="number_picker_decrement_button" >"减小"</string>
    <string name="number_picker_increment_scroll_mode" >"<xliff:g id="VALUE">%s</xliff:g> 触摸并按住。"</string>
    <string name="number_picker_increment_scroll_action" >"向上滑动可增大数值，向下滑动可减小数值。"</string>
    <string name="time_picker_increment_minute_button" >"增大分钟值"</string>
    <string name="time_picker_decrement_minute_button" >"减小分钟值"</string>
    <string name="time_picker_increment_hour_button" >"增大小时值"</string>
    <string name="time_picker_decrement_hour_button" >"减小小时值"</string>
    <string name="time_picker_increment_set_pm_button" >"设置下午时间"</string>
    <string name="time_picker_decrement_set_am_button" >"设置上午时间"</string>
    <string name="date_picker_increment_month_button" >"增大月份值"</string>
    <string name="date_picker_decrement_month_button" >"减小月份值"</string>
    <string name="date_picker_increment_day_button" >"增大日期值"</string>
    <string name="date_picker_decrement_day_button" >"减小日期值"</string>
    <string name="date_picker_increment_year_button" >"增大年份值"</string>
    <string name="date_picker_decrement_year_button" >"减小年份值"</string>
    <string name="date_picker_prev_month_button" >"上个月"</string>
    <string name="date_picker_next_month_button" >"下个月"</string>
    <string name="keyboardview_keycode_alt" >"Alt"</string>
    <string name="keyboardview_keycode_cancel" >"取消"</string>
    <string name="keyboardview_keycode_delete" >"Delete"</string>
    <string name="keyboardview_keycode_done" >"完成"</string>
    <string name="keyboardview_keycode_mode_change" >"模式更改"</string>
    <string name="keyboardview_keycode_shift" >"Shift"</string>
    <string name="keyboardview_keycode_enter" >"Enter"</string>
    <string name="activitychooserview_choose_application" >"选择应用"</string>
    <string name="activitychooserview_choose_application_error" >"无法启动“<xliff:g id="APPLICATION_NAME">%s</xliff:g>”"</string>
    <string name="shareactionprovider_share_with" >"分享方式"</string>
    <string name="shareactionprovider_share_with_application" >"使用<xliff:g id="APPLICATION_NAME">%s</xliff:g>分享"</string>
    <string name="content_description_sliding_handle" >"滑动手柄。触摸并按住。"</string>
    <string name="description_target_unlock_tablet" >"滑动解锁。"</string>
    <string name="action_bar_home_description" >"导航首页"</string>
    <string name="action_bar_up_description" >"向上导航"</string>
    <string name="action_menu_overflow_description" >"更多选项"</string>
    <string name="action_bar_home_description_format" >"%1$s：%2$s"</string>
    <string name="action_bar_home_subtitle_description_format" >"%1$s - %2$s：%3$s"</string>
    <string name="storage_internal" >"内部共享存储空间"</string>
    <string name="storage_sd_card" >"SD卡"</string>
    <string name="storage_sd_card_label" >"<xliff:g id="MANUFACTURER">%s</xliff:g> SD 卡"</string>
    <string name="storage_usb_drive" >"U 盘"</string>
    <string name="storage_usb_drive_label" >"<xliff:g id="MANUFACTURER">%s</xliff:g> U 盘"</string>
    <string name="storage_usb" >"USB存储器"</string>
    <string name="extract_edit_menu_button" >"修改"</string>
    <string name="data_usage_warning_title" >"数据流量警告"</string>
    <string name="data_usage_warning_body" >"您已使用 <xliff:g id="APP">%s</xliff:g> 的数据流量"</string>
    <string name="data_usage_mobile_limit_title" >"已达到移动数据流量上限"</string>
    <string name="data_usage_wifi_limit_title" >"已达到 WLAN 流量上限"</string>
    <string name="data_usage_limit_body" >"已暂停使用数据网络连接，直到这个周期结束为止"</string>
    <string name="data_usage_mobile_limit_snoozed_title" >"已超出移动数据流量上限"</string>
    <string name="data_usage_wifi_limit_snoozed_title" >"已超出 WLAN 数据流量上限"</string>
    <string name="data_usage_limit_snoozed_body" >"您已使用 <xliff:g id="SIZE">%s</xliff:g>，超出所设上限"</string>
    <string name="data_usage_restricted_title" >"后台流量受限制"</string>
    <string name="data_usage_restricted_body" >"点按即可取消限制。"</string>
    <string name="data_usage_rapid_title" >"移动数据用量较多"</string>
    <string name="data_usage_rapid_body" >"您的应用使用的数据流量比平时多"</string>
    <string name="data_usage_rapid_app_body" >"<xliff:g id="APP">%s</xliff:g>使用的数据流量比平时多"</string>
    <string name="ssl_certificate" >"安全证书"</string>
    <string name="ssl_certificate_is_valid" >"该证书有效。"</string>
    <string name="issued_to" >"颁发给："</string>
    <string name="common_name" >"常用名称："</string>
    <string name="org_name" >"组织："</string>
    <string name="org_unit" >"组织单位："</string>
    <string name="issued_by" >"颁发者："</string>
    <string name="validity_period" >"有效期："</string>
    <string name="issued_on" >"颁发时间："</string>
    <string name="expires_on" >"有效期至："</string>
    <string name="serial_number" >"序列号："</string>
    <string name="fingerprints" >"指纹："</string>
    <string name="sha256_fingerprint" >"SHA-256 指纹："</string>
    <string name="sha1_fingerprint" >"SHA-1 指纹："</string>
    <string name="activity_chooser_view_see_all" >"查看全部"</string>
    <string name="activity_chooser_view_dialog_title_default" >"选择活动"</string>
    <string name="share_action_provider_share_with" >"分享方式"</string>
    <string name="sending" >"正在发送..."</string>
    <string name="launchBrowserDefault" >"要启动浏览器吗？"</string>
    <string name="SetupCallDefault" >"要接听电话吗？"</string>
    <string name="activity_resolver_use_always" >"始终"</string>
    <string name="activity_resolver_use_once" >"仅此一次"</string>
    <string name="activity_resolver_work_profiles_support" >"%1$s不支持工作资料"</string>
    <string name="default_audio_route_name" product="tablet" >"平板电脑"</string>
    <string name="default_audio_route_name" product="tv" >"电视"</string>
    <string name="default_audio_route_name" product="default" >"手机"</string>
    <string name="default_audio_route_name_dock_speakers" >"基座扬声器"</string>
    <string name="default_audio_route_name_hdmi" >"HDMI"</string>
    <string name="default_audio_route_name_headphones" >"耳机"</string>
    <string name="default_audio_route_name_usb" >"USB"</string>
    <string name="default_audio_route_category_name" >"系统"</string>
    <string name="bluetooth_a2dp_audio_route_name" >"蓝牙音频"</string>
    <string name="wireless_display_route_description" >"无线显示"</string>
    <string name="media_route_button_content_description" >"投射"</string>
    <string name="media_route_chooser_title" >"连接到设备"</string>
    <string name="media_route_chooser_title_for_remote_display" >"将屏幕投射到设备上"</string>
    <string name="media_route_chooser_searching" >"正在搜索设备…"</string>
    <string name="media_route_chooser_extended_settings" >"设置"</string>
    <string name="media_route_controller_disconnect" >"断开连接"</string>
    <string name="media_route_status_scanning" >"正在扫描..."</string>
    <string name="media_route_status_connecting" >"正在连接..."</string>
    <string name="media_route_status_available" >"可连接"</string>
    <string name="media_route_status_not_available" >"无法连接"</string>
    <string name="media_route_status_in_use" >"正在使用"</string>
    <string name="display_manager_built_in_display_name" >"内置屏幕"</string>
    <string name="display_manager_hdmi_display_name" >"HDMI 屏幕"</string>
    <string name="display_manager_overlay_display_name" >"叠加视图 #<xliff:g id="ID">%1$d</xliff:g>"</string>
    <string name="display_manager_overlay_display_title" >"<xliff:g id="NAME">%1$s</xliff:g>：<xliff:g id="WIDTH">%2$d</xliff:g>x<xliff:g id="HEIGHT">%3$d</xliff:g>，<xliff:g id="DPI">%4$d</xliff:g> dpi"</string>
    <string name="display_manager_overlay_display_secure_suffix" >"，安全"</string>
    <string name="kg_forgot_pattern_button_text" >"忘记了图案"</string>
    <string name="kg_wrong_pattern" >"图案错误"</string>
    <string name="kg_wrong_password" >"密码错误"</string>
    <string name="kg_wrong_pin" >"PIN码有误"</string>
    <plurals name="kg_too_many_failed_attempts_countdown" formatted="false" >
      <item quantity="other">请在 <xliff:g id="NUMBER">%d</xliff:g> 秒后重试。</item>
      <item quantity="one">请在 1 秒后重试。</item>
    </plurals>
    <string name="kg_pattern_instructions" >"绘制您的图案"</string>
    <string name="kg_sim_pin_instructions" >"输入 SIM 卡 PIN 码"</string>
    <string name="kg_pin_instructions" >"输入PIN码"</string>
    <string name="kg_password_instructions" >"输入密码"</string>
    <string name="kg_puk_enter_puk_hint" >"SIM卡已被停用，需要输入PUK码才能继续使用。有关详情，请联系您的运营商。"</string>
    <string name="kg_puk_enter_pin_hint" >"请输入所需的PIN码"</string>
    <string name="kg_enter_confirm_pin_hint" >"请确认所需的PIN码"</string>
    <string name="kg_sim_unlock_progress_dialog_message" >"正在解锁SIM卡..."</string>
    <string name="kg_password_wrong_pin_code" >"PIN码有误。"</string>
    <string name="kg_invalid_sim_pin_hint" >"请输入4至8位数的PIN码。"</string>
    <string name="kg_invalid_sim_puk_hint" >"PUK码应包含8位数字。"</string>
    <string name="kg_invalid_puk" >"请重新输入正确的PUK码。如果尝试错误次数过多，SIM卡将永久停用。"</string>
    <string name="kg_invalid_confirm_pin_hint" product="default" >"PIN码不匹配"</string>
    <string name="kg_login_too_many_attempts" >"图案尝试次数过多"</string>
    <string name="kg_login_instructions" >"要解锁，请登录您的Google帐号。"</string>
    <string name="kg_login_username_hint" >"用户名（电子邮件地址）"</string>
    <string name="kg_login_password_hint" >"密码"</string>
    <string name="kg_login_submit_button" >"登录"</string>
    <string name="kg_login_invalid_input" >"用户名或密码无效。"</string>
    <string name="kg_login_account_recovery_hint" >"忘记了用户名或密码？\n请访问 "<b>"google.com/accounts/recovery"</b>"。"</string>
    <string name="kg_login_checking_password" >"正在检查帐号…"</string>
    <string name="kg_too_many_failed_pin_attempts_dialog_message" >"您已经<xliff:g id="NUMBER_0">%1$d</xliff:g>次输错了PIN码。\n\n请在<xliff:g id="NUMBER_1">%2$d</xliff:g>秒后重试。"</string>
    <string name="kg_too_many_failed_password_attempts_dialog_message" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次输错密码。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="kg_too_many_failed_pattern_attempts_dialog_message" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="tablet" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁平板电脑。如果再尝试  <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，平板电脑将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="tv" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁电视。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，电视将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="default" >"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁手机。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，手机将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_now_wiping" product="tablet" >"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁平板电脑。平板电脑现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_now_wiping" product="tv" >"您已经 <xliff:g id="NUMBER">%d</xliff:g> 次错误地尝试解锁电视。电视现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_now_wiping" product="default" >"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁手机。手机现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="tablet" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的电子邮件帐号解锁平板电脑。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="tv" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用电子邮件帐号解锁电视。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="default" >"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的电子邮件帐号解锁手机。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_text_message_separator" product="default" >" — "</string>
    <string name="kg_reordering_delete_drop_target_text" >"删除"</string>
    <string name="safe_media_volume_warning" product="default" >"要将音量调高到建议的音量以上吗？\n\n长时间保持高音量可能会损伤听力。"</string>
    <string name="accessibility_shortcut_warning_dialog_title" >"要使用无障碍快捷方式吗？"</string>
    <string name="accessibility_shortcut_toogle_warning" >"开启快捷方式后，同时按下两个音量按钮 3 秒钟即可启动所设定的无障碍功能。\n\n当前设定的无障碍功能：\n<xliff:g id="SERVICE_NAME">%1$s</xliff:g>\n\n如需更改设定的功能，请依次转到“设置”&gt;“无障碍”。"</string>
    <string name="disable_accessibility_shortcut" >"关闭快捷方式"</string>
    <string name="leave_accessibility_shortcut_on" >"使用快捷方式"</string>
    <string name="color_inversion_feature_name" >"颜色反转"</string>
    <string name="color_correction_feature_name" >"色彩校正"</string>
    <string name="accessibility_shortcut_enabling_service" >"无障碍快捷方式已开启<xliff:g id="SERVICE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_shortcut_disabling_service" >"无障碍快捷方式已关闭<xliff:g id="SERVICE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_button_prompt_text" >"选择按下“无障碍”按钮时要使用的功能："</string>
    <string name="accessibility_button_instructional_text" >"要更改指定的功能，请触摸并按住“无障碍”按钮。"</string>
    <string name="accessibility_magnification_chooser_text" >"放大功能"</string>
    <string name="user_switched" >"当前用户是<xliff:g id="NAME">%1$s</xliff:g>。"</string>
    <string name="user_switching_message" >"正在切换为<xliff:g id="NAME">%1$s</xliff:g>…"</string>
    <string name="user_logging_out_message" >"正在将<xliff:g id="NAME">%1$s</xliff:g>退出帐号…"</string>
    <string name="owner_name" >"机主"</string>
    <string name="error_message_title" >"错误"</string>
    <string name="error_message_change_not_allowed" >"您的管理员不允许进行这项更改"</string>
    <string name="app_not_found" >"找不到可处理此操作的应用"</string>
    <string name="revoke" >"撤消"</string>
    <string name="mediasize_iso_a0" >"ISO A0"</string>
    <string name="mediasize_iso_a1" >"ISO A1"</string>
    <string name="mediasize_iso_a2" >"ISO A2"</string>
    <string name="mediasize_iso_a3" >"ISO A3"</string>
    <string name="mediasize_iso_a4" >"ISO A4"</string>
    <string name="mediasize_iso_a5" >"ISO A5"</string>
    <string name="mediasize_iso_a6" >"ISO A6"</string>
    <string name="mediasize_iso_a7" >"ISO A7"</string>
    <string name="mediasize_iso_a8" >"ISO A8"</string>
    <string name="mediasize_iso_a9" >"ISO A9"</string>
    <string name="mediasize_iso_a10" >"ISO A10"</string>
    <string name="mediasize_iso_b0" >"ISO B0"</string>
    <string name="mediasize_iso_b1" >"ISO B1"</string>
    <string name="mediasize_iso_b2" >"ISO B2"</string>
    <string name="mediasize_iso_b3" >"ISO B3"</string>
    <string name="mediasize_iso_b4" >"ISO B4"</string>
    <string name="mediasize_iso_b5" >"ISO B5"</string>
    <string name="mediasize_iso_b6" >"ISO B6"</string>
    <string name="mediasize_iso_b7" >"ISO B7"</string>
    <string name="mediasize_iso_b8" >"ISO B8"</string>
    <string name="mediasize_iso_b9" >"ISO B9"</string>
    <string name="mediasize_iso_b10" >"ISO B10"</string>
    <string name="mediasize_iso_c0" >"ISO C0"</string>
    <string name="mediasize_iso_c1" >"ISO C1"</string>
    <string name="mediasize_iso_c2" >"ISO C2"</string>
    <string name="mediasize_iso_c3" >"ISO C3"</string>
    <string name="mediasize_iso_c4" >"ISO C4"</string>
    <string name="mediasize_iso_c5" >"ISO C5"</string>
    <string name="mediasize_iso_c6" >"ISO C6"</string>
    <string name="mediasize_iso_c7" >"ISO C7"</string>
    <string name="mediasize_iso_c8" >"ISO C8"</string>
    <string name="mediasize_iso_c9" >"ISO C9"</string>
    <string name="mediasize_iso_c10" >"ISO C10"</string>
    <string name="mediasize_na_letter" >"Letter"</string>
    <string name="mediasize_na_gvrnmt_letter" >"Government Letter"</string>
    <string name="mediasize_na_legal" >"Legal"</string>
    <string name="mediasize_na_junior_legal" >"Junior Legal"</string>
    <string name="mediasize_na_ledger" >"Ledger"</string>
    <string name="mediasize_na_tabloid" >"Tabloid"</string>
    <string name="mediasize_na_index_3x5" >"Index Card 3x5"</string>
    <string name="mediasize_na_index_4x6" >"Index Card 4x6"</string>
    <string name="mediasize_na_index_5x8" >"Index Card 5x8"</string>
    <string name="mediasize_na_monarch" >"Monarch"</string>
    <string name="mediasize_na_quarto" >"Quarto"</string>
    <string name="mediasize_na_foolscap" >"Foolscap"</string>
    <string name="mediasize_chinese_roc_8k" >"ROC 8K"</string>
    <string name="mediasize_chinese_roc_16k" >"ROC 16K"</string>
    <string name="mediasize_chinese_prc_1" >"PRC 1"</string>
    <string name="mediasize_chinese_prc_2" >"PRC 2"</string>
    <string name="mediasize_chinese_prc_3" >"PRC 3"</string>
    <string name="mediasize_chinese_prc_4" >"PRC 4"</string>
    <string name="mediasize_chinese_prc_5" >"PRC 5"</string>
    <string name="mediasize_chinese_prc_6" >"PRC 6"</string>
    <string name="mediasize_chinese_prc_7" >"PRC 7"</string>
    <string name="mediasize_chinese_prc_8" >"PRC 8"</string>
    <string name="mediasize_chinese_prc_9" >"PRC 9"</string>
    <string name="mediasize_chinese_prc_10" >"PRC 10"</string>
    <string name="mediasize_chinese_prc_16k" >"PRC 16K"</string>
    <string name="mediasize_chinese_om_pa_kai" >"8开"</string>
    <string name="mediasize_chinese_om_dai_pa_kai" >"大8开"</string>
    <string name="mediasize_chinese_om_jurro_ku_kai" >"16开"</string>
    <string name="mediasize_japanese_jis_b10" >"JIS B10"</string>
    <string name="mediasize_japanese_jis_b9" >"JIS B9"</string>
    <string name="mediasize_japanese_jis_b8" >"JIS B8"</string>
    <string name="mediasize_japanese_jis_b7" >"JIS B7"</string>
    <string name="mediasize_japanese_jis_b6" >"JIS B6"</string>
    <string name="mediasize_japanese_jis_b5" >"JIS B5"</string>
    <string name="mediasize_japanese_jis_b4" >"JIS B4"</string>
    <string name="mediasize_japanese_jis_b3" >"JIS B3"</string>
    <string name="mediasize_japanese_jis_b2" >"JIS B2"</string>
    <string name="mediasize_japanese_jis_b1" >"JIS B1"</string>
    <string name="mediasize_japanese_jis_b0" >"JIS B0"</string>
    <string name="mediasize_japanese_jis_exec" >"JIS Exec"</string>
    <string name="mediasize_japanese_chou4" >"Chou4"</string>
    <string name="mediasize_japanese_chou3" >"Chou3"</string>
    <string name="mediasize_japanese_chou2" >"Chou2"</string>
    <string name="mediasize_japanese_hagaki" >"Hagaki"</string>
    <string name="mediasize_japanese_oufuku" >"Oufuku"</string>
    <string name="mediasize_japanese_kahu" >"Kahu"</string>
    <string name="mediasize_japanese_kaku2" >"Kaku2"</string>
    <string name="mediasize_japanese_you4" >"You4"</string>
    <string name="mediasize_unknown_portrait" >"未知（纵向）"</string>
    <string name="mediasize_unknown_landscape" >"未知（横向）"</string>
    <string name="write_fail_reason_cancelled" >"已取消"</string>
    <string name="write_fail_reason_cannot_write" >"写入内容时出错"</string>
    <string name="reason_unknown" >"未知"</string>
    <string name="reason_service_unavailable" >"未启用打印服务"</string>
    <string name="print_service_installed_title" >"已安装“<xliff:g id="NAME">%s</xliff:g>”服务"</string>
    <string name="print_service_installed_message" >"点按即可启用"</string>
    <string name="restr_pin_enter_admin_pin" >"请输入管理员 PIN 码"</string>
    <string name="restr_pin_enter_pin" >"输入PIN码"</string>
    <string name="restr_pin_incorrect" >"错误"</string>
    <string name="restr_pin_enter_old_pin" >"当前PIN码"</string>
    <string name="restr_pin_enter_new_pin" >"新PIN码"</string>
    <string name="restr_pin_confirm_pin" >"确认新PIN码"</string>
    <string name="restr_pin_create_pin" >"设置PIN码，防止他人修改限制条件"</string>
    <string name="restr_pin_error_doesnt_match" >"PIN码不符，请重试。"</string>
    <string name="restr_pin_error_too_short" >"PIN码太短，至少应包含4位数字。"</string>
    <plurals name="restr_pin_countdown" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 秒后重试</item>
      <item quantity="one">1 秒后重试</item>
    </plurals>
    <string name="restr_pin_try_later" >"稍后重试"</string>
    <string name="immersive_cling_title" >"目前处于全屏模式"</string>
    <string name="immersive_cling_description" >"要退出，请从顶部向下滑动。"</string>
    <string name="immersive_cling_positive" >"知道了"</string>
    <string name="done_label" >"完成"</string>
    <string name="hour_picker_description" >"小时转盘"</string>
    <string name="minute_picker_description" >"分钟转盘"</string>
    <string name="select_hours" >"选择小时"</string>
    <string name="select_minutes" >"选择分钟"</string>
    <string name="select_day" >"选择月份和日期"</string>
    <string name="select_year" >"选择年份"</string>
    <string name="deleted_key" >"已删除<xliff:g id="KEY">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge" >"工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge_2" >"第二个工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge_3" >"第三个工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="lock_to_app_unlock_pin" >"取消时要求输入PIN码"</string>
    <string name="lock_to_app_unlock_pattern" >"取消时要求绘制解锁图案"</string>
    <string name="lock_to_app_unlock_password" >"取消时要求输入密码"</string>
    <string name="package_installed_device_owner" >"已由您的管理员安装"</string>
    <string name="package_updated_device_owner" >"已由您的管理员更新"</string>
    <string name="package_deleted_device_owner" >"已由您的管理员删除"</string>
    <string name="battery_saver_description_with_learn_more" >"为了延长电池续航时间，省电模式会关闭部分设备功能并限制应用。"<annotation id="url">"了解详情"</annotation></string>
    <string name="battery_saver_description" >"为了延长电池续航时间，省电模式会关闭部分设备功能并限制应用。"</string>
    <string name="data_saver_description" >"为了减少流量消耗，流量节省程序会阻止某些应用在后台收发数据。您当前使用的应用可以收发数据，但频率可能会降低。举例而言，这可能意味着图片只有在您点按之后才会显示。"</string>
    <string name="data_saver_enable_title" >"要开启流量节省程序吗？"</string>
    <string name="data_saver_enable_button" >"开启"</string>
    <plurals name="zen_mode_duration_minutes_summary" formatted="false" >
      <item quantity="other">%1$d 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes_summary_short" formatted="false" >
      <item quantity="other">%1$d 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>)）</item>
      <item quantity="one">1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_summary" formatted="false" >
      <item quantity="other">%1$d 小时（直到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 小时（直到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_summary_short" formatted="false" >
      <item quantity="other">%1$d 小时（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 小时（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes" formatted="false" >
      <item quantity="other">%d 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes_short" formatted="false" >
      <item quantity="other">%d 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="zen_mode_duration_hours" formatted="false" >
      <item quantity="other">%d 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_short" formatted="false" >
      <item quantity="other">%d 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <string name="zen_mode_until" >"到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>"</string>
    <string name="zen_mode_alarm" >"直到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>（闹钟下次响铃时）"</string>
    <string name="zen_mode_forever" >"直到您将其关闭"</string>
    <string name="zen_mode_forever_dnd" >"直到您关闭“勿扰”模式"</string>
    <string name="zen_mode_rule_name_combination" >"<xliff:g id="FIRST">%1$s</xliff:g> / <xliff:g id="REST">%2$s</xliff:g>"</string>
    <string name="toolbar_collapse_description" >"收起"</string>
    <string name="zen_mode_feature_name" >"勿扰"</string>
    <string name="zen_mode_downtime_feature_name" >"休息时间"</string>
    <string name="zen_mode_default_weeknights_name" >"周一至周五夜间"</string>
    <string name="zen_mode_default_weekends_name" >"周末"</string>
    <string name="zen_mode_default_events_name" >"活动"</string>
    <string name="zen_mode_default_every_night_name" >"睡眠"</string>
    <string name="muted_by" >"<xliff:g id="THIRD_PARTY">%1$s</xliff:g>正在将某些音效设为静音"</string>
    <string name="system_error_wipe_data" >"您的设备内部出现了问题。如果不将设备恢复出厂设置，设备运行可能会不稳定。"</string>
    <string name="system_error_manufacturer" >"您的设备内部出现了问题。请联系您的设备制造商了解详情。"</string>
    <string name="stk_cc_ussd_to_dial" >"USSD 请求已更改为普通通话"</string>
    <string name="stk_cc_ussd_to_ss" >"USSD 请求已更改为 SS 请求"</string>
    <string name="stk_cc_ussd_to_ussd" >"已更改为新的 USSD 请求"</string>
    <string name="stk_cc_ussd_to_dial_video" >"USSD 请求已更改为视频通话"</string>
    <string name="stk_cc_ss_to_dial" >"SS 请求已更改为普通通话"</string>
    <string name="stk_cc_ss_to_dial_video" >"SS 请求已更改为视频通话"</string>
    <string name="stk_cc_ss_to_ussd" >"SS 请求已更改为 USSD 请求"</string>
    <string name="stk_cc_ss_to_ss" >"已更改为新的 SS 请求"</string>
    <string name="notification_work_profile_content_description" >"工作资料"</string>
    <string name="expand_button_content_description_collapsed" >"展开"</string>
    <string name="expand_button_content_description_expanded" >"收起"</string>
    <string name="expand_action_accessibility" >"切换展开模式"</string>
    <string name="usb_midi_peripheral_name" >"Android USB 外设端口"</string>
    <string name="usb_midi_peripheral_manufacturer_name" >"Android"</string>
    <string name="usb_midi_peripheral_product_name" >"USB 外设端口"</string>
    <string name="floating_toolbar_open_overflow_description" >"更多选项"</string>
    <string name="floating_toolbar_close_overflow_description" >"关闭工具栏溢出"</string>
    <string name="maximize_button_text" >"最大化"</string>
    <string name="close_button_text" >"关闭"</string>
    <string name="notification_messaging_title_template" >"<xliff:g id="CONVERSATION_TITLE">%1$s</xliff:g>：<xliff:g id="SENDER_NAME">%2$s</xliff:g>"</string>
    <plurals name="selected_count" formatted="false" >
      <item quantity="other">已选择 <xliff:g id="COUNT_1">%1$d</xliff:g> 项</item>
      <item quantity="one">已选择 <xliff:g id="COUNT_0">%1$d</xliff:g> 项</item>
    </plurals>
    <string name="default_notification_channel_label" >"未分类"</string>
    <string name="importance_from_user" >"这些通知的重要程度由您来设置。"</string>
    <string name="importance_from_person" >"这条通知涉及特定的人，因此被归为重要通知。"</string>
    <string name="user_creation_account_exists" >"允许<xliff:g id="APP">%1$s</xliff:g>使用 <xliff:g id="ACCOUNT">%2$s</xliff:g> 创建新用户吗？"</string>
    <string name="user_creation_adding" >"允许<xliff:g id="APP">%1$s</xliff:g>使用 <xliff:g id="ACCOUNT">%2$s</xliff:g>（目前已有用户使用此帐号）创建新用户吗？"</string>
    <string name="language_selection_title" >"添加语言"</string>
    <string name="country_selection_title" >"区域偏好设置"</string>
    <string name="search_language_hint" >"输入语言名称"</string>
    <string name="language_picker_section_suggested" >"建议语言"</string>
    <string name="language_picker_section_all" >"所有语言"</string>
    <string name="region_picker_section_all" >"所有国家/地区"</string>
    <string name="locale_search_menu" >"搜索"</string>
    <string name="app_suspended_title" >"应用无法使用"</string>
    <string name="app_suspended_default_message" >"<xliff:g id="APP_NAME_0">%1$s</xliff:g>目前无法使用。该应用是由<xliff:g id="APP_NAME_1">%2$s</xliff:g>所管理。"</string>
    <string name="app_suspended_more_details" >"了解详情"</string>
    <string name="work_mode_off_title" >"要开启工作资料吗？"</string>
    <string name="work_mode_off_message" >"您的工作应用、通知、数据及其他工作资料功能将会开启"</string>
    <string name="work_mode_turn_on" >"开启"</string>
    <string name="deprecated_target_sdk_message" >"此应用专为旧版 Android 打造，因此可能无法正常运行。请尝试检查更新或与开发者联系。"</string>
    <string name="deprecated_target_sdk_app_store" >"检查更新"</string>
    <string name="new_sms_notification_title" >"您有新消息"</string>
    <string name="new_sms_notification_content" >"打开短信应用查看"</string>
    <string name="user_encrypted_title" >"部分功能可能会受到限制"</string>
    <string name="user_encrypted_message" >"点按即可解锁"</string>
    <string name="user_encrypted_detail" >"用户数据已锁定"</string>
    <string name="profile_encrypted_detail" >"工作资料已锁定"</string>
    <string name="profile_encrypted_message" >"点按即可解锁工作资料"</string>
    <string name="usb_mtp_launch_notification_title" >"已连接到<xliff:g id="PRODUCT_NAME">%1$s</xliff:g>"</string>
    <string name="usb_mtp_launch_notification_description" >"点按即可查看文件"</string>
    <string name="pin_target" >"固定"</string>
    <string name="unpin_target" >"取消固定"</string>
    <string name="app_info" >"应用信息"</string>
    <string name="negative_duration" >"−<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="demo_starting_message" >"正在启动演示模式…"</string>
    <string name="demo_restarting_message" >"正在重置设备…"</string>
    <string name="suspended_widget_accessibility" >"已停用的<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="conference_call" >"电话会议"</string>
    <string name="tooltip_popup_title" >"提示"</string>
    <string name="app_category_game" >"游戏"</string>
    <string name="app_category_audio" >"音乐和音频"</string>
    <string name="app_category_video" >"电影和视频"</string>
    <string name="app_category_image" >"照片和图片"</string>
    <string name="app_category_social" >"社交和通信"</string>
    <string name="app_category_news" >"新闻和杂志"</string>
    <string name="app_category_maps" >"地图和导航"</string>
    <string name="app_category_productivity" >"办公"</string>
    <string name="device_storage_monitor_notification_channel" >"设备存储空间"</string>
    <string name="adb_debugging_notification_channel_tv" >"USB 调试"</string>
    <string name="time_picker_hour_label" >"点"</string>
    <string name="time_picker_minute_label" >"分"</string>
    <string name="time_picker_header_text" >"设置时间"</string>
    <string name="time_picker_input_error" >"请输入有效的时间"</string>
    <string name="time_picker_prompt_label" >"请输入时间"</string>
    <string name="time_picker_text_input_mode_description" >"切换到文字输入模式来输入时间。"</string>
    <string name="time_picker_radial_mode_description" >"切换到时钟模式来输入时间。"</string>
    <string name="autofill_picker_accessibility_title" >"自动填充选项"</string>
    <string name="autofill_save_accessibility_title" >"保存以便用于自动填充"</string>
    <string name="autofill_error_cannot_autofill" >"无法自动填充内容"</string>
    <string name="autofill_picker_no_suggestions" >"没有自动填充建议"</string>
    <plurals name="autofill_picker_some_suggestions" formatted="false" >
      <item quantity="other"><xliff:g id="COUNT">%1$s</xliff:g> 条自动填充建议</item>
      <item quantity="one">1 条自动填充建议</item>
    </plurals>
    <string name="autofill_save_title" >"要保存到&lt;b&gt;<xliff:g id="LABEL">%1$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_type" >"要将<xliff:g id="TYPE">%1$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%2$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_2types" >"要将<xliff:g id="TYPE_0">%1$s</xliff:g>和<xliff:g id="TYPE_1">%2$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%3$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_3types" >"要将<xliff:g id="TYPE_0">%1$s</xliff:g>、<xliff:g id="TYPE_1">%2$s</xliff:g>和<xliff:g id="TYPE_2">%3$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%4$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_yes" >"保存"</string>
    <string name="autofill_save_no" >"不用了"</string>
    <string name="autofill_save_type_password" >"密码"</string>
    <string name="autofill_save_type_address" >"地址"</string>
    <string name="autofill_save_type_credit_card" >"信用卡"</string>
    <string name="autofill_save_type_username" >"用户名"</string>
    <string name="autofill_save_type_email_address" >"电子邮件地址"</string>
    <string name="etws_primary_default_message_earthquake" >"请保持冷静，并寻找附近的避难地点。"</string>
    <string name="etws_primary_default_message_tsunami" >"请立即从沿海和河滨区域撤离到高地等较安全的地方。"</string>
    <string name="etws_primary_default_message_earthquake_and_tsunami" >"请保持冷静，并寻找附近的避难地点。"</string>
    <string name="etws_primary_default_message_test" >"紧急消息测试"</string>
    <string name="notification_reply_button_accessibility" >"回复"</string>
    <string name="etws_primary_default_message_others" ></string>
    <string name="mmcc_authentication_reject" >"SIM 卡不支持语音"</string>
    <string name="mmcc_imsi_unknown_in_hlr" >"未配置支持语音的 SIM 卡"</string>
    <string name="mmcc_illegal_ms" >"SIM 卡不支持语音"</string>
    <string name="mmcc_illegal_me" >"手机不支持语音"</string>
    <string name="mmcc_authentication_reject_msim_template" >"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_imsi_unknown_in_hlr_msim_template" >"未配置 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_illegal_ms_msim_template" >"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_illegal_me_msim_template" >"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="popup_window_default_title" >"弹出式窗口"</string>
    <string name="slice_more_content" >"+ <xliff:g id="NUMBER">%1$d</xliff:g>"</string>
    <string name="shortcut_restored_on_lower_version" >"应用版本已降级或与此快捷方式不兼容"</string>
    <string name="shortcut_restore_not_supported" >"无法恢复快捷方式，因为应用不支持备份和恢复功能"</string>
    <string name="shortcut_restore_signature_mismatch" >"无法恢复快捷方式，因为应用签名不相符"</string>
    <string name="shortcut_restore_unknown_issue" >"无法恢复快捷方式"</string>
    <string name="shortcut_disabled_reason_unknown" >"快捷方式已停用"</string>
    <string name="harmful_app_warning_uninstall" >"卸载"</string>
    <string name="harmful_app_warning_open_anyway" >"仍然打开"</string>
    <string name="harmful_app_warning_title" >"检测到有害应用"</string>
    <string name="slices_permission_request" >"“<xliff:g id="APP_0">%1$s</xliff:g>”想要显示“<xliff:g id="APP_2">%2$s</xliff:g>”图块"</string>
    <string name="screenshot_edit" >"编辑"</string>
    <string name="volume_dialog_ringer_guidance_vibrate" >"有来电和通知时会振动"</string>
    <string name="volume_dialog_ringer_guidance_silent" >"有来电和通知时会静音"</string>
    <string name="notification_channel_system_changes" >"系统变更"</string>
    <string name="notification_channel_do_not_disturb" >"勿扰"</string>
    <string name="zen_upgrade_notification_visd_title" >"新功能：勿扰模式目前可隐藏通知"</string>
    <string name="zen_upgrade_notification_visd_content" >"点按即可了解详情以及进行更改。"</string>
    <string name="zen_upgrade_notification_title" >"“勿扰”设置有变更"</string>
    <string name="zen_upgrade_notification_content" >"点按即可查看屏蔽内容。"</string>
    <string name="notification_app_name_system" >"系统"</string>
    <string name="notification_app_name_settings" >"设置"</string>
    <string name="notification_appops_camera_active" >"相机"</string>
    <string name="notification_appops_microphone_active" >"麦克风"</string>
    <string name="notification_appops_overlay_active" >"显示在屏幕上其他应用的上层"</string>
    <string name="car_loading_profile" >"正在加载"</string>
</resources>


```



# frameworks/base/packages/SystemUI

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/frameworks/base/packages/SystemUI/res/values-zh-rCN/strings.xml
frameworks_base_packages_SystemUI_strings_cn.xml

```

<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="app_label" >"系统界面"</string>
    <string name="status_bar_clear_all_button" >"清除"</string>
    <string name="status_bar_recent_remove_item_title" >"从列表中删除"</string>
    <string name="status_bar_recent_inspect_item_title" >"应用信息"</string>
    <string name="status_bar_no_recent_apps" >"您最近浏览过的屏幕会显示在此处"</string>
    <string name="status_bar_accessibility_dismiss_recents" >"关闭最近运行的应用"</string>
    <plurals name="status_bar_accessibility_recent_apps" formatted="false" >
      <item quantity="other">概览中有 %d 个屏幕</item>
      <item quantity="one">概览中有 1 个屏幕</item>
    </plurals>
    <string name="status_bar_no_notifications_title" >"无通知"</string>
    <string name="status_bar_ongoing_events_title" >"正在进行的"</string>
    <string name="status_bar_latest_events_title" >"通知"</string>
    <string name="battery_low_title" >"电池电量可能很快就要耗尽"</string>
    <string name="battery_low_percent_format" >"剩余<xliff:g id="PERCENTAGE">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_hybrid" >"剩余电量：<xliff:g id="PERCENTAGE">%s</xliff:g>；根据您的使用情况，大约还可使用 <xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_hybrid_short" >"剩余电量：<xliff:g id="PERCENTAGE">%s</xliff:g>；大约还可使用 <xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_saver_started" >"剩余 <xliff:g id="PERCENTAGE">%s</xliff:g>。省电模式已开启。"</string>
    <string name="invalid_charger" >"无法通过 USB 充电。请使用设备随附的充电器。"</string>
    <string name="invalid_charger_title" >"无法通过 USB 充电"</string>
    <string name="invalid_charger_text" >"使用设备随附的充电器"</string>
    <string name="battery_low_why" >"设置"</string>
    <string name="battery_saver_confirmation_title" >"要开启省电模式吗？"</string>
    <string name="battery_saver_confirmation_ok" >"开启"</string>
    <string name="battery_saver_start_action" >"开启省电模式"</string>
    <string name="status_bar_settings_settings_button" >"设置"</string>
    <string name="status_bar_settings_wifi_button" >"WLAN"</string>
    <string name="status_bar_settings_auto_rotation" >"自动旋转屏幕"</string>
    <string name="status_bar_settings_mute_label" >"静音"</string>
    <string name="status_bar_settings_auto_brightness_label" >"自动"</string>
    <string name="status_bar_settings_notifications" >"通知"</string>
    <string name="bluetooth_tethered" >"已通过蓝牙共享网络"</string>
    <string name="status_bar_input_method_settings_configure_input_methods" >"设置输入法"</string>
    <string name="status_bar_use_physical_keyboard" >"物理键盘"</string>
    <string name="usb_device_permission_prompt" >"要允许<xliff:g id="APPLICATION">%1$s</xliff:g>访问<xliff:g id="USB_DEVICE">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_permission_prompt" >"要允许<xliff:g id="APPLICATION">%1$s</xliff:g>访问<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>吗？"</string>
    <string name="usb_device_confirm_prompt" >"要打开<xliff:g id="APPLICATION">%1$s</xliff:g>来处理<xliff:g id="USB_DEVICE">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_confirm_prompt" >"要打开<xliff:g id="APPLICATION">%1$s</xliff:g>来处理<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_uri_prompt" >"未安装此USB配件适用的应用。要了解此配件的详情，请访问：<xliff:g id="URL">%1$s</xliff:g>"</string>
    <string name="title_usb_accessory" >"USB配件"</string>
    <string name="label_view" >"查看"</string>
    <string name="always_use_device" >"连接<xliff:g id="USB_DEVICE">%2$s</xliff:g>后一律打开<xliff:g id="APPLICATION">%1$s</xliff:g>"</string>
    <string name="always_use_accessory" >"连接<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>后一律打开<xliff:g id="APPLICATION">%1$s</xliff:g>"</string>
    <string name="usb_debugging_title" >"允许 USB 调试吗？"</string>
    <string name="usb_debugging_message" >"这台计算机的 RSA 密钥指纹如下：\n<xliff:g id="FINGERPRINT">%1$s</xliff:g>"</string>
    <string name="usb_debugging_always" >"一律允许使用这台计算机进行调试"</string>
    <string name="usb_debugging_secondary_user_title" >"不允许使用 USB 调试功能"</string>
    <string name="usb_debugging_secondary_user_message" >"目前已登录此设备的用户无法开启 USB 调试功能。要使用此功能，请切换为主要用户的帐号。"</string>
    <string name="compat_mode_on" >"缩放以填满屏幕"</string>
    <string name="compat_mode_off" >"拉伸以填满屏幕"</string>
    <string name="global_action_screenshot" >"屏幕截图"</string>
    <string name="screenshot_saving_ticker" >"正在保存屏幕截图..."</string>
    <string name="screenshot_saving_title" >"正在保存屏幕截图..."</string>
    <string name="screenshot_saved_title" >"已保存屏幕截图"</string>
    <string name="screenshot_saved_text" >"点按即可查看您的屏幕截图"</string>
    <string name="screenshot_failed_title" >"无法保存屏幕截图"</string>
    <string name="screenshot_failed_to_save_unknown_text" >"请再次尝试截屏"</string>
    <string name="screenshot_failed_to_save_text" >"由于存储空间有限，无法保存屏幕截图"</string>
    <string name="screenshot_failed_to_capture_text" >"此应用或您所在的单位不允许进行屏幕截图"</string>
    <string name="usb_preference_title" >"USB文件传输选项"</string>
    <string name="use_mtp_button_title" >"作为媒体播放器(MTP)装载"</string>
    <string name="use_ptp_button_title" >"作为相机(PTP)装载"</string>
    <string name="installer_cd_button_title" >"安装适用于 Mac 的 Android 文件传输应用"</string>
    <string name="accessibility_back" >"返回"</string>
    <string name="accessibility_home" >"主屏幕"</string>
    <string name="accessibility_menu" >"菜单"</string>
    <string name="accessibility_accessibility_button" >"无障碍"</string>
    <string name="accessibility_rotate_button" >"旋转屏幕"</string>
    <string name="accessibility_recent" >"概览"</string>
    <string name="accessibility_search_light" >"搜索"</string>
    <string name="accessibility_camera_button" >"相机"</string>
    <string name="accessibility_phone_button" >"电话"</string>
    <string name="accessibility_voice_assist_button" >"语音助理"</string>
    <string name="accessibility_unlock_button" >"解锁"</string>
    <string name="accessibility_waiting_for_fingerprint" >"正在等待提供指纹"</string>
    <string name="accessibility_unlock_without_fingerprint" >"不使用指纹解锁"</string>
    <string name="accessibility_scanning_face" >"正在扫描面孔"</string>
    <string name="accessibility_send_smart_reply" >"发送"</string>
    <string name="unlock_label" >"解锁"</string>
    <string name="phone_label" >"打开电话"</string>
    <string name="voice_assist_label" >"打开语音助理"</string>
    <string name="camera_label" >"打开相机"</string>
    <string name="recents_caption_resize" >"选择新的任务布局"</string>
    <string name="cancel" >"取消"</string>
    <string name="fingerprint_dialog_touch_sensor" >"请触摸指纹传感器"</string>
    <string name="accessibility_fingerprint_dialog_fingerprint_icon" >"指纹图标"</string>
    <string name="accessibility_fingerprint_dialog_app_icon" >"应用图标"</string>
    <string name="accessibility_fingerprint_dialog_help_area" >"帮助消息区域"</string>
    <string name="accessibility_compatibility_zoom_button" >"兼容性缩放按钮。"</string>
    <string name="accessibility_compatibility_zoom_example" >"将小屏幕的图片放大在较大屏幕上显示。"</string>
    <string name="accessibility_bluetooth_connected" >"蓝牙已连接。"</string>
    <string name="accessibility_bluetooth_disconnected" >"蓝牙连接已断开。"</string>
    <string name="accessibility_no_battery" >"没有电池。"</string>
    <string name="accessibility_battery_one_bar" >"电池电量为一格。"</string>
    <string name="accessibility_battery_two_bars" >"电池电量为两格。"</string>
    <string name="accessibility_battery_three_bars" >"电池电量为三格。"</string>
    <string name="accessibility_battery_full" >"电池电量满格。"</string>
    <string name="accessibility_no_phone" >"没有手机信号。"</string>
    <string name="accessibility_phone_one_bar" >"手机信号强度为一格。"</string>
    <string name="accessibility_phone_two_bars" >"手机信号强度为两格。"</string>
    <string name="accessibility_phone_three_bars" >"手机信号强度为三格。"</string>
    <string name="accessibility_phone_signal_full" >"手机信号满格。"</string>
    <string name="accessibility_no_data" >"没有数据网络信号。"</string>
    <string name="accessibility_data_one_bar" >"数据信号强度为一格。"</string>
    <string name="accessibility_data_two_bars" >"数据信号强度为两格。"</string>
    <string name="accessibility_data_three_bars" >"数据信号强度为三格。"</string>
    <string name="accessibility_data_signal_full" >"数据信号满格。"</string>
    <string name="accessibility_wifi_name" >"已连接到<xliff:g id="WIFI">%s</xliff:g>。"</string>
    <string name="accessibility_bluetooth_name" >"已连接到<xliff:g id="BLUETOOTH">%s</xliff:g>。"</string>
    <string name="accessibility_cast_name" >"已连接到 <xliff:g id="CAST">%s</xliff:g>。"</string>
    <string name="accessibility_no_wimax" >"无 WiMAX 信号。"</string>
    <string name="accessibility_wimax_one_bar" >"WiMAX 信号强度为一格。"</string>
    <string name="accessibility_wimax_two_bars" >"WiMAX 信号强度为两格。"</string>
    <string name="accessibility_wimax_three_bars" >"WiMAX 信号强度为三格。"</string>
    <string name="accessibility_wimax_signal_full" >"WiMAX 信号满格。"</string>
    <string name="accessibility_ethernet_disconnected" >"以太网已断开连接。"</string>
    <string name="accessibility_ethernet_connected" >"以太网已连接。"</string>
    <string name="accessibility_no_signal" >"无信号。"</string>
    <string name="accessibility_not_connected" >"未连接。"</string>
    <string name="accessibility_zero_bars" >"信号强度为零格。"</string>
    <string name="accessibility_one_bar" >"信号强度为一格。"</string>
    <string name="accessibility_two_bars" >"信号强度为两格。"</string>
    <string name="accessibility_three_bars" >"信号强度为三格。"</string>
    <string name="accessibility_signal_full" >"信号满格。"</string>
    <string name="accessibility_desc_on" >"开启。"</string>
    <string name="accessibility_desc_off" >"关闭。"</string>
    <string name="accessibility_desc_connected" >"已连接。"</string>
    <string name="accessibility_desc_connecting" >"正在连接。"</string>
    <string name="data_connection_gprs" >"GPRS"</string>
    <string name="data_connection_hspa" >"HSPA"</string>
    <string name="data_connection_3g" >"3G"</string>
    <string name="data_connection_3_5g" >"H"</string>
    <string name="data_connection_3_5g_plus" >"H+"</string>
    <string name="data_connection_4g" >"4G"</string>
    <string name="data_connection_4g_plus" >"4G+"</string>
    <string name="data_connection_lte" >"LTE"</string>
    <string name="data_connection_lte_plus" >"LTE+"</string>
    <string name="data_connection_cdma" >"1X"</string>
    <string name="data_connection_roaming" >"漫游"</string>
    <string name="data_connection_edge" >"EDGE"</string>
    <string name="accessibility_data_connection_wifi" >"WLAN"</string>
    <string name="accessibility_no_sim" >"无 SIM 卡。"</string>
    <string name="accessibility_cell_data" >"移动数据"</string>
    <string name="accessibility_cell_data_on" >"移动数据已开启"</string>
    <string name="cell_data_off_content_description" >"移动数据网络已关闭"</string>
    <string name="cell_data_off" >"关闭"</string>
    <string name="accessibility_bluetooth_tether" >"蓝牙网络共享。"</string>
    <string name="accessibility_airplane_mode" >"飞行模式。"</string>
    <string name="accessibility_vpn_on" >"VPN 已开启。"</string>
    <string name="accessibility_no_sims" >"没有 SIM 卡。"</string>
    <string name="carrier_network_change_mode" >"运营商网络正在更改"</string>
    <string name="accessibility_battery_details" >"打开电量详情"</string>
    <string name="accessibility_battery_level" >"电池电量为百分之 <xliff:g id="NUMBER">%d</xliff:g>。"</string>
    <string name="accessibility_battery_level_charging" >"正在充电，已完成百分之<xliff:g id="BATTERY_PERCENTAGE">%d</xliff:g>。"</string>
    <string name="accessibility_settings_button" >"系统设置。"</string>
    <string name="accessibility_notifications_button" >"通知。"</string>
    <string name="accessibility_overflow_action" >"查看所有通知"</string>
    <string name="accessibility_remove_notification" >"清除通知。"</string>
    <string name="accessibility_gps_enabled" >"GPS已启用。"</string>
    <string name="accessibility_gps_acquiring" >"正在获取GPS信号。"</string>
    <string name="accessibility_tty_enabled" >"电传打字机已启用。"</string>
    <string name="accessibility_ringer_vibrate" >"振铃器振动。"</string>
    <string name="accessibility_ringer_silent" >"振铃器静音。"</string>
    <!-- no translation found for accessibility_casting (6887382141726543668) -->
    <skip />
    <!-- no translation found for accessibility_work_mode (702887484664647430) -->
    <skip />
    <string name="accessibility_recents_item_will_be_dismissed" >"移除<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="accessibility_recents_item_dismissed" >"已删除<xliff:g id="APP">%s</xliff:g>"</string>
    <string name="accessibility_recents_all_items_dismissed" >"已关闭所有最近用过的应用。"</string>
    <string name="accessibility_recents_item_open_app_info" >"打开<xliff:g id="APP">%s</xliff:g>应用信息。"</string>
    <string name="accessibility_recents_item_launched" >"正在启动<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="accessibility_notification_dismissed" >"已关闭通知。"</string>
    <string name="accessibility_desc_notification_shade" >"通知栏。"</string>
    <string name="accessibility_desc_quick_settings" >"快捷设置。"</string>
    <string name="accessibility_desc_lock_screen" >"锁定屏幕。"</string>
    <string name="accessibility_desc_settings" >"设置"</string>
    <string name="accessibility_desc_recent_apps" >"概览。"</string>
    <string name="accessibility_desc_work_lock" >"工作锁定屏幕"</string>
    <string name="accessibility_desc_close" >"关闭"</string>
    <string name="accessibility_quick_settings_wifi" >"<xliff:g id="SIGNAL">%1$s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_wifi_changed_off" >"WLAN已关闭。"</string>
    <string name="accessibility_quick_settings_wifi_changed_on" >"WLAN已开启。"</string>
    <string name="accessibility_quick_settings_mobile" >"移动数据连接：<xliff:g id="SIGNAL">%1$s</xliff:g>，<xliff:g id="TYPE">%2$s</xliff:g>，<xliff:g id="NETWORK">%3$s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_battery" >"电池电量：<xliff:g id="STATE">%s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_airplane_off" >"飞行模式关闭。"</string>
    <string name="accessibility_quick_settings_airplane_on" >"飞行模式开启。"</string>
    <string name="accessibility_quick_settings_airplane_changed_off" >"飞行模式已关闭。"</string>
    <string name="accessibility_quick_settings_airplane_changed_on" >"飞行模式已开启。"</string>
    <string name="accessibility_quick_settings_dnd_none_on" >"完全静音"</string>
    <string name="accessibility_quick_settings_dnd_alarms_on" >"仅限闹钟"</string>
    <string name="accessibility_quick_settings_dnd" >"勿扰。"</string>
    <string name="accessibility_quick_settings_dnd_changed_off" >"已关闭勿扰模式。"</string>
    <string name="accessibility_quick_settings_dnd_changed_on" >"已开启勿扰模式。"</string>
    <string name="accessibility_quick_settings_bluetooth" >"蓝牙。"</string>
    <string name="accessibility_quick_settings_bluetooth_off" >"蓝牙关闭。"</string>
    <string name="accessibility_quick_settings_bluetooth_on" >"蓝牙开启。"</string>
    <string name="accessibility_quick_settings_bluetooth_connecting" >"蓝牙连接中。"</string>
    <string name="accessibility_quick_settings_bluetooth_connected" >"蓝牙已连接。"</string>
    <string name="accessibility_quick_settings_bluetooth_changed_off" >"蓝牙已关闭。"</string>
    <string name="accessibility_quick_settings_bluetooth_changed_on" >"蓝牙已开启。"</string>
    <string name="accessibility_quick_settings_location_off" >"位置报告功能关闭。"</string>
    <string name="accessibility_quick_settings_location_on" >"位置报告功能开启。"</string>
    <string name="accessibility_quick_settings_location_changed_off" >"位置报告功能已关闭。"</string>
    <string name="accessibility_quick_settings_location_changed_on" >"位置报告功能已开启。"</string>
    <string name="accessibility_quick_settings_alarm" >"闹钟已设置为：<xliff:g id="TIME">%s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_close" >"关闭面板。"</string>
    <string name="accessibility_quick_settings_more_time" >"延长时间。"</string>
    <string name="accessibility_quick_settings_less_time" >"缩短时间。"</string>
    <string name="accessibility_quick_settings_flashlight_off" >"手电筒关闭。"</string>
    <string name="accessibility_quick_settings_flashlight_unavailable" >"无法使用手电筒。"</string>
    <string name="accessibility_quick_settings_flashlight_on" >"手电筒打开。"</string>
    <string name="accessibility_quick_settings_flashlight_changed_off" >"手电筒已关闭。"</string>
    <string name="accessibility_quick_settings_flashlight_changed_on" >"手电筒已打开。"</string>
    <string name="accessibility_quick_settings_color_inversion_changed_off" >"颜色反转功能已关闭。"</string>
    <string name="accessibility_quick_settings_color_inversion_changed_on" >"颜色反转功能已开启。"</string>
    <string name="accessibility_quick_settings_hotspot_changed_off" >"移动热点已关闭。"</string>
    <string name="accessibility_quick_settings_hotspot_changed_on" >"移动热点已开启。"</string>
    <string name="accessibility_casting_turned_off" >"屏幕投射已停止。"</string>
    <string name="accessibility_quick_settings_work_mode_off" >"工作模式关闭。"</string>
    <string name="accessibility_quick_settings_work_mode_on" >"工作模式开启。"</string>
    <string name="accessibility_quick_settings_work_mode_changed_off" >"工作模式已关闭。"</string>
    <string name="accessibility_quick_settings_work_mode_changed_on" >"工作模式已开启。"</string>
    <string name="accessibility_quick_settings_data_saver_changed_off" >"流量节省程序已关闭。"</string>
    <string name="accessibility_quick_settings_data_saver_changed_on" >"流量节省程序已开启。"</string>
    <string name="accessibility_brightness" >"屏幕亮度"</string>
    <string name="accessibility_ambient_display_charging" >"正在充电"</string>
    <string name="data_usage_disabled_dialog_3g_title" >"2G-3G 数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog_4g_title" >"4G 数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog_mobile_title" >"已暂停使用移动数据网络"</string>
    <string name="data_usage_disabled_dialog_title" >"数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog" >"您的数据流量消耗已达到所设置的上限，因此已停用移动数据网络。\n\n如果您要继续使用移动数据网络，则可能需要支付相应的流量费用。"</string>
    <string name="data_usage_disabled_dialog_enable" >"恢复"</string>
    <string name="gps_notification_searching_text" >"正在搜索GPS"</string>
    <string name="gps_notification_found_text" >"已通过GPS确定位置"</string>
    <string name="accessibility_location_active" >"应用发出了有效位置信息请求"</string>
    <string name="accessibility_clear_all" >"清除所有通知。"</string>
    <string name="notification_group_overflow_indicator" >"+ <xliff:g id="NUMBER">%s</xliff:g>"</string>
    <string name="notification_group_overflow_indicator_ambient" >"<xliff:g id="NOTIFICATION_TITLE">%s</xliff:g> (+<xliff:g id="OVERFLOW">%s</xliff:g>)"</string>
    <plurals name="notification_group_overflow_description" formatted="false" >
      <item quantity="other">此群组内还有 <xliff:g id="NUMBER_1">%s</xliff:g> 条通知。</item>
      <item quantity="one">此群组内还有 <xliff:g id="NUMBER_0">%s</xliff:g> 条通知。</item>
    </plurals>
    <string name="status_bar_notification_inspect_item_title" >"通知设置"</string>
    <string name="status_bar_notification_app_settings_title" >"<xliff:g id="APP_NAME">%s</xliff:g>设置"</string>
    <string name="accessibility_rotation_lock_off" >"屏幕会自动旋转。"</string>
    <string name="accessibility_rotation_lock_on_landscape" >"屏幕锁定为横屏模式。"</string>
    <string name="accessibility_rotation_lock_on_portrait" >"屏幕锁定为纵向模式。"</string>
    <string name="accessibility_rotation_lock_off_changed" >"屏幕将会自动旋转。"</string>
    <string name="accessibility_rotation_lock_on_landscape_changed" >"屏幕现已锁定为横屏模式。"</string>
    <string name="accessibility_rotation_lock_on_portrait_changed" >"屏幕现已锁定为纵向模式。"</string>
    <string name="dessert_case" >"甜品盒"</string>
    <string name="start_dreams" >"屏保"</string>
    <string name="ethernet_label" >"有线网络"</string>
    <string name="quick_settings_header_onboarding_text" >"触摸并按住相应图标即可查看更多选项"</string>
    <string name="quick_settings_dnd_label" >"勿扰"</string>
    <string name="quick_settings_dnd_priority_label" >"仅限优先事项"</string>
    <string name="quick_settings_dnd_alarms_label" >"仅限闹钟"</string>
    <string name="quick_settings_dnd_none_label" >"完全阻止"</string>
    <string name="quick_settings_bluetooth_label" >"蓝牙"</string>
    <string name="quick_settings_bluetooth_multiple_devices_label" >"蓝牙（<xliff:g id="NUMBER">%d</xliff:g> 台设备）"</string>
    <string name="quick_settings_bluetooth_off_label" >"蓝牙：关闭"</string>
    <string name="quick_settings_bluetooth_detail_empty_text" >"没有可用的配对设备"</string>
    <string name="quick_settings_bluetooth_secondary_label_battery_level" >"电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%s</xliff:g>"</string>
    <string name="quick_settings_bluetooth_secondary_label_audio" >"音频"</string>
    <string name="quick_settings_bluetooth_secondary_label_headset" >"耳机"</string>
    <string name="quick_settings_bluetooth_secondary_label_input" >"输入"</string>
    <string name="quick_settings_bluetooth_secondary_label_transient" >"正在开启…"</string>
    <string name="quick_settings_brightness_label" >"亮度"</string>
    <string name="quick_settings_rotation_unlocked_label" >"自动旋转"</string>
    <string name="accessibility_quick_settings_rotation" >"自动旋转屏幕"</string>
    <string name="accessibility_quick_settings_rotation_value" >"<xliff:g id="ID_1">%s</xliff:g>模式"</string>
    <string name="quick_settings_rotation_locked_label" >"屏幕方向：锁定"</string>
    <string name="quick_settings_rotation_locked_portrait_label" >"纵向"</string>
    <string name="quick_settings_rotation_locked_landscape_label" >"横向"</string>
    <string name="quick_settings_ime_label" >"输入法"</string>
    <string name="quick_settings_location_label" >"位置信息"</string>
    <string name="quick_settings_location_off_label" >"位置信息：关闭"</string>
    <string name="quick_settings_media_device_label" >"媒体设备"</string>
    <string name="quick_settings_rssi_label" >"RSSI"</string>
    <string name="quick_settings_rssi_emergency_only" >"只能拨打紧急呼救电话"</string>
    <string name="quick_settings_settings_label" >"设置"</string>
    <string name="quick_settings_time_label" >"时间"</string>
    <string name="quick_settings_user_label" >"我"</string>
    <string name="quick_settings_user_title" >"用户"</string>
    <string name="quick_settings_user_new_user" >"新用户"</string>
    <string name="quick_settings_wifi_label" >"WLAN"</string>
    <string name="quick_settings_wifi_not_connected" >"未连接"</string>
    <string name="quick_settings_wifi_no_network" >"无网络"</string>
    <string name="quick_settings_wifi_off_label" >"WLAN：关闭"</string>
    <string name="quick_settings_wifi_on_label" >"WLAN 已开启"</string>
    <string name="quick_settings_wifi_detail_empty_text" >"没有 WLAN 网络"</string>
    <string name="quick_settings_wifi_secondary_label_transient" >"正在开启…"</string>
    <string name="quick_settings_cast_title" >"投射"</string>
    <string name="quick_settings_casting" >"正在投射"</string>
    <string name="quick_settings_cast_device_default_name" >"未命名设备"</string>
    <string name="quick_settings_cast_device_default_description" >"已准备好投射"</string>
    <string name="quick_settings_cast_detail_empty_text" >"没有可用设备"</string>
    <string name="quick_settings_brightness_dialog_title" >"亮度"</string>
    <string name="quick_settings_brightness_dialog_auto_brightness_label" >"自动"</string>
    <string name="quick_settings_inversion_label" >"反色"</string>
    <string name="quick_settings_color_space_label" >"颜色校正模式"</string>
    <string name="quick_settings_more_settings" >"更多设置"</string>
    <string name="quick_settings_done" >"完成"</string>
    <string name="quick_settings_connected" >"已连接"</string>
    <string name="quick_settings_connected_battery_level" >"已连接，电池电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="quick_settings_connecting" >"正在连接…"</string>
    <string name="quick_settings_tethering_label" >"网络共享"</string>
    <string name="quick_settings_hotspot_label" >"热点"</string>
    <string name="quick_settings_hotspot_secondary_label_transient" >"正在开启…"</string>
    <string name="quick_settings_hotspot_secondary_label_data_saver_enabled" >"流量节省程序已开启"</string>
    <plurals name="quick_settings_hotspot_secondary_label_num_devices" formatted="false" >
      <item quantity="other">%d 台设备</item>
      <item quantity="one">%d 台设备</item>
    </plurals>
    <string name="quick_settings_notifications_label" >"通知"</string>
    <string name="quick_settings_flashlight_label" >"手电筒"</string>
    <string name="quick_settings_cellular_detail_title" >"移动数据"</string>
    <string name="quick_settings_cellular_detail_data_usage" >"流量使用情况"</string>
    <string name="quick_settings_cellular_detail_remaining_data" >"剩余流量"</string>
    <string name="quick_settings_cellular_detail_over_limit" >"超出上限"</string>
    <string name="quick_settings_cellular_detail_data_used" >"已使用<xliff:g id="DATA_USED">%s</xliff:g>"</string>
    <string name="quick_settings_cellular_detail_data_limit" >"上限为<xliff:g id="DATA_LIMIT">%s</xliff:g>"</string>
    <string name="quick_settings_cellular_detail_data_warning" >"<xliff:g id="DATA_LIMIT">%s</xliff:g>警告"</string>
    <string name="quick_settings_work_mode_label" >"工作资料"</string>
    <string name="quick_settings_night_display_label" >"夜间模式"</string>
    <string name="quick_settings_night_secondary_label_on_at_sunset" >"在日落时开启"</string>
    <string name="quick_settings_night_secondary_label_until_sunrise" >"在日出时关闭"</string>
    <string name="quick_settings_night_secondary_label_on_at" >"在<xliff:g id="TIME">%s</xliff:g> 开启"</string>
    <string name="quick_settings_secondary_label_until" >"直到<xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="quick_settings_nfc_label" >"NFC"</string>
    <string name="quick_settings_nfc_off" >"NFC 已停用"</string>
    <string name="quick_settings_nfc_on" >"NFC 已启用"</string>
    <string name="recents_empty_message" >"近期没有任何内容"</string>
    <string name="recents_empty_message_dismissed_all" >"您已清除所有内容"</string>
    <string name="recents_app_info_button_label" >"应用信息"</string>
    <string name="recents_lock_to_app_button_label" >"固定屏幕"</string>
    <string name="recents_search_bar_label" >"搜索"</string>
    <string name="recents_launch_error_message" >"无法启动<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="recents_launch_disabled_message" >"<xliff:g id="APP">%s</xliff:g>已在安全模式下停用。"</string>
    <string name="recents_stack_action_button_label" >"全部清除"</string>
    <string name="recents_drag_hint_message" >"拖动到此处即可使用分屏功能"</string>
    <string name="recents_swipe_up_onboarding" >"向上滑动可切换应用"</string>
    <string name="recents_quick_scrub_onboarding" >"向右拖动可快速切换应用"</string>
    <string name="recents_multistack_add_stack_dialog_split_horizontal" >"水平分割"</string>
    <string name="recents_multistack_add_stack_dialog_split_vertical" >"垂直分割"</string>
    <string name="recents_multistack_add_stack_dialog_split_custom" >"自定义分割"</string>
    <string name="recents_accessibility_split_screen_top" >"将屏幕分隔线移到上方"</string>
    <string name="recents_accessibility_split_screen_left" >"将屏幕分隔线移到左侧"</string>
    <string name="recents_accessibility_split_screen_right" >"将屏幕分隔线移到右侧"</string>
    <string name="quick_step_accessibility_toggle_overview" >"切换概览"</string>
    <string name="expanded_header_battery_charged" >"已充满"</string>
    <string name="expanded_header_battery_charging" >"正在充电"</string>
    <string name="expanded_header_battery_charging_with_time" >"还需<xliff:g id="CHARGING_TIME">%s</xliff:g>充满"</string>
    <string name="expanded_header_battery_not_charging" >"未在充电"</string>
    <string name="ssl_ca_cert_warning" >"网络可能会\n受到监控"</string>
    <string name="description_target_search" >"搜索"</string>
    <string name="description_direction_up" >"向上滑动以<xliff:g id="TARGET_DESCRIPTION">%s</xliff:g>。"</string>
    <string name="description_direction_left" >"向左滑动以<xliff:g id="TARGET_DESCRIPTION">%s</xliff:g>。"</string>
    <string name="zen_priority_introduction" >"您将不会受到声音和振动的打扰（闹钟、提醒、活动和所指定来电者的相关提示音除外）。您依然可以听到您选择播放的任何内容（包括音乐、视频和游戏）的相关音效。"</string>
    <string name="zen_alarms_introduction" >"您将不会受到声音和振动的打扰（闹钟提示音除外）。您依然可以听到您选择播放的任何内容（包括音乐、视频和游戏）的相关音效。"</string>
    <string name="zen_priority_customize_button" >"自定义"</string>
    <string name="zen_silence_introduction_voice" >"这会阻止所有声音和振动（包括闹钟、音乐、视频和游戏）打扰您。您仍然可以拨打电话。"</string>
    <string name="zen_silence_introduction" >"这会阻止所有声音和振动（包括闹钟、音乐、视频和游戏）打扰您。"</string>
    <string name="keyguard_more_overflow_text" >"+<xliff:g id="NUMBER_OF_NOTIFICATIONS">%d</xliff:g>"</string>
    <string name="speed_bump_explanation" >"不太紧急的通知会显示在下方"</string>
    <string name="notification_tap_again" >"再次点按即可打开"</string>
    <string name="keyguard_unlock" >"向上滑动即可解锁"</string>
    <string name="do_disclosure_generic" >"此设备由您所属单位管理"</string>
    <string name="do_disclosure_with_name" >"此设备是由<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>托管"</string>
    <string name="phone_hint" >"滑动图标即可拨打电话"</string>
    <string name="voice_hint" >"滑动图标即可打开语音助理"</string>
    <string name="camera_hint" >"滑动图标即可打开相机"</string>
    <string name="interruption_level_none_with_warning" >"完全阻止。此模式也会将屏幕阅读器静音。"</string>
    <string name="interruption_level_none" >"完全阻止"</string>
    <string name="interruption_level_priority" >"仅限优先事项"</string>
    <string name="interruption_level_alarms" >"仅限闹钟"</string>
    <string name="interruption_level_none_twoline" >"完全\n静音"</string>
    <string name="interruption_level_priority_twoline" >"仅限\n优先打扰"</string>
    <string name="interruption_level_alarms_twoline" >"仅限\n闹钟"</string>
    <string name="keyguard_indication_charging_time" >"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="keyguard_indication_charging_time_fast" >"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在快速充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="keyguard_indication_charging_time_slowly" >"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在慢速充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="accessibility_multi_user_switch_switcher" >"切换用户"</string>
    <string name="accessibility_multi_user_switch_switcher_with_current" >"切换用户，当前用户为<xliff:g id="CURRENT_USER_NAME">%s</xliff:g>"</string>
    <string name="accessibility_multi_user_switch_inactive" >"当前用户为<xliff:g id="CURRENT_USER_NAME">%s</xliff:g>"</string>
    <string name="accessibility_multi_user_switch_quick_contact" >"显示个人资料"</string>
    <string name="user_add_user" >"添加用户"</string>
    <string name="user_new_user_name" >"新用户"</string>
    <string name="guest_nickname" >"访客"</string>
    <string name="guest_new_guest" >"添加访客"</string>
    <string name="guest_exit_guest" >"移除访客"</string>
    <string name="guest_exit_guest_dialog_title" >"要移除访客吗？"</string>
    <string name="guest_exit_guest_dialog_message" >"此会话中的所有应用和数据都将被删除。"</string>
    <string name="guest_exit_guest_dialog_remove" >"移除"</string>
    <string name="guest_wipe_session_title" >"访客，欢迎回来！"</string>
    <string name="guest_wipe_session_message" >"要继续您的会话吗？"</string>
    <string name="guest_wipe_session_wipe" >"重新开始"</string>
    <string name="guest_wipe_session_dontwipe" >"是，继续"</string>
    <string name="guest_notification_title" >"访客用户"</string>
    <string name="guest_notification_text" >"要删除应用和数据，请退出访客用户身份"</string>
    <string name="guest_notification_remove_action" >"移除访客"</string>
    <string name="user_logout_notification_title" >"退出当前用户"</string>
    <string name="user_logout_notification_text" >"退出当前用户"</string>
    <string name="user_logout_notification_action" >"退出当前用户"</string>
    <string name="user_add_user_title" >"要添加新用户吗？"</string>
    <string name="user_add_user_message_short" >"当您添加新用户时，该用户必须设置自己的空间。\n\n任何用户均可为其他所有用户更新应用。"</string>
    <string name="user_remove_user_title" >"是否移除用户？"</string>
    <string name="user_remove_user_message" >"此用户的所有应用和数据均将被删除。"</string>
    <string name="user_remove_user_remove" >"移除"</string>
    <string name="battery_saver_notification_title" >"省电模式已开启"</string>
    <string name="battery_saver_notification_text" >"降低性能并限制后台流量"</string>
    <string name="battery_saver_notification_action_text" >"关闭省电模式"</string>
    <string name="media_projection_dialog_text" >"<xliff:g id="APP_SEEKING_PERMISSION">%s</xliff:g>将开始截取您的屏幕上显示的所有内容。"</string>
    <string name="media_projection_remember_text" >"不再显示"</string>
    <string name="clear_all_notifications_text" >"全部清除"</string>
    <string name="manage_notifications_text" >"管理通知"</string>
    <string name="dnd_suppressing_shade_text" >"勿扰模式暂停的通知"</string>
    <string name="media_projection_action_text" >"立即开始"</string>
    <string name="empty_shade_text" >"没有通知"</string>
    <string name="profile_owned_footer" >"资料可能会受到监控"</string>
    <string name="vpn_footer" >"网络可能会受到监控"</string>
    <string name="branded_vpn_footer" >"网络可能会受到监控"</string>
    <string name="quick_settings_disclosure_management_monitoring" >"您所在的单位会管理此设备，且可能会监控网络流量"</string>
    <string name="quick_settings_disclosure_named_management_monitoring" >"“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”会管理此设备，且可能会监控网络流量"</string>
    <string name="quick_settings_disclosure_management_named_vpn" >"设备由您所在的单位负责管理，且已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_named_management_named_vpn" >"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="VPN_APP">%2$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_management" >"设备由您所在的单位负责管理"</string>
    <string name="quick_settings_disclosure_named_management" >"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理"</string>
    <string name="quick_settings_disclosure_management_vpns" >"设备由您所在的单位负责管理，且已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_named_management_vpns" >"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理，且已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_managed_profile_monitoring" >"您所在的单位可能会监控您工作资料中的网络流量"</string>
    <string name="quick_settings_disclosure_named_managed_profile_monitoring" >"“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”可能会监控您工作资料中的网络流量"</string>
    <string name="quick_settings_disclosure_monitoring" >"网络可能会受到监控"</string>
    <string name="quick_settings_disclosure_vpns" >"设备已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_managed_profile_named_vpn" >"工作资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_personal_profile_named_vpn" >"个人资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_named_vpn" >"设备已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="monitoring_title_device_owned" >"设备管理"</string>
    <string name="monitoring_title_profile_owned" >"资料监控"</string>
    <string name="monitoring_title" >"网络监控"</string>
    <string name="monitoring_subtitle_vpn" >"VPN"</string>
    <string name="monitoring_subtitle_network_logging" >"网络日志"</string>
    <string name="monitoring_subtitle_ca_certificate" >"CA 证书"</string>
    <string name="disable_vpn" >"关闭VPN"</string>
    <string name="disconnect_vpn" >"断开VPN连接"</string>
    <string name="monitoring_button_view_policies" >"查看政策"</string>
    <string name="monitoring_description_named_management" >"您的设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理。\n\n您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。\n\n要了解详情，请与您的管理员联系。"</string>
    <string name="monitoring_description_management" >"您的设备由贵单位负责管理。\n\n您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。\n\n要了解详情，请与您的管理员联系。"</string>
    <string name="monitoring_description_management_ca_certificate" >"您所在的单位已在此设备上安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_managed_profile_ca_certificate" >"您所在的单位已为您的工作资料安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_ca_certificate" >"此设备上已安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_management_network_logging" >"您的管理员已开启网络日志功能（该功能会监控您设备上的流量）。"</string>
    <string name="monitoring_description_named_vpn" >"您已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_two_named_vpns" >"您已连接到“<xliff:g id="VPN_APP_0">%1$s</xliff:g>”和“<xliff:g id="VPN_APP_1">%2$s</xliff:g>”（这两个应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_managed_profile_named_vpn" >"您的工作资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_personal_profile_named_vpn" >"您的个人资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_do_header_generic" >"您的设备由<xliff:g id="DEVICE_OWNER_APP">%1$s</xliff:g>管理。"</string>
    <string name="monitoring_description_do_header_with_name" >"<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>会使用<xliff:g id="DEVICE_OWNER_APP">%2$s</xliff:g>管理您的设备。"</string>
    <string name="monitoring_description_do_body" >"您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。"</string>
    <string name="monitoring_description_do_learn_more_separator" >" "</string>
    <string name="monitoring_description_do_learn_more" >"了解详情"</string>
    <string name="monitoring_description_do_body_vpn" >"您已连接到<xliff:g id="VPN_APP">%1$s</xliff:g>，该应用可以监控您的网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_vpn_settings_separator" >" "</string>
    <string name="monitoring_description_vpn_settings" >"打开 VPN 设置"</string>
    <string name="monitoring_description_ca_cert_settings_separator" >" "</string>
    <string name="monitoring_description_ca_cert_settings" >"打开可信凭据列表"</string>
    <string name="monitoring_description_network_logging" >"您的管理员已开启网络日志功能，该功能会监控您设备上的流量。\n\n如需更多信息，请与您的管理员联系。"</string>
    <string name="monitoring_description_vpn" >"您已授权应用设置 VPN 连接。\n\n该应用可以监控您的设备和网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_vpn_profile_owned" >"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”管理。\n\n您的管理员能够监控您的网络活动，其中包括收发电子邮件、使用应用和访问网站。\n\n如需更多信息，请与您的管理员联系。\n\n此外，您还连接到了 VPN，它同样可以监控您的网络活动。"</string>
    <string name="legacy_vpn_name" >"VPN"</string>
    <string name="monitoring_description_app" >"您已连接到“<xliff:g id="APPLICATION">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_app_personal" >"您已连接到<xliff:g id="APPLICATION">%1$s</xliff:g>，该应用可以监控您的个人网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="branded_monitoring_description_app_personal" >"您已连接到<xliff:g id="APPLICATION">%1$s</xliff:g>，该应用可以监控您的个人网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_app_work" >"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="APPLICATION">%2$s</xliff:g>”（该应用能够监控您的工作网络活动，其中包括收发电子邮件、使用应用和浏览网站）。\n\n如需更多信息，请与您的管理员联系。"</string>
    <string name="monitoring_description_app_personal_work" >"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="APPLICATION_WORK">%2$s</xliff:g>”（该应用能够监控您的工作网络活动，其中包括收发电子邮件、使用应用和浏览网站）。\n\n此外，您还连接到了“<xliff:g id="APPLICATION_PERSONAL">%3$s</xliff:g>”（该应用能够监控您的个人网络活动）。"</string>
    <string name="keyguard_indication_trust_granted" >"已为<xliff:g id="USER_NAME">%1$s</xliff:g>解锁"</string>
    <string name="keyguard_indication_trust_managed" >"“<xliff:g id="TRUST_AGENT">%1$s</xliff:g>”正在运行"</string>
    <string name="keyguard_indication_trust_disabled" >"在您手动解锁之前，设备会保持锁定状态"</string>
    <string name="hidden_notifications_title" >"更快捷地查看通知"</string>
    <string name="hidden_notifications_text" >"无需解锁即可查看通知"</string>
    <string name="hidden_notifications_cancel" >"不用了"</string>
    <string name="hidden_notifications_setup" >"设置"</string>
    <string name="zen_mode_and_condition" >"<xliff:g id="ZEN_MODE">%1$s</xliff:g>（<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>）"</string>
    <string name="volume_zen_end_now" >"立即关闭"</string>
    <string name="accessibility_volume_settings" >"声音设置"</string>
    <string name="accessibility_volume_expand" >"展开"</string>
    <string name="accessibility_volume_collapse" >"收起"</string>
    <string name="accessibility_output_chooser" >"切换输出设备"</string>
    <string name="screen_pinning_title" >"已固定屏幕"</string>
    <string name="screen_pinning_description" >"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“返回”和“概览”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_recents_invisible" >"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“返回”和“主屏幕”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_accessible" >"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“概览”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_recents_invisible_accessible" >"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“主屏幕”即可取消固定屏幕。"</string>
    <string name="screen_pinning_toast" >"要取消固定此屏幕，请触摸并按住“返回”和“概览”按钮"</string>
    <string name="screen_pinning_toast_recents_invisible" >"要取消固定此屏幕，请触摸并按住“返回”和“主屏幕”按钮"</string>
    <string name="screen_pinning_positive" >"知道了"</string>
    <string name="screen_pinning_negative" >"不用了"</string>
    <string name="screen_pinning_start" >"已固定屏幕"</string>
    <string name="screen_pinning_exit" >"已取消固定屏幕"</string>
    <string name="quick_settings_reset_confirmation_title" >"要隐藏“<xliff:g id="TILE_LABEL">%1$s</xliff:g>”吗？"</string>
    <string name="quick_settings_reset_confirmation_message" >"下次在设置中将其开启后，此快捷设置条目将会重新显示。"</string>
    <string name="quick_settings_reset_confirmation_button" >"隐藏"</string>
    <string name="managed_profile_foreground_toast" >"您当前正在使用工作资料"</string>
    <string name="stream_voice_call" >"通话"</string>
    <string name="stream_system" >"系统"</string>
    <string name="stream_ring" >"铃声"</string>
    <string name="stream_music" >"媒体"</string>
    <string name="stream_alarm" >"闹钟"</string>
    <string name="stream_notification" >"通知"</string>
    <string name="stream_bluetooth_sco" >"蓝牙"</string>
    <string name="stream_dtmf" >"双音多频"</string>
    <string name="stream_accessibility" >"无障碍"</string>
    <string name="ring_toggle_title" >"通话"</string>
    <string name="volume_ringer_status_normal" >"响铃"</string>
    <string name="volume_ringer_status_vibrate" >"振动"</string>
    <string name="volume_ringer_status_silent" >"静音"</string>
    <string name="qs_status_phone_vibrate" >"手机已设为振动"</string>
    <string name="qs_status_phone_muted" >"手机已设为静音"</string>
    <string name="volume_stream_content_description_unmute" >"%1$s。点按即可取消静音。"</string>
    <string name="volume_stream_content_description_vibrate" >"%1$s。点按即可设为振动，但可能会同时将无障碍服务设为静音。"</string>
    <string name="volume_stream_content_description_mute" >"%1$s。点按即可设为静音，但可能会同时将无障碍服务设为静音。"</string>
    <string name="volume_stream_content_description_vibrate_a11y" >"%1$s。点按即可设为振动。"</string>
    <string name="volume_stream_content_description_mute_a11y" >"%1$s。点按即可设为静音。"</string>
    <string name="volume_ringer_hint_mute" >"静音"</string>
    <string name="volume_ringer_hint_unmute" >"取消静音"</string>
    <string name="volume_ringer_hint_vibrate" >"振动"</string>
    <string name="volume_dialog_title" >"%s音量控件"</string>
    <string name="volume_dialog_ringer_guidance_ring" >"有来电和通知时会响铃 (<xliff:g id="VOLUME_LEVEL">%1$s</xliff:g>)"</string>
    <string name="output_title" >"媒体输出"</string>
    <string name="output_calls_title" >"通话输出"</string>
    <string name="output_none_found" >"未找到任何设备"</string>
    <string name="output_none_found_service_off" >"未找到任何设备。请尝试开启<xliff:g id="SERVICE">%1$s</xliff:g>"</string>
    <string name="output_service_bt" >"蓝牙"</string>
    <string name="output_service_wifi" >"WLAN"</string>
    <string name="output_service_bt_wifi" >"蓝牙和 WLAN"</string>
    <string name="system_ui_tuner" >"系统界面调节工具"</string>
    <string name="show_battery_percentage" >"嵌入式显示电池电量百分比 显示嵌入的电池电量百分比"</string>
    <string name="show_battery_percentage_summary" >"未充电时在状态栏图标内显示电池电量百分比"</string>
    <string name="quick_settings" >"快捷设置"</string>
    <string name="status_bar" >"状态栏"</string>
    <string name="overview" >"概览"</string>
    <string name="demo_mode" >"系统界面演示模式"</string>
    <string name="enable_demo_mode" >"启用演示模式"</string>
    <string name="show_demo_mode" >"显示演示模式"</string>
    <string name="status_bar_ethernet" >"以太网"</string>
    <string name="status_bar_alarm" >"闹钟"</string>
    <string name="status_bar_work" >"工作资料"</string>
    <string name="status_bar_airplane" >"飞行模式"</string>
    <string name="add_tile" >"添加图块"</string>
    <string name="broadcast_tile" >"播送图块"</string>
    <string name="zen_alarm_warning_indef" >"您在<xliff:g id="WHEN">%1$s</xliff:g>将不会听到下次闹钟响铃，除非您在该时间之前关闭此模式"</string>
    <string name="zen_alarm_warning" >"您在<xliff:g id="WHEN">%1$s</xliff:g>将不会听到下次闹钟响铃"</string>
    <string name="alarm_template" >"<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="alarm_template_far" >"<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="accessibility_quick_settings_detail" >"快捷设置，<xliff:g id="TITLE">%s</xliff:g>。"</string>
    <string name="accessibility_status_bar_hotspot" >"热点"</string>
    <string name="accessibility_managed_profile" >"工作资料"</string>
    <string name="tuner_warning_title" >"并不适合所有用户"</string>
    <string name="tuner_warning" >"系统界面调节工具可让您以更多方式调整及定制 Android 界面。在日后推出的版本中，这些实验性功能可能会变更、失效或消失。操作时请务必谨慎。"</string>
    <string name="tuner_persistent_warning" >"在日后推出的版本中，这些实验性功能可能会变更、失效或消失。操作时请务必谨慎。"</string>
    <string name="got_it" >"知道了"</string>
    <string name="tuner_toast" >"恭喜！系统界面调节工具已添加到“设置”中"</string>
    <string name="remove_from_settings" >"从“设置”中移除"</string>
    <string name="remove_from_settings_prompt" >"要从“设置”中移除系统界面调节工具，并停止使用所有相关功能吗？"</string>
    <string name="activity_not_found" >"您的设备中未安装此应用"</string>
    <string name="clock_seconds" >"显示时钟的秒数"</string>
    <string name="clock_seconds_desc" >"在状态栏中显示时钟的秒数。这可能会影响电池的续航时间。"</string>
    <string name="qs_rearrange" >"重新排列快捷设置"</string>
    <string name="show_brightness" >"在快捷设置中显示亮度栏"</string>
    <string name="experimental" >"实验性功能"</string>
    <string name="enable_bluetooth_title" >"要开启蓝牙吗？"</string>
    <string name="enable_bluetooth_message" >"要将您的键盘连接到平板电脑，您必须先开启蓝牙。"</string>
    <string name="enable_bluetooth_confirmation_ok" >"开启"</string>
    <string name="show_silently" >"显示通知，但不发出提示音"</string>
    <string name="block" >"屏蔽所有通知"</string>
    <string name="do_not_silence" >"不静音"</string>
    <string name="do_not_silence_block" >"不静音也不屏蔽"</string>
    <string name="tuner_full_importance_settings" >"高级通知设置"</string>
    <string name="tuner_full_importance_settings_on" >"开启"</string>
    <string name="tuner_full_importance_settings_off" >"关闭"</string>
    <string name="power_notification_controls_description" >"利用高级通知设置，您可以为应用通知设置从 0 级到 5 级的重要程度等级。\n\n"<b>"5 级"</b>" \n- 在通知列表顶部显示 \n- 允许全屏打扰 \n- 一律短暂显示通知 \n\n"<b>"4 级"</b>" \n- 禁止全屏打扰 \n- 一律短暂显示通知 \n\n"<b>"3 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n\n"<b>"2 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n- 一律不发出声音或振动 \n\n"<b>"1 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n- 一律不发出声音或振动 \n- 不在锁定屏幕和状态栏中显示 \n- 在通知列表底部显示 \n\n"<b>"0 级"</b>" \n- 屏蔽应用的所有通知"</string>
    <string name="notification_header_default_channel" >"通知"</string>
    <string name="notification_channel_disabled" >"您将不会再看到这些通知"</string>
    <string name="notification_channel_minimized" >"系统将会最小化这些通知"</string>
    <string name="inline_blocking_helper" >"您通常会关闭这些通知。\n是否继续显示通知？"</string>
    <string name="inline_keep_showing" >"要继续显示这些通知吗？"</string>
    <string name="inline_stop_button" >"停止通知"</string>
    <string name="inline_keep_button" >"继续显示"</string>
    <string name="inline_minimize_button" >"最小化"</string>
    <string name="inline_keep_showing_app" >"要继续显示来自此应用的通知吗？"</string>
    <string name="notification_unblockable_desc" >"无法关闭这些通知"</string>
    <string name="appops_camera" >"此应用正在使用摄像头。"</string>
    <string name="appops_microphone" >"此应用正在使用麦克风。"</string>
    <string name="appops_overlay" >"此应用正显示在屏幕上其他应用的上层。"</string>
    <string name="appops_camera_mic" >"此应用正在使用麦克风和摄像头。"</string>
    <string name="appops_camera_overlay" >"此应用正显示在屏幕上其他应用的上层，并且正在使用摄像头。"</string>
    <string name="appops_mic_overlay" >"此应用正显示在屏幕上其他应用的上层，并且正在使用麦克风。"</string>
    <string name="appops_camera_mic_overlay" >"此应用正显示在屏幕上其他应用的上层，并且正在使用麦克风和摄像头。"</string>
    <string name="notification_appops_settings" >"设置"</string>
    <string name="notification_appops_ok" >"确定"</string>
    <string name="notification_channel_controls_opened_accessibility" >"<xliff:g id="APP_NAME">%1$s</xliff:g>的通知控件已打开"</string>
    <string name="notification_channel_controls_closed_accessibility" >"<xliff:g id="APP_NAME">%1$s</xliff:g>的通知控件已关闭"</string>
    <string name="notification_channel_switch_accessibility" >"允许接收来自此频道的通知"</string>
    <string name="notification_more_settings" >"更多设置"</string>
    <string name="notification_app_settings" >"自定义"</string>
    <string name="notification_done" >"完成"</string>
    <string name="inline_undo" >"撤消"</string>
    <string name="notification_menu_accessibility" >"<xliff:g id="APP_NAME">%1$s</xliff:g><xliff:g id="MENU_DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="notification_menu_gear_description" >"通知设置"</string>
    <string name="notification_menu_snooze_description" >"通知延后选项"</string>
    <string name="notification_menu_snooze_action" >"延后"</string>
    <string name="snooze_undo" >"撤消"</string>
    <string name="snoozed_for_time" >"已延后 <xliff:g id="TIME_AMOUNT">%1$s</xliff:g>"</string>
    <plurals name="snoozeHourOptions" formatted="false" >
      <item quantity="other">%d 小时</item>
      <item quantity="one">%d 小时</item>
    </plurals>
    <plurals name="snoozeMinuteOptions" formatted="false" >
      <item quantity="other">%d 分钟</item>
      <item quantity="one">%d 分钟</item>
    </plurals>
    <string name="battery_panel_title" >"电池使用情况"</string>
    <string name="battery_detail_charging_summary" >"充电过程中无法使用省电模式"</string>
    <string name="battery_detail_switch_title" >"省电模式"</string>
    <string name="battery_detail_switch_summary" >"降低性能并限制后台流量"</string>
    <string name="keyboard_key_button_template" >"<xliff:g id="NAME">%1$s</xliff:g>按钮"</string>
    <string name="keyboard_key_home" >"Home"</string>
    <string name="keyboard_key_back" >"返回"</string>
    <string name="keyboard_key_dpad_up" >"向上"</string>
    <string name="keyboard_key_dpad_down" >"向下"</string>
    <string name="keyboard_key_dpad_left" >"向左"</string>
    <string name="keyboard_key_dpad_right" >"向右"</string>
    <string name="keyboard_key_dpad_center" >"中心"</string>
    <string name="keyboard_key_tab" >"Tab"</string>
    <string name="keyboard_key_space" >"空格"</string>
    <string name="keyboard_key_enter" >"Enter"</string>
    <string name="keyboard_key_backspace" >"退格"</string>
    <string name="keyboard_key_media_play_pause" >"播放/暂停"</string>
    <string name="keyboard_key_media_stop" >"停止"</string>
    <string name="keyboard_key_media_next" >"下一曲"</string>
    <string name="keyboard_key_media_previous" >"上一曲"</string>
    <string name="keyboard_key_media_rewind" >"快退"</string>
    <string name="keyboard_key_media_fast_forward" >"快进"</string>
    <string name="keyboard_key_page_up" >"向上翻页"</string>
    <string name="keyboard_key_page_down" >"PgDn"</string>
    <string name="keyboard_key_forward_del" >"删除"</string>
    <string name="keyboard_key_move_home" >"Home"</string>
    <string name="keyboard_key_move_end" >"End"</string>
    <string name="keyboard_key_insert" >"Insert"</string>
    <string name="keyboard_key_num_lock" >"Num Lock"</string>
    <string name="keyboard_key_numpad_template" >"数字键盘 <xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="keyboard_shortcut_group_system" >"系统"</string>
    <string name="keyboard_shortcut_group_system_home" >"主屏幕"</string>
    <string name="keyboard_shortcut_group_system_recents" >"最近"</string>
    <string name="keyboard_shortcut_group_system_back" >"返回"</string>
    <string name="keyboard_shortcut_group_system_notifications" >"通知"</string>
    <string name="keyboard_shortcut_group_system_shortcuts_helper" >"键盘快捷键"</string>
    <string name="keyboard_shortcut_group_system_switch_input" >"切换输入法"</string>
    <string name="keyboard_shortcut_group_applications" >"应用"</string>
    <string name="keyboard_shortcut_group_applications_assist" >"助手应用"</string>
    <string name="keyboard_shortcut_group_applications_browser" >"浏览器"</string>
    <string name="keyboard_shortcut_group_applications_contacts" >"通讯录"</string>
    <string name="keyboard_shortcut_group_applications_email" >"电子邮件"</string>
    <string name="keyboard_shortcut_group_applications_sms" >"短信"</string>
    <string name="keyboard_shortcut_group_applications_music" >"音乐"</string>
    <string name="keyboard_shortcut_group_applications_youtube" >"YouTube"</string>
    <string name="keyboard_shortcut_group_applications_calendar" >"日历"</string>
    <string name="tuner_full_zen_title" >"与音量控件一起显示"</string>
    <string name="volume_and_do_not_disturb" >"勿扰"</string>
    <string name="volume_dnd_silent" >"音量按钮快捷键"</string>
    <string name="volume_up_silent" >"按音量调高键时退出勿扰模式"</string>
    <string name="battery" >"电池"</string>
    <string name="clock" >"时钟"</string>
    <string name="headset" >"耳机"</string>
    <string name="accessibility_long_click_tile" >"打开“设置”"</string>
    <string name="accessibility_status_bar_headphones" >"已连接到耳机"</string>
    <string name="accessibility_status_bar_headset" >"已连接到耳机"</string>
    <string name="data_saver" >"流量节省程序"</string>
    <string name="accessibility_data_saver_on" >"流量节省程序已开启"</string>
    <string name="accessibility_data_saver_off" >"流量节省程序已关闭"</string>
    <string name="switch_bar_on" >"开启"</string>
    <string name="switch_bar_off" >"关闭"</string>
    <string name="nav_bar" >"导航栏"</string>
    <string name="nav_bar_layout" >"布局"</string>
    <string name="left_nav_bar_button_type" >"其他向左按钮类型"</string>
    <string name="right_nav_bar_button_type" >"其他向右按钮类型"</string>
    <string name="nav_bar_default" >"（默认）"</string>
  <string-array name="nav_bar_buttons">
    <item >"剪贴板"</item>
    <item >"键码"</item>
    <item >"确认旋转、键盘切换器"</item>
    <item >"无"</item>
  </string-array>
  <string-array name="nav_bar_layouts">
    <item >"一般"</item>
    <item >"紧凑"</item>
    <item >"靠左"</item>
    <item >"靠右"</item>
  </string-array>
    <string name="menu_ime" >"键盘切换器"</string>
    <string name="save" >"保存"</string>
    <string name="reset" >"重置"</string>
    <string name="adjust_button_width" >"调整按钮宽度"</string>
    <string name="clipboard" >"剪贴板"</string>
    <string name="accessibility_key" >"自定义导航按钮"</string>
    <string name="left_keycode" >"向左键码"</string>
    <string name="right_keycode" >"向右键码"</string>
    <string name="left_icon" >"向左图标"</string>
    <string name="right_icon" >"向右图标"</string>
    <string name="drag_to_add_tiles" >"按住并拖动即可添加图块"</string>
    <string name="drag_to_remove_tiles" >"拖动到此处即可移除"</string>
    <string name="drag_to_remove_disabled" >"您至少需要 6 个图块"</string>
    <string name="qs_edit" >"编辑"</string>
    <string name="tuner_time" >"时间"</string>
  <string-array name="clock_options">
    <item >"显示小时、分钟和秒"</item>
    <item >"显示小时和分钟（默认）"</item>
    <item >"不显示此图标"</item>
  </string-array>
  <string-array name="battery_options">
    <item >"一律显示百分比"</item>
    <item >"充电时显示百分比（默认）"</item>
    <item >"不显示此图标"</item>
  </string-array>
    <string name="other" >"其他"</string>
    <string name="accessibility_divider" >"分屏分隔线"</string>
    <string name="accessibility_action_divider_left_full" >"左侧全屏"</string>
    <string name="accessibility_action_divider_left_70" >"左侧 70%"</string>
    <string name="accessibility_action_divider_left_50" >"左侧 50%"</string>
    <string name="accessibility_action_divider_left_30" >"左侧 30%"</string>
    <string name="accessibility_action_divider_right_full" >"右侧全屏"</string>
    <string name="accessibility_action_divider_top_full" >"顶部全屏"</string>
    <string name="accessibility_action_divider_top_70" >"顶部 70%"</string>
    <string name="accessibility_action_divider_top_50" >"顶部 50%"</string>
    <string name="accessibility_action_divider_top_30" >"顶部 30%"</string>
    <string name="accessibility_action_divider_bottom_full" >"底部全屏"</string>
    <string name="accessibility_qs_edit_tile_label" >"位置 <xliff:g id="POSITION">%1$d</xliff:g>，<xliff:g id="TILE_NAME">%2$s</xliff:g>。点按两次即可修改。"</string>
    <string name="accessibility_qs_edit_add_tile_label" >"<xliff:g id="TILE_NAME">%1$s</xliff:g>。点按两次即可添加。"</string>
    <string name="accessibility_qs_edit_position_label" >"位置 <xliff:g id="POSITION">%1$d</xliff:g>。点按两次即可选择。"</string>
    <string name="accessibility_qs_edit_move_tile" >"移动<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_remove_tile" >"移除<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_added" >"已将<xliff:g id="TILE_NAME">%1$s</xliff:g>添加到位置 <xliff:g id="POSITION">%2$d</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_removed" >"已移除<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_moved" >"已将<xliff:g id="TILE_NAME">%1$s</xliff:g>移至位置 <xliff:g id="POSITION">%2$d</xliff:g>"</string>
    <string name="accessibility_desc_quick_settings_edit" >"快捷设置编辑器。"</string>
    <string name="accessibility_desc_notification_icon" >"<xliff:g id="ID_1">%1$s</xliff:g>通知：<xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="dock_forced_resizable" >"应用可能无法在分屏模式下正常运行。"</string>
    <string name="dock_non_resizeble_failed_to_dock_text" >"应用不支持分屏。"</string>
    <string name="forced_resizable_secondary_display" >"应用可能无法在辅显示屏上正常运行。"</string>
    <string name="activity_launch_on_secondary_display_failed_text" >"应用不支持在辅显示屏上启动。"</string>
    <string name="accessibility_quick_settings_settings" >"打开设置。"</string>
    <string name="accessibility_quick_settings_expand" >"开启快捷设置。"</string>
    <string name="accessibility_quick_settings_collapse" >"关闭快捷设置。"</string>
    <string name="accessibility_quick_settings_alarm_set" >"已设置闹钟。"</string>
    <string name="accessibility_quick_settings_user" >"目前登录的用户名为<xliff:g id="ID_1">%s</xliff:g>"</string>
    <string name="data_connection_no_internet" >"未连接到互联网"</string>
    <string name="accessibility_quick_settings_open_details" >"打开详情页面。"</string>
    <string name="accessibility_quick_settings_open_settings" >"打开<xliff:g id="ID_1">%s</xliff:g>设置。"</string>
    <string name="accessibility_quick_settings_edit" >"修改设置顺序。"</string>
    <string name="accessibility_quick_settings_page" >"第 <xliff:g id="ID_1">%1$d</xliff:g> 页，共 <xliff:g id="ID_2">%2$d</xliff:g> 页"</string>
    <string name="tuner_lock_screen" >"锁定屏幕"</string>
    <string name="pip_phone_expand" >"展开"</string>
    <string name="pip_phone_minimize" >"最小化"</string>
    <string name="pip_phone_close" >"关闭"</string>
    <string name="pip_phone_settings" >"设置"</string>
    <string name="pip_phone_dismiss_hint" >"向下拖动即可关闭"</string>
    <string name="pip_menu_title" >"菜单"</string>
    <string name="pip_notification_title" >"<xliff:g id="NAME">%s</xliff:g>目前位于“画中画”中"</string>
    <string name="pip_notification_message" >"如果您不想让“<xliff:g id="NAME">%s</xliff:g>”使用此功能，请点按以打开设置，然后关闭此功能。"</string>
    <string name="pip_play" >"播放"</string>
    <string name="pip_pause" >"暂停"</string>
    <string name="pip_skip_to_next" >"跳到下一个"</string>
    <string name="pip_skip_to_prev" >"跳到上一个"</string>
    <string name="thermal_shutdown_title" >"手机因严重发热而自动关机"</string>
    <string name="thermal_shutdown_message" >"现在，您的手机已恢复正常运行"</string>
    <string name="thermal_shutdown_dialog_message" >"由于发热严重，因此您的手机执行了自动关机以降温。现在，您的手机已恢复正常运行。\n\n以下情况可能会导致您的手机严重发热：\n • 使用占用大量资源的应用（例如游戏、视频或导航应用）\n • 下载或上传大型文件\n • 在高温环境下使用手机"</string>
    <string name="high_temp_title" >"手机温度上升中"</string>
    <string name="high_temp_notif_message" >"手机降温时，部分功能的使用会受限制"</string>
    <string name="high_temp_dialog_message" >"您的手机将自动尝试降温。您依然可以使用您的手机，但是手机运行速度可能会更慢。\n\n手机降温后，就会恢复正常的运行速度。"</string>
    <string name="lockscreen_shortcut_left" >"向左快捷方式"</string>
    <string name="lockscreen_shortcut_right" >"向右快捷方式"</string>
    <string name="lockscreen_unlock_left" >"向左快捷方式也可以解锁设备"</string>
    <string name="lockscreen_unlock_right" >"向右快捷方式也可以解锁设备"</string>
    <string name="lockscreen_none" >"无"</string>
    <string name="tuner_launch_app" >"启动<xliff:g id="APP">%1$s</xliff:g>"</string>
    <string name="tuner_other_apps" >"其他应用"</string>
    <string name="tuner_circle" >"圆形"</string>
    <string name="tuner_plus" >"加号"</string>
    <string name="tuner_minus" >"减号"</string>
    <string name="tuner_left" >"向左"</string>
    <string name="tuner_right" >"向右"</string>
    <string name="tuner_menu" >"菜单"</string>
    <string name="tuner_app" >"<xliff:g id="APP">%1$s</xliff:g>应用"</string>
    <string name="notification_channel_alerts" >"提醒"</string>
    <string name="notification_channel_battery" >"电池"</string>
    <string name="notification_channel_screenshot" >"屏幕截图"</string>
    <string name="notification_channel_general" >"常规消息"</string>
    <string name="notification_channel_storage" >"存储空间"</string>
    <string name="notification_channel_hints" >"提示"</string>
    <string name="instant_apps" >"免安装应用"</string>
    <string name="instant_apps_message" >"免安装应用无需安装就能使用。"</string>
    <string name="app_info" >"应用信息"</string>
    <string name="go_to_web" >"转到浏览器"</string>
    <string name="mobile_data" >"移动数据"</string>
    <string name="mobile_data_text_format" >"<xliff:g id="ID_1">%s</xliff:g> - <xliff:g id="ID_2">%s</xliff:g>"</string>
    <string name="wifi_is_off" >"WLAN 已关闭"</string>
    <string name="bt_is_off" >"蓝牙已关闭"</string>
    <string name="dnd_is_off" >"“勿扰”模式已关闭"</string>
    <string name="qs_dnd_prompt_auto_rule" >"某个自动规则（<xliff:g id="ID_1">%s</xliff:g>）已开启勿扰模式。"</string>
    <string name="qs_dnd_prompt_app" >"某个应用（<xliff:g id="ID_1">%s</xliff:g>）已开启勿扰模式。"</string>
    <string name="qs_dnd_prompt_auto_rule_app" >"某个自动规则或应用已开启勿扰模式。"</string>
    <string name="qs_dnd_until" >"直到<xliff:g id="ID_1">%s</xliff:g>"</string>
    <string name="qs_dnd_keep" >"保留"</string>
    <string name="qs_dnd_replace" >"替换"</string>
    <string name="running_foreground_services_title" >"在后台运行的应用"</string>
    <string name="running_foreground_services_msg" >"点按即可详细了解电量和流量消耗情况"</string>
    <string name="mobile_data_disable_title" >"要关闭移动数据网络吗？"</string>
    <string name="mobile_data_disable_message" >"您将无法通过<xliff:g id="CARRIER">%s</xliff:g>获取移动数据访问权限或连接到互联网。您只能通过 WLAN 连接到互联网。"</string>
    <string name="mobile_data_disable_message_default_carrier" >"您的运营商"</string>
    <string name="touch_filtered_warning" >"由于某个应用遮挡了权限请求界面，因此“设置”应用无法验证您的回应。"</string>
    <string name="slice_permission_title" >"要允许“<xliff:g id="APP_0">%1$s</xliff:g>”显示“<xliff:g id="APP_2">%2$s</xliff:g>”图块吗？"</string>
    <string name="slice_permission_text_1" >"- 可以读取“<xliff:g id="APP">%1$s</xliff:g>”中的信息"</string>
    <string name="slice_permission_text_2" >"- 可以在“<xliff:g id="APP">%1$s</xliff:g>”内执行操作"</string>
    <string name="slice_permission_checkbox" >"允许“<xliff:g id="APP">%1$s</xliff:g>”显示任何应用的图块"</string>
    <string name="slice_permission_allow" >"允许"</string>
    <string name="slice_permission_deny" >"拒绝"</string>
    <string name="auto_saver_title" >"点按即可预设省电模式"</string>
    <string name="auto_saver_text" >"当电池电量剩余 <xliff:g id="PERCENTAGE">%d</xliff:g>%% 时自动开启"</string>
    <string name="no_auto_saver_action" >"不用了"</string>
    <string name="auto_saver_enabled_title" >"预设的省电模式已开启"</string>
    <string name="auto_saver_enabled_text" >"一旦电池电量降到 <xliff:g id="PERCENTAGE">%d</xliff:g>%% 以下，省电模式就会自动开启。"</string>
    <string name="open_saver_setting_action" >"设置"</string>
    <string name="auto_saver_okay_action" >"知道了"</string>
    <string name="heap_dump_tile_name" >"转储 SysUI 堆"</string>
</resources>


```

# frameworks/base/packages/SettingsLib

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/frameworks/base/packages/SettingsLib/res/values-zh-rCN/strings.xml
frameworks_base_packages_SettingsLib_strings_cn.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="wifi_fail_to_scan" >"无法扫描网络"</string>
    <string name="wifi_security_none" >"无"</string>
    <string name="wifi_remembered" >"已保存"</string>
    <string name="wifi_disabled_generic" >"已停用"</string>
    <string name="wifi_disabled_network_failure" >"IP 配置失败"</string>
    <string name="wifi_disabled_by_recommendation_provider" >"网络质量较差，因此未连接"</string>
    <string name="wifi_disabled_wifi_failure" >"WLAN 连接失败"</string>
    <string name="wifi_disabled_password_failure" >"身份验证出现问题"</string>
    <string name="wifi_cant_connect" >"无法连接"</string>
    <string name="wifi_cant_connect_to_ap" >"无法连接到“<xliff:g id="AP_NAME">%1$s</xliff:g>”"</string>
    <string name="wifi_check_password_try_again" >"请检查密码，然后重试"</string>
    <string name="wifi_not_in_range" >"不在范围内"</string>
    <string name="wifi_no_internet_no_reconnect" >"无法自动连接"</string>
    <string name="wifi_no_internet" >"无法访问互联网"</string>
    <string name="saved_network" >"由“<xliff:g id="NAME">%1$s</xliff:g>”保存"</string>
    <string name="connected_via_network_scorer" >"已通过%1$s自动连接"</string>
    <string name="connected_via_network_scorer_default" >"已自动连接（通过网络评分服务提供方）"</string>
    <string name="connected_via_passpoint" >"已通过%1$s连接"</string>
    <string name="available_via_passpoint" >"可通过%1$s连接"</string>


    <string name="wifi_connected_no_internet" >"已连接，但无法访问互联网"</string>
    <!-- Summary for Connected wifi network without internet -->
    <string name="wifi_connected_no_internet">Connected, no internet</string>



    <string name="wifi_status_no_internet" >"无法访问互联网"</string>
    <!-- Wi-Fi status indicating that the current network is connected, but has no internet access. -->
    <string name="wifi_status_no_internet">No internet</string>


    <string name="wifi_status_sign_in_required" >"必须登录"</string>
    <!-- Wi-Fi status indicating that the current network is connected requires sign in to access the internet. -->
    <string name="wifi_status_sign_in_required">Sign in required</string>

    <string name="wifi_ap_unable_to_handle_new_sta" >"接入点暂时满载"</string>
    <string name="connected_via_carrier" >"已通过%1$s连接"</string>
    <string name="available_via_carrier" >"可通过%1$s连接"</string>
    <string name="speed_label_very_slow" >"很慢"</string>
    <string name="speed_label_slow" >"慢"</string>
    <string name="speed_label_okay" >"良好"</string>
    <string name="speed_label_medium" >"适中"</string>
    <string name="speed_label_fast" >"快"</string>
    <string name="speed_label_very_fast" >"很快"</string>
    <string name="preference_summary_default_combination" >"<xliff:g id="STATE">%1$s</xliff:g>/<xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="bluetooth_disconnected" >"已断开连接"</string>
    <string name="bluetooth_disconnecting" >"正在断开连接..."</string>
    <string name="bluetooth_connecting" >"正在连接..."</string>
    <string name="bluetooth_connected" >"已连接<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_pairing" >"正在配对..."</string>
    <string name="bluetooth_connected_no_headset" >"已连接（无手机信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_a2dp" >"已连接（无媒体信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_map" >"已连接（无消息访问权限）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_no_a2dp" >"已连接（无手机或媒体信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_battery_level" >"已连接，电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_battery_level" >"已连接（无手机信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_a2dp_battery_level" >"已连接（无媒体信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_no_a2dp_battery_level" >"已连接（无手机或媒体信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_active_battery_level" >"使用中，电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_battery_level" >"电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_active_no_battery_level" >"使用中"</string>
    <string name="bluetooth_profile_a2dp" >"媒体音频"</string>
    <string name="bluetooth_profile_headset" >"通话"</string>
    <string name="bluetooth_profile_opp" >"文件传输"</string>
    <string name="bluetooth_profile_hid" >"输入设备"</string>
    <string name="bluetooth_profile_pan" >"互联网连接"</string>
    <string name="bluetooth_profile_pbap" >"共享联系人"</string>
    <string name="bluetooth_profile_pbap_summary" >"用于共享联系人"</string>
    <string name="bluetooth_profile_pan_nap" >"共享互联网连接"</string>
    <string name="bluetooth_profile_map" >"短信"</string>
    <string name="bluetooth_profile_sap" >"SIM 卡存取权限"</string>
    <string name="bluetooth_profile_a2dp_high_quality" >"HD 音频：<xliff:g id="CODEC_NAME">%1$s</xliff:g>"</string>
    <string name="bluetooth_profile_a2dp_high_quality_unknown_codec" >"HD 音频"</string>
    <string name="bluetooth_profile_hearing_aid" >"助听器"</string>
    <string name="bluetooth_hearing_aid_profile_summary_connected" >"已连接到助听器"</string>
    <string name="bluetooth_a2dp_profile_summary_connected" >"已连接到媒体音频"</string>
    <string name="bluetooth_headset_profile_summary_connected" >"已连接到手机音频"</string>
    <string name="bluetooth_opp_profile_summary_connected" >"已连接到文件传输服务器"</string>
    <string name="bluetooth_map_profile_summary_connected" >"已连接到地图"</string>
    <string name="bluetooth_sap_profile_summary_connected" >"已连接到 SAP"</string>
    <string name="bluetooth_opp_profile_summary_not_connected" >"未连接到文件传输服务器"</string>
    <string name="bluetooth_hid_profile_summary_connected" >"已连接到输入设备"</string>
    <string name="bluetooth_pan_user_profile_summary_connected" >"经由其他设备连接到互联网"</string>
    <string name="bluetooth_pan_nap_profile_summary_connected" >"与其他设备共享该设备的互联网连接"</string>
    <string name="bluetooth_pan_profile_summary_use_for" >"用于访问互联网"</string>
    <string name="bluetooth_map_profile_summary_use_for" >"用于地图"</string>
    <string name="bluetooth_sap_profile_summary_use_for" >"用于存取 SIM 卡"</string>
    <string name="bluetooth_a2dp_profile_summary_use_for" >"用于媒体音频"</string>
    <string name="bluetooth_headset_profile_summary_use_for" >"用于手机音频"</string>
    <string name="bluetooth_opp_profile_summary_use_for" >"用于文件传输"</string>
    <string name="bluetooth_hid_profile_summary_use_for" >"用于输入"</string>
    <string name="bluetooth_hearing_aid_profile_summary_use_for" >"用于助听器"</string>
    <string name="bluetooth_pairing_accept" >"配对"</string>
    <string name="bluetooth_pairing_accept_all_caps" >"配对"</string>
    <string name="bluetooth_pairing_decline" >"取消"</string>
    <string name="bluetooth_pairing_will_share_phonebook" >"配对之后，所配对的设备将可以在建立连接后访问您的通讯录和通话记录。"</string>
    <string name="bluetooth_pairing_error_message" >"无法与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”进行配对。"</string>
    <string name="bluetooth_pairing_pin_error_message" >"PIN码或配对密钥不正确，无法与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对。"</string>
    <string name="bluetooth_pairing_device_down_error_message" >"无法与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”进行通信。"</string>
    <string name="bluetooth_pairing_rejected_error_message" >"<xliff:g id="DEVICE_NAME">%1$s</xliff:g> 已拒绝配对。"</string>
    <string name="bluetooth_talkback_computer" >"计算机"</string>
    <string name="bluetooth_talkback_headset" >"耳麦"</string>
    <string name="bluetooth_talkback_phone" >"手机"</string>
    <string name="bluetooth_talkback_imaging" >"成像设备"</string>
    <string name="bluetooth_talkback_headphone" >"耳机"</string>
    <string name="bluetooth_talkback_input_peripheral" >"外围输入设备"</string>
    <string name="bluetooth_talkback_bluetooth" >"蓝牙"</string>
    <string name="bluetooth_hearingaid_left_pairing_message" >"正在配对左侧助听器…"</string>
    <string name="bluetooth_hearingaid_right_pairing_message" >"正在配对右侧助听器…"</string>
    <string name="bluetooth_hearingaid_left_battery_level" >"左侧 - 电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_hearingaid_right_battery_level" >"右侧 - 电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="accessibility_wifi_off" >"WLAN 已关闭。"</string>
    <string name="accessibility_no_wifi" >"WLAN 连接已断开。"</string>
    <string name="accessibility_wifi_one_bar" >"WLAN 信号强度为一格。"</string>
    <string name="accessibility_wifi_two_bars" >"WLAN 信号强度为两格。"</string>
    <string name="accessibility_wifi_three_bars" >"WLAN 信号强度为三格。"</string>
    <string name="accessibility_wifi_signal_full" >"WLAN 信号满格。"</string>
    <string name="accessibility_wifi_security_type_none" >"开放网络"</string>
    <string name="accessibility_wifi_security_type_secured" >"安全网络"</string>
    <string name="process_kernel_label" >"Android 操作系统"</string>
    <string name="data_usage_uninstalled_apps" >"已删除的应用"</string>
    <string name="data_usage_uninstalled_apps_users" >"已删除的应用和用户"</string>
    <string name="tether_settings_title_usb" >"USB 网络共享"</string>
    <string name="tether_settings_title_wifi" >"便携式热点"</string>
    <string name="tether_settings_title_bluetooth" >"蓝牙网络共享"</string>
    <string name="tether_settings_title_usb_bluetooth" >"网络共享"</string>
    <string name="tether_settings_title_all" >"网络共享与便携式热点"</string>
    <string name="managed_user_title" >"所有工作应用"</string>
    <string name="user_guest" >"访客"</string>
    <string name="unknown" >"未知"</string>
    <string name="running_process_item_user_label" >"用户：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="launch_defaults_some" >"已设置部分默认选项"</string>
    <string name="launch_defaults_none" >"没有默认操作"</string>
    <string name="tts_settings" >"文字转语音设置"</string>
    <string name="tts_settings_title" >"文字转语音 (TTS) 输出"</string>
    <string name="tts_default_rate_title" >"语速"</string>
    <string name="tts_default_rate_summary" >"文字转换成语音后的播放速度"</string>
    <string name="tts_default_pitch_title" >"音高"</string>
    <string name="tts_default_pitch_summary" >"会影响合成语音的音调"</string>
    <string name="tts_default_lang_title" >"语言"</string>
    <string name="tts_lang_use_system" >"使用系统语言"</string>
    <string name="tts_lang_not_selected" >"未选择语言"</string>
    <string name="tts_default_lang_summary" >"设置文字转语音功能要使用的语言"</string>
    <string name="tts_play_example_title" >"收听示例"</string>
    <string name="tts_play_example_summary" >"播放简短的语音合成示例"</string>
    <string name="tts_install_data_title" >"安装语音数据包"</string>
    <string name="tts_install_data_summary" >"安装语音合成所需的数据包"</string>
    <string name="tts_engine_security_warning" >"此语音合成引擎能够收集语音中出现的所有信息，包括密码和信用卡号码之类的个人数据。此功能由 <xliff:g id="TTS_PLUGIN_ENGINE_NAME">%s</xliff:g> 引擎提供。是否启用此语音合成引擎？"</string>
    <string name="tts_engine_network_required" >"您必须连接到网络才能使用文字转语音功能输出这种语言。"</string>
    <string name="tts_default_sample_string" >"这是语音合成示例"</string>
    <string name="tts_status_title" >"默认语言状态"</string>
    <string name="tts_status_ok" >"完全支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_requires_network" >"只有在连接到网络的情况下，才支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_not_supported" >"不支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_checking" >"正在检查…"</string>
    <string name="tts_engine_settings_title" >"“<xliff:g id="TTS_ENGINE_NAME">%s</xliff:g>”的设置"</string>
    <string name="tts_engine_settings_button" >"进行引擎设置"</string>
    <string name="tts_engine_preference_section_title" >"首选引擎"</string>
    <string name="tts_general_section_title" >"常规"</string>
    <string name="tts_reset_speech_pitch_title" >"重置语音音调"</string>
    <string name="tts_reset_speech_pitch_summary" >"将文字的读出音调重置为默认值。"</string>
  <string-array name="tts_rate_entries">
    <item >"很慢"</item>
    <item >"慢"</item>
    <item >"正常"</item>
    <item >"快"</item>
    <item >"较快"</item>
    <item >"非常快"</item>
    <item >"超快"</item>
    <item >"极快"</item>
    <item >"最快"</item>
  </string-array>
    <string name="choose_profile" >"选择个人资料"</string>
    <string name="category_personal" >"个人"</string>
    <string name="category_work" >"工作"</string>
    <string name="development_settings_title" >"开发者选项"</string>
    <string name="development_settings_enable" >"启用开发者选项"</string>
    <string name="development_settings_summary" >"设置应用开发选项"</string>
    <string name="development_settings_not_available" >"此用户无法使用开发者选项"</string>
    <string name="vpn_settings_not_available" >"此用户无权修改VPN设置"</string>
    <string name="tethering_settings_not_available" >"此用户无权修改网络共享设置"</string>
    <string name="apn_settings_not_available" >"此用户无权修改接入点名称设置"</string>
    <string name="enable_adb" >"USB 调试"</string>
    <string name="enable_adb_summary" >"连接 USB 后启用调试模式"</string>
    <string name="clear_adb_keys" >"撤消 USB 调试授权"</string>
    <string name="bugreport_in_power" >"错误报告快捷方式"</string>
    <string name="bugreport_in_power_summary" >"在电源菜单中显示用于提交错误报告的按钮"</string>
    <string name="keep_screen_on" >"不锁定屏幕"</string>
    <string name="keep_screen_on_summary" >"充电时屏幕不会休眠"</string>
    <string name="bt_hci_snoop_log" >"启用蓝牙 HCI 信息收集日志"</string>
    <string name="bt_hci_snoop_log_summary" >"捕获单个文件中的所有蓝牙 HCI 包（更改此设置之后切换蓝牙开关）"</string>
    <string name="oem_unlock_enable" >"OEM 解锁"</string>
    <string name="oem_unlock_enable_summary" >"允许解锁引导加载程序"</string>
    <string name="confirm_enable_oem_unlock_title" >"要允许 OEM 解锁吗？"</string>
    <string name="confirm_enable_oem_unlock_text" >"警告：如果开启此设置，此设备上的设备保护功能将无法使用。"</string>
    <string name="mock_location_app" >"选择模拟位置信息应用"</string>
    <string name="mock_location_app_not_set" >"尚未设置模拟位置信息应用"</string>
    <string name="mock_location_app_set" >"模拟位置信息应用：<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="debug_networking_category" >"网络"</string>
    <string name="wifi_display_certification" >"无线显示认证"</string>
    <string name="wifi_verbose_logging" >"启用 WLAN 详细日志记录功能"</string>
    <string name="wifi_connected_mac_randomization" >"连接时随机选择 MAC 网址"</string>
    <string name="mobile_data_always_on" >"始终开启移动数据网络"</string>
    <string name="tethering_hardware_offload" >"网络共享硬件加速"</string>
    <string name="bluetooth_show_devices_without_names" >"显示没有名称的蓝牙设备"</string>
    <string name="bluetooth_disable_absolute_volume" >"停用绝对音量功能"</string>
    <string name="bluetooth_select_avrcp_version_string" >"蓝牙 AVRCP 版本"</string>
    <string name="bluetooth_select_avrcp_version_dialog_title" >"选择蓝牙 AVRCP 版本"</string>
    <string name="bluetooth_select_a2dp_codec_type" >"蓝牙音频编解码器"</string>
    <string name="bluetooth_select_a2dp_codec_type_dialog_title" >"触发蓝牙音频编解码器\n选择"</string>
    <string name="bluetooth_select_a2dp_codec_sample_rate" >"蓝牙音频采样率"</string>
    <string name="bluetooth_select_a2dp_codec_sample_rate_dialog_title" >"触发蓝牙音频编解码器\n选择：采样率"</string>
    <string name="bluetooth_select_a2dp_codec_bits_per_sample" >"蓝牙音频每样本位数"</string>
    <string name="bluetooth_select_a2dp_codec_bits_per_sample_dialog_title" >"触发蓝牙音频编解码器\n选择：每样本位数"</string>
    <string name="bluetooth_select_a2dp_codec_channel_mode" >"蓝牙音频声道模式"</string>
    <string name="bluetooth_select_a2dp_codec_channel_mode_dialog_title" >"触发蓝牙音频编解码器\n选择：声道模式"</string>
    <string name="bluetooth_select_a2dp_codec_ldac_playback_quality" >"蓝牙音频 LDAC 编解码器：播放质量"</string>
    <string name="bluetooth_select_a2dp_codec_ldac_playback_quality_dialog_title" >"触发蓝牙音频 LDAC\n编解码器选择：播放质量"</string>
    <string name="bluetooth_select_a2dp_codec_streaming_label" >"正在流式传输：<xliff:g id="STREAMING_PARAMETER">%1$s</xliff:g>"</string>
    <string name="select_private_dns_configuration_title" >"私人 DNS"</string>
    <string name="select_private_dns_configuration_dialog_title" >"选择私人 DNS 模式"</string>
    <string name="private_dns_mode_off" >"关闭"</string>
    <string name="private_dns_mode_opportunistic" >"自动"</string>
    <string name="private_dns_mode_provider" >"私人 DNS 提供商主机名"</string>
    <string name="private_dns_mode_provider_hostname_hint" >"输入 DNS 提供商的主机名"</string>
    <string name="private_dns_mode_provider_failure" >"无法连接"</string>
    <string name="wifi_display_certification_summary" >"显示无线显示认证选项"</string>
    <string name="wifi_verbose_logging_summary" >"提升 WLAN 日志记录级别（在 WLAN 选择器中显示每个 SSID 的 RSSI）"</string>
    <string name="wifi_connected_mac_randomization_summary" >"连接到 WLAN 网络时随机选择 MAC 地址"</string>
    <string name="wifi_metered_label" >"按流量计费"</string>
    <string name="wifi_unmetered_label" >"不按流量计费"</string>
    <string name="select_logd_size_title" >"日志记录器缓冲区大小"</string>
    <string name="select_logd_size_dialog_title" >"选择每个日志缓冲区的日志记录器大小"</string>
    <string name="dev_logpersist_clear_warning_title" >"要清除永久存储的日志记录器数据吗？"</string>
    <string name="dev_logpersist_clear_warning_message" >"当我们不再使用永久日志记录器进行监控时，我们需要清除保存在您设备上的日志记录器数据。"</string>
    <string name="select_logpersist_title" >"在设备上永久存储日志记录器数据"</string>
    <string name="select_logpersist_dialog_title" >"选择要在设备上永久存储的日志缓冲区"</string>
    <string name="select_usb_configuration_title" >"选择USB配置"</string>
    <string name="select_usb_configuration_dialog_title" >"选择USB配置"</string>
    <string name="allow_mock_location" >"允许模拟位置"</string>
    <string name="allow_mock_location_summary" >"允许模拟位置"</string>
    <string name="debug_view_attributes" >"启用视图属性检查功能"</string>
    <string name="mobile_data_always_on_summary" >"始终开启移动数据网络，即使 WLAN 网络已开启（便于快速切换网络）。"</string>
    <string name="tethering_hardware_offload_summary" >"使用网络共享硬件加速功能（如果可用）"</string>
    <string name="adb_warning_title" >"是否允许 USB 调试？"</string>
    <string name="adb_warning_message" >"USB 调试仅用于开发目的。该功能可用于在您的计算机和设备之间复制数据、在您的设备上安装应用（事先不发通知）以及读取日志数据。"</string>
    <string name="adb_keys_warning_message" >"是否针对您之前授权的所有计算机撤消 USB 调试的访问权限？"</string>
    <string name="dev_settings_warning_title" >"允许开发设置？"</string>
    <string name="dev_settings_warning_message" >"这些设置仅适用于开发工作。一旦启用，会导致您的设备以及设备上的应用崩溃或出现异常。"</string>
    <string name="verify_apps_over_usb_title" >"通过USB验证应用"</string>
    <string name="verify_apps_over_usb_summary" >"通过 ADB/ADT 检查安装的应用是否存在有害行为。"</string>
    <string name="bluetooth_show_devices_without_names_summary" >"系统将显示没有名称（只有 MAC 地址）的蓝牙设备"</string>
    <string name="bluetooth_disable_absolute_volume_summary" >"停用蓝牙绝对音量功能，即可避免在连接到远程设备时出现音量问题（例如音量高得让人无法接受或无法控制音量等）。"</string>
    <string name="enable_terminal_title" >"本地终端"</string>
    <string name="enable_terminal_summary" >"启用终端应用，以便在本地访问 Shell"</string>
    <string name="hdcp_checking_title" >"HDCP 检查"</string>
    <string name="hdcp_checking_dialog_title" >"设置 HDCP 检查行为"</string>
    <string name="debug_debugging_category" >"调试"</string>
    <string name="debug_app" >"选择调试应用"</string>
    <string name="debug_app_not_set" >"未设置任何调试应用"</string>
    <string name="debug_app_set" >"调试应用：<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="select_application" >"选择应用"</string>
    <string name="no_application" >"无"</string>
    <string name="wait_for_debugger" >"等待调试程序"</string>
    <string name="wait_for_debugger_summary" >"调试应用会在执行前等待附加调试器"</string>
    <string name="debug_input_category" >"输入"</string>
    <string name="debug_drawing_category" >"绘图"</string>
    <string name="debug_hw_drawing_category" >"硬件加速渲染"</string>
    <string name="media_category" >"媒体"</string>
    <string name="debug_monitoring_category" >"监控"</string>
    <string name="strict_mode" >"启用严格模式"</string>
    <string name="strict_mode_summary" >"应用在主线程上执行长时间操作时闪烁屏幕"</string>
    <string name="pointer_location" >"指针位置"</string>
    <string name="pointer_location_summary" >"屏幕叠加层显示当前触摸数据"</string>
    <string name="show_touches" >"显示点按操作反馈"</string>
    <string name="show_touches_summary" >"显示点按操作的视觉反馈"</string>
    <string name="show_screen_updates" >"显示面 (surface) 更新"</string>
    <string name="show_screen_updates_summary" >"窗口中的面 (surface) 更新时全部闪烁"</string>
    <string name="show_hw_screen_updates" >"显示 GPU 视图更新"</string>
    <string name="show_hw_screen_updates_summary" >"使用 GPU 进行绘图时闪烁显示窗口中的视图"</string>
    <string name="show_hw_layers_updates" >"显示硬件层更新"</string>
    <string name="show_hw_layers_updates_summary" >"Flash 硬件层在进行更新时会显示为绿色"</string>
    <string name="debug_hw_overdraw" >"调试 GPU 过度绘制"</string>
    <string name="disable_overlays" >"停用 HW 叠加层"</string>
    <string name="disable_overlays_summary" >"始终使用 GPU 进行屏幕合成"</string>
    <string name="simulate_color_space" >"模拟颜色空间"</string>
    <string name="enable_opengl_traces_title" >"启用 OpenGL 跟踪"</string>
    <string name="usb_audio_disable_routing" >"关闭 USB 音频转接"</string>
    <string name="usb_audio_disable_routing_summary" >"关闭自动转接至 USB 音频外围设备的功能"</string>
    <string name="debug_layout" >"显示布局边界"</string>
    <string name="debug_layout_summary" >"显示剪辑边界、边距等。"</string>
    <string name="force_rtl_layout_all_locales" >"强制使用从右到左的布局方向"</string>
    <string name="force_rtl_layout_all_locales_summary" >"强制将所有语言区域的屏幕布局方向改为从右到左"</string>
    <string name="force_hw_ui" >"强制进行 GPU 渲染"</string>
    <string name="force_hw_ui_summary" >"强制使用 GPU 进行 2D 绘图"</string>
    <string name="force_msaa" >"强制启用 4x MSAA"</string>
    <string name="force_msaa_summary" >"在 OpenGL ES 2.0 应用中启用 4x MSAA"</string>
    <string name="show_non_rect_clip" >"调试非矩形剪裁操作"</string>
    <string name="track_frame_time" >"GPU 渲染模式分析"</string>
    <string name="enable_gpu_debug_layers" >"启用 GPU 调试层"</string>
    <string name="enable_gpu_debug_layers_summary" >"允许为调试应用加载 GPU 调试层"</string>
    <string name="window_animation_scale_title" >"窗口动画缩放"</string>
    <string name="transition_animation_scale_title" >"过渡动画缩放"</string>
    <string name="animator_duration_scale_title" >"Animator 时长缩放"</string>
    <string name="overlay_display_devices_title" >"模拟辅助显示设备"</string>
    <string name="debug_applications_category" >"应用"</string>
    <string name="immediately_destroy_activities" >"不保留活动"</string>
    <string name="immediately_destroy_activities_summary" >"用户离开后即销毁每个活动"</string>
    <string name="app_process_limit_title" >"后台进程限制"</string>
    <string name="show_all_anrs" >"显示后台 ANR"</string>
    <string name="show_all_anrs_summary" >"为后台应用显示“应用无响应”对话框"</string>
    <string name="show_notification_channel_warnings" >"显示通知渠道警告"</string>
    <string name="show_notification_channel_warnings_summary" >"当应用未经有效渠道发布通知时，在屏幕上显示警告"</string>
    <string name="force_allow_on_external" >"强制允许将应用写入外部存储设备"</string>
    <string name="force_allow_on_external_summary" >"允许将任何应用写入外部存储设备（无论清单值是什么）"</string>
    <string name="force_resizable_activities" >"强制将活动设为可调整大小"</string>
    <string name="force_resizable_activities_summary" >"将所有 Activity 设为可配合多窗口环境调整大小（忽略清单值）。"</string>
    <string name="enable_freeform_support" >"启用可自由调整的窗口"</string>
    <string name="enable_freeform_support_summary" >"启用可自由调整的窗口这一实验性功能。"</string>
    <string name="local_backup_password_title" >"桌面备份密码"</string>
    <string name="local_backup_password_summary_none" >"桌面完整备份当前未设置密码保护"</string>
    <string name="local_backup_password_summary_change" >"点按即可更改或移除用于保护桌面完整备份的密码"</string>
    <string name="local_backup_password_toast_success" >"已设置了新的备份密码"</string>
    <string name="local_backup_password_toast_confirmation_mismatch" >"新密码和确认密码不一致"</string>
    <string name="local_backup_password_toast_validation_failure" >"设置备份密码失败"</string>
  <string-array name="color_mode_names">
    <item >"鲜亮（默认）"</item>
    <item >"自然"</item>
    <item >"标准"</item>
  </string-array>
  <string-array name="color_mode_descriptions">
    <item >"增强的颜色"</item>
    <item >"肉眼所看到的自然颜色"</item>
    <item >"针对数字内容优化的颜色"</item>
  </string-array>
    <string name="inactive_apps_title" >"待机应用"</string>
    <string name="inactive_app_inactive_summary" >"未启用。点按即可切换。"</string>
    <string name="inactive_app_active_summary" >"已启用。点按即可切换。"</string>
    <string name="standby_bucket_summary" >"应用待机状态：<xliff:g id="BUCKET"> %s</xliff:g>"</string>
    <string name="runningservices_settings_title" >"正在运行的服务"</string>
    <string name="runningservices_settings_summary" >"查看和控制当前正在运行的服务"</string>
    <string name="select_webview_provider_title" >"WebView 实现"</string>
    <string name="select_webview_provider_dialog_title" >"设置 WebView 实现"</string>
    <string name="select_webview_provider_toast_text" >"此选项已失效，请重试。"</string>
    <string name="convert_to_file_encryption" >"转换为文件加密"</string>
    <string name="convert_to_file_encryption_enabled" >"转换…"</string>
    <string name="convert_to_file_encryption_done" >"文件已加密"</string>
    <string name="title_convert_fbe" >"正在转换为文件加密"</string>
    <string name="convert_to_fbe_warning" >"将数据分区转换为文件加密。\n！！警告！！此操作将会清除您所有的数据。\n此功能为 Alpha 版功能，可能无法正常运行。\n要继续操作，请按“清除并转换…”。"</string>
    <string name="button_convert_fbe" >"清除并转换…"</string>
    <string name="picture_color_mode" >"图片颜色模式"</string>
    <string name="picture_color_mode_desc" >"使用 sRGB"</string>
    <string name="daltonizer_mode_disabled" >"已停用"</string>
    <string name="daltonizer_mode_monochromacy" >"全色盲"</string>
    <string name="daltonizer_mode_deuteranomaly" >"绿色弱视（红绿不分）"</string>
    <string name="daltonizer_mode_protanomaly" >"红色弱视（红绿不分）"</string>
    <string name="daltonizer_mode_tritanomaly" >"蓝色弱视（蓝黄不分）"</string>
    <string name="accessibility_display_daltonizer_preference_title" >"色彩校正"</string>
    <string name="accessibility_display_daltonizer_preference_subtitle" >"这是实验性功能，性能可能不稳定。"</string>
    <string name="daltonizer_type_overridden" >"已被“<xliff:g id="TITLE">%1$s</xliff:g>”覆盖"</string>
    <string name="power_remaining_settings_home_page" >"<xliff:g id="PERCENTAGE">%1$s</xliff:g> - <xliff:g id="TIME_STRING">%2$s</xliff:g>"</string>
    <string name="power_remaining_duration_only" >"大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharging_duration" >"大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_duration_only_enhanced" >"根据您的使用情况，大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharging_duration_enhanced" >"根据您的使用情况，大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_duration_only_short" >"还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharge_by_enhanced" >"根据您的使用情况，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>（目前电量为 <xliff:g id="LEVEL">%2$s</xliff:g>）"</string>
    <string name="power_discharge_by_only_enhanced" >"根据您的使用情况，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_discharge_by" >"目前电量为 <xliff:g id="LEVEL">%2$s</xliff:g>，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_discharge_by_only" >"估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_remaining_less_than_duration_only" >"剩余电池续航时间不到 <xliff:g id="THRESHOLD">%1$s</xliff:g>"</string>
    <string name="power_remaining_less_than_duration" >"电量剩余使用时间不到 <xliff:g id="THRESHOLD">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_more_than_subtext" >"电量剩余使用时间超过 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_only_more_than_subtext" >"电量剩余使用时间超过 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="default" >"手机可能即将关机"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="tablet" >"平板电脑可能即将关机"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="device" >"设备可能即将关机"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="default" >"手机可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="tablet" >"平板电脑可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="device" >"设备可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_charging" >"<xliff:g id="LEVEL">%1$s</xliff:g> - <xliff:g id="STATE">%2$s</xliff:g>"</string>
    <string name="power_remaining_charging_duration_only" >"还需 <xliff:g id="TIME">%1$s</xliff:g>充满电"</string>
    <string name="power_charging_duration" >"<xliff:g id="LEVEL">%1$s</xliff:g> - 还需 <xliff:g id="TIME">%2$s</xliff:g>充满"</string>
    <string name="battery_info_status_unknown" >"未知"</string>
    <string name="battery_info_status_charging" >"正在充电"</string>
    <string name="battery_info_status_charging_lower" >"正在充电"</string>
    <string name="battery_info_status_discharging" >"未在充电"</string>
    <string name="battery_info_status_not_charging" >"已插入电源，但是现在无法充电"</string>
    <string name="battery_info_status_full" >"电量充足"</string>
    <string name="disabled_by_admin_summary_text" >"由管理员控制"</string>
    <string name="enabled_by_admin" >"已被管理员启用"</string>
    <string name="disabled_by_admin" >"已被管理员停用"</string>
    <string name="disabled" >"已停用"</string>
    <string name="external_source_trusted" >"允许"</string>
    <string name="external_source_untrusted" >"不允许"</string>
    <string name="install_other_apps" >"安装未知应用"</string>
    <string name="home" >"设置主屏幕"</string>
  <string-array name="battery_labels">
    <item >"0%"</item>
    <item >"50%"</item>
    <item >"100%"</item>
  </string-array>
    <string name="charge_length_format" >"<xliff:g id="ID_1">%1$s</xliff:g>前"</string>
    <string name="remaining_length_format" >"还剩 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="screen_zoom_summary_small" >"小"</string>
    <string name="screen_zoom_summary_default" >"默认"</string>
    <string name="screen_zoom_summary_large" >"大"</string>
    <string name="screen_zoom_summary_very_large" >"较大"</string>
    <string name="screen_zoom_summary_extremely_large" >"最大"</string>
    <string name="screen_zoom_summary_custom" >"自定义 (<xliff:g id="DENSITYDPI">%d</xliff:g>)"</string>
    <string name="help_feedback_label" >"帮助和反馈"</string>
    <string name="content_description_menu_button" >"菜单"</string>
    <string name="retail_demo_reset_message" >"输入密码即可在演示模式下恢复出厂设置"</string>
    <string name="retail_demo_reset_next" >"下一步"</string>
    <string name="retail_demo_reset_title" >"需要输入密码"</string>
    <string name="active_input_method_subtypes" >"有效的输入法"</string>
    <string name="use_system_language_to_select_input_method_subtypes" >"使用系统语言"</string>
    <string name="failed_to_open_app_settings_toast" >"无法打开 <xliff:g id="SPELL_APPLICATION_NAME">%1$s</xliff:g> 的设置"</string>
    <string name="ime_security_warning" >"此输入法可能会收集您输入的所有内容，包括密码和信用卡号等个人数据。它来自“<xliff:g id="IME_APPLICATION_NAME">%1$s</xliff:g>”应用。要使用此输入法吗？"</string>
    <string name="direct_boot_unaware_dialog_message" >"注意：重新启动后，您必须将手机解锁才能运行此应用"</string>
    <string name="ims_reg_title" >"IMS 注册状态"</string>
    <string name="ims_reg_status_registered" >"已注册"</string>
    <string name="ims_reg_status_not_registered" >"未注册"</string>
    <string name="status_unavailable" >"无法获取"</string>
    <string name="wifi_status_mac_randomized" >"MAC 已随机化"</string>
    <plurals name="wifi_tether_connected_summary" formatted="false" >
      <item quantity="other">已连接 %1$d 个设备</item>
      <item quantity="one">已连接 %1$d 个设备</item>
    </plurals>
    <string name="accessibility_manual_zen_more_time" >"增加时间。"</string>
    <string name="accessibility_manual_zen_less_time" >"减少时间。"</string>
    <string name="cancel" >"取消"</string>
    <string name="okay" >"确定"</string>
    <string name="zen_mode_enable_dialog_turn_on" >"开启"</string>
    <string name="zen_mode_settings_turn_on_dialog_title" >"开启“勿扰”模式"</string>
    <string name="zen_mode_settings_summary_off" >"永不"</string>
    <string name="zen_interruption_level_priority" >"仅限优先事项"</string>
    <string name="zen_mode_and_condition" >"<xliff:g id="ZEN_MODE">%1$s</xliff:g>。<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_alarm_warning_indef" >"您将不会听到下一个<xliff:g id="WHEN">%1$s</xliff:g> 的闹钟响铃，除非您在该时间之前关闭这项功能"</string>
    <string name="zen_alarm_warning" >"您将不会听到下一个<xliff:g id="WHEN">%1$s</xliff:g> 的闹钟响铃"</string>
    <string name="alarm_template" >"时间：<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="alarm_template_far" >"时间：<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="zen_mode_duration_settings_title" >"持续时间"</string>
    <string name="zen_mode_duration_always_prompt_title" >"每次都询问"</string>
    <string name="time_unit_just_now" >"刚刚"</string>
</resources>

```

### arrays_cn.xml
http://androidxref.com/9.0.0_r3/xref/frameworks/base/packages/SettingsLib/res/values-zh-rCN/arrays.xml
frameworks_base_packages_SettingsLib_arrays_cn.xml

```
<?xml version="1.0" encoding="UTF-8"?>

<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
  <string-array name="wifi_status">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接..."</item>
    <item >"正在验证身份…"</item>
    <item >"正在获取IP地址..."</item>
    <item >"已连接"</item>
    <item >"已暂停"</item>
    <item >"正在断开连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="wifi_status_with_ssid">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item >"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item >"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item >"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item >"已暂停"</item>
    <item >"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="hdcp_checking_titles">
    <item >"永不检查"</item>
    <item >"仅检查 DRM 内容"</item>
    <item >"总是检查"</item>
  </string-array>
  <string-array name="hdcp_checking_summaries">
    <item >"一律不使用 HDCP 检查"</item>
    <item >"仅使用 HDCP 检查 DRM 内容"</item>
    <item >"始终使用 HDCP 检查"</item>
  </string-array>
  <string-array name="bluetooth_avrcp_versions">
    <item >"AVRCP 1.4（默认）"</item>
    <item >"AVRCP 1.3"</item>
    <item >"AVRCP 1.5"</item>
    <item >"AVRCP 1.6"</item>
  </string-array>
  <string-array name="bluetooth_avrcp_version_values">
    <item >"avrcp14"</item>
    <item >"avrcp13"</item>
    <item >"avrcp15"</item>
    <item >"avrcp16"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_titles">
    <item >"使用系统选择（默认）"</item>
    <item >"SBC"</item>
    <item >"AAC"</item>
    <item >"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX">aptX™</xliff:g> 音频"</item>
    <item >"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX_HD">aptX™ HD</xliff:g> 音频"</item>
    <item >"LDAC"</item>
    <item >"启用可选编解码器"</item>
    <item >"停用可选编解码器"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_summaries">
    <item >"使用系统选择（默认）"</item>
    <item >"SBC"</item>
    <item >"AAC"</item>
    <item >"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX">aptX™</xliff:g> 音频"</item>
    <item >"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX_HD">aptX™ HD</xliff:g> 音频"</item>
    <item >"LDAC"</item>
    <item >"启用可选编解码器"</item>
    <item >"停用可选编解码器"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_sample_rate_titles">
    <item >"使用系统选择（默认）"</item>
    <item >"44.1 kHz"</item>
    <item >"48.0 kHz"</item>
    <item >"88.2 kHz"</item>
    <item >"96.0 kHz"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_sample_rate_summaries">
    <item >"使用系统选择（默认）"</item>
    <item >"44.1 kHz"</item>
    <item >"48.0 kHz"</item>
    <item >"88.2 kHz"</item>
    <item >"96.0 kHz"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_bits_per_sample_titles">
    <item >"使用系统选择（默认）"</item>
    <item >"16 位/样本"</item>
    <item >"24 位/样本"</item>
    <item >"32 位/样本"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_bits_per_sample_summaries">
    <item >"使用系统选择（默认）"</item>
    <item >"16 位/样本"</item>
    <item >"24 位/样本"</item>
    <item >"32 位/样本"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_channel_mode_titles">
    <item >"使用系统选择（默认）"</item>
    <item >"单声道"</item>
    <item >"立体声"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_channel_mode_summaries">
    <item >"使用系统选择（默认）"</item>
    <item >"单声道"</item>
    <item >"立体声"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_ldac_playback_quality_titles">
    <item >"偏重音频质量 (990kbps/909kbps)"</item>
    <item >"兼顾音频和连接质量 (660kbps/606kbps)"</item>
    <item >"偏重连接质量 (330kbps/303kbps)"</item>
    <item >"尽可能提供更佳音质（自适应比特率）"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_ldac_playback_quality_summaries">
    <item >"偏重音频质量"</item>
    <item >"兼顾音频和连接质量"</item>
    <item >"偏重连接质量"</item>
    <item >"尽可能提供更佳音质（自适应比特率）"</item>
  </string-array>
  <string-array name="bluetooth_audio_active_device_summaries">
    <item ></item>
    <item >"，使用中"</item>
    <item >"，使用中（媒体）"</item>
    <item >"，使用中（手机）"</item>
  </string-array>
  <string-array name="select_logd_size_titles">
    <item >"关闭"</item>
    <item >"64K"</item>
    <item >"256K"</item>
    <item >"1M"</item>
    <item >"4M"</item>
    <item >"16M"</item>
  </string-array>
  <string-array name="select_logd_size_lowram_titles">
    <item >"关闭"</item>
    <item >"64K"</item>
    <item >"256K"</item>
    <item >"1M"</item>
  </string-array>
  <string-array name="select_logd_size_summaries">
    <item >"关闭"</item>
    <item >"每个日志缓冲区 64K"</item>
    <item >"每个日志缓冲区 256K"</item>
    <item >"每个日志缓冲区 1M"</item>
    <item >"每个日志缓冲区 4M"</item>
    <item >"每个日志缓冲区 16M"</item>
  </string-array>
  <string-array name="select_logpersist_titles">
    <item >"关闭"</item>
    <item >"全部"</item>
    <item >"所有非无线电"</item>
    <item >"仅限内核"</item>
  </string-array>
  <string-array name="select_logpersist_summaries">
    <item >"关闭"</item>
    <item >"所有日志缓冲区"</item>
    <item >"所有非无线电日志缓冲区"</item>
    <item >"仅限内核日志缓冲区"</item>
  </string-array>
  <string-array name="window_animation_scale_entries">
    <item >"关闭动画"</item>
    <item >"动画缩放 0.5x"</item>
    <item >"动画缩放 1x"</item>
    <item >"动画缩放 1.5x"</item>
    <item >"动画缩放 2x"</item>
    <item >"动画缩放 5x"</item>
    <item >"动画缩放 10x"</item>
  </string-array>
  <string-array name="transition_animation_scale_entries">
    <item >"关闭动画"</item>
    <item >"动画缩放 0.5x"</item>
    <item >"动画缩放 1x"</item>
    <item >"动画缩放 1.5x"</item>
    <item >"动画缩放 2x"</item>
    <item >"动画缩放 5x"</item>
    <item >"动画缩放 10x"</item>
  </string-array>
  <string-array name="animator_duration_scale_entries">
    <item >"关闭动画"</item>
    <item >"动画缩放 0.5x"</item>
    <item >"动画缩放 1x"</item>
    <item >"动画缩放 1.5x"</item>
    <item >"动画缩放 2x"</item>
    <item >"动画缩放 5x"</item>
    <item >"动画缩放 10x"</item>
  </string-array>
  <string-array name="overlay_display_devices_entries">
    <item >"无"</item>
    <item >"480p"</item>
    <item >"480p（安全）"</item>
    <item >"720p"</item>
    <item >"720p（安全）"</item>
    <item >"1080p"</item>
    <item >"1080p（安全）"</item>
    <item >"4K"</item>
    <item >"4K（安全）"</item>
    <item >"4K（画质提升）"</item>
    <item >"4K（画质提升、安全）"</item>
    <item >"720p，1080p（双屏）"</item>
  </string-array>
  <string-array name="enable_opengl_traces_entries">
    <item >"无"</item>
    <item >"Logcat"</item>
    <item >"Systrace（图形）"</item>
    <item >"glGetError 上的调用堆栈"</item>
  </string-array>
  <string-array name="show_non_rect_clip_entries">
    <item >"关闭"</item>
    <item >"以蓝色填充非矩形剪裁区域"</item>
    <item >"以绿色突出显示测试绘制命令"</item>
  </string-array>
  <string-array name="track_frame_time_entries">
    <item >"关闭"</item>
    <item >"在屏幕上显示为条形图"</item>
    <item >"在 <xliff:g id="AS_TYPED_COMMAND">adb shell dumpsys gfxinfo</xliff:g> 中"</item>
  </string-array>
  <string-array name="debug_hw_overdraw_entries">
    <item >"关闭"</item>
    <item >"显示过度绘制区域"</item>
    <item >"显示适合绿色弱视患者查看的区域"</item>
  </string-array>
  <string-array name="app_process_limit_entries">
    <item >"标准限制"</item>
    <item >"不允许后台进程"</item>
    <item >"不得超过1个进程"</item>
    <item >"不得超过2个进程"</item>
    <item >"不得超过3个进程"</item>
    <item >"不得超过4个进程"</item>
  </string-array>
  <string-array name="usb_configuration_titles">
    <item >"充电"</item>
    <item >"MTP（媒体传输协议）"</item>
    <item >"PTP（图片传输协议）"</item>
    <item >"RNDIS（USB 以太网）"</item>
    <item >"音频来源"</item>
    <item >"MIDI"</item>
  </string-array>
</resources>

```

# /packages/apps/Launcher3/

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Launcher3/res/values-zh-rCN/strings.xml
packages_apps_Launcher3_res_strings_cn.xml

```


<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="app_name" >"Launcher3"</string>
    <string name="folder_name" ></string>
    <string name="work_folder_name" >"Work"</string>
    <string name="activity_not_found" >"未安装该应用。"</string>
    <string name="activity_not_available" >"应用不可用"</string>
    <string name="safemode_shortcut_error" >"安全模式下不允许使用下载的此应用"</string>
    <string name="safemode_widget_error" >"安全模式下不允许使用微件"</string>
    <string name="shortcut_not_available" >"无法使用快捷方式"</string>
    <string name="home_screen" >"主屏幕"</string>
    <string name="custom_actions" >"自定义操作"</string>
    <string name="long_press_widget_to_add" >"触摸并按住微件即可选择。"</string>
    <string name="long_accessible_way_to_add" >"点按两次并按住微件即可选择微件，您也可以使用自定义操作。"</string>
    <string name="widget_dims_format" >"%1$d × %2$d"</string>
    <string name="widget_accessible_dims_format" >"宽 %1$d，高 %2$d"</string>
    <string name="add_item_request_drag_hint" >"触摸并按住即可手动放置"</string>
    <string name="place_automatically" >"自动添加"</string>
    <string name="all_apps_search_bar_hint" >"搜索应用"</string>
    <string name="all_apps_loading_message" >"正在加载应用…"</string>
    <string name="all_apps_no_search_results" >"未找到与“<xliff:g id="QUERY">%1$s</xliff:g>”相符的应用"</string>
    <string name="all_apps_search_market_message" >"搜索更多应用"</string>
    <string name="notifications_header" >"通知"</string>
    <string name="long_press_shortcut_to_add" >"触摸并按住快捷方式即可选择快捷方式。"</string>
    <string name="long_accessible_way_to_add_shortcut" >"点按两次并按住快捷方式即可选择快捷方式，您也可以使用自定义操作。"</string>
    <string name="out_of_space" >"此主屏幕上已没有空间。"</string>
    <string name="hotseat_out_of_space" >"收藏栏已满"</string>
    <string name="all_apps_button_label" >"应用列表"</string>
    <string name="all_apps_button_personal_label" >"个人应用列表"</string>
    <string name="all_apps_button_work_label" >"工作应用列表"</string>
    <string name="all_apps_home_button_label" >"主屏幕"</string>
    <string name="remove_drop_target_label" >"移除"</string>
    <string name="uninstall_drop_target_label" >"卸载"</string>
    <string name="app_info_drop_target_label" >"应用信息"</string>
    <string name="install_drop_target_label" >"安装"</string>
    <string name="permlab_install_shortcut" >"安装快捷方式"</string>
    <string name="permdesc_install_shortcut" >"允许应用自行添加快捷方式。"</string>
    <string name="permlab_read_settings" >"读取主屏幕设置和快捷方式"</string>
    <string name="permdesc_read_settings" >"允许应用读取主屏幕中的设置和快捷方式。"</string>
    <string name="permlab_write_settings" >"写入主屏幕设置和快捷方式"</string>
    <string name="permdesc_write_settings" >"允许应用更改主屏幕中的设置和快捷方式。"</string>
    <string name="msg_no_phone_permission" >"不允许使用“<xliff:g id="APP_NAME">%1$s</xliff:g>”拨打电话"</string>
    <string name="gadget_error_text" >"加载微件时出现问题"</string>
    <string name="gadget_setup_text" >"设置"</string>
    <string name="uninstall_system_app_text" >"这是系统应用，无法卸载。"</string>
    <string name="folder_hint_text" >"未命名文件夹"</string>
    <string name="disabled_app_label" >"已停用<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <plurals name="badged_app_label" formatted="false" >
      <item quantity="other"><xliff:g id="APP_NAME_2">%1$s</xliff:g>，有 <xliff:g id="NOTIFICATION_COUNT_3">%2$d</xliff:g> 个通知</item>
      <item quantity="one"><xliff:g id="APP_NAME_0">%1$s</xliff:g>，有 <xliff:g id="NOTIFICATION_COUNT_1">%2$d</xliff:g> 个通知</item>
    </plurals>
    <string name="default_scroll_format" >"第%1$d页，共%2$d页"</string>
    <string name="workspace_scroll_format" >"主屏幕：第%1$d屏，共%2$d屏"</string>
    <string name="workspace_new_page" >"主屏幕新页面"</string>
    <string name="folder_opened" >"文件夹已打开，大小为<xliff:g id="WIDTH">%1$d</xliff:g>×<xliff:g id="HEIGHT">%2$d</xliff:g>"</string>
    <string name="folder_tap_to_close" >"点按可关闭文件夹"</string>
    <string name="folder_tap_to_rename" >"点按可保存新名称"</string>
    <string name="folder_closed" >"文件夹已关闭"</string>
    <string name="folder_renamed" >"已将文件夹重命名为“<xliff:g id="NAME">%1$s</xliff:g>”"</string>
    <string name="folder_name_format" >"文件夹：<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="widget_button_text" >"微件"</string>
    <string name="wallpaper_button_text" >"壁纸"</string>
    <string name="settings_button_text" >"主屏幕设置"</string>
    <string name="msg_disabled_by_admin" >"已被您的管理员停用"</string>
    <string name="allow_rotation_title" >"允许旋转主屏幕"</string>
    <string name="allow_rotation_desc" >"手机旋转时"</string>
    <string name="icon_badging_title" >"通知圆点"</string>
    <string name="icon_badging_desc_on" >"开启"</string>
    <string name="icon_badging_desc_off" >"关闭"</string>
    <string name="title_missing_notification_access" >"需要获取通知使用权"</string>
    <string name="msg_missing_notification_access" >"要显示通知圆点，请开启<xliff:g id="NAME">%1$s</xliff:g>的应用通知功能"</string>
    <string name="title_change_settings" >"更改设置"</string>
    <string name="icon_badging_service_title" >"显示通知圆点"</string>
    <string name="auto_add_shortcuts_label" >"将图标添加到主屏幕"</string>
    <string name="auto_add_shortcuts_description" >"适用于新应用"</string>
    <string name="icon_shape_override_label" >"更改图标形状"</string>
    <string name="icon_shape_override_label_location" >"在主屏幕上"</string>
    <string name="icon_shape_system_default" >"使用系统默认设置"</string>
    <string name="icon_shape_square" >"方形"</string>
    <string name="icon_shape_squircle" >"方圆形"</string>
    <string name="icon_shape_circle" >"圆形"</string>
    <string name="icon_shape_teardrop" >"泪珠形"</string>
    <string name="icon_shape_override_progress" >"正在应用图标形状更改"</string>
    <string name="package_state_unknown" >"未知"</string>
    <string name="abandoned_clean_this" >"移除"</string>
    <string name="abandoned_search" >"搜索"</string>
    <string name="abandoned_promises_title" >"未安装此应用"</string>
    <string name="abandoned_promise_explanation" >"未安装此图标对应的应用。您可以移除此图标，也可以尝试搜索相应的应用并手动安装。"</string>
    <string name="app_downloading_title" >"正在下载<xliff:g id="NAME">%1$s</xliff:g>，已完成 <xliff:g id="PROGRESS">%2$s</xliff:g>"</string>
    <string name="app_waiting_download_title" >"<xliff:g id="NAME">%1$s</xliff:g>正在等待安装"</string>
    <string name="widgets_bottom_sheet_title" >"<xliff:g id="NAME">%1$s</xliff:g>微件"</string>
    <string name="widgets_list" >"微件列表"</string>
    <string name="widgets_list_closed" >"微件列表已关闭"</string>
    <string name="action_add_to_workspace" >"添加到主屏幕"</string>
    <string name="action_move_here" >"将项目移至此处"</string>
    <string name="item_added_to_workspace" >"已将项目添加到主屏幕"</string>
    <string name="item_removed" >"项目已移除"</string>
    <string name="action_move" >"移动项目"</string>
    <string name="move_to_empty_cell" >"移至第 <xliff:g id="NUMBER_0">%1$s</xliff:g> 行第 <xliff:g id="NUMBER_1">%2$s</xliff:g> 列"</string>
    <string name="move_to_position" >"移至第 <xliff:g id="NUMBER">%1$s</xliff:g> 个位置"</string>
    <string name="move_to_hotseat_position" >"移至收藏夹第 <xliff:g id="NUMBER">%1$s</xliff:g> 个位置"</string>
    <string name="item_moved" >"已移动项目"</string>
    <string name="add_to_folder" >"添加到“<xliff:g id="NAME">%1$s</xliff:g>”文件夹"</string>
    <string name="add_to_folder_with_app" >"添加到“<xliff:g id="NAME">%1$s</xliff:g>”所在文件夹"</string>
    <string name="added_to_folder" >"项目已添加到文件夹"</string>
    <string name="create_folder_with" >"创建“<xliff:g id="NAME">%1$s</xliff:g>”文件夹"</string>
    <string name="folder_created" >"文件夹已创建"</string>
    <string name="action_move_to_workspace" >"移至主屏幕"</string>
    <string name="action_resize" >"调整大小"</string>
    <string name="action_increase_width" >"增加宽度"</string>
    <string name="action_increase_height" >"增加高度"</string>
    <string name="action_decrease_width" >"减小宽度"</string>
    <string name="action_decrease_height" >"减小高度"</string>
    <string name="widget_resized" >"微件尺寸已调整为：宽度 <xliff:g id="NUMBER_0">%1$s</xliff:g>，高度 <xliff:g id="NUMBER_1">%2$s</xliff:g>"</string>
    <string name="action_deep_shortcut" >"快捷方式"</string>
    <string name="shortcuts_menu_with_notifications_description" >"快捷方式和通知"</string>
    <string name="action_dismiss_notification" >"关闭"</string>
    <string name="notification_dismissed" >"已关闭通知"</string>
    <string name="all_apps_personal_tab" >"个人"</string>
    <string name="all_apps_work_tab" >"工作"</string>
    <string name="work_profile_toggle_label" >"工作资料"</string>
    <string name="bottom_work_tab_user_education_title" >"请在此处查找工作应用"</string>
    <string name="bottom_work_tab_user_education_body" >"每个工作应用均有一个徽标，并由贵单位负责确保其安全。请将工作应用移到主屏幕，以便轻松访问。"</string>
    <string name="work_mode_on_label" >"由贵单位管理"</string>
    <string name="work_mode_off_label" >"通知和应用均已关闭"</string>
    <string name="bottom_work_tab_user_education_close_button" >"关闭"</string>
    <string name="bottom_work_tab_user_education_closed" >"已关闭"</string>
</resources>


```



# /packages/apps/Bluetooth/
## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Bluetooth/res/values-zh-rCN/strings.xml
packages_apps_Bluetooth_res_strings_cn.xml

```


<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="permlab_bluetoothShareManager" >"使用下载管理器。"</string>
    <string name="permdesc_bluetoothShareManager" >"允许应用访问蓝牙共享 (BluetoothShare) 管理器并将其用于传输文件。"</string>
    <string name="permlab_bluetoothWhitelist" >"将蓝牙设备列入访问权限白名单。"</string>
    <string name="permdesc_bluetoothWhitelist" >"允许该应用暂时将某个蓝牙设备列入白名单，从而允许该设备在不经过用户确认的情况下，将文件发送到本设备。"</string>
    <string name="bt_share_picker_label" >"蓝牙"</string>
    <string name="unknown_device" >"未知设备"</string>
    <string name="unknownNumber" >"未知号码"</string>
    <string name="airplane_error_title" >"飞行模式"</string>
    <string name="airplane_error_msg" >"飞行模式中不能使用蓝牙。"</string>
    <string name="bt_enable_title" ></string>
    <string name="bt_enable_line1" >"要使用蓝牙服务，您需要先开启蓝牙。"</string>
    <string name="bt_enable_line2" >"要现在开启蓝牙吗？"</string>
    <string name="bt_enable_cancel" >"取消"</string>
    <string name="bt_enable_ok" >"开启"</string>
    <string name="incoming_file_confirm_title" >"文件传输"</string>
    <string name="incoming_file_confirm_content" >"要接受传入的文件吗？"</string>
    <string name="incoming_file_confirm_cancel" >"拒绝"</string>
    <string name="incoming_file_confirm_ok" >"接受"</string>
    <string name="incoming_file_confirm_timeout_ok" >"确定"</string>
    <string name="incoming_file_confirm_timeout_content" >"接受来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件时发生超时"</string>
    <string name="incoming_file_confirm_Notification_title" >"有人发送文件给您"</string>
    <string name="incoming_file_confirm_Notification_content" >"<xliff:g id="SENDER">%1$s</xliff:g>已准备好发送<xliff:g id="FILE">%2$s</xliff:g>"</string>
    <string name="notification_receiving" >"蓝牙共享：正在接收“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_received" >"蓝牙共享：已接收“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_received_fail" >"蓝牙共享：未收到文件“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sending" >"蓝牙共享：正在发送“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sent" >"蓝牙共享：已发送“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sent_complete" >"已完成100%"</string>
    <string name="notification_sent_fail" >"蓝牙共享：未发送文件“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="download_title" >"文件传输"</string>
    <string name="download_line1" >"来自：“<xliff:g id="SENDER">%1$s</xliff:g>”"</string>
    <string name="download_line2" >"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="download_line3" >"文件大小：<xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="download_line4" ></string>
    <string name="download_line5" >"正在接收文件..."</string>
    <string name="download_cancel" >"停止"</string>
    <string name="download_ok" >"隐藏"</string>
    <string name="incoming_line1" >"来自"</string>
    <string name="incoming_line2" >"文件名"</string>
    <string name="incoming_line3" >"大小"</string>
    <string name="download_fail_line1" >"未收到文件"</string>
    <string name="download_fail_line2" >"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="download_fail_line3" >"原因：<xliff:g id="REASON">%1$s</xliff:g>"</string>
    <string name="download_fail_ok" >"确定"</string>
    <string name="download_succ_line5" >"已接收文件"</string>
    <string name="download_succ_ok" >"打开"</string>
    <string name="upload_line1" >"发给：“<xliff:g id="RECIPIENT">%1$s</xliff:g>”"</string>
    <string name="upload_line3" >"文件类型：<xliff:g id="TYPE">%1$s</xliff:g> (<xliff:g id="SIZE">%2$s</xliff:g>)"</string>
    <string name="upload_line5" >"正在发送文件..."</string>
    <string name="upload_succ_line5" >"已发送文件"</string>
    <string name="upload_succ_ok" >"确定"</string>
    <string name="upload_fail_line1" >"文件未发送至“<xliff:g id="RECIPIENT">%1$s</xliff:g>”。"</string>
    <string name="upload_fail_line1_2" >"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="upload_fail_ok" >"请重试"</string>
    <string name="upload_fail_cancel" >"关闭"</string>
    <string name="bt_error_btn_ok" >"确定"</string>
    <string name="unknown_file" >"未知文件"</string>
    <string name="unknown_file_desc" >"没有可处理此类文件的应用。\n"</string>
    <string name="not_exist_file" >"没有文件"</string>
    <string name="not_exist_file_desc" >"该文件不存在。\n"</string>
    <string name="enabling_progress_title" >"请稍候..."</string>
    <string name="enabling_progress_content" >"正在打开蓝牙..."</string>
    <string name="bt_toast_1" >"系统将会接收该文件。请在通知面板中检查进度。"</string>
    <string name="bt_toast_2" >"无法接收该文件。"</string>
    <string name="bt_toast_3" >"已停止接收来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_toast_4" >"正在向“<xliff:g id="RECIPIENT">%1$s</xliff:g>”发送文件"</string>
    <string name="bt_toast_5" >"正在向“<xliff:g id="RECIPIENT">%2$s</xliff:g>”发送<xliff:g id="NUMBER">%1$s</xliff:g>个文件"</string>
    <string name="bt_toast_6" >"已停止向“<xliff:g id="RECIPIENT">%1$s</xliff:g>”发送文件"</string>
    <string name="bt_sm_2_1" product="nosdcard" >"USB存储设备空间不足，无法保存来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_sm_2_1" product="default" >"SD卡存储空间不足，无法保存来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_sm_2_2" >"所需空间：<xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="ErrorTooManyRequests" >"正在处理的请求太多。请稍后重试。"</string>
    <string name="status_pending" >"尚未开始传输文件。"</string>
    <string name="status_running" >"正在传输文件。"</string>
    <string name="status_success" >"已成功完成文件传输。"</string>
    <string name="status_not_accept" >"不支持此内容。"</string>
    <string name="status_forbidden" >"目标设备禁止进行传输。"</string>
    <string name="status_canceled" >"用户取消了传输。"</string>
    <string name="status_file_error" >"存储问题。"</string>
    <string name="status_no_sd_card" product="nosdcard" >"没有USB存储设备。"</string>
    <string name="status_no_sd_card" product="default" >"无SD卡。请插入SD卡保存传输的文件。"</string>
    <string name="status_connection_error" >"连接失败。"</string>
    <string name="status_protocol_error" >"无法正确处理请求。"</string>
    <string name="status_unknown_error" >"未知错误。"</string>
    <string name="btopp_live_folder" >"通过蓝牙接收的文件"</string>
    <string name="opp_notification_group" >"蓝牙共享"</string>
    <string name="download_success" >"<xliff:g id="FILE_SIZE">%1$s</xliff:g>接收完成。"</string>
    <string name="upload_success" >"<xliff:g id="FILE_SIZE">%1$s</xliff:g>发送完成。"</string>
    <string name="inbound_history_title" >"传入"</string>
    <string name="outbound_history_title" >"传出"</string>
    <string name="no_transfers" >"没有传输历史记录。"</string>
    <string name="transfer_clear_dlg_msg" >"所有内容都将从列表中清除。"</string>
    <string name="outbound_noti_title" >"蓝牙共享：已发送文件"</string>
    <string name="inbound_noti_title" >"蓝牙共享：已接收文件"</string>
    <plurals name="noti_caption_unsuccessful" formatted="false" >
      <item quantity="other"><xliff:g id="UNSUCCESSFUL_NUMBER_1">%1$d</xliff:g> 个文件传输失败。</item>
      <item quantity="one"><xliff:g id="UNSUCCESSFUL_NUMBER_0">%1$d</xliff:g> 个文件传输失败。</item>
    </plurals>
    <plurals name="noti_caption_success" formatted="false" >
      <item quantity="other"><xliff:g id="SUCCESSFUL_NUMBER_1">%1$d</xliff:g> 个文件传输成功，%2$s</item>
      <item quantity="one"><xliff:g id="SUCCESSFUL_NUMBER_0">%1$d</xliff:g> 个文件传输成功，%2$s</item>
    </plurals>
    <string name="transfer_menu_clear_all" >"清除列表"</string>
    <string name="transfer_menu_open" >"打开"</string>
    <string name="transfer_menu_clear" >"从列表中清除"</string>
    <string name="transfer_clear_dlg_title" >"清除"</string>
    <string name="bluetooth_map_settings_save" >"保存"</string>
    <string name="bluetooth_map_settings_cancel" >"取消"</string>
    <string name="bluetooth_map_settings_intro" >"选择您要通过蓝牙共享的帐号。连接时，您仍必须接受所有帐号访问请求。"</string>
    <string name="bluetooth_map_settings_count" >"剩余空档数："</string>
    <string name="bluetooth_map_settings_app_icon" >"应用图标"</string>
    <string name="bluetooth_map_settings_title" >"蓝牙消息共享设置"</string>
    <string name="bluetooth_map_settings_no_account_slots_left" >"无法选择帐号，目前没有任何空档"</string>
    <string name="bluetooth_connected" >"蓝牙音频已连接"</string>
    <string name="bluetooth_disconnected" >"蓝牙音频已断开连接"</string>
    <string name="a2dp_sink_mbs_label" >"蓝牙音频"</string>
    <string name="bluetooth_opp_file_limit_exceeded" >"无法传输 4GB 以上的文件"</string>
</resources>


```



# /packages/apps/Nfc/

## 中文

### strings_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Nfc/res/values-zh-rCN/strings.xml
packages_apps_Nfc_res_strings_cn.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
    <string name="app_name" >"NFC服务"</string>
    <string name="nfcUserLabel" >"NFC"</string>
    <string name="accessibility_nfc_enabled" >"NFC已开启。"</string>
    <string name="tap_to_beam" >"点按即可传输"</string>
    <string name="beam_progress" >"正在接收传输内容..."</string>
    <string name="beam_outgoing" >"正在传输信息..."</string>
    <string name="beam_complete" >"传输完毕"</string>
    <string name="beam_failed" >"传输未完成"</string>
    <string name="beam_canceled" >"传输已取消"</string>
    <string name="cancel" >"取消"</string>
    <string name="beam_tap_to_view" >"点按即可查看"</string>
    <string name="beam_handover_not_supported" >"接收者的设备不支持传输较大的文件。"</string>
    <string name="beam_try_again" >"再次让两台设备接触"</string>
    <string name="beam_busy" >"Beam 目前正忙。请在之前的传输任务完成后重试。"</string>
    <string name="device" >"设备"</string>
    <string name="connecting_peripheral" >"正在连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="connected_peripheral" >"已连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="connect_peripheral_failed" >"无法连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="disconnecting_peripheral" >"正在断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接"</string>
    <string name="disconnected_peripheral" >"已断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接"</string>
    <string name="pairing_peripheral" >"正在与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对"</string>
    <string name="pairing_peripheral_failed" >"无法与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对"</string>
    <string name="failed_to_enable_bt" >"无法启用蓝牙"</string>
    <string name="confirm_pairing" >"您确定要与蓝牙设备<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对吗？"</string>
    <string name="pair_yes" >"是"</string>
    <string name="pair_no" >"否"</string>
    <string name="tap_again_to_pay" >"再次点按即可使用“<xliff:g id="APP">%1$s</xliff:g>”付款"</string>
    <string name="tap_again_to_complete" >"再次点按即可使用“<xliff:g id="APP">%1$s</xliff:g>”完成付款"</string>
    <string name="transaction_failure" >"无法使用“<xliff:g id="APP">%1$s</xliff:g>”完成本次交易。"</string>
    <string name="could_not_use_app" >"无法使用“<xliff:g id="APP">%1$s</xliff:g>”。"</string>
    <string name="pay_with" >"付款方式："</string>
    <string name="complete_with" >"使用以下应用完成操作："</string>
    <string name="default_pay_app_removed" >"用于触碰付款的首选服务已遭删除，您要选择使用其他服务吗？"</string>
    <string name="ask_nfc_tap" >"请点按其他设备来完成操作"</string>
    <string name="wifi_connect" >"连接"</string>
    <string name="status_unable_to_connect" >"无法连接到网络"</string>
    <string name="status_wifi_connected" >"已连接"</string>
    <string name="title_connect_to_network" >"连接到网络"</string>
    <string name="prompt_connect_to_network" >"连接到网络<xliff:g id="NETWORK_SSID">%1$s</xliff:g>？"</string>
    <string name="beam_requires_nfc_enabled" >"需要开启NFC才能使用Android Beam。要开启吗？"</string>
    <string name="android_beam" >"Android Beam"</string>
    <string name="beam_requires_external_storage_permission" >"应用不具备外部存储权限。需要此权限才能传输此文件"</string>
    <string name="title_confirm_url_open" >"要打开链接吗？"</string>
    <string name="summary_confirm_url_open" product="tablet" >"您的平板电脑已通过 NFC 收到一个链接："</string>
    <string name="summary_confirm_url_open" product="default" >"您的手机已通过 NFC 收到一个链接："</string>
    <string name="action_confirm_url_open" >"打开链接"</string>
</resources>

```
