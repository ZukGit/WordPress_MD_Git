#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:~/Desktop/zbin/B8_javaparser.jar:~/Desktop/zbin/

/opt/jdk/jdk1.8.0_73/bin/javac  -classpath $classpath -encoding UTF-8 ~/Desktop/zbin/B8_LogAdd.java 
#java -server -Xmx16000m -Xms16000m -Xss8012k -classpath $classpath B8_LogAdd $1 $2 $3 $4 $5 $6 $7 $8 $9 
/opt/jdk/jdk1.8.0_73/bin/java  -classpath $classpath B8_LogAdd $DIR $1 $2 $3 $4 $5  $6 $7 $8 $9 