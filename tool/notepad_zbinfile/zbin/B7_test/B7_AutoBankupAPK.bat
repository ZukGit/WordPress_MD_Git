@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
@REM 将adb.exe添加到PATH中
ECHO 初始化…
@SET PATH=%PATH%;%CD%\Adb
@REM SET PATH

@REM 无限循环的标签
:LOOP

ECHO 等待您插入手机…
adb wait-for-device
adb shell pm list packages -3 -f > ./B7_autoBankupAPP.txt
@REM 循环安装本目录下的APK文件
if exist B7_autoBankupAPP.txt ( 
@rem 遍历文件多行 
  for /f %%l in (B7_autoBankupAPP.txt) do ( 
set /a num+=1 
::set s=%%l
echo "aaaa"
set mystr=%%l

echo "bbbbb1 = !mystr!"
echo "bbbbb2 = %mystr%"
set str=!mystr!
echo "ccccc = !str!"
::将"号替换成空格
set "str=!str:"= !"
:lengthnext
if not "!str!"=="" (
 set /a numsize+=1
 set "str=!str:~1!"
 goto lengthnext
)
echo !mystr!字符串长度为:%numsize%


set ch1==
::注意，这里是区分大小写的！
set str2=!mystr!
::复制字符串，用来截短，而不影响源字符串
:indexofnext
if not "!str2!"=="" (
set /a index+=1
if "!str2:~0,1!"=="%ch1%" goto last
::比较首字符是否为要求的字符，如果是则跳出循环
set "str2=!str2:~1!"
goto indexofnext
)
set /a index=0
::没有找到字符时，将num置零
:last
echo 字符'%ch1%'在字符串"!mystr!"中的首次出现位置为%index%


::  长度82 , = 的位置是56
set /a pos = %index% - 9 
set apkpath=!mystr!
set apkpath=!mystr:~8,%index%!
set packagename=!mystr!
set packagename=!mystr:~%index%,%numsize%!
echo 安装路径为'!apkpath!'包名为"!packagename!"
	   
@rem 	echo !s%! >> B7_test.txt
::       ECHO 卸载包:  !s%!
::       call adb uninstall !s%!
set /a index = 0
set /a pos = 0
set /a numsize = 0
  )
ECHO 手机三方应用已完全卸载！   
) else (
ECHO B7_uninstallpacket.txt不存在！ 
)

ECHO 卸载三方APK好了；换一台手机卸载吧！！！
PAUSE
GOTO LOOP

@ECHO on