@ECHO off
setlocal enabledelayedexpansion
rem ══════════════════════════════════════════ System_Init_Aera_Begin  ══════════════════════════════════════════

rem 函数定义之前的提示必须以英文结尾 否则 可能 报出一些 找不到之类的 错误 ----  
rem ________________ 系统路径初始化   
set init_cd=%cd%
set init_dp0=%~dp0
set init_f0=%~f0
set init_path=%path%
set init_input_0=%0
echo init_cd=%init_cd%               rem %cd% === 当前执行命令的当前路径  C:\Users\zhuxx  
echo init_f0=%init_f0%               rem %~f0 === 当前执行文件的全路径       C:\Users\zhuzj5\Desktop\zbin\win_zbin\zzbattest_10.bat   
echo init_input_0=%init_input_0%     rem %0 ===当前执行文件的名称 init_input_0=zzbattest_10.bat  
echo init_dp0=%init_dp0%             rem %~dp0 ===  当前执行文件的文件夹名称  init_dp0 = C:\Users\zhuxx\Desktop\zbin\win_zbin\  
echo init_path=%init_path%           rem %path% === 当前的系统坏境变量PATH
rem init_path=D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\bin;C:\Users\zhuzj5\Desktop\zbin\win_soft\Redis;C:\Program Files\Tesseract-OCR;D:\software\ffmpeg\bin;C:\Users\zhuzj5\Desktop\zbin\lin_zbin;C:\Swift\bin\;C:\Users\zhuzj5\Desktop\zbin\mac_zbin;C:\Users\zhuzj5\Desktop\zbin\win_zbin;C:\Users\zhuzj5\Desktop\zbin\python;
rem D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5\ConEmu\Scripts;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\conemu-maximus5\ConEmu;C:\Windows\System32;C:\Users\zhuzj5\Desktop\zbin\win_zbin;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\jre\bin;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\npp.7.8.9.bin.x64;
rem D:\ZWin_Software\D0_Environment_Zip_Dir_Path\python-3.7.9-embed-amd64;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\python-3.7.9-embed-amd64/Scripts;C:\Program Files (x86)\Qualcomm\QUTS\bin;C:\Program Files (x86)\Qualcomm\QXDM5;C:\Program Files (x86)\Qualcomm\PCAT\bin;
rem C:\Program Files (x86)\Qualcomm\EUD;C:\Program Files (x86)\Qualcomm\QIKTool\1.0.109.1;C:\Windows;C:\Windows\System32;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\ADB;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\npp.7.8.9.bin.x64;C:\Users\zhuzj5\Desktop\zbin\win_zbin;C:\Program Files (x86)\Graphviz2.38\bin;
rem D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\bin;C:\Program Files\Tesseract-OCR;C:\Users\zhuzj5\AppData\Local\Google\Chrome\Application;D:\ZWin_Software\D0_Environment_Zip_Dir_Path\JDK8_64\jre\bin;C:\Users\zhuzj5\AppData\Local\Android\Sdk\platform-tools;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\cmd;C:\Program Files\Tesseract-OCR;C:\Users\zhuzj5\Desktop\zbin\lin_zbin;C:\Users\zhuzj5\Desktop\zbin\mac_zbin;
rem C:\Users\zhuzj5\Desktop\zbin\win_zbin;C:\Users\zhuzj5\AppData\Local\Programs\Microsoft VS Code\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\usr\bin;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\vendor\git-for-windows\usr\share\vim\vim74;D:\ZWin_Software\C1_GreenSoft_Zip_Dir\cmder\
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
echo init_userprofile=%init_userprofile%     rem %userprofile% 标示为 用户主目录 init_userprofile=C:\Users\zhuzj5  
echo desktop=%desktop%                       rem init_desktop 和 desktop 标示 桌面路径 C:\Users\zhuzj5\Desktop
echo init_desktop=%init_desktop%             rem init_desktop 和 desktop 标示 桌面路径 C:\Users\zhuzj5\Desktop
echo zbin=%zbin%                             rem zbin 和 init_zbin 标示 桌面zbin路径 C:\Users\zhuzj5\Desktop\zbin
echo init_zbin=%init_zbin%                   rem zbin 和 init_zbin 标示 桌面zbin路径 C:\Users\zhuzj5\Desktop\zbin
echo win_zbin=%win_zbin%                     rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\Users\zhuzj5\Desktop\zbin\win_zbin
echo init_win_zbin=%init_win_zbin%           rem win_zbin 和 init_win_zbin 标示 桌面zbin路径里的win_zbin C:\Users\zhuzj5\Desktop\zbin\win_zbin
echo=
echo=
echo=
echo=

