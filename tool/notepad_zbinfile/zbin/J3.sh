#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
#classpath=$classes:$HOME/Desktop/zbin/I9_pinyin4j.jar:$HOME/Desktop/zbin/I9_fastjson.jar:$HOME/Desktop/zbin/I9_zxing.jar:$HOME/Desktop/zbin/I9_hutool.jar:$HOME/Desktop/zbin/
classpath=$classes:$HOME/Desktop/zbin/J3_fastjson.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/J3_JsonServer_NPM.java 
java  -classpath $classpath J3_JsonServer_NPM $1 $2 $3 $4 $5
