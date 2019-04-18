@echo off
@cd %1
@javac  -cp B2_hutool.jar -encoding UTF-8  B2.java 
@java -cp .;B2_hutool.jar B2 %2
@exit