
## shell操作demo1

```
#!/bin/bash
who
date
echo "hello every"
echo $HOME
var1="zukgit"       #字符串赋值变量
echo ${var1}        # 打印字符串变量
echo "my name is ${var1}" #字符串相加
var2=`date +%Y年%m月%d日%H:%M:%S` #执行字符串命令
echo "${var2} is today"

var3=15
var4=$[$var3 - 10]    ## 数值的运算
echo $var4

var5=`date +%S`         ## 获取到当前的秒数数值
echo "var5 = ${var5}"
var6=`expr  ${var5} + 0`    ## expr是一个计算器   +0 可实现把字符串转为整数
echo "var6 = $var6"
if [ $var6 -ge 30 ]  ## -eq等于  -ge大于等于 -gt大于 -le小于等于 -lt小于 -ne 不等于
then 
 echo "opera var6:  $[$var6 +1] 字符串内完成数值加减操作 秒数大于30"
else
 echo "var6 = $var6 秒数小于 30"
fi

if (( $var6 + 30 > 60 )) ##双圆括号内部可进行数字赋值并判断    
then echo "var6 在30秒以下"
else echo "var6 在30秒以上"
fi  

var7="abc"
var8="aaa"

if [ $var7 = $var8 ]   ## 检查字符串是否相等 
then echo "var7 == var8  var7=var8=$var7"
else echo "var != var8 var7=$var7 var8=$var8"
fi

var9=""
var10="ABCDEF"
if [ -z $var9 ] ##
then echo "var9 length = 0"
fi



if [ -z $var10 ] ## 检查长度是否为0  字符串长度是否为0
then echo "var10 length = 0"
else echo "var10 length > 0"
fi
 
if [ -e $HOME/shell.txt ] ##判断文件是否存在  -f 单独用于判断文件是否存在
then echo " $HOME/shell.txt is exist"
else echo " $HOME/shell.txt not exist"
fi
# -r    检查文件是否存在并可读   -w可写     -x可执行 
if [ -e $HOME/shelltest ] ## -d 单独判断文件夹是否存在  -e 检查文件 或文件夹是否存在
then echo " $HOME/shelltest dir is exist"
else echo "$HOME/shelltest dir not exist"
fi

var11="1"
case $var11 in   ##case语句相当于 switch(value){ case1:  case2:} 
"1") echo "value = 1";;
"2") echo "value = 2";;
"3") echo "value = 3";;
*) echo "value is other";;
esac


for value_num in  1  2  3  4  5  6   7    ##for  遍历一个列表 
do  echo "today is $value_num"
done

list_1="A B C D E F"
list_1=$list_1" G"  ## 增加字符串
echo $list_1

for value_num1 in $list_1
do echo "the word is:$value_num1"
done

for value_2 in `ls / `   ## 使用命令返回值作为list 在for循环中输出
do echo "output is: $value_2"
done


for value_3 in /home/* /*  ##使用扩展通配符*  遍历多个文件夹 
do
  if [ -d "$value_3" ]       ##用引号包住变量名 是为了解决 路径中包含空格的情况
  then echo "$value_3 is a dir"
  elif [ -f "$value_3" ]
   then echo "$value_3 is a file"
  fi
done


##C 语言风格for循环    break 功能

for (( i=1; i<10; i++ ))
do 
if [ $i -eq 5 ]
 then  
 echo "use break in for test "
 break           ## 测试break   循环到5 跳出for循环
fi
   echo "the value number is： ${i}"
done



for ((i=0,j=0; j<10 && i<10; i++,j++ ))
do  echo " $i * $j = $[$i * $j]"
done 


varA=1
while [ $varA -le 10 ]   ## 为 true  执行
do echo "test while function numer is: $varA"
   varA=$[ $varA + 1 ]
done


varB=10
until [ $varB -eq 0 ]   ## 为false执行
do echo "test until func varB is: $varB"
   varB=$[ $varB -1 ]
done

for (( i=0; i<10; i++ ))
do 
  for (( j=0; j<10; j++ ))
   do 
      if [ $i -eq 5 ] && [ $j -eq 5 ]
        then echo "i=5  j=5  is special" 
         break  2      ## break 2 跳出两个循环    可以替换为 break 察看效果
      else  echo " i=$i j=$j"
      fi
   done 
done 


for (( i=0; i<10; i++ ))
do 
  for (( j=0; j<10; j++ ))
   do 
      if [ $i -eq $j ]
        then echo "i=j=$i  is special" 
         continue      ## 跳出这次循环 在j的下一次循环开始
      else  echo " i=$i j=$j"
      fi
   done 
done  > output.txt    ## 可以在done后面重定向输出内容到文件  而不是屏幕


```


