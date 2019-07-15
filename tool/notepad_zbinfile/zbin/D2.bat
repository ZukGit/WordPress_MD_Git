@echo off
@cd %1
@javac -encoding UTF-8 D2.java
@java D2 %2
@exit