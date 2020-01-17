@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\G3_RedBlackTree.java  %userprofile%\Desktop\zbin\G3_TreeJpanel.java  %userprofile%\Desktop\zbin\G3_StartFrame.java
@java -cp  %userprofile%\Desktop   zbin.G3_StartFrame %1  %2  %3 %4  %5  %6  %7  %8  %9 