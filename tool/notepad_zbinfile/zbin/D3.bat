@echo off
@cd %1
@javac   -cp D3_hutool.jar;D3_jsoup.jar  -encoding UTF-8 D3.java
@java -cp .;D3_hutool.jar;D3_jsoup.jar D3 %2
@pause