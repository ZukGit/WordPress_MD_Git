#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/J6_javaparser.jar:~/Desktop/zbin
javac   -classpath $classpath  -encoding UTF-8 ~/Desktop/zbin/J6_TAG_ModifyFlag_AOSP.java 
java  -classpath $classpath J6_TAG_ModifyFlag_AOSP $CURPATH $1 $2 $3 $4 $5 6 $7 $8 $9 