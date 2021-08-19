@ECHO off   
setlocal enabledelayedexpansion    
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit   
start cmd /K D:   
start cmd /K "cd /d D:/zsoft/"    
