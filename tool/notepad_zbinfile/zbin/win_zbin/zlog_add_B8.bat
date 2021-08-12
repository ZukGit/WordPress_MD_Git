@echo off
setlocal EnableDelayedExpansion
set b=%~dp0
set pathA=%cd%
echo cd=%cd%
@REM    D5_SrcReplace.bat filez#"txt_md"srcz#"zhuzhengjie"dstz#"guohuixing"
@REM    D5_SrcReplace.bat filez#"txt"srcz#"zhuzhengjie"dstz#"guohuixing"
@REM    D5_SrcReplace.bat filez#"txt_md"srcz#"guohuixing"dstz#"zhuzhengjie"
@REM    D5_SrcReplace.bat filez#"md"srcz#"guohuixing"dstz#"zhuzhengjie"
%userprofile%\Desktop\zbin\B8.bat   %pathA%  %1  %2  %3  %4  %5  %6  %7 %8  %9 