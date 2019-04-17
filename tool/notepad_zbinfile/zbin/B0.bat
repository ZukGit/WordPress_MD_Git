@echo off
@cd %1
@javac  -cp B0_pinyin4j.jar -encoding UTF-8  B0.java 
@java -cp .;B0_pinyin4j.jar B0 %2
@pause