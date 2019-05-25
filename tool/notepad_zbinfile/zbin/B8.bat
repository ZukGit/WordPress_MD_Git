@echo off
setlocal EnableDelayedExpansion
echo 当前正在运行的批处理文件所在路径：!cd!
cd !cd!
@javac  -cp B8_javaparser.jar -encoding UTF-8  B8.java 
@java -cp .;B8_javaparser.jar B8
@pause