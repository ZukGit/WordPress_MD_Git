@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
set input_path=%1

if "%1"=="" (
echo ����Ĳ�����ַ������ ���򿪱���·���ļ���
explorer.exe %cur_path%
) else (

set first_two_char=!input_path:~0,2!
set rest_path=!input_path:~1!
set fixed_first_two_char=!first_two_char!

if "!first_two_char!" == ".\" (
set compare_path=!cur_path:~2!
echo ���·�� %cur_path%!rest_path!
explorer.exe %cur_path%!rest_path!
) else (
echo ����·�� %input_path%
explorer.exe  %input_path%
)
)

                         
                                                    





