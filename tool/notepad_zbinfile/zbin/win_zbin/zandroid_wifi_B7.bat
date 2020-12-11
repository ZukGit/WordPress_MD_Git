@ECHO off
adb root
adb remount
adb root & adb shell cmd wifi  get-ipreach-disconnect  
adb root & adb shell cmd wifi  get-poll-rssi-interval-msecs   
adb root & adb shell cmd wifi  get-country-code 
adb root & adb shell cmd wifi  status   
adb root & adb shell cmd wifi  list-suggestions 
adb root & adb shell cmd wifi  get-softap-supported-features  
adb root & adb shell cmd wifi  list-scan-results  