rem ________________ 输入参数打印  input_0=[zzbattest_10.bat]     init_input_0=[zzbattest_10.bat]  
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
echo input_0=[%0]     init_input_0=[%init_input_0%]
echo input_1=[%1]     init_input_1=[%init_input_1%] 
echo input_2=[%2]     init_input_2=[%init_input_2%]
echo input_3=[%3]     init_input_3=[%init_input_3%]
echo input_4=[%4]     init_input_4=[%init_input_4%]
echo input_5=[%5]     init_input_5=[%init_input_5%]
echo input_6=[%6]     init_input_6=[%init_input_6%]
echo input_7=[%7]     init_input_7=[%init_input_7%]
echo input_8=[%8]     init_input_8=[%init_input_8%]
echo input_9=[%9]     init_input_9=[%init_input_9%]
echo=
echo=
echo=
echo=
rem ══════════════════════════════════════════ System_Init_Aera_End  ══════════════════════════════════════════  

rem ══════════════════════════════════════════ Program_Execute_Aera_Begin  ════════════════════════════ 

call :test_func_0x0

goto:eof
rem ══════════════════════════════════════════ Program_Execute_Aera_End  ══════════════════════════════════════════ 

rem ══════════════════════════════════════════ Func_Define_Aera_Begin ═════════════════════════════════════════════


rem ========================  File_Operation_Begin ================================================

rem ======================== recordFileNameToFile_func_1x1
rem 不是全路径 到 当前目录的  ./Z_temp.txt 文件夹中 并返回这个路径
rem 打印当前指定目录下的所有文件的文件名  
:recordFileNameToFile_func_1x1
::SETLOCAL
echo recordFileNameToFile_func_1x1  input_1_param == %1
dir *.* /o:-d /B %1 > %1\Z_TEMP.txt
set recordFileNameToFile_return_1=%1\Z_temp.txt
echo recordFileNameToFile_return_1=[%recordFileNameToFile_return_1%]  param1=[%1]  

::ENDLOCAL
goto:eof





rem ======================== searchLastFile_func_1x1
rem 检测当前目录下 时间最新的那么文件 
rem searchLastFile_func_1x1 接受一个路径参数  给出该路径下 最新的那个 实体文件名称   函数的返回值 一致 定义为 函数名_return 
rem searchLastFile_return=K3_MD_Rule.class
:searchLastFile_func_1x1  
::SETLOCAL
echo searchLastFile_func_1x1 input_1_param == %1
for /f "delims=" %%j in ('dir %1 /o-d /a /b') do (
echo searchLastFile_return_1=%%j 
set  searchLastFile_return_1=%%j
echo searchLastFile_return_1=[!searchLastFile_return_1!]  param1=[%1]  

::ENDLOCAL
goto:eof
)

rem ======================== searchLastFullPath_func_1x1
rem searchLastFile_func 接受一个路径参数  给出该路径下 最新的那个 实体文件名称   函数的返回值 一致 定义为 函数名_return 
rem searchLastFullPath_return=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class 
:searchLastFullPath_func_1x1  
::SETLOCAL
echo searchLastFullPath_func_1x1 input_1_param == %1
for /f "delims=" %%j in ('dir %1 /o-d /a /b /s') do (
echo searchLastFullPath_return_1=%%j 
set  searchLastFullPath_return_1=%%j
echo searchLastFullPath_return_1=[!searchLastFullPath_return_1!]  param1=[%1]  

::ENDLOCAL
goto:eof
)







rem ======================== getFileName_withFullPath_func_1x1
rem 参数给一个文件的全路径   返回这个文件的文件名称 名称.类型 
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem  ~nx1=K3_MD_Rule.class
:getFileNameWithFullPath_func_1x1  
::SETLOCAL
echo getFileNameWithFullPath_func_1x1 input_1_param == %1
set getFileNameWithFullPath_return_1=%~nx1
echo getFileNameWithFullPath_return_1=[%getFileNameWithFullPath_return_1%]  param1=[%1]  
::ENDLOCAL
goto:eof

