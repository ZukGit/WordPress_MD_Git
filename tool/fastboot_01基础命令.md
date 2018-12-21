# fastboot概述

## fastboot简介
```
在安卓手机中Fastboot是一种比recovery更底层的刷机模式（俗称引导模式）。就是使用USB数据线连接手机的一种刷机模式。相对于Recovery、Fota等卡刷来说，线刷更可靠，安全。

recovery是一种卡刷，就是将刷机包放在sd卡上，然后在recovery中刷机的模式
fastboot是一种线刷，就是使用USB数据线连接手机的一种刷机模式
```

## 解锁BootLoader
```
使用 Fastboot 刷机必须先解锁BootLoader，否则无法刷机
解锁BootLoader的方法是在开发者模式中开起OEM unlocking开关。
如开发者模式隐藏，请进入Settings--System--About Phone--多次点击build number 即可打开隐藏的开发者模式。

Setting 》 System 》 Advanced（Developer options）》OEM unlocking 
设置  》 系统 》 开发者选择 》  OEM解锁 
```
<img src="//../zimage/tool/fastboot/fastboot01.jpg">

## 进入fastboot方式
```
目前主流Android手机进入Fastboot的方式为【关机】 【音量-】+【电源键】

ADB 命令为:    adb reboot bootloader

```

## 安卓设备分区解释
```

system:系统分区.
userdata:数据分区.
cache:缓存分区
recovery:Recovery分区.
boot:存放内核和ramdisk的分区.
hboot:这个是SPL所在的分区.很重要哦.也是fastboot所在的分区.刷错就真的变砖了.
splash1:这个就是开机Log屏幕.
radio:这个是radio所在的分区

```

##  高通安卓设备主要开机模式
<table><colgroup><col /><col /><col /><col /><col /><col /></colgroup>
<tbody>
<tr>
<td><span style=" font-size: 16px;">开机模式</span></td>
<td><span style=" font-size: 16px;">屏幕显示</span></td>
<td><span style=" font-size: 16px;">冷启动</span></td>
<td><span style=" font-size: 16px;">热启动</span></td>
<td><span style=" font-size: 16px;">按键退出</span></td>
<td><span style=" font-size: 16px;">命令退出</span></td>
</tr>
<tr>
<td><span style=" font-size: 16px;">Android/Normal</span></td>
<td><span style=" font-size: 16px;">Android界面</span></td>
<td><span style=" font-size: 16px;">按Power键</span></td>
<td><span style=" font-size: 16px;">adb&nbsp;reboot</span></td>
<td><span style=" font-size: 16px;">手机短按，VR长按Power键</span></td>
<td><span style=" font-size: 16px;">adb shell reboot -p(关机)</span></td>
</tr>
<tr>
<td><span style=" font-size: 16px;">Recovery/OTA/卡刷</span></td>
<td><span style=" font-size: 16px;">Recovery界面</span></td>
<td><span style=" font-size: 16px;">按住OK键(Vol+)，再按Power键</span></td>
<td><span style=" font-size: 16px;">adb reboot recovery</span></td>
<td><span style=" font-size: 16px;">长按Power键重启</span></td>
<td><span style=" font-size: 16px;">adb reboot</span></td>
</tr>
<tr>
<td><span style=" font-size: 16px;">Fastboot/线刷</span></td>
<td><span style=" font-size: 16px;">Fastboot界面</span></td>
<td><span style=" font-size: 16px;">按住BACK键(Vol-)，再按Power键</span></td>
<td>
<p><span style=" font-size: 16px;">adb reboot bootloader</span></p>
</td>
<td><span style=" font-size: 16px;">长按Power键重启</span></td>
<td>
<div><span style=" font-size: 16px;">fastboot&nbsp;reboot</span></div>
<div><span style=" font-size: 16px;">fastboot continue(resuming boot)</span></div>
</td>
</tr>
<tr>
<td><span style=" font-size: 16px;">FFBM/Fast&nbsp;Factory/厂测/半开机</span></td>
<td><span style=" font-size: 16px;">显示测试列表</span></td>
<td><span style=" font-size: 16px;">misc分区头部为ffbm时，按Power键</span></td>
<td><span style=" font-size: 16px;">misc分区头部为ffbm时，adb&nbsp;reboot</span></td>
<td><span style=" font-size: 16px;">长按Power键重启依然进入FFBM</span></td>
<td>
<div><span style=" font-size: 16px;">唯一退出方式擦除misc分区</span></div>
</td>
</tr>
<tr>
<td><span style=" font-size: 16px;">EDL/紧急下载/9008/砖头/裸板</span></td>
<td><span style=" font-size: 16px;">无显示,黑屏</span></td>
<td><span style=" font-size: 16px;">同时按住OK键(Vol+)和BACK键(Vol-)，再按Power键</span></td>
<td>
<div><span style=" font-size: 16px;">adb reboot edl</span></div>
<div><span style=" font-size: 16px;">fastboot reboot emergency</span></div>
</td>
<td><span style=" font-size: 16px;">长按Power键重启</span></td>
<td><span style=" font-size: 16px;">无</span></td>
</tr>
</tbody>
</table>


