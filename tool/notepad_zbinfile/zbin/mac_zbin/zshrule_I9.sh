#!/bin/bash
# zshrule_I9.sh
CURPATH=$(pwd)
CUR_OS_UNAME=$(uname -a)


echo "╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤ Env Begin ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤"
env
echo "╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧ Env Endxx ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧"
echo
echo


echo "╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤ PATH Begin ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤"
init_path=$PATH
echo "init_path="$init_path
echo "╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧ PATH Endxx ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧"
echo
echo

echo "╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤ Input_Var Begin ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤"
init_os_name=$(uname -a)     # 当前操作系统的简介 Darwin Mac-mini.local 22.3.0 Darwin Kernel Version 22.3.0
init_cd=$(pwd)     #  当前执行命令的路径   init_cd=/Users/zukgit/Deskto
init_shfile_path=$(dirname "$0")    #  当前执行文件自身的文件夹路径    # init_shfile_path=/Users/zukgit/Desktop/zbin/mac_zbin
init_shfile_name=$(basename "$0")   # 当前执行文件的文件名称  init_shfile_name=zshrule_I9.sh
init_input_0=$0                     # init_input_0=/Users/zukgit/Desktop/zbin/mac_zbin/zshrule_I9.sh
init_input_1=$1
init_input_2=$2
init_input_3=$3
init_input_4=$4
init_input_5=$5
init_input_6=$6
init_input_7=$7
init_input_8=$8
init_input_9=$9
init_f0=$0      
echo "init_os_name="$init_os_name
echo "init_cd="$init_cd
echo "init_input_0="$init_input_0
echo "init_input_1="$init_input_1
echo "init_input_2="$init_input_2
echo "init_input_3="$init_input_3
echo "init_input_4="$init_input_4
echo "init_input_5="$init_input_5
echo "init_input_6="$init_input_6
echo "init_input_7="$init_input_7
echo "init_input_8="$init_input_8
echo "init_input_9="$init_input_9
echo "init_shfile_path="$init_shfile_path
echo "init_shfile_name="$init_shfile_name
echo "init_f0="$init_f0
echo "╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧ Input_Var Endxx ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧"
echo
echo



echo "╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤ Absolute Path Begin ╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤"
init_userprofile=$HOME
init_desktop=$HOME/Desktop
desktop=$HOME/Desktop
init_zbin=$init_desktop/zbin
zbin=$init_desktop/zbin
init_mac_zbin=$init_zbin/mac_zbin
echo "init_userprofile="$init_userprofile
echo "desktop="$desktop
echo "init_desktop="$init_desktop
echo "zbin="$zbin
echo "init_zbin="$init_zbin
echo "init_mac_zbin="$init_mac_zbin
echo "╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧ Absolute Path Endxx ╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧"
echo
echo


##  ═══════════════════════════ Function Define Area Begin ═══════════════════════════

# **************************************   Method Define Area  Begin *************************************
function isstartwith_func_2x1(){
# =========================================================================== isstartwith_func_2x1
# desc: 检测当前第一个参数字符串是否是以 第二个参数的字符串为起始  忽略引号
# sample:  isstartwith_func_2x1  1234  12
# sample_out: 

echo "__________________________________Method_In "$FUNCNAME

echo "__________________________________Method_Out "$FUNCNAME
}

function pause_get_char(){
SAVEDSTTY=`stty -g`

#隐藏终端输入显示
stty -echo
stty cbreak

#dd等待用户按键 bs(block size)块大小=1,count总数=1，作用只取一个字符, 2> /dev/null 不显示任何信息
dd if=/dev/tty bs=1 count=1 2> /dev/null
stty -raw

#恢复终端显示
stty echo
stty $SAVEDSTTY
}


function pause(){
echo
echo "Press any key to start...or Press Ctrl+c to cancel  按任意键继续! ....."
char=`pause_get_char`
echo "任意键结束!"
}



# **************************************   Method Define Area  Endxx *************************************

