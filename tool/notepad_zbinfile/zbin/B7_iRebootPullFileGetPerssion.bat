@ECHO off

@REM ��adb.exe��ӵ�PATH��
ECHO ��ʼ����
@SET PATH=%PATH%;%CD%\Adb
@REM SET PATH

@REM ����ѭ���ı�ǩ
:LOOP

ECHO �ȴ��������ֻ���
adb wait-for-device

@rem adb shell pm list packages -3 > ./B7_uninstallpacket.txt


adb disable-verity && adb reboot bootloader && fastboot oem ssm_test 10 1  &&  fastboot reboot


@rem adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   ������
PAUSE
GOTO LOOP
@ECHO on