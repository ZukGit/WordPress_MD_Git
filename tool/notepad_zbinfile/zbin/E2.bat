@echo off
Setlocal ENABLEDELAYEDEXPANSION
cd %userprofile%\Desktop\zbin\
@javac -cp %userprofile%\Desktop\zbin\E2_sigar.jar -encoding UTF-8 %userprofile%\Desktop\zbin\E2_SystemInfo.java
@java  -cp  .;%userprofile%\Desktop\zbin\E2_sigar.jar;%userprofile%\Desktop\zbin\   E2_SystemInfo
