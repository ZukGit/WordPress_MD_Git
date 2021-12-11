@ECHO off
setlocal enabledelayedexpansion
chcp 65001
rem ══════════════════════════════════════════ System_Init_Aera Begin  ══════════════════════════════════════════

rem 函数定义之前的提示必须以英文结尾 否则 可能 报出一些 找不到之类的 错误 ----  
rem ________________ 系统路径初始化   
set init_cd=%cd%
set init_dp0=%~dp0
set init_f0=%~f0
set init_path=%path%
set init_input_0=%0
echo init_cd=%init_cd%               rem %cd% === 当前执行命令的当前路径  C:\Users\zhuxx  
echo init_f0=%init_f0%               rem %~f0 === 当前执行文件的全路径       C:\Users\xxx\Desktop\zbin\win_zbin\init_input_0.bat   
echo init_input_0=%init_input_0%     rem %0 ===当前执行文件的名称 init_input_0=[zbatrule_I9_Rule30.bat]     init_input_0=[zbatrule_I9_Rule30.bat]
echo init_dp0=%init_dp0%             rem %~dp0 ===  当前执行文件的文件夹名称  init_dp0 = C:\Users\zhuxx\Desktop\zbin\win_zbin\  
echo init_path=%init_path%           rem %path% === 当前的系统坏境变量PATH
rem init_path=D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\bin;C:\Users\xxx\Desktop\zbin\win_soft\Redis;C:\Program Files\Tesseract-OCR;D:\software\ffmpeg\bin;C:\Users\xxx\Desktop\zbin\lin_zbin;C:\Swift\bin\;C:\Users\xxx\Desktop\zbin\mac_zbin;C:\Users\xxx\Desktop\zbin\win_zbin;C:\Users\xxx\Desktop\zbin\python;
rem D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5\ConEmu\Scripts;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5\ConEmu;C:\Windows\System32;C:\Users\xxx\Desktop\zbin\win_zbin;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\jre\bin;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\npp.7.8.9.bin.x64;
rem D:\ZWin_Software\D0_Environment_Zip_Dir_Path\python-3.7.9-embed-amd64;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\python-3.7.9-embed-amd64/Scripts;C:\Program Files (x86)\Qualcomm\QUTS\bin;C:\Program Files (x86)\Qualcomm\QXDM5;C:\Program Files (x86)\Qualcomm\PCAT\bin;
rem C:\Program Files (x86)\Qualcomm\EUD;C:\Program Files (x86)\Qualcomm\QIKTool\1.0.109.1;C:\Windows;C:\Windows\System32;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\ADB;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\npp.7.8.9.bin.x64;C:\Users\xxx\Desktop\zbin\win_zbin;C:\Program Files (x86)\Graphviz2.38\bin;
rem D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\bin;C:\Program Files\Tesseract-OCR;C:\Users\xxx\AppData\Local\Google\Chrome\Application;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\jre\bin;C:\Users\xxx\AppData\Local\Android\Sdk\platform-tools;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\cmd;C:\Program Files\Tesseract-OCR;C:\Users\xxx\Desktop\zbin\lin_zbin;C:\Users\xxx\Desktop\zbin\mac_zbin;
rem C:\Users\xxx\Desktop\zbin\win_zbin;C:\Users\xxx\AppData\Local\Programs\Microsoft VS Code\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\usr\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\usr\share\vim\vim74;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\
echo=
echo=
echo=
echo=

 
rem ________________ 相对路径初始化   
set init_userprofile=%userprofile%
set init_desktop=%userprofile%\Desktop
set desktop=%userprofile%\Desktop
set init_zbin=%userprofile%\Desktop\zbin
set zbin=%userprofile%\Desktop\zbin
set win_zbin=%userprofile%\Desktop\zbin\win_zbin
set init_win_zbin=%userprofile%\Desktop\zbin\win_zbin
echo init_userprofile=%init_userprofile%     rem %userprofile% 标示为 用户主目录 init_userprofile=C:\Users\xxx  
echo desktop=%desktop%                       rem init_desktop 和 desktop 标示 桌面路径 C:\Users\xxx\Desktop
echo init_desktop=%init_desktop%             rem init_desktop 和 desktop 标示 桌面路径 C:\Users\xxx\Desktop
echo zbin=%zbin%                             rem zbin 和 init_zbin 标示 桌面zbin路径 C:\Users\xxx\Desktop\zbin
echo init_zbin=%init_zbin%                   rem zbin 和 init_zbin 标示 桌面zbin路径 C:\Users\xxx\Desktop\zbin
echo win_zbin=%win_zbin%                     rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\Users\xxx\Desktop\zbin\win_zbin
echo init_win_zbin=%init_win_zbin%           rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\Users\xxx\Desktop\zbin\win_zbin
echo=
echo=
echo=
echo=

rem ________________ 输入参数打印  init_input_0=[zbatrule_I9_Rule30.bat]     init_input_0=[zbatrule_I9_Rule30.bat]
set init_input_0=%0
set init_input_1=%1
set init_input_2=%2
set init_input_3=%3
set init_input_4=%4
set init_input_5=%5
set init_input_6=%6
set init_input_7=%7
set init_input_8=%8
set init_input_9=%9
echo init_cd=%init_cd%              
echo init_f0=%init_f0% 
echo init_input_0=[%0]     init_input_0=[%init_input_0%]
echo init_input_1=[%1]     init_input_1=[%init_input_1%] 
echo init_input_2=[%2]     init_input_2=[%init_input_2%]
echo init_input_3=[%3]     init_input_3=[%init_input_3%]
echo init_input_4=[%4]     init_input_4=[%init_input_4%]
echo init_input_5=[%5]     init_input_5=[%init_input_5%]
echo init_input_6=[%6]     init_input_6=[%init_input_6%]
echo init_input_7=[%7]     init_input_7=[%init_input_7%]
echo init_input_8=[%8]     init_input_8=[%init_input_8%]
echo init_input_9=[%9]     init_input_9=[%init_input_9%]
echo=
echo=
echo=
echo=
rem ══════════════════════════════════════════ System_Init_Aera End  ══════════════════════════════════════════  
rem ══════════════════════════════════════════ Program_Execute_Aera Begin  ════════════════════════════ 
call :ruletipanalysis_func_0x1
echo=
echo=
echo=
echo=
call :____Main____

goto:eof
rem ══════════════════════════════════════════ Program_Execute_Aera End  ══════════════════════════════════════════ 


rem ══════════════════════════════════════════FUNC_DEFINE_AERA Begin══════════════════════════════════════════


rem ======================== FILE_OPERATION Begin======================== 


:recordfilenametofile_func_1x1
rem ======================================== recordfilenametofile_func_1x1
rem desc: 把当前指定文件夹路径 假如一个文件 并返回这个文件的全路径
rem sample: call :recordfilenametofile_func_1x1 %zbin%
rem sample_out: [recordfilenametofile_func_1x1 ]   recordfilenametofile_return_1=[C:\Users\xxx\Desktop\zbin\Z_temp.txt]   param1=[C:\Users\xxx\Desktop\zbin]
rem sample_out: 文件 包含 A8_GPS_Log_Search$GnssLocationProvider_SCREEN_OFF.class

rem sample_out: 文件 包含A8_GPS_Log_Search$GnssLocationProvider_SCREEN_ON.class

rem sample_out: 文件 包含A8_WIFI_Log_Search.class
::SETLOCAL
echo ______________Method_In recordfilenametofile_func_1x1
echo recordFileNameToFile_func_1x1  input_1_param == %1
dir *.* /o:-d /B %1 > %1\Z_TEMP.txt
set recordfilenametofile_return_1=%1\Z_temp.txt
echo recordfilenametofile_return_1=[%recordfilenametofile_return_1%]  param1=[%1]  
echo [recordfilenametofile_func_1x1 EndPrintCode]   recordfilenametofile_return_1=[!recordfilenametofile_return_1!]   param1=[%1]   
echo ______________Method_Out recordfilenametofile_func_1x1
::ENDLOCAL
goto:eof




:getfiledpn_func_1x1
rem ======================================== getfiledpn_func_1x1
rem desc: 给一个文件 fileAbsPath=C:\Users\xxx\Desktop\zbin\A0.bat  取到dpn去除文件类型的路径  ~dpn=C:\Users\xxx\Desktop\zbin\A0
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getfiledpn_func_1x1
set getfiledpn_return_1=%~dpn1
echo [getfiledpn_func_1x1 EndPrintCode]   getfiledpn_return_1=[!getfiledpn_return_1!]   param1=[%1]   
echo ______________Method_Out getfiledpn_func_1x1
::ENDLOCAL
goto:eof




:getfilex_func_1x1
rem ======================================== getfilex_func_1x1
rem desc: 给一个文件 fileAbsPath=C:\Users\xxx\Desktop\zbin\A0.bat  取到x当前的文件名称  ~x=.bat
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getfilex_func_1x1
set getfilex_return_1=%~x1
echo [getfilex_func_1x1 EndPrintCode]   getfilex_return_1=[!getfilex_return_1!]   param1=[%1]   
echo ______________Method_Out getfilex_func_1x1
::ENDLOCAL
goto:eof




:getfilenx_func_1x1
rem ======================================== getfilenx_func_1x1
rem desc: 给一个文件 fileAbsPath=C:\Users\xxx\Desktop\zbin\A0.bat  取到nx当前的文件名称  ~nx=A0.bat
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getfilenx_func_1x1
set getfilenx_return_1=%~nx1
echo [getfilenx_func_1x1 EndPrintCode]   getfilenx_return_1=[!getfilenx_return_1!]   param1=[%1]   
echo ______________Method_Out getfilenx_func_1x1
::ENDLOCAL
goto:eof




:getfiledp_func_1x1
rem ======================================== getfiledp_func_1x1
rem desc: 给一个文件 fileAbsPath=C:\Users\xxx\Desktop\zbin\A0.bat  取到dp父目录路径  ~dp=C:\Users\xxx\Desktop\zbin\
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getfiledp_func_1x1
set getfiledp_return_1=%~dp1
echo [getfiledp_func_1x1 EndPrintCode]   getfiledp_return_1=[!getfiledp_return_1!]   param1=[%1]   
echo ______________Method_Out getfiledp_func_1x1
::ENDLOCAL
goto:eof




