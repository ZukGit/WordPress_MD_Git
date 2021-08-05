#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
classpath=$classes:$HOME/Desktop/zbin/D6_metadata-extractor-2.11.0.jar:$HOME/Desktop/zbin/D6_mp3agic-0.9.1.jar:$HOME/Desktop/zbin/D6_xmpcore-5.1.2.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/D6_FileNameSearch.java
@javac -cp $HOME/Desktop/zbin/D6_metadata-extractor-2.11.0.jar:$HOME/Desktop/zbin/D6_mp3agic-0.9.1.jar:$HOME/Desktop/zbin/D6_xmpcore-5.1.2.jar:  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\D6_FileNameSearch.java
java  -classpath $classpath   -Xmx10240m -Xms10240m -Xmn5120m   -Dfile.encoding=UTF-8 D6_FileNameSearch  $1 $2 $3 $4 $5 $6 $7 $8 $9 
