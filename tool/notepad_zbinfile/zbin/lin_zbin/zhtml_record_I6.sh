#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/

javac -encoding UTF-8 ~/Desktop/zbin/I6_HtmlRecord.java 
java  -classpath $classpath I6_HtmlRecord $classes $1 $2 $3 $4 $5
