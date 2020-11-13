@echo off
Setlocal ENABLEDELAYEDEXPANSION

set CURPATH=%~dp0
echo CURPATH=%CURPATH%

FOR %%i IN (%CURPATH%*.py) DO ( 
ECHO python-execute: %%i
@py  "%%i"
)
pwd


ECHO "_____________ execute all python code  finish! _____________"