:copyfiledirovertodir_func_2x0
rem ======================================== copyfiledirovertodir_func_2x0
rem desc: 复制 [src文件或者目录] 到 目标目录 [dst目录] 如果dst目录存在那么强制覆盖 执行复制操作
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In copyfiledirovertodir_func_2x0
set copy_src_file=%1
set copy_dest_dirfile=%2
call ::isfileexist_func_1x1  %copy_dest_dirfile%
set copy_dest_dirfile_existflag=!isfileexist_return_1!
echo copy_dest_dirfile_existflag=%copy_dest_dirfile_existflag%
if "%copy_dest_dirfile_existflag%"=="true" (
echo 当前src文件 To dst目标文件夹  src=[%1]   dst=[%2]  由于dst目标文件夹已经存在 强制覆盖!!
) else (
echo 当前src文件 To dst目标文件夹  src=[%1]   dst=[%2]  由于目标文件夹不存在 那么创建这样的文件夹
rem mkdir %2
)
echo copy_src_file=[%copy_src_file%]  copy_dest_dirfile=[%copy_dest_dirfile%]
echo 执行 xcopy 命令 复制文件(夹)[%copy_src_file%]   到目标文件夹[%copy_dest_dirfile%]   命令如下:
call ::isrealfile_func_1x1 %copy_src_file%
set copy_srcfile_isrealfile=!isrealfile_return_1!
if "!copy_srcfile_isrealfile!"=="true" (
echo xcopy  "%copy_src_file%" "%copy_dest_dirfile%\"      [复制文件]
xcopy /y   "%copy_src_file%" "%copy_dest_dirfile%\"
) else (
echo xcopy /y /c  /e /h /r /s "%copy_src_file%\*.*" "%copy_dest_dirfile%\"      [复制文件夹]
xcopy /y /c  /e /h /r /s "%copy_src_file%\*.*" "%copy_dest_dirfile%\"
)
echo [copyfiledirovertodir_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out copyfiledirovertodir_func_2x0
::ENDLOCAL
goto:eof




:copyfiledirtodir_func_2x0
rem ======================================== copyfiledirtodir_func_2x0
rem desc: 复制 [src文件或者目录] 到 目标目录 [dst目录] 如果dst目录存在那么不执行复制操作
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In copyfiledirtodir_func_2x0
set copy_src_file=%1
set copy_dest_dirfile=%2
call ::isfileexist_func_1x1  %copy_dest_dirfile%
set copy_dest_dirfile_existflag=!isfileexist_return_1!
echo copy_dest_dirfile_existflag=%copy_dest_dirfile_existflag%
if "%copy_dest_dirfile_existflag%"=="true" (
echo 当前src文件 To dst目标文件夹  src=[%1]   dst=[%2]  由于dst目标文件夹已经存在 为了保险 跳过复制过程!!
echo [copyfiledirtodir_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out copyfiledirtodir_func_2x0
GOTO:EOF
) else (
echo 当前src文件 To dst目标文件夹  src=[%1]   dst=[%2]  由于目标文件夹不存在 那么创建这样的文件夹
rem mkdir %2
)
echo copy_src_file=[%copy_src_file%]  copy_dest_dirfile=[%copy_dest_dirfile%]
echo 执行 xcopy 命令 复制文件(夹)[%copy_src_file%]   到目标文件夹[%copy_dest_dirfile%]   命令如下:
call ::isrealfile_func_1x1 %copy_src_file%
set copy_srcfile_isrealfile=!isrealfile_return_1!
if "%copy_dest_dirfile_existflag%"=="true" (
echo xcopy  "%copy_src_file%" "%copy_dest_dirfile%\"      [复制文件]
xcopy  "%copy_src_file%" "%copy_dest_dirfile%\"
) else (
echo xcopy /y /c  /e /h /r /s "%copy_src_file%\*.*" "%copy_dest_dirfile%\"      [复制文件夹]
xcopy /y /c  /e /h /r /s "%copy_src_file%\*.*" "%copy_dest_dirfile%\"
)
echo [copyfiledirtodir_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out copyfiledirtodir_func_2x0
::ENDLOCAL
goto:eof




:expressfiletodir_func_1x1
rem ======================================== expressfiletodir_func_1x1
rem desc: 把当前的zip rar 7z 文件解压成当前文件名对应的文件夹并返回全路径  11.zip 转为 11   ABC.7z 转为 ABC 如果已经存在文件夹那么不解压
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In expressfiletodir_func_1x1
set zip_full_path=%1
echo zip_full_path=%zip_full_path%
call :getfiledpn_func_1x1 %zip_full_path%
set zip_notype_dir_path=!getfiledpn_return_1!
echo zip_notype_dir_path=!zip_notype_dir_path!
call :isfileexist_func_1x1  !zip_notype_dir_path!
set zip_notype_dir_path_existflag=!isfileexist_return_1!
if "%zip_notype_dir_path_existflag%"=="true" (
echo 当前压缩文件 %1 已经存在对应的解压缩文件夹  !zip_notype_dir_path!   跳过解压缩流程！
set expressfiletodir_return_1=!zip_notype_dir_path!
echo [expressfiletodir_func_1x1 EndPrintCode]   expressfiletodir_return_1=[!expressfiletodir_return_1!]   param1=[%1]   
echo ______________Method_Out expressfiletodir_func_1x1
GOTO:EOF
)
rem zip_notype_dir_path=D:\Local_Soft_Test\zsoft\zbin
rem zip_express_dir=D:\Local_Soft_Test\zsoft    7z解压缩的路径
call :getfiledp_func_1x1 !zip_notype_dir_path!   
set zip_express_dir=!getfiledp_return_1!
echo zip_express_dir=!zip_express_dir!
echo 当前压缩文件 %1 将解压到文件夹  !zip_express_dir!  中！
echo  7z.exe -y -p""  x "%1" -o!zip_express_dir!
7z.exe -y -p""  x "%1" -o!zip_express_dir!
set expressfiletodir_return_1=!zip_notype_dir_path!
echo [expressfiletodir_func_1x1 EndPrintCode]   expressfiletodir_return_1=[!expressfiletodir_return_1!]   param1=[%1]   
echo ______________Method_Out expressfiletodir_func_1x1
::ENDLOCAL
goto:eof




:expressfiletoinputdir_func_2x0
rem ======================================== expressfiletoinputdir_func_2x0
rem desc: 把当前的zip tar .gz .7z 解压到指定的文件夹 并不返回数值
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In expressfiletoinputdir_func_2x0
set zip_full_path=%1
echo zip_full_path=%zip_full_path%
rem  [getfilenamenopointwithfullpath_func_1x1 ]   getfilenamenopointwithfullpath_return_1=[A0]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
call ::getfilenamenopointwithfullpath_func_1x1  %1
set zip_notype_name=!getfilenamenopointwithfullpath_return_1!
echo zip_notype_name=!zip_notype_name!
echo zip_source_dir=%2\zip_notype_name
call :isfileexist_func_1x1  %2\!zip_notype_name!
set zip_targetdir_path_existflag=!isfileexist_return_1!
if "%zip_targetdir_path_existflag%"=="true" (
echo 当前压缩文件 %1 已经存在对应的解压缩文件夹  %2\!zip_notype_name!   跳过解压缩流程！  如果要测试请删除对应的文件夹
echo [expressfiletoinputdir_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out expressfiletoinputdir_func_2x0
GOTO:EOF
)
set zip_express_dir=%2
echo zip_express_dir=!zip_express_dir!
echo 当前压缩文件 %1 将解压到文件夹  !zip_express_dir!  中！
echo  7z.exe -y -p""  x "%1" -o!zip_express_dir!
7z.exe -y -p""  x "%1" -o!zip_express_dir!
set expressfiletodir_return_1=!zip_notype_dir_path!
echo [expressfiletoinputdir_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out expressfiletoinputdir_func_2x0
::ENDLOCAL
goto:eof




:searchlastfile_func_1x1
rem ======================================== searchlastfile_func_1x1
rem desc: 搜索一个文件夹下最新的那个文件夹以及文件
rem sample: call :searchlastfile_func_1x1 %zbin%
rem sample_out: [searchlastfullpath_func_1x1 ]   searchlastfullpath_return_1=[C:\Users\xxx\Desktop\zbin\TestX]   param1=[C:\Users\xxx\Desktop\zbin]
::SETLOCAL
echo ______________Method_In searchlastfile_func_1x1
echo searchLastFile_func_1x1 input_1_param == %1
for /f "delims=" %%j in ('dir %1 /o-d /a /b') do (
echo searchLastFile_return_1=%%j 
set  searchLastFile_return_1=%%j
echo searchLastFile_return_1=[!searchLastFile_return_1!]  param1=[%1]  
echo [searchlastfile_func_1x1 EndPrintCode]   searchlastfile_return_1=[!searchlastfile_return_1!]   param1=[%1]   
echo ______________Method_Out searchlastfile_func_1x1
goto:eof
)
echo [searchlastfile_func_1x1 EndPrintCode]   searchlastfile_return_1=[!searchlastfile_return_1!]   param1=[%1]   
echo ______________Method_Out searchlastfile_func_1x1
::ENDLOCAL
goto:eof




:searchlastfullpath_func_1x1
rem ======================================== searchlastfullpath_func_1x1
rem desc: 搜索一个文件夹下最新的那个文件夹以及文件   短路径
rem sample: call :searchlastfullpath_func_1x1 %zbin%
rem sample_out: [searchlastfile_func_1x1 ]   searchlastfile_return_1=[TestX]   param1=[C:\Users\xxx\Desktop\zbin]
::SETLOCAL
echo ______________Method_In searchlastfullpath_func_1x1
echo searchLastFullPath_func_1x1 input_1_param == %1
for /f "delims=" %%j in ('dir %1 /o-d /a /b /s') do (
echo searchLastFullPath_return_1=%%j 
set  searchLastFullPath_return_1=%%j
echo searchLastFullPath_return_1=[!searchLastFullPath_return_1!]  param1=[%1]  
echo [searchlastfullpath_func_1x1 EndPrintCode]   searchlastfullpath_return_1=[!searchlastfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out searchlastfullpath_func_1x1
goto:eof
)
echo [searchlastfullpath_func_1x1 EndPrintCode]   searchlastfullpath_return_1=[!searchlastfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out searchlastfullpath_func_1x1
::ENDLOCAL
goto:eof




:getfilenamewithfullpath_func_1x1
rem ======================================== getfilenamewithfullpath_func_1x1
rem desc: 获取文件名称  包含 类型   给定一个文件的全路径
rem sample: call ::getfilenamewithfullpath_func_1x1  %zbin%\A0.bat
rem sample_out: [getfilenamewithfullpath_func_1x1 ]   getfilenamewithfullpath_return_1=[A0.bat]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In getfilenamewithfullpath_func_1x1
echo getFileNameWithFullPath_func_1x1 input_1_param == %1
set getFileNameWithFullPath_return_1=%~nx1
echo getFileNameWithFullPath_return_1=[%getFileNameWithFullPath_return_1%]  param1=[%1]  
echo [getfilenamewithfullpath_func_1x1 EndPrintCode]   getfilenamewithfullpath_return_1=[!getfilenamewithfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out getfilenamewithfullpath_func_1x1
::ENDLOCAL
goto:eof




:getfiletypewithfullpath_func_1x1
rem ======================================== getfiletypewithfullpath_func_1x1
rem desc: 获取当前文件的类型   给一个全路径的文件路径 包含 点号
rem sample: call ::getfiletypewithfullpath_func_1x1  %zbin%\A0.bat
rem sample_out: [getfiletypewithfullpath_func_1x1 ]   getfiletypewithfullpath_return_1=[.bat]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In getfiletypewithfullpath_func_1x1
echo getFileTypeWithFullPath_func_1x1 input_1_param == %1
set getFileTypeWithFullPath_return_1=%~x1
echo getFileTypeWithFullPath_return_1=[%getFileTypeWithFullPath_return_1%]  param1=[%1]  
echo [getfiletypewithfullpath_func_1x1 EndPrintCode]   getfiletypewithfullpath_return_1=[!getfiletypewithfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out getfiletypewithfullpath_func_1x1
::ENDLOCAL
goto:eof




:getfilenamenopointwithfullpath_func_1x1
rem ======================================== getfilenamenopointwithfullpath_func_1x1
rem desc: 获取当前文件的文件名称  但不包括 点号  类型
rem sample: call ::getfilenamenopointwithfullpath_func_1x1  %zbin%\A0.bat
rem sample_out: [getfilenamenopointwithfullpath_func_1x1 ]   getfilenamenopointwithfullpath_return_1=[A0]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In getfilenamenopointwithfullpath_func_1x1
echo getFileNameNoPointWithFullPath_func_1x1 input_1_param == %1
set getFileNameNoPointWithFullPath_return_1=%~n1
echo getFileNameNoPointWithFullPath_return_1=[%getFileNameNoPointWithFullPath_return_1%]  param1=[%1]  
echo [getfilenamenopointwithfullpath_func_1x1 EndPrintCode]   getfilenamenopointwithfullpath_return_1=[!getfilenamenopointwithfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out getfilenamenopointwithfullpath_func_1x1
::ENDLOCAL
goto:eof




:getparentfilepathwithfullpath_func_1x1
rem ======================================== getparentfilepathwithfullpath_func_1x1
rem desc: 获取当前全路径的父路径的全路径
rem sample: call :getparentfilepathwithfullpath_func_1x1 %zbin%
rem sample_out: [getparentfilepathwithfullpath_func_1x1 ]   getparentfilepathwithfullpath_return_1=[\Users\xxx\Desktop\]   param1=[C:\Users\xxx\Desktop\zbin]
::SETLOCAL
echo ______________Method_In getparentfilepathwithfullpath_func_1x1
echo getParentFilePathWithFullPath_func_1x1 input_1_param == %1
set getParentFilePathWithFullPath_return_1=%~p1
echo getParentFilePathWithFullPath_return_1=[%getParentFilePathWithFullPath_return_1%]  param1=[%1]  
echo [getparentfilepathwithfullpath_func_1x1 EndPrintCode]   getparentfilepathwithfullpath_return_1=[!getparentfilepathwithfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out getparentfilepathwithfullpath_func_1x1
::ENDLOCAL
goto:eof




:getfilesizebytewithfullpath_func_1x1
rem ======================================== getfilesizebytewithfullpath_func_1x1
rem desc: 获取指定文件的占用大小  字节 bytes
rem sample: call :getfilesizebytewithfullpath_func_1x1 %zbin%\A0.bat
rem sample_out: [getfilesizebytewithfullpath_func_1x1 ]   getfilesizebytewithfullpath_return_1=[114]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In getfilesizebytewithfullpath_func_1x1
echo getFileSizeByteWithFullPath_func_1x1 input_1_param == %1
set getFileSizeByteWithFullPath_return_1=%~z1
echo getFileSizeByteWithFullPath_return_1=[%getFileSizeByteWithFullPath_return_1%]  param1=[%1]  
echo [getfilesizebytewithfullpath_func_1x1 EndPrintCode]   getfilesizebytewithfullpath_return_1=[!getfilesizebytewithfullpath_return_1!]   param1=[%1]   
echo ______________Method_Out getfilesizebytewithfullpath_func_1x1
::ENDLOCAL
goto:eof




:showfile_func_1x0
rem ======================================== showfile_func_1x0
rem desc: 展示文件属性的方法
rem sample: call :showfile_func_1x0 %zbin%\A0.bat
rem sample_out: fileAbsPath=C:\Users\xxx\Desktop\zbin\A0.bat
rem sample_out: ~a=--a--------
rem sample_out: ~d=C:
rem sample_out: ~f=C:\Users\xxx\Desktop\zbin\A0.bat
rem sample_out: ~n=A0
rem sample_out: ~s=C:\Users\xxx\Desktop\zbin\A0.bat
rem sample_out: ~p=\Users\xxx\Desktop\zbin\
rem sample_out: ~x=.bat
rem sample_out: ~z=114
rem sample_out: ~dp=C:\Users\xxx\Desktop\zbin\
rem sample_out: ~nx=A0.bat
rem sample_out: ~fs=C:\Users\xxx\Desktop\zbin\A0.bat
rem sample_out: ~dpn=C:\Users\xxx\Desktop\zbin\A0
rem sample_out: [showfile_func_1x0 ]    output=[__empty__] param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In showfile_func_1x0
echo showfile_func input_1_param == %1
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
echo showfile_func_1x0   param1=[%1]  
echo [showfile_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out showfile_func_1x0
::ENDLOCAL
goto:eof




:showsubfile4dir4type_func_2x0
rem ======================================== showsubfile4dir4type_func_2x0
rem desc: 显示满足条件的 参数一 全路径下 的 满足 条件二的 实体文件的 列表
rem sample: call :showsubfile4dir4type_func_2x0 %zbin%  *.java
rem sample_out: Index_showSubFile4Dir4Type[1] == C:\Users\xxx\Desktop\zbin\K2_NotepadTip.java
rem sample_out: Index_showSubFile4Dir4Type[113] == C:\Users\xxx\Desktop\zbin\A2.java
::SETLOCAL
echo ______________Method_In showsubfile4dir4type_func_2x0
echo showSubFile4Dir4Type_func_2x0 input_1_param == %1
echo showSubFile4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showSubFile4Dir4Type = 0
for /f "delims=\" %%a in ('dir /b /a-d /o-d "%1\%2"') do (
set /a Index_showSubFile4Dir4Type+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo Index_showSubFile4Dir4Type[!Index_showSubFile4Dir4Type!] == %1\%%a 
)
echo showSubFile4Dir4Type_func_2x0 Index_showSubFile4Dir4Type=[!Index_showSubFile4Dir4Type!]  param1=[%1]   param2=[%2] 
echo [showsubfile4dir4type_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out showsubfile4dir4type_func_2x0
::ENDLOCAL
goto:eof




:showsubdir4dir4type_func_2x0
rem ======================================== showsubdir4dir4type_func_2x0
rem desc: 显示当前给定参数一文件夹的全路径下的文件夹    显示的是文件夹全路径
rem sample: call :showsubdir4dir4type_func_2x0 %zbin% *
rem sample_out: showSubDir4Dir4Type[1] == C:\Users\xxx\Desktop\zbin\and_zbin
rem sample_out: showSubDir4Dir4Type[22] == C:\Users\xxx\Desktop\zbin\win_zbin
rem sample_out: [showsubdir4dir4type_func_2x0 ]    output=[__empty__] param1=[C:\Users\xxx\Desktop\zbin]   param2=[*]
::SETLOCAL
echo ______________Method_In showsubdir4dir4type_func_2x0
echo showSubDir4Dir4Type_func_2x0 input_1_param == %1
echo showSubDir4Dir4Type_func_2x0 input_2_param == %2
set /a showSubDir4Dir4Type = 0
for /d %%a in (%1\%2) do (
set /a showSubDir4Dir4Type+=1
echo showSubDir4Dir4Type[!showSubDir4Dir4Type!] == %%a 
)
echo [showsubdir4dir4type_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out showsubdir4dir4type_func_2x0
::ENDLOCAL
goto:eof




:showsubdir_func_1x0
rem ======================================== showsubdir_func_1x0
rem desc: 显示当前给定参数一文件夹的全路径下的子文件夹 不包扩 孙文件夹
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In showsubdir_func_1x0
set /a Index_Song_Dir = 0
for /d %%a in (%1\*) do (
set /a Index_Song_Dir+=1
rem %%a 就是目录的全路径
echo  showsubdir_index=[!Index_Song_Dir!]   Song_Dir_Full_Path=[%%a] 
)
echo [showsubdir_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out showsubdir_func_1x0
::ENDLOCAL
goto:eof




:showsubfileanddir_func_1x0
rem ======================================== showsubfileanddir_func_1x0
rem desc: 显示当前给定参数一文件夹的全路径下的文件夹 和 文件    显示的是文件名称 不是全路径
rem sample: call :showsubfileanddir_func_1x0 %zbin%
rem sample_out: showSubFileAndDir_index_file=[1]  showSubFileAndDir_all_index=[1] file=[K2_NotepadTip.class]
rem sample_out: showSubFileAndDir_index_file=[1233]  showSubFileAndDir_all_index=[1233] file=[I1_SNMP4J.jar]
rem sample_out: showSubFileAndDir_index_dir[1]  showSubFileAndDir_all_index=[1234] dir=[C:\Users\xxx\Desktop\zbin\and_zbin]
::SETLOCAL
echo ______________Method_In showsubfileanddir_func_1x0
echo showSubFileAndDir_func_1x0 input_1_param == %1
set /a Index_Dir_showFileAndDir = 0
set /a Index_All_showFileAndDir = 0
set /a Index_RealFile_showFileAndDir = 0
for /f "delims=\" %%a in ('dir /b /a-d /o-d "%1\*"') do (
set /a Index_RealFile_showFileAndDir+=1
set /a Index_All_showFileAndDir+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo  showSubFileAndDir_index_file=[!Index_RealFile_showFileAndDir!]  showSubFileAndDir_all_index=[!Index_All_showFileAndDir!] file=[%%a] 
)
for /d %%a in (%1\*) do (
set /a Index_Dir_showFileAndDir+=1
set /a Index_All_showFileAndDir+=1
echo showSubFileAndDir_index_dir[!Index_Dir_showFileAndDir!]  showSubFileAndDir_all_index=[!Index_All_showFileAndDir!] dir=[%%a] 
)
echo showSubFileAndDir_func_1x0 Index_RealFile_showFileAndDir=[!Index_RealFile_showFileAndDir!]  Index_All_showFileAndDir=[!Index_All_showFileAndDir!] param1=[%1] 
echo [showsubfileanddir_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out showsubfileanddir_func_1x0
::ENDLOCAL
goto:eof




:showallsubfile4dir4type_func_2x0
rem ======================================== showallsubfile4dir4type_func_2x0
rem desc: 显示指定全路径文件夹下的所有 满足 参数二的文件
rem sample: call :showallsubfile4dir4type_func_2x0  %zbin%  Tip
rem sample_out: Index_showAllSubFile4Dir4Type[1] == C:\Users\xxx\Desktop\zbin\H0_Tip$1.class
rem sample_out: Index_showAllSubFile4Dir4Type[2] == C:\Users\xxx\Desktop\zbin\H0_Tip$OS_TYPE.class
rem sample_out: Index_showAllSubFile4Dir4Type[49] == C:\Users\xxx\Desktop\zbin\win_zbin\zvim_tip_I5.bat
::SETLOCAL
echo ______________Method_In showallsubfile4dir4type_func_2x0
echo showAllSubFile4Dir4Type_func_2x0 input_1_param == %1
echo showAllSubFile4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showAllSubFile4Dir4Type = 0
for /r %1 %%j in  (*%2*)do (
set /a Index_showAllSubFile4Dir4Type+=1
echo Index_showAllSubFile4Dir4Type[!Index_showAllSubFile4Dir4Type!] == %%j 
)
echo showAllSubFile4Dir4Type_func_2x0 Index_showAllSubFile4Dir4Type=[!Index_showAllSubFile4Dir4Type!]  param1=[%1]  param2=[%2] 
echo [showallsubfile4dir4type_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out showallsubfile4dir4type_func_2x0
::ENDLOCAL
goto:eof




:searchonetargetfile4dir4type_func_2x1
rem ======================================== searchonetargetfile4dir4type_func_2x1
rem desc: 给定一个文件夹的全路径 以及 要过滤的文件的名称  返回 最符合要求的那一个文件
rem sample: call :searchonetargetfile4dir4type_func_2x1  %zbin%  Tip
rem sample_out: [searchonetargetfile4dir4type_func_2x1 ]   searchonetargetfile4dir4type_return_1=[C:\Users\xxx\Desktop\zbin\H0_Tip$1.class]   param1=[C:\Users\xxx\Desktop\zbin]   param2=[Tip]
::SETLOCAL
echo ______________Method_In searchonetargetfile4dir4type_func_2x1
set /a Index_searchOneTargetFile4Dir4Type = 0
for /r %1 %%j in  (*%2*)do (
set /a Index_searchOneTargetFile4Dir4Type+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo Index_searchOneTargetFile4Dir4Type[!Index_searchOneTargetFile4Dir4Type!] == %%j
set searchonetargetfile4dir4type_return_1=%%j
echo searchonetargetfile4dir4type_return_1=[%searchonetargetfile4dir4type_return_1%]  param1=[%1]  param2=[%2] 
echo [searchonetargetfile4dir4type_func_2x1 EndPrintCode]   searchonetargetfile4dir4type_return_1=[!searchonetargetfile4dir4type_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out searchonetargetfile4dir4type_func_2x1
goto:eof
)
echo [searchonetargetfile4dir4type_func_2x1 EndPrintCode]   searchonetargetfile4dir4type_return_1=[!searchonetargetfile4dir4type_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out searchonetargetfile4dir4type_func_2x1
::ENDLOCAL
goto:eof




:showallsubdir4dir4type_func_2x0
rem ======================================== showallsubdir4dir4type_func_2x0
rem desc: 给一个文件夹的全路径参数 以及需要过滤的参数名称  显示经过过滤满足条件的文件夹
rem sample: call :showallsubdir4dir4type_func_2x0  %zbin% z
rem sample_out: Index_showAllSubDir4Dir4Type[1]  == C:\Users\xxx\Desktop\zbin\and_zbin
rem sample_out: Index_showAllSubDir4Dir4Type[31] == C:\Users\xxx\Desktop\zbin\J1_Plugin\Tampermonkey\_locales\zh_TW
::SETLOCAL
echo ______________Method_In showallsubdir4dir4type_func_2x0
echo showAllSubDir4Dir4Type_func_2x0 input_1_param == %1
echo showAllSubDir4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showAllSubDir4Dir4Type = 0
for /r  %1 /d %%a in (*%2*) do (
set /a Index_showAllSubDir4Dir4Type+=1
echo Index_showAllSubDir4Dir4Type[!Index_showAllSubDir4Dir4Type!] == %%a 
)
echo showAllSubDir4Dir4Type_func_2x0 noReturn flitter_num=[!Index_showAllSubDir4Dir4Type!]  param1=[%1] param2=[%2]
echo [showallsubdir4dir4type_func_2x0 EndPrintCode]    output=[__empty__] param1=[%1]   param2=[%2]   
echo ______________Method_Out showallsubdir4dir4type_func_2x0
::ENDLOCAL
goto:eof




:showallsubfileanddir_func_1x0
rem ======================================== showallsubfileanddir_func_1x0
rem desc: 给一个目录的全路径 显示该目录下的所有文件以及文件夹
rem sample: call :showallsubfileanddir_func_1x0 %zbin%
rem sample_out: showSubFileAndDir_index_file[1]  showAllSubFileAndDir_all_index=[1] == C:\Users\xxx\Desktop\zbin\A0.bat
rem sample_out: showSubFileAndDir_index_dir[489]  showAllSubFileAndDir_all_index=[3358] == C:\Users\xxx\Desktop\zbin\J7_WallPager\2880x1880
::SETLOCAL
echo ______________Method_In showallsubfileanddir_func_1x0
echo showAllSubFileAndDir_func_1x0 input_1_param == %1
set /a Index_Dir_showAllFileAndDir = 0
set /a Index_All_showAllFileAndDir = 0
set /a Index_RealFile_showAllFileAndDir = 0
for /r %1 %%j in  (*)do (
set /a Index_RealFile_showAllFileAndDir+=1
set /a Index_All_showAllFileAndDir+=1
echo showSubFileAndDir_index_file[!Index_RealFile_showAllFileAndDir!]  showAllSubFileAndDir_all_index=[!Index_All_showAllFileAndDir!] == %%j
)
for /r %1 /d %%a in (*) do (
set /a Index_Dir_showAllFileAndDir+=1
set /a Index_All_showAllFileAndDir+=1
echo showSubFileAndDir_index_dir[!Index_Dir_showAllFileAndDir!]  showAllSubFileAndDir_all_index=[!Index_All_showAllFileAndDir!] == %%a 
)
echo [showallsubfileanddir_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out showallsubfileanddir_func_1x0
::ENDLOCAL
goto:eof




:isfileexist_func_1x1
rem ======================================== isfileexist_func_1x1
rem desc: 检测文件是否存在   存在返回true  不存在 返回false
rem sample: call :isfileexist_func_1x1  %zbin%/A0.bat
rem sample_out: [isfileexist_func_1x1 ]   isfileexist_return_1=[true]   param1=[C:\Users\xxx\Desktop\zbin/A0.bat]
::SETLOCAL
echo ______________Method_In isfileexist_func_1x1
echo showAllSubFileAndDir_func_1x0 input_1_param == %1
if exist %1 (
set isFileExist_return_1=true
) else (
set isFileExist_return_1=false
)
echo isFileExist_return_1=[%isFileExist_return_1%]  param1=[%1] 
echo [isfileexist_func_1x1 EndPrintCode]   isfileexist_return_1=[!isfileexist_return_1!]   param1=[%1]   
echo ______________Method_Out isfileexist_func_1x1
::ENDLOCAL
goto:eof




:getfilerownum_func_1x1
rem ======================================== getfilerownum_func_1x1
rem desc: 动态计算当前给定文件的行数并返回
rem sample: call :getfilerownum_func_1x1  %zbin%/A0.bat
rem sample_out: [getfilerownum_func_1x1 ]   getfilerownum_return_1=[10]   param1=[C:\Users\xxx\Desktop\zbin\A0.bat]
::SETLOCAL
echo ______________Method_In getfilerownum_func_1x1
set getfilerownum_return_1=0
if exist %1 (
for /f %%a in (' find /c /v "" ^<"%1" ') do set rownum=%%a
set getfilerownum_return_1=!rownum!
echo rownum[!rownum!]  getfilerownum_return_1[!getfilerownum_return_1!]
echo %1 Zukgit  File Exist
) else (
echo %1 Zukgit  File Do Not Exist
)
echo [getfilerownum_func_1x1 EndPrintCode]   getfilerownum_return_1=[!getfilerownum_return_1!]   param1=[%1]   
echo ______________Method_Out getfilerownum_func_1x1
::ENDLOCAL
goto:eof




:isrealfile_func_1x1
rem ======================================== isrealfile_func_1x1
rem desc: 检测文件是否存在    不存在 返回false   存在那么 继续判断是否是文件 如果是 那么返回true   是文件夹返回false
rem sample: call :isrealfile_func_1x1  %zbin%/A0.bat
rem sample_out: [isrealfile_func_1x1 ]   isfileexist_return_1=[true]   param1=[C:\Users\xxx\Desktop\zbin/A0.bat]
::SETLOCAL
echo ______________Method_In isrealfile_func_1x1
set isrealfile_exist=false
if exist %1 (
set isrealfile_exist=true
) else (
set isrealfile_exist=false
set isrealfile_return_1=false
echo [isrealfile_func_1x1 EndPrintCode]   isrealfile_return_1=[!isrealfile_return_1!]   param1=[%1]   
echo ______________Method_Out isrealfile_func_1x1
goto:eof
)
if "%isrealfile_exist%"=="true" (
if exist test\. (
set isrealfile_return_1=false
) else (
set isrealfile_return_1=true
) 
)
echo [isrealfile_func_1x1 EndPrintCode]   isrealfile_return_1=[!isrealfile_return_1!]   param1=[%1]   
echo ______________Method_Out isrealfile_func_1x1
::ENDLOCAL
goto:eof




:isemptydirfile_func_1x1
rem ======================================== isemptydirfile_func_1x1
rem desc: 检测当前目录是否是空目录(没有子文件 没有子文件夹)
rem sample: call :isemptydirfile_func_1x1  %zbin%
rem sample_out: [isemptydirfile_func_1x1 ]   isfileexist_return_1=[true]   param1=[C:\Users\xxx\Desktop\zbin/A0.bat]
::SETLOCAL
echo ______________Method_In isemptydirfile_func_1x1
echo readFile_func_1x0 input_1_param == %1
set isemptydirfile_return_1=false
dir /b /a-d /o-d /s   %1
echo errorlevel=%ERRORLEVEL%
if %ERRORLEVEL% LEQ 0 (
echo 当前目录有文件
set isemptydirfile_return_1=false
) else (
echo 当前目录没有有文件
set isemptydirfile_return_1=true
)
echo [isemptydirfile_func_1x1 EndPrintCode]   isemptydirfile_return_1=[!isemptydirfile_return_1!]   param1=[%1]   
echo ______________Method_Out isemptydirfile_func_1x1
::ENDLOCAL
goto:eof




:isdirfile_func_1x1
rem ======================================== isdirfile_func_1x1
rem desc: 检测文件夹是否存在    不存在 返回false   存在那么 继续判断是否是文件夹 如果是 那么返回true   是文件返回false
rem sample: call :isdirfile_func_1x1  %zbin%/A0.bat
rem sample_out: [isdirfile_func_1x1 ]   isfileexist_return_1=[true]   param1=[C:\Users\xxx\Desktop\zbin/A0.bat]
::SETLOCAL
echo ______________Method_In isdirfile_func_1x1
set isdirfile_exist=false
if exist %1 (
set isdirfile_exist=true
) else (
set isdirfile_exist=false
set isdirfile_return_1=false
echo [isdirfile_func_1x1 EndPrintCode]   isdirfile_return_1=[!isdirfile_return_1!]   param1=[%1]   
echo ______________Method_Out isdirfile_func_1x1
goto:eof
)
if "%isdirfile_exist%"=="true" (
if exist test\. (
set isdirfile_return_1=true
) else (
set isdirfile_return_1=false
) 
)
echo [isdirfile_func_1x1 EndPrintCode]   isdirfile_return_1=[!isdirfile_return_1!]   param1=[%1]   
echo ______________Method_Out isdirfile_func_1x1
::ENDLOCAL
goto:eof




:readfile_func_1x0
rem ======================================== readfile_func_1x0
rem desc: 读取指定全路径文件的每行内容
rem sample: call :readfile_func_1x0  %zbin%\A.bat
rem sample_out: 读取了 C:\Users\xxxx\Desktop\zbin\A.bat 的每行内容
::SETLOCAL
echo ______________Method_In readfile_func_1x0
echo readFile_func_1x0 input_1_param == %1
if exist %1 (
echo readFile_func_1x0 Success Read __: File not exist for __: readFile_func_1x0 input_1_param == %1
  for /f "delims=" %%l  in (%1) do ( 
       set /a num+=1 
       set s=%%l
	   echo line[!num!] = %%l
       )
   ) else (
echo readFile_func_1x0 Failed Read __: File not exist for __: readFile_func_1x0 input_1_param == %1
   )
echo [readfile_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out readfile_func_1x0
::ENDLOCAL
goto:eof




rem ======================== FILE_OPERATION End======================== 
rem ======================== SYSTEM_OPERATION Begin======================== 


:getandroidreceivembwithin5s_func_0x1
rem ======================================== getandroidreceivembwithin5s_func_0x1
rem desc: 检测当前USB adb 连接的安卓设备整体的对应的 当前5秒钟 接收流量 接受到的数据量 单位为 MB 不区分应用
rem sample: call :getandroidreceivembwithin5s_func_0x1
rem sample_out: [getandroidreceivembwithin5s_func_0x1 ]   getandroidreceivembwithin5s_return_1=[0] getandroidreceivembwithin5s_return_1=[1]
::SETLOCAL
echo ______________Method_In getandroidreceivembwithin5s_func_0x1
echo getandroidreceivembwithin5s_func_0x1  input_1_param == %1
set input_1_param=%1
set /a getandroidreceivembwithin5s_return_1=0
adb shell cat /proc/net/dev | findstr wlan0 | awk '{print $2}' > dev_recv_1.txt
ping -n 1 127.0.0.1>nul
cat dev_recv_1.txt
set /p dev_recv_num1=<dev_recv_1.txt
rem 0开头会有错误 所以再添加一个1 
set dev_recv_num1_end9=1!dev_recv_num1:~2!
echo dev_recv_num1_end9=!dev_recv_num1_end9!  dev_recv_num1=!dev_recv_num1!
ping -n 5 127.0.0.1>nul
adb shell cat /proc/net/dev | findstr wlan0 | awk '{print $2}' > dev_recv_2.txt
ping -n 1 127.0.0.1>nul
cat dev_recv_2.txt
set /p dev_recv_num2=<dev_recv_2.txt
rem 0开头会有错误 所以再添加一个1 
set dev_recv_num2_end9=1!dev_recv_num2:~2!
set /a dev_recv_num1_end9_10bit=!dev_recv_num1_end9!
set /a dev_recv_num2_end9_10bit=!dev_recv_num2_end9!
echo dev_recv_num2_end9=!dev_recv_num2_end9!  dev_recv_num2=!dev_recv_num2!   dev_recv_num1_end9_10bit=!dev_recv_num1_end9_10bit!   dev_recv_num2_end9_10bit=!dev_recv_num2_end9_10bit!
set /a dev_receive_distance_byte=!dev_recv_num2_end9_10bit!-!dev_recv_num1_end9_10bit!
set /a dev_receive_MB=!dev_receive_distance_byte!/(1024*1024)
echo  dev_recv_num1=!dev_recv_num1!  dev_recv_num2=!dev_recv_num2!_______ dev_recv_num1_end9=!dev_recv_num1_end9! dev_recv_num2_end9=!dev_recv_num2_end9!  dev_receive_distance_byte=!dev_receive_distance_byte! dev_receive_MB=!dev_receive_MB!
if !dev_receive_MB! GEQ 1  (
echo 当前有流量A  dev_receive_MB=!dev_receive_MB!
set /a getandroidreceivembwithin5s_return_1=!dev_receive_MB!
) else if !dev_receive_MB! LSS 0  (
echo 当前有流量B   dev_receive_MB=!dev_receive_MB!
echo=
echo=
rem 差距是负数 仍然说明 有流量 那么返回为1  意思意思 
set /a getandroidreceivembwithin5s_return_1=1
) else (
echo 当前 没流量 没流量   dev_receive_MB=!dev_receive_MB!
)
del dev_recv_1.txt
del dev_recv_2.txt
echo [getandroidreceivembwithin5s_func_0x1 EndPrintCode] getandroidreceivembwithin5s_return_1=[!getandroidreceivembwithin5s_return_1!]   param1=[__empty__] 
echo ______________Method_Out getandroidreceivembwithin5s_func_0x1
::ENDLOCAL
goto:eof




:getandroidreceivembwithappname_func_1x1
rem ======================================== getandroidreceivembwithappname_func_1x1
rem desc: 检测当前USB adb 连接的安卓设备对应提供的app名称在 5秒钟 接受到的数据量 单位为 MB
rem sample: call :getandroidreceivembwithappname_func_1x1
rem sample_out: [getandroidreceivembwithappname_func_1x1 ]   getandroidreceivembwith5s_return_1=[0] getandroidreceivembwith5s_return_1=[5]
::SETLOCAL
echo ______________Method_In getandroidreceivembwithappname_func_1x1
echo getandroidreceivembwithappname_func_1x1  input_1_param == %1
set input_1_param=%1
set /a getandroidreceivembwithappname_return_1=0
adb shell ps | findstr !input_1_param! | awk '{print $2}' > tel_pid.txt
ping -n 1 127.0.0.1>nul
cat tel_pid.txt
set /p tel_pid_num=<tel_pid.txt
adb shell cat /proc/!tel_pid_num!/net/dev | findstr wlan0 | awk '{print $2}' > tel_recv_1.txt
ping -n 1 127.0.0.1>nul
cat tel_recv_1.txt
set /p tel_recv_num1=<tel_recv_1.txt
rem 0开头会有错误 所以再添加一个1 
set tel_recv_num1_end9=1!tel_recv_num1:~2!
echo tel_recv_num1_end9=!tel_recv_num1_end9!  tel_recv_num1=!tel_recv_num1!
echo  tel_pid_num=!tel_pid_num!  tel_recv_num1=!tel_recv_num1!
ping -n 5 127.0.0.1>nul
adb shell cat /proc/!tel_pid_num!/net/dev | findstr wlan0 | awk '{print $2}' > tel_recv_2.txt
ping -n 1 127.0.0.1>nul
cat tel_recv_2.txt
set /p tel_recv_num2=<tel_recv_2.txt
rem 0开头会有错误 所以再添加一个1 
set tel_recv_num2_end9=1!tel_recv_num2:~2!
set /a tel_recv_num1_end9_10bit=!tel_recv_num1_end9!
set /a tel_recv_num2_end9_10bit=!tel_recv_num2_end9!
echo tel_recv_num2_end9=!tel_recv_num2_end9!  tel_recv_num2=!tel_recv_num2!   tel_recv_num1_end9_10bit=!tel_recv_num1_end9_10bit!   tel_recv_num2_end9_10bit=!tel_recv_num2_end9_10bit!
set /a receive_distance_byte=!tel_recv_num2_end9_10bit!-!tel_recv_num1_end9_10bit!
set /a receive_MB=!receive_distance_byte!/(1024*1024)
echo  ____ tel_pid_num=!tel_pid_num! tel_recv_num1=!tel_recv_num1!  tel_recv_num2=!tel_recv_num2!_______ tel_recv_num1_end9=!tel_recv_num1_end9! tel_recv_num2_end9=!tel_recv_num2_end9!  receive_distance_byte=!receive_distance_byte! receive_MB=!receive_MB!
if !receive_MB! GEQ 1  (
echo 当前有流量A  receive_MB=!receive_MB!
set /a getandroidreceivembwithappname_return_1=!receive_MB!
) else if !receive_MB! LSS 0  (
echo 当前有流量B   receive_MB=!receive_MB!
echo=
echo=
rem 差距是负数 仍然说明 有流量 那么返回为1  意思意思 
set /a getandroidreceivembwithappname_return_1=1
) else (
echo 当前 没流量 没流量   receive_MB=!receive_MB!
)
del tel_recv_1.txt
del tel_recv_2.txt
echo [getandroidreceivembwithappname_func_1x1 EndPrintCode]   getandroidreceivembwithappname_return_1=[!getandroidreceivembwithappname_return_1!]   param1=[%1]   
echo ______________Method_Out getandroidreceivembwithappname_func_1x1
::ENDLOCAL
goto:eof




:isadminuser_func_0x1
rem ======================================== isadminuser_func_0x1
rem desc: 检测当前运行环境是否是Admin 管理员权限   如果是管理员返回true    如果只是普通的用户返回false  通过net session 的返回值判断
rem sample: call :isadminuser_func_0x1
rem sample_out: [isadminuser_func_0x1 ]   isadminuser_return_1=[false]
::SETLOCAL
echo ______________Method_In isadminuser_func_0x1
net session
echo errorlevel=%ERRORLEVEL%
if %ERRORLEVEL% LEQ 1 (
echo 当前Admin用户权限
set isadminuser_return_1=true
) else (
echo 当前普通用户权限
set isadminuser_return_1=false
)
echo [isadminuser_func_0x1 EndPrintCode] isadminuser_return_1=[!isadminuser_return_1!]   param1=[__empty__] 
echo ______________Method_Out isadminuser_func_0x1
::ENDLOCAL
goto:eof




:showcmdercommand_func_1x0
rem ======================================== showcmdercommand_func_1x0
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In showcmdercommand_func_1x0
echo  %1 /REGISTER ALL  
%1 /REGISTER ALL  
start %1
echo;
echo;
echo ========= please add command as environment settings in Cmder.exe =========
echo;
echo set PATH=%%ConEmuBaseDir%%\Scripts;%%PATH%%
echo set PATH=%%USERPROFILE%%\Desktop\zbin\win_zbin;%%PATH%%
echo alias cdd=cd /D %%USERPROFILE%%\Desktop
echo alias cdz=cd /D %%USERPROFILE%%\Desktop\zbin
echo set LANG=zh_CN.UTF-8
echo;
rem echo set PATH=%ConEmuBaseDir%\Scripts;%PATH%
rem echo set PATH=E:\Temp_Install\jdk\bin;%PATH%
rem echo set PATH=%USERPROFILE%\Desktop\zbin\win_zbin;%PATH%
echo [showcmdercommand_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out showcmdercommand_func_1x0
::ENDLOCAL
goto:eof




:isenvironmentpathcontain_func_1x1
rem ======================================== isenvironmentpathcontain_func_1x1
rem desc: EQU - 等于   NEQ - 不等于   　LSS - 小于   LEQ - 小于或等于   GTR - 大于  　GEQ - 大于或等于
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In isenvironmentpathcontain_func_1x1
SET LocalPathCopy=%PATH%
SET InPathResult=0
SET Step_InPathResult=0
SET Step_InPathResult_Loop=0
SET isenvironmentpathcontain_return_1=""
:isenvironmentpathcontain_loop
IF "%LocalPathCopy" == "" GOTO isenvironmentpathcontain_exit
FOR /F "delims=;" %%I IN ("%LocalPathCopy%") DO (
  IF /I "%%~I" == "%~1" (
    SET InPathResult=1
    GOTO isenvironmentpathcontain_exit
  )
  set /a Step_InPathResult+=1
  echo Step_InPathResult=[%Step_InPathResult%]
)
FOR /F "delims=; tokens=1,*" %%I IN ("%LocalPathCopy%") DO (
  SET LocalPathCopy=%%~J
)
set /a Step_InPathResult_Loop+=1
echo Step_InPathResult_Loop=[%Step_InPathResult_Loop%]
if %Step_InPathResult_Loop%  GEQ 100 (
GOTO isenvironmentpathcontain_exit
)
GOTO isenvironmentpathcontain_loop
:isenvironmentpathcontain_exit
if "%InPathResult%" == "0" (
set isenvironmentpathcontain_return_1=false
)else (
set isenvironmentpathcontain_return_1=true
)
echo [isenvironmentpathcontain_func_1x1 EndPrintCode]   isenvironmentpathcontain_return_1=[!isenvironmentpathcontain_return_1!]   param1=[%1]   
echo ______________Method_Out isenvironmentpathcontain_func_1x1
::ENDLOCAL
goto:eof




:addpathenvironment_func_1x0
rem ======================================== addpathenvironment_func_1x0
rem desc: 添加一个全路径到环境变量 PATH中   echo %PATH%   实际执行 setx "Path" "%1;%path%" /m  如果存在则不添加
rem sample: call :addpathenvironment_func_1x0  %zbin%
rem sample_out: 把 C:\Users\xxxx\Desktop\zbin 假如到环境变量 PATH
::SETLOCAL
echo ______________Method_In addpathenvironment_func_1x0
rem 需要检查当前输入的路径  在当前Path 系统环境中是否存在  不存在才添加 
set cur_path_item=%1
rem iscontainstring_func_2x1  param1=[D:\Local_Soft_Test\zsoft\D0_Environment_Zip_Dir_Path\JDK8_64\bin\]   
rem param2=[D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\bin]
rem iscontainstring_func_2x1 传递参数时候 path 的数值太大  无法传递完全 
call  :isenvironmentpathcontain_func_1x1    %cur_path_item%
set cur_path_item_existflag=!isenvironmentpathcontain_return_1!
echo cur_path_item_existflag=[!cur_path_item_existflag!]
if "!cur_path_item_existflag!" == "true" (
echo cur_path_item_existflag=true__[!cur_path_item_existflag!]
echo 当前添加到 系统环境变量PATH中的Dir项 %cur_path_item% 已经存在  跳过该项加入到PATH的操作
echo [addpathenvironment_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out addpathenvironment_func_1x0
GOTO:EOF
) else (
echo cur_path_item_existflag=false__[!cur_path_item_existflag!]
echo 当前添加到 系统环境变量PATH中的Dir项 %cur_path_item% 不存在  将执行%cur_path_item% 加入到PATH的操作
)
echo 执行添加 %1 到系统环境变量的命令如下:
call ::clearstringpadding_func_1x1 %1
set path_no_padding=!clearstringpadding_return_1!
echo clear padding result str ___ path_no_padding[!path_no_padding!]   path_no_paddingA[%path_no_padding%]  clearstringpadding_return_1=[!clearstringpadding_return_1!] param1=[%1]   
echo  setx "Path" "!path_no_padding!;%path%" /m
setx "Path" "!path_no_padding!;%path%" /m
echo [addpathenvironment_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out addpathenvironment_func_1x0
::ENDLOCAL
goto:eof




:openfilewithnotepad_func_1x0
rem ======================================== openfilewithnotepad_func_1x0
rem desc: 使用 notepad++打开 指定的全路径文件
rem sample: call :openfilewithnotepad_func_1x0 %zbin%/A0.bat
rem sample_out: 用 notepad++ 打开了文件  C:\Users\xxxx\Desktop\zbin\A0.bat
::SETLOCAL
echo ______________Method_In openfilewithnotepad_func_1x0
echo  openFileWithNotepad_func_1x0 input_1_param == %1
echo  open the file with notepad++.exe -> [%1]
notepad++.exe   %1
echo [openfilewithnotepad_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out openfilewithnotepad_func_1x0
::ENDLOCAL
goto:eof




:gettimestamp_func_0x1
rem ======================================== gettimestamp_func_0x1
rem desc: 获取当前时间戳 包含汉字 用于打印
rem sample: call :gettimestamp_func_0x1
rem sample_out: [gettimestamp_func_0x1 ] gettimestamp_return_1=[周二 22105_185844]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimestamp_func_0x1
set getTimeStamp_hh=%time:~0,2%
if /i %getTimeStamp_hh% LSS 10 (set getTimeStamp_hh=0%time:~1,1%)
set getTimeStamp_return_1=%date:~0,4%%date:~5,2%%date:~8,2%_%getTimeStamp_hh%%time:~3,2%%time:~6,2%
echo [gettimestamp_func_0x1 EndPrintCode] gettimestamp_return_1=[!gettimestamp_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimestamp_func_0x1
::ENDLOCAL
goto:eof




:gettimehhmmsss_func_0x1
rem ======================================== gettimehhmmsss_func_0x1
rem desc: 获取当前年份时间戳记录全数字
rem sample: [gettimehhmmsss_func_0x1 ] gettimehhmmsss_return_1=[21-05-18_18_57_36_51]   param1=[__empty__]
rem sample_out: [gettimehhmmsss_func_0x1 ] gettimehhmmsss_return_1=[21-05-18_18_57_36_52]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimehhmmsss_func_0x1
set gettimehhmmsss_return_1=%DATE:~5,2%-%DATE:~8,2%-%DATE:~11,2%_%TIME:~0,2%_%TIME:~3,2%_%TIME:~6,2%_%TIME:~9,2%
echo gettimehhmmsss_return_1=%getTimeNona_return_1%
echo [gettimehhmmsss_func_0x1 EndPrintCode] gettimehhmmsss_return_1=[!gettimehhmmsss_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimehhmmsss_func_0x1
::ENDLOCAL
goto:eof




:gettimemmddhhmm_func_0x1
rem ======================================== gettimemmddhhmm_func_0x1
rem desc: 获取当前年份时间戳记录全数字
rem sample: [gettimemmddhhmm_func_0x1 ] gettimemmddhhmm_return_1=[21-05-18_18_57_36_51]   param1=[__empty__]
rem sample_out: [gettimemmddhhmm_func_0x1 ] gettimemmddhhmm_return_1=[21-05-18_18_57_36_52]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimemmddhhmm_func_0x1
set gettimemmddhhmm_return_1=%DATE:~8,2%%DATE:~11,2%_%TIME:~0,2%%TIME:~3,2%
echo gettimemmddhhmm_return_1=%gettimemmddhhmm_return_1%
echo [gettimemmddhhmm_func_0x1 EndPrintCode] gettimemmddhhmm_return_1=[!gettimemmddhhmm_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimemmddhhmm_func_0x1
::ENDLOCAL
goto:eof




:gettimeddhhmm_func_0x1
rem ======================================== gettimeddhhmm_func_0x1
rem desc: 获取当前日时分数据
rem sample: [gettimeddhhmm_func_0x1 ] gettimeddhhmm_return_1=[21-05-18_18_57_36_51]   param1=[__empty__]
rem sample_out: [gettimeddhhmm_func_0x1 ] gettimeddhhmm_return_1=[21-05-18_18_57_36_52]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimeddhhmm_func_0x1
set gettimeddhhmm_return_1=%DATE:~11,2%%TIME:~0,2%%TIME:~3,2%
echo gettimeddhhmm_return_1=%gettimeddhhmm_return_1%
echo [gettimeddhhmm_func_0x1 EndPrintCode] gettimeddhhmm_return_1=[!gettimeddhhmm_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimeddhhmm_func_0x1
::ENDLOCAL
goto:eof




:gettimenona_func_0x1
rem ======================================== gettimenona_func_0x1
rem desc: 获取当前的时间戳信息 包含周几 用于打印
rem sample: call :gettimenona_func_0x1
rem sample_out: [gettimenona_func_0x1 ] gettimenona_return_1=[周二 2-21-05 18:55:51.25]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimenona_func_0x1
set getTimeNona_return_1=%DATE:~0,4%-%DATE:~5,2%-%DATE:~8,2% %TIME:~0,2%:%TIME:~3,2%:%TIME:~6,2%.%TIME:~9,2%
echo getTimeNona_return_1=%getTimeNona_return_1%
echo [gettimenona_func_0x1 EndPrintCode] gettimenona_return_1=[!gettimenona_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimenona_func_0x1
::ENDLOCAL
goto:eof




:createdirwithpath_func_1x0
rem ======================================== createdirwithpath_func_1x0
rem desc: 在当前路径创建 输入的文件夹全路径  对应的文件夹
rem sample: call :createdirwithpath_func_1x0 %zbin%\TestX
rem sample_out: 路径下 C:\Users\xxxx\Desktop\zbin\TestX  文件夹 被创建
::SETLOCAL
echo ______________Method_In createdirwithpath_func_1x0
echo  createDirWithPath_func_1x0 input_1_param == %1
rem 创建指定输入全路径下的文件夹
md  %1
echo [createdirwithpath_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out createdirwithpath_func_1x0
::ENDLOCAL
goto:eof




:explorerwithpath_func_1x0
rem ======================================== explorerwithpath_func_1x0
rem desc: 使用explorer 打开给定的路径文件夹
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In explorerwithpath_func_1x0
echo  explorerWithPath_func_1x0 input_1_param == %1
rem 使用文件管理器打开 对应的目录
explorer.exe  %1
echo [explorerwithpath_func_1x0 EndPrintCode]    output=[__empty__] param1=[%1]   
echo ______________Method_Out explorerwithpath_func_1x0
::ENDLOCAL
goto:eof




rem ======================== SYSTEM_OPERATION End======================== 
rem ======================== STRING_OPERATION Begin======================== 


:stringtest_func_0x0
rem ======================================== stringtest_func_0x0
rem desc: 字符串的操作集合

rem sample: 操作示例看代码
rem sample_out: 
::SETLOCAL
echo ______________Method_In stringtest_func_0x0
echo  stringTest_func_0x0 input_1_param == empty
rem  set 后面不要使用 rem注释     set后面空格也会影响结果!
echo ___________________________________ Get SubString   
set number_str=0123456789
echo number_str=%number_str%       
set number_str_1=!number_str:~1!   rem 去掉前一个字符串
echo number_str_1=%number_str_1%   rem 123456789
set number_str_2=!number_str:~2!   rem 去掉前两个字符串
echo number_str_2=%number_str_2%   rem number_str_2=3456789
set number_str_2_down=!number_str:~-2!
echo number_str_2_down=%number_str_2_down%    rem  :~-2  只保留最后两个字符
set number_str_02_down=!number_str:~0,-2!
echo number_str_02_down=%number_str_02_down%    rem  :~0,-2  去掉最后两个字符串
set number_str_02_down_T=!number_str:~-2,1!
echo number_str_02_down_T=%number_str_02_down_T%     rem  取到倒数第二个字符
set number_str_01_down_T=!number_str:~-1,1!
echo number_str_01_down_T=%number_str_01_down_T%       rem  取到倒数第一个字符
set number_str_3=!number_str:~3! 
echo number_str_3=%number_str_3%   rem 去掉前三个字符串   rem number_str_3=456789

rem  set number_str_3=!number_str:~3!   rem 去掉前三个字符串 

rem  set number_str_A=!number_str:~-1!  最后一个字符 

rem   set number_str_02_down=!number_str:~0,-2!  去掉最后两个字符 

rem   set number_str_02_down_T=!number_str:~-2,1!  取到倒数第二个字符    

rem 取前四个字符串  
set number_str_0_4=!number_str:~0,4!  
echo number_str_0_4=%number_str_0_4%  rem number_str_0_4=1234  
rem 取索引 2(索引0开始) 开始 后面5个字符串  
set number_str_2_5=!number_str:~2,5!
echo number_str_2_5=%number_str_2_5%  rem  number_str_2_5=34567  
rem 只保留最后一个字符  ~-1  只保留最后一个字符  
set number_str_A=!number_str:~-1!
echo number_str_A(number_str:~-1)=%number_str_A%    rem  number_str_A(number_str:~-1)=9 
rem 只保留最后四个字符  ~-4  只保留最后一个字符  
set number_str_B=!number_str:~-4!
echo number_str_B(number_str:~-4)=%number_str_B%    rem  number_str_B(number_str:~-4)=6789
echo ___________________________________ String Eaqual Test 
if "!number_str_B!"=="6789" (
echo number_str_B==6789 is true  true  number_str_B====[!number_str_B!]
) else ( 
echo number_str_B=6789 is false false  number_str_B====[!number_str_B!]
)
rem number_str_T1 左右 各三个空格 
set number_str_T1=   123456789   
echo number_str_T1=[%number_str_T1%]    rem  number_str_T1=[   123456789   ]
call :stringTrim_func_1x1 %number_str_T1%
set number_str_T1=%stringTrim_return_1%
echo number_str_T1=[%number_str_T1%]       rem  number_str_T1=[123456789]
rem number_str_T1 左右中都有空格 
set number_str_T2=   123 456 789   
echo number_str_T2=[%number_str_T2%]    rem   number_str_T2=[   123 456 789   ]
rem 去除所有空格  中间的 左右的 都去掉 

rem  去除掉所有的空格 路径字符串 有用 
set number_str_T2_clearBlank=%number_str_T2: =%
echo number_str_T2_clearBlank=[%number_str_T2_clearBlank%]    rem  number_str_T2_clearBlank=[123456789]
if "!number_str_T1!"=="!number_str_T2_clearBlank!" (
echo number_str_T1=!number_str_T1!  =======  number_str_T2_clearBlank====[!number_str_T2_clearBlank!]
) else ( 
echo number_str_T1=!number_str_T1!  !!!!====  number_str_T2_clearBlank====[!number_str_T2_clearBlank!]
)
echo [stringtest_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out stringtest_func_0x0
::ENDLOCAL
goto:eof




:getrandomintwithmaxmin_func_2x1
rem ======================================== getrandomintwithmaxmin_func_2x1
rem desc: 获得一个指定 max 与 min 之间的一个随机数
rem sample: call :getrandomintwithmaxmin_func_2x1  0 100
rem sample: call :getrandomintwithmaxmin_func_2x1  1000 10000
rem sample_out: getrandomintwithmaxmin_return_1=[21]   param1=[0]   param2=[100]
rem sample_out: getrandomintwithmaxmin_return_1=[5437]   param1=[1000]   param2=[10000]
::SETLOCAL
echo ______________Method_In getrandomintwithmaxmin_func_2x1
set getrandomintwithmaxmin_return_1=%1
set random_min=%1
set random_max=%2
if "%random_min%"=="" (
set /a random_min=0
) 
if "%random_max%"=="" (
set /a random_min=100
)
set /a random_mod=!random_max!-!random_min!+1 
echo random_mod=!random_mod!
rem 产生[min,max]之间的随机数 
for /l %%i in (1,1,1) do (
rem echo random=!random!
set /a getrandomintwithmaxmin_return_1=!random!%%!random_mod!+!random_min! 
)
rem  去除掉所有的空格 路径字符串 有用  
echo [getrandomintwithmaxmin_func_2x1 EndPrintCode]   getrandomintwithmaxmin_return_1=[!getrandomintwithmaxmin_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getrandomintwithmaxmin_func_2x1
::ENDLOCAL
goto:eof




:stringtrim_func_1x1
rem ======================================== stringtrim_func_1x1
rem desc: 去字符串中的所有的空格  不去除引号
rem sample: call :stringtrim_func_1x1 " ABC "
rem sample: call :stringtrim_func_1x1 " ABCD    "
rem sample: call :stringtrim_func_1x1 " A B C D    "
rem sample_out: [stringtrim_func_1x1 ]   stringtrim_return_1=["ABC"]   param1=[" ABC "]
rem sample_out: [stringtrim_func_1x1 ]   stringtrim_return_1=["ABCD"]   param1=[" ABCD    "]
rem sample_out: [stringtrim_func_1x1 ]   stringtrim_return_1=["ABCD"]   param1=[" A B C D    "]
::SETLOCAL
echo ______________Method_In stringtrim_func_1x1
echo  stringTrim_func_1x1 input_1_param ==[%1]
set trim_temp=%1
rem  去除掉所有的空格 路径字符串 有用  
set trim_str=%trim_temp: =%
echo stringTrim_return_1 = [%trim_str%]
set stringTrim_return_1=%trim_str%
echo stringTrim_return_1=[%stringTrim_return_1%]  param1=[%1] 
echo [stringtrim_func_1x1 EndPrintCode]   stringtrim_return_1=[!stringtrim_return_1!]   param1=[%1]   
echo ______________Method_Out stringtrim_func_1x1
::ENDLOCAL
goto:eof




:getpaddingstringlength_func_1x1
rem ======================================== getpaddingstringlength_func_1x1
rem desc: 获取包含两边引号的字符串的长度 任意字符串原始长度
rem sample: call :getpaddingstringlength_func_1x1 123456789
rem sample: call :getpaddingstringlength_func_1x1 "123456789"
rem sample_out: [getpaddingstringlength_func_1x1 ]   getpaddingstringlength_return_1=[11]   param1=["123456789"]
rem sample_out: [getpaddingstringlength_func_1x1 ]   getpaddingstringlength_return_1=[9]   param1=[123456789]
::SETLOCAL
echo ______________Method_In getpaddingstringlength_func_1x1
echo  getpaddingstringlength_func_1x1 input_1_param ==[%1]
set /a getStringLength_numsize = 0
set getStringLength_str=%1
:getStringPaddingLength_length_block
if not "!getStringLength_str!"=="" (
 set /a getStringLength_numsize+=1
 set "getStringLength_str=!getStringLength_str:~1!"
 goto getStringPaddingLength_length_block
)
set /a getpaddingstringlength_return_1=%getStringLength_numsize%
echo getpaddingstringlength_return_1=[!getpaddingstringlength_return_1!]  param1=[%1] 
echo [getpaddingstringlength_func_1x1 EndPrintCode]   getpaddingstringlength_return_1=[!getpaddingstringlength_return_1!]   param1=[%1]   
echo ______________Method_Out getpaddingstringlength_func_1x1
::ENDLOCAL
goto:eof




:stringspecialrecovery_func_1x1
rem ======================================== stringspecialrecovery_func_1x1
rem desc: 对字符串中的=后进行转词z转为 z_deng_z的恢复措施
rem sample: call :stringspecialrecovery_func_1x1 "123z_deng_z321z_deng_z456z_deng_zz_deng_z"
rem sample: call :stringspecialrecovery_func_1x1 "z_deng_zz_deng_zz_deng_zz_deng_zz_deng_zz_deng_z"
rem sample_out: [stringspecialrecovery_func_1x1 ]   stringspecialrecovery_return_1=["123=321=456=="]   param1=["123z_deng_z321z_deng_z456z_deng_zz_deng_z"]
rem sample_out: [stringspecialrecovery_func_1x1 ]   stringspecialrecovery_return_1=["======"]   param1=["z_deng_zz_deng_zz_deng_zz_deng_zz_deng_zz_deng_z"]
::SETLOCAL
echo ______________Method_In stringspecialrecovery_func_1x1
set temp_str=%1
set stringspecialrecovery_return_1=%temp_str:z_deng_z==%
echo [stringspecialrecovery_func_1x1 EndPrintCode]   stringspecialrecovery_return_1=[!stringspecialrecovery_return_1!]   param1=[%1]   
echo ______________Method_Out stringspecialrecovery_func_1x1
::ENDLOCAL
goto:eof




:stringspecialchange_func_1x1
rem ======================================== stringspecialchange_func_1x1
rem desc: 当字符串中存在= 等号 那么对这样的字符串进行if比较 时会报错 所以 需要把 =等号转词  =转为z_deng_z
rem sample: call :stringspecialchange_func_1x1 "123=321=456=="
rem sample: call :stringspecialchange_func_1x1 "======"
rem sample_out: [stringspecialchange_func_1x1 ]   stringspecialchange_return_1=["123z_deng_z321z_deng_z456z_deng_zz_deng_z"]          param1=["123=321=456=="]
rem sample_out: [stringspecialchange_func_1x1 ]   stringspecialchange_return_1=["z_deng_zz_deng_zz_deng_zz_deng_zz_deng_zz_deng_z"]   param1=["======"]
::SETLOCAL
echo ______________Method_In stringspecialchange_func_1x1
echo [stringspecialchange_func_1x1 ]   param1=[%1] 
set origin_str=%1
set temp_str=%1
echo param_str_1_nopadding=[!param_str_1_nopadding!]   origin_str=[%origin_str%]    temp_str=[%temp_str%]
call :getpaddingstringlength_func_1x1  %1
set /a str1_length=!getpaddingstringlength_return_1!
echo str1_length==%str1_length%
set order_str=
for /l %%i in (1, 1, %str1_length%) do (
set first_char=!temp_str:~0,1!
echo i == %%i    first_char=[!first_char!]   temp_str=[!temp_str!]  order_str=[!order_str!]
set  temp_str=!origin_str:~%%i!
if "!first_char!" equ "=" (
echo 当前是空格=  需要替换为 z_deng_z 符号  索引=%%i
set order_str=!order_str!z_deng_z
) else (
set order_str=!order_str!!first_char!
)
)
echo order_str_end=[!order_str!]
set order_str_fixed=%order_str:z_deng_z==%
echo order_str_fixed=[%order_str_fixed%]
set stringspecialchange_return_1=!order_str!
echo [stringspecialchange_func_1x1 EndPrintCode]   stringspecialchange_return_1=[!stringspecialchange_return_1!]   param1=[%1]   
echo ______________Method_Out stringspecialchange_func_1x1
::ENDLOCAL
goto:eof




:getsubstringwithpre_func_2x1
rem ======================================== getsubstringwithpre_func_2x1
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getsubstringwithpre_func_2x1
echo  getSubStringWithPre_func_2x1 input_1_param ==[%1]
echo  getSubStringWithPre_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set getSubStringWithPre_param_str_1_nopadding=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set getSubStringWithPre_param_str_2_nopadding=%clearStringPadding_return_1%
set getSubStringWithPre_param_str_1=%1
set getSubStringWithPre_param_str_trim1=%getSubStringWithPre_param_str_1: =%
set getSubStringWithPre_param_str_2=%2
set getSubStringWithPre_param_str_trim2=%getSubStringWithPre_param_str_2: =%
set getSubStringWithPre_param_str_2_existflag=false
set getSubStringWithPre_return_1=
rem 如果第一个字符串为空 输入的要截取的字符串是空的话  那么直接返回空
echo X2_getSubStringWithPre_param_str_trim1==%getSubStringWithPre_param_str_trim1%
if "%getSubStringWithPre_param_str_trim1%"=="" (
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithPre_param_str_trim2%"=="" (
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
set isContainString_return_1=false
call :isContainString_func_2x1 %getSubStringWithPre_param_str_1%   %getSubStringWithPre_param_str_2%
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithPre_func_2x1  Str1_Param do not contain Str2_Param "
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
set getStringLength_return_1=
call :getStringLength_func_1x1  %getSubStringWithPre_param_str_1_nopadding%
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1  %getSubStringWithPre_param_str_2_nopadding%
set /a str2_length=getStringLength_return_1
echo getSubStringWithPre_func_2x1  str1_length=[%str1_length%]   str2_length=[%str2_length%]   
set getInt4SubStringIndexOf_return_1=
call :getInt4SubStringIndexOf_func_2x1 "%getSubStringWithPre_param_str_1_nopadding%"  "%getSubStringWithPre_param_str_2_nopadding%"
echo getInt4SubStringIndexOf_return_1=[!getInt4SubStringIndexOf_return_1!]
rem  匹配到的索引  加上 自身的长度就等于 新的 需要返回的字符串的索引 
set /a getSubStringWithPre_begin_index=getInt4SubStringIndexOf_return_1+str2_length-1
echo getInt4SubStringIndexOf_return_1=[!getInt4SubStringIndexOf_return_1!] str2_length=[%str2_length%]  getSubStringWithPre_param_str_2_nopadding=[%getSubStringWithPre_param_str_2_nopadding%]
rem 取前四个字符串  
rem set number_str_0_4=!number_str:~0,4!  
rem 去掉 前一个字符串
rem set number_str_1=!number_str:~1!
echo  getSubStringWithPre_begin_index=[%getSubStringWithPre_begin_index%]  getSubStringWithPre_param_str_1_nopadding=[%getSubStringWithPre_param_str_1_nopadding%]
set  getSubStringWithPre_result=!getSubStringWithPre_param_str_1_nopadding:~%getSubStringWithPre_begin_index%!
echo getSubStringWithPre_result=[%getSubStringWithPre_result%]
set getSubStringWithPre_return_1=%getSubStringWithPre_result%
set getTimeNona_return_1=
call :getTimeNona_func_0x1
echo getSubStringWithPre_return_1=[%getSubStringWithPre_return_1%]   param1=[%1]  param2=[%2] Time=%getTimeNona_return_1%
rem if 1 LSS 2 echo %str1_length%小于%str2_length%

rem if 2 LSS 1 ( echo %str1_length%xx小于xx%str2_length% ) else ( echo  Continue.... )
rem  if %str1_length% LSS %str2_length% (
rem  GOTO:EOF
rem  ) else (
rem  echo  First-String-Length  Second-String-Length  ,  continue execute ! 
rem  )
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
::ENDLOCAL
goto:eof




:getsubstringwithend_func_2x1
rem ======================================== getsubstringwithend_func_2x1
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getsubstringwithend_func_2x1
echo  getSubStringWithEnd_func_2x1 input_1_param ==[%1]
echo  getSubStringWithEnd_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set getSubStringWithEnd_param_str_1_nopadding=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set getSubStringWithEnd_param_str_2_nopadding=%clearStringPadding_return_1%
set getSubStringWithEnd_param_str_1=%1
set getSubStringWithEnd_param_str_trim1=%getSubStringWithEnd_param_str_1: =%
set getSubStringWithEnd_param_str_2=%2
set getSubStringWithEnd_param_str_trim2=%getSubStringWithEnd_param_str_2: =%
set getSubStringWithEnd_return_1=
rem 如果第一个字符串为空 输入的要截取的字符串是空的话  那么直接返回空
if "%getSubStringWithEnd_param_str_1_nopadding%"=="" (
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
echo x2  getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]
rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithEnd_param_str_2_nopadding%"=="" (
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
echo x1getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]
set isContainString_return_1=false
call :isContainString_func_2x1 "%getSubStringWithEnd_param_str_1_nopadding%"   "%getSubStringWithEnd_param_str_2_nopadding%"
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithEnd_func_2x1  Str1_Param do not contain Str2_Param "
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
set getStringLength_return_1=
call :getStringLength_func_1x1  %getSubStringWithEnd_param_str_1_nopadding%
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1  %getSubStringWithEnd_param_str_2_nopadding%
set /a str2_length=getStringLength_return_1
echo getSubStringWithEnd_func_2x1  str1_length=[%str1_length%]   str2_length=[%str2_length%]   
echo  getSubStringWithEnd_param_str_1_nopadding=[%getSubStringWithEnd_param_str_1_nopadding%]    getSubStringWithEnd_param_str_2_nopadding=[%getSubStringWithEnd_param_str_2_nopadding%]
set getInt4SubStringIndexOf_return_1=
call :getInt4SubStringIndexOf_func_2x1 "%getSubStringWithEnd_param_str_1_nopadding%"  "%getSubStringWithEnd_param_str_2_nopadding%"
rem 当前匹配的索引 -1  就是之前的 EndFlag 之前的 字符串了  
set /a getSubStringWithEnd_begin_index=getInt4SubStringIndexOf_return_1-1
echo getSubStringWithEnd_begin_index=[%getSubStringWithEnd_begin_index%]
set  getSubStringWithEnd_result=!getSubStringWithEnd_param_str_1_nopadding:~0,%getSubStringWithEnd_begin_index%!
set getSubStringWithEnd_return_1=%getSubStringWithEnd_result%
echo getSubStringWithEnd_return_1=[%getSubStringWithEnd_return_1%]   param1=[%1]   param2=[%2] 
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
::ENDLOCAL
goto:eof




:stringreplace_func_3x1
rem ======================================== stringreplace_func_3x1
rem desc: 把字符串进行替换 实现replace方法

rem desc: 返回在参数一字符串的夹在中间的字符串 没有找到对应字符串返回空
rem sample: call :stringreplace_func_3x1 "123456789"  "123"  "789"
rem sample: call :stringreplace_func_3x1 "123456789"  "23"  "56"
rem sample: call :stringreplace_func_3x1 "123456789"  ""  "45"
rem sample: call :stringreplace_func_3x1 "111222111"  "11"  "AA"
rem sample_out: [stringreplace_func_3x1 ]   stringreplace_return_1=[789456789]   param1=["123456789"]   param2=["123"]   param3=["789"]
rem sample_out: [stringreplace_func_3x1 ]   stringreplace_return_1=[156456789]   param1=["123456789"]   param2=["23"]   param3=["56"]
rem sample_out: [stringreplace_func_3x1 ]   stringreplace_return_1=[123456789]   param1=["123456789"]   param2=[""]     param3=["45"]
rem sample_out: [stringreplace_func_3x1 ]   stringreplace_return_1=[AA1222AA1]   param1=["111222111"]   param2=["11"]   param3=["AA"]
::SETLOCAL
echo ______________Method_In stringreplace_func_3x1
echo  stringreplace_func_3x1 input_1_param ==[%1]
echo  stringreplace_func_3x1 input_2_param ==[%2]
echo  stringreplace_func_3x1 input_3_param ==[%3]
set   stringreplace_return_1=
set param_str_1=%1
set param_str_trim1=%param_str_1: =%
set param_str_2=%2
set param_str_trim2=%param_str_2: =%
set param_str_2_existflag=false
set param_str_3=%3
set param_str_trim3=%param_str_3: =%
set param_str_3_existflag=false
echo  stringreplace_func_3x1--param_str_trim1  ==[%param_str_trim1%]
echo  stringreplace_func_3x1--param_str_trim2  ==[%param_str_trim2%]
echo  stringreplace_func_3x1--param_str_trim3  ==[%param_str_trim3%]
call :clearStringPadding_func_1x1  %param_str_1%
set param_str_trim1_nopadding=!clearStringPadding_return_1!
call :clearStringPadding_func_1x1  %param_str_2%
set param_str_trim2_nopadding=!clearStringPadding_return_1!
call :clearStringPadding_func_1x1  %param_str_3%
set param_str_trim3_nopadding=!clearStringPadding_return_1!
echo  stringreplace_func_3x1--param_str_trim1_nopadding  ==[%param_str_trim1_nopadding%]
echo  stringreplace_func_3x1--param_str_trim2_nopadding  ==[%param_str_trim2_nopadding%]
echo  stringreplace_func_3x1--param_str_trim3_nopadding  ==[%param_str_trim3_nopadding%]
set temp_str=%1
echo temp_str=%temp_str%
echo command [ set stringreplace_return_1=%temp_str:!param_str_trim2_nopadding!=!param_str_trim3_nopadding!% ]
call set "stringreplace_return_1=%%temp_str:!param_str_trim2_nopadding!=!param_str_trim3_nopadding!%%"
echo stringreplace_return_1__[ !stringreplace_return_1! ] 
echo [stringreplace_func_3x1 EndPrintCode]   stringreplace_return_1=[!stringreplace_return_1!]   param1=[%1]   param2=[%2]   param3=[%3]   
echo ______________Method_Out stringreplace_func_3x1
::ENDLOCAL
goto:eof




:getsubstringwithpreendtag_func_3x1
rem ======================================== getsubstringwithpreendtag_func_3x1
rem desc: 忽略两边引号  以参数二为起始头字符串(不包含)  以参数三为结尾字符串(不包含)

rem desc: 返回在参数一字符串的夹在中间的字符串 没有找到对应字符串返回空

rem desc: 如果前缀preTag[参数二 为空 ] 为空  那么返回开头直到后缀[参数三](不包含)的 参数一子字符串

rem desc: 如果后缀endTag[参数三 为空 ] 为空  那么返回前缀[参数二](不包含)到字符串末尾的 参数一子字符串
rem sample: call :getsubstringwithpreendtag_func_3x1 "123456789"  "123"  "789"
rem sample: call :getsubstringwithpreendtag_func_3x1 "123456789"  "23"  "56"
rem sample: call :getsubstringwithpreendtag_func_3x1 "123456789"  ""  "45"
rem sample: call :getsubstringwithpreendtag_func_3x1 "123456789"  "56"  ""
rem sample: call :getsubstringwithpreendtag_func_3x1 "123456789"  "AB"  "CD"
rem sample_out: [getsubstringwithpreendtag_func_3x1 ]   getsubstringwithpreendtag_return_1=[456]   param1=["123456789"]   param2=["123"]   param3=["789"]
rem sample_out: [getsubstringwithpreendtag_func_3x1 ]   getsubstringwithpreendtag_return_1=[4]   param1=["123456789"]   param2=["23"]   param3=["56"]
rem sample_out: [getsubstringwithpreendtag_func_3x1 ]   getsubstringwithpreendtag_return_1=[123]   param1=["123456789"]   param2=[""]     param3=["45"]
rem sample_out: [getsubstringwithpreendtag_func_3x1 ]   getsubstringwithpreendtag_return_1=[789]   param1=["123456789"]   param2=["56"]   param3=[""]
rem sample_out: [getsubstringwithpreendtag_func_3x1 ]   getsubstringwithpreendtag_return_1=[]     param1=["123456789"]    param2=["AB"]   param3=["CD"]
::SETLOCAL
echo ______________Method_In getsubstringwithpreendtag_func_3x1
echo  getSubStringWithPreEndTag_func_3x1 input_1_param ==[%1]
echo  getSubStringWithPreEndTag_func_3x1 input_2_param ==[%2]
echo  getSubStringWithPreEndTag_func_3x1 input_3_param ==[%3]
set   getSubStringWithPreEndTag_return_1=
set param_str_1=%1
set param_str_trim1=%param_str_1: =%
set param_str_2=%2
set param_str_trim2=%param_str_2: =%
set param_str_2_existflag=false
set param_str_3=%3
set param_str_trim3=%param_str_3: =%
set param_str_3_existflag=false
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim1  ==[%param_str_trim1%]
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim2  ==[%param_str_trim2%]
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim3  ==[%param_str_trim3%]
call :clearStringPadding_func_1x1  %param_str_1%
set param_str_trim1_nopadding=!clearStringPadding_return_1!
call :clearStringPadding_func_1x1  %param_str_2%
set param_str_trim2_nopadding=!clearStringPadding_return_1!
call :clearStringPadding_func_1x1  %param_str_3%
set param_str_trim3_nopadding=!clearStringPadding_return_1!
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim1_nopadding  ==[%param_str_trim1_nopadding%]
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim2_nopadding  ==[%param_str_trim2_nopadding%]
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim3_nopadding  ==[%param_str_trim3_nopadding%]
rem 如果第一个字符串为空 输入的要截取的字符串是空的话  那么直接返回空
if "%param_str_trim1_nopadding%"=="" (
echo [getsubstringwithpreendtag_func_3x1 EndPrintCode]   getsubstringwithpreendtag_return_1=[!getsubstringwithpreendtag_return_1!]   param1=[%1]   param2=[%2]   param3=[%3]   
echo ______________Method_Out getsubstringwithpreendtag_func_3x1
GOTO:EOF
)
rem  检测第二个参数  是否为 空  为空的话 表示 当前不想要 前缀 
if "%param_str_trim2_nopadding%"=="" (
echo  getSubStringWithPreEndTag_func_3x1--param_str_trim2 is null is empty! 
    if "%param_str_trim3_nopadding%"=="" (
	 rem 如果  第二个  三个  参数 都为空  那么  直接返回 
	 echo getSubStringWithPreEndTag  str1[ok]   str2[null]   str3[null]  
	) else (
	 rem 如果  第二个为空   第三个参数不为空     那么说明 要返回  最后参数 之前的字符串  
	 echo getSubStringWithPreEndTag  str1[ok]   str2[null]   str3[ok]  
	 set getSubStringWithEnd_return_1=
	 call :getSubStringWithEnd_func_2x1  "%param_str_trim1_nopadding%"   "%param_str_trim3_nopadding%"
	 set getSubStringWithPreEndTag_return_1=!getSubStringWithEnd_return_1!
	 echo 1_0_1_getSubStringWithEnd_return_1=[!getSubStringWithEnd_return_1!]   getSubStringWithPreEndTag_return_1=[%getSubStringWithPreEndTag_return_1%]
	)
) else (
      if "%param_str_trim3_nopadding%"=="" (
	  	 echo getSubStringWithPreEndTag  str1[ok]   str2[ok]   str3[null]  
	  	 rem 如果  第二个参数不为空   第三个参数为空  那么  直接返回  第二个参数之后的字符串 
	  	 set getSubStringWithPre_return_1=
		 call :getSubStringWithPre_func_2x1  "%param_str_trim1_nopadding%"   "%param_str_trim2_nopadding%"
		 set getSubStringWithPreEndTag_return_1=!getSubStringWithPre_return_1!
	     echo 1_1_0_getSubStringWithEnd_return_1=[!getSubStringWithPre_return_1!]   getSubStringWithPreEndTag_return_1=[%getSubStringWithPreEndTag_return_1%]
	  ) else (
            echo getSubStringWithPreEndTag  str1[ok]   str2[ok]   str3[ok]  
            rem 如果  第二个参数 第三个参数 都不为空     那么  返回两个参数之间的字符串数据

            rem 先取到 preFlag之后的字符串
            set getSubStringWithPre_return_1=
            echo xxxxxxaaaaaA getSubStringWithPre_return_1=%getSubStringWithPre_return_1%  param_str_trim1_nopadding=[%param_str_trim1_nopadding%]  param_str_trim2_nopadding=[%param_str_trim2_nopadding%]
            call :getSubStringWithPre_func_2x1  "%param_str_trim1_nopadding%"   "%param_str_trim2_nopadding%"
            echo x3_getSubStringWithPre_return_1=!getSubStringWithPre_return_1!
            echo x2_getSubStringWithPre_return_1=!getSubStringWithPre_return_1!
            echo getSubStringWithPre_return_1=[!getSubStringWithPre_return_1!]  param_str_3=[%param_str_3%]
            set getSubStringWithEnd_return_1=
            call :getSubStringWithEnd_func_2x1  "!getSubStringWithPre_return_1!"   !param_str_3!
            echo xxxxxxaaaaa1   getSubStringWithEnd_return_1=[!getSubStringWithEnd_return_1!]
            echo getSubStringWithEnd_return_1=[!getSubStringWithEnd_return_1!] 
            set getSubStringWithPreEndTag_return_1=!getSubStringWithEnd_return_1!
	  ) 
)
set getTimeNona_return_1=
call :getTimeNona_func_0x1
echo getSubStringWithPreEndTag_return_1=[%getSubStringWithPreEndTag_return_1%]  param1=[%1]  param2=[%2]   param3=[%3]  time=[%getTimeNona_return_1%]
echo [getsubstringwithpreendtag_func_3x1 EndPrintCode]   getsubstringwithpreendtag_return_1=[!getsubstringwithpreendtag_return_1!]   param1=[%1]   param2=[%2]   param3=[%3]   
echo ______________Method_Out getsubstringwithpreendtag_func_3x1
::ENDLOCAL
goto:eof




:iscontainstring_func_2x1
rem ======================================== iscontainstring_func_2x1
rem desc: 检测 字符串参数一 是否包含字符串参数二  忽略两边引号 包含返回 true  不包含返回 false
rem sample: call  :iscontainstring_func_2x1  1234 12
rem sample: call  :iscontainstring_func_2x1  1234 234
rem sample: call  :iscontainstring_func_2x1  "1234" "123"
rem sample: call  :iscontainstring_func_2x1   123  321
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=[1234]   param2=[12]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=[1234]   param2=[234]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=["1234"]   param2=["123"]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[false]   param1=[123]   param2=[321]
::SETLOCAL
echo ______________Method_In iscontainstring_func_2x1
echo  isContainString_func_2x1 input_1_param ==[%1]
echo  isContainString_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set isContainString_str=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set isContainString_matchStr=%clearStringPadding_return_1%
set isContainString_return_1=false
if not "x!isContainString_str:%isContainString_matchStr%=!"=="x%isContainString_str%" (
set isContainString_return_1=true
) else (
set isContainString_return_1=false
)
echo isContainString_return_1=[%isContainString_return_1%] param1=[%1]  param2=[%2] 
echo [iscontainstring_func_2x1 EndPrintCode]   iscontainstring_return_1=[!iscontainstring_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out iscontainstring_func_2x1
::ENDLOCAL
goto:eof




:isstartwith_func_2x1
rem ======================================== isstartwith_func_2x1
rem desc: 检测当前第一字符串 是否是以第二字符串为起始  忽略两边引号  如果为起始返回true  不为起始返回false
rem sample: call  :isstartwith_func_2x1  1234 12
rem sample: call  :isstartwith_func_2x1  1234 234
rem sample: call  :isstartwith_func_2x1  "1234" "123"
rem sample: call  :isstartwith_func_2x1  123  456
rem sample_out: [isstartwith_func_2x1 ]   isstartwith_return_1=[true]   param1=[1234]   param2=[12]
rem sample_out: [isstartwith_func_2x1 ]   isstartwith_return_1=[false]   param1=[1234]   param2=[234]
rem sample_out: [isstartwith_func_2x1 ]   isstartwith_return_1=[true]   param1=["1234"]   param2=["123"]
rem sample_out: [isstartwith_func_2x1 ]   isstartwith_return_1=[false]   param1=[123]   param2=[456]
::SETLOCAL
echo ______________Method_In isstartwith_func_2x1
call :clearStringPadding_func_1x1  %1
set isStartString_Oristr=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set isStartString_MatchStr=%clearStringPadding_return_1%
call :getStringLength_func_1x1 %1
set  Oristr_Length=%getStringLength_return_1%
call :getStringLength_func_1x1 %2
set  MatchStr_Length=%getStringLength_return_1%
set Sub_isStartString_Oristr=!isStartString_Oristr:~0,%MatchStr_Length%!
if "%Sub_isStartString_Oristr%"=="%isStartString_MatchStr%" (
set  isstartwith_return_1=true
) else (
set isstartwith_return_1=false
)
echo [isstartwith_func_2x1 EndPrintCode]   isstartwith_return_1=[!isstartwith_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out isstartwith_func_2x1
::ENDLOCAL
goto:eof




:getstringlength_func_1x1
rem ======================================== getstringlength_func_1x1
rem desc: 获取字符串长度 长度不包括两边的引号"
rem sample: call  :getstringlength_func_1x1  ""12345"
rem sample: call  :getstringlength_func_1x1  ""123456"""
rem sample: call  :getstringlength_func_1x1  ""123""456"""
rem sample: call  :getstringlength_func_1x1   123""456
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[123""456]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[6]   param1=[""123456"""]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[""123""456"""]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[123""456]
::SETLOCAL
echo ______________Method_In getstringlength_func_1x1
echo  getStringLength_func_1x1 input_1_param ==[%1]
set /a getStringLength_numsize = 0
call :clearStringPadding_func_1x1 %1
set getStringLength_str=%clearStringPadding_return_1%
:getStringLength_length_block
if not "!getStringLength_str!"=="" (
 set /a getStringLength_numsize+=1
 set "getStringLength_str=!getStringLength_str:~1!"
 goto getStringLength_length_block
)
set /a getStringLength_return_1=%getStringLength_numsize%
echo getStringLength_return_1=[!getStringLength_return_1!]  param1=[%1] 
echo [getstringlength_func_1x1 EndPrintCode]   getstringlength_return_1=[!getstringlength_return_1!]   param1=[%1]   
echo ______________Method_Out getstringlength_func_1x1
::ENDLOCAL
goto:eof




:clearstringpadding_func_1x1
rem ======================================== clearstringpadding_func_1x1
rem desc: 对当前给的字符串去掉两边的引号
rem sample: call :clearstringpadding_func_1x1 "123"
rem sample: call :clearstringpadding_func_1x1 """""AAABBB""
rem sample: call :clearstringpadding_func_1x1 "1"2"3"
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[123]   param1=["123"]
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[AAABBB]   param1=["""""AAABBB""]
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[1"2"3]   param1=["1"2"3"]
::SETLOCAL
echo ______________Method_In clearstringpadding_func_1x1
rem set number_str_0_4=!number_str:~0,4!   第一个字符
echo  clearStringPadding_func_1x1 input_1_param ==[%1]
set clearStringPadding_tempstr=%1
set clearStringPadding_index_pre=0
set clearStringPadding_index_end=-1
:clearStringPadding_prefix_Block
rem 没检测到前面的字符是 引号 那么加 step 加1
set char_pre_1=!clearStringPadding_tempstr:~%clearStringPadding_index_pre%,1!
echo  clearStringPadding_index_pre=[%clearStringPadding_index_pre%]   char_pre_1=[!char_pre_1!]  
if [^!char_pre_1!]==[^"] (
echo AA_clearStringPadding_index_pre=[%clearStringPadding_index_pre%]
set /a clearStringPadding_index_pre+=1
goto clearStringPadding_prefix_Block
)
echo clearStringPadding_index_pre=[%clearStringPadding_index_pre%]
set clearStringPadding_prestr=!clearStringPadding_tempstr:~%clearStringPadding_index_pre%!
echo clearStringPadding_prestr=[%clearStringPadding_prestr%]
:clearStringPadding_endfix_Block
rem 没检测到前面的字符是 引号 那么加 step 加1
set char_end_1=!clearStringPadding_prestr:~%clearStringPadding_index_end%,1!
echo char_end_1=[%char_end_1%]
if [^!char_end_1!]==[^"] (
set /a clearStringPadding_index_end-=1
goto clearStringPadding_endfix_Block
)
echo clearStringPadding_index_end=[%clearStringPadding_index_end%]
set /a clearStringPadding_index_end+=1
echo clearStringPadding_index_end=[%clearStringPadding_index_end%]
if !clearStringPadding_index_end! EQU 0 ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr!) else ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr:~-0,%clearStringPadding_index_end%!)
echo clearStringPadding_pre_end_str=[%clearStringPadding_pre_end_str%]
set clearStringPadding_return_1=%clearStringPadding_pre_end_str%
echo clearStringPadding_return_1=[%clearStringPadding_pre_end_str%]  param1=[%1] 
echo [clearstringpadding_func_1x1 EndPrintCode]   clearstringpadding_return_1=[!clearstringpadding_return_1!]   param1=[%1]   
echo ______________Method_Out clearstringpadding_func_1x1
::ENDLOCAL
goto:eof




:getint4substringindexof_func_2x1
rem ======================================== getint4substringindexof_func_2x1
rem desc: 接受两个字符串参数 给出第二个参数字符串在第一个参数字符串中的起始位置 不存在返回-1
rem sample: call :getint4substringindexof_func_2x1  1234567890  0987
rem sample: call :getint4substringindexof_func_2x1  "1234567890"  "456"
rem sample_out: getint4substringindexof_return_1=[-1]  param1=[1234567890]  param2=[0987]
rem sample_out: getint4substringindexof_return_1=[4]  param1=["1234567890"]  param2=["456"]
::SETLOCAL
echo ______________Method_In getint4substringindexof_func_2x1
echo  getInt4SubStringIndexOf_func_2x1 input_1_param ==[%1]
echo  getInt4SubStringIndexOf_func_2x1 input_2_param ==[%2]
set getInt4SubStringIndexOf_return_1=-1
set isContainString_return_1=false
call :isContainString_func_2x1 %1  %2
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getInt4SubStringIndexOf_func_2x1  Str1_Param do not contain Str2_Param "
echo [getint4substringindexof_func_2x1 EndPrintCode]   getint4substringindexof_return_1=[!getint4substringindexof_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getint4substringindexof_func_2x1
GOTO:EOF
)
rem  从起点开始截取 整段 整段 的 进行对比
call :clearStringPadding_func_1x1 %1
set origin_str_param1=!clearStringPadding_return_1!
echo ZZZZZorigin_str_param1=%origin_str_param1%   clearStringPadding_return_1=[%clearStringPadding_return_1%]  input1=[%1]  input2=[%2]
call :clearStringPadding_func_1x1 %2
set match_str_param2=!clearStringPadding_return_1!
echo  AAAAmatch_str_param2=[!clearStringPadding_return_1!]     input1=[%1]  input2=[%2]
rem set match_str_param2=%2
rem set origin_str_param1=%1
set getStringLength_return_1=
call :getStringLength_func_1x1  "%origin_str_param1%"
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1   "%match_str_param2%"
set /a str2_length=getStringLength_return_1
echo str1_length=[%str1_length%]   str2_length=[%str2_length%]   input1_nopadding=[%origin_str_param1%]  input2_nopadding=[%match_str_param2%]
set /a getInt4SubStringIndexOf_step_index=0
:getInt4SubStringIndexOf_getNextStep_block:
set step_temp_str=!origin_str_param1:~0,%str2_length%!
if not "!origin_str_param1!"=="" (
set /a getInt4SubStringIndexOf_step_index+=1
echo step_temp_str=[%step_temp_str%]   str2_length=[%str2_length%]  getInt4SubStringIndexOf_step_index=[%getInt4SubStringIndexOf_step_index%] match_str_param2=[%match_str_param2%]
if "!step_temp_str!"=="%match_str_param2%" goto getInt4SubStringIndexOf_getIndexResult_block
set "origin_str_param1=!origin_str_param1:~1!"
goto getInt4SubStringIndexOf_getNextStep_block
)
:getInt4SubStringIndexOf_getIndexResult_block:
echo getInt4SubStringIndexOf_step_index=[%getInt4SubStringIndexOf_step_index%] ori_str[!origin_str_param1!]  match_str[%match_str_param2%]
echo getInt4SubStringIndexOf_return_1=[getInt4SubStringIndexOf_return_1]
set getInt4SubStringIndexOf_return_1=%getInt4SubStringIndexOf_step_index%
echo [getint4substringindexof_func_2x1 EndPrintCode]   getint4substringindexof_return_1=[!getint4substringindexof_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getint4substringindexof_func_2x1
::ENDLOCAL
goto:eof




rem ======================== STRING_OPERATION End======================== 
rem ======================== TEST_OPERATION Begin======================== 


:test_func_0x0
rem ======================================== test_func_0x0
rem desc: 方法测试程序
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In test_func_0x0
echo ==================== for_1 no_param ====================  
rem [1]无参：遍历当前路径的文件夹下的文件，但也可在(匹配符)中指定路径  查看 C:\Users\zhuxx 下 所有实体文件   
rem i == C:\Users\xxx\.bash_history
rem i == C:\Users\xxx\.gitconfig
rem i == C:\Users\xxx\164.jpg
rem i == C:\Users\xxx\Tip.txt
rem  进行运算后 自增一  必须使用 !! 括号括起来 
set /a Index_NoParam = 0
for  %%i in (%zbin%) do (
set /a Index_NoParam+=1
echo no_param_index[!Index_NoParam!] == %%i
)
echo ==================== for_2 /d ====================
rem d_index[1] == C:\Users\xxx\.android
rem d_index[2] == C:\Users\xxx\.config
rem d_index[3] == C:\Users\xxx\.dotnet
rem d_index[4] == C:\Users\xxx\.eclipse
set /a Index_D = 0
for /d %%i in (%zbin%) do (
set /a Index_D+=1
echo d_index[!Index_D!] == %%i
)
echo ==================== for_3 /r ====================
rem  /r [路径]：深度遍历指定路径下的所有文件，子目录中的文件也会被遍历到，如果没指定路径，默认当前路径  

rem 包含所有实体文件  不包含文件夹
rem r_index[33601] == C:\Users\zhuxx\AppData\Local\FileZilla\
rem r_index[33602] == C:\Users\zhuxx\AppData\Local\FileZilla\\default_cancel30x30.png
rem r_index[33603] == C:\Users\zhuxx\AppData\Local\FileZilla\\default_cancel36x36.png
rem r_index[15638] ======  C:\Users\xxx\Videos\Captures\C:\Users\xxx\Desktop\zbin\  (((for /r 遍历 出现两个地址)))
set /a Index_R = 0
for /r %zbin% %%j in  (*)do (
set /a Index_R+=1 
echo r_index[!Index_R!] =  %%j
)
echo ==================== for_3 /r /d ====================
rem 只循环遍历文件夹
set /a Index_R_D = 0
for /d  /r %zbin% %%j in  (*)do (
set /a Index_R_D+=1 
echo r_d_index[!Index_R_D!] =  %%j
)
echo ==================== for_3 /r .,* ====================
rem 遍历所有文件夹 以及 文件   文件夹 \.为结尾
rem r_all_index[2838] =  C:\Users\xxx\Desktop\zbin\J1_Plugin\video_cat_down\css\.
set /a Index_R_All = 0
for /r %zbin% %%j in  (.,*)do (
set /a Index_R_All+=1 
echo r_all_index[!Index_R_All!] =  %%j
)
echo ==================== for_4 /l ====================
rem for /l  循环  等效  for (int i = 1; i <= 5; i++)
rem i == 1 , i == 2 , i == 3 , i == 4 , i == 5 , 
for /l %%i in (1, 1, 5) do (
echo i == %%i             
)
rem for /f  用于解析文件中的内容  读取文件的每一行  空格之前的内容 
rem Index_F_1[1] == @echo          
rem Index_F_1[2] == @cd          
rem Index_F_1[3] == @javac          
rem Index_F_1[4] == @java          
rem Index_F_1[5] == @exit 
set /a Index_F_1 = 0
for /f %%i in (%zbin%\A0.bat) do (
set /a Index_F_1+=1
echo Index_F_1[!Index_F_1!] == %%i          
)
set str1_temp="package:/data/app/~~iTJ7qQFCuiitsOv-NxYfsQ==/com.android.gpstest-8aetQzpfCBbjIcHfnhH_kA==/base.apk=com.android.gpstest"
echo ___________________________________ stringTest_func_0x0  
rem 测试字符串函数  
call :stringTest_func_0x0
echo ___________________________________ searchLastFile_func_1x1
rem  searchLastFile_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin)))
rem  searchLastFile_return_1=(((K3_MD_Rule.class )))
rem  给一个目录进去  得到该目录下最新修改创建的文件名  不是全路径
set  searchLastFile_return_1=""
call :searchLastFile_func_1x1 %zbin%
echo searchLastFile_return_1=%searchLastFile_return_1%
echo ___________________________________ searchLastFullPath_func_1x1
rem  searchLastFile_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin)))
rem searchLastFullPath_return_1=(((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem  给一个目录进去  得到该目录下最新修改创建的文件全路径
set  searchLastFullPath_return_1=""
call :searchLastFullPath_func_1x1 %zbin%
echo searchLastFullPath_return_1=%searchLastFullPath_return_1% 
echo ___________________________________ showfile_func_1x0
rem  显示该文件的File 信息
call :showfile_func_1x0 %searchLastFullPath_return_1%
echo ___________________________________ getFileNameWithFullPath_func_1x1
rem getFileNameWithFullPath_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem getFileNameWithFullPath_return_1=(((K3_MD_Rule.class)))
rem  给一个文件的全路径返回该文件的 本身的文件名 去掉全路径
set  getFileNameWithFullPath_return_1=""
call :getFileNameWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileNameWithFullPath_return_1=%getFileNameWithFullPath_return_1%
echo ___________________________________ getFileTypeWithFullPath_func_1x1
rem getFileTypeWithFullPath_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem getFileTypeWithFullPath_return_1=(((.class)))
rem  给一个文件的全路径返回该文件的 本身的文件的类型
set  getFileTypeWithFullPath_return_1=""
call :getFileTypeWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileTypeWithFullPath_return_1=%getFileTypeWithFullPath_return_1%
echo ___________________________________ getParentFilePathWithFullPath_func_1x1
rem getParentFilePathWithFullPath_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem getParentFilePathWithFullPath_return_1=(((\Users\xxx\Desktop\zbin\)))
rem  给一个文件的全路径返回该文件的 父文件夹的 全路径 
set  getParentFilePathWithFullPath_return_1=""
call :getParentFilePathWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getParentFilePathWithFullPath_return_1=%getParentFilePathWithFullPath_return_1%
echo ___________________________________ getFileNameNoPointWithFullPath_func_1x1
rem getFileNameNoPointWithFullPath_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem getFileNameNoPointWithFullPath_return_1=(((K3_MD_Rule)))
rem  给一个文件的全路径返回该文件的 去掉类型的 文件名字
set  getFileNameNoPointWithFullPath_return_1=""
call :getFileNameNoPointWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileNameNoPointWithFullPath_return_1=%getFileNameNoPointWithFullPath_return_1% 
echo ___________________________________ getFileNameNoPointWithFullPath_func_1x1
rem getFileSizeByteWithFullPath_func_1x1 input_1_param == (((C:\Users\xxx\Desktop\zbin\K3_MD_Rule.class)))
rem getFileSizeByteWithFullPath_return_1=(((28529)))
rem  给一个文件的全路径返回该文件的  返回该文件的字节Byte大小
set  getFileSizeByteWithFullPath_return_1=""
call :getFileSizeByteWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileSizeByteWithFullPath_return_1=%getFileSizeByteWithFullPath_return_1% 
echo ___________________________________ showSubFile4Dir4Type_func_2x0
rem showSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showSubFile4Dir4Type_func_2x0 input_2_param == *.bat
rem showSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showSubFile4Dir4Type_func_2x0 input_2_param == *
rem 给一个要搜寻的路径 以及路径上过滤的字符串 找出 符合条件的 实体文件  不包含文件夹
call :showSubFile4Dir4Type_func_2x0 %zbin%  *.bat
call :showSubFile4Dir4Type_func_2x0 %zbin%  *
echo ___________________________________ showSubDir4Dir4Type_func_2x0
rem showSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showSubDir4Dir4Type_func_2x0 input_2_param == win
rem showSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showSubDir4Dir4Type_func_2x0 input_2_param == *
rem 搜索指定目录下的子目录  不包含文件  已经文件夹对应的名字符合条件的文件夹
call :showSubDir4Dir4Type_func_2x0 %zbin%  win
call :showSubDir4Dir4Type_func_2x0 %zbin%  *
echo ___________________________________ showSubFileAndDir_func_1x0
rem showSubFileAndDir_func_1x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem 遍历指定文件夹下的所有 子文件 和 子目录 
call :showSubFileAndDir_func_1x0 %zbin%
echo ___________________________________ showAllSubFile4Dir4Type_func_2x0
rem showAllSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showAllSubFile4Dir4Type_func_2x0 input_2_param == *.bat
rem showAllSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showAllSubFile4Dir4Type_func_2x0 input_2_param == *
rem 给一个要搜寻的路径 以及路径上过滤的字符串 找出 符合条件的 所有的孙文件 子文件 循环遍历 实体文件  
call :showAllSubFile4Dir4Type_func_2x0 %zbin%  win
call :showAllSubFile4Dir4Type_func_2x0 %zbin%  *
echo ___________________________________ showAllSubDir4Dir4Type_func_2x0
rem showAllSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showAllSubDir4Dir4Type_func_2x0 input_2_param == win
rem showAllSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem showAllSubDir4Dir4Type_func_2x0 input_2_param == *
rem 搜索指定目录下的所有的子目录 孙目录  不包含文件   并且过滤文件名称
call :showAllSubDir4Dir4Type_func_2x0 %zbin%  win
call :showAllSubDir4Dir4Type_func_2x0 %zbin%  *
echo ___________________________________ showAllSubFileAndDir_func_1x0
rem showAllSubFileAndDir_func_1x0 input_1_param == C:\Users\xxx\Desktop\zbin
rem 遍历指定文件夹下的所有 子文件 和 子目录  孙文件 孙目录 循环 
call :showAllSubFileAndDir_func_1x0  %zbin%
echo ___________________________________ getTimeStamp_func_0x1
rem 返回yyyyMMdd_HHmmss 20210507_161904 这样的时间戳字符串
set getTimeStamp_return_1=""
call :getTimeStamp_func_0x1
echo getTimeStamp_return_1=%getTimeStamp_return_1%
echo ___________________________________ recordFileNameToFile_func_1x1
rem  recordFileNameToFile_func_1x1  input_1_param == C:\Users\xxx\Desktop\zbin
rem  recordFileNameToFile_return_1=C:\Users\xxx\Desktop\zbin\Z_temp.txt [  A1.txt   WIFI.txt ] 
rem  把当前指定目录的文件都保存在一个文件中  
set recordFileNameToFile_return_1=""
call :recordFileNameToFile_func_1x1  %zbin%
echo recordFileNameToFile_return_1=%recordFileNameToFile_return_1%
echo ___________________________________ openFileWithNotepad_func_1x0
rem 使用 notepad++ 打开文件  
rem call :openFileWithNotepad_func_1x0 %recordFileNameToFile_return_1%
echo ___________________________________ explorerWithPath_func_1x0
rem  explorerWithPath_func_1x0  打开一个目录  
rem call :explorerWithPath_func_1x0 %zbin%
echo ___________________________________ searchOneTargetFile4Dir4Type_func_2x1
rem 搜索指定目录下搜索到的唯一一个符合过滤条件的文件 返回这个文件的全路径
set searchOneTargetFile4Dir4Type_return_1=""
call :searchOneTargetFile4Dir4Type_func_2x1  %zbin%  JetbrainsCrack-4.2.jar
echo searchOneTargetFile4Dir4Type_return_1=%searchOneTargetFile4Dir4Type_return_1%
echo ___________________________________ isFileExist_func_1x1
rem 给定一个目录全路径 判断当前目录是否存在 
set isFileExist_return_1=""
call :isFileExist_func_1x1  %zbin% 
echo isFileExist_return_1=%isFileExist_return_1% ---for---  %zbin% 
call :isFileExist_func_1x1  %searchOneTargetFile4Dir4Type_return_1% 
echo isFileExist_return_1=%isFileExist_return_1%   ---for---  %searchOneTargetFile4Dir4Type_return_1% 
call :isFileExist_func_1x1  %zbin%/ABC.txt
echo isFileExist_return_1=%isFileExist_return_1%  ---for---  %zbin%\ABC.txt
echo ___________________________________ readFile_func_1x0
rem 提供一个文件全路径 然后打印它的每一行
call :readFile_func_1x0  %recordFileNameToFile_return_1%
echo ___________________________________ getStringLength_func_1x1
rem 检测字符串的长度 
set getStringLength_return_1=
call :getStringLength_func_1x1  1234567890
echo getStringLength_return_1=%getStringLength_return_1%
call :getStringLength_func_1x1  "1 2 3 4 5 6 7 8 9 0"
echo getStringLength_return_1=%getStringLength_return_1%
rem echo ___________________________________ getSubStringWithPre_func_2x1
set getSubStringWithPre_return_1=
rem call :getSubStringWithPre_func_2x1  1234567890  4567
rem echo getSubStringWithPre_return_1=%getSubStringWithPre_return_1%
echo ___________________________________ isContainString_func_2x1
set isContainString_return_1=
call :isContainString_func_2x1  1234567890  222
echo isContainString_return_1=%isContainString_return_1%
call :isContainString_func_2x1  1234567890  890
echo isContainString_return_1=%isContainString_return_1%
call :isContainString_func_2x1  1234567890  1234
echo isContainString_return_1=%isContainString_return_1%
call :isContainString_func_2x1  1234567890  0987
echo isContainString_return_1=%isContainString_return_1%
echo ___________________________________ getInt4SubStringIndexOf_func_2x1
rem  检测 匹配字符串在 原始字符串中的 起始位置 
set getInt4SubStringIndexOf_return_1=
call :getInt4SubStringIndexOf_func_2x1 1234567890  890
echo getInt4SubStringIndexOf_return_1=%getInt4SubStringIndexOf_return_1%
call :getInt4SubStringIndexOf_func_2x1 1234567890  0987
echo getInt4SubStringIndexOf_return_1=%getInt4SubStringIndexOf_return_1%
echo ___________________________________ getSubStringWithPre_func_2x1
set getSubStringWithPre_return_1=
call :getSubStringWithPre_func_2x1 1234567890  456
echo getSubStringWithPre_return_1=%getSubStringWithPre_return_1%
set str1_temp="package:/data/app/~~iTJ7qQFCuiitsOv-NxYfsQ==/com.android.gpstest-8aetQzpfCBbjIcHfnhH_kA==/base.apk=com.android.gpstest"
call :getSubStringWithPre_func_2x1 %str1_temp%  package:
echo getSubStringWithPre_return_1=%getSubStringWithPre_return_1%
call :getSubStringWithPre_func_2x1 %str1_temp%  apk=
echo getSubStringWithPre_return_1=%getSubStringWithPre_return_1%
echo ___________________________________ getSubStringWithEnd_func_2x1
set getSubStringWithEnd_return_1=
call :getSubStringWithEnd_func_2x1 1234567890  456
echo getSubStringWithEnd_return_1=%getSubStringWithEnd_return_1%
echo ___________________________________ clearStringPadding_func_1x1
rem 测试去掉双引号的操作  
set clearStringPadding_return_1=
call :clearStringPadding_func_1x1 "ABC""   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  ["ABC""]
call :clearStringPadding_func_1x1 ""ABC""   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  [""ABC""]
call :clearStringPadding_func_1x1 "ABC"CDE"   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  ["ABC"CDE"]
call :clearStringPadding_func_1x1 "ABC"CDE"1234"""""   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  ["ABC"CDE"1234"""""]
call :clearStringPadding_func_1x1 "ABC"CDE"1234   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  ["ABC"CDE"1234]
call :clearStringPadding_func_1x1 """ABC"CDE"1234   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  ["""ABC"CDE"1234]
call :clearStringPadding_func_1x1 ABC"CDE"1234"""   
echo clearStringPadding_return_1=[%clearStringPadding_return_1%]  [ABC"CDE"1234"""]
echo ___________________________________ getSubStringWithPreEndTag_func_3x1
set getSubStringWithPreEndTag_return_1=
rem call :getSubStringWithPreEndTag_func_3x1 1234567890  456 90
rem echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%
call :getSubStringWithPreEndTag_func_3x1 %str1_temp%  "package:"    "apk="
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%
call :getSubStringWithPreEndTag_func_3x1 %str1_temp%  "package:"    "==/base"
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%
call :getSubStringWithPreEndTag_func_3x1 %str1_temp%     ""   "==/base"
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%
call :getSubStringWithPreEndTag_func_3x1 %str1_temp%       "==/base"   ""
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%
echo [test_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out test_func_0x0
::ENDLOCAL
goto:eof




:helloworld_func_0x0
rem ======================================== helloworld_func_0x0
rem desc: helloworld测试程序
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In helloworld_func_0x0
echo hello_world zukgit
echo [helloworld_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out helloworld_func_0x0
::ENDLOCAL
goto:eof




:showlove_func_0x0
rem ======================================== showlove_func_0x0
rem desc: showlove测试程序
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In showlove_func_0x0
mode con cols=75 lines=28
echo.
echo                    *********               *********
ping -n 1 127.0.0.1>nul
echo                ****************           *****************
ping -n 1 127.0.0.1>nul
echo           **********************       ************************
ping -n 1 127.0.0.1>nul
echo        ***********************************************************
ping -n 1 127.0.0.1>nul
echo      ***************************************************************
ping -n 1 127.0.0.1>nul
echo     *****************************************************************
ping -n 1 127.0.0.1>nul
echo    -------------------------------------------------------------------
ping -n 1 127.0.0.1>nul
echo                                亲爱的XXX
ping -n 1 127.0.0.1>nul
echo     -----------------------------------------------------------------
ping -n 1 127.0.0.1>nul
echo      ******** 我 ***************************************************
ping -n 1 127.0.0.1>nul
echo        *************** 爱 ****************************************
ping -n 1 127.0.0.1>nul
echo          ********************* 你 ******************************
ping -n 1 127.0.0.1>nul
echo            *****************，********************************
ping -n 1 127.0.0.1>nul
echo              ***********************************************
ping -n 1 127.0.0.1>nul
echo                ********* 一 ******************************
ping -n 1 127.0.0.1>nul
echo                  ********** 生 *************************
ping -n 1 127.0.0.1>nul
echo                    *********** 一 ********************
ping -n 1 127.0.0.1>nul
echo                      ************ 世 ***************
ping -n 1 127.0.0.1>nul
echo                        **************！***********
ping -n 1 127.0.0.1>nul
echo                          ***********************
ping -n 1 127.0.0.1>nul
echo                            *******************
ping -n 1 127.0.0.1>nul
echo                              ***************
ping -n 1 127.0.0.1>nul
echo                                ***********
ping -n 1 127.0.0.1>nul
echo                                  *******
ping -n 1 127.0.0.1>nul
echo                                    ***
ping -n 1 127.0.0.1>nul
echo                                     * 
ping -n 1 127.0.0.1>nul
for /l %%i in (1, 1, 1000) do (
set /a dj=!random!%%9
color !dj!f
ping -n 1.7 127.0.0.1>nul
)
echo [showlove_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out showlove_func_0x0
::ENDLOCAL
goto:eof




rem ======================== TEST_OPERATION End======================== 
rem ======================== BUSSINESS_OPERATION Begin======================== 


:rule0vmethodtemplate_func_0x0
rem ======================================== rule0vmethodtemplate_func_0x0
rem rule_tip: %init_input_0% _0_   ## 打印当前 rule规则的method模板
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule0vmethodtemplate_func_0x0
echo ======================================================================== Rule_Method_模板 Begin ========================================================================
echo :rule0vmethodtemplate_func_0x0
echo rem ======================================== rule0vmethodtemplate_func_0x0
echo rem desc:
echo rem sample_out:
echo ::SETLOCAL
echo ::ENDLOCAL
echo goto:eof
echo ======================================================================== Rule_Method_模板 End ========================================================================
echo [rule0vmethodtemplate_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule0vmethodtemplate_func_0x0
::ENDLOCAL
goto:eof




:rule1vbankupapk_func_0x0
rem ======================================== rule1vbankupapk_func_0x0
rem rule_tip: %init_input_0% _1_   ## 备份bankup所有当前手机安装的三方 apk本地PC本地目录
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule1vbankupapk_func_0x0
echo  bankup当前目录apk
set rule1_num=1
adb shell pm list packages -3 -f > rule1.txt
for /f %%l in (rule1.txt) do (
set /a rule1_num+=1 
echo third_app[!rule1_num!]=%%l
call :stringspecialchange_func_1x1 "%%l"
rem 对读取到的内容 进行 =等号替换
set local_stringspecialchange_return_1=!stringspecialchange_return_1!
echo third_app[!rule1_num!]=%%l
echo local_stringspecialchange_return_1=[!local_stringspecialchange_return_1!]
call :getsubstringwithpreendtag_func_3x1 !local_stringspecialchange_return_1! "package:"  ".apk"
echo getsubstringwithpreendtag_return_1=[%getsubstringwithpreendtag_return_1%]
echo getsubstringwithpreendtag_return_2=[!getsubstringwithpreendtag_return_1!]
call :stringspecialrecovery_func_1x1  !getsubstringwithpreendtag_return_1!
set local_fixed_stringspecialrecovery_return_1=!stringspecialrecovery_return_1!
set third_app_path=!local_fixed_stringspecialrecovery_return_1!.apk
echo third_app_path=[!third_app_path!]
call :getsubstringwithpreendtag_func_3x1 !local_stringspecialchange_return_1! ".apk"  ""
echo getsubstringwithpreendtag_return_1_name=[%getsubstringwithpreendtag_return_1%]
echo getsubstringwithpreendtag_return_2_name=[!getsubstringwithpreendtag_return_1!]
call :stringspecialrecovery_func_1x1  !getsubstringwithpreendtag_return_1!
set local_fixed_stringspecialrecovery_return_1_name=!stringspecialrecovery_return_1!
echo local_fixed_stringspecialrecovery_return_1_name=[!local_fixed_stringspecialrecovery_return_1_name!]
set no_blank_name=!local_fixed_stringspecialrecovery_return_1_name:~1!
echo  third_app_path=[!third_app_path!]   no_blank_name=[!no_blank_name!]  
echo 拉取apk命令如下:
echo app[!rule1_num!]  adb pull !third_app_path!   !no_blank_name!.apk
adb pull !third_app_path!   !no_blank_name!.apk
)
rem del rule1.txt
echo [rule1vbankupapk_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule1vbankupapk_func_0x0
::ENDLOCAL
goto:eof




:rule2vgetapk_func_0x0
rem ======================================== rule2vgetapk_func_0x0
rem rule_tip: %init_input_0% _2_   ## 获取手机当前正在运行的APK_必须是三方的APK 到本地
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule2vgetapk_func_0x0
:rule2vgetapk_getapp_block
rem del rule2.txt
rem del rule2_3app.txt
echo  获取当前正在运行的APK 
rem adb shell dumpsys window | findstr "mCurrentFocus=Window"
rem mCurrentFocus=Window{52b526e u0 com.autonavi.minimap/com.autonavi.map.activity.NewMapActivity}
adb shell dumpsys window | findstr "mCurrentFocus=Window" > rule2.txt 
set isscreenon=false
set /a Index_F_1 = 0
for  /f "delims=" %%l in (rule2.txt) do (
set /a Index_F_1+=1
echo Index_F_1[!Index_F_1!] == %%l          
rem   亮屏 Index_F_1[1] ==   mCurrentFocus=Window{52b526e u0 com.autonavi.minimap/com.autonavi.map.activity.NewMapActivity}    

rem   灭屏 mCurrentFocus=Window{9cfe782 u0 AOD}

rem     mCurrentFocus=Window{87b6a8d u0 com.android.launcher3/com.android.launcher3} 主页  

rem  使用 /  来判断当前是否是灭屏状态 
set current_app_name=%%l
echo current_app_name=!current_app_name!
call :isContainString_func_2x1 "!current_app_name!"  /
set isscreenon=!isContainString_return_1!
echo local_isscreenon=[!isscreenon!]
if "!local_isscreenon!"=="false" (
echo 当前的APP没有过滤到正在运行的APP 可能屏幕为黑屏,清重新测算
echo [rule2vgetapk_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule2vgetapk_func_0x0
goto:eof
)
call :stringspecialchange_func_1x1 "!current_app_name!"
rem 对读取到的内容 进行 =等号替换
set current_app_name_specialchange=!stringspecialchange_return_1!
echo current_app_name_specialchange=[!current_app_name_specialchange!]
call :getsubstringwithpreendtag_func_3x1 !current_app_name_specialchange!  "u0"  "/"
echo getsubstringwithpreendtag_return_1=[%getsubstringwithpreendtag_return_1%]
echo getsubstringwithpreendtag_return_2=[!getsubstringwithpreendtag_return_1!]
adb shell pm list packages -3 -f | findstr !getsubstringwithpreendtag_return_1! > rule2_3app.txt
rem  package:/data/app/~~KLeJf_gsGhiZkGf37dk1hQ==/com.autonavi.minimap-2lHoHfzlz0XYX3ZcVJ-G2A==/base.apk=com.autonavi.minimap
for /f %%a in (rule2_3app.txt) do (
set /a rule1_num+=1 
echo third_app[!rule1_num!]=%%a
call :stringspecialchange_func_1x1 "%%a"
rem 对读取到的内容 进行 =等号替换
set local_stringspecialchange_return_1=!stringspecialchange_return_1!
echo third_app[!rule1_num!]=%%a
echo local_stringspecialchange_return_1=[!local_stringspecialchange_return_1!]
call :getsubstringwithpreendtag_func_3x1 !local_stringspecialchange_return_1! "package:"  ".apk"
echo getsubstringwithpreendtag_return_1=[%getsubstringwithpreendtag_return_1%]
echo getsubstringwithpreendtag_return_2=[!getsubstringwithpreendtag_return_1!]
call :stringspecialrecovery_func_1x1  !getsubstringwithpreendtag_return_1!
set local_fixed_stringspecialrecovery_return_1=!stringspecialrecovery_return_1!
set third_app_path=!local_fixed_stringspecialrecovery_return_1!.apk
echo third_app_path=[!third_app_path!]
call :getsubstringwithpreendtag_func_3x1 !local_stringspecialchange_return_1! ".apk"  ""
echo getsubstringwithpreendtag_return_1_name=[%getsubstringwithpreendtag_return_1%]
echo getsubstringwithpreendtag_return_2_name=[!getsubstringwithpreendtag_return_1!]
call :stringspecialrecovery_func_1x1  !getsubstringwithpreendtag_return_1!
set local_fixed_stringspecialrecovery_return_1_name=!stringspecialrecovery_return_1!
echo local_fixed_stringspecialrecovery_return_1_name=[!local_fixed_stringspecialrecovery_return_1_name!]
set no_blank_name=!local_fixed_stringspecialrecovery_return_1_name:~1!
echo  third_app_path=[!third_app_path!]   no_blank_name=[!no_blank_name!]  
echo 拉取apk命令如下:
echo app[!rule1_num!]  adb pull !third_app_path!   !no_blank_name!.apk
adb pull !third_app_path!   !no_blank_name!.apk
)
echo 按住回车继续下一次执行 导出正在运行的app到PC本地:
rem del rule2.txt
rem del rule2_3app.txt
pause
goto rule2vgetapk_getapp_block
)
echo 按住回车继续下一次执行 导出正在运行的app到PC本地:
pause
goto rule2vgetapk_getapp_block
echo [rule2vgetapk_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule2vgetapk_func_0x0
::ENDLOCAL
goto:eof




:rule3vinstalldirapk_func_1x0
rem ======================================== rule3vinstalldirapk_func_1x0
rem rule_tip: %init_input_0% _3_  ##安装当前目录的apk到手机
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule3vinstalldirapk_func_1x0
set rule3vinstalldirapk_dynamic_param1=
if "%init_input_2%"=="" (
set  rule3vinstalldirapk_dynamic_param1=!init_cd!
) else (
set rule3vinstalldirapk_dynamic_param1=!init_cd!%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule3vinstalldirapk_dynamic_param1!]   
set /a Index_NoParam = 0
for  %%i in (!rule3vinstalldirapk_dynamic_param1!\*.apk) do (
set /a Index_NoParam+=1
echo install_index[!Index_NoParam!] == %%i   [ commonad: adb install -r  %%i ]
echo adb install -r  %%i
adb install -r  %%i
)
echo [rule3vinstalldirapk_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule3vinstalldirapk_dynamic_param1!]   
echo ______________Method_Out rule3vinstalldirapk_func_1x0
::ENDLOCAL
goto:eof




:rule4vscreendown_func_1x0
rem ======================================== rule4vscreendown_func_1x0
rem rule_tip: %init_input_0% _4_ 10000   ##手机执行 adb shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem desc: 手机执行 adb shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule4vscreendown_func_1x0
set rule4vscreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule4vscreendown_dynamic_param1=10000
) else (
set rule4vscreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule4vscreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule4vscreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule4vscreendown_dynamic_param1!]     
adb shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule4vscreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule4vscreendown_dynamic_param1!]        
adb shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule4vscreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule4vscreendown_dynamic_param1!]   
echo ______________Method_Out rule4vscreendown_func_1x0
::ENDLOCAL
goto:eof




:rule5vscreen2end_func_1x0
rem ======================================== rule5vscreen2end_func_1x0
rem rule_tip: %init_input_0% _5_  1000000  ##手机执行 adb shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule5vscreen2end_func_1x0
set rule5vscreen2end_dynamic_param1=
if "%init_input_2%"=="" (
set  rule5vscreen2end_dynamic_param1=1000000
)
echo allLoop=[!rule5vscreen2end_dynamic_param1!]        
for /l %%i in (1, 1, !rule5vscreen2end_dynamic_param1!) do (
rem echo i == %%i   _todown        
adb shell input swipe 340 1000 340 200  50
)
echo [rule5vscreen2end_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule5vscreen2end_dynamic_param1!]   
echo ______________Method_Out rule5vscreen2end_func_1x0
::ENDLOCAL
goto:eof




:rule6vclearapk_func_0x0
rem ======================================== rule6vclearapk_func_0x0
rem rule_tip: %init_input_0% _6_  ## 执行清空apk的操作
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule6vclearapk_func_0x0
:rule6_clearapp_loop
ECHO waiting conenct with USB
adb wait-for-device
adb shell pm list packages -3 > rule6.txt
if exist rule6.txt (
  for /f %%l in (rule6.txt) do ( 
       set /a num+=1 
	   set s=%%l
       set str1=!s:~8!
	   ECHO  s=!str1!
       ECHO install-packages :  !str1!
       call adb uninstall !str1!
  )
ECHO all third-part APP uninstall !
) else (
ECHO B7_uninstallpacket.txt file not exist !
)
ECHO 更换一个设备去执行 清空apk的 操作！
del rule6.txt
pause
GOTO rule6_clearapp_loop
echo [rule6vclearapk_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule6vclearapk_func_0x0
::ENDLOCAL
goto:eof




:rule7vbtonoff_func_1x0
rem ======================================== rule7vbtonoff_func_1x0
rem rule_tip: %init_input_0% _7_  ## 蓝牙开启关闭 默认执行1000次
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule7vbtonoff_func_1x0
set rule7vbtonoff_dynamic_param1=
if "%init_input_2%"=="" (
set  rule7vbtonoff_dynamic_param1=1000000
) else (
set rule7vbtonoff_dynamic_param1=%init_input_2% 
)
for /l %%i in (1, 1, !rule7vbtonoff_dynamic_param1!) do (
echo i == %%i   open_bt
adb shell svc bluetooth enable
echo i == %%i   close_bt
adb shell svc bluetooth disable
rem ping -n 1  127.0.0.1 >nul 
)
echo [rule7vbtonoff_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule7vbtonoff_dynamic_param1!]   
echo ______________Method_Out rule7vbtonoff_func_1x0
::ENDLOCAL
goto:eof




:rule8vtapxy_func_3x0
rem ======================================== rule8vtapxy_func_3x0
rem rule_tip: %init_input_0% _8_ 400 800 1000  ## 点击屏幕位置 x[400]  y[800] repeat[1000] 重复 repeat次 默认1000次

rem desc: %init_input_0% _8_ 400 800 1000  点击屏幕位置 x[400]  y[800] repeat[1000] 重复 repeat次 默认1000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule8vtapxy_func_3x0
set rule8vtapxy_dynamic_param1=
set rule8vtapxy_dynamic_param2=
set rule8vtapxy_dynamic_param3=
if "%init_input_2%"=="" (
set  rule8vtapxy_dynamic_param1=400
) else (
set rule8vtapxy_dynamic_param1=%init_input_2% 
)
if "%init_input_3%"=="" (
set  rule8vtapxy_dynamic_param2=800
) else (
set rule8vtapxy_dynamic_param2=%init_input_3% 
)
if "%init_input_4%"=="" (
set  rule8vtapxy_dynamic_param3=1000
) else (
set rule8vtapxy_dynamic_param3=%init_input_4% 
)
echo [rule8vtapxy_func_3x0]   dynamic_param1=[!rule8vtapxy_dynamic_param1!]   dynamic_param2=[!rule8vtapxy_dynamic_param2!]   dynamic_param3=[!rule8vtapxy_dynamic_param3!]   
for /l %%i in (1, 1, !rule8vtapxy_dynamic_param3!) do (
echo adb shell input tap !rule8vtapxy_dynamic_param1!  !rule8vtapxy_dynamic_param2!  index=[%%i]
adb shell input tap !rule8vtapxy_dynamic_param1!  !rule8vtapxy_dynamic_param2!
rem ping  127.0.0.1 >nul 
)
echo [rule8vtapxy_func_3x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule8vtapxy_dynamic_param1!]   dynamic_param2=[!rule8vtapxy_dynamic_param2!]   dynamic_param3=[!rule8vtapxy_dynamic_param3!]   
echo ______________Method_Out rule8vtapxy_func_3x0
::ENDLOCAL
goto:eof




:rule9vwifionoff_func_1x0
rem ======================================== rule9vwifionoff_func_1x0
rem rule_tip: %init_input_0% _9_  ## wifi开启关闭  默认1000次

rem desc: 快速关闭开启 wifi开关 默认执行1000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule9vwifionoff_func_1x0
set rule9vwifionoff_dynamic_param1=
if "%init_input_2%"=="" (
set  rule9vwifionoff_dynamic_param1=1000
) else (
set rule9vwifionoff_dynamic_param1=%init_input_2% 
)
adb shell am start -a android.settings.WIRELESS_SETTINGS 
adb shell am start com.android.settings/com.android.settings.wifi.WifiPickerActivity  
for /l %%i in (1, 1, !rule9vwifionoff_dynamic_param1!) do (
echo i == %%i   open_wifi 
adb shell svc wifi enable
echo i == %%i   close_wifi
adb shell svc wifi disable
rem ping   127.0.0.1 >nul 
)
echo [rule9vwifionoff_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule9vwifionoff_dynamic_param1!]   
echo ______________Method_Out rule9vwifionoff_func_1x0
::ENDLOCAL
goto:eof




:rule10vgpsonoff_func_1x0
rem ======================================== rule10vgpsonoff_func_1x0
rem rule_tip: %init_input_0% _10_  1000  ## GPS开启关闭  默认1000次

rem desc: 快速关闭开启GPS开关 默认执行1000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule10vgpsonoff_func_1x0
set rule10vgpsonoff_dynamic_param1=
if "%init_input_2%"=="" (
set  rule10vgpsonoff_dynamic_param1=1000
) else (
set rule10vgpsonoff_dynamic_param1=%init_input_2% 
)
adb shell am start -a android.settings.LOCATION_SOURCE_SETTINGS
for /l %%i in (1, 1, !rule10vgpsonoff_dynamic_param1!) do (
echo i == %%i   open_gps 
adb root & adb shell cmd  location set-location-enabled  true
echo i == %%i   close_gps 
adb root & adb shell cmd  location set-location-enabled  false
rem ping   127.0.0.1 >nul 
)
echo [rule10vgpsonoff_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule10vgpsonoff_dynamic_param1!]   
echo ______________Method_Out rule10vgpsonoff_func_1x0
::ENDLOCAL
goto:eof




:rule11vgowifipage_func_1x0
rem ======================================== rule11vgowifipage_func_1x0
rem rule_tip: %init_input_0% _11_   ## 开启wifi连接WPS网络

rem desc: 打开手机wifi列表界面 手动添加并携带密码加入 WPS网络
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule11vgowifipage_func_1x0
set rule11vgowifipage_dynamic_param1=
rem 进入设置主界面
adb shell am start com.android.settings/com.android.settings.SubSettings 
rem 进行WIFI列表页面
adb shell am start com.android.settings/com.android.settings.wifi.WifiPickerActivity   
adb shell svc wifi disable
adb shell svc wifi enable
adb shell cmd wifi  connect-network WPS网络  wpa2 87654321  && adb shell cmd wifi  connect-network WPS网络5G  wpa2 87654321
echo [rule11vgowifipage_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule11vgowifipage_dynamic_param1!]   
echo ______________Method_Out rule11vgowifipage_func_1x0
::ENDLOCAL
goto:eof




:rule12vinputtext_func_1x0
rem ======================================== rule12vinputtext_func_1x0
rem rule_tip: %init_input_0% _12_  www.baidu.com  ## adb输入字符串 默认为 www.baidu.com

rem desc: 对当前连接adb手机输入字符串  默认为 www.baidu.com
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule12vinputtext_func_1x0
set rule12vinputtext_dynamic_param1=
if "%init_input_2%"=="" (
set  rule12vinputtext_dynamic_param1=www.baidu.com
) else (
set rule12vinputtext_dynamic_param1=%init_input_2% 
)
echo shell input text !rule12vinputtext_dynamic_param1!
adb shell input text !rule12vinputtext_dynamic_param1!
echo [rule12vinputtext_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule12vinputtext_dynamic_param1!]   
echo ______________Method_Out rule12vinputtext_func_1x0
::ENDLOCAL
goto:eof




:rule13vtakepicture_func_1x0
rem ======================================== rule13vtakepicture_func_1x0
rem rule_tip: %init_input_0% _13_   ## 对当前手机屏幕截屏并拉取到手机本地

rem desc: 对adb 连接着手机进行屏幕截屏 并实时保存到本地 文件名称类似于 B7_zscreenshot_21-05-17_19_00_26_04.png
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule13vtakepicture_func_1x0
set rule13vtakepicture_dynamic_param1=
:rule13vtakepicture_loop
adb wait-for-device
rem set hh=%time:~0,2%
rem if /i %hh% LSS 10 (set hh=0%time:~1,1%)
rem set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%%date:~8,2%_%hh%%time:~3,2%%time:~6,2%
call :gettimehhmmsss_func_0x1
set CURRENT_DATE_TIME_STAMP=!gettimehhmmsss_return_1!
echo CURRENT_DATE_TIME_STAMP=!CURRENT_DATE_TIME_STAMP!
adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
ECHO Picture_Name: /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png 
adb pull /sdcard/Pictures/B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png  ./B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
%win_zbin%/nconvert  -out jpeg -truecolors   B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
ping -n 1 127.0.0.1>nul 
del B7_zscreenshot_%CURRENT_DATE_TIME_STAMP%.png
echo  对当前文件截屏成功 请继续截屏 否则 ctrl+c 停止软件运行 
PAUSE
goto rule13vtakepicture_loop
echo [rule13vtakepicture_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule13vtakepicture_dynamic_param1!]   
echo ______________Method_Out rule13vtakepicture_func_1x0
::ENDLOCAL
goto:eof




:rule14vtakevideo_func_1x0
rem ======================================== rule14vtakevideo_func_1x0
rem rule_tip: %init_input_0% _14_     ## 对当前手机屏幕录屏 然后拔出插入 使得mp4文件拉取到手机本地

rem desc: 对连接着adb的安卓设备进行录屏操作,拔除usb将 停止录屏,重新插入USB 将 拉取录屏mp4到本地
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule14vtakevideo_func_1x0
set rule14vtakevideo_dynamic_param1=
:rule14vtakevideo_loop
adb wait-for-device
adb root
adb remount
call :gettimehhmmsss_func_0x1
set CURRENT_DATE_TIME_STAMP=!gettimehhmmsss_return_1!
echo 创建文件夹  mp4_!CURRENT_DATE_TIME_STAMP!
md  mp4_!CURRENT_DATE_TIME_STAMP!
ECHO  take screen video nowing Please remove device with USB  to stop video shot when you finish takescreen!
echo  正在进行屏幕录像操作,如果想停止录像请拔掉USB连接线 使得录屏程序停止!
adb shell screenrecord --bit-rate 4000000 /sdcard/Pictures/rule14_!CURRENT_DATE_TIME_STAMP!.mp4
ECHO   take screen video stop ! 
ECHO MP4_Name: /sdcard/Pictures/rule14_!CURRENT_DATE_TIME_STAMP!.mp4
ECHO   请重新插入USB 使得拉取当前录像文件 /sdcard/Pictures/rule14_!CURRENT_DATE_TIME_STAMP!.mp4
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/rule14_%CURRENT_DATE_TIME_STAMP%.mp4  ./mp4_!CURRENT_DATE_TIME_STAMP!/rule14_!CURRENT_DATE_TIME_STAMP!.mp4 
ECHO   请输入 Entry 键 使得开始下一个录屏操作！ 
PAUSE
GOTO rule14vtakevideo_loop
echo [rule14vtakevideo_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule14vtakevideo_dynamic_param1!]   
echo ______________Method_Out rule14vtakevideo_func_1x0
::ENDLOCAL
goto:eof




:rule15vxmusbcreendown_func_2x0
rem ======================================== rule15vxmusbcreendown_func_2x0
rem rule_tip: %init_input_0% _15_   100000   ## 有线USB_手机执行 adb  shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  c2f5b32c  100000    ##小米_有线USB_手机执行  adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  721QECRM2ZMT4  100000  ##魅蓝_有线USB_手机执行  adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  HMKNW17531001873 100000  ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  192.168.31.89:5555 100000  ##小米_无线USB_无线 adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  192.168.31.40:5555 100000  ##魅蓝_无线USB_无线 adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem rule_tip: %init_input_0% _15_  192.168.31.10:5555 100000  ##荣耀_无线USB_无线 adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem desc: 小米_有线USB_手机执行 adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule15vxmusbcreendown_func_2x0
set rule15vxmusbcreendown_dynamic_param1=
set rule15vxmusbcreendown_dynamic_param2=
if "%init_input_2%"=="" (
set  rule15vxmusbcreendown_dynamic_param1=c2f5b32c
) else (
set rule15vxmusbcreendown_dynamic_param1=%init_input_2% 
)
if "%init_input_3%"=="" (
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule15vxmusbcreendown_dynamic_param1!]     
adb  shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb  shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb  shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule15vxmusbcreendown_dynamic_param1!]        
adb   shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb   shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule15vxmusbcreendown_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule15vxmusbcreendown_dynamic_param1!]   dynamic_param2=[!rule15vxmusbcreendown_dynamic_param2!]   
echo ______________Method_Out rule15vxmusbcreendown_func_2x0
goto:eof
) else (
set rule15vxmusbcreendown_dynamic_param2=%init_input_3% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule15vxmusbcreendown_dynamic_param1!]  dynamic_param2=[!rule15vxmusbcreendown_dynamic_param2!]    
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param2!) do (
echo i == %%i   _todown   allLoop=[!rule15vxmusbcreendown_dynamic_param2!]     
adb -s %init_input_2%  shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s %init_input_2%  shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s %init_input_2%  shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param2!) do (
echo i == %%i  _toup     allLoop=[!rule15vxmusbcreendown_dynamic_param2!]        
adb -s %init_input_2%   shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s %init_input_2%  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s %init_input_2%   shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule15vxmusbcreendown_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule15vxmusbcreendown_dynamic_param1!]   dynamic_param2=[!rule15vxmusbcreendown_dynamic_param2!]   
echo ______________Method_Out rule15vxmusbcreendown_func_2x0
::ENDLOCAL
goto:eof




:rule16vmultiscreen2end_func_1x0
rem ======================================== rule16vmultiscreen2end_func_1x0
rem rule_tip: %init_input_0% _16_  c2f5b32c        ##小米_有线USB_手机执行  adb -s c2f5b32c shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

rem rule_tip: %init_input_0% _16_  721QECRM2ZMT4   ##魅蓝_有线USB_手机执行  adb -s 721QECRM2ZMT4 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

rem rule_tip: %init_input_0% _16_  HMKNW17531001873   ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

rem rule_tip: %init_input_0% _16_  192.168.31.89:5555   ##小米_无线USB_无线adb adb -s 192.168.31.89:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

rem rule_tip: %init_input_0% _16_  192.168.31.40:5555   ##魅蓝_无线USB_无线adb adb -s 192.168.31.40:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

rem rule_tip: %init_input_0% _16_  192.168.31.10:5555   ##荣耀_无线USB_无线adb adb -s 192.168.31.10:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule16vmultiscreen2end_func_1x0
set rule16vmultiscreen2end_dynamic_param1=
if "%init_input_2%"=="" (
set  rule16vmultiscreen2end_dynamic_param1=c2f5b32c
) 
echo allLoop=[1000000]        
for /l %%i in (1, 1, 1000000) do (
rem echo i == %%i   _todown        
echo adb -s %init_input_2% shell input swipe 340 1000 340 200  50 [i == %%i   _todown  ]
adb -s %init_input_2% shell input swipe 340 1000 340 200  50
)
echo [rule16vmultiscreen2end_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule16vmultiscreen2end_dynamic_param1!]   
echo ______________Method_Out rule16vmultiscreen2end_func_1x0
::ENDLOCAL
goto:eof




:rule17vadbdumpservice_func_0x0
rem ======================================== rule17vadbdumpservice_func_0x0
rem rule_tip: %init_input_0% _17_       ##把 连接adb的 手机 执行 adb dump service 抓取一些配置文件 命令 到 adb_dump_时间戳 文件夹
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule17vadbdumpservice_func_0x0
cd !cd!
ECHO init-ing
ECHO waiting conenct with USB
adb wait-for-device
call :gettimehhmmsss_func_0x1
set CURRENT_DATE_TIME_STAMP=!gettimehhmmsss_return_1!
echo 创建文件夹  adb_dump_!CURRENT_DATE_TIME_STAMP!
md  adb_dump_!CURRENT_DATE_TIME_STAMP!
adb root 
adb remount
echo adb pull /data/system/users/0/settings_system.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_system.xml
adb pull /data/system/users/0/settings_system.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_system.xml
echo adb pull /data/system/users/0/settings_secure.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_secure.xml
adb pull /data/system/users/0/settings_secure.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_secure.xml
echo adb pull /data/system/users/0/settings_global.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_global.xml
adb pull /data/system/users/0/settings_global.xml   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_system_users_0_settings_global.xml
echo adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini        adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_WCNSS_qcom_cfg.ini
adb pull /vendor/etc/wifi/WCNSS_qcom_cfg.ini        adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_WCNSS_qcom_cfg.ini
echo adb pull /vendor/etc/wifi/wpa_supplicant.conf     adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_wpa_supplicant.conf
adb pull /vendor/etc/wifi/wpa_supplicant.conf     adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_wpa_supplicant.conf
echo adb pull /system/etc/wifi/p2p_supplicant.conf    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_p2p_supplicant.conf
adb pull /system/etc/wifi/p2p_supplicant.conf    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_etc_wifi_p2p_supplicant.conf
echo adb pull /data/misc/wifi/WifiConfigStore.xml    adb_dump_!CURRENT_DATE_TIME_STAMP!\data_misc_wifi_WifiConfigStore.xml
adb pull /data/misc/wifi/WifiConfigStore.xml    adb_dump_!CURRENT_DATE_TIME_STAMP!\data_misc_wifi_WifiConfigStore.xml
echo adb pull /data/misc/wifi/softap.conf     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_misc_wifi_softap.conf
adb pull /data/misc/wifi/softap.conf     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_misc_wifi_softap.conf
echo adb pull /system/build.prop               adb_dump_!CURRENT_DATE_TIME_STAMP!\system_build.prop
adb pull /system/build.prop               adb_dump_!CURRENT_DATE_TIME_STAMP!\system_build.prop
echo adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_fireware_mnt_image_wlanmdsp.mbn
adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_fireware_mnt_image_wlanmdsp.mbn
echo 
adb pull  /vendor/fireware_mnt/image/Data.msc    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_fireware_mnt_image_Data.mbn
echo adb pull /vendor/rfs/msm/mpss/ramdumps    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_rfs_msm_mpss_ramdumps\
adb pull /vendor/rfs/msm/mpss/ramdumps    adb_dump_!CURRENT_DATE_TIME_STAMP!\vendor_rfs_msm_mpss_ramdumps\
echo adb pull /storage/emulated/0/Pictures/Screenshots  adb_dump_!CURRENT_DATE_TIME_STAMP!\sdcard_Pictures_Screenshots\
adb pull /storage/emulated/0/Pictures/Screenshots  adb_dump_!CURRENT_DATE_TIME_STAMP!\sdcard_Pictures_Screenshots\
echo adb pull /system/etc/hostapd/hostapd.deny       adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc_hostapd.deny 
adb pull /system/etc/hostapd/hostapd.deny       adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc_hostapd.deny 
echo adb pull /system/etc/hostapd/hostapd.accept      adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc_hostapd.accept 
adb pull /system/etc/hostapd/hostapd.accept      adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc_hostapd.accept 
echo adb pull /data/vendor/wifi/hostapd/hostapd.conf    adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.conf
adb pull /data/vendor/wifi/hostapd/hostapd.conf    adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.conf
echo adb pull /data/vendor/wifi/hostapd/hostapd.accept   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.accept
adb pull /data/vendor/wifi/hostapd/hostapd.accept   adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.accept
echo adb pull /data/vendor/wifi/hostapd/hostapd.deny  adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.deny
adb pull /data/vendor/wifi/hostapd/hostapd.deny  adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_wifi_hostapd_hostapd.deny
echo pull /data/vendor/bug2go       adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_bug2go\
adb pull /data/vendor/bug2go       adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_bug2go\
echo  adb pull  /data/vendor/diag_mdlog     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_diag_mdlog\
adb pull  /data/vendor/diag_mdlog     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_diag_mdlog\
echo  pull /data/vendor/aplogd/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_aplogd\
adb pull /data/vendor/aplogd/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_aplogd\
echo adb pull  /system/etc/     adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc\
adb pull  /system/etc/     adb_dump_!CURRENT_DATE_TIME_STAMP!\system_etc\
echo adb pull /etc/      adb_dump_!CURRENT_DATE_TIME_STAMP!\etc\
adb pull /etc/      adb_dump_!CURRENT_DATE_TIME_STAMP!\etc\
echo adb pull  /data/user/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_user\
adb pull  /data/user/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_user\
echo adb pull  /data/user_de/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_user_de\
adb pull  /data/user_de/     adb_dump_!CURRENT_DATE_TIME_STAMP!\data_user_de\
echo adb pull  /data/debuglogger  adb_dump_!CURRENT_DATE_TIME_STAMP!\data_mtk_debuglogger\
adb pull  /data/debuglogger  adb_dump_!CURRENT_DATE_TIME_STAMP!\data_mtk_debuglogger\
adb pull   /data/vendor/mtklog/  adb_dump_!CURRENT_DATE_TIME_STAMP!\data_vendor_mtklog\
adb shell ls -l  /dev/  > adb_dump_!CURRENT_DATE_TIME_STAMP!\dev.txt
adb shell service list  > adb_dump_!CURRENT_DATE_TIME_STAMP!\Adb_AllService.txt
adb shell getprop > adb_dump_!CURRENT_DATE_TIME_STAMP!\Prop.txt
adb shell pm list features > adb_dump_!CURRENT_DATE_TIME_STAMP!\pm_list_features.txt
adb shell pm list packages  > adb_dump_!CURRENT_DATE_TIME_STAMP!\pm_list_packages.txt
ping -n 2 127.0.0.1>nul
if exist adb_dump_!CURRENT_DATE_TIME_STAMP!\Adb_AllService.txt (
  for /f "tokens=1,2" %%i in (adb_dump_!CURRENT_DATE_TIME_STAMP!\Adb_AllService.txt) do (
       set /a num+=1 
	   set count=%%i
       set service=%%j
	   set realservice=!service:~0,-1!
	   echo service_index=[!count!]     service_name=[!realservice!]                       dump_file=[!count!_!realservice!.txt]
	   	if not "!realservice!"=="bugreport" (
	   adb shell dumpsys !realservice! > adb_dump_!CURRENT_DATE_TIME_STAMP!\!count!_!realservice!.txt
	   )
rem   call adb uninstall !str1!
  )
ECHO all dumpsys service loaded at  adb_dump_!CURRENT_DATE_TIME_STAMP!\ !
) else (
ECHO adb_dump_!CURRENT_DATE_TIME_STAMP!\Adb_AllService.txt  not exist !
)
adb shell dumpres        > adb_dump_!CURRENT_DATE_TIME_STAMP!\dumpres.txt
adb shell dumpsys wifi               > adb_dump_!CURRENT_DATE_TIME_STAMP!\dumpsys_wifi.txt
adb shell dumpsys wifiscanner        > adb_dump_!CURRENT_DATE_TIME_STAMP!\dumpsys_wifiscanner.txt
adb shell iw phy0 reg get            > adb_dump_!CURRENT_DATE_TIME_STAMP!\iw_reg_get.txt  
adb shell pm list features           > adb_dump_!CURRENT_DATE_TIME_STAMP!\pm_list_features.txt  
adb shell dumpsys telephony.registry > adb_dump_!CURRENT_DATE_TIME_STAMP!\dumpsys_telephony.txt  
echo [rule17vadbdumpservice_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule17vadbdumpservice_func_0x0
::ENDLOCAL
goto:eof




:rule18vinstalllocalsoft_func_0x0
rem ======================================== rule18vinstalllocalsoft_func_0x0
rem rule_tip: %init_input_0% _18_  ## 安装本地zsoft到zsoft_dest目录 1.解压 2.复制zbin 3.配置环境变量 4.静默安装 5.添加绿色软件桌面icon

rem desc: 安装本地zsoft的软件 1.解压 2.复制zbin 3.配置环境变量 4.静默安装 5.添加绿色软件桌面icon
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule18vinstalllocalsoft_func_0x0
echo init_cd=!init_cd!
call ::getfiledp_func_1x1 !init_cd!
set zsoft_parent_dir=!getfiledp_return_1!
set zsoft_dest_dir=!zsoft_parent_dir!zsoft_dest
echo zsoft_parent_dir=!zsoft_parent_dir!
echo zsoft_dest_dir=!zsoft_dest_dir!
call ::getfilenamenopointwithfullpath_func_1x1  !init_cd!
echo init_cd_dirname=!getfilenamenopointwithfullpath_return_1!
if not "!getfilenamenopointwithfullpath_return_1!"=="zsoft"  (
echo ______________ rule18_1_Error Error Error Error Error Begin ______________
echo 当前目录[!init_cd!]不是 zsoft 软件集成根目录 无法执行安装软件操作 请检查当前执行bat文件 %init_input_0% 的执行目录
echo ______________ rule18_1_Error Error Error Error Error End ______________
echo [rule18vinstalllocalsoft_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule18vinstalllocalsoft_func_0x0
goto:eof
)
call :isadminuser_func_0x1
echo  isadminuser_return_1=!isadminuser_return_1!
if "!isadminuser_return_1!"=="false" (
echo 请确保当前是运行在管理员权限下,否则系统环境变量可能无法设置.
ping -n 2 127.0.0.1>nul
echo 当前不是 Admin 的 运行环境 无法执行安装程序操作  程序停止！
echo [rule18vinstalllocalsoft_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule18vinstalllocalsoft_func_0x0
goto:eof
)
echo 当前CMD环境是 Admin环境 将往下执行 3
ping -n 2 127.0.0.1>nul
echo 当前CMD环境是 Admin环境 将往下执行 2
ping -n 2 127.0.0.1>nul
echo 当前CMD环境是 Admin环境 将往下执行 1

echo 0开始执行zsoft_dest目录生成操作!!
call :isfileexist_func_1x1  !zsoft_dest_dir!
set zsoft_dest_dir_existflag=!isfileexist_return_1!
if "%zsoft_dest_dir_existflag%"=="true" (
echo 当前zsoft目录[!init_cd!]  对应的使能目录[!zsoft_dest_dir!] 已存在了!!!
) else (
echo 当前zsoft目录[!init_cd!]  对应的使能目录[!zsoft_dest_dir!]   不存在 将创建这个目录!!!
mkdir  !zsoft_dest_dir!
)
echo 1.解压zsoft所有 .zip .rar .7z 压缩文件   范围[1.当前目录内  2.当前目录子目录内 3.不包含孙目录 ]

echo ____________________________ 显示当前 !init_cd! 目录 .zip 文件列表 Begin ____________________________
call :showsubfile4dir4type_func_2x0 !init_cd!  *.zip
echo ____________________________ 显示当前 !init_cd! 目录 .zip 文件列表 Endxx ____________________________

echo ____________________________ 解压缩 !init_cd! 目录 .zip 文件列表 Begin ____________________________
set /a index_zip_file = 0
for /f "delims=\" %%a in ('dir /b /a-d /o-d "!init_cd!\*.zip"') do (
set /a index_zip_file+=1
rem %1\%%a    %%a是文件名字    %1 !init_cd! 是当前搜索目录   zip[0]  D:\Local_Soft_Test\zsoft\zbin.zip
set zip_file_fullpath=!init_cd!/%%a
rem  zsoft/zbin.zip   express To  zsoft_dest/
echo zip[!index_zip_file!] == !zsoft_dest_dir!  开始执行解压缩操作
call ::expressfiletoinputdir_func_2x0 !zip_file_fullpath!  !zsoft_dest_dir!
)
echo ____________________________ 解压缩 !init_cd! 目录 .zip 文件列表 Endxx ____________________________

rem  开始解压 子目录 中的 zip 文件 

echo ____________________________ 开始解压 子目录 中的 zip 文件   Begin ____________________________
call ::showsubdir_func_1x0 !init_cd!
set /a Index_zsoft_subdir = 0
for /d %%a in (!init_cd!\*) do (
set /a Index_zsoft_subdir+=1
rem %%a 就是目录的全路径
echo  zsoft_subdir_index=[!Index_zsoft_subdir!]   zsoft_sub_dir=[%%a] 
echo ____________________________ 解压缩 %%a 目录下 .zip 文件列表 Begin ____________________________
set /a index_zip_file = 0
for /f "delims=\" %%b in ('dir /b /a-d /o-d "%%a\*.zip"') do (
set /a index_zip_file+=1
rem %1\%%a    %%a是文件名字    %1 !init_cd! 是当前搜索目录   zip[0]  D:\Local_Soft_Test\zsoft\zbin.zip
set zip_file_fullpath=%%a/%%b
call ::getfilenx_func_1x1 %%a
set local_sondir_name=!getfilenx_return_1!
echo GG_A=%%a
echo GG_B=%%b
echo local_sondir_name=!local_sondir_name!
set  zsoftdst_zipdir_fullpath=!zsoft_dest_dir!\!local_sondir_name!
echo zsoft_sondir_name=[!zsoft_dest_dir!\!local_sondir_name!]  [!zsoftdst_zipdir_fullpath!]
echo zip[!index_zip_file!] ==  !zsoft_dest_dir!!local_sondir_name! 开始执行解压缩操作
call ::expressfiletoinputdir_func_2x0 !zip_file_fullpath!  !zsoftdst_zipdir_fullpath!
rem zip[1] == D:\TEMP\ZZ\zsoft\Z1_Must_Manual_Zip/IDEA_IntelliJ_2018.3.3.zip  开始执行解压缩操作
rem call ::expressfiletodir_func_1x1  !zip_file_fullpath!
)
echo ____________________________ 解压缩 %%a 目录下 .zip 文件列表 Endxx ____________________________

echo ____________________________ 复制 %%a 目录下 .exe .mci 文件列表 Begin ____________________________
set /a index_exe_file = 1
for /f "delims=\" %%b in ('dir /b /a-d /o-d "%%a\*.*"') do (
rem  需要 过滤 掉 zip 文件 
set exe_file_fullpath=%%a\%%b
call ::getfilex_func_1x1 !exe_file_fullpath!
set cur_exefile_type=!getfilex_return_1!
echo cur_exefile_type=!cur_exefile_type!    exe_file_fullpath=!exe_file_fullpath!
if "!cur_exefile_type!"==".zip" (
rem echo 当前选中文件 !exe_file_fullpath!  是 zip 文件 跳过复制 该文件的操作!
) else (
rem 当前选中文件 exe[1]=[D:\TEMP\ZZ\zsoft\B0_Slient1_OneExe_Local_Install/YoudaoDictSetup_8.9.6.0.exe]  非zip 文件   copyfiledirtodir_func_2x0  D:\TEMP\ZZ\zsoft\B0_Slient1_OneExe_Local_Install/YoudaoDictSetup_8.9.6.0.exe   D:\TEMP\ZZ\zsoft_dest
echo=
echo 当前选中文件 exe[!index_exe_file!]=[!exe_file_fullpath!]  非zip 文件   copyfiledirtodir_func_2x0  !exe_file_fullpath!   !zsoft_dest_dir!   将执行复制该文件的操作!
rem copyfiledirtodir_func_2x0  D:\TEMP\ZZ\zsoft\B0_Slient1_OneExe_Local_Install/YoudaoDictSetup_8.9.6.0.exe   D:\TEMP\ZZ\zsoft_dest\B0_Slient1_OneExe_Local_Install
echo GG_exeA=%%a
echo GG_exeB=%%b
call ::getfilenx_func_1x1 %%a
set cur_exedir_name=!getfilenx_return_1!
echo cur_exedir_name=!cur_exedir_name!
rem GG_exeA=D:\TEMP\ZZ\zsoft\C0_Green_OneExe
set zsoftdst_exedir_name=!zsoft_dest_dir!\!cur_exedir_name!
echo zsoftdst_exedir_name=!zsoftdst_exedir_name!
echo   copyfiledirovertodir_func_2x0  !exe_file_fullpath!   !zsoftdst_exedir_name!
set /a index_exe_file+=1
call ::copyfiledirovertodir_func_2x0  !exe_file_fullpath!   !zsoftdst_exedir_name!
)
)
echo ____________________________ 复制 %%a 目录下 .exe .mci 文件列表 Endxx ____________________________
)
echo ____________________________ 开始解压 子目录 中的 zip 文件   End ____________________________

echo ____________________________ 开始执行 复制当前 !zsoft_dest_dir!\zbin\ 目录 到 !desktop! 目录的操作 Begin ____________________________
call ::copyfiledirtodir_func_2x0  !zsoft_dest_dir!\zbin\  !zbin!
echo ____________________________ 开始执行 复制当前 !zsoft_dest_dir!\zbin\ 目录 到 !desktop! 目录的操作 Endxx ____________________________

echo ____________________________ java python win_zbin ffmpeg notepad++ 添加到环境变量 Begin  ____________________________
rem  javac.exe
rem  java.exe
rem  python.exe
rem  python.exe-dir/Scripts/
rem  %zbin%/win_zbin/    %win_zbin%   [包含了 adb ]
rem  ffmpeg.exe
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  javac.exe
set javac_file_fullpath=!searchonetargetfile4dir4type_return_1!
rem  javac_file_fullpath=D:\zsoft_dest\D0_Environment_Zip_Dir_Path\JDK8_64\bin\javac.exe
echo javac_file_fullpath=!javac_file_fullpath!
call ::getfiledp_func_1x1 !javac_file_fullpath!
set javac_dir_fullpath=!getfiledp_return_1!
echo javac_dir_fullpath=!javac_dir_fullpath!  javac_file_fullpath=!javac_file_fullpath!
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  java.exe
set java_file_fullpath=!searchonetargetfile4dir4type_return_1!
echo java_file_fullpath=!java_file_fullpath!
call ::getfiledp_func_1x1 !java_file_fullpath!
set java_dir_fullpath=!getfiledp_return_1!
echo java_dir_fullpath=!java_dir_fullpath!  java_file_fullpath=!java_file_fullpath!
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  python.exe
set python_file_fullpath=!searchonetargetfile4dir4type_return_1!
echo python_file_fullpath=!python_file_fullpath!
call ::getfiledp_func_1x1 !python_file_fullpath!
set python_dir_fullpath=!getfiledp_return_1!
echo python_dir_fullpath=!python_dir_fullpath!  python_file_fullpath=!python_file_fullpath!
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  notepad++.exe
set notepad_file_fullpath=!searchonetargetfile4dir4type_return_1!
echo notepad_file_fullpath=!notepad_file_fullpath!
call ::getfiledp_func_1x1 !notepad_file_fullpath!
set notepad_dir_fullpath=!getfiledp_return_1!
echo notepad_dir_fullpath=!notepad_dir_fullpath!  notepad_file_fullpath=!notepad_file_fullpath!
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  ffmpeg.exe
set ffmpeg_file_fullpath=!searchonetargetfile4dir4type_return_1!
echo ffmpeg_file_fullpath=!ffmpeg_file_fullpath!
call ::getfiledp_func_1x1 !ffmpeg_file_fullpath!
set  ffmpeg_dir_fullpath=!getfiledp_return_1!
echo ffmpeg_dir_fullpath=!ffmpeg_dir_fullpath!  ffmpeg_file_fullpath=!ffmpeg_file_fullpath!
echo  win_zbin_dir_fullpath=%win_zbin%
echo pythonscript_dir_fullpath=!python_dir_fullpath!Scripts
rem  addpathenvironment_func_1x0  添加到环境变量  检查环境变量是否有这个值 如果有 跳过   没有 就加入 
rem  call ::addpathenvironment_func_1x0 %win_zbin%
rem  call ::addpathenvironment_func_1x0 !python_dir_fullpath!Scripts
rem  call ::addpathenvironment_func_1x0 !ffmpeg_dir_fullpath!
rem  call ::addpathenvironment_func_1x0 !notepad_dir_fullpath!
rem  call ::addpathenvironment_func_1x0 !python_dir_fullpath!
rem  call ::addpathenvironment_func_1x0 !java_dir_fullpath!
rem  call ::addpathenvironment_func_1x0 !javac_dir_fullpath!
set all_environment_var="!win_zbin!;!python_dir_fullpath!Scripts;!ffmpeg_dir_fullpath!;!notepad_dir_fullpath!;!python_dir_fullpath!;!java_dir_fullpath!;!javac_dir_fullpath!"
call ::addpathenvironment_func_1x0  !all_environment_var!
echo ____________________________ java python win_zbin ffmpeg notepad++ 添加到环境变量 End  ____________________________

echo ____________________________ java 执行 %desktop%\zbin\J1_InstallSoftware.java Begin  ____________________________
echo %javac_file_fullpath%  -cp %desktop%\zbin\J1_guava.jar;%desktop%\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8   %desktop%\zbin\J1_InstallSoftware.java
call %javac_file_fullpath%  -cp %desktop%\zbin\J1_guava.jar;%desktop%\zbin\J1_jshortcut_oberzalek.jar -Xlint:unchecked  -encoding UTF-8  %desktop%\zbin\J1_InstallSoftware.java
echo;
echo %java_file_fullpath% -cp %desktop%\zbin\J1_guava.jar;%desktop%\zbin\J1_jshortcut_oberzalek.jar;%desktop%\zbin  J1_InstallSoftware  !zsoft_dest_dir!
call %java_file_fullpath% -cp %desktop%\zbin\J1_guava.jar;%desktop%\zbin\J1_jshortcut_oberzalek.jar;%desktop%\zbin  J1_InstallSoftware  !zsoft_dest_dir!
echo ____________________________ java 执行 %desktop%\zbin\J1_InstallSoftware.java Endxx  ____________________________

echo ____________________________ python pip安装工具 Begin  ____________________________

rem   复制所有的 .whl 文件到 !python_dir_fullpath!wheel_tool_dir/文件夹中
for /r %init_cd% %%j in  (*.whl*)do (
set whl_file_fullpath=%%j
echo  whl_file_fullpath=!whl_file_fullpath!
echo  call ::copyfiledirovertodir_func_2x0  !whl_file_fullpath!   !python_dir_fullpath!wheel_tool_dir\
call ::copyfiledirovertodir_func_2x0  !whl_file_fullpath!   !python_dir_fullpath!wheel_tool_dir\
)
rem  python  get-pip.py 
call %python_file_fullpath% !python_dir_fullpath!get-pip.py     
echo ____________________________ python pip安装工具 Endxx  ____________________________
echo J1_InstallSoft.bat__PATH=!zsoft_dest_dir!\J1_InstallSoft.bat
call !zsoft_dest_dir!\J1_InstallSoft.bat
echo ____________________________ Cmder 注册 Setting Environment 提示 Begin  ____________________________
call ::searchonetargetfile4dir4type_func_2x1  !zsoft_dest_dir!  Cmder.exe
set cmder_file_fullpath=!searchonetargetfile4dir4type_return_1!
echo cmder_file_fullpath=!cmder_file_fullpath!
call ::showcmdercommand_func_1x0 !cmder_file_fullpath!
echo ____________________________ Cmder 注册 Setting Environment 提示 Endxx  ____________________________

echo ____________________________ 执行 notepad.reg 注册notepad到右键菜单 Begin  ____________________________
echo notepad.reg__PATH=!zsoft_dest_dir!\notepad.reg 
call !zsoft_dest_dir!\notepad.reg 
echo ____________________________ 执行 notepad.reg 注册notepad到右键菜单 End  ____________________________
echo [rule18vinstalllocalsoft_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule18vinstalllocalsoft_func_0x0
::ENDLOCAL
goto:eof




:rule19vdownloadshoucangjia_func_0x0
rem ======================================== rule19vdownloadshoucangjia_func_0x0
rem rule_tip: %init_input_0% _19_        ## ADB 命令 Tel自动加载命令 第19规则
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule19vdownloadshoucangjia_func_0x0
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule3vinstalldirapk_dynamic_param1!]   
adb shell am force-stop org.telegram.messenger.web
ping -n 1 127.0.0.1>nul
rem 回到桌面
adb shell input keyevent 3
ping -n 1 127.0.0.1>nul
rem 打开应用
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
echo _____ 模拟点击进入 favourite 目录 Begin  _____
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
echo _____ 模拟点击进入 favourite 目录 End  _____

rem 往下滑动 到最底层

echo ____ 往下滑动 到最底层 Begin ____
adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
rem adb shell input swipe   1050 1200 1050 400  200
set rule19teldown_dynamic_param1=100000
echo ____ 往下滑动 到最底层 End ____
for /l %%i in (1, 1, !rule19teldown_dynamic_param1!) do (
set loop_numnber=%%i
set reset_yushu_number=500
set fanhuishu_number=30
set move_begin_number=1000
set /a loop_numnber_resetyushu=!loop_numnber!%%!reset_yushu_number!
set /a loop_numnber_fanhuishu=!loop_numnber!%%!fanhuishu_number!
set /a loop_numnber_movebeginshu=!loop_numnber!%%!move_begin_number!
adb shell ps | findstr telegram | awk '{print $2}' > tel_pid.txt
set /a tel_pid_num =< tel_pid.txt
adb shell cat /proc/!tel_pid_num!/net/dev | findstr wlan0 | awk '{print $2}' > tel_recv.txt
set /a tel_recv_num =< tel_recv.txt
echo  loop_begin__________ tel_pid_num=!tel_pid_num!  tel_recv_num=!tel_recv_num!_______
echo __________________ Loop_Index[%%i] Begin  _______________________
echo i == %%i      allLoop=[!rule19teldown_dynamic_param1!]  yushu=[!loop_numnber_resetyushu!]    强制返回桌面循环数[!fanhuishu_number!] 强制重启循环数=[!reset_yushu_number!]   adb shell input tap 190 1000

echo 屏幕点击 _A1  x_190 y_1000
adb shell input tap 190 1000
ping -n 3 127.0.0.1>nul
echo 往上滑动 _A1
adb shell input swipe  1050 600 1050 1050  200
ping -n 3 127.0.0.1>nul
echo i == %%i      allLoop=[!rule19teldown_dynamic_param1!]  yushu=[!loop_numnber_resetyushu!]    强制返回桌面循环数[!fanhuishu_number!] 强制重启循环数=[!reset_yushu_number!]    adb shell input tap 190 1100

echo 屏幕点击 _A2  x_190 y_1100
adb shell input tap 190 1100
ping -n 3 127.0.0.1>nul
echo 往上滑动 _A2
adb shell input swipe  1050 600 1050 1050  200
ping -n 3 127.0.0.1>nul
echo i == %%i      allLoop=[!rule19teldown_dynamic_param1!]  yushu=[!loop_numnber_resetyushu!]    强制返回桌面循环数[!fanhuishu_number!] 强制重启循环数=[!reset_yushu_number!]    adb shell input tap 190 1200

echo 屏幕点击 _A3  x_190 y_1200
adb shell input tap 190 1200
ping -n 3 127.0.0.1>nul
echo 往上滑动 _A3
adb shell input swipe  1050 600 1050 1050  200
ping -n 1 127.0.0.1>nul
echo i == %%i      allLoop=[!rule19teldown_dynamic_param1!]  yushu=[!loop_numnber_resetyushu!]    强制返回桌面循环数[!fanhuishu_number!] 强制重启循环数=[!reset_yushu_number!]    adb shell input tap 190 1300

echo 屏幕点击 _A4  x_190 y_1300
adb shell input tap 190 1300
ping -n 3 127.0.0.1>nul
echo 往上滑动 _A4
adb shell input swipe  1050 600 1050 1050  200
ping -n 3 127.0.0.1>nul
echo i == %%i      allLoop=[!rule19teldown_dynamic_param1!]  yushu=[!loop_numnber_resetyushu!]    强制返回桌面循环数[!fanhuishu_number!] 强制重启循环数=[!reset_yushu_number!]    adb shell input tap 190 1400

echo 屏幕点击 _A5  x_190 y_1400
adb shell input tap 190 1400
ping -n 3 127.0.0.1>nul
echo 往上滑动 _A5
adb shell input swipe  1050 600 1050 1050  200
ping -n 3 127.0.0.1>nul
echo loop_numnber_resetyushu=!loop_numnber_resetyushu!
if !loop_numnber_fanhuishu! EQU 0 (
echo  loop_numnber_fanhuishu=[!loop_numnber_fanhuishu!]____________一直返回  返回到主界面 Begin ____________ 
adb shell input keyevent 4
ping -n 1 127.0.0.1>nul
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
adb shell input keyevent 4
ping -n 1 127.0.0.1>nul
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
adb shell input keyevent 4
ping -n 1 127.0.0.1>nul
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
adb shell input keyevent 4
ping -n 1 127.0.0.1>nul
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
rem 打开应用

echo  loop_numnber_fanhuishu=[!loop_numnber_fanhuishu!]____________一直返回  返回到主界面 End ____________ 
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
echo ......等待5秒......
ping -n 5 127.0.0.1>nul
echo 屏幕点击 _B1  x_450 y_450
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
echo 屏幕点击 _B2  x_450 y_450
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
rem call :getrandomintwithmaxmin_func_2x1  0 100
rem set randomInt_A=!getrandomintwithmaxmin_return_1!
rem echo 随机往上下拉  randomInt_A=[!randomInt_A!] 
rem if !randomInt_A! GEQ 50 (
rem echo 随机结果[!randomInt_A! 大于等于50 ]: _________往上滑______

rem echo 往上滑动 _B1
rem adb shell input swipe    1050 400 1050 1000  200
rem echo 往上滑动 _B2
rem adb shell input swipe    1050 400 1050 1000  200
rem ) else (
rem echo 随机结果[!randomInt_A! 小于 50    ]: _________往下滑______
rem adb shell input swipe     1050 1000 1050 400 200
rem echo 往下滑动 _B1
rem adb shell input swipe     1050 1000 1050 400 200
rem echo 往下滑动 _B1
rem )
)
echo _______________________  依次循环结束 _______________________
if !loop_numnber_resetyushu! EQU 0 (
echo  尝试强制关闭应用   !reset_yushu_number! 
adb shell am force-stop org.telegram.messenger.web
ping -n 1 127.0.0.1>nul
echo 尝试强制关闭应用 回到桌面
adb shell input keyevent 3
ping -n 1 127.0.0.1>nul
echo 尝试强制关闭应用 打开应用
adb shell am start  org.telegram.messenger.web/org.telegram.ui.LaunchActivity
ping -n 1 127.0.0.1>nul
echo  模拟点击 _C1 450 450  进入 favourite 目录
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
echo  模拟点击 _C2 450 450 进入 favourite 目录
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
echo  模拟点击 _C3 450 450 进入 favourite 目录
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
echo  模拟点击 _C4 450 450 进入 favourite 目录
adb shell input tap 450 450
ping -n 1 127.0.0.1>nul
rem 随机往下拉 往上拉
call :getrandomintwithmaxmin_func_2x1  0 100
set randomInt_A=!getrandomintwithmaxmin_return_1!
echo 尝试强制关闭应用 随机往上下拉  randomInt_A=[!randomInt_A!] 
if !randomInt_A! GEQ 50 (
echo 尝试强制关闭应用 随机结果[!randomInt_A! 大于等于50 ]: _________往上滑______
echo=
echo 往上滑动 _C1
adb shell input swipe    1050 400 1050 1000  200
echo 往上滑动 _C2
adb shell input swipe    1050 400 1050 1000  200
) else (
echo 尝试强制关闭应用 随机结果[!randomInt_A! 小于 50    ]: _________往下滑______
echo=
echo 往下滑动 _C1
adb shell input swipe     1050 1000 1050 400 200
echo 往下滑动 _C2
adb shell input swipe     1050 1000 1050 400 200
)
)
rem echo  模拟点击 _D1 1050 1600
if !loop_numnber_movebeginshu! EQU 0 (
echo  模拟点击回到地步按键 _D1 1050 1600
adb shell input tap 1050 1600
)
call :getandroidreceivembwithin5s_func_0x1
set currentdev_receive_MD=!getandroidreceivembwithin5s_return_1!
echo currentdev_receive_MD=!currentdev_receive_MD!  getandroidreceivembwithin5s_return_1=!getandroidreceivembwithin5s_return_1!
rem 有流量 时  不移动   无流量 MD=0 才移动  
if !currentdev_receive_MD! EQU 0 (
set /a move_base_step=!loop_numnber!/!reset_yushu_number! + 1
echo move_base_step=!move_base_step!
rem for /l %%k in (1, 1, !move_base_step!) do (
echo 无流量 往上滑动  D1  move_base_step=!move_base_step!
adb shell input swipe  1050 600 1050 900  200
rem )
echo 无流量 往上滑动  D2  move_base_step=!move_base_step!
adb shell input swipe  1050 600 1050 900  200
ping -n 1 127.0.0.1>nul
call :getrandomintwithmaxmin_func_2x1  0 100
set randomInt_A=!getrandomintwithmaxmin_return_1!
echo 无流量 往上滑动  D3   randomInt_A=[!randomInt_A!] 
if !randomInt_A! GEQ 50 (
adb shell input swipe  1050 600 1050 900  200
ping -n 1 127.0.0.1>nul
)
)
echo __________________ Loop_Index[%%i] Endxx  _______________________
)
echo [rule19vdownloadshoucangjia_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule19vdownloadshoucangjia_func_0x0
::ENDLOCAL
goto:eof




:rule20vrenamewithtype_func_2x0
rem ======================================== rule20vrenamewithtype_func_2x0
rem rule_tip: %init_input_0% _20_      ## 把当前目录的 media媒体文件 jpg_jpeg_webp_mp4_avi_gif_mp3_txt 改成 数字.类型 形式

rem rule_tip: %init_input_0% _20_   jpg   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

rem rule_tip: %init_input_0% _20_   png   ## 已经当前输入的类型去对匹配的文件改名 改为  数字_.类型 形式

rem rule_tip: %init_input_0% _20_   gif    ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

rem rule_tip: %init_input_0% _20_   mp4   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

rem rule_tip: %init_input_0% _20_   webp   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

rem rule_tip: %init_input_0% _20_   jpg timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

rem rule_tip: %init_input_0% _20_   png timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

rem rule_tip: %init_input_0% _20_   gif timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

rem rule_tip: %init_input_0% _20_   mp4 timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

rem rule_tip: %init_input_0% _20_   webp timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule20vrenamewithtype_func_2x0
set rule20vrenamewithtype_dynamic_param1=
set rule20vrenamewithtype_dynamic_param2=
if "%init_input_2%"=="" (
echo init_input_2=null 
) else (
rem set rule4vscreendown_dynamic_param1=%init_input_2% 
echo init_input_2=%rule4vscreendown_dynamic_param1% 
)
rem   !init_cd! 
set /a n=0
set  fileName_timestamp_pre=
if "%init_input_3%"=="timestamp" (
call :gettimehhmmsss_func_0x1
set CURRENT_DATE_TIME_STAMP=!gettimehhmmsss_return_1!
set  fileName_timestamp_pre=_!CURRENT_DATE_TIME_STAMP!
echo CURRENT_DATE_TIME_STAMP=!CURRENT_DATE_TIME_STAMP!
echo fileName_timestamp_pre=!fileName_timestamp_pre!
) 
if "%init_input_2%"=="" (
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.jpg"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[jpg]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.png"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[png]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.gif"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[gif]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.jpeg"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[jpeg]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.bmp"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[bmp]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.webp"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[webp]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.mp4"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[mp4]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
del zzZZzz.txt
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.txt"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[txt]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.mp3"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[mp3]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
set /a n=0
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.avi"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!!fileName_timestamp_pre!%%~xi
    echo 完全匹配[avi]源文件:%%i 重命名为:!newfileName!
    ren "%%i" "!newfileName!"
)
) else (
call :clearStringPadding_func_1x1  %init_input_2%
set rule4vscreendown_dynamic_param1=!clearStringPadding_return_1!
echo rule4vscreendown_dynamic_param1=[!rule4vscreendown_dynamic_param1!] [!clearStringPadding_return_1!]
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!rule4vscreendown_dynamic_param1!"') do (
    set /a n+=1
    set number=!n!
    set newfileName=!n!%fileName_timestamp_pre%.!rule4vscreendown_dynamic_param1!
    echo 输入类型[!rule4vscreendown_dynamic_param1!]匹配源文件:[%%i] 重命名为:[!newfileName!]
    ren "%%i" "!newfileName!"
)
)
echo [rule20vrenamewithtype_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule20vrenamewithtype_dynamic_param1!]   dynamic_param2=[!rule20vrenamewithtype_dynamic_param2!]   
echo ______________Method_Out rule20vrenamewithtype_func_2x0
::ENDLOCAL
goto:eof




:rule21vshowfilemd_func_2x0
rem ======================================== rule21vshowfilemd_func_2x0
rem rule_tip: %init_input_0% _21_   ## 查看当前目录下所有文件的 MD属性

rem rule_tip: %init_input_0% _21_ mp4  ## 查看当前目录下所有 mp4文件的MD属性

rem rule_tip: %init_input_0% _21_ jpg   ## 查看当前目录下所有 jpg文件的MD属性

rem rule_tip: %init_input_0% _21_ gif  ## 查看当前目录下所有 gif文件的MD属性

rem rule_tip: %init_input_0% _21_ mdname_true    ## 查看当前目录下所有文件的 MD属性  并把当前名字改为md值

rem rule_tip: %init_input_0% _21_ mp4 mdname_true ## 查看当前目录下所有 mp4文件的MD属性  并把当前名字改为md值

rem rule_tip: %init_input_0% _21_ jpg mdname_true ## 查看当前目录下所有 gif文件的MD属性 并把当前名字改为md值

rem rule_tip: %init_input_0% _21_ gif mdname_true ## 查看当前目录下所有 gif文件的MD属性 并把当前名字改为md值
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule21vshowfilemd_func_2x0
set rule21vshowfilemd_dynamic_param1=
set rule21vshowfilemd_dynamic_param2=
set rule21vshowfilemd_dynamic_param1=%init_input_2%
set rule21vshowfilemd_dynamic_param2=%init_input_3%
set /a n=0
if "%init_input_2%"=="" (
echo init_input_2=null 
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.*"') do (
    set /a n+=1
    echo 全类型源文件[!n!][%%i] MD属性信息如下: command [ certutil -hashfile "%%i"  MD5 ]
	certutil -hashfile  "%%i"  MD5  | findstr /V "CertUtil MD5 "
	echo=
)
echo [rule21vshowfilemd_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule21vshowfilemd_dynamic_param1!]   dynamic_param2=[!rule21vshowfilemd_dynamic_param2!]   
echo ______________Method_Out rule21vshowfilemd_func_2x0
goto:eof
) else if not "%init_input_2%"=="mdname_true" (
if "%init_input_3%"=="" (
echo init_input_2=%init_input_2%
set rule21vshowfilemd_dynamic_param1=%init_input_2%
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!rule21vshowfilemd_dynamic_param1!"') do (
    set /a n+=1
    echo !rule21vshowfilemd_dynamic_param1!]类型源文件[!n!][%%i] MD属性信息如下: command [ certutil -hashfile "%%i"  MD5 ]
	certutil -hashfile  "%%i"  MD5  | findstr /V "CertUtil MD5 "
	echo=
)
echo [rule21vshowfilemd_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule21vshowfilemd_dynamic_param1!]   dynamic_param2=[!rule21vshowfilemd_dynamic_param2!]   
echo ______________Method_Out rule21vshowfilemd_func_2x0
goto:eof
)
)
if not "%init_input_2%"=="" (
if "%init_input_2%"=="mdname_true" (
del zbatrule_21.txt
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.*"') do (
    set /a n+=1
	call ::getfilex_func_1x1  !init_cd!\%%i
    set cur_file_type=!getfilex_return_1!
	echo ____全类型源文件[!n!][%%i] MD属性信息如下: command [ certutil -hashfile "%%i"  MD5 ]
	certutil -hashfile  "%%i"  MD5  | findstr /V "CertUtil MD5 " > zbatrule_21.txt
	set /p md5str=<zbatrule_21.txt
	echo md5str=!md5str!
	echo type=!cur_file_type!
	ren "%%i" "!md5str!!cur_file_type!"
	echo ____全类型源文件[!n!][%%i]改为MD5名称命名文件[!md5str!!cur_file_type!] 成功
)
del zbatrule_21.txt
echo [rule21vshowfilemd_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule21vshowfilemd_dynamic_param1!]   dynamic_param2=[!rule21vshowfilemd_dynamic_param2!]   
echo ______________Method_Out rule21vshowfilemd_func_2x0
goto:eof
) else if "%init_input_3%"=="mdname_true" (
del zbatrule_21.txt
echo init_input_2=%init_input_2%
set rule21vshowfilemd_dynamic_param1=%init_input_2%
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!rule21vshowfilemd_dynamic_param1!"') do (
    set /a n+=1
	call ::getfilex_func_1x1  !init_cd!\%%i
    set cur_file_type=!getfilex_return_1!
    echo !rule21vshowfilemd_dynamic_param1! ]类型源文件[!n!][%%i] MD属性信息如下: command [ certutil -hashfile "%%i"  MD5 ]
	certutil -hashfile  "%%i"  MD5  | findstr /V "CertUtil MD5 "  > zbatrule_21.txt
	set /p md5str=<zbatrule_21.txt
	echo md5str=!md5str!
	echo type=!cur_file_type!
	ren "%%i" "!md5str!!cur_file_type!"
	echo !rule21vshowfilemd_dynamic_param1! 类型源文件[!n!][%%i]改为MD5名称命名文件[!md5str!!cur_file_type!] 成功
)
del zbatrule_21.txt
echo [rule21vshowfilemd_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule21vshowfilemd_dynamic_param1!]   dynamic_param2=[!rule21vshowfilemd_dynamic_param2!]   
echo ______________Method_Out rule21vshowfilemd_func_2x0
goto:eof
)
)   
echo [rule21vshowfilemd_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule21vshowfilemd_dynamic_param1!]   dynamic_param2=[!rule21vshowfilemd_dynamic_param2!]   
echo ______________Method_Out rule21vshowfilemd_func_2x0
::ENDLOCAL
goto:eof




:rule22vfastopenexe_func_1x0
rem ======================================== rule22vfastopenexe_func_1x0
rem rule_tip: %init_input_0% _22_  control      ##  start control.exe  快速打开控制面板

rem rule_tip: %init_input_0% _22_  regedit      ##  start regedit.exe  快速打开注册表

rem rule_tip: %init_input_0% _22_  dxdiag       ##  start  dxdiag.exe  快速打开 DirectX信息面板

rem rule_tip: %init_input_0% _22_  mstsc        ##  start  mstsc.exe  快速打开 远程桌面连接窗口

rem rule_tip: %init_input_0% _22_  ncpa.cpl     ##  start control ncpa.cpl   快速打开本地连接页面

rem rule_tip: %init_input_0% _22_  gpedit.msc   ##  start gpedit.msc  快速打开组策略

rem rule_tip: %init_input_0% _22_  compmgmt.msc   ##  start  compmgmt.msc  快速打开计算机管理

rem rule_tip: %init_input_0% _22_  secpol.msc   ##  start  secpol.msc  快速打开本地安全策略

rem rule_tip: %init_input_0% _22_  cmd   ##    管理员权限打开CMD_初始路径D盘 【Win+X+Shift+A】 打开管理员CMD的命快键_依次点击

rem rule_tip: %init_input_0% _22_   showfiletype  ##   显示文件的扩展名

rem rule_tip: %init_input_0% _22_   showhiddenfile  ##   显示隐藏文件夹 文件

rem rule_tip: %init_input_0% _22_   wifi        ##  显示wifi信息
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule22vfastopenexe_func_1x0
set rule22vfastopenexe_dynamic_param1=
if "%init_input_2%"=="control" (
start control.exe
)
if "%init_input_2%"=="dxdiag" (
start dxdiag.exe
)
if "%init_input_2%"=="mstsc" (
start mstsc.exe
)
if "%init_input_2%"=="ncpa.cpl" (
start control ncpa.cpl
)
if "%init_input_2%"=="gpedit.msc" (
start gpedit.msc
)
if "%init_input_2%"=="compmgmt.msc" (
start compmgmt.msc
)
if "%init_input_2%"=="cmd" (
del zcmd_admin_I9.bat
echo @ECHO off >> zcmd_admin_I9.bat  
echo setlocal enabledelayedexpansion  >> zcmd_admin_I9.bat  
rem set local_str=^%1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c ^%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit
echo ^%%^1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c %%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit  >> zcmd_admin_I9.bat 
echo start cmd /K D: >> zcmd_admin_I9.bat  
echo start cmd /K "cd /d D:/zsoft/"  >> zcmd_admin_I9.bat  
start zcmd_admin_I9.bat  
rem start %win_zbin%/zcmd_admin_I9.bat  
rem del  zcmd_admin_I9.bat  
)
if "%init_input_2%"=="regedit" (
start regedit.exe
)
if "%init_input_2%"=="showfiletype" (
echo 显示文件的扩展名
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v HideFileExt /t reg_dword /d 00000000 /f
)
if "%init_input_2%"=="showhiddenfile" (
echo 显示隐藏文件
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v Hidden /t reg_dword /d 1 /f
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v HideFileExt /t reg_dword /d 0 /f
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\Advanced" /v ShowSuperHidden /t reg_dword /d 1 /f
)
if "%init_input_2%"=="wifi" (
echo 显示WIFI
del showwifi.txt
for /f "skip=9 tokens=1,2 delims=:" %%i in ('netsh wlan show profiles') do  @echo %%j | findstr -i -v echo | netsh wlan show profiles %%j key=clear >> "showwifi.txt"
echo ___________________ WIFI Info Begin ___________________
type showwifi.txt  | findstr  "SSID.name Key"
echo ___________________ WIFI Info End ___________________
rem del  showwifi.txt
)
echo [rule22vfastopenexe_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule22vfastopenexe_dynamic_param1!]   
echo ______________Method_Out rule22vfastopenexe_func_1x0
::ENDLOCAL
goto:eof




:rule23vfiletypechange_func_1x0
rem ======================================== rule23vfiletypechange_func_1x0
rem rule_tip: %init_input_0% _23_  png_jpg  ##  更改当前文件的类型  原类型_目标类型  png_jpg

rem rule_tip: %init_input_0% _23_  _jpg  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 jpg 类型

rem rule_tip: %init_input_0% _23_  jpg_  ##  更改当前文件的类型  原类型_目标类型    jpg 类型 转为  无类型

rem rule_tip: %init_input_0% _23_  _png  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 png 类型

rem rule_tip: %init_input_0% _23_  png_  ##  更改当前文件的类型  原类型_目标类型    png 类型 转为  无类型

rem rule_tip: %init_input_0% _23_  _webp  ##  更改当前文件的类型  原类型_目标类型   无类型 转为 webp 类型

rem rule_tip: %init_input_0% _23_  webp_  ##  更改当前文件的类型  原类型_目标类型    webp 类型 转为  无类型

rem rule_tip: %init_input_0% _23_  _gif  ##  更改当前文件的类型  原类型_目标类型   无类型 转为 gif 类型

rem rule_tip: %init_input_0% _23_  gif_  ##  更改当前文件的类型  原类型_目标类型    gif 类型 转为  无类型

rem rule_tip: %init_input_0% _23_  _mp4  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 mp4 类型

rem rule_tip: %init_input_0% _23_  mp4_  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 mp4 类型

rem desc: 把当前目录中的原类型 转为 目标类型
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule23vfiletypechange_func_1x0
set rule23vfiletypechange_dynamic_param1=
set /a n=0
if "%init_input_2%"=="" (
echo 当前用户输入为空 无法执行规则 _23_ 去更改当前文件的类型  原类型_目标类型 例: png_jpg
echo [rule23vfiletypechange_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule23vfiletypechange_dynamic_param1!]   
echo ______________Method_Out rule23vfiletypechange_func_1x0
goto:eof
) else (
set rule23vfiletypechange_dynamic_param1=%init_input_2% 
echo rule23vfiletypechange_dynamic_param1=!rule23vfiletypechange_dynamic_param1!
set isContainString_return_1=
call :isContainString_func_2x1  !rule23vfiletypechange_dynamic_param1!  _
echo isContainString_return_1=!isContainString_return_1!
if "!isContainString_return_1!"=="false" (
echo 当前输入参数[ %init_input_2%  ]  没有包含 类型分隔符 [ _ ]   执行程序失败  示例:  png_jpg    mp4_  _mp4  jpg_   _jpg  
echo [rule23vfiletypechange_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule23vfiletypechange_dynamic_param1!]   
echo ______________Method_Out rule23vfiletypechange_func_1x0
GOTO:EOF
)
)
echo 用户输入有效类型参数  [ !rule23vfiletypechange_dynamic_param1! ]
set src_type_str=
set dst_type_str=
set getSubStringWithPre_return_1=
call :getSubStringWithPre_func_2x1 !rule23vfiletypechange_dynamic_param1!  _
echo getSubStringWithPre_return_1=!getSubStringWithPre_return_1!
set getSubStringWithEnd_return_1=
call :getSubStringWithEnd_func_2x1 !rule23vfiletypechange_dynamic_param1!  _
echo getSubStringWithEnd_return_1=!getSubStringWithEnd_return_1!
set src_type_str=!getSubStringWithEnd_return_1!
set dst_type_str=!getSubStringWithPre_return_1!
echo src_type_str=[ !src_type_str! ]   dst_type_str=[ !dst_type_str! ]
if "!src_type_str!"=="" (
echo 过滤没有类型的文件  转为 [ !dst_type_str! ] 的文件
set dest_file_type=
if "!dst_type_str!"=="" (
set dest_file_type=
) else (
set dest_file_type=.!dst_type_str!
)
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.*"') do (
    set /a n+=1
	rem call ::showfile_func_1x0  !init_cd!\%%i
	rem  ~x= 
	call ::getfilex_func_1x1  !init_cd!\%%i
	set cur_file_type=!getfilex_return_1!
	if "!cur_file_type!"=="" (
	echo ____无类型源文件[!n!][%%i] 文件信息如下:  ~x=!cur_file_type!
		call :getfilenamenopointwithfullpath_func_1x1 %%i
set filename_no_type=!getfilenamenopointwithfullpath_return_1!
set filename_with_type=!getfilenamenopointwithfullpath_return_1!!dest_file_type!
		 ren "!init_cd!\%%i" "!filename_with_type!"
	echo=
	)
)
echo [rule23vfiletypechange_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule23vfiletypechange_dynamic_param1!]   
echo ______________Method_Out rule23vfiletypechange_func_1x0
goto:eof
)
set dest_file_type=
if "!dst_type_str!"=="" (
set dest_file_type=
) else (
set dest_file_type=.!dst_type_str!
)
echo 过滤 [ !src_type_str! ] 类型的文件 转为 [ !dst_type_str! ] 追尾[ !dest_file_type! ]的文件
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!src_type_str!"') do (
    set /a n+=1
	call :getfilenamenopointwithfullpath_func_1x1 %%i
set filename_no_type=!getfilenamenopointwithfullpath_return_1!
set filename_with_type=!getfilenamenopointwithfullpath_return_1!!dest_file_type!
    echo 匹配[ !src_type_str! ]类型源文件[!n!][%%i] 转为 [ !filename_with_type! ]
	echo command [  ren "!init_cd!\%%i" "!filename_with_type!"  ]
	 ren "!init_cd!\%%i" "!filename_with_type!"
    echo=
)
echo rule23vfiletypechange_dynamic_param1=%init_input_2%
echo [rule23vfiletypechange_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule23vfiletypechange_dynamic_param1!]   
echo ______________Method_Out rule23vfiletypechange_func_1x0
::ENDLOCAL
goto:eof




:rule24vfilenamewithpreend_func_3x0
rem ======================================== rule24vfilenamewithpreend_func_3x0
rem rule_tip: %init_input_0% _24_ pre_temp_  end__end    ##  对当前目录的所有实体文件名称 加上前缀 temp_ 后缀加上 _end

rem rule_tip: %init_input_0% _24_ pre_temp_  end_2020  jpg  ##  对当前目录的jpg文件名称 加上前缀 temp_ 后缀加上 2020

rem rule_tip: %init_input_0% _24_ pre_2021  end_base  mp4  ##  对当前目录的mp4文件名称 加上前缀 2021 后缀加上 base

rem desc: %init_input_0% _24_  pre_2021  end_base  mp4  对当前目录的mp4文件名称 加上前缀 2021 后缀加上 base
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule24vfilenamewithpreend_func_3x0
set rule24vfilenamewithpreend_dynamic_param1=
set rule24vfilenamewithpreend_dynamic_param2=
set rule24vfilenamewithpreend_dynamic_param3=
set prestr=
set endstr=
set filetypestr=*
if "%init_input_2%"=="" (
echo pre_ 前缀没有输入
) else (
set rule24vfilenamewithpreend_dynamic_param1=%init_input_2%
call :isstartwith_func_2x1 %init_input_2%  pre_ 
set local_isstartwith_return_1=!isstartwith_return_1!
if "!local_isstartwith_return_1!"=="true" (
call :stringreplace_func_3x1 %init_input_2%  pre_  ""
echo stringreplace_return_1=!stringreplace_return_1!
set prestr=!stringreplace_return_1!
echo  输入的第一个参数是 pre_开头   值为  prestr=!prestr!
) else (
echo  输入的第一个参数不是 pre_开头   将默认pre_为空  prestr=
set prestr=
)
)
if "%init_input_3%"=="" (
echo end_ 后缀没有输入
) else (
set rule24vfilenamewithpreend_dynamic_param2=%init_input_3%
call :isstartwith_func_2x1 %init_input_3%  end_ 
set local_isstartwith_return_1=!isstartwith_return_1!
if "!local_isstartwith_return_1!"=="true" (
call :stringreplace_func_3x1 %init_input_3%  end_  ""
echo stringreplace_return_1=!stringreplace_return_1!
set endstr=!stringreplace_return_1!
echo  输入的第一个参数是 end_ 开头   值为  endstr=!endstr!
) else (
echo  输入的第一个参数不是 end_ 开头  将默认 end_为空  endstr=
set endstr=
)
)
if "%init_input_4%"=="" (
echo 第三个参数 类型输入为空  默认类型为 filetypestr=*
) else (
set rule24vfilenamewithpreend_dynamic_param2=%init_input_4%
set filetypestr=%init_input_4%
)
echo prestr[ !prestr! ]_________endstr[ !endstr! ]_________filetype[ !filetypestr! ]   
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!filetypestr!"') do (
    set /a n+=1
	call ::getfilex_func_1x1  !init_cd!\%%i
    set cur_file_type=!getfilex_return_1!
	echo init_cd/i = !init_cd!\%%i
	call ::getFileNameNoPointWithFullPath_func_1x1 !init_cd!\%%i
	set cur_file_name=!getFileNameNoPointWithFullPath_return_1!
	set cur_file_new_name=!prestr!!getFileNameNoPointWithFullPath_return_1!!endstr!!getfilex_return_1!
	echo cur_file_new_name=[ !cur_file_new_name! ]
	echo ____类型[ !filetypestr! ]源文件[!n!][%%i] 新名字[ !cur_file_new_name! ]
	echo command[  ren "%%i" "!cur_file_new_name!"  ] 
	ren "%%i" "!cur_file_new_name!"
)
echo [rule24vfilenamewithpreend_func_3x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule24vfilenamewithpreend_dynamic_param1!]   dynamic_param2=[!rule24vfilenamewithpreend_dynamic_param2!]   dynamic_param3=[!rule24vfilenamewithpreend_dynamic_param3!]   
echo ______________Method_Out rule24vfilenamewithpreend_func_3x0
::ENDLOCAL
goto:eof




:rule25vfilenamereplace_func_2x0
rem ======================================== rule25vfilenamereplace_func_2x0
rem rule_tip: %init_input_0% _25_  123_AAA  ##  更改当前文件名称 把 123 替换为 AAA

rem rule_tip: %init_input_0% _25_  123_AAA  jpg ##  更改当前目录的 jpg 文件名称中 把 123 替换为 AAA

rem desc: 把当前目录中的原文件名称 替换字符串 转为新的文件名称
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule25vfilenamereplace_func_2x0
set rule25vfilenamereplace_dynamic_param1=
set rule25vfilenamereplace_dynamic_param2=
set /a n=0
if "%init_input_2%"=="" (
echo 当前用户输入为空 无法执行规则 _25_ 去更改当前文件的类型  原名称_目标名称 例: 123_AAA
echo [rule25vfilenamereplace_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule25vfilenamereplace_dynamic_param1!]   dynamic_param2=[!rule25vfilenamereplace_dynamic_param2!]   
echo ______________Method_Out rule25vfilenamereplace_func_2x0
goto:eof
) else (
set rule25vfilenamereplace_dynamic_param1=%init_input_2% 
echo rule25vfilenamereplace_dynamic_param1=!rule25vfilenamereplace_dynamic_param1!
set isContainString_return_1=
call :isContainString_func_2x1  !rule25vfilenamereplace_dynamic_param1!  _
echo isContainString_return_1=!isContainString_return_1!
if "!isContainString_return_1!"=="false" (
echo 当前输入参数[ %init_input_2%  ]  没有包含 类型分隔符 [ _ ]   执行程序失败  示例:  123_AAA  456_BBB  原始名称_替换名称 
echo [rule25vfilenamereplace_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule25vfilenamereplace_dynamic_param1!]   dynamic_param2=[!rule25vfilenamereplace_dynamic_param2!]   
echo ______________Method_Out rule25vfilenamereplace_func_2x0
GOTO:EOF
)
)
echo 用户输入有效类型参数  [ !rule25vfilenamereplace_dynamic_param1! ]
set src_name_str=
set dst_name_str=
set getSubStringWithPre_return_1=
call :getSubStringWithPre_func_2x1 !rule25vfilenamereplace_dynamic_param1!  _
echo getSubStringWithPre_return_1=!getSubStringWithPre_return_1!
set getSubStringWithEnd_return_1=
call :getSubStringWithEnd_func_2x1 !rule25vfilenamereplace_dynamic_param1!  _
echo getSubStringWithEnd_return_1=!getSubStringWithEnd_return_1!
set src_name_str=!getSubStringWithEnd_return_1!
set dst_name_str=!getSubStringWithPre_return_1!
echo src_name_str[ !src_name_str! ]__________dst_name_str[ !dst_name_str! ]
if "!src_name_str!"=="" (
echo 用户输入类似  _!dst_name_str!  的字符串, 没有输入 需要替换的字符串  请检查输入参数 重新执行
echo [rule25vfilenamereplace_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule25vfilenamereplace_dynamic_param1!]   dynamic_param2=[!rule25vfilenamereplace_dynamic_param2!]   
echo ______________Method_Out rule25vfilenamereplace_func_2x0
GOTO:EOF
)
set filetypestr=* 
if "%init_input_3%"=="" (
echo 第二个参数 类型输入为空  默认类型为当前目录下全部实体文件 filetypestr=*
) else (
set rule24vfilenamewithpreend_dynamic_param2=%init_input_3%
set filetypestr=%init_input_3%
)
echo  src_name_str[ !src_name_str! ]________dst_name_str[ !dst_name_str! ]_______filetypestr[ !filetypestr! ]
for /f "delims=\" %%i in ('dir /b /a-d /o-d "!init_cd!\*.!filetypestr!"') do (
    set /a n+=1
	rem call ::showfile_func_1x0  !init_cd!\%%i
	rem  ~x= 
	call ::getfilex_func_1x1  !init_cd!\%%i
	set cur_file_type=!getfilex_return_1!
	call ::getfilenamenopointwithfullpath_func_1x1 %%i
    set filename_no_type=!getfilenamenopointwithfullpath_return_1!
    set old_name_str=!getfilenamenopointwithfullpath_return_1!!cur_file_type!
	call ::stringreplace_func_3x1 !filename_no_type!  "!src_name_str!"  "!dst_name_str!"
	set new_name_str=!stringreplace_return_1!!cur_file_type!
	echo oldname[ !old_name_str! ]________newname[ !new_name_str! ]
	if not "!old_name_str!"=="!new_name_str!" (
		echo ____类型[ !cur_file_type! ]源文件[!n!][%%i] 新名字[ !new_name_str! ] 执行改名操作
		ren "!init_cd!\%%i" "!new_name_str!"
	)
)
echo [rule25vfilenamereplace_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule25vfilenamereplace_dynamic_param1!]   dynamic_param2=[!rule25vfilenamereplace_dynamic_param2!]   
echo ______________Method_Out rule25vfilenamereplace_func_2x0
::ENDLOCAL
goto:eof




:rule26vmakedirwithtemplate_func_1x0
rem ======================================== rule26vmakedirwithtemplate_func_1x0
rem rule_tip: %init_input_0% _26_  mkdir_zapp  ##  在当前目录创建 zapp 对应的 目录

rem rule_tip: %init_input_0% _26_  mkdir_zmain  ##  在当前目录创建 zmain 对应的 目录

rem rule_tip: %init_input_0% _26_  mkdir_yymmdd_1970zvi  ##  在当前目录创建 temp_media 对应日期的 1970zvi 目录

rem desc: 给定一个 标识符 如-mkdir_zapp 创建符合这个标识符的文件夹列表
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule26vmakedirwithtemplate_func_1x0
set rule26vmakedirwithtemplate_dynamic_param1=
if "%init_input_2%"=="mkdir_zapp" (
set rule26vmakedirwithtemplate_dynamic_param1=%init_input_2%
mkdir jpg_common_port
mkdir jpg_common_land
mkdir jpg_top_land
mkdir jpg_top_port
mkdir jpg_girl_port
mkdir jpg_girl_land
mkdir jpg_lin_port
mkdir jpg_lin_land
mkdir gif_common_land
mkdir gif_common_port
mkdir gif_top_port
mkdir gif_top_land
mkdir mp4_common_land
mkdir mp4_common_port
mkdir mp4_top_port
mkdir mp4_top_land
mkdir mp4_single_port
mkdir mp4_single_land
mkdir mp4_raw_port
mkdir mp4_raw_land
mkdir mp4_hua_port
mkdir mp4_hua_land
mkdir mp4_dan_port
mkdir mp4_dan_land
mkdir mp4_sss_port
mkdir mp4_sss_land
mkdir mp4_anim_port
mkdir mp4_anim_land
mkdir mp4_toto_port
mkdir mp4_toto_land
mkdir mp4_fake_port
mkdir mp4_fake_land
)
if "%init_input_2%"=="mkdir_zmain" (
set rule26vmakedirwithtemplate_dynamic_param1=%init_input_2%
mkdir jpg_common_port
mkdir jpg_common_land
mkdir jpg_gaokao_land
mkdir jpg_kaoyan_land
mkdir jpg_scene_port
mkdir jpg_scene_land
mkdir jpg_home_port
mkdir jpg_home_land
mkdir gif_common_land
mkdir gif_common_port
mkdir gif
mkdir mp4_common_land
mkdir mp4_common_port
mkdir mp4_home_port
mkdir mp4_home_land
mkdir mp4_scene_port
mkdir mp4_scene_land
mkdir mp4_music_port
mkdir mp4_music_land
mkdir mp4_kaoyan_land
mkdir mp4_gaokao_land
mkdir mp3
)
if "%init_input_2%"=="mkdir_yymmdd_1970zvi" (
set rule26vmakedirwithtemplate_dynamic_param1=%init_input_2%
call :gettimemmddhhmm_func_0x1
set mmdd_hhmm_timestr=!gettimemmddhhmm_return_1!
echo mmdd_hhmm_timestr=!mmdd_hhmm_timestr!  gettimemmddhhmm_return_1=!gettimemmddhhmm_return_1!
echo mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_common_port
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_common_port
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_common_land
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_top_land
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_top_port
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_girl_port
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_girl_land
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_lin_port
mkdir .\!mmdd_hhmm_timestr!\zapp\jpg_lin_land
mkdir .\!mmdd_hhmm_timestr!\zapp\gif_common_land
mkdir .\!mmdd_hhmm_timestr!\zapp\gif_common_port
mkdir .\!mmdd_hhmm_timestr!\zapp\gif_top_port
mkdir .\!mmdd_hhmm_timestr!\zapp\gif_top_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_common_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_common_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_top_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_top_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_single_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_single_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_raw_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_raw_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_hua_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_hua_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_dan_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_dan_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_sss_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_sss_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_anim_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_anim_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_toto_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_toto_land
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_fake_port
mkdir .\!mmdd_hhmm_timestr!\zapp\mp4_fake_land
mkdir .\!mmdd_hhmm_timestr!\z_sss\animation
mkdir .\!mmdd_hhmm_timestr!\z_sss\bcxs
mkdir .\!mmdd_hhmm_timestr!\z_sss\bdyjy
mkdir .\!mmdd_hhmm_timestr!\z_sss\bsllx
mkdir .\!mmdd_hhmm_timestr!\z_sss\cjmy
mkdir .\!mmdd_hhmm_timestr!\z_sss\fakeface
mkdir .\!mmdd_hhmm_timestr!\z_sss\foreign
mkdir .\!mmdd_hhmm_timestr!\z_sss\gcyz
mkdir .\!mmdd_hhmm_timestr!\z_sss\hmh
mkdir .\!mmdd_hhmm_timestr!\z_sss\korean_land
mkdir .\!mmdd_hhmm_timestr!\z_sss\korean_port
mkdir .\!mmdd_hhmm_timestr!\z_sss\mly
mkdir .\!mmdd_hhmm_timestr!\z_sss\other
mkdir .\!mmdd_hhmm_timestr!\z_sss\qglxc
mkdir .\!mmdd_hhmm_timestr!\z_sss\qlln
mkdir .\!mmdd_hhmm_timestr!\z_sss\qsll
mkdir .\!mmdd_hhmm_timestr!\z_sss\ssyy
mkdir .\!mmdd_hhmm_timestr!\z_sss\stym
mkdir .\!mmdd_hhmm_timestr!\z_sss\tandan_land
mkdir .\!mmdd_hhmm_timestr!\z_sss\tandan_port
mkdir .\!mmdd_hhmm_timestr!\z_sss\tanhua_land
mkdir .\!mmdd_hhmm_timestr!\z_sss\tanhua_port
mkdir .\!mmdd_hhmm_timestr!\z_sss\thy
mkdir .\!mmdd_hhmm_timestr!\z_sss\xnnm
mkdir .\!mmdd_hhmm_timestr!\z_sss\xqkjx
mkdir .\!mmdd_hhmm_timestr!\z_sss\yml
mkdir .\!mmdd_hhmm_timestr!\z_sss\yyzzz
mkdir .\!mmdd_hhmm_timestr!\z_sss\zyw
mkdir .\!mmdd_hhmm_timestr!\z_sss\zz_unknow_land
mkdir .\!mmdd_hhmm_timestr!\z_sss\zz_unknow_port
)
echo rule26vmakedirwithtemplate_dynamic_param1=%init_input_2%
echo [rule26vmakedirwithtemplate_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule26vmakedirwithtemplate_dynamic_param1!]   
echo ______________Method_Out rule26vmakedirwithtemplate_func_1x0
::ENDLOCAL
goto:eof




:rule27vwirelessadbconnect_func_4x0
rem ======================================== rule27vwirelessadbconnect_func_4x0
rem rule_tip: %init_input_0% _27_   192ipend3_115  ipport_44971  paircode_300827 pairport_43173     ## 对当前输入的 192ipend3(IP地址最后三位) 192.168.0.115 的 安卓设置进行无线连接

rem rule_tip: %init_input_0% _27_   10ipend3_115  ipport_44971  paircode_300827 pairport_43173     ## 对当前输入的 10ipend3(IP地址最后三位) 10.106.20.115 的 安卓设置进行无线连接

rem rule_tip: %init_input_0% _27_   10ipend3_  ipport_  paircode_ pairport_     ## 对当前输入的 10ipend3(IP地址最后三位) 10.106.20.115 的 安卓设置进行无线连接

rem rule_tip: %init_input_0% _27_   192ipend3_  ipport_  paircode_ pairport_     ## 对当前输入的 192ipend3(IP地址最后三位) 192.168.0.115 的 安卓设置进行无线连接

rem desc: 对无线安卓调试设备进行无线连接adb操作
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule27vwirelessadbconnect_func_4x0
set rule27vwirelessadbconnect_dynamic_param1=
set rule27vwirelessadbconnect_dynamic_param2=
set rule27vwirelessadbconnect_dynamic_param3=
set rule27vwirelessadbconnect_dynamic_param4=
set rule27vwirelessadbconnect_dynamic_param1=%init_input_2% 
set rule27vwirelessadbconnect_dynamic_param2=%init_input_3% 
set rule27vwirelessadbconnect_dynamic_param3=%init_input_4% 
set rule27vwirelessadbconnect_dynamic_param4=%init_input_5% 
rem Param1______192ipend3_115        Param1______10ipend3_115
set ipaddress=
set ipaddress_pre=192.168.0.
set ipaddress_end3_number=
call :isstartwith_func_2x1 %init_input_2%  192ipend3_ 
set is192ipaddress=!isstartwith_return_1!
if "!is192ipaddress!"=="true" (
set ipaddress_pre=192.168.0.
call :stringreplace_func_3x1 %init_input_2%  192ipend3_  ""
set ipaddress_end3_number=!stringreplace_return_1!
) else (
call :isstartwith_func_2x1 %init_input_2%  10ipend3_ 
set is10ipaddress=!isstartwith_return_1!
if "!is10ipaddress!"=="true" (
set ipaddress_pre=10.106.20.
call :stringreplace_func_3x1 %init_input_2%  10ipend3_  ""
set ipaddress_end3_number=!stringreplace_return_1!
)
)
echo ipaddress_pre=!ipaddress_pre!
echo ipaddress_end3_number=!ipaddress_end3_number!
set ipaddress=!ipaddress_pre!!ipaddress_end3_number!
echo ipaddress=!ipaddress!
rem Param2______ipport_44971      
set ipport=
call :stringreplace_func_3x1 %init_input_3%  ipport_  ""
set ipport=!stringreplace_return_1!
echo ipaddress=!ipaddress!  ipport=!ipport!
rem Param3______paircode_300827
set paircode=
call :stringreplace_func_3x1 %init_input_4%  paircode_  ""
set paircode=!stringreplace_return_1!
echo ipaddress=!ipaddress!  ipport=!ipport!  paircode=!paircode!
rem Param4______pairport_43173
set pairport=
call :stringreplace_func_3x1 %init_input_5%  pairport_  ""
set pairport=!stringreplace_return_1!
echo ipaddress=!ipaddress!  ipport=!ipport!  paircode=!paircode!  pairport=!pairport!
echo=
echo _____________connect command Begin_____________
rem echo adb kill-server ^&^& adb pair !ipaddress!^:!pairport!  !paircode!  ^&^&  adb connect !ipaddress!^:!ipport! ^&^&  adb -s !ipaddress!^:!ipport! shell 
echo adb pair !ipaddress!^:!pairport!  !paircode!  ^&^&  adb connect !ipaddress!^:!ipport! ^&^&  adb -s !ipaddress!^:!ipport! shell 
echo _____________connect command End_____________
rem adb kill-server && adb pair !ipaddress!:!pairport!  !paircode!  &&  adb connect !ipaddress!:!ipport! &&  adb -s !ipaddress!:!ipport! shell 
adb pair !ipaddress!:!pairport!  !paircode!  &&  adb connect !ipaddress!:!ipport! &&  adb -s !ipaddress!:!ipport! shell 
echo=
echo rule997vmethodholdplace3_dynamic_param2=%init_input_3%
echo ========================================== Rule_Method_HoldPlace_占位模板 End ===============================
echo [rule27vwirelessadbconnect_func_4x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule27vwirelessadbconnect_dynamic_param1!]   dynamic_param2=[!rule27vwirelessadbconnect_dynamic_param2!]   dynamic_param3=[!rule27vwirelessadbconnect_dynamic_param3!]   dynamic_param4=[!rule27vwirelessadbconnect_dynamic_param4!]   
echo ______________Method_Out rule27vwirelessadbconnect_func_4x0
::ENDLOCAL
goto:eof




:rule28vfiltermediatodir_func_1x0
rem ======================================== rule28vfiltermediatodir_func_1x0
rem rule_tip: %init_input_0% _28_   mediafilter_true  ## 搜索所有(包含孙文件)当前目录下的所有多媒体文件 把它们 统一归类到 一个文件类型的文件夹Z_jpg Z_mp4 Z_gif Z_avi Z_webp 中

rem rule_tip: %init_input_0% _28_   typefilter_xlsx  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[xlsx] 把它们 统一归类到 一个文件类型的文件夹Z_xlsx  文件目录中

rem rule_tip: %init_input_0% _28_   typefilter_txt  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[txt] 把它们 统一归类到 一个文件类型的文件夹Z_txt  文件目录中

rem rule_tip: %init_input_0% _28_   typefilter_java  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[java] 把它们 统一归类到 一个文件类型的文件夹Z_java  文件目录中
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule28vfiltermediatodir_func_1x0
set rule28vfiltermediatodir_dynamic_param1=
set rule28vfiltermediatodir_dynamic_param1=%init_input_2% 
echo  init_input_2=%init_input_2% 
set searchdir=!init_cd!
if "%init_input_2%"=="mediafilter_true" (
set rule28vfiltermediatodir_dynamic_param1=%init_input_2%
set Z_jpg_dirname=Z_jpg
echo  mkdir !Z_jpg_dirname!
mkdir !Z_jpg_dirname!
echo searchdir=%searchdir%   init_cd=!init_cd!
dir /b /a-d /o-d /s "%searchdir%\*.jpg"  > rule28_jpg.txt
dir /b /a-d /o-d /s "%searchdir%\*.png"  > rule28_png.txt
set /a index_jpg = 0
for /f %%x in (rule28_jpg.txt) do (
echo ___________[!index_jpg!_jpg]______________
set jpg_file_fullpath=%%x
set /a index_jpg+=1
set oldname=%%~nx
echo [!index_jpg!_jpg] i=%%x  jpg_file_fullpath=!jpg_file_fullpath! index_jpg=!index_jpg! oldname=!oldname! newfileName_raw=!newfileName_raw! newfileName=!newfileName! xi=%%~xx  ni=%%~nx
set newfileName_raw=!index_jpg!_!oldname!%%~xx
set newfileName=!newfileName_raw: =!
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.jpg  D:\TEMP\0915\A\1_1.jpg && del D:\TEMP\0915\1.jpg 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_dirname!\!newfileName!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_dirname!\!newfileName!  && del  "!itemfullpath!"
)
for /f %%z in (rule28_png.txt) do (
echo ___________[!index_jpg!_png]______________
set png_file_fullpath=%%z
set /a index_jpg+=1
set oldname=%%~nz
set newfileName_jpg_raw=!index_jpg!_!oldname!.jpg
set newfileName_jpg=!newfileName_jpg_raw: =!
echo [!index_jpg!_png]  i=%%z png_file_fullpath=!png_file_fullpath! index_jpg=!index_jpg! oldname=!oldname!   newfileName_jpg_raw=%newfileName_jpg_raw%   newfileName_jpg=!newfileName_jpg! xi=%%~xz  ni=%%~nz
set itemfullpath=%%~sz
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.png  D:\TEMP\0915\A\1_1.png && del D:\TEMP\0915\1.png 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_dirname!\!newfileName!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_dirname!\!newfileName!  && del  "!itemfullpath!"
)
echo del "!Z_jpg_dirname!_!index_jpg!" Begin 
rd /s/q  "!Z_jpg_dirname!_!index_jpg!"
echo del "!Z_jpg_dirname!_!index_jpg!" End
ren "!Z_jpg_dirname!" "!Z_jpg_dirname!_!index_jpg!"
del rule28_jpg.txt
del rule28_png.txt
dir /b /a-d /o-d /s "%searchdir%\*.mp4"  > rule28_mp4.txt
set /a index_mp4 = 0
set Z_mp4_dirname=Z_mp4
echo  mkdir !Z_mp4_dirname!
for /f %%x in (rule28_mp4.txt) do (
echo ___________[!index_mp4!_mp4]______________
set mp4_file_fullpath=%%x
set /a index_mp4+=1
set oldname=%%~nx
set newfileName_mp4_raw=!index_mp4!_!oldname!%%~xx
set newfileName_mp4=!newfileName_mp4_raw: =!
echo [!index_mp4!_mp4] i=%%x  mp4_file_fullpath=!mp4_file_fullpath! index_mp4=!index_mp4! oldname=!oldname! newfileName_mp4_raw=!newfileName_mp4_raw! newfileName_mp4=!newfileName_mp4! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.mp4  D:\TEMP\0915\A\1_1.mp4 && del D:\TEMP\0915\1.mp4 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_dirname!\!newfileName_mp4!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_dirname!\!newfileName_mp4!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_mp4_dirname!_!index_mp4!"
ren "!Z_mp4_dirname!" "!Z_mp4_dirname!_!index_mp4!"
del rule28_mp4.txt
dir /b /a-d /o-d /s "%searchdir%\*.avi"  > rule28_avi.txt
set /a index_avi = 0
set Z_avi_dirname=Z_avi
echo  mkdir !Z_avi_dirname!
for /f %%x in (rule28_avi.txt) do (
echo ___________[!index_avi!_avi]______________
set avi_file_fullpath=%%x
set /a index_avi+=1
set oldname=%%~nx
set newfileName_avi_raw=!index_avi!_!oldname!%%~xx
set newfileName_avi=!newfileName_avi_raw: =!
echo [!index_avi!_avi] i=%%x  avi_file_fullpath=!avi_file_fullpath! index_avi=!index_avi! oldname=!oldname! newfileName_avi_raw=!newfileName_avi_raw! newfileName_avi=!newfileName_avi! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.avi  D:\TEMP\0915\A\1_1.avi && del D:\TEMP\0915\1.avi 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_avi_dirname!\!newfileName_avi!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_avi_dirname!\!newfileName_avi!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_avi_dirname!_!index_avi!"
ren "!Z_avi_dirname!" "!Z_avi_dirname!_!index_avi!"
del rule28_avi.txt
dir /b /a-d /o-d /s "%searchdir%\*.gif"  > rule28_gif.txt
set /a index_gif = 0
set Z_gif_dirname=Z_gif
echo  mkdir !Z_gif_dirname!
for /f %%x in (rule28_gif.txt) do (
echo ___________[!index_gif!_gif]______________
set gif_file_fullpath=%%x
set /a index_gif+=1
set oldname=%%~nx
set newfileName_gif_raw=!index_gif!_!oldname!%%~xx
set newfileName_gif=!newfileName_gif_raw: =!
echo [!index_gif!_gif] i=%%x  gif_file_fullpath=!gif_file_fullpath! index_gif=!index_gif! oldname=!oldname! newfileName_gif_raw=!newfileName_gif_raw! newfileName_gif=!newfileName_gif! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.gif  D:\TEMP\0915\A\1_1.gif && del D:\TEMP\0915\1.gif 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_dirname!\!newfileName_gif!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_dirname!\!newfileName_gif!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_gif_dirname!_!index_gif!"
ren "!Z_gif_dirname!" "!Z_gif_dirname!_!index_gif!"
del rule28_gif.txt
dir /b /a-d /o-d /s "%searchdir%\*.webp"  > rule28_webp.txt
set /a index_webp = 0
set Z_webp_dirname=Z_webp
echo  mkdir !Z_webp_dirname!
for /f %%x in (rule28_webp.txt) do (
echo ___________[!index_webp!_webp]______________
set webp_file_fullpath=%%x
set /a index_webp+=1
set oldname=%%~nx
set newfileName_webp_raw=!index_webp!_!oldname!%%~xx
set newfileName_webp=!newfileName_webp_raw: =!
echo [!index_webp!_webp] i=%%x  webp_file_fullpath=!webp_file_fullpath! index_webp=!index_webp! oldname=!oldname! newfileName_webp_raw=!newfileName_webp_raw! newfileName_webp=!newfileName_webp! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.webp  D:\TEMP\0915\A\1_1.webp && del D:\TEMP\0915\1.webp 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_dirname!\!newfileName_webp!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_dirname!\!newfileName_webp!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_webp_dirname!_!index_webp!"
ren "!Z_webp_dirname!" "!Z_webp_dirname!_!index_webp!"
del rule28_webp.txt
dir /b /a-d /o-d /s "%searchdir%\*.flv"  > rule28_flv.txt
set /a index_flv = 0
set Z_flv_dirname=Z_flv
echo  mkdir !Z_flv_dirname!
for /f %%x in (rule28_flv.txt) do (
echo ___________[!index_flv!_flv]______________
set flv_file_fullpath=%%x
set /a index_flv+=1
set oldname=%%~nx
set newfileName_flv_raw=!index_flv!_!oldname!%%~xx
set newfileName_flv=!newfileName_flv_raw: =!
echo [!index_flv!_flv] i=%%x  flv_file_fullpath=!flv_file_fullpath! index_flv=!index_flv! oldname=!oldname! newfileName_flv_raw=!newfileName_flv_raw! newfileName_flv=!newfileName_flv! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.flv  D:\TEMP\0915\A\1_1.flv && del D:\TEMP\0915\1.flv 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_flv_dirname!\!newfileName_flv!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_flv_dirname!\!newfileName_flv!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_flv_dirname!_!index_flv!"
ren "!Z_flv_dirname!" "!Z_flv_dirname!_!index_flv!"
del rule28_flv.txt
dir /b /a-d /o-d /s "%searchdir%\*.wmv"  > rule28_wmv.txt
set /a index_wmv = 0
set Z_wmv_dirname=Z_wmv
echo  mkdir !Z_wmv_dirname!
for /f %%x in (rule28_wmv.txt) do (
echo ___________[!index_wmv!_wmv]______________
set wmv_file_fullpath=%%x
set /a index_wmv+=1
set oldname=%%~nx
set newfileName_wmv_raw=!index_wmv!_!oldname!%%~xx
set newfileName_wmv=!newfileName_wmv_raw: =!
echo [!index_wmv!_wmv] i=%%x  wmv_file_fullpath=!wmv_file_fullpath! index_wmv=!index_wmv! oldname=!oldname! newfileName_wmv_raw=!newfileName_wmv_raw! newfileName_wmv=!newfileName_wmv! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.wmv  D:\TEMP\0915\A\1_1.wmv && del D:\TEMP\0915\1.wmv 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_wmv_dirname!\!newfileName_wmv!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_wmv_dirname!\!newfileName_wmv!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_wmv_dirname!_!index_wmv!"
ren "!Z_wmv_dirname!" "!Z_wmv_dirname!_!index_wmv!"
del rule28_wmv.txt
rem 遍历Z_开头的目录  检测是否是空文件夹  是 空文件夹 删除它
set /a Index_R_D = 0
for /d  /r %searchdir% %%j in  (*Z_*)do (
set /a Index_R_D+=1 
call :isemptydirfile_func_1x1 %%j
set emptydir_flag=!isemptydirfile_return_1!
echo r_d_index[!Index_R_D!] =  %%j  isemptydirfile_return_1=!isemptydirfile_return_1! emptydir_flag=!emptydir_flag!
if "!emptydir_flag!"=="true" (
echo 删除空文件夹 %%j
rd /s/q  "%%j"
)
)
) else (
call :isstartwith_func_2x1 %init_input_2%  typefilter_ 
set isstart_typefillter=!isstartwith_return_1!
echo isstart_typefillter = !isstart_typefillter!
if "!isstart_typefillter!"=="true" (
call :stringreplace_func_3x1 %init_input_2%  typefilter_  ""
set target_file_type=!stringreplace_return_1!
set zzztype=!stringreplace_return_1!
echo target_file_type=!target_file_type!  zzztype=!zzztype!
rem 开始执行过滤 指定类型文件 如 xlsx 的 目录 
dir /b /a-d /o-d /s "%searchdir%\*.!zzztype!"  > rule28_!zzztype!.txt
set /a index_zzz = 0
set Z_zzz_dirname=Z_!zzztype!
echo  mkdir !Z_zzz_dirname!
for /f %%x in (rule28_!zzztype!.txt) do (
echo ___________[!index_zzz!_!zzztype!]______________
set zzz_file_fullpath=%%x
set /a index_zzz+=1
set oldname=%%~nx
set newfileName_zzz_raw=!index_zzz!_!oldname!%%~xx
set newfileName_zzz=!newfileName_zzz_raw: =!
echo [!index_zzz!_!zzztype!] i=%%x  zzz_file_fullpath=!zzz_file_fullpath! index_zzz=!index_zzz! oldname=!oldname! newfileName_zzz_raw=!newfileName_zzz_raw! newfileName_zzz=!newfileName_zzz! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_zzz_dirname!\!newfileName_zzz!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_zzz_dirname!\!newfileName_zzz!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_zzz_dirname!_!index_zzz!"
ren "!Z_zzz_dirname!" "!Z_zzz_dirname!_!index_zzz!"
del rule28_!zzztype!.txt
rem 遍历Z_!zzztype!开头的目录  检测是否是空文件夹  是 空文件夹 删除它
set /a Index_R_D = 0
for /d  /r %searchdir% %%j in  (*Z_!zzztype!*)do (
set /a Index_R_D+=1 
call :isemptydirfile_func_1x1 %%j
set emptydir_flag=!isemptydirfile_return_1!
echo r_d_index[!Index_R_D!] =  %%j  isemptydirfile_return_1=!isemptydirfile_return_1! emptydir_flag=!emptydir_flag!
if "!emptydir_flag!"=="true" (
echo 删除空文件夹 %%j
rd /s/q  "%%j"
)
)
)
)
echo rule28vfiltermediatodir_dynamic_param1=%init_input_2%
echo [rule28vfiltermediatodir_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule28vfiltermediatodir_dynamic_param1!]   
echo ______________Method_Out rule28vfiltermediatodir_func_1x0
::ENDLOCAL
goto:eof




:rule29vflitermediatozappdir_func_0x0
rem ======================================== rule29vflitermediatozappdir_func_0x0
rem rule_tip: %init_input_0% _29_       ## zapp 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zapp分类目录 jpg_common_land

rem rule_tip: %init_input_0% _29_      ## zapp 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zapp分类目录 jpg_common_port
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule29vflitermediatozappdir_func_0x0
mkdir jpg_common_port
mkdir jpg_common_land
mkdir jpg_top_land
mkdir jpg_top_port
mkdir jpg_girl_port
mkdir jpg_girl_land
mkdir jpg_lin_port
mkdir jpg_lin_land
mkdir gif_common_land
mkdir gif_common_port
mkdir gif_top_port
mkdir gif_top_land
mkdir mp4_common_land
mkdir mp4_common_port
mkdir mp4_top_port
mkdir mp4_top_land
mkdir mp4_single_port
mkdir mp4_single_land
mkdir mp4_raw_port
mkdir mp4_raw_land
mkdir mp4_hua_port
mkdir mp4_hua_land
mkdir mp4_dan_port
mkdir mp4_dan_land
mkdir mp4_sss_port
mkdir mp4_sss_land
mkdir mp4_anim_port
mkdir mp4_anim_land
mkdir mp4_toto_land
mkdir mp4_toto_port
mkdir mp4_fake_port
mkdir mp4_fake_land
mkdir z_sss
mkdir z_sss\zz_unknow_land\ 
mkdir z_sss\zz_unknow_port\
mkdir z_sss\other\     
mkdir z_sss\foreign\     
mkdir z_sss\tandan_port\  
mkdir z_sss\tanhua_port\
mkdir z_sss\korean_port\  
mkdir z_sss\korean_land\ 
mkdir z_sss\tandan_land\  
mkdir z_sss\tanhua_land\
mkdir z_sss\ssyy\
mkdir z_sss\mly\
mkdir z_sss\bdyjy\
mkdir z_sss\cjmy\
mkdir z_sss\xqkjx\
mkdir z_sss\lzll\
mkdir z_sss\yzzzz\
mkdir z_sss\xcny\
mkdir z_sss\stym\
mkdir z_sss\thy\
mkdir z_sss\dyf\
mkdir z_webp
mkdir zzmp4_temp_land
mkdir zzmp4_temp_port
call ::gettimeddhhmm_func_0x1
set ddhhmmstr=!gettimeddhhmm_return_1!
echo ddhhmmstr=!ddhhmmstr!
dir /b /a-d /o-d /s "%init_cd%\Port_*.jpg"  > jpg_common_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\jpg_common_port.txt
set jpg_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  jpg_common_port_rownum[!jpg_common_port_rownum!]
set /a index_jpg = 0
set Z_jpg_port_dirname=jpg_common_port
echo  mkdir !Z_jpg_port_dirname!
for /f %%x in (jpg_common_port.txt) do (
echo ___________[!index_jpg!_jpg]______________
set jpg_file_fullpath=%%x
set /a index_jpg+=1
set oldname=%%~nx
set newfileName_jpg_raw=!ddhhmmstr!_!index_jpg!_!jpg_common_port_rownum!_!oldname!%%~xx
set newfileName_jpg=!newfileName_jpg_raw: =!
echo [!index_jpg!_jpg] i=%%x  jpg_file_fullpath=!jpg_file_fullpath! index_jpg=!index_jpg! oldname=!oldname! newfileName_jpg_raw=!newfileName_jpg_raw! newfileName_jpg=!newfileName_jpg! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.jpg  D:\TEMP\0915\A\1_1.jpg && del D:\TEMP\0915\1.jpg 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_port_dirname!\!newfileName_jpg!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_port_dirname!\!newfileName_jpg!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_jpg_port_dirname!_!index_jpg!"
del jpg_common_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.jpg"  > jpg_common_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\jpg_common_land.txt
set jpg_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  jpg_common_land_rownum[!jpg_common_land_rownum!]
set /a index_jpg = 0
set Z_jpg_land_dirname=jpg_common_land
echo  mkdir !Z_jpg_land_dirname!
for /f %%x in (jpg_common_land.txt) do (
echo ___________[!index_jpg!_jpg]______________
set jpg_file_fullpath=%%x
set /a index_jpg+=1
set oldname=%%~nx
set newfileName_jpg_raw=!ddhhmmstr!_!index_jpg!_!jpg_common_land_rownum!_!oldname!%%~xx
set newfileName_jpg=!newfileName_jpg_raw: =!
echo [!index_jpg!_jpg] i=%%x  jpg_file_fullpath=!jpg_file_fullpath! index_jpg=!index_jpg! oldname=!oldname! newfileName_jpg_raw=!newfileName_jpg_raw! newfileName_jpg=!newfileName_jpg! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.jpg  D:\TEMP\0915\A\1_1.jpg && del D:\TEMP\0915\1.jpg 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_land_dirname!\!newfileName_jpg!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_land_dirname!\!newfileName_jpg!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_jpg_land_dirname!_!index_jpg!"
del jpg_common_land.txt
dir /b /a-d /o-d /s "%init_cd%\Port_*.gif"  > gif_common_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\gif_common_port.txt
set gif_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  gif_common_port_rownum[!gif_common_port_rownum!]
set /a index_gif = 0
set Z_gif_port_dirname=gif_common_port
echo  mkdir !Z_gif_port_dirname!
for /f %%x in (gif_common_port.txt) do (
echo ___________[!index_gif!_gif]______________
set gif_file_fullpath=%%x
set /a index_gif+=1
set oldname=%%~nx
set newfileName_gif_raw=!ddhhmmstr!_!index_gif!_!gif_common_port_rownum!_!oldname!%%~xx
set newfileName_gif=!newfileName_gif_raw: =!
echo [!index_gif!_gif] i=%%x  gif_file_fullpath=!gif_file_fullpath! index_gif=!index_gif! oldname=!oldname! newfileName_gif_raw=!newfileName_gif_raw! newfileName_gif=!newfileName_gif! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.gif  D:\TEMP\0915\A\1_1.gif && del D:\TEMP\0915\1.gif 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_port_dirname!\!newfileName_gif!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_port_dirname!\!newfileName_gif!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_gif_port_dirname!_!index_gif!"
del gif_common_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.gif"  > gif_common_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\gif_common_land.txt
set gif_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  gif_common_land_rownum[!gif_common_land_rownum!]
set /a index_gif = 0
set Z_gif_land_dirname=gif_common_land
echo  mkdir !Z_gif_land_dirname!
for /f %%x in (gif_common_land.txt) do (
echo ___________[!index_gif!_gif]______________
set gif_file_fullpath=%%x
set /a index_gif+=1
set oldname=%%~nx
set newfileName_gif_raw=!ddhhmmstr!_!index_gif!_!gif_common_land_rownum!_!oldname!%%~xx
set newfileName_gif=!newfileName_gif_raw: =!
echo [!index_gif!_gif] i=%%x  gif_file_fullpath=!gif_file_fullpath! index_gif=!index_gif! oldname=!oldname! newfileName_gif_raw=!newfileName_gif_raw! newfileName_gif=!newfileName_gif! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.gif  D:\TEMP\0915\A\1_1.gif && del D:\TEMP\0915\1.gif 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_land_dirname!\!newfileName_gif!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_land_dirname!\!newfileName_gif!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_gif_land_dirname!_!index_gif!"
del gif_common_land.txt
dir /b /a-d /o-d /s "%init_cd%\Port_*.mp4"  > mp4_common_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\mp4_common_port.txt
set mp4_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  mp4_common_port_rownum[!mp4_common_port_rownum!]
set /a index_mp4 = 0
set Z_mp4_port_dirname=mp4_common_port
echo  mkdir !Z_mp4_port_dirname!
for /f %%x in (mp4_common_port.txt) do (
echo ___________[!index_mp4!_mp4]______________
set mp4_file_fullpath=%%x
set /a index_mp4+=1
set oldname=%%~nx
set newfileName_mp4_raw=!ddhhmmstr!_!index_mp4!_!mp4_common_port_rownum!_!oldname!%%~xx
set newfileName_mp4=!newfileName_mp4_raw: =!
echo [!index_mp4!_mp4] i=%%x  mp4_file_fullpath=!mp4_file_fullpath! index_mp4=!index_mp4! oldname=!oldname! newfileName_mp4_raw=!newfileName_mp4_raw! newfileName_mp4=!newfileName_mp4! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.mp4  D:\TEMP\0915\A\1_1.mp4 && del D:\TEMP\0915\1.mp4 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_port_dirname!\!newfileName_mp4!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_port_dirname!\!newfileName_mp4!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_mp4_port_dirname!_!index_mp4!"
del mp4_common_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.mp4"  > mp4_common_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\mp4_common_land.txt
set mp4_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  mp4_common_land_rownum[!mp4_common_land_rownum!]
set /a index_mp4 = 0
set Z_mp4_land_dirname=mp4_common_land
echo  mkdir !Z_mp4_land_dirname!
for /f %%x in (mp4_common_land.txt) do (
echo ___________[!index_mp4!_mp4]______________
set mp4_file_fullpath=%%x
set /a index_mp4+=1
set oldname=%%~nx
set newfileName_mp4_raw=!ddhhmmstr!_!index_mp4!_!mp4_common_land_rownum!_!oldname!%%~xx
set newfileName_mp4=!newfileName_mp4_raw: =!
echo [!index_mp4!_mp4] i=%%x  mp4_file_fullpath=!mp4_file_fullpath! index_mp4=!index_mp4! oldname=!oldname! newfileName_mp4_raw=!newfileName_mp4_raw! newfileName_mp4=!newfileName_mp4! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.mp4  D:\TEMP\0915\A\1_1.mp4 && del D:\TEMP\0915\1.mp4 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_land_dirname!\!newfileName_mp4!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_land_dirname!\!newfileName_mp4!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_mp4_land_dirname!_!index_mp4!"
del mp4_common_land.txt
dir /b /a-d /o-d /s "%init_cd%\*.webp"  > z_webp.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\z_webp.txt
set webp_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  webp_common_port_rownum[!webp_common_port_rownum!]
set /a index_webp = 0
set Z_webp_port_dirname=z_webp
echo  mkdir !Z_webp_port_dirname!
for /f %%x in (z_webp.txt) do (
echo ___________[!index_webp!_webp]______________
set webp_file_fullpath=%%x
set /a index_webp+=1
set oldname=%%~nx
set newfileName_webp_raw=!ddhhmmstr!_!index_webp!_!webp_common_port_rownum!_!oldname!%%~xx
set newfileName_webp=!newfileName_webp_raw: =!
echo [!index_webp!_webp] i=%%x  webp_file_fullpath=!webp_file_fullpath! index_webp=!index_webp! oldname=!oldname! newfileName_webp_raw=!newfileName_webp_raw! newfileName_webp=!newfileName_webp! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.webp  D:\TEMP\0915\A\1_1.webp && del D:\TEMP\0915\1.webp 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_port_dirname!\!newfileName_webp!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_port_dirname!\!newfileName_webp!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_webp_port_dirname!_!index_webp!"
del z_webp.txt
echo [rule29vflitermediatozappdir_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule29vflitermediatozappdir_func_0x0
::ENDLOCAL
goto:eof




:rule31vcopyfiletostartupwithadmin_func_1x0
rem ======================================== rule31vcopyfiletostartupwithadmin_func_1x0
rem rule_tip: %init_input_0% _31_  file_xxxx.bat  ##   admin权限复制到 Windows启动路径  explorer.exe  "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp"
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule31vcopyfiletostartupwithadmin_func_1x0
set rule31vcopyfiletostartupwithadmin_dynamic_param1=
set rule31vcopyfiletostartup_dynamic_param1=%init_input_2%
call :isstartwith_func_2x1 %init_input_2%  file_ 
set isstart_file=!isstartwith_return_1!
echo isstart_file = !isstart_file!
if "!isstart_file!"=="true" (
call :stringreplace_func_3x1 %init_input_2%  file_  ""
set target_file_path=!stringreplace_return_1!
echo "当前输入文件路径为" target_file_path=!target_file_path!  
call ::isfileexist_func_1x1 !target_file_path!  
set paramfile_existflag=!isfileexist_return_1!
echo paramfile_existflag=!paramfile_existflag!
if "!paramfile_existflag!"=="true" (
echo "当前输入文件路径为" target_file_path=!target_file_path!  并且 文件 存在
del  %win_zbin%/zcmd_admin_I9.bat    
echo @ECHO off > %win_zbin%/zcmd_admin_I9.bat  
echo setlocal enabledelayedexpansion  >> %win_zbin%/zcmd_admin_I9.bat  
rem set local_str=^%1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c ^%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit
echo ^%%^1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c %%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit  >> %win_zbin%/zcmd_admin_I9.bat  
echo  cmd /K xcopy /y "!target_file_path!"   "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp"  >> %win_zbin%/zcmd_admin_I9.bat
start %win_zbin%/zcmd_admin_I9.bat
rem start %win_zbin%/zcmd_admin_I9.bat  
rem del  zcmd_admin_I9.bat  
) else (
echo "当前输入文件路径为 target_file_path=!target_file_path!  但文件经检测 文件 不存在  程序执行失败!"
)
) else (
echo "当前输入参数 %init_input_2% 不是以 file_开头! 程序执行失败! "
)
echo [rule31vcopyfiletostartupwithadmin_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule31vcopyfiletostartupwithadmin_dynamic_param1!]   
echo ______________Method_Out rule31vcopyfiletostartupwithadmin_func_1x0
::ENDLOCAL
goto:eof




:rule32vflitermediatozmaindir_func_0x0
rem ======================================== rule32vflitermediatozmaindir_func_0x0
rem rule_tip: %init_input_0% _32_       ## main 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zmain分类目录 jpg_common_land

rem rule_tip: %init_input_0% _32_      ## main 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zmain分类目录 jpg_common_port
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule32vflitermediatozmaindir_func_0x0
mkdir jpg_common_port
mkdir jpg_common_land
mkdir jpg_gaokao_land
mkdir jpg_kaoyan_port
mkdir jpg_home_port
mkdir jpg_home_land
mkdir jpg_scene_port
mkdir jpg_scene_land
mkdir gif_common_land
mkdir gif_common_port
mkdir gif 
mkdir stock
mkdir jpg_stock_port
mkdir mp3
mkdir mp4_common_land
mkdir mp4_common_port
mkdir mp4_gaokao_land
mkdir mp4_kaoyan_land
mkdir mp4_home_port
mkdir mp4_home_land
mkdir mp4_music_port
mkdir mp4_music_land
mkdir mp4_scene_port
mkdir mp4_scene_land
call ::gettimeddhhmm_func_0x1
set ddhhmmstr=!gettimeddhhmm_return_1!
echo ddhhmmstr=!ddhhmmstr!
dir /b /a-d /o-d /s "%init_cd%\Port_*.jpg"  > jpg_home_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\jpg_home_port.txt
set jpg_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  jpg_common_port_rownum[!jpg_common_port_rownum!]
set /a index_jpg = 0
set Z_jpg_port_dirname=jpg_home_port
echo  mkdir !Z_jpg_port_dirname!
for /f %%x in (jpg_home_port.txt) do (
echo ___________[!index_jpg!_jpg]______________
set jpg_file_fullpath=%%x
set /a index_jpg+=1
set oldname=%%~nx
set newfileName_jpg_raw=!ddhhmmstr!_!index_jpg!_!jpg_common_port_rownum!_!oldname!%%~xx
set newfileName_jpg=!newfileName_jpg_raw: =!
echo [!index_jpg!_jpg] i=%%x  jpg_file_fullpath=!jpg_file_fullpath! index_jpg=!index_jpg! oldname=!oldname! newfileName_jpg_raw=!newfileName_jpg_raw! newfileName_jpg=!newfileName_jpg! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.jpg  D:\TEMP\0915\A\1_1.jpg && del D:\TEMP\0915\1.jpg 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_port_dirname!\!newfileName_jpg!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_port_dirname!\!newfileName_jpg!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_jpg_port_dirname!_!index_jpg!"
del jpg_home_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.jpg"  > jpg_home_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\jpg_home_land.txt
set jpg_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  jpg_common_land_rownum[!jpg_common_land_rownum!]
set /a index_jpg = 0
set Z_jpg_land_dirname=jpg_home_land
echo  mkdir !Z_jpg_land_dirname!
for /f %%x in (jpg_home_land.txt) do (
echo ___________[!index_jpg!_jpg]______________
set jpg_file_fullpath=%%x
set /a index_jpg+=1
set oldname=%%~nx
set newfileName_jpg_raw=!ddhhmmstr!_!index_jpg!_!jpg_common_land_rownum!_!oldname!%%~xx
set newfileName_jpg=!newfileName_jpg_raw: =!
echo [!index_jpg!_jpg] i=%%x  jpg_file_fullpath=!jpg_file_fullpath! index_jpg=!index_jpg! oldname=!oldname! newfileName_jpg_raw=!newfileName_jpg_raw! newfileName_jpg=!newfileName_jpg! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.jpg  D:\TEMP\0915\A\1_1.jpg && del D:\TEMP\0915\1.jpg 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_land_dirname!\!newfileName_jpg!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_jpg_land_dirname!\!newfileName_jpg!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_jpg_land_dirname!_!index_jpg!"
del jpg_home_land.txt
dir /b /a-d /o-d /s "%init_cd%\Port_*.gif"  > gif_common_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\gif_common_port.txt
set gif_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  gif_common_port_rownum[!gif_common_port_rownum!]
set /a index_gif = 0
set Z_gif_port_dirname=gif_common_port
echo  mkdir !Z_gif_port_dirname!
for /f %%x in (gif_common_port.txt) do (
echo ___________[!index_gif!_gif]______________
set gif_file_fullpath=%%x
set /a index_gif+=1
set oldname=%%~nx
set newfileName_gif_raw=!ddhhmmstr!_!index_gif!_!gif_common_port_rownum!_!oldname!%%~xx
set newfileName_gif=!newfileName_gif_raw: =!
echo [!index_gif!_gif] i=%%x  gif_file_fullpath=!gif_file_fullpath! index_gif=!index_gif! oldname=!oldname! newfileName_gif_raw=!newfileName_gif_raw! newfileName_gif=!newfileName_gif! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.gif  D:\TEMP\0915\A\1_1.gif && del D:\TEMP\0915\1.gif 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_port_dirname!\!newfileName_gif!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_port_dirname!\!newfileName_gif!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_gif_port_dirname!_!index_gif!"
del gif_common_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.gif"  > gif_common_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\gif_common_land.txt
set gif_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  gif_common_land_rownum[!gif_common_land_rownum!]
set /a index_gif = 0
set Z_gif_land_dirname=gif_common_land
echo  mkdir !Z_gif_land_dirname!
for /f %%x in (gif_common_land.txt) do (
echo ___________[!index_gif!_gif]______________
set gif_file_fullpath=%%x
set /a index_gif+=1
set oldname=%%~nx
set newfileName_gif_raw=!ddhhmmstr!_!index_gif!_!gif_common_land_rownum!_!oldname!%%~xx
set newfileName_gif=!newfileName_gif_raw: =!
echo [!index_gif!_gif] i=%%x  gif_file_fullpath=!gif_file_fullpath! index_gif=!index_gif! oldname=!oldname! newfileName_gif_raw=!newfileName_gif_raw! newfileName_gif=!newfileName_gif! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.gif  D:\TEMP\0915\A\1_1.gif && del D:\TEMP\0915\1.gif 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_land_dirname!\!newfileName_gif!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_gif_land_dirname!\!newfileName_gif!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_gif_land_dirname!_!index_gif!"
del gif_common_land.txt
dir /b /a-d /o-d /s "%init_cd%\Port_*.mp4"  > mp4_home_port.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\mp4_home_port.txt
set mp4_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  mp4_common_port_rownum[!mp4_common_port_rownum!]
set /a index_mp4 = 0
set Z_mp4_port_dirname=mp4_home_port
echo  mkdir !Z_mp4_port_dirname!
for /f %%x in (mp4_home_port.txt) do (
echo ___________[!index_mp4!_mp4]______________
set mp4_file_fullpath=%%x
set /a index_mp4+=1
set oldname=%%~nx
set newfileName_mp4_raw=!ddhhmmstr!_!index_mp4!_!mp4_common_port_rownum!_!oldname!%%~xx
set newfileName_mp4=!newfileName_mp4_raw: =!
echo [!index_mp4!_mp4] i=%%x  mp4_file_fullpath=!mp4_file_fullpath! index_mp4=!index_mp4! oldname=!oldname! newfileName_mp4_raw=!newfileName_mp4_raw! newfileName_mp4=!newfileName_mp4! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.mp4  D:\TEMP\0915\A\1_1.mp4 && del D:\TEMP\0915\1.mp4 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_port_dirname!\!newfileName_mp4!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_port_dirname!\!newfileName_mp4!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_mp4_port_dirname!_!index_mp4!"
del mp4_home_port.txt
dir /b /a-d /o-d /s "%init_cd%\Land_*.mp4"  > mp4_home_land.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\mp4_home_land.txt
set mp4_common_land_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  mp4_common_land_rownum[!mp4_common_land_rownum!]
set /a index_mp4 = 0
set Z_mp4_land_dirname=mp4_home_land
echo  mkdir !Z_mp4_land_dirname!
for /f %%x in (mp4_home_land.txt) do (
echo ___________[!index_mp4!_mp4]______________
set mp4_file_fullpath=%%x
set /a index_mp4+=1
set oldname=%%~nx
set newfileName_mp4_raw=!ddhhmmstr!_!index_mp4!_!mp4_common_land_rownum!_!oldname!%%~xx
set newfileName_mp4=!newfileName_mp4_raw: =!
echo [!index_mp4!_mp4] i=%%x  mp4_file_fullpath=!mp4_file_fullpath! index_mp4=!index_mp4! oldname=!oldname! newfileName_mp4_raw=!newfileName_mp4_raw! newfileName_mp4=!newfileName_mp4! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.mp4  D:\TEMP\0915\A\1_1.mp4 && del D:\TEMP\0915\1.mp4 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_land_dirname!\!newfileName_mp4!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_mp4_land_dirname!\!newfileName_mp4!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_mp4_land_dirname!_!index_mp4!"
del mp4_home_land.txt
dir /b /a-d /o-d /s "%init_cd%\*.mp3"  > mp3.txt
ping -n 1 127.0.0.1>nul 
call ::getfilerownum_func_1x1  %init_cd%\mp3.txt
set webp_common_port_rownum=!getfilerownum_return_1!
echo getfilerownum_return_1[!getfilerownum_return_1!]  webp_common_port_rownum[!webp_common_port_rownum!]
set /a index_webp = 0
set Z_webp_port_dirname=mp3
echo  mkdir !Z_webp_port_dirname!
for /f %%x in (mp3.txt) do (
echo ___________[!index_webp!_webp]______________
set webp_file_fullpath=%%x
set /a index_webp+=1
set oldname=%%~nx
set newfileName_webp_raw=!ddhhmmstr!_!index_webp!_!webp_common_port_rownum!_!oldname!%%~xx
set newfileName_webp=!newfileName_webp_raw: =!
echo [!index_webp!_webp] i=%%x  webp_file_fullpath=!webp_file_fullpath! index_webp=!index_webp! oldname=!oldname! newfileName_webp_raw=!newfileName_webp_raw! newfileName_webp=!newfileName_webp! xi=%%~xx  ni=%%~nx
set itemfullpath=%%~sx
rem  ren "%%i" "!newfileName!"
rem echo f | xcopy /y D:\TEMP\0915\1.webp  D:\TEMP\0915\A\1_1.webp && del D:\TEMP\0915\1.webp 
echo echo f ^| xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_port_dirname!\!newfileName_webp!  ^&^& del  "!itemfullpath!"
echo=
echo f | xcopy /y "!itemfullpath!"  !init_cd!\!Z_webp_port_dirname!\!newfileName_webp!  && del  "!itemfullpath!"
)
rd /s/q  "!Z_webp_port_dirname!_!index_webp!"
del mp3.txt
echo [rule32vflitermediatozmaindir_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule32vflitermediatozmaindir_func_0x0
::ENDLOCAL
goto:eof




:rule996vwindowsbomb_func_0x0
rem ======================================== rule996vwindowsbomb_func_0x0
rem rule_tip: %init_input_0% _996_     ## 不断循环打开关闭 CMD页面  感觉像 轰炸屏幕 寓意轰炸996  无奈下周修好电脑继续
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule996vwindowsbomb_func_0x0
del zcmd_winbomb_I9_996.bat
echo @ECHO off >> zcmd_winbomb_I9_996.bat  
echo setlocal enabledelayedexpansion  >> zcmd_winbomb_I9_996.bat  
rem set local_str=^%1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c ^%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit
echo call mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c %%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit  >> zcmd_winbomb_I9_996.bat 
rem echo start cmd /K D: >> zcmd_winbomb_I9_996.bat  
rem echo start cmd /K "cd /d D:/zsoft/"  >> zcmd_winbomb_I9_996.bat  
start zcmd_winbomb_I9_996.bat
ping -n 100 127.0.0.1>nul
del zcmd_winbomb_I9_996.bat
echo [rule996vwindowsbomb_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule996vwindowsbomb_func_0x0
::ENDLOCAL
goto:eof




:rule997vmethodholdplace3_func_2x0
rem ======================================== rule997vmethodholdplace3_func_2x0
rem rule_tip: %init_input_0% _997_   ## 打印当前 rule规则的method模板 2x0模板
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule997vmethodholdplace3_func_2x0
set rule997vmethodholdplace3_dynamic_param1=
set rule997vmethodholdplace3_dynamic_param2=
echo ========================================== Rule_Method_HoldPlace_占位模板 Begin ===============================
if "%init_input_2%"=="HolderOn" (
set rule997vmethodholdplace3_dynamic_param1=%init_input_2%
) else (
)
echo rule997vmethodholdplace3_dynamic_param1=%init_input_2%
if "%init_input_3%"=="HolderOn" (
set rule997vmethodholdplace3_dynamic_param2=%init_input_3%
) else (
)
echo rule997vmethodholdplace3_dynamic_param2=%init_input_3%
echo ========================================== Rule_Method_HoldPlace_占位模板 End ===============================
echo [rule997vmethodholdplace3_func_2x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule997vmethodholdplace3_dynamic_param1!]   dynamic_param2=[!rule997vmethodholdplace3_dynamic_param2!]   
echo ______________Method_Out rule997vmethodholdplace3_func_2x0
::ENDLOCAL
goto:eof




:rule998vmethodholdplace1_func_1x0
rem ======================================== rule998vmethodholdplace1_func_1x0
rem rule_tip: %init_input_0% _998_   ## 打印当前 rule规则的method模板 1x0模板
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule998vmethodholdplace1_func_1x0
set rule998vmethodholdplace1_dynamic_param1=
echo ========================================== Rule_Method_HoldPlace_占位模板 Begin ===============================
if "%init_input_2%"=="HolderOn" (
set rule998vmethodholdplace1_dynamic_param1=%init_input_2%
) else (
)
echo rule998vmethodholdplace1_dynamic_param1=%init_input_2%
echo ========================================== Rule_Method_HoldPlace_占位模板 End ===============================
echo [rule998vmethodholdplace1_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule998vmethodholdplace1_dynamic_param1!]   
echo ______________Method_Out rule998vmethodholdplace1_func_1x0
::ENDLOCAL
goto:eof




:rule999vmethodholdplace0_func_0x0
rem ======================================== rule999vmethodholdplace0_func_0x0
rem rule_tip: %init_input_0% _999_   ## 打印当前 rule规则的method模板 0x0模板
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule999vmethodholdplace0_func_0x0
echo ========================================== Rule_Method_HoldPlace_占位模板 Begin ===============================
echo :rule999vmethodholdplace0_func_0x0
echo ========================================== Rule_Method_HoldPlace_占位模板 End ===============================
echo [rule999vmethodholdplace0_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule999vmethodholdplace0_func_0x0
::ENDLOCAL
goto:eof




:ruletipprint_func_0x0
rem ======================================== ruletipprint_func_0x0
rem desc: Bussiness_Rule打印程序用于打印batrule规则序列
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In ruletipprint_func_0x0
echo %init_input_0% _0_   ## 打印当前 rule规则的method模板

echo %init_input_0% _1_   ## 备份bankup所有当前手机安装的三方 apk本地PC本地目录

echo %init_input_0% _2_   ## 获取手机当前正在运行的APK_必须是三方的APK 到本地

echo %init_input_0% _3_  ##安装当前目录的apk到手机

echo %init_input_0% _4_ 10000   ##手机执行 adb shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _5_  1000000  ##手机执行 adb shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _6_  ## 执行清空apk的操作

echo %init_input_0% _7_  ## 蓝牙开启关闭 默认执行1000次

echo %init_input_0% _8_ 400 800 1000  ## 点击屏幕位置 x[400]  y[800] repeat[1000] 重复 repeat次 默认1000次

echo %init_input_0% _9_  ## wifi开启关闭  默认1000次

echo %init_input_0% _10_  1000  ## GPS开启关闭  默认1000次

echo %init_input_0% _11_   ## 开启wifi连接WPS网络

echo %init_input_0% _12_  www.baidu.com  ## adb输入字符串 默认为 www.baidu.com

echo %init_input_0% _13_   ## 对当前手机屏幕截屏并拉取到手机本地

echo %init_input_0% _14_     ## 对当前手机屏幕录屏 然后拔出插入 使得mp4文件拉取到手机本地

echo %init_input_0% _15_   100000   ## 有线USB_手机执行 adb  shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  c2f5b32c  100000    ##小米_有线USB_手机执行  adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  721QECRM2ZMT4  100000  ##魅蓝_有线USB_手机执行  adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  HMKNW17531001873 100000  ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  192.168.31.89:5555 100000  ##小米_无线USB_无线 adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  192.168.31.40:5555 100000  ##魅蓝_无线USB_无线 adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _15_  192.168.31.10:5555 100000  ##荣耀_无线USB_无线 adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _16_  c2f5b32c        ##小米_有线USB_手机执行  adb -s c2f5b32c shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _16_  721QECRM2ZMT4   ##魅蓝_有线USB_手机执行  adb -s 721QECRM2ZMT4 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _16_  HMKNW17531001873   ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _16_  192.168.31.89:5555   ##小米_无线USB_无线adb adb -s 192.168.31.89:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _16_  192.168.31.40:5555   ##魅蓝_无线USB_无线adb adb -s 192.168.31.40:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _16_  192.168.31.10:5555   ##荣耀_无线USB_无线adb adb -s 192.168.31.10:5555 shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _17_       ##把 连接adb的 手机 执行 adb dump service 抓取一些配置文件 命令 到 adb_dump_时间戳 文件夹

echo %init_input_0% _18_  ## 安装本地zsoft到zsoft_dest目录 1.解压 2.复制zbin 3.配置环境变量 4.静默安装 5.添加绿色软件桌面icon

echo %init_input_0% _19_        ## ADB 命令 Tel自动加载命令 第19规则

echo %init_input_0% _20_      ## 把当前目录的 media媒体文件 jpg_jpeg_webp_mp4_avi_gif_mp3_txt 改成 数字.类型 形式

echo %init_input_0% _20_   jpg   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

echo %init_input_0% _20_   png   ## 已经当前输入的类型去对匹配的文件改名 改为  数字_.类型 形式

echo %init_input_0% _20_   gif    ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

echo %init_input_0% _20_   mp4   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

echo %init_input_0% _20_   webp   ## 已经当前输入的类型去对匹配的文件改名 改为  数字.类型 形式

echo %init_input_0% _20_   jpg timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

echo %init_input_0% _20_   png timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

echo %init_input_0% _20_   gif timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

echo %init_input_0% _20_   mp4 timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

echo %init_input_0% _20_   webp timestamp  ## 已经当前输入的类型去对匹配的文件改名 改为  数字_时间戳.类型 形式

echo %init_input_0% _21_   ## 查看当前目录下所有文件的 MD属性

echo %init_input_0% _21_ mp4  ## 查看当前目录下所有 mp4文件的MD属性

echo %init_input_0% _21_ jpg   ## 查看当前目录下所有 jpg文件的MD属性

echo %init_input_0% _21_ gif  ## 查看当前目录下所有 gif文件的MD属性

echo %init_input_0% _21_ mdname_true    ## 查看当前目录下所有文件的 MD属性  并把当前名字改为md值

echo %init_input_0% _21_ mp4 mdname_true ## 查看当前目录下所有 mp4文件的MD属性  并把当前名字改为md值

echo %init_input_0% _21_ jpg mdname_true ## 查看当前目录下所有 gif文件的MD属性 并把当前名字改为md值

echo %init_input_0% _21_ gif mdname_true ## 查看当前目录下所有 gif文件的MD属性 并把当前名字改为md值

echo %init_input_0% _22_  control      ##  start control.exe  快速打开控制面板

echo %init_input_0% _22_  regedit      ##  start regedit.exe  快速打开注册表

echo %init_input_0% _22_  dxdiag       ##  start  dxdiag.exe  快速打开 DirectX信息面板

echo %init_input_0% _22_  mstsc        ##  start  mstsc.exe  快速打开 远程桌面连接窗口

echo %init_input_0% _22_  ncpa.cpl     ##  start control ncpa.cpl   快速打开本地连接页面

echo %init_input_0% _22_  gpedit.msc   ##  start gpedit.msc  快速打开组策略

echo %init_input_0% _22_  compmgmt.msc   ##  start  compmgmt.msc  快速打开计算机管理

echo %init_input_0% _22_  secpol.msc   ##  start  secpol.msc  快速打开本地安全策略

echo %init_input_0% _22_  cmd   ##    管理员权限打开CMD_初始路径D盘 【Win+X+Shift+A】 打开管理员CMD的命快键_依次点击

echo %init_input_0% _22_   showfiletype  ##   显示文件的扩展名

echo %init_input_0% _22_   showhiddenfile  ##   显示隐藏文件夹 文件

echo %init_input_0% _22_   wifi        ##  显示wifi信息

echo %init_input_0% _23_  png_jpg  ##  更改当前文件的类型  原类型_目标类型  png_jpg

echo %init_input_0% _23_  _jpg  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 jpg 类型

echo %init_input_0% _23_  jpg_  ##  更改当前文件的类型  原类型_目标类型    jpg 类型 转为  无类型

echo %init_input_0% _23_  _png  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 png 类型

echo %init_input_0% _23_  png_  ##  更改当前文件的类型  原类型_目标类型    png 类型 转为  无类型

echo %init_input_0% _23_  _webp  ##  更改当前文件的类型  原类型_目标类型   无类型 转为 webp 类型

echo %init_input_0% _23_  webp_  ##  更改当前文件的类型  原类型_目标类型    webp 类型 转为  无类型

echo %init_input_0% _23_  _gif  ##  更改当前文件的类型  原类型_目标类型   无类型 转为 gif 类型

echo %init_input_0% _23_  gif_  ##  更改当前文件的类型  原类型_目标类型    gif 类型 转为  无类型

echo %init_input_0% _23_  _mp4  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 mp4 类型

echo %init_input_0% _23_  mp4_  ##  更改当前文件的类型  原类型_目标类型  无类型 转为 mp4 类型

echo %init_input_0% _24_ pre_temp_  end__end    ##  对当前目录的所有实体文件名称 加上前缀 temp_ 后缀加上 _end

echo %init_input_0% _24_ pre_temp_  end_2020  jpg  ##  对当前目录的jpg文件名称 加上前缀 temp_ 后缀加上 2020

echo %init_input_0% _24_ pre_2021  end_base  mp4  ##  对当前目录的mp4文件名称 加上前缀 2021 后缀加上 base

echo %init_input_0% _25_  123_AAA  ##  更改当前文件名称 把 123 替换为 AAA

echo %init_input_0% _25_  123_AAA  jpg ##  更改当前目录的 jpg 文件名称中 把 123 替换为 AAA

echo %init_input_0% _26_  mkdir_zapp  ##  在当前目录创建 zapp 对应的 目录

echo %init_input_0% _26_  mkdir_zmain  ##  在当前目录创建 zmain 对应的 目录

echo %init_input_0% _26_  mkdir_yymmdd_1970zvi  ##  在当前目录创建 temp_media 对应日期的 1970zvi 目录

echo %init_input_0% _27_   192ipend3_115  ipport_44971  paircode_300827 pairport_43173     ## 对当前输入的 192ipend3(IP地址最后三位) 192.168.0.115 的 安卓设置进行无线连接

echo %init_input_0% _27_   10ipend3_115  ipport_44971  paircode_300827 pairport_43173     ## 对当前输入的 10ipend3(IP地址最后三位) 10.106.20.115 的 安卓设置进行无线连接

echo %init_input_0% _27_   10ipend3_  ipport_  paircode_ pairport_     ## 对当前输入的 10ipend3(IP地址最后三位) 10.106.20.115 的 安卓设置进行无线连接

echo %init_input_0% _27_   192ipend3_  ipport_  paircode_ pairport_     ## 对当前输入的 192ipend3(IP地址最后三位) 192.168.0.115 的 安卓设置进行无线连接

echo %init_input_0% _28_   mediafilter_true  ## 搜索所有(包含孙文件)当前目录下的所有多媒体文件 把它们 统一归类到 一个文件类型的文件夹Z_jpg Z_mp4 Z_gif Z_avi Z_webp 中

echo %init_input_0% _28_   typefilter_xlsx  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[xlsx] 把它们 统一归类到 一个文件类型的文件夹Z_xlsx  文件目录中

echo %init_input_0% _28_   typefilter_txt  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[txt] 把它们 统一归类到 一个文件类型的文件夹Z_txt  文件目录中

echo %init_input_0% _28_   typefilter_java  ## 搜索所有(包含孙文件)当前目录下指定的输入类例如[java] 把它们 统一归类到 一个文件类型的文件夹Z_java  文件目录中

echo %init_input_0% _29_       ## 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zapp分类目录 jpg_common_land

echo %init_input_0% _29_      ## 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zapp分类目录 jpg_common_port

echo %init_input_0% _31_  file_xxxx.bat  ##   admin权限复制到 Windows启动路径  explorer.exe  "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp"

echo %init_input_0% _32_       ## main 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zmain分类目录 jpg_common_land

echo %init_input_0% _32_      ## main 过滤当前路径(不包括孙文件)下Port_Land命名的的jpg gif mp4到新建的zmain分类目录 jpg_common_port

echo %init_input_0% _996_     ## 不断循环打开关闭 CMD页面  感觉像 轰炸屏幕 寓意轰炸996  无奈下周修好电脑继续

echo %init_input_0% _997_   ## 打印当前 rule规则的method模板 2x0模板

echo %init_input_0% _998_   ## 打印当前 rule规则的method模板 1x0模板

echo %init_input_0% _999_   ## 打印当前 rule规则的method模板 0x0模板
echo [ruletipprint_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out ruletipprint_func_0x0
::ENDLOCAL
goto:eof




:ruletipanalysis_func_0x1
rem ======================================== ruletipanalysis_func_0x1
rem desc: 对当前用户输入的第一个参数进行匹配  以此调用 对应的 ruleXXX方法
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In ruletipanalysis_func_0x1
if "%init_input_1%"=="" ( 
echo 没有任何 _RuleIndex_ 输入 打印 RuleTip
call :ruletipprint_func_0x0 
echo [ruletipanalysis_func_0x1 EndPrintCode] ruletipanalysis_return_1=[!ruletipanalysis_return_1!]   param1=[__empty__] 
echo ______________Method_Out ruletipanalysis_func_0x1
goto:eof
)
set  rule_tip_index=%init_input_1:~1,-1%
findstr "^:rule%rule_tip_index%v*" %init_f0% > zzZZzz.txt
echo rule_tip_index=[%rule_tip_index%]
set selecte_method_name=:rule%rule_tip_index%v
echo selecte_method_name=[%selecte_method_name%]
for /f %%i in (zzZZzz.txt) do (
set /a Index_F_1+=1
call :isstartwith_func_2x1 %%i  %selecte_method_name%
set local_isstartwith_return_1=!isstartwith_return_1!
echo %init_f0%[!Index_F_1!] == %%i    
if "!local_isstartwith_return_1!"=="true" (
echo  selected_method_name = [%%i] 
set ruletipanalysis_return_1=%%i
echo _____________________________________________________________________ 执行选中方法【 call :!ruletipanalysis_return_1! 】 _______________________________________
call :!ruletipanalysis_return_1!
del zzZZzz.txt
echo [ruletipanalysis_func_0x1 EndPrintCode] ruletipanalysis_return_1=[!ruletipanalysis_return_1!]   param1=[__empty__] 
echo ______________Method_Out ruletipanalysis_func_0x1
goto:eof
)
)
del zzZZzz.txt
echo _____________ 【 未找到输入参数方法 】 _____________
echo [ruletipanalysis_func_0x1 EndPrintCode] ruletipanalysis_return_1=[!ruletipanalysis_return_1!]   param1=[__empty__] 
echo ______________Method_Out ruletipanalysis_func_0x1
::ENDLOCAL
goto:eof




rem ======================== BUSSINESS_OPERATION End======================== 
rem ══════════════════════════════════════════FUNC_DEFINE_AERA End══════════════════════════════════════════
rem ══════════════════════════════════════════ Main_Enter_Aera Begin══════════════════════════════════════════
rem Tip:  methodName_func_xx  中 MethodName不能包含下划线 _
rem Tip:  ruleXXvmethodName_func_xx  是定义 rule方法的固定格式
rem ======================== 主函数main函数入口   
:____Main____
echo __________________ Main_Method Begin _______________________
call ::helloworld_func_0x0
call ::recordfilenametofile_func_1x1 %zbin%
call ::getrandomintwithmaxmin_func_2x1  1000 10000
echo getrandomintwithmaxmin_return_1=!getrandomintwithmaxmin_return_1!
call ::isadminuser_func_0x1
echo  isadminuser_return_1=!isadminuser_return_1!


echo startup_dir=%USERPROFILE%\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup  

rem call ::showlove_func_0x0

rem call ::getfilerownum_func_1x1  D:/TEMP/0415/K3_MD_Rule.java
rem echo getfilerownum_return_1[!getfilerownum_return_1!]

rem set all_environment_var="!win_zbin!;!desktop!;!init_cd!;!zbin!;!init_userprofile!"
rem call ::addpathenvironment_func_1x0  !all_environment_var!

rem call :isemptydirfile_func_1x1 !init_cd!
rem echo isemptydirfile_return_1=!isemptydirfile_return_1!
rem call :stringreplace_func_3x1 123456789  123  ""
rem echo stringreplace_return_1=!stringreplace_return_1!

rem call :gettimemmddhhmm_func_0x1
rem echo gettimemmddhhmm_func_0x1=!gettimemmddhhmm_func_0x1!



rem call :getandroidreceivembwithin5s_func_0x1
rem set currentdev_receive_MD=!getandroidreceivembwithin5s_return_1!
rem echo currentdev_receive_MD=!currentdev_receive_MD!  getandroidreceivembwithin5s_return_1=!getandroidreceivembwithin5s_return_1!

echo __________________ Main_Method End _______________________
goto:eof
rem ══════════════════════════════════════════ Main_Enter_Aera End══════════════════════════════════════════
