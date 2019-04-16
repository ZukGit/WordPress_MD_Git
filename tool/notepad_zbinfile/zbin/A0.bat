@echo off
@cd %1
@javac  -cp A0_pinyin4j.jar -encoding UTF-8  A0.java 
@java -cp .;A0_pinyin4j.jar A0 %2
@exit