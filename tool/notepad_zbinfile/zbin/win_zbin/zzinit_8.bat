@ECHO off
setlocal enabledelayedexpansion 
set cur_path=%cd%
set cur_Desktop=%USERPROFILE%
set cur_jdk_path=%JAVA_HOME%
set notepad_desc="       cmd /K cd /d %%userprofile%%\Desktop\zbin & %%userprofile%%\Desktop\zbin\I9.bat %%userprofile%%\Desktop\zbin  $(FULL_CURRENT_PATH)                                      			  "
echo=
echo ___________Notepad++ TextRule Command___________
echo Name: text_rule   Key:  F8
echo  %notepad_desc%
 
echo=
echo ___________CMDER REGISTER___________
echo Cmder.exe /REGISTER ALL
echo=
echo=
echo ___________CMDER1___________
echo=
echo set PATH=%%USERPROFILE%%\Desktop\zbin\win_zbin;%%PATH%%
echo alias gits=git status
echo alias cdd=cd /D %%USERPROFILE%%\Desktop
echo alias cdz=cd /D %%USERPROFILE%%\Desktop\zbin

echo=
echo=
set cur_Path=%PATH%
set remain=%cur_Path%
set cur_path_index=1
set cur_jdk_path=
set cur_ffmpeg_path=
set cur_Tesseract_path=

echo ___________Print And Search Environment_Path___________
:loop
for /f "tokens=1* delims=;" %%a in ("%remain%") do (
	echo [%cur_path_index%]    %%a
	
	
    set JDK_EXISTS_FLAG=false
	echo %%a |find "jdk">nul&&set JDK_EXISTS_FLAG=true
	set  temp_path_jdk="%%a"
	set  temp_path_ffmpeg="%%a"
	set  temp_path_Tesseract="%%a"
			
    if "%JDK_EXISTS_FLAG%"=="true" (
        set  cur_jdk_path=%temp_path_jdk%
		rem echo [JDK_EXISTS_FLAG]=%JDK_EXISTS_FLAG%
		rem echo [cur_jdk_path]=%cur_jdk_path%
    )
	
	set ffmpeg_EXISTS_FLAG=false
	echo %%a |find "ffmpeg">nul&&set ffmpeg_EXISTS_FLAG=true
    set  temp_path_ffmpeg="%%a"
    if "%ffmpeg_EXISTS_FLAG%"=="true" (
        set  cur_ffmpeg_path=%temp_path_ffmpeg%
    )
	
	set Tesseract_EXISTS_FLAG=false
	echo %%a |find "Tesseract">nul&&set Tesseract_EXISTS_FLAG=true
    set  temp_path_Tesseract="%%a"
    if "%Tesseract_EXISTS_FLAG%"=="true" (
        set  cur_Tesseract_path=%temp_path_Tesseract%
    )
	
	
	set remain=%%b
	set /a cur_path_index  = !cur_path_index! + 1 
)
::
if defined remain goto :loop


echo [JAVA_HOME] ==== %cur_jdk_path%
echo [cur_jdk_path]=%cur_jdk_path:~1,-1%
echo [curPath]   ==== %cur_path%
echo [win_zbin] ==== %cur_Desktop%\zbin\win_zbin
echo=
echo ___________CMDER2___________
echo set PATH=%%USERPROFILE%%\Desktop\zbin\win_zbin;%%PATH%%
echo alias gits=git status
echo alias cdd=cd /D %%USERPROFILE%%\Desktop
echo alias cdz=cd /D %USERPROFILE%\Desktop\zbin
echo set PATH=%cur_jdk_path:~1,-1%;%%PATH%%
echo set PATH=%cur_ffmpeg_path:~1,-1%;%%PATH%%
echo set PATH=%cur_Tesseract_path:~1,-1%;%%PATH%%
echo=
echo=
echo ___________SHOW PATH___________
echo %cur_jdk_path:~1,-1%
echo %cur_ffmpeg_path:~1,-1%
echo %cur_Tesseract_path:~1,-1%
echo=

echo ___________SETX WIN_ZBIN_PATH___________
echo SETX PATH %USERPROFILE%\Desktop\zbin\win_zbin;%PATH%
echo=

echo ___________SETX JDK_PATH___________
echo SETX PATH %cur_jdk_path%;%PATH%
echo=

echo ___________SETX FFMPEG_PATH___________
echo SETX PATH %cur_ffmpeg_path%;%PATH%
echo=

echo ___________SETX Tesseract_PATH___________
echo SETX PATH %cur_Tesseract_path%;%PATH%
echo 

