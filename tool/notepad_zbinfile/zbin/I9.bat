@echo off
Setlocal ENABLEDELAYEDEXPANSION
@rem echo 1=%1
@rem echo 2=%2
@rem echo 3=%3
@rem echo 4=%4
@rem echo 5=%5
@rem echo 6=%6
@rem echo 7=%7
@rem echo 8=%8
@rem echo 9=%9

@javac   -cp %userprofile%\Desktop\zbin\I9_pinyin4j.jar;%userprofile%\Desktop\zbin\I9_fastjson.jar;;%userprofile%\Desktop\zbin\I9_zxing.jar;%userprofile%\Desktop\zbin\I9_guava.jar;%userprofile%\Desktop\zbin\I9_hutool.jar -Xlint:unchecked  -encoding UTF-8 %userprofile%\Desktop\zbin\I9_TextRuleOperation.java
@java -cp .;%userprofile%\Desktop\zbin\I9_pinyin4j.jar;%userprofile%\Desktop\zbin\I9_fastjson.jar;;%userprofile%\Desktop\zbin\I9_zxing.jar;%userprofile%\Desktop\zbin\I9_guava.jar;%userprofile%\Desktop\zbin\I9_hutool.jar;%userprofile%\Desktop\zbin -Xmx10240m -Xms10240m -Xmn5120m    I9_TextRuleOperation  %1  %2  %3 %4  %5  %6  %7  %8  %9 