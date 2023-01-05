#!/bin/bash
##################### CUR_REPO_PATH Begin #####################
## HOME=/home/zhuzj5
## raw_F0_repo_init=/home/zhuzj5/Desktop/zbin/F0_repo_init.sh
## cur_F0_repo_init=/home/zhuzj5/Desktop/MotoCode/GenevnT/Code1/Vendor5/F0_repo_init.sh
## Raw  File /home/zhuzj5/Desktop/zbin/F0_repo_init.sh  Exist! 
## Cur File /home/zhuzj5/Desktop/MotoCode/GenevnT/Code1/Vendor5/F0_repo_init.sh Exist! 
## CUR_REPO_PATH=/home/zhuzj5/Desktop/MotoCode/GenevnT/Code1/Vendor5
## zrepo_reset_dir_path=/home/zhuzj5/Desktop/zbin/lin_zbin



## 当前AOSP位置
CUR_REPO_PATH=$(pwd)       

## 当前 ~/Desktop/zbin/lin_zbin/zrepo_reset.sh 的目录
zrepo_reset_dir_path="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"  

echo "════════════════════════════0.  F0_repo_init.sh  Cherry-Pick Init Begin  ════════════════════════════  "  
raw_F0_repo_init="$HOME/Desktop/zbin/F0_repo_init.sh"  ##  系统 ~/Desktop/zbin/lin_zbin/F0_repo_init.sh 路径
cur_F0_repo_init="$CUR_REPO_PATH/F0_repo_init.sh"      ##  AOSP目录中的 F0_repo_init.sh
echo "HOME=$HOME"                                        
echo "raw_F0_repo_init=$raw_F0_repo_init"
echo "cur_F0_repo_init=$cur_F0_repo_init"


## ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤  1A 复制 系统  ~/Desktop/zbin/lin_zbin/F0_repo_init.sh 到 AOSP根目录 
    if [ ! -f "$raw_F0_repo_init" ]; then
	        ## 系统 ~/Desktop/zbin/lin_zbin/F0_repo_init.sh 路径 文件不存在
           echo " Raw  File $raw_F0_repo_init Not Exist!!!! "   
    
       else   
           echo " Raw  File $raw_F0_repo_init  Exist! "
	        ## 系统 ~/Desktop/zbin/lin_zbin/F0_repo_init.sh 路径 文件存在 , 那么复制到 AOSP 根目录
            if [ ! -f "$cur_F0_repo_init" ]; then
                   echo "Raw File $cur_F0_repo_init  Not Exist! "
                   cp $raw_F0_repo_init   $cur_F0_repo_init
                   echo " Cur File $cur_F0_repo_init  Copy Operation Finish ! "
                    
               else   
                   echo "Cur File $cur_F0_repo_init Exist! "
            fi
            
    fi
## ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧



## ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤  获取 AOSP的  每个 repo  ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤
echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "zrepo_reset_dir_path="$zrepo_reset_dir_path
echo "try execute  [ repo forall -c pwd ] please waitting! "
## 获取所有的 AOSP的repo的 路径到 repo_pwd.txt
repo forall -c pwd > ./repo_pwd.txt
#repo forall -c git clean -dxf

## 当前遍历到的 repo_pwd.txt 的 行数
line_number=1

## 获取 repo_pwd.txt 所有的行  既 当前 AOSP的 所有的 repo仓库的 个数
all_number=`grep -n  ""   ./repo_pwd.txt | wc -l`

## ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧



## ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤  遍历 AOSP的  每个 repo  ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤
echo "════════════════════════════════════════""["$all_number"]"" Git Reset Begin ════════════════════════════════════════"

## 遍历  repo_pwd.txt 中的每一行 既 对应的 repo仓库的路径
for line in `cat ./repo_pwd.txt`
do
 cd $line

## 打印 git status 信息
git_status_desc=`git status`

## 查看git status 是否有本地的提交commit 
git_status_commits=`git status | grep "commits"`  

## 查看git status 是否有需要git pull 更新
git_status_git_pull=`git status | grep "git pull"`  

## 查看git status 是否有Your branch is behind 字样
git_status_branch_behind=`git status | grep "Your branch is behind"`  



## 查看git status 是否有release打印 这样的仓库无需更新  
git_status_release=`git status | grep "release"`

## 查看git status 是否有Untracked files打印 既新增未提交的文件
git_status_untracked=`git status | grep "Untracked files"`

