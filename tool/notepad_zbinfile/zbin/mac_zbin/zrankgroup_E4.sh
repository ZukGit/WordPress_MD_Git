#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/

javac  -classpath $classpath -encoding UTF-8 ~/Desktop/zbin/E4_RankGroup.java 
java  -classpath $classpath E4_RankGroup $1 $2 $3 $4 $5 $6 $7 $8 $9 
