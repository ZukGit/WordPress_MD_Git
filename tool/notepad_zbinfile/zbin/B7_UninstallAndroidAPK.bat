@rem echo off &setlocal enabledelayedexpansion 
@rem title adb uninstall [by Join] 
@ECHO off
@rem ����adb·�� 
@rem set adb_path=C:\Program Files\Wandoujia2 
set adb_path=C:\Program Files (x86)\ADB\ADB_1_0_39_windows
 
@rem adb.exe�����жϼ����� 
if exist %adb_path%\adb.exe (goto :handle) else (echo abd.exe·������ȷ����������&goto :end) 
 
@rem  ��ȡ�ֻ��еĵ������� package:xxx.xxxx.xxx 
:handle 
call  adb shell pm list packages -3 > ./B7_uninstallpacket.txt
@rem path����adb·�� 
set path=%adb_path%;%path% 
 
echo ע������ 
echo 1���뽫��Ҫж�ص�apk������д��ͬĿ¼��B7_uninstallpacket.txt�� 
echo 2����֪��apk���������������¼��ּ򵥷�ʽ�鿴�� 
echo   1��ֱ�ӽ�ѹapk���õ�AndroidManifest.xml���ü��±��򿪾��У� 
echo      �ܿ�������*package *manifest *org.join.xxx������-- 
echo   2��ʹ��apktool��������õ�apk��Դ�ļ����鿴AndroidManifest.xml�� 
echo      ���ϲ�manifest��ǩ��package="org.join.xxx"���������� 
 
@rem package.txt�����жϼ����� 
if exist B7_uninstallpacket.txt ( 
setlocal enabledelayedexpansion
@rem �����ļ����� 
  for /f %%l in (B7_uninstallpacket.txt) do ( 
       set /a num+=1 
	   set s=%%l
       set s=!s:~8!
@rem 	echo !s%! >> B7_test.txt
       echo ж�ذ�:  !s%!
       call adb uninstall !s%!
  )
echo.&echo �ֻ�����Ӧ������ȫж�أ�   
) else ( 
  echo.&echo B7_uninstallpacket.txt�����ڣ� 
) 
 
:end 
echo. 
pause 