@ECHO off
Setlocal enabledelayedexpansion
set hh=%time:~0,2%
if /i %hh% LSS 10 (set hh=0%time:~1,1%)
set CURRENT_DATE_TIME_STAMP=%date:~0,4%%date:~5,2%-%date:~8,2%-%hh%-%time:~3,2%-%time:~6,2%-
git add ./
git commit -m %CURRENT_DATE_TIME_STAMP%____Zukgit'Commit
git push
@pause