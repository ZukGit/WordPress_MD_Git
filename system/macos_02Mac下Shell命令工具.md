
# A

## Alias命令
```

alias suz="/usr/local/sh_expect_work/suz.sh"
alias cmt="/usr/local/sh_expect_work/cmt.sh"
alias cdsh='cd /usr/local/sh_expect_work/'
alias cdgit='cd  /Users/aaa/Desktop/code_place/zzj_git/'

```

## aalib库-aview库

```
详细说明： https://blog.csdn.net/exbob/article/details/7321903
aalib简介：aalib这是一个功能库  目的是将计算机上的一切都用ASCII字符来表现，包括图形和视频

aview简介：aview程序可以将pbm、pgm或pnm图片用ASCII字符显示。但是不支持JPEG图片，所以它提供了一个shell脚本asciiview，先调用convert将JPEG图片转换为pgm图片，然后再用aview显示。
convert 功能依赖于包 imagemagick  所以还要下载该包
安装方法( Mac )： 
brew install  aalib
brew install  aview
brew install  imagemagick


命令： asciiview ./1.png
```
<img src="//../zimage/system/macos/02_shellcommand/1.png" height="50%" width="50%"><img src="//../zimage/system/macos/02_shellcommand/1_arscii.png" height="50%" width="50%">

## ACK命令
```
简介：ack诞生的目的就是要取代grep，从作者开发的初衷以及它官网的名字，另外它还有一个“可以替代99%grep的工作”这个口号。ACK专门为
程序员进行搜索进行了优化

特点：
1.速度非常快,因为它只搜索有意义的东西。
2.更友好的搜索，忽略那些不是你源码的东西。
3.为源代码搜索而设计，用更少的击键完成任务。
4.非常轻便，移植性好。
5.免费且开源

与grep对比：
grep常用操作
grep -r 'hello_world' # 简单用法
grep '^hello_world' . # 简单正则
ls -l | grep .py      # 管道用法

grep的一些参数：
-c(统记 搜索结果)/ -i(忽略大小)/ -h(不显示名称)/  -C(显示行数)
-l(只显文件名)/ -n(加行号)/ -v(显示不匹配)  / -s (no-messages不显示错误信息)
-r (表示在当前目录和子目录，循环搜索)


ack功能划分:
1. Searching代码搜索
2. Search output搜索结果处理
3. File presentation文件展示
4. File finding文件查找
5. File inclusion/exclusion文件过滤


ack 参数用法：默认是递归
-i         忽略大小写
-v        显示不匹配行
-w        强制匹配整个单词
-l         打印匹配的文件名
-L        打印不匹配的文件名
-m       在每个文件中最多匹配多少行就停止搜索
-c        显示匹配的总行数

ack -i -w eat   // 在当前目录递归搜索单词”eat”,不匹配类似于”feature”或”eating”的字符串
ack -i  --php  protected   //忽略大小写搜索php文件中包含protected的文件
ack -f hello.py     // 查找多有匹配的文件   hello.py

ack --python hello     //查找所有包含hello字符串的python文件 

ack --java zukgit     // 检查所有包含 zukgit字符串的文件

```

## ADB 命令

```

adb shell setprop vidc.debug.level 7 


adb shell tcpdump -i any -p -s 0 -w /data/op_pcap.pcap
```


## Afl-fuzz库命令
```
简介： https://blog.csdn.net/youkawa/article/details/45696317
AFL(American Fuzzy Lop)是由Google安全工程师Micha  Zalewski开发的一款开源fuzzing测试工具，可以高效地对【二进制程序】进行【fuzzing模糊测试】，挖掘可能存在的内存安全漏洞，如栈溢出、堆溢出、UAF、double free等。由于需要在相关代码处插桩，因此AFL主要用于对开源软件进行测试。当然配合QEMU等工具，也可对闭源二进制代码进行fuzzing，但执行效率会受到影响。

AFL主要由3部分组成：
编译器wrapper，该部分用于编译目标代码，如afl-gcc, afl-clang等
fuzzer: afl-fuzz，即为用于fuzzing的主要工具
其他工具：如afl-cmin, afl-tmin等


Fuzzing技术被证明是当前鉴别软件安全问题方面最强大测试技术。

```



## aircrack-ng 破解WIFI的WEP/WPA/WPA2密码的主流工具
```
Aircrack-ng是一套套件包含的工具可用于捕获数据包、握手验证。可用来进行暴力破解和字典攻击。
Aircrack-ng 攻击 主要是拿到握手包，用字典破解握手包

作用领域：
监控：数据包捕获并将数据导出到文本文件以供第三方工具进一步处理。
攻击：通过数据包注入重播攻击，解除身份验证，假接入点和其他攻击点。
测试：检查WiFi卡和驱动程序功能（捕获和注入）。
破解：WEP和WPA PSK（WPA 1和2） 


使用：
// 1.使用该命令 查看周围wifi网络的 SSID热点名 BSSID热点唯一Mac标识            
//   RSSI信号强度  CHANNEL信道  HT CC SECURITY加密方式 (auth/unicast/group)
【配置airport 链接到 bin 使得 shell中能找到】
sudo ln -s /System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport /usr/sbin/airport

airport -s     


//2. en0表示当前的网卡名 ifconfig命令查看 6表示当前探测的网络工作的信道channel
// 该命令开始执行抓取在该信道传输的WIFI帧   Ctrl + C 结束该过程
//会生成 /tmp/airportsniffXXX.cap(或者/private/tmp/airportsniffXXX.cap) 捕获帧文件
airport en0 sniff 6 


// 3.复制该文件到 zidian.txt(里面包含穷举的密码)统一文件夹下 改名为 1.cap 并在shell界面cd到该目录  执行查找握手帧的过程
aircrack-ng -w zidian.txt 1.cap

//4.在出现的帧编号中 查找一个描述为 WPA( 1 handshake ) 的帧 , 并输入该帧对应的 帧编号开始解析
//等待字典.txt中所有的Item进行破解，如果字典.txt存在密码就会破解成功，若不存在则需要更换破解字典.txt文件 
```
**检测网络信息**
<img src="//../zimage/system/macos/02_shellcommand/airport.png">

**选择捕获帧中显示（1 handshake）的帧**
<img src="//../zimage/system/macos/02_shellcommand/wpa1.png">
<img src="//../zimage/system/macos/02_shellcommand/select_num.png">

**字典中未包含枚举密码解析失败**
<img src="//../zimage/system/macos/02_shellcommand/not_find.png">

**字典中包含密码解析成功**
<img src="//../zimage/system/macos/02_shellcommand/success.png">


## antigen 工具
```
antigen简介:
Zsh 是一个 Linux 下强大的 shell, 由于大多数 Linux 产品安装，以及默认使用 bash shell, 但是丝毫不影响极客们对 zsh 的热衷, 几乎每一款 Linux 产品都包含有 zsh，通常可以用 apt-get、urpmi 或 yum 等包管理器进行安装。虽然说 Zsh 是开箱即用，但是为了更好用，还是需要一些定制化的配置。之前一直使用 oh-my-zsh , oh-my-zsh 把主题、插件等都是一起管理的，但是很多其他的主题和插件，且都是由不同的作者开发的，这样的话，管理起来就比较麻烦。 antigen 就是针对此问题，应运而生。antigen是zsh的包管理器，让我们以类似apt-get的方式来安装zsh功能，非常方便。


安装命令(Mac)： brew install antigen

```

```
antigen 的命令(用法):


antigen  apply       -- 该命令会应用所有之前所做的更改。使得加载的插件生效 Load all bundle completions

antigen bundle  xxxx插件名称|url地址     -- 该命令用于下载和安装插件如果插件已安装那么就加载该插件 Install and load the given plugin
antigen bundle git
antigen bundle heroku
antigen bundle pip
antigen bundle lein
antigen bundle command-not-found
antigen bundle zsh-users/zsh-syntax-highlighting  #语法高亮功能
antigen bundle zsh-users/zsh-autosuggestions   #代码提示功能
antigen bundle zsh-users/zsh-completions   #自动补全功能

antigen  cache-gen   -- 把当前加载的插件生成缓存，可以加快之后的加载过程 Generate cache
antigen  cleanup     -- 清除所有插件多余的版本Clean up the clones of repos which are not used by any bundle
antigen  help        -- Show this message 帮助手册
antigen  init  ~/.antigenrc    -- 该命令可以指定一个antigen配置文件  使用该配置文件来初始化 antigen


antigen  list        -- 查看当前插件集合List out the currently loaded bundles
【
[zukgit的MacPro-/Users/aaa]➜  ~ antigen  list
robbyrussell/oh-my-zsh ~ lib @ master
robbyrussell/oh-my-zsh ~ plugins/git @ master
robbyrussell/oh-my-zsh ~ plugins/heroku @ master
robbyrussell/oh-my-zsh ~ plugins/pip @ master
robbyrussell/oh-my-zsh ~ plugins/lein @ master
robbyrussell/oh-my-zsh ~ plugins/command-not-found @ master
zsh-users/zsh-syntax-highlighting @ master
zsh-users/zsh-autosuggestions @ master
zsh-users/zsh-completions @ master
robbyrussell/oh-my-zsh ~ themes/Fishy2.zsh-theme @ master
】

antigen purge xxxx插件  --清理插件多余的版本Remove a cloned bundle from filesystem
antigen purge   robbyrussell/oh-my-zsh             
antigen purge   zsh-users/zsh-completions        
antigen purge   zsh-users/zsh-autosuggestions      
antigen purge   zsh-users/zsh-syntax-highlighting


antigen  reset       -- Clears cache 清理缓存

antigen  restore     -- Restore the bundles state as specified in the snapshot
antigen  revert      -- 还原？  不懂

antigen selfupdate  -- Update antigen itself 更新antigen自身
antigen snapshot    -- Create a snapshot of all the active clones
antigen  theme  awesomepanda      -- Switch the prompt theme
antigen  theme  xxxxxx     // 切换主题
antigen  update      --  执行更新插件  Update all bundles
antigen  use  oh-my-zsh
antigen  use  prezto   -- Load any (supported) zsh pre-packaged framework
antigen  version     -- Display Antigen version

```


```
cat ~/.zshrc 【zsh的配置文件如下】  antigen是下.zshrc脚本执行命令的方式实现对zsh的管理 
-------------------------------------------------------------


export ZSH=$HOME/.oh-my-zsh
ZSH_THEME="robbyrussell"

plugins=(git)

source $ZSH/oh-my-zsh.sh
source $HOME/.bash_profile

###############客制化#################
##设置 sh 脚本执行的命令别名
alias suz="/usr/local/sh_expect_work/suz.sh"
alias cmt="/usr/local/sh_expect_work/cmt.sh"
alias cdsh='cd /usr/local/sh_expect_work/'
alias cdgit='cd  /Users/aaa/Desktop/code_place/zzj_git/'
alias cls="clear"

## 把root的bin路径加入到 PATH变量中
export PATH="/usr/local/sbin:$PATH"


## 执行antigen 用于初始化 antigen的环境
source /usr/local/share/antigen/antigen.zsh

## 通过 antigen 加载 oh-my-zsh库
antigen use oh-my-zsh

## 加载原版oh-my-zsh中的功能
antigen bundle git
antigen bundle heroku
antigen bundle pip
antigen bundle lein
antigen bundle command-not-found


antigen bundle zsh-users/zsh-syntax-highlighting  #语法高亮功能
antigen bundle zsh-users/zsh-autosuggestions   #代码提示功能
antigen bundle zsh-users/zsh-completions   #自动补全功能

##zsh主题地址    https://github.com/robbyrussell/oh-my-zsh/wiki/External-themes
##zsh主题地址    https://github.com/robbyrussell/oh-my-zsh/wiki/themes
antigen theme Fishy2   # 加载主题为 Fishy2

antigen apply     # 保存当前设置 并进行生效设置

#修改 shell的行提示符
PS1="[zukgit的MacPro-\`pwd\`]${ret_status} %{$fg[cyan]%}%c%{$reset_color%} $(git_prompt_info)"


```

```
【个人配置】
1. 终端 》 偏好设置 》 通用tab 》 使用描述文件新建窗口dropdown 》 选中 Homebrew
2. 终端 》 偏好设置 》 描述文件tab 》 Homebrew 》字体 18pt    颜色 绿色 green
```
 
```
【解决打开两个shell窗口应用主题不一致的问题】
打开Shell 》 Shell【menu】 》 将设置用作默认设置


```


```
zsh 快捷键：
Command + T    //  当前窗口打开一个新的tab页 
Ctrl + Tab    // 在打开的Tab之间切换

Command + N   //  新创建一个 Shell窗口
Command +  1    // 在新创建的 Shell窗口进行切换  
Command +  2  
Command +  3


Command + Q  // 强制关闭窗口 所有的窗口
Command + W  // 关闭当前窗口
Command + K   // 清理屏幕
Command + H   // 窗口最小化
```
** Homebrew主题 18pt字体的shell**
<img src="//../zimage/system/macos/02_shellcommand/shell.png">


## align 工具
```
align简介：  该工具是一个把shell中的输入字符整齐排序的功能  对输入进行整齐padding再输出

安装命令:   brew install  align  

使用方法命令:   xxxx   | align

```

```
【示例】
arp -a  | align     【有 align】
[/Users/aaa]➜  ~ git:(master*)arp -a  | align
? (192.168.72.173) at 84:c7:ea:39:e6:a1 on en0 ifscope [ethernet]
? (192.168.73.31)  at b4:b:44:9f:e2:f1  on en0 ifscope [ethernet]
? (192.168.73.97)  at 20:47:da:9c:f7:18 on en0 ifscope [ethernet]
? (192.168.73.180) at b8:fc:9a:2f:cc:8f on en0 ifscope [ethernet]
? (192.168.79.254) at 0:42:5a:11:35:cd  on en0 ifscope [ethernet]
? (224.0.0.251)    at 1:0:5e:0:0:fb     on en0 ifscope permanent  [ethernet]



arp -a   【无 align】
[/Users/aaa]➜  ~ git:(master*)arp -a         
? (192.168.72.173) at 84:c7:ea:39:e6:a1 on en0 ifscope [ethernet]
? (192.168.73.31) at b4:b:44:9f:e2:f1 on en0 ifscope [ethernet]
? (192.168.73.97) at 20:47:da:9c:f7:18 on en0 ifscope [ethernet]
? (192.168.73.180) at b8:fc:9a:2f:cc:8f on en0 ifscope [ethernet]
? (192.168.79.254) at 0:42:5a:11:35:cd on en0 ifscope [ethernet]
? (224.0.0.251) at 1:0:5e:0:0:fb on en0 ifscope permanent [ethernet]
```






## antiword 工具
```
antiword 是一个可以把doc文件中的字符串进行输出的工具。
antiword 是linux及其他RISC OS下免费的ms word文档读取器。使用它可以很方便的在Linux中读取word文档并输出为纯文本字符串。 其中的非字符串部分被过滤了。


官网： http://www.winfield.demon.nl/#Mac%20OS%20X

```

```
用法：   antiword    xxxxx.doc
// 例如：      antiword   ./1.doc

```
**doc 文件**
<img src="//../zimage/system/macos/02_shellcommand/doc.png">
**antiword读取的字符串**
<img src="//../zimage/system/macos/02_shellcommand/antiword.png">


## apktool 工具
```
apktool作用: 主要查看res文件下xml文件、AndroidManifest.xml和图片。（注意：如果直接解压.apk文件，xml文件打开全部是乱码）
apktool下载地址： https://ibotpeaches.github.io/Apktool/install/

apktool 安装命令( Mac ):   brew install apktool

命令:  apktool d app.apk    【 java -jar apktool.jar d yourApkFile.apk 】
```



```
apktool.jar的 使用方法:
1. 将下载的 apktool_2.3.3.jar  名字改为  apktool.jar
2. 运行CMD，把 app.apk放到所在目录，然后运行apktool d app.apk 【apk文件名字】就可以了，默认解压的文件就在app-release.apk所在目录。
  例如 [ apktool d bibibi.apk ]


java -jar apktool.jar d yourApkFile.apk
// 注意`apktool.jar`是刚才下载后的jar的名称，`d`参数表示decode
// 在这个命令后面还可以添加像`-o -s`之类的参数，例如
// java -jar apktool.jar d yourApkFile.apk -o destiantionDir -s
// 几个主要的参数设置方法及其含义：
-f 如果目标文件夹已存在，强制删除现有文件夹
-o 指定反编译的目标文件夹的名称（默认会将文件输出到以Apk文件名命名的文件夹中）
-s 保留classes.dex文件（默认会将dex文件解码成smali文件）
-r 保留resources.arsc文件（默认会将resources.arsc解码成具体的资源文件）


```

```
apktool命令：

//  都是显示  apktool 详细用法  
apktool  -advance     //  显示  apktool 详细用法  
apktool  --advanced   //  显示  apktool 详细用法  
apktool  -v
apktool  -q
apktool  --quiet
apktool  --quiet 
apktool  --verbose  //  显示  apktool 详细用法  


apktool d ./yourApkFile.apk    // 解析当前apk的xml文件 和 图片 （注意：如果直接解压.apk文件，xml文件打开全部是乱码）


```

**apktoll d xxx.apk 命令**
<img src="//../zimage/system/macos/02_shellcommand/apktool.png">
**antiword读取的字符串**
<img src="//../zimage/system/macos/02_shellcommand/apktool_result.png">

## apng2gif 工具
```
APNG又叫动态PNG，第1帧为标准PNG图像，剩余的动画和帧速等数据放在PNG扩展数据块里，因此只支持原版PNG的软件会正确显示第1帧
APNG解决了 GIF的锯齿问题，  gif锯齿问题网址： http://www.ui.cn/detail/34100.html
APNG平均会比GIF的体积大一点点（3%左右）

APNG博客
https://www.zhangxinxu.com/wordpress/2014/09/apng-history-character-maker-editor/

APNG制作：
http://littlesvr.ca/apng/
```

```
apng2gif 安装命令：    brew install apng2gif

使用命令：


```

## apngasm
```
apngasm 是一个使多张png照片生成一张 apng照片(无锯齿gif动画)的工具
使用该命令行生成 APNG 图片
实例地址：  https://www.cnblogs.com/xiaoniuzai/p/8127101.html
安装命令：    brew install apngasm

```

```
【apngasm 用法】
### 使用 dowload-1,2,3,4,5,6~ 序列的图片生成 output.png 的 apng图片，循环次数为1
apngasm ./images2/output.png ./images2/download-*.png -l1


### 使用 dowload-1,2,3,4,5,6~ 序列的图片生成 output.png 的 apng图片，循环次数为1，压缩算法为zlib
apngasm ./images2/output.png ./images2/download-*.png -l1 -z0

```


```
apngasm帮助手册：
APNG Assembler 2.91

Usage   : apngasm output.png frame001.png [options]
          apngasm output.png frame*.png   [options]

Options :
1 10    : frame delay is 1/10 sec. (default) 帧间隔
-l2     : 2 loops (default is 0, forever) 循环次数
-f      : skip the first frame 忽略第一帧
-hs##   : input is horizontal strip of ## frames (example: -hs12) 
-vs##   : input is vertical strip of ## frames   (example: -vs12)
-kp     : keep palette
-kc     : keep color type
-z0     : zlib compression zlib压缩
-z1     : 7zip compression (default) 7zip压缩
-z2     : Zopfli compression zopli 压缩
-i##    : number of iterations (default -i15)


```



## apparix
```
apparix简介： apparix是一个在Shell中为路径起别名bookmark书签的工具,  通过起别名我们可以方便的切换cd 到该别名工作目录

安装方法：   brew install apparix
```


```
【apparix 初始化配置1 】
apparix需要初始化环境才能使用，需要手动创建一个 ~/.bourne-apparish   这样一个可执行文件
在该文件内部填充 https://github.com/micans/bash-utils/blob/master/.bourne-apparish 的内容  ，
 之后在 ~/.zshrc 中增加   source ~/.bourne-apparish   来初始化 apparix 下 shell 下的环境
#############
 vim  ~/.zshrc
 xxxxx
 source ~/.bourne-apparish 
 xxxxx
 
#########
```
 
``` shell
【apparix 初始化配置2 】
vim  ~/.bourne-apparish       // 【  https://github.com/micans/bash-utils/blob/master/.bourne-apparish   内容  】
###################


APPARIXRC=$HOME/.apparixrc
APPARIXEXPAND=$HOME/.apparixexpand
APPARIXLOG=$HOME/.apparixlog


function apparix-init {
   already=""
   if [[ -e $APPARIXRC && -e $APPARIXEXPAND ]]; then
      already=" already"
   fi
   >> $APPARIXRC
   >> $APPARIXEXPAND
   echo "Apparish is up and running$already"
}

function apparish() {
   if [[ 0 == $# ]]; then
      cat $APPARIXRC $APPARIXEXPAND | tr ', ' '\t_' | column -t
      return
   fi
   local mark=$1
   local list=$(grep -F ",$mark," $APPARIXRC $APPARIXEXPAND)
   if [[ -z $list ]]; then
      echo "Mark not found"
      return
   fi
   local target=$((tail -n 1 | cut -f 3 -d ',') <<< "$list")
   if [[ 2 == $# ]]; then
      echo $target/$2
   else
      echo $target
   fi
}

function apparix-list () {
   if [[ 0 == $# ]]; then
      echo Need mark
      return
   fi
   local mark=$1
   grep -F ",$mark," $APPARIXRC $APPARIXEXPAND | cut -f 3 -d ','
}

function bm {
   if [[ 0 == $# ]]; then
      echo Need mark
      return
   fi
   local mark=$1
   local list=$(apparix-list $mark)
   echo "j,$mark,$PWD" | tee -a $APPARIXLOG >> $APPARIXRC
   if [[ ! -z $list ]]; then
      echo -e "Bookmark $mark exists:\n$list" 
      echo "$PWD (added)"
   fi
}

function to () {
  # local IFS=$'\n'
  if [[ 2 == $# ]]; then
    loc=$(apparish "$1" "$2")
  elif [[ 1 == $# ]]; then
    if [[ "$1" == '-' ]]; then
      loc="-"
    else
      loc=$(apparish "$1")
    fi
  else
    loc=$HOME
  fi
  if [[ $? == 0 ]]; then
    cd "$loc"
  fi
}

function portal {
   echo "e,$PWD" >> $APPARIXRC
   portal-expand
}

function portal-expand {
   local parentdir subdir
   > $APPARIXEXPAND
   grep '^e,' $APPARIXRC | cut -f 2 -d , | while read parentdir; do
      cd $parentdir
      find . -maxdepth 1 -type d | cut -b 3- | tail -n +2 | while read subdir; do
         echo "j,$subdir,$parentdir/$subdir" >> $APPARIXEXPAND
      done
   done
}

function whence() {
   if [[ 0 == $# ]]; then
      echo Need mark
      return
   fi
   local mark=$1
   select target in $(apparix-list $mark); do cd $target; break; done
}

function toot () {
   if [[ 3 == $# ]]; then
      file="$(apparish "$1" "$2")/$3"
   elif [[ 2 == $# ]]; then
      file="$(apparish "$1")/$2"
   else
      echo "toot tag dir file OR toot tag file"
      return
   fi
   if [[ $? == 0 ]]; then
      $EDITOR $file
   fi
}

function todo () {
   toot $@ TODO
}

   # apparix listing of directories of mark
function ald () {
  if [[ 2 == $# ]]; then
    loc=$(apparish "$1" "$2")
  elif [[ 1 == $# ]]; then
    loc=$(apparish "$1")
  fi
  if [[ $? == 0 ]]; then
    ls -d "$loc"
  fi
}

   # apparix ls of mark
function als () {
  if [[ 2 == $# ]]; then
    loc=$(apparish "$1" "$2")
  elif [[ 1 == $# ]]; then
    loc=$(apparish "$1")
  fi
  if [[ $? == 0 ]]; then
    ls "$loc"
  fi
}

   # apparix edit of file in mark or subdirectory of mark
function ae () {
  if [[ 2 == $# ]]; then
    loc=$(apparish "$1" "$2")
  elif [[ 1 == $# ]]; then
    loc=$(apparish "$1")
  fi
  if [[ $? == 0 ]]; then
     $EDITOR "$loc"
  fi
}


if [[ -n $BASH_VERSION ]]; then
    # function to complete sensibly on filenames and directories
    # https://stackoverflow.com/questions/12933362/getting-compgen-to-include-slashes-on-directories-when-looking-for-files
    function _my_compgen {
        local cur="$1"

        # Files, excluding directories:
        comm -3 <(compgen -f -- "$cur" | sort) <(compgen -d -- "$cur" | sort) # | sed -e 's/$/ /'
        # Directories (add -S / for slash separator):
        compgen -d -- "$cur"
    }

    # function completing a file, used by _apparix_comp
    function _apparix_comp_file {
      local caller="$1"
      local cur_file="$2"
      # local IFS=$'\n'
      case $caller in
        # complete on directories. this is easy with compgen.
        to|als|ald)
          # Directories (add -S / for slash separator):
          compgen -d -- "$cur_file"
          ;;
        # complete on filenames. this is a little harder to do nicely.
        a|ae|apparish) # Huffman (remove a|)
          _my_compgen "$cur_file"
          ;;
        *)
          echo "please register this function in ~/.bash_apparix:_apparix_dirs" 1>&2
          ;;
      esac
    }

    # function to complete an apparix tag followed by a file inside that tag's
    # directory
    function _apparix_comp {
      local tag="${COMP_WORDS[1]}"
      local IFS=$'\n'
      COMPREPLY=()
      if [[ $COMP_CWORD == 1 ]]; then
        local tags=( $(cut -f2 -d, $HOME/.apparix{rc,expand}) )
        COMPREPLY=( $(compgen -W "${tags[*]}" -- "$tag") )
      else
        local cur_file="${COMP_WORDS[2]}"
        local app_dir=$(apparish $tag 2>/dev/null)
        if [[ -d $app_dir ]]; then
            # run in subshell so cd isn't permanent
            COMPREPLY=( $(cd $app_dir && _apparix_comp_file $1 $cur_file) )
        else
            COMPREPLY=()
        fi
      fi
      if (( ${#COMPREPLY[@]} > 0 )); then
        # The line below makes all know cases with spaces in directory names work.
        COMPREPLY=($(printf "%q\n" "${COMPREPLY[@]}"))
      fi
      return 0
    }
  # register completions
  complete -o nospace -F _apparix_comp a to als ald ae apparish   # Huffman (remove a)
elif [[ -n $ZSH_VERSION ]]; then
    function _apparix_file {
        # local IFS=$'\n'
        _arguments '1:mark:_values "\n" $(cut -d, -f2 $HOME/.apparix{rc,expand})' \
                   '2:file:_path_files -W "$(apparish $words[2] 2>/dev/null)"'
    }

    function _apparix_directory {
        # local IFS=$'\n'
        _arguments '1:mark:_values "\n" $(cut -d, -f2 $HOME/.apparix{rc,expand})' \
                   '2:file:_path_files -/W "$(apparish $words[2] 2>/dev/null)"'
    }

    compdef _apparix_file ae apparish a          # Huffman (remove a)
    compdef _apparix_directory to ald als
fi

export APPARIXLOG=$HOME/.apparixlog

alias a='apparish'                              # Huffman (remove entire line)
alias via='vi $HOME/.apparixrc'



```


```
【使用方法：】
【1】   apparix      //必须首次执行【执行改命令会创建扩展文件   ~./.apparixexpand  用于保存 bm命令产生的 <标记名,路径> 的 Map对应关系】
[apparix] created expansion file /Users/aaa/.apparixexpand
portals
expansions
bookmarks
j home         /Users/aaa
j MD           /Users/aaa/Desktop/code_place/MD_GIT/MD_FILE


【2】 bm   bookmark       // 该命令会在 ~/.apparixlog 生成路径 与 bookmark的对应关系   <bookmark , pdw(path)> 

【3】 to  bookmark   // 该命令直接切换到对应的bookmark对应的path路径  

【4】 vim  ~/.apparixrc     // apparix 好像没有提供对应的删除map关系的函数   需要手动在 ~/.apparixrc 这里删除对应的map 路径关系

```

**apparix的bm 和 to命令**
<img src="//../zimage/system/macos/02_shellcommand/apparix.png">


## archey 显示工具
```
archey 命令会为你安装  Archey，它是一个很酷的脚本，用来在一个多彩的Apple Logo显示你的Mac的配置

安装命令 : brew install archey 


执行 ：   archey


```
<img src="//../zimage/system/macos/02_shellcommand/archey.png">


## aria2 命令行下载工具
```
【aria2 简介】：  aria2是一个多平台轻量级，支持 HTTP、FTP、BitTorrent 等多协议、多来源的命令行下载工具。
Aria2 可以从多个来源、多个协议下载资源，最大的程度上利用了你的带宽。。Aria2 跟 IDM 类似，不仅可以多线程下载，
还可以通过多来源进行下载，简单的说就是从多个镜像服务器同时下载一个文件，Aria2 还支持 BT 协议，弥补了 IDM 只支持 HTTP 和 FTP 的痛点。
aria2是一款优秀的多线程下载器，不光可以下载百度网盘下载器也可以作为普通的下载器以及接管chrome等浏览器的下载器。


【aria2 与百度网盘插件】
百度网盘插件地址： 
https://github.com/acgotaku/BaiduExporter/blob/master/BaiduExporter.crx
刷新百度云盘，发现多了个导出下载，就是它了，选择需要下载的文件----插件作用

【Aria2GUI 是 aria2 的 UI实现界面软件】
Aria2GUI 版本 1.3 (7) 可用 路径： https://github.com/yangshun1029/aria2gui/releases
http://127.0.0.1:6800/jsonrpc?jsoncallback=1    【  检测Aria2GUI 是否正常工作 】
【安装命令】    brew install aria2
 
```

