@echo off
@cd %1
@javac -encoding UTF-8 A8_WIFI_Log_Search.java
@java A8_WIFI_Log_Search %2


@javac -encoding UTF-8 A8_GPS_Log_Search.java
@java A8_GPS_Log_Search %2

@javac  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\F8_Dump_Analysis.java
@java -cp  %userprofile%\Desktop\zbin    F8_Dump_Analysis %2

@pause