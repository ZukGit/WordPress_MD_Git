# 首页 
https://android-review.googlesource.com
https://android-review.googlesource.com/q/status:open

https://review.openstack.org/Documentation/user-search.html   【openstack 搜索释义】


Gerrit 上数据的AndroidSource数据上相差了十天左右


# 页面显示
## ActionMent页面

<img src="//../zimage/website/08_12.jpg">

## FindOwner按钮
<img src="//../zimage/website/08_13.jpg">



## Edit 链接字
<img src="//../zimage/website/08_14.jpg">

## Reply 链接字
<img src="//../zimage/website/08_15.jpg">

## 状态视图
<img src="//../zimage/website/08_16.jpg">


# List标题
```


Subject【主题】	Status【状态】     Owner【责任人】	  Assignee【被指定人】	  
Repo【仓库】 	Branch【分支】	Updated	Size【上传大小】	 
AR【API-Review】   	A【Autosubmit】	   BCO【Build-Cop-Override】
CR【Code Review】	PR【Presubmit-Ready】	PV【Presubmit-Veritified】	V【Veritified】



```

# 搜索栏搜索

## Status【状态】可选值
```
负号: -  表示查询该状态的相反的状态

https://android-review.googlesource.com/q/status:abandoned,100

```


###  status:abandoned   (已经丢弃的提交)

```

status:abandoned
-status:abandoned

```


### status:closed  (merged状态 abandoned状态的集合) 
```
status:closed
-status:closed
```

###  status:merged  （已合入状态）
```
status:merged      
-status:merged
```

###  status:open (pending状态reviewed状态集合)

```
status:open
-status:open
```

###  status:pending （等待审核的状态）
```
status:pending
-status:pending
```

###  status:reviewed  （已审核待合入的状态）

```
status:reviewed
-status:reviewed
```

###  Merge Conflict
```
Merge Conflict 说明合入存在冲突 , 两个owner的修改冲突(同一个位置的不同修改)

```



## age:1week    
reviewed 审核后时长超过1周的提交List列表
```
age:1year
age:1mon
age:1week
age:1day
age:1hr
age:1min
age:1sec

s, sec, second, seconds
m, min, minute, minutes
h, hr, hour, hours
d, day, days
w, week, weeks (1 week is treated as 7 days)
mon, month, months (1 month is treated as 30 days)
y, year, years (1 year is treated as 365 days)

https://android-review.googlesource.com/q/age:1d,100
https://android-review.googlesource.com/q/age:1d,3000                // 100(第二页)  和 3000(第三十一页) 代表显示的起始索引


```


##  change:【change-Id】
```
查看 commitId 对应的 修改
change:Icea23616ed20b63d98666e66c3ad16dbdaccb8e3


https://android-review.googlesource.com/q/change:Icea23616ed20b63d98666e66c3ad16dbdaccb8e3

```

##  commit:【commit-Id】
```
commit:ed4d7c282051a9f20f125ac00f8f8efa29cf65f6
change:Icea23616ed20b63d98666e66c3ad16dbdaccb8e3

【Commit-ID】 和 【Change-Id】不一样 位数就不一样
change:【Change-Id】
commit：【commit-Id】


```
<img src="//../zimage/website/08_01.jpg">
<img src="//../zimage/website/08_02.jpg">

##  conflicts:【commit-Id】
```
查看 commitId 对应的 修改
conflicts: Icea23616ed20b63d98666e66c3ad16dbdaccb8e3    //  没有冲突的commit    只能查看该commit的提交
conflicts: Icea975f34336d5a08cdca5c54bb5ad6931621004     // 有冲突的commit      能查看得到与该commit冲突的多个commit提交

https://android-review.googlesource.com/q/change:Icea23616ed20b63d98666e66c3ad16dbdaccb8e3

```


##  owner:elsk@google.com
```
owner:elsk@google.com status:merged

查看负责人owner的所有提交历史
https://android-review.googlesource.com/q/owner:elsk%2540google.com,1100


https://android-review.googlesource.com/q/owner:elsk%2540google.com+status:merged,900
```


##  ownerin:Anonymous Users

```
查看owner 所属组的提交记录

https://android-review.googlesource.com/q/ownerin:Anonymous+Users,100

可选值：
Anonymous Users

approvers-platform-build
approvers-platform-art
android-ndk-api-council
android-supportlib-eng-owners
approvers-brillo-intel
approvers-devtools
approvers-jack

brillo-bsp-devs
contacts-owners
devtools-dr-no-approvers
external-sepolicy-dr-no-approvers


jetstream-dev
Registered Users
skia-android-reviewers
system-sepolicy-dr-no-approvers
trusty-approvers
trusty-devs

Group的介绍
https://blog.csdn.net/chenjh213/article/details/50571190
```




##  reviewer:elsk@google.com 
```

查看 elsk@google.com   作为 owner  和 reviewer 的提交集合



https://android-review.googlesource.com/q/reviewer:elsk%2540google.com+,100

```


