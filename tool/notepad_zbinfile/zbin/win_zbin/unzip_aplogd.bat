@echo off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
echo cur_path=%cur_path%
set gzip_path=%userprofile%\Desktop\zbin\win_zbin\gzip.exe
set z7_path=%userprofile%\Desktop\zbin\win_zbin\7z.exe

for %%i in (*.gz) do ( 
    %gzip_path% -d %%i
	echo %%i
)


for %%i in (*.zip) do ( 
    %z7_path% x -y %%i -o%%~ni 1>nul
	echo %z7_path% x -y %%i -o%%~ni
	echo %%i
)

for %%i in (*.rar) do ( 
    %z7_path% x -y %%i -o%%~ni 1>nul
	echo %z7_path% x -y %%i -o%%~ni
	echo %%i
)

for %%i in (*.7z) do ( 
    %z7_path% x -y %%i -o%%~ni 1>nul
	echo %z7_path% x -y %%i -o%%~ni
	echo %%i
)

echo done!
rem for %%i in (*.gz) do "C:\Program Files\7-Zip\7z.exe" x -y %%i -o%%~ni 1>nul
rem for %%i in (%cur_path%*.zip) do "C:\Program Files\7-Zip\7z.exe" x -y %%i -o%%~ni 1>nul
rem for %%i in (%cur_path%*.rar) do "C:\Program Files\7-Zip\7z.exe" x -y %%i -o%%~ni 1>nul
rem for %%i in (%cur_path%*.7z) do "C:\Program Files\7-Zip\7z.exe" x -y %%i -o%%~ni 1>nul

