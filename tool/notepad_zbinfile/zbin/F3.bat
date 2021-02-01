@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac -cp %userprofile%\Desktop\zbin\F3_hutool.jar;%userprofile%\Desktop\zbin\F3_zxing.jar;%userprofile%\Desktop\zbin\F3_BT.jar  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F3_BT.java
@java -cp  .;%userprofile%\Desktop\zbin\F3_hutool.jar;%userprofile%\Desktop\zbin\F3_zxing.jar;%userprofile%\Desktop\zbin\F3_BT.jar;%userprofile%\Desktop\zbin    F3_BT %1  %2  %3 %4  %5  %6  %7  %8  %9 