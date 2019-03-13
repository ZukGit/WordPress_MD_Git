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


	eloop_register_timeout(WPA_SUPPLICANT_CLEANUP_INTERVAL===10, 0, wpas_periodic, global, NULL);  【10】

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
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#1111

详情见长函数分析

```


#### wpa_msg_register_cb(wpa_supplicant_ctrl_iface_msg_cb)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpa_debug.c#593    调试debug的回复?

wpa_msg_register_cb(wpa_supplicant_ctrl_iface_msg_cb);   【1】


/**
 * wpa_msg_register_cb - Register callback function for wpa_msg() messages
 * @func: Callback function (%NULL to unregister)
 */

typedef void (*wpa_msg_cb_func)(void *ctx, int level, enum wpa_msg_type type,const char *txt, size_t len);

static wpa_msg_cb_func wpa_msg_cb = NULL;

void wpa_msg_register_cb(wpa_msg_cb_func func)
{
	wpa_msg_cb = func;
}


void wpa_msg(void *ctx, int level, const char *fmt, ...)
{
......
wpa_msg_cb(ctx, level, WPA_MSG_PER_INTERFACE, buf, len);
......
}


void wpa_msg_global_ctrl(void *ctx, int level, const char *fmt, ...)
{.......
	wpa_msg_cb(ctx, level, WPA_MSG_GLOBAL, buf, len);
	bin_clear_free(buf, buflen);
}


```

##### wpa_supplicant_ctrl_iface_msg_cb & wpa_msg_cb & wpa_msg_cb_func 消息回调

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#415


static void wpa_supplicant_ctrl_iface_msg_cb(void *ctx, int level, enum wpa_msg_type type,const char *txt, size_t len)
{
	struct wpa_supplicant *wpa_s = ctx;
	struct ctrl_iface_priv *priv;
	struct ctrl_iface_global_priv *gpriv;

	if (wpa_s == NULL)
		return;

	gpriv = wpa_s->global->ctrl_iface;

	if (type != WPA_MSG_NO_GLOBAL && gpriv && !dl_list_empty(&gpriv->ctrl_dst)) {
		if (!dl_list_empty(&gpriv->msg_queue) ||  wpas_ctrl_iface_throttle(gpriv->sock) 【1】) {
			if (gpriv->throttle_count == 0) {
				wpa_printf(MSG_MSGDUMP, "CTRL: Had to throttle global event message for sock %d", gpriv->sock);
			}
			gpriv->throttle_count++;
			wpas_ctrl_msg_queue_limit(gpriv->throttle_count,&gpriv->msg_queue); 【2】
			wpas_ctrl_msg_queue(&gpriv->msg_queue, wpa_s, level,type, txt, len);  【3】
		} else {
			if (gpriv->throttle_count) {
				wpa_printf(MSG_MSGDUMP,"CTRL: Had to throttle %u global event message(s) for sock %d",gpriv->throttle_count, gpriv->sock);
			}
			gpriv->throttle_count = 0;
			wpa_supplicant_ctrl_iface_send(wpa_s,type != WPA_MSG_PER_INTERFACE ?NULL : wpa_s->ifname,gpriv->sock, &gpriv->ctrl_dst, level,txt, len, NULL, gpriv); 【4】
		}
	}

	priv = wpa_s->ctrl_iface;

	if (type != WPA_MSG_ONLY_GLOBAL && priv) {
		if (!dl_list_empty(&priv->msg_queue) || wpas_ctrl_iface_throttle(priv->sock)) {
			if (priv->throttle_count == 0) {
				wpa_printf(MSG_MSGDUMP, "CTRL: Had to throttle event message for sock %d",  priv->sock);
			}
			priv->throttle_count++;
			wpas_ctrl_msg_queue_limit(priv->throttle_count,  &priv->msg_queue);
			wpas_ctrl_msg_queue(&priv->msg_queue, wpa_s, level, type, txt, len);
		} else {
			if (priv->throttle_count) {
				wpa_printf(MSG_MSGDUMP,"CTRL: Had to throttle %u event message(s) for sock %d", priv->throttle_count, priv->sock);
			}
			priv->throttle_count = 0;
			wpa_supplicant_ctrl_iface_send(wpa_s, NULL, priv->sock,&priv->ctrl_dst, level, txt, len, priv, NULL);
		}
	}
}


```

###### wpas_ctrl_iface_throttle(gpriv->sock)
###### wpas_ctrl_msg_queue_limit
###### wpas_ctrl_msg_queue
###### wpa_supplicant_ctrl_iface_send



### wpas_notify_supplicant_initialized
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/notify.c#28



int wpas_notify_supplicant_initialized(struct wpa_global *global)
{

	global->hidl = wpas_hidl_init(global); 【1】

	if (!global->hidl)
		return -1;

	return 0;
}

```

#### wpas_hidl_init(global)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl.cpp#34

struct wpas_hidl_priv *wpas_hidl_init(struct wpa_global *global)
{
	struct wpas_hidl_priv *priv;
	HidlManager *hidl_manager;

	priv = (wpas_hidl_priv *)os_zalloc(sizeof(*priv));
	if (!priv)
		return NULL;
	priv->global = global;

	wpa_printf(MSG_DEBUG, "Initing hidl control");

	configureRpcThreadpool(1, true /* callerWillJoin */);  【1】  设置进程最大线程数 1
	priv->hidl_fd = setupTransportPolling();                 【2】 从 HwServiceManager 进程得到的一个句柄
	if (priv->hidl_fd < 0)
		goto err;

	wpa_printf(MSG_INFO, "Processing hidl events on FD %d", priv->hidl_fd);
	// Look for read events from the hidl socket in the eloop.

	if (eloop_register_read_sock(priv->hidl_fd, wpas_hidl_sock_handler, global, priv) < 0)  【3】
		goto err;

	hidl_manager = HidlManager::getInstance();  【4】 获得系统 hidl_manager的C语言本地代理引用
	if (!hidl_manager)
		goto err;
	hidl_manager->registerHidlService(global);  【5】

	// We may not need to store this hidl manager reference in the
	// global data strucure because we've made it a singleton class.
	priv->hidl_manager = (void *)hidl_manager;    //  把 hidl_manager保存起来 hidl_manager 初始化完成

	return priv;
err:
	wpas_hidl_deinit(priv);  【6】
	return NULL;
}


数据结构
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl_i.h#17
struct wpas_hidl_priv
{
	int hidl_fd;       // 句柄
	struct wpa_global *global;    // 全局数据
	void *hidl_manager;     // 无类型指针
};


```

##### configureRpcThreadpool(1, true)
```
http://androidxref.com/9.0.0_r3/xref/system/libhidl/transport/HidlTransportSupport.cpp#24

 //  hidl service启动时要设置binder的线程池：    configureRpcThreadpool(1, true);
// roc->max_threads 设置的时候有的是1 有的是10不等，具体这个值要设置多少呢？



void configureRpcThreadpool(size_t maxThreads, bool callerWillJoin) {
    // TODO(b/32756130) this should be transport-dependent
    configureBinderRpcThreadpool(maxThreads, callerWillJoin);
}


http://androidxref.com/9.0.0_r3/xref/system/libhidl/transport/HidlBinderSupport.cpp#199
void configureBinderRpcThreadpool(size_t maxThreads, bool callerWillJoin) {
    ProcessState::self()->setThreadPoolConfiguration(maxThreads, callerWillJoin /*callerJoinsPool*/);
}


http://androidxref.com/9.0.0_r3/xref/system/libhwbinder/ProcessState.cpp#319

https://blog.csdn.net/wzoxylwzoxyl/article/details/81872974   【 HwServiceManager 进程启动 】
   size_t              mMaxThreads;  // Maximum number for binder threads allowed for this process. 最大线程数在一个进程中
   bool                mSpawnThreadOnStart;    // true:标识当前创建的线程的子线程为一个新的统计线程   false:标识当前创建的线程不标记为新线程而是线程池线程       这里为 false




status_t ProcessState::setThreadPoolConfiguration(size_t maxThreads, bool callerJoinsPool) {
    LOG_ALWAYS_FATAL_IF(maxThreads < 1, "Binder threadpool must have a minimum of one thread.");
    status_t result = NO_ERROR;

    // the BINDER_SET_MAX_THREADS ioctl really tells the kernel how many threads
    // it's allowed to spawn, *in addition* to any threads we may have already
    // spawned locally. If 'callerJoinsPool' is true, it means that the caller
    // will join the threadpool, and so the kernel needs to create one less thread.
    // If 'callerJoinsPool' is false, we will still spawn a thread locally, and we should
    // also tell the kernel to create one less thread than what was requested here.
   //  mMaxThreads(DEFAULT_MAX_BINDER_THREADS)

    size_t kernelMaxThreads = maxThreads - 1;
/* 最终其实就是调用ioctl()通知驱动  最大线程数 */
    if (ioctl(mDriverFD, BINDER_SET_MAX_THREADS, &kernelMaxThreads) != -1) {   //   #define BINDER_SET_MAX_THREADS _IOW('b', 5, __u32) 
        AutoMutex _l(mLock);
        mMaxThreads = maxThreads;
        mSpawnThreadOnStart = !callerJoinsPool;
    } else {
        result = -errno;
        ALOGE("Binder ioctl to set max threads failed: %s", strerror(-result));
    }
    return result;
}


构造器；

ProcessState::ProcessState(size_t mmap_size)
    : mDriverFD(open_driver())  //构造函数中open("/dev/hwbinder")
    , mVMStart(MAP_FAILED)
    , mThreadCountLock(PTHREAD_MUTEX_INITIALIZER)
    , mThreadCountDecrement(PTHREAD_COND_INITIALIZER)
    , mExecutingThreadsCount(0)
    , mMaxThreads(DEFAULT_MAX_BINDER_THREADS)
    , mStarvationStartTimeMs(0)
    , mManagesContexts(false)
    , mBinderContextCheckFunc(NULL)
    , mBinderContextUserData(NULL)
    , mThreadPoolStarted(false)
    , mSpawnThreadOnStart(true)
    , mThreadPoolSeq(1)
    , mMmapSize(mmap_size)
{
    if (mDriverFD >= 0) {
        // mmap the binder, providing a chunk of virtual address space to receive transactions.
        mVMStart = mmap(0, mMmapSize, PROT_READ, MAP_PRIVATE | MAP_NORESERVE, mDriverFD, 0);
        if (mVMStart == MAP_FAILED) {
            // *sigh*
            ALOGE("Using /dev/hwbinder failed: unable to mmap transaction memory.\n");
            close(mDriverFD);
            mDriverFD = -1;
        }
    }
    else {
        ALOGE("Binder driver could not be opened.  Terminating.");
    }
}

```


##### setupTransportPolling()
```
http://androidxref.com/9.0.0_r3/xref/system/libhidl/transport/HidlTransportSupport.cpp#33

int setupTransportPolling() {
    return setupBinderPolling();
}

http://androidxref.com/9.0.0_r3/xref/system/libhidl/transport/HidlBinderSupport.cpp#207

int setupBinderPolling() {
    int fd;
    int err = IPCThreadState::self()->setupPolling(&fd); 【1】

    if (err != OK) {
        ALOGE("Failed to setup binder polling: %d (%s)", err, strerror(err));
    }

    return err == OK ? fd : -1;
}

```
######  IPCThreadState.setupPolling
```
http://androidxref.com/9.0.0_r3/xref/system/libhwbinder/IPCThreadState.cpp#565 

  bool mIsPollingThread;   轮训线程 标记位
  Parcel              mOut;

int IPCThreadState::setupPolling(int* fd)
{
    if (mProcess->mDriverFD <= 0) {
        return -EBADF;
    }

    // Tells the kernel to not spawn any additional binder threads,  告诉内核不要生成任何额外的绑定线程
    // as that won't work with polling 因为这对轮询不起作用 . Also, the caller is responsible  调用者有责任调用 handlePolledCommands()
    // for subsequently calling handlePolledCommands()
    mProcess->setThreadPoolConfiguration(1, true /* callerWillJoin */);
    mIsPollingThread = true;   

    mOut.writeInt32(BC_ENTER_LOOPER);  // BC_ENTER_LOOPER 是给驱动的命令   enum  binder_driver_command_protocol { BC_EXIT_LOOPER = _IO('c', 13), }
    *fd = mProcess->mDriverFD;       // 返回句柄
    return 0;
}




static const char *kReturnStrings[] = {
    "BR_ERROR",
    "BR_OK",
    "BR_TRANSACTION",
    "BR_REPLY",
    "BR_ACQUIRE_RESULT",
    "BR_DEAD_REPLY",
    "BR_TRANSACTION_COMPLETE",
    "BR_INCREFS",
    "BR_ACQUIRE",
    "BR_RELEASE",
    "BR_DECREFS",
    "BR_ATTEMPT_ACQUIRE",
    "BR_NOOP",
    "BR_SPAWN_LOOPER",
    "BR_FINISHED",
    "BR_DEAD_BINDER",
    "BR_CLEAR_DEATH_NOTIFICATION_DONE",
    "BR_FAILED_REPLY"
};

static const char *kCommandStrings[] = {
    "BC_TRANSACTION",
    "BC_REPLY",
    "BC_ACQUIRE_RESULT",
    "BC_FREE_BUFFER",
    "BC_INCREFS",
    "BC_ACQUIRE",
    "BC_RELEASE",
    "BC_DECREFS",
    "BC_INCREFS_DONE",
    "BC_ACQUIRE_DONE",
    "BC_ATTEMPT_ACQUIRE",
    "BC_REGISTER_LOOPER",
    "BC_ENTER_LOOPER",
    "BC_EXIT_LOOPER",
    "BC_REQUEST_DEATH_NOTIFICATION",
    "BC_CLEAR_DEATH_NOTIFICATION",
    "BC_DEAD_BINDER_DONE"
};


```

##### eloop_register_read_sock(priv->hidl_fd, wpas_hidl_sock_handler...)  
```
同 其他的  eloop_register_read_sock   把 hidl_fd 注册到 eloop_data 的 read_table 中 ,一直监听往该fd 写入的 数据  并处理

```
##### HidlManager::getInstance()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl_manager.cpp#409

HidlManager *HidlManager::instance_ = NULL;


HidlManager *HidlManager::getInstance()
{
	if (!instance_)
		instance_ = new HidlManager();   // 和malloc 差不多
	return instance_;
}

	HidlManager() = default;

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl_manager.h#51

/**  HidlManager负责管理wpa_supplicant创建的所有hidl对象的生命周期
 * HidlManager is responsible for managing the lifetime of all hidl objects created by wpa_supplicant. 
 * 
 * 这是一个由supplicant core创建的单例类，可用于获取对hidl对象的引用
 * This is a singleton class which is created by the supplicant core and can be used to get references to the hidl objects.
 */
class HidlManager
{
public:
	static HidlManager *getInstance();
.....

}



HidlManager属性

	// Singleton instance of this class.
	static HidlManager *instance_;


	// The main hidl service object.   Supplicant注册到 HwServiceManager的服务类    , 它是 HidlManager属性
	android::sp<Supplicant> supplicant_object_;  


	// Map of all the P2P interface specific hidl objects controlled by
	// wpa_supplicant. This map is keyed in by the corresponding
	// |ifname|. P2P 接口Map  <namestring,P2pIface> map
	std::map<const std::string, android::sp<P2pIface>>p2p_iface_object_map_;



	// Map of all the STA interface specific hidl objects controlled by
	// wpa_supplicant. This map is keyed in by the corresponding
	// |ifname|.    STA 接口Map
	std::map<const std::string, android::sp<StaIface>> sta_iface_object_map_;




	// Map of all the P2P network specific hidl objects controlled by
	// wpa_supplicant. This map is keyed in by the corresponding
	// |ifname| & |network_id|.  < p2p_1  , P2pNetwork > Map 映射
	std::map<const std::string, android::sp<P2pNetwork>> p2p_network_object_map_;



	// Map of all the STA network specific hidl objects controlled by
	// wpa_supplicant. This map is keyed in by the corresponding
	// |ifname| & |network_id|.    < wlan0_1  , StaNetwork > Map 映射
	std::map<const std::string, android::sp<StaNetwork>> sta_network_object_map_;

	// Callback registered for the main hidl service object.  ISupplicantCallback 回调集合
	std::vector<android::sp<ISupplicantCallback>> supplicant_callbacks_;


	// Map of all the callbacks registered for P2P interface specific
	// hidl objects controlled by wpa_supplicant.  This map is keyed in by
	// the corresponding |ifname|.  P2P接口回调 集合映射
	std::map<const std::string,std::vector<android::sp<ISupplicantP2pIfaceCallback>>> p2p_iface_callbacks_map_;

	// Map of all the callbacks registered for STA interface specific
	// hidl objects controlled by wpa_supplicant.  This map is keyed in by
	// the corresponding |ifname|.  STA 接口回调 集合映射
	std::map<const std::string,std::vector<android::sp<ISupplicantStaIfaceCallback>>>sta_iface_callbacks_map_;



	// Map of all the callbacks registered for P2P network specific
	// hidl objects controlled by wpa_supplicant.  This map is keyed in by
	// the corresponding |ifname| & |network_id|.   P2P接口  网络回调 集合映射
	std::map<const std::string,std::vector<android::sp<ISupplicantP2pNetworkCallback>>>p2p_network_callbacks_map_;


	// Map of all the callbacks registered for STA network specific
	// hidl objects controlled by wpa_supplicant.  This map is keyed in by
	// the corresponding |ifname| & |network_id|.   STA 接口  网络回调 集合映射
	std::map<const std::string,std::vector<android::sp<ISupplicantStaNetworkCallback>>> sta_network_callbacks_map_;

```

##### hidl_manager->registerHidlService
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl_manager.cpp#423


int HidlManager::registerHidlService(struct wpa_global *global)
{
	// Create the main hidl service object and register it.
	supplicant_object_ = new Supplicant(global);  【1】

	if (supplicant_object_->registerAsService() != android::NO_ERROR) {   【2】
		return 1;
	}
	return 0;
}



```

###### new Supplicant(global)
```
// Create the main hidl service object and register it.
// 创建主hidl服务对象 supplicant  并注册它

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/supplicant.cpp#164


Supplicant::Supplicant(struct wpa_global* global) : wpa_global_(global) {   // malloc  this.wpa_global_ = wpa_global;

}

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/supplicant.h#83


namespace android {
namespace hardware {
namespace wifi {
namespace supplicant {
namespace V1_0 {
namespace implementation {
/**
 * Implementation of the supplicant hidl object. This hidl object is used core for global control operations on wpa_supplicant.
 */
class Supplicant : public android::hardware::wifi::supplicant::V1_0::ISupplicant     // 继承关系
{
public:
	Supplicant(struct wpa_global* global);
	~Supplicant() override = default;
	bool isValid();


	// Raw pointer to the global structure maintained by the core.  hidl中需要使用的 wpa_global 执行  
	struct wpa_global* wpa_global_;

```

###### supplicant_object_->registerAsService()
```
尼玛  找不到
https://blog.csdn.net/yangwen123/article/details/79876534

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl_manager.cpp#427

int HidlManager::registerHidlService(struct wpa_global *global)
{
	// Create the main hidl service object and register it.
	supplicant_object_ = new Supplicant(global);                         // class Supplicant : public android::hardware::wifi::supplicant::V1_0::ISupplicant   继承ISupplicant 
	if (supplicant_object_->registerAsService() != android::NO_ERROR) {  //将 ISupplicant 注册到 hwservicemanager 中
		return 1;
	}
	return 0;
}

registerAsService 是 HwBinder 中定义的方法
当我们定义IXXX.hal文件后，通过编译将在out/target/common/gen/JAVA_LIBRARIES目录下生成对应的IXXX.java       /out/target/common/obj/JAVA_LIBRARIES/android.hardware.wifi-java/classes.jar
当我们定义IXXX.hal文件后，通过编译将在out/target/common/gen/STATIC_LIBRARIES目录下生成对应的IXXX.cpp     /out/target/product/xxNamexx/obj/STATIC_LIBRARY/libwpa_hidlxxxx
http://androidxref.com/9.0.0_r3/xref/system/tools/hidl/generateCpp.cpp#126   .hal 文件通过 hidl-gen编译为 cpp文件 ,


如下图所示，当我们定义IXXX.hal文件后，通过编译将在out/target/common/gen/JAVA_LIBRARIES目录下生成对应的IXXX.java，
该文件按上述类继承关系自动生成相关代码，我们只需要定义一个XXXImp类，继承Stub并实现所有方法，
然后在某个服务进程中创建一个XXXImp对象，并调用registerService（）函数进行hidl服务注册，如下所示：

XXXImp mXXXImp = new XXXImp();
mXXXImp.registerAsService("XXXImp")



```

<img src="//../zimage/wireless/wifi/09_supplicant/hwbinder.png" width = "50%" height="50%"/>

##### wpas_hidl_deinit
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hidl/1.1/hidl.cpp#71


void wpas_hidl_deinit(struct wpas_hidl_priv *priv)
{
	if (!priv)
		return;

	wpa_printf(MSG_DEBUG, "Deiniting hidl control");

	HidlManager::destroyInstance();
	eloop_unregister_read_sock(priv->hidl_fd);   // 从 eloop_data.reader_table 去掉句柄
	os_free(priv);
}


HidlManager *HidlManager::instance_ = NULL;    // 把当前单例 HidlManager 置空 释放内存
void HidlManager::destroyInstance()
{
	if (instance_)
		delete instance_;
	instance_ = NULL;
}


```



### wifi_display_init
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wifi_display.c#22

