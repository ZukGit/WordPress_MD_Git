@echo off
setlocal EnableDelayedExpansion
rem echo  !cd!
rem cd !cd!
@javac  -cp %userprofile%\Desktop\zbin\B8_javaparser.jar -encoding UTF-8  %userprofile%\Desktop\zbin\B8_LogAdd.java 
@java -cp .;%userprofile%\Desktop\zbin;%userprofile%\Desktop\zbin\B8_javaparser.jar B8_LogAdd %1  %2  %3 %4  %5  %6  %7  %8  %9 
