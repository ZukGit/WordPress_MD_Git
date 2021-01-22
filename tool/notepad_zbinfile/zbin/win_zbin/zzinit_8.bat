@ECHO off
setlocal enabledelayedexpansion 
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
set cur_path=%cd%
echo cd /d "%~dp0"
cd /d "%~dp0"
set cur_Desktop=%USERPROFILE%
echo cur_Desktop=%cur_Desktop%  cur_path=%cd% ~dp0=%~dp0
set exe_count=1
set Environment_java_exe=""
set Environment_javac=""

set Environment_python_exe=""
set python_exe_file=""

set java_exe_file=""
set javac_run_file=""
set Environment_win_zbin="%cur_Desktop%\Desktop\zbin\win_zbin"
set Environment_notepad=""
set notepad_exe_file=""

rem call :search_java_envirppnment
rem echo Environment_java_exe_end = %Environment_java_exe%
rem echo Environment_javac_end = %Environment_javac%
rem echo Environment_win_zbin = %Environment_win_zbin%
rem echo java_exe_file = %java_exe_file%
rem echo javac_run_file = %javac_run_file%
rem 
rem echo %javac_run_file%  -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8   %cur_Desktop%\Desktop\zbin\J1_InstallSoftware.java
rem call %javac_run_file%  -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8  %cur_Desktop%\Desktop\zbin\J1_InstallSoftware.java
rem echo;
rem echo %java_exe_file% -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar;%cur_Desktop%\Desktop\zbin  J1_InstallSoftware  %~dp0
rem call %java_exe_file% -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar;%cur_Desktop%\Desktop\zbin  J1_InstallSoftware  %~dp0
rem pause









call :express_all_zip
call :copy_zbin
call :search_java_envirppnment
call :search_python_envirppnment
call :search_notepad_envirppnment
call :add_all_environment




echo Environment_java_exe_dir = %Environment_java_exe%
echo Environment_javac_dir= %Environment_javac%
echo Environment_win_zbin = %Environment_win_zbin%
echo Environment_python_exe_dir = %Environment_python_exe%
echo python_exe_file = %python_exe_file%

echo java_exe_file = %java_exe_file%
echo javac_run_file = %javac_run_file%
echo Environment_notepad = %Environment_notepad%
echo notepad_exe_file = %notepad_exe_file%

echo %javac_run_file%  -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8   %cur_Desktop%\Desktop\zbin\J1_InstallSoftware.java
call %javac_run_file%  -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8  %cur_Desktop%\Desktop\zbin\J1_InstallSoftware.java
echo;
echo %java_exe_file% -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar;%cur_Desktop%\Desktop\zbin  J1_InstallSoftware  %~dp0
call %java_exe_file% -cp %cur_Desktop%\Desktop\zbin\J1_guava.jar;%cur_Desktop%\Desktop\zbin\J1_jshortcut_oberzalek.jar;%cur_Desktop%\Desktop\zbin  J1_InstallSoftware  %~dp0







rem    *********** python  network operation begin  ***********
rem  python  get-pip.py 
call %python_exe_file% %Environment_python_exe%\get-pip.py      

rem    *********** python network operation end  ***********



call J1_InstallSoft.bat
call :showCmderCommand 
call reg.reg
pause
goto:eof
rem  end**************end**************end**************end**************end**************end**************



:express_all_zip
rem  express all zip file
for /f "delims=" %%i in ('dir /b /a-d /s "*.zip"') do (
rem set cur_file_path=%%i
rem echo %%i
rem echo cur_file_path=%cur_file_path%
call :show_7z_command %%i
rem call :showfile %%i
)
goto:eof



:add_all_environment
rem echo  setx "Path" "%path%;%Environment_win_zbin%;%Environment_java_exe%;%Environment_javac%;%Environment_notepad%;%Environment_python_exe%;%Environment_python_exe%/Scripts" /m
rem setx "Path" "%path%;%Environment_win_zbin%;%Environment_java_exe%;%Environment_javac%;%Environment_notepad%;%Environment_python_exe%;%Environment_python_exe%/Scripts" /m

