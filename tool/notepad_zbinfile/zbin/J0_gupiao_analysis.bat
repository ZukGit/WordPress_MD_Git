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

@javac  -Xlint:unchecked    -cp %userprofile%\Desktop\zbin\J0_guava.jar;%userprofile%\Desktop\zbin\J0_fastjson.jar;%userprofile%\Desktop\zbin\J0_pinyin4j.jar;%userprofile%\Desktop\zbin\J0_dom4j-1.6.1.jar;%userprofile%\Desktop\zbin\J0_ojdbc6-11.jar;%userprofile%\Desktop\zbin\J0_poi-3.9.jar;%userprofile%\Desktop\zbin\J0_poi-ooxml-3.9.jar;%userprofile%\Desktop\zbin\J0_poi-ooxml-schemas-3.9.jar;%userprofile%\Desktop\zbin\J0_xercesImpl.jar;%userprofile%\Desktop\zbin\J0_xmlbeans-2.3.0.jar -encoding UTF-8 %userprofile%\Desktop\zbin\J0_GuPiao_Analysis.java
@java    -Dfile.encoding=UTF-8 -Xmx10240m -Xms10240m -Xmn5120m   -cp   .;%userprofile%\Desktop\zbin\J0_guava.jar;%userprofile%\Desktop\zbin\J0_fastjson.jar;%userprofile%\Desktop\zbin\J0_pinyin4j.jar;%userprofile%\Desktop\zbin\J0_dom4j-1.6.1.jar;%userprofile%\Desktop\zbin\J0_ojdbc6-11.jar;%userprofile%\Desktop\zbin\J0_poi-3.9.jar;%userprofile%\Desktop\zbin\J0_poi-ooxml-3.9.jar;%userprofile%\Desktop\zbin\J0_poi-ooxml-schemas-3.9.jar;%userprofile%\Desktop\zbin\J0_xercesImpl.jar;%userprofile%\Desktop\zbin\J0_xmlbeans-2.3.0.jar;%userprofile%\Desktop\zbin\ J0_GuPiao_Analysis  %1  %2  %3 %4  %5  %6  %7  %8  %9 