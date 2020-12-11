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

@javac   -cp %userprofile%\Desktop\zbin\J8_opencv-320.jar;%userprofile%\Desktop\zbin\J8_javacv.jar;%userprofile%\Desktop\zbin\J8_javacpp.jar;%userprofile%\Desktop\zbin\J8_opencv-2.4.8-windows-x86_64.jar;%userprofile%\Desktop\zbin\J8_ezmorph-1.0.6.jar;%userprofile%\Desktop\zbin\J8_javacv-windows-x86_64.jar -encoding UTF-8 %userprofile%\Desktop\zbin\J8_TakePic_ORC.java
@java  -cp  .;%userprofile%\Desktop\zbin\J8_opencv-320.jar;%userprofile%\Desktop\zbin\J8_javacv.jar;%userprofile%\Desktop\zbin\J8_javacpp.jar;%userprofile%\Desktop\zbin\J8_opencv-2.4.8-windows-x86_64.jar;%userprofile%\Desktop\zbin\J8_ezmorph-1.0.6.jar;%userprofile%\Desktop\zbin\J8_javacv-windows-x86_64.jar;%userprofile%\Desktop\zbin\ J8_TakePic_ORC  %1  %2  %3 %4  %5  %6  %7  %8  %9 