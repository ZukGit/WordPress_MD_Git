@ECHO off
Setlocal ENABLEDELAYEDEXPANSION
adb kill-server
adb start-server
adb -s c2f5b32c tcpip 5555 &  adb -s 721QECRM2ZMT4 tcpip 5555 & adb -s HMKNW17531001873 tcpip 5555
adb connect 192.168.31.89:5555
adb connect 192.168.31.40:5555
adb connect 192.168.31.10:5555
adb reconnect device
adb reconnect offline
adb reconnect device
ping -n 3 127.0.0.1>nul
echo ________________________________ ADB_Connect_List_0 ________________________________
adb devices

echo ________________________________ ADB_Connect_List_1 ________________________________
adb devices

