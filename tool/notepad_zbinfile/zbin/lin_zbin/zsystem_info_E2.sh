#!/bin/bash

# echo "shell over0.."
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR

libs=$DIR
#classpath=$classes:$libs/C3_javaparser.jar:
classpath=$classes:~/Desktop/zbin/E2_sigar.jar:~/Desktop/zbin/
#javac -classpath $classpath -encoding UTF-8 C3_Zanalyze.java 
javac -classpath $classpath -encoding UTF-8 ~/Desktop/zbin/E2_SystemInfo.java 
#java -classpath $classpath C3_Zanalyze #>> "$DIR/javalog.txt"
java -classpath $classpath E2_SystemInfo $classes $1 $2 $3 $4 $5 $6 $7 $8 $9
#echo "shell over.."