rem ======================== getFileTypeWithFullPath_func_1x1
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~x1=.class
:getFileTypeWithFullPath_func_1x1  
::SETLOCAL
echo getFileTypeWithFullPath_func_1x1 input_1_param == %1
set getFileTypeWithFullPath_return_1=%~x1
echo getFileTypeWithFullPath_return_1=[%getFileTypeWithFullPath_return_1%]  param1=[%1]  

::ENDLOCAL
goto:eof

rem ======================== getFileNameNoPointWithFullPath_func_1x1
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~n=K3_MD_Rule
:getFileNameNoPointWithFullPath_func_1x1  
::SETLOCAL
echo getFileNameNoPointWithFullPath_func_1x1 input_1_param == %1
set getFileNameNoPointWithFullPath_return_1=%~n1
echo getFileNameNoPointWithFullPath_return_1=[%getFileNameNoPointWithFullPath_return_1%]  param1=[%1]  
::ENDLOCAL
goto:eof


rem ======================== getParentFilePathWithFullPath_func_1x1
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~p1=\Users\zhuzj5\Desktop\zbin\
:getParentFilePathWithFullPath_func_1x1  
::SETLOCAL
echo getParentFilePathWithFullPath_func_1x1 input_1_param == %1
set getParentFilePathWithFullPath_return_1=%~p1
echo getParentFilePathWithFullPath_return_1=[%getParentFilePathWithFullPath_return_1%]  param1=[%1]  
::ENDLOCAL
goto:eof


rem ======================== getFileSizeByteWithFullPath_func_1x1
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~z=28529
:getFileSizeByteWithFullPath_func_1x1  
::SETLOCAL
echo getFileSizeByteWithFullPath_func_1x1 input_1_param == %1
set getFileSizeByteWithFullPath_return_1=%~z1
echo getFileSizeByteWithFullPath_return_1=[%getFileSizeByteWithFullPath_return_1%]  param1=[%1]  

::ENDLOCAL
goto:eof



rem ======================== showfile_func_1x0
rem fileAbsPath=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~a=--a--------
rem ~d=C:
rem ~f=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class
rem ~n=K3_MD_Rule
rem ~s=C:\Users\zhuzj5\Desktop\zbin\K37DAE~1.CLA
rem ~p=\Users\zhuzj5\Desktop\zbin\
rem ~x=.class
rem ~z=28529
rem ~dp=C:\Users\zhuzj5\Desktop\zbin\
rem %~nx1=K3_MD_Rule.class
rem ~fs=C:\Users\zhuzj5\Desktop\zbin\K37DAE~1.CLA
rem ~dpn=C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule
rem 接受一个文件路径 返回这个文件的详细信息  没有返回值  
:showfile_func_1x0
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

goto:eof





rem ======================== showSubFile4Dir4Type_func_2x0
rem 打印显示指定目录的指定类型  参数1 全目录路径  参数2 过滤类型 *.xx
:showSubFile4Dir4Type_func_2x0
echo showSubFile4Dir4Type_func_2x0 input_1_param == %1
echo showSubFile4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showSubFile4Dir4Type = 0
for /f "delims=\" %%a in ('dir /b /a-d /o-d "%1\%2"') do (
set /a Index_showSubFile4Dir4Type+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo Index_showSubFile4Dir4Type[!Index_showSubFile4Dir4Type!] == %1\%%a 
)
echo showSubFile4Dir4Type_func_2x0 Index_showSubFile4Dir4Type=[!Index_showSubFile4Dir4Type!]  param1=[%1]   param2=[%2] 

goto:eof







rem ======================== showSubDir4Dir4Type_func_2x0
rem 打印显示指定目录的名称的文件夹  参数1 全目录路径  参数2 过滤类型 123 * 
:showSubDir4Dir4Type_func_2x0
echo showSubDir4Dir4Type_func_2x0 input_1_param == %1
echo showSubDir4Dir4Type_func_2x0 input_2_param == %2
set /a showSubDir4Dir4Type = 0
for /d %%a in (%1\%2) do (
set /a showSubDir4Dir4Type+=1
echo showSubDir4Dir4Type[!showSubDir4Dir4Type!] == %%a 
)
goto:eof

rem ======================== showSubFileAndDir_func_1x0
rem 打印当前所有的文件与目录
:showSubFileAndDir_func_1x0
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

goto:eof

