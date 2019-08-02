#!/bin/bash 
echo param1=$1
echo param2=$2
cd $1
javac    -encoding UTF-8  D4_ImgSrcRevert.java 
java   D4_ImgSrcRevert $2
echo "shell over.."