<img src="//../zimage/system/macos/02_shellcommand/aria2.png">
<a href="./file/shell_command_tool/BaiduExporter.crx">点击下载百度网盘插件</a>


```
【aria2命令  aria2c 命令】
aria2c -i    xxxx.url.xxx.file  // 下载的文件url路径
aria2c -v     //  aria2 version 1.34.0
aria2c  -h       // aria2c 命令的 帮助文档
aria2c  -d         //本地文件下载后保存的路径  默认  /Users/aaa
aria2c  -o       // 下载文件的本地保存的名字 
aria2c  -s  5  //   aria2c  -s  N 数字     // 下载一个文件使用多少个连接 connect  默认为5
aria2c   -c  true                 // 暂停下载   
aria2c   -c  false                // 继续下载
aria2c   -j  5   //   aria2c  -j  N 数字     // 最大下载数量    默认为5
aria2c   -x    5   //   aria2c  -x  N 数字     // 每个下载的服务器最大连接数  默认为5
aria2c   -T    xxxx.torrent        // 设置下载的种子文件开始下载
aria2c  -k  20M    // 最小分包下载大小  
aria2c   -S    // 显示show 下载情况
aria2c   --max-overall-upload-limit 10M  // 设置整理上传速度    bytes/sec.   You can append K or M(1K = 1024, 1M = 1024K).
aria2c   --max-upload-limit=2M         // 设置每一个下载任务的最大限制的上传速度   

aria2c    --listen-port 6881-7999  // 设置TCP下载 BitTorrent 下载的 端口号 默认6881-6999
                              



aria2c   --ftp-user   xxxxx   //  ftp账户
aria2c  --ftp-passwd  xxxxx   //  ftp密码
aria2c   --http-user   xxxxx   //  http账户
aria2c  --http-passwd  xxxxx   //  http密码

```


```
aria2c 命令实例： https://www.xuebuyuan.com/2047577.html

aria2c -o QQ.exe http://dl_dir.qq.com/qqfile/qq/QQ2011/QQ2011.exe   

aria2c -o 1.png http://host805073097.s507.pppf.com.cn/wp-content/image/wifi/img/1.png
##默认下载保存地址  /Users/aaa
```

**命令行执行下载任务**
<img src="//../zimage/system/macos/02_shellcommand/aria2_command.png">


## Argon2 加密工具(下一代加密工具 比MD5 SHA1优秀得多)
```
Argon2是一个密钥导出函数（KDF），并提供了几个改进的版本：

【1】Argon2d ： 最大限度地提高了对GPU破解攻击的抵抗力。它以密码相关的顺序访问存储器阵列，
    这降低了时间 - 存储器权衡（TMTO）攻击的可能性，但是引入了可能的侧面信道攻击。

【2】Argon2i ： 优化了抵御侧向通道攻击的能力。它以密码无关的顺序访问内存阵列。

【3】Argon2id：是一个混合版本。它遵循Argon2i方法进行第一次通过内存，Argon2d方法用于后续通过。
    Internet-Draft建议使用Argon2id，除非有理由选择其他两种模式之一。
       
上述三种模式允许通过三个参数来进行控制：      
memory_cost (integer) -需要内存大小 计算 Argon2 散列时的最大内存
time_cost (integer) - 执行时间处理时间  计算 Argon2 散列时最多的时间
threads (integer) -并行度  计算 Argon2 散列时最多的线程数

```

```
在PHP方法中 对 MD5   SHA1  Bcrypt  Argon2 加密方式进行对比

在PHP中通过 ## password_hash ## 来生成 第二个参数对应加密算法的密文
PASSWORD_DEFAULT= Bcrypt【默认是 Bcrypt 加密算法】
PASSWORD_ARGON2I 【 Argon2 加密算法 】
$password = password_hash(“明文1234578”, PASSWORD_DEFAULT);


在PHP中通过  验证密码和hash值是否匹配可以使用 ## password_verify ## 函数
boolean flag = password_verify (“明文12345678”, “faufbjakbfakjbkfjabfajk密文”)
明文经过password_hash函数哈希过后的值 与密文相等 相等会返回true，不相等会返回false


在PHP中通过函数 password_needs_rehash 检测指定的hash值 密文是否实现了提供的算法和选项。 
返回true表示 密文 就是对应加密算法生成的     返回false 表示密文不是当前加密算法生产的

boolean flag= password_needs_rehash(“jkfjks密文”, PASSWORD_DEFAULT);
      
      
http://www.runoob.com/try/runcode.php?filename=demo_intro&type=php    测试网站  

<php>
	
$password=123456;
	
echo "MD5:\n"; 
var_dump(md5($password));
echo "SHA1:\n";
var_dump(sha1($password));
echo "Bcrypt:\n";
var_dump($password = password_hash($password, PASSWORD_DEFAULT));
echo "Argon2I:\n";
var_dump($password = password_hash($password, PASSWORD_ARGON2I));
	
</php>

输出：
MD5:
string(32) "e10adc3949ba59abbe56e057f20f883e"
SHA1:
string(40) "7c4a8d09ca3762af61e59520943dc26494f8941b"
Bcrypt:
string(60) "$2y$10$m.t9Gq4h5IT0UV7a4tapNuTQUqbx1JncyfuXIjDa9HdL6V/3iDMU2"
Argon2I:
string(95) "$argon2i$v=19$m=1024,t=2,p=2$YmlGMWRaNHA2VWFVYlpTNQ$8xzVvtfPhdeeL+1NPr5AiA+L/fwr8pl5CADuj7H6usU"
 
再次运行输出:
MD5:
string(32) "e10adc3949ba59abbe56e057f20f883e"
SHA1:
string(40) "7c4a8d09ca3762af61e59520943dc26494f8941b"
Bcrypt:
string(60) "$2y$10$UY2BfPh.Lyeibs96oEScCuwHB6AbeiC8hgY/80eXvPdCwKpeasmri"
Argon2I:
string(95) "$argon2i$v=19$m=1024,t=2,p=2$OTJIS01rUnhGamJ3cXFSSA$sFfHpfji1NkbPmhUhqWJeTsT3o4cIwrGm67geYjS3WA"
 



结论：
显然易见 我们可以发现 bcrypt 和 argon2i 更加安全 , 每次运行结果不一样   
如果有不相信的同学 可以到一些解密网站 如 http://www.cmd5.com/ 
等去尝试解密 你会发现 md5 和 sha1 是真的非常容易被破解
 
```

```
【argon2 命令】 【密码是从标准shell输入流输入的 所以需要用到 echo “xxx” | argon2 xx 】
argon2 -h
Usage:  argon2 [-h] salt [-i|-d|-id] [-t iterations] [-m log2(memory in KiB) | -k memory in KiB] [-p parallelism] [-l hash length] [-e|-r] [-v (10|13)]
	Password is read from stdin
Parameters:
	salt	【 用于加密的配对钥匙码 至少是8位的(配对钥匙码不同 相同的明文生成的密文不同) 】 The salt to use, at least 8 characters
	-i		【使用 Argon2i 版本生成密文  默认 】Use Argon2i (this is the default)
	-d		【使用 Argon2d 版本生成密文 】Use Argon2d instead of Argon2i
	-id		【使用 Argon2id 版本生成密文 】Use Argon2id instead of Argon2i
	-t N	【设置密文生成需要迭代运行的字数 默认是3 】	Sets the number of iterations to N (default = 3)
	-m N	【设置密文生成的使用内存空间 默认是 2^12 KiB = 4096 KB 】 Sets the memory usage of 2^N KiB (default 12)
	-k N	【设置密文生成的使用内存空间 默认是 4096 KiB  4M空间  和-m 参数一样 但表示的不同  】Sets the memory usage of N KiB (default 4096)
	-p N	【设置密文生成需要运行的线程数量 默认是1 】Sets parallelism to N threads (default 1)
	-l N	【设置明文产生的hash字符的字节长度 默认是 32 （ 64 个 十六进制字符0-f） 】Sets hash output length to N bytes (default 32)
	-e		 【仅仅输出 产生的加密 hash 哈希值】Output only encoded hash
	-r		 【仅仅输出加密hash值对应的字节 16进制】Output only the raw bytes of the hash
	-v (10|13)	Argon2 version (defaults to the most recent version, currently 13)
	-h		Print argon2 usage




使用方法：



【  echo -n "12345678" | argon2 zukgit_bb   】  【除了salt配对钥匙码 需要完全自定义 其余全都用默认的配置】
输出：
Type:		Argon2i
Iterations:	3
Memory:		4096 KiB
Parallelism:	1
Hash:		04f449693957255e70784d0357a829ee79cc7d34db283dedb032260973f5e639
Encoded:	$argon2i$v=19$m=4096,t=3,p=1$enVrZ2l0X2Ji$BPRJaTlXJV5weE0DV6gp7nnMfTTbKD3tsDImCXP15jk
0.011 seconds
Verification ok






【 echo -n "12345678" | argon2 zukgit_bb -id -t 3 -m 16 -p 3 -l 40   】
输出：
Type:		Argon2id
Iterations:	3
Memory:		65536 KiB
Parallelism:	3
Hash:		821d7aa13642c138d3f8465e3854dd2d273613654730ebc7aa452f67b934ae185d6e3ffb342123c9
Encoded:	$argon2id$v=19$m=65536,t=3,p=3$enVrZ2l0X2Ji$gh16oTZCwTjT+EZeOFTdLSc2E2VHMOvHqkUvZ7k0rhhdbj/7NCEjyQ
0.205 seconds
Verification ok

解释： 使用的加密类型是 -id Argon2id加密版本算法  ， 密文产生需要迭代的字数 -t 3  对应3次  ，
      需要的空间大小 -m 16 对应  2^16 KiB  = 64MB ，   -p 3   使用线程数量为3 ， 
      -l  40 表示  hash值保存为 40个字节(即 80位 16进制)
       



```


## arp-scan
```
简介： arp-scan是一个用来进来系统发现和指纹识别的命令行工具。它可以构建并发送ARP请求到指定的IP地址，并且显示返回的任何响应。
ARP（地址解析协议）是一种协议，通过已知的IP网络地址，计算出了链路层（第2层）地址Mac地址。 ARP协议在RFC826以太网地址解析协议定义。

ARP协议被设计成允许被用于任何链路层和网络层协议。然而在实际中它仅用于以太网（包括802.11无线）和IPv4，我们假定在整个文档中，这些协议。 IPv6使用NDP（邻居发现协议）来代替，这是一种不同的协议。

ARP是一个不可路由的协议，因此只能在同一个以太网网络上的系统之间使用。


arp-scan可以做到这些：
1.使用一个可以配置的输出带宽或者包速率，发送ARP包到任意多个目标主机。
2.对于系统发现，这是非常有用的，你可能需要扫描大量的地址空间。 发现本地网络上的所有主机
3.以灵活的方式构建并传出ARP报文。
4.arp-scan可以控制ARP包中所有的字段以及以太网帧的头部。
5.解码并且显示任何返回的数据包。
6.arp-scan可以解码显示返回的任何ARP数据包，并且使用MAC地址查找供应商。
7.使用arp-指纹工具采集IP主机的指纹
https://www.oschina.net/translate/arp-scan-arp-scanning-and-fingerprinting-tool?print

```


```
arp-scan -h  帮助手册

Usage: arp-scan [options] [hosts...]

【1】目标主机必须在命令行中除非带有 --file 参数 挥着 带有 -localnet 参数
【2】arp-scan命令由于需要读网络包所以需要root的权限才能执行
【3】目标主机可以以IP地址或者 主机名hostname的形式出现，或者IP地址/掩码 的形式出现
【4】目标地址可以以 IPstart-IPend  IP地址开始起点-IP地址结束终点的形式出现
(如 192.168.1.3-192.168.1.27)或者 
IP地址:子网掩码的形式(如 192.168.1.0:255.255.255.0) ，工具将扫描子网掩码下的所有IP地址
【5】arp-scan可选参数可以通过 命令行的形式输入，也可以通过 --file 指定包含option执行操作的文件来给 arp-scan工具执行

Options 可选参数:
<s> 表示一个字符串 A character string, e.g. --file=hostlist.txt.

<i> 表示一个整数值 An integer, which can be specified as a decimal number or as a hexadecimal  number if preceeded with 0x, e.g. --arppro=2048 or --arpro=0x0800.

<f> 表示一个浮点数值 A floating point decimal number, e.g. --backoff=1.5.

<m> 表示一个mac地址 An Ethernet MAC address, which can be specified either in the format
    01:23:45:67:89:ab, or as 01-23-45-67-89-ab. The alphabetic hex characters
    may be either upper or lower case. E.g. --arpsha=01:23:45:67:89:ab.

<a> 表示一个IP地址 An IPv4 address, e.g. --arpspa=10.0.0.1

<h> 表示以16进制输入的二进制数据 Binary data specified as a hexadecimal string, which should not
    include a leading 0x. The alphabetic hex characters may be either
    upper or lower case. E.g. --padding=aaaaaaaaaaaa

<x> 表示其他的一些输入 Something else. See the description of the option for details.

arp-scan -f ./address.txt   【 读取address.txt每行的IP地址 到aep-scan 】


arp-scan  --interface=en0 --localnet    【通过网络接口 以及掩码自动所搜主机列表 包括广播地址 】
          对于一个地址 10.0.0.1 以及子网掩码 255.255.255.0  将会产生256个网络IP地址
          与10.0.0.0-10.0.0.255 所表示的地址等价。如果你用了这个 --localnet命令参数
          那么就不可以同时使用 -f 参数。  这个参数必须包含另一个 --interface参数使用，该
          --interface用于指定当前网络工作的网卡接口名称
          
    

arp-scan --retry=3  【 与 host主机进行发包尝试的次数 默认是2 】
【arp-scan  --interface=en0 --localnet  --retry=3  】


arp-scan --timeout=1000  --backoff=2  【 设置每个主机的 尝试访问超时时间 单位是 ms 毫秒  默认是 500ms ，有参数 --backoff=1.5 超时退避指数 配合使用】
【arp-scan  --interface=en0 --localnet  --retry=3  --timeout=1000  --backoff=2  】                               
     【当第一次发生超时时那 那么下次的超时时间是 当前 timeout * backoff = 2000ms = 2秒 ， 再下次超时时间为4 秒 】


arp-scan  --bandwidth=512KB     【设置传输包的带宽  默认是 256000 bit/s = 32KB/s   可以设置为 --bandwidth=64K  --bandwidth=1M 】
【arp-scan  --interface=en0 --localnet  --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K  】 


arp-scan -v   【显示更多详情信息】
【 arp-scan  --interface=en0 --localnet  --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K -v   】 

arp-scan --version    【显示版本信息】
【
arp-scan 1.9.5
Copyright (C) 2005-2016 Roy Hills, NTA Monitor Ltd.
arp-scan comes with NO WARRANTY to the extent permitted by law.
You may redistribute copies of arp-scan under the terms of the GNU
General Public License.
For more information about these matters, see the file named COPYING.
libpcap version 1.8.1 -- Apple version 67.60.1
  】


arp-scan  --random   【 不按顺序随机发送ARP包到指定的主机 host】



arp-scan   --quiet    【 显示最少最小的输出信息 】
【 arp-scan  --interface=en0 --localnet  --random --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K   --quiet   】 

arp-scan  --plain      【 只显示有回复的主机列表 不显示那么没有回复的主机列表 】
【 arp-scan  --interface=en0 --localnet  --random --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K  -v --plain   】 


arp-scan  --ignoredups     【不展示重发的包 默认重发的包会被标记为 (DUP: n) 】
【 arp-scan  --interface=en0 --localnet  --random --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K  -v  --ignoredups     】 




arp-scan   --ouifile=./ieee-oui.txt  【用于把当前的IP地址进行Map映射  对对应的MAc地址 进行映射显示】
【最新的文件信息网站：   http://standards-oui.ieee.org/oui/oui.txt 】
【科普： Mac地址申请的方式】
【1.OUI方式： 一般都是以这种方式申请为多，因为总共12位16进制码的MAC地址, 厂商ID占前6位, 其他６位客户都可以随意使用，自行管理，
申请费用是1650美金,一次性收费。这样申请一个厂商ID号可以享用2的24次方即16777216个MAC地址，通常记为1600万个。】
【2.IAB方式： 这种方式比较特别，主要是针对个人用户。因为这种方式最多只能申请4096个MAC地址，也就是只有３位16进制，12位二进制 2的12次方= 4096的地址可以使用。】

【/usr/local/Cellar/arp-scan/1.9.5/share/arp-scan/ieee-oui.txt 保存的是Mac地址前三位数 和生产产商的关系】
080007	Apple, Inc.
000A95	Apple, Inc.
48AD08	HUAWEI TECHNOLOGIES CO.,LTD
2CAB00	HUAWEI TECHNOLOGIES CO.,LTD
2082C0	Xiaomi Communications Co Ltd
C46AB7	Xiaomi Communications Co Ltd
38BC1A	MEIZU Technology Co., Ltd.
90F052	MEIZU Technology Co., Ltd.
683E34	MEIZU Technology Co., Ltd.
2C5731 MEIZU Technology Co., Ltd.
0022DE	OPPO Digital, Inc.
78009E	Samsung Electronics Co.,Ltd
6073BC	zte corporation
FC1A11	vivo Mobile Communication Co., Ltd.
78FFCA	TECNO MOBILE LIMITED【传音】
D0577B	Intel Corporate
8056F2	Hon Hai Precision Ind. Co.,Ltd.
E09861	Motorola Mobility LLC, a Lenovo Company
E02CB2	Lenovo Mobile Communication (Wuhan) Company Limited
F80D60	CANON INC.
4040A7	Sony Mobile Communications AB
045604	Gionee Communication Equipment Co.,Ltd.
982F3C	Sichuan Changhong Electric Ltd.
506441	Greenlee  【格力】
D896E0	Alibaba Cloud Computing Ltd.
001906	Cisco Systems, Inc
A826D9	HTC Corporation
00A0C6	Qualcomm Inc.
000A00	Mediatek Corp. 【MTK】
【我们可以自定义对应的关系  并不指定前三位 这样能客制化 知道的MAC地址的机器的名字的 arp-scan命令显示出现】
【1.txt   --ouifile=./1.txt   这样就能显示Mac地址 对应客制化关系图  能看得到谁是谁了】
64B0A6AE2EA2	小红的电脑
88E9FE7B705D	小红的手机
2C5731C6E212	小明的手机
1C91485E3992	小黑的手机
8C8590870F94	小龙的手机
B019C63062B6	小林的手机
34D7128DE5E0	小海的手机
【 arp-scan  --interface=en0 --localnet  --random --retry=3  --timeout=1000  --backoff=2  --bandwidth=512K  -v  --ignoredups  --ouifile=./1.txt   】 



arp-scan  --srcaddr=64:B0:A6:AE:2E:A2    【设置ARP包的原地址为 64B0A6AE2EA2 】


arp-scan  --destaddr=64:B0:A6:AE:2E:A2    【设置ARP包的目的地址为 64B0A6AE2EA2 】
arp-scan  --destaddr=ff:ff:ff:ff:ff:ff    【目的地址为ff  说明这个包是一个广播包】



arp-scan  --arpsha=64:B0:A6:AE:2E:A2 【设置ARP包中的 ar$sha 原地址域 为指定的mac地址 】
arp-scan  --arptha=64:B0:A6:AE:2E:A2 【设置ARP包中的 ar$tha 目的地址域 为指定的mac地址  默认是0 】


arp-scan  --prototype=0x0806     【设置以太网协议版本】

arp-scan  --arphrd=1   【This sets the 16-bit ar$hrd field in the ARP packet 设置硬件类型】

arp-scan --arppro=0x0800  【设置ARP协议版本  ar$pro  】  

arp-scan  --arphln=6 【设置硬件地址长度 ar$hln  默认为6】  

arp-scan  --arppln=4  【设置协议地址长度 ar$pln  默认为4】    


arp-scan --arpop=1  【设置ARP协议地操作类型 ar$op  默认为1】   

arp-scan --arpspa=  【设置目的IP地址 ar$spa  32bit 】  

arp-scan --padding=0123456789ABCDEF  【设置ARP协议不满一帧情况下 在包末尾填充的二进制数据】


arp-scan --pcapsavefile=./xxx.pcap   【把当前接收到的包写入pcap文件   这个文件可以被软件  wireshake tcpdump这样的工具分析】
arp-scan -W=./xxx.pcap 
【 arp-scan  --interface=en0 --localnet   --rtt  --retry=2  --timeout=1000  --backoff=2  --bandwidth=512K  -v  --ignoredups    】


arp-scan --rtt   【显示 RTT  Round-Trip-Time  往返的时间】
arp-scan -D 
【 arp-scan  --interface=en0 --localnet   --rtt  --retry=2  --timeout=1000  --backoff=2  --bandwidth=512K  -v   --pcapsavefile=./xxx.pcap   】

```

**arp-scan --pcapsavefile捕获到的包对应的手机IP地址与Mac地址**
<img src="//../zimage/system/macos/02_shellcommand/arp_scan.png">

## arpoison 工具
```
arpoison:arpoison 是ARP地址解析协议即ARP(Address Resolution Protocol)的防御攻击武器
简介：https://blog.csdn.net/flfblog/article/details/8745293
http://www.sohu.com/a/245155421_609556
https://www.cnblogs.com/k-yukiho/p/6669234.html


安装命令(Mac)： brew install  arpoison



使用方法: 
arpoison -help
Usage: -i <device> -d <dest IP> -s <src IP> -t <target MAC> -r <src MAC> [-a] [-w time between packets] [-n number to send]

参数说明
-i 指定发送arp包的网卡接口eth0
-d 192.168.1.1 指定目的ip为192.168.1.1 
-s 192.168.1.101 指定源ip为192.168.1.101
-t ff:ff:ff:ff:ff:ff 指定目的mac地址为ff:ff:ff:ff:ff:ff(arp广播地址)
-r 00:1c:bf:03:9f:c7 指定源mac地址为00:1c:bf:03:9f:c7
-w 等待时间
-n 发送包的数目



防御ARP欺骗
sudo arpoison -i en0 -d 192.168.1.1 -s 192.168.1.101 -t ff:ff:ff:ff:ff:ff -r 00:1c:bf:03:9f:c7
【发送arp广播 告诉当前网络中的所有主机  IP地址192.168.1.101  对应的Mac地址是 00:1c:bf:03:9f:c7】


进行arp欺骗
sudo arpoison -i eth0 -d 192.168.1.50 -s 192.168.1.1 -t ff:ff:ff:ff:ff:ff -r 00:1c:bf:03:9f:c7
【伪装成网关发送给 IP地址192.168.1.50 高速这个主机网关192.168.1.1 的mac地址是  00:1c:bf:03:9f:c7 ， 其实这个地址是错误的 以此达到 ARP欺骗攻击 】



```
## ascii 工具
```
安装命令(Mac)： brew install  ascii

ascii -o     
  000 NUL   020 DLE   040     060 0   100 @   120 P   140 `   160 p 
  001 SOH   021 DC1   041 !   061 1   101 A   121 Q   141 a   161 q 
  002 STX   022 DC2   042 "   062 2   102 B   122 R   142 b   162 r 
  003 ETX   023 DC3   043 #   063 3   103 C   123 S   143 c   163 s 
  004 EOT   024 DC4   044 $   064 4   104 D   124 T   144 d   164 t 
  005 ENQ   025 NAK   045 %   065 5   105 E   125 U   145 e   165 u 
  006 ACK   026 SYN   046 &   066 6   106 F   126 V   146 f   166 v 
  007 BEL   027 ETB   047 '   067 7   107 G   127 W   147 g   167 w 
  010 BS    030 CAN   050 (   070 8   110 H   130 X   150 h   170 x 
  011 HT    031 EM    051 )   071 9   111 I   131 Y   151 i   171 y 
  012 LF    032 SUB   052 *   072 :   112 J   132 Z   152 j   172 z 
  013 VT    033 ESC   053 +   073 ;   113 K   133 [   153 k   173 { 
  014 FF    034 FS    054 ,   074 <   114 L   134 \   154 l   174 | 
  015 CR    035 GS    055 -   075 =   115 M   135 ]   155 m   175 } 
  016 SO    036 RS    056 .   076 >   116 N   136 ^   156 n   176 ~ 
  017 SI    037 US    057 /   077 ?   117 O   137 _   157 o   177 DEL 


```

<img src="//../zimage/system/macos/02_shellcommand/ascii.png">


## AsciiDoc
```
AsciiDoc简介：  AsciiDoc是一个成熟的、为大型文档设计的标记语言。AsciiDoc类似于MarkDown语法，提供的功能比Markdown更丰富的  【文档属性，设置作者、版本信息等】【语法高亮】【表格】 
【Include 功能，将大文档拆分几个文件】 【自定义块语法，可扩展性】
如果你在搭建复杂的文档环境，并且认为 Markdown 已经不能满足需求，那么可以试试 AsciiDoc。
http://ju.outofmemory.cn/entry/95397


安装命令(mac): 
brew install asciidoc

```




## asciinema
```
简介：asciinema 是一个在终端下非常棒的录制分享软件，基于文本的录屏工具，对终端输入输出进行捕捉， 然后以文本的形式来记录和回放！这使其拥有非常炫酷的特性：在 播放 过程中你随时可以暂停， 然后对“播放器”中的文本进行复制或者其它操作！

官网: https://asciinema.org/


安装命令： brew install asciinema


```


```
asciinema帮助手册：
example usage:
  Record terminal and upload it to asciinema.org:
    asciinema rec    【开始录制命令】
  Record terminal to local file:
    asciinema rec demo.cast     【开始本地录制视频】
  Record terminal and upload it to asciinema.org, specifying title:
    asciinema rec -t "My git tutorial"  【开始录制 完成之后以  My git tutorial 为标题上传到 官网 www.asciinema.org 】
  Record terminal to local file, limiting idle time to max 2.5 sec:
    asciinema rec -i 2.5 demo.cast  【录制本地视频 命令为demo.cast 最长闲置时间2.5秒】
  Replay terminal recording from local file:
    asciinema play demo.cast    【本地视频播放】
  Replay terminal recording hosted on asciinema.org:
    asciinema play https://asciinema.org/a/difqlgx86ym6emrmd8u62yqu8 【播放官网视频】
  Print full output of recorded session:
    asciinema cat demo.cast   【打印所有本地视频中输入的内容】


```

```
录制命令： asciinema rec
回车后你会看到下面两行输出
asciinema: recording asciicast to 
/var/folders/dn/g8yhcfnj3gnbj9421vfpb52c0000gn/T/tmpf0t3tjw5-ascii.cast
asciinema: press <ctrl-d> or type "exit" when you're done
这表示录制已经开始 显示了保存的路径，你可以按 Ctrl+D 或输入 exit 进行退出

回放命令： asciinema play /var/folders/dn/g8yhcfnj3gnbj9421vfpb52c0000gn/T/tmpf0t3tjw5-ascii.cast
asciinema play https://asciinema.org/a/170142     【播放官网上的视频】

打印播放内容命令：
asciinema cat /var/folders/dn/g8yhcfnj3gnbj9421vfpb52c0000gn/T/tmpf0t3tjw5-ascii.cast

```


## asciinema2gif
```
简介：  该工具用于把 asciinema record 产生的 shell视频转换为 gif动态图片文件
项目地址：  https://github.com/tav/asciinema2gif

```


```
帮助手册： 
asciinema2gif [options] <asciinema_id|asciinema_api_url>
asciinema2gif -s small 
asciinema2gif -s medium 
asciinema2gif -s big

asciinema2gif -t asciinema
asciinema2gif -t tango
asciinema2gif -t olarized-dark
asciinema2gif -t solarized-light
asciinema2gif -t monokai

asciinema2gif -p 2     【播放速度】

asciinema2gif -o xxxx.gif  【默认 asciicast.gif 】

  options:
    -s <size>, --size <size>      One of 'small', 'medium', 'big'
    -p <speed>, --speed <speed>   Any integer (whole number) to multiply regular speed by
    -t <theme>, --theme <theme>   One of 'asciinema', 'tango', 'solarized-dark', 'solarized-light', 'monokai'
    -o <file>, --output <file>    File to write to (defaults to 'asciicast.gif' in current directory)
    -h, --help                    Show this help.


```

```
使用命令：
asciinema2gif --size small --speed 2 --theme  8332

asciinema2gif --theme solarized-light -o "${HOME}/Desktop/another.gif" https://asciinema.org/api/asciicasts/8332

【注意】 尼玛这个工具只能对上传到asciinema网站的record，拥有编号ID的才能转为gif 本地的不能转为gif~~~  差评 
```




##  asciiquarium shell水族箱动画
```
asciiquarium ：  该工具是一个在shell显示字符构建的水族箱动画，仅提供观赏。

安装命令：   brew install asciiquarium
使用命令：   asciiquarium

```

<img src="//../zimage/system/macos/02_shellcommand/asciiquarium.png">




##  autossh 远程登录界面
```
autossh 简介： Automatically restart SSH sessions and tunnels
autossh本身就是个管理、维护ssh的命令，所以其参数也只有最基本的-V(version)、-M(monitoring)、-f(background)。而我们常说的端口转发，反向代理等厉害的功能其实是ssh实现的，我们把参数传给autossh，然后autossh传给ssh来实现特定的功能。

