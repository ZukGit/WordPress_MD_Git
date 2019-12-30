#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
#echo DIR=$DIR
#echo CURPATH=$CURPATH
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/F7_javaparser.jar:~/Desktop/zbin/
javac  -classpath $classpath -encoding UTF-8  ~/Desktop/zbin/F7_WifiFile.java
java   -classpath $classpath F7_WifiFile  $CURPATH  $1 $2 $3 $4 $5 $6 $7 $8 $9