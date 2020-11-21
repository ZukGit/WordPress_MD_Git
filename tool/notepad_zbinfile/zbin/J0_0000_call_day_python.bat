@echo off
Setlocal ENABLEDELAYEDEXPANSION
set CURPATH=%~dp0
echo CURPATH=%CURPATH%

set input_index_1=%1
ECHO input_index_1:%input_index_1%

FOR %%i IN (%CURPATH%*.py) DO (
set temp_content=%%i
ECHO  curFile === %%i
ECHO  temp_content_fixed1 === !temp_content!
set "temp_content=!temp_content:%CURPATH%=!"
ECHO  temp_content_fixed2 === !temp_content!
set current_index=!temp_content:~4,8!

ECHO  === input_index_1:!input_index_1!      index:!current_index!   input_index_1:%input_index_1% 

if "%input_index_1%"=="!current_index!" (
	ECHO  === temp_content:!temp_content!      index:!current_index!   input_index_1:%input_index_1%   Flag: Success and Run ! ===
	@py  "%%i"
	goto end
)

)

:end
ECHO "_____________ execute single python code  finish! _____________"

