# 全屏__F11
```
全屏快捷键:   F11

```


# 查找__Ctrl+F
```

查找下一个   F3 
查找上一个   Shift+F3

查找上一个   F4 
查找下一个   Shift+F4

```


# 调整字体_ Ctrl + 鼠标滚轮
```
 Ctrl + 鼠标滚轮↑        // 大字体

 Ctrl + 鼠标滚轮↓        // 小字体
```


# 主题_设置-语言格式设置-选择主题->Navajo
```
设置-语言格式设置-选择主题->Navajo    土褐色

```

# 当前行上下移动_ Ctrl + Shift + ↑
```

Ctrl + Shift + ↑          // 当前行向上移动一行

Ctrl + Shift + ↓          // 当前行向下移动一行

```

# 快速复制一行_ Ctrl + D
```
Ctrl + D   // 快速复制一行 

```

# 双视图_点击tab标签下拉
```

移动到另一视图   √ 选中即可打开双视图
复制到另一视图
```

# 书签_F2
```
1. 双击行号进行书签  添加/删除
2. F2 切换到书签位置  或下一个书签
3. Shift + F2 切换到上一个书签
```

# notepad 默认快捷键
```
F1：   打开版本窗口  【鸡肋】【打开文件管理器】
F2:    书签
F3:    查找
F4：   查找
F5:    运行窗口
F6:    【未定义】 【自动换行 View-自动换行】 运行-》管理快捷键-》主菜单-》View-自动换行  存储为F6
F7：  打开搜索结果框
F8：   激活从视图【鸡肋】
F9:    【未定义】
F10:    【未定义】
F11:     切换全屏模式【鸡肋】
F12:     切换便签模式
```

# notepad默认环境变量
```

变量名称               含义              结果
FULL_CURRENT_PATH     文件路径名称      C:/test/HelloWorld.cs
CURRENT_DIRECTORY     文件目录          C:/test
FILE_NAME             文件全名称        HelloWorld.cs
NAME_PART             文件名称          HelloWorld
EXT_PART              文件扩展名        cs

```


# F5_运行命令窗口


## 打开当前文件目录FileManager_F5
```
1.   F5 打开运行窗口

2. 输入命令:        explorer  $(CURRENT_DIRECTORY) 

3.该命令可保存为快捷键执行
```

## 打开当前文件目录CMD窗口_F5
```
1.   F5 打开运行窗口

2. 输入命令:        cmd /K cd /d $(CURRENT_DIRECTORY) 

3.该命令可保存为快捷键执行 即可快速该路径打开CMD窗口
```




## notepad执行bat命令_F5
```
1. F5 打开运行窗口
2. 输入命令:  
    cmd /K cd /d $(CURRENT_DIRECTORY) & C:\Users\aaa\Desktop\zbin\A1.bat  $(FULL_CURRENT_PATH)

其中 C:\Users\aaa\Desktop\zbin\A1.bat 为对应的 BAT命令  $(FULL_CURRENT_PATH) 表示当前文件的全路径

```




### A0_把当前井号与资源一一对齐

```
Wscript.exe  /x %userprofile%\Desktop\zbin\A0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A0 bat】

```

```
#### 半岛铁盒
#### 最后的战役
#### 可爱女人
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>

转为

#### 半岛铁盒
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
#### 最后的战役
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
#### 可爱女人
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>

================================

#### ban_dao_tie_he
#### zui_hou_de_zhan_yi
#### ke_ai_nv_ren
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>


转为

#### ban_dao_tie_he
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ban_dao_tie_he.mp3"type="audio/mpeg"/></audio>
#### zui_hou_de_zhan_yi
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/zui_hou_de_zhan_yi.mp3"type="audio/mpeg"/></audio>
#### ke_ai_nv_ren
<audiocontrols><sourcesrc="https://raw.githubusercontent.com/ZukGit/Z_Music/master/music/chinese/zhou_jie_lun/ke_ai_nv_ren.mp3"type="audio/mpeg"/></audio>



```

#### A0.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A0.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If



```

#### A0.bat
```
@echo off
@cd %1
@javac  -cp A0_pinyin4j.jar -encoding UTF-8  A0.java 
@java -cp .;A0_pinyin4j.jar A0 %2
@exit


```



### A1_添加行号
```
Wscript.exe  /x %userprofile%\Desktop\zbin\A1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A1 bat】

