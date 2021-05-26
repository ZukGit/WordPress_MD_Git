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
set recordFileNameToFile_return_1=%1\Z_temp.txt
echo recordFileNameToFile_return_1=[%recordFileNameToFile_return_1%]  param1=[%1]  
echo [recordfilenametofile_func_1x1 EndPrintCode]   recordfilenametofile_return_1=[!recordfilenametofile_return_1!]   param1=[%1]   
echo ______________Method_Out recordfilenametofile_func_1x1
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
echo searchOneTargetFile4Dir4Type_func_2x1 input_1_param == %1
echo searchOneTargetFile4Dir4Type_func_2x1 input_2_param == %2
set /a Index_searchOneTargetFile4Dir4Type = 0
for /r %1 %%j in  (*%2*)do (
set /a Index_searchOneTargetFile4Dir4Type+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo Index_searchOneTargetFile4Dir4Type[!Index_searchOneTargetFile4Dir4Type!] == %%j
set searchOneTargetFile4Dir4Type_return_1=%%j
echo searchOneTargetFile4Dir4Type_return_1=[%searchOneTargetFile4Dir4Type_return_1%]  param1=[%1]  param2=[%2] 
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


:addpathenvironment_func_1x0
rem ======================================== addpathenvironment_func_1x0
rem desc: 添加一个全路径到环境变量 PATH中   echo %PATH%   实际执行 setx "Path" "%1;%path%" /m
rem sample: call :addpathenvironment_func_1x0  %zbin%
rem sample_out: 把 C:\Users\xxxx\Desktop\zbin 假如到环境变量 PATH
::SETLOCAL
echo ______________Method_In addpathenvironment_func_1x0
echo addPathEnvironment_func_1x0 input_1_param == %1
echo  the new item dir add to Environment->Path == [%1]
echo  setx "Path" "%1;%path%" /m
setx "Path" "%1;%path%" /m
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




rem ======================== TEST_OPERATION End======================== 
rem ======================== BUSSINESS_OPERATION Begin======================== 


:rule1vbankupapk_func_0x0
rem ======================================== rule1vbankupapk_func_0x0
rem rule_tip: %init_input_0% _1_   ## 备份bankup所有当前手机安装的apk本地PC本地目录
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
rem rule_tip: %init_input_0% _2_   ## 获取手机当前正在运行的APK到本地
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule2vgetapk_func_0x0
:rule2vgetapk_getapp_block
rem del rule2.txt
rem del rule2_3app.txt
echo  获取当前正在运行的APK 
rem adb shell dumpsys window | findstr "mCurrentFocus"
rem mCurrentFocus=Window{52b526e u0 com.autonavi.minimap/com.autonavi.map.activity.NewMapActivity}
adb shell dumpsys window | findstr "mCurrentFocus" > rule2.txt 
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
) else (  
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
rem rule_tip: %init_input_0% _8_ 400 800 1000  ## 点击屏幕位置 x y repeat 重复 repeat次 默认1000次

rem desc: %init_input_0% _8_ 400 800 1000  点击屏幕位置 x y repeat 重复 repeat次 默认1000次
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




:rule15vxmusbcreendown_func_1x0
rem ======================================== rule15vxmusbcreendown_func_1x0
rem rule_tip: %init_input_0% _15_   100000   ##小米_有线USB_手机执行 adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem desc: 小米_有线USB_手机执行 adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule15vxmusbcreendown_func_1x0
set rule15vxmusbcreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule15vxmusbcreendown_dynamic_param1=100000
) else (
set rule15vxmusbcreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule15vxmusbcreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule15vxmusbcreendown_dynamic_param1!]     
adb -s c2f5b32c shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s c2f5b32c shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s c2f5b32c shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule15vxmusbcreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule15vxmusbcreendown_dynamic_param1!]        
adb -s c2f5b32c  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s c2f5b32c shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s c2f5b32c  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule15vxmusbcreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule15vxmusbcreendown_dynamic_param1!]   
echo ______________Method_Out rule15vxmusbcreendown_func_1x0
::ENDLOCAL
goto:eof




