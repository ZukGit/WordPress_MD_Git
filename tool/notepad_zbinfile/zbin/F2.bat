@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F2_DownFile_AOSP.java
@java -cp  %userprofile%\Desktop\zbin    F2_DownFile_AOSP %1  %2  %3 %4  %5  %6  %7  %8  %9 