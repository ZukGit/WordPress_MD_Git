# 安装目录文件说明

<img src="//../zimage/tool/omnipeek/1.jpg">

|  序号 | 名称  |  作用 |   |
| ------------ | ------------ | ------------ | ------------ |
|  1 | w-kg11.exe  | 注册码生成器   |   |
|  2 | Windows6.1-KB3033929-x64.msu  |  omnipeek运行必须要的补丁  |   |
| 3  | omnipeek7.8.7z  |  omnipeek抓包软件 |   |
| 4  |  wifi_anaysis.apk | 安卓APK用于查看当前热点信道质量信息  |   |
| 5  |  Ralink_Wireless_USB_Driver_v5.1.12.48.zip | A6210的混杂模式驱动，官方光盘驱动无法进入混杂模式 |   |



# 安装omnipeek软件

<img src="//../zimage/tool/omnipeek/2.jpg">

<img src="//../zimage/tool/omnipeek/3.jpg">


<img src="//../zimage/tool/omnipeek/4.jpg">

<img src="//../zimage/tool/omnipeek/5.jpg">
```
1. 由于正常安装过程中会联网,破解需要在无网环境，首先断开网络连接
2. 点击Omnipeek的setup.exe 安装软件
3. 点击 w-kg11.exe  输入版本号 【 78 】 生成 Serial Number  和 Activation Key
4. 在安装过程的  User-Name  Company-Name  Email 随意填写
5.  Serial Number 填写步骤三生成的 Serial Number 
6.  之后引导程序中选择  Activate-Key (如果此时能正常联网，那么默认就不能手动填写直接访问官网导致破解版安装失败)
7. 填写步骤三中 生成的 Activation Key
8.  一直Next  然后 安装 successful
```

# 更新A6210驱动使得网络适配器进入混杂模式


<img src="//../zimage/tool/omnipeek/6.jpg">

<img src="//../zimage/tool/omnipeek/7.jpg">


<img src="//../zimage/tool/omnipeek/8.jpg">

<img src="//../zimage/tool/omnipeek/9.jpg">

<img src="//../zimage/tool/omnipeek/10.jpg">

<img src="//../zimage/tool/omnipeek/11.jpg">

```
1. 右键我的电脑 选中  管理
2. 选中网卡适配器然后更新网卡驱动  选择 混杂模式的驱动
3. 驱动安装完成后 打开omnipeek 如果 capture-option 的 80211信道可选 那么说明 网卡的混杂模式已经打开  可以正常抓包了

```



# 抓包


```
1. 使用 wifi-analysis APK 查看 wifi热点工作信道


```