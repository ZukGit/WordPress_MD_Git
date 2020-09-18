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
env > %userprofile%\Desktop\zbin\J1_env.txt
@javac  -cp %userprofile%\Desktop\zbin\J1_guava.jar; -Xlint:unchecked  -encoding UTF-8 %userprofile%\Desktop\zbin\J1_Add_Check_Path.java
@java -cp %userprofile%\Desktop\zbin\J1_guava.jar;%userprofile%\Desktop\zbin  J1_Add_Check_Path  %1  %2  %3 %4  %5  %6  %7  %8  %9 