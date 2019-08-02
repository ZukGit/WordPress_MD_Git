# A

## AAA
AAA： Authentication Authorization Accounting
中文:   **认证 (Authentication)、授权 (Authorization)和计费 (Accounting)**



## ARP
ARP：  Address Resolution Protocol
中文:   **地址解析协议   把网络层的IP地址 -> 数据链路层的Mac地址的协议**

## AKM
AKM： Authentication and key management
中文:   **身份验证与秘钥管理**

## ACS
ACS： Access Control Server
中文:   **访问控制服务器 使用802.1X协议实现LAN或WLAN用户的接入认证**


## ASK
ASK: Amplitude-shift keying :  幅移键控
数字信号转为模拟信号的基本方式之一:  ASK幅移键控   PSK相移键控   FSK频移键控

# B

## Beacon
Beacon：  信标

## BPF
BPF:  Berkeley Packet Filter
中文:   **伯克利包过滤语法(wireshark中的过滤表达式) 例如: dst host 192.xx.xx.xx && tcp port 80**


## BPSK
BPSK: Binary Phase Shift Keying :  二进制相移键控   用两个相位分别表示0，1
把模拟信号 转为  数字信号的方式之一

QPSK: Quadrature Phase Shift Keying  :  正交相移键控 用四个相位分别表示: 00,01,10,11
8PSK:  八相位相移键控   :     8位相移键控   用八个相位分别表示  
000 ， 001， 010 ， 011， 100 ， 101 ， 110 ， 111


## BSS
BSS：  Basic Service Set
中文:   **基本服务集**



## BSSID
BSSID:  Basic Service Set Identifier 
中文:   **WAP无线接入点的 基础服务设备识别码**

## bandwidth
bandwidth： 频带带宽 


# C

## CAC 
CAC:   Channel Availability Check
中文:   **DFC动态频率选择中的 信道可用性检查**

## CSMS/CA
CSMS/CA:  Carrier Sense Multiple Access / Collision Avoidance
中文:   **载波侦听多路访问/冲突避免**


## CSMS/CD
CSMS/CD:  Carrier Sense Multiple Access / Collision Detection
中文:   **载波侦听多路访问/冲突检测**

## CTS
CTS:   Clear To Send
中文:   **准予发送数据包**


## coverage holes
 coverage holes ：  覆盖盲区

## CCK
CCK:  Complementary Code Keying   补码键控调制方式

## CP
```
CP:  cyclic prefix   :   循环前缀

持续的输出波形  使得波形在任何位置 都能正交  而不产生干扰
```


# D

## DNS
DNS：  Domain Name System
中文:   **域名系统, 把 www.xx.com  -> 182.141.12.241 的协议系统**


## DHCP
DHCP：  Dynamic  Host Configuration Protocol
中文:   **动态主机配置协议(在局域网中向DHCP服务器获取IP地址的协议)**

## DSSS
DSSS:  direct-sequence sprerad-spectrum
中文:   **直接序列扩频**


## DFS
DFS:  Dynamic Frequency  Selection
```
中文:   **动态频率选择**

简介: WIFI一开始工作在2.4G 但由于升级到5G后，对5G频带的使用各个地区不一样(美国可使用，欧洲该频段是军事雷达频率所以民用禁止)，所以在欧洲销售的产品需要支持 DFS 和 TPC 传输功率控制。
DFS作用是:  主动探测军方使用的频率 并主动选择另一个频率，以避免干扰军方通讯，【DFS和TPC在欧洲这两个功能是强制性的】，其检测的信道范围为 

5250MHz ----  5350MHz
5470MHz ----  5725MHz     这两个范围区间需要进行作用DFS

```

## DCF
DCF： Distributed coordination function
中文:   **分布式协调功能**

## DIFS
DIFS:  distributed interframe space
中文:   **分布式帧间间隔 **

# E

## EAP

```
 可扩展认证协议(Extensible authentication protocol,EAP)

```

## ESS
ESS:  Extended Service Set
中文:   **扩展服务集(把多个BSS串联为逻辑上彼此相连的组)**

## ERO
ERO： Eroupean Radiocommunicayions Office
中文:   **欧洲无线电信局**

