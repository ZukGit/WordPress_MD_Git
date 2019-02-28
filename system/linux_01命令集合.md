

# A

## alias 定义别名
```
alias    为命令定义别名  更快速
alias suz="/usr/local/sh_expect_work/suz.sh"
alias cmt="/usr/local/sh_expect_work/cmt.sh"
alias cdsh='cd /usr/local/sh_expect_work/'
alias cdgit='cd  /Users/aaa/Desktop/code_place/zzj_git/'




```

## apt-get 
```
apt-get install gcc
apt-get install g++

```

# C

## ctrl + alt + F1
```
ctrl + alt + F1        // 该快捷键是在Linux系统中 从完全命令行模式切换回桌面模式的快捷键

```
## ctrl + alt + F2
```

ctrl + alt + F2   // 该快捷键是在Linux系统中 从完全桌面模式 切换到 完全命令行模式的 快捷键

export PATH=$PATH:~/Desktop/bin:/sbin:/xbin:/bin:/usr/bin/usr/sbin     【 首次进入需要加入 PATH 环境变量 来调用系统工具命令】

```

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



umask  和 touch 1.txt   默认创建文件的权限
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

## cp 
```
cp命令用来将一个或多个源文件或者目录复制到指定的目的文件或目录。
它可以将单个源文件复制成一个指定文件名的具体的文件或一个已经存在的目录下。
cp命令还支持同时复制多个文件，当一次复制多个文件时，目标文件参数必须是一个已经存在的目录，否则将出现错误。


cp -fr ./xxx  /xxx/xxx/xxx

cp(选项)(参数)

-a：此参数的效果和同时指定"-dpR"参数相同；
-d：当复制符号连接时，把目标文件或目录也建立为符号连接，并指向与源文件或目录连接的原始文件或目录；
-f：强行复制文件或目录，不论目标文件或目录是否已存在；
-i：覆盖既有文件之前先询问用户；
-l：对源文件建立硬连接，而非复制文件；
-p：保留源文件或目录的属性；
-R/r：递归处理，将指定目录下的所有文件与子目录一并处理；
-s：对源文件建立符号连接，而非复制文件；
-u：使用这项参数后只会在源文件的更改时间较目标文件更新时或是名称相互对应的目标文件并不存在时，才复制文件；
-S：在备份文件时，用指定的后缀“SUFFIX”代替文件的默认后缀；
-b：覆盖已存在的文件目标前将目标文件备份；
-v：详细显示命令执行的操作。
```


## cat 命令

### cat /proc/meminfo 

```
cat /proc/meminfo  察看系统内存使用情况
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
cat /proc/cpuinfo   察看系统处理器情况
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
cat /proc/version   察看系统版本
root@ubuntu:/proc# cat version
Linux version 3.13.0-32-generic (buildd@phianna) (gcc version 4.6.3 (Ubuntu/Linaro 4.6.3-1ubuntu5) ) 
#57~precise1-Ubuntu SMP Tue Jul 15 03:51:20 UTC 2014

```




###  cat /proc/version
```
cat /proc/devices   察看当前设备
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
df -h   【察看嘎u载磁盘的使用情况】
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

## export
```
export    命令用于将shell变量输出为环境变量，或者将shell函数输出为环境变量。
一个变量创建时，它不会自动地为在它之后创建的shell进程所知。而命令export可以向后面的shell传递变量的值。
当一个shell脚本调用并执 行时，它不会自动得到原为脚本（调用者）里定义的变量的访问权，除非这些变量已经被显式地设置为可用。
export命令可以用于传递一个或多个变量的值到任何后继脚本


export(选项)(参数)

export PATH=$PATH:~/Desktop/bin:/sbin:/xbin:/bin:/usr/bin/usr/sbin            【设置PATH环境变量】
echo $PATH


```

# g


## grep 搜索

```
grep  搜索察看文档中的多个关键字
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


# id root
```
id
uid=0(root) gid=0(root) groups=0(root),1004(input),1007(log),1011(adb),1015(sdcard_rw),1028(sdcard_r),3001(net_bt_admin),3002(net_bt),3003(inet),3006(net_bw_stats)
,3009(readproc),3011(uhid) context=u:r:su:s0

id root
uid=0(root) gid=0(root) groups=0(root), context=u:r:su:s0

id wifi
uid=1010(wifi) gid=1010(wifi) groups=1010(wifi), context=u:r:su:s0
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
## ps      // 查看进程信息命令
```
ps --version
toybox 0.7.6-android   (版本很重要)

ps  == Process Status
// 进程信息
adb shell
ps -A | grep  12192


ps -A | grep setting

USER           PID   PPID     VSZ     RSS    WCHAN                     ADDR  S   NAME
root             1     0    36868     3484   SyS_epoll+                0     S   init
root           876     1    3485264   64888  poll_schedule_timeout     0     S   zygote64
system       12192   876    3352400   98016  SyS_epoll_wait            0     S   com.android.settings


USER----(登录用户)           
PID-----(进程ID)   
PPID----(父进程ID)     
VSZ-----(使用掉的虚拟内存大小)    
RSS-----(进程使用的物理内存 包括共享内存)    
WCHAN---(进程正在睡眠的内核函数名称 该函数的名称是从/root/system.map文件中获得的)                     
ADDR----(内核进程，那么为预处理数据区的地)  
S-------(进程状态)   
NAME----(进程名称)



```

### ps --help  （英文）toybox 0.7.6-android

```
ps --version
toybox 0.7.6-android

ps --help
usage: ps [-AadefLlnwZ] [-gG GROUP,] [-k FIELD,] [-o FIELD,] [-p PID,] [-t TTY,] [-uU USER,]

List processes.

Which processes to show (selections may be comma separated lists):

-A      All processes
-a      Processes with terminals that aren't session leaders
-d      All processes that aren't session leaders
-e      Same as -A
-g      Belonging to GROUPs
-G      Belonging to real GROUPs (before sgid)
-p      PIDs (--pid)
-P      Parent PIDs (--ppid)
-s      In session IDs
-t      Attached to selected TTYs
-T      Show threads
-u      Owned by USERs
-U      Owned by real USERs (before suid)

Output modifiers:

