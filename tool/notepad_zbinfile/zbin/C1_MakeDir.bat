@echo ���Ƚ�Ҫ�½��ļ��е����Ʊ��浽��ǰbatĿ¼�е� C1_MakeDir_List.txt ��(һ���ļ�����ռһ��)
cd /d %~dp0
@pause
@for /f %%a in (C1_MakeDir_List.txt) do (md %%a)
@echo �����½��ļ������
@pause