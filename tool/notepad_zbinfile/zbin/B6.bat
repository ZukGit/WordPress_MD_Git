@echo off
@cd %1
@javac  -cp B6_hutool.jar;B6_fastjson.jar -encoding UTF-8  B6.java 
@java -cp .;B6_hutool.jar;B6_fastjson.jar B6 %2
@exit