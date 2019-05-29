@echo off
Setlocal ENABLEDELAYEDEXPANSION
cd !cd!
cd /d %~dp0
netsh wlan show profiles | find " :" > C1_Wifi_Info.txt

for /f "delims=" %%l in (C1_Wifi_Info.txt) do (
set mystr=%%l
rem @echo mystr=!mystr!
set str=!mystr!
set prestr=!str:~15! 
echo prestr=!prestr!

netsh wlan show profiles !prestr! key=clear >> C1_Wifi_Info_Result.txt
)

pause