```

```
aaa              01      aaa
bbb              02      bbb
ccc              03      ccc
ddd              04      ddd
eee              05      eee
fff              06      fff
ggg   ===》      07      ggg
hhh              08      hhh
jjj              09      jjj
kkk              10      kkk
lll              11      lll
mmm              12      mmm
nnn              13      nnn
bbb              14      bbb
null             15      null

```

#### commandA1
```   
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【bat】

Wscript.exe  /x %userprofile%\Desktop\zbin\A1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【vbs】
```

#### A1.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A1.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```


#### A1.bat
```
C:\Users\aaa\Desktop\TestA\A1.bat 

@echo off
@cd %1
@javac A1.java
@java A1 %2
@exit


```




### A2_对齐行(命令输出的那些)
```
Wscript.exe  /x %userprofile%\Desktop\zbin\A2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A2 bat】

```


```
   71     aaa      staff     2414                         
     3      aaa  staff     102                          
   5      aaa      staff     170                          
     5      aaa      staff     170                      
     5      aaa      staff     170                         
     1      aaa      staff     44578168   

变为

71     aaa     staff     2414         
3      aaa     staff     102          
5      aaa     staff     170          
5      aaa     staff     170          
5      aaa     staff     170          
1      aaa     staff     44578168     


```

#### A2.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A2.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```
#### A2.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A2.java
@java A2 %2
@exit

```

### A3_竖排列切换横排列(命令输出的那些)

```
Wscript.exe  /x %userprofile%\Desktop\zbin\A3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A3 bat】

```

```
1
2
3
4
5
6
7
8
9
10

转为
1 2 3 4 
5 6 7 8
9 10




```


#### A3.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A3.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A3.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A3.java
@java A3 %2
@exit


```


### A4_在每行开头以及结尾添加固定字符串
```
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"前缀"endz#"后缀" 
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"endz#"yyyy"   【A4 bat OK】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"prez#"xxxx"  
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"<audio> <source src=""endz#"" /><audio>"   【A4 bat OK 使用简单无规则】


```

```
1.在行首添加字符串
2.在行尾添加字符串
3.同时的行首以及行尾添加字符串

【1】在行首添加字符串示例(同 alt键)

abcd.mp3
1.mp3
23.mp3
456.mp3
78910.mp3

转为
<audio> <source src="abcd.mp3
<audio> <source src="1.mp3
<audio> <source src="23.mp3
<audio> <source src="456.mp3
<audio> <source src="78910.mp3



【2】在行尾添加字符串示例(由于长度不一致 所以 alt键 发挥不了作用)
<audio> <source src="abcd.mp3
<audio> <source src="1.mp3
<audio> <source src="23.mp3
<audio> <source src="456.mp3
<audio> <source src="78910.mp3
转为
<audio> <source src="abcd.mp3" /><audio>
<audio> <source src="1.mp3" /><audio>
<audio> <source src="23.mp3" /><audio>
<audio> <source src="456.mp3" /><audio>
<audio> <source src="78910.mp3" /><audio>


【3】同时的行首以及行尾添加字符串
abcd.mp3
1.mp3
23.mp3
456.mp3
78910.mp3
转为
<audio> <source src="abcd.mp3" /><audio>
<audio> <source src="1.mp3" /><audio>
<audio> <source src="23.mp3" /><audio>
<audio> <source src="456.mp3" /><audio>
<audio> <source src="78910.mp3" /><audio>

```


#### A4.vbs
```
Set args = WScript.Arguments
If args.Count = 3 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)
paramString=WScript.Arguments(2)

command = "cmd /c "+ zbinpath +"\A4.bat  " +zbinpath+"\  "+textpath+"  "+paramString
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A4.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A4.java
@echo %3 
@java A4 %2 %3

```



### A5_生成MD语法的表格依据当前数据
```

Wscript.exe  /x %userprofile%\Desktop\zbin\A5.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A5 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A5.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A5 bat】

```

```
A  B   C
1  2   3

转为

| A | B | C |
| ---- | ---- | ---- |
| 1 | 2 | 3 |


```

#### A5.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A5.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```

#### A5.bat
```
@echo off
@cd %1
@javac -encoding UTF-8 A5.java
@java A5 %2
@exit


```


