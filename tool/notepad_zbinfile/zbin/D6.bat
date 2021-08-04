@echo off
Setlocal ENABLEDELAYEDEXPANSION
@javac -cp %userprofile%\Desktop\zbin\D6_metadata-extractor-2.11.0.jar;%userprofile%\Desktop\zbin\D6_mp3agic-0.9.1.jar;%userprofile%\Desktop\zbin\D6_xmpcore-5.1.2.jar;%userprofile%\Desktop\zbin  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\D6_FileNameSearch.java
@java -cp %userprofile%\Desktop\zbin\D6_metadata-extractor-2.11.0.jar;%userprofile%\Desktop\zbin\D6_mp3agic-0.9.1.jar;%userprofile%\Desktop\zbin\D6_xmpcore-5.1.2.jar;%userprofile%\Desktop\zbin  D6_FileNameSearch  %1  %2  %3 %4  %5  %6  %7  %8  %9 
