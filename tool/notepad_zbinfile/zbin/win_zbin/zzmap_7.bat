@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
set input_path=%1
set perl_kml_path=%userprofile%\Desktop\zbin\win_zbin\zzmap_gpsLocation_parser_new_7.pl
echo 把当前 main.log 等文件中的 reportLocation Location 位置信息转换为 谷歌地图能识别的 klm文件 !
echo ══════════════════			
echo 单独一个输入文件可执行   ## 单独目标Location的Log文件
echo zzmap_7.bat  aplogcat-main.txt                       
echo ══════════════════				
echo 多文件输入执行   ## *所有目标Location的Log文件
echo  perl %perl_kml_path% *.txt                           
perl %userprofile%\Desktop\zbin\win_zbin\zzmap_gpsLocation_parser_new_7.pl  %1
