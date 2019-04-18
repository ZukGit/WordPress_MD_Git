@echo off
@cd %1
@javac  -cp B1_hutool.jar;B1_zxing.jar -encoding UTF-8  B1.java 
@java -cp .;B1_hutool.jar;B1_zxing.jar B1 %2
@exit