#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/F1_DicWlan.java 
java  -classpath $classpath F1_DicWlan $1 $2 $3 $4 $5