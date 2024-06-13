@echo off
:: 关闭命令回显
if "%1"=="" (
    echo No file specified.
    pause
    exit /b
)
 
:: 获取传递的文件路径
set "filePath=%1"
:: 获取文件名（不含路径）
set "fileName=%~nx1"
:: 获取文件的目录路径
set "fileDir=%~dp1"
 
:: 输出将要添加到注册表的信息
echo Adding fileName[%fileName%]  param1:[%%1] fileDir[%fileDir%] file information to the registry: filePath[%filePath%]


del zcmd_admin_I9.bat
echo @ECHO off >> zcmd_admin_I9.bat  
echo setlocal enabledelayedexpansion  >> zcmd_admin_I9.bat  
rem set local_str=^%1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c ^%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit
echo ^%%^1 mshta vbscript^:CreateObject^(^"Shell^.Application^"^)^.ShellExecute^(^"cmd^.exe^",^"/c %%^~s0 ::^",^"^",^"runas^",1^)^(window^.close^)^&^&exit  >> zcmd_admin_I9.bat 
echo REG ADD "HKCR\*\shell\%fileName%" /ve /d "%fileName%" /f >> zcmd_admin_I9.bat  
echo REG ADD "HKCR\*\shell\%fileName%" /v "icon" /t REG_SZ /d  "%filePath%" /f >> zcmd_admin_I9.bat  
echo REG ADD "HKCR\*\shell\%fileName%\command" /ve /d  "\"%filePath%\" \"^%%^%%^1\"" /f >> zcmd_admin_I9.bat  
start zcmd_admin_I9.bat  

:: 添加文件路径到注册表
echo ____Command1_____: REG ADD "HKCR\*\shell\%fileName%" /ve /d "%fileName%" /f
rem REG ADD "HKCR\*\shell\%fileName%" /ve /d "%fileName%" /f

echo ____Command2_____: REG ADD "HKCR\*\shell\%fileName%" /v "icon" /t REG_SZ /d  "%filePath%" /f
rem REG ADD "HKCR\*\shell\%fileName%" /v "icon" /t REG_SZ /d  "%filePath%" /f

echo ____Command3_____: REG ADD "HKCR\*\shell\%fileName%\command" /ve /d  "\"%filePath%\" \"^%%^%%^1\"" /f
rem REG ADD "HKCR\*\shell\%fileName%\command" /ve /d  "\"%filePath%\" \"%%1\"" /f

echo File information added successfully
:: 输出提示信息，告知用户文件信息已成功添加到注册表
delete zcmd_admin_I9.bat 
exit