rem ======================== showAllSubFile4Dir4Type_func_2x0
rem 循环打印当前指定目录下的所有子文件  参数1 全目录路径  参数2 过滤类型 *.xx
:showAllSubFile4Dir4Type_func_2x0
echo showAllSubFile4Dir4Type_func_2x0 input_1_param == %1
echo showAllSubFile4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showAllSubFile4Dir4Type = 0
for /r %1 %%j in  (*%2*)do (
set /a Index_showAllSubFile4Dir4Type+=1
echo Index_showAllSubFile4Dir4Type[!Index_showAllSubFile4Dir4Type!] == %%j 
)
echo showAllSubFile4Dir4Type_func_2x0 Index_showAllSubFile4Dir4Type=[!Index_showAllSubFile4Dir4Type!]  param1=[%1]  param2=[%2] 

goto:eof



rem ======================== searchOneTargetFile4Dir4Type_func_2x1
rem 找到指定的唯一一个符合过滤条件的文件 返回赋值返回  参数1 全目录路径  参数2 过滤类型 *.xx
:searchOneTargetFile4Dir4Type_func_2x1
::SETLOCAL
echo searchOneTargetFile4Dir4Type_func_2x1 input_1_param == %1
echo searchOneTargetFile4Dir4Type_func_2x1 input_2_param == %2
set /a Index_searchOneTargetFile4Dir4Type = 0
for /r %1 %%j in  (*%2*)do (
set /a Index_searchOneTargetFile4Dir4Type+=1
rem %1\%%a    %%a是文件名字    %1 是当前搜索目录
echo Index_searchOneTargetFile4Dir4Type[!Index_searchOneTargetFile4Dir4Type!] == %%j
set searchOneTargetFile4Dir4Type_return_1=%%j
echo searchOneTargetFile4Dir4Type_return_1=[%searchOneTargetFile4Dir4Type_return_1%]  param1=[%1]  param2=[%2] 
goto:eof
)
::ENDLOCAL
goto:eof



rem ======================== showAllSubDir4Dir4Type_func_2x0
rem 打印显示指定目录的名称的文件夹  参数1 全目录路径  参数2 过滤类型 123 * 
:showAllSubDir4Dir4Type_func_2x0
echo showAllSubDir4Dir4Type_func_2x0 input_1_param == %1
echo showAllSubDir4Dir4Type_func_2x0 input_2_param == %2
set /a Index_showAllSubDir4Dir4Type = 0
for /r  %1 /d %%a in (*%2*) do (
set /a Index_showAllSubDir4Dir4Type+=1
echo Index_showAllSubDir4Dir4Type[!Index_showAllSubDir4Dir4Type!] == %%a 
)
echo showAllSubDir4Dir4Type_func_2x0 noReturn flitter_num=[!Index_showAllSubDir4Dir4Type!]  param1=[%1] param2=[%2]

goto:eof



rem ======================== showAllSubFileAndDir_func_1x0
rem 打印当前所有的文件与目录
:showAllSubFileAndDir_func_1x0
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
goto:eof


rem ======================== isFileExist_func_1x1
rem 判断当前文件是否存在
:isFileExist_func_1x1
::SETLOCAL
echo showAllSubFileAndDir_func_1x0 input_1_param == %1
if exist %1 (
set isFileExist_return_1=true
) else (
set isFileExist_return_1=false
)
echo isFileExist_return_1=[%isFileExist_return_1%]  param1=[%1] 
::ENDLOCAL
goto:eof



rem ======================== readFile_func_1x0
rem 读取文件的每一行
:readFile_func_1x0
echo readFile_func_1x0 input_1_param == %1
if exist %1 (
echo readFile_func_1x0 Success Read -> File not exist for --> readFile_func_1x0 input_1_param == %1
  for /f "delims=" %%l  in (%1) do ( 
       set /a num+=1 
       set s=%%l
	   echo line[!num!] = %%l
       )
   ) else (
echo readFile_func_1x0 Failed Read -> File not exist for --> readFile_func_1x0 input_1_param == %1
   )

goto:eof



rem ================================================  File_Operation_End ================================================


rem ================================================  System_Operation_Begin ================================================
rem ======================== addPathEnvironment_func_1x0
rem    把参数路径 添加环境变量Path中
:addPathEnvironment_func_1x0
echo addPathEnvironment_func_1x0 input_1_param == %1
echo  the new item dir add to Environment->Path == [%1]
echo  setx "Path" "%1;%path%" /m
setx "Path" "%1;%path%" /m
goto:eof

