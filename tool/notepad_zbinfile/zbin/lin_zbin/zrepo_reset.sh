#!/bin/bash
##################### CUR_REPO_PATH Begin #####################
CUR_REPO_PATH=$(pwd)
cur_shell_path="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 

echo "════════════════════════════0.  F0_repo_init.sh  Cherry-Pick Init Begin  ════════════════════════════  "  
raw_F0_repo_init="$HOME/Desktop/zbin/F0_repo_init.sh"
cur_F0_repo_init="$CUR_REPO_PATH/F0_repo_init.sh"
echo "HOME=$HOME"
echo "raw_F0_repo_init=$raw_F0_repo_init"
echo "cur_F0_repo_init=$cur_F0_repo_init"

    if [ ! -f "$raw_F0_repo_init" ]; then
           echo " Raw  File $raw_F0_repo_init Not Exist!!!! "
    
       else   
           echo " Raw  File $raw_F0_repo_init  Exist! "

            if [ ! -f "$cur_F0_repo_init" ]; then
                   echo "Raw File $cur_F0_repo_init  Not Exist! "
                   cp $raw_F0_repo_init   $cur_F0_repo_init
                   echo " Cur File $cur_F0_repo_init  Copy Operation Finish ! "
                    
               else   
                   echo "Cur File $cur_F0_repo_init Exist! "
            fi
            
    fi


echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "cur_shell_path="$cur_shell_path
echo "try execute  [ repo forall -c pwd ] please waitting! "
repo forall -c pwd > ./repo_pwd.txt
repo forall -c git clean -dxf
line_number=1
all_number=`grep -n  ""   ./repo_pwd.txt | wc -l`
 echo "════════════════════════════════════════""["$all_number"]"" Git Reset Begin ════════════════════════════════════════"
for line in `cat ./repo_pwd.txt`
do
 cd $line
 

git_status=`git status | grep "commits"`
git_release=`git status | grep "release"`
git_untracked=`git status | grep "Untracked files"`

echo -e "\n index=["$line_number"]["$all_number"]"  "pwd="$line "git_status="$git_status 
git_desc=`git status`
echo "git_desc="$git_desc
git checkout TEMP
git clean -dxf

if [ -n "$git_status" ]; then
 echo "________ go git_status block 1_"
    if [ -n "$git_untracked" ]; then
             echo "________ go git_untracked block 2_"
             echo "cur_path="$line " try to remove the untracked and git checkout .   "
             echo "cd "$line " && " "rm -fr ./ &&  git checkout . "
             git clean -dxf
             rm -fr ./
             git checkout .
    fi


    if [ -z "$git_release" ]; then
            echo "________ go git_release block 3_"
            echo "════════════════════════════════════════""index=["$line_number"]["$all_number"]"  "pwd="$line "git_status="$git_status"════════════════════════"


            echo "cur_path="$line " try to execute  <git reset --hard HEAD~5>  <repo sync .> "
            git clean -dxf
            git reset --hard HEAD~5
            repo sync .
            echo "cd "$line " && " "git status "
            echo "══════════End["$line_number"]["$all_number"]════════════"  "pwd="$line    "git_status="$git_status

        else
          echo "________ go git_release_else  block 4_"
          echo "cd "$line " && " "git status "
          echo "═#═#══#═#══#══End["$line_number"]["$all_number"]═#═#══#═#══#══"  "pwd="$line    "git_status="$git_status
    fi
  else
      echo "________ go git_status_else  block 5_ "
      echo "cd "$line " && " "git status "
      echo "═════════End["$line_number"]["$all_number"]═════════"  "pwd="$line    "git_status="$git_status
fi

 let line_number=line_number+1
done

cd $CUR_REPO_PATH


echo "════════════════════════════════════════""["$all_number"]"" Git Repo Reset Finish ════════════════════════════════════════"
echo "════════════════════════════ 1.   mv out out_1 "
date_time=`date +%Y%m%d_%H%M%S`
echo "date_time=$date_time"
## mv ./out  ./out_"$date_time"
## mv ./kernel_platform/out ./kernel_platform/out_"$date_time"
## mv ./release  ./release_"$date_time"
echo "════════════════════════════ 2.   repo sync -j2  "
## repo sync -j2    ##  sync at F0_repo_init.sh
echo "════════════════════════════  repo sync -j2 finish  ════════════════════════════  "


echo "════════════════════════════3.  F0_repo_init  Cherry-Pick Operation Begin  ════════════════════════════  "
raw_zrepo_reset="$HOME/Desktop/zbin/lin_zbin/zrepo_rest.sh"
$cur_F0_repo_init
if [ $? -eq 0 ]; then
    echo "_____ $cur_F0_repo_init  build success _____raw_zrepo_reset=$raw_zrepo_reset "
else
    echo "_____ $cur_F0_repo_init build failed ____rereset_____raw_zrepo_reset=$raw_zrepo_reset ___ "
$raw_zrepo_reset
fi



 