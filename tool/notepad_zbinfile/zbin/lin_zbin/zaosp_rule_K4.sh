#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
classpath=$classes:$HOME/Desktop/zbin/K4_hutool.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/K4_AOSP_Rule.java
java  -classpath $classpath   -Dfile.encoding=UTF-8   K4_AOSP_Rule  $1 $2 $3 $4 $5 $6 $7 $8 $9 
