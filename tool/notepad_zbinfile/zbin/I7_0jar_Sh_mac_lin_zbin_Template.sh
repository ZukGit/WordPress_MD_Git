#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin
~/Desktop/zbin/I7.sh $CURPATH $1 $2 $3 $4 $5 6 $7 $8 $9 
# javac -encoding UTF-8 ~/Desktop/zbin/I7.java 
# java  -classpath $classpath I7 $CURPATH $1 $2 $3 $4 $5 6 $7 $8 $9 