## 命令帮助提示
```

usage: fastboot [ <option> ] <command>

commands:
  update <filename>                        reflash device from update.zip
  flashall                                 flash boot + recovery + system
  flash <partition> [ <filename> ]         write a file to a flash partition
  erase <partition>                        erase a flash partition
  format <partition>                       format a flash partition
  getvar <variable>                        display a bootloader variable
  boot <kernel> [ <ramdisk> ]              download and boot kernel
  flash:raw boot <kernel> [ <ramdisk> ]    create bootimage and flash it
  devices                                  list all connected devices
  continue                                 continue with autoboot
  reboot                                   reboot device normally
  reboot-bootloader                        reboot device into bootloader
  help                                     show this help message

options:
  -w                                       erase userdata and cache (and format
                                           if supported by partition type)
  -u                                       do not first erase partition before
                                           formatting
  -s <specific device>                     specify device serial number
                                           or path to device port
  -l                                       with "devices", lists device paths
  -p <product>                             specify product name
  -c <cmdline>                             override kernel commandline
  -i <vendor id>                           specify a custom USB vendor id
  -b <base_addr>                           specify a custom kernel base address
  -n <page size>                           specify the nand page size. default: 2048
  -S <size>[K|M|G]                         automatically sparse files greater than
                                           size.  0 to disable

```
# A
# B

## fastboot boot xxx.img
```
fastboot boot  test.img     // 启动一个名为test.img的镜像

```
# C

## fastboot continue
```
fastboot continue         // 退出fastboot模式 重启手机

// 输出
resuming boot...
OKAY [  0.006s]
finished. total time: 0.011s

```
# D

## fastboot devices 
```
 fastboot devices      //  列出当前 已连接的fastboot模式设备 

//输出
NLHA140257      fastboot
```
# E

## fastboot erase frp
```
fastboot erase frp                // frp 即 Factory Reset Protection，用于防止用户信息在手机丢失后外泄  擦除工厂保护

// 输出
erasing 'frp'...
OKAY [  0.011s]
finished. total time: 0.016s
```

##  fastboot erase boot 
```

 fastboot erase boot   // 擦除引导分区  !危险命令! 容易变砖     类似  rm -fr /

```

##  fastboot erase system
```
fastboot erase system     //  擦除系统分区

```

## fastboot erase cache
```
 
fastboot erase cache      // cache分区


//输出
erasing 'cache'...
OKAY [  0.007s]
finished. total time: 0.013s

```

## fastboot erase userdata
```
fastboot erase userdata    // 擦除userdata分区

```

