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

ECHO  ���ڿ�ʼ¼����Ļ,�ε�USBֹͣ¼��!
adb shell screenrecord --bit-rate 4000000 /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
ECHO   �Ѱε�USB��ֹͣ¼�ƣ�

ECHO ¼���ļ�����: /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4

ECHO  �����²���USB�Ի�ȡ�ļ� /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4  ./mp4_%CURRENT_DATE_TIME_STAMP%/B7_zScreenRecord_%CURRENT_DATE_TIME_STAMP%.mp4 

ECHO   �ļ���ȡ�ɹ�,����Իس��Խ�����һ�ε�¼����Ļ��
PAUSE


GOTO LOOP
@ECHO on