echo  setx "Path" "%Environment_win_zbin%;%Environment_java_exe%;%Environment_javac%;%Environment_notepad%;%Environment_python_exe%;%Environment_python_exe%/Scripts;%path%" /m
setx "Path" "%Environment_win_zbin%;%Environment_java_exe%;%Environment_javac%;%Environment_notepad%;%Environment_python_exe%;%Environment_python_exe%/Scripts;%path%" /m
goto:eof


:showCmderCommand
for /f "delims=" %%j in ('dir /b /a-d /s "*Cmder.exe"') do (
echo num=!exe_count! %%j 

echo  %%j /REGISTER ALL  
%%j /REGISTER ALL  
start %%j
echo;
echo;
echo ========= please add command as environment settings in Cmder.exe =========
echo;
echo set PATH=%%ConEmuBaseDir%%\Scripts;%%PATH%%
echo set PATH=%%USERPROFILE%%\Desktop\zbin\win_zbin;%%PATH%%
echo alias cdd=cd /D %%USERPROFILE%%\Desktop
echo alias cdz=cd /D %%USERPROFILE%%\Desktop\zbin
echo;
rem echo set PATH=%ConEmuBaseDir%\Scripts;%PATH%
rem echo set PATH=E:\Temp_Install\jdk\bin;%PATH%
rem echo set PATH=%USERPROFILE%\Desktop\zbin\win_zbin;%PATH%
)
goto:eof





:copy_zbin
echo xcopy /y /c /h /r /s ".\zbin\*.*" "%cur_Desktop%\Desktop\zbin\"
xcopy /y /c /h /r /s ".\zbin\*.*" "%cur_Desktop%\Desktop\zbin\"
goto:eof



:search_notepad_envirppnment
for /f "delims=" %%j in ('dir /b /a-d /s "*notepad++.exe"') do (
echo num=!exe_count! %%j 
set /a exe_count  = !exe_count! + 1
set notepad_exe_file=%%j 
call :add_path_environment_file_notepad %%j 
)

goto:eof



:search_python_envirppnment
for /f "delims=" %%j in ('dir /b /a-d /s "*python.exe"') do (
echo num=!exe_count! %%j 
set python_exe_file=%%j
call :add_path_environment_file_python %%j 
)
goto:eof



:add_path_environment_file_python
set str2_dp=%~dp1 
echo add_environment_python_path=%str2_dp%    for  %1
rem setx "Path" "%str2_dp%;%path%" /m

call :add_path_environment_dir_python %str2_dp%
goto:eof


:add_path_environment_dir_python
rem call :showfile %1  
set str2_dp_dir=%~dp1

rem set str_temp=123456789
rem echo last_str = %str_temp:~0,-1%

echo str2_dp_dir="%str2_dp_dir%"
set fixed_path=%str2_dp_dir:~0,-1%
echo fixed_path="%fixed_path%"
echo setx_command---------setx "Path" "%fixed_path%;%path%" /m
rem setx "Path" "%fixed_path%;%path%" /m
echo Environment_python_exe = %fixed_path%
set Environment_python_exe=%fixed_path%
goto:eof



:search_java_envirppnment
for /f "delims=" %%j in ('dir /b /a-d /s "*java.exe"') do (
echo num=!exe_count! %%j 
set /a exe_count  = !exe_count! + 1
set java_exe_file=%%j 
call :add_path_environment_file_java %%j 
)

set /a exe_count  = 1
for /f "delims=" %%j in ('dir /b /a-d /s "*javac.exe"') do (
echo num=!exe_count! %%j 
set /a exe_count  = !exe_count! + 1
set javac_run_file=%%j 
call :add_path_environment_file_javaC %%j 
)
goto:eof


:add_path_environment_file_notepad
set str2_dp=%~dp1 
echo notepad_environment_path=%str2_dp%    for  %1
rem setx "Path" "%str2_dp%;%path%" /m
call :add_path_environment_dir_notepad %str2_dp%
goto:eof


:add_path_environment_dir_notepad
rem call :showfile %1  
set str2_dp_dir=%~dp1

