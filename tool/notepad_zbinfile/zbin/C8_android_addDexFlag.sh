#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
cd $DIR
classes=$DIR

libs=$DIR
./prebuilts/jdk/jdk8/linux-x86/bin/javac   -encoding UTF-8  C8_AOSP_AddDexFlag.java 
./prebuilts/jdk/jdk8/linux-x86/bin/java  C8_AOSP_AddDexFlag #>> "$DIR/javalog.txt"
echo "add dex flag over!"