## EIFS
EIFS： Extended innerframe Space
中文:   **扩展帧间间隔**

## exponential backoff
exponential backoff ： 指数退避

# F

## FCC
FCC:  Federal Communications Commission 
中文:   **美国联邦通信委员会**


## FCS
FCS：  Frame Check Sequence
中文:   **帧校验序列**

## FHSS
FHSS: frequency-hopping sprerad-spectrum
中文:   **跳频扩频**

## FSK频移键控
FSK: Frequence-shift keying :   FSK频移键控
数字信号转为模拟信号的基本方式之一:  ASK幅移键控   PSK相移键控   FSK频移键控

## FFT
在OFDM 正交频分复用中用来对信道的cons函数进行操作  以从传输的总信道中提取自身需要的信号
FFT :  快速傅里叶变换
IFFT : 快速傅里叶逆变换



## knobs and dials
knobs and dials ：  可供调教之处


## 符号时间
符号时间:  在编码格式下 发送一个码元 symbol所需要的时间
64QAM中  符号时间 = (保护时间) + (积分时间) = 800ns + 3200ns = 4000ns =4us
一秒钟发送的码元 =  1s /  0.000004 = 250000 symbole 
48个子载波 在 1 s 发出的码元  =  250000 * 48 = 12000000 symbole   一千两百万码元

12000000 symbole   *  每个码元所包含的比特数量  6 = 72000000 比特数据
编码率  75%
实际数据 = 72000000 比特数据  *  编码率  75% = 54000000 bit /s   = 54Mb/s   = 6.75MB/s





### 空操作

```
在 802.11ac 中新的特性  为了实现 MU-MIMO  多用户 多输入多输出 
当AP往 user1 发送数据时   AP会向user1 发送一个 强波束  同时 AP会在 user2 user3 方向上降低波束能量
这被称为空操作
这样:   每个user 都能获得所需数据的强信号

```



# G

## GPL
GPL:   GUN Public Licence
中文:   **GPL开源许可证**


## 格雷码

```
调制数字信号中 把相邻两个编码之间只差一个数字不同的编码就格雷码

00   01   10    11 不是格雷码
00   01   11    10 是格雷码

```


## GI
```
GI: Guard Interval    保护间隔

在OFDM中接收端从信道接受到一个符号  等待一个时间间隔   只要这个间隔大于最大的信号时延    那么因为信道环境造成的 ISI 符号间干扰就可以消除

```

# H

## HotSpot
HotSpot：  hot  spot
中文:   ** 热点**


## HS2.0 && Hotspot2.0 

```
Hotspot 2.0(HS 2.0)由Wi-Fi联盟和无线带宽联盟开发，旨在当支持HS2的移动设备进入HS2覆盖区域，实现蜂窝移动网络和Wi-Fi网络之间的无缝流量切换
无须用户选择Wi-Fi SIID和输入认证密钥，以提供更好的带宽和QoS,减少蜂窝移动网络的流量负载。
Hotspot 2.0采用IEEE 802.11u协议，实现STA和AP之间的通信，支持自动发现网络、授权和分配访问权限

支持802.11u的移动设备可以在本地存储操作员帐号信息和网络首选项策略。
STA使用访问网络查询协议(ANQP)发送请求，寻找可用操作员、漫游伙伴和热点EAP认证的信息。
AP通过一般广告服务(GAS)在移动设备和运营商网络服务器之间实现广告协议帧的2层网络传输
然后，AP会将服务器响应返回设备，如果匹配，它就自动验证和连接用户。

```


## HCF
HCF:  hybrid coordination function
中文:   ** 混合协调功能**



## HEW

HEW: High-Efficiency Wireless :  就是 802.11ax 新一代WIFI技术协议 高保真无线





# I

## ICMP
ICMP：  Internet Control Message Protocol
中文:   **互联网控制消息协议(ping命令使用了该协议)**


## ICI


```
ICI:  Inter-Channel Interference  :  信道间干扰  载波间干扰  频率干扰
OFDM 信道有多个不同频率但相互正交的载波, 理论上各个载波不存在 信道间干扰  但由于信道环境的影响还是会有ICI 信道干扰存在

```



## ISI

