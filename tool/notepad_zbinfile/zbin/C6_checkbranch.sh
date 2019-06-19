#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
for line in $(cat C6_git_repository_path.txt)
do 
   echo currentLine=$line
   echo "pwd= "$( pwd )
    Checkcommand="$( cd "$line" && git branch -vv )"
  
   echo "Checkcommand="$Checkcommand

   echo "==============================="
done