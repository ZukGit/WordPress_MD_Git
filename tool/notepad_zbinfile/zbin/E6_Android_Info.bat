@echo off                           
Setlocal ENABLEDELAYEDEXPANSION
rem system
echo off
del %userprofile%\Desktop\zbin\E6\1_Prop.txt
del %userprofile%\Desktop\zbin\E6\2_1_CpuInfo.txt
del %userprofile%\Desktop\zbin\E6\2_2_MemInfo.txt
del %userprofile%\Desktop\zbin\E6\2_3_Battery.txt
del %userprofile%\Desktop\zbin\E6\3_1_WM_Density.txt
del %userprofile%\Desktop\zbin\E6\3_2_WM_Size.txt
del %userprofile%\Desktop\zbin\E6\4_1_Telephony_Registry.txt
del %userprofile%\Desktop\zbin\E6\5_1_Bluetooth_Manager.txt
del %userprofile%\Desktop\zbin\E6\6_1_Wifi.txt
del %userprofile%\Desktop\zbin\E6\6_2_Wifiscanner.txt
del %userprofile%\Desktop\zbin\E6\6_3_IW_Reg.txt
del %userprofile%\Desktop\zbin\E6\6_4_WifiConfigStore.txt
del %userprofile%\Desktop\zbin\E6\7_1_FeatureList.txt
del %userprofile%\Desktop\zbin\E6\8_1_DumpRes.txt
del %userprofile%\Desktop\zbin\E6\9_1_settings_system.txt
del %userprofile%\Desktop\zbin\E6\9_2_settings_secure.txt
del %userprofile%\Desktop\zbin\E6\9_3_settings_global.txt
del %userprofile%\Desktop\zbin\E6\10_1_WCNSS_qcom_cfg.txt
del %userprofile%\Desktop\zbin\E6\11_1_hostapd.txt


adb shell getprop                    > %userprofile%\Desktop\zbin/E6/1_Prop.txt

rem hardware
adb shell cat /proc/cpuinfo          > %userprofile%\Desktop\zbin/E6/2_1_CpuInfo.txt
adb shell cat /proc/meminfo          > %userprofile%\Desktop\zbin/E6/2_2_MemInfo.txt
adb shell dumpsys battery            > %userprofile%\Desktop\zbin/E6/2_3_Battery.txt  


rem screen
adb shell wm density                 > %userprofile%\Desktop\zbin/E6/3_1_WM_Density.txt
adb shell wm size                    > %userprofile%\Desktop\zbin/E6/3_2_WM_Size.txt


rem telecom
adb shell dumpsys telephony.registry > %userprofile%\Desktop\zbin/E6/4_1_Telephony_Registry.txt  



rem bluetooth
adb shell dumpsys bluetooth_manager  > %userprofile%\Desktop\zbin/E6/5_1_Bluetooth_Manager.txt  

rem wifi 
adb shell dumpsys wifi               > %userprofile%\Desktop\zbin/E6/6_1_Wifi.txt
adb shell dumpsys wifiscanner        > %userprofile%\Desktop\zbin/E6/6_2_Wifiscanner.txt  
adb shell iw phy0 reg get            > %userprofile%\Desktop\zbin/E6/6_3_IW_Reg.txt
adb pull /data/misc/wifi/WifiConfigStore.xml %userprofile%\Desktop\zbin/E6/6_4_WifiConfigStore.txt
adb shell pm list features        > %userprofile%\Desktop\zbin\E6/7_1_FeatureList.txt

rem settings
adb pull /data/system/users/0/settings_system.xml  %userprofile%\Desktop\zbin/E6/9_1_settings_system.txt
adb pull /data/system/users/0/settings_secure.xml  %userprofile%\Desktop\zbin/E6/9_2_settings_secure.txt
adb pull /data/system/users/0/settings_global.xml  %userprofile%\Desktop\zbin/E6/9_3_settings_global.txt


rem  WCNSS_qcom_cfg
adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini   %userprofile%\Desktop\zbin/E6/10_1_WCNSS_qcom_cfg.txt

rem hostapd.conf
adb pull /data/vendor/wifi/hostapd/hostapd.conf %userprofile%\Desktop\zbin/E6/11_1_hostapd.txt


@javac -encoding UTF-8 %userprofile%\Desktop\zbin\E6_Android_Info.java
@java -cp  %userprofile%\Desktop\zbin    E6_Android_Info 
@echo "Prin DumpRes take a long time , you can press Ctrl+C to Stop DumpRes!"
adb shell dumpres        > %userprofile%\Desktop\zbin\E6\8_1_DumpRes.txt

@javac -encoding UTF-8 %userprofile%\Desktop\zbin\E6_Android_Res.java
@java -cp  %userprofile%\Desktop\zbin    E6_Android_Res 