rem ======================== openFileWithNotepad_func_1x0
rem  使用 notepad++打开文件
:openFileWithNotepad_func_1x0
echo  openFileWithNotepad_func_1x0 input_1_param == %1
echo  open the file with notepad++.exe -> [%1]
notepad++.exe   %1
goto:eof


rem ======================== getTimeStamp_func_0x1
rem  使用 notepad++打开文件 
:getTimeStamp_func_0x1
::SETLOCAL
set getTimeStamp_hh=%time:~0,2%
if /i %getTimeStamp_hh% LSS 10 (set getTimeStamp_hh=0%time:~1,1%)
set getTimeStamp_return_1=%date:~0,4%%date:~5,2%%date:~8,2%_%getTimeStamp_hh%%time:~3,2%%time:~6,2%
::ENDLOCAL
goto:eof

rem ======================== getTimeNona_func_0x1
rem  获取当前毫秒时间 
:getTimeNona_func_0x1
::SETLOCAL
set getTimeNona_return_1=%DATE:~0,4%-%DATE:~5,2%-%DATE:~8,2% %TIME:~0,2%:%TIME:~3,2%:%TIME:~6,2%.%TIME:~9,2%
echo getTimeNona_return_1=%getTimeNona_return_1%
::ENDLOCAL
goto:eof

rem ======================== createDirWithPath_func_1x0
:createDirWithPath_func_1x0
echo  createDirWithPath_func_1x0 input_1_param == %1
rem 创建指定输入全路径下的文件夹
md  %1
goto:eof


rem ======================== explorerWithPath_func_1x0
:explorerWithPath_func_1x0
echo  explorerWithPath_func_1x0 input_1_param == %1
rem 使用文件管理器打开 对应的目录
explorer.exe  %1
goto:eof




rem ================================================  System_Operation_End ================================================


rem ================================================  String_Operation_Begin ================================================
rem ======================== string_test_func_0x0
:string_test_func_0x0
echo  string_test_func_0x0 input_1_param == empty
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


goto:eof



rem ======================== stringTrim_func_1x1
rem   去除当前所有的字符串中的空格
:stringTrim_func_1x1
::SETLOCAL
echo  stringTrim_func_1x1 input_1_param ==[%1]
set trim_temp=%1
rem  去除掉所有的空格 路径字符串 有用  
set trim_str=%trim_temp: =%
echo stringTrim_return_1 = [%trim_str%]
set stringTrim_return_1=%trim_str%
echo stringTrim_return_1=[%stringTrim_return_1%]  param1=[%1] 
::ENDLOCAL
GOTO:EOF

rem ======================== getSubStringWithPre_func_2x1
rem  获取一个字符串  和 后置标识字符串串 , 返回以它为前缀的(不包含它的) 后面的字符串
:getSubStringWithPre_func_2x1
::SETLOCAL
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
if "%getSubStringWithPre_param_str_trim1%"=="" (
GOTO:EOF
)

rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithPre_param_str_trim2%"=="" (
GOTO:EOF
)

set isContainString_return_1=false
call :isContainString_func_2x1 %getSubStringWithPre_param_str_1%   %getSubStringWithPre_param_str_2%
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithPre_func_2x1  Str1_Param do not contain Str2_Param "
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
rem  echo  getSubStringWithPre_func_2x1  execute Failed! the First-String-Length  small than  Second-String-Length
rem  echo  getSubStringWithPre_func_2x1 input_1_param ==[%1]
rem  echo  getSubStringWithPre_func_2x1 input_2_param ==[%2]
rem  GOTO:EOF
rem  ) else (
rem  echo  First-String-Length  Second-String-Length  ,  continue execute ! 
rem  )

::ENDLOCAL
GOTO:EOF




rem ======================== getSubStringWithEnd_func_2x1
rem  获取一个字符串  和标识字符串串 , 返回以它为后缀的(不包含它的) 的原始字符串前面的字符串
:getSubStringWithEnd_func_2x1
::SETLOCAL
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
GOTO:EOF
)
echo x2  getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]
rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithEnd_param_str_2_nopadding%"=="" (
GOTO:EOF
)
echo x1getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]

