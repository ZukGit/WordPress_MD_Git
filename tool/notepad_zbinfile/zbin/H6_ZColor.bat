@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac -cp %userprofile%\Desktop\zbin    -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\H6_ZColor.java
@java -cp %userprofile%\Desktop\zbin    H6_ZColor %1  %2  %3 %4  %5  %6  %7  %8  %9 