## fastboot erase mdm1m9kefs1
```
fastboot erase mdm1m9kefs1
fastboot erase mdm1m9kefs2
fastboot erase mdm1m9kefs3

(基带缓存分区，三个分区互补加密，破解3G其实就是改的这三个分区，要清空就一起清空。)


fastboot erase mdm1m9kefs1
// 输出
erasing 'mdm1m9kefs1'...
(bootloader) Permission denied
FAILED (remote failure)
finished. total time: 0.055s


fastboot erase mdm1m9kefs2
// 输出
erasing 'mdm1m9kefs2'...
(bootloader) Permission denied
FAILED (remote failure)
finished. total time: 0.019s


fastboot erase mdm1m9kefs3
// 输出
erasing 'mdm1m9kefs3'...
(bootloader) Permission denied
FAILED (remote failure)
finished. total time: 0.019s
```
# F
## fastboot flashall
```
fastboot flashall        // 此命令会在当前文件夹中查找全部img文件，将这些img文件烧写到全部相应的分区中，并又一次启动手机
```
## fastboot  format  data
```

fastboot  format  data              //   格式化 data 分区
// 输出
formatting 'data' partition...
Formatting is not supported for filesystem with type ''.
FAILED ()
finished. total time: 0.036s
```
## fastboot flash boot boot.img
```
fastboot flash boot boot.img    // 刷入 boot.img  引导分区 

Sending 'boot__a' (32768 KB)                       OKAY [  0.954s]
Writing 'boot__a'                                  (bootloader) Invalid partition name boot__a
FAILED (remote: '')
Finished. Total time: 1.006s

```


## fastboot flash system system.img
```
fastboot flash system system.img    // 刷入 system.img  刷入系统分区

//输出
Sending sparse 'system__a' 1/5 (514554 KB)         OKAY [ 17.261s]
Writing sparse 'system__a' 1/5                     (bootloader) Invalid partition name system__a
FAILED (remote: '')
Finished. Total time: 86.836s

```

## fastboot flash  recovery recovery.img
```

fastboot flash  recovery recovery.img    // 刷入 recovery.img  刷入recovery系统恢复分区
fastboot -w      // 效果相同
```

## fastboot flash cache cache.img
```
fastboot flash cache cache.img   // 刷写cache分区：(用于清空cache分区等)
fastboot -w      // 效果相同
```

## fastboot flash vendor vendor.img
```
fastboot flash vendor vendor.img             // 刷写 vendor分区
//输出
Sending 'vendor__a' (410261 KB)                    OKAY [ 10.688s]
Writing 'vendor__a'                                (bootloader) Invalid partition name vendor__a
FAILED (remote: '')
Finished. Total time: 10.736s

```
## fastboot flash userdata userdata.img
```
fastboot flash userdata userdata.img     // 刷写data分区：(用于清空data分区等)

//输出
Sending 'userdata' (968 KB)                        OKAY [  0.036s]
Writing 'userdata'                                 OKAY [  0.290s]
Finished. Total time: 0.548s


```

## fastboot flash bootloader  bootloader.img
```
fastboot flash bootloader  bootloader.img        // 刷入 bootloader.img 

Sending 'bootloader' (7419 KB)                     OKAY [  0.240s]
Writing 'bootloader'                               (bootloader) Validating 'bootloader.default.xml'
(bootloader) Committing 'bootloader.default.xml'
(bootloader) - flashing 'emmc_appsboot.mbn' to 'aboot'
(bootloader) - flashing 'rpm.mbn' to 'rpm'
(bootloader) - flashing 'tz.mbn' to 'tz'
(bootloader) - flashing 'devcfg.mbn' to 'devcfg'
(bootloader) - flashing 'cmnlib_30.mbn' to 'cmnlib'
(bootloader) - flashing 'cmnlib64_30.mbn' to 'cmnlib64'
(bootloader) - flashing 'keymaster64.mbn' to 'keymaster'
(bootloader) - flashing 'prov.mbn' to 'prov'
(bootloader) - flashing 'sbl1.mbn' to 'sbl1'
OKAY [  0.266s]
Finished. Total time: 0.919s
```
## fastbootflash radio radio.img
```
fastbootflash radio radio.img     // 刷写基带分区

```

