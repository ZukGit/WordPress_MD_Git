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
init_cd=$(pwd)     #  当前执行命令的路径   init_cd=/Users/zukgit/Desktop
init_pwd=$(pwd)     #  当前执行命令的路径   init_cd=/Users/zukgit/Desktop
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

function rule904vfixreporevisionerror_func_0x0(){
# =========================================================================== rule901vrepobackuprecord_func_0x0
# rule_tip:  $init_shfile_name  _904_   ##  repo_fix_revision_error  对当前的 repo 仓库 执行失败 repo forall -c pwd 的那个路径git仓库执行更新 使得 repo forall -c pwd执行成功
# desc:  ManifestInvalidRevisionError  repo_fix_revision_error    修复 repo forall -c pwd 遇到的  ManifestInvalidRevisionError   当执行 repo forall -c pwd (_901_) 执行失败时出现的 ManifestInvalidRevisionError 修复  "
echo
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
CUR_REPO_PATH=$init_pwd   ## Vendor_xxx   Msi_xxx 开头
Code_Tag_Vendor_1="vendor"
Code_Tag_Msi_1="msi"
Code_Tag_Msi_2="system"
rule904_timestamp=`date +%Y-%m-%d_%H-%M-%S`  ##  获取到时间戳 2023-03-21_16-33-13 

REPO_BackUp_File=$CUR_REPO_PATH/vendor_RevisionError_$rule904_timestamp.txt
Code_Match_Vendor_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Vendor_1")
if [[ "$Code_Match_Vendor_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/vendor_RevisionError_$rule904_timestamp.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/msi_RevisionError_$rule904_timestamp.txt
fi

Code_Match_Msi_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Msi_1"  -ie "$Code_Tag_Msi_2")
if [[ "$Code_Match_Msi_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/msi_RevisionError_$rule904_timestamp.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/vendor_RevisionError_$rule904_timestamp.txt
fi

echo "Code_Match_Vendor_Result="$Code_Match_Vendor_Result
echo "Code_Match_Msi_Result="$Code_Match_Msi_Result
echo "REPO_BackUp_File="$REPO_BackUp_File

Repo_Check_Loop_Number=20;
Repo_Check_Loop_Index=1;


for i in $(seq 1 ${Repo_Check_Loop_Number})
do
         echo "_________ repo forall -c pwd ____Loop["$i"_"$Repo_Check_Loop_Number"]_________"
         
         repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log
         REPO_Git_Path_Check_Message=`cat repo_git_path_error_check.log | grep "Error:"`
         Manifest_InvalidRevision_Error_AOSP_FullPath=""
         Manifest_InvalidRevision_Error_AOSP_Path=""
         if [[ "$REPO_Git_Path_Check_Message" == "" ]] ; then
             echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
             echo " cat repo_git_path_error_check.log | grep "'Error:'"  is Epmty!! <<Fix Error>>   <<___Command Success___>>"
	         break
         else
             echo " REPO_Git_Path_Check_Message=$REPO_Git_Path_Check_Message "
             Manifest_InvalidRevision_Error_AOSP_Path=""
			 Manifest_InvalidRevision_Error_AOSP_FullPath=""
			 
			 
             #Project list error on project home/repo/dev/platform/android/platform/    external/sonivox:
             if [[ ${REPO_Git_Path_Check_Message} =~ "Project list error on project home/repo/dev/platform/android/platform/" ]] ; then 
                 Manifest_InvalidRevision_Error_AOSP_Path=${REPO_Git_Path_Check_Message#*Project list error on project home/repo/dev/platform/android/platform/}  
                 echo "1_1_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                 Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%:*}  
                 echo "1_2_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	             Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                 echo "1_2_1_Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"    
	         
                if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                         echo "Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path exist !  execute ( repo sync .)"
                         cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                         repo sync . 
                         cd $CUR_REPO_PATH
                         continue
                else 
                                                                          
                         Search_Manifest_Path_Info=`repo manifest | grep $Manifest_InvalidRevision_Error_AOSP_Path`
                         echo "1_3_Search_Manifest_Path_Info="$Search_Manifest_Path_Info
                         Manifest_InvalidRevision_Error_AOSP_Path=${Search_Manifest_Path_Info#*path=\"}  
                         echo "1_4_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                         Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%\"*}  
                         echo "1_5_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                     Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                         echo "1_6_Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath""___Exist__ "          
                            if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                               cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                               repo sync . 
                               cd $CUR_REPO_PATH
                               continue
                            else 

                               echo "1_7_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                         Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path" ___Not Exist ___"
	             
                            fi
                            
                    echo "1_8_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path not exist ! "    
                fi

		        continue
             fi
			 
			 
	         #Project list error on project home/repo/dev/platform/android/         oneplus/packages/apps/DemoMode-binary
             if [[ ${REPO_Git_Path_Check_Message} =~ "Project list error on project home/repo/dev/platform/android/" ]] ; then 
                 Manifest_InvalidRevision_Error_AOSP_Path=${REPO_Git_Path_Check_Message#*Project list error on project home/repo/dev/platform/android/}  
                 echo "2_1_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                 Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%:*}  
                 echo "2_2_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	             Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                 echo "Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"    
	         
                if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                         echo "Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path exist !  execute ( repo sync .)"
                         cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                         repo sync . 
                         cd $CUR_REPO_PATH
                         continue
                else 

                         
                         Search_Manifest_Path_Info=`repo manifest | grep $Manifest_InvalidRevision_Error_AOSP_Path`
                         echo "2_3_Search_Manifest_Path_Info="$Search_Manifest_Path_Info
                         Manifest_InvalidRevision_Error_AOSP_Path=${Search_Manifest_Path_Info#*path=\"}  
                         echo "2_4_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                         Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%\"*}  
                         echo "2_5_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                     Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                         echo "2_6_Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath""___Exist__ "          
                            if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                               cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                               repo sync . 
                               cd $CUR_REPO_PATH
                               continue
                            else 

                               echo "2_7_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                         Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path" ___Not Exist ___"
	             
                            fi
                            
                         echo "2_8_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path not exist !  please check the Path!!!  "
                         
                fi
                
		         continue
             fi
			 
	
             #Project list error on project home/repo/dev/apps/        OnePlusSpaces-binary:
             if [[ ${REPO_Git_Path_Check_Message} =~ "Project list error on project home/repo/dev/apps/" ]] ; then 
                 Manifest_InvalidRevision_Error_AOSP_Path=${REPO_Git_Path_Check_Message#*Project list error on project home/repo/dev/apps/}  
                 echo "3_1_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                 Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%:*}  
                 echo "3_2_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	             Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                 echo "Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"    
	         
                 if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                         echo "3_2_1_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path exist !  execute ( repo sync .)"
                         cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                         repo sync . 
                         cd $CUR_REPO_PATH
                         continue
                 else 
                         echo "3_3_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path not exist !   # search for  repo manifest | grep $Manifest_InvalidRevision_Error_AOSP_Path Path!!!  "
                         Search_Manifest_Path_Info=`repo manifest | grep $Manifest_InvalidRevision_Error_AOSP_Path`
                         echo "3_4_Search_Manifest_Path_Info="$Search_Manifest_Path_Info
                         Manifest_InvalidRevision_Error_AOSP_Path=${Search_Manifest_Path_Info#*path=\"}  
                         echo "3_5_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                         Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%\"*}  
                         echo "3_6_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                     Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                         echo "3_7_Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"
                             if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                                echo "3_8_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path exist !  execute ( repo sync .)"
                                cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                                repo sync . 
                                cd $CUR_REPO_PATH
                                continue
                             else
                               echo "failed to find the Git Dir Path: Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"  
                               echo "failed to find the Git Dir Path: Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"  
                               echo "failed to find the Git Dir Path: Search_Manifest_Path_Info=$Search_Manifest_Path_Info"  
                               echo "failed to find the Git Dir Path: please check the path  REPO_Git_Path_Check_Message="$REPO_Git_Path_Check_Message
                               echo "_________________________execute failed__________"
                               exit
                             fi
                        
                          
                 fi

		         continue
            fi





	         #Project list error on project home/repo/dev/platform/  oneplus/packages/apps/DolbyUI-binary: 
             if [[ ${REPO_Git_Path_Check_Message} =~ "Project list error on project home/repo/dev/platform/" ]] ; then 
                 Manifest_InvalidRevision_Error_AOSP_Path=${REPO_Git_Path_Check_Message#*Project list error on project home/repo/dev/platform/}  
                 echo "4_1_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                 Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%:*}  
                 echo "4_2_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	             Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                 echo "Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath"    
	         
                if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                         echo "Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path exist !  execute ( repo sync .)"
                         cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                         repo sync . 
                         cd $CUR_REPO_PATH
                         continue
                else 

                         
                         Search_Manifest_Path_Info=`repo manifest | grep $Manifest_InvalidRevision_Error_AOSP_Path`
                         echo "4_3_Search_Manifest_Path_Info="$Search_Manifest_Path_Info
                         Manifest_InvalidRevision_Error_AOSP_Path=${Search_Manifest_Path_Info#*path=\"}  
                         echo "4_4_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
                         Manifest_InvalidRevision_Error_AOSP_Path=${Manifest_InvalidRevision_Error_AOSP_Path%%\"*}  
                         echo "4_5_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                     Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path
                         echo "4_6_Manifest_InvalidRevision_Error_AOSP_FullPath=$Manifest_InvalidRevision_Error_AOSP_FullPath""___Exist__ "          
                            if [ -d "$Manifest_InvalidRevision_Error_AOSP_FullPath" ] ; then
                               cd $Manifest_InvalidRevision_Error_AOSP_FullPath
                               repo sync . 
                               cd $CUR_REPO_PATH
                               continue
                            else 

                               echo "4_7_Manifest_InvalidRevision_Error_AOSP_Path=$Manifest_InvalidRevision_Error_AOSP_Path"
	                         Manifest_InvalidRevision_Error_AOSP_FullPath=$CUR_REPO_PATH/$Manifest_InvalidRevision_Error_AOSP_Path" ___Not Exist ___"
	             
                            fi
                            
                         echo "4_8_Manifest_InvalidRevision_Error_AOSP_FullPath="$Manifest_InvalidRevision_Error_AOSP_FullPath" Git Dir Path not exist !  please check the Path!!!  "
                         
                fi
                
		         continue
             fi
             
             #Project list error_add
             #Project list error_add
             #Project list error_add
            echo "Failed Find the Rule To Resolve the Error Message: REPO_Git_Path_Check_Message="$REPO_Git_Path_Check_Message" please add check rule!!"

         fi
         echo "_________ repo forall -c pwd ____Loop["$i"_"$Repo_Check_Loop_Number"] End_________"
         Repo_Check_Loop_Index=$(($Repo_Check_Loop_Index+1))
done

echo " _904_ finish!! _________  repo forall -c pwd  (ok!)_____________Loop_Index="$Repo_Check_Loop_Index"  please repo sync -j2 "

rm -fr repo_git_path_error_check.log

echo "__________________________________Method_Out "$FUNCNAME
}



function rule903vmsivendorbackup_func_0x0(){
# =========================================================================== rule0v_func_0x0
# rule_tip:  $init_shfile_name  _903_  Msi_1  Vendor_1    ##   msi vendor 同时进行备份 满足编译成功节点记录
# desc: 
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME

CUR_REPO_PATH=$init_pwd
Code_Tag_Vendor_1="vendor"
Code_Tag_Msi_1="msi"
Code_Tag_Msi_2="system"
rule903_timestamp=`date +%Y-%m-%d_%H-%M-%S`    ##  获取到时间戳 2023-03-21_16-33-13 
REPO_NODE_LIST_DIR=$init_pwd/repo_node_record/$rule903_timestamp
REPO_MSI_NODE_FILE=$REPO_NODE_LIST_DIR/repo_backup_msi.txt
REPO_VENDOR_NODE_FILE=$REPO_NODE_LIST_DIR/repo_backup_vendor.txt
INPUT_DIR_1=$init_pwd/$init_input_2
INPUT_DIR_2=$init_pwd/$init_input_3
CUR_INPUT_MSI_DIR=
CUR_INPUT_VENDOR_DIR=

## 对输入的  Msi 和  Vendor 路径 进行拼凑 绝对路径
INPUT_DIR_1_Msi_Result=$(echo "$INPUT_DIR_1" | grep -ie "$Code_Tag_Msi_1"  -ie "$Code_Tag_Msi_2")
if [[ "$INPUT_DIR_1_Msi_Result" != "" ]] ; then
CUR_INPUT_MSI_DIR=$INPUT_DIR_1
fi

INPUT_DIR_1_Vendor_Result=$(echo "$INPUT_DIR_1" | grep -ie "$Code_Tag_Vendor_1")
if [[ "$INPUT_DIR_1_Vendor_Result" != "" ]] ; then
CUR_INPUT_VENDOR_DIR=$INPUT_DIR_1
fi

INPUT_DIR_2_Msi_Result=$(echo "$INPUT_DIR_2" | grep -ie "$Code_Tag_Msi_1"  -ie "$Code_Tag_Msi_2")
if [[ "$INPUT_DIR_2_Msi_Result" != "" ]] ; then
CUR_INPUT_MSI_DIR=$INPUT_DIR_2
fi

INPUT_DIR_2_Vendor_Result=$(echo "$INPUT_DIR_2" | grep -ie "$Code_Tag_Vendor_1")
if [[ "$INPUT_DIR_2_Vendor_Result" != "" ]] ; then
CUR_INPUT_VENDOR_DIR=$INPUT_DIR_2
fi


echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "REPO_NODE_LIST_DIR="$REPO_NODE_LIST_DIR
echo "REPO_MSI_NODE_FILE="$REPO_MSI_NODE_FILE
echo "REPO_VENDOR_NODE_FILE="$REPO_VENDOR_NODE_FILE
echo "CUR_INPUT_MSI_DIR="$CUR_INPUT_MSI_DIR
echo "CUR_INPUT_VENDOR_DIR="$CUR_INPUT_VENDOR_DIR

### 判断当前 输入的 Msi  和  Vendor 命令是否存在

if [[ "$CUR_INPUT_MSI_DIR" == "" ]] ; then
echo "CUR_INPUT_MSI_DIR="$CUR_INPUT_MSI_DIR" is empty , please check the msi path you input! "
exit
fi

if [[ "$CUR_INPUT_VENDOR_DIR" == "" ]] ; then
echo "CUR_INPUT_VENDOR_DIR="$CUR_INPUT_VENDOR_DIR" is empty , please check the vendor path you input! "
exit
fi

### 判断当前 输入的 Msi  和  Vendor  文件夹是否存在


if [ -d "$CUR_INPUT_MSI_DIR" ] ; then
    echo "CUR_INPUT_MSI_DIR="$CUR_INPUT_MSI_DIR" Msi File exist ! "
else 
    echo "CUR_INPUT_MSI_DIR="$CUR_INPUT_MSI_DIR" Msi File not exist !  please check the msi dir exist you input!  "
    exit
fi

if [ -d "$CUR_INPUT_VENDOR_DIR" ] ; then
    echo "CUR_INPUT_VENDOR_DIR="$CUR_INPUT_VENDOR_DIR" Vendor Dir File exist ! "
else 
    echo "CUR_INPUT_VENDOR_DIR="$CUR_INPUT_VENDOR_DIR" Dir File not exist !  please check the vendor dir exist you input!  "
    exit
fi



###############  执行  Msi的 记录保存   ###############

cd $CUR_INPUT_MSI_DIR
REPO_BackUp_File=$CUR_INPUT_MSI_DIR/repo_backup_msi.txt
## 获取所有的 AOSP的repo的 路径到 repo_git_path.txt
repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log
REPO_Git_Path_Check_Message=`cat repo_git_path_error_check.log | grep "Error:"`
    if [[ "$REPO_Git_Path_Check_Message" == "" ]] ; then
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
     echo " cd $CUR_INPUT_MSI_DIR &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Success___>>"
else
     echo " repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log "
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
     echo " cd $CUR_INPUT_MSI_DIR &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Failed___>> <<___Path_Error__>>"
     exit
fi
rm -fr repo_git_path_error_check.log


repo forall -c pwd > ./repo_git_path.txt
## 当前遍历到的 repo_git_path.txt 的 行数
gitpath_line_number=1
gitpath_min_count=500
## 获取 repo_git_path.txt 所有的行  既 当前 AOSP的 所有的 repo仓库的 个数
gitpath_all_number=`grep -n  ""   ./repo_git_path.txt | wc -l`

if [ $gitpath_all_number -lt $gitpath_min_count ] ; then
  echo " gitpath_all_number="$gitpath_all_number" xiaoyu 500   repo forall -c pwd > ./repo_git_path.txt   # check error"
  exit
fi
echo "CUR_MSI_REPO_PATH="$CUR_INPUT_MSI_DIR
echo "REPO_BackUp_File="$REPO_BackUp_File
echo "gitpath_all_number="$gitpath_all_number
echo -e "" > $REPO_BackUp_File

for gitpath_line in `cat ./repo_git_path.txt`
do
    cd $gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    ## 打印 git status 信息
    
    
    ### Command 本地主分支
    git_local_branch_desc=`git branch -vv | grep "*" |awk -F' ' '{ print $2 }'`
    echo "__git_local_branch:"$git_local_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取本地主分支的第一个commitid   git rev-parse HEAD  || git rev-parse origin/master
    git_remote_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    if [[ "$git_remote_branch_desc" == "" ]] ; then
        git_remote_branch_desc="HEAD"
    fi
    git_head_commitid_desc=`git rev-parse $git_remote_branch_desc`
    echo "__git_head_commitid:"$git_head_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_head_command:" 'cd' $gitpath_line '&&' 'git rev-parse' $git_remote_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	
	git_local_first_commitid_desc=`git rev-parse HEAD`
    echo "__git_local_first_commitid:"$git_local_first_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_local_first_command:" 'cd' $gitpath_line '&&' 'git rev-parse HEAD'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	
	git_local_head_1_commitid_desc=`git rev-parse HEAD~1`
    echo "__git_local_head_1_commitid:"$git_local_head_1_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~1'  >> $REPO_BackUp_File
	git_local_head_2_commitid_desc=`git rev-parse HEAD~2`
    echo "__git_local_head_2_commitid:"$git_local_head_2_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~2'  >> $REPO_BackUp_File
	git_local_head_3_commitid_desc=`git rev-parse HEAD~3`
    echo "__git_local_head_3_commitid:"$git_local_head_3_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~3'  >> $REPO_BackUp_File
	git_local_head_4_commitid_desc=`git rev-parse HEAD~4`
    echo "__git_local_head_4_commitid:"$git_local_head_4_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~4'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	
    ### Command 获取本地的 分支详细信息
    git_branch_vv_desc=`git branch -vv`
    echo "__git_branch_vv:"$git_branch_vv_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    ### Command 获取本地的 git 状态 信息
    git_status_desc=`git status`
    echo "__git_status:"$git_status_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取远程主分支(可能 没有 )
    git_master_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    echo "__git_master_branch:"$git_master_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    ### Command 获取远程第一个分支(远程主分支没有情况下 )
    git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`
    echo "__git_nomaster_number1_branch:"$git_nomaster_number1_branch  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    

    ### Local 和 Remote分支 的Head Commit 是否相等
	git_branch_local_remote_head_same_flag="false"
    if [[ "$git_head_commitid_desc" == "$git_local_first_commitid_desc" ]] ; then
        git_branch_local_remote_head_same_flag="true"
    else
        git_branch_local_remote_head_same_flag="false"
    fi
    echo "__git_status_local_remote_samehead:"$git_branch_local_remote_head_same_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Prop "Untracked files"  标记是否有 未追踪文件
    git_status_untracked=`git status | grep "Untracked files"`
    git_status_untracked_flag="false"
    if [ -n "$git_status_untracked" ]; then
        git_status_untracked_flag="true"
    else
        git_status_untracked_flag="false"
    fi
    echo "__git_status_untracked:"$git_status_untracked_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
    ### Prop "modified:" 标记是否有 修改后未提交文件
    git_status_modified=`git status | grep "modified:"`
    git_status_modified_flag="false"
    if [ -n "$git_status_modified" ]; then
        git_status_modified_flag="true"
    else
        git_status_modified_flag="false"
    fi
    echo "__git_status_modified:"$git_status_modified_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
        
    ### Prop "commits" 标记是否有未提交commit标识
    git_status_commits=`git status | grep "commits"`  
    git_status_commits_flag="false"
    if [ -n "$git_status_commits" ]; then
        git_status_commits_flag="true"
    else
        git_status_commits_flag="false"
    fi
    echo "__git_status_uncommits:"$git_status_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    
    #  Prop Your branch is ahead of   标记是否本地分支有超过远程分支提交的标识
    git_status_ahead_commits=`git status | grep "Your branch is ahead of"`  
    git_status_ahead_commits_flag="false"
    if [ -n "$git_status_ahead_commits" ]; then
        git_status_ahead_commits_flag="true"
    else
        git_status_ahead_commits_flag="false"
    fi
    echo "__git_status_aheadcommits:"$git_status_ahead_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is behind   标记是否本地分支有少于远程分支提交的标识
    git_status_behind_commits=`git status | grep "Your branch is behind"`  
    git_status_behind_commits_flag="false"
    if [ -n "$git_status_behind_commits" ]; then
        git_status_behind_commits_flag="true"
    else
        git_status_behind_commits_flag="false"
    fi
    echo "__git_status_behindcommits:"$git_status_behind_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is up to date   标记是否本地分支是否与远程分支一致
    git_status_update_commits=`git status | grep "Your branch is up to date"`  
    git_status_update_commits_flag="false"
    if [ -n "$git_status_update_commits" ]; then
        git_status_update_commits_flag="true"
    else
        git_status_update_commits_flag="false"
    fi
    echo "__git_status_updatecommits:"$git_status_update_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	

    #  Prop Have TEMP Branch  标记是否有本地 TEMP分支 标识位
    is_have_TEMP_branch_flag="false"
    if [[ "$git_local_branch_desc" == "TEMP" ]] ; then
        is_have_TEMP_branch_flag="true"
    else
        is_have_TEMP_branch_flag="false"
    fi
    echo "__git_have_TEMP_branch:"$is_have_TEMP_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File   
	

    #  Prop Have Master Branch  标记是否有远程主分支 标识位
    is_have_master_branch_flag="false"
    if [ -n "$git_master_branch_desc" ]; then
        is_have_master_branch_flag="true"
    else
        is_have_master_branch_flag="false"
    fi
    echo "__git_have_master_branch:"$is_have_master_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    
    echo "___________________________________________________________"   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    
    echo -e   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
     gitpath_line_number=$(($gitpath_line_number+1))
 done

mkdir -p $REPO_NODE_LIST_DIR  
cp -fr $REPO_BackUp_File $REPO_MSI_NODE_FILE


###############  执行  Vendor 的 记录保存  ###############
cd $CUR_INPUT_VENDOR_DIR
REPO_BackUp_File=$CUR_INPUT_VENDOR_DIR/repo_backup_vendor.txt


## 获取所有的 AOSP的repo的 路径到 repo_git_path.txt

repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log
REPO_Git_Path_Check_Message=`cat repo_git_path_error_check.log | grep "Error:"`
    if [[ "$REPO_Git_Path_Check_Message" == "" ]] ; then
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
     echo " cd $CUR_INPUT_VENDOR_DIR &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Success___>>"
else
     echo " repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log "
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "

     echo " cd $CUR_INPUT_VENDOR_DIR &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Failed___>> <<___Path_Error__>>"
     exit
fi
rm -fr repo_git_path_error_check.log


repo forall -c pwd > ./repo_git_path.txt
## 当前遍历到的 repo_git_path.txt 的 行数
gitpath_line_number=1
gitpath_min_count=500
## 获取 repo_git_path.txt 所有的行  既 当前 AOSP的 所有的 repo仓库的 个数
gitpath_all_number=`grep -n  ""   ./repo_git_path.txt | wc -l`

if [ $gitpath_all_number -lt $gitpath_min_count ] ; then
  echo " gitpath_all_number="$gitpath_all_number" xiaoyu 500   repo forall -c pwd > ./repo_git_path.txt   # check error"
  exit
fi
echo "CUR_VENDOR_REPO_PATH="$CUR_INPUT_VENDOR_DIR
echo "REPO_BackUp_File="$REPO_BackUp_File
echo "gitpath_all_number="$gitpath_all_number
echo -e "" > $REPO_BackUp_File

for gitpath_line in `cat ./repo_git_path.txt`
do
    cd $gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    ## 打印 git status 信息
    
    
    ### Command 本地主分支
    git_local_branch_desc=`git branch -vv | grep "*" |awk -F' ' '{ print $2 }'`
    echo "__git_local_branch:"$git_local_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取本地主分支的第一个commitid   git rev-parse HEAD  || git rev-parse origin/master
    git_remote_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    if [[ "$git_remote_branch_desc" == "" ]] ; then
        git_remote_branch_desc="HEAD"
    fi
    git_head_commitid_desc=`git rev-parse $git_remote_branch_desc`
    echo "__git_head_commitid:"$git_head_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_head_command:" 'cd' $gitpath_line '&&' 'git rev-parse' $git_remote_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    git_local_first_commitid_desc=`git rev-parse HEAD`
    echo "__git_local_first_commitid:"$git_local_first_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_local_first_command:" 'cd' $gitpath_line '&&' 'git rev-parse HEAD'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

	git_local_head_1_commitid_desc=`git rev-parse HEAD~1`
    echo "__git_local_head_1_commitid:"$git_local_head_1_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~1'  >> $REPO_BackUp_File
	git_local_head_2_commitid_desc=`git rev-parse HEAD~2`
    echo "__git_local_head_2_commitid:"$git_local_head_2_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~2'  >> $REPO_BackUp_File
	git_local_head_3_commitid_desc=`git rev-parse HEAD~3`
    echo "__git_local_head_3_commitid:"$git_local_head_3_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~3'  >> $REPO_BackUp_File
	git_local_head_4_commitid_desc=`git rev-parse HEAD~4`
    echo "__git_local_head_4_commitid:"$git_local_head_4_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~4'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	
    ### Command 获取本地的 分支详细信息
    git_branch_vv_desc=`git branch -vv`
    echo "__git_branch_vv:"$git_branch_vv_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    ### Command 获取本地的 git 状态 信息
    git_status_desc=`git status`
    echo "__git_status:"$git_status_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取远程主分支(可能 没有 )
    git_master_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    echo "__git_master_branch:"$git_master_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    ### Command 获取远程第一个分支(远程主分支没有情况下 )
    git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`
    echo "__git_nomaster_number1_branch:"$git_nomaster_number1_branch  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    

    ### Local 和 Remote分支 的Head Commit 是否相等
	git_branch_local_remote_head_same_flag="false"
    if [[ "$git_head_commitid_desc" == "$git_local_first_commitid_desc" ]] ; then
        git_branch_local_remote_head_same_flag="true"
    else
        git_branch_local_remote_head_same_flag="false"
    fi
    echo "__git_status_local_remote_samehead:"$git_branch_local_remote_head_same_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Prop "Untracked files"  标记是否有 未追踪文件
    git_status_untracked=`git status | grep "Untracked files"`
    git_status_untracked_flag="false"
    if [ -n "$git_status_untracked" ]; then
        git_status_untracked_flag="true"
    else
        git_status_untracked_flag="false"
    fi
    echo "__git_status_untracked:"$git_status_untracked_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
    ### Prop "modified:" 标记是否有 修改后未提交文件
    git_status_modified=`git status | grep "modified:"`
    git_status_modified_flag="false"
    if [ -n "$git_status_modified" ]; then
        git_status_modified_flag="true"
    else
        git_status_modified_flag="false"
    fi
    echo "__git_status_modified:"$git_status_modified_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
        
    ### Prop "commits" 标记是否有未提交commit标识
    git_status_commits=`git status | grep "commits"`  
    git_status_commits_flag="false"
    if [ -n "$git_status_commits" ]; then
        git_status_commits_flag="true"
    else
        git_status_commits_flag="false"
    fi
    echo "__git_status_uncommits:"$git_status_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    
    #  Prop Your branch is ahead of   标记是否本地分支有超过远程分支提交的标识
    git_status_ahead_commits=`git status | grep "Your branch is ahead of"`  
    git_status_ahead_commits_flag="false"
    if [ -n "$git_status_ahead_commits" ]; then
        git_status_ahead_commits_flag="true"
    else
        git_status_ahead_commits_flag="false"
    fi
    echo "__git_status_aheadcommits:"$git_status_ahead_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is behind   标记是否本地分支有少于远程分支提交的标识
    git_status_behind_commits=`git status | grep "Your branch is behind"`  
    git_status_behind_commits_flag="false"
    if [ -n "$git_status_behind_commits" ]; then
        git_status_behind_commits_flag="true"
    else
        git_status_behind_commits_flag="false"
    fi
    echo "__git_status_behindcommits:"$git_status_behind_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is up to date   标记是否本地分支是否与远程分支一致
    git_status_update_commits=`git status | grep "Your branch is up to date"`  
    git_status_update_commits_flag="false"
    if [ -n "$git_status_update_commits" ]; then
        git_status_update_commits_flag="true"
    else
        git_status_update_commits_flag="false"
    fi
    echo "__git_status_updatecommits:"$git_status_update_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	

    #  Prop Have TEMP Branch  标记是否有本地 TEMP分支 标识位
    is_have_TEMP_branch_flag="false"
    if [[ "$git_local_branch_desc" == "TEMP" ]] ; then
        is_have_TEMP_branch_flag="true"
    else
        is_have_TEMP_branch_flag="false"
    fi
    echo "__git_have_TEMP_branch:"$is_have_TEMP_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File   

    #  Prop Have Master Branch  标记是否有远程主分支 标识位
    is_have_master_branch_flag="false"
    if [ -n "$git_master_branch_desc" ]; then
        is_have_master_branch_flag="true"
    else
        is_have_master_branch_flag="false"
    fi
    echo "__git_have_master_branch:"$is_have_master_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    echo "___________________________________________________________"   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	echo -e   >> $REPO_BackUp_File  
     gitpath_line_number=$(($gitpath_line_number+1))
 done

cp -fr $REPO_BackUp_File $REPO_VENDOR_NODE_FILE

echo "REPO_MSI_NODE_FILE="$REPO_MSI_NODE_FILE" Success!"
echo "REPO_VENDOR_NODE_FILE="$REPO_VENDOR_NODE_FILE" Success!"
echo "__________________________________Method_Out "$FUNCNAME
cd $init_pwd
}



function rule902vrepobackupoperation_func_0x0(){
# =========================================================================== rule902vrepobackupoperation_func_0x0
# rule_tip:  $init_shfile_name  _902_   ##  repo_backup_operation 依据当前路径的 repo_backup.txt  来对当前 repo 进行备份恢复操作  与服务器提交一致
# desc: repo_backup_operation 依据当前路径的 repo_backup_msi.txt   repo_backup_vendor.txt  来对当前 repo 进行备份恢复操作
# sample:  
# sample_out: 
CUR_REPO_PATH=$init_pwd
Code_Tag_Vendor_1="vendor"
Code_Tag_Msi_1="msi"
Code_Tag_Msi_2="system"
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
Code_Match_Vendor_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Vendor_1")
if [[ "$Code_Match_Vendor_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_msi.txt
fi

Code_Match_Msi_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Msi_1"  -ie "$Code_Tag_Msi_2")
if [[ "$Code_Match_Msi_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_msi.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
fi

echo "Code_Match_Vendor_Result="$Code_Match_Vendor_Result
echo "Code_Match_Msi_Result="$Code_Match_Msi_Result
echo "REPO_BackUp_File="$REPO_BackUp_File

backup_all_number=`grep -n  ""   $REPO_BackUp_File | wc -l`
backup_line_number=1

if [ -f "$REPO_BackUp_File" ] ; then
    echo  "$REPO_BackUp_File File exist!"
else 
    echo  "$REPO_BackUp_File File not exist!  backup_operation stop! "
    exit
fi



############### param define begin ###############
repo_param_gitpath=""
repo_param_local_branch=""
repo_param_remote_head_commitid=""
repo_param_local_first_commitid=""
repo_param_local_remote_samehead_flag=""
repo_param_untracked_flag=""
repo_param_modified_flag=""
repo_param_uncommits_flag=""
repo_param_aheadcommits_flag=""
repo_param_behindcommits_flag=""
repo_param_updatecommits_flag=""
repo_param_have_temp_branch_flag=""
repo_param_have_master_branch_flag=""

############### param define end ###############

## 使用 while read 使得可以一行一行读取  而不是空格进行切分的读取
while read  backup_line
do
    echo "backup_line["$backup_line_number"_"$backup_all_number"]:"$backup_line 
    ####repo_git[1077_2071]_gitpath:xxxxx  切换到对应路径  并且去除掉对应的 untrack文件 ( git reset 没法删除 untrack文件)
    if [[ ${backup_line} =~ "####repo_git" ]] ; then 
        repo_param_gitpath=${backup_line#*gitpath:}   ##  #井号截取 右边字符串 后面
        echo "repo_param_gitpath=$repo_param_gitpath"
		cd $repo_param_gitpath
        repo_param_local_branch=""
        repo_param_remote_head_commitid=""
        repo_param_local_first_commitid=""
        repo_param_local_remote_samehead_flag=""
        repo_param_untracked_flag=""
        repo_param_modified_flag=""
        repo_param_uncommits_flag=""
        repo_param_aheadcommits_flag=""
        repo_param_behindcommits_flag=""
        repo_param_updatecommits_flag=""
        repo_param_have_temp_branch_flag=""
        repo_param_have_master_branch_flag=""
		continue
    fi
    
    ##__git_local_branch:TEMP  切换到记录的本地分支
    if [[ ${backup_line} =~ "__git_local_branch:" ]] ; then 
        repo_param_local_branch=${backup_line#*__git_local_branch:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_local_branch=$repo_param_local_branch"
		continue
    fi
    
    ##__git_head_commitid:0f015b711ae44b8bd6b3be8ba1d21cbfc32442e6   reset到指定的commitid
    if [[ ${backup_line} =~ "__git_head_commitid:" ]] ; then 
        repo_param_remote_head_commitid=${backup_line#*__git_head_commitid:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_remote_head_commitid=$repo_param_remote_head_commitid"
		continue
    fi

    ##__git_local_first_commitid:0f015b711ae44b8bd6b3be8ba1d21cbfc32442e6   本地的第一个提交
    if [[ ${backup_line} =~ "__git_local_first_commitid:" ]] ; then 
        repo_param_local_first_commitid=${backup_line#*__git_local_first_commitid:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_local_first_commitid=$repo_param_local_first_commitid"
		continue
    fi

    ##__git_status_local_remote_samehead:false   本地Head提交 和 服务器 Head 提交是否 一致 
    if [[ ${backup_line} =~ "__git_status_local_remote_samehead:" ]] ; then 
        repo_param_local_remote_samehead_flag=${backup_line#*__git_status_local_remote_samehead:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_local_remote_samehead_flag=$repo_param_local_remote_samehead_flag"
		continue
    fi
	
    ##__git_status_untracked:false   是否有 未追踪的文件
    if [[ ${backup_line} =~ "__git_status_untracked:" ]] ; then 
        repo_param_untracked_flag=${backup_line#*__git_status_untracked:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_untracked_flag=$repo_param_untracked_flag"
		continue
    fi

    ##__git_status_modified:false   是否有 修改的文件
    if [[ ${backup_line} =~ "__git_status_modified:" ]] ; then 
        repo_param_modified_flag=${backup_line#*__git_status_modified:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_modified_flag=$repo_param_modified_flag"
		continue
    fi
	
    ##__git_status_uncommits:false   是否有 本地提交未上传的 commit 
    if [[ ${backup_line} =~ "__git_status_uncommits:" ]] ; then 
        repo_param_uncommits_flag=${backup_line#*__git_status_uncommits:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_uncommits_flag=$repo_param_uncommits_flag"
		continue
    fi
	
    ##__git_status_aheadcommits:false   本地是否有还未上传服务器的提交 
    if [[ ${backup_line} =~ "__git_status_aheadcommits:" ]] ; then 
        repo_param_aheadcommits_flag=${backup_line#*__git_status_aheadcommits:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_aheadcommits_flag=$repo_param_aheadcommits_flag"
		continue
    fi
	
    ##__git_status_behindcommits:false   本地是否有落后服务器提交
    if [[ ${backup_line} =~ "__git_status_behindcommits:" ]] ; then 
        repo_param_behindcommits_flag=${backup_line#*__git_status_behindcommits:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_behindcommits_flag=$repo_param_behindcommits_flag"
		continue
    fi
	
    ##__git_status_updatecommits:false   本地是否与服务器的提交保持一致
    if [[ ${backup_line} =~ "__git_status_updatecommits:" ]] ; then 
        repo_param_updatecommits_flag=${backup_line#*__git_status_updatecommits:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_updatecommits_flag=$repo_param_updatecommits_flag"
		continue
    fi

    ##__git_have_TEMP_branch:false   本地 是否 所在为 TEMP 分支
    if [[ ${backup_line} =~ "__git_have_TEMP_branch:" ]] ; then 
        repo_param_have_temp_branch_flag=${backup_line#*__git_have_TEMP_branch:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_have_temp_branch_flag=$repo_param_have_temp_branch_flag"
		continue
    fi

    ##__git_have_master_branch:false   本地 是否 有远程主分支 master
    if [[ ${backup_line} =~ "__git_have_master_branch:" ]] ; then 
        repo_param_have_master_branch_flag=${backup_line#*__git_have_master_branch:}   ##  #井号截取 右边字符串 后面
        #echo "repo_param_have_master_branch_flag=$repo_param_have_master_branch_flag"
		continue
    fi
	
    if [[ ${backup_line} =~ "________________________________________" ]] ; then   ### 当前这个repo 结束 时候的打印  这里 执行 一些 逻辑操作
    echo "_______________["$backup_line_number"_"$backup_all_number"]_____"$repo_param_gitpath"_____Git_Operation_Begin_____"        
    echo "__param1__ repo_param_gitpath=$repo_param_gitpath"
    echo "__param2__ repo_param_local_branch=$repo_param_local_branch"
    echo "__param3__ repo_param_remote_head_commitid=$repo_param_remote_head_commitid"
    echo "__param4__ repo_param_local_first_commitid=$repo_param_local_first_commitid"
    echo "__param5__ repo_param_local_remote_samehead_flag=$repo_param_local_remote_samehead_flag"
    echo "__param6__ repo_param_untracked_flag=$repo_param_untracked_flag"
    echo "__param7__ repo_param_modified_flag=$repo_param_modified_flag"
    echo "__param8__ repo_param_uncommits_flag=$repo_param_uncommits_flag"
    echo "__param9__ repo_param_aheadcommits_flag=$repo_param_aheadcommits_flag"
    echo "__param10__ repo_param_behindcommits_flag=$repo_param_behindcommits_flag"
    echo "__param11__ repo_param_updatecommits_flag=$repo_param_updatecommits_flag"
    echo "__param12__ repo_param_have_temp_branch_flag=$repo_param_have_temp_branch_flag"
    echo "__param13__ repo_param_have_master_branch_flag=$repo_param_have_master_branch_flag"
	
	git_command_line_number=1
	cd $repo_param_gitpath
### rule1  (repo_param_updatecommits_flag == true &&  repo_param_local_remote_samehead_flag == true  && repo_param_modified_flag == false)  那么 跳过更新这个仓库

        if [ "$repo_param_updatecommits_flag" = "true" -a  "$repo_param_local_remote_samehead_flag" = "true" -a "$repo_param_modified_flag" = "false"  ]; then
            echo "==== rule1 No Need Update ===="
	    else 
            echo "###### Need Update "$repo_param_gitpath" ######"
	    	cd  $repo_param_gitpath 
	    
### rule 2  (repo_param_have_temp_branch_flag == false )   如果当前分支 不在 TEMP  那么 切换到这个 branch
            if [ "$repo_param_have_temp_branch_flag" = "false"  ]; then
                echo "command_"$git_command_line_number": git checkout TEMP"
		    	git_command_line_number=$(($git_command_line_number+1))
		        git checkout TEMP    
		    
	        fi
		    
            if [ "$repo_param_modified_flag" = "true"  ]; then
		        echo "command_"$git_command_line_number": git checkout ."
		    	git_command_line_number=$(($git_command_line_number+1))
		        git checkout .
		    
	        fi
		    
		    
            if [ "$repo_param_untracked_flag" = "true"  ]; then
		       echo "command_"$git_command_line_number": git clean -dxf "
		    	git_command_line_number=$(($git_command_line_number+1))
		        git clean -dxf
		    
	        fi
		    
            #if [ "$repo_param_behindcommits_flag" = "true"  ]; then
		    #    echo "command_"$git_command_line_number": repo sync . "
		    #	git_command_line_number=$(($git_command_line_number+1))
		    #    repo sync .
		    #
	        #fi
		
		
            if [ "$repo_param_local_remote_samehead_flag" = "false"  ]; then   ## 与远程分支不一致 
		         cd  $repo_param_gitpath 
	             git_local_head_1_commitid_desc=`git rev-parse HEAD~1`
	             git_local_head_2_commitid_desc=`git rev-parse HEAD~2`
	             git_local_head_3_commitid_desc=`git rev-parse HEAD~3`
	             git_local_head_4_commitid_desc=`git rev-parse HEAD~4`
	             echo "repo_param_gitpath=$repo_param_gitpath"
	             echo "remote_head_commitid=$repo_param_remote_head_commitid"
                 echo "local_first_commitid=$repo_param_local_first_commitid"
                 echo "local_head_1_commitid=$git_local_head_1_commitid_desc"
                 echo "local_head_2_commitid=$git_local_head_2_commitid_desc"
                 echo "local_head_3_commitid=$git_local_head_3_commitid_desc"
                 echo "local_head_4_commitid=$git_local_head_4_commitid_desc"
			
			## 如果 远程分支 在 这 head_1  head_2  head_3 head_4 中间 那么就切换到这个commitid , 否则 维持原样  ||  或者 运算符  -o   -o==||    -a==&&
			
                if [ "$git_local_head_1_commitid_desc" = "$repo_param_remote_head_commitid" -o  "$git_local_head_2_commitid_desc" = "$repo_param_remote_head_commitid" -o "$git_local_head_3_commitid_desc" = "$repo_param_remote_head_commitid"  -o  "$git_local_head_4_commitid_desc" = "$repo_param_remote_head_commitid"  ]; then
                    echo "command_"$git_command_line_number": git reset --hard  "$repo_param_remote_head_commitid "    ( remote_head_commitid same with  previos 5 commitids )"
	    			git_command_line_number=$(($git_command_line_number+1))
	    			git reset --hard $repo_param_remote_head_commitid
	    		else
				
				    ## 如果当前  有 TEMP 并且 behingcommits = true 的话 那么 也更新这个 repo 仓库
				    if [ "$repo_param_have_temp_branch_flag" = "true" -a  "$repo_param_behindcommits_flag" = "true"  ]; then
                        echo "command_"$git_command_line_number": git reset --hard  "$repo_param_remote_head_commitid "     ( repo_param_behindcommits_flag="$repo_param_behindcommits_flag")"
	    			    git_command_line_number=$(($git_command_line_number+1))
	    			    git reset --hard $repo_param_remote_head_commitid
                    else
	    		        echo "command_"$git_command_line_number":failed to search remote commitid: "$repo_param_remote_head_commitid" on previos 5 commitids !!  we do not do anything change!"
	    			    git_command_line_number=$(($git_command_line_number+1))
					fi


	    		fi 
	    
	        fi
	    	
	    fi
	
	
	echo "_______________["$backup_line_number"_"$backup_all_number"]_____"$repo_param_gitpath"_____Git_Operation_End_____"
    continue	
    fi

     backup_line_number=$(($backup_line_number+1))
done  < $REPO_BackUp_File

cd $CUR_REPO_PATH

}



function rule901vrepobackuprecord_func_0x0(){
# =========================================================================== rule901vrepobackuprecord_func_0x0
# rule_tip:  $init_shfile_name  _901_   ##  repo_backup_record    对当前repo仓库 状态进行状态备份   会生成 repo_backup_msi.txt  repo_backup_vendor.txt 文件 
# desc:  repo_backup_record    对当前repo仓库 状态进行状态备份   会生成 repo_backup.txt 文件
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
CUR_REPO_PATH=$init_pwd   ## Vendor_xxx   Msi_xxx 开头
Code_Tag_Vendor_1="vendor"
Code_Tag_Msi_1="msi"
Code_Tag_Msi_2="system"
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
Code_Match_Vendor_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Vendor_1")
if [[ "$Code_Match_Vendor_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_msi.txt
fi

Code_Match_Msi_Result=$(echo "$CUR_REPO_PATH" | grep -ie "$Code_Tag_Msi_1"  -ie "$Code_Tag_Msi_2")
if [[ "$Code_Match_Msi_Result" != "" ]] ; then
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_msi.txt
else
REPO_BackUp_File=$CUR_REPO_PATH/repo_backup_vendor.txt
fi

echo "Code_Match_Vendor_Result="$Code_Match_Vendor_Result
echo "Code_Match_Msi_Result="$Code_Match_Msi_Result
echo "REPO_BackUp_File="$REPO_BackUp_File

rule900_timestamp=`date +%Y-%m-%d_%H-%M-%S`  ##  获取到时间戳 2023-03-21_16-33-13 


repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log
REPO_Git_Path_Check_Message=`cat repo_git_path_error_check.log | grep "Error:"`
    if [[ "$REPO_Git_Path_Check_Message" == "" ]] ; then
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
     echo " cd $CUR_REPO_PATH &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Success___>>"
else
     echo " repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log "
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "

     echo " cd $CUR_REPO_PATH &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Failed___>> <<___Path_Error__>>"
     exit
fi
rm -fr repo_git_path_error_check.log


## 获取所有的 AOSP的repo的 路径到 repo_git_path.txt

repo forall -c pwd > ./repo_git_path.txt
## 当前遍历到的 repo_git_path.txt 的 行数
gitpath_line_number=1
gitpath_min_count=500
## 获取 repo_git_path.txt 所有的行  既 当前 AOSP的 所有的 repo仓库的 个数
gitpath_all_number=`grep -n  ""   ./repo_git_path.txt | wc -l`

if [ $gitpath_all_number -lt $gitpath_min_count ] ; then
  echo " gitpath_all_number="$gitpath_all_number" xiaoyu 500   repo forall -c pwd > ./repo_git_path.txt   # check error"
  exit
fi
echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "REPO_BackUp_File="$REPO_BackUp_File
echo "gitpath_all_number="$gitpath_all_number
echo -e "" > $REPO_BackUp_File

for gitpath_line in `cat ./repo_git_path.txt`
do
    cd $gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    ## 打印 git status 信息
    
    
    ### Command 本地主分支
    git_local_branch_desc=`git branch -vv | grep "*" |awk -F' ' '{ print $2 }'`
    echo "__git_local_branch:"$git_local_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取本地主分支的第一个commitid   git rev-parse HEAD  || git rev-parse origin/master
    git_remote_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    if [[ "$git_remote_branch_desc" == "" ]] ; then
        git_remote_branch_desc="HEAD"
    fi
    git_head_commitid_desc=`git rev-parse $git_remote_branch_desc`
    echo "__git_head_commitid:"$git_head_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_head_command:" 'cd' $gitpath_line '&&' 'git rev-parse' $git_remote_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	

    git_local_first_commitid_desc=`git rev-parse HEAD`
    echo "__git_local_first_commitid:"$git_local_first_commitid_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    echo "__git_local_first_command:" 'cd' $gitpath_line '&&' 'git rev-parse HEAD'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

	git_local_head_1_commitid_desc=`git rev-parse HEAD~1`
    echo "__git_local_head_1_commitid:"$git_local_head_1_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~1'  >> $REPO_BackUp_File
	git_local_head_2_commitid_desc=`git rev-parse HEAD~2`
    echo "__git_local_head_2_commitid:"$git_local_head_2_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~2'  >> $REPO_BackUp_File
	git_local_head_3_commitid_desc=`git rev-parse HEAD~3`
    echo "__git_local_head_3_commitid:"$git_local_head_3_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~3'  >> $REPO_BackUp_File
	git_local_head_4_commitid_desc=`git rev-parse HEAD~4`
    echo "__git_local_head_4_commitid:"$git_local_head_4_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~4'  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取本地的 分支详细信息
    git_branch_vv_desc=`git branch -vv`
    echo "__git_branch_vv:"$git_branch_vv_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    ### Command 获取本地的 git 状态 信息
    git_status_desc=`git status`
    echo "__git_status:"$git_status_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Command 获取远程主分支(可能 没有 )
    git_master_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    echo "__git_master_branch:"$git_master_branch_desc  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    ### Command 获取远程第一个分支(远程主分支没有情况下 )
    git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`
    echo "__git_nomaster_number1_branch:"$git_nomaster_number1_branch  >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    ### Local 和 Remote分支 的Head Commit 是否相等
	git_branch_local_remote_head_same_flag="false"
    if [[ "$git_head_commitid_desc" == "$git_local_first_commitid_desc" ]] ; then
        git_branch_local_remote_head_same_flag="true"
    else
        git_branch_local_remote_head_same_flag="false"
    fi
    echo "__git_status_local_remote_samehead:"$git_branch_local_remote_head_same_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File

    
    ### Prop "Untracked files"  标记是否有 未追踪文件
    git_status_untracked=`git status | grep "Untracked files"`
    git_status_untracked_flag="false"
    if [ -n "$git_status_untracked" ]; then
        git_status_untracked_flag="true"
    else
        git_status_untracked_flag="false"
    fi
    echo "__git_status_untracked:"$git_status_untracked_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
    ### Prop "modified:" 标记是否有 修改后未提交文件
    git_status_modified=`git status | grep "modified:"`
    git_status_modified_flag="false"
    if [ -n "$git_status_modified" ]; then
        git_status_modified_flag="true"
    else
        git_status_modified_flag="false"
    fi
    echo "__git_status_modified:"$git_status_modified_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
        
        
    ### Prop "commits" 标记是否有未提交commit标识
    git_status_commits=`git status | grep "commits"`  
    git_status_commits_flag="false"
    if [ -n "$git_status_commits" ]; then
        git_status_commits_flag="true"
    else
        git_status_commits_flag="false"
    fi
    echo "__git_status_uncommits:"$git_status_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    
    
    
    #  Prop Your branch is ahead of   标记是否本地分支有超过远程分支提交的标识
    git_status_ahead_commits=`git status | grep "Your branch is ahead of"`  
    git_status_ahead_commits_flag="false"
    if [ -n "$git_status_ahead_commits" ]; then
        git_status_ahead_commits_flag="true"
    else
        git_status_ahead_commits_flag="false"
    fi
    echo "__git_status_aheadcommits:"$git_status_ahead_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is behind   标记是否本地分支有少于远程分支提交的标识
    git_status_behind_commits=`git status | grep "Your branch is behind"`  
    git_status_behind_commits_flag="false"
    if [ -n "$git_status_behind_commits" ]; then
        git_status_behind_commits_flag="true"
    else
        git_status_behind_commits_flag="false"
    fi
    echo "__git_status_behindcommits:"$git_status_behind_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File    


    #  Prop Your branch is up to date   标记是否本地分支是否与远程分支一致
    git_status_update_commits=`git status | grep "Your branch is up to date"`  
    git_status_update_commits_flag="false"
    if [ -n "$git_status_update_commits" ]; then
        git_status_update_commits_flag="true"
    else
        git_status_update_commits_flag="false"
    fi
    echo "__git_status_updatecommits:"$git_status_update_commits_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
	

    #  Prop Have TEMP Branch  标记是否有本地 TEMP分支 标识位
    is_have_TEMP_branch_flag="false"
    if [[ "$git_local_branch_desc" == "TEMP" ]] ; then
        is_have_TEMP_branch_flag="true"
    else
        is_have_TEMP_branch_flag="false"
    fi
    echo "__git_have_TEMP_branch:"$is_have_TEMP_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File   
	

    #  Prop Have Master Branch  标记是否有远程主分支 标识位
    is_have_master_branch_flag="false"
    if [ -n "$git_master_branch_desc" ]; then
        is_have_master_branch_flag="true"
    else
        is_have_master_branch_flag="false"
    fi
    echo "__git_have_master_branch:"$is_have_master_branch_flag   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File  
    echo "___________________________________________________________"   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
    echo -e   >> $REPO_BackUp_File
     gitpath_line_number=$(($gitpath_line_number+1))
 done

echo "__________________________________Method_Out "$FUNCNAME
}





function rule900vreporeset_func_0x0(){
# =========================================================================== rule900vreporeset_func_0x0
# rule_tip:  $init_shfile_name  _0_   ##  强制更新repo仓库 比 repo sync -j2强 会有log文件 repo_sync_log_*.txt 文件生成
# desc:  强制更新repo仓库 比 repo sync -j2强 会有log文件 repo_sync_log_*.txt 文件生成
# sample:  
# sample_out: 
echo "__________________________________Method_In "$FUNCNAME
CUR_REPO_PATH=$init_pwd
rule900_timestamp=`date +%Y-%m-%d_%H-%M-%S`  ##  获取到时间戳 2023-03-21_16-33-13 
CUR_REPO_File=$CUR_REPO_PATH/repo_sync_log_$rule900_timestamp.txt

repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log
REPO_Git_Path_Check_Message=`cat repo_git_path_error_check.log | grep "Error:"`
    if [[ "$REPO_Git_Path_Check_Message" == "" ]] ; then
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "
     echo " cd $CUR_REPO_PATH &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Success___>>"
else
     echo " repo forall -c pwd  2>&1 | tee repo_git_path_error_check.log "
     echo " REPO_Git_Path_Check_Message  =  $REPO_Git_Path_Check_Message "

     echo " cd $CUR_REPO_PATH &&  cat repo_git_path_error_check.log | grep "'Error:'"   <<___Command Failed___>> <<___Path_Error__>>"
     exit
fi
rm -fr repo_git_path_error_check.log

## 获取所有的 AOSP的repo的 路径到 repo_pwd.txt
repo forall -c pwd > ./repo_git_path.txt
## 当前遍历到的 repo_pwd.txt 的 行数
gitpath_line_number=1
gitpath_min_count=500
## 获取 repo_pwd.txt 所有的行  既 当前 AOSP的 所有的 repo仓库的 个数
gitpath_all_number=`grep -n  ""   ./repo_git_path.txt | wc -l`

if [ $gitpath_all_number -lt $gitpath_min_count ] ; then
  echo " gitpath_all_number="$gitpath_all_number" 当前读取到的repo_git仓库数量小于500  似乎出现错误,请检查! repo forall -c pwd > ./repo_git_path.txt   # check error"
  exit
fi
echo "CUR_REPO_PATH="$CUR_REPO_PATH
echo "CUR_REPO_File="$CUR_REPO_File
echo "gitpath_all_number="$gitpath_all_number
echo -e "__Reset_Operation__" > $CUR_REPO_File
echo -e  >> $CUR_REPO_File
echo -e  >> $CUR_REPO_File
for gitpath_line in `cat ./repo_git_path.txt`
do
    cd $gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line
    echo "####repo_git["$gitpath_line_number"_"$gitpath_all_number"]_gitpath:"$gitpath_line >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File

    
    ### Command  获取本地分支名称
    git_local_branch_desc=`git branch -vv | grep "*" |awk -F' ' '{ print $2 }'`
    echo "__git_local_branch:"$git_local_branch_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    


    ### Command 获取本地远程主分支的第一个commitid   git rev-parse HEAD  || git rev-parse origin/master
    git_remote_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    if [[ "$git_remote_branch_desc" == "" ]] ; then
        git_remote_branch_desc="HEAD"
    fi
    git_head_commitid_desc=`git rev-parse $git_remote_branch_desc`
    echo "__git_head_commitid:"$git_head_commitid_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File

    echo "__git_head_command:" 'cd' $gitpath_line '&&' 'git rev-parse' $git_remote_branch_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
	
    ### Command 获取当前local最新提交的commitid
    git_local_first_commitid_desc=`git rev-parse HEAD`
    echo "__git_local_first_commitid:"$git_local_first_commitid_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
	
    echo "__git_local_first_command:" 'cd' $gitpath_line '&&' 'git rev-parse HEAD'  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File


	git_local_head_1_commitid_desc=`git rev-parse HEAD~1`
    echo "__git_local_head_1_commitid:"$git_local_head_1_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~1'  >> $CUR_REPO_File
	git_local_head_2_commitid_desc=`git rev-parse HEAD~2`
    echo "__git_local_head_2_commitid:"$git_local_head_2_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~2'  >> $CUR_REPO_File
	git_local_head_3_commitid_desc=`git rev-parse HEAD~3`
    echo "__git_local_head_3_commitid:"$git_local_head_3_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~3'  >> $CUR_REPO_File
	git_local_head_4_commitid_desc=`git rev-parse HEAD~4`
    echo "__git_local_head_4_commitid:"$git_local_head_4_commitid_desc" cd " $gitpath_line '&&' 'git rev-parse HEAD~4'  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File  

    ### Command 获取分支详细信息
    git_branch_vv_desc=`git branch -vv`
    echo "__git_branch_vv:"$git_branch_vv_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    

    
    ### Command 获取当前 git status 信息
    git_status_desc=`git status`
    echo "__git_status:"$git_status_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File

    ### Command 获取当前本地分支绑定的远程分支(可能不存在)
    git_master_branch_desc=`git branch -r | grep " -" |awk -F' ' '{ print $3 }'`
    echo "__git_master_branch:"$git_master_branch_desc  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    
    ### Command 获取所有分支中第一个打印的分支(一般来说是主分支)
    git_nomaster_number1_branch=`git branch -r |awk 'NR==1'`
    echo "__git_nomaster_number1_branch:"$git_nomaster_number1_branch  >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    
    
    ### Prop "Untracked files" 新创建还没有add commit到工程的 还没有任何历史记录的文件 的一种状态
    git_status_untracked=`git status | grep "Untracked files"`
    git_status_untracked_flag="false"
    if [ -n "$git_status_untracked" ]; then
        git_status_untracked_flag="true"
    else
        git_status_untracked_flag="false"
    fi
    echo "__git_status_untracked:"$git_status_untracked_flag   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
        
    ### Prop "modified:" 对当前已经存在的文件的修改的打印信息
    git_status_modified=`git status | grep "modified:"`
    git_status_modified_flag="false"
    if [ -n "$git_status_modified" ]; then
        git_status_modified_flag="true"
    else
        git_status_modified_flag="false"
    fi
    echo "__git_status_modified:"$git_status_modified_flag   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
        
        
        

	### Prop "commits"     当前已经有提交但还没上传的 一种状态
    git_status_commits=`git status | grep "commits"`  
    git_status_commits_flag="false"
    if [ -n "$git_status_commits" ]; then
        git_status_commits_flag="true"
    else
        git_status_commits_flag="false"
    fi
    echo "__git_status_uncommits:"$git_status_commits_flag   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    
    
    
    ###  Prop Your branch is ahead of   当前分支比远程主线分支多了几个提交commit的状态
    git_status_ahead_commits=`git status | grep "Your branch is ahead of"`  
    git_status_ahead_commits_flag="false"
    if [ -n "$git_status_ahead_commits" ]; then
        git_status_ahead_commits_flag="true"
    else
        git_status_ahead_commits_flag="false"
    fi
    echo "__git_status_aheadcommits:"$git_status_ahead_commits_flag   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File    


    ###  Prop Have Master Branch  当前本地分支是否有对应的远程分支的标识位
    is_have_master_branch_flag="false"
    if [ -n "$git_master_branch_desc" ]; then
        is_have_master_branch_flag="true"
    else
        is_have_master_branch_flag="false"
    fi
    echo "__git_have_master_branch:"$is_have_master_branch_flag   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File    
    echo "___________________________________________________________"   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
    

    ######### Reset Operation Area  #########
    gitpath_operation_index=1
	### 当有远程主分支  并且  本地有ahead-commit时   执行清除ahead-commit的操作 
    if [ "$git_status_ahead_commits_flag" = "true" -a  "$is_have_master_branch_flag" = "true" ]; then
       echo "__Reset_Operation__"$gitpath_operation_index   >> $CUR_REPO_File
       echo "__Reset_Operation__"$gitpath_operation_index 
       echo "git_status_ahead_commits_flag(true) && is_have_master_branch_flag(true) : git reset --hard HEAD~5 && repo sync ."   >> $CUR_REPO_File    
       echo "git_status_ahead_commits_flag(true) && is_have_master_branch_flag(true) : git reset --hard HEAD~5 && repo sync ."
       echo -e   >> $CUR_REPO_File
       gitpath_operation_index=$(($gitpath_operation_index+1))
       git reset --hard HEAD~5
       repo sync .
       repo sync .
    fi
    

	### 当有远程主分支  并且  本地有 未追踪 untrack 的文件时   执行清除untrack文件 并 更新的操作( untrack-file 无法通过 git reset命令删除)
    if [ "$git_status_untracked_flag" = "true" -a  "$is_have_master_branch_flag" = "true" ]; then
       echo "__Reset_Operation__"$gitpath_operation_index   >> $CUR_REPO_File
       echo "__Reset_Operation__"$gitpath_operation_index 
       echo "git_status_untracked_flag(true) && is_have_master_branch_flag(true) : git clean -dxf && git reset --hard HEAD~5 && repo sync ."   >> $CUR_REPO_File    
       echo "git_status_untracked_flag(true) && is_have_master_branch_flag(true) : git clean -dxf && git reset --hard HEAD~5 && repo sync ."  
       echo -e   >> $CUR_REPO_File
       gitpath_operation_index=$(($gitpath_operation_index+1))
	   # git clean -ndxf 会打印当前 所有可以 去除 untrack-file 的文件和目录
	   # git clean -dxf 去除所有的 untrack-file 
       git clean -ndxf
       git clean -dxf
       git reset --hard HEAD~5
       repo sync .
       repo sync .
    fi

	### 当有远程主分支  并且  本地有 未uncommit 的文件时   执行恢复文件的操作
    if [ "$git_status_modified_flag" = "true" -a  "$is_have_master_branch_flag" = "true" ]; then
       echo "__Reset_Operation__"$gitpath_operation_index   >> $CUR_REPO_File
       echo "__Reset_Operation__"$gitpath_operation_index 
       echo "git_status_modified_flag(true) && is_have_master_branch_flag(true) : git reset --hard HEAD~5 && repo sync ."   >> $CUR_REPO_File    
       echo "git_status_modified_flag(true) && is_have_master_branch_flag(true) : git reset --hard HEAD~5 && repo sync ."  
       echo -e   >> $CUR_REPO_File
       gitpath_operation_index=$(($gitpath_operation_index+1))
       git reset --hard HEAD~5
       repo sync .
       repo sync .
    fi

    echo -e   >> $CUR_REPO_File
    echo -e   >> $CUR_REPO_File
     gitpath_line_number=$(($gitpath_line_number+1))
 done

echo "=========================== repo sync -j2 -f   begin ==========================="  >> $CUR_REPO_File  
echo "=========================== repo sync -j2 -f   begin ==========================="
##  当前去除了所有repo git 的  uncommit和 ahead_commit 后 才开始 强制更新所有 repo git 仓库
repo sync -j2 -f 
echo "=========================== repo sync -j2 -f   end ==========================="
echo "=========================== repo sync -j2 -f   end ==========================="  >> $CUR_REPO_File  

echo " repo sync -j2 -f && mv out out_"$rule900_timestamp
echo " repo sync -j2 -f && mv out out_"$rule900_timestamp   >> $CUR_REPO_File  

echo "__________________________________Method_Out "$FUNCNAME
}




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
echo $init_shfile_name" _900_    ## AOSP_repo_foce_sync        强制更新repo仓库 比 repo sync -j2强 会有log文件 repo_sync_log_*.txt 文件生成 "
echo
echo $init_shfile_name" _901_    ## AOSP_repo_backup_record    对当前repo仓库 状态进行状态备份   会生成 repo_backup_msi.txt  repo_backup_vendor.txt 文件"
echo
echo $init_shfile_name" _902_    ## AOSP_repo_backup_operation 依据当前路径的 repo_backup.txt  来对当前 repo 进行备份恢复操作 "
echo
echo $init_shfile_name" _903_   Msi_1  Vendor_1   ## AOSP_msi_vendor_backup_operation 输入当前 Msi Vendor文件夹 对 Msi Vendor进行备份在repo_node_list 目录保存 repo_backup_msi.txt  repo_backup_vendor.txt "
echo
echo $init_shfile_name" _904_    ##  修复 repo forall -c pwd 遇到的  ManifestInvalidRevisionError   当执行 repo forall -c pwd (_901_) 执行失败时出现的 ManifestInvalidRevisionError 修复  "
echo


echo "__________________________________Method_Out "$FUNCNAME
}



function ruletipanalysis_func_0x0(){
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
            return 
            
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
ruletipanalysis_func_0x0
__Main__
exit

