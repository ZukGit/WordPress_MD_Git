@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac -cp %userprofile%\Desktop\zbin\G2_webp-imageio.jar;%userprofile%\Desktop\zbin\G2_sunjce_provider.jar;%userprofile%\Desktop\zbin\G2_hutool.jar;%userprofile%\Desktop\zbin\G2_jshortcut_oberzalek.jar -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\G2_ApplyRuleFor_TypeFile.java
@java -cp  %userprofile%\Desktop\zbin\G2_webp-imageio.jar;%userprofile%\Desktop\zbin\G2_sunjce_provider.jar;%userprofile%\Desktop\zbin\G2_hutool.jar;%userprofile%\Desktop\zbin\G2_jshortcut_oberzalek.jar;%userprofile%\Desktop\zbin    G2_ApplyRuleFor_TypeFile %1  %2  %3 %4  %5  %6  %7  %8  %9 
