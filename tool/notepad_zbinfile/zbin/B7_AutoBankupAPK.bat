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
:forbegin
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


set ch1=apk=
::ע�⣬���������ִ�Сд�ģ�
set str2=!mystr!
::�����ַ����������ض̣�����Ӱ��Դ�ַ���
:indexofnext
set prestr=!str2:~0,4! 
if not "!str2!"=="" (
set /a index+=1
if "!prestr!"=="%ch1% " goto last
::�Ƚ����ַ��Ƿ�ΪҪ����ַ��������������ѭ��
set "str2=!str2:~1!"
goto indexofnext
)
set /a index=0
::û���ҵ��ַ�ʱ����num����
:last
echo �ַ�'%ch1%'���ַ���"!mystr!"�е��״γ���λ��Ϊ%index%


::  ����82 , = ��λ����56
set /a pos = %index% -6
set apkpath=!mystr!
set apkpath=!mystr:~8,%pos%!
set packagename=!mystr!
set /a pos = %index% + 3
set packagename=!mystr:~%pos%,%numsize%!
::echo ��װ·��Ϊ'!apkpath!'����Ϊ"!packagename!"
	   
@rem 	echo !s%! >> B7_test.txt
::       ECHO ж�ذ�:  !s%!
::       call adb uninstall !s%!
call adb pull !apkpath!  ./"!packagename!".apk

set /a index = 0
set /a pos = 0
set /a numsize = 0

more +1 B7_autoBankupAPP.txt > a.tmp
del B7_autoBankupAPP.txt
ren a.tmp B7_autoBankupAPP.txt

goto forbegin
)
ECHO �ֻ��е�����APK����ȫ��ȡ���   
) else (
ECHO B7_autoBankupAPP.txt�����ڣ� 
)

ECHO ��һ̨�ֻ�������ȡAPK�ɣ�����
PAUSE
GOTO LOOP

@ECHO on