#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/H5_commons-compress-1.20.jar:$HOME/Desktop/zbin/H5_java-unrar-20120702.jar:$HOME/Desktop/zbin/H5_javatar-2.5.jar:$HOME/Desktop/zbin/H5_junrar-0.7.jar:$HOME/Desktop/zbin/H5_sevenzipjbinding-9.20.jar:$HOME/Desktop/zbin/H5_sevenzipjbinding-all-platforms-16.02.jar:$HOME/Desktop/zbin/H5_xz-1.8.jar:$HOME/Desktop/zbin/H5_zip4j_1.3.2.jar:$HOME/Desktop/zbin/
#classpath=$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/H5_Zip.java 
java  -classpath $classpath H5_Zip $1 $2 $3 $4 $5 $6 $7 $8 $9