SSH简介： SSH 为 Secure Shell 的缩写 （安全外壳协议）。SSH 为建立在应用层基础上的安全协议 (七层网络架构的第一层)。
SSH 是目前较可靠，专为远程登录会话和其他网络服务提供安全性的协议。
利用 SSH 协议可以有效防止远程管理过程中的信息泄露问题。SSH最初是UNIX系统上的一个程序，后来又迅速扩展到其他操作平台。SSH在正确使用时可弥补网络中的漏洞。
传统的网络服务程序，如：ftp、pop和telnet在本质上都是不安全的，因为它们在网络上用明文传送口令和数据，别有用心的人非常容易就可以截获这些口令和数据
通过使用SSH，你可以把所有传输的数据进行加密，而且也能够防止DNS欺骗和IP欺骗
使用SSH，还有一个额外的好处就是传输的数据是经过压缩的，所以可以加快传输的速度。
SSH有很多功能，它既可以代替Telnet，又可以为FTP、PoP、甚至为PPP提供一个安全的"通道"。


SSH提供两种级别的安全验证。
第一种级别（基于口令的安全验证）
          只要你知道自己帐号和口令，就可以登录到远程主机。所有传输的数据都会被加密，但是不能保证你正在连接的服务器就是你想连接的服务器
          可能会有别的服务器在冒充真正的服务器，也就是受到“中间人”这种方式的攻击。
          
第二种级别（基于密匙的安全验证）
          需要依靠密匙，也就是你必须为自己创建一对密匙，并把公用密匙放在需要访问的服务器上。如果你要连接到SSH服务器上，客户端软件就会向服务器发出请求，请求用你的密匙进行安全验证。
          服务器收到请求之后，先在该服务器上你的主目录下寻找你的公用密匙，然后把它和你发送过来的公用密匙进行比较。
          如果两个密匙一致，服务器就用公用密匙加密“质询”（challenge）并把它发送给客户端软件。客户端软件收到“质询”之后就可以用你的私人密匙解密再把它发送给服务器。
          用这种方式，你必须知道自己密匙的口令。但是，与第一种级别相比，第二种级别不需要在网络上传送口令。
          第二种级别不仅加密所有传送的数据，而且“中间人”这种攻击方式也是不可能的（因为他没有你的私人密匙）。但是整个登录的过程可能需要10秒
```


```
ssh的结构： 
ssh服务端一般是sshd进程，提供了对远程连接的处理，一般包括公共密钥认证、密钥交换、对称密钥加密和非安全连接。
服务端是一个守护进程(daemon)，他在后台运行并响应来自客户端的连接请求。

客户端包含ssh程序以及像scp（远程拷贝）、slogin（远程登陆）、sftp（安全文件传输）等其他的应用程序。

ssh的工作机制：
他们的工作机制大致是本地的客户端发送一个连接请求到远程的服务端，服务端检查申请的包和IP地址再发送密钥给SSH的客户端，
本地再将密钥发回给服务端，自此连接建立。SSH 1.x和SSH 2.x在连接协议上有一些差异。

启动SSH服务器后，sshd运行起来并在默认的22端口进行监听（你可以用 # ps -waux | grep sshd 来查看sshd是否已经被正确的运行了）
SSH服务器进程 sshd 会一直等待连接请求。当请求到来的时候SSH守护进程会产生一个子进程，该子进程进行这次的连接处理。
SSH采用面向连接的TCP协议传输 应用22号端口 安全系数较高
```


```
ssh的安装
SSH分客户端  openssh-client  和  openssh-server

如果你只是想登陆别的机器的SSH只需要安装openssh-client
【  Linux，sudo apt-get install openssh-client   】
【  Mac ，brew install openssh   】

如果要使本机开放SSH服务就需要安装 openssh-server。

```

```
【 Mac下启动ssh服务 】
mac本身安装了ssh服务，默认情况下不会开机自启

1.启动sshd服务：
sudo launchctl load -w /System/Library/LaunchDaemons/ssh.plist

2.停止sshd服务：
sudo launchctl unload -w /System/Library/LaunchDaemons/ssh.plist

3查看是否启动：
sudo launchctl list | grep ssh

如果看到下面的输出表示成功启动了：
－－－－－－－－－－－－－－
- 0 com.openssh.sshd
```

```
【 Mac下登录 ssh服务 提供 远程控制功能】
ssh aaa@192.168.73.241              【  ssh   远程计算机用户名@远程计算机IP地址   (默认端口是 22) 】
The authenticity of host '192.168.73.241 (192.168.73.241)' can't be established.
ECDSA key fingerprint is SHA256:muB5tHgJPeaYXqoQ2L00qAHtlZ6FTN6Wi0fxt+Q7TD8.
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added '192.168.73.241' (ECDSA) to the list of known hosts.
Password:                             【在此输入 aaa远程计算机用户名 对应的密码 】
Last login: Thu Aug 16 15:16:44 2018
[/Users/aaa]➜  ~       【 完成登录】






```
<img src="//../zimage/system/macos/02_shellcommand/ssh.png">


```
SSH帮助手册：
ssh -V             // OpenSSH_7.4p1, LibreSSL 2.5.0
ssh -h
usage: ssh [-1246AaCfGgKkMNnqsTtVvXxYy] [-b bind_address] [-c cipher_spec]
           [-D [bind_address:]port] [-E log_file] [-e escape_char]
           [-F configfile] [-I pkcs11] [-i identity_file]
           [-J [user@]host[:port]] [-L address] [-l login_name] [-m mac_spec]
           [-O ctl_cmd] [-o option] [-p port] [-Q query_option] [-R address]
           [-S ctl_path] [-W host:port] [-w local_tun[:remote_tun]]
           [user@]hostname [command]



AUTOSSH帮助手册：
autossh   
usage: autossh [-V] [-M monitor_port[:echo_port]] [-f] [SSH_OPTIONS]

    -M specifies monitor port. May be overridden by environment
       variable AUTOSSH_PORT. 0 turns monitoring loop off.
       Alternatively, a port for an echo service on the remote
       machine may be specified. (Normally port 7.)
    -f run in background (autossh handles this, and does not
       pass it to ssh.)
    -V print autossh version and exit.

Environment variables are:
    AUTOSSH_GATETIME    - how long must an ssh session be established
                          before we decide it really was established
                          (in seconds). Default is 30 seconds; use of -f
                          flag sets this to 0.
    AUTOSSH_LOGFILE     - file to log to (default is to use the syslog
                          facility)
    AUTOSSH_LOGLEVEL    - level of log verbosity
    AUTOSSH_MAXLIFETIME - set the maximum time to live (seconds)
    AUTOSSH_MAXSTART    - max times to restart (default is no limit)
    AUTOSSH_MESSAGE     - message to append to echo string (max 64 bytes)
    AUTOSSH_PATH        - path to ssh if not default
    AUTOSSH_PIDFILE     - write pid to this file
    AUTOSSH_POLL        - how often to check the connection (seconds)
    AUTOSSH_FIRST_POLL  - time before first connection check (seconds)
    AUTOSSH_PORT        - port to use for monitor connection
    AUTOSSH_DEBUG       - turn logging to maximum verbosity and log to
                          stderr


```



## axel
```
axel简介：
Axel试图通过对一个文件使用多个连接来加速下载过程，类似于DownThemAll和其他著名程序。它还可以在一次下载中使用多个镜像。使用Axel，您可以更快地从Internet上获得文件。因此，Axel可以将下载速度提高到60%(根据某些测试，大约是60%)。Axel尽量轻巧，所以它可能作为一个wget克隆(和其他基于控制台的程序)在字节关键系统上有用。Axel支持HTTP、HTTPS、FTP和FTPS协议。

axel是Linux下一个不错的HTTP/ftp高速下载工具。支持多线程下载、断点续传，且可以从多个地址或者从一个地址的多个连接来下载同一个文件。适合网速不给力时多线程下载提高下载速度。比如在国内VPS或服务器上下载lnmp一键安装包用Axel就比wget快。


http://man.linuxde.net/axel



【 Mac安装命令： 】brew install axel


```
# B


##  bastet  俄罗斯方块游戏
```

安装命令(Mac)： brew install bastet 
具体看截图：


```

<img src="//../zimage/system/macos/02_shellcommand/bastet.png">


## bcal 二进制计算工具
```
简介:  存储转换与表达式计算器
根据SI标准，1kB=1000B（字节, Byte），而根据IEC标准 [2-4]  ，1kiB=1024B。
Storage conversion and expression calculator
https://github.com/jarun/bcal



安装命令：   brew install  bcal


```



```
// bcal 帮助手册
bcal -h          
usage: bcal [-c N] [-f FORMAT] [-s bytes] [-m] [-b] [-d] [-h]
            [expression] [N [unit]] 

Storage expression calculator.

positional arguments:
 expression  evaluate storage arithmetic expression
             +, -, *, / operators with decimal or hex operands
             unit can be multiplied or divided by +ve integers
             +, -, / work with two unit operands
             Examples:
               bcal "(5kb+2mb)/3"
               bcal "5 tb / 12"
               bcal "2.5mb*3"
               bcal "(2giB * 2) / 2kib"
 N [unit]    capacity in B/KiB/MiB/GiB/TiB/kB/MB/GB/TB
             see https://wiki.ubuntu.com/UnitsPolicy
             default unit is B (byte), case is ignored
             N can be decimal or '0x' prefixed hex value

optional arguments:
 -c N        show +ve integer N in binary, decimal and hex
 -f FORMAT   convert CHS to LBA or LBA to CHS
             formats are hyphen-separated
             LBA format:
               starts with 'l':
               lLBA-MAX_HEAD-MAX_SECTOR
             CHS format:
               starts with 'c':
               cC-H-S-MAX_HEAD-MAX_SECTOR
             omitted values are considered 0
             FORMAT 'c-50--0x12-' denotes:
               C = 0, H = 50, S = 0, MH = 0x12, MS = 0
             FORMAT 'l50-0x12' denotes:
               LBA = 50, MH = 0x12, MS = 0
             default MAX_HEAD: 16, default MAX_SECTOR: 63
 -s bytes    sector size [default 512]
 -m          show minimal output (e.g. decimal bytes)
 -b          list sizes of basic data types in bytes
 -d          enable debug information and logs
 -h          show this help and exit

Version 1.9
Copyright © 2016-2018 Arun Prakash Jana <engineerarun@gmail.com>
License: GPLv3
Webpage: https://github.com/jarun/bcal


```


```
【1】.计算存储单元表达式  存储相关的计算   bacl "表达式+-*/"
使用示例：
根据SI标准，1kB=1000B（字节, Byte），而根据IEC标准 [2-4]  ，1kiB=1024B。

bcal "2.5mb*3"

