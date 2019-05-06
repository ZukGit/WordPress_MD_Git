# A
# B
# C
# D
# E
# F
# G
# H
# I

## ICMP && ICMPv6(Internet Control Managemet Protocol Version 6)
```
ICMPv6(Internet Control Managemet Protocol Version 6)
互联网控制信息协议版本六

历史:
ICMPv6为了与IPv6配套使用而开发的互联网控制信息协议。
与IPv4一样，IPv6也需要使用ICMP，旧版本的ICMP不能满足IPv6全部要求，因此开发了新版本的ICMP，称为ICMPv6。

基本功能:
互联网控制信息协议是IP协议的一个重要组成部分
ICMPv6向源节点报告关于目的地址传输IPv6包的错误和信息，具有【差错报告】、【网络诊断】、【邻节点发现】和【多播】实现等功能。
在IPv6中，ICMPv6实现IPv4中【ICMP】、【ARP】和【IGMP】的功能

使用场景:
1.通告网络错误。比如，某台主机或整个网络由于某些故障不可达。如果有指向某个端口号的TCP或UDP包没有指明接受端，这也由ICMP报告。
2.通告网络拥塞。当路由器缓存太多包，由于传输速度无法达到它们的接收速度，将会生成“ ICMP 源结束”信息。
  对于发送者，这些信息将会导致传输速度降低。当然，更多的 ICMP 源结束信息的生成也将引起更多的网络拥塞，所以使用起来较为保守。
3.协助解决故障。ICMP支持Echo功能，即在两个主机间一个往返路径上发送一个包。
  Ping是一种基于这种特性的通用网络管理工具，它将传输一系列的包，测量平均往返次数并计算丢失百分比。
4.通告超时。如果一个IP包的TTL降低到零，路由器就会丢弃此包，这时会生成一个 ICMP 包通告这一事实。
   TraceRoute是一个工具，它通过发送小TTL值的包及监视ICMP超时通告可以显示网络路由。

```

### NDP(基于ICMP协议实现的协议)(Neighbor Discovery Protocol)

#### RS( Router Solicitation Message )路由器请求
```
PC主机启动后，通过RS消息向路由器发出请求，期望路由器立即发送RA消息响应。

```

#### RA(Router Advertisement)路由器通告
```
路由器对于 主机发出的RS的响应  
路由器利用此消息，周期性的通告其存在及各种链路参数，或者用于响应 Router Solicitation

```


#### NS(Neighbor Solicitation Message )相邻结点请求
```
由节点发送，以便确定邻居的链路层地址(组播)，或者通过缓存的链路层地址，验证邻居仍然可达(单播)，也可用于 Duplicate Address Detect。
等同于 IPv4 中的 ARP 协议。

```

#### NA(Neighbor Advertisement) 相邻结点通告
```
 响应 Neighbor Solicitation 消息。此外，节点也可单独发送该消息，用于通知节点链路层地址的改变


```

#### Redirected 重定向
```
 由路由器使用，通知主机有到目的地的更好的下一跳

```
# J
# K
# L
# M
# N
# O
# P
# Q
# R
# S
# T
# U
# V
# W
# X
# Y
# Z