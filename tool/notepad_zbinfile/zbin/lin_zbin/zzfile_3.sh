#!/bin/bash
CURPATH=$(pwd)
CUR_OS_UNAME=$(uname -a)
echo "CUR_OS_UNAME="$CUR_OS_UNAME
MAC_FLAG="Darwin"
First_Param=$1
final_dir_path=${First_Param%/*}
echo "xxxxx"$1

## $0 是当前 zzfile 文件路径 $1 是第一个输入的参数
if [[ $CUR_OS_UNAME =~ $MAC_FLAG ]];then
    echo "MacOS 参数1="$1
   if [[ $1 =~ "./" ]];
   then
     fixed_path=${First_Param:1}
     cur_abs_path=$CURPATH$fixed_path
     echo "fixed_path ->"$fixed_path
     echo "打开指定目录 文件 ->"$cur_abs_path
     if [ -d $cur_abs_path ];
     then
     open $cur_abs_path
     else
      open $final_dir_path
     fi
     exit
   else
         if [ ${First_Param:0:1} == "/" ] ;
             then
                 if [ -d $First_Param ];
                  then
                     echo "打开文件夹 First_Param->"$First_Param
                     open $First_Param
                     exit
                 else
                     real_path=${First_Param%/*}
                     open $real_path
                     echo "打开绝对路径 real_path->"$real_path
                     echo "打开绝对路径 First_Param->"$First_Param
                     exit
                 
                 fi
 
             
         fi
         
   fi
   echo "打开当前目录"
   open $CURPATH
   exit
else
   echo "Linux"
   if [ $# -eq 0 ];
   then
     nautilus -s $CURPATH/1
     exit
   fi
      if [[ $1 =~ "./" ]];
      then
        fixed_path=${First_Param:1}
        test_dir=$CURPATH$fixed_path
        echo "CURPATH_fixed_path="$CURPATH$fixed_path
        nautilus -s $CURPATH$fixed_path
      exit
      fi

         if [ ${First_Param:0:1} == "/" ] ;
             then
        test_dir=$CURPATH$First_Param
                 if [ -d $test_dir ] || [ -f $test_dir ];
                  then
                       echo "test_dir="$test_dir
                       nautilus -s $test_dir/1
                fi
         fi

   nautilus -s $1/1
   exit
fi