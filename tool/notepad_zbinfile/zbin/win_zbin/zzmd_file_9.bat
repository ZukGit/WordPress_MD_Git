@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
set input_path=%1
set cur_index=1
echo=
echo=
echo input_param_0=%0
echo input_param_1=%1
echo input_param_2=%2
echo input_param_3=%3
echo input_param_4=%4
echo input_param_5=%5
echo input_param_6=%6
echo input_param_7=%7
echo input_param_8=%8
echo input_param_9=%9

echo=
echo=

if "%1"=="" (

rem  echo input_param_1=%1

for  %%x in (*) do (
echo [!cur_index!] [ %%x ]  MD-info as below 
 certutil -hashfile   %%x MD5
set /a cur_index  = !cur_index! + 1 
echo=
)

) else (
echo input 1th file %1  MD-info as below 
certutil -hashfile %1 MD5
echo=
)


if "%2"=="" (

rem  echo input_param_2=%2

) else (
echo input 2th file %2  MD-info as below 
certutil -hashfile %2 MD5
echo=
)


if "%3"=="" (

rem  echo input_param_3=%3

) else (
echo input 3th file %3  MD-info as below 
certutil -hashfile %3 MD5
echo=
)


if "%4"=="" (
rem echo input_param_4=%4

) else (
echo input 4th file %4  MD-info as below 
certutil -hashfile %4 MD5
echo=
)


if "%5"=="" (
rem echo input_param_5=%5

) else (
echo input 5th file %5  MD-info as below 
certutil -hashfile %5 MD5
echo=
)



if "%6"=="" (
rem echo input_param_6=%6

) else (
echo input 6th file %6  MD-info as below 
certutil -hashfile %6 MD5
echo=
)



if "%7"=="" (
rem echo input_param_7=%7

) else (
echo input 7th file %7  MD-info as below 
certutil -hashfile %7 MD5
echo=
)


if "%8"=="" (

rem echo input_param_8=%8
) else (
echo input 8th file %8  MD-info as below 
certutil -hashfile %8 MD5
echo=
)



if "%9"=="" (

rem echo input_param_9=%9

) else (
echo input 9th file %9  MD-info as below 
certutil -hashfile %9 MD5
echo=
)



