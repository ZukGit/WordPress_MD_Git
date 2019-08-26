@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\D7_FileCopy.java
@java -cp  %userprofile%\Desktop\zbin    D7_FileCopy %1  %2  %3 %4  %5  %6  %7  %8  %9 