##  fastboot flash splash1   xxx.bmp  
```
 fastboot flash splash1 xxx.bmp      // 烧录 开机画面

```

## fastboot flash oem oem.img
```
fastboot flash oem oem.img      //  刷写oem分区(运营商配置文件，和运营商配置有关)

//输出
Sending 'oem__a' (120704 KB)                       OKAY [  3.203s]
Writing 'oem__a'                                   (bootloader) Invalid partition name oem__a
FAILED (remote: '')
Finished. Total time: 3.253s
```
## fastboot flash system system.img_sparsechunk.0
```

fastboot flash system system.img_sparsechunk.0
fastboot flash system system.img_sparsechunk.1
fastboot flash system system.img_sparsechunk.2
fastboot flash system system.img_sparsechunk.3
fastboot flash system system.img_sparsechunk.4
fastboot flash system system.img_sparsechunk.5
fastboot flash system system.img_sparsechunk.6
为解决分区过大刷机容易导致出错，所以采用了分段式的方法。刷机时，方法还是一致的，只不过要从分段0开始，按次序刷到分段N
```
# G

##  fastboot getvar all
```

fastboot getvar all          // 获取手机相关信息


version 客户端支持的fastboot协议版本
version-bootloader  Bootloader的版本号
version-baseband    基带版本
product             产品名称
serialno             产品序列号
secure              返回yes 表示在刷机时需要获取签名
hwrev               硬件环境

fastboot getvar all  
// 输出
(bootloader) version:0.5
(bootloader) battery-soc-ok:yes
(bootloader) battery-voltage:4082000
(bootloader) variant:QRD eMMC
(bootloader) secure_state:secure
(bootloader) secure:yes
(bootloader) version-baseband:
(bootloader) version-bootloader:
(bootloader) display-panel:
(bootloader) off-mode-charge:0
(bootloader) charger-screen-enabled:0
(bootloader) max-download-size: 0x20000000
(bootloader) partition-type:cache:ext4
(bootloader) partition-size:cache:       0x10000000
(bootloader) partition-type:userdata:ext4
(bootloader) partition-size:userdata:    0x5d3bf7b98
(bootloader) partition-type:system:ext4
(bootloader) partition-size:system:      0x100000000
(bootloader) chipid:721QZY132E2D6B8500000031000000650000003200000065
(bootloader) serialno:721Qxxxxsafa
(bootloader) kernel:lk
(bootloader) product:MSM8953
all:
finished. total time: 0.256s



fastboot getvar all  
// 输出
(bootloader) version: 0.5
(bootloader) version-bootloader: MBM-4.3
(bootloader) product: xmail
(bootloader) board: xmail
(bootloader) secure: no
(bootloader) hwrev: caac
(bootloader) radio: 1
(bootloader) storage-type: emmc
(bootloader) emmc: 32GB SAMSUNG QD63MB RV=08 PV=01 FV=0000000000000001
(bootloader) ram: 2GB SAMSUNG LP3 DIE=8Gb M5=01 M6=06 M7=00 M8=1F
(bootloader) cpu: SDM632
(bootloader) serialno: daafafaa
(bootloader) cid: 0xDEAD
(bootloader) channelid: 0x00
(bootloader) uid: 9A6BE31F00000000000000000000
(bootloader) securestate: engineering
(bootloader) iswarrantyvoid: n/a
(bootloader) max-download-size: 536870912
(bootloader) reason: Reboot mode set to fastboot
(bootloader) imei: 359525090009484
(bootloader) meid:
(bootloader) date: 08-10-2018
(bootloader) sku: XT1955-5
(bootloader) carrier_sku: XT1955-5
(bootloader) battid: SB18C28957
(bootloader) iccid:
(bootloader) cust_md5:
(bootloader) max-sparse-size: 268435456
(bootloader) current-time: "Thu Dec  6 13:57:39 UTC 2018"
(bootloader) frp-state: no protection (0)
(bootloader) ro.carrier: unknown
(bootloader) current-slot: _a
(bootloader) running-boot-lun: 0
(bootloader) running-slot: _a
(bootloader) slot-suffixes: _a,_b
(bootloader) slot-count: 2
(bootloader) slot-successful:_a: Yes
(bootloader) slot-successful:_b: No
(bootloader) slot-bootable:_a: Yes
(bootloader) slot-bootable:_b: Yes
(bootloader) slot-retry-count:_a: 6
(bootloader) slot-retry-count:_b: 0
all: listed above
finished. total time: 0.564s
```
# H
# I
# J
# K