 wpa_global.wifi_display   
int wifi_display;

int wifi_display_init(struct wpa_global *global)
{
	global->wifi_display = 1;   // 对wpa_global的 int wifidisplay 初始化为1 
	return 0;
}



```

### eloop_register_timeout
```
#define WPA_SUPPLICANT_CLEANUP_INTERVAL 10

eloop_register_timeout(WPA_SUPPLICANT_CLEANUP_INTERVAL, 0, wpas_periodic 【1】, global, NULL);  

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#762

int eloop_register_timeout(unsigned int secs, unsigned int usecs,eloop_timeout_handler handler,void *eloop_data, void *user_data)
{
	struct eloop_timeout *timeout, *tmp;
	os_time_t now_sec;

	timeout = os_zalloc(sizeof(*timeout));
	if (timeout == NULL)
		return -1;
	if (os_get_reltime(&timeout->time) < 0) {
		os_free(timeout);
		return -1;
	}
	now_sec = timeout->time.sec;
	timeout->time.sec += secs;
	if (timeout->time.sec < now_sec) {
		/*
		 * Integer overflow - assume long enough timeout to be assumed
		 * to be infinite, i.e., the timeout would never happen.
		 */
		wpa_printf(MSG_DEBUG, "ELOOP: Too long timeout (secs=%u) to ever happen - ignore it", secs);
		os_free(timeout);
		return 0;
	}
	timeout->time.usec += usecs;
	while (timeout->time.usec >= 1000000) {
		timeout->time.sec++;
		timeout->time.usec -= 1000000;
	}
	timeout->eloop_data = eloop_data;
	timeout->user_data = user_data;
	timeout->handler = handler;
	wpa_trace_add_ref(timeout, eloop, eloop_data);
	wpa_trace_add_ref(timeout, user, user_data);
	wpa_trace_record(timeout);

 //  static struct eloop_data eloop; 
   //  eloop定义 http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#eloop
	/* Maintain timeouts in order of increasing time 维护超时双向链表的顺序 随着时间的增长的情况下 */
	dl_list_for_each(tmp, &eloop.timeout, struct eloop_timeout, list) {  

		if (os_reltime_before(&timeout->time, &tmp->time)) {
			dl_list_add(tmp->list.prev, &timeout->list);    【插入一个 dllist_timeout item 】
			return 0;
		}
	}
	dl_list_add_tail(&eloop.timeout, &timeout->list);    【插入到末尾的 dllist_timeout item 】

	return 0;
}


```
#### eloop_timeout_handler wpas_periodic(*void func)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5868

#define WPA_SUPPLICANT_CLEANUP_INTERVAL 10

/* Periodic cleanup tasks  定期执行 清理任务 */
static void wpas_periodic(void *eloop_ctx, void *timeout_ctx)
{
	struct wpa_global *global = eloop_ctx;
	struct wpa_supplicant *wpa_s;

	eloop_register_timeout(WPA_SUPPLICANT_CLEANUP_INTERVAL, 0,wpas_periodic, global, NULL);  // 再次注册到 global的 timeout_dllist 双向链表 【已有分析 # eloop_register_timeout 】


	if (global->p2p)    //   struct wpa_global {  struct p2p_data *p2p;  .... }
		p2p_expire_peers(global->p2p); 【1】

	for (wpa_s = global->ifaces; wpa_s; wpa_s = wpa_s->next) {
		wpa_bss_flush_by_age(wpa_s, wpa_s->conf->bss_expiration_age@180@); 【2】
		ap_periodic(wpa_s); 【3】

	}
}



```

#####  p2p_expire_peers(global->p2p @p2p_data@)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.c#53


void p2p_expire_peers(struct p2p_data *p2p)
{
	struct p2p_device *dev, *n;
	struct os_reltime now;
	size_t i;

	os_get_reltime(&now);
	dl_list_for_each_safe(dev, n, &p2p->devices, struct p2p_device, list) {
		if (dev->last_seen.sec + P2P_PEER_EXPIRATION_AGE @ 60 @ >= now.sec)
			continue;

		if (dev == p2p->go_neg_peer) {
			/*
			 * GO Negotiation is in progress with the peer, so
			 * don't expire the peer entry until GO Negotiation
			 * fails or times out.
			 */
			continue;
		}

		if (p2p->cfg->go_connected && p2p->cfg->go_connected(p2p->cfg->cb_ctx,dev->info.p2p_device_addr) 【1】) {
			/*
			 * We are connected as a client to a group in which the
			 * peer is the GO, so do not expire the peer entry.
			 */
			os_get_reltime(&dev->last_seen);
			continue;
		}

		for (i = 0; i < p2p->num_groups; i++) {
			   if (p2p_group_is_client_connected(p2p->groups[i], dev->info.p2p_device_addr)) 【2】
				break;
		}


		if (i < p2p->num_groups) {
			/*
			 * The peer is connected as a client in a group where
			 * we are the GO, so do not expire the peer entry.
			 */
			os_get_reltime(&dev->last_seen);
			continue;   // 继续 dl_list_for_each_safe 循环
		}

		p2p_dbg(p2p, "Expiring old peer entry " MACSTR,MAC2STR(dev->info.p2p_device_addr));
		dl_list_del(&dev->list);       【已有分析  删除双向链表的一个 Item 】
		p2p_device_free(p2p, dev);      【3】
	}
}


```
###### p2p->cfg->go_connected & p2p_config.go_connected
```

```

###### p2p_group_is_client_connected
```


```

###### p2p_device_free
```


```

#####  wpa_bss_flush_by_age(wpa_s, wpa_s->conf->bss_expiration_age)
```

wpa_bss_flush_by_age(wpa_s, wpa_s->conf->bss_expiration_age@180@); 

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/bss.c#911


/**
 * wpa_bss_flush_by_age - Flush old BSS entries
 * @wpa_s: Pointer to wpa_supplicant data
 * @age: Maximum entry age in seconds
 *
 * Remove BSS entries that have not been updated during the last @age seconds.
 */
void wpa_bss_flush_by_age(struct wpa_supplicant *wpa_s, int age@180@)
{
	struct wpa_bss *bss, *n;
	struct os_reltime t;

	if (dl_list_empty(&wpa_s->bss))
		return;

	os_get_reltime(&t);
	t.sec -= age;

	dl_list_for_each_safe(bss, n, &wpa_s->bss, struct wpa_bss, list) {
		if (wpa_bss_in_use(wpa_s, bss))  【1】
			continue;

		if (os_reltime_before(&bss->last_update, &t)) {
			wpa_bss_remove(wpa_s, bss, __func__);【2】     // #define __func__ "__func__ not defined"
		} else
			break;
	}
}



```
###### wpa_bss_in_use(wpa_s, bss)
```


```
###### wpa_bss_remove(wpa_s, bss, __func__)
```

```


#####  ap_periodic(wpa_s)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ap.c#1580

void ap_periodic(struct wpa_supplicant *wpa_s)
{
	if (wpa_s->ap_iface)
		hostapd_periodic_iface(wpa_s->ap_iface);
}


http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/hostapd.c#3404


void hostapd_periodic_iface(struct hostapd_iface *iface)
{
	size_t i;

	ap_list_timer(iface);   【1】

	for (i = 0; i < iface->num_bss; i++) {
		struct hostapd_data *hapd = iface->bss[i];

		if (!hapd->started)
			continue;

		hostapd_acl_expire(hapd);  【2】

	}
}

```
###### ap_list_timer(hostapd_iface face)
```
未完待续
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_list.c#251

void ap_list_timer(struct hostapd_iface *iface)
{
	struct os_reltime now;
	struct ap_info *ap;
	int set_beacon = 0;

	if (!iface->ap_list)
		return;

	os_get_reltime(&now);

	while (iface->ap_list) {
		ap = iface->ap_list->prev;
		if (!os_reltime_expired(&now, &ap->last_beacon,iface->conf->ap_table_expiration_time))
			break;

		ap_free_ap(iface, ap);
	}

	if (iface->olbc || iface->olbc_ht) {
		int olbc = 0;
		int olbc_ht = 0;

		ap = iface->ap_list;
		while (ap && (olbc == 0 || olbc_ht == 0)) {
			if (ap_list_beacon_olbc(iface, ap))
				olbc = 1;
			if (!ap->ht_support)
				olbc_ht = 1;
			ap = ap->next;
		}
		if (!olbc && iface->olbc) {
			wpa_printf(MSG_DEBUG, "OLBC not detected anymore");
			iface->olbc = 0;
			set_beacon++;
		}

		if (!olbc_ht && iface->olbc_ht) {
			wpa_printf(MSG_DEBUG, "OLBC HT not detected anymore");
			iface->olbc_ht = 0;
			hostapd_ht_operation_update(iface);
			set_beacon++;
		}

	}

	if (set_beacon)
		ieee802_11_update_beacons(iface);
}

```

###### hostapd_acl_expire(hostapd_data hapd)
```

```


## wpa_supplicant* wpa_supplicant_add_iface( wpa_global,wpa_interface ,wpa_supplicant)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5673


struct wpa_interface *ifaces;
struct wpa_global *global;

wpa_supplicant_add_iface(global, &ifaces[i], NULL)
/**
 * wpa_supplicant_add_iface - Add a new network interface
 * @global: Pointer to global data from wpa_supplicant_init()
 * @iface: Interface configuration options
 * @parent: Parent interface or %NULL to assign new interface as parent
 * Returns: Pointer to the created interface or %NULL on failure
 *### wpa_supplicant_global_ctrl
 * This function is used to add new network interfaces for %wpa_supplicant.
 * This can be called before wpa_supplicant_run() to add interfaces before the
 * main event loop has been started. In addition, new interfaces can be added
 * dynamically while %wpa_supplicant is already running. This could happen,
 * e.g., when a hotplug network adapter is inserted.
 */





struct wpa_supplicant * wpa_supplicant_add_iface(struct wpa_global *global, struct wpa_interface *iface, struct wpa_supplicant *parent)
{
	struct wpa_supplicant *wpa_s;
	struct wpa_interface t_iface;
	struct wpa_ssid *ssid;

	if (global == NULL || iface == NULL)
		return NULL;

	wpa_s = wpa_supplicant_alloc(parent);  【1】
	if (wpa_s == NULL)
		return NULL;

	wpa_s->global = global;

	t_iface = *iface;
	if (global->params.override_driver) {
		wpa_printf(MSG_DEBUG, "Override interface parameter: driver "('%s' -> '%s')",iface->driver, global->params.override_driver);
		t_iface.driver = global->params.override_driver;
	}
	if (global->params.override_ctrl_interface) {
		wpa_printf(MSG_DEBUG, "Override interface parameter: ""ctrl_interface ('%s' -> '%s')",iface->ctrl_interface,global->params.override_ctrl_interface);
		t_iface.ctrl_interface =global->params.override_ctrl_interface;
	}
	if (wpa_supplicant_init_iface(wpa_s, &t_iface)) {    【2】
		wpa_printf(MSG_DEBUG, "Failed to add interface %s", iface->ifname);
		wpa_supplicant_deinit_iface(wpa_s, 0, 0);
		return NULL;
	}

	/* Notify the control interfaces about new iface */
	if (wpas_notify_iface_added(wpa_s)) {     【3】
		wpa_supplicant_deinit_iface(wpa_s, 1, 0);
		return NULL;
	}

	/* Notify the control interfaces about new networks for non p2p mgmt
	 * ifaces. */
	if (iface->p2p_mgmt == 0) {
		for (ssid = wpa_s->conf->ssid; ssid; ssid = ssid->next)
			wpas_notify_network_added(wpa_s, ssid);   【4】
	}

	wpa_s->next = global->ifaces;
	global->ifaces = wpa_s;

	wpa_dbg(wpa_s, MSG_DEBUG, "Added interface %s", wpa_s->ifname);  【5】
	wpa_supplicant_set_state(wpa_s, WPA_DISCONNECTED);       【6】

#ifdef CONFIG_P2P

	if (wpa_s->global->p2p == NULL && !wpa_s->global->p2p_disabled && !wpa_s->conf->p2p_disabled &&
	    (wpa_s->drv_flags & WPA_DRIVER_FLAGS_DEDICATED_P2P_DEVICE) &&  wpas_p2p_add_p2pdev_interface( wpa_s, wpa_s->global->params.conf_p2p_dev) < 0)  【7 】{
		wpa_printf(MSG_INFO,  "P2P: Failed to enable P2P Device interface");

		/* Try to continue without. P2P will be disabled. */
	}
#endif /* CONFIG_P2P */


	return wpa_s;
}



```
### wpa_supplicant_alloc(parent@null@)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5673

static struct wpa_supplicant *  wpa_supplicant_alloc(struct wpa_supplicant *parent)
{
	struct wpa_supplicant *wpa_s;

	wpa_s = os_zalloc(sizeof(*wpa_s));   //wpa_supplicant初始化 
	if (wpa_s == NULL)
		return NULL;
	wpa_s->scan_req = INITIAL_SCAN_REQ;    // 扫描分类
	wpa_s->scan_interval = 5;      // 扫描间隔
	wpa_s->new_connection = 1;   
	wpa_s->parent = parent ? parent : wpa_s;  // 自己指向自己    struct wpa_supplicant * 	parent
	wpa_s->p2pdev = wpa_s->parent;            //自己指向自己  struct wpa_supplicant *p2pdev;  
	wpa_s->sched_scanning = 0;

	dl_list_init(&wpa_s->bss_tmp_disallowed);   // 双向链表初始化  dllist  【已有分析】
	dl_list_init(&wpa_s->fils_hlp_req);         // 双向链表初始化

	return wpa_s;
}


扫描类型分类
	enum scan_req_type {
		/**
		 * NORMAL_SCAN_REQ - Normal scan request
		 * This is used for scans initiated by wpa_supplicant to find an AP for a connection.
		 */
		NORMAL_SCAN_REQ,

		/**
		 * INITIAL_SCAN_REQ - Initial scan request
		 * This is used for the first scan on an interface to force at
		 * least one scan to be run even if the configuration does not
		 * include any enabled networks.
		 */
		INITIAL_SCAN_REQ,

		/**
		 * MANUAL_SCAN_REQ - Manual scan request
		 * This is used for scans where the user request a scan or
		 * a specific wpa_supplicant operation (e.g., WPS) requires scan
		 * to be run.
		 */
		MANUAL_SCAN_REQ
	} scan_req, last_scan_req;


```

### wpa_supplicant_init_iface(wpa_s, &t_iface)
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#5157

static int wpa_supplicant_init_iface(struct wpa_supplicant *wpa_s,struct wpa_interface *iface)                                


	struct wpa_driver_capa capa;
	int capa_res;
	u8 dfs_domain;

	wpa_printf(MSG_DEBUG, "Initializing interface '%s' conf '%s' driver "
		   "'%s' ctrl_interface '%s' bridge '%s'", iface->ifname,
		   iface->confname ? iface->confname : "N/A",
		   iface->driver ? iface->driver : "default",
		   iface->ctrl_interface ? iface->ctrl_interface : "N/A",
		   iface->bridge_ifname ? iface->bridge_ifname : "N/A");

	if (iface->confname) {

		wpa_s->confname = os_strdup(iface->confname);
		wpa_s->conf = wpa_config_read(wpa_s->confname, NULL); 【1】
		if (wpa_s->conf == NULL) {
			wpa_printf(MSG_ERROR, "Failed to read or parse configuration '%s'.", wpa_s->confname);
			return -1;
		}
		wpa_s->confanother = os_rel2abs_path(iface->confanother); 【2】
		wpa_config_read(wpa_s->confanother, wpa_s->conf);   // 【同1】

		/*
		 * Override ctrl_interface and driver_param if set on command line.
		 */
		if (iface->ctrl_interface) {
			os_free(wpa_s->conf->ctrl_interface);
			wpa_s->conf->ctrl_interface = os_strdup(iface->ctrl_interface); 【3】
		}

		if (iface->driver_param) {
			os_free(wpa_s->conf->driver_param);
			wpa_s->conf->driver_param = os_strdup(iface->driver_param);  【同3】
		}

		if (iface->p2p_mgmt && !iface->ctrl_interface) {
			os_free(wpa_s->conf->ctrl_interface);
			wpa_s->conf->ctrl_interface = NULL;
		}
	} else
		wpa_s->conf = wpa_config_alloc_empty(iface->ctrl_interface,iface->driver_param); 【4】 // 没有定义配置文件的话

	if (wpa_s->conf == NULL) {
		wpa_printf(MSG_ERROR, "\nNo configuration found.");
		return -1;
	}

	if (iface->ifname == NULL) {
		wpa_printf(MSG_ERROR, "\nInterface name is required.");
		return -1;
	}
	if (os_strlen(iface->ifname) >= sizeof(wpa_s->ifname)) {
		wpa_printf(MSG_ERROR, "\nToo long interface name '%s'.", iface->ifname);
		return -1;
	}
	os_strlcpy(wpa_s->ifname, iface->ifname, sizeof(wpa_s->ifname)); // 把参数 wpa_interface 中的 ifname 接口名称复制到 wpa_supplicant 中

	if (iface->bridge_ifname) {
		if (os_strlen(iface->bridge_ifname) >= sizeof(wpa_s->bridge_ifname)) {
			wpa_printf(MSG_ERROR, "\nToo long bridge interface  name '%s'.", iface->bridge_ifname);
			return -1;
		}
		os_strlcpy(wpa_s->bridge_ifname, iface->bridge_ifname,sizeof(wpa_s->bridge_ifname));  // 把参数 wpa_interface 中的 bridge_ifname 接口名称复制到 wpa_supplicant 中
	}

	/* RSNA Supplicant Key Management - INITIALIZE */
	eapol_sm_notify_portEnabled(wpa_s->eapol, FALSE);  【5】
	eapol_sm_notify_portValid(wpa_s->eapol, FALSE);    【6】

	/* Initialize driver interface and register driver event handler before
	 * L2 receive handler so that association events are processed before
	 * EAPOL-Key packets if both become available for the same select() call. */
	if (wpas_init_driver(wpa_s, iface) < 0)    【7】
		return -1;

	if (wpa_supplicant_init_wpa(wpa_s) < 0)  【8】
		return -1;

	wpa_sm_set_ifname(wpa_s->wpa, wpa_s->ifname, wpa_s->bridge_ifname[0] ? wpa_s->bridge_ifname : NULL); 【9】
	wpa_sm_set_fast_reauth(wpa_s->wpa, wpa_s->conf->fast_reauth);  【10】

	if (wpa_s->conf->dot11RSNAConfigPMKLifetime@ 43200 @ && wpa_sm_set_param(wpa_s->wpa, RSNA_PMK_LIFETIME,wpa_s->conf->dot11RSNAConfigPMKLifetime)) {  【11】
		wpa_msg(wpa_s, MSG_ERROR, "Invalid WPA parameter value for dot11RSNAConfigPMKLifetime"); 【12】
		return -1;
	}

	if (wpa_s->conf->dot11RSNAConfigPMKReauthThreshold@ 0 @ && wpa_sm_set_param(wpa_s->wpa, RSNA_PMK_REAUTH_THRESHOLD,wpa_s->conf->dot11RSNAConfigPMKReauthThreshold)) {
		wpa_msg(wpa_s, MSG_ERROR, "Invalid WPA parameter value for dot11RSNAConfigPMKReauthThreshold");
		return -1;
	}

	if (wpa_s->conf->dot11RSNAConfigSATimeout @ 0 @ &&wpa_sm_set_param(wpa_s->wpa, RSNA_SA_TIMEOUT, wpa_s->conf->dot11RSNAConfigSATimeout)) {
		wpa_msg(wpa_s, MSG_ERROR, "Invalid WPA parameter value for "
			"dot11RSNAConfigSATimeout");
		return -1;
	}

	wpa_s->hw.modes = wpa_drv_get_hw_feature_data(wpa_s,&wpa_s->hw.num_modes,&wpa_s->hw.flags,&dfs_domain); 【13】
	if (wpa_s->hw.modes) {
		u16 i;

		for (i = 0; i < wpa_s->hw.num_modes; i++) {
			if (wpa_s->hw.modes[i].vht_capab) {
				wpa_s->hw_capab = CAPAB_VHT;
				break;
			}

                enum local_hw_capab {
//                    CAPAB_NO_HT_VHT,
//                    CAPAB_HT,
//                    CAPAB_HT40,
//                    CAPAB_VHT,
//                } hw_capab;
// #define HT_CAP_INFO_SUPP_CHANNEL_WIDTH_SET	((u16) BIT(1)) 


			if (wpa_s->hw.modes[i].ht_capab & HT_CAP_INFO_SUPP_CHANNEL_WIDTH_SET)   // 依据bit字节位  检测能力 capable 
				wpa_s->hw_capab = CAPAB_HT40;
			else if (wpa_s->hw.modes[i].ht_capab && wpa_s->hw_capab == CAPAB_NO_HT_VHT)
				wpa_s->hw_capab = CAPAB_HT;
		}
	}

	int capa_res = wpa_drv_get_capa(wpa_s, &capa);   【14】
	if (capa_res == 0) {    // 初始化 capa数据
		wpa_s->drv_capa_known = 1;
		wpa_s->drv_flags = capa.flags;
		wpa_s->drv_enc = capa.enc;
		wpa_s->drv_smps_modes = capa.smps_modes;
		wpa_s->drv_rrm_flags = capa.rrm_flags;
		wpa_s->probe_resp_offloads = capa.probe_resp_offloads;
		wpa_s->max_scan_ssids = capa.max_scan_ssids;
		wpa_s->max_sched_scan_ssids = capa.max_sched_scan_ssids;
		wpa_s->max_sched_scan_plans = capa.max_sched_scan_plans;
		wpa_s->max_sched_scan_plan_interval =capa.max_sched_scan_plan_interval;
		wpa_s->max_sched_scan_plan_iterations =capa.max_sched_scan_plan_iterations;
		wpa_s->sched_scan_supported = capa.sched_scan_supported;
		wpa_s->max_match_sets = capa.max_match_sets;
		wpa_s->max_remain_on_chan = capa.max_remain_on_chan;
		wpa_s->max_stations = capa.max_stations;
		wpa_s->extended_capa = capa.extended_capa;
		wpa_s->extended_capa_mask = capa.extended_capa_mask;
		wpa_s->extended_capa_len = capa.extended_capa_len;
		wpa_s->num_multichan_concurrent =capa.num_multichan_concurrent;
		wpa_s->wmm_ac_supported = capa.wmm_ac_supported;

		if (capa.mac_addr_rand_scan_supported)
			wpa_s->mac_addr_rand_supported |= MAC_ADDR_RAND_SCAN;
		if (wpa_s->sched_scan_supported &&
		    capa.mac_addr_rand_sched_scan_supported)
			wpa_s->mac_addr_rand_supported |=
				(MAC_ADDR_RAND_SCHED_SCAN | MAC_ADDR_RAND_PNO);
	}
	if (wpa_s->max_remain_on_chan == 0)
		wpa_s->max_remain_on_chan = 1000;

	/*
	 * Only take p2p_mgmt parameters when P2P Device is supported.
	 * Doing it here as it determines whether l2_packet_init() will be done
	 * during wpa_supplicant_driver_init().
	 */
	if (wpa_s->drv_flags & WPA_DRIVER_FLAGS_DEDICATED_P2P_DEVICE  @ 0x20000000 @)
		wpa_s->p2p_mgmt = iface->p2p_mgmt; 
	else
		iface->p2p_mgmt = 1;

	if (wpa_s->num_multichan_concurrent == 0)
		wpa_s->num_multichan_concurrent = 1;

	if (wpa_supplicant_driver_init(wpa_s) < 0)  【15】
		return -1;

#ifdef CONFIG_TDLS
	if ((!iface->p2p_mgmt || !(wpa_s->drv_flags & WPA_DRIVER_FLAGS_DEDICATED_P2P_DEVICE)) && wpa_tdls_init(wpa_s->wpa))  【16】
		return -1;
#endif /* CONFIG_TDLS */

	if (wpa_s->conf->country[0] && wpa_s->conf->country[1] && wpa_drv_set_country(wpa_s, wpa_s->conf->country)) {  【 17 设置国家编码 countrycode 】
		wpa_dbg(wpa_s, MSG_DEBUG, "Failed to set country");
		return -1;
	}


	if (wpas_wps_init(wpa_s))   【18】
		return -1;




	if (wpa_supplicant_init_eapol(wpa_s) < 0)  【19】
		return -1;
	wpa_sm_set_eapol(wpa_s->wpa, wpa_s->eapol);  【20】

	wpa_s->ctrl_iface = wpa_supplicant_ctrl_iface_init(wpa_s);   【21】
	if (wpa_s->ctrl_iface == NULL) {
		wpa_printf(MSG_ERROR,
			   "Failed to initialize control interface '%s'.\n"
			   "You may have another wpa_supplicant process "
			   "already running or the file was\n"
			   "left by an unclean termination of wpa_supplicant "
			   "in which case you will need\n"
			   "to manually remove this file before starting "
			   "wpa_supplicant again.\n",
			   wpa_s->conf->ctrl_interface);
		return -1;
	}

	wpa_s->gas = gas_query_init(wpa_s);  【22】
	if (wpa_s->gas == NULL) {
		wpa_printf(MSG_ERROR, "Failed to initialize GAS query");
		return -1;
	}

	if (iface->p2p_mgmt && wpas_p2p_init(wpa_s->global, wpa_s) < 0) {   【23】
		wpa_msg(wpa_s, MSG_ERROR, "Failed to init P2P");
		return -1;
	}

	if (wpa_bss_init(wpa_s) < 0)    【24】
		return -1;


	/*
	 * Set Wake-on-WLAN triggers, if configured.
	 * Note: We don't restore/remove the triggers on shutdown (it doesn't
	 * have effect anyway when the interface is down).
	 */
	if (capa_res == 0 && wpas_set_wowlan_triggers(wpa_s, &capa) < 0)    【25】
		return -1;



	if (pcsc_reader_init(wpa_s) < 0)  【26】
		return -1;

	if (wpas_init_ext_pw(wpa_s) < 0)   【27】
		return -1;

	wpas_rrm_reset(wpa_s);   【28】

	wpas_sched_scan_plans_set(wpa_s, wpa_s->conf->sched_scan_plans);   【29】

#ifdef CONFIG_HS20
	hs20_init(wpa_s);       【29】
#endif /* CONFIG_HS20 */



	wpa_supplicant_set_default_scan_ies(wpa_s);   【30】

	return 0;
}
```

#### wpa_config_read(wpa_s->confname, NULL)
```
继续点

```
#### os_rel2abs_path(iface->confanother)
#### os_strdup(iface->ctrl_interface)
#### wpa_config_alloc_empty(iface->ctrl_interface,iface->driver_param)
#### eapol_sm_notify_portEnabled(wpa_s->eapol, FALSE); 
#### eapol_sm_notify_portValid(wpa_s->eapol, FALSE)
#### wpas_init_driver(wpa_s, iface)
#### wpa_supplicant_init_wpa(wpa_s)
#### wpa_sm_set_ifname
#### wpa_sm_set_fast_reauth
#### wpa_sm_set_param
#### wpa_msg(wpa_s, MSG_ERROR,"")
#### wpa_drv_get_hw_feature_data
#### wpa_drv_get_capa(wpa_s, &capa)
#### wpa_supplicant_driver_init(wpa_s)
#### wpa_tdls_init(wpa_s->wpa)
#### wpa_drv_set_country
#### wpas_wps_init(wpa_s)
#### wpa_supplicant_init_eapol(wpa_s)
#### wpa_sm_set_eapol(wpa_s->wpa, wpa_s->eapol)
#### wpa_supplicant_ctrl_iface_init(wpa_s)
#### gas_query_init(wpa_s)
#### wpas_p2p_init(wpa_s->global, wpa_s) 
#### wpa_bss_init(wpa_s) 
#### wpas_set_wowlan_triggers(wpa_s, &capa)
#### pcsc_reader_init(wpa_s)
#### wpas_init_ext_pw(wpa_s)
#### wpas_rrm_reset(wpa_s)
#### wpas_sched_scan_plans_set(wpa_s, wpa_s->conf->sched_scan_plans)
#### hs20_init(wpa_s)
#### wpa_supplicant_set_default_scan_ies(wpa_s);


### wpas_notify_iface_added(wpa_s)
### wpas_notify_network_added(wpa_s, ssid)
### wpa_dbg(wpa_s, MSG_DEBUG, "Added interface %s", wpa_s->ifname)
### wpa_supplicant_set_state(wpa_s, WPA_DISCONNECTED)
### wpas_p2p_add_p2pdev_interface

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

# wpas_global_ctrl_iface_open_sock 长函数分析
```

static int wpas_global_ctrl_iface_open_sock(struct wpa_global *global,
					    struct ctrl_iface_global_priv *priv)
{
	struct sockaddr_un addr;
	const char *ctrl = global->params.ctrl_interface;   //  -g@android:vendor_wpa_wlan0
	int flags;

	wpa_printf(MSG_DEBUG, "Global control interface '%s'", ctrl);

	if (os_strncmp(ctrl, "@android:", 9) == 0) {  //strncmp比较特定长度的字符串   大于时返回1；等于时返回0；小于时返回-1     判断借口前9个字符是不是  @android:
		priv->sock = android_get_control_socket(ctrl + 9);  【1】
		if (priv->sock < 0) {
			wpa_printf(MSG_ERROR, "Failed to open Android control socket '%s'", ctrl + 9);
			goto fail;
		}
		wpa_printf(MSG_DEBUG, "Using Android control socket '%s'", ctrl + 9);
		priv->android_control_socket = 1;   // 安卓控制接口 int 初始化为1 
		goto havesock;
	}

	if (os_strncmp(ctrl, "@abstract:", 10) != 0) {
		/*
		 * Backwards compatibility - try to open an Android control
		 * socket and if that fails, assume this was a UNIX domain
		 * socket instead.
		 */
        // 尝试打开 socket 句柄 以  @android:vendor_wpa_wlan0 为参数
		priv->sock = android_get_control_socket(ctrl);     
		if (priv->sock >= 0) {
			wpa_printf(MSG_DEBUG,
				   "Using Android control socket '%s'",
				   ctrl);
			priv->android_control_socket = 1;
			goto havesock;
		}
	}

