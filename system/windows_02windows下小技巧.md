# 查看连接过的wifi信息(包括密码)

```
// Ctrl + R            CMD       
 
for /f "skip=9 tokens=1,2 delims=:" %i in ('netsh wlan show profiles') do @echo %j | findstr -i -v echo | netsh wlan show profiles %j key=clear

```

<img src="//../zimage/system/windows/02_tip/wifi.jpg">



# 上帝模式

```
创建一个文件夹 重命名为     // PS： 可以隐藏文件夹内容作用
上帝模式.{ED7BA470-8E54-465E-825C-99712043E01C}



WIN+R打开运行输入   shell:::{ED7BA470-8E54-465E-825C-99712043E01C}     也可也打开上帝模式
```
<img src="//../zimage/system/windows/02_tip/godmode.jpg">



# 查看系统配置信息
```
Ctrl + R   // 打开命令窗口
msinfo32   // 查看系统信息
dxdiag    //  DirectX 诊断工具

```
<img src="//../zimage/system/windows/02_tip/systeminfo.jpg"><img src="//../zimage/system/windows/02_tip/diag.jpg">


# 打开系统自带截屏工具

```

Ctrl + R   // 打开命令窗口
snippingtool      // 打开系统自带截屏工具

```
<img src="//../zimage/system/windows/02_tip/snippingtool.jpg">


# 显示IP地址
```
Ctrl + R    CMD  //  打开命令窗口
route print  //显示出IP路由，将主要显示网络地址Network addres，子网掩码Netmask，网关地址Gateway addres，接口地址Interface

```
<img src="//../zimage/system/windows/02_tip/route.jpg">

# 文档自动朗读
```
在 speed.vbs 中保存下面的字符串, 引号内容就是可朗读部分

CreateObject("SAPI.SpVoice").Speak "你好，2019"   
```
<img src="//../zimage/system/windows/02_tip/speed.jpg">


# 批量创建文件夹(包括多级命令)

```
makedir.bat
@echo 请先将要新建文件夹的名称保存到 文件夹名列表.txt 中(一个文件夹名占一行)
@pause
@for /f %%a in (文件夹名列表.txt) do (md %%a)
@echo 批量新建文件夹完成
@pause

```

```
文件夹名列表.txt     \ 代表下一级目录
1\A
1\B
1\C
1\D
2\A
3\A
4\A

```
<img src="//../zimage/system/windows/02_tip/createdir.jpg">
<img src="//../zimage/system/windows/02_tip/createdir1.jpg">


# 批量安装APK脚本
AudoInstallAPK.bat
点击该文件 自动安装该文件夹下的apk文件,包括中文.apk
提示:  当前绝对路径中避免 空格 和 中文文件夹
```

@ECHO off

@REM 将adb.exe添加到PATH中
ECHO 初始化…
@SET PATH=%PATH%;%CD%\Adb
@REM SET PATH

@REM 无限循环的标签
:LOOP

ECHO 等待您插入手机…
adb wait-for-device

@REM 循环安装本目录下的APK文件
FOR %%i IN (*.apk) DO ( 
 	ECHO 正在安装：%%i
 	adb install "%%i"
 	)

ECHO 安装好了；换一台手机吧！！！
PAUSE
GOTO LOOP

@ECHO on


```