set isContainString_return_1=false
call :isContainString_func_2x1 "%getSubStringWithEnd_param_str_1_nopadding%"   "%getSubStringWithEnd_param_str_2_nopadding%"
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithEnd_func_2x1  Str1_Param do not contain Str2_Param "
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
rem echo getSubStringWithEnd_result=[%getSubStringWithEnd_result%]
set getSubStringWithEnd_return_1=%getSubStringWithEnd_result%
echo getSubStringWithEnd_return_1=[%getSubStringWithEnd_return_1%]   param1=[%1]   param2=[%2] 

::ENDLOCAL

GOTO:EOF




rem ======================== getSubStringWithPreEndTag_func_3x1
rem package:/data/app/~~iTJ7qQFCuiitsOv-NxYfsQ==/com.android.gpstest-8aetQzpfCBbjIcHfnhH_kA==/base.apk=com.android.gpstest
rem  获取一个字符串  并且有一个 前置 和 一个后置  返回  前置 和 后置 之间的 字符串的内容  
:getSubStringWithPreEndTag_func_3x1
::SETLOCAL
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
            echo xxxxxxaaaaa1 getSubStringWithPre_return_1=%getSubStringWithPre_return_1%  param_str_trim1_nopadding=[%param_str_trim1_nopadding%]  param_str_trim2_nopadding=[%param_str_trim2_nopadding%]
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

::ENDLOCAL

GOTO:EOF

rem ======================== isContainString_func_2x1
rem 获取字符串大小    
:isContainString_func_2x1
::SETLOCAL
echo  isContainString_func_2x1 input_1_param ==[%1]
echo  isContainString_func_2x1 input_2_param ==[%2]

call :clearStringPadding_func_1x1  %1
set isContainString_str=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set isContainString_matchStr=%clearStringPadding_return_1%
rem set isContainString_str=%1
rem set isContainString_matchStr=%2
set isContainString_return_1=false
if not "x!isContainString_str:%isContainString_matchStr%=!"=="x%isContainString_str%" (
set isContainString_return_1=true
) else (
set isContainString_return_1=false
)
echo isContainString_return_1=[%isContainString_return_1%] param1=[%1]  param2=[%2] 
::ENDLOCAL
GOTO:EOF


rem ======================== getStringLength_func_1x1
rem 获取字符串大小 
:getStringLength_func_1x1
::SETLOCAL
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
::ENDLOCAL
GOTO:EOF












rem ======================== clearStringPadding_func_1x1
rem 去除字符串两边的引号 
:clearStringPadding_func_1x1
::SETLOCAL
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
rem clearStringPadding_index_end 恢复1 
set /a clearStringPadding_index_end+=1
echo clearStringPadding_index_end=[%clearStringPadding_index_end%]
if !clearStringPadding_index_end! EQU 0 ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr!) else ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr:~-0,%clearStringPadding_index_end%!)

echo clearStringPadding_pre_end_str=[%clearStringPadding_pre_end_str%]
set clearStringPadding_return_1=%clearStringPadding_pre_end_str%
echo clearStringPadding_return_1=[%clearStringPadding_pre_end_str%]  param1=[%1] 

::ENDLOCAL
GOTO:EOF


rem ======================== getInt4SubStringIndexOf_func_2x1
rem 获取子字符串在原字符串中的起始索引  如果不包含 那么返回 -1  
:getInt4SubStringIndexOf_func_2x1
::SETLOCAL
echo  getInt4SubStringIndexOf_func_2x1 input_1_param ==[%1]
echo  getInt4SubStringIndexOf_func_2x1 input_2_param ==[%2]
set getInt4SubStringIndexOf_return_1=-1

set isContainString_return_1=false
call :isContainString_func_2x1 %1  %2
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getInt4SubStringIndexOf_func_2x1  Str1_Param do not contain Str2_Param "
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
set getInt4SubStringIndexOf_return_1=%getInt4SubStringIndexOf_step_index%  param1=[%1]   param2=[%2]

::ENDLOCAL
GOTO:EOF

rem ================================================  String_Operation_End ================================================


rem ================================================  Test_Operation_Begin ================================================
:test_func_0x0
::SETLOCAL
echo ==================== for_1 no_param ====================  
rem [1]无参：遍历当前路径的文件夹下的文件，但也可在(匹配符)中指定路径  查看 C:\Users\zhuxx 下 所有实体文件   
rem i == C:\Users\zhuzj5\.bash_history
rem i == C:\Users\zhuzj5\.gitconfig
rem i == C:\Users\zhuzj5\164.jpg
rem i == C:\Users\zhuzj5\Tip.txt
rem  进行运算后 自增一  必须使用 !! 括号括起来 