	priv->sock = socket(PF_UNIX, SOCK_DGRAM, 0);
	if (priv->sock < 0) {
		wpa_printf(MSG_ERROR, "socket(PF_UNIX): %s", strerror(errno));
		goto fail;
	}

	os_memset(&addr, 0, sizeof(addr));
	addr.sun_family = AF_UNIX;

	if (os_strncmp(ctrl, "@abstract:", 10) == 0) {
		addr.sun_path[0] = '\0';
		os_strlcpy(addr.sun_path + 1, ctrl + 10,
			   sizeof(addr.sun_path) - 1);
		if (bind(priv->sock, (struct sockaddr *) &addr, sizeof(addr)) <
		    0) {
			wpa_printf(MSG_ERROR, "supp-global-ctrl-iface-init: "
				   "bind(PF_UNIX;%s) failed: %s",
				   ctrl, strerror(errno));
			goto fail;
		}
		wpa_printf(MSG_DEBUG, "Using Abstract control socket '%s'",
			   ctrl + 10);
		goto havesock;
	}

	os_strlcpy(addr.sun_path, ctrl, sizeof(addr.sun_path));
	if (bind(priv->sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
		wpa_printf(MSG_INFO, "supp-global-ctrl-iface-init(%s) (will try fixup): bind(PF_UNIX): %s",
			   ctrl, strerror(errno));
		if (connect(priv->sock, (struct sockaddr *) &addr,
			    sizeof(addr)) < 0) {
			wpa_printf(MSG_DEBUG, "ctrl_iface exists, but does not"
				   " allow connections - assuming it was left"
				   "over from forced program termination");
			if (unlink(ctrl) < 0) {
				wpa_printf(MSG_ERROR,
					   "Could not unlink existing ctrl_iface socket '%s': %s",
					   ctrl, strerror(errno));
				goto fail;
			}
			if (bind(priv->sock, (struct sockaddr *) &addr,
				 sizeof(addr)) < 0) {
				wpa_printf(MSG_ERROR, "supp-glb-iface-init: bind(PF_UNIX;%s): %s",
					   ctrl, strerror(errno));
				goto fail;
			}
			wpa_printf(MSG_DEBUG, "Successfully replaced leftover "
				   "ctrl_iface socket '%s'",
				   ctrl);
		} else {
			wpa_printf(MSG_INFO, "ctrl_iface exists and seems to "
				   "be in use - cannot override it");
			wpa_printf(MSG_INFO, "Delete '%s' manually if it is "
				   "not used anymore",
				   ctrl);
			goto fail;
		}
	}

	wpa_printf(MSG_DEBUG, "Using UNIX control socket '%s'", ctrl);

	if (global->params.ctrl_interface_group) {
		char *gid_str = global->params.ctrl_interface_group;
		gid_t gid = 0;
		struct group *grp;
		char *endp;

		grp = getgrnam(gid_str);
		if (grp) {
			gid = grp->gr_gid;
			wpa_printf(MSG_DEBUG, "ctrl_interface_group=%d"
				   " (from group name '%s')",
				   (int) gid, gid_str);
		} else {
			/* Group name not found - try to parse this as gid */
			gid = strtol(gid_str, &endp, 10);
			if (*gid_str == '\0' || *endp != '\0') {
				wpa_printf(MSG_ERROR, "CTRL: Invalid group "
					   "'%s'", gid_str);
				goto fail;
			}
			wpa_printf(MSG_DEBUG, "ctrl_interface_group=%d",
				   (int) gid);
		}
		if (chown(ctrl, -1, gid) < 0) {
			wpa_printf(MSG_ERROR,
				   "chown[global_ctrl_interface=%s,gid=%d]: %s",
				   ctrl, (int) gid, strerror(errno));
			goto fail;
		}

		if (chmod(ctrl, S_IRWXU | S_IRWXG) < 0) {
			wpa_printf(MSG_ERROR,
				   "chmod[global_ctrl_interface=%s]: %s",
				   ctrl, strerror(errno));
			goto fail;
		}
	} else {
		if (chmod(ctrl, S_IRWXU) < 0) {
			wpa_printf(MSG_DEBUG,
				   "chmod[global_ctrl_interface=%s](S_IRWXU): %s",
				   ctrl, strerror(errno));
			/* continue anyway since group change was not required
			 */
		}
	}

havesock:

	/*  使套接字不阻塞，以便在目标意外死亡时不会永久挂起。
	 * Make socket non-blocking so that we don't hang forever if target dies unexpectedly.
	 */
	flags = fcntl(priv->sock, F_GETFL);  【2】  取得文件描述词状态旗标
	if (flags >= 0) {
		flags |= O_NONBLOCK;          // 添加非阻塞标识 旗标
		if (fcntl(priv->sock, F_SETFL, flags) < 0) {    //  设置文件状态旗标 
			wpa_printf(MSG_INFO, "fcntl(ctrl, O_NONBLOCK): %s", strerror(errno));
			/* Not fatal, continue on.*/
		}
	}

// 往 eloop_data 中的 read table 加入一个新的读 socket item
// wpa_supplicant_global_ctrl_iface_receive 是 vendor_wpa_wlan0 读取socket 的 处理函数
	eloop_register_read_sock(priv->sock,wpa_supplicant_global_ctrl_iface_receive,global, priv); 【3】

	return 0;

fail:
	if (priv->sock >= 0) {
		close(priv->sock);
		priv->sock = -1;
	}
	return -1;
}


```

## android_get_control_socket
```
获取 init_xx.rc 中配置为 @android:vendor_wpa_wlan0 Socket 的 句柄 

http://androidxref.com/9.0.0_r3/xref/system/core/libcutils/sockets_unix.cpp#97

http://eeepage.info/android_get_control_socket/


