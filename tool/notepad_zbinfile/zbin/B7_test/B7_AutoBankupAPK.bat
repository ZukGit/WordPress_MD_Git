@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
@REM ��adb.exe��ӵ�PATH��
ECHO ��ʼ����
@SET PATH=%PATH%;%CD%\Adb
@REM SET PATH

@REM ����ѭ���ı�ǩ
:LOOP

ECHO �ȴ��������ֻ���
adb wait-for-device
adb shell pm list packages -3 -f > ./B7_autoBankupAPP.txt
@REM ѭ����װ��Ŀ¼�µ�APK�ļ�
if exist B7_autoBankupAPP.txt ( 
@rem �����ļ����� 
  for /f %%l in (B7_autoBankupAPP.txt) do ( 
set /a num+=1 
::set s=%%l
echo "aaaa"
set mystr=%%l

echo "bbbbb1 = !mystr!"
echo "bbbbb2 = %mystr%"
set str=!mystr!
echo "ccccc = !str!"
::��"���滻�ɿո�
set "str=!str:"= !"
:lengthnext
if not "!str!"=="" (
 set /a numsize+=1
 set "str=!str:~1!"
 goto lengthnext
)
echo !mystr!�ַ�������Ϊ:%numsize%


set ch1==
::ע�⣬���������ִ�Сд�ģ�
set str2=!mystr!
::�����ַ����������ض̣�����Ӱ��Դ�ַ���
:indexofnext
if not "!str2!"=="" (
set /a index+=1
if "!str2:~0,1!"=="%ch1%" goto last
::�Ƚ����ַ��Ƿ�ΪҪ����ַ��������������ѭ��
set "str2=!str2:~1!"
goto indexofnext
)
set /a index=0
::û���ҵ��ַ�ʱ����num����
:last
echo �ַ�'%ch1%'���ַ���"!mystr!"�е��״γ���λ��Ϊ%index%


::  ����82 , = ��λ����56
set /a pos = %index% - 9 
set apkpath=!mystr!
set apkpath=!mystr:~8,%index%!
set packagename=!mystr!
set packagename=!mystr:~%index%,%numsize%!
echo ��װ·��Ϊ'!apkpath!'����Ϊ"!packagename!"
	   
@rem 	echo !s%! >> B7_test.txt
::       ECHO ж�ذ�:  !s%!
::       call adb uninstall !s%!
set /a index = 0
set /a pos = 0
set /a numsize = 0
  )
ECHO �ֻ�����Ӧ������ȫж�أ�   
) else (
ECHO B7_uninstallpacket.txt�����ڣ� 
)

ECHO ж������APK���ˣ���һ̨�ֻ�ж�ذɣ�����
PAUSE
GOTO LOOP

@ECHO on