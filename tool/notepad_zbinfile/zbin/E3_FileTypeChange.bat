@echo off
Setlocal ENABLEDELAYEDEXPANSION
@echo 1=%1
@echo 2=%2
@echo 3=%3 
@javac -encoding UTF-8 %userprofile%\Desktop\zbin\E3_FileTypeChange.java
@java -cp  %userprofile%\Desktop\zbin    E3_FileTypeChange  %2  %3