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

md  mp4_%CURRENT_DATE_TIME_STAMP%

ECHO  take screen video nowing Please remove device with USB  to stop video shot when you finish takescreen!
adb shell screenrecord --bit-rate 4000000 /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
ECHO   take screen video stop ! 

ECHO MP4_Name: /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4

ECHO   Please ReJoin In USB to GET /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4  ./mp4_%CURRENT_DATE_TIME_STAMP%/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4 

ECHO   Please Press Enter to take next video Screenshot !
PAUSE


GOTO LOOP
@ECHO on