rem set str_temp=123456789
rem echo last_str = %str_temp:~0,-1%

echo str2_dp_dir="%str2_dp_dir%"
set fixed_path=%str2_dp_dir:~0,-1%
echo fixed_path="%fixed_path%"
echo setx_command---------setx "Path" "%fixed_path%;%path%" /m
rem setx "Path" "%fixed_path%;%path%" /m
echo Environment_notepad = %fixed_path%
set Environment_notepad=%fixed_path%
goto:eof





:add_path_environment_file_java
set str2_dp=%~dp1 
echo add_environment_path=%str2_dp%    for  %1
rem setx "Path" "%str2_dp%;%path%" /m
call :add_path_environment_dir_java %str2_dp%
goto:eof



:add_path_environment_dir_java
rem call :showfile %1  
set str2_dp_dir=%~dp1

rem set str_temp=123456789
rem echo last_str = %str_temp:~0,-1%

echo str2_dp_dir="%str2_dp_dir%"
set fixed_path=%str2_dp_dir:~0,-1%
echo fixed_path="%fixed_path%"
echo setx_command---------setx "Path" "%fixed_path%;%path%" /m
rem setx "Path" "%fixed_path%;%path%" /m
echo Environment_java_exe = %fixed_path%
set Environment_java_exe=%fixed_path%
goto:eof




:add_path_environment_file_javaC
set str2_dp=%~dp1 
echo add_environment_path=%str2_dp%    for  %1
rem setx "Path" "%str2_dp%;%path%" /m
call :add_path_environment_dir_javaC %str2_dp%
goto:eof


:add_path_environment_dir_javaC
rem call :showfile %1  
set str2_dp_dir=%~dp1

rem set str_temp=123456789
rem echo last_str = %str_temp:~0,-1%

echo str2_dp_dir="%str2_dp_dir%"
set fixed_path=%str2_dp_dir:~0,-1%
echo fixed_path="%fixed_path%"
echo setx_command---------setx "Path" "%fixed_path%;%path%" /m
rem setx "Path" "%fixed_path%;%path%" /m

echo Environment_javac = %fixed_path%
set Environment_javac=%fixed_path%
goto:eof






:show_7z_command
set str2_dp=%~dp1   
rem echo str2_dp=%str2_dp%
rem set cur_item_dir_path=%str2_dp%
rem echo cur_item_dir_path=%cur_item_dir_path%
echo  7z.exe -y -p""  x "%1" -o%str2_dp%
7z.exe -y -p""  x "%1" -o%str2_dp%
goto:eof

rem  fileAbsPath=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\sourceinsight4.0.zip
rem  ~a=--a--------
rem  ~d=D:
rem  ~f=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\sourceinsight4.0.zip
rem  ~n=sourceinsight4.0
rem  ~s=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\sourceinsight4.0.zip
rem  ~p=\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\
rem  ~x=.zip
rem  ~z=22173677
rem  ~dp=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\
rem  ~nx=sourceinsight4.0.zip
rem  ~fs=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\sourceinsight4.0.zip
rem  ~dpn=D:\zbin_model\Software\ZWin_Software\E1_No_Slient_Zip_Dir_Path\sourceinsight4.0




:showfile
set str1_a=%~a1
set str1_d=%~d1
set str1_f=%~f1
set str1_n=%~n1
set str1_s=%~s1
set str1_t=%~t1
set str1_p=%~p1
set str1_x=%~x1
set str1_z=%~z1
set str2_dp=%~dp1
set str2_nx=%~nx1
set str2_fs=%~fs1
set str3_dpn=%~dpn1
echo;
echo fileAbsPath=%1
echo ~a=%str1_a%
echo ~d=%str1_d%
echo ~f=%str1_f%
echo ~n=%str1_n%
echo ~s=%str1_s%
echo ~p=%str1_p%
echo ~x=%str1_x%
echo ~z=%str1_z%
echo ~dp=%str2_dp%
echo ~nx=%str2_nx%
echo ~fs=%str2_fs%
echo ~dpn=%str3_dpn%
goto:eof