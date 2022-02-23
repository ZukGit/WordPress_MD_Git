@echo off
rem   zjira.bat  
Setlocal ENABLEDELAYEDEXPANSION
set dp0=%~dp0
set input_1=%1
set pathA=%cd%
echo  [curPath=%pathA%]   [input_1=%input_1%]    [dp0=%dp0%]
set jira_log_path=

IF "%input_1%"=="" ( 
echo "input is null , please try again!"
) ELSE (
echo "input is [%input_1%]"
)
set itemPath=D:\jira_work\%input_1%\
set itemPath_txt="D:\jira_work\%input_1%\fenxi.asm"
echo itemPath=%itemPath
echo itemPath_same=%itemPath%
md %itemPath%
echo create txt_file  %itemPath_txt% 
echo  ''> %itemPath_txt%
notepad++.exe   %itemPath_txt%
echo open %itemPath_txt%
echo ######################################

set downloadPath=D:\jira_download\

echo downloadPath_1=%downloadPath%
echo downloadPath_2=%downloadPath%
rem dir /o-d /a /b  | head -1 
echo ________begin______searchLastFile____
call :searchLastFile
echo jira_log_path_2=%jira_log_path%
cd  /d  %jira_log_path%
echo =============== pwd begin ===============
pwd
echo =============== pwd end ===============
echo ===============  zwisl_log_search_J9 ===============
echo  %userprofile%\Desktop\zbin\win_zbin\zwisl_log_search_J9.bat  %jira_log_path%

@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F8_Dump_Analysis.java
 
call %userprofile%\Desktop\zbin\win_zbin\zwisl_log_search_J9.bat  %jira_log_path% &&  @java -cp  %userprofile%\Desktop\zbin   F8_Dump_Analysis %jira_log_path%  %1  %2  %3 %4  %5  %6  %7  %8  > %jira_log_path%\Z_dump_all_analysis.txt && echo ===============  notepad++ search tip =============== && %userprofile%\Desktop\zbin\K2.bat 


goto:eof




:searchLastFile
set for_index=1
for /f "delims=" %%j in ('dir %downloadPath% /o-d /a /b') do (
rem num=1 123.txt 
echo num=!for_index! %%j 
set /a for_index  = !for_index! + 1
set short_path=%%j
call :tryZipLastFile %%j

goto:eof
)
goto:eof


:tryZipLastFile
set str_dp0=%~dp0
set str_dp1=%~dp1
set str_dp2=%~dp2
set str_in_1=%1
set jira_last_fullpath_file=%downloadPath%%str_in_1%
echo  str_dp0=%str_dp0%   str_dp1=%str_dp1%  str_dp2=%str_dp2%   str_in_1=%str_in_1%  
echo jira_last_fullpath_file=%jira_last_fullpath_file%
rem  C:\Users\zhuzj5\Desktop\zbin\H5_7z.exe  -y -p""  x "C:\Users\zhuzj5\Desktop\0323\0324.zip"  -o"./0324_zip"
echo %userprofile%\Desktop\zbin\H5_7z.exe  -y -p""  x "%jira_last_fullpath_file%" -o"%itemPath%%str_in_1%"
 
%userprofile%\Desktop\zbin\H5_7z.exe  -y -p""  x "%jira_last_fullpath_file%" -o"%itemPath%%str_in_1%"
echo cd-path ---> %itemPath%%str_in_1%

set  jira_log_path=%itemPath%%str_in_1%
echo jira_log_path_1=%jira_log_path%


rem  __________try zip bugreport.zip begin__________
echo __________try zip bugreport.zip begin__________

echo %userprofile%\Desktop\zbin\H5_7z.exe  -y -p""  x "%itemPath%%str_in_1%\bugreport.zip" -o"%itemPath%%str_in_1%"
 
%userprofile%\Desktop\zbin\H5_7z.exe  -y -p""  x "%itemPath%%str_in_1%\bugreport.zip" -o"%itemPath%%str_in_1%"
echo __________try zip bugreport.zip end__________
rem  __________try zip bugreport.zip end__________ 

goto:eof 