        // 尝试打开 socket 句柄 以  @android:vendor_wpa_wlan0 为参数
		priv->sock = android_get_control_socket(ctrl);  


android_get_control_socket 定义在 /system/core/include/cutils/sockets.h

android_get_control_socket  简单的辅助功能,让我初始化 unix_domain_socket socket文件描述符
android_get_control_socket(char* name) 是socket的名字, 可以在 init_xx.rc 文件中找到
返回 -1  表示socket创建失败 



int android_get_control_socket(const char* name) {
    int fd = __android_get_control_from_env(ANDROID_SOCKET_ENV_PREFIX, name);  // #define ANDROID_SOCKET_ENV_PREFIX "ANDROID_SOCKET_"   获取句柄函数
    if (fd < 0) return fd;

    // Compare to UNIX domain socket name, must match!
    struct sockaddr_un addr;
    socklen_t addrlen = sizeof(addr);
    int ret = TEMP_FAILURE_RETRY(getsockname(fd, (struct sockaddr *)&addr, &addrlen));
    if (ret < 0) return -1;
    char *path = NULL;
    if (asprintf(&path, ANDROID_SOCKET_DIR "/%s", name) < 0) return -1;
    if (!path) return -1;
    int cmp = strcmp(addr.sun_path, path);
    free(path);
    if (cmp != 0) return -1;

    // It is what we think it is
    return fd;
}






```
## fcntl(priv->sock, F_GETFL)
```
int flags = fcntl(priv->sock, F_GETFL);  // 取得文件描述词状态旗标

http://net.pku.edu.cn/~yhf/linux_c/function/09.html#linuxc137

表头文件             #include<unistd.h>                  #include<fcntl.h>
定义函数
             int fcntl(int fd , int cmd);
             int fcntl(int fd,int cmd,long arg);
             int fcntl(int fd,int cmd,struct flock * lock);
函数说明
fcntl()用来操作文件描述词的一些特性。参数fd代表欲设置的文件描述词，参数cmd代表欲操作的指令。
有以下几种情况:
F_DUPFD用来查找大于或等于参数arg的最小且仍未使用的文件描述词，并且复制参数fd的文件描述词。执行成功则返回新复制的文件描述词。请参考dup2()。
F_GETFD 取得close-on-exec旗标。若此旗标的FD_CLOEXEC位为0，代表在调用exec()相关函数时文件将不会关闭。
F_SETFD 设置close-on-exec 旗标。该旗标以参数arg 的FD_CLOEXEC位决定。
F_GETFL 取得文件描述词状态旗标，此旗标为open（）的参数flags。
F_SETFL 设置文件描述词状态旗标，参数arg为新旗标，但只允许O_APPEND、O_NONBLOCK和O_ASYNC位的改变，其他位的改变将不受影响。
F_GETLK 取得文件锁定的状态。
F_SETLK 设置文件锁定的状态。此时flcok 结构的l_type 值必须是F_RDLCK、F_WRLCK或F_UNLCK。如果无法建立锁定，则返回-1，错误代码为EACCES 或EAGAIN。
F_SETLKW F_SETLK 作用相同，但是无法建立锁定时，此调用会一直等到锁定动作成功为止。若在等待锁定的过程中被信号中断时，会立即返回-1，错误代码为EINTR。参数lock指针为flock 结构指针
```

## eloop_register_read_sock(priv->sock,wpa_supplicant_global_ctrl_iface_receive）
```
设置 @android:vendor_wpa_wlan0  这个 eloop_data 中  read-table 中 一个 socket-item【vendor_wpa_wlan0】的处理函数  wpa_supplicant_global_ctrl_iface_receive


eloop_register_read_sock(priv->sock,wpa_supplicant_global_ctrl_iface_receive 【1】,global, priv); 

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#711

int eloop_register_read_sock(int sock, eloop_sock_handler handler,void *eloop_data, void *user_data)
{
	return eloop_register_sock(sock, EVENT_TYPE_READ, handler,   eloop_data, user_data); 
}


http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/eloop.c#740
int eloop_register_sock(int sock, eloop_event_type type,eloop_sock_handler handler,void *eloop_data, void *user_data)
{
	struct eloop_sock_table *table;

	assert(sock >= 0);
	table = eloop_get_sock_table(type);
	return eloop_sock_table_add_sock(table, sock, handler, eloop_data, user_data);   
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

### wpa_supplicant_global_ctrl_iface_receive
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#1044


/* Global ctrl_iface */

static void wpa_supplicant_global_ctrl_iface_receive(int sock, void *eloop_ctx, void *sock_ctx)
{
	struct wpa_global *global = eloop_ctx;
	struct ctrl_iface_global_priv *priv = sock_ctx;
	char buf[4096];
	int res;
	struct sockaddr_storage from;
	socklen_t fromlen = sizeof(from);
	char *reply = NULL, *reply_buf = NULL;
	size_t reply_len;

	res = recvfrom(sock, buf, sizeof(buf) - 1, 0, (struct sockaddr *) &from, &fromlen); 【1】 （经socket接收数据）
	if (res < 0) {
		wpa_printf(MSG_ERROR, "recvfrom(ctrl_iface): %s",  strerror(errno));
		return;
	}
	buf[res] = '\0';  // 设置字符串终结符 \0

	if (os_strcmp(buf, "ATTACH") == 0) {
		if (wpa_supplicant_ctrl_iface_attach(&priv->ctrl_dst, &from, fromlen, 1))  【2】 如果命令为 ATTACH 那么执行函数 wpa_supplicant_ctrl_iface_attach  // ctrl_dst为 控制双向链表
			reply_len = 1;
		else
			reply_len = 2;
	} else if (os_strcmp(buf, "DETACH") == 0) { 
		if (wpa_supplicant_ctrl_iface_detach(&priv->ctrl_dst, &from,fromlen))      【3】如果命令为 DETACH 那么执行函数 wpa_supplicant_ctrl_iface_detach
			reply_len = 1;
		else
			reply_len = 2;
	} else {
		reply_buf = wpa_supplicant_global_ctrl_iface_process(global, buf, &reply_len); 【4】如果命令不为 ATTACH 和 DETACH 那么执行函数 wpa_supplicant_global_ctrl_iface_process
		reply = reply_buf;

		/*
		 * There could be some password/key material in the command, so
		 * clear the buffer explicitly now that it is not needed
		 * anymore.
		 */
		os_memset(buf, 0, res);
	}

	if (!reply && reply_len == 1) {
		reply = "FAIL\n";
		reply_len = 5;
	} else if (!reply && reply_len == 2) {
		reply = "OK\n";
		reply_len = 3;
	}

	if (reply) {
		wpas_ctrl_sock_debug("global_ctrl_sock-sendto",sock, reply, reply_len);
		if (sendto(sock, reply, reply_len, 0, (struct sockaddr *) &from, fromlen) < 0) {
			wpa_printf(MSG_DEBUG, "ctrl_iface sendto failed: %s",strerror(errno));
		}
	}
	os_free(reply_buf);
}


```

#### recvfrom(sock, buf, sizeof(buf) - 1 ,0）
```
http://net.pku.edu.cn/~yhf/linux_c/function/14.html#linuxc278

recvfrom（经socket接收数据）
相关函数   recv，recvmsg，send，sendto，socket
表头文件          #include<sys/types.h>              #include<sys/socket.h>
定义函数          int recvfrom(int s,void *buf,int len,unsigned int flags ,struct sockaddr *from ,int *fromlen);
函数说明
recv()用来接收远程主机经指定的socket 传来的数据，并把数据存到由参数buf 指向的内存空间，参数len 为可接收数据的最大长度。
参数flags 一般设0，其他数值定义请参考recv()。参数from用来指定欲传送的网络地址，结构sockaddr 请参考bind()。参数fromlen为sockaddr的结构长度。

返回值     成功则返回接收到的字符数，失败则返回-1，错误原因存于errno中。
错误代码
EBADF    参数s非合法的socket处理代码
EFAULT   参数中有一指针指向无法存取的内存空间。
ENOTSOCK 参数s为一文件描述词，非socket。
EINTR    被信号所中断。
EAGAIN   此动作会令进程阻断，但参数s的socket为不可阻断。
ENOBUFS  系统的缓冲内存不足
ENOMEM   核心内存不足
EINVAL   传给系统调用的参数不正确。


```

#### wpa_supplicant_ctrl_iface_attach
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#102

static int wpa_supplicant_ctrl_iface_attach(struct dl_list *ctrl_dst,struct sockaddr_storage *from, socklen_t fromlen, int global)
{
	return ctrl_iface_attach(ctrl_dst, from, fromlen);
}


http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/ctrl_iface_common.c#116

int ctrl_iface_attach(struct dl_list *ctrl_dst, struct sockaddr_storage *from, socklen_t fromlen)
{
	struct wpa_ctrl_dst *dst;

	dst = os_zalloc(sizeof(*dst));
	if (dst == NULL)
		return -1;
	os_memcpy(&dst->addr, from, fromlen);
	dst->addrlen = fromlen;
	dst->debug_level = MSG_INFO;
	dl_list_add(ctrl_dst, &dst->list); 【1】

	sockaddr_print(MSG_DEBUG, "CTRL_IFACE monitor attached", from, fromlen);
	return 0;
}



```

##### dl_list_add
```
dl_list_add(struct dl_list ctrl_dst 【主链表】,struct dl_list &dst->list【待添加链表结点】); 

把控制接口加入双向链表中
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/list.h#28


static inline void dl_list_add(struct dl_list *list, struct dl_list *item)
{
	item->next = list->next;
	item->prev = list;
	list->next->prev = item;
	list->next = item;
}


```

#### wpa_supplicant_ctrl_iface_detach
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#110

wpa_supplicant_ctrl_iface_detach(&priv->ctrl_dst, &from,fromlen)

static int wpa_supplicant_ctrl_iface_detach(struct dl_list *ctrl_dst,struct sockaddr_storage *from,socklen_t fromlen)
{
	return ctrl_iface_detach(ctrl_dst, from, fromlen);
}


http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/ctrl_iface_common.c#134


int ctrl_iface_detach(struct dl_list *ctrl_dst, struct sockaddr_storage *from,socklen_t fromlen)
{
	struct wpa_ctrl_dst *dst;


// ##   for (dst = dl_list_entry((ctrl_dst)->next, wpa_ctrl_dst, list); &dst->list != (ctrl_dst);  dst = dl_list_entry(dst->list.next, wpa_ctrl_dst, list))##
	dl_list_for_each(dst, ctrl_dst, struct wpa_ctrl_dst, list) {        // 【1】  遍历双向链表
		if (!sockaddr_compare(from, fromlen, &dst->addr, dst->addrlen)) {    // 【2】 socket 地址进行判断? 
			sockaddr_print(MSG_DEBUG, "CTRL_IFACE monitor detached",  from, fromlen);  
			dl_list_del(&dst->list);    //   【3】  从双向链表中删除一个 item
			os_free(dst);
			return 0;
		}
	}

	return -1;
}

```
##### dl_list_for_each
```
dl_list_for_each 是一个 for(;;)  条件的宏 
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/utils/list.h#78

#define dl_list_for_each(item, list, type, member) 
 for (item = dl_list_entry((list)->next, type, member); &item->member != (list);  item = dl_list_entry 【1】(item->member.next, type, member))


	dl_list_for_each(dst, ctrl_dst, struct wpa_ctrl_dst, list) {       
		if (!sockaddr_compare(from, fromlen, &dst->addr, dst->addrlen)) {   
			sockaddr_print(MSG_DEBUG, "CTRL_IFACE monitor detached",  from, fromlen);  
			dl_list_del(&dst->list);  
			os_free(dst);
			return 0;
		}
	}

等于


 for (dst = dl_list_entry((ctrl_dst)->next, wpa_ctrl_dst, list); &dst->list != (ctrl_dst);  dst = dl_list_entry(dst->list.next, wpa_ctrl_dst, list))
		if (!sockaddr_compare(from, fromlen, &dst->addr, dst->addrlen)) {   
			sockaddr_print(MSG_DEBUG, "CTRL_IFACE monitor detached",  from, fromlen);  
			dl_list_del(&dst->list);  
			os_free(dst);
			return 0;
		}
	}

```
###### dl_list_entry
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/utils/list.h#67
#define dl_list_entry(item, type, member)  ((type *) ((char *) item - offsetof(type, member)))   // 通过链表获得包含该链表的那个数据结构体


```

##### sockaddr_compare
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/ctrl_iface_common.c#17

要打Log 查看  貌似执行不到这里

static int sockaddr_compare(struct sockaddr_storage *a, socklen_t a_len,struct sockaddr_storage *b, socklen_t b_len)
{
	if (a->ss_family != b->ss_family)
		return 1;

	switch (a->ss_family) {
#ifdef CONFIG_CTRL_IFACE_UDP
	case AF_INET:
	{
		struct sockaddr_in *in_a, *in_b;

		in_a = (struct sockaddr_in *) a;
		in_b = (struct sockaddr_in *) b;

		if (in_a->sin_port != in_b->sin_port)
			return 1;
		if (in_a->sin_addr.s_addr != in_b->sin_addr.s_addr)
			return 1;
		break;
	}
	case AF_INET6:
	{
		struct sockaddr_in6 *in6_a, *in6_b;

		in6_a = (struct sockaddr_in6 *) a;
		in6_b = (struct sockaddr_in6 *) b;

		if (in6_a->sin6_port != in6_b->sin6_port)
			return 1;
		if (os_memcmp(&in6_a->sin6_addr, &in6_b->sin6_addr,
			      sizeof(in6_a->sin6_addr)) != 0)
			return 1;
		break;
	}
#endif /* CONFIG_CTRL_IFACE_UDP */
#ifdef CONFIG_CTRL_IFACE_UNIX
	case AF_UNIX:
	{
		struct sockaddr_un *u_a, *u_b;

		u_a = (struct sockaddr_un *) a;
		u_b = (struct sockaddr_un *) b;

		if (a_len != b_len ||
		    os_memcmp(u_a->sun_path, u_b->sun_path,
			      a_len - offsetof(struct sockaddr_un, sun_path))
		    != 0)
			return 1;
		break;
	}
#endif /* CONFIG_CTRL_IFACE_UNIX */
	default:
		return 1;
	}

	return 0;
}



```


#####  dl_list_del
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/list.h#41



static inline void dl_list_del(struct dl_list *item)
{
	item->next->prev = item->prev;
	item->prev->next = item->next;
	item->next = NULL;
	item->prev = NULL;
}

```


#### wpa_supplicant_global_ctrl_iface_process
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface.c#11084

char * wpa_supplicant_global_ctrl_iface_process(struct wpa_global *global,char *buf, size_t *resp_len)
{
	char *reply;
	const int reply_size = 2048;
	int reply_len;
	int level = MSG_DEBUG;

	if (os_strncmp(buf, "IFNAME=", 7) == 0) {     // 判断 buf 字符串命令 是以 IFNAME= 为开头的话
		char *pos = os_strchr(buf + 7, ' ');
		if (pos) {
			*pos++ = '\0';
			return wpas_global_ctrl_iface_ifname(global, buf + 7, pos, resp_len);   【1】
		}
	}

	reply = wpas_global_ctrl_iface_redir(global, buf, resp_len);   【2】
	if (reply)
		return reply;

	if (os_strcmp(buf, "PING") == 0)
		level = MSG_EXCESSIVE;
	wpa_hexdump_ascii(level, "RX global ctrl_iface", (const u8 *) buf, os_strlen(buf)); 【3】

	reply = os_malloc(reply_size);
	if (reply == NULL) {
		*resp_len = 1;
		return NULL;
	}

	os_memcpy(reply, "OK\n", 3);
	reply_len = 3;

	if (os_strcmp(buf, "PING") == 0) {  // 如果命令是 PING  
		os_memcpy(reply, "PONG\n", 5);
		reply_len = 5;
	} else if (os_strncmp(buf, "INTERFACE_ADD ", 14) == 0) {   // 如果命令是 INTERFACE_ADD
		if (wpa_supplicant_global_iface_add(global, buf + 14))     【4】
			reply_len = -1;
	} else if (os_strncmp(buf, "INTERFACE_REMOVE ", 17) == 0) {   // 如果命令是  INTERFACE_REMOVE
		if (wpa_supplicant_global_iface_remove(global, buf + 17))     【5】
			reply_len = -1;
	} else if (os_strcmp(buf, "INTERFACE_LIST") == 0) {            //  如果命令是 INTERFACE_LIST 
		reply_len = wpa_supplicant_global_iface_list(global, reply, reply_size);    【6】

	} else if (os_strncmp(buf, "INTERFACES", 10) == 0) {            //  如果命令是 INTERFACES
		reply_len = wpa_supplicant_global_iface_interfaces(global, buf + 10, reply, reply_size);    【7】

	} else if (os_strncmp(buf, "FST-ATTACH ", 11) == 0) {             // 如果命令是  FST-ATTACH
		reply_len = wpas_global_ctrl_iface_fst_attach(global, buf + 11,reply,reply_size);    【8】

	} else if (os_strncmp(buf, "FST-DETACH ", 11) == 0) {                // 如果命令是 FST-DETACH
		reply_len = wpas_global_ctrl_iface_fst_detach(global, buf + 11,reply,reply_size);  【9】

	} else if (os_strncmp(buf, "FST-MANAGER ", 12) == 0) {                //  如果命令是 FST-MANAGER
		reply_len = fst_ctrl_iface_receive(buf + 12, reply, reply_size);             【10 】
	} else if (os_strcmp(buf, "TERMINATE") == 0) {    //  如果命令是 TERMINATE
		wpa_supplicant_terminate_proc(global);                      【11】
	} else if (os_strcmp(buf, "SUSPEND") == 0) {   //  如果命令是 SUSPEND
		wpas_notify_suspend(global);                  【12】
	} else if (os_strcmp(buf, "RESUME") == 0) {   //  如果命令是 RESUME
		wpas_notify_resume(global);                   【13】
	} else if (os_strncmp(buf, "SET ", 4) == 0) {   //  如果命令是 SET
		if (wpas_global_ctrl_iface_set(global, buf + 4)) {               【14】
			if (global->p2p_init_wpa_s) {
				os_free(reply);

				/* Check if P2P redirection would work for thiscommand. */
				return wpa_supplicant_ctrl_iface_process(global->p2p_init_wpa_s,buf, resp_len);    【15】
			}
			reply_len = -1;
		}
	} else if (os_strncmp(buf, "DUP_NETWORK ", 12) == 0) {        //  如果命令是 DUP_NETWORK
		if (wpas_global_ctrl_iface_dup_network(global, buf + 12))            【16】
			reply_len = -1;
	} else if (os_strcmp(buf, "SAVE_CONFIG") == 0) {    //  如果命令是 SAVE_CONFIG
		if (wpas_global_ctrl_iface_save_config(global))                     【17】
			reply_len = -1;
	} else if (os_strcmp(buf, "STATUS") == 0) {         //  如果命令是 STATUS
		reply_len = wpas_global_ctrl_iface_status(global, reply, reply_size);          【18】

	} else if (os_strncmp(buf, "RELOG", 5) == 0) {    //  如果命令是 RELOG
		if (wpa_debug_reopen_file() < 0)                                      【19】
			reply_len = -1;
	} else {
		os_memcpy(reply, "UNKNOWN COMMAND\n", 16);
		reply_len = 16;
	}

	if (reply_len < 0) {
		os_memcpy(reply, "FAIL\n", 5);
		reply_len = 5;
	}

	*resp_len = reply_len;
	return reply;
}



```

##### wpas_global_ctrl_iface_ifname
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface.c#10746



static char * wpas_global_ctrl_iface_ifname(struct wpa_global *global, const char *ifname, char *cmd, size_t *resp_len)
{
	struct wpa_supplicant *wpa_s;

// 查询所有保存在 wpa_global->ifaces 【wpa_supplicant *ifaces】中的wpa_supplicant数组, 查看其中的 wpa_supplicant.ifname 【 char ifname[100]; 】 是否与参数ifname相等
// 如果相等 就跳出循环   此时 临时变量  wpa_supplicant *wpa_s;  就指向了 参数 ifname 确定的  wpa_supplicant 结构
	for (wpa_s = global->ifaces; wpa_s; wpa_s = wpa_s->next) {  
		if (os_strcmp(ifname, wpa_s->ifname) == 0)
			break;
	}

	if (wpa_s == NULL) {   // 如果查找的 wpa_s 为空 说明 在 wpa_global->ifaces 【wpa_supplicant *ifaces】中 未找到参数指定的那个 wpa_supplicant
		char *resp = os_strdup("FAIL-NO-IFNAME-MATCH\n");
		if (resp)
			*resp_len = os_strlen(resp);
		else
			*resp_len = 1;
		return resp;
	}

	return wpa_supplicant_ctrl_iface_process(wpa_s, cmd, resp_len);   // 【1】 在 该 wpa_supplicant 处理该命令
}





```

###### wpa_supplicant_ctrl_iface_process()
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface.c#wpas_global_ctrl_iface_ifname

详情见wpa_supplicant_ctrl_iface_process 长函数分析
```

##### wpas_global_ctrl_iface_redir
##### wpa_hexdump_ascii
##### wpa_supplicant_global_iface_add
##### wpa_supplicant_global_iface_remove
##### wpa_supplicant_global_iface_list
##### wpa_supplicant_global_iface_interfaces
##### wpas_global_ctrl_iface_fst_attach
##### wpas_global_ctrl_iface_fst_detach
##### fst_ctrl_iface_receive
##### wpa_supplicant_terminate_proc
##### wpas_notify_suspend
##### wpas_notify_resume
##### wpas_global_ctrl_iface_set
##### wpa_supplicant_ctrl_iface_process
##### wpas_global_ctrl_iface_dup_network
##### wpas_global_ctrl_iface_save_config
##### wpas_global_ctrl_iface_status
##### wpa_debug_reopen_file


# wpa_supplicant_ctrl_iface_process 长函数分析
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface.c#wpas_global_ctrl_iface_ifname



char * wpa_supplicant_ctrl_iface_process(struct wpa_supplicant *wpa_s, char *buf, size_t *resp_len)
{
	char *reply;
	const int reply_size = 4096;
	int reply_len;

// 如果命令 buf 以 "CTRL-RSP-"   "SET_NETWORK "   "PMKSA_ADD "    "MESH_PMKSA_ADD "  开头 那么进入 if语句
	if (os_strncmp(buf, WPA_CTRL_RSP【"CTRL-RSP-"】, os_strlen(WPA_CTRL_RSP【CTRL-RSP-】)) == 0 || os_strncmp(buf, "SET_NETWORK ", 12) == 0 ||
	    os_strncmp(buf, "PMKSA_ADD ", 10) == 0 || os_strncmp(buf, "MESH_PMKSA_ADD ", 15) == 0) {
		if (wpa_debug_show_keys)  // int wpa_debug_show_keys = 0;  是否打印具体的命令的开关  打印命令
			wpa_dbg(wpa_s, MSG_DEBUG,"Control interface command '%s'", buf);
		else
			wpa_dbg(wpa_s, MSG_DEBUG,"Control interface command '%s [REMOVED]'",
           os_strncmp(buf, WPA_CTRL_RSP, os_strlen(WPA_CTRL_RSP)) == 0 ?	WPA_CTRL_RSP :	(os_strncmp(buf, "SET_NETWORK ", 12) == 0 ? "SET_NETWORK" : "key-add"));
	} else if (os_strncmp(buf, "WPS_NFC_TAG_READ", 16) == 0 || os_strncmp(buf, "NFC_REPORT_HANDOVER", 19) == 0) {
// 如果命令 buf 以   "WPS_NFC_TAG_READ"    "NFC_REPORT_HANDOVER" 开头
		wpa_hexdump_ascii_key(MSG_DEBUG, "RX ctrl_iface",(const u8 *) buf, os_strlen(buf));   【1】
	} else { // 其余命令执行以下code
		int level = wpas_ctrl_cmd_debug_level(buf);    【2】
		wpa_dbg(wpa_s, level, "Control interface command '%s'", buf);   // 打印Log  【3】
	}



	reply = os_malloc(reply_size);
	if (reply == NULL) {
		*resp_len = 1;
		return NULL;
	}

	os_memcpy(reply, "OK\n", 3);
	reply_len = 3;

	if (os_strcmp(buf, "PING") == 0) {   // 如果命令 buf 是 PING  那么回复 PING 命令
		os_memcpy(reply, "PONG\n", 5);
		reply_len = 5;
	} else if (os_strcmp(buf, "IFNAME") == 0) {  // 如果命令 buf 是 IFNAME  那么回复 wpa_s->ifname (wlan0)的名字  接口名字
		reply_len = os_strlen(wpa_s->ifname);
		os_memcpy(reply, wpa_s->ifname, reply_len);
	} else if (os_strncmp(buf, "RELOG", 5) == 0) {  // 如果命令 buf 是 RELOG  那么执行 函数
		if (wpa_debug_reopen_file() < 0)         【4】
			reply_len = -1;
	} else if (os_strncmp(buf, "NOTE ", 5) == 0) {  // 需要有命令参数输入
		wpa_printf(MSG_INFO, "NOTE: %s", buf + 5);
	} else if (os_strcmp(buf, "MIB") == 0) {  // 如果 命令 buf  是 MIB  那么执行 函数 返回MIB信息
		reply_len = wpa_sm_get_mib(wpa_s->wpa, reply, reply_size);  【5】
		if (reply_len >= 0) {
			reply_len += eapol_sm_get_mib(wpa_s->eapol,reply + reply_len,  reply_size - reply_len);  【6】
		}
	} else if (os_strncmp(buf, "STATUS", 6) == 0) { // 如果 命令 buf  是 STATUS  那么执行函数 
		reply_len = wpa_supplicant_ctrl_iface_status(wpa_s, buf + 6, reply, reply_size);   【7】

	} else if (os_strcmp(buf, "PMKSA") == 0) {     // 如果 命令 buf  是 PMKSA  那么执行函数 
		reply_len = wpas_ctrl_iface_pmksa(wpa_s, reply, reply_size);   【8】

	} else if (os_strcmp(buf, "PMKSA_FLUSH") == 0) {// 如果 命令 buf  是 PMKSA_FLUSH  那么执行函数 
		wpas_ctrl_iface_pmksa_flush(wpa_s);          【9】

	} else if (os_strncmp(buf, "SET ", 4) == 0) {    // 如果 命令 buf  是 SET 那么执行函数   需要有命令参数输入 key value  ,例如 set passive_scan 1
		if (wpa_supplicant_ctrl_iface_set(wpa_s, buf + 4))  【10】
			reply_len = -1;

	} else if (os_strncmp(buf, "DUMP", 4) == 0) {   // 如果 命令 buf  是 DUMP  那么执行函数 
		reply_len = wpa_config_dump_values(wpa_s->conf,  reply, reply_size); 【11】

	} else if (os_strncmp(buf, "GET ", 4) == 0) {   // 如果 命令 buf  是 GET  那么执行函数 
		reply_len = wpa_supplicant_ctrl_iface_get(wpa_s, buf + 4, reply, reply_size);   【12】

	} else if (os_strcmp(buf, "LOGON") == 0) {   // 如果 命令 buf  是 LOGON  那么执行函数 
		eapol_sm_notify_logoff(wpa_s->eapol, FALSE);  【13】

	} else if (os_strcmp(buf, "LOGOFF") == 0) {      // 如果 命令 buf  是 LOGOFF  那么执行函数 
		eapol_sm_notify_logoff(wpa_s->eapol, TRUE);   【14】

	} else if (os_strcmp(buf, "REASSOCIATE") == 0) {   // 如果 命令 buf  是 REASSOCIATE  那么执行函数 
		if (wpa_s->wpa_state == WPA_INTERFACE_DISABLED)  
			reply_len = -1;
		else
			wpas_request_connection(wpa_s);   【15】
	} else if (os_strcmp(buf, "REATTACH") == 0) {      // 如果 命令 buf  是 REATTACH  那么执行函数 
		if (wpa_s->wpa_state == WPA_INTERFACE_DISABLED ||  !wpa_s->current_ssid)
			reply_len = -1;
		else {
			wpa_s->reattach = 1;
			wpas_request_connection(wpa_s);
		}
	} else if (os_strcmp(buf, "RECONNECT") == 0) { // 如果 命令 buf  是 RECONNECT  那么执行函数 
		if (wpa_s->wpa_state == WPA_INTERFACE_DISABLED)
			reply_len = -1;
		else if (wpa_s->disconnected)
			wpas_request_connection(wpa_s);

	} else if (os_strncmp(buf, "PREAUTH ", 8) == 0) { // 如果 命令 buf  是 PREAUTH  那么执行函数   需要输入命令参数
		if (wpa_supplicant_ctrl_iface_preauth(wpa_s, buf + 8))   【16】
			reply_len = -1;
	} else if (os_strncmp(buf, "FT_DS ", 6) == 0) {  // 如果 命令 buf  是 FT_DS  那么执行函数   需要输入命令参数
		if (wpa_supplicant_ctrl_iface_ft_ds(wpa_s, buf + 6))   【17】
			reply_len = -1;

	} else if (os_strcmp(buf, "WPS_PBC") == 0) { // 如果 命令 buf  是 [WPS_PBC]  那么执行函数 
		int res = wpa_supplicant_ctrl_iface_wps_pbc(wpa_s, NULL);  【18】
		if (res == -2) {
			os_memcpy(reply, "FAIL-PBC-OVERLAP\n", 17);
			reply_len = 17;
		} else if (res)
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_PBC ", 8) == 0) {  // 如果 命令 buf  是 [WPS_PBC 输入参数] 那么执行函数 
		int res = wpa_supplicant_ctrl_iface_wps_pbc(wpa_s, buf + 8);
		if (res == -2) {
			os_memcpy(reply, "FAIL-PBC-OVERLAP\n", 17);
			reply_len = 17;
		} else if (res)
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_PIN ", 8) == 0) {   // 如果 命令 buf  是WPS_PIN  那么执行函数    需要输入命令参数
		reply_len = wpa_supplicant_ctrl_iface_wps_pin(wpa_s, buf + 8,reply,reply_size);  【19】

	} else if (os_strncmp(buf, "WPS_CHECK_PIN ", 14) == 0) {   // 如果 命令 buf  是 WPS_CHECK_PIN  那么执行函数    需要输入命令参数
		reply_len = wpa_supplicant_ctrl_iface_wps_check_pin(wpa_s, buf + 14, reply, reply_size); 【20】

	} else if (os_strcmp(buf, "WPS_CANCEL") == 0) {   // 如果 命令 buf  是 WPS_CANCEL  那么执行函数 
		if (wpas_wps_cancel(wpa_s))  【21】
			reply_len = -1;

	} else if (os_strcmp(buf, "WPS_NFC") == 0) {     // 如果 命令 buf  是 WPS_NFC  那么执行函数 
		if (wpa_supplicant_ctrl_iface_wps_nfc(wpa_s, NULL))  【22】
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_NFC ", 8) == 0) {
		if (wpa_supplicant_ctrl_iface_wps_nfc(wpa_s, buf + 8))
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_NFC_CONFIG_TOKEN ", 21) == 0) {  // 如果 命令 buf  是 WPS_NFC_CONFIG_TOKEN  那么执行函数 
		reply_len = wpa_supplicant_ctrl_iface_wps_nfc_config_token(wpa_s, buf + 21, reply, reply_size);      【23】

	} else if (os_strncmp(buf, "WPS_NFC_TOKEN ", 14) == 0) {   // 如果 命令 buf  是 WPS_NFC_TOKEN  那么执行函数   需要输入命令参数
		reply_len = wpa_supplicant_ctrl_iface_wps_nfc_token(wpa_s, buf + 14, reply, reply_size);     【24】

	} else if (os_strncmp(buf, "WPS_NFC_TAG_READ ", 17) == 0) {   // 如果 命令 buf  是 WPS_NFC_TAG_READ  那么执行函数   需要输入命令参数
		if (wpa_supplicant_ctrl_iface_wps_nfc_tag_read(wpa_s, buf + 17))   【25】
			reply_len = -1;
	} else if (os_strncmp(buf, "NFC_GET_HANDOVER_REQ ", 21) == 0) {    // 如果 命令 buf  是 NFC_GET_HANDOVER_REQ  那么执行函数   需要输入2个命令参数
		reply_len = wpas_ctrl_nfc_get_handover_req(wpa_s, buf + 21, reply, reply_size);  【26】

	} else if (os_strncmp(buf, "NFC_GET_HANDOVER_SEL ", 21) == 0) {   // 如果 命令 buf  是 NFC_GET_HANDOVER_SEL  那么执行函数   需要输入2个命令参数
		reply_len = wpas_ctrl_nfc_get_handover_sel(wpa_s, buf + 21, reply, reply_size);   【27】

	} else if (os_strncmp(buf, "NFC_REPORT_HANDOVER ", 20) == 0) {  // 如果 命令 buf  是 NFC_REPORT_HANDOVER  那么执行函数   需要输入4个命令参数
		if (wpas_ctrl_nfc_report_handover(wpa_s, buf + 20))                【28】
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_REG ", 8) == 0) {   // 如果 命令 buf  是 WPS_REG  那么执行函数   需要输入4个命令参数
		if (wpa_supplicant_ctrl_iface_wps_reg(wpa_s, buf + 8))   【29】
			reply_len = -1;


	} else if (os_strcmp(buf, "WPS_ER_START") == 0) {  // 如果 命令 buf  是 WPS_ER_START  那么执行函数   可选输入参数命令
		if (wpas_wps_er_start(wpa_s, NULL))        【30】
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_ER_START ", 13) == 0) { 
		if (wpas_wps_er_start(wpa_s, buf + 13))
			reply_len = -1;
	} else if (os_strcmp(buf, "WPS_ER_STOP") == 0) {  // 如果 命令 buf  是 WPS_ER_STOP  那么执行函数   可选输入参数命令
		wpas_wps_er_stop(wpa_s);                               【31】
	} else if (os_strncmp(buf, "WPS_ER_PIN ", 11) == 0) {            // 如果 命令 buf  是 WPS_ER_PIN  那么执行函数   可选输入参数命令
		if (wpa_supplicant_ctrl_iface_wps_er_pin(wpa_s, buf + 11))              【32】    
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_ER_PBC ", 11) == 0) {     // 如果 命令 buf  是 WPS_ER_PIN  那么执行函数    需要输入1个命令参数
		int ret = wpas_wps_er_pbc(wpa_s, buf + 11);             【33】    
		if (ret == -2) {
			os_memcpy(reply, "FAIL-PBC-OVERLAP\n", 17);
			reply_len = 17;
		} else if (ret == -3) {
			os_memcpy(reply, "FAIL-UNKNOWN-UUID\n", 18);
			reply_len = 18;
		} else if (ret == -4) {
			os_memcpy(reply, "FAIL-NO-AP-SETTINGS\n", 20);
			reply_len = 20;
		} else if (ret)
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_ER_LEARN ", 13) == 0) {  // 如果 命令 buf  是 WPS_ER_LEARN  那么执行函数    需要输入2个命令参数
		if (wpa_supplicant_ctrl_iface_wps_er_learn(wpa_s, buf + 13))   【34】
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_ER_SET_CONFIG ", 18) == 0) {    // 如果 命令 buf  是 WPS_ER_LEARN  那么执行函数    需要输入2个命令参数
		if (wpa_supplicant_ctrl_iface_wps_er_set_config(wpa_s,buf + 18))    【35】
			reply_len = -1;
	} else if (os_strncmp(buf, "WPS_ER_CONFIG ", 14) == 0) {   // 如果 命令 buf  是 WPS_ER_CONFIG  那么执行函数    需要输入6个命令参数
		if (wpa_supplicant_ctrl_iface_wps_er_config(wpa_s, buf + 14))   【36】
			reply_len = -1;

	} else if (os_strncmp(buf, "WPS_ER_NFC_CONFIG_TOKEN ", 24) == 0) {   // 如果 命令 buf  是 WPS_ER_NFC_CONFIG_TOKEN  那么执行函数    需要输入2个命令参数 
		reply_len = wpa_supplicant_ctrl_iface_wps_er_nfc_config_token(wpa_s, buf + 24, reply, reply_size);     【37】

	} else if (os_strncmp(buf, "IBSS_RSN ", 9) == 0) { // 如果 命令 buf  是 IBSS_RSN  那么执行函数    需要输入1个命令参数  奇怪 打印 UNKNOW_COMMAND
		if (wpa_supplicant_ctrl_iface_ibss_rsn(wpa_s, buf + 9))       【38】
			reply_len = -1;

	} else if (os_strncmp(buf, "MESH_INTERFACE_ADD ", 19) == 0) { // 如果 命令 buf  是 MESH_INTERFACE_ADD  那么执行函数   可带参数
		reply_len = wpa_supplicant_ctrl_iface_mesh_interface_add(wpa_s, buf + 19, reply, reply_size);     【39】
	} else if (os_strcmp(buf, "MESH_INTERFACE_ADD") == 0) {
		reply_len = wpa_supplicant_ctrl_iface_mesh_interface_add(wpa_s, "", reply, reply_size);
	} else if (os_strncmp(buf, "MESH_GROUP_ADD ", 15) == 0) {       // 如果 命令 buf  是 MESH_GROUP_ADD  那么执行函数    需要输入1个命令参数 
		if (wpa_supplicant_ctrl_iface_mesh_group_add(wpa_s, buf + 15))  【168】
			reply_len = -1;
	} else if (os_strncmp(buf, "MESH_GROUP_REMOVE ", 18) == 0) {   // 如果 命令 buf  是 MESH_GROUP_ADD  那么执行函数    需要输入1个命令参数 
		if (wpa_supplicant_ctrl_iface_mesh_group_remove(wpa_s,buf + 18))    【 40 】
			reply_len = -1;
	} else if (os_strncmp(buf, "MESH_PEER_REMOVE ", 17) == 0) {     // 如果 命令 buf  是 MESH_PEER_REMOVE  那么执行函数    需要输入1个命令参数 
		if (wpa_supplicant_ctrl_iface_mesh_peer_remove(wpa_s, buf + 17))        【 41 】
			reply_len = -1;
	} else if (os_strncmp(buf, "MESH_PEER_ADD ", 14) == 0) {   // 如果 命令 buf  是 MESH_PEER_ADD  那么执行函数    需要输入1个命令参数 
		if (wpa_supplicant_ctrl_iface_mesh_peer_add(wpa_s, buf + 14))   【 42 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_FIND ", 9) == 0) {  // 如果 命令 buf  是 P2P_FIND  那么执行函数    可选带参数
		if (p2p_ctrl_find(wpa_s, buf + 8))      【 43 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_FIND") == 0) {   
		if (p2p_ctrl_find(wpa_s, ""))
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_STOP_FIND") == 0) {   // 如果 命令 buf  是 P2P_STOP_FIND  那么执行函数    可选带参数
		wpas_p2p_stop_find(wpa_s);                    【 44 】
	} else if (os_strncmp(buf, "P2P_ASP_PROVISION ", 18) == 0) {   // 如果 命令 buf  是 P2P_ASP_PROVISION  那么执行函数     需要输入3个命令参数 
		if (p2p_ctrl_asp_provision(wpa_s, buf + 18))                      【 45 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_ASP_PROVISION_RESP ", 23) == 0) {  // 如果 命令 buf  是 P2P_ASP_PROVISION_RESP  那么执行函数     需要输入2个命令参数 
		if (p2p_ctrl_asp_provision_resp(wpa_s, buf + 23))        【 46 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_CONNECT ", 12) == 0) {   // 如果 命令 buf  是 P2P_CONNECT  那么执行函数         需要输入2个命令参数 
		reply_len = p2p_ctrl_connect(wpa_s, buf + 12, reply, reply_size);      【 47 】
	} else if (os_strncmp(buf, "P2P_LISTEN ", 11) == 0) {  // 如果 命令 buf  是 P2P_LISTEN  那么执行函数        可选带参数
		if (p2p_ctrl_listen(wpa_s, buf + 11))                       【 48 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_LISTEN") == 0) {
		if (p2p_ctrl_listen(wpa_s, ""))
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_GROUP_REMOVE ", 17) == 0) {   // 如果 命令 buf  是 P2P_GROUP_REMOVE  那么执行函数        可选带参数
		if (wpas_p2p_group_remove(wpa_s, buf + 17))                 【 49 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_GROUP_ADD") == 0) {         // 如果 命令 buf  是 P2P_GROUP_ADD  那么执行函数        可选带参数
		if (p2p_ctrl_group_add(wpa_s, ""))                              【 50 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_GROUP_ADD ", 14) == 0) {
		if (p2p_ctrl_group_add(wpa_s, buf + 14))
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_GROUP_MEMBER ", 17) == 0) {     // 如果 命令 buf  是 P2P_GROUP_MEMBER  那么执行函数        可选带参数
		reply_len = p2p_ctrl_group_member(wpa_s, buf + 17, reply, reply_size);           【51】
	} else if (os_strncmp(buf, "P2P_PROV_DISC ", 14) == 0) {
		if (p2p_ctrl_prov_disc(wpa_s, buf + 14))
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_GET_PASSPHRASE") == 0) {     // 如果 命令 buf  是 P2P_GET_PASSPHRASE  那么执行函数        
		reply_len = p2p_get_passphrase(wpa_s, reply, reply_size);       【 52 】
	} else if (os_strncmp(buf, "P2P_SERV_DISC_REQ ", 18) == 0) {                           // 如果 命令 buf  是 P2P_SERV_DISC_REQ   需要输入2个命令参数    
		reply_len = p2p_ctrl_serv_disc_req(wpa_s, buf + 18, reply,reply_size);       【 53 】
	} else if (os_strncmp(buf, "P2P_SERV_DISC_CANCEL_REQ ", 25) == 0) {      // 如果 命令 buf  是 P2P_SERV_DISC_CANCEL_REQ   需要输入1个命令参数    
		if (p2p_ctrl_serv_disc_cancel_req(wpa_s, buf + 25) < 0)                      【54】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_SERV_DISC_RESP ", 19) == 0) {             // 如果 命令 buf  是 P2P_SERV_DISC_RESP     需要输入4个命令参数    
		if (p2p_ctrl_serv_disc_resp(wpa_s, buf + 19) < 0)                               【 55 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_SERVICE_UPDATE") == 0) {           // 如果 命令 buf  是 P2P_SERVICE_UPDATE     
		wpas_p2p_sd_service_update(wpa_s);                               【 56 】
	} else if (os_strncmp(buf, "P2P_SERV_DISC_EXTERNAL ", 23) == 0) {           // 如果 命令 buf  是 P2P_SERV_DISC_EXTERNAL        需要输入1个命令参数    
		if (p2p_ctrl_serv_disc_external(wpa_s, buf + 23) < 0)             【 57 】          
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_SERVICE_FLUSH") == 0) {            // 如果 命令 buf  是 P2P_SERVICE_FLUSH    
		wpas_p2p_service_flush(wpa_s);                             【 58 】  
	} else if (os_strncmp(buf, "P2P_SERVICE_ADD ", 16) == 0) {        // 如果 命令 buf  是 P2P_SERVICE_ADD    需要输入3--6个命令参数    
		if (p2p_ctrl_service_add(wpa_s, buf + 16) < 0)                         【59】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_SERVICE_DEL ", 16) == 0) {    // 如果 命令 buf  是 P2P_SERVICE_DEL   需要输入2--3个命令参数    
		if (p2p_ctrl_service_del(wpa_s, buf + 16) < 0)                            【60】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_SERVICE_REP ", 16) == 0) {  // 如果 命令 buf  是 P2P_SERVICE_REP   需要输入5--6个命令参数    
		if (p2p_ctrl_service_replace(wpa_s, buf + 16) < 0)         【 61 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_REJECT ", 11) == 0) {        // 如果 命令 buf  是 P2P_REJECT   需要输入1 个命令参数    
		if (p2p_ctrl_reject(wpa_s, buf + 11) < 0)                          【 62 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_INVITE ", 11) == 0) {        // 如果 命令 buf  是 P2P_INVITE   需要输入1 个命令参数    
		if (p2p_ctrl_invite(wpa_s, buf + 11) < 0)                  【63】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_PEER ", 9) == 0) {                // 如果 命令 buf  是 P2P_PEER   需要输入1 个命令参数    
		reply_len = p2p_ctrl_peer(wpa_s, buf + 9, reply,  reply_size);           【 64 】
	} else if (os_strncmp(buf, "P2P_SET ", 8) == 0) {        // 如果 命令 buf  是 P2P_SET   需要输入2 个命令参数     
		if (p2p_ctrl_set(wpa_s, buf + 8) < 0)                               【 65 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_FLUSH") == 0) {         // 如果 命令 buf  是 P2P_FLUSH 
		p2p_ctrl_flush(wpa_s);                                  【 66 】 
	} else if (os_strncmp(buf, "P2P_UNAUTHORIZE ", 16) == 0) {   // 如果 命令 buf  是 P2P_UNAUTHORIZE   需要输入1 个命令参数   
		if (wpas_p2p_unauthorize(wpa_s, buf + 16) < 0)                     【 67 】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_CANCEL") == 0) {   // 如果 命令 buf  是 P2P_CANCEL 
		if (wpas_p2p_cancel(wpa_s))                                     【 68 】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_PRESENCE_REQ ", 17) == 0) {    // 如果 命令 buf  是 P2P_PRESENCE_REQ   可选可带2个参数
		if (p2p_ctrl_presence_req(wpa_s, buf + 17) < 0)                【 69 】                     
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_PRESENCE_REQ") == 0) {  
		if (p2p_ctrl_presence_req(wpa_s, "") < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_EXT_LISTEN ", 15) == 0) {    // 如果 命令 buf  是 P2P_EXT_LISTEN   可选可带2个参数
		if (p2p_ctrl_ext_listen(wpa_s, buf + 15) < 0)                    【70】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_EXT_LISTEN") == 0) {
		if (p2p_ctrl_ext_listen(wpa_s, "") < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_REMOVE_CLIENT ", 18) == 0) {        // 如果 命令 buf  是 P2P_REMOVE_CLIENT   需要输入1 个命令参数   
		if (p2p_ctrl_remove_client(wpa_s, buf + 18) < 0)                        【71】
			reply_len = -1;
	} else if (os_strncmp(buf, "P2P_LO_START ", 13) == 0) {      // 如果 命令 buf  是 P2P_LO_START   需要输入4 个命令参数         
		if (p2p_ctrl_iface_p2p_lo_start(wpa_s, buf + 13))                   【72】
			reply_len = -1;
	} else if (os_strcmp(buf, "P2P_LO_STOP") == 0) {              // 如果 命令 buf  是 P2P_LO_STOP   
		if (wpas_p2p_lo_stop(wpa_s))                                                 【73】
			reply_len = -1;

	} else if (os_strncmp(buf, "WFD_SUBELEM_SET ", 16) == 0) {        // 如果 命令 buf  是 WFD_SUBELEM_SET    需要输入2个命令参数     
		if (wifi_display_subelem_set(wpa_s->global, buf + 16) < 0)               【74】
			reply_len = -1;
	} else if (os_strncmp(buf, "WFD_SUBELEM_GET ", 16) == 0) {           // 如果 命令 buf  是 WFD_SUBELEM_GET   
		reply_len = wifi_display_subelem_get(wpa_s->global, buf + 16,  reply, reply_size);           【75】


	} else if (os_strcmp(buf, "FETCH_ANQP") == 0) {             // 如果 命令 buf  是 FETCH_ANQP   
		if (interworking_fetch_anqp(wpa_s) < 0)                           【76】          
			reply_len = -1;
	} else if (os_strcmp(buf, "STOP_FETCH_ANQP") == 0) {            // 如果 命令 buf  是 STOP_FETCH_ANQP   
		interworking_stop_fetch_anqp(wpa_s);                                【77】      
	} else if (os_strcmp(buf, "INTERWORKING_SELECT") == 0) {               // 如果 命令 buf  是 INTERWORKING_SELECT  
		if (ctrl_interworking_select(wpa_s, NULL) < 0)                                 【78】
			reply_len = -1;
	} else if (os_strncmp(buf, "INTERWORKING_SELECT ", 20) == 0) {
		if (ctrl_interworking_select(wpa_s, buf + 20) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "INTERWORKING_CONNECT ", 21) == 0) {             // 如果 命令 buf  是 INTERWORKING_CONNECT  
		if (ctrl_interworking_connect(wpa_s, buf + 21, 0) < 0)                              【79】
			reply_len = -1;
	} else if (os_strncmp(buf, "INTERWORKING_ADD_NETWORK ", 25) == 0) {       // 如果 命令 buf  是 INTERWORKING_ADD_NETWORK  
		int id;

		id = ctrl_interworking_connect(wpa_s, buf + 25, 1);                                   【80】
		if (id < 0)
			reply_len = -1;
		else {
			reply_len = os_snprintf(reply, reply_size, "%d\n", id);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "ANQP_GET ", 9) == 0) {             // 如果 命令 buf  是 ANQP_GET     需要输入2个命令参数                
		if (get_anqp(wpa_s, buf + 9) < 0)                                      【81】
			reply_len = -1;
	} else if (os_strncmp(buf, "GAS_REQUEST ", 12) == 0) {        // 如果 命令 buf  是 GAS_REQUEST     需要输入2个命令参数          
		if (gas_request(wpa_s, buf + 12) < 0)                                         【82】
			reply_len = -1;
	} else if (os_strncmp(buf, "GAS_RESPONSE_GET ", 17) == 0) {  // 如果 命令 buf  是 GAS_RESPONSE_GET     需要输入2个命令参数     
		reply_len = gas_response_get(wpa_s, buf + 17, reply, reply_size);            【83】

	} else if (os_strncmp(buf, "HS20_ANQP_GET ", 14) == 0) {  // 如果 命令 buf  是 HS20_ANQP_GET     需要输入2个命令参数    
		if (get_hs20_anqp(wpa_s, buf + 14) < 0)                                 【84】   
			reply_len = -1;
	} else if (os_strncmp(buf, "HS20_GET_NAI_HOME_REALM_LIST ", 29) == 0) {   // 如果 命令 buf  是 HS20_GET_NAI_HOME_REALM_LIST     需要输入2个命令参数   
		if (hs20_get_nai_home_realm_list(wpa_s, buf + 29) < 0)                       【 不识别 UNKNOW COMMAND 】
			reply_len = -1;
	} else if (os_strncmp(buf, "HS20_ICON_REQUEST ", 18) == 0) { // 如果 命令 buf  是 HS20_ICON_REQUEST     需要输入2个命令参数   
		if (hs20_icon_request(wpa_s, buf + 18, 0) < 0)                              【85】
			reply_len = -1;
	} else if (os_strncmp(buf, "REQ_HS20_ICON ", 14) == 0) {           // 如果 命令 buf  是 REQ_HS20_ICON     需要输入2个命令参数   
		if (hs20_icon_request(wpa_s, buf + 14, 1) < 0)                           【 不识别 UNKNOW COMMAND 】
			reply_len = -1;
	} else if (os_strncmp(buf, "GET_HS20_ICON ", 14) == 0) {         【 不识别 UNKNOW COMMAND 】  
		reply_len = get_hs20_icon(wpa_s, buf + 14, reply, reply_size);
	} else if (os_strncmp(buf, "DEL_HS20_ICON ", 14) == 0) {                【 不识别 UNKNOW COMMAND 】
		if (del_hs20_icon(wpa_s, buf + 14) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "FETCH_OSU") == 0) {        // 如果 命令 buf  是 FETCH_OSU     
		if (hs20_fetch_osu(wpa_s, 0) < 0)                                 【86】
			reply_len = -1;
	} else if (os_strcmp(buf, "FETCH_OSU no-scan") == 0) {
		if (hs20_fetch_osu(wpa_s, 1) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "CANCEL_FETCH_OSU") == 0) {         // 如果 命令 buf  是 CANCEL_FETCH_OSU     
		hs20_cancel_fetch_osu(wpa_s);                                      【87】

	} else if (os_strncmp(buf, WPA_CTRL_RSP 【"CTRL-RSP-"】, os_strlen(WPA_CTRL_RSP)) == 0)          // 如果 命令 buf  是 CTRL-RSP- 那么执行函数        
	{
		if (wpa_supplicant_ctrl_iface_ctrl_rsp(wpa_s, buf + os_strlen(WPA_CTRL_RSP)))            【88】   
			reply_len = -1;
		else {
			/*
			 * Notify response from timeout to allow the control
			 * interface response to be sent first.
			 */
			eloop_register_timeout(0, 0, wpas_ctrl_eapol_response, wpa_s, NULL);             【89】
		}
	} else if (os_strcmp(buf, "RECONFIGURE") == 0) {     // 如果 命令 buf  是 RECONFIGURE     
		if (wpa_supplicant_reload_configuration(wpa_s))             【90】
			reply_len = -1;
	} else if (os_strcmp(buf, "TERMINATE") == 0) {           // 如果 命令 buf  是 TERMINATE  
		wpa_supplicant_terminate_proc(wpa_s->global);                       【91】
	} else if (os_strncmp(buf, "BSSID ", 6) == 0) {               // 如果 命令 buf  是 BSSID    需要输入2个命令参数     
		if (wpa_supplicant_ctrl_iface_bssid(wpa_s, buf + 6))                      【92】
			reply_len = -1;
	} else if (os_strncmp(buf, "BLACKLIST", 9) == 0) {           // 如果 命令 buf  是 BLACKLIST   
		reply_len = wpa_supplicant_ctrl_iface_blacklist(wpa_s, buf + 9, reply, reply_size);            【93】

	} else if (os_strncmp(buf, "LOG_LEVEL", 9) == 0) {            // 如果 命令 buf  是 LOG_LEVEL 
		reply_len = wpa_supplicant_ctrl_iface_log_level(wpa_s, buf + 9, reply, reply_size);       【94】

	} else if (os_strncmp(buf, "LIST_NETWORKS ", 14) == 0) {           // 如果 命令 buf  是 LIST_NETWORKS 
		reply_len = wpa_supplicant_ctrl_iface_list_networks(wpa_s, buf + 14, reply, reply_size);          【95】
	} else if (os_strcmp(buf, "LIST_NETWORKS") == 0) {
		reply_len = wpa_supplicant_ctrl_iface_list_networks(wpa_s, NULL, reply, reply_size);

	} else if (os_strcmp(buf, "DISCONNECT") == 0) {              // 如果 命令 buf  是 DISCONNECT 
		wpas_request_disconnection(wpa_s);                              【96】

	} else if (os_strcmp(buf, "SCAN") == 0) {                 // 如果 命令 buf  是 SCAN                   
		wpas_ctrl_scan(wpa_s, NULL, reply, reply_size, &reply_len);       【97】  
	} else if (os_strncmp(buf, "SCAN ", 5) == 0) {
		wpas_ctrl_scan(wpa_s, buf + 5, reply, reply_size, &reply_len);


	} else if (os_strcmp(buf, "SCAN_RESULTS") == 0) {        // 如果 命令 buf  是 SCAN_RESULTS         
		reply_len = wpa_supplicant_ctrl_iface_scan_results(wpa_s, reply, reply_size);   【98】  

	} else if (os_strcmp(buf, "ABORT_SCAN") == 0) {      // 如果 命令 buf  是 ABORT_SCAN    
		if (wpas_abort_ongoing_scan(wpa_s) < 0)                       【99】
			reply_len = -1;
	} else if (os_strncmp(buf, "SELECT_NETWORK ", 15) == 0) {              //  如果 命令 buf  是 SELECT_NETWORK    
		if (wpa_supplicant_ctrl_iface_select_network(wpa_s, buf + 15))                【100】          
			reply_len = -1;
	} else if (os_strncmp(buf, "ENABLE_NETWORK ", 15) == 0) {             //  如果 命令 buf  是 ENABLE_NETWORK    
		if (wpa_supplicant_ctrl_iface_enable_network(wpa_s, buf + 15))            【101】
			reply_len = -1;
	} else if (os_strncmp(buf, "DISABLE_NETWORK ", 16) == 0) {              //  如果 命令 buf  是 DISABLE_NETWORK    
		if (wpa_supplicant_ctrl_iface_disable_network(wpa_s, buf + 16))               【102】
			reply_len = -1;
	} else if (os_strcmp(buf, "ADD_NETWORK") == 0) {                          //  如果 命令 buf  是 ADD_NETWORK                     
		reply_len = wpa_supplicant_ctrl_iface_add_network(wpa_s, reply, reply_size);         【103】

	} else if (os_strncmp(buf, "REMOVE_NETWORK ", 15) == 0) {                 //  如果 命令 buf  是 REMOVE_NETWORK       
		if (wpa_supplicant_ctrl_iface_remove_network(wpa_s, buf + 15))                        【104】 
			reply_len = -1;
	} else if (os_strncmp(buf, "SET_NETWORK ", 12) == 0) {            //  如果 命令 buf  是 SET_NETWORK      
		if (wpa_supplicant_ctrl_iface_set_network(wpa_s, buf + 12))                【105】 
			reply_len = -1;
	} else if (os_strncmp(buf, "GET_NETWORK ", 12) == 0) {                       //  如果 命令 buf  是 GET_NETWORK      
		reply_len = wpa_supplicant_ctrl_iface_get_network(wpa_s, buf + 12, reply, reply_size);          【106】 

	} else if (os_strncmp(buf, "DUP_NETWORK ", 12) == 0) {            //  如果 命令 buf  是 DUP_NETWORK       需要输入3个命令参数   
		if (wpa_supplicant_ctrl_iface_dup_network(wpa_s, buf + 12,wpa_s))      【107】
			reply_len = -1;
	} else if (os_strcmp(buf, "LIST_CREDS") == 0) {              //  如果 命令 buf  是 LIST_CREDS             
		reply_len = wpa_supplicant_ctrl_iface_list_creds(wpa_s, reply, reply_size);      【108】

	} else if (os_strcmp(buf, "ADD_CRED") == 0) {                    //  如果 命令 buf  是 ADD_CRED      
		reply_len = wpa_supplicant_ctrl_iface_add_cred(wpa_s, reply, reply_size);       【109】

	} else if (os_strncmp(buf, "REMOVE_CRED ", 12) == 0) {          //  如果 命令 buf  是 REMOVE_CRED  
		if (wpa_supplicant_ctrl_iface_remove_cred(wpa_s, buf + 12))                【110】
			reply_len = -1;
	} else if (os_strncmp(buf, "SET_CRED ", 9) == 0) {                //  如果 命令 buf  是 SET_CRED  
		if (wpa_supplicant_ctrl_iface_set_cred(wpa_s, buf + 9))                          【111】
			reply_len = -1;
	} else if (os_strncmp(buf, "GET_CRED ", 9) == 0) {               //  如果 命令 buf  是 GET_CRED  需要输入3个命令参数   
		reply_len = wpa_supplicant_ctrl_iface_get_cred(wpa_s, buf + 9, reply,  reply_size);   【112】

	} else if (os_strcmp(buf, "SAVE_CONFIG") == 0) {          //  如果 命令 buf  是 SAVE_CONFIG  
		if (wpa_supplicant_ctrl_iface_save_config(wpa_s))                          【113】
			reply_len = -1;
	} else if (os_strncmp(buf, "GET_CAPABILITY ", 15) == 0) {         //  如果 命令 buf  是 GET_CAPABILITY  
		reply_len = wpa_supplicant_ctrl_iface_get_capability(wpa_s, buf + 15, reply, reply_size);    【114】

	} else if (os_strncmp(buf, "AP_SCAN ", 8) == 0) {          //  如果 命令 buf  是 AP_SCAN   需要输入1个命令参数   
		if (wpa_supplicant_ctrl_iface_ap_scan(wpa_s, buf + 8))               【115】
			reply_len = -1;
	} else if (os_strncmp(buf, "SCAN_INTERVAL ", 14) == 0) {            //  如果 命令 buf  是 SCAN_INTERVAL  
		if (wpa_supplicant_ctrl_iface_scan_interval(wpa_s, buf + 14))                  【116】
			reply_len = -1;
	} else if (os_strcmp(buf, "INTERFACE_LIST") == 0) {                   //  如果 命令 buf  是 INTERFACE_LIST  
		reply_len = wpa_supplicant_global_iface_list(wpa_s->global, reply, reply_size);       【117】

	} else if (os_strncmp(buf, "INTERFACES", 10) == 0) {                                   //  如果 命令 buf  是 INTERFACES  
		reply_len = wpa_supplicant_global_iface_interfaces(wpa_s->global, buf + 10, reply, reply_size);    【不识别 UNKNOW COMMAND 】
	} else if (os_strncmp(buf, "BSS ", 4) == 0) {                       //  如果 命令 buf  是 BSS  
		reply_len = wpa_supplicant_ctrl_iface_bss(wpa_s, buf + 4, reply, reply_size);   【118】

	} else if (os_strcmp(buf, "STA-FIRST") == 0) {            【不识别 UNKNOW COMMAND 】  
		reply_len = ap_ctrl_iface_sta_first(wpa_s, reply, reply_size);

	} else if (os_strncmp(buf, "STA ", 4) == 0) {                //  如果 命令 buf  是 STA   需要输入1个命令参数 
		reply_len = ap_ctrl_iface_sta(wpa_s, buf + 4, reply,reply_size);                 【119】

	} else if (os_strncmp(buf, "STA-NEXT ", 9) == 0) {              【不识别 UNKNOW COMMAND 】  
		reply_len = ap_ctrl_iface_sta_next(wpa_s, buf + 9, reply,reply_size);

	} else if (os_strncmp(buf, "DEAUTHENTICATE ", 15) == 0) {          //  如果 命令 buf  是 DEAUTHENTICATE    
		if (ap_ctrl_iface_sta_deauthenticate(wpa_s, buf + 15))                     【120】
			reply_len = -1;
	} else if (os_strncmp(buf, "DISASSOCIATE ", 13) == 0) { //  如果 命令 buf  是 DISASSOCIATE    
		if (ap_ctrl_iface_sta_disassociate(wpa_s, buf + 13))                          【121】
			reply_len = -1;
	} else if (os_strncmp(buf, "CHAN_SWITCH ", 12) == 0) {  //  如果 命令 buf  是 CHAN_SWITCH      需要输入2个命令参数 
		if (ap_ctrl_iface_chanswitch(wpa_s, buf + 12))               【122】
			reply_len = -1;
	} else if (os_strcmp(buf, "STOP_AP") == 0) {         //  如果 命令 buf  是 STOP_AP 
		if (wpas_ap_stop_ap(wpa_s))                       【不识别 UNKNOW COMMAND 】  
			reply_len = -1;
#endif /* CONFIG_AP */
	} else if (os_strcmp(buf, "SUSPEND") == 0) {  //  如果 命令 buf  是 SUSPEND 
		wpas_notify_suspend(wpa_s->global);                      【123】
	} else if (os_strcmp(buf, "RESUME") == 0) {         //  如果 命令 buf  是 RESUME 
		wpas_notify_resume(wpa_s->global);                              【124】
#ifdef CONFIG_TESTING_OPTIONS
	} else if (os_strcmp(buf, "DROP_SA") == 0) {
		wpa_supplicant_ctrl_iface_drop_sa(wpa_s);
#endif /* CONFIG_TESTING_OPTIONS */
	} else if (os_strncmp(buf, "ROAM ", 5) == 0) {          //  如果 命令 buf  是 ROAM 
		if (wpa_supplicant_ctrl_iface_roam(wpa_s, buf + 5))              【125】
			reply_len = -1;

	} else if (os_strncmp(buf, "STA_AUTOCONNECT ", 16) == 0) {        //  如果 命令 buf  是 STA_AUTOCONNECT 
		wpa_s->auto_reconnect_disabled = atoi(buf + 16) == 0;                 【126】  
            
	} else if (os_strncmp(buf, "BSS_EXPIRE_AGE ", 15) == 0) {  //  如果 命令 buf  是 BSS_EXPIRE_AGE 
		if (wpa_supplicant_ctrl_iface_bss_expire_age(wpa_s, buf + 15))       【127】
			reply_len = -1;
	} else if (os_strncmp(buf, "BSS_EXPIRE_COUNT ", 17) == 0) {     //  如果 命令 buf  是 BSS_EXPIRE_COUNT    需要输入1个命令参数 
		if (wpa_supplicant_ctrl_iface_bss_expire_count(wpa_s, buf + 17))         【128】 
			reply_len = -1;
	} else if (os_strncmp(buf, "BSS_FLUSH ", 10) == 0) {      //  如果 命令 buf  是 BSS_FLUSH   
		wpa_supplicant_ctrl_iface_bss_flush(wpa_s, buf + 10);                   【129】 
#ifdef CONFIG_TDLS
	} else if (os_strncmp(buf, "TDLS_DISCOVER ", 14) == 0) {            //  如果 命令 buf  是 TDLS_DISCOVER        
		if (wpa_supplicant_ctrl_iface_tdls_discover(wpa_s, buf + 14))             【130】
			reply_len = -1;
	} else if (os_strncmp(buf, "TDLS_SETUP ", 11) == 0) {                 //  如果 命令 buf  是 TDLS_SETUP   需要输入1个命令参数       
		if (wpa_supplicant_ctrl_iface_tdls_setup(wpa_s, buf + 11))                 【131】
			reply_len = -1;
	} else if (os_strncmp(buf, "TDLS_TEARDOWN ", 14) == 0) {     //  如果 命令 buf  是 TDLS_TEARDOWN 
		if (wpa_supplicant_ctrl_iface_tdls_teardown(wpa_s, buf + 14))                   【132】
			reply_len = -1;
	} else if (os_strncmp(buf, "TDLS_CHAN_SWITCH ", 17) == 0) {             //  如果 命令 buf  是 TDLS_CHAN_SWITCH    需要输入2个命令参数           
		if (wpa_supplicant_ctrl_iface_tdls_chan_switch(wpa_s,buf + 17))                    【133】
			reply_len = -1;
	} else if (os_strncmp(buf, "TDLS_CANCEL_CHAN_SWITCH ", 24) == 0) {   //  如果 命令 buf  是 TDLS_CANCEL_CHAN_SWITCH    需要输入2个命令参数    
		if (wpa_supplicant_ctrl_iface_tdls_cancel_chan_switch(wpa_s,buf + 24))            【134】
			reply_len = -1;
	} else if (os_strncmp(buf, "TDLS_LINK_STATUS ", 17) == 0) {  //  如果 命令 buf  是 TDLS_LINK_STATUS    需要输入1个命令参数    
		reply_len = wpa_supplicant_ctrl_iface_tdls_link_status(wpa_s, buf + 17, reply, reply_size);    【135】
#endif /* CONFIG_TDLS */
	} else if (os_strcmp(buf, "WMM_AC_STATUS") == 0) {  //  如果 命令 buf  是 WMM_AC_STATUS    需要输入1个命令参数    
		reply_len = wpas_wmm_ac_status(wpa_s, reply, reply_size);             【136】

	} else if (os_strncmp(buf, "WMM_AC_ADDTS ", 13) == 0) {  //  如果 命令 buf  是 WMM_AC_ADDTS    需要输入3个命令参数    
		if (wmm_ac_ctrl_addts(wpa_s, buf + 13))                                       【137】
			reply_len = -1;
	} else if (os_strncmp(buf, "WMM_AC_DELTS ", 13) == 0) {         //  如果 命令 buf  是 WMM_AC_DELTS    需要输入3个命令参数   
		if (wmm_ac_ctrl_delts(wpa_s, buf + 13))                                            【138】
			reply_len = -1;
	} else if (os_strncmp(buf, "SIGNAL_POLL", 11) == 0) {                     //  如果 命令 buf  是 SIGNAL_POLL    需要输入3个命令参数          
		reply_len = wpa_supplicant_signal_poll(wpa_s, reply,reply_size);                            【139】

	} else if (os_strncmp(buf, "SIGNAL_MONITOR", 14) == 0) {        //  如果 命令 buf  是 SIGNAL_MONITOR    需要输入3个命令参数  
		if (wpas_ctrl_iface_signal_monitor(wpa_s, buf + 14))                             【140】              
			reply_len = -1;
	} else if (os_strncmp(buf, "PKTCNT_POLL", 11) == 0) {         //  如果 命令 buf  是 PKTCNT_POLL    
		reply_len = wpa_supplicant_pktcnt_poll(wpa_s, reply,reply_size);             【141】  
#ifdef CONFIG_AUTOSCAN
	} else if (os_strncmp(buf, "AUTOSCAN ", 9) == 0) {         【不识别 UNKNOW COMMAND 】  
		if (wpa_supplicant_ctrl_iface_autoscan(wpa_s, buf + 9))
			reply_len = -1;
#endif /* CONFIG_AUTOSCAN */
	} else if (os_strcmp(buf, "DRIVER_FLAGS") == 0) {                   //  如果 命令 buf  是 DRIVER_FLAGS    
		reply_len = wpas_ctrl_iface_driver_flags(wpa_s, reply,reply_size);                【142】
#ifdef ANDROID
	} else if (os_strncmp(buf, "DRIVER ", 7) == 0) {                              //  如果 命令 buf  是 DRIVER    
		reply_len = wpa_supplicant_driver_cmd(wpa_s, buf + 7, reply,reply_size);           【143】
#endif /* ANDROID */
	} else if (os_strncmp(buf, "VENDOR ", 7) == 0) {                                      //  如果 命令 buf  是 VENDOR   
		reply_len = wpa_supplicant_vendor_cmd(wpa_s, buf + 7, reply, reply_size);                             【144】
	} else if (os_strcmp(buf, "REAUTHENTICATE") == 0) {                 //  如果 命令 buf  是 REAUTHENTICATE   
		pmksa_cache_clear_current(wpa_s->wpa);            【145】
		eapol_sm_request_reauth(wpa_s->eapol);             【146】
#ifdef CONFIG_WNM
	} else if (os_strncmp(buf, "WNM_SLEEP ", 10) == 0) {  【不识别 UNKNOW COMMAND 】  
		if (wpas_ctrl_iface_wnm_sleep(wpa_s, buf + 10))
			reply_len = -1;
	} else if (os_strncmp(buf, "WNM_BSS_QUERY ", 14) == 0) {      //  如果 命令 buf  是 WNM_BSS_QUERY    
		if (wpas_ctrl_iface_wnm_bss_query(wpa_s, buf + 14))                    【147】
				reply_len = -1;
#endif /* CONFIG_WNM */
	} else if (os_strcmp(buf, "FLUSH") == 0) {            //  如果 命令 buf  是 FLUSH   
		wpa_supplicant_ctrl_iface_flush(wpa_s);            【148】

	} else if (os_strncmp(buf, "RADIO_WORK ", 11) == 0) {                        //  如果 命令 buf  是 RADIO_WORK     需要输入1个命令参数    
		reply_len = wpas_ctrl_radio_work(wpa_s, buf + 11, reply, reply_size);            【149】

#ifdef CONFIG_TESTING_OPTIONS
	} else if (os_strncmp(buf, "MGMT_TX ", 8) == 0) {
		if (wpas_ctrl_iface_mgmt_tx(wpa_s, buf + 8) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "MGMT_TX_DONE") == 0) {
		wpas_ctrl_iface_mgmt_tx_done(wpa_s);
	} else if (os_strncmp(buf, "MGMT_RX_PROCESS ", 16) == 0) {
		if (wpas_ctrl_iface_mgmt_rx_process(wpa_s, buf + 16) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "DRIVER_EVENT ", 13) == 0) {
		if (wpas_ctrl_iface_driver_event(wpa_s, buf + 13) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "EAPOL_RX ", 9) == 0) {
		if (wpas_ctrl_iface_eapol_rx(wpa_s, buf + 9) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "DATA_TEST_CONFIG ", 17) == 0) {
		if (wpas_ctrl_iface_data_test_config(wpa_s, buf + 17) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "DATA_TEST_TX ", 13) == 0) {
		if (wpas_ctrl_iface_data_test_tx(wpa_s, buf + 13) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "DATA_TEST_FRAME ", 16) == 0) {
		if (wpas_ctrl_iface_data_test_frame(wpa_s, buf + 16) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "TEST_ALLOC_FAIL ", 16) == 0) {
		if (wpas_ctrl_test_alloc_fail(wpa_s, buf + 16) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "GET_ALLOC_FAIL") == 0) {
		reply_len = wpas_ctrl_get_alloc_fail(wpa_s, reply, reply_size);
	} else if (os_strncmp(buf, "TEST_FAIL ", 10) == 0) {
		if (wpas_ctrl_test_fail(wpa_s, buf + 10) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "GET_FAIL") == 0) {
		reply_len = wpas_ctrl_get_fail(wpa_s, reply, reply_size);
	} else if (os_strncmp(buf, "EVENT_TEST ", 11) == 0) {
		if (wpas_ctrl_event_test(wpa_s, buf + 11) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "TEST_ASSOC_IE ", 14) == 0) {
		if (wpas_ctrl_test_assoc_ie(wpa_s, buf + 14) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "RESET_PN") == 0) {
		if (wpas_ctrl_reset_pn(wpa_s) < 0)
			reply_len = -1;
	} else if (os_strncmp(buf, "KEY_REQUEST ", 12) == 0) {
		if (wpas_ctrl_key_request(wpa_s, buf + 12) < 0)
			reply_len = -1;
	} else if (os_strcmp(buf, "RESEND_ASSOC") == 0) {
		if (wpas_ctrl_resend_assoc(wpa_s) < 0)
			reply_len = -1;
#endif /* CONFIG_TESTING_OPTIONS */
	} else if (os_strncmp(buf, "VENDOR_ELEM_ADD ", 16) == 0) {    //  如果 命令 buf  是 VENDOR_ELEM_ADD     需要输入2个命令参数
		if (wpas_ctrl_vendor_elem_add(wpa_s, buf + 16) < 0)                         【150】
			reply_len = -1;
	} else if (os_strncmp(buf, "VENDOR_ELEM_GET ", 16) == 0) {           //  如果 命令 buf  是 VENDOR_ELEM_GET     需要输入2个命令参数
		reply_len = wpas_ctrl_vendor_elem_get(wpa_s, buf + 16, reply, reply_size);         【151】

	} else if (os_strncmp(buf, "VENDOR_ELEM_REMOVE ", 19) == 0) {  //  如果 命令 buf  是 VENDOR_ELEM_REMOVE     需要输入2个命令参数
		if (wpas_ctrl_vendor_elem_remove(wpa_s, buf + 19) < 0)                              【152】
			reply_len = -1;
	} else if (os_strncmp(buf, "NEIGHBOR_REP_REQUEST", 20) == 0) {    //  如果 命令 buf  是 NEIGHBOR_REP_REQUEST   
		if (wpas_ctrl_iface_send_neighbor_rep(wpa_s, buf + 20))                               【153】
			reply_len = -1;
	} else if (os_strcmp(buf, "ERP_FLUSH") == 0) {           //  如果 命令 buf  是 ERP_FLUSH   
		wpas_ctrl_iface_erp_flush(wpa_s);                                    【154】

	} else if (os_strncmp(buf, "MAC_RAND_SCAN ", 14) == 0) {     //  如果 命令 buf  是 MAC_RAND_SCAN   
		if (wpas_ctrl_iface_mac_rand_scan(wpa_s, buf + 14))                       【155】
			reply_len = -1;
	} else if (os_strncmp(buf, "GET_PREF_FREQ_LIST ", 19) == 0) {    //  如果 命令 buf  是 GET_PREF_FREQ_LIST  
		reply_len = wpas_ctrl_iface_get_pref_freq_list(wpa_s, buf + 19, reply, reply_size);   【156】
#ifdef CONFIG_FILS
	} else if (os_strncmp(buf, "FILS_HLP_REQ_ADD ", 17) == 0) {         【不识别 UNKNOW COMMAND 】  
		if (wpas_ctrl_iface_fils_hlp_req_add(wpa_s, buf + 17))
			reply_len = -1;
	} else if (os_strcmp(buf, "FILS_HLP_REQ_FLUSH") == 0) {   【不识别 UNKNOW COMMAND 】 
		wpas_flush_fils_hlp_req(wpa_s);
#endif /* CONFIG_FILS */
#ifdef CONFIG_DPP
	} else if (os_strncmp(buf, "DPP_QR_CODE ", 12) == 0) {       【不识别 UNKNOW COMMAND 】 
		int res;

		res = wpas_dpp_qr_code(wpa_s, buf + 12);
		if (res < 0) {
			reply_len = -1;
		} else {
			reply_len = os_snprintf(reply, reply_size, "%d", res);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "DPP_BOOTSTRAP_GEN ", 18) == 0) {     //  如果 命令 buf  是 DPP_BOOTSTRAP_GEN     需要输入1个命令参数
		int res;

		res = wpas_dpp_bootstrap_gen(wpa_s, buf + 18);                          【157】
		if (res < 0) {
			reply_len = -1;
		} else {
			reply_len = os_snprintf(reply, reply_size, "%d", res);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "DPP_BOOTSTRAP_REMOVE ", 21) == 0) {      //  如果 命令 buf  是 DPP_BOOTSTRAP_REMOVE      需要输入1个命令参数
		if (wpas_dpp_bootstrap_remove(wpa_s, buf + 21) < 0)                                【158】
			reply_len = -1;
	} else if (os_strncmp(buf, "DPP_BOOTSTRAP_GET_URI ", 22) == 0) {            //  如果 命令 buf  是 DPP_BOOTSTRAP_GET_URI     需要输入1个命令参数
		const char *uri;
		uri = wpas_dpp_bootstrap_get_uri(wpa_s, atoi(buf + 22));                             【159】
		if (!uri) {
			reply_len = -1;
		} else {
			reply_len = os_snprintf(reply, reply_size, "%s", uri);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "DPP_BOOTSTRAP_INFO ", 19) == 0) {              //  如果 命令 buf  是 DPP_BOOTSTRAP_INFO     需要输入1个命令参数 
		reply_len = wpas_dpp_bootstrap_info(wpa_s, atoi(buf + 19), reply, reply_size);           【160】

	} else if (os_strncmp(buf, "DPP_AUTH_INIT ", 14) == 0) {    //  如果 命令 buf  是 DPP_AUTH_INIT     需要输入1个命令参数 
		if (wpas_dpp_auth_init(wpa_s, buf + 13) < 0)                              【161】
			reply_len = -1;
	} else if (os_strncmp(buf, "DPP_LISTEN ", 11) == 0) {    //  如果 命令 buf  是 DPP_LISTEN     需要输入1个命令参数 
		if (wpas_dpp_listen(wpa_s, buf + 11) < 0)                                  【162】
			reply_len = -1;
	} else if (os_strcmp(buf, "DPP_STOP_LISTEN") == 0) {            //  如果 命令 buf  是 DPP_STOP_LISTEN     需要输入1个命令参数 
		wpas_dpp_listen_stop(wpa_s);                                                    【163】

	} else if (os_strncmp(buf, "DPP_CONFIGURATOR_ADD", 20) == 0) {    //  如果 命令 buf  是 DPP_CONFIGURATOR_ADD     
		int res;
		res = wpas_dpp_configurator_add(wpa_s, buf + 20);                            【164】
		if (res < 0) {
			reply_len = -1;
		} else {
			reply_len = os_snprintf(reply, reply_size, "%d", res);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "DPP_CONFIGURATOR_REMOVE ", 24) == 0) {  //  如果 命令 buf  是 DPP_CONFIGURATOR_REMOVE     
		if (wpas_dpp_configurator_remove(wpa_s, buf + 24) < 0)                          【165】
			reply_len = -1;
	} else if (os_strncmp(buf, "DPP_CONFIGURATOR_SIGN ", 22) == 0) {   
		if (wpas_dpp_configurator_sign(wpa_s, buf + 22) < 0)                               【不识别 UNKNOW COMMAND 】 
			reply_len = -1;
	} else if (os_strncmp(buf, "DPP_PKEX_ADD ", 13) == 0) {    //  如果 命令 buf  是 DPP_PKEX_ADD     
		int res;
		res = wpas_dpp_pkex_add(wpa_s, buf + 12);                        【166】
		if (res < 0) {
			reply_len = -1;
		} else {
			reply_len = os_snprintf(reply, reply_size, "%d", res);
			if (os_snprintf_error(reply_size, reply_len))
				reply_len = -1;
		}
	} else if (os_strncmp(buf, "DPP_PKEX_REMOVE ", 16) == 0) {   //  如果 命令 buf  是 DPP_PKEX_REMOVE     
		if (wpas_dpp_pkex_remove(wpa_s, buf + 16) < 0)                      【167】
			reply_len = -1;
#endif /* CONFIG_DPP */
	} else {
		os_memcpy(reply, "UNKNOWN COMMAND\n", 16);
		reply_len = 16;
	}

	if (reply_len < 0) {
		os_memcpy(reply, "FAIL\n", 5);
		reply_len = 5;
	}

	*resp_len = reply_len;
	return reply;
}


```
## wpa_hexdump_ascii_key
## wpas_ctrl_cmd_debug_level
## wpa_dbg
## wpa_debug_reopen_file
## wpa_sm_get_mib
## eapol_sm_get_mib
## wpa_supplicant_ctrl_iface_status
## wpas_ctrl_iface_pmksa
## wpas_ctrl_iface_pmksa_flush
## wpa_supplicant_ctrl_iface_set
## wpa_config_dump_values
## wpa_supplicant_ctrl_iface_get
## eapol_sm_notify_logoff(on)
## eapol_sm_notify_logoff(off)
## wpas_request_connection
## wpa_supplicant_ctrl_iface_preauth
## wpa_supplicant_ctrl_iface_ft_ds
## wpa_supplicant_ctrl_iface_wps_pbc
## wpa_supplicant_ctrl_iface_wps_pin
## wpa_supplicant_ctrl_iface_wps_check_pin
## wpas_wps_cancel
## wpa_supplicant_ctrl_iface_wps_nfc
## wpa_supplicant_ctrl_iface_wps_nfc_config_token
## wpa_supplicant_ctrl_iface_wps_nfc_token
## wpa_supplicant_ctrl_iface_wps_nfc_tag_read
## wpas_ctrl_nfc_get_handover_req
## wpas_ctrl_nfc_get_handover_sel
## wpas_ctrl_nfc_report_handover
## wpa_supplicant_ctrl_iface_wps_reg
## wpas_wps_er_start
## wpas_wps_er_stop
## wpa_supplicant_ctrl_iface_wps_er_pin
## wpas_wps_er_pbc
## wpa_supplicant_ctrl_iface_wps_er_learn
## wpa_supplicant_ctrl_iface_wps_er_set_config
## wpa_supplicant_ctrl_iface_wps_er_config
## wpa_supplicant_ctrl_iface_wps_er_nfc_config_token
## wpa_supplicant_ctrl_iface_ibss_rsn
## wpa_supplicant_ctrl_iface_mesh_interface_add
## wpa_supplicant_ctrl_iface_mesh_group_remove
## wpa_supplicant_ctrl_iface_mesh_peer_remove
## wpa_supplicant_ctrl_iface_mesh_peer_add
## p2p_ctrl_find
## wpas_p2p_stop_find
## p2p_ctrl_asp_provision
## p2p_ctrl_asp_provision_resp
## p2p_ctrl_connect
## p2p_ctrl_listen
## wpas_p2p_group_remove
## p2p_ctrl_group_add
## p2p_ctrl_group_member
## p2p_get_passphrase
## p2p_ctrl_serv_disc_req
## p2p_ctrl_serv_disc_cancel_req
## p2p_ctrl_serv_disc_resp
## wpas_p2p_sd_service_update
## p2p_ctrl_serv_disc_external
## wpas_p2p_service_flush
## p2p_ctrl_service_add
## p2p_ctrl_service_del
## p2p_ctrl_service_replace
## p2p_ctrl_reject
## p2p_ctrl_invite
## p2p_ctrl_peer
## p2p_ctrl_set
## p2p_ctrl_flush
## wpas_p2p_unauthorize
## wpas_p2p_cancel
## p2p_ctrl_presence_req
## p2p_ctrl_ext_listen
## p2p_ctrl_remove_client
## p2p_ctrl_iface_p2p_lo_start
## wpas_p2p_lo_stop
## wifi_display_subelem_set
## wifi_display_subelem_get
## interworking_fetch_anqp
## interworking_stop_fetch_anqp
## ctrl_interworking_select
## ctrl_interworking_connect
## ctrl_interworking_connect
## get_anqp
## gas_request
## gas_response_get
## get_hs20_anqp
## hs20_icon_request
## hs20_fetch_osu
## hs20_cancel_fetch_osu
## wpa_supplicant_ctrl_iface_ctrl_rsp
## eloop_register_timeout
## wpa_supplicant_reload_configuration
## wpa_supplicant_terminate_proc
## wpa_supplicant_ctrl_iface_bssid
## wpa_supplicant_ctrl_iface_blacklist
## wpa_supplicant_ctrl_iface_log_level
## wpa_supplicant_ctrl_iface_list_networks
## wpas_request_disconnection
## wpas_ctrl_scan
## wpa_supplicant_ctrl_iface_scan_results
## wpas_abort_ongoing_scan
## wpa_supplicant_ctrl_iface_select_network
## wpa_supplicant_ctrl_iface_enable_network
## wpa_supplicant_ctrl_iface_disable_network
## wpa_supplicant_ctrl_iface_add_network
## wpa_supplicant_ctrl_iface_remove_network
## wpa_supplicant_ctrl_iface_set_network
## wpa_supplicant_ctrl_iface_get_network
## wpa_supplicant_ctrl_iface_dup_network
## wpa_supplicant_ctrl_iface_list_creds
## wpa_supplicant_ctrl_iface_add_cred
## wpa_supplicant_ctrl_iface_remove_cred
## wpa_supplicant_ctrl_iface_set_cred
## wpa_supplicant_ctrl_iface_get_cred
## wpa_supplicant_ctrl_iface_save_config
## wpa_supplicant_ctrl_iface_get_capability
## wpa_supplicant_ctrl_iface_ap_scan
## wpa_supplicant_ctrl_iface_scan_interval
## wpa_supplicant_global_iface_list
## wpa_supplicant_ctrl_iface_bss
## ap_ctrl_iface_sta
## ap_ctrl_iface_sta_deauthenticate
## ap_ctrl_iface_sta_disassociate
## ap_ctrl_iface_chanswitch
## wpas_notify_suspend
## wpas_notify_resume
## wpa_supplicant_ctrl_iface_roam
## atoi(buf + 16)
## wpa_supplicant_ctrl_iface_bss_expire_age
## wpa_supplicant_ctrl_iface_bss_expire_count
## wpa_supplicant_ctrl_iface_bss_flush
## wpa_supplicant_ctrl_iface_tdls_discover
## wpa_supplicant_ctrl_iface_tdls_setup
## wpa_supplicant_ctrl_iface_tdls_teardown
## wpa_supplicant_ctrl_iface_tdls_chan_switch
## wpa_supplicant_ctrl_iface_tdls_cancel_chan_switch
## wpa_supplicant_ctrl_iface_tdls_link_status
## wpas_wmm_ac_status
## wmm_ac_ctrl_addts
## wmm_ac_ctrl_delts
## wpa_supplicant_signal_poll
## wpas_ctrl_iface_signal_monitor
## wpa_supplicant_pktcnt_poll
## wpas_ctrl_iface_driver_flags
## wpa_supplicant_driver_cmd
## wpa_supplicant_vendor_cmd
## pmksa_cache_clear_current
## eapol_sm_request_reauth
## wpas_ctrl_iface_wnm_bss_query
## wpa_supplicant_ctrl_iface_flush
## wpas_ctrl_radio_work
## wpas_ctrl_vendor_elem_add
## wpas_ctrl_vendor_elem_get
## wpas_ctrl_vendor_elem_remove
## wpas_ctrl_iface_send_neighbor_rep
## wpas_ctrl_iface_erp_flush
## wpas_ctrl_iface_mac_rand_scan
## wpas_ctrl_iface_get_pref_freq_list
## wpas_dpp_bootstrap_gen
## wpas_dpp_bootstrap_remove
## wpas_dpp_bootstrap_get_uri
## wpas_dpp_bootstrap_info
## wpas_dpp_auth_init
## wpas_dpp_listen
## wpas_dpp_listen_stop
## wpas_dpp_configurator_add
## wpas_dpp_configurator_remove
## wpas_dpp_pkex_add
## wpas_dpp_pkex_remove
## wpa_supplicant_ctrl_iface_mesh_group_add
 



# wpa_cli 命令

```
adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 IFNAME
wlan0

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PING
PONG

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NOTE
Invalid NOTE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NOTE wlan
ok


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MIB
dot11RSNAOptionImplemented=TRUE
dot11RSNAPreauthenticationImplemented=TRUE
dot11RSNAEnabled=FALSE
dot11RSNAPreauthenticationEnabled=FALSE
dot11RSNAConfigVersion=1
dot11RSNAConfigPairwiseKeysSupported=5
dot11RSNAConfigGroupCipherSize=0
dot11RSNAConfigPMKLifetime=43200
dot11RSNAConfigPMKReauthThreshold=70
dot11RSNAConfigNumberOfPTKSAReplayCounters=1
dot11RSNAConfigSATimeout=60
dot11RSNAAuthenticationSuiteSelected=00-00-00-0
dot11RSNAPairwiseCipherSelected=00-00-00-0
dot11RSNAGroupCipherSelected=00-00-00-0
dot11RSNAPMKIDUsed=
dot11RSNAAuthenticationSuiteRequested=00-00-00-0
dot11RSNAPairwiseCipherRequested=00-00-00-0
dot11RSNAGroupCipherRequested=00-00-00-0
dot11RSNAConfigNumberOfGTKSAReplayCounters=0
dot11RSNA4WayHandshakeFailures=0
dot1xSuppPaeState=1
dot1xSuppHeldPeriod=60
dot1xSuppAuthPeriod=30
dot1xSuppStartPeriod=30
dot1xSuppMaxStart=3
dot1xSuppSuppControlledPortStatus=Unauthorized
dot1xSuppBackendPaeState=1
dot1xSuppEapolFramesRx=0
dot1xSuppEapolFramesTx=0
dot1xSuppEapolStartFramesTx=0
dot1xSuppEapolLogoffFramesTx=0
dot1xSuppEapolRespFramesTx=0
dot1xSuppEapolReqIdFramesRx=0
dot1xSuppEapolReqFramesRx=0
dot1xSuppInvalidEapolFramesRx=0
dot1xSuppEapLengthErrorFramesRx=0
dot1xSuppLastEapolFrameVersion=0
dot1xSuppLastEapolFrameSource=00:00:00:00:00:00


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 STATUS
bssid=18:64:72:21:86:54
freq=5745
ssid=xxx-guest
id=0
id_str=%7B%22configKey%22%3A%22%5C%22xxx-guest%5C%22NONE%22%2C%22creatorUid%22%3A%221000%22%7D
mode=station
pairwise_cipher=NONE
group_cipher=NONE
key_mgmt=NONE
wpa_state=COMPLETED
ip_address=10.104.218.64
address=c0:8c:71:ac:da:33
uuid=c1d4e1cd-6d7e-5c2b-a2db-d731c1decc57


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PMKSA
Index / AA / PMKID / expiration (in seconds) / opportunistic


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PMKSA_FLUSH
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SET
Invalid SET command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SET passive_scan 1
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DUMP
ctrl_interface=/data/vendor/wifi/wpa/sockets
ctrl_interface_group=null
eapol_version=1
ap_scan=1
user_mpm=1
max_peer_links=99
mesh_max_inactivity=300
dot11RSNASAERetransPeriod=1000
disable_scan_offload=1
fast_reauth=1
opensc_engine_path=null
pkcs11_engine_path=null
pkcs11_module_path=null
openssl_ciphers=null
pcsc_reader=null
pcsc_pin=null
external_sim=1
driver_param=null
dot11RSNAConfigPMKLifetime=0
dot11RSNAConfigPMKReauthThreshold=0
dot11RSNAConfigSATimeout=0
update_config=1
auto_uuid=0
device_name=foles
manufacturer=motorola
model_name=Moto Z3 Play
model_number=Moto Z3 Play
serial_number=NNBA260199
config_methods=display push_button virtual_push_button physical_display
wps_cred_processing=0
p2p_listen_reg_class=0
p2p_listen_channel=0
p2p_oper_reg_class=0
p2p_oper_channel=0
p2p_go_intent=7
p2p_ssid_postfix=null
persistent_reconnect=0
p2p_intra_bss=1
p2p_group_idle=0
p2p_go_freq_change_policy=2
p2p_passphrase_len=0
p2p_add_cli_chan=1
p2p_optimize_listen_chan=0
p2p_go_ht40=0
p2p_go_vht=0
p2p_disabled=1
p2p_go_ctwindow=0
p2p_no_group_iface=1
p2p_ignore_shared_freq=0
ip_addr_go=0.0.0.0
ip_addr_mask=0.0.0.0
ip_addr_start=0.0.0.0
ip_addr_end=0.0.0.0
p2p_cli_probe=0
bss_max_count=400
bss_expiration_age=180
bss_expiration_scan_count=2
filter_ssids=0
filter_rssi=0
max_num_sta=128
ap_isolate=0
disassoc_low_ack=0
hs20=0
interworking=1
access_network_type=15
go_interworking=0
go_access_network_type=0
go_internet=0
go_venue_group=0
go_venue_type=0
pbc_in_m1=0
autoscan=null
wps_nfc_dev_pw_id=0
ext_password_backend=null
p2p_go_max_inactivity=300
auto_interworking=0
okc=0
pmf=1
dtim_period=0
beacon_int=0
ignore_old_scan_res=0
scan_cur_freq=0
sched_scan_interval=0
sched_scan_start_delay=0
tdls_external_control=1
osu_dir=null
wowlan_triggers=magic_pkt
p2p_search_delay=500
mac_addr=0
rand_addr_lifetime=60
preassoc_mac_addr=0
key_mgmt_offload=1
passive_scan=1
reassoc_same_bss_optim=0
wps_priority=0
fst_group_id=null
fst_priority=0
fst_llt=0
cert_in_cb=1
wpa_rsc_relaxation=1
sched_scan_plans=null
gas_address3=0
ftm_responder=0
ftm_initiator=0
gas_rand_addr_lifetime=60
gas_rand_mac_addr=0
dpp_config_processing=0


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET
Invalid GET command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET passive_scan
1


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 LOGON
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 LOGOFF
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REASSOCIATE
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REATTACH
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 RECONNECT
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PREAUTH
Invalid PREAUTH command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PREAUTH ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FT_DS
Invalid FT_DS command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FT_DS ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_PBC
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_PBC ssid     ## 可跟输入参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_PIN
Invalid WPS_PIN command: need one or two arguments:
- BSSID: use 'any' to select any
- PIN: optional, used only with devices that have no display


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_PIN any
42142003



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_CHECK_PIN 
Invalid WPS_CHECK_PIN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_CHECK_PIN 42142003
42142003

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_CHECK_PIN  ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_CANCEL
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC sss  ## 可跟输入参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_CONFIG_TOKEN
Invalid WPS_NFC_CONFIG_TOKEN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_CONFIG_TOKEN ssid    ## 必须要输入参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_TOKEN
Invalid WPS_NFC_TOKEN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_TOKEN ssid  ## 必须要输入参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_TAG_READ
Invalid 'wps_nfc_tag_read' command - one argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_NFC_TAG_READ ssid  ## 必须要输入参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_GET_HANDOVER_REQ
Invalid NFC_GET_HANDOVER_REQ command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_GET_HANDOVER_REQ ssid
Invalid NFC_GET_HANDOVER_REQ command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_GET_HANDOVER_REQ ssid 12  ## 必须要输入2个参数
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_GET_HANDOVER_SEL
Invalid NFC_GET_HANDOVER_REQ command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_GET_HANDOVER_SEL ssid 12   ## 必须要输入2个参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_REPORT_HANDOVER
Invalid NFC_REPORT_HANDOVER command - at least 4 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NFC_REPORT_HANDOVER 1 1 1  1  ## 必须要输入5个参数
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_REG
Invalid WPS_REG command: need two arguments:
- BSSID of the target AP
- AP PIN
Alternatively, six arguments can be used to reconfigure the AP:
- BSSID of the target AP
- AP PIN
- new SSID
- new auth (OPEN, WPAPSK, WPA2PSK)
- new encr (NONE, WEP, TKIP, CCMP)
- new key

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_REG BSSID 01:01:01:01:01
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_REG AP 2314141
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_REG SSID 01:01:01:01:01
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_START
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_STOP
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_PBC
Invalid WPS_ER_PBC command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_PBC enable
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_LEARN
Invalid WPS_ER_LEARN command: need two arguments:
- UUID: specify which AP to use
- PIN: AP PIN

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_LEARN uuid 213
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_LEARN PIN 213
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_SET_CONFIG 
Invalid WPS_ER_SET_CONFIG command: need two arguments:
- UUID: specify which AP to use
- Network configuration id

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_SET_CONFIG UUID 1241
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_CONFIG 
Invalid WPS_ER_CONFIG command: need six arguments:
- AP UUID
- AP PIN
- new SSID
- new auth (OPEN, WPAPSK, WPA2PSK)
- new encr (NONE, WEP, TKIP, CCMP)
- new key

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_CONFIG  ssid 1234   ## 必须要输入6个参数
Invalid WPS_ER_CONFIG command: need six arguments:
- AP UUID
- AP PIN
- new SSID
- new auth (OPEN, WPAPSK, WPA2PSK)
- new encr (NONE, WEP, TKIP, CCMP)
- new key


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_NFC_CONFIG_TOKEN
Invalid WPS_ER_NFC_CONFIG_TOKEN command: need two arguments:
- WPS/NDEF: token format
- UUID: specify which AP to use

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WPS_ER_NFC_CONFIG_TOKEN UUID 123 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 IBSS_RSN
Invalid IBSS_RSN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 IBSS_RSN ssid  
UNKNOWN COMMAND   奇怪了



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_INTERFACE_ADD
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_INTERFACE_ADD ssid
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_GROUP_ADD
Invalid MESH_GROUP_ADD command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_GROUP_ADD ssid
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_GROUP_REMOVE
Invalid MESH_GROUP_REMOVE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_GROUP_REMOVE ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_PEER_REMOVE
Invalid MESH_PEER_REMOVE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_PEER_REMOVE ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_PEER_ADD
Invalid MESH_PEER_ADD command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MESH_PEER_ADD ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_FIND 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_FIND ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_STOP_FIND 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_ASP_PROVISION
Invalid P2P_ASP_PROVISION command - at least 3 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_ASP_PROVISION 1 2 3
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_ASP_PROVISION_RESP
Invalid P2P_ASP_PROVISION_RESP command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_ASP_PROVISION_RESP ssid 1
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_CONNECT
Invalid P2P_CONNECT command - at least 2 arguments are required. 

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_CONNECT ssid 01:01:01:01:01
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_LISTEN
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_LISTEN ssid


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_REMOVE
Invalid P2P_GROUP_REMOVE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_REMOVE ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_ADD
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_ADD ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_MEMBER 
Invalid P2P_GROUP_MEMBER command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GROUP_MEMBER  ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_GET_PASSPHRASE
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_REQ
Invalid P2P_SERV_DISC_REQ command: needs two or more arguments (address and TLVs)


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_REQ address 01:01:02:03:04
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_CANCEL_REQ 
Invalid P2P_SERV_DISC_CANCEL_REQ command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_CANCEL_REQ  ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_RESP 
Invalid P2P_SERV_DISC_RESP command: needs four arguments (freq, address, dialog token, and TLVs)


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_RESP  1 2 3 4 
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_UPDATE
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_EXTERNAL
Invalid P2P_SERV_DISC_EXTERNAL command - at least 1 argument is required. 


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERV_DISC_EXTERNAL  ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_FLUSH
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_ADD
Invalid P2P_SERVICE_ADD command: needs 3-6 arguments

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_ADD 1 2 3 4 5 6 
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_DEL
Invalid P2P_SERVICE_DEL command: needs two or three arguments

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_DEL 1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_REP
Invalid P2P_SERVICE_REP command: needs 5-6 arguments


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SERVICE_REP 1 2 3 4 5 
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_REJECT
Invalid P2P_REJECT command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_REJECT 1 
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_INVITE
Invalid P2P_INVITE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_INVITE 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_PEER
Invalid P2P_PEER command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_PEER 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SET 
Invalid P2P_SET command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_SET  1 2
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_FLUSH
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_UNAUTHORIZE
Invalid P2P_UNAUTHORIZE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_UNAUTHORIZE 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_CANCEL
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_PRESENCE_REQ 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_PRESENCE_REQ 1
Invalid P2P_PRESENCE_REQ command: needs two arguments 
(preferred duration, interval ,in microsecods).Optional second pair can be used to provide acceptable values.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_PRESENCE_REQ  1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_EXT_LISTEN
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_EXT_LISTEN 1
Invalid P2P_EXT_LISTEN command: needs two arguments (availability period, availability interval; in millisecods).
Extended Listen Timing can be cancelled with this command when used without parameters.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_EXT_LISTEN 1 2 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_REMOVE_CLIENT
Invalid P2P_REMOVE_CLIENT command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_REMOVE_CLIENT 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_LO_START
Invalid P2P_LO_START command - at least 4 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_LO_START 1 2 3 4
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 P2P_LO_STOP
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WFD_SUBELEM_SET
Invalid WFD_SUBELEM_SET command: needs one or two arguments (subelem, hexdump)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WFD_SUBELEM_SET 1 2 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WFD_SUBELEM_GET
Invalid WFD_SUBELEM_GET command: needs one argument (subelem)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WFD_SUBELEM_GET any
[]

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WFD_SUBELEM_GET  1 


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FETCH_ANQP 
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 STOP_FETCH_ANQP 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_SELECT
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_SELECT 1 
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_CONNECT
Invalid INTERWORKING_CONNECT command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_CONNECT 1 
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_ADD_NETWORK 
Invalid INTERWORKING_ADD_NETWORK command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERWORKING_ADD_NETWORK  1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ANQP_GET 
Invalid ANQP_GET command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ANQP_GET  any ssid
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GAS_REQUEST 
Invalid GAS_REQUEST command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GAS_REQUEST  1 2 
FAIL
 

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GAS_RESPONSE_GET
Invalid GAS_RESPONSE_GET command - at least 2 arguments are required.  

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GAS_RESPONSE_GET 1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 HS20_ANQP_GET
Invalid HS20_ANQP_GET command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 HS20_ANQP_GET 1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 HS20_ICON_REQUEST
Command needs two arguments (dst mac addr and icon name)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 HS20_ICON_REQUEST 1 2 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FETCH_OSU 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FETCH_OSU no-scan
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 CANCEL_FETCH_OSU 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 RECONFIGURE 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TERMINATE 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0  BSSID 01:01:01:01:01
Invalid BSSID command: needs two arguments (network id and BSSID)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0  BSSID bssid 01:01:01:01:01
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BLACKLIST 
[]

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 LOG_LEVEL 
Current level: INFO
Timestamp: 0


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 LIST_NETWORK 
network id / ssid / bssid / flags
0       RD-Test any     [CURRENT]


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DISCONNECT
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SCAN
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SCAN 1 
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SCAN_RESULT
bssid / frequency / signal level / flags / ssid
1c:5f:2b:5e:d5:54       5785    -29     [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS]       D-Link_DIR-816_5G
88:25:93:9f:31:44       5785    -70     [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS]      bsptest-5G
02:e1:8c:d8:25:61       5745    -39     [WPA2-PSK-CCMP][WPS][ESS][P2P]  DIRECT-GOZHUZJ5-PF10GBUUmsJQ


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ABORT_SCAN
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SELECT_NETWORK 
Invalid SELECT_NETWORK command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SELECT_NETWORK D-Link_DIR-816_5G
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ENABLE_NETWORK 
Invalid ENABLE_NETWORK command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ENABLE_NETWORK D-Link_DIR-816_5G
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DISABLE_NETWORK RD-Test
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ADD_NETWORK Bell
2

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REMOVE_NETWORK zukgit
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REMOVE_NETWORK RD-Test
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SET_NETWORK RD-Test
Invalid SET_NETWORK command: needs three arguments
(network id, variable name, and value)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SET_NETWORK 1 2 3
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_NETWORK 
set_network variables:
  ssid (network name, SSID)
  psk (WPA passphrase or pre-shared key)
  key_mgmt (key management protocol)
  identity (EAP identity)
  password (EAP password)
  ...
Note: Values are entered in the same format as the configuration file is using,
i.e., strings values need to be inside double quotation marks.
For example: set_network 1 ssid "network name"
Please see wpa_supplicant.conf documentation for full list of
available variables.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_NETWORK  RD-Test
Invalid GET_NETWORK command: needs two arguments
(network id and variable name)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_NETWORK 1  RD-Test
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DUP_NETWORK  RD-Test
Invalid DUP_NETWORK command: needs three arguments
(src netid, dest netid, and variable name)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 LIST_CREDS
cred id / realm / username / domain / imsi


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ADD_CRED
0

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REMOVE_CRED
Invalid REMOVE_CRED command - at least 1 argument is required.



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REMOVE_CRED 1
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SET_CRED 
Invalid SET_CRED command: needs three arguments
(cred id, variable name, and value)


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_CRED
Invalid GET_CRED command: needs two arguments
(cred id, variable name)

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_CRED  1 1
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SAVE_CONFIG
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_CAPABILITY
Invalid GET_CAPABILITY command: need either one or two arguments

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_CAPABILITY ssid
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 AP_SCAN
Invalid AP_SCAN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 AP_SCAN 1
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SCAN_INTERVAL
Invalid SCAN_INTERVAL command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SCAN_INTERVAL 1
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 INTERFACE_LIST
[]

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS
Invalid BSS command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS 1
id=278
bssid=1c:5f:2b:5e:d5:54
freq=5785
beacon_int=100
capabilities=0x0031
qual=0
noise=-104
level=-32
tsf=0000083860155465
age=181
ie=0011442d4c696e6b5f4449522d3831365f354701088c129824b048606c03019d3c04011a9d052d1a6e0116ff000000010000000000000000000000000c00000000003d169d050000000000000000000000000000000000000000dd1a0050f20101000050f20202000050f2020050f20401000050f20230180100000fac020200000fac02000fac040100000fac020000dd180050f2020101000003a4000027a4000042435e0062322f000b050000ce127add07000c4307000000bf0c0000c031feff2401feff2401c005019b00feffdd8a0050f204104a0001101044000102103b00010310470010bc329e001dd811b286011c5f2b5ed55410210014442d4c696e6b2053797374656d732c20496e632e102300074449522d3831361024000676312e302e30104200033130311054000800060050f20400011011000a4449522d3831365f3547100800020080103c0001021049000600372a000120dd1900a0c6000100000000000077386aa9cb48eed706040000ed02
flags=[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS]
ssid=D-Link_DIR-816_5G
wps_state=configured
wps_primary_device_type=6-0050F204-1
wps_device_name=DIR-816_5G
wps_config_methods=0x0080
snr=72
est_throughput=390001
update_idx=282
beacon_ie=0011442d4c696e6b5f4449522d3831365f354701088c129824b048606c03019d050400010002dd310050f204104a000110104400010210470010bc329e001dd811b286011c5f2b5ed554103c0001021049000600372a0001202d1a6e0116ff000000010000000000000000000000000c00000000003d169d050000000000000000000000000000000000000000bf0c0000c031feff2401feff2401c005019b00feffdd1a0050f20101000050f20202000050f2020050f20401000050f20230180100000fac020200000fac02000fac040100000fac020000dd180050f2020101000003a4000027a4000042435e0062322f000b050000ce127add07000c4307000000dd1900a0c6000100000000000079386aa94fd0edd7060400005c0d


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 STA
Invalid STA command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 STA 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DEAUTHENTICATE 
Invalid DEAUTHENTICATE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DISASSOCIATE  
Invalid DISASSOCIATE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DISASSOCIATE   1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 CHAN_SWITCH 
Invalid CHAN_SWITCH command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 CHAN_SWITCH 1 2 
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SUSPEND  
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 RESUME  
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DROP_SA  


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ROAM  
Invalid ROAM command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ROAM   1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ROAM   0
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS_EXPIRE_AGE
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS_EXPIRE_COUNT
Invalid BSS_EXPIRE_AGE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS_EXPIRE_COUNT 1
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 BSS_FLUSH
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_DISCOVER
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_SETUP
Invalid TDLS_DISCOVER command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_SETUP 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_TEARDOWN
Invalid TDLS_TEARDOWN command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_TEARDOWN 1 
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_CHAN_SWITCH
Invalid TDLS_CHAN_SWITCH command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_CHAN_SWITCH 1 2 


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_CANCEL_CHAN_SWITCH
Invalid TDLS_CANCEL_CHAN_SWITCH command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_CANCEL_CHAN_SWITCH 1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_LINK_STATUS
Invalid TDLS_LINK_STATUS command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 TDLS_LINK_STATUS 1 
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_STATUS
Invalid TDLS_LINK_STATUS command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_STATUS 1
Not associated to a WMM AP, WMM AC is Disabled


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_ADDTS
Invalid WMM_AC_ADDTS command - at least 3 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_ADDTS 1 2 3
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_DELTS
Invalid WMM_AC_DELTS command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WMM_AC_DELTS 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SIGNAL_POLL
RSSI=-48
LINKSPEED=200
NOISE=-104
FREQUENCY=5745
AVG_RSSI=-47


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 SIGNAL_MONITOR
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 PKTCNT_POLL
TXGOOD=26478
TXBAD=1
RXGOOD=58453


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DRIVER_FLAG
00A17A769ABCFBC0:
AP
SET_KEYS_AFTER_ASSOC_DONE
HT_2040_COEX
P2P_CONCURRENT
P2P_CAPABLE
AP_TEARDOWN_SUPPORT
P2P_MGMT_AND_NON_P2P
SANE_ERROR_CODES
OFFCHANNEL_TX
BSS_SELECTION
TDLS_SUPPORT
TDLS_EXTERNAL_SETUP
PROBE_RESP_OFFLOAD
INACTIVITY_TIMER
SAE
IBSS
RADAR
AP_CSA
ACS_OFFLOAD
KEY_MGMT_OFFLOAD
HT_IBSS
VHT_IBSS
SUPPORT_HW_MODE_ANY
P2P_LISTEN_OFFLOAD
UNKNOWN
UNKNOWN
UNKNOWN
UNKNOWN
UNKNOWN
UNKNOWN
UNKNOWN


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DRIVER
Invalid DRIVER command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DRIVER IBSS
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR
Invalid VENDOR command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 REAUTHENTICATE
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WNM_BSS_QUERY
Invalid WNM_BSS_QUERY command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 WNM_BSS_QUERY 1
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 FLUSH
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 RADIO_WORK
Invalid RADIO_WORK command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 RADIO_WORK 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_ADD
Invalid VENDOR_ELEM_ADD command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_ADD 1 2 
FAIL



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_GET 
Invalid VENDOR_ELEM_ADD command - at least 2 arguments are required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_GET  1 2
[]


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_REMOVE
Invalid VENDOR_ELEM_REMOVE command - at least 2 arguments are required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 VENDOR_ELEM_REMOVE 1 2
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 NEIGHBOR_REP_REQUEST
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 ERP_FLUSH
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MAC_RAND_SCAN
Invalid MAC_RAND_SCAN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 MAC_RAND_SCAN 0
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_PREF_FREQ_LIST
Invalid GET_PREF_FREQ_LIST command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 GET_PREF_FREQ_LIST 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_GEN
Invalid DPP_BOOTSTRAP_GEN command - at least 1 argument is required.




adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_GEN 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_REMOVE
Invalid DPP_BOOTSTRAP_REMOVE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_REMOVE 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_GET_URI
Invalid DPP_BOOTSTRAP_GET_URI command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_GET_URI 1
FAIL

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_INFO
Invalid DPP_BOOTSTRAP_INFO command - at least 1 argument is required

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_BOOTSTRAP_INFO 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_AUTH_INIT
Invalid DPP_AUTH_INIT command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_AUTH_INIT 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_LISTEN
Invalid DPP_LISTEN command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_LISTEN 1
OK



adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_STOP_LISTEN
OK


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_CONFIGURATOR_ADD 
1

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_CONFIGURATOR_REMOVE
Invalid DPP_CONFIGURATOR_REMOVE command - at least 1 argument is required.

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_CONFIGURATOR_REMOVE 1
OK

adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_PKEX_ADD
Invalid DPP_PKEX_ADD command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_PKEX_ADD 1
FAIL


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_PKEX_REMOVE
Invalid DPP_PKEX_REMOVE command - at least 1 argument is required.


adb root & adb shell wpa_cli -iwlan0 -g@android:vendor_wpa_wlan0 IFNAME=wlan0 DPP_PKEX_REMOVE 1
FAIL


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
struct wpa_supplicant *     p2pdev;

/*
 * This should be under CONFIG_MBO, but it is left out to allow using the bss_temp_disallowed list for other purposes as well.
 */
struct dl_list bss_tmp_disallowed;

/* FILS HLP requests (struct fils_hlp_req) */
struct dl_list fils_hlp_req;


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

###  p2p_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#174


/** struct p2p_data - P2P module data (internal to P2P module) */
struct p2p_data {
	/**
	 * cfg - P2P module configuration
	 *
	 * This is included in the same memory allocation with the
	 * struct p2p_data and as such, must not be freed separately.
	 */
	struct p2p_config *cfg;

	/**
	 * state - The current P2P state
	 */
	enum p2p_state {
		/**
		 * P2P_IDLE - Idle
		 */
		P2P_IDLE,

		/**
		 * P2P_SEARCH - Search (Device Discovery)
		 */
		P2P_SEARCH,

		/**
		 * P2P_CONNECT - Trying to start GO Negotiation
		 */
		P2P_CONNECT,

		/**
		 * P2P_CONNECT_LISTEN - Listen during GO Negotiation start
		 */
		P2P_CONNECT_LISTEN,

		/**
		 * P2P_GO_NEG - In GO Negotiation
		 */
		P2P_GO_NEG,

		/**
		 * P2P_LISTEN_ONLY - Listen only
		 */
		P2P_LISTEN_ONLY,

		/**
		 * P2P_WAIT_PEER_CONNECT - Waiting peer in List for GO Neg
		 */
		P2P_WAIT_PEER_CONNECT,

		/**
		 * P2P_WAIT_PEER_IDLE - Waiting peer idle for GO Neg
		 */
		P2P_WAIT_PEER_IDLE,

		/**
		 * P2P_SD_DURING_FIND - Service Discovery during find
		 */
		P2P_SD_DURING_FIND,

		/**
		 * P2P_PROVISIONING - Provisioning (during group formation)
		 */
		P2P_PROVISIONING,

		/**
		 * P2P_PD_DURING_FIND - Provision Discovery during find
		 */
		P2P_PD_DURING_FIND,

		/**
		 * P2P_INVITE - Trying to start Invite
		 */
		P2P_INVITE,

		/**
		 * P2P_INVITE_LISTEN - Listen during Invite
		 */
		P2P_INVITE_LISTEN,
	} state;

	/**
	 * min_disc_int - minDiscoverableInterval
	 */
	int min_disc_int;

	/**
	 * max_disc_int - maxDiscoverableInterval
	 */
	int max_disc_int;

	/**
	 * max_disc_tu - Maximum number of TUs for discoverable interval
	 */
	int max_disc_tu;

	/**
	 * devices - List of known P2P Device peers
	 */
	struct dl_list devices;

	/**
	 * go_neg_peer - Pointer to GO Negotiation peer
	 */
	struct p2p_device *go_neg_peer;

	/**
	 * invite_peer - Pointer to Invite peer
	 */
	struct p2p_device *invite_peer;

	/**
	 * last_p2p_find_oper - Pointer to last pre-find operation peer
	 */
	struct p2p_device *last_p2p_find_oper;

	const u8 *invite_go_dev_addr;
	u8 invite_go_dev_addr_buf[ETH_ALEN];
	int invite_dev_pw_id;

	unsigned int retry_invite_req:1;
	unsigned int retry_invite_req_sent:1;

	/**
	 * sd_peer - Pointer to Service Discovery peer
	 */
	struct p2p_device *sd_peer;

	/**
	 * sd_query - Pointer to Service Discovery query
	 */
	struct p2p_sd_query *sd_query;

	/**
	 * num_p2p_sd_queries - Total number of broadcast SD queries present in
	 * the list
	 */
	int num_p2p_sd_queries;

	/**
	 * sd_query_no_ack - The first peer (Dev Addr) that did not ACK SD Query
	 *
	 * This is used to track the first peer that did not ACK an SD Query
	 * within a single P2P Search iteration. All zeros address means no such
	 * peer was yet seen. This information is used to allow a new Listen and
	 * Search phases to be once every pending SD Query has been sent once to
	 * each peer instead of looping all pending attempts continuously until
	 * running out of retry maximums.
	 */
	u8 sd_query_no_ack[ETH_ALEN];

	/* GO Negotiation data */

	/**
	 * intended_addr - Local Intended P2P Interface Address
	 *
	 * This address is used during group owner negotiation as the Intended
	 * P2P Interface Address and the group interface will be created with
	 * address as the local address in case of successfully completed
	 * negotiation.
	 */
	u8 intended_addr[ETH_ALEN];

	/**
	 * go_intent - Local GO Intent to be used during GO Negotiation
	 */
	u8 go_intent;

	/**
	 * next_tie_breaker - Next tie-breaker value to use in GO Negotiation
	 */
	u8 next_tie_breaker;

	/**
	 * ssid - Selected SSID for GO Negotiation (if local end will be GO)
	 */
	u8 ssid[SSID_MAX_LEN];

	/**
	 * ssid_len - ssid length in octets
	 */
	size_t ssid_len;

	/**
	 * ssid_set - Whether SSID is already set for GO Negotiation
	 */
	int ssid_set;

	/**
	 * Regulatory class for own operational channel
	 */
	u8 op_reg_class;

	/**
	 * op_channel - Own operational channel
	 */
	u8 op_channel;

	/**
	 * channels - Own supported regulatory classes and channels
	 *
	 * List of supposerted channels per regulatory class. The regulatory
	 * classes are defined in IEEE Std 802.11-2007 Annex J and the
	 * numbering of the clases depends on the configured country code.
	 */
	struct p2p_channels channels;

	struct wpa_freq_range_list no_go_freq;

	enum p2p_pending_action_state {
		P2P_NO_PENDING_ACTION,
		P2P_PENDING_GO_NEG_REQUEST,
		P2P_PENDING_GO_NEG_RESPONSE,
		P2P_PENDING_GO_NEG_RESPONSE_FAILURE,
		P2P_PENDING_GO_NEG_CONFIRM,
		P2P_PENDING_SD,
		P2P_PENDING_PD,
		P2P_PENDING_PD_RESPONSE,
		P2P_PENDING_INVITATION_REQUEST,
		P2P_PENDING_INVITATION_RESPONSE,
		P2P_PENDING_DEV_DISC_REQUEST,
		P2P_PENDING_DEV_DISC_RESPONSE,
		P2P_PENDING_GO_DISC_REQ
	} pending_action_state;

	unsigned int pending_listen_freq;
	unsigned int pending_listen_sec;
	unsigned int pending_listen_usec;

	u8 dev_capab;

	int in_listen;
	int drv_in_listen;

	/**
	 * sd_queries - Pending service discovery queries
	 */
	struct p2p_sd_query *sd_queries;

	/**
	 * srv_update_indic - Service Update Indicator for local services
	 */
	u16 srv_update_indic;

	struct wpabuf *sd_resp; /* Fragmented SD response */
	u8 sd_resp_addr[ETH_ALEN];
	u8 sd_resp_dialog_token;
	size_t sd_resp_pos; /* Offset in sd_resp */
	u8 sd_frag_id;

	struct wpabuf *sd_rx_resp; /* Reassembled SD response */
	u16 sd_rx_update_indic;

	/* P2P Invitation data */
	enum p2p_invite_role inv_role;
	u8 inv_bssid[ETH_ALEN];
	int inv_bssid_set;
	u8 inv_ssid[SSID_MAX_LEN];
	size_t inv_ssid_len;
	u8 inv_sa[ETH_ALEN];
	u8 inv_group_bssid[ETH_ALEN];
	u8 *inv_group_bssid_ptr;
	u8 inv_go_dev_addr[ETH_ALEN];
	u8 inv_status;
	int inv_op_freq;
	int inv_persistent;

	enum p2p_discovery_type find_type;
	int find_specified_freq;
	unsigned int last_p2p_find_timeout;
	u8 last_prog_scan_class;
	u8 last_prog_scan_chan;
	unsigned int find_pending_full:1;
	int p2p_scan_running;
	enum p2p_after_scan {
		P2P_AFTER_SCAN_NOTHING,
		P2P_AFTER_SCAN_LISTEN,
		P2P_AFTER_SCAN_CONNECT
	} start_after_scan;
	u8 after_scan_peer[ETH_ALEN];
	struct p2p_pending_action_tx *after_scan_tx;
	unsigned int after_scan_tx_in_progress:1;
	unsigned int send_action_in_progress:1;

	/* Requested device types for find/search */
	unsigned int num_req_dev_types;
	u8 *req_dev_types;
	u8 *find_dev_id;
	u8 find_dev_id_buf[ETH_ALEN];

	struct os_reltime find_start; /* time of last p2p_find start */

	struct p2p_group **groups;
	size_t num_groups;

	struct p2p_device *pending_client_disc_go;
	u8 pending_client_disc_addr[ETH_ALEN];
	u8 pending_dev_disc_dialog_token;
	u8 pending_dev_disc_addr[ETH_ALEN];
	int pending_dev_disc_freq;
	unsigned int pending_client_disc_freq;

	int ext_listen_only;
	unsigned int ext_listen_period;
	unsigned int ext_listen_interval;
	unsigned int ext_listen_interval_sec;
	unsigned int ext_listen_interval_usec;

	u8 peer_filter[ETH_ALEN];

	int cross_connect;

	int best_freq_24;
	int best_freq_5;
	int best_freq_overall;
	int own_freq_preference;

	/**
	 * wps_vendor_ext - WPS Vendor Extensions to add
	 */
	struct wpabuf *wps_vendor_ext[P2P_MAX_WPS_VENDOR_EXT];

	/*
	 * user_initiated_pd - Whether a PD request is user initiated or not.
	 */
	u8 user_initiated_pd;

	/*
	 * Keep track of which peer a given PD request was sent to.
	 * Used to raise a timeout alert in case there is no response.
	 */
	u8 pending_pd_devaddr[ETH_ALEN];

	/*
	 * Retry counter for provision discovery requests when issued
	 * in IDLE state.
	 */
	int pd_retries;

	/**
	 * pd_force_freq - Forced frequency for PD retries or 0 to auto-select
	 *
	 * This is is used during PD retries for join-a-group case to use the
	 * correct operating frequency determined from a BSS entry for the GO.
	 */
	int pd_force_freq;

	u8 go_timeout;
	u8 client_timeout;

	/* Extra delay in milliseconds between search iterations */
	unsigned int search_delay;
	int in_search_delay;

	u8 pending_reg_class;
	u8 pending_channel;
	u8 pending_channel_forced;

	/* ASP Support */
	struct p2ps_advertisement *p2ps_adv_list;
	struct p2ps_provision *p2ps_prov;
	u8 wild_card_hash[P2PS_HASH_LEN];
	u8 p2ps_seek;
	u8 p2ps_seek_hash[P2P_MAX_QUERY_HASH * P2PS_HASH_LEN];
	u8 p2ps_seek_count;

#ifdef CONFIG_WIFI_DISPLAY
	struct wpabuf *wfd_ie_beacon;
	struct wpabuf *wfd_ie_probe_req;
	struct wpabuf *wfd_ie_probe_resp;
	struct wpabuf *wfd_ie_assoc_req;
	struct wpabuf *wfd_ie_invitation;
	struct wpabuf *wfd_ie_prov_disc_req;
	struct wpabuf *wfd_ie_prov_disc_resp;
	struct wpabuf *wfd_ie_go_neg;
	struct wpabuf *wfd_dev_info;
	struct wpabuf *wfd_assoc_bssid;
	struct wpabuf *wfd_coupled_sink_info;
	struct wpabuf *wfd_r2_dev_info;
#endif /* CONFIG_WIFI_DISPLAY */

	u16 authorized_oob_dev_pw_id;

	struct wpabuf **vendor_elem;

	unsigned int pref_freq_list[P2P_MAX_PREF_CHANNELS];
	unsigned int num_pref_freq;

	/* Override option for preferred operating channel in GO Negotiation */
	u8 override_pref_op_class;
	u8 override_pref_channel;
};


```

###  p2p_message
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#568

/**
 * struct p2p_message - Parsed P2P message (or P2P IE)
 */
struct p2p_message {
	struct wpabuf *p2p_attributes;
	struct wpabuf *wps_attributes;
	struct wpabuf *wfd_subelems;

