
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


# Wireshark 列表表头Column设置

##  增加Wiredhark显示表头项
Edit 》Preference 》Appearaance 》Columns 》+ 》 dropdownSelect 下拉框
快捷键:       Ctrl + Shift + P 
<img src="//../zimage/tool/wireshark/wireshark_30.jpg">


## 默认表头显示项

| Number | Title  | Type  |  desc  |  Example  |
| ------ | ------ | ------ | ------ |------ |
| 1  | No.  | Number | 帧序列  |  1,2,3..999...N |
| 2 |  Time | Time(format as specified) | 时间(自定义格式)   |
| 3  | Source  | Source address  | 源地址  | 192.168.0.2 或者 14:13:20:cd:02 |
| 4 |  Destination |  Destination address | 目的地址 | Broadcast IP地址 Mac地址 |
| 5  | Protocol  | Protocol  | 协议类型  | ARP  EAP 802.11 TCP ... |
| 6 | Length  | Packet length(bytes)  | 该帧字节数据大小  | 20--1554 |
| 7 | Info  | infomation  | 该帧对应的类型描述信息  | data SN=xx FN=xx Flag=........ |

<img src="//../zimage/tool/wireshark/wireshark_29.jpg">

## 表头可选项列表
| Number | Title  | Type  |  desc  |  Example  |
| ------ | ------ | ------ | ------ |------ |
| 1  | No.  | Number | 帧序列  |  1,2,3..999...N |
| 2 |  Time | Time(format as specified) | 时间(自定义格式)   |
| 3  | Source  | Source address  | 源地址  | 192.168.0.2 或者 14:13:20:cd:02 |
| 4 |  Destination |  Destination address | 目的地址 | Broadcast IP地址 Mac地址 |
| 5  | Protocol  | Protocol  | 协议类型  | ARP  EAP 802.11 TCP ... |
| 6 | Length  | Packet length(bytes)  | 该帧字节数据大小  | 20--1554 |
| 7 | Info  | infomation  | 该帧对应的类型描述信息  | data SN=xx FN=xx Flag=........ |
| 8  |自定义名称(VLAN id)|   802.1Q VLAN id  | xx | 空  |
| 9  |自定义名称(AbDate)#   | Absolute date, as YYYY-MM-DD, and time | 标准时间 | 2017-05-28 23:38:07.62513  |
| 10  |自定义名称(AbDate1)#    |Absolute date, as YYYY/DOY, and time  |一年第几天格式  | 2017/148 23:38:07.62513  |
| 11  |自定义名称(AbTime)   |Absolute time  | 只显示时间点 |  23:38:07.62513  |
| 12  |自定义名称(Cisco VSAN)   |Cisco VSAN  | xx | 空  |
| 13  |自定义名称(CumulativeBytes)   |Cumulative Bytes  | 字节数累计递增  | 166,565...7093    |
| 14  |自定义名称(Custom)   |Custom  | xx | 空  |
| 15  |自定义名称(Call)   |DCE/RPC call (cn_call_id / dg_seqnum)  | xx |  空 |
| 16  |自定义名称(DeltaTime1)   |Delta time displayed  | 时间增量 | 0.00  0.0121 0.02434 递增  |
| 17  |自定义名称(DeltaTime2)   |Delta time  | 同上DeltaTime |  同上DeltaTime |
| 18  |自定义名称(DstAddr_R)   |Dest addr (resolved)  | 目的地址(解析vendor) | XiaomiEl_5a:f5:7c  |
| 19  |自定义名称(DstAddr_U)   |Dest addr (unresolved)  |目的地址(不解析vendor)  | 28::6c:07:5a:f5:7c   |
| 20  |自定义名称(DestPort_R)  |Dest port (resolved)  | 目的端口(解析端口) | 空 |
| 21  |自定义名称(DestPort_U)   |Dest port (unresolved)  | 目的端口(不解析端口) | 空  |
| 22(同4)  |自定义名称(DstAddr) | Destination address | 目的地址 | Broadcast IP地址 Mac地址 |
| 23  |自定义名称(DestPort)   |Destination port  | xx | 空  |
| 24  |自定义名称(ExpertInfo)   |Expert Info Severity  | xx |  Note |
| 25  |自定义名称(FW-1)   |FW-1 monitor if/direction  | xx | 空  |
| 26  |自定义名称(Freq)   |Frequency/Channel  | 信道频率 | 5745MHz  |
| 27  |自定义名称(Hard_destAddr)  |Hardware dest addr  | Mac目的地址(解析Vendor) |  XiaomiEl_5a:f5:7c  |
| 28  |自定义名称(Hard_srcAddr)   |Hardware src addr  | Mac源地址(解析Vendor) |  xx |
| 29  |自定义名称(Hard_destAddr_R)   |Hw dest addr (resolved) | Mac目的地址(解析Vendor) | XiaomiEl_5a:f5:7c  |
| 30  |自定义名称(Hard_destAddr_U)   |Hw dest addr (unresolved)  |Mac目的地址(未解析Vendor)  | 28::6c:07:5a:f5:7c  |
| 31  |自定义名称(Hard_srcAddr_R)    |Hw src addr (resolved)  | Mac源地址(解析Vendor)  |  XiaomiEl_5a:f5:7c  |
| 32  |自定义名称(Hard_srcAddr_U)    |Hw src addr (unresolved) | Mac源地址(未解析Vendor) |  28::6c:07:5a:f5:7c  |
| 33  |自定义名称(RSSI)   |IEEE 802.11 RSSI | 信号强度  | -46dBm  |
| 34  |自定义名称(TX_RATE)   |IEEE 802.11 TX rate | 传输速率 | 24.0  |
| 35  |自定义名称(IP_DSCP)   |IP DSCP Value  | xx | 空  |
| 36(同7)  |自定义名称(INFO)   | infomation  | 帧信息 | 默认项  |
| 37  |自定义名称(Net_destAddr_R)  |Net dest addr (resolved)  | 网络目的地址(解析Vendor) | xxx  |
| 38  |自定义名称(Net_destAddr_U)    |Net dest addr (unresolved)  |  网络目的地址(未解析Vendor) | xxx  |
| 39  |自定义名称(Net_srcAddr_R)    |Net src addr (resolved)  | 网络源地址(解析Vendor) | xxx  |
| 40  |自定义名称(Net_srcAddr_U)    |Net src addr (unresolved)  | 网络源地址(未解析Vendor) | xxx  |
| 41  |自定义名称(Net_destAddr)   |Network dest addr  |  网络目的地址(解析Vendor) | xx  |
| 42  |自定义名称(Net_srcAddr)    |Network src addr  | 网络源地址(解析Vendor) | xx  |
| 43(同1)  |自定义名称(No.)   |Number  | 帧标识 | 1...1000 默认项  |
| 44(同6)  |自定义名称(PacketLength字节)   |Packet length (bytes)  | xx |  xx |
| 45(同5)  |自定义名称(Protocol)   |Protocol  | 帧协议 | 默认项  |
| 46  |自定义名称(RelativeTime)  |Relative time  | 相对时间 | 0.1  0.2 累加时间  |
| 47(同3)  |自定义名称(SrcAddr)   |Source address  | xx |  xx |
| 48  |自定义名称(SrcPort)   |Source port  | 源地址端口(解析port) |  xxx |
| 49  |自定义名称(SrcAddr_R)   |Src addr (resolved)  | 源地址(解析Vendor)  |  XiaomiEl_5a:f5:7c |
| 50  |自定义名称(SrcAddr_U)    |Src addr (unresolved)  | 源地址(未解析Vendor)  | 28::6c:07:5a:f5:7c  |
| 51  |自定义名称(SrcPort_R)    |Src port (resolved)  | 源地址端口(解析port)  | xxx  |
| 52  |自定义名称(SrcPort_U)     |Src port (unresolved)  |源地址端口(未解析port)  |  xxx |
| 53  |自定义名称(TEI)   |TEI  | xx | 空  |
| 54  |自定义名称(UTC_DATE)  |UTC date, as YYYY-MM-DD, and time  | UTC时间 | 2017-05-28 15:38:09.6472  |
| 55  |自定义名称(UTC_DATE2)   |UTC date, as YYYY/DOY, and time  | 年内第几天格式 | 2017/148 15:38:09.6472  |
| 56  |自定义名称(UTC_TIME)   |UTC time  | xx | 2017-05-28  |
| 57(同2)  |自定义名称(Time)   | Time(format as specified) | 时间(自定义格式)   |xx|

<img src="//../zimage/tool/wireshark/wireshark_29.jpg">
<img src="//../zimage/tool/wireshark/wireshark_31.jpg">
<img src="//../zimage/tool/wireshark/wireshark_32.jpg">
<img src="//../zimage/tool/wireshark/wireshark_33.jpg">
<img src="//../zimage/tool/wireshark/wireshark_34.jpg">

## 推荐列表序号
```
1.No. 编号
2.Time 
11.AbTime
16.DeltaTime1
3.Source
50.SrcAddr_U
19.DstAddr_U
4.Destination
5.Protocol 
6.Length
13.CumulativeBytes
7.info
26.Freq
33.RSSI
34.TX_RATE
24.ExpertInfo
```
<img src="//../zimage/tool/wireshark/wireshark_35.jpg">



# wireshark 过滤表达式

## 帧分类过滤列表
|  帧类型 |  帧分类  |过滤器语法  |   
| ------------ | ------------ |  ------------ | 
| ManagementFrame管理帧                  |  管理帧 wlan.fc.type == 0         | wlan.fc.type == 0   | 
| ControlFrame控制帧                     |  控制帧  wlan.fc.type == 1        |  wlan.fc.type == 1  | 
| Data数据帧                             |  数据帧  wlan.fc.type == 2        |  wlan.fc.type == 2  | 
| Association Request 关联请求帧         |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x00  | 
| Association Response 关联响应帧        |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x01  | 
| ReAssociation Request 重关联请求帧     |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x02  | 
| ReAssociation Response 重关联响应帧    |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x03  | 
|Probe Request 探测周围无线网络的探测帧   |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x04  | 
|Probe Response 无线网络响应的探测回复帧  |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x05  | 
| Beacon 无线网络持续发送的HeatBeat心跳帧 |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x08  | 
| Disassociate 取消关联帧                |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x0A  | 
| Authentication 认证帧                  |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x0B  | 
| Deauthentication 取消认证帧            |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x0C  |
| Action Frame 触发测量动作帧            |  管理帧 wlan.fc.type == 0         |   wlan.fc.type_subtype == 0x0D  |  
| Block ACK Request 块确认请求帧         |  控制帧  wlan.fc.type == 1        |   wlan.fc.type_subtype == 0x18  |  
| Block ACK  块确认响应帧                |  控制帧  wlan.fc.type == 1        |   wlan.fc.type_subtype == 0x19  | 
| Power save poll 省电轮询帧             |  控制帧  wlan.fc.type == 1        |   wlan.fc.type_subtype == 0x1A  |  
| Request to Send RTS 请求发送帧         |  控制帧  wlan.fc.type == 1        |   wlan.fc.type_subtype == 0x1B  |    
| Clear to Send CTS 准予发送帧           |  控制帧  wlan.fc.type == 1        |   wlan.fc.type_subtype == 0x1C  |  
|ACK  数据帧确认接收帧                   |  控制帧  wlan.fc.type == 1         |   wlan.fc.type_subtype == 0x1D  |  
|Content free period end 无竞争结束帧    |  控制帧  wlan.fc.type == 1         |   wlan.fc.type_subtype == 0x1E  | 
| 携带data数据帧                         |  数据帧  wlan.fc.type == 2         |   wlan.fc.type_subtype == 0x20 |  
|NULL data空数据帧                       |  数据帧  wlan.fc.type == 2         |   wlan.fc.type_subtype == 0x24  | 
|Qos data  Qos服务质量数据帧             |  数据帧  wlan.fc.type == 2         |   wlan.fc.type_subtype == 0x28  | 
|Null Qos data  空Qos服务质量数据帧      |  数据帧  wlan.fc.type == 2         |   wlan.fc.type_subtype == 0x2C  | 


## 所有可能帧组合

```
 2位比特 决定帧分类  00管理帧   01控制帧   10数据帧   11扩展帧
管理帧  wlan.fc.type == 0 
 wlan.fc.type_subtype == 0x00_____【Association Request】
 wlan.fc.type_subtype == 0x01_____【Association Response】
 wlan.fc.type_subtype == 0x02_____【Reassociation Request】
 wlan.fc.type_subtype == 0x03_____【Reassociation Response】
 wlan.fc.type_subtype == 0x04_____【Probe Request】
 wlan.fc.type_subtype == 0x05_____【Probe Response】
 wlan.fc.type_subtype == 0x06_____
 wlan.fc.type_subtype == 0x07_____
 wlan.fc.type_subtype == 0x08_____【Beacon】
 wlan.fc.type_subtype == 0x09_____【ATIM 通知传输指示消息】
 wlan.fc.type_subtype == 0x0A_____【Disassociate】
 wlan.fc.type_subtype == 0x0B_____【Authentication】
 wlan.fc.type_subtype == 0x0C_____【Deauthentication】
 wlan.fc.type_subtype == 0x0D_____【Action Frame 】
 wlan.fc.type_subtype == 0x0E_____
 wlan.fc.type_subtype == 0x0F_____【★ Malformed Packet】


ATIM:announcement traffic indication message【通知传输指示消息】 IBSS中没有接入点，无法依赖接入点缓存帧， 
一个STA缓存了另一个STA的数据【数据是传送给另外一个处于Sleep状态的STA】的缓存帧时发送

Beacon:  Beacon 可携带通知sleep状态的STA有数据缓存在AP的相关信息，STA使用TIM(traffic indication map)传输指示映射来确定





控制帧 wlan.fc.type == 1 
 wlan.fc.type_subtype == 0x10_____
 wlan.fc.type_subtype == 0x11_____
 wlan.fc.type_subtype == 0x12_____
 wlan.fc.type_subtype == 0x13_____
 wlan.fc.type_subtype == 0x14_____
 wlan.fc.type_subtype == 0x15_____
 wlan.fc.type_subtype == 0x16_____
 wlan.fc.type_subtype == 0x17_____
 wlan.fc.type_subtype == 0x18_____【Block ACK Request】 
 wlan.fc.type_subtype == 0x19_____【Block ACK Response】 
 wlan.fc.type_subtype == 0x1A_____【Power save poll】
 wlan.fc.type_subtype == 0x1B_____【 RTS 】
 wlan.fc.type_subtype == 0x1C_____【 CTS 】
 wlan.fc.type_subtype == 0x1D_____【 ACK 】
 wlan.fc.type_subtype == 0x1E_____【Content free period end CF-End】
 wlan.fc.type_subtype == 0x1F_____【CF-End + CF-ACK】


Power save poll: 省电轮询-由从休眠状态醒来的STA发出
CF-End： 无竞争周期结束
CF-ACK:  无竞争周期确认



数据帧   wlan.fc.type == 2 
 wlan.fc.type_subtype == 0x20_____【 Data 】
 wlan.fc.type_subtype == 0x21_____【 Data + CF-ACK 】
 wlan.fc.type_subtype == 0x22_____【 Data + CF-Poll 】
 wlan.fc.type_subtype == 0x23_____【 Data + CF-ACK + CF-Poll 】
 wlan.fc.type_subtype == 0x24_____【NULL data 未传输数据】
 wlan.fc.type_subtype == 0x25_____【CF-ACK 未传送数据】
 wlan.fc.type_subtype == 0x26_____【CF-Poll 未传送数据】
 wlan.fc.type_subtype == 0x27_____【 Data + CF-ACK + CF-Poll 】
 wlan.fc.type_subtype == 0x28_____【Qos data 】【EAPOP-Key1234】
 wlan.fc.type_subtype == 0x29_____【Qos data + CF-ACK】
 wlan.fc.type_subtype == 0x2A_____【Qos data + CF-Poll】
 wlan.fc.type_subtype == 0x2B_____【 QosData + CF-ACK + CF-Poll 】
 wlan.fc.type_subtype == 0x2C_____【Null Qos data 未传输数据】
 wlan.fc.type_subtype == 0x2D_____【 Qos CF-ACK 未传输数据】
 wlan.fc.type_subtype == 0x2E_____【 Qos CF-Poll 未传输数据】
 wlan.fc.type_subtype == 0x2F_____【 Qos CF-ACK CF-Poll 未传输数据】


未知扩展帧
数据帧   wlan.fc.type == 3
 wlan.fc.type_subtype == 0x30
 wlan.fc.type_subtype == 0x31
 wlan.fc.type_subtype == 0x32
 wlan.fc.type_subtype == 0x33
 wlan.fc.type_subtype == 0x34
 wlan.fc.type_subtype == 0x35
 wlan.fc.type_subtype == 0x36
 wlan.fc.type_subtype == 0x37
 wlan.fc.type_subtype == 0x38
 wlan.fc.type_subtype == 0x39
 wlan.fc.type_subtype == 0x3A
 wlan.fc.type_subtype == 0x3B
 wlan.fc.type_subtype == 0x3C
 wlan.fc.type_subtype == 0x3D
 wlan.fc.type_subtype == 0x3E
 wlan.fc.type_subtype == 0x3F


```

## wlan.flags =0xff 所有帧可能组合
```
Flags: .........     Flags: ........C   -->    Flags: opmPRMFTC
1  【 o 】 Order 序号域_长帧分段严格编号
2  【 p 】 protect enctypt frame
3  【 m 】 more Data in AP
4  【 P 】 Power_Save
5  【 R 】 Retry
6  【 M 】 More_Frame 长帧分段
7  【 F 】 Download_Fang 
8  【 T 】 Upload_Translate
9  【 C 】 Check_correct 【 . 】 Correct_wrong



 · Protocol Version（协议版本）：通常为0；
     · Type（类型域）和Subtype（子类型域）：共同指出帧的类型；
     · To DS：表明该帧是BSS向DS发送的帧；
     · From DS：表明该帧是DS向BSS发送的帧；
     · More Frag：用于说明长帧被分段的情况，是否还有其它的帧；
     · Retry（重传域）：用于帧的重传，接收STA利用该域消除重传帧；
     · Pwr Mgt（能量管理域）：1：STA处于power_save模式；0：处于active模式；
     · More Data in AP（更多数据域）：1：至少还有一个数据帧要发送给STA ；
     · Protected Frame： 1：帧体部分包含被密钥套处理过的数据；否则：0；
     · Order（序号域）：1：长帧分段传送采用严格编号方式；否则：0。
	 
wlan.flags == 0x00   Flags: ........C
C:  Check  Correct
---------------------------------
wlan.flags == 0x01    Fags:  .......TC

T:  toDS   upload-frame ↑
C:  Check  Correct
---------------------------------
wlan.flags == 0x02   Flags: ......F.C

F :  From DS： download-frame  ↓
C:  Check  Correct
---------------------------------
wlan.flags == 0x03   Flags: ......FT.    【x】

F :  From DS： download-frame  ↓
T :  toDS   upload-frame ↑
. :  最后是. 不是C  说明  Check Wrong
---------------------------------
wlan.flags == 0x04  Flags: .....M...    【x】

M: More Frag   长帧分段 
. :  最后是. 不是C  说明  Check Wrong
---------------------------------
wlan.flags == 0x05     【x】

---------------------------------
wlan.flags == 0x06     【x】

---------------------------------
wlan.flags == 0x07     【x】

---------------------------------

wlan.flags == 0x08  Flags:   ....R...C

R:  Retry
C:  Check  Correct
---------------------------------
wlan.flags == 0x09   Flags:  ....R..TC
R:  Retry
T:  toDS   upload-frame ↑
C:  Check  Correct

---------------------------------
wlan.flags == 0x10   Flags: ...P....C              


P:  PWR MGT--STA will go to sleep 
C:   Check  Correct
---------------------------------
wlan.flags == 0x11    Flags: ...P...TC


P:  PWR MGT--STA will go to sleep 
T:  toDS   upload-frame ↑
C:   Check  Correct
---------------------------------

wlan.flags == 0x12    【x】

---------------------------------
wlan.flags == 0x13    【x】

---------------------------------
wlan.flags == 0x14    【x】

---------------------------------
wlan.flags == 0x15    【x】

---------------------------------
wlan.flags == 0x16    Flags: ...P.MF.C    IEEE 802.11 Beamforming Report Poll

P :  PWR MGT--STA will go to sleep 
M :  More Frag   长帧分段 
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------

wlan.flags == 0x17      Flags: ...P.MFTC    IEEE 802.11 QoS CF-Poll (No Data)

P :  PWR MGT--STA will go to sleep 
M :  More Frag   长帧分段 
F :  From DS： download-frame  ↓        AP -to-AP 
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x18   Flags: ...PR...C     IEEE 802.11 QoS CF-Ack + CF-Poll (No data)

P :  PWR MGT--STA will go to sleep 
R :  Retry
C :  Check  Correct
---------------------------------
wlan.flags == 0x19    Flags: ...PR..TC    IEEE 802.11 QoS Null function (No data)


P :  PWR MGT--STA will go to sleep 
R :  Retry
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x1a    【x】

---------------------------------
wlan.flags == 0x1b    Flags: ...PR.FTC   IEEE 802.11 Unrecognized (Reserved frame)


P :  PWR MGT--STA will go to sleep 
R :  Retry
F :  From DS： download-frame  ↓  
T :  toDS   upload-frame ↑
C :  Check  Correct

---------------------------------
wlan.flags == 0x1c    【x】

---------------------------------
wlan.flags == 0x1d    【x】

---------------------------------
wlan.flags == 0x1e    【x】

---------------------------------
wlan.flags == 0x1f   【x】

---------------------------------
wlan.flags == 0x20   Flags: ..m.....C   IEEE 802.11 VHT NDP Announcement

m :  More Data in AP
C :  Check  Correct
---------------------------------
wlan.flags == 0x21   【x】

---------------------------------
wlan.flags == 0x22      Flags: ..m...F.C    IEEE 802.11 QoS Null function (No data)


m :  More Data in AP
F :  From DS： download-frame  ↓  
C :  Check  Correct
---------------------------------
wlan.flags == 0x23   Flags: ..m...FTC      IEEE 802.11 QoS CF-Ack + CF-Poll (No data)

m :  More Data in AP
F :  From DS： download-frame  ↓  
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x24   【x】

---------------------------------

wlan.flags == 0x25   【x】

---------------------------------

wlan.flags == 0x26   【x】

---------------------------------
wlan.flags == 0x27   【x】

---------------------------------
wlan.flags == 0x28   【x】

---------------------------------
wlan.flags == 0x29   【x】

---------------------------------
wlan.flags == 0x2a   【x】  Flags: ..m.R.F.C   IEEE 802.11 QoS Null function (No data)


m :  More Data in AP
R :  Retry
F :  From DS： download-frame  ↓  
C :  Check  Correct
---------------------------------

wlan.flags == 0x2b   【x】

---------------------------------
wlan.flags == 0x2c   【x】

---------------------------------
wlan.flags == 0x2d   【x】

---------------------------------

wlan.flags == 0x2e   【x】

---------------------------------

wlan.flags == 0x2f   【x】

---------------------------------
wlan.flags == 0x2f   【x】
---------------------------------

wlan.flags == 0x30   【x】
---------------------------------
wlan.flags == 0x31   【x】
---------------------------------
wlan.flags == 0x32   【x】
---------------------------------
wlan.flags == 0x33   【x】
---------------------------------
wlan.flags == 0x34   【x】
---------------------------------
wlan.flags == 0x35   【x】
---------------------------------
wlan.flags == 0x36   【x】
---------------------------------
wlan.flags ==  0x37  Flags: ..mP.MFTC   IEEE 802.11 CF-Ack/Poll (No data)

m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
M : More Frag   长帧分段 
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x38   【x】
---------------------------------

wlan.flags ==  0x39   Flags: ..mPR..TC    IEEE 802.11 QoS Null function (No data)

m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
R :  Retry
T:  toDS   upload-frame ↑
C :  Check  Correct

---------------------------------
wlan.flags == 0x3a   【x】
---------------------------------
wlan.flags == 0x3b   【x】
---------------------------------
wlan.flags == 0x3c   【x】
---------------------------------
wlan.flags == 0x3d   【x】
---------------------------------
wlan.flags == 0x3e   【x】
---------------------------------
wlan.flags == 0x3f  【x】
---------------------------------
wlan.flags == 0x40  【x】
---------------------------------
wlan.flags ==0x41  Flags: .p.....TC

p :  protected frame： encrypted frame
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags ==0x42  Flags: .p....F.C

p :  protected frame： encrypted frame
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0x43   Flags: .p....FTC     IEEE 802.11 QoS Data + CF-Acknowledgment


p :  protected frame： encrypted frame
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x44  【x】
---------------------------------
wlan.flags == 0x45  【x】
---------------------------------
wlan.flags == 0x46  【x】
---------------------------------
wlan.flags == 0x47  【x】
---------------------------------
wlan.flags == 0x48  【x】
---------------------------------
wlan.flags == 0x49   Flags: .p..R..TC   IEEE 802.11 QoS Data

p :  protected frame： encrypted frame
R :  Retry
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
0x4a  Flags: .p..R.F.C

p :  protected frame：  encrypted frame
R :  Retry
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0x4b  【x】
---------------------------------
wlan.flags == 0x4c  【x】
---------------------------------
wlan.flags == 0x4d  【x】
---------------------------------
wlan.flags == 0x4e  【x】
---------------------------------
wlan.flags == 0x4f  【x】
---------------------------------
wlan.flags == 0x50  【x】
---------------------------------
wlan.flags == 0x51     Flags: .p.P...TC   IEEE 802.11 QoS Data

p :  protected frame：  encrypted frame
P :  PWR MGT--STA will go to sleep 
T :  toDS   upload-frame ↑
C :  Check  Correct

---------------------------------

wlan.flags == 0x52  【x】
---------------------------------
wlan.flags == 0x53  【x】
---------------------------------
wlan.flags == 0x54  【x】
---------------------------------
wlan.flags == 0x55  【x】
---------------------------------
wlan.flags == 0x56  【x】
---------------------------------
wlan.flags == 0x57  【x】
---------------------------------
wlan.flags == 0x58  【x】
---------------------------------
wlan.flags == 0x58  【x】
---------------------------------
wlan.flags == 0x59    Flags: .p.PR..TC     IEEE 802.11 QoS Data

p :  protected frame：  encrypted frame
P :  PWR MGT--STA will go to sleep 
R :  Retry
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x5a  【x】
---------------------------------
wlan.flags == 0x5b    Flags: .p.PR.FTC      IEEE 802.11 Acknowledgement (No data)

p :  protected frame：  encrypted frame
P :  PWR MGT--STA will go to sleep 
R :  Retry
F :  From DS： download-frame  ↓
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x5c   【x】
---------------------------------
wlan.flags == 0x5d   【x】
---------------------------------
wlan.flags == 0x5e   【x】
---------------------------------
wlan.flags == 0x5f   【x】
---------------------------------
wlan.flags == 0x60  【x】
---------------------------------
wlan.flags == 0x61   【x】  
---------------------------------
wlan.flags == 0x62      Flags: .pm...F.C

p :  protected frame：  encrypted frame
m :  More Data in AP
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0x63  【x】
---------------------------------
wlan.flags == 0x64  【x】
---------------------------------
wlan.flags == 0x65     Flags: .pm..M.TC

p :  protected frame：  encrypted frame
m :  More Data in AP
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x66  【x】
---------------------------------
wlan.flags == 0x67  【x】
---------------------------------
wlan.flags == 0x68  【x】
---------------------------------
wlan.flags == 0x69  【x】
---------------------------------
wlan.flags == 0x6a  【x】
---------------------------------
wlan.flags == 0x6b  【x】
---------------------------------
wlan.flags == 0x6c   【x】
---------------------------------
wlan.flags == 0x6d   【x】
---------------------------------
wlan.flags == 0x6e     Flags: .pm.RMF.C       IEEE 802.11 QoS Data + CF-Acknowledgment,

p :  protected frame：  encrypted frame
m :  More Data in AP
R :  Retry
M :  More Frag   长帧分段 
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0x6f   【x】
---------------------------------
wlan.flags == 0x70  【x】
---------------------------------
wlan.flags == 0x71   【x】  
---------------------------------
wlan.flags == 0x72  【x】
---------------------------------
wlan.flags == 0x73   Flags: .pmP..FTC        IEEE 802.11 Data + CF-Ack + CF-Poll

p :  protected frame：  encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
F :  From DS： download-frame  ↓
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x74  【x】
---------------------------------
wlan.flags == 0x75  【x】
---------------------------------
wlan.flags == 0x76  【x】
---------------------------------
wlan.flags == 0x77  【x】
---------------------------------
wlan.flags == 0x78  【x】
---------------------------------
wlan.flags == 0x79    Flags:  .pmPR..T.                

p :  protected frame：  encrypted frame
m :  More Data
P :  PWR MGT--STA will go to sleep 
R :  Retry
T :  toDS   upload-frame ↑
. :  最后是. 不是C  说明  Check Wrong
---------------------------------
wlan.flags == 0x7a  【x】
---------------------------------
wlan.flags == 0x7b  【x】
---------------------------------
wlan.flags == 0x7c   【x】
---------------------------------
wlan.flags == 0x7d   【x】
---------------------------------
wlan.flags == 0x7e   【x】
---------------------------------
wlan.flags == 0x7f   【x】
---------------------------------
wlan.flags == 0x80  【x】
---------------------------------
wlan.flags == 0x81   【x】  
---------------------------------
wlan.flags == 0x82  【x】
---------------------------------
wlan.flags == 0x83  【x】
---------------------------------
wlan.flags == 0x84  【x】
---------------------------------
wlan.flags == 0x85  【x】
---------------------------------
wlan.flags == 0x86  【x】
---------------------------------
wlan.flags == 0x87  【x】
---------------------------------
wlan.flags == 0x88  Flags: o...R...C     IEEE 802.11 Beamforming Report Poll

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
R :  Retry
C :  Check  Correct
---------------------------------
wlan.flags == 0x89  【x】
---------------------------------
wlan.flags == 0x8a  【x】
---------------------------------
wlan.flags == 0x8b  【x】
---------------------------------
wlan.flags == 0x8c   【x】
---------------------------------
wlan.flags == 0x8d   【x】
---------------------------------
wlan.flags == 0x8e   【x】
---------------------------------
wlan.flags == 0x8f   【x】
---------------------------------
wlan.flags == 0x90  【x】
---------------------------------
wlan.flags == 0x91   Flags: o..P...TC     IEEE 802.11 QoS Null function (No data)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
P :  PWR MGT--STA will go to sleep 
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x92  【x】
---------------------------------
wlan.flags == 0x93  【x】
---------------------------------
wlan.flags == 0x94  【x】
---------------------------------
wlan.flags == 0x95  【x】
---------------------------------
wlan.flags == 0x96  【x】
---------------------------------
wlan.flags == 0x97  【x】
---------------------------------
wlan.flags == 0x98     Flags: o..PR...C       IEEE 802.11 QoS CF-Ack + CF-Poll (No data)


o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
P :  PWR MGT--STA will go to sleep 
R :  Retry
C :  Check  Correct
---------------------------------
wlan.flags == 0x99      Flags: o..PR..TC    IEEE 802.11 Measurement Pilot

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
P :  PWR MGT--STA will go to sleep 
R :  Retry
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0x9a  【x】
---------------------------------
wlan.flags == 0x9b  【x】
---------------------------------
wlan.flags == 0x9c   【x】
---------------------------------
wlan.flags == 0x9d   【x】
---------------------------------
wlan.flags == 0x9e   【x】
---------------------------------
wlan.flags == 0x9f   【x】
---------------------------------
wlan.flags == 0xa0  【x】
---------------------------------
wlan.flags == 0xa1   Flags: o.m....TC   IEEE 802.11 Action,

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
m :  More Data in AP
T :  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xa2  【x】
---------------------------------
wlan.flags == 0xa3  【x】
---------------------------------
wlan.flags == 0xa4  【x】
---------------------------------
wlan.flags == 0xa5  【x】
---------------------------------
wlan.flags == 0xa6  【x】
---------------------------------
wlan.flags == 0xa7  【x】
---------------------------------
wlan.flags == 0xa8  【x】
---------------------------------
wlan.flags == 0xa9  【x】
---------------------------------
wlan.flags == 0xaa  Flags: o.m.R.F.C   IEEE 802.11 QoS Null function (No data)  (数据帧)    wlan.fc.type == 2 
                    Flags: o.m.R.F.C   IEEE 802.11 Action No Ack (管理帧)                   wlan.fc.type == 0 
o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
m :  More Data in AP
R :  Retry
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0xab  【x】
---------------------------------
wlan.flags == 0xac   【x】
---------------------------------
wlan.flags == 0xad   【x】
---------------------------------
wlan.flags == 0xae   【x】
---------------------------------
wlan.flags == 0xaf   【x】
---------------------------------
wlan.flags == 0xb0  【x】
---------------------------------
wlan.flags == 0xb1   【x】  
---------------------------------
wlan.flags == 0xb2  【x】
---------------------------------
wlan.flags == 0xb3  【x】
---------------------------------
wlan.flags == 0xb4  【x】
---------------------------------
wlan.flags == 0xb5  【x】
---------------------------------
wlan.flags == 0xb6  【x】
---------------------------------
wlan.flags == 0xb7  【x】
---------------------------------
wlan.flags == 0xb8   Flags: o.mPR...C     IEEE 802.11 QoS CF-Poll (No Data)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
R :  Retry
C :  Check  Correct
---------------------------------
wlan.flags == 0xb9    【x】 
---------------------------------
wlan.flags == 0xba  【x】
---------------------------------
wlan.flags == 0xbb   Flags: o.mPR.FTC   IEEE 802.11 Acknowledgement (No data)     wlan.fc.type == 2  数据帧
                     Flags: o.mPR.FTC   IEEE 802.11 Beamforming Report Poll       wlan.fc.type == 1  控制帧

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
R :  Retry
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xbc   【x】
---------------------------------
wlan.flags == 0xbd        Flags: o.mPRM.TC       IEEE 802.11 QoS Null function (No data)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
R :  Retry
M :  More Frag   长帧分段
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xbe   【x】
---------------------------------
wlan.flags == 0xbf   【x】
---------------------------------
wlan.flags == 0xc0  【x】
---------------------------------
wlan.flags == 0xc1   【x】  
---------------------------------
wlan.flags == 0xc2  【x】
---------------------------------
wlan.flags == 0xc3  【x】
---------------------------------
wlan.flags == 0xc4  Flags: op...M..C      IEEE 802.11 QoS Data

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
M :  More Frag   长帧分段
C :  Check  Correct
---------------------------------
wlan.flags == 0xc5  【x】
---------------------------------
wlan.flags == 0xc6  【x】
---------------------------------
wlan.flags == 0xc7  【x】
---------------------------------
wlan.flags == 0xc8  【x】
---------------------------------
wlan.flags == 0xc9    Flags: op..R..TC  IEEE 802.11 QoS Null function (No data)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
R :  Retry
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xca  【x】
---------------------------------
wlan.flags == 0xcb  【x】
---------------------------------
wlan.flags == 0xcc   【x】
---------------------------------
wlan.flags == 0xcd   【x】
---------------------------------
wlan.flags == 0xce   【x】
---------------------------------
wlan.flags == 0xcf   【x】
---------------------------------
wlan.flags == 0xd0  【x】
---------------------------------
wlan.flags == 0xd1   【x】  
---------------------------------
wlan.flags == 0xd2  【x】
---------------------------------
wlan.flags == 0xd3  【x】
---------------------------------
wlan.flags == 0xd4  【x】
---------------------------------
wlan.flags == 0xd5  【x】
---------------------------------
wlan.flags == 0xd6  【x】
---------------------------------
wlan.flags == 0xd7  Flags: op.P.MFTC      IEEE 802.11 QoS CF-Poll (No Data)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
P :  PWR MGT--STA will go to sleep 
M :  More Frag   长帧分段
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xd8  【x】
---------------------------------
wlan.flags == 0xd9  【x】
---------------------------------
wlan.flags == 0xda  【x】
---------------------------------
wlan.flags == 0xdb  【x】
---------------------------------
wlan.flags == 0xdc     Flags: op.PRM..C     IEEE 802.11 QoS Data + CF-Poll

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
P :  PWR MGT--STA will go to sleep 
R :  Retry
M :  More Frag   长帧分段
C :  Check  Correct

---------------------------------
wlan.flags == 0xdd   【x】
---------------------------------
wlan.flags == 0xde   【x】
---------------------------------
wlan.flags == 0xdf   【x】
---------------------------------
wlen.flegs == 0xe0  【x】
---------------------------------
wlen.flegs == 0xe1   【x】  
---------------------------------
wlen.flegs == 0xe2      Flags: opm...F.C    IEEE 802.11 VHT NDP Announcement

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlen.flegs == 0xe3  Flags: opm...FTC     IEEE 802.11 Association Request

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlen.flegs == 0xe4  【x】
---------------------------------
wlen.flegs == 0xe5  【x】
---------------------------------
wlen.flegs == 0xe6     Flags: opm..MF.C       IEEE 802.11 Unrecognized (Reserved frame)

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
M :  More Frag   长帧分段
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlen.flegs == 0xe7     Flags: opm..MFTC         IEEE 802.11 Probe Request

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
M :  More Frag   长帧分段
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct

---------------------------------
wlen.flegs == 0xe8  【x】
---------------------------------
wlen.flegs == 0xe9     Flags: opm.R..TC     IEEE 802.11 QoS Data

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
R :  Retry
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlen.flegs == 0xea   Flags: opm.R.F.C       IEEE 802.11 QoS Null function (No data)


o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
R :  Retry
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlen.flegs == 0xeb    Flags: opm.R.FTC       IEEE 802.11 Beacon frame

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
R :  Retry
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlen.flegs == 0xec   【x】
---------------------------------
wlen.flegs == 0xed   【x】
---------------------------------
wlen.flegs == 0xee   【x】
---------------------------------
wlen.flegs == 0xef        Flags: opm.RMFTC          IEEE 802.11 Probe Request

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
R :  Retry
M :  More Frag   长帧分段
F :  From DS： download-frame  ↓
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xf0  【x】
---------------------------------
wlan.flags == 0xf1   Flags: opmP...TC     IEEE 802.11 Association Response
                     Flags: opmP...TC     IEEE 802.11 QoS CF-Ack + CF-Poll 
o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xf2  Flags: opmP..F.C    IEEE 802.11 QoS CF-Poll (No Data)


o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to   Correct
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0xf3  【x】
---------------------------------
wlan.flags == 0xf4     Flags: opmP.M..C   IEEE 802.11 Action 

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to sleep 
M :  More Frag   长帧分段
C :  Check
---------------------------------
wlan.flags == 0xf5  【x】
---------------------------------
wlan.flags == 0xf6  【x】
---------------------------------
wlan.flags == 0xf7  【x】
---------------------------------
wlan.flags == 0xf8  【x】
---------------------------------
wlan.flags == 0xf9     Flags: opmPR..TC       IEEE 802.11 Measurement Pilot

o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to   Correct
R :  Retry
T:  toDS   upload-frame ↑
C :  Check  Correct
---------------------------------
wlan.flags == 0xfa  Flags: opmPR.F.C       IEEE 802.11 QoS Data


o :  Order 序号域_长帧分段严格编号 1-长帧分段传送采用严格编号方式  否则为0
p :  protected frame： encrypted frame
m :  More Data in AP
P :  PWR MGT--STA will go to   Correct
R :  Retry
F :  From DS： download-frame  ↓
C :  Check  Correct
---------------------------------
wlan.flags == 0xfb  【x】
---------------------------------
wlan.flags == 0xfc   【x】
---------------------------------
wlan.flags == 0xfd   【x】
---------------------------------
wlan.flags == 0xfe   【x】
---------------------------------
wlan.flags == 0xff   【x】
---------------------------------
```

## Mac地址过滤  信道过滤 2.4G 5G 信道
```
wlan.bssid ==  28:6c:07:5a:f5:7c              // 热点BSSID 过滤
radiotap.channel.freq == 5745                 // 信道过滤


2.4G  wifi信道
radiotap.channel.freq == 2412             // 【1 信道编号】 2412MHz
radiotap.channel.freq == 2417             // 【2】2417MHz
radiotap.channel.freq == 2422             // 【3】 2422MHz
radiotap.channel.freq == 2427             // 【4】 2427MHz
radiotap.channel.freq == 2432             // 【5】 2432MHz
radiotap.channel.freq == 2437             // 【6】 2437MHz
radiotap.channel.freq == 2442             // 【7】 2442MHz
radiotap.channel.freq == 2447             // 【8】 2447MHz
radiotap.channel.freq == 2452             // 【9】 2452MHz
radiotap.channel.freq == 2457            // 【10】 2457MHz
radiotap.channel.freq == 2462            // 【11】 2462MHz
radiotap.channel.freq == 2467            // 【12】 2467MHz
radiotap.channel.freq == 2472            // 【13】 2472MHz



5G wifi信道
radiotap.channel.freq == 5035            // 【7 】 5035MHz
radiotap.channel.freq == 5040            // 【8 】 5040MHz
radiotap.channel.freq == 5045            // 【9 】 5045MHz
radiotap.channel.freq == 5055            // 【11】 5055MHz
radiotap.channel.freq == 5060            // 【12】 5060MHz
radiotap.channel.freq == 5080            // 【16】 5080MHz  
radiotap.channel.freq == 5170            // 【34】 5170MHz   
radiotap.channel.freq == 5180            // 【36】 5180MHz     中国OK
radiotap.channel.freq == 5190            // 【38】 5190MHz
radiotap.channel.freq == 5200            // 【40】 5200MHz     中国OK
radiotap.channel.freq == 5210            // 【42】 5210MHz
radiotap.channel.freq == 5220            // 【44】 5220MHz     中国OK
radiotap.channel.freq == 5230            // 【46】 5230MHz
radiotap.channel.freq == 5240            // 【48】 5240MHz
radiotap.channel.freq == 5250            // 【50】 5250MHz   【DFS__Begin__1】
radiotap.channel.freq == 5260            // 【52】 5260MHz
radiotap.channel.freq == 5280            // 【56】 5280MHz
radiotap.channel.freq == 5300            // 【60】 5300MHz
radiotap.channel.freq == 5320            // 【64】 5320MHz
radiotap.channel.freq == 5320            // 【70】 5350MHz   【DFS__End__1】

【DFS 检测的区段  5250MHz~5350MHz   5470MHz~5725MHz 欧洲军事雷达频段 DFS需主动避免 】
radiotap.channel.freq == 5470            // 【94 】 5470 MHz  【DFS__Begin__2】

radiotap.channel.freq == 5500            // 【100 】 5500 MHz
radiotap.channel.freq == 5520            // 【104 】 5520 MHz
radiotap.channel.freq == 5540            // 【108 】 5540 MHz
radiotap.channel.freq == 5560            // 【112 】 5560 MHz
radiotap.channel.freq == 5580            // 【116 】 5580 MHz
radiotap.channel.freq == 5600            // 【120 】 5600 MHz
radiotap.channel.freq == 5620            // 【124 】 5620 MHz
radiotap.channel.freq == 5640            // 【128 】 5640 MHz
radiotap.channel.freq == 5660            // 【132 】 5660 MHz
radiotap.channel.freq == 5680            // 【136 】 5680 MHz
radiotap.channel.freq == 5700            // 【140 】 5700 MHz
radiotap.channel.freq == 5700            // 【145 】 5725 MHz  【DFS__End__2】
radiotap.channel.freq == 5745            // 【149 】 5745 MHz   中国OK
radiotap.channel.freq == 5765            // 【153 】 5765 MHz   中国OK
radiotap.channel.freq == 5785            // 【157 】 5785 MHz   中国OK
radiotap.channel.freq == 5805            // 【161 】 5805 MHz   中国OK
radiotap.channel.freq == 5825            // 【165 】 5825 MHz   中国OK

radiotap.channel.freq == 4915            // 【183 】 4915 MHz
radiotap.channel.freq == 5520            // 【184 】 4920 MHz
radiotap.channel.freq == 4925            // 【185 】 4925 MHz
radiotap.channel.freq == 4935            // 【187 】 4935 MHz
radiotap.channel.freq == 4940            // 【188 】 4940 MHz
radiotap.channel.freq == 4945            // 【189 】 4945 MHz
radiotap.channel.freq == 4960            // 【192 】 4960 MHz
radiotap.channel.freq == 4980            // 【196 】 4980 MHz



```


# wifi详细帧

## 管理帧

## 控制帧


## 数据帧



# wireshark源码
 wireshark源码地址:   https://github.com/wireshark/wireshark

```
过滤地址
/epan/dissectors/packet-ieee80211.c:25733:      {"Hardware address", "wlan.addr", }


```

## IEEE802.11 过滤条件统计
```
1  :【 wlan.fc 】    	{"Frame Control Field"     【 wlan.fc  】
2  :【 wlan.fc.version 】    	{"Version"     【 wlan.fc.version  】
3  :【 wlan.fc.type 】    	{"Type"     【 wlan.fc.type  】
4  :【 wlan.fc.subtype 】    	{"Subtype"     【 wlan.fc.subtype  】
5  :【 wlan.fc.type_subtype 】    	{"Type/Subtype"     【 wlan.fc.type_subtype  】
6  :【 wlan.fc.extension 】    	{"Control Frame Extension"     【 wlan.fc.extension  】
7  :【 wlan.flags 】    	{"Flags"     【 wlan.flags  】
8  :【 wlan.fc.ds 】    	{"DS status"     【 wlan.fc.ds  】
9  :【 wlan.fc.tods 】    	{"To DS"     【 wlan.fc.tods  】
10  :【 wlan.fc.fromds 】    	{"From DS"     【 wlan.fc.fromds  】
11  :【 wlan.fc.frag 】    	{"More Fragments"     【 wlan.fc.frag  】
12  :【 wlan.fc.retry 】    	{"Retry"     【 wlan.fc.retry  】
13  :【 wlan.analysis.retransmission 】    	{"Retransmission"     【 wlan.analysis.retransmission  】
14  :【 wlan.analysis.retransmission_frame 】    	{"Retransmission of frame"     【 wlan.analysis.retransmission_frame  】
15  :【 wlan.fc.pwrmgt 】    	{"PWR MGT"     【 wlan.fc.pwrmgt  】
16  :【 wlan.fc.moredata 】    	{"More Data"     【 wlan.fc.moredata  】
17  :【 wlan.fc.protected 】    	{"Protected flag"     【 wlan.fc.protected  】
18  :【 wlan.fc.order 】    	{"Order flag"     【 wlan.fc.order  】
19  :【 wlan.aid 】    	{"Association ID"     【 wlan.aid  】
20  :【 wlan.duration 】    	{"Duration"     【 wlan.duration  】
21  :【 wlan.da 】    	{"Destination address"     【 wlan.da  】
22  :【 wlan.da_resolved 】     	{"Destination address (resolved)"     【 wlan.da_resolved  】 FT_STRING,
23  :【 wlan.sa 】    	{"Source address"     【 wlan.sa  】
24  :【 wlan.sa_resolved 】     	{"Source address (resolved)"     【 wlan.sa_resolved  】 FT_STRING,
25  :【 wlan.addr 】    	{"Hardware address"     【 wlan.addr  】
26  :【 wlan.addr_resolved 】    	{ "Hardware address (resolved)"     【 wlan.addr_resolved  】 FT_STRING,
27  :【 wlan.ra 】    	{"Receiver address"     【 wlan.ra  】
28  :【 wlan.ra_resolved 】      	{"Receiver address (resolved)"     【 wlan.ra_resolved  】 FT_STRING, BASE_NONE,
29  :【 wlan.ta 】    	{"Transmitter address"     【 wlan.ta  】
30  :【 wlan.ta_resolved 】    	{"Transmitter address (resolved)"     【 wlan.ta_resolved  】 FT_STRING,
31  :【 wlan.bssid 】    	{"BSS Id"     【 wlan.bssid  】
32  :【 wlan.bssid_resolved 】      	{"BSS Id (resolved)"     【 wlan.bssid_resolved  】 FT_STRING, BASE_NONE, NULL,
33  :【 wlan.staa 】    	{"STA address"     【 wlan.staa  】
34  :【 wlan.staa_resolved 】      	{"STA address (resolved)"     【 wlan.staa_resolved  】 FT_STRING, BASE_NONE, NULL,
35  :【 wlan.frag 】    	{"Fragment number"     【 wlan.frag  】
36  :【 wlan.seq 】    	{"Sequence number"     【 wlan.seq  】
37  :【 wlan.mesh.control_field 】    	{"Mesh Control Field"     【 wlan.mesh.control_field  】
38  :【 wlan.qos 】    	{"Qos Control"     【 wlan.qos  】
39  :【 wlan.qos.tid 】    	{"TID"     【 wlan.qos.tid  】
40  :【 wlan.qos.priority 】    	{"Priority"     【 wlan.qos.priority  】
41  :【 wlan.qos.eosp 】    	{"EOSP"     【 wlan.qos.eosp  】
42  :【 wlan.qos.bit4 】    	{"QoS bit 4"     【 wlan.qos.bit4  】
43  :【 wlan.qos.ack 】    	{"Ack Policy"     【 wlan.qos.ack  】
44  :【 wlan.qos.amsdupresent 】    	{"Payload Type"     【 wlan.qos.amsdupresent  】
45  :【 wlan.qos.txop_limit 】    	{"TXOP Limit"     【 wlan.qos.txop_limit  】
46  :【 wlan.qos.ps_buf_state 】    	{"QAP PS Buffer State"     【 wlan.qos.ps_buf_state  】
47  :【 wlan.qos.buf_state_indicated 】    	{"Buffer State Indicated"     【 wlan.qos.buf_state_indicated  】
48  :【 wlan.qos.highest_pri_buf_ac 】    	{"Highest-Priority Buffered AC"     【 wlan.qos.highest_pri_buf_ac  】
49  :【 wlan.qos.qap_buf_load 】    	{"QAP Buffered Load"     【 wlan.qos.qap_buf_load  】
50  :【 wlan.qos.txop_dur_req 】    	{"TXOP Duration Requested"     【 wlan.qos.txop_dur_req  】
51  :【 wlan.qos.queue_size 】    	{"Queue Size"     【 wlan.qos.queue_size  】
52  :【 wlan.fcs 】    	{"Frame check sequence"     【 wlan.fcs  】
53  :【 wlan.fcs.status 】    	{"FCS Status"     【 wlan.fcs.status  】
54  :【 wlan.fragment.overlap 】    	{"Fragment overlap"     【 wlan.fragment.overlap  】
55  :【 wlan.fragment.overlap.conflict 】    	{"Conflicting data in fragment overlap"     【 wlan.fragment.overlap.conflict  】
56  :【 wlan.fragment.multipletails 】    	{"Multiple tail fragments found"     【 wlan.fragment.multipletails  】
57  :【 wlan.fragment.toolongfragment 】    	{"Fragment too long"     【 wlan.fragment.toolongfragment  】
58  :【 wlan.fragment.error 】    	{"Defragmentation error"     【 wlan.fragment.error  】
59  :【 wlan.fragment.count 】    	{"Fragment count"     【 wlan.fragment.count  】
60  :【 wlan.fragment 】    	{"802.11 Fragment"     【 wlan.fragment  】
61  :【 wlan.fragments 】    	{"802.11 Fragments"     【 wlan.fragments  】
62  :【 wlan.reassembled_in 】    	{"Reassembled 802.11 in frame"     【 wlan.reassembled_in  】
63  :【 wlan.reassembled.length 】    	{"Reassembled 802.11 length"     【 wlan.reassembled.length  】
64  :【 wlan.wep.iv 】    	{"Initialization Vector"     【 wlan.wep.iv  】
65  :【 wlan.wep.weakiv 】    	{"Weak IV"     【 wlan.wep.weakiv  】
66  :【 wlan.tkip.extiv 】    	{"TKIP Ext. Initialization Vector"     【 wlan.tkip.extiv  】
67  :【 wlan.ccmp.extiv 】    	{"CCMP Ext. Initialization Vector"     【 wlan.ccmp.extiv  】
68  :【 wlan.wep.key 】    	{"Key Index"     【 wlan.wep.key  】
69  :【 wlan.wep.icv 】    	{"WEP ICV"     【 wlan.wep.icv  】
70  :【 wlan.analysis.pmk 】    	{"PMK"     【 wlan.analysis.pmk  】
71  :【 wlan.analysis.kck 】    	{"KCK"     【 wlan.analysis.kck  】
72  :【 wlan.analysis.kek 】    	{"KEK"     【 wlan.analysis.kek  】
73  :【 wlan.analysis.tk 】    	{"TK"     【 wlan.analysis.tk  】
74  :【 wlan.analysis.gtk 】    	{"GTK"     【 wlan.analysis.gtk  】
75  :【 wlan.ba.control 】    	{"Block Ack Control"     【 wlan.ba.control  】
76  :【 wlan.ba.control.ackpolicy 】    	{"BA Ack Policy"     【 wlan.ba.control.ackpolicy  】
77  :【 wlan.ba.control.ba_type 】    	{"BA Type"     【 wlan.ba.control.ba_type  】
78  :【 wlan.ba.control.reserved 】    	{"Reserved"     【 wlan.ba.control.reserved  】
79  :【 wlan.ba.basic.tidinfo 】    	{"TID for which a Basic BlockAck frame is requested"     【 wlan.ba.basic.tidinfo  】
80  :【 wlan.ba.multi_sta.aid11 】    	{"AID11"     【 wlan.ba.multi_sta.aid11  】
81  :【 wlan.ba.multi_sta.ack_type 】    	{"Ack Type"     【 wlan.ba.multi_sta.ack_type  】
82  :【 wlan.ba.multi_sta.tid 】    	{"TID"     【 wlan.ba.multi_sta.tid  】
83  :【 wlan.ba.multi_sta.aid_tid_info 】    	{"AID TID Info"     【 wlan.ba.multi_sta.aid_tid_info  】
84  :【 wlan.ba.multi_sta.reserved 】    	{"Reserved"     【 wlan.ba.multi_sta.reserved  】
85  :【 wlan.ba.multi_sta.ra 】    	{"RA"     【 wlan.ba.multi_sta.ra  】
86  :【 wlan.bar.mtid.tidinfo.reserved 】    	{"Reserved"     【 wlan.bar.mtid.tidinfo.reserved  】
87  :【 wlan.bar.mtid.tidinfo.value 】    	{"Multi-TID Value"     【 wlan.bar.mtid.tidinfo.value  】
88  :【 wlan.ba.bm 】    	{"Block Ack Bitmap"     【 wlan.ba.bm  】
89  :【 wlan.ba.RBUFCAP 】    	{"Block Ack RBUFCAP"     【 wlan.ba.RBUFCAP  】
90  :【 wlan.ba.bm.missing_frame 】    	{"Missing frame"     【 wlan.ba.bm.missing_frame  】
91  :【 wlan.ba.gcr_group_addr 】    	{"GCR Group Address"     【 wlan.ba.gcr_group_addr  】
92  :【 wlan.beamform.feedback_seg_retrans_bitmap 】    	{"Feedback segment Retansmission Bitmap"     【 wlan.beamform.feedback_seg_retrans_bitmap  】
93  :【 wlan.vht_ndp.token 】    	{"Sounding Dialog Token"     【 wlan.vht_ndp.token  】
94  :【 wlan.vht_ndp.token.number 】    	{"Sounding Dialog Token Number"     【 wlan.vht_ndp.token.number  】
95  :【 wlan.vht_ndp.token.he 】    	{"HE"     【 wlan.vht_ndp.token.he  】
96  :【 wlan.vht_ndp.token.reserved 】    	{"Reserved"     【 wlan.vht_ndp.token.reserved  】
97  :【 wlan.vht_ndp.sta_info.aid12 】    	{"AID12"     【 wlan.vht_ndp.sta_info.aid12  】
98  :【 wlan.vht_ndp.sta_info.feedback_type 】    	{"Feedback Type"     【 wlan.vht_ndp.sta_info.feedback_type  】
99  :【 wlan.vht_ndp.sta_info.nc_index 】    	{"Nc Index"     【 wlan.vht_ndp.sta_info.nc_index  】
100  :【 wlan.vht_ndp.sta_info.reserved 】    	{"Reserved"     【 wlan.vht_ndp.sta_info.reserved  】
101  :【 wlan.data_encap.payload_type 】    	{"Payload Type"     【 wlan.data_encap.payload_type  】
102  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
103  :【 wlan.fixed.target_channel 】    	{"Target Channel"     【 wlan.fixed.target_channel  】
104  :【 wlan.fixed.operating_class 】    	{"Operating Class"     【 wlan.fixed.operating_class  】
105  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
106  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
107  :【 wlan.fixed.key_data 】    	{"Key Data"     【 wlan.fixed.key_data  】
108  :【 wlan.fixed.key_data_length 】    	{"Key Data Length"     【 wlan.fixed.key_data_length  】
109  :【 wlan.fixed.wnm_notification_type 】    	{"WNM-Notification type"     【 wlan.fixed.wnm_notification_type  】
110  :【 wlan.rm.action_code 】    	{"Action code"     【 wlan.rm.action_code  】
111  :【 wlan.rm.dialog_token 】    	{"Dialog token"     【 wlan.rm.dialog_token  】
112  :【 wlan.rm.repetitions 】    	{"Repetitions"     【 wlan.rm.repetitions  】
113  :【 wlan.rm.tx_power 】    	{"Transmit Power Used"     【 wlan.rm.tx_power  】
114  :【 wlan.rm.max_tx_power 】    	{"Max Transmit Power"     【 wlan.rm.max_tx_power  】
115  :【 wlan.rm.tpc 】    	{"TPC Report"     【 wlan.rm.tpc  】
116  :【 wlan.rm.tpc.element_id 】    	{"TPC Element ID"     【 wlan.rm.tpc.element_id  】
117  :【 wlan.rm.tpc.length 】    	{"TPC Length"     【 wlan.rm.tpc.length  】
118  :【 wlan.rm.tpc.tx_power 】    	{"TPC Transmit Power"     【 wlan.rm.tpc.tx_power  】
119  :【 wlan.rm.tpc.link_margin 】    	{"TPC Link Margin"     【 wlan.rm.tpc.link_margin  】
120  :【 wlan.rm.rx_antenna_id 】    	{"Receive Antenna ID"     【 wlan.rm.rx_antenna_id  】
121  :【 wlan.rm.tx_antenna_id 】    	{"Transmit Antenna ID"     【 wlan.rm.tx_antenna_id  】
122  :【 wlan.rm.rcpi 】    	{"Received Channel Power"     【 wlan.rm.rcpi  】
123  :【 wlan.rm.rsni 】    	{"Received Signal to noise indication"     【 wlan.rm.rsni  】
124  :【 wlan.fixed.request_mode.pref_cand 】    	{"Preferred Candidate List Included"     【 wlan.fixed.request_mode.pref_cand  】
125  :【 wlan.fixed.request_mode.abridged 】    	{"Abridged"     【 wlan.fixed.request_mode.abridged  】
126  :【 wlan.fixed.request_mode.disassoc_imminent 】    	{"Disassociation Imminent"     【 wlan.fixed.request_mode.disassoc_imminent  】
127  :【 wlan.fixed.request_mode.bss_term_included 】    	{"BSS Termination Included"     【 wlan.fixed.request_mode.bss_term_included  】
128  :【 wlan.fixed.request_mode.ess_disassoc_imminent 】    	{"ESS Disassociation Imminent"     【 wlan.fixed.request_mode.ess_disassoc_imminent  】
129  :【 wlan.fixed.disassoc_timer 】    	{"Disassociation Timer"     【 wlan.fixed.disassoc_timer  】
130  :【 wlan.fixed.bss_termination_delay 】    	{"BSS Termination Delay"     【 wlan.fixed.bss_termination_delay  】
131  :【 wlan.fixed.bss_transition_status_code 】    	{"BSS Transition Status Code"     【 wlan.fixed.bss_transition_status_code  】
132  :【 wlan.fixed.validity_interval 】    	{"Validity Interval"     【 wlan.fixed.validity_interval  】
133  :【 wlan.fixed.bss_termination_duration 】    	{"BSS Termination Duration"     【 wlan.fixed.bss_termination_duration  】
134  :【 wlan.fixed.session_information.url_length】    	{ "wlan.fixed.session_information.url_length  】
135  :【 wlan.fixed.session_information.url 】    	{"Session Information URL"     【 wlan.fixed.session_information.url  】
136  :【 wlan.fixed.bss_transition_target_bss 】    	{"BSS Transition Target BSS"     【 wlan.fixed.bss_transition_target_bss  】
137  :【 wlan.fixed.bss_transition_query_reason 】    	{"BSS Transition Query Reason"     【 wlan.fixed.bss_transition_query_reason  】
138  :【 wlan.fixed.bss_transition_candidate_list_entries 】    	{"BSS Transition Candidate List Entries"     【 wlan.fixed.bss_transition_candidate_list_entries  】
139  :【 wlan.res_offset 】    	{"Response Offset"     【 wlan.res_offset  】
140  :【 wlan.grant_ack.reserved 】    	{"Reserved"     【 wlan.grant_ack.reserved  】
141  :【 wlan.dynamic_allocation 】    	{"Dynamic Allocation"     【 wlan.dynamic_allocation  】
142  :【 wlan.dynamic_allocation.tid 】    	{"TID"     【 wlan.dynamic_allocation.tid  】
143  :【 wlan.dynamic_allocation.alloc_type 】    	{"Allocation Type"     【 wlan.dynamic_allocation.alloc_type  】
144  :【 wlan.dynamic_allocation.src_aid 】    	{"Source AID"     【 wlan.dynamic_allocation.src_aid  】
145  :【 wlan.dynamic_allocation.dest_aid 】    	{"Destination AID"     【 wlan.dynamic_allocation.dest_aid  】
146  :【 wlan.dynamic_allocation.alloc_duration 】    	{"Allocation Duration"     【 wlan.dynamic_allocation.alloc_duration  】
147  :【 wlan.dynamic_allocation.b39 】    	{"Reserved (b39)"     【 wlan.dynamic_allocation.b39  】
148  :【 wlan.ssw 】    	{"Sector Sweep"     【 wlan.ssw  】
149  :【 wlan.ssw.direction 】    	{"Sector Sweep Direction"     【 wlan.ssw.direction  】
150  :【 wlan.ssw.cdown 】    	{"Sector Sweep CDOWN"     【 wlan.ssw.cdown  】
151  :【 wlan.ssw.sector_id 】    	{"Sector Sweep Sector ID"     【 wlan.ssw.sector_id  】
152  :【 wlan.ssw.dmg_ant_id 】    	{"Sector Sweep DMG Antenna ID"     【 wlan.ssw.dmg_ant_id  】
153  :【 wlan.ssw.rxss_len 】    	{"Sector Sweep RXSS Length"     【 wlan.ssw.rxss_len  】
154  :【 wlan.bf 】    	{"Beam Forming"     【 wlan.bf  】
155  :【 wlan.bf.train 】    	{"Beam Forming Training"     【 wlan.bf.train  】
156  :【 wlan.bf.isInit 】    	{"Beam Forming Is InitiatorTXSS"     【 wlan.bf.isInit  】
157  :【 wlan.bf.isResp 】    	{"Beam Forming Is ResponderTXSS"     【 wlan.bf.isResp  】
158  :【 wlan.bf.rxss_len 】    	{"Beam Forming RXSS Length"     【 wlan.bf.rxss_len  】
159  :【 wlan.bf.rxss_rate 】    	{"Beam Forming RXSS Rate"     【 wlan.bf.rxss_rate  】
160  :【 wlan.bf.reserved 】    	{"Reserved (B10-B15)"     【 wlan.bf.reserved  】
161  :【 wlan.bf.num_sectors 】    	{"Beam Forming Total Number of Sectors"     【 wlan.bf.num_sectors  】
162  :【 wlan.bf.num_dmg_ants 】    	{"Beam Forming Number of DMG Antennas"     【 wlan.bf.num_dmg_ants  】
163  :【 wlan.bf.reserved 】    	{"Reserved (B12-B15)"     【 wlan.bf.reserved  】
164  :【 wlan.nav_da 】    	{"Destination address of STA that caused NAV update"     【 wlan.nav_da  】
165  :【 wlan.nav_sa 】    	{"Source address of STA that caused NAV update"     【 wlan.nav_sa  】
166  :【 wlan.sswf 】    	{"Sector Sweep Feedback"     【 wlan.sswf  】
167  :【 wlan.sswf.num_sectors 】    	{"Sector Sweep Feedback total number of sectors"     【 wlan.sswf.num_sectors  】
168  :【 wlan.sswf.num_dmg_ants 】    	{"Sector Sweep Feedback Number of receive DMG Antennas"     【 wlan.sswf.num_dmg_ants  】
169  :【 wlan.sswf.poll 】    	{"Sector Sweep Feedback Poll required"     【 wlan.sswf.poll  】
170  :【 wlan.sswf.reserved 】    	{"Sector Sweep Feedback Reserved"     【 wlan.sswf.reserved  】
171  :【 wlan.sswf.reserved 】    	{"Sector Sweep Feedback Reserved"     【 wlan.sswf.reserved  】
172  :【 wlan.sswf.sector_select 】    	{"Sector Sweep Feedback Sector Select"     【 wlan.sswf.sector_select  】
173  :【 wlan.sswf.dmg_antenna_select 】    	{"Sector Sweep Feedback DMG Antenna Select"     【 wlan.sswf.dmg_antenna_select  】
174  :【 wlan.sswf.snr_report 】    	{"Sector Sweep Feedback SNR Report"     【 wlan.sswf.snr_report  】
175  :【 wlan.brp 】    	{"BRP Request"     【 wlan.brp  】
176  :【 wlan.brp.l_rx 】    	{"BRP Request L-RX"     【 wlan.brp.l_rx  】
177  :【 wlan.brp.tx_trn_req 】    	{"BRP Request TX-TRN-REQ"     【 wlan.brp.tx_trn_req  】
178  :【 wlan.brp.mid_req 】    	{"BRP Request MID-REQ"     【 wlan.brp.mid_req  】
179  :【 wlan.brp.bc_req 】    	{"BRP Request BC-REQ"     【 wlan.brp.bc_req  】
180  :【 wlan.brp.mid_grant 】    	{"BRP Request MID-GRANT"     【 wlan.brp.mid_grant  】
181  :【 wlan.brp.bc_grant 】    	{"BRP Request BC-GRANT"     【 wlan.brp.bc_grant  】
182  :【 wlan.brp.chan_fbck_cap 】    	{"BRP Request Chan FBCK-CAP"     【 wlan.brp.chan_fbck_cap  】
183  :【 wlan.brp.tx_sector_id 】    	{"BRP Request TX Sector ID"     【 wlan.brp.tx_sector_id  】
184  :【 wlan.brp.other_aid 】    	{"BRP Request Other AID"     【 wlan.brp.other_aid  】
185  :【 wlan.brp.tx_antenna_id 】    	{"BRP Request TX Antenna ID"     【 wlan.brp.tx_antenna_id  】
186  :【 wlan.brp.reserved 】    	{"BRP Request Reserved"     【 wlan.brp.reserved  】
187  :【 wlan.blm 】    	{"Beamformed Link Maintenance"     【 wlan.blm  】
188  :【 wlan.blm.uint_index 】    	{"BeamLink Maintenance Uint Index"     【 wlan.blm.uint_index  】
189  :【 wlan.blm.value 】    	{"BeamLink Maintenance Value"     【 wlan.blm.value  】
190  :【 wlan.blm.is_master 】    	{"BeamLink Is Master"     【 wlan.blm.is_master  】
191  :【 wlan.bic 】    	{"Beacon Interval Control"     【 wlan.bic  】
192  :【 wlan.bic.cc 】    	{"Clustering Control Present"     【 wlan.bic.cc  】
193  :【 wlan.bic.discovery_mode 】    	{"Discovery Mode"     【 wlan.bic.discovery_mode  】
194  :【 wlan.bic.next_beacon 】    	{"Next Beacon"     【 wlan.bic.next_beacon  】
195  :【 wlan.bic.ati 】    	{"ATI Present"     【 wlan.bic.ati  】
196  :【 wlan.bic.abft_len 】    	{"A-BFT length"     【 wlan.bic.abft_len  】
197  :【 wlan.bic.fss 】    	{"FSS"     【 wlan.bic.fss  】
198  :【 wlan.bic.is_responder 】    	{"Is TXSS Responder"     【 wlan.bic.is_responder  】
199  :【 wlan.bic.next_abft 】    	{"Next A-BFT"     【 wlan.bic.next_abft  】
200  :【 wlan.bic.frag_txss 】    	{"Fragmented TXSS"     【 wlan.bic.frag_txss  】
201  :【 wlan.bic.txss_span 】    	{"TXSS span"     【 wlan.bic.txss_span  】
202  :【 wlan.bic.NBI_abft 】    	{"Number of Beacon Intervals that are needed to allocate A-BFT"     【 wlan.bic.NBI_abft  】
203  :【 wlan.bic.abft_count 】    	{"A-BFT Count"     【 wlan.bic.abft_count  】
204  :【 wlan.bic.nabft 】    	{"Number of A-BFT's received from each Antenna"     【 wlan.bic.nabft  】
205  :【 wlan.bic.pcp 】    	{"PCP Association Ready"     【 wlan.bic.pcp  】
206  :【 wlan.bic.reserved 】    	{"Reserved"     【 wlan.bic.reserved  】
207  :【 wlan.dmg_params 】    	{"DMG Parameters"     【 wlan.dmg_params  】
208  :【 wlan.dmg_params.bss 】    	{"BSS Type"     【 wlan.dmg_params.bss  】
209  :【 wlan.dmg_params.cbap_only 】    	{"CBAP Only"     【 wlan.dmg_params.cbap_only  】
210  :【 wlan.dmg_params.cbap_src 】    	{"CBAP Source"     【 wlan.dmg_params.cbap_src  】
211  :【 wlan.dmg_params.privacy 】    	{"DMG Privacy"     【 wlan.dmg_params.privacy  】
212  :【 wlan.dmg_params.policy 】    	{"ECAPC Policy Enforced"     【 wlan.dmg_params.policy  】
213  :【 wlan.cc 】    	{"Clustering Control"     【 wlan.cc  】
214  :【 wlan.cc.abft_resp_addr 】    	{"A-BFT Responder Address"     【 wlan.cc.abft_resp_addr  】
215  :【 wlan.cc.sp_duration 】    	{"Beacon SP Duration"     【 wlan.cc.sp_duration  】
216  :【 wlan.cc.cluster_id 】    	{"Cluster ID"     【 wlan.cc.cluster_id  】
217  :【 wlan.cc.rold 】    	{"Cluster Member Role"     【 wlan.cc.rold  】
218  :【 wlan.cc.max_mem 】    	{"Cluster MaxMem"     【 wlan.cc.max_mem  】
219  :【 wlan.relay_capabilities.relay_support 】    	{"Relay Supportability"     【 wlan.relay_capabilities.relay_support  】
220  :【 wlan.relay_capabilities.relay_use 】    	{"Relay Usability"     【 wlan.relay_capabilities.relay_use  】
221  :【 wlan.relay_capabilities.relay_permission 】    	{"Relay Permission"     【 wlan.relay_capabilities.relay_permission  】
222  :【 wlan.relay_capabilities.AC_power 】    	{"A/C Power"     【 wlan.relay_capabilities.AC_power  】
223  :【 wlan.relay_capabilities.relay_prefer 】    	{"Relay Preference"     【 wlan.relay_capabilities.relay_prefer  】
224  :【 wlan.relay_capabilities.duplex 】    	{"Duplex"     【 wlan.relay_capabilities.duplex  】
225  :【 wlan.relay_capabilities.cooperation 】    	{"Cooperation"     【 wlan.relay_capabilities.cooperation  】
226  :【 wlan.rcsi 】    	{"Relay Capable STA Info"     【 wlan.rcsi  】
227  :【 wlan.rcsi.aid 】    	{"AID"     【 wlan.rcsi.aid  】
228  :【 wlan.band_id 】    	{"Band ID"     【 wlan.band_id  】
229  :【 wlan.dmg_bss_param_change.move 】    	{"Move"     【 wlan.dmg_bss_param_change.move  】
230  :【 wlan.dmg_bss_param_change.size 】    	{"Size"     【 wlan.dmg_bss_param_change.size  】
231  :【 wlan.dmg_bss_param_change.tbtt_offset 】    	{"TBTT Offset"     【 wlan.dmg_bss_param_change.tbtt_offset  】
232  :【 wlan.dmg_bss_param_change.bi_duration 】    	{"BI Duration"     【 wlan.dmg_bss_param_change.bi_duration  】
233  :【 wlan.dmg_capa.sta_addr 】    	{"STA Address"     【 wlan.dmg_capa.sta_addr  】
234  :【 wlan.dmg_capa.aid 】    	{"AID"     【 wlan.dmg_capa.aid  】
235  :【 wlan.dmg_capa.reverse_direction 】    	{"Reverse Direction"     【 wlan.dmg_capa.reverse_direction  】
236  :【 wlan.dmg_capa.htls 】    	{"Higher Layer Timer Synchronization"     【 wlan.dmg_capa.htls  】
237  :【 wlan.dmg_capa.tpc 】    	{"TPC"     【 wlan.dmg_capa.tpc  】
238  :【 wlan.dmg_capa.spsh 】    	{"SPSH and Interference Mitigation"     【 wlan.dmg_capa.spsh  】
239  :【 wlan.dmg_capa.num_rx 】    	{"Number of RX DMG Antennas"     【 wlan.dmg_capa.num_rx  】
240  :【 wlan.dmg_capa.fast_link 】    	{"Fast Link Adaptation"     【 wlan.dmg_capa.fast_link  】
241  :【 wlan.dmg_capa.num_sectors 】    	{"Total Number of Sectors"     【 wlan.dmg_capa.num_sectors  】
242  :【 wlan.dmg_capa.rxss_len 】    	{"RXSS Length"     【 wlan.dmg_capa.rxss_len  】
243  :【 wlan.dmg_capa.reciprocity 】    	{"DMG Antenna Reciprocity"     【 wlan.dmg_capa.reciprocity  】
244  :【 wlan.dmg_capa.max_ampdu_exp 】    	{"Maximum A-MPDU Length Exponent"     【 wlan.dmg_capa.max_ampdu_exp  】
245  :【 wlan.dmg_capa.min_mpdu_spacing 】    	{"Minimum MPDU Start Spacing"     【 wlan.dmg_capa.min_mpdu_spacing  】
246  :【 wlan.dmg_capa.bs_flow_ctrl 】    	{"BA with Flow Control"     【 wlan.dmg_capa.bs_flow_ctrl  】
247  :【 wlan.dmg_capa.max_sc_rx_mcs 】    	{"Maximum SC Rx MCS"     【 wlan.dmg_capa.max_sc_rx_mcs  】
248  :【 wlan.dmg_capa.max_ofdm_rx_mcs 】    	{"Maximum OFDM Rx MCS"     【 wlan.dmg_capa.max_ofdm_rx_mcs  】
249  :【 wlan.dmg_capa.max_sc_tx_mcs 】    	{"Maximum SC Tx MCS"     【 wlan.dmg_capa.max_sc_tx_mcs  】
250  :【 wlan.dmg_capa.max_ofdm_tx_mcs 】    	{"Maximum OFDM Tx MCS"     【 wlan.dmg_capa.max_ofdm_tx_mcs  】
251  :【 wlan.dmg_capa.low_power_suuported 】    	{"Low Power SC PHY Supported"     【 wlan.dmg_capa.low_power_suuported  】
252  :【 wlan.dmg_capa.code_rate 】    	{"Code Rate 13/16"     【 wlan.dmg_capa.code_rate  】
253  :【 wlan.dmg_capa.dtp 】    	{"DTP Supported"     【 wlan.dmg_capa.dtp  】
254  :【 wlan.dmg_capa.appdu_supp 】    	{"A-PPDU Supported"     【 wlan.dmg_capa.appdu_supp  】
255  :【 wlan.dmg_capa.heartbeat 】    	{"HeartBeat"     【 wlan.dmg_capa.heartbeat  】
256  :【 wlan.dmg_capa.other_aid 】    	{"Supports Other_AID"     【 wlan.dmg_capa.other_aid  】
257  :【 wlan.dmg_capa.pattern_recip 】    	{"Antenna Pattern Reciprocity"     【 wlan.dmg_capa.pattern_recip  】
258  :【 wlan.dmg_capa.heartbeat_elapsed 】    	{"Heartbeat Elapsed Indication"     【 wlan.dmg_capa.heartbeat_elapsed  】
259  :【 wlan.dmg_capa.grant_ack_supp 】    	{"Grant ACK Supported"     【 wlan.dmg_capa.grant_ack_supp  】
260  :【 wlan.dmg_capa.RXSSTxRate 】    	{"RXSSTxRate Supported"     【 wlan.dmg_capa.RXSSTxRate  】
261  :【 wlan.dmg_capa.pcp_tdtti 】    	{"TDDTI"     【 wlan.dmg_capa.pcp_tdtti  】
262  :【 wlan.dmg_capa.pcp_psa 】    	{"Pseudo-static Allocations"     【 wlan.dmg_capa.pcp_psa  】
263  :【 wlan.dmg_capa.pcp_handover 】    	{"PDP Handover"     【 wlan.dmg_capa.pcp_handover  】
264  :【 wlan.dmg_capa.pcp_max_assoc 】    	{"Max Associated STA Number"     【 wlan.dmg_capa.pcp_max_assoc  】
265  :【 wlan.dmg_capa.pcp_power_src 】    	{"Power Source"     【 wlan.dmg_capa.pcp_power_src  】
266  :【 wlan.dmg_capa.pcp_decenter 】    	{"Decentralized PCP/AP Clustering"     【 wlan.dmg_capa.pcp_decenter  】
267  :【 wlan.dmg_capa.pcp_forwarding 】    	{"PCP Forwarding"     【 wlan.dmg_capa.pcp_forwarding  】
268  :【 wlan.dmg_capa.pcp_center 】    	{"Centralized PCP/AP Clustering"     【 wlan.dmg_capa.pcp_center  】
269  :【 wlan.dmg_capa.beam_track 】    	{"STA Beam Tracking Time Limit"     【 wlan.dmg_capa.beam_track  】
270  :【 wlan.dmg_capa.ext_sc_mcs_capa_max_tx 】    	{"Extended SC Max Tx MCS Name"     【 wlan.dmg_capa.ext_sc_mcs_capa_max_tx  】
271  :【 wlan.dmg_capa.ext_sc_mcs_tx_code_7_8 】    	{"Extended SC Tx MCS code rate 7/8"     【 wlan.dmg_capa.ext_sc_mcs_tx_code_7_8  】
272  :【 wlan.dmg_capa.ext_sc_mcs_capa_max_rx 】    	{"Extended SC Max Rx MCS Name"     【 wlan.dmg_capa.ext_sc_mcs_capa_max_rx  】
273  :【 wlan.dmg_capa.ext_sc_mcs_rx_code_7_8 】    	{"Extended SC Rx MCS code rate 7/8"     【 wlan.dmg_capa.ext_sc_mcs_rx_code_7_8  】
274  :【 wlan.dmg_capa.max_basic_sf_amsdu 】    	{"Max Number of Basic Subframes in an A-MSDU"     【 wlan.dmg_capa.max_basic_sf_amsdu  】
275  :【 wlan.dmg_capa.max_short_sf_amsdu 】    	{"Max Number of short Subframes in an A-MSDU"     【 wlan.dmg_capa.max_short_sf_amsdu  】
276  :【 wlan.dmg_oper.psrsi 】    	{"PS Request Suspension Interval"     【 wlan.dmg_oper.psrsi  】
277  :【 wlan.dmg_oper.min_BHI_duration 】    	{"Min BHI Duration"     【 wlan.dmg_oper.min_BHI_duration  】
278  :【 wlan.dmg_oper.brdcst_sta_info_dur 】    	{"Broadcast STA Info Duration"     【 wlan.dmg_oper.brdcst_sta_info_dur  】
279  :【 wlan.dmg_oper.assoc_resp_confirm_time 】    	{"Associated Response Confirm Time"     【 wlan.dmg_oper.assoc_resp_confirm_time  】
280  :【 wlan.dmg_oper.min_pp_duration 】    	{"Min PP Duration"     【 wlan.dmg_oper.min_pp_duration  】
281  :【 wlan.dmg_oper.SP_idle_timeout 】    	{"SP Idle Timeout"     【 wlan.dmg_oper.SP_idle_timeout  】
282  :【 wlan.dmg_oper.max_lost_beacons 】    	{"Max Lost Beacons"     【 wlan.dmg_oper.max_lost_beacons  】
283  :【 wlan.sctor_id.type 】    	{"Type"     【 wlan.sctor_id.type  】
284  :【 wlan.sctor_id.tap1 】    	{"Tap 1"     【 wlan.sctor_id.tap1  】
285  :【 wlan.sctor_id.state1 】    	{"State 1"     【 wlan.sctor_id.state1  】
286  :【 wlan.sctor_id.tap2 】    	{"Tap 2"     【 wlan.sctor_id.tap2  】
287  :【 wlan.sctor_id.state2 】    	{"State 2"     【 wlan.sctor_id.state2  】
288  :【 wlan.ext_sched.alloc_id 】    	{"Allocation ID"     【 wlan.ext_sched.alloc_id  】
289  :【 wlan.ext_sched.alloc_type 】    	{"Allocation Type"     【 wlan.ext_sched.alloc_type  】
290  :【 wlan.ext_sched.p_static 】    	{"Pseudo-static"     【 wlan.ext_sched.p_static  】
291  :【 wlan.ext_sched.truncatable 】    	{"Truncatable"     【 wlan.ext_sched.truncatable  】
292  :【 wlan.ext_sched.extendable 】    	{"Extenedable"     【 wlan.ext_sched.extendable  】
293  :【 wlan.ext_sched.pcp_active 】    	{"PCP Active"     【 wlan.ext_sched.pcp_active  】
294  :【 wlan.ext_sched.lp_sc_used 】    	{"LP SC Used"     【 wlan.ext_sched.lp_sc_used  】
295  :【 wlan.ext_sched.src_id 】    	{"Source AID"     【 wlan.ext_sched.src_id  】
296  :【 wlan.ext_sched.dest_id 】    	{"Destination AID"     【 wlan.ext_sched.dest_id  】
297  :【 wlan.ext_sched.alloc_start 】    	{"Allocation Start"     【 wlan.ext_sched.alloc_start  】
298  :【 wlan.ext_sched.block_duration 】    	{"Allocation Block Duration"     【 wlan.ext_sched.block_duration  】
299  :【 wlan.ext_sched.num_blocks 】    	{"Number of Blocks"     【 wlan.ext_sched.num_blocks  】
300  :【 wlan.ext_sched.alloc_block_period 】    	{"Allocation Block Period"     【 wlan.ext_sched.alloc_block_period  】
301  :【 wlan.sta_avail.aid 】    	{"AID"     【 wlan.sta_avail.aid  】
302  :【 wlan.sta_avail.cbap 】    	{"CBAP"     【 wlan.sta_avail.cbap  】
303  :【 wlan.sta_avail.pp_avail 】    	{"PP Available"     【 wlan.sta_avail.pp_avail  】
304  :【 wlan.next_ati.start_time 】    	{"Start Time"     【 wlan.next_ati.start_time  】
305  :【 wlan.next_ati.duration 】    	{"ATI Duration"     【 wlan.next_ati.duration  】
306  :【 wlan.pcp_handover.old_bssid 】    	{"Old BSSID"     【 wlan.pcp_handover.old_bssid  】
307  :【 wlan.pcp_handover.new_pcp_addr 】    	{"New PCP Address"     【 wlan.pcp_handover.new_pcp_addr  】
308  :【 wlan.quiet_res.bssid 】    	{"BSSID"     【 wlan.quiet_res.bssid  】
309  :【 wlan.relay_capabilities.duplex 】    	{"Duplex"     【 wlan.relay_capabilities.duplex  】
310  :【 wlan.relay_capabilities.cooperation 】    	{"Cooperation"     【 wlan.relay_capabilities.cooperation  】
311  :【 wlan.realy_trans_param.tx_mode 】    	{"TX-Mode"     【 wlan.realy_trans_param.tx_mode  】
312  :【 wlan.realy_trans_param.link_change_interval 】    	{"Link Change Interval"     【 wlan.realy_trans_param.link_change_interval  】
313  :【 wlan.realy_trans_param.data_sensing_time 】    	{"Data Sensing Time"     【 wlan.realy_trans_param.data_sensing_time  】
314  :【 wlan.realy_trans_param.first_period 】    	{"First Period"     【 wlan.realy_trans_param.first_period  】
315  :【 wlan.realy_trans_param.second_period 】    	{"Second Period"     【 wlan.realy_trans_param.second_period  】
316  :【 wlan.beam_refine.initiator 】    	{"Initiator"     【 wlan.beam_refine.initiator  】
317  :【 wlan.beam_refine.tx_train_res 】    	{"TX-train-response"     【 wlan.beam_refine.tx_train_res  】
318  :【 wlan.beam_refine.rx_train_res 】    	{"RX-train-response"     【 wlan.beam_refine.rx_train_res  】
319  :【 wlan.beam_refine.tx_trn_ok 】    	{"TX-TRN-OK"     【 wlan.beam_refine.tx_trn_ok  】
320  :【 wlan.beam_refine.txss_fbck_req 】    	{"TXSS-FBCK-REQ"     【 wlan.beam_refine.txss_fbck_req  】
321  :【 wlan.beam_refine.bs_fbck 】    	{"BS-FBCK"     【 wlan.beam_refine.bs_fbck  】
322  :【 wlan.beam_refine.bs_fbck_antenna_id 】    	{"BS-FBCK Anetenna ID"     【 wlan.beam_refine.bs_fbck_antenna_id  】
323  :【 wlan.beam_refine.snr_req 】    	{"SNR Requested"     【 wlan.beam_refine.snr_req  】
324  :【 wlan.beam_refine.ch_measure_req 】    	{"Channel Measurement Requested"     【 wlan.beam_refine.ch_measure_req  】
325  :【 wlan.beam_refine.taps_req 】    	{"Number of Taps Requested"     【 wlan.beam_refine.taps_req  】
326  :【 wlan.beam_refine.sector_id_req 】    	{"Sector ID Order Requested"     【 wlan.beam_refine.sector_id_req  】
327  :【 wlan.beam_refine.snr_present 】    	{"SNR Present"     【 wlan.beam_refine.snr_present  】
328  :【 wlan.beam_refine.ch_measure_present 】    	{"Channel Measurement Present"     【 wlan.beam_refine.ch_measure_present  】
329  :【 wlan.beam_refine.tap_delay_present 】    	{"Tap Delay Present"     【 wlan.beam_refine.tap_delay_present  】
330  :【 wlan.beam_refine.taps_present 】    	{"Number of Taps Present"     【 wlan.beam_refine.taps_present  】
331  :【 wlan.beam_refine.num_measurement 】    	{"Number of Measurements"     【 wlan.beam_refine.num_measurement  】
332  :【 wlan.beam_refine.sector_id_present 】    	{"Sector ID Order Present"     【 wlan.beam_refine.sector_id_present  】
333  :【 wlan.beam_refine.num_beams 】    	{"Number of Beams"     【 wlan.beam_refine.num_beams  】
334  :【 wlan.beam_refine.mid_ext 】    	{"MID Extension"     【 wlan.beam_refine.mid_ext  】
335  :【 wlan.beam_refine.cap_req 】    	{"Capability Request"     【 wlan.beam_refine.cap_req  】
336  :【 wlan.beam_refine.reserved 】    	{"Reserved"     【 wlan.beam_refine.reserved  】
337  :【 wlan.next_pcp.list 】    	{"AID of NextPCP"     【 wlan.next_pcp.list  】
338  :【 wlan.next_pcp.token 】    	{"NextPCP List Token"     【 wlan.next_pcp.token  】
339  :【 wlan.pcp_handover.remaining_BIs 】    	{"Remaining BI's"     【 wlan.pcp_handover.remaining_BIs  】
340  :【 wlan.request_token 】    	{"Request Token"     【 wlan.request_token  】
341  :【 wlan.bi_start_time 】    	{"BI Start Time"     【 wlan.bi_start_time  】
342  :【 wlan.sleep_cycle 】    	{"Sleep Cycle"     【 wlan.sleep_cycle  】
343  :【 wlan.num_awake_bis 】    	{"Number of Awake/Doze BIs"     【 wlan.num_awake_bis  】
344  :【 wlan.fixed.dmg_act 】    	{"DMG Action"     【 wlan.fixed.dmg_act  】
345  :【 wlan.fixed.unprotected_dmg_act 】    	{"Unprotected DMG Action"     【 wlan.fixed.unprotected_dmg_act  】
346  :【 wlan.dmg.pwr_mgmt 】    	{"DMG Power Management"     【 wlan.dmg.pwr_mgmt  】
347  :【 wlan.dmg.subject_addr 】    	{"Subject Address"     【 wlan.dmg.subject_addr  】
348  :【 wlan.dmg.handover_reason 】    	{"Handover Reason"     【 wlan.dmg.handover_reason  】
349  :【 wlan.dmg.handover_remaining_bi 】    	{"Handover Remaining BI"     【 wlan.dmg.handover_remaining_bi  】
350  :【 wlan.dmg.handover_result 】    	{"Handover Result"     【 wlan.dmg.handover_result  】
351  :【 wlan.dmg.handover_reject_reason 】    	{"Handover Reject Reason"     【 wlan.dmg.handover_reject_reason  】
352  :【 wlan.dmg.destination_reds_aid 】    	{"Destination REDS AID"     【 wlan.dmg.destination_reds_aid  】
353  :【 wlan.dmg.destination_aid 】    	{"Destination AID"     【 wlan.dmg.destination_aid  】
354  :【 wlan.dmg.realy_aid 】    	{"Relay AID"     【 wlan.dmg.realy_aid  】
355  :【 wlan.dmg.source_aid 】    	{"Source AID"     【 wlan.dmg.source_aid  】
356  :【 wlan.dmg.timing_offset 】    	{"Timing Offset"     【 wlan.dmg.timing_offset  】
357  :【 wlan.dmg.sampling_frequency_offset 】    	{"Sampling Frequency Offset"     【 wlan.dmg.sampling_frequency_offset  】
358  :【 wlan.dmg.relay_operation_type 】    	{"Relay Operation Type"     【 wlan.dmg.relay_operation_type  】
359  :【 wlan.dmg.peer_sta_aid 】    	{"Peer STA AID"     【 wlan.dmg.peer_sta_aid  】
360  :【 wlan.dmg.snr 】    	{"SNR"     【 wlan.dmg.snr  】
361  :【 wlan.dmg.internal_angle 】    	{"Internal Angle"     【 wlan.dmg.internal_angle  】
362  :【 wlan.dmg.recommend 】    	{"Recommend"     【 wlan.dmg.recommend  】
363  :【 wlan.fst.action_code 】    	{"FST Action Code"     【 wlan.fst.action_code  】
364  :【 wlan.fst.llt 】    	{"Link Loss Timeout"     【 wlan.fst.llt  】
365  :【 wlan.session_trans.fsts_id 】    	{"FSTS ID"     【 wlan.session_trans.fsts_id  】
366  :【 wlan.fst.mmpdu_length 】    	{"MMPDU Length"     【 wlan.fst.mmpdu_length  】
367  :【 wlan.fst.mmpdu_ctrl 】    	{"MMPDU Control"     【 wlan.fst.mmpdu_ctrl  】
368  :【 wlan.fst.oct_mmpdu 】    	{"OCT MMPDU"     【 wlan.fst.oct_mmpdu  】
369  :【 wlan.vht.mimo_control.control 】    	{"VHT MIMO Control"     【 wlan.vht.mimo_control.control  】
370  :【 wlan.vht.mimo_control.ncindex 】    	{"Nc Index"     【 wlan.vht.mimo_control.ncindex  】
371  :【 wlan.vht.mimo_control.nrindex 】    	{"Nr Index"     【 wlan.vht.mimo_control.nrindex  】
372  :【 wlan.vht.mimo_control.chanwidth 】    	{"Channel Width"     【 wlan.vht.mimo_control.chanwidth  】
373  :【 wlan.vht.mimo_control.grouping 】    	{"Grouping (Ng)"     【 wlan.vht.mimo_control.grouping  】
374  :【 wlan.vht.mimo_control.codebookinfo 】    	{"Codebook Information"     【 wlan.vht.mimo_control.codebookinfo  】
375  :【 wlan.vht.mimo_control.feedbacktype 】    	{"Feedback Type"     【 wlan.vht.mimo_control.feedbacktype  】
376  :【 wlan.vht.mimo_control.remainingfeedbackseg 】    	{"Remaining Feedback Segments"     【 wlan.vht.mimo_control.remainingfeedbackseg  】
377  :【 wlan.vht.mimo_control.firstfeedbackseg 】    	{"First Feedback Segments"     【 wlan.vht.mimo_control.firstfeedbackseg  】
378  :【 wlan.vht.mimo_control.reserved 】    	{"Reserved"     【 wlan.vht.mimo_control.reserved  】
379  :【 wlan.vht.mimo_control.sounding_dialog_tocken_nbr 】    	{"Sounding Dialog Token Number"     【 wlan.vht.mimo_control.sounding_dialog_tocken_nbr  】
380  :【 wlan.vht.action 】    	{"VHT Action"     【 wlan.vht.action  】
381  :【 wlan.vht.compressed_beamforming_report 】    	{"VHT Compressed Beamforming Report"     【 wlan.vht.compressed_beamforming_report  】
382  :【wlan.vht.exclusive_beamforming_report 】    	{"VHT MU Exclusive Beamforming Report  】"wlan.vht.exclusive_beamforming_report  】
383  :【 wlan.vht.compressed_beamforming_report.snr 】    	{"Signal to Noise Ratio (SNR)"     【 wlan.vht.compressed_beamforming_report.snr  】
384  :【 wlan.vht.compressed_beamforming_report.phi 】    	{"PHI"     【 wlan.vht.compressed_beamforming_report.phi  】
385  :【 wlan.vht.compressed_beamforming_report.psi 】    	{"PSI"     【 wlan.vht.compressed_beamforming_report.psi  】
386  :【 wlan.vht.compressed_beamforming_report.feedback_matr 】   	{"Compressed Beamforming Feedback Matrix"     【 wlan.vht.compressed_beamforming_report.feedback_matrix  】
387  :【 wlan.vht.exclusive_beamforming_report.d 】   	{"Delta SNR for space-time stream Nc for subcarrier k"     【 wlan.vht.exclusive_beamforming_report.delta_snr  】
388  :【 wlan.vht.group_id_management 】    	{"Group ID Management"     【 wlan.vht.group_id_management  】
389  :【 wlan.vht.membership_status_array 】    	{"Membership Status Array"     【 wlan.vht.membership_status_array  】
390  :【 wlan.vht.user_position_array 】    	{"User Position Array"     【 wlan.vht.user_position_array  】
391  :【 wlan.vht.membership_status_array.field 】    	{"Membership Status Field"     【 wlan.vht.membership_status_array.field  】
392  :【 wlan.vht.user_position_array.field 】    	{"User Position Field"     【 wlan.vht.user_position_array.field  】
393  :【 wlan.vht.operation_mode_notification 】    	{"Operation Mode Notification"     【 wlan.vht.operation_mode_notification  】
394  :【 wlan.he.action 】    	{"HE Action"     【 wlan.he.action  】
395  :【 wlan.he.protected_action 】    	{"Protected HE Action"     【 wlan.he.protected_action  】
396  :【 wlan.he.mimo.nc_index 】    	{"Nc Index"     【 wlan.he.mimo.nc_index  】
397  :【 wlan.he.mimo.nr_index 】    	{"Nr Index"     【 wlan.he.mimo.nr_index  】
398  :【 wlan.he.mimo.bw 】    	{"BW"     【 wlan.he.mimo.bw  】
399  :【 wlan.he.mimo.grouping 】    	{"Grouping"     【 wlan.he.mimo.grouping  】
400  :【 wlan.he.mimo.codebook_info 】    	{"Codebook Information"     【 wlan.he.mimo.codebook_info  】
401  :【 wlan.he.mimo.feedback_type 】    	{"Feedback Type"     【 wlan.he.mimo.feedback_type  】
402  :【 wlan.he.mimo.remaining_feedback_segs 】    	{"Remaining Feedback Segments"     【 wlan.he.mimo.remaining_feedback_segs  】
403  :【 wlan.he.mimo.first_feedback_seg 】    	{"First Feedback Segment"     【 wlan.he.mimo.first_feedback_seg  】
404  :【 wlan.he.mimo.ru_start_index 】    	{"RU Start Index"     【 wlan.he.mimo.ru_start_index  】
405  :【 wlan.he.mimo.ru_end_index 】    	{"RU End Index"     【 wlan.he.mimo.ru_end_index  】
406  :【 wlan.he.mimo.sounding_dialog_token_num 】    	{"Sounding Dialog Token Number"     【 wlan.he.mimo.sounding_dialog_token_num  】
407  :【 wlan.he.mimo.reserved 】    	{"Reserved"     【 wlan.he.mimo.reserved  】
408  :【 wlan.he.action.he_mimo_control 】    	{"HE MIMO Control"     【 wlan.he.action.he_mimo_control  】
409  :【 wlan.he.mimo.beamforming_report.avgsnr 】    	{"AgvSNR"     【 wlan.he.mimo.beamforming_report.avgsnr  】
410  :【 wlan.he.action.he_mimo_control.scidx 】    	{"SCIDX"     【 wlan.he.action.he_mimo_control.scidx  】
411  :【 wlan.he.action.he_mimo_control.report_len 】    	{"Report Len"     【 wlan.he.action.he_mimo_control.report_len  】
412  :【 wlan.dmg_tspec.allocation_id 】    	{"Allocation ID"     【 wlan.dmg_tspec.allocation_id  】
413  :【 wlan.dmg_tspec.allocation_type 】    	{"Allocation Type"     【 wlan.dmg_tspec.allocation_type  】
414  :【 wlan.dmg_tspec.allocation_format 】    	{"Allocation Format"     【 wlan.dmg_tspec.allocation_format  】
415  :【 wlan.dmg_tspec.pseudo_static 】    	{"Pseudo Static"     【 wlan.dmg_tspec.pseudo_static  】
416  :【 wlan.dmg_tspec.truncatable 】    	{"Truncatable"     【 wlan.dmg_tspec.truncatable  】
417  :【 wlan.dmg_tspec.extendable 】    	{"Extendable"     【 wlan.dmg_tspec.extendable  】
418  :【 wlan.dmg_tspec.lp_sc_used 】    	{"LP SC Usec"     【 wlan.dmg_tspec.lp_sc_used  】
419  :【 wlan.dmg_tspec.up 】    	{"UP"     【 wlan.dmg_tspec.up  】
420  :【 wlan.dmg_tspec.dest_aid 】    	{"Destination AID"     【 wlan.dmg_tspec.dest_aid  】
421  :【 wlan.dmg_tspec.allocation_period 】    	{"Allocation Period"     【 wlan.dmg_tspec.allocation_period  】
422  :【 wlan.dmg_tspec.min_allocation 】    	{"Minimal Allocation"     【 wlan.dmg_tspec.min_allocation  】
423  :【 wlan.dmg_tspec.max_allocation 】    	{"Maximal Allocation"     【 wlan.dmg_tspec.max_allocation  】
424  :【 wlan.dmg_tspec.min_duration 】    	{"Minimal Duration"     【 wlan.dmg_tspec.min_duration  】
425  :【 wlan.dmg_tspec.num_of_constraints 】    	{"Number Of Constraints"     【 wlan.dmg_tspec.num_of_constraints  】
426  :【 wlan.dmg_tspec.tsconst.start_time 】    	{"TS Constraint Start Time"     【 wlan.dmg_tspec.tsconst.start_time  】
427  :【 wlan.dmg_tspec.tsconst.duration 】    	{"TS Constraint Duration"     【 wlan.dmg_tspec.tsconst.duration  】
428  :【 wlan.dmg_tspec.tsconst.period 】    	{"TS Constraint Period"     【 wlan.dmg_tspec.tsconst.period  】
429  :【 wlan.dmg_tspec.tsconst.interferer_mac 】    	{"TS Constraint Interferer MAC Address"     【 wlan.dmg_tspec.tsconst.interferer_mac  】
430  :【 wlan.ch_meas_fb.realtive_I 】    	{"Channel Measurement Feedback Relative I"     【 wlan.ch_meas_fb.realtive_I  】
431  :【 wlan.ch_meas_fb.realtive_Q 】    	{"Channel Measurement Feedback Relative Q"     【 wlan.ch_meas_fb.realtive_Q  】
432  :【 wlan.ch_meas_fb.tap_delay 】    	{"Channel Measurement Feedback Tap Delay"     【 wlan.ch_meas_fb.tap_delay  】
433  :【 wlan.ch_meas_fb.sector_id 】    	{"Channel Measurement Feedback Secotr ID"     【 wlan.ch_meas_fb.sector_id  】
434  :【 wlan.ch_meas_fb.antenna_id 】    	{"Channel Measurement Feedback Antenna ID"     【 wlan.ch_meas_fb.antenna_id  】
435  :【 wlan.awake_window 】    	{"Awake Window"     【 wlan.awake_window  】
436  :【 wlan.addba.no_frag 】    	{"ADDBA No Fragmentation"     【 wlan.addba.no_frag  】
437  :【 wlan.addba.he_frag_oper 】    	{"ADDBA HE Fragmentation Operation"     【 wlan.addba.he_frag_oper  】
438  :【 wlan.addba.he_frag_oper 】    	{"Reserved"     【 wlan.addba.he_frag_oper  】
439  :【 wlan.multi_band.ctrl_sta_role 】    	{"STA Rold"     【 wlan.multi_band.ctrl_sta_role  】
440  :【 wlan.multi_band.ctrl_addr_present 】    	{"STA MAC Address Present"     【 wlan.multi_band.ctrl_addr_present  】
441  :【 wlan.multi_band.ctrl_cipher_present 】    	{"PCS Present"     【 wlan.multi_band.ctrl_cipher_present  】
442  :【 wlan.multi_band.oper_class 】    	{"Operating Class"     【 wlan.multi_band.oper_class  】
443  :【 wlan.multi_band.channel_number 】    	{"Channel Number"     【 wlan.multi_band.channel_number  】
444  :【 wlan.multi_band.tsf_offset 】    	{"TSF Offset"     【 wlan.multi_band.tsf_offset  】
445  :【 wlan.multi_band.conn_ap 】    	{"Connection Capability AP"     【 wlan.multi_band.conn_ap  】
446  :【 wlan.multi_band.conn_pcp 】    	{"Connection Capability PCP"     【 wlan.multi_band.conn_pcp  】
447  :【 wlan.multi_band.conn_dls 】    	{"Connection Capability DLS"     【 wlan.multi_band.conn_dls  】
448  :【 wlan.multi_band.conn_tdls 】    	{"Connection Capability TDLS"     【 wlan.multi_band.conn_tdls  】
449  :【 wlan.multi_band.conn_ibss 】    	{"Connection Capability IBSS"     【 wlan.multi_band.conn_ibss  】
450  :【 wlan.multi_band.fst_timeout 】    	{"FST Session Timeout"     【 wlan.multi_band.fst_timeout  】
451  :【 wlan.multi_band.sta_mac 】    	{"Transmitting STA MAC Address"     【 wlan.multi_band.sta_mac  】
452  :【 wlan.activity 】    	{"Activity"     【 wlan.activity  】
453  :【 wlan.dmg_link_adapt.mcs 】    	{"MCS"     【 wlan.dmg_link_adapt.mcs  】
454  :【 wlan.dmg_link_adapt.link_margin 】    	{"Link Margin"     【 wlan.dmg_link_adapt.link_margin  】
455  :【 wlan.ref_timestamp 】    	{"Reference Timestamp"     【 wlan.ref_timestamp  】
456  :【 wlan.switching_stream.non_qos 】    	{"Non-Qos Data Frames"     【 wlan.switching_stream.non_qos  】
457  :【 wlan.switching_stream.param_num 】    	{"Number Of Switching Stream Elements"     【 wlan.switching_stream.param_num  】
458  :【 wlan.switching_stream.old_tid 】    	{"Old Band TID"     【 wlan.switching_stream.old_tid  】
459  :【 wlan.switching_stream.old_direction 】    	{"Old Band Direction"     【 wlan.switching_stream.old_direction  】
460  :【 wlan.switching_stream.new_tid 】    	{"New Band TID"     【 wlan.switching_stream.new_tid  】
461  :【 wlan.switching_stream.new_direction 】    	{"New Band Direction"     【 wlan.switching_stream.new_direction  】
462  :【 wlan.switching_stream.new_valid_id 】    	{"Stream ID in New Band Valid"     【 wlan.switching_stream.new_valid_id  】
463  :【 wlan.switching_stream.llt_type 】    	{"LLT Type"     【 wlan.switching_stream.llt_type  】
464  :【 wlan.fixed.timestamp 】    	{"Timestamp"     【 wlan.fixed.timestamp  】
465  :【 wlan.fixed.auth.alg 】    	{"Authentication Algorithm"     【 wlan.fixed.auth.alg  】
466  :【 wlan.fixed.beacon 】    	{"Beacon Interval"     【 wlan.fixed.beacon  】
467  :【 wlan.fixed.all 】    	{"Fixed parameters"     【 wlan.fixed.all  】
468  :【 wlan.tagged.all 】    	{"Tagged parameters"     【 wlan.tagged.all  】
469  :【 wlan.ssid 】    	{"SSID"     【 wlan.ssid  】
470  :【 wlan.supported_rates 】    	{"Supported Rates"     【 wlan.supported_rates  】
471  :【 wlan.fh.dwell_time 】    	{"Dwell Time"     【 wlan.fh.dwell_time  】
472  :【 wlan.fh.hop_set 】    	{"Hop Set"     【 wlan.fh.hop_set  】
473  :【 wlan.fh.hop_pattern 】    	{"Hop Pattern"     【 wlan.fh.hop_pattern  】
474  :【 wlan.fh.hop_index 】    	{"Hop Index"     【 wlan.fh.hop_index  】
475  :【 wlan.fixed.baparams 】    	{"Block Ack Parameters"     【 wlan.fixed.baparams  】
476  :【 wlan.fixed.baparams.amsdu 】    	{"A-MSDUs"     【 wlan.fixed.baparams.amsdu  】
477  :【 wlan.fixed.baparams.policy 】    	{"Block Ack Policy"     【 wlan.fixed.baparams.policy  】
478  :【 wlan.fixed.baparams.tid 】    	{"Traffic Identifier"     【 wlan.fixed.baparams.tid  】
479  :【 wlan.fixed.baparams.buffersize 】    	{"Number of Buffers (1 Buffer = 2304 Bytes)"     【 wlan.fixed.baparams.buffersize  】
480  :【 wlan.fixed.batimeout 】    	{"Block Ack Timeout"     【 wlan.fixed.batimeout  】
481  :【 wlan.fixed.ssc 】    	{"Block Ack Starting Sequence Control (SSC)"     【 wlan.fixed.ssc  】
482  :【 wlan.fixed.ssc.fragment 】    	{"Fragment"     【 wlan.fixed.ssc.fragment  】
483  :【 wlan.fixed.ssc.sequence 】    	{"Starting Sequence Number"     【 wlan.fixed.ssc.sequence  】
484  :【 wlan.fixed.delba.param 】    	{"Delete Block Ack (DELBA) Parameter Set"     【 wlan.fixed.delba.param  】
485  :【 wlan.fixed.delba.param.reserved 】    	{"Reserved"     【 wlan.fixed.delba.param.reserved  】
486  :【 wlan.fixed.delba.param.initiator 】    	{"Initiator"     【 wlan.fixed.delba.param.initiator  】
487  :【 wlan.fixed.delba.param.tid 】    	{"TID"     【 wlan.fixed.delba.param.tid  】
488  :【 wlan.fixed.maxregpwr 】    	{"Maximum Regulation Power"     【 wlan.fixed.maxregpwr  】
489  :【 wlan.fixed.msmtpilotint 】    	{"Measurement Pilot Interval"     【 wlan.fixed.msmtpilotint  】
490  :【 wlan.fixed.country 】    	{"Country String"     【 wlan.fixed.country  】
491  :【 wlan.fixed.maxtxpwr 】    	{"Maximum Transmit Power"     【 wlan.fixed.maxtxpwr  】
492  :【 wlan.fixed.txpwr 】    	{"Transmit Power Used"     【 wlan.fixed.txpwr  】
493  :【 wlan.fixed.tnoisefloor 】    	{"Transceiver Noise Floor"     【 wlan.fixed.tnoisefloor  】
494  :【 wlan.fixed.chanwidth 】    	{"Supported Channel Width"     【 wlan.fixed.chanwidth  】
495  :【 wlan.fixed.qosinfo.ap 】    	{"QoS Information (AP)"     【 wlan.fixed.qosinfo.ap  】
496  :【 wlan.fixed.qosinfo.ap.edcaupdate 】    	{"EDCA Parameter Set Update Count"     【 wlan.fixed.qosinfo.ap.edcaupdate  】
497  :【 wlan.fixed.qosinfo.ap.qack 】    	{"Q-Ack"     【 wlan.fixed.qosinfo.ap.qack  】
498  :【 wlan.fixed.qosinfo.ap.queue_req 】    	{"Queue Request"     【 wlan.fixed.qosinfo.ap.queue_req  】
499  :【 wlan.fixed.qosinfo.ap.txopreq 】    	{"TXOP Request"     【 wlan.fixed.qosinfo.ap.txopreq  】
500  :【 wlan.fixed.qosinfo.ap.reserved 】    	{"Reserved"     【 wlan.fixed.qosinfo.ap.reserved  】
501  :【 wlan.fixed.qosinfo.sta 】    	{"QoS Information (STA)"     【 wlan.fixed.qosinfo.sta  】
502  :【 wlan.fixed.qosinfo.sta.ac_vo 】    	{"AC_VO U-APSD Flag"     【 wlan.fixed.qosinfo.sta.ac_vo  】
503  :【 wlan.fixed.qosinfo.sta.ac_vi 】    	{"AC_VI U-APSD Flag"     【 wlan.fixed.qosinfo.sta.ac_vi  】
504  :【 wlan.fixed.qosinfo.sta.ac_bk 】    	{"AC_BK U-APSD Flag"     【 wlan.fixed.qosinfo.sta.ac_bk  】
505  :【 wlan.fixed.qosinfo.sta.ac_be 】    	{"AC_BE U-APSD Flag"     【 wlan.fixed.qosinfo.sta.ac_be  】
506  :【 wlan.fixed.qosinfo.sta.qack 】    	{"Q-Ack"     【 wlan.fixed.qosinfo.sta.qack  】
507  :【 wlan.fixed.qosinfo.sta.max_sp_length 】    	{"Max SP Length"     【 wlan.fixed.qosinfo.sta.max_sp_length  】
508  :【 wlan.fixed.qosinfo.sta.more_data_ack 】    	{"More Data Ack"     【 wlan.fixed.qosinfo.sta.more_data_ack  】
509  :【 wlan.fixed.sm.powercontrol 】    	{"Spatial Multiplexing (SM) Power Control"     【 wlan.fixed.sm.powercontrol  】
510  :【 wlan.fixed.sm.powercontrol.enabled 】    	{"SM Power Save"     【 wlan.fixed.sm.powercontrol.enabled  】
511  :【 wlan.fixed.sm.powercontrol.mode 】    	{"SM Mode"     【 wlan.fixed.sm.powercontrol.mode  】
512  :【 wlan.fixed.sm.powercontrol.reserved 】    	{"Reserved"     【 wlan.fixed.sm.powercontrol.reserved  】
513  :【 wlan.fixed.pco.phasecntrl 】    	{"Phased Coexistence Operation (PCO) Phase Control"     【 wlan.fixed.pco.phasecntrl  】
514  :【 wlan.fixed.psmp.paramset 】    	{"Power Save Multi-Poll (PSMP) Parameter Set"     【 wlan.fixed.psmp.paramset  】
515  :【 wlan.fixed.psmp.paramset.nsta 】    	{"Number of STA Info Fields Present"     【 wlan.fixed.psmp.paramset.nsta  】
516  :【 wlan.fixed.psmp.paramset.more 】    	{"More PSMP"     【 wlan.fixed.psmp.paramset.more  】
517  :【 wlan.fixed.psmp.paramset.seqduration 】    	{"PSMP Sequence Duration [us]"     【 wlan.fixed.psmp.paramset.seqduration  】
518  :【 wlan.fixed.mimo.control 】    	{"MIMO Control"     【 wlan.fixed.mimo.control  】
519  :【 wlan.fixed.mimo.control.ncindex 】    	{"Nc Index"     【 wlan.fixed.mimo.control.ncindex  】
520  :【 wlan.fixed.mimo.control.nrindex 】    	{"Nr Index"     【 wlan.fixed.mimo.control.nrindex  】
521  :【 wlan.fixed.mimo.control.chanwidth 】    	{"Channel Width"     【 wlan.fixed.mimo.control.chanwidth  】
522  :【 wlan.fixed.mimo.control.grouping 】    	{"Grouping (Ng)"     【 wlan.fixed.mimo.control.grouping  】
523  :【 wlan.fixed.mimo.control.cosize 】    	{"Coefficient Size (Nb)"     【 wlan.fixed.mimo.control.cosize  】
524  :【 wlan.fixed.mimo.control.codebookinfo 】    	{"Codebook Information"     【 wlan.fixed.mimo.control.codebookinfo  】
525  :【 wlan.fixed.mimo.control.matrixseg 】    	{"Remaining Matrix Segment"     【 wlan.fixed.mimo.control.matrixseg  】
526  :【 wlan.fixed.mimo.control.reserved 】    	{"Reserved"     【 wlan.fixed.mimo.control.reserved  】
527  :【 wlan.fixed.mimo.control.soundingtime 】    	{"Sounding Timestamp"     【 wlan.fixed.mimo.control.soundingtime  】
528  :【 wlan.fixed.psmp.stainfo 】    	{"Power Save Multi-Poll (PSMP) Station Information"     【 wlan.fixed.psmp.stainfo  】
529  :【 wlan.fixed.psmp.stainfo.type 】    	{"Sta Info Type"     【 wlan.fixed.psmp.stainfo.type  】
530  :【 wlan.fixed.psmp.stainfo.dttstart 】    	{"DTT Start Offset"     【 wlan.fixed.psmp.stainfo.dttstart  】
531  :【 wlan.fixed.psmp.stainfo.dttduration 】    	{"DTT Duration"     【 wlan.fixed.psmp.stainfo.dttduration  】
532  :【 wlan.fixed.psmp.stainfo.staid 】    	{"Target Station ID"     【 wlan.fixed.psmp.stainfo.staid  】
533  :【 wlan.fixed.psmp.stainfo.uttstart 】    	{"UTT Start Offset"     【 wlan.fixed.psmp.stainfo.uttstart  】
534  :【 wlan.fixed.psmp.stainfo.uttduration 】    	{"UTT Duration"     【 wlan.fixed.psmp.stainfo.uttduration  】
535  :【 wlan.fixed.psmp.stainfo.reserved 】    	{"Reserved"     【 wlan.fixed.psmp.stainfo.reserved  】
536  :【 wlan.fixed.psmp.stainfo.reserved64 】    	{"Reserved"     【 wlan.fixed.psmp.stainfo.reserved64  】
537  :【 wlan.fixed.psmp.stainfo.multicastid 】    	{"Power Save Multi-Poll (PSMP) Multicast ID"     【 wlan.fixed.psmp.stainfo.multicastid  】
538  :【 wlan.fixed.antsel 】    	{"Antenna Selection"     【 wlan.fixed.antsel  】
539  :【 wlan.fixed.antsel.ant0 】    	{"Antenna 0"     【 wlan.fixed.antsel.ant0  】
540  :【 wlan.fixed.antsel.ant1 】    	{"Antenna 1"     【 wlan.fixed.antsel.ant1  】
541  :【 wlan.fixed.antsel.ant2 】    	{"Antenna 2"     【 wlan.fixed.antsel.ant2  】
542  :【 wlan.fixed.antsel.ant3 】    	{"Antenna 3"     【 wlan.fixed.antsel.ant3  】
543  :【 wlan.fixed.antsel.ant4 】    	{"Antenna 4"     【 wlan.fixed.antsel.ant4  】
544  :【 wlan.fixed.antsel.ant5 】    	{"Antenna 5"     【 wlan.fixed.antsel.ant5  】
545  :【 wlan.fixed.antsel.ant6 】    	{"Antenna 6"     【 wlan.fixed.antsel.ant6  】
546  :【 wlan.fixed.antsel.ant7 】    	{"Antenna 7"     【 wlan.fixed.antsel.ant7  】
547  :【 wlan.fixed.extchansw 】    	{"Extended Channel Switch Announcement"     【 wlan.fixed.extchansw  】
548  :【 wlan.fixed.extchansw.switchmode 】    	{"Channel Switch Mode"     【 wlan.fixed.extchansw.switchmode  】
549  :【 wlan.fixed.extchansw.new.opeclass 】    	{"New Operating Class"     【 wlan.fixed.extchansw.new.opeclass  】
550  :【 wlan.fixed.extchansw.new.channumber 】    	{"New Channel Number"     【 wlan.fixed.extchansw.new.channumber  】
551  :【 wlan.extchanswitch.switchcount 】    	{"Channel Switch Count"     【 wlan.extchanswitch.switchcount  】
552  :【 wlan.fixed.extchansw 】    	{"HT Information"     【 wlan.fixed.extchansw  】
553  :【 wlan.fixed.mimo.control.chanwidth 】    	{"Information Request"     【 wlan.fixed.mimo.control.chanwidth  】
554  :【 wlan.fixed.mimo.control.chanwidth 】    	{"40 MHz Intolerant"     【 wlan.fixed.mimo.control.chanwidth  】
555  :【 wlan.fixed.mimo.control.chanwidth 】    	{"Station Channel Width"     【 wlan.fixed.mimo.control.chanwidth  】
556  :【 wlan.fixed.extchansw 】    	{"Reserved"     【 wlan.fixed.extchansw  】
557  :【 wlan.fixed.htact 】    	{"HT Action"     【 wlan.fixed.htact  】
558  :【 wlan.mimo.csimatrices.snr 】    	{"Signal to Noise Ratio (SNR)"     【 wlan.mimo.csimatrices.snr  】
559  :【 wlan.mimo.csimatrices 】    	{"CSI Matrices"     【 wlan.mimo.csimatrices  】
560  :【 wlan.mimo.csimatrices.bf 】    	{"Beamforming Feedback Matrices"     【 wlan.mimo.csimatrices.bf  】
561  :【 wlan.mimo.csimatrices.cbf 】    	{"Compressed Beamforming Feedback Matrices"     【 wlan.mimo.csimatrices.cbf  】
562  :【 wlan.fixed.publicact 】    	{"Public Action"     【 wlan.fixed.publicact  】
563  :【 wlan.fixed.publicact 】    	{"Protected Public Action"     【 wlan.fixed.publicact  】
564  :【 wlan.fixed.capabilities 】    	{"Capabilities Information"     【 wlan.fixed.capabilities  】
565  :【 wlan.fixed.capabilities.ess 】    	{"ESS capabilities"     【 wlan.fixed.capabilities.ess  】
566  :【 wlan.fixed.capabilities.ibss 】    	{"IBSS status"     【 wlan.fixed.capabilities.ibss  】
567  :【 wlan.fixed.capabilities.cfpoll.sta 】    	{"CFP participation capabilities"     【 wlan.fixed.capabilities.cfpoll.sta  】
568  :【 wlan.fixed.capabilities.cfpoll.ap 】    	{"CFP participation capabilities"     【 wlan.fixed.capabilities.cfpoll.ap  】
569  :【 wlan.fixed.capabilities.privacy 】    	{"Privacy"     【 wlan.fixed.capabilities.privacy  】
570  :【 wlan.fixed.capabilities.preamble 】    	{"Short Preamble"     【 wlan.fixed.capabilities.preamble  】
571  :【 wlan.fixed.capabilities.pbcc 】    	{"PBCC"     【 wlan.fixed.capabilities.pbcc  】
572  :【 wlan.fixed.capabilities.agility 】    	{"Channel Agility"     【 wlan.fixed.capabilities.agility  】
573  :【 wlan.fixed.capabilities.spec_man 】    	{"Spectrum Management"     【 wlan.fixed.capabilities.spec_man  】
574  :【 wlan.fixed.capabilities.short_slot_time 】    	{"Short Slot Time"     【 wlan.fixed.capabilities.short_slot_time  】
575  :【 wlan.fixed.capabilities.apsd 】    	{"Automatic Power Save Delivery"     【 wlan.fixed.capabilities.apsd  】
576  :【 wlan.fixed.capabilities.radio_measurement 】    	{"Radio Measurement"     【 wlan.fixed.capabilities.radio_measurement  】
577  :【 wlan.fixed.capabilities.dsss_ofdm 】    	{"DSSS-OFDM"     【 wlan.fixed.capabilities.dsss_ofdm  】
578  :【 wlan.fixed.capabilities.del_blk_ack 】    	{"Delayed Block Ack"     【 wlan.fixed.capabilities.del_blk_ack  】
579  :【 wlan.fixed.capabilities.imm_blk_ack 】    	{"Immediate Block Ack"     【 wlan.fixed.capabilities.imm_blk_ack  】
580  :【 wlan.fixed.auth_seq 】    	{"Authentication SEQ"     【 wlan.fixed.auth_seq  】
581  :【 wlan.fixed.aid 】    	{"Association ID"     【 wlan.fixed.aid  】
582  :【 wlan.fixed.listen_ival 】    	{"Listen Interval"     【 wlan.fixed.listen_ival  】
583  :【 wlan.fixed.current_ap 】    	{"Current AP"     【 wlan.fixed.current_ap  】
584  :【 wlan.fixed.reason_code 】    	{"Reason code"     【 wlan.fixed.reason_code  】
585  :【 wlan.fixed.status_code 】    	{"Status code"     【 wlan.fixed.status_code  】
586  :【 wlan.fixed.category_code 】    	{"Category code"     【 wlan.fixed.category_code  】
587  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
588  :【 wlan.fixed.dialog_token 】    	{"Dialog token"     【 wlan.fixed.dialog_token  】
589  :【 wlan.fixed.followup_dialog_token 】    	{"Followup Dialog token"     【 wlan.fixed.followup_dialog_token  】
590  :【 wlan.fixed.mrvl_action_type 】    	{"Marvell Action type"     【 wlan.fixed.mrvl_action_type  】
591  :【 wlan.fixed.mrvl_mesh_action 】    	{"Mesh action(Marvell)"     【 wlan.fixed.mrvl_mesh_action  】
592  :【 wlan.fixed.length 】    	{"Message Length"     【 wlan.fixed.length  】
593  :【 wlan.fixed.mode 】    	{"Message Mode"     【 wlan.fixed.mode  】
594  :【 wlan.fixed.ttl 】    	{"Message TTL"     【 wlan.fixed.ttl  】
595  :【 wlan.fixed.dstcount 】    	{"Destination Count"     【 wlan.fixed.dstcount  】
596  :【 wlan.fixed.hopcount 】    	{"Hop Count"     【 wlan.fixed.hopcount  】
597  :【 wlan.fixed.rreqid 】    	{"RREQ ID"     【 wlan.fixed.rreqid  】
598  :【 wlan.fixed.sa 】    	{"Source Address"     【 wlan.fixed.sa  】
599  :【 wlan.fixed.ssn 】    	{"SSN"     【 wlan.fixed.ssn  】
600  :【 wlan.fixed.metric 】    	{"Metric"     【 wlan.fixed.metric  】
601  :【 wlan.fixed.hopcount 】    	{"RREQ Flags"     【 wlan.fixed.hopcount  】
602  :【 wlan.fixed.da 】    	{"Destination Address"     【 wlan.fixed.da  】
603  :【 wlan.fixed.dsn 】    	{"DSN"     【 wlan.fixed.dsn  】
604  :【 wlan.fixed.lifetime 】    	{"Lifetime"     【 wlan.fixed.lifetime  】
605  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
606  :【 wlan.fixed.status_code 】    	{"Status code"     【 wlan.fixed.status_code  】
607  :【 wlan.fixed.mesh_action 】    	{"Mesh Action code"     【 wlan.fixed.mesh_action  】
608  :【 wlan.fixed.multihop_action 】    	{"Multihop Action code"     【 wlan.fixed.multihop_action  】
609  :【 wlan.fixed.mesh_flags 】    	{"Mesh Flags"     【 wlan.fixed.mesh_flags  】
610  :【 wlan.fixed.mesh_ttl 】    	{"Mesh TTL"     【 wlan.fixed.mesh_ttl  】
611  :【 wlan.fixed.mesh_sequence 】    	{"Sequence Number"     【 wlan.fixed.mesh_sequence  】
612  :【 wlan.fixed.mesh_addr4 】    	{"Mesh Extended Address 4"     【 wlan.fixed.mesh_addr4  】
613  :【 wlan.fixed.mesh_addr5 】    	{"Mesh Extended Address 5"     【 wlan.fixed.mesh_addr5  】
614  :【 wlan.fixed.mesh_addr6 】    	{"Mesh Extended Address 6"     【 wlan.fixed.mesh_addr6  】
615  :【 wlan.fixed.selfprot_action 】    	{"Self-protected Action code"     【 wlan.fixed.selfprot_action  】
616  :【 wlan.peering.proto 】    	{"Mesh Peering Protocol ID"     【 wlan.peering.proto  】
617  :【 wlan.peering.local_id 】    	{"Local Link ID"     【 wlan.peering.local_id  】
618  :【 wlan.peering.peer_id 】    	{"Peer Link ID"     【 wlan.peering.peer_id  】
619  :【 wlan.hwmp.flags 】    	{"HWMP Flags"     【 wlan.hwmp.flags  】
620  :【 wlan.hwmp.hopcount 】    	{"HWMP Hop Count"     【 wlan.hwmp.hopcount  】
621  :【 wlan.hwmp.ttl 】    	{"HWMP TTL"     【 wlan.hwmp.ttl  】
622  :【 wlan.hwmp.pdid 】    	{"HWMP Path Discovery ID"     【 wlan.hwmp.pdid  】
623  :【 wlan.hwmp.orig_sta 】    	{"Originator STA Address"     【 wlan.hwmp.orig_sta  】
624  :【 wlan.hwmp.orig_sn 】    	{"HWMP Originator Sequence Number"     【 wlan.hwmp.orig_sn  】
625  :【 wlan.hwmp.orig_ext 】    	{"Originator External Address"     【 wlan.hwmp.orig_ext  】
626  :【 wlan.hwmp.lifetime 】    	{"HWMP Lifetime"     【 wlan.hwmp.lifetime  】
627  :【 wlan.hwmp.metric 】    	{"HWMP Metric"     【 wlan.hwmp.metric  】
628  :【 wlan.hwmp.targ_count 】    	{"HWMP Target Count"     【 wlan.hwmp.targ_count  】
629  :【 wlan.hwmp.targ_flags 】    	{"HWMP Per-Target Flags"     【 wlan.hwmp.targ_flags  】
630  :【 wlan.hwmp.to_flag 】    	{"TO Flag"     【 wlan.hwmp.to_flag  】
631  :【 wlan.hwmp.usn_flag 】    	{"USN Flag"     【 wlan.hwmp.usn_flag  】
632  :【 wlan.hwmp.targ_sta 】    	{"Target STA Address"     【 wlan.hwmp.targ_sta  】
633  :【 wlan.hwmp.targ_ext 】    	{"Target External Address"     【 wlan.hwmp.targ_ext  】
634  :【 wlan.hwmp.targ_sn 】    	{"Target HWMP Sequence Number"     【 wlan.hwmp.targ_sn  】
635  :【 wlan.mesh.config.ps_protocol 】    	{"Path Selection Protocol"     【 wlan.mesh.config.ps_protocol  】
636  :【 wlan.mesh.config.ps_metric 】    	{"Path Selection Metric"     【 wlan.mesh.config.ps_metric  】
637  :【 wlan.mesh.config.cong_ctl 】    	{"Congestion Control"     【 wlan.mesh.config.cong_ctl  】
638  :【 wlan.mesh.config.sync_method 】    	{"Synchronization Method"     【 wlan.mesh.config.sync_method  】
639  :【 wlan.mesh.config.auth_protocol 】    	{"Authentication Protocol"     【 wlan.mesh.config.auth_protocol  】
640  :【 wlan.mesh.config.formation_info 】    	{"Formation Info"     【 wlan.mesh.config.formation_info  】
641  :【 wlan.mesh.config.formation_info.num_peers 】    	{"Number of Peerings"     【 wlan.mesh.config.formation_info.num_peers  】
642  :【 wlan.mesh.config.cap 】    	{"Capability"     【 wlan.mesh.config.cap  】
643  :【 wlan.mesh.config.cap.accept 】    	{"Accepting Additional Mesh Peerings"     【 wlan.mesh.config.cap.accept  】
644  :【 wlan.mesh.config.cap.mcca_support 】    	{"MCCA Support"     【 wlan.mesh.config.cap.mcca_support  】
645  :【 wlan.mesh.config.cap.mcca_enabled 】    	{"MCCA Enabled"     【 wlan.mesh.config.cap.mcca_enabled  】
646  :【 wlan.mesh.config.cap.forwarding 】    	{"Mesh Forwarding"     【 wlan.mesh.config.cap.forwarding  】
647  :【 wlan.mesh.config.cap.mbca_enabled 】    	{"MBCA Enabled"     【 wlan.mesh.config.cap.mbca_enabled  】
648  :【 wlan.mesh.config.cap.tbtt_adjusting 】    	{"TBTT Adjustment"     【 wlan.mesh.config.cap.tbtt_adjusting  】
649  :【 wlan.mesh.config.cap.power_save_level 】    	{"Power Save"     【 wlan.mesh.config.cap.power_save_level  】
650  :【 wlan.mesh.id 】    	{"Mesh ID"     【 wlan.mesh.id  】
651  :【 wlan.rann.flags 】    	{"RANN Flags"     【 wlan.rann.flags  】
652  :【 wlan.rann.root_sta 】    	{"Root STA Address"     【 wlan.rann.root_sta  】 FT_ETHER, BASE_NONE, NULL, 0,
653  :【 wlan.rann.rann_sn 】    	{"Root STA Sequence Number"     【 wlan.rann.rann_sn  】
654  :【 wlan.rann.interval 】    	{"RANN Interval"     【 wlan.rann.interval  】
655  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
656  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
657  :【 wlan.fixed.check_beacon 】    	{"Check Beacon"     【 wlan.fixed.check_beacon  】
658  :【 wlan.fixed.tod 】    	{"TOD"     【 wlan.fixed.tod  】
659  :【 wlan.fixed.toa 】    	{"TOA"     【 wlan.fixed.toa  】
660  :【 wlan.fixed.max_tod_err 】    	{"MAX TOD ERROR"     【 wlan.fixed.max_tod_err  】
661  :【 wlan.fixed.max_toa_err 】    	{"MAX TOA ERROR"     【 wlan.fixed.max_toa_err  】
662  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
663  :【 wlan.fixed.dst_mac_addr 】    	{"Destination address"     【 wlan.fixed.dst_mac_addr  】
664  :【 wlan.fixed.src_mac_addr 】    	{"Source address"     【 wlan.fixed.src_mac_addr  】
665  :【 wlan.fixed.req_ap_addr 】    	{"RequesterAP address"     【 wlan.fixed.req_ap_addr  】
666  :【 wlan.fixed.res_ap_addr 】    	{"ResponderAP address"     【 wlan.fixed.res_ap_addr  】
667  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
668  :【 wlan.fixed.sta_address 】    	{"STA Address"     【 wlan.fixed.sta_address  】
669  :【 wlan.fixed.target_ap_address 】    	{"Target AP Address"     【 wlan.fixed.target_ap_address  】
670  :【 wlan.fixed.gas_comeback_delay 】    	{"GAS Comeback Delay"     【 wlan.fixed.gas_comeback_delay  】
671  :【 wlan.fixed.gas_fragment_id 】    	{"GAS Query Response Fragment ID"     【 wlan.fixed.gas_fragment_id  】
672  :【 wlan.fixed.more_gas_fragments 】    	{"More GAS Fragments"     【 wlan.fixed.more_gas_fragments  】
673  :【 wlan.fixed.query_request_length 】    	{"Query Request Length"     【 wlan.fixed.query_request_length  】
674  :【 wlan.fixed.query_request 】    	{"Query Request"     【 wlan.fixed.query_request  】
675  :【 wlan.fixed.query_response_length 】    	{"Query Response Length"     【 wlan.fixed.query_response_length  】
676  :【 wlan.fixed.query_response 】    	{"Query Response"     【 wlan.fixed.query_response  】
677  :【 wlan.fixed.fragments 】    	{"GAS Query Response fragments"     【 wlan.fixed.fragments  】
678  :【 wlan.fixed.fragment 】    	{"GAS Query Response fragment"     【 wlan.fixed.fragment  】
679  :【 wlan.fixed.fragment.overlap 】    	{"GAS Query Response fragment overlap"     【 wlan.fixed.fragment.overlap  】
680  :【 wlan.fixed.fragment.overlap.conflicts 】    	{"GAS Query Response fragment overlapping with conflicting data"     【 wlan.fixed.fragment.overlap.conflicts  】
681  :【 wlan.fixed.fragment.multiple_tails 】    	{"GAS Query Response has multiple tail fragments  】  "wlan.fixed.fragment.multiple_tails  】
682  :【 wlan.fixed.fragment.too_long_fragment 】    	{"GAS Query Response fragment too long"     【 wlan.fixed.fragment.too_long_fragment  】
683  :【 wlan.fixed.fragment.error 】    	{"GAS Query Response reassembly error"     【 wlan.fixed.fragment.error  】
684  :【 wlan.fixed.fragment.count 】    	{"GAS Query Response fragment count"     【 wlan.fixed.fragment.count  】
685  :【 wlan.fixed.reassembled.in 】    	{"Reassembled in"     【 wlan.fixed.reassembled.in  】
686  :【 wlan.fixed.reassembled.length 】    	{"Reassembled length"     【 wlan.fixed.reassembled.length  】
687  :【 wlan.fixed.anqp.info_id 】    	{"Info ID"     【 wlan.fixed.anqp.info_id  】
688  :【 wlan.fixed.anqp.info_length 】    	{"Length"     【 wlan.fixed.anqp.info_length  】
689  :【 wlan.fixed.anqp.info 】    	{"Information"     【 wlan.fixed.anqp.info  】
690  :【 wlan.fixed.anqp.query_id 】    	{"ANQP Query ID"     【 wlan.fixed.anqp.query_id  】
691  :【 wlan.fixed.anqp.capability 】    	{"ANQP Capability"     【 wlan.fixed.anqp.capability  】
692  :【 wlan.fixed.anqp.capability_vlen 】    	{"Vendor-specific Capability Length"     【 wlan.fixed.anqp.capability_vlen  】
693  :【 wlan.fixed.anqp.capability_vendor 】    	{"Vendor-specific Capability"     【 wlan.fixed.anqp.capability_vendor  】
694  :【 wlan.fixed.venue_info.group 】    	{"Venue Group"     【 wlan.fixed.venue_info.group  】
695  :【 wlan.fixed.venue_info.type 】    	{"Venue Type"     【 wlan.fixed.venue_info.type  】
696  :【 wlan.fixed.anqp.venue.length 】    	{"Venue Name Duple Length"     【 wlan.fixed.anqp.venue.length  】
697  :【 wlan.fixed.anqp.venue.language 】    	{"Language Code"     【 wlan.fixed.anqp.venue.language  】
698  :【 wlan.fixed.anqp.venue.name 】    	{"Venue Name"     【 wlan.fixed.anqp.venue.name  】
699  :【 wlan.fixed.anqp.nw_auth_type.indicator 】    	{"Network Authentication Type Indicator"     【 wlan.fixed.anqp.nw_auth_type.indicator  】
700  :【 wlan.fixed.anqp.nw_auth_type.url_len 】    	{"Re-direct URL Length"     【 wlan.fixed.anqp.nw_auth_type.url_len  】
701  :【 wlan.fixed.anqp.nw_auth_type_url 】    	{"Re-direct URL"     【 wlan.fixed.anqp.nw_auth_type_url  】
702  :【 wlan.fixed.anqp.roaming_consortium.oi_len 】    	{"OI Length"     【 wlan.fixed.anqp.roaming_consortium.oi_len  】
703  :【 wlan.fixed.anqp.roaming_consortium.oi 】    	{"OI"     【 wlan.fixed.anqp.roaming_consortium.oi  】
704  :【 wlan.fixed.anqp.ip_addr_availability.ipv6 】    	{"IPv6 Address"     【 wlan.fixed.anqp.ip_addr_availability.ipv6  】
705  :【 wlan.fixed.anqp.ip_addr_availability.ipv4 】    	{"IPv4 Address"     【 wlan.fixed.anqp.ip_addr_availability.ipv4  】
706  :【 wlan.fixed.anqp.nai_realm_list.count 】    	{"NAI Realm Count"     【 wlan.fixed.anqp.nai_realm_list.count  】
707  :【 wlan.fixed.anqp.nai_realm_list.field_len 】    	{"NAI Realm Data Field Length"     【 wlan.fixed.anqp.nai_realm_list.field_len  】
708  :【 wlan.fixed.naqp_nai_realm_list.encoding 】    	{"NAI Realm Encoding"     【 wlan.fixed.naqp_nai_realm_list.encoding  】
709  :【 wlan.fixed.naqp_nai_realm_list.realm_length 】    	{"NAI Realm Length"     【 wlan.fixed.naqp_nai_realm_list.realm_length  】
710  :【 wlan.fixed.naqp_nai_realm_list.realm 】    	{"NAI Realm"     【 wlan.fixed.naqp_nai_realm_list.realm  】
711  :【 wlan.fixed.naqp_nai_realm_list.eap_method_count 】    	{"EAP Method Count"     【 wlan.fixed.naqp_nai_realm_list.eap_method_count  】
712  :【 wlan.fixed.naqp_nai_realm_list.eap_method_len 】    	{"EAP Method subfield Length"     【 wlan.fixed.naqp_nai_realm_list.eap_method_len  】
713  :【 wlan.fixed.naqp_nai_realm_list.eap_method 】    	{"EAP Method"     【 wlan.fixed.naqp_nai_realm_list.eap_method  】
714  :【 wlan.fixed.naqp_nai_realm_list.auth_param_count 】    	{"Authentication Parameter Count"     【 wlan.fixed.naqp_nai_realm_list.auth_param_count  】
715  :【 wlan.fixed.naqp_nai_realm_list.auth_param_id 】    	{"Authentication Parameter ID"     【 wlan.fixed.naqp_nai_realm_list.auth_param_id  】
716  :【 wlan.fixed.naqp_nai_realm_list.auth_param_len 】    	{"Authentication Parameter Length"     【 wlan.fixed.naqp_nai_realm_list.auth_param_len  】
717  :【 wlan.fixed.naqp_nai_realm_list.auth_param_value 】    	{"Authentication Parameter Value"     【 wlan.fixed.naqp_nai_realm_list.auth_param_value  】
718  :【 wlan.fixed.anqp.3gpp_cellular_info.gud 】    	{"GUD"     【 wlan.fixed.anqp.3gpp_cellular_info.gud  】
719  :【 wlan.fixed.anqp.3gpp_cellular_info.udhl 】    	{"UDHL"     【 wlan.fixed.anqp.3gpp_cellular_info.udhl  】
720  :【 wlan.fixed.anqp.3gpp_cellular_info.iei 】    	{"IEI"     【 wlan.fixed.anqp.3gpp_cellular_info.iei  】
721  :【 wlan.fixed.anqp.3gpp_cellular_info.plmn_len 】    	{"PLMN Length"     【 wlan.fixed.anqp.3gpp_cellular_info.plmn_len  】
722  :【 wlan.fixed.anqp.3gpp_cellular_info.num_plmns 】    	{"Number of PLMNs"     【 wlan.fixed.anqp.3gpp_cellular_info.num_plmns  】
723  :【 wlan.fixed.anqp.3gpp_cellular_info.plmn_info 】    	{"PLMN"     【 wlan.fixed.anqp.3gpp_cellular_info.plmn_info  】
724  :【 wlan.fixed.anqp.domain_name_list.len 】    	{"Domain Name Length"     【 wlan.fixed.anqp.domain_name_list.len  】
725  :【 wlan.fixed.anqp.domain_name_list.name 】    	{"Domain Name"     【 wlan.fixed.anqp.domain_name_list.name  】
726  :【 wlan.fixed.dls_timeout 】    	{"DLS timeout"     【 wlan.fixed.dls_timeout  】
727  :【 wlan.fixed.action_code 】    	{"Action code"     【 wlan.fixed.action_code  】
728  :【 wlan.fixed.transaction_id 】    	{"Transaction Id"     【 wlan.fixed.transaction_id  】
729  :【 wlan.fixed.send_confirm 】    	{"Send-Confirm"     【 wlan.fixed.send_confirm  】
730  :【 wlan.fixed.anti_clogging_token 】    	{"Anti-Clogging Token"     【 wlan.fixed.anti_clogging_token  】
731  :【 wlan.fixed.scalar 】    	{"Scalar"     【 wlan.fixed.scalar  】
732  :【 wlan.fixed.finite_field_element 】    	{"Finite Field Element"     【 wlan.fixed.finite_field_element  】
733  :【 wlan.fixed.confirm 】    	{"Confirm"     【 wlan.fixed.confirm  】
734  :【 wlan.fixed.finite_cyclic_group 】    	{"Group Id"     【 wlan.fixed.finite_cyclic_group  】
735  :【 wlan.fixed.sae_message_type 】    	{"SAE Message Type"     【 wlan.fixed.sae_message_type  】
736  :【 wlan.anqp.wfa.subtype 】    	{"WFA Subtype"     【 wlan.anqp.wfa.subtype  】
737  :【 wlan.wfa.dpp.subtype 】    	{"DPP Subtype"     【 wlan.wfa.dpp.subtype  】
738  :【 wlan.hs20.indication.dgaf_disabled 】    	{"DGAF Disabled"     【 wlan.hs20.indication.dgaf_disabled  】
739  :【 wlan.hs20.indication.pps_mo_id_present 】    	{"PPS MO ID Present"     【 wlan.hs20.indication.pps_mo_id_present  】
740  :【 wlan.hs20.indication.anqp_domain_id_present 】    	{"ANQP Domain ID Present"     【 wlan.hs20.indication.anqp_domain_id_present  】
741  :【 wlan.hs20.indication.reserved 】    	{ "Reserved"     【 wlan.hs20.indication.reserved  】
742  :【 wlan.hs20.indication.release_number 】    	{"Release Number"     【 wlan.hs20.indication.release_number  】
743  :【 wlan.hs20.indication.pps_mo_id 】    	{"PPS MO ID"     【 wlan.hs20.indication.pps_mo_id  】
744  :【 wlan.hs20.indication.domain_id 】    	{"ANQP Domain ID"     【 wlan.hs20.indication.domain_id  】
745  :【 wlan.hs20.anqp.subtype 】    	{"Subtype"     【 wlan.hs20.anqp.subtype  】
746  :【 wlan.hs20.anqp.reserved 】    	{"Reserved"     【 wlan.hs20.anqp.reserved  】
747  :【 wlan.hs20.anqp.payload 】    	{"Payload"     【 wlan.hs20.anqp.payload  】
748  :【 wlan.hs20.anqp.hs_query_list 】    	{"Queried Subtype"     【 wlan.hs20.anqp.hs_query_list  】
749  :【 wlan.hs20.anqp.hs_capability_list 】    	{"Capability"     【 wlan.hs20.anqp.hs_capability_list  】
750  :【 wlan.hs20.anqp.ofn.length 】    	{"Length"     【 wlan.hs20.anqp.ofn.length  】
751  :【 wlan.hs20.anqp.ofn.language 】    	{"Language Code"     【 wlan.hs20.anqp.ofn.language  】
752  :【 wlan.hs20.anqp.ofn.name 】    	{"Operator Friendly Name"     【 wlan.hs20.anqp.ofn.name  】
753  :【 wlan.hs20.anqp.wan_metrics.link_status 】    	{"Link Status"     【 wlan.hs20.anqp.wan_metrics.link_status  】
754  :【 wlan.hs20.anqp.wan_metrics.symmetric_link 】    	{"Symmetric Link"     【 wlan.hs20.anqp.wan_metrics.symmetric_link  】
755  :【 wlan.hs20.anqp.wan_metrics.at_capacity 】    	{"At Capacity"     【 wlan.hs20.anqp.wan_metrics.at_capacity  】
756  :【 wlan.hs20.anqp.wan_metrics.reserved 】    	{"Reserved"     【 wlan.hs20.anqp.wan_metrics.reserved  】
757  :【 wlan.hs20.anqp.wan_metrics.downlink_speed 】    	{"Downlink Speed"     【 wlan.hs20.anqp.wan_metrics.downlink_speed  】
758  :【 wlan.hs20.anqp.wan_metrics.uplink_speed 】    	{"Uplink Speed"     【 wlan.hs20.anqp.wan_metrics.uplink_speed  】
759  :【 wlan.hs20.anqp.wan_metrics.downlink_load 】    	{"Downlink Load"     【 wlan.hs20.anqp.wan_metrics.downlink_load  】
760  :【 wlan.hs20.anqp.wan_metrics.uplink_load 】    	{"Uplink Load"     【 wlan.hs20.anqp.wan_metrics.uplink_load  】
761  :【 wlan.hs20.anqp.wan_metrics.lmd 】    	{"LMD"     【 wlan.hs20.anqp.wan_metrics.lmd  】
762  :【 wlan.hs20.anqp.cc.ip_proto 】    	{"IP Protocol"     【 wlan.hs20.anqp.cc.ip_proto  】
763  :【 wlan.hs20.anqp.cc.port_num 】    	{"Port Number"     【 wlan.hs20.anqp.cc.port_num  】
764  :【 wlan.hs20.anqp.cc.status 】    	{"Status"     【 wlan.hs20.anqp.cc.status  】
765  :【 wlan.hs20.anqp.nai_hrq.count 】    	{"NAI Home Realm Count"     【 wlan.hs20.anqp.nai_hrq.count  】
766  :   	{"wlan.hs20.anqp.nai_hrq.encoding_type  】
767  :【 wlan.hs20.anqp.nai_hrq.length 】    	{"NAI Home Realm Name Length"     【 wlan.hs20.anqp.nai_hrq.length  】
768  :【 wlan.hs20.anqp.nai_hrq.name 】    	{"NAI Home Realm Name"     【 wlan.hs20.anqp.nai_hrq.name  】
769  :【 wlan.hs20.anqp.oper_class_indic.oper_class 】    	{"Operating Class"     【 wlan.hs20.anqp.oper_class_indic.oper_class  】
770  :【 wlan.hs20.osu_friendly_names_len 】    	{"OSU Friendly Name Length"     【 wlan.hs20.osu_friendly_names_len  】
771  :【 wlan.hs20.osu_friendly_name.len 】    	{"Length"     【 wlan.hs20.osu_friendly_name.len  】
772  :【 wlan.hs20.osu_friendly_name.language 】    	{"Language Code"     【 wlan.hs20.osu_friendly_name.language  】
773  :【 wlan.hs20.osu_friendly_name.name 】    	{"OSU Friendly Name"     【 wlan.hs20.osu_friendly_name.name  】
774  :【 wlan.hs20.osu_server_uri_len 】    	{"OSU Server URI Length"     【 wlan.hs20.osu_server_uri_len  】
775  :【 wlan.hs20.osu_server_uri 】    	{"OSU Server URI"     【 wlan.hs20.osu_server_uri  】
776  :【 wlan.hs20.osu_method_list_len 】    	{"OSU Method List Length"     【 wlan.hs20.osu_method_list_len  】
777  :【 wlan.hs20.osu_method_list.method 】    	{"OSU Method"     【 wlan.hs20.osu_method_list.method  】
778  :【 wlan.hs20.osu_icons_avail_len 】    	{"Icons Available Length"     【 wlan.hs20.osu_icons_avail_len  】
779  :【 wlan.hs20.anqp_osu_prov_list.ssid_len 】    	{"SSID Length"     【 wlan.hs20.anqp_osu_prov_list.ssid_len  】
780  :【 wlan.hs20.anqp_osu_prov_list.ssid 】    	{"SSID"     【 wlan.hs20.anqp_osu_prov_list.ssid  】
781  :【 wlan.hs20.anqp_osu_prov_list.number 】    	{"Number of OSU Providers"     【 wlan.hs20.anqp_osu_prov_list.number  】
782  :【 wlan.hs20.anqp_osu_prov.len 】    	{"OSU Provider Length"     【 wlan.hs20.anqp_osu_prov.len  】
783  :【 wlan.hs20.anqp_icon_request.icon_filename 】    	{"Icon Filename"     【 wlan.hs20.anqp_icon_request.icon_filename  】
784  :【 wlan.hs20.osu_icons_avail.icon_width 】    	{"Icon Width"     【 wlan.hs20.osu_icons_avail.icon_width  】
785  :【 wlan.hs20.osu_icons_avail.icon_height 】    	{"Icon Height"     【 wlan.hs20.osu_icons_avail.icon_height  】
786  :【 wlan.hs20.osu_icons_avail.lang_code 】    	{"Language Code"     【 wlan.hs20.osu_icons_avail.lang_code  】
787  :【 wlan.hs20.osu_icons_avail.icon_type_len 】    	{"Icon Type Length"     【 wlan.hs20.osu_icons_avail.icon_type_len  】
788  :【 wlan.hs20.osu_icons_avail.icon_type 】    	{"Icon Type"     【 wlan.hs20.osu_icons_avail.icon_type  】
789  :【 wlan.hs20.osu_icons_avail.icon_filename_len 】    	{"Icon Filename Length"     【 wlan.hs20.osu_icons_avail.icon_filename_len  】
790  :【 wlan.hs20.osu_icons_avail.icon_filename 】    	{"Icon Filename"     【 wlan.hs20.osu_icons_avail.icon_filename  】
791  :【 wlan.hs20.osu_nai.len 】    	{"OSU_NAI Length"     【 wlan.hs20.osu_nai.len  】
792  :【 wlan.hs20.osu_nai 】    	{"OSU_NAI"     【 wlan.hs20.osu_nai  】
793  :【 wlan.hs20.osu_service_desc_len 】    	{"OSU Service Desctription Length"     【 wlan.hs20.osu_service_desc_len  】
794  :【 wlan.hs20.osu_service_desc.duple.len 】    	{"Length"     【 wlan.hs20.osu_service_desc.duple.len  】
795  :【 wlan.hs20.osu_service_desc.duple.lang 】    	{"Language Code"     【 wlan.hs20.osu_service_desc.duple.lang  】
796  :【 wlan.hs20.osu_service_desc.duple.desc 】    	{"OSU Service Description"     【 wlan.hs20.osu_service_desc.duple.desc  】
797  :【 wlan.hs20.anqp_icon_request.download_status 】    	{"Download Status Code"     【 wlan.hs20.anqp_icon_request.download_status  】
798  :【 wlan.hs20.anqp_icon_request.icon_type_len 】    	{"Icon Type Length"     【 wlan.hs20.anqp_icon_request.icon_type_len  】
799  :【 wlan.hs20.anqp_icon_request.icon_type 】    	{"Icon Type"     【 wlan.hs20.anqp_icon_request.icon_type  】
800  :【 wlan.anqp_icon_request.icon_binary_data_len 】    	{"Icon Binary Data Length"     【 wlan.anqp_icon_request.icon_binary_data_len  】
801  :【 wlan.h220.anqp_icon_request.icon_binary_data 】    	{"Icon Binary Data"     【 wlan.h220.anqp_icon_request.icon_binary_data  】
802  :【 wlan.hs20.subs_remediation.server_url_len 】    	{"Server URL Length"     【 wlan.hs20.subs_remediation.server_url_len  】
803  :【 wlan.hs20.subs_remediation.server_url 】    	{"Server URL"     【 wlan.hs20.subs_remediation.server_url  】
804  :【 wlan.hs20.subs_remediation.server_method 】    	{"Server Method"     【 wlan.hs20.subs_remediation.server_method  】
805  :【 wlan.hs20.deauth.reason_code 】    	{"De-Auth Reason Code"     【 wlan.hs20.deauth.reason_code  】
806  :【 wlan.hs20.deauth.reauth_delay 】    	{"Re-Auth Delay"     【 wlan.hs20.deauth.reauth_delay  】
807  :【 wlan.hs20.deauth.reason_url_len 】    	{"Reason URL Length"     【 wlan.hs20.deauth.reason_url_len  】
808  :【 wlan.hs20.deauth.reason_url 】    	{"Reason URL"     【 wlan.hs20.deauth.reason_url  】
809  :【 wlan.hs20.venue_url.len 】    	{"Length"     【 wlan.hs20.venue_url.len  】
810  :【 wlan.hs20.venue_url.venue_num 】    	{"Venue number"     【 wlan.hs20.venue_url.venue_num  】
811  :【 wlan.hs20.venue_url.url 】    	{"Venue URL"     【 wlan.hs20.venue_url.url  】
812  :【 wlan.hs20.advice_of_charge.len 】    	{"Length"     【 wlan.hs20.advice_of_charge.len  】
813  :【 wlan.hs20.advice_of_charge.type 】    	{"Advice of Charge Type"     【 wlan.hs20.advice_of_charge.type  】
814  :【 wlan.hs20.advice_of_charge.nai_realm_enc 】    	{"NAI Realm Encoding"     【 wlan.hs20.advice_of_charge.nai_realm_enc  】
815  :【 wlan.hs20.advice_of_charge.nai_realm_len 】    	{"NAI Realm Length"     【 wlan.hs20.advice_of_charge.nai_realm_len  】
816  :【 wlan.hs20.advice_of_charge.nai_realm 】    	{"NAI Realm"     【 wlan.hs20.advice_of_charge.nai_realm  】
817  :【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_len 】    	{"Plan length"     【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_len  】
818  :【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_lang 】    	{"Plan language"     【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_lang  】
819  :【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_curcy 】    	{"Plan currency"     【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_curcy  】
820  :【 wlan.hs20.advice_of_charge.plan_info_tuples.info 】    	{"Plan information"     【 wlan.hs20.advice_of_charge.plan_info_tuples.info  】
821  :【 wlan.tag 】    	{"Tag"     【 wlan.tag  】
822  :【 wlan.tag.number 】    	{"Tag Number"     【 wlan.tag.number  】
823  :【 wlan.tag.length 】    	{"Tag length"     【 wlan.tag.length  】
824  :【 wlan.tag.data 】    	{"Tag Data"     【 wlan.tag.data  】
825  :【 wlan.tag.oui 】    	{"OUI"     【 wlan.tag.oui  】
826  :【 wlan.tag.oui.wfa_subtype 】    	{"WFA Subtype"     【 wlan.tag.oui.wfa_subtype  】
827  :【 wlan.ds.current_channel 】    	{"Current Channel"     【 wlan.ds.current_channel  】
828  :【 wlan.cfp.count 】    	{"CFP Count"     【 wlan.cfp.count  】
829  :【 wlan.cfp.period 】    	{"CFP Period"     【 wlan.cfp.period  】
830  :【 wlan.cfp.max_duration 】    	{"CFP Max Duration"     【 wlan.cfp.max_duration  】
831  :【 wlan.cfp.dur_remaining 】    	{"CFP Dur Remaining"     【 wlan.cfp.dur_remaining  】
832  :【 wlan.tag.vendor.oui.type 】    	{"Vendor Specific OUI Type"     【 wlan.tag.vendor.oui.type  】
833  :【 wlan.tag.vendor.data 】    	{"Vendor Specific Data"     【 wlan.tag.vendor.data  】
834  :【 wlan.tim.dtim_count 】    	{"DTIM count"     【 wlan.tim.dtim_count  】
835  :【 wlan.tim.dtim_period 】    	{"DTIM period"     【 wlan.tim.dtim_period  】
836  :【 wlan.tim.bmapctl 】    	{"Bitmap control"     【 wlan.tim.bmapctl  】
837  :【 wlan.tim.bmapctl.multicast 】    	{"Multicast"     【 wlan.tim.bmapctl.multicast  】
838  :【 wlan.tim.bmapctl.offset 】    	{"Bitmap Offset"     【 wlan.tim.bmapctl.offset  】
839  :【 wlan.tim.partial_virtual_bitmap 】    	{"Partial Virtual Bitmap"     【 wlan.tim.partial_virtual_bitmap  】
840  :【 wlan.tim.aid 】    	{"Association ID"     【 wlan.tim.aid  】
841  :【 wlan.ibss.atim_windows 】    	{"Atim Windows"     【 wlan.ibss.atim_windows  】
842  :【 wlan.country_info.code 】    	{"Code"     【 wlan.country_info.code  】
843  :【 wlan.country_info.environment 】    	{"Environment"     【 wlan.country_info.environment  】
844  :【 wlan.country_info.padding 】    	{"Padding"     【 wlan.country_info.padding  】
845  :【 wlan.country_info.fnm 】    	{"Country Info"     【 wlan.country_info.fnm  】
846  :【 wlan.country_info.fnm.fcn 】    	{"First Channel Number"     【 wlan.country_info.fnm.fcn  】
847  :【 wlan.country_info.fnm.nc 】    	{"Number of Channels"     【 wlan.country_info.fnm.nc  】
848  :【 wlan.country_info.fnm.mtpl 】    	{"Maximum Transmit Power Level"     【 wlan.country_info.fnm.mtpl  】
849  :【 wlan.country_info.rrc 】    	{"Country Info"     【 wlan.country_info.rrc  】
850  :【 wlan.country_info.rrc.oei 】    	{"Operating Extension Identifier"     【 wlan.country_info.rrc.oei  】
851  :【 wlan.country_info.rrc.oc 】    	{"Operating Class"     【 wlan.country_info.rrc.oc  】
852  :【 wlan.country_info.rrc.cc 】    	{"Coverage Class"     【 wlan.country_info.rrc.cc  】
853  :【 wlan.fh_hopping.parameter.prime_radix 】    	{"Prime Radix"     【 wlan.fh_hopping.parameter.prime_radix  】
854  :【 wlan.fh_hopping.parameter.nb_channels 】    	{"Number of Channels"     【 wlan.fh_hopping.parameter.nb_channels  】
855  :【 wlan.fh_hopping.table.flag 】    	{"Flag"     【 wlan.fh_hopping.table.flag  】
856  :【 wlan.fh_hopping.table.number_of_sets 】    	{"Number of Sets"     【 wlan.fh_hopping.table.number_of_sets  】
857  :【 wlan.fh_hopping.table.modulus 】    	{"Modulus"     【 wlan.fh_hopping.table.modulus  】
858  :【 wlan.fh_hopping.table.offset 】    	{"Offset"     【 wlan.fh_hopping.table.offset  】
859  :【 wlan.fh_hopping.table.random_table 】    	{"Random Table"     【 wlan.fh_hopping.table.random_table  】
860  :【 wlan.tag.request 】    	{"Requested Element ID"     【 wlan.tag.request  】
861  :【 wlan.tclas.user_priority 】    	{"User Priority"     【 wlan.tclas.user_priority  】
862  :【 wlan.tclas.class_type 】    	{"Classifier Type"     【 wlan.tclas.class_type  】
863  :【 wlan.tclas.class_mask 】    	{"Classifier Mask"     【 wlan.tclas.class_mask  】
864  :【 wlan.tclas.class_mask.src_addr 】    	{"Source Address"     【 wlan.tclas.class_mask.src_addr  】
865  :【 wlan.tclas.class_mask.dst_addr 】    	{"Destination Address"     【 wlan.tclas.class_mask.dst_addr  】
866  :【 wlan.tclas.class_mask.type 】    	{"Type"     【 wlan.tclas.class_mask.type  】
867  :【 wlan.tclas.class_mask.version 】    	{"Version"     【 wlan.tclas.class_mask.version  】
868  :【 wlan.tclas.class_mask.src_ip 】    	{"Source IP Address"     【 wlan.tclas.class_mask.src_ip  】
869  :【 wlan.tclas.class_mask.dst_ip 】    	{"Destination IP Address"     【 wlan.tclas.class_mask.dst_ip  】
870  :【 wlan.tclas.class_mask.src_port 】    	{"Source Port"     【 wlan.tclas.class_mask.src_port  】
871  :【 wlan.tclas.class_mask.dst_port 】    	{"Destination Port"     【 wlan.tclas.class_mask.dst_port  】
872  :【 wlan.tclas.class_mask.dscp 】    	{"DSCP"     【 wlan.tclas.class_mask.dscp  】
873  :【 wlan.tclas.class_mask.proto 】    	{"Protocol"     【 wlan.tclas.class_mask.proto  】
874  :【 wlan.tclas.class_mask.flow_label 】    	{"Flow Label"     【 wlan.tclas.class_mask.flow_label  】
875  :【 wlan.tclas.class_mask.tci 】    	{"802.1Q CLAN TCI"     【 wlan.tclas.class_mask.tci  】
876  :【 wlan.tclas.src_mac_addr 】    	{"Source address"     【 wlan.tclas.src_mac_addr  】
877  :【 wlan.tclas.dat_mac_addr 】    	{"Destination address"     【 wlan.tclas.dat_mac_addr  】
878  :【 wlan.tclas.ether_type 】    	{"Ethernet Type"     【 wlan.tclas.ether_type  】
879  :【 wlan.tclas.version 】    	{"IP Version"     【 wlan.tclas.version  】
880  :【 wlan.tclas.ipv4_src 】    	{"IPv4 Src Addr"     【 wlan.tclas.ipv4_src  】
881  :【 wlan.tclas.ipv4_dst 】    	{"IPv4 Dst Addr"     【 wlan.tclas.ipv4_dst  】
882  :【 wlan.tclas.src_port 】    	{"Source Port"     【 wlan.tclas.src_port  】
883  :【 wlan.tclas.dst_port 】    	{"Destination Port"     【 wlan.tclas.dst_port  】
884  :【 wlan.tclas.dscp 】    	{"IPv4 DSCP"     【 wlan.tclas.dscp  】
885  :【 wlan.tclas.protocol 】    	{"Protocol"     【 wlan.tclas.protocol  】
886  :【 wlan.tclas.ipv6_src 】    	{"IPv6 Src Addr"     【 wlan.tclas.ipv6_src  】
887  :【 wlan.tclas.ipv6_dst 】    	{"IPv6 Dst Addr"     【 wlan.tclas.ipv6_dst  】
888  :【 wlan.tclas.flow 】    	{"Flow Label"     【 wlan.tclas.flow  】
889  :【 wlan.tclas.tag_type 】    	{"802.1Q Tag Type"     【 wlan.tclas.tag_type  】
890  :【 wlan.tag.challenge_text 】    	{"Challenge Text"     【 wlan.tag.challenge_text  】
891  :【 wlan.rsn.version 】    	{"RSN Version"     【 wlan.rsn.version  】
892  :【 wlan.rsn.gcs 】    	{"Group Cipher Suite"     【 wlan.rsn.gcs  】
893  :【 wlan.rsn.gcs.oui 】    	{"Group Cipher Suite OUI"     【 wlan.rsn.gcs.oui  】
894  :【 wlan.rsn.gcs.type 】    	{"Group Cipher Suite type"     【 wlan.rsn.gcs.type  】
895  :【 wlan.rsn.gcs.type 】    	{"Group Cipher Suite type"     【 wlan.rsn.gcs.type  】
896  :【 wlan.rsn.pcs.count 】    	{"Pairwise Cipher Suite Count"     【 wlan.rsn.pcs.count  】
897  :【 wlan.rsn.pcs.list 】    	{"Pairwise Cipher Suite List"     【 wlan.rsn.pcs.list  】
898  :【 wlan.rsn.pcs 】    	{"Pairwise Cipher Suite"     【 wlan.rsn.pcs  】
899  :【 wlan.rsn.pcs.oui 】    	{"Pairwise Cipher Suite OUI"     【 wlan.rsn.pcs.oui  】
900  :【 wlan.rsn.pcs.type 】    	{"Pairwise Cipher Suite type"     【 wlan.rsn.pcs.type  】
901  :【 wlan.rsn.pcs.type 】    	{"Pairwise Cipher Suite type"     【 wlan.rsn.pcs.type  】
902  :【 wlan.rsn.akms.count 】    	{"Auth Key Management (AKM) Suite Count"     【 wlan.rsn.akms.count  】
903  :【 wlan.rsn.akms.list 】    	{"Auth Key Management (AKM) List"     【 wlan.rsn.akms.list  】
904  :【 wlan.rsn.akms 】    	{"Auth Key Management (AKM) Suite"     【 wlan.rsn.akms  】
905  :【 wlan.rsn.akms.oui 】    	{"Auth Key Management (AKM) OUI"     【 wlan.rsn.akms.oui  】
906  :【 wlan.rsn.akms.type 】    	{"Auth Key Management (AKM) type"     【 wlan.rsn.akms.type  】
907  :【 wlan.rsn.akms.type 】    	{"Auth Key Management (AKM) type"     【 wlan.rsn.akms.type  】
908  :【 wlan.rsn.capabilities 】    	{"RSN Capabilities"     【 wlan.rsn.capabilities  】
909  :【 wlan.rsn.capabilities.preauth 】    	{"RSN Pre-Auth capabilities"     【 wlan.rsn.capabilities.preauth  】
910  :【 wlan.rsn.capabilities.no_pairwise 】    	{"RSN No Pairwise capabilities"     【 wlan.rsn.capabilities.no_pairwise  】
911  :【 wlan.rsn.capabilities.ptksa_replay_counter 】    	{"RSN PTKSA Replay Counter capabilities"     【 wlan.rsn.capabilities.ptksa_replay_counter  】
912  :【 wlan.rsn.capabilities.gtksa_replay_counter 】    	{"RSN GTKSA Replay Counter capabilities"     【 wlan.rsn.capabilities.gtksa_replay_counter  】
913  :【 wlan.rsn.capabilities.mfpr 】    	{"Management Frame Protection Required"     【 wlan.rsn.capabilities.mfpr  】
914  :【 wlan.rsn.capabilities.mfpc 】    	{"Management Frame Protection Capable"     【 wlan.rsn.capabilities.mfpc  】
915  :【 wlan.rsn.capabilities.jmr 】    	{"Joint Multi-band RSNA"     【 wlan.rsn.capabilities.jmr  】
916  :【 wlan.rsn.capabilities.peerkey 】    	{"PeerKey Enabled"     【 wlan.rsn.capabilities.peerkey  】
917  :【 wlan.rsn.pmkid.count 】    	{"PMKID Count"     【 wlan.rsn.pmkid.count  】
918  :【 wlan.rsn.pmkid.list 】    	{"PMKID List"     【 wlan.rsn.pmkid.list  】
919  :【 wlan.pmkid.akms 】    	{"PMKID"     【 wlan.pmkid.akms  】
920  :【 wlan.rsn.gmcs 】    	{"Group Management Cipher Suite"     【 wlan.rsn.gmcs  】
921  :【 wlan.rsn.gmcs.oui 】    	{"Group Management Cipher Suite OUI"     【 wlan.rsn.gmcs.oui  】
922  :【 wlan.rsn.gmcs.type 】    	{"Group Management Cipher Suite type"     【 wlan.rsn.gmcs.type  】
923  :【 wlan.rsn.gmcs.type 】    	{"Group Management Cipher Suite type"     【 wlan.rsn.gmcs.type  】
924  :【 wlan.vs.pren.type 】    	{"802.11n (Pre) Type"     【 wlan.vs.pren.type  】
925  :【 wlan.vs.pren.unknown_data 】    	{"802.11n (Pre) Unknown Data"     【 wlan.vs.pren.unknown_data  】
926  :【 wlan.ht.capabilities 】    	{"HT Capabilities Info"     【 wlan.ht.capabilities  】
927  :【 wlan.vs.ht.capabilities 】    	{"HT Capabilities Info (VS)"     【 wlan.vs.ht.capabilities  】
928  :【 wlan.ht.capabilities.ldpccoding 】    	{"HT LDPC coding capability"     【 wlan.ht.capabilities.ldpccoding  】
929  :【 wlan.ht.capabilities.width 】    	{"HT Support channel width"     【 wlan.ht.capabilities.width  】
930  :【 wlan.ht.capabilities.sm 】    	{"HT SM Power Save"     【 wlan.ht.capabilities.sm  】
931  :【 wlan.ht.capabilities.green 】    	{"HT Green Field"     【 wlan.ht.capabilities.green  】
932  :【 wlan.ht.capabilities.short20 】    	{"HT Short GI for 20MHz"     【 wlan.ht.capabilities.short20  】
933  :【 wlan.ht.capabilities.short40 】    	{"HT Short GI for 40MHz"     【 wlan.ht.capabilities.short40  】
934  :【 wlan.ht.capabilities.txstbc 】    	{"HT Tx STBC"     【 wlan.ht.capabilities.txstbc  】
935  :【 wlan.ht.capabilities.rxstbc 】    	{"HT Rx STBC"     【 wlan.ht.capabilities.rxstbc  】
936  :【 wlan.ht.capabilities.delayedblockack 】    	{"HT Delayed Block ACK"     【 wlan.ht.capabilities.delayedblockack  】
937  :【 wlan.ht.capabilities.amsdu 】    	{"HT Max A-MSDU length"     【 wlan.ht.capabilities.amsdu  】
938  :【 wlan.ht.capabilities.dsscck 】    	{"HT DSSS/CCK mode in 40MHz"     【 wlan.ht.capabilities.dsscck  】
939  :【 wlan.ht.capabilities.psmp 】    	{"HT PSMP Support"     【 wlan.ht.capabilities.psmp  】
940  :【 wlan.ht.capabilities.40mhzintolerant 】    	{"HT Forty MHz Intolerant"     【 wlan.ht.capabilities.40mhzintolerant  】
941  :【 wlan.ht.capabilities.lsig 】    	{"HT L-SIG TXOP Protection support"     【 wlan.ht.capabilities.lsig  】
942  :【 wlan.ext_bss.mu_mimo_capable_sta_count 】    	{"MU-MIMO Capable STA Count"     【 wlan.ext_bss.mu_mimo_capable_sta_count  】
943  :【 wlan.ext_bss.ss_underutilization 】    	{"Spatial Stream Underutilization"     【 wlan.ext_bss.ss_underutilization  】
944  :【 wlan.ext_bss.observable_sec_20mhz_utilization 】    	{"Observable Secondary 20MHz Utilization"     【 wlan.ext_bss.observable_sec_20mhz_utilization  】
945  :【 wlan.ext_bss.observable_sec_40mhz_utilization 】    	{"Observable Secondary 40MHz Utilization"     【 wlan.ext_bss.observable_sec_40mhz_utilization  】
946  :【 wlan.ext_bss.observable_sec_80mhz_utilization 】    	{"Observable Secondary 80MHz Utilization"     【 wlan.ext_bss.observable_sec_80mhz_utilization  】
947  :【 wlan.wide_bw.new_channel_width 】    	{"New Channel Width"     【 wlan.wide_bw.new_channel_width  】
948  :【 wlan.wide_bw.new_channel_center_freq_segment0 】    	{"New Channel Center Frequency Segment 0"     【 wlan.wide_bw.new_channel_center_freq_segment0  】
949  :【 wlan.wide_bw.new_channel_center_freq_segment1 】    	{"New Channel Center Frequency Segment 1"     【 wlan.wide_bw.new_channel_center_freq_segment1  】
950  :【 wlan.operat_notification_mode 】    	{"Operating Mode Notification"     【 wlan.operat_notification_mode  】
951  :【 wlan.operat_mode_field.channelwidth 】    	{"Channel Width"     【 wlan.operat_mode_field.channelwidth  】
952  :【 wlan.operat_mode_field.reserved 】    	{"Reserved"     【 wlan.operat_mode_field.reserved  】
953  :【 wlan.operat_mode_field.rxnss 】    	{"Rx NSS"     【 wlan.operat_mode_field.rxnss  】
954  :【 wlan.operat_mode_field.rxnsstype 】    	{"Rx NSS Type"     【 wlan.operat_mode_field.rxnsstype  】
955  :【 wlan.ht.ampduparam 】    	{"A-MPDU Parameters"     【 wlan.ht.ampduparam  】
956  :【 wlan.vs.ht.ampduparam 】    	{"A-MPDU Parameters (VS)"     【 wlan.vs.ht.ampduparam  】
957  :【 wlan.ht.ampduparam.maxlength 】    	{"Maximum Rx A-MPDU Length"     【 wlan.ht.ampduparam.maxlength  】
958  :【 wlan.ht.ampduparam.mpdudensity 】    	{"MPDU Density"     【 wlan.ht.ampduparam.mpdudensity  】
959  :【 wlan.ht.ampduparam.reserved 】    	{"Reserved"     【 wlan.ht.ampduparam.reserved  】
960  :【 wlan.ht.mcsset 】    	{"Rx Supported Modulation and Coding Scheme Set"     【 wlan.ht.mcsset  】
961  :【 wlan.vs.ht.mcsset 】    	{"Rx Supported Modulation and Coding Scheme Set (VS)"     【 wlan.vs.ht.mcsset  】
962  :【 wlan.ht.mcsset.rxbitmask 】    	{"Rx Modulation and Coding Scheme (One bit per modulation)"     【 wlan.ht.mcsset.rxbitmask  】
963  :【 wlan.ht.mcsset.rxbitmask.0to7 】    	{"Rx Bitmask Bits 0-7"     【 wlan.ht.mcsset.rxbitmask.0to7  】
964  :【 wlan.ht.mcsset.rxbitmask.8to15 】    	{"Rx Bitmask Bits 8-15"     【 wlan.ht.mcsset.rxbitmask.8to15  】
965  :【 wlan.ht.mcsset.rxbitmask.16to23 】    	{"Rx Bitmask Bits 16-23"     【 wlan.ht.mcsset.rxbitmask.16to23  】
966  :【 wlan.ht.mcsset.rxbitmask.24to31 】    	{"Rx Bitmask Bits 24-31"     【 wlan.ht.mcsset.rxbitmask.24to31  】
967  :【 wlan.ht.mcsset.rxbitmask.32 】    	{"Rx Bitmask Bit 32"     【 wlan.ht.mcsset.rxbitmask.32  】
968  :【 wlan.ht.mcsset.rxbitmask.33to38 】    	{"Rx Bitmask Bits 33-38"     【 wlan.ht.mcsset.rxbitmask.33to38  】
969  :【 wlan.ht.mcsset.rxbitmask.39to52 】    	{"Rx Bitmask Bits 39-52"     【 wlan.ht.mcsset.rxbitmask.39to52  】
970  :【 wlan.ht.mcsset.rxbitmask.53to76 】    	{"Rx Bitmask Bits 53-76"     【 wlan.ht.mcsset.rxbitmask.53to76  】
971  :【 wlan.ht.mcsset.highestdatarate 】    	{"Highest Supported Data Rate"     【 wlan.ht.mcsset.highestdatarate  】
972  :【 wlan.ht.mcsset.txsetdefined 】    	{"Tx Supported MCS Set"     【 wlan.ht.mcsset.txsetdefined  】
973  :【 wlan.ht.mcsset.txrxmcsnotequal 】    	{"Tx and Rx MCS Set"     【 wlan.ht.mcsset.txrxmcsnotequal  】
974  :【 wlan.ht.mcsset.txmaxss 】    	{"Maximum Number of Tx Spatial Streams Supported"     【 wlan.ht.mcsset.txmaxss  】
975  :【 wlan.ht.mcsset.txunequalmod 】    	{"Unequal Modulation"     【 wlan.ht.mcsset.txunequalmod  】
976  :【 wlan.htex.capabilities 】    	{"HT Extended Capabilities"     【 wlan.htex.capabilities  】
977  :【 wlan.vs.htex.capabilities 】    	{"HT Extended Capabilities (VS)"     【 wlan.vs.htex.capabilities  】
978  :【 wlan.htex.capabilities.pco 】    	{"Transmitter supports PCO"     【 wlan.htex.capabilities.pco  】
979  :【 wlan.htex.capabilities.transtime 】    	{"Time needed to transition between 20MHz and 40MHz"     【 wlan.htex.capabilities.transtime  】
980  :【 wlan.htex.capabilities.mcs 】    	{"MCS Feedback capability"     【 wlan.htex.capabilities.mcs  】
981  :【 wlan.htex.capabilities.htc 】    	{"High Throughput"     【 wlan.htex.capabilities.htc  】
982  :【 wlan.htex.capabilities.rdresponder 】    	{"Reverse Direction Responder"     【 wlan.htex.capabilities.rdresponder  】
983  :【 wlan.txbf 】    	{"Transmit Beam Forming (TxBF) Capabilities"     【 wlan.txbf  】
984  :【 wlan.vs.txbf 】    	{"Transmit Beam Forming (TxBF) Capabilities (VS)"     【 wlan.vs.txbf  】
985  :【 wlan.txbf.txbf 】    	{"Transmit Beamforming"     【 wlan.txbf.txbf  】
986  :【 wlan.txbf.rxss 】    	{"Receive Staggered Sounding"     【 wlan.txbf.rxss  】
987  :【 wlan.txbf.txss 】    	{"Transmit Staggered Sounding"     【 wlan.txbf.txss  】
988  :【 wlan.txbf.rxndp 】    	{"Receive Null Data packet (NDP)"     【 wlan.txbf.rxndp  】
989  :【 wlan.txbf.txndp 】    	{"Transmit Null Data packet (NDP)"     【 wlan.txbf.txndp  】
990  :【 wlan.txbf.impltxbf 】    	{"Implicit TxBF capable"     【 wlan.txbf.impltxbf  】
991  :【 wlan.txbf.calibration 】    	{"Calibration"     【 wlan.txbf.calibration  】
992  :【 wlan.txbf.csi 】    	{"STA can apply TxBF using CSI explicit feedback"     【 wlan.txbf.csi  】
993  :【 wlan.txbf.fm.uncompressed 】   	{"STA can apply TxBF using uncompressed beamforming feedback matrix"     【 wlan.txbf.fm.uncompressed.tbf  】
994  :【 wlan.txbf.fm.compressed.tbf 】   	{"STA can apply TxBF using compressed beamforming feedback matrix"     【 wlan.txbf.fm.compressed.tbf  】
995  :【 wlan.txbf.rcsi 】    	{"Receiver can return explicit CSI feedback"     【 wlan.txbf.rcsi  】
996  :【 wlan.txbf.fm.uncompre   	{"Receiver can return explicit uncompressed Beamforming Feedback Matrix"     【 wlan.txbf.fm.uncompressed.rbf  】
997  :【 wlan.txbf.fm.compressed.bf    	{"STA can compress and use compressed Beamforming Feedback Matrix"     【 wlan.txbf.fm.compressed.bf  】
998  :【 wlan.txbf.mingroup 】    	{"Minimal grouping used for explicit feedback reports"     【 wlan.txbf.mingroup  】
999  :【 wlan.vht.capabilities 】    	{"VHT Capabilities Info"     【 wlan.vht.capabilities  】
1000  :【 wlan.vht.capabilities.maxmpdulength 】    	{"Maximum MPDU Length"     【 wlan.vht.capabilities.maxmpdulength  】
1001  :【 wlan.vht.capabilities.supportedchanwidthset 】    	{"Supported Channel Width Set"     【 wlan.vht.capabilities.supportedchanwidthset  】
1002  :【 wlan.vht.capabilities.rxldpc 】    	{"Rx LDPC"     【 wlan.vht.capabilities.rxldpc  】
1003  :【 wlan.vht.capabilities.short80 】    	{"Short GI for 80MHz/TVHT_MODE_4C"     【 wlan.vht.capabilities.short80  】
1004  :【 wlan.vht.capabilities.short160 】    	{"Short GI for 160MHz and 80+80MHz"     【 wlan.vht.capabilities.short160  】
1005  :【 wlan.vht.capabilities.txstbc 】    	{"Tx STBC"     【 wlan.vht.capabilities.txstbc  】
1006  :【 wlan.vht.capabilities.rxstbc 】    	{"Rx STBC"     【 wlan.vht.capabilities.rxstbc  】
1007  :【 wlan.vht.capabilities.subeamformer 】    	{"SU Beamformer Capable"     【 wlan.vht.capabilities.subeamformer  】
1008  :【 wlan.vht.capabilities.subeamformee 】    	{"SU Beamformee Capable"     【 wlan.vht.capabilities.subeamformee  】
1009  :【 wlan.vht.capabilities.beamformee_sts_cap 】    	{"Beamformee STS Capability"     【 wlan.vht.capabilities.beamformee_sts_cap  】
1010  :【 wlan.vht.capabilities.soundingdimensions 】    	{"Number of Sounding Dimensions"     【 wlan.vht.capabilities.soundingdimensions  】
1011  :【 wlan.vht.capabilities.mubeamformer 】    	{"MU Beamformer Capable"     【 wlan.vht.capabilities.mubeamformer  】
1012  :【 wlan.vht.capabilities.mubeamformee 】    	{"MU Beamformee Capable"     【 wlan.vht.capabilities.mubeamformee  】
1013  :【 wlan.vht.capabilities.vhttxopps 】    	{"TXOP PS"     【 wlan.vht.capabilities.vhttxopps  】
1014  :【 wlan.vht.capabilities.vhthtc 】    	{"+HTC-VHT Capable"     【 wlan.vht.capabilities.vhthtc  】
1015  :【 wlan.vht.capabilities.maxampdu 】    	{"Max A-MPDU Length Exponent"     【 wlan.vht.capabilities.maxampdu  】
1016  :【 wlan.vht.capabilities.linkadapt 】    	{"VHT Link Adaptation"     【 wlan.vht.capabilities.linkadapt  】
1017  :【 wlan.vht.capabilities.rxpatconsist 】    	{"Rx Antenna Pattern Consistency"     【 wlan.vht.capabilities.rxpatconsist  】
1018  :【 wlan.vht.capabilities.txpatconsist 】    	{"Tx Antenna Pattern Consistency"     【 wlan.vht.capabilities.txpatconsist  】
1019  :【 wlan.vht.capabilities.ext_nss_bw_support 】    	{"Extended NSS BW Support"     【 wlan.vht.capabilities.ext_nss_bw_support  】
1020  :【 wlan.vht.mcsset 】    	{"VHT Supported MCS Set"     【 wlan.vht.mcsset  】
1021  :【 wlan.vht.mcsset.rxmcsmap 】    	{"Rx MCS Map"     【 wlan.vht.mcsset.rxmcsmap  】
1022  :【 wlan.vht.mcsset.rxmcsmap.ss1 】    	{"Rx 1 SS"     【 wlan.vht.mcsset.rxmcsmap.ss1  】
1023  :【 wlan.vht.mcsset.rxmcsmap.ss2 】    	{"Rx 2 SS"     【 wlan.vht.mcsset.rxmcsmap.ss2  】
1024  :【 wlan.vht.mcsset.rxmcsmap.ss3 】    	{"Rx 3 SS"     【 wlan.vht.mcsset.rxmcsmap.ss3  】
1025  :【 wlan.vht.mcsset.rxmcsmap.ss4 】    	{"Rx 4 SS"     【 wlan.vht.mcsset.rxmcsmap.ss4  】
1026  :【 wlan.vht.mcsset.rxmcsmap.ss5 】    	{"Rx 5 SS"     【 wlan.vht.mcsset.rxmcsmap.ss5  】
1027  :【 wlan.vht.mcsset.rxmcsmap.ss6 】    	{"Rx 6 SS"     【 wlan.vht.mcsset.rxmcsmap.ss6  】
1028  :【 wlan.vht.mcsset.rxmcsmap.ss7 】    	{"Rx 7 SS"     【 wlan.vht.mcsset.rxmcsmap.ss7  】
1029  :【 wlan.vht.mcsset.rxmcsmap.ss8 】    	{"Rx 8 SS"     【 wlan.vht.mcsset.rxmcsmap.ss8  】
1030  :【 wlan.vht.mcsset.max_nsts_total 】    	{"MaX NSTS Total"     【 wlan.vht.mcsset.max_nsts_total  】
1031  :【 wlan.vht.mcsset.rxhighestl   	{"Rx Highest Long GI Data Rate (in Mb/s, 0 = subfield not in use)"     【 wlan.vht.mcsset.rxhighestlonggirate  】
1032  :【 wlan.vht.mcsset.txmcsmap 】    	{"Tx MCS Map"     【 wlan.vht.mcsset.txmcsmap  】
1033  :【 wlan.vht.mcsset.txmcsmap.ss1 】    	{"Tx 1 SS"     【 wlan.vht.mcsset.txmcsmap.ss1  】
1034  :【 wlan.vht.mcsset.txmcsmap.ss2 】    	{"Tx 2 SS"     【 wlan.vht.mcsset.txmcsmap.ss2  】
1035  :【 wlan.vht.mcsset.txmcsmap.ss3 】    	{"Tx 3 SS"     【 wlan.vht.mcsset.txmcsmap.ss3  】
1036  :【 wlan.vht.mcsset.txmcsmap.ss4 】    	{"Tx 4 SS"     【 wlan.vht.mcsset.txmcsmap.ss4  】
1037  :【 wlan.vht.mcsset.txmcsmap.ss5 】    	{"Tx 5 SS"     【 wlan.vht.mcsset.txmcsmap.ss5  】
1038  :【 wlan.vht.mcsset.txmcsmap.ss6 】    	{"Tx 6 SS"     【 wlan.vht.mcsset.txmcsmap.ss6  】
1039  :【 wlan.vht.mcsset.txmcsmap.ss7 】    	{"Tx 7 SS"     【 wlan.vht.mcsset.txmcsmap.ss7  】
1040  :【 wlan.vht.mcsset.txmcsmap.ss8 】    	{"Tx 8 SS"     【 wlan.vht.mcsset.txmcsmap.ss8  】
1041  :【 wlan.vht.mcsset.txhighest   	{"Tx Highest Long GI Data Rate  (in Mb/s, 0 = subfield not in use)"     【 wlan.vht.mcsset.txhighestlonggirate  】
1042  :【 wlan.vht.ncsset.ext_nss_bw_cap 】    	{"Extended NSS BW Capable"     【 wlan.vht.ncsset.ext_nss_bw_cap  】
1043  :【 wlan.vht.ncsset.reserved 】    	{"Reserved"     【 wlan.vht.ncsset.reserved  】
1044  :【 wlan.vht.op 】    	{"VHT Operation Info"     【 wlan.vht.op  】
1045  :【 wlan.vht.op.channelwidth 】    	{"Channel Width"     【 wlan.vht.op.channelwidth  】
1046  :【 wlan.vht.op.channelcenter0 】    	{"Channel Center Segment 0"     【 wlan.vht.op.channelcenter0  】
1047  :【 wlan.vht.op.channelcenter1 】    	{"Channel Center Segment 1"     【 wlan.vht.op.channelcenter1  】
1048  :【 wlan.vht.op.basicmcsmap 】    	{"Basic MCS Map"     【 wlan.vht.op.basicmcsmap  】
1049  :【 wlan.vht.op.basicmcsmap.ss1 】    	{"Basic 1 SS"     【 wlan.vht.op.basicmcsmap.ss1  】
1050  :【 wlan.vht.op.basicmcsmap.ss2 】    	{"Basic 2 SS"     【 wlan.vht.op.basicmcsmap.ss2  】
1051  :【 wlan.vht.op.basicmcsmap.ss3 】    	{"Basic 3 SS"     【 wlan.vht.op.basicmcsmap.ss3  】
1052  :【 wlan.vht.op.basicmcsmap.ss4 】    	{"Basic 4 SS"     【 wlan.vht.op.basicmcsmap.ss4  】
1053  :【 wlan.vht.op.basicmcsmap.ss5 】    	{"Basic 5 SS"     【 wlan.vht.op.basicmcsmap.ss5  】
1054  :【 wlan.vht.op.basicmcsmap.ss6 】    	{"Basic 6 SS"     【 wlan.vht.op.basicmcsmap.ss6  】
1055  :【 wlan.vht.op.basicmcsmap.ss7 】    	{"Basic 7 SS"     【 wlan.vht.op.basicmcsmap.ss7  】
1056  :【 wlan.vht.op.basicmcsmap.ss8 】    	{"Basic 8 SS"     【 wlan.vht.op.basicmcsmap.ss8  】
1057  :【 wlan.vht.tpe.pwr_info 】    	{"Tx Pwr Info"     【 wlan.vht.tpe.pwr_info  】
1058  :【 wlan.vht.tpe.pwr_info.count 】    	{"Max Tx Pwr Count"     【 wlan.vht.tpe.pwr_info.count  】
1059  :【 wlan.vht.tpe.pwr_info.unit 】    	{"Max Tx Pwr Unit Interpretation"     【 wlan.vht.tpe.pwr_info.unit  】
1060  :【 wlan.vht.tpe.pwr_info.reserved 】    	{"Reserved"     【 wlan.vht.tpe.pwr_info.reserved  】
1061  :【 wlan.vht.tpe.pwr_constr_20 】    	{"Local Max Tx Pwr Constraint 20MHz"     【 wlan.vht.tpe.pwr_constr_20  】
1062  :【 wlan.vht.tpe.pwr_constr_40 】    	{"Local Max Tx Pwr Constraint 40MHz"     【 wlan.vht.tpe.pwr_constr_40  】
1063  :【 wlan.vht.tpe.pwr_constr_80 】    	{"Local Max Tx Pwr Constraint 80MHz"     【 wlan.vht.tpe.pwr_constr_80  】
1064  :【 wlan.vht.tpe.pwr_constr_160 】    	{"Local Max Tx Pwr Constraint 160MHz/80+80 MHz"     【 wlan.vht.tpe.pwr_constr_160  】
1065  :【 wlan.txbf.csinumant 】    	{"Max antennae STA can support when CSI feedback required"     【 wlan.txbf.csinumant  】
1066  :【 wlan.txbf.fm.uncompressed.maxant 】    	{"Max antennae STA can support when uncompressed Beamforming feedback required"     【 wlan.txbf.fm.uncompressed.maxant  】
1067  :【 wlan.txbf.fm.compressed.maxant 】   	{"Max antennae STA can support when compressed Beamforming feedback required"     【 wlan.txbf.fm.compressed.maxant  】
1068  :【 wlan.txbf.csi.maxrows 】    	{"Maximum number of rows of CSI explicit feedback"     【 wlan.txbf.csi.maxrows  】
1069  :【 wlan.txbf.channelest 】    	{"Maximum number of space time streams for which channel dimensions can be simultaneously estimated"     【 wlan.txbf.channelest  】
1070  :【 wlan.txbf.reserved 】    	{"Reserved"     【 wlan.txbf.reserved  】
1071  :【 wlan.hta.control_channel 】    	{"HT Control Channel"     【 wlan.hta.control_channel  】
1072  :【 wlan.hta.capabilities 】    	{"HT Additional Capabilities"     【 wlan.hta.capabilities  】
1073  :【 wlan.hta.capabilities 】    	{"HT Additional Capabilities"     【 wlan.hta.capabilities  】
1074  :【 wlan.hta.capabilities.ext_chan_offset 】    	{"Extension Channel Offset"     【 wlan.hta.capabilities.ext_chan_offset  】
1075  :【 wlan.hta.capabilities.rec_tx_width 】    	{"Recommended Tx Channel Width"     【 wlan.hta.capabilities.rec_tx_width  】
1076  :【 wlan.hta.capabilities.rifs_mode 】    	{"Reduced Interframe Spacing (RIFS) Mode"     【 wlan.hta.capabilities.rifs_mode  】
1077  :【 wlan.hta.capabilities.controlled_access 】    	{"Controlled Access Only"     【 wlan.hta.capabilities.controlled_access  】
1078  :【 wlan.hta.capabilities.service_interval 】    	{"Service Interval Granularity"     【 wlan.hta.capabilities.service_interval  】
1079  :【 wlan.hta.capabilities.operating_mode 】    	{"Operating Mode"     【 wlan.hta.capabilities.operating_mode  】
1080  :【 wlan.hta.capabilities.non_gf_devices 】    	{"Non Greenfield (GF) devices Present"     【 wlan.hta.capabilities.non_gf_devices  】
1081  :【 wlan.hta.capabilities.basic_stbc_mcs 】    	{"Basic STB Modulation and Coding Scheme (MCS)"     【 wlan.hta.capabilities.basic_stbc_mcs  】
1082  :【 wlan.hta.capabilities.dual_stbc_protection 】    	{"Dual Clear To Send (CTS) Protection"     【 wlan.hta.capabilities.dual_stbc_protection  】
1083  :【 wlan.hta.capabilities.secondary_beacon 】    	{"Secondary Beacon"     【 wlan.hta.capabilities.secondary_beacon  】
1084  :【 wlan.hta.capabilities.lsig_txop_protection 】    	{"L-SIG TXOP Protection Support"     【 wlan.hta.capabilities.lsig_txop_protection  】
1085  :【 wlan.hta.capabilities.pco_active 】    	{"Phased Coexistence Operation (PCO) Active"     【 wlan.hta.capabilities.pco_active  】
1086  :【 wlan.hta.capabilities.pco_phase 】    	{"Phased Coexistence Operation (PCO) Phase"     【 wlan.hta.capabilities.pco_phase  】
1087  :【 wlan.asel 】    	{"Antenna Selection (ASEL) Capabilities"     【 wlan.asel  】
1088  :【 wlan.vs.asel 】    	{"Antenna Selection (ASEL) Capabilities (VS)"     【 wlan.vs.asel  】
1089  :【 wlan.asel.capable 】    	{"Antenna Selection Capable"     【 wlan.asel.capable  】
1090  :【 wlan.asel.txcsi 】    	{"Explicit CSI Feedback Based Tx ASEL"     【 wlan.asel.txcsi  】
1091  :【 wlan.asel.txif 】    	{"Antenna Indices Feedback Based Tx ASEL"     【 wlan.asel.txif  】
1092  :【 wlan.asel.csi 】    	{"Explicit CSI Feedback"     【 wlan.asel.csi  】
1093  :【 wlan.asel.if 】    	{"Antenna Indices Feedback"     【 wlan.asel.if  】
1094  :【 wlan.asel.rx 】    	{"Rx ASEL"     【 wlan.asel.rx  】
1095  :【 wlan.asel.sppdu 】    	{"Tx Sounding PPDUs"     【 wlan.asel.sppdu  】
1096  :【 wlan.asel.reserved 】    	{"Reserved"     【 wlan.asel.reserved  】
1097  :【 wlan.ht.info.delim1 】    	{"HT Information Subset (1 of 3)"     【 wlan.ht.info.delim1  】
1098  :【 wlan.ht.info.primarychannel 】    	{"Primary Channel"     【 wlan.ht.info.primarychannel  】
1099  :【 wlan.ht.info.secchanoffset 】    	{"Secondary channel offset"     【 wlan.ht.info.secchanoffset  】
1100  :【 wlan.ht.info.chanwidth 】    	{"Supported channel width"     【 wlan.ht.info.chanwidth  】
1101  :【 wlan.ht.info.rifs 】    	{"Reduced Interframe Spacing (RIFS)"     【 wlan.ht.info.rifs  】
1102  :【 wlan.ht.info.reserved_b4_b7 】    	{"Reserved"     【 wlan.ht.info.reserved_b4_b7  】
1103  :【 wlan.ht.info.delim2 】    	{"HT Information Subset (2 of 3)"     【 wlan.ht.info.delim2  】
1104  :【 wlan.ht.info.ht_protection 】    	{"HT Protection"     【 wlan.ht.info.ht_protection  】
1105  :【 wlan.ht.info.greenfield 】    	{"Non-greenfield STAs present"     【 wlan.ht.info.greenfield  】
1106  :【 wlan.ht.info.reserved_b11 】    	{"Reserved"     【 wlan.ht.info.reserved_b11  】
1107  :【 wlan.ht.info.obssnonht 】    	{"OBSS non-HT STAs present"     【 wlan.ht.info.obssnonht  】
1108  :【 wlan.ht.info.chan_center_freq_seg_2 】    	{"Channel Center Frequency Segment 2"     【 wlan.ht.info.chan_center_freq_seg_2  】
1109  :【 wlan.ht.info.reserved_b21_b23 】    	{"Reserved"     【 wlan.ht.info.reserved_b21_b23  】
1110  :【 wlan.ht.info.delim3 】    	{"HT Information Subset (3 of 3)"     【 wlan.ht.info.delim3  】
1111  :【 wlan.ht.info.reserved_b24_b29 】    	{"Reserved"     【 wlan.ht.info.reserved_b24_b29  】
1112  :【 wlan.ht.info.dualbeacon 】    	{"Dual beacon"     【 wlan.ht.info.dualbeacon  】
1113  :【 wlan.ht.info.dualcts 】    	{"Dual Clear To Send (CTS) protection"     【 wlan.ht.info.dualcts  】
1114  :【 wlan.ht.info.secondarybeacon 】    	{"Beacon ID"     【 wlan.ht.info.secondarybeacon  】
1115  :【 wlan.ht.info.lsigprotsupport 】    	{"L-SIG TXOP Protection Full Support"     【 wlan.ht.info.lsigprotsupport  】
1116  :【 wlan.ht.info.pco.active 】    	{"Phased Coexistence Operation (PCO)"     【 wlan.ht.info.pco.active  】
1117  :【 wlan.ht.info.pco.phase 】    	{"Phased Coexistence Operation (PCO) Phase"     【 wlan.ht.info.pco.phase  】
1118  :【 wlan.ht.info.reserved_b36_b39 】    	{"Reserved"     【 wlan.ht.info.reserved_b36_b39  】
1119  :【 wlan.ap_channel_report.operating_class 】    	{"Operating Class"     【 wlan.ap_channel_report.operating_class  】
1120  :【 wlan.ap_channel_report.channel_list 】    	{"Channel List"     【 wlan.ap_channel_report.channel_list  】
1121  :【 wlan.secchanoffset 】    	{"Secondary Channel Offset"     【 wlan.secchanoffset  】
1122  :【 wlan.bss_ap_avg_access_delay 】    	{"AP Average Access Delay"     【 wlan.bss_ap_avg_access_delay  】
1123  :【 wlan.antenna.id 】    	{"Antenna ID"     【 wlan.antenna.id  】
1124  :【 wlan.rsni 】    	{"RSNI"     【 wlan.rsni  】
1125  :【 wlan.bss_avb_adm_cap.bitmask 】    	{"Available Admission Capacity Bitmask"     【 wlan.bss_avb_adm_cap.bitmask  】
1126  :【 wlan.bss_avb_adm_cap.bitmask.up0 】    	{"UP0 (bit0)"     【 wlan.bss_avb_adm_cap.bitmask.up0  】
1127  :【 wlan.bss_avb_adm_cap.bitmask.up1 】    	{"UP1 (bit1)"     【 wlan.bss_avb_adm_cap.bitmask.up1  】
1128  :【 wlan.bss_avb_adm_cap.bitmask.up2 】    	{"UP2 (bit2)"     【 wlan.bss_avb_adm_cap.bitmask.up2  】
1129  :【 wlan.bss_avb_adm_cap.bitmask.up3 】    	{"UP3 (bit3)"     【 wlan.bss_avb_adm_cap.bitmask.up3  】
1130  :【 wlan.bss_avb_adm_cap.bitmask.up4 】    	{"UP4 (bit4)"     【 wlan.bss_avb_adm_cap.bitmask.up4  】
1131  :【 wlan.bss_avb_adm_cap.bitmask.up5 】    	{"UP5 (bit5)"     【 wlan.bss_avb_adm_cap.bitmask.up5  】
1132  :【 wlan.bss_avb_adm_cap.bitmask.up6 】    	{"UP0 (bit6)"     【 wlan.bss_avb_adm_cap.bitmask.up6  】
1133  :【 wlan.bss_avb_adm_cap.bitmask.up7 】    	{"UP7 (bit7)"     【 wlan.bss_avb_adm_cap.bitmask.up7  】
1134  :【 wlan.bss_avb_adm_cap.bitmask.ac0 】    	{"AC0 (bit8)"     【 wlan.bss_avb_adm_cap.bitmask.ac0  】
1135  :【 wlan.bss_avb_adm_cap.bitmask.AC1 】    	{"AC1 (bit9)"     【 wlan.bss_avb_adm_cap.bitmask.AC1  】
1136  :【 wlan.bss_avb_adm_cap.bitmask.ac2 】    	{"AC2 (bit10)"     【 wlan.bss_avb_adm_cap.bitmask.ac2  】
1137  :【 wlan.bss_avb_adm_cap.bitmask.ac3 】    	{"AC3 (bit11)"     【 wlan.bss_avb_adm_cap.bitmask.ac3  】
1138  :【 wlan.bss_avb_adm_cap.bitmask.rsv 】    	{"Reserved"     【 wlan.bss_avb_adm_cap.bitmask.rsv  】
1139  :【 wlan.bss_avb_adm_cap.up0 】    	{"UP0"     【 wlan.bss_avb_adm_cap.up0  】
1140  :【 wlan.bss_avb_adm_cap.up1 】    	{"UP1"     【 wlan.bss_avb_adm_cap.up1  】
1141  :【 wlan.bss_avb_adm_cap.up2 】    	{"UP2"     【 wlan.bss_avb_adm_cap.up2  】
1142  :【 wlan.bss_avb_adm_cap.up3 】    	{"UP3"     【 wlan.bss_avb_adm_cap.up3  】
1143  :【 wlan.bss_avb_adm_cap.up4 】    	{"UP4"     【 wlan.bss_avb_adm_cap.up4  】
1144  :【 wlan.bss_avb_adm_cap.up5 】    	{"UP5"     【 wlan.bss_avb_adm_cap.up5  】
1145  :【 wlan.bss_avb_adm_cap.up6 】    	{"UP6"     【 wlan.bss_avb_adm_cap.up6  】
1146  :【 wlan.bss_avb_adm_cap.up7 】    	{"UP7"     【 wlan.bss_avb_adm_cap.up7  】
1147  :【 wlan.bss_avb_adm_cap.ac0 】    	{"AC0"     【 wlan.bss_avb_adm_cap.ac0  】
1148  :【 wlan.bss_avb_adm_cap.ac1 】    	{"AC1"     【 wlan.bss_avb_adm_cap.ac1  】
1149  :【 wlan.bss_avb_adm_cap.ac2 】    	{"AC2"     【 wlan.bss_avb_adm_cap.ac2  】
1150  :【 wlan.bss_avb_adm_cap.ac3 】    	{"AC3"     【 wlan.bss_avb_adm_cap.ac3  】
1151  :【 wlan.bss_avg_ac_access_delay.be 】    	{"AC Average Access Delay for Best Effort"     【 wlan.bss_avg_ac_access_delay.be  】
1152  :【 wlan.bss_avg_ac_access_delay.bk 】    	{"AC Average Access Delay for Best Background"     【 wlan.bss_avg_ac_access_delay.bk  】
1153  :【 wlan.bss_avg_ac_access_delay_vi 】    	{"AC Average Access Delay for Video"     【 wlan.bss_avg_ac_access_delay_vi  】
1154  :【 wlan.bss_avg_ac_access_delay_vo 】    	{"AC Average Access Delay for Voice"     【 wlan.bss_avg_ac_access_delay_vo  】
1155  :【 wlan.rmcap 】    	{"RM Capabilities"     【 wlan.rmcap  】
1156  :【 wlan.rmcap.b0 】    	{"Link Measurement"     【 wlan.rmcap.b0  】
1157  :【 wlan.rmcap.b1 】    	{"Neighbor Report"     【 wlan.rmcap.b1  】
1158  :【 wlan.rmcap.b2 】    	{"Parallel Measurements"     【 wlan.rmcap.b2  】
1159  :【 wlan.rmcap.b3 】    	{"Repeated Measurements"     【 wlan.rmcap.b3  】
1160  :【 wlan.rmcap.b4 】    	{"Beacon Passive Measurement"     【 wlan.rmcap.b4  】
1161  :【 wlan.rmcap.b5 】    	{"Beacon Active Measurement"     【 wlan.rmcap.b5  】
1162  :【 wlan.rmcap.b6 】    	{"Beacon Table Measurement"     【 wlan.rmcap.b6  】
1163  :【 wlan.rmcap.b7 】    	{"Beacon Measurement Reporting Conditions"     【 wlan.rmcap.b7  】
1164  :【 wlan.rmcap.b8 】    	{"Frame Measurement"     【 wlan.rmcap.b8  】
1165  :【 wlan.rmcap.b9 】    	{"Channel Load Measurement"     【 wlan.rmcap.b9  】
1166  :【 wlan.rmcap.b10 】    	{"Noise Histogram Measurement"     【 wlan.rmcap.b10  】
1167  :【 wlan.rmcap.b11 】    	{"Statistics Measurement"     【 wlan.rmcap.b11  】
1168  :【 wlan.rmcap.b12 】    	{"LCI Measurement"     【 wlan.rmcap.b12  】
1169  :【 wlan.rmcap.b13 】    	{"LCI Azimuth capability"     【 wlan.rmcap.b13  】
1170  :【 wlan.rmcap.b14 】    	{"Transmit Stream/Category Measurement"     【 wlan.rmcap.b14  】
1171  :【 wlan.rmcap.b15 】    	{"Triggered Transmit Stream/Category Measurement"     【 wlan.rmcap.b15  】
1172  :【 wlan.rmcap.b16 】    	{"AP Channel Report capability"     【 wlan.rmcap.b16  】
1173  :【 wlan.rmcap.b17 】    	{"RM MIB capability"     【 wlan.rmcap.b17  】
1174  :【 wlan.rmcap.b18to20 】    	{"Operating Channel Max Measurement Duration"     【 wlan.rmcap.b18to20  】
1175  :【 wlan.rmcap.b21to23 】    	{"Nonoperating Channel Max Measurement Duration"     【 wlan.rmcap.b21to23  】
1176  :【 wlan.rmcap.b24to26 】    	{"Measurement Pilotcapability"     【 wlan.rmcap.b24to26  】
1177  :【 wlan.rmcap.b27 】    	{"Measurement Pilot Transmission Information"     【 wlan.rmcap.b27  】
1178  :【 wlan.rmcap.b28 】    	{"Neighbor Report TSF Offset"     【 wlan.rmcap.b28  】
1179  :【 wlan.rmcap.b29 】    	{"RCPI Measurement capability"     【 wlan.rmcap.b29  】
1180  :【 wlan.rmcap.b30 】    	{"RSNI Measurement capability"     【 wlan.rmcap.b30  】
1181  :【 wlan.rmcap.b31 】    	{"BSS Average Access Delay capability"     【 wlan.rmcap.b31  】
1182  :【 wlan.rmcap.b32 】    	{"BSS Available Admission Capacity capability"     【 wlan.rmcap.b32  】
1183  :【 wlan.rmcap.b33 】    	{"Antenna capability"     【 wlan.rmcap.b33  】
1184  :【 wlan.rmcap.o5 】    	{"Reserved"     【 wlan.rmcap.o5  】
1185  :【 wlan.rcpi 】    	{"RCPI"     【 wlan.rcpi  】
1186  :【 wlan.multiple_bssid 】    	{"Max BSSID Indicator"     【 wlan.multiple_bssid  】
1187  :【 wlan.multiple_bssid.subelem.id 】    	{"Subelement ID"     【 wlan.multiple_bssid.subelem.id  】
1188  :【 wlan.multiple_bssid.subelem.len 】    	{"Length"     【 wlan.multiple_bssid.subelem.len  】
1189  :【 wlan.multiple_bssid.subelem.reserved 】    	{"Reserved"     【 wlan.multiple_bssid.subelem.reserved  】
1190  :【 wlan.multiple_bssid.subelem.nontrans_profile 】    	{"Nontransmitted Profile"     【 wlan.multiple_bssid.subelem.nontrans_profile  】
1191  :【 wlan.20_40_bc 】    	{"20/40 BSS Coexistence Flags"     【 wlan.20_40_bc  】
1192  :【 wlan.20_40_bc.information_request 】    	{"Information Request"     【 wlan.20_40_bc.information_request  】
1193  :【 wlan.20_40_bc.forty_mhz_intolerant 】    	{"Forty MHz Intolerant"     【 wlan.20_40_bc.forty_mhz_intolerant  】
1194  :【 wlan.20_40_bc.20_mhz_bss_witdh_request 】    	{"20 MHz BSS Witdh Request"     【 wlan.20_40_bc.20_mhz_bss_witdh_request  】
1195  :【 wlan.20_40_bc.obss_scanning_exemption_request 】    	{"OBSS Scanning Exemption Request"     【 wlan.20_40_bc.obss_scanning_exemption_request  】
1196  :【 wlan.20_40_bc.obss_scanning_exemption_grant 】    	{"OBSS Scanning Exemption Grant"     【 wlan.20_40_bc.obss_scanning_exemption_grant  】
1197  :【 wlan.20_40_bc.reserved 】    	{"Reserved"     【 wlan.20_40_bc.reserved  】
1198  :【 wlan.powercon.local 】    	{"Local Power Constraint"     【 wlan.powercon.local  】
1199  :【 wlan.powercap.min 】    	{"Minimum Transmit Power"     【 wlan.powercap.min  】
1200  :【 wlan.powercap.max 】    	{"Maximum Transmit Power"     【 wlan.powercap.max  】
1201  :【 wlan.tcprep.trsmt_pow 】    	{"Transmit Power"     【 wlan.tcprep.trsmt_pow  】
1202  :【 wlan.tcprep.link_mrg 】    	{"Link Margin"     【 wlan.tcprep.link_mrg  】
1203  :【 wlan.supchan 】    	{"Supported Channels Set"     【 wlan.supchan  】
1204  :【 wlan.supchan.first 】    	{"First Supported Channel"     【 wlan.supchan.first  】
1205  :【 wlan.supchan.range 】    	{"Supported Channel Range"     【 wlan.supchan.range  】
1206  :【 wlan.csa.channel_switch_mode 】    	{"Channel Switch Mode"     【 wlan.csa.channel_switch_mode  】
1207  :【 wlan.csa.new_channel_number 】    	{"New Channel Number"     【 wlan.csa.new_channel_number  】
1208  :【 wlan.csa.channel_switch.count 】    	{"Channel Switch Count"     【 wlan.csa.channel_switch.count  】
1209  :【 wlan.csa.mesh_channel_switch.ttl 】    	{"Mesh Channel Switch TTL"     【 wlan.csa.mesh_channel_switch.ttl  】
1210  :【 wlan.csa.mesh_channel_switch.flag 】    	{"Mesh Channel Switch Flag"     【 wlan.csa.mesh_channel_switch.flag  】
1211  :【 wlan.csa.mesh_channel_switch.flag.txrestrict 】    	{"CSA Tx Restrict"     【 wlan.csa.mesh_channel_switch.flag.txrestrict  】
1212  :【 wlan.csa.mesh_channel_switch.flag.initiator 】    	{"CSA Initiator"     【 wlan.csa.mesh_channel_switch.flag.initiator  】
1213  :【 wlan.csa.mesh_channel_switch.reason_code 】    	{"Mesh Channel Switch Reason Code"     【 wlan.csa.mesh_channel_switch.reason_code  】
1214  :【 wlan.csa.mesh_channel_switch.pre_value 】    	{"Mesh Channel Switch Precedence Value"     【 wlan.csa.mesh_channel_switch.pre_value  】
1215  :【 wlan.mesh.mesh_awake_window 】    	{"Mesh Awake Window"     【 wlan.mesh.mesh_awake_window  】
1216  :【 wlan.measure.req.token 】    	{"Measurement Token"     【 wlan.measure.req.token  】
1217  :【 wlan.measure.req.mode 】    	{"Measurement Request Mode"     【 wlan.measure.req.mode  】
1218  :【 wlan.measure.req.reqmode.parallel 】    	{"Parallel"     【 wlan.measure.req.reqmode.parallel  】
1219  :【 wlan.measure.req.reqmode.enable 】    	{"Measurement Request Mode Field"     【 wlan.measure.req.reqmode.enable  】
1220  :【 wlan.measure.req.reqmode.request 】    	{"Measurement Reports"     【 wlan.measure.req.reqmode.request  】
1221  :【 wlan.measure.req.reqmode.report 】    	{"Autonomous Measurement Reports"     【 wlan.measure.req.reqmode.report  】
1222  :【 wlan.measure.req.reqmode.duration_mandatory 】    	{"Duration Mandatory"     【 wlan.measure.req.reqmode.duration_mandatory  】
1223  :【 wlan.measure.req.reqmode.reserved 】    	{"Reserved"     【 wlan.measure.req.reqmode.reserved  】
1224  :【 wlan.measure.req.reqtype 】    	{"Measurement Request Type"     【 wlan.measure.req.reqtype  】
1225  :【 wlan.measure.req.channelnumber 】    	{"Measurement Channel Number"     【 wlan.measure.req.channelnumber  】
1226  :【 wlan.measure.req.starttime 】    	{"Measurement Start Time"     【 wlan.measure.req.starttime  】
1227  :【 wlan.measure.req.channelnumber 】    	{"Measurement Duration"     【 wlan.measure.req.channelnumber  】
1228  :【 wlan.measure.req.operatingclass 】    	{"Operating Class"     【 wlan.measure.req.operatingclass  】
1229  :【 wlan.measure.req.randint 】    	{"Randomization Interval"     【 wlan.measure.req.randint  】
1230  :【 wlan.measure.req.measurementmode 】    	{"Measurement Mode"     【 wlan.measure.req.measurementmode  】
1231  :【 wlan.measure.req.bssid 】    	{"BSSID"     【 wlan.measure.req.bssid  】
1232  :【 wlan.measure.req.sub.length 】    	{"Length"     【 wlan.measure.req.sub.length  】
1233  :【 wlan.measure.req.beacon.sub.id 】    	{"SubElement ID"     【 wlan.measure.req.beacon.sub.id  】
1234  :【 wlan.measure.req.beacon.sub.ssid 】    	{"SSID"     【 wlan.measure.req.beacon.sub.ssid  】
1235  :【 wlan.measure.req.beacon.sub.bri.repcond 】    	{"Reporting Condition"     【 wlan.measure.req.beacon.sub.bri.repcond  】
1236  :【 wlan.measure.req.beacon.sub.bri.threshold_offset 】    	{"Threshold/Offset"     【 wlan.measure.req.beacon.sub.bri.threshold_offset  】
1237  :【 wlan.measure.req.beacon.sub.bri.reporting_detail 】    	{"Reporting Detail"     【 wlan.measure.req.beacon.sub.bri.reporting_detail  】
1238  :【 wlan.measure.req.beacon.sub.request 】    	{"Request"     【 wlan.measure.req.beacon.sub.request  】
1239  :【 wlan.measure.req.beacon.unknown 】    	{"Unknown Data"     【 wlan.measure.req.beacon.unknown  】
1240  :【 wlan.measure.req.channel_load.sub.id 】    	{"SubElement ID"     【 wlan.measure.req.channel_load.sub.id  】
1241  :【 wlan.measure.req.channel_load.sub.repcond 】    	{"Reporting Condition"     【 wlan.measure.req.channel_load.sub.repcond  】
1242  :【 wlan.measure.req.channel_load.sub.ref 】    	{"Reference Value"     【 wlan.measure.req.channel_load.sub.ref  】
1243  :【 wlan.measure.req.noise_histogram.sub.id 】    	{"SubElement ID"     【 wlan.measure.req.noise_histogram.sub.id  】
1244  :【 wlan.measure.reqnoise_histogram.sub.repcond 】    	{"Reporting Condition"     【 wlan.measure.reqnoise_histogram.sub.repcond  】
1245  :【 wlan.measure.req.noise_histogram.sub.anpiref 】    	{"ANPI Reference Value"     【 wlan.measure.req.noise_histogram.sub.anpiref  】
1246  :【 wlan.measure.req.frame_request_type 】    	{"Frame Request Type"     【 wlan.measure.req.frame_request_type  】
1247  :【 wlan.measure.req.mac_address 】    	{"MAC Address"     【 wlan.measure.req.mac_address  】
1248  :【 wlan.measure.req.peer_mac_address 】    	{"Peer MAC Address"     【 wlan.measure.req.peer_mac_address  】
1249  :【 wlan.measure.req.groupid 】    	{"Group ID"     【 wlan.measure.req.groupid  】
1250  :【 wlan.measure.req.unknown 】    	{"Unknown Data"     【 wlan.measure.req.unknown  】
1251  :【 wlan.measure.req.token 】    	{"Measurement Token"     【 wlan.measure.req.token  】
1252  :【 wlan.measure.req.mode 】    	{"Measurement Report Mode"     【 wlan.measure.req.mode  】
1253  :【 wlan.measure.rep.repmode.late 】    	{"Measurement Report Mode Field"     【 wlan.measure.rep.repmode.late  】
1254  :【 wlan.measure.rep.repmode.incapable 】    	{"Measurement Reports"     【 wlan.measure.rep.repmode.incapable  】
1255  :【 wlan.measure.rep.repmode.refused 】    	{"Autonomous Measurement Reports"     【 wlan.measure.rep.repmode.refused  】
1256  :【 wlan.measure.rep.repmode.reserved 】    	{"Reserved"     【 wlan.measure.rep.repmode.reserved  】
1257  :【 wlan.measure.rep.reptype 】    	{"Measurement Report Type"     【 wlan.measure.rep.reptype  】
1258  :【 wlan.measure.rep.channelnumber 】    	{"Measurement Channel Number"     【 wlan.measure.rep.channelnumber  】
1259  :【 wlan.measure.rep.starttime 】    	{"Measurement Start Time"     【 wlan.measure.rep.starttime  】
1260  :【 wlan.measure.rep.channelnumber 】    	{"Measurement Duration"     【 wlan.measure.rep.channelnumber  】
1261  :【 wlan.measure.rep.ccabusy 】    	{"CCA Busy Fraction"     【 wlan.measure.rep.ccabusy  】
1262  :【 wlan.measure.rep.mapfield 】    	{"Map Field"     【 wlan.measure.rep.mapfield  】
1263  :【 wlan.measure.rep.repmode.mapfield.bss 】    	{"BSS"     【 wlan.measure.rep.repmode.mapfield.bss  】
1264  :【 wlan.measure.rep.repmode.mapfie   	{"Orthogonal Frequency Division Multiplexing (OFDM) Preamble"     【 wlan.measure.rep.repmode.mapfield.ofdm_preamble  】
1265  :【 wlan.measure.rep.repmode.mapfield.unidentsig 】    	{"Unidentified Signal"     【 wlan.measure.rep.repmode.mapfield.unidentsig  】
1266  :【 wlan.measure.rep.repmode.mapfield.radar 】    	{"Radar"     【 wlan.measure.rep.repmode.mapfield.radar  】
1267  :【 wlan.measure.rep.repmode.mapfield.unmeasured 】    	{"Unmeasured"     【 wlan.measure.rep.repmode.mapfield.unmeasured  】
1268  :【 wlan.measure.rep.repmode.mapfield.reserved 】    	{"Reserved"     【 wlan.measure.rep.repmode.mapfield.reserved  】
1269  :【 wlan.measure.rep.rpi.histogram_report 】    	{"Receive Power Indicator (RPI) Histogram Report"     【 wlan.measure.rep.rpi.histogram_report  】
1270  :【 wlan.measure.rep.rpi.rpi0density 】    	{"RPI 0 Density"     【 wlan.measure.rep.rpi.rpi0density  】
1271  :【 wlan.measure.rep.rpi.rpi1density 】    	{"RPI 1 Density"     【 wlan.measure.rep.rpi.rpi1density  】
1272  :【 wlan.measure.rep.rpi.rpi2density 】    	{"RPI 2 Density"     【 wlan.measure.rep.rpi.rpi2density  】
1273  :【 wlan.measure.rep.rpi.rpi3density 】    	{"RPI 3 Density"     【 wlan.measure.rep.rpi.rpi3density  】
1274  :【 wlan.measure.rep.rpi.rpi4density 】    	{"RPI 4 Density"     【 wlan.measure.rep.rpi.rpi4density  】
1275  :【 wlan.measure.rep.rpi.rpi5density 】    	{"RPI 5 Density"     【 wlan.measure.rep.rpi.rpi5density  】
1276  :【 wlan.measure.rep.rpi.rpi6density 】    	{"RPI 6 Density"     【 wlan.measure.rep.rpi.rpi6density  】
1277  :【 wlan.measure.rep.rpi.rpi7density 】    	{"RPI 7 Density"     【 wlan.measure.rep.rpi.rpi7density  】
1278  :【 wlan.measure.rep.operatingclass 】    	{"Operating Class"     【 wlan.measure.rep.operatingclass  】
1279  :【 wlan.measure.rep.chanload 】    	{"Channel Load"     【 wlan.measure.rep.chanload  】
1280  :【 wlan.measure.rep.frameinfo 】    	{"Reported Frame Information"     【 wlan.measure.rep.frameinfo  】
1281  :【 wlan.measure.rep.frameinfo.phytype 】    	{"Condensed PHY"     【 wlan.measure.rep.frameinfo.phytype  】
1282  :【 wlan.measure.rep.frameinfo.frametype 】    	{"Reported Frame Type"     【 wlan.measure.rep.frameinfo.frametype  】
1283  :【 wlan.measure.rep.rcpi 】    	{"Received Channel Power Indicator (RCPI)"     【 wlan.measure.rep.rcpi  】
1284  :【 wlan.measure.rep.rsni 】    	{"Received Signal to Noise Indicator (RSNI)"     【 wlan.measure.rep.rsni  】
1285  :【 wlan.measure.rep.bssid 】    	{"BSSID Being Reported"     【 wlan.measure.rep.bssid  】
1286  :【 wlan.measure.rep.antid 】    	{"Antenna ID"     【 wlan.measure.rep.antid  】
1287  :【 wlan.measure.rep.anpi 】    	{"ANPI"     【 wlan.measure.rep.anpi  】
1288  :【 wlan.measure.rep.ipi_density0 】    	{"IPI Density 0"     【 wlan.measure.rep.ipi_density0  】
1289  :【 wlan.measure.rep.ipi_density1 】    	{"IPI Density 1"     【 wlan.measure.rep.ipi_density1  】
1290  :【 wlan.measure.rep.ipi_density2 】    	{"IPI Density 2"     【 wlan.measure.rep.ipi_density2  】
1291  :【 wlan.measure.rep.ipi_density3 】    	{"IPI Density 3"     【 wlan.measure.rep.ipi_density3  】
1292  :【 wlan.measure.rep.ipi_density4 】    	{"IPI Density 4"     【 wlan.measure.rep.ipi_density4  】
1293  :【 wlan.measure.rep.ipi_density5 】    	{"IPI Density 5"     【 wlan.measure.rep.ipi_density5  】
1294  :【 wlan.measure.rep.ipi_density6 】    	{"IPI Density 6"     【 wlan.measure.rep.ipi_density6  】
1295  :【 wlan.measure.rep.ipi_density7 】    	{"IPI Density 7"     【 wlan.measure.rep.ipi_density7  】
1296  :【 wlan.measure.rep.ipi_density8 】    	{"IPI Density 8"     【 wlan.measure.rep.ipi_density8  】
1297  :【 wlan.measure.rep.ipi_density9 】    	{"IPI Density 9"     【 wlan.measure.rep.ipi_density9  】
1298  :【 wlan.measure.rep.ipi_density10 】    	{"IPI Density 10"     【 wlan.measure.rep.ipi_density10  】
1299  :【 wlan.measure.rep.parenttsf 】    	{"Parent Timing Synchronization Function (TSF)"     【 wlan.measure.rep.parenttsf  】
1300  :【 wlan.measure.req.sub.length 】    	{"Length"     【 wlan.measure.req.sub.length  】
1301  :【 wlan.measure.req.beacon.sub.id 】    	{"SubElement ID"     【 wlan.measure.req.beacon.sub.id  】
1302  :【 wlan.measure.rep.unknown 】    	{"Unknown Data"     【 wlan.measure.rep.unknown  】
1303  :【 wlan.quiet.count 】    	{"Count"     【 wlan.quiet.count  】
1304  :【 wlan.quiet.period 】    	{"Period"     【 wlan.quiet.period  】
1305  :【 wlan.quiet.duration 】    	{"Duration"     【 wlan.quiet.duration  】
1306  :【 wlan.quiet.offset 】    	{"Offset"     【 wlan.quiet.offset  】
1307  :【 wlan.dfs.owner 】    	{"Owner"     【 wlan.dfs.owner  】
1308  :【 wlan.dfs.recovery_interval 】    	{"Recovery Interval"     【 wlan.dfs.recovery_interval  】
1309  :【 wlan.dfs.channel_map 】    	{"Channel Map"     【 wlan.dfs.channel_map  】
1310  :【 wlan.dfs.channel_number 】    	{"Channel Number"     【 wlan.dfs.channel_number  】
1311  :【 wlan.dfs.map 】    	{"Map"     【 wlan.dfs.map  】
1312  :【 wlan.erp_info 】    	{"ERP Information"     【 wlan.erp_info  】
1313  :【 wlan.erp_info.erp_present 】    	{"Non ERP Present"     【 wlan.erp_info.erp_present  】
1314  :【 wlan.erp_info.use_protection 】    	{"Use Protection"     【 wlan.erp_info.use_protection  】
1315  :【 wlan.erp_info.barker_preamble_mode 】    	{"Barker Preamble Mode"     【 wlan.erp_info.barker_preamble_mode  】
1316  :【 wlan.erp_info.reserved 】    	{"Reserved"     【 wlan.erp_info.reserved  】
1317  :【 wlan.extcap 】    	{"Extended Capabilities"     【 wlan.extcap  】
1318  :【 wlan.extcap.b0 】    	{"20/40 BSS Coexistence Management Support"     【 wlan.extcap.b0  】
1319  :【 wlan.extcap.b1 】    	{"Reserved (was On-demand beacon)"     【 wlan.extcap.b1  】
1320  :【 wlan.extcap.b2 】    	{"Extended Channel Switching"     【 wlan.extcap.b2  】
1321  :【 wlan.extcap.b3 】    	{"Reserved (was WAVE indication)"     【 wlan.extcap.b3  】
1322  :【 wlan.extcap.b4 】    	{"PSMP Capability"     【 wlan.extcap.b4  】
1323  :【 wlan.extcap.b5 】    	{"Reserved"     【 wlan.extcap.b5  】
1324  :【 wlan.extcap.b6 】    	{"S-PSMP Support"     【 wlan.extcap.b6  】
1325  :【 wlan.extcap.b7 】    	{"Event"     【 wlan.extcap.b7  】
1326  :【 wlan.extcap.b8 】    	{"Diagnostics"     【 wlan.extcap.b8  】
1327  :【 wlan.extcap.b9 】    	{"Multicast Diagnostics"     【 wlan.extcap.b9  】
1328  :【 wlan.extcap.b10 】    	{"Location Tracking"     【 wlan.extcap.b10  】
1329  :【 wlan.extcap.b11 】    	{"FMS"     【 wlan.extcap.b11  】
1330  :【 wlan.extcap.b12 】    	{"Proxy ARP Service"     【 wlan.extcap.b12  】
1331  :【 wlan.extcap.b13 】    	{"Collocated Interference Reporting"     【 wlan.extcap.b13  】
1332  :【 wlan.extcap.b14 】    	{"Civic Location"     【 wlan.extcap.b14  】
1333  :【 wlan.extcap.b15 】    	{"Geospatial Location"     【 wlan.extcap.b15  】
1334  :【 wlan.extcap.b16 】    	{"TFS"     【 wlan.extcap.b16  】
1335  :【 wlan.extcap.b17 】    	{"WNM Sleep Mode"     【 wlan.extcap.b17  】
1336  :【 wlan.extcap.b18 】    	{"TIM Broadcast"     【 wlan.extcap.b18  】
1337  :【 wlan.extcap.b19 】    	{"BSS Transition"     【 wlan.extcap.b19  】
1338  :【 wlan.extcap.b20 】    	{"QoS Traffic Capability"     【 wlan.extcap.b20  】
1339  :【 wlan.extcap.b21 】    	{"AC Station Count"     【 wlan.extcap.b21  】
1340  :【 wlan.extcap.b22 】    	{"Multiple BSSID"     【 wlan.extcap.b22  】
1341  :【 wlan.extcap.b23 】    	{"Timing Measurement"     【 wlan.extcap.b23  】
1342  :【 wlan.extcap.b24 】    	{"Channel Usage"     【 wlan.extcap.b24  】
1343  :【 wlan.extcap.b25 】    	{"SSID List"     【 wlan.extcap.b25  】
1344  :【 wlan.extcap.b26 】    	{"DMS"     【 wlan.extcap.b26  】
1345  :【 wlan.extcap.b27 】    	{"UTC TSF Offset"     【 wlan.extcap.b27  】
1346  :【 wlan.extcap.b28 】    	{"TPU Buffer STA Support"     【 wlan.extcap.b28  】
1347  :【 wlan.extcap.b29 】    	{"TDLS Peer PSM Support"     【 wlan.extcap.b29  】
1348  :【 wlan.extcap.b30 】    	{"TDLS channel switching"     【 wlan.extcap.b30  】
1349  :【 wlan.extcap.b31 】    	{"Interworking"     【 wlan.extcap.b31  】
1350  :【 wlan.extcap.b32 】    	{"QoS Map"     【 wlan.extcap.b32  】
1351  :【 wlan.extcap.b33 】    	{"EBR"     【 wlan.extcap.b33  】
1352  :【 wlan.extcap.b34 】    	{"SSPN Interface"     【 wlan.extcap.b34  】
1353  :【 wlan.extcap.b35 】    	{"Reserved"     【 wlan.extcap.b35  】
1354  :【 wlan.extcap.b36 】    	{"MSGCF Capability"     【 wlan.extcap.b36  】
1355  :【 wlan.extcap.b37 】    	{"TDLS Support"     【 wlan.extcap.b37  】
1356  :【 wlan.extcap.b38 】    	{"TDLS Prohibited"     【 wlan.extcap.b38  】
1357  :【 wlan.extcap.b39 】    	{"TDLS Channel Switching Prohibited"     【 wlan.extcap.b39  】
1358  :【 wlan.extcap.b40 】    	{"Reject Unadmitted Frame"     【 wlan.extcap.b40  】
1359  :【 wlan.extcap.serv_int_granularity 】    	"wlan.extcap.serv_int_granularity  】
1360  :【 wlan.extcap.b44 】    	{"Identifier Location"     【 wlan.extcap.b44  】
1361  :【 wlan.extcap.b45 】    	{"U-APSD Coexistence"     【 wlan.extcap.b45  】
1362  :【 wlan.extcap.b46 】    	{"WNM Notification"     【 wlan.extcap.b46  】
1363  :【 wlan.extcap.b47 】    	{"QAB Capability"     【 wlan.extcap.b47  】
1364  :【 wlan.extcap.b48 】    	{"UTF-8 SSID"     【 wlan.extcap.b48  】
1365  :【 wlan.extcap.b49 】    	{"QMFActivated"     【 wlan.extcap.b49  】
1366  :【 wlan.extcap.b50 】    	{"QMFReconfigurationActivated"     【 wlan.extcap.b50  】
1367  :【 wlan.extcap.b51 】    	{"Robust AV Streaming"     【 wlan.extcap.b51  】
1368  :【 wlan.extcap.b52 】    	{"Advanced GCR"     【 wlan.extcap.b52  】
1369  :【 wlan.extcap.b53 】    	{"Mesh GCR"     【 wlan.extcap.b53  】
1370  :【 wlan.extcap.b54 】    	{"SCS"     【 wlan.extcap.b54  】
1371  :【 wlan.extcap.b55 】    	{"QLoad Report"     【 wlan.extcap.b55  】
1372  :【 wlan.extcap.b56 】    	{"Alternate EDCA"     【 wlan.extcap.b56  】
1373  :【 wlan.extcap.b57 】    	{"Unprotected TXOP Negotiation"     【 wlan.extcap.b57  】
1374  :【 wlan.extcap.b58 】    	{"Protected TXOP Negotiation"     【 wlan.extcap.b58  】
1375  :【 wlan.extcap.b59 】    	{"Reserved"     【 wlan.extcap.b59  】
1376  :【 wlan.extcap.b61 】    	{"Protected QLoad Report"     【 wlan.extcap.b61  】
1377  :【 wlan.extcap.b61 】    	{"TDLS Wider Bandwidth"     【 wlan.extcap.b61  】
1378  :【 wlan.extcap.b62 】    	{"Operating Mode Notification"     【 wlan.extcap.b62  】
1379  :【 wlan.extcap.b63 】    	{"Max Number Of MSDUs In A-MSDU"     【 wlan.extcap.b63  】
1380  :【 wlan.extcap 】    	{"Extended Capabilities"     【 wlan.extcap  】
1381  :【 wlan.extcap.b56 】    	{"Alternate EDCA"     【 wlan.extcap.b56  】
1382  :【 wlan.extcap.b57 】    	{"Unprotected TXOP Negotiation"     【 wlan.extcap.b57  】
1383  :【 wlan.extcap.b58 】    	{"Protected TXOP Negotiation"     【 wlan.extcap.b58  】
1384  :【 wlan.extcap.b59 】    	{"Reserved"     【 wlan.extcap.b59  】
1385  :【 wlan.extcap.b61 】    	{"Protected QLoad Report"     【 wlan.extcap.b61  】
1386  :【 wlan.extcap.b61 】    	{"TDLS Wider Bandwidth"     【 wlan.extcap.b61  】
1387  :【 wlan.extcap.b62 】    	{"Operating Mode Notification"     【 wlan.extcap.b62  】
1388  :【 wlan.extcap.b63 】    	{"Max Number Of MSDUs In A-MSDU"     【 wlan.extcap.b63  】
1389  :【 wlan.extcap.b65 】    	{"Channel Schedule Management"     【 wlan.extcap.b65  】
1390  :【 wlan.extcap.b66 】    	{"Geodatabase Inband Enabling Signal"     【 wlan.extcap.b66  】
1391  :【 wlan.extcap.b67 】    	{"Network Channel Control"     【 wlan.extcap.b67  】
1392  :【 wlan.extcap.b68 】    	{"White Space Map"     【 wlan.extcap.b68  】
1393  :【 wlan.extcap.b69 】    	{"Channel Availability Query"     【 wlan.extcap.b69  】
1394  :【 wlan.extcap.b70 】    	{"Fine Timing Measurement Responder"     【 wlan.extcap.b70  】
1395  :【 wlan.extcap.b71 】    	{"Fine Timing Measurement Initiator"     【 wlan.extcap.b71  】
1396  :【 wlan.extcap.b72 】    	{"FILS Capable"     【 wlan.extcap.b72  】
1397  :【 wlan.extcap.b73 】    	{"Extended Spectrum Management Capable"     【 wlan.extcap.b73  】
1398  :【 wlan.extcap.b74 】    	{"Future Channel Capable"     【 wlan.extcap.b74  】
1399  :【 wlan.extcap.b75 】    	{"Reserved"     【 wlan.extcap.b75  】
1400  :【 wlan.extcap.b76 】    	{"Reserved"     【 wlan.extcap.b76  】
1401  :【 wlan.extcap.b77 】    	{"TWT Requester Support"     【 wlan.extcap.b77  】
1402  :【 wlan.extcap.b78 】    	{"TWT Responder Support"     【 wlan.extcap.b78  】
1403  :【 wlan.extcap.b79 】    	{"OBSS Narrow Bandwidth RU in UL OFDMA Tolerance Support"     【 wlan.extcap.b79  】
1404  :【 wlan.cisco.ccx1.unknown 】    	{"Unknown"     【 wlan.cisco.ccx1.unknown  】
1405  :【 wlan.cisco.ccx1.name 】    	{"Name"     【 wlan.cisco.ccx1.name  】
1406  :【 wlan.cisco.ccx1.clients 】    	{"Clients"     【 wlan.cisco.ccx1.clients  】
1407  :【 wlan.cisco.ccx1.unknown2 】    	{"Unknown2"     【 wlan.cisco.ccx1.unknown2  】
1408  :【 wlan.nreport.bssid 】    	{"BSSID"     【 wlan.nreport.bssid  】
1409  :【 wlan.nreport.bssid.info 】    	{"BSSID Information"     【 wlan.nreport.bssid.info  】
1410  :【 wlan.nreport.bssid.info.reachability 】    	{"AP Reachability"     【 wlan.nreport.bssid.info.reachability  】
1411  :【 wlan.nreport.bssid.info.security 】    	{"Security"     【 wlan.nreport.bssid.info.security  】
1412  :【 wlan.nreport.bssid.info.keyscope 】    	{"Key Scope"     【 wlan.nreport.bssid.info.keyscope  】
1413  :【 wlan.nreport.bssid.info.capability 】    	{"Capability"     【 wlan.nreport.bssid.info.capability  】
1414  :【 wlan.nreport.bssid.info.capability.specmngt 】    	{"Spectrum Management"     【 wlan.nreport.bssid.info.capability.specmngt  】
1415  :【 wlan.nreport.bssid.info.capability.qos 】    	{"QoS"     【 wlan.nreport.bssid.info.capability.qos  】
1416  :【 wlan.nreport.bssid.info.capability.apsd 】    	{"APSD"     【 wlan.nreport.bssid.info.capability.apsd  】
1417  :【 wlan.nreport.bssid.info.capability.radiomsnt 】    	{"Radio Measurement"     【 wlan.nreport.bssid.info.capability.radiomsnt  】
1418  :【 wlan.nreport.bssid.info.capability.dback 】    	{"Delayed Block Ack"     【 wlan.nreport.bssid.info.capability.dback  】
1419  :【 wlan.nreport.bssid.info.capability.iback 】    	{"Immediate Block Ack"     【 wlan.nreport.bssid.info.capability.iback  】
1420  :【 wlan.nreport.bssid.info.mobilitydomain 】    	{"Mobility Domain"     【 wlan.nreport.bssid.info.mobilitydomain  】
1421  :【 wlan.nreport.bssid.info.hthoughput 】    	{"High Throughput Control (+HTC)"     【 wlan.nreport.bssid.info.hthoughput  】
1422  :【 wlan.nreport.bssid.info.vht 】    	{"Very High Throughput (+VHT)"     【 wlan.nreport.bssid.info.vht  】
1423  :【 wlan.nreport.bssid.info.ftm 】    	{"Fine Timing Measurement (FTM)"     【 wlan.nreport.bssid.info.ftm  】
1424  :【 wlan.nreport.bssid.info.he 】    	{"High Efficiency (HE AP)"     【 wlan.nreport.bssid.info.he  】
1425  :【 wlan.nreport.bssid.info.er_bss 】    	{"Extended Range BSS"     【 wlan.nreport.bssid.info.er_bss  】
1426  :【 wlan.nreport.bssid.info.reserved 】    	{"Reserved"     【 wlan.nreport.bssid.info.reserved  】
1427  :【 wlan.nreport.opeclass 】    	{"Operating Class"     【 wlan.nreport.opeclass  】
1428  :【 wlan.nreport.channumber 】    	{"Channel Number"     【 wlan.nreport.channumber  】
1429  :【 wlan.nreport.phytype 】    	{"PHY Type"     【 wlan.nreport.phytype  】
1430  :【 wlan.nreport.subelem.id 】    	{"ID"     【 wlan.nreport.subelem.id  】
1431  :【 wlan.nreport.subelem.len 】    	{"Length"     【 wlan.nreport.subelem.len  】
1432  :【 wlan.nreport.subelem.data 】    	{"Data"     【 wlan.nreport.subelem.data  】
1433  :【 wlan.nreport.subelem.bss_trn_can_pref 】    	{"Preference"     【 wlan.nreport.subelem.bss_trn_can_pref  】
1434  :【 wlan.nreport.subelem.bss_ter_tsf 】    	{"BSS Termination TSF"     【 wlan.nreport.subelem.bss_ter_tsf  】
1435  :【 wlan.nreport.subelem.bss_dur 】    	{"Duration"     【 wlan.nreport.subelem.bss_dur  】
1436  :【 wlan.nreport.subelem.tsf_offset 】    	{"TSF Offset"     【 wlan.nreport.subelem.tsf_offset  】
1437  :【 wlan.nreport.subelem.beacon_interval 】    	{"Beacon Interval"     【 wlan.nreport.subelem.beacon_interval  】
1438  :【 wlan.nreport.subelem.country_code 】    	{"Country Code"     【 wlan.nreport.subelem.country_code  】
1439  :【 wlan.supopeclass.current 】    	{"Current Operating Class"     【 wlan.supopeclass.current  】
1440  :【 wlan.supopeclass.alt 】    	{"Alternate Operating Classes"     【 wlan.supopeclass.alt  】
1441  :【 wlan.wfa.ie.type 】    	{"Type"     【 wlan.wfa.ie.type  】
1442  :【 wlan.wfa.ie.wpa.version 】    	{"WPA Version"     【 wlan.wfa.ie.wpa.version  】
1443  :【 wlan.wfa.ie.wpa.mcs 】    	{"Multicast Cipher Suite"     【 wlan.wfa.ie.wpa.mcs  】
1444  :【 wlan.wfa.ie.wpa.mcs.oui 】    	{"Multicast Cipher Suite OUI"     【 wlan.wfa.ie.wpa.mcs.oui  】
1445  :【 wlan.wfa.ie.wpa.mcs.type 】    	{"Multicast Cipher Suite type"     【 wlan.wfa.ie.wpa.mcs.type  】
1446  :【 wlan.wfa.ie.wpa.mcs.type 】    	{"Multicast Cipher Suite type"     【 wlan.wfa.ie.wpa.mcs.type  】
1447  :【 wlan.wfa.ie.wpa.ucs.count 】    	{"Unicast Cipher Suite Count"     【 wlan.wfa.ie.wpa.ucs.count  】
1448  :【 wlan.wfa.ie.wpa.ucs.list 】    	{"Unicast Cipher Suite List"     【 wlan.wfa.ie.wpa.ucs.list  】
1449  :【 wlan.wfa.ie.wpa.ucs 】    	{"Unicast Cipher Suite"     【 wlan.wfa.ie.wpa.ucs  】
1450  :【 wlan.wfa.ie.wpau.cs.oui 】    	{"Unicast Cipher Suite OUI"     【 wlan.wfa.ie.wpau.cs.oui  】
1451  :【 wlan.wfa.ie.wpa.ucs.type 】    	{"Unicast Cipher Suite type"     【 wlan.wfa.ie.wpa.ucs.type  】
1452  :【 wlan.wfa.ie.wpa.ucs.type 】    	{"Unicast Cipher Suite type"     【 wlan.wfa.ie.wpa.ucs.type  】
1453  :【 wlan.wfa.ie.wpa.akms.count 】    	{"Auth Key Management (AKM) Suite Count"     【 wlan.wfa.ie.wpa.akms.count  】
1454  :【 wlan.wfa.ie.wpa.akms.list 】    	{"Auth Key Management (AKM) List"     【 wlan.wfa.ie.wpa.akms.list  】
1455  :【 wlan.wfa.ie.wpa.akms 】    	{"Auth Key Management (AKM) Suite"     【 wlan.wfa.ie.wpa.akms  】
1456  :【 wlan.wfa.ie.wpa.akms.oui 】    	{"Auth Key Management (AKM) OUI"     【 wlan.wfa.ie.wpa.akms.oui  】
1457  :【 wlan.wfa.ie.wpa.akms.type 】    	{"Auth Key Management (AKM) type"     【 wlan.wfa.ie.wpa.akms.type  】
1458  :【 wlan.wfa.ie.wpa.type 】    	{"Auth Key Management (AKM) type"     【 wlan.wfa.ie.wpa.type  】
1459  :【 wlan.wfa.ie.wme.subtype 】    	{"WME Subtype"     【 wlan.wfa.ie.wme.subtype  】
1460  :【 wlan.wfa.ie.wme.version 】    	{"WME Version"     【 wlan.wfa.ie.wme.version  】
1461  :【 wlan.wfa.ie.wme.qos_info 】    	{"WME QoS Info"     【 wlan.wfa.ie.wme.qos_info  】
1462  :【 wlan.wfa.ie.wme.qos_info.sta.max_sp_length 】    	{"Max SP Length"     【 wlan.wfa.ie.wme.qos_info.sta.max_sp_length  】
1463  :【 wlan.wfa.ie.wme.qos_info.sta.ac_be 】    	{"AC_BE"     【 wlan.wfa.ie.wme.qos_info.sta.ac_be  】
1464  :【 wlan.wfa.ie.wme.qos_info.sta.ac_bk 】    	{"AC_BK"     【 wlan.wfa.ie.wme.qos_info.sta.ac_bk  】
1465  :【 wlan.wfa.ie.wme.qos_info.sta.ac_vi 】    	{"AC_VI"     【 wlan.wfa.ie.wme.qos_info.sta.ac_vi  】
1466  :【 wlan.wfa.ie.wme.qos_info.sta.ac_vo 】    	{"AC_VO"     【 wlan.wfa.ie.wme.qos_info.sta.ac_vo  】
1467  :【 wlan.wfa.ie.wme.qos_info.sta.reserved 】    	{"Reserved"     【 wlan.wfa.ie.wme.qos_info.sta.reserved  】
1468  :【 wlan.wfa.ie.wme.qos_info.ap.u_apsd 】    	{"U-APSD"     【 wlan.wfa.ie.wme.qos_info.ap.u_apsd  】
1469  :【 wlan.wfa.ie.wme.qos_info.ap.parameter_set_count 】    	{"Parameter Set Count"     【 wlan.wfa.ie.wme.qos_info.ap.parameter_set_count  】
1470  :【 wlan.wfa.ie.wme.qos_info.ap.reserved 】    	{"Reserved"     【 wlan.wfa.ie.wme.qos_info.ap.reserved  】
1471  :【 wlan.wfa.ie.wme.reserved 】    	{"Reserved"     【 wlan.wfa.ie.wme.reserved  】
1472  :【 wlan.wfa.ie.wme.acp 】    	{"Ac Parameters"     【 wlan.wfa.ie.wme.acp  】
1473  :【 wlan.wfa.ie.wme.acp.aci_aifsn 】    	{"ACI / AIFSN Field"     【 wlan.wfa.ie.wme.acp.aci_aifsn  】
1474  :【 wlan.wfa.ie.wme.acp.aci 】    	{"ACI"     【 wlan.wfa.ie.wme.acp.aci  】
1475  :【 wlan.wfa.ie.wme.acp.acm 】    	{"Admission Control Mandatory"     【 wlan.wfa.ie.wme.acp.acm  】
1476  :【 wlan.wfa.ie.wme.acp.aifsn 】    	{"AIFSN"     【 wlan.wfa.ie.wme.acp.aifsn  】
1477  :【 wlan.wfa.ie.wme.acp.reserved 】    	{"Reserved"     【 wlan.wfa.ie.wme.acp.reserved  】
1478  :【 wlan.wfa.ie.wme.acp.ecw 】    	{"ECW"     【 wlan.wfa.ie.wme.acp.ecw  】
1479  :【 wlan.wfa.ie.wme.acp.ecw.max 】    	{"ECW Max"     【 wlan.wfa.ie.wme.acp.ecw.max  】
1480  :【 wlan.wfa.ie.wme.acp.ecw.min 】    	{"ECW Min"     【 wlan.wfa.ie.wme.acp.ecw.min  】
1481  :【 wlan.wfa.ie.wme.acp.cw.max 】    	{"CW Max"     【 wlan.wfa.ie.wme.acp.cw.max  】
1482  :【 wlan.wfa.ie.wme.acp.cw.min 】    	{"CW Min"     【 wlan.wfa.ie.wme.acp.cw.min  】
1483  :【 wlan.wfa.ie.wme.acp.txop_limit 】    	{"TXOP Limit"     【 wlan.wfa.ie.wme.acp.txop_limit  】
1484  :【 wlan.wfa.ie.wme.tspec.ts_info 】    	{"TS Info"     【 wlan.wfa.ie.wme.tspec.ts_info  】
1485  :【 wlan.wfa.ie.wme.tspec.ts_info.tid 】    	{"TID"     【 wlan.wfa.ie.wme.tspec.ts_info.tid  】
1486  :【 wlan.wfa.ie.wme.tspec.ts_info.dir 】    	{"Direction"     【 wlan.wfa.ie.wme.tspec.ts_info.dir  】
1487  :【 wlan.wfa.ie.wme.tspec.ts_info.psb 】    	{"PSB"     【 wlan.wfa.ie.wme.tspec.ts_info.psb  】
1488  :【 wlan.wfa.ie.wme.tspec.ts_info.up 】    	{"UP"     【 wlan.wfa.ie.wme.tspec.ts_info.up  】
1489  :【 wlan.wfa.ie.wme.tspec.ts_info.reserved 】    	{"Reserved"     【 wlan.wfa.ie.wme.tspec.ts_info.reserved  】
1490  :【 wlan.wfa.ie.wme.tspec.nor_msdu 】    	{"Normal MSDU Size"     【 wlan.wfa.ie.wme.tspec.nor_msdu  】
1491  :【 wlan.wfa.ie.wme.tspec.max_msdu 】    	{"Maximum MSDU Size"     【 wlan.wfa.ie.wme.tspec.max_msdu  】
1492  :【 wlan.wfa.ie.wme.tspec.min_srv 】    	{"Minimum Service Interval"     【 wlan.wfa.ie.wme.tspec.min_srv  】
1493  :【 wlan.wfa.ie.wme.tspec.max_srv 】    	{"Maximum Service Interval"     【 wlan.wfa.ie.wme.tspec.max_srv  】
1494  :【 wlan.wfa.ie.wme.tspec.inact_int 】    	{"Inactivity Interval"     【 wlan.wfa.ie.wme.tspec.inact_int  】
1495  :【 wlan.wfa.ie.wme.tspec.susp_int 】    	{"Suspension Interval"     【 wlan.wfa.ie.wme.tspec.susp_int  】
1496  :【 wlan.wfa.ie.wme.tspec.srv_start 】    	{"Service Start Time"     【 wlan.wfa.ie.wme.tspec.srv_start  】
1497  :【 wlan.wfa.ie.wme.tspec.min_data 】    	{"Minimum Data Rate"     【 wlan.wfa.ie.wme.tspec.min_data  】
1498  :【 wlan.wfa.ie.wme.tspec.mean_data 】    	{"Mean Data Rate"     【 wlan.wfa.ie.wme.tspec.mean_data  】
1499  :【 wlan.wfa.ie.wme.tspec.peak_data 】    	{"Peak Data Rate"     【 wlan.wfa.ie.wme.tspec.peak_data  】
1500  :【 wlan.wfa.ie.wme.tspec.burst_size 】    	{"Burst Size"     【 wlan.wfa.ie.wme.tspec.burst_size  】
1501  :【 wlan.wfa.ie.wme.tspec.delay_bound 】    	{"Delay Bound"     【 wlan.wfa.ie.wme.tspec.delay_bound  】
1502  :【 wlan.wfa.ie.wme.tspec.min_phy 】    	{"Minimum PHY Rate"     【 wlan.wfa.ie.wme.tspec.min_phy  】
1503  :【 wlan.wfa.ie.wme.tspec.surplus 】    	{"Surplus Bandwidth Allowance"     【 wlan.wfa.ie.wme.tspec.surplus  】
1504  :【 wlan.wfa.ie.wme.tspec.medium 】    	{"Medium Time"     【 wlan.wfa.ie.wme.tspec.medium  】
1505  :【 wlan.wfa.ie.owe.bssid 】    	{"BSSID"     【 wlan.wfa.ie.owe.bssid  】
1506  :【 wlan.wfa.ie.owe.ssid_length 】    	{"SSID length"     【 wlan.wfa.ie.owe.ssid_length  】
1507  :【 wlan.wfa.ie.owe.ssid 】    	{"SSID"     【 wlan.wfa.ie.owe.ssid  】
1508  :【 wlan.wfa.ie.owe.band_info 】    	{"Band info"     【 wlan.wfa.ie.owe.band_info  】
1509  :【 wlan.wfa.ie.owe.channel_info 】    	{"Channel info"     【 wlan.wfa.ie.owe.channel_info  】
1510  :【 wlan.rsn.ie.gtk.key 】    	{"GTK"     【 wlan.rsn.ie.gtk.key  】
1511  :【 wlan.rsn.ie.gtk.keyid 】    	{"KeyID"     【 wlan.rsn.ie.gtk.keyid  】
1512  :【 wlan.rsn.ie.gtk.tx 】    	{"Tx"     【 wlan.rsn.ie.gtk.tx  】
1513  :【 wlan.rsn.ie.gtk.reserved1 】    	{"Reserved"     【 wlan.rsn.ie.gtk.reserved1  】
1514  :【 wlan.rsn.ie.gtk.reserved2 】    	{"Reserved"     【 wlan.rsn.ie.gtk.reserved2  】
1515  :【 wlan.rsn.ie.pmkid 】    	{"PMKID"     【 wlan.rsn.ie.pmkid  】
1516  :【 wlan.rsn.ie.igtk.keyid 】    	{"KeyId"     【 wlan.rsn.ie.igtk.keyid  】
1517  :【 wlan.rsn.ie.igtk.ipn 】    	{"IPN"     【 wlan.rsn.ie.igtk.ipn  】
1518  :【 wlan.rsn.ie.igtk.key 】    	{"IGTK"     【 wlan.rsn.ie.igtk.key  】
1519  :【 wlan.rsn.ie.unknown 】    	{"RSN Unknown"     【 wlan.rsn.ie.unknown  】
1520  :【 wlan.marvell.ie.type 】    	{"Type"     【 wlan.marvell.ie.type  】
1521  :【 wlan.marvell.ie.subtype 】    	{"Subtype"     【 wlan.marvell.ie.subtype  】
1522  :【 wlan.marvell.ie.version 】    	{"Version"     【 wlan.marvell.ie.version  】
1523  :【 wlan.marvell.ie.proto_id 】    	{"Path Selection Protocol"     【 wlan.marvell.ie.proto_id  】
1524  :【 wlan.marvell.ie.metric_id 】    	{"Path Selection Metric"     【 wlan.marvell.ie.metric_id  】
1525  :【 wlan.marvell.ie.cap 】    	{"Mesh Capabilities"     【 wlan.marvell.ie.cap  】
1526  :【 wlan.marvell.data 】    	{ "Marvell IE data"     【 wlan.marvell.data  】
1527  :【 wlan.atheros.ie.type 】    	{"Type"     【 wlan.atheros.ie.type  】
1528  :【 wlan.atheros.ie.subtype 】    	{"Subtype"     【 wlan.atheros.ie.subtype  】
1529  :【 wlan.atheros.ie.version 】    	{"Version"     【 wlan.atheros.ie.version  】
1530  :【 wlan.ie.atheros.capabilities.turbop 】    	{"Turbo Prime"     【 wlan.ie.atheros.capabilities.turbop  】
1531  :【 wlan.ie.atheros.capabilities.comp 】    	{"Compression"     【 wlan.ie.atheros.capabilities.comp  】
1532  :【 wlan.ie.atheros.capabilities.ff 】    	{"Fast Frames"     【 wlan.ie.atheros.capabilities.ff  】
1533  :【 wlan.ie.atheros.capabilities.xr 】    	{"eXtended Range"     【 wlan.ie.atheros.capabilities.xr  】
1534  :【 wlan.ie.atheros.capabilities.ar 】    	{"Advanced Radar"     【 wlan.ie.atheros.capabilities.ar  】
1535  :【 wlan.ie.atheros.capabilities.burst 】    	{"Burst"     【 wlan.ie.atheros.capabilities.burst  】
1536  :【 wlan.ie.atheros.capabilities.wme 】    	{"CWMin tuning"     【 wlan.ie.atheros.capabilities.wme  】
1537  :【 wlan.ie.atheros.capabilities.boost 】    	{"Boost"     【 wlan.ie.atheros.capabilities.boost  】
1538  :【 wlan.atheros.ie.advcap.cap 】    	{"Capabilities"     【 wlan.atheros.ie.advcap.cap  】
1539  :【 wlan.atheros.ie.advcap.defkey 】    	{"Default key index"     【 wlan.atheros.ie.advcap.defkey  】
1540  :【 wlan.atheros.ie.xr.info 】    	{"Info"     【 wlan.atheros.ie.xr.info  】
1541  :【 wlan.atheros.ie.xr.base_bssid 】    	{"Base BSS Id"     【 wlan.atheros.ie.xr.base_bssid  】
1542  :【 wlan.atheros.ie.xr.xr_bssid 】    	{"XR BSS Id"     【 wlan.atheros.ie.xr.xr_bssid  】
1543  :【 wlan.atheros.ie.xr.xr_beacon 】    	{"XR Beacon Interval"     【 wlan.atheros.ie.xr.xr_beacon  】
1544  :【 wlan.atheros.ie.xr.base_cap 】    	{"Base capabilities"     【 wlan.atheros.ie.xr.base_cap  】
1545  :【 wlan.atheros.ie.xr.xr_cap 】    	{"XR capabilities"     【 wlan.atheros.ie.xr.xr_cap  】
1546  :【 wlan.atheros.data 】    	{"Atheros IE data"     【 wlan.atheros.data  】
1547  :【 wlan.aironet.type 】    	{"Aironet IE type"     【 wlan.aironet.type  】
1548  :【 wlan.aironet.dtpc 】    	{"Aironet IE CCX DTCP"     【 wlan.aironet.dtpc  】
1549  :【 wlan.aironet.dtpc_unknown 】    	{"Aironet IE CCX DTCP Unknown"     【 wlan.aironet.dtpc_unknown  】
1550  :【 wlan.aironet.version 】    	{"Aironet IE CCX version"     【 wlan.aironet.version  】
1551  :【 wlan.aironet.data 】    	{ "Aironet IE data"     【 wlan.aironet.data  】
1552  :【 wlan.qbss.version 】    	{"QBSS Version"     【 wlan.qbss.version  】
1553  :【 wlan.qbss.scount 】    	{"Station Count"     【 wlan.qbss.scount  】
1554  :【 wlan.qbss.cu 】    	{"Channel Utilization"     【 wlan.qbss.cu  】
1555  :【 wlan.qbss.adc 】    	{"Available Admission Capacity"     【 wlan.qbss.adc  】
1556  :【 wlan.qbss2.cu 】    	{"Channel Utilization"     【 wlan.qbss2.cu  】
1557  :【 wlan.qbss2.glimit 】    	{"G.711 CU Quantum"     【 wlan.qbss2.glimit  】
1558  :【 wlan.qbss2.cal 】    	{"Call Admission Limit"     【 wlan.qbss2.cal  】
1559  :【 wlan.qbss2.scount 】    	{"Station Count"     【 wlan.qbss2.scount  】
1560  :【 wlan.aironet.qos.reserved 】    	{"Aironet IE QoS reserved"     【 wlan.aironet.qos.reserved  】
1561  :【 wlan.aironet.qos.paramset 】    	{"Aironet IE QoS paramset"     【 wlan.aironet.qos.paramset  】
1562  :【 wlan.aironet.qos.val 】    	{"Aironet IE QoS valueset"     【 wlan.aironet.qos.val  】
1563  :【 wlan.aironet.clientmfp 】    	{"Aironet IE Client MFP"     【 wlan.aironet.clientmfp  】
1564  :【 wlan.vs.nintendo.type 】    	{"Type"     【 wlan.vs.nintendo.type  】
1565  :【 wlan.vs.nintendo.length 】    	{"Length"     【 wlan.vs.nintendo.length  】
1566  :【 wlan.vs.nintendo.servicelist 】    	{"Servicelist"     【 wlan.vs.nintendo.servicelist  】
1567  :【 wlan.vs.nintendo.service 】    	{"Service"     【 wlan.vs.nintendo.service  】
1568  :【 wlan.vs.nintendo.consoleid 】    	{"Console ID"     【 wlan.vs.nintendo.consoleid  】
1569  :【 wlan.vs.nintendo.unknown 】    	{"Unknown"     【 wlan.vs.nintendo.unknown  】
1570  :【 wlan.vs.aruba.subtype 】    	{"Subtype"     【 wlan.vs.aruba.subtype  】
1571  :【 wlan.vs.aruba.ap_name 】    	{"AP Name"     【 wlan.vs.aruba.ap_name  】
1572  :【 wlan.vs.aruba.data 】    	{"Data"     【 wlan.vs.aruba.data  】
1573  :【 wlan.vs.routerboard.unknown 】    	{"Unknown"     【 wlan.vs.routerboard.unknown  】
1574  :【 wlan.vs.routerboard.subitem 】    	{"Sub IE"     【 wlan.vs.routerboard.subitem  】
1575  :【 wlan.vs.routerboard.subtype 】    	{"Subtype"     【 wlan.vs.routerboard.subtype  】
1576  :【 wlan.vs.routerboard.sublength 】    	{"Sublength"     【 wlan.vs.routerboard.sublength  】
1577  :【 wlan.vs.routerboard.subdata 】    	{"Subdata"     【 wlan.vs.routerboard.subdata  】
1578  :【 wlan.vs.routerboard.subtype1_prefix 】    	{"Subtype 1 Prefix"     【 wlan.vs.routerboard.subtype1_prefix  】
1579  :【 wlan.vs.routerboard.subtype1_data 】    	{"Subtype 1 Data"     【 wlan.vs.routerboard.subtype1_data  】
1580  :【 wlan.vs.meru.unknown 】    	{"Sub IE"     【 wlan.vs.meru.unknown  】
1581  :【 wlan.vs.meru.subtype 】    	{"Subtype"     【 wlan.vs.meru.subtype  】
1582  :【 wlan.vs.meru.sublength 】    	{"Sublength"     【 wlan.vs.meru.sublength  】
1583  :【 wlan.vs.meru.subdata 】    	{"Subdata"     【 wlan.vs.meru.subdata  】
1584  :【 wlan.vs.extreme.subtype 】    	{"Subtype"     【 wlan.vs.extreme.subtype  】
1585  :【 wlan.vs.extreme.subdata 】    	{"Subdata"     【 wlan.vs.extreme.subdata  】
1586  :【 wlan.vs.extreme.unknown 】    	{"Unknown"     【 wlan.vs.extreme.unknown  】
1587  :【 wlan.vs.extreme.ap_length 】    	{"AP Length"     【 wlan.vs.extreme.ap_length  】
1588  :【 wlan.vs.extreme.ap_name 】    	{"AP Name"     【 wlan.vs.extreme.ap_name  】
1589  :【 wlan.vs.aerohive.unknown 】    	{"Unknown"     【 wlan.vs.aerohive.unknown  】
1590  :【 wlan.vs.aerohive.hostname_length 】    	{"Host Name Length"     【 wlan.vs.aerohive.hostname_length  】
1591  :【 wlan.vs.aerohive.hostname 】    	{"Host Name"     【 wlan.vs.aerohive.hostname  】
1592  :【 wlan.vs.aerohive.data 】    	{"Data"     【 wlan.vs.aerohive.data  】
1593  :【 wlan.ts_info 】    	{"Traffic Stream (TS) Info"     【 wlan.ts_info  】
1594  :【 wlan.ts_info.type 】    	{"Traffic Type"     【 wlan.ts_info.type  】
1595  :【 wlan.ts_info.tsid 】    	{"Traffic Stream ID (TSID)"     【 wlan.ts_info.tsid  】
1596  :【 wlan.ts_info.dir 】    	{"Direction"     【 wlan.ts_info.dir  】
1597  :【 wlan.ts_info.dir 】    	{"Access Policy"     【 wlan.ts_info.dir  】
1598  :【 wlan.ts_info.agg 】    	{"Aggregation"     【 wlan.ts_info.agg  】
1599  :【 wlan.ts_info.apsd 】    	{"Automatic Power-Save Delivery (APSD)"     【 wlan.ts_info.apsd  】
1600  :【 wlan.ts_info.up 】    	{"User Priority"     【 wlan.ts_info.up  】
1601  :【 wlan.ts_info.ack 】    	{"Ack Policy"     【 wlan.ts_info.ack  】
1602  :【 wlan.ts_info.sched 】    	{"Schedule"     【 wlan.ts_info.sched  】
1603  :【 wlan.ts_info.rsv 】    	{"Reserved"     【 wlan.ts_info.rsv  】
1604  :【 wlan.tspec.nor_msdu 】    	{"Normal MSDU Size"     【 wlan.tspec.nor_msdu  】
1605  :【 wlan.tspec.max_msdu 】    	{"Maximum MSDU Size"     【 wlan.tspec.max_msdu  】
1606  :【 wlan.tspec.min_srv 】    	{"Minimum Service Interval"     【 wlan.tspec.min_srv  】
1607  :【 wlan.tspec.max_srv 】    	{"Maximum Service Interval"     【 wlan.tspec.max_srv  】
1608  :【 wlan.tspec.inact_int 】    	{"Inactivity Interval"     【 wlan.tspec.inact_int  】
1609  :【 wlan.tspec.susp_int 】    	{"Suspension Interval"     【 wlan.tspec.susp_int  】
1610  :【 wlan.tspec.srv_start 】    	{"Service Start Time"     【 wlan.tspec.srv_start  】
1611  :【 wlan.tspec.min_data 】    	{"Minimum Data Rate"     【 wlan.tspec.min_data  】
1612  :【 wlan.tspec.mean_data 】    	{"Mean Data Rate"     【 wlan.tspec.mean_data  】
1613  :【 wlan.tspec.peak_data 】    	{"Peak Data Rate"     【 wlan.tspec.peak_data  】
1614  :【 wlan.tspec.burst_size 】    	{"Burst Size"     【 wlan.tspec.burst_size  】
1615  :【 wlan.tspec.delay_bound 】    	{"Delay Bound"     【 wlan.tspec.delay_bound  】
1616  :【 wlan.tspec.min_phy 】    	{"Minimum PHY Rate"     【 wlan.tspec.min_phy  】
1617  :【 wlan.tspec.surplus 】    	{"Surplus Bandwidth Allowance"     【 wlan.tspec.surplus  】
1618  :【 wlan.tspec.medium 】    	{"Medium Time"     【 wlan.tspec.medium  】
1619  :【 wlan.tspec.dmg 】    	{"DMG attributes"     【 wlan.tspec.dmg  】
1620  :【 wlan.ts_delay 】    	{"Traffic Stream (TS) Delay"     【 wlan.ts_delay  】
1621  :【 wlan.tclas_proc.processing 】    	{"Processing"     【 wlan.tclas_proc.processing  】
1622  :【 wlan.extended_supported_rates 】    	{"Extended Supported Rates"     【 wlan.extended_supported_rates  】
1623  :【 wlan.sched.sched_info 】    	{"Schedule Info"     【 wlan.sched.sched_info  】
1624  :【 wlan.sched_info.agg 】    	{"Schedule Aggregation"     【 wlan.sched_info.agg  】
1625  :【 wlan.sched_info.tsid 】    	{"Schedule Traffic Stream ID (TSID)"     【 wlan.sched_info.tsid  】
1626  :【 wlan.sched_info.dir 】    	{"Schedule Direction"     【 wlan.sched_info.dir  】
1627  :【 wlan.sched.srv_start 】    	{"Service Start Time"     【 wlan.sched.srv_start  】
1628  :【 wlan.sched.srv_int 】    	{"Service Interval"     【 wlan.sched.srv_int  】
1629  :【 wlan.sched.spec_int 】    	{"Specification Interval"     【 wlan.sched.spec_int  】
1630  :【 wlan.aruba.type 】    	{"Aruba Type"     【 wlan.aruba.type  】
1631  :【 wlan.aruba.heartbeat_sequence 】    	{"Aruba Heartbeat Sequence"     【 wlan.aruba.heartbeat_sequence  】
1632  :【 wlan.aruba.mtu_size 】    	{"Aruba MTU Size"     【 wlan.aruba.mtu_size  】
1633  :【 wlan.htc 】    	{"HT Control (+HTC)"     【 wlan.htc  】
1634  :【 wlan.htc.vht 】    	{"VHT"     【 wlan.htc.vht  】
1635  :【 wlan.htc.he 】    	{"HE"     【 wlan.htc.he  】
1636  :【 wlan.htc.he.a_control.ctrl_id 】    	{"Control ID"     【 wlan.htc.he.a_control.ctrl_id  】
1637  :【 wlan.htc.he.a_control.umrs.he_tb_ppdu_len 】    	{"HE TB PPDU Length"     【 wlan.htc.he.a_control.umrs.he_tb_ppdu_len  】
1638  :【 wlan.htc.he.a_control.umrs.ru_allocation 】    	{"RU Allocation"     【 wlan.htc.he.a_control.umrs.ru_allocation  】
1639  :【 wlan.htc.he.a_control.umrs.dl_tx_power 】    	{"DL Tx Power"     【 wlan.htc.he.a_control.umrs.dl_tx_power  】
1640  :【 wlan.htc.he.a_control.umrs.ul_target_rssi 】    	{"UL Target RSSI"     【 wlan.htc.he.a_control.umrs.ul_target_rssi  】
1641  :【 wlan.htc.he.a_control.umrs.ul_mcs 】    	{"UL MCS"     【 wlan.htc.he.a_control.umrs.ul_mcs  】
1642  :【 wlan.htc.he.a_control.umrs.reserved 】    	{"reserved"     【 wlan.htc.he.a_control.umrs.reserved  】
1643  :【 wlan.htc.he.a_control.om.rx_nss 】    	{"Rx NSS"     【 wlan.htc.he.a_control.om.rx_nss  】
1644  :【 wlan.htc.he.a_control.om.channel_width 】    	{"Channel Width"     【 wlan.htc.he.a_control.om.channel_width  】
1645  :【 wlan.htc.he.a_control.om.ul_mu_disable 】    	{"UL MU Disable"     【 wlan.htc.he.a_control.om.ul_mu_disable  】
1646  :【 wlan.htc.he.a_control.om.tx_nsts 】    	{"Tx NSTS"     【 wlan.htc.he.a_control.om.tx_nsts  】
1647  :【 wlan.htc.he.a_control.om.reserved 】    	{"Reserved"     【 wlan.htc.he.a_control.om.reserved  】
1648  :【 wlan.htc.he.a_control.hla.unsolicited_mfb 】    	{"Unsolicited MFB"     【 wlan.htc.he.a_control.hla.unsolicited_mfb  】
1649  :【 wlan.htc.he.a_control.hla.mrq 】    	{"MRQ"     【 wlan.htc.he.a_control.hla.mrq  】
1650  :【 wlan.htc.he.a_control.hla.NSS 】    	{"NSS"     【 wlan.htc.he.a_control.hla.NSS  】
1651  :【 wlan.htc.he.a_control.hla.he_mcs 】    	{"HE-MCS"     【 wlan.htc.he.a_control.hla.he_mcs  】
1652  :【 wlan.htc.he.a_control.hla.dcm 】    	{"DCM"     【 wlan.htc.he.a_control.hla.dcm  】
1653  :【 wlan.htc.he.a_control.hla.ru 】    	{"RU"     【 wlan.htc.he.a_control.hla.ru  】
1654  :【 wlan.htc.he.a_control.hla.bw 】    	{"BW"     【 wlan.htc.he.a_control.hla.bw  】
1655  :【 wlan.htc.he.a_control.hla.msi_ppdu_type 】    	{"MSI/PPDU Type"     【 wlan.htc.he.a_control.hla.msi_ppdu_type  】
1656  :【 wlan.htc.he.a_control.hla.tx_bf 】    	{"Tx BF"     【 wlan.htc.he.a_control.hla.tx_bf  】
1657  :【 wlan.htc.he.a_control.hla.reserved 】    	{"Reserved"     【 wlan.htc.he.a_control.hla.reserved  】
1658  :【 wlan.htc.he.a_control.bsr.aci_bitmap 】    	{"ACI Bitmap"     【 wlan.htc.he.a_control.bsr.aci_bitmap  】
1659  :【 wlan.htc.he.a_control.bsr.delta_tid 】    	{"Delta TID"     【 wlan.htc.he.a_control.bsr.delta_tid  】
1660  :【 wlan.htc.he.a_control.bsr.aci_high 】    	{"ACI High"     【 wlan.htc.he.a_control.bsr.aci_high  】
1661  :【 wlan.htc.he.a_control.bsr.scaling_factor 】    	{"Scaling Factor"     【 wlan.htc.he.a_control.bsr.scaling_factor  】
1662  :【 wlan.htc.he.a_control.bsr.queue_size_high 】    	{"Queue Size High"     【 wlan.htc.he.a_control.bsr.queue_size_high  】
1663  :【 wlan.htc.he.a_control.bsr.queue_size_all 】    	{"Queue Size All"     【 wlan.htc.he.a_control.bsr.queue_size_all  】
1664  :【 wlan.htc.he.a_control.uph.ul_power_headroom 】    	{"UL Power Headroom"     【 wlan.htc.he.a_control.uph.ul_power_headroom  】
1665  :【 wlan.htc.he.a_control.uph.min_transmit_power_flag 】    	{"Minimum Transmit Power Flag"     【 wlan.htc.he.a_control.uph.min_transmit_power_flag  】
1666  :【 wlan.htc.he.a_control.uph.reserved 】    	{"Reserved"     【 wlan.htc.he.a_control.uph.reserved  】
1667  :【 wlan.htc.he.a_control.bqr.avail_chan_bitmap 】    	{"Available Channel Bitmap"     【 wlan.htc.he.a_control.bqr.avail_chan_bitmap  】
1668  :【 wlan.htc.he.a_control.bqr.reserved 】    	{"Reserved"     【 wlan.htc.he.a_control.bqr.reserved  】
1669  :【 wlan.htc.he.a_control.cci.ac_constraint 】    	{"AC Constraint"     【 wlan.htc.he.a_control.cci.ac_constraint  】
1670  :【 wlan.htc.he.a_control.cci.rdg_more_ppdu 】    	{"RDG/More PPDU"     【 wlan.htc.he.a_control.cci.rdg_more_ppdu  】
1671  :【 wlan.htc.he.a_control.cci.sr_ppdu_indic 】    	{"SR PPDU Indication"     【 wlan.htc.he.a_control.cci.sr_ppdu_indic  】
1672  :【 wlan.htc.htc.a_control.cci.reserved 】    	{"Reserved"     【 wlan.htc.htc.a_control.cci.reserved  】
1673  :【 wlan.trigger.he.common_info 】    	{"HE Trigger Common Info"     【 wlan.trigger.he.common_info  】
1674  :【 wlan.trigger.he.trigger_type 】    	{"Trigger Type"     【 wlan.trigger.he.trigger_type  】
1675  :【 wlan.trigger.he.ul_length 】    	{"UL Length"     【 wlan.trigger.he.ul_length  】
1676  :【 wlan.trigger.he.more_tf 】    	{"More TF"     【 wlan.trigger.he.more_tf  】
1677  :【 wlan.trigger.he.cs_required 】    	{"CS Required"     【 wlan.trigger.he.cs_required  】
1678  :【 wlan.trigger.he.ul_bw 】    	{"UL BW"     【 wlan.trigger.he.ul_bw  】
1679  :【 wlan.trigger.he.gi_and_ltf_type 】    	{"GI And LTF Type"     【 wlan.trigger.he.gi_and_ltf_type  】
1680  :【 wlan.trigger.he.mu_mimo_ltf_mode 】    	{"MU-MIMO LTF Mode"     【 wlan.trigger.he.mu_mimo_ltf_mode  】
1681  :【wlan.trigger.he.num_he_ltf_syms_and_midamble_per 】    	"wlan.trigger.he.num_he_ltf_syms_and_midamble_per  】
1682  :【 wlan.trigger.he.ul_stbc 】    	{"UL STBC"     【 wlan.trigger.he.ul_stbc  】
1683  :【 wlan.trigger.he.ldpc_extra_symbol_segment 】    	{"LDPC Extra Symbol Segment"     【 wlan.trigger.he.ldpc_extra_symbol_segment  】
1684  :【 wlan.trigger.he.ap_tx_power 】    	{"AP TX Power"     【 wlan.trigger.he.ap_tx_power  】
1685  :【 wlan.trigger.he.packet_extension 】    	{"Packet Extension"     【 wlan.trigger.he.packet_extension  】
1686  :【 wlan.trigger.he.spatial_reuse 】    	{"Spatial Reuse"     【 wlan.trigger.he.spatial_reuse  】
1687  :【 wlan.trigger.he.ul_he_sig_a2_reserved 】    	{"UL HE-SIG-A2 Reserved"     【 wlan.trigger.he.ul_he_sig_a2_reserved  】
1688  :【 wlan.trigger.he.doppler 】    	{"Doppler"     【 wlan.trigger.he.doppler  】
1689  :【 wlan.trigger.he.reserved 】    	{"Reserved"     【 wlan.trigger.he.reserved  】
1690  :【 wlan.trigger.he.common_info.bar_ctrl 】    	{"BAR Control"     【 wlan.trigger.he.common_info.bar_ctrl  】
1691  :【 wlan.trigger.he.common_info.bar_ctrl.ba_ack_policy 】    	{"BA Ack Policy"     【 wlan.trigger.he.common_info.bar_ctrl.ba_ack_policy  】
1692  :【 wlan.trigger.he.common_info.bar_ctrl.ba_type 】    	{"BA Type"     【 wlan.trigger.he.common_info.bar_ctrl.ba_type  】
1693  :【 wlan.trigger.he.common_info.bar_ctrl.reserved 】    	{"Reserved"     【 wlan.trigger.he.common_info.bar_ctrl.reserved  】
1694  :【 wlan.trigger.he.common_info.bar_ctrl.tid_info 】    	{"TID_INFO"     【 wlan.trigger.he.common_info.bar_ctrl.tid_info  】
1695  :【 wlan.trigger.he.common_info.bar_info 】    	{"BAR Information"     【 wlan.trigger.he.common_info.bar_info  】
1696  :   	"wlan.trigger.he.common_info.bar_info.blk_ack_starting_seq_ctrl  】
1697  :【 wlan.trigger.he.user_info 】    	{"User Info"     【 wlan.trigger.he.user_info  】
1698  :【 wlan.trigger.he.mpdu_mu_spacing_factor 】    	{"MPDU MU Spacing Factor"     【 wlan.trigger.he.mpdu_mu_spacing_factor  】
1699  :【 wlan.trigger.he.tid_aggregation_limit 】    	{"TID Aggregation Limit"     【 wlan.trigger.he.tid_aggregation_limit  】
1700  :【 wlan.trigger.he.reserved1 】    	{"Reserved"     【 wlan.trigger.he.reserved1  】
1701  :【 wlan.trigger.he.preferred_ac 】    	{"Preferred AC"     【 wlan.trigger.he.preferred_ac  】
1702  :【 wlan.trigger.he.basic_user_info 】    	{"Basic Trigger Dependent User Info"     【 wlan.trigger.he.basic_user_info  】
1703  :【 wlan.trigger.he.starting_aid 】    	{"Starting AID"     【 wlan.trigger.he.starting_aid  】
1704  :【 wlan.trigger.he.reserved2 】    	{"Reserved"     【 wlan.trigger.he.reserved2  】
1705  :【 wlan.trigger.he.feedback_type 】    	{"Feedback Type"     【 wlan.trigger.he.feedback_type  】
1706  :【 wlan.trigger.he.reserved3 】    	{"Reserved"     【 wlan.trigger.he.reserved3  】
1707  :【 wlan.trigger.he.target_rssi 】    	{"Target RSSI"     【 wlan.trigger.he.target_rssi  】
1708  :【 wlan.trigger.he.multiplexing_flag 】    	{"Multiplexing Flag"     【 wlan.trigger.he.multiplexing_flag  】
1709  :【 wlan.trigger.he.nfrp_user_info 】    	{"NFRP Trigger Dependent User Unfo"     【 wlan.trigger.he.nfrp_user_info  】
1710  :【 wlan.trigger.he.feedback_bm 】    	{"Feedback Segment Retransmission Bitmap"     【 wlan.trigger.he.feedback_bm  】
1711  :【 wlan.trigger.he.user_info.aid12 】    	{"AID12"     【 wlan.trigger.he.user_info.aid12  】
1712  :【 wlan.trigger.he.ru_allocation_region 】    	{"RU Allocation Region"     【 wlan.trigger.he.ru_allocation_region  】
1713  :【 wlan.trigger.he.ru_allocation 】    	{"RU Allocation"     【 wlan.trigger.he.ru_allocation  】
1714  :【 wlan.trigger.he.coding_type 】    	{"Coding Type"     【 wlan.trigger.he.coding_type  】
1715  :【 wlan.trigger.he.mcs 】    	{"MCS"     【 wlan.trigger.he.mcs  】
1716  :【 wlan.trigger.he.dcm 】    	{"DCM"     【 wlan.trigger.he.dcm  】
1717  :【 wlan.trigger.he.ru_starting_spatial_stream 】    	{"Starting Spatial Stream"     【 wlan.trigger.he.ru_starting_spatial_stream  】
1718  :【 wlan.trigger.he.ru_number_of_spatial_stream 】    	{"Number Of Spatial Streams"     【 wlan.trigger.he.ru_number_of_spatial_stream  】
1719  :【 wlan.trigger.he.ru_number_of_ra_ru 】    	{"Number of RA-RU"     【 wlan.trigger.he.ru_number_of_ra_ru  】
1720  :【 wlan.trigger.he.ru_no_more_ra_ru 】    	{"No More RA-RU"     【 wlan.trigger.he.ru_no_more_ra_ru  】
1721  :【 wlan.trigger.he.target_rssi 】    	{"Target RSSI"     【 wlan.trigger.he.target_rssi  】
1722  :【 wlan.trigger.he.user_reserved 】    	{"Reserved"     【 wlan.trigger.he.user_reserved  】
1723  :【 wlan.ext_tag.quiet_time_period.control 】    	{"Control"     【 wlan.ext_tag.quiet_time_period.control  】
1724  :【 wlan.ext_tag.quiet_time_period.setup.duration 】    	{"Quiet Period Duration"     【 wlan.ext_tag.quiet_time_period.setup.duration  】
1725  :【 wlan.ext_tag.quiet_time_period.setup.srv_specific_identif 】    	{"Service Specific Identifier"     【 wlan.ext_tag.quiet_time_period.setup.srv_specific_identif  】
1726  :【 wlan.ext_tag.quiet_time_period.request.dialog_token 】    	{"Dialog Token"     【 wlan.ext_tag.quiet_time_period.request.dialog_token  】
1727  :【 wlan.ext_tag.quiet_time_period.request.offset 】    	{"Quiet Period Offset"     【 wlan.ext_tag.quiet_time_period.request.offset  】
1728  :【 wlan.ext_tag.quiet_time_period.request.duration 】    	{"Quiet Period Duration"     【 wlan.ext_tag.quiet_time_period.request.duration  】
1729  :【 wlan.ext_tag.quiet_time_period.request.interval 】    	{"Quiet Period Interval"     【 wlan.ext_tag.quiet_time_period.request.interval  】
1730  :【 wlan.ext_tag.quiet_time_period.request.repetition_count 】    	{"Repetition Count"     【 wlan.ext_tag.quiet_time_period.request.repetition_count  】
1731  :【 wlan.ext_tag.quiet_time_period.request.srv_specific_identif 】   	{"Service Specific Identifier"     【 wlan.ext_tag.quiet_time_period.request.srv_specific_identif  】
1732  :【 wlan.ext_tag.quiet_time_period.response.dialog_token 】    	{"Dialog Token"     【 wlan.ext_tag.quiet_time_period.response.dialog_token  】
1733  :【 wlan.ext_tag.quiet_time_period.response.status_code 】    	{"Status Code"     【 wlan.ext_tag.quiet_time_period.response.status_code  】
1734  :【 wlan.ext_tag.quiet_time_period.response.offset 】    	{"Quiet Period Offset"     【 wlan.ext_tag.quiet_time_period.response.offset  】
1735  :【 wlan.ext_tag.quiet_time_period.response.duration 】    	{"Quiet Period Duration"     【 wlan.ext_tag.quiet_time_period.response.duration  】
1736  :【 wlan.ext_tag.quiet_time_period.response.interval 】    	{"Quiet Period Interval"     【 wlan.ext_tag.quiet_time_period.response.interval  】
1737  :【 wlan.ext_tag.quiet_time_period.response.repetition_count 】    	{"Repetition Count"     【 wlan.ext_tag.quiet_time_period.response.repetition_count  】
1738  :【 wlan.ext_tag.quiet_time_period.response.srv_specific_identif 】   	{"Service Specific Identifier"     【 wlan.ext_tag.quiet_time_period.response.srv_specific_identif  】
1739  :【 wlan.he_ndp.token.number 】    	{"Sounding Dialog Token Number"     【 wlan.he_ndp.token.number  】
1740  :【 wlan.vht_he.token.he 】    	{"HE"     【 wlan.vht_he.token.he  】
1741  :【 wlan.he_ndp.token.reserved 】    	{"Reserved"     【 wlan.he_ndp.token.reserved  】
1742  :【 wlan.he_ndp.token 】    	{"Sounding Dialog Token"     【 wlan.he_ndp.token  】
1743  :【 wlan.he_ndp.sta_info 】    	{"STA Info"     【 wlan.he_ndp.sta_info  】
1744  :【 wlan.he_ndp.sta_info.aid11 】    	{"AID11"     【 wlan.he_ndp.sta_info.aid11  】
1745  :【 wlan.he_ndp.sta_info.ru_start 】    	{"RU Start Index"     【 wlan.he_ndp.sta_info.ru_start  】
1746  :【 wlan.he_ndp.sta_info.ru_end 】    	{"RU End Index"     【 wlan.he_ndp.sta_info.ru_end  】
1747  :【 wlan.he_ndp.sta_info.feedback_type_and_ng 】    	{"Feedback Type and Ng"     【 wlan.he_ndp.sta_info.feedback_type_and_ng  】
1748  :【 wlan.he_ndp.sta_info.disambiguation 】    	{"Disambiguation"     【 wlan.he_ndp.sta_info.disambiguation  】
1749  :【 wlan.he_ndp.sta_info.codebook_size 】    	{"Codebook Size"     【 wlan.he_ndp.sta_info.codebook_size  】
1750  :【 wlan.he_ndp.sta_info.nc 】    	{"Nc"     【 wlan.he_ndp.sta_info.nc  】
1751  :【 wlan.htc.lac 】    	{"Link Adaptation Control (LAC)"     【 wlan.htc.lac  】
1752  :【 wlan.htc.lac.trq 】    	{"Training Request (TRQ)"     【 wlan.htc.lac.trq  】
1753  :【 wlan.htc.lac.mai.aseli 】    	{"Antenna Selection Indication (ASELI)"     【 wlan.htc.lac.mai.aseli  】
1754  :【 wlan.htc.lac.mai.mrq 】    	{"MCS Request (MRQ)"     【 wlan.htc.lac.mai.mrq  】
1755  :【 wlan.htc.lac.mai.msi 】    	{"MCS Request Sequence Identifier (MSI)"     【 wlan.htc.lac.mai.msi  】
1756  :【 wlan.htc.lac.mai.reserved 】    	{"Reserved"     【 wlan.htc.lac.mai.reserved  】
1757  :【 wlan.htc.lac.mfsi 】    	{"MCS Feedback Sequence Identifier (MFSI)"     【 wlan.htc.lac.mfsi  】
1758  :【 wlan.htc.lac.asel.command 】    	{"Antenna Selection (ASEL) Command"     【 wlan.htc.lac.asel.command  】
1759  :【 wlan.htc.lac.asel.data 】    	{"Antenna Selection (ASEL) Data"     【 wlan.htc.lac.asel.data  】
1760  :【 wlan.htc.lac.mfb 】    	{"MCS Feedback (MFB)"     【 wlan.htc.lac.mfb  】
1761  :【 wlan.htc.cal.pos 】    	{"Calibration Position"     【 wlan.htc.cal.pos  】
1762  :【 wlan.htc.cal.seq 】    	{"Calibration Sequence Identifier"     【 wlan.htc.cal.seq  】
1763  :【 wlan.htc.reserved1 】    	{"Reserved"     【 wlan.htc.reserved1  】
1764  :【 wlan.htc.csi_steering 】    	{"CSI/Steering"     【 wlan.htc.csi_steering  】
1765  :【 wlan.htc.ndp_announcement 】    	{"NDP Announcement"     【 wlan.htc.ndp_announcement  】
1766  :【 wlan.htc.reserved2 】    	{"Reserved"     【 wlan.htc.reserved2  】
1767  :【 wlan.htc.mrq 】    	{"MRQ"     【 wlan.htc.mrq  】
1768  :【 wlan.htc.msi 】    	{"MSI"     【 wlan.htc.msi  】
1769  :【 wlan.htc.msi_stbc_reserved 】    	{"Reserved"     【 wlan.htc.msi_stbc_reserved  】
1770  :【 wlan.htc.compressed_msi 】    	{"Compressed MSI"     【 wlan.htc.compressed_msi  】
1771  :【 wlan.htc.ppdu_stbc_encoded 】    	{"PPDU was STBC encoded"     【 wlan.htc.ppdu_stbc_encoded  】
1772  :【 wlan.htc.mfsi 】    	{"MFSI"     【 wlan.htc.mfsi  】
1773  :【 wlan.htc.gid_l 】    	{"GID-L"     【 wlan.htc.gid_l  】
1774  :【 wlan.htc.mfb 】    	{"MFB"     【 wlan.htc.mfb  】
1775  :【 wlan.htc.num_sts 】    	{"NUM_STS"     【 wlan.htc.num_sts  】
1776  :【 wlan.htc.vht_mcs 】    	{"VHT-MCS"     【 wlan.htc.vht_mcs  】
1777  :【 wlan.htc.bw 】    	{"BW"     【 wlan.htc.bw  】
1778  :【 wlan.htc.snr 】    	{"SNR"     【 wlan.htc.snr  】
1779  :【 wlan.htc.reserved3 】    	{"Reserved"     【 wlan.htc.reserved3  】
1780  :【 wlan.htc.gid_h 】    	{"GID-H"     【 wlan.htc.gid_h  】
1781  :【 wlan.htc.coding_type 】    	{"Coding type"     【 wlan.htc.coding_type  】
1782  :【 wlan.htc.fb_tx_type 】    	{"FB Tx type"     【 wlan.htc.fb_tx_type  】
1783  :【 wlan.htc.unsolicited_mfb 】    	{"Unsolicited MFB"     【 wlan.htc.unsolicited_mfb  】
1784  :【 wlan.htc.ac_constraint 】    	{"AC Constraint"     【 wlan.htc.ac_constraint  】
1785  :【 wlan.htc.rdg_more_ppdu 】    	{"RDG/More PPDU"     【 wlan.htc.rdg_more_ppdu  】
1786  :【 wlan.mobility_domain.mdid 】    	{"Mobility Domain Identifier"     【 wlan.mobility_domain.mdid  】
1787  :【 wlan.mobility_domain.ft_capab 】    	{"FT Capability and Policy"     【 wlan.mobility_domain.ft_capab  】
1788  :【wlan.mobility_domain.ft_capab.ft_over_ds 】    	"wlan.mobility_domain.ft_capab.ft_over_ds  】
1789  :【wlan.mobility_domain.ft_capab.resource_req 】    	"wlan.mobility_domain.ft_capab.resource_req  】
1790  :【 wlan.ft.mic_control 】    	{"MIC Control"     【 wlan.ft.mic_control  】
1791  :【 wlan.ft.element_count 】    	{"Element Count"     【 wlan.ft.element_count  】
1792  :【 wlan.ft.mic 】    	{"MIC"     【 wlan.ft.mic  】
1793  :【 wlan.ft.anonce 】    	{"ANonce"     【 wlan.ft.anonce  】
1794  :【 wlan.ft.snonce 】    	{"SNonce"     【 wlan.ft.snonce  】
1795  :【 wlan.ft.subelem.id 】    	{"Subelement ID"     【 wlan.ft.subelem.id  】
1796  :【 wlan.ft.subelem.len 】    	{"Length"     【 wlan.ft.subelem.len  】
1797  :【 wlan.ft.subelem.data 】    	{"Data"     【 wlan.ft.subelem.data  】
1798  :【 wlan.ft.subelem.r1kh_id 】    	{"PMK-R1 key holder identifier (R1KH-ID)"     【 wlan.ft.subelem.r1kh_id  】
1799  :【 wlan.ft.subelem.gtk.key_info 】    	{"Key Info"     【 wlan.ft.subelem.gtk.key_info  】
1800  :【 wlan.ft.subelem.gtk.key_id 】    	{"Key ID"     【 wlan.ft.subelem.gtk.key_id  】
1801  :【 wlan.ft.subelem.gtk.key_length 】    	{"Key Length"     【 wlan.ft.subelem.gtk.key_length  】
1802  :【 wlan.ft.subelem.gtk.rsc 】    	{"RSC"     【 wlan.ft.subelem.gtk.rsc  】
1803  :【 wlan.ft.subelem.gtk.key 】    	{"GTK"     【 wlan.ft.subelem.gtk.key  】
1804  :【 wlan.ft.subelem.r0kh_id 】    	{"PMK-R0 key holder identifier (R0KH-ID)"     【 wlan.ft.subelem.r0kh_id  】
1805  :【 wlan.ft.subelem.igtk.key_id 】    	{"Key ID"     【 wlan.ft.subelem.igtk.key_id  】
1806  :【 wlan.ft.subelem.igtk.ipn 】    	{"IPN"     【 wlan.ft.subelem.igtk.ipn  】
1807  :【 wlan.ft.subelem.igtk.key_length 】    	{"Key Length"     【 wlan.ft.subelem.igtk.key_length  】
1808  :【 wlan.ft.subelem.igtk.key 】    	{"Wrapped Key (IGTK)"     【 wlan.ft.subelem.igtk.key  】
1809  :【 wlan.ric_data.id 】    	{"Resource Handshake Identifier"     【 wlan.ric_data.id  】
1810  :【 wlan.ric_data.desc_cnt 】    	{"Resource Descriptor Count"     【 wlan.ric_data.desc_cnt  】
1811  :【 wlan.ric_data.status_code 】    	{"Status Code"     【 wlan.ric_data.status_code  】
1812  :【 wlan.obss.spd 】    	{"Scan Passive Dwell"     【 wlan.obss.spd  】
1813  :【 wlan.obss.sad 】    	{"Scan Active Dwell"     【 wlan.obss.sad  】
1814  :【 wlan.obss.cwtsi 】    	{"Channel Width Trigger Scan Interval"     【 wlan.obss.cwtsi  】
1815  :【 wlan.obss.sptpc 】    	{"Scan Passive Total Per Channel"     【 wlan.obss.sptpc  】
1816  :【 wlan.obss.satpc 】    	{"Scan Active Total Per Channel"     【 wlan.obss.satpc  】
1817  :【 wlan.obss.wctdf 】    	{"Width Channel Transition Delay Factor"     【 wlan.obss.wctdf  】
1818  :【 wlan.obss.sat 】    	{"Scan Activity Threshold"     【 wlan.obss.sat  】
1819  :【 wlan.osen.gdcs.oui 】    	{"Group Data Cypher Suite OUI"     【 wlan.osen.gdcs.oui  】
1820  :【 wlan.osen.gdcs.type 】    	{"Group Data Cypher Suite type"     【 wlan.osen.gdcs.type  】
1821  :【 wlan.osen.pwcs.count 】    	{"OSEN Pairwise Cipher Suite Count"     【 wlan.osen.pwcs.count  】
1822  :【 wlan.osen.pwcs.oui 】    	{"OSEN Pairwise Cypher Suite OUI"     【 wlan.osen.pwcs.oui  】
1823  :【 wlan.osen.pwcs.type 】    	{"OSEN Pairwise Cypher Suite type"     【 wlan.osen.pwcs.type  】
1824  :【 wlan.osen.akms.count 】    	{"OSEN AKM Cipher Suite Count"     【 wlan.osen.akms.count  】
1825  :【 wlan.osen.akms.oui 】    	{"OSEN AKM Cipher Suite OUI"     【 wlan.osen.akms.oui  】
1826  :【 wlan.osen.akms.type 】    	{"OSEN AKM Cipher Suite Type"     【 wlan.osen.akms.type  】
1827  :【 wlan.osen.rsn.capabilities.preauth 】    	{"RSN Pre-Auth capabilities"     【 wlan.osen.rsn.capabilities.preauth  】
1828  :【 wlan.osen.rsn.capabilities.no_pairwise 】    	{"RSN No Pairwise capabilities"     【 wlan.osen.rsn.capabilities.no_pairwise  】
1829  :【an.osen.rsn.capabilities.ptksa_replay_counter 】    	{"【 wlan.osen.rsn.capabilities.ptksa_replay_counter  】
1830  :【an.osen.rsn.capabilities.gtksa_replay_counter 】    	{"【 wlan.osen.rsn.capabilities.gtksa_replay_counter  】
1831  :【 wlan.osen.gmcs.oui 】    	{"OSEN Group Management Cipher Suite OUI"     【 wlan.osen.gmcs.oui  】
1832  :【 wlan.osen.gmcs.type 】    	{"OSEN Group Management Cipher Suite Type"     【 wlan.osen.gmcs.type  】
1833  :【 wlan.osen.rsn.capabilities.mfpr 】    	{"Management Frame Protection Required"     【 wlan.osen.rsn.capabilities.mfpr  】
1834  :【 wlan.osen.rsn.capabilities.mfpc 】    	{"Management Frame Protection Capable"     【 wlan.osen.rsn.capabilities.mfpc  】
1835  :【 wlan.osen.rsn.capabilities.jmr 】    	{"Joint Multi-band RSNA"     【 wlan.osen.rsn.capabilities.jmr  】
1836  :【 wlan.osen.rsn.capabilities.peerkey 】    	{"PeerKey Enabled"     【 wlan.osen.rsn.capabilities.peerkey  】
1837  :【 wlan.osen.rsn.cabailities.flags 】    	{"RSN Capability Flags"     【 wlan.osen.rsn.cabailities.flags  】
1838  :【 wlan.osen.rsn.capabilities.spp_a_msdu_cap 】    	{"SPP A-MSDU Capable"     【 wlan.osen.rsn.capabilities.spp_a_msdu_cap  】
1839  :【 wlan.osen.rsn.capabilities.spp_a_msdu_req 】    	{"SPP A-MSDU Required"     【 wlan.osen.rsn.capabilities.spp_a_msdu_req  】
1840  :【 wlan.osen.rsn.capabilities.pbac 】    	{"Protected Block Ack Agreement Capable"     【 wlan.osen.rsn.capabilities.pbac  】
1841  :【 wlan.osn.rsn.extended_key_id_iaf 】    	{"   【wlan.osn.rsn.extended_key_id_iaf  】
1842  :【 wlan.osen.rsn.capabilities.reserved 】    	{"Reserved"     【 wlan.osen.rsn.capabilities.reserved  】
1843  :【 wlan.osen.pmkid.count 】    	{"OSEN PMKID Count"     【 wlan.osen.pmkid.count  】
1844  :【 wlan.osen.pmkid.bytes 】    	{"OSEN PKMID"     【 wlan.osen.pmkid.bytes  】
1845  :【 wlan.ric_desc.rsrc_type 】    	{"Resource Type"     【 wlan.ric_desc.rsrc_type  】
1846  :【 wlan.ric_desc.var_params 】    	{"Variable Params"     【 wlan.ric_desc.var_params  】
1847  :【 wlan.mmie.keyid 】    	{"KeyID"     【 wlan.mmie.keyid  】
1848  :【 wlan.mmie.ipn 】    	{"IPN"     【 wlan.mmie.ipn  】
1849  :【 wlan.mmie.mic 】    	{"MIC"     【 wlan.mmie.mic  】
1850  :【 wlan.wapi.version 】    	{"Version"     【 wlan.wapi.version  】
1851  :【 wlan.wapi.akm_suite.count 】    	{"AKM Suite Count"     【 wlan.wapi.akm_suite.count  】
1852  :【 wlan.wapi.akm_suite.oui 】    	{"AKM Suite OUI"     【 wlan.wapi.akm_suite.oui  】
1853  :【 wlan.wapi.akm_suite.type 】    	{"AKM Suite Type"     【 wlan.wapi.akm_suite.type  】
1854  :【 wlan.wapi.unicast_cipher.suite.count 】    	{"Unicast Cipher Suite Count"     【 wlan.wapi.unicast_cipher.suite.count  】
1855  :【 wlan.wapi.unicast_cipher.suite.oui 】    	{"Unicast Cipher Suite OUI"     【 wlan.wapi.unicast_cipher.suite.oui  】
1856  :【 wlan.wapi.unicast_cipher.suite.type 】    	{"Unicast Cipher Suite Type"     【 wlan.wapi.unicast_cipher.suite.type  】
1857  :【 wlan.wapi.multicast_cipher.suite.oui 】    	{"Multicast Cipher Suite OUI"     【 wlan.wapi.multicast_cipher.suite.oui  】
1858  :【 wlan.wapi.multicast_cipher.suite.type 】    	{"Multicast Cipher Suite Type"     【 wlan.wapi.multicast_cipher.suite.type  】
1859  :【 wlan.wapi.capab 】    	{"WAPI Capability Info"     【 wlan.wapi.capab  】
1860  :【 wlan.wapi.capab.preauth 】    	{"Supports Preauthentication?"     【 wlan.wapi.capab.preauth  】
1861  :【 wlan.wapi.capab.rsvd 】    	{"Reserved"     【 wlan.wapi.capab.rsvd  】
1862  :【 wlan.wapi.bkid.count 】    	{"No of BKID's"     【 wlan.wapi.bkid.count  】
1863  :【 wlan.wapi.bkid 】    	{"BKID"     【 wlan.wapi.bkid  】
1864  :【 wlan.bss_max_idle.period 】    	{"BSS Max Idle Period (1000 TUs)"     【 wlan.bss_max_idle.period  】
1865  :【wlan.bss_max_idle.options.protected 】    	"wlan.bss_max_idle.options.protected  】
1866  :【 wlan.tfs_request.id 】    	{"TFS ID"     【 wlan.tfs_request.id  】
1867  :【wlan.tfs_request.action_code.delete_after_match 】    	"wlan.tfs_request.action_code.delete_after_match  】
1868  :【wlan.tfs_request.action_code.notify 】    	"wlan.tfs_request.action_code.notify  】
1869  :【 wlan.tfs_request.subelem.id 】    	{"Subelement ID"     【 wlan.tfs_request.subelem.id  】
1870  :【 wlan.tfs_request.subelem.len 】    	{"Length"     【 wlan.tfs_request.subelem.len  】
1871  :【 wlan.tfs_request.subelem 】    	{"Subelement Data"     【 wlan.tfs_request.subelem  】
1872  :【 wlan.tfs_response.subelem.id 】    	{"Subelement ID"     【 wlan.tfs_response.subelem.id  】
1873  :【 wlan.tfs_response.subelem.len 】    	{"Length"     【 wlan.tfs_response.subelem.len  】
1874  :【 wlan.tfs_response.subelem 】    	{"Subelement Data"     【 wlan.tfs_response.subelem  】
1875  :【 wlan.tfs_response.status 】    	{"TFS Response Status"     【 wlan.tfs_response.status  】
1876  :【 wlan.tfs_response.tfs_id 】    	{"TFS ID"     【 wlan.tfs_response.tfs_id  】
1877  :【 wlan.wnm_sleep_mode.action_type 】    	{"Action Type"     【 wlan.wnm_sleep_mode.action_type  】  
1878  :【wlan.wnm_sleep_mode.response_status 】    	{【wlan.wnm_sleep_mode.response_status  】
1879  :【 wlan.wnm_sleep_mode.interval 】    	{"WNM-Sleep Interval"     【 wlan.wnm_sleep_mode.interval  】
1880  :【 wlan.wnm_subelt.id 】    	{"Subelement ID"     【 wlan.wnm_subelt.id  】
1881  :【 wlan.wnm_subelt.len 】    	{"Subelement len"     【 wlan.wnm_subelt.len  】
1882  :【 wlan.time_adv.timing_capab 】    	{"Timing capabilities"     【 wlan.time_adv.timing_capab  】
1883  :【 wlan.time_adv.time_value 】    	{"Time Value"     【 wlan.time_adv.time_value  】
1884  :【 wlan.time_adv.time_value.year 】    	{"Time Value: Year"     【 wlan.time_adv.time_value.year  】
1885  :【 wlan.time_adv.time_value.month 】    	{"Time Value: Month"     【 wlan.time_adv.time_value.month  】
1886  :【 wlan.time_adv.time_value.month 】    	{"Time Value: Day"     【 wlan.time_adv.time_value.month  】
1887  :【 wlan.time_adv.time_value.hours 】    	{"Time Value: Hours"     【 wlan.time_adv.time_value.hours  】
1888  :【 wlan.time_adv.time_value.minutes 】    	{"Time Value: Minutes"     【 wlan.time_adv.time_value.minutes  】
1889  :【 wlan.time_adv.time_value.seconds 】    	{"Time Value: Seconds"     【 wlan.time_adv.time_value.seconds  】
1890  :【 wlan.time_adv.time_value.milliseconds 】    	{"Time Value: Milliseconds"     【 wlan.time_adv.time_value.milliseconds  】
1891  :【 wlan.time_adv.time_value.reserved 】    	{"Time Value: Reserved"     【 wlan.time_adv.time_value.reserved  】
1892  :【 wlan.time_adv.time_error 】    	{"Time Error"     【 wlan.time_adv.time_error  】
1893  :【 wlan.time_adv.time_update_counter 】    	{"Time Update Counter"     【 wlan.time_adv.time_update_counter  】
1894  :【 wlan.time_zone 】    	{"Time Zone"     【 wlan.time_zone  】
1895  :【 wlan.interworking.access_network_type 】    	{"Access Network Type"     【 wlan.interworking.access_network_type  】
1896  :【 wlan.interworking.internet 】    	{"Internet"     【 wlan.interworking.internet  】
1897  :【 wlan.interworking.asra 】    	{"ASRA"     【 wlan.interworking.asra  】
1898  :【 wlan.interworking.esr 】    	{"ESR"     【 wlan.interworking.esr  】
1899  :【 wlan.interworking.uesa 】    	{"UESA"     【 wlan.interworking.uesa  】
1900  :【 wlan.interworking.hessid 】    	{"HESSID"     【 wlan.interworking.hessid  】
1901  :【 wlan.qos_map_set.dscp_exception 】    	{"DSCP Exception"     【 wlan.qos_map_set.dscp_exception  】
1902  :【 wlan.qos_map_set.dscp_value 】    	{"DSCP Value"     【 wlan.qos_map_set.dscp_value  】
1903  :【 wlan.qos_map_set.up 】    	{"User Priority"     【 wlan.qos_map_set.up  】
1904  :【 wlan.qos_map_set.range 】    	{"DSCP Range description"     【 wlan.qos_map_set.range  】
1905  :【 wlan.qos_map_set.dscp_low_value 】    	{"DSCP Low Value"     【 wlan.qos_map_set.dscp_low_value  】
1906  :【 wlan.qos_map_set.dscp_high_value 】    	{"DSCP High Value"     【 wlan.qos_map_set.dscp_high_value  】
1907  :【 wlan.adv_proto.resp_len_limit 】    	{"Query Response Length Limit"     【 wlan.adv_proto.resp_len_limit  】
1908  :【 wlan.adv_proto.pame_bi 】    	{"PAME-BI"     【 wlan.adv_proto.pame_bi  】
1909  :【 wlan.adv_proto.id 】    	{"Advertisement Protocol ID"     【 wlan.adv_proto.id  】
1910  :【 wlan.adv_proto.vs_len 】    	{"Advertisement Protocol Vendor Specific length"     【 wlan.adv_proto.vs_len  】
1911  :【 wlan.adv_proto.vs_info 】    	{"Advertisement Protocol Vendor Specific info"     【 wlan.adv_proto.vs_info  】
1912  :【 wlan.roaming_consortium.num_anqp_oi 】    	{"Number of ANQP OIs"     【 wlan.roaming_consortium.num_anqp_oi  】
1913  :【 wlan.roaming_consortium.oi1_len 】    	{"OI #1 Length"     【 wlan.roaming_consortium.oi1_len  】
1914  :【 wlan.roaming_consortium.oi2_len 】    	{"OI #2 Length"     【 wlan.roaming_consortium.oi2_len  】
1915  :【 wlan.roaming_consortium.oi1 】    	{"OI #1"     【 wlan.roaming_consortium.oi1  】
1916  :【 wlan.roaming_consortium.oi2 】    	{"OI #2"     【 wlan.roaming_consortium.oi2  】
1917  :【 wlan.roaming_consortium.oi3 】    	{"OI #3"     【 wlan.roaming_consortium.oi3  】
1918  :【 wlan.timeout_int.type 】    	{"Timeout Interval Type"     【 wlan.timeout_int.type  】
1919  :【 wlan.timeout_int.value 】    	{"Timeout Interval Value"     【 wlan.timeout_int.value  】
1920  :【 wlan.link_id.bssid 】    	{"BSSID"     【 wlan.link_id.bssid  】
1921  :【 wlan.link_id.init_sta 】    	{"TDLS initiator STA Address"     【 wlan.link_id.init_sta  】
1922  :【 wlan.link_id.resp_sta 】    	{"TDLS responder STA Address"     【 wlan.link_id.resp_sta  】
1923  :【 wlan.wakeup_schedule.offset 】    	{"Offset"     【 wlan.wakeup_schedule.offset  】
1924  :【 wlan.wakeup_schedule.interval 】    	{"Interval"     【 wlan.wakeup_schedule.interval  】
1925  :【 wlan.wakeup_schedule.awake_window_slots 】    	{"Awake Window Slots"     【 wlan.wakeup_schedule.awake_window_slots  】
1926  :【 wlan.wakeup_schedule.max_awake_dur 】    	{"Maximum Awake Window Duration"     【 wlan.wakeup_schedule.max_awake_dur  】
1927  :【 wlan.wakeup_schedule.idle_count 】    	{"Idle Count"     【 wlan.wakeup_schedule.idle_count  】
1928  :【 wlan.channel_switch_timing.switch_time 】    	{"Switch Time"     【 wlan.channel_switch_timing.switch_time  】
1929  :【 wlan.channel_switch_timing.switch_timeout 】    	{"Switch Timeout"     【 wlan.channel_switch_timing.switch_timeout  】
1930  :【 wlan.pti_control.tid 】    	{"TID"     【 wlan.pti_control.tid  】
1931  :【 wlan.pti_control.sequence_control 】    	{"Sequence Control"     【 wlan.pti_control.sequence_control  】
1932  :【 wlan.pu_buffer_status.ac_bk 】    	{"AC_BK traffic available"     【 wlan.pu_buffer_status.ac_bk  】
1933  :【 wlan.pu_buffer_status.ac_be 】    	{"AC_BE traffic available"     【 wlan.pu_buffer_status.ac_be  】
1934  :【 wlan.pu_buffer_status.ac_vi 】    	{"AC_VI traffic available"     【 wlan.pu_buffer_status.ac_vi  】
1935  :【 wlan.pu_buffer_status.ac_vo 】    	{"AC_VO traffic available"     【 wlan.pu_buffer_status.ac_vo  】
1936  :【 wlan.mysterious_olpc_stuff 】    	{"Mysterious OLPC stuff"     【 wlan.mysterious_olpc_stuff  】
1937  :【 wlan.ext_tag.estimated_service_params 】    	{"Estimated Service Parameters"     【 wlan.ext_tag.estimated_service_params  】
1938  :【 wlan.ext_tag.estimated_service_params.access_category 】    	{"Access Category"     【 wlan.ext_tag.estimated_service_params.access_category  】
1939  :【 wlan.ext_tag.estimated_service_params.reserved 】    	{"Reserved"     【 wlan.ext_tag.estimated_service_params.reserved  】
1940  :【 wlan.ext_tag.estimated_service_params.data_format 】    	{"Data Format"     【 wlan.ext_tag.estimated_service_params.data_format  】
1941  :【 wlan.ext_tag.estimated_service_params.ba_window_size 】    	{"BA Window Size"     【 wlan.ext_tag.estimated_service_params.ba_window_size  】
1942  :【 wlan.ext_tag.estimated_service_params.air_time_frac 】    	{"Estimated Air Time Fraction"     【 wlan.ext_tag.estimated_service_params.air_time_frac  】
1943  :【 wlan.ext_tag.estimated_service_params.data_ppdu_dur_target 】    	{"Data PPDU Duration Target"     【 wlan.ext_tag.estimated_service_params.data_ppdu_dur_target  】
1944  :【 wlan.ext_tag.future_channel_guidance.new_chan_num 】    	{"New Channel Number"     【 wlan.ext_tag.future_channel_guidance.new_chan_num  】
1945  :【 wlan.ext_tag.future_channel_guidance.extra_bytes 】    	{"Extra bytes"     【 wlan.ext_tag.future_channel_guidance.extra_bytes  】
1946  :【 wlan.fils_indication.info.nr_pk 】    	{"Number of Public Key Identifiers"     【 wlan.fils_indication.info.nr_pk  】
1947  :【 wlan.fils_indication.info.nr_realm 】    	{"Number of Realm Identifiers"     【 wlan.fils_indication.info.nr_realm  】
1948  :【 wlan.fils_indication.info.ip_config 】    	{"FILS IP Address Configuration"     【 wlan.fils_indication.info.ip_config  】
1949  :【 wlan.fils_indication.info.cache_id_included 】    	{"Cache Identifier"     【 wlan.fils_indication.info.cache_id_included  】
1950  :【 wlan.fils_indication.info.hessid_included 】    	{"HESSID"     【 wlan.fils_indication.info.hessid_included  】
1951  :【 wlan.fils_indication.info.ska_without_pfs 】    	{"FILS Shared Key Authentication without PFS"     【 wlan.fils_indication.info.ska_without_pfs  】
1952  :【 wlan.fils_indication.info.ska_with_pfs 】    	{"FILS Shared Key Authentication with PFS"     【 wlan.fils_indication.info.ska_with_pfs  】
1953  :【 wlan.fils_indication.info.pka 】    	{"FILS Public Key Authentication"     【 wlan.fils_indication.info.pka  】
1954  :【 wlan.fils_indication.info.reserved 】    	{"Reserved"     【 wlan.fils_indication.info.reserved  】
1955  :【 wlan.fils_indication.cache_identifier 】    	{"Cache Identifier"     【 wlan.fils_indication.cache_identifier  】
1956  :【 wlan.fils_indication.hessid 】    	{"HESSID"     【 wlan.fils_indication.hessid  】
1957  :【 wlan.fils_indication.realms 】    	{"Realm Identifiers"     【 wlan.fils_indication.realms  】
1958  :【 wlan.fils_indication.realms.identifier 】    	{"Realm Identifier"     【 wlan.fils_indication.realms.identifier  】
1959  :【 wlan.fils_indication.public_keys 】    	{"Public Keys"     【 wlan.fils_indication.public_keys  】
1960  :【 wlan.fils_indication.public_keys.key_type 】    	{"Key Type"     【 wlan.fils_indication.public_keys.key_type  】
1961  :【 wlan.fils_indication.public_keys.length 】    	{"Length"     【 wlan.fils_indication.public_keys.length  】
1962  :【 wlan.fils_indication.public_keys.indicator 】    	{"Public Key Indicator"     【 wlan.fils_indication.public_keys.indicator  】
1963  :【 wlan.ext_tag 】    	{"Ext Tag"     【 wlan.ext_tag  】
1964  :【 wlan.ext_tag.number 】    	{"Ext Tag Number"     【 wlan.ext_tag.number  】
1965  :【 wlan.ext_tag.length 】    	{"Ext Tag length"     【 wlan.ext_tag.length  】
1966  :【 wlan.ext_tag.fils.session 】    	{"FILS Session"     【 wlan.ext_tag.fils.session  】
1967  :【 wlan.ext_tag.fils.encrypted_data 】    	{"FILS Encrypted Data"     【 wlan.ext_tag.fils.encrypted_data  】
1968  :【 wlan.ext_tag.fils.wrapped_data 】    	{"FILS Wrapped Data"     【 wlan.ext_tag.fils.wrapped_data  】
1969  :【 wlan.ext_tag.fils.nonce 】    	{"FILS Nonce"     【 wlan.ext_tag.fils.nonce  】
1970  :【 wlan.ext_tag.he_mac_caps 】    	{"HE MAC Capabilities Information"     【 wlan.ext_tag.he_mac_caps  】
1971  :【 wlan.ext_tag.he_mac_cap.htc_he_support 】    	{"+HTC HE Support"     【 wlan.ext_tag.he_mac_cap.htc_he_support  】
1972  :【 wlan.ext_tag.he_mac_cap.twt_req_support 】    	{"TWT Requester Support"     【 wlan.ext_tag.he_mac_cap.twt_req_support  】
1973  :【 wlan.ext_tag.he_mac_cap.twt_rsp_support 】    	{"TWT Responder Support"     【 wlan.ext_tag.he_mac_cap.twt_rsp_support  】
1974  :【 wlan.ext_tag.he_mac_cap.fragmentation_support 】    	{"Fragmentation Support"     【 wlan.ext_tag.he_mac_cap.fragmentation_support  】
1975  :【 wlan.ext_tag.he_mac_cap.max_frag_msdus 】    	{"Maximum Number of Fragmented MSDUs"     【 wlan.ext_tag.he_mac_cap.max_frag_msdus  】
1976  :【 wlan.ext_tag.he_mac_cap.min_frag_size 】    	{"Minimum Fragment Size"     【 wlan.ext_tag.he_mac_cap.min_frag_size  】
1977  :【wlan.ext_tag.he_mac_cap.trig_frm_mac_padding_dur 】    	{"Trigger Frame MAC Padding Duration"     【 wlan.ext_tag.he_mac_cap.trig_frm_mac_padding_dur  】
1978  :【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】    	{"Multi-TID Aggregation Support"     【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support  】
1979  :【 wlan.ext_tag.he_mac_cap.he_link_adaptation_support 】    	{"HE Link Adaptation Support"     【 wlan.ext_tag.he_mac_cap.he_link_adaptation_support  】
1980  :【 wlan.ext_tag.he_mac_cap.all_ack_support 】    	{"All Ack Support"     【 wlan.ext_tag.he_mac_cap.all_ack_support  】
1981  :【 wlan.ext_tag.he_mac_cap.Trs_support 】    	{"TRS Support"     【 wlan.ext_tag.he_mac_cap.Trs_support  】
1982  :【 wlan.ext_tag.he_mac_cap.bsr_support 】    	{"BSR Support"     【 wlan.ext_tag.he_mac_cap.bsr_support  】
1983  :【 wlan.ext_tag.he_mac_cap.broadcast_twt_support 】    	{"Broadcast TWT Support"     【 wlan.ext_tag.he_mac_cap.broadcast_twt_support  】
1984  :【 wlan.ext_tag.he_mac_cap.32_bit_ba_bitmap_support 】    	{"32-bit BA Bitmap Support"     【 wlan.ext_tag.he_mac_cap.32_bit_ba_bitmap_support  】
1985  :【 wlan.ext_tag.he_mac_cap.mu_cascading_support 】    	{"MU Cascading Support"     【 wlan.ext_tag.he_mac_cap.mu_cascading_support  】
1986  :【 wlan.ext_tag.he_mac_cap.ack_enabled_agg_support 】    	{"Ack-Enabled Aggregation Support"     【 wlan.ext_tag.he_mac_cap.ack_enabled_agg_support  】
1987  :【 wlan.ext_tag.he_mac_cap.reserved_b24 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_b24  】
1988  :【 wlan.ext_tag.he_mac_cap.om_control_support 】    	{"OM Control Support"     【 wlan.ext_tag.he_mac_cap.om_control_support  】
1989  :【 wlan.ext_tag.he_mac_cap.ofdma_ra_support"  】   	{"OFDMA RA Support"     【 wlan.ext_tag.he_mac_cap.ofdma_ra_support  】
1990  :【 wlan.ext_tag.he_mac_cap.max_a_mpdu_len_exp_ext  】                                 	{"   【 wlan.ext_tag.he_mac_cap.max_a_mpdu_len_exp_ext  】
1991  :【 wlan.ext_tag.he_mac_cap.a_msdu_frag_support 】    	{"A-MSDU Fragmentation Support"     【 wlan.ext_tag.he_mac_cap.a_msdu_frag_support  】
1992  :【 wlan.ext_tag.he_mac_cap.flexible_twt_sched_support 】    	{"Flexible TWT Schedule Support"     【 wlan.ext_tag.he_mac_cap.flexible_twt_sched_support  】
1993  :【 wlan.ext_tag.he_mac_cap.rx_ctl_frm_multibss 】    	{"Rx Control Frame to MultiBSS"     【 wlan.ext_tag.he_mac_cap.rx_ctl_frm_multibss  】
1994  :【 wlan.ext_tag.he_mac_cap.bsrp_bqrp_a_mpdu_agg 】    	{"BSRP BQRP A-MPDU Aggregation"     【 wlan.ext_tag.he_mac_cap.bsrp_bqrp_a_mpdu_agg  】
1995  :【 wlan.ext_tag.he_mac_cap.qtp_support 】    	{"QTP Support"     【 wlan.ext_tag.he_mac_cap.qtp_support  】
1996  :【 wlan.ext_tag.he_mac_cap.bqr_support 】    	{"BQR Support"     【 wlan.ext_tag.he_mac_cap.bqr_support  】
1997  :【 wlan.ext_tag.he_mac_cap.sr_responder 】    	{"SRP Responder Role"     【 wlan.ext_tag.he_mac_cap.sr_responder  】
1998  :【 wlan.ext_tag.he_mac_cap.ndp_feedback_report_support 】    	{"NDP Feedback Report Support"     【 wlan.ext_tag.he_mac_cap.ndp_feedback_report_support  】
1999  :【 wlan.ext_tag.he_mac_cap.ops_support 】    	{"OPS Support"     【 wlan.ext_tag.he_mac_cap.ops_support  】
2000  :【 wlan.ext_tag.he_mac_cap.a_msdu_in_a_mpdu_support 】    	{"A-MSDU in A-MPDU Support"     【 wlan.ext_tag.he_mac_cap.a_msdu_in_a_mpdu_support  】
2001  :【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】    	{"Multi-TID Aggregation TX Support"     【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support  】
2002  :【 wlan.ext_tag.he_mac_cap.subchannel_selective_xmit_support 】    	{"HE Subchannel Selective Transmission Support"     【 wlan.ext_tag.he_mac_cap.subchannel_selective_xmit_support  】
2003  :【 wlan.ext_tag.he_mac_cap.ul_2_996_tone_ru_support 】    	{"UL 2x996-tone RU Support"     【 wlan.ext_tag.he_mac_cap.ul_2_996_tone_ru_support  】
2004  :【wlan.ext_tag.he_mac_cap.om_cntl_ul_mu_data_disble_rx_support 】    	{"OM Control UL MU Data Disable RX Support"     【 wlan.ext_tag.he_mac_cap.om_cntl_ul_mu_data_disble_rx_support  】
2005  :【 wlan.ext_tag.he_mac_cap.reserved_bit_39 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bit_39  】
2006  :【 wlan.ext_tag.he_mac_cap.reserved_bits_5_7 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bits_5_7  】
2007  :【 wlan.ext_tag.he_mac_cap.reserved_bits_8_9 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bits_8_9  】
2008  :【 wlan.ext_tag.he_mac_cap.reserved_bits_15_16 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bits_15_16  】
2009  :【 wlan.ext_tag.he_mac_cap.reserved_bit_18 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bit_18  】
2010  :【 wlan.ext_tag.he_mac_cap.reserved_bit_19 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bit_19  】
2011  :【 wlan.ext_tag.he_mac_cap.reserved_bit_25 】    	{"Reserved"     【 wlan.ext_tag.he_mac_cap.reserved_bit_25  】
2012  :【 wlan.ext_tag.he_phy_cap.reserved_b0 】    	{"Reserved"     【 wlan.ext_tag.he_phy_cap.reserved_b0  】
2013  :【 wlan.ext_tag.he_phy_cap.fbyte.reserved_b0 】    	{"Reserved"     【 wlan.ext_tag.he_phy_cap.fbyte.reserved_b0  】
2014  :【 wlan.ext_tag.he_phy_cap.fbytes 】    	{"Channel Width Set"     【 wlan.ext_tag.he_phy_cap.fbytes  】
2015  :【 wlan.ext_tag.he_phy_cap.chan_width_set.40mhz_in_2_4ghz 】    	{"40MHz in 2.4GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.40mhz_in_2_4ghz  】
2016  :【 wlan.ext_tag.he_phy_cap.chan_width_set.40_80_in_5ghz 】    	{"40 & 80MHz in the 5GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.40_80_in_5ghz  】
2017  :【 wlan.ext_tag.he_phy_cap.chan_width_set.160_in_5ghz 】    	{"160MHz in the 5GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.160_in_5ghz  】
2018  :【 wlan.ext_tag.he_phy_cap.chan_width_set.160_80_80_in_5ghz 】    	{"160/80+80MHz in the 5GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.160_80_80_in_5ghz  】
2019  :【 wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_5ghz 】    	{"242 tone RUs in the 2.4GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_2_4ghz  】
2020  :【 wlan.ext_tag.he_phy_cap.chan_width_set.reserved 】    	{"242 tone RUs in the 5GHz band"     【 wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_5ghz  】
2021  :【 wlan.ext_tag.he_phy_cap.bits_8_to_23 】    	{"Reserved"     【 wlan.ext_tag.he_phy_cap.chan_width_set.reserved  】
2022  :【 wlan.ext_tag.he_phy_cap.nbytes.punc_preamble_rx 】    	{"Bits 8 to 23"     【 wlan.ext_tag.he_phy_cap.bits_8_to_23  】
2023  :【 wlan.ext_tag.he_phy_cap.nbytes.device_class 】    	{"Punctured Preamble RX"     【 wlan.ext_tag.he_phy_cap.nbytes.punc_preamble_rx  】
2024  :【 wlan.ext_tag.he_phy_cap.nbytes.ldpc_coding_in_payload 】    	{"Device Class"     【 wlan.ext_tag.he_phy_cap.nbytes.device_class  】
2025  :【wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_with_1x_he_ltf_08us 】    	{"LDPC Coding In Payload"     【 wlan.ext_tag.he_phy_cap.nbytes.ldpc_coding_in_payload  】
2026  :【 wlan.ext_tag.he_phy_cap.mbytes.midamble_rx_max_nsts 】    	"wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_with_1x_he_ltf_08us  】
2027  :【wlan.ext_tag.he_phy_cap.nbytes.ndp_with_4x_he_ltf_4x_3.2us 】    	{"Midamble Rx Max NSTS"     【 wlan.ext_tag.he_phy_cap.mbytes.midamble_rx_max_nsts  】
2028  :【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_lt_80mhz 】    	"wlan.ext_tag.he_phy_cap.nbytes.ndp_with_4x_he_ltf_4x_3.2us  】
2029  :【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_lt_80mhz 】    	{"STBC Tx <= 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_lt_80mhz  】
2030  :【 wlan.ext_tag.he_phy_cap.nbytes.doppler_tx 】    	{"STBC Rx <= 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_lt_80mhz  】
2031  :【 wlan.ext_tag.he_phy_cap.nbytes.doppler_rx 】    	{"Doppler Tx"     【 wlan.ext_tag.he_phy_cap.nbytes.doppler_tx  】
2032  :【 wlan.ext_tag.he_phy_cap.nbytes.full_bw_ul_mu_mimo 】    	{"Doppler Rx"     【 wlan.ext_tag.he_phy_cap.nbytes.doppler_rx  】
2033  :【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_ul_mu_mimo 】    	{"Full Bandwidth UL MU-MIMO"     【 wlan.ext_tag.he_phy_cap.nbytes.full_bw_ul_mu_mimo  】
2034  :【 wlan.ext_tag.he_phy_cap.bits_24_to_39 】    	{"Partial Bandwidth UL MU-MIMO"     【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_ul_mu_mimo  】
2035  :【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_tx 】    	{"Bits 24 to 39"     【 wlan.ext_tag.he_phy_cap.bits_24_to_39  】
2036  :【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】    	{"DCM Max Constellation Tx"     【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_tx  】
2037  :【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_rx 】    	{"DCM Max NSS Tx"     【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx  】
2038  :【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】    	{"DCM Max Constellation Rx"     【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_rx  】
2039  :【 wlan.ext_tag.he_phy_cap.nbytes.rx_he_mu_ppdu 】    	{"DCM Max NSS Rx"     【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx  】
2040  :【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformer 】    	{"Rx HE MU PPDU from Non-AP STA"     【 wlan.ext_tag.he_phy_cap.nbytes.rx_he_mu_ppdu  】
2041  :【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformee 】    	{"SU Beamformer"     【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformer  】
2042  :【 wlan.ext_tag.he_phy_cap.nbytes.mu_beamformer 】    	{"SU Beamformee"     【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformee  】
2043  :【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_lte_80mhz 】    	{"MU Beamformer"     【 wlan.ext_tag.he_phy_cap.nbytes.mu_beamformer  】
2044  :【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_gt_80mhz 】    	{"Beamformee STS <= 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_lte_80mhz  】
2045  :【 wlan.ext_tag.he_phy_cap.bits_40_to_55 】    	{"Beamformee STS > 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_gt_80mhz  】
2046  :【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_lt  】    	{"Bits 40 to 55"     【 wlan.ext_tag.he_phy_cap.bits_40_to_55  】
2047  :【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_gt_  】    	{"Number Of Sounding Dimensions <= 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_lte_80  】
2048  :【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_su_fb 】    	{"Number Of Sounding Dimensions > 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_gt_80  】
2049  :【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_mu_fb 】    	{"Ng = 16 SU Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_su_fb  】
2050  :【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_su_fb 】    	{"Ng = 16 MU Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_mu_fb  】
2051  :【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_mu_fb 】    	{"Codebook Size SU Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_su_fb  】
2052  :【 wlan.ext_tag.he_phy_cap.nbytes.trig_su_bf_fb 】    	{"Codebook Size MU Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_mu_fb  】
2053  :【 wlan.ext_tag.he_phy_cap.nbytes.trig_mu_bf_fb 】    	{"Triggered SU Beamforming Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.trig_su_bf_fb  】
2054  :【 wlan.ext_tag.he_phy_cap.nbytes.trig_cqi_fb 】    	{"Triggered MU Beamforming Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.trig_mu_bf_fb  】
2055  :【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_er 】    	{"Triggered CQI Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.trig_cqi_fb  】
2056  :【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_dl_mu_mimo 】    	{"Partial Bandwidth Extended Range"     【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_er  】
2057  :【 wlan.ext_tag.he_phy_cap.nbytes.ppe_thres_present 】    	{"Partial Bandwidth DL MU-MIMO"     【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_dl_mu_mimo  】
2058  :【 wlan.ext_tag.he_phy_cap.bits_56_to_71 】    	{"PPE Threshold Present"     【 wlan.ext_tag.he_phy_cap.nbytes.ppe_thres_present  】
2059  :【 wlan.ext_tag.he_phy_cap.nbytes.srp_based_sr_sup 】    	{"Bits 56 to 71"     【 wlan.ext_tag.he_phy_cap.bits_56_to_71  】
2060  :【 wlan.ext_tag.he_phy_cap.nbytes.pwr_bst_factor_ar_sup 】    	{"SRP-based SR Support"     【 wlan.ext_tag.he_phy_cap.nbytes.srp_based_sr_sup  】
2061  :【 wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_e   	{"Power Boost Factor ar Support"     【 wlan.ext_tag.he_phy_cap.nbytes.pwr_bst_factor_ar_sup  】
2062  :【 wlan.ext_tag.he_phy_cap.nbytes.max_nc 】    	{"HE SU PPDU & HE MU PPDU w 4x HE-LTF & 0.8us GI"     【 wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_etc_gi  】
2063  :【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_gt_80_mhz 】    	{"Max Nc"     【 wlan.ext_tag.he_phy_cap.nbytes.max_nc  】
2064  :【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_gt_80_mhz 】    	{"STBC Tx > 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_gt_80_mhz  】
2065  :【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_4xxx_gi    	{"STBC Rx > 80 MHz"     【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_gt_80_mhz  】
2066  :【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_40_in_2_4   	{"HE ER SU PPDU W 4x HE-LTF & 0.8us GI"     【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_4xxx_gi  】
2067  :【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_160_80p80_ppdu 】   	{"20 MHz In 40 MHz HE PPDU In 2.4GHz Band"     【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_40_in_2_4ghz  】
2068  :【 wlan.ext_tag.he_phy_cap.nbytes.80_mhz_in_160_80p80_ppdu 】   	{"20 MHz In 160/80+80 MHz HE PPDU"     【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_160_80p80_ppdu  】
2069  :【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_1xxx_gi    	{"80 MHz In 160/80+80 MHz HE PPDU"     【 wlan.ext_tag.he_phy_cap.nbytes.80_mhz_in_160_80p80_ppdu  】
2070  :【 wlan.ext_tag.he_phy_cap.nbytes.midamble_rx_2x_1x_he_ltf 】    	{"HE ER SU PPDU W 1x HE-LTF & 0.8us GI"     【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_1xxx_gi  】
2071  :【 wlan.ext_tag.he_phy_cap.bits_72_to_87 】    	{"Midamble Rx 2x & 1x HE-LTF"     【 wlan.ext_tag.he_phy_cap.nbytes.midamble_rx_2x_1x_he_ltf  】
2072  :【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_bw 】    	{"Bits 72 to 87"     【 wlan.ext_tag.he_phy_cap.bits_72_to_87  】
2073  :【 wlan.ext_tag.he_phy_cap.nbyes.longer_than_16_   	{"DCM Max BW"     【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_bw  】
2074  :【 wlan.ext_tag.he_phy_cap.nbytes.non_triggered_feedback 】    	{"Longer Than 16 HE SIG-B OFDM Symbols Support"     【 wlan.ext_tag.he_phy_cap.nbyes.longer_than_16_he_sigb_ofdm_sym_support  】
2075  :【 wlan.ext_tag.he_phy_cap.nbytes.tx_1024_qam_support_lt_24 】    	{"Non-Triggered CQI Feedback"     【 wlan.ext_tag.he_phy_cap.nbytes.non_triggered_feedback  】
2076  :【 wlan.ext_tag.he_phy_cap.nbytes.rx_1024_qam_support_lt_24  】    	{"Tx 1024-QAM Support < 242-tone RU"     【 wlan.ext_tag.he_phy_cap.nbytes.tx_1024_qam_support_lt_242_tone_ru  】
2077  :【 wlan.ext_tag.he_phy_cap.nbytes.rx_full  】    	{"Rx 1024-QAM Support < 242-tone RU"     【 wlan.ext_tag.he_phy_cap.nbytes.rx_1024_qam_support_lt_242_tone_ru  】
2078  :【 wlan.ext_tag.he_phy_cap.nbytes.rx_   】    	{"Rx Full BW SU Using HE MU PPDU With Compressed SIGB"     【 wlan.ext_tag.he_phy_cap.nbytes.rx_full_bw_su_using_he_mu_ppdu_with_compressed_sigb  】
2079  :【 wlan.ext_tag.he_phy_cap.nbytes.reserved_b78_b87 】    	{"Rx Full BW SU Using HE MU PPDU With Non-Compressed SIGB"     【 wlan.ext_tag.he_phy_cap.nbytes.rx_full_bw_su_using_he_mu_ppdu_with_non_compressed_sigb  】
2080  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss 】    	{"Reserved"     【 wlan.ext_tag.he_phy_cap.nbytes.reserved_b78_b87  】
2081  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss 】    	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss  】
2082  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_2_ss 】    	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_2_ss  】
2083  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_3_ss 】    	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_3_ss  】
2084  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_4_ss 】    	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_4_ss  】
2085  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_5_ss 】    	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_5_ss  】
2086  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_6_ss 】    	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_6_ss  】
2087  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_7_ss 】    	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_7_ss  】
2088  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_8_ss 】    	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_8_ss  】
2089  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_1_ss 】    	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_1_ss  】
2090  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_2_ss 】    	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_2_ss  】
2091  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_3_ss 】    	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_3_ss  】
2092  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_4_ss 】    	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_4_ss  】
2093  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_5_ss 】    	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_5_ss  】
2094  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_6_ss 】    	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_6_ss  】
2095  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_7_ss 】    	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_7_ss  】
2096  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_8_ss 】    	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_8_ss  】
2097  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_1_ss 】    	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_1_ss  】
2098  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_2_ss 】    	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_2_ss  】
2099  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_3_ss 】    	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_3_ss  】
2100  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_4_ss 】    	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_4_ss  】
2101  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_5_ss 】    	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_5_ss  】
2102  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_6_ss 】    	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_6_ss  】
2103  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_7_ss 】    	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_7_ss  】
2104  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_8_ss 】    	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_8_ss  】
2105  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_1_ss 】    	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_1_ss  】
2106  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_2_ss 】    	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_2_ss  】
2107  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_3_ss 】    	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_3_ss  】
2108  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_4_ss 】    	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_4_ss  】
2109  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_5_ss 】    	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_5_ss  】
2110  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_6_ss 】                      	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_6_ss  】
2111  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_7_ss 】                      	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_7_ss  】
2112  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_8_ss 】                      	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_8_ss  】
2113  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_1_ss 】                        	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_1_ss  】
2114  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_2_ss 】                        	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_2_ss  】
2115  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_3_ss 】   	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_3_ss  】
2116  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_4_ss 】   	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_4_ss  】
2117  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_5_ss 】   	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_5_ss  】
2118  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_6_ss 】   	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_6_ss  】
2119  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_7_ss 】   	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_7_ss  】
2120  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_8_ss 】   	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_8_ss  】
2121  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_1_ss 】   	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_1_ss  】
2122  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_2_ss 】   	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_2_ss  】
2123  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_3_ss 】   	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_3_ss  】
2124  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_4_ss 】   	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_4_ss  】
2125  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_5_ss 】   	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_5_ss  】
2126  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_6_ss 】   	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_6_ss  】
2127  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_7_ss 】   	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_7_ss  】
2128  :【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_8_ss 】   	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_8_ss  】
2129  :【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_lte_80 】    	{"Rx HEX-MCS Map <= 80 MHz"     【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_lte_80  】
2130  :【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_lte_80 】    	{"Tx HEX-MCS Map <= 80 MHz"     【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_lte_80  】
2131  :【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_160 】    	{"Rx HEX-MCS Map 160 MHz"     【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_160  】
2132  :【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_160 】    	{"Tx HEX-MCS Map 160 MHz"     【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_160  】
2133  :【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_80_80 】    	{"Rx HEX-MCS Map 80+80 MHz"     【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_80_80  】
2134  :【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_80_80 】    	{"Tx HEX-MCS Map 80+80 MHz"     【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_80_80  】
2135  :【 wlan.ext_tag.he_ppe_thresholds.nss 】    	{"NSS"     【 wlan.ext_tag.he_ppe_thresholds.nss  】
2136  :【 wlan.ext_tag.he_ppe_thresholds.ru_index_bitmask 】    	{"RU Index Bitmask"     【 wlan.ext_tag.he_ppe_thresholds.ru_index_bitmask  】
2137  :【wlan.ext_tag.he_ppe_thresholds.ppet16 】   	{"PPET16  】"wlan.ext_tag.he_ppe_thresholds.ppet16  】
2138  :【 wlan.ext_tag.he_ppe_thresholds.ppet8 】        	{"PPET8  】"wlan.ext_tag.he_ppe_thresholds.ppet8  】
2139  :【 wlan.ext_tag.he_operation.params 】    	{"HE Operation Parameters"     【 wlan.ext_tag.he_operation.params  】
2140  :【 wlan.ext_tag.he_operation.default_pe_duration 】    	{"Default PE Duration"     【 wlan.ext_tag.he_operation.default_pe_duration  】
2141  :【 wlan.ext_tag.he_operation.twt_required 】    	{"TWT Required"     【 wlan.ext_tag.he_operation.twt_required  】
2142  :【 wlan.ext_tag.he_operation.txop_duration_rts_thre  】    	{"TXOP Duration RTS Threshold"     【 wlan.ext_tag.he_operation.txop_duration_rts_thresh  】
2143  :【 wlan.ext_tag.he_operation.vht_op_info_pres  】    	{"VHT Operation Information Present"     【 wlan.ext_tag.he_operation.vht_op_info_present  】
2144  :【 wlan.ext_tag.he_operation.co_located_bss 】    	{"Co-Located BSS"     【 wlan.ext_tag.he_operation.co_located_bss  】
2145  :【 wlan.ext_tag.he_operation.er_su_disable 】    	{"ER SU Disable"     【 wlan.ext_tag.he_operation.er_su_disable  】
2146  :【 wlan.ext_tag.he_operation.reserved_b17_b32 】    	{"Reserved"     【 wlan.ext_tag.he_operation.reserved_b17_b32  】
2147  :【 wlan.ext_tag.bss_color_information 】    	{"BSS Color Information"     【 wlan.ext_tag.bss_color_information  】
2148  :【 wlan.ext_tag.bss_color_information.bss_color 】    	{"BSS Color"     【 wlan.ext_tag.bss_color_information.bss_color  】
2149  :【 wlan.ext_tag.bss_color_information.partial_bss_color 】    	{"Partial BSS Color"     【 wlan.ext_tag.bss_color_information.partial_bss_color  】
2150  :【 wlan.ext_tag.bss_color_information.bss_color_disabled 】    	{"BSS Color Disabled"     【 wlan.ext_tag.bss_color_information.bss_color_disabled  】
2151  :【 wlan.ext_tag.he_operation.basic_he_mcs_and_nss 】    	{"Basic HE-MCS and NSS Set"     【 wlan.ext_tag.he_operation.basic_he_mcs_and_nss  】
2152  :【 wlan.ext_tag.he_operation.max_he_mcs_for_1_ss 】    	{"Max HE-MCS for 1 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_1_ss  】
2153  :【 wlan.ext_tag.he_operation.max_he_mcs_for_2_ss 】    	{"Max HE-MCS for 2 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_2_ss  】
2154  :【 wlan.ext_tag.he_operation.max_he_mcs_for_3_ss 】    	{"Max HE-MCS for 3 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_3_ss  】
2155  :【 wlan.ext_tag.he_operation.max_he_mcs_for_4_ss 】    	{"Max HE-MCS for 4 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_4_ss  】
2156  :【 wlan.ext_tag.he_operation.max_he_mcs_for_5_ss 】    	{"Max HE-MCS for 5 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_5_ss  】
2157  :【 wlan.ext_tag.he_operation.max_he_mcs_for_6_ss 】    	{"Max HE-MCS for 6 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_6_ss  】
2158  :【 wlan.ext_tag.he_operation.max_he_mcs_for_7_ss 】    	{"Max HE-MCS for 7 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_7_ss  】
2159  :【 wlan.ext_tag.he_operation.max_he_mcs_for_8_ss 】    	{"Max HE-MCS for 8 SS"     【 wlan.ext_tag.he_operation.max_he_mcs_for_8_ss  】
2160  :【 wlan.ext_tag.he_operation.vht_op_info.channel_width 】    	{"channel Width"     【 wlan.ext_tag.he_operation.vht_op_info.channel_width  】
2161  :【 wlan.ext_tag.he_operatoon.vht_op_info.cha 】    	{"Channel Center Frequency Segment 0"     【 wlan.ext_tag.he_operatoon.vht_op_info.chan_center_freq_seg_0  】
2162  :【 wlan.ext_tag.he_operatoon.vht_op_info.cha  】    	{"Channel Center Frequency Segment 1"     【 wlan.ext_tag.he_operatoon.vht_op_info.chan_center_freq_seg_1  】
2163  :【 wlan.ext_tag.he_operation.max_colocated_bssid  】    	{"Max Co-Located BSSID Indicator"     【 wlan.ext_tag.he_operation.max_colocated_bssid_indicator  】
2164  :【wlan.ext_tag.mu_edca_parameter_set.aic_aifsn 】     	{"AIC/AIFSN  】"wlan.ext_tag.mu_edca_parameter_set.aic_aifsn  】
2165  :【 wlan.ext_tag.mu_edca_parameter_set.aifsn 】    	{"AIFSN"     【 wlan.ext_tag.mu_edca_parameter_set.aifsn  】
2166  :【 wlan.ext_tag.mu_edca_parameter_set.acm 】    	{"Admission Control Mandatory"     【 wlan.ext_tag.mu_edca_parameter_set.acm  】
2167  :【 wlan.ext_tag.mu_edca_parameter_set.aci 】    	{"ACI"     【 wlan.ext_tag.mu_edca_parameter_set.aci  】
2168  :【 wlan.ext_tag.mu_edca_parameter_set.reserved 】    	{"Reserved"     【 wlan.ext_tag.mu_edca_parameter_set.reserved  】
2169  :【wlan.ext_tag.mu_edca_parameter_set.mu_edca_timer 】         	{"MU EDCA Timer  】"wlan.ext_tag.mu_edca_parameter_set.mu_edca_timer  】
2170  :【wlan.ext_tag.mu_edca_parameter_set.ecwmin_ecwmax 】    	{"ECWmin/ECWmax  】"wlan.ext_tag.mu_edca_parameter_set.ecwmin_ecwmax  】
2171  :【 wlan.ext_tag.spatial_reuse.sr_control 】    	{"SR Control"     【 wlan.ext_tag.spatial_reuse.sr_control  】
2172  :【 wlan.ext_tag.spatial_reuse.sr_control.srp_dis 】    	{"SRP Disallowed"     【 wlan.ext_tag.spatial_reuse.sr_control.srp_dis  】
2173  :【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_ 】    	{"NON-SRG OBSS PD SR Disallowed"     【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_obss_pd_sr_dis  】
2174  :【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_ofs_pre 】    	{"Non-SRG Offset Present"     【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_ofs_present  】
2175  :【 wlan.ext_tag.spatial_reuse.sr_control.srg_info_prese  】    	{"SRG Information Present"     【 wlan.ext_tag.spatial_reuse.sr_control.srg_info_present  】
2176  :【 wlan.ext_tag.spatial_reuse.sr_control.       】    	{"HESIGA Spatial Reuse value 15 allowed"     【 wlan.ext_tag.spatial_reuse.sr_control.hesiga_val_15_allowed  】
2177  :【 wlan.ext_tag.spatial_reuse.sr_control.reserved 】    	{"Reserved"     【 wlan.ext_tag.spatial_reuse.sr_control.reserved  】
2178  :【 wlan.ext_tag.spatial_reuse.non_srg_obss_pd_max_of  】    	{"Non-SRG OBSS PD Max Offset"     【 wlan.ext_tag.spatial_reuse.non_srg_obss_pd_max_offset  】
2179  :【 wlan.ext_tag.spatial_reuse.srg_obss_pd_min_offset 】    	{"SRG OBSS PD Min Offset"     【 wlan.ext_tag.spatial_reuse.srg_obss_pd_min_offset  】
2180  :【 wlan.ext_tag.spatial_reuse.srg_obss_pd_max_offset 】    	{"SRG OBSS PD Max Offset"     【 wlan.ext_tag.spatial_reuse.srg_obss_pd_max_offset  】
2181  :【 wlan.ext_tag.spatial_reuse.srg_bss_color_bitmap 】    	{"SRG BSS Color Bitmap"     【 wlan.ext_tag.spatial_reuse.srg_bss_color_bitmap  】
2182  :【 wlan.ext_tag.spatial_reuse.srg_partial_bssid_bitmap   	{"SRG Partial BSSID Bitmap"     【 wlan.ext_tag.spatial_reuse.srg_partial_bssid_bitmap  】
2183  :【 wlan.ext_tag.ndp_feedback.res_req 】   	{"Resource Request Buffer Threshold Exponent"     【 wlan.ext_tag.ndp_feedback.res_req_buf_thresh_exp  】
2184  :【 wlan.ext_tag.bss_color_change.new_color_info 】    	{"New BSS Color Info"     【 wlan.ext_tag.bss_color_change.new_color_info  】
2185  :【 wlan.ext_tag.bss_color_change.new_bss_color 】    	{"New BSS Color"     【 wlan.ext_tag.bss_color_change.new_bss_color  】
2186  :【 wlan.ext_tag.bss_color_change.new_color_reserved 】    	{"Reserved"     【 wlan.ext_tag.bss_color_change.new_color_reserved  】
2187  :【 wlan.ext_tag.bss_color_change.color_switch_countd   	{"BSS Color Switch Countdown"     【 wlan.ext_tag.bss_color_change.color_switch_countdown  】
2188  :【 wlan.ext_tag.ess_report.ess_info.planned_ess 】    	{"Planned ESS"     【 wlan.ext_tag.ess_report.ess_info.planned_ess  】
2189  :【 wlan.ext_tag.ess_report.ess_info.edge_of_ess 】    	{"Edge of ESS"     【 wlan.ext_tag.ess_report.ess_info.edge_of_ess  】
2190  :【 wlan.ext_tag.ess_report.ess_info.field 】    	{"ESS Information field"     【 wlan.ext_tag.ess_report.ess_info.field  】
2191  :【 wlan.ext_tag.ess_report.ess_info.thresh  】    	{"Recommended BSS Transition Threshold"     【 wlan.ext_tag.ess_report.ess_info.thresh  】
2192  :【 wlan.ext_tag.uora_parameter_set.f  】    	{"UL OFDMA-based Random Access Parameter SET"     【 wlan.ext_tag.uora_parameter_set.field  】
2193  :【 wlan.ext_tag.uora_parameter_set.eocwmin 】    	{"EOCWmin"     【 wlan.ext_tag.uora_parameter_set.eocwmin  】
2194  :【 wlan.ext_tag.uora_parameter_set.eocwmax 】    	{"EOCWmax"     【 wlan.ext_tag.uora_parameter_set.eocwmax  】
2195  :【 wlan.ext_tag.uora_parameter_set.reserved 】    	{"Reserved"     【 wlan.ext_tag.uora_parameter_set.reserved  】
2196  :【 wlan.s1g.action 】    	{"S1G Action"     【 wlan.s1g.action  】
2197  :【 wlan.twt.bcast_flow 】    	{"TWT Flow"     【 wlan.twt.bcast_flow  】
2198  :【 wlan.twt.individual_flow 】    	{"TWT Flow"     【 wlan.twt.individual_flow  】
2199  :【 wlan.twt.individual_flow_id 】    	{"Individual TWT Flow Id"     【 wlan.twt.individual_flow_id  】
2200  :【 wlan.twt.bcast_flow_id 】    	{"Broadcast TWT Id"     【 wlan.twt.bcast_flow_id  】
2201  :【 wlan.twt.neg_type 】    	{"TWT Negotiation type"     【 wlan.twt.neg_type  】
2202  :【 wlan.twt.control_field 】    	{"Control Field"     【 wlan.twt.control_field  】
2203  :【 wlan.twt.ndp_paging_indicator 】    	{"NDP Paging Indicator"     【 wlan.twt.ndp_paging_indicator  】
2204  :【 wlan.twt.resp_pm 】    	{"Responder PM Mode"     【 wlan.twt.resp_pm  】
2205  :【 wlan.twt.neg_type 】    	{"Negotiation type"     【 wlan.twt.neg_type  】
2206  :【 wlan.twt.control_field_reserved 】    	{"Reserved"     【 wlan.twt.control_field_reserved  】
2207  :【 wlan.twt.request_type 】    	{"Request Type"     【 wlan.twt.request_type  】
2208  :【 wlan.twt.requester 】    	{"Requester"     【 wlan.twt.requester  】
2209  :【 wlan.twt.setup_cmd 】    	{"Setup Command"     【 wlan.twt.setup_cmd  】
2210  :【 wlan.twt.trigger 】    	{"Trigger"     【 wlan.twt.trigger  】
2211  :【 wlan.twt.implicit 】    	{"Implicit"     【 wlan.twt.implicit  】
2212  :【 wlan.twt.flow_type 】    	{"Flow type"     【 wlan.twt.flow_type  】
2213  :【 wlan.twt.flow_id 】    	{"Flow ID"     【 wlan.twt.flow_id  】
2214  :【 wlan.twt.wake_interval_exp 】    	{"Wake Interval Exponent"     【 wlan.twt.wake_interval_exp  】
2215  :【 wlan.twt.prot 】    	{"Protection"     【 wlan.twt.prot  】
2216  :【 wlan.twt.target_wake_time 】    	{"Target Wake Time"     【 wlan.twt.target_wake_time  】
2217  :【 wlan.twt.nom_min_twt_wake_duration 】    	{"Nominal Minimum TWT Wake duration"     【 wlan.twt.nom_min_twt_wake_duration  】
2218  :【 wlan.twt.wake_interval_mantissa 】    	{"TWT Wake Interval Mantissa"     【 wlan.twt.wake_interval_mantissa  】
2219  :【 wlan.twt.channel 】    	{"TWT Channel"     【 wlan.twt.channel  】
2220  :【 wlan.ext_tag.owe_dh_parameter.group 】    	{"Group"     【 wlan.ext_tag.owe_dh_parameter.group  】
2221  :【 wlan.ext_tag.owe_dh_parameter.public_key 】    	{"Public Key"     【 wlan.ext_tag.owe_dh_parameter.public_key  】
2222  :【 wlan_aggregate.a_mdsu.subframe 】    	{"A-MSDU Subframe"     【 wlan_aggregate.a_mdsu.subframe  】
2223  :【 wlan_aggregate.a_mdsu.length 】    	{"A-MSDU Length"     【 wlan_aggregate.a_mdsu.length  】
2224  :【 wlan.tag.number.unexpected_ie 】         	{ "wlan.tag.number.unexpected_ie  】  
2225  :【 wlan.tag.length.bad 】         	{ "wlan.tag.length.bad  】  
2226  :【 wlan.fixed.anqp.capability.invalid 】         	{ "wlan.fixed.anqp.capability.invalid  】  
2227  :【 wlan.fixed.anqp.venue.length.invalid 】         	{ "wlan.fixed.anqp.venue.length.invalid  】  
2228  :【 wlan.fixed.anqp.roaming_consortium.oi_len.invalid 】         	{ "wlan.fixed.anqp.roaming_consortium.oi_len.invalid  】  
2229  :【 wlan.fixed.anqp.nai_realm_list.field_len.invalid 】         	{ "wlan.fixed.anqp.nai_realm_list.field_len.invalid  】  
2230  :【 wlan.fixed.naqp_nai_realm_list.eap_method_len.invalid 】         	{ "wlan.fixed.naqp_nai_realm_list.eap_method_len.invalid  】  
2231  :【 wlan.hs20.anqp.ofn.length.invalid 】         	{ "wlan.hs20.anqp.ofn.length.invalid  】  
2232  :【 wlan.hs20.anqp.nai_hrq.length.invalid 】         	{ "wlan.hs20.anqp.nai_hrq.length.invalid  】  
2233  :【 wlan.fixed.anqp.info_length.invalid 】         	{ "wlan.fixed.anqp.info_length.invalid  】  
2234  :【 wlan.fixed.query_length_invalid 】         	{ "wlan.fixed.query_length_invalid  】  
2235  :【 wlan.fixed.query_request_length.invalid 】         	{ "wlan.fixed.query_request_length.invalid  】  
2236  :【 wlan.fixed.query_response_length.invalid 】         	{ "wlan.fixed.query_response_length.invalid  】  
2237  :【 wlan.wnm_sleep_mode.no_key_data 】         	{ "wlan.wnm_sleep_mode.no_key_data  】  
2238  :【 wlan.tdls_setup_response_malformed 】         	{ "wlan.tdls_setup_response_malformed  】  
2239  :【 wlan.tdls_setup_confirm_malformed 】         	{ "wlan.tdls_setup_confirm_malformed  】  
2240  :【 wlan.wfa.ie.wme.qos_info.bad_ftype 】       	{ "wlan.wfa.ie.wme.qos_info.bad_ftype  】   
2241  :【 wlan.qos_info.bad_ftype 】       	{ "wlan.qos_info.bad_ftype  】   
2242  :【 wlan.qos_info.bad_aifsn 】         	{ "wlan.qos_info.bad_aifsn  】  
2243  :【 wlan.rsn.pcs.count.invalid 】         	{ "wlan.rsn.pcs.count.invalid  】  
2244  :【 wlan.rsn.akms.count.invalid 】         	{ "wlan.rsn.akms.count.invalid  】  
2245  :【 wlan.rsn.pmkid.count.invalid 】         	{ "wlan.rsn.pmkid.count.invalid  】  
2246  :【 wlan.vht.tpe.pwr_info.count.invalid 】         	{ "wlan.vht.tpe.pwr_info.count.invalid  】  
2247  :【 wlan.fc.retry.expert 】  PI_SEQUENCE,     	{ "wlan.fc.retry.expert  】 PI_SEQUENCE,  
2248  :【 wlan.measure.req.unknown.expert 】       	{ "wlan.measure.req.unknown.expert  】   
2249  :【 wlan.measure.req.beacon.unknown.expert 】       	{ "wlan.measure.req.beacon.unknown.expert  】   
2250  :【 wlan.measure.req.unknown.expert 】       	{ "wlan.measure.req.unknown.expert  】   
2251  :【 wlan.tag.data.undecoded 】       	{ "wlan.tag.data.undecoded  】   
2252  :【 wlan.dmg_subtype.bad 】         	{ "wlan.dmg_subtype.bad  】  
2253  :【 wlan.vht.action.undecoded 】       	{ "wlan.vht.action.undecoded  】   
2254  :【 wlan.peering.unexpected 】         	{ "wlan.peering.unexpected  】  
2255  :【 wlan.fcs.bad_checksum 】         	{ "wlan.fcs.bad_checksum  】  
2256  :【 wlan.rsn.akms.mismatched 】      	{ "wlan.rsn.akms.mismatched  】   
2257  :【 wlan.vs.routerboard.unexpected_len 】      	{ "wlan.vs.routerboard.unexpected_len  】   
2258  :【 wlan.twt.tear_down_bad_neg_type 】      	{ "wlan.twt.tear_down_bad_neg_type  】   
2259  :【 wlan.twt.setup_unsup_neg_type 】       	{ "wlan.twt.setup_unsup_neg_type  】   
2260  :【 wlan.twt.setup_bad_command 】      	{ "wlan.twt.setup_bad_command  】   



```


