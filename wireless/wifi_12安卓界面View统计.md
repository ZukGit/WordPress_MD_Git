# systembar

## wifi

<img src="//../zimage/wireless/wifi/12_wifiview/systembar_wifi_1.png"  />

<img src="//../zimage/wireless/wifi/12_wifiview/systembar_wifi_2.png"  />



## hotspot
<img src="../zimage/wireless/wifi/12_wifiview/systembar_hotspot_1.png"  />

# dropdownfame


## wifi
<img src="//../zimage/wireless/wifi/12_wifiview/dropdownfame_wifi_1.png"  />
<img src="//../zimage/wireless/wifi/12_wifiview/dropdownfame_wifi_2.png"  />
<img src="//../zimage/wireless/wifi/12_wifiview/dropdownfame_wifi_3.png"  />

## hotspot

<img src="//../zimage/wireless/wifi/12_wifiview/dropdownfame_hotspot_1.png"  />

<img src="//../zimage/wireless/wifi/12_wifiview/dropdownfame_hotspot_2.png"  />

## Cast
```
Settings > Connected devices > Connection preferences > Cast

```

# notificationbar

## wifi

<img src="//../zimage/wireless/wifi/12_wifiview/notificationbar_wifi_1.png"  />
<img src="//../zimage/wireless/wifi/12_wifiview/notificationbar_wifi_2.png"  />

## hotspot


# settings[Network&internet]-page

<img src="//../zimage/wireless/wifi/12_wifiview/network_1.png"  />


## Passpoint(SIM)
```
Passpoint
查找并自动连接到基于SIM的Passpoint 网络

字符串显示: 
<string name="sim_switch_summary">Find and auto-connect to SIM based Passpoint&#8482; networks</string>
<string name="sim_switch_summary">查找并自动连接到基于 SIM 的 Passpoint™ 网络</string>




显示开关:  R.bool.config_wifi_hs20_sim_mnc_mcc_retail  

使能开关: NA

默认值位置: 
adb pull /data/system/users/0/settings_global.xml
<setting id="119" name="hs20_mncmcc_retail_saved_state" value="1【当前值】" package="android" defaultValue="1 【默认值】" defaultSysSet="true" />



值位置:  Settings.Global.HS20_MNCMCC_RETAIL_SAVED_STATE = "hs20_mncmcc_retail_saved_state"

adb shell settings get global hs20_mncmcc_retail_saved_state
1  // 打开

adb shell settings get global hs20_mncmcc_retail_saved_state
0  // 关闭



```

## Passpoint(no password)
```
Passpoint(no password)
Seamlessly connect to Passpoint Wi-Fi without needing a password.
无缝连接到 Passpoint™ WLAN，无需密码

字符串显示: 
<string name="passpoint_switch_summary">Seamlessly connect to Passpoint&#8482; Wi-Fi without needing a password</string>
<string name="passpoint_switch_summary">无缝连接到 Passpoint™ WLAN，无需密码</string>


显示开关:   R.bool.config_show_passpoint_switch = true  显示
(如果 )

默认值位置: 
adb pull /data/system/users/0/settings_global.xml
<setting id="126" name="hs20_saved_state" value="1【当前值】" package="android" defaultValue="1 【默认值】" defaultSysSet="true" />


值位置:  adb shell settings get global hs20_saved_state
adb shell settings get global global hs20_saved_state
1  // 打开

adb shell settings get global global hs20_saved_state
0  // 关闭


```

# settings[wifi]-page

<img src="//../zimage/wireless/wifi/12_wifiview/wifi_1.png"  />
# settings[Scaning]-page

<img src="//../zimage/wireless/wifi/12_wifiview/scaning_1.png"  />

# settings[wifi preferences]-page
<img src="//../zimage/wireless/wifi/12_wifiview/preferences_1.jpg"  />

# settings[Add network]-page
<img src="//../zimage/wireless/wifi/12_wifiview/add_1.jpg"  />




# settings[Saved networks]-page

<img src="//../zimage/wireless/wifi/12_wifiview/save_1.jpg"  />
# settings[Network details]-page

<img src="//../zimage/wireless/wifi/12_wifiview/detail_1.jpg"  />


# settings[Hotspot&tethering]-page

<img src="//../zimage/wireless/wifi/12_wifiview/hotspot_1.jpg"  />

# settings[wifi hotspot]-page
<img src="//../zimage/wireless/wifi/12_wifiview/hotspot_detail_1.jpg"  />


# settings[developer options]-page

<img src="//../zimage/wireless/wifi/12_wifiview/developer_1.jpg"  />

## Wireless display certification
```
Wireless display certification
Show options for wireless dispaly certification
```

## Enable Wifi verbose Logging
```
Enable Wifi verbose Logging
Increase Wifi logging level , show per SSID RSSI in WIFI picker


```

##  Connected MAC Randomization
```
Connected MAC Randomzation
Randomzize MAC Address when connecting to Wi-Fi networks

显示开关:  R.bool.config_wifi_support_connected_mac_randomization     true-显示   false-不显示

默认值位置: 
adb pull /data/system/users/0/settings_global.xml


值位置:  adb shell settings get global wifi_connected_mac_randomization_enabled
  <setting id="1010" name="wifi_connected_mac_randomization_enabled" value="1【当前值】" package="com.android.settings" defaultValue="1【默认值】" defaultSysSet="true" />
 


adb shell settings get global wifi_connected_mac_randomization_enabled
0   // 关闭

adb shell settings get global wifi_connected_mac_randomization_enabled
1   // 开启

```


##  Mobile data always active
```
Mobile data always active
Always keep mobile data active , even when Wi-Fi is active( for fast network switching )

```


## Tethering hadware acceleration
```
Tethering hadware acceleration
Use tethering hardware  acceleration if available

```
