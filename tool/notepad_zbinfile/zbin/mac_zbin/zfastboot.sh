#!/bin/bash

function pause(){
        read -n 1 -p "$*" INP
        if [ $INP != '' ] ; then
                echo -ne '\b \n'
        fi
}

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR

adb kill-server
adb root
adb disable-verity
adb reboot bootloader
pause 'Press any key to continue...'