## project:platform/packages/apps/Settings
```
project：  指定要查看的Git 仓库

常用git 仓库

git仓库对应Gerrit地址:  https://android-review.googlesource.com/q/project:++platform/packages/apps/Settings

## WIFI
project:platform/packages/apps/Settings
project: platform/frameworks/av
project: platform/frameworks/base
project: platform/external/wpa_supplicant_8
project: platform/packages/apps/Launcher3
project: platform/system/connectivity/wificond
project: platform/system/hwservicemanager
project: platform/frameworks/opt/net/wifi
project: platform/system/nvram
project: platform/hardware/libhardware_legacy
project: platform/hardware/interfaces


## BT
project:  platform/packages/apps/Bluetooth
project:  platform/system/bt
project:  platform/external/blktrace
project:  platform/hardware/libhardware
project:  platform/system/hardware/interfaces

## NFC

project: platform/hardware/nxp/nfc
project: platform/hardware/nxp/secure_element
project: platform/packages/apps/Nfc
project: platform/system/nfc

## GPS

project: platform/hardware/qcom/gps


## FM
project: platform/packages/apps/FMRadio

```


## projects:platform/packages/apps
```
projects:【git仓库前缀】    // 查询出在该路径下的多个git仓库


projects:platform/packages/apps
projects: platform/
```


## branch: master
```
在分支名为  master的分支上的 commit 
branch: master       //  显示所有git仓库中 master分支的修改

project:platform/packages/apps/Settings branch: master   //显示在Settings这一个git仓库中 master分支的修改
```
<img src="//../zimage/website/08_03.jpg">


##  topic:"master" (status:open OR status:merged)
```
Topic是一个把一组changes归集到某个分类里的方法。比如有一个很大的feature需要由若干个人一起来完成。
每个人都在自己的change里面工作。那么就可以把这一组changes全部都通过一个topic归集到一起。
通过git push的方式添加带有某个topic的change的方法是在push的reference后面加上%topic=topicname参数，或者在reference后面加上/topicname

git push HEAD:/refs/for/master%topic=#1
git push HEAD:/refs/for/master/#1


例如:
topic:"call-redirection-role-and-test" 
topic:"toybox-touch" (status:open OR status:merged)
```
<img src="//../zimage/website/08_04.jpg">


## ref:  这个不是git的规则，而是gerrit的规则，
```
https://www.cnblogs.com/0616--ataozhijia/p/4165052.html

关于refs/for/ 和refs/heads/

1. refs的概念是gerrit的规则     这个不是git的规则
2. refs/for/xxxx   需要经过code review之后才可以提交 
   refs/heads/xxx  不需要code review

Push Merge Commit权限通常必须以refs/for/为前缀,
例如refs/for/refs/heads/BRANCH. refs/heads/BRANCH作为补充


Special and magic references
refs/heads/*和refs/tags/*是Git常用的引用命名空间, 一个用来存储分支一个用来标签 
在refs/*命名空间下的引用都是有效的,Gerrit在refs/*有一些特殊用处的命名空间和引用

Special references
这些特殊的引用的内容由Gerrit生成或者包含重要的项目配置信息

refs/changes/*          #用于存储审查的补丁
#获取某个补丁集需要审查序号和补丁集序号
#'refs/changes/    '<last two digits of change number>/ <change number>/ <patch set number>
refs/meta/config        #项目配置的分支
refs/meta/dashboards/*　　#
refs/notes/review       #保存代码审查信息的分支

Magic references
refs/for/<branch ref>  #进行代码审查时需要提交代码到这个命名空间
refs/publish/*         #和refs/for/*命名空间作用一样
refs/drafts/*          #用于草案代码审查,和refs/for/*的区别在于只有部分人可见



```
<img src="//../zimage/website/08_05.png">


##  file:WifiStateMachine.java

```
file:WifiStateMachine.java   查看文件的修改记录
file:WifiStateMachine.java  branch:sdk-release
file:WifiStateMachine.java  branch:master

https://android-review.googlesource.com/q/file:WifiStateMachine.java++branch:master

```
<img src="//../zimage/website/08_06.jpg">


##  message:WifiStateMachine
```
在提交的commit-message 搜搜关键字
message:【commit-message】

message:wlan

```

<img src="//../zimage/website/08_07.jpg">
<img src="//../zimage/website/08_09.jpg">

##  comment:wlan
```
在review审核过程的 comment 搜搜关键字
comment:【comment-message】

comment:wlan

```

<img src="//../zimage/website/08_08.jpg">



## bug:113373927

```

bug:113373927 
搜索解决该 问题 bugID 的相关 commit


```
<img src="//../zimage/website/08_10.jpg">



##  label:Code-Review=2

```
lable 是一个固定的公式字符串

label:Code-Review=2
label:Code-Review=+2
label:Code-Review+2
label:Code-Review=1
label:Code-Review>=1
label:Code-Review=2,user=elsk@google.com 
label:Code-Review=1,user=elsk@google.com
label:Code-Review=0,user=elsk@google.com

```

##  added:>50    deleted:>200
```

added:>50   // 提交行数大于 50 行新增的那些提交
deleted:>200  // 提交行数大于 200行删除的那些提交

```