




# A
# B
# C
# D
# E
# F

## framework.jar
```
adb disable-verify & adb reboot
adb root & adb remount & adb push ./framework.jar /system/framework/ &adb push ./boot.art /system/framework/arm64/ & adb push ./boot.oat /system/framework/arm64/ & adb reboot


out/.../system/framework/arm64/目录下的boot.art和boot.oat也push进去,重启,改动的地方才会起效!



http://androidxref.com/9.0.0_r3/xref/
http://androidxref.com/9.0.0_r3/xref/frameworks/base/
http://androidxref.com/9.0.0_r3/xref/frameworks/base/Android.mk


http://androidxref.com/9.0.0_r3/xref/frameworks/base/Android.bp
frameworks/base/

java_library {
    name: "framework",   // 【 生成 framework.jar 】
    srcs: [
        "wifi/java/android/net/wifi/ISoftApCallback.aidl",
        "wifi/java/android/net/wifi/IWifiManager.aidl",
            ..............
         ]
      aidl: {
             export_include_dirs: [
            "core/java",
            "graphics/java",
            "location/java",
            .....
            ],
            
       }
         .....
}

```

## framework-res.apk
```
adb disable-verify & adb reboot
adb root & adb remount & adb push ./framework-res.apk  /system/framework/ & adb reboot

http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/res/res/values-zh-rCN/strings.xml#1172
<string name="wifi_available_sign_in" msgid="9157196203958866662">"登录到WLAN网络"</string>
<string name="wifi_available_sign_in" msgid="9157196203958866662">"登录到WLAN网络zukgit"</string>    //  查看framework-res.apk 是否生效

http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/res/Android.bp
frameworks/base/core/res/

android_app {
    name: "framework-res",      //【生成 framework-res.apk 】
    no_framework_libs: true,
    certificate: "platform",

    // Soong special-cases framework-res to install this alongside
    // the libraries at /system/framework/framework-res.apk.

    // Generate private symbols into the com.android.internal.R class
    // so they are not accessible to 3rd party apps.
    aaptflags: [
        "--private-symbols",
        "com.android.internal",

        // Framework doesn't need versioning since it IS the platform.
        "--no-auto-version",

        // Allow overlay to add resource
        "--auto-add-overlay",
    ],

    // Create package-export.apk, which other packages can use to get
    // PRODUCT-agnostic resource data like IDs and type definitions.
    export_package_resources: true,
}


```

# G
# H
# I
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
## Settings.apk
```
adb root & adb remount &  adb install -r ./Settings.apk
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/
/packages/apps/Settings/
/packages/apps/Settings/

http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/Android.mk
/packages/apps/Settings/Android.mk
LOCAL_PACKAGE_NAME := Settings
include $(BUILD_PACKAGE)
```

# T
# U
# V
# W
## wifi-service.jar
```
adb root & adb remount & adb push ./wifi-service.jar  /system/framework/

http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/Android.mk
/frameworks/opt/net/wifi/service
LOCAL_MODULE := wifi-service

```

## wpa_supplicant
# X
# Y
# Z

# 系统文件列表

