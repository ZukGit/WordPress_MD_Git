@echo off
setlocal EnableDelayedExpansion
echo ��ǰ�������е��������ļ�����·����!cd!
cd !cd!
@javac  -cp C3_javaparser.jar -encoding UTF-8  C3_Zanalyze.java 
@java -cp .;C3_javaparser.jar C3_Zanalyze
@pause