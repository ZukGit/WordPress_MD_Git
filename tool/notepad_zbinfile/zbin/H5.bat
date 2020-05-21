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

@javac   -cp %userprofile%\Desktop\zbin\H5_zip4j_1.3.2.jar;%userprofile%\Desktop\zbin\H5_commons-compress-1.20.jar;%userprofile%\Desktop\zbin\H5_javatar-2.5.jar;%userprofile%\Desktop\zbin\H5_junrar-0.7.jar;%userprofile%\Desktop\zbin\H5_sevenzipjbinding-9.20.jar;%userprofile%\Desktop\zbin\H5_java-unrar-20120702.jar;%userprofile%\Desktop\zbin\H5_sevenzipjbinding-all-platforms-16.02.jar;%userprofile%\Desktop\zbin\H5_xz-1.8.jar  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\H5_Zip.java
@java  -cp  .;%userprofile%\Desktop\zbin\H5_zip4j_1.3.2.jar;%userprofile%\Desktop\zbin\H5_commons-compress-1.20.jar;%userprofile%\Desktop\zbin\H5_javatar-2.5.jar;%userprofile%\Desktop\zbin\H5_junrar-0.7.jar;%userprofile%\Desktop\zbin\H5_sevenzipjbinding-9.20.jar;%userprofile%\Desktop\zbin\H5_java-unrar-20120702.jar;%userprofile%\Desktop\zbin\H5_sevenzipjbinding-all-platforms-16.02.jar;%userprofile%\Desktop\zbin\H5_xz-1.8.jar;%userprofile%\Desktop\zbin\ H5_Zip  %1  %2  %3 %4  %5  %6  %7  %8  %9 