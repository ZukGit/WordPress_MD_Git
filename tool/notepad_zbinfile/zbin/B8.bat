@echo off
setlocal EnableDelayedExpansion
echo ��ǰ�������е��������ļ�����·����!cd!
cd !cd!
@javac  -cp B8_javaparser.jar -encoding UTF-8  B8.java 
@java -cp .;B8_javaparser.jar B8
@pause