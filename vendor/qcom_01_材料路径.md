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
## PRONTO_MR.elf|WLAN.elf
```
vendor/qcom/nonhlos/WLAN.HL.xxx/wcnss_proc/build/ms/xxx_WLAN.elf

```

## pd_dump_wlan_process.00.elf
```
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.00.elf  【在 bug2go中有】
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.count
/data/vendor/tombstones/rfs/modem/pd_dump_wlan_process.01.elf

```

# F
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