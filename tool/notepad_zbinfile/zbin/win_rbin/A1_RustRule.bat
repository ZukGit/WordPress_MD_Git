@echo off
Setlocal ENABLEDELAYEDEXPANSION
set  B_dp0=%~dp0
set  B_inpu_1=%1
set  B_cd_path=%cd%
echo [B_cd_path=%B_cd_path%]   [B_inpu_1=%B_inpu_1%]    [B_dp0=%~dp0%]
cd  /d  %B_dp0%
set  win_rbin=%userprofile%\Desktop\zbin\win_rbin
rustc.exe  %win_rbin%\A1_RustRule.rs  --extern num_traits=%win_rbin%\libnum_traits.rlib  --extern num_integer=%win_rbin%\libnum_integer.rlib --extern winapi=%win_rbin%\libwinapi.rlib --extern chrono=%win_rbin%\libchrono.rlib --extern time=%win_rbin%\libtime.rlib 

%win_rbin%\A1_RustRule.exe %1 %2 %3 %4 %5 %6 %7 %8 %9   