### A6_对当前文件进行进行逐行的复制(剪切板中),原字符字词右移动

```
Wscript.exe  /x %userprofile%\Desktop\zbin\A6.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A6 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A6.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A6 bat】

```


```
剪切板内容:
abcd
edgh
hijk
lmn
opq
adaac  


文件内容：
12345
67890
141441
411413
1441413

转为: 
abcd       12345
edgh       67890
hijk       141441
lmn        411413
opq        1441413
adaac       

```




### A7_对当前文件中的中文进行清除(notepad)
```

Wscript.exe  /x %userprofile%\Desktop\zbin\A7.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A7 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A7.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A7 bat】


```

```
A你B我C他
A你B我C他
A你B我C他


转为

A B C
A B C
A B C


```

#### A7.vbs
```

Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A7.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If


```

#### A7.bat
```

@echo off
@cd %1
@javac A7.java
@java A7 %2
@exit

```



### A8_对当前文件夹内所有文件进行Log的分析(后续开发 定位先)(notepad check.txt分析)
```

Wscript.exe  /x %userprofile%\Desktop\zbin\A8.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A8 vbs】  解析Log
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A8.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A8 bat】 解析Log


在当前Log的文件夹内新建一个 txt文件，在该文档内执行命名  分析结果将出现在 该文档中

【持续更新】
```

 <img src="//../zimage/tool/notepad/A8_1.png">
### A9_把当前汉字转为下划线拼音形式(notepad)
```
Wscript.exe  /x %userprofile%\Desktop\zbin\A9.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A9 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A9.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A9 bat】

```



```
你我他
大家好
真的好

转为

ni_wo_ta
da_jia_hao
zhen_de_hao
=================================
中 国
美 国
俄 罗 斯
转为

zhong_guo
mei_guo
e_luo_si


```
#### A9.vbs
```
Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\A9.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If

```


#### A9.bat
```
@echo off
@cd %1
@javac  -cp A9_pinyin4j.jar -encoding UTF-8  A9.java 
@java -cp .;A9_pinyin4j.jar A9 %2
@exit

```






### B0_解析当前路径下的所有文件(非文件夹)生成<audio片段>
(对应路径的notepad打开的txt文件)

```

Wscript.exe  /x %userprofile%\Desktop\zbin\B0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【B0 bat】

```


```
文件内容为空    文件夹内文件： 蜗牛.mp3    zui_hou_de_zhan_yi.mp3   yuan_you_hui.mp3   路径为：  C:\Users\aaa\Desktop\zbin\test\yuan_you_hui.mp3

转为

#### yuan_you_hui
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\yuan_you_hui.mp3" type="audio/mpeg"/></audio>
#### zui_hou_de_zhan_yi
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\zui_hou_de_zhan_yi.mp3" type="audio/mpeg"/></audio>
#### 蜗牛
<audio controls><source src="C:\Users\aaa\Desktop\zbin\test\wo_niu.mp3" type="audio/mpeg"/></audio>



```


### B1_解析当前文件第一行内容转为二维码并自动打开((notepad第一行)
```
Wscript.exe  /x %userprofile%\Desktop\zbin\B1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B1 bat】

```

```
123456789

转为

二维码图片

```

### B2_在当前文件对选中的字符串进行翻译 (notepad剪切板)
```
Wscript.exe  /x %userprofile%\Desktop\zbin\B2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B2 bat】

```
```
对 Ctrl+C 选中到剪切板的字符串进行操作
Hello World
转为
你好世界

Good Morning
转为
早上好

```

### B3_在当前文件对选中的字符串英文进行阅读(notepad剪切板)

```
Wscript.exe  /x %userprofile%\Desktop\zbin\B3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B3 bat】

```

```
控制面板 => 小图标 => 语音识别 => 文本到语音转换 => 语音选择   Zira Desktop English   【 语音速度 】

Hello World
转为
发出声音:  Hello World

```


### B4_对当前程序开机运行(定时拍照并上传)
```
'  runas /user:administrator cmd  
'  copy %userprofile%\Desktop\zbin\B4_startup.vbs  C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\
把 vbs 放到系统默认自动回运行的目录  然后 运行 B4_startup.vbs   之后执行 B4.java 程序

zbin/ 下必须包含  repo  image_monitor 仓库

cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B4.bat %userprofile%\Desktop\zbin    【 B4 bat 】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B4_push.bat %userprofile%\Desktop\zbin\image_monitor   【B4_push.bat 】


```

