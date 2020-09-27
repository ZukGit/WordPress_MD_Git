@echo off
@cd %1
@javac -encoding UTF-8 A8_WIFI_Log_Search.java
@java A8_WIFI_Log_Search %2

@pause