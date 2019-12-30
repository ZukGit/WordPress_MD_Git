#!/bin/bash
. /etc/profile
 dos2unix   ~/Desktop/zbin/C6_git_repository_path.txt
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
   echo "fill absolute path for git to file ~/Desktop/zbin/C6_git_repository_path.txt" 
   echo "==============================="
for line in $(cat ~/Desktop/zbin/C6_git_repository_path.txt)
do 
   echo currentLine=$line
   echo "pwd= "$( pwd )
    Checkcommand="$( cd "$line" && git branch -vv )"
  
   echo "Checkcommand="$Checkcommand

   echo "==============================="
done