set /a Index_NoParam = 0
for  %%i in (%zbin%) do (
set /a Index_NoParam+=1
echo no_param_index[!Index_NoParam!] == %%i
)
echo ==================== for_2 /d ====================
rem d_index[1] == C:\Users\zhuzj5\.android
rem d_index[2] == C:\Users\zhuzj5\.config
rem d_index[3] == C:\Users\zhuzj5\.dotnet
rem d_index[4] == C:\Users\zhuzj5\.eclipse


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
rem r_index[15638] ======  C:\Users\zhuzj5\Videos\Captures\C:\Users\zhuzj5\Desktop\zbin\  (((for /r 遍历 出现两个地址)))
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
rem r_all_index[2838] =  C:\Users\zhuzj5\Desktop\zbin\J1_Plugin\video_cat_down\css\.

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

echo ___________________________________ string_test_func_0x0  
rem 测试字符串函数  
call :string_test_func_0x0

echo ___________________________________ searchLastFile_func_1x1
rem  searchLastFile_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin)))
rem  searchLastFile_return_1=(((K3_MD_Rule.class )))
rem  给一个目录进去  得到该目录下最新修改创建的文件名  不是全路径
set  searchLastFile_return_1=""
call :searchLastFile_func_1x1 %zbin%
echo searchLastFile_return_1=%searchLastFile_return_1%

