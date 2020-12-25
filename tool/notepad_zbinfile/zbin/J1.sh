#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/J1_guava.jar:$HOME/Desktop/zbin/J1_jshortcut_oberzalek.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/J1_InstallSoftware.java 
java  -classpath $classpath J1_InstallSoftware $1 $2 $3 $4 $5
