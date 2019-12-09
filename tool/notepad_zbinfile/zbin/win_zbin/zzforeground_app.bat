@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
adb shell dumpsys window | grep mCurrentFocus