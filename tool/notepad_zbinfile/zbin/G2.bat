@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\G2_ApplyRuleFor_TypeFile.java
@java -cp  %userprofile%\Desktop\zbin    G2_ApplyRuleFor_TypeFile %1  %2  %3 %4  %5  %6  %7  %8  %9 