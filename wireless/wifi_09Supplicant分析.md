https://blog.csdn.net/sinat_20059415/article/details/83684932
# wpa_supplicant手机内相关文件
```
手机内相关文件

可执行文件:
/vendor/bin/hw/wpa_supplicant
/vendor/bin/wpa_cli

共享库文件
/vendor/lib/libwpa_client.so
/vendor/lib64/libwpa_client.so


配置文件
/vendor/etc/wifi/p2p_supplicant_overlay.conf
/vendor/etc/wifi/wpa_supplicant_overlay.conf
/data/vendor/wifi/wpa/p2p_supplicant.conf
/data/vendor/wifi/wpa/wpa_supplicant.conf
/data/vendor/wifi/wigig_supplicant.conf 
/vendor/etc/wifi/wpa_supplicant.conf
/vendor/etc/wifi/WCNSS_qcom_cfg.ini
/data/vendor/wifi/cnss_diag.conf
/data/vendor/wifi/hostapd/hostapd.conf
/data/vendor/wifi/hostapd/hostapd.accept
/data/vendor/wifi/hostapd/hostapd.deny



数据文件
/data/misc/wifi/WifiConfigStore.xml
/data/misc/wifi/softap.conf


socket文件
/dev/socket/vendor_wpa_wlan0
/data/vendor/misc/cutback/wpa_ctrl_1465-5
/data/vendor/misc/cutback/wpa_ctrl_1465-6
srw-rw---- 1 radio radio 0 2019-02-27 15:17 wpa_ctrl_1470-5
srw-rw---- 1 radio radio 0 2019-02-27 15:17 wpa_ctrl_1470-6
/data/vendor/wifi/hostapd/ctrl/wlan0   
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-1
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-2
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1145-5
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1145-6
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1147-15
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1147-16
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1150-3
srwxrwx--- 1 wifi   wifi 0 2019-02-27 15:17 wlan0
srw-rw---- 1 system wifi 0 2019-02-01 17:34 wpa_ctrl_1142-1
srw-rw---- 1 system wifi 0 2019-02-01 17:34 wpa_ctrl_1142-2


Log文件
foles:/data/vendor/wifi/wlan_logs/cnss_fw_logs_000.txt  
foles:/data/vendor/wifi/wlan_logs/host_driver_logs_000.txt
foles:/data/vendor/wifi/wlan_logs/txrx_pktlog_000.dat



APK应用文件
/system/priv-app/WpaConfigApp/WpaConfigApp.apk


```

# wpa_supplicant初始化文件
```



/device/xxxx/common/etc/init.xxx.rc
service wpa_supplicant /vendor/bin/hw/wpa_supplicant \        #  【 以运行服务的方式 执行二进制可执行文件 wpa_supplicant /vendor/bin/hw/wpa_supplicant 】
    -O/data/vendor/wifi/wpa/sockets  \                        #  【-O  参数指定 ctrl_interface 控制接口工作目录】                         
    -puse_p2p_group_interface=1  \                            #  【-p 配置驱动参数 key=value 】
    -dd \                                                     #  【 -dd 设置log等级2  -ddd   -dddd  Log等级越高 】
    -g@android:vendor_wpa_wlan0                               #  【-g 配置global ctrl_interface 全局控制接口 [ @android:vendor_wpa_wlan0 ] /dev/socket/vendor_wpa_wlan0 】
#   we will start as root and wpa_supplicant will switch to user wifi
#   after setting up the capabilities required for WEXT
#   user wifi
#   group wifi inet keystore
    interface android.hardware.wifi.supplicant@1.0::ISupplicant default #【 安卓O的 HAL 接口 定义 http://androidxref.com/9.0.0_r3/xref/hardware/interfaces/current.txt 接口集合 】
    interface android.hardware.wifi.supplicant@1.1::ISupplicant default
    class main
    socket vendor_wpa_wlan0 dgram 660 wifi wifi  ##【 表示新建一个socket 这个socket的名字是 vendor_wpa_wlan0 属于用户群组 wifi  socket路径为 /dev/socket/vendor_wpa_wlan0】
    disabled     
    oneshot       ## 【表示 只创建只能存在一个相同进程】
------------------------------

/device/xxxx/common/etc/init.xxx.rc
service vendor.xx_supplicant /vendor/bin/hw/wpa_supplicant \  #  【 以运行服务的方式 执行二进制可执行文件 wpa_supplicant /vendor/bin/hw/wpa_supplicant 】
    -iwigig0                                               
    -Dnl80211 
    -c/data/vendor/wifi/wpa/wpa_supplicant.conf \
    -m/data/vendor/wifi/wigig_p2p_supplicant.conf \
    -O/data/vendor/wifi/wigig_sockets  \
    -dd \
    -e/data/vendor/wifi/wigig_entropy.bin -g@android:wpa_wigig0 \
    -S wigigsvc 
    #   we will start as root and wpa_supplicant will switch to user wifi
    #   after setting up the capabilities required for WEXT
    #   user wifi
    #   group wifi inet keystore
    class main
    socket wpa_wigig0 dgram 660 wifi wifi
    disabled
    oneshot

------------------------------


init.mt6795.rc
service wpa_supplicant /system/bin/wpa_supplicant \
        -iwlan0 -Dnl80211 -c/data/misc/wifi/wpa_supplicant.conf -e/data/misc/wifi/entropy.bin -ddd \
        -I/system/etc/wifi/wpa_supplicant_overlay.conf \
        -O/data/misc/wifi/sockets -g@android:wpa_wlan0
        #   we will start as root and wpa_supplicant will switch to user wifi
        socket wpa_wlan0 dgram 660 wifi wifi

解析:
1.启动一个服务，这个服务的名字为wpa_supplicant，这个服务是执行/system/bin/wpa_supplicant二进制可执行文件启动
2.参数说明
-i 用于配置 wpa_supplicant 硬件接口名 指定网络设备接口名  [wlan0]
-D 用于配置 wpa_supplicant 使用的驱动程序[nl80211]
-e 用于配置产生随机数的可执行程序
-I 用于配置当前wpa_supplicant程序的一些feature 以及一些预定义网络所需要的配置文件
-d 增加调试信息
-O 配置 override ctrl_interface parameter for new interfaces
-g 配置global ctrl_interface 全局控制接口 [ @android:wpa_wlan0 ] wpa_wlan0
  socket wpa_wlan0 dgram 660 wifi wifi 表示新建一个socket 这个socket的名字是wpa_wlan0 属于用户wifi
  对应的socket路径为 /dev/socket/wpa_wlan0


ps -g wifi
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           912     1   27136  10140 binder_io+          0 S android.hardware.wifi@1.0-service
wifi          1267     1   19180   6132 ep_poll             0 S wificond
wifi         31519     1 2144672  13008 poll_sche+          0 S wpa_supplicant


```
# wpa_supplicant进程信息
```

F,S,PRI,NI,CPU,PCPU,C,RSS,WCHAN,SZ,VSZ,LABEL,ARGS=CMD | grep wpa                               <
1010  5102  5102     1    6 2019-02-27 15:17:49    20:27:52 00:00:19 4 S  19   0   2  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0
1010  5102  5144     1    6 2019-02-27 15:17:50    20:27:51 00:00:01 1 S  19   0   5  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0
1010  5102  5145     1    6 2019-02-27 15:17:50    20:27:51 00:00:01 1 S  19   0   5  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0
1010  5102  5147     1    6 2019-02-27 15:17:50    20:27:51 00:00:01 1 S  19   0   5  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0
1010  5102  5148     1    6 2019-02-27 15:17:50    20:27:51 00:00:01 1 S  19   0   0  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0
1010  5102  5157     1    6 2019-02-27 15:17:50    20:27:51 00:00:01 1 S  19   0   3  0.0  0  13236 poll_schedule_timeout       536168 2144672 u:r:hal_wifi_supplicant_default:s0       wpa_supplicant -O/data/vendor/wifi/wpa/sockets -puse_p2p_group_interface=1 -dd -g@android:vendor_wpa_wlan0

F----------------(Flag 位标记)-----------------(0_0000 普通进程)  (1_0001 Fork后未执行的进程 ）(4_0100 使用超级用户权限的进程)   (5_0101 超级用户权限Fork后未执行的进程) (1=FORKNOEXEC 4=SUPERPRIV)
S---------------(进程状态Process state)--------【R(正在运行running)） S(睡眠sleeping)  D(设备输入输出device I/O)  T(停止stopped)   t(追踪traced)  Z(僵尸进程zombie) X(deader)   x(dead)       K(wakekill)  W(waking)】
PRI-------------(进程优先级Priority , 数值越大优先级越高)
NI--------------(CPU调度优先级  数值越低 CPU 优先调度)-----------------------------niceness的范围一般从 -20 到 19，-20 表示调度优先级最高，19 表示优先级最低
CPU------------(标明 哪一个CPU在运行进程Which processor running on)  0,1,2,3,4,5,6,7
PCPU------------(该进程占用的CPU时间和总时间的百分比 Percentage of CPU time used)
C----------------(代表CPU使用率 百分比)------(77 , 1 ,0 ...)
RSS-------------进程占用的固定的内存量 (Kbytes) Resident Set Size (pages in use)------------- 2396kB  4680KB 1208KB  0KB ....
WCHAN----------(正在等待的进程资源 What are we waiting in kernel for) 默认0
SZ--------------(使用掉的内存大小 Memory Size )  默认0
VSZ------------进程使用的虚拟内存大小 Virtual memory size (1k units)
LABEL----------(信息安全标签 Linux系统上下文使用 Security label)  u:r:init:s0   0 u:r:kernel:s0  u:r:hal_sensors_default:s0   u:r:hal_wifi_default:s0   u:r:audioserver:s0
MAJFL----------(主要页错误的数量  Major page faults)

```

# /vendor/bin/hw/wpa_supplicant 命令使用说明
```
wpa_supplicant v2.7-devel-9
Copyright (c) 2003-2017, Jouni Malinen <j@w1.fi> and contributors

This software may be distributed under the terms of the BSD license.
See README for more details.

This product includes software developed by the OpenSSL Project
for use in the OpenSSL Toolkit (http://www.openssl.org/)

usage:
  wpa_supplicant [-BddhKLqqtvW] [-P<pid file>] [-g<global ctrl>] \
        [-G<group>] \
        -i<ifname> -c<config file> [-C<ctrl>] [-D<driver>] [-p<driver_param>] \
        [-b<br_ifname>] [-e<entropy file>] \
        [-o<override driver>] [-O<override ctrl>] \
        [-N -i<ifname> -c<conf> [-C<ctrl>] [-D<driver>] \
        [-m<P2P Device config file>] \
        [-p<driver_param>] [-b<br_ifname>] [-I<config file>] ...]

drivers:
  nl80211 = Linux nl80211/cfg80211
options:
  -b = optional bridge interface name      网桥接口名称 -bxxx【wpa_interface.bridge_ifname】
  -B = run daemon in the background       daemonize守护进程  -B  【 wpa_params.daemon】
  -c = Configuration file   配置文件文件路径全名    -c/data/vendor/wifi/wpa/wpa_supplicant.conf   【wpa_interface.confname】
  -C = ctrl_interface parameter (only used if -c is not)
  -d = increase debugging verbosity (-dd even more)  Log打印等级信息  -dd -ddd -dddd   【wpa_params.wpa_debug_level】
  -D = driver name (can be multiple drivers: nl80211,wext)    驱动程序类型   -Dnl80211    -Dwext 【wpa_interface.driver】
  -e = entropy file   用于产生随机数的可执行程序用于加密  -e/data/misc/wifi/entropy.bin    【wpa_params.entropy_file 】
  -g = global ctrl_interface   全局控制接口  -g@android:vendor_wpa_wlan0             【wpa_params.ctrl_interface】
  -G = global ctrl_interface group
  -h = show this help text
  -i = interface name       网卡接口名称    -iwlan0  【wpa_interface.ifname】
  -I = additional configuration file  备份附加的配置文件全名   -I/system/etc/wifi/wpa_supplicant_overlay.conf  【wpa_interface.confanother】
  -K = include keys (passwords, etc.) in debug output
  -L = show license (BSD)
  -m = Configuration file for the P2P Device interface     P2P设备接口配置文件 -m/data/vendor/wifi/wigig_p2p_supplicant.conf  【wpa_params.conf_p2p_dev 】
  -N = start describing new interface
  -o = override driver parameter for new interfaces        
  -O = override ctrl_interface parameter for new interfaces    -O 指定控制接口参数 -O/data/misc/wifi/sockets  【wpa_params.override_ctrl_interface】
  -p = driver parameters   驱动参数键值对 -puse_p2p_group_interface=1  【wpa_interface.driver_param】
  -P = PID file
  -q = decrease debugging verbosity (-qq even less)
  -S = override default HIDL service name
  -t = include timestamp in debug messages
  -v = show version
  -W = wait for a control interface monitor before starting
example:
  wpa_supplicant -Dnl80211 -iwlan0 -c/etc/wpa_supplicant.conf


```