-k      Sort FIELDs in +increasing or -decreasting order (--sort)
-M      Measure field widths (expanding as necessary)
-n      Show numeric USER and GROUP
-w      Wide output (don't truncate fields)

Which FIELDs to show. (Default = -o PID,TTY,TIME,CMD)

-f      Full listing (-o USER:12=UID,PID,PPID,C,STIME,TTY,TIME,ARGS=CMD)
-l      Long listing (-o F,S,UID,PID,PPID,C,PRI,NI,ADDR,SZ,WCHAN,TTY,TIME,CMD)
-o      Output FIELDs instead of defaults, each with optional :size and =title
-O      Add FIELDS to defaults
-Z      Include LABEL

Command line -o fields:

  ARGS     CMDLINE minus initial path     CMD  Command (thread) name (stat[2])
  CMDLINE  Command line (argv[])          COMM Command filename (/proc/$PID/exe)
  COMMAND  Command file (/proc/$PID/exe)  NAME Process name (argv[0] of $PID)

Process attribute -o FIELDs:

  ADDR  Instruction pointer               BIT   Is this process 32 or 64 bits
  CPU   Which processor running on        ETIME   Elapsed time since PID start
  F     Flags (1=FORKNOEXEC 4=SUPERPRIV)  GID     Group id
  GROUP Group name                        LABEL   Security label
  MAJFL Major page faults                 MINFL   Minor page faults
  NI    Niceness (lower is faster)
  PCPU  Percentage of CPU time used       PCY     Android scheduling policy
  PGID  Process Group ID
  PID   Process ID                        PPID    Parent Process ID
  PRI   Priority (higher is faster)       PSR     Processor last executed on
  RGID  Real (before sgid) group ID       RGROUP  Real (before sgid) group name
  RSS   Resident Set Size (pages in use)  RTPRIO  Realtime priority
  RUID  Real (before suid) user ID        RUSER   Real (before suid) user name
  S     Process state:
        R (running) S (sleeping) D (device I/O) T (stopped)  t (traced)
        Z (zombie)  X (deader)   x (dead)       K (wakekill) W (waking)
  SCHED Scheduling policy (0=other, 1=fifo, 2=rr, 3=batch, 4=iso, 5=idle)
  STAT  Process state (S) plus:
        < high priority          N low priority L locked memory
        s session leader         + foreground   l multithreaded
  STIME Start time of process in hh:mm (size :19 shows yyyy-mm-dd hh:mm:ss)
  SZ    Memory Size (4k pages needed to completely swap out process)
  TCNT  Thread count                      TID     Thread ID
  TIME  CPU time consumed                 TTY     Controlling terminal
  UID   User id                           USER    User name
  VSZ   Virtual memory size (1k units)    %VSZ    VSZ as % of physical memory
  WCHAN What are we waiting in kernel for


```

### ps --help  （中文）
```

ps [-aAcdefHjlmNVwy][acefghLnrsSTuvxX][-C <指令名称>][-g <群组名称>]

[-G <群组识别码>][-p <进程识别码>][p <进程识别码>][-s <阶段作业>]

[-t <终端机编号>][t <终端机编号>][-u <用户识别码>][-U <用户识别码>]

[U <用户名称>][-<进程识别码>][--cols <每列字符数>]

[--columns <每列字符数>][--cumulative][--deselect][--forest]

[--headers][--help][-- info][--lines <显示列数>][--no-headers]

[--group <群组名称>][-Group <群组识别码>][--pid <进程识别码>]

[--rows <显示列数>][--sid <阶段作业>][--tty <终端机编号>]

[--user <用户名称>][--User <用户识别码>][--version]

[--width <每列字符数>]

参数说明：

　　-a  显示所有终端机下执行的进程，除了阶段作业领导者之外。
　　 a  显示现行终端机下的所有进程，包括其他用户的进程。
　　-A  显示所有进程。
　　-c  显示CLS和PRI栏位。
　　 c  列出进程时，显示每个进程真正的指令名称，而不包含路径，参数或常驻服务的标示。
　　-C<指令名称> 　指定执行指令的名称，并列出该指令的进程的状况。
　　-d 　显示所有进程，但不包括阶段作业领导者的进程。
　　-e 　此参数的效果和指定"A"参数相同。
　　 e 　列出进程时，显示每个进程所使用的环境变量。
　　-f 　显示UID,PPIP,C与STIME栏位。
　　 f 　用ASCII字符显示树状结构，表达进程间的相互关系。
　　-g<群组名称> 　此参数的效果和指定"-G"参数相同，当亦能使用阶段作业领导者的名称来指定。
　　 g 　显示现行终端机下的所有进程，包括群组领导者的进程。
　　-G<群组识别码> 　列出属于该群组的进程的状况，也可使用群组名称来指定。
　　 h 　不显示标题列。
　　-H 　显示树状结构，表示进程间的相互关系。
　　-j或j 　采用工作控制的格式显示进程状况。
　　-l或l 　采用详细的格式来显示进程状况。
　　 L 　列出栏位的相关信息。
　　-m或m 　显示所有的执行绪。
　　 n 　以数字来表示USER和WCHAN栏位。
　　-N 　显示所有的进程，除了执行ps指令终端机下的进程之外。
　　-p<进程识别码> 　指定进程识别码，并列出该进程的状况。
　 　p<进程识别码> 　此参数的效果和指定"-p"参数相同，只在列表格式方面稍有差异。
　　 r 　只列出现行终端机正在执行中的进程。
　　-s<阶段作业> 　指定阶段作业的进程识别码，并列出隶属该阶段作业的进程的状况。
　 　s 　采用进程信号的格式显示进程状况。
　　 S 　列出进程时，包括已中断的子进程资料。
　　-t<终端机编号> 　指定终端机编号，并列出属于该终端机的进程的状况。
　　 t<终端机编号> 　此参数的效果和指定"-t"参数相同，只在列表格式方面稍有差异。
　　-T 　显示现行终端机下的所有进程。
　　-u<用户识别码> 　此参数的效果和指定"-U"参数相同。
　　 u 　以用户为主的格式来显示进程状况。
　　-U<用户识别码> 　列出属于该用户的进程的状况，也可使用用户名称来指定。
　　 U<用户名称> 　列出属于该用户的进程的状况。
　　 v 　采用虚拟内存的格式显示进程状况。
　　-V或V 　显示版本信息。
　　-w或w 　采用宽阔的格式来显示进程状况。　
　 　x 　显示所有进程，不以终端机来区分。
　　 X 　采用旧式的Linux i386登陆格式显示进程状况。
　　 -y 配合参数"-l"使用时，不显示F(flag)栏位，并以RSS栏位取代ADDR栏位
　　-<进程识别码> 　此参数的效果和指定"p"参数相同。
　　--cols<每列字符数> 　设置每列的最大字符数。
　　--columns<每列字符数> 　此参数的效果和指定"--cols"参数相同。
　　--cumulative 　此参数的效果和指定"S"参数相同。
　　--deselect 　此参数的效果和指定"-N"参数相同。
　　--forest 　此参数的效果和指定"f"参数相同。
　　--headers 　重复显示标题列。
　　--help 　在线帮助。
　　--info 　显示排错信息。
　　--lines<显示列数> 设置显示画面的列数。
　　--no-headers  此参数的效果和指定"h"参数相同，只在列表格式方面稍有差异。
　　--group<群组名称> 　此参数的效果和指定"-G"参数相同。
　　--Group<群组识别码> 　此参数的效果和指定"-G"参数相同。
　　--pid<进程识别码> 　此参数的效果和指定"-p"参数相同。
　　--rows<显示列数> 　此参数的效果和指定"--lines"参数相同。
　　--sid<阶段作业> 　此参数的效果和指定"-s"参数相同。
　　--tty<终端机编号> 　此参数的效果和指定"-t"参数相同。
　　--user<用户名称> 　此参数的效果和指定"-U"参数相同。
　　--User<用户识别码> 　此参数的效果和指定"-U"参数相同。
　　--version 　此参数的效果和指定"-V"参数相同。
　　--widty<每列字符数> 　此参数的效果和指定"-cols"参数相同。 


```
### ps -a
```

ps -a
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root          6857  6808   11800   3504 0                   0 R ps

```

### ps -A
```
ps -A
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root             1     0   36868   3620 SyS_epoll+          0 S init
root             2     0       0      0 kthreadd            0 S [kthreadd]
root             3     2       0      0 smpboot_t+          0 S [ksoftirqd/0]
root             5     2       0      0 worker_th+          0 S [kworker/0:0H]
root             7     2       0      0 rcu_gp_kt+          0 S [rcu_preempt]
root             8     2       0      0 rcu_gp_kt+          0 S [rcu_sched]
root             9     2       0      0 rcu_gp_kt+          0 S [rcu_bh]
root            10     2       0      0 rcu_nocb_+          0 S [rcuop/0]
root            11     2       0      0 rcu_nocb_+          0 S [rcuos/0]
......
log           1087     1   25436   2628 __skb_wai+          0 S diag_mdlog
root          1107     1   19544   2572 poll_sche+          0 S qcom-system-daemon
system        1111     1 2152112   3884 SyS_epoll+          0 S cnd
system        1117     1   23276   2692 futex_wai+          0 S time_daemon

```

### ps -d 
```
ps -d
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root             1     0   36868   3620 SyS_epoll+          0 S init
root             2     0       0      0 kthreadd            0 S [kthreadd]
root             3     2       0      0 smpboot_t+          0 S [ksoftirqd/0]
root             5     2       0      0 worker_th+          0 S [kworker/0:0H]
root             7     2       0      0 rcu_gp_kt+          0 S [rcu_preempt]
root             8     2       0      0 rcu_gp_kt+          0 S [rcu_sched]
root             9     2       0      0 rcu_gp_kt+          0 S [rcu_bh]
root            10     2       0      0 rcu_nocb_+          0 S [rcuop/0]
.....
root          7033     2       0      0 worker_th+          0 S [kworker/2:0]
root          7037     2       0      0 worker_th+          0 S [kworker/4:2]
root          7040     2       0      0 diag_sock+          0 S [kworker/u16:0]
root          7046     2       0      0 worker_th+          0 S [kworker/2:3]
root          7052  6808   11804   3772 0                   0 R ps
```


### ps -g   [进程组名称]
```
【进程组名称】
【     ps -g wifi   】 
【     ps -g bluetooth   】 
【     ps -g nfc   】 
【     ps -g gps   】 
【     ps -g camera   】 
【     ps -g media   】 
【     ps -g audio   】 
【     ps -g system   】 
【     ps -g radio   】 
【     ps -g root   】 
【     ps -g drm   】 
【     ps -g log   】 
【     ps -g keystore   】 
【     ps -g vendor_pwric   】 

ps -g wifi
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           971     1   23040   6252 binder_th+          0 S android.hardware.wifi@1.0-service
wifi          1208     1   19180   4112 SyS_epoll+          0 S wificond
wifi          4690     1 2144292  13076 poll_sche+          0 S wpa_supplicant



ps -g bluetooth
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
bluetooth      952     1   19612   3108 binder_th+          0 S android.hardware.bluetooth@1.0-service-qti

ps -g nfc
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system         980     1   13348   2716 binder_th+          0 S vendor.qti.esepowermanager@1.0-service


ps -g gps
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
gps           1228     1   15444   2432 futex_wai+          0 S mlid
gps           1231     1   15700   2944 futex_wai+          0 S loc_launcher
gps           1273  1231 2133448   3868 futex_wai+          0 S lowi-server
gps           1274  1231   29100   3540 __skb_wai+          0 S xtra-daemon



ps -g camera
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
camera         974     1   20832   2504 binder_th+          0 S motorola.hardware.camera.imgtuner@1.0-service
mediacodec    1210     1   91548   4088 binder_th+          0 S media.codec


ps -g media
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
media          959     1   19576   2588 binder_th+          0 S android.hardware.drm@1.1-service.clearkey
media          960     1   22700   2716 binder_th+          0 S android.hardware.drm@1.1-service.widevine
media         1140     1   15388   2452 poll_sche+          0 S adsprpcd
media         1199     1 2155828   4644 binder_th+          0 S media.metrics



ps -g audio
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
cameraserver   953     1  104456    932 binder_th+          0 S android.hardware.camera.provider@2.4-service
audioserver    973     1   41288   2972 binder_th+          0 S motorola.hardware.audio@2.0-service
audioserver   1010     1   76768   4780 binder_th+          0 S audioserver
cameraserver  1164     1   34552   1992 binder_th+          0 S cameraserver
media         1202     1  205548   2920 binder_th+          0 S mediaserver





ps -g system
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system         785     1   11616   2732 binder_th+          0 S servicemanager
system         786     1 2127152   4092 binder_th+          0 S hwservicemanager
system         787     1   12328   2444 binder_th+          0 S vndservicemanager
system         807     1   19248   2332 do_wait             0 S qseecomd
system         810   807   32052   1728 sigsuspend          0 S qseecomd
system         824     1   14820   2580 binder_th+          0 S android.hardware.gatekeeper@1.0-service-qti
system         825     1   16656   2656 binder_th+          0 S android.hardware.keymaster@4.0-service-qti
system         948     1  104392   2812 binder_th+          0 S android.hardware.sensors@1.0-service
system         949     1   12820   3016 binder_th+          0 S android.hidl.allocator@1.0-service
system         951     1   20116   2984 binder_th+          0 S motorola.hardware.tcmd@1.0-service
system         956     1   18732   2880 binder_th+          0 S android.hardware.configstore@1.1-service
system         964     1   14184   3008 SyS_epoll+          0 S android.hardware.health@2.0-service
system         965     1   14216   2932 binder_th+          0 S android.hardware.light@2.0-service
system         966     1   14216   2928 binder_th+          0 S android.hardware.memtrack@1.0-service
system         967     1   15948   2992 binder_th+          0 S android.hardware.power@1.0-service
system         968     1   14212   2672 binder_th+          0 S android.hardware.thermal@1.0-service
system         970     1   14216   2912 binder_th+          0 S android.hardware.vibrator@1.0-service



ps -g radio
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1127     1   15740   2612 poll_sche+          0 S imsqmidaemon
system        1131     1   31456   2788 sigsuspend          0 S ims_rtp_daemon
system        1137     1   19444   2836 binder_th+          0 S imsrcsd
radio         1215     1    4872    664 __skb_wai+          0 S ssgqmigd
radio         1477     1  145240   6648 binder_th+          0 S qcrild
radio         1484     1   14204   2648 poll_sche+          0 S ipacm-diag
radio         1490     1   22160   2828 futex_wai+          0 S ipacm
radio         1499     1   23204   2668 poll_sche+          0 S qti
radio         1507     1  124668   3064 __skb_wai+          0 S netmgrd
radio         1516     1   15412   2444 futex_wai+          0 S port-bridge
radio         1729  1477   29760   3024 poll_sche+          0 S qmi_motext_hook
radio         2553   876 3238152  56256 SyS_epoll+          0 S com.qualcomm.qti.telephonyservice
radio         2587   876 3315716  66996 SyS_epoll+          0 S com.android.phone
radio         2805   876 3236688  59280 SyS_epoll+          0 S com.qualcomm.qcrilmsgtunnel
radio         4708   876 3234316  59836 SyS_epoll+          0 S com.qualcomm.qti.modemtestmode


ps -g drm
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
drm           1182     1   24552   1836 binder_th+          0 S drmserver


ps -g log
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1072     1   11324   2500 poll_sche+          0 S subsystem_ramdump
log           1078     1   15128   3436 SyS_epoll+          0 S aplogd
log           1087     1   25436   2628 __skb_wai+          0 S diag_mdlog



ps -g keystore
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
keystore      1188     1 2135868   4196 binder_th+          0 S keystore


ps -g vendor_pwric
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
vendor_pwric  2994     1   12556   2916 poll_sche+          0 S batt_health

```

### ps -p   [进程id]
```
ps -p 12192        // 查看当前进程id 的信息
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system       12192   876 3371888 160860 SyS_epoll+          0 S com.android.settings


```

### ps -P   [父进程id]
```
 ps -P  876        //  查看以 [ 876(父进程) ] 为父进程的进程信息

USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1799   876 3992396 193192 SyS_epoll+          0 S system_server
u0_a150       2349   876 3427332 119036 SyS_epoll+          0 S com.google.android.inputmethod.latin
u0_a30        2361   876 3433612 174948 SyS_epoll+          0 S com.android.systemui
system        2526   876 3248248  64016 SyS_epoll+          0 S .dataservices
radio         2553   876 3238152  56256 SyS_epoll+          0 S com.qualcomm.qti.telephonyservice
log           2565   876 3236188  53156 SyS_epoll+          0 S com.motorola.android.nativedropboxagent
radio         2587   876 3315716  66996 SyS_epoll+          0 S com.android.phone
u0_a39        2722   876 3234436  63140 SyS_epoll+          0 S com.google.android.ext.services
radio         2805   876 3236688  59280 SyS_epoll+          0 S com.qualcomm.qcrilmsgtunnel
u0_a80        2906   876 3367656 118512 SyS_epoll+          0 S com.google.android.gms.persist
```

### ps -AT     ▲
```
 ps -AT     //  显示所有进程  并且同时 显示

USER           PID   TID  PPID     VSZ    RSS WCHAN            ADDR S CMD
root             1     1     0   36868   3620 SyS_epoll+          0 S init
root             2     2     0       0      0 kthreadd            0 S kthreadd
root             3     3     2       0      0 smpboot_t+          0 S ksoftirqd/0
root             5     5     2       0      0 worker_th+          0 S kworker/0:0H
root             7     7     2       0      0 rcu_gp_kt+          0 S rcu_preempt
root             8     8     2       0      0 rcu_gp_kt+          0 S rcu_sched
..........
system       12192 12192   876 3371888 159964 SyS_epoll+          0 S ndroid.settings
system       12192 12197   876 3371888 159964 futex_wai+          0 S Jit thread pool
system       12192 12198   876 3371888 159964 do_sigtim+          0 S Signal Catcher
system       12192 12199   876 3371888 159964 poll_sche+          0 S ADB-JDWP Connec
system       12192 12200   876 3371888 159964 futex_wai+          0 S ReferenceQueueD
system       12192 12201   876 3371888 159964 futex_wai+          0 S FinalizerDaemon
system       12192 12202   876 3371888 159964 futex_wai+          0 S FinalizerWatchd
system       12192 12203   876 3371888 159964 futex_wai+          0 S HeapTaskDaemon
system       12192 12204   876 3371888 159964 binder_th+          0 S Binder:12192_1
system       12192 12205   876 3371888 159964 binder_th+          0 S Binder:12192_2
system       12192 12206   876 3371888 159964 futex_wai+          0 S Profile Saver
system       12192 12210   876 3371888 159964 SyS_epoll+          0 S ConnectivityThr
system       12192 12211   876 3371888 159964 futex_wai+          0 S pool-1-thread-1
system       12192 12216   876 3371888 159964 SyS_epoll+          0 S RenderThread
system       12192 12232   876 3371888 159964 binder_th+          0 S Binder:12192_3
system       12192 12292   876 3371888 159964 binder_th+          0 S Binder:12192_4
system       12192 12293   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 12298   876 3371888 159964 SyS_epoll+          0 S queued-work-loo
system       12192 12453   876 3371888 159964 binder_th+          0 S Binder:12192_5
system       12192 12742   876 3371888 159964 binder_th+          0 S Binder:12192_6
system       12192 12967   876 3371888 159964 binder_th+          0 S Binder:12192_7
system       12192 24077   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24089   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24295   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24705   876 3371888 159964 binder_th+          0 S Binder:12192_8
system       12192 29260   876 3371888 159964 binder_th+          0 S Binder:12192_9
system       12192 29617   876 3371888 159964 SyS_epoll+          0 S magnifier pixel
system       12192 29750   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 30131   876 3371888 159964 binder_th+          0 S Binder:12192_A
system       12192 31628   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6351   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6393   876 3371888 159964 binder_th+          0 S Binder:12192_B
system       12192  6397   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6451   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6467   876 3371888 159964 SyS_epoll+          0 S SummaryLoader



```

### ps -u   [wifi 用户名称]
```    
ps -u  wifi                    //  查看属于wifi用户的进程 
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           971     1   23040   6252 binder_th+          0 S android.hardware.wifi@1.0-service
wifi          1208     1   19180   4112 SyS_epoll+          0 S wificond
wifi          4690     1 2144292  13076 poll_sche+          0 S wpa_supplicant

ps -u u0_a80
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a80        2906   876 3367656 119664 SyS_epoll+          0 S com.google.android.gms.persistent
u0_a80        3175   876 3509964 109836 SyS_epoll+          0 S com.google.android.gms
u0_a80       15642   876 3252304  59736 SyS_epoll+          0 S com.google.process.gservices



ps -u u0_a36
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a36        6207   876 3244532  71152 SyS_epoll+          0 S com.google.android.apps.turbo


```

### ps  -n
```
ps  -n    // 以数值的方式显示 USER列表  

ps  -n
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
0             6808  6702    9532   3236 sigsuspend          0 S sh
0             8734  6808   11800   3772 0                   0 R ps


ps  -a
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root          8740  6808   11800   3616 0                   0 R ps




```



### ps  -AM
```
 ps  -AM     // M 的作用在于使用较宽的宽度去显示


对比如下:  标题位置较宽
ps -A
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a80       15642   876 3252304  60992 SyS_epoll+          0 S com.google.process.gservices

ps -AM
USER             PID  PPID     VSZ    RSS WCHAN                             ADDR S NAME  
u0_a80       15642   876 3252304  60980 SyS_epoll+                          0    S com.google.process.gservices
```

### ps  -ATMf
```
ps  -ATMf               // f 参数用于 设置表头为  UID,PID,PPID,C,STIME,TTY,TIME,CMD
 

UID              PID   TID  PPID TCNT               STIME TTY          TIME CMD
root               1     1     0    1 2018-12-25 10:59:44 ?        00:00:07 init
root               2     2     0    1 2018-12-25 10:59:44 ?        00:00:01 [kthreadd]
root               3     3     2    1 2018-12-25 10:59:44 ?        00:00:30 [ksoftirqd/0]
root               5     5     2    1 2018-12-25 10:59:44 ?        00:00:00 [kworker/0:0H]
root               7     7     2    1 2018-12-25 10:59:44 ?        00:04:24 [rcu_preempt]
root               8     8     2    1 2018-12-25 10:59:44 ?        00:00:23 [rcu_sched]
root               9     9     2    1 2018-12-25 10:59:44 ?        00:00:00 [rcu_bh]
root              10    10     2    1 2018-12-25 10:59:44 ?        00:01:31 [rcuop/0]
root              11    11     2    1 2018-12-25 10:59:44 ?        00:00:02 [rcuos/0]
........
system         12192 12192   876   36 2018-12-25 11:52:00 ?        00:01:16 com.android.settings
system         12192 12197   876   36 2018-12-25 11:52:00 ?        00:00:02 com.android.settings
system         12192 12198   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12199   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12200   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12201   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12202   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12203   876   36 2018-12-25 11:52:00 ?        00:00:03 com.android.settings
system         12192 12204   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12205   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12206   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12210   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12211   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12216   876   36 2018-12-25 11:52:01 ?        00:00:39 com.android.settings
system         12192 12232   876   36 2018-12-25 11:52:01 ?        00:00:00 com.android.settings
system         12192 12292   876   36 2018-12-25 11:52:19 ?        00:00:00 com.android.settings
system         12192 12293   876   36 2018-12-25 11:52:19 ?        00:00:00 com.android.settings
system         12192 12298   876   36 2018-12-25 11:52:20 ?        00:00:00 com.android.settings
system         12192 12453   876   36 2018-12-25 11:52:59 ?        00:00:00 com.android.settings
system         12192 12742   876   36 2018-12-25 11:54:40 ?        00:00:00 com.android.settings
system         12192 12967   876   36 2018-12-25 11:56:31 ?        00:00:00 com.android.settings
system         12192 24077   876   36 2018-12-26 08:29:32 ?        00:00:00 com.android.settings
system         12192 24089   876   36 2018-12-26 08:29:35 ?        00:00:00 com.android.settings
system         12192 24295   876   36 2018-12-26 08:31:53 ?        00:00:00 com.android.settings
system         12192 24705   876   36 2018-12-26 08:33:45 ?        00:00:00 com.android.settings
system         12192 29260   876   36 2018-12-26 10:29:21 ?        00:00:00 com.android.settings
system         12192 29617   876   36 2018-12-26 10:30:29 ?        00:00:00 com.android.settings
system         12192 29750   876   36 2018-12-26 10:30:36 ?        00:00:00 com.android.settings
system         12192 30131   876   36 2018-12-26 10:31:10 ?        00:00:00 com.android.settings
system         12192 31628   876   36 2018-12-26 11:56:12 ?        00:00:00 com.android.settings
system         12192  6351   876   36 2018-12-27 07:26:42 ?        00:00:00 com.android.settings
system         12192  6393   876   36 2018-12-27 07:26:48 ?        00:00:00 com.android.settings
system         12192  6397   876   36 2018-12-27 07:26:48 ?        00:00:00 com.android.settings
system         12192  6451   876   36 2018-12-27 07:27:20 ?        00:00:00 com.android.settings
system         12192 12908   876   36 2018-12-27 10:01:19 ?        00:00:00 com.android.settings
system         12192 13938   876   36 2018-12-27 10:12:09 ?        00:00:00 com.android.settings
```


### ps  -ATMlf
```
ps  -ATMl       //  l 参数用于设置表头为 F,S,UID,PID,PPID,C,PRI,NI,ADDR,SZ,WCHAN,TTY,TIME,CMD
F S   UID   PID   TID  PPID  C PRI  NI BIT     SZ WCHAN                           TTY          TIME CMD
4 S     0     1     1     0  0  19   0  64   9217 SyS_epoll_wait                  ?        00:00:07 init
1 S     0     2     2     0  0  19   0   -      0 kthreadd                        ?        00:00:01 kthreadd
.........
5 S  1000 12192 12192   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:01:16 ndroid.settings
1 S  1000 12192 12197   876  0  10   9  64 843411 futex_wait_queue_me             ?        00:00:02 Jit thread pool
1 S  1000 12192 12198   876  0  19   0  64 843411 do_sigtimedwait                 ?        00:00:00 Signal Catcher
1 S  1000 12192 12199   876  0  19   0  64 843411 poll_schedule_timeout           ?        00:00:00 ADB-JDWP Connec
1 S  1000 12192 12200   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 ReferenceQueueD
1 S  1000 12192 12201   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 FinalizerDaemon
1 S  1000 12192 12202   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 FinalizerWatchd
1 S  1000 12192 12203   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:03 HeapTaskDaemon
1 S  1000 12192 12204   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_1
1 S  1000 12192 12205   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_2
1 S  1000 12192 12206   876  0  10   9  64 843411 futex_wait_queue_me             ?        00:00:00 Profile Saver
1 S  1000 12192 12210   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:00 ConnectivityThr
1 S  1000 12192 12211   876  0  19   0  64 843411 futex_wait_queue_me             ?        00:00:00 pool-1-thread-1
1 S  1000 12192 12216   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:39 RenderThread
1 S  1000 12192 12232   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_3
1 S  1000 12192 12292   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_4
1 S  1000 12192 12293   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 12298   876  0  21  -2  64 843411 SyS_epoll_wait                  ?        00:00:00 queued-work-loo
1 S  1000 12192 12453   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_5
1 S  1000 12192 12742   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_6
1 S  1000 12192 12967   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_7
1 S  1000 12192 24077   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24089   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24295   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24705   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_8
1 S  1000 12192 29260   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_9
1 S  1000 12192 29617   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:00 magnifier pixel
1 S  1000 12192 29750   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 30131   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_A
1 S  1000 12192 31628   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6351   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6393   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_B
1 S  1000 12192  6397   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6451   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 12908   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 13938   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader

```

### ps  -ATM -o  [表头项]
```
ps  -ATM -o ARGS                // -o 参数标识只显示的表头项
ps  -ATM -o CMD 
ps  -ATM -o CMDLINE 
ps  -ATM -o COMM 
ps  -ATM -o COMMAND 
ps  -ATM -o NAME 


ps  -ATM -o ARGS 
ARGS
init
[kthreadd]
[ksoftirqd/0]
[kworker/0:0H]
[rcu_preempt]
[rcu_sched]

ps  -ATM -o CMD 
CMD
init
kthreadd
ksoftirqd/0
kworker/0:0H
rcu_preempt
rcu_sched



```
###  ps -ATM -o  表头1,表头2...   // 所有可能
```
ps  -ATM -o USER,UID,PID,PPID,C,STIME,TTY,TIME,CMD,F,S,PRI,NI,ADDR,SZ,WCHAN,BIT,CPU,ETIME,GROUP,GID,LABEL,MAJFL,MINFL,PCPU,PCY,PGID,PSR,RGROUP,RSS,RTPRIO,RUID,RUSER,SCHED,STAT,STIME,SZ,TCNT,TID,VSZ,%VSZ



USER             UID   PID  PPID  C               STIME TTY          TIME CMD             F S PRI  NI         ADDR     SZ WCHAN                    BIT CPU     ELAPSED GROUP               GID LABEL                                     MAJFL   MINFL %CPU PCY  PGID PSR RGROUP            RSS RTPRIO  RUID RUSER          SCH STAT                STIME     SZ TCNT   TID     VSZ   %VSZ

root               0     1     0  0 2018-12-28 03:31:12 ?        00:00:06 init            4 S  19   0            0   9217 0                          -   0    01:01:19 root                  0 u:r:init:s0                                  76   13267  0.1  fg     0   0 root             2396      -     0 root             0 S     2018-12-28 03:31:12   9217    1     1   36868   2036
root               0     2     0  0 2018-12-28 03:31:12 ?        00:00:00 kthreadd        1 S  19   0            0      0 0                          -   3    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   3 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1     2       0    0.0
root               0     3     2  0 2018-12-28 03:31:12 ?        00:00:01 ksoftirqd/0     1 S  19   0            0      0 0                          -   0    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   0 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1     3       0    0.0
root               0     5     2  0 2018-12-28 03:31:12 ?        00:00:00 kworker/0:0H    1 S  39 -20            0      0 0                          -   0    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   0 root                0      -     0 root             0 S<    2018-12-28 03:31:12      0    1     5       0    0.0
root               0     7     2  0 2018-12-28 03:31:12 ?        00:00:12 rcu_preempt     1 S  19   0            0      0 0                          -   1    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.3  fg     0   1 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1     7       0    0.0
root               0     8     2  0 2018-12-28 03:31:12 ?        00:00:00 rcu_sched       1 S  19   0            0      0 0                          -   1    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   1 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1     8       0    0.0
root               0     9     2  0 2018-12-28 03:31:12 ?        00:00:00 rcu_bh          1 S  19   0            0      0 0                          -   0    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   0 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1     9       0    0.0
root               0    10     2  0 2018-12-28 03:31:12 ?        00:00:05 rcuop/0         1 S  19   0            0      0 0                          -   2    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.1  fg     0   2 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1    10       0    0.0
root               0    11     2  0 2018-12-28 03:31:12 ?        00:00:00 rcuos/0         1 S  19   0            0      0 0                          -   3    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   3 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1    11       0    0.0
root               0    12     2  0 2018-12-28 03:31:12 ?        00:00:00 rcuob/0         1 S  19   0            0      0 0                          -   0    01:01:19 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   0 root                0      -     0 root             0 S     2018-12-28 03:31:12      0    1    12       0    0.0
...........

u0_a78         10078  5987   864  0 2018-12-28 03:32:26 ?        00:00:00 Binder:5987_6   1 S  19   0            0 810985 0                          -   0    01:00:05 u0_a78            10078 u:r:platform_app:s0:c512,c768                61    1946  0.0  fg   864   0 u0_a78          70084      - 10078 u0_a78           0 S     2018-12-28 03:32:26 810985   20  7257 3243940 179223
u0_a78         10078  5987   864  0 2018-12-28 03:44:03 ?        00:00:00 ConnectivityThr 1 S  19   0            0 810985 0                          -   7       48:28 u0_a78            10078 u:r:platform_app:s0:c512,c768                 1      33  0.0  fg   864   7 u0_a78          70084      - 10078 u0_a78           0 S     2018-12-28 03:44:03 810985   20  9091 3243940 179223
system          1000  6020   864  1 2018-12-28 03:32:16 ?        00:00:25 ndroid.settings 5 S  19   0            0 843337 0                          -   2    01:00:15 system             1000 u:r:system_app:s0                            72  145387  0.7  bg   864   2 system         141132      -  1000 system           0 S     2018-12-28 03:32:16 843337   27  6020 3373348 186373
................
u0_a87         10087 13507   864  0 2018-12-28 04:32:06 ?        00:00:00 Binder:13507_4  1 S  19   0            0 809743 0                          -   1       00:25 u0_a87            10087 u:r:priv_app:s0:c512,c768                     0       8  0.0  bg   864   1 u0_a87          68300      - 10087 u0_a87           0 S     2018-12-28 04:32:06 809743   15 13532 3238972 178949
u0_a87         10087 13507   864  0 2018-12-28 04:32:11 ?        00:00:00 Binder:13507_5  1 S  19   0            0 809743 0                          -   2       00:20 u0_a87            10087 u:r:priv_app:s0:c512,c768                     0       9  0.0  bg   864   2 u0_a87          68300      - 10087 u0_a87           0 S     2018-12-28 04:32:11 809743   15 13534 3238972 178949
root               0 13550     2  0 2018-12-28 04:32:30 ?        00:00:00 mdss_fb0        5 D  56   0            0      0 0                          -   2       00:01 root                  0 u:r:kernel:s0                                 0       0  0.0  fg     0   2 root                0     16     0 root             1 D     2018-12-28 04:32:30      0    1 13550       0    0.0
shell           2000 13551  8283 77 2018-12-28 04:32:30 pts/0    00:00:00 ps              0 R  19   0            0   4486 0                         64   4       00:01 shell              2000 u:r:shell:s0                                  0    2489 77.1  fg 13551   4 shell           10036      -  2000 shell            0 R+    2018-12-28 04:32:30   4486    1 13551   17944    991
```
### PS命令表头项
```




USER-------------(登录用户名)---------------(system wifi shell ) 
UID--------------(登录用户id)---------------(0[root] 2000[shell] ) 
PID--------------(进程ID号)-----------------(1[root] 13551[shell] ) 
PPID-------------(父进程ID号)---------------(0[root] , 8283[shell])
C----------------(代表CPU使用率 百分比)------(77 , 1 ,0 ...)
STIME------------(进程的启动时间)------------(root[ 2018-12-28 03:31:12 ])
TTY--------------(与进程关联的终端)----------(root[?]  shell[ pts/0 ])
TIME-------------( 进程使用CPU累计时间)------(00:00:00 , 00:00:25)
CMD--------------( 执行文件的名称 )----------(ps  Binder:5987_6 ndroid.settings) 要查怎么全显示
F----------------(Flag 位标记)-----------------(0_0000 普通进程)  (1_0001 Fork后未执行的进程 ）(4_0100 使用超级用户权限的进程)   (5_0101 超级用户权限Fork后未执行的进程) (1=FORKNOEXEC 4=SUPERPRIV)
S---------------(进程状态Process state)--------【R(正在运行running)） S(睡眠sleeping)  D(设备输入输出device I/O)  T(停止stopped)   t(追踪traced)  Z(僵尸进程zombie) X(deader)   x(dead)       K(wakekill)  W(waking)】
PRI-------------(进程优先级Priority , 数值越大优先级越高)
NI--------------(CPU调度优先级  数值越低 CPU 优先调度)-----------------------------niceness的范围一般从 -20 到 19，-20 表示调度优先级最高，19 表示优先级最低
ADDR------------(该程序在内存的地址) 默认0
SZ--------------(使用掉的内存大小 Memory Size )  默认0
WCHAN----------(正在等待的进程资源 What are we waiting in kernel for) 默认0
BIT------------( 软件运行位数) 默认32位 默认显示"-"   还有64位 128位
CPU------------(标明 哪一个CPU在运行进程Which processor running on)  0,1,2,3,4,5,6,7
ETIME----------(记录进程已启动的时间 Elapsed time since PID start) 01:01:19   07:06
GROUP----------(进程所在组名称Group name) root  system   bluetooth   audio  mediadrm drmrpc  media graphics wifi nfc  vendor_qrtr  vendor_rfs  log net_raw drm incidentd statsd package_info camera  gps  radio  u0_a150  u0_a30  webview_zygote  secure_element  shell
GID------------(组IDGroup id) root-0  system-1000   bluetooth-1002   audio-1005  mediadrm-1031  media-1013 graphics-1003 wifi-1010  nfc-1027 vendor_qrtr-2906  vendor_rfs-2903  log-1007 net_raw-3004 drm-1019 keystore-1017  drmrpc-1026 incidentd-1067 statsd-1066 package_info-1032 camera-1006  gps-1021  radio-1001  u0_a150-10150  u0_a30-10030  webview_zygote-1053  secure_element-1068  shell-2000
LABEL----------(信息安全标签 Linux系统上下文使用 Security label)  u:r:init:s0   0 u:r:kernel:s0  u:r:hal_sensors_default:s0   u:r:hal_wifi_default:s0   u:r:audioserver:s0
MAJFL----------(主要页错误的数量  Major page faults)
MINFL-----------(次要页错误的数量 Minor page faults)
PCPU------------(该进程占用的CPU时间和总时间的百分比 Percentage of CPU time used)
PCY-------------(安卓调度策略 Android scheduling policy)  fg  bg  ta
PGID------------(进程组ID Process Group ID)  root-0   logd-783  system-784  system-785  system-825    root-863   gps-1248  gps-1247  u0_a32-864
PSR-------------(最后执行进程的CPU  Processor last executed on)   0,1,2,3,4,5,6,7
RGROUP----------组名称 信号量ID到达之前 Real (before sgid) group name--------root  system   bluetooth   audio  mediadrm drmrpc  media graphics wifi nfc  vendor_qrtr  vendor_rfs  log net_raw drm incidentd statsd package_info camera  gps  radio  u0_a150  u0_a30  webview_zygote  secure_element  shell
RSS-------------进程占用的固定的内存量 (Kbytes) Resident Set Size (pages in use)------------- 2396kB  4680KB 1208KB  0KB ....
RTPRIO----------实时优先级 Realtime priority    99  - 50  16  19
RUID------------实时用户ID  Real (before suid) user ID ---------------------(0[root] 2000[shell] ) 
RUSER-----------实时登录用户名 (root  system wifi shell ) 
SCHED-----------调度策略Scheduling policy (0=other普通进程调度策略,  1=fifo高优先级会抢占低优先级, 2=rr高优先级会抢占低优先级 等优先级的进程，各个进程会轮流运行一定的时间片（大约100ms） , 3=batch, 4=iso, 5=idle)
STAT-----------进程高级状态Process state Plus----------  S  , S< , SN , SNL , D,R+ , Ss+ ,SL ---------- <=[ high priority]  S=[Sleep] N=[low priority]  L=[locked memory]  +=[foreground]   l=[multithreaded]
STIME----------进程开始执行的时间戳 Start time of process in hh:mm----------- 2018-12-28 03:31:35 2018-12-28 04:16:48
SZ-------------进程核心映像的大小单位是 1KB Memory Size
TCNT-----------进程中使用线程的数量 Thread count
TID------------进程中线程的ID Thread ID
VSZ------------进程使用的虚拟内存大小 Virtual memory size (1k units)
%VSZ-----------进程所占用的虚拟内存占物理内存的百分比  MemTotal is found in /proc/meminfo,   VSZ as % of physical memory



 cat /proc/meminfo
MemTotal:        1853436 kB
MemFree:           65348 kB
MemAvailable:     657980 kB
Buffers:            1472 kB
Cached:           618836 kB
SwapCached:         9592 kB
Active:           542300 kB
Inactive:         512332 kB
Active(anon):     233752 kB
Inactive(anon):   249416 kB
Active(file):     308548 kB
Inactive(file):   262916 kB
Unevictable:       48024 kB
Mlocked:           48024 kB
SwapTotal:       1048572 kB
SwapFree:         611484 kB
Dirty:                36 kB
Writeback:             0 kB
AnonPages:        479696 kB
Mapped:           415972 kB
Shmem:              1448 kB
Slab:             202728 kB
SReclaimable:      63432 kB
SUnreclaim:       139296 kB
KernelStack:       41776 kB
PageTables:        50344 kB
NFS_Unstable:          0 kB
Bounce:                0 kB
WritebackTmp:          0 kB
CommitLimit:     1975288 kB
Committed_AS:   41612000 kB
VmallocTotal:   263061440 kB
VmallocUsed:           0 kB
VmallocChunk:          0 kB
CmaTotal:         245760 kB
CmaFree:             476 kB

   VSZ   %VSZ
 36868   2036       
3498768  193302
 36868 /  2036  ~ 18.1
3498768  / 193302 ~  18.1

 36868 /  18.1 =   2036

ulimit -s     // 查看系统默认的应用程序线程运行栈大小 以及修改应用运行时的栈大小，默认的8192KB
8192
ulimit -s 512   // 设置默认线程栈大小为 512   KB


```

###  ps -efATM -o UID,PID,TID,PPID,TCNT,STIME,ETIME,TIME,F,S,PRI,NI,CPU,PCPU,C,RSS,WCHAN,SZ,VSZ,LABEL,CMD  
```
 ps -ATMl

F S   UID   PID   TID  PPID  C PRI  NI BIT     SZ WCHAN                    TTY          TIME CMD
1 S 10080  2913  2921   864  0  19   0   - 875062 0                        ?        00:00:01 ADB-JDWP Connec

 ps -ATM
USER             PID   TID  PPID     VSZ    RSS WCHAN                          ADDR S CMD
root              22    22     2       0      0 0                                 0 S kworker/1:0H

  ps -efATM -o UID,PID,TID,PPID,TCNT,STIME,ETIME,TIME,F,S,PRI,NI,CPU,PCPU,C,RSS,WCHAN,SZ,VSZ,LABEL,ARGS=CMD      > log.txt

 UID   PID   TID  PPID TCNT               STIME     ELAPSED     TIME F S PRI  NI CPU %CPU  C    RSS WCHAN                        SZ     VSZ LABEL                                    CMD                                                                                                                                                         
1000  6020  6020   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:28 5 S  19   0   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6026   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  10   9   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6027   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  19   0   5  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6029   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:01 1 S  19   0   2  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6030   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  15   4   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6031   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  15   4   1  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6032   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  15   4   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6033   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  15   4   2  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6034   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  19   0   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6035   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  19   0   2  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6037   864   27 2018-12-28 03:32:16  4-05:05:26 00:00:00 1 S  10   9   4  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6057   864   27 2018-12-28 03:32:17  4-05:05:25 00:00:00 1 S  19   0   5  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6113   864   27 2018-12-28 03:32:17  4-05:05:25 00:00:00 1 S  19   0   4  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  6123   864   27 2018-12-28 03:32:17  4-05:05:25 00:00:14 1 S  19   0   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  7968   864   27 2018-12-28 03:32:55  4-05:04:47 00:00:00 1 S  19   0   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  7969   864   27 2018-12-28 03:32:55  4-05:04:47 00:00:00 1 S  21  -2   2  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  8163   864   27 2018-12-28 03:33:34  4-05:04:08 00:00:00 1 S  19   0   6  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  8984   864   27 2018-12-28 03:43:54  4-04:53:48 00:00:00 1 S   9  10   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  9014   864   27 2018-12-28 03:43:56  4-04:53:46 00:00:00 1 S  19   0   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  9234   864   27 2018-12-28 03:44:13  4-04:53:29 00:00:00 1 S   9  10   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  9301   864   27 2018-12-28 03:44:18  4-04:53:24 00:00:00 1 S  19   0   4  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  9307   864   27 2018-12-28 03:44:19  4-04:53:23 00:00:00 1 S   9  10   5  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020  9941   864   27 2018-12-28 03:44:36  4-04:53:06 00:00:00 1 S   9  10   0  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020 10008   864   27 2018-12-28 03:44:37  4-04:53:05 00:00:00 1 S   9  10   4  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020 10272   864   27 2018-12-28 03:44:40  4-04:53:02 00:00:00 1 S   9  10   6  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020 10414   864   27 2018-12-28 03:44:41  4-04:53:01 00:00:00 1 S   9  10   2  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0                        com.android.settings
1000  6020 10822   864   27 2018-12-28 03:44:45  4-04:52:57 00:00:00 1 S  19   0   3  0.0  0  89212 0                        843337 3373348 u:r:system_app:s0   

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

# r

## rm 
```
rm  是删除命令 全文是 remove     -fr  表示直接强制删除 
rm -fr  /xxx


1. 对文件拥有777 权限但无法删除  无法替换的原因 ，  是因为用户对该文件所在目录 没有 w 写的权限 4的权限导致
ls -la
-rwxrwxrwx 1 zukgit zukgit  19K Mar 12  2018  记录点_2018年3月12日.kmz
-rwxrwxrwx 1 zukgit zukgit 1.4K May 30  2018  迅雷.lnk
-rwxrwxrwx 1 zukgit zukgit 1.1K Jul  8  2018  酷狗音乐.lnk


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