```
ISI:   Inter-Symbol Interference     码间串扰  符号间干扰 

对于一个自在波上的symbol符号，有多条路径到达终端,当不同路径之间延迟差距过大,导致后来发送
数据副本混叠先前收到的数据  就会发生符号间干扰



```

## IEEE
IEEE:   Institute of Electrical and Electronics Engineers 
中文:   **美国电子和电器工程师协会**

## IES
```
IES ：  InfomationElements      在 Beacon  ,  Probe Response 都包含这种信息元素   具有 AP的安全配置信息( 加密算法  安全配置 协议等等 )
WPA IES：    WPA 信息元素     InfomationElements  
```

## ISN
ISN:  Initial  Sequence Number
中文:   **初始序列号( TCP连接 )**

## IBSS
IBSS:  Independent   Basic Service Set
中文:   **独立基本服务集**

## ITU 
ITU：  International  Telecomunications Union
中文:   **国际电信联盟**

## IAPP
IAPP:  inner-access point protocol
中文:   **接入点内部协议**

## IQ 调制/解调正交 正交调制
IQ:  in-phase(同相位)  quadrature（正交）
解释:  数据分为两路,分别进行载波调制,两路载波相互正交。接收端能独立获取各路数据 两路数据不相互干扰



## IFFT
在OFDM 正交频分复用中用来对信道的cons函数进行操作  以从传输的总信道中提取自身需要的信号
FFT :  快速傅里叶变换
IFFT : 快速傅里叶逆变换
OFDM 中 使用  IFFT 来实现 多载波映射叠加过程


# J
# K
# L
## line-of-sight
line-of-sight:  视距
中文:   **视距**

##  LLC
LLC: Logical Link  Control 
中文:   **逻辑链路控制**
# M

## MAC
MAC:   Media Access Control
中文:   **媒体访问控制**

## MITM
MITM:   man-in-the-middle
中文:   **中间人攻击(ARP)**

## MTU
MTU: Maximun Transmission Unit
中文:   **数据链路层最大传输单元  以太网默认1500字节，IP头部占用20字节 所以MTU携带数据最多1480字节**

## MIMO
MIMO：  Multiple-input/Multiple-output
中文:   **多路输入多路输出**

## MSDU
MSDU:  Mac Serivce Data Unit
中文:   **Mac服务数据单元**


## MLME
```

MLME: Mac Layer Manager Entry  MAC层管理实体  可以提供调用Mac层管理功能的服务接口 同时还负责管理 MAC-PAN信息库(MAC-PIB)

```

## MCS
MCS:  Modulation and Coding Scheme :      调制与编码策略
MCS制作了了一张表  索引1....100  每行四速率与编码方式

https://blog.csdn.net/zhangfan406/article/details/80758624

```
802.11n 的物理速率由于依赖的元素过多
1.调制方式
2.编码率
3.空间流数量 MIMO
4.是否 40MHz 绑定
.....
这些因素组合在一起将导致非常多的物理速率可供选择 为此 802.11n提出 MCS的概念
MCS可以理解为 影响速率因素的完整组合   每种组合都用整数唯一标识
```



## MRC

MRC:   Maximal-Radio Combining  最大比合并法   MIMO调制中同时收到来自不同路径的同一个信号数据 对各个输入信号加权合并  已得到更好更稳定的信号数据

# N

## NAV
NAV:  network allocation vector
中文:   **虚拟载波监听由 网络分配矢量 提供**


## NOP
NOP: non-occupancy
中文:   **不可占用 EVENT_DFS_NOP_FINISHED - Notify that non-occupancy period is over**
# O

## OFDM

```
OFDM: orthogonal frequency division multiplexing
中文:   **正交频分复用   调制方式**
1. 一个余弦函数cosA  和它自身 的乘积 cosA * cosA在 基波周期内的积分 [积分: cosA * cosA][基波周期] > 0
2. 一个余弦函数cosA  和另一个不同的余弦函数的乘积  cosA * cosB 在基波周期的积分为0
[积分: cosA * cosB][基波周期] = 0

利用以上特点就可以实现  数据正交传输  用同一个波形 传递不同数据 而这些数据又不相互影响
```



## OFDMA
OFDMA:   orthogonal frequency division multiple Access 
中文:   **正交频分多址    多地址接入技术方式**


