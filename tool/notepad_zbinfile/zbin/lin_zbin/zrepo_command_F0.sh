#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
#echo DIR=$DIR
#echo CURPATH=$CURPATH
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/

javac -encoding UTF-8 ~/Desktop/zbin/F0_RepoCommand.java 
java  -classpath $classpath F0_RepoCommand $CURPATH $1 $2 $3 $4 $5
