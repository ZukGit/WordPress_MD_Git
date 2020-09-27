@echo off
@cd %1
@javac -encoding UTF-8 A8_GPS.java
@java A8_GPS %2
@pause