@echo off
Setlocal ENABLEDELAYEDEXPANSION
rem @javac  -cp %userprofile%\Desktop\zbin\E9_hutool.jar -encoding UTF-8  %userprofile%\Desktop\zbin\E9_ScreenShot.java 
SET E9ClassFile=%userprofile%\Desktop\zbin\E9_ScreenShot.class
if not exist %E9ClassFile% (
@javac -Xlint:unchecked -encoding UTF-8  %userprofile%\Desktop\zbin\E9_ScreenShot.java 
)
@java -cp  %userprofile%\Desktop\zbin  E9_ScreenShot 
@exit
