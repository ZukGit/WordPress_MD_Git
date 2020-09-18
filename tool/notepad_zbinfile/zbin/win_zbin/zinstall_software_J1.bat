@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set b=%~dp0
set pathA=%cd%
echo ~~~~~~~~~~~~~~~~~~~~~~~Try Zip Check~~~~~~~~~~~~~~~~~~~~~~~
call %userprofile%\Desktop\zbin\H5.bat   %pathA%  J1_all
echo  %userprofile%\Desktop\zbin\J1.bat  %pathA%
echo  %pathA%\J1_InstallSoft.bat
echo ~~~~~~~~~~~~~~~~~~~~~~~A begin_zsoftware_install~~~~~~~~~~~~~~~~~~~~~~~
call  %userprofile%\Desktop\zbin\J1.bat  %pathA%  %1  %2  %3 %4  %5  %6  %7  %8  %9 
echo ~~~~~~~~~~~~~~~~~~~~~~~B begin_zsoftware_install~~~~~~~~~~~~~~~~~~~~~~~
echo  %pathA%\J1_InstallSoft.bat
call %pathA%\J1_InstallSoft.bat
echo ~~~~~~~~~~~~~~~~~~~~~~~C Add_PATH_Operation~~~~~~~~~~~~~~~~~~~~~~~
call %userprofile%\Desktop\zbin\J1_Path.bat  %pathA%  %1  %2  %3 %4  %5  %6  %7  %8  %9