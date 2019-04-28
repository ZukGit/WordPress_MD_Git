@echo off
@cd %1
@javac  -cp B2_hutool.jar -encoding UTF-8  A6.java 
@java -cp .;B2_hutool.jar A6 %2
@exit