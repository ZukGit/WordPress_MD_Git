#!/bin/bash


echo ═════════════════════════════ [5][1]System_Init_Area Begin ═════════════════════════════

echo _____________________________ printenv begin _____________________________

printenv

echo _____________________________ printenv end   _____________________________


init_cd=$(pwd)
init_f0=$0
init_dp0="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
init_input_0=$(basename $0 )
init_input_1=$1
init_input_2=$2
init_input_3=$3
init_input_4=$4
init_input_5=$5
init_input_6=$6
init_input_7=$7
init_input_8=$8
init_input_9=$9


init_bash_source_0=${BASH_SOURCE[0]}
file_seperator=/
init_path=${PATH}
init_home=${HOME}
init_userprofile=${HOME}
init_logname=${LOGNAME}
init_username=${USER}
init_desktop=${init_userprofile}${file_seperator}Desktop
init_zbin=${init_desktop}${file_seperator}zbin
init_lin_zbin=${init_zbin}${file_seperator}lin_zbin


echo
echo init_bash_source_0=${init_bash_source_0}
echo
echo init_cd=${init_cd}
echo
echo init_input_0=${init_input_0}
echo
echo init_f0=${init_f0}
echo
echo init_dp0=${init_dp0}
echo
echo init_path=${init_path}
echo
echo init_username=${init_username}
echo
echo init_logname=${init_logname}
echo
echo init_home=${init_home}
echo
echo init_userprofile=${init_userprofile}
echo
echo init_desktop=${init_desktop}
echo
echo init_zbin=${init_zbin}
echo
echo init_lin_zbin=${init_lin_zbin}

echo 
echo init_input_0=${init_input_0}
echo init_input_1=$1
echo init_input_2=$2
echo init_input_3=$3
echo init_input_4=$4
echo init_input_5=$5
echo init_input_6=$6
echo init_input_7=$7
echo init_input_8=$8
echo init_input_9=$9
echo ═════════════════════════════ [5][1]System_Init_Area EndXX   ═════════════════════════════


echo ═════════════════════════════ [5][2]FUNC_DEFINE_AREA Begin        ═════════════════════════════


# ======================== STRING_OPERATION Begin======================== 


