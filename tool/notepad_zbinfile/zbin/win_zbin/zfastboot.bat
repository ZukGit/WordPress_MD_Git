@ECHO off
Setlocal enabledelayedexpansion
adb kill-server
adb root
adb disable-verity
adb reboot bootloader
@pause