	u8 dialog_token;

	const u8 *capability;
	const u8 *go_intent;
	const u8 *status;
	const u8 *listen_channel;
	const u8 *operating_channel;
	const u8 *channel_list;
	u8 channel_list_len;
	const u8 *config_timeout;
	const u8 *intended_addr;
	const u8 *group_bssid;
	const u8 *invitation_flags;

	const u8 *group_info;
	size_t group_info_len;

	const u8 *group_id;
	size_t group_id_len;

	const u8 *device_id;

	const u8 *manageability;

	const u8 *noa;
	size_t noa_len;

	const u8 *ext_listen_timing;

	const u8 *minor_reason_code;

	const u8 *oob_go_neg_channel;

	/* P2P Device Info */
	const u8 *p2p_device_info;
	size_t p2p_device_info_len;
	const u8 *p2p_device_addr;
	const u8 *pri_dev_type;
	u8 num_sec_dev_types;
	char device_name[WPS_DEV_NAME_MAX_LEN + 1];
	u16 config_methods;

	/* WPS IE */
	u16 dev_password_id;
	int dev_password_id_present;
	u16 wps_config_methods;
	const u8 *wps_pri_dev_type;
	const u8 *wps_sec_dev_type_list;
	size_t wps_sec_dev_type_list_len;
	const u8 *wps_vendor_ext[P2P_MAX_WPS_VENDOR_EXT];
	size_t wps_vendor_ext_len[P2P_MAX_WPS_VENDOR_EXT];
	const u8 *manufacturer;
	size_t manufacturer_len;
	const u8 *model_name;
	size_t model_name_len;
	const u8 *model_number;
	size_t model_number_len;
	const u8 *serial_number;
	size_t serial_number_len;
	const u8 *oob_dev_password;
	size_t oob_dev_password_len;

