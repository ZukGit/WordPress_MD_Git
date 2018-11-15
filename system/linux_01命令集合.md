

# A

## alias 定义别名
```
15.alias    为命令定义别名  更快速
alias suz="/usr/local/sh_expect_work/suz.sh"
alias cmt="/usr/local/sh_expect_work/cmt.sh"
alias cdsh='cd /usr/local/sh_expect_work/'
alias cdgit='cd  /Users/aaa/Desktop/code_place/zzj_git/'




```

# C


## chmod   改变文件权限

```
chmod 777 1.txt    改变文件权限  
4  r   可读
2  w   可写
1  x   可执行     
默认与ls -l 显示的权限匹配
ls -l 
rwx 一组
drwxrwxrwx  2 ubuntu ubuntu       4096 11月 29  2014 模板/
drwxrwxrwx  2 ubuntu ubuntu       4096 11月 29  2014 视频/
drwxrwxrwx  2 ubuntu ubuntu       4096  5月 26 10:07 图片/



17.umask  和 touch 1.txt   默认创建文件的权限
umask 显示为  0002   表示意思是  掩码  需要屏蔽掉的权限     其中对于文件 全权限的值就是666   对于文件夹全权限的值就是777
ls -l touch 1.txt    显示为 664    -rw-rw-r--    【全权限666去掉了umask 002掩码之后的权限664】

ubuntu@ubuntu:~$ mkdir abc
ubuntu@ubuntu:~$ ll  |  grep abc
drwxrwxr-x  2 ubuntu ubuntu       4096  5月 29 13:57 abc/   【全权限777去掉了umask 002掩码之后的权限775】

设置权限  掩码     命令
umask     000    
umask     001     去掉可执行x权限
umask     002     去掉可写w权限
umask     003     去掉可写w权限  + 可执行x权限
umask     004     去掉可读r权限
umask     005     去掉可读r权限  + 可执行x权限
umask     006     去掉可读r权限  + 可写w权限
umask     007     去掉可读r权限  + 可写w权限  + 可执行x权限




```

## cat 命令

### cat /proc/meminfo 

```
1.cat /proc/meminfo  察看系统内存使用情况
root@ubuntu:/home/ubuntu# cat /proc/meminfo 
MemTotal:       16349608 kB   【总共16GB】
MemFree:        14963176 kB   【空闲内存总共15GB】
Buffers:           48288 kB
Cached:           695148 kB
SwapCached:            0 kB
Active:           524512 kB
Inactive:         617396 kB
Active(anon):     399888 kB
Inactive(anon):   249604 kB
Active(file):     124624 kB
Inactive(file):   367792 kB
Unevictable:           0 kB
Mlocked:               0 kB
SwapTotal:      19528744 kB  【可交换空间swap有19GB】
SwapFree:       19528744 kB
Dirty:              1180 kB
Writeback:             0 kB
AnonPages:        398472 kB
Mapped:           193176 kB
Shmem:            251020 kB
Slab:              56904 kB
SReclaimable:      28528 kB
SUnreclaim:        28376 kB
KernelStack:        5056 kB
PageTables:        28512 kB
NFS_Unstable:          0 kB
Bounce:                0 kB
WritebackTmp:          0 kB
CommitLimit:    27703548 kB
Committed_AS:    4319208 kB
VmallocTotal:   34359738367 kB
VmallocUsed:      384836 kB
VmallocChunk:   34359346684 kB
HardwareCorrupted:     0 kB
AnonHugePages:     30720 kB
HugePages_Total:       0
HugePages_Free:        0
HugePages_Rsvd:        0
HugePages_Surp:        0
Hugepagesize:       2048 kB
DirectMap4k:       85584 kB
DirectMap2M:    16607232 kB

```

###  cat /proc/cpuinfo
```
2.cat /proc/cpuinfo   察看系统处理器情况
root@ubuntu:/home/ubuntu# cat /proc/cpuinfo 
processor	: 0
vendor_id	: GenuineIntel
cpu family	: 6
model		: 58
model name	: Intel(R) Core(TM) i5-3470 CPU @ 3.20GHz
stepping	: 9
microcode	: 0x19
cpu MHz		: 1600.000
cache size	: 6144 KB
physical id	: 0
siblings	: 4
core id		: 0
cpu cores	: 4
```



