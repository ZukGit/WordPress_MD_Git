@ECHO off
rem  notepad win自带笔记本 另存为时，UTF-8保存为ANSI 格式。即可解决运行是乱码问题
Setlocal enabledelayedexpansion
set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)
set CURRENT_DATE_TIME_STAMP=%date:~0,4%年%date:~5,2%月%date:~8,2%日-%hh%时%time:~3,2%分%time:~6,2%秒
git add ./
git commit -m %CURRENT_DATE_TIME_STAMP%______Zukgit-的提交
git push
@pause