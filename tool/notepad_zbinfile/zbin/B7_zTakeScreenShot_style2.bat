@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
ECHO ��ʼ����ing
:LOOP

adb wait-for-device
adb root
adb remount


set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)

set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%


ECHO ���ڽ��н�ͼ! �����԰ε�USB �Խ�����ν�ͼ
adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
ECHO   �ε�USB && ��ͼ��ɣ� ���²���USB �Ի�ȡ����ͼƬ

adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png

ECHO   �������ν�ͼ ͼƬ�Ա��浽����,���� �س� �Լ�����һ�ν�ͼ������
PAUSE


GOTO LOOP
@ECHO on