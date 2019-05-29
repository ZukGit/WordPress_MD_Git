#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
classes=$DIR

libs=$DIR
classpath=$classes:$libs/B8_javaparser.jar:
javac  -classpath $classpath -encoding UTF-8  B8.java 
java -classpath $classpath B8 
echo "shell over.."