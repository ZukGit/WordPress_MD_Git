#!/bin/bash
##################### CUR_REPO_PATH Begin #####################
CUR_REPO_PATH=$(pwd)
cur_shell_path="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "cur_shell_path="$cur_shell_path
echo "try execute  [ repo forall -c pwd ] please waitting! "
repo forall -c pwd > ./repo_pwd.txt

line_number=1
all_number=`grep -n  ""   ./repo_pwd.txt | wc -l`
 echo "════════════════════════════════════════""["$all_number"]"" Git Reset Begin ════════════════════════════════════════"
for line in `cat ./repo_pwd.txt`
do
 cd $line
git_status=`git status | grep "commits"`
git_release=`git status | grep "release"`

 echo "index=["$line_number"]["$all_number"]"  "pwd="$line "git_status="$git_status
if [ -n "$git_status" ]; then
    if [ -z "$git_release" ]; then
        echo "════════════════════════════════════════""index=["$line_number"]["$all_number"]"  "pwd="$line "git_status="$git_status"════════════════════════"
        echo "cur_path="$line " try to execute  <git reset --hard HEAD~5>  <repo sync .> "
        git reset --hard HEAD~5
        repo sync .
        echo "cd "$line " && " "git status "
        echo "══════════End["$line_number"]["$all_number"]════════════"  "pwd="$line    "git_status="$git_status
    fi
  else
      echo "cd "$line " && " "git status "
      echo "__________End["$line_number"]["$all_number"]____________"  "pwd="$line    "git_status="$git_status

fi

 let line_number=line_number+1
done

cd $CUR_REPO_PATH

echo "════════════════════════════════════════""["$all_number"]"" Git Repo Reset Finish ════════════════════════════════════════"
echo "══════1.   rm -fr out* "
echo "══════2.   repo sync -j2  "