# wpa_supplicant 接口
## wpa_supplicant上行接口
```
wpa_supplicant的上行接口 
wpa_supplicant 提供两种由外部模块获取信息的方式：
一种是外部模块通过发送request 命令然后获取response的问答模式，
一种是wpa_supplicant主动向外部发送event事件，由外部模块监听接收。

一般的常用做法是外部模块通过调用wpa_ctrl_open()两次，分别建立两个control interface接口。
一个为ctrl interface，用于发送命令，获取信息。
一个接口调用wpa_ctrl_attach，成为 monitor interface，用于监听接收来自于wpa_supplicant的event事件。此举可以降低通信的耦合性，避免response和event的相互干扰。

主要作用: 通过Unix domainsocket建立一个control interface 的client结点，与作为server的wpa_supplicant结点通信


/data/vendor/wifi/wpa/sockets
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-1
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-2

/dev/socket/vendor_wpa_wlan0
```

## wpa_supplicant下行接口
```
WPA_SUPPLICANT 中的NL80211 数据结构  使用netlink与驱动交互
nl80211其实就是利用netlink机制将一些802.11相关的命令和参数发送给驱动去执行
// 发送netlink消息
ret = send_and_recv_msgs(drv, msg, NULL, NULL);


```



# wpa_supplicant初始化过程main分析
```
main 函数位置
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/main.c#181



int main(int argc, char *argv[])
{
	int c, i;
	struct wpa_interface *ifaces, *iface;
	int iface_count, exitcode = -1;
	struct wpa_params params;
	struct wpa_global *global;

	if (os_program_init())  // 【1】
		return -1;

	os_memset(&params, 0, sizeof(params));
	params.wpa_debug_level = MSG_INFO;

	iface = ifaces = os_zalloc(sizeof(struct wpa_interface));
	if (ifaces == NULL)
		return -1;
	iface_count = 1;

	wpa_supplicant_fd_workaround(1);  // 【2】

	for (;;) {
		c = getopt(argc, argv,
			   "b:Bc:C:D:de:f:g:G:hi:I:KLMm:No:O:p:P:qsTtuvW");
		if (c < 0)
			break;
		switch (c) {
		case 'b':
			iface->bridge_ifname = optarg;
			break;
		case 'B':
			params.daemonize++;
			break;
		case 'm':
			params.conf_p2p_dev = optarg;
			break;
		case 'e':
			params.entropy_file = optarg;
			break;
		case 'g':
			params.ctrl_interface = optarg;
			break;
		case 'p':
			iface->driver_param = optarg;
			break;
		case 'O':
			params.override_ctrl_interface = optarg;
			break;
		case 'i':
			iface->ifname = optarg;
			break;
		case 'I':
			iface->confanother = optarg;
			break;
		case 'D':
			iface->driver = optarg;
			break;
		case 'd':
			break;
			params.wpa_debug_level--;  
		case 'c':
			iface->confname = optarg;
			break;
 // ...................   循环查找命令行输入参数

}

	exitcode = 0;
	global = wpa_supplicant_init(&params);   // 【3】
	if (global == NULL) {
		wpa_printf(MSG_ERROR, "Failed to initialize wpa_supplicant");
		exitcode = -1;
		goto out;
	} else {
		wpa_printf(MSG_INFO, "Successfully initialized "
			   "wpa_supplicant");
	}

	if (fst_global_init()) {
		wpa_printf(MSG_ERROR, "Failed to initialize FST");
		exitcode = -1;
		goto out;
	}

	for (i = 0; exitcode == 0 && i < iface_count; i++) {
		struct wpa_supplicant *wpa_s;

		if ((ifaces[i].confname == NULL && ifaces[i].ctrl_interface == NULL) || ifaces[i].ifname == NULL) {
			if (iface_count == 1 && (params.ctrl_interface ) ){
                break;
            }
			usage();
			exitcode = -1;
			break;
         }
		wpa_s = wpa_supplicant_add_iface(global, &ifaces[i], NULL); // 【4】
		if (wpa_s == NULL) {
			exitcode = -1;
			break;
		}
    }// for-end


	if (exitcode == 0)
		exitcode = wpa_supplicant_run(global);  // 【5】

	wpa_supplicant_deinit(global);  //  【6】

	fst_global_deinit();    // 【7】

out:
	wpa_supplicant_fd_workaround(0);
	os_free(ifaces);
	os_free(params.pid_file);
	os_program_deinit();
	return exitcode;
}




```

## os_unix.c int os_program_init()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/android.config#248
CONFIG_OS=unix  

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/Android.mk#137
ifndef CONFIG_OS
ifdef CONFIG_NATIVE_WINDOWS
CONFIG_OS=win32
else
CONFIG_OS=unix
endif
endif
/* 如果没有指定CONFIG_OS宏，则看下有没有指定CONFIG_NATIVE_WINDOWS，若指定了那么CONFIG_OS就是win32，否则是unix * / 
/* Android底层是基于Linux，属于unix   os_program_init()这个方法自然是调用的os_unix.c中的方法 */ 


```

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/utils/os_unix.c#326

int os_program_init(void)
{
#ifdef ANDROID
	struct __user_cap_header_struct header;
	struct __user_cap_data_struct cap;
	struct group *grp = getgrnam("wifi"); 【1】
	gid_t gid_wifi = grp ? grp->gr_gid : 0;
	struct passwd *pwd = getpwnam("wifi"); 【2】
	uid_t uid_wifi = pwd ? pwd->pw_uid : 0;

	/*
	 * We ignore errors here since errors are normal if we
	 * are already running as non-root.
	 */

	gid_t groups[4];
	int group_idx = 0;

	if (!gid_wifi || !uid_wifi) return -1;
	groups[group_idx] = gid_wifi;

	grp = getgrnam("inet");
	groups[++group_idx] = grp ? grp->gr_gid : 0;
	if (!groups[group_idx]) return -1;

	grp = getgrnam("keystore");
	groups[++group_idx] = grp ? grp->gr_gid : 0;
	if (!groups[group_idx]) return -1;

	grp = getgrnam("log");
	groups[++group_idx] = grp ? grp->gr_gid : 0;
	if (!groups[group_idx]) group_idx--;

	setgroups(group_idx + 1, groups); 【3】

	prctl(PR_SET_KEEPCAPS, 1, 0, 0, 0);  【4】

	setgid(gid_wifi);  // 设置组id  设置用户id
	setuid(uid_wifi);

	header.version = _LINUX_CAPABILITY_VERSION;
	header.pid = 0;
	cap.effective = cap.permitted =
		(1 << CAP_NET_ADMIN) | (1 << CAP_NET_RAW);
	cap.inheritable = 0;
	capset(&header, &cap);  【5】
	return 0;
}


```

```
数据结构:

http://androidxref.com/9.0.0_r3/xref/external/kernel-headers/original/uapi/linux/capability.h#39
typedef struct __user_cap_header_struct {
	__u32 version;   // _LINUX_CAPABILITY_U32S_1 或者  _LINUX_CAPABILITY_U32S_2
	int pid;        // 进程ID
} __user *cap_user_header_t;



typedef struct __user_cap_data_struct {
        __u32 effective;    //  进程有三个能力集：允许（permitted，P）、可继承（inheritable，I） 和有效（effective，E）。在产生进程时，子进程从父进程复制能力集
        __u32 permitted;
        __u32 inheritable;
} __user *cap_user_data_t;

#define _LINUX_CAPABILITY_VERSION_1  0x19980330
#define _LINUX_CAPABILITY_U32S_1     1

#define _LINUX_CAPABILITY_VERSION_2  0x20071026
#define _LINUX_CAPABILITY_U32S_2     2
32 位系统要在程序中使用 _LINUX_CAPABILITY_VERSION_1 这个宏，而 64 位系统使用 _LINUX_CAPABILITY_VERSION_2 宏





typedef unsigned int gid_t
http://androidxref.com/9.0.0_r3/xref/bionic/libc/include/sys/types.h#43

/* gids 组ID, uids 用户ID, and pids 进程ID are all 32-bit. 【32位 int值】 */
typedef __kernel_gid32_t __gid_t;
typedef __gid_t gid_t;
typedef __kernel_uid32_t __uid_t;
typedef __uid_t uid_t;
typedef __kernel_pid_t __pid_t;
typedef __pid_t pid_t;
typedef uint32_t __id_t;
typedef __id_t id_t;

http://androidxref.com/9.0.0_r3/xref/bionic/libc/kernel/uapi/asm-generic/posix_types.h#50
typedef unsigned int __kernel_uid32_t;
typedef unsigned int __kernel_gid32_t;

```
### getgrnam("wifi")
```
https://blog.csdn.net/Michaelwubo/article/details/41210083
定义函数：strcut group * getgrnam(const char * name);
函数说明：getgrnam()用来逐一搜索参数名指定的组名称, 找到时便将该组的数据以group 结构返回
头文件：#include <grp.h>    #include <sys/types.h>
返回值：返回 group 结构数据, 如果返回NULL 则表示已无数据, 或有错误发生.


/* 取得adm 的组数据 */
#include <grp.h>
#include <sys/types.h>
main()
{
    strcut group * data;
    int i = 0;
    data = getgrnam("adm");
    printf("%s:%s:%d:", data->gr_name, data->gr_passwd, data->gr_gid);
    while(data->gr_mem[i])
        printf("%s, ", data->gr_mem[i++]);
    printf("\n");
}

执行：
adm:x:4:


http://androidxref.com/9.0.0_r3/xref/external/e2fsprogs/include/mingw/grp.h#8

struct group
  {
    char *gr_name;     // 组名称  
    char *gr_passwd;   // 组密码       
    __gid_t gr_gid;    // 组id GID       
    char **gr_mem;     // 用户列表组成员     
  }; 

adm:x:4  // 组名称  组密码    组id GID   
root, adm, daemon  // 用户列表组成员  

wifi:x:1010
system,wifi,root
```

### getpwnam("wifi")
```
原型定义: struct passwd *getpwnam(const char *name);
返回值：若成功，返回指针；若出错或者达到文件尾端，返回NULL。
头文件：#include <grp.h>    #include <sys/types.h>
函数功能：  获取用户登录相关信息


struct passwd {
char * pw_name;    /* Username. */
char * pw_passwd;   /* Password. */
__uid_t pw_uid;     /* User ID. */
__gid_t pw_gid;     /* Group ID. */
char * pw_gecos;    /* Real name. */
char * pw_dir;      /* Home directory. -*/
char * pw_shell;    /* Shell program. */
};

```