## OWE
OWE : Opportunistic Wireless encryption 
中文:  机会无限加密( 为每个用户提供单独的加密方式) 
# P

## PSK 
Phase Shift Keying : 相位键控
一般标注在单位圆中 (编码方式)   每个编码的模为1


## PAE
```
PAE (Port Access Entity)
端口PAE（Port Access Entity，端口访问实体）

原文：https://blog.csdn.net/lee244868149/article/details/52132350 
端口PAE为802.1x系统中，在一个给定的设备端口上执行算法和协议操作的实体对象。
设备服务端PAE利用认证服务器对需要接入局域网的客户端执行认证，并根据认证结果相应地控制受控端口的授权/非授权状态。
客户端PAE负责响应设备端的认证请求，向设备服务端提交用户的认证信息。
客户端PAE也可以主动向设备端发送认证请求和下线请求。



设备端为客户端提供接入局域网的端口，这个端口被划分为两个逻辑端口：受控端口和非受控端口。

  非受控端口始终处于双向连通状态，主要用来传递EAPOL协议帧，保证客户端始终能够发出或接收认证报文。

  受控端口在授权状态下处于双向连通状态，用于传递业务报文；在非授权状态下禁止从客户端接收任何报文。

  受控端口和非受控端口是同一端口的两个部分；任何到达该端口的帧，在受控端口与非受控端口上均可见。


在非授权状态下，受控端口可以被设置成单向受控和双向受控。
实行双向受控时，禁止帧的发送和接收；
实行单向受控时，禁止从客户端接收帧，但允许向客户端发送帧。


```

## Passpoint
```
CERTIFIED Passpoint(认证控制点)计划实际上是创建一个Wi-Fi热点数据库，允许用户访问参加该计划的那个地区的任何热点。
而且，参加这个计划的任何热点都允许用户接入，不必输入登录或者计费信息，
因为这个计划支持蜂窝网络目前用于批准用户在基站之间无缝转接的SIM卡身份识别技术。
这还意味着运营商可以相互之间建立Wi-Fi漫游协议


WiFi联盟推出了一项名为Passpoint的技术，旨在让WiFi热点更像蜂窝网络和LTE网络一样工作

简单比方:
比如我们正用手机在家中通过WiFi上网，这时候我们起身出门，
手机的WiFi连接瞬间从家里的无线网络切换到中国移动的WiFi网络上，
走着走着，手机的WiFi连接又不知不觉的蹭上了联通的WiFi热点
截止目前为止，北美仅有T-Mobile一家运营商公开承诺支持Passpoint认证



```

## PLMN
```
PLMN（Public Land Mobile Network，公共陆地移动网络）
PLMN = MCC + MNC
由政府或它所批准的经营者，为公众提供陆地移动通信业务目的而建立和经营的网络。该网路必须与公众交换电话网（PSTN）互连，形成整个地区或国家规模的通信网。
例如中国移动的PLMN为46000，中国联通的PLMN为46001


```


## PHY
PHY：  physical layer
中文:   ** 物理层 **

## PDU
PDU：  Protocol Data Unit
中文:   **协议数据单元( 协议头 + 协议体(数据) )**

## PMD
PMD:   Physical Medium Dependent
中文:   ** 物理媒体相关 **

## PLCP
PLCP：  Physical Layer Convergencce Procedure
中文:   ** 物理层会聚过程 **

## preamble
preamble :  前导码

## PSK
PSK： Pre-shared Key
中文:   ** 预共享秘钥 wifi密码 **

## PSM
PSM： Power Save Mode
中文:   ** WLAN节能模式 **

## PCF
PCF: point coordination function
中文:   ** 点协调功能 **

## PIFS
PIFS： point coordination function innerframe Space
中文:   ** 优先级帧间间隔 **

# Q

## QoS
QoS:  quality-of-service
中文:   ** MAC 服务质量扩展功能 **

## QAM
QAM:  Quardrature Amplitude Modulation 正交振幅调制  
模拟信号转为数字信号的方式之一 ， 一般标注在矩形的星座图中


# R

## RF 
RF：  Radio Frequence
中文:   **射频**


