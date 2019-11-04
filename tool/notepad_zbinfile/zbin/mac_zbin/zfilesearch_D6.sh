#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/D6_FileNameSearch.java 
java  -classpath $classpath D6_FileNameSearch $CURPATH $1 $2 $3 $4 $5 $6 $7 $8 $9 