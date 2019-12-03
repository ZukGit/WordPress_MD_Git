@ECHO off


ECHO INIT WITH USB
@SET PATH=%PATH%;%CD%\Adb

:LOOP

ECHO waitting device join USB
adb wait-for-device



set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)

set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%

md  file_%CURRENT_DATE_TIME_STAMP%
adb root
adb remount
adb pull /data/system/users/0/settings_system.xml ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /data/system/users/0/settings_secure.xml ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /data/system/users/0/settings_global.xml ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /vendor/etc/wifi/wpa_supplicant.conf  ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /system/etc/wifi/p2p_supplicant.conf ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /data/misc/wifi/WifiConfigStore.xml ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /data/misc/wifi/softap.conf ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /system/build.prop ./file_%CURRENT_DATE_TIME_STAMP%
adb shell getprop > ./file_%CURRENT_DATE_TIME_STAMP%/prop.txt
adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn ./file_%CURRENT_DATE_TIME_STAMP%
adb pull  /vendor/fireware_mnt/image/Data.msc  ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /vendor/rfs/msm/mpss/ramdumps ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /storage/emulated/0/Pictures/Screenshots ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /system/etc/hostapd/hostapd.deny      ./file_%CURRENT_DATE_TIME_STAMP%/system_etc_hostapd.deny
adb pull /system/etc/hostapd/hostapd.accept     ./file_%CURRENT_DATE_TIME_STAMP%/system_etc_hostapd.accept
adb pull /data/vendor/wifi/hostapd/hostapd.conf ./file_%CURRENT_DATE_TIME_STAMP%
adb pull /data/vendor/wifi/hostapd/hostapd.accept ./file_%CURRENT_DATE_TIME_STAMP%/vendor_wifi_hostapd.accept
adb pull /data/vendor/wifi/hostapd/hostapd.deny ./file_%CURRENT_DATE_TIME_STAMP%/vendor_wifi_hostapd.deny
adb pull /data/vendor/bug2go ./file_%CURRENT_DATE_TIME_STAMP%
ECHO   END! 
PAUSE
GOTO LOOP
@ECHO on