## RTS
RTS:   Request To Send
中文:   **请求发送数据库**

## RTO
RTO:   Retransmission timeout
中文:   **重传超时时间**


## RTT
RTO:   Round-trip Time
中文:   **从发送数据包到接受ACK确认之间的时间---往返时间**

## RSSI
RSSI :    Recevived Signal  Strength Indication
中文:   **射频信号强度**

```
https://source.android.google.cn/devices/tech/connect/wifi-rtt


```

## RSN

RSN: Robust Security Network
中文:   **强健安全网络 802.11x 安全交互协议 针对WEP加密机制的各种缺陷做了多方面的改进**


## Roaming
Roaming ：  漫游

# S

## STA
STA:  Station
中文:   **工作站,配备无线网卡的设备**

## SIFS
SIFS： Short interframe Space
中文:   **短帧间间隔**


## signal-to-noise-ratio
signal-to-noise-ratio : 信噪比


## signal-quality
signal-quality : 信号质量

## Symbol码元

```
在IQ相位正交调频中使用编码方式中 PSK  QAM 中最小编码单位
每种编码方式的最小编码单元所能表示的 bit数据不一样 
调制方式     每个码元所代表比特        星座图(码元的个数)（码元所代表的比特数bit）
 BPSK            1                 0,1
 QPSK            2                 2^2=4 (0,0)(0,1)(1,1)(1,0)
8PSK             3                 2^3=8 (0,0,0)...(1,1,1)   【圆】
16QAM            4                 2^4=16 (0,0,0,0)...(1,1,1,1)  【矩】
64QAM            6                 2^6=64 (0,0,0,0,0,0)...(1,1,1,1,1,1)
256QAM           8                 2^8=256 (0,0,0,0,0,0,0,0).(1,1,1,1,1,1,1,1)
1024QAM         10                 2^10=1024 (0,0,0,0,0,0,0,0,0,0)...(10个1)(1,1,1,1,1,1,1,1)

```



## SNR

SNR: Signal-to-Noise Ratio  信噪比

即放大器的输出信号的功率，与同时输出的噪声功率的比值，常常用分贝数表示

设备的信噪比越高表明它产生的杂音越少 说明混在信号里的噪声越小，声音回放的音质量越高





## SISO

SISO:  Single Input Single Output   单次输入单次输出



## 数据分集

数据分集: 数据分集就是把一个数据重复发送到信道多次 以确保接收端能收到 提高可靠性



### 空间多路复用

空间多路复用:  在同一个时刻  M 个 天线上 发送 M 个 symbol 码元  可以提升速率



### 时间分集

时间分集:  在 SISO 单次输入单次输出 系统中 可通过时间实现分集 在不同的时刻发送同一个symbol码元



### 空间分集

空间分集： 在MIMO 多路输入多路输出 系统中  分集 可以在不同的天线上实现， 同时在不同的天线上发送同一种符号Symbol



### 分集增益

分集增益： 分集增益是用来衡量空间分集的标准，即 从 发送端到接收端 有多少条 可辨识别的 通路 路径

MIMO中   2x2 =4   3x3=9     2x1=2 (站在发送端考虑)



### 自由度

自由度:  自由度是衡量  复用 的标准 , 即 一个系统每时刻最多可以接收多少个不同的 码元 symbol数据 把资源用来发送不同的数据 可以提升 传输速率(站在接收端考虑)


# T

## TCP
TCP：  Transmission Control Protocol
中文:   **传输控制协议 面向连接 包丢失需要重传  网络效率较低**

## TPC
TPC：  Transmit Power Control
中文:   **传输功率控制**

## TKIP
TKIP ： Temporary Key Integrity Protocol
中文:   **临时秘钥完整性协议**

## threshold
threshold：  阈值

## TIM
TIM：  Traffic Indication Map
中文:   **传输指示映射,在beacon帧中用于确定beacon中信息是哪一个睡眠状态STA的**

## TDLS
TDLS: Tunneled Direct Link Setup
中文: **通道直接链路建立 项标准允许两款设备通过WiFi网络进行点对点直连，与早起提倡的WiFi Direct相似，不过功能则更加完善 **