### B5_对时间进行统计 记录每个人剩余时间 B5.html界面(浏览器视图)
```

B5.html 页面
https://zukgit.github.io/ZHtml/

```


### B6_对Json的txt文档进行graphviz的解析生成JavaBean类(NotePad执行)
```
{xx:arr[{"a":a}{"b":b}]}   对满足 Json格式的字符串 进行 结构图形的输出


Wscript.exe  /x %userprofile%\Desktop\zbin\B6.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B6 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B6.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B6 bat】

```

### B7_安卓批量安装_批量删除_批量备份_拍照脚本(点击执行)
```
B7_AutoBankupAPK.bat
B7_AutoInstallAndroidAPK.bat
B7_UninstallAndroidAPK.bat
B7_zTakeScreenShot.bat


```


### B8_安卓打印Log脚本(CMD下执行)
```
B8.bat                     //WIndows  本地执行脚本
B8.java                    //  持续添加ZMethod
B8_Test.java                       // 测试文件
B8_android.sh             // AOSP 根目录执行脚本
B8_javaparser.jar
B8_local.sh                // 本地Linux执行脚本

Shell 下执行  B8_android.sh  
```


### B9_立体视图幻灯片 HTML(浏览器视图)
```
B9_gif.html
B9_jpg.html

```

### C0_加密解密文件(IDE执行)
```
C0_Encryption/JIEMA
C0_Encryption/YAMA
```

### C1_Windows下一些脚本工具(点击与CMD运行)
```

C1_CapturePicture.bat       //  打开系统截图工具 snippingtool
C1_MakeDir.bat               // 批量创建文件夹   C1_MakeDir_List.txt 文件中保存文件夹的路径  1\A   1\B   2  3   
C1_MakeDir_List.txt             
C1_Router_IP.bat             // 打印当前 设备的 IP地址 配置   
C1_SystemIfo.bat               // 打开当前系统的配置信息  msinfo32   dxdiag 
C1_WifiCode.bat                // 查看当前电脑连接的wifi密码
                      

```


### C2_对Java多行字符串转为一行(当需要把方法转为字符串时)
```

public static void main(String[] args) {       
 System.out.println("Hello World!");
 }

转为字符串:

String codeLog = "    public static void main(String[] args) {        System.out.println(\"Hello World!\");		}";



Wscript.exe  /x %userprofile%\Desktop\zbin\C2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)            【C2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\C2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)    【C2 bat】


```



# F5_命令小集合
```
cmd /K   echo %OS%             // 检测 %OS% 这个环境变量  打印   Windows_NT



cmd /K   echo %userprofile%\Desktop        //  打印   C:\Users\aaa\Desktop


Wscript.exe  /x %userprofile%\Desktop\zbin\A0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A0 bat】



Wscript.exe  /x %userprofile%\Desktop\zbin\A1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A1 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A2 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【A3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  【A3 bat】



在VBS中使用下划线_代表空格     在BAT中不需要使用下划线代表空格 【A4 vbs OK】  【A4 vbs  建议使用 bat来操作  使用 空格等号<> vbs需要替换规则】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"yyyy"prez#"xxxx"     【A4 vbs OK】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"yy_yy"prez#"xx_xx"  【A4 vbs OK 】
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) endz#"后缀"prez#"前缀" 
在VBS中前后缀 使用下划线z_z代表空格blank  使用#代表等号z=  使用z[代表左尖括号<    使用 z]代表> 右尖括号 前缀后缀 
1.【z_z = 空格】 2.【z# = =等号】 3.【z[ = <】  4.【z] = >】 5.【z` = " 引号】

abcd.mp3          <audio><source src="abcd.mp3"/><audio>
1.mp3             <audio><source src="1.mp3"/><audio>
23.mp3   ===》    <audio><source src="23.mp3"/><audio>
456.mp3           <audio><source src="456.mp3"/><audio>
78910.mp3         <audio><source src="78910.mp3"/><audio>
  