:rule16vmzusbcreendown_func_1x0
rem ======================================== rule16vmzusbcreendown_func_1x0
rem rule_tip: %init_input_0% _16_   100000   ##魅族_有线USB_手机执行 adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem desc: 魅族_有线USB_手机执行 adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule16vmzusbcreendown_func_1x0
set rule16vmzusbcreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule16vmzusbcreendown_dynamic_param1=10000
) else (
set rule16vmzusbcreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule16vmzusbcreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule16vmzusbcreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule16vmzusbcreendown_dynamic_param1!]     
adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 721QECRM2ZMT4 shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule16vmzusbcreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule16vmzusbcreendown_dynamic_param1!]        
adb -s 721QECRM2ZMT4  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 721QECRM2ZMT4 shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 721QECRM2ZMT4  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule16vmzusbcreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule16vmzusbcreendown_dynamic_param1!]   
echo ______________Method_Out rule16vmzusbcreendown_func_1x0
::ENDLOCAL
goto:eof




:rule17vhnusbcreendown_func_1x0
rem ======================================== rule17vhnusbcreendown_func_1x0
rem rule_tip: %init_input_0% _17_   100000   ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

rem desc: 荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule17vhnusbcreendown_func_1x0
set rule17vhnusbcreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule17vhnusbcreendown_dynamic_param1=100000
) else (
set rule17vhnusbcreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule17vhnusbcreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule17vhnusbcreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule17vhnusbcreendown_dynamic_param1!]     
adb -s HMKNW17531001873 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s HMKNW17531001873 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s HMKNW17531001873 shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule17vhnusbcreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule17vhnusbcreendown_dynamic_param1!]        
adb -s HMKNW17531001873  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s HMKNW17531001873 shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s HMKNW17531001873  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule17vhnusbcreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule17vhnusbcreendown_dynamic_param1!]   
echo ______________Method_Out rule17vhnusbcreendown_func_1x0
::ENDLOCAL
goto:eof




:rule18vxmwirelesscreendown_func_1x0
rem ======================================== rule18vxmwirelesscreendown_func_1x0
rem rule_tip: %init_input_0% _18_   100000   ##小米_无线USB_无线adb  adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次

rem desc: 小米_无线USB_无线adb adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule18vxmwirelesscreendown_func_1x0
set rule18vxmwirelesscreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule18vxmwirelesscreendown_dynamic_param1=100000
) else (
set rule18vxmwirelesscreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule18vxmwirelesscreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule18vxmwirelesscreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule18vxmwirelesscreendown_dynamic_param1!]     
adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.89:5555 shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule18vxmwirelesscreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule18vxmwirelesscreendown_dynamic_param1!]        
adb -s 192.168.31.89:5555  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.89:5555 shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.89:5555  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule18vxmwirelesscreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule18vxmwirelesscreendown_dynamic_param1!]   
echo ______________Method_Out rule18vxmwirelesscreendown_func_1x0
::ENDLOCAL
goto:eof




:rule19vmzwirelesscreendown_func_1x0
rem ======================================== rule19vmzwirelesscreendown_func_1x0
rem rule_tip: %init_input_0% _19_   100000   ##魅蓝_无线USB_无线adb  adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次

rem desc: 魅蓝_无线USB_无线adb adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule19vmzwirelesscreendown_func_1x0
set rule19vmzwirelesscreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule19vmzwirelesscreendown_dynamic_param1=100000
) else (
set rule19vmzwirelesscreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule19vmzwirelesscreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule19vmzwirelesscreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule19vmzwirelesscreendown_dynamic_param1!]     
adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.40:5555 shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule19vmzwirelesscreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule19vmzwirelesscreendown_dynamic_param1!]        
adb -s 192.168.31.40:5555  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.40:5555 shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.40:5555  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule19vmzwirelesscreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule19vmzwirelesscreendown_dynamic_param1!]   
echo ______________Method_Out rule19vmzwirelesscreendown_func_1x0
::ENDLOCAL
goto:eof




:rule20vhnwirelesscreendown_func_1x0
rem ======================================== rule20vhnwirelesscreendown_func_1x0
rem rule_tip: %init_input_0% _20_   100000   ##荣耀_无线USB_无线adb  adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次

