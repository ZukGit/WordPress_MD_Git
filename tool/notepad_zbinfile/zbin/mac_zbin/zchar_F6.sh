#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/F6_ZChar.java 
java  -classpath $classpath F6_ZChar $1 $2 $3 $4 $5 $6 $7 $8 $9 