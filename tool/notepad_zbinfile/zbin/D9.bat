@echo off
@cd %1
@javac -encoding UTF-8 D9_DeleteSpecialRowStr.java
@echo %3 
@java D9_DeleteSpecialRowStr %2 %3