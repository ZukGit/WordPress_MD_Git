@echo off
@cd %1
@javac -encoding UTF-8 C4.java
@java C4 %2
@exit