#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
cd $DIR
classes=$DIR

libs=$DIR
classpath=$classes:$libs/B8_javaparser.jar:
./prebuilts/jdk/jdk8/linux-x86/bin/javac  -classpath $classpath -encoding UTF-8  B8.java 
./prebuilts/jdk/jdk8/linux-x86/bin/java -classpath $classpath B8 #>> "$DIR/javalog.txt"
echo "shell over.."