```
#include <stdio.h>
#include <pwd.h>
 
int main()
{
        struct passwd * pw;
        char *username = "zukgit";
        pw = getpwnam(username);
        if(!pw)
        {
                printf("%s is not exist\n", username);
                return -1;
        }
 
        printf("pw->pw_name = %s\n", pw->pw_name);
        printf("pw->pw_passwd = %s\n", pw->pw_passwd);
        printf("pw->pw_uid = %d\n", pw->pw_uid);
        printf("pw->pw_gid = %d\n", pw->pw_gid);
        printf("pw->pw_gecos = %s\n", pw->pw_gecos);
        printf("pw->pw_dir = %s\n", pw->pw_dir);
        printf("pw->pw_shell = %s\n", pw->pw_shell);
 
        return 0;
}


/**

 
运行的结果：
pw->pw_name = zukgit
pw->pw_passwd = x
pw->pw_uid = 500
pw->pw_gid = 500
pw->pw_gecos = devis
pw->pw_dir = /home/devis
pw->pw_shell = /bin/bash
**/

```


### setgroups
```
表头文件 #include<grp.h>
定义函数 int setgroups(size_t size,const gid_t * list);
函数说明 setgroups()用来将list 数组中所标明的组加入到目前进程的组设置中 参数size为list()的gid_t数目，最大值为NGROUP(32)。
返回值 设置成功则返回0，如有错误则返回-1。

错误代码 
EFAULT 参数list数组地址不合法。
EPERM 权限不足，必须是root权限
EINVAL 参数size值大于NGROUP(32)。

```


### prctl
```
prctl(PR_SET_KEEPCAPS, 1, 0, 0, 0);  // 进程的执行时保持进程的能力 权限 等

程序告诉系统，它希望保留它的能力，但是要修改它的有效 userid。这要使用 prctl
进程有三个能力集：允许（permitted，P）、可继承（inheritable，I） 和有效（effective，E）。在产生进程时，子进程从父进程复制能力集

表头文件 #include <sys/prctl.h>
定义函数  int prctl(int option, unsigned long arg2, unsigned long arg3, unsigned long arg4, unsigned long arg5);
功能：
第一个参数是操作类型，指定 PR_SET_NAME，即设置进程名  可选值如下

1. PR_CAPBSET_READ
2. PR_CAPBSET_DROP 
3. PR_SET_CHILD_SUBREAPER 
4. PR_GET_CHILD_SUBREAPER 
5. PR_SET_DUMPABLE 
6. PR_SET_ENDIAN 
7. PR_GET_ENDIAN 
8. PR_SET_FPEMU 
9. PR_GET_FPEMU 
10. PR_SET_FPEXC 
11. PR_GET_FPEXC 
12. PR_SET_KEEPCAPS 
13. PR_GET_KEEPCAPS 
14. PR_SET_NAME 
    1) Set the name of the calling thread, using the value in the location pointed to by (char *) arg2.  
    2) The name can be up to 16 bytes long, including the terminating null byte. (If the length of the string, including the terminating null byte, exceeds 16 bytes, the string is silently truncated.) 
    3) This is the same attribute that can be set via pthread_setname_np and retrieved using pthread_getname_np.  
    4) The attribute is likewise accessible via /proc/self/task/[tid]/comm, where tid is the name of the calling thread.
15. PR_GET_NAME 
16. PR_SET_NO_NEW_PRIVS 
17. PR_GET_NO_NEW_PRIVS 
18. PR_SET_PDEATHSIG 
19. PR_GET_PDEATHSIG 
20. PR_SET_PTRACER 
21. PR_SET_SECCOMP 
22. PR_GET_SECCOMP 
23. PR_SET_SECUREBITS 
24. PR_GET_SECUREBITS 
25. PR_SET_THP_DISABLE 
26. PR_GET_THP_DISABLE 
27. PR_GET_TID_ADDRESS 
28. PR_SET_TIMERSLACK 
29. PR_GET_TIMERSLACK 
30. PR_SET_TIMING 
31. PR_GET_TIMING 
32. PR_TASK_PERF_EVENTS_DISABLE 
33. PR_TASK_PERF_EVENTS_ENABLE 
34. PR_SET_TSC 
35. PR_GET_TSC 
36. PR_SET_UNALIGN
37. PR_GET_UNALIGN
38. PR_MCE_KILL 
39. PR_MCE_KILL_GET 
40. PR_SET_MM 
41. PR_MPX_ENABLE_MANAGEMENT, PR_MPX_DISABLE_MANAGEMENT

```

### capset(&header, &cap);
```
capset() 用来设置进程权能。

#undef _POSIX_SOURCE
#include <sys/capability.h>

int capset(cap_user_header_t hdrp, const cap_user_data_t datap);

```

```
#undef _POSIX_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <linux/capability.h>
#include <errno.h>

int main()
{
     struct __user_cap_header_struct cap_header_data;
     cap_user_header_t cap_header = &cap_header_data;

     struct __user_cap_data_struct cap_data_data;
     cap_user_data_t cap_data = &cap_data_data;

     cap_header->pid = getpid();
     cap_header->version = _LINUX_CAPABILITY_VERSION_1;

     if (capget(cap_header, cap_data) < 0) {
         perror("Failed capget");
         exit(1);
     }
     printf("Cap data 0x%x, 0x%x, 0x%x/n", cap_data->effective,
         cap_data->permitted, cap_data->inheritable);
}

Cap data 0xffffffff, 0xffffffff, 0x0
```


## void wpa_supplicant_fd_workaround(1)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/main.c#128
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/android.config#248
http://androidxref.com/9.0.0_r3/xref/external/libchrome/build/build_config.h#41 
#define __linux__ 1

```


```
static void wpa_supplicant_fd_workaround(int start)
{
#ifdef __linux__
	static int fd[3] = { -1, -1, -1 };
	int i;
	/* When started from pcmcia-cs scripts, wpa_supplicant might start with
	 * fd 0, 1, and 2 closed. This will cause some issues because many
	 * places in wpa_supplicant are still printing out to stdout. As a
	 * workaround, make sure that fd's 0, 1, and 2 are not used for other
	 * sockets. */
	if (start) {
// 代码中利用while先把文件描述符0，1，2分配出去，以后再分配的时候就不会将stdin/stdout/stderr打开 而打开其他端口，以达到保护stdin/stdout/stderr 目的 
		for (i = 0; i < 3; i++) {                 
			fd[i] = open("/dev/null", O_RDWR);    // 对 stdin 标准输入/ stdout标准输出 /stderr 标准错误输出 进行保护  
			if (fd[i] > 2) {
				close(fd[i]);
				fd[i] = -1;
				break;
			}
		}
	} else {
		for (i = 0; i < 3; i++) {
			if (fd[i] >= 0) {
				close(fd[i]);
				fd[i] = -1;
			}
		}
	}
}

// open("/dev/null",O_RDWR)   这句返回一个文件描述符，成功返回大于等于0的数，出错返回-1 
// 标准输入描述符值为0      标准输出描述符值为1    标准错误输出描述符值为2   所以，一般open函数返回的值会是3以上的值。
// 确保 fd[3] 分别为  标准输入0   标准输出1  错误输出2  重新打开标准输入输出
// 避免了其他socket使用0,1,2这三个文件描述符而导致的程序出错。 
```
## wpa_global wpa_supplicant_init(&wpa_params)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5899

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/android.config
/**
 * wpa_supplicant_init - Initialize %wpa_supplicant
 * @params: Parameters for %wpa_supplicant
 * Returns: Pointer to global %wpa_supplicant data, or %NULL on failure
 *
 * This function is used to initialize %wpa_supplicant. After successful
 * initialization, the returned data pointer can be used to add and remove
 * network interfaces, and eventually, to deinitialize %wpa_supplicant.
 */


```

```

struct wpa_global * wpa_supplicant_init(struct wpa_params *params)
{
	struct wpa_global *global;
	int ret, i;

	if (params == NULL)
		return NULL;




	wpa_msg_register_ifname_cb(wpa_supplicant_msg_ifname_cb); 【1】

	if (params->wpa_debug_file_path)
		wpa_debug_open_file(params->wpa_debug_file_path); 
	else
		wpa_debug_setup_stdout();  【2】

	if (params->wpa_debug_syslog)
		wpa_debug_open_syslog();

	if (params->wpa_debug_tracing) {
		ret = wpa_debug_open_linux_tracing();
		if (ret) {
			wpa_printf(MSG_ERROR,
				   "Failed to enable trace logging");
			return NULL;
		}
	}

	ret = eap_register_methods();  【3】
	if (ret) {
		wpa_printf(MSG_ERROR, "Failed to register EAP methods");
		if (ret == -2)
			wpa_printf(MSG_ERROR, "Two or more EAP methods used "
				   "the same EAP type.");
		return NULL;
	}

	global = os_zalloc(sizeof(*global));
	if (global == NULL)
		return NULL;
	dl_list_init(&global->p2p_srv_bonjour); 【4】
	dl_list_init(&global->p2p_srv_upnp);
	global->params.daemonize = params->daemonize;
	global->params.wait_for_monitor = params->wait_for_monitor;
	global->params.dbus_ctrl_interface = params->dbus_ctrl_interface;
	if (params->pid_file)
		global->params.pid_file = os_strdup(params->pid_file);

	if (params->ctrl_interface)
		global->params.ctrl_interface = os_strdup(params->ctrl_interface);

	if (params->ctrl_interface_group)
		global->params.ctrl_interface_group = os_strdup(params->ctrl_interface_group);

	if (params->override_driver)
		global->params.override_driver = os_strdup(params->override_driver);

	if (params->override_ctrl_interface)
		global->params.override_ctrl_interface = os_strdup(params->override_ctrl_interface);



	if (params->conf_p2p_dev)
		global->params.conf_p2p_dev = os_strdup(params->conf_p2p_dev);

	wpa_debug_level     = global->params.wpa_debug_level     = params->wpa_debug_level;
	wpa_debug_show_keys = global->params.wpa_debug_show_keys = params->wpa_debug_show_keys;
	wpa_debug_timestamp = global->params.wpa_debug_timestamp = params->wpa_debug_timestamp;

	wpa_printf(MSG_DEBUG, "wpa_supplicant v" VERSION_STR);

	if (eloop_init()) { 【5】
		wpa_printf(MSG_ERROR, "Failed to initialize event loop");
		wpa_supplicant_deinit(global);
		return NULL;
	}

	random_init(params->entropy_file); 【6】

	global->ctrl_iface = wpa_supplicant_global_ctrl_iface_init(global);  【7】
	if (global->ctrl_iface == NULL) {
		wpa_supplicant_deinit(global);
		return NULL;
	}

	if (wpas_notify_supplicant_initialized(global)) {   【8】
		wpa_supplicant_deinit(global);
		return NULL;
	}

	for (i = 0; wpa_drivers[i]; i++)
		global->drv_count++;
	if (global->drv_count == 0) {
		wpa_printf(MSG_ERROR, "No drivers enabled");
		wpa_supplicant_deinit(global);
		return NULL;
	}
	global->drv_priv = os_calloc(global->drv_count, sizeof(void *));
	if (global->drv_priv == NULL) {
		wpa_supplicant_deinit(global);
		return NULL;
	}

	if (wifi_display_init(global) < 0) { 【9】
		wpa_printf(MSG_ERROR, "Failed to initialize Wi-Fi Display");
		wpa_supplicant_deinit(global);
		return NULL;
	}


	eloop_register_timeout(WPA_SUPPLICANT_CLEANUP_INTERVAL, 0, wpas_periodic, global, NULL);  【10】

	return global;
}

```


### wpa_msg_register_ifname_cb
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpa_debug.c#601
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpa_debug.h#280


	wpa_msg_register_ifname_cb(wpa_supplicant_msg_ifname_cb); 【1】

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5853

static const char * wpa_supplicant_msg_ifname_cb(void *ctx)  // 返回ifname  接口名称
{
	struct wpa_supplicant *wpa_s = ctx;   // 把指向无类型的指针强制转换为 wpa_supplicant指针
	if (wpa_s == NULL)
		return NULL;
	return wpa_s->ifname;
}