RESULT
                                 7500000 B    【表示存储字节B 大小】
            IEC 国际电工委员会
            IEC standard (base 2)   【以二进制表示的存储字节大小】

                        7.3242187500e+03 KiB
                        7.1525573730e+00 MiB
                        6.9849193096e-03 GiB
                        6.8212102633e-06 TiB
            国际单位制(法语:Système International d'Unités 符号:SI),
            SI standard (base 10) 【以十进制表示的存储字节大小】

                                    7500 kB
                        7.5000000000e+00 MB
                        7.5000000000e-03 GB
                        7.5000000000e-06 TB

ADDRESS
 (d) 7500000        【digital 数字十进制表示形式】
 (h) 0x7270e0       【hex 十六进制表示形式】

##################
bcal "5 tb / 12"
WARNING: result truncated
RESULT
                            416666666666 B

            IEC standard (base 2)

                        4.0690104167e+08 KiB
                        3.9736429850e+05 MiB
                        3.8805107276e+02 GiB
                        3.7895612574e-01 TiB

            SI standard (base 10)

                        4.1666666667e+08 kB
                        4.1666666667e+05 MB
                        4.1666666667e+02 GB
                        4.1666666667e-01 TB

ADDRESS
 (d) 416666666666
 (h) 0x610344c6aa
[/Users/aaa]➜  ~



```

```

【2】.转换存储容量到其他单位，并获得地址，LBA。

 bcal 48769[十进制或者oxFF十六进制]  N[B/KiB/MiB/GiB/TiB/kB/MB/GB/TB]  默认单位为 B
根据SI标准，1kB=1000B（字节, Byte），而根据IEC标准 [2-4]  ，1kiB=1024B。
所以输入的 KiB/MiB/GiB/TiB  是以 1kiB=1024B 为标准计算的结果
所以输入的 KB/MB/GB/TB  是以 1kB=1000B 为标准计算的结果  
所以作为程序员 我要以 KiB/MiB/GiB/TiB   才能达到我的想法

bcal 1KiB      // 以SI    1kB=1024B    为标准
                                    1024 B

            IEC standard (base 2)

                                       1 KiB
                        9.7656250000e-04 MiB
                        9.5367431641e-07 GiB
                        9.3132257462e-10 TiB

            SI standard (base 10)

                        1.0240000000e+00 kB
                        1.0240000000e-03 MB
                        1.0240000000e-06 GB
                        1.0240000000e-09 TB

ADDRESS
 (d) 1024
 (h) 0x400

LBA:OFFSET (sector size: 0x200)
 (d) 2:0
 (h) 0x2:0x0

############
bcal 1KB         // 以SI    1kB=1000B    为标准
                                    1000 B

            IEC standard (base 2)

                        9.7656250000e-01 KiB
                        9.5367431641e-04 MiB
                        9.3132257462e-07 GiB
                        9.0949470177e-10 TiB

            SI standard (base 10)

                                       1 kB
                        1.0000000000e-03 MB
                        1.0000000000e-06 GB
                        1.0000000000e-09 TB

ADDRESS
 (d) 1000
 (h) 0x3e8

LBA:OFFSET (sector size: 0x200)
 (d) 1:488
 (h) 0x1:0x1e8
 
 
 
############
bcal 1024KiB   
                                 1048576 B

            IEC standard (base 2)

                                    1024 KiB
                                       1 MiB  【1M】
                        9.7656250000e-04 GiB
                        9.5367431641e-07 TiB

            SI standard (base 10)

                        1.0485760000e+03 kB
                        1.0485760000e+00 MB
                        1.0485760000e-03 GB
                        1.0485760000e-06 TB

ADDRESS
 (d) 1048576    【十进制地址  十进制数值】
 (h) 0x100000    【十六进制地址  十六进制数值】

LBA:OFFSET (sector size: 0x200)   【偏移地址 】
 (d) 2048:0
 (h) 0x800:0x0


```



```

【3】. bcal -c   1231[十进制  ox十六进制 0b二进制 ] 显示转换结果

bcal -c 20140115
BASE CONVERSION
 (b) 0b1001100110101000001010011
 (d) 20140115
 (h) 0x1335053


bcal -c 0b1001100110101000001010011
BASE CONVERSION
 (b) 0b1001100110101000001010011
 (d) 20140115
 (h) 0x1335053



bcal -c 0x1335053
BASE CONVERSION
 (b) 0b1001100110101000001010011
 (d) 20140115
 (h) 0x1335053

```

```
【4】硬盘寻址模式  LBA  和   CHS 的相互转换


LBA  逻辑寻址模式：LBA(Logical Block Addressing)  在LBA地址中，地址不再表示实际硬盘的实际物理地址（柱面、磁头和扇区）。LBA编址方式将CHS这种三维寻址方式转变为一维的线性寻址，它把硬盘所有的物理扇区的C/H/S编号通过一定的规则转变为一线性的编号，系统效率得到大大提高，避免了烦琐的磁头/柱面/扇区的寻址方式。在访问硬盘时，由硬盘控制器再将这种逻辑地址转换为实际硬盘的物理地址


CHS（也称3D）物理寻址 ，对早期的磁盘有效 但是支持的硬盘容量有限 最大 8.4GB
https://blog.csdn.net/zztan/article/details/70861021


逻辑扇区号LBA的公式：

LBA(逻辑扇区号)=磁头数 × 每磁道扇区数 × 当前所在柱面号 + 每磁道扇区数 × 当前所在磁头号 + 当前所在扇区号 – 1


例如：CHS=0/0/1，则根据公式LBA=255 × 63 × 0 + 63 × 0 + 1 – 1= 0
也就是说物理0柱面0磁头1扇区，是逻辑0扇区。
也就是说  LBA就是扇区的编号，  按照磁道  柱面 和 磁头 从小到大的顺序编号





 LBA ==》 CHS    【LBA的数值以  'l'为开头 】
 bcal -f l500
LBA2CHS
  LBA:500  MAX_HEAD:16  MAX_SECTOR:63
  CHS: (d) 0 7 60, (h) 0x0 0x7 0x3c   既 CHS=0/7/60  【柱面/磁头/扇区】
  
bcal -f l0x600-18-0x7e
LBA2CHS
  LBA:1536  MAX_HEAD:18  MAX_SECTOR:126
  CHS: (d) 0 12 25, (h) 0x0 0xc 0x19  既 CHS=0/12/25  【柱面/磁头/扇区】
  
  
  
  
  CHS  ==》LBA     CHS地址 以 ‘c’  字符c开头

~ bcal -f c10-10-10
CHS2LBA
  C:10  H:10  S:10  MAX_HEAD:16  MAX_SECTOR:63
  LBA: (d) 10719, (h) 0x29df             既 LBA逻辑地址 = 10719
  
  
~ bcal -f c0x10-10-2-0x12
CHS2LBA
  C:16  H:10  S:2  MAX_HEAD:18  MAX_SECTOR:63
  LBA: (d) 18775, (h) 0x4957           既 LBA逻辑地址 = 18775
  
  

```

##   bettercap  中间人攻击软件框架 内网神器 黑客工具
```
bettercap:  Bettercap 是一款瑞士军刀，这款开源软件可用于网络攻击和监控
使用教程地址： https://blog.csdn.net/u012570105/article/details/80561778
github地址: github地址

安装命令(Mac)： brew install bettercap


```

```
使用命令： 
sudo bettercap       // 该命令进入shell交互模式
```

<img src="//../zimage/system/macos/02_shellcommand/battercap2.png"/>
<img src="//../zimage/system/macos/02_shellcommand/battercap.png"/>

<img src="//../zimage/system/macos/02_shellcommand/battercap1.png"/>
```
192.168.72.0/21 > 192.168.73.241  » help

                  help : 查看帮助文档
                active : 查看当前所有活动可用的模块
                  quit :  关闭当前交互模式退出离开 回到Linux的Shell
         sleep SECONDS : 睡眠Second 秒
         get NAME :  获得一个定义变量的值
        set NAME VALUE :   设置一个变量值， 可用GET获得
  read VARIABLE PROMPT : Show a PROMPT to ask the user for input that will be saved inside VARIABLE.
                 clear : Clear the screen.
        include CAPLET : Load and run this caplet in the current session.
             ! COMMAND : 执行Linux shell下的命令
        alias MAC NAME : Assign an alias to a given endpoint given its MAC address.

     
Modules   【battercap 包含的模块  not running表示没有运行】

      any.proxy > not running
       api.rest > not running
      arp.spoof > not running
      ble.recon > not running
    dhcp6.spoof > not running
      dns.spoof > not running
  events.stream > running
            gps > not running
     http.proxy > not running
    http.server > not running
    https.proxy > not running
    mac.changer > not running
   mysql.server > not running
      net.probe > not running
      net.recon > running
      net.sniff > not running
   packet.proxy > not running
       syn.scan > not running
      tcp.proxy > not running
         ticker > not running
         update > not running
           wifi > not running
            wol > not running


     help events.stream :  查看模块events.stream 帮助文档
     


【1】    help  any.proxy 
192.168.72.0/21 > 192.168.73.241  » help  any.proxy

any.proxy (not running): A firewall redirection to any custom proxy.

   any.proxy on : Start the custom proxy redirection.
  any.proxy off : Stop the custom proxy redirection.

  Parameters

  any.proxy.dst_address : Address where the proxy is listening. (default=<interface address>)
     any.proxy.dst_port : Port where the proxy is listening. (default=8080)
        any.proxy.iface : Interface to redirect packets from. (default=<interface name>)
     any.proxy.protocol : Proxy protocol. (default=TCP)
  any.proxy.src_address : Leave empty to intercept any source address. (default=)
     any.proxy.src_port : Remote port to redirect when the module is activated. (default=80)



【2】  help     api.rest 

192.168.72.0/21 > 192.168.73.241  » help     api.rest

api.rest (not running): Expose a RESTful API.

   api.rest on : Start REST API server.
  api.rest off : Stop REST API server.

  Parameters

                         api.rest.address : Address to bind the API REST server to. (default=<interface address>)
                     api.rest.certificate : API TLS certificate. (default=~/.bcap-api.rest.certificate.pem)
                api.rest.certificate.bits : Number of bits of the RSA private key of the generated HTTPS certificate. (default=4096)
          api.rest.certificate.commonname : Common Name field of the generated HTTPS certificate. (default=bettercap)
             api.rest.certificate.country : Country field of the generated HTTPS certificate. (default=US)
            api.rest.certificate.locality : Locality field of the generated HTTPS certificate. (default=)
        api.rest.certificate.organization : Organization field of the generated HTTPS certificate. (default=bettercap devteam)
  api.rest.certificate.organizationalunit : Organizational Unit field of the generated HTTPS certificate. (default=https://bettercap.org/)
                             api.rest.key : API TLS key (default=~/.bcap-api.rest.key.pem)
                        api.rest.password : API authentication password. (default=)
                            api.rest.port : Port to bind the API REST server to. (default=8083)
                        api.rest.username : API authentication username. (default=)
                       api.rest.websocket : If true the /api/events route will be available as a websocket endpoint instead of HTTPS. (default=false)
                       
                       
                       
                       
                       
【3】    help      arp.spoof 
192.168.72.0/21 > 192.168.73.241  » help      arp.spoof

arp.spoof (not running): Keep spoofing selected hosts on the network.

   arp.spoof on : Start ARP spoofer.
     arp.ban on : Start ARP spoofer in ban mode, meaning the target(s) connectivity will not work.
  arp.spoof off : Stop ARP spoofer.
    arp.ban off : Stop ARP spoofer.

  Parameters

   arp.spoof.internal : If true, local connections among computers of the network will be spoofed, otherwise only connections going to and coming from the external network. (default=false)
    arp.spoof.targets : Comma separated list of IP addresses, MAC addresses or aliases to spoof, also supports nmap style IP ranges. (default=<entire subnet>)
  arp.spoof.whitelist : Comma separated list of IP addresses, MAC addresses or aliases to skip while spoofing. (default=)



【4】    help      ble.recon 
192.168.72.0/21 > 192.168.73.241  » help      ble.recon

ble.recon (not running): Bluetooth Low Energy devices discovery.

   ble.recon on : Start Bluetooth Low Energy devices discovery.    【This component is not supported on this OS】
  ble.recon off : Stop Bluetooth Low Energy devices discovery.
  
  
  
  
【5】    help    dhcp6.spoof 
192.168.72.0/21 > 192.168.73.241  » help    dhcp6.spoof

dhcp6.spoof (not running): Replies to DHCPv6 messages, providing victims with a link-local IPv6 address and setting the attackers host as default DNS server (https://github.com/fox-it/mitm6/).

   dhcp6.spoof on : Start the DHCPv6 spoofer in the background.
  dhcp6.spoof off : Stop the DHCPv6 spoofer in the background.

  Parameters

  dhcp6.spoof.domains : Comma separated values of domain names to spoof. (default=microsoft.com, google.com, facebook.com, apple.com, twitter.com)
  
  
  
  
【6】    help      dns.spoof 
192.168.72.0/21 > 192.168.73.241  » help      dns.spoof

dns.spoof (not running): Replies to DNS messages with spoofed responses.

   dns.spoof on : Start the DNS spoofer in the background.
  dns.spoof off : Stop the DNS spoofer in the background.

  Parameters

  dns.spoof.address : IP address to map the domains to. (default=<interface address>)
      dns.spoof.all : If true the module will reply to every DNS request, otherwise it will only reply to the one targeting the local pc. (default=false)
  dns.spoof.domains : Comma separated values of domain names to spoof. (default=*)
  
  
  
  
【7】   help  events.stream 
192.168.72.0/21 > 192.168.73.241  » help  events.stream

events.stream (running): Print events as a continuous stream.

             events.stream on : Start events stream.
            events.stream off : Stop events stream.
           events.show LIMIT? : Show events stream.
  events.waitfor TAG TIMEOUT? : Wait for an event with the given tag either forever or for a timeout in seconds.
         events.ignore FILTER : Events with an identifier matching this filter will not be shown (use multiple times to add more filters).
        events.include FILTER : Used to remove filters passed with the events.ignore command.
               events.filters : Print the list of filters used to ignore events.
                 events.clear : Clear events stream.

  Parameters

  events.stream.output : If not empty, events will be written to this file instead of the standard output. (default=)





【8】   help    gps 
192.168.72.0/21 > 192.168.73.241  » help    gps

gps (not running): A module talking with GPS hardware on a serial interface.

    gps on : Start acquiring from the GPS hardware.  【open /dev/ttyUSB0: no such file or directory】
   gps off : Stop acquiring from the GPS hardware.
  gps.show : Show the last coordinates returned by the GPS hardware.

  Parameters

  gps.baudrate : Baud rate of the GPS serial device. (default=19200)
    gps.device : Serial device of the GPS hardware. (default=/dev/ttyUSB0)
    
    
    
    
【9】    help     http.proxy 
192.168.72.0/21 > 192.168.73.241  » help     http.proxy

http.proxy (not running): A full featured HTTP proxy that can be used to inject malicious contents into webpages, all HTTP traffic will be redirected to it.

   http.proxy on : Start HTTP proxy.
  http.proxy off : Stop HTTP proxy.

  Parameters

            http.port : HTTP port to redirect when the proxy is activated. (default=80)
   http.proxy.address : Address to bind the HTTP proxy to. (default=<interface address>)
  http.proxy.injectjs : URL, path or javascript code to inject into every HTML page. (default=)
      http.proxy.port : Port to bind the HTTP proxy to. (default=8080)
    http.proxy.script : Path of a proxy JS script. (default=)
  http.proxy.sslstrip : Enable or disable SSL stripping. (default=false)





【10】   help    http.server 
192.168.72.0/21 > 192.168.73.241  » help    http.server

http.server (not running): A simple HTTP server, to be used to serve files and scripts across the network.

   http.server on : Start httpd server.
  http.server off : Stop httpd server.

  Parameters

                         http.server.address : Address to bind the http server to. (default=<interface address>)
                     http.server.certificate : TLS certificate file, if not empty will configure this as a HTTPS server (will be auto generated if filled but not existing). (default=)
                http.server.certificate.bits : Number of bits of the RSA private key of the generated HTTPS certificate. (default=4096)
          http.server.certificate.commonname : Common Name field of the generated HTTPS certificate. (default=bettercap)
             http.server.certificate.country : Country field of the generated HTTPS certificate. (default=US)
            http.server.certificate.locality : Locality field of the generated HTTPS certificate. (default=)
        http.server.certificate.organization : Organization field of the generated HTTPS certificate. (default=bettercap devteam)
  http.server.certificate.organizationalunit : Organizational Unit field of the generated HTTPS certificate. (default=https://bettercap.org/)
                             http.server.key : TLS key file, if not empty will configure this as a HTTPS server (will be auto generated if filled but not existing). (default=)
                            http.server.path : Server folder. (default=.)
                            http.server.port : Port to bind the http server to. (default=80)
                            
                            
                            
                            
【11】    help    https.proxy 
192.168.72.0/21 > 192.168.73.241  » help    https.proxy

https.proxy (not running): A full featured HTTPS proxy that can be used to inject malicious contents into webpages, all HTTPS traffic will be redirected to it.

   https.proxy on : Start HTTPS proxy.
  https.proxy off : Stop HTTPS proxy.

  Parameters

                                  https.port : HTTPS port to redirect when the proxy is activated. (default=443)
                         https.proxy.address : Address to bind the HTTPS proxy to. (default=<interface address>)
                     https.proxy.certificate : HTTPS proxy certification authority TLS certificate file. (default=~/.bettercap-ca.cert.pem)
                https.proxy.certificate.bits : Number of bits of the RSA private key of the generated HTTPS certificate. (default=4096)
          https.proxy.certificate.commonname : Common Name field of the generated HTTPS certificate. (default=Go Daddy Secure Certificate Authority - G2)
             https.proxy.certificate.country : Country field of the generated HTTPS certificate. (default=US)
            https.proxy.certificate.locality : Locality field of the generated HTTPS certificate. (default=Scottsdale)
        https.proxy.certificate.organization : Organization field of the generated HTTPS certificate. (default=GoDaddy.com, Inc.)
  https.proxy.certificate.organizationalunit : Organizational Unit field of the generated HTTPS certificate. (default=https://certs.godaddy.com/repository/)
                        https.proxy.injectjs : URL, path or javascript code to inject into every HTML page. (default=)
                             https.proxy.key : HTTPS proxy certification authority TLS key file. (default=~/.bettercap-ca.key.pem)
                            https.proxy.port : Port to bind the HTTPS proxy to. (default=8083)
                          https.proxy.script : Path of a proxy JS script. (default=)
                        https.proxy.sslstrip : Enable or disable SSL stripping. (default=false)
                        
                        
                        
                        
【12】    help    mac.changer 
192.168.72.0/21 > 192.168.73.241  » help    mac.changer

mac.changer (not running): Change active interface mac address.

   mac.changer on : Start mac changer module.
  mac.changer off : Stop mac changer module and restore original mac address.

  Parameters

  mac.changer.address : Hardware address to apply to the interface. (default=<random mac>)
    mac.changer.iface : Name of the interface to use. (default=<interface name>)
    
    
    
    
【13】   help  mysql.server 
192.168.72.0/21 > 192.168.73.241  » help  mysql.server

mysql.server (not running): A simple Rogue MySQL server, to be used to exploit LOCAL INFILE and read arbitrary files from the client.

   mysql.server on : Start mysql server. 【listen tcp 192.168.73.241:3306: bind: can't assign requested address】
  mysql.server off : Stop mysql server.

  Parameters

  mysql.server.address : Address to bind the mysql server to. (default=<interface address>)
   mysql.server.infile : File you want to read. UNC paths are also supported. (default=/etc/passwd)
     mysql.server.port : Port to bind the mysql server to. (default=3306)
     
     
     
     
【14】  help     net.probe 
192.168.72.0/21 > 192.168.73.241  » help     net.probe

net.probe (not running): Keep probing for new hosts on the network by sending dummy UDP packets to every possible IP on the subnet.

   net.probe on : Start network hosts probing in background.
  net.probe off : Stop network hosts probing in background.

  Parameters

  net.probe.throttle : If greater than 0, probe packets will be throttled by this value in milliseconds. (default=10)






【15】    help     net.recon 
192.168.72.0/21 > 192.168.73.241  » help     net.recon

net.recon (running): Read periodically the ARP cache in order to monitor for new hosts on the network.

      net.recon on : Start network hosts discovery.
     net.recon off : Stop network hosts discovery.
          net.show : Show cache hosts list (default sorting by ip).
  net.show by seen : Show cache hosts list (sort by last seen).
  net.show by sent : Show cache hosts list (sort by sent packets).
  net.show by rcvd : Show cache hosts list (sort by received packets).
  
  
  
  
【16】  help  net.sniff 
192.168.72.0/21 > 192.168.73.241  » help  net.sniff

net.sniff (not running): Sniff packets from the network.

  net.sniff stats : Print sniffer session configuration and statistics.
     net.sniff on : Start network sniffer in background.
    net.sniff off : Stop network sniffer in background.

  Parameters

   net.sniff.filter : BPF filter for the sniffer. (default=not arp)
    net.sniff.local : If true it will consider packets from/to this computer, otherwise it will skip them. (default=false)
   net.sniff.output : If set, the sniffer will write captured packets to this file. (default=)
   net.sniff.regexp : If set, only packets matching this regular expression will be considered. (default=)
   net.sniff.source : If set, the sniffer will read from this pcap file instead of the current interface. (default=)
  net.sniff.verbose : If true, every captured and parsed packet will be sent to the events.stream for displaying, otherwise only the ones parsed at the application layer (sni, http, etc). (default=true)
  
  
  
  
  
【17】  help  packet.proxy 
192.168.72.0/21 > 192.168.73.241  » help  packet.proxy

packet.proxy (not running): Not supported on this MacOS



【18】   help      syn.scan 
192.168.72.0/21 > 192.168.73.241  » help      syn.scan

syn.scan (not running): A module to perform SYN port scanning.

  syn.scan IP-RANGE [START-PORT] [END-PORT] : Perform a syn port scanning against an IP address within the provided ports range.






【19】     help     tcp.proxy 
192.168.72.0/21 > 192.168.73.241  » help     tcp.proxy

tcp.proxy (not running): A full featured TCP proxy and tunnel, all TCP traffic to a given remote address and port will be redirected to it.

   tcp.proxy on : Start TCP proxy.
  tcp.proxy off : Stop TCP proxy.

  Parameters

         tcp.address : Remote address of the TCP proxy. (default=)
            tcp.port : Remote port to redirect when the TCP proxy is activated. (default=443)
   tcp.proxy.address : Address to bind the TCP proxy to. (default=<interface address>)
      tcp.proxy.port : Port to bind the TCP proxy to. (default=8443)
    tcp.proxy.script : Path of a TCP proxy JS script. (default=)
  tcp.tunnel.address : Address to redirect the TCP tunnel to (optional). (default=)
     tcp.tunnel.port : Port to redirect the TCP tunnel to (optional). (default=0)
     
     
     
     
     
【20】     help       ticker 
192.168.72.0/21 > 192.168.73.241  » help       ticker

ticker (not running): A module to execute one or more commands every given amount of seconds.

   ticker on : Start the ticker.
  ticker off : Stop the ticker.

  Parameters

  ticker.commands : List of commands separated by a ; (default=clear; net.show; events.show 20)
    ticker.period : Ticker period in seconds (default=1)
    
    
    
    
【21】    help       update 
192.168.72.0/21 > 192.168.73.241  » help       update

update (not running): A module to check for bettercap's updates.

  update.check on : Check latest available stable version and compare it with the one being used.






【22】    help        wifi 
192.168.72.0/21 > 192.168.73.241  » help        wifi

wifi (not running): A module to monitor and perform wireless attacks on 802.11.

       wifi.recon on : Start 802.11 wireless base stations discovery and channel hopping.
      wifi.recon off : Stop 802.11 wireless base stations discovery and channel hopping.
      wifi.recon MAC : Set 802.11 base station address to filter for.
    wifi.recon clear : Remove the 802.11 base station filter.
   wifi.deauth BSSID : Start a 802.11 deauth attack, if an access point BSSID is provided, every client will be deauthenticated, otherwise only the selected client. Use a broadcast BSSID (ff:ff:ff:ff:ff:ff) to iterate every access point with at least one client and start a deauth attack for each one.
             wifi.ap : Inject fake management beacons in order to create a rogue access point.
           wifi.show : Show current wireless stations list (default sorting by essid).
  wifi.recon.channel : WiFi channels (comma separated) or 'clear' for channel hopping.

  Parameters

       wifi.ap.bssid : BSSID of the fake access point. (default=<random mac>)
     wifi.ap.channel : Channel of the fake access point. (default=1)
  wifi.ap.encryption : If true, the fake access point will use WPA2, otherwise it'll result as an open AP. (default=true)
        wifi.ap.ssid : SSID of the fake access point. (default=FreeWiFi)
     wifi.hop.period : If channel hopping is enabled (empty wifi.recon.channel), this is the time in milliseconds the algorithm will hop on every channel (it'll be doubled if both 2.4 and 5.0 bands are available). (default=250)
    wifi.skip-broken : If true, dot11 packets with an invalid checksum will be skipped. (default=true)
    wifi.source.file : If set, the wifi module will read from this pcap file instead of the hardware interface. (default=)






【23】      help          wol 
192.168.72.0/21 > 192.168.73.241  » help          wol

wol (not running): A module to send Wake On LAN packets in broadcast or to a specific MAC.

  wol.eth MAC : Send a WOL as a raw ethernet packet of type 0x0847 (if no MAC is specified, ff:ff:ff:ff:ff:ff will be used).
  wol.udp MAC : Send a WOL as an IPv4 broadcast packet to UDP port 9 (if no MAC is specified, ff:ff:ff:ff:ff:ff will be used).




     
```

```
实践应用： 
未完待续




```


##  bitcoin   P2P支付网络
```
安装命令( Mac ):  brew info  bitcoin
官网：  https://bitcoin.org/

```


##  bittwist    数据包编辑工具 bittwist
```
bittwiste 简介 : bittwiste是数据包重放工具bittwist的一个工具。该工具可以编辑修改PCAP抓包文件。
该工具提供数据包过滤功能，如根据范围和时间过滤。同时，该工具支持数据包的截断，并添加数据载荷。
对于ETH、ARP、IP、ICMP、TCP和UDP类型数据包，渗透测试人员还可以修改其对应的包头。pcap 编辑工具


安装命令：  brew install bittwist



```


```
【  bittwist 帮助手册  】

bittwist -h
bittwist version 2.0
libpcap version 1.8.1 -- Apple version 67.60.1
Usage: bittwist [-d] [-v] [-i interface] [-s length] [-l loop] [-c count]
                [-m speed] [-r rate] [-p sleep] [-h] pcap-file(s)

Options:
 -d             Print a list of network interfaces available.
 -v             Print timestamp for each packet.
 -vv            Print timestamp and hex data for each packet.
 -i interface   Send 'pcap-file(s)' out onto the network through 'interface'.
 -s length      Packet length to send. Set 'length' to:
                     0 to send the actual packet length. This is the default.
                    -1 to send the captured length.
                or any other value from 14 to 1514.
 -l loop        Send 'pcap-file(s)' out onto the network for 'loop' times.
                Set 'loop' to 0 to send 'pcap-file(s)' until stopped.
                To stop, type Control-C.
 -c count       Send up to 'count' packets.
                Default is to send all packets from 'pcap-file(s)'.
 -m speed       Set interval multiplier to 'speed'.
                Set 'speed' to 0 or less to send the next packet immediately.
                Minimum positive value for 'speed' is 0.000001.
 -r rate        Limit the sending to 'rate' Mbps.
                Value for 'rate' must be between 1 to 10000.
                This option is meant to limit the maximum packet throughput.
                If you want to send packets at line rate of 100Mbps,
                try -m 0 -r 100
 -p sleep       Set interval to 'sleep' (in seconds), ignoring the actual
                interval.
                Value for 'sleep' must be between 1 to 2146.
 -h             Print version information and usage.
bittwist -d
1. en0
2. bridge0
3. p2p0
4. awdl0
5. utun0
6. en1
7. utun1
8. en2
9. lo0
10. gif0
11. stf0



```

```
【  bittwiste 帮助手册  】
帮助手册： bittwiste  和  bittwist
BITTWISTE(1)							  BITTWISTE(1)
NAME
       bittwiste -- pcap capture file editor

SYNOPSIS
       bittwiste [ -I input ] [ -O output ] [ -L layer ] [ -X payload ]
		 [ -C ] [ -M linktype ] [ -D offset ] [ -R range ]
		 [ -S timeframe ] [ -T header ]
		 [ header-specific-options ] [ -h ]

DESCRIPTION
       This document describes the bittwiste program, the pcap(3) capture file
       editor. Bittwiste is designed to work only with	Ethernet  frame,  e.g.
       link  type  DLT_EN10MB  in  pcap(3),  with a maximum frame size of 1514
       bytes which is equivalent to a MTU of 1500 bytes, 14 bytes for Ethernet
       header.

       Bittwiste  can  currently  edit	Ethernet,  ARP, IP, ICMP, TCP, and UDP
       headers. If run with the -X flag, you can append your own payload after
       any  of	these  headers;  specified using the -L and -T flag. Bittwiste
       will, if not run with the -C flag, recalculate the  checksums  for  IP,
       ICMP,  TCP,  and  UDP  packets, except for the last fragment of a frag-
       mented IP datagram; bittwiste does not currently support checksum  cor-
       rection	for the last fragment of a fragmented IP datagram. While pars-
       ing the packets in a trace file, bittwiste will	skip,  i.e.  write  to
       output  file  as  is, any truncated packet, for example, an ICMP packet
       with a captured length of 25 bytes (we need at least 28 bytes; 14 bytes
       for  Ethernet  header,  minimum 20 bytes for IP header, and 4 bytes for
       ICMP header) does not give enough information on its  ICMP  header  for
       bittwiste  to  read and modify it. In this case, you can utilize the -L
       and -T flag to copy the original packet up to its IP header and	append
       your  customized  ICMP header and data to the packet using the -X flag.
       When specifying payload that covers the ICMP, TCP or UDP header and its
       data, you can use zeros, e.g. 0000 for 2 bytes of zeros, for the header
       checksum which is then corrected automatically by bittwiste.

       In order to simplify the way options are specified, you can  only  edit
       packets	of  a  specific  type supplied to the -T flag per execution of
       bittwiste on a trace file. In addition, the -T flag  must  appear  last
       among  the general options which are the -I, -O, -L, -X, -C, -M, -D, -R
       and -S flag.

OPTIONS
       -I input
	      Input pcap based trace file.

       -O output
	      Output trace file.

       -L layer
	      Copy up to the specified layer and discard the  remaining  data.
	      Value for layer must be either 2, 3 or 4 where 2 for Ethernet, 3
	      for ARP or IP, and 4 for ICMP, TCP or UDP.

       -X payload
	      Append payload in hex digits to the end of each packet.
	      Example: -X 0302aad1
	      -X flag is ignored if -L and -T flag are not specified.

       -C     Specify this flag to disable checksum correction. Checksum  cor-
	      rection  is applicable for non-fragmented IP, ICMP, TCP, and UDP
	      packets only.

       -M linktype
	      Replace the linktype stored in the pcap file header.  Typically,
	      value for linktype is 1 for Ethernet.
	      Example: -M 12 (for raw IP), -M 51 (for PPPoE)

	      For the complete list, see:
	      http://www.tcpdump.org/linktypes.html

       -D offset
	      Delete the specified byte offset from each packet.
	      First byte (starting from link layer header) starts from 1.
	      -L, -X, -C and -T flag are ignored if -D flag is specified.
	      Example: -D 15-40, -D 10 or -D 18-9999

       -R range
	      Save only the specified range of packets.
	      Example: -R 5-21 or -R 9

       -S timeframe
	      Save  only the packets within the specified timeframe with up to
	      one-second resolution using DD/MM/YYYY,HH:MM:SS  as  the format
	      for start and end time in timeframe.
	      Example: -S 22/10/2006,21:47:35-24/10/2006,13:16:05
	      -S flag is evaluated after -R flag.

       -T header
	      Edit  only  the  specified  header. Possible keywords for header
	      are, eth, arp, ip, icmp, tcp, or udp. -T flag must  appear  last
	      among the general options.

       -h     Print version information and usage.

         header-specific-options
	      Each  packet  that  matches  the type supplied to the -T flag is
	      modified based on the options described below:

	      Options for eth (RFC 894):

	      -d dmac or omac,nmac
		     Destination MAC address. Example: -d 00:08:55:64:65:6a
		     If omac and nmac are specified instead, all occurences of
		     omac  in  the  destination  MAC  address  field  will  be
		     replaced with nmac.

	      -s smac or omac,nmac
		     Source MAC address. Example: -s 00:13:20:3e:ab:cf
		     If omac and nmac are specified instead, all occurences of
		     omac  in  the  source  MAC address field will be replaced
		     with nmac.

	      -t type
		     EtherType. Possible keywords for type  are,  ip  and  arp
		     only.

	      Options for arp (RFC 826):

	      -o opcode
		     Operation	code  in integer value between 0 to 65535. For
		     example, you can set opcode to 1 for ARP request,2  for ARP reply.

	      -s smac or omac,nmac
		     Sender MAC address. Example: -s 00:13:20:3e:ab:cf
		     If omac and nmac are specified instead, all occurences of
		     omac in the sender MAC address  field  will  be  replaced
		     with nmac.

	      -p sip or oip,nip
		     Sender IP address. Example: -p 192.168.0.1
		     If  oip  and nip are specified instead, all occurences of
		     oip in the sender IP address field will be replaced  with
		     nip.

	      -t tmac or omac,nmac
		     Target MAC address. Example: -t 00:08:55:64:65:6a
		     If omac and nmac are specified instead, all occurences of
		     omac in the target MAC address  field  will  be  replaced
		     with nmac.

	      -q tip or oip,nip
		     Target IP address. Example: -q 192.168.0.2
		     If  oip  and nip are specified instead, all occurences of
		     oip in the target IP address field will be replaced  with
		     nip.

	      Options for ip (RFC 791): 【IP的后置参数】

	      -i id
		     Identification in integer value between 0 to 65535.

	      -f flags
		     Control flags. Possible characters for flags are:

		     - : remove all flags
		     r : set the reserved flag
		     d : set the don't fragment flag
		     m : set the more fragment flag

		     Example: -f d
		     If  any of the flags is specified, all original flags are
		     removed automatically.

	      -o offset
		     Fragment offset in integer value between 0 to 7770. Value
		     for  offset represents the number of 64-bit segments con-
		     tained in earlier fragments which must  not  exceed  7770
		     (62160 bytes).

	      -t ttl
		     Time to live in integer value between 0 to 255 (millisec-
		     onds).

	      -p proto
		     Protocol number in integer value between 0 to  255.  Some
		     common protocol numbers are:

		     1	: Internet Control Message Protocol (ICMP)
		     6	: Transmission Control Protocol (TCP)
		     17 : User Datagram Protocol (UDP)

		     For the complete list, see:
		     http://www.iana.org/assignments/protocol-numbers

	      -s sip or oip,nip
		     Source IP address. Example: -s 192.168.0.1
		     If  oip  and nip are specified instead, all occurences of
		     oip in the source IP address field will be replaced  with
		     nip.

	      -d dip or oip,nip
		     Destination IP address. Example: -d 192.168.0.2
		     If  oip  and nip are specified instead, all occurences of
		     oip in the destination IP address field will be  replaced
		     with nip.

	      Options for icmp (RFC 792):  【 icmp 的后置参数】

	      -t type
		     Type  of  message in integer value between 0 to 255. Some
		     common messages are:

		     0	: Echo reply
		     3	: Destination unreachable
		     8	: Echo
		     11 : Time exceeded

		     For the complete list, see:
		     http://www.iana.org/assignments/icmp-parameters

	      -c code
		     Error code for this ICMP message in integer value between
		     0 to 255. For example, code for time exceeded message may
		     have one of the following values:

		     0 : transit TTL exceeded
		     1 : reassembly TTL exceeded

		     For the complete list, see:
		     http://www.iana.org/assignments/icmp-parameters

	      Options for tcp (RFC 793):  【 tcp 的后置参数】

	      -s sport or op,np
		     Source port number in integer value between 0  to  65535.
		     If op and np are specified instead, all occurrences of op
		     in the source port field will be replaced with np.

	      -d dport or op,np
		     Destination port number in integer  value  between  0  to
		     65535.  If  op  and  np are specified instead, all occur-
		     rences of op  in  the  destination  port  field  will  be
		     replaced with np.

	      -q seq
		     Sequence number in integer value between 0 to 4294967295.
		     If SYN control bit is set, e.g. character s  is  supplied
		     to  the -f flag, seq represents the initial sequence num-
		     ber (ISN) and the first data byte is ISN + 1.

	      -a ack
		     Acknowledgment number  in	integer  value between  0  to
		     4294967295.  If  ACK control bit is set, e.g. character a
		     is supplied to the -f flag, ack represents the  value  of
		     the  next	sequence number that the receiver is expecting
		     to receive.

	      -f flags
		     Control flags. Possible characters for flags are:

		     - : remove all flags
		     u : urgent pointer field is significant
		     a : acknowledgment field is significant
		     p : push function
		     r : resets the connection
		     s : synchronizes the sequence numbers
		     f : no more data from sender

		     Example: -f s
		     If any of the flags is specified, all original flags  are
		     removed automatically.

	      -w win
		     Window  size  in integer value between 0 to 65535. If ACK
		     control bit is set, e.g. character a is supplied  to  the
		     -f  flag, win represents the number of data bytes, begin-
		     ning with the one indicated in the acknowledgment number
		     field that the receiver is willing to accept.

	      -u urg
		     Urgent  pointer  in  integer value between 0 to 65535. If
		     URG control bit is set, e.g. character u is  supplied  to
		     the  -f flag, urg represents a pointer that points to the
		     first data byte following the urgent data.

	      Options for udp (RFC 768):   【 udp 的后置参数】

	      -s sport or op,np
		     Source port number in integer value between 0  to  65535.
		     If op and np are specified instead, all occurrences of op
		     in the source port field will be replaced with np.

	      -d dport or op,np
		     Destination port number in integer  value between  0  to
		     65535.  If  op  and  np are specified instead, all occur-
		     rences of op  in  the  destination  port  field  will  be
		     replaced with np.

SEE ALSO
       bittwist(1), pcap(3), tcpdump(1)








```


```

# 将目的ip从192.168.1.3修改为10.10.10.10
bittwiste -I in.pcap -O out.pcap -T ip -d 192.168.1.3,10.10.10.10
 


#  将一个 数据包生成多个数据包，并将所有的数据包的192.168.2.1 改为 192.168.2.X  
for((i=1;i<251;i++))
do
bittwiste -I  test.pcap -O test-${i}.pcap -T ip -s 192.168.2.1,192.168.2.${i} -d 192.168.2.1,192.168.2.${i}
done



```

## bmon   Linux 下一个强大的网络带宽监视和调试工具
```
bmon  : Linux 下一个强大的网络带宽监视和调试工具

安装命令：     brew  install bmon 

功能按键： 
d
i
↑
↓
```
<img src="//../zimage/system/macos/02_shellcommand/bmon.png">



## boxes   一个对字符串进行包装的工具  输出字符图像


```
boxes: boxes是对字符串进行外围的填充扩满 使得好看


安装命令：  brew install boxes

```

```
所有样式：    https://boxes.thomasjensen.com/box-designs.txt

 echo hello | boxes
/*********/
/* hello */
/*********/


echo Hello World!  | boxes -d ada-box
------------------
-- Hello World! --
------------------



echo Hello World!  | boxes -d ada-cmt
-- Hello World!


echo Hello World!  | boxes -d boxquote
,---- [  ]
| Hello World!
`----


echo Hello World!  | boxes -d boy
        .-"""-.
       / .===. \
       \/ 6 6 \/
       ( \___/ )
  _ooo__\_____/______
 /                   \
| Hello World!        |
 \_______________ooo_/
        |  |  |
        |_ | _|
        |  |  |
        |__|__|
        /-'Y'-\
       (__/ \__)
       


echo Hello World!  | boxes -d c
/****************/
/* Hello World! */
/****************/




echo Hello World!  | boxes -d c-cmt
/* Hello World! */




echo Hello World!  | boxes -d c-cmt2
/*
 *  Hello World!
 */
 
 
 
echo Hello World!  | boxes -d caml
(****************)
(* Hello World! *)
(****************)


echo Hello World!  | boxes -d capgirl
         .-"`*`"-.
        /_______; \
       (_________)\|
       / / a a \ \(_)
      / ( \___/ ) \
  _ooo\__\_____/__/____
 /                     \
| Hello World!          |
 \_________________ooo_/
      /           \
     /:.:.:.:.:.:.:\
         |  |  |
         \==|==/
         /-'Y'-\
        (__/ \__)
        
        
 echo Hello World! | boxes -d cc
 /****************
 * Hello World! *
 ****************/
 
 
 echo Hello World! | boxes -d  ccel
 ///////////////
Hello World! //
///////////////


echo Hello World! | boxes -d  columns
 __^__              __^__
( ___ )------------( ___ )
 | / | Hello World! | \ |
 |___|              |___|
(_____)------------(_____)




echo Hello World! | boxes -d diamonds -a hcvc
       /\          /\          /\
    /\//\\/\    /\//\\/\    /\//\\/\
 /\//\\\///\\/\//\\\///\\/\//\\\///\\/\
//\\\//\/\\///\\\//\/\\///\\\//\/\\///\\
\\//\/                            \/\\//
 \/                                  \/
 /\                                  /\
//\\          Hello World!          //\\
\\//                                \\//
 \/                                  \/
 /\                                  /\
//\\/\                            /\//\\
\\///\\/\//\\\///\\/\//\\\///\\/\//\\\//
 \/\\///\\\//\/\\///\\\//\/\\///\\\//\/
    \/\\//\/    \/\\//\/    \/\\//\/
       \/          \/          \/
       
       

   echo 你这只狗 | boxes -d dog -a c
          __   _,--="=--,_   __
         /  \."    .-.    "./  \
        /  ,/  _   : :   _  \/` \
        \  `| /o\  :_:  /o\ |\__/
         `-'| :="~` _ `~"=: |
            \`     (_)     `/
     .-"-.   \      |      /   .-"-.
.---{     }--|  /,.-'-.,\  |--{     }---.
 )  (_)_)_)  \_/`~-===-~`\_/  (_(_(_)  (
(             你这只狗              )
 )                                     (
'---------------------------------------'


echo Hello World! | boxes -d girl
         .-"""-.
        / .===. \
       / / a a \ \
      / ( \___/ ) \
  _ooo\__\_____/__/____
 /                     \
| Hello World!          |
 \_________________ooo_/
      /           \
     /:.:.:.:.:.:.:\
         |  |  |
         \==|==/
         /-'Y'-\
        (__/ \__)
        
        
echo  Hello World! | boxes -d headline
/*****************************/
/*  H e l l o   W o r l d !  */
/*****************************/



echo  Hello World! | boxes -d html
<!-- - - - - - - - -->
<!-- Hello World!  -->
<!-- - - - - - - - -->


echo  Hello World! | boxes -d html-cmt
<!-- Hello World! -->



echo  Hello World! | boxes -d ian_jones
       \\\///
      / _  _ \
    (| (.)(.) |)
.-.OOOo--()--oOOO.-.
|                  |
| Hello World!     |
|                  |
'-.oooO------------'
   (   )   Oooo.
    \ (    (   )
     \_)    ) /
           (_/
           
           
           
echo Hello World! | boxes -d  java-cmt
// Hello World!


echo Hello World! | boxes -d  javadoc
/**
 *  Hello World!
 */      




echo Hello World! | boxes -d mouse
   .--,       .--,
  ( (  \.---./  ) )
   '.__/o   o\__.'
      {=  ^  =}
       >  -  <
 __.""`-------`"".__
/                   \
\ Hello World!      /
/                   \
\___________________/
     ___)( )(___
    (((__) (__)))
    
    
    
echo Hello World! | boxes -d netdata
%{--------------+
 | Hello World! |
 +--------------%}


echo Hello World! | boxes -d nuke
        _ ._  _ , _ ._
      (_ ' ( `  )_  .__)
    ( (  (    )   `)  ) _)
   (__ (_   (_ . _) _) ,__)
       `~~`\ ' . /`~~`
       ,::: ;   ; :::,
      ':::::::::::::::'
 _jgs______/_ __ \__________
|                           |
| Hello World!              |
|___________________________|




echo Hello World! | boxes -d parchment
 __________________
/\                 \  
\_|                |  
  |  Hello World!  |  
  |                |  
  |  ______________|_ 
  \_/_______________/
 


echo  Hello World! |boxes -d peek -a c -s 40x11
/*       _\|/_
         (o o)
 +----oOO-{_}-OOo----------------------+
 |                                     |
 |                                     |
 |                                     |
 |            Hello World!             |
 |                                     |
 |                                     |
 |                                     |
 +------------------------------------*/
 
 
echo  Hello World! | boxes -d pound-cmt
‘#’Hello World!

echo  Hello World! | boxes -d right
Hello World!    /*@TJ@*/


echo  Hello World! | boxes -d santa
           .-"``"-.
          /______; \
         {_______}\|
         (/ a a \)(_)
         (.-.).-.)
  _ooo__(    ^    )_____
 /       '-.___.-'      \
| Hello World!           |
 \__________________ooo_/
         |_  |  _|  jgs
         \___|___/
         {___|___}
          |_ | _|
          /-'Y'-\
         (__/ \__)
         
         
echo  -e "奉天承运,皇帝诏约\n猪狗go die" | boxes -d scroll
 / ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\
|  /~~\                               /  /~~\ 
|\ \   |  奉天承运,皇帝诏约             | / /  | 
| \   /|  猪狗go die                  | \   /|
|  ~~  |                             |  ~~  |
|      |                             |      |
 \     |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|     /
  \   /                               \   /
   ~~~                                 ~~~
   
   
   
echo  Hello World! | boxes -d scroll-akn
 /"\/\_..---------------._/\/"\
(     _|| Hello World! ||_     )
 \_/\/ ||              || \/\_/
       ||              ||
 /"\/\_|----------------|_/\/"\
(     _|                |_     )
 \_/\/ `----------------' \/\_/
 
 
 
echo  Hello World! | boxes -d shell
################
# Hello World! #
################


   
echo  Hello World! | boxes -d simple
****************
* Hello World! *
****************


echo  Hello World! | boxes -d spring
      ,
  /\^/`\   Hello World!
 | \/   |
 | |    |                   jgs
 \ \    /                 _ _
  '\\//'                _{ ' }_
    ||                 { `.!.` }
    ||                 ',_/Y\_,'
    ||  ,                {_,_}
|\  ||  |\                 |
| | ||  | |              (\|  /)
| | || / /                \| //
 \ \||/ /                  |//
  `\\//`   \   \./    \ \\ |/ /
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 
echo  Hello World! | boxes -d stark1
=-=-=-=-=-=-=-=-=
^ Hello World!  ^
=-=-=-=-=-=-=-=-=



echo  Hello World! | boxes -d stark2
.:-@.:-@.:-@.:-@.:-@-:.
.:-@.:-@.:-@.:-@.:-@-:.
.:-@Hello World!   @-:.
.:-@.:-@.:-@.:-@.:-@-:.
.:-@.:-@.:-@.:-@.:-@-:.



echo  Hello World! | boxes -d stone
+--------------+
| Hello World! |
+--------------+




echo  Hello World! | boxes -d sunset
                .
           .    |    .
            \   |   /
        '.   \  '  /   .'
          '. .'`*`'. .'
<>...:::::::`.......`:::::::..<>
<>:Hello World!              :<>
<>:..........................:<>
<><><><><><><><><><><><><><><><>



echo  Hello World! | boxes -d tex-box
%==============%
% Hello World! %
%==============%


echo  Hello World! | boxes -d tex-cmt
% Hello World!



echo  Hello World! | boxes -d tjc
/*
 *  Hello World!
 *
* * * * * * * * *
 */
 
 
 
echo  Hello World! | boxes -d twisted
._____. ._____. ._______. ._____. ._____.
| ._. | | ._. | | .___. | | ._. | | ._. |
| !_| |_|_|_! | | !___! | | !_| |_|_|_! |
!___| |_______! !_______! !___| |_______!
.___|_|_| |___________________|_|_| |___.
| ._____| |_______________________| |_. |
| !_! | | |                   | | ! !_! |
!_____! | | Hello World!      | | !_____!
._____. | |                   | | ._____.
| ._. | | |                   | | | ._. |
| | | | | |                   | | | | | |
| !_! | | |                   | | ! !_! |
!_____! | |                   | | !_____!
._____. | |                   | | ._____.
| ._. | | |                   | | | ._. |
| !_| |_|_|___________________| |_|_|_! |
!___| |_______________________| |_______!
.___|_|_| |___. ._______. .___|_|_| |___.
| ._____| |_. | | .___. | | ._____| |_. |
| !_! | | !_! | | !___! | | !_! | | !_! |
!_____! !_____! !_______! !_____! !_____!



echo  Hello World! | boxes -d underline
// Hello World!
// ~~~~~~~~~~~~



echo  Hello World! | boxes -d unicornsay
   _________________________________
  /                                 \
  |  Hello World!                   |
  \___________________________  __'\
                              |/   \\
                               \    \\  .
                                    |\\/|
                                    / " '\
                                    . .   .
                                   /    ) |
                                  '  _.'  |
                                  '-'/    \
                                  
echo  Hello World! | boxes -d unicornthink
   _________________________________
  /                                 \
  |  Hello World!                   |
  \______________________________ '\
                             ()    \\
                               O    \\  .
                                 o  |\\/|
                                    / " '\
                                    . .   .
                                   /    ) |
                                  '  _.'  |
                                  '-'/    \
                           
                                  
echo  Hello World! | boxes -d whirly                            
 .+"+.+"+.+"+.+"+.
( Hello World!    )
 )               (
(                 )
 "+.+"+.+"+.+"+.+"
 
 
 
echo  Hello World! | boxes -d  xes                                
        <\/><\/>
    <\/></\></\><\/>
    </\>        </\>
 <\/>Hello World!  <\/>
 </\>              </\>
<\/>                <\/>
</\>                </\>
 <\/>              <\/>
 </\>              </\>
    <\/>        <\/>
    </\><\/><\/></\>
        </\></\>
```



## btfs (bittorrent filesystem) 比特流文件系统可实现边播边下载功能
```
btfs (bittorrent filesystem):
使用BTFS，您可以挂载任何.torrent文件或磁铁链接，然后将其用作文件树中的任何只读目录。
当应用程序读取文件时，文件的内容将按需下载。像ls、cat和cp这样的工具可以像预期的那样工作。
像VLC和mPlayer这样的应用程序也可以不受更改地工作。
使用BTFS，你可以将torrent文件或磁力链接安装为目录，然后将其作为文件树中的任何只读目录。
这些文件的内容将在应用程序读取时按需下载。由于BTFS在FUSE之上运行，因此不需要进入Linux内核。



安装命令( mac ): 
brew cask install osxfuse    ##  文件处理OSXFUSE一个软件包,一旦安装后,可以让你在Mac上使用这些第三方文件系统
brew install btfs


用法：
BTFS的使用非常简单。你只需要找到一个torrent文件或磁力链接，并将其安装在一个目录中。然后将torrent文件或磁力链接的内容安装在你选择的目录内。
当一个程序试图访问该文件进行读取时，实际的数据将会被按需下载。此外，像ls，cat和cp这样的工具也能按照预期的方式操作torrent。
像 vlc 和 mplayer 这样的应用程序也可以不加改变地工作。
玩家甚至可能不知道实际内容并非物理上的存在于本地磁盘中，而是根据需要从种子中进行收集。


1.创建一个目录来安装torrent / magnet链接：
mkdir mnt

2.安装  torrent/magnet链接：挂载到之前创建的文件夹
btfs video.torrent mnt         //   xxxxxxx.torrent
   
3. 进入目录
cd mnt

4.开始观看！
vlc <path-to-video.mp4>   xxxx.mp4

5. 要卸载BTFS文件系统，只需运行以下命令：
fuser mount -u mnt


git地址：   https://github.com/johang/btfs


使用示例：
mkdir mnt
btfs video.torrent mnt
cd mnt
vlc video.mp4

```


```
帮助手册：

DESCRIPTION
       btfs  allows  one  to mount any torrent file or a magnet link as a file
       system. The contents of the files will be downloaded on-demand as  they
       are read by applications.

OPTIONS
       -v   --version
	      print version

       -h   --help
	      print help

       -b   --browse-only
	      download metadata only

       -k   --keep
	      keep files after unmount

       --min-port=PORT
	      start of listen port range

       --max-port=PORT
	      end of listen port range

       --max-download-rate=RATE
	      maximum download rate (in kilobytes per second)

       --max-upload-rate=RATE
	      maximum upload rate (in kilobytes per second)

EXAMPLES
       mounting a torrent file:
	 btfs video.torrent ~/mnt

       mounting a magnet link:
	 btfs 'magnet:?xt=urn:btih:...' ~/mnt

       unmounting:
	 fusermount -u ~/mnt
```

##  bwm-ng 带宽监控器
```
bwm-ng（下一代带宽监控器）是另一款非常简单的实时网络负载监控工具，可以报告摘要信息，显示进出系统上所有可用网络接口的不同数据的传输速度。

安装命令： brew install bwm-ng 

```


```
bwm-ng 
/        iface                       Rx                   Tx                Total  
=================================================================================
          eth0:               1.18 KB/s            2.65 KB/s            3.82 KB/s           
            lo:               0.66 KB/s            0.66 KB/s            1.33 KB/s
         wlan1:               2.05 KB/s            0.73 KB/s            2.79 KB/s
--------------------------------------------------------------------------------- 
         total:               3.89 KB/s            4.04 KB/s            7.94 KB/s 


```

<img src="//../zimage/system/macos/02_shellcommand/bwm.png">







## brew | homebrew  (Mac-Shell专用)
```
【官网】    https://brew.sh/index_zh-cn
【可安装包】  https://formulae.brew.sh/formula/
【安装命令】
ruby -e "$(curl -fsSL https://raw.github.com/mxcl/homebrew/go)"


【常用命令】
brew search  xxxx          //  xxxx 表示你想要搜的软件包  brew search  abcde 

brew install xxxx软件包        //安装 软件包   例如： brew install wget
brew uninstall xxxx软件包       //  卸载软件包  

brew list                   //  显示安装软件列表
brew ls                   //  显示安装软件列表
brew desc  xxxx包名    // 显示安装包的描述作用   brew desc  git

brew  deps   xxxx包名      【显示一个安装包的依赖关系】  【例如: brew  deps  wget 】


brew  config        // 显示 homebrew的配置
brew  home         // 进入 homebrew官网

```



```
[brew 命令简介集合]
    
brew --cache     【/Users/aaa/Library/Caches/Homebrew】-- brew cache

brew --cellar    【/usr/local/Cellar】 -- brew cellar

brew --env         
【HOMEBREW_CC: clang
HOMEBREW_CXX: clang++
MAKEFLAGS: -j8
CMAKE_PREFIX_PATH: /usr/local
CMAKE_INCLUDE_PATH: /usr/include/libxml2:/System/Library/Frameworks/OpenGL.framework/Versions/Current/Headers
CMAKE_LIBRARY_PATH: /System/Library/Frameworks/OpenGL.framework/Versions/Current/Libraries
PKG_CONFIG_LIBDIR: /usr/lib/pkgconfig:/usr/local/Homebrew/Library/Homebrew/os/mac/pkgconfig/10.12
ACLOCAL_PATH: /usr/local/share/aclocal
PATH: /usr/local/Homebrew/Library/Homebrew/shims/mac/super:/usr/bin:/bin:/usr/sbin:/sbin】  
-- brew environment


brew --prefix      【/usr/local】-- where brew lives on this system

brew --repository   【/usr/local/Homebrew】  -- brew repository

brew --version     
【Homebrew 1.6.17
Homebrew/homebrew-core (git revision ddb17a; last commit 2018-08-12)】
 -- version information
 
 
brew audit         -- check formulae for Homebrew coding style
brew cat git  【显示安装包的详细信息】    -- display formula file for a formula
brew cleanup   【卸载不使用的旧的包】 -- uninstall unused and old versions of packages
brew commands     【显示多有的brew 内部可执行命令】 -- show a list of commands


brew  config       【显示 brew的配置 以及一些系统配置】
【HOMEBREW_VERSION: 1.6.17
ORIGIN: https://github.com/Homebrew/brew.git
HEAD: 526bece65feca74bf9cdc7f88b37e9a58548b09a
Last commit: 4 weeks ago
Core tap ORIGIN: https://github.com/Homebrew/homebrew-core
Core tap HEAD: ddb17a02ea1147ad63c3cac586fc492d7d0c30bb
Core tap last commit: 22 hours ago
HOMEBREW_PREFIX: /usr/local
HOMEBREW_DEV_CMD_RUN: 1
CPU: octa-core 64-bit ivybridge
Homebrew Ruby: 2.3.7 => /usr/local/Homebrew/Library/Homebrew/vendor/portable-ruby/2.3.7/bin/ruby
Clang: 9.0 build 900
Git: 2.17.0 => /usr/local/bin/git
Curl: 7.54.0 => /usr/bin/curl
Java: 1.8.0_141
macOS: 10.12.6-x86_64
CLT: 9.2.0.0.1.1510905681
Xcode: 9.2
XQuartz: N/A】 -- show homebrew and system configuration


brew  create       【创建一个命令方案?  不会用到的~】 -- create a new formula


brew  deps   xxxx包名      【显示一个安装包的依赖关系】  
【例如: brew  deps  wget
gettext
libidn2
libunistring
openssl 】
 -- list dependencies of formulae
 
 
 
brew desc  xxxx包名    
【例如  brew desc  wget   wget: Internet file retriever】
【例如 brew desc  git   git: Distributed revision control system 】
-- display a description of a formula
         


brew doctor    【检查安装包】    -- audits your installation for common issues
brew  edit          【 编辑安装包】-- edit a formula
brew fetch xxxx包名  【下载安装包到cache目录  不安装】        -- download formula resources to the cache

brew formula     
【显示安装包的安装路径】
【brew formula  git
/usr/local/Homebrew/Library/Taps/homebrew/homebrew-core/Formula/git.rb】
  -- the path for a formula
  
  
brew  gist-logs  xxxx安装包   【brew  gist-logs git 显示安装的Log信息】    -- generate a gist of the full build logs

brew home        【在浏览器打开homebrew的主页】  -- visit the homepage of a formula or the brew project

brew info  xxxx安装包   
【 查看安装包的消息信息 包括 desc描述 】        -- information about a formula

brew  install  xxxx安装包       【安装包 到本地系统  如：brew info  git         brew info  wget】
-- install a formula


brew leaves      
【显示已安装包中不依赖其他安装包的软件】 
【例如：    brew leaves 
aircrack-ng       automake     cocoapods
git        ideviceinstaller     ios-deploy
iperf      libtool     pkg-config    wget】
 -- show installed formulae that are not dependencies of anothe
 
 
brew  link  xxxx安装包  【测试安装包连通性】      -- link a formula



brew list      同  brew  ls   【查看已安装软件包】
【  例如： brew list
aircrack-ng		iperf			libxml2
autoconf		libidn2			libzip
automake		libimobiledevice	openssl
cocoapods		libplist		pkg-config
gettext			libtasn1		readline
git			libtool			sqlite
ideviceinstaller	libunistring		usbmuxd
ios-deploy		libusb			wget】
 -- list files in a formula or not-installed formulae



brew  log        【查看homebrew 这个工具库的git提交记录】  -- git commit log for a formula


brew migrate     【修改一个安装包的名称?】  -- migrate renamed formula to new name

brew missing     【检查所有已安装 但失去依赖关系的包】     -- check all installed formuale for missing dependencies.


brew outdated  【显示所有已安装包的服务器最新版本】    
【brew outdated ：
aircrack-ng (1.1_2) < 1.3
automake (1.15.1) < 1.16.1
cocoapods (1.3.1) < 1.5.3
git (2.17.0) < 2.18.0
ideviceinstaller (1.1.0_3) < 1.1.0_4
libtasn1 (4.12) < 4.13
libusb (1.0.21) < 1.0.22
libxml2 (2.9.4_4) < 2.9.7
libzip (1.2.0) < 1.5.1
readline (7.0.3_1) < 7.0.5
sqlite (3.19.3) < 3.24.0 】
-- list formulae for which a newer version is available



brew pin  xxxx安装包           【暂停安装包 安装过程  例如：brew pin  git】-- pin specified formulae
brew postinstall  xxxx安装包  【安装向导?】 -- perform post_install for a given formula
brew prune         【移除 那些不起作用的 链接Link 在安装 homebrew的目录】 -- remove dead links

brew reinstall  xxxx安装包   【更新已经安装的安装包】-- install a formula anew; re-using its current options

brew remove  xxxx安装包      【移除一个安装包】      -- remove a formula

brew  search  xxxx安装包    【搜索安装包】      -- search for a formula (/regex/ or string)

brew  switch  xxxx安装包 【在已安装的相同软件不同版本间切换】      -- switch between different versions of a formula

brew tap         【可以为brew的软件的 跟踪,更新,安装添加更多的的tap formulae】 
https://segmentfault.com/a/1190000012826983    【homebrew的tap功能详解】
 -- tap a new formula repository from GitHub, or list existing 
 
 
brew tap-info      【显示 tab 信息】-- information about a tap
【 brew tap-info 
2 taps, 0 pinned, 0 private, 4611 formulae, 3 commands, 9,165 files, 181.4MB 】 


brew  uninstall  xxxx安装包  【 卸载安装包 】  -- uninstall a formula   


brew  update     【下载所有的新的安装包】   -- fetch latest version of Homebrew and all formulae

brew upgrade   【升级安装所有的本地安装包对应的服务器新版本】     -- upgrade outdated formulae

brew  uses  xxxx安装包    【查看所有依赖指定包的其他安装包】     
【brew  uses wget
dasht               jigdo               luaver              wgetpaste】
-- show formulae which depend on a formula

 brew  analytics   【统计？】
【
Analytics is enabled.
UUID: 167DFEA1-3D1B-48B3-8025-1380F6630017
】

 brew  irb   【进入 brew自己的shell】

```

```
[homebrew 能安装的软件集合   4628个]
a2ps                        a52dec                            aacgain                        aalib                         
aamath                      aap                               aardvark_shell_utils           abcde                         
abcl                        abcm2ps                           abcmidi                        abduco                        
abnfgen                     abook                             abuse                          abyss                         
ace                         aces_container                    ack                            acme                          
acmetool                    acpica                            activemq                       activemq-cpp                  
admesh                      adns                              adplug                         adr-tools                     
advancecomp                 advancemame                       advancemenu                    advancescan                   
adwaita-icon-theme          aescrypt                          aescrypt-packetizer            aespipe                       
afflib                      afio                              afl-fuzz                       afsctool                      
aften                       afuse                             agda                           agedu                         
aggregate                   aha                               ahcpd                          aiccu                         
aide                        aircrack-ng                       airspy                         akamai                        
akka                        alac                              aldo                           alexjs                        
algernon                    algol68g                          align                          allegro                       
allure                      alluxio                           alot                           alpine                        
alure                       amap                              amazon-ecs-cli                 amber                         
amdatu-bootstrap            ammonite-repl                     ampl-mp                        amqp-cpp                      
amtk                        amtterm                           analog                         angband                       
angle-grinder               angular-cli                       anjuta                         annie                         
ansible                     ansible-cmdb                      ansible-lint                   ansible@1.9                   
ansible@2.0                 ansifilter                        ansiweather                    ant                           
ant-contrib                 ant@1.9                           antigen                        antiword                      
antlr                       antlr4-cpp-runtime                antlr@2                        anttweakbar                   
aoeui                       apache-archiva                    apache-arrow                   apache-arrow-glib             
apache-brooklyn-cli         apache-ctakes                     apache-drill                   apache-flink                  
apache-forrest              apache-geode                      apache-opennlp                 apache-spark                  
apache-zeppelin             apachetop                         apcupsd                        ape                           
apel                        apgdiff                           apib                           apibuilder-cli                
apktool                     apm-bash-completion               apm-server                     apng2gif                      
apngasm                     apollo                            app-engine-java                app-engine-python             
apparix                     apple-gcc42                       appledoc                       appscale-tools                
apr                         apr-util                          apt-dater                      aptly                         
aptly-completion            aqbanking                         arabica                        arangodb                      
arcade-learning-environment archey                            archi-steam-farm               archivemail                   
archivemount                argon2                            argp-standalone                argtable                      
argus                       argus-clients                     argyll-cms                     aria2                         
ark                         arm-linux-gnueabihf-binutils      armadillo                      armor                         
arp-scan                    arp-sk                            arpack                         arping                        
arpoison                    arss                              artifactory                    artifactory-cli-go            
arx                         arx-libertatis                    ascii                          asciidoc                      
asciidoctor                 asciidoctorj                      asciinema                      asciinema2gif                 
asciiquarium                asciitex                          asdf                           asio                          
ask-cli                     asn1c                             aspcud                         aspectj                       
aspell                      assh                              assimp                         astyle                        
at-spi2-atk                 at-spi2-core                      atari800                       atdtool                       
aterm                       atf                               atk                            atkmm                         
atlassian-cli               atomicparsley                     atool                          ats2-postiats                 
aubio                       audacious                         audiofile                      auditbeat                     
augeas                      augustus                          aurora                         aurora-cli                    
auto-scaling                autobench                         autocode                       autoconf                      
autoconf-archive            autoconf@2.13                     autoenv                        autogen                       
autojump                    automake                          automysqlbackup                autopano-sift-c               
autopep8                    autopsy                           autorest                       autossh                       
avanor                      avce00                            avfs                           avian                         
aview                       avimetaedit                       avra                           avrdude                       
avro-c                      avro-cpp                          avro-tools                     awf                           
awk                         aws-apigateway-importer           aws-cfn-tools                  aws-cloudsearch               
aws-elasticache             aws-elasticbeanstalk              aws-es-proxy                   aws-keychain                  
aws-sdk-cpp                 aws-shell                         aws-sns-cli                    awscli                        
awslogs                     axel                              azure-cli                      b2-tools                      
b2sum                       b43-fwcutter                      babel                          babeld                        
babl                        backupninja                       bacula-fd                      badtouch                      
bagit                       balance                           ballerburg                     ballerina                     
bam                         bamtools                          bandcamp-dl                    baobab                        
bar                         bareos-client                     baresip                        bartycrouch                   
base64                      basex                             bash                           bash-completion               
bash-completion@2           bash-git-prompt                   bash-preexec                   bash-snippets                 
bashdb                      bashish                           bastet                         bat                           
batik                       bats                              bats-core                      bazaar                        
bazel                       bbcolors                          bbe                            bbftp-client                  
bc                          bcal                              bcftools                       bchunk                        
bcpp                        bcrypt                            bde                            bdsup2sub                     
bdw-gc                      beagle                            beansdb                        beanstalkd                    
bear                        beast                             bedops                         bedtools                      
bee                         beecrypt                          befunge93                      bench                         
bento4                      berkeley-db                       berkeley-db@4                  bettercap                     
betty                       bfg                               bgpdump                        bgpq3                         
bgpstream                   bgrep                             bib-tool                       bibclean                      
bibtex2html                 bibtexconv                        bibutils                       bigloo                        
binaryen                    bind                              bindfs                         binkd                         
binutils                    binwalk                           bioawk                         biogeme                       
bison                       bison@2.7                         bit                            bitchx                        
bitcoin                     bitlbee                           bitrise                        bittwist                      
bitwarden-cli               black                             blackbox                       blahtexml                     
blast                       blastem                           blazeblogger                   blazegraph                    
blink1                      blitz                             blitzwave                      blockhash                     
bltool                      bluepill                          blueutil                       bmake                         
bmon                        bnd                               bochs                          bogofilter                    
bokken                      bonnie++                          bookloupe                      boom-completion               
boost                       boost-bcp                         boost-build                    boost-mpi                     
boost-python                boost-python3                     boost-python@1.59              boost@1.55                    
boost@1.57                  boost@1.59                        boost@1.60                     boot-clj                      
boot2docker                 boot2docker-completion            borg                           bork                          
botan                       bower                             bowtie2                        boxes                         
bpm-tools                   brag                              braid                          brainfuck                     
brew-cask-completion        brew-gem                          brew-php-switcher              brew-pip                      
brightness                  briss                             bro                            brogue                        
brotli                      browser                           bsdconv                        bsdiff                        
bsdmake                     bsdsfv                            bsponmpi                       btfs                          
btparse                     btpd                              bubbros                        buildapp                      
buildifier                  buku                              bulk_extractor                 bullet                        
bundler-completion          bup                               burl                           burp                          
bvi                         bwa                               bwctl                          bwfmetaedit                   
bwm-ng                      byacc                             byobu                          byteman                       
bzip2                       bzr-builder                       bzr-colo                       bzr-externals                 
bzr-extmerge                bzr-fastimport                    bzr-rewrite                    bzr-upload                    
bzr-xmloutput               bzrtools                          bzt                            c-ares                        
c-kermit                    c10t                              c14-cli                        c2048                         
cabal-install               cabextract                        cabocha                        cadaver                       
caddy                       cadubi                            caf                            caffe                         
cairo                       cairomm                           cake                           calabash                      
calc                        calcurse                          calicoctl                      camellia                      
camlp4                      camlp5                            cap-completion                 capnp                         
capstone                    cargo-completion                  carina                         carrot2                       
carthage                    cask                              casperjs                       cassandra                     
cassandra@2.1               cassandra@2.2                     castxml                        cataclysm                     
catimg                      cattle                            cayley                         cbmbasic                      
cc65                        ccache                            ccal                           ccat                          
ccd2iso                     ccextractor                       cclive                         ccm                           
cconv                       ccrypt                            cctools                        cctools-headers               
ccze                        cd-discid                         cdargs                         cdb                           
cdecl                       cdiff                             cdk                            cdlabelgen                    
cdogs-sdl                   cdparanoia                        cdpr                           cdrdao                        
cdrtools                    center-im                         cereal                         ceres-solver                  
cern-ndiff                  certbot                           certigo                        certstrap                     
ceylon                      cf                                cf4ocl                         cfengine                      
cfitsio                     cflow                             cfr-decompiler                 cfssl                         
cfv                         cgal                              cgdb                           cglm                          
cgoban                      cgrep                             cgvg                           chadwick                      
chaiscript                  chakra                            chamber                        chapel                        
charm                       charm-tools                       chcase                         cheapglk                      
cheat                       check                             check_postgres                 checkbashisms                 
checkstyle                  cheops                            cherokee                       chezscheme                    
chgems                      chibi-scheme                      chicken                        chinadns-c                    
chipmunk                    chisel                            chkrootkit                     chmlib                        
chocolate-doom              choose                            choose-gui                     chordii                       
chromaprint                 chrome-cli                        chrome-export                  chronograf                    
chruby                      chruby-fish                       chuck                          cidrmerge                     
cifer                       cig                               cimg                           circleci                      
citus                       cityhash                          civl                           cjdns                         
ck                          ckan                              cksfv                          clac                          
clamav                      clamz                             clang-format                   classads                      
clblas                      clblast                           clean                          clearlooks-phenix             
clens                       cless                             clfft                          clhep                         
cli53                       clib                              click                          cliclick                      
clinfo                      cling                             clingo                         clipper                       
clipsafe                    clisp                             cln                            cloc                          
clockywock                  clog                              clojure                        clojurescript                 
cloog                       closure-compiler                  closure-linter                 closure-stylesheets           
cloud-watch                 clozure-cl                        clpbar                         clucene                       
clutter                     clutter-gst                       clutter-gtk                    cmake                         
cmark                       cmark-gfm                         cmatrix                        cmigemo                       
cminpack                    cmocka                            cmockery                       cmockery2                     
cmu-pocketsphinx            cmu-sphinxbase                    cmuclmtk                       cmus                          
cnats                       cntlm                             coccinelle                     cockatrice                    
cockroach                   cocoapods                         cocot                          coda-cli                      
codec2                      codemod                           codequery                      coffeescript                  
cogl                        cointop                           collada-dom                    collectd                      
collector-sidecar           color-code                        colordiff                      colormake                     
colorsvn                    colortail                         commandbox                     compcert                      
compface                    compose2kube                      composer                       conan                         
concurrencykit              configen                          confluent-oss                  confuse                       
conjure-up                  connect                           conserver                      console_bridge                
consul                      consul-backinator                 consul-template                contacts                      
container-diff              convertlit                        convmv                         convox                        
cookiecutter                coq                               corebird                       corectl                       
coreos-ct                   coreutils                         corkscrew                      corsixth                      
cosi                        coturn                            couchdb                        couchdb-lucene                
couchpotatoserver           cowsay                            cp2k                           cpanminus                     
cpansearch                  cpmtools                          cppad                          cppcheck                      
cppcms                      cppi                              cppp                           cpprestsdk                    
cpptest                     cppunit                           cpputest                       cproto                        
cpulimit                    cputhrottle                       cquery                         cracklib                      
crash                       crc32c                            credstash                      creduce                       
crf++                       crm114                            cromwell                       cronolog                      
crosstool-ng                crowdin                           crunch                         crush-tools                   
cryfs                       cryptol                           cryptopp                       crystal                       
crystal-icr                 crystal-lang                      cscope                         csfml                         
csmith                      cspice                            css-crush                      cssembed                      
csshx                       csup                              csv-fix                        csvkit                        
csvprintf                   csvtomd                           ctags                          ctail                         
ctemplate                   ctl                               ctop                           ctunnel                       
cuba                        cubeb                             cucumber-cpp                   cuetools                      
cunit                       curaengine                        curl                           curlftpfs                     
curlish                     curlpp                            curseofwar                     cutter                        
cvs                         cvs2svn                           cvsps                          cvsutils                      
cvsync                      cweb                              cxxtest                        cython                        
czmq                        daemon                            daemonize                      daemonlogger                  
daemontools                 dante                             daq                            dar                           
darcs                       dark-mode                         darkhttpd                      darkice                       
darksky-weather             darkstat                          dartsim                        dash                          
dashing                     dasht                             dasm                           datamash                      
datetime-fortran            dateutils                         datomic                        davix                         
davmail                     dbacl                             dbhash                         dbus                          
dbus-glib                   dbxml                             dc3dd                          dcadec                        
dcal                        dcd                               dcfldd                         dcled                         
dcm2niix                    dcmtk                             dcos-cli                       dcraw                         
ddar                        ddate                             ddclient                       ddd                           
ddgr                        ddrescue                          deark                          debianutils                   
defaultbrowser              deheader                          dehydrated                     deis                          
deisctl                     deja-gnu                          delta                          denominator                   
density                     dep                               dependency-check               deployer                      
depqbf                      derby                             desk                           desktop-file-utils            
detox                       devd                              devil                          devtodo                       
dex                         dex2jar                           dfc                            dfix                          
dfmt                        dfu-programmer                    dfu-util                       dgen                          
dhall-json                  dhcpdump                          dhcping                        dhex                          
di                          dialog                            diamond                        dict                          
diction                     dieharder                         diff-pdf                       diff-so-fancy                 
diffoscope                  diffstat                          diffuse                        diffutils                     
digdag                      digitemp                          dirac                          direnv                        
direvent                    dirmngr                           dirt                           discount                      
disktype                    dislocker                         distcc                         distribution                  
dita-ot                     ditaa                             django-completion              djbdns                        
djmount                     djview4                           djvu2pdf                       djvulibre                     
dlib                        dlite                             dmalloc                        dmd                           
dmenu                       dmtx-utils                        dns2tcp                        dnscrypt-proxy                
dnscrypt-wrapper            dnsdist                           dnsmap                         dnsmasq                       
dnsperf                     dnsrend                           dnstop                         dnstracer                     
dnstwist                    dnsviz                            docbook                        docbook-xsl                   
docbook2x                   docfx                             docker                         docker-clean                  
docker-cloud                docker-completion                 docker-compose                 docker-compose-completion     
docker-credential-helper    docker-credential-helper-ecr      docker-gen                     docker-ls                     
docker-machine              docker-machine-completion         docker-machine-driver-hyperkit docker-machine-driver-vultr   
docker-machine-driver-xhyve docker-machine-nfs                docker-machine-parallels       docker-squash                 
docker-swarm                docker2aci                        dockutil                       dockviz                       
dockward                    doctl                             docutils                       docx2txt                      
doitlive                    dopewars                          dos2unix                       dosbox                        
dosbox-x                    dosfstools                        double-conversion              doublecpp                     
doubledown                  dovecot                           doxygen                        doxymacs                      
dpkg                        dps8m                             draco                          drafter                       
drake                       drip                              dromeaudio                     dropbear                      
dropbox-uploader            druid                             dscanner                       dsd                           
dsh                         dshb                              dsocks                         dspdfviewer                   
dssim                       dtach                             dtc                            dterm                         
dtrx                        dub                               duc                            duck                          
duff                        dumb                              dungeon                        duo_unix                      
duplicity                   duply                             dupseek                        duti                          
dvanalyzer                  dvd+rw-tools                      dvd-vr                         dvdauthor                     
dvdbackup                   dvdrtools                         dvm                            dvorak7min                    
dwarf                       dwarfutils                        dwatch                         dwdiff                        
dwm                         dxflib                            dxpy                           dyld-headers                  
dylibbundler                dynamips                          dynare                         e2fsprogs                     
e2tools                     easy-git                          easy-tag                       easyrpg-player                
ebook-tools                 ec2-ami-tools                     ec2-api-tools                  ecasound                      
eccodes                     echoprint-codegen                 ecj                            ecl                           
ecm                         ed                                editorconfig                   efl                           
eg                          eiffelstudio                      eigen                          einstein                      
ejabberd                    ejdb                              eject                          ekg2                          
ekhtml                      elasticsearch                     elasticsearch@1.7              elasticsearch@2.4             
elasticsearch@5.6           elb-tools                         elektra                        eless                         
elinks                      elixir                            elixir-build                   elm                           
elm-format                  elvish                            emacs                          emacs-clang-complete-async    
embulk                      emojify                           emp                            ems-flasher                   
emscripten                  enca                              encfs                          enchant                       
enet                        engine_pkcs11                     enigma                         enscript                      
ent                         entr                              envchain                       envconsul                     
envv                        eot-utils                         epeg                           ephemeralpg                   
epic5                       eprover                           epsilon                        epstool                       
epubcheck                   eralchemy                         erlang                         erlang@17                     
erlang@18                   erlang@19                         erlang@20                      es                            
esniper                     espeak                            etcd                           ethereum                      
etl                         etsh                              ettercap                       euca2ools                     
euler-py                    eventlog                          eventql                        evince                        
ex-vi                       exa                               exact-image                    excel-compare                 
exempi                      exenv                             exercism                       exif                          
exiftags                    exiftool                          exiftran                       exim                          
exiv2                       exodriver                         exomizer                       expat                         
expect                      exploitdb                         ext2fuse                       ext4fuse                      
extract_url                 exult                             eye-d3                         ezstream                      
f3                          faac                              faad2                          faas-cli                      
fabio                       fabric                            fabric-completion              fades                         
fail2ban                    fairymax                          fakeroot                       falcon                        
fantom                      fasd                              fastbit                        fastd                         
fastjar                     fastme                            fastqc                         fatsort                       
fb-client                   fbi-servefiles                    fceux                          fcgi                          
fcgiwrap                    fcitx-remote-for-osx              fcl                            fcrackzip                     
fd                          fdclone                           fdk-aac                        fdk-aac-encoder               
fdroidserver                fdupes                            feedgnuplot                    feh                           
fetch-crl                   fetchmail                         fex                            ffe                           
ffind                       ffmbc                             ffmpeg                         ffmpeg2theora                 
ffmpeg@2.8                  ffmpegthumbnailer                 ffms2                          fftw                          
fibjs                       ficy                              field3d                        fifechan                      
fig2dev                     figlet                            file-formula                   file-roller                   
filebeat                    finatra                           findutils                      fio                           
firebase-cli                fish                              fits                           fizmo                         
fizsh                       flac                              flac123                        flactag                       
flake                       flake8                            flann                          flashrom                      
flasm                       flatbuffers                       flatcc                         flawfinder                    
fleetctl                    flex                              flickcurl                      flif                          
flint-checker               flintrock                         flow                           flow-tools                    
flowgrind                   fltk                              fluent-bit                     fluid-synth                   
flume                       flvmeta                           flvstreamer                    flyway                        
fmdiff                      fmpp                              fmsx                           fmt                           
fn                          fobis                             folly                          foma                          
fon-flash-cli               fondu                             fontconfig                     fontforge                     
fonttools                   fop                               ford                           forego                        
foremost                    fork-cleaner                      format-udf                     fortio                        
fortune                     fossil                            fourstore                      fox                           
fpc                         fping                             fpp                            fprobe                        
fq                          frag_find                         fragroute                      freealut                      
freeciv                     freediameter                      freedink                       freeglut                      
freeimage                   freeipmi                          freeling                       freeradius-server             
freerdp                     freeswitch                        freetds                        freetds@0.91                  
freetype                    freexl                            frege                          frege-repl                    
frei0r                      fribidi                           frobtads                       frotz                         
frugal                      fruit                             fs-uae                         fselect                       
fsevent_watch               fsevents-tools                    fsh                            fsql                          
fstar                       fstrm                             fsw                            fswatch                       
ftgl                        ftimes                            ftjam                          fuego                         
funcoeszz                   fuse-emulator                     fuse-zip                       fuseki                        
futhark                     fuzzy-find                        fwknop                         fwup                          
fzf                         fzy                               g2                             g3log                         
gabedit                     gaffitter                         galen                          game-music-emu                
gammaray                    gammu                             gandi.cli                      ganglia                       
garmintools                 gauche                            gauge                          gaul                          
gawk                        gbdfed                            gcab                           gcal                          
gcc                         gcc@4.9                           gcc@5                          gcc@6                         
gcc@7                       gconf                             gcore                          gcovr                         
gcsfuse                     gcutil                            gcviewer                       gd                            
gdal                        gdb                               gdbm                           gdcm                          
gdk-pixbuf                  gdl                               gdm                            gdmap                         
gdnsd                       gdrive                            gdub                           gearboy                       
gearman                     gearsystem                        geckodriver                    gecode                        
gedit                       geeqie                            gegl                           gem-completion                
genact                      genders                           generate-json-schema           genext2fs                     
gengetopt                   genometools                       genstats                       geocode-glib                  
geographiclib               geoip                             geoipupdate                    geomview                      
geos                        geoserver                         gerbv                          gerrit-tools                  
get-flash-videos            get_iplayer                       getdns                         geth                          
getmail                     gettext                           getxbook                       gexiv2                        
gf-complete                 gflags                            gforth                         ghc                           
ghc@8.0                     ghc@8.2                           ghex                           ghi                           
ghostscript                 ghq                               gibbslda                       gibo                          
gif2png                     gifcap                            gifify                         giflib                        
giflossy                    gifsicle                          gifski                         gimme                         
ginac                       gist                              gistit                         git                           
git-annex                   git-appraise                      git-archive-all                git-cal                       
git-cinnabar                git-cola                          git-credential-manager         git-crypt                     
git-extras                  git-fixup                         git-flow                       git-flow-avh                  
git-fresh                   git-ftp                           git-game                       git-gerrit                    
git-hooks                   git-if                            git-imerge                     git-integration               
git-lfs                     git-multipush                     git-now                        git-number                    
git-octopus                 git-open                          git-plus                       git-quick-stats               
git-recent                  git-remote-hg                     git-review                     git-secret                    
git-secrets                 git-series                        git-sh                         git-sizer                     
git-ssh                     git-standup                       git-subrepo                    git-svn-abandon               
git-test                    git-tf                            git-town                       git-tracker                   
git-url-sub                 git-utils                         git-vendor                     git-when-merged               
gitbucket                   giter8                            gitfs                          gitg                          
github-keygen               github-markdown-toc               github-release                 gitlab-gem                    
gitlab-runner               gitless                           gitslave                       gitter-cli                    
gitup                       gitversion                        gjs                            gjstest                       
gkrellm                     gl2ps                             glade                          glances                       
glassfish                   glbinding                         glew                           glfw                          
glib                        glib-networking                   glib-openssl                   glibmm                        
glide                       glkterm                           glktermw                       glm                           
global                      globe                             globjects                      globus-toolkit                
glog                        gloox                             glpk                           glslang                       
glslviewer                  glui                              glulxe                         glyr                          
gmail-backup                gmediaserver                      gmic                           gmime                         
gmp                         gmsh                              gmt                            gmt@4                         
gmtl                        gnatsd                            gnome-autoar                   gnome-builder                 
gnome-common                gnome-doc-utils                   gnome-latex                    gnome-recipes                 
gnome-themes-standard       gnu-apl                           gnu-barcode                    gnu-chess                     
gnu-cobol                   gnu-complexity                    gnu-getopt                     gnu-go                        
gnu-indent                  gnu-prolog                        gnu-sed                        gnu-shogi                     
gnu-smalltalk               gnu-tar                           gnu-time                       gnu-typist                    
gnu-units                   gnu-which                         gnumeric                       gnupg                         
gnupg-pkcs11-scd            gnupg@1.4                         gnupg@2.0                      gnuplot                       
gnuplot@4                   gnuradio                          gnuski                         gnustep-make                  
gnutls                      go                                go-bindata                     go-jira                       
go-statik                   go@1.4                            go@1.8                         go@1.9                        
goaccess                    goad                              gobby                          gobject-introspection         
gobuster                    gocr                              gocryptfs                      godep                         
goenv                       gofabric8                         goffice                        gollum                        
golo                        gom                               gomplate                       goocanvas                     
goofys                      google-authenticator-libpam       google-benchmark               google-java-format            
google-sparsehash           google-sql-tool                   googler                        goolabs                       
goose                       gopass                            gor                            goreleaser                    
gost                        gosu                              gotags                         goto                          
gource                      govendor                          gowsdl                         gox                           
gpa                         gpac                              gpatch                         gpcslots2                     
gperf                       gperftools                        gpg-agent                      gpgme                         
gphoto2                     gplcver                           gpm                            gpp                           
gpredict                    gprof2dot                         gpsbabel                       gpsd                          
gpsim                       gptfdisk                          gptsync                        gputils                       
gpx                         gqlplus                           gqview                         gr-osmosdr                    
grace                       gradio                            gradle                         gradle-completion             
gradle@2.14                 grafana                           grails                         grakn                         
grap                        graph-tool                        graphene                       graphicsmagick                
graphite2                   graphviz                          grc                            greed                         
grep                        grepcidr                          grib-api                       griffon                       
grip                        groff                             grok                           gromacs                       
gron                        groonga                           groovy                         groovysdk                     
groovyserv                  growly                            grpc                           grsync                        
grt                         grunt-cli                         grunt-completion               grv                           
gsar                        gsasl                             gsettings-desktop-schemas      gsl                           
gsmartcontrol               gsoap                             gspell                         gssdp                         
gssh                        gst-editing-services              gst-libav                      gst-plugins-bad               
gst-plugins-base            gst-plugins-good                  gst-plugins-ugly               gst-python                    
gst-rtsp-server             gst-validate                      gstreamer                      gstreamermm                   
gti                         gtk+                              gtk+3                          gtk-chtheme                   
gtk-doc                     gtk-engines                       gtk-gnutella                   gtk-mac-integration           
gtk-murrine-engine          gtk-vnc                           gtkdatabox                     gtkextra                      
gtkglext                    gtkmm                             gtkmm3                         gtksourceview                 
gtksourceview3              gtksourceview@4                   gtksourceviewmm                gtksourceviewmm3              
gtkspell3                   gtmess                            gts                            gucharmap                     
guetzli                     guichan                           guile                          guile@2.0                     
gumbo-parser                gupnp                             gupnp-av                       gupnp-tools                   
gutenberg                   gv                                gvp                            gwenhywfar                    
gws                         gwt                               gwyddion                       gx                            
gx-go                       gxml                              gzip                           gzrt                          
h2                          h264bitstream                     h2o                            hachoir-metadata              
hackrf                      hadolint                          hadoop                         halibut                       
hamlib                      hana                              handbrake                      hapi-fhir-cli                 
haproxy                     harbour                           hardlink                       hardlink-osx                  
harfbuzz                    hashcash                          hashcat                        hashpump                      
haskell-stack               haste-client                      hatari                         haxe                          
hayai                       hbase                             hcloud                         hdf5                          
hdf5@1.8                    headphones                        heartbeat                      hebcal                        
heimdal                     hello                             helmfile                       help2man                      
henplus                     hercules                          heroku                         herrie                        
hesiod                      hevea                             hexcurse                       hexedit                       
hexgui                      hfstospell                        hfsutils                       hg-fast-export                
hg-flow                     hh                                hicolor-icon-theme             hidapi                        
highlight                   highlighting-kate                 hilite                         hiredis                       
historian                   hive                              hivemind                       hledger                       
hlint                       hmmer                             hoedown                        homebank                      
homeshick                   homesick-completion               homeworlds                     honcho                        
hopenpgp-tools              hornetq                           hostdb                         hostess                       
howard-hinnant-date         howdoi                            hping                          hqx                           
hr                          hspell                            hss                            ht                            
html-xml-utils              html2text                         htmlcleaner                    htmlcompressor                
htmlcxx                     htmldoc                           htop                           htpdate                       
htslib                      httest                            http-parser                    http-server                   
http_load                   httpd                             httpdiff                       httperf                       
httpflow                    httpie                            httping                        httpry                        
httpstat                    httptunnel                        httrack                        hub                           
hubflow                     huexpress                         hugo                           hunspell                      
hwloc                       hydra                             hyper                          hyperestraier                 
hyperfine                   hyperscan                         hyperspec                      hypre                         
i2p                         i2util                            i3                             i3status                      
iamy                        iat                               ib                             ibex                          
ical-buddy                  icarus-verilog                    icbirc                         icdiff                        
ice                         icecast                           icecream                       icemon                        
icon                        icon-naming-utils                 icoutils                       icu4c                         
id3ed                       id3lib                            id3tool                        id3v2                         
ideviceinstaller            idnits                            idris                          idutils                       
ievms                       ifstat                            iftop                          ifuse                         
igraph                      igv                               ii                             ike-scan                      
ilmbase                     imagejs                           imagemagick                    imagemagick@6                 
imageoptim-cli              imagesnap                         imageworsener                  imake                         
imap-uw                     imapfilter                        imapsync                       imessage-ruby                 
imgur-screenshot            imlib2                            immortal                       inetutils                     
infer                       influxdb                          inform6                        infrakit                      
iniparser                   innoextract                       innotop                        ino                           
insect                      inspectrum                        inspircd                       instead                       
intercal                    internetarchive                   intltool                       io                            
ioke                        ioping                            ios-class-guard                ios-deploy                    
ios-sim                     ios-webkit-debug-proxy            iozone                         ip_relay                      
ipbt                        ipcalc                            iperf                          iperf3                        
ipfs                        iphotoexport                      ipinfo                         ipmitool                      
ipmiutil                    iprint                            iproute2mac                    ipsumdump                     
ipv6calc                    ipv6toolkit                       ipython                        ipython@5                     
ircd-hybrid                 ircd-irc2                         ircii                          ired                          
irods                       iron-functions                    ironcli                        irrlicht                      
irrtoolset                  irssi                             isc-dhcp                       isl                           
iso-codes                   ispc                              ispell                         isync                         
itex2mml                    itpp                              itstool                        ivy                           
ivykis                      jabba                             jack                           jadx                          
jags                        jailkit                           jam                            jansson                       
jasmin                      jasper                            javarepl                       jbake                         
jbig2dec                    jbig2enc                          jbigkit                        jboss-forge                   
jcal                        jdnssec-tools                     jdupes                         jed                           
jemalloc                    jena                              jenkins                        jenkins-job-builder           
jenkins-lts                 jenv                              jerasure                       jerm                          
jetty                       jetty-runner                      jflex                          jfrog-cli-go                  
jhead                       jhiccup                           jhipster                       jid                           
jigdo                       jing-trang                        jlog                           jmeter                        
jmxtrans                    jnethack                          jnettop                        jo                            
joe                         john                              john-jumbo                     jooby-bootstrap               
joplin                      jose                              joshua                         jove                          
jp2a                        jpcsp                             jpeg                           jpeg-archive                  
jpeg-turbo                  jpeginfo                          jpegoptim                      jpegrescan                    
jq                          jrnl                              jrtplib                        jruby                         
js-test-driver              jsawk                             jsdoc3                         jshon                         
jslint4java                 jsmin                             json-c                         json-fortran                  
json-glib                   json-table                        json_spirit                    jsoncpp                       
jsonlint                    jsonnet                           jsonpp                         jsonrpc-glib                  
jsonschema2pojo             jsvc                              jthread                        juise                         
juju                        juju-quickstart                   juju-wait                      julius                        
juman                       jumanpp                           jump                           jupyter                       
just                        jvgrep                            jvm-mon                        jvmtop                        
jxrlib                      jython                            kafka                          kafkacat                      
kaitai-struct-compiler      kakasi                            kakoune                        kallisto                      
kanif                       kapacitor                         karn                           kawa                          
kedge                       keepassc                          keepkey-agent                  kerl                          
kestrel                     kettle                            keychain                       keystone                      
khal                        khard                             kibana                         kibana@4.4                    
kibana@5.6                  kimwitu++                         kitchen-completion             kitchen-sync                  
kite                        klavaro                           knock                          knot                          
knot-resolver               known_hosts                       kobalt                         kommit                        
kompose                     konoha                            kontena                        kops                          
kore                        kotlin                            kpcli                          kqwait                        
krakend                     krb5                              ksh                            kstart                        
ktmpl                       ktoblzcheck                       kube-aws                       kube-ps1                      
kubecfg                     kubectx                           kubeless                       kubernetes-cli                
kubernetes-helm             kubernetes-service-catalog-client kumo                           kvazaar                       
kyoto-cabinet               kyoto-tycoon                      kytea                          kyua                          
l-smash                     lablgtk                           lame                           lammps                        
landscaper                  languagetool                      lapack                         lasi                          
lasso                       lastfmfpclient                    lastpass-cli                   laszip                        
latex2html                  latex2rtf                         latexdiff                      latexila                      
latexml                     launch                            launch4j                       launch_socket_server          
launchctl-completion        launchdns                         lbdb                           lbzip2                        
lcdf-typetools              lcdproc                           lci                            lcm                           
lcov                        lcrack                            lcs                            ld64                          
ldapvi                      ldc                               ldid                           ldns                          
le                          leafnode                          lean                           lean-cli                      
leaps                       ledger                            ledit                          legit                         
lego                        leiningen                         lemon                          lensfun                       
lepton                      leptonica                         less                           lesspipe                      
lesstif                     leveldb                           lf                             lfe                           
lft                         lftp                              lgogdownloader                 lha                           
lhasa                       lib3ds                            libaacs                        libagar                       
libagg                      libantlr3c                        libao                          libarchive                    
libart                      libass                            libassuan                      libatomic_ops                 
libav                       libb2                             libbdplus                      libbi                         
libbind                     libbinio                          libbitcoin                     libbitcoin-blockchain         
libbitcoin-client           libbitcoin-consensus              libbitcoin-database            libbitcoin-explorer           
libbitcoin-network          libbitcoin-node                   libbitcoin-protocol            libbitcoin-server             
libbladerf                  libbluray                         libbpg                         libbs2b                       
libbtbb                     libcaca                           libcanberra                    libcapn                       
libccd                      libcddb                           libcdio                        libcdr                        
libcds                      libcec                            libcello                       libchamplain                  
libchaos                    libchewing                        libcmph                        libcoap                       
libconfig                   libcouchbase                      libcroco                       libcsv                        
libcue                      libcuefile                        libdaemon                      libdap                        
libdazzle                   libdbi                            libdc1394                      libdca                        
libde265                    libdill                           libdiscid                      libdivecomputer               
libdmtx                     libdnet                           libdrawtext                    libdshconfig                  
libdsk                      libdv                             libdvbpsi                      libdvdcss                     
libdvdnav                   libdvdread                        libebml                        libebur128                    
libedit                     libelf                            libepoxy                       liberasurecode                
libestr                     libetonyek                        libetpan                       libev                         
libevent                    libewf                            libexif                        libexosip                     
libextractor                libfabric                         libfaketime                    libffi                        
libfishsound                libfixbuf                         libfixposix                    libflowmanager                
libforensic1394             libfreefare                       libfreehand                    libfreenect                   
libftdi                     libftdi0                          libgadu                        libgaiagraphics               
libgcrypt                   libgda                            libgdata                       libgee                        
libgeotiff                  libgetdata                        libgfshare                     libggz                        
libghthash                  libgig                            libgit2                        libgit2-glib                  
libglade                    libglademm                        libgnomecanvas                 libgnomecanvasmm              
libgosu                     libgpg-error                      libgphoto2                     libgraphqlparser              
libgsf                      libgsm                            libgtop                        libguess                      
libgweather                 libgxps                           libharu                        libhdhomerun                  
libheif                     libhid                            libhttpserver                  libhttpseverywhere            
libical                     libicns                           libiconv                       libid3tag                     
libident                    libidl                            libidn                         libidn2                       
libilbc                     libimagequant                     libimobiledevice               libinfinity                   
libiodbc                    libiptcdata                       libiscsi                       libjson-rpc-cpp               
libjwt                      libkate                           libkml                         libksba                       
liblacewing                 liblas                            liblastfm                      liblcf                        
liblinear                   liblo                             liblockfile                    liblqr                        
libltc                      liblunar                          liblwgeom                      liblzf                        
libmaa                      libmagic                          libmatio                       libmatroska                   
libmaxminddb                libmemcached                      libmetalink                    libmicrohttpd                 
libmikmod                   libmill                           libming                        libmms                        
libmodbus                   libmodplug                        libmonome                      libmowgli                     
libmp3splt                  libmpc                            libmpd                         libmpdclient                  
libmpeg2                    libmrss                           libmspub                       libmtp                        
libmusicbrainz              libmwaw                           libmxml                        libmypaint                    
libnatpmp                   libnet                            libnfc                         libnfs                        
libngspice                  libnice                           libnids                        libnotify                     
libntlm                     libnxml                           liboauth                       libodfgen                     
libofx                      libogg                            liboil                         libomp                        
libopendkim                 libopennet                        liboping                       libopkele                     
libopusenc                  libosinfo                         libosip                        libosmium                     
libotr                      libowfat                          libp11                         libpagemaker                  
libpano                     libpcap                           libpcl                         libpeas                       
libpgm                      libphonenumber                    libplctag                      libplist                      
libpng                      libpointing                       libpoker-eval                  libpq                         
libpqxx                     libprotoident                     libproxy                       libpst                        
libpuzzle                   libqalculate                      libquantum                     libquicktime                  
libquvi                     librasterlite                     libraw                         librcsc                       
librdkafka                  libre                             libreadline-java               librealsense                  
librem                      libreplaygain                     libresample                    libressl                      
librest                     librevenge                        librsvg                        librsync                      
librtlsdr                   libsamplerate                     libsass                        libsbol                       
libscrypt                   libsecret                         libserialport                  libshout                      
libsigc++                   libsigsegv                        libslax                        libsmf                        
libsmi                      libsndfile                        libsodium                      libsoundio                    
libsoup                     libsoxr                           libspatialite                  libspectre                    
libspectrum                 libspiro                          libspnav                       libsquish                     
libssh                      libssh2                           libstatgrab                    libstfl                       
libstrophe                  libstxxl                          libsvg                         libsvg-cairo                  
libsvm                      libswiften                        libswiftnav                    libtar                        
libtasn1                    libtcod                           libtecla                       libtensorflow                 
libtermkey                  libtextcat                        libtiff                        libtins                       
libtomcrypt                 libtommath                        libtool                        libtorrent-rasterbar          
libtrace                    libtrng                           libu2f-host                    libu2f-server                 
libucl                      libuecc                           libuninameslist                libunistring                  
libunwind-headers           libupnp                           libusb                         libusb-compat                 
libusrsctp                  libutf                            libuv                          libuvc                        
libvbucket                  libvidstab                        libvirt                        libvisio                      
libvo-aacenc                libvoikko                         libvorbis                      libvpx                        
libvterm                    libwandevent                      libwbxml                       libwebm                       
libwebsockets               libwmf                            libwpd                         libwpg                        
libwps                      libxc                             libxdg-basedir                 libxdiff                      
libxkbcommon                libxlsxwriter                     libxmi                         libxml++                      
libxml++3                   libxml2                           libxmlsec1                     libxmp                        
libxmp-lite                 libxo                             libxslt                        libxspf                       
libyaml                     libyubikey                        libzdb                         libzip                        
libzzip                     lifelines                         lightning                      lighttpd                      
lilv                        lincity-ng                        link-grammar                   linkerd                       
linklint                    links                             liquibase                      liquid-dsp                    
liquidprompt                liquigraph                        little-cms                     little-cms2                   
livestreamer                lldpd                             llnode                         llvm                          
llvm@3.7                    llvm@3.9                          llvm@4                         llvm@5                        
lm4tools                    lmdb                              lmod                           lnav                          
loc                         locateme                          lockrun                        log4c                         
log4cplus                   log4cpp                           log4cxx                        log4shib                      
logcheck                    logentries                        logrotate                      logstalgia                    
logstash                    logtalk                           lolcat                         lolcode                       
lorem                       loudmouth                         lout                           lpc21isp                      
lrdf                        lrzip                             lrzsz                          lsdvd                         
lsh                         lsof                              lsusb                          lsyncd                        
ltc-tools                   ltl2ba                            lua                            lua@5.1                       
luabind                     luajit                            luaradio                       luaver                        
luciddb                     lumo                              lutok                          luvit                         
lv                          lv2                               lwtools                        lxc                           
lxsplit                     lynis                             lynx                           lysp                          
lz4                         lzfse                             lzip                           lzlib                         
lzo                         lzop                              m-cli                          m2c                           
m4                          mac-robber                        mackup                         macosvpn                      
macvim                      mad                               madplay                        mafft                         
magic-wormhole              magnetix                          mahout                         mailcheck                     
mailhog                     mailutils                         mairix                         make                          
makedepend                  makefile2graph                    makeicns                       makensis                      
makepkg                     makepp                            makeself                       malbolge                      
mame                        man2html                          mandoc                         mapcrafter                    
mapnik                      mapserver                         marathon-swift                 mariadb                       
mariadb-connector-c         mariadb-connector-odbc            mariadb@10.0                   mariadb@10.1                  
mariadb@10.2                markdown                          marst                          mas                           
masscan                     massren                           mat                            math-comp                     
matlab2tikz                 maven                             maven-completion               maven-shell                   
maven@3.0                   maven@3.1                         maven@3.2                      maven@3.3                     
mawk                        maxima                            maxwell                        mbedtls                       
mbelib                      mboxgrep                          mcabber                        mcpp                          
mcrypt                      md                                md5deep                        md5sha1sum                    
mda-lv2                     mdbtools                          mdcat                          mdds                          
mdf2iso                     mdk                               mdp                            mdr                           
mdv                         mdxmini                           mecab                          mecab-ipadic                  
mecab-jumandic              mecab-ko                          mecab-ko-dic                   mecab-unidic                  
mecab-unidic-extended       media-info                        mediaconch                     mediatomb                     
mednafen                    megacmd                           megatools                      memcache-top                  
memcached                   memcacheq                         memtester                      menhir                        
mercurial                   mercury                           mergelog                       mergepbx                      
mesalib-glw                 meson                             meson-internal                 mesos                         
metabase                    metaproxy                         metashell                      metis                         
metricbeat                  mfcuk                             mfoc                           mfterm                        
mftrace                     mg                                mgba                           mhash                         
micro                       micronaut                         micropython                    midgard2                      
midicsv                     midnight-commander                mighttpd2                      mikmod                        
mikutter                    mill                              miller                         mimic                         
mimms                       minbif                            minetest                       mingw-w64                     
minicom                     minidjvu                          minidlna                       minimal-racket                
minimesos                   minimodem                         minio                          minio-mc                      
minisat                     minised                           miniserve                      minisign                      
miniupnpc                   minizinc                          minizip                        mint                          
minuit2                     miruo                             mit-scheme                     mitie                         
mitmproxy                   mix-completion                    mjpegtools                     mk-configure                  
mkcert                      mkclean                           mkcue                          mkdocs                        
mkhexgrid                   mkl-dnn                           mksh                           mktorrent                     
mkvalidator                 mkvdts2ac3                        mkvtomp4                       mkvtoolnix                    
mldonkey                    mlkit                             mlogger                        mlt                           
mlton                       mm-common                         mmark                          mmix                          
mmseqs2                     mmsrip                            mmv                            mobiledevice                  
moc                         mockserver                        moco                           modd                          
modgit                      modman                            modules                        moe                           
mogenerator                 molecule                          mon                            monax                         
monero                      monetdb                           mongo-c-driver                 mongo-cxx-driver              
mongo-orchestration         mongodb                           mongodb@3.0                    mongodb@3.2                   
mongodb@3.4                 mongodb@3.6                       mongoose                       mongrel2                      
mongroup                    monit                             monitoring-plugins             monkeysphere                  
mono                        mono-libgdiplus                   monotone                       montage                       
moon-buggy                  moreutils                         morse                          mosh                          
mosml                       mosquitto                         most                           movgrab                       
moz-git-tools               mozilla-addon-sdk                 mozjpeg                        mp3blaster                    
mp3cat                      mp3check                          mp3fs                          mp3gain                       
mp3info                     mp3splt                           mp3unicode                     mp3val                        
mp3wrap                     mp4v2                             mpack                          mpage                         
mpc                         mpck                              mpd                            mpdas                         
mpdscribble                 mpdviz                            mpegdemux                      mpfi                          
mpfr                        mpg123                            mpg321                         mpgtx                         
mpich                       mpir                              mplayer                        mplayershell                  
mpop                        mps-youtube                       mpssh                          mpv                           
mpw                         mr                                mrboom                         mrtg                          
mruby                       mruby-cli                         mscgen                         msdl                          
msgpack                     msgpuck                           msitools                       msktutil                      
msmtp                       mspdebug                          mstch                          mtools                        
mtr                         mu                                mujs                           multimarkdown                 
multitail                   muparser                          mupdf                          mupdf-tools                   
mupen64plus                 musepack                          mussh                          mutt                          
muttils                     mvnvm                             mvtools                        mycli                         
mydumper                    myman                             mypy                           mysql                         
mysql-client                mysql-cluster                     mysql-connector-c++            mysql-sandbox                 
mysql-search-replace        mysql-utilities                   mysql@5.5                      mysql@5.6                     
mysql@5.7                   mysqltuner                        mytop                          n                             
nacl                        naga                              nagios                         nagios-plugins                
nailgun                     namazu                            namebench                      nano                          
nanomsg                     nanomsgxx                         nanopb-generator               narwhal                       
nasm                        natalie                           nativefier                     nats-streaming-server         
naturaldocs                 nave                              nbimg                          ncdc                          
ncdu                        ncftp                             ncmpc                          ncmpcpp                       
nco                         ncompress                         ncp                            ncrack                        
ncurses                     ncview                            ndenv                          ndiff                         
ndpi                        ne                                neal                           neatvi                        
nedit                       negfix8                           neko                           neo4j                         
neofetch                    neomutt                           neon                           neopop-sdl                    
neovim                      nesc                              nesemu2                        nestopia-ue                   
net-snmp                    netcat                            netcat6                        netcdf                        
netdata                     nethack                           nethack4                       nethacked                     
nethogs                     netpbm                            netperf                        netris                        
nettle                      nettoe                            newlisp                        newsboat                      
newt                        nexus                             nfcutils                       nfdump                        
nghttp2                     nginx                             ngircd                         ngrep                         
ngspice                     nickle                            nicotine-plus                  nicovideo-dl                  
nifi                        nifi-registry                     nikto                          nim                           
ninja                       ninvaders                         nkf                            nload                         
nlopt                       nmap                              nmh                            nnn                           
no-more-secrets             node                              node-build                     node@4                        
node@6                      node@8                            node_exporter                  nodebrew                      
nodeenv                     nodenv                            nomad                          nopoll                        
nordugrid-arc               norm                              normalize                      noti                          
notmuch                     noweb                             np2                            npth                          
npush                       nq                                nrg2iso                        nrpe                          
nsd                         nsnake                            nspr                           nsq                           
nss                         nsuds                             ntfs-3g                        ntl                           
ntopng                      ntp                               nu                             nudoku                        
nuget                       num-utils                         numpy                          nut                           
nutcracker                  nuttcp                            nuvie                          nuxeo                         
nvc                         nvi                               nvm                            nxengine                      
nyancat                     nylon                             nyx                            nzbget                        
oath-toolkit                oauth2_proxy                      objc-codegenutils              objc-run                      
ocaml                       ocaml-findlib                     ocaml-num                      ocamlbuild                    
ocamlsdl                    ocp                               ocproxy                        ocrad                         
ocrmypdf                    octave                            octomap                        ode                           
odo                         odpi                              odt2txt                        offlineimap                   
oggz                        ogmtools                          ohcount                        ola                           
olsrd                       omega                             omega-rpg                      omniorb                       
ompl                        ondir                             one-ml                         onepass                       
onetime                     oniguruma                         onioncat                       onscripter                    
ooniprobe                   opam                              open-babel                     open-cobol                    
open-completion             open-jtalk                        open-mesh                      open-mpi                      
open-ocd                    open-scene-graph                  open-sp                        open-tyrian                   
open-vcdiff                 open-zwave                        openal-soft                    openapi-generator             
openblas                    opencascade                       opencbm                        opencc                        
openclonk                   opencoarrays                      opencolorio                    openconnect                   
opencore-amr                opencsg                           opencv                         opencv@2                      
opendbx                     opendetex                         openexr                        openfortivpn                  
openh264                    openhmd                           openimageio                    openjazz                      
openjpeg                    openldap                          openmotif                      openmsx                       
openrct2                    openrtsp                          opensaml                       opensc                        
openshift-cli               openslide                         openslp                        openssh                       
openssl                     openssl@1.1                       opensyobon                     opentsdb                      
openttd                     openvdb                           openvpn                        ophcrack                      
optipng                     opus                              opus-tools                     opusfile                      
orbit                       orc                               orc-tools                      ori                           
orientdb                    orocos-kdl                        ortp                           osc                           
oscats                      osm-gps-map                       osm-pbf                        osm2pgrouting                 
osm2pgsql                   osmfilter                         osmium-tool                    osmosis                       
osquery                     osrm-backend                      osslsigncode                   ossp-uuid                     
osxutils                    otf2bdf                           ott                            overmind                      
owamp                       owfs                              oysttyer                       p0f                           
p11-kit                     p7zip                             pacapt                         pachi                         
packer                      packer-completion                 packetbeat                     packetq                       
packmol                     pacman4console                    pacparser                      pacvim                        
pakchois                    paket                             pam-u2f                        pam_yubico                    
pandoc                      pandoc-citeproc                   pandoc-crossref                pango                         
pangomm                     paperkey                          paps                           par                           
par2                        parallel                          parallelstl                    pari                          
parquet-tools               parrot                            partio                         pass                          
passenger                   passpie                           passwdqc                       pastebinit                    
patchelf                    patchutils                        path-extractor                 pax-construct                 
pax-runner                  payara                            pazpar2                        pbc                           
pbc-sig                     pbrt                              pbzip2                         pc6001vx                      
pcal                        pcb                               pcb2gcode                      pce                           
pcl                         pcre                              pcre2                          pcsc-lite                     
pdal                        pdf-redact-tools                  pdf2htmlex                     pdf2image                     
pdf2json                    pdf2svg                           pdfcrack                       pdfgrep                       
pdflib-lite                 pdfpc                             pdfsandwich                    pdftoedn                      
pdftohtml                   pdftoipe                          pdns                           pdnsd                         
pdnsrec                     pdsh                              peco                           peg                           
peg-markdown                pegtl                             pelikan                        perceptualdiff                
percol                      percona-server                    percona-server-mongodb         percona-server@5.6            
percona-toolkit             percona-xtrabackup                perkeep                        perl                          
perl-build                  perl@5.18                         petsc                          pev                           
pex                         pg_top                            pgbadger                       pgbouncer                     
pgcli                       pgdbf                             pgformatter                    pgloader                      
pgpdump                     pgplot                            pgpool-ii                      pgroonga                      
pgrouting                   pgtoolkit                         pgtune                         pgweb                         
phantomjs                   phoon                             phoronix-test-suite            php                           
php-code-sniffer            php-cs-fixer                      php@5.6                        php@7.0                       
php@7.1                     phplint                           phpmyadmin                     phpunit                       
physfs                      pianobar                          pianod                         picard-tools                  
pick                        picoc                             picocom                        pidcat                        
pidgin                      pidof                             pig                            pigz                          
pijul                       pike                              piknik                         pillar                        
pilosa                      pinboard-notes-backup             pincaster                      pinentry                      
pinentry-mac                pinfo                             pioneer                        pioneers                      
pip-completion              pipebench                         pipemeter                      pipenv                        
pipes-sh                    pit                               pius                           pixman                        
pixz                        pjproject                         pk                             pkcrack                       
pkcs11-helper               pkg-config                        pkgdiff                        pktanon                       
pla                         plan9port                         planck                         plank                         
plantuml                    platformio                        platypus                       pldebugger                    
plenv                       plod                              ploticus                       plotutils                     
plowshare                   plplot                            plustache                      plzip                         
pmccabe                     pmd                               pmdmini                        pms                           
png++                       png2ico                           pngcheck                       pngcrush                      
pngnq                       pngpaste                          pngquant                       poco                          
pod2man                     podiff                            podofo                         points2grid                   
polipo                      polyglot                          polyml                         pony-stable                   
ponyc                       ponysay                           poppler                        popt                          
portaudio                   portmidi                          posh                           poster                        
postgis                     postgres-xc                       postgresql                     postgresql@9.4                
postgresql@9.5              postgresql@9.6                    postgrest                      postmark                      
potrace                     pound                             povray                         pow                           
powerman                    ppl                               ppss                           ppsspp                        
pqiv                        pre-commit                        precomp                        predictionio                  
prefixsuffix                premake                           prest                          presto                        
prettier                    prettyping                        primer3                        primesieve                    
prips                       privoxy                           procmail                       proctools                     
procyon-decompiler          prodigal                          profanity                      proftpd                       
progress                    proguard                          proj                           prometheus                    
proof-general               proselint                         protobuf                       protobuf-c                    
protobuf-swift              protobuf@2.5                      protobuf@2.6                   protobuf@3.1                  
proxychains-ng              proxytunnel                       ps2eps                         psftools                      
psgrep                      pspg                              psql2csv                       psqlodbc                      
pssh                        pstoedit                          pstree                         psutils                       
ptex                        pth                               ptunnel                        puf                           
pugixml                     pulledpork                        pulseaudio                     pumba                         
pup                         pure-ftpd                         purescript                     pushpin                       
putmail                     putmail-queue                     putty                          puzzles                       
pv                          pwgen                             pwnat                          pwntools                      
pwsafe                      pxz                               py2cairo                       py3cairo                      
pybind11                    pycodestyle                       pyenv                          pyenv-ccache                  
pyenv-pip-migrate           pyenv-virtualenv                  pyenv-virtualenvwrapper        pyenv-which-ext               
pyexiv2                     pygitup                           pygobject                      pygobject3                    
pygtk                       pygtkglext                        pygtksourceview                pyinvoke                      
pypy                        pypy3                             pyqt                           pyside                        
python                      python-markdown                   python-yq                      python@2                      
pytouhou                    pyvim                             q                              qbs                           
qca                         qcachegrind                       qcli                           qd                            
qdae                        qdbm                              qemu                           qhull                         
qjackctl                    qjson                             qmmp                           qpdf                          
qpid-proton                 qpm                               qprint                         qrencode                      
qriollo                     qrupdate                          qscintilla2                    qsoas                         
qstat                       qt                                qt@5.5                         qtads                         
qtfaststart                 qtkeychain                        quantlib                       quasi88                       
quazip                      queequeg                          questdb                        quex                          
quicktype                   quilt                             quotatool                      quvi                          
qwt                         qwtpolar                          qxmpp                          r                             
r3                          rabbitmq                          rabbitmq-c                     rack                          
radamsa                     radare2                           ragel                          rails-completion              
rainbarf                    raine                             rake-completion                rakudo-star                   
rancher-cli                 rancher-compose                   rancid                         randomize-lines               
range-v3                    ranger                            rapidjson                      raptor                        
rarian                      rasqal                            ratfor                         rats                          
rawgl                       rawtoaces                         raylib                         rbenv                         
rbenv-aliases               rbenv-binstubs                    rbenv-bundle-exec              rbenv-bundler                 
rbenv-bundler-ruby-version  rbenv-chefdk                      rbenv-communal-gems            rbenv-ctags                   
rbenv-default-gems          rbenv-gemset                      rbenv-use                      rbenv-vars                    
rbenv-whatis                rc                                rclone                         rcs                           
rdate                       rdesktop                          rdfind                         rdiff-backup                  
rds-command-line-tools      rdup                              re2                            re2c                          
readline                    readosm                           reattach-to-user-namespace     reaver                        
rebar                       rebar@3                           reclass                        recode                        
recon-ng                    recoverjpeg                       recutils                       redex                         
redir                       redis                             redis-leveldb                  redis@2.8                     
redis@3.2                   redland                           redo                           redpen                        
redshift                    redsocks                          redstore                       regex-opt                     
regina-rexx                 regldg                            rem                            remake                        
remarshal                   remctl                            remind                         reminiscence                  
ren                         rename                            renameutils                    reop                          
repl                        repo                              reposurgeon                    residualvm                    
rest-shell                  restic                            restund                        restview                      
resty                       rethinkdb                         rex                            rfcmarkup                     
rfcstrip                    rgbds                             rgxg                           rhash                         
rhino                       riak                              riemann                        riemann-client                
rig                         rinetd                            ringojs                        ripgrep                       
ripmime                     rkflashtool                       rkhunter                       rlog                          
rlvm                        rlwrap                            rmate                          rmcast                        
rmlint                      rmtrash                           rnv                            robodoc                       
robot-framework             robotfindskitten                  rock                           rocksdb                       
rofs-filtered               rogue                             roll                           rolldice                      
rom-tools                   root                              roswell                        roundup                       
rp                          rpcgen                            rpg                            rpl                           
rpm                         rpm2cpio                          rrdtool                        rsnapshot                     
rssh                        rsstail                           rst-lint                       rswift                        
rsync                       rsync-time-backup                 rsyslog                        rt-audio                      
rtags                       rtf2latex2e                       rtmidi                         rtmpdump                      
rtptools                    rtv                               rubberband                     ruby                          
ruby-build                  ruby-completion                   ruby-install                   ruby@1.8                      
ruby@2.0                    ruby@2.2                          ruby@2.3                       runcocoa                      
runit                       rush                              rust                           rustc-completion              
rustup-init                 rxvt-unicode                      ry                             rzip                          
s-lang                      s-nail                            s-search                       s3-backer                     
s3cmd                       s3fs                              s6                             safe                          
safe-rm                     sagittarius-scheme                saldl                          salt                          
saltstack                   samtools                          sane-backends                  sary                          
sassc                       savana                            saxon                          saxon-b                       
sbcl                        sbjson                            sblim-sfcc                     sbt                           
sbt@0.13                    sbtenv                            sbuild                         sc-im                         
sc68                        scala                             scala@2.10                     scala@2.11                    
scalaenv                    scalapack                         scalariform                    scalastyle                    
scale2x                     scamper                           sccache                        sceptre                       
schema-evolution-manager    scheme48                          schismtracker                  schroedinger                  
scipy                       scm-manager                       scmpuff                        scons                         
scour                       scrcpy                            screen                         screenfetch                   
screenresolution            scriptcs                          scrollkeeper                   scrub                         
scrypt                      scummvm                           scummvm-tools                  scw                           
sdb                         sdcc                              sdcv                           sdedit                        
sdf                         sdhash                            sdl                            sdl2                          
sdl2_gfx                    sdl2_image                        sdl2_mixer                     sdl2_net                      
sdl2_ttf                    sdl_gfx                           sdl_image                      sdl_mixer                     
sdl_net                     sdl_rtf                           sdl_sound                      sdl_ttf                       
sdlpop                      sec                               securefs                       seexpr                        
selecta                     selenium-server-standalone        sendemail                      seqtk                         
ser2net                     serd                              serf                           serialosc                     
sersniff                    serveit                           serverless                     servus                        
setweblocthumb              sf-pwgen                          sfcgal                         sfk                           
sflowtool                   sfml                              sfst                           sgrep                         
sha1dc                      sha2                              shadowsocks-libev              shairport                     
shairport-sync              shakespeare                       shapelib                       shared-mime-info              
shc                         shellcheck                        shellharden                    shellinabox                   
shellshare                  shelltestrunner                   shfmt                          shibboleth-sp                 
shivavg                     shmcat                            shml                           shmux                         
shntool                     shocco                            shogun                         shorten                       
shpotify                    shtool                            shunit2                        shyaml                        
sic                         sickbeard                         sickle                         siege                         
sift                        signify-osx                       sile                           silk                          
simg2img                    simgrid                           simh                           simple-amqp-client            
simple-mtpfs                simple-obfs                       simple-tiles                   simutrans                     
since                       singular                          sip                            sipcalc                       
sipp                        sipsak                            siril                          sisc-scheme                   
sispmctl                    sjk                               skaffold                       skafos                        
ski                         skinny                            skipfish                       skktools                      
sl                          slackcat                          slacknimate                    slashem                       
sleepwatcher                sleuthkit                         slimerjs                       sloccount                     
slowhttptest                slrn                              slugify                        slurm                         
smake                       smali                             smartmontools                  smartypants                   
smlnj                       smpeg                             smpeg2                         snag                          
snakemake                   snap-telemetry                    snap7                          snapcraft                     
snappy                      snappystream                      snapraid                       sngrep                        
snobol4                     snort                             snow                           snownews                      
sntop                       snzip                             socat                          soci                          
sofia-sip                   softhsm                           solarus                        solid                         
solr                        solr@5.5                          somagic                        somagic-tools                 
sonar-completion            sonar-scanner                     sonarlint                      sonarqube                     
sonarqube-lts               sops                              sord                           sound-touch                   
soundpipe                   source-highlight                  source-to-image                sourcekitten                  
sourcery                    sox                               spaceinvaders-go               spaceman-diff                 
spades                      spandsp                           spark                          sparkey                       
sparse                      spatialindex                      spatialite-gui                 spatialite-tools              
spawn-fcgi                  spdlog                            spdylay                        speech-tools                  
speedread                   speedtest-cli                     speex                          speexdsp                      
sphinx                      sphinx-doc                        spidermonkey                   spigot                        
spim                        spin                              spiped                         splint                        
spoof-mac                   spotbugs                          spring-completion              spring-loaded                 
spring-roo                  sproxy                            sql-translator                 sqlcipher                     
sqldiff                     sqlite                            sqlite-analyzer                sqliteodbc                    
sqlmap                      sqlparse                          sqoop                          sqtop                         
squashfs                    squashfuse                        squid                          squirrel                      
sratom                      sratoolkit                        src                            srclib                        
srecord                     srmio                             srt                            srtp                          
ssdb                        ssdeep                            ssed                           ssh-audit                     
ssh-copy-id                 ssh-permit-a38                    ssh-vault                      sshconfigfs                   
sshfs                       sshguard                          sshrc                          sshtrix                       
sshuttle                    ssldump                           sslh                           ssllabs-scan                  
sslmate                     sslscan                           sslsplit                       sslyze                        
ssss                        sstp-client                       st                             stanford-ner                  
stanford-parser             star                              startup-notification           statik                        
stdman                      stella                            stellar-core                   stern                         
stgit                       stk                               stlink                         stlviewer                     
stm32flash                  stockfish                         stoken                         stone                         
stone-soup                  storm                             stormlib                       stormpath-cli                 
stormssh                    stormssh-completion               stout                          stow                          
streamlink                  streamripper                      stress                         stress-ng                     
strongswan                  stubby                            stunnel                        stuntman                      
style-check                 sub2srt                           subliminal                     submarine                     
subnetcalc                  subversion                        subversion@1.8                 suil                          
suite-sparse                sundials                          superlu                        supermodel                    
supersonic                  supertux                          supervisor                     surfraw                       
suricata                    svdlibc                           svg2pdf                        svg2png                       
svgcleaner                  svgo                              svtplay-dl                     swagger-codegen               
swaks                       swfmill                           swftools                       swi-prolog                    
swift                       swift-protobuf                    swiftformat                    swiftgen                      
swiftlint                   swiftplate                        swig                           swig@3.04                     
swimat                      switchaudio-osx                   sword                          sxiv                          
syck                        sylpheed                          sync_gateway                   syncthing                     
syncthing-inotify           synfig                            synscan                        syntaxerl                     
sysbench                    sysdig                            systemc                        sz81                          
szip                        t-completion                      t1lib                          t1utils                       
ta-lib                      tag                               taglib                         tailor                        
taisei                      takt                              taktuk                         tal                           
talloc                      tarantool                         tarsnap                        tarsnap-gui                   
tarsnapper                  task                              task-spooler                   taskd                         
taskell                     tasksh                            taylor                         tbb                           
tbox                        tcc                               tccutil                        tcl-tk                        
tclap                       tcpdump                           tcpflow                        tcping                        
tcpkali                     tcpreplay                         tcpsplit                       tcpstat                       
tcptrace                    tcptraceroute                     tcptrack                       tcptunnel                     
tcsh                        td                                teapot                         tectonic                      
tee-clc                     teem                              teensy_loader_cli              teleconsole                   
telegraf                    telegram-cli                      teleport                       telnet                        
telnetd                     template-glib                     temporal_tables                tenyr                         
tepl                        term                              termbox                        terminal-notifier             
terminator                  termius                           termrec                        termshare                     
terraform                   terraform-docs                    terraform-inventory            terraform-provisioner-ansible 
terraform_landscape         terraforming                      terragrunt                     tesseract                     
testbottest                 testdisk                          testssl                        texapp                        
texi2html                   texinfo                           texmath                        textql                        
tfenv                       tgif                              tgui                           thc-pptp-bruter               
the_platinum_searcher       the_silver_searcher               thefuck                        theharvester                  
theora                      thors-serializer                  thrift                         thrift@0.9                    
thrulay                     tidy-html5                        tidyp                          tiff2png                      
tig                         tiger-vnc                         tika                           tile38                        
timedog                     timelimit                         timewarrior                    timidity                      
tin                         tinc                              tintin                         tiny-fugue                    
tinycdb                     tinyproxy                         tinysvm                        tinyxml                       
tinyxml2                    tippecanoe                        titan-server                   titlecase                     
tivodecode                  tj                                tkdiff                         tldr                          
tlsdate                     tmate                             tmpreaper                      tmpwatch                      
tmux                        tmux-cssh                         tmux-mem-cpu-load              tmux-xpanes                   
tmuxinator-completion       tn5250                            tnef                           tnftp                         
tnftpd                      tnote                             todo-txt                       todolist                      
todoman                     tofrodos                          toilet                         tokei                         
tokyo-cabinet               tokyo-dystopia                    tomcat                         tomcat-native                 
tomcat@6                    tomcat@7                          tomcat@8                       tomee-jax-rs                  
tomee-plume                 tomee-plus                        tomee-webprofile               topgit                        
tor                         torrentcheck                      torsocks                       tox                           
tpl                         tpp                               trace2html                     tracebox                      
tractorgen                  traefik                           trafficserver                  trafshow                      
traildb                     transcrypt                        translate-shell                translate-toolkit             
transmission                trash                             trash-cli                      travis                        
tre                         tree                              treecc                         treefrog                      
trezor-agent                triton                            trr                            truecrack                     
truncate                    tsung                             tta                            ttf2eot                       
ttf2pt1                     ttfautohint                       tth                            tty-clock                     
tty-solitaire               ttyd                              ttygif                         ttyrec                        
tundra                      tunnel                            tup                            tvnamer                       
twarc                       twemcache                         twine-pypi                     two-lame                      
twoping                     twtxt                             txr                            txt2tags                      
typesafe-activator          typescript                        typespeed                      u-boot-tools                  
uade                        uberftp                           ubertooth                      ucg                           
uchardet                    ucl                               ucommon                        ucon64                        
ucspi-tcp                   udis86                            udns                           udptunnel                     
udpxy                       udunits                           ufraw                          uftp                          
uggconv                     uhd                               umlet                          unac                          
unar                        unarj                             unbound                        uncrustify                    
uni2ascii                   unibilium                         unicorn                        unifdef                       
unison                      unittest                          unittest-cpp                   uniutils                      
unixodbc                    unnethack                         unoconv                        unp                           
unp64                       unpaper                           unrar                          unravel                       
unrtf                       unshield                          unyaffs                        unzip                         
upscaledb                   uptimed                           upx                            urbit                         
urdfdom                     urdfdom_headers                   urh                            uriparser                     
urlview                     uru                               urweb                          usbmuxd                       
userspace-rcu               utf8proc                          util-linux                     utimer                        
uudeview                    uwsgi                             v                              v8                            
v8@3.15                     vagrant-completion                vala                           valabind                      
valgrind                    vamp-plugin-sdk                   vapoursynth                    varnish                       
varnish@4                   vassh                             vault                          vault-cli                     
vaulted                     vavrdiasm                         vbindiff                       vc4asm                        
vcdimager                   vcftools                          vcprompt                       vcs                           
vcsh                        vde                               vdirsyncer                     veclibfort                    
vecx                        vegeta                            vera++                         verilator                     
vert                        vert.x                            vf                             vgmstream                     
vice                        viennacl                          viewvc                         vifm                          
vilistextum                 vim                               vim@7.4                        vimpager                      
vimpc                       vip                               vips                           virtualhost.sh                
virtualpg                   virtuoso                          vis                            visionmedia-watch             
visitors                    visp                              vit                            vitetris                      
vmdktool                    vmtouch                           vncsnapshot                    vnstat                        
vnu                         volatility                        voldemort                      voms                          
vorbis-tools                vorbisgain                        voro++                         vowpal-wabbit                 
vpcs                        vramsteg                          vrpn                           vsftpd                        
vstr                        vsts-cli                          vtclock                        vte                           
vte3                        vtk                               vttest                         vultr                         
w-calc                      w3m                               wabt                           wait_on                       
wakatime-cli                wakeonlan                         walkmod                        wallpaper                     
wandio                      waon                              warp                           wartremover                   
watch                       watch-sim                         watchexec                      watchman                      
watson                      wavpack                           wbox                           wdc                           
wdfs                        wdiff                             weaver                         web100clt                     
webalizer                   webarchiver                       webdis                         webfs                         
webkit2png                  weboob                            webp                           webpack                       
websocketd                  webtorrent-cli                    weechat                        wego                          
weighttp                    wellington                        wemux                          wesnoth                       
wget                        wgetpaste                         whatmask                       whatmp3                       
when                        whirr                             whitedb                        whohas                        
whois                       widelands                         wifi-password                  wiggle                        
wiki                        wildfly-as                        willgit                        wimlib                        
wine                        winetricks                        winexe                         wiredtiger                    
wireguard-go                wireguard-tools                   wiremock-standalone            wireshark                     
wirouter_keyrec             with-readline                     wla-dx                         wmctrl                        
woboq_codebrowser           woff2                             wolfssl                        woof                          
wordnet                     wordplay                          wp-cli                         wp-cli-completion             
wpcli-completion            wpscan                            wput                           wrangler                      
write-good                  writerperfect                     wrk                            wrk-trello                    
wry                         wslay                             wtf                            wu                            
wumpus                      wv                                wv2                            wwwoffle                      
wxmac                       wxmaxima                          wxpython                       wy60                          
x11vnc                      x264                              x265                           x3270                         
xa                          xalan-c                           xapian                         xar-mackyle                   
xaric                       xbee-comm                         xboard                         xcenv                         
xclip                       xcodegen                          xcproj                         xctool                        
xcv                         xdelta                            xdot                           xdotool                       
xerces-c                    xhyve                             xidel                          xlispstat                     
xlslib                      xmake                             xml-coreutils                  xml-security-c                
xml-tooling-c               xml2                              xmlcatmgr                      xmlformat                     
xmlrpc-c                    xmlsectool                        xmlsh                          xmlstarlet                    
xmlto                       xmltoman                          xmoto                          xmount                        
xmp                         xmrig                             xonsh                          xorriso                       
xpa                         xpdf                              xplanet                        xqilla                        
xrick                       xrootd                            xsane                          xsd                           
xshogi                      xsimd                             xspin                          xsv                           
xsw                         xtail                             xtensor                        xtitle                        
xu4                         xvid                              xxhash                         xz                            
yacas                       yadm                              yaf                            yafc                          
yajl                        yamcha                            yamdi                          yaml-cpp                      
yamllint                    yank                              yara                           yarn                          
yarn-completion             yash                              yasm                           yaws                          
yaz                         yaze-ag                           yazpp                          yconalyzer                    
ydcv                        ydiff                             yelp-tools                     yeti                          
yetris                      ykclient                          ykman                          ykneomgr                      
ykpers                      yle-dl                            yosys                          you-get                       
youtube-dl                  yq                                yubico-piv-tool                yuicompressor                 
yydecode                    z                                 z3                             z80asm                        
z80dasm                     zabbix                            zanata-client                  zbackup                       
zbar                        zboy                              zdelta                         zebra                         
zelda-roth-se               zenity                            zero-install                   zeromq                        
zig                         zile                              zim                            zimg                          
zinc                        zint                              zip                            zita-convolver                
zlib                        zmap                              zmqpp                          znapzend                      
znc                         zookeeper                         zopfli                         zorba                         
zork                        zpaq                              zplug                          zpython                       
zsdx                        zsh                               zsh-autosuggestions            zsh-completions               
zsh-git-prompt              zsh-history-substring-search      zsh-lovers                     zsh-navigation-tools          
zsh-syntax-highlighting     zshdb                             zssh                           zstd                          
zsxd                        zsync                             zurl                           zxcc                          
zxing-cpp                   zyre                              zzuf                           zzz                           

                         



```
# C

##  caffe   Caffe （卷积神经网络框架）
```
caffe，全称Convolutional Architecture for Fast Feature Embedding。是一种常用的深度学习框架，主要应用在视频、图像处理方面的应用上。



caffe是一个清晰，可读性高，快速的深度学习框架。作者是贾扬清，加州大学伯克利的ph.D，现就职于Facebook。

caffe的官网:   
  http://caffe.berkeleyvision.org/
Caffe 测试Demo：    能分析上传的照片得出一些图片描述的信息
http://demo.caffe.berkeleyvision.org/

入门介绍：      https://blog.csdn.net/cham_3/article/details/72141753
https://blog.csdn.net/u013108511/article/details/79265659
https://blog.csdn.net/haima1998/article/details/78950106



安装命令：   brew install caffe
有兴趣再来深入查看 具体内容
```




## cowsay  显示动物画面
```
安装命令 ：    brew install cowsay


使用方法：
echo  Hello World! | cowsay
 ______________
< Hello World! >
 --------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||




```

## ctop  查看进程情况的工具
```
ctop是一个新的基于命令行的工具，它可用于在容器层级监控进程。
ctop requires no arguments and uses Docker host variables by default.
Ctop需要使用容器docker来完成检测性能的场景

简介：   https://www.linuxidc.com/Linux/2015-05/117344.html


git地址：   https://github.com/bcicen/ctop/


安装命令：    brew install  ctop

```
<img src="https://github.com/bcicen/ctop/raw/master/_docs/img/grid.gif"/>
# D

## dark-mode Mac黑暗主题命令
```
简介：   dark-mode 是一款可以在命令行切换Macbook暗黑主题的命令工具。


安装命令：
brew install dark-mode



dark-mode --help

  Usage
    $ dark-mode [command]

  Commands
    <none>  Toggle dark mode
    on      Enable dark mode
    off     Disable dark mode
    status  Dark mode status


dark-mode      //黑暗主题切换
```


## dex2jar
[dex2jar](Android_RevertFile/dex2jar)
[jd-gui](Android_RevertFile/jd-gui)
命令:  dex2jar classes.dex

dex2jar
   作用：将apk反编译成Java源码（classes.dex转化成jar文件）

jd-gui
   作用：查看APK中classes.dex转化成出的jar文件，即源码文件

<img src="https://raw.githubusercontent.com/ZukGit/Android_Revert/master/Android_RevertImage/dex2jar.png" width = "75%" height="75%"/>

```
第一步：找到dex2jar和jd-gui两个文件

第二步：然后在把你需要反编译的apk包，后缀名改成zip，再解压，得到相应的解压文件

第三步：找到你解压后的文件里面的classes.dex文件，同时打开dex2jar-2.0文件夹，将classes.dex文件拷贝到dex2jar-2.0文件中

第四步：在dex2jar-2.0文件中shift+鼠标右键选择“在此处打开命令窗口（W）”，这样就进入到了dex2jar-2.0文件目录下，当然也可以自己通过cmd去指定dex2jar-2.0文件的路径，效果都是一样的

第五步：在此时的命令窗口中输入:d2j-dex2jar classes.dex 然后回车（注意：d2j-dex2jar和classes.dex之间在输入的时候有空格），执行完成后，会多出一个classes-dex2jar.jar文件，这个文件就是我们需要的

第六步：找到你下载的zip包解压后文件中的jd-gui文件夹，然后打开里面有jd-gui.exe文件,可以双击直接打开，然后，右上角 File —>Open File，将上面得到的classes-dex2jar.jar文件打开，就会得到我们最终想要的到的东西。
里面有别人引用的jar包，也有别人自定义项目工程的包，反正各种【代码资源】都在这了，【图片声音资源这里得不到的】，不过在第一次，你将apk后缀名改成zip解压后得到的解压文件里面就可以拿到别人的资源了
```






## dhcpdump
```
Monitor DHCP Traffic For Debugging Purpose

https://www.cyberciti.biz/faq/linux-unix-dhcpdump-monitor-dhcp-traffic/

Monitor DHCP traffic for debugging purposes
https://www.mavetju.org/

DHCP（Dynamic Host Configuration Protocol，动态主机配置协议）是一个局域网的网络协议，
使用UDP协议工作， 主要有两个用途：给内部网络或网络服务供应商自动分配IP地址，
给用户或者内部网络管理员作为对所有计算机作中央管理的手段，在RFC 2131中有详细的描述



DHCP 报文结构类型： https://blog.csdn.net/jl2011/article/details/49334297


安装命令：     brew install dhcpdump

使用命令；
dhcpdump -i en0
相同效果的 tcpdump命令
tcpdump -lenx -i eth0 -s 1500  8850   // 最后的8850是端口


```



```
帮助手册：
DESCRIPTION
       This command parses the output of tcpdump to display the dhcp-packets for easier checking and debugging.

USAGE
       dhcpdump -i /dev/fxp0

       If you want to filter a specific Client Hardware Address (CHADDR), then you can specifiy it as a regular
       expressions:

       dhcpdump -i /dev/fxp0 -h ^00:c0:4f

       This will display only the packets with Client Hardware Addresses which start with 00:c0:4f.




OUTPUT
         TIME: 15:45:02.084272
           IP: 0.0.0.0.68 (0:c0:4f:82:ac:7f) > 255.255.255.255.67 (ff:ff:ff:ff:ff:ff)
           OP: 1 (BOOTPREQUEST)
        HTYPE: 1 (Ethernet)
         HLEN: 6
         HOPS: 0
          XID: 28f61b03
         SECS: 0
        FLAGS: 0
       CIADDR: 0.0.0.0
       YIADDR: 0.0.0.0
       SIADDR: 0.0.0.0
       GIADDR: 0.0.0.0
       CHADDR: 00:c0:4f:82:ac:7f:00:00:00:00:00:00:00:00:00:00
        SNAME: .
        FNAME: .
       OPTION:  53 (  1) DHCP message type         3 (DHCPREQUEST)
       OPTION:  54 (  4) Server identifier         130.139.64.101
       OPTION:  50 (  4) Request IP address        130.139.64.143
       OPTION:  55 (  7) Parameter Request List      1 (Subnet mask)
                                                     3 (Routers)
                                                    58 (T1)
                                                    59 (T2)

       At the option field, the first field is the value of the option, the second one (between brackets) is the
       length of the option-datafield, the third field is the name of the option, the fourth field is the data of the
       option.



```


```
输出   打开WIFI 从当前网络的dhcp服务器获取IP地址： 
[/Users/aaa]➜  ~ dhcpdump -i en0
  TIME: 2018-08-27 17:01:07.551
    IP: 0.0.0.0 (14:10:9f:e5:db:e9) > 255.255.255.255 (ff:ff:ff:ff:ff:ff)
    OP: 1 (BOOTPREQUEST)
 HTYPE: 1 (Ethernet)
  HLEN: 6
  HOPS: 0
   XID: b25552fc
  SECS: 0
 FLAGS: 0
CIADDR: 0.0.0.0
YIADDR: 0.0.0.0
SIADDR: 0.0.0.0
GIADDR: 0.0.0.0
CHADDR: 14:10:9f:e5:db:e9:00:00:00:00:00:00:00:00:00:00
 SNAME: .
 FNAME: .
OPTION:  53 (  1) DHCP message type         3 (DHCPREQUEST)
OPTION:  55 ( 10) Parameter Request List      1 (Subnet mask)
					    121 (Classless Static Route)
					      3 (Routers)
					      6 (DNS server)
					     15 (Domainname)
					     					    119 (Domain Search)
					    252 (MSFT - WinSock Proxy Auto Detect)
					     95 (LDAP)
					     44 (NetBIOS name server)
					     46 (NetBIOS node type)

OPTION:  57 (  2) Maximum DHCP message size 1500
OPTION:  61 (  7) Client-identifier         01:14:10:9f:e5:db:e9
OPTION:  50 (  4) Request IP address        192.168.73.241
OPTION:  51 (  4) IP address leasetime      7776000 (12w6d)
OPTION:  12 ( 15) Host name                 zhuzhenjiedeMBP
---------------------------------------------------------------------------

  TIME: 2018-08-27 17:01:07.551
    IP: 1.1.1.1 (cc:16:7e:98:ee:f1) > 192.168.73.241 (14:10:9f:e5:db:e9)
    OP: 2 (BOOTPREPLY)
 HTYPE: 1 (Ethernet)
   HLEN: 6
  HOPS: 0
   XID: b25552fc
  SECS: 0
 FLAGS: 0
CIADDR: 0.0.0.0
YIADDR: 192.168.73.241    【从 dhcp服务器获得的ip地址 】
SIADDR: 0.0.0.0
GIADDR: 0.0.0.0
CHADDR: 14:10:9f:e5:db:e9:00:00:00:00:00:00:00:00:00:00
 SNAME: .
 FNAME: .
OPTION:  53 (  1) DHCP message type         5 (DHCPACK)
OPTION:  58 (  4) T1                        172800 (2d)
OPTION:  59 (  4) T2                        302400 (3d12h)
OPTION:  51 (  4) IP address leasetime      345600 (4d)
OPTION:  54 (  4) Server identifier         1.1.1.1
OPTION:   1 (  4) Subnet mask               255.255.248.0
OPTION:   3 (  4) Routers                   192.168.79.254
OPTION:   6 (  8) DNS server                192.168.60.6,192.168.3.2
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (  0) pad
OPTION:   0 (255) pad
---------------------------------------------------------------------------




```
## docker
```
Pack, ship and run any application as a lightweight container
以轻量级容器的形式打包、装载和运行任何应用程序。



docker教程：    http://www.runoob.com/docker/docker-tutorial.html

安装命令：    brew   install   docker



```



```
帮助手册： 
[/Users/aaa]➜  ~ docker -h
Flag shorthand -h has been deprecated, please use --help

Usage:	docker [OPTIONS] COMMAND

A self-sufficient runtime for containers 【一个容器自动运行所需】

Options:
      --config string      Location of client config files (default "/Users/aaa/.docker") 【客户端配置文件位置（默认 "/home/docker/.docker"）】
  -D, --debug              Enable debug mode 【 启用调试 】
  -H, --host list          Daemon socket(s) to connect to   （socket链接进程守护）
  -l, --log-level string   Set the logging level ("debug"|"info"|"warn"|"error"|"fatal") (default "info")
                            【设置日志级别（“debug”|“info”|“warn”|“error”|“fatal”）（默认为“info”）】
      --tls                Use TLS; implied by --tlsverify
      --tlscacert string   Trust certs signed only by this CA (default "/Users/aaa/.docker/ca.pem")【ca证书远程用】
      --tlscert string     Path to TLS certificate file (default "/Users/aaa/.docker/cert.pem")【私钥远程用】
      --tlskey string      Path to TLS key file (default "/Users/aaa/.docker/key.pem")【公钥远程用】
      --tlsverify          Use TLS and verify the remote【远程链接验证】
  -v, --version            Print version information and quit【打印版本信息并退出】

Management Commands:
  config      Manage Docker configs  【 管理docker 配置 】
  container   Manage containers   【 管理容器】
  image       Manage images  【管理镜像】
  network     Manage networks  【联网管理】
  node        Manage Swarm nodes  【管理集群节点】
  plugin      Manage plugins   【插件管理】
  secret      Manage Docker secrets   【docker秘钥管理】
  service     Manage services  【服务管理】
  stack       Manage Docker stacks   【docker堆栈管理】
  swarm       Manage Swarm
  system      Manage Docker   【管理docker系统】
  trust       Manage trust on Docker images  【 docker 权限管理】
  volume      Manage volumes     【空间管理】

Commands:
  attach      Attach local standard input, output, and error streams to a running container
             【将本地标准输入，输出和错误流附加到正在运行的容器】
  build       Build an image from a Dockerfile
              【构建docker文件镜像】
  commit      Create a new image from a container's changes
              【创建一个镜像修改容器】
  cp          Copy files/folders between a container and the local filesystem
              【在容器和本地复制文件和文件夹】
  create      Create a new container
               【创建一个容器】
  deploy      Deploy a new stack or update an existing stack
               【部署堆栈容器】
  diff        Inspect changes to files or directories on a container's filesystem
               【检查容器系统上文件是否发生改变】
  events      Get real time events from the server
              【获取服务器实时推送】
  exec        Run a command in a running container
              【在容器中运行命令】
  export      Export a container's filesystem as a tar archive
              【将容器的文件系统导出为tar存档】
  history     Show the history of an image
              【显示镜像的历史变革】
  images      List images
               【列出所有镜像文件】
  import      Import the contents from a tarball to create a filesystem image
              【从 tarball导入文件内容到创建一个系统镜像文件】
  info        Display system-wide information
              【查看信息】
  inspect     Return low-level information on Docker objects
              【返回底层信息的docker对象】
  kill        Kill one or more running containers
               【终止一个或多个正在运行的容器】
  load        Load an image from a tar archive or STDIN
              【加载图像到一个tar归档文件或STDIN】
  login       Log in to a Docker registry
             【登陆某个docker之中】
  logout      Log out from a Docker registry
              【退出某个docker】
  logs        Fetch the logs of a container
              【获取容器运行的日志】
  pause       Pause all processes within one or more containers
              【暂停一个或多个容器进程】
  port        List port mappings or a specific mapping for the container
              【查看所有映射信息】
  ps          List containers
              【查看所有正在运行的容器】
  pull        Pull an image or a repository from a registry
               【拉取一个镜像或者仓库到本地】
  push        Push an image or a repository to a registry
              【上传镜像Image到服务器分支】
  rename      Rename a container
              【重命名一个docker容器】
  restart     Restart one or more containers
               【重新启动一个docker容器】
  rm          Remove one or more containers
               【移除容器Container】
  rmi         Remove one or more images
              【移除镜像Image】
  run         Run a command in a new container
              【在容器中运行命令】
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
              【保存镜像到本地tar压缩文件】
  search      Search the Docker Hub for images
              【在hub中搜索对应关键字的镜像】
  start       Start one or more stopped containers
               【开始关闭容器】
  stats       Display a live stream of container(s) resource usage statistics
              【展示活跃容器使用资源统计】
  stop        Stop one or more running containers
               【停止正在运行的镜像】
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
               【标记本地镜像，将其归入某一仓库】
  top         Display the running processes of a container
               【查看容器中运行的进程信息，支持 ps 命令参数】
  unpause     Unpause all processes within one or more containers
               【恢复容器中所有的进程】
  update      Update configuration of one or more containers
              【更新容器的配置】
  version     Show the Docker version information
              【显示docker的版本信息】
  wait        Block until one or more containers stop, then print their exit codes
              【阻塞运行直到容器停止，然后打印出它的退出代码】
#############容器操作命令############

Run 'docker COMMAND --help' for more information on a command.
[/Users/aaa]➜  ~





```




## docker-completion
```


```

## docx2txt
```
Converts Microsoft Office docx documents to equivalent text documents


```


# E

## exa
```
exa是ls命令的替换  能显示更人性化的数据


exa -lh

```
<img src="//../zimage/system/macos/02_shellcommand/exa.png">

# F

## fcrackzip
```
破解zip密码的工具包

```

## fd
```
一个更友好的查找文件工具 find


```
# G
## Git命令
```

git clone  git@github.com:ZukGit/Source_Code.git
git config --global user.name 'yourName'
git config --global user.email yourEmail@xxx.com
git config --global review."gerrit.huaqin.com:8081".username 101002790

git config --list


```


## Glances
```


```
<img src="https://raw.githubusercontent.com/nicolargo/glances/develop/docs/_static/glances-summary.png">

 

## grep

# H

##  hh
```


Bash and zsh shell history suggest box - easily view, navigate, search and manage your command history.

```
<img src="https://camo.githubusercontent.com/4c12b73d5b00e0716ded4366e7c84178122ce9fe/687474703a2f2f6d652e6d696e64666f726765722e636f6d2f70726f6a656374732f696d616765732f68682d616e696d617465642d30312e676966"/>


## htop
```

```

<img src="https://hisham.hm/htop/htop-2.0.png"/>


## httpflow
```


```



# I

## ifstat


## iftop (Mac/Linux 命令)
```
iftop是一款实时流量监控工具,监控TCP/IP连接等,能查看得到连接到的IP地址信息
 使用方法： iftop -i en0 -n -P
 
中间的<= =>这两个左右箭头，表示的是流量的方向。
TX：发送流量
RX：接收流量
TOTAL：总流量
cum：运行iftop到目前时间的总流量
peak：流量峰值
rates：分别表示过去 2s 10s 40s 的平均流量
（直接按 q 可退出界面）
 (直接按 n 可跳到设置界面)
```
<img src="//../zimage/system/macos/02_shellcommand/if.png">
<img src="//../zimage/system/macos/02_shellcommand/if1.png">

## ipcalc
 
## ipinfo
```
http://kyberdigi.cz/projects/ipinfo/

```
## iperf3
### iperf3 for linux

#### 安装命令
```
sudo add-apt-repository "ppa:patrickdk/general-lucid"
sudo apt-get update
sudo apt-get install iperf3

手机端安装: Magic iperf
下载地址：  https://github.com/ZukGit/SoftWare_Resource/blob/master/Android/iperf/magic_iperf.apk

测试条件:  PC连接路由器通过网线，手机连接无线路由器，使得PC和手机在同一个局域网内才能正确测试
```



#### iperf3帮助手册
```
Desktopiperf3 -h                                                                           \
Usage: iperf [-s|-c host] [options]
       iperf [-h|--help] [-v|--version]

Server or Client: // Server和Client都能使用的参数
  -p, --port      #         server port to listen on/connect to     //  -p 5055    指定端口 5055 进行测试
  -f, --format    [kmgKMG]  format to report: Kbits, Mbits, KBytes, MBytes  
                  // 1. -f Kb   2. -f KB   3. -f Mb 【默认】    4. -f MB
				  
  -i, --interval  #   seconds between periodic bandwidth reports  // 报告数据的周期  默认为 1s      -i 2   // 报告周期为2秒
                 
  -F, --file name           xmit/recv the specified file   // 把输出保存到文件    -F ./xxx.xmit  -F ./xxx.recv
  -A, --affinity n/n,m      set CPU affinity                 
  -B, --bind      <host>    bind to a specific interface
  -V, --verbose             more detailed output      -V   // 打印更多输出数据
  -J, --json                output in JSON format      -J  // 以Json的格式作为输出
  --logfile f               send output to a log file      // 设置 输出到一个 log文件
  -d, --debug               emit debugging output
  -v, --version             show version information and quit
  -h, --help                show this message and quit
Server specific: // 【只有 Server才能使用的参数】
  -s, --server              run in server mode
  -D, --daemon              run the server as a daemon
  -I, --pidfile file        write PID file
  -1, --one-off             handle one client connection then exit
Client specific:  // 【只有Client端 才能使用的参数】
  -c, --client    <host>    run in client mode, connecting to <host>  
  -u, --udp                 use UDP rather than TCP
  -b, --bandwidth #[KMG][/#] target bandwidth in bits/sec (0 for unlimited)
                            (default 1 Mbit/sec for UDP, unlimited for TCP)
                            (optional slash and packet count for burst mode)
  -t, --time      #         time in seconds to transmit for (default 10 secs)
  -n, --bytes     #[KMG]    number of bytes to transmit (instead of -t)
  -k, --blockcount #[KMG]   number of blocks (packets) to transmit (instead of -t or -n)
  -l, --len       #[KMG]    length of buffer to read or write
                            (default 128 KB for TCP, 8 KB for UDP)
  --cport         <port>    bind to a specific client port (TCP and UDP, default: ephemeral port)
  -P, --parallel  #         number of parallel client streams to run
  -R, --reverse             run in reverse mode (server sends, client receives)
  -w, --window    #[KMG]    set window size / socket buffer size
  -C, --congestion <algo>   set TCP congestion control algorithm (Linux and FreeBSD only)
  -M, --set-mss   #         set TCP/SCTP maximum segment size (MTU - 40 bytes)
  -N, --no-delay            set TCP/SCTP no delay, disabling Nagle's Algorithm
  -4, --version4            only use IPv4
  -6, --version6            only use IPv6
  -S, --tos N               set the IP 'type of service'
  -L, --flowlabel N         set the IPv6 flow label (only supported on Linux)
  -Z, --zerocopy            use a 'zero copy' method of sending data
  -O, --omit N              omit the first n seconds
  -T, --title str           prefix every output line with this string
  --get-server-output       get results from server
  --udp-counters-64bit      use 64-bit counters in UDP test packets
  --no-fq-socket-pacing     disable fair-queuing based socket pacing
                            (Linux only)

[KMG] indicates options that support a K/M/G suffix for kilo-, mega-, or giga-

iperf3 homepage at: http://software.es.net/iperf/
Report bugs to:     https://github.com/esnet/iperf

```

#### 查看PC本地网络连接路由器的IP地址
inet 192.168.1.7
```
ifconfig
eth1: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.1.7  netmask 255.255.255.0  broadcast 192.168.1.255
        inet6 fe80::fd84:f739:9fdf:434b  prefixlen 64  scopeid 0x0<global>
        ether 54:e1:ad:e5:34:0c  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 1500
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x0<global>
        loop  (Local Loopback)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

wifi3: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.137.1  netmask 255.255.255.0  broadcast 192.168.137.255
        inet6 fe80::f0e3:425b:7c26:ed46  prefixlen 64  scopeid 0x0<global>
        unspec 02-E1-8C-D8-25-61-00-00-00-00-00-00-00-00-00-00  (UNSPEC)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
```

#### 上行下行速率测试_demo1

##### PC运行iperf3的服务端 
```
.\iperf3.exe -s -i 1 -p  5364               // Windows 下  PC 端运行 Iperf3的服务端

iperf3 -s -i 1 -p  5364            // Linux 下   PC 端运行 Iperf3的服务端

// 输出如下
iperf3 -s -i 1 -p  5364                                             
-----------------------------------------------------------
Server listening on 5364
-----------------------------------------------------------
```
##### Mobile端运行Magic iperf 作为客户端Client 

```
192.168.0.105 是PC 服务端Service的 IPv4地址

手机下行【↓】 TCP:          iperf3 -c 192.168.0.105 -t 300 -i 1  -p 5364 -R 
手机下行【↓】 UDP:          iperf3 -c 192.168.0.105 -t 300 -i 1  -p 5364 -u  -R 
手机上行【↑】 TCP:          iperf3 -c 192.168.0.105 -t 300 -i 1  -p 5364  
手机上行【↑】 UDP:          iperf3 -c 192.168.0.105 -t 300 -i 1  -p 5364 -u

```




#### 上行下行速率测试_demo2

##### Mobile端运行Magic iperf 作为服务端server 
```
-s -p 5055     //  在端口5055 开启服务 magic-iperf 会显示 ip地址  info:  192.168.1.5

```


##### PC运行iperf3的客户端client 
```
192.168.1.5  是Mobile服务端Service的 IPv4地址 info地址  192.168.1.5


手机下行【↓】 TCP:          iperf3 -c 192.168.1.5 -t 300 -i 1  -p 5055 
手机下行【↓】 UDP:          iperf3 -c 192.168.1.5 -t 300 -i 1  -p 5055 -u 
手机上行【↑】 TCP:          iperf3 -c 192.168.1.5 -t 300 -i 1  -p 5055 -R
手机上行【↑】 UDP:          iperf3 -c 192.168.1.5 -t 300 -i 1  -p 5055 -u -R


```




###### Iperf3输出Log
```

iperf3 -c 192.168.1.5 -t 20 -i 1  -p 5055    // -c 上面 server端的Ip地址  -t 运行时间 -p 5055 // 指定端口5055

输出结果：
iperf3 -c 192.168.1.5 -t 20 -i 1  -p 5055
Connecting to host 192.168.1.5, port 5055
[  4] local 192.168.1.7 port 6453 connected to 192.168.1.5 port 5055
iperf3: getsockopt - Protocol not available
[ ID] Interval           Transfer     Bandwidth       Retr  Cwnd
[  4]   0.00-1.00   sec  1.00 MBytes  8.39 Mbits/sec  1296506937   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   1.00-2.00   sec  1.38 MBytes  11.5 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   2.00-3.00   sec  1.00 MBytes  8.38 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   3.00-4.00   sec  1.50 MBytes  12.6 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   4.00-5.00   sec  1.25 MBytes  10.5 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   5.00-6.00   sec  1.62 MBytes  13.6 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   6.00-7.00   sec  1.25 MBytes  10.5 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   7.00-8.00   sec  1.50 MBytes  12.6 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   8.00-9.00   sec  1.12 MBytes  9.44 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]   9.00-10.00  sec  1.38 MBytes  11.5 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  10.00-11.00  sec  2.00 MBytes  16.8 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  11.00-12.00  sec  1.50 MBytes  12.6 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  12.00-13.00  sec  0.00 Bytes  0.00 bits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  13.00-14.00  sec  0.00 Bytes  0.00 bits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  14.00-15.00  sec  3.38 MBytes  28.3 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  15.00-16.00  sec  5.50 MBytes  46.1 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  16.00-17.00  sec  6.25 MBytes  52.4 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  17.00-18.00  sec  5.12 MBytes  43.0 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  18.00-19.00  sec  3.25 MBytes  27.3 Mbits/sec    0   0.00 Bytes
iperf3: getsockopt - Protocol not available
[  4]  19.00-20.00  sec  1.75 MBytes  14.7 Mbits/sec  2998460359   0.00 Bytes
- - - - - - - - - - - - - - - - - - - - - - - - -
[ ID] Interval           Transfer     Bandwidth       Retr
[  4]   0.00-20.00  sec  41.8 MBytes  17.5 Mbits/sec    0             sender
[  4]   0.00-20.00  sec  41.7 MBytes  17.5 Mbits/sec                  receiver


```




##### 手机server端【↑】 PC client端【↓】
```
测试的是手机的上行速率
```
###### Mobile端运行Magic iperf 作为服务端server 【↑】
```
-s -p 5055     //  在端口5055 开启服务 magic-iperf 会显示 ip地址  info:  192.168.1.5

```


###### PC运行iperf3的客户端client 【↓】

```
-R 参数把数据传输反向 所以能实现 数据反向
iperf3 -c 192.168.1.5 -t 20 -i 1  -p 5055 -R   // -c 上面 server端的Ip地址  -t 运行时间 -p 5055 // 指定端口5055

```


```
输出结果：
iperf3 -c 192.168.1.5 -t 20 -i
 1  -p 5055 -R
Connecting to host 192.168.1.5, port 5055
Reverse mode, remote host 192.168.1.5 is sending
[  4] local 192.168.1.7 port 6450 connected to 192.168.1.5 port 5055
iperf3: getsockopt - Protocol not available
[ ID] Interval           Transfer     Bandwidth
[  4]   0.00-1.00   sec  5.59 MBytes  46.9 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   1.00-2.00   sec  5.64 MBytes  47.3 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   2.00-3.00   sec  5.98 MBytes  50.1 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   3.00-4.00   sec  6.33 MBytes  53.1 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   4.00-5.00   sec  6.08 MBytes  51.0 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   5.00-6.00   sec  6.53 MBytes  54.8 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   6.00-7.00   sec  6.13 MBytes  51.4 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   7.00-8.00   sec  6.25 MBytes  52.4 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   8.00-9.00   sec  6.20 MBytes  52.0 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]   9.00-10.00  sec  6.13 MBytes  51.4 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  10.00-11.00  sec  6.52 MBytes  54.7 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  11.00-12.00  sec  6.02 MBytes  50.5 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  12.00-13.00  sec  5.90 MBytes  49.5 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  13.00-14.00  sec  6.25 MBytes  52.5 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  14.00-15.00  sec  6.08 MBytes  51.0 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  15.00-16.00  sec  6.05 MBytes  50.7 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  16.00-17.00  sec  6.31 MBytes  52.9 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  17.00-18.00  sec  5.93 MBytes  49.7 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  18.00-19.00  sec  6.33 MBytes  53.1 Mbits/sec
iperf3: getsockopt - Protocol not available
[  4]  19.00-20.00  sec  6.32 MBytes  53.0 Mbits/sec
- - - - - - - - - - - - - - - - - - - - - - - - -
[ ID] Interval           Transfer     Bandwidth
[  4]   0.00-20.00  sec   123 MBytes  51.5 Mbits/sec                  sender
[  4]   0.00-20.00  sec   123 MBytes  51.5 Mbits/sec                  receiver


```
# J

## jp2a


 
# K
# L

## lolcat

# M

##  mplayer
```
mac的播放器  类似于  Linux下的  slv  并有对应的 shell 命令  mplayershell




```

## m-cli
```


```


# N

## ncdu命令 （Linux Mac）
```
ncdu 命令可以用来查看和分析 Linux/Mac 中各目录对磁盘空间占用情况的工具

使用方法：   ncdu   【显示当前shell路径内文件 文件夹的大小】按键N-----以name进行排序显示
按键S-----以size大小进行排序显示
```
<img src="//../zimage/system/macos/02_shellcommand/3.png">

# O


## osxutils
```

```


# P

## PS宏   Shell脚本提示符
```
echo $PS1
${ret_status} %{$fg[cyan]%}%c%{$reset_color%} $(git_prompt_info)

【设置显示的样式】
PS1="[XXXXX的MacPro-\`pwd\`]${ret_status} %{$fg[cyan]%}%c%{$reset_color%} $(git_prompt_info)"    【普通权限下显示】

PS1="[\h]\t:\w\$"            //  【root 权限下才能正常显示】
PS1="[XXX的MacPro] \t:\w\$"       //  【root 权限下才能正常显示】

[zhuzhengjiedeMacBook-Pro]10:13:41:~$        // 【  PS1="[\h]\t:\w\$"   显示】
[XXX的MacPro] 10:14:28:~$                   // 【 PS1="[XXX的MacPro] \t:\w\$"   显示】
[XXXXX的MacPro-/Users/aaa]➜  ~            //【脚本提示显示】

```


## pandoc
```
https://pandoc.org/

```
<img src="//../zimage/system/macos/02_shellcommand/pandoc.png">


## piknik

# Q
# R
# S

## source 命令
```

source .bashrc       //立即更新环境变量 

```



## scrcpy
```
Display and control your Android device

```


## screenFetch

## sjk
```
	Swiss Java Knife

```

##  subnetcalc
```
https://www.uni-due.de/~be0001/subnetcalc/

```
# T

## tcpdump
```


```


##  tcpflow

## tcpkali

## tcptrack
```
tcptrack - Monitor TCP connections on the network


```


## terminator
```
多终端一个窗口
```
<img src="http://1.bp.blogspot.com/-pBBJoLyOUbs/Ti9BfXqRu7I/AAAAAAAADUk/Uw-4Zy9pAQU/s320/2008-08-17-terminator.png" />


## thrulay



## tor


## tree


# U
# V
# W
## w命令
<img src="//../zimage/system/macos/02_shellcommand/w.png">

```
查看到当前登录系统的用户是谁
```

## wifi-password


## wine


# X
# Y
## yum命令 （Linux-Shell）
```
yum 是linux环境安装软件包的一种方式   Shell前端软件包管理器
基于RPM包管理，能够从指定的服务器自动下载RPM包并且安装，可以自动处理依赖性关系，
并且一次安装所有依赖的软件包，无须繁琐地一次次下载、安装。
```
# Z

## zsh shell终端 （Mac\Linux）
```
zsh是shell的一个版本,它的功能是所有的shell版本中最为强大的一个

安装命令(Mac): brew install zsh

快捷键： 
Ctrl + D       // 拆分窗口
Ctrl +Shift +D    // 还原拆分窗口
xxx + Tab  //   可显示可能的命令
窗口 》 将窗口存储为组   【该功能可实现打开指定路径的shell】

```

## zsh-history-substring-search
```


```

## zsh-lovers


##  zsh-syntax-highlighting

## zsh-navigation-tools

## zsh-git-prompt


## zsh-completions


## zsh-autosuggestions