## /   根目录
```
/ # ls -la
total 1728
drwxr-xr-x  23 root   root      4096 2009-01-01 14:00 .
drwxr-xr-x  23 root   root      4096 2009-01-01 14:00 ..
dr-xr-xr-x 101 system system       0 2018-12-11 04:58 acct
lrw-r--r--   1 root   root        11 2009-01-01 14:00 bin -> /system/bin
lrw-r--r--   1 root   root        19 2009-01-01 14:00 bt_firmware -> /vendor/bt_firmware
lrw-r--r--   1 root   root        11 2009-01-01 14:00 cache -> /data/cache
lrw-r--r--   1 root   root        13 2009-01-01 14:00 charger -> /sbin/charger
drwxr-xr-x   4 root   root         0 1970-01-01 08:00 config
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 customize
lrw-r--r--   1 root   root        17 2009-01-01 14:00 d -> /sys/kernel/debug
drwxrwx--x  45 system system    4096 2018-12-11 03:18 data
lrw-------   1 root   root        23 2009-01-01 14:00 default.prop -> system/etc/prop.default
drwxr-xr-x  16 root   root      3800 2018-12-11 02:19 dev
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 dsp
lrw-r--r--   1 root   root        11 2009-01-01 14:00 etc -> /system/etc
lrw-r--r--   1 root   root        20 2009-01-01 14:00 firmware -> /vendor/firmware_mnt
-rwxr-x---   1 root   shell  1593796 2009-01-01 14:00 init
-rwxr-x---   1 root   shell     1273 2009-01-01 14:00 init.environ.rc
-rwxr-x---   1 root   shell        0 2009-01-01 14:00 init.qcom.build.rc
-rwxr-x---   1 root   shell    31208 2009-01-01 14:00 init.rc
-rwxr-x---   1 root   shell     3171 2009-01-01 14:00 init.recovery.qcom.rc
-rwxr-x---   1 root   shell     8563 2009-01-01 14:00 init.usb.configfs.rc
-rwxr-x---   1 root   shell     5646 2009-01-01 14:00 init.usb.rc
-rwxr-x---   1 root   shell      511 2009-01-01 14:00 init.zygote32.rc
drwx------   2 root   root     16384 2009-01-01 14:00 lost+found
drwxr-xr-x  12 root   system     260 1970-01-02 16:04 mnt
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 modem
-rw-r--r--   1 root   root       574 2009-01-01 14:00 module_hashes
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 odm
drwxr-xr-x   4 root   root      4096 2018-12-11 04:00 oem
lrw-r--r--   1 root   root         8 2009-01-01 14:00 pds -> /persist
drwxrwx--x  30 root   system    4096 1970-01-01 10:27 persist
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 postinstall
drwxr-xr-x   2 root   root      4096 2009-01-01 14:00 postinstall_oem
dr-xr-xr-x 605 root   root         0 1970-01-01 08:00 proc
lrw-r--r--   1 root   root        15 2009-01-01 14:00 product -> /system/product
drwxr-xr-x   3 root   root      4096 2009-01-01 14:00 res
drwxr-x---   2 root   shell     4096 2009-01-01 14:00 sbin
lrw-r--r--   1 root   root        21 2009-01-01 14:00 sdcard -> /storage/self/primary
drwxr-xr-x   4 root   root        80 2018-12-11 02:19 storage
dr-xr-xr-x  14 root   root         0 1970-01-02 16:04 sys
drwxr-xr-x  14 root   root      4096 2009-01-01 14:00 system
lrw-r--r--   1 root   root        16 2009-01-01 14:00 tombstones -> /data/tombstones
-rw-r--r--   1 root   root      5400 2009-01-01 14:00 ueventd.rc
drwxr-xr-x  16 root   root      4096 2009-01-01 14:00 vendor
-rw-r--r--   1 root   root       524 2009-01-01 14:00 verity_key


```

## /system
```

1|M6Note:/system $ ls -la
ls: ./rfs: Permission denied
total 9512
drwxr-xr-x 21 root root     4096 1970-01-01 08:00 .
drwxrwxrwt 21 root root     1200 1970-03-16 21:50 ..
drwxr-xr-x 32 root root     4096 2009-01-01 00:00 MzApp
-rw-r--r--  1 root root    31723 2009-01-01 00:00 WCNSS_qcom_wlan_nv.bin
drwxr-xr-x 82 root root     4096 2009-01-01 00:00 app
drwxr-xr-x  3 root shell    8192 2009-01-01 00:00 bin
-rw-r--r--  1 root root    13077 2009-01-01 00:00 build.prop
-rw-r--r--  1 root root    12718 2009-01-01 00:00 build.prop.bakforspec
drwxr-xr-x  5 root root     4096 2009-01-01 00:00 customizecenter
drwxr-xr-x 20 root root     4096 2009-01-01 00:00 etc
drwxr-xr-x  2 root root     4096 2009-01-01 00:00 fake-libs
drwxr-xr-x  2 root root     4096 2009-01-01 00:00 fake-libs64
drwxr-xr-x  2 root root     8192 2009-01-01 00:00 fonts
drwxr-xr-x  6 root root     4096 2009-01-01 00:00 framework
drwxr-xr-x  8 root root    12288 2009-01-01 00:00 lib
drwxr-xr-x  7 root root    12288 2009-01-01 00:00 lib64
drwx------  2 root root     4096 1970-01-01 08:00 lost+found
drwxr-xr-x  4 root root     4096 2009-01-01 00:00 media
drwxr-xr-x 78 root root     4096 2009-01-01 00:00 priv-app
-rw-r--r--  1 root root  4698542 2009-01-01 00:00 recovery-from-boot.p
drwxr-xr-x  3 root root     4096 2009-01-01 00:00 tts
drwxr-xr-x  8 root root     4096 2009-01-01 00:00 usr
drwxr-xr-x 60 root shell    4096 2009-01-01 00:00 vendor
drwxr-xr-x  2 root shell    4096 2009-01-01 00:00 xbin


```

