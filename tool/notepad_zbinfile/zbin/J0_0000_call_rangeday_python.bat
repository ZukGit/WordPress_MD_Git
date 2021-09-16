@echo off
Setlocal ENABLEDELAYEDEXPANSION
set CURPATH=%~dp0
echo CURPATH=%CURPATH%

set input_index_1=%1
ECHO input_index_1:%input_index_1%
call :iscontainstring_func_2x1 %input_index_1%  -
echo isContainString_return_1=!isContainString_return_1!
if "!isContainString_return_1!"=="false" (
echo 当前输入参数[ %init_input_2%  ]  没有包含 匹配输入格式 日期1-日期2   不包含 分隔符 [ - ]  请检查程序
GOTO:EOF
)


call :getSubStringWithEnd_func_2x1 %input_index_1%  -
echo datastr_1=!getSubStringWithEnd_return_1!
set begin_index=!getSubStringWithEnd_return_1!

call :getsubstringwithpre_func_2x1 %input_index_1%  -
echo datastr_1=!getSubStringWithEnd_return_1!  datastr_2=!getsubstringwithpre_return_1!
set end_index=!getsubstringwithpre_return_1!



FOR %%i IN (%CURPATH%*.py) DO (
set temp_content=%%i
rem ECHO  curFile === %%i
rem ECHO  temp_content_fixed1 === !temp_content!
set "temp_content=!temp_content:%CURPATH%=!"

set current_index=!temp_content:~4,8!

rem ECHO  === input_index_1:!input_index_1!      index:!current_index!   input_index_1:%input_index_1% 

if !current_index! GEQ !begin_index! (


if !current_index! LEQ !end_index! (

ECHO  temp_content_fixed2 === !temp_content!  current_index=!current_index!   datastr_1=!begin_index!  datastr_2=!end_index!

ECHO  === temp_content:!temp_content!  index:!current_index!    begindate=!begin_index!  enddate=!end_index!  Flag: Success and Run ! ===
@python  "%%i"

)

)


)

ECHO "__________________________ execute single python code begin_!begin_index!  end_!end_index! finish! __________________________"

goto:eof




