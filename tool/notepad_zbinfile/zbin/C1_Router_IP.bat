@echo off
Setlocal ENABLEDELAYEDEXPANSION
set b=%cd%
cd /d %~dp0
route print
ipconfig -all
route print  >> ./C1_Router_IP_Info.txt
ipconfig -all  >> ./C1_Router_IP_Info.txt
pause