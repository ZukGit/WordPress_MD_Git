@echo off
Setlocal ENABLEDELAYEDEXPANSION
::����������չ���μ�setlocal /?����
set str1=package:/data/app/com.baidu.quickmind-1/base.apk=com.baidu.quickmind
set ch1==
::ע�⣬���������ִ�Сд�ģ�
set str=%str1%
::�����ַ����������ض̣�����Ӱ��Դ�ַ���
:indexofnext
if not "%str%"=="" (
set /a num+=1
if "!str:~0,1!"=="%ch1%" goto last
::�Ƚ����ַ��Ƿ�ΪҪ����ַ��������������ѭ��
set "str=%str:~1%"
goto indexofnext
)
set /a num=0
::û���ҵ��ַ�ʱ����num����
:last
echo �ַ�'%ch1%'���ַ���"%str1%"�е��״γ���λ��Ϊ%num%
       set s=!s:~8!
       set s=%str1:% 
echo �����ϣ���������˳�&&pause>nul&&exit
pause


