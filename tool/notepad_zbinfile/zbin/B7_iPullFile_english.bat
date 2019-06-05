@ECHO off


ECHO INIT WITH USB
@SET PATH=%PATH%;%CD%\Adb

:LOOP

ECHO waitting device join USB
adb wait-for-device



set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)

set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%

md  %CURRENT_DATE_TIME_STAMP%
adb root
adb remount
adb pull /data/system/users/0/settings_system.xml ./%CURRENT_DATE_TIME_STAMP%
adb pull /data/system/users/0/settings_secure.xml ./%CURRENT_DATE_TIME_STAMP%
adb pull /data/system/users/0/settings_global.xml
adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini ./%CURRENT_DATE_TIME_STAMP%
adb pull /vendor/etc/wifi/wpa_supplicant.conf  ./%CURRENT_DATE_TIME_STAMP%
adb pull /system/etc/wifi/p2p_supplicant.conf ./%CURRENT_DATE_TIME_STAMP%
adb pull /data/misc/wifi/WifiConfigStore.xml ./%CURRENT_DATE_TIME_STAMP%
adb pull /data/misc/wifi/softap.conf ./%CURRENT_DATE_TIME_STAMP%
adb pull /system/build.prop ./%CURRENT_DATE_TIME_STAMP%
adb shell getprop > ./%CURRENT_DATE_TIME_STAMP%/prop.txt
adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn ./%CURRENT_DATE_TIME_STAMP%
adb pull  /vendor/fireware_mnt/image/Data.msc  ./%CURRENT_DATE_TIME_STAMP%
adb pull /vendor/rfs/msm/mpss/ramdumps ./%CURRENT_DATE_TIME_STAMP%
adb pull /storage/emulated/0/Pictures/Screenshots ./%CURRENT_DATE_TIME_STAMP%

@rem adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   END! 
PAUSE
GOTO LOOP
@ECHO on