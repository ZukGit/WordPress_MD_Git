@ECHO off
Setlocal enabledelayedexpansion
git add ./
set CURRENT_DATE_TIME_STAMP=%date:~0,4%��%date:~5,2%��%date:~8,2%��-%hh%ʱ%time:~3,2%��%time:~6,2%��
git commit -m %CURRENT_DATE_TIME_STAMP%______Zukgit-���ύ
git push
@pause