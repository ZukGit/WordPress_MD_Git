@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
set input_path=%1

if "%1"=="" (
echo 输入的参数地址不存在 将打开本地路径文件夹
explorer.exe %cur_path%
) else (

set first_two_char=!input_path:~0,2!
set rest_path=!input_path:~1!
set fixed_first_two_char=!first_two_char!

if "!first_two_char!" == ".\" (
set compare_path=!cur_path:~2!
echo 相对路径 %cur_path%!rest_path!
explorer.exe %cur_path%!rest_path!
) else (
echo 绝对路径 %input_path%
explorer.exe  %input_path%
)
)

                         
                                                    





