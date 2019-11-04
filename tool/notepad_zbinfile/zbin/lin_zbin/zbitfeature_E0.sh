#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/E0_bitfeature.java 
java  -classpath $classpath E0_bitfeature $1 $2 $3 $4 $5 6 $7 $8 $9 