## git branch -r | grep " -"  打印所有的远程分支并查找关键单词 " -"  输出 m/ms -> origin/bs-qc 主分支
## `git branch -r | grep " -" |awk -F' ' '{ print $3 }'`  输出 origin/bs-qc    awk空格分割打印第三列字符串
## 当前 git branch -r 输出列表中 带 箭头符号 -> 的分支是主分支 
git_master_branch=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`

## 如果 没有 主分支 没有箭头的话   那么 通过 git branch -r |awk 'NR==1' 命令查询第一个分支
## git branch -r |awk 'NR==1'  输出 m/mt
git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`

echo -e "\n ═══════════════════"$line_number"[Begin]══════════════════"$line_number"[Begin]  index=["$line_number"]["$all_number"]"  "pwd="$line  "═════════════════════════════════════"


echo "git_status_desc="$git_status_desc
echo "git_status_commits="$git_status_commits
echo "git_status_release="$git_status_release
echo "git_status_untracked="$git_status_untracked
echo "git_master_branch="$git_master_branch
echo "git_nomaster_number1_branch="$git_nomaster_number1_branch
echo "git_status_git_pull="$git_status_git_pull
echo "git_status_branch_behind="$git_status_branch_behind


#git checkout TEMP
#git clean -dxf


## 如果主分支 不为空  那么重置到主分支

echo -e "╤╤╤╤╤╤╤[1] git_master_branch=true reset begin ["$line_number"]["$all_number"] git reset --hard" $git_master_branch "  ╤╤╤╤╤╤╤" 
echo -e "git_master_branch="$git_master_branch
if [ -n "$git_master_branch" ]; then
##  重置到主分支  既   git reset --hard origin/bs-qc
echo  reset --hard $git_master_branch
git reset --hard $git_master_branch
fi
echo -e "╧╧╧╧╧╧╧[1] git_master_branch=true reset end  ["$line_number"]["$all_number"] git reset --hard" $git_master_branch "  ╧╧╧╧╧╧╧" 



## 主分支为空  远程第一分支 &&  没有release 信息    才主动切换到第一分支
echo -e "╤╤╤╤╤╤╤[2] git_master_branch=false && git_nomaster_number1_branch=true && git_status_release=false reset begin ["$line_number"]["$all_number"] git reset --hard" $git_nomaster_number1_branch "  ╤╤╤╤╤╤╤" 
## 如果 没有 主分支 没有箭头的话   那么 通过 git branch -r |awk 'NR==1' 命令查询第一个分支
## git branch -r |awk 'NR==1'  输出 m/mt
echo "git_master_branch="$git_master_branch
echo "git_nomaster_number1_branch="$git_nomaster_number1_branch
echo "git_status_release="$git_status_release

