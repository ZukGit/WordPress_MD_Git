@echo off
Setlocal ENABLEDELAYEDEXPANSION
::启用命令扩展，参加setlocal /?命令
set str1=package:/data/app/com.baidu.quickmind-1/base.apk=com.baidu.quickmind
set ch1==
::注意，这里是区分大小写的！
set str=%str1%
::复制字符串，用来截短，而不影响源字符串
:indexofnext
if not "%str%"=="" (
set /a num+=1
if "!str:~0,1!"=="%ch1%" goto last
::比较首字符是否为要求的字符，如果是则跳出循环
set "str=%str:~1%"
goto indexofnext
)
set /a num=0
::没有找到字符时，将num置零
:last
echo 字符'%ch1%'在字符串"%str1%"中的首次出现位置为%num%
       set s=!s:~8!
       set s=%str1:% 
echo 输出完毕，按任意键退出&&pause>nul&&exit
pause


