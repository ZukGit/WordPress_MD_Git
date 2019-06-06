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

ECHO  正在开始录制屏幕,拔掉USB停止录制!
adb shell screenrecord --bit-rate 4000000 /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
ECHO   已拔掉USB，停止录制！

ECHO 录制文件名称: /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4

ECHO  请重新插入USB以获取文件 /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4  ./mp4_%CURRENT_DATE_TIME_STAMP%/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4 

ECHO   文件获取成功,你可以回车以进行下一次的录制屏幕！
PAUSE


GOTO LOOP
@ECHO on