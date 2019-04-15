@echo off
@cd %1
@javac  -cp A9_pinyin4j.jar -encoding UTF-8  A9.java 
@java -cp .;A9_pinyin4j.jar A9 %2
@exit