###  cat /proc/version
```
3.cat /proc/version   察看系统版本
root@ubuntu:/proc# cat version
Linux version 3.13.0-32-generic (buildd@phianna) (gcc version 4.6.3 (Ubuntu/Linaro 4.6.3-1ubuntu5) ) 
#57~precise1-Ubuntu SMP Tue Jul 15 03:51:20 UTC 2014

```




###  cat /proc/version
```
4.cat /proc/devices   察看当前设备
root@ubuntu:/proc# cat devices 
Character devices:   【字符设备】
  1 mem
  4 /dev/vc/0
  4 tty
  4 ttyS
  5 /dev/tty
  5 /dev/console
  5 /dev/ptmx
  5 ttyprintk
  6 lp
  7 vcs
 10 misc
 13 input
 21 sg
 29 fb
 99 ppdev
108 ppp
116 alsa
128 ptm
136 pts
180 usb
189 usb_device
216 rfcomm
226 drm
251 hidraw
252 bsg
253 watchdog
254 rtc

Block devices:   【块设备】
  1 ramdisk
259 blkext
  7 loop
  8 sd
  9 md
 11 sr
 65 sd
135 sd
252 device-mapper
253 virtblk
254 mdp
```



# d


## df 

```
11.  df -h   【察看嘎u载磁盘的使用情况】
df -h
ubuntu@ubuntu:~$ df -h
文件系统        容量  已用  可用 已用% 挂载点
/dev/sdb1        65G  5.1G   56G    9% /
udev            7.8G  4.0K  7.8G    1% /dev
tmpfs           1.6G  1.1M  1.6G    1% /run
none            5.0M     0  5.0M    0% /run/lock
none            7.8G  372K  7.8G    1% /run/shm
/dev/sdb5       915M   40M  824M    5% /boot
/dev/sdb7       137G   28G  102G   22% /home
```


#  e

## echo

```
echo $PATH    察看单个系统环境变量     PATH全局变量用于系统命令所在目录的集合，系统识别的命令都在该目录集合下 否则不识别
ubuntu@ubuntu:~$ echo $PATH
/home/ubuntu/bin:/home/ubuntu/SDK/adt-bundle-linux-x86_64/sdk/platform-tools:/home/ubuntu/bin:/usr/lib/lightdm/lightdm:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/lib/jvm/java-1.7.0-openjdk-amd64/bin
u



```

# g


## grep 搜索

```
12.grep  搜索察看文档中的多个关键字
cat 1.txt  | grep -e "4096"   -e socket
```


# i

##  ipcs -m   察看当前进程共享内存页面

```
5.ipcs -m  【察看当前进程共享内存页面】
root@ubuntu:/proc# ipcs 

------------ 共享内存段 --------------
键           shmid      拥有者     权限        字节      nattch      状态      
0x00000000   0          ubuntu     600        393216     2          目标       
0x00000000   32769      ubuntu     600        393216     2          目标       
0x00000000   65538      ubuntu     600        393216     2          目标       

--------- 信号量数组 -----------
键         semid      拥有者     权限     nsems     
0x00000000 32768      root       666        1         

--------- 消息队列 -----------
键        msqid      拥有者  权限     已用字节数 消息      
```




# k

## kill

