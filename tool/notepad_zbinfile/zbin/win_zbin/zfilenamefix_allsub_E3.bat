@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set b=%~dp0
echo path=%b%
set path1=%cd%
echo path1=%path1%
@REM    string_replace_D5.bat filez#"txt_md"srcz#"zhuzhengjie"dstz#"guohuixing"
@REM    string_replace_D5.bat filez#"txt"srcz#"zhuzhengjie"dstz#"guohuixing"
@REM    string_replace_D5.bat filez#"txt_md"srcz#"guohuixing"dstz#"zhuzhengjie"
@REM    string_replace_D5.bat filez#"md"srcz#"guohuixing"dstz#"zhuzhengjie"
%userprofile%\Desktop\zbin\E3_AllSubFile.bat  %userprofile%\Desktop\zbin %path1%  %1