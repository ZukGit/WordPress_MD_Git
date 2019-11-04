#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin

javac -encoding UTF-8 ~/Desktop/zbin/E7_Tree.java 
java  -classpath $classpath E7_Tree $1 $2 $3 $4 $5