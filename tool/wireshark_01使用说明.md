
# Packet-List 数据包列表操作

## Ctrl + M  
Ctrl+M  ：在数据包列表 标记当前的帧 加深颜色以区分 或者 取消当前已经标记的帧 取消颜色

<img src="//../zimage/tool/wireshark/wireshark_01.gif">

## Shift + Ctrl + B 
## Shift + Ctrl + N 
Shift + Ctrl + B (Back)   
Shift + Ctrl + N (Next) 
在数据包列表中 这两个命令用于在 Ctrl + M  标记的数据包间前后切换
<img src="//../zimage/tool/wireshark/wireshark_03.gif">


## Ctrl + D
Ctrl+D  ： 在数据包列表  标记当前的帧为不关注 取消该帧的内容显示   或者   恢复该帧的内容显示
<img src="//../zimage/tool/wireshark/wireshark_02.gif">



## Ctrl + T 
Ctrl + T :   在数据包列表 用于标记从当前选中 数据包开始 时间为0 此后的数据包依次得到新的时间戳数据并显示
<img src="//../zimage/tool/wireshark/wireshark_04.gif">



## Ctrl + Shift + T 
Ctrl + Shift + T :  在数据包列表  对捕获包的时间戳 进行调整  其他帧依据当前帧来显示 计算后的 时间戳
<img src="//../zimage/tool/wireshark/wireshark_01.jpg">


|  序号 | 标记  | 说明  | 默认单位(秒)  |
| ------------ | ------------ | ------------ |
| 1   |  Shift all packets by |   对当前所有帧添加一个时间偏移已得到新的时间戳 | 1hh  1mm 1   |
|  2 | Set the time for packet  | 设置当前选中的时间戳为某个时间点，其余的帧会依据该时间点自动显示新的时间戳   |
|  3 |  Undo all shifts  | 取消所有设置的时间偏移值  |

### 1_设置时间偏移值
1hh    1小时
1mm    1分钟
1      1秒
<img src="//../zimage/tool/wireshark/wireshark_02.jpg">
<img src="//../zimage/tool/wireshark/wireshark_03.jpg">
<img src="//../zimage/tool/wireshark/wireshark_04.jpg">

### 2_设置当前帧的时间戳
11:11:11       设置当前的帧为11点11分11秒
<img src="//../zimage/tool/wireshark/wireshark_05.jpg">
<img src="//../zimage/tool/wireshark/wireshark_06.jpg">
<img src="//../zimage/tool/wireshark/wireshark_07.jpg">
<img src="//../zimage/tool/wireshark/wireshark_08.jpg">

演示：

<img src="//../zimage/tool/wireshark/wireshark_09.gif">



## Ctrl + Alt + C
Ctrl + Alt + C:  在数据包列表  对当前的数据包进行备注   在数据包详情窗口将绿色显示这个Command

<img src="//../zimage/tool/wireshark/wireshark_10.jpg">
<img src="//../zimage/tool/wireshark/wireshark_11.jpg">


# 搜索显示过滤操作


## Ctrl + F
Ctrl + F
<img src="//../zimage/tool/wireshark/wireshark_12.jpg">



|  搜索方式 | 说明  |   例子 |
| ------------ | ------------ | ------------ |
| Display Filter   | 表达式过滤 显示满足条件的数据包  | wlan.addr == 34:ab:37:77:17:c5 |
| Hex Value   | 输入十六进制数 对数据包进行过滤  | 34:ab:37:77:17:c5   | 
| String   | 输入字符串 对数据包进行过滤  |  Data |
| Regular Expression  | 正则表达式过滤 显示满足条件的数据包  | Auth*  |



## 显示过滤器
<img src="//../zimage/tool/wireshark/wireshark_13.jpg">



|  序列号 | 标识  |   说明 |
| ------------ | ------------ | ------------ |
| 1  | 表达式输入框  | 输入 类似 wlan.addr == 34:ab:37:77:17:c5 过滤表达式 |
| 2  | Expression  | 显示所有支持的协议 的表达式  | 
| 3  | +按钮  |  过滤表达式便签(类似于浏览器Tab标签) |


### 1_ExpressionInputBox
<img src="//../zimage/tool/wireshark/wireshark_14.jpg">


### 2_ExpressionDialog

<img src="//../zimage/tool/wireshark/wireshark_15.jpg">

### 3_addTagButton

