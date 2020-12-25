#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/J8_ezmorph-1.0.6.jar:$HOME/Desktop/zbin/J8_javacpp.jar:$HOME/Desktop/zbin/J8_javacv.jar:$HOME/Desktop/zbin/J8_opencv-2.4.8-macosx-x86_64.jar:$HOME/Desktop/zbin/J8_opencv-320.jar:$HOME/Desktop/zbin/
#classpath=$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/J8_TakePic_ORC.java 
java  -classpath $classpath J8_TakePic_ORC $1 $2 $3 $4 $5