	/* DS Parameter Set IE */
	const u8 *ds_params;

	/* SSID IE */
	const u8 *ssid;

	/* P2PS */
	u8 service_hash_count;
	const u8 *service_hash;

	const u8 *session_info;
	size_t session_info_len;

	const u8 *conn_cap;

	const u8 *adv_id;
	const u8 *adv_mac;

	const u8 *adv_service_instance;
	size_t adv_service_instance_len;

	const u8 *session_id;
	const u8 *session_mac;

	const u8 *feature_cap;
	size_t feature_cap_len;

	const u8 *persistent_dev;
	const u8 *persistent_ssid;
	size_t persistent_ssid_len;

	const u8 *pref_freq_list;
	size_t pref_freq_list_len;
};

```

### p2p_group_info
```

struct p2p_group_info {
	unsigned int num_clients;
	struct p2p_client_info {
		const u8 *p2p_device_addr;
		const u8 *p2p_interface_addr;
		u8 dev_capab;
		u16 config_methods;
		const u8 *pri_dev_type;
		u8 num_sec_dev_types;
		const u8 *sec_dev_types;
		const char *dev_name;
		size_t dev_name_len;
	} client[P2P_MAX_GROUP_ENTRIES];
};


```

### hostapd_iface
```
/**
 * struct hostapd_iface - hostapd per-interface data structure
 */
struct hostapd_iface {
	struct hapd_interfaces *interfaces;
	void *owner;
	char *config_fname;
	struct hostapd_config *conf;
	char phy[16]; /* Name of the PHY (radio) */

