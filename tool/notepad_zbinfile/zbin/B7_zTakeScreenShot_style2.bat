@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
ECHO 初始化中ing
:LOOP

adb wait-for-device
adb root
adb remount


set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)

set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%


ECHO 正在进行截图! 您可以拔掉USB 以结束这次截图
adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
ECHO   拔掉USB && 截图完成！ 重新插入USB 以获取该张图片

adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   以完成这次截图 图片以保存到本地,可以 回车 以继续下一次截图操作！
PAUSE


GOTO LOOP
@ECHO on