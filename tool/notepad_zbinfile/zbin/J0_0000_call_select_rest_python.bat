@echo off
Setlocal ENABLEDELAYEDEXPANSION


set CURPATH=%~dp0
echo CURPATH=%CURPATH%

set input_index_1=%1
set match_index_flag=false
ECHO input_index_1:%input_index_1%
FOR %%i IN (%CURPATH%*.py) DO (
set temp_content=%%i

ECHO  temp_content_fixed1 === !temp_content!
set "temp_content=!temp_content:%CURPATH%=!"
ECHO  temp_content_fixed2 === !temp_content!

set current_index=!temp_content:~3,4!



if "!match_index_flag!"=="false" (
    if "%input_index_1%"=="!current_index!" (
	    set  match_index_flag=true
		ECHO  === temp_content:!temp_content!      index:!current_index!   input_index_1:%input_index_1%   Flag: Success! ===
    ) else (
	    ECHO  === temp_content:!temp_content!      index:!current_index!   input_index_1:%input_index_1%   Flag: Failed! ===
	)	
)

rem echo match_index_flag = !match_index_flag! index:!current_index!
if "!match_index_flag!"=="true" (
ECHO  === temp_content:!temp_content!      index:!current_index!   input_index_1:%input_index_1%   Flag: Success and Run ! === Run_Run 
@py  "%%i"
)

)


ECHO "_____________ execute rest python code  finish! _____________"