```
9.kill   命令用于给进程发送信号量


ubuntu@ubuntu:~$ kill -l     【信号量】
 1) SIGHUP	 2) SIGINT	 3) SIGQUIT	 4) SIGILL	 5) SIGTRAP
 6) SIGABRT	 7) SIGBUS	 8) SIGFPE	 9) SIGKILL	10) SIGUSR1
11) SIGSEGV	12) SIGUSR2	13) SIGPIPE	14) SIGALRM	15) SIGTERM
16) SIGSTKFLT	17) SIGCHLD	18) SIGCONT	19) SIGSTOP	20) SIGTSTP
21) SIGTTIN	22) SIGTTOU	23) SIGURG	24) SIGXCPU	25) SIGXFSZ
26) SIGVTALRM	27) SIGPROF	28) SIGWINCH	29) SIGIO	30) SIGPWR
31) SIGSYS	34) SIGRTMIN	35) SIGRTMIN+1	36) SIGRTMIN+2	37) SIGRTMIN+3
38) SIGRTMIN+4	39) SIGRTMIN+5	40) SIGRTMIN+6	41) SIGRTMIN+7	42) SIGRTMIN+8
43) SIGRTMIN+9	44) SIGRTMIN+10	45) SIGRTMIN+11	46) SIGRTMIN+12	47) SIGRTMIN+13
48) SIGRTMIN+14	49) SIGRTMIN+15	50) SIGRTMAX-14	51) SIGRTMAX-13	52) SIGRTMAX-12
53) SIGRTMAX-11	54) SIGRTMAX-10	55) SIGRTMAX-9	56) SIGRTMAX-8	57) SIGRTMAX-7
58) SIGRTMAX-6	59) SIGRTMAX-5	60) SIGRTMAX-4	61) SIGRTMAX-3	62) SIGRTMAX-2
63) SIGRTMAX-1	64) SIGRTMAX	


kill  进程号     【默认给进程号发送一个 TERM(15) 尽可能终止的信号】
kill -s 信号量值  进程号
kill -s 9 2512     【ps察看自己能操作的进程号】
```



# m

## mount

```
10.mount    输出系统挂载的设备列表
ubuntu@ubuntu:~$ mount
【挂载设备】   【挂载目录】   【文件系统类型】   【可范围的权限】
/dev/sdb1 on / type ext4 (rw,errors=remount-ro)
proc on /proc type proc (rw,noexec,nosuid,nodev)
sysfs on /sys type sysfs (rw,noexec,nosuid,nodev)
none on /sys/fs/fuse/connections type fusectl (rw)
none on /sys/kernel/debug type debugfs (rw)
none on /sys/kernel/security type securityfs (rw)
udev on /dev type devtmpfs (rw,mode=0755)
devpts on /dev/pts type devpts (rw,noexec,nosuid,gid=5,mode=0620)
tmpfs on /run type tmpfs (rw,noexec,nosuid,size=10%,mode=0755)
none on /run/lock type tmpfs (rw,noexec,nosuid,nodev,size=5242880)
none on /run/shm type tmpfs (rw,nosuid,nodev)
/dev/sdb5 on /boot type ext4 (rw)
/dev/sdb7 on /home type ext4 (rw)
binfmt_misc on /proc/sys/fs/binfmt_misc type binfmt_misc (rw,noexec,nosuid,nodev)
gvfs-fuse-daemon on /home/ubuntu/.gvfs type fuse.gvfs-fuse-daemon (rw,nosuid,nodev,user=ubuntu)

```



# p

## ps

```
6.ps ax    【察看当前运行的进程】
root@ubuntu:/proc# ps ax
  PID TTY      STAT   TIME COMMAND【进程在执行的程序文件】
    1 ?        Ss     0:00 /sbin/init   
    2 ?        S      0:00 [kthreadd]
    3 ?        S      0:00 [ksoftirqd/0]
    5 ?        S<     0:00 [kworker/0:0H]
    7 ?        S      0:00 [rcu_sched]
    8 ?        S      0:00 [rcuos/0]
    9 ?        S      0:00 [rcuos/1]
   10 ?        S      0:00 [rcuos/2]
   11 ?        S      0:00 [rcuos/3]
   12 ?        S      0:00 [rcu_bh]
   13 ?        S      0:00 [rcuob/0]
   14 ?        S      0:00 [rcuob/1]
   15 ?        S      0:00 [rcuob/2]
   16 ?        S      0:00 [rcuob/3]
 1140 tty4     Ss+    0:00 /sbin/getty -8 38400 tty4
 1147 tty5     Ss+    0:00 /sbin/getty -8 38400 tty5
 1156 tty2     Ss+    0:00 /sbin/getty -8 38400 tty2
 1159 tty3     Ss+    0:00 /sbin/getty -8 38400 tty3
 1161 tty6     Ss+    0:00 /sbin/getty -8 38400 tty6
 3006 ?        Sl     0:00 C:\windows\system32\services.exe     
 3010 ?        Sl     0:00 C:\windows\system32\winedevice.exe Mou
 3018 ?        Sl     0:00 C:\windows\system32\plugplay.exe     
 3025 ?        Ss     0:00 C:\windows\system32\explorer.exe /desk
 3027 ?        Sl     0:02 C:\Program Files (x86)\Source Insight
```