# L
# M
# N
# O

## fastboot oem lock

```
fastboot oem lock    // 危险 无法进去bootlaoder 重新锁定 bootloader 

// 输出
(bootloader) Not in unlocked state
OKAY [  0.004s]
finished. total time: 0.019s

```

## fastboot oem unlock
```
fastboot oem unlock    // 解锁bootloader

// 输出
(bootloader) feature disabled
OKAY [  0.008s]
finished. total time: 0.024s
```

##  fastboot oem device-info
```

fastboot oem device-info        // 查看设备信息

// 输出
(bootloader)    Device tampered: false
(bootloader)    Device unlocked: false
(bootloader)    Device critical unlocked: false
(bootloader)    Charger screen enabled: false
(bootloader)    Display panel:
OKAY [  0.058s]
finished. total time: 0.063s
```

## fastboot oem help
```

fastboot oem  help
(bootloader) config...
(bootloader) hw...
(bootloader) partition...
(bootloader) blankflash
(bootloader) fb_mode_set
(bootloader) fb_mode_clear
(bootloader) bp-tools-on
(bootloader) bp-tools-off
(bootloader) qcom-on
(bootloader) qcom-off
(bootloader) build-signature
(bootloader) display...
(bootloader) backlight...
(bootloader) logo...
(bootloader) unlock
(bootloader) lock
(bootloader) get_unlock_data
(bootloader) cid_prov_req
(bootloader) ssm_test...
(bootloader) ssm...
(bootloader) wptest...
(bootloader) logdump
(bootloader) shwi_test
(bootloader) prod_fuse
(bootloader) regex...
(bootloader) pmic...
(bootloader) led...
(bootloader) read_sv
(bootloader) sp_test...
(bootloader) off-mode-charge
(bootloader) show_screen
(bootloader) batt-voltage...
(bootloader) usb_tune...
(bootloader) dt...
(bootloader) hwid...
(bootloader) prov_test...
(bootloader) ramdump...
OKAY [  0.179s]
Finished. Total time: 0.182s


```
# P
# Q
# R

## fastboot reboot
```
fastboot reboot       // 重新启动手机  效果同  fastboot continue

// 输出
erasing 'userdata'...
OKAY [  0.368s]
formatting 'userdata' partition...
Erase successful, but not automatically formatting.
File system type raw not supported.
OKAY [  0.080s]
erasing 'cache'...
OKAY [  0.016s]
formatting 'cache' partition...
Erase successful, but not automatically formatting.
File system type  not supported.
OKAY [  0.082s]
finished. total time: 0.602s

```

## fastboot reboot-bootloader
```
fastboot reboot-bootloader           // 再一次从fastboot模式重新启动  启动到fastboot模式

// 输出
rebooting into bootloader...
OKAY [  0.012s]
finished. total time: 0.028s


```
# S

# T
# U

## fastboot update update.zip
```
fastboot update update.zip        // 刷入升级包


```
# V
# W

## fastboot -w 
```
fastboot -w             //erase userdata and cache清楚用户数据以及对应缓存
                         

```
# X
# Y
# Z