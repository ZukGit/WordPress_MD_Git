@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac -cp %userprofile%\Desktop\zbin\I9_fastjson.jar;%userprofile%\Desktop\zbin\I9_guava.jar;%userprofile%\Desktop\zbin\I9_hutool.jar;%userprofile%\Desktop\zbin\I9_jsoup.jar;%userprofile%\Desktop\zbin\I9_pinyin4j.jar;%userprofile%\Desktop\zbin\I9_zxing.jar;%userprofile%\Desktop\zbin -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\I9_TextRuleOperation.java
@java -cp %userprofile%\Desktop\zbin\I9_fastjson.jar;%userprofile%\Desktop\zbin\I9_guava.jar;%userprofile%\Desktop\zbin\I9_hutool.jar;%userprofile%\Desktop\zbin\I9_jsoup.jar;%userprofile%\Desktop\zbin\I9_pinyin4j.jar;%userprofile%\Desktop\zbin\I9_zxing.jar;%userprofile%\Desktop\zbin    I9_TextRuleOperation  %1  %2  %3 %4  %5  %6  %7  %8  %9 