## $PS1  当前shell标号

```
7.PS1="[祝正杰的MacPro] \t:\w\$"     修改shell登陆显示的格式
修改shell标本登录的显示格式
zhuzhengjiedeMacBook-Pro:~ aaa$ echo $PS1
\h:\W \u\$
MacBook-Pro:~ aaa$PS1="[\h]\t:\w\$"     【改变shell输出的格式】
[MacBook-Pro]11:48:02:/$cd home
[MacBook-Pro]11:49:49:/home$
[MacPro]11:59:31:~$PS1="[祝正杰的MacPro] \t:\w\$"
[MacPro] 11:59:44:~$
```

##  $PATH   环境变量

```
PATH=$PATH:/home/test   为PATH添加一个命令集合文件夹
ubuntu@ubuntu:~$ echo $PATH
/home/ubuntu/bin:/home/ubuntu/SDK/adt-bundle-linux-x86_64/sdk/platform-tools:/home/ubuntu/bin:/usr/lib/lightdm/lightdm:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/lib/jvm/java-1.7.0-openjdk-amd64/bin
ubuntu@ubuntu:~$ PATH=$PATH:/home/test
ubuntu@ubuntu:~$ echo $PATH
/home/ubuntu/bin:/home/ubuntu/SDK/adt-bundle-linux-x86_64/sdk/platform-tools:/home/ubuntu/bin:/usr/lib/lightdm/lightdm:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/lib/jvm/java-1.7.0-openjdk-amd64/bin:/home/test
ubuntu@ubuntu:~$ 



```


##  printenv

