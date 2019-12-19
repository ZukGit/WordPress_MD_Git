@echo off
Setlocal enabledelayedexpansion

cd !cd!

ECHO init-ing

:LOOP

ECHO waiting conenct with USB


adb wait-for-device

md  %userprofile%\Desktop\zbin\F8\
del /f/s/q %userprofile%\Desktop\zbin\F8\wifi.txt
adb shell getprop > %userprofile%\Desktop\zbin\F8\getprop.txt
adb shell dumpsys wifi > %userprofile%\Desktop\zbin\F8\wifi.txt
adb shell dumpsys wifiscanner > %userprofile%\Desktop\zbin\F8\wifiscanner.txt


@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F8_Dump_Wifi.java
@java -cp  %userprofile%\Desktop\zbin    F8_Dump_Wifi %1  %2  %3 %4  %5  %6  %7  %8  %9 

Pause