function rule0v_func_0x0(){
# =========================================================================== rule0v_func_0x0
# rule_tip:  $init_shfile_name  _0_   ##   当前是 Rule方法的模板  方便复制
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
echo "__________________________________Method_Out "$FUNCNAME
}


echo $init_shfile_name" _27_  ip_10.106.20.115 ipport_54321 paircode_654321 pairport_54321   ## 本地无线 wireless adb 连接 需要打开 Android10以上 开发者模式无线调试"

function rule27vwirelessadbconnect_func_0x0(){
# =========================================================================== rule0v_func_0x0
# rule_tip:  $init_shfile_name  _27_    ip_10.106.20.115 ipport_54321 paircode_654321 pairport_54321    ##   本地无线 wireless adb 连接 需要打开 Android10以上 开发者模式无线调试
# rule_tip:  $init_shfile_name  _27_    ip_192.106.0.2   ipport_54321  paircode_654321 pairport_54321    ##   本地无线 wireless adb 连接 需要打开 Android10以上 开发者模式无线调试
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
rule27_param1=$init_input_2
rule27_param2=$init_input_3
rule27_param3=$init_input_4
rule27_param4=$init_input_5
echo "rule27_param1="$rule27_param1"  rule27_param2="$rule27_param2"  rule27_param3="$rule27_param3"  rule27_param4="$rule27_param4
rule27_ip=${rule27_param1:3}      #  去除 前面三个 字符串     ip_                   ip_192.168.0.2
rule27_ipport=${rule27_param2:7}  #  去除 前面七个 字符串     ipport_            ipport_54321
rule27_paircode=${rule27_param3:9}  #  去除 前面九个 字符串 paircode_         paircode_654321
rule27_pairport=${rule27_param4:9}  #  去除 前面九个 字符串     pairport_         pairport_54321


echo "rule27_ip="$rule27_ip"  rule27_ipport="$rule27_ipport"  rule27_paircode="$rule27_paircode"  rule27_pairport="$rule27_pairport

rule27_wireless_command="adb pair "$rule27_ip":"$rule27_pairport" "$rule27_paircode" && adb connect "$rule27_ip":"$rule27_ipport" && adb -s "$rule27_ip":"$rule27_ipport" shell"

echo "########################## adb-wireless 无线连接配对 adb命令 ########################## "
echo $rule27_wireless_command
echo 
echo 
echo "########################## 进入Shell命令  无线 Wireless adb命令   ##########################"
echo " adb -s "$rule27_ip":"$rule27_ipport" shell"
echo 
echo 
# adb pair $rule27_ip:$rule27_pairport $rule27_paircode && adb connect $rule27_ip:$rule27_ipport && adb -s $rule27_ip:$rule27_ipport shell"

echo "__________________________________Method_Out "$FUNCNAME
}


# **************************************   Rule Define Area  Begin *************************************
function rule14vtakevideo_func_0x0(){
# =========================================================================== rule14vtakevideo_func_0x0
# rule_tip:  $init_shfile_name  _14_   ##   对当前手机屏幕进行录屏 然后拔出 并重新插入 使得mp4 文件导出到PC本地
# desc: 对当前手机屏幕进行录屏  然后拔出usb  并重新插入 使得mp4 文件导出到PC本地
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
adb wait-for-device
adb root
adb remount
rule14_timestamp=`date +%Y-%m-%d_%H-%M-%S`  ##  获取到时间戳 2023-03-21_16-33-13 
echo "创建文件夹  mp4_"$rule14_timestamp
rule14_mp4_dir="mp4_"$rule14_timestamp
rule14_mp4_name="rule14_"$rule14_timestamp".mp4"
mkdir $rule14_mp4_dir

echo "正在进行录屏操作, 如果想停止 请拔掉 USB 断开后  重新插入USB 使得 导出 当前 mp4文件: "$rule14_mp4_name"  rule14_mp4_dir="$rule14_mp4_dir

adb shell screenrecord --bit-rate 4000000   /sdcard/Pictures/$rule14_mp4_name
echo "当前拔出了  USB 停止 录屏  请重新插入USB 使得导出 mp4文件: "$rule14_mp4_name"到本地!"
echo "当前拔出了  USB 停止 录屏  请重新插入USB 使得导出 mp4文件: "$rule14_mp4_name"到本地!"
adb wait-for-device
adb root
adb remount
adb pull /sdcard/Pictures/$rule14_mp4_name   ./$rule14_mp4_dir/
echo "当前已经导出文件到本地PC mp4:  ./"$rule14_mp4_dir/$rule14_mp4_name
echo "Press any key to start...or Press Ctrl+c to cancel  按任意键继续对手机进行录屏并导出到本地! ....."
char=`pause_get_char`
rule14vtakevideo_func_0x0

echo "__________________________________Method_Out "$FUNCNAME
}




