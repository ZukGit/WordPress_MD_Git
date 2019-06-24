@ECHO off

Setlocal enabledelayedexpansion
echo !cd!
cd !cd!
@javac -encoding UTF-8  C5_Qcom_WCNSS_Analysis.java 
@java C5_Qcom_WCNSS_Analysis 
@pause