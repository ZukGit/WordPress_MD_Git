@echo 请先将要新建文件夹的名称保存到当前bat目录中的 C1_MakeDir_List.txt 中(一个文件夹名占一行)
cd /d %~dp0
@pause
@for /f %%a in (C1_MakeDir_List.txt) do (md %%a)
@echo 批量新建文件夹完成
@pause