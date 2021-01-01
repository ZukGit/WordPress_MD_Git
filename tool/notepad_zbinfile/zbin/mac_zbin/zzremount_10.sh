#!/bin/bash
UUID_Str=`diskutil info /Volumes/$1 | grep UUID`
UUID_Str_Real=` echo $UUID_Str | awk '{print $3}'  `
echo UUID_Str_Real=$UUID_Str_Real
UUID_Str_Command=`echo " echo UUID="$UUID_Str_Real" none ntfs rw,auto,nobrowse | sudo tee -a /etc/fstab " `
echo /Volumes/$1 Remount Comamnd as below:
echo ================
echo  $UUID_Str_Command
echo ================

$UUID_Str_Command
#DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
#classes=$DIR
#$HOME/Desktop/zbin/H5.sh $CURPATH $1 $2 $3 $4 $5 $6 $7 $8 $9
#   diskutil info /Volumes/Z_Disk | grep UUID
#    source "diskutil info /Volumes/Z_Disk| grep UUID"