function helloword_func_0x0(){
#  ======================================== helloword_func_0x0
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In helloword_func_0x0
echo hello_world
echo [helloword_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out helloword_func_0x0
}




function string_replace_func_3x1(){
#  ======================================== string_replace_func_3x1
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In string_replace_func_3x1
string_replace_dynamic_param_1=$1
string_replace_dynamic_param_2=$2
string_replace_dynamic_param_3=$3
string_replace_return_1=${string_replace_dynamic_param_1//${string_replace_dynamic_param_2}/${string_replace_dynamic_param_3}}
echo [string_replace_func_3x1 EndPrintCode]   string_replace_return_1=[${string_replace_return_1}]   param1=[$1]   param2=[$2]   param3=[$3]   
echo ______________Method_Out string_replace_func_3x1
}




function aaaa_func_0x0(){
#  ======================================== aaaa_func_0x0
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In aaaa_func_0x0
echo AAAA
echo [aaaa_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out aaaa_func_0x0
}




# ======================== STRING_OPERATION End======================== 
# ======================== FILE_OPERATION Begin======================== 


function dddd_func_2x2(){
#  ======================================== dddd_func_2x2
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In dddd_func_2x2
dddd_dynamic_param_1=$1
dddd_dynamic_param_2=$2
echo DDDD 
echo [dddd_func_2x2 EndPrintCode]   dddd_return_1=[${dddd_return_1}]   dddd_return_2=[${dddd_return_2}]   param1=[$1]   param2=[$2]   
echo ______________Method_Out dddd_func_2x2
}




function bbbb_func_3x2(){
#  ======================================== bbbb_func_3x2
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In bbbb_func_3x2
bbbb_dynamic_param_1=$1
bbbb_dynamic_param_2=$2
bbbb_dynamic_param_3=$3
echo bbbb
echo [bbbb_func_3x2 EndPrintCode]   bbbb_return_1=[${bbbb_return_1}]   bbbb_return_2=[${bbbb_return_2}]   param1=[$1]   param2=[$2]   param3=[$3]   
echo ______________Method_Out bbbb_func_3x2
}




function cccc_func_1x3(){
#  ======================================== cccc_func_1x3
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In cccc_func_1x3
cccc_dynamic_param_1=$1
echo cccc
echo [cccc_func_1x3 EndPrintCode]   cccc_return_1=[${cccc_return_1}]   cccc_return_2=[${cccc_return_2}]   cccc_return_3=[${cccc_return_3}]   param1=[$1]   
echo ______________Method_Out cccc_func_1x3
}




# ======================== FILE_OPERATION End======================== 
echo ═════════════════════════════ [5][2]FUNC_DEFINE_AREA EndXX        ═════════════════════════════


echo ═════════════════════════════ [5][3]RULE_DEFINE_AREA Begin        ═════════════════════════════


# ======================== RULE_OPERATION Begin======================== 


function rule1vxxxxx_func_0x0(){
#  ======================================== rule1vxxxxx_func_0x0
# rule_tip: ${init_input_0} _1_     ════   get rule1 xsaxsa safcakcadada
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In rule1vxxxxx_func_0x0
echo rule1_test
echo [rule1vxxxxx_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule1vxxxxx_func_0x0
}




function rule2vtest_func_0x0(){
#  ======================================== rule2vtest_func_0x0
# rule_tip: ${init_input_0} _2_     ════   get rule2 xsaxsa safcakcadada
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In rule2vtest_func_0x0
echo rule2_test
echo [rule2vtest_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule2vtest_func_0x0
}




function rule3vxsaa_func_0x0(){
#  ======================================== rule3vxsaa_func_0x0
# rule_tip: ${init_input_0} _3_        ════   get rule3 xsaxsa safcakcadada
# rule_tip: ${init_input_0} _3_ ab     ════   get rule3 xsaxsa safcakcadada
# rule_tip: ${init_input_0} _3_ cd     ════   get rule3 xsaxsa safcakcadada
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In rule3vxsaa_func_0x0
echo rule3_test
echo [rule3vxsaa_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule3vxsaa_func_0x0
}




function rule4vxsaa_func_0x0(){
#  ======================================== rule4vxsaa_func_0x0
# rule_tip: ${init_input_0} _4_        ════   get rule4 xsaxsa safcakcadada
# rule_tip: ${init_input_0} _4_ ab     ════   get rule4 xsaxsa safcakcadada
# rule_tip: ${init_input_0} _4_ cd     ════   get rule4 xsaxsa safcakcadada
# desc: 
# sample: 
# sample_out: 
echo ______________Method_In rule4vxsaa_func_0x0
#  ======================================== rule3vxsaa_func_0x0
echo rule4_test
echo [rule4vxsaa_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out rule4vxsaa_func_0x0
}




# ======================== RULE_OPERATION End======================== 
echo ═════════════════════════════ [5][3]RULE_DEFINE_AREA EndXX        ═════════════════════════════


echo ═════════════════════════════ [5][4]TIP_DEFINE_AREA Begin         ═════════════════════════════


# ======================== RULEANALYSIS_OPERATION Begin======================== 


function ruletipanalysis_func_0x0(){
#  ======================================== ruletipanalysis_func_0x0
# desc: 对当前用户输入的规则找到匹配的 rule_method 并 执行 这个 rule_method
# sample: 
# sample_out: 
echo ______________Method_In ruletipanalysis_func_0x0
if [ "${init_input_1}" == "" ]; then
echo user does not input any rule number , please  check input rule_num !
ruletipprint_func_0x0
exit 0
fi
echo user input rule number is  ${init_input_1}
init_1_length=${#init_input_1}
echo init_1_length=${init_1_length}
init_1_dynic_length=$(( ${init_1_length}-2))
echo init_1_dynic_length=${init_1_dynic_length}
rule_tip_index=${init_input_1:1:${init_1_dynic_length}}
echo rule_tip_index=${rule_tip_index} 
match_rule_func_fullname=`cat ${init_f0} | grep "function rule"${rule_tip_index}"v"`
echo match_rule_func_fullname=${match_rule_func_fullname}
match_rule_func_fullname_fix1=${match_rule_func_fullname#function} 
echo match_rule_func_fullname_fix1=${match_rule_func_fullname_fix1}
match_rule_func_fullname_fix2=${match_rule_func_fullname_fix1%%(*}
echo match_rule_func_fullname_fix2=${match_rule_func_fullname_fix2}
match_rule_func_realname=${match_rule_func_fullname_fix2}
if [ "${match_rule_func_realname}" == "" ]; then
echo we can not get rule method from user input param , param1=${init_input_1}
ruletipprint_func_0x0
exit 0
fi
echo ══════════════ ${match_rule_func_realname} begin ══════════════
${match_rule_func_realname}
echo ══════════════ ${match_rule_func_realname} endxx ══════════════
echo [ruletipanalysis_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out ruletipanalysis_func_0x0
}




# ======================== RULEANALYSIS_OPERATION End======================== 
# ======================== RULETIP_OPERATION Begin======================== 


function ruletipprint_func_0x0(){
#  ======================================== ruletipprint_func_0x0
# desc: Bussiness_Rule打印程序用于打印batrule规则序列
# sample: 
# sample_out: 
echo ______________Method_In ruletipprint_func_0x0
echo ${init_input_0} _1_     ════   get rule1 xsaxsa safcakcadada
echo ${init_input_0} _2_     ════   get rule2 xsaxsa safcakcadada
echo ${init_input_0} _3_        ════   get rule3 xsaxsa safcakcadada
echo ${init_input_0} _3_ ab     ════   get rule3 xsaxsa safcakcadada
echo ${init_input_0} _3_ cd     ════   get rule3 xsaxsa safcakcadada
echo ${init_input_0} _4_        ════   get rule4 xsaxsa safcakcadada
echo ${init_input_0} _4_ ab     ════   get rule4 xsaxsa safcakcadada
echo ${init_input_0} _4_ cd     ════   get rule4 xsaxsa safcakcadada
echo [ruletipprint_func_0x0 EndPrintCode]   output=[__empty__]  param1=[__empty__] 
echo ______________Method_Out ruletipprint_func_0x0
}




# ======================== RULETIP_OPERATION End======================== 
echo ═════════════════════════════ [5][4]TIP_DEFINE_AREA EndXX         ═════════════════════════════
echo ═════════════════════════════ [5][5]Main_Method_Area Begin ═════════════════════════════


function main(){
# desc: main enter point 
echo _________ this is main method begin _________ 
# _________________________ use test begin _________________________
ruletipprint_func_0x0
helloword_func_0x0

string_replace_func_3x1 "123456789" "789" ""
echo string_replace_return_1=${string_replace_return_1}

# _________________________ use test end   _________________________

if [ "${init_input_1}" != "" ]; then
ruletipanalysis_func_0x0   ## analysis_rule && execute_rule
fi
echo _________ this is main method end _________ 
}


main

echo ═════════════════════════════ [5][5]Main_Method_Area EndXX    ═════════════════════════════
