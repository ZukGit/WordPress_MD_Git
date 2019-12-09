@echo off
Setlocal enabledelayedexpansion

cd !cd!

ECHO init-ing

:LOOP

ECHO waiting conenct with USB
adb wait-for-device

del /f/s/q %userprofile%\Desktop\zbin\F8\F8_AllService.txt
del /f/s/q %userprofile%\Desktop\zbin\F8\
md  %userprofile%\Desktop\zbin\F8\
adb shell service list  > %userprofile%\Desktop\zbin\F8\F8_AllService.txt
adb shell getprop > %userprofile%\Desktop\zbin\F8\getprop.txt
if exist %userprofile%\Desktop\zbin\F8\F8_AllService.txt (
  for /f "tokens=1,2" %%i in (%userprofile%\Desktop\zbin\F8\F8_AllService.txt) do (
       set /a num+=1 
	   set count=%%i
       set service=%%j
	   set realservice=!service:~0,-1!
	   echo count=!count! realservice=!realservice!
       adb shell dumpsys !realservice! > %userprofile%\Desktop\zbin\F8\!realservice!.txt
    
rem   call adb uninstall !str1!
  )
ECHO all dumpsys service loaded at  %userprofile%\Desktop\zbin\F8\ !
) else (
ECHO F8_AllService.txt file not exist !
)

@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F8_Dump_All.java
@java -cp  %userprofile%\Desktop\zbin    F8_Dump_All %1  %2  %3 %4  %5  %6  %7  %8  %9 

Pause
