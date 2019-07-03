@echo off
Setlocal ENABLEDELAYEDEXPANSION

set b=%~dp0
echo %b%
cd /d %userprofile%\Desktop\zbin
call D0_mp4.bat %userprofile%\Desktop\zbin  %b%

echo end!
pause