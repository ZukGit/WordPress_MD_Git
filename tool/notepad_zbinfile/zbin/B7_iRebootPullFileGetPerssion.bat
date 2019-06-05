@ECHO off

ECHO Initized
@SET PATH=%PATH%;%CD%\Adb

:LOOP

ECHO waiting device join USB
adb wait-for-device



adb disable-verity && adb reboot bootloader && fastboot oem ssm_test 10 1  &&  fastboot reboot


ECHO   END!
PAUSE
GOTO LOOP
@ECHO on