<img src="//../zimage/tool/wireshark/wireshark_16.jpg">


# Statistics 统计分析菜单


## Capture File Properties 无线包文件属性
Ctrl + Shift + Alt + C   // 快捷打开文件属性命令

<img src="//../zimage/tool/wireshark/wireshark_20.jpg">


|  序列号 | 标识  |   说明 |
| ------------ | ------------ | ------------ |
| 1  | Packets捕获的总的数据包  |  45个数据包 |
| 2  | time span捕获的时间跨度  |  1.907秒  |
| 3  | Bytes捕获的总的字节数大小  |  6511 字节bytes  |


## Resolved Address 地址名称解析
```
Mac地址 28:6C:07:5A:F5:7C  IP地址 192.168.0.1 这类地址。
Mac前三位字节是产商标识，为了更容易对Mac地址起到辨识作用

地址解析统计hosts解析记录统计：会记录捕获过程中所有解析的域名及对应的IP地址

```

<img src="//../zimage/tool/wireshark/wireshark_23.jpg">

<img src="//../zimage/tool/wireshark/wireshark_24.jpg">

 



## Protocol Hierarchy 协议分层统计

Statistics 》Protocol Hierarchy

```
此信息显示的是抓包文件包含的所有协议的树状分支。
数据包通常会包含许多协议，有很多协议会在每个包中被统计。
End Packets，End Bytes，End Mbit/s列是该层在抓包中作为最后一层协议的统计数据，
Percentage参照的是相同协议层的百分比。


你可能注意到所有比例加到一起并不是正好等于 百分之百 , 
那是因为很多很多数据包都包含着不同分层【七层OSI协议层】的多个协议,
这导致有些相同层的不同协议被单独统计造成 
数据比例与数据包计算有些差异，不是百分百的比例
```

**【不同协议被单独统计造成 】一个数据包以协议的角度进行统计，IEEE802.11 协议在此被统计成两次 **
<img src="//../zimage/tool/wireshark/wireshark_22.jpg">



<img src="//../zimage/tool/wireshark/wireshark_19.jpg">

|Protocol协议 | 协议数据包所占百分比  |   协议数据包个数 | 字节占总字节百分比 | 协议字节数|
| ------------ | ------------ | ------------ |------------ |------------ |
| Frame帧 | 100%   | 45(总数据包)  | 100% | 6511(总字节数) |
| IEEE WLAN | 131.1%   | 59(当前使用协议数据包)  | 40.9% | 2665(当前协议字节数) |

59/45 = 1.31111 = 131.1%   （说明 最多有 59-45=14 个数据包 被WLAN层协议 重复统计）
2665/6511 = 0.40930  = 40.9%  （当前层协议涉及到的数据包 占总的字节大小百分比为 40.9% ）





|Protocol协议 | 协议数据包所占百分比  |   协议数据包个数 | 字节占总字节百分比 | 协议字节数|
| ------------ | ------------ | ------------ |------------ |------------ |
| Logical-Link  | 8.9   | 4(数据链路层数据包)  | 8.1 | 530(数据链路层字节数) |
| 802.1X Auth | 8.9   | 4(当前使用认证协议数据包)  |7.6% | 498(当前协议字节数) |

数据链路层中包含了 802.1X这个子协议 ， 所以802.1X的数据包一定包含在 数据链路层数据包中。
所以当前数据链路层只包含 802.1X  所以 两者的 数据包数量 4  所占百分比都是 7.6%
而在数据字节大小上,由于802.1X 计算时去掉了  数据链路层的表头 表尾所占的 8个字节 * 4个数据包 = 32个字节，

例如

<img src="//../zimage/tool/wireshark/wireshark_21.jpg">




---


<img src="//../zimage/tool/wireshark/wireshark_19.jpg">

|Protocol协议 | 协议数据包所占百分比  |   协议数据包个数 | 字节占总字节百分比 | 协议字节数|
| ------------ | ------------ | ------------ |------------ |------------ |
| Data帧 | 26.7%   | 12(data数据包)  | 29.4% | 1915(data字节数) |

12/45 = 0.2666666  = 26.7 %   


**Null Function( no Data) 不被当做Data帧来统计**

