@echo off
Setlocal ENABLEDELAYEDEXPANSION
set mystr=package:/data/app/com.ccdi.news-2/base.apk=com.ccdi.news
set str=%mystr%
::将"号替换成空格
set "str=%str:"= %"
:lengthnext
if not "%str%"=="" (
 set /a num+=1
 set "str=%str:~1%"
 goto lengthnext
)
echo %mystr%字符串长度为:%num%



set ch1==
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
set packagename=!mystr:~%index%,%num%!
echo 安装路径为'%apkpath%'包名为"%packagename%"
pause
