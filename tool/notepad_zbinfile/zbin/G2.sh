#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
libs=$DIR
classpath=$classes:$HOME/Desktop/zbin/G2_jshortcut_oberzalek.jar:$HOME/Desktop/zbin/G2_pdfbox.jar:$HOME/Desktop/zbin/G2_commons-logging-api.jar:$HOME/Desktop/zbin/G2_hutool.jar:$HOME/Desktop/zbin/G2_webp-imageio.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/G2_ApplyRuleFor_TypeFile.java
java  -classpath $classpath G2_ApplyRuleFor_TypeFile $1 $2 $3 $4 $5