```
13.printenv       察看系统环境变量
ORBIT_SOCKETDIR=/tmp/orbit-ubuntu
SSH_AGENT_PID=2157
TERMINATOR_UUID=urn:uuid:e71292db-2246-4bbf-8f59-13d36a4a1370
GIO_LAUNCHED_DESKTOP_FILE_PID=3557
GPG_AGENT_INFO=/tmp/keyring-SSJ0kw/gpg:0:1
TERM=xterm
SHELL=/bin/bash
XDG_SESSION_COOKIE=a1314982df6b66e37405aa7500000001-1496027291.322061-1719533086
WINDOWID=52428804
GNOME_KEYRING_CONTROL=/tmp/keyring-SSJ0kw
USER=ubuntu
LS_COLORS=rs=0:di=01;34:ln=01;36:mh=00:pi=40;33:so=01;35:do=01;35:bd=40;33;01:cd=40;33;01:or=40;31;01:su=37;41:sg=30;43:ca=30;41:tw=30;42:ow=34;42:st=37;44:ex=01;32:*.tar=01;31:*.tgz=01;31:*.arj=01;31:*.taz=01;31:*.lzh=01;31:*.lzma=01;31:*.tlz=01;31:*.txz=01;31:*.zip=01;31:*.z=01;31:*.Z=01;31:*.dz=01;31:*.gz=01;31:*.lz=01;31:*.xz=01;31:*.bz2=01;31:*.bz=01;31:*.tbz=01;31:*.tbz2=01;31:*.tz=01;31:*.deb=01;31:*.rpm=01;31:*.jar=01;31:*.war=01;31:*.ear=01;31:*.sar=01;31:*.rar=01;31:*.ace=01;31:*.zoo=01;31:*.cpio=01;31:*.7z=01;31:*.rz=01;31:*.jpg=01;35:*.jpeg=01;35:*.gif=01;35:*.bmp=01;35:*.pbm=01;35:*.pgm=01;35:*.ppm=01;35:*.tga=01;35:*.xbm=01;35:*.xpm=01;35:*.tif=01;35:*.tiff=01;35:*.png=01;35:*.svg=01;35:*.svgz=01;35:*.mng=01;35:*.pcx=01;35:*.mov=01;35:*.mpg=01;35:*.mpeg=01;35:*.m2v=01;35:*.mkv=01;35:*.webm=01;35:*.ogm=01;35:*.mp4=01;35:*.m4v=01;35:*.mp4v=01;35:*.vob=01;35:*.qt=01;35:*.nuv=01;35:*.wmv=01;35:*.asf=01;35:*.rm=01;35:*.rmvb=01;35:*.flc=01;35:*.avi=01;35:*.fli=01;35:*.flv=01;35:*.gl=01;35:*.dl=01;35:*.xcf=01;35:*.xwd=01;35:*.yuv=01;35:*.cgm=01;35:*.emf=01;35:*.axv=01;35:*.anx=01;35:*.ogv=01;35:*.ogx=01;35:*.aac=00;36:*.au=00;36:*.flac=00;36:*.mid=00;36:*.midi=00;36:*.mka=00;36:*.mp3=00;36:*.mpc=00;36:*.ogg=00;36:*.ra=00;36:*.wav=00;36:*.axa=00;36:*.oga=00;36:*.spx=00;36:*.xspf=00;36:
XDG_SESSION_PATH=/org/freedesktop/DisplayManager/Session0
XDG_SEAT_PATH=/org/freedesktop/DisplayManager/Seat0
SSH_AUTH_SOCK=/tmp/keyring-SSJ0kw/ssh
SESSION_MANAGER=local/ubuntu:@/tmp/.ICE-unix/2120,unix/ubuntu:/tmp/.ICE-unix/2120
DEFAULTS_PATH=/usr/share/gconf/ubuntu.default.path
GIO_LAUNCHED_DESKTOP_FILE=/usr/share/applications/terminator.desktop
XDG_CONFIG_DIRS=/etc/xdg/xdg-ubuntu:/etc/xdg
PATH=/home/ubuntu/bin:/home/ubuntu/SDK/adt-bundle-linux-x86_64/sdk/platform-tools:/home/ubuntu/bin:/usr/lib/lightdm/lightdm:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/lib/jvm/java-1.7.0-openjdk-amd64/bin
DESKTOP_SESSION=ubuntu
QT_IM_MODULE=xim
PWD=/home/ubuntu
XMODIFIERS=@im=ibus
JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-amd64
GNOME_KEYRING_PID=2109
LANG=zh_CN.UTF-8
MANDATORY_PATH=/usr/share/gconf/ubuntu.mandatory.path
UBUNTU_MENUPROXY=libappmenu.so
COMPIZ_CONFIG_PROFILE=ubuntu
GDMSESSION=ubuntu
SHLVL=1
HOME=/home/ubuntu
LANGUAGE=zh_CN:zh
GNOME_DESKTOP_SESSION_ID=this-is-deprecated
LOGNAME=ubuntu
XDG_DATA_DIRS=/usr/share/ubuntu:/usr/share/gnome:/usr/local/share/:/usr/share/
DBUS_SESSION_BUS_ADDRESS=unix:abstract=/tmp/dbus-Xf7vBwKV3t,guid=e9febd8efc86b1b430c4dfe100000008
CLASSPATH=.:/usr/lib/jvm/java-1.7.0-openjdk-amd64/lib
LESSOPEN=| /usr/bin/lesspipe %s
DISPLAY=:0
XDG_CURRENT_DESKTOP=Unity
GTK_IM_MODULE=ibus
LESSCLOSE=/usr/bin/lesspipe %s %s
COLORTERM=gnome-terminal
XAUTHORITY=/home/ubuntu/.Xauthority
_=/usr/bin/printenv



```
# t


## top

```
8.top   察看系统运行实时状态
top - 12:25:39 up  1:17,  1 user,  load average: 0.44, 0.27, 0.15
Tasks: 212 total,   1 running, 209 sleeping,   0 stopped,   2 zombie
Cpu(s):  3.8%us, 18.4%sy,  0.0%ni, 77.4%id,  0.4%wa,  0.1%hi,  0.0%si,  0.0%st
Mem:  16349608k total,  6361548k used,  9988060k free,    59140k buffers
Swap: 19528744k total,        0k used, 19528744k free,  1831628k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND                                                                      
 3377 ubuntu    20   0 5961m 3.4g 3.3g S   84 21.6   2:23.60 VirtualBox                                                                   
 1205 root      20   0  438m  80m  62m S    3  0.5   0:27.27 Xorg                                                                         
 2188 ubuntu    20   0 1451m  90m  36m S    3  0.6   0:24.54 compiz                                                                       
 2480 ubuntu    20   0  672m  43m  20m S    1  0.3   0:05.66 /usr/bin/termin                                                              
 3349 ubuntu    20   0  628m  11m 8024 S    1  0.1   0:00.80 VBoxSVC      
```
