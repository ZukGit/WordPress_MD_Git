@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F5_CreateSqliteTableTool.java
@java -cp  %userprofile%\Desktop\zbin    F5_CreateSqliteTableTool %1  %2  %3 %4  %5  %6  %7  %8  %9 