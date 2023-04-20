@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
set cur_path=%cd%
set input_path=%1
set perl_concat_path=%userprofile%\Desktop\zbin\win_zbin\log_concat.pl
echo 把当前 main.log 等文件中的 reportLocation Location 位置信息转换为 谷歌地图能识别的 klm文件 !
			
echo 多文件输入执行   ## *所有目标Location的Log文件
echo  cur_path=%cur_path% 
echo  perl %perl_concat_path% %1                     
perl %perl_concat_path%  %1 
