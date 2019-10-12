@echo off
Setlocal ENABLEDELAYEDEXPANSION

@rem %userprofile%\Desktop\zbin\E2_WifiDetail.bat 
call %userprofile%\Desktop\zbin\E2_WifiDetail.bat 
start  msinfo32 
start  dxdiag  
cd %userprofile%\Desktop\zbin\
@javac -cp %userprofile%\Desktop\zbin\E2_sigar.jar -encoding UTF-8 %userprofile%\Desktop\zbin\E2_SystemInfo.java
@java  -cp  .;%userprofile%\Desktop\zbin\E2_sigar.jar;%userprofile%\Desktop\zbin\   E2_SystemInfo

