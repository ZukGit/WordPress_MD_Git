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

@javac  -cp %userprofile%\Desktop\zbin\J3_fastjson.jar  -Xlint:unchecked  -encoding UTF-8 %userprofile%\Desktop\zbin\J3_JsonServer_NPM.java
@java -cp %userprofile%\Desktop\zbin;%userprofile%\Desktop\zbin\J3_fastjson.jar    J3_JsonServer_NPM %1  %2  %3 %4  %5  %6  %7  %8  %9 