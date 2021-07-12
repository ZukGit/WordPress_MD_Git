#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
classpath=$classes:$HOME/Desktop/zbin/I9_commons-exec-1.3.jar:$HOME/Desktop/zbin/I9_fastjson.jar:$HOME/Desktop/zbin/I9_guava-25.0-jre.jar:$HOME/Desktop/zbin/I9_guava.jar:$HOME/Desktop/zbin/I9_hutool.jar:$HOME/Desktop/zbin/I9_jsoup.jar:$HOME/Desktop/zbin/I9_okhttp-3.11.0.jar:$HOME/Desktop/zbin/I9_okio-1.14.0.jar:$HOME/Desktop/zbin/I9_pinyin4j.jar:$HOME/Desktop/zbin/I9_selenium-api-3.141.59.jar:$HOME/Desktop/zbin/I9_selenium-chrome-driver-3.141.59.jar:$HOME/Desktop/zbin/I9_selenium-remote-driver-3.141.59.jar:$HOME/Desktop/zbin/I9_zxing.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/I9_TextRuleOperation.java
@javac -cp $HOME/Desktop/zbin/I9_commons-exec-1.3.jar:$HOME/Desktop/zbin/I9_fastjson.jar:$HOME/Desktop/zbin/I9_guava-25.0-jre.jar:$HOME/Desktop/zbin/I9_guava.jar:$HOME/Desktop/zbin/I9_hutool.jar:$HOME/Desktop/zbin/I9_jsoup.jar:$HOME/Desktop/zbin/I9_okhttp-3.11.0.jar:$HOME/Desktop/zbin/I9_okio-1.14.0.jar:$HOME/Desktop/zbin/I9_pinyin4j.jar:$HOME/Desktop/zbin/I9_selenium-api-3.141.59.jar:$HOME/Desktop/zbin/I9_selenium-chrome-driver-3.141.59.jar:$HOME/Desktop/zbin/I9_selenium-remote-driver-3.141.59.jar:$HOME/Desktop/zbin/I9_zxing.jar:  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\I9_TextRuleOperation.java
java  -classpath $classpath   -Xmx10240m -Xms10240m -Xmn5120m I9_TextRuleOperation  $1 $2 $3 $4 $5 $6 $7 $8 $9 
