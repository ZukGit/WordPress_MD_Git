#!/bin/bash


CurPath_Begin=$(pwd)
echo "PATH_Begin="$CurPath_Begin


####### Code Dir Begin
cd ..
Code_Dir=$(pwd)
echo "Code_Dir="$Code_Dir
####══════════ Code Dir Operation Begin ══════════

# cp -fr ./xxxx.java    $CurPath_Begin/external/wpa_supplicant_8/xxx.java   

####══════════ Code Dir Operation End ══════════
cd $CurPath_Begin
####### Code Dir End


##══════════ Cherry_Pick_Template_1 Begin ══════════
#cd $CurPath_Begin/external/wpa_supplicant_8 ## pick-path-1
#git fetch ssh://zzj@gerrit.xxx.com:29418/home/repo/dev/platform/android/platform/external/wpa_supplicant_8 refs/changes/85/1724185/2 && git cherry-pick FETCH_HEAD
#git fetch ssh://zzj@gerrit.xxx.com:29418/home/repo/dev/platform/android/platform/external/wpa_supplicant_8 refs/changes/85/1724185/2 && git cherry-pick FETCH_HEAD
#mPickPath_1=$(pwd)
#echo "mPickPath_1="$mPickPath_1
##══════════ Cherry_Pick_Template_1 End ══════════



##══════════ Cherry_Pick_Template_2 Begin══════════
#cd $CurPath_Begin/vendor/qcom/opensource/wlan/qcacld-3.0  ## pick-path-2
#git fetch ssh://zzj@xxxxx.xxx.xx/home/repo/dev/platform/android/platform/vendor/qcom/opensource/wlan/qcacld-3.0 refs/changes/54/1719154/2 && git cherry-pick FETCH_HEAD
#mPickPath_2=$(pwd)
#echo "mPickPath_2="$mPickPath_2
##══════════ Cherry_Pick_Template_2 End══════════



cd $CurPath_Begin
CurPath_End=$(pwd)
echo "PATH_End="$CurPath_End