echo ___________________________________ searchLastFullPath_func_1x1
rem  searchLastFile_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin)))
rem searchLastFullPath_return_1=(((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem  给一个目录进去  得到该目录下最新修改创建的文件全路径
set  searchLastFullPath_return_1=""
call :searchLastFullPath_func_1x1 %zbin%
echo searchLastFullPath_return_1=%searchLastFullPath_return_1% 

echo ___________________________________ showfile_func_1x0
rem  显示该文件的File 信息
call :showfile_func_1x0 %searchLastFullPath_return_1%


echo ___________________________________ getFileNameWithFullPath_func_1x1
rem getFileNameWithFullPath_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem getFileNameWithFullPath_return_1=(((K3_MD_Rule.class)))
rem  给一个文件的全路径返回该文件的 本身的文件名 去掉全路径
set  getFileNameWithFullPath_return_1=""
call :getFileNameWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileNameWithFullPath_return_1=%getFileNameWithFullPath_return_1%

echo ___________________________________ getFileTypeWithFullPath_func_1x1
rem getFileTypeWithFullPath_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem getFileTypeWithFullPath_return_1=(((.class)))
rem  给一个文件的全路径返回该文件的 本身的文件的类型
set  getFileTypeWithFullPath_return_1=""
call :getFileTypeWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileTypeWithFullPath_return_1=%getFileTypeWithFullPath_return_1%


echo ___________________________________ getParentFilePathWithFullPath_func_1x1
rem getParentFilePathWithFullPath_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem getParentFilePathWithFullPath_return_1=(((\Users\zhuzj5\Desktop\zbin\)))
rem  给一个文件的全路径返回该文件的 父文件夹的 全路径 
set  getParentFilePathWithFullPath_return_1=""
call :getParentFilePathWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getParentFilePathWithFullPath_return_1=%getParentFilePathWithFullPath_return_1%



echo ___________________________________ getFileNameNoPointWithFullPath_func_1x1
rem getFileNameNoPointWithFullPath_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem getFileNameNoPointWithFullPath_return_1=(((K3_MD_Rule)))
rem  给一个文件的全路径返回该文件的 去掉类型的 文件名字
set  getFileNameNoPointWithFullPath_return_1=""
call :getFileNameNoPointWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileNameNoPointWithFullPath_return_1=%getFileNameNoPointWithFullPath_return_1% 


echo ___________________________________ getFileNameNoPointWithFullPath_func_1x1
rem getFileSizeByteWithFullPath_func_1x1 input_1_param == (((C:\Users\zhuzj5\Desktop\zbin\K3_MD_Rule.class)))
rem getFileSizeByteWithFullPath_return_1=(((28529)))
rem  给一个文件的全路径返回该文件的  返回该文件的字节Byte大小
set  getFileSizeByteWithFullPath_return_1=""
call :getFileSizeByteWithFullPath_func_1x1 %searchLastFullPath_return_1%
echo getFileSizeByteWithFullPath_return_1=%getFileSizeByteWithFullPath_return_1% 

echo ___________________________________ showSubFile4Dir4Type_func_2x0
rem showSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showSubFile4Dir4Type_func_2x0 input_2_param == *.bat
rem showSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showSubFile4Dir4Type_func_2x0 input_2_param == *
rem 给一个要搜寻的路径 以及路径上过滤的字符串 找出 符合条件的 实体文件  不包含文件夹
call :showSubFile4Dir4Type_func_2x0 %zbin%  *.bat
call :showSubFile4Dir4Type_func_2x0 %zbin%  *

echo ___________________________________ showSubDir4Dir4Type_func_2x0
rem showSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showSubDir4Dir4Type_func_2x0 input_2_param == win
rem showSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showSubDir4Dir4Type_func_2x0 input_2_param == *
rem 搜索指定目录下的子目录  不包含文件  已经文件夹对应的名字符合条件的文件夹
call :showSubDir4Dir4Type_func_2x0 %zbin%  win
call :showSubDir4Dir4Type_func_2x0 %zbin%  *

echo ___________________________________ showSubFileAndDir_func_1x0
rem showSubFileAndDir_func_1x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem 遍历指定文件夹下的所有 子文件 和 子目录 
call :showSubFileAndDir_func_1x0 %zbin%

echo ___________________________________ showAllSubFile4Dir4Type_func_2x0
rem showAllSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showAllSubFile4Dir4Type_func_2x0 input_2_param == *.bat
rem showAllSubFile4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showAllSubFile4Dir4Type_func_2x0 input_2_param == *
rem 给一个要搜寻的路径 以及路径上过滤的字符串 找出 符合条件的 所有的孙文件 子文件 循环遍历 实体文件  
call :showAllSubFile4Dir4Type_func_2x0 %zbin%  win
call :showAllSubFile4Dir4Type_func_2x0 %zbin%  *


echo ___________________________________ showAllSubDir4Dir4Type_func_2x0
rem showAllSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showAllSubDir4Dir4Type_func_2x0 input_2_param == win
rem showAllSubDir4Dir4Type_func_2x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem showAllSubDir4Dir4Type_func_2x0 input_2_param == *
rem 搜索指定目录下的所有的子目录 孙目录  不包含文件   并且过滤文件名称
call :showAllSubDir4Dir4Type_func_2x0 %zbin%  win
call :showAllSubDir4Dir4Type_func_2x0 %zbin%  *



echo ___________________________________ showAllSubFileAndDir_func_1x0
rem showAllSubFileAndDir_func_1x0 input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem 遍历指定文件夹下的所有 子文件 和 子目录  孙文件 孙目录 循环 
call :showAllSubFileAndDir_func_1x0  %zbin%

echo ___________________________________ getTimeStamp_func_0x1
rem 返回yyyyMMdd_HHmmss 20210507_161904 这样的时间戳字符串
set getTimeStamp_return_1=""
call :getTimeStamp_func_0x1
echo getTimeStamp_return_1=%getTimeStamp_return_1%



echo ___________________________________ recordFileNameToFile_func_1x1
rem  recordFileNameToFile_func_1x1  input_1_param == C:\Users\zhuzj5\Desktop\zbin
rem  recordFileNameToFile_return_1=C:\Users\zhuzj5\Desktop\zbin\Z_temp.txt [  A1.txt   WIFI.txt ] 
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
rem "package:/data/app/~~iTJ7qQFCuiitsOv-NxYfsQ==/com.android.gpstest-8aetQzpfCBbjIcHfnhH_kA==/base.apk=com.android.gpstest"

call :getSubStringWithPreEndTag_func_3x1 %str1_temp%  "package:"    "apk="
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%

call :getSubStringWithPreEndTag_func_3x1 %str1_temp%  "package:"    "==/base"
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%

call :getSubStringWithPreEndTag_func_3x1 %str1_temp%     ""   "==/base"
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%

call :getSubStringWithPreEndTag_func_3x1 %str1_temp%       "==/base"   ""
echo getSubStringWithPreEndTag_return_1=%getSubStringWithPreEndTag_return_1%

rem  执行测试起始点   




::ENDLOCAL
goto:eof
rem ================================================  Test_Operation_End ================================================


rem ══════════════════════════════════════════ Func_Define_Aera_End ══════════════════════════════════════════

