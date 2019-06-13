@echo off
setlocal EnableDelayedExpansion
echo 当前正在运行的批处理文件所在路径：!cd!
cd !cd!
@javac  -cp C3_javaparser.jar -encoding UTF-8  C3_Zanalyze.java 
@java -cp .;C3_javaparser.jar C3_Zanalyze
@pause