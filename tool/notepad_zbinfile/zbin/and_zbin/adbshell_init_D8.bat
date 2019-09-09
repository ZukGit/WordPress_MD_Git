@echo off
adb root
adb disable-verify
adb remount
adb push %userprofile%\Desktop\zbin\and_zbin\and_zbin /data  
adb shell < %userprofile%\Desktop\zbin\and_zbin\param_pre.txt 
echo " please run [ source e  ||  cd /data/and_zbin/ && source e  ]  to init environment!"
adb shell 
set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)
set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%

adb pull /sdcard/and_zbin/  ./%CURRENT_DATE_TIME_STAMP%_Log
adb shell < %userprofile%\Desktop\zbin\and_zbin\param_end.txt 
@pause