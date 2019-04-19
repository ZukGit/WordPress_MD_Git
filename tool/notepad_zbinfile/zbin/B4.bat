@echo off
@cd %1
@javac  -cp B4_hutool.jar -encoding UTF-8  B4.java 
@java -cp .;B4_hutool.jar B4 %1
@pause