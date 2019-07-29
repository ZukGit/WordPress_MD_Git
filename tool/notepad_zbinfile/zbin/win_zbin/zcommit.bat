@ECHO off
Setlocal enabledelayedexpansion
git add ./
set CURRENT_DATE_TIME_STAMP=%date:~0,4%年%date:~5,2%月%date:~8,2%日-%hh%时%time:~3,2%分%time:~6,2%秒
git commit -m %CURRENT_DATE_TIME_STAMP%______Zukgit-的提交
git push
@pause