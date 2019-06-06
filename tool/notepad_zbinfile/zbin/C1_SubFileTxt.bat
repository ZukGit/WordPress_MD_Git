@echo off
Setlocal ENABLEDELAYEDEXPANSION
cd !cd!
cd /d %~dp0

dir /s /b > C1_SubFileTxt.txt
exit