
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



# wireshark源码
 wireshark源码地址:   https://github.com/wireshark/wireshark

```
过滤地址
/epan/dissectors/packet-ieee80211.c:25733:      {"Hardware address", "wlan.addr", }


```

## IEEE802.11 过滤条件统计
```

1:    {"Frame Control Field       【  wlan.fc 】
2:    {"Version       【  wlan.fc.version 】
3:    {"Type       【  wlan.fc.type 】
4:    {"Subtype       【  wlan.fc.subtype 】
5:    {"Type/Subtype       【  wlan.fc.type_subtype 】
6:    {"Control Frame Extension       【  wlan.fc.extension 】
7:    {"Flags       【  wlan.flags 】
8:    {"DS status       【  wlan.fc.ds 】
9:    {"To DS       【  wlan.fc.tods 】
10:   {"From DS       【  wlan.fc.fromds 】
11:   {"More Fragments       【  wlan.fc.frag 】
12:   {"Retry       【  wlan.fc.retry 】
13:   {"Retransmission       【  wlan.analysis.retransmission 】
14:   {"Retransmission of frame       【  wlan.analysis.retransmission_frame 】
15:   {"PWR MGT       【  wlan.fc.pwrmgt 】
16:   {"More Data       【  wlan.fc.moredata 】
17:   {"Protected flag       【  wlan.fc.protected 】
18:   {"Order flag       【  wlan.fc.order 】
19:   {"Association ID       【  wlan.aid 】
20:   {"Duration       【  wlan.duration 】
21:   {"Destination address       【  wlan.da 】
22:   {"Destination address (resolved)       【  wlan.da_resolved 】 FT_STRING,
23:   {"Source address       【  wlan.sa 】
24:   {"Source address (resolved)       【  wlan.sa_resolved 】 FT_STRING,
25:   {"Hardware address       【  wlan.addr 】
26:   {"Hardware address (resolved)       【  wlan.addr_resolved 】 FT_STRING,
27:   {"Receiver address       【  wlan.ra 】
28:   {"Receiver address (resolved)       【  wlan.ra_resolved 】 FT_STRING, BASE_NONE,
29:   {"Transmitter address       【  wlan.ta 】
30:   {"Transmitter address (resolved)       【  wlan.ta_resolved 】 FT_STRING,
31:   {"BSS Id       【  wlan.bssid 】
32:   {"BSS Id (resolved)       【  wlan.bssid_resolved 】 FT_STRING, BASE_NONE, NULL,
33:   {"STA address       【  wlan.staa 】
34:   {"STA address (resolved)       【  wlan.staa_resolved 】 FT_STRING, BASE_NONE, NULL,
35:   {"Fragment number       【  wlan.frag 】
36:   {"Sequence number       【  wlan.seq 】
37:   {"Mesh Control Field       【  wlan.mesh.control_field 】
38:   {"Qos Control       【  wlan.qos 】
39:   {"TID       【  wlan.qos.tid 】
40:   {"Priority       【  wlan.qos.priority 】
41:   {"EOSP       【  wlan.qos.eosp 】
42:   {"QoS bit 4       【  wlan.qos.bit4 】
43:   {"Ack Policy       【  wlan.qos.ack 】
44:   {"Payload Type       【  wlan.qos.amsdupresent 】
45:   {"TXOP Limit       【  wlan.qos.txop_limit 】
46:   {"QAP PS Buffer State       【  wlan.qos.ps_buf_state 】
47:   {"Buffer State Indicated       【  wlan.qos.buf_state_indicated 】
48:   {"Highest-Priority Buffered AC       【  wlan.qos.highest_pri_buf_ac 】
49:   {"QAP Buffered Load       【  wlan.qos.qap_buf_load 】
50:   {"TXOP Duration Requested       【  wlan.qos.txop_dur_req 】
51:   {"Queue Size       【  wlan.qos.queue_size 】
52:   {"Frame check sequence       【  wlan.fcs 】
53:   {"FCS Status       【  wlan.fcs.status 】
54:   {"Fragment overlap       【  wlan.fragment.overlap 】
55:   {"Conflicting data in fragment overlap       【  wlan.fragment.overlap.conflict 】
56:   {"Multiple tail fragments found       【  wlan.fragment.multipletails 】
57:   {"Fragment too long       【  wlan.fragment.toolongfragment 】
58:   {"Defragmentation error       【  wlan.fragment.error 】
59:   {"Fragment count       【  wlan.fragment.count 】
60:   {"802.11 Fragment       【  wlan.fragment 】
61:   {"802.11 Fragments       【  wlan.fragments 】
62:   {"Reassembled 802.11 in frame       【  wlan.reassembled_in 】
63:   {"Reassembled 802.11 length       【  wlan.reassembled.length 】
64:   {"Initialization Vector       【  wlan.wep.iv 】
65:   {"Weak IV       【  wlan.wep.weakiv 】
66:   {"TKIP Ext. Initialization Vector       【  wlan.tkip.extiv 】
67:   {"CCMP Ext. Initialization Vector       【  wlan.ccmp.extiv 】
68:   {"Key Index       【  wlan.wep.key 】
69:   {"WEP ICV       【  wlan.wep.icv 】
70:   {"PMK       【  wlan.analysis.pmk 】
71:   {"KCK       【  wlan.analysis.kck 】
72:   {"KEK       【  wlan.analysis.kek 】
73:   {"TK       【  wlan.analysis.tk 】
74:   {"GTK       【  wlan.analysis.gtk 】
75:   {"Block Ack Control       【  wlan.ba.control 】
76:   {"BA Ack Policy       【  wlan.ba.control.ackpolicy 】
77:   {"BA Type       【  wlan.ba.control.ba_type 】
78:   {"Reserved       【  wlan.ba.control.reserved 】
79:   {"TID for which a Basic BlockAck frame is requested       【  wlan.ba.basic.tidinfo 】
80:   {"AID11       【  wlan.ba.multi_sta.aid11 】
81:   {"Ack Type       【  wlan.ba.multi_sta.ack_type 】
82:   {"TID       【  wlan.ba.multi_sta.tid 】
83:   {"AID TID Info       【  wlan.ba.multi_sta.aid_tid_info 】
84:   {"Reserved       【  wlan.ba.multi_sta.reserved 】
85:   {"RA       【  wlan.ba.multi_sta.ra 】
86:   {"Reserved       【  wlan.bar.mtid.tidinfo.reserved 】
87:   {"Multi-TID Value       【  wlan.bar.mtid.tidinfo.value 】
88:   {"Block Ack Bitmap       【  wlan.ba.bm 】
89:   {"Block Ack RBUFCAP       【  wlan.ba.RBUFCAP 】
90:   {"Missing frame       【  wlan.ba.bm.missing_frame 】
91:   {"GCR Group Address       【  wlan.ba.gcr_group_addr 】
92:   {"Feedback segment Retansmission Bitmap       【  wlan.beamform.feedback_seg_retrans_bitmap 】
93:   {"Sounding Dialog Token       【  wlan.vht_ndp.token 】
94:   {"Sounding Dialog Token Number       【  wlan.vht_ndp.token.number 】
95:   {"HE       【  wlan.vht_ndp.token.he 】
96:   {"Reserved       【  wlan.vht_ndp.token.reserved 】
97:   {"AID12       【  wlan.vht_ndp.sta_info.aid12 】
98:   {"Feedback Type       【  wlan.vht_ndp.sta_info.feedback_type 】
99:   {"Nc Index       【  wlan.vht_ndp.sta_info.nc_index 】
100:   {"Reserved       【  wlan.vht_ndp.sta_info.reserved 】
101:   {"Payload Type       【  wlan.data_encap.payload_type 】
102:   {"Action code       【  wlan.fixed.action_code 】
103:   {"Target Channel       【  wlan.fixed.target_channel 】
104:   {"Operating Class       【  wlan.fixed.operating_class 】
105:   {"Action code       【  wlan.fixed.action_code 】
106:   {"Action code       【  wlan.fixed.action_code 】
107:   {"Key Data       【  wlan.fixed.key_data 】
108:   {"Key Data Length       【  wlan.fixed.key_data_length 】
109:   {"WNM-Notification type       【  wlan.fixed.wnm_notification_type 】
110:   {"Action code       【  wlan.rm.action_code 】
111:   {"Dialog token       【  wlan.rm.dialog_token 】
112:   {"Repetitions       【  wlan.rm.repetitions 】
113:   {"Transmit Power Used       【  wlan.rm.tx_power 】
114:   {"Max Transmit Power       【  wlan.rm.max_tx_power 】
115:   {"TPC Report       【  wlan.rm.tpc 】
116:   {"TPC Element ID       【  wlan.rm.tpc.element_id 】
117:   {"TPC Length       【  wlan.rm.tpc.length 】
118:   {"TPC Transmit Power       【  wlan.rm.tpc.tx_power 】
119:   {"TPC Link Margin       【  wlan.rm.tpc.link_margin 】
120:   {"Receive Antenna ID       【  wlan.rm.rx_antenna_id 】
121:   {"Transmit Antenna ID       【  wlan.rm.tx_antenna_id 】
122:   {"Received Channel Power       【  wlan.rm.rcpi 】
123:   {"Received Signal to noise indication       【  wlan.rm.rsni 】
124:   {"Preferred Candidate List Included       【  wlan.fixed.request_mode.pref_cand 】
125:   {"Abridged       【  wlan.fixed.request_mode.abridged 】
126:   {"Disassociation Imminent       【  wlan.fixed.request_mode.disassoc_imminent 】
127:   {"BSS Termination Included       【  wlan.fixed.request_mode.bss_term_included 】
128:   {"ESS Disassociation Imminent       【  wlan.fixed.request_mode.ess_disassoc_imminent 】
129:   {"Disassociation Timer       【  wlan.fixed.disassoc_timer 】
130:   {"BSS Termination Delay       【  wlan.fixed.bss_termination_delay 】
131:   {"BSS Transition Status Code       【  wlan.fixed.bss_transition_status_code 】
132:   {"Validity Interval       【  wlan.fixed.validity_interval 】
133:   {"BSS Termination Duration       【  wlan.fixed.bss_termination_duration 】
134:   {      【  wlan.fixed.session_information.url_length 】
135:   {"Session Information URL       【  wlan.fixed.session_information.url 】
136:   {"BSS Transition Target BSS       【  wlan.fixed.bss_transition_target_bss 】
137:   {"BSS Transition Query Reason       【  wlan.fixed.bss_transition_query_reason 】
138:   {"BSS Transition Candidate List Entries       【  wlan.fixed.bss_transition_candidate_list_entries 】
139:   {"Response Offset       【  wlan.res_offset 】
140:   {"Reserved       【  wlan.grant_ack.reserved 】
141:   {"Dynamic Allocation       【  wlan.dynamic_allocation 】
142:   {"TID       【  wlan.dynamic_allocation.tid 】
143:   {"Allocation Type       【  wlan.dynamic_allocation.alloc_type 】
144:   {"Source AID       【  wlan.dynamic_allocation.src_aid 】
145:   {"Destination AID       【  wlan.dynamic_allocation.dest_aid 】
146:   {"Allocation Duration       【  wlan.dynamic_allocation.alloc_duration 】
147:   {"Reserved (b39)       【  wlan.dynamic_allocation.b39 】
148:   {"Sector Sweep       【  wlan.ssw 】
149:   {"Sector Sweep Direction       【  wlan.ssw.direction 】
150:   {"Sector Sweep CDOWN       【  wlan.ssw.cdown 】
151:   {"Sector Sweep Sector ID       【  wlan.ssw.sector_id 】
152:   {"Sector Sweep DMG Antenna ID       【  wlan.ssw.dmg_ant_id 】
153:   {"Sector Sweep RXSS Length       【  wlan.ssw.rxss_len 】
154:   {"Beam Forming       【  wlan.bf 】
155:   {"Beam Forming Training       【  wlan.bf.train 】
156:   {"Beam Forming Is InitiatorTXSS       【  wlan.bf.isInit 】
157:   {"Beam Forming Is ResponderTXSS       【  wlan.bf.isResp 】
158:   {"Beam Forming RXSS Length       【  wlan.bf.rxss_len 】
159:   {"Beam Forming RXSS Rate       【  wlan.bf.rxss_rate 】
160:   {"Reserved (B10-B15)       【  wlan.bf.reserved 】
161:   {"Beam Forming Total Number of Sectors       【  wlan.bf.num_sectors 】
162:   {"Beam Forming Number of DMG Antennas       【  wlan.bf.num_dmg_ants 】
163:   {"Reserved (B12-B15)       【  wlan.bf.reserved 】
164:   {"Destination address of STA that caused NAV update       【  wlan.nav_da 】
165:   {"Source address of STA that caused NAV update       【  wlan.nav_sa 】
166:   {"Sector Sweep Feedback       【  wlan.sswf 】
167:   {"Sector Sweep Feedback total number of sectors       【  wlan.sswf.num_sectors 】
168:   {"Sector Sweep Feedback Number of receive DMG Antennas       【  wlan.sswf.num_dmg_ants 】
169:   {"Sector Sweep Feedback Poll required       【  wlan.sswf.poll 】
170:   {"Sector Sweep Feedback Reserved       【  wlan.sswf.reserved 】
171:   {"Sector Sweep Feedback Reserved       【  wlan.sswf.reserved 】
172:   {"Sector Sweep Feedback Sector Select       【  wlan.sswf.sector_select 】
173:   {"Sector Sweep Feedback DMG Antenna Select       【  wlan.sswf.dmg_antenna_select 】
174:   {"Sector Sweep Feedback SNR Report       【  wlan.sswf.snr_report 】
175:   {"BRP Request       【  wlan.brp 】
176:   {"BRP Request L-RX       【  wlan.brp.l_rx 】
177:   {"BRP Request TX-TRN-REQ       【  wlan.brp.tx_trn_req 】
178:   {"BRP Request MID-REQ       【  wlan.brp.mid_req 】
179:   {"BRP Request BC-REQ       【  wlan.brp.bc_req 】
180:   {"BRP Request MID-GRANT       【  wlan.brp.mid_grant 】
181:   {"BRP Request BC-GRANT       【  wlan.brp.bc_grant 】
182:   {"BRP Request Chan FBCK-CAP       【  wlan.brp.chan_fbck_cap 】
183:   {"BRP Request TX Sector ID       【  wlan.brp.tx_sector_id 】
184:   {"BRP Request Other AID       【  wlan.brp.other_aid 】
185:   {"BRP Request TX Antenna ID       【  wlan.brp.tx_antenna_id 】
186:   {"BRP Request Reserved       【  wlan.brp.reserved 】
187:   {"Beamformed Link Maintenance       【  wlan.blm 】
188:   {"BeamLink Maintenance Uint Index       【  wlan.blm.uint_index 】
189:   {"BeamLink Maintenance Value       【  wlan.blm.value 】
190:   {"BeamLink Is Master       【  wlan.blm.is_master 】
191:   {"Beacon Interval Control       【  wlan.bic 】
192:   {"Clustering Control Present       【  wlan.bic.cc 】
193:   {"Discovery Mode       【  wlan.bic.discovery_mode 】
194:   {"Next Beacon       【  wlan.bic.next_beacon 】
195:   {"ATI Present       【  wlan.bic.ati 】
196:   {"A-BFT length       【  wlan.bic.abft_len 】
197:   {"FSS       【  wlan.bic.fss 】
198:   {"Is TXSS Responder       【  wlan.bic.is_responder 】
199:   {"Next A-BFT       【  wlan.bic.next_abft 】
200:   {"Fragmented TXSS       【  wlan.bic.frag_txss 】
201:   {"TXSS span       【  wlan.bic.txss_span 】
202:   {"Number of Beacon Intervals that are needed to allocate A-BFT       【  wlan.bic.NBI_abft 】
203:   {"A-BFT Count       【  wlan.bic.abft_count 】
204:   {"Number of A-BFT's received from each Antenna       【  wlan.bic.nabft 】
205:   {"PCP Association Ready       【  wlan.bic.pcp 】
206:   {"Reserved       【  wlan.bic.reserved 】
207:   {"DMG Parameters       【  wlan.dmg_params 】
208:   {"BSS Type       【  wlan.dmg_params.bss 】
209:   {"CBAP Only       【  wlan.dmg_params.cbap_only 】
210:   {"CBAP Source       【  wlan.dmg_params.cbap_src 】
211:   {"DMG Privacy       【  wlan.dmg_params.privacy 】
212:   {"ECAPC Policy Enforced       【  wlan.dmg_params.policy 】
213:   {"Clustering Control       【  wlan.cc 】
214:   {"A-BFT Responder Address       【  wlan.cc.abft_resp_addr 】
215:   {"Beacon SP Duration       【  wlan.cc.sp_duration 】
216:   {"Cluster ID       【  wlan.cc.cluster_id 】
217:   {"Cluster Member Role       【  wlan.cc.rold 】
218:   {"Cluster MaxMem       【  wlan.cc.max_mem 】
219:   {"Relay Supportability       【  wlan.relay_capabilities.relay_support 】
220:   {"Relay Usability       【  wlan.relay_capabilities.relay_use 】
221:   {"Relay Permission       【  wlan.relay_capabilities.relay_permission 】
222:   {"A/C Power       【  wlan.relay_capabilities.AC_power 】
223:   {"Relay Preference       【  wlan.relay_capabilities.relay_prefer 】
224:   {"Duplex       【  wlan.relay_capabilities.duplex 】
225:   {"Cooperation       【  wlan.relay_capabilities.cooperation 】
226:   {"Relay Capable STA Info       【  wlan.rcsi 】
227:   {"AID       【  wlan.rcsi.aid 】
228:   {"Band ID       【  wlan.band_id 】
229:   {"Move       【  wlan.dmg_bss_param_change.move 】
230:   {"Size       【  wlan.dmg_bss_param_change.size 】
231:   {"TBTT Offset       【  wlan.dmg_bss_param_change.tbtt_offset 】
232:   {"BI Duration       【  wlan.dmg_bss_param_change.bi_duration 】
233:   {"STA Address       【  wlan.dmg_capa.sta_addr 】
234:   {"AID       【  wlan.dmg_capa.aid 】
235:   {"Reverse Direction       【  wlan.dmg_capa.reverse_direction 】
236:   {"Higher Layer Timer Synchronization       【  wlan.dmg_capa.htls 】
237:   {"TPC       【  wlan.dmg_capa.tpc 】
238:   {"SPSH and Interference Mitigation       【  wlan.dmg_capa.spsh 】
239:   {"Number of RX DMG Antennas       【  wlan.dmg_capa.num_rx 】
240:   {"Fast Link Adaptation       【  wlan.dmg_capa.fast_link 】
241:   {"Total Number of Sectors       【  wlan.dmg_capa.num_sectors 】
242:   {"RXSS Length       【  wlan.dmg_capa.rxss_len 】
243:   {"DMG Antenna Reciprocity       【  wlan.dmg_capa.reciprocity 】
244:   {"Maximum A-MPDU Length Exponent       【  wlan.dmg_capa.max_ampdu_exp 】
245:   {"Minimum MPDU Start Spacing       【  wlan.dmg_capa.min_mpdu_spacing 】
246:   {"BA with Flow Control       【  wlan.dmg_capa.bs_flow_ctrl 】
247:   {"Maximum SC Rx MCS       【  wlan.dmg_capa.max_sc_rx_mcs 】
248:   {"Maximum OFDM Rx MCS       【  wlan.dmg_capa.max_ofdm_rx_mcs 】
249:   {"Maximum SC Tx MCS       【  wlan.dmg_capa.max_sc_tx_mcs 】
250:   {"Maximum OFDM Tx MCS       【  wlan.dmg_capa.max_ofdm_tx_mcs 】
251:   {"Low Power SC PHY Supported       【  wlan.dmg_capa.low_power_suuported 】
252:   {"Code Rate 13/16       【  wlan.dmg_capa.code_rate 】
253:   {"DTP Supported       【  wlan.dmg_capa.dtp 】
254:   {"A-PPDU Supported       【  wlan.dmg_capa.appdu_supp 】
255:   {"HeartBeat       【  wlan.dmg_capa.heartbeat 】
256:   {"Supports Other_AID       【  wlan.dmg_capa.other_aid 】
257:   {"Antenna Pattern Reciprocity       【  wlan.dmg_capa.pattern_recip 】
258:   {"Heartbeat Elapsed Indication       【  wlan.dmg_capa.heartbeat_elapsed 】
259:   {"Grant ACK Supported       【  wlan.dmg_capa.grant_ack_supp 】
260:   {"RXSSTxRate Supported       【  wlan.dmg_capa.RXSSTxRate 】
261:   {"TDDTI       【  wlan.dmg_capa.pcp_tdtti 】
262:   {"Pseudo-static Allocations       【  wlan.dmg_capa.pcp_psa 】
263:   {"PDP Handover       【  wlan.dmg_capa.pcp_handover 】
264:   {"Max Associated STA Number       【  wlan.dmg_capa.pcp_max_assoc 】
265:   {"Power Source       【  wlan.dmg_capa.pcp_power_src 】
266:   {"Decentralized PCP/AP Clustering       【  wlan.dmg_capa.pcp_decenter 】
267:   {"PCP Forwarding       【  wlan.dmg_capa.pcp_forwarding 】
268:   {"Centralized PCP/AP Clustering       【  wlan.dmg_capa.pcp_center 】
269:   {"STA Beam Tracking Time Limit       【  wlan.dmg_capa.beam_track 】
270:   {"Extended SC Max Tx MCS Name       【  wlan.dmg_capa.ext_sc_mcs_capa_max_tx 】
271:   {"Extended SC Tx MCS code rate 7/8       【  wlan.dmg_capa.ext_sc_mcs_tx_code_7_8 】
272:   {"Extended SC Max Rx MCS Name       【  wlan.dmg_capa.ext_sc_mcs_capa_max_rx 】
273:   {"Extended SC Rx MCS code rate 7/8       【  wlan.dmg_capa.ext_sc_mcs_rx_code_7_8 】
274:   {"Max Number of Basic Subframes in an A-MSDU       【  wlan.dmg_capa.max_basic_sf_amsdu 】
275:   {"Max Number of short Subframes in an A-MSDU       【  wlan.dmg_capa.max_short_sf_amsdu 】
276:   {"PS Request Suspension Interval       【  wlan.dmg_oper.psrsi 】
277:   {"Min BHI Duration       【  wlan.dmg_oper.min_BHI_duration 】
278:   {"Broadcast STA Info Duration       【  wlan.dmg_oper.brdcst_sta_info_dur 】
279:   {"Associated Response Confirm Time       【  wlan.dmg_oper.assoc_resp_confirm_time 】
280:   {"Min PP Duration       【  wlan.dmg_oper.min_pp_duration 】
281:   {"SP Idle Timeout       【  wlan.dmg_oper.SP_idle_timeout 】
282:   {"Max Lost Beacons       【  wlan.dmg_oper.max_lost_beacons 】
283:   {"Type       【  wlan.sctor_id.type 】
284:   {"Tap 1       【  wlan.sctor_id.tap1 】
285:   {"State 1       【  wlan.sctor_id.state1 】
286:   {"Tap 2       【  wlan.sctor_id.tap2 】
287:   {"State 2       【  wlan.sctor_id.state2 】
288:   {"Allocation ID       【  wlan.ext_sched.alloc_id 】
289:   {"Allocation Type       【  wlan.ext_sched.alloc_type 】
290:   {"Pseudo-static       【  wlan.ext_sched.p_static 】
291:   {"Truncatable       【  wlan.ext_sched.truncatable 】
292:   {"Extenedable       【  wlan.ext_sched.extendable 】
293:   {"PCP Active       【  wlan.ext_sched.pcp_active 】
294:   {"LP SC Used       【  wlan.ext_sched.lp_sc_used 】
295:   {"Source AID       【  wlan.ext_sched.src_id 】
296:   {"Destination AID       【  wlan.ext_sched.dest_id 】
297:   {"Allocation Start       【  wlan.ext_sched.alloc_start 】
298:   {"Allocation Block Duration       【  wlan.ext_sched.block_duration 】
299:   {"Number of Blocks       【  wlan.ext_sched.num_blocks 】
300:   {"Allocation Block Period       【  wlan.ext_sched.alloc_block_period 】
301:   {"AID       【  wlan.sta_avail.aid 】
302:   {"CBAP       【  wlan.sta_avail.cbap 】
303:   {"PP Available       【  wlan.sta_avail.pp_avail 】
304:   {"Start Time       【  wlan.next_ati.start_time 】
305:   {"ATI Duration       【  wlan.next_ati.duration 】
306:   {"Old BSSID       【  wlan.pcp_handover.old_bssid 】
307:   {"New PCP Address       【  wlan.pcp_handover.new_pcp_addr 】
308:   {"BSSID       【  wlan.quiet_res.bssid 】
309:   {"Duplex       【  wlan.relay_capabilities.duplex 】
310:   {"Cooperation       【  wlan.relay_capabilities.cooperation 】
311:   {"TX-Mode       【  wlan.realy_trans_param.tx_mode 】
312:   {"Link Change Interval       【  wlan.realy_trans_param.link_change_interval 】
313:   {"Data Sensing Time       【  wlan.realy_trans_param.data_sensing_time 】
314:   {"First Period       【  wlan.realy_trans_param.first_period 】
315:   {"Second Period       【  wlan.realy_trans_param.second_period 】
316:   {"Initiator       【  wlan.beam_refine.initiator 】
317:   {"TX-train-response       【  wlan.beam_refine.tx_train_res 】
318:   {"RX-train-response       【  wlan.beam_refine.rx_train_res 】
319:   {"TX-TRN-OK       【  wlan.beam_refine.tx_trn_ok 】
320:   {"TXSS-FBCK-REQ       【  wlan.beam_refine.txss_fbck_req 】
321:   {"BS-FBCK       【  wlan.beam_refine.bs_fbck 】
322:   {"BS-FBCK Anetenna ID       【  wlan.beam_refine.bs_fbck_antenna_id 】
323:   {"SNR Requested       【  wlan.beam_refine.snr_req 】
324:   {"Channel Measurement Requested       【  wlan.beam_refine.ch_measure_req 】
325:   {"Number of Taps Requested       【  wlan.beam_refine.taps_req 】
326:   {"Sector ID Order Requested       【  wlan.beam_refine.sector_id_req 】
327:   {"SNR Present       【  wlan.beam_refine.snr_present 】
328:   {"Channel Measurement Present       【  wlan.beam_refine.ch_measure_present 】
329:   {"Tap Delay Present       【  wlan.beam_refine.tap_delay_present 】
330:   {"Number of Taps Present       【  wlan.beam_refine.taps_present 】
331:   {"Number of Measurements       【  wlan.beam_refine.num_measurement 】
332:   {"Sector ID Order Present       【  wlan.beam_refine.sector_id_present 】
333:   {"Number of Beams       【  wlan.beam_refine.num_beams 】
334:   {"MID Extension       【  wlan.beam_refine.mid_ext 】
335:   {"Capability Request       【  wlan.beam_refine.cap_req 】
336:   {"Reserved       【  wlan.beam_refine.reserved 】
337:   {"AID of NextPCP       【  wlan.next_pcp.list 】
338:   {"NextPCP List Token       【  wlan.next_pcp.token 】
339:   {"Remaining BI's       【  wlan.pcp_handover.remaining_BIs 】
340:   {"Request Token       【  wlan.request_token 】
341:   {"BI Start Time       【  wlan.bi_start_time 】
342:   {"Sleep Cycle       【  wlan.sleep_cycle 】
343:   {"Number of Awake/Doze BIs       【  wlan.num_awake_bis 】
344:   {"DMG Action       【  wlan.fixed.dmg_act 】
345:   {"Unprotected DMG Action       【  wlan.fixed.unprotected_dmg_act 】
346:   {"DMG Power Management       【  wlan.dmg.pwr_mgmt 】
347:   {"Subject Address       【  wlan.dmg.subject_addr 】
348:   {"Handover Reason       【  wlan.dmg.handover_reason 】
349:   {"Handover Remaining BI       【  wlan.dmg.handover_remaining_bi 】
350:   {"Handover Result       【  wlan.dmg.handover_result 】
351:   {"Handover Reject Reason       【  wlan.dmg.handover_reject_reason 】
352:   {"Destination REDS AID       【  wlan.dmg.destination_reds_aid 】
353:   {"Destination AID       【  wlan.dmg.destination_aid 】
354:   {"Relay AID       【  wlan.dmg.realy_aid 】
355:   {"Source AID       【  wlan.dmg.source_aid 】
356:   {"Timing Offset       【  wlan.dmg.timing_offset 】
357:   {"Sampling Frequency Offset       【  wlan.dmg.sampling_frequency_offset 】
358:   {"Relay Operation Type       【  wlan.dmg.relay_operation_type 】
359:   {"Peer STA AID       【  wlan.dmg.peer_sta_aid 】
360:   {"SNR       【  wlan.dmg.snr 】
361:   {"Internal Angle       【  wlan.dmg.internal_angle 】
362:   {"Recommend       【  wlan.dmg.recommend 】
363:   {"FST Action Code       【  wlan.fst.action_code 】
364:   {"Link Loss Timeout       【  wlan.fst.llt 】
365:   {"FSTS ID       【  wlan.session_trans.fsts_id 】
366:   {"MMPDU Length       【  wlan.fst.mmpdu_length 】
367:   {"MMPDU Control       【  wlan.fst.mmpdu_ctrl 】
368:   {"OCT MMPDU       【  wlan.fst.oct_mmpdu 】
369:   {"VHT MIMO Control       【  wlan.vht.mimo_control.control 】
370:   {"Nc Index       【  wlan.vht.mimo_control.ncindex 】
371:   {"Nr Index       【  wlan.vht.mimo_control.nrindex 】
372:   {"Channel Width       【  wlan.vht.mimo_control.chanwidth 】
373:   {"Grouping (Ng)       【  wlan.vht.mimo_control.grouping 】
374:   {"Codebook Information       【  wlan.vht.mimo_control.codebookinfo 】
375:   {"Feedback Type       【  wlan.vht.mimo_control.feedbacktype 】
376:   {"Remaining Feedback Segments       【  wlan.vht.mimo_control.remainingfeedbackseg 】
377:   {"First Feedback Segments       【  wlan.vht.mimo_control.firstfeedbackseg 】
378:   {"Reserved       【  wlan.vht.mimo_control.reserved 】
379:   {"Sounding Dialog Token Number       【  wlan.vht.mimo_control.sounding_dialog_tocken_nbr 】
380:   {"VHT Action       【  wlan.vht.action 】
381:   {"VHT Compressed Beamforming Report       【  wlan.vht.compressed_beamforming_report 】
382:   {"VHT MU Exclusive Beamforming Report       【  wlan.vht.exclusive_beamforming_report 】
383:   {"Signal to Noise Ratio (SNR)       【  wlan.vht.compressed_beamforming_report.snr 】
384:   {"PHI       【  wlan.vht.compressed_beamforming_report.phi 】
385:   {"PSI       【  wlan.vht.compressed_beamforming_report.psi 】
386:   {"Compressed Beamforming Feedback Matrix       【  wlan.vht.compressed_beamforming_report.feedback_matrix 】
387:   {"Delta SNR for space-time stream Nc for subcarrier k       【  wlan.vht.exclusive_beamforming_report.delta_snr 】
388:   {"Group ID Management       【  wlan.vht.group_id_management 】
389:   {"Membership Status Array       【  wlan.vht.membership_status_array 】
390:   {"User Position Array       【  wlan.vht.user_position_array 】
391:   {"Membership Status Field       【  wlan.vht.membership_status_array.field 】
392:   {"User Position Field       【  wlan.vht.user_position_array.field 】
393:   {"Operation Mode Notification       【  wlan.vht.operation_mode_notification 】
394:   {"HE Action       【  wlan.he.action 】
395:   {"Protected HE Action       【  wlan.he.protected_action 】
396:   {"Nc Index       【  wlan.he.mimo.nc_index 】
397:   {"Nr Index       【  wlan.he.mimo.nr_index 】
398:   {"BW       【  wlan.he.mimo.bw 】
399:   {"Grouping       【  wlan.he.mimo.grouping 】
400:   {"Codebook Information       【  wlan.he.mimo.codebook_info 】
401:   {"Feedback Type       【  wlan.he.mimo.feedback_type 】
402:   {"Remaining Feedback Segments       【  wlan.he.mimo.remaining_feedback_segs 】
403:   {"First Feedback Segment       【  wlan.he.mimo.first_feedback_seg 】
404:   {"RU Start Index       【  wlan.he.mimo.ru_start_index 】
405:   {"RU End Index       【  wlan.he.mimo.ru_end_index 】
406:   {"Sounding Dialog Token Number       【  wlan.he.mimo.sounding_dialog_token_num 】
407:   {"Reserved       【  wlan.he.mimo.reserved 】
408:   {"HE MIMO Control       【  wlan.he.action.he_mimo_control 】
409:   {"AgvSNR       【  wlan.he.mimo.beamforming_report.avgsnr 】
410:   {"SCIDX       【  wlan.he.action.he_mimo_control.scidx 】
411:   {"Report Len       【  wlan.he.action.he_mimo_control.report_len 】
412:   {"Allocation ID       【  wlan.dmg_tspec.allocation_id 】
413:   {"Allocation Type       【  wlan.dmg_tspec.allocation_type 】
414:   {"Allocation Format       【  wlan.dmg_tspec.allocation_format 】
415:   {"Pseudo Static       【  wlan.dmg_tspec.pseudo_static 】
416:   {"Truncatable       【  wlan.dmg_tspec.truncatable 】
417:   {"Extendable       【  wlan.dmg_tspec.extendable 】
418:   {"LP SC Usec       【  wlan.dmg_tspec.lp_sc_used 】
419:   {"UP       【  wlan.dmg_tspec.up 】
420:   {"Destination AID       【  wlan.dmg_tspec.dest_aid 】
421:   {"Allocation Period       【  wlan.dmg_tspec.allocation_period 】
422:   {"Minimal Allocation       【  wlan.dmg_tspec.min_allocation 】
423:   {"Maximal Allocation       【  wlan.dmg_tspec.max_allocation 】
424:   {"Minimal Duration       【  wlan.dmg_tspec.min_duration 】
425:   {"Number Of Constraints       【  wlan.dmg_tspec.num_of_constraints 】
426:   {"TS Constraint Start Time       【  wlan.dmg_tspec.tsconst.start_time 】
427:   {"TS Constraint Duration       【  wlan.dmg_tspec.tsconst.duration 】
428:   {"TS Constraint Period       【  wlan.dmg_tspec.tsconst.period 】
429:   {"TS Constraint Interferer MAC Address       【  wlan.dmg_tspec.tsconst.interferer_mac 】
430:   {"Channel Measurement Feedback Relative I       【  wlan.ch_meas_fb.realtive_I 】
431:   {"Channel Measurement Feedback Relative Q       【  wlan.ch_meas_fb.realtive_Q 】
432:   {"Channel Measurement Feedback Tap Delay       【  wlan.ch_meas_fb.tap_delay 】
433:   {"Channel Measurement Feedback Secotr ID       【  wlan.ch_meas_fb.sector_id 】
434:   {"Channel Measurement Feedback Antenna ID       【  wlan.ch_meas_fb.antenna_id 】
435:   {"Awake Window       【  wlan.awake_window 】
436:   {"ADDBA No Fragmentation       【  wlan.addba.no_frag 】
437:   {"ADDBA HE Fragmentation Operation       【  wlan.addba.he_frag_oper 】
438:   {"Reserved       【  wlan.addba.he_frag_oper 】
439:   {"STA Rold       【  wlan.multi_band.ctrl_sta_role 】
440:   {"STA MAC Address Present       【  wlan.multi_band.ctrl_addr_present 】
441:   {"PCS Present       【  wlan.multi_band.ctrl_cipher_present 】
442:   {"Operating Class       【  wlan.multi_band.oper_class 】
443:   {"Channel Number       【  wlan.multi_band.channel_number 】
444:   {"TSF Offset       【  wlan.multi_band.tsf_offset 】
445:   {"Connection Capability AP       【  wlan.multi_band.conn_ap 】
446:   {"Connection Capability PCP       【  wlan.multi_band.conn_pcp 】
447:   {"Connection Capability DLS       【  wlan.multi_band.conn_dls 】
448:   {"Connection Capability TDLS       【  wlan.multi_band.conn_tdls 】
449:   {"Connection Capability IBSS       【  wlan.multi_band.conn_ibss 】
450:   {"FST Session Timeout       【  wlan.multi_band.fst_timeout 】
451:   {"Transmitting STA MAC Address       【  wlan.multi_band.sta_mac 】
452:   {"Activity       【  wlan.activity 】
453:   {"MCS       【  wlan.dmg_link_adapt.mcs 】
454:   {"Link Margin       【  wlan.dmg_link_adapt.link_margin 】
455:   {"Reference Timestamp       【  wlan.ref_timestamp 】
456:   {"Non-Qos Data Frames       【  wlan.switching_stream.non_qos 】
457:   {"Number Of Switching Stream Elements       【  wlan.switching_stream.param_num 】
458:   {"Old Band TID       【  wlan.switching_stream.old_tid 】
459:   {"Old Band Direction       【  wlan.switching_stream.old_direction 】
460:   {"New Band TID       【  wlan.switching_stream.new_tid 】
461:   {"New Band Direction       【  wlan.switching_stream.new_direction 】
462:   {"Stream ID in New Band Valid       【  wlan.switching_stream.new_valid_id 】
463:   {"LLT Type       【  wlan.switching_stream.llt_type 】
464:   {"Timestamp       【  wlan.fixed.timestamp 】
465:   {"Authentication Algorithm       【  wlan.fixed.auth.alg 】
466:   {"Beacon Interval       【  wlan.fixed.beacon 】
467:   {"Fixed parameters       【  wlan.fixed.all 】
468:   {"Tagged parameters       【  wlan.tagged.all 】
469:   {"SSID       【  wlan.ssid 】
470:   {"Supported Rates       【  wlan.supported_rates 】
471:   {"Dwell Time       【  wlan.fh.dwell_time 】
472:   {"Hop Set       【  wlan.fh.hop_set 】
473:   {"Hop Pattern       【  wlan.fh.hop_pattern 】
474:   {"Hop Index       【  wlan.fh.hop_index 】
475:   {"Block Ack Parameters       【  wlan.fixed.baparams 】
476:   {"A-MSDUs       【  wlan.fixed.baparams.amsdu 】
477:   {"Block Ack Policy       【  wlan.fixed.baparams.policy 】
478:   {"Traffic Identifier       【  wlan.fixed.baparams.tid 】
479:   {"Number of Buffers (1 Buffer = 2304 Bytes)       【  wlan.fixed.baparams.buffersize 】
480:   {"Block Ack Timeout       【  wlan.fixed.batimeout 】
481:   {"Block Ack Starting Sequence Control (SSC)       【  wlan.fixed.ssc 】
482:   {"Fragment       【  wlan.fixed.ssc.fragment 】
483:   {"Starting Sequence Number       【  wlan.fixed.ssc.sequence 】
484:   {"Delete Block Ack (DELBA) Parameter Set       【  wlan.fixed.delba.param 】
485:   {"Reserved       【  wlan.fixed.delba.param.reserved 】
486:   {"Initiator       【  wlan.fixed.delba.param.initiator 】
487:   {"TID       【  wlan.fixed.delba.param.tid 】
488:   {"Maximum Regulation Power       【  wlan.fixed.maxregpwr 】
489:   {"Measurement Pilot Interval       【  wlan.fixed.msmtpilotint 】
490:   {"Country String       【  wlan.fixed.country 】
491:   {"Maximum Transmit Power       【  wlan.fixed.maxtxpwr 】
492:   {"Transmit Power Used       【  wlan.fixed.txpwr 】
493:   {"Transceiver Noise Floor       【  wlan.fixed.tnoisefloor 】
494:   {"Supported Channel Width       【  wlan.fixed.chanwidth 】
495:   {"QoS Information (AP)       【  wlan.fixed.qosinfo.ap 】
496:   {"EDCA Parameter Set Update Count       【  wlan.fixed.qosinfo.ap.edcaupdate 】
497:   {"Q-Ack       【  wlan.fixed.qosinfo.ap.qack 】
498:   {"Queue Request       【  wlan.fixed.qosinfo.ap.queue_req 】
499:   {"TXOP Request       【  wlan.fixed.qosinfo.ap.txopreq 】
500:   {"Reserved       【  wlan.fixed.qosinfo.ap.reserved 】
501:   {"QoS Information (STA)       【  wlan.fixed.qosinfo.sta 】
502:   {"AC_VO U-APSD Flag       【  wlan.fixed.qosinfo.sta.ac_vo 】
503:   {"AC_VI U-APSD Flag       【  wlan.fixed.qosinfo.sta.ac_vi 】
504:   {"AC_BK U-APSD Flag       【  wlan.fixed.qosinfo.sta.ac_bk 】
505:   {"AC_BE U-APSD Flag       【  wlan.fixed.qosinfo.sta.ac_be 】
506:   {"Q-Ack       【  wlan.fixed.qosinfo.sta.qack 】
507:   {"Max SP Length       【  wlan.fixed.qosinfo.sta.max_sp_length 】
508:   {"More Data Ack       【  wlan.fixed.qosinfo.sta.more_data_ack 】
509:   {"Spatial Multiplexing (SM) Power Control       【  wlan.fixed.sm.powercontrol 】
510:   {"SM Power Save       【  wlan.fixed.sm.powercontrol.enabled 】
511:   {"SM Mode       【  wlan.fixed.sm.powercontrol.mode 】
512:   {"Reserved       【  wlan.fixed.sm.powercontrol.reserved 】
513:   {"Phased Coexistence Operation (PCO) Phase Control       【  wlan.fixed.pco.phasecntrl 】
514:   {"Power Save Multi-Poll (PSMP) Parameter Set       【  wlan.fixed.psmp.paramset 】
515:   {"Number of STA Info Fields Present       【  wlan.fixed.psmp.paramset.nsta 】
516:   {"More PSMP       【  wlan.fixed.psmp.paramset.more 】
517:   {"PSMP Sequence Duration [us]       【  wlan.fixed.psmp.paramset.seqduration 】
518:   {"MIMO Control       【  wlan.fixed.mimo.control 】
519:   {"Nc Index       【  wlan.fixed.mimo.control.ncindex 】
520:   {"Nr Index       【  wlan.fixed.mimo.control.nrindex 】
521:   {"Channel Width       【  wlan.fixed.mimo.control.chanwidth 】
522:   {"Grouping (Ng)       【  wlan.fixed.mimo.control.grouping 】
523:   {"Coefficient Size (Nb)       【  wlan.fixed.mimo.control.cosize 】
524:   {"Codebook Information       【  wlan.fixed.mimo.control.codebookinfo 】
525:   {"Remaining Matrix Segment       【  wlan.fixed.mimo.control.matrixseg 】
526:   {"Reserved       【  wlan.fixed.mimo.control.reserved 】
527:   {"Sounding Timestamp       【  wlan.fixed.mimo.control.soundingtime 】
528:   {"Power Save Multi-Poll (PSMP) Station Information       【  wlan.fixed.psmp.stainfo 】
529:   {"Sta Info Type       【  wlan.fixed.psmp.stainfo.type 】
530:   {"DTT Start Offset       【  wlan.fixed.psmp.stainfo.dttstart 】
531:   {"DTT Duration       【  wlan.fixed.psmp.stainfo.dttduration 】
532:   {"Target Station ID       【  wlan.fixed.psmp.stainfo.staid 】
533:   {"UTT Start Offset       【  wlan.fixed.psmp.stainfo.uttstart 】
534:   {"UTT Duration       【  wlan.fixed.psmp.stainfo.uttduration 】
535:   {"Reserved       【  wlan.fixed.psmp.stainfo.reserved 】
536:   {"Reserved       【  wlan.fixed.psmp.stainfo.reserved64 】
537:   {"Power Save Multi-Poll (PSMP) Multicast ID       【  wlan.fixed.psmp.stainfo.multicastid 】
538:   {"Antenna Selection       【  wlan.fixed.antsel 】
539:   {"Antenna 0       【  wlan.fixed.antsel.ant0 】
540:   {"Antenna 1       【  wlan.fixed.antsel.ant1 】
541:   {"Antenna 2       【  wlan.fixed.antsel.ant2 】
542:   {"Antenna 3       【  wlan.fixed.antsel.ant3 】
543:   {"Antenna 4       【  wlan.fixed.antsel.ant4 】
544:   {"Antenna 5       【  wlan.fixed.antsel.ant5 】
545:   {"Antenna 6       【  wlan.fixed.antsel.ant6 】
546:   {"Antenna 7       【  wlan.fixed.antsel.ant7 】
547:   {"Extended Channel Switch Announcement       【  wlan.fixed.extchansw 】
548:   {"Channel Switch Mode       【  wlan.fixed.extchansw.switchmode 】
549:   {"New Operating Class       【  wlan.fixed.extchansw.new.opeclass 】
550:   {"New Channel Number       【  wlan.fixed.extchansw.new.channumber 】
551:   {"Channel Switch Count       【  wlan.extchanswitch.switchcount 】
552:   {"HT Information       【  wlan.fixed.extchansw 】
553:   {"Information Request       【  wlan.fixed.mimo.control.chanwidth 】
554:   {"40 MHz Intolerant       【  wlan.fixed.mimo.control.chanwidth 】
555:   {"Station Channel Width       【  wlan.fixed.mimo.control.chanwidth 】
556:   {"Reserved       【  wlan.fixed.extchansw 】
557:   {"HT Action       【  wlan.fixed.htact 】
558:   {"Signal to Noise Ratio (SNR)       【  wlan.mimo.csimatrices.snr 】
559:   {"CSI Matrices       【  wlan.mimo.csimatrices 】
560:   {"Beamforming Feedback Matrices       【  wlan.mimo.csimatrices.bf 】
561:   {"Compressed Beamforming Feedback Matrices       【  wlan.mimo.csimatrices.cbf 】
562:   {"Public Action       【  wlan.fixed.publicact 】
563:   {"Protected Public Action       【  wlan.fixed.publicact 】
564:   {"Capabilities Information       【  wlan.fixed.capabilities 】
565:   {"ESS capabilities       【  wlan.fixed.capabilities.ess 】
566:   {"IBSS status       【  wlan.fixed.capabilities.ibss 】
567:   {"CFP participation capabilities       【  wlan.fixed.capabilities.cfpoll.sta 】
568:   {"CFP participation capabilities       【  wlan.fixed.capabilities.cfpoll.ap 】
569:   {"Privacy       【  wlan.fixed.capabilities.privacy 】
570:   {"Short Preamble       【  wlan.fixed.capabilities.preamble 】
571:   {"PBCC       【  wlan.fixed.capabilities.pbcc 】
572:   {"Channel Agility       【  wlan.fixed.capabilities.agility 】
573:   {"Spectrum Management       【  wlan.fixed.capabilities.spec_man 】
574:   {"Short Slot Time       【  wlan.fixed.capabilities.short_slot_time 】
575:   {"Automatic Power Save Delivery       【  wlan.fixed.capabilities.apsd 】
576:   {"Radio Measurement       【  wlan.fixed.capabilities.radio_measurement 】
577:   {"DSSS-OFDM       【  wlan.fixed.capabilities.dsss_ofdm 】
578:   {"Delayed Block Ack       【  wlan.fixed.capabilities.del_blk_ack 】
579:   {"Immediate Block Ack       【  wlan.fixed.capabilities.imm_blk_ack 】
580:   {"Authentication SEQ       【  wlan.fixed.auth_seq 】
581:   {"Association ID       【  wlan.fixed.aid 】
582:   {"Listen Interval       【  wlan.fixed.listen_ival 】
583:   {"Current AP       【  wlan.fixed.current_ap 】
584:   {"Reason code       【  wlan.fixed.reason_code 】
585:   {"Status code       【  wlan.fixed.status_code 】
586:   {"Category code       【  wlan.fixed.category_code 】
587:   {"Action code       【  wlan.fixed.action_code 】
588:   {"Dialog token       【  wlan.fixed.dialog_token 】
589:   {"Followup Dialog token       【  wlan.fixed.followup_dialog_token 】
590:   {"Marvell Action type       【  wlan.fixed.mrvl_action_type 】
591:   {"Mesh action(Marvell)       【  wlan.fixed.mrvl_mesh_action 】
592:   {"Message Length       【  wlan.fixed.length 】
593:   {"Message Mode       【  wlan.fixed.mode 】
594:   {"Message TTL       【  wlan.fixed.ttl 】
595:   {"Destination Count       【  wlan.fixed.dstcount 】
596:   {"Hop Count       【  wlan.fixed.hopcount 】
597:   {"RREQ ID       【  wlan.fixed.rreqid 】
598:   {"Source Address       【  wlan.fixed.sa 】
599:   {"SSN       【  wlan.fixed.ssn 】
600:   {"Metric       【  wlan.fixed.metric 】
601:   {"RREQ Flags       【  wlan.fixed.hopcount 】
602:   {"Destination Address       【  wlan.fixed.da 】
603:   {"DSN       【  wlan.fixed.dsn 】
604:   {"Lifetime       【  wlan.fixed.lifetime 】
605:   {"Action code       【  wlan.fixed.action_code 】
606:   {"Status code       【  wlan.fixed.status_code 】
607:   {"Mesh Action code       【  wlan.fixed.mesh_action 】
608:   {"Multihop Action code       【  wlan.fixed.multihop_action 】
609:   {"Mesh Flags       【  wlan.fixed.mesh_flags 】
610:   {"Mesh TTL       【  wlan.fixed.mesh_ttl 】
611:   {"Sequence Number       【  wlan.fixed.mesh_sequence 】
612:   {"Mesh Extended Address 4       【  wlan.fixed.mesh_addr4 】
613:   {"Mesh Extended Address 5       【  wlan.fixed.mesh_addr5 】
614:   {"Mesh Extended Address 6       【  wlan.fixed.mesh_addr6 】
615:   {"Self-protected Action code       【  wlan.fixed.selfprot_action 】
616:   {"Mesh Peering Protocol ID       【  wlan.peering.proto 】
617:   {"Local Link ID       【  wlan.peering.local_id 】
618:   {"Peer Link ID       【  wlan.peering.peer_id 】
619:   {"HWMP Flags       【  wlan.hwmp.flags 】
620:   {"HWMP Hop Count       【  wlan.hwmp.hopcount 】
621:   {"HWMP TTL       【  wlan.hwmp.ttl 】
622:   {"HWMP Path Discovery ID       【  wlan.hwmp.pdid 】
623:   {"Originator STA Address       【  wlan.hwmp.orig_sta 】
624:   {"HWMP Originator Sequence Number       【  wlan.hwmp.orig_sn 】
625:   {"Originator External Address       【  wlan.hwmp.orig_ext 】
626:   {"HWMP Lifetime       【  wlan.hwmp.lifetime 】
627:   {"HWMP Metric       【  wlan.hwmp.metric 】
628:   {"HWMP Target Count       【  wlan.hwmp.targ_count 】
629:   {"HWMP Per-Target Flags       【  wlan.hwmp.targ_flags 】
630:   {"TO Flag       【  wlan.hwmp.to_flag 】
631:   {"USN Flag       【  wlan.hwmp.usn_flag 】
632:   {"Target STA Address       【  wlan.hwmp.targ_sta 】
633:   {"Target External Address       【  wlan.hwmp.targ_ext 】
634:   {"Target HWMP Sequence Number       【  wlan.hwmp.targ_sn 】
635:   {"Path Selection Protocol       【  wlan.mesh.config.ps_protocol 】
636:   {"Path Selection Metric       【  wlan.mesh.config.ps_metric 】
637:   {"Congestion Control       【  wlan.mesh.config.cong_ctl 】
638:   {"Synchronization Method       【  wlan.mesh.config.sync_method 】
639:   {"Authentication Protocol       【  wlan.mesh.config.auth_protocol 】
640:   {"Formation Info       【  wlan.mesh.config.formation_info 】
641:   {"Number of Peerings       【  wlan.mesh.config.formation_info.num_peers 】
642:   {"Capability       【  wlan.mesh.config.cap 】
643:   {"Accepting Additional Mesh Peerings       【  wlan.mesh.config.cap.accept 】
644:   {"MCCA Support       【  wlan.mesh.config.cap.mcca_support 】
645:   {"MCCA Enabled       【  wlan.mesh.config.cap.mcca_enabled 】
646:   {"Mesh Forwarding       【  wlan.mesh.config.cap.forwarding 】
647:   {"MBCA Enabled       【  wlan.mesh.config.cap.mbca_enabled 】
648:   {"TBTT Adjustment       【  wlan.mesh.config.cap.tbtt_adjusting 】
649:   {"Power Save       【  wlan.mesh.config.cap.power_save_level 】
650:   {"Mesh ID       【  wlan.mesh.id 】
651:   {"RANN Flags       【  wlan.rann.flags 】
652:   {"Root STA Address       【  wlan.rann.root_sta 】 FT_ETHER, BASE_NONE, NULL, 0,
653:   {"Root STA Sequence Number       【  wlan.rann.rann_sn 】
654:   {"RANN Interval       【  wlan.rann.interval 】
655:   {"Action code       【  wlan.fixed.action_code 】
656:   {"Action code       【  wlan.fixed.action_code 】
657:   {"Check Beacon       【  wlan.fixed.check_beacon 】
658:   {"TOD       【  wlan.fixed.tod 】
659:   {"TOA       【  wlan.fixed.toa 】
660:   {"MAX TOD ERROR       【  wlan.fixed.max_tod_err 】
661:   {"MAX TOA ERROR       【  wlan.fixed.max_toa_err 】
662:   {"Action code       【  wlan.fixed.action_code 】
663:   {"Destination address       【  wlan.fixed.dst_mac_addr 】
664:   {"Source address       【  wlan.fixed.src_mac_addr 】
665:   {"RequesterAP address       【  wlan.fixed.req_ap_addr 】
666:   {"ResponderAP address       【  wlan.fixed.res_ap_addr 】
667:   {"Action code       【  wlan.fixed.action_code 】
668:   {"STA Address       【  wlan.fixed.sta_address 】
669:   {"Target AP Address       【  wlan.fixed.target_ap_address 】
670:   {"GAS Comeback Delay       【  wlan.fixed.gas_comeback_delay 】
671:   {"GAS Query Response Fragment ID       【  wlan.fixed.gas_fragment_id 】
672:   {"More GAS Fragments       【  wlan.fixed.more_gas_fragments 】
673:   {"Query Request Length       【  wlan.fixed.query_request_length 】
674:   {"Query Request       【  wlan.fixed.query_request 】
675:   {"Query Response Length       【  wlan.fixed.query_response_length 】
676:   {"Query Response       【  wlan.fixed.query_response 】
677:   {"GAS Query Response fragments       【  wlan.fixed.fragments 】
678:   {"GAS Query Response fragment       【  wlan.fixed.fragment 】
679:   {"GAS Query Response fragment overlap       【  wlan.fixed.fragment.overlap 】
680:   {"GAS Query Response fragment overlapping with conflicting data       【  wlan.fixed.fragment.overlap.conflicts 】
681:   {"GAS Query Response has multiple tail fragments 】{       【  wlan.fixed.fragment.multiple_tails 】
682:   {"GAS Query Response fragment too long       【  wlan.fixed.fragment.too_long_fragment 】
683:   {"GAS Query Response reassembly error       【  wlan.fixed.fragment.error 】
684:   {"GAS Query Response fragment count       【  wlan.fixed.fragment.count 】
685:   {"Reassembled in       【  wlan.fixed.reassembled.in 】
686:   {"Reassembled length       【  wlan.fixed.reassembled.length 】
687:   {"Info ID       【  wlan.fixed.anqp.info_id 】
688:   {"Length       【  wlan.fixed.anqp.info_length 】
689:   {"Information       【  wlan.fixed.anqp.info 】
690:   {"ANQP Query ID       【  wlan.fixed.anqp.query_id 】
691:   {"ANQP Capability       【  wlan.fixed.anqp.capability 】
692:   {"Vendor-specific Capability Length       【  wlan.fixed.anqp.capability_vlen 】
693:   {"Vendor-specific Capability       【  wlan.fixed.anqp.capability_vendor 】
694:   {"Venue Group       【  wlan.fixed.venue_info.group 】
695:   {"Venue Type       【  wlan.fixed.venue_info.type 】
696:   {"Venue Name Duple Length       【  wlan.fixed.anqp.venue.length 】
697:   {"Language Code       【  wlan.fixed.anqp.venue.language 】
698:   {"Venue Name       【  wlan.fixed.anqp.venue.name 】
699:   {"Network Authentication Type Indicator       【  wlan.fixed.anqp.nw_auth_type.indicator 】
700:   {"Re-direct URL Length       【  wlan.fixed.anqp.nw_auth_type.url_len 】
701:   {"Re-direct URL       【  wlan.fixed.anqp.nw_auth_type_url 】
702:   {"OI Length       【  wlan.fixed.anqp.roaming_consortium.oi_len 】
703:   {"OI       【  wlan.fixed.anqp.roaming_consortium.oi 】
704:   {"IPv6 Address       【  wlan.fixed.anqp.ip_addr_availability.ipv6 】
705:   {"IPv4 Address       【  wlan.fixed.anqp.ip_addr_availability.ipv4 】
706:   {"NAI Realm Count       【  wlan.fixed.anqp.nai_realm_list.count 】
707:   {"NAI Realm Data Field Length       【  wlan.fixed.anqp.nai_realm_list.field_len 】
708:   {"NAI Realm Encoding       【  wlan.fixed.naqp_nai_realm_list.encoding 】
709:   {"NAI Realm Length       【  wlan.fixed.naqp_nai_realm_list.realm_length 】
710:   {"NAI Realm       【  wlan.fixed.naqp_nai_realm_list.realm 】
711:   {"EAP Method Count       【  wlan.fixed.naqp_nai_realm_list.eap_method_count 】
712:   {"EAP Method subfield Length       【  wlan.fixed.naqp_nai_realm_list.eap_method_len 】
713:   {"EAP Method       【  wlan.fixed.naqp_nai_realm_list.eap_method 】
714:   {"Authentication Parameter Count       【  wlan.fixed.naqp_nai_realm_list.auth_param_count 】
715:   {"Authentication Parameter ID       【  wlan.fixed.naqp_nai_realm_list.auth_param_id 】
716:   {"Authentication Parameter Length       【  wlan.fixed.naqp_nai_realm_list.auth_param_len 】
717:   {"Authentication Parameter Value       【  wlan.fixed.naqp_nai_realm_list.auth_param_value 】
718:   {"GUD       【  wlan.fixed.anqp.3gpp_cellular_info.gud 】
719:   {"UDHL       【  wlan.fixed.anqp.3gpp_cellular_info.udhl 】
720:   {"IEI       【  wlan.fixed.anqp.3gpp_cellular_info.iei 】
721:   {"PLMN Length       【  wlan.fixed.anqp.3gpp_cellular_info.plmn_len 】
722:   {"Number of PLMNs       【  wlan.fixed.anqp.3gpp_cellular_info.num_plmns 】
723:   {"PLMN       【  wlan.fixed.anqp.3gpp_cellular_info.plmn_info 】
724:   {"Domain Name Length       【  wlan.fixed.anqp.domain_name_list.len 】
725:   {"Domain Name       【  wlan.fixed.anqp.domain_name_list.name 】
726:   {"DLS timeout       【  wlan.fixed.dls_timeout 】
727:   {"Action code       【  wlan.fixed.action_code 】
728:   {"Transaction Id       【  wlan.fixed.transaction_id 】
729:   {"Send-Confirm       【  wlan.fixed.send_confirm 】
730:   {"Anti-Clogging Token       【  wlan.fixed.anti_clogging_token 】
731:   {"Scalar       【  wlan.fixed.scalar 】
732:   {"Finite Field Element       【  wlan.fixed.finite_field_element 】
733:   {"Confirm       【  wlan.fixed.confirm 】
734:   {"Group Id       【  wlan.fixed.finite_cyclic_group 】
735:   {"SAE Message Type       【  wlan.fixed.sae_message_type 】
736:   {"WFA Subtype       【  wlan.anqp.wfa.subtype 】
737:   {"DPP Subtype       【  wlan.wfa.dpp.subtype 】
738:   {"DGAF Disabled       【  wlan.hs20.indication.dgaf_disabled 】
739:   {"PPS MO ID Present       【  wlan.hs20.indication.pps_mo_id_present 】
740:   {"ANQP Domain ID Present       【  wlan.hs20.indication.anqp_domain_id_present 】
741:      {"Reserved       【  wlan.hs20.indication.reserved 】
742:   {"Release Number       【  wlan.hs20.indication.release_number 】
743:   {"PPS MO ID       【  wlan.hs20.indication.pps_mo_id 】
744:   {"ANQP Domain ID       【  wlan.hs20.indication.domain_id 】
745:   {"Subtype       【  wlan.hs20.anqp.subtype 】
746:   {"Reserved       【  wlan.hs20.anqp.reserved 】
747:   {"Payload       【  wlan.hs20.anqp.payload 】
748:   {"Queried Subtype       【  wlan.hs20.anqp.hs_query_list 】
749:   {"Capability       【  wlan.hs20.anqp.hs_capability_list 】
750:   {"Length       【  wlan.hs20.anqp.ofn.length 】
751:   {"Language Code       【  wlan.hs20.anqp.ofn.language 】
752:   {"Operator Friendly Name       【  wlan.hs20.anqp.ofn.name 】
753:   {"Link Status       【  wlan.hs20.anqp.wan_metrics.link_status 】
754:   {"Symmetric Link       【  wlan.hs20.anqp.wan_metrics.symmetric_link 】
755:   {"At Capacity       【  wlan.hs20.anqp.wan_metrics.at_capacity 】
756:   {"Reserved       【  wlan.hs20.anqp.wan_metrics.reserved 】
757:   {"Downlink Speed       【  wlan.hs20.anqp.wan_metrics.downlink_speed 】
758:   {"Uplink Speed       【  wlan.hs20.anqp.wan_metrics.uplink_speed 】
759:   {"Downlink Load       【  wlan.hs20.anqp.wan_metrics.downlink_load 】
760:   {"Uplink Load       【  wlan.hs20.anqp.wan_metrics.uplink_load 】
761:   {"LMD       【  wlan.hs20.anqp.wan_metrics.lmd 】
762:   {"IP Protocol       【  wlan.hs20.anqp.cc.ip_proto 】
763:   {"Port Number       【  wlan.hs20.anqp.cc.port_num 】
764:   {"Status       【  wlan.hs20.anqp.cc.status 】
765:   {"NAI Home Realm Count       【  wlan.hs20.anqp.nai_hrq.count 】
766:      {      【  wlan.hs20.anqp.nai_hrq.encoding_type 】
767:   {"NAI Home Realm Name Length       【  wlan.hs20.anqp.nai_hrq.length 】
768:   {"NAI Home Realm Name       【  wlan.hs20.anqp.nai_hrq.name 】
769:   {"Operating Class       【  wlan.hs20.anqp.oper_class_indic.oper_class 】
770:   {"OSU Friendly Name Length       【  wlan.hs20.osu_friendly_names_len 】
771:   {"Length       【  wlan.hs20.osu_friendly_name.len 】
772:   {"Language Code       【  wlan.hs20.osu_friendly_name.language 】
773:   {"OSU Friendly Name       【  wlan.hs20.osu_friendly_name.name 】
774:   {"OSU Server URI Length       【  wlan.hs20.osu_server_uri_len 】
775:   {"OSU Server URI       【  wlan.hs20.osu_server_uri 】
776:   {"OSU Method List Length       【  wlan.hs20.osu_method_list_len 】
777:   {"OSU Method       【  wlan.hs20.osu_method_list.method 】
778:   {"Icons Available Length       【  wlan.hs20.osu_icons_avail_len 】
779:   {"SSID Length       【  wlan.hs20.anqp_osu_prov_list.ssid_len 】
780:   {"SSID       【  wlan.hs20.anqp_osu_prov_list.ssid 】
781:   {"Number of OSU Providers       【  wlan.hs20.anqp_osu_prov_list.number 】
782:   {"OSU Provider Length       【  wlan.hs20.anqp_osu_prov.len 】
783:   {"Icon Filename       【  wlan.hs20.anqp_icon_request.icon_filename 】
784:   {"Icon Width       【  wlan.hs20.osu_icons_avail.icon_width 】
785:   {"Icon Height       【  wlan.hs20.osu_icons_avail.icon_height 】
786:   {"Language Code       【  wlan.hs20.osu_icons_avail.lang_code 】
787:   {"Icon Type Length       【  wlan.hs20.osu_icons_avail.icon_type_len 】
788:   {"Icon Type       【  wlan.hs20.osu_icons_avail.icon_type 】
789:   {"Icon Filename Length       【  wlan.hs20.osu_icons_avail.icon_filename_len 】
790:   {"Icon Filename       【  wlan.hs20.osu_icons_avail.icon_filename 】
791:   {"OSU_NAI Length       【  wlan.hs20.osu_nai.len 】
792:   {"OSU_NAI       【  wlan.hs20.osu_nai 】
793:   {"OSU Service Desctription Length       【  wlan.hs20.osu_service_desc_len 】
794:   {"Length       【  wlan.hs20.osu_service_desc.duple.len 】
795:   {"Language Code       【  wlan.hs20.osu_service_desc.duple.lang 】
796:   {"OSU Service Description       【  wlan.hs20.osu_service_desc.duple.desc 】
797:   {"Download Status Code       【  wlan.hs20.anqp_icon_request.download_status 】
798:   {"Icon Type Length       【  wlan.hs20.anqp_icon_request.icon_type_len 】
799:   {"Icon Type       【  wlan.hs20.anqp_icon_request.icon_type 】
800:   {"Icon Binary Data Length       【  wlan.anqp_icon_request.icon_binary_data_len 】
801:   {"Icon Binary Data       【  wlan.h220.anqp_icon_request.icon_binary_data 】
802:   {"Server URL Length       【  wlan.hs20.subs_remediation.server_url_len 】
803:   {"Server URL       【  wlan.hs20.subs_remediation.server_url 】
804:   {"Server Method       【  wlan.hs20.subs_remediation.server_method 】
805:   {"De-Auth Reason Code       【  wlan.hs20.deauth.reason_code 】
806:   {"Re-Auth Delay       【  wlan.hs20.deauth.reauth_delay 】
807:   {"Reason URL Length       【  wlan.hs20.deauth.reason_url_len 】
808:   {"Reason URL       【  wlan.hs20.deauth.reason_url 】
809:   {"Length       【  wlan.hs20.venue_url.len 】
810:   {"Venue number       【  wlan.hs20.venue_url.venue_num 】
811:   {"Venue URL       【  wlan.hs20.venue_url.url 】
812:   {"Length       【  wlan.hs20.advice_of_charge.len 】
813:   {"Advice of Charge Type       【  wlan.hs20.advice_of_charge.type 】
814:   {"NAI Realm Encoding       【  wlan.hs20.advice_of_charge.nai_realm_enc 】
815:   {"NAI Realm Length       【  wlan.hs20.advice_of_charge.nai_realm_len 】
816:   {"NAI Realm       【  wlan.hs20.advice_of_charge.nai_realm 】
817:   {"Plan length       【  wlan.hs20.advice_of_charge.plan_info_tuples.plan_len 】
818:   {"Plan language       【  wlan.hs20.advice_of_charge.plan_info_tuples.plan_lang 】
819:   {"Plan currency       【  wlan.hs20.advice_of_charge.plan_info_tuples.plan_curcy 】
820:   {"Plan information       【  wlan.hs20.advice_of_charge.plan_info_tuples.info 】
821:   {"Tag       【  wlan.tag 】
822:   {"Tag Number       【  wlan.tag.number 】
823:   {"Tag length       【  wlan.tag.length 】
824:   {"Tag Data       【  wlan.tag.data 】
825:   {"OUI       【  wlan.tag.oui 】
826:   {"WFA Subtype       【  wlan.tag.oui.wfa_subtype 】
827:   {"Current Channel       【  wlan.ds.current_channel 】
828:   {"CFP Count       【  wlan.cfp.count 】
829:   {"CFP Period       【  wlan.cfp.period 】
830:   {"CFP Max Duration       【  wlan.cfp.max_duration 】
831:   {"CFP Dur Remaining       【  wlan.cfp.dur_remaining 】
832:   {"Vendor Specific OUI Type       【  wlan.tag.vendor.oui.type 】
833:   {"Vendor Specific Data       【  wlan.tag.vendor.data 】
834:   {"DTIM count       【  wlan.tim.dtim_count 】
835:   {"DTIM period       【  wlan.tim.dtim_period 】
836:   {"Bitmap control       【  wlan.tim.bmapctl 】
837:   {"Multicast       【  wlan.tim.bmapctl.multicast 】
838:   {"Bitmap Offset       【  wlan.tim.bmapctl.offset 】
839:   {"Partial Virtual Bitmap       【  wlan.tim.partial_virtual_bitmap 】
840:   {"Association ID       【  wlan.tim.aid 】
841:   {"Atim Windows       【  wlan.ibss.atim_windows 】
842:   {"Code       【  wlan.country_info.code 】
843:   {"Environment       【  wlan.country_info.environment 】
844:   {"Padding       【  wlan.country_info.padding 】
845:   {"Country Info       【  wlan.country_info.fnm 】
846:   {"First Channel Number       【  wlan.country_info.fnm.fcn 】
847:   {"Number of Channels       【  wlan.country_info.fnm.nc 】
848:   {"Maximum Transmit Power Level       【  wlan.country_info.fnm.mtpl 】
849:   {"Country Info       【  wlan.country_info.rrc 】
850:   {"Operating Extension Identifier       【  wlan.country_info.rrc.oei 】
851:   {"Operating Class       【  wlan.country_info.rrc.oc 】
852:   {"Coverage Class       【  wlan.country_info.rrc.cc 】
853:   {"Prime Radix       【  wlan.fh_hopping.parameter.prime_radix 】
854:   {"Number of Channels       【  wlan.fh_hopping.parameter.nb_channels 】
855:   {"Flag       【  wlan.fh_hopping.table.flag 】
856:   {"Number of Sets       【  wlan.fh_hopping.table.number_of_sets 】
857:   {"Modulus       【  wlan.fh_hopping.table.modulus 】
858:   {"Offset       【  wlan.fh_hopping.table.offset 】
859:   {"Random Table       【  wlan.fh_hopping.table.random_table 】
860:   {"Requested Element ID       【  wlan.tag.request 】
861:   {"User Priority       【  wlan.tclas.user_priority 】
862:   {"Classifier Type       【  wlan.tclas.class_type 】
863:   {"Classifier Mask       【  wlan.tclas.class_mask 】
864:   {"Source Address       【  wlan.tclas.class_mask.src_addr 】
865:   {"Destination Address       【  wlan.tclas.class_mask.dst_addr 】
866:   {"Type       【  wlan.tclas.class_mask.type 】
867:   {"Version       【  wlan.tclas.class_mask.version 】
868:   {"Source IP Address       【  wlan.tclas.class_mask.src_ip 】
869:   {"Destination IP Address       【  wlan.tclas.class_mask.dst_ip 】
870:   {"Source Port       【  wlan.tclas.class_mask.src_port 】
871:   {"Destination Port       【  wlan.tclas.class_mask.dst_port 】
872:   {"DSCP       【  wlan.tclas.class_mask.dscp 】
873:   {"Protocol       【  wlan.tclas.class_mask.proto 】
874:   {"Flow Label       【  wlan.tclas.class_mask.flow_label 】
875:   {"802.1Q CLAN TCI       【  wlan.tclas.class_mask.tci 】
876:   {"Source address       【  wlan.tclas.src_mac_addr 】
877:   {"Destination address       【  wlan.tclas.dat_mac_addr 】
878:   {"Ethernet Type       【  wlan.tclas.ether_type 】
879:   {"IP Version       【  wlan.tclas.version 】
880:   {"IPv4 Src Addr       【  wlan.tclas.ipv4_src 】
881:   {"IPv4 Dst Addr       【  wlan.tclas.ipv4_dst 】
882:   {"Source Port       【  wlan.tclas.src_port 】
883:   {"Destination Port       【  wlan.tclas.dst_port 】
884:   {"IPv4 DSCP       【  wlan.tclas.dscp 】
885:   {"Protocol       【  wlan.tclas.protocol 】
886:   {"IPv6 Src Addr       【  wlan.tclas.ipv6_src 】
887:   {"IPv6 Dst Addr       【  wlan.tclas.ipv6_dst 】
888:   {"Flow Label       【  wlan.tclas.flow 】
889:   {"802.1Q Tag Type       【  wlan.tclas.tag_type 】
890:   {"Challenge Text       【  wlan.tag.challenge_text 】
891:   {"RSN Version       【  wlan.rsn.version 】
892:   {"Group Cipher Suite       【  wlan.rsn.gcs 】
893:   {"Group Cipher Suite OUI       【  wlan.rsn.gcs.oui 】
894:   {"Group Cipher Suite type       【  wlan.rsn.gcs.type 】
895:   {"Group Cipher Suite type       【  wlan.rsn.gcs.type 】
896:   {"Pairwise Cipher Suite Count       【  wlan.rsn.pcs.count 】
897:   {"Pairwise Cipher Suite List       【  wlan.rsn.pcs.list 】
898:   {"Pairwise Cipher Suite       【  wlan.rsn.pcs 】
899:   {"Pairwise Cipher Suite OUI       【  wlan.rsn.pcs.oui 】
900:   {"Pairwise Cipher Suite type       【  wlan.rsn.pcs.type 】
901:   {"Pairwise Cipher Suite type       【  wlan.rsn.pcs.type 】
902:   {"Auth Key Management (AKM) Suite Count       【  wlan.rsn.akms.count 】
903:   {"Auth Key Management (AKM) List       【  wlan.rsn.akms.list 】
904:   {"Auth Key Management (AKM) Suite       【  wlan.rsn.akms 】
905:   {"Auth Key Management (AKM) OUI       【  wlan.rsn.akms.oui 】
906:   {"Auth Key Management (AKM) type       【  wlan.rsn.akms.type 】
907:   {"Auth Key Management (AKM) type       【  wlan.rsn.akms.type 】
908:   {"RSN Capabilities       【  wlan.rsn.capabilities 】
909:   {"RSN Pre-Auth capabilities       【  wlan.rsn.capabilities.preauth 】
910:   {"RSN No Pairwise capabilities       【  wlan.rsn.capabilities.no_pairwise 】
911:   {"RSN PTKSA Replay Counter capabilities       【  wlan.rsn.capabilities.ptksa_replay_counter 】
912:   {"RSN GTKSA Replay Counter capabilities       【  wlan.rsn.capabilities.gtksa_replay_counter 】
913:   {"Management Frame Protection Required       【  wlan.rsn.capabilities.mfpr 】
914:   {"Management Frame Protection Capable       【  wlan.rsn.capabilities.mfpc 】
915:   {"Joint Multi-band RSNA       【  wlan.rsn.capabilities.jmr 】
916:   {"PeerKey Enabled       【  wlan.rsn.capabilities.peerkey 】
917:   {"PMKID Count       【  wlan.rsn.pmkid.count 】
918:   {"PMKID List       【  wlan.rsn.pmkid.list 】
919:   {"PMKID       【  wlan.pmkid.akms 】
920:   {"Group Management Cipher Suite       【  wlan.rsn.gmcs 】
921:   {"Group Management Cipher Suite OUI       【  wlan.rsn.gmcs.oui 】
922:   {"Group Management Cipher Suite type       【  wlan.rsn.gmcs.type 】
923:   {"Group Management Cipher Suite type       【  wlan.rsn.gmcs.type 】
924:   {"802.11n (Pre) Type       【  wlan.vs.pren.type 】
925:   {"802.11n (Pre) Unknown Data       【  wlan.vs.pren.unknown_data 】
926:   {"HT Capabilities Info       【  wlan.ht.capabilities 】
927:   {"HT Capabilities Info (VS)       【  wlan.vs.ht.capabilities 】
928:   {"HT LDPC coding capability       【  wlan.ht.capabilities.ldpccoding 】
929:   {"HT Support channel width       【  wlan.ht.capabilities.width 】
930:   {"HT SM Power Save       【  wlan.ht.capabilities.sm 】
931:   {"HT Green Field       【  wlan.ht.capabilities.green 】
932:   {"HT Short GI for 20MHz       【  wlan.ht.capabilities.short20 】
933:   {"HT Short GI for 40MHz       【  wlan.ht.capabilities.short40 】
934:   {"HT Tx STBC       【  wlan.ht.capabilities.txstbc 】
935:   {"HT Rx STBC       【  wlan.ht.capabilities.rxstbc 】
936:   {"HT Delayed Block ACK       【  wlan.ht.capabilities.delayedblockack 】
937:   {"HT Max A-MSDU length       【  wlan.ht.capabilities.amsdu 】
938:   {"HT DSSS/CCK mode in 40MHz       【  wlan.ht.capabilities.dsscck 】
939:   {"HT PSMP Support       【  wlan.ht.capabilities.psmp 】
940:   {"HT Forty MHz Intolerant       【  wlan.ht.capabilities.40mhzintolerant 】
941:   {"HT L-SIG TXOP Protection support       【  wlan.ht.capabilities.lsig 】
942:   {"MU-MIMO Capable STA Count       【  wlan.ext_bss.mu_mimo_capable_sta_count 】
943:   {"Spatial Stream Underutilization       【  wlan.ext_bss.ss_underutilization 】
944:   {"Observable Secondary 20MHz Utilization       【  wlan.ext_bss.observable_sec_20mhz_utilization 】
945:   {"Observable Secondary 40MHz Utilization       【  wlan.ext_bss.observable_sec_40mhz_utilization 】
946:   {"Observable Secondary 80MHz Utilization       【  wlan.ext_bss.observable_sec_80mhz_utilization 】
947:   {"New Channel Width       【  wlan.wide_bw.new_channel_width 】
948:   {"New Channel Center Frequency Segment 0       【  wlan.wide_bw.new_channel_center_freq_segment0 】
949:   {"New Channel Center Frequency Segment 1       【  wlan.wide_bw.new_channel_center_freq_segment1 】
950:   {"Operating Mode Notification       【  wlan.operat_notification_mode 】
951:   {"Channel Width       【  wlan.operat_mode_field.channelwidth 】
952:   {"Reserved       【  wlan.operat_mode_field.reserved 】
953:   {"Rx NSS       【  wlan.operat_mode_field.rxnss 】
954:   {"Rx NSS Type       【  wlan.operat_mode_field.rxnsstype 】
955:   {"A-MPDU Parameters       【  wlan.ht.ampduparam 】
956:   {"A-MPDU Parameters (VS)       【  wlan.vs.ht.ampduparam 】
957:   {"Maximum Rx A-MPDU Length       【  wlan.ht.ampduparam.maxlength 】
958:   {"MPDU Density       【  wlan.ht.ampduparam.mpdudensity 】
959:   {"Reserved       【  wlan.ht.ampduparam.reserved 】
960:   {"Rx Supported Modulation and Coding Scheme Set       【  wlan.ht.mcsset 】
961:   {"Rx Supported Modulation and Coding Scheme Set (VS)       【  wlan.vs.ht.mcsset 】
962:   {"Rx Modulation and Coding Scheme (One bit per modulation)       【  wlan.ht.mcsset.rxbitmask 】
963:   {"Rx Bitmask Bits 0-7       【  wlan.ht.mcsset.rxbitmask.0to7 】
964:   {"Rx Bitmask Bits 8-15       【  wlan.ht.mcsset.rxbitmask.8to15 】
965:   {"Rx Bitmask Bits 16-23       【  wlan.ht.mcsset.rxbitmask.16to23 】
966:   {"Rx Bitmask Bits 24-31       【  wlan.ht.mcsset.rxbitmask.24to31 】
967:   {"Rx Bitmask Bit 32       【  wlan.ht.mcsset.rxbitmask.32 】
968:   {"Rx Bitmask Bits 33-38       【  wlan.ht.mcsset.rxbitmask.33to38 】
969:   {"Rx Bitmask Bits 39-52       【  wlan.ht.mcsset.rxbitmask.39to52 】
970:   {"Rx Bitmask Bits 53-76       【  wlan.ht.mcsset.rxbitmask.53to76 】
971:   {"Highest Supported Data Rate       【  wlan.ht.mcsset.highestdatarate 】
972:   {"Tx Supported MCS Set       【  wlan.ht.mcsset.txsetdefined 】
973:   {"Tx and Rx MCS Set       【  wlan.ht.mcsset.txrxmcsnotequal 】
974:   {"Maximum Number of Tx Spatial Streams Supported       【  wlan.ht.mcsset.txmaxss 】
975:   {"Unequal Modulation       【  wlan.ht.mcsset.txunequalmod 】
976:   {"HT Extended Capabilities       【  wlan.htex.capabilities 】
977:   {"HT Extended Capabilities (VS)       【  wlan.vs.htex.capabilities 】
978:   {"Transmitter supports PCO       【  wlan.htex.capabilities.pco 】
979:   {"Time needed to transition between 20MHz and 40MHz       【  wlan.htex.capabilities.transtime 】
980:   {"MCS Feedback capability       【  wlan.htex.capabilities.mcs 】
981:   {"High Throughput       【  wlan.htex.capabilities.htc 】
982:   {"Reverse Direction Responder       【  wlan.htex.capabilities.rdresponder 】
983:   {"Transmit Beam Forming (TxBF) Capabilities       【  wlan.txbf 】
984:   {"Transmit Beam Forming (TxBF) Capabilities (VS)       【  wlan.vs.txbf 】
985:   {"Transmit Beamforming       【  wlan.txbf.txbf 】
986:   {"Receive Staggered Sounding       【  wlan.txbf.rxss 】
987:   {"Transmit Staggered Sounding       【  wlan.txbf.txss 】
988:   {"Receive Null Data packet (NDP)       【  wlan.txbf.rxndp 】
989:   {"Transmit Null Data packet (NDP)       【  wlan.txbf.txndp 】
990:   {"Implicit TxBF capable       【  wlan.txbf.impltxbf 】
991:   {"Calibration       【  wlan.txbf.calibration 】
992:   {"STA can apply TxBF using CSI explicit feedback       【  wlan.txbf.csi 】
993:   {"STA can apply TxBF using uncompressed beamforming feedback matrix       【  wlan.txbf.fm.uncompressed.tbf 】
994:   {"STA can apply TxBF using compressed beamforming feedback matrix       【  wlan.txbf.fm.compressed.tbf 】
995:   {"Receiver can return explicit CSI feedback       【  wlan.txbf.rcsi 】
996:   {"Receiver can return explicit uncompressed Beamforming Feedback Matrix       【  wlan.txbf.fm.uncompressed.rbf 】
997:   {"STA can compress and use compressed Beamforming Feedback Matrix       【  wlan.txbf.fm.compressed.bf 】
998:   {"Minimal grouping used for explicit feedback reports       【  wlan.txbf.mingroup 】
999:   {"VHT Capabilities Info       【  wlan.vht.capabilities 】
1000:   {"Maximum MPDU Length       【  wlan.vht.capabilities.maxmpdulength 】
1001:   {"Supported Channel Width Set       【  wlan.vht.capabilities.supportedchanwidthset 】
1002:   {"Rx LDPC       【  wlan.vht.capabilities.rxldpc 】
1003:   {"Short GI for 80MHz/TVHT_MODE_4C       【  wlan.vht.capabilities.short80 】
1004:   {"Short GI for 160MHz and 80+80MHz       【  wlan.vht.capabilities.short160 】
1005:   {"Tx STBC       【  wlan.vht.capabilities.txstbc 】
1006:   {"Rx STBC       【  wlan.vht.capabilities.rxstbc 】
1007:   {"SU Beamformer Capable       【  wlan.vht.capabilities.subeamformer 】
1008:   {"SU Beamformee Capable       【  wlan.vht.capabilities.subeamformee 】
1009:   {"Beamformee STS Capability       【  wlan.vht.capabilities.beamformee_sts_cap 】
1010:   {"Number of Sounding Dimensions       【  wlan.vht.capabilities.soundingdimensions 】
1011:   {"MU Beamformer Capable       【  wlan.vht.capabilities.mubeamformer 】
1012:   {"MU Beamformee Capable       【  wlan.vht.capabilities.mubeamformee 】
1013:   {"TXOP PS       【  wlan.vht.capabilities.vhttxopps 】
1014:   {"+HTC-VHT Capable       【  wlan.vht.capabilities.vhthtc 】
1015:   {"Max A-MPDU Length Exponent       【  wlan.vht.capabilities.maxampdu 】
1016:   {"VHT Link Adaptation       【  wlan.vht.capabilities.linkadapt 】
1017:   {"Rx Antenna Pattern Consistency       【  wlan.vht.capabilities.rxpatconsist 】
1018:   {"Tx Antenna Pattern Consistency       【  wlan.vht.capabilities.txpatconsist 】
1019:   {"Extended NSS BW Support       【  wlan.vht.capabilities.ext_nss_bw_support 】
1020:   {"VHT Supported MCS Set       【  wlan.vht.mcsset 】
1021:   {"Rx MCS Map       【  wlan.vht.mcsset.rxmcsmap 】
1022:   {"Rx 1 SS       【  wlan.vht.mcsset.rxmcsmap.ss1 】
1023:   {"Rx 2 SS       【  wlan.vht.mcsset.rxmcsmap.ss2 】
1024:   {"Rx 3 SS       【  wlan.vht.mcsset.rxmcsmap.ss3 】
1025:   {"Rx 4 SS       【  wlan.vht.mcsset.rxmcsmap.ss4 】
1026:   {"Rx 5 SS       【  wlan.vht.mcsset.rxmcsmap.ss5 】
1027:   {"Rx 6 SS       【  wlan.vht.mcsset.rxmcsmap.ss6 】
1028:   {"Rx 7 SS       【  wlan.vht.mcsset.rxmcsmap.ss7 】
1029:   {"Rx 8 SS       【  wlan.vht.mcsset.rxmcsmap.ss8 】
1030:   {"MaX NSTS Total       【  wlan.vht.mcsset.max_nsts_total 】
1031:   {"Rx Highest Long GI Data Rate (in Mb/s, 0 = subfield not in use)       【  wlan.vht.mcsset.rxhighestlonggirate 】
1032:   {"Tx MCS Map       【  wlan.vht.mcsset.txmcsmap 】
1033:   {"Tx 1 SS       【  wlan.vht.mcsset.txmcsmap.ss1 】
1034:   {"Tx 2 SS       【  wlan.vht.mcsset.txmcsmap.ss2 】
1035:   {"Tx 3 SS       【  wlan.vht.mcsset.txmcsmap.ss3 】
1036:   {"Tx 4 SS       【  wlan.vht.mcsset.txmcsmap.ss4 】
1037:   {"Tx 5 SS       【  wlan.vht.mcsset.txmcsmap.ss5 】
1038:   {"Tx 6 SS       【  wlan.vht.mcsset.txmcsmap.ss6 】
1039:   {"Tx 7 SS       【  wlan.vht.mcsset.txmcsmap.ss7 】
1040:   {"Tx 8 SS       【  wlan.vht.mcsset.txmcsmap.ss8 】
1041:   {"Tx Highest Long GI Data Rate  (in Mb/s, 0 = subfield not in use)       【  wlan.vht.mcsset.txhighestlonggirate 】
1042:   {"Extended NSS BW Capable       【  wlan.vht.ncsset.ext_nss_bw_cap 】
1043:   {"Reserved       【  wlan.vht.ncsset.reserved 】
1044:   {"VHT Operation Info       【  wlan.vht.op 】
1045:   {"Channel Width       【  wlan.vht.op.channelwidth 】
1046:   {"Channel Center Segment 0       【  wlan.vht.op.channelcenter0 】
1047:   {"Channel Center Segment 1       【  wlan.vht.op.channelcenter1 】
1048:   {"Basic MCS Map       【  wlan.vht.op.basicmcsmap 】
1049:   {"Basic 1 SS       【  wlan.vht.op.basicmcsmap.ss1 】
1050:   {"Basic 2 SS       【  wlan.vht.op.basicmcsmap.ss2 】
1051:   {"Basic 3 SS       【  wlan.vht.op.basicmcsmap.ss3 】
1052:   {"Basic 4 SS       【  wlan.vht.op.basicmcsmap.ss4 】
1053:   {"Basic 5 SS       【  wlan.vht.op.basicmcsmap.ss5 】
1054:   {"Basic 6 SS       【  wlan.vht.op.basicmcsmap.ss6 】
1055:   {"Basic 7 SS       【  wlan.vht.op.basicmcsmap.ss7 】
1056:   {"Basic 8 SS       【  wlan.vht.op.basicmcsmap.ss8 】
1057:   {"Tx Pwr Info       【  wlan.vht.tpe.pwr_info 】
1058:   {"Max Tx Pwr Count       【  wlan.vht.tpe.pwr_info.count 】
1059:   {"Max Tx Pwr Unit Interpretation       【  wlan.vht.tpe.pwr_info.unit 】
1060:   {"Reserved       【  wlan.vht.tpe.pwr_info.reserved 】
1061:   {"Local Max Tx Pwr Constraint 20MHz       【  wlan.vht.tpe.pwr_constr_20 】
1062:   {"Local Max Tx Pwr Constraint 40MHz       【  wlan.vht.tpe.pwr_constr_40 】
1063:   {"Local Max Tx Pwr Constraint 80MHz       【  wlan.vht.tpe.pwr_constr_80 】
1064:   {"Local Max Tx Pwr Constraint 160MHz/80+80 MHz       【  wlan.vht.tpe.pwr_constr_160 】
1065:   {"Max antennae STA can support when CSI feedback required       【  wlan.txbf.csinumant 】
1066:   {"Max antennae STA can support when uncompressed Beamforming feedback required       【  wlan.txbf.fm.uncompressed.maxant 】
1067:   {"Max antennae STA can support when compressed Beamforming feedback required       【  wlan.txbf.fm.compressed.maxant 】
1068:   {"Maximum number of rows of CSI explicit feedback       【  wlan.txbf.csi.maxrows 】
1069:   {"Maximum number of space time streams for which channel dimensions can be simultaneously estimated       【  wlan.txbf.channelest 】
1070:   {"Reserved       【  wlan.txbf.reserved 】
1071:   {"HT Control Channel       【  wlan.hta.control_channel 】
1072:   {"HT Additional Capabilities       【  wlan.hta.capabilities 】
1073:   {"HT Additional Capabilities       【  wlan.hta.capabilities 】
1074:   {"Extension Channel Offset       【  wlan.hta.capabilities.ext_chan_offset 】
1075:   {"Recommended Tx Channel Width       【  wlan.hta.capabilities.rec_tx_width 】
1076:   {"Reduced Interframe Spacing (RIFS) Mode       【  wlan.hta.capabilities.rifs_mode 】
1077:   {"Controlled Access Only       【  wlan.hta.capabilities.controlled_access 】
1078:   {"Service Interval Granularity       【  wlan.hta.capabilities.service_interval 】
1079:   {"Operating Mode       【  wlan.hta.capabilities.operating_mode 】
1080:   {"Non Greenfield (GF) devices Present       【  wlan.hta.capabilities.non_gf_devices 】
1081:   {"Basic STB Modulation and Coding Scheme (MCS)       【  wlan.hta.capabilities.basic_stbc_mcs 】
1082:   {"Dual Clear To Send (CTS) Protection       【  wlan.hta.capabilities.dual_stbc_protection 】
1083:   {"Secondary Beacon       【  wlan.hta.capabilities.secondary_beacon 】
1084:   {"L-SIG TXOP Protection Support       【  wlan.hta.capabilities.lsig_txop_protection 】
1085:   {"Phased Coexistence Operation (PCO) Active       【  wlan.hta.capabilities.pco_active 】
1086:   {"Phased Coexistence Operation (PCO) Phase       【  wlan.hta.capabilities.pco_phase 】
1087:   {"Antenna Selection (ASEL) Capabilities       【  wlan.asel 】
1088:   {"Antenna Selection (ASEL) Capabilities (VS)       【  wlan.vs.asel 】
1089:   {"Antenna Selection Capable       【  wlan.asel.capable 】
1090:   {"Explicit CSI Feedback Based Tx ASEL       【  wlan.asel.txcsi 】
1091:   {"Antenna Indices Feedback Based Tx ASEL       【  wlan.asel.txif 】
1092:   {"Explicit CSI Feedback       【  wlan.asel.csi 】
1093:   {"Antenna Indices Feedback       【  wlan.asel.if 】
1094:   {"Rx ASEL       【  wlan.asel.rx 】
1095:   {"Tx Sounding PPDUs       【  wlan.asel.sppdu 】
1096:   {"Reserved       【  wlan.asel.reserved 】
1097:   {"HT Information Subset (1 of 3)       【  wlan.ht.info.delim1 】
1098:   {"Primary Channel       【  wlan.ht.info.primarychannel 】
1099:   {"Secondary channel offset       【  wlan.ht.info.secchanoffset 】
1100:   {"Supported channel width       【  wlan.ht.info.chanwidth 】
1101:   {"Reduced Interframe Spacing (RIFS)       【  wlan.ht.info.rifs 】
1102:   {"Reserved       【  wlan.ht.info.reserved_b4_b7 】
1103:   {"HT Information Subset (2 of 3)       【  wlan.ht.info.delim2 】
1104:   {"HT Protection       【  wlan.ht.info.ht_protection 】
1105:   {"Non-greenfield STAs present       【  wlan.ht.info.greenfield 】
1106:   {"Reserved       【  wlan.ht.info.reserved_b11 】
1107:   {"OBSS non-HT STAs present       【  wlan.ht.info.obssnonht 】
1108:   {"Channel Center Frequency Segment 2       【  wlan.ht.info.chan_center_freq_seg_2 】
1109:   {"Reserved       【  wlan.ht.info.reserved_b21_b23 】
1110:   {"HT Information Subset (3 of 3)       【  wlan.ht.info.delim3 】
1111:   {"Reserved       【  wlan.ht.info.reserved_b24_b29 】
1112:   {"Dual beacon       【  wlan.ht.info.dualbeacon 】
1113:   {"Dual Clear To Send (CTS) protection       【  wlan.ht.info.dualcts 】
1114:   {"Beacon ID       【  wlan.ht.info.secondarybeacon 】
1115:   {"L-SIG TXOP Protection Full Support       【  wlan.ht.info.lsigprotsupport 】
1116:   {"Phased Coexistence Operation (PCO)       【  wlan.ht.info.pco.active 】
1117:   {"Phased Coexistence Operation (PCO) Phase       【  wlan.ht.info.pco.phase 】
1118:   {"Reserved       【  wlan.ht.info.reserved_b36_b39 】
1119:   {"Operating Class       【  wlan.ap_channel_report.operating_class 】
1120:   {"Channel List       【  wlan.ap_channel_report.channel_list 】
1121:   {"Secondary Channel Offset       【  wlan.secchanoffset 】
1122:   {"AP Average Access Delay       【  wlan.bss_ap_avg_access_delay 】
1123:   {"Antenna ID       【  wlan.antenna.id 】
1124:   {"RSNI       【  wlan.rsni 】
1125:   {"Available Admission Capacity Bitmask       【  wlan.bss_avb_adm_cap.bitmask 】
1126:   {"UP0 (bit0)       【  wlan.bss_avb_adm_cap.bitmask.up0 】
1127:   {"UP1 (bit1)       【  wlan.bss_avb_adm_cap.bitmask.up1 】
1128:   {"UP2 (bit2)       【  wlan.bss_avb_adm_cap.bitmask.up2 】
1129:   {"UP3 (bit3)       【  wlan.bss_avb_adm_cap.bitmask.up3 】
1130:   {"UP4 (bit4)       【  wlan.bss_avb_adm_cap.bitmask.up4 】
1131:   {"UP5 (bit5)       【  wlan.bss_avb_adm_cap.bitmask.up5 】
1132:   {"UP0 (bit6)       【  wlan.bss_avb_adm_cap.bitmask.up6 】
1133:   {"UP7 (bit7)       【  wlan.bss_avb_adm_cap.bitmask.up7 】
1134:   {"AC0 (bit8)       【  wlan.bss_avb_adm_cap.bitmask.ac0 】
1135:   {"AC1 (bit9)       【  wlan.bss_avb_adm_cap.bitmask.AC1 】
1136:   {"AC2 (bit10)       【  wlan.bss_avb_adm_cap.bitmask.ac2 】
1137:   {"AC3 (bit11)       【  wlan.bss_avb_adm_cap.bitmask.ac3 】
1138:   {"Reserved       【  wlan.bss_avb_adm_cap.bitmask.rsv 】
1139:   {"UP0       【  wlan.bss_avb_adm_cap.up0 】
1140:   {"UP1       【  wlan.bss_avb_adm_cap.up1 】
1141:   {"UP2       【  wlan.bss_avb_adm_cap.up2 】
1142:   {"UP3       【  wlan.bss_avb_adm_cap.up3 】
1143:   {"UP4       【  wlan.bss_avb_adm_cap.up4 】
1144:   {"UP5       【  wlan.bss_avb_adm_cap.up5 】
1145:   {"UP6       【  wlan.bss_avb_adm_cap.up6 】
1146:   {"UP7       【  wlan.bss_avb_adm_cap.up7 】
1147:   {"AC0       【  wlan.bss_avb_adm_cap.ac0 】
1148:   {"AC1       【  wlan.bss_avb_adm_cap.ac1 】
1149:   {"AC2       【  wlan.bss_avb_adm_cap.ac2 】
1150:   {"AC3       【  wlan.bss_avb_adm_cap.ac3 】
1151:   {"AC Average Access Delay for Best Effort       【  wlan.bss_avg_ac_access_delay.be 】
1152:   {"AC Average Access Delay for Best Background       【  wlan.bss_avg_ac_access_delay.bk 】
1153:   {"AC Average Access Delay for Video       【  wlan.bss_avg_ac_access_delay_vi 】
1154:   {"AC Average Access Delay for Voice       【  wlan.bss_avg_ac_access_delay_vo 】
1155:   {"RM Capabilities       【  wlan.rmcap 】
1156:   {"Link Measurement       【  wlan.rmcap.b0 】
1157:   {"Neighbor Report       【  wlan.rmcap.b1 】
1158:   {"Parallel Measurements       【  wlan.rmcap.b2 】
1159:   {"Repeated Measurements       【  wlan.rmcap.b3 】
1160:   {"Beacon Passive Measurement       【  wlan.rmcap.b4 】
1161:   {"Beacon Active Measurement       【  wlan.rmcap.b5 】
1162:   {"Beacon Table Measurement       【  wlan.rmcap.b6 】
1163:   {"Beacon Measurement Reporting Conditions       【  wlan.rmcap.b7 】
1164:   {"Frame Measurement       【  wlan.rmcap.b8 】
1165:   {"Channel Load Measurement       【  wlan.rmcap.b9 】
1166:   {"Noise Histogram Measurement       【  wlan.rmcap.b10 】
1167:   {"Statistics Measurement       【  wlan.rmcap.b11 】
1168:   {"LCI Measurement       【  wlan.rmcap.b12 】
1169:   {"LCI Azimuth capability       【  wlan.rmcap.b13 】
1170:   {"Transmit Stream/Category Measurement       【  wlan.rmcap.b14 】
1171:   {"Triggered Transmit Stream/Category Measurement       【  wlan.rmcap.b15 】
1172:   {"AP Channel Report capability       【  wlan.rmcap.b16 】
1173:   {"RM MIB capability       【  wlan.rmcap.b17 】
1174:   {"Operating Channel Max Measurement Duration       【  wlan.rmcap.b18to20 】
1175:   {"Nonoperating Channel Max Measurement Duration       【  wlan.rmcap.b21to23 】
1176:   {"Measurement Pilotcapability       【  wlan.rmcap.b24to26 】
1177:   {"Measurement Pilot Transmission Information       【  wlan.rmcap.b27 】
1178:   {"Neighbor Report TSF Offset       【  wlan.rmcap.b28 】
1179:   {"RCPI Measurement capability       【  wlan.rmcap.b29 】
1180:   {"RSNI Measurement capability       【  wlan.rmcap.b30 】
1181:   {"BSS Average Access Delay capability       【  wlan.rmcap.b31 】
1182:   {"BSS Available Admission Capacity capability       【  wlan.rmcap.b32 】
1183:   {"Antenna capability       【  wlan.rmcap.b33 】
1184:   {"Reserved       【  wlan.rmcap.o5 】
1185:   {"RCPI       【  wlan.rcpi 】
1186:   {"Max BSSID Indicator       【  wlan.multiple_bssid 】
1187:   {"Subelement ID       【  wlan.multiple_bssid.subelem.id 】
1188:   {"Length       【  wlan.multiple_bssid.subelem.len 】
1189:   {"Reserved       【  wlan.multiple_bssid.subelem.reserved 】
1190:   {"Nontransmitted Profile       【  wlan.multiple_bssid.subelem.nontrans_profile 】
1191:   {"20/40 BSS Coexistence Flags       【  wlan.20_40_bc 】
1192:   {"Information Request       【  wlan.20_40_bc.information_request 】
1193:   {"Forty MHz Intolerant       【  wlan.20_40_bc.forty_mhz_intolerant 】
1194:   {"20 MHz BSS Witdh Request       【  wlan.20_40_bc.20_mhz_bss_witdh_request 】
1195:   {"OBSS Scanning Exemption Request       【  wlan.20_40_bc.obss_scanning_exemption_request 】
1196:   {"OBSS Scanning Exemption Grant       【  wlan.20_40_bc.obss_scanning_exemption_grant 】
1197:   {"Reserved       【  wlan.20_40_bc.reserved 】
1198:   {"Local Power Constraint       【  wlan.powercon.local 】
1199:   {"Minimum Transmit Power       【  wlan.powercap.min 】
1200:   {"Maximum Transmit Power       【  wlan.powercap.max 】
1201:   {"Transmit Power       【  wlan.tcprep.trsmt_pow 】
1202:   {"Link Margin       【  wlan.tcprep.link_mrg 】
1203:   {"Supported Channels Set       【  wlan.supchan 】
1204:   {"First Supported Channel       【  wlan.supchan.first 】
1205:   {"Supported Channel Range       【  wlan.supchan.range 】
1206:   {"Channel Switch Mode       【  wlan.csa.channel_switch_mode 】
1207:   {"New Channel Number       【  wlan.csa.new_channel_number 】
1208:   {"Channel Switch Count       【  wlan.csa.channel_switch.count 】
1209:   {"Mesh Channel Switch TTL       【  wlan.csa.mesh_channel_switch.ttl 】
1210:   {"Mesh Channel Switch Flag       【  wlan.csa.mesh_channel_switch.flag 】
1211:   {"CSA Tx Restrict       【  wlan.csa.mesh_channel_switch.flag.txrestrict 】
1212:   {"CSA Initiator       【  wlan.csa.mesh_channel_switch.flag.initiator 】
1213:   {"Mesh Channel Switch Reason Code       【  wlan.csa.mesh_channel_switch.reason_code 】
1214:   {"Mesh Channel Switch Precedence Value       【  wlan.csa.mesh_channel_switch.pre_value 】
1215:   {"Mesh Awake Window       【  wlan.mesh.mesh_awake_window 】
1216:   {"Measurement Token       【  wlan.measure.req.token 】
1217:   {"Measurement Request Mode       【  wlan.measure.req.mode 】
1218:   {"Parallel       【  wlan.measure.req.reqmode.parallel 】
1219:   {"Measurement Request Mode Field       【  wlan.measure.req.reqmode.enable 】
1220:   {"Measurement Reports       【  wlan.measure.req.reqmode.request 】
1221:   {"Autonomous Measurement Reports       【  wlan.measure.req.reqmode.report 】
1222:   {"Duration Mandatory       【  wlan.measure.req.reqmode.duration_mandatory 】
1223:   {"Reserved       【  wlan.measure.req.reqmode.reserved 】
1224:   {"Measurement Request Type       【  wlan.measure.req.reqtype 】
1225:   {"Measurement Channel Number       【  wlan.measure.req.channelnumber 】
1226:   {"Measurement Start Time       【  wlan.measure.req.starttime 】
1227:   {"Measurement Duration       【  wlan.measure.req.channelnumber 】
1228:   {"Operating Class       【  wlan.measure.req.operatingclass 】
1229:   {"Randomization Interval       【  wlan.measure.req.randint 】
1230:   {"Measurement Mode       【  wlan.measure.req.measurementmode 】
1231:   {"BSSID       【  wlan.measure.req.bssid 】
1232:   {"Length       【  wlan.measure.req.sub.length 】
1233:   {"SubElement ID       【  wlan.measure.req.beacon.sub.id 】
1234:   {"SSID       【  wlan.measure.req.beacon.sub.ssid 】
1235:   {"Reporting Condition       【  wlan.measure.req.beacon.sub.bri.repcond 】
1236:   {"Threshold/Offset       【  wlan.measure.req.beacon.sub.bri.threshold_offset 】
1237:   {"Reporting Detail       【  wlan.measure.req.beacon.sub.bri.reporting_detail 】
1238:   {"Request       【  wlan.measure.req.beacon.sub.request 】
1239:   {"Unknown Data       【  wlan.measure.req.beacon.unknown 】
1240:   {"SubElement ID       【  wlan.measure.req.channel_load.sub.id 】
1241:   {"Reporting Condition       【  wlan.measure.req.channel_load.sub.repcond 】
1242:   {"Reference Value       【  wlan.measure.req.channel_load.sub.ref 】
1243:   {"SubElement ID       【  wlan.measure.req.noise_histogram.sub.id 】
1244:   {"Reporting Condition       【  wlan.measure.reqnoise_histogram.sub.repcond 】
1245:   {"ANPI Reference Value       【  wlan.measure.req.noise_histogram.sub.anpiref 】
1246:   {"Frame Request Type       【  wlan.measure.req.frame_request_type 】
1247:   {"MAC Address       【  wlan.measure.req.mac_address 】
1248:   {"Peer MAC Address       【  wlan.measure.req.peer_mac_address 】
1249:   {"Group ID       【  wlan.measure.req.groupid 】
1250:   {"Unknown Data       【  wlan.measure.req.unknown 】
1251:   {"Measurement Token       【  wlan.measure.req.token 】
1252:   {"Measurement Report Mode       【  wlan.measure.req.mode 】
1253:   {"Measurement Report Mode Field       【  wlan.measure.rep.repmode.late 】
1254:   {"Measurement Reports       【  wlan.measure.rep.repmode.incapable 】
1255:   {"Autonomous Measurement Reports       【  wlan.measure.rep.repmode.refused 】
1256:   {"Reserved       【  wlan.measure.rep.repmode.reserved 】
1257:   {"Measurement Report Type       【  wlan.measure.rep.reptype 】
1258:   {"Measurement Channel Number       【  wlan.measure.rep.channelnumber 】
1259:   {"Measurement Start Time       【  wlan.measure.rep.starttime 】
1260:   {"Measurement Duration       【  wlan.measure.rep.channelnumber 】
1261:   {"CCA Busy Fraction       【  wlan.measure.rep.ccabusy 】
1262:   {"Map Field       【  wlan.measure.rep.mapfield 】
1263:   {"BSS       【  wlan.measure.rep.repmode.mapfield.bss 】
1264:   {"Orthogonal Frequency Division Multiplexing (OFDM) Preamble       【  wlan.measure.rep.repmode.mapfield.ofdm_preamble 】
1265:   {"Unidentified Signal       【  wlan.measure.rep.repmode.mapfield.unidentsig 】
1266:   {"Radar       【  wlan.measure.rep.repmode.mapfield.radar 】
1267:   {"Unmeasured       【  wlan.measure.rep.repmode.mapfield.unmeasured 】
1268:   {"Reserved       【  wlan.measure.rep.repmode.mapfield.reserved 】
1269:   {"Receive Power Indicator (RPI) Histogram Report       【  wlan.measure.rep.rpi.histogram_report 】
1270:   {"RPI 0 Density       【  wlan.measure.rep.rpi.rpi0density 】
1271:   {"RPI 1 Density       【  wlan.measure.rep.rpi.rpi1density 】
1272:   {"RPI 2 Density       【  wlan.measure.rep.rpi.rpi2density 】
1273:   {"RPI 3 Density       【  wlan.measure.rep.rpi.rpi3density 】
1274:   {"RPI 4 Density       【  wlan.measure.rep.rpi.rpi4density 】
1275:   {"RPI 5 Density       【  wlan.measure.rep.rpi.rpi5density 】
1276:   {"RPI 6 Density       【  wlan.measure.rep.rpi.rpi6density 】
1277:   {"RPI 7 Density       【  wlan.measure.rep.rpi.rpi7density 】
1278:   {"Operating Class       【  wlan.measure.rep.operatingclass 】
1279:   {"Channel Load       【  wlan.measure.rep.chanload 】
1280:   {"Reported Frame Information       【  wlan.measure.rep.frameinfo 】
1281:   {"Condensed PHY       【  wlan.measure.rep.frameinfo.phytype 】
1282:   {"Reported Frame Type       【  wlan.measure.rep.frameinfo.frametype 】
1283:   {"Received Channel Power Indicator (RCPI)       【  wlan.measure.rep.rcpi 】
1284:   {"Received Signal to Noise Indicator (RSNI)       【  wlan.measure.rep.rsni 】
1285:   {"BSSID Being Reported       【  wlan.measure.rep.bssid 】
1286:   {"Antenna ID       【  wlan.measure.rep.antid 】
1287:   {"ANPI       【  wlan.measure.rep.anpi 】
1288:   {"IPI Density 0       【  wlan.measure.rep.ipi_density0 】
1289:   {"IPI Density 1       【  wlan.measure.rep.ipi_density1 】
1290:   {"IPI Density 2       【  wlan.measure.rep.ipi_density2 】
1291:   {"IPI Density 3       【  wlan.measure.rep.ipi_density3 】
1292:   {"IPI Density 4       【  wlan.measure.rep.ipi_density4 】
1293:   {"IPI Density 5       【  wlan.measure.rep.ipi_density5 】
1294:   {"IPI Density 6       【  wlan.measure.rep.ipi_density6 】
1295:   {"IPI Density 7       【  wlan.measure.rep.ipi_density7 】
1296:   {"IPI Density 8       【  wlan.measure.rep.ipi_density8 】
1297:   {"IPI Density 9       【  wlan.measure.rep.ipi_density9 】
1298:   {"IPI Density 10       【  wlan.measure.rep.ipi_density10 】
1299:   {"Parent Timing Synchronization Function (TSF)       【  wlan.measure.rep.parenttsf 】
1300:   {"Length       【  wlan.measure.req.sub.length 】
1301:   {"SubElement ID       【  wlan.measure.req.beacon.sub.id 】
1302:   {"Unknown Data       【  wlan.measure.rep.unknown 】
1303:   {"Count       【  wlan.quiet.count 】
1304:   {"Period       【  wlan.quiet.period 】
1305:   {"Duration       【  wlan.quiet.duration 】
1306:   {"Offset       【  wlan.quiet.offset 】
1307:   {"Owner       【  wlan.dfs.owner 】
1308:   {"Recovery Interval       【  wlan.dfs.recovery_interval 】
1309:   {"Channel Map       【  wlan.dfs.channel_map 】
1310:   {"Channel Number       【  wlan.dfs.channel_number 】
1311:   {"Map       【  wlan.dfs.map 】
1312:   {"ERP Information       【  wlan.erp_info 】
1313:   {"Non ERP Present       【  wlan.erp_info.erp_present 】
1314:   {"Use Protection       【  wlan.erp_info.use_protection 】
1315:   {"Barker Preamble Mode       【  wlan.erp_info.barker_preamble_mode 】
1316:   {"Reserved       【  wlan.erp_info.reserved 】
1317:   {"Extended Capabilities       【  wlan.extcap 】
1318:   {"20/40 BSS Coexistence Management Support       【  wlan.extcap.b0 】
1319:   {"Reserved (was On-demand beacon)       【  wlan.extcap.b1 】
1320:   {"Extended Channel Switching       【  wlan.extcap.b2 】
1321:   {"Reserved (was WAVE indication)       【  wlan.extcap.b3 】
1322:   {"PSMP Capability       【  wlan.extcap.b4 】
1323:   {"Reserved       【  wlan.extcap.b5 】
1324:   {"S-PSMP Support       【  wlan.extcap.b6 】
1325:   {"Event       【  wlan.extcap.b7 】
1326:   {"Diagnostics       【  wlan.extcap.b8 】
1327:   {"Multicast Diagnostics       【  wlan.extcap.b9 】
1328:   {"Location Tracking       【  wlan.extcap.b10 】
1329:   {"FMS       【  wlan.extcap.b11 】
1330:   {"Proxy ARP Service       【  wlan.extcap.b12 】
1331:   {"Collocated Interference Reporting       【  wlan.extcap.b13 】
1332:   {"Civic Location       【  wlan.extcap.b14 】
1333:   {"Geospatial Location       【  wlan.extcap.b15 】
1334:   {"TFS       【  wlan.extcap.b16 】
1335:   {"WNM Sleep Mode       【  wlan.extcap.b17 】
1336:   {"TIM Broadcast       【  wlan.extcap.b18 】
1337:   {"BSS Transition       【  wlan.extcap.b19 】
1338:   {"QoS Traffic Capability       【  wlan.extcap.b20 】
1339:   {"AC Station Count       【  wlan.extcap.b21 】
1340:   {"Multiple BSSID       【  wlan.extcap.b22 】
1341:   {"Timing Measurement       【  wlan.extcap.b23 】
1342:   {"Channel Usage       【  wlan.extcap.b24 】
1343:   {"SSID List       【  wlan.extcap.b25 】
1344:   {"DMS       【  wlan.extcap.b26 】
1345:   {"UTC TSF Offset       【  wlan.extcap.b27 】
1346:   {"TPU Buffer STA Support       【  wlan.extcap.b28 】
1347:   {"TDLS Peer PSM Support       【  wlan.extcap.b29 】
1348:   {"TDLS channel switching       【  wlan.extcap.b30 】
1349:   {"Interworking       【  wlan.extcap.b31 】
1350:   {"QoS Map       【  wlan.extcap.b32 】
1351:   {"EBR       【  wlan.extcap.b33 】
1352:   {"SSPN Interface       【  wlan.extcap.b34 】
1353:   {"Reserved       【  wlan.extcap.b35 】
1354:   {"MSGCF Capability       【  wlan.extcap.b36 】
1355:   {"TDLS Support       【  wlan.extcap.b37 】
1356:   {"TDLS Prohibited       【  wlan.extcap.b38 】
1357:   {"TDLS Channel Switching Prohibited       【  wlan.extcap.b39 】
1358:   {"Reject Unadmitted Frame       【  wlan.extcap.b40 】
1359:     {      【  wlan.extcap.serv_int_granularity 】
1360:   {"Identifier Location       【  wlan.extcap.b44 】
1361:   {"U-APSD Coexistence       【  wlan.extcap.b45 】
1362:   {"WNM Notification       【  wlan.extcap.b46 】
1363:   {"QAB Capability       【  wlan.extcap.b47 】
1364:   {"UTF-8 SSID       【  wlan.extcap.b48 】
1365:   {"QMFActivated       【  wlan.extcap.b49 】
1366:   {"QMFReconfigurationActivated       【  wlan.extcap.b50 】
1367:   {"Robust AV Streaming       【  wlan.extcap.b51 】
1368:   {"Advanced GCR       【  wlan.extcap.b52 】
1369:   {"Mesh GCR       【  wlan.extcap.b53 】
1370:   {"SCS       【  wlan.extcap.b54 】
1371:   {"QLoad Report       【  wlan.extcap.b55 】
1372:   {"Alternate EDCA       【  wlan.extcap.b56 】
1373:   {"Unprotected TXOP Negotiation       【  wlan.extcap.b57 】
1374:   {"Protected TXOP Negotiation       【  wlan.extcap.b58 】
1375:   {"Reserved       【  wlan.extcap.b59 】
1376:   {"Protected QLoad Report       【  wlan.extcap.b61 】
1377:   {"TDLS Wider Bandwidth       【  wlan.extcap.b61 】
1378:   {"Operating Mode Notification       【  wlan.extcap.b62 】
1379:   {"Max Number Of MSDUs In A-MSDU       【  wlan.extcap.b63 】
1380:   {"Extended Capabilities       【  wlan.extcap 】
1381:   {"Alternate EDCA       【  wlan.extcap.b56 】
1382:   {"Unprotected TXOP Negotiation       【  wlan.extcap.b57 】
1383:   {"Protected TXOP Negotiation       【  wlan.extcap.b58 】
1384:   {"Reserved       【  wlan.extcap.b59 】
1385:   {"Protected QLoad Report       【  wlan.extcap.b61 】
1386:   {"TDLS Wider Bandwidth       【  wlan.extcap.b61 】
1387:   {"Operating Mode Notification       【  wlan.extcap.b62 】
1388:   {"Max Number Of MSDUs In A-MSDU       【  wlan.extcap.b63 】
1389:   {"Channel Schedule Management       【  wlan.extcap.b65 】
1390:   {"Geodatabase Inband Enabling Signal       【  wlan.extcap.b66 】
1391:   {"Network Channel Control       【  wlan.extcap.b67 】
1392:   {"White Space Map       【  wlan.extcap.b68 】
1393:   {"Channel Availability Query       【  wlan.extcap.b69 】
1394:   {"Fine Timing Measurement Responder       【  wlan.extcap.b70 】
1395:   {"Fine Timing Measurement Initiator       【  wlan.extcap.b71 】
1396:   {"FILS Capable       【  wlan.extcap.b72 】
1397:   {"Extended Spectrum Management Capable       【  wlan.extcap.b73 】
1398:   {"Future Channel Capable       【  wlan.extcap.b74 】
1399:   {"Reserved       【  wlan.extcap.b75 】
1400:   {"Reserved       【  wlan.extcap.b76 】
1401:   {"TWT Requester Support       【  wlan.extcap.b77 】
1402:   {"TWT Responder Support       【  wlan.extcap.b78 】
1403:   {"OBSS Narrow Bandwidth RU in UL OFDMA Tolerance Support       【  wlan.extcap.b79 】
1404:   {"Unknown       【  wlan.cisco.ccx1.unknown 】
1405:   {"Name       【  wlan.cisco.ccx1.name 】
1406:   {"Clients       【  wlan.cisco.ccx1.clients 】
1407:   {"Unknown2       【  wlan.cisco.ccx1.unknown2 】
1408:   {"BSSID       【  wlan.nreport.bssid 】
1409:   {"BSSID Information       【  wlan.nreport.bssid.info 】
1410:   {"AP Reachability       【  wlan.nreport.bssid.info.reachability 】
1411:   {"Security       【  wlan.nreport.bssid.info.security 】
1412:   {"Key Scope       【  wlan.nreport.bssid.info.keyscope 】
1413:   {"Capability       【  wlan.nreport.bssid.info.capability 】
1414:   {"Spectrum Management       【  wlan.nreport.bssid.info.capability.specmngt 】
1415:   {"QoS       【  wlan.nreport.bssid.info.capability.qos 】
1416:   {"APSD       【  wlan.nreport.bssid.info.capability.apsd 】
1417:   {"Radio Measurement       【  wlan.nreport.bssid.info.capability.radiomsnt 】
1418:   {"Delayed Block Ack       【  wlan.nreport.bssid.info.capability.dback 】
1419:   {"Immediate Block Ack       【  wlan.nreport.bssid.info.capability.iback 】
1420:   {"Mobility Domain       【  wlan.nreport.bssid.info.mobilitydomain 】
1421:   {"High Throughput Control (+HTC)       【  wlan.nreport.bssid.info.hthoughput 】
1422:   {"Very High Throughput (+VHT)       【  wlan.nreport.bssid.info.vht 】
1423:   {"Fine Timing Measurement (FTM)       【  wlan.nreport.bssid.info.ftm 】
1424:   {"High Efficiency (HE AP)       【  wlan.nreport.bssid.info.he 】
1425:   {"Extended Range BSS       【  wlan.nreport.bssid.info.er_bss 】
1426:   {"Reserved       【  wlan.nreport.bssid.info.reserved 】
1427:   {"Operating Class       【  wlan.nreport.opeclass 】
1428:   {"Channel Number       【  wlan.nreport.channumber 】
1429:   {"PHY Type       【  wlan.nreport.phytype 】
1430:   {"ID       【  wlan.nreport.subelem.id 】
1431:   {"Length       【  wlan.nreport.subelem.len 】
1432:   {"Data       【  wlan.nreport.subelem.data 】
1433:   {"Preference       【  wlan.nreport.subelem.bss_trn_can_pref 】
1434:   {"BSS Termination TSF       【  wlan.nreport.subelem.bss_ter_tsf 】
1435:   {"Duration       【  wlan.nreport.subelem.bss_dur 】
1436:   {"TSF Offset       【  wlan.nreport.subelem.tsf_offset 】
1437:   {"Beacon Interval       【  wlan.nreport.subelem.beacon_interval 】
1438:   {"Country Code       【  wlan.nreport.subelem.country_code 】
1439:   {"Current Operating Class       【  wlan.supopeclass.current 】
1440:   {"Alternate Operating Classes       【  wlan.supopeclass.alt 】
1441:   {"Type       【  wlan.wfa.ie.type 】
1442:   {"WPA Version       【  wlan.wfa.ie.wpa.version 】
1443:   {"Multicast Cipher Suite       【  wlan.wfa.ie.wpa.mcs 】
1444:   {"Multicast Cipher Suite OUI       【  wlan.wfa.ie.wpa.mcs.oui 】
1445:   {"Multicast Cipher Suite type       【  wlan.wfa.ie.wpa.mcs.type 】
1446:   {"Multicast Cipher Suite type       【  wlan.wfa.ie.wpa.mcs.type 】
1447:   {"Unicast Cipher Suite Count       【  wlan.wfa.ie.wpa.ucs.count 】
1448:   {"Unicast Cipher Suite List       【  wlan.wfa.ie.wpa.ucs.list 】
1449:   {"Unicast Cipher Suite       【  wlan.wfa.ie.wpa.ucs 】
1450:   {"Unicast Cipher Suite OUI       【  wlan.wfa.ie.wpau.cs.oui 】
1451:   {"Unicast Cipher Suite type       【  wlan.wfa.ie.wpa.ucs.type 】
1452:   {"Unicast Cipher Suite type       【  wlan.wfa.ie.wpa.ucs.type 】
1453:   {"Auth Key Management (AKM) Suite Count       【  wlan.wfa.ie.wpa.akms.count 】
1454:   {"Auth Key Management (AKM) List       【  wlan.wfa.ie.wpa.akms.list 】
1455:   {"Auth Key Management (AKM) Suite       【  wlan.wfa.ie.wpa.akms 】
1456:   {"Auth Key Management (AKM) OUI       【  wlan.wfa.ie.wpa.akms.oui 】
1457:   {"Auth Key Management (AKM) type       【  wlan.wfa.ie.wpa.akms.type 】
1458:   {"Auth Key Management (AKM) type       【  wlan.wfa.ie.wpa.type 】
1459:   {"WME Subtype       【  wlan.wfa.ie.wme.subtype 】
1460:   {"WME Version       【  wlan.wfa.ie.wme.version 】
1461:   {"WME QoS Info       【  wlan.wfa.ie.wme.qos_info 】
1462:   {"Max SP Length       【  wlan.wfa.ie.wme.qos_info.sta.max_sp_length 】
1463:   {"AC_BE       【  wlan.wfa.ie.wme.qos_info.sta.ac_be 】
1464:   {"AC_BK       【  wlan.wfa.ie.wme.qos_info.sta.ac_bk 】
1465:   {"AC_VI       【  wlan.wfa.ie.wme.qos_info.sta.ac_vi 】
1466:   {"AC_VO       【  wlan.wfa.ie.wme.qos_info.sta.ac_vo 】
1467:   {"Reserved       【  wlan.wfa.ie.wme.qos_info.sta.reserved 】
1468:   {"U-APSD       【  wlan.wfa.ie.wme.qos_info.ap.u_apsd 】
1469:   {"Parameter Set Count       【  wlan.wfa.ie.wme.qos_info.ap.parameter_set_count 】
1470:   {"Reserved       【  wlan.wfa.ie.wme.qos_info.ap.reserved 】
1471:   {"Reserved       【  wlan.wfa.ie.wme.reserved 】
1472:   {"Ac Parameters       【  wlan.wfa.ie.wme.acp 】
1473:   {"ACI / AIFSN Field       【  wlan.wfa.ie.wme.acp.aci_aifsn 】
1474:   {"ACI       【  wlan.wfa.ie.wme.acp.aci 】
1475:   {"Admission Control Mandatory       【  wlan.wfa.ie.wme.acp.acm 】
1476:   {"AIFSN       【  wlan.wfa.ie.wme.acp.aifsn 】
1477:   {"Reserved       【  wlan.wfa.ie.wme.acp.reserved 】
1478:   {"ECW       【  wlan.wfa.ie.wme.acp.ecw 】
1479:   {"ECW Max       【  wlan.wfa.ie.wme.acp.ecw.max 】
1480:   {"ECW Min       【  wlan.wfa.ie.wme.acp.ecw.min 】
1481:   {"CW Max       【  wlan.wfa.ie.wme.acp.cw.max 】
1482:   {"CW Min       【  wlan.wfa.ie.wme.acp.cw.min 】
1483:   {"TXOP Limit       【  wlan.wfa.ie.wme.acp.txop_limit 】
1484:   {"TS Info       【  wlan.wfa.ie.wme.tspec.ts_info 】
1485:   {"TID       【  wlan.wfa.ie.wme.tspec.ts_info.tid 】
1486:   {"Direction       【  wlan.wfa.ie.wme.tspec.ts_info.dir 】
1487:   {"PSB       【  wlan.wfa.ie.wme.tspec.ts_info.psb 】
1488:   {"UP       【  wlan.wfa.ie.wme.tspec.ts_info.up 】
1489:   {"Reserved       【  wlan.wfa.ie.wme.tspec.ts_info.reserved 】
1490:   {"Normal MSDU Size       【  wlan.wfa.ie.wme.tspec.nor_msdu 】
1491:   {"Maximum MSDU Size       【  wlan.wfa.ie.wme.tspec.max_msdu 】
1492:   {"Minimum Service Interval       【  wlan.wfa.ie.wme.tspec.min_srv 】
1493:   {"Maximum Service Interval       【  wlan.wfa.ie.wme.tspec.max_srv 】
1494:   {"Inactivity Interval       【  wlan.wfa.ie.wme.tspec.inact_int 】
1495:   {"Suspension Interval       【  wlan.wfa.ie.wme.tspec.susp_int 】
1496:   {"Service Start Time       【  wlan.wfa.ie.wme.tspec.srv_start 】
1497:   {"Minimum Data Rate       【  wlan.wfa.ie.wme.tspec.min_data 】
1498:   {"Mean Data Rate       【  wlan.wfa.ie.wme.tspec.mean_data 】
1499:   {"Peak Data Rate       【  wlan.wfa.ie.wme.tspec.peak_data 】
1500:   {"Burst Size       【  wlan.wfa.ie.wme.tspec.burst_size 】
1501:   {"Delay Bound       【  wlan.wfa.ie.wme.tspec.delay_bound 】
1502:   {"Minimum PHY Rate       【  wlan.wfa.ie.wme.tspec.min_phy 】
1503:   {"Surplus Bandwidth Allowance       【  wlan.wfa.ie.wme.tspec.surplus 】
1504:   {"Medium Time       【  wlan.wfa.ie.wme.tspec.medium 】
1505:   {"BSSID       【  wlan.wfa.ie.owe.bssid 】
1506:   {"SSID length       【  wlan.wfa.ie.owe.ssid_length 】
1507:   {"SSID       【  wlan.wfa.ie.owe.ssid 】
1508:   {"Band info       【  wlan.wfa.ie.owe.band_info 】
1509:   {"Channel info       【  wlan.wfa.ie.owe.channel_info 】
1510:   {"GTK       【  wlan.rsn.ie.gtk.key 】
1511:   {"KeyID       【  wlan.rsn.ie.gtk.keyid 】
1512:   {"Tx       【  wlan.rsn.ie.gtk.tx 】
1513:   {"Reserved       【  wlan.rsn.ie.gtk.reserved1 】
1514:   {"Reserved       【  wlan.rsn.ie.gtk.reserved2 】
1515:   {"PMKID       【  wlan.rsn.ie.pmkid 】
1516:   {"KeyId       【  wlan.rsn.ie.igtk.keyid 】
1517:   {"IPN       【  wlan.rsn.ie.igtk.ipn 】
1518:   {"IGTK       【  wlan.rsn.ie.igtk.key 】
1519:   {"RSN Unknown       【  wlan.rsn.ie.unknown 】
1520:   {"Type       【  wlan.marvell.ie.type 】
1521:   {"Subtype       【  wlan.marvell.ie.subtype 】
1522:   {"Version       【  wlan.marvell.ie.version 】
1523:   {"Path Selection Protocol       【  wlan.marvell.ie.proto_id 】
1524:   {"Path Selection Metric       【  wlan.marvell.ie.metric_id 】
1525:   {"Mesh Capabilities       【  wlan.marvell.ie.cap 】
1526:   {"Marvell IE data       【  wlan.marvell.data 】
1527:   {"Type       【  wlan.atheros.ie.type 】
1528:   {"Subtype       【  wlan.atheros.ie.subtype 】
1529:   {"Version       【  wlan.atheros.ie.version 】
1530:   {"Turbo Prime       【  wlan.ie.atheros.capabilities.turbop 】
1531:   {"Compression       【  wlan.ie.atheros.capabilities.comp 】
1532:   {"Fast Frames       【  wlan.ie.atheros.capabilities.ff 】
1533:   {"eXtended Range       【  wlan.ie.atheros.capabilities.xr 】
1534:   {"Advanced Radar       【  wlan.ie.atheros.capabilities.ar 】
1535:   {"Burst       【  wlan.ie.atheros.capabilities.burst 】
1536:   {"CWMin tuning       【  wlan.ie.atheros.capabilities.wme 】
1537:   {"Boost       【  wlan.ie.atheros.capabilities.boost 】
1538:   {"Capabilities       【  wlan.atheros.ie.advcap.cap 】
1539:   {"Default key index       【  wlan.atheros.ie.advcap.defkey 】
1540:   {"Info       【  wlan.atheros.ie.xr.info 】
1541:   {"Base BSS Id       【  wlan.atheros.ie.xr.base_bssid 】
1542:   {"XR BSS Id       【  wlan.atheros.ie.xr.xr_bssid 】
1543:   {"XR Beacon Interval       【  wlan.atheros.ie.xr.xr_beacon 】
1544:   {"Base capabilities       【  wlan.atheros.ie.xr.base_cap 】
1545:   {"XR capabilities       【  wlan.atheros.ie.xr.xr_cap 】
1546:   {"Atheros IE data       【  wlan.atheros.data 】
1547:   {"Aironet IE type       【  wlan.aironet.type 】
1548:   {"Aironet IE CCX DTCP       【  wlan.aironet.dtpc 】
1549:   {"Aironet IE CCX DTCP Unknown       【  wlan.aironet.dtpc_unknown 】
1550:   {"Aironet IE CCX version       【  wlan.aironet.version 】
1551:     {"Aironet IE data       【  wlan.aironet.data 】
1552:   {"QBSS Version       【  wlan.qbss.version 】
1553:   {"Station Count       【  wlan.qbss.scount 】
1554:   {"Channel Utilization       【  wlan.qbss.cu 】
1555:   {"Available Admission Capacity       【  wlan.qbss.adc 】
1556:   {"Channel Utilization       【  wlan.qbss2.cu 】
1557:   {"G.711 CU Quantum       【  wlan.qbss2.glimit 】
1558:   {"Call Admission Limit       【  wlan.qbss2.cal 】
1559:   {"Station Count       【  wlan.qbss2.scount 】
1560:   {"Aironet IE QoS reserved       【  wlan.aironet.qos.reserved 】
1561:   {"Aironet IE QoS paramset       【  wlan.aironet.qos.paramset 】
1562:   {"Aironet IE QoS valueset       【  wlan.aironet.qos.val 】
1563:   {"Aironet IE Client MFP       【  wlan.aironet.clientmfp 】
1564:   {"Type       【  wlan.vs.nintendo.type 】
1565:   {"Length       【  wlan.vs.nintendo.length 】
1566:   {"Servicelist       【  wlan.vs.nintendo.servicelist 】
1567:   {"Service       【  wlan.vs.nintendo.service 】
1568:   {"Console ID       【  wlan.vs.nintendo.consoleid 】
1569:   {"Unknown       【  wlan.vs.nintendo.unknown 】
1570:   {"Subtype       【  wlan.vs.aruba.subtype 】
1571:   {"AP Name       【  wlan.vs.aruba.ap_name 】
1572:   {"Data       【  wlan.vs.aruba.data 】
1573:   {"Unknown       【  wlan.vs.routerboard.unknown 】
1574:   {"Sub IE       【  wlan.vs.routerboard.subitem 】
1575:   {"Subtype       【  wlan.vs.routerboard.subtype 】
1576:   {"Sublength       【  wlan.vs.routerboard.sublength 】
1577:   {"Subdata       【  wlan.vs.routerboard.subdata 】
1578:   {"Subtype 1 Prefix       【  wlan.vs.routerboard.subtype1_prefix 】
1579:   {"Subtype 1 Data       【  wlan.vs.routerboard.subtype1_data 】
1580:   {"Sub IE       【  wlan.vs.meru.unknown 】
1581:   {"Subtype       【  wlan.vs.meru.subtype 】
1582:   {"Sublength       【  wlan.vs.meru.sublength 】
1583:   {"Subdata       【  wlan.vs.meru.subdata 】
1584:   {"Subtype       【  wlan.vs.extreme.subtype 】
1585:   {"Subdata       【  wlan.vs.extreme.subdata 】
1586:   {"Unknown       【  wlan.vs.extreme.unknown 】
1587:   {"AP Length       【  wlan.vs.extreme.ap_length 】
1588:   {"AP Name       【  wlan.vs.extreme.ap_name 】
1589:   {"Unknown       【  wlan.vs.aerohive.unknown 】
1590:   {"Host Name Length       【  wlan.vs.aerohive.hostname_length 】
1591:   {"Host Name       【  wlan.vs.aerohive.hostname 】
1592:   {"Data       【  wlan.vs.aerohive.data 】
1593:   {"Traffic Stream (TS) Info       【  wlan.ts_info 】
1594:   {"Traffic Type       【  wlan.ts_info.type 】
1595:   {"Traffic Stream ID (TSID)       【  wlan.ts_info.tsid 】
1596:   {"Direction       【  wlan.ts_info.dir 】
1597:   {"Access Policy       【  wlan.ts_info.dir 】
1598:   {"Aggregation       【  wlan.ts_info.agg 】
1599:   {"Automatic Power-Save Delivery (APSD)       【  wlan.ts_info.apsd 】
1600:   {"User Priority       【  wlan.ts_info.up 】
1601:   {"Ack Policy       【  wlan.ts_info.ack 】
1602:   {"Schedule       【  wlan.ts_info.sched 】
1603:   {"Reserved       【  wlan.ts_info.rsv 】
1604:   {"Normal MSDU Size       【  wlan.tspec.nor_msdu 】
1605:   {"Maximum MSDU Size       【  wlan.tspec.max_msdu 】
1606:   {"Minimum Service Interval       【  wlan.tspec.min_srv 】
1607:   {"Maximum Service Interval       【  wlan.tspec.max_srv 】
1608:   {"Inactivity Interval       【  wlan.tspec.inact_int 】
1609:   {"Suspension Interval       【  wlan.tspec.susp_int 】
1610:   {"Service Start Time       【  wlan.tspec.srv_start 】
1611:   {"Minimum Data Rate       【  wlan.tspec.min_data 】
1612:   {"Mean Data Rate       【  wlan.tspec.mean_data 】
1613:   {"Peak Data Rate       【  wlan.tspec.peak_data 】
1614:   {"Burst Size       【  wlan.tspec.burst_size 】
1615:   {"Delay Bound       【  wlan.tspec.delay_bound 】
1616:   {"Minimum PHY Rate       【  wlan.tspec.min_phy 】
1617:   {"Surplus Bandwidth Allowance       【  wlan.tspec.surplus 】
1618:   {"Medium Time       【  wlan.tspec.medium 】
1619:   {"DMG attributes       【  wlan.tspec.dmg 】
1620:   {"Traffic Stream (TS) Delay       【  wlan.ts_delay 】
1621:   {"Processing       【  wlan.tclas_proc.processing 】
1622:   {"Extended Supported Rates       【  wlan.extended_supported_rates 】
1623:   {"Schedule Info       【  wlan.sched.sched_info 】
1624:   {"Schedule Aggregation       【  wlan.sched_info.agg 】
1625:   {"Schedule Traffic Stream ID (TSID)       【  wlan.sched_info.tsid 】
1626:   {"Schedule Direction       【  wlan.sched_info.dir 】
1627:   {"Service Start Time       【  wlan.sched.srv_start 】
1628:   {"Service Interval       【  wlan.sched.srv_int 】
1629:   {"Specification Interval       【  wlan.sched.spec_int 】
1630:   {"Aruba Type       【  wlan.aruba.type 】
1631:   {"Aruba Heartbeat Sequence       【  wlan.aruba.heartbeat_sequence 】
1632:   {"Aruba MTU Size       【  wlan.aruba.mtu_size 】
1633:   {"HT Control (+HTC)       【  wlan.htc 】
1634:   {"VHT       【  wlan.htc.vht 】
1635:   {"HE       【  wlan.htc.he 】
1636:   {"Control ID       【  wlan.htc.he.a_control.ctrl_id 】
1637:   {"HE TB PPDU Length       【  wlan.htc.he.a_control.umrs.he_tb_ppdu_len 】
1638:   {"RU Allocation       【  wlan.htc.he.a_control.umrs.ru_allocation 】
1639:   {"DL Tx Power       【  wlan.htc.he.a_control.umrs.dl_tx_power 】
1640:   {"UL Target RSSI       【  wlan.htc.he.a_control.umrs.ul_target_rssi 】
1641:   {"UL MCS       【  wlan.htc.he.a_control.umrs.ul_mcs 】
1642:   {"reserved       【  wlan.htc.he.a_control.umrs.reserved 】
1643:   {"Rx NSS       【  wlan.htc.he.a_control.om.rx_nss 】
1644:   {"Channel Width       【  wlan.htc.he.a_control.om.channel_width 】
1645:   {"UL MU Disable       【  wlan.htc.he.a_control.om.ul_mu_disable 】
1646:   {"Tx NSTS       【  wlan.htc.he.a_control.om.tx_nsts 】
1647:   {"Reserved       【  wlan.htc.he.a_control.om.reserved 】
1648:   {"Unsolicited MFB       【  wlan.htc.he.a_control.hla.unsolicited_mfb 】
1649:   {"MRQ       【  wlan.htc.he.a_control.hla.mrq 】
1650:   {"NSS       【  wlan.htc.he.a_control.hla.NSS 】
1651:   {"HE-MCS       【  wlan.htc.he.a_control.hla.he_mcs 】
1652:   {"DCM       【  wlan.htc.he.a_control.hla.dcm 】
1653:   {"RU       【  wlan.htc.he.a_control.hla.ru 】
1654:   {"BW       【  wlan.htc.he.a_control.hla.bw 】
1655:   {"MSI/PPDU Type       【  wlan.htc.he.a_control.hla.msi_ppdu_type 】
1656:   {"Tx BF       【  wlan.htc.he.a_control.hla.tx_bf 】
1657:   {"Reserved       【  wlan.htc.he.a_control.hla.reserved 】
1658:   {"ACI Bitmap       【  wlan.htc.he.a_control.bsr.aci_bitmap 】
1659:   {"Delta TID       【  wlan.htc.he.a_control.bsr.delta_tid 】
1660:   {"ACI High       【  wlan.htc.he.a_control.bsr.aci_high 】
1661:   {"Scaling Factor       【  wlan.htc.he.a_control.bsr.scaling_factor 】
1662:   {"Queue Size High       【  wlan.htc.he.a_control.bsr.queue_size_high 】
1663:   {"Queue Size All       【  wlan.htc.he.a_control.bsr.queue_size_all 】
1664:   {"UL Power Headroom       【  wlan.htc.he.a_control.uph.ul_power_headroom 】
1665:   {"Minimum Transmit Power Flag       【  wlan.htc.he.a_control.uph.min_transmit_power_flag 】
1666:   {"Reserved       【  wlan.htc.he.a_control.uph.reserved 】
1667:   {"Available Channel Bitmap       【  wlan.htc.he.a_control.bqr.avail_chan_bitmap 】
1668:   {"Reserved       【  wlan.htc.he.a_control.bqr.reserved 】
1669:   {"AC Constraint       【  wlan.htc.he.a_control.cci.ac_constraint 】
1670:   {"RDG/More PPDU       【  wlan.htc.he.a_control.cci.rdg_more_ppdu 】
1671:   {"SR PPDU Indication       【  wlan.htc.he.a_control.cci.sr_ppdu_indic 】
1672:   {"Reserved       【  wlan.htc.htc.a_control.cci.reserved 】
1673:   {"HE Trigger Common Info       【  wlan.trigger.he.common_info 】
1674:   {"Trigger Type       【  wlan.trigger.he.trigger_type 】
1675:   {"UL Length       【  wlan.trigger.he.ul_length 】
1676:   {"More TF       【  wlan.trigger.he.more_tf 】
1677:   {"CS Required       【  wlan.trigger.he.cs_required 】
1678:   {"UL BW       【  wlan.trigger.he.ul_bw 】
1679:   {"GI And LTF Type       【  wlan.trigger.he.gi_and_ltf_type 】
1680:   {"MU-MIMO LTF Mode       【  wlan.trigger.he.mu_mimo_ltf_mode 】
1681: 								{{       【  wlan.trigger.he.num_he_ltf_syms_and_midamble_per 】
1682:   {"UL STBC       【  wlan.trigger.he.ul_stbc 】
1683:   {"LDPC Extra Symbol Segment       【  wlan.trigger.he.ldpc_extra_symbol_segment 】
1684:   {"AP TX Power       【  wlan.trigger.he.ap_tx_power 】
1685:   {"Packet Extension       【  wlan.trigger.he.packet_extension 】
1686:   {"Spatial Reuse       【  wlan.trigger.he.spatial_reuse 】
1687:   {"UL HE-SIG-A2 Reserved       【  wlan.trigger.he.ul_he_sig_a2_reserved 】
1688:   {"Doppler       【  wlan.trigger.he.doppler 】
1689:   {"Reserved       【  wlan.trigger.he.reserved 】
1690:   {"BAR Control       【  wlan.trigger.he.common_info.bar_ctrl 】
1691:   {"BA Ack Policy       【  wlan.trigger.he.common_info.bar_ctrl.ba_ack_policy 】
1692:   {"BA Type       【  wlan.trigger.he.common_info.bar_ctrl.ba_type 】
1693:   {"Reserved       【  wlan.trigger.he.common_info.bar_ctrl.reserved 】
1694:   {"TID_INFO       【  wlan.trigger.he.common_info.bar_ctrl.tid_info 】
1695:   {"BAR Information       【  wlan.trigger.he.common_info.bar_info 】
1696:   {      【  wlan.trigger.he.common_info.bar_info.blk_ack_starting_seq_ctrl 】
1697:   {"User Info       【  wlan.trigger.he.user_info 】
1698:   {"MPDU MU Spacing Factor       【  wlan.trigger.he.mpdu_mu_spacing_factor 】
1699:   {"TID Aggregation Limit       【  wlan.trigger.he.tid_aggregation_limit 】
1700:   {"Reserved       【  wlan.trigger.he.reserved1 】
1701:   {"Preferred AC       【  wlan.trigger.he.preferred_ac 】
1702:   {"Basic Trigger Dependent User Info       【  wlan.trigger.he.basic_user_info 】
1703:   {"Starting AID       【  wlan.trigger.he.starting_aid 】
1704:   {"Reserved       【  wlan.trigger.he.reserved2 】
1705:   {"Feedback Type       【  wlan.trigger.he.feedback_type 】
1706:   {"Reserved       【  wlan.trigger.he.reserved3 】
1707:   {"Target RSSI       【  wlan.trigger.he.target_rssi 】
1708:   {"Multiplexing Flag       【  wlan.trigger.he.multiplexing_flag 】
1709:   {"NFRP Trigger Dependent User Unfo       【  wlan.trigger.he.nfrp_user_info 】
1710:   {"Feedback Segment Retransmission Bitmap       【  wlan.trigger.he.feedback_bm 】
1711:   {"AID12       【  wlan.trigger.he.user_info.aid12 】
1712:   {"RU Allocation Region       【  wlan.trigger.he.ru_allocation_region 】
1713:   {"RU Allocation       【  wlan.trigger.he.ru_allocation 】
1714:   {"Coding Type       【  wlan.trigger.he.coding_type 】
1715:   {"MCS       【  wlan.trigger.he.mcs 】
1716:   {"DCM       【  wlan.trigger.he.dcm 】
1717:   {"Starting Spatial Stream       【  wlan.trigger.he.ru_starting_spatial_stream 】
1718:   {"Number Of Spatial Streams       【  wlan.trigger.he.ru_number_of_spatial_stream 】
1719:   {"Number of RA-RU       【  wlan.trigger.he.ru_number_of_ra_ru 】
1720:   {"No More RA-RU       【  wlan.trigger.he.ru_no_more_ra_ru 】
1721:   {"Target RSSI       【  wlan.trigger.he.target_rssi 】
1722:   {"Reserved       【  wlan.trigger.he.user_reserved 】
1723:   {"Control       【  wlan.ext_tag.quiet_time_period.control 】
1724:   {"Quiet Period Duration       【  wlan.ext_tag.quiet_time_period.setup.duration 】
1725:   {"Service Specific Identifier       【  wlan.ext_tag.quiet_time_period.setup.srv_specific_identif 】
1726:   {"Dialog Token       【  wlan.ext_tag.quiet_time_period.request.dialog_token 】
1727:   {"Quiet Period Offset       【  wlan.ext_tag.quiet_time_period.request.offset 】
1728:   {"Quiet Period Duration       【  wlan.ext_tag.quiet_time_period.request.duration 】
1729:   {"Quiet Period Interval       【  wlan.ext_tag.quiet_time_period.request.interval 】
1730:   {"Repetition Count       【  wlan.ext_tag.quiet_time_period.request.repetition_count 】
1731:   {"Service Specific Identifier       【  wlan.ext_tag.quiet_time_period.request.srv_specific_identif 】
1732:   {"Dialog Token       【  wlan.ext_tag.quiet_time_period.response.dialog_token 】
1733:   {"Status Code       【  wlan.ext_tag.quiet_time_period.response.status_code 】
1734:   {"Quiet Period Offset       【  wlan.ext_tag.quiet_time_period.response.offset 】
1735:   {"Quiet Period Duration       【  wlan.ext_tag.quiet_time_period.response.duration 】
1736:   {"Quiet Period Interval       【  wlan.ext_tag.quiet_time_period.response.interval 】
1737:   {"Repetition Count       【  wlan.ext_tag.quiet_time_period.response.repetition_count 】
1738:   {"Service Specific Identifier       【  wlan.ext_tag.quiet_time_period.response.srv_specific_identif 】
1739:   {"Sounding Dialog Token Number       【  wlan.he_ndp.token.number 】
1740:   {"HE       【  wlan.vht_he.token.he 】
1741:   {"Reserved       【  wlan.he_ndp.token.reserved 】
1742:   {"Sounding Dialog Token       【  wlan.he_ndp.token 】
1743:   {"STA Info       【  wlan.he_ndp.sta_info 】
1744:   {"AID11       【  wlan.he_ndp.sta_info.aid11 】
1745:   {"RU Start Index       【  wlan.he_ndp.sta_info.ru_start 】
1746:   {"RU End Index       【  wlan.he_ndp.sta_info.ru_end 】
1747:   {"Feedback Type and Ng       【  wlan.he_ndp.sta_info.feedback_type_and_ng 】
1748:   {"Disambiguation       【  wlan.he_ndp.sta_info.disambiguation 】
1749:   {"Codebook Size       【  wlan.he_ndp.sta_info.codebook_size 】
1750:   {"Nc       【  wlan.he_ndp.sta_info.nc 】
1751:   {"Link Adaptation Control (LAC)       【  wlan.htc.lac 】
1752:   {"Training Request (TRQ)       【  wlan.htc.lac.trq 】
1753:   {"Antenna Selection Indication (ASELI)       【  wlan.htc.lac.mai.aseli 】
1754:   {"MCS Request (MRQ)       【  wlan.htc.lac.mai.mrq 】
1755:   {"MCS Request Sequence Identifier (MSI)       【  wlan.htc.lac.mai.msi 】
1756:   {"Reserved       【  wlan.htc.lac.mai.reserved 】
1757:   {"MCS Feedback Sequence Identifier (MFSI)       【  wlan.htc.lac.mfsi 】
1758:   {"Antenna Selection (ASEL) Command       【  wlan.htc.lac.asel.command 】
1759:   {"Antenna Selection (ASEL) Data       【  wlan.htc.lac.asel.data 】
1760:   {"MCS Feedback (MFB)       【  wlan.htc.lac.mfb 】
1761:   {"Calibration Position       【  wlan.htc.cal.pos 】
1762:   {"Calibration Sequence Identifier       【  wlan.htc.cal.seq 】
1763:   {"Reserved       【  wlan.htc.reserved1 】
1764:   {"CSI/Steering       【  wlan.htc.csi_steering 】
1765:   {"NDP Announcement       【  wlan.htc.ndp_announcement 】
1766:   {"Reserved       【  wlan.htc.reserved2 】
1767:   {"MRQ       【  wlan.htc.mrq 】
1768:   {"MSI       【  wlan.htc.msi 】
1769:   {"Reserved       【  wlan.htc.msi_stbc_reserved 】
1770:   {"Compressed MSI       【  wlan.htc.compressed_msi 】
1771:   {"PPDU was STBC encoded       【  wlan.htc.ppdu_stbc_encoded 】
1772:   {"MFSI       【  wlan.htc.mfsi 】
1773:   {"GID-L       【  wlan.htc.gid_l 】
1774:   {"MFB       【  wlan.htc.mfb 】
1775:   {"NUM_STS       【  wlan.htc.num_sts 】
1776:   {"VHT-MCS       【  wlan.htc.vht_mcs 】
1777:   {"BW       【  wlan.htc.bw 】
1778:   {"SNR       【  wlan.htc.snr 】
1779:   {"Reserved       【  wlan.htc.reserved3 】
1780:   {"GID-H       【  wlan.htc.gid_h 】
1781:   {"Coding type       【  wlan.htc.coding_type 】
1782:   {"FB Tx type       【  wlan.htc.fb_tx_type 】
1783:   {"Unsolicited MFB       【  wlan.htc.unsolicited_mfb 】
1784:   {"AC Constraint       【  wlan.htc.ac_constraint 】
1785:   {"RDG/More PPDU       【  wlan.htc.rdg_more_ppdu 】
1786:   {"Mobility Domain Identifier       【  wlan.mobility_domain.mdid 】
1787:   {"FT Capability and Policy       【  wlan.mobility_domain.ft_capab 】
1788:     {      【  wlan.mobility_domain.ft_capab.ft_over_ds 】
1789:     {      【  wlan.mobility_domain.ft_capab.resource_req 】
1790:   {"MIC Control       【  wlan.ft.mic_control 】
1791:   {"Element Count       【  wlan.ft.element_count 】
1792:   {"MIC       【  wlan.ft.mic 】
1793:   {"ANonce       【  wlan.ft.anonce 】
1794:   {"SNonce       【  wlan.ft.snonce 】
1795:   {"Subelement ID       【  wlan.ft.subelem.id 】
1796:   {"Length       【  wlan.ft.subelem.len 】
1797:   {"Data       【  wlan.ft.subelem.data 】
1798:   {"PMK-R1 key holder identifier (R1KH-ID)       【  wlan.ft.subelem.r1kh_id 】
1799:   {"Key Info       【  wlan.ft.subelem.gtk.key_info 】
1800:   {"Key ID       【  wlan.ft.subelem.gtk.key_id 】
1801:   {"Key Length       【  wlan.ft.subelem.gtk.key_length 】
1802:   {"RSC       【  wlan.ft.subelem.gtk.rsc 】
1803:   {"GTK       【  wlan.ft.subelem.gtk.key 】
1804:   {"PMK-R0 key holder identifier (R0KH-ID)       【  wlan.ft.subelem.r0kh_id 】
1805:   {"Key ID       【  wlan.ft.subelem.igtk.key_id 】
1806:   {"IPN       【  wlan.ft.subelem.igtk.ipn 】
1807:   {"Key Length       【  wlan.ft.subelem.igtk.key_length 】
1808:   {"Wrapped Key (IGTK)       【  wlan.ft.subelem.igtk.key 】
1809:   {"Resource Handshake Identifier       【  wlan.ric_data.id 】
1810:   {"Resource Descriptor Count       【  wlan.ric_data.desc_cnt 】
1811:   {"Status Code       【  wlan.ric_data.status_code 】
1812:   {"Scan Passive Dwell       【  wlan.obss.spd 】
1813:   {"Scan Active Dwell       【  wlan.obss.sad 】
1814:   {"Channel Width Trigger Scan Interval       【  wlan.obss.cwtsi 】
1815:   {"Scan Passive Total Per Channel       【  wlan.obss.sptpc 】
1816:   {"Scan Active Total Per Channel       【  wlan.obss.satpc 】
1817:   {"Width Channel Transition Delay Factor       【  wlan.obss.wctdf 】
1818:   {"Scan Activity Threshold       【  wlan.obss.sat 】
1819:   {"Group Data Cypher Suite OUI       【  wlan.osen.gdcs.oui 】
1820:   {"Group Data Cypher Suite type       【  wlan.osen.gdcs.type 】
1821:   {"OSEN Pairwise Cipher Suite Count       【  wlan.osen.pwcs.count 】
1822:   {"OSEN Pairwise Cypher Suite OUI       【  wlan.osen.pwcs.oui 】
1823:   {"OSEN Pairwise Cypher Suite type       【  wlan.osen.pwcs.type 】
1824:   {"OSEN AKM Cipher Suite Count       【  wlan.osen.akms.count 】
1825:   {"OSEN AKM Cipher Suite OUI       【  wlan.osen.akms.oui 】
1826:   {"OSEN AKM Cipher Suite Type       【  wlan.osen.akms.type 】
1827:   {"RSN Pre-Auth capabilities       【  wlan.osen.rsn.capabilities.preauth 】
1828:   {"RSN No Pairwise capabilities       【  wlan.osen.rsn.capabilities.no_pairwise 】
1829:     {      【  wlan.osen.rsn.capabilities.ptksa_replay_counter 】
1830:     {      【  wlan.osen.rsn.capabilities.gtksa_replay_counter 】
1831:   {"OSEN Group Management Cipher Suite OUI       【  wlan.osen.gmcs.oui 】
1832:   {"OSEN Group Management Cipher Suite Type       【  wlan.osen.gmcs.type 】
1833:   {"Management Frame Protection Required       【  wlan.osen.rsn.capabilities.mfpr 】
1834:   {"Management Frame Protection Capable       【  wlan.osen.rsn.capabilities.mfpc 】
1835:   {"Joint Multi-band RSNA       【  wlan.osen.rsn.capabilities.jmr 】
1836:   {"PeerKey Enabled       【  wlan.osen.rsn.capabilities.peerkey 】
1837:   {"RSN Capability Flags       【  wlan.osen.rsn.cabailities.flags 】
1838:   {"SPP A-MSDU Capable       【  wlan.osen.rsn.capabilities.spp_a_msdu_cap 】
1839:   {"SPP A-MSDU Required       【  wlan.osen.rsn.capabilities.spp_a_msdu_req 】
1840:   {"Protected Block Ack Agreement Capable       【  wlan.osen.rsn.capabilities.pbac 】
1841:     {      【  wlan.osn.rsn.extended_key_id_iaf 】
1842:   {"Reserved       【  wlan.osen.rsn.capabilities.reserved 】
1843:   {"OSEN PMKID Count       【  wlan.osen.pmkid.count 】
1844:   {"OSEN PKMID       【  wlan.osen.pmkid.bytes 】
1845:   {"Resource Type       【  wlan.ric_desc.rsrc_type 】
1846:   {"Variable Params       【  wlan.ric_desc.var_params 】
1847:   {"KeyID       【  wlan.mmie.keyid 】
1848:   {"IPN       【  wlan.mmie.ipn 】
1849:   {"MIC       【  wlan.mmie.mic 】
1850:   {"Version       【  wlan.wapi.version 】
1851:   {"AKM Suite Count       【  wlan.wapi.akm_suite.count 】
1852:   {"AKM Suite OUI       【  wlan.wapi.akm_suite.oui 】
1853:   {"AKM Suite Type       【  wlan.wapi.akm_suite.type 】
1854:   {"Unicast Cipher Suite Count       【  wlan.wapi.unicast_cipher.suite.count 】
1855:   {"Unicast Cipher Suite OUI       【  wlan.wapi.unicast_cipher.suite.oui 】
1856:   {"Unicast Cipher Suite Type       【  wlan.wapi.unicast_cipher.suite.type 】
1857:   {"Multicast Cipher Suite OUI       【  wlan.wapi.multicast_cipher.suite.oui 】
1858:   {"Multicast Cipher Suite Type       【  wlan.wapi.multicast_cipher.suite.type 】
1859:   {"WAPI Capability Info       【  wlan.wapi.capab 】
1860:   {"Supports Preauthentication?       【  wlan.wapi.capab.preauth 】
1861:   {"Reserved       【  wlan.wapi.capab.rsvd 】
1862:   {"No of BKID's       【  wlan.wapi.bkid.count 】
1863:   {"BKID       【  wlan.wapi.bkid 】
1864:   {"BSS Max Idle Period (1000 TUs)       【  wlan.bss_max_idle.period 】
1865:     {      【  wlan.bss_max_idle.options.protected 】
1866:   {"TFS ID       【  wlan.tfs_request.id 】
1867:     {      【  wlan.tfs_request.action_code.delete_after_match 】
1868:     {      【  wlan.tfs_request.action_code.notify 】
1869:   {"Subelement ID       【  wlan.tfs_request.subelem.id 】
1870:   {"Length       【  wlan.tfs_request.subelem.len 】
1871:   {"Subelement Data       【  wlan.tfs_request.subelem 】
1872:   {"Subelement ID       【  wlan.tfs_response.subelem.id 】
1873:   {"Length       【  wlan.tfs_response.subelem.len 】
1874:   {"Subelement Data       【  wlan.tfs_response.subelem 】
1875:   {"TFS Response Status       【  wlan.tfs_response.status 】
1876:   {"TFS ID       【  wlan.tfs_response.tfs_id 】
1877:   {"Action Type       【  wlan.wnm_sleep_mode.action_type 】
1878:     {      【  wlan.wnm_sleep_mode.response_status 】
1879:   {"WNM-Sleep Interval       【  wlan.wnm_sleep_mode.interval 】
1880:   {"Subelement ID       【  wlan.wnm_subelt.id 】
1881:   {"Subelement len       【  wlan.wnm_subelt.len 】
1882:   {"Timing capabilities       【  wlan.time_adv.timing_capab 】
1883:   {"Time Value       【  wlan.time_adv.time_value 】
1884:   {"Time Value: Year       【  wlan.time_adv.time_value.year 】
1885:   {"Time Value: Month       【  wlan.time_adv.time_value.month 】
1886:   {"Time Value: Day       【  wlan.time_adv.time_value.month 】
1887:   {"Time Value: Hours       【  wlan.time_adv.time_value.hours 】
1888:   {"Time Value: Minutes       【  wlan.time_adv.time_value.minutes 】
1889:   {"Time Value: Seconds       【  wlan.time_adv.time_value.seconds 】
1890:   {"Time Value: Milliseconds       【  wlan.time_adv.time_value.milliseconds 】
1891:   {"Time Value: Reserved       【  wlan.time_adv.time_value.reserved 】
1892:   {"Time Error       【  wlan.time_adv.time_error 】
1893:   {"Time Update Counter       【  wlan.time_adv.time_update_counter 】
1894:   {"Time Zone       【  wlan.time_zone 】
1895:   {"Access Network Type       【  wlan.interworking.access_network_type 】
1896:   {"Internet       【  wlan.interworking.internet 】
1897:   {"ASRA       【  wlan.interworking.asra 】
1898:   {"ESR       【  wlan.interworking.esr 】
1899:   {"UESA       【  wlan.interworking.uesa 】
1900:   {"HESSID       【  wlan.interworking.hessid 】
1901:   {"DSCP Exception       【  wlan.qos_map_set.dscp_exception 】
1902:   {"DSCP Value       【  wlan.qos_map_set.dscp_value 】
1903:   {"User Priority       【  wlan.qos_map_set.up 】
1904:   {"DSCP Range description       【  wlan.qos_map_set.range 】
1905:   {"DSCP Low Value       【  wlan.qos_map_set.dscp_low_value 】
1906:   {"DSCP High Value       【  wlan.qos_map_set.dscp_high_value 】
1907:   {"Query Response Length Limit       【  wlan.adv_proto.resp_len_limit 】
1908:   {"PAME-BI       【  wlan.adv_proto.pame_bi 】
1909:   {"Advertisement Protocol ID       【  wlan.adv_proto.id 】
1910:   {"Advertisement Protocol Vendor Specific length       【  wlan.adv_proto.vs_len 】
1911:   {"Advertisement Protocol Vendor Specific info       【  wlan.adv_proto.vs_info 】
1912:   {"Number of ANQP OIs       【  wlan.roaming_consortium.num_anqp_oi 】
1913:   {"OI #1 Length       【  wlan.roaming_consortium.oi1_len 】
1914:   {"OI #2 Length       【  wlan.roaming_consortium.oi2_len 】
1915:   {"OI #1       【  wlan.roaming_consortium.oi1 】
1916:   {"OI #2       【  wlan.roaming_consortium.oi2 】
1917:   {"OI #3       【  wlan.roaming_consortium.oi3 】
1918:   {"Timeout Interval Type       【  wlan.timeout_int.type 】
1919:   {"Timeout Interval Value       【  wlan.timeout_int.value 】
1920:   {"BSSID       【  wlan.link_id.bssid 】
1921:   {"TDLS initiator STA Address       【  wlan.link_id.init_sta 】
1922:   {"TDLS responder STA Address       【  wlan.link_id.resp_sta 】
1923:   {"Offset       【  wlan.wakeup_schedule.offset 】
1924:   {"Interval       【  wlan.wakeup_schedule.interval 】
1925:   {"Awake Window Slots       【  wlan.wakeup_schedule.awake_window_slots 】
1926:   {"Maximum Awake Window Duration       【  wlan.wakeup_schedule.max_awake_dur 】
1927:   {"Idle Count       【  wlan.wakeup_schedule.idle_count 】
1928:   {"Switch Time       【  wlan.channel_switch_timing.switch_time 】
1929:   {"Switch Timeout       【  wlan.channel_switch_timing.switch_timeout 】
1930:   {"TID       【  wlan.pti_control.tid 】
1931:   {"Sequence Control       【  wlan.pti_control.sequence_control 】
1932:   {"AC_BK traffic available       【  wlan.pu_buffer_status.ac_bk 】
1933:   {"AC_BE traffic available       【  wlan.pu_buffer_status.ac_be 】
1934:   {"AC_VI traffic available       【  wlan.pu_buffer_status.ac_vi 】
1935:   {"AC_VO traffic available       【  wlan.pu_buffer_status.ac_vo 】
1936:   {"Mysterious OLPC stuff       【  wlan.mysterious_olpc_stuff 】
1937:   {"Estimated Service Parameters       【  wlan.ext_tag.estimated_service_params 】
1938:   {"Access Category       【  wlan.ext_tag.estimated_service_params.access_category 】
1939:   {"Reserved       【  wlan.ext_tag.estimated_service_params.reserved 】
1940:   {"Data Format       【  wlan.ext_tag.estimated_service_params.data_format 】
1941:   {"BA Window Size       【  wlan.ext_tag.estimated_service_params.ba_window_size 】
1942:   {"Estimated Air Time Fraction       【  wlan.ext_tag.estimated_service_params.air_time_frac 】
1943:   {"Data PPDU Duration Target       【  wlan.ext_tag.estimated_service_params.data_ppdu_dur_target 】
1944:   {"New Channel Number       【  wlan.ext_tag.future_channel_guidance.new_chan_num 】
1945:   {"Extra bytes       【  wlan.ext_tag.future_channel_guidance.extra_bytes 】
1946:   {"Number of Public Key Identifiers       【  wlan.fils_indication.info.nr_pk 】
1947:   {"Number of Realm Identifiers       【  wlan.fils_indication.info.nr_realm 】
1948:   {"FILS IP Address Configuration       【  wlan.fils_indication.info.ip_config 】
1949:   {"Cache Identifier       【  wlan.fils_indication.info.cache_id_included 】
1950:   {"HESSID       【  wlan.fils_indication.info.hessid_included 】
1951:   {"FILS Shared Key Authentication without PFS       【  wlan.fils_indication.info.ska_without_pfs 】
1952:   {"FILS Shared Key Authentication with PFS       【  wlan.fils_indication.info.ska_with_pfs 】
1953:   {"FILS Public Key Authentication       【  wlan.fils_indication.info.pka 】
1954:   {"Reserved       【  wlan.fils_indication.info.reserved 】
1955:   {"Cache Identifier       【  wlan.fils_indication.cache_identifier 】
1956:   {"HESSID       【  wlan.fils_indication.hessid 】
1957:   {"Realm Identifiers       【  wlan.fils_indication.realms 】
1958:   {"Realm Identifier       【  wlan.fils_indication.realms.identifier 】
1959:   {"Public Keys       【  wlan.fils_indication.public_keys 】
1960:   {"Key Type       【  wlan.fils_indication.public_keys.key_type 】
1961:   {"Length       【  wlan.fils_indication.public_keys.length 】
1962:   {"Public Key Indicator       【  wlan.fils_indication.public_keys.indicator 】
1963:   {"Ext Tag       【  wlan.ext_tag 】
1964:   {"Ext Tag Number       【  wlan.ext_tag.number 】
1965:   {"Ext Tag length       【  wlan.ext_tag.length 】
1966:   {"FILS Session       【  wlan.ext_tag.fils.session 】
1967:   {"FILS Encrypted Data       【  wlan.ext_tag.fils.encrypted_data 】
1968:   {"FILS Wrapped Data       【  wlan.ext_tag.fils.wrapped_data 】
1969:   {"FILS Nonce       【  wlan.ext_tag.fils.nonce 】
1970:   {"HE MAC Capabilities Information       【  wlan.ext_tag.he_mac_caps 】
1971:   {"+HTC HE Support       【  wlan.ext_tag.he_mac_cap.htc_he_support 】
1972:   {"TWT Requester Support       【  wlan.ext_tag.he_mac_cap.twt_req_support 】
1973:   {"TWT Responder Support       【  wlan.ext_tag.he_mac_cap.twt_rsp_support 】
1974:   {"Fragmentation Support       【  wlan.ext_tag.he_mac_cap.fragmentation_support 】
1975:   {"Maximum Number of Fragmented MSDUs       【  wlan.ext_tag.he_mac_cap.max_frag_msdus 】
1976:   {"Minimum Fragment Size       【  wlan.ext_tag.he_mac_cap.min_frag_size 】
1977:   {"Trigger Frame MAC Padding Duration       【  wlan.ext_tag.he_mac_cap.trig_frm_mac_padding_dur 】
1978:   {"Multi-TID Aggregation Support       【  wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】
1979:   {"HE Link Adaptation Support       【  wlan.ext_tag.he_mac_cap.he_link_adaptation_support 】
1980:   {"All Ack Support       【  wlan.ext_tag.he_mac_cap.all_ack_support 】
1981:   {"TRS Support       【  wlan.ext_tag.he_mac_cap.Trs_support 】
1982:   {"BSR Support       【  wlan.ext_tag.he_mac_cap.bsr_support 】
1983:   {"Broadcast TWT Support       【  wlan.ext_tag.he_mac_cap.broadcast_twt_support 】
1984:   {"32-bit BA Bitmap Support       【  wlan.ext_tag.he_mac_cap.32_bit_ba_bitmap_support 】
1985:   {"MU Cascading Support       【  wlan.ext_tag.he_mac_cap.mu_cascading_support 】
1986:   {"Ack-Enabled Aggregation Support       【  wlan.ext_tag.he_mac_cap.ack_enabled_agg_support 】
1987:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_b24 】
1988:   {"OM Control Support       【  wlan.ext_tag.he_mac_cap.om_control_support 】
1989:   {"OFDMA RA Support       【  wlan.ext_tag.he_mac_cap.ofdma_ra_support 】
1990:     {      【  wlan.ext_tag.he_mac_cap.max_a_mpdu_len_exp_ext 】
1991:   {"A-MSDU Fragmentation Support       【  wlan.ext_tag.he_mac_cap.a_msdu_frag_support 】
1992:   {"Flexible TWT Schedule Support       【  wlan.ext_tag.he_mac_cap.flexible_twt_sched_support 】
1993:   {"Rx Control Frame to MultiBSS       【  wlan.ext_tag.he_mac_cap.rx_ctl_frm_multibss 】
1994:   {"BSRP BQRP A-MPDU Aggregation       【  wlan.ext_tag.he_mac_cap.bsrp_bqrp_a_mpdu_agg 】
1995:   {"QTP Support       【  wlan.ext_tag.he_mac_cap.qtp_support 】
1996:   {"BQR Support       【  wlan.ext_tag.he_mac_cap.bqr_support 】
1997:   {"SRP Responder Role       【  wlan.ext_tag.he_mac_cap.sr_responder 】
1998:   {"NDP Feedback Report Support       【  wlan.ext_tag.he_mac_cap.ndp_feedback_report_support 】
1999:   {"OPS Support       【  wlan.ext_tag.he_mac_cap.ops_support 】
2000:   {"A-MSDU in A-MPDU Support       【  wlan.ext_tag.he_mac_cap.a_msdu_in_a_mpdu_support 】
2001:   {"Multi-TID Aggregation TX Support       【  wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】
2002:   {"HE Subchannel Selective Transmission Support       【  wlan.ext_tag.he_mac_cap.subchannel_selective_xmit_support 】
2003:   {"UL 2x996-tone RU Support       【  wlan.ext_tag.he_mac_cap.ul_2_996_tone_ru_support 】
2004:   {"OM Control UL MU Data Disable RX Support       【  wlan.ext_tag.he_mac_cap.om_cntl_ul_mu_data_disble_rx_support 】
2005:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bit_39 】
2006:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bits_5_7 】
2007:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bits_8_9 】
2008:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bits_15_16 】
2009:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bit_18 】
2010:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bit_19 】
2011:   {"Reserved       【  wlan.ext_tag.he_mac_cap.reserved_bit_25 】
2012:   {"Reserved       【  wlan.ext_tag.he_phy_cap.reserved_b0 】
2013:   {"Reserved       【  wlan.ext_tag.he_phy_cap.fbyte.reserved_b0 】
2014:   {"Channel Width Set       【  wlan.ext_tag.he_phy_cap.fbytes 】
2015:   {"40MHz in 2.4GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.40mhz_in_2_4ghz 】
2016:   {"40 & 80MHz in the 5GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.40_80_in_5ghz 】
2017:   {"160MHz in the 5GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.160_in_5ghz 】
2018:   {"160/80+80MHz in the 5GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.160_80_80_in_5ghz 】
2019:   {"242 tone RUs in the 2.4GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_2_4ghz 】
2020:   {"242 tone RUs in the 5GHz band       【  wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_5ghz 】
2021:   {"Reserved       【  wlan.ext_tag.he_phy_cap.chan_width_set.reserved 】
2022:   {"Bits 8 to 23       【  wlan.ext_tag.he_phy_cap.bits_8_to_23 】
2023:   {"Punctured Preamble RX       【  wlan.ext_tag.he_phy_cap.nbytes.punc_preamble_rx 】
2024:   {"Device Class       【  wlan.ext_tag.he_phy_cap.nbytes.device_class 】
2025:   {"LDPC Coding In Payload       【  wlan.ext_tag.he_phy_cap.nbytes.ldpc_coding_in_payload 】
2026:     {      【  wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_with_1x_he_ltf_08us 】
2027:   {"Midamble Rx Max NSTS       【  wlan.ext_tag.he_phy_cap.mbytes.midamble_rx_max_nsts 】
2028:     {      【  wlan.ext_tag.he_phy_cap.nbytes.ndp_with_4x_he_ltf_4x_3.2us 】
2029:   {"STBC Tx <= 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_lt_80mhz 】
2030:   {"STBC Rx <= 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_lt_80mhz 】
2031:   {"Doppler Tx       【  wlan.ext_tag.he_phy_cap.nbytes.doppler_tx 】
2032:   {"Doppler Rx       【  wlan.ext_tag.he_phy_cap.nbytes.doppler_rx 】
2033:   {"Full Bandwidth UL MU-MIMO       【  wlan.ext_tag.he_phy_cap.nbytes.full_bw_ul_mu_mimo 】
2034:   {"Partial Bandwidth UL MU-MIMO       【  wlan.ext_tag.he_phy_cap.nbytes.partial_bw_ul_mu_mimo 】
2035:   {"Bits 24 to 39       【  wlan.ext_tag.he_phy_cap.bits_24_to_39 】
2036:   {"DCM Max Constellation Tx       【  wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_tx 】
2037:   {"DCM Max NSS Tx       【  wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】
2038:   {"DCM Max Constellation Rx       【  wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_rx 】
2039:   {"DCM Max NSS Rx       【  wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】
2040:   {"Rx HE MU PPDU from Non-AP STA       【  wlan.ext_tag.he_phy_cap.nbytes.rx_he_mu_ppdu 】
2041:   {"SU Beamformer       【  wlan.ext_tag.he_phy_cap.nbytes.su_beamformer 】
2042:   {"SU Beamformee       【  wlan.ext_tag.he_phy_cap.nbytes.su_beamformee 】
2043:   {"MU Beamformer       【  wlan.ext_tag.he_phy_cap.nbytes.mu_beamformer 】
2044:   {"Beamformee STS <= 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_lte_80mhz 】
2045:   {"Beamformee STS > 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_gt_80mhz 】
2046:   {"Bits 40 to 55       【  wlan.ext_tag.he_phy_cap.bits_40_to_55 】
2047:   {"Number Of Sounding Dimensions <= 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_lte_80 】
2048:   {"Number Of Sounding Dimensions > 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_gt_80 】
2049:   {"Ng = 16 SU Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_su_fb 】
2050:   {"Ng = 16 MU Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_mu_fb 】
2051:   {"Codebook Size SU Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.codebook_size_su_fb 】
2052:   {"Codebook Size MU Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.codebook_size_mu_fb 】
2053:   {"Triggered SU Beamforming Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.trig_su_bf_fb 】
2054:   {"Triggered MU Beamforming Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.trig_mu_bf_fb 】
2055:   {"Triggered CQI Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.trig_cqi_fb 】
2056:   {"Partial Bandwidth Extended Range       【  wlan.ext_tag.he_phy_cap.nbytes.partial_bw_er 】
2057:   {"Partial Bandwidth DL MU-MIMO       【  wlan.ext_tag.he_phy_cap.nbytes.partial_bw_dl_mu_mimo 】
2058:   {"PPE Threshold Present       【  wlan.ext_tag.he_phy_cap.nbytes.ppe_thres_present 】
2059:   {"Bits 56 to 71       【  wlan.ext_tag.he_phy_cap.bits_56_to_71 】
2060:   {"SRP-based SR Support       【  wlan.ext_tag.he_phy_cap.nbytes.srp_based_sr_sup 】
2061:   {"Power Boost Factor ar Support       【  wlan.ext_tag.he_phy_cap.nbytes.pwr_bst_factor_ar_sup 】
2062:   {"HE SU PPDU & HE MU PPDU w 4x HE-LTF & 0.8us GI       【  wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_etc_gi 】
2063:   {"Max Nc       【  wlan.ext_tag.he_phy_cap.nbytes.max_nc 】
2064:   {"STBC Tx > 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_gt_80_mhz 】
2065:   {"STBC Rx > 80 MHz       【  wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_gt_80_mhz 】
2066:   {"HE ER SU PPDU W 4x HE-LTF & 0.8us GI       【  wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_4xxx_gi 】
2067:   {"20 MHz In 40 MHz HE PPDU In 2.4GHz Band       【  wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_40_in_2_4ghz 】
2068:   {"20 MHz In 160/80+80 MHz HE PPDU       【  wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_160_80p80_ppdu 】
2069:   {"80 MHz In 160/80+80 MHz HE PPDU       【  wlan.ext_tag.he_phy_cap.nbytes.80_mhz_in_160_80p80_ppdu 】
2070:   {"HE ER SU PPDU W 1x HE-LTF & 0.8us GI       【  wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_1xxx_gi 】
2071:   {"Midamble Rx 2x & 1x HE-LTF       【  wlan.ext_tag.he_phy_cap.nbytes.midamble_rx_2x_1x_he_ltf 】
2072:   {"Bits 72 to 87       【  wlan.ext_tag.he_phy_cap.bits_72_to_87 】
2073:   {"DCM Max BW       【  wlan.ext_tag.he_phy_cap.nbytes.dcm_max_bw 】
2074:   {"Longer Than 16 HE SIG-B OFDM Symbols Support       【  wlan.ext_tag.he_phy_cap.nbyes.longer_than_16_he_sigb_ofdm_sym_support 】
2075:   {"Non-Triggered CQI Feedback       【  wlan.ext_tag.he_phy_cap.nbytes.non_triggered_feedback 】
2076:   {"Tx 1024-QAM Support < 242-tone RU       【  wlan.ext_tag.he_phy_cap.nbytes.tx_1024_qam_support_lt_242_tone_ru 】
2077:   {"Rx 1024-QAM Support < 242-tone RU       【  wlan.ext_tag.he_phy_cap.nbytes.rx_1024_qam_support_lt_242_tone_ru 】
2078:   {"Rx Full BW SU Using HE MU PPDU With Compressed SIGB       【  wlan.ext_tag.he_phy_cap.nbytes.rx_full_bw_su_using_he_mu_ppdu_with_compressed_sigb 】
2079:   {"Rx Full BW SU Using HE MU PPDU With Non-Compressed SIGB       【  wlan.ext_tag.he_phy_cap.nbytes.rx_full_bw_su_using_he_mu_ppdu_with_non_compressed_sigb 】
2080:   {"Reserved       【  wlan.ext_tag.he_phy_cap.nbytes.reserved_b78_b87 】
2081:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss 】
2082:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_2_ss 】
2083:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_3_ss 】
2084:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_4_ss 】
2085:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_5_ss 】
2086:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_6_ss 】
2087:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_7_ss 】
2088:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_8_ss 】
2089:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_1_ss 】
2090:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_2_ss 】
2091:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_3_ss 】
2092:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_4_ss 】
2093:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_5_ss 】
2094:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_6_ss 】
2095:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_7_ss 】
2096:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_8_ss 】
2097:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_1_ss 】
2098:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_2_ss 】
2099:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_3_ss 】
2100:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_4_ss 】
2101:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_5_ss 】
2102:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_6_ss 】
2103:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_7_ss 】
2104:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_8_ss 】
2105:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_1_ss 】
2106:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_2_ss 】
2107:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_3_ss 】
2108:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_4_ss 】
2109:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_5_ss 】
2110:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_6_ss 】
2111:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_7_ss 】
2112:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_8_ss 】
2113:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_1_ss 】
2114:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_2_ss 】
2115:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_3_ss 】
2116:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_4_ss 】
2117:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_5_ss 】
2118:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_6_ss 】
2119:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_7_ss 】
2120:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_8_ss 】
2121:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_1_ss 】
2122:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_2_ss 】
2123:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_3_ss 】
2124:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_4_ss 】
2125:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_5_ss 】
2126:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_6_ss 】
2127:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_7_ss 】
2128:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_8_ss 】
2129:   {"Rx HEX-MCS Map <= 80 MHz       【  wlan.ext_tag.he_mcs_map.rx_he_mcs_map_lte_80 】
2130:   {"Tx HEX-MCS Map <= 80 MHz       【  wlan.ext_tag.he_mcs_map.tx_he_mcs_map_lte_80 】
2131:   {"Rx HEX-MCS Map 160 MHz       【  wlan.ext_tag.he_mcs_map.rx_he_mcs_map_160 】
2132:   {"Tx HEX-MCS Map 160 MHz       【  wlan.ext_tag.he_mcs_map.tx_he_mcs_map_160 】
2133:   {"Rx HEX-MCS Map 80+80 MHz       【  wlan.ext_tag.he_mcs_map.rx_he_mcs_map_80_80 】
2134:   {"Tx HEX-MCS Map 80+80 MHz       【  wlan.ext_tag.he_mcs_map.tx_he_mcs_map_80_80 】
2135:   {"NSS       【  wlan.ext_tag.he_ppe_thresholds.nss 】
2136:   {"RU Index Bitmask       【  wlan.ext_tag.he_ppe_thresholds.ru_index_bitmask 】
2137:   {"PPET16       【  wlan.ext_tag.he_ppe_thresholds.ppet16 】
2138:   {"PPET8       【  wlan.ext_tag.he_ppe_thresholds.ppet8 】
2139:   {"HE Operation Parameters       【  wlan.ext_tag.he_operation.params 】
2140:   {"Default PE Duration       【  wlan.ext_tag.he_operation.default_pe_duration 】
2141:   {"TWT Required       【  wlan.ext_tag.he_operation.twt_required 】
2142:   {"TXOP Duration RTS Threshold       【  wlan.ext_tag.he_operation.txop_duration_rts_thresh 】
2143:   {"VHT Operation Information Present       【  wlan.ext_tag.he_operation.vht_op_info_present 】
2144:   {"Co-Located BSS       【  wlan.ext_tag.he_operation.co_located_bss 】
2145:   {"ER SU Disable       【  wlan.ext_tag.he_operation.er_su_disable 】
2146:   {"Reserved       【  wlan.ext_tag.he_operation.reserved_b17_b32 】
2147:   {"BSS Color Information       【  wlan.ext_tag.bss_color_information 】
2148:   {"BSS Color       【  wlan.ext_tag.bss_color_information.bss_color 】
2149:   {"Partial BSS Color       【  wlan.ext_tag.bss_color_information.partial_bss_color 】
2150:   {"BSS Color Disabled       【  wlan.ext_tag.bss_color_information.bss_color_disabled 】
2151:   {"Basic HE-MCS and NSS Set       【  wlan.ext_tag.he_operation.basic_he_mcs_and_nss 】
2152:   {"Max HE-MCS for 1 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_1_ss 】
2153:   {"Max HE-MCS for 2 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_2_ss 】
2154:   {"Max HE-MCS for 3 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_3_ss 】
2155:   {"Max HE-MCS for 4 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_4_ss 】
2156:   {"Max HE-MCS for 5 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_5_ss 】
2157:   {"Max HE-MCS for 6 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_6_ss 】
2158:   {"Max HE-MCS for 7 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_7_ss 】
2159:   {"Max HE-MCS for 8 SS       【  wlan.ext_tag.he_operation.max_he_mcs_for_8_ss 】
2160:   {"channel Width       【  wlan.ext_tag.he_operation.vht_op_info.channel_width 】
2161:   {"Channel Center Frequency Segment 0       【  wlan.ext_tag.he_operatoon.vht_op_info.chan_center_freq_seg_0 】
2162:   {"Channel Center Frequency Segment 1       【  wlan.ext_tag.he_operatoon.vht_op_info.chan_center_freq_seg_1 】
2163:   {"Max Co-Located BSSID Indicator       【  wlan.ext_tag.he_operation.max_colocated_bssid_indicator 】
2164:   {"AIC/AIFSN       【  wlan.ext_tag.mu_edca_parameter_set.aic_aifsn 】
2165:   {"AIFSN       【  wlan.ext_tag.mu_edca_parameter_set.aifsn 】
2166:   {"Admission Control Mandatory       【  wlan.ext_tag.mu_edca_parameter_set.acm 】
2167:   {"ACI       【  wlan.ext_tag.mu_edca_parameter_set.aci 】
2168:   {"Reserved       【  wlan.ext_tag.mu_edca_parameter_set.reserved 】
2169:   {"MU EDCA Timer       【  wlan.ext_tag.mu_edca_parameter_set.mu_edca_timer 】
2170:   {"ECWmin/ECWmax       【  wlan.ext_tag.mu_edca_parameter_set.ecwmin_ecwmax 】
2171:   {"SR Control       【  wlan.ext_tag.spatial_reuse.sr_control 】
2172:   {"SRP Disallowed       【  wlan.ext_tag.spatial_reuse.sr_control.srp_dis 】
2173:   {"NON-SRG OBSS PD SR Disallowed       【  wlan.ext_tag.spatial_reuse.sr_control.non_srg_obss_pd_sr_dis 】
2174:   {"Non-SRG Offset Present       【  wlan.ext_tag.spatial_reuse.sr_control.non_srg_ofs_present 】
2175:   {"SRG Information Present       【  wlan.ext_tag.spatial_reuse.sr_control.srg_info_present 】
2176:   {"HESIGA Spatial Reuse value 15 allowed       【  wlan.ext_tag.spatial_reuse.sr_control.hesiga_val_15_allowed 】
2177:   {"Reserved       【  wlan.ext_tag.spatial_reuse.sr_control.reserved 】
2178:   {"Non-SRG OBSS PD Max Offset       【  wlan.ext_tag.spatial_reuse.non_srg_obss_pd_max_offset 】
2179:   {"SRG OBSS PD Min Offset       【  wlan.ext_tag.spatial_reuse.srg_obss_pd_min_offset 】
2180:   {"SRG OBSS PD Max Offset       【  wlan.ext_tag.spatial_reuse.srg_obss_pd_max_offset 】
2181:   {"SRG BSS Color Bitmap       【  wlan.ext_tag.spatial_reuse.srg_bss_color_bitmap 】
2182:   {"SRG Partial BSSID Bitmap       【  wlan.ext_tag.spatial_reuse.srg_partial_bssid_bitmap 】
2183:   {"Resource Request Buffer Threshold Exponent       【  wlan.ext_tag.ndp_feedback.res_req_buf_thresh_exp 】
2184:   {"New BSS Color Info       【  wlan.ext_tag.bss_color_change.new_color_info 】
2185:   {"New BSS Color       【  wlan.ext_tag.bss_color_change.new_bss_color 】
2186:   {"Reserved       【  wlan.ext_tag.bss_color_change.new_color_reserved 】
2187:   {"BSS Color Switch Countdown       【  wlan.ext_tag.bss_color_change.color_switch_countdown 】
2188:   {"Planned ESS       【  wlan.ext_tag.ess_report.ess_info.planned_ess 】
2189:   {"Edge of ESS       【  wlan.ext_tag.ess_report.ess_info.edge_of_ess 】
2190:   {"ESS Information field       【  wlan.ext_tag.ess_report.ess_info.field 】
2191:   {"Recommended BSS Transition Threshold       【  wlan.ext_tag.ess_report.ess_info.thresh 】
2192:   {"UL OFDMA-based Random Access Parameter SET       【  wlan.ext_tag.uora_parameter_set.field 】
2193:   {"EOCWmin       【  wlan.ext_tag.uora_parameter_set.eocwmin 】
2194:   {"EOCWmax       【  wlan.ext_tag.uora_parameter_set.eocwmax 】
2195:   {"Reserved       【  wlan.ext_tag.uora_parameter_set.reserved 】
2196:   {"S1G Action       【  wlan.s1g.action 】
2197:   {"TWT Flow       【  wlan.twt.bcast_flow 】
2198:   {"TWT Flow       【  wlan.twt.individual_flow 】
2199:   {"Individual TWT Flow Id       【  wlan.twt.individual_flow_id 】
2200:   {"Broadcast TWT Id       【  wlan.twt.bcast_flow_id 】
2201:   {"TWT Negotiation type       【  wlan.twt.neg_type 】
2202:   {"Control Field       【  wlan.twt.control_field 】
2203:   {"NDP Paging Indicator       【  wlan.twt.ndp_paging_indicator 】
2204:   {"Responder PM Mode       【  wlan.twt.resp_pm 】
2205:   {"Negotiation type       【  wlan.twt.neg_type 】
2206:   {"Reserved       【  wlan.twt.control_field_reserved 】
2207:   {"Request Type       【  wlan.twt.request_type 】
2208:   {"Requester       【  wlan.twt.requester 】
2209:   {"Setup Command       【  wlan.twt.setup_cmd 】
2210:   {"Trigger       【  wlan.twt.trigger 】
2211:   {"Implicit       【  wlan.twt.implicit 】
2212:   {"Flow type       【  wlan.twt.flow_type 】
2213:   {"Flow ID       【  wlan.twt.flow_id 】
2214:   {"Wake Interval Exponent       【  wlan.twt.wake_interval_exp 】
2215:   {"Protection       【  wlan.twt.prot 】
2216:   {"Target Wake Time       【  wlan.twt.target_wake_time 】
2217:   {"Nominal Minimum TWT Wake duration       【  wlan.twt.nom_min_twt_wake_duration 】
2218:   {"TWT Wake Interval Mantissa       【  wlan.twt.wake_interval_mantissa 】
2219:   {"TWT Channel       【  wlan.twt.channel 】
2220:   {"Group       【  wlan.ext_tag.owe_dh_parameter.group 】
2221:   {"Public Key       【  wlan.ext_tag.owe_dh_parameter.public_key 】
2222:   {"A-MSDU Subframe       【  wlan_aggregate.a_mdsu.subframe 】
2223:   {"A-MSDU Length       【  wlan_aggregate.a_mdsu.length 】
2224:   {      【  wlan.tag.number.unexpected_ie 】  
2225:   {      【  wlan.tag.length.bad 】  
2226:   {      【  wlan.fixed.anqp.capability.invalid 】  
2227:   {      【  wlan.fixed.anqp.venue.length.invalid 】  
2228:   {      【  wlan.fixed.anqp.roaming_consortium.oi_len.invalid 】  
2229:   {      【  wlan.fixed.anqp.nai_realm_list.field_len.invalid 】  
2230:   {      【  wlan.fixed.naqp_nai_realm_list.eap_method_len.invalid 】  
2231:   {      【  wlan.hs20.anqp.ofn.length.invalid 】  
2232:   {      【  wlan.hs20.anqp.nai_hrq.length.invalid 】  
2233:   {      【  wlan.fixed.anqp.info_length.invalid 】  
2234:   {      【  wlan.fixed.query_length_invalid 】  
2235:   {      【  wlan.fixed.query_request_length.invalid 】  
2236:   {      【  wlan.fixed.query_response_length.invalid 】  
2237:   {      【  wlan.wnm_sleep_mode.no_key_data 】  
2238:   {      【  wlan.tdls_setup_response_malformed 】  
2239:   {      【  wlan.tdls_setup_confirm_malformed 】  
2240:   {      【  wlan.wfa.ie.wme.qos_info.bad_ftype 】    
2241:   {      【  wlan.qos_info.bad_ftype 】    
2242:   {      【  wlan.qos_info.bad_aifsn 】  
2243:   {      【  wlan.rsn.pcs.count.invalid 】  
2244:   {      【  wlan.rsn.akms.count.invalid 】  
2245:   {      【  wlan.rsn.pmkid.count.invalid 】  
2246:   {      【  wlan.vht.tpe.pwr_info.count.invalid 】  
2247:   {      【  wlan.fc.retry.expert 】 PI_SEQUENCE,  
2248:   {      【  wlan.measure.req.unknown.expert 】    
2249:   {      【  wlan.measure.req.beacon.unknown.expert 】    
2250:   {      【  wlan.measure.req.unknown.expert 】    
2251:   {      【  wlan.tag.data.undecoded 】    
2252:   {      【  wlan.dmg_subtype.bad 】  
2253:   {      【  wlan.vht.action.undecoded 】    
2254:   {      【  wlan.peering.unexpected 】  
2255:   {      【  wlan.fcs.bad_checksum 】  
2256:   {      【  wlan.rsn.akms.mismatched 】    
2257:   {      【  wlan.vs.routerboard.unexpected_len 】    
2258:   {      【  wlan.twt.tear_down_bad_neg_type 】    
2259:   {      【  wlan.twt.setup_unsup_neg_type 】    
2260:   {      【  wlan.twt.setup_bad_command 】    




```



###  补充
```

1: 【 wlan.fc 】 
2: 【 wlan.fc.version 】 
3: 【 wlan.fc.type 】 
4: 【 wlan.fc.subtype 】 
5: 【 wlan.fc.type_subtype 】 
6: 【 wlan.fc.extension 】 
7: 【 wlan.flags 】 
8: 【 wlan.fc.ds 】 
9: 【 wlan.fc.tods 】 
10: 【 wlan.fc.fromds 】 
11: 【 wlan.fc.frag 】 
12: 【 wlan.fc.retry 】 
13: 【 wlan.analysis.retransmission 】 
14: 【 wlan.analysis.retransmission_frame 】 
15: 【 wlan.fc.pwrmgt 】 
16: 【 wlan.fc.moredata 】 
17: 【 wlan.fc.protected 】 
18: 【 wlan.fc.order 】 
19: 【 wlan.aid 】 
20: 【 wlan.duration 】 
21: 【 wlan.da 】 
22: 【 wlan.da_resolved 】  
23: 【 wlan.sa 】 
24: 【 wlan.sa_resolved 】  
25: 【 wlan.addr 】 
26: 【 wlan.addr_resolved 】 
27: 【 wlan.ra 】 
28: 【 wlan.ra_resolved 】   
29: 【 wlan.ta 】 
30: 【 wlan.ta_resolved 】 
31: 【 wlan.bssid 】 
32: 【 wlan.bssid_resolved 】   
33: 【 wlan.staa 】 
34: 【 wlan.staa_resolved 】   
35: 【 wlan.frag 】 
36: 【 wlan.seq 】 
37: 【 wlan.mesh.control_field 】 
38: 【 wlan.qos 】 
39: 【 wlan.qos.tid 】 
40: 【 wlan.qos.priority 】 
41: 【 wlan.qos.eosp 】 
42: 【 wlan.qos.bit4 】 
43: 【 wlan.qos.ack 】 
44: 【 wlan.qos.amsdupresent 】 
45: 【 wlan.qos.txop_limit 】 
46: 【 wlan.qos.ps_buf_state 】 
47: 【 wlan.qos.buf_state_indicated 】 
48: 【 wlan.qos.highest_pri_buf_ac 】 
49: 【 wlan.qos.qap_buf_load 】 
50: 【 wlan.qos.txop_dur_req 】 
51: 【 wlan.qos.queue_size 】 
52: 【 wlan.fcs 】 
53: 【 wlan.fcs.status 】 
54: 【 wlan.fragment.overlap 】 
55: 【 wlan.fragment.overlap.conflict 】 
56: 【 wlan.fragment.multipletails 】 
57: 【 wlan.fragment.toolongfragment 】 
58: 【 wlan.fragment.error 】 
59: 【 wlan.fragment.count 】 
60: 【 wlan.fragment 】 
61: 【 wlan.fragments 】 
62: 【 wlan.reassembled_in 】 
63: 【 wlan.reassembled.length 】 
64: 【 wlan.wep.iv 】 
65: 【 wlan.wep.weakiv 】 
66: 【 wlan.tkip.extiv 】 
67: 【 wlan.ccmp.extiv 】 
68: 【 wlan.wep.key 】 
69: 【 wlan.wep.icv 】 
70: 【 wlan.analysis.pmk 】 
71: 【 wlan.analysis.kck 】 
72: 【 wlan.analysis.kek 】 
73: 【 wlan.analysis.tk 】 
74: 【 wlan.analysis.gtk 】 
75: 【 wlan.ba.control 】 
76: 【 wlan.ba.control.ackpolicy 】 
77: 【 wlan.ba.control.ba_type 】 
78: 【 wlan.ba.control.reserved 】 
79: 【 wlan.ba.basic.tidinfo 】 
80: 【 wlan.ba.multi_sta.aid11 】 
81: 【 wlan.ba.multi_sta.ack_type 】 
82: 【 wlan.ba.multi_sta.tid 】 
83: 【 wlan.ba.multi_sta.aid_tid_info 】 
84: 【 wlan.ba.multi_sta.reserved 】 
85: 【 wlan.ba.multi_sta.ra 】 
86: 【 wlan.bar.mtid.tidinfo.reserved 】 
87: 【 wlan.bar.mtid.tidinfo.value 】 
88: 【 wlan.ba.bm 】 
89: 【 wlan.ba.RBUFCAP 】 
90: 【 wlan.ba.bm.missing_frame 】 
91: 【 wlan.ba.gcr_group_addr 】 
92: 【 wlan.beamform.feedback_seg_retrans_bitmap 】 
93: 【 wlan.vht_ndp.token 】 
94: 【 wlan.vht_ndp.token.number 】 
95: 【 wlan.vht_ndp.token.he 】 
96: 【 wlan.vht_ndp.token.reserved 】 
97: 【 wlan.vht_ndp.sta_info.aid12 】 
98: 【 wlan.vht_ndp.sta_info.feedback_type 】 
99: 【 wlan.vht_ndp.sta_info.nc_index 】 
100: 【 wlan.vht_ndp.sta_info.reserved 】 
101: 【 wlan.data_encap.payload_type 】 
102: 【 wlan.fixed.action_code 】 
103: 【 wlan.fixed.target_channel 】 
104: 【 wlan.fixed.operating_class 】 
105: 【 wlan.fixed.action_code 】 
106: 【 wlan.fixed.action_code 】 
107: 【 wlan.fixed.key_data 】 
108: 【 wlan.fixed.key_data_length 】 
109: 【 wlan.fixed.wnm_notification_type 】 
110: 【 wlan.rm.action_code 】 
111: 【 wlan.rm.dialog_token 】 
112: 【 wlan.rm.repetitions 】 
113: 【 wlan.rm.tx_power 】 
114: 【 wlan.rm.max_tx_power 】 
115: 【 wlan.rm.tpc 】 
116: 【 wlan.rm.tpc.element_id 】 
117: 【 wlan.rm.tpc.length 】 
118: 【 wlan.rm.tpc.tx_power 】 
119: 【 wlan.rm.tpc.link_margin 】 
120: 【 wlan.rm.rx_antenna_id 】 
121: 【 wlan.rm.tx_antenna_id 】 
122: 【 wlan.rm.rcpi 】 
123: 【 wlan.rm.rsni 】 
124: 【 wlan.fixed.request_mode.pref_cand 】 
125: 【 wlan.fixed.request_mode.abridged 】 
126: 【 wlan.fixed.request_mode.disassoc_imminent 】 
127: 【 wlan.fixed.request_mode.bss_term_included 】 
128: 【 wlan.fixed.request_mode.ess_disassoc_imminent 】 
129: 【 wlan.fixed.disassoc_timer 】 
130: 【 wlan.fixed.bss_termination_delay 】 
131: 【 wlan.fixed.bss_transition_status_code 】 
132: 【 wlan.fixed.validity_interval 】 
133: 【 wlan.fixed.bss_termination_duration 】 
135: 【 wlan.fixed.session_information.url 】 
136: 【 wlan.fixed.bss_transition_target_bss 】 
137: 【 wlan.fixed.bss_transition_query_reason 】 
138: 【 wlan.fixed.bss_transition_candidate_list_entries 】 
139: 【 wlan.res_offset 】 
140: 【 wlan.grant_ack.reserved 】 
141: 【 wlan.dynamic_allocation 】 
142: 【 wlan.dynamic_allocation.tid 】 
143: 【 wlan.dynamic_allocation.alloc_type 】 
144: 【 wlan.dynamic_allocation.src_aid 】 
145: 【 wlan.dynamic_allocation.dest_aid 】 
146: 【 wlan.dynamic_allocation.alloc_duration 】 
147: 【 wlan.dynamic_allocation.b39 】 
148: 【 wlan.ssw 】 
149: 【 wlan.ssw.direction 】 
150: 【 wlan.ssw.cdown 】 
151: 【 wlan.ssw.sector_id 】 
152: 【 wlan.ssw.dmg_ant_id 】 
153: 【 wlan.ssw.rxss_len 】 
154: 【 wlan.bf 】 
155: 【 wlan.bf.train 】 
156: 【 wlan.bf.isInit 】 
157: 【 wlan.bf.isResp 】 
158: 【 wlan.bf.rxss_len 】 
159: 【 wlan.bf.rxss_rate 】 
160: 【 wlan.bf.reserved 】 
161: 【 wlan.bf.num_sectors 】 
162: 【 wlan.bf.num_dmg_ants 】 
163: 【 wlan.bf.reserved 】 
164: 【 wlan.nav_da 】 
165: 【 wlan.nav_sa 】 
166: 【 wlan.sswf 】 
167: 【 wlan.sswf.num_sectors 】 
168: 【 wlan.sswf.num_dmg_ants 】 
169: 【 wlan.sswf.poll 】 
170: 【 wlan.sswf.reserved 】 
171: 【 wlan.sswf.reserved 】 
172: 【 wlan.sswf.sector_select 】 
173: 【 wlan.sswf.dmg_antenna_select 】 
174: 【 wlan.sswf.snr_report 】 
175: 【 wlan.brp 】 
176: 【 wlan.brp.l_rx 】 
177: 【 wlan.brp.tx_trn_req 】 
178: 【 wlan.brp.mid_req 】 
179: 【 wlan.brp.bc_req 】 
180: 【 wlan.brp.mid_grant 】 
181: 【 wlan.brp.bc_grant 】 
182: 【 wlan.brp.chan_fbck_cap 】 
183: 【 wlan.brp.tx_sector_id 】 
184: 【 wlan.brp.other_aid 】 
185: 【 wlan.brp.tx_antenna_id 】 
186: 【 wlan.brp.reserved 】 
187: 【 wlan.blm 】 
188: 【 wlan.blm.uint_index 】 
189: 【 wlan.blm.value 】 
190: 【 wlan.blm.is_master 】 
191: 【 wlan.bic 】 
192: 【 wlan.bic.cc 】 
193: 【 wlan.bic.discovery_mode 】 
194: 【 wlan.bic.next_beacon 】 
195: 【 wlan.bic.ati 】 
196: 【 wlan.bic.abft_len 】 
197: 【 wlan.bic.fss 】 
198: 【 wlan.bic.is_responder 】 
199: 【 wlan.bic.next_abft 】 
200: 【 wlan.bic.frag_txss 】 
201: 【 wlan.bic.txss_span 】 
202: 【 wlan.bic.NBI_abft 】 
203: 【 wlan.bic.abft_count 】 
204: 【 wlan.bic.nabft 】 
205: 【 wlan.bic.pcp 】 
206: 【 wlan.bic.reserved 】 
207: 【 wlan.dmg_params 】 
208: 【 wlan.dmg_params.bss 】 
209: 【 wlan.dmg_params.cbap_only 】 
210: 【 wlan.dmg_params.cbap_src 】 
211: 【 wlan.dmg_params.privacy 】 
212: 【 wlan.dmg_params.policy 】 
213: 【 wlan.cc 】 
214: 【 wlan.cc.abft_resp_addr 】 
215: 【 wlan.cc.sp_duration 】 
216: 【 wlan.cc.cluster_id 】 
217: 【 wlan.cc.rold 】 
218: 【 wlan.cc.max_mem 】 
219: 【 wlan.relay_capabilities.relay_support 】 
220: 【 wlan.relay_capabilities.relay_use 】 
221: 【 wlan.relay_capabilities.relay_permission 】 
222: 【 wlan.relay_capabilities.AC_power 】 
223: 【 wlan.relay_capabilities.relay_prefer 】 
224: 【 wlan.relay_capabilities.duplex 】 
225: 【 wlan.relay_capabilities.cooperation 】 
226: 【 wlan.rcsi 】 
227: 【 wlan.rcsi.aid 】 
228: 【 wlan.band_id 】 
229: 【 wlan.dmg_bss_param_change.move 】 
230: 【 wlan.dmg_bss_param_change.size 】 
231: 【 wlan.dmg_bss_param_change.tbtt_offset 】 
232: 【 wlan.dmg_bss_param_change.bi_duration 】 
233: 【 wlan.dmg_capa.sta_addr 】 
234: 【 wlan.dmg_capa.aid 】 
235: 【 wlan.dmg_capa.reverse_direction 】 
236: 【 wlan.dmg_capa.htls 】 
237: 【 wlan.dmg_capa.tpc 】 
238: 【 wlan.dmg_capa.spsh 】 
239: 【 wlan.dmg_capa.num_rx 】 
240: 【 wlan.dmg_capa.fast_link 】 
241: 【 wlan.dmg_capa.num_sectors 】 
242: 【 wlan.dmg_capa.rxss_len 】 
243: 【 wlan.dmg_capa.reciprocity 】 
244: 【 wlan.dmg_capa.max_ampdu_exp 】 
245: 【 wlan.dmg_capa.min_mpdu_spacing 】 
246: 【 wlan.dmg_capa.bs_flow_ctrl 】 
247: 【 wlan.dmg_capa.max_sc_rx_mcs 】 
248: 【 wlan.dmg_capa.max_ofdm_rx_mcs 】 
249: 【 wlan.dmg_capa.max_sc_tx_mcs 】 
250: 【 wlan.dmg_capa.max_ofdm_tx_mcs 】 
251: 【 wlan.dmg_capa.low_power_suuported 】 
252: 【 wlan.dmg_capa.code_rate 】 
253: 【 wlan.dmg_capa.dtp 】 
254: 【 wlan.dmg_capa.appdu_supp 】 
255: 【 wlan.dmg_capa.heartbeat 】 
256: 【 wlan.dmg_capa.other_aid 】 
257: 【 wlan.dmg_capa.pattern_recip 】 
258: 【 wlan.dmg_capa.heartbeat_elapsed 】 
259: 【 wlan.dmg_capa.grant_ack_supp 】 
260: 【 wlan.dmg_capa.RXSSTxRate 】 
261: 【 wlan.dmg_capa.pcp_tdtti 】 
262: 【 wlan.dmg_capa.pcp_psa 】 
263: 【 wlan.dmg_capa.pcp_handover 】 
264: 【 wlan.dmg_capa.pcp_max_assoc 】 
265: 【 wlan.dmg_capa.pcp_power_src 】 
266: 【 wlan.dmg_capa.pcp_decenter 】 
267: 【 wlan.dmg_capa.pcp_forwarding 】 
268: 【 wlan.dmg_capa.pcp_center 】 
269: 【 wlan.dmg_capa.beam_track 】 
270: 【 wlan.dmg_capa.ext_sc_mcs_capa_max_tx 】 
271: 【 wlan.dmg_capa.ext_sc_mcs_tx_code_7_8 】 
272: 【 wlan.dmg_capa.ext_sc_mcs_capa_max_rx 】 
273: 【 wlan.dmg_capa.ext_sc_mcs_rx_code_7_8 】 
274: 【 wlan.dmg_capa.max_basic_sf_amsdu 】 
275: 【 wlan.dmg_capa.max_short_sf_amsdu 】 
276: 【 wlan.dmg_oper.psrsi 】 
277: 【 wlan.dmg_oper.min_BHI_duration 】 
278: 【 wlan.dmg_oper.brdcst_sta_info_dur 】 
279: 【 wlan.dmg_oper.assoc_resp_confirm_time 】 
280: 【 wlan.dmg_oper.min_pp_duration 】 
281: 【 wlan.dmg_oper.SP_idle_timeout 】 
282: 【 wlan.dmg_oper.max_lost_beacons 】 
283: 【 wlan.sctor_id.type 】 
284: 【 wlan.sctor_id.tap1 】 
285: 【 wlan.sctor_id.state1 】 
286: 【 wlan.sctor_id.tap2 】 
287: 【 wlan.sctor_id.state2 】 
288: 【 wlan.ext_sched.alloc_id 】 
289: 【 wlan.ext_sched.alloc_type 】 
290: 【 wlan.ext_sched.p_static 】 
291: 【 wlan.ext_sched.truncatable 】 
292: 【 wlan.ext_sched.extendable 】 
293: 【 wlan.ext_sched.pcp_active 】 
294: 【 wlan.ext_sched.lp_sc_used 】 
295: 【 wlan.ext_sched.src_id 】 
296: 【 wlan.ext_sched.dest_id 】 
297: 【 wlan.ext_sched.alloc_start 】 
298: 【 wlan.ext_sched.block_duration 】 
299: 【 wlan.ext_sched.num_blocks 】 
300: 【 wlan.ext_sched.alloc_block_period 】 
301: 【 wlan.sta_avail.aid 】 
302: 【 wlan.sta_avail.cbap 】 
303: 【 wlan.sta_avail.pp_avail 】 
304: 【 wlan.next_ati.start_time 】 
305: 【 wlan.next_ati.duration 】 
306: 【 wlan.pcp_handover.old_bssid 】 
307: 【 wlan.pcp_handover.new_pcp_addr 】 
308: 【 wlan.quiet_res.bssid 】 
309: 【 wlan.relay_capabilities.duplex 】 
310: 【 wlan.relay_capabilities.cooperation 】 
311: 【 wlan.realy_trans_param.tx_mode 】 
312: 【 wlan.realy_trans_param.link_change_interval 】 
313: 【 wlan.realy_trans_param.data_sensing_time 】 
314: 【 wlan.realy_trans_param.first_period 】 
315: 【 wlan.realy_trans_param.second_period 】 
316: 【 wlan.beam_refine.initiator 】 
317: 【 wlan.beam_refine.tx_train_res 】 
318: 【 wlan.beam_refine.rx_train_res 】 
319: 【 wlan.beam_refine.tx_trn_ok 】 
320: 【 wlan.beam_refine.txss_fbck_req 】 
321: 【 wlan.beam_refine.bs_fbck 】 
322: 【 wlan.beam_refine.bs_fbck_antenna_id 】 
323: 【 wlan.beam_refine.snr_req 】 
324: 【 wlan.beam_refine.ch_measure_req 】 
325: 【 wlan.beam_refine.taps_req 】 
326: 【 wlan.beam_refine.sector_id_req 】 
327: 【 wlan.beam_refine.snr_present 】 
328: 【 wlan.beam_refine.ch_measure_present 】 
329: 【 wlan.beam_refine.tap_delay_present 】 
330: 【 wlan.beam_refine.taps_present 】 
331: 【 wlan.beam_refine.num_measurement 】 
332: 【 wlan.beam_refine.sector_id_present 】 
333: 【 wlan.beam_refine.num_beams 】 
334: 【 wlan.beam_refine.mid_ext 】 
335: 【 wlan.beam_refine.cap_req 】 
336: 【 wlan.beam_refine.reserved 】 
337: 【 wlan.next_pcp.list 】 
338: 【 wlan.next_pcp.token 】 
339: 【 wlan.pcp_handover.remaining_BIs 】 
340: 【 wlan.request_token 】 
341: 【 wlan.bi_start_time 】 
342: 【 wlan.sleep_cycle 】 
343: 【 wlan.num_awake_bis 】 
344: 【 wlan.fixed.dmg_act 】 
345: 【 wlan.fixed.unprotected_dmg_act 】 
346: 【 wlan.dmg.pwr_mgmt 】 
347: 【 wlan.dmg.subject_addr 】 
348: 【 wlan.dmg.handover_reason 】 
349: 【 wlan.dmg.handover_remaining_bi 】 
350: 【 wlan.dmg.handover_result 】 
351: 【 wlan.dmg.handover_reject_reason 】 
352: 【 wlan.dmg.destination_reds_aid 】 
353: 【 wlan.dmg.destination_aid 】 
354: 【 wlan.dmg.realy_aid 】 
355: 【 wlan.dmg.source_aid 】 
356: 【 wlan.dmg.timing_offset 】 
357: 【 wlan.dmg.sampling_frequency_offset 】 
358: 【 wlan.dmg.relay_operation_type 】 
359: 【 wlan.dmg.peer_sta_aid 】 
360: 【 wlan.dmg.snr 】 
361: 【 wlan.dmg.internal_angle 】 
362: 【 wlan.dmg.recommend 】 
363: 【 wlan.fst.action_code 】 
364: 【 wlan.fst.llt 】 
365: 【 wlan.session_trans.fsts_id 】 
366: 【 wlan.fst.mmpdu_length 】 
367: 【 wlan.fst.mmpdu_ctrl 】 
368: 【 wlan.fst.oct_mmpdu 】 
369: 【 wlan.vht.mimo_control.control 】 
370: 【 wlan.vht.mimo_control.ncindex 】 
371: 【 wlan.vht.mimo_control.nrindex 】 
372: 【 wlan.vht.mimo_control.chanwidth 】 
373: 【 wlan.vht.mimo_control.grouping 】 
374: 【 wlan.vht.mimo_control.codebookinfo 】 
375: 【 wlan.vht.mimo_control.feedbacktype 】 
376: 【 wlan.vht.mimo_control.remainingfeedbackseg 】 
377: 【 wlan.vht.mimo_control.firstfeedbackseg 】 
378: 【 wlan.vht.mimo_control.reserved 】 
379: 【 wlan.vht.mimo_control.sounding_dialog_tocken_nbr 】 
380: 【 wlan.vht.action 】 
381: 【 wlan.vht.compressed_beamforming_report 】 
383: 【 wlan.vht.compressed_beamforming_report.snr 】 
384: 【 wlan.vht.compressed_beamforming_report.phi 】 
385: 【 wlan.vht.compressed_beamforming_report.psi 】 
386: 【 wlan.vht.compressed_beamforming_report.feedback_matr
387: 【 wlan.vht.exclusive_beamforming_report.d
388: 【 wlan.vht.group_id_management 】 
389: 【 wlan.vht.membership_status_array 】 
390: 【 wlan.vht.user_position_array 】 
391: 【 wlan.vht.membership_status_array.field 】 
392: 【 wlan.vht.user_position_array.field 】 
393: 【 wlan.vht.operation_mode_notification 】 
394: 【 wlan.he.action 】 
395: 【 wlan.he.protected_action 】 
396: 【 wlan.he.mimo.nc_index 】 
397: 【 wlan.he.mimo.nr_index 】 
398: 【 wlan.he.mimo.bw 】 
399: 【 wlan.he.mimo.grouping 】 
400: 【 wlan.he.mimo.codebook_info 】 
401: 【 wlan.he.mimo.feedback_type 】 
402: 【 wlan.he.mimo.remaining_feedback_segs 】 
403: 【 wlan.he.mimo.first_feedback_seg 】 
404: 【 wlan.he.mimo.ru_start_index 】 
405: 【 wlan.he.mimo.ru_end_index 】 
406: 【 wlan.he.mimo.sounding_dialog_token_num 】 
407: 【 wlan.he.mimo.reserved 】 
408: 【 wlan.he.action.he_mimo_control 】 
409: 【 wlan.he.mimo.beamforming_report.avgsnr 】 
410: 【 wlan.he.action.he_mimo_control.scidx 】 
411: 【 wlan.he.action.he_mimo_control.report_len 】 
412: 【 wlan.dmg_tspec.allocation_id 】 
413: 【 wlan.dmg_tspec.allocation_type 】 
414: 【 wlan.dmg_tspec.allocation_format 】 
415: 【 wlan.dmg_tspec.pseudo_static 】 
416: 【 wlan.dmg_tspec.truncatable 】 
417: 【 wlan.dmg_tspec.extendable 】 
418: 【 wlan.dmg_tspec.lp_sc_used 】 
419: 【 wlan.dmg_tspec.up 】 
420: 【 wlan.dmg_tspec.dest_aid 】 
421: 【 wlan.dmg_tspec.allocation_period 】 
422: 【 wlan.dmg_tspec.min_allocation 】 
423: 【 wlan.dmg_tspec.max_allocation 】 
424: 【 wlan.dmg_tspec.min_duration 】 
425: 【 wlan.dmg_tspec.num_of_constraints 】 
426: 【 wlan.dmg_tspec.tsconst.start_time 】 
427: 【 wlan.dmg_tspec.tsconst.duration 】 
428: 【 wlan.dmg_tspec.tsconst.period 】 
429: 【 wlan.dmg_tspec.tsconst.interferer_mac 】 
430: 【 wlan.ch_meas_fb.realtive_I 】 
431: 【 wlan.ch_meas_fb.realtive_Q 】 
432: 【 wlan.ch_meas_fb.tap_delay 】 
433: 【 wlan.ch_meas_fb.sector_id 】 
434: 【 wlan.ch_meas_fb.antenna_id 】 
435: 【 wlan.awake_window 】 
436: 【 wlan.addba.no_frag 】 
437: 【 wlan.addba.he_frag_oper 】 
438: 【 wlan.addba.he_frag_oper 】 
439: 【 wlan.multi_band.ctrl_sta_role 】 
440: 【 wlan.multi_band.ctrl_addr_present 】 
441: 【 wlan.multi_band.ctrl_cipher_present 】 
442: 【 wlan.multi_band.oper_class 】 
443: 【 wlan.multi_band.channel_number 】 
444: 【 wlan.multi_band.tsf_offset 】 
445: 【 wlan.multi_band.conn_ap 】 
446: 【 wlan.multi_band.conn_pcp 】 
447: 【 wlan.multi_band.conn_dls 】 
448: 【 wlan.multi_band.conn_tdls 】 
449: 【 wlan.multi_band.conn_ibss 】 
450: 【 wlan.multi_band.fst_timeout 】 
451: 【 wlan.multi_band.sta_mac 】 
452: 【 wlan.activity 】 
453: 【 wlan.dmg_link_adapt.mcs 】 
454: 【 wlan.dmg_link_adapt.link_margin 】 
455: 【 wlan.ref_timestamp 】 
456: 【 wlan.switching_stream.non_qos 】 
457: 【 wlan.switching_stream.param_num 】 
458: 【 wlan.switching_stream.old_tid 】 
459: 【 wlan.switching_stream.old_direction 】 
460: 【 wlan.switching_stream.new_tid 】 
461: 【 wlan.switching_stream.new_direction 】 
462: 【 wlan.switching_stream.new_valid_id 】 
463: 【 wlan.switching_stream.llt_type 】 
464: 【 wlan.fixed.timestamp 】 
465: 【 wlan.fixed.auth.alg 】 
466: 【 wlan.fixed.beacon 】 
467: 【 wlan.fixed.all 】 
468: 【 wlan.tagged.all 】 
469: 【 wlan.ssid 】 
470: 【 wlan.supported_rates 】 
471: 【 wlan.fh.dwell_time 】 
472: 【 wlan.fh.hop_set 】 
473: 【 wlan.fh.hop_pattern 】 
474: 【 wlan.fh.hop_index 】 
475: 【 wlan.fixed.baparams 】 
476: 【 wlan.fixed.baparams.amsdu 】 
477: 【 wlan.fixed.baparams.policy 】 
478: 【 wlan.fixed.baparams.tid 】 
479: 【 wlan.fixed.baparams.buffersize 】 
480: 【 wlan.fixed.batimeout 】 
481: 【 wlan.fixed.ssc 】 
482: 【 wlan.fixed.ssc.fragment 】 
483: 【 wlan.fixed.ssc.sequence 】 
484: 【 wlan.fixed.delba.param 】 
485: 【 wlan.fixed.delba.param.reserved 】 
486: 【 wlan.fixed.delba.param.initiator 】 
487: 【 wlan.fixed.delba.param.tid 】 
488: 【 wlan.fixed.maxregpwr 】 
489: 【 wlan.fixed.msmtpilotint 】 
490: 【 wlan.fixed.country 】 
491: 【 wlan.fixed.maxtxpwr 】 
492: 【 wlan.fixed.txpwr 】 
493: 【 wlan.fixed.tnoisefloor 】 
494: 【 wlan.fixed.chanwidth 】 
495: 【 wlan.fixed.qosinfo.ap 】 
496: 【 wlan.fixed.qosinfo.ap.edcaupdate 】 
497: 【 wlan.fixed.qosinfo.ap.qack 】 
498: 【 wlan.fixed.qosinfo.ap.queue_req 】 
499: 【 wlan.fixed.qosinfo.ap.txopreq 】 
500: 【 wlan.fixed.qosinfo.ap.reserved 】 
501: 【 wlan.fixed.qosinfo.sta 】 
502: 【 wlan.fixed.qosinfo.sta.ac_vo 】 
503: 【 wlan.fixed.qosinfo.sta.ac_vi 】 
504: 【 wlan.fixed.qosinfo.sta.ac_bk 】 
505: 【 wlan.fixed.qosinfo.sta.ac_be 】 
506: 【 wlan.fixed.qosinfo.sta.qack 】 
507: 【 wlan.fixed.qosinfo.sta.max_sp_length 】 
508: 【 wlan.fixed.qosinfo.sta.more_data_ack 】 
509: 【 wlan.fixed.sm.powercontrol 】 
510: 【 wlan.fixed.sm.powercontrol.enabled 】 
511: 【 wlan.fixed.sm.powercontrol.mode 】 
512: 【 wlan.fixed.sm.powercontrol.reserved 】 
513: 【 wlan.fixed.pco.phasecntrl 】 
514: 【 wlan.fixed.psmp.paramset 】 
515: 【 wlan.fixed.psmp.paramset.nsta 】 
516: 【 wlan.fixed.psmp.paramset.more 】 
517: 【 wlan.fixed.psmp.paramset.seqduration 】 
518: 【 wlan.fixed.mimo.control 】 
519: 【 wlan.fixed.mimo.control.ncindex 】 
520: 【 wlan.fixed.mimo.control.nrindex 】 
521: 【 wlan.fixed.mimo.control.chanwidth 】 
522: 【 wlan.fixed.mimo.control.grouping 】 
523: 【 wlan.fixed.mimo.control.cosize 】 
524: 【 wlan.fixed.mimo.control.codebookinfo 】 
525: 【 wlan.fixed.mimo.control.matrixseg 】 
526: 【 wlan.fixed.mimo.control.reserved 】 
527: 【 wlan.fixed.mimo.control.soundingtime 】 
528: 【 wlan.fixed.psmp.stainfo 】 
529: 【 wlan.fixed.psmp.stainfo.type 】 
530: 【 wlan.fixed.psmp.stainfo.dttstart 】 
531: 【 wlan.fixed.psmp.stainfo.dttduration 】 
532: 【 wlan.fixed.psmp.stainfo.staid 】 
533: 【 wlan.fixed.psmp.stainfo.uttstart 】 
534: 【 wlan.fixed.psmp.stainfo.uttduration 】 
535: 【 wlan.fixed.psmp.stainfo.reserved 】 
536: 【 wlan.fixed.psmp.stainfo.reserved64 】 
537: 【 wlan.fixed.psmp.stainfo.multicastid 】 
538: 【 wlan.fixed.antsel 】 
539: 【 wlan.fixed.antsel.ant0 】 
540: 【 wlan.fixed.antsel.ant1 】 
541: 【 wlan.fixed.antsel.ant2 】 
542: 【 wlan.fixed.antsel.ant3 】 
543: 【 wlan.fixed.antsel.ant4 】 
544: 【 wlan.fixed.antsel.ant5 】 
545: 【 wlan.fixed.antsel.ant6 】 
546: 【 wlan.fixed.antsel.ant7 】 
547: 【 wlan.fixed.extchansw 】 
548: 【 wlan.fixed.extchansw.switchmode 】 
549: 【 wlan.fixed.extchansw.new.opeclass 】 
550: 【 wlan.fixed.extchansw.new.channumber 】 
551: 【 wlan.extchanswitch.switchcount 】 
552: 【 wlan.fixed.extchansw 】 
553: 【 wlan.fixed.mimo.control.chanwidth 】 
554: 【 wlan.fixed.mimo.control.chanwidth 】 
555: 【 wlan.fixed.mimo.control.chanwidth 】 
556: 【 wlan.fixed.extchansw 】 
557: 【 wlan.fixed.htact 】 
558: 【 wlan.mimo.csimatrices.snr 】 
559: 【 wlan.mimo.csimatrices 】 
560: 【 wlan.mimo.csimatrices.bf 】 
561: 【 wlan.mimo.csimatrices.cbf 】 
562: 【 wlan.fixed.publicact 】 
563: 【 wlan.fixed.publicact 】 
564: 【 wlan.fixed.capabilities 】 
565: 【 wlan.fixed.capabilities.ess 】 
566: 【 wlan.fixed.capabilities.ibss 】 
567: 【 wlan.fixed.capabilities.cfpoll.sta 】 
568: 【 wlan.fixed.capabilities.cfpoll.ap 】 
569: 【 wlan.fixed.capabilities.privacy 】 
570: 【 wlan.fixed.capabilities.preamble 】 
571: 【 wlan.fixed.capabilities.pbcc 】 
572: 【 wlan.fixed.capabilities.agility 】 
573: 【 wlan.fixed.capabilities.spec_man 】 
574: 【 wlan.fixed.capabilities.short_slot_time 】 
575: 【 wlan.fixed.capabilities.apsd 】 
576: 【 wlan.fixed.capabilities.radio_measurement 】 
577: 【 wlan.fixed.capabilities.dsss_ofdm 】 
578: 【 wlan.fixed.capabilities.del_blk_ack 】 
579: 【 wlan.fixed.capabilities.imm_blk_ack 】 
580: 【 wlan.fixed.auth_seq 】 
581: 【 wlan.fixed.aid 】 
582: 【 wlan.fixed.listen_ival 】 
583: 【 wlan.fixed.current_ap 】 
584: 【 wlan.fixed.reason_code 】 
585: 【 wlan.fixed.status_code 】 
586: 【 wlan.fixed.category_code 】 
587: 【 wlan.fixed.action_code 】 
588: 【 wlan.fixed.dialog_token 】 
589: 【 wlan.fixed.followup_dialog_token 】 
590: 【 wlan.fixed.mrvl_action_type 】 
591: 【 wlan.fixed.mrvl_mesh_action 】 
592: 【 wlan.fixed.length 】 
593: 【 wlan.fixed.mode 】 
594: 【 wlan.fixed.ttl 】 
595: 【 wlan.fixed.dstcount 】 
596: 【 wlan.fixed.hopcount 】 
597: 【 wlan.fixed.rreqid 】 
598: 【 wlan.fixed.sa 】 
599: 【 wlan.fixed.ssn 】 
600: 【 wlan.fixed.metric 】 
601: 【 wlan.fixed.hopcount 】 
602: 【 wlan.fixed.da 】 
603: 【 wlan.fixed.dsn 】 
604: 【 wlan.fixed.lifetime 】 
605: 【 wlan.fixed.action_code 】 
606: 【 wlan.fixed.status_code 】 
607: 【 wlan.fixed.mesh_action 】 
608: 【 wlan.fixed.multihop_action 】 
609: 【 wlan.fixed.mesh_flags 】 
610: 【 wlan.fixed.mesh_ttl 】 
611: 【 wlan.fixed.mesh_sequence 】 
612: 【 wlan.fixed.mesh_addr4 】 
613: 【 wlan.fixed.mesh_addr5 】 
614: 【 wlan.fixed.mesh_addr6 】 
615: 【 wlan.fixed.selfprot_action 】 
616: 【 wlan.peering.proto 】 
617: 【 wlan.peering.local_id 】 
618: 【 wlan.peering.peer_id 】 
619: 【 wlan.hwmp.flags 】 
620: 【 wlan.hwmp.hopcount 】 
621: 【 wlan.hwmp.ttl 】 
622: 【 wlan.hwmp.pdid 】 
623: 【 wlan.hwmp.orig_sta 】 
624: 【 wlan.hwmp.orig_sn 】 
625: 【 wlan.hwmp.orig_ext 】 
626: 【 wlan.hwmp.lifetime 】 
627: 【 wlan.hwmp.metric 】 
628: 【 wlan.hwmp.targ_count 】 
629: 【 wlan.hwmp.targ_flags 】 
630: 【 wlan.hwmp.to_flag 】 
631: 【 wlan.hwmp.usn_flag 】 
632: 【 wlan.hwmp.targ_sta 】 
633: 【 wlan.hwmp.targ_ext 】 
634: 【 wlan.hwmp.targ_sn 】 
635: 【 wlan.mesh.config.ps_protocol 】 
636: 【 wlan.mesh.config.ps_metric 】 
637: 【 wlan.mesh.config.cong_ctl 】 
638: 【 wlan.mesh.config.sync_method 】 
639: 【 wlan.mesh.config.auth_protocol 】 
640: 【 wlan.mesh.config.formation_info 】 
641: 【 wlan.mesh.config.formation_info.num_peers 】 
642: 【 wlan.mesh.config.cap 】 
643: 【 wlan.mesh.config.cap.accept 】 
644: 【 wlan.mesh.config.cap.mcca_support 】 
645: 【 wlan.mesh.config.cap.mcca_enabled 】 
646: 【 wlan.mesh.config.cap.forwarding 】 
647: 【 wlan.mesh.config.cap.mbca_enabled 】 
648: 【 wlan.mesh.config.cap.tbtt_adjusting 】 
649: 【 wlan.mesh.config.cap.power_save_level 】 
650: 【 wlan.mesh.id 】 
651: 【 wlan.rann.flags 】 
652: 【 wlan.rann.root_sta 】  FT_ETHER,   0,
653: 【 wlan.rann.rann_sn 】 
654: 【 wlan.rann.interval 】 
655: 【 wlan.fixed.action_code 】 
656: 【 wlan.fixed.action_code 】 
657: 【 wlan.fixed.check_beacon 】 
658: 【 wlan.fixed.tod 】 
659: 【 wlan.fixed.toa 】 
660: 【 wlan.fixed.max_tod_err 】 
661: 【 wlan.fixed.max_toa_err 】 
662: 【 wlan.fixed.action_code 】 
663: 【 wlan.fixed.dst_mac_addr 】 
664: 【 wlan.fixed.src_mac_addr 】 
665: 【 wlan.fixed.req_ap_addr 】 
666: 【 wlan.fixed.res_ap_addr 】 
667: 【 wlan.fixed.action_code 】 
668: 【 wlan.fixed.sta_address 】 
669: 【 wlan.fixed.target_ap_address 】 
670: 【 wlan.fixed.gas_comeback_delay 】 
671: 【 wlan.fixed.gas_fragment_id 】 
672: 【 wlan.fixed.more_gas_fragments 】 
673: 【 wlan.fixed.query_request_length 】 
674: 【 wlan.fixed.query_request 】 
675: 【 wlan.fixed.query_response_length 】 
676: 【 wlan.fixed.query_response 】 
677: 【 wlan.fixed.fragments 】 
678: 【 wlan.fixed.fragment 】 
679: 【 wlan.fixed.fragment.overlap 】 
680: 【 wlan.fixed.fragment.overlap.conflicts 】 
681: 【 wlan.fixed.fragment.multiple_tails 】 
682: 【 wlan.fixed.fragment.too_long_fragment 】 
683: 【 wlan.fixed.fragment.error 】 
684: 【 wlan.fixed.fragment.count 】 
685: 【 wlan.fixed.reassembled.in 】 
686: 【 wlan.fixed.reassembled.length 】 
687: 【 wlan.fixed.anqp.info_id 】 
688: 【 wlan.fixed.anqp.info_length 】 
689: 【 wlan.fixed.anqp.info 】 
690: 【 wlan.fixed.anqp.query_id 】 
691: 【 wlan.fixed.anqp.capability 】 
692: 【 wlan.fixed.anqp.capability_vlen 】 
693: 【 wlan.fixed.anqp.capability_vendor 】 
694: 【 wlan.fixed.venue_info.group 】 
695: 【 wlan.fixed.venue_info.type 】 
696: 【 wlan.fixed.anqp.venue.length 】 
697: 【 wlan.fixed.anqp.venue.language 】 
698: 【 wlan.fixed.anqp.venue.name 】 
699: 【 wlan.fixed.anqp.nw_auth_type.indicator 】 
700: 【 wlan.fixed.anqp.nw_auth_type.url_len 】 
701: 【 wlan.fixed.anqp.nw_auth_type_url 】 
702: 【 wlan.fixed.anqp.roaming_consortium.oi_len 】 
703: 【 wlan.fixed.anqp.roaming_consortium.oi 】 
704: 【 wlan.fixed.anqp.ip_addr_availability.ipv6 】 
705: 【 wlan.fixed.anqp.ip_addr_availability.ipv4 】 
706: 【 wlan.fixed.anqp.nai_realm_list.count 】 
707: 【 wlan.fixed.anqp.nai_realm_list.field_len 】 
708: 【 wlan.fixed.naqp_nai_realm_list.encoding 】 
709: 【 wlan.fixed.naqp_nai_realm_list.realm_length 】 
710: 【 wlan.fixed.naqp_nai_realm_list.realm 】 
711: 【 wlan.fixed.naqp_nai_realm_list.eap_method_count 】 
712: 【 wlan.fixed.naqp_nai_realm_list.eap_method_len 】 
713: 【 wlan.fixed.naqp_nai_realm_list.eap_method 】 
714: 【 wlan.fixed.naqp_nai_realm_list.auth_param_count 】 
715: 【 wlan.fixed.naqp_nai_realm_list.auth_param_id 】 
716: 【 wlan.fixed.naqp_nai_realm_list.auth_param_len 】 
717: 【 wlan.fixed.naqp_nai_realm_list.auth_param_value 】 
718: 【 wlan.fixed.anqp.3gpp_cellular_info.gud 】 
719: 【 wlan.fixed.anqp.3gpp_cellular_info.udhl 】 
720: 【 wlan.fixed.anqp.3gpp_cellular_info.iei 】 
721: 【 wlan.fixed.anqp.3gpp_cellular_info.plmn_len 】 
722: 【 wlan.fixed.anqp.3gpp_cellular_info.num_plmns 】 
723: 【 wlan.fixed.anqp.3gpp_cellular_info.plmn_info 】 
724: 【 wlan.fixed.anqp.domain_name_list.len 】 
725: 【 wlan.fixed.anqp.domain_name_list.name 】 
726: 【 wlan.fixed.dls_timeout 】 
727: 【 wlan.fixed.action_code 】 
728: 【 wlan.fixed.transaction_id 】 
729: 【 wlan.fixed.send_confirm 】 
730: 【 wlan.fixed.anti_clogging_token 】 
731: 【 wlan.fixed.scalar 】 
732: 【 wlan.fixed.finite_field_element 】 
733: 【 wlan.fixed.confirm 】 
734: 【 wlan.fixed.finite_cyclic_group 】 
735: 【 wlan.fixed.sae_message_type 】 
736: 【 wlan.anqp.wfa.subtype 】 
737: 【 wlan.wfa.dpp.subtype 】 
738: 【 wlan.hs20.indication.dgaf_disabled 】 
739: 【 wlan.hs20.indication.pps_mo_id_present 】 
740: 【 wlan.hs20.indication.anqp_domain_id_present 】 
741: 【 wlan.hs20.indication.reserved 】 
742: 【 wlan.hs20.indication.release_number 】 
743: 【 wlan.hs20.indication.pps_mo_id 】 
744: 【 wlan.hs20.indication.domain_id 】 
745: 【 wlan.hs20.anqp.subtype 】 
746: 【 wlan.hs20.anqp.reserved 】 
747: 【 wlan.hs20.anqp.payload 】 
748: 【 wlan.hs20.anqp.hs_query_list 】 
749: 【 wlan.hs20.anqp.hs_capability_list 】 
750: 【 wlan.hs20.anqp.ofn.length 】 
751: 【 wlan.hs20.anqp.ofn.language 】 
752: 【 wlan.hs20.anqp.ofn.name 】 
753: 【 wlan.hs20.anqp.wan_metrics.link_status 】 
754: 【 wlan.hs20.anqp.wan_metrics.symmetric_link 】 
755: 【 wlan.hs20.anqp.wan_metrics.at_capacity 】 
756: 【 wlan.hs20.anqp.wan_metrics.reserved 】 
757: 【 wlan.hs20.anqp.wan_metrics.downlink_speed 】 
758: 【 wlan.hs20.anqp.wan_metrics.uplink_speed 】 
759: 【 wlan.hs20.anqp.wan_metrics.downlink_load 】 
760: 【 wlan.hs20.anqp.wan_metrics.uplink_load 】 
761: 【 wlan.hs20.anqp.wan_metrics.lmd 】 
762: 【 wlan.hs20.anqp.cc.ip_proto 】 
763: 【 wlan.hs20.anqp.cc.port_num 】 
764: 【 wlan.hs20.anqp.cc.status 】 
765: 【 wlan.hs20.anqp.nai_hrq.count 】 
767: 【 wlan.hs20.anqp.nai_hrq.length 】 
768: 【 wlan.hs20.anqp.nai_hrq.name 】 
769: 【 wlan.hs20.anqp.oper_class_indic.oper_class 】 
770: 【 wlan.hs20.osu_friendly_names_len 】 
771: 【 wlan.hs20.osu_friendly_name.len 】 
772: 【 wlan.hs20.osu_friendly_name.language 】 
773: 【 wlan.hs20.osu_friendly_name.name 】 
774: 【 wlan.hs20.osu_server_uri_len 】 
775: 【 wlan.hs20.osu_server_uri 】 
776: 【 wlan.hs20.osu_method_list_len 】 
777: 【 wlan.hs20.osu_method_list.method 】 
778: 【 wlan.hs20.osu_icons_avail_len 】 
779: 【 wlan.hs20.anqp_osu_prov_list.ssid_len 】 
780: 【 wlan.hs20.anqp_osu_prov_list.ssid 】 
781: 【 wlan.hs20.anqp_osu_prov_list.number 】 
782: 【 wlan.hs20.anqp_osu_prov.len 】 
783: 【 wlan.hs20.anqp_icon_request.icon_filename 】 
784: 【 wlan.hs20.osu_icons_avail.icon_width 】 
785: 【 wlan.hs20.osu_icons_avail.icon_height 】 
786: 【 wlan.hs20.osu_icons_avail.lang_code 】 
787: 【 wlan.hs20.osu_icons_avail.icon_type_len 】 
788: 【 wlan.hs20.osu_icons_avail.icon_type 】 
789: 【 wlan.hs20.osu_icons_avail.icon_filename_len 】 
790: 【 wlan.hs20.osu_icons_avail.icon_filename 】 
791: 【 wlan.hs20.osu_nai.len 】 
792: 【 wlan.hs20.osu_nai 】 
793: 【 wlan.hs20.osu_service_desc_len 】 
794: 【 wlan.hs20.osu_service_desc.duple.len 】 
795: 【 wlan.hs20.osu_service_desc.duple.lang 】 
796: 【 wlan.hs20.osu_service_desc.duple.desc 】 
797: 【 wlan.hs20.anqp_icon_request.download_status 】 
798: 【 wlan.hs20.anqp_icon_request.icon_type_len 】 
799: 【 wlan.hs20.anqp_icon_request.icon_type 】 
800: 【 wlan.anqp_icon_request.icon_binary_data_len 】 
801: 【 wlan.h220.anqp_icon_request.icon_binary_data 】 
802: 【 wlan.hs20.subs_remediation.server_url_len 】 
803: 【 wlan.hs20.subs_remediation.server_url 】 
804: 【 wlan.hs20.subs_remediation.server_method 】 
805: 【 wlan.hs20.deauth.reason_code 】 
806: 【 wlan.hs20.deauth.reauth_delay 】 
807: 【 wlan.hs20.deauth.reason_url_len 】 
808: 【 wlan.hs20.deauth.reason_url 】 
809: 【 wlan.hs20.venue_url.len 】 
810: 【 wlan.hs20.venue_url.venue_num 】 
811: 【 wlan.hs20.venue_url.url 】 
812: 【 wlan.hs20.advice_of_charge.len 】 
813: 【 wlan.hs20.advice_of_charge.type 】 
814: 【 wlan.hs20.advice_of_charge.nai_realm_enc 】 
815: 【 wlan.hs20.advice_of_charge.nai_realm_len 】 
816: 【 wlan.hs20.advice_of_charge.nai_realm 】 
817: 【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_len 】 
818: 【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_lang 】 
819: 【 wlan.hs20.advice_of_charge.plan_info_tuples.plan_curcy 】 
820: 【 wlan.hs20.advice_of_charge.plan_info_tuples.info 】 
821: 【 wlan.tag 】 
822: 【 wlan.tag.number 】 
823: 【 wlan.tag.length 】 
824: 【 wlan.tag.data 】 
825: 【 wlan.tag.oui 】 
826: 【 wlan.tag.oui.wfa_subtype 】 
827: 【 wlan.ds.current_channel 】 
828: 【 wlan.cfp.count 】 
829: 【 wlan.cfp.period 】 
830: 【 wlan.cfp.max_duration 】 
831: 【 wlan.cfp.dur_remaining 】 
832: 【 wlan.tag.vendor.oui.type 】 
833: 【 wlan.tag.vendor.data 】 
834: 【 wlan.tim.dtim_count 】 
835: 【 wlan.tim.dtim_period 】 
836: 【 wlan.tim.bmapctl 】 
837: 【 wlan.tim.bmapctl.multicast 】 
838: 【 wlan.tim.bmapctl.offset 】 
839: 【 wlan.tim.partial_virtual_bitmap 】 
840: 【 wlan.tim.aid 】 
841: 【 wlan.ibss.atim_windows 】 
842: 【 wlan.country_info.code 】 
843: 【 wlan.country_info.environment 】 
844: 【 wlan.country_info.padding 】 
845: 【 wlan.country_info.fnm 】 
846: 【 wlan.country_info.fnm.fcn 】 
847: 【 wlan.country_info.fnm.nc 】 
848: 【 wlan.country_info.fnm.mtpl 】 
849: 【 wlan.country_info.rrc 】 
850: 【 wlan.country_info.rrc.oei 】 
851: 【 wlan.country_info.rrc.oc 】 
852: 【 wlan.country_info.rrc.cc 】 
853: 【 wlan.fh_hopping.parameter.prime_radix 】 
854: 【 wlan.fh_hopping.parameter.nb_channels 】 
855: 【 wlan.fh_hopping.table.flag 】 
856: 【 wlan.fh_hopping.table.number_of_sets 】 
857: 【 wlan.fh_hopping.table.modulus 】 
858: 【 wlan.fh_hopping.table.offset 】 
859: 【 wlan.fh_hopping.table.random_table 】 
860: 【 wlan.tag.request 】 
861: 【 wlan.tclas.user_priority 】 
862: 【 wlan.tclas.class_type 】 
863: 【 wlan.tclas.class_mask 】 
864: 【 wlan.tclas.class_mask.src_addr 】 
865: 【 wlan.tclas.class_mask.dst_addr 】 
866: 【 wlan.tclas.class_mask.type 】 
867: 【 wlan.tclas.class_mask.version 】 
868: 【 wlan.tclas.class_mask.src_ip 】 
869: 【 wlan.tclas.class_mask.dst_ip 】 
870: 【 wlan.tclas.class_mask.src_port 】 
871: 【 wlan.tclas.class_mask.dst_port 】 
872: 【 wlan.tclas.class_mask.dscp 】 
873: 【 wlan.tclas.class_mask.proto 】 
874: 【 wlan.tclas.class_mask.flow_label 】 
875: 【 wlan.tclas.class_mask.tci 】 
876: 【 wlan.tclas.src_mac_addr 】 
877: 【 wlan.tclas.dat_mac_addr 】 
878: 【 wlan.tclas.ether_type 】 
879: 【 wlan.tclas.version 】 
880: 【 wlan.tclas.ipv4_src 】 
881: 【 wlan.tclas.ipv4_dst 】 
882: 【 wlan.tclas.src_port 】 
883: 【 wlan.tclas.dst_port 】 
884: 【 wlan.tclas.dscp 】 
885: 【 wlan.tclas.protocol 】 
886: 【 wlan.tclas.ipv6_src 】 
887: 【 wlan.tclas.ipv6_dst 】 
888: 【 wlan.tclas.flow 】 
889: 【 wlan.tclas.tag_type 】 
890: 【 wlan.tag.challenge_text 】 
891: 【 wlan.rsn.version 】 
892: 【 wlan.rsn.gcs 】 
893: 【 wlan.rsn.gcs.oui 】 
894: 【 wlan.rsn.gcs.type 】 
895: 【 wlan.rsn.gcs.type 】 
896: 【 wlan.rsn.pcs.count 】 
897: 【 wlan.rsn.pcs.list 】 
898: 【 wlan.rsn.pcs 】 
899: 【 wlan.rsn.pcs.oui 】 
900: 【 wlan.rsn.pcs.type 】 
901: 【 wlan.rsn.pcs.type 】 
902: 【 wlan.rsn.akms.count 】 
903: 【 wlan.rsn.akms.list 】 
904: 【 wlan.rsn.akms 】 
905: 【 wlan.rsn.akms.oui 】 
906: 【 wlan.rsn.akms.type 】 
907: 【 wlan.rsn.akms.type 】 
908: 【 wlan.rsn.capabilities 】 
909: 【 wlan.rsn.capabilities.preauth 】 
910: 【 wlan.rsn.capabilities.no_pairwise 】 
911: 【 wlan.rsn.capabilities.ptksa_replay_counter 】 
912: 【 wlan.rsn.capabilities.gtksa_replay_counter 】 
913: 【 wlan.rsn.capabilities.mfpr 】 
914: 【 wlan.rsn.capabilities.mfpc 】 
915: 【 wlan.rsn.capabilities.jmr 】 
916: 【 wlan.rsn.capabilities.peerkey 】 
917: 【 wlan.rsn.pmkid.count 】 
918: 【 wlan.rsn.pmkid.list 】 
919: 【 wlan.pmkid.akms 】 
920: 【 wlan.rsn.gmcs 】 
921: 【 wlan.rsn.gmcs.oui 】 
922: 【 wlan.rsn.gmcs.type 】 
923: 【 wlan.rsn.gmcs.type 】 
924: 【 wlan.vs.pren.type 】 
925: 【 wlan.vs.pren.unknown_data 】 
926: 【 wlan.ht.capabilities 】 
927: 【 wlan.vs.ht.capabilities 】 
928: 【 wlan.ht.capabilities.ldpccoding 】 
929: 【 wlan.ht.capabilities.width 】 
930: 【 wlan.ht.capabilities.sm 】 
931: 【 wlan.ht.capabilities.green 】 
932: 【 wlan.ht.capabilities.short20 】 
933: 【 wlan.ht.capabilities.short40 】 
934: 【 wlan.ht.capabilities.txstbc 】 
935: 【 wlan.ht.capabilities.rxstbc 】 
936: 【 wlan.ht.capabilities.delayedblockack 】 
937: 【 wlan.ht.capabilities.amsdu 】 
938: 【 wlan.ht.capabilities.dsscck 】 
939: 【 wlan.ht.capabilities.psmp 】 
940: 【 wlan.ht.capabilities.40mhzintolerant 】 
941: 【 wlan.ht.capabilities.lsig 】 
942: 【 wlan.ext_bss.mu_mimo_capable_sta_count 】 
943: 【 wlan.ext_bss.ss_underutilization 】 
944: 【 wlan.ext_bss.observable_sec_20mhz_utilization 】 
945: 【 wlan.ext_bss.observable_sec_40mhz_utilization 】 
946: 【 wlan.ext_bss.observable_sec_80mhz_utilization 】 
947: 【 wlan.wide_bw.new_channel_width 】 
948: 【 wlan.wide_bw.new_channel_center_freq_segment0 】 
949: 【 wlan.wide_bw.new_channel_center_freq_segment1 】 
950: 【 wlan.operat_notification_mode 】 
951: 【 wlan.operat_mode_field.channelwidth 】 
952: 【 wlan.operat_mode_field.reserved 】 
953: 【 wlan.operat_mode_field.rxnss 】 
954: 【 wlan.operat_mode_field.rxnsstype 】 
955: 【 wlan.ht.ampduparam 】 
956: 【 wlan.vs.ht.ampduparam 】 
957: 【 wlan.ht.ampduparam.maxlength 】 
958: 【 wlan.ht.ampduparam.mpdudensity 】 
959: 【 wlan.ht.ampduparam.reserved 】 
960: 【 wlan.ht.mcsset 】 
961: 【 wlan.vs.ht.mcsset 】 
962: 【 wlan.ht.mcsset.rxbitmask 】 
963: 【 wlan.ht.mcsset.rxbitmask.0to7 】 
964: 【 wlan.ht.mcsset.rxbitmask.8to15 】 
965: 【 wlan.ht.mcsset.rxbitmask.16to23 】 
966: 【 wlan.ht.mcsset.rxbitmask.24to31 】 
967: 【 wlan.ht.mcsset.rxbitmask.32 】 
968: 【 wlan.ht.mcsset.rxbitmask.33to38 】 
969: 【 wlan.ht.mcsset.rxbitmask.39to52 】 
970: 【 wlan.ht.mcsset.rxbitmask.53to76 】 
971: 【 wlan.ht.mcsset.highestdatarate 】 
972: 【 wlan.ht.mcsset.txsetdefined 】 
973: 【 wlan.ht.mcsset.txrxmcsnotequal 】 
974: 【 wlan.ht.mcsset.txmaxss 】 
975: 【 wlan.ht.mcsset.txunequalmod 】 
976: 【 wlan.htex.capabilities 】 
977: 【 wlan.vs.htex.capabilities 】 
978: 【 wlan.htex.capabilities.pco 】 
979: 【 wlan.htex.capabilities.transtime 】 
980: 【 wlan.htex.capabilities.mcs 】 
981: 【 wlan.htex.capabilities.htc 】 
982: 【 wlan.htex.capabilities.rdresponder 】 
983: 【 wlan.txbf 】 
984: 【 wlan.vs.txbf 】 
985: 【 wlan.txbf.txbf 】 
986: 【 wlan.txbf.rxss 】 
987: 【 wlan.txbf.txss 】 
988: 【 wlan.txbf.rxndp 】 
989: 【 wlan.txbf.txndp 】 
990: 【 wlan.txbf.impltxbf 】 
991: 【 wlan.txbf.calibration 】 
992: 【 wlan.txbf.csi 】 
993: 【 wlan.txbf.fm.uncompressed
994: 【 wlan.txbf.fm.compressed.tbf
995: 【 wlan.txbf.rcsi 】 
996: 【 wlan.txbf.fm.uncompre
997: 【 wlan.txbf.fm.compressed.bf 
998: 【 wlan.txbf.mingroup 】 
999: 【 wlan.vht.capabilities 】 
1000: 【 wlan.vht.capabilities.maxmpdulength 】 
1001: 【 wlan.vht.capabilities.supportedchanwidthset 】 
1002: 【 wlan.vht.capabilities.rxldpc 】 
1003: 【 wlan.vht.capabilities.short80 】 
1004: 【 wlan.vht.capabilities.short160 】 
1005: 【 wlan.vht.capabilities.txstbc 】 
1006: 【 wlan.vht.capabilities.rxstbc 】 
1007: 【 wlan.vht.capabilities.subeamformer 】 
1008: 【 wlan.vht.capabilities.subeamformee 】 
1009: 【 wlan.vht.capabilities.beamformee_sts_cap 】 
1010: 【 wlan.vht.capabilities.soundingdimensions 】 
1011: 【 wlan.vht.capabilities.mubeamformer 】 
1012: 【 wlan.vht.capabilities.mubeamformee 】 
1013: 【 wlan.vht.capabilities.vhttxopps 】 
1014: 【 wlan.vht.capabilities.vhthtc 】 
1015: 【 wlan.vht.capabilities.maxampdu 】 
1016: 【 wlan.vht.capabilities.linkadapt 】 
1017: 【 wlan.vht.capabilities.rxpatconsist 】 
1018: 【 wlan.vht.capabilities.txpatconsist 】 
1019: 【 wlan.vht.capabilities.ext_nss_bw_support 】 
1020: 【 wlan.vht.mcsset 】 
1021: 【 wlan.vht.mcsset.rxmcsmap 】 
1022: 【 wlan.vht.mcsset.rxmcsmap.ss1 】 
1023: 【 wlan.vht.mcsset.rxmcsmap.ss2 】 
1024: 【 wlan.vht.mcsset.rxmcsmap.ss3 】 
1025: 【 wlan.vht.mcsset.rxmcsmap.ss4 】 
1026: 【 wlan.vht.mcsset.rxmcsmap.ss5 】 
1027: 【 wlan.vht.mcsset.rxmcsmap.ss6 】 
1028: 【 wlan.vht.mcsset.rxmcsmap.ss7 】 
1029: 【 wlan.vht.mcsset.rxmcsmap.ss8 】 
1030: 【 wlan.vht.mcsset.max_nsts_total 】 
1031: 【 wlan.vht.mcsset.rxhighestl
1032: 【 wlan.vht.mcsset.txmcsmap 】 
1033: 【 wlan.vht.mcsset.txmcsmap.ss1 】 
1034: 【 wlan.vht.mcsset.txmcsmap.ss2 】 
1035: 【 wlan.vht.mcsset.txmcsmap.ss3 】 
1036: 【 wlan.vht.mcsset.txmcsmap.ss4 】 
1037: 【 wlan.vht.mcsset.txmcsmap.ss5 】 
1038: 【 wlan.vht.mcsset.txmcsmap.ss6 】 
1039: 【 wlan.vht.mcsset.txmcsmap.ss7 】 
1040: 【 wlan.vht.mcsset.txmcsmap.ss8 】 
1041: 【 wlan.vht.mcsset.txhighest
1042: 【 wlan.vht.ncsset.ext_nss_bw_cap 】 
1043: 【 wlan.vht.ncsset.reserved 】 
1044: 【 wlan.vht.op 】 
1045: 【 wlan.vht.op.channelwidth 】 
1046: 【 wlan.vht.op.channelcenter0 】 
1047: 【 wlan.vht.op.channelcenter1 】 
1048: 【 wlan.vht.op.basicmcsmap 】 
1049: 【 wlan.vht.op.basicmcsmap.ss1 】 
1050: 【 wlan.vht.op.basicmcsmap.ss2 】 
1051: 【 wlan.vht.op.basicmcsmap.ss3 】 
1052: 【 wlan.vht.op.basicmcsmap.ss4 】 
1053: 【 wlan.vht.op.basicmcsmap.ss5 】 
1054: 【 wlan.vht.op.basicmcsmap.ss6 】 
1055: 【 wlan.vht.op.basicmcsmap.ss7 】 
1056: 【 wlan.vht.op.basicmcsmap.ss8 】 
1057: 【 wlan.vht.tpe.pwr_info 】 
1058: 【 wlan.vht.tpe.pwr_info.count 】 
1059: 【 wlan.vht.tpe.pwr_info.unit 】 
1060: 【 wlan.vht.tpe.pwr_info.reserved 】 
1061: 【 wlan.vht.tpe.pwr_constr_20 】 
1062: 【 wlan.vht.tpe.pwr_constr_40 】 
1063: 【 wlan.vht.tpe.pwr_constr_80 】 
1064: 【 wlan.vht.tpe.pwr_constr_160 】 
1065: 【 wlan.txbf.csinumant 】 
1066: 【 wlan.txbf.fm.uncompressed.maxant 】 
1067: 【 wlan.txbf.fm.compressed.maxant 】
1068: 【 wlan.txbf.csi.maxrows 】 
1069: 【 wlan.txbf.channelest 】 
1070: 【 wlan.txbf.reserved 】 
1071: 【 wlan.hta.control_channel 】 
1072: 【 wlan.hta.capabilities 】 
1073: 【 wlan.hta.capabilities 】 
1074: 【 wlan.hta.capabilities.ext_chan_offset 】 
1075: 【 wlan.hta.capabilities.rec_tx_width 】 
1076: 【 wlan.hta.capabilities.rifs_mode 】 
1077: 【 wlan.hta.capabilities.controlled_access 】 
1078: 【 wlan.hta.capabilities.service_interval 】 
1079: 【 wlan.hta.capabilities.operating_mode 】 
1080: 【 wlan.hta.capabilities.non_gf_devices 】 
1081: 【 wlan.hta.capabilities.basic_stbc_mcs 】 
1082: 【 wlan.hta.capabilities.dual_stbc_protection 】 
1083: 【 wlan.hta.capabilities.secondary_beacon 】 
1084: 【 wlan.hta.capabilities.lsig_txop_protection 】 
1085: 【 wlan.hta.capabilities.pco_active 】 
1086: 【 wlan.hta.capabilities.pco_phase 】 
1087: 【 wlan.asel 】 
1088: 【 wlan.vs.asel 】 
1089: 【 wlan.asel.capable 】 
1090: 【 wlan.asel.txcsi 】 
1091: 【 wlan.asel.txif 】 
1092: 【 wlan.asel.csi 】 
1093: 【 wlan.asel.if 】 
1094: 【 wlan.asel.rx 】 
1095: 【 wlan.asel.sppdu 】 
1096: 【 wlan.asel.reserved 】 
1097: 【 wlan.ht.info.delim1 】 
1098: 【 wlan.ht.info.primarychannel 】 
1099: 【 wlan.ht.info.secchanoffset 】 
1100: 【 wlan.ht.info.chanwidth 】 
1101: 【 wlan.ht.info.rifs 】 
1102: 【 wlan.ht.info.reserved_b4_b7 】 
1103: 【 wlan.ht.info.delim2 】 
1104: 【 wlan.ht.info.ht_protection 】 
1105: 【 wlan.ht.info.greenfield 】 
1106: 【 wlan.ht.info.reserved_b11 】 
1107: 【 wlan.ht.info.obssnonht 】 
1108: 【 wlan.ht.info.chan_center_freq_seg_2 】 
1109: 【 wlan.ht.info.reserved_b21_b23 】 
1110: 【 wlan.ht.info.delim3 】 
1111: 【 wlan.ht.info.reserved_b24_b29 】 
1112: 【 wlan.ht.info.dualbeacon 】 
1113: 【 wlan.ht.info.dualcts 】 
1114: 【 wlan.ht.info.secondarybeacon 】 
1115: 【 wlan.ht.info.lsigprotsupport 】 
1116: 【 wlan.ht.info.pco.active 】 
1117: 【 wlan.ht.info.pco.phase 】 
1118: 【 wlan.ht.info.reserved_b36_b39 】 
1119: 【 wlan.ap_channel_report.operating_class 】 
1120: 【 wlan.ap_channel_report.channel_list 】 
1121: 【 wlan.secchanoffset 】 
1122: 【 wlan.bss_ap_avg_access_delay 】 
1123: 【 wlan.antenna.id 】 
1124: 【 wlan.rsni 】 
1125: 【 wlan.bss_avb_adm_cap.bitmask 】 
1126: 【 wlan.bss_avb_adm_cap.bitmask.up0 】 
1127: 【 wlan.bss_avb_adm_cap.bitmask.up1 】 
1128: 【 wlan.bss_avb_adm_cap.bitmask.up2 】 
1129: 【 wlan.bss_avb_adm_cap.bitmask.up3 】 
1130: 【 wlan.bss_avb_adm_cap.bitmask.up4 】 
1131: 【 wlan.bss_avb_adm_cap.bitmask.up5 】 
1132: 【 wlan.bss_avb_adm_cap.bitmask.up6 】 
1133: 【 wlan.bss_avb_adm_cap.bitmask.up7 】 
1134: 【 wlan.bss_avb_adm_cap.bitmask.ac0 】 
1135: 【 wlan.bss_avb_adm_cap.bitmask.AC1 】 
1136: 【 wlan.bss_avb_adm_cap.bitmask.ac2 】 
1137: 【 wlan.bss_avb_adm_cap.bitmask.ac3 】 
1138: 【 wlan.bss_avb_adm_cap.bitmask.rsv 】 
1139: 【 wlan.bss_avb_adm_cap.up0 】 
1140: 【 wlan.bss_avb_adm_cap.up1 】 
1141: 【 wlan.bss_avb_adm_cap.up2 】 
1142: 【 wlan.bss_avb_adm_cap.up3 】 
1143: 【 wlan.bss_avb_adm_cap.up4 】 
1144: 【 wlan.bss_avb_adm_cap.up5 】 
1145: 【 wlan.bss_avb_adm_cap.up6 】 
1146: 【 wlan.bss_avb_adm_cap.up7 】 
1147: 【 wlan.bss_avb_adm_cap.ac0 】 
1148: 【 wlan.bss_avb_adm_cap.ac1 】 
1149: 【 wlan.bss_avb_adm_cap.ac2 】 
1150: 【 wlan.bss_avb_adm_cap.ac3 】 
1151: 【 wlan.bss_avg_ac_access_delay.be 】 
1152: 【 wlan.bss_avg_ac_access_delay.bk 】 
1153: 【 wlan.bss_avg_ac_access_delay_vi 】 
1154: 【 wlan.bss_avg_ac_access_delay_vo 】 
1155: 【 wlan.rmcap 】 
1156: 【 wlan.rmcap.b0 】 
1157: 【 wlan.rmcap.b1 】 
1158: 【 wlan.rmcap.b2 】 
1159: 【 wlan.rmcap.b3 】 
1160: 【 wlan.rmcap.b4 】 
1161: 【 wlan.rmcap.b5 】 
1162: 【 wlan.rmcap.b6 】 
1163: 【 wlan.rmcap.b7 】 
1164: 【 wlan.rmcap.b8 】 
1165: 【 wlan.rmcap.b9 】 
1166: 【 wlan.rmcap.b10 】 
1167: 【 wlan.rmcap.b11 】 
1168: 【 wlan.rmcap.b12 】 
1169: 【 wlan.rmcap.b13 】 
1170: 【 wlan.rmcap.b14 】 
1171: 【 wlan.rmcap.b15 】 
1172: 【 wlan.rmcap.b16 】 
1173: 【 wlan.rmcap.b17 】 
1174: 【 wlan.rmcap.b18to20 】 
1175: 【 wlan.rmcap.b21to23 】 
1176: 【 wlan.rmcap.b24to26 】 
1177: 【 wlan.rmcap.b27 】 
1178: 【 wlan.rmcap.b28 】 
1179: 【 wlan.rmcap.b29 】 
1180: 【 wlan.rmcap.b30 】 
1181: 【 wlan.rmcap.b31 】 
1182: 【 wlan.rmcap.b32 】 
1183: 【 wlan.rmcap.b33 】 
1184: 【 wlan.rmcap.o5 】 
1185: 【 wlan.rcpi 】 
1186: 【 wlan.multiple_bssid 】 
1187: 【 wlan.multiple_bssid.subelem.id 】 
1188: 【 wlan.multiple_bssid.subelem.len 】 
1189: 【 wlan.multiple_bssid.subelem.reserved 】 
1190: 【 wlan.multiple_bssid.subelem.nontrans_profile 】 
1191: 【 wlan.20_40_bc 】 
1192: 【 wlan.20_40_bc.information_request 】 
1193: 【 wlan.20_40_bc.forty_mhz_intolerant 】 
1194: 【 wlan.20_40_bc.20_mhz_bss_witdh_request 】 
1195: 【 wlan.20_40_bc.obss_scanning_exemption_request 】 
1196: 【 wlan.20_40_bc.obss_scanning_exemption_grant 】 
1197: 【 wlan.20_40_bc.reserved 】 
1198: 【 wlan.powercon.local 】 
1199: 【 wlan.powercap.min 】 
1200: 【 wlan.powercap.max 】 
1201: 【 wlan.tcprep.trsmt_pow 】 
1202: 【 wlan.tcprep.link_mrg 】 
1203: 【 wlan.supchan 】 
1204: 【 wlan.supchan.first 】 
1205: 【 wlan.supchan.range 】 
1206: 【 wlan.csa.channel_switch_mode 】 
1207: 【 wlan.csa.new_channel_number 】 
1208: 【 wlan.csa.channel_switch.count 】 
1209: 【 wlan.csa.mesh_channel_switch.ttl 】 
1210: 【 wlan.csa.mesh_channel_switch.flag 】 
1211: 【 wlan.csa.mesh_channel_switch.flag.txrestrict 】 
1212: 【 wlan.csa.mesh_channel_switch.flag.initiator 】 
1213: 【 wlan.csa.mesh_channel_switch.reason_code 】 
1214: 【 wlan.csa.mesh_channel_switch.pre_value 】 
1215: 【 wlan.mesh.mesh_awake_window 】 
1216: 【 wlan.measure.req.token 】 
1217: 【 wlan.measure.req.mode 】 
1218: 【 wlan.measure.req.reqmode.parallel 】 
1219: 【 wlan.measure.req.reqmode.enable 】 
1220: 【 wlan.measure.req.reqmode.request 】 
1221: 【 wlan.measure.req.reqmode.report 】 
1222: 【 wlan.measure.req.reqmode.duration_mandatory 】 
1223: 【 wlan.measure.req.reqmode.reserved 】 
1224: 【 wlan.measure.req.reqtype 】 
1225: 【 wlan.measure.req.channelnumber 】 
1226: 【 wlan.measure.req.starttime 】 
1227: 【 wlan.measure.req.channelnumber 】 
1228: 【 wlan.measure.req.operatingclass 】 
1229: 【 wlan.measure.req.randint 】 
1230: 【 wlan.measure.req.measurementmode 】 
1231: 【 wlan.measure.req.bssid 】 
1232: 【 wlan.measure.req.sub.length 】 
1233: 【 wlan.measure.req.beacon.sub.id 】 
1234: 【 wlan.measure.req.beacon.sub.ssid 】 
1235: 【 wlan.measure.req.beacon.sub.bri.repcond 】 
1236: 【 wlan.measure.req.beacon.sub.bri.threshold_offset 】 
1237: 【 wlan.measure.req.beacon.sub.bri.reporting_detail 】 
1238: 【 wlan.measure.req.beacon.sub.request 】 
1239: 【 wlan.measure.req.beacon.unknown 】 
1240: 【 wlan.measure.req.channel_load.sub.id 】 
1241: 【 wlan.measure.req.channel_load.sub.repcond 】 
1242: 【 wlan.measure.req.channel_load.sub.ref 】 
1243: 【 wlan.measure.req.noise_histogram.sub.id 】 
1244: 【 wlan.measure.reqnoise_histogram.sub.repcond 】 
1245: 【 wlan.measure.req.noise_histogram.sub.anpiref 】 
1246: 【 wlan.measure.req.frame_request_type 】 
1247: 【 wlan.measure.req.mac_address 】 
1248: 【 wlan.measure.req.peer_mac_address 】 
1249: 【 wlan.measure.req.groupid 】 
1250: 【 wlan.measure.req.unknown 】 
1251: 【 wlan.measure.req.token 】 
1252: 【 wlan.measure.req.mode 】 
1253: 【 wlan.measure.rep.repmode.late 】 
1254: 【 wlan.measure.rep.repmode.incapable 】 
1255: 【 wlan.measure.rep.repmode.refused 】 
1256: 【 wlan.measure.rep.repmode.reserved 】 
1257: 【 wlan.measure.rep.reptype 】 
1258: 【 wlan.measure.rep.channelnumber 】 
1259: 【 wlan.measure.rep.starttime 】 
1260: 【 wlan.measure.rep.channelnumber 】 
1261: 【 wlan.measure.rep.ccabusy 】 
1262: 【 wlan.measure.rep.mapfield 】 
1263: 【 wlan.measure.rep.repmode.mapfield.bss 】 
1264: 【 wlan.measure.rep.repmode.mapfie
1265: 【 wlan.measure.rep.repmode.mapfield.unidentsig 】 
1266: 【 wlan.measure.rep.repmode.mapfield.radar 】 
1267: 【 wlan.measure.rep.repmode.mapfield.unmeasured 】 
1268: 【 wlan.measure.rep.repmode.mapfield.reserved 】 
1269: 【 wlan.measure.rep.rpi.histogram_report 】 
1270: 【 wlan.measure.rep.rpi.rpi0density 】 
1271: 【 wlan.measure.rep.rpi.rpi1density 】 
1272: 【 wlan.measure.rep.rpi.rpi2density 】 
1273: 【 wlan.measure.rep.rpi.rpi3density 】 
1274: 【 wlan.measure.rep.rpi.rpi4density 】 
1275: 【 wlan.measure.rep.rpi.rpi5density 】 
1276: 【 wlan.measure.rep.rpi.rpi6density 】 
1277: 【 wlan.measure.rep.rpi.rpi7density 】 
1278: 【 wlan.measure.rep.operatingclass 】 
1279: 【 wlan.measure.rep.chanload 】 
1280: 【 wlan.measure.rep.frameinfo 】 
1281: 【 wlan.measure.rep.frameinfo.phytype 】 
1282: 【 wlan.measure.rep.frameinfo.frametype 】 
1283: 【 wlan.measure.rep.rcpi 】 
1284: 【 wlan.measure.rep.rsni 】 
1285: 【 wlan.measure.rep.bssid 】 
1286: 【 wlan.measure.rep.antid 】 
1287: 【 wlan.measure.rep.anpi 】 
1288: 【 wlan.measure.rep.ipi_density0 】 
1289: 【 wlan.measure.rep.ipi_density1 】 
1290: 【 wlan.measure.rep.ipi_density2 】 
1291: 【 wlan.measure.rep.ipi_density3 】 
1292: 【 wlan.measure.rep.ipi_density4 】 
1293: 【 wlan.measure.rep.ipi_density5 】 
1294: 【 wlan.measure.rep.ipi_density6 】 
1295: 【 wlan.measure.rep.ipi_density7 】 
1296: 【 wlan.measure.rep.ipi_density8 】 
1297: 【 wlan.measure.rep.ipi_density9 】 
1298: 【 wlan.measure.rep.ipi_density10 】 
1299: 【 wlan.measure.rep.parenttsf 】 
1300: 【 wlan.measure.req.sub.length 】 
1301: 【 wlan.measure.req.beacon.sub.id 】 
1302: 【 wlan.measure.rep.unknown 】 
1303: 【 wlan.quiet.count 】 
1304: 【 wlan.quiet.period 】 
1305: 【 wlan.quiet.duration 】 
1306: 【 wlan.quiet.offset 】 
1307: 【 wlan.dfs.owner 】 
1308: 【 wlan.dfs.recovery_interval 】 
1309: 【 wlan.dfs.channel_map 】 
1310: 【 wlan.dfs.channel_number 】 
1311: 【 wlan.dfs.map 】 
1312: 【 wlan.erp_info 】 
1313: 【 wlan.erp_info.erp_present 】 
1314: 【 wlan.erp_info.use_protection 】 
1315: 【 wlan.erp_info.barker_preamble_mode 】 
1316: 【 wlan.erp_info.reserved 】 
1317: 【 wlan.extcap 】 
1318: 【 wlan.extcap.b0 】 
1319: 【 wlan.extcap.b1 】 
1320: 【 wlan.extcap.b2 】 
1321: 【 wlan.extcap.b3 】 
1322: 【 wlan.extcap.b4 】 
1323: 【 wlan.extcap.b5 】 
1324: 【 wlan.extcap.b6 】 
1325: 【 wlan.extcap.b7 】 
1326: 【 wlan.extcap.b8 】 
1327: 【 wlan.extcap.b9 】 
1328: 【 wlan.extcap.b10 】 
1329: 【 wlan.extcap.b11 】 
1330: 【 wlan.extcap.b12 】 
1331: 【 wlan.extcap.b13 】 
1332: 【 wlan.extcap.b14 】 
1333: 【 wlan.extcap.b15 】 
1334: 【 wlan.extcap.b16 】 
1335: 【 wlan.extcap.b17 】 
1336: 【 wlan.extcap.b18 】 
1337: 【 wlan.extcap.b19 】 
1338: 【 wlan.extcap.b20 】 
1339: 【 wlan.extcap.b21 】 
1340: 【 wlan.extcap.b22 】 
1341: 【 wlan.extcap.b23 】 
1342: 【 wlan.extcap.b24 】 
1343: 【 wlan.extcap.b25 】 
1344: 【 wlan.extcap.b26 】 
1345: 【 wlan.extcap.b27 】 
1346: 【 wlan.extcap.b28 】 
1347: 【 wlan.extcap.b29 】 
1348: 【 wlan.extcap.b30 】 
1349: 【 wlan.extcap.b31 】 
1350: 【 wlan.extcap.b32 】 
1351: 【 wlan.extcap.b33 】 
1352: 【 wlan.extcap.b34 】 
1353: 【 wlan.extcap.b35 】 
1354: 【 wlan.extcap.b36 】 
1355: 【 wlan.extcap.b37 】 
1356: 【 wlan.extcap.b38 】 
1357: 【 wlan.extcap.b39 】 
1358: 【 wlan.extcap.b40 】 
1359: 【 wlan.extcap.serv_int_granularity 】 
1360: 【 wlan.extcap.b44 】 
1361: 【 wlan.extcap.b45 】 
1362: 【 wlan.extcap.b46 】 
1363: 【 wlan.extcap.b47 】 
1364: 【 wlan.extcap.b48 】 
1365: 【 wlan.extcap.b49 】 
1366: 【 wlan.extcap.b50 】 
1367: 【 wlan.extcap.b51 】 
1368: 【 wlan.extcap.b52 】 
1369: 【 wlan.extcap.b53 】 
1370: 【 wlan.extcap.b54 】 
1371: 【 wlan.extcap.b55 】 
1372: 【 wlan.extcap.b56 】 
1373: 【 wlan.extcap.b57 】 
1374: 【 wlan.extcap.b58 】 
1375: 【 wlan.extcap.b59 】 
1376: 【 wlan.extcap.b61 】 
1377: 【 wlan.extcap.b61 】 
1378: 【 wlan.extcap.b62 】 
1379: 【 wlan.extcap.b63 】 
1380: 【 wlan.extcap 】 
1381: 【 wlan.extcap.b56 】 
1382: 【 wlan.extcap.b57 】 
1383: 【 wlan.extcap.b58 】 
1384: 【 wlan.extcap.b59 】 
1385: 【 wlan.extcap.b61 】 
1386: 【 wlan.extcap.b61 】 
1387: 【 wlan.extcap.b62 】 
1388: 【 wlan.extcap.b63 】 
1389: 【 wlan.extcap.b65 】 
1390: 【 wlan.extcap.b66 】 
1391: 【 wlan.extcap.b67 】 
1392: 【 wlan.extcap.b68 】 
1393: 【 wlan.extcap.b69 】 
1394: 【 wlan.extcap.b70 】 
1395: 【 wlan.extcap.b71 】 
1396: 【 wlan.extcap.b72 】 
1397: 【 wlan.extcap.b73 】 
1398: 【 wlan.extcap.b74 】 
1399: 【 wlan.extcap.b75 】 
1400: 【 wlan.extcap.b76 】 
1401: 【 wlan.extcap.b77 】 
1402: 【 wlan.extcap.b78 】 
1403: 【 wlan.extcap.b79 】 
1404: 【 wlan.cisco.ccx1.unknown 】 
1405: 【 wlan.cisco.ccx1.name 】 
1406: 【 wlan.cisco.ccx1.clients 】 
1407: 【 wlan.cisco.ccx1.unknown2 】 
1408: 【 wlan.nreport.bssid 】 
1409: 【 wlan.nreport.bssid.info 】 
1410: 【 wlan.nreport.bssid.info.reachability 】 
1411: 【 wlan.nreport.bssid.info.security 】 
1412: 【 wlan.nreport.bssid.info.keyscope 】 
1413: 【 wlan.nreport.bssid.info.capability 】 
1414: 【 wlan.nreport.bssid.info.capability.specmngt 】 
1415: 【 wlan.nreport.bssid.info.capability.qos 】 
1416: 【 wlan.nreport.bssid.info.capability.apsd 】 
1417: 【 wlan.nreport.bssid.info.capability.radiomsnt 】 
1418: 【 wlan.nreport.bssid.info.capability.dback 】 
1419: 【 wlan.nreport.bssid.info.capability.iback 】 
1420: 【 wlan.nreport.bssid.info.mobilitydomain 】 
1421: 【 wlan.nreport.bssid.info.hthoughput 】 
1422: 【 wlan.nreport.bssid.info.vht 】 
1423: 【 wlan.nreport.bssid.info.ftm 】 
1424: 【 wlan.nreport.bssid.info.he 】 
1425: 【 wlan.nreport.bssid.info.er_bss 】 
1426: 【 wlan.nreport.bssid.info.reserved 】 
1427: 【 wlan.nreport.opeclass 】 
1428: 【 wlan.nreport.channumber 】 
1429: 【 wlan.nreport.phytype 】 
1430: 【 wlan.nreport.subelem.id 】 
1431: 【 wlan.nreport.subelem.len 】 
1432: 【 wlan.nreport.subelem.data 】 
1433: 【 wlan.nreport.subelem.bss_trn_can_pref 】 
1434: 【 wlan.nreport.subelem.bss_ter_tsf 】 
1435: 【 wlan.nreport.subelem.bss_dur 】 
1436: 【 wlan.nreport.subelem.tsf_offset 】 
1437: 【 wlan.nreport.subelem.beacon_interval 】 
1438: 【 wlan.nreport.subelem.country_code 】 
1439: 【 wlan.supopeclass.current 】 
1440: 【 wlan.supopeclass.alt 】 
1441: 【 wlan.wfa.ie.type 】 
1442: 【 wlan.wfa.ie.wpa.version 】 
1443: 【 wlan.wfa.ie.wpa.mcs 】 
1444: 【 wlan.wfa.ie.wpa.mcs.oui 】 
1445: 【 wlan.wfa.ie.wpa.mcs.type 】 
1446: 【 wlan.wfa.ie.wpa.mcs.type 】 
1447: 【 wlan.wfa.ie.wpa.ucs.count 】 
1448: 【 wlan.wfa.ie.wpa.ucs.list 】 
1449: 【 wlan.wfa.ie.wpa.ucs 】 
1450: 【 wlan.wfa.ie.wpau.cs.oui 】 
1451: 【 wlan.wfa.ie.wpa.ucs.type 】 
1452: 【 wlan.wfa.ie.wpa.ucs.type 】 
1453: 【 wlan.wfa.ie.wpa.akms.count 】 
1454: 【 wlan.wfa.ie.wpa.akms.list 】 
1455: 【 wlan.wfa.ie.wpa.akms 】 
1456: 【 wlan.wfa.ie.wpa.akms.oui 】 
1457: 【 wlan.wfa.ie.wpa.akms.type 】 
1458: 【 wlan.wfa.ie.wpa.type 】 
1459: 【 wlan.wfa.ie.wme.subtype 】 
1460: 【 wlan.wfa.ie.wme.version 】 
1461: 【 wlan.wfa.ie.wme.qos_info 】 
1462: 【 wlan.wfa.ie.wme.qos_info.sta.max_sp_length 】 
1463: 【 wlan.wfa.ie.wme.qos_info.sta.ac_be 】 
1464: 【 wlan.wfa.ie.wme.qos_info.sta.ac_bk 】 
1465: 【 wlan.wfa.ie.wme.qos_info.sta.ac_vi 】 
1466: 【 wlan.wfa.ie.wme.qos_info.sta.ac_vo 】 
1467: 【 wlan.wfa.ie.wme.qos_info.sta.reserved 】 
1468: 【 wlan.wfa.ie.wme.qos_info.ap.u_apsd 】 
1469: 【 wlan.wfa.ie.wme.qos_info.ap.parameter_set_count 】 
1470: 【 wlan.wfa.ie.wme.qos_info.ap.reserved 】 
1471: 【 wlan.wfa.ie.wme.reserved 】 
1472: 【 wlan.wfa.ie.wme.acp 】 
1473: 【 wlan.wfa.ie.wme.acp.aci_aifsn 】 
1474: 【 wlan.wfa.ie.wme.acp.aci 】 
1475: 【 wlan.wfa.ie.wme.acp.acm 】 
1476: 【 wlan.wfa.ie.wme.acp.aifsn 】 
1477: 【 wlan.wfa.ie.wme.acp.reserved 】 
1478: 【 wlan.wfa.ie.wme.acp.ecw 】 
1479: 【 wlan.wfa.ie.wme.acp.ecw.max 】 
1480: 【 wlan.wfa.ie.wme.acp.ecw.min 】 
1481: 【 wlan.wfa.ie.wme.acp.cw.max 】 
1482: 【 wlan.wfa.ie.wme.acp.cw.min 】 
1483: 【 wlan.wfa.ie.wme.acp.txop_limit 】 
1484: 【 wlan.wfa.ie.wme.tspec.ts_info 】 
1485: 【 wlan.wfa.ie.wme.tspec.ts_info.tid 】 
1486: 【 wlan.wfa.ie.wme.tspec.ts_info.dir 】 
1487: 【 wlan.wfa.ie.wme.tspec.ts_info.psb 】 
1488: 【 wlan.wfa.ie.wme.tspec.ts_info.up 】 
1489: 【 wlan.wfa.ie.wme.tspec.ts_info.reserved 】 
1490: 【 wlan.wfa.ie.wme.tspec.nor_msdu 】 
1491: 【 wlan.wfa.ie.wme.tspec.max_msdu 】 
1492: 【 wlan.wfa.ie.wme.tspec.min_srv 】 
1493: 【 wlan.wfa.ie.wme.tspec.max_srv 】 
1494: 【 wlan.wfa.ie.wme.tspec.inact_int 】 
1495: 【 wlan.wfa.ie.wme.tspec.susp_int 】 
1496: 【 wlan.wfa.ie.wme.tspec.srv_start 】 
1497: 【 wlan.wfa.ie.wme.tspec.min_data 】 
1498: 【 wlan.wfa.ie.wme.tspec.mean_data 】 
1499: 【 wlan.wfa.ie.wme.tspec.peak_data 】 
1500: 【 wlan.wfa.ie.wme.tspec.burst_size 】 
1501: 【 wlan.wfa.ie.wme.tspec.delay_bound 】 
1502: 【 wlan.wfa.ie.wme.tspec.min_phy 】 
1503: 【 wlan.wfa.ie.wme.tspec.surplus 】 
1504: 【 wlan.wfa.ie.wme.tspec.medium 】 
1505: 【 wlan.wfa.ie.owe.bssid 】 
1506: 【 wlan.wfa.ie.owe.ssid_length 】 
1507: 【 wlan.wfa.ie.owe.ssid 】 
1508: 【 wlan.wfa.ie.owe.band_info 】 
1509: 【 wlan.wfa.ie.owe.channel_info 】 
1510: 【 wlan.rsn.ie.gtk.key 】 
1511: 【 wlan.rsn.ie.gtk.keyid 】 
1512: 【 wlan.rsn.ie.gtk.tx 】 
1513: 【 wlan.rsn.ie.gtk.reserved1 】 
1514: 【 wlan.rsn.ie.gtk.reserved2 】 
1515: 【 wlan.rsn.ie.pmkid 】 
1516: 【 wlan.rsn.ie.igtk.keyid 】 
1517: 【 wlan.rsn.ie.igtk.ipn 】 
1518: 【 wlan.rsn.ie.igtk.key 】 
1519: 【 wlan.rsn.ie.unknown 】 
1520: 【 wlan.marvell.ie.type 】 
1521: 【 wlan.marvell.ie.subtype 】 
1522: 【 wlan.marvell.ie.version 】 
1523: 【 wlan.marvell.ie.proto_id 】 
1524: 【 wlan.marvell.ie.metric_id 】 
1525: 【 wlan.marvell.ie.cap 】 
1526: 【 wlan.marvell.data 】 
1527: 【 wlan.atheros.ie.type 】 
1528: 【 wlan.atheros.ie.subtype 】 
1529: 【 wlan.atheros.ie.version 】 
1530: 【 wlan.ie.atheros.capabilities.turbop 】 
1531: 【 wlan.ie.atheros.capabilities.comp 】 
1532: 【 wlan.ie.atheros.capabilities.ff 】 
1533: 【 wlan.ie.atheros.capabilities.xr 】 
1534: 【 wlan.ie.atheros.capabilities.ar 】 
1535: 【 wlan.ie.atheros.capabilities.burst 】 
1536: 【 wlan.ie.atheros.capabilities.wme 】 
1537: 【 wlan.ie.atheros.capabilities.boost 】 
1538: 【 wlan.atheros.ie.advcap.cap 】 
1539: 【 wlan.atheros.ie.advcap.defkey 】 
1540: 【 wlan.atheros.ie.xr.info 】 
1541: 【 wlan.atheros.ie.xr.base_bssid 】 
1542: 【 wlan.atheros.ie.xr.xr_bssid 】 
1543: 【 wlan.atheros.ie.xr.xr_beacon 】 
1544: 【 wlan.atheros.ie.xr.base_cap 】 
1545: 【 wlan.atheros.ie.xr.xr_cap 】 
1546: 【 wlan.atheros.data 】 
1547: 【 wlan.aironet.type 】 
1548: 【 wlan.aironet.dtpc 】 
1549: 【 wlan.aironet.dtpc_unknown 】 
1550: 【 wlan.aironet.version 】 
1551: 【 wlan.aironet.data 】 
1552: 【 wlan.qbss.version 】 
1553: 【 wlan.qbss.scount 】 
1554: 【 wlan.qbss.cu 】 
1555: 【 wlan.qbss.adc 】 
1556: 【 wlan.qbss2.cu 】 
1557: 【 wlan.qbss2.glimit 】 
1558: 【 wlan.qbss2.cal 】 
1559: 【 wlan.qbss2.scount 】 
1560: 【 wlan.aironet.qos.reserved 】 
1561: 【 wlan.aironet.qos.paramset 】 
1562: 【 wlan.aironet.qos.val 】 
1563: 【 wlan.aironet.clientmfp 】 
1564: 【 wlan.vs.nintendo.type 】 
1565: 【 wlan.vs.nintendo.length 】 
1566: 【 wlan.vs.nintendo.servicelist 】 
1567: 【 wlan.vs.nintendo.service 】 
1568: 【 wlan.vs.nintendo.consoleid 】 
1569: 【 wlan.vs.nintendo.unknown 】 
1570: 【 wlan.vs.aruba.subtype 】 
1571: 【 wlan.vs.aruba.ap_name 】 
1572: 【 wlan.vs.aruba.data 】 
1573: 【 wlan.vs.routerboard.unknown 】 
1574: 【 wlan.vs.routerboard.subitem 】 
1575: 【 wlan.vs.routerboard.subtype 】 
1576: 【 wlan.vs.routerboard.sublength 】 
1577: 【 wlan.vs.routerboard.subdata 】 
1578: 【 wlan.vs.routerboard.subtype1_prefix 】 
1579: 【 wlan.vs.routerboard.subtype1_data 】 
1580: 【 wlan.vs.meru.unknown 】 
1581: 【 wlan.vs.meru.subtype 】 
1582: 【 wlan.vs.meru.sublength 】 
1583: 【 wlan.vs.meru.subdata 】 
1584: 【 wlan.vs.extreme.subtype 】 
1585: 【 wlan.vs.extreme.subdata 】 
1586: 【 wlan.vs.extreme.unknown 】 
1587: 【 wlan.vs.extreme.ap_length 】 
1588: 【 wlan.vs.extreme.ap_name 】 
1589: 【 wlan.vs.aerohive.unknown 】 
1590: 【 wlan.vs.aerohive.hostname_length 】 
1591: 【 wlan.vs.aerohive.hostname 】 
1592: 【 wlan.vs.aerohive.data 】 
1593: 【 wlan.ts_info 】 
1594: 【 wlan.ts_info.type 】 
1595: 【 wlan.ts_info.tsid 】 
1596: 【 wlan.ts_info.dir 】 
1597: 【 wlan.ts_info.dir 】 
1598: 【 wlan.ts_info.agg 】 
1599: 【 wlan.ts_info.apsd 】 
1600: 【 wlan.ts_info.up 】 
1601: 【 wlan.ts_info.ack 】 
1602: 【 wlan.ts_info.sched 】 
1603: 【 wlan.ts_info.rsv 】 
1604: 【 wlan.tspec.nor_msdu 】 
1605: 【 wlan.tspec.max_msdu 】 
1606: 【 wlan.tspec.min_srv 】 
1607: 【 wlan.tspec.max_srv 】 
1608: 【 wlan.tspec.inact_int 】 
1609: 【 wlan.tspec.susp_int 】 
1610: 【 wlan.tspec.srv_start 】 
1611: 【 wlan.tspec.min_data 】 
1612: 【 wlan.tspec.mean_data 】 
1613: 【 wlan.tspec.peak_data 】 
1614: 【 wlan.tspec.burst_size 】 
1615: 【 wlan.tspec.delay_bound 】 
1616: 【 wlan.tspec.min_phy 】 
1617: 【 wlan.tspec.surplus 】 
1618: 【 wlan.tspec.medium 】 
1619: 【 wlan.tspec.dmg 】 
1620: 【 wlan.ts_delay 】 
1621: 【 wlan.tclas_proc.processing 】 
1622: 【 wlan.extended_supported_rates 】 
1623: 【 wlan.sched.sched_info 】 
1624: 【 wlan.sched_info.agg 】 
1625: 【 wlan.sched_info.tsid 】 
1626: 【 wlan.sched_info.dir 】 
1627: 【 wlan.sched.srv_start 】 
1628: 【 wlan.sched.srv_int 】 
1629: 【 wlan.sched.spec_int 】 
1630: 【 wlan.aruba.type 】 
1631: 【 wlan.aruba.heartbeat_sequence 】 
1632: 【 wlan.aruba.mtu_size 】 
1633: 【 wlan.htc 】 
1634: 【 wlan.htc.vht 】 
1635: 【 wlan.htc.he 】 
1636: 【 wlan.htc.he.a_control.ctrl_id 】 
1637: 【 wlan.htc.he.a_control.umrs.he_tb_ppdu_len 】 
1638: 【 wlan.htc.he.a_control.umrs.ru_allocation 】 
1639: 【 wlan.htc.he.a_control.umrs.dl_tx_power 】 
1640: 【 wlan.htc.he.a_control.umrs.ul_target_rssi 】 
1641: 【 wlan.htc.he.a_control.umrs.ul_mcs 】 
1642: 【 wlan.htc.he.a_control.umrs.reserved 】 
1643: 【 wlan.htc.he.a_control.om.rx_nss 】 
1644: 【 wlan.htc.he.a_control.om.channel_width 】 
1645: 【 wlan.htc.he.a_control.om.ul_mu_disable 】 
1646: 【 wlan.htc.he.a_control.om.tx_nsts 】 
1647: 【 wlan.htc.he.a_control.om.reserved 】 
1648: 【 wlan.htc.he.a_control.hla.unsolicited_mfb 】 
1649: 【 wlan.htc.he.a_control.hla.mrq 】 
1650: 【 wlan.htc.he.a_control.hla.NSS 】 
1651: 【 wlan.htc.he.a_control.hla.he_mcs 】 
1652: 【 wlan.htc.he.a_control.hla.dcm 】 
1653: 【 wlan.htc.he.a_control.hla.ru 】 
1654: 【 wlan.htc.he.a_control.hla.bw 】 
1655: 【 wlan.htc.he.a_control.hla.msi_ppdu_type 】 
1656: 【 wlan.htc.he.a_control.hla.tx_bf 】 
1657: 【 wlan.htc.he.a_control.hla.reserved 】 
1658: 【 wlan.htc.he.a_control.bsr.aci_bitmap 】 
1659: 【 wlan.htc.he.a_control.bsr.delta_tid 】 
1660: 【 wlan.htc.he.a_control.bsr.aci_high 】 
1661: 【 wlan.htc.he.a_control.bsr.scaling_factor 】 
1662: 【 wlan.htc.he.a_control.bsr.queue_size_high 】 
1663: 【 wlan.htc.he.a_control.bsr.queue_size_all 】 
1664: 【 wlan.htc.he.a_control.uph.ul_power_headroom 】 
1665: 【 wlan.htc.he.a_control.uph.min_transmit_power_flag 】 
1666: 【 wlan.htc.he.a_control.uph.reserved 】 
1667: 【 wlan.htc.he.a_control.bqr.avail_chan_bitmap 】 
1668: 【 wlan.htc.he.a_control.bqr.reserved 】 
1669: 【 wlan.htc.he.a_control.cci.ac_constraint 】 
1670: 【 wlan.htc.he.a_control.cci.rdg_more_ppdu 】 
1671: 【 wlan.htc.he.a_control.cci.sr_ppdu_indic 】 
1672: 【 wlan.htc.htc.a_control.cci.reserved 】 
1673: 【 wlan.trigger.he.common_info 】 
1674: 【 wlan.trigger.he.trigger_type 】 
1675: 【 wlan.trigger.he.ul_length 】 
1676: 【 wlan.trigger.he.more_tf 】 
1677: 【 wlan.trigger.he.cs_required 】 
1678: 【 wlan.trigger.he.ul_bw 】 
1679: 【 wlan.trigger.he.gi_and_ltf_type 】 
1680: 【 wlan.trigger.he.mu_mimo_ltf_mode 】 
1682: 【 wlan.trigger.he.ul_stbc 】 
1683: 【 wlan.trigger.he.ldpc_extra_symbol_segment 】 
1684: 【 wlan.trigger.he.ap_tx_power 】 
1685: 【 wlan.trigger.he.packet_extension 】 
1686: 【 wlan.trigger.he.spatial_reuse 】 
1687: 【 wlan.trigger.he.ul_he_sig_a2_reserved 】 
1688: 【 wlan.trigger.he.doppler 】 
1689: 【 wlan.trigger.he.reserved 】 
1690: 【 wlan.trigger.he.common_info.bar_ctrl 】 
1691: 【 wlan.trigger.he.common_info.bar_ctrl.ba_ack_policy 】 
1692: 【 wlan.trigger.he.common_info.bar_ctrl.ba_type 】 
1693: 【 wlan.trigger.he.common_info.bar_ctrl.reserved 】 
1694: 【 wlan.trigger.he.common_info.bar_ctrl.tid_info 】 
1695: 【 wlan.trigger.he.common_info.bar_info 】 
1697: 【 wlan.trigger.he.user_info 】 
1698: 【 wlan.trigger.he.mpdu_mu_spacing_factor 】 
1699: 【 wlan.trigger.he.tid_aggregation_limit 】 
1700: 【 wlan.trigger.he.reserved1 】 
1701: 【 wlan.trigger.he.preferred_ac 】 
1702: 【 wlan.trigger.he.basic_user_info 】 
1703: 【 wlan.trigger.he.starting_aid 】 
1704: 【 wlan.trigger.he.reserved2 】 
1705: 【 wlan.trigger.he.feedback_type 】 
1706: 【 wlan.trigger.he.reserved3 】 
1707: 【 wlan.trigger.he.target_rssi 】 
1708: 【 wlan.trigger.he.multiplexing_flag 】 
1709: 【 wlan.trigger.he.nfrp_user_info 】 
1710: 【 wlan.trigger.he.feedback_bm 】 
1711: 【 wlan.trigger.he.user_info.aid12 】 
1712: 【 wlan.trigger.he.ru_allocation_region 】 
1713: 【 wlan.trigger.he.ru_allocation 】 
1714: 【 wlan.trigger.he.coding_type 】 
1715: 【 wlan.trigger.he.mcs 】 
1716: 【 wlan.trigger.he.dcm 】 
1717: 【 wlan.trigger.he.ru_starting_spatial_stream 】 
1718: 【 wlan.trigger.he.ru_number_of_spatial_stream 】 
1719: 【 wlan.trigger.he.ru_number_of_ra_ru 】 
1720: 【 wlan.trigger.he.ru_no_more_ra_ru 】 
1721: 【 wlan.trigger.he.target_rssi 】 
1722: 【 wlan.trigger.he.user_reserved 】 
1723: 【 wlan.ext_tag.quiet_time_period.control 】 
1724: 【 wlan.ext_tag.quiet_time_period.setup.duration 】 
1725: 【 wlan.ext_tag.quiet_time_period.setup.srv_specific_identif 】 
1726: 【 wlan.ext_tag.quiet_time_period.request.dialog_token 】 
1727: 【 wlan.ext_tag.quiet_time_period.request.offset 】 
1728: 【 wlan.ext_tag.quiet_time_period.request.duration 】 
1729: 【 wlan.ext_tag.quiet_time_period.request.interval 】 
1730: 【 wlan.ext_tag.quiet_time_period.request.repetition_count 】 
1731: 【 wlan.ext_tag.quiet_time_period.request.srv_specific_identif 】
1732: 【 wlan.ext_tag.quiet_time_period.response.dialog_token 】 
1733: 【 wlan.ext_tag.quiet_time_period.response.status_code 】 
1734: 【 wlan.ext_tag.quiet_time_period.response.offset 】 
1735: 【 wlan.ext_tag.quiet_time_period.response.duration 】 
1736: 【 wlan.ext_tag.quiet_time_period.response.interval 】 
1737: 【 wlan.ext_tag.quiet_time_period.response.repetition_count 】 
1738: 【 wlan.ext_tag.quiet_time_period.response.srv_specific_identif 】
1739: 【 wlan.he_ndp.token.number 】 
1740: 【 wlan.vht_he.token.he 】 
1741: 【 wlan.he_ndp.token.reserved 】 
1742: 【 wlan.he_ndp.token 】 
1743: 【 wlan.he_ndp.sta_info 】 
1744: 【 wlan.he_ndp.sta_info.aid11 】 
1745: 【 wlan.he_ndp.sta_info.ru_start 】 
1746: 【 wlan.he_ndp.sta_info.ru_end 】 
1747: 【 wlan.he_ndp.sta_info.feedback_type_and_ng 】 
1748: 【 wlan.he_ndp.sta_info.disambiguation 】 
1749: 【 wlan.he_ndp.sta_info.codebook_size 】 
1750: 【 wlan.he_ndp.sta_info.nc 】 
1751: 【 wlan.htc.lac 】 
1752: 【 wlan.htc.lac.trq 】 
1753: 【 wlan.htc.lac.mai.aseli 】 
1754: 【 wlan.htc.lac.mai.mrq 】 
1755: 【 wlan.htc.lac.mai.msi 】 
1756: 【 wlan.htc.lac.mai.reserved 】 
1757: 【 wlan.htc.lac.mfsi 】 
1758: 【 wlan.htc.lac.asel.command 】 
1759: 【 wlan.htc.lac.asel.data 】 
1760: 【 wlan.htc.lac.mfb 】 
1761: 【 wlan.htc.cal.pos 】 
1762: 【 wlan.htc.cal.seq 】 
1763: 【 wlan.htc.reserved1 】 
1764: 【 wlan.htc.csi_steering 】 
1765: 【 wlan.htc.ndp_announcement 】 
1766: 【 wlan.htc.reserved2 】 
1767: 【 wlan.htc.mrq 】 
1768: 【 wlan.htc.msi 】 
1769: 【 wlan.htc.msi_stbc_reserved 】 
1770: 【 wlan.htc.compressed_msi 】 
1771: 【 wlan.htc.ppdu_stbc_encoded 】 
1772: 【 wlan.htc.mfsi 】 
1773: 【 wlan.htc.gid_l 】 
1774: 【 wlan.htc.mfb 】 
1775: 【 wlan.htc.num_sts 】 
1776: 【 wlan.htc.vht_mcs 】 
1777: 【 wlan.htc.bw 】 
1778: 【 wlan.htc.snr 】 
1779: 【 wlan.htc.reserved3 】 
1780: 【 wlan.htc.gid_h 】 
1781: 【 wlan.htc.coding_type 】 
1782: 【 wlan.htc.fb_tx_type 】 
1783: 【 wlan.htc.unsolicited_mfb 】 
1784: 【 wlan.htc.ac_constraint 】 
1785: 【 wlan.htc.rdg_more_ppdu 】 
1786: 【 wlan.mobility_domain.mdid 】 
1787: 【 wlan.mobility_domain.ft_capab 】 
1790: 【 wlan.ft.mic_control 】 
1791: 【 wlan.ft.element_count 】 
1792: 【 wlan.ft.mic 】 
1793: 【 wlan.ft.anonce 】 
1794: 【 wlan.ft.snonce 】 
1795: 【 wlan.ft.subelem.id 】 
1796: 【 wlan.ft.subelem.len 】 
1797: 【 wlan.ft.subelem.data 】 
1798: 【 wlan.ft.subelem.r1kh_id 】 
1799: 【 wlan.ft.subelem.gtk.key_info 】 
1800: 【 wlan.ft.subelem.gtk.key_id 】 
1801: 【 wlan.ft.subelem.gtk.key_length 】 
1802: 【 wlan.ft.subelem.gtk.rsc 】 
1803: 【 wlan.ft.subelem.gtk.key 】 
1804: 【 wlan.ft.subelem.r0kh_id 】 
1805: 【 wlan.ft.subelem.igtk.key_id 】 
1806: 【 wlan.ft.subelem.igtk.ipn 】 
1807: 【 wlan.ft.subelem.igtk.key_length 】 
1808: 【 wlan.ft.subelem.igtk.key 】 
1809: 【 wlan.ric_data.id 】 
1810: 【 wlan.ric_data.desc_cnt 】 
1811: 【 wlan.ric_data.status_code 】 
1812: 【 wlan.obss.spd 】 
1813: 【 wlan.obss.sad 】 
1814: 【 wlan.obss.cwtsi 】 
1815: 【 wlan.obss.sptpc 】 
1816: 【 wlan.obss.satpc 】 
1817: 【 wlan.obss.wctdf 】 
1818: 【 wlan.obss.sat 】 
1819: 【 wlan.osen.gdcs.oui 】 
1820: 【 wlan.osen.gdcs.type 】 
1821: 【 wlan.osen.pwcs.count 】 
1822: 【 wlan.osen.pwcs.oui 】 
1823: 【 wlan.osen.pwcs.type 】 
1824: 【 wlan.osen.akms.count 】 
1825: 【 wlan.osen.akms.oui 】 
1826: 【 wlan.osen.akms.type 】 
1827: 【 wlan.osen.rsn.capabilities.preauth 】 
1828: 【 wlan.osen.rsn.capabilities.no_pairwise 】 
1831: 【 wlan.osen.gmcs.oui 】 
1832: 【 wlan.osen.gmcs.type 】 
1833: 【 wlan.osen.rsn.capabilities.mfpr 】 
1834: 【 wlan.osen.rsn.capabilities.mfpc 】 
1835: 【 wlan.osen.rsn.capabilities.jmr 】 
1836: 【 wlan.osen.rsn.capabilities.peerkey 】 
1837: 【 wlan.osen.rsn.cabailities.flags 】 
1838: 【 wlan.osen.rsn.capabilities.spp_a_msdu_cap 】 
1839: 【 wlan.osen.rsn.capabilities.spp_a_msdu_req 】 
1840: 【 wlan.osen.rsn.capabilities.pbac 】 
1841: 【 wlan.osn.rsn.extended_key_id_iaf 】 
1842: 【 wlan.osen.rsn.capabilities.reserved 】 
1843: 【 wlan.osen.pmkid.count 】 
1844: 【 wlan.osen.pmkid.bytes 】 
1845: 【 wlan.ric_desc.rsrc_type 】 
1846: 【 wlan.ric_desc.var_params 】 
1847: 【 wlan.mmie.keyid 】 
1848: 【 wlan.mmie.ipn 】 
1849: 【 wlan.mmie.mic 】 
1850: 【 wlan.wapi.version 】 
1851: 【 wlan.wapi.akm_suite.count 】 
1852: 【 wlan.wapi.akm_suite.oui 】 
1853: 【 wlan.wapi.akm_suite.type 】 
1854: 【 wlan.wapi.unicast_cipher.suite.count 】 
1855: 【 wlan.wapi.unicast_cipher.suite.oui 】 
1856: 【 wlan.wapi.unicast_cipher.suite.type 】 
1857: 【 wlan.wapi.multicast_cipher.suite.oui 】 
1858: 【 wlan.wapi.multicast_cipher.suite.type 】 
1859: 【 wlan.wapi.capab 】 
1860: 【 wlan.wapi.capab.preauth 】 
1861: 【 wlan.wapi.capab.rsvd 】 
1862: 【 wlan.wapi.bkid.count 】 
1863: 【 wlan.wapi.bkid 】 
1864: 【 wlan.bss_max_idle.period 】 
1866: 【 wlan.tfs_request.id 】 
1869: 【 wlan.tfs_request.subelem.id 】 
1870: 【 wlan.tfs_request.subelem.len 】 
1871: 【 wlan.tfs_request.subelem 】 
1872: 【 wlan.tfs_response.subelem.id 】 
1873: 【 wlan.tfs_response.subelem.len 】 
1874: 【 wlan.tfs_response.subelem 】 
1875: 【 wlan.tfs_response.status 】 
1876: 【 wlan.tfs_response.tfs_id 】 
1877: 【 wlan.wnm_sleep_mode.action_type 】 
1879: 【 wlan.wnm_sleep_mode.interval 】 
1880: 【 wlan.wnm_subelt.id 】 
1881: 【 wlan.wnm_subelt.len 】 
1882: 【 wlan.time_adv.timing_capab 】 
1883: 【 wlan.time_adv.time_value 】 
1884: 【 wlan.time_adv.time_value.year 】 
1885: 【 wlan.time_adv.time_value.month 】 
1886: 【 wlan.time_adv.time_value.month 】 
1887: 【 wlan.time_adv.time_value.hours 】 
1888: 【 wlan.time_adv.time_value.minutes 】 
1889: 【 wlan.time_adv.time_value.seconds 】 
1890: 【 wlan.time_adv.time_value.milliseconds 】 
1891: 【 wlan.time_adv.time_value.reserved 】 
1892: 【 wlan.time_adv.time_error 】 
1893: 【 wlan.time_adv.time_update_counter 】 
1894: 【 wlan.time_zone 】 
1895: 【 wlan.interworking.access_network_type 】 
1896: 【 wlan.interworking.internet 】 
1897: 【 wlan.interworking.asra 】 
1898: 【 wlan.interworking.esr 】 
1899: 【 wlan.interworking.uesa 】 
1900: 【 wlan.interworking.hessid 】 
1901: 【 wlan.qos_map_set.dscp_exception 】 
1902: 【 wlan.qos_map_set.dscp_value 】 
1903: 【 wlan.qos_map_set.up 】 
1904: 【 wlan.qos_map_set.range 】 
1905: 【 wlan.qos_map_set.dscp_low_value 】 
1906: 【 wlan.qos_map_set.dscp_high_value 】 
1907: 【 wlan.adv_proto.resp_len_limit 】 
1908: 【 wlan.adv_proto.pame_bi 】 
1909: 【 wlan.adv_proto.id 】 
1910: 【 wlan.adv_proto.vs_len 】 
1911: 【 wlan.adv_proto.vs_info 】 
1912: 【 wlan.roaming_consortium.num_anqp_oi 】 
1913: 【 wlan.roaming_consortium.oi1_len 】 
1914: 【 wlan.roaming_consortium.oi2_len 】 
1915: 【 wlan.roaming_consortium.oi1 】 
1916: 【 wlan.roaming_consortium.oi2 】 
1917: 【 wlan.roaming_consortium.oi3 】 
1918: 【 wlan.timeout_int.type 】 
1919: 【 wlan.timeout_int.value 】 
1920: 【 wlan.link_id.bssid 】 
1921: 【 wlan.link_id.init_sta 】 
1922: 【 wlan.link_id.resp_sta 】 
1923: 【 wlan.wakeup_schedule.offset 】 
1924: 【 wlan.wakeup_schedule.interval 】 
1925: 【 wlan.wakeup_schedule.awake_window_slots 】 
1926: 【 wlan.wakeup_schedule.max_awake_dur 】 
1927: 【 wlan.wakeup_schedule.idle_count 】 
1928: 【 wlan.channel_switch_timing.switch_time 】 
1929: 【 wlan.channel_switch_timing.switch_timeout 】 
1930: 【 wlan.pti_control.tid 】 
1931: 【 wlan.pti_control.sequence_control 】 
1932: 【 wlan.pu_buffer_status.ac_bk 】 
1933: 【 wlan.pu_buffer_status.ac_be 】 
1934: 【 wlan.pu_buffer_status.ac_vi 】 
1935: 【 wlan.pu_buffer_status.ac_vo 】 
1936: 【 wlan.mysterious_olpc_stuff 】 
1937: 【 wlan.ext_tag.estimated_service_params 】 
1938: 【 wlan.ext_tag.estimated_service_params.access_category 】 
1939: 【 wlan.ext_tag.estimated_service_params.reserved 】 
1940: 【 wlan.ext_tag.estimated_service_params.data_format 】 
1941: 【 wlan.ext_tag.estimated_service_params.ba_window_size 】 
1942: 【 wlan.ext_tag.estimated_service_params.air_time_frac 】 
1943: 【 wlan.ext_tag.estimated_service_params.data_ppdu_dur_target 】 
1944: 【 wlan.ext_tag.future_channel_guidance.new_chan_num 】 
1945: 【 wlan.ext_tag.future_channel_guidance.extra_bytes 】 
1946: 【 wlan.fils_indication.info.nr_pk 】 
1947: 【 wlan.fils_indication.info.nr_realm 】 
1948: 【 wlan.fils_indication.info.ip_config 】 
1949: 【 wlan.fils_indication.info.cache_id_included 】 
1950: 【 wlan.fils_indication.info.hessid_included 】 
1951: 【 wlan.fils_indication.info.ska_without_pfs 】 
1952: 【 wlan.fils_indication.info.ska_with_pfs 】 
1953: 【 wlan.fils_indication.info.pka 】 
1954: 【 wlan.fils_indication.info.reserved 】 
1955: 【 wlan.fils_indication.cache_identifier 】 
1956: 【 wlan.fils_indication.hessid 】 
1957: 【 wlan.fils_indication.realms 】 
1958: 【 wlan.fils_indication.realms.identifier 】 
1959: 【 wlan.fils_indication.public_keys 】 
1960: 【 wlan.fils_indication.public_keys.key_type 】 
1961: 【 wlan.fils_indication.public_keys.length 】 
1962: 【 wlan.fils_indication.public_keys.indicator 】 
1963: 【 wlan.ext_tag 】 
1964: 【 wlan.ext_tag.number 】 
1965: 【 wlan.ext_tag.length 】 
1966: 【 wlan.ext_tag.fils.session 】 
1967: 【 wlan.ext_tag.fils.encrypted_data 】 
1968: 【 wlan.ext_tag.fils.wrapped_data 】 
1969: 【 wlan.ext_tag.fils.nonce 】 
1970: 【 wlan.ext_tag.he_mac_caps 】 
1971: 【 wlan.ext_tag.he_mac_cap.htc_he_support 】 
1972: 【 wlan.ext_tag.he_mac_cap.twt_req_support 】 
1973: 【 wlan.ext_tag.he_mac_cap.twt_rsp_support 】 
1974: 【 wlan.ext_tag.he_mac_cap.fragmentation_support 】 
1975: 【 wlan.ext_tag.he_mac_cap.max_frag_msdus 】 
1976: 【 wlan.ext_tag.he_mac_cap.min_frag_size 】 
1978: 【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】 
1979: 【 wlan.ext_tag.he_mac_cap.he_link_adaptation_support 】 
1980: 【 wlan.ext_tag.he_mac_cap.all_ack_support 】 
1981: 【 wlan.ext_tag.he_mac_cap.Trs_support 】 
1982: 【 wlan.ext_tag.he_mac_cap.bsr_support 】 
1983: 【 wlan.ext_tag.he_mac_cap.broadcast_twt_support 】 
1984: 【 wlan.ext_tag.he_mac_cap.32_bit_ba_bitmap_support 】 
1985: 【 wlan.ext_tag.he_mac_cap.mu_cascading_support 】 
1986: 【 wlan.ext_tag.he_mac_cap.ack_enabled_agg_support 】 
1987: 【 wlan.ext_tag.he_mac_cap.reserved_b24 】 
1988: 【 wlan.ext_tag.he_mac_cap.om_control_support 】 
1989: 【 wlan.ext_tag.he_mac_cap.ofdma_ra_support"                                
1990: 【 wlan.ext_tag.he_mac_cap.a_msdu_frag_support 】 
1991: 【 wlan.ext_tag.he_mac_cap.flexible_twt_sched_support 】 
1992: 【 wlan.ext_tag.he_mac_cap.rx_ctl_frm_multibss 】 
1993: 【 wlan.ext_tag.he_mac_cap.bsrp_bqrp_a_mpdu_agg 】 
1994: 【 wlan.ext_tag.he_mac_cap.qtp_support 】 
1995: 【 wlan.ext_tag.he_mac_cap.bqr_support 】 
1996: 【 wlan.ext_tag.he_mac_cap.sr_responder 】 
1997: 【 wlan.ext_tag.he_mac_cap.ndp_feedback_report_support 】 
1998: 【 wlan.ext_tag.he_mac_cap.ops_support 】 
1999: 【 wlan.ext_tag.he_mac_cap.a_msdu_in_a_mpdu_support 】 
2000: 【 wlan.ext_tag.he_mac_cap.multi_tid_agg_support 】 
2001: 【 wlan.ext_tag.he_mac_cap.subchannel_selective_xmit_support 】 
2002: 【 wlan.ext_tag.he_mac_cap.ul_2_996_tone_ru_support 】 
2004: 【 wlan.ext_tag.he_mac_cap.reserved_bit_39 】 
2005: 【 wlan.ext_tag.he_mac_cap.reserved_bits_5_7 】 
2006: 【 wlan.ext_tag.he_mac_cap.reserved_bits_8_9 】 
2007: 【 wlan.ext_tag.he_mac_cap.reserved_bits_15_16 】 
2008: 【 wlan.ext_tag.he_mac_cap.reserved_bit_18 】 
2009: 【 wlan.ext_tag.he_mac_cap.reserved_bit_19 】 
2010: 【 wlan.ext_tag.he_mac_cap.reserved_bit_25 】 
2011: 【 wlan.ext_tag.he_phy_cap.reserved_b0 】 
2012: 【 wlan.ext_tag.he_phy_cap.fbyte.reserved_b0 】 
2013: 【 wlan.ext_tag.he_phy_cap.fbytes 】 
2014: 【 wlan.ext_tag.he_phy_cap.chan_width_set.40mhz_in_2_4ghz 】 
2015: 【 wlan.ext_tag.he_phy_cap.chan_width_set.40_80_in_5ghz 】 
2016: 【 wlan.ext_tag.he_phy_cap.chan_width_set.160_in_5ghz 】 
2017: 【 wlan.ext_tag.he_phy_cap.chan_width_set.160_80_80_in_5ghz 】 
2018: 【 wlan.ext_tag.he_phy_cap.chan_width_set.242_tone_in_5ghz 】 
2019: 【 wlan.ext_tag.he_phy_cap.chan_width_set.reserved 】 
2020: 【 wlan.ext_tag.he_phy_cap.bits_8_to_23 】 
2021: 【 wlan.ext_tag.he_phy_cap.nbytes.punc_preamble_rx 】 
2022: 【 wlan.ext_tag.he_phy_cap.nbytes.device_class 】 
2023: 【 wlan.ext_tag.he_phy_cap.nbytes.ldpc_coding_in_payload 】 
2025: 【 wlan.ext_tag.he_phy_cap.mbytes.midamble_rx_max_nsts 】 
2027: 【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_lt_80mhz 】 
2028: 【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_lt_80mhz 】 
2029: 【 wlan.ext_tag.he_phy_cap.nbytes.doppler_tx 】 
2030: 【 wlan.ext_tag.he_phy_cap.nbytes.doppler_rx 】 
2031: 【 wlan.ext_tag.he_phy_cap.nbytes.full_bw_ul_mu_mimo 】 
2032: 【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_ul_mu_mimo 】 
2033: 【 wlan.ext_tag.he_phy_cap.bits_24_to_39 】 
2034: 【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_tx 】 
2035: 【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】 
2036: 【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_const_rx 】 
2037: 【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_nss_tx 】 
2038: 【 wlan.ext_tag.he_phy_cap.nbytes.rx_he_mu_ppdu 】 
2039: 【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformer 】 
2040: 【 wlan.ext_tag.he_phy_cap.nbytes.su_beamformee 】 
2041: 【 wlan.ext_tag.he_phy_cap.nbytes.mu_beamformer 】 
2042: 【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_lte_80mhz 】 
2043: 【 wlan.ext_tag.he_phy_cap.nbytes.beamformee_sts_gt_80mhz 】 
2044: 【 wlan.ext_tag.he_phy_cap.bits_40_to_55 】 
2045: 【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_lt  】 
2046: 【 wlan.ext_tag.he_phy_cap.nbytes.no_sounding_dims_gt_  】 
2047: 【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_su_fb 】 
2048: 【 wlan.ext_tag.he_phy_cap.nbytes.ng_eq_16_mu_fb 】 
2049: 【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_su_fb 】 
2050: 【 wlan.ext_tag.he_phy_cap.nbytes.codebook_size_mu_fb 】 
2051: 【 wlan.ext_tag.he_phy_cap.nbytes.trig_su_bf_fb 】 
2052: 【 wlan.ext_tag.he_phy_cap.nbytes.trig_mu_bf_fb 】 
2053: 【 wlan.ext_tag.he_phy_cap.nbytes.trig_cqi_fb 】 
2054: 【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_er 】 
2055: 【 wlan.ext_tag.he_phy_cap.nbytes.partial_bw_dl_mu_mimo 】 
2056: 【 wlan.ext_tag.he_phy_cap.nbytes.ppe_thres_present 】 
2057: 【 wlan.ext_tag.he_phy_cap.bits_56_to_71 】 
2058: 【 wlan.ext_tag.he_phy_cap.nbytes.srp_based_sr_sup 】 
2059: 【 wlan.ext_tag.he_phy_cap.nbytes.pwr_bst_factor_ar_sup 】 
2060: 【 wlan.ext_tag.he_phy_cap.nbytes.he_su_ppdu_e
2061: 【 wlan.ext_tag.he_phy_cap.nbytes.max_nc 】 
2062: 【 wlan.ext_tag.he_phy_cap.nbytes.stbc_tx_gt_80_mhz 】 
2063: 【 wlan.ext_tag.he_phy_cap.nbytes.stbc_rx_gt_80_mhz 】 
2064: 【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_4xxx_gi 
2065: 【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_40_in_2_4
2066: 【 wlan.ext_tag.he_phy_cap.nbytes.20_mhz_in_160_80p80_ppdu 】
2067: 【 wlan.ext_tag.he_phy_cap.nbytes.80_mhz_in_160_80p80_ppdu 】
2068: 【 wlan.ext_tag.he_phy_cap.nbytes.he_er_su_ppdu_1xxx_gi 
2069: 【 wlan.ext_tag.he_phy_cap.nbytes.midamble_rx_2x_1x_he_ltf 】 
2070: 【 wlan.ext_tag.he_phy_cap.bits_72_to_87 】 
2071: 【 wlan.ext_tag.he_phy_cap.nbytes.dcm_max_bw 】 
2072: 【 wlan.ext_tag.he_phy_cap.nbyes.longer_than_16_
2073: 【 wlan.ext_tag.he_phy_cap.nbytes.non_triggered_feedback 】 
2074: 【 wlan.ext_tag.he_phy_cap.nbytes.tx_1024_qam_support_lt_24 】 
2075: 【 wlan.ext_tag.he_phy_cap.nbytes.rx_1024_qam_support_lt_24  】 
2076: 【 wlan.ext_tag.he_phy_cap.nbytes.rx_full  】 
2077: 【 wlan.ext_tag.he_phy_cap.nbytes.rx_   】 
2078: 【 wlan.ext_tag.he_phy_cap.nbytes.reserved_b78_b87 】 
2079: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss 】 
2080: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_1_ss 】 
2081: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_2_ss 】 
2082: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_3_ss 】 
2083: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_4_ss 】 
2084: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_5_ss 】 
2085: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_6_ss 】 
2086: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_7_ss 】 
2087: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_rx_8_ss 】 
2088: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_1_ss 】 
2089: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_2_ss 】 
2090: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_3_ss 】 
2091: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_4_ss 】 
2092: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_5_ss 】 
2093: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_6_ss 】 
2094: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_7_ss 】 
2095: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80_tx_8_ss 】 
2096: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_1_ss 】 
2097: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_2_ss 】 
2098: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_3_ss 】 
2099: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_4_ss 】 
2100: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_5_ss 】 
2101: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_6_ss 】 
2102: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_7_ss 】 
2103: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_rx_8_ss 】 
2104: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_1_ss 】 
2105: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_2_ss 】 
2106: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_3_ss 】 
2107: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_4_ss 】 
2108: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_5_ss 】 
2109: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_6_ss 】                   
2110: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_7_ss 】                   
2111: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_80p80_tx_8_ss 】                   
2112: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_1_ss 】                     
2113: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_2_ss 】                     
2114: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_3_ss 】
2115: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_4_ss 】
2116: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_5_ss 】
2117: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_6_ss 】
2118: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_7_ss 】
2119: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_rx_8_ss 】
2120: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_1_ss 】
2121: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_2_ss 】
2122: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_3_ss 】
2123: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_4_ss 】
2124: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_5_ss 】
2125: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_6_ss 】
2126: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_7_ss 】
2127: 【 wlan.ext_tag.he_mcs_map.max_he_mcs_160_tx_8_ss 】
2128: 【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_lte_80 】 
2129: 【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_lte_80 】 
2130: 【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_160 】 
2131: 【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_160 】 
2132: 【 wlan.ext_tag.he_mcs_map.rx_he_mcs_map_80_80 】 
2133: 【 wlan.ext_tag.he_mcs_map.tx_he_mcs_map_80_80 】 
2134: 【 wlan.ext_tag.he_ppe_thresholds.nss 】 
2135: 【 wlan.ext_tag.he_ppe_thresholds.ru_index_bitmask 】 
2137: 【 wlan.ext_tag.he_ppe_thresholds.ppet8 】     
2138: 【 wlan.ext_tag.he_operation.params 】 
2139: 【 wlan.ext_tag.he_operation.default_pe_duration 】 
2140: 【 wlan.ext_tag.he_operation.twt_required 】 
2141: 【 wlan.ext_tag.he_operation.txop_duration_rts_thre  】 
2142: 【 wlan.ext_tag.he_operation.vht_op_info_pres  】 
2143: 【 wlan.ext_tag.he_operation.co_located_bss 】 
2144: 【 wlan.ext_tag.he_operation.er_su_disable 】 
2145: 【 wlan.ext_tag.he_operation.reserved_b17_b32 】 
2146: 【 wlan.ext_tag.bss_color_information 】 
2147: 【 wlan.ext_tag.bss_color_information.bss_color 】 
2148: 【 wlan.ext_tag.bss_color_information.partial_bss_color 】 
2149: 【 wlan.ext_tag.bss_color_information.bss_color_disabled 】 
2150: 【 wlan.ext_tag.he_operation.basic_he_mcs_and_nss 】 
2151: 【 wlan.ext_tag.he_operation.max_he_mcs_for_1_ss 】 
2152: 【 wlan.ext_tag.he_operation.max_he_mcs_for_2_ss 】 
2153: 【 wlan.ext_tag.he_operation.max_he_mcs_for_3_ss 】 
2154: 【 wlan.ext_tag.he_operation.max_he_mcs_for_4_ss 】 
2155: 【 wlan.ext_tag.he_operation.max_he_mcs_for_5_ss 】 
2156: 【 wlan.ext_tag.he_operation.max_he_mcs_for_6_ss 】 
2157: 【 wlan.ext_tag.he_operation.max_he_mcs_for_7_ss 】 
2158: 【 wlan.ext_tag.he_operation.max_he_mcs_for_8_ss 】 
2159: 【 wlan.ext_tag.he_operation.vht_op_info.channel_width 】 
2160: 【 wlan.ext_tag.he_operatoon.vht_op_info.cha 】 
2161: 【 wlan.ext_tag.he_operatoon.vht_op_info.cha  】 
2162: 【 wlan.ext_tag.he_operation.max_colocated_bssid  】 
2164: 【 wlan.ext_tag.mu_edca_parameter_set.aifsn 】 
2165: 【 wlan.ext_tag.mu_edca_parameter_set.acm 】 
2166: 【 wlan.ext_tag.mu_edca_parameter_set.aci 】 
2167: 【 wlan.ext_tag.mu_edca_parameter_set.reserved 】 
2170: 【 wlan.ext_tag.spatial_reuse.sr_control 】 
2171: 【 wlan.ext_tag.spatial_reuse.sr_control.srp_dis 】 
2172: 【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_ 】 
2173: 【 wlan.ext_tag.spatial_reuse.sr_control.non_srg_ofs_pre 】 
2174: 【 wlan.ext_tag.spatial_reuse.sr_control.srg_info_prese  】 
2175: 【 wlan.ext_tag.spatial_reuse.sr_control.       】 
2176: 【 wlan.ext_tag.spatial_reuse.sr_control.reserved 】 
2177: 【 wlan.ext_tag.spatial_reuse.non_srg_obss_pd_max_of  】 
2178: 【 wlan.ext_tag.spatial_reuse.srg_obss_pd_min_offset 】 
2179: 【 wlan.ext_tag.spatial_reuse.srg_obss_pd_max_offset 】 
2180: 【 wlan.ext_tag.spatial_reuse.srg_bss_color_bitmap 】 
2181: 【 wlan.ext_tag.spatial_reuse.srg_partial_bssid_bitmap
2182: 【 wlan.ext_tag.ndp_feedback.res_req 】
2183: 【 wlan.ext_tag.bss_color_change.new_color_info 】 
2184: 【 wlan.ext_tag.bss_color_change.new_bss_color 】 
2185: 【 wlan.ext_tag.bss_color_change.new_color_reserved 】 
2186: 【 wlan.ext_tag.bss_color_change.color_switch_countd
2187: 【 wlan.ext_tag.ess_report.ess_info.planned_ess 】 
2188: 【 wlan.ext_tag.ess_report.ess_info.edge_of_ess 】 
2189: 【 wlan.ext_tag.ess_report.ess_info.field 】 
2190: 【 wlan.ext_tag.ess_report.ess_info.thresh  】 
2191: 【 wlan.ext_tag.uora_parameter_set.f  】 
2192: 【 wlan.ext_tag.uora_parameter_set.eocwmin 】 
2193: 【 wlan.ext_tag.uora_parameter_set.eocwmax 】 
2194: 【 wlan.ext_tag.uora_parameter_set.reserved 】 
2195: 【 wlan.s1g.action 】 
2196: 【 wlan.twt.bcast_flow 】 
2197: 【 wlan.twt.individual_flow 】 
2198: 【 wlan.twt.individual_flow_id 】 
2199: 【 wlan.twt.bcast_flow_id 】 
2200: 【 wlan.twt.neg_type 】 
2201: 【 wlan.twt.control_field 】 
2202: 【 wlan.twt.ndp_paging_indicator 】 
2203: 【 wlan.twt.resp_pm 】 
2204: 【 wlan.twt.neg_type 】 
2205: 【 wlan.twt.control_field_reserved 】 
2206: 【 wlan.twt.request_type 】 
2207: 【 wlan.twt.requester 】 
2208: 【 wlan.twt.setup_cmd 】 
2209: 【 wlan.twt.trigger 】 
2210: 【 wlan.twt.implicit 】 
2211: 【 wlan.twt.flow_type 】 
2212: 【 wlan.twt.flow_id 】 
2213: 【 wlan.twt.wake_interval_exp 】 
2214: 【 wlan.twt.prot 】 
2215: 【 wlan.twt.target_wake_time 】 
2216: 【 wlan.twt.nom_min_twt_wake_duration 】 
2217: 【 wlan.twt.wake_interval_mantissa 】 
2218: 【 wlan.twt.channel 】 
2219: 【 wlan.ext_tag.owe_dh_parameter.group 】 
2220: 【 wlan.ext_tag.owe_dh_parameter.public_key 】 
2221: 【 wlan_aggregate.a_mdsu.subframe 】 
2222: 【 wlan_aggregate.a_mdsu.length 】 
2223: 【 wlan.tag.number.unexpected_ie 】      
2224: 【 wlan.tag.length.bad 】      
2225: 【 wlan.fixed.anqp.capability.invalid 】      
2226: 【 wlan.fixed.anqp.venue.length.invalid 】      
2227: 【 wlan.fixed.anqp.roaming_consortium.oi_len.invalid 】      
2228: 【 wlan.fixed.anqp.nai_realm_list.field_len.invalid 】      
2229: 【 wlan.fixed.naqp_nai_realm_list.eap_method_len.invalid 】      
2230: 【 wlan.hs20.anqp.ofn.length.invalid 】      
2231: 【 wlan.hs20.anqp.nai_hrq.length.invalid 】      
2232: 【 wlan.fixed.anqp.info_length.invalid 】      
2233: 【 wlan.fixed.query_length_invalid 】      
2234: 【 wlan.fixed.query_request_length.invalid 】      
2235: 【 wlan.fixed.query_response_length.invalid 】      
2236: 【 wlan.wnm_sleep_mode.no_key_data 】      
2237: 【 wlan.tdls_setup_response_malformed 】      
2238: 【 wlan.tdls_setup_confirm_malformed 】      
2239: 【 wlan.wfa.ie.wme.qos_info.bad_ftype 】    
2240: 【 wlan.qos_info.bad_ftype 】    
2241: 【 wlan.qos_info.bad_aifsn 】      
2242: 【 wlan.rsn.pcs.count.invalid 】      
2243: 【 wlan.rsn.akms.count.invalid 】      
2244: 【 wlan.rsn.pmkid.count.invalid 】      
2245: 【 wlan.vht.tpe.pwr_info.count.invalid 】      
2246: 【 wlan.fc.retry.expert 】  PI_SEQUENCE,  
2247: 【 wlan.measure.req.unknown.expert 】    
2248: 【 wlan.measure.req.beacon.unknown.expert 】    
2249: 【 wlan.measure.req.unknown.expert 】    
2250: 【 wlan.tag.data.undecoded 】    
2251: 【 wlan.dmg_subtype.bad 】      
2252: 【 wlan.vht.action.undecoded 】    
2253: 【 wlan.peering.unexpected 】      
2254: 【 wlan.fcs.bad_checksum 】      
2255: 【 wlan.rsn.akms.mismatched 】   
2256: 【 wlan.vs.routerboard.unexpected_len 】   
2257: 【 wlan.twt.tear_down_bad_neg_type 】   
2258: 【 wlan.twt.setup_unsup_neg_type 】    
2259: 【 wlan.twt.setup_bad_command 】   


```