:iscontainstring_func_2x1
rem ======================================== iscontainstring_func_2x1
rem desc: 检测 字符串参数一 是否包含字符串参数二  忽略两边引号 包含返回 true  不包含返回 false
rem sample: call  :iscontainstring_func_2x1  1234 12
rem sample: call  :iscontainstring_func_2x1  1234 234
rem sample: call  :iscontainstring_func_2x1  "1234" "123"
rem sample: call  :iscontainstring_func_2x1   123  321
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=[1234]   param2=[12]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=[1234]   param2=[234]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[true]   param1=["1234"]   param2=["123"]
rem sample_out: [iscontainstring_func_2x1 ]   iscontainstring_return_1=[false]   param1=[123]   param2=[321]
::SETLOCAL
echo ______________Method_In iscontainstring_func_2x1
echo  isContainString_func_2x1 input_1_param ==[%1]
echo  isContainString_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set isContainString_str=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set isContainString_matchStr=%clearStringPadding_return_1%
set isContainString_return_1=false
if not "x!isContainString_str:%isContainString_matchStr%=!"=="x%isContainString_str%" (
set isContainString_return_1=true
) else (
set isContainString_return_1=false
)
echo isContainString_return_1=[%isContainString_return_1%] param1=[%1]  param2=[%2] 
echo [iscontainstring_func_2x1 EndPrintCode]   iscontainstring_return_1=[!iscontainstring_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out iscontainstring_func_2x1
::ENDLOCAL
goto:eof



:getsubstringwithpre_func_2x1
rem ======================================== getsubstringwithpre_func_2x1
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getsubstringwithpre_func_2x1
echo  getSubStringWithPre_func_2x1 input_1_param ==[%1]
echo  getSubStringWithPre_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set getSubStringWithPre_param_str_1_nopadding=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set getSubStringWithPre_param_str_2_nopadding=%clearStringPadding_return_1%
set getSubStringWithPre_param_str_1=%1
set getSubStringWithPre_param_str_trim1=%getSubStringWithPre_param_str_1: =%
set getSubStringWithPre_param_str_2=%2
set getSubStringWithPre_param_str_trim2=%getSubStringWithPre_param_str_2: =%
set getSubStringWithPre_param_str_2_existflag=false
set getSubStringWithPre_return_1=
rem 如果第一个字符串为空 输入的要截取的字符串是空的话  那么直接返回空
echo X2_getSubStringWithPre_param_str_trim1==%getSubStringWithPre_param_str_trim1%
if "%getSubStringWithPre_param_str_trim1%"=="" (
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithPre_param_str_trim2%"=="" (
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
set isContainString_return_1=false
call :isContainString_func_2x1 %getSubStringWithPre_param_str_1%   %getSubStringWithPre_param_str_2%
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithPre_func_2x1  Str1_Param do not contain Str2_Param "
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
GOTO:EOF
)
set getStringLength_return_1=
call :getStringLength_func_1x1  %getSubStringWithPre_param_str_1_nopadding%
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1  %getSubStringWithPre_param_str_2_nopadding%
set /a str2_length=getStringLength_return_1
echo getSubStringWithPre_func_2x1  str1_length=[%str1_length%]   str2_length=[%str2_length%]   
set getInt4SubStringIndexOf_return_1=
call :getInt4SubStringIndexOf_func_2x1 "%getSubStringWithPre_param_str_1_nopadding%"  "%getSubStringWithPre_param_str_2_nopadding%"
echo getInt4SubStringIndexOf_return_1=[!getInt4SubStringIndexOf_return_1!]
rem  匹配到的索引  加上 自身的长度就等于 新的 需要返回的字符串的索引 
set /a getSubStringWithPre_begin_index=getInt4SubStringIndexOf_return_1+str2_length-1
echo getInt4SubStringIndexOf_return_1=[!getInt4SubStringIndexOf_return_1!] str2_length=[%str2_length%]  getSubStringWithPre_param_str_2_nopadding=[%getSubStringWithPre_param_str_2_nopadding%]
rem 取前四个字符串  
rem set number_str_0_4=!number_str:~0,4!  
rem 去掉 前一个字符串
rem set number_str_1=!number_str:~1!
echo  getSubStringWithPre_begin_index=[%getSubStringWithPre_begin_index%]  getSubStringWithPre_param_str_1_nopadding=[%getSubStringWithPre_param_str_1_nopadding%]
set  getSubStringWithPre_result=!getSubStringWithPre_param_str_1_nopadding:~%getSubStringWithPre_begin_index%!
echo getSubStringWithPre_result=[%getSubStringWithPre_result%]
set getSubStringWithPre_return_1=%getSubStringWithPre_result%
set getTimeNona_return_1=
call :getTimeNona_func_0x1
echo getSubStringWithPre_return_1=[%getSubStringWithPre_return_1%]   param1=[%1]  param2=[%2] Time=%getTimeNona_return_1%
rem if 1 LSS 2 echo %str1_length%小于%str2_length%

rem if 2 LSS 1 ( echo %str1_length%xx小于xx%str2_length% ) else ( echo  Continue.... )
rem  if %str1_length% LSS %str2_length% (
rem  GOTO:EOF
rem  ) else (
rem  echo  First-String-Length  Second-String-Length  ,  continue execute ! 
rem  )
echo [getsubstringwithpre_func_2x1 EndPrintCode]   getsubstringwithpre_return_1=[!getsubstringwithpre_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithpre_func_2x1
::ENDLOCAL
goto:eof



:getsubstringwithend_func_2x1
rem ======================================== getsubstringwithend_func_2x1
rem desc: 
rem sample: 
rem sample_out: 
::SETLOCAL
echo ______________Method_In getsubstringwithend_func_2x1
echo  getSubStringWithEnd_func_2x1 input_1_param ==[%1]
echo  getSubStringWithEnd_func_2x1 input_2_param ==[%2]
call :clearStringPadding_func_1x1  %1
set getSubStringWithEnd_param_str_1_nopadding=%clearStringPadding_return_1%
call :clearStringPadding_func_1x1  %2
set getSubStringWithEnd_param_str_2_nopadding=%clearStringPadding_return_1%
set getSubStringWithEnd_param_str_1=%1
set getSubStringWithEnd_param_str_trim1=%getSubStringWithEnd_param_str_1: =%
set getSubStringWithEnd_param_str_2=%2
set getSubStringWithEnd_param_str_trim2=%getSubStringWithEnd_param_str_2: =%
set getSubStringWithEnd_return_1=
rem 如果第一个字符串为空 输入的要截取的字符串是空的话  那么直接返回空
if "%getSubStringWithEnd_param_str_1_nopadding%"=="" (
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
echo x2  getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]
rem 如果第二个字符串为空 要匹配的字符串为空的话 那么也 直接返回 空
if "%getSubStringWithEnd_param_str_2_nopadding%"=="" (
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
echo x1getSubStringWithEnd_param_str_2_nopadding = [%getSubStringWithEnd_param_str_2_nopadding%]
set isContainString_return_1=false
call :isContainString_func_2x1 "%getSubStringWithEnd_param_str_1_nopadding%"   "%getSubStringWithEnd_param_str_2_nopadding%"
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getSubStringWithEnd_func_2x1  Str1_Param do not contain Str2_Param "
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
GOTO:EOF
)
set getStringLength_return_1=
call :getStringLength_func_1x1  %getSubStringWithEnd_param_str_1_nopadding%
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1  %getSubStringWithEnd_param_str_2_nopadding%
set /a str2_length=getStringLength_return_1
echo getSubStringWithEnd_func_2x1  str1_length=[%str1_length%]   str2_length=[%str2_length%]   
echo  getSubStringWithEnd_param_str_1_nopadding=[%getSubStringWithEnd_param_str_1_nopadding%]    getSubStringWithEnd_param_str_2_nopadding=[%getSubStringWithEnd_param_str_2_nopadding%]
set getInt4SubStringIndexOf_return_1=
call :getInt4SubStringIndexOf_func_2x1 "%getSubStringWithEnd_param_str_1_nopadding%"  "%getSubStringWithEnd_param_str_2_nopadding%"
rem 当前匹配的索引 -1  就是之前的 EndFlag 之前的 字符串了  
set /a getSubStringWithEnd_begin_index=getInt4SubStringIndexOf_return_1-1
echo getSubStringWithEnd_begin_index=[%getSubStringWithEnd_begin_index%]
set  getSubStringWithEnd_result=!getSubStringWithEnd_param_str_1_nopadding:~0,%getSubStringWithEnd_begin_index%!
set getSubStringWithEnd_return_1=%getSubStringWithEnd_result%
echo getSubStringWithEnd_return_1=[%getSubStringWithEnd_return_1%]   param1=[%1]   param2=[%2] 
echo [getsubstringwithend_func_2x1 EndPrintCode]   getsubstringwithend_return_1=[!getsubstringwithend_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getsubstringwithend_func_2x1
::ENDLOCAL
goto:eof





:clearstringpadding_func_1x1
rem ======================================== clearstringpadding_func_1x1
rem desc: 对当前给的字符串去掉两边的引号
rem sample: call :clearstringpadding_func_1x1 "123"
rem sample: call :clearstringpadding_func_1x1 """""AAABBB""
rem sample: call :clearstringpadding_func_1x1 "1"2"3"
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[123]   param1=["123"]
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[AAABBB]   param1=["""""AAABBB""]
rem sample_out: [clearstringpadding_func_1x1 ]    clearstringpadding_return_1=[1"2"3]   param1=["1"2"3"]
::SETLOCAL
echo ______________Method_In clearstringpadding_func_1x1
rem set number_str_0_4=!number_str:~0,4!   第一个字符
echo  clearStringPadding_func_1x1 input_1_param ==[%1]
set clearStringPadding_tempstr=%1
set clearStringPadding_index_pre=0
set clearStringPadding_index_end=-1
:clearStringPadding_prefix_Block
rem 没检测到前面的字符是 引号 那么加 step 加1
set char_pre_1=!clearStringPadding_tempstr:~%clearStringPadding_index_pre%,1!
echo  clearStringPadding_index_pre=[%clearStringPadding_index_pre%]   char_pre_1=[!char_pre_1!]  
if [^!char_pre_1!]==[^"] (
echo AA_clearStringPadding_index_pre=[%clearStringPadding_index_pre%]
set /a clearStringPadding_index_pre+=1
goto clearStringPadding_prefix_Block
)
echo clearStringPadding_index_pre=[%clearStringPadding_index_pre%]
set clearStringPadding_prestr=!clearStringPadding_tempstr:~%clearStringPadding_index_pre%!
echo clearStringPadding_prestr=[%clearStringPadding_prestr%]
:clearStringPadding_endfix_Block
rem 没检测到前面的字符是 引号 那么加 step 加1
set char_end_1=!clearStringPadding_prestr:~%clearStringPadding_index_end%,1!
echo char_end_1=[%char_end_1%]
if [^!char_end_1!]==[^"] (
set /a clearStringPadding_index_end-=1
goto clearStringPadding_endfix_Block
)
echo clearStringPadding_index_end=[%clearStringPadding_index_end%]
set /a clearStringPadding_index_end+=1
echo clearStringPadding_index_end=[%clearStringPadding_index_end%]
if !clearStringPadding_index_end! EQU 0 ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr!) else ( set clearStringPadding_pre_end_str=!clearStringPadding_prestr:~-0,%clearStringPadding_index_end%!)
echo clearStringPadding_pre_end_str=[%clearStringPadding_pre_end_str%]
set clearStringPadding_return_1=%clearStringPadding_pre_end_str%
echo clearStringPadding_return_1=[%clearStringPadding_pre_end_str%]  param1=[%1] 
echo [clearstringpadding_func_1x1 EndPrintCode]   clearstringpadding_return_1=[!clearstringpadding_return_1!]   param1=[%1]   
echo ______________Method_Out clearstringpadding_func_1x1
::ENDLOCAL
goto:eof




:getstringlength_func_1x1
rem ======================================== getstringlength_func_1x1
rem desc: 获取字符串长度 长度不包括两边的引号"
rem sample: call  :getstringlength_func_1x1  ""12345"
rem sample: call  :getstringlength_func_1x1  ""123456"""
rem sample: call  :getstringlength_func_1x1  ""123""456"""
rem sample: call  :getstringlength_func_1x1   123""456
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[123""456]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[6]   param1=[""123456"""]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[""123""456"""]
rem sample_out: [getstringlength_func_1x1 ]   getstringlength_return_1=[8]   param1=[123""456]
::SETLOCAL
echo ______________Method_In getstringlength_func_1x1
echo  getStringLength_func_1x1 input_1_param ==[%1]
set /a getStringLength_numsize = 0
call :clearStringPadding_func_1x1 %1
set getStringLength_str=%clearStringPadding_return_1%
:getStringLength_length_block
if not "!getStringLength_str!"=="" (
 set /a getStringLength_numsize+=1
 set "getStringLength_str=!getStringLength_str:~1!"
 goto getStringLength_length_block
)
set /a getStringLength_return_1=%getStringLength_numsize%
echo getStringLength_return_1=[!getStringLength_return_1!]  param1=[%1] 
echo [getstringlength_func_1x1 EndPrintCode]   getstringlength_return_1=[!getstringlength_return_1!]   param1=[%1]   
echo ______________Method_Out getstringlength_func_1x1
::ENDLOCAL
goto:eof




:getint4substringindexof_func_2x1
rem ======================================== getint4substringindexof_func_2x1
rem desc: 接受两个字符串参数 给出第二个参数字符串在第一个参数字符串中的起始位置 不存在返回-1
rem sample: call :getint4substringindexof_func_2x1  1234567890  0987
rem sample: call :getint4substringindexof_func_2x1  "1234567890"  "456"
rem sample_out: getint4substringindexof_return_1=[-1]  param1=[1234567890]  param2=[0987]
rem sample_out: getint4substringindexof_return_1=[4]  param1=["1234567890"]  param2=["456"]
::SETLOCAL
echo ______________Method_In getint4substringindexof_func_2x1
echo  getInt4SubStringIndexOf_func_2x1 input_1_param ==[%1]
echo  getInt4SubStringIndexOf_func_2x1 input_2_param ==[%2]
set getInt4SubStringIndexOf_return_1=-1
set isContainString_return_1=false
call :isContainString_func_2x1 %1  %2
rem 如果字符串参数一  不包含 字符串参数二  那么 也直接退出
if "%isContainString_return_1%"=="false" (
echo "getInt4SubStringIndexOf_func_2x1  Str1_Param do not contain Str2_Param "
echo [getint4substringindexof_func_2x1 EndPrintCode]   getint4substringindexof_return_1=[!getint4substringindexof_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getint4substringindexof_func_2x1
GOTO:EOF
)
rem  从起点开始截取 整段 整段 的 进行对比
call :clearStringPadding_func_1x1 %1
set origin_str_param1=!clearStringPadding_return_1!
echo ZZZZZorigin_str_param1=%origin_str_param1%   clearStringPadding_return_1=[%clearStringPadding_return_1%]  input1=[%1]  input2=[%2]
call :clearStringPadding_func_1x1 %2
set match_str_param2=!clearStringPadding_return_1!
echo  AAAAmatch_str_param2=[!clearStringPadding_return_1!]     input1=[%1]  input2=[%2]
rem set match_str_param2=%2
rem set origin_str_param1=%1
set getStringLength_return_1=
call :getStringLength_func_1x1  "%origin_str_param1%"
set /a str1_length=getStringLength_return_1
call :getStringLength_func_1x1   "%match_str_param2%"
set /a str2_length=getStringLength_return_1
echo str1_length=[%str1_length%]   str2_length=[%str2_length%]   input1_nopadding=[%origin_str_param1%]  input2_nopadding=[%match_str_param2%]
set /a getInt4SubStringIndexOf_step_index=0
:getInt4SubStringIndexOf_getNextStep_block:
set step_temp_str=!origin_str_param1:~0,%str2_length%!
if not "!origin_str_param1!"=="" (
set /a getInt4SubStringIndexOf_step_index+=1
echo step_temp_str=[%step_temp_str%]   str2_length=[%str2_length%]  getInt4SubStringIndexOf_step_index=[%getInt4SubStringIndexOf_step_index%] match_str_param2=[%match_str_param2%]
if "!step_temp_str!"=="%match_str_param2%" goto getInt4SubStringIndexOf_getIndexResult_block
set "origin_str_param1=!origin_str_param1:~1!"
goto getInt4SubStringIndexOf_getNextStep_block
)
:getInt4SubStringIndexOf_getIndexResult_block:
echo getInt4SubStringIndexOf_step_index=[%getInt4SubStringIndexOf_step_index%] ori_str[!origin_str_param1!]  match_str[%match_str_param2%]
echo getInt4SubStringIndexOf_return_1=[getInt4SubStringIndexOf_return_1]
set getInt4SubStringIndexOf_return_1=%getInt4SubStringIndexOf_step_index%
echo [getint4substringindexof_func_2x1 EndPrintCode]   getint4substringindexof_return_1=[!getint4substringindexof_return_1!]   param1=[%1]   param2=[%2]   
echo ______________Method_Out getint4substringindexof_func_2x1
::ENDLOCAL
goto:eof



:gettimenona_func_0x1
rem ======================================== gettimenona_func_0x1
rem desc: 获取当前的时间戳信息 包含周几 用于打印
rem sample: call :gettimenona_func_0x1
rem sample_out: [gettimenona_func_0x1 ] gettimenona_return_1=[周二 2-21-05 18:55:51.25]   param1=[__empty__]
::SETLOCAL
echo ______________Method_In gettimenona_func_0x1
set getTimeNona_return_1=%DATE:~0,4%-%DATE:~5,2%-%DATE:~8,2% %TIME:~0,2%:%TIME:~3,2%:%TIME:~6,2%.%TIME:~9,2%
echo getTimeNona_return_1=%getTimeNona_return_1%
echo [gettimenona_func_0x1 EndPrintCode] gettimenona_return_1=[!gettimenona_return_1!]   param1=[__empty__] 
echo ______________Method_Out gettimenona_func_0x1
::ENDLOCAL
goto:eof