```

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpa_debug.h#280

typedef const char * (*wpa_msg_get_ifname_func)(void *ctx);   // 函数名 wpa_msg_get_ifname_func   参数是一个void*无类型指针(void* 方法名称)    返回值是 const char* 


static wpa_msg_get_ifname_func wpa_msg_ifname_cb = NULL;

void wpa_msg_register_ifname_cb(wpa_msg_get_ifname_func func)
{
	wpa_msg_ifname_cb = func;
}



```

### wpa_debug_setup_stdout()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpa_debug.c#580

void wpa_debug_setup_stdout(void)
{
#ifndef _WIN32
	setvbuf(stdout, NULL, _IOLBF, 0); 【1】  // 设置输出Log缓存区域类型为  行缓冲
#endif /* _WIN32 */
}

stdout 是一个定义在 <stdio.h>的宏（macro）  ，它展开到一个 FILE* （“指向 FILE 的指针”）类型的表达式   指向标准输出文件的句柄 
```


#### setvbuf(stdout, NULL, _IOLBF, 0)  
```
用 法:             int setvbuf(FILE *stream, char *buf, int type, unsigned size);
setvbuf函数作用:   用于设定文件流的缓冲区

type ： 期望缓冲区的类型：
_IOFBF(满缓冲）：当缓冲区为空时，从流读入数据。或者当缓冲区满时，向流写入数 据。
_IOLBF(行缓冲）：每次从流中读入一行数据或向流中写入一行数据。
_IONBF(无缓冲）：直接从流中读入数据或直接向流中写入数据，而没有缓冲区。
size ： 缓冲区内字节的数量。

```

```
示例:  
#include <stdio.h>
 
int main()
{
    FILE *input, *output;
    char bufr[512];
    input = fopen("file.in", "r+b");
    output = fopen("file.out", "w");
    /* set up input stream for minimal disk access,
    using our own character buffer */
    if (setvbuf(input, bufr, _IOFBF, 512) != 0)
        printf("failed to set up buffer for input file\n");
    else
        printf("buffer set up for input file\n");
    /* set up output stream for line buffering using space that
    will be obtained through an indirect call to malloc */
    if (setvbuf(output, NULL, _IOLBF, 132) != 0)
        printf("failed to set up buffer for output file\n");
    else
        printf("buffer set up for output file\n");
    /* perform file I/O here */
    /* close files */
    fclose(input);
    fclose(output);
    return 0;
}

```


###  eap_register_methods()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/eap_register.c#24



/**
 * eap_register_methods - Register statically linked EAP methods
 * Returns: 0 on success, -1 or -2 on failure
 *
 * This function is called at program initialization to register all EAP
 * methods that were linked in statically.
 */

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/android.config#79
CONFIG_EAP_MD5=y
CONFIG_EAP_TLS=y
CONFIG_HS20=y   # Hotspot 2.0
CONFIG_EAP_MSCHAPV2=y
CONFIG_EAP_PEAP=y
CONFIG_EAP_TTLS=y
CONFIG_EAP_GTC=y
CONFIG_EAP_OTP=y
CONFIG_EAP_SIM=y
CONFIG_EAP_LEAP=y
CONFIG_EAP_AKA=y
CONFIG_EAP_AKA_PRIME=y

```


```

int eap_register_methods(void)
{
	int ret = 0;

	if (ret == 0)   ret = eap_peer_md5_register();  【1】

	if (ret == 0)	ret = eap_peer_tls_register();

	if (ret == 0)   ret = eap_peer_wfa_unauth_tls_register();

	if (ret == 0)   ret = eap_peer_mschapv2_register();

	if (ret == 0)   ret = eap_peer_peap_register();

	if (ret == 0)   ret = eap_peer_ttls_register();

	if (ret == 0)   ret = eap_peer_gtc_register();

	if (ret == 0)   ret = eap_peer_otp_register();

	if (ret == 0)   ret = eap_peer_sim_register();

	if (ret == 0)   ret = eap_peer_leap_register();

	if (ret == 0)   ret = eap_peer_aka_register();

	if (ret == 0)   ret = eap_peer_aka_prime_register();

	if (ret == 0)   ret = eap_peer_pwd_register();

	return ret;
}


```

#### eap_peer_md5_register
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eap_peer/eap_md5.c#102


int eap_peer_md5_register(void)
{
	struct eap_method *eap;

	eap = eap_peer_method_alloc(EAP_PEER_METHOD_INTERFACE_VERSION, EAP_VENDOR_IETF, EAP_TYPE_MD5, "MD5");  【1】
	if (eap == NULL)
		return -1;

	eap->init = eap_md5_init;
	eap->deinit = eap_md5_deinit;
	eap->process = eap_md5_process;

	return eap_peer_method_register(eap);  【2】
}


```

#####  eap_peer_method_alloc
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/eap_peer/eap_methods.c#281


struct eap_method * eap_peer_method_alloc(int version, int vendor, EapType method, const char *name)
{
	struct eap_method *eap;
	eap = os_zalloc(sizeof(*eap));
	if (eap == NULL)  return NULL;
	eap->version = version;
	eap->vendor = vendor;
	eap->method = method;
	eap->name = name;
	return eap;
}


```

##### eap_peer_method_register

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/eap_peer/eap_methods.c#316

/**
 * eap_peer_method_register - Register an EAP peer method
 * @method: EAP method to register from eap_peer_method_alloc()
 * Returns: 0 on success, -1 on invalid method, or -2 if a matching EAP method
 * has already been registered
 *
 * Each EAP peer method needs to call this function to register itself as a
 * supported EAP method. The caller must not free the allocated method data
 * regardless of the return value.
 */


int eap_peer_method_register(struct eap_method *method)
{
	struct eap_method *m, *last = NULL;

	if (method == NULL || method->name == NULL ||
	    method->version != EAP_PEER_METHOD_INTERFACE_VERSION) {
		eap_peer_method_free(method);
		return -1;
	}

	for (m = eap_methods; m; m = m->next) {
		if ((m->vendor == method->vendor && m->method == method->method) || os_strcmp(m->name, method->name) == 0) {
			eap_peer_method_free(method);
			return -2;
		}
		last = m;
	}

	if (last)
		last->next = method;  // 把eap_methods 链接起来 放在末尾
	else
		eap_methods = method;   

	return 0;
}



```
<img src="//../zimage/wireless/wifi/09_supplicant/eap_method.jpg"/>


### dl_list_init
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/list.h#22


dl_list_init(&global->p2p_srv_bonjour);   // 把数据结构  p2p_srv_bonjour 结成链表
dl_list_init(&global->p2p_srv_upnp);     // 把数据结构  p2p_srv_upnp  结成链表

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#246

struct p2p_srv_bonjour {   // p2p_srv_bonjour  包含链表指针 dl_list    包含dl_list  就可以把对应数据结构结成链接
	struct dl_list list;
	struct wpabuf *query;
	struct wpabuf *resp;
};

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#252 

struct p2p_srv_upnp {    //p2p_srv_upnp  包含链表指针 dl_list    包含dl_list  就可以把对应数据结构结成链接
	struct dl_list list;
	u8 version;
	char *service;
};


```

```

static inline void dl_list_init(struct dl_list *list)   // 把双向链表的前指针和后指针都指向自己
{
	list->next = list;
	list->prev = list;
}


```
<img src="//../zimage/wireless/wifi/09_supplicant/dl_list.jpg" width = "50%" height="50%"/>


### eloop_init()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#161


static struct eloop_data eloop;

int eloop_init(void)
{
	os_memset(&eloop, 0, sizeof(eloop));  
	dl_list_init(&eloop.timeout);   // 使用 dl_list timeout 这个双向链表变量 把所有的  eloop_data链接起来  这里初始化  两个链表都指向自己

	return 0;
}

```

### random_init(params->entropy_file)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/crypto/random.c#405

static char *random_entropy_file = NULL;

void random_init(const char *entropy_file)
{
	os_free(random_entropy_file);
	if (entropy_file)
		random_entropy_file = os_strdup(entropy_file);   //   -e/data/misc/wifi/entropy.bin   把  /data/misc/wifi/entropy.bin赋值到 random_entropy_file
	else
		random_entropy_file = NULL;

	random_read_entropy();   // 【1】

	if (random_fd >= 0)
		return;

	random_fd = open("/dev/random", O_RDONLY | O_NONBLOCK);  // 打开随机数设备 /dev/random是 Linux系统中提供的随机伪设备

	if (random_fd < 0) {
		wpa_printf(MSG_ERROR, "random: Cannot open /dev/random: %s", strerror(errno));
		return;
	}
	wpa_printf(MSG_DEBUG, "random: Trying to read entropy from /dev/random");

	eloop_register_read_sock(random_fd, random_read_fd, NULL, NULL);  //【2】


	random_write_entropy();
}


```

#### random_read_entropy()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/crypto/random.c#339


static void random_read_entropy(void)   // 检测随机数文件 /data/misc/wifi/entropy.bin 是否可用 
{
	char *buf;
	size_t len;

	if (!random_entropy_file)
		return;

	buf = os_readfile(random_entropy_file, &len);    // 从加密文件/data/misc/wifi/entropy.bin 读取字符串 
	if (buf == NULL)
		return; /* entropy file not yet available */

	if (len != 1 + RANDOM_ENTROPY_SIZE) {  // #define RANDOM_ENTROPY_SIZE 20
		wpa_printf(MSG_DEBUG, "random: Invalid entropy file %s",  random_entropy_file);
		os_free(buf);
		return;
	}

	own_pool_ready = (u8) buf[0];     // static unsigned int own_pool_ready = 0;
	random_add_randomness(buf + 1, RANDOM_ENTROPY_SIZE);
	random_entropy_file_read = 1;
	os_free(buf);
	wpa_printf(MSG_DEBUG, "random: Added entropy from %s (own_pool_ready=%u)",random_entropy_file, own_pool_ready);
}


```

#### eloop_register_read_sock(random_fd, random_read_fd, NULL, NULL)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#711

int eloop_register_read_sock(int sock, eloop_sock_handler handler,void *eloop_data, void *user_data)
{
	return eloop_register_sock(sock, EVENT_TYPE_READ, handler,   eloop_data, user_data); // 【1】
}


```

##### eloop_register_sock()_1
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#740
int eloop_register_sock(int sock, eloop_event_type type,eloop_sock_handler handler,void *eloop_data, void *user_data)
{
	struct eloop_sock_table *table;

	assert(sock >= 0);
	table = eloop_get_sock_table(type);
	return eloop_sock_table_add_sock(table, sock, handler, eloop_data, user_data);   // 【1】
}



http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#725


static struct eloop_sock_table *eloop_get_sock_table(eloop_event_type type)    //  返回依据类型初始化了的 eloop_data中的 reader  writer exception table
{
	switch (type) {
	case EVENT_TYPE_READ:
		return &eloop.readers;
	case EVENT_TYPE_WRITE:
		return &eloop.writers;
	case EVENT_TYPE_EXCEPTION:
		return &eloop.exceptions;
	}

	return NULL;
}


```


##### eloop_sock_table_add_sock
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#253


static int eloop_sock_table_add_sock(struct eloop_sock_table *table,int sock, eloop_sock_handler handler,void *eloop_data, void *user_data)
{


	struct eloop_sock *tmp;
	int new_max_sock;

	if (sock > eloop.max_sock)   // eloop_data 存储的 int max_sock  始终更新为最大的值
		new_max_sock = sock;
	else
		new_max_sock = eloop.max_sock;

	if (table == NULL)
		return -1;

	eloop_trace_sock_remove_ref(table);  【1】
	tmp = os_realloc_array(table->table, table->count + 1,sizeof(struct eloop_sock));
	if (tmp == NULL) {
		eloop_trace_sock_add_ref(table);  【1】
		return -1;
	}

	tmp[table->count].sock = sock;
	tmp[table->count].eloop_data = eloop_data;
	tmp[table->count].user_data = user_data;
	tmp[table->count].handler = handler;
	wpa_trace_record(&tmp[table->count]);  【2】
	table->count++;
	table->table = tmp;
	eloop.max_sock = new_max_sock;
	eloop.count++;
	table->changed = 1;
	eloop_trace_sock_add_ref(table);  【1】
	return 0;
}

```

###### eloop_trace_sock_remove_ref
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#140


static struct eloop_data eloop;

没有定义WPA_TRACE   那么  函数 eloop_trace_sock_remove_ref 就是空     #define eloop_trace_sock_remove_ref(table) do { } while (0)
没有定义WPA_TRACE   那么  函数 eloop_trace_sock_add_ref就是空   #define eloop_trace_sock_remove_ref(table) do { } while (0)


#ifdef WPA_TRACE

static void eloop_sigsegv_handler(int sig)
{
	wpa_trace_show("eloop SIGSEGV");
	abort();
}

static void eloop_trace_sock_add_ref(struct eloop_sock_table *table)
{
	int i;
	if (table == NULL || table->table == NULL)
		return;
	for (i = 0; i < table->count; i++) {
		wpa_trace_add_ref(&table->table[i], eloop,table->table[i].eloop_data);
		wpa_trace_add_ref(&table->table[i], user,table->table[i].user_data);
	}
}


static void eloop_trace_sock_remove_ref(struct eloop_sock_table *table)
{
	int i;
	if (table == NULL || table->table == NULL)
		return;
	for (i = 0; i < table->count; i++) {  // 遍历 epool_sock_table上的所有 epool_sock  和  epool_sock.eloop_data    epool_sock.user_data
		wpa_trace_remove_ref(&table->table[i], eloop, table->table[i].eloop_data);
		wpa_trace_remove_ref(&table->table[i], user,  table->table[i].user_data);
	}
}

#else /* WPA_TRACE */

#define eloop_trace_sock_add_ref(table) do { } while (0)
#define eloop_trace_sock_remove_ref(table) do { } while (0)

#endif /* WPA_TRACE */



```


###### wpa_trace_record
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/trace.h#50

没有定义WPA_TRACE  那么函数  #define wpa_trace_record(ptr) do { } while (0) 就是空  

#ifdef WPA_TRACE

#define wpa_trace_record(ptr)  (ptr)->btrace_num = backtrace((ptr)->btrace, WPA_TRACE_LEN)

#else /* WPA_TRACE */

#define wpa_trace_record(ptr) do { } while (0)
#endif /* WPA_TRACE */

```


## wpa_supplicant* wpa_supplicant_add_iface( wpa_global,wpa_interface ,wpa_supplicant)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5673


 

/**
 * wpa_supplicant_add_iface - Add a new network interface
 * @global: Pointer to global data from wpa_supplicant_init()
 * @iface: Interface configuration options
 * @parent: Parent interface or %NULL to assign new interface as parent
 * Returns: Pointer to the created interface or %NULL on failure
 *
 * This function is used to add new network interfaces for %wpa_supplicant.
 * This can be called before wpa_supplicant_run() to add interfaces before the
 * main event loop has been started. In addition, new interfaces can be added
 * dynamically while %wpa_supplicant is already running. This could happen,
 * e.g., when a hotplug network adapter is inserted.
 */

```

## int wpa_supplicant_run( wpa_global *global)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#6047

/**
 * wpa_supplicant_run - Run the %wpa_supplicant main event loop
 * @global: Pointer to global data from wpa_supplicant_init()
 * Returns: 0 after successful event loop run, -1 on failure
 *
 * This function starts the main event loop and continues running as long as
 * there are any remaining events. In most cases, this function is running as
 * long as the %wpa_supplicant process in still in use.
 */


```

### wpa_supplicant_global_ctrl_iface_init()

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#1293

struct ctrl_iface_global_priv * wpa_supplicant_global_ctrl_iface_init(struct wpa_global *global)
{
	struct ctrl_iface_global_priv *priv;

	priv = os_zalloc(sizeof(*priv));
	if (priv == NULL)
		return NULL;
	dl_list_init(&priv->ctrl_dst);  // 初始化 双向链表
	dl_list_init(&priv->msg_queue);    // 初始化 双向链表
	priv->global = global;            // 建立引用关系
	priv->sock = -1;

	if (global->params.ctrl_interface == NULL)
		return priv;

	if (wpas_global_ctrl_iface_open_sock(global, priv) < 0) {     // 【1】
		os_free(priv);
		return NULL;
	}

	wpa_msg_register_cb(wpa_supplicant_ctrl_iface_msg_cb);  // 【2】

	return priv;
}




```
#### wpas_global_ctrl_iface_open_sock(global, priv) 


#### wpa_msg_register_cb(wpa_supplicant_ctrl_iface_msg_cb)



## void wpa_supplicant_deinit( wpa_global *global)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#6083


/**
 * wpa_supplicant_deinit - Deinitialize %wpa_supplicant
 * @global: Pointer to global data from wpa_supplicant_init()
 *
 * This function is called to deinitialize %wpa_supplicant and to free all
 * allocated resources. Remaining network interfaces will also be removed.
 */


```


## fst_global_deinit
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/fst/fst.c#115


```

```
void fst_global_deinit(void)
{
	struct fst_group *group;
	struct fst_ctrl_handle *h;

	if (!fst_global_initialized)
		return;

	fst_session_global_deinit();
	while ((group = fst_first_group()) != NULL)
		fst_group_delete(group);
	while ((h = dl_list_first(&fst_global_ctrls_list,struct fst_ctrl_handle,global_ctrls_lentry)))
		fst_global_del_ctrl(h);
	fst_global_initialized = 0;
}


```
#  WIFI数据结构


## wpa_supplicant(struct)

http://w1.fi/wpa_supplicant/devel/classes.html


### wpa_interface
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#53
http://w1.fi/wpa_supplicant/devel/structwpa__interface.html

/**  为函数wpa_supplicant_add_iface() 准备基础数据的 数据结构
 * struct wpa_interface - Parameters for wpa_supplicant_add_iface()
 */
struct wpa_interface {

	const char *confname;   // 配置文件路径名称    -c/data/vendor/wifi/wpa/wpa_supplicant.conf  -c 参数指明     
	const char *confanother;  // 备份的配置文件路径名称   -I参数指定   -I/system/etc/wifi/wpa_supplicant_overlay.conf 
	const char *ctrl_interface;  // 全局控制接口 -g参数指定  控制接口  .rc文件执行命令如果没有设置-g 参数 , 该值默认从配置文件读取
	const char *driver;           //  WPA适配的驱动程序类型 -D 参数指定    -Dnl80211  
	const char *driver_param;      // WPA 适配驱动程序键值对参数 -p参数指定 
	const char *ifname;                 //  	Interface name.网卡接口名称  -i 参数指定  -iwlan0
	const char *bridge_ifname;       //  -b 参数指定  桥接接口 主要用于接收EAP认证帧 -b
	int p2p_mgmt;                    // 指示是否必须为此接口调用 wpas_p2p_init()
};

```

### wpa_params
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#126
http://w1.fi/wpa_supplicant/devel/structwpa__params.html




/**
 * struct wpa_params - Parameters for wpa_supplicant_init()
 */
struct wpa_params {
int 	     daemonize                       // Run wpa_supplicant in the background.       开关用来设置 wpa_supplicant作为守护进程运行    -B 参数指定
int 	     wait_for_monitor               // Wait for a monitor program before starting.  开关控制   启动之前等待 监视程序运行 wpa才继续运行
char * 	     pid_file                       //Path to a PID (process ID) file. More...      WPA进程文件全路径名
int 	     wpa_debug_level                // Debugging verbosity level (e.g., MSG_INFO)   WPA的LOG等级  1-高  2-中  3-低
int 	     wpa_debug_show_keys            // Whether keying material is included in debug. Key配置信息是否打印开关  仅用于开发调试 用户版本必须关闭
int 	     wpa_debug_timestamp            // Whether to include timestamp in debug messages.  时间戳是否显示在Log中的 开关
char * 	     ctrl_interface                // Global ctrl_iface path/parameter.  全局控制接口 ( 单个 )   -g @android:vendor_wpa_wlan0   
char * 	     ctrl_interface_group          //	Global ctrl_iface group.   全局控制接口( 群组 )
int 	     dbus_ctrl_interface           // Enable the DBus control interface.   是否开启DBus 控制接口 开关
const char*  wpa_debug_file_path           // Path of debug file or NULL to use stdout.   -f 参数指定  Log文件全路径
int 	     wpa_debug_syslog              // Enable log output through syslog.   通过 syslog系统Log日志 来输出WPA的Log 开关
int 	     wpa_debug_tracing              // Enable log output through Linux tracing.  通过 Linux tracing 输出 WPA的LOG 开关
char *       override_driver                //Optional driver parameter override. More...  可选的driver驱动参数覆盖  key-value覆盖
char *       override_ctrl_interface      // Optional ctrl_interface override. More...  可选的 -O参数指定 ctrl_interface 控制接口参数覆盖   key-value覆盖 
char *       entropy_file                 // Optional entropy file. More... 用于生产随机数字的二进制执行程序全路径  用于加密  -e 参数指定 -e/data/misc/wifi/entropy.bin
char *       conf_p2p_dev                 //Configuration file P2P Device configuration parameters  WLAN P2P的配置文件全路径  -m 参数指定 -m/data/vendor/wifi/wigig_p2p_supplicant.conf 
}


```


### wpa_global
```
Internal, global data for all wpa_supplicant interfaces
内部的全局的所有数据  对于所有 wpa_supplicant 接口


http://w1.fi/wpa_supplicant/devel/structwpa__global.html
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#264

/**
 * struct wpa_global - Internal, global data for all %wpa_supplicant interfaces
 *
 * This structure is initialized by calling wpa_supplicant_init() when starting
 * %wpa_supplicant.
 */
struct wpa_global {
	struct wpa_supplicant *ifaces;
	struct wpa_params params;
	struct ctrl_iface_global_priv *ctrl_iface;
	struct wpas_dbus_priv *dbus;
	struct wpas_hidl_priv *hidl;
	void **drv_priv;
	size_t drv_count;
	struct os_time suspend_time;
	struct p2p_data *p2p;
	struct wpa_supplicant *p2p_init_wpa_s;
	struct wpa_supplicant *p2p_group_formation;
	struct wpa_supplicant *p2p_invite_group;
	u8 p2p_dev_addr[ETH_ALEN];
	struct os_reltime p2p_go_wait_client;
	struct dl_list p2p_srv_bonjour; /* struct p2p_srv_bonjour */
	struct dl_list p2p_srv_upnp; /* struct p2p_srv_upnp */
	int p2p_disabled;
	int cross_connection;
	struct wpa_freq_range_list p2p_disallow_freq;
	struct wpa_freq_range_list p2p_go_avoid_freq;
	enum wpa_conc_pref {
		WPA_CONC_PREF_NOT_SET,
		WPA_CONC_PREF_STA,
		WPA_CONC_PREF_P2P
	} conc_pref;
	unsigned int p2p_per_sta_psk:1;
	unsigned int p2p_fail_on_wps_complete:1;
	unsigned int p2p_24ghz_social_channels:1;
	unsigned int pending_p2ps_group:1;
	unsigned int pending_group_iface_for_p2ps:1;
	unsigned int pending_p2ps_group_freq;
	int wifi_display;
#define MAX_WFD_SUBELEMS 12
	struct wpabuf *wfd_subelem[MAX_WFD_SUBELEMS];
#endif 

	struct psk_list_entry *add_psk; /* From group formation */
};


```


<img src="//../zimage/wireless/wifi/09_supplicant/wpa_global.png" width = "50%" height="50%"/>

### wpa_supplicant
```
Internal data for wpa_supplicant interface
http://w1.fi/wpa_supplicant/devel/structwpa__supplicant.html




struct wpa_global * 	global
struct wpa_radio * 	radio
struct dl_list 	radio_list
struct wpa_supplicant * 	parent
struct wpa_supplicant * 	next
struct l2_packet_data * 	l2
struct l2_packet_data * 	l2_br
unsigned char 	own_addr [ETH_ALEN]
unsigned char 	perm_addr [ETH_ALEN]
char 	ifname [100]
char 	bridge_ifname [16]
char * 	confname
char * 	confanodther
struct wpa_config * 	conf
int 	countermeasures
struct os_reltime 	last_michael_mic_error
u8 	bssid [ETH_ALEN]
u8 	pending_bssid [ETH_ALEN]
int 	reassociate
int 	reassoc_same_bss
int 	disconnected
struct wpa_ssid * 	current_ssid
struct wpa_ssid * 	last_ssid
struct wpa_bss * 	current_bss
int 	ap_ies_from_associnfo
unsigned int 	assoc_freq
int 	pairwise_cipher
int 	group_cipher
int 	key_mgmt
int 	wpa_proto
int 	mgmt_group_cipher
void * 	drv_priv
void * 	global_drv_priv
u8 * 	bssid_filter
size_t 	bssid_filter_count
u8 * 	disallow_aps_bssid
size_t 	disallow_aps_bssid_count
struct wpa_ssid_value * 	disallow_aps_ssid
size_t 	disallow_aps_ssid_count
enum set_band 	setband
struct wpa_ssid * 	next_ssid
int 	prev_scan_wildcard
struct wpa_ssid * 	prev_scan_ssid
struct wpa_ssid * 	prev_sched_ssid
int 	sched_scan_timeout
int 	sched_scan_interval
int 	first_sched_scan
int 	sched_scan_timed_out
void(* 	scan_res_handler )(struct wpa_supplicant *wpa_s, struct wpa_scan_results *scan_res)
struct dl_list 	bss
struct dl_list 	bss_id
size_t 	num_bss
unsigned int 	bss_update_idx
unsigned int 	bss_next_id
struct wpa_bss ** 	last_scan_res
unsigned int 	last_scan_res_used
unsigned int 	last_scan_res_size
struct os_reltime 	last_scan
const struct wpa_driver_ops * 	driver
int 	interface_removed
struct wpa_sm * 	wpa
struct eapol_sm * 	eapol
struct ctrl_iface_priv * 	ctrl_iface
enum wpa_states 	wpa_state
struct wpa_radio_work * 	scan_work
int 	scanning
int 	sched_scanning
int 	new_connection
int 	eapol_received
struct scard_data * 	scard
char 	imsi [20]
int 	mnc_len
unsigned char 	last_eapol_src [ETH_ALEN]
unsigned int 	keys_cleared
struct wpa_blacklist * 	blacklist
int 	extra_blacklist_count
 	Sum of blacklist counts after last connection. More...
enum wpa_supplicant::scan_req_type 	scan_req
enum wpa_supplicant::scan_req_type 	last_scan_req
enum wpa_states 	scan_prev_wpa_state
struct os_reltime scan_trigger_time 	scan_start_time
struct os_reltime 	scan_min_time
int 	scan_runs
int * 	next_scan_freqs
int * 	manual_scan_freqs
int * 	manual_sched_scan_freqs
unsigned int 	manual_scan_passive:1
unsigned int 	manual_scan_use_id:1
unsigned int 	manual_scan_only_new:1
unsigned int 	own_scan_requested:1
unsigned int 	own_scan_running:1
unsigned int 	clear_driver_scan_cache:1
unsigned int 	manual_scan_id
int 	scan_interval
int 	normal_scans
int 	scan_for_connection
int 	scan_id [MAX_SCAN_ID]
unsigned int 	scan_id_count
struct wpa_ssid_value * 	ssids_from_scan_req
unsigned int 	num_ssids_from_scan_req
u64 	drv_flags
unsigned int 	drv_enc
unsigned int 	drv_smps_modes
unsigned int 	drv_rrm_flags
unsigned int 	probe_resp_offloads
const u8 * 	extended_capa
const u8 * 	extended_capa_mask
unsigned int 	extended_capa_len
int 	max_scan_ssids
int 	max_sched_scan_ssids
int 	sched_scan_supported
unsigned int 	max_match_sets
unsigned int 	max_remain_on_chan
unsigned int 	max_stations
int 	pending_mic_error_report
int 	pending_mic_error_pairwise
int 	mic_errors_seen
struct wps_context * 	wps
int 	wps_success
struct wps_er * 	wps_er
unsigned int 	wps_run
struct os_reltime 	wps_pin_start_time
int 	blacklist_cleared
struct wpabuf * 	pending_eapol_rx
struct os_reltime 	pending_eapol_rx_time
u8 	pending_eapol_rx_src [ETH_ALEN]
unsigned int 	last_eapol_matches_bssid:1
unsigned int 	eap_expected_failure:1
unsigned int 	reattach:1
unsigned int 	mac_addr_changed:1
unsigned int 	added_vif:1
struct os_reltime 	last_mac_addr_change
int 	last_mac_addr_style
struct ibss_rsn * 	ibss_rsn
int 	set_sta_uapsd
int 	sta_uapsd
int 	set_ap_uapsd
int 	ap_uapsd
struct hostapd_iface * 	ifmsh
unsigned int 	off_channel_freq
struct wpabuf * 	pending_action_tx
u8 	pending_action_src [ETH_ALEN]
u8 	pending_action_dst [ETH_ALEN]
u8 	pending_action_bssid [ETH_ALEN]
unsigned int 	pending_action_freq
int 	pending_action_no_cck
int 	pending_action_without_roc
unsigned int 	pending_action_tx_done:1
void(* 	pending_action_tx_status_cb )(struct wpa_supplicant *wpa_s, unsigned int freq, const u8 *dst, const u8 *src, const u8 *bssid, const u8 *data, size_t data_len, enum offchannel_send_action_result result)
unsigned int 	roc_waiting_drv_freq
int 	action_tx_wait_time
int 	p2p_mgmt
struct p2p_go_neg_results * 	go_params
int 	create_p2p_iface
u8 	pending_interface_addr [ETH_ALEN]
char 	pending_interface_name [100]
int 	pending_interface_type
int 	p2p_group_idx
unsigned int 	pending_listen_freq
unsigned int 	pending_listen_duration
enum wpa_supplicant:: { ... }  	p2p_group_interface
struct p2p_group * 	p2p_group
int 	p2p_long_listen
char 	p2p_pin [10]
int 	p2p_wps_method
u8 	p2p_auth_invite [ETH_ALEN]
int 	p2p_sd_over_ctrl_iface
int 	p2p_in_provisioning
int 	p2p_in_invitation
int 	p2p_invite_go_freq
int 	pending_invite_ssid_id
int 	show_group_started
u8 	go_dev_addr [ETH_ALEN]
int 	pending_pd_before_join
u8 	pending_join_iface_addr [ETH_ALEN]
u8 	pending_join_dev_addr [ETH_ALEN]
int 	pending_join_wps_method
u8 	p2p_join_ssid [SSID_MAX_LEN]
size_t 	p2p_join_ssid_len
int 	p2p_join_scan_count
int 	auto_pd_scan_retry
int 	force_long_sd
u16 	pending_pd_config_methods
enum wpa_supplicant:: { ... }  	pending_pd_use
int 	cross_connect_disallowed
int 	cross_connect_enabled
int 	cross_connect_in_use
char 	cross_connect_uplink [100]
unsigned int 	p2p_auto_join:1
unsigned int 	p2p_auto_pd:1
unsigned int 	p2p_persistent_group:1
unsigned int 	p2p_fallback_to_go_neg:1
unsigned int 	p2p_pd_before_go_neg:1
unsigned int 	p2p_go_ht40:1
unsigned int 	p2p_go_vht:1
unsigned int 	user_initiated_pd:1
unsigned int 	p2p_go_group_formation_completed:1
unsigned int 	group_formation_reported:1
unsigned int 	waiting_presence_resp
int 	p2p_first_connection_timeout
unsigned int 	p2p_nfc_tag_enabled:1
unsigned int 	p2p_peer_oob_pk_hash_known:1
unsigned int 	p2p_disable_ip_addr_req:1
unsigned int 	p2ps_method_config_any:1
unsigned int 	p2p_cli_probe:1
int 	p2p_persistent_go_freq
int 	p2p_persistent_id
int 	p2p_go_intent
int 	p2p_connect_freq
struct os_reltime 	p2p_auto_started
struct wpa_ssid * 	p2p_last_4way_hs_fail
struct wpa_radio_work * 	p2p_scan_work
struct wpa_radio_work * 	p2p_listen_work
struct wpa_radio_work * 	p2p_send_action_work
u16 	p2p_oob_dev_pw_id
struct wpabuf * 	p2p_oob_dev_pw
u8 	p2p_peer_oob_pubkey_hash [WPS_OOB_PUBKEY_HASH_LEN]
u8 	p2p_ip_addr_info [3 *4]
int * 	p2p_group_common_freqs
unsigned int 	p2p_group_common_freqs_num
u8 	p2ps_join_addr [ETH_ALEN]
struct wpa_ssid * 	bgscan_ssid
const struct bgscan_ops * 	bgscan
void * 	bgscan_priv
const struct autoscan_ops * 	autoscan
struct wpa_driver_scan_params * 	autoscan_params
void * 	autoscan_priv
struct wpa_ssid * 	connect_without_scan
struct wps_ap_info * 	wps_ap
size_t 	num_wps_ap
int 	wps_ap_iter
int 	after_wps
int 	known_wps_freq
unsigned int 	wps_freq
int 	wps_fragment_size
int 	auto_reconnect_disabled
int 	best_24_freq
int 	best_5_freq
int 	best_overall_freq
struct gas_query * 	gas
unsigned int 	drv_capa_known
struct {
   struct hostapd_hw_modes *   modes
   u16   num_modes
   u16   flags
} 	hw
enum wpa_supplicant::local_hw_capab 	hw_capab
int 	pno
int 	pno_sched_pending
int 	disconnect_reason
struct ext_password_data * 	ext_pw
struct wpabuf * 	last_gas_resp
struct wpabuf * 	prev_gas_resp
u8 	last_gas_addr [ETH_ALEN]
u8 	prev_gas_addr [ETH_ALEN]
u8 	last_gas_dialog_token
u8 	prev_gas_dialog_token
unsigned int 	no_keep_alive:1
unsigned int 	ext_mgmt_frame_handling:1
unsigned int 	ext_eapol_frame_io:1
unsigned int 	wmm_ac_supported:1
unsigned int 	ext_work_in_progress:1
unsigned int 	own_disconnect_req:1
unsigned int 	mac_addr_rand_supported
unsigned int 	mac_addr_rand_enable
u8 * 	mac_addr_scan
u8 * 	mac_addr_sched_scan
u8 * 	mac_addr_pno
unsigned int 	num_multichan_concurrent
struct wpa_radio_work * 	connect_work
unsigned int 	ext_work_id
struct wpabuf * 	vendor_elem [NUM_VENDOR_ELEM_FRAMES]
struct wmm_ac_assoc_data * 	wmm_ac_assoc_info
struct wmm_tspec_element * 	tspecs [WMM_AC_NUM][TS_DIR_IDX_COUNT]
struct wmm_ac_addts_request * 	addts_request
u8 	wmm_ac_last_dialog_token
struct wmm_tspec_element * 	last_tspecs
u8 	last_tspecs_count
struct rrm_data 	rrm

```

<img src="//../zimage/wireless/wifi/09_supplicant/wpa_supplicant.png" width = "50%" height="50%"/>






### wpa_driver_ops

```
Driver interface API definition
与驱动交互的API接口
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/drivers/driver.h#2061
http://w1.fi/wpa_supplicant/devel/structwpa__driver__ops.html



const char * 	name    // Name of the driver interface.
const char * 	desc    // One line description of the driver interface.
int(* 	get_bssid )(void *priv, u8 *bssid)  //Get the current BSSID. More...
int(* 	get_ssid )(void *priv, u8 *ssid)  // Get the current SSID. More...

// Configure encryption key. More...
int(* 	set_key )(const char *ifname, void *priv, enum wpa_alg alg, const u8 *addr, int key_idx, int set_tx, const u8 *seq, size_t seq_len, const u8 *key, size_t key_len)

// Initialize driver interface. More...
void *(* 	init )(void *ctx, const char *ifname)

void(* 	deinit )(void *priv)  // Deinitialize driver interface. More...

int(* 	set_param )(void *priv, const char *param)  // Set driver configuration parameters. More...

int(* 	set_countermeasures )(void *priv, int enabled)  // Enable/disable TKIP countermeasures. More...

int(* 	deauthenticate )(void *priv, const u8 *addr, int reason_code)  // Request driver to deauthenticate. More...

int(* 	associate )(void *priv, struct wpa_driver_associate_params *params)  //Request driver to associate. More...

int(* 	add_pmkid )(void *priv, const u8 *bssid, const u8 *pmkid)  // Add PMKSA cache entry to the driver. More...

int(* 	remove_pmkid )(void *priv, const u8 *bssid, const u8 *pmkid)  // Remove PMKSA cache entry to the driver. More...

int(* 	flush_pmkid )(void *priv)  // Flush PMKSA cache. More...

int(* 	get_capa )(void *priv, struct wpa_driver_capa *capa)  // Get driver capabilities. More...

void(* 	poll )(void *priv)   // Poll driver for association information. More...

const char *(* 	get_ifname )(void *priv)  // Get interface name. More...

const u8 *(* 	get_mac_addr )(void *priv)   // Get own MAC address. More...

int(* 	set_operstate )(void *priv, int state)   // Sets device operating state to DORMANT or UP. More...

//MLME-SETPROTECTION.request primitive. More...
int(* 	mlme_setprotection )(void *priv, const u8 *addr, int protect_type, int key_type)

//Get hardware support data (channels and rates) More...
struct hostapd_hw_modes *(* 	get_hw_feature_data )(void *priv, u16 *num_modes, u16 *flags)

//Send management frame from MLME. More...
int(* 	send_mlme )(void *priv, const u8 *data, size_t data_len, int noack, unsigned int freq)

//Update FT (IEEE 802.11r) IEs. More...
int(* 	update_ft_ies )(void *priv, const u8 *md, const u8 *ies, size_t ies_len)

//Fetch the latest scan results. More...
struct wpa_scan_results *(* 	get_scan_results2 )(void *priv)


// Set country. More...
int(* 	set_country )(void *priv, const char *alpha2)

// Get country. More...
int(* 	get_country )(void *priv, char *alpha2)


// Global driver initialization. More...
void *(* 	global_init )(void)

// Global driver deinitialization. More...
void(* 	global_deinit )(void *priv)

//Initialize driver interface (with global data) More...
void *(* 	init2 )(void *ctx, const char *ifname, void *global_priv)

//Get information about available interfaces. More...
struct wpa_interface_info *(* 	get_interfaces )(void *global_priv)

//Request the driver to initiate scan. More...
int(* 	scan2 )(void *priv, struct wpa_driver_scan_params *params)

//Request driver to authenticate. More...
int(* 	authenticate )(void *priv, struct wpa_driver_auth_params *params)

//Set Beacon and Probe Response information for AP mode. More...
int(* 	set_ap )(void *priv, struct wpa_driver_ap_params *params)

//Set ACL in AP mode. More...
int(* 	set_acl )(void *priv, struct hostapd_acl_params *params)

//Initialize driver interface (hostapd only) More...
void *(* 	hapd_init )(struct hostapd_data *hapd, struct wpa_init_params *params)

// Deinitialize driver interface (hostapd only) More...
void(* 	hapd_deinit )(void *priv)

// Enable/disable IEEE 802.1X support (AP only) More...
int(* 	set_ieee8021x )(void *priv, struct wpa_bss_params *params)

// Enable/disable privacy (AP only) More...
int(* 	set_privacy )(void *priv, int enabled)

// Fetch the current TSC/packet number (AP only) More...
int(* 	get_seqnum )(const char *ifname, void *priv, const u8 *addr, int idx, u8 *seq)

// Flush all association stations (AP only) More...
int(* 	flush )(void *priv)

// Add IEs into Beacon/Probe Response frames (AP) More...
int(* 	set_generic_elem )(void *priv, const u8 *elem, size_t elem_len)

// Fetch station data. More...
int(* 	read_sta_data )(void *priv, struct hostap_sta_driver_data *data, const u8 *addr)

// Send an EAPOL packet (AP only) More...
int(* 	hapd_send_eapol )(void *priv, const u8 *addr, const u8 *data, size_t data_len, int encrypt, const u8 *own_addr, u32 flags)

//Deauthenticate a station (AP only) More...
int(* 	sta_deauth )(void *priv, const u8 *own_addr, const u8 *addr, int reason)

// Disassociate a station (AP only) More...
int(* 	sta_disassoc )(void *priv, const u8 *own_addr, const u8 *addr, int reason)

//Remove a station entry (AP only) More...
int(* 	sta_remove )(void *priv, const u8 *addr)

// Get the current SSID (AP only) More...
int(* 	hapd_get_ssid )(void *priv, u8 *buf, int len)

// Set SSID (AP only) More...
int(* 	hapd_set_ssid )(void *priv, const u8 *buf, int len)

// Enable/disable TKIP countermeasures (AP) More...
int(* 	hapd_set_countermeasures )(void *priv, int enabled)

//Add a station entry. More...
int(* 	sta_add )(void *priv, struct hostapd_sta_add_params *params)

// Get station inactivity duration (AP only) More...
int(* 	get_inact_sec )(void *priv, const u8 *addr)

// Clear station statistics (AP only) More...
int(* 	sta_clear_stats )(void *priv, const u8 *addr)

//Set channel/frequency (AP only) More...
int(* 	set_freq )(void *priv, struct hostapd_freq_params *freq)

// Set RTS threshold. More...
int(* 	set_rts )(void *priv, int rts)

//Set fragmentation threshold. More...
int(* 	set_frag )(void *priv, int frag)

// Set station flags (AP only) More...
int(* 	sta_set_flags )(void *priv, const u8 *addr, unsigned int total_flags, unsigned int flags_or, unsigned int flags_and)

//Set TX queue parameters. More...
int(* 	set_tx_queue_params )(void *priv, int queue, int aifs, int cw_min, int cw_max, int burst_time)


// Add a virtual interface. More...
int(* 	if_add )(void *priv, enum wpa_driver_if_type type, const char *ifname, const u8 *addr, void *bss_ctx, void **drv_priv, char *force_ifname, u8 *if_addr, const char *bridge, int use_existing)

// Remove a virtual interface. More...
int(* 	if_remove )(void *priv, enum wpa_driver_if_type type, const char *ifname)

// Bind a station into a specific interface (AP only) More...
int(* 	set_sta_vlan )(void *priv, const u8 *addr, const char *ifname, int vlan_id)

// Optional commit changes handler (AP only) More...
int(* 	commit )(void *priv)

//Send an ethernet packet (AP only) More...
int(* 	send_ether )(void *priv, const u8 *dst, const u8 *src, u16 proto, const u8 *data, size_t data_len)

//Notification of RADIUS ACL change. More...
int(* 	set_radius_acl_auth )(void *priv, const u8 *mac, int accepted, u32 session_timeout)

// Notification of RADIUS ACL expiration. More...
int(* 	set_radius_acl_expire )(void *priv, const u8 *mac)

// Add WPS IE(s) into Beacon/Probe Response frames (AP) More...
int(* 	set_ap_wps_ie )(void *priv, const struct wpabuf *beacon, const struct wpabuf *proberesp, const struct wpabuf *assocresp)

// Set IEEE 802.1X Supplicant Port status. More...
int(* 	set_supp_port )(void *priv, int authorized)

// Bind a station into a 4-address WDS (AP only) More...
int(* 	set_wds_sta )(void *priv, const u8 *addr, int aid, int val, const char *bridge_ifname, char *ifname_wds)

// Transmit an Action frame. More...
int(* 	send_action )(void *priv, unsigned int freq, unsigned int wait, const u8 *dst, const u8 *src, const u8 *bssid, const u8 *data, size_t data_len, int no_cck)

// Cancel action frame TX wait. More...
void(* 	send_action_cancel_wait )(void *priv)

//Remain awake on a channel. More...
int(* 	remain_on_channel )(void *priv, unsigned int freq, unsigned int duration)

// Cancel remain-on-channel operation. More...
int(* 	cancel_remain_on_channel )(void *priv)

// Request Probe Request frames to be indicated. More...
int(* 	probe_req_report )(void *priv, int report)

// Deinitialize AP mode. More...
int(* 	deinit_ap )(void *priv)

// Deinitialize P2P client mode. More...
int(* 	deinit_p2p_cli )(void *priv)

//Notification on system suspend/hibernate event. More...
void(* 	suspend )(void *priv)

// Notification on system resume/thaw event. More...
void(* 	resume )(void *priv)

// Set signal monitoring parameters. More...
int(* 	signal_monitor )(void *priv, int threshold, int hysteresis)

// Send IEEE 802.11 frame (testing use only) More...
int(* 	send_frame )(void *priv, const u8 *data, size_t data_len, int encrypt)

// Get current Notice of Absence attribute payload. More...
int(* 	get_noa )(void *priv, u8 *buf, size_t buf_len)


// Set Notice of Absence parameters for GO (testing) More...
int(* 	set_noa )(void *priv, u8 count, int start, int duration)

//Set P2P power save options. More...
int(* 	set_p2p_powersave )(void *priv, int legacy_ps, int opp_ps, int ctwindow)

// Enable/disable aggregation. More...
int(* 	ampdu )(void *priv, int ampdu)

//Get physical radio name for the device. More...
const char *(* 	get_radio_name )(void *priv)

//for sending TDLS management packets More...
int(* 	send_tdls_mgmt )(void *priv, const u8 *dst, u8 action_code, u8 dialog_token, u16 status_code, u32 peer_capab, int initiator, const u8 *buf, size_t len)

// Ask the driver to perform high-level TDLS operations. More...
int(* 	tdls_oper )(void *priv, enum tdls_oper oper, const u8 *peer)

// Notify driver of the WNM frame reception. More...
int(* 	wnm_oper )(void *priv, enum wnm_oper oper, const u8 *peer, u8 *buf, u16 *buf_len)

// Set QoS Map. More...
int(* 	set_qos_map )(void *priv, const u8 *qos_map_set, u8 qos_map_set_len)

// Add a neigh to the bridge ip neigh table. More...
int(* 	br_add_ip_neigh )(void *priv, u8 version, const u8 *ipaddr, int prefixlen, const u8 *addr)

// Remove a neigh from the bridge ip neigh table. More...
int(* 	br_delete_ip_neigh )(void *priv, u8 version, const u8 *ipaddr)


// Set a bridge port attribute. More...
int(* 	br_port_set_attr )(void *priv, enum drv_br_port_attr attr, unsigned int val)

// Set a bridge network parameter. More...
int(* 	br_set_net_param )(void *priv, enum drv_br_net_param param, unsigned int val)

// Set wake-on-wireless triggers. More...
int(* 	set_wowlan )(void *priv, const struct wowlan_triggers *triggers)

//Get current connection information. More...
int(* 	signal_poll )(void *priv, struct wpa_signal_info *signal_info)

// Set authentication algorithm(s) for static WEP. More...
int(* 	set_authmode )(void *priv, int authmode)

// Execute vendor specific command. More...
int(* 	vendor_cmd )(void *priv, unsigned int vendor_id, unsigned int subcmd, const u8 *data, size_t data_len, struct wpabuf *buf)

// Set rekey information. More...
void(* 	set_rekey_info )(void *priv, const u8 *kek, size_t kek_len, const u8 *kck, size_t kck_len, const u8 *replay_ctr)

// Station association indication. More...
int(* 	sta_assoc )(void *priv, const u8 *own_addr, const u8 *addr, int reassoc, u16 status, const u8 *ie, size_t len)

// Station authentication indication. More...
int(* 	sta_auth )(void *priv, const u8 *own_addr, const u8 *addr, u16 seq, u16 status, const u8 *ie, size_t len)

// Add traffic stream. More...
int(* 	add_tspec )(void *priv, const u8 *addr, u8 *tspec_ie, size_t tspec_ielen)

// Add a station node in the driver. More...
int(* 	add_sta_node )(void *priv, const u8 *addr, u16 auth_alg)


// Request the driver to initiate scheduled scan. More...
int(* 	sched_scan )(void *priv, struct wpa_driver_scan_params *params, u32 interval)

// Request the driver to stop a scheduled scan. More...
int(* 	stop_sched_scan )(void *priv)

// Probe (null data or such) the given station. More...
void(* 	poll_client )(void *priv, const u8 *own_addr, const u8 *addr, int qos)

// Disable/enable radio. More...
int(* 	radio_disable )(void *priv, int disabled)

// Announce channel switch and migrate the GO to the given frequency. More...
int(* 	switch_channel )(void *priv, struct csa_settings *settings)

// Add traffic stream. More...
int(* 	add_tx_ts )(void *priv, u8 tsid, const u8 *addr, u8 user_prio, u16 admitted_time)

// Delete traffic stream. More...
int(* 	del_tx_ts )(void *priv, u8 tsid, const u8 *addr)

// Enable channel-switching with TDLS peer. More...
int(* 	tdls_enable_channel_switch )(void *priv, const u8 *addr, u8 oper_class, const struct hostapd_freq_params *params)

//Disable channel switching with TDLS peer. More...
int(* 	tdls_disable_channel_switch )(void *priv, const u8 *addr)

// Listen for radar interference on the channel. More...
int(* 	start_dfs_cac )(void *priv, struct hostapd_freq_params *freq)

// Removes beacon from AP. More...
int(* 	stop_ap )(void *priv)

// Retrieve survey data. More...
int(* 	get_survey )(void *priv, unsigned int freq)

// Get driver interface status information. More...
int(* 	status )(void *priv, char *buf, size_t buflen)

// Set roaming policy for driver-based BSS selection. More...
int(* 	roaming )(void *priv, int allowed, const u8 *bssid)

// Set MAC address. More...
int(* 	set_mac_addr )(void *priv, const u8 *addr)

// Driver specific initialization for mesh. More...
int(* 	init_mesh )(void *priv)

// Join a mesh network. More...
int(* 	join_mesh )(void *priv, struct wpa_driver_mesh_join_params *params)

// Leave a mesh network. More...
int(* 	leave_mesh )(void *priv)

// Automatically select channel. More...
int(* 	do_acs )(void *priv, struct drv_acs_params *params)

//Notify driver of band selection. More...
int(* 	set_band )(void *priv, enum set_band band)

//Get preferred frequency list for an interface. More...
int(* 	get_pref_freq_list )(void *priv, enum wpa_driver_if_type if_type, unsigned int *num, unsigned int *freq_list)

//Indicate probable P2P operating channel. More...
int(* 	set_prob_oper_freq )(void *priv, unsigned int freq)



```
<img src="//../zimage/wireless/wifi/09_supplicant/wpa_driver_ops.png" width = "50%" height="50%"/>



### eap_method 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/eap_peer/eap_i.h#66
http://w1.fi/wpa_supplicant/devel/structeap__method.html

/**
 * struct eap_method - EAP method interface
 * This structure defines the EAP method interface. Each method will need to
 * register its own EAP type, EAP name, and set of function pointers for method
 * specific operations. This interface is based on section 4.4 of RFC 4137.
 */

struct eap_method  {
	int vendor;
	EapType method;
	const char *name;
	void * (*init)(struct eap_sm *sm);
	void (*deinit)(struct eap_sm *sm, void *priv);
	struct wpabuf * (*process)(struct eap_sm *sm, void *priv,struct eap_method_ret *ret,const struct wpabuf *reqData);
	Boolean (*isKeyAvailable)(struct eap_sm *sm, void *priv);
	u8 * (*getKey)(struct eap_sm *sm, void *priv, size_t *len);
	int (*get_status)(struct eap_sm *sm, void *priv, char *buf,size_t buflen, int verbose);
	Boolean (*has_reauth_data)(struct eap_sm *sm, void *priv);
	void (*deinit_for_reauth)(struct eap_sm *sm, void *priv);
	void * (*init_for_reauth)(struct eap_sm *sm, void *priv);
	const u8 * (*get_identity)(struct eap_sm *sm, void *priv, size_t *len);
	int (*get_error_code)(void *priv);
	void (*free)(struct eap_method *method);
	int version;
	struct eap_method *next;
	void *dl_handle;
	u8 * (*get_emsk)(struct eap_sm *sm, void *priv, size_t *len);
	u8 * (*getSessionId)(struct eap_sm *sm, void *priv, size_t *len);
};


```

```
#graphviz 绘图代码

from graphviz import Digraph

s = Digraph('structs', filename='structs_revisited.gv', node_attr={'shape': 'record'})
s.attr(rankdir='LR')
s.node('struct_md5', "{{ EAP-Name=EAP_MD5 |void * XmethodX |<md5here> *eap_method next}}")
s.node('struct_tls', "{{ EAP-Name=EAP_TLS |void * XmethodX |<tlshere> *eap_method next}}")
s.node('struct_peap', "{{ EAP-Name=EAP_PEAP |void * XmethodX |<peaphere> *eap_method next}}")
s.node('struct_null', "NULL")
s.edges([('struct_md5:md5here', 'struct_tls')])
s.edges([('struct_tls:tlshere', 'struct_peap')])
s.edges([('struct_peap:peaphere', 'struct_null')])
s.view()
print(s.source)


```

<img src="//../zimage/wireless/wifi/09_supplicant/eap_method.jpg"/>

### dl_list
```
struct dl_list {
    struct dl_list *next, *prev;
};


```



### eloop_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#161


struct eloop_data {
	int max_sock;
	int count; /* sum of all table counts */

	struct eloop_sock_table readers;
	struct eloop_sock_table writers;
	struct eloop_sock_table exceptions;
	struct eloop_signal *signals;
	struct dl_list timeout;

	int signal_count;

	int signaled;
	int pending_terminate;

	int terminate;
};




```

<img src="//../zimage/wireless/wifi/09_supplicant/eloop_data.png" width = "50%" height="50%"/>
```
# eloop_data 数据结构绘图

from graphviz import Digraph

s = Digraph('structs', filename='structs_revisited.gv', node_attr={'shape': 'record'} )
s.attr(rankdir='LR')

s.node('eloop_data2', "{{ eloop_data2 | <data2_reader>eloop_sock_table reader "
                      "|<data2_writer>eloop_sock_table writer "
                      "|<data2_exception> eloop_sock_table exception "
                      "|<data2_signal> eloop_signal signals"
                      "|<data2_dllist> timeout dl_list  }}")

s.node('eloop_data0', "{{ eloop_data0 | <data0_reader>eloop_sock_table reader "
                      "|<data0_writer>eloop_sock_table writer "
                      "|<data0_exception> eloop_sock_table exception "
                      "|<data0_signal> eloop_signal signals"
                      "|<data0_dllist> timeout dl_list }}")

s.node('eloop_data1', "{{ eloop_data1 | <data1_reader>eloop_sock_table reader "
                      "|<data1_writer>eloop_sock_table writer "
                      "|<data1_exception> eloop_sock_table exception "
                      "|<data1_signal> eloop_signal signals"
                      "| {<data1_dllist_left> timeout dl_list_pre | <data1_dllist_right> timeout dl_list_next} }}")


s.node('eloop_sock_table_reader', "{{ eloop_sock_table_reader | int count "
                      "|eloop_event_type#READ#WRITE#EXCEPTION# "
                      "|<eloop_sock_reader>eloop_sock   }}")

s.node('eloop_sock_table_writer', "{{ eloop_sock_table_writer | int count "
                      "|eloop_event_type#READ#WRITE#EXCEPTION# "
                      "|<eloop_sock_writer>eloop_sock   }}")

s.node('eloop_sock_table_exception', "{{ eloop_sock_table_exception | int count "
                      "|eloop_event_type#READ#WRITE#EXCEPTION# "
                      "|<eloop_sock_exception>eloop_sock   }}")


s.node('eloop_signal', "{{ eloop_signal | int sig 信号量 "
                      "|void *user_data 用户数据 "
                      "|void* eloop_signal_handler 处理函数 }}")

s.node('eloop_sock_reader', "{{ eloop_sock_reader | int sock 句柄 "
                      "|void *eloop_data 数据 "
                      "|void *user_data 数据 "
                      "|void * eloop_sock_handler reader处理函数指针  }}")

s.node('eloop_sock_writer', "{{ eloop_sock_writer | int sock 句柄 "
                      "|void *eloop_data 数据 "
                      "|void *user_data 数据 "
                      "|void * eloop_sock_handler writer处理函数指针  }}")


s.node('eloop_sock_exception', "{{ eloop_sock_exception | int sock 句柄 "
                      "|void *eloop_data 数据 "
                      "|void *user_data 数据 "
                      "|void * eloop_sock_handler exception处理函数指针  }}")


s.edges([('eloop_data1:data1_dllist_left', 'eloop_data0')])
s.edges([('eloop_data1:data1_dllist_right', 'eloop_data2')])
s.edges([('eloop_data2:data2_reader', 'eloop_sock_table_reader')])
s.edges([('eloop_data2:data2_writer', 'eloop_sock_table_writer')])
s.edges([('eloop_data2:data2_exception', 'eloop_sock_table_exception')])
s.edges([('eloop_data2:data2_signal', 'eloop_signal')])

s.edges([('eloop_sock_table_reader:eloop_sock_reader', 'eloop_sock_reader')])
s.edges([('eloop_sock_table_writer:eloop_sock_writer', 'eloop_sock_writer')])
s.edges([('eloop_sock_table_exception:eloop_sock_exception', 'eloop_sock_exception')])
s.view()
print(s.source)


```





#### eloop_sock_table

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#70

struct eloop_sock_table {
	int count;
	struct eloop_sock *table;
	eloop_event_type type;   // 事件类型
	int changed;
};

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.h#31
typedef enum {
	EVENT_TYPE_READ = 0,    // 读 
	EVENT_TYPE_WRITE,       // 写
	EVENT_TYPE_EXCEPTION    // 异常
} eloop_event_type;



```


#### eloop_sock
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#42
struct eloop_sock {
	int sock;
	void *eloop_data;
	void *user_data;
	eloop_sock_handler handler;  // 函数指针
	WPA_TRACE_REF(eloop);
	WPA_TRACE_REF(user);
	WPA_TRACE_INFO
};

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.h#43
typedef void (*eloop_sock_handler)(int sock, void *eloop_ctx, void *sock_ctx);

```

#### eloop_signal
```

struct eloop_signal {
	int sig;
	void *user_data;
	eloop_signal_handler handler;   // 函数指针
	int signaled;
};

```



### ctrl_iface_global_priv

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#ctrl_iface_global_priv

struct ctrl_iface_global_priv {
	struct wpa_global *global;
	int sock;
	struct dl_list ctrl_dst;
	int android_control_socket;
	struct dl_list msg_queue;
	unsigned int throttle_count;
};



```


<img src="//../zimage/wireless/wifi/09_supplicant/ctrl_iface_global_priv.jpg" width = "50%" height="50%"/>