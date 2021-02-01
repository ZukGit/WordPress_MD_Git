#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/F3_hutool.jar:$HOME/Desktop/zbin/F3_zxing.jar:$HOME/Desktop/zbin/F3_BT.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/F3_BT.java 
java  -classpath $classpath F3_BT $1 $2 $3 $4 $5