function rule13vtakepicture_func_0x0(){
# =========================================================================== rule13vtakepicture_func_0x0
# rule_tip:  $init_shfile_name  _13_   ##   对当前 adb 连接手机进行屏幕截屏并拉取到PC本地
# desc:  对adb 连接着的手机进行 截屏  然后导出到本地 文件名称类似于 B7_zscreenshot_
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
rule13_timestamp=`date +%Y-%m-%d_%H-%M-%S`       ##  获取到时间戳 2023-03-21_16-33-13 
echo "时间戳 rule13_timestamp="$rule13_timestamp
echo "执行截图命令如下:"
echo "adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_"$rule13_timestamp".png"

adb shell screencap -p /sdcard/Pictures/B7_zscreenshot_$rule13_timestamp.png

echo "拉取文件 /sdcard/Pictures/B7_zscreenshot_"$rule13_timestamp".png 到本地"
echo "拉取命令:  adb pull /sdcard/Pictures/B7_zscreenshot_$rule13_timestamp.png ./B7_zscreenshot_$rule13_timestamp.png"
adb pull /sdcard/Pictures/B7_zscreenshot_$rule13_timestamp.png ./B7_zscreenshot_$rule13_timestamp.png
echo "Press any key to start...or Press Ctrl+c to cancel  按任意键继续对手机进行截屏并导出到本地! ....."
char=`pause_get_char`
rule13vtakepicture_func_0x0

echo "__________________________________Method_Out "$FUNCNAME
}