## TKR SM
```
The Key Receiver SM
TKR SM包含两个状态。
第一个是NO_KEY_RECEIVE状态
当rxKey（boolean型变量，当Supplicant收到EAPOL Key帧后，该值为TRUE）变为TRUE时，TKR进入KEY_RECEIVE状态。
TKR在KEY_RECEIVE状态时需要调用processKey函数处理EAPOL Key消息


```

# U

## UDP   
UDP： User Datagram Protocol
中文:   **用户数据报协议  面向无连接 包丢失不重传 不保证完全发送正确 网络效率高**

## unicast data
 unicast data :  单播数据

# V
## vendor prefix
vendor prefix：  Mac地址的厂商前缀 





## *VHT* *Sounding* protocol

*VHT* *Sounding* protocol ：  Very High Throughput Sounding protocol   : 高速信道信号探测协议 802.11ac中使用 用来检测束波    　　

NDP（Neighbor Discovery Protocol）：IPV6邻居发现协议

NDPA（Null Data Packet Announcement）：空数据包声明

NDP（Neighbor Discovery Protocol）：IPV6邻居发现协议

Beamforming/Beamformer/Beamformee：波束成形技术/发送端/接收端



```
1. Beamformer AP 发送端  首先发送  VHT NDP Announcement 高速邻居发现通知空数据包
2. AP 发送端 为每个  接收端 Beamformee 发送 NDP 
3. 每个接受到 NDP的 STA 使用在 NDP中保存的 前导符 测量 AP 与自身STA的 RF 信道 并压缩信道
4. 第一个 Beamformee STA 立即使用  VHT Compressed Beamforming【 VHT 压缩成形帧包】来响应AP 
5. 其他的STA 在接受到AP 发送的 轮训 Beamforming Report Poll后 响应  VHT Compressed Beamforming【 VHT 压缩成形帧包】来响应AP 

```





# W

## WAPI
WAPI:  WLAN Authentication and Privacy Infrastructure
中文:   **无线局域网鉴别与保密基础结构**

```
WAPI 是中国自主知识产权的WLAN安全标准协议 , 而 WLAN安全协议的国际标准是 802.11i标准
两者都是关于无线接入安全方面的协议，都旨在保证无线网络接入的安全以及无线数据通信的安全。
两者的区别在于802.11i只定义了对于无线客户端的安全保证，而WAPI定义了针对无线客户端和无线接入点两者的鉴权，对应的数据加密算法也不相同，属于私有。

```

## wiphy

```
wiphy是一个结构体  用于把硬件注册到 cfg80211 , 那么就需要 定义一系列的硬件功能描述的结构体
每个设备的基础性结构体是wiphy , 设备连接到系统时，都要使用。
每个wiphy有0个，1个或者许多个虚拟接口相关联

cfg80211是Linux 802.11用于管理配置的一套API，它是用户和驱动之间的桥梁，替代了WEXT，提供和802.11相关的功能。
用户空间通过nl80211用来配置80211设备，即cfg80122与用户空间的交互窗口。 
cfg80211使用的是netlink方式。


```

<img src="../zimage/wireless/wifi/03_englishcode/wiphy.jpg"/>


##  WAP 
WAP :  Wireless Access Point
中文:   **无线接入点**

## WPA
WPA :  Wi-Fi Protected Access
中文:   **无线上网保护接入**

## WEP
WEP :   Wired  Equivalent Privacy
中文:   **有效等效解密**

## WLAN
WLAN:  Wireless Local Area Network
中文:   **无线局域网**

## WMM
WMM：  Wi-Fi Multi-Media
中文:   **WiFi多媒体**

## WNM
WNM : Wireless Network Manager
中文:   **无线网络管理**



## WDS
WDS:  wireless distribution system
中文:   **无线分布式系统 ( 无线桥接器)**

# X
# Y
# Z


# 重点句子

```
1. 无线网络并没有物理连接接口，因此用户身份确认认证便成为网络设计的中心
2.MAC是一组规则，这些规则决定如何访问媒介 和 传输数据 ，  至于传送和接收数据的细节交给PHY物理层负责

3.RTS帧  CTS帧  数据帧 以及最后的 ACK帧均被视为院子操作的额一部分
4. 错误恢复  错误检测 是发送端的责任  
```