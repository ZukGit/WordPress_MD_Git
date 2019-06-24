#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
cd $DIR
classes=$DIR
libs=$DIR
./prebuilts/jdk/jdk8/linux-x86/bin/javac  -encoding UTF-8  C5_Qcom_WCNSS_Analysis.java 
./prebuilts/jdk/jdk8/linux-x86/bin/java  C5_Qcom_WCNSS_Analysis #>> "$DIR/javalog.txt"
echo "shell over.."