        enum hostapd_iface_state state;
#ifdef CONFIG_MESH
	struct mesh_conf *mconf;
#endif /* CONFIG_MESH */

	size_t num_bss;
	struct hostapd_data **bss;

	unsigned int wait_channel_update:1;
	unsigned int cac_started:1;
#ifdef CONFIG_FST
	struct fst_iface *fst;
	const struct wpabuf *fst_ies;
#endif /* CONFIG_FST */

	/*
	 * When set, indicates that the driver will handle the AP
	 * teardown: delete global keys, station keys, and stations.
	 */
	unsigned int driver_ap_teardown:1;

	/*
	 * When set, indicates that this interface is part of list of
	 * interfaces that need to be started together (synchronously).
	 */
	unsigned int need_to_start_in_sync:1;

	/* Ready to start but waiting for other interfaces to become ready. */
	unsigned int ready_to_start_in_sync:1;

	int num_ap; /* number of entries in ap_list */
	struct ap_info *ap_list; /* AP info list head */
	struct ap_info *ap_hash[STA_HASH_SIZE];

	u64 drv_flags;

	/* SMPS modes supported by the driver (WPA_DRIVER_SMPS_MODE_*) */
	unsigned int smps_modes;

	/*
	 * A bitmap of supported protocols for probe response offload. See
	 * struct wpa_driver_capa in driver.h
	 */
	unsigned int probe_resp_offloads;

