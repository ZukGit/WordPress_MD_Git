@echo off
Setlocal ENABLEDELAYEDEXPANSION
cd !cd!
cd /d %~dp0
echo "null" > %userprofile%\Desktop\zbin\E2_WifiDetail.txt
netsh wlan show profiles | findstr " :" > %userprofile%\Desktop\zbin\E2_Wifi_Info.txt
del /f /q E2_WifiDetail.txt
for /f "delims=" %%l in (%userprofile%\Desktop\zbin\E2_Wifi_Info.txt) do (
set mystr=%%l
rem @echo mystr=!mystr!
set str=!mystr!
set prestr=!str:~15!
rem @echo prestr1=!prestr!
set prestr="!prestr!"
rem @echo prestr2=!prestr!
rem @set name=!prestr:~0,-2!"
rem @echo name=!name!
rem @echo  'netsh wlan show profiles !prestr! key=clear  >> E2_WifiDetail.txt '
netsh wlan show profiles !prestr! key=clear >> %userprofile%\Desktop\zbin\E2_WifiDetail.txt
)
dos2unix %userprofile%\Desktop\zbin\E2_WifiDetail.txt
exit