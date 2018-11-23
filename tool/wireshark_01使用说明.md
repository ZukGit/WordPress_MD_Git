
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


# 1_ExpressionInputBox
<img src="//../zimage/tool/wireshark/wireshark_14.jpg">


# 2_ExpressionDialog

<img src="//../zimage/tool/wireshark/wireshark_15.jpg">

# 3_addTagButton

<img src="//../zimage/tool/wireshark/wireshark_16.jpg">

