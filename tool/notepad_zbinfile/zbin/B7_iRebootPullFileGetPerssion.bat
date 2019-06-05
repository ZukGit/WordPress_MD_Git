@ECHO off

@REM 将adb.exe添加到PATH中
ECHO 初始化…
@SET PATH=%PATH%;%CD%\Adb
@REM SET PATH

@REM 无限循环的标签
:LOOP

ECHO 等待您插入手机…
adb wait-for-device

@rem adb shell pm list packages -3 > ./B7_uninstallpacket.txt


adb disable-verity && adb reboot bootloader && fastboot oem ssm_test 10 1  &&  fastboot reboot


@rem adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   结束！
PAUSE
GOTO LOOP
@ECHO on