function rule6vclearapk_func_0x0(){
# =========================================================================== rule6vclearapk_func_0x0
# rule_tip:  $init_shfile_name  _6_   ##   执行批量卸载清空当前手机的三方 3rd apk的操作
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME

adb shell pm list package -3 -f > sh_rule6.txt
sh_rule6_linecout=`awk 'END{print NR}' sh_rule3.txt`    ## 统计文件行数
rule6_apk_index=1
## sh_rule3[111]_package_apk_prepath:package:/data/app/~~JpEjflXC8tmQSmOb9yRQqA==/com.samsundot.talking-mL_Xcmvxp_CGuf3vB3LTbA==/base.apk=com.samsundot.talking
    for package_line in `cat sh_rule6.txt`
    do 
        echo "sh_rule6["$rule6_apk_index"]:${package_line}"
		package_apk_name=${package_line#*==/base.apk=}   ##  #井号截取  ==/base.apk= 右边字符串 后面

        echo "sh_rule6["$rule6_apk_index"]:${package_apk_name}"
        echo "sh_rule6["$rule6_apk_index"]:${package_apk_name} 卸载命令如下:"
        echo "adb uninstall "$package_apk_name
		adb uninstall $package_apk_name
		rule6_apk_index=$(($rule6_apk_index+1))
		echo 
    done
rm -fr  sh_rule3.txt
echo "__________________________________Method_Out "$FUNCNAME



echo "__________________________________Method_Out "$FUNCNAME
}




function rule3vinstalldirapk_func_0x0(){
# =========================================================================== rule3vinstalldirapk_func_0x0
# rule_tip:  $init_shfile_name  _3_   ##   安装当前目录下的apk 手机adb连接的安卓手机
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
rule3_dir_path=$init_cd
echo "当前安装 apk 的 本地路径 rule3_dir_path="$rule3_dir_path
sh_rule3_fileindex=1
sh_rule3_apkcount=0
    for real_file_path in `find ${rule3_dir_path} -type f -name "*.apk"`; do  ## 过滤当前文件夹下的apk 结尾的文件
	    # rule3_realfile[2]: /Users/zukgit/Desktop/zbin/mac_zbin/apk/com.samsundot.talking.apk
        echo "rule3_realfile["$sh_rule3_fileindex"]: "$real_file_path
        sh_rule3_apkcount=$(($sh_rule3_apkcount+1))
        sh_rule3_fileindex=$(($sh_rule3_fileindex+1))
    done
sh_rule3_apkindex=1
	for real_file_path in `find ${rule3_dir_path} -type f -name "*.apk"`; do  ## 过滤当前文件夹下的apk 结尾的文件
	    # rule3_realfile[2]: /Users/zukgit/Desktop/zbin/mac_zbin/apk/com.samsundot.talking.apk
        echo "安装apk rule3_realfile["$sh_rule3_apkindex"_"$sh_rule3_apkcount"]: "$real_file_path"命令:"
		echo "adb install -r "$real_file_path
		adb install -r $real_file_path      ## 开始安装apk
        sh_rule3_apkindex=$(($sh_rule3_apkindex+1))
    done
	
echo "__________________________________Method_Out "$FUNCNAME
}





function rule2vbankupapk_func_0x0(){
# =========================================================================== rule2vbankupapk_func_0x0
# rule_tip:  $init_shfile_name  _2_   ## 获取当前安卓手机前台的apk到PC本地目录
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME

# mCurrentFocus=Window{826bd27 u0 com.ss.android.article.news/com.bytedance.ugc.ugcfeed.innerfeed.PostInnerFeedActivity}
adb shell dumpsys window | grep "mCurrentFocus=Window" > sh_rule2.txt
sh_rule2_linecout=`awk 'END{print NR}' sh_rule2.txt`
sh_rule2_lineindex=1
sh_rule2_seperat_line_str="/"
    for package_line in `cat sh_rule2.txt`
    do 
        echo "sh_rule2["$sh_rule2_lineindex"]:${package_line}"
		if [[ ${package_line} =~ ${sh_rule2_seperat_line_str} ]];then      ##  包含子字符串
		echo "当前行["$sh_rule2_lineindex"_"$sh_rule2_linecout"]包含应用名称: "${package_line}
		forground_apk_name=${package_line%/*}
		echo "当前行["$sh_rule2_lineindex"_"$sh_rule2_linecout"]前台应用名称 forground_apk_name="${forground_apk_name}
		
		adb shell pm list package -3 -f  | grep ${forground_apk_name} > sh_rule2_foreground_app.txt
		
		sh_rule2_forground_app_lineindex=1
		sh_rule2_forground_app_flag_str="base.apk"
		## package:/data/app/com.ss.android.article.news-LIGZLzy_kSW_08CkcqgxQQ==/base.apk=com.ss.android.article.news
            for foreground_app_line in `cat sh_rule2_foreground_app.txt`
		    do
			
				if [[ ${foreground_app_line} =~ ${sh_rule2_forground_app_flag_str} ]];then      ##  包含子字符串
                     echo "foreground_app_line["$sh_rule2_forground_app_lineindex"]:${foreground_app_line}"
				     package_apk_prepath=${foreground_app_line%==/base.apk*}  ##  %百分号号截取 左边字符串 前面
				     package_apk_endpath=${foreground_app_line#*==/base.apk=}   ##  #井号截取 右边字符串 后面
				     package_apk_path=${package_apk_prepath:8}"==/base.apk"    ## 去除前面的8个字符  package:
		             package_apk_name=${package_apk_endpath}".apk"
				     echo "当前前台APP["$package_apk_name"]拉取命令:    adb pull "${package_apk_path}"  " ${package_apk_name}
		             adb pull ${package_apk_path}   ${package_apk_name}
			         sh_rule2_forground_app_lineindex=$(($sh_rule2_forground_app_lineindex+1))
				     rm -fr sh_rule2_foreground_app.txt
				     rm -fr sh_rule2.txt
				     echo "__________________________________Method_Out "$FUNCNAME
				     echo "Press any key to start...or Press Ctrl+c to cancel  按任意键继续抓取当前前台APP! ....."
                     char=`pause_get_char`
					 rule2vbankupapk_func_0x0
               fi
		    done
		fi
		
		sh_rule2_lineindex=$(($sh_rule2_lineindex+1))
    done
rm -fr sh_rule2_foreground_app.txt
rm -fr sh_rule2.txt
echo "当前 安卓 手机 必须 亮屏 && 当前的前台APK 是三方apk  请检查后执行!!!!"
echo "Press any key to start...or Press Ctrl+c to cancel  按任意键继续抓取当前前台APP! ....Ctrl+C 停止当前程序!"
char=`pause_get_char`
rule2vbankupapk_func_0x0
echo "__________________________________Method_Out "$FUNCNAME
}





function rule1vbankupapk_func_0x0(){
# =========================================================================== rule1vbankupapk_func_0x0
# rule_tip:  $init_shfile_name  _1_   ## 备份bankup当前安卓手机安装的三方apk到PC本地目录
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
echo "bankup 当前连接adb的手机中的三方apk"
rule1_apk_index=1
adb shell pm list package -3 -f > sh_rule1.txt
sh_rule1_linecout=`awk 'END{print NR}' sh_rule1.txt`    ## 统计文件行数

## sh_rule1[111]_package_apk_prepath:package:/data/app/~~JpEjflXC8tmQSmOb9yRQqA==/com.samsundot.talking-mL_Xcmvxp_CGuf3vB3LTbA==/base.apk=com.samsundot.talking
    for package_line in `cat sh_rule1.txt`
    do 
        echo "sh_rule1["$rule1_apk_index"]:${package_line}"
		package_apk_prepath=${package_line%==/base.apk*}    ##  %百分号号截取 左边字符串 前面
		package_apk_endpath=${package_line#*==/base.apk=}   ##  #井号截取 右边字符串 后面
        echo "sh_rule1["$rule1_apk_index"]_package_apk_prepath:${package_apk_prepath}"
        echo "sh_rule1["$rule1_apk_index"]_package_apk_endpath:${package_apk_endpath}"
		package_apk_path=${package_apk_prepath:8}"==/base.apk"    ## 去除前面的8个字符  package:
		package_apk_name=${package_apk_endpath}".apk"
		echo "sh_rule1["$rule1_apk_index"]_apkpath:${package_apk_path}"
        echo "sh_rule1["$rule1_apk_index"]_apkname:${package_apk_name}"
		echo "拉取 ${package_apk_name} 的命令如下:"
		echo "app["$rule1_apk_index"_"$sh_rule1_linecout"]: adb pull "${package_apk_path}"  " ${package_apk_name}
		adb pull ${package_apk_path}   ${package_apk_name}
		echo 
		rule1_apk_index=$(($rule1_apk_index+1))
    done
rm -fr  sh_rule1.txt
echo "__________________________________Method_Out "$FUNCNAME
}




# **************************************   Rule Define Area  Endxx *************************************


##  ════════════════════Main Function Begin ═══════════════════════════

function ruletipprint_func_0x0(){
# =========================================================================== ruletipprint_func_0x0
echo "__________________________________Method_In "$FUNCNAME
echo
echo $init_shfile_name" _1_    ## 备份bankup当前安卓手机安装的三方apk到PC本地目录"
echo
echo $init_shfile_name" _2_    ## 获取当前正在运行的前台安卓应用到PC本地目录"
echo
echo $init_shfile_name" _3_    ## 安装当前目录下的apk到手机中"
echo
echo $init_shfile_name" _6_    ## 执行批量清空卸载手机安装三方app的操作"
echo
echo $init_shfile_name" _13_    ## 对当前 adb 连接手机进行屏幕截屏并拉取到PC本地"
echo
echo $init_shfile_name" _14_    ## 对当前手机屏幕进行录屏 然后拔出 并重新插入 使得mp4 文件导出到PC本地"
echo
echo $init_shfile_name" _27_  ip_10.106.20.115 ipport_54321 paircode_654321 pairport_54321   ## 本地无线 wireless adb 连接 需要打开 Android10以上 开发者模式无线调试"
echo $init_shfile_name" _27_  ip_192.168.0.2 ipport_54321 paircode_654321 pairport_54321   ## 本地无线 wireless adb 连接 需要打开 Android10以上 开发者模式无线调试"

echo

echo "__________________________________Method_Out "$FUNCNAME
}



function ruletipanalysis_func_0x1(){
# =========================================================================== ruletipanalysis_func_0x1
# desc:
# sample:
# sample_out:
echo "__________________________________Method_In "$FUNCNAME
if [ ! -n "$init_input_1" ] ; then
    echo "没有任何的 _RuleIndex_ 输入 将打印 RuleTip"
	ruletipprint_func_0x0
	echo "__________________________________Method_Out "$FUNCNAME
	exit 
fi
	ruletipanalysis_return_1=""
    real_rule_index=${init_input_1:1}        ### 输入 _123_  去除第一个字符 _ 变为  123_
	real_rule_index=${real_rule_index%?}     ### 去除最后一个字符    从 123_ 变为123
    echo "用户当前输入的 RuleIndex ="$init_input_1
    echo "real_rule_index="$real_rule_index
	fliter_rule_str="^function.rule"$real_rule_index"v*"
	echo "fliter_rule_str="$fliter_rule_str
	cat $init_f0 | grep $fliter_rule_str  > zzZZzz.txt     ##  过滤当前的方法找到那个匹配好的 ruleXfunc名称
	
	select_method_name="rule"$real_rule_index"v"
	echo select_method_name=$select_method_name

	line_index=1
	is_match_rule_name=false
    for line in `cat zzZZzz.txt`
    do 
        echo "FileLine["$line_index"]:${line}"
		if [[ ${line} =~ ^${select_method_name}.* ]] ; then   ##  判断是否是以 RuleName 开头 ruleXv开头
		    is_match_rule_name=true
			match_rule_method_name=${line}
			real_rule_method_name=${match_rule_method_name%???}   ## 去除末尾的 (){ 总共三个字符
			echo "从文件匹配到了 IndexRule函数 match_rule_method_name=${match_rule_method_name}"
			echo "从文件匹配到了 IndexRule函数 real_rule_method_name=${real_rule_method_name}"
			ruletipanalysis_return_1=${real_rule_method_name}
			echo "__________________________________执行选中方法 【  "$ruletipanalysis_return_1"】________________________________"
			$ruletipanalysis_return_1    ## 执行选中方法
			rm -fr zzZZzz.txt
			echo "["$FUNCNAME" EndPrint] ruletipanalysis_return_1="$ruletipanalysis_return_1"  param1=[__empty__]"
			echo "__________________________________Method_Out "$FUNCNAME
			return $ruletipanalysis_return_1
		fi
		line_index=$(($line_index+1))
    done
rm -fr zzZZzz.txt
if [[ "$is_match_rule_name" == false ]]; then
    echo "在当前 zshrule_I9.sh 文件中没有搜索到对应RuleIndex的方法  init_input_1="$init_input_1"  select_method_name="$select_method_name
	ruletipprint_func_0x0
fi

echo "["$FUNCNAME" EndPrint] ruletipanalysis_return_1="$ruletipanalysis_return_1"  param1=[__empty__]"
echo "__________________________________Method_Out "$FUNCNAME
return $ruletipanalysis_return_1
}



function __Main__(){
# =========================================================================== __Main__
echo "__________________________________Method_In "$FUNCNAME
echo "init_desktop="$init_desktop
echo "zbin="$zbin
echo "init_zbin="$init_zbin
echo "__________________________________Method_Out "$FUNCNAME
}
##  ════════════════════Main Function End ═══════════════════════════
##  ═══════════════════════════ Function Define Area Endxx ═══════════════════════════
ruletipanalysis_func_0x1
__Main__
exit
