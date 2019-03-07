# A



# B
# C
# D

## dumps(dir)
```

/data/vendor/ss-ram-dumps/bp-dumps/20190226xxxxxx/ramdump_wcss_msa0_xxxxx.bin
/data/vendor/ss-ram-dumps/riva-dumps/






could you help to provide log file to resolve this issure?
input command below on shell to get the file . file path is /data/ss-ram-dumps
--------------------
adb shell
$cd sdcard
$mkdir ss-ram-dumps
$ cp -r /data/vendor/ss-ram-dumps . 
adb pull /sdcard/ss-ram-dumps .


```

# E
## PRONTO_MR.elf|WLAN_MERGED.elf
```
vendor/qcom/nonhlos/WLAN.HL.xxx/wcnss_proc/build/ms/xxx型号_MERGED_WLAN.elf

/wlan_proc/build/ms/WLAN_MERGED.elf 
==M6150_WLAN_MERGED.elf

vendor/qcom/nonhlos/WLAN.HL.xxx/wcnss_proc/build/ms/M6150_WLAN_MERGED.elf

```

## pd_dump_wlan_process.00.elf
```
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.00.elf  【在 bug2go中有】
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.count
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.01.elf

```

# F
## fireware
```

adb pull   /vendor/fireware_mnt/image/wlanmdsp.mbn
adb pull  /vendor/fireware_mnt/image/Data.msc



fastboot oem ssm_test
adb root & adb disable-verity & adb remount & adb shell mount -o rw, remount /vendor/firmware_mnt

adb push wlanmdsp.mbn  /vendor/fireware_mnt/image/
adb push Data.msc  /vendor/fireware_mnt/image/


```
# G
# H

## about.html
```
AOSP:
/vendor/qcom/nonhlos/common/about.html


```

# I
# J
# K
# L
# M

## Modem symbol
```
Artifactory中依据 PDF29.xx 找到 ReleaseNote.html 中的 Modem_ReleaseNote 就能找到对应的 modem_symbol 文件


1.  \modem_proc\build\ms\xxx_ELF_LOADER.elf 
2.  \modem_proc\build\ms\orig_MODEM_PROC_IMG_xxx.elf 
3.  \modem_proc\core\bsp\core_user_pic\build\xxx\CORE_USER.so 
4.   \modem_proc\build\myps\qshrink\msg_hash_xxxx.qsr4 


CORE_USER.so
M6150sm6150.gen.prodQ00364_ELF_LOADER.elf
msg_hash_e799c8e2-4367-a5f7-3e94-8f7455fdffae.qsr4
orig_MODEM_PROC_IMG_sm6150.gen.prodQ.elf

about.html 中 MPSS 中有版本号




```

## Flash_Modem
```

AT4.3_xxxxxxMxxxx_sideload 文件夹

adb 模式下 点击  文件夹内  sideloadmodem.bat  即可完成 Modem的 flash
```


# N
# O
## vmlinux.o
```
out/target/product/xxxxx/obj/KERNEL_OBJ/vmlinux.o

```
# P

# Q
# R
# S
# T
# U
## wlan.ko.unstripped
```
out/target/product/xxx/obj/vendor/qcom/opensource/wlan/prima/pronto_wlan.ko.unstripped
out/target/product/xxx/obj/vendor/qcom/opensource/wlan/qcacld/qca_wlan.ko.unstripped

```
# V

# W
# X
# Y
# Z


# Operation
```
iwpriv wlan0 crash_inject 1 0        可以手动触发一个dump



adb shell iwpriv wlan0 version 
wlan0  version:Host SW:5.1.1.28U, FW:1.0.1.350.0, HW:HW_VERSION=40050000.

```