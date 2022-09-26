Setlocal ENABLEDELAYEDEXPANSION
set  A_dp0=%~dp0
set  A_inpu_1=%1
set  A_cd_path=%cd%
echo [A_cd_path=%cd_path%]   [A_inpu_1=%A_inpu_1%]    [A_dp0=%dp0%]
call %userprofile%\Desktop\zbin\win_rbin\A1_rust_rule.bat %cd% %1 %2 %3 %4 %5 %6 %7 %8 %9  