|序号 | 帧编号  |   内容 |  帧大小 | 帧内数据大小
| ------------ | ------------ | ------------ |------------ |------------ |
| 1 | 24  | Qos Data数据包 |  107 bytes |  44  bytes |
| 2 | 27 | Data数据包 |       109 bytes |  48  bytes |
| 3 | 29  | Qos Data数据包 |  107 bytes |  44  bytes |
| 4 | 32  | Data数据包 |      218 bytes |  157 bytes |
| 5 | 33  | Data数据包 |      238 bytes |  177 bytes |
| 6 | 34  | Data数据包 |      337 bytes |  276 bytes |
| 7 | 35  | Data数据包 |      357 bytes |  296 bytes |
| 8 | 36  | Data数据包 |      109 bytes |  48  bytes |
| 9 | 38  | Data数据包 |      113 bytes |  52  bytes |
| 10 | 39  | Data数据包 |     113 bytes |  52  bytes |
| 11 | 41  |Qos Data数据包 |  436 bytes |  373 bytes |
| 12 | 42  | Data数据包 |     409 bytes |  348 bytes |

数据帧总计大小【帧大小】:  2653 bytes 
数据帧内携带数据大小【帧内数据大小】： 1915 bytes

1915 / 2653 = 0.7218 = 72.18%  数据帧内数据占用率

1915 / 6511 = 0.2941  = 29.4%  有效数据占用率


## Conversations 会话

Statistics 》 Conversations


|  序列号 | 标识  |   说明 |
| ------------ | ------------ | ------------ |
| 1  | AddressA:     |  会话中端点A的Mac地址 |
| 2  | AddressB:     | 会话中端点B的Mac地址   | 
| 3  | Packets:      |  两端点AB会话中使用的数据包总数 |
| 4  | Bytes:        |  两端点AB会话中使用的字节总数 |
| 5  |Packets A->B   | 结点A发送给结点B的数据包数量 |
| 6  | Bytes A->B    |  结点A发送给结点B的字节数 |
| 7  | Packets B->A  |  结点B发送给结点A的数据包数量 |
| 8  | Bytes B->A    |  结点B发送给结点A的字节数  |
| 9  | Rel Start:    | 会话开始的时间戳  | 
| 10  | Duration      | 会话持续事件 |
| 11  | Bits/s A->B  | 结点A传递结点B的速率 |
| 12  | Bits/s B->A | 结点B传递结点A的速率 |



```
|  序列号 | 设备  |   Mac地址 |
| ------------ | ------------ | ------------ |
| 1  | 小米路由器3   | 28:6C:07:5A:F5:7C  |
| 2  | iPad         | 34:AB:37:77:17:C5  |
| 3  | 红米Note3    | 64:CC:2E:DE:40:C6  |
| 4  | Mac         |  14:10:9f:e5:db:e9   |
| 5  | 空气净化器  |  F0:B4:29:BB:57:5C  |
| 5  | 小米路由器5G  |  28:6c:07:5a:f5:7a  |

```

<img src="//../zimage/tool/wireshark/wireshark_18.jpg">


## Endpoints 端点

Statistics 》 Endpoints


|  序列号 | 标识  |   说明 |
| ------------ | ------------ | ------------ |
| 1  | Address:  |  Mac地址 |
| 2  | Packet:  | 发送包和接收包的总和  | 
| 3  | Bytes:  |  该端点接收与发送的字节总数 |
| 4  | Tx Packet:  |  本结点发送的包的总数 |
| 5  | Tx Bytes:  | 本结点发送的字节数 |
| 6  | Rx Packet:  |  本结点发送的字节数 |
| 7  | Rx Bytes:  |  本结点接收到的字节数 |
   

<img src="//../zimage/tool/wireshark/wireshark_17.jpg">



## Packet Legth  数据包长度信息统计
```
packet-length ：  包含了长度区间
min-val   max-val:  包含了在该长度区间内最小和最大长度的帧的数据

```
<img src="//../zimage/tool/wireshark/wireshark_25.jpg">




## IO Graph 数据发送接收时间图

```
横轴：  时间   (可选 Interval 间隔)
纵轴:   包/每100ms  
显示出了数据发送接收时间图
```
<img src="//../zimage/tool/wireshark/wireshark_26.jpg">
<img src="//../zimage/tool/wireshark/wireshark_27.jpg">



## Flow Graph  交互流图

```
概括了所有45个帧之间的相互交互情况
每一帧的交互  都画出来了

```
<img src="//../zimage/tool/wireshark/wireshark_28.jpg">