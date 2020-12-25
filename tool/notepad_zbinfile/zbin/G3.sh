#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
#classpath=$classes:$HOME/Desktop/zbin/I9_pinyin4j.jar:$HOME/Desktop/zbin/I9_fastjson.jar:$HOME/Desktop/zbin/I9_zxing.jar:$HOME/Desktop/zbin/I9_hutool.jar:$HOME/Desktop/zbin/
classpath=$HOME/Desktop/zbin/:$HOME/Desktop

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/G3_RedBlackTree.java $HOME/Desktop/zbin/G3_TreeJpanel.java  $HOME/Desktop/zbin/G3_StartFrame.java 
java  -classpath $classpath zbin.G3_StartFrame $1 $2 $3 $4 $5
