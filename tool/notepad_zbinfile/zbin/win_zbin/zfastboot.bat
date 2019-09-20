@ECHO off
Setlocal enabledelayedexpansion
adb kill-server
adb reboot bootloader
@pause