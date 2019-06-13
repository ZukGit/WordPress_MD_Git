#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
cd $DIR
classes=$DIR

libs=$DIR
classpath=$classes:$libs/C3_javaparser.jar:
./prebuilts/jdk/jdk8/linux-x86/bin/javac  -classpath $classpath -encoding UTF-8  C3_Zanalyze.java 
./prebuilts/jdk/jdk8/linux-x86/bin/java -classpath $classpath C3_Zanalyze #>> "$DIR/javalog.txt"
echo "shell over.."