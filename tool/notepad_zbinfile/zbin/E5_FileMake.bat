@echo off
Setlocal ENABLEDELAYEDEXPANSION
@echo 1=%1
@echo 2=%2
@echo 3=%3 
@javac -encoding UTF-8 %userprofile%\Desktop\zbin\E5_FileMake.java
@java -cp  %userprofile%\Desktop\zbin    E5_FileMake  %2  %3