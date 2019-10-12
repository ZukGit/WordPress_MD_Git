@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac     -cp %userprofile%\Desktop\zbin\F2_jsoup.jar;%userprofile%\Desktop\zbin\F2_javaparser.jar -Xlint:unchecked   -encoding UTF-8 %userprofile%\Desktop\zbin\F2_DownFile_AOSP.java
@java -cp  %userprofile%\Desktop\zbin;%userprofile%\Desktop\zbin\F2_jsoup.jar;%userprofile%\Desktop\zbin\F2_javaparser.jar    F2_DownFile_AOSP %1  %2  %3 %4  %5  %6  %7  %8  %9 