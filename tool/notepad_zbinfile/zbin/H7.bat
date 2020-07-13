@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac   -cp %userprofile%\Desktop\zbin\H7_hutool.jar;%userprofile%\Desktop\zbin\H7_javax.mail.jar  -encoding UTF-8 %userprofile%\Desktop\zbin\H7_EmailMonitor.java
@java -cp .;%userprofile%\Desktop\zbin\H7_hutool.jar;%userprofile%\Desktop\zbin\H7_javax.mail.jar;%userprofile%\Desktop\zbin\ H7_EmailMonitor  %1  %2  %3 %4  %5  %6  %7  %8  %9 