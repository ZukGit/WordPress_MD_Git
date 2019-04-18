@echo off
@cd %1
@javac  -cp B3_hutool.jar -encoding UTF-8  B3.java 
@java -cp .;B3_hutool.jar B3 %2
@exit