## /system/etc

```
/system/etc # ls -la
total 1856
drwxr-xr-x 29 root root   4096 2009-01-01 14:00 .
drwxr-xr-x 17 root root   4096 2009-01-01 14:00 ..
-rw-r--r--  1 root root 256179 2009-01-01 14:00 NOTICE.xml.gz
-rw-r--r--  1 root root  61990 2009-01-01 14:00 NT36xxx_MP_Setting_Criteria_600E.csv
-rw-r--r--  1 root root 741124 2009-01-01 14:00 apns-conf.xml
-rw-r--r--  1 root root   5668 2009-01-01 14:00 audio_effects.conf
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 bluetooth
-rw-r--r--  1 root root  77860 2009-01-01 14:00 boot-image.prof
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 bpf
-rw-r--r--  1 root root    407 2009-01-01 14:00 buffers-conf.xml
-rw-r--r--  1 root root   1613 2009-01-01 14:00 call_matching.xml
-rw-r--r--  1 root root   1045 2009-01-01 14:00 clatd.conf
drwxr-xr-x  3 root root   4096 2009-01-01 14:00 cne
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 default-permissions
-rw-r--r--  1 root root   6062 2009-01-01 14:00 dirty-image-objects
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 enable-disable-packages
-rw-r--r--  1 root root  24549 2009-01-01 14:00 event-log-tags
drwxr-xr-x  3 root root   4096 2009-01-01 14:00 firmware
-rw-r--r--  1 root root  30581 2009-01-01 14:00 fonts.xml
-r--r--r--  1 root root    120 2009-01-01 14:00 fs_config_dirs
-r--r--r--  1 root root   1256 2009-01-01 14:00 fs_config_files
-rw-r--r--  1 root root    148 2009-01-01 14:00 fstab.install.oem
-rw-r--r--  1 root root    150 2009-01-01 14:00 fstab.install.sys
-rw-r--r--  1 root root   3221 2009-01-01 14:00 gamedwhitelist.xml
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 hostapd
-rw-r--r--  1 root root     56 2009-01-01 14:00 hosts
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 init
-rw-r--r--  1 root root   1987 2009-01-01 14:00 init.qcom.testscripts.sh
-rw-r--r--  1 root root  62213 2009-01-01 14:00 ixitdata.xml
-rw-r--r--  1 root root  25696 2009-01-01 14:00 ld.config.28.txt
-rw-r--r--  1 root root    289 2009-01-01 14:00 llndk.libraries.28.txt
-rw-r--r--  1 root root  25185 2009-01-01 14:00 media_profiles.xml
-rw-r--r--  1 root root  22947 2009-01-01 14:00 media_profiles_8953_v1.xml
-rw-r--r--  1 root root   2727 2009-01-01 14:00 media_profiles_V1_0.dtd
-rw-r--r--  1 root root   1178 2009-01-01 14:00 mke2fs.conf
-rw-r--r--  1 root root    375 2009-01-01 14:00 mkshrc
drwxr-xr-x  3 root root   4096 2009-01-01 14:00 mmi
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 nondisable
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 notiflistenerhideordisableui
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 perf
drwxr-xr-x  2 root root   8192 2009-01-01 14:00 permissions
-rw-r--r--  1 root root   7067 2009-01-01 14:00 powerhint.xml
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 ppp
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 preferred-apps
-rw-r--r--  1 root root 257506 2009-01-01 14:00 preloaded-classes
-rw-------  1 root root   1730 2009-01-01 14:00 prop.default
-rw-r--r--  1 root root      0 2009-01-01 14:00 public.libraries-qti.txt
-rw-r--r--  1 root root    432 2009-01-01 14:00 public.libraries.txt
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 regulatory
-rw-r--r--  1 root root    328 2009-01-01 14:00 rtt-conf.xml
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 seccomp_policy
drwxr-xr-x  5 root root   4096 2009-01-01 14:00 security
drwxr-xr-x  3 root root   4096 2009-01-01 14:00 selinux
-rw-r--r--  1 root root      0 2009-01-01 14:00 sepolicy_freeze_test
-rw-r--r--  1 root root      0 2009-01-01 14:00 sepolicy_tests
-rw-r--r--  1 root root   1311 2009-01-01 14:00 sig-permission.xml
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 sysconfig
-rw-r--r--  1 root root    395 2009-01-01 14:00 system_sign_info
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 textclassifier
-rw-r--r--  1 root root   2579 2009-01-01 14:00 thermal-engine-addison.conf
-rw-r--r--  1 root root   2542 2009-01-01 14:00 thermal-engine-albus.conf
-rw-r--r--  1 root root   3253 2009-01-01 14:00 thermal-engine-ali.conf
-rw-r--r--  1 root root   3253 2009-01-01 14:00 thermal-engine-ali1.conf
-rw-r--r--  1 root root   5262 2009-01-01 14:00 thermal-engine-ali2.conf
-rw-r--r--  1 root root   2226 2009-01-01 14:00 thermal-engine-deen.conf
-rw-r--r--  1 root root   2542 2009-01-01 14:00 thermal-engine-johnson.conf
-rw-r--r--  1 root root   2530 2009-01-01 14:00 thermal-engine-ocean.conf
-rw-r--r--  1 root root   2579 2009-01-01 14:00 thermal-engine-potter.conf
-rw-r--r--  1 root root      0 2009-01-01 14:00 treble_sepolicy_tests_26.0
-rw-r--r--  1 root root      0 2009-01-01 14:00 treble_sepolicy_tests_27.0
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 update_engine
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 updatecmds
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 updates
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 vintf
-rw-r--r--  1 root root    638 2009-01-01 14:00 vndksp.libraries.28.txt
-rw-r--r--  1 root root   7472 2009-01-01 14:00 voicemail-conf.xml
-rw-r--r--  1 root root   1630 2009-01-01 14:00 vold.fstab
-rw-r--r--  1 root root   3610 2009-01-01 14:00 whitelistedapps.xml
drwxr-xr-x  2 root root   4096 2009-01-01 14:00 wifi
-rw-r--r--  1 root root    714 2009-01-01 14:00 wigig_logcollector.ini
-rw-r--r--  1 root root      0 2009-01-01 14:00 xtables.lock


```


