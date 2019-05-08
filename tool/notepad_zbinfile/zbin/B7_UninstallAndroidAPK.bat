@rem echo off &setlocal enabledelayedexpansion 
@rem title adb uninstall [by Join] 
@ECHO off
@rem 定义adb路径 
@rem set adb_path=C:\Program Files\Wandoujia2 
set adb_path=C:\Program Files (x86)\ADB\ADB_1_0_39_windows
 
@rem adb.exe存在判断及处理 
if exist %adb_path%\adb.exe (goto :handle) else (echo abd.exe路径不正确，请修正！&goto :end) 
 
@rem  获取手机中的第三方包 package:xxx.xxxx.xxx 
:handle 
call  adb shell pm list packages -3 > ./B7_uninstallpacket.txt
@rem path增加adb路径 
set path=%adb_path%;%path% 
 
echo 注意事项 
echo 1、请将需要卸载的apk包名，写入同目录的B7_uninstallpacket.txt！ 
echo 2、不知道apk包名？可以用如下几种简单方式查看。 
echo   1）直接解压apk，得到AndroidManifest.xml，用记事本打开就行！ 
echo      能看到类似*package *manifest *org.join.xxx的内容-- 
echo   2）使用apktool，反编译得到apk资源文件，查看AndroidManifest.xml！ 
echo      最上部manifest标签内package="org.join.xxx"属性内内容 
 
@rem package.txt存在判断及处理 
if exist B7_uninstallpacket.txt ( 
setlocal enabledelayedexpansion
@rem 遍历文件多行 
  for /f %%l in (B7_uninstallpacket.txt) do ( 
       set /a num+=1 
	   set s=%%l
       set s=!s:~8!
@rem 	echo !s%! >> B7_test.txt
       echo 卸载包:  !s%!
       call adb uninstall !s%!
  )
echo.&echo 手机三方应用已完全卸载！   
) else ( 
  echo.&echo B7_uninstallpacket.txt不存在！ 
) 
 
:end 
echo. 
pause 