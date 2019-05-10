@echo off
Setlocal ENABLEDELAYEDEXPANSION
::set mystr=package:/data/app/com.farproc.wifi.analyzer-YFcZDtlV0Qq7fG4UKXY4Uw==/base.apk=com.farproc.wifi.analyzer
set mystr=package:/data/app/com.farproc.wifi.analyzer/base.apk=com.farproc.wifi.analyzer
set str=%mystr%
::将"号替换成空格
set "str=%str:"= %"
:lengthnext
if not "%str%"=="" (
 set /a numsize+=1
 set "str=%str:~1%"
 goto lengthnext
)
echo %mystr%字符串长度为:%numsize%



set ch1=apk=
::注意，这里是区分大小写的！
set str2=%mystr%
::复制字符串，用来截短，而不影响源字符串
:indexofnext
if not "%str2%"=="" (
set /a index+=1
if "!str2:~0,1!"=="%ch1%" goto last
::比较首字符是否为要求的字符，如果是则跳出循环
set "str2=%str2:~1%"
goto indexofnext
)
set /a index=0
::没有找到字符时，将num置零
:last
echo 字符'%ch1%'在字符串"%mystr%"中的首次出现位置为%index%


::  长度82 , = 的位置是56
set /a pos = %index% - 9 
set apkpath=%mystr%
set apkpath=!mystr:~8,%pos%!
set packagename=%mystr%
set packagename=!mystr:~%index%,%numsize%!
echo 安装路径为'%apkpath%'包名为"%packagename%"
pause