if [ -z "$git_master_branch" ]; then
git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`
## 如果没有查询到主分支 但 查询到第一个 分支的话 那么重置到第一个分支
if [ -n "$git_nomaster_number1_branch" ]; then
##  没有查询到主分支 重置到查询到的第一个分支   git reset --hard m/mt
    if [ -z "$git_status_release" ]; then
        echo  reset --hard $git_nomaster_number1_branch
        git reset --hard $git_nomaster_number1_branch
	fi
fi
fi
echo -e "╧╧╧╧╧╧╧[2]  git_master_branch=false && git_nomaster_number1_branch=true && git_status_release=false reset end  ["$line_number"]["$all_number"] git reset --hard" $git_nomaster_number1_branch "  ╧╧╧╧╧╧╧" 



## 如果 git_status_commits  有提示信息的话
if [ -n "$git_status_commits" ]; then
    ##  如果当前 有 未追踪的新增文件时 
    if [ -n "$git_status_untracked" ]; then
	        echo -e "╤╤╤╤╤╤╤[3] git_status_commits=true && git_status_untracked=true begin   ["$line_number"]["$all_number"]   ╤╤╤╤╤╤╤" 
             echo "git_status_untracked="$git_status_untracked
			 echo "git_status_commits="$git_status_commits
             echo "cur_path="$line " try to remove the untracked and git checkout .   "
             echo "cd "$line " && " " &&  git checkout . "
             #git checkout TEMP
             #git clean -dxf
             # rm -fr ./ 
             #git checkout .
			echo -e "╧╧╧╧╧╧╧[3] git_status_commits=true && git_status_untracked=true begin   ["$line_number"]["$all_number"]   ╧╧╧╧╧╧╧" 

    fi

    ## 如果当前的 git status 中 不包含 release 信息时 
    if [ -z "$git_status_release" ]; then
	        echo -e "╤╤╤╤╤╤╤[4] git_status_commits=true && git_status_release=false  begin   ["$line_number"]["$all_number"] pwd="$line"   ╤╤╤╤╤╤╤" 
			 echo "git_status_commits="$git_status_commits
			 echo "git_status_release="$git_status_release
            echo "cur_path="$line " try to execute  <git reset --hard HEAD~5>  <repo sync .> "
            #git checkout TEMP
            #git clean -dxf
            #git reset --hard HEAD~5        xxx
            #git reset --hard origin/master  xxx
            #repo sync .
            #git pull
	        echo -e "╧╧╧╧╧╧╧[4] git_status_commits=true && git_status_release=false  endxx   ["$line_number"]["$all_number"] pwd="$line"   ╧╧╧╧╧╧╧" 

        else
		  echo -e "╤╤╤╤╤╤╤[5] git_status_commits=true && git_status_release=true  begin   ["$line_number"]["$all_number"] pwd="$line"   ╤╤╤╤╤╤╤" 
			 echo "git_status_commits="$git_status_commits
			 echo "git_status_release="$git_status_release
		  ## 如果当前的 git status 中 包含 release 信息时 
          echo "cd "$line " && " "git status "
	      echo -e "╧╧╧╧╧╧╧[5] git_status_commits=true && git_status_release=true  endxx   ["$line_number"]["$all_number"] pwd="$line"   ╧╧╧╧╧╧╧" 
    fi
  else
      ## git_status_commits 没有 commits的信息时 
	   echo -e "╤╤╤╤╤╤╤[6] git_status_commits=false   begin   ["$line_number"]["$all_number"] pwd="$line"   ╤╤╤╤╤╤╤" 

	   echo -e "╧╧╧╧╧╧╧[6] git_status_commits=false   endxx   ["$line_number"]["$all_number"] pwd="$line"   ╧╧╧╧╧╧╧" 
fi



if [ -n "$git_status_git_pull" ]; then
  if [ -n "$git_status_branch_behind" ]; then
    if [ -n "$git_status_release" ]; then
		echo -e "╤╤╤╤╤╤╤[7] git_status_git_pull=true && git_status_branch_behind=true && git_status_release=true    begin   ["$line_number"]["$all_number"] pwd="$line"   ╤╤╤╤╤╤╤" 

	    echo "git_status_git_pull="$git_status_git_pull
	    echo "git_status_release="$git_status_release
	    echo "git_status_branch_behind="$git_status_branch_behind
		echo git pull 
		git pull
	    echo -e "╧╧╧╧╧╧╧[7] git_status_git_pull=true && git_status_branch_behind=true && git_status_release=true  endxx   ["$line_number"]["$all_number"] pwd="$line"   ╧╧╧╧╧╧╧" 

    fi
  fi
fi



echo -e "\n ═══════════════════"$line_number"[End]══════════════════"$line_number"[End]  index=["$line_number"]["$all_number"]"  "pwd="$line  "═════════════════════════════════════"
echo -e "\n"
echo -e "\n"
echo -e "\n"
echo -e "\n"
let line_number=line_number+1


done
## ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧





## 返回 AOSP 主路径
cd $CUR_REPO_PATH

echo "════════════════════════════════════════""["$all_number"]"" Git Repo Reset Finish ════════════════════════════════════════"
echo "════════════════════════════ 1.   mv out out_1 "
date_time=`date +%Y%m%d_%H%M%S`
echo "date_time=$date_time"
## mv ./out  ./out_"$date_time"
## mv ./kernel_platform/out ./kernel_platform/out_"$date_time"
## mv ./release  ./release_"$date_time"
echo "════════════════════════════ 2.   repo sync -j2  "
#repo sync -j2
echo "════════════════════════════  repo sync -j2 finish  ════════════════════════════  "


echo "════════════════════════════3.  F0_repo_init  Cherry-Pick Operation Begin  ════════════════════════════  "
#raw_zrepo_reset="$HOME/Desktop/zbin/lin_zbin/zrepo_rest.sh"
#source $cur_F0_repo_init
if [ $? -eq 0 ]; then
    echo "_____ $cur_F0_repo_init  build success _____raw_zrepo_reset=$raw_zrepo_reset "
else
    echo "_____ $cur_F0_repo_init build failed ____rereset_____raw_zrepo_reset=$raw_zrepo_reset ___ "
#$raw_zrepo_reset
fi