rem desc: 荣耀_无线USB_无线adb adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In rule20vhnwirelesscreendown_func_1x0
set rule20vhnwirelesscreendown_dynamic_param1=
if "%init_input_2%"=="" (
set  rule20vhnwirelesscreendown_dynamic_param1=100000
) else (
set rule20vhnwirelesscreendown_dynamic_param1=%init_input_2% 
)
echo [rule3vadbscreen_func_1x0 ]    output=[__empty__] dynamic_param1=[!rule20vhnwirelesscreendown_dynamic_param1!]   
for /l %%i in (1, 1, !rule20vhnwirelesscreendown_dynamic_param1!) do (
echo i == %%i   _todown   allLoop=[!rule20vhnwirelesscreendown_dynamic_param1!]     
adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400  
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.10:5555 shell input swipe 340 400 340 800
ping -n 1 127.0.0.1>nul
)
for /l %%i in (1, 1, !rule20vhnwirelesscreendown_dynamic_param1!) do (
echo i == %%i  _toup     allLoop=[!rule20vhnwirelesscreendown_dynamic_param1!]        
adb -s 192.168.31.10:5555  shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.10:5555 shell input swipe  340 400  340 1200
ping -n 1 127.0.0.1>nul
adb -s 192.168.31.10:5555  shell input swipe  340 800 340 400
ping -n 1 127.0.0.1>nul
)
echo [rule20vhnwirelesscreendown_func_1x0 EndPrintCode]    output=[__empty__] dynamic_param1=[!rule20vhnwirelesscreendown_dynamic_param1!]   
echo ______________Method_Out rule20vhnwirelesscreendown_func_1x0
::ENDLOCAL
goto:eof




:ruletipprint_func_0x0
rem ======================================== ruletipprint_func_0x0
rem desc: Bussiness_Rule打印程序用于打印batrule规则序列
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In ruletipprint_func_0x0
echo %init_input_0% _1_   ## 备份bankup所有当前手机安装的apk本地PC本地目录

echo %init_input_0% _2_   ## 获取手机当前正在运行的APK到本地

echo %init_input_0% _3_  ##安装当前目录的apk到手机

echo %init_input_0% _4_ 10000   ##手机执行 adb shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _5_  1000000  ##手机执行 adb shell input swipe 340 1000 340 100 10 快速往下滑动 不打印Log了 默认执行百万次

echo %init_input_0% _6_  ## 执行清空apk的操作

echo %init_input_0% _7_  ## 蓝牙开启关闭 默认执行1000次

echo %init_input_0% _8_ 400 800 1000  ## 点击屏幕位置 x y repeat 重复 repeat次 默认1000次

echo %init_input_0% _9_  ## wifi开启关闭  默认1000次

echo %init_input_0% _10_  1000  ## GPS开启关闭  默认1000次

echo %init_input_0% _11_   ## 开启wifi连接WPS网络

echo %init_input_0% _12_  www.baidu.com  ## adb输入字符串 默认为 www.baidu.com

echo %init_input_0% _13_   ## 对当前手机屏幕截屏并拉取到手机本地

echo %init_input_0% _14_     ## 对当前手机屏幕录屏 然后拔出插入 使得mp4文件拉取到手机本地

echo %init_input_0% _15_   100000   ##小米_有线USB_手机执行 adb -s c2f5b32c shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _16_   100000   ##魅族_有线USB_手机执行 adb -s 721QECRM2ZMT4 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _17_   100000   ##荣耀_有线USB_手机执行 adb -s HMKNW17531001873 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认10000次

echo %init_input_0% _18_   100000   ##小米_无线USB_无线adb  adb -s 192.168.31.89:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次

echo %init_input_0% _19_   100000   ##魅蓝_无线USB_无线adb  adb -s 192.168.31.40:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次

echo %init_input_0% _20_   100000   ##荣耀_无线USB_无线adb  adb -s 192.168.31.10:5555 shell input swipe 340 1200 340 400 命令向下滑动两下向上滑动一下 默认100000次
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
call :helloworld_func_0x0

call :recordfilenametofile_func_1x1 %zbin%
echo __________________ Main_Method End _______________________
goto:eof
rem ══════════════════════════════════════════ Main_Enter_Aera End══════════════════════════════════════════