## /system/framework

```

M6Note:/system/framework $ ls -la
total 34216
drwxr-xr-x  6 root root     4096 2009-01-01 00:00 .
drwxr-xr-x 21 root root     4096 1970-01-01 08:00 ..
-rw-r--r--  1 root root     6389 2009-01-01 00:00 ConnectivityExt.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 QPerformance.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 QtiTelephonyServicelibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 WfdCommon.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 am.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 android.test.runner.jar
-rw-r--r--  1 root root    17632 2009-01-01 00:00 apache-xml.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 appwidget.jar
drwxr-xr-x  2 root root     4096 2009-01-01 00:00 arm
drwxr-xr-x  2 root root     4096 2009-01-01 00:00 arm64
-rw-r--r--  1 root root      310 2009-01-01 00:00 bmgr.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 bouncycastle.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 btmultisimlibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 bu.jar
-rw-r--r--  1 root root     5726 2009-01-01 00:00 cneapiclient.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.android.future.usb.accessory.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.android.location.provider.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.android.media.remotedisplay.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.android.mediadrm.signer.jar
-rw-r--r--  1 root root   174483 2009-01-01 00:00 com.google.android.maps.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.google.widevine.software.drm.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.meizu.camera.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.qrd.wappush.jar
-rw-r--r--  1 root root    45208 2009-01-01 00:00 com.qti.dpmframework.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.qti.location.sdk.jar
-rw-r--r--  1 root root    21030 2009-01-01 00:00 com.qti.snapdragon.sdk.display.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 com.qualcomm.location.vzw_library.jar
-rw-r--r--  1 root root   129081 2009-01-01 00:00 com.quicinc.cne.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 conscrypt.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 content.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 core-junit.jar
-rw-r--r--  1 root root    17017 2009-01-01 00:00 core-libart.jar
-rw-r--r--  1 root root   121425 2009-01-01 00:00 core-oj.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 dpm.jar
-rw-r--r--  1 root root     9715 2009-01-01 00:00 dpmapi.jar
-rw-r--r--  1 root root     6736 2009-01-01 00:00 embmslibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 ethernet-service.jar
-rw-r--r--  1 root root  1649624 2009-01-01 00:00 ext.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 flyme-alipay.jar
-rw-r--r--  1 root root  2092803 2009-01-01 00:00 flyme-framework.jar
drwxr-xr-x  2 root root     4096 2009-01-01 00:00 flyme-res
-rw-r--r--  1 root root      310 2009-01-01 00:00 flyme-telephony-common.jar
-rw-r--r--  1 root root 12754970 2009-01-01 00:00 framework-res.apk
-rw-r--r--  1 root root      126 2009-01-01 00:00 framework.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 hid.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 ime.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 ims-common.jar
-rw-r--r--  1 root root     5207 2009-01-01 00:00 imscmlibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 input.jar
-rw-r--r--  1 root root     6093 2009-01-01 00:00 izat.xt.srv.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 javax.obex.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 media_cmd.jar
-rw-r--r--  1 root root    80144 2009-01-01 00:00 meizu2_jcifs.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 monkey.jar
drwxr-xr-x  4 root root     4096 2009-01-01 00:00 oat
-rw-r--r--  1 root root     1174 2009-01-01 00:00 oem-services.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 okhttp.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 org.apache.http.legacy.boot.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 org.codeaurora.camera.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 org.simalliance.openmobileapi.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 pm.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 qcmediaplayer.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 qcrilhook.jar
-rw-r--r--  1 root root     9757 2009-01-01 00:00 qmapbridge.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 qti-telephony-common.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 rcs_service_aidl.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 rcs_service_api.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 rcscommon.jar
-rw-r--r--  1 root root    17139 2009-01-01 00:00 rcsimssettings.jar
-rw-r--r--  1 root root     9140 2009-01-01 00:00 rcsservice.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 requestsync.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 services.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 settings.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 sm.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 smartsearch.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 svc.jar
-rw-r--r--  1 root root     4146 2009-01-01 00:00 tcmclient.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 tcmiface.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 telecom.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 telephony-common.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 telephony-ext.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 uiautomator.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 uimlpalibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 uimremoteclientlibrary.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 vcard.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 voip-common.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 wifi-service.jar
-rw-r--r--  1 root root      310 2009-01-01 00:00 wm.jar

```