## shell脚本demo2

```
1.getcode1.sh
====================================================================================
#!/bin/bash
keyValue=$(cat /zzj500G/bianhao)
echo "keyValue1: "${keyValue}
keyValue=`expr $keyValue + 1`
echo "keyValue2: "${keyValue}
echo "$keyValue" > /zzj500G/bianhao
var1=/zzj500G/
var2= helloworld_code
newPath="$var1""$keyValue""$var2"
echo "newPath: "${newPath}

for((i=0; i<${keyValue}; i++))
do
oldPath="$var1""${i}""$var2"
echo "oldPath"${i}":"${oldPath}
if [ -d "$oldPath" ];then
rm -fr "$oldPath"
break
fi
done

if [ -d "$newPath" ];then
rm -fr "$newPath"
mkdir "$newPath"
else
mkdir "$newPath"
fi
cd "$newPath"
repo init -u ssh://10.140.8.130/platform/manifest -b hw/wh/marshmallow/mtk/MT6737T_base --no-repo-verify --repo-branch=stable 
repo sync -c
#expect_init1.sh
#expect_sync2.sh
#repo start TEMP --all
#cd "$newPath"
#chmod 777 ./hq_build.sh
#expect_build3.sh
====================================================================================

2.buildcode2.sh
====================================================================================
#!/bin/bash
keyValue=$(cat /zzj500G/bianhao)
echo "path_source_build_number: "${keyValue}
var1=/zzj500G/
var2= helloworld_code
newPath="$var1""$keyValue""$var2"
cd "$newPath"


/usr/bin/expect <<-EOF
spawn   /zzj500G/sh/buildcode3.sh;
set timeout 10000
expect {
"accout:" {send “ABCDEFG\r”; exp_continue;}
"passwd:" {send "zzj7520254~\r"; exp_continue}
"sign success" {send "#######################\r";exp_continue}
}
interact
expect eof
EOF
sleep 10
echo "---------------------------- "
echo "============================ "
echo "---------------------------- "
echo "============================ "
====================================================================================


3.buildcode3.sh
====================================================================================

#!/bin/bash
keyValue=$(cat /zzj500G/bianhao)
echo "path_source_build_number: "${keyValue}
var1=/zzj500G/
var2= helloworld_code
newPath="$var1""$keyValue""$var2"
echo "path_source_build: "${newPath}
cd "$newPath"
#source build/envsetup.sh && project l03 && client default && lunch full_hq6737t_66_1ha_m0-eng
./hq_build.sh l03 default 23
====================================================================================



```





## expect交互脚本

```

#!/usr/bin/expect -f
spawn sudo su
expect "Password"      
send "abcdef\r"
interact


```

##  git提交脚本
```


#!/bin/sh
git add ./
git commit -m  `date +%Y年%m月%d日-%H时:%M分:%S秒_____Zukgit的提交`
git push origin master

```


##  别名设置

```
vim  /etc/bashrc
alias suz="/usr/local/sh_expect_work/suz.sh"
alias cmt="/usr/local/sh_expect_work/cmt.sh"
alias cdsh='cd /usr/local/sh_expect_work/'
alias cdgit='cd  /Users/aaa/Desktop/code_place/zzj_git/'


```