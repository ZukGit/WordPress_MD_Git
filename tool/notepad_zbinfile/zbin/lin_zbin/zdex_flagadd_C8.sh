#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/C8_AOSP_AddDexFlag.java 
java  -classpath $classpath C8_AOSP_AddDexFlag $1 $2 $3 $4 $5