Wscript.exe /x %userprofile%\Desktop\zbin\A4.vbs %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH) prez#"z[audioz]z[sourcez_zsrcz#z`"endz#"z`/z]z[audioz]"  【OK 使用困难】
1.【z_z = 空格】 2.【z# = =等号】 3.【z[ = <】  4.【z] = >】 5.【z` = " 引号】
prez#"前缀"endz#"后缀"
prez#"z[audioz]z[sourcez_zsrcz#z`"endz#"z`/z]z[audioz]"
 

cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"前缀"endz#"后缀" 
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"endz#"yyyy"   【A4 bat OK】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"prez#"xxxx"  
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  endz#"yyyy"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"xxxx"
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A4.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)  prez#"<audio> <source src=""endz#"" /><audio>"   【A4 bat OK 使用简单无规则】


Wscript.exe  /x %userprofile%\Desktop\zbin\A5.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A5 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A5.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A5 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\A6.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A6 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A6.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A6 bat】




Wscript.exe  /x %userprofile%\Desktop\zbin\A7.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A7 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A7.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A7 bat】




Wscript.exe  /x %userprofile%\Desktop\zbin\A8.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)       【A8 vbs】  解析Log
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A8.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A8 bat】 解析Log


Wscript.exe  /x %userprofile%\Desktop\zbin\A9.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【A9 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\A9.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【A9 bat】





Wscript.exe  /x %userprofile%\Desktop\zbin\B0.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B0 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B0.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)      【B0 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\B1.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B1 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B1.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B1 bat】


Wscript.exe  /x %userprofile%\Desktop\zbin\B2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B2 bat】



Wscript.exe  /x %userprofile%\Desktop\zbin\B3.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B3 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B3.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B3 bat】





cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B4.bat %userprofile%\Desktop\zbin    【 B4 bat 】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B4_push.bat %userprofile%\Desktop\zbin\image_monitor   【B4_push.bat 】


xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 B5 vbs 】 【B5.html 呵呵倒计时HTML页面】
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   【B5 bat 】


{xx:arr[{"a":a}{"b":b}]}   对满足 Json格式的字符串 进行 结构图形的输出
Wscript.exe  /x %userprofile%\Desktop\zbin\B6.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)   【B6 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\B6.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)     【B6 bat】



Wscript.exe  /x %userprofile%\Desktop\zbin\C2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)            【C2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\C2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)    【C2 bat】



xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 B7 vbs 】 【B7 安卓Bat文件批量执行脚本】
B7_AutoBankupAPK.bat
B7_AutoInstallAndroidAPK.bat
B7_UninstallAndroidAPK.bat
B7_zTakeScreenShot.bat
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   【B7 bat 】


xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 B8 vbs 】 【B8 JavaParser对Java文件添加Log】
B8_安卓打印Log脚本(CMD下执行)
B8.bat                     //WIndows  本地执行脚本
B8.java                    //  持续添加ZMethod
B8_Test.java                       // 测试文件
B8_android.sh             // AOSP 根目录执行脚本
B8_javaparser.jar
B8_local.sh                // 本地Linux执行脚本
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   【B8 bat 】





xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 B9 vbs 】 【B9_gif.html  Gif幻灯片】
B9_gif.html
B9_jpg.html
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   【B9 bat 】【B9_jpg.html  jpg幻灯片】

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 C0 vbs 】
C0_加密解密文件(IDE执行)
C0_Encryption/JIEMA
C0_Encryption/YAMA
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 C0 vbs 】



xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 C1 vbs 】
C1_Windows下一些脚本工具(点击与CMD运行)
C1_CapturePicture.bat       //  打开系统截图工具 snippingtool
C1_MakeDir.bat               // 批量创建文件夹   C1_MakeDir_List.txt 文件中保存文件夹的路径  1\A   1\B   2  3   
C1_MakeDir_List.txt             
C1_Router_IP.bat             // 打印当前 设备的 IP地址 配置   
C1_SystemIfo.bat               // 打开当前系统的配置信息  msinfo32   dxdiag 
C1_WifiCode.bat                // 查看当前电脑连接的wifi密码
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  【 C1 vbs 】                


对Java多行字符串转为一行(当需要把方法转为字符串时)
Wscript.exe  /x %userprofile%\Desktop\zbin\C2.vbs  %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)            【C2 vbs】
cmd /K cd /d %userprofile%\Desktop\zbin & %userprofile%\Desktop\zbin\C2.bat %userprofile%\Desktop\zbin  $(FULL_CURRENT_PATH)    【C2 bat】

```

