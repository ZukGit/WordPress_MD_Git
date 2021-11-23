@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\H0_Tip.java
@java  -Dfile.encoding=UTF-8  -cp  %userprofile%\Desktop\zbin    H0_Tip %1  %2  %3 %4  %5  %6  %7  %8  %9 