@echo off
@cd %1
@javac  -cp D0_mp4tool.jar -encoding UTF-8 D0_MP4_NameWithSize.java
@java -cp .;D0_mp4tool.jar D0_MP4_NameWithSize %2