	/* extended capabilities supported by the driver */
	const u8 *extended_capa, *extended_capa_mask;
	unsigned int extended_capa_len;

	unsigned int drv_max_acl_mac_addrs;

	struct hostapd_hw_modes *hw_features;
	int num_hw_features;
	struct hostapd_hw_modes *current_mode;
	/* Rates that are currently used (i.e., filtered copy of
	 * current_mode->channels */
	int num_rates;
	struct hostapd_rate_data *current_rates;
	int *basic_rates;
	int freq;

	u16 hw_flags;

	/* Number of associated Non-ERP stations (i.e., stations using 802.11b
	 * in 802.11g BSS) */
	int num_sta_non_erp;

	/* Number of associated stations that do not support Short Slot Time */
	int num_sta_no_short_slot_time;

	/* Number of associated stations that do not support Short Preamble */
	int num_sta_no_short_preamble;

	int olbc; /* Overlapping Legacy BSS Condition */

	/* Number of HT associated stations that do not support greenfield */
	int num_sta_ht_no_gf;

	/* Number of associated non-HT stations */
	int num_sta_no_ht;

	/* Number of HT associated stations 20 MHz */
	int num_sta_ht_20mhz;

	/* Number of HT40 intolerant stations */
	int num_sta_ht40_intolerant;

	/* Overlapping BSS information */
	int olbc_ht;

	u16 ht_op_mode;

	/* surveying helpers */

	/* number of channels surveyed */
	unsigned int chans_surveyed;

	/* lowest observed noise floor in dBm */
	s8 lowest_nf;

	/* channel utilization calculation */
	u64 last_channel_time;
	u64 last_channel_time_busy;
	u8 channel_utilization;

	/* eCSA IE will be added only if operating class is specified */
	u8 cs_oper_class;

	unsigned int dfs_cac_ms;
	struct os_reltime dfs_cac_start;

	/* Latched with the actual secondary channel information and will be
	 * used while juggling between HT20 and HT40 modes. */
	int secondary_ch;

#ifdef CONFIG_ACS
	unsigned int acs_num_completed_scans;
#endif /* CONFIG_ACS */

	void (*scan_cb)(struct hostapd_iface *iface);
	int num_ht40_scan_tries;

	struct dl_list sta_seen; /* struct hostapd_sta_info */
	unsigned int num_sta_seen;

	u8 dfs_domain;
};

```



### eloop_timeout
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/utils/eloop.c#52


struct eloop_timeout {
	struct dl_list list;
	struct os_reltime time;
	void *eloop_data;
	void *user_data;
	eloop_timeout_handler handler;
	WPA_TRACE_REF(eloop);
	WPA_TRACE_REF(user);
	WPA_TRACE_INFO
};



```


### wpa_driver_capa
```

/**
 * struct wpa_driver_capa - Driver capability information
 */
struct wpa_driver_capa {
#define WPA_DRIVER_CAPA_KEY_MGMT_WPA		0x00000001
#define WPA_DRIVER_CAPA_KEY_MGMT_WPA2		0x00000002
#define WPA_DRIVER_CAPA_KEY_MGMT_WPA_PSK	0x00000004
#define WPA_DRIVER_CAPA_KEY_MGMT_WPA2_PSK	0x00000008
#define WPA_DRIVER_CAPA_KEY_MGMT_WPA_NONE	0x00000010
#define WPA_DRIVER_CAPA_KEY_MGMT_FT		0x00000020
#define WPA_DRIVER_CAPA_KEY_MGMT_FT_PSK		0x00000040
#define WPA_DRIVER_CAPA_KEY_MGMT_WAPI_PSK	0x00000080
#define WPA_DRIVER_CAPA_KEY_MGMT_SUITE_B	0x00000100
#define WPA_DRIVER_CAPA_KEY_MGMT_SUITE_B_192	0x00000200
#define WPA_DRIVER_CAPA_KEY_MGMT_OWE		0x00000400
#define WPA_DRIVER_CAPA_KEY_MGMT_DPP		0x00000800
#define WPA_DRIVER_CAPA_KEY_MGMT_FILS_SHA256    0x00001000
#define WPA_DRIVER_CAPA_KEY_MGMT_FILS_SHA384    0x00002000
#define WPA_DRIVER_CAPA_KEY_MGMT_FT_FILS_SHA256 0x00004000
#define WPA_DRIVER_CAPA_KEY_MGMT_FT_FILS_SHA384 0x00008000
	/** Bitfield of supported key management suites */
	unsigned int key_mgmt;

#define WPA_DRIVER_CAPA_ENC_WEP40	0x00000001
#define WPA_DRIVER_CAPA_ENC_WEP104	0x00000002
#define WPA_DRIVER_CAPA_ENC_TKIP	0x00000004
#define WPA_DRIVER_CAPA_ENC_CCMP	0x00000008
#define WPA_DRIVER_CAPA_ENC_WEP128	0x00000010
#define WPA_DRIVER_CAPA_ENC_GCMP	0x00000020
#define WPA_DRIVER_CAPA_ENC_GCMP_256	0x00000040
#define WPA_DRIVER_CAPA_ENC_CCMP_256	0x00000080
#define WPA_DRIVER_CAPA_ENC_BIP		0x00000100
#define WPA_DRIVER_CAPA_ENC_BIP_GMAC_128	0x00000200
#define WPA_DRIVER_CAPA_ENC_BIP_GMAC_256	0x00000400
#define WPA_DRIVER_CAPA_ENC_BIP_CMAC_256	0x00000800
#define WPA_DRIVER_CAPA_ENC_GTK_NOT_USED	0x00001000
	/** Bitfield of supported cipher suites */
	unsigned int enc;

#define WPA_DRIVER_AUTH_OPEN		0x00000001
#define WPA_DRIVER_AUTH_SHARED		0x00000002
#define WPA_DRIVER_AUTH_LEAP		0x00000004
	/** Bitfield of supported IEEE 802.11 authentication algorithms */
	unsigned int auth;

/** Driver generated WPA/RSN IE */
#define WPA_DRIVER_FLAGS_DRIVER_IE	0x00000001
/** Driver needs static WEP key setup after association command */
#define WPA_DRIVER_FLAGS_SET_KEYS_AFTER_ASSOC 0x00000002
/** Driver takes care of all DFS operations */
#define WPA_DRIVER_FLAGS_DFS_OFFLOAD			0x00000004
/** Driver takes care of RSN 4-way handshake internally; PMK is configured with
 * struct wpa_driver_ops::set_key using alg = WPA_ALG_PMK */
#define WPA_DRIVER_FLAGS_4WAY_HANDSHAKE 0x00000008
/** Driver is for a wired Ethernet interface */
#define WPA_DRIVER_FLAGS_WIRED		0x00000010
/** Driver provides separate commands for authentication and association (SME in
 * wpa_supplicant). */
#define WPA_DRIVER_FLAGS_SME		0x00000020
/** Driver supports AP mode */
#define WPA_DRIVER_FLAGS_AP		0x00000040
/** Driver needs static WEP key setup after association has been completed */
#define WPA_DRIVER_FLAGS_SET_KEYS_AFTER_ASSOC_DONE	0x00000080
/** Driver supports dynamic HT 20/40 MHz channel changes during BSS lifetime */
#define WPA_DRIVER_FLAGS_HT_2040_COEX			0x00000100
/** Driver supports concurrent P2P operations */
#define WPA_DRIVER_FLAGS_P2P_CONCURRENT	0x00000200
/**
 * Driver uses the initial interface as a dedicated management interface, i.e.,
 * it cannot be used for P2P group operations or non-P2P purposes.
 */
#define WPA_DRIVER_FLAGS_P2P_DEDICATED_INTERFACE	0x00000400
/** This interface is P2P capable (P2P GO or P2P Client) */
#define WPA_DRIVER_FLAGS_P2P_CAPABLE	0x00000800
/** Driver supports station and key removal when stopping an AP */
#define WPA_DRIVER_FLAGS_AP_TEARDOWN_SUPPORT		0x00001000
/**
 * Driver uses the initial interface for P2P management interface and non-P2P
 * purposes (e.g., connect to infra AP), but this interface cannot be used for
 * P2P group operations.
 */
#define WPA_DRIVER_FLAGS_P2P_MGMT_AND_NON_P2P		0x00002000
/**
 * Driver is known to use sane error codes, i.e., when it indicates that
 * something (e.g., association) fails, there was indeed a failure and the
 * operation does not end up getting completed successfully later.
 */
#define WPA_DRIVER_FLAGS_SANE_ERROR_CODES		0x00004000
/** Driver supports off-channel TX */
#define WPA_DRIVER_FLAGS_OFFCHANNEL_TX			0x00008000
/** Driver indicates TX status events for EAPOL Data frames */
#define WPA_DRIVER_FLAGS_EAPOL_TX_STATUS		0x00010000
/** Driver indicates TX status events for Deauth/Disassoc frames */
#define WPA_DRIVER_FLAGS_DEAUTH_TX_STATUS		0x00020000
/** Driver supports roaming (BSS selection) in firmware */
#define WPA_DRIVER_FLAGS_BSS_SELECTION			0x00040000
/** Driver supports operating as a TDLS peer */
#define WPA_DRIVER_FLAGS_TDLS_SUPPORT			0x00080000
/** Driver requires external TDLS setup/teardown/discovery */
#define WPA_DRIVER_FLAGS_TDLS_EXTERNAL_SETUP		0x00100000
/** Driver indicates support for Probe Response offloading in AP mode */
#define WPA_DRIVER_FLAGS_PROBE_RESP_OFFLOAD		0x00200000
/** Driver supports U-APSD in AP mode */
#define WPA_DRIVER_FLAGS_AP_UAPSD			0x00400000
/** Driver supports inactivity timer in AP mode */
#define WPA_DRIVER_FLAGS_INACTIVITY_TIMER		0x00800000
/** Driver expects user space implementation of MLME in AP mode */
#define WPA_DRIVER_FLAGS_AP_MLME			0x01000000
/** Driver supports SAE with user space SME */
#define WPA_DRIVER_FLAGS_SAE				0x02000000
/** Driver makes use of OBSS scan mechanism in wpa_supplicant */
#define WPA_DRIVER_FLAGS_OBSS_SCAN			0x04000000
/** Driver supports IBSS (Ad-hoc) mode */
#define WPA_DRIVER_FLAGS_IBSS				0x08000000
/** Driver supports radar detection */
#define WPA_DRIVER_FLAGS_RADAR				0x10000000
/** Driver supports a dedicated interface for P2P Device */
#define WPA_DRIVER_FLAGS_DEDICATED_P2P_DEVICE		0x20000000
/** Driver supports QoS Mapping */
#define WPA_DRIVER_FLAGS_QOS_MAPPING			0x40000000
/** Driver supports CSA in AP mode */
#define WPA_DRIVER_FLAGS_AP_CSA				0x80000000
/** Driver supports mesh */
#define WPA_DRIVER_FLAGS_MESH			0x0000000100000000ULL
/** Driver support ACS offload */
#define WPA_DRIVER_FLAGS_ACS_OFFLOAD		0x0000000200000000ULL
/** Driver supports key management offload */
#define WPA_DRIVER_FLAGS_KEY_MGMT_OFFLOAD	0x0000000400000000ULL
/** Driver supports TDLS channel switching */
#define WPA_DRIVER_FLAGS_TDLS_CHANNEL_SWITCH	0x0000000800000000ULL
/** Driver supports IBSS with HT datarates */
#define WPA_DRIVER_FLAGS_HT_IBSS		0x0000001000000000ULL
/** Driver supports IBSS with VHT datarates */
#define WPA_DRIVER_FLAGS_VHT_IBSS		0x0000002000000000ULL
/** Driver supports automatic band selection */
#define WPA_DRIVER_FLAGS_SUPPORT_HW_MODE_ANY	0x0000004000000000ULL
/** Driver supports simultaneous off-channel operations */
#define WPA_DRIVER_FLAGS_OFFCHANNEL_SIMULTANEOUS	0x0000008000000000ULL
/** Driver supports full AP client state */
#define WPA_DRIVER_FLAGS_FULL_AP_CLIENT_STATE	0x0000010000000000ULL
/** Driver supports P2P Listen offload */
#define WPA_DRIVER_FLAGS_P2P_LISTEN_OFFLOAD     0x0000020000000000ULL
/** Driver supports FILS */
#define WPA_DRIVER_FLAGS_SUPPORT_FILS		0x0000040000000000ULL
/** Driver supports Beacon frame TX rate configuration (legacy rates) */
#define WPA_DRIVER_FLAGS_BEACON_RATE_LEGACY	0x0000080000000000ULL
/** Driver supports Beacon frame TX rate configuration (HT rates) */
#define WPA_DRIVER_FLAGS_BEACON_RATE_HT		0x0000100000000000ULL
/** Driver supports Beacon frame TX rate configuration (VHT rates) */
#define WPA_DRIVER_FLAGS_BEACON_RATE_VHT	0x0000200000000000ULL
/** Driver supports mgmt_tx with random TX address in non-connected state */
#define WPA_DRIVER_FLAGS_MGMT_TX_RANDOM_TA	0x0000400000000000ULL
/** Driver supports mgmt_tx with random TX addr in connected state */
#define WPA_DRIVER_FLAGS_MGMT_TX_RANDOM_TA_CONNECTED	0x0000800000000000ULL
/** Driver supports better BSS reporting with sched_scan in connected mode */
#define WPA_DRIVER_FLAGS_SCHED_SCAN_RELATIVE_RSSI	0x0001000000000000ULL
/** Driver supports HE capabilities */
#define WPA_DRIVER_FLAGS_HE_CAPABILITIES	0x0002000000000000ULL
/** Driver supports FILS shared key offload */
#define WPA_DRIVER_FLAGS_FILS_SK_OFFLOAD	0x0004000000000000ULL
/** Driver supports all OCE STA specific mandatory features */
#define WPA_DRIVER_FLAGS_OCE_STA		0x0008000000000000ULL
/** Driver supports all OCE AP specific mandatory features */
#define WPA_DRIVER_FLAGS_OCE_AP			0x0010000000000000ULL
/**
 * Driver supports all OCE STA-CFON specific mandatory features only.
 * If a driver sets this bit but not the %WPA_DRIVER_FLAGS_OCE_AP, the
 * userspace shall assume that this driver may not support all OCE AP
 * functionality but can support only OCE STA-CFON functionality.
 */
#define WPA_DRIVER_FLAGS_OCE_STA_CFON		0x0020000000000000ULL
	u64 flags;

#define FULL_AP_CLIENT_STATE_SUPP(drv_flags) \
	(drv_flags & WPA_DRIVER_FLAGS_FULL_AP_CLIENT_STATE)

#define WPA_DRIVER_SMPS_MODE_STATIC			0x00000001
#define WPA_DRIVER_SMPS_MODE_DYNAMIC			0x00000002
	unsigned int smps_modes;

	unsigned int wmm_ac_supported:1;

	unsigned int mac_addr_rand_scan_supported:1;
	unsigned int mac_addr_rand_sched_scan_supported:1;

	/** Maximum number of supported active probe SSIDs */
	int max_scan_ssids;

	/** Maximum number of supported active probe SSIDs for sched_scan */
	int max_sched_scan_ssids;

	/** Maximum number of supported scan plans for scheduled scan */
	unsigned int max_sched_scan_plans;

	/** Maximum interval in a scan plan. In seconds */
	u32 max_sched_scan_plan_interval;

	/** Maximum number of iterations in a single scan plan */
	u32 max_sched_scan_plan_iterations;

	/** Whether sched_scan (offloaded scanning) is supported */
	int sched_scan_supported;

	/** Maximum number of supported match sets for sched_scan */
	int max_match_sets;

	/**
	 * max_remain_on_chan - Maximum remain-on-channel duration in msec
	 */
	unsigned int max_remain_on_chan;

	/**
	 * max_stations - Maximum number of associated stations the driver
	 * supports in AP mode
	 */
	unsigned int max_stations;

	/**
	 * probe_resp_offloads - Bitmap of supported protocols by the driver
	 * for Probe Response offloading.
	 */
/** Driver Probe Response offloading support for WPS ver. 1 */
#define WPA_DRIVER_PROBE_RESP_OFFLOAD_WPS		0x00000001
/** Driver Probe Response offloading support for WPS ver. 2 */
#define WPA_DRIVER_PROBE_RESP_OFFLOAD_WPS2		0x00000002
/** Driver Probe Response offloading support for P2P */
#define WPA_DRIVER_PROBE_RESP_OFFLOAD_P2P		0x00000004
/** Driver Probe Response offloading support for IEEE 802.11u (Interworking) */
#define WPA_DRIVER_PROBE_RESP_OFFLOAD_INTERWORKING	0x00000008
	unsigned int probe_resp_offloads;

	unsigned int max_acl_mac_addrs;

	/**
	 * Number of supported concurrent channels
	 */
	unsigned int num_multichan_concurrent;

	/**
	 * extended_capa - extended capabilities in driver/device
	 *
	 * Must be allocated and freed by driver and the pointers must be
	 * valid for the lifetime of the driver, i.e., freed in deinit()
	 */
	const u8 *extended_capa, *extended_capa_mask;
	unsigned int extended_capa_len;

	struct wowlan_triggers wowlan_triggers;

/** Driver adds the DS Params Set IE in Probe Request frames */
#define WPA_DRIVER_FLAGS_DS_PARAM_SET_IE_IN_PROBES	0x00000001
/** Driver adds the WFA TPC IE in Probe Request frames */
#define WPA_DRIVER_FLAGS_WFA_TPC_IE_IN_PROBES		0x00000002
/** Driver handles quiet period requests */
#define WPA_DRIVER_FLAGS_QUIET				0x00000004
/**
 * Driver is capable of inserting the current TX power value into the body of
 * transmitted frames.
 * Background: Some Action frames include a TPC Report IE. This IE contains a
 * TX power field, which has to be updated by lower layers. One such Action
 * frame is Link Measurement Report (part of RRM). Another is TPC Report (part
 * of spectrum management). Note that this insertion takes place at a fixed
 * offset, namely the 6th byte in the Action frame body.
 */
#define WPA_DRIVER_FLAGS_TX_POWER_INSERTION		0x00000008
/**
 * Driver supports RRM. With this support, the driver will accept to use RRM in
 * (Re)Association Request frames, without supporting quiet period.
 */
#define WPA_DRIVER_FLAGS_SUPPORT_RRM			0x00000010

/** Driver supports setting the scan dwell time */
#define WPA_DRIVER_FLAGS_SUPPORT_SET_SCAN_DWELL		0x00000020
/** Driver supports Beacon Report Measurement */
#define WPA_DRIVER_FLAGS_SUPPORT_BEACON_REPORT		0x00000040

	u32 rrm_flags;

	/* Driver concurrency capabilities */
	unsigned int conc_capab;
	/* Maximum number of concurrent channels on 2.4 GHz */
	unsigned int max_conc_chan_2_4;
	/* Maximum number of concurrent channels on 5 GHz */
	unsigned int max_conc_chan_5_0;

	/* Maximum number of supported CSA counters */
	u16 max_csa_counters;
};

```
