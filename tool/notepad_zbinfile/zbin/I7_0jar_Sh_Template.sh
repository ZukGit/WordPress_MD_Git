#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
classes=$DIR

libs=$DIR
classpath=$classes:$libs:
#classpath=$classes:$libs/B8_javaparser.jar:
javac  -classpath $classpath -encoding UTF-8  I7.java 
java -classpath $classpath I7   $1 $2 $3 $4 $5 6 $7 $8 $9 
echo "shell over.."