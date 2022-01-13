#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/D6_metadata-extractor-2.11.0.jar:$HOME/Desktop/zbin/D6_mp3agic-0.9.1.jar:$HOME/Desktop/zbin/D6_xmpcore-5.1.2.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 ~/Desktop/zbin/D6_FileNameSearch.java 
java  -classpath $classpath D6_FileNameSearch $CURPATH $1 $2 $3 $4 $5