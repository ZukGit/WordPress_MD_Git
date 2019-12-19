@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\G1_CarGame.java
@java -cp  %userprofile%\Desktop\zbin    G1_CarGame %1  %2  %3 %4  %5  %6  %7  %8  %9 