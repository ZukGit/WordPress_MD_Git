@echo off
Setlocal ENABLEDELAYEDEXPANSION
@echo 1=%1
@echo 2=%2
@echo 3=%3 
@javac -encoding UTF-8 %userprofile%\Desktop\zbin\D5_StrReplace.java
@java -cp  %userprofile%\Desktop\zbin    D5_StrReplace  %2  %3