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
    <string name="yes" msgid="4676390750360727396">"是"</string>
    <string name="no" msgid="6731231425810196216">"否"</string>
    <string name="create" msgid="3578857613172647409">"创建"</string>
    <string name="allow" msgid="3349662621170855910">"允许"</string>
    <string name="deny" msgid="6947806159746484865">"拒绝"</string>
    <string name="dlg_close" msgid="7471087791340790015">"关闭"</string>
    <string name="dlg_switch" msgid="6243971420240639064">"切换"</string>
    <string name="device_info_default" msgid="7847265875578739287">"未知"</string>
    <plurals name="show_dev_countdown" formatted="false" msgid="7201398282729229649">
      <item quantity="other">现在只需再执行 <xliff:g id="STEP_COUNT_1">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
      <item quantity="one">现在只需再执行 <xliff:g id="STEP_COUNT_0">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
    </plurals>
    <string name="show_dev_on" msgid="1110711554982716293">"您现在处于开发者模式！"</string>
    <string name="show_dev_already" msgid="2151632240145446227">"您已处于开发者模式，无需进行此操作。"</string>
    <string name="dev_settings_disabled_warning" msgid="4909448907673974370">"请先启用开发者选项。"</string>
    <string name="header_category_wireless_networks" msgid="5110914332313954940">"无线和网络"</string>
    <string name="header_category_connections" msgid="6471513040815680662">"无线和网络"</string>
    <string name="header_category_device" msgid="4544026001618307754">"设备"</string>
    <string name="header_category_personal" msgid="3310195187905720823">"个人"</string>
    <string name="header_category_access" msgid="7580499097416970962">"帐号和权限"</string>
    <string name="header_category_system" msgid="2816866961183068977">"系统"</string>
    <string name="radio_info_data_connection_enable" msgid="8656750679353982712">"启用数据网络连接"</string>
    <string name="radio_info_data_connection_disable" msgid="8541302390883231216">"停用数据网络连接"</string>
    <string name="volte_provisioned_switch_string" msgid="7979882929810283786">"已配置 VoLTE"</string>
    <string name="vt_provisioned_switch_string" msgid="7876998291744854759">"已配置视频通话"</string>
    <string name="wfc_provisioned_switch_string" msgid="3985406545172898078">"已配置 WLAN 通话"</string>
    <string name="eab_provisioned_switch_string" msgid="3482272907448592975">"已配置 EAB/Presence"</string>
    <string name="radio_info_radio_power" msgid="7187666084867419643">"移动无线装置电源"</string>
    <string name="radioInfo_menu_viewADN" msgid="7069468158519465139">"查看 SIM 卡通讯录"</string>
    <string name="radioInfo_menu_viewFDN" msgid="7934301566925610318">"查看固定拨号号码"</string>
    <string name="radioInfo_menu_viewSDN" msgid="7130280686244955669">"查看服务拨号"</string>
    <string name="radioInfo_menu_getIMS" msgid="185171476413967831">"IMS 服务状态"</string>
    <string name="radio_info_ims_reg_status_title" msgid="16971785902696970">"IMS 状态"</string>
    <string name="radio_info_ims_reg_status_registered" msgid="5614116179751126247">"已注册"</string>
    <string name="radio_info_ims_reg_status_not_registered" msgid="4438054067642750717">"未注册"</string>
    <string name="radio_info_ims_feature_status_available" msgid="3687807290327566879">"可供使用"</string>
    <string name="radio_info_ims_feature_status_unavailable" msgid="4606182208970114368">"无法使用"</string>
    <string name="radio_info_ims_reg_status" msgid="7534612158445529715">"IMS 注册：<xliff:g id="STATUS">%1$s</xliff:g>\nLTE 语音通话：<xliff:g id="AVAILABILITY_0">%2$s</xliff:g>\nWLAN 语音通话：<xliff:g id="AVAILABILITY_1">%3$s</xliff:g>\n视频通话：<xliff:g id="AVAILABILITY_2">%4$s</xliff:g>\nUT 接口：<xliff:g id="AVAILABILITY_3">%5$s</xliff:g>"</string>
    <string name="radioInfo_service_in" msgid="1697703164394784618">"服务中"</string>
    <string name="radioInfo_service_out" msgid="7999094221728929681">"不在服务区"</string>
    <string name="radioInfo_service_emergency" msgid="6274434235469661525">"只能拨打紧急呼救电话"</string>
    <string name="radioInfo_service_off" msgid="7536423790014501173">"无线装置已关闭"</string>
    <string name="radioInfo_roaming_in" msgid="9045363884600341051">"漫游"</string>
    <string name="radioInfo_roaming_not" msgid="4849214885629672819">"未使用漫游服务"</string>
    <string name="radioInfo_phone_idle" msgid="7489244938838742820">"空闲"</string>
    <string name="radioInfo_phone_ringing" msgid="4883724645684297895">"响铃"</string>
    <string name="radioInfo_phone_offhook" msgid="5873835692449118954">"正在通话"</string>
    <string name="radioInfo_data_disconnected" msgid="1959735267890719418">"已断开连接"</string>
    <string name="radioInfo_data_connecting" msgid="8404571440697917823">"正在连接"</string>
    <string name="radioInfo_data_connected" msgid="7074301157399238697">"已连接"</string>
    <string name="radioInfo_data_suspended" msgid="5315325487890334196">"已暂停"</string>
    <string name="radioInfo_unknown" msgid="1476509178755955088">"未知"</string>
    <string name="radioInfo_display_packets" msgid="8654359809877290639">"pkts"</string>
    <string name="radioInfo_display_bytes" msgid="4018206969492931883">"字节"</string>
    <string name="radioInfo_display_dbm" msgid="3621221793699882781">"dBm"</string>
    <string name="radioInfo_display_asu" msgid="1422248392727818082">"asu"</string>
    <string name="radioInfo_lac" msgid="8415219164758307156">"LAC"</string>
    <string name="radioInfo_cid" msgid="4362599198392643138">"CID"</string>
    <string name="sdcard_unmount" product="nosdcard" msgid="6325292633327972272">"卸载USB存储设备"</string>
    <string name="sdcard_unmount" product="default" msgid="3364184561355611897">"卸载SD卡"</string>
    <string name="sdcard_format" product="nosdcard" msgid="6285310523155166716">"格式化USB存储设备"</string>
    <string name="sdcard_format" product="default" msgid="6713185532039187532">"格式化SD卡"</string>
    <string name="preview_pager_content_description" msgid="8926235999291761243">"预览"</string>
    <string name="preview_page_indicator_content_description" msgid="4821343428913401264">"预览第 <xliff:g id="CURRENT_PAGE">%1$d</xliff:g> 页（共 <xliff:g id="NUM_PAGES">%2$d</xliff:g> 页）"</string>
    <string name="font_size_summary" msgid="1690992332887488183">"缩小或放大屏幕上的文字。"</string>
    <string name="font_size_make_smaller_desc" msgid="7919995133365371569">"缩小"</string>
    <string name="font_size_make_larger_desc" msgid="4316986572233686061">"放大"</string>
    <!-- no translation found for font_size_preview_text (4818424565068376732) -->
    <skip />
    <string name="font_size_preview_text_headline" msgid="7955317408475392247">"示例文本"</string>
    <string name="font_size_preview_text_title" msgid="1310536233106975546">"绿野仙踪"</string>
    <string name="font_size_preview_text_subtitle" msgid="4231671528173110093">"第 11 章：奥兹国神奇的翡翠城"</string>
    <string name="font_size_preview_text_body" msgid="2846183528684496723">"尽管有绿色眼镜保护着眼睛，桃乐丝和她的朋友们在刚看到这座奇妙的城市时，还是被它耀眼的光芒照得眼花缭乱。街道两旁耸立着绿色大理石砌成的美丽房屋，到处镶嵌着闪闪发光的翡翠。他们走过的人行道同样是用绿色大理石铺砌而成，石块接合处嵌有一排排紧密相连的翡翠，在阳光的照耀下闪闪发亮。房屋的窗户镶嵌着绿色的玻璃，城市上空有淡淡的绿晕，就连阳光也散发着绿色光芒。\n\n街道上有很多行人，无论男女老少全都穿着绿色衣物，连皮肤都泛着绿色。他们都用惊异的眼光注视着桃乐丝和她这群外貌迥异的伙伴们，孩子们一看到狮子都拔腿跑了，躲到他们母亲身后；没有人想开口跟桃乐丝他们说话。街上有许多商店，桃乐丝看见店里的每一件商品都是绿色的，有绿色的糖果，绿色的爆米花，还有各种各样的绿鞋子、绿帽子和绿衣服。有位小贩在路上卖绿色的柠檬水，当孩子们去买汽水时，桃乐丝发现他们付钱时所用的硬币竟然也是绿色的。\n\n城里似乎没有马，也没有其他任何动物。有人用绿色小推车来回运载物品。每个人看起来都是那么快乐、满足，一切都显得安定繁荣。"</string>
    <string name="font_size_save" msgid="3450855718056759095">"确定"</string>
    <string name="sdcard_setting" product="nosdcard" msgid="8281011784066476192">"USB存储设备"</string>
    <string name="sdcard_setting" product="default" msgid="5922637503871474866">"SD卡"</string>
    <string name="bluetooth" msgid="5549625000628014477">"蓝牙"</string>
    <string name="bluetooth_is_discoverable" msgid="8373421452106840526">"附近所有蓝牙设备均可检测到此设备（<xliff:g id="DISCOVERABLE_TIME_PERIOD">%1$s</xliff:g>）"</string>
    <string name="bluetooth_is_discoverable_always" msgid="2849387702249327748">"附近所有蓝牙设备均可检测到此设备"</string>
    <string name="bluetooth_not_visible_to_other_devices" msgid="9120274591523391910">"其他蓝牙设备检测不到此设备"</string>
    <string name="bluetooth_only_visible_to_paired_devices" msgid="2049983392373296028">"已配对的设备可检测到此设备"</string>
    <string name="bluetooth_visibility_timeout" msgid="8002247464357005429">"检测超时设置"</string>
    <string name="bluetooth_lock_voice_dialing" msgid="3139322992062086225">"锁定语音拨号"</string>
    <string name="bluetooth_lock_voice_dialing_summary" msgid="4741338867496787042">"屏幕锁定时停止使用蓝牙拨号器"</string>
    <string name="bluetooth_devices" msgid="1886018064039454227">"蓝牙设备"</string>
    <string name="bluetooth_device_name" msgid="8415828355207423800">"设备名称"</string>
    <string name="bluetooth_device_details" msgid="4594773497930028085">"设备设置"</string>
    <string name="bluetooth_profile_details" msgid="6823621790324933337">"配置文件设置"</string>
    <string name="bluetooth_name_not_set" msgid="2653752006416027426">"未设置名称，使用帐号名"</string>
    <string name="bluetooth_scan_for_devices" msgid="9214184305566815727">"扫描设备"</string>
    <string name="bluetooth_rename_device" msgid="4352483834491958740">"重命名此设备"</string>
    <string name="bluetooth_rename_button" msgid="1648028693822994566">"重命名"</string>
    <string name="bluetooth_disconnect_title" msgid="7830252930348734303">"要断开与该设备的连接吗？"</string>
    <string name="bluetooth_disconnect_all_profiles" product="default" msgid="8208712728668714199">"您的手机将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_all_profiles" product="tablet" msgid="6611038575213485336">"您的平板电脑将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_all_profiles" product="device" msgid="3995834526315103965">"您的设备将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接。"</string>
    <string name="bluetooth_disconnect_dialog_ok" msgid="3308586619539119106">"断开连接"</string>
    <string name="bluetooth_empty_list_user_restricted" msgid="603521233563983689">"您无权更改蓝牙设置。"</string>
    <string name="bluetooth_pairing_pref_title" msgid="7429413067477968637">"与新设备配对"</string>
    <string name="bluetooth_is_visible_message" msgid="6222396240776971862">"在 <xliff:g id="DEVICE_NAME">%1$s</xliff:g> 上开启蓝牙设置后，附近的设备将可以检测到该设备。"</string>
    <string name="bluetooth_footer_mac_message" product="default" msgid="1109366350000220283">"手机的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_footer_mac_message" product="tablet" msgid="6807634484499166486">"平板电脑的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_footer_mac_message" product="device" msgid="8413944740341742061">"设备的蓝牙地址：<xliff:g id="BLUETOOTH_MAC_ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_is_disconnect_question" msgid="5334933802445256306">"要断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接吗？"</string>
    <string name="bluetooth_broadcasting" msgid="16583128958125247">"广播"</string>
    <string name="bluetooth_disable_profile_title" msgid="5916643979709342557">"要停用配置文件吗？"</string>
    <string name="bluetooth_disable_profile_message" msgid="2895844842011809904">"此操作将停用：&lt;br&gt;&lt;b&gt;<xliff:g id="PROFILE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;来自：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%2$s</xliff:g>&lt;/b&gt;"</string>
    <string name="bluetooth_unknown" msgid="644716244548801421"></string>
    <string name="bluetooth_device" msgid="5291950341750186887">"未命名的蓝牙设备"</string>
    <string name="progress_scanning" msgid="192587958424295789">"正在搜索"</string>
    <string name="bluetooth_no_devices_found" msgid="1085232930277181436">"未在附近找到蓝牙设备。"</string>
    <string name="bluetooth_notif_ticker" msgid="4726721390078512173">"蓝牙配对请求"</string>
    <string name="bluetooth_notif_title" msgid="2485175521845371514">"配对请求"</string>
    <string name="bluetooth_notif_message" msgid="5057417127600942904">"点按即可与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”配对。"</string>
    <string name="bluetooth_show_received_files" msgid="3144149432555230410">"收到的文件"</string>
    <string name="device_picker" msgid="4978696506172252813">"选择蓝牙设备"</string>
    <string name="bluetooth_ask_enablement" msgid="3387222809404177525">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙"</string>
    <string name="bluetooth_ask_disablement" msgid="5890386255790160573">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求关闭蓝牙"</string>
    <string name="bluetooth_ask_enablement_no_name" msgid="1644353686104482763">"某个应用请求开启蓝牙"</string>
    <string name="bluetooth_ask_disablement_no_name" msgid="9218830122674868548">"某个应用请求关闭蓝牙"</string>
    <string name="bluetooth_ask_discovery" product="tablet" msgid="4791779658660357386">"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_discovery" product="default" msgid="1308225382575535366">"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_discovery_no_name" product="tablet" msgid="225715443477752935">"某个应用想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_discovery_no_name" product="default" msgid="4949152735544109994">"某个应用想让其他蓝牙设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_lasting_discovery" product="tablet" msgid="8528329166577187961">"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery" product="default" msgid="4398738575307583138">"<xliff:g id="APP_NAME">%1$s</xliff:g>想让其他蓝牙设备检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery_no_name" product="tablet" msgid="1702590641426207062">"某个应用想让其他蓝牙设备检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_lasting_discovery_no_name" product="default" msgid="8549952177383992238">"某个应用想让其他蓝牙设备检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_discovery" product="tablet" msgid="1141843490422565755">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_enablement_and_discovery" product="default" msgid="5195836980079191473">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%2$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_enablement_and_discovery_no_name" product="tablet" msgid="7009338445281693765">"某个应用请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的平板电脑。"</string>
    <string name="bluetooth_ask_enablement_and_discovery_no_name" product="default" msgid="8386904242279878734">"某个应用请求开启蓝牙，以便其他设备在 <xliff:g id="TIMEOUT">%1$d</xliff:g> 秒内可检测到您的手机。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery" product="tablet" msgid="2279471426575892686">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备可检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery" product="default" msgid="6961969825475461450">"<xliff:g id="APP_NAME">%1$s</xliff:g>请求开启蓝牙，以便其他设备可检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery_no_name" product="tablet" msgid="692477613671555006">"某个应用请求开启蓝牙，以便其他设备可检测到您的平板电脑。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_ask_enablement_and_lasting_discovery_no_name" product="default" msgid="6374480121751597648">"某个应用请求开启蓝牙，以便其他设备可检测到您的手机。您可以稍后在“蓝牙”设置中更改此设置。"</string>
    <string name="bluetooth_turning_on" msgid="4850574877288036646">"正在打开蓝牙..."</string>
    <string name="bluetooth_turning_off" msgid="2337747302892025192">"正在关闭蓝牙…"</string>
    <string name="bluetooth_auto_connect" msgid="40711424456733571">"自动连接"</string>
    <string name="bluetooth_connection_permission_request" msgid="4747918249032890077">"蓝牙连接请求"</string>
    <string name="bluetooth_connection_notif_message" msgid="3603316575471431846">"点按即可连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”。"</string>
    <string name="bluetooth_connection_dialog_text" msgid="8455427559949998023">"要连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”吗？"</string>
    <string name="bluetooth_phonebook_request" msgid="3951420080540915279">"电话簿权限申请"</string>
    <string name="bluetooth_pb_acceptance_dialog_text" msgid="8930347091018455505">"<xliff:g id="DEVICE_NAME_0">%1$s</xliff:g> 想访问您的通讯录和通话记录。要向 <xliff:g id="DEVICE_NAME_1">%2$s</xliff:g> 授予访问权限吗？"</string>
    <string name="bluetooth_remember_choice" msgid="6919682671787049800">"不要再询问"</string>
    <string name="bluetooth_pb_remember_choice" msgid="3622898084442402071">"下次不再询问"</string>
    <string name="bluetooth_map_request" msgid="4595727689513143902">"消息权限申请"</string>
    <string name="bluetooth_map_acceptance_dialog_text" msgid="8712508202081143737">"“%1$s”想要查看您的消息。要向“%2$s”授予权限吗？"</string>
    <string name="bluetooth_sap_request" msgid="2669762224045354417">"SIM 访问权限请求"</string>
    <string name="bluetooth_sap_acceptance_dialog_text" msgid="4414253873553608690">"<xliff:g id="DEVICE_NAME_0">%1$s</xliff:g>想要访问您的 SIM 卡。如果授权该设备访问 SIM 卡，您设备的数据连接功能在蓝牙连接期间将停用。将访问权限授予<xliff:g id="DEVICE_NAME_1">%2$s?</xliff:g>"</string>
    <string name="bluetooth_device_name_summary" msgid="522235742194965734">"向其他设备显示为“<xliff:g id="DEVICE_NAME">^1</xliff:g>”"</string>
    <string name="bluetooth_off_footer" msgid="8406865700572772936">"开启蓝牙即可连接到其他设备。"</string>
    <string name="bluetooth_paired_device_title" msgid="8638994696317952019">"您的设备"</string>
    <string name="bluetooth_pairing_page_title" msgid="7712127387361962608">"与新设备配对"</string>
    <string name="bluetooth_pref_summary" product="tablet" msgid="3520035819421024105">"允许您的平板电脑与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_pref_summary" product="device" msgid="2205100629387332862">"允许您的设备与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_pref_summary" product="default" msgid="782032074675157079">"允许您的手机与附近的蓝牙设备进行通信"</string>
    <string name="bluetooth_disable_a2dp_hw_offload" msgid="4936610906348223810">"停用蓝牙 A2DP 硬件卸载"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_title" msgid="4340101417209145308">"要重启设备吗？"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_message" msgid="8827019472003234568">"您需要重启设备才能让这项设置更改生效。"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_confirm" msgid="2053793518537051975">"重启"</string>
    <string name="bluetooth_disable_a2dp_hw_offload_dialog_cancel" msgid="2382443064737856652">"取消"</string>
    <string name="connected_device_available_media_title" msgid="2560067541413280645">"可用的媒体设备"</string>
    <string name="connected_device_available_call_title" msgid="697154660967595684">"可用的通话设备"</string>
    <string name="connected_device_connected_title" msgid="5871712271201945606">"当前已连接"</string>
    <string name="connected_device_saved_title" msgid="688364359746674536">"保存的设备"</string>
    <string name="connected_device_add_device_title" msgid="7803521577708810621">"添加设备"</string>
    <string name="connected_device_add_device_summary" msgid="4041865900298680338">"系统将开启蓝牙以进行配对"</string>
    <string name="connected_device_connections_title" msgid="5988939345181466770">"连接偏好设置"</string>
    <string name="connected_device_previously_connected_title" msgid="491765792822244604">"之前连接的设备"</string>
    <string name="date_and_time" msgid="9062980487860757694">"日期和时间"</string>
    <string name="choose_timezone" msgid="1362834506479536274">"选择时区"</string>
    <!-- no translation found for intent_sender_data_label (6332324780477289261) -->
    <skip />
    <string name="intent_sender_sendbroadcast_text" msgid="1415735148895872715">"发送<xliff:g id="BROADCAST">broadcast</xliff:g>"</string>
    <string name="intent_sender_action_label" msgid="616458370005452389">"<xliff:g id="ACTION">Action</xliff:g>:"</string>
    <string name="intent_sender_startactivity_text" msgid="5080516029580421895">"启动<xliff:g id="ACTIVITY">activity</xliff:g>"</string>
    <string name="intent_sender_resource_label" msgid="6963659726895482829">"<xliff:g id="RESOURCE">Resource</xliff:g>:"</string>
    <string name="intent_sender_account_label" msgid="465210404475603404">"帐号:"</string>
    <string name="proxy_settings_title" msgid="9049437837600320881">"代理"</string>
    <string name="proxy_clear_text" msgid="5555400754952012657">"清除"</string>
    <string name="proxy_port_label" msgid="5655276502233453400">"代理服务器端口"</string>
    <string name="proxy_exclusionlist_label" msgid="7700491504623418701">"对以下网址不使用代理"</string>
    <string name="proxy_defaultView_text" msgid="6387985519141433291">"恢复默认设置"</string>
    <string name="proxy_action_text" msgid="2957063145357903951">"完成"</string>
    <string name="proxy_hostname_label" msgid="8490171412999373362">"代理服务器主机名"</string>
    <string name="proxy_error" msgid="8926675299638611451">"注意"</string>
    <string name="proxy_error_dismiss" msgid="4993171795485460060">"确定"</string>
    <string name="proxy_error_invalid_host" msgid="6865850167802455230">"您键入的主机名无效。"</string>
    <string name="proxy_error_invalid_exclusion_list" msgid="678527645450894773">"您输入的排除列表格式有误。请输入以逗号分隔的排除网域列表。"</string>
    <string name="proxy_error_empty_port" msgid="5539106187558215246">"您需要填写端口字段。"</string>
    <string name="proxy_error_empty_host_set_port" msgid="2451694104858226781">"如果主机字段为空，则端口字段必须为空。"</string>
    <string name="proxy_error_invalid_port" msgid="5988270202074492710">"您键入的端口无效。"</string>
    <string name="proxy_warning_limited_support" msgid="7229337138062837422">"浏览器会使用 HTTP 代理，但其他应用可能不会使用。"</string>
    <string name="proxy_url_title" msgid="7185282894936042359">"PAC网址： "</string>
    <string name="radio_info_dl_kbps" msgid="6894556071523815984">"DL 带宽 (kbps)："</string>
    <string name="radio_info_ul_kbps" msgid="946464073571185678">"UL 带宽 (kbps)："</string>
    <string name="radio_info_signal_location_label" msgid="3242990404410530456">"移动网络位置信息（已弃用）："</string>
    <string name="radio_info_neighboring_location_label" msgid="5766020323342985397">"邻域移动网络信息（已弃用）："</string>
    <string name="radio_info_phy_chan_config" msgid="7133247058801474028">"LTE 物理信道配置："</string>
    <string name="radio_info_cell_info_refresh_rate" msgid="7062777594049622128">"移动网络信息刷新频率："</string>
    <string name="radio_info_cellinfo_label" msgid="6213223844927623098">"所有移动网络测量信息："</string>
    <string name="radio_info_dcrtinfo_label" msgid="4062076024399431876">"数据网络连接实时信息："</string>
    <string name="radio_info_gprs_service_label" msgid="4209624131644060517">"数据服务："</string>
    <string name="radio_info_roaming_label" msgid="6141505430275138647">"漫游："</string>
    <string name="radio_info_imei_label" msgid="1220524224732944192">"移动通信国际识别码："</string>
    <string name="radio_info_call_redirect_label" msgid="2743797189722106231">"来电转接："</string>
    <string name="radio_info_ppp_resets_label" msgid="3587319503902576102">"启动后重置 PPP 的次数："</string>
    <string name="radio_info_current_network_label" msgid="9151285540639134945">"当前网络："</string>
    <string name="radio_info_ppp_received_label" msgid="363579470428151850">"已接收的数据量："</string>
    <string name="radio_info_gsm_service_label" msgid="1370863866816125489">"语音服务："</string>
    <string name="radio_info_signal_strength_label" msgid="5155734002519307416">"信号强度："</string>
    <string name="radio_info_call_status_label" msgid="2611065018172747413">"语音通话状态："</string>
    <string name="radio_info_ppp_sent_label" msgid="7748668735880404586">"已发送的数据量："</string>
    <string name="radio_info_message_waiting_label" msgid="1037302619943328273">"消息等待："</string>
    <string name="radio_info_phone_number_label" msgid="7942153178953255231">"电话号码："</string>
    <string name="radio_info_band_mode_label" msgid="8730871744887454509">"选择无线装置频道"</string>
    <string name="radio_info_voice_network_type_label" msgid="1443496502370667071">"语音网络类型："</string>
    <string name="radio_info_data_network_type_label" msgid="7094323145105149312">"数据网络类型："</string>
    <string name="radio_info_set_perferred_label" msgid="3511830813500105512">"设置首选网络类型："</string>
    <string name="radio_info_ping_hostname_v4" msgid="7045103377818314709">"ping 主机名(www.google.com) IPv4："</string>
    <string name="radio_info_ping_hostname_v6" msgid="1130906124160553954">"ping 主机名(www.google.com) IPv6："</string>
    <string name="radio_info_http_client_test" msgid="2382286093023138339">"HTTP 客户端测试："</string>
    <string name="ping_test_label" msgid="579228584343892613">"运行 ping 测试"</string>
    <string name="radio_info_smsc_label" msgid="6399460520126501354">"SMSC："</string>
    <string name="radio_info_smsc_update_label" msgid="7258686760358791539">"更新"</string>
    <string name="radio_info_smsc_refresh_label" msgid="6902302130315125102">"刷新"</string>
    <string name="radio_info_toggle_dns_check_label" msgid="6625185764803245075">"切换 DNS 检查"</string>
    <string name="oem_radio_info_label" msgid="6163141792477958941">"特定 OEM 的信息/设置"</string>
    <string name="band_mode_title" msgid="4071411679019296568">"设置无线装置频道模式"</string>
    <string name="band_mode_loading" msgid="3555063585133586152">"正在加载频道列表…"</string>
    <string name="band_mode_set" msgid="5730560180249458484">"设置"</string>
    <string name="band_mode_failed" msgid="1495968863884716379">"失败"</string>
    <string name="band_mode_succeeded" msgid="2701016190055887575">"成功"</string>
    <string name="sdcard_changes_instructions" msgid="4482324130377280131">"重新连接USB线后更改就会生效。"</string>
    <string name="sdcard_settings_screen_mass_storage_text" msgid="3741220147296482474">"启用USB大容量存储设备"</string>
    <string name="sdcard_settings_total_bytes_label" msgid="9184160745785062144">"总字节数："</string>
    <string name="sdcard_settings_not_present_status" product="nosdcard" msgid="1636218515775929394">"未装载USB存储设备。"</string>
    <string name="sdcard_settings_not_present_status" product="default" msgid="2048419626134861599">"无SD卡。"</string>
    <string name="sdcard_settings_available_bytes_label" msgid="763232429899373001">"可用的字节数："</string>
    <string name="sdcard_settings_mass_storage_status" product="nosdcard" msgid="7993410985895217054">"计算机正将USB存储设备用作大容量存储设备。"</string>
    <string name="sdcard_settings_mass_storage_status" product="default" msgid="2742075324087038036">"计算机正将SD卡用作大容量存储设备。"</string>
    <string name="sdcard_settings_unmounted_status" product="nosdcard" msgid="5128923500235719226">"现在可以安全地移除该USB存储设备了。"</string>
    <string name="sdcard_settings_unmounted_status" product="default" msgid="666233604712540408">"现在可以安全地移除SD卡了。"</string>
    <string name="sdcard_settings_bad_removal_status" product="nosdcard" msgid="7761390725880773697">"USB存储设备仍在使用中便被移除！"</string>
    <string name="sdcard_settings_bad_removal_status" product="default" msgid="5145797653495907970">"SD卡仍在使用中便被移除！"</string>
    <string name="sdcard_settings_used_bytes_label" msgid="8820289486001170836">"已使用的字节数："</string>
    <string name="sdcard_settings_scanning_status" product="nosdcard" msgid="7503429447676219564">"正在扫描USB存储设备中的媒体..."</string>
    <string name="sdcard_settings_scanning_status" product="default" msgid="2763464949274455656">"正从SD卡扫描媒体..."</string>
    <string name="sdcard_settings_read_only_status" product="nosdcard" msgid="3624143937437417788">"已将USB存储设备装载为只读设备。"</string>
    <string name="sdcard_settings_read_only_status" product="default" msgid="4518291824764698112">"已将SD卡装载为只读设备。"</string>
    <string name="skip_label" msgid="47510779345218297">"跳过"</string>
    <string name="next_label" msgid="4693520878012668114">"下一步"</string>
    <string name="language_picker_title" msgid="3596315202551687690">"语言"</string>
    <string name="pref_title_lang_selection" msgid="2014920136978776034">"语言偏好设置"</string>
    <string name="locale_remove_menu" msgid="7651301406723638854">"移除"</string>
    <string name="add_a_language" msgid="2330538812283783022">"添加语言"</string>
    <plurals name="dlg_remove_locales_title" formatted="false" msgid="4276642359346122396">
      <item quantity="other">要移除所选语言吗？</item>
      <item quantity="one">要移除所选语言吗？</item>
    </plurals>
    <string name="dlg_remove_locales_message" msgid="1361354927342876114">"系统将以其他语言显示文字。"</string>
    <string name="dlg_remove_locales_error_title" msgid="2653242337224911425">"无法移除所有语言"</string>
    <string name="dlg_remove_locales_error_message" msgid="6697381512654262821">"请保留至少一种首选语言"</string>
    <string name="locale_not_translated" msgid="516862628177166755">"某些应用可能无法以该语言显示"</string>
    <string name="action_drag_label_move_up" msgid="9052210023727612540">"上移"</string>
    <string name="action_drag_label_move_down" msgid="7448713844582912157">"下移"</string>
    <string name="action_drag_label_move_top" msgid="557081389352288310">"移至顶部"</string>
    <string name="action_drag_label_move_bottom" msgid="2468642142414126482">"移至底部"</string>
    <string name="action_drag_label_remove" msgid="2861038147166966206">"移除语言"</string>
    <string name="activity_picker_label" msgid="6295660302548177109">"选择活动"</string>
    <string name="device_info_label" msgid="6551553813651711205">"设备信息"</string>
    <string name="display_label" msgid="8074070940506840792">"屏幕"</string>
    <string name="phone_info_label" product="tablet" msgid="7820855350955963628">"平板电脑信息"</string>
    <string name="phone_info_label" product="default" msgid="2127552523124277664">"手机信息"</string>
    <string name="sd_card_settings_label" product="nosdcard" msgid="8101475181301178428">"USB存储设备"</string>
    <string name="sd_card_settings_label" product="default" msgid="5743100901106177102">"SD卡"</string>
    <string name="proxy_settings_label" msgid="3271174136184391743">"代理设置"</string>
    <string name="cancel" msgid="6859253417269739139">"取消"</string>
    <string name="okay" msgid="1997666393121016642">"确定"</string>
    <string name="forget" msgid="1400428660472591263">"取消保存"</string>
    <string name="save" msgid="879993180139353333">"保存"</string>
    <string name="done" msgid="6942539184162713160">"完成"</string>
    <string name="apply" msgid="1577045208487259229">"应用"</string>
    <string name="settings_label" msgid="1626402585530130914">"设置"</string>
    <string name="settings_label_launcher" msgid="8344735489639482340">"设置"</string>
    <string name="settings_shortcut" msgid="3936651951364030415">"设置快捷方式"</string>
    <string name="activity_list_empty" msgid="6428823323471264836">"未找到匹配的活动。"</string>
    <string name="airplane_mode" msgid="8837269988154128601">"飞行模式"</string>
    <string name="radio_controls_title" msgid="3447085191369779032">"更多"</string>
    <string name="wireless_networks_settings_title" msgid="3643009077742794212">"无线和网络"</string>
    <string name="radio_controls_summary" msgid="1838624369870907268">"管理WLAN、蓝牙、飞行模式、移动网络和VPN"</string>
    <string name="cellular_data_title" msgid="6835451574385496662">"移动数据"</string>
    <string name="calls_title" msgid="3544471959217176768">"通话"</string>
    <string name="sms_messages_title" msgid="1778636286080572535">"短信"</string>
    <string name="cellular_data_summary" msgid="4660351864416939504">"允许使用移动数据网络"</string>
    <string name="allow_data_usage_title" msgid="2238205944729213062">"允许漫游时使用数据流量"</string>
    <string name="roaming" msgid="3596055926335478572">"移动数据网络漫游"</string>
    <string name="roaming_enable" msgid="3737380951525303961">"漫游时连接到移动数据网络服务"</string>
    <string name="roaming_disable" msgid="1295279574370898378">"漫游时连接到移动数据网络服务"</string>
    <string name="roaming_reenable_message" msgid="9141007271031717369">"移动数据网络连接已断开，因为您已离开本地网络并关闭了移动数据网络漫游功能。"</string>
    <string name="roaming_turn_it_on_button" msgid="4387601818162120589">"将其启用"</string>
    <string name="roaming_warning" msgid="4275443317524544705">"这可能会产生高额费用。"</string>
    <string name="roaming_warning_multiuser" product="tablet" msgid="6458990250829214777">"如果允许数据漫游，您可能需要支付高昂的漫游费用！\n\n此设置会影响这部平板电脑上的所有用户。"</string>
    <string name="roaming_warning_multiuser" product="default" msgid="6368421100292355440">"如果允许数据漫游，您可能需要支付高昂的漫游费用！\n\n此设置会影响这部手机上的所有用户。"</string>
    <string name="roaming_reenable_title" msgid="6068100976707316772">"允许移动数据网络漫游吗？"</string>
    <string name="networks" msgid="6333316876545927039">"运营商选择"</string>
    <string name="sum_carrier_select" msgid="3616956422251879163">"选择网络运营商"</string>
    <string name="date_and_time_settings_title" msgid="3350640463596716780">"日期和时间"</string>
    <string name="date_and_time_settings_title_setup_wizard" msgid="2391530758339384324">"设置日期和时间"</string>
    <string name="date_and_time_settings_summary" msgid="7095318986757583584">"设置日期、时间、时区和格式"</string>
    <string name="date_time_auto" msgid="7076906458515908345">"自动确定日期和时间"</string>
    <string name="date_time_auto_summaryOn" msgid="4609619490075140381">"使用网络提供的时间"</string>
    <string name="date_time_auto_summaryOff" msgid="8698762649061882791">"使用网络提供的时间"</string>
    <string name="zone_auto" msgid="334783869352026648">"自动确定时区"</string>
    <string name="zone_auto_summaryOn" msgid="6142830927278458314">"使用网络提供的时区"</string>
    <string name="zone_auto_summaryOff" msgid="2597745783162041390">"使用网络提供的时区"</string>
    <string name="date_time_24hour_auto" msgid="2117383168985653422">"自动使用 24 小时制"</string>
    <string name="date_time_24hour_auto_summary" msgid="6351812925651480277">"使用默认语言区域"</string>
    <string name="date_time_24hour_title" msgid="3203537578602803850">"24小时制"</string>
    <string name="date_time_24hour" msgid="1193032284921000063">"使用 24 小时制"</string>
    <string name="date_time_set_time_title" msgid="6296795651349047016">"时间"</string>
    <string name="date_time_set_time" msgid="5716856602742530696">"设置时间"</string>
    <string name="date_time_set_timezone_title" msgid="3047322337368233197">"时区"</string>
    <string name="date_time_set_timezone" msgid="5045627174274377814">"选择时区"</string>
    <string name="date_time_set_date_title" msgid="6928286765325608604">"日期"</string>
    <string name="date_time_set_date" msgid="7021491668550232105">"设置日期"</string>
    <string name="date_time_search_region" msgid="2478334699004021972">"搜索区域"</string>
    <string name="date_time_select_region" msgid="5434001881313168586">"区域"</string>
    <string name="date_time_select_zone" msgid="8883690857762652278">"时区"</string>
    <string name="date_time_set_timezone_in_region" msgid="7935631939393423886">"<xliff:g id="REGION">%1$s</xliff:g>的时区"</string>
    <string name="date_time_select_fixed_offset_time_zones" msgid="6084375085203448645">"选择世界协调时间 (UTC) 偏移量"</string>
    <string name="zone_list_menu_sort_alphabetically" msgid="5683377702671088588">"按字母顺序排序"</string>
    <string name="zone_list_menu_sort_by_timezone" msgid="2720190443744884114">"按时区排序"</string>
    <string name="zone_change_to_from_dst" msgid="118656001224045590">"<xliff:g id="TIME_TYPE">%1$s</xliff:g>开始于 <xliff:g id="TRANSITION_DATE">%2$s</xliff:g>。"</string>
    <string name="zone_info_exemplar_location_and_offset" msgid="1359698475641349336">"<xliff:g id="EXEMPLAR_LOCATION">%1$s</xliff:g> (<xliff:g id="OFFSET">%2$s</xliff:g>)"</string>
    <string name="zone_info_offset_and_name" msgid="164876167707284017">"<xliff:g id="TIME_TYPE">%2$s</xliff:g> (<xliff:g id="OFFSET">%1$s</xliff:g>)"</string>
    <string name="zone_info_footer" msgid="4192803402331390389">"采用<xliff:g id="OFFSET_AND_NAME">%1$s</xliff:g>。<xliff:g id="DST_TIME_TYPE">%2$s</xliff:g>从 <xliff:g id="TRANSITION_DATE">%3$s</xliff:g>开始。"</string>
    <string name="zone_info_footer_no_dst" msgid="8652423870143056964">"采用<xliff:g id="OFFSET_AND_NAME">%1$s</xliff:g>。没有夏令时。"</string>
    <string name="zone_time_type_dst" msgid="8850494578766845276">"夏令时"</string>
    <string name="zone_time_type_standard" msgid="3462424485380376522">"标准时间"</string>
    <string name="zone_menu_by_region" msgid="8370437123807764346">"按区域选择"</string>
    <string name="zone_menu_by_offset" msgid="7161573994228041794">"按世界协调时间 (UTC) 偏移量选择"</string>
    <string name="date_picker_title" msgid="1338210036394128512">"日期"</string>
    <string name="time_picker_title" msgid="483460752287255019">"时间"</string>
    <string name="lock_after_timeout" msgid="4590337686681194648">"自动锁定"</string>
    <string name="lock_after_timeout_summary" msgid="6128431871360905631">"休眠<xliff:g id="TIMEOUT_STRING">%1$s</xliff:g>后"</string>
    <string name="lock_immediately_summary_with_exception" msgid="9119632173886172690">"休眠后立即锁定屏幕（<xliff:g id="TRUST_AGENT_NAME">%1$s</xliff:g>让屏幕保持解锁状态时除外）"</string>
    <string name="lock_after_timeout_summary_with_exception" msgid="5579064842797188409">"休眠<xliff:g id="TIMEOUT_STRING">%1$s</xliff:g>后（<xliff:g id="TRUST_AGENT_NAME">%2$s</xliff:g>让屏幕保持解锁状态时除外）"</string>
    <string name="show_owner_info_on_lockscreen_label" msgid="5074906168357568434">"在锁定屏幕上显示机主信息"</string>
    <string name="owner_info_settings_title" msgid="5530285568897386122">"锁定屏幕消息"</string>
    <string name="security_enable_widgets_title" msgid="2754833397070967846">"启用微件"</string>
    <string name="security_enable_widgets_disabled_summary" msgid="6392489775303464905">"已被管理员停用"</string>
    <string name="lockdown_settings_title" msgid="7393790212603280213">"显示锁定选项"</string>
    <string name="lockdown_settings_summary" msgid="429230431748285997">"显示可在锁定屏幕上关闭 Smart Lock、指纹解锁和通知功能的电源按钮选项"</string>
    <string name="owner_info_settings_summary" msgid="7472393443779227052">"无"</string>
    <string name="owner_info_settings_status" msgid="120407527726476378">"<xliff:g id="COUNT_0">%1$d</xliff:g> / <xliff:g id="COUNT_1">%2$d</xliff:g>"</string>
    <string name="owner_info_settings_edit_text_hint" msgid="7591869574491036360">"例如，小明的 Android 设备。"</string>
    <string name="user_info_settings_title" msgid="1195015434996724736">"用户信息"</string>
    <string name="show_profile_info_on_lockscreen_label" msgid="2741208907263877990">"在锁定屏幕上显示个人资料信息"</string>
    <string name="profile_info_settings_title" msgid="3518603215935346604">"个人资料信息"</string>
    <string name="Accounts_settings_title" msgid="1643879107901699406">"帐号"</string>
    <string name="location_settings_title" msgid="1369675479310751735">"位置信息"</string>
    <string name="location_settings_master_switch_title" msgid="3560242980335542411">"使用位置信息"</string>
    <string name="account_settings_title" msgid="626177544686329806">"帐号"</string>
    <string name="security_settings_title" msgid="4918904614964215087">"安全性和位置信息"</string>
    <string name="encryption_and_credential_settings_title" msgid="6514904533438791561">"加密与凭据"</string>
    <string name="encryption_and_credential_settings_summary" product="default" msgid="8721883002237981248">"手机已加密"</string>
    <string name="decryption_settings_summary" product="default" msgid="5671817824042639849">"手机未加密"</string>
    <string name="encryption_and_credential_settings_summary" product="tablet" msgid="7200428573872395685">"设备已加密"</string>
    <string name="decryption_settings_summary" product="tablet" msgid="5794135636155570977">"设备未加密"</string>
    <string name="lockscreen_settings_title" msgid="3922976395527087455">"锁屏时的偏好设置"</string>
    <string name="security_settings_summary" msgid="967393342537986570">"设置我的位置、屏幕解锁、SIM 卡锁定和凭据存储锁定"</string>
    <string name="cdma_security_settings_summary" msgid="6068799952798901542">"设置我的位置、屏幕解锁和凭据存储锁定"</string>
    <string name="security_passwords_title" msgid="2881269890053568809">"隐私设置"</string>
    <string name="disabled_by_administrator_summary" msgid="1601828700318996341">"已被管理员停用"</string>
    <string name="security_status_title" msgid="5848766673665944640">"安全状态"</string>
    <string name="security_dashboard_summary" msgid="6757421634477554939">"屏幕锁定、指纹"</string>
    <string name="security_dashboard_summary_no_fingerprint" msgid="8129641548372335540">"屏幕锁定"</string>
    <string name="security_settings_fingerprint_preference_title" msgid="2488725232406204350">"指纹"</string>
    <string name="fingerprint_manage_category_title" msgid="8293801041700001681">"管理指纹"</string>
    <string name="fingerprint_usage_category_title" msgid="8438526918999536619">"将指纹用于以下用途："</string>
    <string name="fingerprint_add_title" msgid="1926752654454033904">"添加指纹"</string>
    <string name="fingerprint_enable_keyguard_toggle_title" msgid="5078060939636911795">"屏幕锁定"</string>
    <plurals name="security_settings_fingerprint_preference_summary" formatted="false" msgid="624961700033979880">
      <item quantity="other">已设置 <xliff:g id="COUNT_1">%1$d</xliff:g> 个指纹</item>
      <item quantity="one">已设置 <xliff:g id="COUNT_0">%1$d</xliff:g> 个指纹</item>
    </plurals>
    <string name="security_settings_fingerprint_preference_summary_none" msgid="1507739327565151923"></string>
    <string name="security_settings_fingerprint_enroll_introduction_title" msgid="3201556857492526098">"使用指纹解锁"</string>
    <string name="security_settings_fingerprint_enroll_introduction_title_unlock_disabled" msgid="7066417934622827305">"使用指纹"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message" msgid="3508870672887336095">"只需触摸指纹传感器即可解锁您的手机、对购买交易进行授权或登录应用。请务必谨慎添加指纹，因为添加的任何指纹都能够用来执行上述操作。\n\n请注意：指纹识别的安全性可能不及安全系数高的图案或 PIN 码。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message_unlock_disabled" msgid="1550756694054944874">"使用指纹解锁手机或批准购买交易。\n\n请注意：您无法使用指纹来解锁此设备。要了解详情，请与贵单位的管理员联系。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_message_setup" msgid="6817326798834882531">"使用指纹解锁手机或批准购买交易。\n\n请注意：指纹的安全性可能不及安全系数高的图案或 PIN 码。"</string>
    <string name="security_settings_fingerprint_enroll_introduction_cancel" msgid="3199351118385606526">"取消"</string>
    <string name="security_settings_fingerprint_enroll_introduction_continue" msgid="7472492858148162530">"继续"</string>
    <string name="security_settings_fingerprint_enroll_introduction_cancel_setup" msgid="5021369420474432665">"跳过"</string>
    <string name="security_settings_fingerprint_enroll_introduction_continue_setup" msgid="1961957425135180242">"下一步"</string>
    <string name="setup_fingerprint_enroll_skip_title" msgid="362050541117362034">"要跳过指纹设置？"</string>
    <string name="setup_fingerprint_enroll_skip_after_adding_lock_text" msgid="958990414356204763">"指纹设置只需片刻时间。如果您跳过此设置，之后可以在设置中添加您的指纹。"</string>
    <string name="lock_screen_intro_skip_title" msgid="4988210105913705679">"要跳过屏幕锁定吗？"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="tablet" msgid="1581834104051243425">"系统将不会启用设备保护功能。如果您的平板电脑丢失、被盗或被重置，您将无法防止他人使用此平板电脑。"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="device" msgid="4629503416877189572">"系统将不会启用设备保护功能。如果您的设备丢失、被盗或被重置，您将无法防止他人使用此设备。"</string>
    <string name="lock_screen_intro_skip_dialog_text_frp" product="default" msgid="2423428240245737909">"系统将不会启用设备保护功能。如果您的手机丢失、被盗或被重置，您将无法防止他人使用此手机。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="tablet" msgid="5219287483885558525">"系统将不会启用设备保护功能。如果您的平板电脑丢失或被盗，您将无法防止他人使用此平板电脑。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="device" msgid="1466238255429527112">"系统将不会启用设备保护功能。如果您的设备丢失或被盗，您将无法防止他人使用此设备。"</string>
    <string name="lock_screen_intro_skip_dialog_text" product="default" msgid="3008526710555416125">"系统将不会启用设备保护功能。如果您的手机丢失或被盗，您将无法防止他人使用此手机。"</string>
    <string name="skip_anyway_button_label" msgid="2323522873558834513">"仍然跳过"</string>
    <string name="go_back_button_label" msgid="4745265266186209467">"返回"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_title" msgid="3051496861358227199">"触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_message" msgid="8793966374365960368">"指纹传感器位于您手机的背面。请用食指触摸传感器。"</string>
    <string name="security_settings_fingerprint_enroll_find_sensor_content_description" msgid="2058830032070449160">"关于设备和指纹传感器位置的图示说明"</string>
    <string name="security_settings_fingerprint_enroll_dialog_name_label" msgid="7086763077909041106">"名称"</string>
    <string name="security_settings_fingerprint_enroll_dialog_ok" msgid="4150384963879569750">"确定"</string>
    <string name="security_settings_fingerprint_enroll_dialog_delete" msgid="4114615413240707936">"删除"</string>
    <string name="security_settings_fingerprint_enroll_start_title" msgid="2068961812439460133">"触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_start_message" msgid="3909929328942564524">"将您的手指放在指纹传感器上，感觉到振动后移开手指"</string>
    <string name="security_settings_fingerprint_enroll_repeat_title" msgid="2819679722403209778">"移开手指，然后再次触摸传感器"</string>
    <string name="security_settings_fingerprint_enroll_repeat_message" msgid="6158989350522518586">"不时地移开手指，以便传感器更完整地记录下您的指纹"</string>
    <string name="security_settings_fingerprint_enroll_finish_title" msgid="7567276170287972230">"指纹已添加"</string>
    <string name="security_settings_fingerprint_enroll_finish_message" msgid="8970048776120548976">"当您看见此图标时，请使用您的指纹进行身份验证或批准购买交易"</string>
    <string name="security_settings_fingerprint_enroll_enrolling_skip" msgid="3710211704052369752">"以后再说"</string>
    <string name="setup_fingerprint_enroll_enrolling_skip_title" msgid="6808422329107426923">"要跳过指纹设置？"</string>
    <string name="setup_fingerprint_enroll_enrolling_skip_message" msgid="274849306857859783">"您已选择使用指纹作为解锁手机的方式之一。如果您现在跳过这项设置，则以后还需要再进行设置。设置过程只需大约一分钟的时间。"</string>
    <string name="security_settings_fingerprint_enroll_setup_screen_lock" msgid="1195743489835505376">"设置屏幕锁定"</string>
    <string name="security_settings_fingerprint_enroll_done" msgid="4014607378328187567">"完成"</string>
    <string name="security_settings_fingerprint_enroll_touch_dialog_title" msgid="1863561601428695160">"糟糕，这不是传感器"</string>
    <string name="security_settings_fingerprint_enroll_touch_dialog_message" msgid="2989019978041986175">"请用您的食指触摸位于手机背面的传感器。"</string>
    <string name="security_settings_fingerprint_enroll_error_dialog_title" msgid="3618021988442639280">"未完成注册"</string>
    <string name="security_settings_fingerprint_enroll_error_timeout_dialog_message" msgid="2942551158278899627">"指纹注册操作超时，请重试。"</string>
    <string name="security_settings_fingerprint_enroll_error_generic_dialog_message" msgid="3624760637222239293">"无法注册指纹。请重试或使用其他手指。"</string>
    <string name="fingerprint_enroll_button_add" msgid="6317978977419045463">"再添加一个"</string>
    <string name="fingerprint_enroll_button_next" msgid="6247009337616342759">"下一步"</string>
    <string name="security_settings_fingerprint_enroll_disclaimer" msgid="2624905914239271751">"除了将手机解锁，您还可以在购买物品时和登录应用时使用自己的指纹来验证身份。"<annotation id="url">"了解详情"</annotation></string>
    <string name="security_settings_fingerprint_enroll_disclaimer_lockscreen_disabled" msgid="7846871823167357942">" 屏幕锁定选项已停用。要了解详情，请与贵单位的管理员联系。"<annotation id="admin_details">"更多详情"</annotation>\n\n"您仍然可以使用指纹对购买交易进行授权以及登录应用。"<annotation id="url">"了解详情"</annotation></string>
    <string name="security_settings_fingerprint_enroll_lift_touch_again" msgid="1888772560642539718">"移开手指，然后再次触摸传感器"</string>
    <string name="fingerprint_add_max" msgid="1020927549936895822">"您最多可以添加 <xliff:g id="COUNT">%d</xliff:g> 个指纹"</string>
    <string name="fingerprint_intro_error_max" msgid="6864066984678078441">"您添加的指纹数量已达到上限"</string>
    <string name="fingerprint_intro_error_unknown" msgid="1905692132326523040">"无法添加更多的指纹"</string>
    <string name="fingerprint_last_delete_title" msgid="6410310101247028988">"要移除所有指纹吗？"</string>
    <string name="fingerprint_delete_title" msgid="1368196182612202898">"移除“<xliff:g id="FINGERPRINT_ID">%1$s</xliff:g>”"</string>
    <string name="fingerprint_delete_message" msgid="8597787803567398131">"要删除此指纹吗？"</string>
    <string name="fingerprint_last_delete_message" msgid="7852321001254275878">"您将无法使用指纹来解锁手机、对购买交易进行授权或登录应用"</string>
    <string name="fingerprint_last_delete_message_profile_challenge" msgid="6521520787746771912">"您将无法使用指纹解锁您的工作资料、对购买交易进行授权或登录工作应用"</string>
    <string name="fingerprint_last_delete_confirm" msgid="2634726361059274289">"是，移除"</string>
    <string name="confirm_fingerprint_icon_content_description" msgid="5255544532157079096">"请使用您的指纹以继续。"</string>
    <string name="crypt_keeper_settings_title" msgid="4219233835490520414">"加密"</string>
    <string name="crypt_keeper_encrypt_title" product="tablet" msgid="1060273569887301457">"加密平板电脑"</string>
    <string name="crypt_keeper_encrypt_title" product="default" msgid="1878996487755806122">"加密手机"</string>
    <string name="crypt_keeper_encrypted_summary" msgid="1868233637888132906">"已加密"</string>
    <string name="crypt_keeper_desc" product="tablet" msgid="503014594435731275">"您可以对自己的帐号、设置、下载的应用及其数据、媒体和其他文件进行加密。加密平板电脑后，如果您设置了屏幕锁定（即图案、数字PIN码或密码），那么您必须在每次开机时解锁屏幕，才能将平板电脑解密。除此之外，唯一的解密方法就是恢复出厂设置，但这会清除您所有的数据。\n\n加密过程至少需要1个小时的时间。在开始加密之前，您必须先将电池充满电，并让平板电脑在整个加密过程中保持电源接通状态。如果加密过程出现中断，您将丢失部分或所有数据。"</string>
    <string name="crypt_keeper_desc" product="default" msgid="2579929266645543631">"您可以对自己的帐号、设置、下载的应用及其数据、媒体和其他文件进行加密。加密手机后，如果您设置了屏幕锁定（即图案、数字PIN码或密码），那么您必须在每次开机时解锁屏幕，才能将手机解密。除此之外，唯一的解密方法就是恢复出厂设置，但这会清除您所有的数据。\n\n加密过程至少需要1个小时的时间。在开始加密之前，您必须先将电池充满电，并让手机在整个加密过程中保持电源接通状态。如果加密过程出现中断，您将丢失部分或所有数据。"</string>
    <string name="crypt_keeper_button_text" product="tablet" msgid="1189623490604750854">"加密平板电脑"</string>
    <string name="crypt_keeper_button_text" product="default" msgid="2008346408473255519">"加密手机"</string>
    <string name="crypt_keeper_low_charge_text" msgid="2029407131227814893">"请为电池充电，然后重试。"</string>
    <string name="crypt_keeper_unplugged_text" msgid="4785376766063053901">"请插上充电器，然后重试。"</string>
    <string name="crypt_keeper_dialog_need_password_title" msgid="4058971800557767">"没有锁屏PIN码或密码"</string>
    <string name="crypt_keeper_dialog_need_password_message" msgid="4071395977297369642">"您需要先设置锁屏 PIN 码或密码，才能开始加密。"</string>
    <string name="crypt_keeper_confirm_title" msgid="5100339496381875522">"要加密吗？"</string>
    <string name="crypt_keeper_final_desc" product="tablet" msgid="517662068757620756">"加密操作无法撤消，如果您中断该过程，则会丢失数据。加密过程需要1小时或更长时间，在此期间，平板电脑会重新启动数次。"</string>
    <string name="crypt_keeper_final_desc" product="default" msgid="287503113671320916">"加密操作无法撤消，如果您中断该过程，则会丢失数据。加密过程需要1小时或更长时间，在此期间，手机会重新启动数次。"</string>
    <string name="crypt_keeper_setup_title" msgid="1783951453124244969">"正在加密"</string>
    <string name="crypt_keeper_setup_description" product="tablet" msgid="6689952371032099350">"正在加密您的平板电脑（已完成 <xliff:g id="PERCENT">^1</xliff:g>%），请耐心等待。"</string>
    <string name="crypt_keeper_setup_description" product="default" msgid="951918761585534875">"正在加密您的手机（已完成 <xliff:g id="PERCENT">^1</xliff:g>%），请耐心等待。"</string>
    <string name="crypt_keeper_setup_time_remaining" product="tablet" msgid="1655047311546745695">"您的平板电脑正在加密，请耐心等待。剩余时间：<xliff:g id="DURATION">^1</xliff:g>"</string>
    <string name="crypt_keeper_setup_time_remaining" product="default" msgid="1862964662304683072">"您的手机正在加密，请耐心等待。剩余时间：<xliff:g id="DURATION">^1</xliff:g>"</string>
    <string name="crypt_keeper_force_power_cycle" product="tablet" msgid="556504311511212648">"要解锁您的平板电脑，请将其关机然后再重启。"</string>
    <string name="crypt_keeper_force_power_cycle" product="default" msgid="1794353635603020327">"要解锁您的手机，请将其关机然后再重启。"</string>
    <string name="crypt_keeper_warn_wipe" msgid="2738374897337991667">"警告：如果解锁尝试再失败<xliff:g id="COUNT">^1</xliff:g>次，您的设备将被清空！"</string>
    <string name="crypt_keeper_enter_password" msgid="2223340178473871064">"键入密码"</string>
    <string name="crypt_keeper_failed_title" msgid="7133499413023075961">"加密失败"</string>
    <string name="crypt_keeper_failed_summary" product="tablet" msgid="8219375738445017266">"加密过程中断，无法完成。因此，您已无法再使用平板电脑上的数据。\n\n要继续使用您的平板电脑，您需要恢复出厂设置。恢复出厂设置后对平板电脑进行设置时，您可以将之前备份到Google帐号的所有数据恢复到平板电脑中。"</string>
    <string name="crypt_keeper_failed_summary" product="default" msgid="3270131542549577953">"加密过程中断，无法完成。因此，您已无法再使用手机上的数据。\n\n要继续使用您的手机，您需要恢复出厂设置。恢复出厂设置后对手机进行设置时，您可以将之前备份到Google帐号的所有数据恢复到手机中。"</string>
    <string name="crypt_keeper_data_corrupt_title" msgid="8759119849089795751">"解密失败"</string>
    <string name="crypt_keeper_data_corrupt_summary" product="tablet" msgid="840107296925798402">"您输入的密码正确无误，但遗憾的是，您的数据已损坏。\n\n要继续使用您的平板电脑，您需要将其恢复出厂设置。对恢复出厂设置后的平板电脑进行设置时，您可以恢复之前备份到 Google 帐号的任何数据。"</string>
    <string name="crypt_keeper_data_corrupt_summary" product="default" msgid="8843311420059663824">"您输入的密码正确无误，但遗憾的是，您的数据已损坏。\n\n要继续使用您的手机，您需要将其恢复出厂设置。对恢复出厂设置后的手机进行设置时，您可以恢复之前备份到 Google 帐号的任何数据。"</string>
    <string name="crypt_keeper_switch_input_method" msgid="4168332125223483198">"切换输入法"</string>
    <string name="suggested_lock_settings_title" msgid="8498743819223200961">"保护手机的安全"</string>
    <string name="suggested_lock_settings_summary" product="tablet" msgid="2296800316150748710">"设置屏幕锁定方式以保护平板电脑"</string>
    <string name="suggested_lock_settings_summary" product="device" msgid="7562847814806365373">"设置屏幕锁定方式以保护设备"</string>
    <string name="suggested_lock_settings_summary" product="default" msgid="1526355348444658181">"设置屏幕锁定方式以保护手机"</string>
    <string name="suggested_fingerprint_lock_settings_title" msgid="2174553391551398081">"添加用于解锁的指纹"</string>
    <string name="suggested_fingerprint_lock_settings_summary" product="tablet" msgid="5738274583658668124"></string>
    <string name="suggested_fingerprint_lock_settings_summary" product="device" msgid="5738274583658668124"></string>
    <string name="suggested_fingerprint_lock_settings_summary" product="default" msgid="5738274583658668124"></string>
    <string name="lock_settings_picker_title" msgid="1095755849152582712">"选择屏幕锁定方式"</string>
    <string name="lock_settings_picker_title_profile" msgid="8822511284992306796">"选择工作资料锁屏方式"</string>
    <string name="setup_lock_settings_picker_title" product="tablet" msgid="90329443364067215">"保护您的平板电脑"</string>
    <string name="setup_lock_settings_picker_title" product="device" msgid="2399952075134938929">"保护您的设备"</string>
    <string name="setup_lock_settings_picker_title" product="default" msgid="1572244299605153324">"为手机启用保护功能"</string>
    <string name="lock_settings_picker_fingerprint_added_security_message" msgid="5008939545428518367">"为提升安全性，请设置备用屏幕锁定方式"</string>
    <string name="setup_lock_settings_picker_message" product="tablet" msgid="8919671129189936210">"启用设备保护功能可防止他人在未经您允许的情况下使用此平板电脑。请选择您要使用的屏幕锁定方式。"</string>
    <string name="setup_lock_settings_picker_message" product="device" msgid="3787276514406353777">"启用设备保护功能可防止他人在未经您允许的情况下使用此设备。请选择您要使用的屏幕锁定方式。"</string>
    <string name="setup_lock_settings_picker_message" product="default" msgid="3692856437543730446">"启用设备保护功能可防止他人在未经您允许的情况下使用此手机。请选择您要使用的屏幕锁定方式。"</string>
    <string name="lock_settings_picker_fingerprint_message" msgid="4755230324778371292">"选择您的备用屏幕锁定方式"</string>
    <string name="setup_lock_settings_options_button_label" msgid="8511153243629402929">"屏幕锁定选项"</string>
    <string name="setup_lock_settings_options_dialog_title" msgid="5058207955455973917">"屏幕锁定选项"</string>
    <string name="unlock_set_unlock_launch_picker_title" msgid="2084576942666016993">"屏幕锁定"</string>
    <string name="unlock_set_unlock_launch_picker_summary_lock_immediately" msgid="5967714169972542586">"<xliff:g id="UNLOCK_METHOD">%1$s</xliff:g> / 休眠后立即启动"</string>
    <string name="unlock_set_unlock_launch_picker_summary_lock_after_timeout" msgid="4696710373399258413">"<xliff:g id="UNLOCK_METHOD">%1$s</xliff:g> / 休眠 <xliff:g id="TIMEOUT_STRING">%2$s</xliff:g>后启动"</string>
    <string name="unlock_set_unlock_launch_picker_title_profile" msgid="124176557311393483">"工作资料屏幕锁定方式"</string>
    <string name="unlock_set_unlock_launch_picker_change_title" msgid="5045866882028324941">"更改屏幕锁定方式"</string>
    <string name="unlock_set_unlock_launch_picker_change_summary" msgid="2790960639554590668">"更改或关闭图案、PIN码或密码保护"</string>
    <string name="unlock_set_unlock_launch_picker_enable_summary" msgid="4791110798817242301">"选择屏幕锁定方式"</string>
    <string name="unlock_set_unlock_off_title" msgid="7117155352183088342">"无"</string>
    <string name="unlock_set_unlock_off_summary" msgid="94361581669110415"></string>
    <string name="unlock_set_unlock_none_title" msgid="5679243878975864640">"滑动"</string>
    <string name="unlock_set_unlock_none_summary" msgid="8914673583104628191">"无安全措施"</string>
    <string name="unlock_set_unlock_pattern_title" msgid="2912067603917311700">"图案"</string>
    <string name="unlock_set_unlock_pattern_summary" msgid="7062696666227725593">"中等安全性"</string>
    <string name="unlock_set_unlock_pin_title" msgid="5846029709462329515">"PIN 码"</string>
    <string name="unlock_set_unlock_pin_summary" msgid="907878650556383388">"中高等安全性"</string>
    <string name="unlock_set_unlock_password_title" msgid="8775603825675090937">"密码"</string>
    <string name="unlock_set_unlock_password_summary" msgid="8856220848940929546">"高安全性"</string>
    <string name="unlock_set_do_later_title" msgid="4894767558414979243">"以后再说"</string>
    <string name="current_screen_lock" msgid="4104091715420072219">"当前屏幕锁定设置"</string>
    <string name="fingerprint_unlock_set_unlock_pattern" msgid="4939057588092120368">"指纹 + 图案"</string>
    <string name="fingerprint_unlock_set_unlock_pin" msgid="8010746824051056986">"指纹 + PIN 码"</string>
    <string name="fingerprint_unlock_set_unlock_password" msgid="7351131075806338634">"指纹 + 密码"</string>
    <string name="fingerprint_unlock_skip_fingerprint" msgid="1441077909803666681">"不设置指纹并继续"</string>
    <string name="fingerprint_unlock_title" msgid="2826226740306003991">"您可以使用自己的指纹将手机解锁。为了安全起见，要使用此选项，您必须设置备用屏幕锁定方式。"</string>
    <string name="unlock_set_unlock_disabled_summary" msgid="2051593894736282302">"由于管理员、加密策略或凭据存储的要求，您无法使用此选项"</string>
    <string name="unlock_set_unlock_mode_off" msgid="5881952274566013651">"无"</string>
    <string name="unlock_set_unlock_mode_none" msgid="8467360084676871617">"滑动"</string>
    <string name="unlock_set_unlock_mode_pattern" msgid="7837270780919299289">"图案"</string>
    <string name="unlock_set_unlock_mode_pin" msgid="3541326261341386690">"PIN 码"</string>
    <string name="unlock_set_unlock_mode_password" msgid="1203938057264146610">"密码"</string>
    <string name="unlock_setup_wizard_fingerprint_details" msgid="7893457665921363009">"设置了屏幕锁定后，您还可以在“设置”&gt;“安全”中设置指纹。"</string>
    <string name="unlock_disable_lock_title" msgid="1427036227416979120">"关闭屏幕锁定"</string>
    <string name="unlock_disable_frp_warning_title" msgid="264008934468492550">"要移除设备保护功能吗？"</string>
    <string name="unlock_disable_frp_warning_title_profile" msgid="5507136301904313583">"要移除个人资料保护功能吗？"</string>
    <string name="unlock_disable_frp_warning_content_pattern" msgid="8869767290771023461">"移除您的解锁图案后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_fingerprint" msgid="2986105377420905314">"移除您的解锁图案后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pin" msgid="586996206210265131">"移除您的 PIN 码后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_fingerprint" msgid="3370462835533123695">"移除您的 PIN 码后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_password" msgid="5420612686852555537">"移除您的密码后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_password_fingerprint" msgid="3595476296430536798">"移除您的密码后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown" msgid="1550718040483548220">"移除您的屏幕锁定方式后，设备保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_fingerprint" msgid="3679351662094349506">"移除您的屏幕锁定方式后，设备保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此设备中移除，因此您将无法通过指纹解锁手机、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_profile" msgid="8682200103576359918">"移除您的解锁图案后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pattern_fingerprint_profile" msgid="6718155854303231675">"移除您的解锁图案后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_profile" msgid="7790688070593867767">"移除您的 PIN 码后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_pin_fingerprint_profile" msgid="4209564603132870532">"移除您的 PIN 码后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_password_profile" msgid="7569285520567674461">"移除您的密码后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_password_fingerprint_profile" msgid="2994300676764706047">"移除您的密码后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹也将从此资料中移除，因此您将无法通过指纹解锁您的资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_profile" msgid="6984215718701688202">"移除您的屏幕锁定方式后，个人资料保护功能将无法使用。"</string>
    <string name="unlock_disable_frp_warning_content_unknown_fingerprint_profile" msgid="4994062501123299418">"移除您的屏幕锁定方式后，资料保护功能将无法使用。<xliff:g id="EMPTY_LINE">

</xliff:g>您保存的指纹将从此资料中移除，因此您将无法通过指纹解锁资料、授权购买交易或登录应用。"</string>
    <string name="unlock_disable_frp_warning_ok" msgid="7075138677177748705">"是，移除"</string>
    <string name="unlock_change_lock_pattern_title" msgid="2044092014872741130">"更改解锁图案"</string>
    <string name="unlock_change_lock_pin_title" msgid="806629901095938484">"更改解锁PIN码"</string>
    <string name="unlock_change_lock_password_title" msgid="5606298470358768865">"更改解锁密码"</string>
    <string name="lock_failed_attempts_before_wipe" msgid="2219711062197089783">"请重试。您目前已尝试 <xliff:g id="CURRENT_ATTEMPTS">%1$d</xliff:g> 次，最多可尝试 <xliff:g id="TOTAL_ATTEMPTS">%2$d</xliff:g> 次。"</string>
    <string name="lock_last_attempt_before_wipe_warning_title" msgid="4277765862798876826">"您的数据将遭到删除"</string>
    <string name="lock_last_pattern_attempt_before_wipe_device" msgid="1688030823464420974">"如果您下次绘制的解锁图案仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_pin_attempt_before_wipe_device" msgid="5350785938296254352">"如果您下次输入的 PIN 码仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_password_attempt_before_wipe_device" msgid="6208035114731421034">"如果您下次输入的密码仍然有误，系统将删除此设备上的数据"</string>
    <string name="lock_last_pattern_attempt_before_wipe_user" msgid="7851504071368235547">"如果您下次绘制的解锁图案仍然有误，系统将删除此用户"</string>
    <string name="lock_last_pin_attempt_before_wipe_user" msgid="4049024921333961715">"如果您下次输入的 PIN 码仍然有误，系统将删除此用户"</string>
    <string name="lock_last_password_attempt_before_wipe_user" msgid="4660886542496781672">"如果您下次输入的密码仍然有误，系统将删除此用户"</string>
    <string name="lock_last_pattern_attempt_before_wipe_profile" msgid="2437716252059050291">"如果您下次绘制的解锁图案仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_last_pin_attempt_before_wipe_profile" msgid="5799931839127476913">"如果您下次输入的 PIN 码仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_last_password_attempt_before_wipe_profile" msgid="6786586046975042158">"如果您下次输入的密码仍然有误，系统将删除您的工作资料和相关数据"</string>
    <string name="lock_failed_attempts_now_wiping_device" msgid="5047439819181833824">"错误次数过多。系统将删除此设备上的数据。"</string>
    <string name="lock_failed_attempts_now_wiping_user" msgid="6188180643494518001">"错误次数过多。系统将删除此用户。"</string>
    <string name="lock_failed_attempts_now_wiping_profile" msgid="1745475043685915442">"错误次数过多。系统将删除此工作资料和相关数据。"</string>
    <string name="lock_failed_attempts_now_wiping_dialog_dismiss" msgid="8246716090548717312">"关闭"</string>
    <string name="lockpassword_password_too_short" msgid="2726090378672764986">"必须至少包含 <xliff:g id="COUNT">%d</xliff:g> 个字符"</string>
    <string name="lockpassword_pin_too_short" msgid="3638188874397727648">"PIN 码必须至少为 <xliff:g id="COUNT">%d</xliff:g> 位数"</string>
    <string name="lockpassword_continue_label" msgid="4602203784934526940">"继续"</string>
    <string name="lockpassword_password_too_long" msgid="4591720174765403476">"必须少于 <xliff:g id="NUMBER">%d</xliff:g> 个字符"</string>
    <string name="lockpassword_pin_too_long" msgid="2079396149560490458">"必须少于 <xliff:g id="NUMBER">%d</xliff:g> 位数"</string>
    <string name="lockpassword_pin_contains_non_digits" msgid="7284664023164191198">"只能包含 0-9 的数字"</string>
    <string name="lockpassword_pin_recently_used" msgid="1401569207976460727">"设备管理员不允许使用最近用过的 PIN 码"</string>
    <string name="lockpassword_pin_blacklisted_by_admin" msgid="8563366383328811472">"常用 PIN 码已被您的 IT 管理员屏蔽。请尝试一个不同的 PIN 码。"</string>
    <string name="lockpassword_illegal_character" msgid="8049611046639943217">"密码不得包含无效字符"</string>
    <string name="lockpassword_password_requires_alpha" msgid="3036589522150097731">"必须包含至少 1 个字母"</string>
    <string name="lockpassword_password_requires_digit" msgid="5140062925787058765">"必须包含至少 1 个数字"</string>
    <string name="lockpassword_password_requires_symbol" msgid="5944350865681510893">"必须包含至少 1 个符号"</string>
    <plurals name="lockpassword_password_requires_letters" formatted="false" msgid="9013132344745898400">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个字母</item>
      <item quantity="one">必须包含至少 1 个字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_lowercase" formatted="false" msgid="2626327674921055486">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个小写字母</item>
      <item quantity="one">必须包含至少 1 个小写字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_uppercase" formatted="false" msgid="7860796359913920356">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个大写字母</item>
      <item quantity="one">必须包含至少 1 个大写字母</item>
    </plurals>
    <plurals name="lockpassword_password_requires_numeric" formatted="false" msgid="1967587658356336828">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个数字</item>
      <item quantity="one">必须包含至少 1 个数字</item>
    </plurals>
    <plurals name="lockpassword_password_requires_symbols" formatted="false" msgid="6751305770863640574">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个特殊符号</item>
      <item quantity="one">必须包含至少 1 个特殊符号</item>
    </plurals>
    <plurals name="lockpassword_password_requires_nonletter" formatted="false" msgid="4440596998172043055">
      <item quantity="other">必须包含至少 <xliff:g id="COUNT">%d</xliff:g> 个非字母字符</item>
      <item quantity="one">必须包含至少 1 个非字母字符</item>
    </plurals>
    <string name="lockpassword_password_recently_used" msgid="942665351220525547">"设备管理员不允许使用最近用过的密码"</string>
    <string name="lockpassword_password_blacklisted_by_admin" msgid="9105101266246197027">"常用密码已被您的 IT 管理员屏蔽。请尝试一个不同的密码。"</string>
    <string name="lockpassword_pin_no_sequential_digits" msgid="680765285206990584">"不允许使用以升序、降序或重复序列进行排列的一串数字"</string>
    <string name="lockpassword_confirm_label" msgid="8176726201389902380">"确认"</string>
    <string name="lockpassword_cancel_label" msgid="8818529276331121899">"取消"</string>
    <string name="lockpassword_clear_label" msgid="5724429464960458155">"清除"</string>
    <string name="lockpattern_tutorial_cancel_label" msgid="6431583477570493261">"取消"</string>
    <string name="lockpattern_tutorial_continue_label" msgid="3559793618653400434">"下一步"</string>
    <string name="lock_setup" msgid="3355847066343753943">"设置完毕。"</string>
    <string name="manage_device_admin" msgid="537804979483211453">"设备管理应用"</string>
    <string name="number_of_device_admins_none" msgid="7185056721919496069">"没有运行中的应用"</string>
    <plurals name="number_of_device_admins" formatted="false" msgid="3361891840111523393">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个有效应用</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个有效应用</item>
    </plurals>
    <string name="manage_trust_agents" msgid="4629279457536987768">"可信代理"</string>
    <string name="disabled_because_no_backup_security" msgid="6877660253409580377">"要开始使用，请先设置屏幕锁定方式"</string>
    <string name="manage_trust_agents_summary" msgid="1475819820389620546">"无"</string>
    <plurals name="manage_trust_agents_summary_on" formatted="false" msgid="3935182396726101824">
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个可信代理处于有效状态</item>
      <item quantity="one">1 个可信代理处于有效状态</item>
    </plurals>
    <string name="bluetooth_quick_toggle_title" msgid="1037056952714061893">"蓝牙"</string>
    <string name="bluetooth_quick_toggle_summary" msgid="5293641680139873341">"打开蓝牙"</string>
    <string name="bluetooth_settings" msgid="1810521656168174329">"蓝牙"</string>
    <string name="bluetooth_settings_title" msgid="1908745291161353016">"蓝牙"</string>
    <string name="bluetooth_settings_summary" msgid="2091062709530570462">"管理连接、设置设备名称和可检测性"</string>
    <string name="bluetooth_pairing_request" msgid="2605098826364694673">"要与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对吗？"</string>
    <string name="bluetooth_pairing_key_msg" msgid="418124944140102021">"蓝牙配对码"</string>
    <string name="bluetooth_enter_passkey_msg" msgid="6813273136442138444">"输入配对码，然后按回车键"</string>
    <string name="bluetooth_enable_alphanumeric_pin" msgid="7222713483058171357">"PIN码由字母或符号组成"</string>
    <string name="bluetooth_pin_values_hint" msgid="3815897557875873646">"通常为 0000 或 1234"</string>
    <string name="bluetooth_pin_values_hint_16_digits" msgid="7849359451584101077">"必须为 16 位数字"</string>
    <string name="bluetooth_enter_pin_other_device" msgid="4637977124526813470">"您可能还需要在另一设备上输入此PIN码。"</string>
    <string name="bluetooth_enter_passkey_other_device" msgid="2798719004030279602">"您可能还需要在另一台设备上输入此密钥。"</string>
    <string name="bluetooth_confirm_passkey_msg" msgid="3708312912841950052">"要与以下设备配对：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;请确保其显示的配对密钥为：&lt;br&gt;&lt;b&gt;<xliff:g id="PASSKEY">%2$s</xliff:g>&lt;/b&gt;"</string>
    <string name="bluetooth_incoming_pairing_msg" msgid="1615930853859551491">"来自：&lt;br&gt;&lt;b&gt;<xliff:g id="DEVICE_NAME">%1$s</xliff:g>&lt;/b&gt;&lt;br&gt;&lt;br&gt;要与此设备配对吗？"</string>
    <string name="bluetooth_display_passkey_pin_msg" msgid="2796550001376088433">"要与 <xliff:g id="BOLD1_0">&lt;br&gt;&lt;b&gt;</xliff:g><xliff:g id="DEVICE_NAME">%1$s</xliff:g><xliff:g id="END_BOLD1">&lt;/b&gt;&lt;br&gt;&lt;br&gt;</xliff:g> 设备配对，请在该设备上键入：<xliff:g id="BOLD2_1">&lt;br&gt;&lt;b&gt;</xliff:g><xliff:g id="PASSKEY">%2$s</xliff:g><xliff:g id="END_BOLD2">&lt;/b&gt;</xliff:g>，然后按回车键。"</string>
    <string name="bluetooth_pairing_shares_phonebook" msgid="9082518313285787097">"允许访问您的通讯录和通话记录"</string>
    <string name="bluetooth_error_title" msgid="6850384073923533096"></string>
    <string name="bluetooth_connecting_error_message" msgid="1397388344342081090">"无法连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>。"</string>
    <string name="bluetooth_preference_scan_title" msgid="2277464653118896016">"扫描设备"</string>
    <string name="bluetooth_search_for_devices" msgid="2754007356491461674">"刷新"</string>
    <string name="bluetooth_searching_for_devices" msgid="9203739709307871727">"正在搜索..."</string>
    <string name="bluetooth_preference_device_settings" msgid="907776049862799122">"设备设置"</string>
    <string name="bluetooth_preference_paired_dialog_title" msgid="8875124878198774180">"已配对的设备"</string>
    <string name="bluetooth_preference_paired_dialog_name_label" msgid="8111146086595617285">"名称"</string>
    <string name="bluetooth_preference_paired_dialog_internet_option" msgid="7112953286863428412">"互联网连接"</string>
    <string name="bluetooth_preference_paired_dialog_keyboard_option" msgid="2271954176947879628">"键盘"</string>
    <string name="bluetooth_preference_paired_dialog_contacts_option" msgid="7747163316331917594">"通讯录和通话记录"</string>
    <string name="bluetooth_pairing_dialog_title" msgid="1417255032435317301">"要与此设备配对吗？"</string>
    <string name="bluetooth_pairing_dialog_sharing_phonebook_title" msgid="7664141669886358618">"要共享电话簿吗？"</string>
    <string name="bluetooth_pairing_dialog_contants_request" msgid="5531109163573611348">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>想要使用您的通讯录和通话记录。"</string>
    <string name="bluetooth_pairing_dialog_paring_request" msgid="8451248193517851958">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>想通过蓝牙与您的设备配对。连接后，此设备将可以使用您的通讯录和通话记录。"</string>
    <string name="bluetooth_preference_paired_devices" msgid="1970524193086791964">"已配对的设备"</string>
    <string name="bluetooth_preference_found_media_devices" msgid="1617401232446299411">"可用的设备"</string>
    <string name="bluetooth_preference_no_found_devices" msgid="7594339669961811591">"未找到任何设备"</string>
    <string name="bluetooth_device_context_connect" msgid="3997659895003244941">"连接"</string>
    <string name="bluetooth_device_context_disconnect" msgid="8220072022970148683">"断开连接"</string>
    <string name="bluetooth_device_context_pair_connect" msgid="7611522504813927727">"配对和连接"</string>
    <string name="bluetooth_device_context_unpair" msgid="662992425948536144">"取消配对"</string>
    <string name="bluetooth_device_context_disconnect_unpair" msgid="7644014238070043798">"断开连接和取消配对"</string>
    <string name="bluetooth_device_context_connect_advanced" msgid="2643129703569788771">"选项…"</string>
    <string name="bluetooth_menu_advanced" msgid="8572178316357220524">"高级"</string>
    <string name="bluetooth_advanced_titlebar" msgid="2142159726881547669">"高级蓝牙设置"</string>
    <string name="bluetooth_empty_list_bluetooth_off" msgid="6351930724051893423">"开启蓝牙后，您的设备可以与附近的其他蓝牙设备通信。"</string>
    <string name="bluetooth_scanning_on_info_message" msgid="824285504325592644">"开启蓝牙后，您的设备可以与附近的其他蓝牙设备通信。\n\n为了提升设备的使用体验，应用和服务仍然可以随时扫描附近的设备（即使蓝牙已关闭）。这可用于改进基于位置的功能和服务（举例来说）。您可以在"<annotation id="link">"扫描设置"</annotation>"中更改此设置。"</string>
    <string name="ble_scan_notify_text" msgid="1295915006005700650">"为了提高位置信息的精确度，系统应用和服务仍然会检测蓝牙设备。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>
    <string name="bluetooth_connect_failed" msgid="4500234659813241053">"无法连接，请重试。"</string>
    <string name="device_details_title" msgid="6576953269221085300">"设备详细信息"</string>
    <string name="bluetooth_device_mac_address" msgid="2513724313558236181">"设备的蓝牙地址：<xliff:g id="ADDRESS">%1$s</xliff:g>"</string>
    <string name="bluetooth_unpair_dialog_title" msgid="38467834196432400">"要与该设备取消配对吗？"</string>
    <string name="bluetooth_unpair_dialog_body" product="default" msgid="9087609557757135712">"您的手机将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_body" product="tablet" msgid="7785695793007576501">"您的平板电脑将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_body" product="device" msgid="251257782642157557">"您的设备将与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>取消配对"</string>
    <string name="bluetooth_unpair_dialog_forget_confirm_button" msgid="3829370108973879006">"与设备取消配对"</string>
    <string name="bluetooth_connect_specific_profiles_title" msgid="6952214406025825164">"连接到..."</string>
    <string name="bluetooth_disconnect_a2dp_profile" msgid="3524648279150937177">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与媒体音频断开连接。"</string>
    <string name="bluetooth_disconnect_headset_profile" msgid="8635908811168780720">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与免提音频断开连接。"</string>
    <string name="bluetooth_disconnect_hid_profile" msgid="3282295189719352075">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将与输入设备断开连接。"</string>
    <string name="bluetooth_disconnect_pan_user_profile" msgid="8037627994382458698">"经由<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的互联网连接将会断开。"</string>
    <string name="bluetooth_disconnect_pan_nap_profile" product="tablet" msgid="8355910926439312604">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将断开而不再共享该平板电脑的互联网连接。"</string>
    <string name="bluetooth_disconnect_pan_nap_profile" product="default" msgid="6251611115860359886">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g>将断开而不再共享该手机的互联网连接。"</string>
    <string name="bluetooth_device_advanced_title" msgid="6066342531927499308">"已配对的蓝牙设备"</string>
    <string name="bluetooth_device_advanced_online_mode_title" msgid="3689050071425683114">"连接"</string>
    <string name="bluetooth_device_advanced_online_mode_summary" msgid="1204424107263248336">"连接到蓝牙设备"</string>
    <string name="bluetooth_device_advanced_profile_header_title" msgid="102745381968579605">"用于"</string>
    <string name="bluetooth_device_advanced_rename_device" msgid="5148578059584955791">"重命名"</string>
    <string name="bluetooth_device_advanced_enable_opp_title" msgid="8222550640371627365">"允许传入文件"</string>
    <string name="bluetooth_pan_user_profile_summary_connected" msgid="6436258151814414028">"经由其他设备连接到互联网"</string>
    <string name="bluetooth_pan_nap_profile_summary_connected" msgid="1322694224800769308">"与其他设备共享该设备的互联网连接"</string>
    <string name="bluetooth_dock_settings" msgid="3218335822716052885">"基座设置"</string>
    <string name="bluetooth_dock_settings_title" msgid="5543069893044375188">"将基座用于音频"</string>
    <string name="bluetooth_dock_settings_headset" msgid="1001821426078644650">"将基座用作免提电话"</string>
    <string name="bluetooth_dock_settings_a2dp" msgid="8791004998846630574">"用于音乐和媒体"</string>
    <string name="bluetooth_dock_settings_remember" msgid="5551459057010609115">"记住设置"</string>
    <string name="bluetooth_max_connected_audio_devices_string" msgid="6752690395207847881">"已连接蓝牙音频设备的数量上限"</string>
    <string name="bluetooth_max_connected_audio_devices_dialog_title" msgid="5936561749790095473">"选择已连接蓝牙音频设备的数量上限"</string>
    <string name="wifi_display_settings_title" msgid="8740852850033480136">"投射"</string>
    <string name="wifi_display_enable_menu_item" msgid="4883036464138167674">"开启无线显示"</string>
    <string name="wifi_display_no_devices_found" msgid="1382012407154143453">"未在附近找到设备。"</string>
    <string name="wifi_display_status_connecting" msgid="5688608834000748607">"正在连接"</string>
    <string name="wifi_display_status_connected" msgid="8364125226376985558">"已连接"</string>
    <string name="wifi_display_status_in_use" msgid="8556830875615434792">"正在使用中"</string>
    <string name="wifi_display_status_not_available" msgid="5714978725794210102">"无法使用"</string>
    <string name="wifi_display_details" msgid="7791118209992162698">"显示设备设置"</string>
    <string name="wifi_display_options_title" msgid="5740656401635054838">"无线显示选项"</string>
    <string name="wifi_display_options_forget" msgid="9119048225398894580">"取消保存"</string>
    <string name="wifi_display_options_done" msgid="5703116500357822557">"完成"</string>
    <string name="wifi_display_options_name" msgid="4756080222307467898">"名称"</string>
    <string name="wifi_band_24ghz" msgid="852929254171856911">"2.4 GHz"</string>
    <string name="wifi_band_5ghz" msgid="6433822023268515117">"5 GHz"</string>
    <string name="wifi_sign_in_button_text" msgid="8404345621836792112">"登录"</string>
    <string name="wifi_tap_to_sign_in" msgid="6990161842394669054">"点按此处即可登录网络"</string>
    <string name="link_speed" msgid="8896664974117585555">"%1$d Mbps"</string>
    <string name="wifi_ask_enable" msgid="2795469717302060104">"<xliff:g id="REQUESTER">%s</xliff:g>请求开启 WLAN"</string>
    <string name="wifi_ask_disable" msgid="728366570145493573">"<xliff:g id="REQUESTER">%s</xliff:g>请求关闭 WLAN"</string>
    <string name="nfc_quick_toggle_title" msgid="6769159366307299004">"NFC"</string>
    <string name="nfc_quick_toggle_summary" product="tablet" msgid="8302974395787498915">"允许平板电脑在接触其他设备时交换数据"</string>
    <string name="nfc_quick_toggle_summary" product="default" msgid="5237208142892767592">"允许手机在接触其他设备时交换数据"</string>
    <string name="nfc_disclaimer_title" msgid="4364003873202264039">"开启 NFC"</string>
    <string name="nfc_disclaimer_content" msgid="5566907911915158075">"NFC 可让此设备和附近的其他设备或目标（例如付款终端、门禁读卡器和互动式广告或标签）交换数据。"</string>
    <string name="android_beam_settings_title" msgid="7832812974600338649">"Android Beam"</string>
    <string name="android_beam_on_summary" msgid="3618057099355636830">"已准备好通过 NFC 传输应用内容"</string>
    <string name="android_beam_off_summary" msgid="4663095428454779138">"关闭"</string>
    <string name="android_beam_disabled_summary" msgid="1737782116894793393">"无法使用，因为NFC已关闭"</string>
    <string name="android_beam_label" msgid="6257036050366775040">"Android Beam"</string>
    <string name="android_beam_explained" msgid="1810540319385192758">"开启此功能后，只需将本设备与另一台支持 NFC 的设备靠在一起，即可将应用内容传输过去。例如，您可以传输浏览器网页、YouTube 视频、联系人信息等等。\n\n您只需将两台设备靠在一起（通常是背靠背），然后点按屏幕确认，应用就会确定传输哪些内容。"</string>
    <string name="wifi_quick_toggle_title" msgid="8850161330437693895">"WLAN"</string>
    <string name="wifi_quick_toggle_summary" msgid="2696547080481267642">"打开WLAN"</string>
    <string name="wifi_settings" msgid="29722149822540994">"WLAN"</string>
    <string name="wifi_settings_master_switch_title" msgid="4746267967669683259">"使用 WLAN"</string>
    <string name="wifi_settings_category" msgid="8719175790520448014">"WLAN设置"</string>
    <string name="wifi_settings_title" msgid="3103415012485692233">"WLAN"</string>
    <string name="wifi_settings_summary" msgid="668767638556052820">"设置和管理无线接入点"</string>
    <string name="wifi_select_network" msgid="4210954938345463209">"选择WLAN网络"</string>
    <string name="wifi_starting" msgid="6732377932749942954">"正在打开WLAN…"</string>
    <string name="wifi_stopping" msgid="8952524572499500804">"正在关闭WLAN…"</string>
    <string name="wifi_error" msgid="3207971103917128179">"出错"</string>
    <string name="wifi_sap_no_channel_error" msgid="3108445199311817111">"在此国家/地区无法使用 5 GHz 频段"</string>
    <string name="wifi_in_airplane_mode" msgid="8652520421778203796">"正处于飞行模式下"</string>
    <string name="wifi_notify_open_networks" msgid="76298880708051981">"打开网络通知"</string>
    <string name="wifi_notify_open_networks_summary" msgid="2761326999921366960">"有可用的高品质公共网络时通知我"</string>
    <string name="wifi_wakeup" msgid="8815640989361538036">"自动开启 WLAN"</string>
    <string name="wifi_wakeup_summary" msgid="2530814331062997163">"位于已保存的高品质网络（例如您的家庭网络）附近时自动重新开启 WLAN"</string>
    <string name="wifi_wakeup_summary_no_location" msgid="7494539594649967699">"无法使用，因为位置信息服务已关闭。请开启"<annotation id="link">"位置信息服务"</annotation>"。"</string>
    <string name="wifi_wakeup_summary_scanning_disabled" msgid="7247227922074840445">"无法使用，因为 WLAN 搜索功能已关闭"</string>
    <string name="wifi_wakeup_summary_scoring_disabled" msgid="108339002136866897">"要使用该功能，请选择一个网络评分服务提供方"</string>
    <string name="wifi_poor_network_detection" msgid="4925789238170207169">"避开状况不佳的网络"</string>
    <string name="wifi_poor_network_detection_summary" msgid="7016103106105907127">"仅在 WLAN 互联网连接状况良好时使用 WLAN 网络"</string>
    <string name="wifi_avoid_poor_network_detection_summary" msgid="1644292503152790501">"仅使用互联网连接状况良好的网络"</string>
    <string name="use_open_wifi_automatically_title" msgid="6851951242903078588">"连接到开放网络"</string>
    <string name="use_open_wifi_automatically_summary" msgid="2982091714252931713">"自动连接到高品质的公共网络"</string>
    <string name="use_open_wifi_automatically_summary_scoring_disabled" msgid="593964217679325831">"要使用该功能，请选择一个网络评分服务提供方"</string>
    <string name="use_open_wifi_automatically_summary_scorer_unsupported_disabled" msgid="8472122600853650258">"要使用该功能，请选择一个兼容的网络评分服务提供方"</string>
    <string name="wifi_install_credentials" msgid="3551143317298272860">"安装证书"</string>
    <string name="wifi_scan_notify_text" msgid="5544778734762998889">"为了提高位置信息的精确度，应用和服务仍然可以随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>
    <string name="wifi_scan_notify_text_scanning_off" msgid="3426075479272242098">"要提高位置信息的精确度，请在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中开启 WLAN 扫描功能。"</string>
    <string name="wifi_scan_notify_remember_choice" msgid="7104867814641144485">"不再显示"</string>
    <string name="wifi_setting_sleep_policy_title" msgid="5149574280392680092">"在休眠状态下保持WLAN网络连接"</string>
    <string name="wifi_setting_on_during_sleep_title" msgid="8308975500029751565">"在休眠状态下保持WLAN连接"</string>
    <string name="wifi_setting_sleep_policy_error" msgid="8174902072673071961">"更改设置时出现问题"</string>
    <string name="wifi_suspend_efficiency_title" msgid="2338325886934703895">"提高能效"</string>
    <string name="wifi_suspend_optimizations" msgid="1220174276403689487">"WLAN优化"</string>
    <string name="wifi_suspend_optimizations_summary" msgid="4151428966089116856">"开启WLAN时尽可能节约电池用量"</string>
    <string name="wifi_limit_optimizations_summary" msgid="9000801068363468950">"限制WLAN耗电量"</string>
    <string name="wifi_switch_away_when_unvalidated" msgid="8593144541347373394">"在 WLAN 网络无法连接到互联网时切换到移动数据网络。"</string>
    <string name="wifi_cellular_data_fallback_title" msgid="8753386877755616476">"自动切换到移动数据网络"</string>
    <string name="wifi_cellular_data_fallback_summary" msgid="1403505355490119307">"在 WLAN 网络无法连接到互联网时切换到移动数据网络（可能会消耗移动数据流量）。"</string>
    <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>
    <string name="wifi_configure_settings_preference_title" msgid="2913345003906899146">"WLAN 偏好设置"</string>
    <string name="wifi_configure_settings_preference_summary_wakeup_on" msgid="646393113104367290">"自动重新开启 WLAN"</string>
    <string name="wifi_configure_settings_preference_summary_wakeup_off" msgid="2782566279864356713">"不自动重新开启 WLAN"</string>
    <string name="wifi_access_points" msgid="7053990007031968609">"WLAN网络"</string>
    <string name="wifi_menu_more_options" msgid="2448097861752719396">"更多选项"</string>
    <string name="wifi_menu_p2p" msgid="7619851399250896797">"WLAN 直连"</string>
    <string name="wifi_menu_scan" msgid="1470911530412095868">"扫描"</string>
    <string name="wifi_menu_advanced" msgid="7522252991919573664">"高级"</string>
    <string name="wifi_menu_configure" msgid="6150926852602171938">"配置"</string>
    <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>
    <string name="wifi_menu_remember" msgid="8814185749388713796">"记住网络"</string>
    <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
    <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
    <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>
    <string name="wifi_empty_list_wifi_off" msgid="8056223875951079463">"要查看可用网络，请打开 WLAN。"</string>
    <string name="wifi_empty_list_wifi_on" msgid="8746108031587976356">"正在搜索WLAN网络…"</string>
    <string name="wifi_empty_list_user_restricted" msgid="7322372065475939129">"您无权更改WLAN网络。"</string>
    <string name="wifi_more" msgid="3195296805089107950">"更多"</string>
    <string name="wifi_setup_wps" msgid="8128702488486283957">"自动设置 (WPS)"</string>
    <string name="wifi_settings_scanning_required_title" msgid="3815269816331500375">"要开启 WLAN 扫描功能吗？"</string>
    <string name="wifi_settings_scanning_required_summary" msgid="6352918945128328916">"要自动开启 WLAN，您必须先开启 WLAN 扫描功能。"</string>
    <string name="wifi_settings_scanning_required_info" msgid="3155631874578023647">"WLAN 扫描功能可让应用和服务随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="wifi_settings_scanning_required_turn_on" msgid="1364287182804820646">"开启"</string>
    <string name="wifi_settings_scanning_required_enabled" msgid="5527653791584018157">"WLAN 扫描功能已开启"</string>
    <string name="wifi_show_advanced" msgid="3409422789616520979">"高级选项"</string>
    <string name="wifi_advanced_toggle_description_expanded" msgid="2380600578544493084">"高级选项下拉列表。点按两次即可收起。"</string>
    <string name="wifi_advanced_toggle_description_collapsed" msgid="1463812308429197263">"高级选项下拉列表。点按两次即可展开。"</string>
    <string name="wifi_ssid" msgid="5519636102673067319">"网络名称"</string>
    <string name="wifi_ssid_hint" msgid="897593601067321355">"输入SSID"</string>
    <string name="wifi_security" msgid="6603611185592956936">"安全性"</string>
    <string name="wifi_hidden_network" msgid="973162091800925000">"隐藏的网络"</string>
    <string name="wifi_hidden_network_warning" msgid="6674068093531603452">"如果您的路由器没有广播某个网络 ID，但是您想在以后连接到该网络，则可以将该网络设置为隐藏。\n\n此设置可能会带来安全风险，因为您的手机会定期广播其信号以查找该网络。\n\n将网络设置为隐藏将不会更改您的路由器设置。"</string>
    <string name="wifi_signal" msgid="5514120261628065287">"信号强度"</string>
    <string name="wifi_status" msgid="4824568012414605414">"状态信息"</string>
    <string name="wifi_speed" msgid="3526198708812322037">"连接速度"</string>
    <string name="wifi_frequency" msgid="7791090119577812214">"频率"</string>
    <string name="wifi_ip_address" msgid="1440054061044402918">"IP 地址"</string>
    <string name="passpoint_label" msgid="6381371313076009926">"保存方式："</string>
    <string name="passpoint_content" msgid="8447207162397870483">"<xliff:g id="NAME">%1$s</xliff:g>凭据"</string>
    <string name="wifi_eap_method" msgid="8529436133640730382">"EAP方法"</string>
    <string name="please_select_phase2" msgid="5231074529772044898">"阶段2身份验证"</string>
    <string name="wifi_eap_ca_cert" msgid="3521574865488892851">"CA证书"</string>
    <string name="wifi_eap_domain" msgid="8471124344218082064">"域名"</string>
    <string name="wifi_eap_user_cert" msgid="1291089413368160789">"用户证书"</string>
    <string name="wifi_eap_identity" msgid="4359453783379679103">"身份"</string>
    <string name="wifi_eap_anonymous" msgid="2989469344116577955">"匿名身份"</string>
    <string name="wifi_password" msgid="5948219759936151048">"密码"</string>
    <string name="wifi_show_password" msgid="6461249871236968884">"显示密码"</string>
    <string name="wifi_ap_band_config" msgid="1611826705989117930">"选择 AP 频段"</string>
    <string name="wifi_ap_choose_auto" msgid="2677800651271769965">"自动"</string>
    <string name="wifi_ap_choose_2G" msgid="8724267386885036210">"2.4 GHz 频段"</string>
    <string name="wifi_ap_choose_5G" msgid="8813128641914385634">"5.0 GHz 频段"</string>
    <string name="wifi_ap_2G" msgid="8378132945192979364">"2.4 GHz"</string>
    <string name="wifi_ap_5G" msgid="4020713496716329468">"5.0 GHz"</string>
    <string name="wifi_ap_band_select_one" msgid="3476254666116431650">"请为 WLAN 热点至少选择一个频段："</string>
    <string name="wifi_ip_settings" msgid="3359331401377059481">"IP 设置"</string>
    <string name="wifi_shared" msgid="844142443226926070">"与其他设备用户共享"</string>
    <string name="wifi_unchanged" msgid="3410422020930397102">"（未更改）"</string>
    <string name="wifi_unspecified" msgid="4917316464723064807">"请选择"</string>
    <string name="wifi_multiple_cert_added" msgid="3240743501460165224">"（已添加多份证书）"</string>
    <string name="wifi_use_system_certs" msgid="5270879895056893783">"使用系统证书"</string>
    <string name="wifi_do_not_provide_eap_user_cert" msgid="5160499244977160665">"不提供"</string>
    <string name="wifi_do_not_validate_eap_server" msgid="4266754430576348471">"不验证"</string>
    <string name="wifi_do_not_validate_eap_server_warning" msgid="1787190245542586660">"未指定任何证书。您的网络连接将不是私密连接。"</string>
    <string name="wifi_ssid_too_long" msgid="3474753269579895244">"网络名称太长。"</string>
    <string name="wifi_no_domain_warning" msgid="5223011964091727376">"必须指定域名。"</string>
    <string name="wifi_wps_available_first_item" msgid="4422547079984583502">"可使用 WPS"</string>
    <string name="wifi_wps_available_second_item" msgid="8427520131718215301">" （可使用 WPS）"</string>
    <string name="wifi_wps_nfc_enter_password" msgid="2288214226916117159">"请输入您的网络密码"</string>
    <string name="wifi_carrier_connect" msgid="8174696557882299911">"运营商 WLAN 网络"</string>
    <string name="wifi_carrier_content" msgid="4634077285415851933">"通过<xliff:g id="NAME">%1$s</xliff:g>连接"</string>
    <string name="wifi_scan_always_turnon_message" msgid="203123538572122989">"为了提高位置信息精确度以及其他目的，“<xliff:g id="APP_NAME">%1$s</xliff:g>”请求启用网络扫描功能（在关闭了WLAN时也可进行扫描）。\n\n是否对所有需要进行扫描的应用批准这项请求？"</string>
    <string name="wifi_scan_always_turnoff_message" msgid="5538901671131941043">"要关闭此功能，请转到菜单下的“高级”。"</string>
    <string name="wifi_scan_always_confirm_allow" msgid="5355973075896817232">"允许"</string>
    <string name="wifi_scan_always_confirm_deny" msgid="4463982053823520710">"拒绝"</string>
    <string name="wifi_hotspot_title" msgid="7726205804813286950">"要登录以便连接到网络吗？"</string>
    <string name="wifi_hotspot_message" msgid="3673833421453455747">"<xliff:g id="APP_NAME">%1$s</xliff:g>要求您必须先在线登录热点，然后才能连接到网络。"</string>
    <string name="wifi_hotspot_connect" msgid="5065506390164939225">"连接"</string>
    <string name="no_internet_access_text" msgid="5926979351959279577">"使用此网络将无法访问互联网。要继续保持连接吗？"</string>
    <string name="no_internet_access_remember" msgid="4697314331614625075">"对于此网络不再询问"</string>
    <string name="lost_internet_access_title" msgid="5779478650636392426">"此 WLAN 网络无法连接到互联网"</string>
    <string name="lost_internet_access_text" msgid="9029649339816197345">"当 WLAN 网络连接状况不佳时，您可以切换到移动网络（可能会产生移动数据流量费用）。"</string>
    <string name="lost_internet_access_switch" msgid="2262459569601190039">"切换到移动网络"</string>
    <string name="lost_internet_access_cancel" msgid="338273139419871110">"让 WLAN 保持开启状态"</string>
    <string name="lost_internet_access_persist" msgid="7634876061262676255">"不再显示"</string>
    <string name="wifi_connect" msgid="1076622875777072845">"连接"</string>
    <string name="wifi_failed_connect_message" msgid="8491902558970292871">"无法连接网络"</string>
    <string name="wifi_forget" msgid="8168174695608386644">"取消保存"</string>
    <string name="wifi_modify" msgid="6100248070440710782">"修改"</string>
    <string name="wifi_failed_forget_message" msgid="1348172929201654986">"无法取消保存网络"</string>
    <string name="wifi_save" msgid="3331121567988522826">"保存"</string>
    <string name="wifi_failed_save_message" msgid="6650004874143815692">"无法保存网络"</string>
    <string name="wifi_cancel" msgid="6763568902542968964">"取消"</string>
    <string name="wifi_forget_dialog_title" msgid="6224151903586192426">"要取消保存网络吗？"</string>
    <string name="wifi_forget_dialog_message" msgid="2337060138532166680">"系统将删除此网络的所有密码"</string>
    <string name="wifi_saved_access_points_titlebar" msgid="2996149477240134064">"已保存的网络"</string>
    <plurals name="wifi_saved_access_points_summary" formatted="false" msgid="6094679048871529675">
      <item quantity="other">%d 个网络</item>
      <item quantity="one">1 个网络</item>
    </plurals>
    <string name="wifi_advanced_titlebar" msgid="4485841401774142908">"高级WLAN"</string>
    <string name="wifi_advanced_mac_address_title" msgid="6571335466330978393">"MAC 地址"</string>
    <string name="wifi_advanced_ip_address_title" msgid="6215297094363164846">"IP 地址"</string>
    <string name="wifi_details_title" msgid="8954667664081737098">"网络详情"</string>
    <string name="wifi_details_subnet_mask" msgid="6720279144174924410">"子网掩码"</string>
    <string name="wifi_details_dns" msgid="8648826607751830768">"DNS"</string>
    <string name="wifi_details_ipv6_address_header" msgid="6734119149106422148">"IPv6 地址"</string>
    <string name="wifi_saved_access_points_label" msgid="2013409399392285262">"已保存的网络"</string>
    <string name="wifi_advanced_settings_label" msgid="3654366894867838338">"IP 设置"</string>
    <string name="wifi_advanced_not_available" msgid="5823045095444154586">"此用户无法查看或修改 WLAN 网络高级设置"</string>
    <string name="wifi_ip_settings_menu_save" msgid="7296724066102908366">"保存"</string>
    <string name="wifi_ip_settings_menu_cancel" msgid="6582567330136502340">"取消"</string>
    <string name="wifi_ip_settings_invalid_ip_address" msgid="2513142355364274970">"请输入有效的IP地址。"</string>
    <string name="wifi_ip_settings_invalid_gateway" msgid="8164264988361096450">"请键入有效的网关地址。"</string>
    <string name="wifi_ip_settings_invalid_dns" msgid="8744583948328391047">"请输入有效的DNS地址。"</string>
    <string name="wifi_ip_settings_invalid_network_prefix_length" msgid="40470058023181052">"键入长度在 0 到 32 之间的网络前缀。"</string>
    <string name="wifi_dns1" msgid="7344118050720080045">"DNS 1"</string>
    <string name="wifi_dns2" msgid="1368601006824882659">"DNS 2"</string>
    <string name="wifi_gateway" msgid="163914742461092086">"网关"</string>
    <string name="wifi_network_prefix_length" msgid="3028785234245085998">"网络前缀长度"</string>
    <string name="wifi_p2p_settings_title" msgid="5444461191435291082">"WLAN 直连"</string>
    <string name="wifi_p2p_device_info" msgid="3191876744469364173">"设备信息"</string>
    <string name="wifi_p2p_persist_network" msgid="1646424791818168590">"记住该连接"</string>
    <string name="wifi_p2p_menu_search" msgid="3436429984738771974">"搜索设备"</string>
    <string name="wifi_p2p_menu_searching" msgid="2396704492143633876">"正在搜索…"</string>
    <string name="wifi_p2p_menu_rename" msgid="8448896306960060415">"重命名设备"</string>
    <string name="wifi_p2p_peer_devices" msgid="299526878463303432">"对等设备"</string>
    <string name="wifi_p2p_remembered_groups" msgid="3847022927914068230">"已保存的群组"</string>
    <string name="wifi_p2p_failed_connect_message" msgid="8491862096448192157">"无法连接。"</string>
    <string name="wifi_p2p_failed_rename_message" msgid="2562182284946936380">"无法重命名设备。"</string>
    <string name="wifi_p2p_disconnect_title" msgid="3216846049677448420">"要断开连接吗？"</string>
    <string name="wifi_p2p_disconnect_message" msgid="8227342771610125771">"如果断开连接，您与<xliff:g id="PEER_NAME">%1$s</xliff:g>的连接将中断。"</string>
    <string name="wifi_p2p_disconnect_multiple_message" msgid="3283805371034883105">"如果断开连接，您与<xliff:g id="PEER_NAME">%1$s</xliff:g>和另外 <xliff:g id="PEER_COUNT">%2$s</xliff:g> 台设备的连接将中断。"</string>
    <string name="wifi_p2p_cancel_connect_title" msgid="255267538099324413">"取消邀请？"</string>
    <string name="wifi_p2p_cancel_connect_message" msgid="7477756213423749402">"要取消连接<xliff:g id="PEER_NAME">%1$s</xliff:g>的邀请吗？"</string>
    <string name="wifi_p2p_delete_group_message" msgid="834559380069647086">"取消保存此群组？"</string>
    <string name="wifi_hotspot_checkbox_text" msgid="7763495093333664887">"WLAN 热点"</string>
    <string name="wifi_hotspot_off_subtext" msgid="2199911382555864644">"目前没有与其他设备共享互联网连接或内容"</string>
    <string name="wifi_hotspot_tethering_on_subtext" product="tablet" msgid="5936710887156133458">"正在通过热点共享此平板电脑的互联网连接"</string>
    <string name="wifi_hotspot_tethering_on_subtext" product="default" msgid="5556202634866621632">"正在通过热点共享此手机的互联网连接"</string>
    <string name="wifi_hotspot_on_local_only_subtext" msgid="5017191966153008">"应用正在共享内容。要共享互联网连接，请先关闭热点，然后重新打开"</string>
    <string name="wifi_hotspot_no_password_subtext" msgid="353306131026431089">"未设置密码"</string>
    <string name="wifi_hotspot_name_title" msgid="8237000746618636778">"热点名称"</string>
    <string name="wifi_hotspot_name_summary_connecting" msgid="3378299995508671967">"正在打开<xliff:g id="WIFI_HOTSPOT_NAME">%1$s</xliff:g>…"</string>
    <string name="wifi_hotspot_name_summary_connected" msgid="3888672084861445362">"其他设备可以连接到<xliff:g id="WIFI_HOTSPOT_NAME">%1$s</xliff:g>"</string>
    <string name="wifi_hotspot_password_title" msgid="8676859981917573801">"热点密码"</string>
    <string name="wifi_hotspot_ap_band_title" msgid="1165801173359290681">"AP 频段"</string>
    <string name="wifi_hotspot_footer_info_regular" msgid="4789553667374849566">"使用热点创建 WLAN 网络供其他设备使用。热点会使用您的移动数据连接提供互联网连接。这可能会产生额外的移动数据流量费用。"</string>
    <string name="wifi_hotspot_footer_info_local_only" msgid="857988412470694109">"应用可以通过创建热点，与附近的设备共享内容。"</string>
    <string name="wifi_hotspot_auto_off_title" msgid="1590313508558948079">"自动关闭热点"</string>
    <string name="wifi_hotspot_auto_off_summary" msgid="5858098059725925084">"如果未连接任何设备，WLAN 热点将关闭"</string>
    <string name="wifi_tether_starting" msgid="1322237938998639724">"正在打开热点..."</string>
    <string name="wifi_tether_stopping" msgid="4835852171686388107">"正在关闭热点..."</string>
    <string name="wifi_tether_enabled_subtext" msgid="7842111748046063857">"<xliff:g id="NETWORK_SSID">%1$s</xliff:g> 已连接"</string>
    <string name="wifi_tether_failed_subtext" msgid="1484941858530919002">"便携式WLAN热点错误"</string>
    <string name="wifi_tether_configure_ap_text" msgid="7974681394041609308">"设置WLAN热点"</string>
    <string name="wifi_hotspot_configure_ap_text" msgid="5478614731464220432">"WLAN热点设置"</string>
    <string name="wifi_hotspot_configure_ap_text_summary" msgid="5560680057727007011">"AndroidAP WPA2 PSK 热点"</string>
    <string name="wifi_tether_configure_ssid_default" msgid="8467525402622138547">"Android热点"</string>
    <string name="wifi_tether_disabled_by_airplane" msgid="414480185654767932">"无法使用，因为已开启飞行模式"</string>
    <string name="wifi_calling_settings_title" msgid="4102921303993404577">"WLAN 通话"</string>
    <string name="wifi_calling_suggestion_title" msgid="5702964371483390024">"通过 WLAN 扩大通话覆盖范围"</string>
    <string name="wifi_calling_suggestion_summary" msgid="1331793267608673739">"开启 WLAN 通话功能以扩大通话覆盖范围"</string>
    <string name="wifi_calling_mode_title" msgid="2164073796253284289">"通话偏好设置"</string>
    <string name="wifi_calling_mode_dialog_title" msgid="8149690312199253909">"WLAN 通话模式"</string>
    <string name="wifi_calling_roaming_mode_title" msgid="1565039047187685115">"漫游偏好设置"</string>
    <!-- no translation found for wifi_calling_roaming_mode_summary (8642014873060687717) -->
    <skip />
    <string name="wifi_calling_roaming_mode_dialog_title" msgid="7800926602662078576">"漫游偏好设置"</string>
  <string-array name="wifi_calling_mode_choices">
    <item msgid="2124257075906188844">"首选 WLAN"</item>
    <item msgid="1335127656328817518">"首选移动数据网络"</item>
    <item msgid="3132912693346866895">"仅限 WLAN"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_v2">
    <item msgid="742988808283756263">"WLAN"</item>
    <item msgid="7715869266611010880">"移动数据"</item>
    <item msgid="2838022395783120596">"仅限 WLAN"</item>
  </string-array>
  <string-array name="wifi_calling_mode_values">
    <item msgid="4799585830102342375">"2"</item>
    <item msgid="1171822231056612021">"1"</item>
    <item msgid="3194458950573886239">"0"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_without_wifi_only">
    <item msgid="5782108782860004851">"首选 WLAN"</item>
    <item msgid="5074515506087318555">"首选移动数据网络"</item>
  </string-array>
  <string-array name="wifi_calling_mode_choices_v2_without_wifi_only">
    <item msgid="6132150507201243768">"WLAN"</item>
    <item msgid="1118703915148755405">"移动数据"</item>
  </string-array>
  <string-array name="wifi_calling_mode_values_without_wifi_only">
    <item msgid="2339246858001475047">"2"</item>
    <item msgid="6200207341126893791">"1"</item>
  </string-array>
    <string name="wifi_calling_off_explanation" msgid="2597566001655908391">"开启“WLAN 通话”功能后，您的手机可根据您的偏好设置，通过 WLAN 网络或您的运营商网络通话（具体取决于哪个网络信号较强）。开启此功能之前，请先向您的运营商咨询收费情况及其他详情。"</string>
    <string name="wifi_calling_off_explanation_2" msgid="2329334487851497223"></string>
    <string name="emergency_address_title" msgid="932729250447887545">"紧急联系地址"</string>
    <string name="emergency_address_summary" msgid="7751971156196115129">"当您通过 WLAN 网络拨打紧急呼救电话时，系统会判定您位于这个位置"</string>
    <string name="private_dns_help_message" msgid="3299567069152568958"><annotation id="url">"详细了解"</annotation>"私人 DNS 功能"</string>
    <string name="wifi_calling_pref_managed_by_carrier" msgid="6845711858866828986">"这项设置由运营商管理"</string>
    <string name="wifi_calling_settings_activation_instructions" msgid="7492509632478260955">"请启用 WLAN 通话功能"</string>
    <string name="wifi_calling_turn_on" msgid="1171403510313983983">"请开启 WLAN 通话功能"</string>
    <string name="wifi_calling_not_supported" msgid="7878640438907807754">"%1$s不支持 WLAN 通话功能"</string>
    <string name="carrier" msgid="5264014738689761132">"运营商"</string>
    <string name="display_settings_title" msgid="1708697328627382561">"显示"</string>
    <string name="sound_settings" msgid="5534671337768745343">"声音"</string>
    <string name="all_volume_title" msgid="4296957391257836961">"音量"</string>
    <string name="musicfx_title" msgid="3415566786340790345">"音乐效果"</string>
    <string name="ring_volume_title" msgid="5592466070832128777">"铃声音量"</string>
    <string name="vibrate_in_silent_title" msgid="3897968069156767036">"静音时振动"</string>
    <string name="notification_sound_title" msgid="5137483249425507572">"默认通知提示音"</string>
    <string name="incoming_call_volume_title" msgid="8073714801365904099">"铃声"</string>
    <string name="notification_volume_title" msgid="2012640760341080408">"通知"</string>
    <string name="checkbox_notification_same_as_incoming_call" msgid="1073644356290338921">"将来电音量用作通知音量"</string>
    <string name="home_work_profile_not_supported" msgid="1357721012342357037">"不支持工作资料"</string>
    <string name="notification_sound_dialog_title" msgid="3805140135741385667">"默认通知提示音"</string>
    <string name="media_volume_title" msgid="3576565767317118106">"媒体"</string>
    <string name="media_volume_summary" msgid="5363248930648849974">"设置音乐和视频的音量"</string>
    <string name="alarm_volume_title" msgid="2285597478377758706">"闹钟"</string>
    <string name="dock_settings_summary" msgid="455802113668982481">"附加基座的音频设置"</string>
    <string name="dtmf_tone_enable_title" msgid="8533399267725365088">"拨号键盘触摸音效"</string>
    <string name="sound_effects_enable_title" msgid="4429690369187229592">"点按提示音"</string>
    <string name="lock_sounds_enable_title" msgid="450098505659399520">"锁屏提示音"</string>
    <string name="haptic_feedback_enable_title" msgid="7152163068278526530">"点按时振动"</string>
    <string name="audio_record_proc_title" msgid="4271091199976457534">"降噪"</string>
    <string name="volume_media_description" msgid="7949355934788807863">"音乐、视频、游戏和其他媒体"</string>
    <string name="volume_ring_description" msgid="5936851631698298989">"铃声和通知"</string>
    <string name="volume_notification_description" msgid="5810902320215328321">"通知"</string>
    <string name="volume_alarm_description" msgid="8322615148532654841">"闹钟"</string>
    <string name="volume_ring_mute" msgid="3018992671608737202">"使铃声和通知静音"</string>
    <string name="volume_media_mute" msgid="3399059928695998166">"使音乐和其他媒体静音"</string>
    <string name="volume_notification_mute" msgid="7955193480006444159">"使通知静音"</string>
    <string name="volume_alarm_mute" msgid="4452239420351035936">"使闹钟静音"</string>
    <string name="dock_settings" msgid="1820107306693002541">"基座"</string>
    <string name="dock_settings_title" msgid="9161438664257429372">"基座设置"</string>
    <string name="dock_audio_settings_title" msgid="3324750259959570305">"音频"</string>
    <string name="dock_audio_summary_desk" msgid="6487784412371139335">"附加桌面基座的设置"</string>
    <string name="dock_audio_summary_car" msgid="6740897586006248450">"附加车载基座的设置"</string>
    <string name="dock_audio_summary_none" product="tablet" msgid="8215337394914283607">"平板电脑未插入基座"</string>
    <string name="dock_audio_summary_none" product="default" msgid="289909253741048784">"手机未插入基座"</string>
    <string name="dock_audio_summary_unknown" msgid="4465059868974255693">"附加基座的设置"</string>
    <string name="dock_not_found_title" msgid="3290961741828952424">"未找到基座"</string>
    <string name="dock_not_found_text" product="tablet" msgid="8405432495282299143">"您需要先将平板电脑插入基座，才能设置基座音频。"</string>
    <string name="dock_not_found_text" product="default" msgid="1460497923342627801">"您需要先将手机插入基座，才能设置基座音频。"</string>
    <string name="dock_sounds_enable_title" msgid="885839627097024110">"插入基座提示音"</string>
    <string name="dock_sounds_enable_summary_on" product="tablet" msgid="838102386448981339">"平板电脑插入基座或拔出时发出声音"</string>
    <string name="dock_sounds_enable_summary_on" product="default" msgid="8491180514199743771">"手机插入基座或拔出时发出声音"</string>
    <string name="dock_sounds_enable_summary_off" product="tablet" msgid="4308252722466813560">"平板电脑插入基座或拔出时不发出声音"</string>
    <string name="dock_sounds_enable_summary_off" product="default" msgid="2034927992716667672">"手机插入基座或拔出时不发出声音"</string>
    <string name="account_settings" msgid="6403589284618783461">"帐号"</string>
    <string name="accessibility_category_work" msgid="4339262969083355720">"工作资料帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="accessibility_category_personal" msgid="1263518850905945594">"个人资料帐号"</string>
    <string name="accessibility_work_account_title" msgid="1231830766637939527">"工作帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="accessibility_personal_account_title" msgid="2169071663029067826">"个人帐号 - <xliff:g id="MANAGED_BY">%s</xliff:g>"</string>
    <string name="search_settings" msgid="1910951467596035063">"搜索"</string>
    <string name="display_settings" msgid="7965901687241669598">"显示"</string>
    <string name="accelerometer_title" msgid="7854608399547349157">"自动旋转屏幕"</string>
    <string name="color_mode_title" msgid="9186249332902370471">"颜色"</string>
    <string name="color_mode_option_natural" msgid="5013837483986772758">"自然色"</string>
    <string name="color_mode_option_boosted" msgid="8588223970257287524">"效果增强"</string>
    <string name="color_mode_option_saturated" msgid="4569683960058798843">"饱和色"</string>
    <string name="color_mode_option_automatic" msgid="8781254568140509331">"自动"</string>
    <string name="color_mode_summary_natural" msgid="6624188642920403099">"仅使用准确色彩"</string>
    <string name="color_mode_summary_automatic" msgid="4669516973360709431">"在鲜明和准确色彩之间进行调整"</string>
    <string name="accelerometer_summary_on" product="tablet" msgid="429982132339828942">"旋转平板电脑时自动改变浏览模式"</string>
    <string name="accelerometer_summary_on" product="default" msgid="1133737282813048021">"旋转手机时自动改变显示方向"</string>
    <string name="accelerometer_summary_off" product="tablet" msgid="4781734213242521682">"旋转平板电脑时自动切换浏览模式"</string>
    <string name="accelerometer_summary_off" product="default" msgid="5485489363715740761">"旋转手机时自动改变显示方向"</string>
    <string name="brightness" msgid="8480105032417444275">"亮度"</string>
    <string name="brightness_title" msgid="5746272622112982836">"亮度"</string>
    <string name="brightness_summary" msgid="838917350127550703">"调整屏幕亮度"</string>
    <string name="auto_brightness_title" msgid="6341042882350279391">"自动调节亮度"</string>
    <string name="auto_brightness_summary" msgid="1799041158760605375">"根据环境光线情况优化亮度"</string>
    <string name="auto_brightness_summary_off" msgid="2802336459335410626">"关闭"</string>
    <string name="auto_brightness_summary_very_low" msgid="6483976609035853764">"偏好的亮度为“很低”"</string>
    <string name="auto_brightness_summary_low" msgid="5609877905833960427">"偏好的亮度为“低”"</string>
    <string name="auto_brightness_summary_default" msgid="7225666614394726845">"默认使用偏好的亮度"</string>
    <string name="auto_brightness_summary_high" msgid="7172304165631136027">"偏好的亮度为“高”"</string>
    <string name="auto_brightness_summary_very_high" msgid="979277812582279078">"偏好的亮度为“很高”"</string>
    <string name="auto_brightness_off_title" msgid="2996864829946190795">"关闭"</string>
    <string name="auto_brightness_very_low_title" msgid="8252988638614126320">"很低"</string>
    <string name="auto_brightness_low_title" msgid="1632186441514863377">"低"</string>
    <string name="auto_brightness_default_title" msgid="936771997353506620">"默认"</string>
    <string name="auto_brightness_high_title" msgid="2527853305981497345">"高"</string>
    <string name="auto_brightness_very_high_title" msgid="8867164854439331022">"很高"</string>
    <string name="auto_brightness_subtitle" msgid="6454652530864093466">"您偏好的亮度"</string>
    <string name="auto_brightness_off_summary" msgid="7629228736838155268">"不根据环境光线情况调整亮度"</string>
    <string name="auto_brightness_very_high_summary" msgid="4551003097086220709">"耗电量更高"</string>
    <string name="auto_brightness_disclaimer" msgid="871436423746343406">"根据环境光线情况优化亮度。开启此功能后，您仍然可以暂时调整亮度。"</string>
    <string name="auto_brightness_description" msgid="7310335517128283729">"您的屏幕亮度将根据您的环境和活动自动调节。您可以手动移动滑块，以帮助“自动调节亮度”功能了解您偏好的亮度。"</string>
    <string name="night_display_title" msgid="2626451512200357686">"夜间模式"</string>
    <string name="night_display_text" msgid="1837277457033025056">"夜间模式会将您的屏幕色调调为琥珀色，可让您在光线昏暗的环境下更舒适地查看屏幕或阅读文字，并有助您入睡。"</string>
    <string name="night_display_auto_mode_title" msgid="6574111412154833409">"排定时间"</string>
    <string name="night_display_auto_mode_never" msgid="2483761922928753400">"无"</string>
    <string name="night_display_auto_mode_custom" msgid="2379394568898721765">"在设定的时间开启"</string>
    <string name="night_display_auto_mode_twilight" msgid="589042813708244059">"在日落到日出期间开启"</string>
    <string name="night_display_start_time_title" msgid="8918016772613689584">"开始时间"</string>
    <string name="night_display_end_time_title" msgid="8286061578083519350">"结束时间"</string>
    <string name="night_display_status_title" msgid="1784041143360286267">"状态"</string>
    <string name="night_display_temperature_title" msgid="1435292789272017136">"浓度"</string>
    <string name="night_display_summary_off" msgid="1792750041697946539">"关闭 / <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_summary_off_auto_mode_never" msgid="3583590137322963513">"一律不自动开启"</string>
    <string name="night_display_summary_off_auto_mode_custom" msgid="6365668239253173208">"将在<xliff:g id="ID_1">%1$s</xliff:g>自动开启"</string>
    <string name="night_display_summary_off_auto_mode_twilight" msgid="3596291693781757392">"将在日落时自动开启"</string>
    <string name="night_display_summary_on" msgid="1355713529996456744">"开启 / <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_summary_on_auto_mode_never" msgid="9117830821363119835">"一律不自动关闭"</string>
    <string name="night_display_summary_on_auto_mode_custom" msgid="5510753572245577263">"将在<xliff:g id="ID_1">%1$s</xliff:g>自动关闭"</string>
    <string name="night_display_summary_on_auto_mode_twilight" msgid="852270120144683507">"将在日出时自动关闭"</string>
    <string name="night_display_activation_on_manual" msgid="277343561277625826">"立即开启"</string>
    <string name="night_display_activation_off_manual" msgid="4074557720918572883">"立即关闭"</string>
    <string name="night_display_activation_on_twilight" msgid="6976051971534953845">"保持开启状态，直到日出"</string>
    <string name="night_display_activation_off_twilight" msgid="7196227685059907233">"保持关闭状态，直到日落"</string>
    <string name="night_display_activation_on_custom" msgid="5472029024427933598">"保持开启状态，直到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="night_display_activation_off_custom" msgid="6169984658293744715">"保持关闭状态，直到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="screen_timeout" msgid="4351334843529712571">"休眠"</string>
    <string name="screen_timeout_title" msgid="5130038655092628247">"屏幕关闭"</string>
    <string name="screen_timeout_summary" msgid="327761329263064327">"无操作<xliff:g id="TIMEOUT_DESCRIPTION">%1$s</xliff:g>后"</string>
    <string name="wallpaper_settings_title" msgid="5449180116365824625">"壁纸"</string>
    <string name="wallpaper_settings_summary_default" msgid="3395741565658711416">"默认"</string>
    <string name="wallpaper_settings_summary_custom" msgid="515035303981687172">"自定义"</string>
    <string name="wallpaper_suggestion_title" msgid="8583988696513822528">"更换壁纸"</string>
    <string name="wallpaper_suggestion_summary" msgid="1579144009898110491">"对屏幕进行个性化设置"</string>
    <string name="wallpaper_settings_fragment_title" msgid="519078346877860129">"选择壁纸来源"</string>
    <string name="screensaver_settings_title" msgid="1770575686476851778">"屏保"</string>
    <string name="screensaver_settings_summary_either_long" msgid="7302740999250873332">"充电或插入基座时"</string>
    <string name="screensaver_settings_summary_either_short" msgid="6140527286137331478">"以上任一情况"</string>
    <string name="screensaver_settings_summary_sleep" msgid="9086186698140423493">"充电时"</string>
    <string name="screensaver_settings_summary_dock" msgid="2072657401664633283">"插入基座时"</string>
    <string name="screensaver_settings_summary_never" msgid="5165622985174349585">"一律不"</string>
    <string name="screensaver_settings_summary_off" msgid="2481581696365146473">"关闭"</string>
    <string name="screensaver_settings_disabled_prompt" msgid="1239088321034437608">"要控制手机在插入基座时和/或休眠状态下的行为，请开启屏保功能。"</string>
    <string name="screensaver_settings_when_to_dream" msgid="7262410541382890146">"启用时间"</string>
    <string name="screensaver_settings_current" msgid="4663846038247130023">"当前的屏保"</string>
    <string name="screensaver_settings_dream_start" msgid="4998187847985120168">"立即启动"</string>
    <string name="screensaver_settings_button" msgid="7292214707625717013">"设置"</string>
    <string name="automatic_brightness" msgid="5014143533884135461">"自动调整亮度"</string>
    <string name="lift_to_wake_title" msgid="4555378006856277635">"拿起设备时唤醒"</string>
    <string name="ambient_display_screen_title" msgid="4252755516328775766">"主动显示"</string>
    <string name="ambient_display_screen_summary_always_on" msgid="7337555569694794132">"一律开启/耗电量更高"</string>
    <string name="ambient_display_screen_summary_notifications" msgid="1449570742600868654">"新通知"</string>
    <string name="ambient_display_category_triggers" msgid="4359289754456268573">"何时显示"</string>
    <string name="doze_title" msgid="2375510714460456687">"新通知"</string>
    <string name="doze_summary" msgid="3846219936142814032">"收到通知时唤醒屏幕"</string>
    <string name="doze_always_on_title" msgid="1046222370442629646">"始终开启"</string>
    <string name="doze_always_on_summary" msgid="6978257596231155345">"显示时间、通知图标及其他信息。耗电量更高。"</string>
    <string name="title_font_size" msgid="4405544325522105222">"字体大小"</string>
    <string name="short_summary_font_size" msgid="6819778801232989076">"放大或缩小文字"</string>
    <string name="sim_lock_settings" msgid="3392331196873564292">"SIM 卡锁定设置"</string>
    <string name="sim_lock_settings_category" msgid="6242052161214271091">"SIM 卡锁定"</string>
    <string name="sim_lock_settings_summary_off" msgid="8028944267104896401">"关闭"</string>
    <string name="sim_lock_settings_summary_on" msgid="39103355956342985">"已锁定"</string>
    <string name="sim_lock_settings_title" msgid="9018585580955414596">"SIM 卡锁定方式"</string>
    <string name="sim_pin_toggle" msgid="1742123478029451888">"锁定 SIM 卡"</string>
    <string name="sim_lock_on" product="tablet" msgid="5058355081270397764">"需要输入PIN码才能使用平板电脑"</string>
    <string name="sim_lock_on" product="default" msgid="2503536505568814324">"需要输入 PIN 码才能使用手机"</string>
    <string name="sim_lock_off" product="tablet" msgid="2813800553917012356">"需要输入PIN码才能使用平板电脑"</string>
    <string name="sim_lock_off" product="default" msgid="258981978215428916">"需要输入 PIN 码才能使用手机"</string>
    <string name="sim_pin_change" msgid="6311414184279932368">"更改 SIM 卡 PIN 码"</string>
    <string name="sim_enter_pin" msgid="6608715137008508432">"SIM 卡 PIN 码"</string>
    <string name="sim_enable_sim_lock" msgid="4517742794997166918">"锁定 SIM 卡"</string>
    <string name="sim_disable_sim_lock" msgid="7664729528754784824">"解锁SIM卡"</string>
    <string name="sim_enter_old" msgid="6074196344494634348">"旧 SIM 卡 PIN 码"</string>
    <string name="sim_enter_new" msgid="8742727032729243562">"新 SIM 卡 PIN 码"</string>
    <string name="sim_reenter_new" msgid="6523819386793546888">"重新输入新的PIN码"</string>
    <string name="sim_change_pin" msgid="7328607264898359112">"SIM 卡 PIN 码"</string>
    <string name="sim_bad_pin" msgid="2345230873496357977">"PIN 码不正确"</string>
    <string name="sim_pins_dont_match" msgid="1695021563878890574">"PIN码不匹配"</string>
    <string name="sim_change_failed" msgid="3602072380172511475">"无法更改PIN码。\n输入的PIN码可能不正确。"</string>
    <string name="sim_change_succeeded" msgid="8556135413096489627">"已成功更改 SIM 卡 PIN 码"</string>
    <string name="sim_lock_failed" msgid="2489611099235575984">"无法更改 SIM 卡锁定状态。\nPIN 码可能不正确。"</string>
    <string name="sim_enter_ok" msgid="6475946836899218919">"确定"</string>
    <string name="sim_enter_cancel" msgid="6240422158517208036">"取消"</string>
    <string name="sim_multi_sims_title" msgid="9159427879911231239">"找到多张SIM卡"</string>
    <string name="sim_multi_sims_summary" msgid="2698176447067691396">"选择您想用于移动数据网络的 SIM 卡。"</string>
    <string name="sim_change_data_title" msgid="294357201685244532">"要更改用于数据网络的 SIM 卡吗？"</string>
    <string name="sim_change_data_message" msgid="5854582807996717811">"要将用于移动数据网络的 SIM 卡从“<xliff:g id="OLD_SIM">%2$s</xliff:g>”改为“<xliff:g id="NEW_SIM">%1$s</xliff:g>”吗？"</string>
    <string name="sim_preferred_title" msgid="5567909634636045268">"要更新首选 SIM 卡吗？"</string>
    <string name="sim_preferred_message" msgid="8466930554330635780">"“<xliff:g id="NEW_SIM">%1$s</xliff:g>”是此设备上唯一的 SIM 卡。您要将此 SIM 卡用于移动数据网络、通话和收发短信吗？"</string>
    <string name="wrong_pin_code_pukked" msgid="4003655226832658066">"SIM 卡 PIN 码不正确，您现在必须联系运营商为您解锁设备。"</string>
    <plurals name="wrong_pin_code" formatted="false" msgid="1582398808893048097">
      <item quantity="other">SIM 卡 PIN 码不正确，您还可尝试 <xliff:g id="NUMBER_1">%d</xliff:g> 次。</item>
      <item quantity="one">SIM 卡 PIN 码不正确，您还可尝试 <xliff:g id="NUMBER_0">%d</xliff:g> 次。如果仍不正确，则需要联系运营商帮您解锁设备。</item>
    </plurals>
    <string name="pin_failed" msgid="1848423634948587645">"SIM 卡 PIN 码操作失败！"</string>
    <string name="device_info_settings" product="tablet" msgid="1119755927536987178">"平板电脑状态"</string>
    <string name="device_info_settings" product="default" msgid="475872867864762157">"手机状态"</string>
    <string name="system_update_settings_list_item_title" msgid="3342887311059985961">"系统更新"</string>
    <string name="system_update_settings_list_item_summary" msgid="3853057315907710747"></string>
    <string name="firmware_version" msgid="4801135784886859972">"Android 版本"</string>
    <string name="firmware_title" msgid="5203122368389157877">"Android"</string>
    <string name="security_patch" msgid="8438384045870296634">"Android 安全补丁程序级别"</string>
    <string name="model_info" msgid="1952009518045740889">"型号"</string>
    <string name="model_summary" msgid="8306235877567782987">"型号：%1$s"</string>
    <string name="hardware_info" msgid="2605080746512527805">"型号和硬件"</string>
    <string name="hardware_revision" msgid="8893547686367095527">"硬件版本"</string>
    <string name="fcc_equipment_id" msgid="149114368246356737">"设备 ID"</string>
    <string name="baseband_version" msgid="1848990160763524801">"基带版本"</string>
    <string name="kernel_version" msgid="9192574954196167602">"内核版本"</string>
    <string name="build_number" msgid="3075795840572241758">"版本号"</string>
    <string name="device_info_not_available" msgid="8062521887156825182">"无法获取"</string>
    <string name="device_status_activity_title" msgid="1411201799384697904">"状态信息"</string>
    <string name="device_status" msgid="607405385799807324">"状态信息"</string>
    <string name="device_status_summary" product="tablet" msgid="3292717754497039686">"电池状态、网络状态和其他信息"</string>
    <string name="device_status_summary" product="default" msgid="2599162787451519618">"电话号码、信号等"</string>
    <string name="storage_settings" msgid="4211799979832404953">"存储"</string>
    <string name="storage_usb_settings" msgid="7293054033137078060">"存储"</string>
    <string name="storage_settings_title" msgid="8746016738388094064">"存储设置"</string>
    <string name="storage_settings_summary" product="nosdcard" msgid="3543813623294870759">"卸载USB存储设备，查看可用存储设备"</string>
    <string name="storage_settings_summary" product="default" msgid="9176693537325988610">"卸载SD卡，查看可用存储设备"</string>
    <string name="imei_multi_sim" msgid="6387012961838800539">"IMEI（SIM 卡插槽 %1$d）"</string>
    <string name="status_number" product="tablet" msgid="1138837891091222272">"移动目录编号 (MDN)"</string>
    <string name="status_number" product="default" msgid="5948892105546651296">"手机号码"</string>
    <string name="status_number_sim_slot" product="tablet" msgid="2755592991367858860">"MDN（SIM 卡插槽 %1$d）"</string>
    <string name="status_number_sim_slot" product="default" msgid="1898212200138025729">"电话号码（SIM 卡插槽 %1$d）"</string>
    <string name="status_number_sim_status" product="tablet" msgid="1367110147304523864">"SIM 卡上的 MDN"</string>
    <string name="status_number_sim_status" product="default" msgid="9123351360569466330">"SIM 卡上的电话号码"</string>
    <string name="status_min_number" msgid="3519504522179420597">"MIN"</string>
    <string name="status_msid_number" msgid="909010114445780530">"MSID"</string>
    <string name="status_prl_version" msgid="1007470446618081441">"PRL 版本"</string>
    <string name="meid_multi_sim" msgid="748999971744491771">"MEID（SIM 卡插槽 %1$d）"</string>
    <string name="status_meid_number" msgid="1751442889111731088">"MEID"</string>
    <string name="status_icc_id" msgid="943368755577172747">"ICCID"</string>
    <string name="status_data_network_type" msgid="7570837037428932780">"移动数据网络类型"</string>
    <string name="status_voice_network_type" msgid="5663112239742353547">"移动语音网络类型"</string>
    <string name="status_latest_area_info" msgid="7222470836568238054">"运营商信息"</string>
    <string name="status_data_state" msgid="5503181397066522950">"移动网络状态"</string>
    <string name="status_esim_id" msgid="6456255368300906317">"EID"</string>
    <string name="status_service_state" msgid="2323931627519429503">"服务状态"</string>
    <string name="status_signal_strength" msgid="3732655254188304547">"信号强度"</string>
    <string name="status_roaming" msgid="2638800467430913403">"漫游"</string>
    <string name="status_operator" msgid="2274875196954742087">"网络"</string>
    <string name="status_wifi_mac_address" msgid="2202206684020765378">"WLAN MAC 地址"</string>
    <string name="status_bt_address" msgid="4195174192087439720">"蓝牙地址"</string>
    <string name="status_serial_number" msgid="2257111183374628137">"序列号"</string>
    <string name="status_up_time" msgid="7294859476816760399">"已开机时间"</string>
    <string name="status_awake_time" msgid="2393949909051183652">"唤醒时间"</string>
    <string name="internal_memory" msgid="9129595691484260784">"内部存储空间"</string>
    <string name="sd_memory" product="nosdcard" msgid="2510246194083052841">"USB存储设备"</string>
    <string name="sd_memory" product="default" msgid="151871913888051515">"SD卡"</string>
    <string name="memory_available" msgid="5052397223077021181">"可用空间"</string>
    <string name="memory_available_read_only" msgid="6497534390167920206">"可用空间（只读）"</string>
    <string name="memory_size" msgid="6629067715017232195">"总容量"</string>
    <string name="memory_calculating_size" msgid="2188358544203768588">"正在计算..."</string>
    <string name="memory_apps_usage" msgid="5128673488173839077">"应用和应用数据"</string>
    <string name="memory_media_usage" msgid="3738830697707880405">"媒体"</string>
    <string name="memory_downloads_usage" msgid="3755173051677533027">"下载内容"</string>
    <string name="memory_dcim_usage" msgid="558887013613822577">"图片、视频"</string>
    <string name="memory_music_usage" msgid="1363785144783011606">"音频（音乐、铃声、播客等）"</string>
    <string name="memory_media_misc_usage" msgid="6094866738586451683">"其他文件"</string>
    <string name="memory_media_cache_usage" msgid="6704293333141177910">"缓存数据"</string>
    <string name="sd_eject" product="nosdcard" msgid="4988563376492400073">"卸载共享存储设备"</string>
    <string name="sd_eject" product="default" msgid="6915293408836853020">"卸载SD卡"</string>
    <string name="sd_eject_summary" product="nosdcard" msgid="5009296896648072891">"卸载内部USB存储设备"</string>
    <string name="sd_eject_summary" product="default" msgid="3300599435073550246">"需要先卸载SD卡，然后才能将其安全移除"</string>
    <string name="sd_insert_summary" product="nosdcard" msgid="5264016886409577313">"插入要装载的USB存储设备"</string>
    <string name="sd_insert_summary" product="default" msgid="2048640010381803841">"插入SD卡进行安装"</string>
    <string name="sd_mount" product="nosdcard" msgid="8966695015677343116">"装载USB存储设备"</string>
    <string name="sd_mount" product="default" msgid="5940523765187704135">"安装SD卡"</string>
    <string name="sd_mount_summary" product="nosdcard" msgid="4673411327373419641"></string>
    <string name="sd_mount_summary" product="default" msgid="4673411327373419641"></string>
    <string name="sd_format" product="nosdcard" msgid="2148179271623099054">"格式化USB存储设备"</string>
    <string name="sd_format" product="default" msgid="2576054280507119870">"格式化SD卡"</string>
    <string name="sd_format_summary" product="nosdcard" msgid="6331905044907914603">"清除内部USB存储设备中的全部数据，例如音乐和照片"</string>
    <string name="sd_format_summary" product="default" msgid="212703692181793109">"清除SD卡中的全部数据，例如音乐和照片"</string>
    <string name="memory_clear_cache_title" msgid="5423840272171286191">"是否清除缓存数据？"</string>
    <string name="memory_clear_cache_message" msgid="4550262490807415948">"此操作会清除所有应用的缓存数据。"</string>
    <string name="mtp_ptp_mode_summary" msgid="3710436114807789270">"已开启MTP或PTP功能"</string>
    <string name="dlg_confirm_unmount_title" product="nosdcard" msgid="3077285629197874055">"要卸载USB存储设备吗？"</string>
    <string name="dlg_confirm_unmount_title" product="default" msgid="3634502237262534381">"要卸载SD卡吗？"</string>
    <string name="dlg_confirm_unmount_text" product="nosdcard" msgid="4322636662873269018">"如果卸载该USB存储设备，您当前使用的某些应用会停止运行，并且在您重新装载该设备前可能都无法使用。"</string>
    <string name="dlg_confirm_unmount_text" product="default" msgid="6998379994779187692">"如果卸载该SD卡，您当前使用的某些应用会停止，并且在您重新装载该SD卡前，这些应用可能都无法使用。"</string>
    <string name="dlg_error_unmount_title" product="nosdcard" msgid="4642742385125426529"></string>
    <string name="dlg_error_unmount_title" product="default" msgid="4642742385125426529"></string>
    <string name="dlg_error_unmount_text" product="nosdcard" msgid="9191518889746166147">"无法卸载USB存储设备，请稍后重试。"</string>
    <string name="dlg_error_unmount_text" product="default" msgid="3500976899159848422">"无法卸载SD卡，请稍后重试。"</string>
    <string name="unmount_inform_text" product="nosdcard" msgid="7120241136790744265">"系统将会卸载USB存储设备。"</string>
    <string name="unmount_inform_text" product="default" msgid="1904212716075458402">"系统将会卸载SD卡。"</string>
    <string name="sd_ejecting_title" msgid="8824572198034365468">"正在卸载"</string>
    <string name="sd_ejecting_summary" msgid="2028753069184908491">"正在卸载"</string>
    <string name="storage_low_title" msgid="1388569749716225155">"存储空间不足"</string>
    <string name="storage_low_summary" msgid="7737465774892563129">"某些系统功能（如同步）可能无法正常使用。请尝试删除或取消在本地保存某些内容（如应用数据或媒体内容），以便释放存储空间。"</string>
    <string name="storage_menu_rename" msgid="7141058657592615390">"重命名"</string>
    <string name="storage_menu_mount" msgid="1014683672493425425">"装载"</string>
    <string name="storage_menu_unmount" msgid="681485356885955898">"弹出"</string>
    <string name="storage_menu_format" msgid="7690626079653152152">"格式化"</string>
    <string name="storage_menu_format_public" msgid="7464714208010125682">"格式化为便携式存储设备"</string>
    <string name="storage_menu_format_private" msgid="546017531835902096">"格式化为内部存储设备"</string>
    <string name="storage_menu_migrate" msgid="3969621494238154294">"迁移数据"</string>
    <string name="storage_menu_forget" msgid="6305824238997983426">"取消保存"</string>
    <string name="storage_menu_set_up" msgid="4263294929451685366">"设置"</string>
    <string name="storage_menu_explore" msgid="4637496051816521560">"浏览"</string>
    <string name="storage_menu_free" msgid="6386070442027135427">"释放空间"</string>
    <string name="storage_menu_manage" msgid="5914482953856430780">"管理存储空间"</string>
    <string name="storage_title_usb" msgid="679612779321689418">"USB计算机连接"</string>
    <string name="usb_connection_category" msgid="7805945595165422882">"连接方式"</string>
    <string name="usb_mtp_title" msgid="3399663424394065964">"媒体设备(MTP)"</string>
    <string name="usb_mtp_summary" msgid="4617321473211391236">"让您可以在Windows上传输媒体文件，或在Mac上使用Android文件传输应用来传输文件（请参见www.android.com/filetransfer）"</string>
    <string name="usb_ptp_title" msgid="3852760810622389620">"相机(PTP)"</string>
    <string name="usb_ptp_summary" msgid="7406889433172511530">"可让您使用相机软件传输照片，并在不支持MTP的计算机上传输任何文件"</string>
    <string name="usb_midi_title" msgid="3069990264258413994">"MIDI"</string>
    <string name="usb_midi_summary" msgid="539169474810956358">"让支持 MIDI 的应用通过 USB 与您计算机上的 MIDI 软件搭配使用。"</string>
    <string name="storage_other_users" msgid="808708845102611856">"其他用户"</string>
    <string name="storage_internal_title" msgid="690771193137801021">"内部存储设备"</string>
    <string name="storage_external_title" msgid="3433462910096848696">"便携式存储设备"</string>
    <string name="storage_volume_summary" msgid="7023441974367853372">"已使用 <xliff:g id="USED">%1$s</xliff:g>（共 <xliff:g id="TOTAL">%2$s</xliff:g>）"</string>
    <string name="storage_size_large" msgid="5691585991420946254">"<xliff:g id="NUMBER">^1</xliff:g>"<small><small>" <xliff:g id="UNIT">^2</xliff:g>"</small></small>""</string>
    <string name="storage_volume_used" msgid="1303803057698959872">"（共 <xliff:g id="TOTAL">%1$s</xliff:g>）"</string>
    <string name="storage_volume_used_total" msgid="6113121714019000244">"共使用 <xliff:g id="TOTAL">%1$s</xliff:g>"</string>
    <string name="storage_mount_success" msgid="687641090137253647">"<xliff:g id="NAME">%1$s</xliff:g>已装载"</string>
    <string name="storage_mount_failure" msgid="1042621107954547316">"无法装载<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_unmount_success" msgid="5737203344673441677">"<xliff:g id="NAME">%1$s</xliff:g>已安全弹出"</string>
    <string name="storage_unmount_failure" msgid="5758387106579519489">"无法安全弹出<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_format_success" msgid="3023144070597190555">"已将<xliff:g id="NAME">%1$s</xliff:g>格式化"</string>
    <string name="storage_format_failure" msgid="6032640952779735766">"无法格式化<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="storage_rename_title" msgid="8242663969839491485">"重命名存储设备"</string>
    <string name="storage_dialog_unmounted" msgid="6403320870103261477">"此<xliff:g id="NAME_0">^1</xliff:g>已安全弹出，但仍在卡槽内。\n\n要使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行装载。"</string>
    <string name="storage_dialog_unmountable" msgid="3732209361668282254">"此<xliff:g id="NAME_0">^1</xliff:g>已损坏。\n\n要使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行设置。"</string>
    <string name="storage_dialog_unsupported" msgid="4503128224360482228">"此设备不支持该<xliff:g id="NAME_0">^1</xliff:g>。\n\n要在此设备上使用此<xliff:g id="NAME_1">^1</xliff:g>，您必须先对其进行设置。"</string>
    <string name="storage_internal_format_details" msgid="4018647158382548820">"格式化之后，您就可以在其他设备上使用此<xliff:g id="NAME_0">^1</xliff:g>了。\n\n此<xliff:g id="NAME_1">^1</xliff:g>上的所有数据都将被清空，建议您先将数据备份。\n\n"<b>"备份照片和其他媒体文件"</b>\n"将您的媒体文件移动到此设备上的备用存储设备，或使用 USB 线传输到计算机上。\n\n"<b>"备份应用"</b>\n"存储在此<xliff:g id="NAME_6">^1</xliff:g>上的所有应用都将被卸载，且这些应用的数据也将一并被清空。要保留这些应用，请将它们移到此设备上的备用存储设备。"</string>
    <string name="storage_internal_unmount_details" msgid="3582802571684490057"><b>"当您弹出此<xliff:g id="NAME_0">^1</xliff:g>后，存储在其中的应用将会停止运行，而且存储在其中的媒体文件也将无法使用，除非您重新插入设备。"</b>\n\n"此<xliff:g id="NAME_1">^1</xliff:g>已经过格式化，只能在这台设备上使用，因此在任何其他设备上均将无法使用。"</string>
    <string name="storage_internal_forget_details" msgid="9028875424669047327">"要使用此<xliff:g id="NAME">^1</xliff:g>中包含的应用、照片或数据，请将其重新插入。\n\n另外，如果该存储设备无法使用，您可以选择取消保存此设备。\n\n如果您选择取消保存，那么该设备中包含的所有数据均将永久丢失。\n\n尽管您以后可以重新安装这些应用，但存储在此设备上的这些应用数据将会丢失。"</string>
    <string name="storage_internal_forget_confirm_title" msgid="1370847944388479245">"要取消保存<xliff:g id="NAME">^1</xliff:g>吗？"</string>
    <string name="storage_internal_forget_confirm" msgid="1148446041396902905">"此<xliff:g id="NAME">^1</xliff:g>上存储的所有应用、照片和数据将会永久丢失。"</string>
    <string name="storage_detail_apps" msgid="6141154016753507490">"应用"</string>
    <string name="storage_detail_images" msgid="6950678857740634769">"图片"</string>
    <string name="storage_detail_videos" msgid="2919743464827110953">"视频"</string>
    <string name="storage_detail_audio" msgid="1197685141676483213">"音频"</string>
    <string name="storage_detail_cached" msgid="8547136365247818567">"缓存数据"</string>
    <string name="storage_detail_other" msgid="8404938385075638238">"其他"</string>
    <string name="storage_detail_system" msgid="4629506366064709687">"系统"</string>
    <string name="storage_detail_explore" msgid="7911344011431568294">"浏览<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_detail_dialog_other" msgid="8845766044697204852">"“其他”包括应用所保存的共享文件、通过互联网或蓝牙下载的文件、Android 文件等。\n\n要查看此<xliff:g id="NAME">^1</xliff:g>中的可见内容，请点按“浏览”。"</string>
    <string name="storage_detail_dialog_system" msgid="862835644848361569">"“系统”中包含用于运行 Android <xliff:g id="VERSION">%s</xliff:g> 的文件"</string>
    <string name="storage_detail_dialog_user" msgid="3267254783294197804">"<xliff:g id="USER_0">^1</xliff:g>可能保存了照片、音乐、应用或其他数据（占用了 <xliff:g id="SIZE">^2</xliff:g> 的存储空间）。\n\n要查看详情，请切换至<xliff:g id="USER_1">^1</xliff:g>。"</string>
    <string name="storage_wizard_init_title" msgid="5085400514028585772">"设置您的<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_init_external_title" msgid="4867326438945303598">"用作便携式存储设备"</string>
    <string name="storage_wizard_init_external_summary" msgid="7476105886344565074">"用于在设备之间移动照片和其他媒体文件。"</string>
    <string name="storage_wizard_init_internal_title" msgid="9100613534261408519">"用作内部存储设备"</string>
    <string name="storage_wizard_init_internal_summary" msgid="6240417501036216410">"仅用于存储这台设备上的内容，包括应用和照片。为了防止此存储设备在其他设备上使用，您需要先对其进行格式化。"</string>
    <string name="storage_wizard_format_confirm_title" msgid="2814021794538252546">"格式化为内部存储设备"</string>
    <string name="storage_wizard_format_confirm_body" msgid="4401758710076806509">"<xliff:g id="NAME_0">^1</xliff:g>必须进行格式化，以确保安全。\n\n格式化之后，此<xliff:g id="NAME_1">^1</xliff:g>将只能在这台设备上使用。\n\n"<b>"格式化操作会清空当前存储在<xliff:g id="NAME_2">^1</xliff:g>上的所有数据。"</b>"为避免丢失数据，建议您先将数据备份。"</string>
    <string name="storage_wizard_format_confirm_public_title" msgid="4905690038882041566">"格式化为便携式存储设备"</string>
    <string name="storage_wizard_format_confirm_public_body" msgid="1516932692920060272">"<xliff:g id="NAME_0">^1</xliff:g>必须进行格式化。\n\n"<b>"格式化操作会清空当前存储在<xliff:g id="NAME_1">^1</xliff:g>上的所有数据。"</b>"为避免丢失数据，建议您先将数据备份。"</string>
    <string name="storage_wizard_format_confirm_next" msgid="2774557300531702572">"清空并格式化"</string>
    <string name="storage_wizard_format_progress_title" msgid="6487352396450582292">"正在格式化<xliff:g id="NAME">^1</xliff:g>…"</string>
    <string name="storage_wizard_format_progress_body" msgid="5255269692453900303">"请勿在<xliff:g id="NAME">^1</xliff:g>进行格式化时将其移除。"</string>
    <string name="storage_wizard_migrate_title" msgid="1363078147938160407">"将数据移动到新的存储设备"</string>
    <string name="storage_wizard_migrate_body" msgid="890751699549542345">"您可以将自己的照片、文件和部分应用转移到这个新<xliff:g id="NAME">^1</xliff:g>上。\n\n转移操作大约需要 <xliff:g id="TIME">^2</xliff:g>，完成后将腾出 <xliff:g id="SIZE">^3</xliff:g> 的内部存储空间。在转移过程中，部分应用将无法正常运行。"</string>
    <string name="storage_wizard_migrate_now" msgid="4523444323744239143">"立即移动"</string>
    <string name="storage_wizard_migrate_later" msgid="3173482328116026253">"稍后移动"</string>
    <string name="storage_wizard_migrate_confirm_title" msgid="8564833529613286965">"立即移动数据"</string>
    <string name="storage_wizard_migrate_confirm_body" msgid="4212060581792135962"><b>"该移动操作需要花费大约 <xliff:g id="TIME">^1</xliff:g>，完成后将在<xliff:g id="NAME">^3</xliff:g>上腾出 <xliff:g id="SIZE">^2</xliff:g> 的空间。"</b></string>
    <string name="storage_wizard_migrate_confirm_next" msgid="5509475628423823202">"移动"</string>
    <string name="storage_wizard_migrate_progress_title" msgid="1665479429044202868">"正在移动数据…"</string>
    <string name="storage_wizard_migrate_details" msgid="3709728824651136227">"在移动数据的过程中：\n• 请勿移除相应的<xliff:g id="NAME">^1</xliff:g>。\n• 某些应用将无法正常运行。\n• 需确保设备电量充足。"</string>
    <string name="storage_wizard_ready_title" msgid="6553867088682695655">"您的<xliff:g id="NAME">^1</xliff:g>已经可以使用了"</string>
    <string name="storage_wizard_ready_external_body" msgid="2879508114260597474">"您的<xliff:g id="NAME">^1</xliff:g>已设置完毕，可用于存储照片和其他媒体文件了。"</string>
    <string name="storage_wizard_ready_internal_body" msgid="122532674037860197">"新的<xliff:g id="NAME">^1</xliff:g>可以使用了。\n\n要将照片、文件和应用数据移动到此设备，请转到“设置”&gt;“存储”。"</string>
    <string name="storage_wizard_move_confirm_title" msgid="292782012677890250">"移动<xliff:g id="APP">^1</xliff:g>"</string>
    <string name="storage_wizard_move_confirm_body" msgid="5176432115206478941">"将<xliff:g id="APP">^1</xliff:g>及其相关数据移动到<xliff:g id="NAME_0">^2</xliff:g>仅需几分钟时间。在移动操作完成前，您将无法使用该应用。\n\n请勿在移动过程中移除该<xliff:g id="NAME_1">^2</xliff:g>。"</string>
    <string name="storage_wizard_move_unlock" msgid="1526216561023200694">"您必须解锁用户“<xliff:g id="APP">^1</xliff:g>”，才能移动数据。"</string>
    <string name="storage_wizard_move_progress_title" msgid="4443920302548035674">"正在移动<xliff:g id="APP">^1</xliff:g>…"</string>
    <string name="storage_wizard_move_progress_body" msgid="7802577486578105609">"请勿在移动过程中移除该<xliff:g id="NAME">^1</xliff:g>。\n\n在移动操作完成前，您将无法使用此设备上的<xliff:g id="APP">^2</xliff:g>应用。"</string>
    <string name="storage_wizard_move_progress_cancel" msgid="542047237524588792">"取消移动"</string>
    <string name="storage_wizard_slow_body" msgid="8010127667184768025">"此<xliff:g id="NAME_0">^1</xliff:g>似乎运行缓慢。\n\n您可以继续使用，但应用移动到此处后可能无法流畅运行，而且数据传输可能会花费很长时间。\n\n建议您使用运行速度更快的<xliff:g id="NAME_1">^1</xliff:g>来提升性能。"</string>
    <string name="storage_wizard_init_v2_title" msgid="8858395869710288372">"您要如何使用此<xliff:g id="NAME">^1</xliff:g>？"</string>
    <string name="storage_wizard_init_v2_internal_title" product="tablet" msgid="4315585580670552654">"用作额外的平板电脑存储空间"</string>
    <string name="storage_wizard_init_v2_internal_summary" product="tablet" msgid="570443086512059390">"仅限此平板电脑上的应用、文件和媒体"</string>
    <string name="storage_wizard_init_v2_internal_action" product="tablet" msgid="7760758592993284143">"平板电脑存储空间"</string>
    <string name="storage_wizard_init_v2_internal_title" product="default" msgid="8373070138732653456">"用作额外的手机存储空间"</string>
    <string name="storage_wizard_init_v2_internal_summary" product="default" msgid="685194340141573218">"仅限此手机上的应用、文件和媒体"</string>
    <string name="storage_wizard_init_v2_internal_action" product="default" msgid="904425171564310150">"手机存储空间"</string>
    <string name="storage_wizard_init_v2_or" msgid="1958295749349454436">"或"</string>
    <string name="storage_wizard_init_v2_external_title" msgid="3565348221712759463">"用作便携式存储设备"</string>
    <string name="storage_wizard_init_v2_external_summary" msgid="801198071793584445">"可用于在设备之间转移文件和媒体"</string>
    <string name="storage_wizard_init_v2_external_action" msgid="8662451480642784031">"便携式存储设备"</string>
    <string name="storage_wizard_init_v2_later" msgid="1080613420170749130">"稍后设置"</string>
    <string name="storage_wizard_format_confirm_v2_title" msgid="5744790239994621663">"要将此<xliff:g id="NAME">^1</xliff:g>格式化吗？"</string>
    <string name="storage_wizard_format_confirm_v2_body" msgid="4614199613500900975">"此<xliff:g id="NAME_0">^1</xliff:g>需要进行格式化才能存储应用、文件和媒体。\n\n格式化操作将会清空<xliff:g id="NAME_1">^2</xliff:g>上的现有内容。为避免内容丢失，请将内容备份到另一个<xliff:g id="NAME_2">^3</xliff:g>或设备中。"</string>
    <string name="storage_wizard_format_confirm_v2_action" msgid="8258363472135537500">"将<xliff:g id="NAME">^1</xliff:g>格式化"</string>
    <string name="storage_wizard_migrate_v2_title" msgid="31406330052996898">"要将内容移至<xliff:g id="NAME">^1</xliff:g>吗？"</string>
    <string name="storage_wizard_migrate_v2_body" product="tablet" msgid="4476553430145054781">"您可以将文件、媒体和特定应用移至此<xliff:g id="NAME">^1</xliff:g>。\n\n这项移动操作将释放 <xliff:g id="SIZE">^2</xliff:g> 的平板电脑存储空间，大约需要 <xliff:g id="DURATION">^3</xliff:g>的时间完成。"</string>
    <string name="storage_wizard_migrate_v2_body" product="default" msgid="744760728669284385">"您可以将文件、媒体和特定应用移至此<xliff:g id="NAME">^1</xliff:g>。\n\n这项移动操作将释放 <xliff:g id="SIZE">^2</xliff:g> 的手机存储空间，大约需要 <xliff:g id="DURATION">^3</xliff:g>的时间完成。"</string>
    <string name="storage_wizard_migrate_v2_checklist" msgid="1239103359606165360">"移动期间："</string>
    <string name="storage_wizard_migrate_v2_checklist_media" msgid="7176991995007075843">"请勿移除<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_migrate_v2_checklist_apps" msgid="3671266712947078734">"部分应用将无法运行"</string>
    <string name="storage_wizard_migrate_v2_checklist_battery" product="tablet" msgid="346012901366624052">"请将此平板电脑保持在充电状态"</string>
    <string name="storage_wizard_migrate_v2_checklist_battery" product="default" msgid="3061158350109289521">"请将此手机保持在充电状态"</string>
    <string name="storage_wizard_migrate_v2_now" msgid="1279041707982039591">"移动内容"</string>
    <string name="storage_wizard_migrate_v2_later" msgid="8201360307047198853">"稍后再移动内容"</string>
    <string name="storage_wizard_migrate_progress_v2_title" msgid="1323344099111423785">"正在移动内容…"</string>
    <string name="storage_wizard_slow_v2_title" msgid="1318285829973607727">"<xliff:g id="NAME">^1</xliff:g>运行缓慢"</string>
    <string name="storage_wizard_slow_v2_body" msgid="6897444467730463117">"您仍可使用此<xliff:g id="NAME_0">^1</xliff:g>，但它的运行速度可能会很慢。\n\n此<xliff:g id="NAME_1">^2</xliff:g>上存储的应用可能无法正常运行，而且内容转移操作可能会花费很长时间。\n\n建议您使用运行速度更快的<xliff:g id="NAME_2">^3</xliff:g>，或者使用此<xliff:g id="NAME_3">^4</xliff:g>作为便携式存储设备。"</string>
    <string name="storage_wizard_slow_v2_start_over" msgid="4126873669723115805">"重新开始"</string>
    <string name="storage_wizard_slow_v2_continue" msgid="49399942893518218">"继续"</string>
    <string name="storage_wizard_ready_v2_external_body" msgid="11937346870534608">"您可以将内容移至<xliff:g id="NAME">^1</xliff:g>"</string>
    <string name="storage_wizard_ready_v2_internal_body" msgid="4658433206901211269">"要将内容移至<xliff:g id="NAME">^1</xliff:g>，请依次转到"<b>"“设置”&gt;“存储”"</b></string>
    <string name="storage_wizard_ready_v2_internal_moved_body" msgid="6239886818487538806">"您的内容已移至<xliff:g id="NAME_0">^1</xliff:g>。\n\n要管理此<xliff:g id="NAME_1">^2</xliff:g>，请依次转到"<b>"“设置”&gt;“存储”"</b>"。"</string>
    <string name="battery_status_title" msgid="9159414319574976203">"电池状态"</string>
    <string name="battery_level_title" msgid="2965679202786873272">"电池电量"</string>
    <string name="apn_settings" msgid="3743170484827528406">"APN"</string>
    <string name="apn_edit" msgid="1354715499708424718">"修改接入点"</string>
    <string name="apn_not_set" msgid="4974192007399968164">"未设置"</string>
    <string name="apn_name" msgid="4115580098369824123">"名称"</string>
    <string name="apn_apn" msgid="2479425126733513353">"APN"</string>
    <string name="apn_http_proxy" msgid="1826885957243696354">"代理"</string>
    <string name="apn_http_port" msgid="3763259523984976226">"端口"</string>
    <string name="apn_user" msgid="455637547356117761">"用户名"</string>
    <string name="apn_password" msgid="5412301994998250968">"密码"</string>
    <string name="apn_server" msgid="2436185314756372858">"服务器"</string>
    <string name="apn_mmsc" msgid="3670124402105585737">"MMSC"</string>
    <string name="apn_mms_proxy" msgid="5374082621073999275">"彩信代理"</string>
    <string name="apn_mms_port" msgid="4074188088199243040">"彩信端口"</string>
    <string name="apn_mcc" msgid="4258628382260674636">"MCC"</string>
    <string name="apn_mnc" msgid="8629374076888809874">"MNC"</string>
    <string name="apn_auth_type" msgid="6167205395676037015">"身份验证类型"</string>
    <string name="apn_auth_type_none" msgid="5069592676845549926">"无"</string>
    <string name="apn_auth_type_pap" msgid="1666934536996033383">"PAP"</string>
    <string name="apn_auth_type_chap" msgid="3369626283789068360">"CHAP"</string>
    <string name="apn_auth_type_pap_chap" msgid="9102343063036134541">"PAP 或 CHAP"</string>
    <string name="apn_type" msgid="469613123902220544">"APN 类型"</string>
    <string name="apn_protocol" msgid="3272222921649348640">"APN 协议"</string>
    <string name="apn_roaming_protocol" msgid="3386954381510788422">"APN 漫游协议"</string>
    <string name="carrier_enabled" msgid="407655861175280806">"APN 启用/停用"</string>
    <string name="carrier_enabled_summaryOn" msgid="6338915271908057531">"APN 已启用"</string>
    <string name="carrier_enabled_summaryOff" msgid="4300790190221203756">"未启用 APN"</string>
    <string name="bearer" msgid="594270280031923558">"承载系统"</string>
    <string name="mvno_type" msgid="2543253857818336421">"MVNO 类型"</string>
    <string name="mvno_match_data" msgid="4560671695220540466">"MVNO 值"</string>
    <string name="menu_delete" msgid="6981294422841124659">"删除 APN"</string>
    <string name="menu_new" msgid="3014205883303921729">"新建 APN"</string>
    <string name="menu_save" msgid="8109345640668285399">"保存"</string>
    <string name="menu_cancel" msgid="2194502410474697474">"放弃"</string>
    <string name="error_title" msgid="7631322303341024692"></string>
    <string name="error_name_empty" msgid="5508155943840201232">"“名称”字段不能为空。"</string>
    <string name="error_apn_empty" msgid="4932211013600863642">"APN 不能为空。"</string>
    <string name="error_mcc_not3" msgid="4560171714156251661">"MCC 字段必须为 3 位数。"</string>
    <string name="error_mnc_not23" msgid="8418177072458379439">"MNC 字段必须为 2 位数或 3 位数。"</string>
    <string name="error_adding_apn_type" msgid="4181334016628549645">"运营商不允许添加“%s”类型的 APN。"</string>
    <string name="restore_default_apn" msgid="8178010218751639581">"正在恢复默认 APN 设置。"</string>
    <string name="menu_restore" msgid="8260067415075573273">"重置为默认设置"</string>
    <string name="restore_default_apn_completed" msgid="2824775307377604897">"已重置默认APN设置。"</string>
    <string name="reset_dashboard_title" msgid="6254873816990678620">"重置选项"</string>
    <string name="reset_dashboard_summary" msgid="4851012632493522755">"网络、应用或设备可以重置"</string>
    <string name="reset_network_title" msgid="6166025966016873843">"重置 WLAN、移动数据网络和蓝牙设置"</string>
    <string name="reset_network_desc" msgid="5547979398298881406">"此操作会重置所有网络设置，包括：\n\n"<li>"WLAN"</li>\n<li>"移动数据网络"</li>\n<li>"蓝牙"</li></string>
    <string name="reset_esim_title" msgid="2419812515540592802">"一并重置 eSIM 卡"</string>
    <string name="reset_esim_desc" msgid="6412324670559060446">"清空手机上的所有 eSIM 卡。您必须与运营商联系才能重新下载 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="reset_network_button_text" msgid="2035676527471089853">"重置设置"</string>
    <string name="reset_network_final_desc" msgid="6388371121099245116">"要重置所有网络设置吗？此操作无法撤消！"</string>
    <string name="reset_network_final_button_text" msgid="1797434793741744635">"重置设置"</string>
    <string name="reset_network_confirm_title" msgid="1759888886976962773">"要重置网络设置吗？"</string>
    <string name="network_reset_not_available" msgid="7188610385577164676">"此用户无权重置网络设置"</string>
    <string name="reset_network_complete_toast" msgid="787829973559541880">"网络设置已重置"</string>
    <string name="reset_esim_error_title" msgid="1464195710538232590">"无法重置 eSIM 卡"</string>
    <string name="reset_esim_error_msg" msgid="8434956817922668388">"出现错误，无法重置 eSIM 卡。"</string>
    <string name="master_clear_title" msgid="3531267871084279512">"清除所有数据（恢复出厂设置）"</string>
    <string name="master_clear_short_title" msgid="8652450915870274285">"清除所有数据（恢复出厂设置）"</string>
    <string name="master_clear_desc" product="tablet" msgid="9146059417023157222">"此操作会清除您平板电脑"<b>"内部存储设备"</b>"中的所有数据，包括：\n\n"<li>"您的Google帐号"</li>\n<li>"系统和应用的数据和设置"</li>\n<li>"下载的应用"</li></string>
    <string name="master_clear_desc" product="default" msgid="4800386183314202571">"此操作会清除您手机"<b>"内部存储空间"</b>"中的所有数据，包括：\n\n"<li>"您的 Google 帐号"</li>\n<li>"系统和应用的数据和设置"</li>\n<li>"已下载的应用"</li></string>
    <string name="master_clear_accounts" product="default" msgid="6412857499147999073">\n\n"目前，您已登录以下帐号：\n"</string>
    <string name="master_clear_other_users_present" product="default" msgid="5161423070702470742">\n\n"此设备上目前还有其他用户。\n"</string>
    <string name="master_clear_desc_also_erases_external" msgid="1903185203791274237"><li>"音乐"</li>\n<li>"照片"</li>\n<li>"其他的用户数据"</li></string>
    <string name="master_clear_desc_also_erases_esim" msgid="6008213558725767177"><li>"eSIM 卡"</li></string>
    <string name="master_clear_desc_no_cancel_mobile_plan" msgid="5460926449093211144">\n\n"这样并不会取消您的移动服务套餐。"</string>
    <string name="master_clear_desc_erase_external_storage" product="nosdcard" msgid="7744115866662613411">\n\n"要清除音乐、照片和其他用户数据，请清空该 "<b>"USB存储设备"</b>"。"</string>
    <string name="master_clear_desc_erase_external_storage" product="default" msgid="4801026652617377093">\n\n"要清除音乐、图片和其他用户数据，您需要清空该 "<b>"SD卡"</b>"。"</string>
    <string name="erase_external_storage" product="nosdcard" msgid="969364037450286809">"格式化USB存储设备"</string>
    <string name="erase_external_storage" product="default" msgid="1397239046334307625">"格式化SD卡"</string>
    <string name="erase_external_storage_description" product="nosdcard" msgid="4728558173931599429">"清除该内部USB存储设备中的全部数据，例如音乐或照片"</string>
    <string name="erase_external_storage_description" product="default" msgid="1737638779582964966">"清除SD卡中的全部数据，例如音乐或照片"</string>
    <string name="erase_esim_storage" msgid="5684858600215441932">"清空 eSIM 卡"</string>
    <string name="erase_esim_storage_description" product="default" msgid="708691303677321598">"清空手机上的所有 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="erase_esim_storage_description" product="tablet" msgid="1780953956941209107">"清空平板电脑上的所有 eSIM 卡。此操作并不会取消您的移动服务套餐。"</string>
    <string name="master_clear_button_text" product="tablet" msgid="3130786116528304116">"恢复平板电脑出厂设置"</string>
    <string name="master_clear_button_text" product="default" msgid="7550632653343157971">"恢复手机出厂设置"</string>
    <string name="master_clear_final_desc" msgid="7318683914280403086">"要清空您的所有个人信息和下载的应用吗？此操作无法撤消！"</string>
    <string name="master_clear_final_button_text" msgid="5390908019019242910">"清除全部内容"</string>
    <string name="master_clear_failed" msgid="2503230016394586353">"“系统清除”服务不可用，因此未执行恢复出厂设置操作。"</string>
    <string name="master_clear_confirm_title" msgid="7572642091599403668">"要恢复出厂设置吗？"</string>
    <string name="master_clear_not_available" msgid="1000370707967468909">"此用户无权恢复出厂设置"</string>
    <string name="master_clear_progress_title" msgid="5194793778701994634">"正在清除"</string>
    <string name="master_clear_progress_text" msgid="6559096229480527510">"请稍候…"</string>
    <string name="call_settings_title" msgid="5188713413939232801">"通话设置"</string>
    <string name="call_settings_summary" msgid="7291195704801002886">"设置语音信箱、来电转接、来电等待和本机号码显示"</string>
    <string name="tether_settings_title_usb" msgid="6688416425801386511">"USB 网络共享"</string>
    <string name="tether_settings_title_wifi" msgid="3277144155960302049">"便携式热点"</string>
    <string name="tether_settings_title_bluetooth" msgid="355855408317564420">"蓝牙网络共享"</string>
    <string name="tether_settings_title_usb_bluetooth" msgid="5355828977109785001">"网络共享"</string>
    <string name="tether_settings_title_all" msgid="3058586928118801157">"热点和网络共享"</string>
    <string name="tether_settings_summary_hotspot_on_tether_on" msgid="930464462687425777">"已开启热点、网络共享"</string>
    <string name="tether_settings_summary_hotspot_on_tether_off" msgid="3473671453891735907">"已开启热点"</string>
    <string name="tether_settings_summary_hotspot_off_tether_on" msgid="1618256180720077354">"网络共享"</string>
    <string name="tether_settings_disabled_on_data_saver" msgid="1576908608463904152">"当流量节省程序开启时，无法使用网络共享功能或便携式热点"</string>
    <string name="usb_title" msgid="7483344855356312510">"USB"</string>
    <string name="usb_tethering_button_text" msgid="585829947108007917">"USB 网络共享"</string>
    <string name="usb_tethering_subtext" product="default" msgid="3711893746716442706">"通过 USB 共享手机的互联网连接"</string>
    <string name="usb_tethering_subtext" product="tablet" msgid="2292916486612255069">"通过 USB 共享平板电脑的互联网连接"</string>
    <string name="bluetooth_tether_checkbox_text" msgid="2379175828878753652">"蓝牙网络共享"</string>
    <string name="bluetooth_tethering_subtext" product="tablet" msgid="8828883800511737077">"通过蓝牙共享平板电脑的互联网连接"</string>
    <string name="bluetooth_tethering_subtext" product="default" msgid="1904667146601254812">"通过蓝牙共享手机的互联网连接"</string>
    <string name="bluetooth_tethering_off_subtext_config" msgid="376389105752995580">"通过蓝牙共享该<xliff:g id="DEVICE_NAME">%1$d</xliff:g>的互联网连接"</string>
    <string name="bluetooth_tethering_overflow_error" msgid="2135590598511178690">"无法与 <xliff:g id="MAXCONNECTION">%1$d</xliff:g> 台以上的设备共享网络。"</string>
    <string name="bluetooth_untether_blank" msgid="2871192409329334813">"即将断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的网络共享。"</string>
    <string name="tethering_footer_info" msgid="7112228674056306147">"使用热点和网络共享功能，让其他设备能通过您的移动数据网络连接到互联网。应用还可以通过创建热点，与附近的设备共享内容。"</string>
    <string name="tethering_help_button_text" msgid="656117495547173630">"帮助"</string>
    <string name="network_settings_title" msgid="2876509814832830757">"移动网络"</string>
    <string name="manage_mobile_plan_title" msgid="7630170375010107744">"手机套餐"</string>
    <string name="sms_application_title" msgid="4903928270533250448">"短信应用"</string>
    <string name="sms_change_default_dialog_title" msgid="1958688831875804286">"要更改短信应用吗？"</string>
    <string name="sms_change_default_dialog_text" msgid="1522783933230274787">"要使用“<xliff:g id="NEW_APP">%1$s</xliff:g>”取代“<xliff:g id="CURRENT_APP">%2$s</xliff:g>”作为您的短信应用吗？"</string>
    <string name="sms_change_default_no_previous_dialog_text" msgid="602683880284921998">"要使用“<xliff:g id="NEW_APP">%s</xliff:g>”作为您的短信应用吗？"</string>
    <string name="network_scorer_picker_title" msgid="6383879578279046456">"网络评分服务提供方"</string>
    <string name="network_scorer_picker_none_preference" msgid="9028375117241790936">"无"</string>
    <string name="network_scorer_change_active_dialog_title" msgid="3776301550387574975">"要更改WLAN助手吗？"</string>
    <string name="network_scorer_change_active_dialog_text" msgid="8035173880322990715">"不再使用<xliff:g id="CURRENT_APP">%2$s</xliff:g>，而改用<xliff:g id="NEW_APP">%1$s</xliff:g>来管理您的网络连接吗？"</string>
    <string name="network_scorer_change_active_no_previous_dialog_text" msgid="7444620909047611601">"要使用<xliff:g id="NEW_APP">%s</xliff:g>管理您的网络连接吗？"</string>
    <string name="mobile_unknown_sim_operator" msgid="2156912373230276157">"未知的SIM卡运营商"</string>
    <string name="mobile_no_provisioning_url" msgid="9053814051811634125">"<xliff:g id="OPERATOR">%1$s</xliff:g>没有任何已知的配置网站"</string>
    <string name="mobile_insert_sim_card" msgid="9052590985784056395">"请插入SIM卡，然后重新启动"</string>
    <string name="mobile_connect_to_internet" msgid="1733894125065249639">"请连接到互联网"</string>
    <string name="location_title" msgid="1029961368397484576">"我的位置"</string>
    <string name="managed_profile_location_switch_title" msgid="6712332547063039683">"工作资料位置信息"</string>
    <string name="location_app_level_permissions" msgid="1825588230817081339">"应用级权限"</string>
    <string name="location_category_recent_location_requests" msgid="1938721350424447421">"最近的位置信息请求"</string>
    <string name="location_recent_location_requests_see_all" msgid="9063541547120162593">"查看全部"</string>
    <string name="location_no_recent_apps" msgid="2800907699722178041">"最近没有任何应用申请使用位置信息"</string>
    <string name="location_category_location_services" msgid="7437150886946685979">"位置信息服务"</string>
    <string name="location_high_battery_use" msgid="517199943258508020">"高电耗"</string>
    <string name="location_low_battery_use" msgid="8602232529541903596">"低电耗"</string>
    <string name="location_scanning_screen_title" msgid="4408076862929611554">"扫描"</string>
    <string name="location_scanning_wifi_always_scanning_title" msgid="6216705505621183645">"WLAN 扫描"</string>
    <string name="location_scanning_wifi_always_scanning_description" msgid="2691110218127379249">"允许应用和服务随时扫描 WLAN 网络（即使 WLAN 已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="location_scanning_bluetooth_always_scanning_title" msgid="5444989508204520019">"蓝牙扫描"</string>
    <string name="location_scanning_bluetooth_always_scanning_description" msgid="1285526059945206128">"允许应用和服务随时扫描附近的设备（即使蓝牙已关闭）。这可用于改进基于位置的功能和服务。"</string>
    <string name="location_network_based" msgid="9134175479520582215">"WLAN和移动网络位置信息"</string>
    <string name="location_neighborhood_level" msgid="5141318121229984788">"允许应用使用Google位置信息服务更快地大致了解您所在的位置。系统将收集匿名位置数据并将其发送给Google。"</string>
    <string name="location_neighborhood_level_wifi" msgid="4234820941954812210">"通过WLAN确定位置"</string>
    <string name="location_gps" msgid="8392461023569708478">"GPS卫星定位"</string>
    <string name="location_street_level" product="tablet" msgid="1669562198260860802">"允许应用使用您平板电脑上的GPS定位您的位置"</string>
    <string name="location_street_level" product="default" msgid="4617445745492014203">"允许应用使用您手机上的GPS定位您的位置"</string>
    <string name="assisted_gps" msgid="4649317129586736885">"使用增强型GPS"</string>
    <string name="assisted_gps_enabled" msgid="8751899609589792803">"使用服务器来辅助GPS（取消选中可降低网络使用率）"</string>
    <string name="assisted_gps_disabled" msgid="6982698333968010748">"使用服务器来辅助GPS（取消选中可提高GPS性能）"</string>
    <string name="use_location_title" msgid="5206937465504979977">"位置信息和Google搜索"</string>
    <string name="use_location_summary" msgid="3978805802386162520">"允许Google使用您的位置信息改善搜索结果和其他服务"</string>
    <string name="location_access_title" msgid="7064108942964081243">"允许使用我的位置信息"</string>
    <string name="location_access_summary" msgid="69031404093194341">"允许得到您许可的应用使用您的位置信息"</string>
    <string name="location_sources_heading" msgid="1278732419851088319">"位置信息来源"</string>
    <string name="about_settings" product="tablet" msgid="593457295516533765">"关于平板电脑"</string>
    <string name="about_settings" product="default" msgid="1743378368185371685">"关于手机"</string>
    <string name="about_settings" product="device" msgid="6717640957897546887">"关于设备"</string>
    <string name="about_settings" product="emulator" msgid="221313099578564438">"关于模拟设备"</string>
    <string name="about_settings_summary" msgid="3371517697156165959">"查看法律信息、状态和软件版本"</string>
    <string name="legal_information" msgid="5769301644270604095">"法律信息"</string>
    <string name="contributors_title" msgid="5917703088825286504">"活动提供商"</string>
    <string name="manual" msgid="3025943393642974445">"手册"</string>
    <string name="regulatory_labels" msgid="1293050314122427492">"监管标签"</string>
    <string name="safety_and_regulatory_info" msgid="5103161279848427185">"安全和监管手册"</string>
    <string name="copyright_title" msgid="865906688917260647">"版权"</string>
    <string name="license_title" msgid="1990487604356037871">"许可"</string>
    <string name="terms_title" msgid="7697580845616764642">"条款"</string>
    <string name="webview_license_title" msgid="2813507464175738967">"系统 WebView 许可"</string>
    <string name="wallpaper_attributions" msgid="3645880512943433928">"壁纸"</string>
    <string name="wallpaper_attributions_values" msgid="2996183537914690469">"卫星图像提供商：\n©2014 CNES / Astrium, DigitalGlobe, Bluesky"</string>
    <string name="settings_manual_activity_title" msgid="8133150693616006051">"手册"</string>
    <string name="settings_manual_activity_unavailable" msgid="4752403782883814898">"加载手册时出现问题。"</string>
    <string name="settings_license_activity_title" msgid="8525014571806471216">"第三方许可"</string>
    <string name="settings_license_activity_unavailable" msgid="4210539215951487627">"加载许可时出现问题。"</string>
    <string name="settings_license_activity_loading" msgid="3337535809093591740">"正在加载..."</string>
    <string name="settings_safetylegal_title" msgid="1289483965535937431">"安全信息"</string>
    <string name="settings_safetylegal_activity_title" msgid="6901214628496951727">"安全信息"</string>
    <string name="settings_safetylegal_activity_unreachable" msgid="142307697309858185">"您没有数据连接。要立即查看此信息，请使用任何联网的计算机访问%s。"</string>
    <string name="settings_safetylegal_activity_loading" msgid="8059022597639516348">"正在加载..."</string>
    <string name="lockpassword_choose_your_screen_lock_header" msgid="2942199737559900752">"设置屏幕锁定"</string>
    <string name="lockpassword_choose_your_password_message" msgid="5377842480961577542">"为了安全起见，请设置密码"</string>
    <string name="lockpassword_choose_your_password_header_for_fingerprint" msgid="6624409510609085450">"要使用指纹，请设置密码"</string>
    <string name="lockpassword_choose_your_pattern_header_for_fingerprint" msgid="5901096361617543819">"要使用指纹，请设置解锁图案"</string>
    <string name="lockpassword_choose_your_pin_message" msgid="6658264750811929338">"为了安全起见，请设置 PIN 码"</string>
    <string name="lockpassword_choose_your_pin_header_for_fingerprint" msgid="765344692615917183">"要使用指纹，请设置 PIN 码"</string>
    <string name="lockpassword_choose_your_pattern_message" msgid="8631545254345759087">"为了安全起见，请设置解锁图案"</string>
    <string name="lockpassword_confirm_your_password_header" msgid="1266027268220850931">"重新输入密码"</string>
    <string name="lockpassword_confirm_your_pattern_header" msgid="7543433733032330821">"确认您的图案"</string>
    <string name="lockpassword_confirm_your_pin_header" msgid="7744513791910572550">"重新输入 PIN 码"</string>
    <string name="lockpassword_confirm_passwords_dont_match" msgid="5140892109439191415">"密码不匹配"</string>
    <string name="lockpassword_confirm_pins_dont_match" msgid="7226244811505606217">"PIN码不匹配！"</string>
    <string name="lockpassword_draw_your_pattern_again_header" msgid="2872194349688886781">"再次绘制图案"</string>
    <string name="lockpassword_choose_lock_generic_header" msgid="3811438094903786145">"选择解锁方式"</string>
    <string name="lockpassword_password_set_toast" msgid="4875050283108629383">"密码已设置"</string>
    <string name="lockpassword_pin_set_toast" msgid="6011826444725291475">"已设置PIN码"</string>
    <string name="lockpassword_pattern_set_toast" msgid="6867259621331406236">"图案已设置"</string>
    <string name="lockpassword_confirm_your_pattern_generic" msgid="2920960858283879113">"请绘制您的设备解锁图案以继续"</string>
    <string name="lockpassword_confirm_your_pin_generic" msgid="4062335874438910487">"请输入您的设备 PIN 码以继续"</string>
    <string name="lockpassword_confirm_your_password_generic" msgid="3976394862548354966">"请输入您的设备密码以继续"</string>
    <string name="lockpassword_confirm_your_pattern_generic_profile" msgid="4435638308193361861">"请绘制您的工作解锁图案以继续"</string>
    <string name="lockpassword_confirm_your_pin_generic_profile" msgid="3730141667547002246">"请输入您的工作 PIN 码以继续"</string>
    <string name="lockpassword_confirm_your_password_generic_profile" msgid="4250642723467019894">"请输入您的工作密码以继续"</string>
    <string name="lockpassword_strong_auth_required_device_pattern" msgid="530802132223800623">"为了提升安全性，请绘制您的设备解锁图案"</string>
    <string name="lockpassword_strong_auth_required_device_pin" msgid="7829294830078036417">"为了提升安全性，请输入您的设备 PIN 码"</string>
    <string name="lockpassword_strong_auth_required_device_password" msgid="3552644641574796973">"为了提升安全性，请输入您的设备密码"</string>
    <string name="lockpassword_strong_auth_required_work_pattern" msgid="3003781907040522053">"为了提升安全性，请绘制您的工作资料解锁图案"</string>
    <string name="lockpassword_strong_auth_required_work_pin" msgid="3367491332598821552">"为了提升安全性，请输入您的工作资料 PIN 码"</string>
    <string name="lockpassword_strong_auth_required_work_password" msgid="8159775129968582940">"为了提升安全性，请输入您的工作资料密码"</string>
    <string name="lockpassword_confirm_your_pattern_details_frp" msgid="6757336656791723193">"您的手机已恢复出厂设置。要使用此手机，请输入您以前的解锁图案。"</string>
    <string name="lockpassword_confirm_your_pin_details_frp" msgid="826520613445990470">"您的手机已恢复出厂设置。要使用此手机，请输入您以前的 PIN 码。"</string>
    <string name="lockpassword_confirm_your_password_details_frp" msgid="8944081074615739040">"您的手机已恢复出厂设置。要使用此手机，请输入您以前的密码。"</string>
    <string name="lockpassword_confirm_your_pattern_header_frp" msgid="2898036091609128286">"验证图案"</string>
    <string name="lockpassword_confirm_your_pin_header_frp" msgid="4141601774778898803">"验证 PIN 码"</string>
    <string name="lockpassword_confirm_your_password_header_frp" msgid="3762615419295360480">"验证密码"</string>
    <string name="lockpassword_invalid_pin" msgid="15588049067548470">"PIN 码错误"</string>
    <string name="lockpassword_invalid_password" msgid="4038507398784975200">"密码错误"</string>
    <string name="lockpattern_need_to_unlock_wrong" msgid="1745247595356012176">"图案错误"</string>
    <string name="lock_settings_title" msgid="4213839087748988686">"设备安全性"</string>
    <string name="lockpattern_change_lock_pattern_label" msgid="5679630792003440352">"更改解锁图案"</string>
    <string name="lockpattern_change_lock_pin_label" msgid="266707138486731661">"更改解锁PIN码"</string>
    <string name="lockpattern_recording_intro_header" msgid="308287052221942814">"绘制解锁图案"</string>
    <string name="lockpattern_recording_intro_footer" msgid="1118579101409152113">"按 MENU 获得帮助。"</string>
    <string name="lockpattern_recording_inprogress" msgid="6667844062721656773">"完成后松开手指"</string>
    <string name="lockpattern_recording_incorrect_too_short" msgid="1348234155120957561">"至少需连接<xliff:g id="NUMBER">%d</xliff:g>个点，请重试。"</string>
    <string name="lockpattern_pattern_entered_header" msgid="4316818983675591604">"图案已记录"</string>
    <string name="lockpattern_need_to_confirm" msgid="8054853451639221265">"再次绘制图案进行确认"</string>
    <string name="lockpattern_pattern_confirmed_header" msgid="8455614172231880211">"您的新解锁图案"</string>
    <string name="lockpattern_confirm_button_text" msgid="1128204343957002841">"确认"</string>
    <string name="lockpattern_restart_button_text" msgid="3337574403350953926">"重新绘制"</string>
    <string name="lockpattern_retry_button_text" msgid="3480423193273588166">"清除"</string>
    <string name="lockpattern_continue_button_text" msgid="4723771754714471410">"继续"</string>
    <string name="lockpattern_settings_title" msgid="3207750489460466680">"解锁图案"</string>
    <string name="lockpattern_settings_enable_title" msgid="6920616873671115281">"需要解锁图案"</string>
    <string name="lockpattern_settings_enable_summary" msgid="1165707416664252167">"必须绘制图案才能解锁屏幕"</string>
    <string name="lockpattern_settings_enable_visible_pattern_title" msgid="2615606088906120711">"显示图案"</string>
    <string name="lockpattern_settings_enable_visible_pattern_title_profile" msgid="4864525074768391381">"显示资料解锁图案"</string>
    <string name="lockpattern_settings_enable_tactile_feedback_title" msgid="4389015658335522989">"点按时振动"</string>
    <string name="lockpattern_settings_enable_power_button_instantly_locks" msgid="5735444062633666327">"电源按钮即时锁定"</string>
    <string name="lockpattern_settings_power_button_instantly_locks_summary" msgid="8196258755143711694">"<xliff:g id="TRUST_AGENT_NAME">%1$s</xliff:g>让屏幕保持解锁状态时除外"</string>
    <string name="lockpattern_settings_choose_lock_pattern" msgid="1652352830005653447">"设置解锁图案"</string>
    <string name="lockpattern_settings_change_lock_pattern" msgid="1123908306116495545">"更改解锁图案"</string>
    <string name="lockpattern_settings_help_how_to_record" msgid="2614673439060830433">"如何绘制解锁图案"</string>
    <string name="lockpattern_too_many_failed_confirmation_attempts" msgid="6909161623701848863">"尝试解锁失败次数过多。请在 <xliff:g id="NUMBER">%d</xliff:g> 秒后重试。"</string>
    <string name="activity_not_found" msgid="5551664692991605325">"您的手机上未安装相应应用。"</string>
    <string name="lock_settings_profile_title" msgid="2121876391814535295">"工作资料安全"</string>
    <string name="lock_settings_profile_screen_lock_title" msgid="3334747927367115256">"工作资料屏幕锁定"</string>
    <string name="lock_settings_profile_unification_title" msgid="4973102698492659123">"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_summary" msgid="7178299172998641303">"让工作资料和设备屏幕使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_dialog_title" msgid="4824620230229285301">"要使用同一种锁定方式吗？"</string>
    <string name="lock_settings_profile_unification_dialog_body" msgid="7128305504872026659">"您的设备将使用工作资料屏幕锁定方式。这两种锁定方式都需要符合相关工作规范。"</string>
    <string name="lock_settings_profile_unification_dialog_uncompliant_body" msgid="3221303098797469900">"您的工作资料锁定方式不符合贵单位的安全要求。您可以为自己的设备屏幕和工作资料使用同一种锁定方式，但是必须遵守所有相关的工作屏幕锁定规范。"</string>
    <string name="lock_settings_profile_unification_dialog_confirm" msgid="8249970828159656518">"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unification_dialog_uncompliant_confirm" msgid="5943758576756482777">"使用同一种锁定方式"</string>
    <string name="lock_settings_profile_unified_summary" msgid="9008819078132993492">"与设备的屏幕锁定方式相同"</string>
    <string name="manageapplications_settings_title" msgid="7041951105633616745">"管理应用"</string>
    <string name="manageapplications_settings_summary" msgid="1794401500935451259">"管理和删除已安装的应用"</string>
    <string name="applications_settings" msgid="5281808652705396152">"应用信息"</string>
    <string name="applications_settings_summary" msgid="6683465446264515367">"管理应用、设置快速启动快捷方式"</string>
    <string name="applications_settings_header" msgid="1014813055054356646">"应用设置"</string>
    <string name="install_applications" msgid="4872012136210802181">"未知来源"</string>
    <string name="install_applications_title" msgid="4987712352256508946">"允许所有应用来源"</string>
    <string name="recent_app_category_title" msgid="6673071268966003928">"最近打开的应用"</string>
    <string name="see_all_apps_title" msgid="1317153498074308438">"查看全部 <xliff:g id="COUNT">%1$d</xliff:g> 个应用"</string>
    <string name="install_all_warning" product="tablet" msgid="8310489909586138165">"您的平板电脑和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的平板电脑损坏或数据丢失承担责任。"</string>
    <string name="install_all_warning" product="default" msgid="1952257127370115988">"您的手机和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的手机损坏或数据丢失承担责任。"</string>
    <string name="install_all_warning" product="device" msgid="3648003301476423145">"您的设备和个人数据更容易受到未知应用的攻击。安装来自该来源的应用即表示，您同意对因使用这些应用可能导致的设备损坏或数据丢失问题承担责任。"</string>
    <string name="advanced_settings" msgid="1777249286757067969">"高级设置"</string>
    <string name="advanced_settings_summary" msgid="4016682978071086747">"启用更多设置选项"</string>
    <string name="application_info_label" msgid="5736524913065714880">"应用信息"</string>
    <string name="storage_label" msgid="8700867073480107253">"存储"</string>
    <string name="auto_launch_label" msgid="2669236885531442195">"默认打开"</string>
    <string name="auto_launch_label_generic" msgid="3230569852551968694">"默认操作"</string>
    <string name="screen_compatibility_label" msgid="663250687205465394">"屏幕兼容性"</string>
    <string name="permissions_label" msgid="2605296874922726203">"权限"</string>
    <string name="cache_header_label" msgid="1877197634162461830">"缓存"</string>
    <string name="clear_cache_btn_text" msgid="5756314834291116325">"清除缓存"</string>
    <string name="cache_size_label" msgid="7505481393108282913">"缓存"</string>
    <plurals name="uri_permissions_text" formatted="false" msgid="3983110543017963732">
      <item quantity="other">%d 项</item>
      <item quantity="one">1 项</item>
    </plurals>
    <string name="clear_uri_btn_text" msgid="8575655132961012158">"取消访问权限"</string>
    <string name="controls_label" msgid="7611113077086853799">"控件"</string>
    <string name="force_stop" msgid="7435006169872876756">"强行停止"</string>
    <string name="total_size_label" msgid="1048676419552557254">"总计"</string>
    <string name="application_size_label" msgid="7376689739076506885">"应用大小"</string>
    <string name="external_code_size_label" msgid="3459343140355961335">"USB存储（应用）"</string>
    <string name="data_size_label" msgid="6117971066063850416">"用户数据"</string>
    <string name="external_data_size_label" product="nosdcard" msgid="7533821466482000453">"USB存储（数据）"</string>
    <string name="external_data_size_label" product="default" msgid="626414192825329708">"SD卡"</string>
    <string name="uninstall_text" msgid="3644892466144802466">"卸载"</string>
    <string name="uninstall_all_users_text" msgid="851857393177950340">"为所有用户卸载"</string>
    <string name="install_text" msgid="884360662922471113">"安装"</string>
    <string name="disable_text" msgid="6544054052049395202">"停用"</string>
    <string name="enable_text" msgid="9217362512327828987">"启用"</string>
    <string name="clear_user_data_text" msgid="355574089263023363">"清除存储空间"</string>
    <string name="app_factory_reset" msgid="6635744722502563022">"卸载更新"</string>
    <string name="auto_launch_enable_text" msgid="4275746249511874845">"您已选择默认使用此应用处理某些操作。"</string>
    <string name="always_allow_bind_appwidgets_text" msgid="566822577792032925">"您已选择允许该应用创建微件并查看其数据。"</string>
    <string name="auto_launch_disable_text" msgid="7800385822185540166">"没有默认操作。"</string>
    <string name="clear_activities" msgid="7408923511535174430">"清除默认操作"</string>
    <string name="screen_compatibility_text" msgid="1616155457673106022">"此应用可能不是针对您的屏幕设计的。您可以在此处调整其显示尺寸/比例，让它适合您的屏幕。"</string>
    <string name="ask_compatibility" msgid="7225195569089607846">"启动时确认"</string>
    <string name="enable_compatibility" msgid="5806819252068617811">"调整应用的显示尺寸/比例"</string>
    <string name="unknown" msgid="1592123443519355854">"未知"</string>
    <string name="sort_order_alpha" msgid="1410278099123670628">"按名称排序"</string>
    <string name="sort_order_size" msgid="7024513286636502362">"按大小排序"</string>
    <string name="sort_order_recent_notification" msgid="6064103501358974282">"最新"</string>
    <string name="sort_order_frequent_notification" msgid="1733204081305830670">"最频繁"</string>
    <string name="show_running_services" msgid="5736278767975544570">"显示当前运行的服务"</string>
    <string name="show_background_processes" msgid="2009840211972293429">"显示已缓存的进程"</string>
    <string name="default_emergency_app" msgid="1951760659640369980">"紧急警报应用"</string>
    <string name="reset_app_preferences" msgid="1321050641018356925">"重置应用偏好设置"</string>
    <string name="reset_app_preferences_title" msgid="6093179367325336662">"要重置应用偏好设置吗？"</string>
    <string name="reset_app_preferences_desc" msgid="4822447731869201512">"这将重置以下所有偏好设置：\n\n "<li>"已停用的应用"</li>\n" "<li>"已停用的应用通知"</li>\n" "<li>"用于执行操作的默认应用"</li>\n" "<li>"应用的后台流量限制"</li>\n" "<li>"所有权限限制"</li>\n\n" 您将不会丢失任何应用数据。"</string>
    <string name="reset_app_preferences_button" msgid="2559089511841281242">"重置应用"</string>
    <string name="manage_space_text" msgid="8852711522447794676">"管理空间"</string>
    <string name="filter" msgid="2018011724373033887">"过滤"</string>
    <string name="filter_dlg_title" msgid="8693024463731076091">"选择过滤选项"</string>
    <string name="filter_apps_all" msgid="8899612398848280352">"所有应用"</string>
    <string name="filter_apps_disabled" msgid="5862632369555319938">"已停用的应用"</string>
    <string name="filter_apps_third_party" msgid="7786348047690140979">"已下载"</string>
    <string name="filter_apps_running" msgid="7767071454371350486">"正在运行"</string>
    <string name="filter_apps_onsdcard" product="nosdcard" msgid="4843063154701023349">"USB存储设备"</string>
    <string name="filter_apps_onsdcard" product="default" msgid="1477351142334784771">"SD卡中"</string>
    <string name="not_installed" msgid="2797554494953450291">"未针对此用户安装"</string>
    <string name="installed" msgid="3070865169422600098">"已安装"</string>
    <string name="no_applications" msgid="7336588977497084921">"无应用。"</string>
    <string name="internal_storage" msgid="1584700623164275282">"内部存储空间"</string>
    <string name="internal_storage_sentence" msgid="889098931914857143">"内部存储设备"</string>
    <string name="sd_card_storage" product="nosdcard" msgid="2673203150465132465">"USB存储设备"</string>
    <string name="sd_card_storage" product="default" msgid="7623513618171928235">"SD卡存储设备"</string>
    <string name="recompute_size" msgid="7722567982831691718">"正在重新计算大小..."</string>
    <string name="clear_data_dlg_title" msgid="5605258400134511197">"要删除应用数据吗？"</string>
    <string name="clear_data_dlg_text" msgid="3951297329833822490">"系统会永久删除此应用的所有数据。删除的内容包括所有文件、设置、帐号、数据库等。"</string>
    <string name="dlg_ok" msgid="2402639055725653590">"确定"</string>
    <string name="dlg_cancel" msgid="1674753358972975911">"取消"</string>
    <string name="app_not_found_dlg_title" msgid="3127123411738434964"></string>
    <string name="app_not_found_dlg_text" msgid="4893589904687340011">"在已安装应用的列表中找不到该应用。"</string>
    <string name="clear_failed_dlg_text" msgid="8651231637137025815">"无法清除应用的存储空间。"</string>
    <string name="security_settings_desc" product="tablet" msgid="1292421279262430109">"此应用拥有以下权限："</string>
    <string name="security_settings_desc" product="default" msgid="61749028818785244">"此应用拥有以下权限："</string>
    <string name="security_settings_desc_multi" product="tablet" msgid="7300932212437084403">"此应用可访问您平板电脑上的以下内容。为了提高性能和减少内存使用量，<xliff:g id="BASE_APP_NAME">%1$s</xliff:g>可获得其中的部分权限，因为它与<xliff:g id="ADDITIONAL_APPS_LIST">%2$s</xliff:g>运行在同一进程中。"</string>
    <string name="security_settings_desc_multi" product="default" msgid="6610268420793984752">"此应用可访问您手机上的以下内容。为了提高性能和减少内存使用量，<xliff:g id="BASE_APP_NAME">%1$s</xliff:g>可获得其中的部分权限，因为它与<xliff:g id="ADDITIONAL_APPS_LIST">%2$s</xliff:g>运行在同一进程中。"</string>
    <string name="join_two_items" msgid="1336880355987539064">"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>和<xliff:g id="SECOND_ITEM">%2$s</xliff:g>"</string>
    <string name="join_two_unrelated_items" msgid="1873827777191260824">"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>、<xliff:g id="SECOND_ITEM">%2$s</xliff:g>"</string>
    <string name="join_many_items_last" msgid="218498527304674173">"<xliff:g id="ALL_BUT_LAST_ITEM">%1$s</xliff:g>和<xliff:g id="LAST_ITEM_0">%2$s</xliff:g>"</string>
    <string name="join_many_items_first" msgid="4333907712038448660">"<xliff:g id="FIRST_ITEM">%1$s</xliff:g>、<xliff:g id="ALL_BUT_FIRST_AND_LAST_ITEM">%2$s</xliff:g>"</string>
    <string name="join_many_items_middle" msgid="7556692394478220814">"<xliff:g id="ADDED_ITEM">%1$s</xliff:g>、<xliff:g id="REST_OF_ITEMS">%2$s</xliff:g>"</string>
    <string name="security_settings_billing_desc" msgid="8061019011821282358">"使用此应用可能会产生费用："</string>
    <string name="security_settings_premium_sms_desc" msgid="8734171334263713717">"发送付费短信"</string>
    <string name="computing_size" msgid="1599186977475211186">"正在计算..."</string>
    <string name="invalid_size_value" msgid="1582744272718752951">"无法计算软件包的大小。"</string>
    <string name="empty_list_msg" msgid="3552095537348807772">"未安装任何第三方应用。"</string>
    <string name="version_text" msgid="9189073826278676425">"版本 <xliff:g id="VERSION_NUM">%1$s</xliff:g>"</string>
    <string name="move_app" msgid="5042838441401731346">"移动"</string>
    <string name="move_app_to_internal" product="tablet" msgid="2299714147283854957">"移至平板电脑"</string>
    <string name="move_app_to_internal" product="default" msgid="3895430471913858185">"移至手机"</string>
    <string name="move_app_to_sdcard" product="nosdcard" msgid="4350451696315265420">"移至USB存储设备"</string>
    <string name="move_app_to_sdcard" product="default" msgid="1143379049903056407">"移至SD卡"</string>
    <string name="moving" msgid="6431016143218876491">"正在移动"</string>
    <string name="another_migration_already_in_progress" msgid="7817354268848365487">"系统目前正在执行另一项迁移操作。"</string>
    <string name="insufficient_storage" msgid="481763122991093080">"存储空间不足。"</string>
    <string name="does_not_exist" msgid="1501243985586067053">"应用不存在。"</string>
    <string name="app_forward_locked" msgid="6331564656683790866">"应用受版权保护。"</string>
    <string name="invalid_location" msgid="4354595459063675191">"安装位置无效。"</string>
    <string name="system_package" msgid="1352722848400644991">"无法在外部介质上安装系统更新。"</string>
    <string name="move_error_device_admin" msgid="8673026002690505763">"无法在外部媒体上安装设备管理应用"</string>
    <string name="force_stop_dlg_title" msgid="977530651470711366">"要强行停止吗？"</string>
    <string name="force_stop_dlg_text" msgid="7208364204467835578">"强行停止某个应用可能会导致其出现异常。"</string>
    <string name="move_app_failed_dlg_title" msgid="1282561064082384192"></string>
    <string name="move_app_failed_dlg_text" msgid="187885379493011720">"无法移动应用。<xliff:g id="REASON">%1$s</xliff:g>"</string>
    <string name="app_install_location_title" msgid="2068975150026852168">"首选安装位置"</string>
    <string name="app_install_location_summary" msgid="5155453752692959098">"更改安装新应用时使用的首选安装位置"</string>
    <string name="app_disable_dlg_title" msgid="3916469657537695436">"要停用内置应用吗？"</string>
    <string name="app_disable_dlg_positive" msgid="7375627244201714263">"停用应用"</string>
    <string name="app_disable_dlg_text" msgid="5632072173181990531">"如果您停用此应用，Android 和其他应用可能会无法正常运行。"</string>
    <string name="app_special_disable_dlg_title" msgid="2690148680327142674">"确定要删除数据并停用应用吗？"</string>
    <string name="app_special_disable_dlg_text" msgid="5832078825810635913">"如果您停用此应用，Android 和其他应用可能会无法正常运行。您的数据也将会遭到删除。"</string>
    <string name="app_disable_notifications_dlg_title" msgid="7669264654851761857">"要关闭通知吗？"</string>
    <string name="app_disable_notifications_dlg_text" msgid="5088484670924769845">"如果关闭此应用的通知，您可能会错过重要提醒和最新动态信息。"</string>
    <string name="app_install_details_group_title" msgid="7084623031296083574">"商店"</string>
    <string name="app_install_details_title" msgid="6905279702654975207">"应用详情"</string>
    <string name="app_install_details_summary" msgid="6464796332049327547">"通过<xliff:g id="APP_STORE">%1$s</xliff:g>安装的应用"</string>
    <string name="instant_app_details_summary" msgid="4529934403276907045">"前往 <xliff:g id="APP_STORE">%1$s</xliff:g> 查看详细信息"</string>
    <string name="app_ops_running" msgid="7706949900637284122">"正在运行"</string>
    <string name="app_ops_never_used" msgid="9114608022906887802">"（从未使用）"</string>
    <string name="no_default_apps" msgid="2915315663141025400">"没有任何默认应用。"</string>
    <string name="storageuse_settings_title" msgid="5657014373502630403">"存储空间使用情况"</string>
    <string name="storageuse_settings_summary" msgid="3748286507165697834">"查看应用使用的存储空间"</string>
    <string name="service_restarting" msgid="2242747937372354306">"正在重新启动"</string>
    <string name="cached" msgid="1059590879740175019">"缓存的后台进程"</string>
    <string name="no_running_services" msgid="2059536495597645347">"当前未运行任何服务。"</string>
    <string name="service_started_by_app" msgid="818675099014723551">"由应用启动。"</string>
    <!-- no translation found for service_client_name (4037193625611815517) -->
    <skip />
    <string name="service_background_processes" msgid="6844156253576174488">"可用：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="service_foreground_processes" msgid="7583975676795574276">"已用：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory" msgid="6609961111091483458">"内存"</string>
    <!-- no translation found for service_process_name (4098932168654826656) -->
    <skip />
    <string name="running_process_item_user_label" msgid="3129887865552025943">"用户：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="running_process_item_removed_user_label" msgid="8250168004291472959">"已删除用户"</string>
    <string name="running_processes_item_description_s_s" msgid="5790575965282023145">"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_s_p" msgid="8019860457123222953">"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_p_s" msgid="744424668287252915">"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_item_description_p_p" msgid="1607384595790852782">"<xliff:g id="NUMPROCESS">%1$d</xliff:g>个进程和<xliff:g id="NUMSERVICES">%2$d</xliff:g>个服务"</string>
    <string name="running_processes_header_title" msgid="6588371727640789560">"设备内存"</string>
    <string name="running_processes_header_footer" msgid="723908176275428442">"应用内存使用情况"</string>
    <string name="running_processes_header_system_prefix" msgid="6104153299581682047">"系统"</string>
    <string name="running_processes_header_apps_prefix" msgid="5787594452716832727">"应用"</string>
    <string name="running_processes_header_free_prefix" msgid="4620613031737078415">"可用"</string>
    <string name="running_processes_header_used_prefix" msgid="5924288703085123978">"已用"</string>
    <string name="running_processes_header_cached_prefix" msgid="7950853188089434987">"缓存"</string>
    <string name="running_processes_header_ram" msgid="996092388884426817">"<xliff:g id="RAM_0">%1$s</xliff:g>"</string>
    <string name="runningservicedetails_settings_title" msgid="3224004818524731568">"正在运行的应用"</string>
    <string name="no_services" msgid="7133900764462288263">"无活动服务"</string>
    <string name="runningservicedetails_services_title" msgid="391168243725357375">"服务"</string>
    <string name="runningservicedetails_processes_title" msgid="928115582044655268">"进程"</string>
    <string name="service_stop" msgid="6369807553277527248">"停止"</string>
    <string name="service_manage" msgid="1876642087421959194">"设置"</string>
    <string name="service_stop_description" msgid="9146619928198961643">"此服务由其应用启动。停止服务可能会导致应用无法运行。"</string>
    <string name="heavy_weight_stop_description" msgid="6050413065144035971">"无法安全地停止该应用。如果强行停止，可能会导致您目前的部分工作内容丢失。"</string>
    <string name="background_process_stop_description" msgid="3834163804031287685">"这是旧应用进程，仍在运行，以备不时之需。通常不建议停止。"</string>
    <string name="service_manage_description" msgid="479683614471552426">"<xliff:g id="CLIENT_NAME">%1$s</xliff:g>：当前正在使用中。点按“设置”即可对其进行管理。"</string>
    <string name="main_running_process_description" msgid="1130702347066340890">"正在使用的主要进程。"</string>
    <string name="process_service_in_use_description" msgid="8993335064403217080">"正在使用服务<xliff:g id="COMP_NAME">%1$s</xliff:g>。"</string>
    <string name="process_provider_in_use_description" msgid="5586603325677678940">"正在使用提供商<xliff:g id="COMP_NAME">%1$s</xliff:g>。"</string>
    <string name="runningservicedetails_stop_dlg_title" msgid="4253292537154337233">"要停止系统服务吗？"</string>
    <string name="runningservicedetails_stop_dlg_text" product="tablet" msgid="3371302398335665793">"如果停止此服务，您平板电脑上的某些功能也将随之停止工作，并在您将平板电脑关机然后再重新打开后才能使用。"</string>
    <string name="runningservicedetails_stop_dlg_text" product="default" msgid="3920243762189484756">"如果停止此服务，您手机上的某些功能也将随之停止工作，并在您将手机关机然后再重新打开后才能使用。"</string>
    <string name="language_input_gesture_title" msgid="8749227808244881255">"语言、输入法和手势"</string>
    <string name="language_input_gesture_summary_on_with_assist" msgid="7219895055450633449"></string>
    <string name="language_input_gesture_summary_on_non_assist" msgid="756147879200943161"></string>
    <string name="language_input_gesture_summary_off" msgid="4617198819416948217"></string>
    <string name="language_settings" msgid="8758655933029560944">"语言和输入法"</string>
    <string name="language_empty_list_user_restricted" msgid="5984015900102140696">"您无权更改设备语言。"</string>
    <string name="language_keyboard_settings_title" msgid="3709159207482544398">"语言和输入法"</string>
    <string name="input_assistance" msgid="7577795275222555487">"输入帮助"</string>
    <string name="keyboard_settings_category" msgid="8275523930352487827">"键盘和输入法"</string>
    <string name="phone_language" msgid="7116581601133118044">"语言"</string>
    <string name="phone_language_summary" msgid="3871309445655554211"></string>
    <string name="auto_replace" msgid="6199184757891937822">"自动替换"</string>
    <string name="auto_replace_summary" msgid="370288728200084466">"更正错误输入的字词"</string>
    <string name="auto_caps" msgid="6379232078052591265">"自动大写"</string>
    <string name="auto_caps_summary" msgid="6358102538315261466">"将句首字母大写"</string>
    <string name="auto_punctuate" msgid="4595367243950425833">"自动加标点"</string>
    <string name="hardkeyboard_category" msgid="5957168411305769899">"物理键盘设置"</string>
    <string name="auto_punctuate_summary" msgid="4372126865670574837">"按空格键两次可插入句号"</string>
    <string name="show_password" msgid="4837897357002495384">"显示密码"</string>
    <string name="show_password_summary" msgid="3365397574784829969">"输入时短暂显示这些字符"</string>
    <string name="spellchecker_security_warning" msgid="9060897418527708922">"此拼写检查工具可能会收集您键入的所有文字，包括密码和信用卡号等个人数据。它源自应用“<xliff:g id="SPELLCHECKER_APPLICATION_NAME">%1$s</xliff:g>”。要使用此拼写检查工具吗？"</string>
    <string name="spellchecker_quick_settings" msgid="246728645150092058">"设置"</string>
    <string name="spellchecker_language" msgid="6041050114690541437">"语言"</string>
    <string name="keyboard_and_input_methods_category" msgid="6035224122054465137">"键盘和输入法"</string>
    <string name="virtual_keyboard_category" msgid="1012830752318677119">"虚拟键盘"</string>
    <string name="available_virtual_keyboard_category" msgid="7645766574969139819">"可用虚拟键盘"</string>
    <string name="add_virtual_keyboard" msgid="3302152381456516928">"管理键盘"</string>
    <string name="keyboard_assistance_category" msgid="5843634175231134014">"键盘辅助功能"</string>
    <string name="physical_keyboard_title" msgid="8285149877925752042">"实体键盘"</string>
    <string name="show_ime" msgid="2658582193437188227">"显示虚拟键盘"</string>
    <string name="show_ime_summary" msgid="8164993045923240698">"开启后，连接到实体键盘时，它会一直显示在屏幕上"</string>
    <string name="keyboard_shortcuts_helper" msgid="4839453720463798145">"键盘快捷键帮助程序"</string>
    <string name="keyboard_shortcuts_helper_summary" msgid="5871299901459743288">"显示可用的快捷键"</string>
    <string name="default_keyboard_layout" msgid="4172606673510531271">"默认"</string>
    <string name="pointer_speed" msgid="1221342330217861616">"指针速度"</string>
    <string name="game_controller_settings_category" msgid="8794508575329923718">"游戏控制器"</string>
    <string name="vibrate_input_devices" msgid="421936611134697943">"重定向振动"</string>
    <string name="vibrate_input_devices_summary" msgid="82093256723774584">"连接后将振动传到游戏控制器"</string>
    <string name="keyboard_layout_dialog_title" msgid="8030087214949381372">"选择键盘布局"</string>
    <string name="keyboard_layout_dialog_setup_button" msgid="8514583747236476384">"设置键盘布局"</string>
    <string name="keyboard_layout_dialog_switch_hint" msgid="3889961090676293795">"要切换，请按 Ctrl+空格键"</string>
    <string name="keyboard_layout_default_label" msgid="2952672513543482165">"默认"</string>
    <string name="keyboard_layout_picker_title" msgid="556081931972771610">"键盘布局"</string>
    <string name="user_dict_settings_title" msgid="3427169369758733521">"个人字典"</string>
    <string name="user_dict_settings_summary" msgid="7965571192902870454"></string>
    <string name="user_dict_settings_add_menu_title" msgid="4056762757149923551">"添加"</string>
    <string name="user_dict_settings_add_dialog_title" msgid="4702613990174126482">"添加到字典"</string>
    <string name="user_dict_settings_add_screen_title" msgid="742580720124344291">"词组"</string>
    <string name="user_dict_settings_add_dialog_more_options" msgid="8848798370746019825">"显示更多选项"</string>
    <string name="user_dict_settings_add_dialog_less_options" msgid="2441785268726036101">"隐藏部分选项"</string>
    <string name="user_dict_settings_add_dialog_confirm" msgid="6225823625332416144">"确定"</string>
    <string name="user_dict_settings_add_word_option_name" msgid="7868879174905963135">"字词："</string>
    <string name="user_dict_settings_add_shortcut_option_name" msgid="660089258866063925">"快捷键："</string>
    <string name="user_dict_settings_add_locale_option_name" msgid="5696358317061318532">"语言："</string>
    <string name="user_dict_settings_add_word_hint" msgid="5725254076556821247">"输入字词"</string>
    <string name="user_dict_settings_add_shortcut_hint" msgid="7333763456561873445">"快捷键（选填）"</string>
    <string name="user_dict_settings_edit_dialog_title" msgid="8967476444840548674">"编辑字词"</string>
    <string name="user_dict_settings_context_menu_edit_title" msgid="2210564879320004837">"编辑"</string>
    <string name="user_dict_settings_context_menu_delete_title" msgid="9140703913776549054">"删除"</string>
    <string name="user_dict_settings_empty_text" msgid="1971969756133074922">"用户字典中没有任何字词。要添加字词，请点按“添加”(+) 按钮。"</string>
    <string name="user_dict_settings_all_languages" msgid="6742000040975959247">"所有语言"</string>
    <string name="user_dict_settings_more_languages" msgid="7316375944684977910">"更多语言..."</string>
    <string name="testing" msgid="6584352735303604146">"测试"</string>
    <string name="testing_phone_info" product="tablet" msgid="193561832258534798">"平板电脑信息"</string>
    <string name="testing_phone_info" product="default" msgid="8656693364332840056">"手机信息"</string>
    <string name="input_methods_settings_title" msgid="6800066636850553887">"文字输入"</string>
    <string name="input_method" msgid="5434026103176856164">"输入法"</string>
    <string name="current_input_method" msgid="2636466029213488159">"当前输入法"</string>
    <string name="input_method_selector" msgid="4311213129681430709">"选择输入法"</string>
    <string name="input_method_selector_show_automatically_title" msgid="1001612945471546158">"自动"</string>
    <string name="input_method_selector_always_show_title" msgid="3891824124222371634">"总是显示"</string>
    <string name="input_method_selector_always_hide_title" msgid="7699647095118680424">"始终隐藏"</string>
    <string name="configure_input_method" msgid="1317429869771850228">"设置输入法"</string>
    <string name="input_method_settings" msgid="5801295625486269553">"设置"</string>
    <string name="input_method_settings_button" msgid="6778344383871619368">"设置"</string>
    <string name="input_methods_settings_label_format" msgid="6002887604815693322">"<xliff:g id="IME_NAME">%1$s</xliff:g> 设置"</string>
    <string name="input_methods_and_subtype_enabler_title" msgid="4421813273170250462">"选择有效的输入法"</string>
    <string name="onscreen_keyboard_settings_summary" msgid="5841558383556238653">"屏幕键盘设置"</string>
    <string name="builtin_keyboard_settings_title" msgid="7688732909551116798">"物理键盘"</string>
    <string name="builtin_keyboard_settings_summary" msgid="2392531685358035899">"物理键盘设置"</string>
    <string name="gadget_picker_title" msgid="98374951396755811">"选择小工具"</string>
    <string name="widget_picker_title" msgid="9130684134213467557">"选择微件"</string>
    <string name="allow_bind_app_widget_activity_allow_bind_title" msgid="2538303018392590627">"是否允许该应用创建微件并查看其数据？"</string>
    <string name="allow_bind_app_widget_activity_allow_bind" msgid="1584388129273282080">"当您创建微件后，“<xliff:g id="WIDGET_HOST_NAME">%1$s</xliff:g>”将能查看其显示的所有数据。"</string>
    <string name="allow_bind_app_widget_activity_always_allow_bind" msgid="7037503685859688034">"始终允许“<xliff:g id="WIDGET_HOST_NAME">%1$s</xliff:g>”创建微件并查看其数据"</string>
    <string name="usage_stats_label" msgid="5890846333487083609">"使用情况统计数据"</string>
    <string name="testing_usage_stats" msgid="7823048598893937339">"使用情况统计数据"</string>
    <string name="display_order_text" msgid="8592776965827565271">"排序方式："</string>
    <string name="app_name_label" msgid="5440362857006046193">"应用"</string>
    <string name="last_time_used_label" msgid="8459441968795479307">"上次使用时间"</string>
    <string name="usage_time_label" msgid="295954901452833058">"使用时间"</string>
    <string name="accessibility_settings" msgid="3975902491934816215">"无障碍"</string>
    <string name="accessibility_settings_title" msgid="2130492524656204459">"无障碍设置"</string>
    <string name="accessibility_settings_summary" msgid="981260486011624939">"屏幕阅读器、显示、互动控件"</string>
    <string name="vision_settings_title" msgid="4204111425716868288">"阅读辅助设置"</string>
    <string name="vision_settings_description" msgid="5679491180156408260">"您可以根据自己的需求对此设备进行自定义。以后，您可以在“设置”中更改这些辅助功能设置。"</string>
    <string name="vision_settings_suggestion_title" msgid="8058794060304707004">"更改字体大小"</string>
    <string name="screen_reader_category_title" msgid="7739154903913400641">"屏幕阅读器"</string>
    <string name="audio_and_captions_category_title" msgid="3420727114421447524">"音频和屏幕上的文字"</string>
    <string name="display_category_title" msgid="685461049938269166">"显示"</string>
    <string name="interaction_control_category_title" msgid="7836591031872839151">"互动控件"</string>
    <string name="user_installed_services_category_title" msgid="6426376488922158647">"已下载的服务"</string>
    <string name="experimental_category_title" msgid="5272318666666893547">"实验性功能"</string>
    <string name="talkback_title" msgid="7912059827205988080">"TalkBack"</string>
    <string name="talkback_summary" msgid="8331244650729024963">"屏幕阅读器主要适用于盲人和视力不佳的人士"</string>
    <string name="select_to_speak_summary" msgid="4282846695497544515">"点按屏幕上的内容即可让系统大声朗读出来"</string>
    <string name="accessibility_captioning_title" msgid="7589266662024836291">"字幕"</string>
    <string name="accessibility_screen_magnification_title" msgid="6001128808776506021">"放大功能"</string>
    <string name="accessibility_screen_magnification_gestures_title" msgid="3719929521571489913">"点按屏幕三次进行放大"</string>
    <string name="accessibility_screen_magnification_navbar_title" msgid="7141753038957538230">"使用按钮进行放大"</string>
    <string name="accessibility_screen_magnification_state_navbar_gesture" msgid="2760906043221923793">"使用按钮/点按屏幕三次进行放大"</string>
    <string name="accessibility_preference_magnification_summary" msgid="5867883657521404509">"在屏幕上放大"</string>
    <string name="accessibility_screen_magnification_short_summary" msgid="3411979839172752057">"点按三次即可放大屏幕"</string>
    <string name="accessibility_screen_magnification_navbar_short_summary" msgid="3693116360267980492">"点按按钮即可放大"</string>
    <string name="accessibility_screen_magnification_summary" msgid="5258868553337478505"><b>"要放大"</b>"，在屏幕上快速点按屏幕三次即可。\n"<ul><li>"用双指或多指在屏幕上拖动即可进行滚动"</li>\n<li>"张合双指或多指即可调整缩放级别"</li></ul>\n\n<b>"要暂时性地放大"</b>"，请快速在屏幕上点按三次，并在最后一次点按时按住手指不放。\n"<ul><li>"拖动手指即可在屏幕上四处移动"</li>\n<li>"松开手指即可缩小回原来的状态"</li></ul>\n\n"您不能在键盘和导航栏中使用放大功能。"</string>
    <string name="accessibility_screen_magnification_navbar_summary" msgid="1996584694050087161">"启用放大功能后，使用屏幕底部的“无障碍”按钮可以快速放大。\n\n"<b>"要放大"</b>"，请点按“无障碍”按钮，然后点按屏幕上的任意位置。\n"<ul><li>"拖动双指或多指即可进行滚动"</li>\n<li>"张合双指或多指即可调整缩放级别"</li></ul>\n\n<b>"要暂时性地放大"</b>"，请点按“无障碍”按钮，然后触摸并按住屏幕上的任意位置。\n"<ul><li>"拖动手指即可在屏幕上四处移动"</li>\n<li>"松开手指即可缩小回原来的状态"</li></ul>\n\n"您不能在键盘和导航栏中使用放大功能。"</string>
    <string name="accessibility_screen_magnification_navbar_configuration_warning" msgid="70533120652758190">"“无障碍”按钮已设为“<xliff:g id="SERVICE">%1$s</xliff:g>”。要使用放大功能，请触摸并按住“无障碍”按钮，然后选择“放大功能”。"</string>
    <string name="accessibility_global_gesture_preference_title" msgid="2048884356166982714">"音量键快捷方式"</string>
    <string name="accessibility_shortcut_service_title" msgid="4779360749706905640">"快捷方式服务"</string>
    <string name="accessibility_shortcut_service_on_lock_screen_title" msgid="5490636079625489534">"屏幕锁定时也可以用"</string>
    <string name="accessibility_shortcut_description" msgid="1765853731190717372">"启用这项快捷方式后，同时按下两个音量键 3 秒钟即可启动无障碍功能。"</string>
    <string name="accessibility_toggle_high_text_contrast_preference_title" msgid="2567402942683463779">"高对比度文字"</string>
    <string name="accessibility_toggle_screen_magnification_auto_update_preference_title" msgid="7218498768415430963">"自动更新屏幕放大状态"</string>
    <string name="accessibility_toggle_screen_magnification_auto_update_preference_summary" msgid="4392059334816220155">"在应用转换时更新屏幕放大状态"</string>
    <string name="accessibility_power_button_ends_call_prerefence_title" msgid="6673851944175874235">"按电源按钮结束通话"</string>
    <string name="accessibility_toggle_large_pointer_icon_title" msgid="535173100516295580">"大号鼠标指针"</string>
    <string name="accessibility_disable_animations" msgid="5876035711526394795">"移除动画"</string>
    <string name="accessibility_toggle_master_mono_title" msgid="4363806997971905302">"单声道音频"</string>
    <string name="accessibility_toggle_master_mono_summary" msgid="5634277025251530927">"播放音频时合并声道"</string>
    <string name="accessibility_long_press_timeout_preference_title" msgid="6708467774619266508">"触摸和按住延迟"</string>
    <string name="accessibility_display_inversion_preference_title" msgid="2119647786141420802">"颜色反转"</string>
    <string name="accessibility_display_inversion_preference_subtitle" msgid="7052959202195368109">"可能会影响性能"</string>
    <string name="accessibility_autoclick_preference_title" msgid="2434062071927416098">"停留时间"</string>
    <string name="accessibility_autoclick_description" msgid="4908960598910896933">"如果您使用鼠标，则可以将光标设置为停止移动一段时间后自动点击。"</string>
    <string name="accessibility_autoclick_delay_preference_title" msgid="3962261178385106006">"点击前延迟"</string>
    <string name="accessibility_vibration_settings_title" msgid="3453277326300320803">"振动"</string>
    <string name="accessibility_notification_vibration_title" msgid="660829933960942244">"响铃和通知振动"</string>
    <string name="accessibility_touch_vibration_title" msgid="7931823772673770492">"触摸振动"</string>
    <string name="accessibility_service_master_switch_title" msgid="6835441300276358239">"使用服务"</string>
    <string name="accessibility_daltonizer_master_switch_title" msgid="8655284637968823154">"使用色彩校正"</string>
    <string name="accessibility_caption_master_switch_title" msgid="4010227386676077826">"使用字幕"</string>
    <string name="accessibility_hearingaid_title" msgid="8312145423610648518">"助听器"</string>
    <string name="accessibility_hearingaid_not_connected_summary" msgid="6240237523789614599">"未连接任何助听器"</string>
    <string name="accessibility_hearingaid_adding_summary" msgid="6371077608778830020">"添加助听器"</string>
    <string name="accessibility_hearingaid_pair_instructions_first_message" msgid="3912093691643131154">"要为助听器配对，请在下一个屏幕上查找并点按您的设备。"</string>
    <string name="accessibility_hearingaid_pair_instructions_second_message" msgid="5596683393607650243">"请确保您的助听器已处于配对模式。"</string>
    <string name="accessibility_hearingaid_active_device_summary" msgid="1246354030808703545">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g> 目前正在使用中"</string>
    <plurals name="show_number_hearingaid_count" formatted="false" msgid="3160782397139295486">
      <item quantity="other">已保存 <xliff:g id="NUMBER_DEVICE_COUNT_1">%1$d</xliff:g> 台助听器</item>
      <item quantity="one">已保存 <xliff:g id="NUMBER_DEVICE_COUNT_0">%1$d</xliff:g> 台助听器</item>
    </plurals>
    <string name="accessibility_summary_state_enabled" msgid="7914278500885887763">"开启"</string>
    <string name="accessibility_summary_state_disabled" msgid="2984230257590246745">"关闭"</string>
    <string name="accessibility_summary_state_stopped" msgid="1144156815350270876">"无法运行。点按即可查看相关信息。"</string>
    <string name="accessibility_description_state_stopped" msgid="6953539746047006596">"此服务出现故障。"</string>
    <string name="enable_quick_setting" msgid="2366999897816894536">"在“快捷设置”中显示"</string>
    <string name="daltonizer_type" msgid="1124178250809091080">"校正模式"</string>
    <plurals name="accessibilty_autoclick_preference_subtitle_extremely_short_delay" formatted="false" msgid="7340347830562315800">
      <item quantity="other">延迟时间极短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间极短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_very_short_delay" formatted="false" msgid="5589565607652364932">
      <item quantity="other">延迟时间很短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间很短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_short_delay" formatted="false" msgid="5887754135102768400">
      <item quantity="other">延迟时间短（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间短（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_long_delay" formatted="false" msgid="6340683412750219405">
      <item quantity="other">延迟时间长（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间长（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <plurals name="accessibilty_autoclick_preference_subtitle_very_long_delay" formatted="false" msgid="3503199424330634970">
      <item quantity="other">延迟时间很长（<xliff:g id="CLICK_DELAY_LABEL_1">%1$d</xliff:g> 毫秒）</item>
      <item quantity="one">延迟时间很长（<xliff:g id="CLICK_DELAY_LABEL_0">%1$d</xliff:g> 毫秒）</item>
    </plurals>
    <string name="accessibility_vibration_summary" msgid="1372393829668784669">"铃声：<xliff:g id="SUMMARY_RING">%1$s</xliff:g>，触摸强度：<xliff:g id="SUMMARY_TOUCH">%2$s</xliff:g>"</string>
    <string name="accessibility_vibration_summary_off" msgid="1753566394591809629">"将响铃和通知振动设为关闭"</string>
    <string name="accessibility_vibration_summary_low" msgid="7628418309029013867">"将响铃和通知振动设为低"</string>
    <string name="accessibility_vibration_summary_medium" msgid="3422136736880414093">"将响铃和通知振动设为中"</string>
    <string name="accessibility_vibration_summary_high" msgid="3239807793182635729">"将响铃和通知振动设为高"</string>
    <string name="accessibility_vibration_intensity_off" msgid="4613890213008630847">"关闭"</string>
    <string name="accessibility_vibration_intensity_low" msgid="2017572546489862987">"低"</string>
    <string name="accessibility_vibration_intensity_medium" msgid="3782136025830279769">"中"</string>
    <string name="accessibility_vibration_intensity_high" msgid="2543921139337952491">"高"</string>
    <string name="accessibility_menu_item_settings" msgid="3344942964710773365">"设置"</string>
    <string name="accessibility_feature_state_on" msgid="2864292320042673806">"开启"</string>
    <string name="accessibility_feature_state_off" msgid="4172584906487070211">"关闭"</string>
    <string name="captioning_preview_title" msgid="1234015253497016890">"预览"</string>
    <string name="captioning_standard_options_title" msgid="3284211791180335844">"标准选项"</string>
    <string name="captioning_locale" msgid="4559155661018823503">"语言"</string>
    <string name="captioning_text_size" msgid="6737002449104466028">"文字大小"</string>
    <string name="captioning_preset" msgid="8939737196538429044">"字幕样式"</string>
    <string name="captioning_custom_options_title" msgid="5067500939930322405">"自定义选项"</string>
    <string name="captioning_background_color" msgid="9053011212948992570">"背景颜色"</string>
    <string name="captioning_background_opacity" msgid="6029993616419971202">"背景不透明度"</string>
    <string name="captioning_window_color" msgid="6902052743419717394">"字幕窗口颜色"</string>
    <string name="captioning_window_opacity" msgid="5041556024849862376">"字幕窗口不透明度"</string>
    <string name="captioning_foreground_color" msgid="85623486537640059">"文字颜色"</string>
    <string name="captioning_foreground_opacity" msgid="4370967856995419788">"文字不透明度"</string>
    <string name="captioning_edge_color" msgid="3670094753735263238">"边缘颜色"</string>
    <string name="captioning_edge_type" msgid="5997247394951682154">"边缘类型"</string>
    <string name="captioning_typeface" msgid="1826169240566563259">"字体系列"</string>
    <string name="captioning_preview_text" msgid="4067935959797375065">"字幕外观示例"</string>
    <string name="captioning_preview_characters" msgid="7105909138497851769">"Aa"</string>
    <string name="locale_default" msgid="2593883646136326969">"默认"</string>
    <string name="color_title" msgid="4258931051732243983">"颜色"</string>
    <string name="color_unspecified" msgid="5179683785413568326">"默认"</string>
    <string name="color_none" msgid="3475640044925814795">"无"</string>
    <string name="color_white" msgid="8045195170201590239">"白色"</string>
    <string name="color_gray" msgid="9192312087142726313">"灰色"</string>
    <string name="color_black" msgid="7517353520909872561">"黑色"</string>
    <string name="color_red" msgid="4949354900304125428">"红色"</string>
    <string name="color_green" msgid="5537717328428845841">"绿色"</string>
    <string name="color_blue" msgid="7731984529016953223">"蓝色"</string>
    <string name="color_cyan" msgid="7033027180641173211">"青色"</string>
    <string name="color_yellow" msgid="9112680561610873529">"黄色"</string>
    <string name="color_magenta" msgid="5059212823607815549">"紫红色"</string>
    <string name="enable_service_title" msgid="3061307612673835592">"要使用“<xliff:g id="SERVICE">%1$s</xliff:g>”吗？"</string>
    <string name="capabilities_list_title" msgid="86713361724771971">"“<xliff:g id="SERVICE">%1$s</xliff:g>”需要："</string>
    <string name="touch_filtered_warning" msgid="8644034725268915030">"由于某个应用遮挡了权限请求界面，因此“设置”应用无法验证您的回应。"</string>
    <string name="enable_service_encryption_warning" msgid="3064686622453974606">"如果您开启 <xliff:g id="SERVICE">%1$s</xliff:g>，您的设备将无法使用屏幕锁定来增强数据加密。"</string>
    <string name="secure_lock_encryption_warning" msgid="460911459695077779">"由于您已开启无障碍服务，因此您的设备将无法使用屏幕锁定来增强数据加密。"</string>
    <string name="enable_service_pattern_reason" msgid="777577618063306751">"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的解锁图案。"</string>
    <string name="enable_service_pin_reason" msgid="7882035264853248228">"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的 PIN 码。"</string>
    <string name="enable_service_password_reason" msgid="1224075277603097951">"由于开启 <xliff:g id="SERVICE">%1$s</xliff:g> 会影响数据加密，因此您需要确认您的密码。"</string>
    <string name="capability_title_receiveAccessibilityEvents" msgid="1869032063969970755">"监测您的操作"</string>
    <string name="capability_desc_receiveAccessibilityEvents" msgid="6640333613848713883">"在您与应用互动时接收通知。"</string>
    <string name="disable_service_title" msgid="3624005212728512896">"要停用“<xliff:g id="SERVICE">%1$s</xliff:g>”吗？"</string>
    <string name="disable_service_message" msgid="2247101878627941561">"点按“确定”将会停用“<xliff:g id="SERVICE">%1$s</xliff:g>”。"</string>
    <string name="accessibility_no_services_installed" msgid="7200948194639038807">"未安装任何服务"</string>
    <string name="accessibility_no_service_selected" msgid="2840969718780083998">"未选择任何服务"</string>
    <string name="accessibility_service_default_description" msgid="1072730037861494125">"没有提供说明。"</string>
    <string name="settings_button" msgid="3006713718908152930">"设置"</string>
    <string name="print_settings" msgid="4742428530112487843">"打印"</string>
    <string name="print_settings_summary_no_service" msgid="6354322414246865875">"关闭"</string>
    <plurals name="print_settings_summary" formatted="false" msgid="6005468025646083029">
      <item quantity="other">已开启 <xliff:g id="COUNT">%1$d</xliff:g> 项打印服务</item>
      <item quantity="one">已开启 1 项打印服务</item>
    </plurals>
    <plurals name="print_jobs_summary" formatted="false" msgid="5810106725778525400">
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 项打印任务</item>
      <item quantity="one">1 项打印任务</item>
    </plurals>
    <string name="print_settings_title" msgid="3685449667822217816">"打印服务"</string>
    <string name="print_no_services_installed" msgid="8443039625463872294">"未安装任何服务"</string>
    <string name="print_no_printers_found" msgid="989018646884973683">"找不到打印机"</string>
    <string name="print_menu_item_settings" msgid="6591330373682227082">"设置"</string>
    <string name="print_menu_item_add_printers" msgid="2890738028215834012">"添加打印机"</string>
    <string name="print_feature_state_on" msgid="8098901852502441048">"开启"</string>
    <string name="print_feature_state_off" msgid="7294876968403966040">"关闭"</string>
    <string name="print_menu_item_add_service" msgid="3811645167869797802">"添加服务"</string>
    <string name="print_menu_item_add_printer" msgid="8251218970577291032">"添加打印机"</string>
    <string name="print_menu_item_search" msgid="7025589328240514553">"搜索"</string>
    <string name="print_searching_for_printers" msgid="4680248496457576358">"正在搜索打印机"</string>
    <string name="print_service_disabled" msgid="7739452396114245222">"服务已停用"</string>
    <string name="print_print_jobs" msgid="3582094777756968793">"打印作业"</string>
    <string name="print_print_job" msgid="7563741676053287211">"打印作业"</string>
    <string name="print_restart" msgid="8373999687329384202">"重新开始"</string>
    <string name="print_cancel" msgid="3621199386568672235">"取消"</string>
    <string name="print_job_summary" msgid="8472427347192930694">"<xliff:g id="PRINTER">%1$s</xliff:g>\n<xliff:g id="TIME">%2$s</xliff:g>"</string>
    <string name="print_configuring_state_title_template" msgid="1228890182762324249">"正在配置“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_printing_state_title_template" msgid="5736107667714582025">"正在打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_cancelling_state_title_template" msgid="7102968925358219875">"正在取消打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_failed_state_title_template" msgid="1436099128973357969">"打印机在打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”时出错"</string>
    <string name="print_blocked_state_title_template" msgid="9065391617425962424">"打印机拒绝打印“<xliff:g id="PRINT_JOB_NAME">%1$s</xliff:g>”"</string>
    <string name="print_search_box_shown_utterance" msgid="7730361832020726951">"搜索框已显示"</string>
    <string name="print_search_box_hidden_utterance" msgid="7980832833405818400">"搜索框已隐藏"</string>
    <string name="printer_info_desc" msgid="5824995108703060003">"此打印机的详细信息"</string>
    <string name="power_usage_summary_title" msgid="7190304207330319919">"电池"</string>
    <string name="power_usage_summary" msgid="7237084831082848168">"耗电情况"</string>
    <string name="power_usage_not_available" msgid="3109326074656512387">"没有电池使用数据。"</string>
    <string name="power_usage_level_and_status" msgid="7449847570973811784">"<xliff:g id="LEVEL">%1$s</xliff:g> - <xliff:g id="STATUS">%2$s</xliff:g>"</string>
    <string name="power_discharge_remaining" msgid="4925678997049911808">"还可用：<xliff:g id="REMAIN">%1$s</xliff:g>"</string>
    <string name="power_charge_remaining" msgid="6132074970943913135">"充电剩余时间：<xliff:g id="UNTIL_CHARGED">%1$s</xliff:g>"</string>
    <string name="background_activity_title" msgid="8482171736539410135">"后台限制"</string>
    <string name="background_activity_summary" msgid="8140094430510517362">"允许应用在后台运行"</string>
    <string name="background_activity_summary_disabled" msgid="3710669050484599847">"不允许应用在后台运行"</string>
    <string name="background_activity_summary_whitelisted" msgid="1079899502347973947">"无法限制后台使用"</string>
    <string name="background_activity_warning_dialog_title" msgid="2216249969149568871">"要限制后台活动吗？"</string>
    <string name="background_activity_warning_dialog_text" msgid="7049624449246121981">"如果您限制某个应用的后台活动，可能会导致该应用出现异常"</string>
    <string name="background_activity_disabled_dialog_text" msgid="6133420589651880824">"由于此应用未设置为优化电池用量，因此您无法对其加以限制。\n\n要限制该应用，请先开启电池优化功能。"</string>
    <string name="device_screen_usage" msgid="3386088035570409683">"上次充满电后的屏幕用电量"</string>
    <string name="device_screen_consumption" msgid="4607589286438986687">"屏幕耗电量"</string>
    <string name="device_cellular_network" msgid="4724773411762382950">"移动网络扫描"</string>
    <string name="power_usage_list_summary" msgid="5584049564906462506">"充满电后的电池用量"</string>
    <string name="screen_usage_summary" msgid="6687403051423153550">"充满电后的屏幕使用时间"</string>
    <string name="device_usage_list_summary" msgid="5623036661468763251">"充满电后的设备用电量"</string>
    <string name="battery_since_unplugged" msgid="338073389740738437">"拔下电源后的电量消耗情况"</string>
    <string name="battery_since_reset" msgid="7464546661121187045">"重置后的电量消耗情况"</string>
    <string name="battery_stats_on_battery" msgid="4970762168505236033">"电池已用时间：<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="battery_stats_duration" msgid="7464501326709469282">"拔下电源后已过 <xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="battery_stats_charging_label" msgid="4223311142875178785">"充电"</string>
    <string name="battery_stats_screen_on_label" msgid="7150221809877509708">"屏幕开启"</string>
    <string name="battery_stats_gps_on_label" msgid="1193657533641951256">"GPS 开启"</string>
    <string name="battery_stats_camera_on_label" msgid="4935637383628414968">"相机开启"</string>
    <string name="battery_stats_flashlight_on_label" msgid="4319637669889411307">"手电筒开启"</string>
    <string name="battery_stats_wifi_running_label" msgid="1845839195549226252">"WLAN"</string>
    <string name="battery_stats_wake_lock_label" msgid="1908942681902324095">"唤醒"</string>
    <string name="battery_stats_phone_signal_label" msgid="4137799310329041341">"移动网络信号"</string>
    <!-- no translation found for battery_stats_last_duration (1535831453827905957) -->
    <skip />
    <string name="awake" msgid="387122265874485088">"设备唤醒时间"</string>
    <string name="wifi_on_time" msgid="3208518458663637035">"WLAN开启时间"</string>
    <string name="bluetooth_on_time" msgid="3056108148042308690">"WLAN开启时间"</string>
    <string name="advanced_battery_title" msgid="6768618303037280828">"电池用量"</string>
    <string name="history_details_title" msgid="3608240585315506067">"详细电量使用记录"</string>
    <string name="battery_details_title" msgid="6101394441569858580">"电池用量"</string>
    <string name="details_subtitle" msgid="32593908269911734">"详细使用情况"</string>
    <string name="controls_subtitle" msgid="390468421138288702">"省电提示"</string>
    <string name="packages_subtitle" msgid="4736416171658062768">"包含的软件包"</string>
    <string name="battery_abnormal_details_title" msgid="5469019021857291216">"多个应用正大量耗电"</string>
    <string name="battery_abnormal_wakelock_summary" msgid="4326186999058828831">"使设备保持唤醒状态"</string>
    <string name="battery_abnormal_wakeup_alarm_summary" msgid="644657277875785240">"在后台唤醒设备"</string>
    <string name="battery_abnormal_location_summary" msgid="6552797246798806002">"请求获取位置信息的频率过高"</string>
    <string name="battery_abnormal_apps_summary" msgid="792553273248686972">"<xliff:g id="NUMBER">%1$d</xliff:g> 个应用出现异常"</string>
    <string name="battery_tip_summary_title" msgid="368729969313047399">"应用正常运行中"</string>
    <string name="battery_tip_summary_summary" product="default" msgid="2198778125778121221">"手机的后台耗电量正常"</string>
    <string name="battery_tip_summary_summary" product="tablet" msgid="1183976728682325345">"平板电脑的后台耗电量正常"</string>
    <string name="battery_tip_summary_summary" product="device" msgid="363718204492523920">"设备的后台耗电量正常"</string>
    <string name="battery_tip_low_battery_title" msgid="5103420355109677385">"电池电量不足"</string>
    <string name="battery_tip_low_battery_summary" msgid="4702986182940709150">"电池的续航时间不理想"</string>
    <string name="battery_tip_smart_battery_title" product="default" msgid="2542822112725248683">"延长手机的电池续航时间"</string>
    <string name="battery_tip_smart_battery_title" product="tablet" msgid="6452567046912954866">"延长平板电脑的电池续航时间"</string>
    <string name="battery_tip_smart_battery_title" product="device" msgid="4445149029390556382">"延长设备的电池续航时间"</string>
    <string name="battery_tip_smart_battery_summary" msgid="2326809294592208069">"开启电池管理器"</string>
    <string name="battery_tip_early_heads_up_title" msgid="5788492366387119807">"开启省电模式"</string>
    <string name="battery_tip_early_heads_up_summary" msgid="1639271439914224547">"电池电量可能会比平时更早耗尽"</string>
    <string name="battery_tip_early_heads_up_done_title" msgid="4294083319255926811">"省电模式已开启"</string>
    <string name="battery_tip_early_heads_up_done_summary" msgid="7054036010928794364">"部分功能可能会受到限制"</string>
    <string name="battery_tip_high_usage_title" product="default" msgid="1282187115295901930">"手机的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_title" product="tablet" msgid="7422137233845959351">"平板电脑的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_title" product="device" msgid="5483320224273724068">"设备的使用强度比平时高"</string>
    <string name="battery_tip_high_usage_summary" msgid="6341311803303581798">"电池电量即将耗尽"</string>
    <string name="battery_tip_dialog_message" product="default" msgid="7001932078713215338">"手机的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <string name="battery_tip_dialog_message" product="tablet" msgid="8482296786233647690">"平板电脑的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <string name="battery_tip_dialog_message" product="device" msgid="2806861679225286129">"设备的使用强度比平时高。电池电量耗尽的速度可能会比预期更快。\n\n充满电后，耗电量较高的前 <xliff:g id="NUMBER">%1$d</xliff:g> 个应用为："</string>
    <plurals name="battery_tip_restrict_title" formatted="false" msgid="467228882789275512">
      <item quantity="other">限制 %1$d 个应用</item>
      <item quantity="one">限制 %1$d 个应用</item>
    </plurals>
    <plurals name="battery_tip_restrict_handled_title" formatted="false" msgid="2996094393897875408">
      <item quantity="other">%2$d 个应用最近受到限制</item>
      <item quantity="one">%1$s 个应用最近受到限制</item>
    </plurals>
    <plurals name="battery_tip_restrict_summary" formatted="false" msgid="5768962491638423979">
      <item quantity="other">%2$d 个应用的后台耗电量很高</item>
      <item quantity="one">%1$s 个应用的后台耗电量很高</item>
    </plurals>
    <plurals name="battery_tip_restrict_handled_summary" formatted="false" msgid="1040488674178753191">
      <item quantity="other">这些应用无法在后台运行</item>
      <item quantity="one">此应用无法在后台运行</item>
    </plurals>
    <plurals name="battery_tip_restrict_app_dialog_title" formatted="false" msgid="8130618585820429591">
      <item quantity="other">要限制 %1$d 个应用吗？</item>
      <item quantity="one">要限制应用吗？</item>
    </plurals>
    <string name="battery_tip_restrict_app_dialog_message" msgid="7271391929137806299">"为了节省电量，请将<xliff:g id="APP">%1$s</xliff:g>设置为停止在后台使用电量。该应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="battery_tip_restrict_apps_less_than_5_dialog_message" msgid="3175700359860699627">"为了节省电量，请将这些应用设置为停止在后台使用电量。受限应用可能无法正常运行，相应通知也可能会有所延迟。\n\n应用："</string>
    <string name="battery_tip_restrict_apps_more_than_5_dialog_message" msgid="582641081128076191">"为了节省电量，请将这些应用设置为停止在后台使用电量。受限应用可能无法正常运行，相应通知也可能会有所延迟。\n\n应用：\n<xliff:g id="APP_LIST">%1$s</xliff:g>。"</string>
    <string name="battery_tip_restrict_app_dialog_ok" msgid="8291115820018013353">"限制"</string>
    <string name="battery_tip_unrestrict_app_dialog_title" msgid="4321334634106715162">"要移除限制吗？"</string>
    <string name="battery_tip_unrestrict_app_dialog_message" msgid="6537761705584610231">"此应用将能够在后台消耗电量。这可能会导致电量耗尽速度比预期更快。"</string>
    <string name="battery_tip_unrestrict_app_dialog_ok" msgid="6022058431218137646">"移除"</string>
    <string name="battery_tip_unrestrict_app_dialog_cancel" msgid="3058235875830858902">"取消"</string>
    <string name="battery_tip_dialog_summary_message" product="default" msgid="4628448253185085796">"您的应用目前耗电量正常。如果应用耗电量过高，您的手机会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="battery_tip_dialog_summary_message" product="tablet" msgid="8327950887399420971">"您的应用目前耗电量正常。如果应用耗电量过高，您的平板电脑会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="battery_tip_dialog_summary_message" product="device" msgid="6753742263807939789">"您的应用目前耗电量正常。如果应用耗电量过高，您的设备会为您提供操作建议。\n\n如果电池电量不足，您可以随时开启省电模式。"</string>
    <string name="smart_battery_manager_title" msgid="870632749556793417">"电池管理器"</string>
    <string name="smart_battery_title" msgid="6218785691872466076">"自动管理应用"</string>
    <string name="smart_battery_summary" msgid="1339184602000004058">"限制不常用的应用的用电量"</string>
    <string name="smart_battery_footer" product="default" msgid="5555604955956219544">"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="smart_battery_footer" product="tablet" msgid="5555604955956219544">"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="smart_battery_footer" product="device" msgid="5555604955956219544">"当电池管理器检测到应用正在使用电量时，您可以选择限制这些应用。受限应用可能无法正常运行，相应通知也可能会有所延迟。"</string>
    <string name="restricted_app_title" msgid="8982477293044330653">"受限应用"</string>
    <plurals name="restricted_app_summary" formatted="false" msgid="7355687633914223530">
      <item quantity="other">正在限制 %1$d 个应用的电池用量</item>
      <item quantity="one">正在限制 %1$d 个应用的电池用量</item>
    </plurals>
    <string name="restricted_app_detail_footer" msgid="6739863162364046859">"这些应用一直在后台消耗电量。受限应用可能无法正常运行，且相关通知可能也会有所延迟。"</string>
    <string name="battery_auto_restriction_title" msgid="6553271897488963709">"使用电池管理器"</string>
    <string name="battery_auto_restriction_summary" msgid="8561335400991281062">"检测应用何时使用电量"</string>
    <string name="battery_manager_on" msgid="8643310865054362396">"已开启/正在检测应用何时使用电量"</string>
    <string name="battery_manager_off" msgid="5473135235710343576">"已关闭"</string>
    <plurals name="battery_manager_app_restricted" formatted="false" msgid="1026141135861471129">
      <item quantity="other">%1$d 个应用受到限制</item>
      <item quantity="one">%1$d 个应用受到限制</item>
    </plurals>
    <string name="dialog_stop_title" msgid="6395127715596746479">"要停止该应用吗？"</string>
    <string name="dialog_stop_message" product="default" msgid="4006631636646776488">"由于<xliff:g id="APP">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message" product="tablet" msgid="2369957934555162428">"由于<xliff:g id="APP">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message" product="device" msgid="6195430620406365292">"由于<xliff:g id="APP">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止该应用。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="default" msgid="1638726742782558262">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="tablet" msgid="8771690983566539742">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_message_wakeup_alarm" product="device" msgid="2854944538238649520">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>。\n\n如果问题仍然存在，您可能需要卸载此应用，以改善电池性能。"</string>
    <string name="dialog_stop_ok" msgid="2319777211264004900">"停止应用"</string>
    <string name="dialog_background_check_title" msgid="6936542136153283692">"要关闭后台使用功能并停止应用吗？"</string>
    <string name="dialog_background_check_message" product="default" msgid="4045827746349279563">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让手机保持唤醒状态，因此您的手机无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_message" product="tablet" msgid="8348214419901788270">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让平板电脑保持唤醒状态，因此您的平板电脑无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_message" product="device" msgid="5847977433118915863">"由于<xliff:g id="APP_0">%1$s</xliff:g>一直让设备保持唤醒状态，因此您的设备无法正常管理电池。\n\n要尝试解决此问题，您可以停止<xliff:g id="APP_1">%1$s</xliff:g>，并阻止该应用在后台运行。"</string>
    <string name="dialog_background_check_ok" msgid="412876934682899659">"关闭"</string>
    <string name="dialog_location_title" msgid="5888917530725874727">"要禁止获取位置信息吗？"</string>
    <string name="dialog_location_message" product="default" msgid="7774807745601479888">"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的手机无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_message" product="tablet" msgid="118745801732181618">"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的平板电脑无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_message" product="device" msgid="6783678153382298295">"由于<xliff:g id="APP">%1$s</xliff:g>在您并未使用它时仍一直请求获取您的位置信息，因此您的设备无法正常管理电池。\n\n要解决此问题，请关闭此应用的位置信息服务。"</string>
    <string name="dialog_location_ok" msgid="4572391197601313986">"关闭"</string>
    <string name="power_screen" msgid="3023346080675904613">"屏幕"</string>
    <string name="power_flashlight" msgid="7794409781003567614">"手电筒"</string>
    <string name="power_camera" msgid="4976286950934622605">"相机"</string>
    <string name="power_wifi" msgid="1135085252964054957">"WLAN"</string>
    <string name="power_bluetooth" msgid="4373329044379008289">"蓝牙"</string>
    <string name="power_cell" msgid="3392999761958982492">"移动网络待机"</string>
    <string name="power_phone" msgid="5392641106474567277">"语音通话"</string>
    <string name="power_idle" product="tablet" msgid="4612478572401640759">"平板电脑待机"</string>
    <string name="power_idle" product="default" msgid="9055659695602194990">"手机待机"</string>
    <string name="power_unaccounted" msgid="709925017022660740">"其他"</string>
    <string name="power_overcounted" msgid="2762354976171358445">"多算了的"</string>
    <string name="usage_type_cpu" msgid="715162150698338714">"CPU总使用时间"</string>
    <string name="usage_type_cpu_foreground" msgid="6500579611933211831">"CPU（前台）"</string>
    <string name="usage_type_wake_lock" msgid="5125438890233677880">"保持唤醒状态"</string>
    <string name="usage_type_gps" msgid="7989688715128160790">"GPS"</string>
    <string name="usage_type_wifi_running" msgid="3134357198266380400">"WLAN运行时间"</string>
    <string name="usage_type_phone" product="tablet" msgid="262638572890253393">"平板电脑"</string>
    <string name="usage_type_phone" product="default" msgid="9108247984998041853">"通话"</string>
    <string name="usage_type_data_send" msgid="8971710128438365919">"发送的移动数据包"</string>
    <string name="usage_type_data_recv" msgid="5468564329333954445">"接收的移动数据包"</string>
    <string name="usage_type_radio_active" msgid="1732647857619420121">"移动无线装置运行时间"</string>
    <string name="usage_type_data_wifi_send" msgid="1847552143597396162">"发送的WLAN数据包"</string>
    <string name="usage_type_data_wifi_recv" msgid="5678475911549183829">"接收的WLAN数据包"</string>
    <string name="usage_type_audio" msgid="6957269406840886290">"音频"</string>
    <string name="usage_type_video" msgid="4295357792078579944">"视频"</string>
    <string name="usage_type_camera" msgid="8299433109956769757">"相机"</string>
    <string name="usage_type_flashlight" msgid="1516392356962208230">"手电筒"</string>
    <string name="usage_type_on_time" msgid="3351200096173733159">"运行时间"</string>
    <string name="usage_type_no_coverage" msgid="3797004252954385053">"无信号时间"</string>
    <string name="usage_type_total_battery_capacity" msgid="3798285287848675346">"电池总容量"</string>
    <string name="usage_type_computed_power" msgid="5862792259009981479">"计算出的耗电量"</string>
    <string name="usage_type_actual_power" msgid="7047814738685578335">"观察到的耗电量"</string>
    <string name="battery_action_stop" msgid="649958863744041872">"强行停止"</string>
    <string name="battery_action_app_details" msgid="7861051816778419018">"应用信息"</string>
    <string name="battery_action_app_settings" msgid="4570481408106287454">"应用设置"</string>
    <string name="battery_action_display" msgid="7338551244519110831">"屏幕设置"</string>
    <string name="battery_action_wifi" msgid="8181553479021841207">"WLAN设置"</string>
    <string name="battery_action_bluetooth" msgid="8374789049507723142">"蓝牙设置"</string>
    <string name="battery_desc_voice" msgid="8980322055722959211">"语音通话所耗的电量"</string>
    <string name="battery_desc_standby" product="tablet" msgid="6284747418668280364">"平板电脑待机所耗的电量"</string>
    <string name="battery_desc_standby" product="default" msgid="3009080001948091424">"手机待机所耗的电量"</string>
    <string name="battery_desc_radio" msgid="5479196477223185367">"移动网络所耗的电量"</string>
    <string name="battery_sugg_radio" msgid="8211336978326295047">"不在信号覆盖范围内时切换到飞行模式，以节约电量"</string>
    <string name="battery_desc_flashlight" msgid="2908579430841025494">"手电筒所耗的电量"</string>
    <string name="battery_desc_camera" msgid="7375389919760613499">"相机所耗的电量"</string>
    <string name="battery_desc_display" msgid="5432795282958076557">"显示屏和背光所耗的电量"</string>
    <string name="battery_sugg_display" msgid="3370202402045141760">"降低屏幕亮度，缩短休眠延时"</string>
    <string name="battery_desc_wifi" msgid="2375567464707394131">"WLAN网络所耗的电量"</string>
    <string name="battery_sugg_wifi" msgid="7776093125855397043">"未使用或无法使用WLAN网络时将WLAN关闭"</string>
    <string name="battery_desc_bluetooth" msgid="8069070756186680367">"蓝牙所耗的电量"</string>
    <string name="battery_sugg_bluetooth_basic" msgid="4565141162650835009">"请在不使用蓝牙功能时将其关闭"</string>
    <string name="battery_sugg_bluetooth_headset" msgid="4071352514714259230">"尝试连接另一蓝牙设备"</string>
    <string name="battery_desc_apps" msgid="8530418792605735226">"该应用所耗的电量"</string>
    <string name="battery_sugg_apps_info" msgid="6907588126789841231">"停止或卸载应用"</string>
    <string name="battery_sugg_apps_gps" msgid="5959067516281866135">"选择“节电”模式"</string>
    <string name="battery_sugg_apps_settings" msgid="3974902365643634514">"该应用中可能提供了用于减少耗电量的设置"</string>
    <string name="battery_desc_users" msgid="7682989161885027823">"用户所耗的电量"</string>
    <string name="battery_desc_unaccounted" msgid="7404256448541818019">"其他耗电量"</string>
    <string name="battery_msg_unaccounted" msgid="1963583522633067961">"耗电量显示的是大致用电量，并不包括所有耗电来源所消耗的电量。其他耗电量显示的是系统计算出的大致耗电量与实际观察到的耗电量之间的差异量。"</string>
    <string name="battery_desc_overcounted" msgid="5481865509489228603">"多算了的用电量"</string>
    <string name="mah" msgid="95245196971239711">"<xliff:g id="NUMBER">%d</xliff:g>毫安时"</string>
    <string name="battery_used_for" msgid="2690821851327075443">"使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_active_for" msgid="2964359540508103032">"使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_screen_usage" msgid="6537658662149713585">"屏幕使用时间：<xliff:g id="TIME">^1</xliff:g>"</string>
    <string name="battery_used_by" msgid="1135316757755282999">"<xliff:g id="APP">%2$s</xliff:g>用电量占 <xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_overall_usage" msgid="2093409063297375436">"整体用电量的 <xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_detail_since_full_charge" msgid="7515347842046955855">"自上次充满电后的用电明细"</string>
    <string name="battery_last_full_charge" msgid="7151251641099019361">"上次充满电"</string>
    <string name="battery_full_charge_last" msgid="8892335687734288031">"电池在充满电后的预估使用时间"</string>
    <string name="battery_footer_summary" msgid="67169726550144016">"电池消耗量为大致值，可能会因使用情形而变化"</string>
    <string name="battery_detail_foreground" msgid="3350401514602032183">"在前台运行时"</string>
    <string name="battery_detail_background" msgid="1929644393553768999">"在后台运行时"</string>
    <string name="battery_detail_power_usage" msgid="6485766868610469101">"电池用量"</string>
    <string name="battery_detail_info_title" msgid="8227822131405620369">"自充满电后"</string>
    <string name="battery_detail_manage_title" msgid="9094314252105828014">"管理电池用量"</string>
    <string name="advanced_battery_graph_subtext" msgid="5621073891377915877">"系统会根据设备使用情况估算电池的剩余电量"</string>
    <string name="estimated_time_left" msgid="7514194472683370877">"预计剩余时间"</string>
    <string name="estimated_charging_time_left" msgid="5614442409326164691">"剩余充电时间"</string>
    <string name="estimated_time_description" msgid="8760210909000037089">"估算时间可能会因使用情形而有所改变"</string>
    <string name="menu_stats_unplugged" msgid="8296577130840261624">"拔下电源后已过 <xliff:g id="UNPLUGGED">%1$s</xliff:g>"</string>
    <string name="menu_stats_last_unplugged" msgid="5922246077592434526">"上次拔下电源 <xliff:g id="UNPLUGGED">%1$s</xliff:g> 时"</string>
    <string name="menu_stats_total" msgid="8973377864854807854">"总使用量"</string>
    <string name="menu_stats_refresh" msgid="1676215433344981075">"刷新"</string>
    <string name="process_kernel_label" msgid="3916858646836739323">"Android 操作系统"</string>
    <string name="process_mediaserver_label" msgid="6500382062945689285">"媒体服务器"</string>
    <string name="process_dex2oat_label" msgid="2592408651060518226">"应用优化"</string>
    <string name="battery_saver" msgid="8172485772238572153">"省电模式"</string>
    <string name="battery_saver_auto_title" msgid="8368709389419695611">"自动开启"</string>
    <string name="battery_saver_seekbar_title" msgid="4705356758573183963">"<xliff:g id="PERCENT">%1$s</xliff:g>"</string>
    <string name="battery_saver_seekbar_title_placeholder" msgid="1138980155985636295">"开启"</string>
    <string name="battery_saver_master_switch_title" msgid="622539414546588436">"使用省电模式"</string>
    <string name="battery_saver_turn_on_automatically_title" msgid="9023847300114669426">"自动开启"</string>
    <string name="battery_saver_turn_on_automatically_never" msgid="6610846456314373">"一律不"</string>
    <string name="battery_saver_turn_on_automatically_pct" msgid="8665950426992057191">"电量剩余 <xliff:g id="PERCENT">%1$s</xliff:g> 时"</string>
    <string name="battery_percentage" msgid="723291197508049369">"电池电量百分比"</string>
    <string name="battery_percentage_description" msgid="8511658577507384014">"在状态栏中显示电池电量百分比"</string>
    <string name="process_stats_summary_title" msgid="1144688045609771677">"进程统计信息"</string>
    <string name="process_stats_summary" msgid="109387941605607762">"运行中进程的相关技术统计信息"</string>
    <string name="app_memory_use" msgid="7849258480392171939">"内存用量"</string>
    <string name="process_stats_total_duration" msgid="7417201400853728029">"过去 <xliff:g id="TIMEDURATION">%3$s</xliff:g>内使用了 <xliff:g id="USEDRAM">%1$s</xliff:g>（共 <xliff:g id="TOTALRAM">%2$s</xliff:g>）"</string>
    <string name="process_stats_total_duration_percentage" msgid="6522457033380025618">"<xliff:g id="TIMEDURATION">%2$s</xliff:g>内使用了 <xliff:g id="PERCENT">%1$s</xliff:g> 的内存"</string>
    <string name="process_stats_type_background" msgid="3934992858120683459">"后台"</string>
    <string name="process_stats_type_foreground" msgid="7713118254089580536">"前台"</string>
    <string name="process_stats_type_cached" msgid="6314925846944806511">"缓存"</string>
    <string name="process_stats_os_label" msgid="4813434110442733392">"Android 操作系统"</string>
    <string name="process_stats_os_native" msgid="5322428494231768472">"本地"</string>
    <string name="process_stats_os_kernel" msgid="1938523592369780924">"内核"</string>
    <string name="process_stats_os_zram" msgid="677138324651671575">"Z-RAM"</string>
    <string name="process_stats_os_cache" msgid="6432533624875078233">"缓存"</string>
    <string name="process_stats_ram_use" msgid="976912589127397307">"内存使用量"</string>
    <string name="process_stats_bg_ram_use" msgid="5398191511030462404">"内存使用量（后台）"</string>
    <string name="process_stats_run_time" msgid="6520628955709369115">"运行时间"</string>
    <string name="processes_subtitle" msgid="6827502409379462438">"进程"</string>
    <string name="services_subtitle" msgid="4296402367067266425">"服务"</string>
    <string name="menu_proc_stats_duration" msgid="2323483592994720196">"时间长度"</string>
    <string name="mem_details_title" msgid="6548392825497290498">"内存详情"</string>
    <string name="menu_duration_3h" msgid="4714866438374738385">"3小时"</string>
    <string name="menu_duration_6h" msgid="1940846763432184132">"6小时"</string>
    <string name="menu_duration_12h" msgid="7890465404584356294">"12小时"</string>
    <string name="menu_duration_1d" msgid="3393631127622285458">"1天"</string>
    <string name="menu_show_system" msgid="8864603400415567635">"显示系统进程"</string>
    <string name="menu_hide_system" msgid="4106826741703745733">"隐藏系统进程"</string>
    <string name="menu_show_percentage" msgid="4717204046118199806">"显示百分比"</string>
    <string name="menu_use_uss" msgid="467765290771543089">"使用 USS"</string>
    <string name="menu_proc_stats_type" msgid="4700209061072120948">"统计信息类型"</string>
    <string name="menu_proc_stats_type_background" msgid="2236161340134898852">"后台"</string>
    <string name="menu_proc_stats_type_foreground" msgid="2286182659954958586">"前台"</string>
    <string name="menu_proc_stats_type_cached" msgid="5084272779786820693">"缓存"</string>
    <string name="voice_input_output_settings" msgid="1336135218350444783">"语音输入与输出"</string>
    <string name="voice_input_output_settings_title" msgid="2442850635048676991">"语音输入与输出设置"</string>
    <string name="voice_search_settings_title" msgid="2775469246913196536">"语音搜索"</string>
    <string name="keyboard_settings_title" msgid="5080115226780201234">"Android键盘"</string>
    <string name="voice_input_settings" msgid="1099937800539324567">"语音输入设置"</string>
    <string name="voice_input_settings_title" msgid="2676028028084981891">"语音输入"</string>
    <string name="voice_service_preference_section_title" msgid="3778706644257601021">"语音输入服务"</string>
    <string name="voice_interactor_preference_summary" msgid="1801414022026937190">"全语音启动指令和互动"</string>
    <string name="voice_recognizer_preference_summary" msgid="669880813593690527">"简单的语音转文字"</string>
    <string name="voice_interaction_security_warning" msgid="6378608263983737325">"此语音输入服务能够始终进行语音监测，并能替您控制所有支持语音功能的应用。该服务由“<xliff:g id="VOICE_INPUT_SERVICE_APP_NAME">%s</xliff:g>”提供。要启用此服务吗？"</string>
    <string name="tts_engine_preference_title" msgid="1578826947311494239">"首选引擎"</string>
    <string name="tts_engine_settings_title" msgid="6886964122861384818">"引擎设置"</string>
    <string name="tts_sliders_title" msgid="992059150784095263">"语速和音调"</string>
    <string name="tts_engine_section_title" msgid="6289240207677024034">"引擎"</string>
    <string name="tts_install_voice_title" msgid="6275828614052514320">"语音"</string>
    <string name="tts_spoken_language" msgid="5542499183472504027">"使用的语言"</string>
    <string name="tts_install_voices_title" msgid="8808823756936022641">"安装语音"</string>
    <string name="tts_install_voices_text" msgid="5292606786380069134">"继续使用<xliff:g id="TTS_APP_NAME">%s</xliff:g>应用安装语音"</string>
    <string name="tts_install_voices_open" msgid="667467793360277465">"打开应用"</string>
    <string name="tts_install_voices_cancel" msgid="4711492804851107459">"取消"</string>
    <string name="tts_reset" msgid="2661752909256313270">"重置"</string>
    <string name="tts_play" msgid="2628469503798633884">"播放"</string>
    <string name="gadget_title" msgid="5519037532720577836">"电量控制"</string>
    <string name="gadget_toggle_wifi" msgid="319262861956544493">"正在更新WLAN设置"</string>
    <string name="gadget_toggle_bluetooth" msgid="7538903239807020826">"正在更新蓝牙设置"</string>
    <string name="gadget_state_template" msgid="5156935629902649932">"<xliff:g id="ID_1">%1$s</xliff:g><xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="gadget_state_on" msgid="6909119593004937688">"开启"</string>
    <string name="gadget_state_off" msgid="5220212352953066317">"关闭"</string>
    <string name="gadget_state_turning_on" msgid="3395992057029439039">"正在开启"</string>
    <string name="gadget_state_turning_off" msgid="2395546048102176157">"正在关闭"</string>
    <string name="gadget_wifi" msgid="4712584536500629417">"WLAN"</string>
    <string name="gadget_bluetooth" msgid="8998572807378694410">"蓝牙"</string>
    <string name="gadget_location" msgid="2974757497945178165">"位置信息"</string>
    <string name="gadget_sync" msgid="858895763714222152">"同步"</string>
    <string name="gadget_brightness_template" msgid="930541920933123603">"亮度 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="gadget_brightness_state_auto" msgid="6667967252426515446">"自动"</string>
    <string name="gadget_brightness_state_full" msgid="6814570109772137631">"最亮"</string>
    <string name="gadget_brightness_state_half" msgid="3696671957608774204">"一半"</string>
    <string name="gadget_brightness_state_off" msgid="946382262872753084">"关闭"</string>
    <string name="vpn_settings_title" msgid="5662579425832406705">"VPN"</string>
    <string name="credentials_title" msgid="4446234003860769883">"凭据存储空间"</string>
    <string name="credentials_install" product="nosdcard" msgid="466093273825150847">"从存储设备安装"</string>
    <string name="credentials_install" product="default" msgid="953914549998062317">"从SD卡安装"</string>
    <string name="credentials_install_summary" product="nosdcard" msgid="4220422806818210676">"从存储设备安装证书"</string>
    <string name="credentials_install_summary" product="default" msgid="5737658257407822713">"从SD卡安装证书"</string>
    <string name="credentials_reset" msgid="3239382277144980418">"清除凭据"</string>
    <string name="credentials_reset_summary" msgid="3369361230171260282">"删除所有证书"</string>
    <string name="trusted_credentials" msgid="4266945289534242402">"信任的凭据"</string>
    <string name="trusted_credentials_summary" msgid="6735221351155686632">"显示信任的CA证书"</string>
    <string name="user_credentials" msgid="3719013347787187083">"用户凭据"</string>
    <string name="user_credentials_summary" msgid="7271228342106080167">"查看和修改存储的凭据"</string>
    <string name="advanced_security_title" msgid="2434776238010578865">"高级"</string>
    <string name="credential_storage_type" msgid="8629968543494001364">"存储类型"</string>
    <string name="credential_storage_type_hardware" msgid="6077193544333904427">"硬件支持"</string>
    <string name="credential_storage_type_software" msgid="4403117271207715378">"仅限软件"</string>
    <string name="credentials_settings_not_available" msgid="7968275634486624215">"此用户无法查看或修改凭据"</string>
    <string name="credential_for_vpn_and_apps" msgid="4168197158768443365">"已安装（用于 VPN 和应用）"</string>
    <string name="credential_for_wifi" msgid="6228425986551591864">"已安装（用于 WLAN）"</string>
    <string name="credentials_unlock" msgid="385427939577366499"></string>
    <string name="credentials_unlock_hint" msgid="2301301378040499348">"键入凭据存储的密码。"</string>
    <string name="credentials_old_password" msgid="7553393815538684028">"当前密码："</string>
    <string name="credentials_reset_hint" msgid="6297256880896133631">"要移除所有内容吗？"</string>
    <string name="credentials_wrong_password" msgid="2541932597104054807">"密码不正确。"</string>
    <string name="credentials_reset_warning" msgid="5320653011511797600">"密码不正确。您还可以重试一次，如果输入的密码仍不正确，系统将删除凭据存储。"</string>
    <string name="credentials_reset_warning_plural" msgid="6514085665301095279">"密码不正确。您还可以重试 <xliff:g id="NUMBER">%1$d</xliff:g> 次，如果输入的密码仍不正确，系统将删除凭据存储。"</string>
    <string name="credentials_erased" msgid="2907836028586342969">"凭据存储空间已清空。"</string>
    <string name="credentials_not_erased" msgid="7685932772284216097">"系统无法清除凭据存储。"</string>
    <string name="credentials_enabled" msgid="7588607413349978930">"凭证存储已启用。"</string>
    <string name="credentials_configure_lock_screen_hint" msgid="8058230497337529036">"您的设备必须启用安全锁屏功能，才能使用凭据存储"</string>
    <string name="credentials_configure_lock_screen_button" msgid="253239765216055321">"设置锁定方式"</string>
    <string name="usage_access_title" msgid="332333405495457839">"有权查看使用情况的应用"</string>
    <string name="emergency_tone_title" msgid="254495218194925271">"紧急拨号信号"</string>
    <string name="emergency_tone_summary" msgid="722259232924572153">"设置进行紧急呼救时的行为"</string>
    <string name="privacy_settings_title" msgid="2978878794187459190">"备份"</string>
    <string name="backup_section_title" msgid="7952232291452882740">"备份和还原"</string>
    <string name="personal_data_section_title" msgid="7815209034443782061">"个人数据"</string>
    <string name="backup_data_title" msgid="1239105919852668016">"备份我的数据"</string>
    <string name="backup_data_summary" msgid="708773323451655666">"将应用数据、WLAN密码和其他设置备份到Google服务器"</string>
    <string name="backup_configure_account_title" msgid="3790872965773196615">"备份帐号"</string>
    <string name="include_app_data_title" msgid="2829970132260278394">"包括应用数据"</string>
    <string name="auto_restore_title" msgid="5397528966329126506">"自动还原"</string>
    <string name="auto_restore_summary" msgid="4235615056371993807">"重新安装某个应用后，系统会还原已经备份的设置和数据"</string>
    <string name="backup_inactive_title" msgid="685838037986644604">"备份服务未启用"</string>
    <string name="backup_configure_account_default_summary" msgid="2436933224764745553">"目前没有帐号存储备份数据"</string>
    <string name="backup_erase_dialog_title" msgid="1027640829482174106"></string>
    <string name="backup_erase_dialog_message" msgid="5221011285568343155">"要停止备份您的WLAN密码、书签、其他设置和应用数据，并清除Google服务器上的所有副本吗？"</string>
    <string name="fullbackup_erase_dialog_message" msgid="694766389396659626">"要停止备份设备数据（例如 WLAN 密码和通话记录）和应用数据（例如应用存储的设置和文件），并清除远程服务器上的所有副本吗？"</string>
    <string name="fullbackup_data_summary" msgid="960850365007767734">"自动远程备份设备数据（例如 WLAN 密码和通话记录）和应用数据（例如应用存储的设置和文件）。\n\n启用自动备份功能后，系统会定期远程保存设备和应用数据。应用数据可以是应用根据开发者设置保存的任何数据，包括可能比较敏感的数据（例如通讯录、邮件和照片）。"</string>
    <string name="device_admin_settings_title" msgid="4960761799560705902">"设备管理设置"</string>
    <string name="active_device_admin_msg" msgid="578748451637360192">"设备管理应用"</string>
    <string name="remove_device_admin" msgid="9207368982033308173">"停用此设备管理应用"</string>
    <string name="uninstall_device_admin" msgid="271120195128542165">"卸载应用"</string>
    <string name="remove_and_uninstall_device_admin" msgid="3837625952436169878">"停用并卸载"</string>
    <string name="select_device_admin_msg" msgid="7347389359013278077">"设备管理应用"</string>
    <string name="no_device_admins" msgid="4846602835339095768">"没有可用的设备管理应用"</string>
    <string name="personal_device_admin_title" msgid="2849617316347669861">"个人"</string>
    <string name="managed_device_admin_title" msgid="7853955652864478435">"工作"</string>
    <string name="no_trust_agents" msgid="7450273545568977523">"没有可信代理"</string>
    <string name="add_device_admin_msg" msgid="1501847129819382149">"要启用设备管理应用吗？"</string>
    <string name="add_device_admin" msgid="4192055385312215731">"启用此设备管理应用"</string>
    <string name="device_admin_add_title" msgid="3140663753671809044">"设备管理"</string>
    <string name="device_admin_warning" msgid="7482834776510188134">"启用此管理应用将允许应用“<xliff:g id="APP_NAME">%1$s</xliff:g>”执行以下操作："</string>
    <string name="device_admin_status" msgid="7234814785374977990">"此管理应用已启用，且允许应用“<xliff:g id="APP_NAME">%1$s</xliff:g>”执行以下操作："</string>
    <string name="profile_owner_add_title" msgid="6249331160676175009">"要激活个人资料管理器吗？"</string>
    <string name="adding_profile_owner_warning" msgid="1354474524852805802">"如果继续操作，您所设的用户将由您的管理员进行管理。除了您的个人数据之外，管理员可能还会存储其他相关数据。\n\n您的管理员能够监控和管理与此用户相关的设置、权限、应用以及数据（其中包括网络活动和您设备的位置信息）。"</string>
    <string name="admin_disabled_other_options" msgid="7712694507069054530">"其他选项已被您的管理员停用"</string>
    <string name="admin_more_details" msgid="7901420667346456102">"更多详情"</string>
    <string name="sound_category_sound_title" msgid="1488759370067953996">"常规"</string>
    <string name="notification_log_title" msgid="3766148588239398464">"通知日志"</string>
    <string name="sound_category_call_ringtone_vibrate_title" msgid="1543777228646645163">"来电铃声和振动"</string>
    <string name="sound_category_system_title" msgid="1480844520622721141">"系统"</string>
    <string name="wifi_setup_title" msgid="2970260757780025029">"WLAN设置"</string>
    <string name="wifi_setup_title_editing_network" msgid="6020614644556717979">"连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”"</string>
    <string name="wifi_setup_title_connecting_network" msgid="5572226790101017822">"正在连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”…"</string>
    <string name="wifi_setup_title_connected_network" msgid="1608788657122010919">"已连接到WLAN网络“<xliff:g id="NETWORK_NAME">%s</xliff:g>”"</string>
    <string name="wifi_setup_title_add_network" msgid="6932651000151032301">"添加网络"</string>
    <string name="wifi_setup_not_connected" msgid="6997432604664057052">"未连接"</string>
    <string name="wifi_setup_add_network" msgid="5939624680150051807">"添加网络"</string>
    <string name="wifi_setup_refresh_list" msgid="3411615711486911064">"刷新列表"</string>
    <string name="wifi_setup_skip" msgid="6661541841684895522">"跳过"</string>
    <string name="wifi_setup_next" msgid="3388694784447820477">"下一步"</string>
    <string name="wifi_setup_back" msgid="144777383739164044">"上一步"</string>
    <string name="wifi_setup_detail" msgid="2336990478140503605">"网络详情"</string>
    <string name="wifi_setup_connect" msgid="7954456989590237049">"连接"</string>
    <string name="wifi_setup_forget" msgid="2562847595567347526">"取消保存"</string>
    <string name="wifi_setup_save" msgid="3659235094218508211">"保存"</string>
    <string name="wifi_setup_cancel" msgid="3185216020264410239">"取消"</string>
    <string name="wifi_setup_status_scanning" msgid="5317003416385428036">"正在扫描网络..."</string>
    <string name="wifi_setup_status_select_network" msgid="3960480613544747397">"点按某个网络即可与其建立连接"</string>
    <string name="wifi_setup_status_existing_network" msgid="6394925174802598186">"连接到现有网络"</string>
    <string name="wifi_setup_status_unsecured_network" msgid="8143046977328718252">"连接到不受保护的网络"</string>
    <string name="wifi_setup_status_edit_network" msgid="4765340816724760717">"键入网络配置"</string>
    <string name="wifi_setup_status_new_network" msgid="7468952850452301083">"连接到新的网络"</string>
    <string name="wifi_setup_status_connecting" msgid="4971421484401530740">"正在连接..."</string>
    <string name="wifi_setup_status_proceed_to_next" msgid="6708250000342940031">"转至下一步"</string>
    <string name="wifi_setup_status_eap_not_supported" msgid="6796317704783144190">"不支持EAP。"</string>
    <string name="wifi_setup_eap_not_supported" msgid="6812710317883658843">"您无法在初次设置过程中配置EAP WLAN连接。初次设置完毕后，您可以在“设置”&gt;“无线和网络”中进行配置。"</string>
    <string name="wifi_setup_description_connecting" msgid="2793554932006756795">"建立连接可能需要几分钟时间..."</string>
    <string name="wifi_setup_description_connected" msgid="6649168170073219153">"点按"<b>"下一步"</b>"即可继续设置。\n\n点按"<b>"返回"</b>"可连接到其他 WLAN 网络。"</string>
    <string name="accessibility_sync_enabled" msgid="558480439730263116">"同步功能已启用"</string>
    <string name="accessibility_sync_disabled" msgid="1741194106479011384">"未启用同步功能"</string>
    <string name="accessibility_sync_in_progress" msgid="4501160520879902723">"正在同步"</string>
    <string name="accessibility_sync_error" msgid="8703299118794272041">"同步错误。"</string>
    <string name="sync_failed" msgid="1696499856374109647">"同步失败"</string>
    <string name="sync_active" msgid="8476943765960863040">"正在同步"</string>
    <string name="account_sync_settings_title" msgid="5131314922423053588">"同步"</string>
    <string name="sync_is_failing" msgid="1591561768344128377">"同步操作当前遇到了一些问题，很快便可恢复。"</string>
    <string name="add_account_label" msgid="7811707265834013767">"添加帐号"</string>
    <string name="managed_profile_not_available_label" msgid="852263300911325904">"工作资料尚不可用"</string>
    <string name="work_mode_label" msgid="7157582467956920750">"工作资料"</string>
    <string name="work_mode_on_summary" msgid="3628349169847990263">"由贵单位管理"</string>
    <string name="work_mode_off_summary" msgid="2657138190560082508">"应用和通知均已关闭"</string>
    <string name="remove_managed_profile_label" msgid="3856519337797285325">"移除工作资料"</string>
    <string name="background_data" msgid="5779592891375473817">"后台流量"</string>
    <string name="background_data_summary" msgid="8328521479872763452">"应用可以随时同步、发送和接收数据"</string>
    <string name="background_data_dialog_title" msgid="6059217698124786537">"要关闭后台流量吗？"</string>
    <string name="background_data_dialog_message" msgid="6981661606680941633">"停用后台流量可延长电池使用时间并减少流量耗用，但某些应用可能仍会使用后台数据网络。"</string>
    <string name="sync_automatically" msgid="1682730255435062059">"自动同步应用数据"</string>
    <string name="sync_enabled" msgid="4551148952179416813">"同步功能已开启"</string>
    <string name="sync_disabled" msgid="8511659877596511991">"同步功能已关闭"</string>
    <string name="sync_error" msgid="5060969083117872149">"同步出错"</string>
    <string name="last_synced" msgid="4242919465367022234">"上次同步时间：<xliff:g id="LAST_SYNC_TIME">%1$s</xliff:g>"</string>
    <string name="sync_in_progress" msgid="5151314196536070569">"正在同步…"</string>
    <string name="settings_backup" msgid="2274732978260797031">"备份设置"</string>
    <string name="settings_backup_summary" msgid="7916877705938054035">"备份我的设置"</string>
    <string name="sync_menu_sync_now" msgid="6154608350395805683">"立即同步"</string>
    <string name="sync_menu_sync_cancel" msgid="8292379009626966949">"取消同步"</string>
    <string name="sync_one_time_sync" msgid="3733796114909082260">"点按即可立即同步<xliff:g id="LAST_SYNC_TIME">
%1$s</xliff:g>"</string>
    <string name="sync_gmail" msgid="714886122098006477">"Gmail"</string>
    <string name="sync_calendar" msgid="9056527206714733735">"日历"</string>
    <string name="sync_contacts" msgid="9174914394377828043">"联系人"</string>
    <string name="sync_plug" msgid="3905078969081888738"><font fgcolor="#ffffffff">"欢迎使用 Google Sync！"</font>" \n这款数据同步工具可让您随时随地查看您的联系人、约会以及更多信息。"</string>
    <string name="header_application_sync_settings" msgid="6205903695598000286">"应用同步设置"</string>
    <string name="header_data_and_synchronization" msgid="5165024023936509896">"数据与同步"</string>
    <string name="preference_change_password_title" msgid="8955581790270130056">"更改密码"</string>
    <string name="header_account_settings" msgid="5382475087121880626">"帐号设置"</string>
    <string name="remove_account_label" msgid="5921986026504804119">"移除帐号"</string>
    <string name="header_add_an_account" msgid="756108499532023798">"添加帐号"</string>
    <string name="finish_button_label" msgid="481587707657751116">"完成"</string>
    <string name="really_remove_account_title" msgid="8800653398717172460">"要移除帐号吗？"</string>
    <string name="really_remove_account_message" product="tablet" msgid="1936147502815641161">"移除该帐号会从平板电脑中删除所有相关的邮件、联系人以及其他数据。"</string>
    <string name="really_remove_account_message" product="default" msgid="3483528757922948356">"移除该帐号会从手机中删除所有相关的邮件、联系人以及其它数据。"</string>
    <string name="really_remove_account_message" product="device" msgid="7507474724882080166">"移除该帐号后，设备上的相关消息、联系人和其他数据也将全部删除！"</string>
    <string name="remove_account_failed" msgid="3901397272647112455">"您的管理员不允许进行这项更改"</string>
    <string name="cant_sync_dialog_title" msgid="2777238588398046285">"无法手动同步"</string>
    <string name="cant_sync_dialog_message" msgid="1938380442159016449">"此项内容的同步功能目前未开启。要更改此设置，请暂时开启后台流量和自动同步功能。"</string>
    <string name="enter_password" msgid="8035706727471334122">"要启动Android，请输入您的密码"</string>
    <string name="enter_pin" msgid="5305333588093263790">"要启动Android，请输入您的PIN码"</string>
    <string name="enter_pattern" msgid="4187435713036808566">"要启动Android，请绘制您的解锁图案"</string>
    <string name="cryptkeeper_wrong_pattern" msgid="8423835922362956999">"图案错误"</string>
    <string name="cryptkeeper_wrong_password" msgid="5200857195368904047">"密码错误"</string>
    <string name="cryptkeeper_wrong_pin" msgid="755720788765259382">"PIN 码错误"</string>
    <string name="checking_decryption" msgid="8287458611802609493">"正在检查…"</string>
    <string name="starting_android" msgid="4001324195902252681">"正在启动 Android…"</string>
    <string name="delete" msgid="4219243412325163003">"删除"</string>
    <string name="misc_files" msgid="6720680815969643497">"其他文件"</string>
    <string name="misc_files_selected_count" msgid="4647048020823912088">"已选择 <xliff:g id="NUMBER">%1$d</xliff:g> 个，共 <xliff:g id="TOTAL">%2$d</xliff:g> 个"</string>
    <string name="misc_files_selected_count_bytes" msgid="2876232009069114352">"<xliff:g id="NUMBER">%1$s</xliff:g>，共 <xliff:g id="TOTAL">%2$s</xliff:g>"</string>
    <string name="select_all" msgid="1562774643280376715">"全选"</string>
    <string name="data_usage_summary_title" msgid="3804110657238092929">"流量使用情况"</string>
    <string name="data_usage_app_summary_title" msgid="4147258989837459172">"应用的流量使用情况"</string>
    <string name="data_usage_accounting" msgid="7170028915873577387">"运营商的流量计算方式可能与您设备的计算方式不同。"</string>
    <string name="data_usage_app" msgid="4970478397515423303">"应用使用情况"</string>
    <string name="data_usage_app_info_label" msgid="3409931235687866706">"应用信息"</string>
    <string name="data_usage_cellular_data" msgid="9168928285122125137">"移动数据"</string>
    <string name="data_usage_data_limit" msgid="1193930999713192703">"设置流量上限"</string>
    <string name="data_usage_cycle" msgid="5652529796195787949">"流量消耗重置周期"</string>
    <string name="data_usage_app_items_header_text" msgid="5017850810459372828">"应用数据流量"</string>
    <string name="data_usage_menu_roaming" msgid="8042359966835203296">"移动数据网络漫游"</string>
    <string name="data_usage_menu_restrict_background" msgid="1989394568592253331">"限制后台流量"</string>
    <string name="data_usage_menu_allow_background" msgid="2694761978633359223">"允许使用后台流量"</string>
    <string name="data_usage_menu_split_4g" msgid="5322857680792601899">"单独显示4G流量"</string>
    <string name="data_usage_menu_show_wifi" msgid="2296217964873872571">"显示WLAN流量"</string>
    <string name="data_usage_menu_hide_wifi" msgid="7290056718050186769">"隐藏WLAN流量"</string>
    <string name="data_usage_menu_show_ethernet" msgid="5181361208532314097">"显示有线网络流量"</string>
    <string name="data_usage_menu_hide_ethernet" msgid="3326702187179943681">"隐藏有线网络流量"</string>
    <string name="data_usage_menu_metered" msgid="6235119991372755026">"网络限制"</string>
    <string name="data_usage_menu_auto_sync" msgid="8203999775948778560">"自动同步数据"</string>
    <string name="data_usage_menu_sim_cards" msgid="6410498422797244073">"SIM 卡"</string>
    <string name="data_usage_menu_cellular_networks" msgid="8339835014751511300">"移动网络"</string>
    <string name="data_usage_cellular_data_summary" msgid="8413357481361268285">"已暂停（达到数据流量上限）"</string>
    <string name="account_settings_menu_auto_sync" msgid="6243013719753700377">"自动同步数据"</string>
    <string name="account_settings_menu_auto_sync_personal" msgid="785541379617346438">"自动同步个人帐号数据"</string>
    <string name="account_settings_menu_auto_sync_work" msgid="329565580969147026">"自动同步工作帐号数据"</string>
    <string name="data_usage_change_cycle" msgid="7776556448920114866">"更改周期..."</string>
    <string name="data_usage_pick_cycle_day" msgid="4470796861757050966">"每月流量消耗重计日期："</string>
    <string name="data_usage_empty" msgid="8621855507876539282">"没有任何应用在此期间产生过数据流量。"</string>
    <string name="data_usage_label_foreground" msgid="4938034231928628164">"前台"</string>
    <string name="data_usage_label_background" msgid="3225844085975764519">"后台"</string>
    <string name="data_usage_app_restricted" msgid="3568465218866589705">"受限"</string>
    <string name="data_usage_disable_mobile" msgid="8656552431969276305">"要关闭移动数据网络吗？"</string>
    <string name="data_usage_disable_mobile_limit" msgid="4644364396844393848">"设置移动数据流量上限"</string>
    <string name="data_usage_disable_4g_limit" msgid="6233554774946681175">"设置 4G 数据流量上限"</string>
    <string name="data_usage_disable_3g_limit" msgid="2558557840444266906">"设置 2G-3G 数据流量上限"</string>
    <string name="data_usage_disable_wifi_limit" msgid="1394901415264660888">"设置 WLAN 流量上限"</string>
    <string name="data_usage_tab_wifi" msgid="481146038146585749">"WLAN"</string>
    <string name="data_usage_tab_ethernet" msgid="7298064366282319911">"有线网络"</string>
    <string name="data_usage_tab_mobile" msgid="454140350007299045">"移动"</string>
    <string name="data_usage_tab_4g" msgid="1301978716067512235">"4G"</string>
    <string name="data_usage_tab_3g" msgid="6092169523081538718">"2G-3G"</string>
    <string name="data_usage_list_mobile" msgid="5588685410495019866">"移动网络"</string>
    <string name="data_usage_list_none" msgid="3933892774251050735">"无"</string>
    <string name="data_usage_enable_mobile" msgid="986782622560157977">"移动数据"</string>
    <string name="data_usage_enable_3g" msgid="6304006671869578254">"2G-3G 数据"</string>
    <string name="data_usage_enable_4g" msgid="3635854097335036738">"4G 数据"</string>
    <string name="data_roaming_enable_mobile" msgid="1523331545457578362">"漫游"</string>
    <string name="data_usage_forground_label" msgid="7654319010655983591">"前台："</string>
    <string name="data_usage_background_label" msgid="2722008379947694926">"后台："</string>
    <string name="data_usage_app_settings" msgid="2279171379771253165">"应用设置"</string>
    <string name="data_usage_app_restrict_background" msgid="7359227831562303223">"后台数据"</string>
    <string name="data_usage_app_restrict_background_summary" msgid="5853552187570622572">"允许在后台使用移动数据"</string>
    <string name="data_usage_app_restrict_background_summary_disabled" msgid="7401927377070755054">"要限制此应用使用后台流量，请先设置移动数据流量上限。"</string>
    <string name="data_usage_app_restrict_dialog_title" msgid="1613108390242737923">"要限制后台流量吗？"</string>
    <string name="data_usage_app_restrict_dialog" msgid="1466689968707308512">"只能连接到移动网络时，此功能可能会导致需要使用后台流量的应用无法正常运行。\n\n您可以在相关应用的设置中为其设定更合适的数据流量控制选项。"</string>
    <string name="data_usage_restrict_denied_dialog" msgid="55012417305745608">"您必须先设置移动数据流量上限，才能限制后台数据流量。"</string>
    <string name="data_usage_auto_sync_on_dialog_title" msgid="2438617846762244389">"要打开自动同步数据功能吗？"</string>
    <string name="data_usage_auto_sync_on_dialog" product="tablet" msgid="8581983093524041669">"您在网络上对自己的帐号进行的所有更改都会自动同步到您的平板电脑。\n\n有些帐号还可以将您在平板电脑上进行的所有更改自动同步到网络上。Google 帐号就支持此类双向同步。"</string>
    <string name="data_usage_auto_sync_on_dialog" product="default" msgid="8651376294887142858">"您在网络上对自己的帐号进行的所有更改都会自动同步到您的手机。\n\n有些帐号还可以将您在手机上进行的所有更改自动同步到网络上。Google 帐号就支持此类双向同步。"</string>
    <string name="data_usage_auto_sync_off_dialog_title" msgid="9013139130490125793">"要关闭自动同步数据功能吗？"</string>
    <string name="data_usage_auto_sync_off_dialog" msgid="4025938250775413864">"这样可以节省数据流量和电池电量，但您需要手动同步每个帐号才能获得最新信息，并且在有更新时不会收到通知。"</string>
    <string name="data_usage_cycle_editor_title" msgid="1373797281540188533">"流量消耗重置日期"</string>
    <string name="data_usage_cycle_editor_subtitle" msgid="5512903797979928416">"日期："</string>
    <string name="data_usage_cycle_editor_positive" msgid="8821760330497941117">"设置"</string>
    <string name="data_usage_warning_editor_title" msgid="3704136912240060339">"设置流量用量警告"</string>
    <string name="data_usage_limit_editor_title" msgid="9153595142385030015">"设置流量使用上限"</string>
    <string name="data_usage_limit_dialog_title" msgid="3023111643632996097">"限制流量用量"</string>
    <string name="data_usage_limit_dialog_mobile" product="tablet" msgid="4983487893343645667">"当移动数据流量的使用量达到您设置的上限时，您的平板电脑将关闭移动数据网络。\n\n由于流量用量是由您的平板电脑计算的，而您的运营商对流量用量的计算方式可能有所不同，因此建议您设置一个保守的上限值。"</string>
    <string name="data_usage_limit_dialog_mobile" product="default" msgid="3926320594049434225">"当移动数据流量的使用量达到您设置的上限时，您的手机将关闭移动数据网络。\n\n由于流量用量是由您的手机计算的，而您的运营商对流量用量的计算方式可能有所不同，因此建议您设置一个保守的上限值。"</string>
    <string name="data_usage_restrict_background_title" msgid="2201315502223035062">"要限制后台流量吗？"</string>
    <string name="data_usage_restrict_background" msgid="434093644726734586">"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。"</string>
    <string name="data_usage_restrict_background_multiuser" product="tablet" msgid="7096707497743363380">"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。\n\n此设置会影响这部平板电脑上的所有用户。"</string>
    <string name="data_usage_restrict_background_multiuser" product="default" msgid="7910798414964288424">"如果您限制后台移动数据流量，则只有在您连接到 WLAN 网络时，部分应用和服务才能正常运行。\n\n此设置会影响这部手机上的所有用户。"</string>
    <string name="data_usage_sweep_warning" msgid="6387081852568846982"><font size="18">"<xliff:g id="NUMBER">^1</xliff:g>"</font>" "<font size="9">"<xliff:g id="UNIT">^2</xliff:g>"</font>\n<font size="12">"警告"</font></string>
    <string name="data_usage_sweep_limit" msgid="860566507375933039"><font size="18">"<xliff:g id="NUMBER">^1</xliff:g>"</font>" "<font size="9">"<xliff:g id="UNIT">^2</xliff:g>"</font>\n<font size="12">"上限"</font></string>
    <string name="data_usage_uninstalled_apps" msgid="614263770923231598">"已删除的应用"</string>
    <string name="data_usage_uninstalled_apps_users" msgid="7986294489899813194">"已删除的应用和用户"</string>
    <string name="data_usage_received_sent" msgid="5039699009276621757">"已接收<xliff:g id="RECEIVED">%1$s</xliff:g>，已发送<xliff:g id="SENT">%2$s</xliff:g>"</string>
    <string name="data_usage_total_during_range" msgid="4091294280619255237">"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>。"</string>
    <string name="data_usage_total_during_range_mobile" product="tablet" msgid="1925687342154538972">"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>，由您的平板电脑计算得出。您的运营商对于流量的计算方法可能有所不同。"</string>
    <string name="data_usage_total_during_range_mobile" product="default" msgid="5063981061103812900">"<xliff:g id="RANGE">%2$s</xliff:g>：已使用约 <xliff:g id="TOTAL">%1$s</xliff:g>，由您的手机计算得出。您的运营商对于流量的计算方法可能有所不同。"</string>
    <string name="data_usage_metered_title" msgid="7383175371006596441">"网络限制"</string>
    <string name="data_usage_metered_body" msgid="7655851702771342507">"当后台数据流量受到限制时，系统会将按流量计费的网络视为移动网络。在使用这类网络下载大量数据前，应用可能会发出警告消息。"</string>
    <string name="data_usage_metered_mobile" msgid="5423305619126978393">"移动网络"</string>
    <string name="data_usage_metered_wifi" msgid="1761738002328299714">"按流量计费的WLAN网络"</string>
    <string name="data_usage_metered_wifi_disabled" msgid="727808462375941567">"要选择按流量计费的网络，请开启WLAN网络。"</string>
    <string name="data_usage_metered_auto" msgid="1262028400911918865">"自动"</string>
    <string name="data_usage_metered_yes" msgid="9217539611385225894">"按流量计费"</string>
    <string name="data_usage_metered_no" msgid="4025232961929071789">"不按流量计费"</string>
    <string name="data_usage_disclaimer" msgid="6887858149980673444">"运营商的流量计算方式可能与您设备的计算方式不同。"</string>
    <string name="cryptkeeper_emergency_call" msgid="198578731586097145">"紧急呼救"</string>
    <string name="cryptkeeper_return_to_call" msgid="5613717339452772491">"返回通话"</string>
    <string name="vpn_name" msgid="4689699885361002297">"名称"</string>
    <string name="vpn_type" msgid="5435733139514388070">"类型"</string>
    <string name="vpn_server" msgid="2123096727287421913">"服务器地址"</string>
    <string name="vpn_mppe" msgid="6639001940500288972">"PPP 加密 (MPPE)"</string>
    <string name="vpn_l2tp_secret" msgid="529359749677142076">"L2TP 密钥"</string>
    <string name="vpn_ipsec_identifier" msgid="4098175859460006296">"IPSec 标识符"</string>
    <string name="vpn_ipsec_secret" msgid="4526453255704888704">"IPSec 预共享密钥"</string>
    <string name="vpn_ipsec_user_cert" msgid="6880651510020187230">"IPSec 用户证书"</string>
    <string name="vpn_ipsec_ca_cert" msgid="91338213449148229">"IPSec CA证书"</string>
    <string name="vpn_ipsec_server_cert" msgid="6599276718456935010">"IPSec 服务器证书"</string>
    <string name="vpn_show_options" msgid="7182688955890457003">"显示高级选项"</string>
    <string name="vpn_search_domains" msgid="5391995501541199624">"DNS搜索网域"</string>
    <string name="vpn_dns_servers" msgid="5570715561245741829">"DNS服务器（例如8.8.8.8）"</string>
    <string name="vpn_routes" msgid="3818655448226312232">"转发路线（例如10.0.0.0/8）"</string>
    <string name="vpn_username" msgid="1863901629860867849">"用户名"</string>
    <string name="vpn_password" msgid="6756043647233596772">"密码"</string>
    <string name="vpn_save_login" msgid="6350322456427484881">"保存帐号信息"</string>
    <string name="vpn_not_used" msgid="9094191054524660891">"（未使用）"</string>
    <string name="vpn_no_ca_cert" msgid="8776029412793353361">"（不验证服务器）"</string>
    <string name="vpn_no_server_cert" msgid="2167487440231913330">"（来自服务器）"</string>
    <string name="vpn_always_on_invalid_reason_type" msgid="7574518311224455825">"此 VPN 类型无法随时保持连接"</string>
    <string name="vpn_always_on_invalid_reason_server" msgid="477304620899799383">"始终开启的 VPN 仅支持数字格式的服务器地址"</string>
    <string name="vpn_always_on_invalid_reason_no_dns" msgid="2226648961940273294">"必须为始终开启的 VPN 指定 DNS 服务器"</string>
    <string name="vpn_always_on_invalid_reason_dns" msgid="3551394495620249972">"DNS 服务器地址必须为数字才能使用始终开启的 VPN"</string>
    <string name="vpn_always_on_invalid_reason_other" msgid="5959352052515258208">"输入的信息不支持始终开启的 VPN"</string>
    <string name="vpn_cancel" msgid="1979937976123659332">"取消"</string>
    <string name="vpn_done" msgid="8678655203910995914">"关闭"</string>
    <string name="vpn_save" msgid="4233484051644764510">"保存"</string>
    <string name="vpn_connect" msgid="8469608541746132301">"连接"</string>
    <string name="vpn_replace" msgid="5442836256121957861">"替换"</string>
    <string name="vpn_edit" msgid="8647191407179996943">"编辑 VPN 配置文件"</string>
    <string name="vpn_forget" msgid="3684651372749415446">"取消保存"</string>
    <string name="vpn_connect_to" msgid="5965299358485793260">"连接到<xliff:g id="PROFILE">%s</xliff:g>"</string>
    <string name="vpn_disconnect_confirm" msgid="3743970132487505659">"要断开与此 VPN 的连接吗？"</string>
    <string name="vpn_disconnect" msgid="7426570492642111171">"断开连接"</string>
    <string name="vpn_version" msgid="1939804054179766249">"版本 <xliff:g id="VERSION">%s</xliff:g>"</string>
    <string name="vpn_forget_long" msgid="2232239391189465752">"取消保存 VPN"</string>
    <string name="vpn_replace_vpn_title" msgid="2963898301277610248">"要替换现有 VPN 吗？"</string>
    <string name="vpn_set_vpn_title" msgid="4009987321156037267">"要设置始终开启的 VPN 吗？"</string>
    <string name="vpn_first_always_on_vpn_message" msgid="7144543717673197102">"开启这项设置后，在 VPN 成功连接之前，您将无法连接到互联网"</string>
    <string name="vpn_replace_always_on_vpn_enable_message" msgid="798121133114824006">"系统将替换现有 VPN，而且在 VPN 成功连接之前，您将无法连接到互联网"</string>
    <string name="vpn_replace_always_on_vpn_disable_message" msgid="3011818750025879902">"您已连接到某个始终开启的 VPN。如果您要连接到其他 VPN，则系统将替换现有 VPN，并关闭始终开启模式。"</string>
    <string name="vpn_replace_vpn_message" msgid="5611635724578812860">"您已连接到 VPN。如果您要连接到其他 VPN，则系统将替换现有 VPN。"</string>
    <string name="vpn_turn_on" msgid="2363136869284273872">"开启"</string>
    <string name="vpn_cant_connect_title" msgid="4517706987875907511">"无法连接到<xliff:g id="VPN_NAME">%1$s</xliff:g>"</string>
    <string name="vpn_cant_connect_message" msgid="1352832123114214283">"此应用不支持始终开启的 VPN"</string>
    <string name="vpn_title" msgid="6317731879966640551">"VPN"</string>
    <string name="vpn_create" msgid="5628219087569761496">"添加VPN配置文件"</string>
    <string name="vpn_menu_edit" msgid="408275284159243490">"修改配置文件"</string>
    <string name="vpn_menu_delete" msgid="8098021690546891414">"删除配置文件"</string>
    <string name="vpn_menu_lockdown" msgid="7863024538064268139">"始终开启的 VPN"</string>
    <string name="vpn_no_vpns_added" msgid="5002741367858707244">"尚未添加任何 VPN"</string>
    <string name="vpn_always_on_summary" msgid="2821344524094363617">"随时和 VPN 保持连接"</string>
    <string name="vpn_always_on_summary_not_supported" msgid="592304911378771510">"不受此应用支持"</string>
    <string name="vpn_always_on_summary_active" msgid="8800736191241875669">"已启用“始终开启”模式"</string>
    <string name="vpn_require_connection" msgid="8388183166574269666">"屏蔽未使用 VPN 的所有连接"</string>
    <string name="vpn_require_connection_title" msgid="159053539340576331">"需要连接 VPN？"</string>
    <string name="vpn_lockdown_summary" msgid="2200032066376720339">"选择要始终保持连接的VPN配置文件。只有在连接到此VPN之后才可以使用网络。"</string>
    <string name="vpn_lockdown_none" msgid="9214462857336483711">"无"</string>
    <string name="vpn_lockdown_config_error" msgid="3898576754914217248">"始终开启的 VPN 需要服务器和 DNS 的 IP 地址。"</string>
    <string name="vpn_no_network" msgid="3050233675132726155">"目前没有网络连接。请稍后重试。"</string>
    <string name="vpn_disconnected" msgid="280531508768927471">"VPN 连接已断开"</string>
    <string name="vpn_disconnected_summary" msgid="3082851661207900606">"无"</string>
    <string name="vpn_missing_cert" msgid="5357192202207234745">"证书缺失。请尝试修改个人资料。"</string>
    <string name="trusted_credentials_system_tab" msgid="3984284264816924534">"系统"</string>
    <string name="trusted_credentials_user_tab" msgid="2244732111398939475">"用户"</string>
    <string name="trusted_credentials_disable_label" msgid="3864493185845818506">"停用"</string>
    <string name="trusted_credentials_enable_label" msgid="2498444573635146913">"启用"</string>
    <string name="trusted_credentials_remove_label" msgid="3633691709300260836">"删除"</string>
    <string name="trusted_credentials_trust_label" msgid="8003264222650785429">"信任"</string>
    <string name="trusted_credentials_enable_confirmation" msgid="83215982842660869">"要启用该系统CA证书吗？"</string>
    <string name="trusted_credentials_disable_confirmation" msgid="8199697813361646792">"要停用该系统CA证书吗？"</string>
    <string name="trusted_credentials_remove_confirmation" msgid="443561923016852941">"要永久移除该用户CA证书吗？"</string>
    <string name="credential_contains" msgid="3984922924723974084">"此条目包含："</string>
    <string name="one_userkey" msgid="6034020579534914349">"一个用户密钥"</string>
    <string name="one_usercrt" msgid="2150319011101639509">"一个用户证书"</string>
    <string name="one_cacrt" msgid="6844397037970164809">"1 个 CA 证书"</string>
    <string name="n_cacrts" msgid="5979300323482053820">"%d 个 CA 证书"</string>
    <string name="user_credential_title" msgid="1954061209643070652">"凭据详情"</string>
    <string name="user_credential_removed" msgid="6514189495799401838">"已移除以下凭据：<xliff:g id="CREDENTIAL_NAME">%s</xliff:g>"</string>
    <string name="user_credential_none_installed" msgid="3729607560420971841">"未安装任何用户凭据"</string>
    <string name="spellcheckers_settings_title" msgid="399981228588011501">"拼写检查工具"</string>
    <string name="current_backup_pw_prompt" msgid="7735254412051914576">"在此键入您当前的完整备份密码"</string>
    <string name="new_backup_pw_prompt" msgid="8755501377391998428">"在此键入新的完整备份密码"</string>
    <string name="confirm_new_backup_pw_prompt" msgid="3238728882512787864">"在此重新键入新的完整备份密码"</string>
    <string name="backup_pw_set_button_text" msgid="2387480910044648795">"设置备份密码"</string>
    <string name="backup_pw_cancel_button_text" msgid="8845630125391744615">"取消"</string>
    <string name="additional_system_update_settings_list_item_title" msgid="214987609894661992">"其他系统更新"</string>
    <string name="ssl_ca_cert_warning" msgid="2045866713601984673">"网络可能会受到监控"</string>
    <string name="done_button" msgid="1991471253042622230">"完成"</string>
    <plurals name="ssl_ca_cert_dialog_title" formatted="false" msgid="7145092748045794650">
      <item quantity="other">信任或移除证书</item>
      <item quantity="one">信任或移除证书</item>
    </plurals>
    <plurals name="ssl_ca_cert_info_message_device_owner" formatted="false" msgid="1489335297837656666">
      <item quantity="other"><xliff:g id="MANAGING_DOMAIN_1">%s</xliff:g> 已在您的设备上安装多个证书授权中心，它们可用于监控您的设备网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解这些证书，请与您的管理员联系。</item>
      <item quantity="one"><xliff:g id="MANAGING_DOMAIN_0">%s</xliff:g> 已在您的设备上安装一个证书授权中心，它可用于监控您的设备网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解此证书，请与您的管理员联系。</item>
    </plurals>
    <plurals name="ssl_ca_cert_info_message" formatted="false" msgid="30645643499556573">
      <item quantity="other"><xliff:g id="MANAGING_DOMAIN_1">%s</xliff:g> 已为您的工作资料安装多个证书授权中心，它们可用于监控您的工作网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解这些证书，请与您的管理员联系。</item>
      <item quantity="one"><xliff:g id="MANAGING_DOMAIN_0">%s</xliff:g> 已为您的工作资料安装一个证书授权中心，它可用于监控您的工作网络活动（包括使用电子邮件、应用和安全网站）。\n\n如需详细了解此证书，请与您的管理员联系。</item>
    </plurals>
    <string name="ssl_ca_cert_warning_message" msgid="8216218659139190498">"第三方可以监控您的网络活动，包括收发电子邮件、使用应用和浏览安全网站。\n\n出现这种情况的原因是您在设备上安装了信任的凭据。"</string>
    <plurals name="ssl_ca_cert_settings_button" formatted="false" msgid="2426799352517325228">
      <item quantity="other">检查证书</item>
      <item quantity="one">检查证书</item>
    </plurals>
    <string name="user_settings_title" msgid="3493908927709169019">"多用户"</string>
    <string name="user_list_title" msgid="7937158411137563543">"用户和个人资料"</string>
    <string name="user_add_user_or_profile_menu" msgid="6923838875175259418">"添加用户或个人资料"</string>
    <string name="user_add_user_menu" msgid="1675956975014862382">"添加用户"</string>
    <string name="user_summary_restricted_profile" msgid="6354966213806839107">"受限个人资料"</string>
    <string name="user_need_lock_message" msgid="5879715064416886811">"您需要先设置锁定屏幕来保护您的应用和个人数据，然后才可以创建受限个人资料。"</string>
    <string name="user_set_lock_button" msgid="8311219392856626841">"设置屏幕锁定方式"</string>
    <string name="user_summary_not_set_up" msgid="8778205026866794909">"未设置"</string>
    <string name="user_summary_restricted_not_set_up" msgid="1628116001964325544">"未设置 - 受限个人资料"</string>
    <string name="user_summary_managed_profile_not_set_up" msgid="1659125858619760573">"未设置 - 工作资料"</string>
    <string name="user_admin" msgid="993402590002400782">"管理员"</string>
    <string name="user_you" msgid="1639158809315025986">"您（<xliff:g id="NAME">%s</xliff:g>）"</string>
    <string name="user_nickname" msgid="5148818000228994488">"昵称"</string>
    <string name="user_add_user_type_title" msgid="2146438670792322349">"添加"</string>
    <string name="user_add_max_count" msgid="5405885348463433157">"您最多可添加 <xliff:g id="USER_COUNT">%1$d</xliff:g> 位用户"</string>
    <string name="user_add_user_item_summary" msgid="4702776187132008661">"用户拥有个人专属的应用和内容"</string>
    <string name="user_add_profile_item_summary" msgid="5931663986889138941">"您可以限制其他人使用来自您的帐号的应用和内容"</string>
    <string name="user_add_user_item_title" msgid="8212199632466198969">"用户"</string>
    <string name="user_add_profile_item_title" msgid="8353515490730363621">"受限个人资料"</string>
    <string name="user_add_user_title" msgid="2108112641783146007">"要添加新用户吗？"</string>
    <string name="user_add_user_message_long" msgid="6768718238082929201">"创建新用户后，您就能够与其他人共用此设备。每位用户都有自己的专属空间，而且在自己的个人空间内还可以自行安装自己想要的应用，设置壁纸等。此外，用户还可以调整会影响所有用户的设备设置（例如 WLAN 设置）。\n\n当您添加新用户后，该用户需要自行设置个人空间。\n\n任何用户都可以为所有其他用户更新应用。无障碍功能设置和服务可能无法转移给新用户。"</string>
    <string name="user_add_user_message_short" msgid="1511354412249044381">"您添加新用户后，该用户必须设置自己的空间。\n\n任何用户均可为其他所有用户更新应用。"</string>
    <string name="user_setup_dialog_title" msgid="1765794166801864563">"要现在设置该用户吗？"</string>
    <string name="user_setup_dialog_message" msgid="1004068621380867148">"请让相应用户操作设备并设置他们自己的空间。"</string>
    <string name="user_setup_profile_dialog_message" msgid="3896568553327558731">"要立即设置个人资料吗？"</string>
    <string name="user_setup_button_setup_now" msgid="3391388430158437629">"立即设置"</string>
    <string name="user_setup_button_setup_later" msgid="3068729597269172401">"以后再说"</string>
    <string name="user_cannot_manage_message" product="tablet" msgid="7153048188252553320">"只有平板电脑的机主可以管理用户。"</string>
    <string name="user_cannot_manage_message" product="default" msgid="959315813089950649">"只有手机的机主可以管理用户。"</string>
    <string name="user_cannot_add_accounts_message" msgid="5116692653439737050">"受限个人资料无法添加帐号"</string>
    <string name="user_remove_user_menu" msgid="6897150520686691355">"将<xliff:g id="USER_NAME">%1$s</xliff:g>从此设备中删除"</string>
    <string name="user_lockscreen_settings" msgid="4965661345247084878">"锁定屏幕设置"</string>
    <string name="user_add_on_lockscreen_menu" msgid="9072312646546364619">"允许屏幕处于锁定状态时添加用户"</string>
    <string name="user_new_user_name" msgid="369856859816028856">"新用户"</string>
    <string name="user_new_profile_name" msgid="2632088404952119900">"新的个人资料"</string>
    <string name="user_confirm_remove_self_title" msgid="8432050170899479556">"是否删除自己？"</string>
    <string name="user_confirm_remove_title" msgid="1163721647646152032">"要移除此用户吗？"</string>
    <string name="user_profile_confirm_remove_title" msgid="5573161550669867342">"要移除此个人资料吗？"</string>
    <string name="work_profile_confirm_remove_title" msgid="2017323555783522213">"要移除工作资料吗？"</string>
    <string name="user_confirm_remove_self_message" product="tablet" msgid="2391372805233812410">"您将丢失自己在这台平板电脑上的空间和数据，此操作无法撤消。"</string>
    <string name="user_confirm_remove_self_message" product="default" msgid="7943645442479360048">"您将丢失自己在这部手机上的空间和数据，此操作无法撤消。"</string>
    <string name="user_confirm_remove_message" msgid="1020629390993095037">"该用户名下的所有应用和数据都将随之删除。"</string>
    <string name="work_profile_confirm_remove_message" msgid="323856589749078140">"如果继续，则此工作资料中的所有应用和数据都会被删除。"</string>
    <string name="user_profile_confirm_remove_message" msgid="7373754145959298522">"该用户名下的所有应用和数据都将随之删除。"</string>
    <string name="user_adding_new_user" msgid="1521674650874241407">"正在添加新用户…"</string>
    <string name="user_delete_user_description" msgid="3158592592118767056">"删除用户"</string>
    <string name="user_delete_button" msgid="5131259553799403201">"删除"</string>
    <string name="user_guest" msgid="8475274842845401871">"访客"</string>
    <string name="user_exit_guest_title" msgid="5613997155527410675">"移除访客"</string>
    <string name="user_exit_guest_confirm_title" msgid="3405527634738147409">"要移除访客吗？"</string>
    <string name="user_exit_guest_confirm_message" msgid="2194459201944413257">"此会话中的所有应用和数据都将被删除。"</string>
    <string name="user_exit_guest_dialog_remove" msgid="6351370829952745350">"移除"</string>
    <string name="user_enable_calling" msgid="5128605672081602348">"开启通话功能"</string>
    <string name="user_enable_calling_sms" msgid="9172507088023097063">"开启通话和短信功能"</string>
    <string name="user_remove_user" msgid="6490483480937295389">"移除用户"</string>
    <string name="user_enable_calling_confirm_title" msgid="4315789475268695378">"要开启通话功能吗？"</string>
    <string name="user_enable_calling_confirm_message" msgid="8061594235219352787">"将与此用户共享通话记录。"</string>
    <string name="user_enable_calling_and_sms_confirm_title" msgid="7243308401401932681">"要开启通话和短信功能吗？"</string>
    <string name="user_enable_calling_and_sms_confirm_message" msgid="4025082715546544967">"将与此用户共享通话记录和短信记录。"</string>
    <string name="emergency_info_title" msgid="208607506217060337">"急救信息"</string>
    <string name="emergency_info_summary" msgid="5062945162967838521">"<xliff:g id="USER_NAME">%1$s</xliff:g>的相关信息和联系人信息"</string>
    <string name="application_restrictions" msgid="8207332020898004394">"允许应用和内容"</string>
    <string name="apps_with_restrictions_header" msgid="3660449891478534440">"受限应用"</string>
    <string name="apps_with_restrictions_settings_button" msgid="3841347287916635821">"展开应用设置"</string>
    <string name="nfc_payment_settings_title" msgid="1807298287380821613">"触碰付款"</string>
    <string name="nfc_payment_how_it_works" msgid="3028822263837896720">"工作原理"</string>
    <string name="nfc_payment_no_apps" msgid="5477904979148086424">"使用手机在商店内付款"</string>
    <string name="nfc_payment_default" msgid="8648420259219150395">"默认付款应用"</string>
    <string name="nfc_payment_default_not_set" msgid="7485060884228447765">"未设置"</string>
    <string name="nfc_payment_app_and_desc" msgid="7942415346564794258">"<xliff:g id="APP">%1$s</xliff:g> - <xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="nfc_payment_use_default" msgid="3234730182120288495">"使用默认应用"</string>
    <string name="nfc_payment_favor_default" msgid="5743781166099608372">"始终"</string>
    <string name="nfc_payment_favor_open" msgid="1923314062109977944">"但其他付款应用开启时除外"</string>
    <string name="nfc_payment_pay_with" msgid="7524904024378144072">"通过触碰付款终端进行付款时使用以下应用："</string>
    <string name="nfc_how_it_works_title" msgid="1984068457698797207">"通过支付终端付款"</string>
    <string name="nfc_how_it_works_content" msgid="4749007806393224934">"设置付款应用，然后只需将手机背面靠近任何带有非接触标志的终端即可。"</string>
    <string name="nfc_how_it_works_got_it" msgid="259653300203217402">"知道了"</string>
    <string name="nfc_more_title" msgid="815910943655133280">"更多…"</string>
    <string name="nfc_payment_set_default_label" msgid="7315817259485674542">"要设为您的偏好设置吗？"</string>
    <string name="nfc_payment_set_default" msgid="8532426406310833489">"触碰付款时一律使用<xliff:g id="APP">%1$s</xliff:g>吗？"</string>
    <string name="nfc_payment_set_default_instead_of" msgid="6993301165940432743">"触碰付款时一律使用<xliff:g id="APP_0">%1$s</xliff:g>（而非<xliff:g id="APP_1">%2$s</xliff:g>）吗？"</string>
    <string name="restriction_settings_title" msgid="4233515503765879736">"限制"</string>
    <string name="restriction_menu_reset" msgid="2067644523489568173">"取消限制"</string>
    <string name="restriction_menu_change_pin" msgid="740081584044302775">"更改PIN码"</string>
    <string name="app_notifications_switch_label" msgid="9124072219553687583">"显示通知"</string>
    <string name="help_label" msgid="6886837949306318591">"帮助和反馈"</string>
    <string name="support_summary" msgid="2705726826263742491">"帮助文章、电话与聊天支持、使用入门"</string>
    <string name="user_account_title" msgid="1127193807312271167">"内容帐号"</string>
    <string name="user_picture_title" msgid="7297782792000291692">"照片 ID"</string>
    <string name="extreme_threats_title" msgid="6549541803542968699">"极端威胁"</string>
    <string name="extreme_threats_summary" msgid="8777860706500920667">"接收有关生命和财产极端威胁的警报"</string>
    <string name="severe_threats_title" msgid="8362676353803170963">"严重威胁"</string>
    <string name="severe_threats_summary" msgid="8848126509420177320">"接收有关生命和财产严重威胁的警报"</string>
    <string name="amber_alerts_title" msgid="2772220337031146529">"安珀警报"</string>
    <string name="amber_alerts_summary" msgid="4312984614037904489">"接收有关儿童被绑架/拐骗的公告"</string>
    <string name="repeat_title" msgid="6473587828597786996">"重复"</string>
    <string name="call_manager_enable_title" msgid="7718226115535784017">"启用通话管理器"</string>
    <string name="call_manager_enable_summary" msgid="8458447798019519240">"允许此服务管理您的通话。"</string>
    <string name="call_manager_title" msgid="4479949569744516457">"通话管理器"</string>
    <!-- no translation found for call_manager_summary (5918261959486952674) -->
    <skip />
    <string name="cell_broadcast_settings" msgid="4124461751977706019">"紧急警报"</string>
    <string name="network_operators_settings" msgid="2583178259504630435">"网络运营商"</string>
    <string name="access_point_names" msgid="1381602020438634481">"接入点名称(APN)"</string>
    <string name="enhanced_4g_lte_mode_title" msgid="5808043757309522392">"增强型4G LTE模式"</string>
    <string name="enhanced_4g_lte_mode_summary" msgid="1376589643017218924">"使用 LTE 数据网络提升语音和通信质量（推荐）"</string>
    <string name="preferred_network_type_title" msgid="3431041717309776341">"首选网络类型"</string>
    <string name="preferred_network_type_summary" msgid="6564884693884755019">"LTE（推荐）"</string>
    <string name="work_sim_title" msgid="4843322164662606891">"工作用SIM卡"</string>
    <string name="user_restrictions_title" msgid="5794473784343434273">"应用和内容使用权"</string>
    <string name="user_rename" msgid="8523499513614655279">"重命名"</string>
    <string name="app_restrictions_custom_label" msgid="6160672982086584261">"设置应用限制"</string>
    <string name="user_restrictions_controlled_by" msgid="3164078767438313899">"受“<xliff:g id="APP">%1$s</xliff:g>”控制"</string>
    <string name="app_sees_restricted_accounts" msgid="7503264525057246240">"此应用可使用您的帐号"</string>
    <string name="app_sees_restricted_accounts_and_controlled_by" msgid="6968697624437267294">"此应用由“<xliff:g id="APP">%1$s</xliff:g>”控制，可使用您的帐号"</string>
    <string name="restriction_wifi_config_title" msgid="8889556384136994814">"WLAN和移动网络"</string>
    <string name="restriction_wifi_config_summary" msgid="70888791513065244">"允许修改WLAN和移动网络设置"</string>
    <string name="restriction_bluetooth_config_title" msgid="8871681580962503671">"蓝牙"</string>
    <string name="restriction_bluetooth_config_summary" msgid="8372319681287562506">"允许修改蓝牙配对和设置"</string>
    <string name="restriction_nfc_enable_title" msgid="5888100955212267941">"NFC"</string>
    <string name="restriction_nfc_enable_summary_config" msgid="3232480757215851738">"允许在此<xliff:g id="DEVICE_NAME">%1$s</xliff:g>与其他NFC设备触碰时交换数据"</string>
    <string name="restriction_nfc_enable_summary" product="tablet" msgid="3891097373396149915">"允许在平板电脑与其他设备触碰时交换数据"</string>
    <string name="restriction_nfc_enable_summary" product="default" msgid="825331120501418592">"允许在手机与其他设备触碰时交换数据"</string>
    <string name="restriction_location_enable_title" msgid="5020268888245775164">"位置信息"</string>
    <string name="restriction_location_enable_summary" msgid="3489765572281788755">"允许应用使用您的位置信息"</string>
    <string name="wizard_back" msgid="5567007959434765743">"返回"</string>
    <string name="wizard_next" msgid="3606212602795100640">"下一步"</string>
    <string name="wizard_finish" msgid="3286109692700083252">"完成"</string>
    <string name="user_image_take_photo" msgid="1280274310152803669">"拍照"</string>
    <string name="user_image_choose_photo" msgid="7940990613897477057">"从图库中选择照片"</string>
    <string name="user_image_photo_selector" msgid="5492565707299454873">"选择照片"</string>
    <string name="regulatory_info_text" msgid="5623087902354026557"></string>
    <string name="sim_setup_wizard_title" msgid="1732682852692274928">"SIM 卡"</string>
    <string name="sim_settings_title" msgid="6822745211458959756">"SIM 卡"</string>
    <string name="sim_settings_summary" msgid="4050372057097516088">"<xliff:g id="SIM_NAME">%1$s</xliff:g> - <xliff:g id="SIM_NUMBER">%2$s</xliff:g>"</string>
    <string name="sim_cards_changed_message" msgid="7900721153345139783">"SIM 卡已更改"</string>
    <string name="sim_cards_changed_message_summary" msgid="8258058274989383204">"点按即可设置活动"</string>
    <string name="sim_cellular_data_unavailable" msgid="9109302537004566098">"无法使用移动数据网络"</string>
    <string name="sim_cellular_data_unavailable_summary" msgid="5416535001368135327">"点按即可选择上网用的 SIM 卡"</string>
    <string name="sim_calls_always_use" msgid="7936774751250119715">"一律使用这张卡进行通话"</string>
    <string name="select_sim_for_data" msgid="2366081042162853044">"选择用于数据网络的 SIM 卡"</string>
    <string name="data_switch_started" msgid="2040761479817166311">"正在切换用于连接数据网络的 SIM 卡，这最多可能需要 1 分钟的时间…"</string>
    <string name="select_sim_for_calls" msgid="3503094771801109334">"选择用于通话的 SIM 卡"</string>
    <string name="sim_select_card" msgid="211285163525563293">"选择 SIM 卡"</string>
    <string name="sim_card_number_title" msgid="7845379943474336488">"SIM 卡 <xliff:g id="CARD_NUMBER">%1$d</xliff:g>"</string>
    <string name="sim_slot_empty" msgid="8964505511911854688">"未插入SIM卡"</string>
    <string name="sim_editor_name" msgid="1722945976676142029">"SIM 卡名称"</string>
    <string name="sim_name_hint" msgid="7038643345238968930">"输入 SIM 卡名称"</string>
    <string name="sim_editor_title" msgid="4034301817366627870">"SIM 卡插槽 %1$d"</string>
    <string name="sim_editor_carrier" msgid="5684523444677746573">"运营商"</string>
    <string name="sim_editor_number" msgid="6705955651035440667">"号码"</string>
    <string name="sim_editor_color" msgid="2542605938562414355">"SIM卡颜色"</string>
    <string name="sim_card_select_title" msgid="6668492557519243456">"选择 SIM 卡"</string>
    <string name="color_orange" msgid="4417567658855022517">"橙色"</string>
    <string name="color_purple" msgid="3888532466427762504">"紫色"</string>
    <string name="sim_no_inserted_msg" msgid="210316755353227087">"尚未插入SIM卡"</string>
    <string name="sim_status_title" msgid="6744870675182447160">"SIM 卡状态"</string>
    <string name="sim_status_title_sim_slot" msgid="5725659316463979194">"SIM 卡状态（SIM 卡插槽 %1$d）"</string>
    <string name="sim_call_back_title" msgid="5181549885999280334">"使用默认SIM卡回电"</string>
    <string name="sim_outgoing_call_title" msgid="1019763076116874255">"用于外拨电话的 SIM 卡"</string>
    <string name="sim_other_call_settings" msgid="8247802316114482477">"其他通话设置"</string>
    <string name="preferred_network_offload_title" msgid="1605829724169550275">"首选分流网络"</string>
    <string name="preferred_network_offload_header" msgid="2321173571529106767">"关闭网络名称广播"</string>
    <string name="preferred_network_offload_footer" msgid="5857279426054744020">"关闭网络名称广播可防止第三方获取您的网络信息。"</string>
    <string name="preferred_network_offload_popup" msgid="2252915199889604600">"关闭网络名称广播可防止设备自动连接到隐藏的网络。"</string>
    <string name="sim_signal_strength" msgid="9144010043784767984">"<xliff:g id="DBM">%1$d</xliff:g> dBm <xliff:g id="ASU">%2$d</xliff:g> asu"</string>
    <string name="sim_notification_title" msgid="6272913297433198340">"SIM 卡已更改。"</string>
    <string name="sim_notification_summary" msgid="8858043655706669772">"点按即可进行设置"</string>
    <string name="sim_pref_divider" msgid="6778907671867621874">"首选 SIM 卡"</string>
    <string name="sim_calls_ask_first_prefs_title" msgid="7941299533514115976">"每次都询问"</string>
    <string name="sim_selection_required_pref" msgid="3446721423206414652">"必须选择"</string>
    <string name="sim_selection_channel_title" msgid="2760909074892782589">"SIM 卡选择"</string>
    <string name="dashboard_title" msgid="5453710313046681820">"设置"</string>
    <plurals name="settings_suggestion_header_summary_hidden_items" formatted="false" msgid="5597356221942118048">
      <item quantity="other">显示 %d 项隐藏内容</item>
      <item quantity="one">显示 %d 项隐藏内容</item>
    </plurals>
    <string name="dashboard_suggestion_condition_footer_content_description" msgid="2898588191174845961">"收起"</string>
    <string name="network_dashboard_title" msgid="3135144174846753758">"网络和互联网"</string>
    <string name="network_dashboard_summary_mobile" msgid="3851083934739500429">"移动网络"</string>
    <string name="network_dashboard_summary_data_usage" msgid="3843261364705042212">"流量使用"</string>
    <string name="network_dashboard_summary_hotspot" msgid="8494210248613254574">"热点"</string>
    <string name="connected_devices_dashboard_title" msgid="2355264951438890709">"已连接的设备"</string>
    <string name="connected_devices_dashboard_summary" msgid="2665221896894251402">"蓝牙、驾车模式、NFC"</string>
    <string name="connected_devices_dashboard_no_nfc_summary" msgid="3840842725283655533">"蓝牙、驾车模式"</string>
    <string name="connected_devices_dashboard_no_driving_mode_summary" msgid="5018708106066758867">"蓝牙、NFC"</string>
    <string name="connected_devices_dashboard_no_driving_mode_no_nfc_summary" msgid="5250078362483148199">"蓝牙"</string>
    <string name="app_and_notification_dashboard_title" msgid="7838365599185397539">"应用和通知"</string>
    <string name="app_and_notification_dashboard_summary" msgid="2363314178802548682">"权限、默认应用"</string>
    <string name="account_dashboard_title" msgid="5895948991491438911">"帐号"</string>
    <string name="account_dashboard_default_summary" msgid="3998347400161811075">"未添加任何帐号"</string>
    <string name="app_default_dashboard_title" msgid="7342549305933047317">"默认应用"</string>
    <string name="system_dashboard_summary" msgid="5797743225249766685">"语言、时间、备份、更新"</string>
    <string name="search_results_title" msgid="1796252422574886932">"设置"</string>
    <string name="search_menu" msgid="6283419262313758339">"在设置中搜索"</string>
    <string name="keywords_wifi" msgid="3646884600964177062">"wifi, WLAN, 网络连接, 互联网, 无线, 数据, WLAN 网络"</string>
    <string name="keywords_change_wifi_state" msgid="627068244033681010">"WLAN, wlan, 切换, 控制"</string>
    <string name="keywords_more_default_sms_app" msgid="8597706109432491909">"短信, 发短信, 消息, 发消息, 默认"</string>
    <string name="keywords_more_mobile_networks" msgid="8995946622054642367">"移动网络, 移动, 手机运营商, 无线, 数据, 4G, 3G, 2G, LTE"</string>
    <string name="keywords_wifi_calling" msgid="1784064367330122679">"Wi-Fi, WLAN, 呼叫, 通话"</string>
    <string name="keywords_home" msgid="294182527446892659">"启动器, 默认, 应用"</string>
    <string name="keywords_display" msgid="8910345814565493016">"屏幕, 触摸屏"</string>
    <string name="keywords_display_brightness_level" msgid="3138350812626210404">"屏幕变暗, 调暗屏幕, 触摸屏, 电池, 明亮"</string>
    <string name="keywords_display_auto_brightness" msgid="3325150824507953765">"屏幕变暗, 调暗屏幕, 触摸屏, 电池"</string>
    <string name="keywords_display_night_display" msgid="2534032823231355074">"屏幕变暗, 调暗屏幕, 夜间, 色调, night shift, 夜视模式, 亮度, 屏幕颜色, 颜色, 色彩"</string>
    <string name="keywords_display_wallpaper" msgid="7362076351860131776">"背景, 墙纸, 个性化, 自定义, 显示, 显示屏"</string>
    <string name="keywords_display_font_size" msgid="3404655440064726124">"字体大小"</string>
    <string name="keywords_display_cast_screen" msgid="7684618996741933067">"投影, 投射"</string>
    <string name="keywords_storage" msgid="3299217909546089225">"空间, 磁盘, 硬盘, 设备, 使用量, 使用情况"</string>
    <string name="keywords_battery" msgid="1173830745699768388">"耗电量, 充电"</string>
    <string name="keywords_spell_checker" msgid="1399641226370605729">"拼写, 字典, 词典, 拼写检查, 自动更正"</string>
    <string name="keywords_voice_input" msgid="769778245192531102">"识别程序, 输入, 语音, 说出, 语言, 免触摸, 免提, 识别, 令人反感, 字词, 音频, 记录, 蓝牙耳机"</string>
    <string name="keywords_text_to_speech_output" msgid="5150660047085754699">"语速, 语言, 默认, 说出, 语音, TTS, 无障碍, 屏幕阅读器, 盲人"</string>
    <string name="keywords_date_and_time" msgid="758325881602648204">"时钟, 军用"</string>
    <string name="keywords_network_reset" msgid="6024276007080940820">"重置, 恢复, 出厂设置"</string>
    <string name="keywords_factory_data_reset" msgid="2261491208836438871">"擦除, 删除, 恢复, 清除, 移除, 恢复出厂设置"</string>
    <string name="keywords_printing" msgid="1701778563617114846">"打印机"</string>
    <string name="keywords_sounds" msgid="5633386070971736608">"扬声器提示音, 扬声器, 音量, 静音, 无声, 音频, 音乐"</string>
    <string name="keywords_sounds_and_notifications_interruptions" msgid="5426093074031208917">"勿扰, 请勿打扰, 打扰, 打断"</string>
    <string name="keywords_app" msgid="6334757056536837791">"RAM 内存"</string>
    <string name="keywords_location" msgid="6615286961552714686">"附近, 位置信息, 位置, 记录, 历史记录, 报告"</string>
    <string name="keywords_accounts" msgid="1957925565953357627">"帐号"</string>
    <string name="keywords_users" msgid="3434190133131387942">"限制, 限定, 受限"</string>
    <string name="keywords_keyboard_and_ime" msgid="9143339015329957107">"文字, 文本, 更正, 声音, 提示音, 振动, 自动, 语言, 手势, 推荐, 建议, 主题, 主题背景, 令人反感, 字词, 输入, 表情符号, 国际"</string>
    <string name="keywords_reset_apps" msgid="5293291209613191845">"重置, 偏好设置, 默认"</string>
    <string name="keywords_emergency_app" msgid="3143078441279044780">"紧急, 在紧急情况下使用, 应用, 默认"</string>
    <string name="keywords_default_phone_app" msgid="4213090563141778486">"电话, 拨号器, 默认"</string>
    <string name="keywords_all_apps" msgid="7814015440655563156">"应用, 下载, 应用程序, 系统"</string>
    <string name="keywords_app_permissions" msgid="4229936435938011023">"应用, 权限, 安全"</string>
    <string name="keywords_default_apps" msgid="223872637509160136">"应用, 默认"</string>
    <string name="keywords_ignore_optimizations" msgid="6102579291119055029">"忽略优化, 低电耗模式, 应用待机模式"</string>
    <string name="keywords_color_mode" msgid="6362744316886077510">"鲜亮, RGB, SRGB, 颜色, 自然, 标准"</string>
    <string name="keywords_color_temperature" msgid="6239410718075715449">"颜色, 温度, D65, D73, 白色, 黄色, 蓝色, 暖色, 冷色"</string>
    <string name="keywords_lockscreen" msgid="5746561909668570047">"滑动解锁, 密码, 图案, PIN 码"</string>
    <string name="keywords_profile_challenge" msgid="789611397846512845">"工作验证, 工作, 资料"</string>
    <string name="keywords_unification" msgid="1922900767659821025">"工作资料, 托管资料, 汇整, 统一, 工作, 资料"</string>
    <string name="keywords_gesture" msgid="3526905012224714078">"手势"</string>
    <string name="keywords_payment_settings" msgid="5220104934130446416">"支付、点按、付款"</string>
    <string name="keywords_backup" msgid="470070289135403022">"备份内容, 备份"</string>
    <string name="keywords_assist_gesture_launch" msgid="813968759791342591">"手势"</string>
    <string name="keywords_imei_info" msgid="7230982940217544527">"IMEI, MEID, MIN, PRL 版本, IMEI SV"</string>
    <string name="keywords_sim_status" msgid="1474422416860990564">"网络, 移动网络状态, 服务状态, 信号强度, 移动网络类型, 漫游, ICCID"</string>
    <string name="keywords_model_and_hardware" msgid="1459248377212829642">"序列号, 硬件版本"</string>
    <string name="keywords_android_version" msgid="9069747153590902819">"Android 安全补丁程序级别, 基带版本, 内核版本"</string>
    <string name="keywords_ambient_display_screen" msgid="5874969496073249362">"主动显示, 锁定屏幕显示"</string>
    <string name="keywords_fingerprint_settings" msgid="239222512315619538">"指纹"</string>
    <string name="keywords_auto_rotate" msgid="5620879898668211494">"旋转, 翻转, 纵向, 横向, 屏幕方向, 垂直, 水平"</string>
    <string name="keywords_system_update_settings" msgid="7752189778843741773">"升级, android"</string>
    <string name="keywords_zen_mode_settings" msgid="6526742836231604995">"勿扰, 时间表, 通知, 屏蔽, 设为静音, 振动, 休眠, 工作, 焦点, 声音, 静音, 日, 工作日, 周末, 工作日晚上, 活动"</string>
    <string name="keywords_screen_timeout" msgid="8161370660970309476">"屏幕, 锁定时间, 超时, 锁屏"</string>
    <string name="keywords_storage_settings" msgid="1642340184392317296">"存储设备, 数据, 删除, 清除, 免费, 空间"</string>
    <string name="keywords_bluetooth_settings" msgid="6804844062789439858">"已连接, 设备, 头戴式耳机, 耳机, 扬声器, 无线, 配对, 入耳式耳机, 音乐, 媒体"</string>
    <string name="keywords_wallpaper" msgid="5058364390917429896">"背景, 屏幕, 锁屏, 主题背景"</string>
    <string name="keywords_assist_input" msgid="5017533309492679287">"默认, 智能助理"</string>
    <string name="keywords_default_browser" msgid="8324486019657636744">"默认, 默认浏览器"</string>
    <string name="keywords_default_payment_app" msgid="3838565809518896799">"付款, 默认"</string>
    <string name="keywords_default_links" msgid="5830406261253835547">"默认"</string>
    <string name="keywords_ambient_display" msgid="3103487805748659132">"收到的通知"</string>
    <string name="keywords_hotspot_tethering" msgid="1137511742967410918">"USB 网络共享, 蓝牙网络共享, WLAN 热点"</string>
    <string name="keywords_touch_vibration" msgid="5983211715076385822">"触觉, 振动, 屏幕, 灵敏度"</string>
    <string name="keywords_ring_vibration" msgid="2393528037008999296">"触觉, 振动, 手机, 通话, 灵敏度"</string>
    <string name="setup_wifi_nfc_tag" msgid="9028353016222911016">"设置WLAN NFC标签"</string>
    <string name="write_tag" msgid="8571858602896222537">"写入"</string>
    <string name="status_awaiting_tap" msgid="2130145523773160617">"点按标签即可写入…"</string>
    <string name="status_invalid_password" msgid="2575271864572897406">"密码无效，请重试。"</string>
    <string name="status_write_success" msgid="5228419086308251169">"成功！"</string>
    <string name="status_failed_to_write" msgid="8072752734686294718">"无法将数据写入NFC标签。如果该问题一直存在，请尝试使用其他标签"</string>
    <string name="status_tag_not_writable" msgid="2511611539977682175">"无法将数据写入NFC标签，请使用其他标签。"</string>
    <string name="default_sound" msgid="8821684447333687810">"默认铃声"</string>
    <string name="sound_settings_summary" msgid="4100853606668287965">"铃声音量为 <xliff:g id="PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="sound_dashboard_summary" msgid="3402435125958012986">"音量、振动、勿扰"</string>
    <string name="sound_settings_summary_vibrate" msgid="1869282574422220096">"振铃器已设为振动"</string>
    <string name="sound_settings_summary_silent" msgid="5074529767435584948">"振铃器已设为静音"</string>
    <string name="sound_settings_example_summary" msgid="2404914514266523165">"铃声音量为 80%"</string>
    <string name="media_volume_option_title" msgid="2811531786073003825">"媒体音量"</string>
    <string name="call_volume_option_title" msgid="1265865226974255384">"通话音量"</string>
    <string name="alarm_volume_option_title" msgid="8219324421222242421">"闹钟音量"</string>
    <string name="ring_volume_option_title" msgid="6767101703671248309">"铃声音量"</string>
    <string name="notification_volume_option_title" msgid="6064656124416882130">"通知音量"</string>
    <string name="ringtone_title" msgid="5379026328015343686">"手机铃声"</string>
    <string name="notification_ringtone_title" msgid="4468722874617061231">"默认通知提示音"</string>
    <string name="notification_unknown_sound_title" msgid="2535027767851838335">"应用提供的提示音"</string>
    <string name="notification_sound_default" msgid="565135733949733766">"默认通知提示音"</string>
    <string name="alarm_ringtone_title" msgid="6344025478514311386">"默认闹钟提示音"</string>
    <string name="vibrate_when_ringing_title" msgid="3806079144545849032">"有来电时响铃并振动"</string>
    <string name="other_sound_settings" msgid="3151004537006844718">"其他提示音"</string>
    <string name="dial_pad_tones_title" msgid="1999293510400911558">"拨号键盘提示音"</string>
    <string name="screen_locking_sounds_title" msgid="1340569241625989837">"屏幕锁定提示音"</string>
    <string name="charging_sounds_title" msgid="1132272552057504251">"充电提示音"</string>
    <string name="docking_sounds_title" msgid="155236288949940607">"基座提示音"</string>
    <string name="touch_sounds_title" msgid="5326587106892390176">"触摸提示音"</string>
    <string name="vibrate_on_touch_title" msgid="1510405818894719079">"触摸振动"</string>
    <string name="vibrate_on_touch_summary" msgid="8015901758501868229">"点按、按键等操作的触感"</string>
    <string name="dock_audio_media_title" msgid="1346838179626123900">"基座扬声器播放的音频"</string>
    <string name="dock_audio_media_disabled" msgid="3430953622491538080">"所有音频"</string>
    <string name="dock_audio_media_enabled" msgid="667849382924908673">"仅限媒体音频"</string>
    <string name="emergency_tone_silent" msgid="3750231842974733677">"静音"</string>
    <string name="emergency_tone_alert" msgid="8523447641290736852">"提示音"</string>
    <string name="emergency_tone_vibrate" msgid="2278872257053690683">"振动"</string>
    <string name="boot_sounds_title" msgid="567029107382343709">"开机音效"</string>
    <string name="zen_mode_settings_summary_off" msgid="6119891445378113334">"永不"</string>
    <plurals name="zen_mode_settings_summary_on" formatted="false" msgid="7346979080337117366">
      <item quantity="other"><xliff:g id="ON_COUNT">%d</xliff:g> 条规则</item>
      <item quantity="one">1 条规则</item>
    </plurals>
    <string name="zen_mode_settings_title" msgid="1066226840983908121">"勿扰模式"</string>
    <string name="zen_mode_settings_turn_on_dialog_title" msgid="2297134204747331078">"开启“勿扰”模式"</string>
    <string name="zen_mode_behavior_settings_title" msgid="5453115212674008032">"例外情况"</string>
    <string name="zen_mode_duration_settings_title" msgid="229547412251222757">"持续时间"</string>
    <string name="zen_mode_behavior_allow_title" msgid="3845615648136218141">"允许以下类型的提示音和振动："</string>
    <string name="zen_mode_behavior_no_sound" msgid="1219626004723208056">"不发出提示音"</string>
    <string name="zen_mode_behavior_total_silence" msgid="2229976744274214528">"完全静音"</string>
    <string name="zen_mode_behavior_no_sound_except" msgid="4968477585788243114">"不发出提示音（<xliff:g id="CATEGORIES">%1$s</xliff:g>除外）"</string>
    <string name="zen_mode_behavior_alarms_only" msgid="6455884547877702466">"静音（闹钟和媒体音频除外）"</string>
    <string name="zen_mode_automation_settings_title" msgid="2517800938791944915">"自动开启"</string>
    <string name="zen_mode_automation_settings_page_title" msgid="7069221762714457987">"自动规则"</string>
    <string name="zen_mode_automatic_rule_settings_page_title" msgid="9041488774587594301">"自动规则"</string>
    <string name="zen_mode_automation_suggestion_title" msgid="4321254843908888574">"在特定的时间将手机设为静音"</string>
    <string name="zen_mode_automation_suggestion_summary" msgid="6223252025075862701">"设置“勿扰”规则"</string>
    <string name="zen_mode_use_automatic_rule" msgid="4509513632574025380">"使用规则"</string>
    <string name="zen_mode_option_important_interruptions" msgid="3903928008177972500">"仅限优先事项"</string>
    <string name="zen_mode_option_alarms" msgid="5785372117288803600">"仅限闹钟"</string>
    <string name="zen_mode_option_no_interruptions" msgid="8107126344850276878">"完全阻止"</string>
    <string name="zen_mode_summary_combination" msgid="8715563402849273459">"<xliff:g id="MODE">%1$s</xliff:g>：<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_mode_visual_interruptions_settings_title" msgid="6751708745442997940">"屏蔽视觉打扰"</string>
    <string name="zen_mode_visual_signals_settings_subtitle" msgid="6308824824208120508">"允许视觉信号"</string>
    <string name="zen_mode_settings_category" msgid="3982039687186952865">"开启勿扰模式时"</string>
    <string name="zen_mode_restrict_notifications_title" msgid="478040192977063582">"通知"</string>
    <string name="zen_mode_restrict_notifications_mute" msgid="3690261619682396872">"不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_mute_summary" msgid="5810076116489877312">"您会在屏幕上看到通知"</string>
    <string name="zen_mode_restrict_notifications_mute_footer" msgid="3465600930732602159">"手机在收到通知时既不会发出提示音也不会振动。"</string>
    <string name="zen_mode_restrict_notifications_hide" msgid="5305121630186687339">"不显示通知，也不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_hide_summary" msgid="7555448406901864904">"您将不会看到通知或听到通知提示音"</string>
    <string name="zen_mode_restrict_notifications_hide_footer" msgid="6559283246372102465">"您的手机不会显示任何新通知和现有通知，并且既不会发出提示音也不会振动。当您从屏幕顶部向下滑动时，系统不会显示通知。\n\n请注意，系统仍会显示手机活动和状态的重要通知。"</string>
    <string name="zen_mode_restrict_notifications_custom" msgid="7498689167767941034">"自定义"</string>
    <string name="zen_mode_restrict_notifications_enable_custom" msgid="4250962169561739747">"启用自定义设置"</string>
    <string name="zen_mode_restrict_notifications_disable_custom" msgid="6676997522330453597">"移除自定义设置"</string>
    <string name="zen_mode_restrict_notifications_summary_muted" msgid="5450158135853888485">"不发出通知提示音"</string>
    <string name="zen_mode_restrict_notifications_summary_custom" msgid="7416121534987213074">"隐藏部分通知"</string>
    <string name="zen_mode_restrict_notifications_summary_hidden" msgid="3618285192806732504">"不显示通知，也不发出通知提示音"</string>
    <string name="zen_mode_what_to_block_title" msgid="5480903548365697159">"自定义限制"</string>
    <string name="zen_mode_block_effects_screen_on" msgid="4659484530849212827">"屏幕开启时"</string>
    <string name="zen_mode_block_effects_screen_off" msgid="4276414460889400625">"屏幕关闭时"</string>
    <string name="zen_mode_block_effect_sound" msgid="7383953383758025895">"关闭提示音和振动"</string>
    <string name="zen_mode_block_effect_intent" msgid="350764335391428447">"不开启屏幕"</string>
    <string name="zen_mode_block_effect_light" msgid="8106976110224107316">"不闪烁指示灯"</string>
    <string name="zen_mode_block_effect_peek" msgid="6836997464098657115">"不在屏幕上弹出通知"</string>
    <string name="zen_mode_block_effect_status" msgid="6642532634292373081">"隐藏状态栏图标"</string>
    <string name="zen_mode_block_effect_badge" msgid="4656911773512844243">"隐藏通知圆点"</string>
    <string name="zen_mode_block_effect_ambient" msgid="4704755879961212658">"不要在收到通知时唤醒设备"</string>
    <string name="zen_mode_block_effect_list" msgid="3882541635576592530">"不在通知列表中显示"</string>
    <string name="zen_mode_block_effect_summary_none" msgid="2617875282623486256">"永不"</string>
    <string name="zen_mode_block_effect_summary_screen_off" msgid="1230265589026355094">"屏幕关闭时"</string>
    <string name="zen_mode_block_effect_summary_screen_on" msgid="6017536991063513394">"屏幕开启时"</string>
    <string name="zen_mode_block_effect_summary_sound" msgid="1065107568053759972">"提示音和振动"</string>
    <string name="zen_mode_block_effect_summary_some" msgid="3635646031575107456">"通知的声音、振动和部分视觉符号"</string>
    <string name="zen_mode_block_effect_summary_all" msgid="1213328945418248026">"通知的声音、振动和视觉符号"</string>
    <string name="zen_mode_blocked_effects_footer" msgid="5710896246703497760">"基本手机活动和状态所需的通知一律不隐藏"</string>
    <string name="zen_mode_no_exceptions" msgid="7653433997399582247">"无"</string>
    <string name="zen_mode_other_options" msgid="520015080445012355">"其他选项"</string>
    <string name="zen_mode_add" msgid="90014394953272517">"添加"</string>
    <string name="zen_mode_enable_dialog_turn_on" msgid="8287824809739581837">"开启"</string>
    <string name="zen_mode_button_turn_on" msgid="2824380626482175552">"立即开启"</string>
    <string name="zen_mode_button_turn_off" msgid="6181953727880503094">"立即关闭"</string>
    <string name="zen_mode_settings_dnd_manual_end_time" msgid="8860646554263965569">"勿扰模式结束时间：<xliff:g id="FORMATTED_TIME">%s</xliff:g>"</string>
    <string name="zen_mode_settings_dnd_manual_indefinite" msgid="7186615007561990908">"勿扰模式将一直开启，直到您将其关闭"</string>
    <string name="zen_mode_settings_dnd_automatic_rule" msgid="7780048616476170427">"某个规则（<xliff:g id="RULE_NAME">%s</xliff:g>）已自动开启勿扰模式"</string>
    <string name="zen_mode_settings_dnd_automatic_rule_app" msgid="1721179577382915270">"某个应用（<xliff:g id="APP_NAME">%s</xliff:g>）已自动开启勿扰模式"</string>
    <string name="zen_interruption_level_priority" msgid="2078370238113347720">"仅限优先事项"</string>
    <string name="zen_mode_and_condition" msgid="4927230238450354412">"<xliff:g id="ZEN_MODE">%1$s</xliff:g>。<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_on_with_info" msgid="1202632669798211342">"开启/<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_off_with_info" msgid="2348629457144123849">"关闭/<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="zen_mode_sound_summary_off" msgid="4375814717589425561">"关闭"</string>
    <string name="zen_mode_sound_summary_on" msgid="7718273231309882914">"开启"</string>
    <string name="zen_mode_duration_summary_always_prompt" msgid="5976427426278136178">"每次都询问（除非自动开启）"</string>
    <string name="zen_mode_duration_summary_forever" msgid="3144786357459137066">"直到您关闭（除非自动开启）"</string>
    <plurals name="zen_mode_duration_summary_time_hours" formatted="false" msgid="1060823390336822337">
      <item quantity="other"><xliff:g id="NUM_HOURS">%d</xliff:g> 小时（除非自动开启）</item>
      <item quantity="one">1 小时（除非自动开启）</item>
    </plurals>
    <string name="zen_mode_duration_summary_time_minutes" msgid="3959860288930526323">"<xliff:g id="NUM_MINUTES">%d</xliff:g> 分钟（除非自动开启）"</string>
    <plurals name="zen_mode_sound_summary_summary_off_info" formatted="false" msgid="8115159143760078050">
      <item quantity="other"><xliff:g id="ON_COUNT">%d</xliff:g> 个规则可以自动开启</item>
      <item quantity="one">1 个规则可以自动开启</item>
    </plurals>
    <string name="zen_category_behavior" msgid="2989256030084350296">"行为"</string>
    <string name="zen_category_exceptions" msgid="7601136604273265629">"例外情况"</string>
    <string name="zen_category_schedule" msgid="9000447592251450453">"日程"</string>
    <string name="zen_sound_title" msgid="424490228488531372">"提示音和振动"</string>
    <string name="zen_sound_footer" msgid="7621745273287208979">"开启勿扰模式后，系统既不会发出提示音也不会振动（您在上方允许的项目除外）。"</string>
    <string name="zen_sound_category_title" msgid="4336596939661729188">"全部静音，除了"</string>
    <string name="zen_sound_all_muted" msgid="4850363350480968114">"已设为静音"</string>
    <string name="zen_sound_none_muted" msgid="3938508512103612527">"未设为静音"</string>
    <string name="zen_sound_one_allowed" msgid="8447313454438932276">"已设为静音（但<xliff:g id="SOUND_TYPE">%1$s</xliff:g>除外）"</string>
    <string name="zen_sound_two_allowed" msgid="980491120444358550">"已设为静音（但<xliff:g id="SOUND_TYPE_0">%1$s</xliff:g>和<xliff:g id="SOUND_TYPE_1">%2$s</xliff:g>除外）"</string>
    <string name="zen_sound_three_allowed" msgid="3455767205934547985">"已设为静音（但<xliff:g id="SOUND_TYPE_0">%1$s</xliff:g>、<xliff:g id="SOUND_TYPE_1">%2$s</xliff:g>和<xliff:g id="SOUND_TYPE_2">%3$s</xliff:g>除外）"</string>
    <string name="zen_msg_event_reminder_title" msgid="5137894077488924820">"信息、活动和提醒"</string>
    <string name="zen_msg_event_reminder_footer" msgid="3242847055412790819">"开启勿扰模式后，系统会忽略信息、提醒和活动。您可以调整相应信息设置（您在上访允许的项目除外），以便允许您的好友、家人或其他联系人与您联系。"</string>
    <string name="zen_onboarding_ok" msgid="6131211000824433013">"完成"</string>
    <string name="zen_onboarding_settings" msgid="9046451821239946868">"设置"</string>
    <string name="zen_onboarding_new_setting_title" msgid="1893095176110470711">"不显示通知，也不发出通知提示音"</string>
    <string name="zen_onboarding_current_setting_title" msgid="776426065129609376">"不发出通知提示音"</string>
    <string name="zen_onboarding_new_setting_summary" msgid="6293026064871880706">"您将不会看到通知或听到通知提示音。允许已加星标的联系人和重复来电者的来电。"</string>
    <string name="zen_onboarding_current_setting_summary" msgid="1280614488924843713">"（当前设置）"</string>
    <string name="zen_onboarding_dnd_visual_disturbances_header" msgid="1352808651270918932">"要更改勿扰模式的通知设置吗？"</string>
    <string name="sound_work_settings" msgid="6774324553228566442">"工作资料提示音"</string>
    <string name="work_use_personal_sounds_title" msgid="1148331221338458874">"使用个人资料提示音"</string>
    <string name="work_use_personal_sounds_summary" msgid="6207040454949823153">"工作资料和个人资料会使用相同的提示音"</string>
    <string name="work_ringtone_title" msgid="5806657896300235315">"工作电话铃声"</string>
    <string name="work_notification_ringtone_title" msgid="6081247402404510004">"默认工作通知提示音"</string>
    <string name="work_alarm_ringtone_title" msgid="1441926676833738891">"默认工作闹钟提示音"</string>
    <string name="work_sound_same_as_personal" msgid="3123383644475266478">"与个人资料相同"</string>
    <string name="work_sync_dialog_title" msgid="7123973297187354813">"要替换提示音吗？"</string>
    <string name="work_sync_dialog_yes" msgid="7243884940551635717">"替换"</string>
    <string name="work_sync_dialog_message" msgid="7841728953710863208">"系统将为您的工作资料使用个人资料提示音。"</string>
    <string name="ringtones_install_custom_sound_title" msgid="5948792721161302255">"要添加自定义提示音吗？"</string>
    <string name="ringtones_install_custom_sound_content" msgid="2195581481608512786">"此文件将复制到“<xliff:g id="FOLDER_NAME">%s</xliff:g>”文件夹"</string>
    <string name="ringtones_category_preference_title" msgid="5675912303120102366">"铃声"</string>
    <string name="other_sound_category_preference_title" msgid="2521096636124314015">"其他提示音和振动"</string>
    <string name="configure_notification_settings" msgid="7616737397127242615">"通知"</string>
    <string name="recent_notifications" msgid="5660639387705060156">"最近发送"</string>
    <string name="recent_notifications_see_all_title" msgid="8572160812124540326">"查看过去 7 天的所有应用"</string>
    <string name="advanced_section_header" msgid="8833934850242546903">"高级"</string>
    <string name="profile_section_header" msgid="2320848161066912001">"工作通知"</string>
    <string name="notification_badging_title" msgid="5938709971403474078">"允许使用通知圆点"</string>
    <string name="notification_pulse_title" msgid="1905382958860387030">"闪烁指示灯"</string>
    <string name="lock_screen_notifications_title" msgid="2583595963286467672">"在锁定屏幕上"</string>
    <string name="locked_work_profile_notification_title" msgid="8327882003361551992">"当工作资料遭到锁定时"</string>
    <string name="lock_screen_notifications_summary_show" msgid="6407527697810672847">"显示所有通知内容"</string>
    <string name="lock_screen_notifications_summary_hide" msgid="8301305044690264958">"隐藏敏感内容"</string>
    <string name="lock_screen_notifications_summary_disable" msgid="859628910427886715">"完全不显示通知"</string>
    <string name="lock_screen_notifications_interstitial_message" msgid="6164532459432182244">"在设备锁定时，您希望通知如何显示？"</string>
    <string name="lock_screen_notifications_interstitial_title" msgid="1416589393106326972">"通知"</string>
    <string name="lock_screen_notifications_summary_show_profile" msgid="835870815661120772">"显示所有工作通知内容"</string>
    <string name="lock_screen_notifications_summary_hide_profile" msgid="2005907007779384635">"隐藏敏感工作内容"</string>
    <string name="lock_screen_notifications_interstitial_message_profile" msgid="8307705621027472346">"在设备锁定时，您希望个人资料通知如何显示？"</string>
    <string name="lock_screen_notifications_interstitial_title_profile" msgid="3169806586032521333">"个人资料通知"</string>
    <string name="notifications_title" msgid="8086372779371204971">"通知"</string>
    <string name="app_notifications_title" msgid="5810577805218003760">"应用通知"</string>
    <string name="notification_channel_title" msgid="2260666541030178452">"通知类别"</string>
    <string name="notification_group_title" msgid="7180506440133859601">"通知类别组"</string>
    <string name="notification_importance_title" msgid="4368578960344731828">"行为"</string>
    <string name="notification_importance_unspecified" msgid="6622173510486113958">"允许发出提示音"</string>
    <string name="notification_importance_blocked" msgid="7938180808339386300">"一律不显示通知"</string>
    <string name="notification_importance_min" msgid="9054819132085066824">"无声显示并将重要性级别最小化"</string>
    <string name="notification_importance_low" msgid="2445139943005315690">"显示通知但不发出提示音"</string>
    <string name="notification_importance_default" msgid="5958338024601957516">"发出提示音"</string>
    <string name="notification_importance_high" msgid="2082429479238228527">"发出提示音并在屏幕上弹出通知"</string>
    <string name="notification_importance_high_silent" msgid="2667033773703765252">"在屏幕上弹出"</string>
    <string name="notification_importance_min_title" msgid="6974673091137544803">"低"</string>
    <string name="notification_importance_low_title" msgid="8131254047772814309">"中"</string>
    <string name="notification_importance_default_title" msgid="9120383978536089489">"高"</string>
    <string name="notification_importance_high_title" msgid="3058778300264746473">"紧急"</string>
    <string name="allow_interruption" msgid="7136150018111848721">"允许打扰"</string>
    <string name="allow_interruption_summary" msgid="7870159391333957050">"允许应用发出提示音、振动，以及/或在屏幕上弹出通知"</string>
    <string name="notification_channel_summary_min" msgid="5401718014765921892">"重要性：低"</string>
    <string name="notification_channel_summary_low" msgid="322317684244981244">"重要性：中等"</string>
    <string name="notification_channel_summary_default" msgid="1111749130423589931">"重要性：高"</string>
    <string name="notification_channel_summary_high" msgid="2085017556511003283">"重要性：紧急"</string>
    <string name="notification_switch_label" msgid="6843075654538931025">"显示通知"</string>
    <string name="default_notification_assistant" msgid="7631945224761430146">"通知助手"</string>
    <string name="notifications_sent_daily" msgid="3584506541352710975">"每天大约 <xliff:g id="NUMBER">%1$s</xliff:g> 条"</string>
    <string name="notifications_sent_weekly" msgid="1030525736746720584">"每周大约 <xliff:g id="NUMBER">%1$s</xliff:g> 条"</string>
    <string name="notifications_sent_never" msgid="1001964786456700536">"永不"</string>
    <string name="manage_notification_access_title" msgid="7510080164564944891">"通知使用权"</string>
    <string name="work_profile_notification_access_blocked_summary" msgid="8748026238701253040">"已禁止访问工作资料通知"</string>
    <string name="manage_notification_access_summary_zero" msgid="2409912785614953348">"应用无法读取通知"</string>
    <plurals name="manage_notification_access_summary_nonzero" formatted="false" msgid="7930130030691218387">
      <item quantity="other">%d 个应用可以读取通知</item>
      <item quantity="one">%d 个应用可以读取通知</item>
    </plurals>
    <string name="no_notification_listeners" msgid="3487091564454192821">"没有任何已安装的应用请求通知访问权限。"</string>
    <string name="notification_listener_security_warning_title" msgid="5522924135145843279">"要允许<xliff:g id="SERVICE">%1$s</xliff:g>获取通知访问权限吗？"</string>
    <string name="notification_listener_security_warning_summary" msgid="119203147791040151">"<xliff:g id="NOTIFICATION_LISTENER_NAME">%1$s</xliff:g>将可读取所有通知（包括联系人姓名和您收到的消息正文等个人信息），而且还能关闭通知或触发通知中的操作按钮。\n\n另外，此应用还将能开启或关闭勿扰模式，以及更改相关设置。"</string>
    <string name="notification_listener_disable_warning_summary" msgid="6738915379642948000">"如果您停用<xliff:g id="NOTIFICATION_LISTENER_NAME">%1$s</xliff:g>的通知访问权限，勿扰模式的访问权限可能也会遭到停用。"</string>
    <string name="notification_listener_disable_warning_confirm" msgid="8333442186428083057">"停用"</string>
    <string name="notification_listener_disable_warning_cancel" msgid="8586417377104211584">"取消"</string>
    <string name="vr_listeners_title" msgid="1318901577754715777">"VR 助手服务"</string>
    <string name="no_vr_listeners" msgid="2689382881717507390">"没有任何已安装的应用请求以 VR 助手服务的形式运行。"</string>
    <string name="vr_listener_security_warning_title" msgid="8309673749124927122">"允许<xliff:g id="SERVICE">%1$s</xliff:g>访问 VR 服务吗？"</string>
    <string name="vr_listener_security_warning_summary" msgid="6931541068825094653">"只有当您在虚拟实境模式下使用应用时，才能运行<xliff:g id="VR_LISTENER_NAME">%1$s</xliff:g>。"</string>
    <string name="display_vr_pref_title" msgid="8104485269504335481">"当设备处于 VR 模式时"</string>
    <string name="display_vr_pref_low_persistence" msgid="5707494209944718537">"减少模糊（建议）"</string>
    <string name="display_vr_pref_off" msgid="2190091757123260989">"减少闪烁"</string>
    <string name="picture_in_picture_title" msgid="5824849294270017113">"画中画"</string>
    <string name="picture_in_picture_empty_text" msgid="685224245260197779">"已安装的应用均不支持画中画"</string>
    <string name="picture_in_picture_keywords" msgid="8361318686701764690">"画中画, 画中, pip, picture in"</string>
    <string name="picture_in_picture_app_detail_title" msgid="4080800421316791732">"画中画"</string>
    <string name="picture_in_picture_app_detail_switch" msgid="1131910667023738296">"允许进入画中画模式"</string>
    <string name="picture_in_picture_app_detail_summary" msgid="1264019085827708920">"允许此应用在您打开应用时或您离开应用后（例如继续观看视频）创建画中画窗口。这类窗口会显示在您当前使用的其他应用的上层。"</string>
    <string name="manage_zen_access_title" msgid="2611116122628520522">"“勿扰”权限"</string>
    <string name="zen_access_empty_text" msgid="8772967285742259540">"没有任何已安装应用申请“勿扰”权限"</string>
    <string name="loading_notification_apps" msgid="5031818677010335895">"正在加载应用…"</string>
    <string name="app_notifications_off_desc" msgid="8289223211387083447">"根据您的要求，Android 会阻止此应用的通知显示在此设备上"</string>
    <string name="channel_notifications_off_desc" msgid="9013011134681491778">"根据您的要求，Android 会阻止这类通知显示在此设备上"</string>
    <string name="channel_group_notifications_off_desc" msgid="2315252834146837470">"根据您的要求，Android 会阻止这组通知显示在此设备上"</string>
    <string name="notification_channels" msgid="5346841743182627500">"类别"</string>
    <string name="notification_channels_other" msgid="5645317113885788226">"其他"</string>
    <plurals name="notification_group_summary" formatted="false" msgid="3420621520561455358">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <string name="no_channels" msgid="3077375508177744586">"此应用未发布任何通知"</string>
    <string name="app_settings_link" msgid="8894946007543660906">"应用中的其他设置"</string>
    <string name="app_notification_listing_summary_zero" msgid="8046168435207424440">"目前设为接收所有应用的通知"</string>
    <plurals name="app_notification_listing_summary_others" formatted="false" msgid="6709582776823665660">
      <item quantity="other">已关闭 <xliff:g id="COUNT_1">%d</xliff:g> 个应用的通知功能</item>
      <item quantity="one">已关闭 <xliff:g id="COUNT_0">%d</xliff:g> 个应用的通知功能</item>
    </plurals>
    <plurals name="deleted_channels" formatted="false" msgid="8028574302599397935">
      <item quantity="other">已删除 <xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one">已删除 <xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <string name="notification_toggle_on" msgid="650145396718191048">"开启"</string>
    <string name="notification_toggle_off" msgid="2142010737190671762">"关闭"</string>
    <string name="app_notification_block_title" msgid="4069351066849087649">"全部屏蔽"</string>
    <string name="app_notification_block_summary" msgid="4744020456943215352">"一律不显示这类通知"</string>
    <string name="notification_content_block_title" msgid="5854232570963006360">"显示通知"</string>
    <string name="notification_content_block_summary" msgid="7746185794438882389">"一律不在通知栏或外围设备上显示通知"</string>
    <string name="notification_badge_title" msgid="6370122441168519809">"允许使用通知圆点"</string>
    <string name="notification_channel_badge_title" msgid="2240827899882847087">"显示通知圆点"</string>
    <string name="app_notification_override_dnd_title" msgid="7867458246395884830">"覆盖“勿扰”设置"</string>
    <string name="app_notification_override_dnd_summary" msgid="2612502099373472686">"开启勿扰模式时允许继续接收这类通知"</string>
    <string name="app_notification_visibility_override_title" msgid="7821124557634786985">"在锁定屏幕上"</string>
    <string name="app_notification_row_banned" msgid="5983655258784814773">"屏蔽"</string>
    <string name="app_notification_row_priority" msgid="7723839972982746568">"优先"</string>
    <string name="app_notification_row_sensitive" msgid="1809610030432329940">"敏感"</string>
    <string name="app_notifications_dialog_done" msgid="3484067728568791014">"完成"</string>
    <string name="app_notification_importance_title" msgid="8002263131149345584">"重要程度"</string>
    <string name="notification_show_lights_title" msgid="7671781299688190532">"闪烁指示灯"</string>
    <string name="notification_vibrate_title" msgid="1646667807969755957">"振动"</string>
    <string name="notification_channel_sound_title" msgid="3899212238513507941">"提示音"</string>
    <string name="zen_mode_rule_delete_button" msgid="903658142711011617">"删除"</string>
    <string name="zen_mode_rule_rename_button" msgid="4642843370946599164">"重命名"</string>
    <string name="zen_mode_rule_name" msgid="5149068059383837549">"规则名称"</string>
    <string name="zen_mode_rule_name_hint" msgid="3781174510556433384">"输入规则名称"</string>
    <string name="zen_mode_rule_name_warning" msgid="4517805381294494314">"规则名称已被使用"</string>
    <string name="zen_mode_add_rule" msgid="9100929184624317193">"添加规则"</string>
    <string name="zen_mode_add_event_rule" msgid="3997335103633946552">"添加活动规则"</string>
    <string name="zen_mode_add_time_rule" msgid="5002080000597838703">"添加时间规则"</string>
    <string name="zen_mode_delete_rule" msgid="2985902330199039533">"删除规则"</string>
    <string name="zen_mode_choose_rule_type" msgid="5423746638871953459">"选择规则类型"</string>
    <string name="zen_mode_delete_rule_confirmation" msgid="6237882294348570283">"要删除“<xliff:g id="RULE">%1$s</xliff:g>”规则吗？"</string>
    <string name="zen_mode_delete_rule_button" msgid="4248741120307752294">"删除"</string>
    <string name="zen_mode_rule_type_unknown" msgid="3049377282766700600">"未知"</string>
    <string name="zen_mode_app_set_behavior" msgid="1534429320064381355">"目前无法更改这些设置。应用（<xliff:g id="APP_NAME">%1$s</xliff:g>）已通过自定义行为自动开启勿扰模式。"</string>
    <string name="zen_mode_unknown_app_set_behavior" msgid="2558968232814237874">"目前无法更改这些设置。某个应用已通过自定义行为自动开启勿扰模式。"</string>
    <string name="zen_mode_qs_set_behavior" msgid="6200424436456086312">"目前无法更改这些设置。用户已通过自定义行为手动开启勿扰模式。"</string>
    <string name="zen_schedule_rule_type_name" msgid="6163149826036287324">"时间"</string>
    <string name="zen_schedule_rule_enabled_toast" msgid="3379499360390382259">"将自动规则设置为在指定时间段内开启勿扰模式"</string>
    <string name="zen_event_rule_type_name" msgid="6503468472212606158">"活动"</string>
    <string name="zen_event_rule_enabled_toast" msgid="6910577623330811480">"将自动规则设置为发生指定事件时开启勿扰模式"</string>
    <string name="zen_mode_event_rule_calendar" msgid="8787906563769067418">"在以下日历活动期间："</string>
    <string name="zen_mode_event_rule_summary_calendar_template" msgid="5135844750232403975">"在<xliff:g id="CALENDAR">%1$s</xliff:g>的活动期间"</string>
    <string name="zen_mode_event_rule_summary_any_calendar" msgid="4936646399126636358">"所有日历"</string>
    <string name="zen_mode_event_rule_summary_reply_template" msgid="6590671260829837157">"回复为<xliff:g id="REPLY">%1$s</xliff:g>"</string>
    <string name="zen_mode_event_rule_calendar_any" msgid="6485568415998569885">"所有日历"</string>
    <string name="zen_mode_event_rule_reply" msgid="5166322024212403739">"回复为"</string>
    <string name="zen_mode_event_rule_reply_any_except_no" msgid="8868873496008825961">"“参加”、“可能参加”或“未回复”"</string>
    <string name="zen_mode_event_rule_reply_yes_or_maybe" msgid="2769656565454495824">"“参加”或“可能参加”"</string>
    <string name="zen_mode_event_rule_reply_yes" msgid="1003598835878784659">"参加"</string>
    <string name="zen_mode_rule_not_found_text" msgid="8963662446092059836">"找不到规则。"</string>
    <string name="zen_mode_rule_summary_enabled_combination" msgid="976098744828219297">"开启/<xliff:g id="MODE">%1$s</xliff:g>"</string>
    <string name="zen_mode_rule_summary_provider_combination" msgid="2101201392041867409">"<xliff:g id="PACKAGE">%1$s</xliff:g>\n<xliff:g id="SUMMARY">%2$s</xliff:g>"</string>
    <string name="zen_mode_schedule_rule_days" msgid="3195058680641389948">"星期几"</string>
    <string name="zen_mode_schedule_rule_days_none" msgid="4954143628634166317">"无"</string>
    <string name="zen_mode_schedule_rule_days_all" msgid="146511166522076034">"每天"</string>
    <string name="zen_mode_schedule_alarm_title" msgid="767054141267122030">"闹钟设置优先于结束时间设置"</string>
    <string name="zen_mode_schedule_alarm_summary" msgid="4597050434723180422">"在所设结束时间或闹钟下一次响铃时（两者取其先）退出此模式"</string>
    <string name="summary_divider_text" msgid="7228986578690919294">"， "</string>
    <string name="summary_range_symbol_combination" msgid="5695218513421897027">"<xliff:g id="START">%1$s</xliff:g> - <xliff:g id="END">%2$s</xliff:g>"</string>
    <string name="summary_range_verbal_combination" msgid="8467306662961568656">"<xliff:g id="START">%1$s</xliff:g>到<xliff:g id="END">%2$s</xliff:g>"</string>
    <string name="zen_mode_calls" msgid="7051492091133751208">"来电"</string>
    <string name="zen_mode_calls_title" msgid="623395033931747661">"允许进行通话"</string>
    <string name="zen_mode_calls_footer" msgid="3618700268458237781">"开启勿扰模式后，系统会屏蔽来电。您可以调整相应设置，以便允许您的好友、家人或其他联系人与您联系。"</string>
    <string name="zen_mode_starred_contacts_title" msgid="1848464279786960190">"已加星标的联系人"</string>
    <plurals name="zen_mode_starred_contacts_summary_additional_contacts" formatted="false" msgid="500105380255018671">
      <item quantity="other">另外 <xliff:g id="NUM_PEOPLE">%d</xliff:g> 人</item>
      <item quantity="one">另外 1 人</item>
    </plurals>
    <string name="zen_mode_messages" msgid="5886440273537510894">"消息"</string>
    <string name="zen_mode_messages_title" msgid="7729380010396411129">"允许显示消息"</string>
    <string name="zen_mode_all_messages" msgid="8257021584561639816">"信息"</string>
    <string name="zen_mode_selected_messages" msgid="1047355526202106114">"部分信息"</string>
    <string name="zen_mode_from_anyone" msgid="2638322015361252161">"来自任何人"</string>
    <string name="zen_mode_from_contacts" msgid="2232335406106711637">"仅限来自联系人"</string>
    <string name="zen_mode_from_starred" msgid="2678345811950997027">"仅限来自收藏的联系人"</string>
    <string name="zen_calls_summary_starred_repeat" msgid="4046151920710059778">"来自已加星标的联系人和重复来电者"</string>
    <string name="zen_calls_summary_contacts_repeat" msgid="1528716671301999084">"来自联系人和重复来电者"</string>
    <string name="zen_calls_summary_repeat_only" msgid="7105261473107715445">"仅限来自重复来电者"</string>
    <string name="zen_mode_from_none" msgid="8219706639954614136">"无"</string>
    <string name="zen_mode_alarms" msgid="2165302777886552926">"闹钟"</string>
    <string name="zen_mode_media" msgid="8808264142134422380">"媒体"</string>
    <string name="zen_mode_system" msgid="2541380718411593581">"触摸提示音"</string>
    <string name="zen_mode_reminders" msgid="5458502056440485730">"提醒"</string>
    <string name="zen_mode_reminders_title" msgid="2345044406347406902">"允许显示提醒"</string>
    <string name="zen_mode_events" msgid="7914446030988618264">"活动"</string>
    <string name="zen_mode_events_title" msgid="5597241655883329085">"允许显示活动"</string>
    <string name="zen_mode_all_callers" msgid="2378065871253871057">"任何人"</string>
    <string name="zen_mode_contacts_callers" msgid="5569804103920394175">"联系人"</string>
    <string name="zen_mode_starred_callers" msgid="1023167821338514140">"已加星标的联系人"</string>
    <string name="zen_mode_repeat_callers" msgid="5019521886428322131">"重复来电者"</string>
    <string name="zen_mode_repeat_callers_title" msgid="8553876328249671783">"允许显示重复来电者"</string>
    <string name="zen_mode_calls_summary_one" msgid="3972333792749874863">"来自<xliff:g id="CALLER_TYPE">%1$s</xliff:g>"</string>
    <string name="zen_mode_calls_summary_two" msgid="6592821501321201329">"来自<xliff:g id="CALLER_TYPE">%1$s</xliff:g>和<xliff:g id="CALLERT_TPYE">%2$s</xliff:g>"</string>
    <string name="zen_mode_repeat_callers_summary" msgid="239685342222975733">"如果同一个人在 <xliff:g id="MINUTES">%d</xliff:g> 分钟内第二次来电"</string>
    <string name="zen_mode_behavior_summary_custom" msgid="168127313238020146">"自定义"</string>
    <string name="zen_mode_when" msgid="2767193283311106373">"自动开启"</string>
    <string name="zen_mode_when_never" msgid="8809494351918405602">"永不"</string>
    <string name="zen_mode_when_every_night" msgid="3122486110091921009">"每晚"</string>
    <string name="zen_mode_when_weeknights" msgid="8354070633893273783">"周一至周五夜间"</string>
    <string name="zen_mode_start_time" msgid="8102602297273744441">"开始时间"</string>
    <string name="zen_mode_end_time" msgid="8774327885892705505">"结束时间"</string>
    <string name="zen_mode_end_time_next_day_summary_format" msgid="4201521691238728701">"次日<xliff:g id="FORMATTED_TIME">%s</xliff:g>"</string>
    <string name="zen_mode_summary_alarms_only_indefinite" msgid="2061973221027570123">"更改为无限期仅限闹钟"</string>
    <plurals name="zen_mode_summary_alarms_only_by_minute" formatted="false" msgid="6122003583875424601">
      <item quantity="other">更改为仅限闹钟，持续 <xliff:g id="DURATION">%1$d</xliff:g> 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">更改为仅限闹钟，持续 1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_summary_alarms_only_by_hour" formatted="false" msgid="2407703455581767748">
      <item quantity="other">更改为仅限闹钟，持续 <xliff:g id="DURATION">%1$d</xliff:g> 小时（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">更改为仅限闹钟，持续 1 小时（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <string name="zen_mode_summary_alarms_only_by_time" msgid="7465525754879341907">"更改为仅限闹钟（到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>）"</string>
    <string name="zen_mode_summary_always" msgid="6172985102689237703">"更改为一律允许打扰"</string>
    <string name="zen_mode_screen_on" msgid="8774571998575673502">"当屏幕开启时"</string>
    <string name="zen_mode_screen_on_summary" msgid="2208664848367443505">"允许在“勿扰”模式下被静音的通知在屏幕上弹出，并显示状态栏图标"</string>
    <string name="zen_mode_screen_off" msgid="3144446765110327937">"当屏幕关闭时"</string>
    <string name="zen_mode_screen_off_summary" msgid="7430034620565812258">"允许在“勿扰”模式下被静音的通知开启屏幕并闪烁指示灯"</string>
    <string name="zen_mode_screen_off_summary_no_led" msgid="2826121465026642017">"允许在“勿扰”模式下被静音的通知开启屏幕"</string>
    <string name="notification_app_settings_button" msgid="6685640230371477485">"通知设置"</string>
    <string name="suggestion_button_text" msgid="3275010948381252006">"确定"</string>
    <string name="device_feedback" msgid="3238056036766293294">"发送有关此设备的反馈"</string>
    <string name="restr_pin_enter_admin_pin" msgid="1085834515677448072">"输入管理员 PIN 码"</string>
    <string name="switch_on_text" msgid="1124106706920572386">"开启"</string>
    <string name="switch_off_text" msgid="1139356348100829659">"关闭"</string>
    <string name="screen_pinning_title" msgid="2292573232264116542">"屏幕固定"</string>
    <string name="screen_pinning_description" msgid="1110847562111827766">"开启此设置后，您可以使用固定屏幕功能来固定显示当前的屏幕，直到您取消固定为止。\n\n要使用固定屏幕功能，请执行以下操作：\n\n1. 确保固定屏幕功能已开启\n\n2. 打开“概览”\n\n3. 点按屏幕顶部的应用图标，然后点按“固定”"</string>
    <string name="screen_pinning_unlock_pattern" msgid="8282268570060313339">"取消固定屏幕前要求绘制解锁图案"</string>
    <string name="screen_pinning_unlock_pin" msgid="8757588350454795286">"取消固定屏幕前要求输入 PIN 码"</string>
    <string name="screen_pinning_unlock_password" msgid="2514079566873826434">"取消固定屏幕前要求输入密码"</string>
    <string name="screen_pinning_unlock_none" msgid="3814188275713871856">"取消固定屏幕时锁定设备"</string>
    <string name="opening_paragraph_delete_profile_unknown_company" msgid="2232461523882170874">"此工作资料由以下应用管理："</string>
    <string name="managing_admin" msgid="8843802210377459055">"由<xliff:g id="ADMIN_APP_LABEL">%s</xliff:g>管理"</string>
    <string name="experimental_preference" msgid="7083015446690681376">"（实验性）"</string>
    <string name="encryption_interstitial_header" msgid="468015813904595613">"安全启动"</string>
    <string name="encryption_continue_button" msgid="1121880322636992402">"继续"</string>
    <string name="encryption_interstitial_message_pin" msgid="2317181134653424679">"为了进一步保护此设备的安全，您可以将设备设为需要输入 PIN 码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入 PIN 码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pattern" msgid="7081249914068568570">"为了进一步保护此设备的安全，您可以将设备设为需要绘制解锁图案才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要绘制解锁图案才能启动吗？"</string>
    <string name="encryption_interstitial_message_password" msgid="7796567133897436443">"为了进一步保护此设备的安全，您可以将设备设为需要输入密码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入密码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pin_for_fingerprint" msgid="4550632760119547492">"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要输入 PIN 码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入 PIN 码才能启动吗？"</string>
    <string name="encryption_interstitial_message_pattern_for_fingerprint" msgid="932184823193006087">"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要绘制解锁图案才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要绘制解锁图案才能启动吗？"</string>
    <string name="encryption_interstitial_message_password_for_fingerprint" msgid="5560954719370251702">"为了进一步保护此设备的安全，除了使用指纹解锁设备之外，您还可以将设备设为需要输入密码才能启动。在设备启动之前，无法接听电话、接收消息或通知（包括闹钟）。\n\n这样一来，即使设备丢失或被盗，其中的数据仍安全无虞。要将设备设为需要输入密码才能启动吗？"</string>
    <string name="encryption_interstitial_yes" msgid="4439509435889513411">"是"</string>
    <string name="encryption_interstitial_no" msgid="8935031349097025137">"否"</string>
    <string name="restricted_true_label" msgid="4761453839409220473">"受限"</string>
    <string name="restricted_false_label" msgid="3279282180297058755">"应用可以在后台使用电量"</string>
    <string name="encrypt_talkback_dialog_require_pin" msgid="8299960550048989807">"要求输入 PIN 码吗？"</string>
    <string name="encrypt_talkback_dialog_require_pattern" msgid="1499790256154146639">"要求绘制图案吗？"</string>
    <string name="encrypt_talkback_dialog_require_password" msgid="8841994614218049215">"要求输入密码吗？"</string>
    <string name="encrypt_talkback_dialog_message_pin" msgid="7582096542997635316">"当您输入 PIN 码以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="encrypt_talkback_dialog_message_pattern" msgid="2020083142199612743">"当您绘制解锁图案以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="encrypt_talkback_dialog_message_password" msgid="4155875981789127796">"当您输入密码以启动此设备时，<xliff:g id="SERVICE">%1$s</xliff:g>等无障碍服务还未开启。"</string>
    <string name="direct_boot_unaware_dialog_message" msgid="7870273558547549125">"注意：重新启动后，您必须将手机解锁才能运行此应用"</string>
    <string name="imei_information_title" msgid="8499085421609752290">"IMEI 信息"</string>
    <string name="imei_information_summary" msgid="2074095606556565233">"IMEI相关信息"</string>
    <string name="slot_number" msgid="3762676044904653577">"（插槽<xliff:g id="SLOT_NUM">%1$d</xliff:g>）"</string>
    <string name="launch_by_default" msgid="1840761193189009248">"默认打开"</string>
    <string name="app_launch_domain_links_title" msgid="1160925981363706090">"打开链接"</string>
    <string name="app_launch_open_domain_urls_title" msgid="8914721351596745701">"打开支持的链接"</string>
    <string name="app_launch_open_domain_urls_summary" msgid="5367573364240712217">"无需询问即可打开"</string>
    <string name="app_launch_supported_domain_urls_title" msgid="8250695258211477480">"支持的链接"</string>
    <string name="app_launch_other_defaults_title" msgid="2516812499807835178">"其他默认设置"</string>
    <string name="storage_summary_format" msgid="5419902362347539755">"<xliff:g id="STORAGE_TYPE">%2$s</xliff:g>已使用 <xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="storage_type_internal" msgid="6042049833565674948">"内部存储空间"</string>
    <string name="storage_type_external" msgid="7738894330670001898">"外部存储空间"</string>
    <string name="app_data_usage" msgid="7942375313697452803">"应用的流量使用情况"</string>
    <string name="data_summary_format" msgid="6213211533341068366">"自 <xliff:g id="DATE">%2$s</xliff:g>以来已使用 <xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="storage_used" msgid="7128074132917008743">"已使用的存储空间"</string>
    <string name="change" msgid="6657848623929839991">"更改"</string>
    <string name="change_storage" msgid="600475265207060436">"更改存储空间"</string>
    <string name="notifications_label" msgid="2872668710589600731">"通知"</string>
    <string name="notifications_enabled" msgid="6983396130566021385">"开启"</string>
    <string name="notifications_enabled_with_info" msgid="2446033696770133334">"开启/<xliff:g id="NOTIFICATIONS_CATEGORIES_OFF">%1$s</xliff:g>"</string>
    <string name="notifications_disabled" msgid="1262114548434938079">"关闭"</string>
    <string name="notifications_partly_blocked" msgid="592071133950126656">"已关闭 <xliff:g id="COUNT_0">%1$d</xliff:g> 个（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）类别的通知"</string>
    <string name="notifications_silenced" msgid="4728603513072110381">"不发出提示音"</string>
    <string name="notifications_redacted" msgid="4493588975742803160">"屏幕锁定时不显示敏感内容"</string>
    <string name="notifications_hidden" msgid="3619610536038757468">"屏幕锁定时不显示"</string>
    <string name="notifications_priority" msgid="1066342037602085552">"覆盖“勿扰”设置"</string>
    <string name="notifications_summary_divider" msgid="9013807608804041387">" / "</string>
    <string name="notification_summary_level" msgid="2726571692704140826">"%d 级"</string>
    <string name="notification_summary_channel" msgid="5831124672372023524">"<xliff:g id="CHANNEL_NAME">%1$s</xliff:g> • <xliff:g id="GROUP_NAME">%2$s</xliff:g>"</string>
    <plurals name="notifications_categories_off" formatted="false" msgid="5583365573683409754">
      <item quantity="other">已关闭 <xliff:g id="COUNT_1">%d</xliff:g> 个类别</item>
      <item quantity="one">已关闭 <xliff:g id="COUNT_0">%d</xliff:g> 个类别</item>
    </plurals>
    <plurals name="permissions_summary" formatted="false" msgid="6402730318075959117">
      <item quantity="other">已授予 <xliff:g id="COUNT_1">%d</xliff:g> 项权限</item>
      <item quantity="one">已授予 <xliff:g id="COUNT_0">%d</xliff:g> 项权限</item>
    </plurals>
    <plurals name="runtime_permissions_summary" formatted="false" msgid="1564663886246010959">
      <item quantity="other">已授予 <xliff:g id="COUNT_2">%d</xliff:g> 项权限（共 <xliff:g id="COUNT_3">%d</xliff:g> 项）</item>
      <item quantity="one">已授予 <xliff:g id="COUNT_0">%d</xliff:g> 项权限（共 <xliff:g id="COUNT_1">%d</xliff:g> 项）</item>
    </plurals>
    <plurals name="runtime_permissions_additional_count" formatted="false" msgid="931276038884210752">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 项其他权限</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 项其他权限</item>
    </plurals>
    <string name="runtime_permissions_summary_no_permissions_granted" msgid="1679758182657005375">"未授予任何权限"</string>
    <string name="runtime_permissions_summary_no_permissions_requested" msgid="7655100570513818534">"未请求任何权限"</string>
    <string name="filter_all_apps" msgid="1988403195820688644">"所有应用"</string>
    <string name="filter_enabled_apps" msgid="5395727306799456250">"已安装的应用"</string>
    <string name="filter_instant_apps" msgid="574277769963965565">"免安装应用"</string>
    <string name="filter_personal_apps" msgid="3277727374174355971">"个人应用"</string>
    <string name="filter_work_apps" msgid="24519936790795574">"工作应用"</string>
    <string name="filter_notif_all_apps" msgid="2299049859443680242">"应用：全部"</string>
    <string name="filter_notif_blocked_apps" msgid="3300375727887991342">"已屏蔽的应用"</string>
    <string name="filter_notif_urgent_channels" msgid="3972473613117159653">"类别：重要性 - 紧急"</string>
    <string name="filter_notif_low_channels" msgid="4128487387390004604">"类别：重要性 - 低"</string>
    <string name="filter_notif_blocked_channels" msgid="5880190882221644289">"类别：已关闭"</string>
    <string name="filter_notif_dnd_channels" msgid="1817930848881696728">"类别：覆盖“勿扰”设置"</string>
    <string name="advanced_apps" msgid="4812975097124803873">"高级"</string>
    <string name="configure_apps" msgid="6685680790825882528">"配置应用"</string>
    <string name="unknown_app" msgid="5275921288718717656">"未知应用"</string>
    <string name="app_permissions" msgid="4148222031991883874">"应用权限"</string>
    <string name="app_permissions_summary" msgid="5163974162150406324">"目前使用<xliff:g id="APPS">%1$s</xliff:g>的应用"</string>
    <string name="tap_to_wake" msgid="7211944147196888807">"点按唤醒"</string>
    <string name="tap_to_wake_summary" msgid="4341387904987585616">"在屏幕上的任意位置点按两次即可唤醒设备"</string>
    <string name="domain_urls_title" msgid="3132983644568821250">"打开链接"</string>
    <string name="domain_urls_summary_none" msgid="2639588015479657864">"不打开支持的链接"</string>
    <string name="domain_urls_summary_one" msgid="3704934031930978405">"打开 <xliff:g id="DOMAIN">%s</xliff:g>"</string>
    <string name="domain_urls_summary_some" msgid="3950089361819428455">"打开 <xliff:g id="DOMAIN">%s</xliff:g> 和其他网址"</string>
    <string name="domain_urls_apps_summary_off" msgid="1833056772600031220">"没有应用可打开支持的链接"</string>
    <plurals name="domain_urls_apps_summary_on" formatted="false" msgid="240214361240709399">
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个应用可打开支持的链接</item>
      <item quantity="one">1 个应用可打开支持的链接</item>
    </plurals>
    <string name="app_link_open_always" msgid="2474058700623948148">"在此应用中打开"</string>
    <string name="app_link_open_ask" msgid="7800878430190575991">"每次都询问"</string>
    <string name="app_link_open_never" msgid="3407647600352398543">"不要在此应用中打开"</string>
    <string name="fingerprint_not_recognized" msgid="1739529686957438119">"无法识别"</string>
    <string name="default_apps_title" msgid="1660450272764331490">"默认应用"</string>
    <string name="default_for_work" msgid="9152194239366247932">"默认工作应用"</string>
    <string name="assist_and_voice_input_title" msgid="1733165754793221197">"助手和语音输入"</string>
    <string name="default_assist_title" msgid="8868488975409247921">"助手应用"</string>
    <string name="assistant_security_warning_title" msgid="8673079231955467177">"要将<xliff:g id="ASSISTANT_APP_NAME">%s</xliff:g>设为您的辅助应用吗？"</string>
    <string name="assistant_security_warning" msgid="8498726261327239136">"辅助应用将可读取您系统中使用的应用的相关信息，其中包括您屏幕上显示的信息或应用中可使用的信息。"</string>
    <string name="assistant_security_warning_agree" msgid="7710290206928033908">"同意"</string>
    <string name="assistant_security_warning_disagree" msgid="877419950830205913">"不同意"</string>
    <string name="choose_voice_input_title" msgid="975471367067718019">"选择语音输入"</string>
    <string name="default_browser_title" msgid="8101772675085814670">"浏览器应用"</string>
    <string name="default_browser_title_none" msgid="2124785489953628553">"没有默认浏览器"</string>
    <string name="default_phone_title" msgid="282005908059637350">"电话应用"</string>
    <string name="default_app" msgid="6864503001385843060">"（默认）"</string>
    <string name="system_app" msgid="9068313769550747372">"（系统）"</string>
    <string name="system_default_app" msgid="3091113402349739037">"（系统默认）"</string>
    <string name="apps_storage" msgid="4353308027210435513">"应用所占存储空间"</string>
    <string name="usage_access" msgid="5479504953931038165">"使用情况访问权限"</string>
    <string name="permit_usage_access" msgid="4012876269445832300">"允许查看使用情况"</string>
    <string name="app_usage_preference" msgid="7065701732733134991">"应用使用偏好设置"</string>
    <string name="time_spent_in_app_pref_title" msgid="649419747540933845">"应用使用时间"</string>
    <string name="usage_access_description" msgid="1352111094596416795">"给应用授予“使用情况访问权限”后，该应用就能跟踪您正在使用的其他应用和使用频率，以及您的运营商、语言设置及其他详细信息。"</string>
    <string name="memory_settings_title" msgid="7490541005204254222">"内存"</string>
    <string name="memory_details_title" msgid="8542565326053693320">"内存详情"</string>
    <string name="always_running" msgid="6042448320077429656">"始终运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="sometimes_running" msgid="6611250683037700864">"偶尔运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="rarely_running" msgid="348413460168817458">"极少运行 (<xliff:g id="PERCENTAGE">%s</xliff:g>)"</string>
    <string name="memory_max_use" msgid="6874803757715963097">"最高内存使用量"</string>
    <string name="memory_avg_use" msgid="7382015389130622870">"平均内存使用量"</string>
    <string name="memory_max_desc" msgid="2861832149718335864">"最高内存使用量：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory_avg_desc" msgid="1551240906596518412">"平均内存使用量：<xliff:g id="MEMORY">%1$s</xliff:g>"</string>
    <string name="memory_use_running_format" msgid="4172488041800743760">"<xliff:g id="MEMORY">%1$s</xliff:g> / <xliff:g id="RUNNING">%2$s</xliff:g>"</string>
    <string name="process_format" msgid="77905604092541454">"<xliff:g id="APP_NAME">%1$s</xliff:g> (<xliff:g id="COUNT">%2$d</xliff:g>)"</string>
    <string name="high_power_apps" msgid="3459065925679828230">"电池优化"</string>
    <string name="additional_battery_info" msgid="4754099329165411970">"电池用量提醒"</string>
    <string name="show_all_apps" msgid="1512506948197818534">"显示完整的设备用电量"</string>
    <string name="hide_extra_apps" msgid="5016497281322459633">"显示应用的耗电情况"</string>
    <string name="power_high_usage_title" msgid="6027369425057347826">"耗电量高"</string>
    <plurals name="power_high_usage_summary" formatted="false" msgid="467347882627862744">
      <item quantity="other"><xliff:g id="NUMBER">%2$d</xliff:g> 个应用的行为异常</item>
      <item quantity="one"><xliff:g id="APP">%1$s</xliff:g>的行为异常</item>
    </plurals>
    <plurals name="power_high_usage_title" formatted="false" msgid="3826660033363082922">
      <item quantity="other">多个应用正在消耗大量电池电量</item>
      <item quantity="one"><xliff:g id="APP">%1$s</xliff:g>正在消耗大量电池电量</item>
    </plurals>
    <string name="high_power_filter_on" msgid="3222265297576680099">"未优化"</string>
    <string name="high_power_on" msgid="6216293998227583810">"未优化"</string>
    <string name="high_power_off" msgid="3393904131961263278">"优化电池使用"</string>
    <string name="high_power_system" msgid="7362862974428225301">"没有电池优化设置"</string>
    <string name="high_power_desc" msgid="6283926163708585760">"不应用电池优化设置，但电量的消耗速度可能会更快。"</string>
    <string name="high_power_prompt_title" msgid="6358673688590282655">"要允许应用始终在后台运行吗？"</string>
    <string name="high_power_prompt_body" msgid="1031422980602565049">"允许“<xliff:g id="APP_NAME">%1$s</xliff:g>”始终在后台运行可能会缩短电池的续航时间。\n\n您以后可以在“设置”&gt;“应用和通知”中更改此设置。"</string>
    <string name="battery_summary" msgid="8044042095190688654">"自上次充满电后已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="battery_power_management" msgid="5571519699679107523">"电源管理"</string>
    <string name="no_battery_summary" msgid="3528036835462846814">"自上次充满电后未消耗任何电量"</string>
    <string name="app_notification_preferences" msgid="1599319335092722613">"应用设置"</string>
    <string name="system_ui_settings" msgid="579824306467081123">"显示系统界面调节工具"</string>
    <string name="additional_permissions" msgid="6463784193877056080">"其他权限"</string>
    <string name="additional_permissions_more" msgid="3538612272673191451">"另外 <xliff:g id="COUNT">%1$d</xliff:g> 项"</string>
    <string name="share_remote_bugreport_dialog_title" msgid="1124840737776588602">"要分享错误报告吗？"</string>
    <string name="share_remote_bugreport_dialog_message_finished" msgid="4973886976504823801">"您的 IT 管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据。"</string>
    <string name="share_remote_bugreport_dialog_message" msgid="3495929560689435496">"您的 IT 管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据，设备运行速度也可能会因此而暂时减慢。"</string>
    <string name="sharing_remote_bugreport_dialog_message" msgid="5859287696666024466">"正在与您的 IT 管理员分享这份错误报告。要了解详情，请与对方联系。"</string>
    <string name="share_remote_bugreport_action" msgid="532226159318779397">"分享"</string>
    <string name="decline_remote_bugreport_action" msgid="518720235407565134">"拒绝"</string>
    <string name="usb_use_charging_only" msgid="4800495064747543954">"不进行数据传输"</string>
    <string name="usb_use_charging_only_desc" msgid="3066256793008540627">"仅为此设备充电"</string>
    <string name="usb_use_power_only" msgid="3236391691786786070">"为连接的设备充电"</string>
    <string name="usb_use_file_transfers" msgid="1223134119354320726">"文件传输"</string>
    <string name="usb_use_file_transfers_desc" msgid="4235764784331804488">"将文件传输至其他设备"</string>
    <string name="usb_use_photo_transfers" msgid="8192719651229326283">"PTP"</string>
    <string name="usb_use_photo_transfers_desc" msgid="2963034811151325996">"如果 MTP 不受支持，则传输照片或文件 (PTP)"</string>
    <string name="usb_use_tethering" msgid="3944506882789422118">"USB 网络共享"</string>
    <string name="usb_use_MIDI" msgid="5116404702692483166">"MIDI"</string>
    <string name="usb_use_MIDI_desc" msgid="8473936990076693175">"将此设备用作 MIDI 设备"</string>
    <string name="usb_use" msgid="3372728031108932425">"USB 的用途"</string>
    <string name="usb_default_label" msgid="2211094045594574774">"默认 USB 配置"</string>
    <string name="usb_default_info" msgid="8864535445796200695">"连接到其他设备且您的手机处于解锁状态时，系统就会应用这些设置。请只连接到可信设备。"</string>
    <string name="usb_pref" msgid="1400617804525116158">"USB"</string>
    <string name="usb_preference" msgid="7394265019817945275">"USB 偏好设置"</string>
    <string name="usb_control_title" msgid="4404322722995917160">"USB 受控于："</string>
    <string name="usb_control_host" msgid="2276710819046647200">"连接的设备"</string>
    <string name="usb_control_device" msgid="5821511964163469463">"此设备"</string>
    <string name="usb_switching" msgid="8995313698715545619">"正在切换…"</string>
    <string name="usb_switching_failed" msgid="4156073015692409651">"无法切换"</string>
    <string name="usb_summary_charging_only" msgid="7544327009143659751">"为此设备充电"</string>
    <string name="usb_summary_power_only" msgid="1996391096369798526">"为连接的设备充电"</string>
    <string name="usb_summary_file_transfers" msgid="6925168380589489645">"文件传输"</string>
    <string name="usb_summary_tether" msgid="951190049557074535">"USB 网络共享"</string>
    <string name="usb_summary_photo_transfers" msgid="665584667685030007">"PTP"</string>
    <string name="usb_summary_MIDI" msgid="2399066753961085360">"MIDI"</string>
    <string name="usb_summary_file_transfers_power" msgid="7700800611455849806">"开启文件传输模式并为其他设备充电"</string>
    <string name="usb_summary_tether_power" msgid="5825335393952752238">"开启 USB 网络共享模式并为其他设备充电"</string>
    <string name="usb_summary_photo_transfers_power" msgid="6826058111908423069">"开启 PTP 模式并为其他设备充电"</string>
    <string name="usb_summary_MIDI_power" msgid="3308250484012677596">"开启 MIDI 模式并为其他设备充电"</string>
    <string name="background_check_pref" msgid="7550258400138010979">"后台检查"</string>
    <string name="background_check_title" msgid="4534254315824525593">"完整的后台访问权限"</string>
    <string name="assist_access_context_title" msgid="2269032346698890257">"使用屏幕上的文字内容"</string>
    <string name="assist_access_context_summary" msgid="1991421283142279560">"允许助手应用使用屏幕上的文字内容"</string>
    <string name="assist_access_screenshot_title" msgid="4034721336291215819">"使用屏幕截图"</string>
    <string name="assist_access_screenshot_summary" msgid="6761636689013259901">"允许助手应用使用屏幕截图"</string>
    <string name="assist_flash_title" msgid="506661221230034891">"闪烁屏幕"</string>
    <string name="assist_flash_summary" msgid="9160668468824099262">"当助手应用访问屏幕/屏幕截图上的文字时，让屏幕边缘闪烁"</string>
    <string name="assist_footer" msgid="1982791172085896864">"助手应用可根据您当前浏览的屏幕上的内容为您提供帮助。某些应用同时支持启动器和语音输入服务，可为您提供更全面的协助。"</string>
    <string name="average_memory_use" msgid="829566450150198512">"平均内存使用量"</string>
    <string name="maximum_memory_use" msgid="7493720799710132496">"最高内存使用量"</string>
    <string name="memory_usage" msgid="1781358557214390033">"内存使用量"</string>
    <string name="app_list_memory_use" msgid="6987417883876419338">"应用的内存使用量"</string>
    <string name="memory_details" msgid="5943436005716991782">"详细信息"</string>
    <string name="memory_use_summary" msgid="5608257211903075754">"过去 3 小时内平均使用了 <xliff:g id="SIZE">%1$s</xliff:g> 内存"</string>
    <string name="no_memory_use_summary" msgid="2016900536806235588">"过去 3 小时内未使用任何内存"</string>
    <string name="sort_avg_use" msgid="3998036180505143129">"按平均使用量排序"</string>
    <string name="sort_max_use" msgid="4629247978290075124">"按最高使用量排序"</string>
    <string name="memory_performance" msgid="5661005192284103281">"性能"</string>
    <string name="total_memory" msgid="2017287600738630165">"总内存"</string>
    <string name="average_used" msgid="5338339266517245782">"平均使用量 (%)"</string>
    <string name="free_memory" msgid="4003936141603549746">"可用"</string>
    <string name="memory_usage_apps" msgid="5650192998273294098">"各个应用使用的内存"</string>
    <plurals name="memory_usage_apps_summary" formatted="false" msgid="6089210945574265774">
      <item quantity="other">过去 <xliff:g id="DURATION_1">%2$s</xliff:g>内有 <xliff:g id="COUNT">%1$d</xliff:g> 个应用使用了内存</item>
      <item quantity="one">过去 <xliff:g id="DURATION_0">%2$s</xliff:g>内有 1 个应用使用了内存</item>
    </plurals>
    <string name="running_frequency" msgid="6622624669948277693">"频率"</string>
    <string name="memory_maximum_usage" msgid="6513785462055278341">"最高使用量"</string>
    <string name="no_data_usage" msgid="9131454024293628063">"未使用任何数据流量"</string>
    <string name="zen_access_warning_dialog_title" msgid="1198189958031157142">"要允许<xliff:g id="APP">%1$s</xliff:g>获取“勿扰”模式的使用权限吗？"</string>
    <string name="zen_access_warning_dialog_summary" msgid="4015885767653010873">"此应用将可开启/关闭“勿扰”模式以及更改相关设置。"</string>
    <string name="zen_access_disabled_package_warning" msgid="302820100078584431">"必须保持启用状态，因为通知访问权限已开启"</string>
    <string name="zen_access_revoke_warning_dialog_title" msgid="558779234015793950">"要撤消<xliff:g id="APP">%1$s</xliff:g>对勿扰模式的使用权限吗？"</string>
    <string name="zen_access_revoke_warning_dialog_summary" msgid="5518216907304930148">"系统将移除此应用创建的所有勿扰规则。"</string>
    <string name="ignore_optimizations_on" msgid="6915689518016285116">"不优化"</string>
    <string name="ignore_optimizations_off" msgid="6153196256410296835">"优化"</string>
    <string name="ignore_optimizations_on_desc" msgid="3549930955839111652">"电池电量可能会更快耗尽。系统将不再限制应用在后台使用电量。"</string>
    <string name="ignore_optimizations_off_desc" msgid="5255731062045426544">"建议选择此项以延长电池续航时间"</string>
    <string name="ignore_optimizations_title" msgid="2829637961185027768">"要允许<xliff:g id="APP">%s</xliff:g>忽略电池优化吗？"</string>
    <string name="app_list_preference_none" msgid="108006867520327904">"无"</string>
    <string name="work_profile_usage_access_warning" msgid="2918050775124911939">"即使关闭此应用的使用情况访问权限，也无法阻止您的管理员跟踪您工作资料中应用的数据用量"</string>
    <string name="accessibility_lock_screen_progress" msgid="2408292742980383166">"已输入 <xliff:g id="COUNT_0">%1$d</xliff:g> 个字符（共可输入 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）"</string>
    <string name="draw_overlay" msgid="6564116025404257047">"显示在其他应用的上层"</string>
    <string name="system_alert_window_settings" msgid="8466613169103527868">"显示在其他应用的上层"</string>
    <string name="system_alert_window_apps_title" msgid="7005760279028569491">"应用"</string>
    <string name="system_alert_window_access_title" msgid="6297115362542361241">"显示在其他应用的上层"</string>
    <string name="permit_draw_overlay" msgid="7456536798718633432">"允许显示在其他应用的上层"</string>
    <string name="allow_overlay_description" msgid="8961670023925421358">"允许此应用显示在您当前使用的其他应用的上层。这可能会干扰您使用相关应用，或变更这类应用的显示或运行方式。"</string>
    <string name="keywords_vr_listener" msgid="7441221822576384680">"vr 虚拟实境 监听器 立体 助手服务"</string>
    <string name="keywords_system_alert_window" msgid="5049498015597864850">"系统 提醒 窗口 对话框 显示 上层 其他应用"</string>
    <string name="overlay_settings" msgid="6930854109449524280">"显示在其他应用的上层"</string>
    <string name="system_alert_window_summary" msgid="602892301318324492">"<xliff:g id="COUNT_0">%1$d</xliff:g> 个（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）应用可以显示在其他应用的上层"</string>
    <string name="filter_overlay_apps" msgid="6965969283342557573">"具有该权限的应用"</string>
    <string name="app_permission_summary_allowed" msgid="1505409933012886711">"允许"</string>
    <string name="app_permission_summary_not_allowed" msgid="2592617058101882802">"不允许"</string>
    <string name="keywords_install_other_apps" msgid="761078076051006558">"安装应用 未知来源"</string>
    <string name="write_settings" msgid="4797457275727195681">"修改系统设置"</string>
    <string name="keywords_write_settings" msgid="6415597272561105138">"写入 修改 系统 设置"</string>
    <string name="write_settings_summary" msgid="4302268998611412696">"已允许 <xliff:g id="COUNT_0">%1$d</xliff:g> 个应用（共 <xliff:g id="COUNT_1">%2$d</xliff:g> 个）修改系统设置"</string>
    <string name="filter_install_sources_apps" msgid="3102976274848199118">"可以安装其他应用"</string>
    <string name="filter_write_settings_apps" msgid="2914615026197322551">"可修改系统设置"</string>
    <string name="write_settings_title" msgid="4232152481902542284">"可修改系统设置"</string>
    <string name="write_system_settings" msgid="3482913590601096763">"修改系统设置"</string>
    <string name="permit_write_settings" msgid="658555006453212691">"允许修改系统设置"</string>
    <string name="write_settings_description" msgid="6868293938839954623">"此权限允许应用修改系统设置。"</string>
    <string name="write_settings_on" msgid="8230580416068832239">"允许"</string>
    <string name="write_settings_off" msgid="5156104383386336233">"不允许"</string>
    <string name="external_source_switch_title" msgid="3621381992793251070">"允许来自此来源的应用"</string>
    <string name="camera_gesture_title" msgid="1075838577642393011">"扭转两次即可打开相机"</string>
    <string name="camera_gesture_desc" msgid="1831390075255870960">"扭转手腕两次即可打开相机应用"</string>
    <string name="camera_double_tap_power_gesture_title" msgid="1651873760405034645">"按电源按钮两次即可打开相机"</string>
    <string name="camera_double_tap_power_gesture_desc" msgid="7355664631775680376">"在不解锁屏幕的情况下快速打开相机"</string>
    <string name="screen_zoom_title" msgid="5233515303733473927">"显示大小"</string>
    <string name="screen_zoom_short_summary" msgid="7291960817349834688">"放大或缩小屏幕上的内容"</string>
    <string name="screen_zoom_keywords" msgid="9176477565403352552">"显示密度, 屏幕缩放, 比例, 调整比例"</string>
    <string name="screen_zoom_summary" msgid="6445488991799015407">"缩小或放大屏幕上的内容。屏幕上部分应用的位置可能会有变动。"</string>
    <string name="screen_zoom_preview_title" msgid="4680671508172336572">"预览"</string>
    <string name="screen_zoom_make_smaller_desc" msgid="4622359904253364742">"缩小"</string>
    <string name="screen_zoom_make_larger_desc" msgid="2236171043607896594">"放大"</string>
    <string name="screen_zoom_conversation_icon_alex" msgid="8443032489384985820">"A"</string>
    <string name="screen_zoom_conversation_icon_pete" msgid="998709701837681129">"P"</string>
    <string name="screen_zoom_conversation_message_1" msgid="6546951024984852686">"嗨，皮皮！"</string>
    <string name="screen_zoom_conversation_message_2" msgid="6935424214137738647">"嗨，今天一起去喝杯咖啡聚聚，怎么样？"</string>
    <string name="screen_zoom_conversation_message_3" msgid="5218221201861387402">"好啊！我知道这附近有一家不错的咖啡馆。"</string>
    <string name="screen_zoom_conversation_message_4" msgid="5564676794767555447">"太棒了！"</string>
    <string name="screen_zoom_conversation_timestamp_1" msgid="7453710416319650556">"周二下午 6:00"</string>
    <string name="screen_zoom_conversation_timestamp_2" msgid="7107225702890747588">"周二下午 6:01"</string>
    <string name="screen_zoom_conversation_timestamp_3" msgid="3785674344762707688">"周二下午 6:02"</string>
    <string name="screen_zoom_conversation_timestamp_4" msgid="2511469395448561259">"周二下午 6:03"</string>
    <string name="disconnected" msgid="4836600637485526329">"未连接"</string>
    <string name="data_usage_summary_format" msgid="7507047900192160585">"已使用 <xliff:g id="AMOUNT">%1$s</xliff:g>"</string>
    <string name="data_usage_wifi_format" msgid="5417296451392612860">"已使用 <xliff:g id="AMOUNT">^1</xliff:g>（通过 WLAN）"</string>
    <plurals name="notification_summary" formatted="false" msgid="3941492005316143599">
      <item quantity="other">已屏蔽 <xliff:g id="COUNT">%d</xliff:g> 个应用的通知</item>
      <item quantity="one">已屏蔽 1 个应用的通知</item>
    </plurals>
    <string name="notification_summary_none" msgid="4586376436702610">"目前设为接收所有应用的通知"</string>
    <string name="apps_summary" msgid="193158055537070092">"已安装 <xliff:g id="COUNT">%1$d</xliff:g> 个应用"</string>
    <string name="apps_summary_example" msgid="2118896966712746139">"已安装 24 个应用"</string>
    <string name="storage_summary" msgid="3801281635351732202">"已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g> - 还剩 <xliff:g id="FREE_SPACE">%2$s</xliff:g>"</string>
    <string name="storage_summary_with_sdcard" msgid="3290457009629490121">"内部存储空间：已使用 <xliff:g id="PERCENTAGE">%1$s</xliff:g>，还剩 <xliff:g id="FREE_SPACE">%2$s</xliff:g>"</string>
    <string name="display_summary" msgid="6737806235882127328">"闲置 <xliff:g id="TIMEOUT_DESCRIPTION">%1$s</xliff:g>后进入休眠状态"</string>
    <string name="display_dashboard_summary" msgid="4145888780290131488">"壁纸、休眠、字体大小"</string>
    <string name="display_summary_example" msgid="9102633726811090523">"闲置 10 分钟后会进入休眠状态"</string>
    <string name="memory_summary" msgid="8080825904671961872">"平均内存用量为 <xliff:g id="USED_MEMORY">%1$s</xliff:g>，共 <xliff:g id="TOTAL_MEMORY">%2$s</xliff:g>"</string>
    <string name="users_summary" msgid="1674864467098487328">"目前登录的用户为：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="payment_summary" msgid="3472482669588561110">"默认使用<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="location_on_summary" msgid="3637699010986988970">"开启"</string>
    <string name="location_off_summary" msgid="7217264690673949107">"关闭"</string>
    <string name="backup_disabled" msgid="485189128759595412">"备份功能已停用"</string>
    <string name="android_version_summary" msgid="2935995161657697278">"已更新至 Android <xliff:g id="VERSION">%1$s</xliff:g>"</string>
    <string name="android_version_pending_update_summary" msgid="487831391976523090">"有新版本可用"</string>
    <string name="disabled_by_policy_title" msgid="627023216027648534">"不允许执行此操作"</string>
    <string name="disabled_by_policy_title_adjust_volume" msgid="3208724801293696486">"无法调节音量"</string>
    <string name="disabled_by_policy_title_outgoing_calls" msgid="7919816644946067058">"不允许使用通话功能"</string>
    <string name="disabled_by_policy_title_sms" msgid="5733307423899610340">"不允许使用短信功能"</string>
    <string name="disabled_by_policy_title_camera" msgid="6225008536855644874">"不允许使用相机"</string>
    <string name="disabled_by_policy_title_screen_capture" msgid="4066913623298047094">"不允许使用屏幕截图功能"</string>
    <string name="disabled_by_policy_title_turn_off_backups" msgid="7330460584199383321">"无法关闭备份功能"</string>
    <string name="disabled_by_policy_title_suspend_packages" msgid="7872038990805477554">"无法打开此应用"</string>
    <string name="default_admin_support_msg" msgid="4489678214035485367">"如有任何问题，请与您的 IT 管理员联系"</string>
    <string name="admin_support_more_info" msgid="8901377038510512654">"更多详情"</string>
    <string name="admin_profile_owner_message" msgid="5860816886981109626">"您的管理员可以监控和管理与您的工作资料相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="admin_profile_owner_user_message" msgid="3842630535450382172">"您的管理员可以监控和管理与此用户相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="admin_device_owner_message" msgid="6232893638259790789">"您的管理员可以监控和管理与此设备相关的应用和数据（其中包括设置、权限、企业权限、网络活动和设备的位置信息）。"</string>
    <string name="condition_turn_off" msgid="1960945836880080298">"关闭"</string>
    <string name="condition_turn_on" msgid="9089876276117874591">"开启"</string>
    <string name="condition_expand_show" msgid="608202020023489939">"显示"</string>
    <string name="condition_expand_hide" msgid="948507739223760667">"隐藏"</string>
    <string name="condition_hotspot_title" msgid="7778958849468560027">"热点已开启"</string>
    <string name="condition_hotspot_summary" msgid="3433182779269409683">"便携式 WLAN 热点“<xliff:g id="ID_1">%1$s</xliff:g>”已开启，因此系统关闭了该设备的 WLAN。"</string>
    <string name="condition_airplane_title" msgid="287356299107070503">"已开启飞行模式"</string>
    <string name="condition_airplane_summary" msgid="7098837989877102577">"当飞行模式开启时，WLAN、蓝牙和移动网络都将关闭。您可以视需要重新开启 WLAN 和蓝牙。"</string>
    <string name="condition_zen_title" msgid="2897779738211625">"“勿扰”模式已开启"</string>
    <string name="condition_battery_title" msgid="3272131008388575349">"省电模式已开启"</string>
    <string name="condition_battery_summary" msgid="507347940746895275">"省电模式会关闭部分设备功能并限制应用"</string>
    <string name="condition_cellular_title" msgid="1327317003797575735">"移动数据网络已关闭"</string>
    <string name="condition_cellular_summary" msgid="1818046558419658463">"您只能通过 WLAN 网络连接到互联网"</string>
    <string name="condition_bg_data_title" msgid="2483860304802846542">"流量节省程序已开启"</string>
    <string name="condition_bg_data_summary" msgid="656957852895282228">"您必须连接 WLAN 网络才能使用后台数据。如果无法连接 WLAN 网络，则部分应用或服务可能会受影响。"</string>
    <string name="condition_work_title" msgid="7293722361184366648">"工作资料已关闭"</string>
    <string name="condition_work_summary" msgid="7543202177571590378">"与您的工作资料相关的应用、后台同步功能和其他功能均已关闭。"</string>
    <string name="condition_device_muted_action_turn_on_sound" msgid="4930240942726349213">"开启音效"</string>
    <string name="condition_device_muted_title" product="tablet" msgid="3095044864508335783">"设备已设为静音"</string>
    <string name="condition_device_muted_title" product="default" msgid="5818278137378379647">"手机已设为静音"</string>
    <string name="condition_device_muted_summary" msgid="5445341185705628047">"有来电和通知时不会发出铃声"</string>
    <string name="condition_device_vibrate_title" product="tablet" msgid="1983420639621523345">"设备已设为振动"</string>
    <string name="condition_device_vibrate_title" product="default" msgid="1087633233379991925">"手机已设为振动"</string>
    <string name="condition_device_vibrate_summary" product="tablet" msgid="433514444618164607">"设备在有来电和通知时会振动"</string>
    <string name="condition_device_vibrate_summary" product="default" msgid="5877034997839162763">"手机在有来电和通知时会振动"</string>
    <string name="night_display_suggestion_title" msgid="6602129097059325291">"设置“夜间模式”时间安排"</string>
    <string name="night_display_suggestion_summary" msgid="228346372178218442">"每晚自动调节屏幕色调"</string>
    <string name="condition_night_display_title" msgid="5599814941976856183">"“夜间模式”已开启"</string>
    <string name="condition_night_display_summary" msgid="5443722724310650381">"屏幕已变成琥珀色，这可能有助于您安然入睡。"</string>
    <string name="suggestions_title_v2" msgid="5601181602924147569">"为您推荐"</string>
    <string name="suggestions_title" msgid="7280792342273268377">"建议"</string>
    <string name="suggestions_summary" msgid="2509040178581728056">"+<xliff:g id="ID_1">%1$d</xliff:g>"</string>
    <string name="suggestions_more_title" msgid="8223690393059519879">"另外 <xliff:g id="ID_1">%1$d</xliff:g> 条"</string>
    <plurals name="suggestions_collapsed_title" formatted="false" msgid="1857433444865249823">
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 条建议</item>
      <item quantity="one">1 条建议</item>
    </plurals>
    <plurals name="suggestions_collapsed_summary" formatted="false" msgid="7680263825371165461">
      <item quantity="other">另外 <xliff:g id="COUNT">%1$d</xliff:g> 条建议</item>
      <item quantity="one">另外 1 条建议</item>
    </plurals>
    <string name="suggestion_remove" msgid="904627293892092439">"移除"</string>
    <string name="color_temperature" msgid="2070126836910615605">"冷色温"</string>
    <string name="color_temperature_desc" msgid="4793729830226404052">"使用较冷的显示颜色"</string>
    <string name="color_temperature_toast" msgid="4974218172133854827">"要应用颜色更改，请关闭屏幕"</string>
    <string name="camera_laser_sensor_switch" msgid="8913588990743234440">"相机激光传感器"</string>
    <string name="ota_disable_automatic_update" msgid="2319639631655915050">"系统自动更新"</string>
    <string name="ota_disable_automatic_update_summary" msgid="940729694354373087">"重启设备时应用更新"</string>
    <string name="usage" msgid="2977875522080448986">"流量消耗"</string>
    <string name="cellular_data_usage" msgid="2155683719898158203">"移动数据用量"</string>
    <string name="app_cellular_data_usage" msgid="5468472735806533448">"应用的流量使用情况"</string>
    <string name="wifi_data_usage" msgid="771603760674507659">"WLAN 流量用量"</string>
    <string name="ethernet_data_usage" msgid="5108764537574354616">"以太网流量用量"</string>
    <string name="wifi" msgid="1081550856200013637">"WLAN"</string>
    <string name="ethernet" msgid="6600095783781389720">"以太网"</string>
    <string name="cell_data_template" msgid="405684854174361041">"<xliff:g id="AMOUNT">^1</xliff:g>（移动数据流量）"</string>
    <string name="wifi_data_template" msgid="6265570748799357386">"<xliff:g id="AMOUNT">^1</xliff:g> WLAN 流量"</string>
    <string name="ethernet_data_template" msgid="5782476509881033590">"<xliff:g id="AMOUNT">^1</xliff:g> 以太网流量"</string>
    <string name="cell_warning_only" msgid="763147658209027140">"数据流量警告：<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="cell_warning_and_limit" msgid="2273413629267437470">"数据流量警告：<xliff:g id="ID_1">%1$s</xliff:g>/数据流量上限：<xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="billing_cycle" msgid="6614597736285325497">"数据流量警告和上限"</string>
    <string name="app_usage_cycle" msgid="8877223251648092131">"应用流量消耗重计日期"</string>
    <string name="cell_data_warning" msgid="1985956818884847057">"数据流量警告：<xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="cell_data_limit" msgid="1578367585799358854">"数据流量上限：<xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="cell_data_warning_and_limit" msgid="6888825370687743208">"数据流量警告：<xliff:g id="ID_1">^1</xliff:g>/数据流量上限：<xliff:g id="ID_2">^2</xliff:g>"</string>
    <string name="billing_cycle_fragment_summary" msgid="8231066353654583106">"每个月的 <xliff:g id="ID_1">%1$s</xliff:g> 号"</string>
    <string name="network_restrictions" msgid="8234695294536675380">"网络流量限制"</string>
    <plurals name="network_restrictions_summary" formatted="false" msgid="4301618027244595839">
      <item quantity="other"><xliff:g id="COUNT">%1$d</xliff:g> 项限制</item>
      <item quantity="one">1 项限制</item>
    </plurals>
    <string name="operator_warning" msgid="1862988028996859195">"运营商的流量计算方式可能与您设备的计算方式不同"</string>
    <string name="data_used_template" msgid="3245919669966296505">"已使用 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="set_data_warning" msgid="6115364758236594593">"设置数据流量警告"</string>
    <string name="data_warning" msgid="209133958008062117">"数据流量警告"</string>
    <string name="data_warning_footnote" msgid="776341964125603711">"您的设备会自行衡量数据流量警告和数据流量上限。这可能与运营商的数据不同。"</string>
    <string name="set_data_limit" msgid="2901526323210516923">"设置数据流量上限"</string>
    <string name="data_limit" msgid="1885406964934590552">"数据流量上限"</string>
    <string name="data_usage_template" msgid="2923744765873163859">"<xliff:g id="ID_2">%2$s</xliff:g>期间已使用 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="configure" msgid="1029654422228677273">"配置"</string>
    <string name="data_usage_other_apps" msgid="3272872663517382050">"其他消耗流量的应用"</string>
    <plurals name="data_saver_unrestricted_summary" formatted="false" msgid="2635267833484232703">
      <item quantity="other">已允许 <xliff:g id="COUNT">%1$d</xliff:g> 个应用在流量节省程序开启时无限量使用数据流量。</item>
      <item quantity="one">已允许 1 个应用在流量节省程序开启时无限量使用数据流量。</item>
    </plurals>
    <string name="data_usage_title" msgid="3659356290392241789">"主要数据"</string>
    <string name="data_usage_wifi_title" msgid="7063659423081820720">"WLAN 数据"</string>
    <string name="data_used" msgid="5116389957228457203">"已使用 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="data_used_formatted" msgid="2989129931961311051">"已使用 <xliff:g id="ID_1">^1</xliff:g> <xliff:g id="ID_2">^2</xliff:g>"</string>
    <string name="data_overusage" msgid="1134445012475270295">"超过 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <string name="data_remaining" msgid="8998091725895502181">"还剩 <xliff:g id="ID_1">^1</xliff:g>"</string>
    <plurals name="billing_cycle_days_left" formatted="false" msgid="456503215317213651">
      <item quantity="other">还剩 %d 天</item>
      <item quantity="one">还剩 %d 天</item>
    </plurals>
    <string name="billing_cycle_none_left" msgid="5892754995098583472">"已没有剩余时间"</string>
    <string name="billing_cycle_less_than_one_day_left" msgid="825838050296069404">"还剩不到 1 天"</string>
    <string name="carrier_and_update_text" msgid="7963409972475063897">"<xliff:g id="ID_1">^1</xliff:g>已在 <xliff:g id="ID_2">^2</xliff:g>前更新"</string>
    <string name="no_carrier_update_text" msgid="3277403390316201982">"已在 <xliff:g id="ID_1">^2</xliff:g>前更新"</string>
    <string name="carrier_and_update_now_text" msgid="4057997726060106722">"刚刚由<xliff:g id="ID_1">^1</xliff:g>更新"</string>
    <string name="no_carrier_update_now_text" msgid="1766859656868932996">"刚刚更新"</string>
    <string name="launch_mdp_app_text" msgid="6751296320061773169">"查看流量套餐"</string>
    <string name="launch_wifi_text" msgid="2311424914664372687">"查看详情"</string>
    <string name="data_saver_title" msgid="8034286939200289826">"流量节省程序"</string>
    <string name="unrestricted_data_saver" msgid="952796077540228711">"不受流量限制"</string>
    <string name="restrict_background_blacklisted" msgid="3995443391711013068">"后台数据已关闭"</string>
    <string name="data_saver_on" msgid="6774217590237934709">"开启"</string>
    <string name="data_saver_off" msgid="6892309031162738794">"关闭"</string>
    <string name="data_saver_switch_title" msgid="836312690356005669">"使用流量节省程序"</string>
    <string name="unrestricted_app_title" msgid="4465437191723332066">"不限制数据流量用量"</string>
    <string name="unrestricted_app_summary" msgid="6458008993501723912">"允许在流量节省程序开启时无限量使用数据流量"</string>
    <string name="home_app" msgid="4066188520886810030">"主屏幕应用"</string>
    <string name="no_default_home" msgid="7184117487704520238">"没有默认主屏幕"</string>
    <string name="lockpattern_settings_require_cred_before_startup" msgid="3832020101401318248">"安全启动"</string>
    <string name="lockpattern_settings_require_pattern_before_startup_summary" msgid="7873036097628404476">"必须绘制解锁图案才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="lockpattern_settings_require_pin_before_startup_summary" msgid="6022831284097476933">"必须输入 PIN 码才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="lockpattern_settings_require_password_before_startup_summary" msgid="6818285221244966231">"必须输入密码才能启动您的设备。此设备关闭时无法接收来电、消息、通知或闹钟提醒。"</string>
    <string name="suggestion_additional_fingerprints" msgid="2214281455363797037">"添加其他指纹"</string>
    <string name="suggestion_additional_fingerprints_summary" msgid="5471253233176471245">"使用其他指纹解锁"</string>
    <string name="battery_saver_on_summary" msgid="7722791295871319534">"开启"</string>
    <string name="battery_saver_off_scheduled_summary" msgid="3953785517002197881">"在电量降到 <xliff:g id="BATTERY_PERCENTAGE">%1$s</xliff:g> 时开启"</string>
    <string name="battery_saver_off_summary" msgid="784360321235698247">"关闭"</string>
    <string name="battery_saver_button_turn_on" msgid="3699954061337848832">"立即开启"</string>
    <string name="battery_saver_button_turn_off" msgid="5916996792004611890">"立即关闭"</string>
    <string name="not_battery_optimizing" msgid="5362861851864837617">"未使用电池优化设置"</string>
    <string name="lockscreen_remote_input" msgid="969871538778211843">"设备处于锁定状态时，禁止在通知中输入回复内容或其他文字"</string>
    <string name="default_spell_checker" msgid="8506899870026026660">"默认拼写检查工具"</string>
    <string name="choose_spell_checker" msgid="6596539862291699367">"选择拼写检查工具"</string>
    <string name="spell_checker_master_switch_title" msgid="8763132750954344372">"使用拼写检查工具"</string>
    <string name="spell_checker_not_selected" msgid="8871083796179200696">"未选择"</string>
    <string name="notification_log_no_title" msgid="5678029849672024215">"（无）"</string>
    <string name="notification_log_details_delimiter" msgid="3116559361552416747">"： "</string>
    <string name="notification_log_details_package" msgid="2596495677039100284">"pkg"</string>
    <string name="notification_log_details_key" msgid="2995791937075862968">"键"</string>
    <string name="notification_log_details_group" msgid="2430467015200368698">"群组"</string>
    <string name="notification_log_details_group_summary" msgid="7945543958255585829">"（摘要）"</string>
    <string name="notification_log_details_visibility" msgid="2552873780715930971">"公开范围"</string>
    <string name="notification_log_details_public_version" msgid="4247242364605495240">"公开版本"</string>
    <string name="notification_log_details_priority" msgid="8371354971235991398">"优先级"</string>
    <string name="notification_log_details_importance" msgid="2153168790791683139">"重要程度"</string>
    <string name="notification_log_details_explanation" msgid="1914295130775393551">"说明"</string>
    <string name="notification_log_details_badge" msgid="3258183328267662285">"可显示状态标记"</string>
    <string name="notification_log_details_content_intent" msgid="1113554570409128083">"intent"</string>
    <string name="notification_log_details_delete_intent" msgid="905118520685297007">"删除 intent"</string>
    <string name="notification_log_details_full_screen_intent" msgid="7118560817013522978">"全屏 intent"</string>
    <string name="notification_log_details_actions" msgid="242523930165118066">"操作"</string>
    <string name="notification_log_details_title" msgid="7177091647508863295">"标题"</string>
    <string name="notification_log_details_remoteinput" msgid="8328591329858827409">"远程输入"</string>
    <string name="notification_log_details_content_view" msgid="6638731378278561786">"自定义视图"</string>
    <string name="notification_log_details_extras" msgid="4188418723779942047">"其他"</string>
    <string name="notification_log_details_icon" msgid="8939114059726188218">"图标"</string>
    <string name="notification_log_details_parcel" msgid="243148037601903212">"parcel 大小"</string>
    <string name="notification_log_details_ashmem" msgid="7241814108477320636">"ashmem"</string>
    <string name="notification_log_details_sound" msgid="5506232879598808099">"提示音"</string>
    <string name="notification_log_details_vibrate" msgid="6890065466625335940">"振动"</string>
    <string name="notification_log_details_default" msgid="2345249399796730861">"默认"</string>
    <string name="notification_log_details_none" msgid="184131801230614059">"无"</string>
    <string name="notification_log_details_ranking_null" msgid="244660392058720919">"缺少排名对象。"</string>
    <string name="notification_log_details_ranking_none" msgid="599607025882587844">"排名对象不包含此键。"</string>
    <string name="display_cutout_emulation" msgid="6306593933746393170">"模拟“刘海屏”"</string>
    <string name="display_cutout_emulation_keywords" msgid="4495418317471622562">"显示屏凹口, 凹口"</string>
    <string name="display_cutout_emulation_none" msgid="5144174674654097316">"无"</string>
    <string name="special_access" msgid="3458780842491881155">"特殊应用权限"</string>
    <plurals name="special_access_summary" formatted="false" msgid="260765309935675867">
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 个应用可以无限量使用流量</item>
      <item quantity="one">1 个应用可以无限量使用流量</item>
    </plurals>
    <string name="confirm_convert_to_fbe_warning" msgid="1487005506049137659">"您真的要清除用户数据并转换为文件加密吗？"</string>
    <string name="button_confirm_convert_fbe" msgid="7101855374850373091">"清除并转换"</string>
    <string name="reset_shortcut_manager_throttling" msgid="6495066467198668994">"重置 ShortcutManager 调用频率限制"</string>
    <string name="reset_shortcut_manager_throttling_complete" msgid="1826770872063707900">"已重置 ShortcutManager 调用频率限制"</string>
    <string name="notification_suggestion_title" msgid="387052719462473500">"控制锁定屏幕上显示的信息"</string>
    <string name="notification_suggestion_summary" msgid="8521159741445416875">"显示或隐藏通知内容"</string>
    <string name="page_tab_title_summary" msgid="4070309266374993258">"全部"</string>
    <string name="page_tab_title_support" msgid="4407600495101788249">"提示和支持"</string>
    <string name="developer_smallest_width" msgid="7516950434587313360">"最小宽度"</string>
    <string name="premium_sms_none" msgid="8268105565738040566">"没有任何已安装的应用申请付费短信权限"</string>
    <string name="premium_sms_warning" msgid="9086859595338944882">"“付费短信”可能会产生费用，而且相关费用将计入您的运营商帐单。如果您为某个应用启用该权限，那么您将能够使用该应用发送付费短信。"</string>
    <string name="premium_sms_access" msgid="4660047004791638305">"付费短信权限"</string>
    <string name="bluetooth_disabled" msgid="6244000672828617410">"关闭"</string>
    <string name="bluetooth_connected_summary" msgid="7672528674593152862">"已连接到<xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_multiple_devices_summary" msgid="9173661896296663932">"已连接到多部设备"</string>
    <string name="demo_mode" msgid="2798762752209330277">"系统界面演示模式"</string>
    <string name="dark_ui_mode" msgid="2112241426441807273">"夜间模式"</string>
    <string name="dark_ui_mode_title" msgid="975299966259850992">"设置夜间模式"</string>
    <string name="quick_settings_developer_tiles" msgid="5947788063262762448">"快捷设置开发者图块"</string>
    <string name="winscope_trace_quick_settings_title" msgid="1294290008255732032">"Winscope 跟踪"</string>
    <string name="support_country_format" msgid="4502523713489559595">"<xliff:g id="COUNTRY">%1$s</xliff:g> - <xliff:g id="LANGUAGE">%2$s</xliff:g>"</string>
    <string name="managed_profile_settings_title" msgid="2729481936758125054">"工作资料设置"</string>
    <string name="managed_profile_contact_search_title" msgid="6034734926815544221">"联系人搜索"</string>
    <string name="managed_profile_contact_search_summary" msgid="5431253552272970512">"允许您的单位搜索联系人，以便识别来电者和联系人的身份"</string>
    <plurals name="hours" formatted="false" msgid="7020844602875333472">
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <plurals name="minutes" formatted="false" msgid="4666832442068789413">
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="seconds" formatted="false" msgid="3876307354560025025">
      <item quantity="other"><xliff:g id="NUMBER">%s</xliff:g> 秒</item>
      <item quantity="one">1 秒</item>
    </plurals>
    <string name="automatic_storage_manager_settings" msgid="7819434542155181607">"管理存储空间"</string>
    <string name="automatic_storage_manager_text" msgid="4562950476680600604">"存储空间管理器会从您的设备中移除已备份的照片和视频，从而释放存储空间。"</string>
    <string name="automatic_storage_manager_days_title" msgid="2017913896160914647">"移除照片和视频"</string>
    <string name="automatic_storage_manager_preference_title" msgid="5753702798151073383">"存储空间管理器"</string>
    <string name="automatic_storage_manager_master_switch_title" msgid="6792996736190821417">"使用存储空间管理器"</string>
    <string name="deletion_helper_automatic_title" msgid="6605660435498272520">"自动"</string>
    <string name="deletion_helper_manual_title" msgid="7947432164411214029">"手动"</string>
    <string name="deletion_helper_preference_title" msgid="5271510052022285884">"立即释放空间"</string>
    <string name="gesture_preference_title" msgid="5280023307132819052">"手势"</string>
    <string name="gesture_preference_summary" product="default" msgid="8627850388011956901">"通过简单手势控制手机"</string>
    <string name="gesture_preference_summary" product="tablet" msgid="4717535378272065510">"通过简单手势控制平板电脑"</string>
    <string name="gesture_preference_summary" product="device" msgid="4205941452664950852">"通过简单手势控制设备"</string>
    <string name="double_tap_power_for_camera_title" msgid="64716226816032800">"快速打开相机"</string>
    <string name="double_tap_power_for_camera_summary" msgid="242037150983277829">"不论当前显示的是何屏幕画面，只需按两次电源按钮，即可快速打开相机。"</string>
    <string name="double_tap_power_for_camera_suggestion_title" msgid="6500405261202883589">"快速打开相机"</string>
    <string name="double_twist_for_camera_mode_title" msgid="4877834147983530479">"切换相机模式"</string>
    <string name="double_twist_for_camera_mode_summary" msgid="122977081337563340"></string>
    <string name="double_twist_for_camera_suggestion_title" msgid="4689410222517954869">"自拍更便捷"</string>
    <string name="swipe_up_to_switch_apps_title" msgid="2513907834903543667">"在主屏幕按钮上向上滑动"</string>
    <string name="swipe_up_to_switch_apps_summary" msgid="5367798220225997418">"要切换应用，请在主屏幕按钮上向上滑动，再次向上滑动即可看到所有应用（适用于任何屏幕）。您屏幕的右下方将不再显示“概览”按钮。"</string>
    <string name="swipe_up_to_switch_apps_suggestion_title" msgid="1465200107913259595">"试用新版主屏幕按钮"</string>
    <string name="swipe_up_to_switch_apps_suggestion_summary" msgid="4825314186907812743">"开启新手势即可切换应用"</string>
    <string name="ambient_display_title" product="default" msgid="5144814600610448504">"点按两次即可查看手机"</string>
    <string name="ambient_display_title" product="tablet" msgid="8688795028609563837">"点按两次即可查看平板电脑"</string>
    <string name="ambient_display_title" product="device" msgid="3423781975742145894">"点按两次即可查看设备"</string>
    <string name="ambient_display_summary" msgid="525662960806416373">"点按两次屏幕即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_title" product="default" msgid="818688002837687268">"拿起手机即显示"</string>
    <string name="ambient_display_pickup_title" product="tablet" msgid="4455864282995698097">"拿起平板电脑即可查看"</string>
    <string name="ambient_display_pickup_title" product="device" msgid="5380534405773531175">"拿起设备即可查看"</string>
    <string name="ambient_display_pickup_summary" product="default" msgid="4567020486787561873">"拿起您的手机即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_summary" product="tablet" msgid="5435283849947236648">"拿起您的平板电脑即可查看时间、通知图标和其他信息。"</string>
    <string name="ambient_display_pickup_summary" product="device" msgid="8256669101643381568">"要查看时间、通知图标和其他信息，请拿起您的设备。"</string>
    <string name="fingerprint_swipe_for_notifications_title" msgid="5816346492253270243">"滑动指纹即可查看通知"</string>
    <string name="fingerprint_gesture_screen_title" msgid="8562169633234041196">"滑动指纹"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="default" msgid="1770661868393713922">"要查看通知，请在手机背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="tablet" msgid="902719947767712895">"要查看通知，请在平板电脑背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_summary" product="device" msgid="5372926094116306647">"要查看通知，请在设备背面的指纹传感器上向下滑动手指。"</string>
    <string name="fingerprint_swipe_for_notifications_suggestion_title" msgid="1677291167470357802">"快速查看通知"</string>
    <string name="gesture_setting_on" msgid="3455094265233870280">"开启"</string>
    <string name="gesture_setting_off" msgid="5230169535435881894">"关闭"</string>
    <string name="oem_unlock_enable_disabled_summary_bootloader_unlocked" msgid="4265541229765635629">"引导加载程序已解锁"</string>
    <string name="oem_unlock_enable_disabled_summary_connectivity" msgid="3361344735430813695">"请先连接到互联网"</string>
    <string name="oem_unlock_enable_disabled_summary_connectivity_or_locked" msgid="2479038689567925511">"请连接到互联网或与您的运营商联系"</string>
    <string name="oem_unlock_enable_disabled_summary_sim_locked_device" msgid="4149387448213399630">"在与运营商绑定的设备上无法使用"</string>
    <string name="oem_lock_info_message" msgid="9218313722236417510">"请重启设备以启用设备保护功能。"</string>
    <string name="automatic_storage_manager_freed_bytes" msgid="7517560170441007788">"总共释放空间：<xliff:g id="SIZE">%1$s</xliff:g>\n\n上次执行日期：<xliff:g id="DATE">%2$s</xliff:g>"</string>
    <string name="web_action_enable_title" msgid="4051513950976670853">"免安装应用"</string>
    <string name="web_action_enable_summary" msgid="3108127559723396382">"在应用中打开链接（即使未安装相关应用也无妨）"</string>
    <string name="web_action_section_title" msgid="7364647086538399136">"免安装应用"</string>
    <string name="instant_apps_settings" msgid="8827777916518348213">"免安装应用偏好设置"</string>
    <string name="domain_url_section_title" msgid="7046835219056428883">"已安装的应用"</string>
    <string name="automatic_storage_manager_activation_warning" msgid="6353100011690933254">"您的存储空间目前是由存储空间管理器管理"</string>
    <string name="account_for_section_header" msgid="5356566418548737121">"<xliff:g id="USER_NAME">%1$s</xliff:g>的帐号"</string>
    <string name="configure_section_header" msgid="7391183586410814450">"配置"</string>
    <string name="auto_sync_account_title" msgid="898796354710116383">"自动同步数据"</string>
    <string name="auto_sync_personal_account_title" msgid="8496263182646100610">"自动同步个人数据"</string>
    <string name="auto_sync_work_account_title" msgid="4489172450037434152">"自动同步工作数据"</string>
    <string name="auto_sync_account_summary" msgid="692499211629185107">"让应用自动刷新数据"</string>
    <string name="account_sync_title" msgid="7214747784136106491">"帐号同步"</string>
    <string name="account_sync_summary_some_on" msgid="3375930757891381175">"已开启 <xliff:g id="ID_1">%1$d</xliff:g> 项（共 <xliff:g id="ID_2">%2$d</xliff:g> 项）的同步功能"</string>
    <string name="account_sync_summary_all_on" msgid="570431636622254156">"已开启所有项的同步功能"</string>
    <string name="account_sync_summary_all_off" msgid="8782409931761182734">"已关闭所有项的同步功能"</string>
    <string name="enterprise_privacy_settings" msgid="1177106810374146496">"受管理设备的信息"</string>
    <string name="enterprise_privacy_settings_summary_generic" msgid="5853292305730761128">"由贵单位管理的更改和设置"</string>
    <string name="enterprise_privacy_settings_summary_with_name" msgid="4266234968317996188">"由“<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>”管理的更改和设置"</string>
    <string name="enterprise_privacy_header" msgid="7402406406883832509">"为了让您能够访问工作数据，贵单位可能会更改相应的设置并在您的设备上安装软件。\n\n要了解详情，请与贵单位的管理员联系。"</string>
    <string name="enterprise_privacy_exposure_category" msgid="7313392680107938517">"贵单位可查看的信息类型"</string>
    <string name="enterprise_privacy_exposure_changes_category" msgid="9079283547182933771">"贵单位的管理员所做的更改"</string>
    <string name="enterprise_privacy_device_access_category" msgid="5423434164248819058">"您对此设备的访问权限"</string>
    <string name="enterprise_privacy_enterprise_data" msgid="2773968662865848413">"与您的工作帐号关联的数据（例如电子邮件和日历）"</string>
    <string name="enterprise_privacy_installed_packages" msgid="2313698828178764590">"您设备上的应用列表"</string>
    <string name="enterprise_privacy_usage_stats" msgid="4398411405572759370">"每个应用的使用时长和数据用量"</string>
    <string name="enterprise_privacy_network_logs" msgid="161722817268849590">"最新的网络流量日志"</string>
    <string name="enterprise_privacy_bug_reports" msgid="843225086779037863">"最新的错误报告"</string>
    <string name="enterprise_privacy_security_logs" msgid="5377362481617301074">"最新的安全日志"</string>
    <string name="enterprise_privacy_none" msgid="7706621148858381189">"无"</string>
    <string name="enterprise_privacy_enterprise_installed_packages" msgid="6353757812144878828">"已安装的应用"</string>
    <string name="enterprise_privacy_apps_count_estimation_info" msgid="7433213592572082606">"应用数量是估算值，其中可能不包括从 Play 商店以外的来源安装的应用。"</string>
    <plurals name="enterprise_privacy_number_packages_lower_bound" formatted="false" msgid="3005116533873542976">
      <item quantity="other">至少 <xliff:g id="COUNT_1">%d</xliff:g> 个应用</item>
      <item quantity="one">至少 <xliff:g id="COUNT_0">%d</xliff:g> 个应用</item>
    </plurals>
    <string name="enterprise_privacy_location_access" msgid="4158197200885270634">"位置权限"</string>
    <string name="enterprise_privacy_microphone_access" msgid="5717375623568864441">"麦克风使用权限"</string>
    <string name="enterprise_privacy_camera_access" msgid="4858146118537519375">"相机使用权"</string>
    <string name="enterprise_privacy_enterprise_set_default_apps" msgid="3288495615791128724">"默认应用"</string>
    <plurals name="enterprise_privacy_number_packages" formatted="false" msgid="2765037387436064893">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 个应用</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 个应用</item>
    </plurals>
    <string name="enterprise_privacy_input_method" msgid="6531350246850814920">"默认键盘"</string>
    <string name="enterprise_privacy_input_method_name" msgid="4941106433683067953">"已设为<xliff:g id="APP_LABEL">%s</xliff:g>"</string>
    <string name="enterprise_privacy_always_on_vpn_device" msgid="4409098287763221215">"已开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_always_on_vpn_personal" msgid="9217774730260037434">"已在您的个人资料中开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_always_on_vpn_work" msgid="7244472958208315814">"已在您的工作资料中开启“始终开启的 VPN”"</string>
    <string name="enterprise_privacy_global_http_proxy" msgid="7936664553416257333">"已设置全局 HTTP 代理"</string>
    <string name="enterprise_privacy_ca_certs_device" msgid="2019652712782510262">"可信凭据"</string>
    <string name="enterprise_privacy_ca_certs_personal" msgid="2279084820904076599">"您个人资料中的可信凭据"</string>
    <string name="enterprise_privacy_ca_certs_work" msgid="6187377647815301809">"您工作资料中的可信凭据"</string>
    <plurals name="enterprise_privacy_number_ca_certs" formatted="false" msgid="526375234629534165">
      <item quantity="other">至少 <xliff:g id="COUNT_1">%d</xliff:g> 个 CA 证书</item>
      <item quantity="one">至少 <xliff:g id="COUNT_0">%d</xliff:g> 个 CA 证书</item>
    </plurals>
    <string name="enterprise_privacy_lock_device" msgid="8791656477097208540">"管理员可以锁定设备及重置密码"</string>
    <string name="enterprise_privacy_wipe_device" msgid="2821960015797241790">"管理员可以删除所有设备数据"</string>
    <string name="enterprise_privacy_failed_password_wipe_device" msgid="1001255609345002878">"输入错误密码的尝试次数上限（超过此上限将删除所有设备数据）"</string>
    <string name="enterprise_privacy_failed_password_wipe_work" msgid="4040565826652951057">"输入错误密码的尝试次数上限（超过此上限将删除工作资料数据）"</string>
    <plurals name="enterprise_privacy_number_failed_password_wipe" formatted="false" msgid="5279099270351036696">
      <item quantity="other">已尝试 <xliff:g id="COUNT_1">%d</xliff:g> 次</item>
      <item quantity="one">已尝试 <xliff:g id="COUNT_0">%d</xliff:g> 次</item>
    </plurals>
    <string name="enterprise_privacy_backups_enabled" msgid="8186700798406539053">"系统正在备份此设备的数据"</string>
    <string name="do_disclosure_generic" msgid="8653670456990823307">"此设备由贵单位管理。"</string>
    <string name="do_disclosure_with_name" msgid="1141081465968481380">"此设备由“<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>”管理。"</string>
    <string name="do_disclosure_learn_more_separator" msgid="3558079393757238670">" "</string>
    <string name="learn_more" msgid="2623878455042103404">"了解详情"</string>
    <plurals name="default_camera_app_title" formatted="false" msgid="1134677050353971363">
      <item quantity="other">相机应用</item>
      <item quantity="one">相机应用</item>
    </plurals>
    <string name="default_calendar_app_title" msgid="3545972964391065220">"日历应用"</string>
    <string name="default_contacts_app_title" msgid="3497370557378660098">"通讯录应用"</string>
    <plurals name="default_email_app_title" formatted="false" msgid="42826975161049245">
      <item quantity="other">电子邮件客户端应用</item>
      <item quantity="one">电子邮件客户端应用</item>
    </plurals>
    <string name="default_map_app_title" msgid="7560143381633608567">"地图应用"</string>
    <plurals name="default_phone_app_title" formatted="false" msgid="6714041230953195024">
      <item quantity="other">电话应用</item>
      <item quantity="one">电话应用</item>
    </plurals>
    <string name="app_names_concatenation_template_2" msgid="4309216198909946380">"<xliff:g id="FIRST_APP_NAME">%1$s</xliff:g>、<xliff:g id="SECOND_APP_NAME">%2$s</xliff:g>"</string>
    <string name="app_names_concatenation_template_3" msgid="8949045544491604376">"<xliff:g id="FIRST_APP_NAME">%1$s</xliff:g>、<xliff:g id="SECOND_APP_NAME">%2$s</xliff:g>、<xliff:g id="THIRD_APP_NAME">%3$s</xliff:g>"</string>
    <string name="storage_photos_videos" msgid="319854636702241898">"照片和视频"</string>
    <string name="storage_music_audio" msgid="789779084825206838">"音乐和音频"</string>
    <string name="storage_games" msgid="7703159201697117621">"游戏"</string>
    <string name="storage_other_apps" msgid="5524321740031718083">"其他应用"</string>
    <string name="storage_files" msgid="8581083146777364063">"文件"</string>
    <string name="storage_size_large_alternate" msgid="3395208658399637645">"<xliff:g id="NUMBER">^1</xliff:g>"<small>" "<font size="20">"<xliff:g id="UNIT">^2</xliff:g>"</font></small>""</string>
    <string name="storage_volume_total" msgid="3499221850532701342">"已用空间（总共 <xliff:g id="TOTAL">%1$s</xliff:g>）"</string>
    <string name="storage_percent_full" msgid="6095012055875077036">"已使用"</string>
    <string name="clear_instant_app_data" msgid="2004222610585890909">"清除应用"</string>
    <string name="clear_instant_app_confirmation" msgid="7451671214898856857">"要移除这个免安装应用吗？"</string>
    <string name="launch_instant_app" msgid="391581144859010499">"打开"</string>
    <string name="game_storage_settings" msgid="3410689937046696557">"游戏"</string>
    <string name="audio_files_title" msgid="4777048870657911307">"音频文件"</string>
    <string name="app_info_storage_title" msgid="5554719444625611987">"已用空间"</string>
    <string name="webview_uninstalled_for_user" msgid="1819903169194420983">"（已为用户<xliff:g id="USER">%s</xliff:g>卸载）"</string>
    <string name="webview_disabled_for_user" msgid="1216426047631256825">"（已为用户<xliff:g id="USER">%s</xliff:g>停用）"</string>
    <string name="autofill_app" msgid="7338387238377914374">"自动填充服务"</string>
    <string name="autofill_keywords" msgid="7485591824120812710">"自动, 填充, 自动填充, auto, fill, autofill"</string>
    <string name="autofill_confirmation_message" msgid="2784869528908005194">"&lt;b&gt;请确认这是您信任的应用&lt;/b&gt; &lt;br/&gt; &lt;br/&gt; &lt;xliff:g id=app_name example=Google Autofill&gt;%1$s&lt;/xliff:g&gt;会根据您的屏幕内容判断可自动填充哪些内容。"</string>
    <string name="color_theme" msgid="1535685696756738324">"色调"</string>
    <string name="default_theme" msgid="7085644992078579076">"默认"</string>
    <string name="device_theme" msgid="4571803018917608588">"设备主题背景"</string>
    <string name="systemui_theme_wallpaper" msgid="5658521610680281172">"自动（根据壁纸）"</string>
    <string name="systemui_theme_light" msgid="7519689709659152866">"浅色"</string>
    <string name="systemui_theme_dark" msgid="8708733503912628456">"深色"</string>
    <string name="show_operator_name_title" msgid="805135053530442951">"网络名称"</string>
    <string name="show_operator_name_summary" msgid="5962567590205757550">"在状态栏中显示网络名称"</string>
    <string name="storage_manager_indicator" msgid="1516810749625915020">"存储空间管理器：<xliff:g id="STATUS">^1</xliff:g>"</string>
    <string name="storage_manager_indicator_off" msgid="7488057587180724388">"关闭"</string>
    <string name="storage_manager_indicator_on" msgid="8625551710194584733">"开启"</string>
    <string name="install_type_instant" msgid="3174425974536078647">"免安装应用"</string>
    <string name="automatic_storage_manager_deactivation_warning" msgid="5605210730828410482">"要关闭存储空间管理器吗？"</string>
    <string name="storage_movies_tv" msgid="5498394447562086890">"影视应用"</string>
    <string name="carrier_provisioning" msgid="4398683675591893169">"运营商配置信息"</string>
    <string name="trigger_carrier_provisioning" msgid="3434865918009286187">"触发运营商配置"</string>
    <string name="zen_suggestion_title" msgid="798067603460192693">"更新勿扰模式"</string>
    <string name="zen_suggestion_summary" msgid="5928686804697233014">"暂停通知即可保持专注"</string>
    <string name="new_device_suggestion_title" msgid="698847081680980774">"新增了哪些精彩功能？"</string>
    <string name="new_device_suggestion_summary" product="default" msgid="206396571522515855">"新手机功能导览"</string>
    <string name="new_device_suggestion_summary" product="tablet" msgid="393751455688210956">"新平板电脑功能导览"</string>
    <string name="new_device_suggestion_summary" product="device" msgid="2939870049868336652">"新设备功能导览"</string>
    <string name="disabled_low_ram_device" msgid="3751578499721173344">"该设备不支持此功能"</string>
    <string name="enable_gnss_raw_meas_full_tracking" msgid="1294470289520660584">"强制启用 GNSS 测量结果全面跟踪"</string>
    <string name="enable_gnss_raw_meas_full_tracking_summary" msgid="496344699046454200">"在停用工作周期的情况下跟踪所有 GNSS 星座和频率"</string>
    <string name="show_first_crash_dialog" msgid="8889957119867262599">"一律显示崩溃对话框"</string>
    <string name="show_first_crash_dialog_summary" msgid="703224456285060428">"在每次应用崩溃时显示对话框"</string>
    <string name="directory_access" msgid="4722237210725864244">"目录访问权限"</string>
    <string name="keywords_directory_access" msgid="360557532842445280">"目录访问权限"</string>
    <string name="directory_on_volume" msgid="1246959267814974387">"<xliff:g id="VOLUME">%1$s</xliff:g>（<xliff:g id="DIRECTORY">%2$s</xliff:g>）"</string>
    <string name="unsupported_setting_summary" product="default" msgid="11246953620654225">"此手机不支持这项设置"</string>
    <string name="unsupported_setting_summary" product="tablet" msgid="6328431665635673717">"此平板电脑不支持这项设置"</string>
    <string name="unsupported_setting_summary" product="device" msgid="2348970994972110886">"此设备不支持这项设置"</string>
    <string name="disabled_for_user_setting_summary" msgid="3388525317680711262">"当前用户无法更改设置"</string>
    <string name="disabled_dependent_setting_summary" msgid="8291322239940946902">"必须一并更改其他设置"</string>
    <string name="unknown_unavailability_setting_summary" msgid="4589584678033059435">"无法更改设置"</string>
    <string name="my_device_info_account_preference_title" msgid="342933638925781861">"帐号"</string>
    <string name="my_device_info_device_name_preference_title" msgid="7104085224684165324">"设备名称"</string>
    <string name="bluetooth_on_while_driving_pref" msgid="2460847604498343330">"开车时使用蓝牙"</string>
    <string name="bluetooth_on_while_driving_summary" msgid="3196190732516898541">"开车时自动开启蓝牙"</string>
    <string name="change_wifi_state_title" msgid="3261945855372885427">"WLAN 控制"</string>
    <string name="change_wifi_state_app_detail_switch" msgid="7942268646980694224">"允许应用控制 WLAN"</string>
    <string name="change_wifi_state_app_detail_summary" msgid="8434262633905502679">"允许此应用开启或关闭 WLAN、扫描及连接到 WLAN 网络、添加或移除网络，或启动仅限本地的热点"</string>
    <string name="media_output_title" msgid="115223550977351699">"媒体播放到"</string>
    <string name="media_output_default_summary" msgid="8115153381240348279">"此设备"</string>
    <string name="media_output_summary" product="default" msgid="6839458453831567167">"手机"</string>
    <string name="media_output_summary" product="tablet" msgid="7217221078578554515">"平板电脑"</string>
    <string name="media_output_summary" product="device" msgid="5677420090811068649">"设备"</string>
    <string name="media_out_summary_ongoing_call_state" msgid="3533731701018680693">"通话期间无法使用"</string>
    <string name="media_output_summary_unavailable" msgid="7970304720507697019">"无法使用"</string>
    <string name="take_call_on_title" msgid="6066362463436122655">"接听来电的设备："</string>
    <string name="battery_suggestion_title" product="tablet" msgid="752439050748267917">"延长平板电脑的电池续航时间"</string>
    <string name="battery_suggestion_title" product="device" msgid="1507272328369733005">"延长设备的电池续航时间"</string>
    <string name="battery_suggestion_title" product="default" msgid="4038053023336285165">"延长手机的电池续航时间"</string>
    <string name="battery_suggestion_summary" msgid="4585677159811722359"></string>
    <string name="gesture_prevent_ringing_screen_title" msgid="7840226017975251549">"阻止响铃"</string>
    <string name="gesture_prevent_ringing_title" msgid="2483159069038138740">"同时按电源和音量调高按钮"</string>
    <string name="gesture_prevent_ringing_sound_title" msgid="5724512060316688779">"阻止响铃的快捷方式"</string>
    <string name="prevent_ringing_option_vibrate" msgid="7286821846542822661">"振动"</string>
    <string name="prevent_ringing_option_mute" msgid="7551545579059879853">"静音"</string>
    <string name="prevent_ringing_option_none" msgid="4656046650769569175">"不发出任何铃声"</string>
    <string name="prevent_ringing_option_vibrate_summary" msgid="1157524435626890116">"开启（振动）"</string>
    <string name="prevent_ringing_option_mute_summary" msgid="4472465110708640980">"开启（静音）"</string>
    <string name="prevent_ringing_option_none_summary" msgid="5013718946609276137">"关闭"</string>
    <string name="pref_title_network_details" msgid="7186418845727358964">"网络详情"</string>
    <string name="about_phone_device_name_warning" msgid="8885670415541365348">"您的设备名称会显示在手机上的应用中。此外，当您连接到蓝牙设备或设置 WLAN 热点时，其他人可能也会看到您的设备名称。"</string>
    <string name="devices_title" msgid="7701726109334110391">"设备"</string>
</resources>

```

### arrays_cn.xml
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/res/values-zh-rCN/arrays.xml
packages_apps_Settings_res_arrays_cn.xml

```



<resources xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
  <string-array name="timezone_filters">
    <item msgid="5296756001147094692">"美洲"</item>
    <item msgid="3005562397632768392">"欧洲"</item>
    <item msgid="5696915123093701218">"非洲"</item>
    <item msgid="4439789052790868249">"亚洲"</item>
    <item msgid="956915953069815961">"澳大利亚"</item>
    <item msgid="5345178126174698955">"太平洋"</item>
    <item msgid="8392017019801393511">"全部"</item>
  </string-array>
  <string-array name="screen_timeout_entries">
    <item msgid="3342301044271143016">"15秒"</item>
    <item msgid="8881760709354815449">"30秒"</item>
    <item msgid="7589406073232279088">"1分钟"</item>
    <item msgid="7001195990902244174">"2分钟"</item>
    <item msgid="7489864775127957179">"5分钟"</item>
    <item msgid="2314124409517439288">"10分钟"</item>
    <item msgid="6864027152847611413">"30分钟"</item>
  </string-array>
  <string-array name="dream_timeout_entries">
    <item msgid="3149294732238283185">"永不"</item>
    <item msgid="2194151041885903260">"15秒"</item>
    <item msgid="5892295237131074341">"30秒"</item>
    <item msgid="3538441365970038213">"1分钟"</item>
    <item msgid="412343871668955639">"2分钟"</item>
    <item msgid="5076853889688991690">"5分钟"</item>
    <item msgid="1903860996174927898">"10分钟"</item>
    <item msgid="6415509612413178727">"30分钟"</item>
  </string-array>
  <string-array name="lock_after_timeout_entries">
    <item msgid="8929270399652145290">"立即"</item>
    <item msgid="6736512735606834431">"5秒"</item>
    <item msgid="8044619388267891375">"15秒"</item>
    <item msgid="1822002388249545488">"30秒"</item>
    <item msgid="8538071621211916519">"1分钟"</item>
    <item msgid="5663439580228932882">"2分钟"</item>
    <item msgid="49888496216106852">"5分钟"</item>
    <item msgid="9002737361305019353">"10分钟"</item>
    <item msgid="4322676235684793329">"30分钟"</item>
  </string-array>
  <string-array name="entries_font_size">
    <item msgid="8166647333858618801">"小"</item>
    <item msgid="6986443533756848935">"默认"</item>
    <item msgid="38373998008112077">"大"</item>
    <item msgid="7635254317531872272">"最大"</item>
  </string-array>
  <string-array name="wifi_status">
    <item msgid="1922181315419294640"></item>
    <item msgid="8934131797783724664">"正在扫描..."</item>
    <item msgid="8513729475867537913">"正在连接..."</item>
    <item msgid="515055375277271756">"正在验证身份…"</item>
    <item msgid="1943354004029184381">"正在获取IP地址..."</item>
    <item msgid="4221763391123233270">"已连接"</item>
    <item msgid="624838831631122137">"已暂停"</item>
    <item msgid="7979680559596111948">"正在断开连接..."</item>
    <item msgid="1634960474403853625">"已断开连接"</item>
    <item msgid="746097431216080650">"失败"</item>
    <item msgid="6367044185730295334">"已停用"</item>
    <item msgid="503942654197908005">"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="wifi_status_with_ssid">
    <item msgid="7714855332363650812"></item>
    <item msgid="8878186979715711006">"正在扫描..."</item>
    <item msgid="355508996603873860">"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item msgid="554971459996405634">"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item msgid="7928343808033020343">"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item msgid="8937994881315223448">"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item msgid="1330262655415760617">"已暂停"</item>
    <item msgid="7698638434317271902">"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item msgid="197508606402264311">"已断开连接"</item>
    <item msgid="8578370891960825148">"失败"</item>
    <item msgid="5660739516542454527">"已停用"</item>
    <item msgid="1805837518286731242">"暂时关闭（网络状况不佳）"</item>
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
    <item msgid="2252927183588236198">"4"</item>
    <item msgid="5900372418924184351">"0"</item>
  </string-array>
  <string-array name="wifi_eap_method">
    <item msgid="1160193392455075561">"PEAP"</item>
    <item msgid="7981731051382306293">"TLS"</item>
    <item msgid="2892994535305020162">"TTLS"</item>
    <item msgid="435667726254379514">"PWD"</item>
    <item msgid="8549485714107012129">"SIM"</item>
    <item msgid="1023893786681286517">"AKA"</item>
    <item msgid="3030483188676375009">"AKA\'"</item>
  </string-array>
  <string-array name="eap_method_without_sim_auth">
    <item msgid="4047867891913819797">"PEAP"</item>
    <item msgid="641030570679578504">"TLS"</item>
    <item msgid="3079489731769553856">"TTLS"</item>
    <item msgid="35269224158638258">"PWD"</item>
  </string-array>
    <!-- no translation found for wifi_ap_band_config_full:0 (1085243288162893079) -->
    <!-- no translation found for wifi_ap_band_config_full:1 (5531376834915607202) -->
    <!-- no translation found for wifi_ap_band_summary_full:0 (7176872102094020362) -->
    <!-- no translation found for wifi_ap_band_summary_full:1 (311895158827229479) -->
    <!-- no translation found for wifi_ap_band_config_2G_only:0 (7006771583217001015) -->
    <!-- no translation found for wifi_ap_band_config_2G_only:1 (8904289885593822837) -->
  <string-array name="wifi_p2p_wps_setup">
    <item msgid="5085064298144493867">"按钮"</item>
    <item msgid="1624323946324499595">"从对等设备获取的 PIN 码"</item>
    <item msgid="5366790421523328066">"此设备生成的 PIN 码"</item>
  </string-array>
  <string-array name="wifi_p2p_status">
    <item msgid="1701505390737218306">"已连接"</item>
    <item msgid="3189211552661432651">"已邀请"</item>
    <item msgid="3206450250360237549">"失败"</item>
    <item msgid="7785896708926971207">"可用"</item>
    <item msgid="2330782789550628803">"超出范围"</item>
  </string-array>
  <string-array name="bluetooth_visibility_timeout_entries">
    <item msgid="8151962652413645395">"2分钟"</item>
    <item msgid="8675215713017289017">"5分钟"</item>
    <item msgid="477015974247590543">"1小时"</item>
    <item msgid="5198271470953124739">"永不超时"</item>
  </string-array>
  <string-array name="bluetooth_max_connected_audio_devices">
    <item msgid="834764606877643762">"使用系统默认值：<xliff:g id="DEFAULT_BLUETOOTH_MAX_CONNECTED_AUDIO_DEVICES">%1$d</xliff:g>"</item>
    <item msgid="4428462068012149533">"1"</item>
    <item msgid="2620881722754455257">"2"</item>
    <item msgid="402831176731135702">"3"</item>
    <item msgid="4923580285404888038">"4"</item>
    <item msgid="3643103044864989283">"5"</item>
  </string-array>
  <string-array name="wifi_signal">
    <item msgid="2245412278046491293">"弱"</item>
    <item msgid="2042505933058940139">"微弱"</item>
    <item msgid="1344546617235886412">"一般"</item>
    <item msgid="6019931571712517411">"良好"</item>
    <item msgid="8986346415847956850">"极佳"</item>
  </string-array>
  <string-array name="data_usage_data_range">
    <item msgid="5013973108901348144">"过去30天"</item>
    <item msgid="6600989128423965319">"设置流量周期…"</item>
  </string-array>
  <string-array name="usage_stats_display_order_types">
    <item msgid="2100172576767439288">"使用时间"</item>
    <item msgid="4796160515314745154">"上次使用时间"</item>
    <item msgid="2502754479975776899">"应用名称"</item>
  </string-array>
  <string-array name="wifi_eap_entries">
    <item msgid="8615575908717909498">"PEAP"</item>
    <item msgid="8667872640594311615">"TLS"</item>
    <item msgid="7182812872984827322">"TTLS"</item>
    <item msgid="2318274046749286642">"PWD"</item>
  </string-array>
  <string-array name="wifi_peap_phase2_entries">
    <item msgid="2577747762745812488">"无"</item>
    <item msgid="937786527870979616">"MSCHAPV2"</item>
    <item msgid="5302613883318643629">"GTC"</item>
  </string-array>
  <string-array name="wifi_peap_phase2_entries_with_sim_auth">
    <item msgid="5760470455461128892">"无"</item>
    <item msgid="7480272092408291086">"MSCHAPV2"</item>
    <item msgid="5881794903338319324">"GTC"</item>
    <item msgid="5610607665198791980">"SIM"</item>
    <item msgid="2860798636241124128">"AKA"</item>
    <item msgid="8926455723452645935">"AKA\'"</item>
  </string-array>
  <string-array name="wifi_phase2_entries">
    <item msgid="1818786254010764570">"无"</item>
    <item msgid="6189918678874123056">"PAP"</item>
    <item msgid="1524112260493662517">"MSCHAP"</item>
    <item msgid="5923246669412752932">"MSCHAPV2"</item>
    <item msgid="8651992560135239389">"GTC"</item>
  </string-array>
  <string-array name="wifi_ip_settings">
    <item msgid="3906714200993111074">"DHCP"</item>
    <item msgid="628395202971532382">"静态"</item>
  </string-array>
  <string-array name="wifi_proxy_settings">
    <item msgid="4473276491748503377">"无"</item>
    <item msgid="8673874894887358090">"手动"</item>
    <item msgid="168893341855953140">"代理自动配置"</item>
  </string-array>
  <string-array name="apn_auth_entries">
    <item msgid="3856896061242872146">"无"</item>
    <item msgid="5756844015743664882">"PAP"</item>
    <item msgid="535934025797984365">"CHAP"</item>
    <item msgid="8383098660619805783">"PAP 或 CHAP"</item>
  </string-array>
  <string-array name="apn_protocol_entries">
    <item msgid="4852355456199302715">"IPv4"</item>
    <item msgid="4394161344888484571">"IPv6"</item>
    <item msgid="8084938354605535381">"IPv4/IPv6"</item>
  </string-array>
  <string-array name="bearer_entries">
    <item msgid="1697455674244601285">"未指定"</item>
    <item msgid="1317061551798123908">"LTE"</item>
    <item msgid="5005435684511894770">"HSPAP"</item>
    <item msgid="7700603056475539235">"HSPA"</item>
    <item msgid="245973007602397887">"HSUPA"</item>
    <item msgid="6291566767651194016">"HSDPA"</item>
    <item msgid="2005841400859926251">"UMTS"</item>
    <item msgid="3757385691174882861">"EDGE"</item>
    <item msgid="2979115073474306864">"GPRS"</item>
    <item msgid="2271750502778879106">"eHRPD"</item>
    <item msgid="4173379084783381337">"EVDO_B"</item>
    <item msgid="2033682802005776093">"EVDO_A"</item>
    <item msgid="5753917125831466719">"EVDO_0"</item>
    <item msgid="4713807936577071142">"1xRTT"</item>
    <item msgid="1142355797022021906">"IS95B"</item>
    <item msgid="7471182818083460781">"IS95A"</item>
  </string-array>
  <string-array name="mvno_type_entries">
    <item msgid="4367119357633573465">"无"</item>
    <item msgid="6062567900587138000">"SPN"</item>
    <item msgid="2454085083342423481">"IMSI"</item>
    <item msgid="2681427309183221543">"GID"</item>
  </string-array>
  <string-array name="app_install_location_entries">
    <item msgid="8151497958991952759">"内部存储设备"</item>
    <item msgid="3738430123799803530">"可卸载的SD卡"</item>
    <item msgid="4498124044785815005">"由系统确定"</item>
  </string-array>
  <string-array name="app_ops_categories">
    <item msgid="6358963769537892925">"位置"</item>
    <item msgid="255608127647030286">"个人"</item>
    <item msgid="4588829735729884491">"短信"</item>
    <item msgid="886742181977884584">"媒体"</item>
    <item msgid="7924928667052300589">"设备"</item>
  </string-array>
  <string-array name="app_ops_summaries">
    <item msgid="4979188868761515915">"粗略位置"</item>
    <item msgid="5789673140227507995">"精确位置"</item>
    <item msgid="1061584358377390581">"GPS"</item>
    <item msgid="5387405117297558954">"振动"</item>
    <item msgid="3434165993711230924">"读取联系人"</item>
    <item msgid="616161687718081936">"修改联系人"</item>
    <item msgid="7638002295329050091">"读取通话记录"</item>
    <item msgid="6546959730920410907">"修改通话记录"</item>
    <item msgid="446877710771379667">"读取日历"</item>
    <item msgid="7674458294386319722">"修改日历"</item>
    <item msgid="8281201165558093009">"WLAN扫描"</item>
    <item msgid="8694611243479480497">"通知"</item>
    <item msgid="7776439107987345446">"手机网络扫描"</item>
    <item msgid="514615766544675057">"拨打电话"</item>
    <item msgid="8181415497109310680">"读取短信"</item>
    <item msgid="6816551144382117307">"编写短信"</item>
    <item msgid="4600463921908905030">"接收短信"</item>
    <item msgid="5958926493289432745">"接收紧急短信"</item>
    <item msgid="4945269495221089540">"接收彩信"</item>
    <item msgid="5570472453573929087">"接收 WAP PUSH 消息"</item>
    <item msgid="7125408150230860501">"发送短信"</item>
    <item msgid="7080337936612188061">"读取 ICC 短信"</item>
    <item msgid="587124103118495063">"写入 ICC 短信"</item>
    <item msgid="2320577158869025503">"修改设置"</item>
    <item msgid="1545733463471924009">"在最上层显示内容"</item>
    <item msgid="3609046903962454582">"访问通知"</item>
    <item msgid="4671646036128214513">"相机"</item>
    <item msgid="1097324338692486211">"录制音频"</item>
    <item msgid="5031552983987798163">"播放音频"</item>
    <item msgid="8374996688066472414">"读取剪贴板内容"</item>
    <item msgid="3045529469061083747">"修改剪贴板内容"</item>
    <item msgid="5124443975763747838">"媒体按钮"</item>
    <item msgid="4547883971364273343">"音频焦点"</item>
    <item msgid="2603878814882344450">"主音量"</item>
    <item msgid="7136963238377062018">"语音音量"</item>
    <item msgid="4270236897655923007">"铃声音量"</item>
    <item msgid="6325739889222559394">"媒体音量"</item>
    <item msgid="5762123934816216821">"闹钟音量"</item>
    <item msgid="785049718065337473">"通知音量"</item>
    <item msgid="6700305533746877052">"蓝牙音量"</item>
    <item msgid="2029227495214047094">"保持唤醒状态"</item>
    <item msgid="26109888160231211">"监测位置"</item>
    <item msgid="5753382310468855812">"监控高电耗位置信息服务"</item>
    <item msgid="3356591542543137332">"获取使用情况统计信息"</item>
    <item msgid="3073734345226842233">"将麦克风静音或取消静音"</item>
    <item msgid="2111767435887685265">"显示问候语"</item>
    <item msgid="1091168669714823370">"投影媒体"</item>
    <item msgid="485564189219029300">"激活 VPN"</item>
    <item msgid="7155384795265164395">"写入壁纸"</item>
    <item msgid="1835836196806147034">"辅助结构"</item>
    <item msgid="5989890403088155055">"辅助屏幕截图"</item>
    <item msgid="8582699692765917557">"读取手机状态"</item>
    <item msgid="1474039653814954902">"添加语音邮件"</item>
    <item msgid="7222837656938871633">"使用 SIP"</item>
    <item msgid="6108267038969274380">"处理拨出电话"</item>
    <item msgid="4823402479973873358">"指纹"</item>
    <item msgid="5895843015407713543">"身体传感器"</item>
    <item msgid="1436446526955010826">"读取小区广播"</item>
    <item msgid="884172201575690484">"模拟位置"</item>
    <item msgid="3591971310048485247">"读取存储空间"</item>
    <item msgid="4041187808621866119">"写入存储空间"</item>
    <item msgid="6628873315024166197">"开启屏幕"</item>
    <item msgid="3253368931113490863">"获取帐号"</item>
    <item msgid="780392378084812901">"在后台运行"</item>
    <item msgid="2629748510881309577">"无障碍功能音量"</item>
  </string-array>
  <string-array name="app_ops_labels">
    <item msgid="6602854600289714121">"位置"</item>
    <item msgid="8677040780775113033">"位置"</item>
    <item msgid="1660743989948992916">"位置"</item>
    <item msgid="8791172739860195290">"振动"</item>
    <item msgid="383413555642128046">"读取联系人"</item>
    <item msgid="3654594895269697313">"修改联系人"</item>
    <item msgid="7928393476362362538">"读取通话记录"</item>
    <item msgid="6248591205254641116">"修改通话记录"</item>
    <item msgid="6093344633066170692">"读取日历"</item>
    <item msgid="1334886368750347692">"修改日历"</item>
    <item msgid="1638204101698708656">"位置"</item>
    <item msgid="2154671955760380322">"发布通知"</item>
    <item msgid="4282477730595931828">"位置"</item>
    <item msgid="4891423912898525905">"拨打电话"</item>
    <item msgid="2623604824935968113">"读取短信/彩信"</item>
    <item msgid="4420177125221176306">"编写短信/彩信"</item>
    <item msgid="3986142739951490025">"接收短信/彩信"</item>
    <item msgid="3984213795861739778">"接收短信/彩信"</item>
    <item msgid="3656243523752472788">"接收短信/彩信"</item>
    <item msgid="8105802370238551510">"接收短信/彩信"</item>
    <item msgid="1407766984645388488">"发送短信/彩信"</item>
    <item msgid="3527273606643794973">"读取短信/彩信"</item>
    <item msgid="4370895547001583812">"编写短信/彩信"</item>
    <item msgid="4218544235221631789">"修改设置"</item>
    <item msgid="736541391767350377">"在最上层显示内容"</item>
    <item msgid="5530815681721654194">"访问通知"</item>
    <item msgid="781213371706962767">"相机"</item>
    <item msgid="1720492593061838172">"录制音频"</item>
    <item msgid="3493046322001257041">"播放音频"</item>
    <item msgid="136815868796597058">"读取剪贴板内容"</item>
    <item msgid="5238692940326972503">"修改剪贴板内容"</item>
    <item msgid="5753789168376302997">"媒体按钮"</item>
    <item msgid="3265262911688671938">"音频焦点"</item>
    <item msgid="2098976479485046797">"主音量"</item>
    <item msgid="5660213838861789350">"语音音量"</item>
    <item msgid="7983336752371254444">"铃声音量"</item>
    <item msgid="7878027809189330917">"媒体音量"</item>
    <item msgid="7260546305036218513">"闹钟音量"</item>
    <item msgid="9103719301075748925">"通知音量"</item>
    <item msgid="7025966722295861512">"蓝牙音量"</item>
    <item msgid="4665183401128289653">"保持唤醒状态"</item>
    <item msgid="8584357129746649222">"位置"</item>
    <item msgid="7669257279311110599">"位置信息"</item>
    <item msgid="3459320345690097795">"获取使用情况统计信息"</item>
    <item msgid="1312534577834048535">"将麦克风静音或取消静音"</item>
    <item msgid="427580389823724225">"显示问候语"</item>
    <item msgid="4992007785575926253">"投影媒体"</item>
    <item msgid="2482631530338029480">"激活 VPN"</item>
    <item msgid="1662979573471871926">"写入壁纸"</item>
    <item msgid="5964768335278263478">"辅助结构"</item>
    <item msgid="2657138701132782702">"辅助屏幕截图"</item>
    <item msgid="8571369610363539266">"读取手机状态"</item>
    <item msgid="4542463358215230845">"添加语音邮件"</item>
    <item msgid="864565065016166003">"使用 SIP"</item>
    <item msgid="1958009349883195116">"处理拨出电话"</item>
    <item msgid="8526563410140613458">"指纹"</item>
    <item msgid="7864822459293570891">"身体传感器"</item>
    <item msgid="6798698496904810960">"读取小区广播"</item>
    <item msgid="5242052845700875820">"模拟位置"</item>
    <item msgid="1246296877820358565">"读取存储空间"</item>
    <item msgid="2404067308793740341">"写入存储空间"</item>
    <item msgid="5832543806893763620">"开启屏幕"</item>
    <item msgid="5258373962467495905">"获取帐号"</item>
    <item msgid="334625385979270703">"在后台运行"</item>
    <item msgid="9039213578110332702">"无障碍功能音量"</item>
  </string-array>
  <string-array name="long_press_timeout_selector_titles">
    <item msgid="3511504869290423954">"短"</item>
    <item msgid="2560532955514699713">"中"</item>
    <item msgid="2372711992605524591">"长"</item>
  </string-array>
  <string-array name="captioning_typeface_selector_titles">
    <item msgid="1319652728542138112">"默认"</item>
    <item msgid="1016452621833735880">"Sans-serif"</item>
    <item msgid="2496277987934654454">"Sans-serif condensed"</item>
    <item msgid="7247838127505318669">"Sans-serif monospace"</item>
    <item msgid="4478414822462359763">"Serif"</item>
    <item msgid="7502451783483660829">"Serif monospace"</item>
    <item msgid="639503332147461010">"Casual"</item>
    <item msgid="7967169925231332424">"Cursive"</item>
    <item msgid="561832997193039673">"Small capitals"</item>
  </string-array>
  <string-array name="captioning_font_size_selector_titles">
    <item msgid="4800919809575254054">"超小"</item>
    <item msgid="6781094565687692782">"小"</item>
    <item msgid="8222123259497646551">"正常"</item>
    <item msgid="5813217276778560466">"大"</item>
    <item msgid="9044232017390975191">"超大"</item>
  </string-array>
  <string-array name="captioning_edge_type_selector_titles">
    <item msgid="4733815704128258753">"默认"</item>
    <item msgid="3217099060748617005">"无"</item>
    <item msgid="7467615139904599420">"轮廓"</item>
    <item msgid="5623165557468608975">"阴影"</item>
    <item msgid="8088451174058214588">"凸起"</item>
    <item msgid="3821418743395480313">"凹陷"</item>
  </string-array>
  <string-array name="captioning_opacity_selector_titles">
    <item msgid="7622491218136667566">"25%"</item>
    <item msgid="2367156416247936773">"50%"</item>
    <item msgid="5395560410107149298">"75%"</item>
    <item msgid="8342334626783983353">"100%"</item>
  </string-array>
  <string-array name="captioning_preset_selector_titles">
    <item msgid="7009918361545506251">"使用应用默认样式"</item>
    <item msgid="1770533843436933500">"黑底白字"</item>
    <item msgid="758587126802411846">"白底黑字"</item>
    <item msgid="1495307195241623402">"黑底黄字"</item>
    <item msgid="6039700130994371612">"蓝底黄字"</item>
    <item msgid="7169235156349580064">"自定义"</item>
  </string-array>
  <string-array name="vpn_types_long">
    <item msgid="2732002039459078847">"PPTP VPN"</item>
    <item msgid="3799752201662127867">"具有预共享密钥的 L2TP/IPSec VPN"</item>
    <item msgid="4725504331295252103">"具有证书的 L2TP/IPSec VPN"</item>
    <item msgid="7526551163264034377">"具有预共享密钥且进行 Xauth 身份验证的 IPSec VPN"</item>
    <item msgid="8064740940687465039">"具有证书且进行 Xauth 身份验证的 IPSec VPN"</item>
    <item msgid="4946199982372391490">"具有证书且进行混合身份验证的 IPSec VPN"</item>
  </string-array>
  <string-array name="vpn_states">
    <item msgid="8621078286418985762">"连接已断开"</item>
    <item msgid="6692305604213080515">"正在初始化..."</item>
    <item msgid="8001704909356800092">"正在连接..."</item>
    <item msgid="4039737283841672166">"已连接"</item>
    <item msgid="4042143101664725090">"超时"</item>
    <item msgid="7664124146786465092">"失败"</item>
  </string-array>
  <string-array name="security_settings_premium_sms_values">
    <item msgid="7389829271787670252">"询问"</item>
    <item msgid="5077768429488260031">"永不允许"</item>
    <item msgid="1417929597727989746">"始终允许"</item>
  </string-array>
  <string-array name="ram_states">
    <item msgid="3944681673818150669">"正常"</item>
    <item msgid="3256987280393708586">"中等"</item>
    <item msgid="4662917179231875995">"不足"</item>
    <item msgid="5264929699714647509">"严重不足"</item>
    <item msgid="5606155978847838966">"未知"</item>
  </string-array>
  <string-array name="proc_stats_memory_states">
    <item msgid="8845855295876909468">"正常"</item>
    <item msgid="866544120205026771">"中等"</item>
    <item msgid="7851902244436886890">"不足"</item>
    <item msgid="3022922196817563960">"严重不足"</item>
  </string-array>
  <string-array name="proc_stats_process_states">
    <item msgid="5069825997142785829">"常驻"</item>
    <item msgid="5779398140277006695">"顶层 Activity"</item>
    <item msgid="1439598363694578255">"重要（前台）"</item>
    <item msgid="3396458970745718652">"重要（后台）"</item>
    <item msgid="5214825238247511992">"备份"</item>
    <item msgid="311372689168254967">"重量级"</item>
    <item msgid="7438189122367820362">"服务（运行中）"</item>
    <item msgid="918687422516982498">"服务（重新启动中）"</item>
    <item msgid="6807727069641853029">"接收器"</item>
    <item msgid="6782857406100845127">"主屏幕"</item>
    <item msgid="2860945127596974299">"前一个 Activity"</item>
    <item msgid="8610560843693675830">"已缓存 (Activity)"</item>
    <item msgid="4338089220026248848">"已缓存（Activity 客户端）"</item>
    <item msgid="6652164677254579050">"已缓存（空）"</item>
  </string-array>
  <string-array name="color_picker">
    <item msgid="7631642672260600032">"青色"</item>
    <item msgid="8332294763632946560">"蓝色"</item>
    <item msgid="2023216417616991392">"靛青色"</item>
    <item msgid="3170497246594232819">"紫色"</item>
    <item msgid="4608643045752965568">"粉红色"</item>
    <item msgid="6131821495505931173">"红色"</item>
  </string-array>
  <string-array name="automatic_storage_management_days">
    <item msgid="687318592238852312">"超过 30 天"</item>
    <item msgid="2900554746706302178">"超过 60 天"</item>
    <item msgid="5692284879054004388">"超过 90 天"</item>
  </string-array>
  <string-array name="wifi_metered_entries">
    <item msgid="5200910605264415911">"自动检测"</item>
    <item msgid="8745603368609022803">"视为按流量计费"</item>
    <item msgid="2266114985518865625">"视为不按流量计费"</item>
  </string-array>
  <string-array name="wifi_hidden_entries">
    <item msgid="234221371123852300">"否"</item>
    <item msgid="3863157480502955888">"是"</item>
  </string-array>
  <string-array name="dark_ui_mode_entries">
    <item msgid="146804192658443142">"自动（根据一天中的时段）"</item>
    <item msgid="6620560879508595181">"始终开启"</item>
    <item msgid="6385301106124765323">"始终关闭"</item>
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
    <string name="byteShort" msgid="8340973892742019101">"B"</string>
    <string name="kilobyteShort" msgid="7542884022844556968">"kB"</string>
    <string name="megabyteShort" msgid="6355851576770428922">"MB"</string>
    <string name="gigabyteShort" msgid="3259882455212193214">"GB"</string>
    <string name="terabyteShort" msgid="231613018159186962">"TB"</string>
    <string name="petabyteShort" msgid="5637816680144990219">"PB"</string>
    <string name="fileSizeSuffix" msgid="8897567456150907538">"<xliff:g id="NUMBER">%1$s</xliff:g> <xliff:g id="UNIT">%2$s</xliff:g>"</string>
    <string name="untitled" msgid="4638956954852782576">"&lt;未命名&gt;"</string>
    <string name="emptyPhoneNumber" msgid="7694063042079676517">"（无电话号码）"</string>
    <string name="unknownName" msgid="6867811765370350269">"未知"</string>
    <string name="defaultVoiceMailAlphaTag" msgid="2660020990097733077">"语音信箱"</string>
    <string name="defaultMsisdnAlphaTag" msgid="2850889754919584674">"MSISDN1"</string>
    <string name="mmiError" msgid="5154499457739052907">"出现连接问题或 MMI 码无效。"</string>
    <string name="mmiFdnError" msgid="5224398216385316471">"只能对固定拨号号码执行此类操作。"</string>
    <string name="mmiErrorWhileRoaming" msgid="762488890299284230">"漫游时无法通过您的手机来更改来电转接设置。"</string>
    <string name="serviceEnabled" msgid="8147278346414714315">"已启用服务。"</string>
    <string name="serviceEnabledFor" msgid="6856228140453471041">"已针对以下内容启用了服务："</string>
    <string name="serviceDisabled" msgid="1937553226592516411">"已停用服务。"</string>
    <string name="serviceRegistered" msgid="6275019082598102493">"注册成功。"</string>
    <string name="serviceErased" msgid="1288584695297200972">"清除成功。"</string>
    <string name="passwordIncorrect" msgid="7612208839450128715">"密码不正确。"</string>
    <string name="mmiComplete" msgid="8232527495411698359">"MMI 码已完成。"</string>
    <string name="badPin" msgid="9015277645546710014">"您输入的旧PIN码不正确。"</string>
    <string name="badPuk" msgid="5487257647081132201">"您输入的PUK码不正确。"</string>
    <string name="mismatchPin" msgid="609379054496863419">"您输入的PIN码不一致。"</string>
    <string name="invalidPin" msgid="3850018445187475377">"输入一个4至8位数的PIN码。"</string>
    <string name="invalidPuk" msgid="8761456210898036513">"请输入至少8位数字的PUK码。"</string>
    <string name="needPuk" msgid="919668385956251611">"您的 SIM 卡已用 PUK 码锁定。请输入 PUK 码将其解锁。"</string>
    <string name="needPuk2" msgid="4526033371987193070">"输入PUK2码以解锁SIM卡。"</string>
    <string name="enablePin" msgid="209412020907207950">"失败，请开启 SIM/RUIM 卡锁定设置。"</string>
    <plurals name="pinpuk_attempts" formatted="false" msgid="1251012001539225582">
      <item quantity="other">您还可尝试 <xliff:g id="NUMBER_1">%d</xliff:g> 次。如果仍不正确，SIM 卡将被锁定。</item>
      <item quantity="one">您还可尝试 <xliff:g id="NUMBER_0">%d</xliff:g> 次。如果仍不正确，SIM 卡将被锁定。</item>
    </plurals>
    <string name="imei" msgid="2625429890869005782">"IMEI"</string>
    <string name="meid" msgid="4841221237681254195">"MEID"</string>
    <string name="ClipMmi" msgid="6952821216480289285">"来电显示"</string>
    <string name="ClirMmi" msgid="7784673673446833091">"本机号码"</string>
    <string name="ColpMmi" msgid="3065121483740183974">"连接的线路ID"</string>
    <string name="ColrMmi" msgid="4996540314421889589">"连接的线路ID限制"</string>
    <string name="CfMmi" msgid="5123218989141573515">"来电转接"</string>
    <string name="CwMmi" msgid="9129678056795016867">"来电等待"</string>
    <string name="BaMmi" msgid="455193067926770581">"通话限制"</string>
    <string name="PwdMmi" msgid="7043715687905254199">"密码更改"</string>
    <string name="PinMmi" msgid="3113117780361190304">"PIN码更改"</string>
    <string name="CnipMmi" msgid="3110534680557857162">"显示号码"</string>
    <string name="CnirMmi" msgid="3062102121430548731">"来电显示受限制"</string>
    <string name="ThreeWCMmi" msgid="9051047170321190368">"三方通话"</string>
    <string name="RuacMmi" msgid="7827887459138308886">"拒绝不想接听的骚扰电话"</string>
    <string name="CndMmi" msgid="3116446237081575808">"主叫号码传送"</string>
    <string name="DndMmi" msgid="1265478932418334331">"勿扰"</string>
    <string name="CLIRDefaultOnNextCallOn" msgid="429415409145781923">"默认不显示本机号码，在下一次通话中也不显示"</string>
    <string name="CLIRDefaultOnNextCallOff" msgid="3092918006077864624">"默认不显示本机号码，但在下一次通话中显示"</string>
    <string name="CLIRDefaultOffNextCallOn" msgid="6179425182856418465">"默认显示本机号码，但在下一次通话中不显示"</string>
    <string name="CLIRDefaultOffNextCallOff" msgid="2567998633124408552">"默认显示本机号码，在下一次通话中也显示"</string>
    <string name="serviceNotProvisioned" msgid="8614830180508686666">"未提供服务。"</string>
    <string name="CLIRPermanent" msgid="3377371145926835671">"您无法更改来电显示设置。"</string>
    <string name="RestrictedOnDataTitle" msgid="5221736429761078014">"无法使用移动数据服务"</string>
    <string name="RestrictedOnEmergencyTitle" msgid="6855466023161191166">"无法使用紧急呼救服务"</string>
    <string name="RestrictedOnNormalTitle" msgid="3179574012752700984">"无法使用语音通话服务"</string>
    <string name="RestrictedOnAllVoiceTitle" msgid="8037246983606545202">"无法使用语音服务或紧急呼救服务"</string>
    <string name="RestrictedStateContent" msgid="6538703255570997248">"已由运营商暂时关闭"</string>
    <string name="RestrictedStateContentMsimTemplate" msgid="673416791370248176">"SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g> 已由运营商暂时关闭"</string>
    <string name="NetworkPreferenceSwitchTitle" msgid="6982395015324165258">"无法连接到移动网络"</string>
    <string name="NetworkPreferenceSwitchSummary" msgid="509327194863482733">"请尝试更改首选网络。点按即可更改。"</string>
    <string name="EmergencyCallWarningTitle" msgid="813380189532491336">"无法使用紧急呼救服务"</string>
    <string name="EmergencyCallWarningSummary" msgid="1899692069750260619">"无法通过 WLAN 拨打紧急呼救电话"</string>
    <string name="notification_channel_network_alert" msgid="4427736684338074967">"提醒"</string>
    <string name="notification_channel_call_forward" msgid="2419697808481833249">"来电转接"</string>
    <string name="notification_channel_emergency_callback" msgid="6686166232265733921">"紧急回拨模式"</string>
    <string name="notification_channel_mobile_data_status" msgid="4575131690860945836">"移动数据状态"</string>
    <string name="notification_channel_sms" msgid="3441746047346135073">"短信"</string>
    <string name="notification_channel_voice_mail" msgid="3954099424160511919">"语音邮件"</string>
    <string name="notification_channel_wfc" msgid="2130802501654254801">"WLAN 通话"</string>
    <string name="notification_channel_sim" msgid="4052095493875188564">"SIM 卡状态"</string>
    <string name="peerTtyModeFull" msgid="6165351790010341421">"对方请求使用“TTY 完整”模式"</string>
    <string name="peerTtyModeHco" msgid="5728602160669216784">"对方请求使用“TTY HCO”模式"</string>
    <string name="peerTtyModeVco" msgid="1742404978686538049">"对方请求使用“TTY VCO”模式"</string>
    <string name="peerTtyModeOff" msgid="3280819717850602205">"对方请求使用“TTY 关闭”模式"</string>
    <string name="serviceClassVoice" msgid="1258393812335258019">"语音"</string>
    <string name="serviceClassData" msgid="872456782077937893">"数据"</string>
    <string name="serviceClassFAX" msgid="5566624998840486475">"传真"</string>
    <string name="serviceClassSMS" msgid="2015460373701527489">"短信"</string>
    <string name="serviceClassDataAsync" msgid="4523454783498551468">"异步"</string>
    <string name="serviceClassDataSync" msgid="7530000519646054776">"同步"</string>
    <string name="serviceClassPacket" msgid="6991006557993423453">"包"</string>
    <string name="serviceClassPAD" msgid="3235259085648271037">"PAD"</string>
    <string name="roamingText0" msgid="7170335472198694945">"启用漫游指示符"</string>
    <string name="roamingText1" msgid="5314861519752538922">"禁用漫游指示符"</string>
    <string name="roamingText2" msgid="8969929049081268115">"漫游指示符正在闪烁"</string>
    <string name="roamingText3" msgid="5148255027043943317">"不在附近"</string>
    <string name="roamingText4" msgid="8808456682550796530">"室外"</string>
    <string name="roamingText5" msgid="7604063252850354350">"漫游 - 首选系统"</string>
    <string name="roamingText6" msgid="2059440825782871513">"漫游 - 可用系统"</string>
    <string name="roamingText7" msgid="7112078724097233605">"漫游 - 联盟合作伙伴"</string>
    <string name="roamingText8" msgid="5989569778604089291">"漫游 - 高级合作伙伴"</string>
    <string name="roamingText9" msgid="7969296811355152491">"漫游 - 全部服务功能"</string>
    <string name="roamingText10" msgid="3992906999815316417">"漫游 - 服务功能不全"</string>
    <string name="roamingText11" msgid="4154476854426920970">"启用漫游横幅"</string>
    <string name="roamingText12" msgid="1189071119992726320">"禁用漫游横幅"</string>
    <string name="roamingTextSearching" msgid="8360141885972279963">"正在搜索服务"</string>
    <string name="wfcRegErrorTitle" msgid="3855061241207182194">"无法设置 WLAN 通话"</string>
  <string-array name="wfcOperatorErrorAlertMessages">
    <item msgid="3910386316304772394">"要通过 WLAN 打电话和发信息，请先让您的运营商开通此服务，然后再到“设置”中重新开启 WLAN 通话功能（错误代码：<xliff:g id="CODE">%1$s</xliff:g>）。"</item>
  </string-array>
  <string-array name="wfcOperatorErrorNotificationMessages">
    <item msgid="7372514042696663278">"向您的运营商注册 WLAN 通话时遇到问题：<xliff:g id="CODE">%1$s</xliff:g>"</item>
  </string-array>
  <string-array name="wfcSpnFormats">
    <item msgid="6830082633573257149">"%s"</item>
    <item msgid="4397097370387921767">"%s WLAN 通话功能"</item>
  </string-array>
    <string name="wifi_calling_off_summary" msgid="8720659586041656098">"关闭"</string>
    <string name="wfc_mode_wifi_preferred_summary" msgid="1994113411286935263">"首选 WLAN"</string>
    <string name="wfc_mode_cellular_preferred_summary" msgid="1988279625335345908">"首选移动数据网络"</string>
    <string name="wfc_mode_wifi_only_summary" msgid="2379919155237869320">"仅限 WLAN"</string>
    <string name="cfTemplateNotForwarded" msgid="1683685883841272560">"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="cfTemplateForwarded" msgid="1302922117498590521">"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：<xliff:g id="DIALING_NUMBER">{1}</xliff:g>"</string>
    <string name="cfTemplateForwardedTime" msgid="9206251736527085256">"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：<xliff:g id="TIME_DELAY">{2}</xliff:g>秒后<xliff:g id="DIALING_NUMBER">{1}</xliff:g>"</string>
    <string name="cfTemplateRegistered" msgid="5073237827620166285">"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="cfTemplateRegisteredTime" msgid="6781621964320635172">"<xliff:g id="BEARER_SERVICE_CODE">{0}</xliff:g>：无法转接"</string>
    <string name="fcComplete" msgid="3118848230966886575">"功能代码已拨完。"</string>
    <string name="fcError" msgid="3327560126588500777">"出现连接问题或功能代码无效。"</string>
    <string name="httpErrorOk" msgid="1191919378083472204">"确定"</string>
    <string name="httpError" msgid="7956392511146698522">"发生了网络错误。"</string>
    <string name="httpErrorLookup" msgid="4711687456111963163">"找不到该网址。"</string>
    <string name="httpErrorUnsupportedAuthScheme" msgid="6299980280442076799">"不支持此网站身份验证方案。"</string>
    <string name="httpErrorAuth" msgid="1435065629438044534">"无法通过身份验证。"</string>
    <string name="httpErrorProxyAuth" msgid="1788207010559081331">"通过代理服务器进行身份验证失败。"</string>
    <string name="httpErrorConnect" msgid="8714273236364640549">"无法连接到服务器。"</string>
    <string name="httpErrorIO" msgid="2340558197489302188">"无法与服务器进行通信，请稍后再试。"</string>
    <string name="httpErrorTimeout" msgid="4743403703762883954">"与服务器的连接超时。"</string>
    <string name="httpErrorRedirectLoop" msgid="8679596090392779516">"该页面包含太多服务器重定向。"</string>
    <string name="httpErrorUnsupportedScheme" msgid="5015730812906192208">"不支持该协议。"</string>
    <string name="httpErrorFailedSslHandshake" msgid="96549606000658641">"无法建立安全连接。"</string>
    <string name="httpErrorBadUrl" msgid="3636929722728881972">"无法打开网页，因为该网址是无效的。"</string>
    <string name="httpErrorFile" msgid="2170788515052558676">"无法使用该文件。"</string>
    <string name="httpErrorFileNotFound" msgid="6203856612042655084">"找不到请求的文件。"</string>
    <string name="httpErrorTooManyRequests" msgid="1235396927087188253">"正在处理的请求太多，请稍后重试。"</string>
    <string name="notification_title" msgid="8967710025036163822">"登录 <xliff:g id="ACCOUNT">%1$s</xliff:g> 时出错"</string>
    <string name="contentServiceSync" msgid="8353523060269335667">"同步"</string>
    <string name="contentServiceSyncNotificationTitle" msgid="7036196943673524858">"无法同步"</string>
    <string name="contentServiceTooManyDeletesNotificationDesc" msgid="4884451152168188763">"尝试删除的<xliff:g id="CONTENT_TYPE">%s</xliff:g>数量太多。"</string>
    <string name="low_memory" product="tablet" msgid="6494019234102154896">"平板电脑存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="watch" msgid="4415914910770005166">"手表存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="tv" msgid="516619861191025923">"电视存储空间已满。请删除一些文件以腾出空间。"</string>
    <string name="low_memory" product="default" msgid="3475999286680000541">"手机存储空间已满。请删除一些文件以腾出空间。"</string>
    <plurals name="ssl_ca_cert_warning" formatted="false" msgid="5106721205300213569">
      <item quantity="other">已安装证书授权中心</item>
      <item quantity="one">已安装证书授权中心</item>
    </plurals>
    <string name="ssl_ca_cert_noti_by_unknown" msgid="4475437862189850602">"受到不明第三方的监控"</string>
    <string name="ssl_ca_cert_noti_by_administrator" msgid="3541729986326153557">"由您的工作资料管理员监控"</string>
    <string name="ssl_ca_cert_noti_managed" msgid="4030263497686867141">"受到 <xliff:g id="MANAGING_DOMAIN">%s</xliff:g> 监控"</string>
    <string name="work_profile_deleted" msgid="5005572078641980632">"工作资料已删除"</string>
    <string name="work_profile_deleted_details" msgid="6307630639269092360">"工作资料管理应用缺失或损坏，因此系统已删除您的工作资料及相关数据。如需帮助，请与您的管理员联系。"</string>
    <string name="work_profile_deleted_description_dpm_wipe" msgid="8823792115612348820">"您的工作资料已不在此设备上"</string>
    <string name="work_profile_deleted_reason_maximum_password_failure" msgid="8986903510053359694">"密码尝试次数过多"</string>
    <string name="network_logging_notification_title" msgid="6399790108123704477">"设备为受管理设备"</string>
    <string name="network_logging_notification_text" msgid="7930089249949354026">"贵单位会管理该设备，且可能会监控网络流量。点按即可了解详情。"</string>
    <string name="factory_reset_warning" msgid="5423253125642394387">"系统将清空您的设备"</string>
    <string name="factory_reset_message" msgid="9024647691106150160">"无法使用管理应用，系统现在将清空您的设备。\n\n如有疑问，请与您所在单位的管理员联系。"</string>
    <string name="printing_disabled_by" msgid="8936832919072486965">"“<xliff:g id="OWNER_APP">%s</xliff:g>”已停用打印功能。"</string>
    <string name="me" msgid="6545696007631404292">"我"</string>
    <string name="power_dialog" product="tablet" msgid="8545351420865202853">"平板电脑选项"</string>
    <string name="power_dialog" product="tv" msgid="6153888706430556356">"电视选项"</string>
    <string name="power_dialog" product="default" msgid="1319919075463988638">"手机选项"</string>
    <string name="silent_mode" msgid="7167703389802618663">"静音模式"</string>
    <string name="turn_on_radio" msgid="3912793092339962371">"打开无线电"</string>
    <string name="turn_off_radio" msgid="8198784949987062346">"关闭无线电"</string>
    <string name="screen_lock" msgid="799094655496098153">"屏幕锁定"</string>
    <string name="power_off" msgid="4266614107412865048">"关机"</string>
    <string name="silent_mode_silent" msgid="319298163018473078">"振铃器关闭"</string>
    <string name="silent_mode_vibrate" msgid="7072043388581551395">"振铃器振动"</string>
    <string name="silent_mode_ring" msgid="8592241816194074353">"振铃器开启"</string>
    <string name="reboot_to_update_title" msgid="6212636802536823850">"Android 系统更新"</string>
    <string name="reboot_to_update_prepare" msgid="6305853831955310890">"正在准备更新…"</string>
    <string name="reboot_to_update_package" msgid="3871302324500927291">"正在处理更新软件包…"</string>
    <string name="reboot_to_update_reboot" msgid="6428441000951565185">"正在重新启动…"</string>
    <string name="reboot_to_reset_title" msgid="4142355915340627490">"恢复出厂设置"</string>
    <string name="reboot_to_reset_message" msgid="2432077491101416345">"正在重新启动…"</string>
    <string name="shutdown_progress" msgid="2281079257329981203">"正在关机..."</string>
    <string name="shutdown_confirm" product="tablet" msgid="3385745179555731470">"您的平板电脑会关闭。"</string>
    <string name="shutdown_confirm" product="tv" msgid="476672373995075359">"您的电视即将关闭。"</string>
    <string name="shutdown_confirm" product="watch" msgid="3490275567476369184">"您的手表即将关机。"</string>
    <string name="shutdown_confirm" product="default" msgid="649792175242821353">"您的手机将会关机。"</string>
    <string name="shutdown_confirm_question" msgid="2906544768881136183">"您要关机吗？"</string>
    <string name="reboot_safemode_title" msgid="7054509914500140361">"重新启动并进入安全模式"</string>
    <string name="reboot_safemode_confirm" msgid="55293944502784668">"您要重新启动并进入安全模式吗？这样会停用您已安装的所有第三方应用。再次重新启动将恢复这些应用。"</string>
    <string name="recent_tasks_title" msgid="3691764623638127888">"近期任务"</string>
    <string name="no_recent_tasks" msgid="8794906658732193473">"最近没有运行任何应用"</string>
    <string name="global_actions" product="tablet" msgid="408477140088053665">"平板电脑选项"</string>
    <string name="global_actions" product="tv" msgid="7240386462508182976">"电视选项"</string>
    <string name="global_actions" product="default" msgid="2406416831541615258">"手机选项"</string>
    <string name="global_action_lock" msgid="2844945191792119712">"屏幕锁定"</string>
    <string name="global_action_power_off" msgid="4471879440839879722">"关机"</string>
    <string name="global_action_emergency" msgid="7112311161137421166">"紧急呼救"</string>
    <string name="global_action_bug_report" msgid="7934010578922304799">"错误报告"</string>
    <string name="global_action_logout" msgid="935179188218826050">"结束会话"</string>
    <string name="global_action_screenshot" msgid="8329831278085426283">"屏幕截图"</string>
    <string name="bugreport_title" msgid="2667494803742548533">"提交错误报告"</string>
    <string name="bugreport_message" msgid="398447048750350456">"这会收集有关当前设备状态的信息，并以电子邮件的形式进行发送。从开始生成错误报告到准备好发送需要一点时间，请耐心等待。"</string>
    <string name="bugreport_option_interactive_title" msgid="8635056131768862479">"互动式报告"</string>
    <string name="bugreport_option_interactive_summary" msgid="229299488536107968">"在大多数情况下，建议您使用此选项，以便追踪报告的生成进度，输入与相应问题相关的更多详细信息，以及截取屏幕截图。系统可能会省略掉一些不常用的区段，从而缩短生成报告的时间。"</string>
    <string name="bugreport_option_full_title" msgid="6354382025840076439">"完整报告"</string>
    <string name="bugreport_option_full_summary" msgid="7210859858969115745">"如果您的设备无响应或运行速度缓慢，或者您需要查看所有区段的报告信息，则建议您使用此选项将系统干扰程度降到最低。系统不支持您输入更多详细信息，也不会截取其他屏幕截图。"</string>
    <plurals name="bugreport_countdown" formatted="false" msgid="6878900193900090368">
      <item quantity="other">系统将在 <xliff:g id="NUMBER_1">%d</xliff:g> 秒后对错误报告进行截屏。</item>
      <item quantity="one">系统将在 <xliff:g id="NUMBER_0">%d</xliff:g> 秒后对错误报告进行截屏。</item>
    </plurals>
    <string name="global_action_toggle_silent_mode" msgid="8219525344246810925">"静音模式"</string>
    <string name="global_action_silent_mode_on_status" msgid="3289841937003758806">"声音已关闭"</string>
    <string name="global_action_silent_mode_off_status" msgid="1506046579177066419">"声音已开启"</string>
    <string name="global_actions_toggle_airplane_mode" msgid="5884330306926307456">"飞行模式"</string>
    <string name="global_actions_airplane_mode_on_status" msgid="2719557982608919750">"已开启飞行模式"</string>
    <string name="global_actions_airplane_mode_off_status" msgid="5075070442854490296">"未开启飞行模式"</string>
    <string name="global_action_toggle_battery_saver" msgid="708515500418994208">"省电模式"</string>
    <string name="global_action_battery_saver_on_status" msgid="484059130698197787">"省电模式已关闭"</string>
    <string name="global_action_battery_saver_off_status" msgid="75550964969478405">"省电模式已开启"</string>
    <string name="global_action_settings" msgid="1756531602592545966">"设置"</string>
    <string name="global_action_assist" msgid="3892832961594295030">"助手应用"</string>
    <string name="global_action_voice_assist" msgid="7751191495200504480">"语音助理"</string>
    <string name="global_action_lockdown" msgid="1099326950891078929">"锁定"</string>
    <string name="status_bar_notification_info_overflow" msgid="5301981741705354993">"999+"</string>
    <string name="notification_hidden_text" msgid="6351207030447943784">"新通知"</string>
    <string name="notification_channel_virtual_keyboard" msgid="6969925135507955575">"虚拟键盘"</string>
    <string name="notification_channel_physical_keyboard" msgid="7297661826966861459">"实体键盘"</string>
    <string name="notification_channel_security" msgid="7345516133431326347">"安全性"</string>
    <string name="notification_channel_car_mode" msgid="3553380307619874564">"车载模式"</string>
    <string name="notification_channel_account" msgid="7577959168463122027">"帐号状态"</string>
    <string name="notification_channel_developer" msgid="7579606426860206060">"开发者消息"</string>
    <string name="notification_channel_updates" msgid="4794517569035110397">"更新"</string>
    <string name="notification_channel_network_status" msgid="5025648583129035447">"网络状态"</string>
    <string name="notification_channel_network_alerts" msgid="2895141221414156525">"网络提醒"</string>
    <string name="notification_channel_network_available" msgid="4531717914138179517">"有可用的网络"</string>
    <string name="notification_channel_vpn" msgid="8330103431055860618">"VPN 状态"</string>
    <string name="notification_channel_device_admin" msgid="1568154104368069249">"设备管理"</string>
    <string name="notification_channel_alerts" msgid="4496839309318519037">"提醒"</string>
    <string name="notification_channel_retail_mode" msgid="6088920674914038779">"零售演示模式"</string>
    <string name="notification_channel_usb" msgid="9006850475328924681">"USB 连接"</string>
    <string name="notification_channel_heavy_weight_app" msgid="6218742927792852607">"应用正在运行中"</string>
    <string name="notification_channel_foreground_service" msgid="3931987440602669158">"消耗电量的应用"</string>
    <string name="foreground_service_app_in_background" msgid="1060198778219731292">"<xliff:g id="APP_NAME">%1$s</xliff:g>正在消耗电量"</string>
    <string name="foreground_service_apps_in_background" msgid="7175032677643332242">"<xliff:g id="NUMBER">%1$d</xliff:g> 个应用正在消耗电量"</string>
    <string name="foreground_service_tap_for_details" msgid="372046743534354644">"点按即可详细了解电量和流量消耗情况"</string>
    <string name="foreground_service_multiple_separator" msgid="4021901567939866542">"<xliff:g id="LEFT_SIDE">%1$s</xliff:g>、<xliff:g id="RIGHT_SIDE">%2$s</xliff:g>"</string>
    <string name="safeMode" msgid="2788228061547930246">"安全模式"</string>
    <string name="android_system_label" msgid="6577375335728551336">"Android 系统"</string>
    <string name="user_owner_label" msgid="8836124313744349203">"切换到个人资料"</string>
    <string name="managed_profile_label" msgid="8947929265267690522">"切换到工作资料"</string>
    <string name="permgrouplab_contacts" msgid="3657758145679177612">"通讯录"</string>
    <string name="permgroupdesc_contacts" msgid="6951499528303668046">"访问您的通讯录"</string>
    <string name="permgrouprequest_contacts" msgid="6032805601881764300">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的通讯录吗？"</string>
    <string name="permgrouplab_location" msgid="7275582855722310164">"位置信息"</string>
    <string name="permgroupdesc_location" msgid="1346617465127855033">"获取此设备的位置信息"</string>
    <string name="permgrouprequest_location" msgid="3788275734953323491">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;获取此设备的位置信息吗？"</string>
    <string name="permgrouplab_calendar" msgid="5863508437783683902">"日历"</string>
    <string name="permgroupdesc_calendar" msgid="3889615280211184106">"访问您的日历"</string>
    <string name="permgrouprequest_calendar" msgid="289900767793189421">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的日历吗？"</string>
    <string name="permgrouplab_sms" msgid="228308803364967808">"短信"</string>
    <string name="permgroupdesc_sms" msgid="4656988620100940350">"发送和查看短信"</string>
    <string name="permgrouprequest_sms" msgid="7168124215838204719">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;发送和查看短信吗？"</string>
    <string name="permgrouplab_storage" msgid="1971118770546336966">"存储空间"</string>
    <string name="permgroupdesc_storage" msgid="637758554581589203">"访问您设备上的照片、媒体内容和文件"</string>
    <string name="permgrouprequest_storage" msgid="7885942926944299560">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您设备上的照片、媒体内容和文件吗？"</string>
    <string name="permgrouplab_microphone" msgid="171539900250043464">"麦克风"</string>
    <string name="permgroupdesc_microphone" msgid="4988812113943554584">"录制音频"</string>
    <string name="permgrouprequest_microphone" msgid="9167492350681916038">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;录音吗？"</string>
    <string name="permgrouplab_camera" msgid="4820372495894586615">"相机"</string>
    <string name="permgroupdesc_camera" msgid="3250611594678347720">"拍摄照片和录制视频"</string>
    <string name="permgrouprequest_camera" msgid="1299833592069671756">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;拍摄照片和录制视频吗？"</string>
    <string name="permgrouplab_calllog" msgid="8798646184930388160">"通话记录"</string>
    <string name="permgroupdesc_calllog" msgid="3006237336748283775">"读取和写入手机通话记录"</string>
    <string name="permgrouprequest_calllog" msgid="8487355309583773267">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问您的手机通话记录吗？"</string>
    <string name="permgrouplab_phone" msgid="5229115638567440675">"电话"</string>
    <string name="permgroupdesc_phone" msgid="6234224354060641055">"拨打电话和管理通话"</string>
    <string name="permgrouprequest_phone" msgid="9166979577750581037">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;拨打电话和管理通话吗？"</string>
    <string name="permgrouplab_sensors" msgid="416037179223226722">"身体传感器"</string>
    <string name="permgroupdesc_sensors" msgid="7147968539346634043">"访问与您的生命体征相关的传感器数据"</string>
    <string name="permgrouprequest_sensors" msgid="6349806962814556786">"允许&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;访问与您的生命体征相关的传感器数据吗？"</string>
    <string name="capability_title_canRetrieveWindowContent" msgid="3901717936930170320">"检索窗口内容"</string>
    <string name="capability_desc_canRetrieveWindowContent" msgid="3772225008605310672">"检测您正访问的窗口的内容。"</string>
    <string name="capability_title_canRequestTouchExploration" msgid="3108723364676667320">"启用触摸浏览"</string>
    <string name="capability_desc_canRequestTouchExploration" msgid="7543249041581408313">"设备将大声读出您点按的内容，同时您可以通过手势来浏览屏幕。"</string>
    <string name="capability_title_canRequestFilterKeyEvents" msgid="2103440391902412174">"监测您输入的文字"</string>
    <string name="capability_desc_canRequestFilterKeyEvents" msgid="7463135292204152818">"包含个人数据，例如信用卡号和密码。"</string>
    <string name="capability_title_canControlMagnification" msgid="3593493281059424855">"控制显示内容放大功能"</string>
    <string name="capability_desc_canControlMagnification" msgid="4791858203568383773">"控制显示内容的缩放级别和位置。"</string>
    <string name="capability_title_canPerformGestures" msgid="7418984730362576862">"执行手势"</string>
    <string name="capability_desc_canPerformGestures" msgid="8296373021636981249">"可执行点按、滑动、双指张合等手势。"</string>
    <string name="capability_title_canCaptureFingerprintGestures" msgid="6309568287512278670">"指纹手势"</string>
    <string name="capability_desc_canCaptureFingerprintGestures" msgid="4386487962402228670">"可以捕获在设备指纹传感器上执行的手势。"</string>
    <string name="permlab_statusBar" msgid="7417192629601890791">"停用或修改状态栏"</string>
    <string name="permdesc_statusBar" msgid="8434669549504290975">"允许应用停用状态栏或者增删系统图标。"</string>
    <string name="permlab_statusBarService" msgid="4826835508226139688">"用作状态栏"</string>
    <string name="permdesc_statusBarService" msgid="716113660795976060">"允许以状态栏形式显示应用。"</string>
    <string name="permlab_expandStatusBar" msgid="1148198785937489264">"展开/收拢状态栏"</string>
    <string name="permdesc_expandStatusBar" msgid="6917549437129401132">"允许应用展开或收起状态栏。"</string>
    <string name="permlab_install_shortcut" msgid="4279070216371564234">"安装快捷方式"</string>
    <string name="permdesc_install_shortcut" msgid="8341295916286736996">"允许应用自行添加主屏幕快捷方式。"</string>
    <string name="permlab_uninstall_shortcut" msgid="4729634524044003699">"卸载快捷方式"</string>
    <string name="permdesc_uninstall_shortcut" msgid="6745743474265057975">"允许应用自行删除主屏幕快捷方式。"</string>
    <string name="permlab_processOutgoingCalls" msgid="3906007831192990946">"重新设置外拨电话的路径"</string>
    <string name="permdesc_processOutgoingCalls" msgid="5156385005547315876">"允许应用在拨出电话时查看拨打的电话号码，并选择改为拨打其他号码或完全中止通话。"</string>
    <string name="permlab_answerPhoneCalls" msgid="4077162841226223337">"接听来电"</string>
    <string name="permdesc_answerPhoneCalls" msgid="2901889867993572266">"允许该应用接听来电。"</string>
    <string name="permlab_receiveSms" msgid="8673471768947895082">"接收讯息（短信）"</string>
    <string name="permdesc_receiveSms" msgid="6424387754228766939">"允许该应用接收和处理短信。这就意味着，该应用可能会监视发送到您设备的短信，或删除发送到您设备的短信而不向您显示。"</string>
    <string name="permlab_receiveMms" msgid="1821317344668257098">"接收讯息（彩信）"</string>
    <string name="permdesc_receiveMms" msgid="533019437263212260">"允许该应用接收和处理彩信。这就意味着，该应用可能会监视发送到您设备的彩信，或删除发送到您设备的彩信而不向您显示。"</string>
    <string name="permlab_readCellBroadcasts" msgid="1598328843619646166">"读取小区广播消息"</string>
    <string name="permdesc_readCellBroadcasts" msgid="6361972776080458979">"允许应用读取您的设备收到的小区广播消息。小区广播消息是在某些地区发送的、用于发布紧急情况警告的提醒信息。恶意应用可能会在您收到小区紧急广播时干扰您设备的性能或操作。"</string>
    <string name="permlab_subscribedFeedsRead" msgid="4756609637053353318">"读取订阅的供稿"</string>
    <string name="permdesc_subscribedFeedsRead" msgid="5557058907906144505">"允许应用获取有关当前同步的 Feed 的详情。"</string>
    <string name="permlab_sendSms" msgid="7544599214260982981">"发送短信"</string>
    <string name="permdesc_sendSms" msgid="7094729298204937667">"允许该应用发送短信。此权限可能会导致意外收费。恶意应用可能会未经您的确认而发送短信，由此产生相关费用。"</string>
    <string name="permlab_readSms" msgid="8745086572213270480">"读取短信"</string>
    <string name="permdesc_readSms" product="tablet" msgid="4741697454888074891">"此应用可读取您平板电脑上存储的所有短信。"</string>
    <string name="permdesc_readSms" product="tv" msgid="5796670395641116592">"此应用可读取您电视上存储的所有短信。"</string>
    <string name="permdesc_readSms" product="default" msgid="6826832415656437652">"此应用可读取您手机上存储的所有短信。"</string>
    <string name="permlab_receiveWapPush" msgid="5991398711936590410">"接收讯息 (WAP)"</string>
    <string name="permdesc_receiveWapPush" msgid="748232190220583385">"允许该应用接收和处理 WAP 消息。此权限包括监视发送给您的消息或删除发送给您的消息而不向您显示的功能。"</string>
    <string name="permlab_getTasks" msgid="6466095396623933906">"检索正在运行的应用"</string>
    <string name="permdesc_getTasks" msgid="7454215995847658102">"允许该应用检索近期运行的和当前正在运行的任务的相关信息。此权限可让该应用了解设备上使用了哪些应用。"</string>
    <string name="permlab_manageProfileAndDeviceOwners" msgid="7918181259098220004">"管理个人资料和设备所有者"</string>
    <string name="permdesc_manageProfileAndDeviceOwners" msgid="106894851498657169">"允许应用设置个人资料所有者和设备所有者。"</string>
    <string name="permlab_reorderTasks" msgid="2018575526934422779">"对正在运行的应用重新排序"</string>
    <string name="permdesc_reorderTasks" msgid="7734217754877439351">"允许该应用将任务移动到前台和后台。该应用可能不经您的命令自行执行此操作。"</string>
    <string name="permlab_enableCarMode" msgid="5684504058192921098">"启用车载模式"</string>
    <string name="permdesc_enableCarMode" msgid="4853187425751419467">"允许应用启用车载模式。"</string>
    <string name="permlab_killBackgroundProcesses" msgid="3914026687420177202">"关闭其他应用"</string>
    <string name="permdesc_killBackgroundProcesses" msgid="4593353235959733119">"允许该应用结束其他应用的后台进程。此权限可导致其他应用停止运行。"</string>
    <string name="permlab_systemAlertWindow" msgid="7238805243128138690">"此应用可显示在其他应用上方"</string>
    <string name="permdesc_systemAlertWindow" msgid="2393776099672266188">"此应用可显示在其他应用上方或屏幕的其他部分。这可能会妨碍您正常地使用应用，且其他应用的显示方式可能会受到影响。"</string>
    <string name="permlab_runInBackground" msgid="7365290743781858803">"在后台运行"</string>
    <string name="permdesc_runInBackground" msgid="7370142232209999824">"此应用可在后台运行，这样可能会加快耗电速度。"</string>
    <string name="permlab_useDataInBackground" msgid="8694951340794341809">"在后台使用数据"</string>
    <string name="permdesc_useDataInBackground" msgid="6049514223791806027">"此应用可在后台使用数据，这样可能会增加流量消耗。"</string>
    <string name="permlab_persistentActivity" msgid="8841113627955563938">"让应用始终运行"</string>
    <string name="permdesc_persistentActivity" product="tablet" msgid="8525189272329086137">"允许该应用在内存中持续保留其自身的某些组件。这会限制其他应用可用的内存，从而减缓平板电脑运行速度。"</string>
    <string name="permdesc_persistentActivity" product="tv" msgid="5086862529499103587">"允许应用在内存中持续保留其自身的部分组件。此权限可能会限制其他应用可用的内存，从而减缓电视运行速度。"</string>
    <string name="permdesc_persistentActivity" product="default" msgid="4384760047508278272">"允许该应用在内存中持续保留其自身的某些组件。这会限制其他应用可用的内存，从而减缓手机运行速度。"</string>
    <string name="permlab_foregroundService" msgid="3310786367649133115">"运行前台服务"</string>
    <string name="permdesc_foregroundService" msgid="6471634326171344622">"允许该应用使用前台服务。"</string>
    <string name="permlab_getPackageSize" msgid="7472921768357981986">"计算应用存储空间"</string>
    <string name="permdesc_getPackageSize" msgid="3921068154420738296">"允许应用检索其代码、数据和缓存大小"</string>
    <string name="permlab_writeSettings" msgid="2226195290955224730">"修改系统设置"</string>
    <string name="permdesc_writeSettings" msgid="7775723441558907181">"允许应用修改系统的设置数据。恶意应用可能会破坏您的系统配置。"</string>
    <string name="permlab_receiveBootCompleted" msgid="5312965565987800025">"开机启动"</string>
    <string name="permdesc_receiveBootCompleted" product="tablet" msgid="7390304664116880704">"允许应用在系统完成引导后立即自动启动。这样可能会延长平板电脑的启动时间，并允许应用始终运行，从而导致平板电脑总体运行速度减慢。"</string>
    <string name="permdesc_receiveBootCompleted" product="tv" msgid="4525890122209673621">"允许应用在系统启动完毕后立即自行启动。此权限可能会延长电视的启动时间，而且会因为系统一直运行该应用而导致电视的整体运行速度变慢。"</string>
    <string name="permdesc_receiveBootCompleted" product="default" msgid="513950589102617504">"允许应用在系统完成引导后立即自动启动。这样可能会延长手机的启动时间，并允许应用始终运行，从而导致手机总体运行速度减慢。"</string>
    <string name="permlab_broadcastSticky" msgid="7919126372606881614">"发送持久广播"</string>
    <string name="permdesc_broadcastSticky" product="tablet" msgid="7749760494399915651">"允许该应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致平板电脑使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permdesc_broadcastSticky" product="tv" msgid="6839285697565389467">"允许应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致电视使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permdesc_broadcastSticky" product="default" msgid="2825803764232445091">"允许该应用发送持久广播消息，此类消息在广播结束后仍会保留。过度使用可能会导致手机使用过多内存，从而降低其速度或稳定性。"</string>
    <string name="permlab_readContacts" msgid="8348481131899886131">"读取联系人"</string>
    <string name="permdesc_readContacts" product="tablet" msgid="5294866856941149639">"允许该应用读取您平板电脑上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定个人通信的频率。此权限可让应用保存您的联系人数据，而恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permdesc_readContacts" product="tv" msgid="1839238344654834087">"允许应用读取您的电视上存储的联系人相关数据，包括您与特定联系人通话、发送电子邮件或通过其他方式进行通信的频率。此权限可让应用保存您的联系人数据，而且恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permdesc_readContacts" product="default" msgid="8440654152457300662">"允许该应用读取您手机上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定个人通信的频率。此权限可让应用保存您的联系人数据，而恶意应用可能会在您不知情的情况下分享联系人数据。"</string>
    <string name="permlab_writeContacts" msgid="5107492086416793544">"修改您的通讯录"</string>
    <string name="permdesc_writeContacts" product="tablet" msgid="897243932521953602">"允许该应用修改您平板电脑上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定联系人通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permdesc_writeContacts" product="tv" msgid="5438230957000018959">"允许应用修改您的电视上存储的联系人相关数据，包括您与特定联系人通话、发送电子邮件或通过其他方式进行通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permdesc_writeContacts" product="default" msgid="589869224625163558">"允许该应用修改您手机上存储的联系人的相关数据，包括您通过打电话、发送电子邮件或以其他方式与特定联系人通信的频率。此权限可让应用删除联系人数据。"</string>
    <string name="permlab_readCallLog" msgid="3478133184624102739">"读取通话记录"</string>
    <string name="permdesc_readCallLog" msgid="3204122446463552146">"此应用可读取您的通话记录。"</string>
    <string name="permlab_writeCallLog" msgid="8552045664743499354">"新建/修改/删除通话记录"</string>
    <string name="permdesc_writeCallLog" product="tablet" msgid="6661806062274119245">"允许该应用修改平板电脑的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permdesc_writeCallLog" product="tv" msgid="4225034892248398019">"允许应用修改电视的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permdesc_writeCallLog" product="default" msgid="683941736352787842">"允许该应用修改手机的通话记录，包括有关来电和外拨电话的数据。恶意应用可能会借此清除或修改您的通话记录。"</string>
    <string name="permlab_bodySensors" msgid="4683341291818520277">"访问身体传感器（如心率监测器）"</string>
    <string name="permdesc_bodySensors" product="default" msgid="4380015021754180431">"允许该应用存取监测您身体状况的传感器所收集的数据，例如您的心率。"</string>
    <string name="permlab_readCalendar" msgid="6716116972752441641">"读取日历活动和详情"</string>
    <string name="permdesc_readCalendar" product="tablet" msgid="4993979255403945892">"此应用可读取您平板电脑上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permdesc_readCalendar" product="tv" msgid="8837931557573064315">"此应用可读取您电视上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permdesc_readCalendar" product="default" msgid="4373978642145196715">"此应用可读取您手机上存储的所有日历活动，并分享或保存您的日历数据。"</string>
    <string name="permlab_writeCalendar" msgid="8438874755193825647">"添加或修改日历活动，并在所有者不知情的情况下向邀请对象发送电子邮件"</string>
    <string name="permdesc_writeCalendar" product="tablet" msgid="1675270619903625982">"此应用可在平板电脑上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permdesc_writeCalendar" product="tv" msgid="9017809326268135866">"此应用可在电视上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permdesc_writeCalendar" product="default" msgid="7592791790516943173">"此应用可在手机上添加、移除或更改日历活动。此应用可能会以日历所有者的身份发送消息，或在不通知所有者的情况下更改活动。"</string>
    <string name="permlab_accessLocationExtraCommands" msgid="2836308076720553837">"获取额外的位置信息提供程序命令"</string>
    <string name="permdesc_accessLocationExtraCommands" msgid="6078307221056649927">"允许该应用使用其他的位置信息提供程序命令。此权限使该应用可以干扰GPS或其他位置信息源的运作。"</string>
    <string name="permlab_accessFineLocation" msgid="251034415460950944">"访问确切位置信息（以 GPS 和网络为依据）"</string>
    <string name="permdesc_accessFineLocation" msgid="5821994817969957884">"此应用可根据 GPS 或网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的手机必须支持并开启这些位置信息服务，此应用才能使用这些服务。这可能会增加耗电量。"</string>
    <string name="permlab_accessCoarseLocation" msgid="7715277613928539434">"访问大致位置信息（以网络为依据）"</string>
    <string name="permdesc_accessCoarseLocation" product="tablet" msgid="3373266766487862426">"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的平板电脑必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permdesc_accessCoarseLocation" product="tv" msgid="1884022719818788511">"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的电视必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permdesc_accessCoarseLocation" product="default" msgid="7788009094906196995">"此应用可根据网络来源（例如基站和 WLAN 网络）获取您的位置信息。您的手机必须支持并开启这些位置信息服务，此应用才能使用这些服务。"</string>
    <string name="permlab_modifyAudioSettings" msgid="6095859937069146086">"更改您的音频设置"</string>
    <string name="permdesc_modifyAudioSettings" msgid="3522565366806248517">"允许该应用修改全局音频设置，例如音量和用于输出的扬声器。"</string>
    <string name="permlab_recordAudio" msgid="3876049771427466323">"录音"</string>
    <string name="permdesc_recordAudio" msgid="4245930455135321433">"此应用可随时使用麦克风进行录音。"</string>
    <string name="permlab_sim_communication" msgid="2935852302216852065">"向 SIM 卡发送命令"</string>
    <string name="permdesc_sim_communication" msgid="5725159654279639498">"允许应用向SIM卡发送命令（此权限具有很高的危险性）。"</string>
    <string name="permlab_camera" msgid="3616391919559751192">"拍摄照片和视频"</string>
    <string name="permdesc_camera" msgid="5392231870049240670">"此应用可随时使用相机拍摄照片和录制视频。"</string>
    <string name="permlab_vibrate" msgid="7696427026057705834">"控制振动"</string>
    <string name="permdesc_vibrate" msgid="6284989245902300945">"允许应用控制振动器。"</string>
    <string name="permlab_callPhone" msgid="3925836347681847954">"拨打电话"</string>
    <string name="permdesc_callPhone" msgid="3740797576113760827">"允许该应用在您未执行操作的情况下拨打电话号码。此权限可能会导致意外收费或呼叫。请注意，此权限不允许该应用拨打紧急电话号码。恶意应用可通过拨打电话产生相关费用，而无需您的确认。"</string>
    <string name="permlab_accessImsCallService" msgid="3574943847181793918">"使用即时通讯通话服务"</string>
    <string name="permdesc_accessImsCallService" msgid="8992884015198298775">"允许应用自行使用即时通讯服务拨打电话。"</string>
    <string name="permlab_readPhoneState" msgid="9178228524507610486">"读取手机状态和身份"</string>
    <string name="permdesc_readPhoneState" msgid="1639212771826125528">"允许该应用访问设备的电话功能。此权限可让该应用确定本机号码和设备 ID、是否正处于通话状态以及拨打的号码。"</string>
    <string name="permlab_manageOwnCalls" msgid="1503034913274622244">"通过系统转接来电"</string>
    <string name="permdesc_manageOwnCalls" msgid="6552974537554717418">"允许该应用通过系统转接来电，以改善通话体验。"</string>
    <string name="permlab_acceptHandover" msgid="2661534649736022409">"继续进行来自其他应用的通话"</string>
    <string name="permdesc_acceptHandovers" msgid="4570660484220539698">"允许该应用继续进行在其他应用中发起的通话。"</string>
    <string name="permlab_readPhoneNumbers" msgid="6108163940932852440">"读取电话号码"</string>
    <string name="permdesc_readPhoneNumbers" msgid="8559488833662272354">"允许该应用访问设备上的电话号码。"</string>
    <string name="permlab_wakeLock" product="tablet" msgid="1531731435011495015">"阻止平板电脑进入休眠状态"</string>
    <string name="permlab_wakeLock" product="tv" msgid="2601193288949154131">"阻止电视进入休眠状态"</string>
    <string name="permlab_wakeLock" product="default" msgid="573480187941496130">"防止手机休眠"</string>
    <string name="permdesc_wakeLock" product="tablet" msgid="7311319824400447868">"允许应用阻止平板电脑进入休眠状态。"</string>
    <string name="permdesc_wakeLock" product="tv" msgid="3208534859208996974">"允许应用阻止电视进入休眠状态。"</string>
    <string name="permdesc_wakeLock" product="default" msgid="8559100677372928754">"允许应用阻止手机进入休眠状态。"</string>
    <string name="permlab_transmitIr" msgid="7545858504238530105">"发射红外线"</string>
    <string name="permdesc_transmitIr" product="tablet" msgid="5358308854306529170">"允许应用使用平板电脑的红外线发射器。"</string>
    <string name="permdesc_transmitIr" product="tv" msgid="3926790828514867101">"允许应用使用电视的红外线发射器。"</string>
    <string name="permdesc_transmitIr" product="default" msgid="7957763745020300725">"允许应用使用手机的红外线发射器。"</string>
    <string name="permlab_setWallpaper" msgid="6627192333373465143">"设置壁纸"</string>
    <string name="permdesc_setWallpaper" msgid="7373447920977624745">"允许应用设置系统壁纸。"</string>
    <string name="permlab_setWallpaperHints" msgid="3278608165977736538">"调整您的壁纸大小"</string>
    <string name="permdesc_setWallpaperHints" msgid="8235784384223730091">"允许应用设置有关系统壁纸大小的提示。"</string>
    <string name="permlab_setTimeZone" msgid="2945079801013077340">"设置时区"</string>
    <string name="permdesc_setTimeZone" product="tablet" msgid="1676983712315827645">"允许应用更改平板电脑的时区。"</string>
    <string name="permdesc_setTimeZone" product="tv" msgid="888864653946175955">"允许应用更改电视的时区。"</string>
    <string name="permdesc_setTimeZone" product="default" msgid="4499943488436633398">"允许应用更改手机的时区。"</string>
    <string name="permlab_getAccounts" msgid="1086795467760122114">"查找设备上的帐号"</string>
    <string name="permdesc_getAccounts" product="tablet" msgid="2741496534769660027">"允许该应用获取平板电脑已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permdesc_getAccounts" product="tv" msgid="4190633395633907543">"允许应用获取电视已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permdesc_getAccounts" product="default" msgid="3448316822451807382">"允许该应用获取手机已知的帐号列表，其中可能包括由已安装的应用创建的所有帐号。"</string>
    <string name="permlab_accessNetworkState" msgid="4951027964348974773">"查看网络连接"</string>
    <string name="permdesc_accessNetworkState" msgid="8318964424675960975">"允许该应用查看网络连接的相关信息，例如存在和连接的网络。"</string>
    <string name="permlab_createNetworkSockets" msgid="7934516631384168107">"拥有完全的网络访问权限"</string>
    <string name="permdesc_createNetworkSockets" msgid="3403062187779724185">"允许该应用创建网络套接字和使用自定义网络协议。浏览器和其他某些应用提供了向互联网发送数据的途径，因此应用无需该权限即可向互联网发送数据。"</string>
    <string name="permlab_changeNetworkState" msgid="958884291454327309">"更改网络连接性"</string>
    <string name="permdesc_changeNetworkState" msgid="6789123912476416214">"允许应用更改网络连接的状态。"</string>
    <string name="permlab_changeTetherState" msgid="5952584964373017960">"更改网络共享连接"</string>
    <string name="permdesc_changeTetherState" msgid="1524441344412319780">"允许应用更改绑定网络连接的状态。"</string>
    <string name="permlab_accessWifiState" msgid="5202012949247040011">"查看WLAN连接"</string>
    <string name="permdesc_accessWifiState" msgid="5002798077387803726">"允许该应用查看WLAN网络的相关信息，例如是否启用了WLAN以及连接的WLAN设备的名称。"</string>
    <string name="permlab_changeWifiState" msgid="6550641188749128035">"连接WLAN网络和断开连接"</string>
    <string name="permdesc_changeWifiState" msgid="7137950297386127533">"允许该应用与WLAN接入点建立和断开连接，以及更改WLAN网络的设备配置。"</string>
    <string name="permlab_changeWifiMulticastState" msgid="1368253871483254784">"允许接收WLAN多播"</string>
    <string name="permdesc_changeWifiMulticastState" product="tablet" msgid="7969774021256336548">"允许该应用使用多播地址接收发送到WLAN网络上所有设备（而不仅仅是您的平板电脑）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permdesc_changeWifiMulticastState" product="tv" msgid="9031975661145014160">"允许应用使用多播地址接收发送到 WLAN 网络中所有设备（而不仅仅是您的电视）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permdesc_changeWifiMulticastState" product="default" msgid="6851949706025349926">"允许该应用使用多播地址接收发送到WLAN网络上所有设备（而不仅仅是您的手机）的数据包。该操作的耗电量比非多播模式要大。"</string>
    <string name="permlab_bluetoothAdmin" msgid="6006967373935926659">"访问蓝牙设置"</string>
    <string name="permdesc_bluetoothAdmin" product="tablet" msgid="6921177471748882137">"允许应用配置本地蓝牙平板电脑，并允许其查找远程设备且与之配对。"</string>
    <string name="permdesc_bluetoothAdmin" product="tv" msgid="3373125682645601429">"允许应用配置本地蓝牙电视，并允许其查找远程设备且与之配对。"</string>
    <string name="permdesc_bluetoothAdmin" product="default" msgid="8931682159331542137">"允许应用配置本地蓝牙手机，并允许其查找远程设备且与之配对。"</string>
    <string name="permlab_accessWimaxState" msgid="4195907010610205703">"建立或中断 WiMAX 网络连接"</string>
    <string name="permdesc_accessWimaxState" msgid="6360102877261978887">"允许该应用确定是否启用了 WiMAX 以及连接的任何 WiMAX 网络的相关信息。"</string>
    <string name="permlab_changeWimaxState" msgid="340465839241528618">"更改 WiMAX 状态"</string>
    <string name="permdesc_changeWimaxState" product="tablet" msgid="3156456504084201805">"允许该应用建立和断开平板电脑与 WiMAX 网络之间的连接。"</string>
    <string name="permdesc_changeWimaxState" product="tv" msgid="6022307083934827718">"允许应用建立和断开电视与 WiMAX 网络之间的连接。"</string>
    <string name="permdesc_changeWimaxState" product="default" msgid="697025043004923798">"允许该应用建立和断开手机与 WiMAX 网络之间的连接。"</string>
    <string name="permlab_bluetooth" msgid="6127769336339276828">"与蓝牙设备配对"</string>
    <string name="permdesc_bluetooth" product="tablet" msgid="3480722181852438628">"允许该应用查看平板电脑上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permdesc_bluetooth" product="tv" msgid="3974124940101104206">"允许应用查看电视上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permdesc_bluetooth" product="default" msgid="3207106324452312739">"允许该应用查看手机上的蓝牙配置，以及与配对设备建立连接或接受其连接请求。"</string>
    <string name="permlab_nfc" msgid="4423351274757876953">"控制近距离通信"</string>
    <string name="permdesc_nfc" msgid="7120611819401789907">"允许应用与近距离无线通信(NFC)标签、卡和读取器通信。"</string>
    <string name="permlab_disableKeyguard" msgid="3598496301486439258">"停用屏幕锁定"</string>
    <string name="permdesc_disableKeyguard" msgid="6034203065077122992">"允许该应用停用键锁以及任何关联的密码安全措施。例如，让手机在接听来电时停用键锁，在通话结束后重新启用键锁。"</string>
    <string name="permlab_useBiometric" msgid="8837753668509919318">"使用生物特征硬件"</string>
    <string name="permdesc_useBiometric" msgid="8389855232721612926">"允许该应用使用生物特征硬件进行身份验证"</string>
    <string name="permlab_manageFingerprint" msgid="5640858826254575638">"管理指纹硬件"</string>
    <string name="permdesc_manageFingerprint" msgid="178208705828055464">"允许该应用调用方法来添加和删除可用的指纹模板。"</string>
    <string name="permlab_useFingerprint" msgid="3150478619915124905">"使用指纹硬件"</string>
    <string name="permdesc_useFingerprint" msgid="9165097460730684114">"允许该应用使用指纹硬件进行身份验证"</string>
    <string name="fingerprint_acquired_partial" msgid="735082772341716043">"仅检测到部分指纹，请重试。"</string>
    <string name="fingerprint_acquired_insufficient" msgid="4596546021310923214">"无法处理指纹，请重试。"</string>
    <string name="fingerprint_acquired_imager_dirty" msgid="1087209702421076105">"指纹传感器有脏污。请擦拭干净，然后重试。"</string>
    <string name="fingerprint_acquired_too_fast" msgid="6470642383109155969">"手指移动太快，请重试。"</string>
    <string name="fingerprint_acquired_too_slow" msgid="59250885689661653">"手指移动太慢，请重试。"</string>
  <string-array name="fingerprint_acquired_vendor">
  </string-array>
    <string name="fingerprint_not_recognized" msgid="2690661881608146617">"无法识别"</string>
    <string name="fingerprint_authenticated" msgid="5309333983002526448">"已验证指纹"</string>
    <string name="fingerprint_error_hw_not_available" msgid="7955921658939936596">"指纹硬件无法使用。"</string>
    <string name="fingerprint_error_no_space" msgid="1055819001126053318">"无法存储指纹。请移除一个现有的指纹。"</string>
    <string name="fingerprint_error_timeout" msgid="3927186043737732875">"指纹录入操作超时，请重试。"</string>
    <string name="fingerprint_error_canceled" msgid="4402024612660774395">"指纹操作已取消。"</string>
    <string name="fingerprint_error_user_canceled" msgid="7999639584615291494">"用户取消了指纹操作。"</string>
    <string name="fingerprint_error_lockout" msgid="5536934748136933450">"尝试次数过多，请稍后重试。"</string>
    <string name="fingerprint_error_lockout_permanent" msgid="5033251797919508137">"尝试次数过多。指纹传感器已停用。"</string>
    <string name="fingerprint_error_unable_to_process" msgid="6107816084103552441">"请重试。"</string>
    <string name="fingerprint_error_no_fingerprints" msgid="7654382120628334248">"未注册任何指纹。"</string>
    <string name="fingerprint_error_hw_not_present" msgid="5729436878065119329">"此设备没有指纹传感器"</string>
    <string name="fingerprint_name_template" msgid="5870957565512716938">"手指 <xliff:g id="FINGERID">%d</xliff:g>"</string>
  <string-array name="fingerprint_error_vendor">
  </string-array>
    <string name="fingerprint_icon_content_description" msgid="2340202869968465936">"指纹图标"</string>
    <string name="permlab_readSyncSettings" msgid="6201810008230503052">"读取同步设置"</string>
    <string name="permdesc_readSyncSettings" msgid="2706745674569678644">"允许该应用读取某个帐号的同步设置。例如，此权限可确定“联系人”应用是否与某个帐号同步。"</string>
    <string name="permlab_writeSyncSettings" msgid="5408694875793945314">"启用和停用同步"</string>
    <string name="permdesc_writeSyncSettings" msgid="8956262591306369868">"允许该应用修改某个帐号的同步设置。例如，此权限可用于在“联系人”应用与某个帐号之间启用同步。"</string>
    <string name="permlab_readSyncStats" msgid="7396577451360202448">"读取同步统计信息"</string>
    <string name="permdesc_readSyncStats" msgid="1510143761757606156">"允许该应用读取某个帐号的同步统计信息，包括同步活动历史记录和同步数据量。"</string>
    <string name="permlab_sdcardRead" product="nosdcard" msgid="367275095159405468">"读取您的USB存储设备中的内容"</string>
    <string name="permlab_sdcardRead" product="default" msgid="2188156462934977940">"读取您的SD卡中的内容"</string>
    <string name="permdesc_sdcardRead" product="nosdcard" msgid="3446988712598386079">"允许应用读取您USB存储设备中的内容。"</string>
    <string name="permdesc_sdcardRead" product="default" msgid="2607362473654975411">"允许应用读取您SD卡的内容。"</string>
    <string name="permlab_sdcardWrite" product="nosdcard" msgid="8485979062254666748">"修改或删除您的USB存储设备中的内容"</string>
    <string name="permlab_sdcardWrite" product="default" msgid="8805693630050458763">"修改或删除您的SD卡中的内容"</string>
    <string name="permdesc_sdcardWrite" product="nosdcard" msgid="6175406299445710888">"允许应用写入USB存储设备。"</string>
    <string name="permdesc_sdcardWrite" product="default" msgid="4337417790936632090">"允许应用写入SD卡。"</string>
    <string name="permlab_use_sip" msgid="2052499390128979920">"拨打/接听SIP电话"</string>
    <string name="permdesc_use_sip" msgid="2297804849860225257">"允许该应用拨打和接听SIP电话。"</string>
    <string name="permlab_register_sim_subscription" msgid="3166535485877549177">"注册新的电信 SIM 卡连接"</string>
    <string name="permdesc_register_sim_subscription" msgid="2138909035926222911">"允许该应用注册新的电信 SIM 卡连接。"</string>
    <string name="permlab_register_call_provider" msgid="108102120289029841">"注册新的电信网络连接"</string>
    <string name="permdesc_register_call_provider" msgid="7034310263521081388">"允许该应用注册新的电信网络连接。"</string>
    <string name="permlab_connection_manager" msgid="1116193254522105375">"管理电信网络连接"</string>
    <string name="permdesc_connection_manager" msgid="5925480810356483565">"允许该应用管理电信网络连接。"</string>
    <string name="permlab_bind_incall_service" msgid="6773648341975287125">"与通话屏幕互动"</string>
    <string name="permdesc_bind_incall_service" msgid="8343471381323215005">"允许应用控制用户看到通话屏幕的时机和方式。"</string>
    <string name="permlab_bind_connection_service" msgid="3557341439297014940">"与电话服务交互"</string>
    <string name="permdesc_bind_connection_service" msgid="4008754499822478114">"允许该应用与电话服务交互以便接打电话。"</string>
    <string name="permlab_control_incall_experience" msgid="9061024437607777619">"向用户提供通话体验"</string>
    <string name="permdesc_control_incall_experience" msgid="915159066039828124">"允许应用向用户提供通话体验。"</string>
    <string name="permlab_readNetworkUsageHistory" msgid="7862593283611493232">"读取网络使用情况历史记录"</string>
    <string name="permdesc_readNetworkUsageHistory" msgid="7689060749819126472">"允许应用读取特定网络和应用的网络使用情况历史记录。"</string>
    <string name="permlab_manageNetworkPolicy" msgid="2562053592339859990">"管理网络政策"</string>
    <string name="permdesc_manageNetworkPolicy" msgid="7537586771559370668">"允许应用管理网络规范和定义专门针对应用的规则。"</string>
    <string name="permlab_modifyNetworkAccounting" msgid="5088217309088729650">"修改网络使用情况记录方式"</string>
    <string name="permdesc_modifyNetworkAccounting" msgid="5443412866746198123">"允许该应用修改对于各应用的网络使用情况的统计方式。普通应用不应使用此权限。"</string>
    <string name="permlab_accessNotifications" msgid="7673416487873432268">"访问通知"</string>
    <string name="permdesc_accessNotifications" msgid="458457742683431387">"允许该应用检索、检查并清除通知，包括其他应用发布的通知。"</string>
    <string name="permlab_bindNotificationListenerService" msgid="7057764742211656654">"绑定到通知侦听器服务"</string>
    <string name="permdesc_bindNotificationListenerService" msgid="985697918576902986">"允许应用绑定到通知侦听器服务的顶级接口（普通应用绝不需要此权限）。"</string>
    <string name="permlab_bindConditionProviderService" msgid="1180107672332704641">"绑定到条件提供程序服务"</string>
    <string name="permdesc_bindConditionProviderService" msgid="1680513931165058425">"允许应用绑定到条件提供程序服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindDreamService" msgid="4153646965978563462">"绑定到互动屏保服务"</string>
    <string name="permdesc_bindDreamService" msgid="7325825272223347863">"允许应用绑定到互动屏保服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_invokeCarrierSetup" msgid="3699600833975117478">"调用运营商提供的配置应用"</string>
    <string name="permdesc_invokeCarrierSetup" msgid="4159549152529111920">"允许应用调用运营商提供的配置应用。普通应用绝不需要此权限。"</string>
    <string name="permlab_accessNetworkConditions" msgid="8206077447838909516">"监听网络状况的观测信息"</string>
    <string name="permdesc_accessNetworkConditions" msgid="6899102075825272211">"允许应用监听网络状况的观测信息。普通应用绝不需要此权限。"</string>
    <string name="permlab_setInputCalibration" msgid="4902620118878467615">"更改输入设备校准设置"</string>
    <string name="permdesc_setInputCalibration" msgid="4527511047549456929">"允许应用修改触摸屏的校准参数。普通应用绝不需要此权限。"</string>
    <string name="permlab_accessDrmCertificates" msgid="7436886640723203615">"访问DRM证书"</string>
    <string name="permdesc_accessDrmCertificates" msgid="8073288354426159089">"允许应用配置和使用DRM证书。普通应用绝不需要此权限。"</string>
    <string name="permlab_handoverStatus" msgid="7820353257219300883">"接收 Android Beam 的传输状态"</string>
    <string name="permdesc_handoverStatus" msgid="4788144087245714948">"允许此应用接收Android Beam当前传输内容的相关信息"</string>
    <string name="permlab_removeDrmCertificates" msgid="7044888287209892751">"移除DRM证书"</string>
    <string name="permdesc_removeDrmCertificates" msgid="7272999075113400993">"允许应用移除DRM证书。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindCarrierMessagingService" msgid="1490229371796969158">"绑定到运营商消息传递服务"</string>
    <string name="permdesc_bindCarrierMessagingService" msgid="2762882888502113944">"允许应用绑定到运营商消息传递服务的顶级接口。普通应用绝不需要此权限。"</string>
    <string name="permlab_bindCarrierServices" msgid="3233108656245526783">"绑定到运营商服务"</string>
    <string name="permdesc_bindCarrierServices" msgid="1391552602551084192">"允许应用绑定到运营商服务。普通应用绝不需要此权限。"</string>
    <string name="permlab_access_notification_policy" msgid="4247510821662059671">"“勿扰”模式使用权限"</string>
    <string name="permdesc_access_notification_policy" msgid="3296832375218749580">"允许此应用读取和写入“勿扰”模式配置。"</string>
    <string name="policylab_limitPassword" msgid="4497420728857585791">"设置密码规则"</string>
    <string name="policydesc_limitPassword" msgid="2502021457917874968">"控制锁屏密码和 PIN 码所允许的长度和字符。"</string>
    <string name="policylab_watchLogin" msgid="5091404125971980158">"监控屏幕解锁尝试次数"</string>
    <string name="policydesc_watchLogin" product="tablet" msgid="3215729294215070072">"监视在解锁屏幕时输错密码的次数，如果输错次数过多，则锁定平板电脑或清除其所有数据。"</string>
    <string name="policydesc_watchLogin" product="TV" msgid="2707817988309890256">"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定电视或清除电视上的所有数据。"</string>
    <string name="policydesc_watchLogin" product="default" msgid="5712323091846761073">"监视在解锁屏幕时输错密码的次数，如果输错次数过多，则锁定手机或清除其所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="tablet" msgid="4280246270601044505">"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定平板电脑或清空此用户的所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="TV" msgid="3484832653564483250">"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定电视或清空此用户的所有数据。"</string>
    <string name="policydesc_watchLogin_secondaryUser" product="default" msgid="2185480427217127147">"监控在解锁屏幕时输错密码的次数，并在输错次数过多时锁定手机或清空此用户的所有数据。"</string>
    <string name="policylab_resetPassword" msgid="4934707632423915395">"更改锁屏密码"</string>
    <string name="policydesc_resetPassword" msgid="1278323891710619128">"更改锁屏密码。"</string>
    <string name="policylab_forceLock" msgid="2274085384704248431">"锁定屏幕"</string>
    <string name="policydesc_forceLock" msgid="1141797588403827138">"控制屏幕锁定的方式和时间。"</string>
    <string name="policylab_wipeData" msgid="3910545446758639713">"清除所有数据"</string>
    <string name="policydesc_wipeData" product="tablet" msgid="4306184096067756876">"恢复出厂设置时，系统会在不发出警告的情况下清除平板电脑上的数据。"</string>
    <string name="policydesc_wipeData" product="tv" msgid="5816221315214527028">"恢复出厂设置时，不发出警告就直接清除电视上的数据。"</string>
    <string name="policydesc_wipeData" product="default" msgid="5096895604574188391">"恢复出厂设置时，系统会在不发出警告的情况下清除手机上的数据。"</string>
    <string name="policylab_wipeData_secondaryUser" msgid="8362863289455531813">"清空用户数据"</string>
    <string name="policydesc_wipeData_secondaryUser" product="tablet" msgid="6336255514635308054">"清空此用户在这台平板电脑上的数据，而不事先发出警告。"</string>
    <string name="policydesc_wipeData_secondaryUser" product="tv" msgid="2086473496848351810">"清空此用户在这台电视上的数据，而不事先发出警告。"</string>
    <string name="policydesc_wipeData_secondaryUser" product="default" msgid="6787904546711590238">"清空此用户在这部手机上的数据，而不事先发出警告。"</string>
    <string name="policylab_setGlobalProxy" msgid="2784828293747791446">"设置设备全局代理"</string>
    <string name="policydesc_setGlobalProxy" msgid="8459859731153370499">"设置在规范启用时要使用的设备全局代理。只有设备所有者才能设置全局代理。"</string>
    <string name="policylab_expirePassword" msgid="5610055012328825874">"设置锁屏密码的有效期"</string>
    <string name="policydesc_expirePassword" msgid="5367525762204416046">"调整系统强制用户更改锁屏密码、PIN 码或解锁图案的频率。"</string>
    <string name="policylab_encryptedStorage" msgid="8901326199909132915">"设置存储设备加密"</string>
    <string name="policydesc_encryptedStorage" msgid="2637732115325316992">"要求对存储的应用数据进行加密。"</string>
    <string name="policylab_disableCamera" msgid="6395301023152297826">"停用相机"</string>
    <string name="policydesc_disableCamera" msgid="2306349042834754597">"禁止使用所有设备摄像头。"</string>
    <string name="policylab_disableKeyguardFeatures" msgid="8552277871075367771">"停用屏幕锁定的部分功能"</string>
    <string name="policydesc_disableKeyguardFeatures" msgid="2044755691354158439">"禁止使用屏幕锁定的部分功能。"</string>
  <string-array name="phoneTypes">
    <item msgid="8901098336658710359">"住宅"</item>
    <item msgid="869923650527136615">"手机"</item>
    <item msgid="7897544654242874543">"单位"</item>
    <item msgid="1103601433382158155">"单位传真"</item>
    <item msgid="1735177144948329370">"住宅传真"</item>
    <item msgid="603878674477207394">"寻呼机"</item>
    <item msgid="1650824275177931637">"其他"</item>
    <item msgid="9192514806975898961">"自定义"</item>
  </string-array>
  <string-array name="emailAddressTypes">
    <item msgid="8073994352956129127">"个人"</item>
    <item msgid="7084237356602625604">"工作"</item>
    <item msgid="1112044410659011023">"其他"</item>
    <item msgid="2374913952870110618">"自定义"</item>
  </string-array>
  <string-array name="postalAddressTypes">
    <item msgid="6880257626740047286">"住宅"</item>
    <item msgid="5629153956045109251">"单位"</item>
    <item msgid="4966604264500343469">"其他"</item>
    <item msgid="4932682847595299369">"自定义"</item>
  </string-array>
  <string-array name="imAddressTypes">
    <item msgid="1738585194601476694">"住宅"</item>
    <item msgid="1359644565647383708">"工作"</item>
    <item msgid="7868549401053615677">"其他"</item>
    <item msgid="3145118944639869809">"自定义"</item>
  </string-array>
  <string-array name="organizationTypes">
    <item msgid="7546335612189115615">"单位"</item>
    <item msgid="4378074129049520373">"其他"</item>
    <item msgid="3455047468583965104">"自定义"</item>
  </string-array>
  <string-array name="imProtocols">
    <item msgid="8595261363518459565">"AIM"</item>
    <item msgid="7390473628275490700">"Windows Live"</item>
    <item msgid="7882877134931458217">"中国雅虎"</item>
    <item msgid="5035376313200585242">"Skype"</item>
    <item msgid="7532363178459444943">"QQ"</item>
    <item msgid="3713441034299660749">"Google Talk"</item>
    <item msgid="2506857312718630823">"ICQ"</item>
    <item msgid="1648797903785279353">"Jabber"</item>
  </string-array>
    <string name="phoneTypeCustom" msgid="1644738059053355820">"自定义"</string>
    <string name="phoneTypeHome" msgid="2570923463033985887">"住宅"</string>
    <string name="phoneTypeMobile" msgid="6501463557754751037">"手机"</string>
    <string name="phoneTypeWork" msgid="8863939667059911633">"单位"</string>
    <string name="phoneTypeFaxWork" msgid="3517792160008890912">"单位传真"</string>
    <string name="phoneTypeFaxHome" msgid="2067265972322971467">"住宅传真"</string>
    <string name="phoneTypePager" msgid="7582359955394921732">"寻呼机"</string>
    <string name="phoneTypeOther" msgid="1544425847868765990">"其他"</string>
    <string name="phoneTypeCallback" msgid="2712175203065678206">"回拨号码"</string>
    <string name="phoneTypeCar" msgid="8738360689616716982">"车载电话"</string>
    <string name="phoneTypeCompanyMain" msgid="540434356461478916">"公司总机"</string>
    <string name="phoneTypeIsdn" msgid="8022453193171370337">"ISDN"</string>
    <string name="phoneTypeMain" msgid="6766137010628326916">"总机"</string>
    <string name="phoneTypeOtherFax" msgid="8587657145072446565">"其他传真"</string>
    <string name="phoneTypeRadio" msgid="4093738079908667513">"无线装置"</string>
    <string name="phoneTypeTelex" msgid="3367879952476250512">"电报"</string>
    <string name="phoneTypeTtyTdd" msgid="8606514378585000044">"TTY TDD"</string>
    <string name="phoneTypeWorkMobile" msgid="1311426989184065709">"单位手机"</string>
    <string name="phoneTypeWorkPager" msgid="649938731231157056">"单位寻呼机"</string>
    <string name="phoneTypeAssistant" msgid="5596772636128562884">"助理"</string>
    <string name="phoneTypeMms" msgid="7254492275502768992">"彩信"</string>
    <string name="eventTypeCustom" msgid="7837586198458073404">"自定义"</string>
    <string name="eventTypeBirthday" msgid="2813379844211390740">"生日"</string>
    <string name="eventTypeAnniversary" msgid="3876779744518284000">"周年纪念日"</string>
    <string name="eventTypeOther" msgid="7388178939010143077">"其他"</string>
    <string name="emailTypeCustom" msgid="8525960257804213846">"自定义"</string>
    <string name="emailTypeHome" msgid="449227236140433919">"个人"</string>
    <string name="emailTypeWork" msgid="3548058059601149973">"工作"</string>
    <string name="emailTypeOther" msgid="2923008695272639549">"其他"</string>
    <string name="emailTypeMobile" msgid="119919005321166205">"手机"</string>
    <string name="postalTypeCustom" msgid="8903206903060479902">"自定义"</string>
    <string name="postalTypeHome" msgid="8165756977184483097">"住宅"</string>
    <string name="postalTypeWork" msgid="5268172772387694495">"单位"</string>
    <string name="postalTypeOther" msgid="2726111966623584341">"其他"</string>
    <string name="imTypeCustom" msgid="2074028755527826046">"自定义"</string>
    <string name="imTypeHome" msgid="6241181032954263892">"住宅"</string>
    <string name="imTypeWork" msgid="1371489290242433090">"工作"</string>
    <string name="imTypeOther" msgid="5377007495735915478">"其他"</string>
    <string name="imProtocolCustom" msgid="6919453836618749992">"自定义"</string>
    <string name="imProtocolAim" msgid="7050360612368383417">"AIM"</string>
    <string name="imProtocolMsn" msgid="144556545420769442">"Windows Live"</string>
    <string name="imProtocolYahoo" msgid="8271439408469021273">"雅虎"</string>
    <string name="imProtocolSkype" msgid="9019296744622832951">"Skype"</string>
    <string name="imProtocolQq" msgid="8887484379494111884">"QQ"</string>
    <string name="imProtocolGoogleTalk" msgid="493902321140277304">"环聊"</string>
    <string name="imProtocolIcq" msgid="1574870433606517315">"ICQ"</string>
    <string name="imProtocolJabber" msgid="2279917630875771722">"Jabber"</string>
    <string name="imProtocolNetMeeting" msgid="8287625655986827971">"NetMeeting"</string>
    <string name="orgTypeWork" msgid="29268870505363872">"公司"</string>
    <string name="orgTypeOther" msgid="3951781131570124082">"其他"</string>
    <string name="orgTypeCustom" msgid="225523415372088322">"自定义"</string>
    <string name="relationTypeCustom" msgid="3542403679827297300">"自定义"</string>
    <string name="relationTypeAssistant" msgid="6274334825195379076">"助理"</string>
    <string name="relationTypeBrother" msgid="8757913506784067713">"兄弟"</string>
    <string name="relationTypeChild" msgid="1890746277276881626">"子女"</string>
    <string name="relationTypeDomesticPartner" msgid="6904807112121122133">"同居伴侣"</string>
    <string name="relationTypeFather" msgid="5228034687082050725">"父亲"</string>
    <string name="relationTypeFriend" msgid="7313106762483391262">"朋友"</string>
    <string name="relationTypeManager" msgid="6365677861610137895">"经理"</string>
    <string name="relationTypeMother" msgid="4578571352962758304">"母亲"</string>
    <string name="relationTypeParent" msgid="4755635567562925226">"父母"</string>
    <string name="relationTypePartner" msgid="7266490285120262781">"合作伙伴"</string>
    <string name="relationTypeReferredBy" msgid="101573059844135524">"介绍人"</string>
    <string name="relationTypeRelative" msgid="1799819930085610271">"亲属"</string>
    <string name="relationTypeSister" msgid="1735983554479076481">"姐妹"</string>
    <string name="relationTypeSpouse" msgid="394136939428698117">"配偶"</string>
    <string name="sipAddressTypeCustom" msgid="2473580593111590945">"自定义"</string>
    <string name="sipAddressTypeHome" msgid="6093598181069359295">"住宅"</string>
    <string name="sipAddressTypeWork" msgid="6920725730797099047">"单位"</string>
    <string name="sipAddressTypeOther" msgid="4408436162950119849">"其他"</string>
    <string name="quick_contacts_not_available" msgid="746098007828579688">"找不到可用来查看此联系人的应用。"</string>
    <string name="keyguard_password_enter_pin_code" msgid="3037685796058495017">"输入PIN码"</string>
    <string name="keyguard_password_enter_puk_code" msgid="4800725266925845333">"请输入PUK码和新的PIN码"</string>
    <string name="keyguard_password_enter_puk_prompt" msgid="1341112146710087048">"PUK码"</string>
    <string name="keyguard_password_enter_pin_prompt" msgid="8027680321614196258">"新的PIN码"</string>
    <string name="keyguard_password_entry_touch_hint" msgid="2644215452200037944"><font size="17">"点按即可输入密码"</font></string>
    <string name="keyguard_password_enter_password_code" msgid="1054721668279049780">"输入密码以解锁"</string>
    <string name="keyguard_password_enter_pin_password_code" msgid="6391755146112503443">"输入PIN码进行解锁"</string>
    <string name="keyguard_password_wrong_pin_code" msgid="2422225591006134936">"PIN码有误。"</string>
    <string name="keyguard_label_text" msgid="861796461028298424">"要解锁，请先按 MENU 再按 0。"</string>
    <string name="emergency_call_dialog_number_for_display" msgid="696192103195090970">"急救或报警电话"</string>
    <string name="lockscreen_carrier_default" msgid="6169005837238288522">"没有服务"</string>
    <string name="lockscreen_screen_locked" msgid="7288443074806832904">"屏幕已锁定。"</string>
    <string name="lockscreen_instructions_when_pattern_enabled" msgid="46154051614126049">"按 Menu 解锁或进行紧急呼救。"</string>
    <string name="lockscreen_instructions_when_pattern_disabled" msgid="686260028797158364">"按 MENU 解锁。"</string>
    <string name="lockscreen_pattern_instructions" msgid="7478703254964810302">"绘制解锁图案"</string>
    <string name="lockscreen_emergency_call" msgid="5298642613417801888">"紧急呼救"</string>
    <string name="lockscreen_return_to_call" msgid="5244259785500040021">"返回通话"</string>
    <string name="lockscreen_pattern_correct" msgid="9039008650362261237">"正确！"</string>
    <string name="lockscreen_pattern_wrong" msgid="4317955014948108794">"重试"</string>
    <string name="lockscreen_password_wrong" msgid="5737815393253165301">"重试"</string>
    <string name="lockscreen_storage_locked" msgid="9167551160010625200">"解锁即可使用所有功能和数据"</string>
    <string name="faceunlock_multiple_failures" msgid="754137583022792429">"已超过“人脸解锁”尝试次数上限"</string>
    <string name="lockscreen_missing_sim_message_short" msgid="5099439277819215399">"没有 SIM 卡"</string>
    <string name="lockscreen_missing_sim_message" product="tablet" msgid="151659196095791474">"平板电脑中没有SIM卡。"</string>
    <string name="lockscreen_missing_sim_message" product="tv" msgid="1943633865476989599">"电视中没有 SIM 卡。"</string>
    <string name="lockscreen_missing_sim_message" product="default" msgid="2186920585695169078">"手机中无SIM卡"</string>
    <string name="lockscreen_missing_sim_instructions" msgid="5372787138023272615">"请插入SIM卡"</string>
    <string name="lockscreen_missing_sim_instructions_long" msgid="3526573099019319472">"SIM卡缺失或无法读取。请插入SIM卡。"</string>
    <string name="lockscreen_permanent_disabled_sim_message_short" msgid="5096149665138916184">"SIM卡无法使用。"</string>
    <string name="lockscreen_permanent_disabled_sim_instructions" msgid="910904643433151371">"您的SIM卡已永久停用。\n请与您的无线服务提供商联系，以便重新获取一张SIM卡。"</string>
    <string name="lockscreen_transport_prev_description" msgid="6300840251218161534">"上一曲"</string>
    <string name="lockscreen_transport_next_description" msgid="573285210424377338">"下一曲"</string>
    <string name="lockscreen_transport_pause_description" msgid="3980308465056173363">"暂停"</string>
    <string name="lockscreen_transport_play_description" msgid="1901258823643886401">"播放"</string>
    <string name="lockscreen_transport_stop_description" msgid="5907083260651210034">"停止"</string>
    <string name="lockscreen_transport_rew_description" msgid="6944412838651990410">"快退"</string>
    <string name="lockscreen_transport_ffw_description" msgid="42987149870928985">"快进"</string>
    <string name="emergency_calls_only" msgid="6733978304386365407">"只能拨打紧急呼救电话"</string>
    <string name="lockscreen_network_locked_message" msgid="143389224986028501">"网络已锁定"</string>
    <string name="lockscreen_sim_puk_locked_message" msgid="7441797339976230">"SIM 卡已用 PUK 码锁定。"</string>
    <string name="lockscreen_sim_puk_locked_instructions" msgid="8127916255245181063">"请参阅《用户指南》或与客服人员联系。"</string>
    <string name="lockscreen_sim_locked_message" msgid="8066660129206001039">"SIM 卡已被锁定。"</string>
    <string name="lockscreen_sim_unlock_progress_dialog_message" msgid="595323214052881264">"正在解锁SIM卡..."</string>
    <string name="lockscreen_too_many_failed_attempts_dialog_message" msgid="6481623830344107222">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_too_many_failed_password_attempts_dialog_message" msgid="2725973286239344555">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次输错密码。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_too_many_failed_pin_attempts_dialog_message" msgid="6216672706545696955">"您已经<xliff:g id="NUMBER_0">%1$d</xliff:g>次输错了PIN码。\n\n请在<xliff:g id="NUMBER_1">%2$d</xliff:g>秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="tablet" msgid="9191611984625460820">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁平板电脑。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="tv" msgid="5316664559603394684">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁电视。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_glogin" product="default" msgid="2590227559763762751">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的 Google 登录信息解锁手机。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="tablet" msgid="6128106399745755604">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁平板电脑。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，平板电脑将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="tv" msgid="950408382418270260">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁电视。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，电视将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_almost_at_wipe" product="default" msgid="8603565142156826565">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁手机。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，手机将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="tablet" msgid="280873516493934365">"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁平板电脑。平板电脑现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="tv" msgid="3195755534096192191">"您已经 <xliff:g id="NUMBER">%d</xliff:g> 次错误地尝试解锁电视。电视现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_failed_attempts_now_wiping" product="default" msgid="3025504721764922246">"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁手机。手机现在将恢复为出厂默认设置。"</string>
    <string name="lockscreen_too_many_failed_attempts_countdown" msgid="6251480343394389665">"<xliff:g id="NUMBER">%d</xliff:g>秒后重试。"</string>
    <string name="lockscreen_forgot_pattern_button_text" msgid="2626999449610695930">"忘记了图案？"</string>
    <string name="lockscreen_glogin_forgot_pattern" msgid="2588521501166032747">"帐号解锁"</string>
    <string name="lockscreen_glogin_too_many_attempts" msgid="2751368605287288808">"图案尝试次数过多"</string>
    <string name="lockscreen_glogin_instructions" msgid="3931816256100707784">"要解除锁定，请使用您的Google帐号登录。"</string>
    <string name="lockscreen_glogin_username_hint" msgid="8846881424106484447">"用户名（电子邮件）"</string>
    <string name="lockscreen_glogin_password_hint" msgid="5958028383954738528">"密码"</string>
    <string name="lockscreen_glogin_submit_button" msgid="7130893694795786300">"登录"</string>
    <string name="lockscreen_glogin_invalid_input" msgid="1364051473347485908">"用户名或密码无效。"</string>
    <string name="lockscreen_glogin_account_recovery_hint" msgid="1696924763690379073">"忘记了用户名或密码？\n请访问"<b>"google.com/accounts/recovery"</b>"。"</string>
    <string name="lockscreen_glogin_checking_password" msgid="7114627351286933867">"正在检查..."</string>
    <string name="lockscreen_unlock_label" msgid="737440483220667054">"解锁"</string>
    <string name="lockscreen_sound_on_label" msgid="9068877576513425970">"打开声音"</string>
    <string name="lockscreen_sound_off_label" msgid="996822825154319026">"关闭声音"</string>
    <string name="lockscreen_access_pattern_start" msgid="3941045502933142847">"开始绘制图案"</string>
    <string name="lockscreen_access_pattern_cleared" msgid="5583479721001639579">"图案已清除"</string>
    <string name="lockscreen_access_pattern_cell_added" msgid="6756031208359292487">"已添加单元格"</string>
    <string name="lockscreen_access_pattern_cell_added_verbose" msgid="7264580781744026939">"已添加圆点 <xliff:g id="CELL_INDEX">%1$s</xliff:g>"</string>
    <string name="lockscreen_access_pattern_detected" msgid="4988730895554057058">"图案绘制完成"</string>
    <string name="lockscreen_access_pattern_area" msgid="400813207572953209">"图案区域。"</string>
    <string name="keyguard_accessibility_widget_changed" msgid="5678624624681400191">"%1$s。%3$d的微件%2$d。"</string>
    <string name="keyguard_accessibility_add_widget" msgid="8273277058724924654">"添加微件。"</string>
    <string name="keyguard_accessibility_widget_empty_slot" msgid="1281505703307930757">"空白"</string>
    <string name="keyguard_accessibility_unlock_area_expanded" msgid="2278106022311170299">"已展开解锁区域。"</string>
    <string name="keyguard_accessibility_unlock_area_collapsed" msgid="6366992066936076396">"已收起解锁区域。"</string>
    <string name="keyguard_accessibility_widget" msgid="6527131039741808240">"<xliff:g id="WIDGET_INDEX">%1$s</xliff:g>微件。"</string>
    <string name="keyguard_accessibility_user_selector" msgid="1226798370913698896">"用户选择器"</string>
    <string name="keyguard_accessibility_status" msgid="8008264603935930611">"状态"</string>
    <string name="keyguard_accessibility_camera" msgid="8904231194181114603">"相机"</string>
    <string name="keygaurd_accessibility_media_controls" msgid="262209654292161806">"媒体控制"</string>
    <string name="keyguard_accessibility_widget_reorder_start" msgid="8736853615588828197">"已开始将微件重新排序。"</string>
    <string name="keyguard_accessibility_widget_reorder_end" msgid="7170190950870468320">"已完成微件重新排序。"</string>
    <string name="keyguard_accessibility_widget_deleted" msgid="4426204263929224434">"已删除微件<xliff:g id="WIDGET_INDEX">%1$s</xliff:g>。"</string>
    <string name="keyguard_accessibility_expand_lock_area" msgid="519859720934178024">"展开解锁区域。"</string>
    <string name="keyguard_accessibility_slide_unlock" msgid="2959928478764697254">"滑动解锁。"</string>
    <string name="keyguard_accessibility_pattern_unlock" msgid="1490840706075246612">"图案解锁。"</string>
    <string name="keyguard_accessibility_face_unlock" msgid="4817282543351718535">"人脸解锁。"</string>
    <string name="keyguard_accessibility_pin_unlock" msgid="2469687111784035046">"PIN码解锁。"</string>
    <string name="keyguard_accessibility_sim_pin_unlock" msgid="9149698847116962307">"SIM 卡 PIN 码解锁。"</string>
    <string name="keyguard_accessibility_sim_puk_unlock" msgid="9106899279724723341">"SIM 卡 PUK 码解锁。"</string>
    <string name="keyguard_accessibility_password_unlock" msgid="7675777623912155089">"密码解锁。"</string>
    <string name="keyguard_accessibility_pattern_area" msgid="7679891324509597904">"图案区域。"</string>
    <string name="keyguard_accessibility_slide_area" msgid="6736064494019979544">"滑动区域。"</string>
    <string name="password_keyboard_label_symbol_key" msgid="992280756256536042">"?123"</string>
    <string name="password_keyboard_label_alpha_key" msgid="8001096175167485649">"ABC"</string>
    <string name="password_keyboard_label_alt_key" msgid="1284820942620288678">"ALT"</string>
    <string name="granularity_label_character" msgid="7336470535385009523">"字符"</string>
    <string name="granularity_label_word" msgid="7075570328374918660">"字"</string>
    <string name="granularity_label_link" msgid="5815508880782488267">"链接"</string>
    <string name="granularity_label_line" msgid="5764267235026120888">"行"</string>
    <string name="factorytest_failed" msgid="5410270329114212041">"出厂测试失败"</string>
    <string name="factorytest_not_system" msgid="4435201656767276723">"只有安装在/system/app中的软件包支持FACTORY_TEST操作。"</string>
    <string name="factorytest_no_action" msgid="872991874799998561">"找不到提供FACTORY_TEST操作的软件包。"</string>
    <string name="factorytest_reboot" msgid="6320168203050791643">"重新启动"</string>
    <string name="js_dialog_title" msgid="1987483977834603872">"网址为“<xliff:g id="TITLE">%s</xliff:g>”的网页显示："</string>
    <string name="js_dialog_title_default" msgid="6961903213729667573">"JavaScript"</string>
    <string name="js_dialog_before_unload_title" msgid="2619376555525116593">"确认离开"</string>
    <string name="js_dialog_before_unload_positive_button" msgid="3112752010600484130">"离开此页"</string>
    <string name="js_dialog_before_unload_negative_button" msgid="5614861293026099715">"留在此页"</string>
    <string name="js_dialog_before_unload" msgid="3468816357095378590">"<xliff:g id="MESSAGE">%s</xliff:g>\n\n您确定要离开此页面吗？"</string>
    <string name="save_password_label" msgid="6860261758665825069">"确认"</string>
    <string name="double_tap_toast" msgid="4595046515400268881">"提示：点按两次可放大或缩小。"</string>
    <string name="autofill_this_form" msgid="4616758841157816676">"自动填充"</string>
    <string name="setup_autofill" msgid="7103495070180590814">"设置自动填充"</string>
    <string name="autofill_window_title" msgid="4107745526909284887">"<xliff:g id="SERVICENAME">%1$s</xliff:g>的自动填充功能"</string>
    <string name="autofill_address_name_separator" msgid="6350145154779706772">" "</string>
    <string name="autofill_address_summary_name_format" msgid="3268041054899214945">"$1$2$3"</string>
    <string name="autofill_address_summary_separator" msgid="7483307893170324129">"， "</string>
    <string name="autofill_address_summary_format" msgid="4874459455786827344">"$1$2$3"</string>
    <string name="autofill_province" msgid="2231806553863422300">"省/直辖市/自治区"</string>
    <string name="autofill_postal_code" msgid="4696430407689377108">"邮编"</string>
    <string name="autofill_state" msgid="6988894195520044613">"州"</string>
    <string name="autofill_zip_code" msgid="8697544592627322946">"邮编"</string>
    <string name="autofill_county" msgid="237073771020362891">"郡"</string>
    <string name="autofill_island" msgid="4020100875984667025">"岛"</string>
    <string name="autofill_district" msgid="8400735073392267672">"地区"</string>
    <string name="autofill_department" msgid="5343279462564453309">"省"</string>
    <string name="autofill_prefecture" msgid="2028499485065800419">"县/府/都/道"</string>
    <string name="autofill_parish" msgid="8202206105468820057">"行政区"</string>
    <string name="autofill_area" msgid="3547409050889952423">"区域"</string>
    <string name="autofill_emirate" msgid="2893880978835698818">"酋长国"</string>
    <string name="permlab_readHistoryBookmarks" msgid="3775265775405106983">"读取您的网络书签和历史记录"</string>
    <string name="permdesc_readHistoryBookmarks" msgid="8462378226600439658">"允许该应用读取浏览器访问过的所有网址记录以及浏览器的所有书签。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permlab_writeHistoryBookmarks" msgid="3714785165273314490">"写入网络书签和历史记录"</string>
    <string name="permdesc_writeHistoryBookmarks" product="tablet" msgid="6825527469145760922">"允许该应用修改您平板电脑上存储的浏览器历史记录或浏览器书签。此权限可让该应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permdesc_writeHistoryBookmarks" product="tv" msgid="7007393823197766548">"允许应用修改您的电视上存储的浏览器历史记录或书签。此权限可让应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permdesc_writeHistoryBookmarks" product="default" msgid="8497389531014185509">"允许该应用修改您手机上存储的浏览器历史记录或浏览器书签。此权限可让该应用清除或修改浏览器数据。请注意：此权限可能不适用于第三方浏览器或具备网页浏览功能的其他应用。"</string>
    <string name="permlab_setAlarm" msgid="1379294556362091814">"设置闹钟"</string>
    <string name="permdesc_setAlarm" msgid="316392039157473848">"允许应用在已安装的闹钟应用中设置闹钟。有些闹钟应用可能无法实现此功能。"</string>
    <string name="permlab_addVoicemail" msgid="5525660026090959044">"添加语音邮件"</string>
    <string name="permdesc_addVoicemail" msgid="6604508651428252437">"允许应用在您的语音信箱中留言。"</string>
    <string name="permlab_writeGeolocationPermissions" msgid="5962224158955273932">"修改“浏览器”地理位置的权限"</string>
    <string name="permdesc_writeGeolocationPermissions" msgid="1083743234522638747">"允许应用修改“浏览器”的地理位置权限。恶意应用可能借此向任意网站发送位置信息。"</string>
    <string name="save_password_message" msgid="767344687139195790">"是否希望浏览器记住此密码？"</string>
    <string name="save_password_notnow" msgid="6389675316706699758">"暂不保存"</string>
    <string name="save_password_remember" msgid="6491879678996749466">"记住"</string>
    <string name="save_password_never" msgid="8274330296785855105">"永不"</string>
    <string name="open_permission_deny" msgid="7374036708316629800">"您无权打开此网页。"</string>
    <string name="text_copied" msgid="4985729524670131385">"文本已复制到剪贴板。"</string>
    <string name="more_item_label" msgid="4650918923083320495">"更多"</string>
    <string name="prepend_shortcut_label" msgid="2572214461676015642">"MENU+"</string>
    <string name="menu_meta_shortcut_label" msgid="4647153495550313570">"Meta+"</string>
    <string name="menu_ctrl_shortcut_label" msgid="3917070091228880941">"Ctrl+"</string>
    <string name="menu_alt_shortcut_label" msgid="6249849492641218944">"Alt+"</string>
    <string name="menu_shift_shortcut_label" msgid="6773890288720306380">"Shift+"</string>
    <string name="menu_sym_shortcut_label" msgid="4019695553731017933">"Sym+"</string>
    <string name="menu_function_shortcut_label" msgid="1984053777418162618">"Fn+"</string>
    <string name="menu_space_shortcut_label" msgid="2410328639272162537">"空格"</string>
    <string name="menu_enter_shortcut_label" msgid="2743362785111309668">"Enter 键"</string>
    <string name="menu_delete_shortcut_label" msgid="3658178007202748164">"删除"</string>
    <string name="search_go" msgid="8298016669822141719">"搜索"</string>
    <string name="search_hint" msgid="1733947260773056054">"搜索…"</string>
    <string name="searchview_description_search" msgid="6749826639098512120">"搜索"</string>
    <string name="searchview_description_query" msgid="5911778593125355124">"搜索查询"</string>
    <string name="searchview_description_clear" msgid="1330281990951833033">"清除查询"</string>
    <string name="searchview_description_submit" msgid="2688450133297983542">"提交查询"</string>
    <string name="searchview_description_voice" msgid="2453203695674994440">"语音搜索"</string>
    <string name="enable_explore_by_touch_warning_title" msgid="7460694070309730149">"是否启用“触摸浏览”？"</string>
    <string name="enable_explore_by_touch_warning_message" product="tablet" msgid="8655887539089910577">"<xliff:g id="ACCESSIBILITY_SERVICE_NAME">%1$s</xliff:g>想要启用“触摸浏览”。“触摸浏览”启用后，您可以听到或看到所触摸内容的说明，还可以通过手势操作与手机互动。"</string>
    <string name="enable_explore_by_touch_warning_message" product="default" msgid="2708199672852373195">"<xliff:g id="ACCESSIBILITY_SERVICE_NAME">%1$s</xliff:g>想要启用“触摸浏览”。“触摸浏览”启用后，您可以听到或看到所触摸内容的说明，还可以通过手势操作与手机互动。"</string>
    <string name="oneMonthDurationPast" msgid="7396384508953779925">"1 个月前"</string>
    <string name="beforeOneMonthDurationPast" msgid="909134546836499826">"1 个月前"</string>
    <plurals name="last_num_days" formatted="false" msgid="5104533550723932025">
      <item quantity="other">过去 <xliff:g id="COUNT_1">%d</xliff:g> 天</item>
      <item quantity="one">过去 <xliff:g id="COUNT_0">%d</xliff:g> 天</item>
    </plurals>
    <string name="last_month" msgid="3959346739979055432">"上个月"</string>
    <string name="older" msgid="5211975022815554840">"往前"</string>
    <string name="preposition_for_date" msgid="9093949757757445117">"<xliff:g id="DATE">%s</xliff:g>"</string>
    <string name="preposition_for_time" msgid="5506831244263083793">"<xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="preposition_for_year" msgid="5040395640711867177">"年份：<xliff:g id="YEAR">%s</xliff:g>"</string>
    <string name="day" msgid="8144195776058119424">"天"</string>
    <string name="days" msgid="4774547661021344602">"天"</string>
    <string name="hour" msgid="2126771916426189481">"点"</string>
    <string name="hours" msgid="894424005266852993">"小时"</string>
    <string name="minute" msgid="9148878657703769868">"分钟"</string>
    <string name="minutes" msgid="5646001005827034509">"分钟"</string>
    <string name="second" msgid="3184235808021478">"秒"</string>
    <string name="seconds" msgid="3161515347216589235">"秒"</string>
    <string name="week" msgid="5617961537173061583">"周"</string>
    <string name="weeks" msgid="6509623834583944518">"周"</string>
    <string name="year" msgid="4001118221013892076">"年"</string>
    <string name="years" msgid="6881577717993213522">"年"</string>
    <string name="now_string_shortest" msgid="8912796667087856402">"现在"</string>
    <plurals name="duration_minutes_shortest" formatted="false" msgid="3957499975064245495">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟</item>
    </plurals>
    <plurals name="duration_hours_shortest" formatted="false" msgid="3552182110578602356">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时</item>
    </plurals>
    <plurals name="duration_days_shortest" formatted="false" msgid="5213655532597081640">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天</item>
    </plurals>
    <plurals name="duration_years_shortest" formatted="false" msgid="7848711145196397042">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年</item>
    </plurals>
    <plurals name="duration_minutes_shortest_future" formatted="false" msgid="3277614521231489951">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟后</item>
    </plurals>
    <plurals name="duration_hours_shortest_future" formatted="false" msgid="2152452368397489370">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时后</item>
    </plurals>
    <plurals name="duration_days_shortest_future" formatted="false" msgid="8088331502820295701">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天后</item>
    </plurals>
    <plurals name="duration_years_shortest_future" formatted="false" msgid="2317006667145250301">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年后</item>
    </plurals>
    <plurals name="duration_minutes_relative" formatted="false" msgid="3178131706192980192">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟前</item>
    </plurals>
    <plurals name="duration_hours_relative" formatted="false" msgid="676894109982008411">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时前</item>
    </plurals>
    <plurals name="duration_days_relative" formatted="false" msgid="2203515825765397130">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天前</item>
    </plurals>
    <plurals name="duration_years_relative" formatted="false" msgid="4820062134188885734">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年前</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年前</item>
    </plurals>
    <plurals name="duration_minutes_relative_future" formatted="false" msgid="4655043589817680966">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 分钟后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 分钟后</item>
    </plurals>
    <plurals name="duration_hours_relative_future" formatted="false" msgid="8084579714205223891">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 小时后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 小时后</item>
    </plurals>
    <plurals name="duration_days_relative_future" formatted="false" msgid="333215369363433992">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 天后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 天后</item>
    </plurals>
    <plurals name="duration_years_relative_future" formatted="false" msgid="8644862986413104011">
      <item quantity="other"><xliff:g id="COUNT_1">%d</xliff:g> 年后</item>
      <item quantity="one"><xliff:g id="COUNT_0">%d</xliff:g> 年后</item>
    </plurals>
    <string name="VideoView_error_title" msgid="3534509135438353077">"视频问题"</string>
    <string name="VideoView_error_text_invalid_progressive_playback" msgid="3186670335938670444">"抱歉，该视频不适合在此设备上播放。"</string>
    <string name="VideoView_error_text_unknown" msgid="3450439155187810085">"无法播放此视频。"</string>
    <string name="VideoView_error_button" msgid="2822238215100679592">"确定"</string>
    <string name="relative_time" msgid="1818557177829411417">"<xliff:g id="DATE">%1$s</xliff:g>，<xliff:g id="TIME">%2$s</xliff:g>"</string>
    <string name="noon" msgid="7245353528818587908">"中午"</string>
    <string name="Noon" msgid="3342127745230013127">"中午"</string>
    <string name="midnight" msgid="7166259508850457595">"午夜"</string>
    <string name="Midnight" msgid="5630806906897892201">"午夜"</string>
    <string name="elapsed_time_short_format_mm_ss" msgid="4431555943828711473">"<xliff:g id="MINUTES">%1$02d</xliff:g>:<xliff:g id="SECONDS">%2$02d</xliff:g>"</string>
    <string name="elapsed_time_short_format_h_mm_ss" msgid="1846071997616654124">"<xliff:g id="HOURS">%1$d</xliff:g>:<xliff:g id="MINUTES">%2$02d</xliff:g>:<xliff:g id="SECONDS">%3$02d</xliff:g>"</string>
    <string name="selectAll" msgid="6876518925844129331">"全选"</string>
    <string name="cut" msgid="3092569408438626261">"剪切"</string>
    <string name="copy" msgid="2681946229533511987">"复制"</string>
    <string name="failed_to_copy_to_clipboard" msgid="1833662432489814471">"无法复制到剪贴板"</string>
    <string name="paste" msgid="5629880836805036433">"粘贴"</string>
    <string name="paste_as_plain_text" msgid="5427792741908010675">"以纯文本形式粘贴"</string>
    <string name="replace" msgid="5781686059063148930">"替换..."</string>
    <string name="delete" msgid="6098684844021697789">"删除"</string>
    <string name="copyUrl" msgid="2538211579596067402">"复制网址"</string>
    <string name="selectTextMode" msgid="1018691815143165326">"选择文字"</string>
    <string name="undo" msgid="7905788502491742328">"撤消"</string>
    <string name="redo" msgid="7759464876566803888">"重做"</string>
    <string name="autofill" msgid="3035779615680565188">"自动填充"</string>
    <string name="textSelectionCABTitle" msgid="5236850394370820357">"文字选择"</string>
    <string name="addToDictionary" msgid="4352161534510057874">"添加到字典"</string>
    <string name="deleteText" msgid="6979668428458199034">"删除"</string>
    <string name="inputMethod" msgid="1653630062304567879">"输入法"</string>
    <string name="editTextMenuTitle" msgid="4909135564941815494">"文字操作"</string>
    <string name="email" msgid="4560673117055050403">"电子邮件"</string>
    <string name="email_desc" msgid="3638665569546416795">"将电子邮件发送至所选地址"</string>
    <string name="dial" msgid="1253998302767701559">"拨打电话"</string>
    <string name="dial_desc" msgid="6573723404985517250">"拨打所选电话号码"</string>
    <string name="map" msgid="5441053548030107189">"地图"</string>
    <string name="map_desc" msgid="1836995341943772348">"找到所选地址"</string>
    <string name="browse" msgid="1245903488306147205">"打开"</string>
    <string name="browse_desc" msgid="8220976549618935044">"打开所选网址"</string>
    <string name="sms" msgid="4560537514610063430">"发短信"</string>
    <string name="sms_desc" msgid="7526588350969638809">"将短信发送至所选电话号码"</string>
    <string name="add_contact" msgid="7867066569670597203">"添加"</string>
    <string name="add_contact_desc" msgid="4830217847004590345">"添加到通讯录"</string>
    <string name="view_calendar" msgid="979609872939597838">"查看"</string>
    <string name="view_calendar_desc" msgid="5828320291870344584">"在日历中查看所选时间"</string>
    <string name="add_calendar_event" msgid="1953664627192056206">"排定时间"</string>
    <string name="add_calendar_event_desc" msgid="4326891793260687388">"将活动安排在所选时间"</string>
    <string name="view_flight" msgid="7691640491425680214">"跟踪"</string>
    <string name="view_flight_desc" msgid="3876322502674253506">"跟踪所选航班"</string>
    <string name="low_internal_storage_view_title" msgid="5576272496365684834">"存储空间不足"</string>
    <string name="low_internal_storage_view_text" msgid="6640505817617414371">"某些系统功能可能无法正常使用"</string>
    <string name="low_internal_storage_view_text_no_boot" msgid="6935190099204693424">"系统存储空间不足。请确保您有250MB的可用空间，然后重新启动。"</string>
    <string name="app_running_notification_title" msgid="8718335121060787914">"“<xliff:g id="APP_NAME">%1$s</xliff:g>”正在运行"</string>
    <string name="app_running_notification_text" msgid="1197581823314971177">"点按即可了解详情或停止应用。"</string>
    <string name="ok" msgid="5970060430562524910">"确定"</string>
    <string name="cancel" msgid="6442560571259935130">"取消"</string>
    <string name="yes" msgid="5362982303337969312">"确定"</string>
    <string name="no" msgid="5141531044935541497">"取消"</string>
    <string name="dialog_alert_title" msgid="2049658708609043103">"注意"</string>
    <string name="loading" msgid="7933681260296021180">"正在加载..."</string>
    <string name="capital_on" msgid="1544682755514494298">"开启"</string>
    <string name="capital_off" msgid="6815870386972805832">"关闭"</string>
    <string name="whichApplication" msgid="4533185947064773386">"选择要使用的应用："</string>
    <string name="whichApplicationNamed" msgid="8260158865936942783">"使用%1$s完成操作"</string>
    <string name="whichApplicationLabel" msgid="7425855495383818784">"完成操作"</string>
    <string name="whichViewApplication" msgid="3272778576700572102">"打开方式"</string>
    <string name="whichViewApplicationNamed" msgid="2286418824011249620">"使用%1$s打开"</string>
    <string name="whichViewApplicationLabel" msgid="2666774233008808473">"打开"</string>
    <string name="whichEditApplication" msgid="144727838241402655">"编辑方式"</string>
    <string name="whichEditApplicationNamed" msgid="1775815530156447790">"使用%1$s编辑"</string>
    <string name="whichEditApplicationLabel" msgid="7183524181625290300">"编辑"</string>
    <string name="whichSendApplication" msgid="6902512414057341668">"分享方式"</string>
    <string name="whichSendApplicationNamed" msgid="2799370240005424391">"使用%1$s分享"</string>
    <string name="whichSendApplicationLabel" msgid="4579076294675975354">"分享"</string>
    <string name="whichSendToApplication" msgid="8272422260066642057">"通过以下应用发送"</string>
    <string name="whichSendToApplicationNamed" msgid="7768387871529295325">"通过%1$s发送"</string>
    <string name="whichSendToApplicationLabel" msgid="8878962419005813500">"发送"</string>
    <string name="whichHomeApplication" msgid="4307587691506919691">"选择主屏幕应用"</string>
    <string name="whichHomeApplicationNamed" msgid="4493438593214760979">"将“%1$s”设为主屏幕应用"</string>
    <string name="whichHomeApplicationLabel" msgid="809529747002918649">"截图"</string>
    <string name="whichImageCaptureApplication" msgid="3680261417470652882">"使用以下应用截图"</string>
    <string name="whichImageCaptureApplicationNamed" msgid="8619384150737825003">"使用%1$s截图"</string>
    <string name="whichImageCaptureApplicationLabel" msgid="6390303445371527066">"截图"</string>
    <string name="alwaysUse" msgid="4583018368000610438">"设为默认选项。"</string>
    <string name="use_a_different_app" msgid="8134926230585710243">"使用其他应用"</string>
    <string name="clearDefaultHintMsg" msgid="3252584689512077257">"在“系统设置”&gt;“应用”&gt;“已下载”中清除默认设置。"</string>
    <string name="chooseActivity" msgid="7486876147751803333">"选择操作"</string>
    <string name="chooseUsbActivity" msgid="6894748416073583509">"为USB设备选择一个应用"</string>
    <string name="noApplications" msgid="2991814273936504689">"没有应用可执行此操作。"</string>
    <string name="aerr_application" msgid="250320989337856518">"<xliff:g id="APPLICATION">%1$s</xliff:g>已停止运行"</string>
    <string name="aerr_process" msgid="6201597323218674729">"<xliff:g id="PROCESS">%1$s</xliff:g>已停止运行"</string>
    <string name="aerr_application_repeated" msgid="3146328699537439573">"<xliff:g id="APPLICATION">%1$s</xliff:g>屡次停止运行"</string>
    <string name="aerr_process_repeated" msgid="6235302956890402259">"<xliff:g id="PROCESS">%1$s</xliff:g>屡次停止运行"</string>
    <string name="aerr_restart" msgid="7581308074153624475">"重新打开应用"</string>
    <string name="aerr_report" msgid="5371800241488400617">"发送反馈"</string>
    <string name="aerr_close" msgid="2991640326563991340">"关闭"</string>
    <string name="aerr_mute" msgid="1974781923723235953">"忽略（直到设备重启）"</string>
    <string name="aerr_wait" msgid="3199956902437040261">"等待"</string>
    <string name="aerr_close_app" msgid="3269334853724920302">"关闭应用"</string>
    <string name="anr_title" msgid="4351948481459135709"></string>
    <string name="anr_activity_application" msgid="8493290105678066167">"<xliff:g id="APPLICATION">%2$s</xliff:g>没有响应"</string>
    <string name="anr_activity_process" msgid="1622382268908620314">"<xliff:g id="ACTIVITY">%1$s</xliff:g>没有响应"</string>
    <string name="anr_application_process" msgid="6417199034861140083">"<xliff:g id="APPLICATION">%1$s</xliff:g>没有响应"</string>
    <string name="anr_process" msgid="6156880875555921105">"进程“<xliff:g id="PROCESS">%1$s</xliff:g>”没有响应"</string>
    <string name="force_close" msgid="8346072094521265605">"确定"</string>
    <string name="report" msgid="4060218260984795706">"报告"</string>
    <string name="wait" msgid="7147118217226317732">"等待"</string>
    <string name="webpage_unresponsive" msgid="3272758351138122503">"该网页无响应。\n\n要将其关闭吗？"</string>
    <string name="launch_warning_title" msgid="1547997780506713581">"应用已重定向"</string>
    <string name="launch_warning_replace" msgid="6202498949970281412">"<xliff:g id="APP_NAME">%1$s</xliff:g>目前正在运行。"</string>
    <string name="launch_warning_original" msgid="188102023021668683">"<xliff:g id="APP_NAME">%1$s</xliff:g>已启动。"</string>
    <string name="screen_compat_mode_scale" msgid="3202955667675944499">"缩放"</string>
    <string name="screen_compat_mode_show" msgid="4013878876486655892">"始终显示"</string>
    <string name="screen_compat_mode_hint" msgid="1064524084543304459">"在“系统设置”&gt;“应用”&gt;“已下载”中重新启用此模式。"</string>
    <string name="unsupported_display_size_message" msgid="6545327290756295232">"<xliff:g id="APP_NAME">%1$s</xliff:g>不支持当前的显示大小设置，因此可能无法正常显示。"</string>
    <string name="unsupported_display_size_show" msgid="7969129195360353041">"一律显示"</string>
    <string name="unsupported_compile_sdk_message" msgid="4253168368781441759">"<xliff:g id="APP_NAME">%1$s</xliff:g>是专为不兼容的 Android 操作系统版本所打造的应用，因此可能会出现意外行为。您可以使用该应用的更新版本。"</string>
    <string name="unsupported_compile_sdk_show" msgid="2681877855260970231">"一律显示"</string>
    <string name="unsupported_compile_sdk_check_update" msgid="3312723623323216101">"检查更新"</string>
    <string name="smv_application" msgid="3307209192155442829">"“<xliff:g id="APPLICATION">%1$s</xliff:g>”应用（<xliff:g id="PROCESS">%2$s</xliff:g> 进程）违反了自我强制执行的严格模式 (StrictMode) 政策。"</string>
    <string name="smv_process" msgid="5120397012047462446">"进程 <xliff:g id="PROCESS">%1$s</xliff:g> 违反了自我强制执行的严格模式 (StrictMode) 政策。"</string>
    <string name="android_upgrading_title" product="default" msgid="7513829952443484438">"手机正在更新…"</string>
    <string name="android_upgrading_title" product="tablet" msgid="4503169817302593560">"平板电脑正在更新…"</string>
    <string name="android_upgrading_title" product="device" msgid="7009520271220804517">"设备正在更新…"</string>
    <string name="android_start_title" product="default" msgid="4536778526365907780">"手机正在启动…"</string>
    <string name="android_start_title" product="tablet" msgid="4929837533850340472">"平板电脑正在启动…"</string>
    <string name="android_start_title" product="device" msgid="7467484093260449437">"设备正在启动…"</string>
    <string name="android_upgrading_fstrim" msgid="8036718871534640010">"正在优化存储空间。"</string>
    <string name="android_upgrading_notification_title" product="default" msgid="1511552415039349062">"正在完成系统更新…"</string>
    <string name="app_upgrading_toast" msgid="3008139776215597053">"正在升级<xliff:g id="APPLICATION">%1$s</xliff:g>…"</string>
    <string name="android_upgrading_apk" msgid="7904042682111526169">"正在优化第<xliff:g id="NUMBER_0">%1$d</xliff:g>个应用（共<xliff:g id="NUMBER_1">%2$d</xliff:g>个）。"</string>
    <string name="android_preparing_apk" msgid="8162599310274079154">"正在准备升级<xliff:g id="APPNAME">%1$s</xliff:g>。"</string>
    <string name="android_upgrading_starting_apps" msgid="451464516346926713">"正在启动应用。"</string>
    <string name="android_upgrading_complete" msgid="1405954754112999229">"即将完成启动。"</string>
    <string name="heavy_weight_notification" msgid="9087063985776626166">"<xliff:g id="APP">%1$s</xliff:g>正在运行"</string>
    <string name="heavy_weight_notification_detail" msgid="2304833848484424985">"点按即可返回游戏"</string>
    <string name="heavy_weight_switcher_title" msgid="387882830435195342">"选择游戏"</string>
    <string name="heavy_weight_switcher_text" msgid="4176781660362912010">"为了提升性能，一次只能打开其中一个游戏。"</string>
    <string name="old_app_action" msgid="3044685170829526403">"返回<xliff:g id="OLD_APP">%1$s</xliff:g>"</string>
    <string name="new_app_action" msgid="6694851182870774403">"打开<xliff:g id="NEW_APP">%1$s</xliff:g>"</string>
    <string name="new_app_description" msgid="5894852887817332322">"<xliff:g id="OLD_APP">%1$s</xliff:g>将会在不保存的情况下关闭"</string>
    <string name="dump_heap_notification" msgid="2618183274836056542">"<xliff:g id="PROC">%1$s</xliff:g>占用的内存已超出限制"</string>
    <string name="dump_heap_notification_detail" msgid="3993078784053054141">"已收集堆转储数据。点按即可分享。"</string>
    <string name="dump_heap_title" msgid="5864292264307651673">"要共享堆转储数据吗？"</string>
    <string name="dump_heap_text" msgid="4809417337240334941">"<xliff:g id="PROC">%1$s</xliff:g>进程占用的内存已超出限制 (<xliff:g id="SIZE">%2$s</xliff:g>)。您可以将收集的堆转储数据共享给相应的开发者。请注意：此数据中可能包含该应用有权存取的您的个人信息。"</string>
    <string name="sendText" msgid="5209874571959469142">"选择要对文字执行的操作"</string>
    <string name="volume_ringtone" msgid="6885421406845734650">"铃声音量"</string>
    <string name="volume_music" msgid="5421651157138628171">"媒体音量"</string>
    <string name="volume_music_hint_playing_through_bluetooth" msgid="9165984379394601533">"正通过蓝牙播放"</string>
    <string name="volume_music_hint_silent_ringtone_selected" msgid="8310739960973156272">"已设置静音铃声"</string>
    <string name="volume_call" msgid="3941680041282788711">"通话音量"</string>
    <string name="volume_bluetooth_call" msgid="2002891926351151534">"使用蓝牙时的通话音量"</string>
    <string name="volume_alarm" msgid="1985191616042689100">"闹钟音量"</string>
    <string name="volume_notification" msgid="2422265656744276715">"通知音量"</string>
    <string name="volume_unknown" msgid="1400219669770445902">"音量"</string>
    <string name="volume_icon_description_bluetooth" msgid="6538894177255964340">"蓝牙音量"</string>
    <string name="volume_icon_description_ringer" msgid="3326003847006162496">"铃声音量"</string>
    <string name="volume_icon_description_incall" msgid="8890073218154543397">"通话音量"</string>
    <string name="volume_icon_description_media" msgid="4217311719665194215">"媒体音量"</string>
    <string name="volume_icon_description_notification" msgid="7044986546477282274">"通知音量"</string>
    <string name="ringtone_default" msgid="3789758980357696936">"默认铃声"</string>
    <string name="ringtone_default_with_actual" msgid="1767304850491060581">"默认铃声（<xliff:g id="ACTUAL_RINGTONE">%1$s</xliff:g>）"</string>
    <string name="ringtone_silent" msgid="7937634392408977062">"无"</string>
    <string name="ringtone_picker_title" msgid="3515143939175119094">"铃声"</string>
    <string name="ringtone_picker_title_alarm" msgid="6473325356070549702">"闹钟提示音"</string>
    <string name="ringtone_picker_title_notification" msgid="4837740874822788802">"通知提示音"</string>
    <string name="ringtone_unknown" msgid="3914515995813061520">"未知"</string>
    <plurals name="wifi_available" formatted="false" msgid="7900333017752027322">
      <item quantity="other">有可用的 WLAN 网络</item>
      <item quantity="one">有可用的 WLAN 网络</item>
    </plurals>
    <plurals name="wifi_available_detailed" formatted="false" msgid="1140699367193975606">
      <item quantity="other">有可用的开放 WLAN 网络</item>
      <item quantity="one">有可用的开放 WLAN 网络</item>
    </plurals>
    <string name="wifi_available_title" msgid="3817100557900599505">"连接到开放的 WLAN 网络"</string>
    <string name="wifi_available_carrier_network_title" msgid="4527932626916527897">"连接到运营商 WLAN 网络"</string>
    <string name="wifi_available_title_connecting" msgid="1139126673968899002">"正在连接到 WLAN 网络"</string>
    <string name="wifi_available_title_connected" msgid="7542672851522241548">"已连接到 WLAN 网络"</string>
    <string name="wifi_available_title_failed_to_connect" msgid="6861772233582618132">"无法连接到 WLAN 网络"</string>
    <string name="wifi_available_content_failed_to_connect" msgid="3377406637062802645">"点按即可查看所有网络"</string>
    <string name="wifi_available_action_connect" msgid="2635699628459488788">"连接"</string>
    <string name="wifi_available_action_all_networks" msgid="4368435796357931006">"所有网络"</string>
    <string name="wifi_wakeup_onboarding_title" msgid="228772560195634292">"WLAN 将自动开启"</string>
    <string name="wifi_wakeup_onboarding_subtext" msgid="3989697580301186973">"当您位于已保存的高品质网络信号范围内时"</string>
    <string name="wifi_wakeup_onboarding_action_disable" msgid="838648204200836028">"不要重新开启"</string>
    <string name="wifi_wakeup_enabled_title" msgid="6534603733173085309">"已自动开启 WLAN 网络"</string>
    <string name="wifi_wakeup_enabled_content" msgid="189330154407990583">"您位于已保存的网络 (<xliff:g id="NETWORK_SSID">%1$s</xliff:g>) 信号范围内"</string>
    <string name="wifi_available_sign_in" msgid="9157196203958866662">"登录到WLAN网络"</string>
    <string name="network_available_sign_in" msgid="1848877297365446605">"登录到网络"</string>
    <!-- no translation found for network_available_sign_in_detailed (8000081941447976118) -->
    <skip />
    <string name="wifi_no_internet" msgid="8938267198124654938">"此 WLAN 网络无法访问互联网"</string>
    <string name="wifi_no_internet_detailed" msgid="8083079241212301741">"点按即可查看相关选项"</string>
    <string name="network_switch_metered" msgid="4671730921726992671">"已切换至<xliff:g id="NETWORK_TYPE">%1$s</xliff:g>"</string>
    <string name="network_switch_metered_detail" msgid="775163331794506615">"设备会在<xliff:g id="PREVIOUS_NETWORK">%2$s</xliff:g>无法访问互联网时使用<xliff:g id="NEW_NETWORK">%1$s</xliff:g>（可能需要支付相应的费用）。"</string>
    <string name="network_switch_metered_toast" msgid="5779283181685974304">"已从<xliff:g id="PREVIOUS_NETWORK">%1$s</xliff:g>切换至<xliff:g id="NEW_NETWORK">%2$s</xliff:g>"</string>
  <string-array name="network_switch_type_name">
    <item msgid="3979506840912951943">"移动数据"</item>
    <item msgid="75483255295529161">"WLAN"</item>
    <item msgid="6862614801537202646">"蓝牙"</item>
    <item msgid="5447331121797802871">"以太网"</item>
    <item msgid="8257233890381651999">"VPN"</item>
  </string-array>
    <string name="network_switch_type_name_unknown" msgid="4552612897806660656">"未知网络类型"</string>
    <string name="wifi_watchdog_network_disabled" msgid="7904214231651546347">"无法连接到WLAN"</string>
    <string name="wifi_watchdog_network_disabled_detailed" msgid="4917472096696322767">" 互联网连接状况不佳。"</string>
    <string name="wifi_connect_alert_title" msgid="8455846016001810172">"要允许连接吗？"</string>
    <string name="wifi_connect_alert_message" msgid="6451273376815958922">"“%1$s”应用想要连接到 WLAN 网络“%2$s”"</string>
    <string name="wifi_connect_default_application" msgid="7143109390475484319">"一款应用"</string>
    <string name="wifi_p2p_dialog_title" msgid="97611782659324517">"WLAN 直连"</string>
    <string name="wifi_p2p_turnon_message" msgid="2909250942299627244">"启动WLAN直连。此操作将会关闭WLAN客户端/热点。"</string>
    <string name="wifi_p2p_failed_message" msgid="3763669677935623084">"无法启动WLAN直连。"</string>
    <string name="wifi_p2p_enabled_notification_title" msgid="2068321881673734886">"已启用WLAN直连"</string>
    <string name="wifi_p2p_enabled_notification_message" msgid="8064677407830620023">"点按即可查看相关设置"</string>
    <string name="accept" msgid="1645267259272829559">"接受"</string>
    <string name="decline" msgid="2112225451706137894">"拒绝"</string>
    <string name="wifi_p2p_invitation_sent_title" msgid="1318975185112070734">"已发出邀请"</string>
    <string name="wifi_p2p_invitation_to_connect_title" msgid="4958803948658533637">"连接邀请"</string>
    <string name="wifi_p2p_from_message" msgid="570389174731951769">"发件人："</string>
    <string name="wifi_p2p_to_message" msgid="248968974522044099">"收件人："</string>
    <string name="wifi_p2p_enter_pin_message" msgid="5920929550367828970">"输入所需的PIN码："</string>
    <string name="wifi_p2p_show_pin_message" msgid="8530563323880921094">"PIN 码："</string>
    <string name="wifi_p2p_frequency_conflict_message" product="tablet" msgid="8012981257742232475">"平板电脑连接到“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”时会暂时断开与WLAN的连接"</string>
    <string name="wifi_p2p_frequency_conflict_message" product="tv" msgid="3087858235069421128">"电视连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>时会暂时断开与 WLAN 的连接"</string>
    <string name="wifi_p2p_frequency_conflict_message" product="default" msgid="7363907213787469151">"手机连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>时会暂时断开与WLAN的连接。"</string>
    <string name="select_character" msgid="3365550120617701745">"插入字符"</string>
    <string name="sms_control_title" msgid="7296612781128917719">"正在发送短信"</string>
    <string name="sms_control_message" msgid="3867899169651496433">"&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;在发送大量短信。是否允许该应用继续发送短信？"</string>
    <string name="sms_control_yes" msgid="3663725993855816807">"允许"</string>
    <string name="sms_control_no" msgid="625438561395534982">"拒绝"</string>
    <string name="sms_short_code_confirm_message" msgid="1645436466285310855">"&lt;b&gt;<xliff:g id="APP_NAME">%1$s</xliff:g>&lt;/b&gt;想要向 &lt;b&gt;<xliff:g id="DEST_ADDRESS">%2$s</xliff:g>&lt;/b&gt; 发送一条短信。"</string>
    <string name="sms_short_code_details" msgid="5873295990846059400"><b>"这可能会导致您的手机号产生费用。"</b></string>
    <string name="sms_premium_short_code_details" msgid="7869234868023975"><b>"这会导致您的手机号产生费用。"</b></string>
    <string name="sms_short_code_confirm_allow" msgid="4458878637111023413">"发送"</string>
    <string name="sms_short_code_confirm_deny" msgid="2927389840209170706">"取消"</string>
    <string name="sms_short_code_remember_choice" msgid="5289538592272218136">"记住我的选择"</string>
    <string name="sms_short_code_remember_undo_instruction" msgid="4960944133052287484">"之后，您可以在“设置”&gt;“应用”中更改此设置"</string>
    <string name="sms_short_code_confirm_always_allow" msgid="3241181154869493368">"始终允许"</string>
    <string name="sms_short_code_confirm_never_allow" msgid="446992765774269673">"永不允许"</string>
    <string name="sim_removed_title" msgid="6227712319223226185">"已移除SIM卡"</string>
    <string name="sim_removed_message" msgid="2333164559970958645">"移动网络不可用。请插入有效的SIM卡并重新启动。"</string>
    <string name="sim_done_button" msgid="827949989369963775">"完成"</string>
    <string name="sim_added_title" msgid="3719670512889674693">"已添加SIM卡"</string>
    <string name="sim_added_message" msgid="6599945301141050216">"请重新启动您的设备，以便访问移动网络。"</string>
    <string name="sim_restart_button" msgid="4722407842815232347">"重新启动"</string>
    <string name="install_carrier_app_notification_title" msgid="9056007111024059888">"激活移动网络服务"</string>
    <string name="install_carrier_app_notification_text" msgid="3346681446158696001">"下载运营商应用即可激活您的新 SIM 卡"</string>
    <string name="install_carrier_app_notification_text_app_name" msgid="1196505084835248137">"下载<xliff:g id="APP_NAME">%1$s</xliff:g>应用即可激活您的新 SIM 卡"</string>
    <string name="install_carrier_app_notification_button" msgid="3094206295081900849">"下载应用"</string>
    <string name="carrier_app_notification_title" msgid="8921767385872554621">"已插入新 SIM 卡"</string>
    <string name="carrier_app_notification_text" msgid="1132487343346050225">"点按即可进行设置"</string>
    <string name="time_picker_dialog_title" msgid="8349362623068819295">"设置时间"</string>
    <string name="date_picker_dialog_title" msgid="5879450659453782278">"设置日期"</string>
    <string name="date_time_set" msgid="5777075614321087758">"设置"</string>
    <string name="date_time_done" msgid="2507683751759308828">"完成"</string>
    <string name="perms_new_perm_prefix" msgid="8257740710754301407"><font size="12" fgcolor="#ff33b5e5">"新增："</font></string>
    <string name="perms_description_app" msgid="5139836143293299417">"由“<xliff:g id="APP_NAME">%1$s</xliff:g>”提供。"</string>
    <string name="no_permissions" msgid="7283357728219338112">"不需要任何权限"</string>
    <string name="perm_costs_money" msgid="4902470324142151116">"这可能会产生费用"</string>
    <string name="dlg_ok" msgid="7376953167039865701">"确定"</string>
    <string name="usb_charging_notification_title" msgid="1595122345358177163">"正在通过 USB 为此设备充电"</string>
    <string name="usb_supplying_notification_title" msgid="4631045789893086181">"正在通过 USB 为连接的设备充电"</string>
    <string name="usb_mtp_notification_title" msgid="4238227258391151029">"已开启 USB 文件传输模式"</string>
    <string name="usb_ptp_notification_title" msgid="5425857879922006878">"已开启 USB PTP 模式"</string>
    <string name="usb_tether_notification_title" msgid="3716143122035802501">"已开启 USB 网络共享模式"</string>
    <string name="usb_midi_notification_title" msgid="5356040379749154805">"已开启 USB MIDI 模式"</string>
    <string name="usb_accessory_notification_title" msgid="1785694450621427730">"USB 配件已连接"</string>
    <string name="usb_notification_message" msgid="3370903770828407960">"点按即可查看更多选项。"</string>
    <string name="usb_power_notification_message" msgid="4647527153291917218">"正在为连接的设备充电。点按即可查看更多选项。"</string>
    <string name="usb_unsupported_audio_accessory_title" msgid="3529881374464628084">"检测到模拟音频配件"</string>
    <string name="usb_unsupported_audio_accessory_message" msgid="6309553946441565215">"连接的设备与此手机不兼容。点按即可了解详情。"</string>
    <string name="adb_active_notification_title" msgid="6729044778949189918">"已连接到 USB 调试"</string>
    <string name="adb_active_notification_message" msgid="7463062450474107752">"点按即可关闭 USB 调试"</string>
    <string name="adb_active_notification_message" product="tv" msgid="8470296818270110396">"选择即可停用 USB 调试功能。"</string>
    <string name="taking_remote_bugreport_notification_title" msgid="6742483073875060934">"正在生成错误报告…"</string>
    <string name="share_remote_bugreport_notification_title" msgid="4987095013583691873">"要分享错误报告吗？"</string>
    <string name="sharing_remote_bugreport_notification_title" msgid="7572089031496651372">"正在分享错误报告…"</string>
    <string name="share_remote_bugreport_notification_message_finished" msgid="6029609949340992866">"您的管理员希望获取错误报告，以便排查此设备的问题。报告可能会透露您设备上的应用和数据。"</string>
    <string name="share_remote_bugreport_action" msgid="6249476773913384948">"分享"</string>
    <string name="decline_remote_bugreport_action" msgid="6230987241608770062">"拒绝"</string>
    <string name="select_input_method" msgid="8547250819326693584">"更改键盘"</string>
    <string name="show_ime" msgid="2506087537466597099">"开启后，连接到实体键盘时，它会一直显示在屏幕上"</string>
    <string name="hardware" msgid="194658061510127999">"显示虚拟键盘"</string>
    <string name="select_keyboard_layout_notification_title" msgid="597189518763083494">"配置实体键盘"</string>
    <string name="select_keyboard_layout_notification_message" msgid="8084622969903004900">"点按即可选择语言和布局"</string>
    <string name="fast_scroll_alphabet" msgid="5433275485499039199">" ABCDEFGHIJKLMNOPQRSTUVWXYZ"</string>
    <string name="fast_scroll_numeric_alphabet" msgid="4030170524595123610">" 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"</string>
    <string name="alert_windows_notification_channel_group_name" msgid="1463953341148606396">"显示在其他应用的上层"</string>
    <string name="alert_windows_notification_channel_name" msgid="3116610965549449803">"“<xliff:g id="NAME">%s</xliff:g>”正在其他应用的上层显示内容"</string>
    <string name="alert_windows_notification_title" msgid="3697657294867638947">"“<xliff:g id="NAME">%s</xliff:g>”正在其他应用的上层显示内容"</string>
    <string name="alert_windows_notification_message" msgid="8917232109522912560">"如果您不想让“<xliff:g id="NAME">%s</xliff:g>”使用此功能，请点按以打开设置，然后关闭此功能。"</string>
    <string name="alert_windows_notification_turn_off_action" msgid="2902891971380544651">"关闭"</string>
    <string name="ext_media_checking_notification_title" msgid="4411133692439308924">"正在检查<xliff:g id="NAME">%s</xliff:g>…"</string>
    <string name="ext_media_checking_notification_message" msgid="410185170877285434">"正在检查当前内容"</string>
    <string name="ext_media_new_notification_title" msgid="1621805083736634077">"新的<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_new_notification_message" msgid="3673685270558405087">"点按即可进行设置"</string>
    <string name="ext_media_ready_notification_message" msgid="4083398150380114462">"可用于传输照片和媒体文件"</string>
    <string name="ext_media_unmountable_notification_title" msgid="4179418065210797130">"<xliff:g id="NAME">%s</xliff:g>出现问题"</string>
    <string name="ext_media_unmountable_notification_message" msgid="4193858924381066522">"点按即可修正问题"</string>
    <string name="ext_media_unmountable_notification_message" product="tv" msgid="3941179940297874950">"<xliff:g id="NAME">%s</xliff:g>已损坏。选择即可进行修正。"</string>
    <string name="ext_media_unsupported_notification_title" msgid="3797642322958803257">"<xliff:g id="NAME">%s</xliff:g>不受支持"</string>
    <string name="ext_media_unsupported_notification_message" msgid="6121601473787888589">"该设备不支持此<xliff:g id="NAME">%s</xliff:g>。点按即可使用支持的格式进行设置。"</string>
    <string name="ext_media_unsupported_notification_message" product="tv" msgid="3725436899820390906">"此设备不支持该<xliff:g id="NAME">%s</xliff:g>。选择即可使用支持的格式进行设置。"</string>
    <string name="ext_media_badremoval_notification_title" msgid="3206248947375505416">"<xliff:g id="NAME">%s</xliff:g>已意外移除"</string>
    <string name="ext_media_badremoval_notification_message" msgid="8556885808951260574">"请先弹出媒体，再将其移除，以防内容丢失"</string>
    <string name="ext_media_nomedia_notification_title" msgid="6593814191061956856">"<xliff:g id="NAME">%s</xliff:g>已被移除"</string>
    <string name="ext_media_nomedia_notification_message" msgid="2110883356419799994">"部分功能可能无法正常运行。请插入新的存储设备。"</string>
    <string name="ext_media_unmounting_notification_title" msgid="5046532339291216076">"正在弹出<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_unmounting_notification_message" msgid="1003926904442321115">"请勿移除"</string>
    <string name="ext_media_init_action" msgid="7952885510091978278">"设置"</string>
    <string name="ext_media_unmount_action" msgid="1121883233103278199">"弹出"</string>
    <string name="ext_media_browse_action" msgid="8322172381028546087">"浏览"</string>
    <string name="ext_media_missing_title" msgid="620980315821543904">"缺少<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_missing_message" msgid="4012389235250987930">"请再次插入设备"</string>
    <string name="ext_media_move_specific_title" msgid="1471100343872375842">"正在移动<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_move_title" msgid="1022809140035962662">"正在移动数据"</string>
    <string name="ext_media_move_success_title" msgid="7863652232242276066">"内容转移操作已完成"</string>
    <string name="ext_media_move_success_message" msgid="8939137931961728009">"已将内容移至<xliff:g id="NAME">%s</xliff:g>"</string>
    <string name="ext_media_move_failure_title" msgid="1604422634177382092">"无法移动内容"</string>
    <string name="ext_media_move_failure_message" msgid="7388950499623016135">"请再次尝试移动内容"</string>
    <string name="ext_media_status_removed" msgid="6576172423185918739">"已移除"</string>
    <string name="ext_media_status_unmounted" msgid="2551560878416417752">"已弹出"</string>
    <string name="ext_media_status_checking" msgid="6193921557423194949">"正在检查…"</string>
    <string name="ext_media_status_mounted" msgid="7253821726503179202">"就绪"</string>
    <string name="ext_media_status_mounted_ro" msgid="8020978752406021015">"只读"</string>
    <string name="ext_media_status_bad_removal" msgid="8395398567890329422">"未安全移除"</string>
    <string name="ext_media_status_unmountable" msgid="805594039236667894">"已损坏"</string>
    <string name="ext_media_status_unsupported" msgid="4691436711745681828">"不支持"</string>
    <string name="ext_media_status_ejecting" msgid="5463887263101234174">"正在弹出…"</string>
    <string name="ext_media_status_formatting" msgid="1085079556538644861">"正在格式化…"</string>
    <string name="ext_media_status_missing" msgid="5638633895221670766">"未插入"</string>
    <string name="activity_list_empty" msgid="1675388330786841066">"未找到匹配的活动。"</string>
    <string name="permlab_route_media_output" msgid="6243022988998972085">"更改媒体输出线路"</string>
    <string name="permdesc_route_media_output" msgid="4932818749547244346">"允许该应用将媒体输出线路更改到其他外部设备。"</string>
    <string name="permlab_readInstallSessions" msgid="3713753067455750349">"读取安装会话"</string>
    <string name="permdesc_readInstallSessions" msgid="2049771699626019849">"允许应用读取安装会话。这样，应用将可以查看有关当前软件包安装的详情。"</string>
    <string name="permlab_requestInstallPackages" msgid="5782013576218172577">"请求安装文件包"</string>
    <string name="permdesc_requestInstallPackages" msgid="5740101072486783082">"允许应用请求安装文件包。"</string>
    <string name="permlab_requestDeletePackages" msgid="1703686454657781242">"请求删除文件包"</string>
    <string name="permdesc_requestDeletePackages" msgid="3406172963097595270">"允许应用请求删除文件包。"</string>
    <string name="permlab_requestIgnoreBatteryOptimizations" msgid="8021256345643918264">"请求忽略电池优化"</string>
    <string name="permdesc_requestIgnoreBatteryOptimizations" msgid="8359147856007447638">"允许应用请求相应的权限，以便忽略针对该应用的电池优化。"</string>
    <string name="tutorial_double_tap_to_zoom_message_short" msgid="1311810005957319690">"双击可以进行缩放控制"</string>
    <string name="gadget_host_error_inflating" msgid="4882004314906466162">"无法添加微件。"</string>
    <string name="ime_action_go" msgid="8320845651737369027">"开始"</string>
    <string name="ime_action_search" msgid="658110271822807811">"搜索"</string>
    <string name="ime_action_send" msgid="2316166556349314424">"发送"</string>
    <string name="ime_action_next" msgid="3138843904009813834">"下一步"</string>
    <string name="ime_action_done" msgid="8971516117910934605">"完成"</string>
    <string name="ime_action_previous" msgid="1443550039250105948">"上一页"</string>
    <string name="ime_action_default" msgid="2840921885558045721">"执行"</string>
    <string name="dial_number_using" msgid="5789176425167573586">"拨打电话\n<xliff:g id="NUMBER">%s</xliff:g>"</string>
    <string name="create_contact_using" msgid="4947405226788104538">"创建电话号码为\n<xliff:g id="NUMBER">%s</xliff:g> 的联系人"</string>
    <string name="grant_credentials_permission_message_header" msgid="2106103817937859662">"以下一个或多个应用请求获得相应权限，以便在当前和以后访问您的帐号。"</string>
    <string name="grant_credentials_permission_message_footer" msgid="3125211343379376561">"您是否同意此请求？"</string>
    <string name="grant_permissions_header_text" msgid="6874497408201826708">"访问权限请求"</string>
    <string name="allow" msgid="7225948811296386551">"允许"</string>
    <string name="deny" msgid="2081879885755434506">"拒绝"</string>
    <string name="permission_request_notification_title" msgid="6486759795926237907">"权限请求"</string>
    <string name="permission_request_notification_with_subtitle" msgid="8530393139639560189">"应用对帐号 <xliff:g id="ACCOUNT">%s</xliff:g>\n 提出权限请求。"</string>
    <string name="forward_intent_to_owner" msgid="1207197447013960896">"您目前是在工作资料之外使用此应用"</string>
    <string name="forward_intent_to_work" msgid="621480743856004612">"您目前是在工作资料内使用此应用"</string>
    <string name="input_method_binding_label" msgid="1283557179944992649">"输入法"</string>
    <string name="sync_binding_label" msgid="3687969138375092423">"同步"</string>
    <string name="accessibility_binding_label" msgid="4148120742096474641">"无障碍"</string>
    <string name="wallpaper_binding_label" msgid="1240087844304687662">"壁纸"</string>
    <string name="chooser_wallpaper" msgid="7873476199295190279">"更改壁纸"</string>
    <string name="notification_listener_binding_label" msgid="2014162835481906429">"通知侦听器"</string>
    <string name="vr_listener_binding_label" msgid="4316591939343607306">"VR 监听器"</string>
    <string name="condition_provider_service_binding_label" msgid="1321343352906524564">"条件提供程序"</string>
    <string name="notification_ranker_binding_label" msgid="774540592299064747">"通知重要程度排序服务"</string>
    <string name="vpn_title" msgid="19615213552042827">"已激活VPN"</string>
    <string name="vpn_title_long" msgid="6400714798049252294">"<xliff:g id="APP">%s</xliff:g>已激活VPN"</string>
    <string name="vpn_text" msgid="1610714069627824309">"点按即可管理网络。"</string>
    <string name="vpn_text_long" msgid="4907843483284977618">"已连接到<xliff:g id="SESSION">%s</xliff:g>。点按即可管理网络。"</string>
    <string name="vpn_lockdown_connecting" msgid="6443438964440960745">"正在连接到始终开启的 VPN…"</string>
    <string name="vpn_lockdown_connected" msgid="8202679674819213931">"已连接到始终开启的 VPN"</string>
    <string name="vpn_lockdown_disconnected" msgid="735805531187559719">"始终开启的 VPN 已断开连接"</string>
    <string name="vpn_lockdown_error" msgid="3133844445659711681">"无法连接到始终开启的 VPN"</string>
    <string name="vpn_lockdown_config" msgid="8151951501116759194">"更改网络或 VPN 设置"</string>
    <string name="upload_file" msgid="2897957172366730416">"选择文件"</string>
    <string name="no_file_chosen" msgid="6363648562170759465">"未选定任何文件"</string>
    <string name="reset" msgid="2448168080964209908">"重置"</string>
    <string name="submit" msgid="1602335572089911941">"提交"</string>
    <string name="car_mode_disable_notification_title" msgid="5704265646471239078">"驾驶应用正在运行"</string>
    <string name="car_mode_disable_notification_message" msgid="7647248420931129377">"点按即可退出驾驶应用。"</string>
    <string name="tethered_notification_title" msgid="3146694234398202601">"网络共享或热点已启用"</string>
    <string name="tethered_notification_message" msgid="2113628520792055377">"点按即可进行设置。"</string>
    <string name="disable_tether_notification_title" msgid="7526977944111313195">"网络共享已停用"</string>
    <string name="disable_tether_notification_message" msgid="2913366428516852495">"请与您的管理员联系以了解详情"</string>
    <string name="back_button_label" msgid="2300470004503343439">"上一步"</string>
    <string name="next_button_label" msgid="1080555104677992408">"下一步"</string>
    <string name="skip_button_label" msgid="1275362299471631819">"跳过"</string>
    <string name="no_matches" msgid="8129421908915840737">"无匹配项"</string>
    <string name="find_on_page" msgid="1946799233822820384">"在网页上查找"</string>
    <plurals name="matches_found" formatted="false" msgid="1210884353962081884">
      <item quantity="other">第 <xliff:g id="INDEX">%d</xliff:g> 条结果（共 <xliff:g id="TOTAL">%d</xliff:g> 条）</item>
      <item quantity="one">1 条结果</item>
    </plurals>
    <string name="action_mode_done" msgid="7217581640461922289">"完成"</string>
    <string name="progress_erasing" product="nosdcard" msgid="4521573321524340058">"正在清除USB存储设备的数据..."</string>
    <string name="progress_erasing" product="default" msgid="6596988875507043042">"正在清除SD卡的数据..."</string>
    <string name="share" msgid="1778686618230011964">"分享"</string>
    <string name="find" msgid="4808270900322985960">"查找"</string>
    <string name="websearch" msgid="4337157977400211589">"网页搜索"</string>
    <string name="find_next" msgid="5742124618942193978">"下一条结果"</string>
    <string name="find_previous" msgid="2196723669388360506">"上一条结果"</string>
    <string name="gpsNotifTicker" msgid="5622683912616496172">"来自<xliff:g id="NAME">%s</xliff:g>的定位请求"</string>
    <string name="gpsNotifTitle" msgid="5446858717157416839">"定位请求"</string>
    <string name="gpsNotifMessage" msgid="1374718023224000702">"请求人：<xliff:g id="NAME">%1$s</xliff:g>（<xliff:g id="SERVICE">%2$s</xliff:g>）"</string>
    <string name="gpsVerifYes" msgid="2346566072867213563">"是"</string>
    <string name="gpsVerifNo" msgid="1146564937346454865">"否"</string>
    <string name="sync_too_many_deletes" msgid="5296321850662746890">"超出删除限制"</string>
    <string name="sync_too_many_deletes_desc" msgid="496551671008694245">"帐号 <xliff:g id="ACCOUNT_NAME">%3$s</xliff:g> 在进行“<xliff:g id="TYPE_OF_SYNC">%2$s</xliff:g>”同步时删除了 <xliff:g id="NUMBER_OF_DELETED_ITEMS">%1$d</xliff:g> 项内容。您要如何处理这些删除的内容？"</string>
    <string name="sync_really_delete" msgid="2572600103122596243">"删除这些内容"</string>
    <string name="sync_undo_deletes" msgid="2941317360600338602">"撤消删除"</string>
    <string name="sync_do_nothing" msgid="3743764740430821845">"目前不进行任何操作"</string>
    <string name="choose_account_label" msgid="5655203089746423927">"选择帐号"</string>
    <string name="add_account_label" msgid="2935267344849993553">"添加帐号"</string>
    <string name="add_account_button_label" msgid="3611982894853435874">"添加帐号"</string>
    <string name="number_picker_increment_button" msgid="2412072272832284313">"增大"</string>
    <string name="number_picker_decrement_button" msgid="476050778386779067">"减小"</string>
    <string name="number_picker_increment_scroll_mode" msgid="5259126567490114216">"<xliff:g id="VALUE">%s</xliff:g> 触摸并按住。"</string>
    <string name="number_picker_increment_scroll_action" msgid="9101473045891835490">"向上滑动可增大数值，向下滑动可减小数值。"</string>
    <string name="time_picker_increment_minute_button" msgid="8865885114028614321">"增大分钟值"</string>
    <string name="time_picker_decrement_minute_button" msgid="6246834937080684791">"减小分钟值"</string>
    <string name="time_picker_increment_hour_button" msgid="3652056055810223139">"增大小时值"</string>
    <string name="time_picker_decrement_hour_button" msgid="1377479863429214792">"减小小时值"</string>
    <string name="time_picker_increment_set_pm_button" msgid="4147590696151230863">"设置下午时间"</string>
    <string name="time_picker_decrement_set_am_button" msgid="8302140353539486752">"设置上午时间"</string>
    <string name="date_picker_increment_month_button" msgid="5369998479067934110">"增大月份值"</string>
    <string name="date_picker_decrement_month_button" msgid="1832698995541726019">"减小月份值"</string>
    <string name="date_picker_increment_day_button" msgid="7130465412308173903">"增大日期值"</string>
    <string name="date_picker_decrement_day_button" msgid="4131881521818750031">"减小日期值"</string>
    <string name="date_picker_increment_year_button" msgid="6318697384310808899">"增大年份值"</string>
    <string name="date_picker_decrement_year_button" msgid="4482021813491121717">"减小年份值"</string>
    <string name="date_picker_prev_month_button" msgid="2858244643992056505">"上个月"</string>
    <string name="date_picker_next_month_button" msgid="5559507736887605055">"下个月"</string>
    <string name="keyboardview_keycode_alt" msgid="4856868820040051939">"Alt"</string>
    <string name="keyboardview_keycode_cancel" msgid="1203984017245783244">"取消"</string>
    <string name="keyboardview_keycode_delete" msgid="3337914833206635744">"Delete"</string>
    <string name="keyboardview_keycode_done" msgid="1992571118466679775">"完成"</string>
    <string name="keyboardview_keycode_mode_change" msgid="4547387741906537519">"模式更改"</string>
    <string name="keyboardview_keycode_shift" msgid="2270748814315147690">"Shift"</string>
    <string name="keyboardview_keycode_enter" msgid="2985864015076059467">"Enter"</string>
    <string name="activitychooserview_choose_application" msgid="2125168057199941199">"选择应用"</string>
    <string name="activitychooserview_choose_application_error" msgid="8624618365481126668">"无法启动“<xliff:g id="APPLICATION_NAME">%s</xliff:g>”"</string>
    <string name="shareactionprovider_share_with" msgid="806688056141131819">"分享方式"</string>
    <string name="shareactionprovider_share_with_application" msgid="5627411384638389738">"使用<xliff:g id="APPLICATION_NAME">%s</xliff:g>分享"</string>
    <string name="content_description_sliding_handle" msgid="415975056159262248">"滑动手柄。触摸并按住。"</string>
    <string name="description_target_unlock_tablet" msgid="3833195335629795055">"滑动解锁。"</string>
    <string name="action_bar_home_description" msgid="5293600496601490216">"导航首页"</string>
    <string name="action_bar_up_description" msgid="2237496562952152589">"向上导航"</string>
    <string name="action_menu_overflow_description" msgid="2295659037509008453">"更多选项"</string>
    <string name="action_bar_home_description_format" msgid="7965984360903693903">"%1$s：%2$s"</string>
    <string name="action_bar_home_subtitle_description_format" msgid="6985546530471780727">"%1$s - %2$s：%3$s"</string>
    <string name="storage_internal" msgid="3570990907910199483">"内部共享存储空间"</string>
    <string name="storage_sd_card" msgid="3282948861378286745">"SD卡"</string>
    <string name="storage_sd_card_label" msgid="6347111320774379257">"<xliff:g id="MANUFACTURER">%s</xliff:g> SD 卡"</string>
    <string name="storage_usb_drive" msgid="6261899683292244209">"U 盘"</string>
    <string name="storage_usb_drive_label" msgid="4501418548927759953">"<xliff:g id="MANUFACTURER">%s</xliff:g> U 盘"</string>
    <string name="storage_usb" msgid="3017954059538517278">"USB存储器"</string>
    <string name="extract_edit_menu_button" msgid="8940478730496610137">"修改"</string>
    <string name="data_usage_warning_title" msgid="6499834033204801605">"数据流量警告"</string>
    <string name="data_usage_warning_body" msgid="7340198905103751676">"您已使用 <xliff:g id="APP">%s</xliff:g> 的数据流量"</string>
    <string name="data_usage_mobile_limit_title" msgid="6561099244084267376">"已达到移动数据流量上限"</string>
    <string name="data_usage_wifi_limit_title" msgid="5803363779034792676">"已达到 WLAN 流量上限"</string>
    <string name="data_usage_limit_body" msgid="2908179506560812973">"已暂停使用数据网络连接，直到这个周期结束为止"</string>
    <string name="data_usage_mobile_limit_snoozed_title" msgid="3171402244827034372">"已超出移动数据流量上限"</string>
    <string name="data_usage_wifi_limit_snoozed_title" msgid="3547771791046344188">"已超出 WLAN 数据流量上限"</string>
    <string name="data_usage_limit_snoozed_body" msgid="1671222777207603301">"您已使用 <xliff:g id="SIZE">%s</xliff:g>，超出所设上限"</string>
    <string name="data_usage_restricted_title" msgid="5965157361036321914">"后台流量受限制"</string>
    <string name="data_usage_restricted_body" msgid="469866376337242726">"点按即可取消限制。"</string>
    <string name="data_usage_rapid_title" msgid="1809795402975261331">"移动数据用量较多"</string>
    <string name="data_usage_rapid_body" msgid="6897825788682442715">"您的应用使用的数据流量比平时多"</string>
    <string name="data_usage_rapid_app_body" msgid="5396680996784142544">"<xliff:g id="APP">%s</xliff:g>使用的数据流量比平时多"</string>
    <string name="ssl_certificate" msgid="6510040486049237639">"安全证书"</string>
    <string name="ssl_certificate_is_valid" msgid="6825263250774569373">"该证书有效。"</string>
    <string name="issued_to" msgid="454239480274921032">"颁发给："</string>
    <string name="common_name" msgid="2233209299434172646">"常用名称："</string>
    <string name="org_name" msgid="6973561190762085236">"组织："</string>
    <string name="org_unit" msgid="7265981890422070383">"组织单位："</string>
    <string name="issued_by" msgid="2647584988057481566">"颁发者："</string>
    <string name="validity_period" msgid="8818886137545983110">"有效期："</string>
    <string name="issued_on" msgid="5895017404361397232">"颁发时间："</string>
    <string name="expires_on" msgid="3676242949915959821">"有效期至："</string>
    <string name="serial_number" msgid="758814067660862493">"序列号："</string>
    <string name="fingerprints" msgid="4516019619850763049">"指纹："</string>
    <string name="sha256_fingerprint" msgid="4391271286477279263">"SHA-256 指纹："</string>
    <string name="sha1_fingerprint" msgid="7930330235269404581">"SHA-1 指纹："</string>
    <string name="activity_chooser_view_see_all" msgid="4292569383976636200">"查看全部"</string>
    <string name="activity_chooser_view_dialog_title_default" msgid="4710013864974040615">"选择活动"</string>
    <string name="share_action_provider_share_with" msgid="5247684435979149216">"分享方式"</string>
    <string name="sending" msgid="3245653681008218030">"正在发送..."</string>
    <string name="launchBrowserDefault" msgid="2057951947297614725">"要启动浏览器吗？"</string>
    <string name="SetupCallDefault" msgid="5834948469253758575">"要接听电话吗？"</string>
    <string name="activity_resolver_use_always" msgid="8017770747801494933">"始终"</string>
    <string name="activity_resolver_use_once" msgid="2404644797149173758">"仅此一次"</string>
    <string name="activity_resolver_work_profiles_support" msgid="185598180676883455">"%1$s不支持工作资料"</string>
    <string name="default_audio_route_name" product="tablet" msgid="4617053898167127471">"平板电脑"</string>
    <string name="default_audio_route_name" product="tv" msgid="9158088547603019321">"电视"</string>
    <string name="default_audio_route_name" product="default" msgid="4239291273420140123">"手机"</string>
    <string name="default_audio_route_name_dock_speakers" msgid="6240602982276591864">"基座扬声器"</string>
    <string name="default_audio_route_name_hdmi" msgid="1486254205617081251">"HDMI"</string>
    <string name="default_audio_route_name_headphones" msgid="8119971843803439110">"耳机"</string>
    <string name="default_audio_route_name_usb" msgid="1234984851352637769">"USB"</string>
    <string name="default_audio_route_category_name" msgid="3722811174003886946">"系统"</string>
    <string name="bluetooth_a2dp_audio_route_name" msgid="8575624030406771015">"蓝牙音频"</string>
    <string name="wireless_display_route_description" msgid="9070346425023979651">"无线显示"</string>
    <string name="media_route_button_content_description" msgid="591703006349356016">"投射"</string>
    <string name="media_route_chooser_title" msgid="1751618554539087622">"连接到设备"</string>
    <string name="media_route_chooser_title_for_remote_display" msgid="3395541745872017583">"将屏幕投射到设备上"</string>
    <string name="media_route_chooser_searching" msgid="4776236202610828706">"正在搜索设备…"</string>
    <string name="media_route_chooser_extended_settings" msgid="87015534236701604">"设置"</string>
    <string name="media_route_controller_disconnect" msgid="8966120286374158649">"断开连接"</string>
    <string name="media_route_status_scanning" msgid="7279908761758293783">"正在扫描..."</string>
    <string name="media_route_status_connecting" msgid="6422571716007825440">"正在连接..."</string>
    <string name="media_route_status_available" msgid="6983258067194649391">"可连接"</string>
    <string name="media_route_status_not_available" msgid="6739899962681886401">"无法连接"</string>
    <string name="media_route_status_in_use" msgid="4533786031090198063">"正在使用"</string>
    <string name="display_manager_built_in_display_name" msgid="2583134294292563941">"内置屏幕"</string>
    <string name="display_manager_hdmi_display_name" msgid="1555264559227470109">"HDMI 屏幕"</string>
    <string name="display_manager_overlay_display_name" msgid="5142365982271620716">"叠加视图 #<xliff:g id="ID">%1$d</xliff:g>"</string>
    <string name="display_manager_overlay_display_title" msgid="652124517672257172">"<xliff:g id="NAME">%1$s</xliff:g>：<xliff:g id="WIDTH">%2$d</xliff:g>x<xliff:g id="HEIGHT">%3$d</xliff:g>，<xliff:g id="DPI">%4$d</xliff:g> dpi"</string>
    <string name="display_manager_overlay_display_secure_suffix" msgid="6022119702628572080">"，安全"</string>
    <string name="kg_forgot_pattern_button_text" msgid="8852021467868220608">"忘记了图案"</string>
    <string name="kg_wrong_pattern" msgid="1850806070801358830">"图案错误"</string>
    <string name="kg_wrong_password" msgid="2333281762128113157">"密码错误"</string>
    <string name="kg_wrong_pin" msgid="1131306510833563801">"PIN码有误"</string>
    <plurals name="kg_too_many_failed_attempts_countdown" formatted="false" msgid="8790651267324125694">
      <item quantity="other">请在 <xliff:g id="NUMBER">%d</xliff:g> 秒后重试。</item>
      <item quantity="one">请在 1 秒后重试。</item>
    </plurals>
    <string name="kg_pattern_instructions" msgid="398978611683075868">"绘制您的图案"</string>
    <string name="kg_sim_pin_instructions" msgid="2319508550934557331">"输入 SIM 卡 PIN 码"</string>
    <string name="kg_pin_instructions" msgid="2377242233495111557">"输入PIN码"</string>
    <string name="kg_password_instructions" msgid="5753646556186936819">"输入密码"</string>
    <string name="kg_puk_enter_puk_hint" msgid="453227143861735537">"SIM卡已被停用，需要输入PUK码才能继续使用。有关详情，请联系您的运营商。"</string>
    <string name="kg_puk_enter_pin_hint" msgid="7871604527429602024">"请输入所需的PIN码"</string>
    <string name="kg_enter_confirm_pin_hint" msgid="325676184762529976">"请确认所需的PIN码"</string>
    <string name="kg_sim_unlock_progress_dialog_message" msgid="8950398016976865762">"正在解锁SIM卡..."</string>
    <string name="kg_password_wrong_pin_code" msgid="1139324887413846912">"PIN码有误。"</string>
    <string name="kg_invalid_sim_pin_hint" msgid="8795159358110620001">"请输入4至8位数的PIN码。"</string>
    <string name="kg_invalid_sim_puk_hint" msgid="6025069204539532000">"PUK码应包含8位数字。"</string>
    <string name="kg_invalid_puk" msgid="3638289409676051243">"请重新输入正确的PUK码。如果尝试错误次数过多，SIM卡将永久停用。"</string>
    <string name="kg_invalid_confirm_pin_hint" product="default" msgid="7003469261464593516">"PIN码不匹配"</string>
    <string name="kg_login_too_many_attempts" msgid="6486842094005698475">"图案尝试次数过多"</string>
    <string name="kg_login_instructions" msgid="1100551261265506448">"要解锁，请登录您的Google帐号。"</string>
    <string name="kg_login_username_hint" msgid="5718534272070920364">"用户名（电子邮件地址）"</string>
    <string name="kg_login_password_hint" msgid="9057289103827298549">"密码"</string>
    <string name="kg_login_submit_button" msgid="5355904582674054702">"登录"</string>
    <string name="kg_login_invalid_input" msgid="5754664119319872197">"用户名或密码无效。"</string>
    <string name="kg_login_account_recovery_hint" msgid="5690709132841752974">"忘记了用户名或密码？\n请访问 "<b>"google.com/accounts/recovery"</b>"。"</string>
    <string name="kg_login_checking_password" msgid="1052685197710252395">"正在检查帐号…"</string>
    <string name="kg_too_many_failed_pin_attempts_dialog_message" msgid="8276745642049502550">"您已经<xliff:g id="NUMBER_0">%1$d</xliff:g>次输错了PIN码。\n\n请在<xliff:g id="NUMBER_1">%2$d</xliff:g>秒后重试。"</string>
    <string name="kg_too_many_failed_password_attempts_dialog_message" msgid="7813713389422226531">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次输错密码。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="kg_too_many_failed_pattern_attempts_dialog_message" msgid="74089475965050805">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。\n\n请在 <xliff:g id="NUMBER_1">%2$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="tablet" msgid="1575557200627128949">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁平板电脑。如果再尝试  <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，平板电脑将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="tv" msgid="5621231220154419413">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁电视。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，电视将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_almost_at_wipe" product="default" msgid="4051015943038199910">"您已经 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次错误地尝试解锁手机。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，手机将恢复为出厂默认设置，所有用户数据都会丢失。"</string>
    <string name="kg_failed_attempts_now_wiping" product="tablet" msgid="2072996269148483637">"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁平板电脑。平板电脑现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_now_wiping" product="tv" msgid="4987878286750741463">"您已经 <xliff:g id="NUMBER">%d</xliff:g> 次错误地尝试解锁电视。电视现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_now_wiping" product="default" msgid="4817627474419471518">"您已经<xliff:g id="NUMBER">%d</xliff:g>次错误地尝试解锁手机。手机现在将恢复为出厂默认设置。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="tablet" msgid="3253575572118914370">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的电子邮件帐号解锁平板电脑。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="tv" msgid="4224651132862313471">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用电子邮件帐号解锁电视。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_failed_attempts_almost_at_login" product="default" msgid="1437638152015574839">"您已连续 <xliff:g id="NUMBER_0">%1$d</xliff:g> 次画错解锁图案。如果再尝试 <xliff:g id="NUMBER_1">%2$d</xliff:g> 次后仍不成功，系统就会要求您使用自己的电子邮件帐号解锁手机。\n\n请在 <xliff:g id="NUMBER_2">%3$d</xliff:g> 秒后重试。"</string>
    <string name="kg_text_message_separator" product="default" msgid="4160700433287233771">" — "</string>
    <string name="kg_reordering_delete_drop_target_text" msgid="7899202978204438708">"删除"</string>
    <string name="safe_media_volume_warning" product="default" msgid="2276318909314492312">"要将音量调高到建议的音量以上吗？\n\n长时间保持高音量可能会损伤听力。"</string>
    <string name="accessibility_shortcut_warning_dialog_title" msgid="8404780875025725199">"要使用无障碍快捷方式吗？"</string>
    <string name="accessibility_shortcut_toogle_warning" msgid="7256507885737444807">"开启快捷方式后，同时按下两个音量按钮 3 秒钟即可启动所设定的无障碍功能。\n\n当前设定的无障碍功能：\n<xliff:g id="SERVICE_NAME">%1$s</xliff:g>\n\n如需更改设定的功能，请依次转到“设置”&gt;“无障碍”。"</string>
    <string name="disable_accessibility_shortcut" msgid="627625354248453445">"关闭快捷方式"</string>
    <string name="leave_accessibility_shortcut_on" msgid="7653111894438512680">"使用快捷方式"</string>
    <string name="color_inversion_feature_name" msgid="4231186527799958644">"颜色反转"</string>
    <string name="color_correction_feature_name" msgid="6779391426096954933">"色彩校正"</string>
    <string name="accessibility_shortcut_enabling_service" msgid="7771852911861522636">"无障碍快捷方式已开启<xliff:g id="SERVICE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_shortcut_disabling_service" msgid="2747243438223109821">"无障碍快捷方式已关闭<xliff:g id="SERVICE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_button_prompt_text" msgid="4234556536456854251">"选择按下“无障碍”按钮时要使用的功能："</string>
    <string name="accessibility_button_instructional_text" msgid="6942300463612999993">"要更改指定的功能，请触摸并按住“无障碍”按钮。"</string>
    <string name="accessibility_magnification_chooser_text" msgid="1227146738764986237">"放大功能"</string>
    <string name="user_switched" msgid="3768006783166984410">"当前用户是<xliff:g id="NAME">%1$s</xliff:g>。"</string>
    <string name="user_switching_message" msgid="2871009331809089783">"正在切换为<xliff:g id="NAME">%1$s</xliff:g>…"</string>
    <string name="user_logging_out_message" msgid="8939524935808875155">"正在将<xliff:g id="NAME">%1$s</xliff:g>退出帐号…"</string>
    <string name="owner_name" msgid="2716755460376028154">"机主"</string>
    <string name="error_message_title" msgid="4510373083082500195">"错误"</string>
    <string name="error_message_change_not_allowed" msgid="1238035947357923497">"您的管理员不允许进行这项更改"</string>
    <string name="app_not_found" msgid="3429141853498927379">"找不到可处理此操作的应用"</string>
    <string name="revoke" msgid="5404479185228271586">"撤消"</string>
    <string name="mediasize_iso_a0" msgid="1994474252931294172">"ISO A0"</string>
    <string name="mediasize_iso_a1" msgid="3333060421529791786">"ISO A1"</string>
    <string name="mediasize_iso_a2" msgid="3097535991925798280">"ISO A2"</string>
    <string name="mediasize_iso_a3" msgid="3023213259314236123">"ISO A3"</string>
    <string name="mediasize_iso_a4" msgid="231745325296873764">"ISO A4"</string>
    <string name="mediasize_iso_a5" msgid="3484327407340865411">"ISO A5"</string>
    <string name="mediasize_iso_a6" msgid="4861908487129577530">"ISO A6"</string>
    <string name="mediasize_iso_a7" msgid="5890208588072936130">"ISO A7"</string>
    <string name="mediasize_iso_a8" msgid="4319425041085816612">"ISO A8"</string>
    <string name="mediasize_iso_a9" msgid="4882220529506432008">"ISO A9"</string>
    <string name="mediasize_iso_a10" msgid="2382866026365359391">"ISO A10"</string>
    <string name="mediasize_iso_b0" msgid="3651827147402009675">"ISO B0"</string>
    <string name="mediasize_iso_b1" msgid="6072859628278739957">"ISO B1"</string>
    <string name="mediasize_iso_b2" msgid="1348731852150380378">"ISO B2"</string>
    <string name="mediasize_iso_b3" msgid="2612510181259261379">"ISO B3"</string>
    <string name="mediasize_iso_b4" msgid="695151378838115434">"ISO B4"</string>
    <string name="mediasize_iso_b5" msgid="4863754285582212487">"ISO B5"</string>
    <string name="mediasize_iso_b6" msgid="5305816292139647241">"ISO B6"</string>
    <string name="mediasize_iso_b7" msgid="531673542602786624">"ISO B7"</string>
    <string name="mediasize_iso_b8" msgid="9164474595708850034">"ISO B8"</string>
    <string name="mediasize_iso_b9" msgid="282102976764774160">"ISO B9"</string>
    <string name="mediasize_iso_b10" msgid="4517141714407898976">"ISO B10"</string>
    <string name="mediasize_iso_c0" msgid="3103521357901591100">"ISO C0"</string>
    <string name="mediasize_iso_c1" msgid="1231954105985048595">"ISO C1"</string>
    <string name="mediasize_iso_c2" msgid="927702816980087462">"ISO C2"</string>
    <string name="mediasize_iso_c3" msgid="835154173518304159">"ISO C3"</string>
    <string name="mediasize_iso_c4" msgid="5095951985108194011">"ISO C4"</string>
    <string name="mediasize_iso_c5" msgid="1985397450332305739">"ISO C5"</string>
    <string name="mediasize_iso_c6" msgid="8147421924174693013">"ISO C6"</string>
    <string name="mediasize_iso_c7" msgid="8993994925276122950">"ISO C7"</string>
    <string name="mediasize_iso_c8" msgid="6871178104139598957">"ISO C8"</string>
    <string name="mediasize_iso_c9" msgid="7983532635227561362">"ISO C9"</string>
    <string name="mediasize_iso_c10" msgid="5040764293406765584">"ISO C10"</string>
    <string name="mediasize_na_letter" msgid="2841414839888344296">"Letter"</string>
    <string name="mediasize_na_gvrnmt_letter" msgid="5295836838862962809">"Government Letter"</string>
    <string name="mediasize_na_legal" msgid="8621364037680465666">"Legal"</string>
    <string name="mediasize_na_junior_legal" msgid="3309324162155085904">"Junior Legal"</string>
    <string name="mediasize_na_ledger" msgid="5567030340509075333">"Ledger"</string>
    <string name="mediasize_na_tabloid" msgid="4571735038501661757">"Tabloid"</string>
    <string name="mediasize_na_index_3x5" msgid="5182901917818625126">"Index Card 3x5"</string>
    <string name="mediasize_na_index_4x6" msgid="7687620625422312396">"Index Card 4x6"</string>
    <string name="mediasize_na_index_5x8" msgid="8834215284646872800">"Index Card 5x8"</string>
    <string name="mediasize_na_monarch" msgid="213639906956550754">"Monarch"</string>
    <string name="mediasize_na_quarto" msgid="835778493593023223">"Quarto"</string>
    <string name="mediasize_na_foolscap" msgid="1573911237983677138">"Foolscap"</string>
    <string name="mediasize_chinese_roc_8k" msgid="3626855847189438896">"ROC 8K"</string>
    <string name="mediasize_chinese_roc_16k" msgid="9182191577022943355">"ROC 16K"</string>
    <string name="mediasize_chinese_prc_1" msgid="4793232644980170500">"PRC 1"</string>
    <string name="mediasize_chinese_prc_2" msgid="5404109730975720670">"PRC 2"</string>
    <string name="mediasize_chinese_prc_3" msgid="1335092253339363526">"PRC 3"</string>
    <string name="mediasize_chinese_prc_4" msgid="9167997800486569834">"PRC 4"</string>
    <string name="mediasize_chinese_prc_5" msgid="845875168823541497">"PRC 5"</string>
    <string name="mediasize_chinese_prc_6" msgid="3220325667692648789">"PRC 6"</string>
    <string name="mediasize_chinese_prc_7" msgid="1776792138507038527">"PRC 7"</string>
    <string name="mediasize_chinese_prc_8" msgid="1417176642687456692">"PRC 8"</string>
    <string name="mediasize_chinese_prc_9" msgid="4785983473123798365">"PRC 9"</string>
    <string name="mediasize_chinese_prc_10" msgid="7847982299391851899">"PRC 10"</string>
    <string name="mediasize_chinese_prc_16k" msgid="262793383539980677">"PRC 16K"</string>
    <string name="mediasize_chinese_om_pa_kai" msgid="5256815579447959814">"8开"</string>
    <string name="mediasize_chinese_om_dai_pa_kai" msgid="7336412963441354407">"大8开"</string>
    <string name="mediasize_chinese_om_jurro_ku_kai" msgid="6324465444100490742">"16开"</string>
    <string name="mediasize_japanese_jis_b10" msgid="1787262845627694376">"JIS B10"</string>
    <string name="mediasize_japanese_jis_b9" msgid="3336035783663287470">"JIS B9"</string>
    <string name="mediasize_japanese_jis_b8" msgid="6195398299104345731">"JIS B8"</string>
    <string name="mediasize_japanese_jis_b7" msgid="1674621886902828884">"JIS B7"</string>
    <string name="mediasize_japanese_jis_b6" msgid="4170576286062657435">"JIS B6"</string>
    <string name="mediasize_japanese_jis_b5" msgid="4899297958100032533">"JIS B5"</string>
    <string name="mediasize_japanese_jis_b4" msgid="4213158129126666847">"JIS B4"</string>
    <string name="mediasize_japanese_jis_b3" msgid="8513715307410310696">"JIS B3"</string>
    <string name="mediasize_japanese_jis_b2" msgid="4777690211897131190">"JIS B2"</string>
    <string name="mediasize_japanese_jis_b1" msgid="4608142385457034603">"JIS B1"</string>
    <string name="mediasize_japanese_jis_b0" msgid="7587108366572243991">"JIS B0"</string>
    <string name="mediasize_japanese_jis_exec" msgid="5244075432263649068">"JIS Exec"</string>
    <string name="mediasize_japanese_chou4" msgid="4941652015032631361">"Chou4"</string>
    <string name="mediasize_japanese_chou3" msgid="6387319169263957010">"Chou3"</string>
    <string name="mediasize_japanese_chou2" msgid="1299112025415343982">"Chou2"</string>
    <string name="mediasize_japanese_hagaki" msgid="8070115620644254565">"Hagaki"</string>
    <string name="mediasize_japanese_oufuku" msgid="6049065587307896564">"Oufuku"</string>
    <string name="mediasize_japanese_kahu" msgid="6872696027560065173">"Kahu"</string>
    <string name="mediasize_japanese_kaku2" msgid="2359077233775455405">"Kaku2"</string>
    <string name="mediasize_japanese_you4" msgid="2091777168747058008">"You4"</string>
    <string name="mediasize_unknown_portrait" msgid="3088043641616409762">"未知（纵向）"</string>
    <string name="mediasize_unknown_landscape" msgid="4876995327029361552">"未知（横向）"</string>
    <string name="write_fail_reason_cancelled" msgid="7091258378121627624">"已取消"</string>
    <string name="write_fail_reason_cannot_write" msgid="8132505417935337724">"写入内容时出错"</string>
    <string name="reason_unknown" msgid="6048913880184628119">"未知"</string>
    <string name="reason_service_unavailable" msgid="7824008732243903268">"未启用打印服务"</string>
    <string name="print_service_installed_title" msgid="2246317169444081628">"已安装“<xliff:g id="NAME">%s</xliff:g>”服务"</string>
    <string name="print_service_installed_message" msgid="5897362931070459152">"点按即可启用"</string>
    <string name="restr_pin_enter_admin_pin" msgid="8641662909467236832">"请输入管理员 PIN 码"</string>
    <string name="restr_pin_enter_pin" msgid="3395953421368476103">"输入PIN码"</string>
    <string name="restr_pin_incorrect" msgid="8571512003955077924">"错误"</string>
    <string name="restr_pin_enter_old_pin" msgid="1462206225512910757">"当前PIN码"</string>
    <string name="restr_pin_enter_new_pin" msgid="5959606691619959184">"新PIN码"</string>
    <string name="restr_pin_confirm_pin" msgid="8501523829633146239">"确认新PIN码"</string>
    <string name="restr_pin_create_pin" msgid="8017600000263450337">"设置PIN码，防止他人修改限制条件"</string>
    <string name="restr_pin_error_doesnt_match" msgid="2224214190906994548">"PIN码不符，请重试。"</string>
    <string name="restr_pin_error_too_short" msgid="8173982756265777792">"PIN码太短，至少应包含4位数字。"</string>
    <plurals name="restr_pin_countdown" formatted="false" msgid="9061246974881224688">
      <item quantity="other"><xliff:g id="COUNT">%d</xliff:g> 秒后重试</item>
      <item quantity="one">1 秒后重试</item>
    </plurals>
    <string name="restr_pin_try_later" msgid="973144472490532377">"稍后重试"</string>
    <string name="immersive_cling_title" msgid="8394201622932303336">"目前处于全屏模式"</string>
    <string name="immersive_cling_description" msgid="3482371193207536040">"要退出，请从顶部向下滑动。"</string>
    <string name="immersive_cling_positive" msgid="5016839404568297683">"知道了"</string>
    <string name="done_label" msgid="2093726099505892398">"完成"</string>
    <string name="hour_picker_description" msgid="6698199186859736512">"小时转盘"</string>
    <string name="minute_picker_description" msgid="8606010966873791190">"分钟转盘"</string>
    <string name="select_hours" msgid="6043079511766008245">"选择小时"</string>
    <string name="select_minutes" msgid="3974345615920336087">"选择分钟"</string>
    <string name="select_day" msgid="7774759604701773332">"选择月份和日期"</string>
    <string name="select_year" msgid="7952052866994196170">"选择年份"</string>
    <string name="deleted_key" msgid="7659477886625566590">"已删除<xliff:g id="KEY">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge" msgid="2355652472854327647">"工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge_2" msgid="5048136430082124036">"第二个工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="managed_profile_label_badge_3" msgid="2808305070321719040">"第三个工作<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="lock_to_app_unlock_pin" msgid="2552556656504331634">"取消时要求输入PIN码"</string>
    <string name="lock_to_app_unlock_pattern" msgid="4182192144797225137">"取消时要求绘制解锁图案"</string>
    <string name="lock_to_app_unlock_password" msgid="6380979775916974414">"取消时要求输入密码"</string>
    <string name="package_installed_device_owner" msgid="6875717669960212648">"已由您的管理员安装"</string>
    <string name="package_updated_device_owner" msgid="1847154566357862089">"已由您的管理员更新"</string>
    <string name="package_deleted_device_owner" msgid="2307122077550236438">"已由您的管理员删除"</string>
    <string name="battery_saver_description_with_learn_more" msgid="6323937147992667707">"为了延长电池续航时间，省电模式会关闭部分设备功能并限制应用。"<annotation id="url">"了解详情"</annotation></string>
    <string name="battery_saver_description" msgid="769989536172631582">"为了延长电池续航时间，省电模式会关闭部分设备功能并限制应用。"</string>
    <string name="data_saver_description" msgid="6015391409098303235">"为了减少流量消耗，流量节省程序会阻止某些应用在后台收发数据。您当前使用的应用可以收发数据，但频率可能会降低。举例而言，这可能意味着图片只有在您点按之后才会显示。"</string>
    <string name="data_saver_enable_title" msgid="4674073932722787417">"要开启流量节省程序吗？"</string>
    <string name="data_saver_enable_button" msgid="7147735965247211818">"开启"</string>
    <plurals name="zen_mode_duration_minutes_summary" formatted="false" msgid="4367877408072000848">
      <item quantity="other">%1$d 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes_summary_short" formatted="false" msgid="6830154222366042597">
      <item quantity="other">%1$d 分钟（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>)）</item>
      <item quantity="one">1 分钟（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_summary" formatted="false" msgid="736789408293052283">
      <item quantity="other">%1$d 小时（直到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 小时（直到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_summary_short" formatted="false" msgid="4787552595253082371">
      <item quantity="other">%1$d 小时（到<xliff:g id="FORMATTEDTIME_1">%2$s</xliff:g>）</item>
      <item quantity="one">1 小时（到<xliff:g id="FORMATTEDTIME_0">%2$s</xliff:g>）</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes" formatted="false" msgid="5127407202506485571">
      <item quantity="other">%d 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="zen_mode_duration_minutes_short" formatted="false" msgid="2199350154433426128">
      <item quantity="other">%d 分钟</item>
      <item quantity="one">1 分钟</item>
    </plurals>
    <plurals name="zen_mode_duration_hours" formatted="false" msgid="6571961796799076730">
      <item quantity="other">%d 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <plurals name="zen_mode_duration_hours_short" formatted="false" msgid="6748277774662434217">
      <item quantity="other">%d 小时</item>
      <item quantity="one">1 小时</item>
    </plurals>
    <string name="zen_mode_until" msgid="7336308492289875088">"到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>"</string>
    <string name="zen_mode_alarm" msgid="9128205721301330797">"直到<xliff:g id="FORMATTEDTIME">%1$s</xliff:g>（闹钟下次响铃时）"</string>
    <string name="zen_mode_forever" msgid="931849471004038757">"直到您将其关闭"</string>
    <string name="zen_mode_forever_dnd" msgid="3792132696572189081">"直到您关闭“勿扰”模式"</string>
    <string name="zen_mode_rule_name_combination" msgid="191109939968076477">"<xliff:g id="FIRST">%1$s</xliff:g> / <xliff:g id="REST">%2$s</xliff:g>"</string>
    <string name="toolbar_collapse_description" msgid="2821479483960330739">"收起"</string>
    <string name="zen_mode_feature_name" msgid="5254089399895895004">"勿扰"</string>
    <string name="zen_mode_downtime_feature_name" msgid="2626974636779860146">"休息时间"</string>
    <string name="zen_mode_default_weeknights_name" msgid="3081318299464998143">"周一至周五夜间"</string>
    <string name="zen_mode_default_weekends_name" msgid="2786495801019345244">"周末"</string>
    <string name="zen_mode_default_events_name" msgid="8158334939013085363">"活动"</string>
    <string name="zen_mode_default_every_night_name" msgid="3012363838882944175">"睡眠"</string>
    <string name="muted_by" msgid="5942954724562097128">"<xliff:g id="THIRD_PARTY">%1$s</xliff:g>正在将某些音效设为静音"</string>
    <string name="system_error_wipe_data" msgid="6608165524785354962">"您的设备内部出现了问题。如果不将设备恢复出厂设置，设备运行可能会不稳定。"</string>
    <string name="system_error_manufacturer" msgid="8086872414744210668">"您的设备内部出现了问题。请联系您的设备制造商了解详情。"</string>
    <string name="stk_cc_ussd_to_dial" msgid="5214333646366591205">"USSD 请求已更改为普通通话"</string>
    <string name="stk_cc_ussd_to_ss" msgid="4884994189414782605">"USSD 请求已更改为 SS 请求"</string>
    <string name="stk_cc_ussd_to_ussd" msgid="5728637484565449312">"已更改为新的 USSD 请求"</string>
    <string name="stk_cc_ussd_to_dial_video" msgid="4134455726513175559">"USSD 请求已更改为视频通话"</string>
    <string name="stk_cc_ss_to_dial" msgid="1360775164651754978">"SS 请求已更改为普通通话"</string>
    <string name="stk_cc_ss_to_dial_video" msgid="6577956662913194947">"SS 请求已更改为视频通话"</string>
    <string name="stk_cc_ss_to_ussd" msgid="5614626512855868785">"SS 请求已更改为 USSD 请求"</string>
    <string name="stk_cc_ss_to_ss" msgid="7716729801537709054">"已更改为新的 SS 请求"</string>
    <string name="notification_work_profile_content_description" msgid="4600554564103770764">"工作资料"</string>
    <string name="expand_button_content_description_collapsed" msgid="3609784019345534652">"展开"</string>
    <string name="expand_button_content_description_expanded" msgid="8520652707158554895">"收起"</string>
    <string name="expand_action_accessibility" msgid="5307730695723718254">"切换展开模式"</string>
    <string name="usb_midi_peripheral_name" msgid="7221113987741003817">"Android USB 外设端口"</string>
    <string name="usb_midi_peripheral_manufacturer_name" msgid="7176526170008970168">"Android"</string>
    <string name="usb_midi_peripheral_product_name" msgid="4971827859165280403">"USB 外设端口"</string>
    <string name="floating_toolbar_open_overflow_description" msgid="4797287862999444631">"更多选项"</string>
    <string name="floating_toolbar_close_overflow_description" msgid="559796923090723804">"关闭工具栏溢出"</string>
    <string name="maximize_button_text" msgid="7543285286182446254">"最大化"</string>
    <string name="close_button_text" msgid="3937902162644062866">"关闭"</string>
    <string name="notification_messaging_title_template" msgid="3452480118762691020">"<xliff:g id="CONVERSATION_TITLE">%1$s</xliff:g>：<xliff:g id="SENDER_NAME">%2$s</xliff:g>"</string>
    <plurals name="selected_count" formatted="false" msgid="7187339492915744615">
      <item quantity="other">已选择 <xliff:g id="COUNT_1">%1$d</xliff:g> 项</item>
      <item quantity="one">已选择 <xliff:g id="COUNT_0">%1$d</xliff:g> 项</item>
    </plurals>
    <string name="default_notification_channel_label" msgid="5929663562028088222">"未分类"</string>
    <string name="importance_from_user" msgid="7318955817386549931">"这些通知的重要程度由您来设置。"</string>
    <string name="importance_from_person" msgid="9160133597262938296">"这条通知涉及特定的人，因此被归为重要通知。"</string>
    <string name="user_creation_account_exists" msgid="1942606193570143289">"允许<xliff:g id="APP">%1$s</xliff:g>使用 <xliff:g id="ACCOUNT">%2$s</xliff:g> 创建新用户吗？"</string>
    <string name="user_creation_adding" msgid="4482658054622099197">"允许<xliff:g id="APP">%1$s</xliff:g>使用 <xliff:g id="ACCOUNT">%2$s</xliff:g>（目前已有用户使用此帐号）创建新用户吗？"</string>
    <string name="language_selection_title" msgid="2680677278159281088">"添加语言"</string>
    <string name="country_selection_title" msgid="2954859441620215513">"区域偏好设置"</string>
    <string name="search_language_hint" msgid="7042102592055108574">"输入语言名称"</string>
    <string name="language_picker_section_suggested" msgid="8414489646861640885">"建议语言"</string>
    <string name="language_picker_section_all" msgid="3097279199511617537">"所有语言"</string>
    <string name="region_picker_section_all" msgid="8966316787153001779">"所有国家/地区"</string>
    <string name="locale_search_menu" msgid="2560710726687249178">"搜索"</string>
    <string name="app_suspended_title" msgid="2075071241147969611">"应用无法使用"</string>
    <string name="app_suspended_default_message" msgid="123166680425711887">"<xliff:g id="APP_NAME_0">%1$s</xliff:g>目前无法使用。该应用是由<xliff:g id="APP_NAME_1">%2$s</xliff:g>所管理。"</string>
    <string name="app_suspended_more_details" msgid="1131804827776778187">"了解详情"</string>
    <string name="work_mode_off_title" msgid="1118691887588435530">"要开启工作资料吗？"</string>
    <string name="work_mode_off_message" msgid="5130856710614337649">"您的工作应用、通知、数据及其他工作资料功能将会开启"</string>
    <string name="work_mode_turn_on" msgid="2062544985670564875">"开启"</string>
    <string name="deprecated_target_sdk_message" msgid="1449696506742572767">"此应用专为旧版 Android 打造，因此可能无法正常运行。请尝试检查更新或与开发者联系。"</string>
    <string name="deprecated_target_sdk_app_store" msgid="5032340500368495077">"检查更新"</string>
    <string name="new_sms_notification_title" msgid="8442817549127555977">"您有新消息"</string>
    <string name="new_sms_notification_content" msgid="7002938807812083463">"打开短信应用查看"</string>
    <string name="user_encrypted_title" msgid="9054897468831672082">"部分功能可能会受到限制"</string>
    <string name="user_encrypted_message" msgid="4923292604515744267">"点按即可解锁"</string>
    <string name="user_encrypted_detail" msgid="5708447464349420392">"用户数据已锁定"</string>
    <string name="profile_encrypted_detail" msgid="3700965619978314974">"工作资料已锁定"</string>
    <string name="profile_encrypted_message" msgid="6964994232310195874">"点按即可解锁工作资料"</string>
    <string name="usb_mtp_launch_notification_title" msgid="8359219638312208932">"已连接到<xliff:g id="PRODUCT_NAME">%1$s</xliff:g>"</string>
    <string name="usb_mtp_launch_notification_description" msgid="8541876176425411358">"点按即可查看文件"</string>
    <string name="pin_target" msgid="3052256031352291362">"固定"</string>
    <string name="unpin_target" msgid="3556545602439143442">"取消固定"</string>
    <string name="app_info" msgid="6856026610594615344">"应用信息"</string>
    <string name="negative_duration" msgid="5688706061127375131">"−<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="demo_starting_message" msgid="5268556852031489931">"正在启动演示模式…"</string>
    <string name="demo_restarting_message" msgid="952118052531642451">"正在重置设备…"</string>
    <string name="suspended_widget_accessibility" msgid="6712143096475264190">"已停用的<xliff:g id="LABEL">%1$s</xliff:g>"</string>
    <string name="conference_call" msgid="3751093130790472426">"电话会议"</string>
    <string name="tooltip_popup_title" msgid="5253721848739260181">"提示"</string>
    <string name="app_category_game" msgid="5431836943981492993">"游戏"</string>
    <string name="app_category_audio" msgid="1659853108734301647">"音乐和音频"</string>
    <string name="app_category_video" msgid="2728726078629384196">"电影和视频"</string>
    <string name="app_category_image" msgid="4867854544519846048">"照片和图片"</string>
    <string name="app_category_social" msgid="5842783057834965912">"社交和通信"</string>
    <string name="app_category_news" msgid="7496506240743986873">"新闻和杂志"</string>
    <string name="app_category_maps" msgid="5878491404538024367">"地图和导航"</string>
    <string name="app_category_productivity" msgid="3742083261781538852">"办公"</string>
    <string name="device_storage_monitor_notification_channel" msgid="3295871267414816228">"设备存储空间"</string>
    <string name="adb_debugging_notification_channel_tv" msgid="5537766997350092316">"USB 调试"</string>
    <string name="time_picker_hour_label" msgid="2979075098868106450">"点"</string>
    <string name="time_picker_minute_label" msgid="5168864173796598399">"分"</string>
    <string name="time_picker_header_text" msgid="143536825321922567">"设置时间"</string>
    <string name="time_picker_input_error" msgid="7574999942502513765">"请输入有效的时间"</string>
    <string name="time_picker_prompt_label" msgid="7588093983899966783">"请输入时间"</string>
    <string name="time_picker_text_input_mode_description" msgid="4148166758173708199">"切换到文字输入模式来输入时间。"</string>
    <string name="time_picker_radial_mode_description" msgid="4953403779779557198">"切换到时钟模式来输入时间。"</string>
    <string name="autofill_picker_accessibility_title" msgid="8469043291648711535">"自动填充选项"</string>
    <string name="autofill_save_accessibility_title" msgid="7244365268417107822">"保存以便用于自动填充"</string>
    <string name="autofill_error_cannot_autofill" msgid="7402758580060110371">"无法自动填充内容"</string>
    <string name="autofill_picker_no_suggestions" msgid="3908514303773350735">"没有自动填充建议"</string>
    <plurals name="autofill_picker_some_suggestions" formatted="false" msgid="5506565809835815274">
      <item quantity="other"><xliff:g id="COUNT">%1$s</xliff:g> 条自动填充建议</item>
      <item quantity="one">1 条自动填充建议</item>
    </plurals>
    <string name="autofill_save_title" msgid="3345527308992082601">"要保存到&lt;b&gt;<xliff:g id="LABEL">%1$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_type" msgid="8637809388029313305">"要将<xliff:g id="TYPE">%1$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%2$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_2types" msgid="5214035651838265325">"要将<xliff:g id="TYPE_0">%1$s</xliff:g>和<xliff:g id="TYPE_1">%2$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%3$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_title_with_3types" msgid="6943161834231458441">"要将<xliff:g id="TYPE_0">%1$s</xliff:g>、<xliff:g id="TYPE_1">%2$s</xliff:g>和<xliff:g id="TYPE_2">%3$s</xliff:g>保存到&lt;b&gt;<xliff:g id="LABEL">%4$s</xliff:g>&lt;/b&gt;吗？"</string>
    <string name="autofill_save_yes" msgid="6398026094049005921">"保存"</string>
    <string name="autofill_save_no" msgid="2625132258725581787">"不用了"</string>
    <string name="autofill_save_type_password" msgid="5288448918465971568">"密码"</string>
    <string name="autofill_save_type_address" msgid="4936707762193009542">"地址"</string>
    <string name="autofill_save_type_credit_card" msgid="7127694776265563071">"信用卡"</string>
    <string name="autofill_save_type_username" msgid="239040540379769562">"用户名"</string>
    <string name="autofill_save_type_email_address" msgid="5752949432129262174">"电子邮件地址"</string>
    <string name="etws_primary_default_message_earthquake" msgid="5541962250262769193">"请保持冷静，并寻找附近的避难地点。"</string>
    <string name="etws_primary_default_message_tsunami" msgid="1887685943498368548">"请立即从沿海和河滨区域撤离到高地等较安全的地方。"</string>
    <string name="etws_primary_default_message_earthquake_and_tsunami" msgid="998797956848445862">"请保持冷静，并寻找附近的避难地点。"</string>
    <string name="etws_primary_default_message_test" msgid="2709597093560037455">"紧急消息测试"</string>
    <string name="notification_reply_button_accessibility" msgid="3621714652387814344">"回复"</string>
    <string name="etws_primary_default_message_others" msgid="6293148756130398971"></string>
    <string name="mmcc_authentication_reject" msgid="5767701075994754356">"SIM 卡不支持语音"</string>
    <string name="mmcc_imsi_unknown_in_hlr" msgid="5316658473301462825">"未配置支持语音的 SIM 卡"</string>
    <string name="mmcc_illegal_ms" msgid="807334478177362062">"SIM 卡不支持语音"</string>
    <string name="mmcc_illegal_me" msgid="1950705155760872972">"手机不支持语音"</string>
    <string name="mmcc_authentication_reject_msim_template" msgid="1217031195834766479">"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_imsi_unknown_in_hlr_msim_template" msgid="5636464607596778986">"未配置 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_illegal_ms_msim_template" msgid="5994323296399913454">"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="mmcc_illegal_me_msim_template" msgid="5550259730350571826">"不允许使用 SIM 卡 <xliff:g id="SIMNUMBER">%d</xliff:g>"</string>
    <string name="popup_window_default_title" msgid="4874318849712115433">"弹出式窗口"</string>
    <string name="slice_more_content" msgid="8504342889413274608">"+ <xliff:g id="NUMBER">%1$d</xliff:g>"</string>
    <string name="shortcut_restored_on_lower_version" msgid="4860853725206702336">"应用版本已降级或与此快捷方式不兼容"</string>
    <string name="shortcut_restore_not_supported" msgid="5028808567940014190">"无法恢复快捷方式，因为应用不支持备份和恢复功能"</string>
    <string name="shortcut_restore_signature_mismatch" msgid="2406209324521327518">"无法恢复快捷方式，因为应用签名不相符"</string>
    <string name="shortcut_restore_unknown_issue" msgid="8703738064603262597">"无法恢复快捷方式"</string>
    <string name="shortcut_disabled_reason_unknown" msgid="5276016910284687075">"快捷方式已停用"</string>
    <string name="harmful_app_warning_uninstall" msgid="4837672735619532931">"卸载"</string>
    <string name="harmful_app_warning_open_anyway" msgid="596432803680914321">"仍然打开"</string>
    <string name="harmful_app_warning_title" msgid="8982527462829423432">"检测到有害应用"</string>
    <string name="slices_permission_request" msgid="8484943441501672932">"“<xliff:g id="APP_0">%1$s</xliff:g>”想要显示“<xliff:g id="APP_2">%2$s</xliff:g>”图块"</string>
    <string name="screenshot_edit" msgid="7867478911006447565">"编辑"</string>
    <string name="volume_dialog_ringer_guidance_vibrate" msgid="8902050240801159042">"有来电和通知时会振动"</string>
    <string name="volume_dialog_ringer_guidance_silent" msgid="2128975224280276122">"有来电和通知时会静音"</string>
    <string name="notification_channel_system_changes" msgid="5072715579030948646">"系统变更"</string>
    <string name="notification_channel_do_not_disturb" msgid="6766940333105743037">"勿扰"</string>
    <string name="zen_upgrade_notification_visd_title" msgid="3288313883409759733">"新功能：勿扰模式目前可隐藏通知"</string>
    <string name="zen_upgrade_notification_visd_content" msgid="5533674060311631165">"点按即可了解详情以及进行更改。"</string>
    <string name="zen_upgrade_notification_title" msgid="3799603322910377294">"“勿扰”设置有变更"</string>
    <string name="zen_upgrade_notification_content" msgid="1794994264692424562">"点按即可查看屏蔽内容。"</string>
    <string name="notification_app_name_system" msgid="4205032194610042794">"系统"</string>
    <string name="notification_app_name_settings" msgid="7751445616365753381">"设置"</string>
    <string name="notification_appops_camera_active" msgid="5050283058419699771">"相机"</string>
    <string name="notification_appops_microphone_active" msgid="4335305527588191730">"麦克风"</string>
    <string name="notification_appops_overlay_active" msgid="633813008357934729">"显示在屏幕上其他应用的上层"</string>
    <string name="car_loading_profile" msgid="3545132581795684027">"正在加载"</string>
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
    <string name="app_label" msgid="7164937344850004466">"系统界面"</string>
    <string name="status_bar_clear_all_button" msgid="7774721344716731603">"清除"</string>
    <string name="status_bar_recent_remove_item_title" msgid="6026395868129852968">"从列表中删除"</string>
    <string name="status_bar_recent_inspect_item_title" msgid="7793624864528818569">"应用信息"</string>
    <string name="status_bar_no_recent_apps" msgid="7374907845131203189">"您最近浏览过的屏幕会显示在此处"</string>
    <string name="status_bar_accessibility_dismiss_recents" msgid="4576076075226540105">"关闭最近运行的应用"</string>
    <plurals name="status_bar_accessibility_recent_apps" formatted="false" msgid="9138535907802238759">
      <item quantity="other">概览中有 %d 个屏幕</item>
      <item quantity="one">概览中有 1 个屏幕</item>
    </plurals>
    <string name="status_bar_no_notifications_title" msgid="4755261167193833213">"无通知"</string>
    <string name="status_bar_ongoing_events_title" msgid="1682504513316879202">"正在进行的"</string>
    <string name="status_bar_latest_events_title" msgid="6594767438577593172">"通知"</string>
    <string name="battery_low_title" msgid="9187898087363540349">"电池电量可能很快就要耗尽"</string>
    <string name="battery_low_percent_format" msgid="2900940511201380775">"剩余<xliff:g id="PERCENTAGE">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_hybrid" msgid="6838677459286775617">"剩余电量：<xliff:g id="PERCENTAGE">%s</xliff:g>；根据您的使用情况，大约还可使用 <xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_hybrid_short" msgid="9025795469949145586">"剩余电量：<xliff:g id="PERCENTAGE">%s</xliff:g>；大约还可使用 <xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="battery_low_percent_format_saver_started" msgid="7879389868952879166">"剩余 <xliff:g id="PERCENTAGE">%s</xliff:g>。省电模式已开启。"</string>
    <string name="invalid_charger" msgid="2741987096648693172">"无法通过 USB 充电。请使用设备随附的充电器。"</string>
    <string name="invalid_charger_title" msgid="2836102177577255404">"无法通过 USB 充电"</string>
    <string name="invalid_charger_text" msgid="6480624964117840005">"使用设备随附的充电器"</string>
    <string name="battery_low_why" msgid="4553600287639198111">"设置"</string>
    <string name="battery_saver_confirmation_title" msgid="2052100465684817154">"要开启省电模式吗？"</string>
    <string name="battery_saver_confirmation_ok" msgid="7507968430447930257">"开启"</string>
    <string name="battery_saver_start_action" msgid="8187820911065797519">"开启省电模式"</string>
    <string name="status_bar_settings_settings_button" msgid="3023889916699270224">"设置"</string>
    <string name="status_bar_settings_wifi_button" msgid="1733928151698311923">"WLAN"</string>
    <string name="status_bar_settings_auto_rotation" msgid="3790482541357798421">"自动旋转屏幕"</string>
    <string name="status_bar_settings_mute_label" msgid="554682549917429396">"静音"</string>
    <string name="status_bar_settings_auto_brightness_label" msgid="511453614962324674">"自动"</string>
    <string name="status_bar_settings_notifications" msgid="397146176280905137">"通知"</string>
    <string name="bluetooth_tethered" msgid="7094101612161133267">"已通过蓝牙共享网络"</string>
    <string name="status_bar_input_method_settings_configure_input_methods" msgid="3504292471512317827">"设置输入法"</string>
    <string name="status_bar_use_physical_keyboard" msgid="7551903084416057810">"物理键盘"</string>
    <string name="usb_device_permission_prompt" msgid="1825685909587559679">"要允许<xliff:g id="APPLICATION">%1$s</xliff:g>访问<xliff:g id="USB_DEVICE">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_permission_prompt" msgid="2465531696941369047">"要允许<xliff:g id="APPLICATION">%1$s</xliff:g>访问<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>吗？"</string>
    <string name="usb_device_confirm_prompt" msgid="7440562274256843905">"要打开<xliff:g id="APPLICATION">%1$s</xliff:g>来处理<xliff:g id="USB_DEVICE">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_confirm_prompt" msgid="4333670517539993561">"要打开<xliff:g id="APPLICATION">%1$s</xliff:g>来处理<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>吗？"</string>
    <string name="usb_accessory_uri_prompt" msgid="513450621413733343">"未安装此USB配件适用的应用。要了解此配件的详情，请访问：<xliff:g id="URL">%1$s</xliff:g>"</string>
    <string name="title_usb_accessory" msgid="4966265263465181372">"USB配件"</string>
    <string name="label_view" msgid="6304565553218192990">"查看"</string>
    <string name="always_use_device" msgid="4015357883336738417">"连接<xliff:g id="USB_DEVICE">%2$s</xliff:g>后一律打开<xliff:g id="APPLICATION">%1$s</xliff:g>"</string>
    <string name="always_use_accessory" msgid="3257892669444535154">"连接<xliff:g id="USB_ACCESSORY">%2$s</xliff:g>后一律打开<xliff:g id="APPLICATION">%1$s</xliff:g>"</string>
    <string name="usb_debugging_title" msgid="4513918393387141949">"允许 USB 调试吗？"</string>
    <string name="usb_debugging_message" msgid="2220143855912376496">"这台计算机的 RSA 密钥指纹如下：\n<xliff:g id="FINGERPRINT">%1$s</xliff:g>"</string>
    <string name="usb_debugging_always" msgid="303335496705863070">"一律允许使用这台计算机进行调试"</string>
    <string name="usb_debugging_secondary_user_title" msgid="6353808721761220421">"不允许使用 USB 调试功能"</string>
    <string name="usb_debugging_secondary_user_message" msgid="6067122453571699801">"目前已登录此设备的用户无法开启 USB 调试功能。要使用此功能，请切换为主要用户的帐号。"</string>
    <string name="compat_mode_on" msgid="6623839244840638213">"缩放以填满屏幕"</string>
    <string name="compat_mode_off" msgid="4434467572461327898">"拉伸以填满屏幕"</string>
    <string name="global_action_screenshot" msgid="8329831278085426283">"屏幕截图"</string>
    <string name="screenshot_saving_ticker" msgid="7403652894056693515">"正在保存屏幕截图..."</string>
    <string name="screenshot_saving_title" msgid="8242282144535555697">"正在保存屏幕截图..."</string>
    <string name="screenshot_saved_title" msgid="5637073968117370753">"已保存屏幕截图"</string>
    <string name="screenshot_saved_text" msgid="7574667448002050363">"点按即可查看您的屏幕截图"</string>
    <string name="screenshot_failed_title" msgid="7612509838919089748">"无法保存屏幕截图"</string>
    <string name="screenshot_failed_to_save_unknown_text" msgid="3637758096565605541">"请再次尝试截屏"</string>
    <string name="screenshot_failed_to_save_text" msgid="3041612585107107310">"由于存储空间有限，无法保存屏幕截图"</string>
    <string name="screenshot_failed_to_capture_text" msgid="173674476457581486">"此应用或您所在的单位不允许进行屏幕截图"</string>
    <string name="usb_preference_title" msgid="6551050377388882787">"USB文件传输选项"</string>
    <string name="use_mtp_button_title" msgid="4333504413563023626">"作为媒体播放器(MTP)装载"</string>
    <string name="use_ptp_button_title" msgid="7517127540301625751">"作为相机(PTP)装载"</string>
    <string name="installer_cd_button_title" msgid="2312667578562201583">"安装适用于 Mac 的 Android 文件传输应用"</string>
    <string name="accessibility_back" msgid="567011538994429120">"返回"</string>
    <string name="accessibility_home" msgid="8217216074895377641">"主屏幕"</string>
    <string name="accessibility_menu" msgid="316839303324695949">"菜单"</string>
    <string name="accessibility_accessibility_button" msgid="7601252764577607915">"无障碍"</string>
    <string name="accessibility_rotate_button" msgid="7402949513740253006">"旋转屏幕"</string>
    <string name="accessibility_recent" msgid="5208608566793607626">"概览"</string>
    <string name="accessibility_search_light" msgid="1103867596330271848">"搜索"</string>
    <string name="accessibility_camera_button" msgid="8064671582820358152">"相机"</string>
    <string name="accessibility_phone_button" msgid="6738112589538563574">"电话"</string>
    <string name="accessibility_voice_assist_button" msgid="487611083884852965">"语音助理"</string>
    <string name="accessibility_unlock_button" msgid="128158454631118828">"解锁"</string>
    <string name="accessibility_waiting_for_fingerprint" msgid="4808860050517462885">"正在等待提供指纹"</string>
    <string name="accessibility_unlock_without_fingerprint" msgid="7541705575183694446">"不使用指纹解锁"</string>
    <string name="accessibility_scanning_face" msgid="769545173211758586">"正在扫描面孔"</string>
    <string name="accessibility_send_smart_reply" msgid="7766727839703044493">"发送"</string>
    <string name="unlock_label" msgid="8779712358041029439">"解锁"</string>
    <string name="phone_label" msgid="2320074140205331708">"打开电话"</string>
    <string name="voice_assist_label" msgid="3956854378310019854">"打开语音助理"</string>
    <string name="camera_label" msgid="7261107956054836961">"打开相机"</string>
    <string name="recents_caption_resize" msgid="3517056471774958200">"选择新的任务布局"</string>
    <string name="cancel" msgid="6442560571259935130">"取消"</string>
    <string name="fingerprint_dialog_touch_sensor" msgid="8511557690663181761">"请触摸指纹传感器"</string>
    <string name="accessibility_fingerprint_dialog_fingerprint_icon" msgid="3125122495414253226">"指纹图标"</string>
    <string name="accessibility_fingerprint_dialog_app_icon" msgid="3228052542929174609">"应用图标"</string>
    <string name="accessibility_fingerprint_dialog_help_area" msgid="5730471601819225159">"帮助消息区域"</string>
    <string name="accessibility_compatibility_zoom_button" msgid="8461115318742350699">"兼容性缩放按钮。"</string>
    <string name="accessibility_compatibility_zoom_example" msgid="4220687294564945780">"将小屏幕的图片放大在较大屏幕上显示。"</string>
    <string name="accessibility_bluetooth_connected" msgid="2707027633242983370">"蓝牙已连接。"</string>
    <string name="accessibility_bluetooth_disconnected" msgid="7416648669976870175">"蓝牙连接已断开。"</string>
    <string name="accessibility_no_battery" msgid="358343022352820946">"没有电池。"</string>
    <string name="accessibility_battery_one_bar" msgid="7774887721891057523">"电池电量为一格。"</string>
    <string name="accessibility_battery_two_bars" msgid="8500650438735009973">"电池电量为两格。"</string>
    <string name="accessibility_battery_three_bars" msgid="2302983330865040446">"电池电量为三格。"</string>
    <string name="accessibility_battery_full" msgid="8909122401720158582">"电池电量满格。"</string>
    <string name="accessibility_no_phone" msgid="4894708937052611281">"没有手机信号。"</string>
    <string name="accessibility_phone_one_bar" msgid="687699278132664115">"手机信号强度为一格。"</string>
    <string name="accessibility_phone_two_bars" msgid="8384905382804815201">"手机信号强度为两格。"</string>
    <string name="accessibility_phone_three_bars" msgid="8521904843919971885">"手机信号强度为三格。"</string>
    <string name="accessibility_phone_signal_full" msgid="6471834868580757898">"手机信号满格。"</string>
    <string name="accessibility_no_data" msgid="4791966295096867555">"没有数据网络信号。"</string>
    <string name="accessibility_data_one_bar" msgid="1415625833238273628">"数据信号强度为一格。"</string>
    <string name="accessibility_data_two_bars" msgid="6166018492360432091">"数据信号强度为两格。"</string>
    <string name="accessibility_data_three_bars" msgid="9167670452395038520">"数据信号强度为三格。"</string>
    <string name="accessibility_data_signal_full" msgid="2708384608124519369">"数据信号满格。"</string>
    <string name="accessibility_wifi_name" msgid="7202151365171148501">"已连接到<xliff:g id="WIFI">%s</xliff:g>。"</string>
    <string name="accessibility_bluetooth_name" msgid="8441517146585531676">"已连接到<xliff:g id="BLUETOOTH">%s</xliff:g>。"</string>
    <string name="accessibility_cast_name" msgid="4026393061247081201">"已连接到 <xliff:g id="CAST">%s</xliff:g>。"</string>
    <string name="accessibility_no_wimax" msgid="4329180129727630368">"无 WiMAX 信号。"</string>
    <string name="accessibility_wimax_one_bar" msgid="4170994299011863648">"WiMAX 信号强度为一格。"</string>
    <string name="accessibility_wimax_two_bars" msgid="9176236858336502288">"WiMAX 信号强度为两格。"</string>
    <string name="accessibility_wimax_three_bars" msgid="6116551636752103927">"WiMAX 信号强度为三格。"</string>
    <string name="accessibility_wimax_signal_full" msgid="2768089986795579558">"WiMAX 信号满格。"</string>
    <string name="accessibility_ethernet_disconnected" msgid="5896059303377589469">"以太网已断开连接。"</string>
    <string name="accessibility_ethernet_connected" msgid="2692130313069182636">"以太网已连接。"</string>
    <string name="accessibility_no_signal" msgid="7064645320782585167">"无信号。"</string>
    <string name="accessibility_not_connected" msgid="6395326276213402883">"未连接。"</string>
    <string name="accessibility_zero_bars" msgid="3806060224467027887">"信号强度为零格。"</string>
    <string name="accessibility_one_bar" msgid="1685730113192081895">"信号强度为一格。"</string>
    <string name="accessibility_two_bars" msgid="6437363648385206679">"信号强度为两格。"</string>
    <string name="accessibility_three_bars" msgid="2648241415119396648">"信号强度为三格。"</string>
    <string name="accessibility_signal_full" msgid="9122922886519676839">"信号满格。"</string>
    <string name="accessibility_desc_on" msgid="2385254693624345265">"开启。"</string>
    <string name="accessibility_desc_off" msgid="6475508157786853157">"关闭。"</string>
    <string name="accessibility_desc_connected" msgid="8366256693719499665">"已连接。"</string>
    <string name="accessibility_desc_connecting" msgid="3812924520316280149">"正在连接。"</string>
    <string name="data_connection_gprs" msgid="7652872568358508452">"GPRS"</string>
    <string name="data_connection_hspa" msgid="1499615426569473562">"HSPA"</string>
    <string name="data_connection_3g" msgid="503045449315378373">"3G"</string>
    <string name="data_connection_3_5g" msgid="3164370985817123144">"H"</string>
    <string name="data_connection_3_5g_plus" msgid="4464630787664529264">"H+"</string>
    <string name="data_connection_4g" msgid="9139963475267449144">"4G"</string>
    <string name="data_connection_4g_plus" msgid="1148687201877800700">"4G+"</string>
    <string name="data_connection_lte" msgid="2694876797724028614">"LTE"</string>
    <string name="data_connection_lte_plus" msgid="3423013208570937424">"LTE+"</string>
    <string name="data_connection_cdma" msgid="8176597308239086780">"1X"</string>
    <string name="data_connection_roaming" msgid="6037232010953697354">"漫游"</string>
    <string name="data_connection_edge" msgid="871835227939216682">"EDGE"</string>
    <string name="accessibility_data_connection_wifi" msgid="2324496756590645221">"WLAN"</string>
    <string name="accessibility_no_sim" msgid="8274017118472455155">"无 SIM 卡。"</string>
    <string name="accessibility_cell_data" msgid="5326139158682385073">"移动数据"</string>
    <string name="accessibility_cell_data_on" msgid="5927098403452994422">"移动数据已开启"</string>
    <string name="cell_data_off_content_description" msgid="4356113230238585072">"移动数据网络已关闭"</string>
    <string name="cell_data_off" msgid="1051264981229902873">"关闭"</string>
    <string name="accessibility_bluetooth_tether" msgid="4102784498140271969">"蓝牙网络共享。"</string>
    <string name="accessibility_airplane_mode" msgid="834748999790763092">"飞行模式。"</string>
    <string name="accessibility_vpn_on" msgid="5993385083262856059">"VPN 已开启。"</string>
    <string name="accessibility_no_sims" msgid="3957997018324995781">"没有 SIM 卡。"</string>
    <string name="carrier_network_change_mode" msgid="8149202439957837762">"运营商网络正在更改"</string>
    <string name="accessibility_battery_details" msgid="7645516654955025422">"打开电量详情"</string>
    <string name="accessibility_battery_level" msgid="7451474187113371965">"电池电量为百分之 <xliff:g id="NUMBER">%d</xliff:g>。"</string>
    <string name="accessibility_battery_level_charging" msgid="1147587904439319646">"正在充电，已完成百分之<xliff:g id="BATTERY_PERCENTAGE">%d</xliff:g>。"</string>
    <string name="accessibility_settings_button" msgid="799583911231893380">"系统设置。"</string>
    <string name="accessibility_notifications_button" msgid="4498000369779421892">"通知。"</string>
    <string name="accessibility_overflow_action" msgid="5681882033274783311">"查看所有通知"</string>
    <string name="accessibility_remove_notification" msgid="3603099514902182350">"清除通知。"</string>
    <string name="accessibility_gps_enabled" msgid="3511469499240123019">"GPS已启用。"</string>
    <string name="accessibility_gps_acquiring" msgid="8959333351058967158">"正在获取GPS信号。"</string>
    <string name="accessibility_tty_enabled" msgid="4613200365379426561">"电传打字机已启用。"</string>
    <string name="accessibility_ringer_vibrate" msgid="666585363364155055">"振铃器振动。"</string>
    <string name="accessibility_ringer_silent" msgid="9061243307939135383">"振铃器静音。"</string>
    <!-- no translation found for accessibility_casting (6887382141726543668) -->
    <skip />
    <!-- no translation found for accessibility_work_mode (702887484664647430) -->
    <skip />
    <string name="accessibility_recents_item_will_be_dismissed" msgid="395770242498031481">"移除<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="accessibility_recents_item_dismissed" msgid="6803574935084867070">"已删除<xliff:g id="APP">%s</xliff:g>"</string>
    <string name="accessibility_recents_all_items_dismissed" msgid="4464697366179168836">"已关闭所有最近用过的应用。"</string>
    <string name="accessibility_recents_item_open_app_info" msgid="5107479759905883540">"打开<xliff:g id="APP">%s</xliff:g>应用信息。"</string>
    <string name="accessibility_recents_item_launched" msgid="7616039892382525203">"正在启动<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="accessibility_notification_dismissed" msgid="854211387186306927">"已关闭通知。"</string>
    <string name="accessibility_desc_notification_shade" msgid="4690274844447504208">"通知栏。"</string>
    <string name="accessibility_desc_quick_settings" msgid="6186378411582437046">"快捷设置。"</string>
    <string name="accessibility_desc_lock_screen" msgid="5625143713611759164">"锁定屏幕。"</string>
    <string name="accessibility_desc_settings" msgid="3417884241751434521">"设置"</string>
    <string name="accessibility_desc_recent_apps" msgid="4876900986661819788">"概览。"</string>
    <string name="accessibility_desc_work_lock" msgid="4288774420752813383">"工作锁定屏幕"</string>
    <string name="accessibility_desc_close" msgid="7479755364962766729">"关闭"</string>
    <string name="accessibility_quick_settings_wifi" msgid="5518210213118181692">"<xliff:g id="SIGNAL">%1$s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_wifi_changed_off" msgid="8716484460897819400">"WLAN已关闭。"</string>
    <string name="accessibility_quick_settings_wifi_changed_on" msgid="6440117170789528622">"WLAN已开启。"</string>
    <string name="accessibility_quick_settings_mobile" msgid="4876806564086241341">"移动数据连接：<xliff:g id="SIGNAL">%1$s</xliff:g>，<xliff:g id="TYPE">%2$s</xliff:g>，<xliff:g id="NETWORK">%3$s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_battery" msgid="1480931583381408972">"电池电量：<xliff:g id="STATE">%s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_airplane_off" msgid="7786329360056634412">"飞行模式关闭。"</string>
    <string name="accessibility_quick_settings_airplane_on" msgid="6406141469157599296">"飞行模式开启。"</string>
    <string name="accessibility_quick_settings_airplane_changed_off" msgid="66846307818850664">"飞行模式已关闭。"</string>
    <string name="accessibility_quick_settings_airplane_changed_on" msgid="8983005603505087728">"飞行模式已开启。"</string>
    <string name="accessibility_quick_settings_dnd_none_on" msgid="2960643943620637020">"完全静音"</string>
    <string name="accessibility_quick_settings_dnd_alarms_on" msgid="3357131899365865386">"仅限闹钟"</string>
    <string name="accessibility_quick_settings_dnd" msgid="6607873236717185815">"勿扰。"</string>
    <string name="accessibility_quick_settings_dnd_changed_off" msgid="898107593453022935">"已关闭勿扰模式。"</string>
    <string name="accessibility_quick_settings_dnd_changed_on" msgid="4483780856613561039">"已开启勿扰模式。"</string>
    <string name="accessibility_quick_settings_bluetooth" msgid="6341675755803320038">"蓝牙。"</string>
    <string name="accessibility_quick_settings_bluetooth_off" msgid="2133631372372064339">"蓝牙关闭。"</string>
    <string name="accessibility_quick_settings_bluetooth_on" msgid="7681999166216621838">"蓝牙开启。"</string>
    <string name="accessibility_quick_settings_bluetooth_connecting" msgid="6953242966685343855">"蓝牙连接中。"</string>
    <string name="accessibility_quick_settings_bluetooth_connected" msgid="4306637793614573659">"蓝牙已连接。"</string>
    <string name="accessibility_quick_settings_bluetooth_changed_off" msgid="2730003763480934529">"蓝牙已关闭。"</string>
    <string name="accessibility_quick_settings_bluetooth_changed_on" msgid="8722351798763206577">"蓝牙已开启。"</string>
    <string name="accessibility_quick_settings_location_off" msgid="5119080556976115520">"位置报告功能关闭。"</string>
    <string name="accessibility_quick_settings_location_on" msgid="5809937096590102036">"位置报告功能开启。"</string>
    <string name="accessibility_quick_settings_location_changed_off" msgid="8526845571503387376">"位置报告功能已关闭。"</string>
    <string name="accessibility_quick_settings_location_changed_on" msgid="339403053079338468">"位置报告功能已开启。"</string>
    <string name="accessibility_quick_settings_alarm" msgid="3959908972897295660">"闹钟已设置为：<xliff:g id="TIME">%s</xliff:g>。"</string>
    <string name="accessibility_quick_settings_close" msgid="3115847794692516306">"关闭面板。"</string>
    <string name="accessibility_quick_settings_more_time" msgid="3659274935356197708">"延长时间。"</string>
    <string name="accessibility_quick_settings_less_time" msgid="2404728746293515623">"缩短时间。"</string>
    <string name="accessibility_quick_settings_flashlight_off" msgid="4936432000069786988">"手电筒关闭。"</string>
    <string name="accessibility_quick_settings_flashlight_unavailable" msgid="8012811023312280810">"无法使用手电筒。"</string>
    <string name="accessibility_quick_settings_flashlight_on" msgid="2003479320007841077">"手电筒打开。"</string>
    <string name="accessibility_quick_settings_flashlight_changed_off" msgid="3303701786768224304">"手电筒已关闭。"</string>
    <string name="accessibility_quick_settings_flashlight_changed_on" msgid="6531793301533894686">"手电筒已打开。"</string>
    <string name="accessibility_quick_settings_color_inversion_changed_off" msgid="4406577213290173911">"颜色反转功能已关闭。"</string>
    <string name="accessibility_quick_settings_color_inversion_changed_on" msgid="6897462320184911126">"颜色反转功能已开启。"</string>
    <string name="accessibility_quick_settings_hotspot_changed_off" msgid="5004708003447561394">"移动热点已关闭。"</string>
    <string name="accessibility_quick_settings_hotspot_changed_on" msgid="2890951609226476206">"移动热点已开启。"</string>
    <string name="accessibility_casting_turned_off" msgid="1430668982271976172">"屏幕投射已停止。"</string>
    <string name="accessibility_quick_settings_work_mode_off" msgid="7045417396436552890">"工作模式关闭。"</string>
    <string name="accessibility_quick_settings_work_mode_on" msgid="7650588553988014341">"工作模式开启。"</string>
    <string name="accessibility_quick_settings_work_mode_changed_off" msgid="5605534876107300711">"工作模式已关闭。"</string>
    <string name="accessibility_quick_settings_work_mode_changed_on" msgid="249840330756998612">"工作模式已开启。"</string>
    <string name="accessibility_quick_settings_data_saver_changed_off" msgid="650231949881093289">"流量节省程序已关闭。"</string>
    <string name="accessibility_quick_settings_data_saver_changed_on" msgid="4218725402373934151">"流量节省程序已开启。"</string>
    <string name="accessibility_brightness" msgid="8003681285547803095">"屏幕亮度"</string>
    <string name="accessibility_ambient_display_charging" msgid="9084521679384069087">"正在充电"</string>
    <string name="data_usage_disabled_dialog_3g_title" msgid="5281770593459841889">"2G-3G 数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog_4g_title" msgid="1601769736881078016">"4G 数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog_mobile_title" msgid="6801382439018099779">"已暂停使用移动数据网络"</string>
    <string name="data_usage_disabled_dialog_title" msgid="3932437232199671967">"数据网络已暂停使用"</string>
    <string name="data_usage_disabled_dialog" msgid="4919541636934603816">"您的数据流量消耗已达到所设置的上限，因此已停用移动数据网络。\n\n如果您要继续使用移动数据网络，则可能需要支付相应的流量费用。"</string>
    <string name="data_usage_disabled_dialog_enable" msgid="1412395410306390593">"恢复"</string>
    <string name="gps_notification_searching_text" msgid="8574247005642736060">"正在搜索GPS"</string>
    <string name="gps_notification_found_text" msgid="4619274244146446464">"已通过GPS确定位置"</string>
    <string name="accessibility_location_active" msgid="2427290146138169014">"应用发出了有效位置信息请求"</string>
    <string name="accessibility_clear_all" msgid="5235938559247164925">"清除所有通知。"</string>
    <string name="notification_group_overflow_indicator" msgid="1863231301642314183">"+ <xliff:g id="NUMBER">%s</xliff:g>"</string>
    <string name="notification_group_overflow_indicator_ambient" msgid="879560382990377886">"<xliff:g id="NOTIFICATION_TITLE">%s</xliff:g> (+<xliff:g id="OVERFLOW">%s</xliff:g>)"</string>
    <plurals name="notification_group_overflow_description" formatted="false" msgid="4579313201268495404">
      <item quantity="other">此群组内还有 <xliff:g id="NUMBER_1">%s</xliff:g> 条通知。</item>
      <item quantity="one">此群组内还有 <xliff:g id="NUMBER_0">%s</xliff:g> 条通知。</item>
    </plurals>
    <string name="status_bar_notification_inspect_item_title" msgid="5668348142410115323">"通知设置"</string>
    <string name="status_bar_notification_app_settings_title" msgid="5525260160341558869">"<xliff:g id="APP_NAME">%s</xliff:g>设置"</string>
    <string name="accessibility_rotation_lock_off" msgid="4062780228931590069">"屏幕会自动旋转。"</string>
    <string name="accessibility_rotation_lock_on_landscape" msgid="6731197337665366273">"屏幕锁定为横屏模式。"</string>
    <string name="accessibility_rotation_lock_on_portrait" msgid="5809367521644012115">"屏幕锁定为纵向模式。"</string>
    <string name="accessibility_rotation_lock_off_changed" msgid="8134601071026305153">"屏幕将会自动旋转。"</string>
    <string name="accessibility_rotation_lock_on_landscape_changed" msgid="3135965553707519743">"屏幕现已锁定为横屏模式。"</string>
    <string name="accessibility_rotation_lock_on_portrait_changed" msgid="8922481981834012126">"屏幕现已锁定为纵向模式。"</string>
    <string name="dessert_case" msgid="1295161776223959221">"甜品盒"</string>
    <string name="start_dreams" msgid="5640361424498338327">"屏保"</string>
    <string name="ethernet_label" msgid="7967563676324087464">"有线网络"</string>
    <string name="quick_settings_header_onboarding_text" msgid="8030309023792936283">"触摸并按住相应图标即可查看更多选项"</string>
    <string name="quick_settings_dnd_label" msgid="8735855737575028208">"勿扰"</string>
    <string name="quick_settings_dnd_priority_label" msgid="483232950670692036">"仅限优先事项"</string>
    <string name="quick_settings_dnd_alarms_label" msgid="2559229444312445858">"仅限闹钟"</string>
    <string name="quick_settings_dnd_none_label" msgid="5025477807123029478">"完全阻止"</string>
    <string name="quick_settings_bluetooth_label" msgid="6304190285170721401">"蓝牙"</string>
    <string name="quick_settings_bluetooth_multiple_devices_label" msgid="3912245565613684735">"蓝牙（<xliff:g id="NUMBER">%d</xliff:g> 台设备）"</string>
    <string name="quick_settings_bluetooth_off_label" msgid="8159652146149219937">"蓝牙：关闭"</string>
    <string name="quick_settings_bluetooth_detail_empty_text" msgid="4910015762433302860">"没有可用的配对设备"</string>
    <string name="quick_settings_bluetooth_secondary_label_battery_level" msgid="7106697106764717416">"电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%s</xliff:g>"</string>
    <string name="quick_settings_bluetooth_secondary_label_audio" msgid="5673845963301132071">"音频"</string>
    <string name="quick_settings_bluetooth_secondary_label_headset" msgid="1880572731276240588">"耳机"</string>
    <string name="quick_settings_bluetooth_secondary_label_input" msgid="2173322305072945905">"输入"</string>
    <string name="quick_settings_bluetooth_secondary_label_transient" msgid="4551281899312150640">"正在开启…"</string>
    <string name="quick_settings_brightness_label" msgid="6968372297018755815">"亮度"</string>
    <string name="quick_settings_rotation_unlocked_label" msgid="7305323031808150099">"自动旋转"</string>
    <string name="accessibility_quick_settings_rotation" msgid="4231661040698488779">"自动旋转屏幕"</string>
    <string name="accessibility_quick_settings_rotation_value" msgid="8187398200140760213">"<xliff:g id="ID_1">%s</xliff:g>模式"</string>
    <string name="quick_settings_rotation_locked_label" msgid="6359205706154282377">"屏幕方向：锁定"</string>
    <string name="quick_settings_rotation_locked_portrait_label" msgid="5102691921442135053">"纵向"</string>
    <string name="quick_settings_rotation_locked_landscape_label" msgid="8553157770061178719">"横向"</string>
    <string name="quick_settings_ime_label" msgid="7073463064369468429">"输入法"</string>
    <string name="quick_settings_location_label" msgid="5011327048748762257">"位置信息"</string>
    <string name="quick_settings_location_off_label" msgid="7464544086507331459">"位置信息：关闭"</string>
    <string name="quick_settings_media_device_label" msgid="1302906836372603762">"媒体设备"</string>
    <string name="quick_settings_rssi_label" msgid="7725671335550695589">"RSSI"</string>
    <string name="quick_settings_rssi_emergency_only" msgid="2713774041672886750">"只能拨打紧急呼救电话"</string>
    <string name="quick_settings_settings_label" msgid="5326556592578065401">"设置"</string>
    <string name="quick_settings_time_label" msgid="4635969182239736408">"时间"</string>
    <string name="quick_settings_user_label" msgid="5238995632130897840">"我"</string>
    <string name="quick_settings_user_title" msgid="4467690427642392403">"用户"</string>
    <string name="quick_settings_user_new_user" msgid="9030521362023479778">"新用户"</string>
    <string name="quick_settings_wifi_label" msgid="9135344704899546041">"WLAN"</string>
    <string name="quick_settings_wifi_not_connected" msgid="7171904845345573431">"未连接"</string>
    <string name="quick_settings_wifi_no_network" msgid="2221993077220856376">"无网络"</string>
    <string name="quick_settings_wifi_off_label" msgid="7558778100843885864">"WLAN：关闭"</string>
    <string name="quick_settings_wifi_on_label" msgid="7607810331387031235">"WLAN 已开启"</string>
    <string name="quick_settings_wifi_detail_empty_text" msgid="269990350383909226">"没有 WLAN 网络"</string>
    <string name="quick_settings_wifi_secondary_label_transient" msgid="7748206246119760554">"正在开启…"</string>
    <string name="quick_settings_cast_title" msgid="7709016546426454729">"投射"</string>
    <string name="quick_settings_casting" msgid="6601710681033353316">"正在投射"</string>
    <string name="quick_settings_cast_device_default_name" msgid="5367253104742382945">"未命名设备"</string>
    <string name="quick_settings_cast_device_default_description" msgid="2484573682378634413">"已准备好投射"</string>
    <string name="quick_settings_cast_detail_empty_text" msgid="311785821261640623">"没有可用设备"</string>
    <string name="quick_settings_brightness_dialog_title" msgid="8599674057673605368">"亮度"</string>
    <string name="quick_settings_brightness_dialog_auto_brightness_label" msgid="5064982743784071218">"自动"</string>
    <string name="quick_settings_inversion_label" msgid="8790919884718619648">"反色"</string>
    <string name="quick_settings_color_space_label" msgid="853443689745584770">"颜色校正模式"</string>
    <string name="quick_settings_more_settings" msgid="326112621462813682">"更多设置"</string>
    <string name="quick_settings_done" msgid="3402999958839153376">"完成"</string>
    <string name="quick_settings_connected" msgid="1722253542984847487">"已连接"</string>
    <string name="quick_settings_connected_battery_level" msgid="4136051440381328892">"已连接，电池电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="quick_settings_connecting" msgid="47623027419264404">"正在连接…"</string>
    <string name="quick_settings_tethering_label" msgid="7153452060448575549">"网络共享"</string>
    <string name="quick_settings_hotspot_label" msgid="6046917934974004879">"热点"</string>
    <string name="quick_settings_hotspot_secondary_label_transient" msgid="8010579363691405477">"正在开启…"</string>
    <string name="quick_settings_hotspot_secondary_label_data_saver_enabled" msgid="5672131949987422420">"流量节省程序已开启"</string>
    <plurals name="quick_settings_hotspot_secondary_label_num_devices" formatted="false" msgid="2324635800672199428">
      <item quantity="other">%d 台设备</item>
      <item quantity="one">%d 台设备</item>
    </plurals>
    <string name="quick_settings_notifications_label" msgid="4818156442169154523">"通知"</string>
    <string name="quick_settings_flashlight_label" msgid="2133093497691661546">"手电筒"</string>
    <string name="quick_settings_cellular_detail_title" msgid="3661194685666477347">"移动数据"</string>
    <string name="quick_settings_cellular_detail_data_usage" msgid="1964260360259312002">"流量使用情况"</string>
    <string name="quick_settings_cellular_detail_remaining_data" msgid="722715415543541249">"剩余流量"</string>
    <string name="quick_settings_cellular_detail_over_limit" msgid="967669665390990427">"超出上限"</string>
    <string name="quick_settings_cellular_detail_data_used" msgid="1476810587475761478">"已使用<xliff:g id="DATA_USED">%s</xliff:g>"</string>
    <string name="quick_settings_cellular_detail_data_limit" msgid="56011158504994128">"上限为<xliff:g id="DATA_LIMIT">%s</xliff:g>"</string>
    <string name="quick_settings_cellular_detail_data_warning" msgid="2440098045692399009">"<xliff:g id="DATA_LIMIT">%s</xliff:g>警告"</string>
    <string name="quick_settings_work_mode_label" msgid="7608026833638817218">"工作资料"</string>
    <string name="quick_settings_night_display_label" msgid="3577098011487644395">"夜间模式"</string>
    <string name="quick_settings_night_secondary_label_on_at_sunset" msgid="8483259341596943314">"在日落时开启"</string>
    <string name="quick_settings_night_secondary_label_until_sunrise" msgid="4453017157391574402">"在日出时关闭"</string>
    <string name="quick_settings_night_secondary_label_on_at" msgid="6256314040368487637">"在<xliff:g id="TIME">%s</xliff:g> 开启"</string>
    <string name="quick_settings_secondary_label_until" msgid="2749196569462600150">"直到<xliff:g id="TIME">%s</xliff:g>"</string>
    <string name="quick_settings_nfc_label" msgid="9012153754816969325">"NFC"</string>
    <string name="quick_settings_nfc_off" msgid="6883274004315134333">"NFC 已停用"</string>
    <string name="quick_settings_nfc_on" msgid="6680317193676884311">"NFC 已启用"</string>
    <string name="recents_empty_message" msgid="808480104164008572">"近期没有任何内容"</string>
    <string name="recents_empty_message_dismissed_all" msgid="2791312568666558651">"您已清除所有内容"</string>
    <string name="recents_app_info_button_label" msgid="2890317189376000030">"应用信息"</string>
    <string name="recents_lock_to_app_button_label" msgid="6942899049072506044">"固定屏幕"</string>
    <string name="recents_search_bar_label" msgid="8074997400187836677">"搜索"</string>
    <string name="recents_launch_error_message" msgid="2969287838120550506">"无法启动<xliff:g id="APP">%s</xliff:g>。"</string>
    <string name="recents_launch_disabled_message" msgid="1624523193008871793">"<xliff:g id="APP">%s</xliff:g>已在安全模式下停用。"</string>
    <string name="recents_stack_action_button_label" msgid="6593727103310426253">"全部清除"</string>
    <string name="recents_drag_hint_message" msgid="2649739267073203985">"拖动到此处即可使用分屏功能"</string>
    <string name="recents_swipe_up_onboarding" msgid="3824607135920170001">"向上滑动可切换应用"</string>
    <string name="recents_quick_scrub_onboarding" msgid="2778062804333285789">"向右拖动可快速切换应用"</string>
    <string name="recents_multistack_add_stack_dialog_split_horizontal" msgid="8848514474543427332">"水平分割"</string>
    <string name="recents_multistack_add_stack_dialog_split_vertical" msgid="9075292233696180813">"垂直分割"</string>
    <string name="recents_multistack_add_stack_dialog_split_custom" msgid="4177837597513701943">"自定义分割"</string>
    <string name="recents_accessibility_split_screen_top" msgid="9056056469282256287">"将屏幕分隔线移到上方"</string>
    <string name="recents_accessibility_split_screen_left" msgid="8987144699630620019">"将屏幕分隔线移到左侧"</string>
    <string name="recents_accessibility_split_screen_right" msgid="275069779299592867">"将屏幕分隔线移到右侧"</string>
    <string name="quick_step_accessibility_toggle_overview" msgid="7171470775439860480">"切换概览"</string>
    <string name="expanded_header_battery_charged" msgid="5945855970267657951">"已充满"</string>
    <string name="expanded_header_battery_charging" msgid="205623198487189724">"正在充电"</string>
    <string name="expanded_header_battery_charging_with_time" msgid="457559884275395376">"还需<xliff:g id="CHARGING_TIME">%s</xliff:g>充满"</string>
    <string name="expanded_header_battery_not_charging" msgid="4798147152367049732">"未在充电"</string>
    <string name="ssl_ca_cert_warning" msgid="9005954106902053641">"网络可能会\n受到监控"</string>
    <string name="description_target_search" msgid="3091587249776033139">"搜索"</string>
    <string name="description_direction_up" msgid="7169032478259485180">"向上滑动以<xliff:g id="TARGET_DESCRIPTION">%s</xliff:g>。"</string>
    <string name="description_direction_left" msgid="7207478719805562165">"向左滑动以<xliff:g id="TARGET_DESCRIPTION">%s</xliff:g>。"</string>
    <string name="zen_priority_introduction" msgid="1149025108714420281">"您将不会受到声音和振动的打扰（闹钟、提醒、活动和所指定来电者的相关提示音除外）。您依然可以听到您选择播放的任何内容（包括音乐、视频和游戏）的相关音效。"</string>
    <string name="zen_alarms_introduction" msgid="4934328096749380201">"您将不会受到声音和振动的打扰（闹钟提示音除外）。您依然可以听到您选择播放的任何内容（包括音乐、视频和游戏）的相关音效。"</string>
    <string name="zen_priority_customize_button" msgid="7948043278226955063">"自定义"</string>
    <string name="zen_silence_introduction_voice" msgid="3948778066295728085">"这会阻止所有声音和振动（包括闹钟、音乐、视频和游戏）打扰您。您仍然可以拨打电话。"</string>
    <string name="zen_silence_introduction" msgid="3137882381093271568">"这会阻止所有声音和振动（包括闹钟、音乐、视频和游戏）打扰您。"</string>
    <string name="keyguard_more_overflow_text" msgid="9195222469041601365">"+<xliff:g id="NUMBER_OF_NOTIFICATIONS">%d</xliff:g>"</string>
    <string name="speed_bump_explanation" msgid="1288875699658819755">"不太紧急的通知会显示在下方"</string>
    <string name="notification_tap_again" msgid="7590196980943943842">"再次点按即可打开"</string>
    <string name="keyguard_unlock" msgid="8043466894212841998">"向上滑动即可解锁"</string>
    <string name="do_disclosure_generic" msgid="5615898451805157556">"此设备由您所属单位管理"</string>
    <string name="do_disclosure_with_name" msgid="5640615509915445501">"此设备是由<xliff:g id="ORGANIZATION_NAME">%s</xliff:g>托管"</string>
    <string name="phone_hint" msgid="4872890986869209950">"滑动图标即可拨打电话"</string>
    <string name="voice_hint" msgid="8939888732119726665">"滑动图标即可打开语音助理"</string>
    <string name="camera_hint" msgid="7939688436797157483">"滑动图标即可打开相机"</string>
    <string name="interruption_level_none_with_warning" msgid="5114872171614161084">"完全阻止。此模式也会将屏幕阅读器静音。"</string>
    <string name="interruption_level_none" msgid="6000083681244492992">"完全阻止"</string>
    <string name="interruption_level_priority" msgid="6426766465363855505">"仅限优先事项"</string>
    <string name="interruption_level_alarms" msgid="5226306993448328896">"仅限闹钟"</string>
    <string name="interruption_level_none_twoline" msgid="3957581548190765889">"完全\n静音"</string>
    <string name="interruption_level_priority_twoline" msgid="1564715335217164124">"仅限\n优先打扰"</string>
    <string name="interruption_level_alarms_twoline" msgid="3266909566410106146">"仅限\n闹钟"</string>
    <string name="keyguard_indication_charging_time" msgid="2056340799276374421">"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="keyguard_indication_charging_time_fast" msgid="7767562163577492332">"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在快速充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="keyguard_indication_charging_time_slowly" msgid="3769655133567307069">"<xliff:g id="PERCENTAGE">%2$s</xliff:g> • 正在慢速充电（还需 <xliff:g id="CHARGING_TIME_LEFT">%s</xliff:g>充满）"</string>
    <string name="accessibility_multi_user_switch_switcher" msgid="7305948938141024937">"切换用户"</string>
    <string name="accessibility_multi_user_switch_switcher_with_current" msgid="8434880595284601601">"切换用户，当前用户为<xliff:g id="CURRENT_USER_NAME">%s</xliff:g>"</string>
    <string name="accessibility_multi_user_switch_inactive" msgid="1424081831468083402">"当前用户为<xliff:g id="CURRENT_USER_NAME">%s</xliff:g>"</string>
    <string name="accessibility_multi_user_switch_quick_contact" msgid="3020367729287990475">"显示个人资料"</string>
    <string name="user_add_user" msgid="5110251524486079492">"添加用户"</string>
    <string name="user_new_user_name" msgid="426540612051178753">"新用户"</string>
    <string name="guest_nickname" msgid="8059989128963789678">"访客"</string>
    <string name="guest_new_guest" msgid="600537543078847803">"添加访客"</string>
    <string name="guest_exit_guest" msgid="7187359342030096885">"移除访客"</string>
    <string name="guest_exit_guest_dialog_title" msgid="8480693520521766688">"要移除访客吗？"</string>
    <string name="guest_exit_guest_dialog_message" msgid="4155503224769676625">"此会话中的所有应用和数据都将被删除。"</string>
    <string name="guest_exit_guest_dialog_remove" msgid="7402231963862520531">"移除"</string>
    <string name="guest_wipe_session_title" msgid="6419439912885956132">"访客，欢迎回来！"</string>
    <string name="guest_wipe_session_message" msgid="8476238178270112811">"要继续您的会话吗？"</string>
    <string name="guest_wipe_session_wipe" msgid="5065558566939858884">"重新开始"</string>
    <string name="guest_wipe_session_dontwipe" msgid="1401113462524894716">"是，继续"</string>
    <string name="guest_notification_title" msgid="1585278533840603063">"访客用户"</string>
    <string name="guest_notification_text" msgid="335747957734796689">"要删除应用和数据，请退出访客用户身份"</string>
    <string name="guest_notification_remove_action" msgid="8820670703892101990">"移除访客"</string>
    <string name="user_logout_notification_title" msgid="1453960926437240727">"退出当前用户"</string>
    <string name="user_logout_notification_text" msgid="3350262809611876284">"退出当前用户"</string>
    <string name="user_logout_notification_action" msgid="1195428991423425062">"退出当前用户"</string>
    <string name="user_add_user_title" msgid="4553596395824132638">"要添加新用户吗？"</string>
    <string name="user_add_user_message_short" msgid="2161624834066214559">"当您添加新用户时，该用户必须设置自己的空间。\n\n任何用户均可为其他所有用户更新应用。"</string>
    <string name="user_remove_user_title" msgid="4681256956076895559">"是否移除用户？"</string>
    <string name="user_remove_user_message" msgid="1453218013959498039">"此用户的所有应用和数据均将被删除。"</string>
    <string name="user_remove_user_remove" msgid="7479275741742178297">"移除"</string>
    <string name="battery_saver_notification_title" msgid="8614079794522291840">"省电模式已开启"</string>
    <string name="battery_saver_notification_text" msgid="820318788126672692">"降低性能并限制后台流量"</string>
    <string name="battery_saver_notification_action_text" msgid="132118784269455533">"关闭省电模式"</string>
    <string name="media_projection_dialog_text" msgid="3071431025448218928">"<xliff:g id="APP_SEEKING_PERMISSION">%s</xliff:g>将开始截取您的屏幕上显示的所有内容。"</string>
    <string name="media_projection_remember_text" msgid="3103510882172746752">"不再显示"</string>
    <string name="clear_all_notifications_text" msgid="814192889771462828">"全部清除"</string>
    <string name="manage_notifications_text" msgid="8035284146227267681">"管理通知"</string>
    <string name="dnd_suppressing_shade_text" msgid="1904574852846769301">"勿扰模式暂停的通知"</string>
    <string name="media_projection_action_text" msgid="8470872969457985954">"立即开始"</string>
    <string name="empty_shade_text" msgid="708135716272867002">"没有通知"</string>
    <string name="profile_owned_footer" msgid="8021888108553696069">"资料可能会受到监控"</string>
    <string name="vpn_footer" msgid="2388611096129106812">"网络可能会受到监控"</string>
    <string name="branded_vpn_footer" msgid="2168111859226496230">"网络可能会受到监控"</string>
    <string name="quick_settings_disclosure_management_monitoring" msgid="6645176135063957394">"您所在的单位会管理此设备，且可能会监控网络流量"</string>
    <string name="quick_settings_disclosure_named_management_monitoring" msgid="370622174777570853">"“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”会管理此设备，且可能会监控网络流量"</string>
    <string name="quick_settings_disclosure_management_named_vpn" msgid="1085137869053332307">"设备由您所在的单位负责管理，且已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_named_management_named_vpn" msgid="6290456493852584017">"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="VPN_APP">%2$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_management" msgid="3294967280853150271">"设备由您所在的单位负责管理"</string>
    <string name="quick_settings_disclosure_named_management" msgid="1059403025094542908">"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理"</string>
    <string name="quick_settings_disclosure_management_vpns" msgid="3698767349925266482">"设备由您所在的单位负责管理，且已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_named_management_vpns" msgid="7777821385318891527">"设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理，且已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_managed_profile_monitoring" msgid="5125463987558278215">"您所在的单位可能会监控您工作资料中的网络流量"</string>
    <string name="quick_settings_disclosure_named_managed_profile_monitoring" msgid="8973606847896650284">"“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”可能会监控您工作资料中的网络流量"</string>
    <string name="quick_settings_disclosure_monitoring" msgid="679658227269205728">"网络可能会受到监控"</string>
    <string name="quick_settings_disclosure_vpns" msgid="8170318392053156330">"设备已连接到两个 VPN"</string>
    <string name="quick_settings_disclosure_managed_profile_named_vpn" msgid="3494535754792751741">"工作资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_personal_profile_named_vpn" msgid="4467456202486569906">"个人资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="quick_settings_disclosure_named_vpn" msgid="6943724064780847080">"设备已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”"</string>
    <string name="monitoring_title_device_owned" msgid="1652495295941959815">"设备管理"</string>
    <string name="monitoring_title_profile_owned" msgid="6790109874733501487">"资料监控"</string>
    <string name="monitoring_title" msgid="169206259253048106">"网络监控"</string>
    <string name="monitoring_subtitle_vpn" msgid="876537538087857300">"VPN"</string>
    <string name="monitoring_subtitle_network_logging" msgid="3341264304793193386">"网络日志"</string>
    <string name="monitoring_subtitle_ca_certificate" msgid="3874151893894355988">"CA 证书"</string>
    <string name="disable_vpn" msgid="4435534311510272506">"关闭VPN"</string>
    <string name="disconnect_vpn" msgid="1324915059568548655">"断开VPN连接"</string>
    <string name="monitoring_button_view_policies" msgid="100913612638514424">"查看政策"</string>
    <string name="monitoring_description_named_management" msgid="5281789135578986303">"您的设备由“<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>”负责管理。\n\n您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。\n\n要了解详情，请与您的管理员联系。"</string>
    <string name="monitoring_description_management" msgid="4573721970278370790">"您的设备由贵单位负责管理。\n\n您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。\n\n要了解详情，请与您的管理员联系。"</string>
    <string name="monitoring_description_management_ca_certificate" msgid="5202023784131001751">"您所在的单位已在此设备上安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_managed_profile_ca_certificate" msgid="4683248196789897964">"您所在的单位已为您的工作资料安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_ca_certificate" msgid="7886985418413598352">"此设备上已安装证书授权中心。您的安全网络流量可能会受到监控或修改。"</string>
    <string name="monitoring_description_management_network_logging" msgid="7184005419733060736">"您的管理员已开启网络日志功能（该功能会监控您设备上的流量）。"</string>
    <string name="monitoring_description_named_vpn" msgid="7403457334088909254">"您已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_two_named_vpns" msgid="4198511413729213802">"您已连接到“<xliff:g id="VPN_APP_0">%1$s</xliff:g>”和“<xliff:g id="VPN_APP_1">%2$s</xliff:g>”（这两个应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_managed_profile_named_vpn" msgid="1427905889862420559">"您的工作资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_personal_profile_named_vpn" msgid="3133980926929069283">"您的个人资料已连接到“<xliff:g id="VPN_APP">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_do_header_generic" msgid="96588491028288691">"您的设备由<xliff:g id="DEVICE_OWNER_APP">%1$s</xliff:g>管理。"</string>
    <string name="monitoring_description_do_header_with_name" msgid="5511133708978206460">"<xliff:g id="ORGANIZATION_NAME">%1$s</xliff:g>会使用<xliff:g id="DEVICE_OWNER_APP">%2$s</xliff:g>管理您的设备。"</string>
    <string name="monitoring_description_do_body" msgid="3639594537660975895">"您的管理员能够监控和管理与您的设备相关的设置、企业权限、应用、数据，以及您设备的位置信息。"</string>
    <string name="monitoring_description_do_learn_more_separator" msgid="3785251953067436862">" "</string>
    <string name="monitoring_description_do_learn_more" msgid="1849514470437907421">"了解详情"</string>
    <string name="monitoring_description_do_body_vpn" msgid="8255218762488901796">"您已连接到<xliff:g id="VPN_APP">%1$s</xliff:g>，该应用可以监控您的网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_vpn_settings_separator" msgid="1933186756733474388">" "</string>
    <string name="monitoring_description_vpn_settings" msgid="6434859242636063861">"打开 VPN 设置"</string>
    <string name="monitoring_description_ca_cert_settings_separator" msgid="4987350385906393626">" "</string>
    <string name="monitoring_description_ca_cert_settings" msgid="5489969458872997092">"打开可信凭据列表"</string>
    <string name="monitoring_description_network_logging" msgid="7223505523384076027">"您的管理员已开启网络日志功能，该功能会监控您设备上的流量。\n\n如需更多信息，请与您的管理员联系。"</string>
    <string name="monitoring_description_vpn" msgid="4445150119515393526">"您已授权应用设置 VPN 连接。\n\n该应用可以监控您的设备和网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_vpn_profile_owned" msgid="2958019119161161530">"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”管理。\n\n您的管理员能够监控您的网络活动，其中包括收发电子邮件、使用应用和访问网站。\n\n如需更多信息，请与您的管理员联系。\n\n此外，您还连接到了 VPN，它同样可以监控您的网络活动。"</string>
    <string name="legacy_vpn_name" msgid="6604123105765737830">"VPN"</string>
    <string name="monitoring_description_app" msgid="1828472472674709532">"您已连接到“<xliff:g id="APPLICATION">%1$s</xliff:g>”（该应用能够监控您的网络活动，其中包括收发电子邮件、使用应用和浏览网站）。"</string>
    <string name="monitoring_description_app_personal" msgid="484599052118316268">"您已连接到<xliff:g id="APPLICATION">%1$s</xliff:g>，该应用可以监控您的个人网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="branded_monitoring_description_app_personal" msgid="2669518213949202599">"您已连接到<xliff:g id="APPLICATION">%1$s</xliff:g>，该应用可以监控您的个人网络活动，包括收发电子邮件、使用应用和浏览网站。"</string>
    <string name="monitoring_description_app_work" msgid="4612997849787922906">"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="APPLICATION">%2$s</xliff:g>”（该应用能够监控您的工作网络活动，其中包括收发电子邮件、使用应用和浏览网站）。\n\n如需更多信息，请与您的管理员联系。"</string>
    <string name="monitoring_description_app_personal_work" msgid="5664165460056859391">"您的工作资料由“<xliff:g id="ORGANIZATION">%1$s</xliff:g>”负责管理，且已连接到“<xliff:g id="APPLICATION_WORK">%2$s</xliff:g>”（该应用能够监控您的工作网络活动，其中包括收发电子邮件、使用应用和浏览网站）。\n\n此外，您还连接到了“<xliff:g id="APPLICATION_PERSONAL">%3$s</xliff:g>”（该应用能够监控您的个人网络活动）。"</string>
    <string name="keyguard_indication_trust_granted" msgid="4985003749105182372">"已为<xliff:g id="USER_NAME">%1$s</xliff:g>解锁"</string>
    <string name="keyguard_indication_trust_managed" msgid="8319646760022357585">"“<xliff:g id="TRUST_AGENT">%1$s</xliff:g>”正在运行"</string>
    <string name="keyguard_indication_trust_disabled" msgid="7412534203633528135">"在您手动解锁之前，设备会保持锁定状态"</string>
    <string name="hidden_notifications_title" msgid="7139628534207443290">"更快捷地查看通知"</string>
    <string name="hidden_notifications_text" msgid="2326409389088668981">"无需解锁即可查看通知"</string>
    <string name="hidden_notifications_cancel" msgid="3690709735122344913">"不用了"</string>
    <string name="hidden_notifications_setup" msgid="41079514801976810">"设置"</string>
    <string name="zen_mode_and_condition" msgid="4462471036429759903">"<xliff:g id="ZEN_MODE">%1$s</xliff:g>（<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>）"</string>
    <string name="volume_zen_end_now" msgid="6930243045593601084">"立即关闭"</string>
    <string name="accessibility_volume_settings" msgid="4915364006817819212">"声音设置"</string>
    <string name="accessibility_volume_expand" msgid="5946812790999244205">"展开"</string>
    <string name="accessibility_volume_collapse" msgid="3609549593031810875">"收起"</string>
    <string name="accessibility_output_chooser" msgid="8185317493017988680">"切换输出设备"</string>
    <string name="screen_pinning_title" msgid="3273740381976175811">"已固定屏幕"</string>
    <string name="screen_pinning_description" msgid="8909878447196419623">"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“返回”和“概览”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_recents_invisible" msgid="8281145542163727971">"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“返回”和“主屏幕”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_accessible" msgid="426190689254018656">"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“概览”即可取消固定屏幕。"</string>
    <string name="screen_pinning_description_recents_invisible_accessible" msgid="6134833683151189507">"这将会固定显示此屏幕，直到您取消固定为止。触摸并按住“主屏幕”即可取消固定屏幕。"</string>
    <string name="screen_pinning_toast" msgid="2266705122951934150">"要取消固定此屏幕，请触摸并按住“返回”和“概览”按钮"</string>
    <string name="screen_pinning_toast_recents_invisible" msgid="8252402309499161281">"要取消固定此屏幕，请触摸并按住“返回”和“主屏幕”按钮"</string>
    <string name="screen_pinning_positive" msgid="3783985798366751226">"知道了"</string>
    <string name="screen_pinning_negative" msgid="3741602308343880268">"不用了"</string>
    <string name="screen_pinning_start" msgid="1022122128489278317">"已固定屏幕"</string>
    <string name="screen_pinning_exit" msgid="5187339744262325372">"已取消固定屏幕"</string>
    <string name="quick_settings_reset_confirmation_title" msgid="748792586749897883">"要隐藏“<xliff:g id="TILE_LABEL">%1$s</xliff:g>”吗？"</string>
    <string name="quick_settings_reset_confirmation_message" msgid="2235970126803317374">"下次在设置中将其开启后，此快捷设置条目将会重新显示。"</string>
    <string name="quick_settings_reset_confirmation_button" msgid="2660339101868367515">"隐藏"</string>
    <string name="managed_profile_foreground_toast" msgid="5421487114739245972">"您当前正在使用工作资料"</string>
    <string name="stream_voice_call" msgid="4410002696470423714">"通话"</string>
    <string name="stream_system" msgid="7493299064422163147">"系统"</string>
    <string name="stream_ring" msgid="8213049469184048338">"铃声"</string>
    <string name="stream_music" msgid="9086982948697544342">"媒体"</string>
    <string name="stream_alarm" msgid="5209444229227197703">"闹钟"</string>
    <string name="stream_notification" msgid="2563720670905665031">"通知"</string>
    <string name="stream_bluetooth_sco" msgid="2055645746402746292">"蓝牙"</string>
    <string name="stream_dtmf" msgid="2447177903892477915">"双音多频"</string>
    <string name="stream_accessibility" msgid="301136219144385106">"无障碍"</string>
    <string name="ring_toggle_title" msgid="3281244519428819576">"通话"</string>
    <string name="volume_ringer_status_normal" msgid="4273142424125855384">"响铃"</string>
    <string name="volume_ringer_status_vibrate" msgid="1825615171021346557">"振动"</string>
    <string name="volume_ringer_status_silent" msgid="6896394161022916369">"静音"</string>
    <string name="qs_status_phone_vibrate" msgid="204362991135761679">"手机已设为振动"</string>
    <string name="qs_status_phone_muted" msgid="5437668875879171548">"手机已设为静音"</string>
    <string name="volume_stream_content_description_unmute" msgid="4436631538779230857">"%1$s。点按即可取消静音。"</string>
    <string name="volume_stream_content_description_vibrate" msgid="1187944970457807498">"%1$s。点按即可设为振动，但可能会同时将无障碍服务设为静音。"</string>
    <string name="volume_stream_content_description_mute" msgid="3625049841390467354">"%1$s。点按即可设为静音，但可能会同时将无障碍服务设为静音。"</string>
    <string name="volume_stream_content_description_vibrate_a11y" msgid="6427727603978431301">"%1$s。点按即可设为振动。"</string>
    <string name="volume_stream_content_description_mute_a11y" msgid="8995013018414535494">"%1$s。点按即可设为静音。"</string>
    <string name="volume_ringer_hint_mute" msgid="9199811307292269601">"静音"</string>
    <string name="volume_ringer_hint_unmute" msgid="6602880133293060368">"取消静音"</string>
    <string name="volume_ringer_hint_vibrate" msgid="4036802135666515202">"振动"</string>
    <string name="volume_dialog_title" msgid="7272969888820035876">"%s音量控件"</string>
    <string name="volume_dialog_ringer_guidance_ring" msgid="3360373718388509040">"有来电和通知时会响铃 (<xliff:g id="VOLUME_LEVEL">%1$s</xliff:g>)"</string>
    <string name="output_title" msgid="5355078100792942802">"媒体输出"</string>
    <string name="output_calls_title" msgid="8717692905017206161">"通话输出"</string>
    <string name="output_none_found" msgid="5544982839808921091">"未找到任何设备"</string>
    <string name="output_none_found_service_off" msgid="8631969668659757069">"未找到任何设备。请尝试开启<xliff:g id="SERVICE">%1$s</xliff:g>"</string>
    <string name="output_service_bt" msgid="6224213415445509542">"蓝牙"</string>
    <string name="output_service_wifi" msgid="3749735218931825054">"WLAN"</string>
    <string name="output_service_bt_wifi" msgid="4486837869988770896">"蓝牙和 WLAN"</string>
    <string name="system_ui_tuner" msgid="708224127392452018">"系统界面调节工具"</string>
    <string name="show_battery_percentage" msgid="5444136600512968798">"嵌入式显示电池电量百分比 显示嵌入的电池电量百分比"</string>
    <string name="show_battery_percentage_summary" msgid="3215025775576786037">"未充电时在状态栏图标内显示电池电量百分比"</string>
    <string name="quick_settings" msgid="10042998191725428">"快捷设置"</string>
    <string name="status_bar" msgid="4877645476959324760">"状态栏"</string>
    <string name="overview" msgid="4018602013895926956">"概览"</string>
    <string name="demo_mode" msgid="2532177350215638026">"系统界面演示模式"</string>
    <string name="enable_demo_mode" msgid="4844205668718636518">"启用演示模式"</string>
    <string name="show_demo_mode" msgid="2018336697782464029">"显示演示模式"</string>
    <string name="status_bar_ethernet" msgid="5044290963549500128">"以太网"</string>
    <string name="status_bar_alarm" msgid="8536256753575881818">"闹钟"</string>
    <string name="status_bar_work" msgid="6022553324802866373">"工作资料"</string>
    <string name="status_bar_airplane" msgid="7057575501472249002">"飞行模式"</string>
    <string name="add_tile" msgid="2995389510240786221">"添加图块"</string>
    <string name="broadcast_tile" msgid="3894036511763289383">"播送图块"</string>
    <string name="zen_alarm_warning_indef" msgid="3482966345578319605">"您在<xliff:g id="WHEN">%1$s</xliff:g>将不会听到下次闹钟响铃，除非您在该时间之前关闭此模式"</string>
    <string name="zen_alarm_warning" msgid="444533119582244293">"您在<xliff:g id="WHEN">%1$s</xliff:g>将不会听到下次闹钟响铃"</string>
    <string name="alarm_template" msgid="3980063409350522735">"<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="alarm_template_far" msgid="4242179982586714810">"<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="accessibility_quick_settings_detail" msgid="2579369091672902101">"快捷设置，<xliff:g id="TITLE">%s</xliff:g>。"</string>
    <string name="accessibility_status_bar_hotspot" msgid="4099381329956402865">"热点"</string>
    <string name="accessibility_managed_profile" msgid="6613641363112584120">"工作资料"</string>
    <string name="tuner_warning_title" msgid="7094689930793031682">"并不适合所有用户"</string>
    <string name="tuner_warning" msgid="8730648121973575701">"系统界面调节工具可让您以更多方式调整及定制 Android 界面。在日后推出的版本中，这些实验性功能可能会变更、失效或消失。操作时请务必谨慎。"</string>
    <string name="tuner_persistent_warning" msgid="8597333795565621795">"在日后推出的版本中，这些实验性功能可能会变更、失效或消失。操作时请务必谨慎。"</string>
    <string name="got_it" msgid="2239653834387972602">"知道了"</string>
    <string name="tuner_toast" msgid="603429811084428439">"恭喜！系统界面调节工具已添加到“设置”中"</string>
    <string name="remove_from_settings" msgid="8389591916603406378">"从“设置”中移除"</string>
    <string name="remove_from_settings_prompt" msgid="6069085993355887748">"要从“设置”中移除系统界面调节工具，并停止使用所有相关功能吗？"</string>
    <string name="activity_not_found" msgid="348423244327799974">"您的设备中未安装此应用"</string>
    <string name="clock_seconds" msgid="7689554147579179507">"显示时钟的秒数"</string>
    <string name="clock_seconds_desc" msgid="6282693067130470675">"在状态栏中显示时钟的秒数。这可能会影响电池的续航时间。"</string>
    <string name="qs_rearrange" msgid="8060918697551068765">"重新排列快捷设置"</string>
    <string name="show_brightness" msgid="6613930842805942519">"在快捷设置中显示亮度栏"</string>
    <string name="experimental" msgid="6198182315536726162">"实验性功能"</string>
    <string name="enable_bluetooth_title" msgid="5027037706500635269">"要开启蓝牙吗？"</string>
    <string name="enable_bluetooth_message" msgid="9106595990708985385">"要将您的键盘连接到平板电脑，您必须先开启蓝牙。"</string>
    <string name="enable_bluetooth_confirmation_ok" msgid="6258074250948309715">"开启"</string>
    <string name="show_silently" msgid="6841966539811264192">"显示通知，但不发出提示音"</string>
    <string name="block" msgid="2734508760962682611">"屏蔽所有通知"</string>
    <string name="do_not_silence" msgid="6878060322594892441">"不静音"</string>
    <string name="do_not_silence_block" msgid="4070647971382232311">"不静音也不屏蔽"</string>
    <string name="tuner_full_importance_settings" msgid="3207312268609236827">"高级通知设置"</string>
    <string name="tuner_full_importance_settings_on" msgid="7545060756610299966">"开启"</string>
    <string name="tuner_full_importance_settings_off" msgid="8208165412614935229">"关闭"</string>
    <string name="power_notification_controls_description" msgid="4372459941671353358">"利用高级通知设置，您可以为应用通知设置从 0 级到 5 级的重要程度等级。\n\n"<b>"5 级"</b>" \n- 在通知列表顶部显示 \n- 允许全屏打扰 \n- 一律短暂显示通知 \n\n"<b>"4 级"</b>" \n- 禁止全屏打扰 \n- 一律短暂显示通知 \n\n"<b>"3 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n\n"<b>"2 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n- 一律不发出声音或振动 \n\n"<b>"1 级"</b>" \n- 禁止全屏打扰 \n- 一律不短暂显示通知 \n- 一律不发出声音或振动 \n- 不在锁定屏幕和状态栏中显示 \n- 在通知列表底部显示 \n\n"<b>"0 级"</b>" \n- 屏蔽应用的所有通知"</string>
    <string name="notification_header_default_channel" msgid="7506845022070889909">"通知"</string>
    <string name="notification_channel_disabled" msgid="344536703863700565">"您将不会再看到这些通知"</string>
    <string name="notification_channel_minimized" msgid="1664411570378910931">"系统将会最小化这些通知"</string>
    <string name="inline_blocking_helper" msgid="3055064577771478591">"您通常会关闭这些通知。\n是否继续显示通知？"</string>
    <string name="inline_keep_showing" msgid="8945102997083836858">"要继续显示这些通知吗？"</string>
    <string name="inline_stop_button" msgid="4172980096860941033">"停止通知"</string>
    <string name="inline_keep_button" msgid="6665940297019018232">"继续显示"</string>
    <string name="inline_minimize_button" msgid="966233327974702195">"最小化"</string>
    <string name="inline_keep_showing_app" msgid="1723113469580031041">"要继续显示来自此应用的通知吗？"</string>
    <string name="notification_unblockable_desc" msgid="1037434112919403708">"无法关闭这些通知"</string>
    <string name="appops_camera" msgid="8100147441602585776">"此应用正在使用摄像头。"</string>
    <string name="appops_microphone" msgid="741508267659494555">"此应用正在使用麦克风。"</string>
    <string name="appops_overlay" msgid="6165912637560323464">"此应用正显示在屏幕上其他应用的上层。"</string>
    <string name="appops_camera_mic" msgid="1576901651150187433">"此应用正在使用麦克风和摄像头。"</string>
    <string name="appops_camera_overlay" msgid="8869400080809298814">"此应用正显示在屏幕上其他应用的上层，并且正在使用摄像头。"</string>
    <string name="appops_mic_overlay" msgid="4835157962857919804">"此应用正显示在屏幕上其他应用的上层，并且正在使用麦克风。"</string>
    <string name="appops_camera_mic_overlay" msgid="6718768197048030993">"此应用正显示在屏幕上其他应用的上层，并且正在使用麦克风和摄像头。"</string>
    <string name="notification_appops_settings" msgid="1028328314935908050">"设置"</string>
    <string name="notification_appops_ok" msgid="1156966426011011434">"确定"</string>
    <string name="notification_channel_controls_opened_accessibility" msgid="6553950422055908113">"<xliff:g id="APP_NAME">%1$s</xliff:g>的通知控件已打开"</string>
    <string name="notification_channel_controls_closed_accessibility" msgid="7521619812603693144">"<xliff:g id="APP_NAME">%1$s</xliff:g>的通知控件已关闭"</string>
    <string name="notification_channel_switch_accessibility" msgid="3420796005601900717">"允许接收来自此频道的通知"</string>
    <string name="notification_more_settings" msgid="816306283396553571">"更多设置"</string>
    <string name="notification_app_settings" msgid="420348114670768449">"自定义"</string>
    <string name="notification_done" msgid="5279426047273930175">"完成"</string>
    <string name="inline_undo" msgid="558916737624706010">"撤消"</string>
    <string name="notification_menu_accessibility" msgid="2046162834248888553">"<xliff:g id="APP_NAME">%1$s</xliff:g><xliff:g id="MENU_DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="notification_menu_gear_description" msgid="2204480013726775108">"通知设置"</string>
    <string name="notification_menu_snooze_description" msgid="3653669438131034525">"通知延后选项"</string>
    <string name="notification_menu_snooze_action" msgid="1112254519029621372">"延后"</string>
    <string name="snooze_undo" msgid="6074877317002985129">"撤消"</string>
    <string name="snoozed_for_time" msgid="2390718332980204462">"已延后 <xliff:g id="TIME_AMOUNT">%1$s</xliff:g>"</string>
    <plurals name="snoozeHourOptions" formatted="false" msgid="2124335842674413030">
      <item quantity="other">%d 小时</item>
      <item quantity="one">%d 小时</item>
    </plurals>
    <plurals name="snoozeMinuteOptions" formatted="false" msgid="4127251700591510196">
      <item quantity="other">%d 分钟</item>
      <item quantity="one">%d 分钟</item>
    </plurals>
    <string name="battery_panel_title" msgid="7944156115535366613">"电池使用情况"</string>
    <string name="battery_detail_charging_summary" msgid="1279095653533044008">"充电过程中无法使用省电模式"</string>
    <string name="battery_detail_switch_title" msgid="6285872470260795421">"省电模式"</string>
    <string name="battery_detail_switch_summary" msgid="9049111149407626804">"降低性能并限制后台流量"</string>
    <string name="keyboard_key_button_template" msgid="6230056639734377300">"<xliff:g id="NAME">%1$s</xliff:g>按钮"</string>
    <string name="keyboard_key_home" msgid="2243500072071305073">"Home"</string>
    <string name="keyboard_key_back" msgid="2337450286042721351">"返回"</string>
    <string name="keyboard_key_dpad_up" msgid="5584144111755734686">"向上"</string>
    <string name="keyboard_key_dpad_down" msgid="7331518671788337815">"向下"</string>
    <string name="keyboard_key_dpad_left" msgid="1346446024676962251">"向左"</string>
    <string name="keyboard_key_dpad_right" msgid="3317323247127515341">"向右"</string>
    <string name="keyboard_key_dpad_center" msgid="2566737770049304658">"中心"</string>
    <string name="keyboard_key_tab" msgid="3871485650463164476">"Tab"</string>
    <string name="keyboard_key_space" msgid="2499861316311153293">"空格"</string>
    <string name="keyboard_key_enter" msgid="5739632123216118137">"Enter"</string>
    <string name="keyboard_key_backspace" msgid="1559580097512385854">"退格"</string>
    <string name="keyboard_key_media_play_pause" msgid="3861975717393887428">"播放/暂停"</string>
    <string name="keyboard_key_media_stop" msgid="2859963958595908962">"停止"</string>
    <string name="keyboard_key_media_next" msgid="1894394911630345607">"下一曲"</string>
    <string name="keyboard_key_media_previous" msgid="4256072387192967261">"上一曲"</string>
    <string name="keyboard_key_media_rewind" msgid="2654808213360820186">"快退"</string>
    <string name="keyboard_key_media_fast_forward" msgid="3849417047738200605">"快进"</string>
    <string name="keyboard_key_page_up" msgid="5654098530106845603">"向上翻页"</string>
    <string name="keyboard_key_page_down" msgid="8720502083731906136">"PgDn"</string>
    <string name="keyboard_key_forward_del" msgid="1391451334716490176">"删除"</string>
    <string name="keyboard_key_move_home" msgid="2765693292069487486">"Home"</string>
    <string name="keyboard_key_move_end" msgid="5901174332047975247">"End"</string>
    <string name="keyboard_key_insert" msgid="8530501581636082614">"Insert"</string>
    <string name="keyboard_key_num_lock" msgid="5052537581246772117">"Num Lock"</string>
    <string name="keyboard_key_numpad_template" msgid="8729216555174634026">"数字键盘 <xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="keyboard_shortcut_group_system" msgid="6472647649616541064">"系统"</string>
    <string name="keyboard_shortcut_group_system_home" msgid="3054369431319891965">"主屏幕"</string>
    <string name="keyboard_shortcut_group_system_recents" msgid="3154851905021926744">"最近"</string>
    <string name="keyboard_shortcut_group_system_back" msgid="2207004531216446378">"返回"</string>
    <string name="keyboard_shortcut_group_system_notifications" msgid="8366964080041773224">"通知"</string>
    <string name="keyboard_shortcut_group_system_shortcuts_helper" msgid="4892255911160332762">"键盘快捷键"</string>
    <string name="keyboard_shortcut_group_system_switch_input" msgid="2334164096341310324">"切换输入法"</string>
    <string name="keyboard_shortcut_group_applications" msgid="9129465955073449206">"应用"</string>
    <string name="keyboard_shortcut_group_applications_assist" msgid="9095441910537146013">"助手应用"</string>
    <string name="keyboard_shortcut_group_applications_browser" msgid="6465985474000766533">"浏览器"</string>
    <string name="keyboard_shortcut_group_applications_contacts" msgid="2064197111278436375">"通讯录"</string>
    <string name="keyboard_shortcut_group_applications_email" msgid="6257036897441939004">"电子邮件"</string>
    <string name="keyboard_shortcut_group_applications_sms" msgid="638701213803242744">"短信"</string>
    <string name="keyboard_shortcut_group_applications_music" msgid="4775559515850922780">"音乐"</string>
    <string name="keyboard_shortcut_group_applications_youtube" msgid="6555453761294723317">"YouTube"</string>
    <string name="keyboard_shortcut_group_applications_calendar" msgid="9043614299194991263">"日历"</string>
    <string name="tuner_full_zen_title" msgid="4540823317772234308">"与音量控件一起显示"</string>
    <string name="volume_and_do_not_disturb" msgid="3373784330208603030">"勿扰"</string>
    <string name="volume_dnd_silent" msgid="4363882330723050727">"音量按钮快捷键"</string>
    <string name="volume_up_silent" msgid="7141255269783588286">"按音量调高键时退出勿扰模式"</string>
    <string name="battery" msgid="7498329822413202973">"电池"</string>
    <string name="clock" msgid="7416090374234785905">"时钟"</string>
    <string name="headset" msgid="4534219457597457353">"耳机"</string>
    <string name="accessibility_long_click_tile" msgid="6687350750091842525">"打开“设置”"</string>
    <string name="accessibility_status_bar_headphones" msgid="9156307120060559989">"已连接到耳机"</string>
    <string name="accessibility_status_bar_headset" msgid="8666419213072449202">"已连接到耳机"</string>
    <string name="data_saver" msgid="5037565123367048522">"流量节省程序"</string>
    <string name="accessibility_data_saver_on" msgid="8454111686783887148">"流量节省程序已开启"</string>
    <string name="accessibility_data_saver_off" msgid="8841582529453005337">"流量节省程序已关闭"</string>
    <string name="switch_bar_on" msgid="1142437840752794229">"开启"</string>
    <string name="switch_bar_off" msgid="8803270596930432874">"关闭"</string>
    <string name="nav_bar" msgid="1993221402773877607">"导航栏"</string>
    <string name="nav_bar_layout" msgid="3664072994198772020">"布局"</string>
    <string name="left_nav_bar_button_type" msgid="8555981238887546528">"其他向左按钮类型"</string>
    <string name="right_nav_bar_button_type" msgid="2481056627065649656">"其他向右按钮类型"</string>
    <string name="nav_bar_default" msgid="8587114043070993007">"（默认）"</string>
  <string-array name="nav_bar_buttons">
    <item msgid="1545641631806817203">"剪贴板"</item>
    <item msgid="5742013440802239414">"键码"</item>
    <item msgid="1951959982985094069">"确认旋转、键盘切换器"</item>
    <item msgid="8175437057325747277">"无"</item>
  </string-array>
  <string-array name="nav_bar_layouts">
    <item msgid="8077901629964902399">"一般"</item>
    <item msgid="8256205964297588988">"紧凑"</item>
    <item msgid="8719936228094005878">"靠左"</item>
    <item msgid="586019486955594690">"靠右"</item>
  </string-array>
    <string name="menu_ime" msgid="4998010205321292416">"键盘切换器"</string>
    <string name="save" msgid="2311877285724540644">"保存"</string>
    <string name="reset" msgid="2448168080964209908">"重置"</string>
    <string name="adjust_button_width" msgid="6138616087197632947">"调整按钮宽度"</string>
    <string name="clipboard" msgid="1313879395099896312">"剪贴板"</string>
    <string name="accessibility_key" msgid="5701989859305675896">"自定义导航按钮"</string>
    <string name="left_keycode" msgid="2010948862498918135">"向左键码"</string>
    <string name="right_keycode" msgid="708447961000848163">"向右键码"</string>
    <string name="left_icon" msgid="3096287125959387541">"向左图标"</string>
    <string name="right_icon" msgid="3952104823293824311">"向右图标"</string>
    <string name="drag_to_add_tiles" msgid="230586591689084925">"按住并拖动即可添加图块"</string>
    <string name="drag_to_remove_tiles" msgid="3361212377437088062">"拖动到此处即可移除"</string>
    <string name="drag_to_remove_disabled" msgid="2390968976638993382">"您至少需要 6 个图块"</string>
    <string name="qs_edit" msgid="2232596095725105230">"编辑"</string>
    <string name="tuner_time" msgid="6572217313285536011">"时间"</string>
  <string-array name="clock_options">
    <item msgid="5965318737560463480">"显示小时、分钟和秒"</item>
    <item msgid="1427801730816895300">"显示小时和分钟（默认）"</item>
    <item msgid="3830170141562534721">"不显示此图标"</item>
  </string-array>
  <string-array name="battery_options">
    <item msgid="3160236755818672034">"一律显示百分比"</item>
    <item msgid="2139628951880142927">"充电时显示百分比（默认）"</item>
    <item msgid="3327323682209964956">"不显示此图标"</item>
  </string-array>
    <string name="other" msgid="4060683095962566764">"其他"</string>
    <string name="accessibility_divider" msgid="5903423481953635044">"分屏分隔线"</string>
    <string name="accessibility_action_divider_left_full" msgid="2801570521881574972">"左侧全屏"</string>
    <string name="accessibility_action_divider_left_70" msgid="3612060638991687254">"左侧 70%"</string>
    <string name="accessibility_action_divider_left_50" msgid="1248083470322193075">"左侧 50%"</string>
    <string name="accessibility_action_divider_left_30" msgid="543324403127069386">"左侧 30%"</string>
    <string name="accessibility_action_divider_right_full" msgid="4639381073802030463">"右侧全屏"</string>
    <string name="accessibility_action_divider_top_full" msgid="5357010904067731654">"顶部全屏"</string>
    <string name="accessibility_action_divider_top_70" msgid="5090779195650364522">"顶部 70%"</string>
    <string name="accessibility_action_divider_top_50" msgid="6385859741925078668">"顶部 50%"</string>
    <string name="accessibility_action_divider_top_30" msgid="6201455163864841205">"顶部 30%"</string>
    <string name="accessibility_action_divider_bottom_full" msgid="301433196679548001">"底部全屏"</string>
    <string name="accessibility_qs_edit_tile_label" msgid="8374924053307764245">"位置 <xliff:g id="POSITION">%1$d</xliff:g>，<xliff:g id="TILE_NAME">%2$s</xliff:g>。点按两次即可修改。"</string>
    <string name="accessibility_qs_edit_add_tile_label" msgid="8133209638023882667">"<xliff:g id="TILE_NAME">%1$s</xliff:g>。点按两次即可添加。"</string>
    <string name="accessibility_qs_edit_position_label" msgid="5055306305919289819">"位置 <xliff:g id="POSITION">%1$d</xliff:g>。点按两次即可选择。"</string>
    <string name="accessibility_qs_edit_move_tile" msgid="2461819993780159542">"移动<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_remove_tile" msgid="7484493384665907197">"移除<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_added" msgid="8050200862063548309">"已将<xliff:g id="TILE_NAME">%1$s</xliff:g>添加到位置 <xliff:g id="POSITION">%2$d</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_removed" msgid="8584304916627913440">"已移除<xliff:g id="TILE_NAME">%1$s</xliff:g>"</string>
    <string name="accessibility_qs_edit_tile_moved" msgid="4343693412689365038">"已将<xliff:g id="TILE_NAME">%1$s</xliff:g>移至位置 <xliff:g id="POSITION">%2$d</xliff:g>"</string>
    <string name="accessibility_desc_quick_settings_edit" msgid="8073587401747016103">"快捷设置编辑器。"</string>
    <string name="accessibility_desc_notification_icon" msgid="8352414185263916335">"<xliff:g id="ID_1">%1$s</xliff:g>通知：<xliff:g id="ID_2">%2$s</xliff:g>"</string>
    <string name="dock_forced_resizable" msgid="5914261505436217520">"应用可能无法在分屏模式下正常运行。"</string>
    <string name="dock_non_resizeble_failed_to_dock_text" msgid="3871617304250207291">"应用不支持分屏。"</string>
    <string name="forced_resizable_secondary_display" msgid="4230857851756391925">"应用可能无法在辅显示屏上正常运行。"</string>
    <string name="activity_launch_on_secondary_display_failed_text" msgid="7793821742158306742">"应用不支持在辅显示屏上启动。"</string>
    <string name="accessibility_quick_settings_settings" msgid="6132460890024942157">"打开设置。"</string>
    <string name="accessibility_quick_settings_expand" msgid="2375165227880477530">"开启快捷设置。"</string>
    <string name="accessibility_quick_settings_collapse" msgid="1792625797142648105">"关闭快捷设置。"</string>
    <string name="accessibility_quick_settings_alarm_set" msgid="1863000242431528676">"已设置闹钟。"</string>
    <string name="accessibility_quick_settings_user" msgid="1567445362870421770">"目前登录的用户名为<xliff:g id="ID_1">%s</xliff:g>"</string>
    <string name="data_connection_no_internet" msgid="4503302451650972989">"未连接到互联网"</string>
    <string name="accessibility_quick_settings_open_details" msgid="4230931801728005194">"打开详情页面。"</string>
    <string name="accessibility_quick_settings_open_settings" msgid="7806613775728380737">"打开<xliff:g id="ID_1">%s</xliff:g>设置。"</string>
    <string name="accessibility_quick_settings_edit" msgid="7839992848995240393">"修改设置顺序。"</string>
    <string name="accessibility_quick_settings_page" msgid="5032979051755200721">"第 <xliff:g id="ID_1">%1$d</xliff:g> 页，共 <xliff:g id="ID_2">%2$d</xliff:g> 页"</string>
    <string name="tuner_lock_screen" msgid="5755818559638850294">"锁定屏幕"</string>
    <string name="pip_phone_expand" msgid="5889780005575693909">"展开"</string>
    <string name="pip_phone_minimize" msgid="1079119422589131792">"最小化"</string>
    <string name="pip_phone_close" msgid="8416647892889710330">"关闭"</string>
    <string name="pip_phone_settings" msgid="8080777499521528521">"设置"</string>
    <string name="pip_phone_dismiss_hint" msgid="6351678169095923899">"向下拖动即可关闭"</string>
    <string name="pip_menu_title" msgid="4707292089961887657">"菜单"</string>
    <string name="pip_notification_title" msgid="3204024940158161322">"<xliff:g id="NAME">%s</xliff:g>目前位于“画中画”中"</string>
    <string name="pip_notification_message" msgid="5619512781514343311">"如果您不想让“<xliff:g id="NAME">%s</xliff:g>”使用此功能，请点按以打开设置，然后关闭此功能。"</string>
    <string name="pip_play" msgid="1417176722760265888">"播放"</string>
    <string name="pip_pause" msgid="8881063404466476571">"暂停"</string>
    <string name="pip_skip_to_next" msgid="1948440006726306284">"跳到下一个"</string>
    <string name="pip_skip_to_prev" msgid="1955311326688637914">"跳到上一个"</string>
    <string name="thermal_shutdown_title" msgid="4458304833443861111">"手机因严重发热而自动关机"</string>
    <string name="thermal_shutdown_message" msgid="9006456746902370523">"现在，您的手机已恢复正常运行"</string>
    <string name="thermal_shutdown_dialog_message" msgid="566347880005304139">"由于发热严重，因此您的手机执行了自动关机以降温。现在，您的手机已恢复正常运行。\n\n以下情况可能会导致您的手机严重发热：\n • 使用占用大量资源的应用（例如游戏、视频或导航应用）\n • 下载或上传大型文件\n • 在高温环境下使用手机"</string>
    <string name="high_temp_title" msgid="4589508026407318374">"手机温度上升中"</string>
    <string name="high_temp_notif_message" msgid="5642466103153429279">"手机降温时，部分功能的使用会受限制"</string>
    <string name="high_temp_dialog_message" msgid="6840700639374113553">"您的手机将自动尝试降温。您依然可以使用您的手机，但是手机运行速度可能会更慢。\n\n手机降温后，就会恢复正常的运行速度。"</string>
    <string name="lockscreen_shortcut_left" msgid="2182769107618938629">"向左快捷方式"</string>
    <string name="lockscreen_shortcut_right" msgid="3328683699505226536">"向右快捷方式"</string>
    <string name="lockscreen_unlock_left" msgid="2043092136246951985">"向左快捷方式也可以解锁设备"</string>
    <string name="lockscreen_unlock_right" msgid="1529992940510318775">"向右快捷方式也可以解锁设备"</string>
    <string name="lockscreen_none" msgid="4783896034844841821">"无"</string>
    <string name="tuner_launch_app" msgid="1527264114781925348">"启动<xliff:g id="APP">%1$s</xliff:g>"</string>
    <string name="tuner_other_apps" msgid="4726596850501162493">"其他应用"</string>
    <string name="tuner_circle" msgid="2340998864056901350">"圆形"</string>
    <string name="tuner_plus" msgid="6792960658533229675">"加号"</string>
    <string name="tuner_minus" msgid="4806116839519226809">"减号"</string>
    <string name="tuner_left" msgid="8404287986475034806">"向左"</string>
    <string name="tuner_right" msgid="6222734772467850156">"向右"</string>
    <string name="tuner_menu" msgid="191640047241552081">"菜单"</string>
    <string name="tuner_app" msgid="3507057938640108777">"<xliff:g id="APP">%1$s</xliff:g>应用"</string>
    <string name="notification_channel_alerts" msgid="4496839309318519037">"提醒"</string>
    <string name="notification_channel_battery" msgid="5786118169182888462">"电池"</string>
    <string name="notification_channel_screenshot" msgid="6314080179230000938">"屏幕截图"</string>
    <string name="notification_channel_general" msgid="4525309436693914482">"常规消息"</string>
    <string name="notification_channel_storage" msgid="3077205683020695313">"存储空间"</string>
    <string name="notification_channel_hints" msgid="7323870212489152689">"提示"</string>
    <string name="instant_apps" msgid="6647570248119804907">"免安装应用"</string>
    <string name="instant_apps_message" msgid="8116608994995104836">"免安装应用无需安装就能使用。"</string>
    <string name="app_info" msgid="6856026610594615344">"应用信息"</string>
    <string name="go_to_web" msgid="2650669128861626071">"转到浏览器"</string>
    <string name="mobile_data" msgid="7094582042819250762">"移动数据"</string>
    <string name="mobile_data_text_format" msgid="3526214522670876454">"<xliff:g id="ID_1">%s</xliff:g> - <xliff:g id="ID_2">%s</xliff:g>"</string>
    <string name="wifi_is_off" msgid="1838559392210456893">"WLAN 已关闭"</string>
    <string name="bt_is_off" msgid="2640685272289706392">"蓝牙已关闭"</string>
    <string name="dnd_is_off" msgid="6167780215212497572">"“勿扰”模式已关闭"</string>
    <string name="qs_dnd_prompt_auto_rule" msgid="862559028345233052">"某个自动规则（<xliff:g id="ID_1">%s</xliff:g>）已开启勿扰模式。"</string>
    <string name="qs_dnd_prompt_app" msgid="7978037419334156034">"某个应用（<xliff:g id="ID_1">%s</xliff:g>）已开启勿扰模式。"</string>
    <string name="qs_dnd_prompt_auto_rule_app" msgid="2599343675391111951">"某个自动规则或应用已开启勿扰模式。"</string>
    <string name="qs_dnd_until" msgid="3469471136280079874">"直到<xliff:g id="ID_1">%s</xliff:g>"</string>
    <string name="qs_dnd_keep" msgid="1825009164681928736">"保留"</string>
    <string name="qs_dnd_replace" msgid="8019520786644276623">"替换"</string>
    <string name="running_foreground_services_title" msgid="381024150898615683">"在后台运行的应用"</string>
    <string name="running_foreground_services_msg" msgid="6326247670075574355">"点按即可详细了解电量和流量消耗情况"</string>
    <string name="mobile_data_disable_title" msgid="1068272097382942231">"要关闭移动数据网络吗？"</string>
    <string name="mobile_data_disable_message" msgid="4756541658791493506">"您将无法通过<xliff:g id="CARRIER">%s</xliff:g>获取移动数据访问权限或连接到互联网。您只能通过 WLAN 连接到互联网。"</string>
    <string name="mobile_data_disable_message_default_carrier" msgid="6078110473451946831">"您的运营商"</string>
    <string name="touch_filtered_warning" msgid="8671693809204767551">"由于某个应用遮挡了权限请求界面，因此“设置”应用无法验证您的回应。"</string>
    <string name="slice_permission_title" msgid="7465009437851044444">"要允许“<xliff:g id="APP_0">%1$s</xliff:g>”显示“<xliff:g id="APP_2">%2$s</xliff:g>”图块吗？"</string>
    <string name="slice_permission_text_1" msgid="3514586565609596523">"- 可以读取“<xliff:g id="APP">%1$s</xliff:g>”中的信息"</string>
    <string name="slice_permission_text_2" msgid="3146758297471143723">"- 可以在“<xliff:g id="APP">%1$s</xliff:g>”内执行操作"</string>
    <string name="slice_permission_checkbox" msgid="7986504458640562900">"允许“<xliff:g id="APP">%1$s</xliff:g>”显示任何应用的图块"</string>
    <string name="slice_permission_allow" msgid="2340244901366722709">"允许"</string>
    <string name="slice_permission_deny" msgid="7683681514008048807">"拒绝"</string>
    <string name="auto_saver_title" msgid="1217959994732964228">"点按即可预设省电模式"</string>
    <string name="auto_saver_text" msgid="6324376061044218113">"当电池电量剩余 <xliff:g id="PERCENTAGE">%d</xliff:g>%% 时自动开启"</string>
    <string name="no_auto_saver_action" msgid="8086002101711328500">"不用了"</string>
    <string name="auto_saver_enabled_title" msgid="6726474226058316862">"预设的省电模式已开启"</string>
    <string name="auto_saver_enabled_text" msgid="874711029884777579">"一旦电池电量降到 <xliff:g id="PERCENTAGE">%d</xliff:g>%% 以下，省电模式就会自动开启。"</string>
    <string name="open_saver_setting_action" msgid="8314624730997322529">"设置"</string>
    <string name="auto_saver_okay_action" msgid="2701221740227683650">"知道了"</string>
    <string name="heap_dump_tile_name" msgid="9141031328971226374">"转储 SysUI 堆"</string>
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
    <string name="wifi_fail_to_scan" msgid="1265540342578081461">"无法扫描网络"</string>
    <string name="wifi_security_none" msgid="7985461072596594400">"无"</string>
    <string name="wifi_remembered" msgid="4955746899347821096">"已保存"</string>
    <string name="wifi_disabled_generic" msgid="4259794910584943386">"已停用"</string>
    <string name="wifi_disabled_network_failure" msgid="2364951338436007124">"IP 配置失败"</string>
    <string name="wifi_disabled_by_recommendation_provider" msgid="5168315140978066096">"网络质量较差，因此未连接"</string>
    <string name="wifi_disabled_wifi_failure" msgid="3081668066612876581">"WLAN 连接失败"</string>
    <string name="wifi_disabled_password_failure" msgid="8659805351763133575">"身份验证出现问题"</string>
    <string name="wifi_cant_connect" msgid="5410016875644565884">"无法连接"</string>
    <string name="wifi_cant_connect_to_ap" msgid="1222553274052685331">"无法连接到“<xliff:g id="AP_NAME">%1$s</xliff:g>”"</string>
    <string name="wifi_check_password_try_again" msgid="516958988102584767">"请检查密码，然后重试"</string>
    <string name="wifi_not_in_range" msgid="1136191511238508967">"不在范围内"</string>
    <string name="wifi_no_internet_no_reconnect" msgid="5724903347310541706">"无法自动连接"</string>
    <string name="wifi_no_internet" msgid="4663834955626848401">"无法访问互联网"</string>
    <string name="saved_network" msgid="4352716707126620811">"由“<xliff:g id="NAME">%1$s</xliff:g>”保存"</string>
    <string name="connected_via_network_scorer" msgid="5713793306870815341">"已通过%1$s自动连接"</string>
    <string name="connected_via_network_scorer_default" msgid="7867260222020343104">"已自动连接（通过网络评分服务提供方）"</string>
    <string name="connected_via_passpoint" msgid="2826205693803088747">"已通过%1$s连接"</string>
    <string name="available_via_passpoint" msgid="1617440946846329613">"可通过%1$s连接"</string>
    <string name="wifi_connected_no_internet" msgid="8202906332837777829">"已连接，但无法访问互联网"</string>
    <string name="wifi_status_no_internet" msgid="5784710974669608361">"无法访问互联网"</string>
    <string name="wifi_status_sign_in_required" msgid="123517180404752756">"必须登录"</string>
    <string name="wifi_ap_unable_to_handle_new_sta" msgid="5348824313514404541">"接入点暂时满载"</string>
    <string name="connected_via_carrier" msgid="7583780074526041912">"已通过%1$s连接"</string>
    <string name="available_via_carrier" msgid="1469036129740799053">"可通过%1$s连接"</string>
    <string name="speed_label_very_slow" msgid="1867055264243608530">"很慢"</string>
    <string name="speed_label_slow" msgid="813109590815810235">"慢"</string>
    <string name="speed_label_okay" msgid="2331665440671174858">"良好"</string>
    <string name="speed_label_medium" msgid="3175763313268941953">"适中"</string>
    <string name="speed_label_fast" msgid="7715732164050975057">"快"</string>
    <string name="speed_label_very_fast" msgid="2265363430784523409">"很快"</string>
    <string name="preference_summary_default_combination" msgid="8532964268242666060">"<xliff:g id="STATE">%1$s</xliff:g>/<xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
    <string name="bluetooth_disconnected" msgid="6557104142667339895">"已断开连接"</string>
    <string name="bluetooth_disconnecting" msgid="8913264760027764974">"正在断开连接..."</string>
    <string name="bluetooth_connecting" msgid="8555009514614320497">"正在连接..."</string>
    <string name="bluetooth_connected" msgid="5427152882755735944">"已连接<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_pairing" msgid="1426882272690346242">"正在配对..."</string>
    <string name="bluetooth_connected_no_headset" msgid="616068069034994802">"已连接（无手机信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_a2dp" msgid="3736431800395923868">"已连接（无媒体信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_map" msgid="3200033913678466453">"已连接（无消息访问权限）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_no_a2dp" msgid="2047403011284187056">"已连接（无手机或媒体信号）<xliff:g id="ACTIVE_DEVICE">%1$s</xliff:g>"</string>
    <string name="bluetooth_connected_battery_level" msgid="5162924691231307748">"已连接，电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_battery_level" msgid="1610296229139400266">"已连接（无手机信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_a2dp_battery_level" msgid="3908466636369853652">"已连接（无媒体信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_connected_no_headset_no_a2dp_battery_level" msgid="1163440823807659316">"已连接（无手机或媒体信号），电量为 <xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g> <xliff:g id="ACTIVE_DEVICE">%2$s</xliff:g>"</string>
    <string name="bluetooth_active_battery_level" msgid="3149689299296462009">"使用中，电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_battery_level" msgid="1447164613319663655">"电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_active_no_battery_level" msgid="8380223546730241956">"使用中"</string>
    <string name="bluetooth_profile_a2dp" msgid="2031475486179830674">"媒体音频"</string>
    <string name="bluetooth_profile_headset" msgid="7815495680863246034">"通话"</string>
    <string name="bluetooth_profile_opp" msgid="9168139293654233697">"文件传输"</string>
    <string name="bluetooth_profile_hid" msgid="3680729023366986480">"输入设备"</string>
    <string name="bluetooth_profile_pan" msgid="3391606497945147673">"互联网连接"</string>
    <string name="bluetooth_profile_pbap" msgid="5372051906968576809">"共享联系人"</string>
    <string name="bluetooth_profile_pbap_summary" msgid="6605229608108852198">"用于共享联系人"</string>
    <string name="bluetooth_profile_pan_nap" msgid="8429049285027482959">"共享互联网连接"</string>
    <string name="bluetooth_profile_map" msgid="1019763341565580450">"短信"</string>
    <string name="bluetooth_profile_sap" msgid="5764222021851283125">"SIM 卡存取权限"</string>
    <string name="bluetooth_profile_a2dp_high_quality" msgid="5444517801472820055">"HD 音频：<xliff:g id="CODEC_NAME">%1$s</xliff:g>"</string>
    <string name="bluetooth_profile_a2dp_high_quality_unknown_codec" msgid="8510588052415438887">"HD 音频"</string>
    <string name="bluetooth_profile_hearing_aid" msgid="7999237886427812595">"助听器"</string>
    <string name="bluetooth_hearing_aid_profile_summary_connected" msgid="7188282786730266159">"已连接到助听器"</string>
    <string name="bluetooth_a2dp_profile_summary_connected" msgid="963376081347721598">"已连接到媒体音频"</string>
    <string name="bluetooth_headset_profile_summary_connected" msgid="7661070206715520671">"已连接到手机音频"</string>
    <string name="bluetooth_opp_profile_summary_connected" msgid="2611913495968309066">"已连接到文件传输服务器"</string>
    <string name="bluetooth_map_profile_summary_connected" msgid="8191407438851351713">"已连接到地图"</string>
    <string name="bluetooth_sap_profile_summary_connected" msgid="8561765057453083838">"已连接到 SAP"</string>
    <string name="bluetooth_opp_profile_summary_not_connected" msgid="1267091356089086285">"未连接到文件传输服务器"</string>
    <string name="bluetooth_hid_profile_summary_connected" msgid="3381760054215168689">"已连接到输入设备"</string>
    <string name="bluetooth_pan_user_profile_summary_connected" msgid="6436258151814414028">"经由其他设备连接到互联网"</string>
    <string name="bluetooth_pan_nap_profile_summary_connected" msgid="1322694224800769308">"与其他设备共享该设备的互联网连接"</string>
    <string name="bluetooth_pan_profile_summary_use_for" msgid="5736111170225304239">"用于访问互联网"</string>
    <string name="bluetooth_map_profile_summary_use_for" msgid="5154200119919927434">"用于地图"</string>
    <string name="bluetooth_sap_profile_summary_use_for" msgid="7085362712786907993">"用于存取 SIM 卡"</string>
    <string name="bluetooth_a2dp_profile_summary_use_for" msgid="4630849022250168427">"用于媒体音频"</string>
    <string name="bluetooth_headset_profile_summary_use_for" msgid="8705753622443862627">"用于手机音频"</string>
    <string name="bluetooth_opp_profile_summary_use_for" msgid="1255674547144769756">"用于文件传输"</string>
    <string name="bluetooth_hid_profile_summary_use_for" msgid="232727040453645139">"用于输入"</string>
    <string name="bluetooth_hearing_aid_profile_summary_use_for" msgid="908775281788309484">"用于助听器"</string>
    <string name="bluetooth_pairing_accept" msgid="6163520056536604875">"配对"</string>
    <string name="bluetooth_pairing_accept_all_caps" msgid="6061699265220789149">"配对"</string>
    <string name="bluetooth_pairing_decline" msgid="4185420413578948140">"取消"</string>
    <string name="bluetooth_pairing_will_share_phonebook" msgid="4982239145676394429">"配对之后，所配对的设备将可以在建立连接后访问您的通讯录和通话记录。"</string>
    <string name="bluetooth_pairing_error_message" msgid="3748157733635947087">"无法与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”进行配对。"</string>
    <string name="bluetooth_pairing_pin_error_message" msgid="8337234855188925274">"PIN码或配对密钥不正确，无法与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对。"</string>
    <string name="bluetooth_pairing_device_down_error_message" msgid="7870998403045801381">"无法与“<xliff:g id="DEVICE_NAME">%1$s</xliff:g>”进行通信。"</string>
    <string name="bluetooth_pairing_rejected_error_message" msgid="1648157108520832454">"<xliff:g id="DEVICE_NAME">%1$s</xliff:g> 已拒绝配对。"</string>
    <string name="bluetooth_talkback_computer" msgid="4875089335641234463">"计算机"</string>
    <string name="bluetooth_talkback_headset" msgid="5140152177885220949">"耳麦"</string>
    <string name="bluetooth_talkback_phone" msgid="4260255181240622896">"手机"</string>
    <string name="bluetooth_talkback_imaging" msgid="551146170554589119">"成像设备"</string>
    <string name="bluetooth_talkback_headphone" msgid="26580326066627664">"耳机"</string>
    <string name="bluetooth_talkback_input_peripheral" msgid="5165842622743212268">"外围输入设备"</string>
    <string name="bluetooth_talkback_bluetooth" msgid="5615463912185280812">"蓝牙"</string>
    <string name="bluetooth_hearingaid_left_pairing_message" msgid="7378813500862148102">"正在配对左侧助听器…"</string>
    <string name="bluetooth_hearingaid_right_pairing_message" msgid="1550373802309160891">"正在配对右侧助听器…"</string>
    <string name="bluetooth_hearingaid_left_battery_level" msgid="8797811465352097562">"左侧 - 电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="bluetooth_hearingaid_right_battery_level" msgid="7309476148173459677">"右侧 - 电池电量：<xliff:g id="BATTERY_LEVEL_AS_PERCENTAGE">%1$s</xliff:g>"</string>
    <string name="accessibility_wifi_off" msgid="1166761729660614716">"WLAN 已关闭。"</string>
    <string name="accessibility_no_wifi" msgid="8834610636137374508">"WLAN 连接已断开。"</string>
    <string name="accessibility_wifi_one_bar" msgid="4869376278894301820">"WLAN 信号强度为一格。"</string>
    <string name="accessibility_wifi_two_bars" msgid="3569851234710034416">"WLAN 信号强度为两格。"</string>
    <string name="accessibility_wifi_three_bars" msgid="8134185644861380311">"WLAN 信号强度为三格。"</string>
    <string name="accessibility_wifi_signal_full" msgid="7061045677694702">"WLAN 信号满格。"</string>
    <string name="accessibility_wifi_security_type_none" msgid="1223747559986205423">"开放网络"</string>
    <string name="accessibility_wifi_security_type_secured" msgid="862921720418885331">"安全网络"</string>
    <string name="process_kernel_label" msgid="3916858646836739323">"Android 操作系统"</string>
    <string name="data_usage_uninstalled_apps" msgid="614263770923231598">"已删除的应用"</string>
    <string name="data_usage_uninstalled_apps_users" msgid="7986294489899813194">"已删除的应用和用户"</string>
    <string name="tether_settings_title_usb" msgid="6688416425801386511">"USB 网络共享"</string>
    <string name="tether_settings_title_wifi" msgid="3277144155960302049">"便携式热点"</string>
    <string name="tether_settings_title_bluetooth" msgid="355855408317564420">"蓝牙网络共享"</string>
    <string name="tether_settings_title_usb_bluetooth" msgid="5355828977109785001">"网络共享"</string>
    <string name="tether_settings_title_all" msgid="8356136101061143841">"网络共享与便携式热点"</string>
    <string name="managed_user_title" msgid="8109605045406748842">"所有工作应用"</string>
    <string name="user_guest" msgid="8475274842845401871">"访客"</string>
    <string name="unknown" msgid="1592123443519355854">"未知"</string>
    <string name="running_process_item_user_label" msgid="3129887865552025943">"用户：<xliff:g id="USER_NAME">%1$s</xliff:g>"</string>
    <string name="launch_defaults_some" msgid="313159469856372621">"已设置部分默认选项"</string>
    <string name="launch_defaults_none" msgid="4241129108140034876">"没有默认操作"</string>
    <string name="tts_settings" msgid="8186971894801348327">"文字转语音设置"</string>
    <string name="tts_settings_title" msgid="1237820681016639683">"文字转语音 (TTS) 输出"</string>
    <string name="tts_default_rate_title" msgid="6030550998379310088">"语速"</string>
    <string name="tts_default_rate_summary" msgid="4061815292287182801">"文字转换成语音后的播放速度"</string>
    <string name="tts_default_pitch_title" msgid="6135942113172488671">"音高"</string>
    <string name="tts_default_pitch_summary" msgid="1944885882882650009">"会影响合成语音的音调"</string>
    <string name="tts_default_lang_title" msgid="8018087612299820556">"语言"</string>
    <string name="tts_lang_use_system" msgid="2679252467416513208">"使用系统语言"</string>
    <string name="tts_lang_not_selected" msgid="7395787019276734765">"未选择语言"</string>
    <string name="tts_default_lang_summary" msgid="5219362163902707785">"设置文字转语音功能要使用的语言"</string>
    <string name="tts_play_example_title" msgid="7094780383253097230">"收听示例"</string>
    <string name="tts_play_example_summary" msgid="8029071615047894486">"播放简短的语音合成示例"</string>
    <string name="tts_install_data_title" msgid="4264378440508149986">"安装语音数据包"</string>
    <string name="tts_install_data_summary" msgid="5742135732511822589">"安装语音合成所需的数据包"</string>
    <string name="tts_engine_security_warning" msgid="8786238102020223650">"此语音合成引擎能够收集语音中出现的所有信息，包括密码和信用卡号码之类的个人数据。此功能由 <xliff:g id="TTS_PLUGIN_ENGINE_NAME">%s</xliff:g> 引擎提供。是否启用此语音合成引擎？"</string>
    <string name="tts_engine_network_required" msgid="1190837151485314743">"您必须连接到网络才能使用文字转语音功能输出这种语言。"</string>
    <string name="tts_default_sample_string" msgid="4040835213373086322">"这是语音合成示例"</string>
    <string name="tts_status_title" msgid="7268566550242584413">"默认语言状态"</string>
    <string name="tts_status_ok" msgid="1309762510278029765">"完全支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_requires_network" msgid="6042500821503226892">"只有在连接到网络的情况下，才支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_not_supported" msgid="4491154212762472495">"不支持<xliff:g id="LOCALE">%1$s</xliff:g>"</string>
    <string name="tts_status_checking" msgid="5339150797940483592">"正在检查…"</string>
    <string name="tts_engine_settings_title" msgid="3499112142425680334">"“<xliff:g id="TTS_ENGINE_NAME">%s</xliff:g>”的设置"</string>
    <string name="tts_engine_settings_button" msgid="1030512042040722285">"进行引擎设置"</string>
    <string name="tts_engine_preference_section_title" msgid="448294500990971413">"首选引擎"</string>
    <string name="tts_general_section_title" msgid="4402572014604490502">"常规"</string>
    <string name="tts_reset_speech_pitch_title" msgid="5789394019544785915">"重置语音音调"</string>
    <string name="tts_reset_speech_pitch_summary" msgid="8700539616245004418">"将文字的读出音调重置为默认值。"</string>
  <string-array name="tts_rate_entries">
    <item msgid="6695494874362656215">"很慢"</item>
    <item msgid="4795095314303559268">"慢"</item>
    <item msgid="8903157781070679765">"正常"</item>
    <item msgid="164347302621392996">"快"</item>
    <item msgid="5794028588101562009">"较快"</item>
    <item msgid="7163942783888652942">"非常快"</item>
    <item msgid="7831712693748700507">"超快"</item>
    <item msgid="5194774745031751806">"极快"</item>
    <item msgid="9085102246155045744">"最快"</item>
  </string-array>
    <string name="choose_profile" msgid="6921016979430278661">"选择个人资料"</string>
    <string name="category_personal" msgid="1299663247844969448">"个人"</string>
    <string name="category_work" msgid="8699184680584175622">"工作"</string>
    <string name="development_settings_title" msgid="215179176067683667">"开发者选项"</string>
    <string name="development_settings_enable" msgid="542530994778109538">"启用开发者选项"</string>
    <string name="development_settings_summary" msgid="1815795401632854041">"设置应用开发选项"</string>
    <string name="development_settings_not_available" msgid="4308569041701535607">"此用户无法使用开发者选项"</string>
    <string name="vpn_settings_not_available" msgid="956841430176985598">"此用户无权修改VPN设置"</string>
    <string name="tethering_settings_not_available" msgid="6765770438438291012">"此用户无权修改网络共享设置"</string>
    <string name="apn_settings_not_available" msgid="7873729032165324000">"此用户无权修改接入点名称设置"</string>
    <string name="enable_adb" msgid="7982306934419797485">"USB 调试"</string>
    <string name="enable_adb_summary" msgid="4881186971746056635">"连接 USB 后启用调试模式"</string>
    <string name="clear_adb_keys" msgid="4038889221503122743">"撤消 USB 调试授权"</string>
    <string name="bugreport_in_power" msgid="7923901846375587241">"错误报告快捷方式"</string>
    <string name="bugreport_in_power_summary" msgid="1778455732762984579">"在电源菜单中显示用于提交错误报告的按钮"</string>
    <string name="keep_screen_on" msgid="1146389631208760344">"不锁定屏幕"</string>
    <string name="keep_screen_on_summary" msgid="2173114350754293009">"充电时屏幕不会休眠"</string>
    <string name="bt_hci_snoop_log" msgid="3340699311158865670">"启用蓝牙 HCI 信息收集日志"</string>
    <string name="bt_hci_snoop_log_summary" msgid="366083475849911315">"捕获单个文件中的所有蓝牙 HCI 包（更改此设置之后切换蓝牙开关）"</string>
    <string name="oem_unlock_enable" msgid="6040763321967327691">"OEM 解锁"</string>
    <string name="oem_unlock_enable_summary" msgid="4720281828891618376">"允许解锁引导加载程序"</string>
    <string name="confirm_enable_oem_unlock_title" msgid="4802157344812385674">"要允许 OEM 解锁吗？"</string>
    <string name="confirm_enable_oem_unlock_text" msgid="5517144575601647022">"警告：如果开启此设置，此设备上的设备保护功能将无法使用。"</string>
    <string name="mock_location_app" msgid="7966220972812881854">"选择模拟位置信息应用"</string>
    <string name="mock_location_app_not_set" msgid="809543285495344223">"尚未设置模拟位置信息应用"</string>
    <string name="mock_location_app_set" msgid="8966420655295102685">"模拟位置信息应用：<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="debug_networking_category" msgid="7044075693643009662">"网络"</string>
    <string name="wifi_display_certification" msgid="8611569543791307533">"无线显示认证"</string>
    <string name="wifi_verbose_logging" msgid="4203729756047242344">"启用 WLAN 详细日志记录功能"</string>
    <string name="wifi_connected_mac_randomization" msgid="3168165236877957767">"连接时随机选择 MAC 网址"</string>
    <string name="mobile_data_always_on" msgid="8774857027458200434">"始终开启移动数据网络"</string>
    <string name="tethering_hardware_offload" msgid="7470077827090325814">"网络共享硬件加速"</string>
    <string name="bluetooth_show_devices_without_names" msgid="4708446092962060176">"显示没有名称的蓝牙设备"</string>
    <string name="bluetooth_disable_absolute_volume" msgid="2660673801947898809">"停用绝对音量功能"</string>
    <string name="bluetooth_select_avrcp_version_string" msgid="3750059931120293633">"蓝牙 AVRCP 版本"</string>
    <string name="bluetooth_select_avrcp_version_dialog_title" msgid="7277329668298705702">"选择蓝牙 AVRCP 版本"</string>
    <string name="bluetooth_select_a2dp_codec_type" msgid="90597356942154882">"蓝牙音频编解码器"</string>
    <string name="bluetooth_select_a2dp_codec_type_dialog_title" msgid="8436224899475822557">"触发蓝牙音频编解码器\n选择"</string>
    <string name="bluetooth_select_a2dp_codec_sample_rate" msgid="4788245703824623062">"蓝牙音频采样率"</string>
    <string name="bluetooth_select_a2dp_codec_sample_rate_dialog_title" msgid="8010380028880963535">"触发蓝牙音频编解码器\n选择：采样率"</string>
    <string name="bluetooth_select_a2dp_codec_bits_per_sample" msgid="2099645202720164141">"蓝牙音频每样本位数"</string>
    <string name="bluetooth_select_a2dp_codec_bits_per_sample_dialog_title" msgid="8063859754619484760">"触发蓝牙音频编解码器\n选择：每样本位数"</string>
    <string name="bluetooth_select_a2dp_codec_channel_mode" msgid="884855779449390540">"蓝牙音频声道模式"</string>
    <string name="bluetooth_select_a2dp_codec_channel_mode_dialog_title" msgid="7234956835280563341">"触发蓝牙音频编解码器\n选择：声道模式"</string>
    <string name="bluetooth_select_a2dp_codec_ldac_playback_quality" msgid="3619694372407843405">"蓝牙音频 LDAC 编解码器：播放质量"</string>
    <string name="bluetooth_select_a2dp_codec_ldac_playback_quality_dialog_title" msgid="6893955536658137179">"触发蓝牙音频 LDAC\n编解码器选择：播放质量"</string>
    <string name="bluetooth_select_a2dp_codec_streaming_label" msgid="5347862512596240506">"正在流式传输：<xliff:g id="STREAMING_PARAMETER">%1$s</xliff:g>"</string>
    <string name="select_private_dns_configuration_title" msgid="3700456559305263922">"私人 DNS"</string>
    <string name="select_private_dns_configuration_dialog_title" msgid="9221994415765826811">"选择私人 DNS 模式"</string>
    <string name="private_dns_mode_off" msgid="8236575187318721684">"关闭"</string>
    <string name="private_dns_mode_opportunistic" msgid="8314986739896927399">"自动"</string>
    <string name="private_dns_mode_provider" msgid="8354935160639360804">"私人 DNS 提供商主机名"</string>
    <string name="private_dns_mode_provider_hostname_hint" msgid="2487492386970928143">"输入 DNS 提供商的主机名"</string>
    <string name="private_dns_mode_provider_failure" msgid="231837290365031223">"无法连接"</string>
    <string name="wifi_display_certification_summary" msgid="1155182309166746973">"显示无线显示认证选项"</string>
    <string name="wifi_verbose_logging_summary" msgid="6615071616111731958">"提升 WLAN 日志记录级别（在 WLAN 选择器中显示每个 SSID 的 RSSI）"</string>
    <string name="wifi_connected_mac_randomization_summary" msgid="1743059848752201485">"连接到 WLAN 网络时随机选择 MAC 地址"</string>
    <string name="wifi_metered_label" msgid="4514924227256839725">"按流量计费"</string>
    <string name="wifi_unmetered_label" msgid="6124098729457992931">"不按流量计费"</string>
    <string name="select_logd_size_title" msgid="7433137108348553508">"日志记录器缓冲区大小"</string>
    <string name="select_logd_size_dialog_title" msgid="1206769310236476760">"选择每个日志缓冲区的日志记录器大小"</string>
    <string name="dev_logpersist_clear_warning_title" msgid="684806692440237967">"要清除永久存储的日志记录器数据吗？"</string>
    <string name="dev_logpersist_clear_warning_message" msgid="2256582531342994562">"当我们不再使用永久日志记录器进行监控时，我们需要清除保存在您设备上的日志记录器数据。"</string>
    <string name="select_logpersist_title" msgid="7530031344550073166">"在设备上永久存储日志记录器数据"</string>
    <string name="select_logpersist_dialog_title" msgid="4003400579973269060">"选择要在设备上永久存储的日志缓冲区"</string>
    <string name="select_usb_configuration_title" msgid="2649938511506971843">"选择USB配置"</string>
    <string name="select_usb_configuration_dialog_title" msgid="6385564442851599963">"选择USB配置"</string>
    <string name="allow_mock_location" msgid="2787962564578664888">"允许模拟位置"</string>
    <string name="allow_mock_location_summary" msgid="317615105156345626">"允许模拟位置"</string>
    <string name="debug_view_attributes" msgid="6485448367803310384">"启用视图属性检查功能"</string>
    <string name="mobile_data_always_on_summary" msgid="8149773901431697910">"始终开启移动数据网络，即使 WLAN 网络已开启（便于快速切换网络）。"</string>
    <string name="tethering_hardware_offload_summary" msgid="7726082075333346982">"使用网络共享硬件加速功能（如果可用）"</string>
    <string name="adb_warning_title" msgid="6234463310896563253">"是否允许 USB 调试？"</string>
    <string name="adb_warning_message" msgid="7316799925425402244">"USB 调试仅用于开发目的。该功能可用于在您的计算机和设备之间复制数据、在您的设备上安装应用（事先不发通知）以及读取日志数据。"</string>
    <string name="adb_keys_warning_message" msgid="5659849457135841625">"是否针对您之前授权的所有计算机撤消 USB 调试的访问权限？"</string>
    <string name="dev_settings_warning_title" msgid="7244607768088540165">"允许开发设置？"</string>
    <string name="dev_settings_warning_message" msgid="2298337781139097964">"这些设置仅适用于开发工作。一旦启用，会导致您的设备以及设备上的应用崩溃或出现异常。"</string>
    <string name="verify_apps_over_usb_title" msgid="4177086489869041953">"通过USB验证应用"</string>
    <string name="verify_apps_over_usb_summary" msgid="9164096969924529200">"通过 ADB/ADT 检查安装的应用是否存在有害行为。"</string>
    <string name="bluetooth_show_devices_without_names_summary" msgid="2351196058115755520">"系统将显示没有名称（只有 MAC 地址）的蓝牙设备"</string>
    <string name="bluetooth_disable_absolute_volume_summary" msgid="6031284410786545957">"停用蓝牙绝对音量功能，即可避免在连接到远程设备时出现音量问题（例如音量高得让人无法接受或无法控制音量等）。"</string>
    <string name="enable_terminal_title" msgid="95572094356054120">"本地终端"</string>
    <string name="enable_terminal_summary" msgid="67667852659359206">"启用终端应用，以便在本地访问 Shell"</string>
    <string name="hdcp_checking_title" msgid="8605478913544273282">"HDCP 检查"</string>
    <string name="hdcp_checking_dialog_title" msgid="5141305530923283">"设置 HDCP 检查行为"</string>
    <string name="debug_debugging_category" msgid="6781250159513471316">"调试"</string>
    <string name="debug_app" msgid="8349591734751384446">"选择调试应用"</string>
    <string name="debug_app_not_set" msgid="718752499586403499">"未设置任何调试应用"</string>
    <string name="debug_app_set" msgid="2063077997870280017">"调试应用：<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <string name="select_application" msgid="5156029161289091703">"选择应用"</string>
    <string name="no_application" msgid="2813387563129153880">"无"</string>
    <string name="wait_for_debugger" msgid="1202370874528893091">"等待调试程序"</string>
    <string name="wait_for_debugger_summary" msgid="1766918303462746804">"调试应用会在执行前等待附加调试器"</string>
    <string name="debug_input_category" msgid="1811069939601180246">"输入"</string>
    <string name="debug_drawing_category" msgid="6755716469267367852">"绘图"</string>
    <string name="debug_hw_drawing_category" msgid="6220174216912308658">"硬件加速渲染"</string>
    <string name="media_category" msgid="4388305075496848353">"媒体"</string>
    <string name="debug_monitoring_category" msgid="7640508148375798343">"监控"</string>
    <string name="strict_mode" msgid="1938795874357830695">"启用严格模式"</string>
    <string name="strict_mode_summary" msgid="142834318897332338">"应用在主线程上执行长时间操作时闪烁屏幕"</string>
    <string name="pointer_location" msgid="6084434787496938001">"指针位置"</string>
    <string name="pointer_location_summary" msgid="840819275172753713">"屏幕叠加层显示当前触摸数据"</string>
    <string name="show_touches" msgid="2642976305235070316">"显示点按操作反馈"</string>
    <string name="show_touches_summary" msgid="6101183132903926324">"显示点按操作的视觉反馈"</string>
    <string name="show_screen_updates" msgid="5470814345876056420">"显示面 (surface) 更新"</string>
    <string name="show_screen_updates_summary" msgid="2569622766672785529">"窗口中的面 (surface) 更新时全部闪烁"</string>
    <string name="show_hw_screen_updates" msgid="5036904558145941590">"显示 GPU 视图更新"</string>
    <string name="show_hw_screen_updates_summary" msgid="1115593565980196197">"使用 GPU 进行绘图时闪烁显示窗口中的视图"</string>
    <string name="show_hw_layers_updates" msgid="5645728765605699821">"显示硬件层更新"</string>
    <string name="show_hw_layers_updates_summary" msgid="5296917233236661465">"Flash 硬件层在进行更新时会显示为绿色"</string>
    <string name="debug_hw_overdraw" msgid="2968692419951565417">"调试 GPU 过度绘制"</string>
    <string name="disable_overlays" msgid="2074488440505934665">"停用 HW 叠加层"</string>
    <string name="disable_overlays_summary" msgid="3578941133710758592">"始终使用 GPU 进行屏幕合成"</string>
    <string name="simulate_color_space" msgid="6745847141353345872">"模拟颜色空间"</string>
    <string name="enable_opengl_traces_title" msgid="6790444011053219871">"启用 OpenGL 跟踪"</string>
    <string name="usb_audio_disable_routing" msgid="8114498436003102671">"关闭 USB 音频转接"</string>
    <string name="usb_audio_disable_routing_summary" msgid="980282760277312264">"关闭自动转接至 USB 音频外围设备的功能"</string>
    <string name="debug_layout" msgid="5981361776594526155">"显示布局边界"</string>
    <string name="debug_layout_summary" msgid="2001775315258637682">"显示剪辑边界、边距等。"</string>
    <string name="force_rtl_layout_all_locales" msgid="2259906643093138978">"强制使用从右到左的布局方向"</string>
    <string name="force_rtl_layout_all_locales_summary" msgid="9192797796616132534">"强制将所有语言区域的屏幕布局方向改为从右到左"</string>
    <string name="force_hw_ui" msgid="6426383462520888732">"强制进行 GPU 渲染"</string>
    <string name="force_hw_ui_summary" msgid="5535991166074861515">"强制使用 GPU 进行 2D 绘图"</string>
    <string name="force_msaa" msgid="7920323238677284387">"强制启用 4x MSAA"</string>
    <string name="force_msaa_summary" msgid="9123553203895817537">"在 OpenGL ES 2.0 应用中启用 4x MSAA"</string>
    <string name="show_non_rect_clip" msgid="505954950474595172">"调试非矩形剪裁操作"</string>
    <string name="track_frame_time" msgid="6146354853663863443">"GPU 渲染模式分析"</string>
    <string name="enable_gpu_debug_layers" msgid="3848838293793255097">"启用 GPU 调试层"</string>
    <string name="enable_gpu_debug_layers_summary" msgid="8009136940671194940">"允许为调试应用加载 GPU 调试层"</string>
    <string name="window_animation_scale_title" msgid="6162587588166114700">"窗口动画缩放"</string>
    <string name="transition_animation_scale_title" msgid="387527540523595875">"过渡动画缩放"</string>
    <string name="animator_duration_scale_title" msgid="3406722410819934083">"Animator 时长缩放"</string>
    <string name="overlay_display_devices_title" msgid="5364176287998398539">"模拟辅助显示设备"</string>
    <string name="debug_applications_category" msgid="4206913653849771549">"应用"</string>
    <string name="immediately_destroy_activities" msgid="1579659389568133959">"不保留活动"</string>
    <string name="immediately_destroy_activities_summary" msgid="3592221124808773368">"用户离开后即销毁每个活动"</string>
    <string name="app_process_limit_title" msgid="4280600650253107163">"后台进程限制"</string>
    <string name="show_all_anrs" msgid="4924885492787069007">"显示后台 ANR"</string>
    <string name="show_all_anrs_summary" msgid="6636514318275139826">"为后台应用显示“应用无响应”对话框"</string>
    <string name="show_notification_channel_warnings" msgid="1399948193466922683">"显示通知渠道警告"</string>
    <string name="show_notification_channel_warnings_summary" msgid="5536803251863694895">"当应用未经有效渠道发布通知时，在屏幕上显示警告"</string>
    <string name="force_allow_on_external" msgid="3215759785081916381">"强制允许将应用写入外部存储设备"</string>
    <string name="force_allow_on_external_summary" msgid="3640752408258034689">"允许将任何应用写入外部存储设备（无论清单值是什么）"</string>
    <string name="force_resizable_activities" msgid="8615764378147824985">"强制将活动设为可调整大小"</string>
    <string name="force_resizable_activities_summary" msgid="6667493494706124459">"将所有 Activity 设为可配合多窗口环境调整大小（忽略清单值）。"</string>
    <string name="enable_freeform_support" msgid="1461893351278940416">"启用可自由调整的窗口"</string>
    <string name="enable_freeform_support_summary" msgid="8247310463288834487">"启用可自由调整的窗口这一实验性功能。"</string>
    <string name="local_backup_password_title" msgid="3860471654439418822">"桌面备份密码"</string>
    <string name="local_backup_password_summary_none" msgid="6951095485537767956">"桌面完整备份当前未设置密码保护"</string>
    <string name="local_backup_password_summary_change" msgid="5376206246809190364">"点按即可更改或移除用于保护桌面完整备份的密码"</string>
    <string name="local_backup_password_toast_success" msgid="582016086228434290">"已设置了新的备份密码"</string>
    <string name="local_backup_password_toast_confirmation_mismatch" msgid="7805892532752708288">"新密码和确认密码不一致"</string>
    <string name="local_backup_password_toast_validation_failure" msgid="5646377234895626531">"设置备份密码失败"</string>
  <string-array name="color_mode_names">
    <item msgid="2425514299220523812">"鲜亮（默认）"</item>
    <item msgid="8446070607501413455">"自然"</item>
    <item msgid="6553408765810699025">"标准"</item>
  </string-array>
  <string-array name="color_mode_descriptions">
    <item msgid="4979629397075120893">"增强的颜色"</item>
    <item msgid="8280754435979370728">"肉眼所看到的自然颜色"</item>
    <item msgid="5363960654009010371">"针对数字内容优化的颜色"</item>
  </string-array>
    <string name="inactive_apps_title" msgid="9042996804461901648">"待机应用"</string>
    <string name="inactive_app_inactive_summary" msgid="5091363706699855725">"未启用。点按即可切换。"</string>
    <string name="inactive_app_active_summary" msgid="4174921824958516106">"已启用。点按即可切换。"</string>
    <string name="standby_bucket_summary" msgid="6567835350910684727">"应用待机状态：<xliff:g id="BUCKET"> %s</xliff:g>"</string>
    <string name="runningservices_settings_title" msgid="8097287939865165213">"正在运行的服务"</string>
    <string name="runningservices_settings_summary" msgid="854608995821032748">"查看和控制当前正在运行的服务"</string>
    <string name="select_webview_provider_title" msgid="4628592979751918907">"WebView 实现"</string>
    <string name="select_webview_provider_dialog_title" msgid="4370551378720004872">"设置 WebView 实现"</string>
    <string name="select_webview_provider_toast_text" msgid="5466970498308266359">"此选项已失效，请重试。"</string>
    <string name="convert_to_file_encryption" msgid="3060156730651061223">"转换为文件加密"</string>
    <string name="convert_to_file_encryption_enabled" msgid="2861258671151428346">"转换…"</string>
    <string name="convert_to_file_encryption_done" msgid="7859766358000523953">"文件已加密"</string>
    <string name="title_convert_fbe" msgid="1263622876196444453">"正在转换为文件加密"</string>
    <string name="convert_to_fbe_warning" msgid="6139067817148865527">"将数据分区转换为文件加密。\n！！警告！！此操作将会清除您所有的数据。\n此功能为 Alpha 版功能，可能无法正常运行。\n要继续操作，请按“清除并转换…”。"</string>
    <string name="button_convert_fbe" msgid="5152671181309826405">"清除并转换…"</string>
    <string name="picture_color_mode" msgid="4560755008730283695">"图片颜色模式"</string>
    <string name="picture_color_mode_desc" msgid="1141891467675548590">"使用 sRGB"</string>
    <string name="daltonizer_mode_disabled" msgid="7482661936053801862">"已停用"</string>
    <string name="daltonizer_mode_monochromacy" msgid="8485709880666106721">"全色盲"</string>
    <string name="daltonizer_mode_deuteranomaly" msgid="5475532989673586329">"绿色弱视（红绿不分）"</string>
    <string name="daltonizer_mode_protanomaly" msgid="8424148009038666065">"红色弱视（红绿不分）"</string>
    <string name="daltonizer_mode_tritanomaly" msgid="481725854987912389">"蓝色弱视（蓝黄不分）"</string>
    <string name="accessibility_display_daltonizer_preference_title" msgid="5800761362678707872">"色彩校正"</string>
    <string name="accessibility_display_daltonizer_preference_subtitle" msgid="3484969015295282911">"这是实验性功能，性能可能不稳定。"</string>
    <string name="daltonizer_type_overridden" msgid="3116947244410245916">"已被“<xliff:g id="TITLE">%1$s</xliff:g>”覆盖"</string>
    <string name="power_remaining_settings_home_page" msgid="4845022416859002011">"<xliff:g id="PERCENTAGE">%1$s</xliff:g> - <xliff:g id="TIME_STRING">%2$s</xliff:g>"</string>
    <string name="power_remaining_duration_only" msgid="6123167166221295462">"大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharging_duration" msgid="8848256785736335185">"大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_duration_only_enhanced" msgid="4189311599812296592">"根据您的使用情况，大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharging_duration_enhanced" msgid="1992003260664804080">"根据您的使用情况，大约还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_duration_only_short" msgid="3463575350656389957">"还可使用 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_discharge_by_enhanced" msgid="2095821536747992464">"根据您的使用情况，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>（目前电量为 <xliff:g id="LEVEL">%2$s</xliff:g>）"</string>
    <string name="power_discharge_by_only_enhanced" msgid="2175151772952365149">"根据您的使用情况，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_discharge_by" msgid="6453537733650125582">"目前电量为 <xliff:g id="LEVEL">%2$s</xliff:g>，估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_discharge_by_only" msgid="107616694963545745">"估计大约还能用到<xliff:g id="TIME">%1$s</xliff:g>"</string>
    <string name="power_remaining_less_than_duration_only" msgid="5996752448813295329">"剩余电池续航时间不到 <xliff:g id="THRESHOLD">%1$s</xliff:g>"</string>
    <string name="power_remaining_less_than_duration" msgid="5751885147712659423">"电量剩余使用时间不到 <xliff:g id="THRESHOLD">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_more_than_subtext" msgid="3176771815132876675">"电量剩余使用时间超过 <xliff:g id="TIME_REMAINING">%1$s</xliff:g> (<xliff:g id="LEVEL">%2$s</xliff:g>)"</string>
    <string name="power_remaining_only_more_than_subtext" msgid="8931654680569617380">"电量剩余使用时间超过 <xliff:g id="TIME_REMAINING">%1$s</xliff:g>"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="default" msgid="1181059207608751924">"手机可能即将关机"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="tablet" msgid="2606370266981054691">"平板电脑可能即将关机"</string>
    <string name="power_remaining_duration_only_shutdown_imminent" product="device" msgid="2918084807716859985">"设备可能即将关机"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="default" msgid="3090926004324573908">"手机可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="tablet" msgid="7466484148515796216">"平板电脑可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_remaining_duration_shutdown_imminent" product="device" msgid="603933521600231649">"设备可能即将关机 (<xliff:g id="LEVEL">%1$s</xliff:g>)"</string>
    <string name="power_charging" msgid="1779532561355864267">"<xliff:g id="LEVEL">%1$s</xliff:g> - <xliff:g id="STATE">%2$s</xliff:g>"</string>
    <string name="power_remaining_charging_duration_only" msgid="1421102457410268886">"还需 <xliff:g id="TIME">%1$s</xliff:g>充满电"</string>
    <string name="power_charging_duration" msgid="4676999980973411875">"<xliff:g id="LEVEL">%1$s</xliff:g> - 还需 <xliff:g id="TIME">%2$s</xliff:g>充满"</string>
    <string name="battery_info_status_unknown" msgid="196130600938058547">"未知"</string>
    <string name="battery_info_status_charging" msgid="1705179948350365604">"正在充电"</string>
    <string name="battery_info_status_charging_lower" msgid="8689770213898117994">"正在充电"</string>
    <string name="battery_info_status_discharging" msgid="310932812698268588">"未在充电"</string>
    <string name="battery_info_status_not_charging" msgid="8523453668342598579">"已插入电源，但是现在无法充电"</string>
    <string name="battery_info_status_full" msgid="2824614753861462808">"电量充足"</string>
    <string name="disabled_by_admin_summary_text" msgid="6750513964908334617">"由管理员控制"</string>
    <string name="enabled_by_admin" msgid="5302986023578399263">"已被管理员启用"</string>
    <string name="disabled_by_admin" msgid="8505398946020816620">"已被管理员停用"</string>
    <string name="disabled" msgid="9206776641295849915">"已停用"</string>
    <string name="external_source_trusted" msgid="2707996266575928037">"允许"</string>
    <string name="external_source_untrusted" msgid="2677442511837596726">"不允许"</string>
    <string name="install_other_apps" msgid="6986686991775883017">"安装未知应用"</string>
    <string name="home" msgid="3256884684164448244">"设置主屏幕"</string>
  <string-array name="battery_labels">
    <item msgid="8494684293649631252">"0%"</item>
    <item msgid="8934126114226089439">"50%"</item>
    <item msgid="1286113608943010849">"100%"</item>
  </string-array>
    <string name="charge_length_format" msgid="8978516217024434156">"<xliff:g id="ID_1">%1$s</xliff:g>前"</string>
    <string name="remaining_length_format" msgid="7886337596669190587">"还剩 <xliff:g id="ID_1">%1$s</xliff:g>"</string>
    <string name="screen_zoom_summary_small" msgid="5867245310241621570">"小"</string>
    <string name="screen_zoom_summary_default" msgid="2247006805614056507">"默认"</string>
    <string name="screen_zoom_summary_large" msgid="4835294730065424084">"大"</string>
    <string name="screen_zoom_summary_very_large" msgid="7108563375663670067">"较大"</string>
    <string name="screen_zoom_summary_extremely_large" msgid="7427320168263276227">"最大"</string>
    <string name="screen_zoom_summary_custom" msgid="5611979864124160447">"自定义 (<xliff:g id="DENSITYDPI">%d</xliff:g>)"</string>
    <string name="help_feedback_label" msgid="6815040660801785649">"帮助和反馈"</string>
    <string name="content_description_menu_button" msgid="8182594799812351266">"菜单"</string>
    <string name="retail_demo_reset_message" msgid="118771671364131297">"输入密码即可在演示模式下恢复出厂设置"</string>
    <string name="retail_demo_reset_next" msgid="8356731459226304963">"下一步"</string>
    <string name="retail_demo_reset_title" msgid="696589204029930100">"需要输入密码"</string>
    <string name="active_input_method_subtypes" msgid="3596398805424733238">"有效的输入法"</string>
    <string name="use_system_language_to_select_input_method_subtypes" msgid="5747329075020379587">"使用系统语言"</string>
    <string name="failed_to_open_app_settings_toast" msgid="1251067459298072462">"无法打开 <xliff:g id="SPELL_APPLICATION_NAME">%1$s</xliff:g> 的设置"</string>
    <string name="ime_security_warning" msgid="4135828934735934248">"此输入法可能会收集您输入的所有内容，包括密码和信用卡号等个人数据。它来自“<xliff:g id="IME_APPLICATION_NAME">%1$s</xliff:g>”应用。要使用此输入法吗？"</string>
    <string name="direct_boot_unaware_dialog_message" msgid="7870273558547549125">"注意：重新启动后，您必须将手机解锁才能运行此应用"</string>
    <string name="ims_reg_title" msgid="7609782759207241443">"IMS 注册状态"</string>
    <string name="ims_reg_status_registered" msgid="933003316932739188">"已注册"</string>
    <string name="ims_reg_status_not_registered" msgid="6529783773485229486">"未注册"</string>
    <string name="status_unavailable" msgid="7862009036663793314">"无法获取"</string>
    <string name="wifi_status_mac_randomized" msgid="5589328382467438245">"MAC 已随机化"</string>
    <plurals name="wifi_tether_connected_summary" formatted="false" msgid="3871603864314407780">
      <item quantity="other">已连接 %1$d 个设备</item>
      <item quantity="one">已连接 %1$d 个设备</item>
    </plurals>
    <string name="accessibility_manual_zen_more_time" msgid="1636187409258564291">"增加时间。"</string>
    <string name="accessibility_manual_zen_less_time" msgid="6590887204171164991">"减少时间。"</string>
    <string name="cancel" msgid="6859253417269739139">"取消"</string>
    <string name="okay" msgid="1997666393121016642">"确定"</string>
    <string name="zen_mode_enable_dialog_turn_on" msgid="8287824809739581837">"开启"</string>
    <string name="zen_mode_settings_turn_on_dialog_title" msgid="2297134204747331078">"开启“勿扰”模式"</string>
    <string name="zen_mode_settings_summary_off" msgid="6119891445378113334">"永不"</string>
    <string name="zen_interruption_level_priority" msgid="2078370238113347720">"仅限优先事项"</string>
    <string name="zen_mode_and_condition" msgid="4927230238450354412">"<xliff:g id="ZEN_MODE">%1$s</xliff:g>。<xliff:g id="EXIT_CONDITION">%2$s</xliff:g>"</string>
    <string name="zen_alarm_warning_indef" msgid="3007988140196673193">"您将不会听到下一个<xliff:g id="WHEN">%1$s</xliff:g> 的闹钟响铃，除非您在该时间之前关闭这项功能"</string>
    <string name="zen_alarm_warning" msgid="6236690803924413088">"您将不会听到下一个<xliff:g id="WHEN">%1$s</xliff:g> 的闹钟响铃"</string>
    <string name="alarm_template" msgid="4996153414057676512">"时间：<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="alarm_template_far" msgid="3779172822607461675">"时间：<xliff:g id="WHEN">%1$s</xliff:g>"</string>
    <string name="zen_mode_duration_settings_title" msgid="229547412251222757">"持续时间"</string>
    <string name="zen_mode_duration_always_prompt_title" msgid="6478923750878945501">"每次都询问"</string>
    <string name="time_unit_just_now" msgid="6363336622778342422">"刚刚"</string>
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
    <item msgid="1922181315419294640"></item>
    <item msgid="8934131797783724664">"正在扫描..."</item>
    <item msgid="8513729475867537913">"正在连接..."</item>
    <item msgid="515055375277271756">"正在验证身份…"</item>
    <item msgid="1943354004029184381">"正在获取IP地址..."</item>
    <item msgid="4221763391123233270">"已连接"</item>
    <item msgid="624838831631122137">"已暂停"</item>
    <item msgid="7979680559596111948">"正在断开连接..."</item>
    <item msgid="1634960474403853625">"已断开连接"</item>
    <item msgid="746097431216080650">"失败"</item>
    <item msgid="6367044185730295334">"已停用"</item>
    <item msgid="503942654197908005">"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="wifi_status_with_ssid">
    <item msgid="7714855332363650812"></item>
    <item msgid="8878186979715711006">"正在扫描..."</item>
    <item msgid="355508996603873860">"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item msgid="554971459996405634">"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item msgid="7928343808033020343">"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item msgid="8937994881315223448">"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item msgid="1330262655415760617">"已暂停"</item>
    <item msgid="7698638434317271902">"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item msgid="197508606402264311">"已断开连接"</item>
    <item msgid="8578370891960825148">"失败"</item>
    <item msgid="5660739516542454527">"已停用"</item>
    <item msgid="1805837518286731242">"暂时关闭（网络状况不佳）"</item>
  </string-array>
  <string-array name="hdcp_checking_titles">
    <item msgid="441827799230089869">"永不检查"</item>
    <item msgid="6042769699089883931">"仅检查 DRM 内容"</item>
    <item msgid="9174900380056846820">"总是检查"</item>
  </string-array>
  <string-array name="hdcp_checking_summaries">
    <item msgid="505558545611516707">"一律不使用 HDCP 检查"</item>
    <item msgid="3878793616631049349">"仅使用 HDCP 检查 DRM 内容"</item>
    <item msgid="45075631231212732">"始终使用 HDCP 检查"</item>
  </string-array>
  <string-array name="bluetooth_avrcp_versions">
    <item msgid="5347678900838034763">"AVRCP 1.4（默认）"</item>
    <item msgid="2809759619990248160">"AVRCP 1.3"</item>
    <item msgid="6199178154704729352">"AVRCP 1.5"</item>
    <item msgid="5172170854953034852">"AVRCP 1.6"</item>
  </string-array>
  <string-array name="bluetooth_avrcp_version_values">
    <item msgid="2838624067805073303">"avrcp14"</item>
    <item msgid="3011533352527449572">"avrcp13"</item>
    <item msgid="8837606198371920819">"avrcp15"</item>
    <item msgid="3422726142222090896">"avrcp16"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_titles">
    <item msgid="7065842274271279580">"使用系统选择（默认）"</item>
    <item msgid="7539690996561263909">"SBC"</item>
    <item msgid="686685526567131661">"AAC"</item>
    <item msgid="5254942598247222737">"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX">aptX™</xliff:g> 音频"</item>
    <item msgid="2091430979086738145">"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX_HD">aptX™ HD</xliff:g> 音频"</item>
    <item msgid="6751080638867012696">"LDAC"</item>
    <item msgid="723675059572222462">"启用可选编解码器"</item>
    <item msgid="3304843301758635896">"停用可选编解码器"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_summaries">
    <item msgid="5062108632402595000">"使用系统选择（默认）"</item>
    <item msgid="6898329690939802290">"SBC"</item>
    <item msgid="6839647709301342559">"AAC"</item>
    <item msgid="7848030269621918608">"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX">aptX™</xliff:g> 音频"</item>
    <item msgid="298198075927343893">"<xliff:g id="QUALCOMM">Qualcomm®</xliff:g> <xliff:g id="APTX_HD">aptX™ HD</xliff:g> 音频"</item>
    <item msgid="7950781694447359344">"LDAC"</item>
    <item msgid="2209680154067241740">"启用可选编解码器"</item>
    <item msgid="741805482892725657">"停用可选编解码器"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_sample_rate_titles">
    <item msgid="3093023430402746802">"使用系统选择（默认）"</item>
    <item msgid="8895532488906185219">"44.1 kHz"</item>
    <item msgid="2909915718994807056">"48.0 kHz"</item>
    <item msgid="3347287377354164611">"88.2 kHz"</item>
    <item msgid="1234212100239985373">"96.0 kHz"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_sample_rate_summaries">
    <item msgid="3214516120190965356">"使用系统选择（默认）"</item>
    <item msgid="4482862757811638365">"44.1 kHz"</item>
    <item msgid="354495328188724404">"48.0 kHz"</item>
    <item msgid="7329816882213695083">"88.2 kHz"</item>
    <item msgid="6967397666254430476">"96.0 kHz"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_bits_per_sample_titles">
    <item msgid="2684127272582591429">"使用系统选择（默认）"</item>
    <item msgid="5618929009984956469">"16 位/样本"</item>
    <item msgid="3412640499234627248">"24 位/样本"</item>
    <item msgid="121583001492929387">"32 位/样本"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_bits_per_sample_summaries">
    <item msgid="1081159789834584363">"使用系统选择（默认）"</item>
    <item msgid="4726688794884191540">"16 位/样本"</item>
    <item msgid="305344756485516870">"24 位/样本"</item>
    <item msgid="244568657919675099">"32 位/样本"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_channel_mode_titles">
    <item msgid="5226878858503393706">"使用系统选择（默认）"</item>
    <item msgid="4106832974775067314">"单声道"</item>
    <item msgid="5571632958424639155">"立体声"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_channel_mode_summaries">
    <item msgid="4118561796005528173">"使用系统选择（默认）"</item>
    <item msgid="8900559293912978337">"单声道"</item>
    <item msgid="8883739882299884241">"立体声"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_ldac_playback_quality_titles">
    <item msgid="7158319962230727476">"偏重音频质量 (990kbps/909kbps)"</item>
    <item msgid="2921767058740704969">"兼顾音频和连接质量 (660kbps/606kbps)"</item>
    <item msgid="8860982705384396512">"偏重连接质量 (330kbps/303kbps)"</item>
    <item msgid="4414060457677684127">"尽可能提供更佳音质（自适应比特率）"</item>
  </string-array>
  <string-array name="bluetooth_a2dp_codec_ldac_playback_quality_summaries">
    <item msgid="6398189564246596868">"偏重音频质量"</item>
    <item msgid="4327143584633311908">"兼顾音频和连接质量"</item>
    <item msgid="4681409244565426925">"偏重连接质量"</item>
    <item msgid="364670732877872677">"尽可能提供更佳音质（自适应比特率）"</item>
  </string-array>
  <string-array name="bluetooth_audio_active_device_summaries">
    <item msgid="4862957058729193940"></item>
    <item msgid="6481691720774549651">"，使用中"</item>
    <item msgid="8962366465966010158">"，使用中（媒体）"</item>
    <item msgid="4046665544396189228">"，使用中（手机）"</item>
  </string-array>
  <string-array name="select_logd_size_titles">
    <item msgid="8665206199209698501">"关闭"</item>
    <item msgid="1593289376502312923">"64K"</item>
    <item msgid="487545340236145324">"256K"</item>
    <item msgid="2423528675294333831">"1M"</item>
    <item msgid="180883774509476541">"4M"</item>
    <item msgid="2803199102589126938">"16M"</item>
  </string-array>
  <string-array name="select_logd_size_lowram_titles">
    <item msgid="6089470720451068364">"关闭"</item>
    <item msgid="4622460333038586791">"64K"</item>
    <item msgid="2212125625169582330">"256K"</item>
    <item msgid="1704946766699242653">"1M"</item>
  </string-array>
  <string-array name="select_logd_size_summaries">
    <item msgid="6921048829791179331">"关闭"</item>
    <item msgid="2969458029344750262">"每个日志缓冲区 64K"</item>
    <item msgid="1342285115665698168">"每个日志缓冲区 256K"</item>
    <item msgid="1314234299552254621">"每个日志缓冲区 1M"</item>
    <item msgid="3606047780792894151">"每个日志缓冲区 4M"</item>
    <item msgid="5431354956856655120">"每个日志缓冲区 16M"</item>
  </string-array>
  <string-array name="select_logpersist_titles">
    <item msgid="1744840221860799971">"关闭"</item>
    <item msgid="3054662377365844197">"全部"</item>
    <item msgid="688870735111627832">"所有非无线电"</item>
    <item msgid="2850427388488887328">"仅限内核"</item>
  </string-array>
  <string-array name="select_logpersist_summaries">
    <item msgid="2216470072500521830">"关闭"</item>
    <item msgid="172978079776521897">"所有日志缓冲区"</item>
    <item msgid="3873873912383879240">"所有非无线电日志缓冲区"</item>
    <item msgid="8489661142527693381">"仅限内核日志缓冲区"</item>
  </string-array>
  <string-array name="window_animation_scale_entries">
    <item msgid="8134156599370824081">"关闭动画"</item>
    <item msgid="6624864048416710414">"动画缩放 0.5x"</item>
    <item msgid="2219332261255416635">"动画缩放 1x"</item>
    <item msgid="3544428804137048509">"动画缩放 1.5x"</item>
    <item msgid="3110710404225974514">"动画缩放 2x"</item>
    <item msgid="4402738611528318731">"动画缩放 5x"</item>
    <item msgid="6189539267968330656">"动画缩放 10x"</item>
  </string-array>
  <string-array name="transition_animation_scale_entries">
    <item msgid="8464255836173039442">"关闭动画"</item>
    <item msgid="3375781541913316411">"动画缩放 0.5x"</item>
    <item msgid="1991041427801869945">"动画缩放 1x"</item>
    <item msgid="4012689927622382874">"动画缩放 1.5x"</item>
    <item msgid="3289156759925947169">"动画缩放 2x"</item>
    <item msgid="7705857441213621835">"动画缩放 5x"</item>
    <item msgid="6660750935954853365">"动画缩放 10x"</item>
  </string-array>
  <string-array name="animator_duration_scale_entries">
    <item msgid="6039901060648228241">"关闭动画"</item>
    <item msgid="1138649021950863198">"动画缩放 0.5x"</item>
    <item msgid="4394388961370833040">"动画缩放 1x"</item>
    <item msgid="8125427921655194973">"动画缩放 1.5x"</item>
    <item msgid="3334024790739189573">"动画缩放 2x"</item>
    <item msgid="3170120558236848008">"动画缩放 5x"</item>
    <item msgid="1069584980746680398">"动画缩放 10x"</item>
  </string-array>
  <string-array name="overlay_display_devices_entries">
    <item msgid="1606809880904982133">"无"</item>
    <item msgid="9033194758688161545">"480p"</item>
    <item msgid="1025306206556583600">"480p（安全）"</item>
    <item msgid="1853913333042744661">"720p"</item>
    <item msgid="3414540279805870511">"720p（安全）"</item>
    <item msgid="9039818062847141551">"1080p"</item>
    <item msgid="4939496949750174834">"1080p（安全）"</item>
    <item msgid="1833612718524903568">"4K"</item>
    <item msgid="238303513127879234">"4K（安全）"</item>
    <item msgid="3547211260846843098">"4K（画质提升）"</item>
    <item msgid="5411365648951414254">"4K（画质提升、安全）"</item>
    <item msgid="1311305077526792901">"720p，1080p（双屏）"</item>
  </string-array>
  <string-array name="enable_opengl_traces_entries">
    <item msgid="3191973083884253830">"无"</item>
    <item msgid="9089630089455370183">"Logcat"</item>
    <item msgid="5397807424362304288">"Systrace（图形）"</item>
    <item msgid="1340692776955662664">"glGetError 上的调用堆栈"</item>
  </string-array>
  <string-array name="show_non_rect_clip_entries">
    <item msgid="993742912147090253">"关闭"</item>
    <item msgid="675719912558941285">"以蓝色填充非矩形剪裁区域"</item>
    <item msgid="1064373276095698656">"以绿色突出显示测试绘制命令"</item>
  </string-array>
  <string-array name="track_frame_time_entries">
    <item msgid="2193584639058893150">"关闭"</item>
    <item msgid="2751513398307949636">"在屏幕上显示为条形图"</item>
    <item msgid="2355151170975410323">"在 <xliff:g id="AS_TYPED_COMMAND">adb shell dumpsys gfxinfo</xliff:g> 中"</item>
  </string-array>
  <string-array name="debug_hw_overdraw_entries">
    <item msgid="8190572633763871652">"关闭"</item>
    <item msgid="7688197031296835369">"显示过度绘制区域"</item>
    <item msgid="2290859360633824369">"显示适合绿色弱视患者查看的区域"</item>
  </string-array>
  <string-array name="app_process_limit_entries">
    <item msgid="3401625457385943795">"标准限制"</item>
    <item msgid="4071574792028999443">"不允许后台进程"</item>
    <item msgid="4810006996171705398">"不得超过1个进程"</item>
    <item msgid="8586370216857360863">"不得超过2个进程"</item>
    <item msgid="836593137872605381">"不得超过3个进程"</item>
    <item msgid="7899496259191969307">"不得超过4个进程"</item>
  </string-array>
  <string-array name="usb_configuration_titles">
    <item msgid="488237561639712799">"充电"</item>
    <item msgid="5220695614993094977">"MTP（媒体传输协议）"</item>
    <item msgid="2086000968159047375">"PTP（图片传输协议）"</item>
    <item msgid="7398830860950841822">"RNDIS（USB 以太网）"</item>
    <item msgid="1718924214939774352">"音频来源"</item>
    <item msgid="8126315616613006284">"MIDI"</item>
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
    <string name="app_name" msgid="649227358658669779">"Launcher3"</string>
    <string name="folder_name" msgid="7371454440695724752"></string>
    <string name="work_folder_name" msgid="3753320833950115786">"Work"</string>
    <string name="activity_not_found" msgid="8071924732094499514">"未安装该应用。"</string>
    <string name="activity_not_available" msgid="7456344436509528827">"应用不可用"</string>
    <string name="safemode_shortcut_error" msgid="9160126848219158407">"安全模式下不允许使用下载的此应用"</string>
    <string name="safemode_widget_error" msgid="4863470563535682004">"安全模式下不允许使用微件"</string>
    <string name="shortcut_not_available" msgid="2536503539825726397">"无法使用快捷方式"</string>
    <string name="home_screen" msgid="806512411299847073">"主屏幕"</string>
    <string name="custom_actions" msgid="3747508247759093328">"自定义操作"</string>
    <string name="long_press_widget_to_add" msgid="7699152356777458215">"触摸并按住微件即可选择。"</string>
    <string name="long_accessible_way_to_add" msgid="4289502106628154155">"点按两次并按住微件即可选择微件，您也可以使用自定义操作。"</string>
    <string name="widget_dims_format" msgid="2370757736025621599">"%1$d × %2$d"</string>
    <string name="widget_accessible_dims_format" msgid="3640149169885301790">"宽 %1$d，高 %2$d"</string>
    <string name="add_item_request_drag_hint" msgid="5899764264480397019">"触摸并按住即可手动放置"</string>
    <string name="place_automatically" msgid="8064208734425456485">"自动添加"</string>
    <string name="all_apps_search_bar_hint" msgid="1390553134053255246">"搜索应用"</string>
    <string name="all_apps_loading_message" msgid="5813968043155271636">"正在加载应用…"</string>
    <string name="all_apps_no_search_results" msgid="3200346862396363786">"未找到与“<xliff:g id="QUERY">%1$s</xliff:g>”相符的应用"</string>
    <string name="all_apps_search_market_message" msgid="1366263386197059176">"搜索更多应用"</string>
    <string name="notifications_header" msgid="1404149926117359025">"通知"</string>
    <string name="long_press_shortcut_to_add" msgid="4524750017792716791">"触摸并按住快捷方式即可选择快捷方式。"</string>
    <string name="long_accessible_way_to_add_shortcut" msgid="3327314059613154633">"点按两次并按住快捷方式即可选择快捷方式，您也可以使用自定义操作。"</string>
    <string name="out_of_space" msgid="4691004494942118364">"此主屏幕上已没有空间。"</string>
    <string name="hotseat_out_of_space" msgid="7448809638125333693">"收藏栏已满"</string>
    <string name="all_apps_button_label" msgid="8130441508702294465">"应用列表"</string>
    <string name="all_apps_button_personal_label" msgid="1315764287305224468">"个人应用列表"</string>
    <string name="all_apps_button_work_label" msgid="7270707118948892488">"工作应用列表"</string>
    <string name="all_apps_home_button_label" msgid="252062713717058851">"主屏幕"</string>
    <string name="remove_drop_target_label" msgid="7812859488053230776">"移除"</string>
    <string name="uninstall_drop_target_label" msgid="4722034217958379417">"卸载"</string>
    <string name="app_info_drop_target_label" msgid="692894985365717661">"应用信息"</string>
    <string name="install_drop_target_label" msgid="2539096853673231757">"安装"</string>
    <string name="permlab_install_shortcut" msgid="5632423390354674437">"安装快捷方式"</string>
    <string name="permdesc_install_shortcut" msgid="923466509822011139">"允许应用自行添加快捷方式。"</string>
    <string name="permlab_read_settings" msgid="1941457408239617576">"读取主屏幕设置和快捷方式"</string>
    <string name="permdesc_read_settings" msgid="5833423719057558387">"允许应用读取主屏幕中的设置和快捷方式。"</string>
    <string name="permlab_write_settings" msgid="3574213698004620587">"写入主屏幕设置和快捷方式"</string>
    <string name="permdesc_write_settings" msgid="5440712911516509985">"允许应用更改主屏幕中的设置和快捷方式。"</string>
    <string name="msg_no_phone_permission" msgid="9208659281529857371">"不允许使用“<xliff:g id="APP_NAME">%1$s</xliff:g>”拨打电话"</string>
    <string name="gadget_error_text" msgid="6081085226050792095">"加载微件时出现问题"</string>
    <string name="gadget_setup_text" msgid="8274003207686040488">"设置"</string>
    <string name="uninstall_system_app_text" msgid="4172046090762920660">"这是系统应用，无法卸载。"</string>
    <string name="folder_hint_text" msgid="6617836969016293992">"未命名文件夹"</string>
    <string name="disabled_app_label" msgid="6673129024321402780">"已停用<xliff:g id="APP_NAME">%1$s</xliff:g>"</string>
    <plurals name="badged_app_label" formatted="false" msgid="7948068486082879291">
      <item quantity="other"><xliff:g id="APP_NAME_2">%1$s</xliff:g>，有 <xliff:g id="NOTIFICATION_COUNT_3">%2$d</xliff:g> 个通知</item>
      <item quantity="one"><xliff:g id="APP_NAME_0">%1$s</xliff:g>，有 <xliff:g id="NOTIFICATION_COUNT_1">%2$d</xliff:g> 个通知</item>
    </plurals>
    <string name="default_scroll_format" msgid="7475544710230993317">"第%1$d页，共%2$d页"</string>
    <string name="workspace_scroll_format" msgid="8458889198184077399">"主屏幕：第%1$d屏，共%2$d屏"</string>
    <string name="workspace_new_page" msgid="257366611030256142">"主屏幕新页面"</string>
    <string name="folder_opened" msgid="94695026776264709">"文件夹已打开，大小为<xliff:g id="WIDTH">%1$d</xliff:g>×<xliff:g id="HEIGHT">%2$d</xliff:g>"</string>
    <string name="folder_tap_to_close" msgid="4625795376335528256">"点按可关闭文件夹"</string>
    <string name="folder_tap_to_rename" msgid="4017685068016979677">"点按可保存新名称"</string>
    <string name="folder_closed" msgid="4100806530910930934">"文件夹已关闭"</string>
    <string name="folder_renamed" msgid="1794088362165669656">"已将文件夹重命名为“<xliff:g id="NAME">%1$s</xliff:g>”"</string>
    <string name="folder_name_format" msgid="6629239338071103179">"文件夹：<xliff:g id="NAME">%1$s</xliff:g>"</string>
    <string name="widget_button_text" msgid="2880537293434387943">"微件"</string>
    <string name="wallpaper_button_text" msgid="8404103075899945851">"壁纸"</string>
    <string name="settings_button_text" msgid="8873672322605444408">"主屏幕设置"</string>
    <string name="msg_disabled_by_admin" msgid="6898038085516271325">"已被您的管理员停用"</string>
    <string name="allow_rotation_title" msgid="7728578836261442095">"允许旋转主屏幕"</string>
    <string name="allow_rotation_desc" msgid="8662546029078692509">"手机旋转时"</string>
    <string name="icon_badging_title" msgid="874121399231955394">"通知圆点"</string>
    <string name="icon_badging_desc_on" msgid="2627952638544674079">"开启"</string>
    <string name="icon_badging_desc_off" msgid="5503319969924580241">"关闭"</string>
    <string name="title_missing_notification_access" msgid="7503287056163941064">"需要获取通知使用权"</string>
    <string name="msg_missing_notification_access" msgid="281113995110910548">"要显示通知圆点，请开启<xliff:g id="NAME">%1$s</xliff:g>的应用通知功能"</string>
    <string name="title_change_settings" msgid="1376365968844349552">"更改设置"</string>
    <string name="icon_badging_service_title" msgid="2309733118428242174">"显示通知圆点"</string>
    <string name="auto_add_shortcuts_label" msgid="8222286205987725611">"将图标添加到主屏幕"</string>
    <string name="auto_add_shortcuts_description" msgid="7117251166066978730">"适用于新应用"</string>
    <string name="icon_shape_override_label" msgid="2977264953998281004">"更改图标形状"</string>
    <string name="icon_shape_override_label_location" msgid="3841607380657692863">"在主屏幕上"</string>
    <string name="icon_shape_system_default" msgid="1709762974822753030">"使用系统默认设置"</string>
    <string name="icon_shape_square" msgid="633575066111622774">"方形"</string>
    <string name="icon_shape_squircle" msgid="5658049910802669495">"方圆形"</string>
    <string name="icon_shape_circle" msgid="6550072265930144217">"圆形"</string>
    <string name="icon_shape_teardrop" msgid="4525869388200835463">"泪珠形"</string>
    <string name="icon_shape_override_progress" msgid="3461735694970239908">"正在应用图标形状更改"</string>
    <string name="package_state_unknown" msgid="7592128424511031410">"未知"</string>
    <string name="abandoned_clean_this" msgid="7610119707847920412">"移除"</string>
    <string name="abandoned_search" msgid="891119232568284442">"搜索"</string>
    <string name="abandoned_promises_title" msgid="7096178467971716750">"未安装此应用"</string>
    <string name="abandoned_promise_explanation" msgid="3990027586878167529">"未安装此图标对应的应用。您可以移除此图标，也可以尝试搜索相应的应用并手动安装。"</string>
    <string name="app_downloading_title" msgid="8336702962104482644">"正在下载<xliff:g id="NAME">%1$s</xliff:g>，已完成 <xliff:g id="PROGRESS">%2$s</xliff:g>"</string>
    <string name="app_waiting_download_title" msgid="7053938513995617849">"<xliff:g id="NAME">%1$s</xliff:g>正在等待安装"</string>
    <string name="widgets_bottom_sheet_title" msgid="2904559530954183366">"<xliff:g id="NAME">%1$s</xliff:g>微件"</string>
    <string name="widgets_list" msgid="796804551140113767">"微件列表"</string>
    <string name="widgets_list_closed" msgid="6141506579418771922">"微件列表已关闭"</string>
    <string name="action_add_to_workspace" msgid="8902165848117513641">"添加到主屏幕"</string>
    <string name="action_move_here" msgid="2170188780612570250">"将项目移至此处"</string>
    <string name="item_added_to_workspace" msgid="4211073925752213539">"已将项目添加到主屏幕"</string>
    <string name="item_removed" msgid="851119963877842327">"项目已移除"</string>
    <string name="action_move" msgid="4339390619886385032">"移动项目"</string>
    <string name="move_to_empty_cell" msgid="2833711483015685619">"移至第 <xliff:g id="NUMBER_0">%1$s</xliff:g> 行第 <xliff:g id="NUMBER_1">%2$s</xliff:g> 列"</string>
    <string name="move_to_position" msgid="6750008980455459790">"移至第 <xliff:g id="NUMBER">%1$s</xliff:g> 个位置"</string>
    <string name="move_to_hotseat_position" msgid="6295412897075147808">"移至收藏夹第 <xliff:g id="NUMBER">%1$s</xliff:g> 个位置"</string>
    <string name="item_moved" msgid="4606538322571412879">"已移动项目"</string>
    <string name="add_to_folder" msgid="9040534766770853243">"添加到“<xliff:g id="NAME">%1$s</xliff:g>”文件夹"</string>
    <string name="add_to_folder_with_app" msgid="4534929978967147231">"添加到“<xliff:g id="NAME">%1$s</xliff:g>”所在文件夹"</string>
    <string name="added_to_folder" msgid="4793259502305558003">"项目已添加到文件夹"</string>
    <string name="create_folder_with" msgid="4050141361160214248">"创建“<xliff:g id="NAME">%1$s</xliff:g>”文件夹"</string>
    <string name="folder_created" msgid="6409794597405184510">"文件夹已创建"</string>
    <string name="action_move_to_workspace" msgid="1603837886334246317">"移至主屏幕"</string>
    <string name="action_resize" msgid="1802976324781771067">"调整大小"</string>
    <string name="action_increase_width" msgid="8773715375078513326">"增加宽度"</string>
    <string name="action_increase_height" msgid="459390020612501122">"增加高度"</string>
    <string name="action_decrease_width" msgid="1374549771083094654">"减小宽度"</string>
    <string name="action_decrease_height" msgid="282377193880900022">"减小高度"</string>
    <string name="widget_resized" msgid="9130327887929620">"微件尺寸已调整为：宽度 <xliff:g id="NUMBER_0">%1$s</xliff:g>，高度 <xliff:g id="NUMBER_1">%2$s</xliff:g>"</string>
    <string name="action_deep_shortcut" msgid="2864038805849372848">"快捷方式"</string>
    <string name="shortcuts_menu_with_notifications_description" msgid="2676582286544232849">"快捷方式和通知"</string>
    <string name="action_dismiss_notification" msgid="5909461085055959187">"关闭"</string>
    <string name="notification_dismissed" msgid="6002233469409822874">"已关闭通知"</string>
    <string name="all_apps_personal_tab" msgid="4190252696685155002">"个人"</string>
    <string name="all_apps_work_tab" msgid="4884822796154055118">"工作"</string>
    <string name="work_profile_toggle_label" msgid="3081029915775481146">"工作资料"</string>
    <string name="bottom_work_tab_user_education_title" msgid="5785851780786322825">"请在此处查找工作应用"</string>
    <string name="bottom_work_tab_user_education_body" msgid="2818107472360579152">"每个工作应用均有一个徽标，并由贵单位负责确保其安全。请将工作应用移到主屏幕，以便轻松访问。"</string>
    <string name="work_mode_on_label" msgid="4781128097185272916">"由贵单位管理"</string>
    <string name="work_mode_off_label" msgid="3194894777601421047">"通知和应用均已关闭"</string>
    <string name="bottom_work_tab_user_education_close_button" msgid="4224492243977802135">"关闭"</string>
    <string name="bottom_work_tab_user_education_closed" msgid="1098340939861869465">"已关闭"</string>
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
    <string name="permlab_bluetoothShareManager" msgid="311492132450338925">"使用下载管理器。"</string>
    <string name="permdesc_bluetoothShareManager" msgid="8930572979123190223">"允许应用访问蓝牙共享 (BluetoothShare) 管理器并将其用于传输文件。"</string>
    <string name="permlab_bluetoothWhitelist" msgid="7091552898592306386">"将蓝牙设备列入访问权限白名单。"</string>
    <string name="permdesc_bluetoothWhitelist" msgid="5494513855192170109">"允许该应用暂时将某个蓝牙设备列入白名单，从而允许该设备在不经过用户确认的情况下，将文件发送到本设备。"</string>
    <string name="bt_share_picker_label" msgid="6268100924487046932">"蓝牙"</string>
    <string name="unknown_device" msgid="9221903979877041009">"未知设备"</string>
    <string name="unknownNumber" msgid="4994750948072751566">"未知号码"</string>
    <string name="airplane_error_title" msgid="2683839635115739939">"飞行模式"</string>
    <string name="airplane_error_msg" msgid="8698965595254137230">"飞行模式中不能使用蓝牙。"</string>
    <string name="bt_enable_title" msgid="8657832550503456572"></string>
    <string name="bt_enable_line1" msgid="7203551583048149">"要使用蓝牙服务，您需要先开启蓝牙。"</string>
    <string name="bt_enable_line2" msgid="4341936569415937994">"要现在开启蓝牙吗？"</string>
    <string name="bt_enable_cancel" msgid="1988832367505151727">"取消"</string>
    <string name="bt_enable_ok" msgid="3432462749994538265">"开启"</string>
    <string name="incoming_file_confirm_title" msgid="8139874248612182627">"文件传输"</string>
    <string name="incoming_file_confirm_content" msgid="2752605552743148036">"要接受传入的文件吗？"</string>
    <string name="incoming_file_confirm_cancel" msgid="2973321832477704805">"拒绝"</string>
    <string name="incoming_file_confirm_ok" msgid="281462442932231475">"接受"</string>
    <string name="incoming_file_confirm_timeout_ok" msgid="1414676773249857278">"确定"</string>
    <string name="incoming_file_confirm_timeout_content" msgid="172779756093975981">"接受来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件时发生超时"</string>
    <string name="incoming_file_confirm_Notification_title" msgid="5573329005298936903">"有人发送文件给您"</string>
    <string name="incoming_file_confirm_Notification_content" msgid="3359694069319644738">"<xliff:g id="SENDER">%1$s</xliff:g>已准备好发送<xliff:g id="FILE">%2$s</xliff:g>"</string>
    <string name="notification_receiving" msgid="4674648179652543984">"蓝牙共享：正在接收“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_received" msgid="3324588019186687985">"蓝牙共享：已接收“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_received_fail" msgid="3619350997285714746">"蓝牙共享：未收到文件“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sending" msgid="3035748958534983833">"蓝牙共享：正在发送“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sent" msgid="9218710861333027778">"蓝牙共享：已发送“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="notification_sent_complete" msgid="302943281067557969">"已完成100%"</string>
    <string name="notification_sent_fail" msgid="6696082233774569445">"蓝牙共享：未发送文件“<xliff:g id="FILE">%1$s</xliff:g>”"</string>
    <string name="download_title" msgid="3353228219772092586">"文件传输"</string>
    <string name="download_line1" msgid="4926604799202134144">"来自：“<xliff:g id="SENDER">%1$s</xliff:g>”"</string>
    <string name="download_line2" msgid="5876973543019417712">"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="download_line3" msgid="4384821622908676061">"文件大小：<xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="download_line4" msgid="8535996869722666525"></string>
    <string name="download_line5" msgid="3069560415845295386">"正在接收文件..."</string>
    <string name="download_cancel" msgid="9177305996747500768">"停止"</string>
    <string name="download_ok" msgid="5000360731674466039">"隐藏"</string>
    <string name="incoming_line1" msgid="2127419875681087545">"来自"</string>
    <string name="incoming_line2" msgid="3348994249285315873">"文件名"</string>
    <string name="incoming_line3" msgid="7954237069667474024">"大小"</string>
    <string name="download_fail_line1" msgid="3846450148862894552">"未收到文件"</string>
    <string name="download_fail_line2" msgid="8950394574689971071">"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="download_fail_line3" msgid="3451040656154861722">"原因：<xliff:g id="REASON">%1$s</xliff:g>"</string>
    <string name="download_fail_ok" msgid="1521733664438320300">"确定"</string>
    <string name="download_succ_line5" msgid="4509944688281573595">"已接收文件"</string>
    <string name="download_succ_ok" msgid="7053688246357050216">"打开"</string>
    <string name="upload_line1" msgid="2055952074059709052">"发给：“<xliff:g id="RECIPIENT">%1$s</xliff:g>”"</string>
    <string name="upload_line3" msgid="4920689672457037437">"文件类型：<xliff:g id="TYPE">%1$s</xliff:g> (<xliff:g id="SIZE">%2$s</xliff:g>)"</string>
    <string name="upload_line5" msgid="7759322537674229752">"正在发送文件..."</string>
    <string name="upload_succ_line5" msgid="5687317197463383601">"已发送文件"</string>
    <string name="upload_succ_ok" msgid="7705428476405478828">"确定"</string>
    <string name="upload_fail_line1" msgid="7899394672421491701">"文件未发送至“<xliff:g id="RECIPIENT">%1$s</xliff:g>”。"</string>
    <string name="upload_fail_line1_2" msgid="2108129204050841798">"文件：<xliff:g id="FILE">%1$s</xliff:g>"</string>
    <string name="upload_fail_ok" msgid="5807702461606714296">"请重试"</string>
    <string name="upload_fail_cancel" msgid="9118496285835687125">"关闭"</string>
    <string name="bt_error_btn_ok" msgid="5965151173011534240">"确定"</string>
    <string name="unknown_file" msgid="6092727753965095366">"未知文件"</string>
    <string name="unknown_file_desc" msgid="480434281415453287">"没有可处理此类文件的应用。\n"</string>
    <string name="not_exist_file" msgid="3489434189599716133">"没有文件"</string>
    <string name="not_exist_file_desc" msgid="4059531573790529229">"该文件不存在。\n"</string>
    <string name="enabling_progress_title" msgid="436157952334723406">"请稍候..."</string>
    <string name="enabling_progress_content" msgid="4601542238119927904">"正在打开蓝牙..."</string>
    <string name="bt_toast_1" msgid="972182708034353383">"系统将会接收该文件。请在通知面板中检查进度。"</string>
    <string name="bt_toast_2" msgid="8602553334099066582">"无法接收该文件。"</string>
    <string name="bt_toast_3" msgid="6707884165086862518">"已停止接收来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_toast_4" msgid="4678812947604395649">"正在向“<xliff:g id="RECIPIENT">%1$s</xliff:g>”发送文件"</string>
    <string name="bt_toast_5" msgid="2846870992823019494">"正在向“<xliff:g id="RECIPIENT">%2$s</xliff:g>”发送<xliff:g id="NUMBER">%1$s</xliff:g>个文件"</string>
    <string name="bt_toast_6" msgid="1855266596936622458">"已停止向“<xliff:g id="RECIPIENT">%1$s</xliff:g>”发送文件"</string>
    <string name="bt_sm_2_1" product="nosdcard" msgid="352165168004521000">"USB存储设备空间不足，无法保存来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_sm_2_1" product="default" msgid="1989018443456803630">"SD卡存储空间不足，无法保存来自“<xliff:g id="SENDER">%1$s</xliff:g>”的文件"</string>
    <string name="bt_sm_2_2" msgid="2965243265852680543">"所需空间：<xliff:g id="SIZE">%1$s</xliff:g>"</string>
    <string name="ErrorTooManyRequests" msgid="8578277541472944529">"正在处理的请求太多。请稍后重试。"</string>
    <string name="status_pending" msgid="2503691772030877944">"尚未开始传输文件。"</string>
    <string name="status_running" msgid="6562808920311008696">"正在传输文件。"</string>
    <string name="status_success" msgid="239573225847565868">"已成功完成文件传输。"</string>
    <string name="status_not_accept" msgid="1695082417193780738">"不支持此内容。"</string>
    <string name="status_forbidden" msgid="613956401054050725">"目标设备禁止进行传输。"</string>
    <string name="status_canceled" msgid="6664490318773098285">"用户取消了传输。"</string>
    <string name="status_file_error" msgid="3671917770630165299">"存储问题。"</string>
    <string name="status_no_sd_card" product="nosdcard" msgid="1112125377088421469">"没有USB存储设备。"</string>
    <string name="status_no_sd_card" product="default" msgid="5760944071743325592">"无SD卡。请插入SD卡保存传输的文件。"</string>
    <string name="status_connection_error" msgid="947681831523219891">"连接失败。"</string>
    <string name="status_protocol_error" msgid="3245444473429269539">"无法正确处理请求。"</string>
    <string name="status_unknown_error" msgid="8156660554237824912">"未知错误。"</string>
    <string name="btopp_live_folder" msgid="7967791481444474554">"通过蓝牙接收的文件"</string>
    <string name="opp_notification_group" msgid="3486303082135789982">"蓝牙共享"</string>
    <string name="download_success" msgid="7036160438766730871">"<xliff:g id="FILE_SIZE">%1$s</xliff:g>接收完成。"</string>
    <string name="upload_success" msgid="4014469387779648949">"<xliff:g id="FILE_SIZE">%1$s</xliff:g>发送完成。"</string>
    <string name="inbound_history_title" msgid="6940914942271327563">"传入"</string>
    <string name="outbound_history_title" msgid="4279418703178140526">"传出"</string>
    <string name="no_transfers" msgid="3482965619151865672">"没有传输历史记录。"</string>
    <string name="transfer_clear_dlg_msg" msgid="1712376797268438075">"所有内容都将从列表中清除。"</string>
    <string name="outbound_noti_title" msgid="8051906709452260849">"蓝牙共享：已发送文件"</string>
    <string name="inbound_noti_title" msgid="4143352641953027595">"蓝牙共享：已接收文件"</string>
    <plurals name="noti_caption_unsuccessful" formatted="false" msgid="2020750076679526122">
      <item quantity="other"><xliff:g id="UNSUCCESSFUL_NUMBER_1">%1$d</xliff:g> 个文件传输失败。</item>
      <item quantity="one"><xliff:g id="UNSUCCESSFUL_NUMBER_0">%1$d</xliff:g> 个文件传输失败。</item>
    </plurals>
    <plurals name="noti_caption_success" formatted="false" msgid="1572472450257645181">
      <item quantity="other"><xliff:g id="SUCCESSFUL_NUMBER_1">%1$d</xliff:g> 个文件传输成功，%2$s</item>
      <item quantity="one"><xliff:g id="SUCCESSFUL_NUMBER_0">%1$d</xliff:g> 个文件传输成功，%2$s</item>
    </plurals>
    <string name="transfer_menu_clear_all" msgid="790017462957873132">"清除列表"</string>
    <string name="transfer_menu_open" msgid="3368984869083107200">"打开"</string>
    <string name="transfer_menu_clear" msgid="5854038118831427492">"从列表中清除"</string>
    <string name="transfer_clear_dlg_title" msgid="2953444575556460386">"清除"</string>
    <string name="bluetooth_map_settings_save" msgid="7635491847388074606">"保存"</string>
    <string name="bluetooth_map_settings_cancel" msgid="9205350798049865699">"取消"</string>
    <string name="bluetooth_map_settings_intro" msgid="6482369468223987562">"选择您要通过蓝牙共享的帐号。连接时，您仍必须接受所有帐号访问请求。"</string>
    <string name="bluetooth_map_settings_count" msgid="4557473074937024833">"剩余空档数："</string>
    <string name="bluetooth_map_settings_app_icon" msgid="7105805610929114707">"应用图标"</string>
    <string name="bluetooth_map_settings_title" msgid="7420332483392851321">"蓝牙消息共享设置"</string>
    <string name="bluetooth_map_settings_no_account_slots_left" msgid="1796029082612965251">"无法选择帐号，目前没有任何空档"</string>
    <string name="bluetooth_connected" msgid="6718623220072656906">"蓝牙音频已连接"</string>
    <string name="bluetooth_disconnected" msgid="3318303728981478873">"蓝牙音频已断开连接"</string>
    <string name="a2dp_sink_mbs_label" msgid="7566075853395412558">"蓝牙音频"</string>
    <string name="bluetooth_opp_file_limit_exceeded" msgid="8894450394309084519">"无法传输 4GB 以上的文件"</string>
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
    <string name="app_name" msgid="78565911793142902">"NFC服务"</string>
    <string name="nfcUserLabel" msgid="7708535817084357357">"NFC"</string>
    <string name="accessibility_nfc_enabled" msgid="7796246979948787735">"NFC已开启。"</string>
    <string name="tap_to_beam" msgid="5819197866281059878">"点按即可传输"</string>
    <string name="beam_progress" msgid="7453634884807323920">"正在接收传输内容..."</string>
    <string name="beam_outgoing" msgid="4679536649779123495">"正在传输信息..."</string>
    <string name="beam_complete" msgid="477026736424637435">"传输完毕"</string>
    <string name="beam_failed" msgid="5116241718189888630">"传输未完成"</string>
    <string name="beam_canceled" msgid="5425192751826544741">"传输已取消"</string>
    <string name="cancel" msgid="61873902552555096">"取消"</string>
    <string name="beam_tap_to_view" msgid="7430394753262448349">"点按即可查看"</string>
    <string name="beam_handover_not_supported" msgid="4083165921751489015">"接收者的设备不支持传输较大的文件。"</string>
    <string name="beam_try_again" msgid="3364677301009783455">"再次让两台设备接触"</string>
    <string name="beam_busy" msgid="5253335587620612576">"Beam 目前正忙。请在之前的传输任务完成后重试。"</string>
    <string name="device" msgid="4459621591392478151">"设备"</string>
    <string name="connecting_peripheral" msgid="1296182660525660935">"正在连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="connected_peripheral" msgid="20748648543160091">"已连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="connect_peripheral_failed" msgid="7925702596242839275">"无法连接到<xliff:g id="DEVICE_NAME">%1$s</xliff:g>"</string>
    <string name="disconnecting_peripheral" msgid="1443699384809097200">"正在断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接"</string>
    <string name="disconnected_peripheral" msgid="4470578100296504366">"已断开与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>的连接"</string>
    <string name="pairing_peripheral" msgid="6983626861540899365">"正在与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对"</string>
    <string name="pairing_peripheral_failed" msgid="6087643307743264679">"无法与<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对"</string>
    <string name="failed_to_enable_bt" msgid="7229153323594758077">"无法启用蓝牙"</string>
    <string name="confirm_pairing" msgid="4112568077038265363">"您确定要与蓝牙设备<xliff:g id="DEVICE_NAME">%1$s</xliff:g>配对吗？"</string>
    <string name="pair_yes" msgid="3525614878559994448">"是"</string>
    <string name="pair_no" msgid="5022308368904055020">"否"</string>
    <string name="tap_again_to_pay" msgid="5754988005412859897">"再次点按即可使用“<xliff:g id="APP">%1$s</xliff:g>”付款"</string>
    <string name="tap_again_to_complete" msgid="5423640945118279123">"再次点按即可使用“<xliff:g id="APP">%1$s</xliff:g>”完成付款"</string>
    <string name="transaction_failure" msgid="7828102078637936513">"无法使用“<xliff:g id="APP">%1$s</xliff:g>”完成本次交易。"</string>
    <string name="could_not_use_app" msgid="8137587876138569083">"无法使用“<xliff:g id="APP">%1$s</xliff:g>”。"</string>
    <string name="pay_with" msgid="5531545488795798945">"付款方式："</string>
    <string name="complete_with" msgid="6797459104103012992">"使用以下应用完成操作："</string>
    <string name="default_pay_app_removed" msgid="4108250545457437360">"用于触碰付款的首选服务已遭删除，您要选择使用其他服务吗？"</string>
    <string name="ask_nfc_tap" msgid="2925239870458286340">"请点按其他设备来完成操作"</string>
    <string name="wifi_connect" msgid="6250727951843550671">"连接"</string>
    <string name="status_unable_to_connect" msgid="9183908200295307657">"无法连接到网络"</string>
    <string name="status_wifi_connected" msgid="5893022897732105739">"已连接"</string>
    <string name="title_connect_to_network" msgid="2474034615817280146">"连接到网络"</string>
    <string name="prompt_connect_to_network" msgid="8511683573657516114">"连接到网络<xliff:g id="NETWORK_SSID">%1$s</xliff:g>？"</string>
    <string name="beam_requires_nfc_enabled" msgid="2800366967218600534">"需要开启NFC才能使用Android Beam。要开启吗？"</string>
    <string name="android_beam" msgid="1666446406999492763">"Android Beam"</string>
    <string name="beam_requires_external_storage_permission" msgid="8798337545702206901">"应用不具备外部存储权限。需要此权限才能传输此文件"</string>
    <string name="title_confirm_url_open" msgid="8069968913244794478">"要打开链接吗？"</string>
    <string name="summary_confirm_url_open" product="tablet" msgid="3353502750736192055">"您的平板电脑已通过 NFC 收到一个链接："</string>
    <string name="summary_confirm_url_open" product="default" msgid="1246398412196449226">"您的手机已通过 NFC 收到一个链接："</string>
    <string name="action_confirm_url_open" msgid="3458322738812921189">"打开链接"</string>
</resources>

```
