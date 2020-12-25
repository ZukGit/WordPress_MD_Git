#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/J7_jna.jar:$HOME/Desktop/zbin/J7_jna_platform.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/J7_WallPager_Changed.java 
java  -classpath $classpath J7_WallPager_Changed $1 $2 $3 $4 $5