## /vendor/etc/wifi
```
/vendor/etc/wifi # ls -la
total 208
drwxr-xr-x  2 root shell  4096 2009-01-01 14:00 .
drwxr-xr-x 20 root shell  4096 2009-01-01 14:00 ..
-rw-r--r--  1 root root   8515 2009-01-01 14:00 WCNSS_qcom_cfg.ini
-rw-r--r--  1 root root  31723 2009-01-01 14:00 WCNSS_qcom_wlan_nv.bin
-rw-r--r--  1 root root  31723 2009-01-01 14:00 WCNSS_qcom_wlan_nv_Argentina.bin
-rw-r--r--  1 root root  31723 2009-01-01 14:00 WCNSS_qcom_wlan_nv_Brazil.bin
-rw-r--r--  1 root root  31723 2009-01-01 14:00 WCNSS_qcom_wlan_nv_India.bin
-rw-r--r--  1 root root  31723 2009-01-01 14:00 WCNSS_qcom_wlan_nv_epa.bin
-rw-r--r--  1 root root  10419 2009-01-01 14:00 WCNSS_wlan_dictionary.dat
-rw-r--r--  1 root root    394 2009-01-01 14:00 fstman.ini
-rw-r--r--  1 root root     67 2009-01-01 14:00 p2p_supplicant_overlay.conf
-rw-r--r--  1 root root    191 2009-01-01 14:00 wpa_supplicant.conf
-rw-r--r--  1 root root     88 2009-01-01 14:00 wpa_supplicant_overlay.conf

```
## /vendor/etc/wifi/wpa_supplicant.conf
```
update_config=1
ctrl_interface=wlan0
eapol_version=1
ap_scan=1
fast_reauth=1
p2p_add_cli_chan=1
p2p_no_group_iface=1
config_methods=virtual_display virtual_push_button
disable_scan_offload=1


```

## /vendor/etc/wifi/wpa_supplicant_overlay.conf
```
disable_scan_offload=1
p2p_disabled=1
tdls_external_control=1
wowlan_triggers=magic_pkt

```
