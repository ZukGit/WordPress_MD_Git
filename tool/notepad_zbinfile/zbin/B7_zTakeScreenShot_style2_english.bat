@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
ECHO initialize-ing
:LOOP

adb wait-for-device
adb root
adb remount


set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)

set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%


ECHO  take screenshot style2 nowing Please remove device with USB to stop screenshot when you finish takescreen!
adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
ECHO   take screenshot stop ! 

ECHO PNG_Name: /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   Please ReJoin In USB to GET /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   Please Press Enter to take next video Screenshot !
PAUSE


GOTO LOOP
@ECHO on