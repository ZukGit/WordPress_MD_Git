@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac     -cp %userprofile%\Desktop\zbin\F5_fastjson.jar;%userprofile%\Desktop\zbin\F5_sqlite_jdbc.jar  -Xlint:unchecked   -encoding UTF-8 %userprofile%\Desktop\zbin\F5_Sqlite.java
@java -cp  %userprofile%\Desktop\zbin;%userprofile%\Desktop\zbin\F5_fastjson.jar;%userprofile%\Desktop\zbin\F5_sqlite_jdbc.jar    F5_Sqlite  %1  %2  %3 %4  %5  %6  %7  %8  %9 