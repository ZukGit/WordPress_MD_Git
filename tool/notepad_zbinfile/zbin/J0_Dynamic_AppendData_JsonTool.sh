#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR
classpath=$classes:$HOME/Desktop/zbin/J0_dom4j-1.6.1.jar:$HOME/Desktop/zbin/J0_fastjson.jar:$HOME/Desktop/zbin/J0_guava.jar:$HOME/Desktop/zbin/J0_ojdbc6-11.jar:$HOME/Desktop/zbin/J0_pinyin4j.jar:$HOME/Desktop/zbin/J0_poi-3.9.jar:$HOME/Desktop/zbin/J0_poi-ooxml-3.9.jar:$HOME/Desktop/zbin/J0_poi-ooxml-schemas-3.9.jar:$HOME/Desktop/zbin/J0_poi_3.9.jar:$HOME/Desktop/zbin/J0_xercesImpl.jar:$HOME/Desktop/zbin/J0_xmlbeans-2.3.0.jar:$HOME/Desktop/zbin/

javac -classpath $classpath -encoding UTF-8 $HOME/Desktop/zbin/J0_Dynamic_AppendData_JsonTool.java
@javac -cp $HOME/Desktop/zbin/J0_dom4j-1.6.1.jar:$HOME/Desktop/zbin/J0_fastjson.jar:$HOME/Desktop/zbin/J0_guava.jar:$HOME/Desktop/zbin/J0_ojdbc6-11.jar:$HOME/Desktop/zbin/J0_pinyin4j.jar:$HOME/Desktop/zbin/J0_poi-3.9.jar:$HOME/Desktop/zbin/J0_poi-ooxml-3.9.jar:$HOME/Desktop/zbin/J0_poi-ooxml-schemas-3.9.jar:$HOME/Desktop/zbin/J0_poi_3.9.jar:$HOME/Desktop/zbin/J0_xercesImpl.jar:$HOME/Desktop/zbin/J0_xmlbeans-2.3.0.jar:  -Xlint:unchecked -encoding UTF-8 %userprofile%\Desktop\zbin\J0_Dynamic_AppendData_JsonTool.java
java  -classpath $classpath   -Xmx10240m -Xms10240m -Xmn5120m   -Dfile.encoding=UTF-8   J0_Dynamic_AppendData_JsonTool  $1 $2 $3 $4 $5 $6 $7 $8 $9 
