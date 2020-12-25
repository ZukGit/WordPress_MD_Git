#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/G8_hutool.jar:$HOME/Desktop/zbin/G8_jave.jar:$HOME/Desktop/zbin/
#classpath=$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/G8_FFmpeg_Operation.java 
java  -classpath $classpath G8_FFmpeg_Operation $1 $2 $3 $4 $5