@echo off
Setlocal ENABLEDELAYEDEXPANSION
::set mystr=package:/data/app/com.farproc.wifi.analyzer-YFcZDtlV0Qq7fG4UKXY4Uw==/base.apk=com.farproc.wifi.analyzer
set mystr=package:/data/app/com.farproc.wifi.analyzer/base.apk=com.farproc.wifi.analyzer
set str=%mystr%
::��"���滻�ɿո�
set "str=%str:"= %"
:lengthnext
if not "%str%"=="" (
 set /a numsize+=1
 set "str=%str:~1%"
 goto lengthnext
)
echo %mystr%�ַ�������Ϊ:%numsize%



set ch1=apk=
::ע�⣬���������ִ�Сд�ģ�
set str2=%mystr%
::�����ַ����������ض̣�����Ӱ��Դ�ַ���
:indexofnext
if not "%str2%"=="" (
set /a index+=1
if "!str2:~0,1!"=="%ch1%" goto last
::�Ƚ����ַ��Ƿ�ΪҪ����ַ��������������ѭ��
set "str2=%str2:~1%"
goto indexofnext
)
set /a index=0
::û���ҵ��ַ�ʱ����num����
:last
echo �ַ�'%ch1%'���ַ���"%mystr%"�е��״γ���λ��Ϊ%index%


::  ����82 , = ��λ����56
set /a pos = %index% - 9 
set apkpath=%mystr%
set apkpath=!mystr:~8,%pos%!
set packagename=%mystr%
set packagename=!mystr:~%index%,%numsize%!
echo ��װ·��Ϊ'%apkpath%'����Ϊ"%packagename%"
pause
