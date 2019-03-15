# A
# B

## adb bugreport
```
// 期间完成测试 在完成进度100%会在 手机存储一个 bugreport_XXX_xxx.zip 文件
adb bugreport    //会有从开机之后详细的dumpsys,dumpstate和logcat信息，是一份完整的日志记录 

```
# C
# D

##  adb devices
```

adb devices // 一台电脑连接两手机时 分别 adb shell
List of devices attached
emulator-5554 device
SH0A6PL00243 device

adb -s emulator-5554 shell       // 连接两台手机中的一台 adb shell
adb -s SH0A6PL00243 shell

```
# E
# F
# G
# H
# I

## adb install
```
adb install       安装APK命令
adb install -r D:/FmRadio.apk         // 安装替换FmRadio.apk

```
<img src="//../zimage/tool/adb/adb01.png" />
# J
# K
## adb kill-server
```
adb kill-server     // 关闭ADB服务


```
# L

##  adb logact 
```
adb logact        // 打印手机Log

adb logact -s “zukgit”           // 查看tag为 zukgit 的Log信息

adb logact -> D:/test.txt            // 把log信息传输到D:/test.txt 文件

adb logact | grep “hello”           // 查看log并过滤出含有hello的Log信息(Linux)
adb logact | findstr “hello”           // 查看log并过滤出含有hello的Log信息(Windows)

```
# M
# N
# O
# P
##  adb push
```
adb push       // PC文件移动到手机存储的命令


adb push D:/libmedia.so /system/lib                      // 把本地的D盘文件拷贝到安卓系统的 /system/lib 目录下 并赋权限644
chmod 644 /system/lib/libmedia.so


```

## adb pull 
```
adb pull                         // 把手机文件下载到PC本地的命令
adb pull /system/lib/libmedia.so   ./         // 拉取 libmedia.so 到本地

```
# Q
# R

## adb remount 
```
adb remount     // 挂载 使得可以pull 和 push 拉取上传文件到手机

```


## adb reboot 
```
adb reboot    // 重启手机

```


# S
## adb start-server
```
adb start-server    // 开启ADB服务

```
##  adb shell
```
adb shell          // 进入到手机内置Linux的shell 的命令

```
##  adb shell getprop

```
adb shell getprop            // 查看系统配置属性
adb shell getprop  | findstr "wifi"         // Windos的CMD下
getprop  | grep "wifi"                    // Linux Shell内部


adb shell getprop  | findstr "wifi" 
[debug.settings.wifi]: [0]
[net.tcp.buffersize.wifi]: [524288,2097152,4194304,262144,524288,1048576]
[net_wifi.fwpath]: [sta]
[qc.izat.gtp.wifi.low_power]: [DISABLED]
[ro.wifi.channels]: []
[wifi.interface]: [wlan0]
[wlan.driver.config]: [/data/misc/wifi/WCNSS_qcom_cfg.ini]


getprop | grep -e "wlan" -e "wifi"
[debug.settings.wifi]: [0]
[net.tcp.buffersize.wifi]: [524288,2097152,4194304,262144,524288,1048576]
[net_wifi.fwpath]: [sta]
[persist.data.iwlan.enable]: [true]
[persist.sys.cnd.iwlan]: [1]
[qc.izat.gtp.wifi.low_power]: [DISABLED]
[ro.wifi.channels]: []
[wifi.interface]: [wlan0]
[wlan.dbg]: [false]
[wlan.driver.ath]: [0]
[wlan.driver.config]: [/data/misc/wifi/WCNSS_qcom_cfg.ini]
[wlan.driver.status]: [ok]
```


##  adb shell service list 
```
adb shell service list     // windows CMD下 查询系统服务列表             ps   | grep servicemanager     可查看具体进程
adb shell service list  | findstr "wifi"
service list    // Linux_shell 内
service list | grep -e "wlan" -e "wifi"        // Linux_shell 内


 ps   | grep servicemanager
system    584   1     9568   588   binder_thr 0000000000 S /system/bin/servicemanager

service list | grep -e "wlan" -e "wifi"
79      rttmanager: [android.net.wifi.IRttManager]
80      wifiscanner: [android.net.wifi.IWifiScanner]
81      wifi: [android.net.wifi.IWifiManager]
82      wifip2p: [android.net.wifi.p2p.IWifiP2pManager]


adb shell service list  
C:\Users\aaa>adb shell service list
Found 163 services:
0       call_adapter: [com.android.internal.telecom.IMzInCallAdapter]
1       qtitetherservice: [com.qualcomm.qti.tetherstatsextension.ITetherService]
2       dpmservice: [com.qti.dpm.IDpmService]
...........
158     gpu: [android.ui.IGpuService]
159     SurfaceFlinger: [android.ui.ISurfaceComposer]
160     display.qservice: [android.display.IQService]
161     vendor.qcom.PeripheralManager: [vendor.qcom.IPeripheralManager]
162     eSEPowerManagerService: [IeSEPowerManager]


```

## adb shell setprop
```
adb shell setprop  Key键  Value值     设置宏变量

[ro.product.brand]: [Meizu]
adb shell setprop  ro.product.brand   MEIZU      // 把宏变量 ro.product.brand 改为 MEIZU

```
##  adb shell screenrecord
```
adb shell screenrecord    // 手机录屏命令


adb shell screenrecord /sdcard/demo.mp4                  // 录制屏幕视频 命令为demo 格式为mp4 保存路径是  /sdcard/
```
## adb shell dumpsys activity
```
adb shell dumpsys activity               // 查看当前的任务堆栈
ACTIVITY MANAGER PENDING INTENTS (dumpsys activity intents)
  * PendingIntentRecord{3cc3b93 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f043cf9 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3a390ca com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ab9db9b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3b5687c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{5fb7505 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
...........
  mStartedUsers:
    User #0: state=RUNNING_UNLOCKED
  mStartedUserArray: [0]
  mUserLru: [0]
  mHomeProcess: ProcessRecord{ae7c33e 3137:com.meizu.flyme.launcher/u0a11}
  mPreviousProcess: ProcessRecord{9adc0ab 30463:com.android.settings/1000}
  mConfiguration: {1.0 460mcc11mnc [zh_CN] ldltr sw360dp w360dp h618dp 480dpi nrml long port finger -keyb/v/h -nav/h s.22}
  mWakefulness=Awake
  mSleepTokens=[]
  mSleeping=false mLockScreenShown=LOCK_SCREEN_HIDDEN
  mShuttingDown=false mTestPssMode=false
```

##  adb shell dumpsys activity top
```
adb shell dumpsys activity top         // 能查看当前的 View Hierarchy 信息 Framgment信息  栈顶信息
TASK com.meizu.filemanager id=11866
  ACTIVITY com.meizu.filemanager/com.meizu.flyme.filemanager.activity.CategoryActivity aad8262 pid=2923
..............
```


##  adb shell dumpsys activity services
```
 adb shell dumpsys activity services      //  查看Service组件信息

ACTIVITY MANAGER SERVICES (dumpsys activity services)
  User 0 active services:
    #0 id=130 dur=-9m17s228ms dxc=6
      intent=Intent { cmp=com.meizu.safe/com.meizu.networkmanager.service.TrafficPollingService (has extras) }
    #1 id=131 dur=-9m17s228ms dxc=6
      intent=Intent { cmp=com.meizu.safe/com.meizu.networkmanager.service.TrafficPollingService }
    #2 id=132 dur=-9m17s228ms dxc=5
      intent=Intent { cmp=com.meizu.safe/com.meizu.networkmanager.service.TrafficPollingService (has extras) }
    #3 id=133 dur=-9m17s228ms dxc=5
```


## adb shell dumpsys activity providers
```
adb shell dumpsys activity providers        // 产看ContentProvider组件信息

ACTIVITY MANAGER CONTENT PROVIDERS (dumpsys activity providers)
  Published single-user content providers (by class):
  * ContentProviderRecord{d4cd15 u0 com.android.providers.telephony/.TelephonyProvider}
    package=com.android.providers.telephony process=com.android.phone
    proc=ProcessRecord{79ebdca 2355:com.android.phone/1001}
    uid=1001 provider=android.content.ContentProviderProxy@455e82a
    singleton=true
    authority=telephony
  * ContentProviderRecord{fccd61b u0 com.android.providers.settings/.SettingsProvider}
    package=com.android.providers.settings process=system
    proc=ProcessRecord{c983991 1732:system/1000}
    uid=1000 provider=android.content.ContentProvider$Transport@5d108f1
    singleton=true
    authority=settings
    isSyncable=false multiprocess=false initOrder=100
    Connections:
      -> 2063:com.meizu.facerecognition/1000 s1/1 u0/0 +18h42m36s864ms
      -> 2077:com.android.systemui/u0a31 s1/1 u0/0 +18h42m36s751ms

```
##  adb shell dumpsys activity provider com.android.settings
```
adb shell dumpsys activity provider com.android.settings       //查看settings 的 Provider
PROVIDER ContentProviderRecord{e5f4bc0 u0 com.android.settings/.search.SettingsSearchIndexablesProvider} pid=13515
    Client:
      nothing to dump

PROVIDER ContentProviderRecord{93ac2fe u0 com.android.settings/android.support.v4.content.FileProvider} pid=13515
    Client:
      nothing to dump

PROVIDER ContentProviderRecord{d29c49d u0 com.android.settings/com.meizu.settings.search.FlymeGlobalSearchProvider} pid=13515
    Client:
      nothing to dump


```


## adb shell dumpsys media.camera
```
 adb shell dumpsys media.camera    // 查看camera的信息

Camera module HAL API version: 0x100
Camera module API version: 0x204
Camera module name: QCamera Module
Camera module author: Qualcomm Innovation Center Inc
Number of camera devices: 3
Number of normal camera devices: 3
Active Camera Clients:

Camera 0 information:
  Facing: BACK
  Orientation: 90
  Resource Cost: 100
  Conflicting Devices: NONE
  Device version: 0x100
  Camera1 API shim is using parameters:
        CameraParameters::dump: mMap.size = 192
```


##  adb shell dumpsys media.audio_flinger
```
 adb shell dumpsys media.audio_flinger                   //查看audio信息
Library livemusicMz
  Effect Live Music / Meizu Technology Co., Ltd
    UUID: 0bbc89fe-52dc-4c40-8211-cae4da538b50
    TYPE: 38a6a248-94be-4007-bbe1-337d7dc49e6d
    apiVersion: 00020000
    flags: 00000048
Library virtualizerMz
  Virtualizer / Meizu Technology Co., Ltd
    UUID: 0e9779c9-4e8f-494d-b2b1-b4ad4e37c54c
    TYPE: 725d7fa7-9219-4f71-8fa7-240a6d489387
    apiVersion: 00020000
    flags: 00000210

Reroute submix audio module:
 route[0] rate in=0 out=0, addr=[]
 route[1] rate in=0 out=0, addr=[]
 route[2] rate in=0 out=0, addr=[]
 route[3] rate in=0 out=0, addr=[]
 route[4] rate in=0 out=0, addr=[]
```


##  adb shell pm list features 
```
 adb shell pm list features      // 安卓feature
feature:reqGlEsVersion=0x30002
feature:android.hardware.audio.output
feature:android.hardware.bluetooth
feature:android.hardware.bluetooth_le
feature:android.hardware.camera
feature:android.hardware.camera.any
feature:android.hardware.camera.autofocus
feature:android.hardware.camera.flash
feature:android.hardware.camera.front
feature:android.hardware.faketouch
feature:android.hardware.fingerprint
feature:android.hardware.location
feature:android.hardware.location.gps
feature:android.hardware.location.network
feature:android.hardware.microphone
feature:android.hardware.opengles.aep
feature:android.hardware.screen.landscape
feature:android.hardware.screen.portrait
feature:android.hardware.sensor.accelerometer
feature:android.hardware.sensor.compass
feature:android.hardware.sensor.gyroscope
feature:android.hardware.sensor.light
feature:android.hardware.sensor.proximity
feature:android.hardware.sensor.stepcounter
feature:android.hardware.sensor.stepdetector
feature:android.hardware.telephony
feature:android.hardware.telephony.cdma
feature:android.hardware.telephony.gsm
feature:android.hardware.touchscreen
feature:android.hardware.touchscreen.multitouch
feature:android.hardware.touchscreen.multitouch.distinct
feature:android.hardware.touchscreen.multitouch.jazzhand
feature:android.hardware.usb.accessory
feature:android.hardware.usb.host
feature:android.hardware.vulkan.level
feature:android.hardware.vulkan.version=4194307
feature:android.hardware.wifi
feature:android.hardware.wifi.direct
feature:android.software.app_widgets
feature:android.software.backup
feature:android.software.connectionservice
feature:android.software.device_admin
feature:android.software.home_screen
feature:android.software.input_methods
feature:android.software.live_wallpaper
feature:android.software.managed_users
feature:android.software.midi
feature:android.software.print
feature:android.software.sip
feature:android.software.sip.voip
feature:android.software.voice_recognizers
feature:android.software.webview
feature:flyme.face.facerecognition
feature:flyme.hardware.screen.bluelightreduction



```
## adb shell dumpsys activity intents
```
adb shell dumpsys activity intents      // 查看Intent信息

ACTIVITY MANAGER PENDING INTENTS (dumpsys activity intents)
  * PendingIntentRecord{698a3fd com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
    uid=10049 packageName=com.meizu.cloud type=broadcastIntent flags=0x40000000
    requestIntent=act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545117758482 cmp=com.meizu.cloud/.push.CloudPushReceiver (has extras)
    whitelistDuration=+30s0ms
  * PendingIntentRecord{de81bb6 com.android.phone broadcastIntent}
    uid=1001 packageName=com.android.phone type=broadcastIntent flags=0x0
    requestIntent=act=com.android.ims.IMS_INCOMING_CALL flg=0x10000000
  * PendingIntentRecord{90970b7 com.tencent.mm broadcastIntent}
    uid=10108 packageName=com.tencent.mm type=broadcastIntent flags=0x0
    requestCode=753 requestResolvedType=null
    requestIntent=act=ALARM_ACTION(4246) (has extras)
```

## adb shell dumpsys activity processes
```
adb shell dumpsys activity processes          //查看进程信息

ACTIVITY MANAGER RUNNING PROCESSES (dumpsys activity processes)
  All known processes:
  *APP* UID 1000 ProcessRecord{b8706ca 3501:com.meizu.pps/1000}
    user #0 uid=1000 gids={41000, 9997, 3009, 3002, 1023, 1015, 3003, 3001, 1021, 1000, 2002, 2950, 1010, 1007, 3006}
    requiredAbi=arm64-v8a instructionSet=arm64
    class=com.meizu.pps.PPSApplication
    dir=/system/priv-app/AppSandbox/AppSandbox.apk publicDir=/system/priv-app/AppSandbox/AppSandbox.apk data=/data/user/0/com.meizu.pps
    packageList={com.meizu.pps}
    compat={480dpi}
    thread=android.app.ApplicationThreadProxy@38e91a4
    pid=3501 starting=false
    lastActivityTime=-9h13m30s289ms lastPssTime=-3m46s535ms nextPssTime=+11m13s96ms
    adjSeq=105566 lruSeq=0 lastPss=19MB lastSwapPss=2.1MB lastCachedPss=0.00 lastCachedSwapPss=0.00
    cached=false empty=true
    oom: max=1001 curRaw=100 setRaw=100 cur=100 set=100
    curSchedGroup=1 setSchedGroup=1 systemNoUi=false trimMemoryLevel=0
    vrThreadTid=0    curProcState=6 repProcState=6 pssProcState=6 setProcState=6 lastStateTime=-9h13m30s289ms
    reportedInteraction=true time=-18h50m44s953ms
    lastRequestedGc=-2h44m34s239ms lastLowMemory=-2h47m38s22ms reportLowMemory=false

```

## adb shell pm list packages

```
adb shell pm list packages -f        // 查看所有已经安装的应用
adb shell pm list packages -3        //查看系统预先安装的应用


package:/data/app/in.zhaoj.shadowsocksrr-1/base.apk=in.zhaoj.shadowsocksrr
package:/system/app/Bluetooth/Bluetooth.apk=com.android.bluetooth
package:/data/app/com.meizu.media.life-1/base.apk=com.meizu.media.life
package:/system/app/TimeService/TimeService.apk=com.qualcomm.timeservice
package:/system/app/embms/embms.apk=com.qualcomm.embms

```

## adb shell screencap
```
adb shell screencap         // 是截屏命令
adb shell screencap -p /sdcard/screen.png           // 截屏到手机
adb pull /sdcard/screen.png  ./                      // 截屏传到本地

```

##  adb shell dumpsys cpuinfo 
```

adb shell dumpsys cpuinfo              //显示CPU使用信息

Load: 6.79 / 6.73 / 6.75
CPU usage from 168984ms to 151559ms ago (2018-12-17 18:17:27.438 to 2018-12-17 18:17:44.863):
  11% 585/surfaceflinger: 3.8% user + 7.9% kernel / faults: 1 minor
  8.9% 10805/com.ss.android.article.news: 5.7% user + 3.1% kernel / faults: 286 minor 7 major
  8.3% 2053/com.android.systemui: 6.6% user + 1.7% kernel / faults: 76 minor
  2.4% 16300/com.tencent.mm:exdevice: 1.5% user + 0.8% kernel
  2.3% 16174/com.tencent.mm:push: 1.3% user + 1% kernel
  2% 1738/system_server: 1% user + 0.9% kernel / faults: 202 minor 3 major
  1.4% 2674/kworker/u16:7: 0% user + 1.4% kernel
  1% 4989/kworker/u16:4: 0% user + 1% kernel

```

## adb shell dumpsys window 
```

adb shell dumpsys window     //显示键盘，窗口和它们的关系 能查看到当前获得焦点的窗口对象
adb shell dumpsys window | grep mCurrentFocus // 查看焦点窗口（Linux）
adb shell dumpsys window | findstr mCurrentFocus // 查看焦点窗口  (Windows)

adb shell dumpsys window | findstr mCurrentFocus
mCurrentFocus=Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}


adb shell dumpsys window 
WINDOW MANAGER POLICY STATE (dumpsys window policy)
    mSafeMode=false mSystemReady=true mSystemBooted=true
    mLidState=1 mLidOpenRotation=-1 mCameraLensCoverState=-1 mHdmiPlugged=false
    mLastSystemUiFlags=0x2708 mResettingSystemUiFlags=0x0 mForceClearedSystemUiFlags=0x0
    mWakeGestureEnabledSetting=true
    mSupportAutoRotation=true
    mUiMode=1 mDockMode=0 mEnableCarDockHomeCapture=true mCarDockRotation=-1 mDeskDockRotation=-1
    mUserRotationMode=1 mUserRotation=0 mAllowAllRotations=0
    mCurrentAppOrientation=1
    mCarDockEnablesAccelerometer=true mDeskDockEnablesAccelerometer=true
    mLidKeyboardAccessibility=0 mLidNavigationAccessibility=0 mLidControlsScreenLock=true
........
```

## adb shell dumpsys wifi 
```
 adb shell dumpsys wifi                //显示wifi信息 显示 wifi扫描状态相关信息

Wi-Fi is enabled
Stay-awake conditions: 0
mMulticastEnabled 0
mMulticastDisabled 0
mInIdleMode false
mScanPending false
WifiController:
 total records=612
 rec[0]: time=12-17 13:13:05.633 processed=DefaultState org=ApStaDisabledState dest=<null> what=155651(0x26003)

Latest scan results:
    BSSID              Frequency  RSSI    Age      SSID                                 Flags
  28:6c:07:5a:f5:7c       5745    -27  5944.338    3CC_5G                            [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS]
Dump of WifiConfigManager
```

## adb shell dumpsys meminfo 
```
adb shell dumpsys meminfo          // 查看内存使用情况
Applications Memory Usage (in Kilobytes):
Uptime: 52975530 Realtime: 160959241

Total PSS by process:
    274,021K: com.ss.android.article.news (pid 10805 / activities)
    259,802K: system (pid 1738)
    226,702K: com.tencent.mm (pid 16233 / activities)
    224,611K: com.android.systemui (pid 2053)
    129,284K: com.tencent.mobileqq (pid 5885)
     88,148K: com.meizu.filemanager (pid 2923 / activities)
     84,123K: surfaceflinger (pid 585)
     64,410K: com.baidu.input (pid 2838)
     54,605K: com.meizu.flyme.launcher (pid 3137 / activ

```

## adb shell dumpsys meminfo surfaceflinger
```

adb shell dumpsys meminfo surfaceflinger            // 查看SurfaceFlinger进程的内存使用情况
Applications Memory Usage (in Kilobytes):
Uptime: 53108366 Realtime: 161092076
                   Pss  Private  Private  SwapPss     Heap     Heap     Heap
                 Total    Dirty    Clean    Dirty     Size    Alloc     Free
                ------   ------   ------   ------   ------   ------   ------
  Native Heap     2652     2652        0    19760        0        0        0
  Dalvik Heap        0        0        0        0        0        0        0
        Stack      196      196        0       12
      Gfx dev     1892     1292        0        0
    Other dev        9        0        4        0
     .so mmap     1476      404      568     3168
   Other mmap       12        8        4       36
   EGL mtrack    43648    43648        0        0
    GL mtrack    10884    10884        0        0
      Unknown       72       72        0      304
        TOTAL    84121    59156      576    23280        0        0        0

 App Summary
                       Pss(KB)
                        ------
           Java Heap:        0
         Native Heap:     2652
                Code:      972
               Stack:      196
            Graphics:    55824
       Private Other:       88
              System:    24389

               TOTAL:    84121       TOTAL SWAP PSS:    23280
```

##   adb shell dumpsys battery
```

adb shell dumpsys battery         // 查看电池使用情况
Current Battery Service state:
  AC powered: false　　　　　　　　//false表示没使用AC电源
  USB powered: true　　　　　　　　//true表示使用USB电源
  Wireless powered: false　　　　 //false表示没使用无线电源
  status: 2　　　　　　　　　　　　　//2表示电池正在充电，1表示没充电
  health: 2　　　　　　　　　　　　　//2表示电池状态优秀
  present: true　　　　　　　　　　 //true表示已安装电池
  level: 63　　　　　　　　　　　　　//电池百分比
  scale: 100　　　　　　　　　　　　　//满电量时电池百分比为100%（不确定是否正确）
  voltage: 3781　　　　　　　　　　　//电池电压3.781V
  temperature: 250　　　　　　　　　//电池温度为25摄氏度
  technology: Li-ion　　　　　　　　//电池类型为锂电池

```
###  adb shell dumpsys battery set status 1|2
```

//设置电池为非充电状态
adb shell dumpsys battery set status 1


//设置电池为充电状态
 adb shell dumpsys battery set status 2

```
### adb shell dumpsys battery unplug
```
adb shell dumpsys battery unplug    //  断开充电状态  6.0以上


```
###   adb shell dumpsys battery reset
```
 adb shell dumpsys battery reset      // 复位，恢复实际状态

```
###  adb shell dumpsys battery set level 100
```
adb shell dumpsys battery set level 100    // 设置电量百分比

```
## adb shell top -m 5 -t
```
adb shell top -m 5 -t             //按占用cpu大小列出5个进程列表
User 2%, System 6%, IOW 0%, IRQ 0%
User 13 + Nice 0 + Sys 33 + Idle 438 + IOW 2 + IRQ 3 + SIRQ 0 = 489

  PID   TID USER     PR  NI CPU% S     VSS     RSS PCY Thread          Proc
 7531  7531 shell    20   0   7% R   9384K   2988K  fg top             top
10805 10805 u0_a103  16  -4   0% S 2536432K 113128K  bg id.article.news com.ss.android.article.news
 2053  2053 u0_a31   10 -10   0% S 1939152K 145392K  fg ndroid.systemui com.android.systemui
32212 32212 root     19  -1   0% S      0K      0K  fg VosRXThread
 2127  2127 root     20   0   0% S      0K      0K  fg kworker/u16:2
```

## adb shell pm list permission-groups
```
adb shell pm list permission-groups     // 查看权限组合
permission group:android.permission-group.CONTACTS
permission group:android.permission-group.PHONE
permission group:android.permission-group.CALENDAR
permission group:android.permission-group.CAMERA
permission group:android.permission-group.SENSORS
permission group:android.permission-group.LOCATION
permission group:android.permission-group.STORAGE
permission group:com.sina.weibo.permission-group
permission group:android.permission-group.MICROPHONE
permission group:android.permission-group.SMS

```

## adb shell pm path  xx
```
adb shell pm path com.UCMobile    //  查看应用安装路径
package:/data/app/com.UCMobile-2/base.apk
```

## adb shell pm get-install-location    
```
adb shell pm get-install-location       /// 返回当前默认安装位置

　　返回值：
　　　　0　　auto
　　　　1　　内部存储
　　　　2　　外部存储
```

##  adb shell pm set-install-location
```
 adb shell pm set-install-location 0 　    // 修改默认安装位置
 adb shell pm set-install-location 1 
 adb shell pm set-install-location 2 

　　　　0　　auto
　　　　1　　内部存储
　　　　2　　外部存储
```
## adb shell am broadcast
```
https://www.jianshu.com/p/9e3b3a070529


adb shell am broadcast    //   发送广播


adb shell am broadcast -a com.Android.test --es<string> test_string "this is test string" —ei<int> test_int 100 —ez<boolean> test_boolean true

  [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
    [-n <COMPONENT>] [-f <FLAGS>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]


***************************************发送广播*********************************************
=============================================wifi广播========================================



 

adb shell am broadcast -a "android.net.wifi.STATE_CHANGE" -e "networkInfo" 

"android.net.wifi.STATE_CHANGE" ------------- WiFi连接到一个ssid后的广播
"android.net.wifi.WIFI_STATE_CHANGED" ------- WiFi的打开关闭等相关变化的广播 这个广播只关心wifi硬件本身打开与关闭，不关心wifi是否连接；

 WifiManager.WIFI_STATE_DISABLED ==1
 WifiManager.WIFI_STATE_DISABLING ==0
 WifiManager. WIFI_STATE_ENABLED==3
 WifiManager. WIFI_STATE_ENABLING==2
 WifiManager. WIFI_STATE_UNKNOWN==4
 
【 已经关闭wifi硬件 】
adb shell am broadcast -a "android.net.wifi.WIFI_STATE_CHANGED"  --ei "previous_wifi_state" 0  --ei "wifi_state" 1   

【 正在关闭wifi硬件 】          
adb shell am broadcast -a "android.net.wifi.WIFI_STATE_CHANGED"  --ei "previous_wifi_state" 3  --ei "wifi_state" 0   
   
【 已经打开wifi 开关 】     
adb shell am broadcast -a "android.net.wifi.WIFI_STATE_CHANGED"  --ei "previous_wifi_state" 2  --ei "wifi_state" 3

【 正在打开wifi 开关 】  
adb shell am broadcast -a "android.net.wifi.WIFI_STATE_CHANGED"  --ei "previous_wifi_state" 1  --ei "wifi_state" 2 

  
=============================================热点广播========================================
http://androidxref.com/9.0.0_r3/xref/frameworks/base/wifi/java/android/net/wifi/WifiManager.java#511
public static final int WIFI_AP_CLOSEING 		= 10;  //wifi hot is closeing
public static final int WIFI_AP_CLOSE_SUCCESS 	= 11;  //wifi hot close success
public static final int WIFI_AP_OPENING 		= 12;  //WiFi hot is opening 
public static final int WIFI_AP_OPEN_SUCCESS 	= 13;  //WiFi hot open success
public static final int WIFI_AP_STATE_FAILED    = 14;  // Wifi hot open failed


// 打开热点	
am broadcast -a "android.net.wifi.WIFI_AP_STATE_CHANGED"  --ei "wifi_state" 13  --ei "previous_wifi_state" 12  --ei "wifi_ap_mode" 12 --es "wifi_ap_interface_name" "wlan0" 

// 关闭热点
am broadcast -a "android.net.wifi.WIFI_AP_STATE_CHANGED"  --ei "wifi_state" 11  --ei "previous_wifi_state" 10  --ei "wifi_ap_mode" 12 --es "wifi_ap_interface_name" "wlan0"




=============================================飞行模式广播========================================

// 打开 飞行模式
adb shell settings put global airplane_mode_on 1
adb shell am broadcast -a android.intent.action.AIRPLANE_MODE


// 关闭 飞行模式
adb shell settings put global airplane_mode_on 0
adb shell am broadcast -a android.intent.action.AIRPLANE_MODE

***************************************打开应用*********************************************

adb shell am start -n com.android.settings/com.android.settings.SubSettings

adb shell am start -n com.android.settings/com.android.settings.Settings

```

##  adb shell wm density
```
adb shell wm density    // 查看屏幕物理分辨率dpi
Physical density: 480

```

##  adb shell settings get
```
adb shell settings get   XX   // 获得设置值的开关值
adb shell settings get global airplane_mode_on        //获得飞行模式开关值 


adb shell settings get global airplane_mode_on
0

```

## adb shell settings put 
```
adb shell settings put  XXX       // 设置值到系统Setting

adb shell settings put global airplane_mode_on 0 // 设置飞行模式关闭
adb shell settings put global airplane_mode_on 1 // 设置飞行模式打开
```

##  adb shell svc
```
adb shell svc   XXX    // 同步状态命令

adb shell svc wifi disable         //关闭wifi
adb shell svc wifi enable           //打开wifi
```

## adb shell input keyevent 
```
adb shell input keyevent     // 模拟键盘输入

adb shell input keyevent KEYCODE_POWER               // 模拟点击电源键
adb shell input keyevent 20                           // KEYCODE_DPAD_LEFT

```

## adb shell dumpsys input
```
 adb shell dumpsys input         // 输入事件

INPUT MANAGER (dumpsys input)

Input Manager State:
  Interactive: false
  System UI Visibility: 0x608
  Pointer Speed: 0
  Pointer Gestures Enabled: true
  Show Touches: false

Event Hub State:
  BuiltInKeyboardId: -2
  Devices:
    -1: Virtual
      Classes: 0x40000023
      Path: <virtual>
      Descriptor: a718a782d34bc767f4689c232d64d527998ea7fd
      Location: 
      ControllerNumber: 0
      UniqueId: <virtual>
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/Generic.kl
      KeyCharacterMapFile: /system/usr/keychars/Virtual.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false

```

## adb shell pm list permissions 
```
adb shell pm list permissions            // 权限列表

permission:com.mobike.mobikeapp.permission.MIPUSH_RECEIVE
permission:android.permission.WAKE_LOCK
permission:android.permission.INJECT_EVENTS
permission:com.taobao.taobao.push.permission.MESSAGE
permission:android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED
permission:com.hexin.plat.android.permission.MIPUSH_RECEIVE
permission:android.permission.BIND_CHOOSER_TARGET_SERVICE
permission:com.qihoo.antivirus.update.permission.clear_sdk_meizu
permission:com.android.email.permission.ACCESS_PROVIDER
permission:com.ss.android.auto.push.permission.MESSAGE
permission:com.wandoujia.phoenix2.permission.MIPUSH_RECEIVE
permission:com.jingdong.pdj.push.permission.MESSAGE
permission:android.permission.UPDATE_APP_OPS_STATS
```

##  db shell content query --uri
```
db shell content query --uri  content://xxxxxxxxxxxxxxxxx      //  查询provider数据

adb shell content query --uri content://media/internal/images/media            // 查看image数据库
adb shell content query --uri content://media/internal/video/media            // 查看video数据库
adb shell content query --uri content://media/internal/audio/media           // 查看audio数据库
adb shell content query --uri content://media/internal/file                 // 查看文件系统数据库



Row: 0 _id=1, _data=/system, _size=0, format=12289, parent=0, date_added=172827, date_modified=0, mime_type=NULL, title=system, description=NULL, _display_name=system, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=47, bucket_display_name=, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=NULL, width=NULL, height=NULL, camera_refocus=0


Row: 1 _id=2, _data=/system/media, _size=0, format=12289, parent=1, date_added=172827, date_modified=1230739200, mime_type=NULL, title=media, description=NULL, _display_name=media, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=-2124328162, bucket_display_name=system, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=NULL, width=NULL, height=NULL, camera_refocus=0


```

##  adb shell dumpsys netstats detail   
```
adb shell dumpsys netstats detail        // 网络情况详细信息
Active interfaces:
  iface=tun0 ident=[{type=VPN, subType=COMBINED, metered=false}]
  iface=wlan0 ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}]
Active UID interfaces:
  iface=tun0 ident=[{type=VPN, subType=COMBINED, metered=false}]
  iface=wlan0 ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}]
Dev stats:
  Pending bytes: 0
  History since boot:
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=106613 rp=291 tb=39561 tp=334 op=0
      st=1545051600 rb=4297352 rp=7660 tb=8204613 tp=11037 op=0
      st=1545055200 rb=254016 rp=725 tb=157683 tp=833 op=0
      st=1545058800 rb=1868 rp=23 tb=2846 tp=30 op=0
      st=1545062400 rb=72 rp=1 tb=166 tp=2 op=0
      st=1545069600 rb=531 rp=7 tb=715 tp=8 op=0
      st=1545094800 rb=24638985 rp=30319 tb=4763461 tp=26555 op=0
      st=1545098400 rb=6258127 rp=9850 tb=1747421 tp=10521 op=0
      st=1545102000 rb=716947 rp=1378 tb=225295 tp=1449 op=0

```

## adb shell dumpsys procstats --hours 3
```
adb shell dumpsys procstats --hours 3            //   最近三小时 进程情况

AGGREGATED OVER LAST 3 HOURS:
  * com.android.systemui:recents / u0a31 / v7000001:
           TOTAL: 100% (40MB-34MB-89MB/21MB-33MB-69MB over 22)
             Top: 5.4% (89MB-69MB-89MB/69MB-69MB-69MB over 3)
          Imp Fg: 95% (40MB-28MB-53MB/21MB-28MB-36MB over 19)
  * com.tencent.mm:push / u0a108 / v1360:
           TOTAL: 100% (24MB-16MB-28MB/12MB-15MB-18MB over 22)
          Imp Fg: 1.9% (26MB-17MB-28MB/16MB-18MB-18MB over 2)
       Heavy Wgt: 98% (24MB-15MB-27MB/12MB-15MB-17MB over 20)
  * com.meizu.dataservice / 1000 / v45:
  * 
Run time Stats:
  SOff/Norm: +13m1s966ms
       Mod : +8s746ms
       Low : +1m11s498ms
       Crit: +463ms
  SOn /Norm: +57m25s143ms
       Mod : +4m21s977ms
       Low : +2m59s879ms
       Crit: +20m10s227ms
      TOTAL: +1h39m19s899ms

Memory usage:
  Kernel : 335MB (59 samples)
  Native : 270MB (59 samples)
  Persist: 414MB (606 samples)
  Top    : 208MB (391 samples)
  ImpFg  : 155MB (1150 samples)
  ImpBg  : 58MB (469 samples)
  HeavyWt: 109MB (297 samples)
  Service: 254MB (2022 samples)
  Receivr: 167KB (1629 samples)
  Home   : 37MB (125 samples)
  LastAct: 7.1MB (223 samples)
  CchAct : 430KB (10 samples)
  CchCAct: 40MB (68 samples)
  CchEmty: 183MB (740 samples)
  Cached : 335MB (59 samples)
  Free   : 64MB (59 samples)
  Z-Ram  : 235MB (59 samples)
  TOTAL  : 2.6GB
  ServRst: 0.95MB (871 samples)

          Start time: 2018-12-18 09:43:40
  Total elapsed time: +2h16m52s456ms (partial) (swapped-out-pss) libart.so


```


## adb shell dumpsys SurfaceFlinger --latency 
```
adb shell dumpsys SurfaceFlinger --latency                  // 画面合成帧服务状态

16666666
9223372036854775807     24382784960224  9223372036854775807
9223372036854775807     24382868506891  9223372036854775807
9223372036854775807     24382885229391  9223372036854775807
9223372036854775807     24382901935381  9223372036854775807
9223372036854775807     24382935338141  9223372036854775807
9223372036854775807     24382968763089  9223372036854775807
9223372036854775807     24382985478610  9223372036854775807
9223372036854775807     24383002186943  9223372036854775807
9223372036854775807     24383018876474  9223372036854775807
9223372036854775807     24383035587724  9223372036854775807
9223372036854775807     24383052287360  9223372036854775807
9223372036854775807     24383068990433  9223372036854775807

```


## adb shell dumpsys sensorservice
```
adb shell dumpsys sensorservice              // 传感器信息

Sensor Device:
HAL: MultiHal Sensor Module (Google, Inc), version 0x01030001
Fusion States:
9-axis fusion disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
game fusion(no mag) disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
geomag fusion (no gyro) disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
Recent Sensor events:
Orientation: last 10 events
	 1 (ts=49389.806111479, wall=10:25:40.599) 143.28, -38.01, -13.00, 
	 2 (ts=49389.826100493, wall=10:25:40.620) 143.29, -38.01, -13.00, 
Significant Motion Detector: last 2 events
	 1 (ts=2705.598469272, wall=21:27:53.805) 1.00, 
	 2 (ts=3366.641328087, wall=21:38:44.701) 1.00, 
Step Counter: last 50 events
	 1 (ts=58259.297141846, wall=12:53:29.837) 6707, 
	 2 (ts=58259.557395752, wall=12:53:29.837) 6708,
Linear Acceleration: last 10 events
	 1 (ts=46750.969951023, wall=09:41:42.483) 0.03, 0.72, -0.53, 
	 2 (ts=46750.989970555, wall=09:41:42.503) 0.72, -0.04, -0.93, 
Gravity: last 10 events
	 1 (ts=58918.870586946, wall=13:04:28.794) 3.32, 5.83, 7.15, 
	 2 (ts=58918.930553987, wall=13:04:28.856) 3.40, 5.71, 7.21, 
	 3 (ts=58918.990490511, wall=13:04:28.918) 3.53, 5.68, 7.17, 
```

##  adb shell dumpsys wifi ipmanager
```
adb shell dumpsys wifi ipmanager                //   WIFI配置相关信息
IpManager.wlan0 APF dump:
  No apf support

IpManager.wlan0 StateMachine dump:
  12-17 20:43:20.235 - CMD_SET_MULTICAST_FILTER wlan0/-1 0 0 true [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:22.669 - OBSERVED interfaceAdded(wlan0)
  12-17 20:43:35.293 - CMD_STOP wlan0/-1 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:35.305 - INVOKE setNeighborDiscoveryOffload(true)
  12-17 20:43:35.308 - CMD_UPDATE_HTTP_PROXY wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]

```
# T
# U

## adb uninstall -k dest.apk

```
adb uninstall -k dest.apk     // 卸载当前 dest.apk 从手机


```
# V
# W
# X
# Y
# Z


# 附录

## prop所有属性
```
[DEVICE_PROVISIONED]: [1]
[af.fast_track_multiplier]: [1]
[audio.deep_buffer.media]: [true]
[audio.dolby.ds2.enabled]: [true]
[audio.dolby.ds2.hardbypass]: [true]
[audio.heap.size.multiplier]: [7]
[audio.offload.buffer.size.kb]: [64]
[audio.offload.gapless.enabled]: [true]
[audio.offload.min.duration.secs]: [30]
[audio.offload.multiaac.enable]: [true]
[audio.offload.multiple.enabled]: [false]
[audio.offload.passthrough]: [false]
[audio.offload.pcm.16bit.enable]: [true]
[audio.offload.pcm.24bit.enable]: [true]
[audio.offload.track.enable]: [true]
[audio.offload.video]: [false]
[audio.parser.ip.buffer.size]: [262144]
[audio.playback.mch.downsample]: [true]
[audio.pp.asphere.enabled]: [false]
[audio.safx.pbe.enabled]: [true]
[audio_hal.period_size]: [480]
[av.debug.disable.pers.cache]: [1]
[bluetooth.hciattach]: [true]
[bluetooth.hfp.client]: [1]
[bluetooth.status]: [on]
[camera.aux.packagelist]: [org.codeaurora.snapcam]
[camera.display.lmax]: [1280x720]
[camera.display.umax]: [1920x1080]
[camera.hal1.packagelist]: [com.skype.raider,com.google.android.talk]
[camera.lowpower.record.enable]: [1]
[config.svi.path]: [/system/etc/svi_config.xml]
[config.svi.xml]: [1]
[config.svi.xml.print]: [1]
[dalvik.vm.appimageformat]: [lz4]
[dalvik.vm.dex2oat-Xms]: [64m]
[dalvik.vm.dex2oat-Xmx]: [512m]
[dalvik.vm.heapgrowthlimit]: [192m]
[dalvik.vm.heapmaxfree]: [8m]
[dalvik.vm.heapminfree]: [4m]
[dalvik.vm.heapsize]: [512m]
[dalvik.vm.heapstartsize]: [16m]
[dalvik.vm.heaptargetutilization]: [0.75]
[dalvik.vm.image-dex2oat-Xms]: [64m]
[dalvik.vm.image-dex2oat-Xmx]: [64m]
[dalvik.vm.isa.arm.features]: [default]
[dalvik.vm.isa.arm.variant]: [cortex-a53]
[dalvik.vm.isa.arm64.features]: [default]
[dalvik.vm.isa.arm64.variant]: [generic]
[dalvik.vm.stack-trace-file]: [/data/anr/traces.txt]
[dalvik.vm.usejit]: [true]
[dalvik.vm.usejitprofiles]: [true]
[debug.atrace.tags.enableflags]: [0]
[debug.choreographer.skipwarning]: [3]
[debug.egl.hw]: [0]
[debug.enable.gamed]: [0]
[debug.enable.sglscale]: [1]
[debug.force_rtl]: [0]
[debug.gralloc.enable_fb_ubwc]: [1]
[debug.gralloc.gfx_ubwc_disable]: [0]
[debug.hwui.render_dirty_regions]: [true]
[debug.mdpcomp.logs]: [0]
[debug.settings.wifi]: [0]
[debug.sf.hw]: [0]
[dev.bootcomplete]: [1]
[dev.pm.dyn_samplingrate]: [1]
[drm.service.enabled]: [true]
[flac.sw.decoder.24bit.support]: [true]
[gsm.current.phone-type]: [1,1]
[gsm.defaultpdpcontext.active]: [false]
[gsm.network.type]: [Unknown,Unknown]
[gsm.nitz.time]: [1545023508004]
[gsm.operator.alpha]: []
[gsm.operator.idpstring]: [00]
[gsm.operator.iso-country]: []
[gsm.operator.isroaming]: [false,false]
[gsm.operator.numeric]: []
[gsm.serial]: [721QZY132E2D6B85]
[gsm.sim.operator.alpha]: []
[gsm.sim.operator.imsi]: []
[gsm.sim.operator.iso-country]: []
[gsm.sim.operator.numeric]: []
[gsm.sim.state]: [ABSENT,ABSENT]
[gsm.version.baseband]: [953_GEN_PACK-1.117478.1.119723.2]
[gsm.version.baseband1]: [953_GEN_PACK-1.117478.1.119723.2]
[gsm.version.ril-impl]: [Qualcomm RIL 1.0]
[init.svc.adbd]: [stopping]
[init.svc.adsprpcd]: [running]
[init.svc.atfwd]: [stopped]
[init.svc.audiod]: [running]
[init.svc.audioserver]: [running]
[init.svc.bootanim]: [stopped]
[init.svc.cameraserver]: [running]
[init.svc.carrier_switcher]: [stopped]
[init.svc.cnd]: [running]
[init.svc.cnss-daemon]: [running]
[init.svc.cnss_diag]: [stopped]
[init.svc.config_bluetooth]: [stopped]
[init.svc.config_bt_addr]: [stopped]
[init.svc.debuggerd]: [running]
[init.svc.debuggerd64]: [running]
[init.svc.dpmd]: [running]
[init.svc.drm]: [running]
[init.svc.edata-daemon]: [running]
[init.svc.energy-awareness]: [stopped]
[init.svc.esepmdaemon]: [running]
[init.svc.facerecod]: [running]
[init.svc.fingerprintd]: [running]
[init.svc.flash_recovery]: [stopped]
[init.svc.gatekeeperd]: [running]
[init.svc.goodixfpd]: [running]
[init.svc.hciattach]: [stopped]
[init.svc.healthd]: [running]
[init.svc.hvdcp_opti]: [running]
[init.svc.ifaad]: [running]
[init.svc.ims_rtp_daemon]: [running]
[init.svc.imscmservice]: [running]
[init.svc.imsdatadaemon]: [running]
[init.svc.imsqmidaemon]: [running]
[init.svc.installd]: [running]
[init.svc.ipacm]: [running]
[init.svc.ipacm-diag]: [running]
[init.svc.irsc_util]: [stopped]
[init.svc.keystore]: [running]
[init.svc.lmkd]: [running]
[init.svc.loc_launcher]: [running]
[init.svc.logd]: [running]
[init.svc.logd-reinit]: [stopped]
[init.svc.mdtpd]: [running]
[init.svc.media]: [running]
[init.svc.mediacodec]: [running]
[init.svc.mediadrm]: [running]
[init.svc.mediaextractor]: [running]
[init.svc.msm_irqbalance]: [running]
[init.svc.netd]: [running]
[init.svc.netmgrd]: [running]
[init.svc.p2p_supplicant]: [running]
[init.svc.per_mgr]: [running]
[init.svc.per_proxy]: [running]
[init.svc.perfd]: [running]
[init.svc.port-bridge]: [stopped]
[init.svc.ppd]: [running]
[init.svc.ptt_socket_app]: [stopped]
[init.svc.qcamerasvr]: [running]
[init.svc.qcom-c_core-sh]: [stopped]
[init.svc.qcom-c_main-sh]: [stopped]
[init.svc.qcom-post-boot]: [stopped]
[init.svc.qcom-sh]: [stopped]
[init.svc.qcom-usb-sh]: [stopped]
[init.svc.qcomsysd]: [running]
[init.svc.qseecomd]: [running]
[init.svc.qseeproxydaemon]: [running]
[init.svc.qti]: [running]
[init.svc.qti-testscripts]: [stopped]
[init.svc.recovery_service]: [stopped]
[init.svc.remosaic_daemon]: [running]
[init.svc.ril-daemon]: [running]
[init.svc.ril-daemon2]: [running]
[init.svc.rmt_storage]: [running]
[init.svc.sensor-sh]: [stopped]
[init.svc.sensors]: [running]
[init.svc.servicemanager]: [running]
[init.svc.ssr_setup]: [stopped]
[init.svc.surfaceflinger]: [running]
[init.svc.tftp_server]: [running]
[init.svc.thermal-engine]: [running]
[init.svc.time_daemon]: [running]
[init.svc.ueventd]: [running]
[init.svc.vold]: [running]
[init.svc.wcnss-service]: [running]
[init.svc.zygote]: [running]
[init.svc.zygote_secondary]: [running]
[keyguard.no_require_sim]: [true]
[log.tag]: []
[log.tag.VibeTonz]: [ERROR]
[media.aac_51_output_enabled]: [true]
[media.msm8956hw]: [0]
[mm.enable.qcom_parser]: [1040127]
[mm.enable.smoothstreaming]: [true]
[mmp.enable.3g2]: [true]
[net.bt.name]: [Android]
[net.change]: [net.rmnet_data0.gw]
[net.dns1]: [192.168.31.1]
[net.dns2]: []
[net.dns3]: []
[net.dns4]: []
[net.hostname]: [android-a0581edd8a850c6]
[net.lte.ims.data.enabled]: [true]
[net.qtaguid_enabled]: [1]
[net.rmnet_data0.dns1]: []
[net.rmnet_data0.dns2]: []
[net.rmnet_data0.gw]: []
[net.rmnet_data7.dns1]: []
[net.rmnet_data7.dns2]: []
[net.rmnet_data7.gw]: [fe80:0:0:0:919:aaaf:4fa7:6fc0]
[net.tcp.2g_init_rwnd]: [10]
[net.tcp.buffersize.default]: [4096,87380,524288,4096,16384,110208]
[net.tcp.buffersize.edge]: [4093,26280,35040,4096,16384,35040]
[net.tcp.buffersize.evdo]: [4094,87380,524288,4096,16384,262144]
[net.tcp.buffersize.gprs]: [4092,8760,11680,4096,8760,11680]
[net.tcp.buffersize.hsdpa]: [4094,87380,1220608,4096,16384,1220608]
[net.tcp.buffersize.hspa]: [4094,87380,1220608,4096,16384,1220608]
[net.tcp.buffersize.hspap]: [4094,87380,1220608,4096,16384,1220608]
[net.tcp.buffersize.hsupa]: [4094,87380,1220608,4096,16384,1220608]
[net.tcp.buffersize.lte]: [2097152,4194304,8388608,262144,524288,1048576]
[net.tcp.buffersize.umts]: [4094,87380,110208,4096,16384,110208]
[net.tcp.buffersize.wifi]: [524288,2097152,4194304,262144,524288,1048576]
[net.tcp.default_init_rwnd]: [60]
[net_wifi.fwpath]: [sta]
[persist.activate_mbn.enabled]: [false]
[persist.audio.fluence.speaker]: [false]
[persist.audio.fluence.voicecall]: [true]
[persist.audio.fluence.voicerec]: [false]
[persist.camera.gyro.disable]: [0]
[persist.camera.nso.enable]: [1]
[persist.camera.stats.test]: [5]
[persist.cne.feature]: [1]
[persist.console.silent.config]: [1]
[persist.data.iwlan.enable]: [true]
[persist.data.mode]: [concurrent]
[persist.data.netmgrd.qos.enable]: [true]
[persist.data_netmgrd_mtu]: [1300]
[persist.dbg.ims_volte_enable]: [1]
[persist.dbg.volte_avail_ovr]: [1]
[persist.dbg.wfc_avail_ovr]: [1]
[persist.debug.coresight.config]: [stm-events]
[persist.debug.wfd.enable]: [1]
[persist.demo.hdmirotationlock]: [false]
[persist.device.type]: [omt]
[persist.fuse_sdcard]: [true]
[persist.hwc.enable_vds]: [1]
[persist.hwc.mdpcomp.enable]: [true]
[persist.log.tag]: []
[persist.logd.size]: []
[persist.meizu.cpu_l]: [1]
[persist.meizu.cpumaxfreq]: [1958400]
[persist.mm.sta.enable]: [0]
[persist.net.doxlat]: [true]
[persist.nfc.smartcard.config]: [SIM1,eSE1]
[persist.perf.url.wxhb]: [https://wx.gtimg.com/hongbao/1800/hb.png]
[persist.perf.wm_static_blur]: [true]
[persist.qfp]: [false]
[persist.radio.DROPSETENABLE]: [1]
[persist.radio.VT_ENABLE]: [1]
[persist.radio.VT_HYBRID_ENABLE]: [1]
[persist.radio.adb_log_on]: [0]
[persist.radio.apm_sim_not_pwdn]: [1]
[persist.radio.calls.on.ims]: [0]
[persist.radio.cdma.msgid]: [8]
[persist.radio.csvt.enabled]: [false]
[persist.radio.custom_ecc]: [1]
[persist.radio.eons.enabled]: [false]
[persist.radio.facnotsup_as_nonw]: [1]
[persist.radio.factory_phone_sn]: [721QECRM2ZMT4]
[persist.radio.factory_sn]: [721QZY132E2D6B85]
[persist.radio.flexmap_type]: [nw_mode]
[persist.radio.force_on_dc]: [true]
[persist.radio.fourgOff]: [1]
[persist.radio.hw_mbn_update]: [0]
[persist.radio.jbims]: [0]
[persist.radio.msim.stackid_0]: [0]
[persist.radio.msim.stackid_1]: [1]
[persist.radio.mt_sms_ack]: [20]
[persist.radio.multisim.config]: [dsds]
[persist.radio.rat_on]: [combine]
[persist.radio.report_codec]: [0]
[persist.radio.ril_payload_on]: [0]
[persist.radio.sglte_target]: [0]
[persist.radio.sib16_support]: [1]
[persist.radio.stack_id_0]: [0]
[persist.radio.stack_id_1]: [1]
[persist.radio.start_ota_daemon]: [1]
[persist.radio.sw_mbn_loaded]: [1]
[persist.radio.sw_mbn_update]: [0]
[persist.radio.trigger.silence]: [true]
[persist.radio.trigger.supportux]: [true]
[persist.radio.ver_info]: []
[persist.radio.version]: [248]
[persist.radio.videopause.mode]: [1]
[persist.regional.wipedata.level]: [all]
[persist.rild.nitz_long_ons_0]: []
[persist.rild.nitz_long_ons_1]: []
[persist.rild.nitz_long_ons_2]: []
[persist.rild.nitz_long_ons_3]: []
[persist.rild.nitz_plmn]: []
[persist.rild.nitz_short_ons_0]: []
[persist.rild.nitz_short_ons_1]: []
[persist.rild.nitz_short_ons_2]: []
[persist.rild.nitz_short_ons_3]: []
[persist.speaker.prot.enable]: [true]
[persist.sys.app.thread]: [6]
[persist.sys.at.open]: [0]
[persist.sys.boardInfo]: []
[persist.sys.cnd.iwlan]: [1]
[persist.sys.cts_Myanmar_state]: [false]
[persist.sys.cts_state]: [false]
[persist.sys.dalvik.vm.lib.2]: [libart.so]
[persist.sys.disable_blur_view]: [true]
[persist.sys.disable_glass_blur]: [false]
[persist.sys.dpmd.nsrm]: [3]
[persist.sys.dpmd.tcm]: [2]
[persist.sys.enable_scene]: [true]
[persist.sys.enable_strategy]: [true]
[persist.sys.fast_charge]: [1]
[persist.sys.fingerprint.hbd]: [false]
[persist.sys.get_stacks_perm]: [1]
[persist.sys.gps.lpp]: []
[persist.sys.keyguard_intercept]: [true]
[persist.sys.log-events.enable]: [0]
[persist.sys.log-main.enable]: [0]
[persist.sys.log-radio.enable]: [0]
[persist.sys.log-system.enable]: [0]
[persist.sys.log_fingerprint]: [Meizu/meizu_M6Note_CN/M6Note:7.1.2/N2G47H/m1721.Flyme_7.0.1526369438:user/release-keys]
[persist.sys.log_reject_level]: [100]
[persist.sys.meizu.codepage]: [gbk]
[persist.sys.meizu.dim.input]: [false]
[persist.sys.meizu.region]: [cn]
[persist.sys.mstore.dl.num]: [1]
[persist.sys.mz_mainkeys]: [1]
[persist.sys.plugin.compiler]: [interpret-only]
[persist.sys.plugin.path]: [/data/data/|/data/user/]
[persist.sys.plugin.thread]: [4]
[persist.sys.profiler_ms]: [0]
[persist.sys.rplauncher]: [1]
[persist.sys.showdialog]: [0]
[persist.sys.ssr.download_mode]: [0]
[persist.sys.ssr.restart_level]: [modem venus adsp wcnss]
[persist.sys.static_blur_mode]: [false]
[persist.sys.timezone]: [Asia/Shanghai]
[persist.sys.ui.hw]: [false]
[persist.sys.usb.config]: [adb]
[persist.sys.usb.config.extra]: [none]
[persist.sys.use.flyme.icon]: [true]
[persist.sys.webview.vmsize]: [143170432]
[persist.timed.enable]: [true]
[pm.dexopt.ab-ota]: [speed-profile]
[pm.dexopt.bg-dexopt]: [speed-profile]
[pm.dexopt.boot]: [verify-profile]
[pm.dexopt.core-app]: [speed]
[pm.dexopt.first-boot]: [interpret-only]
[pm.dexopt.forced-dexopt]: [speed]
[pm.dexopt.install]: [interpret-only]
[pm.dexopt.nsys-library]: [speed]
[pm.dexopt.shared-apk]: [speed]
[qc.izat.gtp.wifi.low_power]: [DISABLED]
[qcom.hw.aac.encoder]: [true]
[qemu.hw.mainkeys]: [1]
[radio.atfwd.start]: [false]
[recovery.service.control]: []
[recovery.service.errmsg]: []
[recovery.service.fraction]: []
[recovery.service.parameter]: []
[recovery.service.state]: [passed]
[recovery.service.support]: [true]
[ril.cdma.meid]: [86782803895966]
[ril.ecclist]: [*911,#911,000,08,110,999,118,119,120,122,911,112]
[ril.ecclist1]: [*911,#911,000,08,110,999,118,119,120,122,911,112]
[ril.gsm.imei]: [867828038942864,867828038942872]
[ril.iccid.sim1]: [null]
[ril.iccid.sim2]: [null]
[ril.num_1x_wait.wakelock]: [false]
[ril.qcril_pre_init_lock_held]: [0]
[ril.subscription.types]: [RUIM]
[rild.libargs]: [-d /dev/smd0]
[rild.libpath]: [/vendor/lib64/libril-qc-qmi-1.so]
[ro.adb.secure]: [1]
[ro.alarm_boot]: [false]
[ro.allow.mock.location]: [0]
[ro.am.reschedule_service]: [true]
[ro.baseband]: [msm]
[ro.bluetooth.dun]: [true]
[ro.bluetooth.hfp.ver]: [1.7]
[ro.bluetooth.sap]: [true]
[ro.board.platform]: [msm8953]
[ro.boot.authorized_kernel]: [true]
[ro.boot.baseband]: [msm]
[ro.boot.bootdevice]: [7824900.sdhci]
[ro.boot.bootreason]: [normal_reset]
[ro.boot.console]: [ttyHSL0]
[ro.boot.efuse]: [enable]
[ro.boot.emmc]: [true]
[ro.boot.flash.locked]: [1]
[ro.boot.hardware]: [qcom]
[ro.boot.keymaster]: [1]
[ro.boot.selinux]: [permissive]
[ro.boot.serialno]: [721QECRM2ZMT4]
[ro.boot.verifiedbootstate]: [green]
[ro.boot.veritymode]: [enforcing]
[ro.bootimage.build.date]: [Tue Aug 7 20:20:30 CST 2018]
[ro.bootimage.build.date.utc]: [1533644430]
[ro.bootimage.build.fingerprint]: [Meizu/msm8953_64/msm8953_64:7.1.2/N2G47H/m1721.Flyme_7.0.1526369438:user/release-keys]
[ro.bootloader]: [unknown]
[ro.bootmode]: [unknown]
[ro.build.characteristics]: [default]
[ro.build.cta]: [noncta]
[ro.build.date]: [Tue Aug  7 20:26:44 CST 2018]
[ro.build.date.utc]: [1533644804]
[ro.build.description]: [meizu_M6Note_CN-user 7.1.2 N2G47H m1721.Flyme_7.0.1526369438 release-keys]
[ro.build.display.id]: [Flyme 7.0.1.1A]
[ro.build.fingerprint]: [Meizu/meizu_M6Note_CN/M6Note:7.1.2/N2G47H/m1721.Flyme_7.0.1526369438:user/release-keys]
[ro.build.flavor]: [msm8953_64-user]
[ro.build.host]: [Mz-Builder-L19]
[ro.build.id]: [N2G47H]
[ro.build.inside.id]: [7.1.2-20180807192006]
[ro.build.mask.id]: [7.1.2-1533640806_stable]
[ro.build.product]: [M6Note]
[ro.build.tags]: [release-keys]
[ro.build.type]: [user]
[ro.build.user]: [flyme]
[ro.build.version.all_codenames]: [REL]
[ro.build.version.base_os]: []
[ro.build.version.codename]: [REL]
[ro.build.version.incremental]: [m1721.Flyme_7.0.1526369438]
[ro.build.version.preview_sdk]: [0]
[ro.build.version.release]: [7.1.2]
[ro.build.version.sdk]: [25]
[ro.build.version.security_patch]: [2018-04-01]
[ro.carrier]: [unknown]
[ro.cdma.cfall.disable]: [*730]
[ro.cdma.cfb.disable]: [*900]
[ro.cdma.cfb.enable]: [*90]
[ro.cdma.cfdf.disable]: [*680]
[ro.cdma.cfdf.enable]: [*68]
[ro.cdma.cfnr.disable]: [*920]
[ro.cdma.cfnr.enable]: [*92]
[ro.cdma.cfu.disable]: [*720]
[ro.cdma.cfu.enable]: [*72]
[ro.cdma.cw.disable]: [*740]
[ro.cdma.cw.enable]: [*74]
[ro.com.android.dataroaming]: [false]
[ro.com.android.dateformat]: [MM-dd-yyyy]
[ro.com.google.clientidbase]: [android-meizu]
[ro.config.alarm_alert]: [19_Waltz.ogg]
[ro.config.calendar_sound]: [03_Doorbell.ogg]
[ro.config.destory_surface]: [true]
[ro.config.email_sound]: [02_Reminder.ogg]
[ro.config.mms_sound]: [01_Triumph.ogg]
[ro.config.notification_sound]: [02_Reminder.ogg]
[ro.config.ringtone]: [03_Flyme.ogg]
[ro.config.zram]: [true]
[ro.core_ctl_max_cpu]: [4]
[ro.core_ctl_min_cpu]: [2]
[ro.crypto.fs_crypto_blkdev]: [/dev/block/dm-1]
[ro.crypto.state]: [encrypted]
[ro.crypto.type]: [block]
[ro.cutoff_voltage_mv]: [3100]
[ro.dalvik.vm.native.bridge]: [0]
[ro.debuggable]: [0]
[ro.emmc_size]: [16GB]
[ro.expect.recovery_id]: [0x6cda80855d97ca80606ca458bbe82eb5cef5a21f000000000000000000000000]
[ro.flyme.device.legal]: [0]
[ro.flyme.published]: [true]
[ro.flyme.root.state]: [0]
[ro.fm.transmitter]: [false]
[ro.frp.pst]: [/dev/block/bootdevice/by-name/config]
[ro.gpu.available_frequencies]: [650000000 560000000 510000000 400000000 320000000 216000000 133330000 ]
[ro.hardware]: [qcom]
[ro.hwui.disable_asset_atlas]: [true]
[ro.hwui.drop_shadow_cache_size]: [6]
[ro.hwui.fbo_cache_size]: [25]
[ro.hwui.gradient_cache_size]: [1]
[ro.hwui.layer_cache_size]: [48]
[ro.hwui.path_cache_size]: [32]
[ro.hwui.r_buffer_cache_size]: [8]
[ro.hwui.text_large_cache_height]: [1024]
[ro.hwui.text_large_cache_width]: [2048]
[ro.hwui.text_small_cache_height]: [1024]
[ro.hwui.text_small_cache_width]: [1024]
[ro.hwui.texture_cache_flushrate]: [0.4]
[ro.hwui.texture_cache_size]: [72]
[ro.logdumpd.enabled]: [0]
[ro.mdtp.package_name2]: [com.qualcomm.qti.securemsm.mdtp.MdtpDemo]
[ro.meizu.autorecorder]: [true]
[ro.meizu.build.branch]: [M1721_QNF7_mp1]
[ro.meizu.build.devowner]: [meizu]
[ro.meizu.build.spt]: [0]
[ro.meizu.contactmsg.auth]: [false]
[ro.meizu.customize.pccw]: [false]
[ro.meizu.hardware.battery]: [DEERNENG-ATL]
[ro.meizu.hardware.emmc]: [DL2032]
[ro.meizu.hardware.psn]: [721QZY132E2D6B85]
[ro.meizu.hardware.version]: [0x72101002]
[ro.meizu.pcb.version]: [0x1000001FFFFFFD0]
[ro.meizu.permanentkey]: [false]
[ro.meizu.product.model]: [M7210]
[ro.meizu.project.id]: [m1721-6]
[ro.meizu.published.type]: [prd]
[ro.meizu.region.enable]: [true]
[ro.meizu.security]: [true]
[ro.meizu.setupwizard.flyme]: [true]
[ro.meizu.setupwizard.setlang]: [true]
[ro.meizu.sip.support]: [true]
[ro.meizu.srsenable]: [true]
[ro.meizu.tpid]: [FT5X46_EACHOPTO_000d]
[ro.meizu.tptype]: [Black]
[ro.meizu.visualvoicemail]: [true]
[ro.meizu.voip.support]: [true]
[ro.memperf.enable]: [false]
[ro.memperf.lib]: [libmemperf.so]
[ro.mtk_benchmark_boost_tp]: [1]
[ro.oem_unlock_supported]: [1]
[ro.opengles.version]: [196610]
[ro.product.board]: [M6Note]
[ro.product.brand]: [Meizu]
[ro.product.cpu.abi]: [arm64-v8a]
[ro.product.cpu.abilist]: [arm64-v8a,armeabi-v7a,armeabi]
[ro.product.cpu.abilist32]: [armeabi-v7a,armeabi]
[ro.product.cpu.abilist64]: [arm64-v8a]
[ro.product.device]: [M6Note]
[ro.product.first_api_level]: [25]
[ro.product.flyme.model]: [1721]
[ro.product.locale]: [zh-CN]
[ro.product.locale.language]: [zh]
[ro.product.locale.region]: [CN]
[ro.product.manufacturer]: [Meizu]
[ro.product.mobile.name]: [m1721]
[ro.product.model]: [M6 Note]
[ro.product.name]: [meizu_M6Note_CN]
[ro.product.perf.config]: [M1721_base]
[ro.qc.sdk.audio.fluencetype]: [fluence]
[ro.qc.sdk.audio.ssr]: [false]
[ro.qcom.dpps.sensortype]: [2]
[ro.qualcomm.bluetooth.ftp]: [true]
[ro.qualcomm.bluetooth.hfp]: [true]
[ro.qualcomm.bluetooth.hsp]: [true]
[ro.qualcomm.bluetooth.map]: [true]
[ro.qualcomm.bluetooth.nap]: [true]
[ro.qualcomm.bluetooth.opp]: [true]
[ro.qualcomm.bluetooth.pbap]: [true]
[ro.qualcomm.bt.hci_transport]: [smd]
[ro.qualcomm.cabl]: [0]
[ro.qualcomm.svi]: [1]
[ro.recovery.matrix.ver]: [VER_20180226_00]
[ro.regionalization.support]: [true]
[ro.revision]: [0]
[ro.ril.svdo]: [false]
[ro.ril.svlte1x]: [false]
[ro.runtime.firstboot]: [1544881777290]
[ro.secure]: [1]
[ro.serialno]: [721QECRM2ZMT4]
[ro.sf.lcd_density]: [480]
[ro.soc.vendor]: [qcom]
[ro.support.rpmb]: [1]
[ro.sys.fw.bservice_age]: [5000]
[ro.sys.fw.bservice_enable]: [true]
[ro.sys.fw.bservice_limit]: [5]
[ro.sys.fw.dex2oat_thread_count]: [4]
[ro.sys.fw.empty_app_percent]: [50]
[ro.sys.fw.trim_cache_percent]: [100]
[ro.sys.fw.trim_empty_percent]: [100]
[ro.sys.fw.trim_enable_memory]: [2147483648]
[ro.sys.fw.use_trim_settings]: [true]
[ro.sys.usb.default.config]: [diag,serial_smd,rmnet_ipa,adb]
[ro.telephony.call_ring.multiple]: [false]
[ro.telephony.default_network]: [22,20]
[ro.use_data_netmgrd]: [true]
[ro.vendor.at_library]: [libqti-at.so]
[ro.vendor.extension_library]: [libqti-perfd-client.so]
[ro.vendor.gt_library]: [libqti-gt.so]
[ro.wifi.channels]: []
[ro.zygote]: [zygote64_32]
[security.perf_harden]: [1]
[selinux.reload_policy]: [1]
[service.bootanim.exit]: [1]
[sys.boot_completed]: [1]
[sys.ims.DATA_DAEMON_STATUS]: [1]
[sys.ims.ENABLE_HELPER]: [1]
[sys.ims.QMI_DAEMON_STATUS]: [1]
[sys.keymaster.loaded]: [true]
[sys.listeners.registered]: [true]
[sys.logbootcomplete]: [1]
[sys.meizu.m35x.white.config]: [false]
[sys.meizu.white.config]: [false]
[sys.oem_unlock_allowed]: [0]
[sys.post_boot.parsed]: [1]
[sys.precise.call.state]: [0,0,0]
[sys.sysctl.extra_free_kbytes]: [24300]
[sys.sysctl.tcp_def_init_rwnd]: [60]
[sys.usb.charging]: [yes]
[sys.usb.config]: [adb]
[sys.usb.ffs.ready]: [1]
[sys.usb.rps_mask]: [0]
[sys.usb.state]: [adb]
[telephony.lteOnCdmaDevice]: [1]
[tunnel.audio.encode]: [false]
[use.qti.sw.alac.decoder]: [true]
[use.qti.sw.ape.decoder]: [true]
[use.voice.path.for.pcm.voip]: [true]
[vidc.dec.downscalar_height]: [1088]
[vidc.dec.downscalar_width]: [1920]
[vidc.disable.split.mode]: [1]
[vidc.enc.dcvs.extra-buff-count]: [2]
[vidc.enc.disable.pq]: [true]
[vidc.enc.disable_bframes]: [1]
[vidc.enc.disable_pframes]: [1]
[voice.conc.fallbackpath]: [deep-buffer]
[voice.playback.conc.disabled]: [true]
[voice.record.conc.disabled]: [false]
[voice.voip.conc.disabled]: [true]
[vold.decrypt]: [trigger_restart_framework]
[vold.has_adoptable]: [0]
[vold.microsd.fstype]: []
[vold.microsd.inserted]: [false]
[vold.post_fs_data_done]: [1]
[wc_transport.stack_bdaddr]: [2b:1d:c2:31:57:2c]
[wifi.interface]: [wlan0]
[wlan.dbg]: [false]
[wlan.driver.ath]: [0]
[wlan.driver.config]: [/data/misc/wifi/WCNSS_qcom_cfg.ini]
[wlan.driver.status]: [ok]



```

## adb shell service list  全字符
```
Found 163 services:
0	call_adapter: [com.android.internal.telecom.IMzInCallAdapter]
1	qtitetherservice: [com.qualcomm.qti.tetherstatsextension.ITetherService]
2	dpmservice: [com.qti.dpm.IDpmService]
3	sip: [android.net.sip.ISipService]
4	com.qualcomm.location.izat.IzatService: [com.qualcomm.location.izat.IIzatService]
5	dcm_service: [com.meizu.datamanager.IDataCollectManagerInterface]
6	alphame_server: [com.meizu.common.alphame.IAlphaMe]
7	flyme_novccusagestats: [android.nonvccUsageStats.INonVccStatsInterfaces]
8	android.samsung.IRemosaicDaemon: []
9	qti.ims.ext: [org.codeaurora.ims.internal.IQtiImsExt]
10	ims: [com.android.ims.internal.IImsService]
11	carrier_config: [com.android.internal.telephony.ICarrierConfigLoader]
12	phone_ext: [android.telephony.ITelephonyExt]
13	phone: [com.android.internal.telephony.ITelephony]
14	telecom: [com.android.internal.telecom.ITelecomService]
15	extphone: [org.codeaurora.internal.IExtTelephony]
16	isms: [com.android.internal.telephony.ISms]
17	iphonesubinfo: [com.android.internal.telephony.IPhoneSubInfo]
18	simphonebook: [com.android.internal.telephony.IIccPhoneBook]
19	IIccPhoneBookMz: [com.android.internal.telephony.meizu.IIccPhoneBookMz]
20	isub: [com.android.internal.telephony.ISub]
21	cneservice: [com.quicinc.cne.ICNEManager]
22	flyme_theme_service: [android.content.res.flymetheme.IFlymeThemeManager]
23	contexthub_service: [android.hardware.location.IContextHubService]
24	netd_listener: []
25	connmetrics: [android.net.IIpConnectivityMetrics]
26	connectivity_metrics_logger: [android.net.IConnectivityMetricsLogger]
27	bluetooth_manager: [android.bluetooth.IBluetoothManager]
28	trp: [meizu.security.ITRPEng]
29	flyme_intelligent_engine: [android.perf.IIntelligentEngineService]
30	flyme_packagemanager: [android.content.pm.IFlymePackageManager]
31	flyme_wallpaper: [meizu.wallpaper.IFlymeWallpaper]
32	device_states: [meizu.os.IDeviceStateService]
33	hips_service: [meizu.hips.IFlymeHipsService]
34	flyme_permission: [meizu.security.IFlymePermissionService]
35	gesture_manager: [android.view.IGestureManager]
36	access_control: [meizu.security.IAccessControlManager]
37	move_window: [meizu.view.IMoveWindow]
38	imms: [com.android.internal.telephony.IMms]
39	media_projection: [android.media.projection.IMediaProjectionManager]
40	scene_server: [android.scene.ISceneService]
41	launcherapps: [android.content.pm.ILauncherApps]
42	shortcut: [android.content.pm.IShortcutService]
43	fingerprint: [android.hardware.fingerprint.IFingerprintService]
44	trust: [android.app.trust.ITrustManager]
45	media_router: [android.media.IMediaRouterService]
46	media_session: [android.media.session.ISessionManager]
47	restrictions: [android.content.IRestrictionsManager]
48	print: [android.print.IPrintManager]
49	graphicsstats: [android.view.IGraphicsStats]
50	dreams: [android.service.dreams.IDreamManager]
51	commontime_management: []
52	network_time_update_service: []
53	samplingprofiler: []
54	diskstats: []
55	voiceinteraction: [com.android.internal.app.IVoiceInteractionManagerService]
56	appwidget: [com.android.internal.appwidget.IAppWidgetService]
57	backup: [android.app.backup.IBackupManager]
58	soundtrigger: [com.android.internal.app.ISoundTriggerService]
59	jobscheduler: [android.app.job.IJobScheduler]
60	hardware_properties: [android.os.IHardwarePropertiesManager]
61	serial: [android.hardware.ISerialManager]
62	usb: [android.hardware.usb.IUsbManager]
63	midi: [android.media.midi.IMidiManager]
64	DockObserver: []
65	audio: [android.media.IAudioService]
66	wallpaper: [android.app.IWallpaperManager]
67	dropbox: [com.android.internal.os.IDropBoxManagerService]
68	search: [android.app.ISearchManager]
69	country_detector: [android.location.ICountryDetector]
70	location: [android.location.ILocationManager]
71	devicestoragemonitor: []
72	notification: [android.app.INotificationManager]
73	recovery: [android.os.IRecoverySystem]
74	updatelock: [android.os.IUpdateLock]
75	servicediscovery: [android.net.nsd.INsdManager]
76	pppoe: [android.net.pppoe.IPppoeManager]
77	connectivity: [android.net.IConnectivityManager]
78	ethernet: [android.net.IEthernetManager]
79	rttmanager: [android.net.wifi.IRttManager]
80	wifiscanner: [android.net.wifi.IWifiScanner]
81	wifi: [android.net.wifi.IWifiManager]
82	wifip2p: [android.net.wifi.p2p.IWifiP2pManager]
83	netpolicy: [android.net.INetworkPolicyManager]
84	netstats: [android.net.INetworkStatsService]
85	network_score: [android.net.INetworkScoreService]
86	textservices: [com.android.internal.textservice.ITextServicesManager]
87	networkmanagement_service_flyme: [android.os.INetworkManagementServiceFlyme]
88	network_management: [android.os.INetworkManagementService]
89	clipboard: [android.content.IClipboard]
90	flyme_statusbar: [meizu.statusbar.IFlymeStatusBarService]
91	statusbar: [com.android.internal.statusbar.IStatusBarService]
92	device_policy: [android.app.admin.IDevicePolicyManager]
93	deviceidle: [android.os.IDeviceIdleController]
94	persistent_data_block: [android.service.persistentdata.IPersistentDataBlockService]
95	lock_settings: [com.android.internal.widget.ILockSettings]
96	uimode: [android.app.IUiModeManager]
97	mount: [IMountService]
98	accessibility: [android.view.accessibility.IAccessibilityManager]
99	input_method: [com.android.internal.view.IInputMethodManager]
100	pinner: []
101	vrmanager: [android.service.vr.IVrManager]
102	input: [android.hardware.input.IInputManager]
103	window: [android.view.IWindowManager]
104	alarm: [android.app.IAlarmManager]
105	consumer_ir: [android.hardware.IConsumerIrService]
106	vibrator: [android.os.IVibratorService]
107	sensorservice: [android.gui.SensorServer]
108	content: [android.content.IContentService]
109	account: [android.accounts.IAccountManager]
110	media.camera.proxy: [android.hardware.ICameraServiceProxy]
111	telephony.registry: [com.android.internal.telephony.ITelephonyRegistry]
112	scheduling_policy: [android.os.ISchedulingPolicyService]
113	webviewupdate: [android.webkit.IWebViewUpdateService]
114	usagestats: [android.app.usage.IUsageStatsManager]
115	battery: []
116	device_control: [meizu.os.IDeviceControlService]
117	flyme_error_report_manager: [android.app.IFlymeErrorReportManager]
118	processinfo: [android.os.IProcessInfoService]
119	permission: [android.os.IPermissionController]
120	cpuinfo: []
121	dbinfo: []
122	gfxinfo: []
123	meminfo: []
124	procstats: [com.android.internal.app.procstats.IProcessStats]
125	activity: [android.app.IActivityManager]
126	user: [android.os.IUserManager]
127	otadexopt: [android.content.pm.IOtaDexopt]
128	package: [android.content.pm.IPackageManager]
129	media.camera: [android.hardware.ICameraService]
130	regionalization: [com.android.internal.os.IRegionalizationService]
131	display: [android.hardware.display.IDisplayManager]
132	power: [android.os.IPowerManager]
133	appops: [com.android.internal.app.IAppOpsService]
134	batterystats: [com.android.internal.app.IBatteryStats]
135	pps_server: [com.meizu.pps.IPPS]
136	netd: []
137	media.sound_trigger_hw: [android.hardware.ISoundTriggerHwService]
138	media.radio: [android.hardware.IRadioService]
139	media.audio_policy: [android.media.IAudioPolicyService]
140	qti.ims.connectionmanagerservice: []
141	meizu.camera: [meizu.camera.ICameraService]
142	android.vendor.meizu.IFacerecoDaemon: []
143	drm.drmManager: [drm.IDrmManagerService]
144	android.security.keystore: [android.security.IKeystoreService]
145	android.service.gatekeeper.IGateKeeperService: []
146	media.audio_flinger: [android.media.IAudioFlinger]
147	mdtp: [com.qualcomm.qti.securemsm.mdtp.IMdtpService]
148	media.resource_manager: [android.media.IResourceManagerService]
149	media.player: [android.media.IMediaPlayerService]
150	com.qualcomm.qti.qseeproxy: [com.qualcomm.qti.qseeproxy]
151	media.drm: [android.media.IMediaDrmService]
152	media.codec: [android.media.IMediaCodecService]
153	media.extractor: [android.media.IMediaExtractorService]
154	android.hardware.ifaa.IIfaaDaemon: []
155	android.hardware.fingerprint.IGoodixFingerprintDaemon: []
156	android.hardware.fingerprint.IFingerprintDaemon: []
157	batteryproperties: [android.os.IBatteryPropertiesRegistrar]
158	gpu: [android.ui.IGpuService]
159	SurfaceFlinger: [android.ui.ISurfaceComposer]
160	display.qservice: [android.display.IQService]
161	vendor.qcom.PeripheralManager: [vendor.qcom.IPeripheralManager]
162	eSEPowerManagerService: [IeSEPowerManager]


```

## adb shell dumpsys activity 全字符
```
ACTIVITY MANAGER PENDING INTENTS (dumpsys activity intents)
  * PendingIntentRecord{3cc3b93 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f043cf9 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3a390ca com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ab9db9b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3b5687c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{5fb7505 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2de645b com.android.phone broadcastIntent}
  * PendingIntentRecord{fb3979 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4a6a4f8 com.meizu.safe startService}
  * PendingIntentRecord{c0c391d com.android.mms broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{af137a1 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b22504e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{107fdd1 com.ss.android.article.news startService}
  * PendingIntentRecord{bf675ab com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c68af5d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f14eb37 com.meizu.net.pedometer broadcastIntent}
  * PendingIntentRecord{f1d18a4 com.meizu.flyme.wallet startActivity}
  * PendingIntentRecord{a7ad7d0 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{caea548 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{f28eaab com.android.systemui startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{c86ef25 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3ecea com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{909f5c6 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{4e7680e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{fbda60d android startActivity}
  * PendingIntentRecord{5081f89 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{d06c1d0 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{6daec66 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{95920f com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{723a9cd com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{765d9c2 com.meizu.safe startService}
  * PendingIntentRecord{cc303d3 com.meizu.alphame broadcastIntent}
  * PendingIntentRecord{2651b10 android broadcastIntent}
  * PendingIntentRecord{1ab9609 android broadcastIntent}
  * PendingIntentRecord{6a8381e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{1bbd00e com.tencent.androidqqmail startService}
  * PendingIntentRecord{f300a2f com.meizu.safe startService}
  * PendingIntentRecord{9e8d83c com.tencent.mm broadcastIntent}
  * PendingIntentRecord{407e3c7 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{9372c08 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{742ad2b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{958c541 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3892428 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f0b3779 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{179e3a com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3a52493 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{46e89c5 com.tencent.mm startService}
  * PendingIntentRecord{468df1a com.tencent.mm startService}
  * PendingIntentRecord{46deb61 com.android.incallui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{cbd1a4b android broadcastIntent}
  * PendingIntentRecord{dbb9ce3 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e622140 com.tencent.mm startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{59e84c9 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b893c28 com.meizu.safe broadcastIntent}
  * PendingIntentRecord{adffd58 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8b2fd41 android startActivity}
  * PendingIntentRecord{6b42aa6 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ea5d2e6 android broadcastIntent}
  * PendingIntentRecord{905ac69 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{63cf48d com.android.systemui startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{33efc15 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4c2c086 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6c11027 com.tencent.mm startService}
  * PendingIntentRecord{1570be8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{849f2d4 android broadcastIntent}
  * PendingIntentRecord{7258c4d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7efc5f1 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{fb9859 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{86341af com.sina.weibo broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ff3cf74 com.android.incallui startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{3712c7d com.meizu.media.music broadcastIntent}
  * PendingIntentRecord{316f991 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e9dca54 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c4ebe74 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4429e3d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{86c3772 com.meizu.pps broadcastIntent}
  * PendingIntentRecord{5030399 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{32487c3 com.tencent.androidqqmail startService}
  * PendingIntentRecord{bf8d1ce com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{bbd6840 com.tencent.mm broadcastIntent}
  * PendingIntentRecord{5b0ee13 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{69a34fa com.android.systemui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ba2fcf6 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{a9a0b25 com.android.systemui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6e91379 com.flyme.systemuitools broadcastIntent}
  * PendingIntentRecord{596fb21 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{9cd8a1c com.android.mms broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{157a3c0 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ee87c81 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{81b9535 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7358a8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{bbbab42 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8e6432c android startActivity}
  * PendingIntentRecord{5a15ae3 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4db9628 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4d39996 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{dc258be com.android.packageinstaller broadcastIntent}
  * PendingIntentRecord{3bddd1f android broadcastIntent}
  * PendingIntentRecord{630c86c com.meizu.alphame broadcastIntent}
  * PendingIntentRecord{263358e com.sina.weibo startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{f5e6e35 com.meizu.flyme.wallet startActivity}
  * PendingIntentRecord{ce88cbd com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{85abb93 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b437b4e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{20f1a46 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f442987 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{2c742ca com.ss.android.article.news broadcastIntent}
  * PendingIntentRecord{76d2c3b com.tencent.androidqqmail startService}
  * PendingIntentRecord{7337e1a in.zhaoj.shadowsocksrr startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{d97ff58 android broadcastIntent}
  * PendingIntentRecord{df1bbb2 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f94b8b1 android broadcastIntent}
  * PendingIntentRecord{58dd364 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{e7b3106 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{eb7565e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b0d47c0 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f1e279f com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c7ec196 com.tencent.mobileqq broadcastIntent}
  * PendingIntentRecord{74f6f89 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f85117 com.meizu.net.pedometer broadcastIntent}
  * PendingIntentRecord{4960c22 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ccd09e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3e380ec com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e4ec950 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a0ef9ca com.android.packageinstaller startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{32a350d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a30bcc2 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{58c37cf com.tencent.mm startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{3c86640 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4782b3b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{24a7328 in.zhaoj.shadowsocksrr startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{837e3bb com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{35ed88f com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a075459 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{bf693fa com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{3633fc5 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{473ffe2 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{53052d8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{139b904 com.meizu.location broadcastIntent}
  * PendingIntentRecord{1accdf4 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{e4ecb42 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{90b2eed com.meizu.mzsyncservice startService}
  * PendingIntentRecord{281f4f com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a5d6122 cm.aptoide.pt startService}
  * PendingIntentRecord{76dd31d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{1b8f56c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7dfe979 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4087c33 android startActivity}
  * PendingIntentRecord{3650b6e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6f9277f com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a408625 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{977d727 android startActivity}
  * PendingIntentRecord{293b407 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f051cff com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a8c3ed8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{fa6e7b3 com.android.systemui broadcastIntent}
  * PendingIntentRecord{374cd4b in.zhaoj.shadowsocksrr broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2ac0e86 com.meizu.flyme.update startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{68988a6 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6da0ef0 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4332831 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{979f282 com.android.mms broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{cd78e7e android startActivity}
  * PendingIntentRecord{cdb24d0 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{4795c78 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c1b6170 com.android.systemui broadcastIntent}
  * PendingIntentRecord{2f6e56b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{bf8cce9 com.tencent.mm startService}
  * PendingIntentRecord{96c78d3 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7edb63a com.android.dialer broadcastIntent}
  * PendingIntentRecord{66f5dab com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{28c0a67 com.tencent.androidqqmail startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{91100c5 com.sina.weibo broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{9bcd8f7 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{ddc5c95 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c946d6e com.meizu.flyme.wallet startActivity}
  * PendingIntentRecord{12d4b6b com.meizu.flyme.update broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{dc3d8cb com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{d5cfe71 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8b6c711 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f1b1ed5 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{565578c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{59e4c0f com.meizu.location broadcastIntent}
  * PendingIntentRecord{a91d7d2 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{22d249c com.meizu.safe broadcastIntent}
  * PendingIntentRecord{b084ea5 com.meizu.flyme.input startService}
  * PendingIntentRecord{76ee048 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6e84917 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{69a44a2 com.android.systemui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7e4c533 com.android.systemui startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{1453879 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{31df27a com.meizu.mstore broadcastIntent}
  * PendingIntentRecord{64cbcee com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6d1142b com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{7fd9a2b com.tencent.mm startActivity}
  * PendingIntentRecord{40f9735 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{9ebfb47 com.android.incallui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{435ee88 com.sina.weibo broadcastIntent}
  * PendingIntentRecord{7fdac14 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{75f2a7c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ed43021 com.tencent.mm broadcastIntent}
  * PendingIntentRecord{188bc46 com.tencent.mm broadcastIntent}
  * PendingIntentRecord{4b9ae07 com.tencent.androidqqmail startService}
  * PendingIntentRecord{7602c24 android startActivity}
  * PendingIntentRecord{67f6b34 com.meizu.location broadcastIntent}
  * PendingIntentRecord{e8af686 com.android.incallui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2382331 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{107082b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{cc24749 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ea02076 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{20179b5 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{9af831d com.sina.weibo startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{522ad5d com.tencent.mm startService}
  * PendingIntentRecord{476e848 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b6c7562 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ecde28e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{583e992 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{1886c9 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2a362eb com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{251a77b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2185fa1 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{38456d2 cm.aptoide.pt startService}
  * PendingIntentRecord{a687fe1 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{ec73fbc com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{d05e6c8 com.meizu.flyme.update broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a3923a3 com.tencent.mobileqq broadcastIntent}
  * PendingIntentRecord{84eaaf com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{64206a0 com.tencent.mm broadcastIntent}
  * PendingIntentRecord{7e1c259 com.meizu.location broadcastIntent}
  * PendingIntentRecord{2bf69af com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e2d0e1e com.meizu.safe broadcastIntent}
  * PendingIntentRecord{2171394 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{cb056ff com.tencent.mm startService}
  * PendingIntentRecord{23fa2a0 com.tencent.mm startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{9ffd84c com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7663cc9 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{9d0eccc com.android.bluetooth broadcastIntent}
  * PendingIntentRecord{b528044 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{fd5fd99 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e757094 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3f47cd8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8c878c8 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a915850 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7e32b15 com.tencent.mobileqq broadcastIntent}
  * PendingIntentRecord{471240f android startActivity}
  * PendingIntentRecord{ad75bdb com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8efb0f0 com.android.systemui broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{8c8e4c3 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a17ee2a com.tencent.mobileqq broadcastIntent}
  * PendingIntentRecord{53d641b android broadcastIntent}
  * PendingIntentRecord{3aca538 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2c90989 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{1ff3eb com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{78609b8 com.hexin.plat.android startService}
  * PendingIntentRecord{7586391 com.meizu.flyme.input broadcastIntent}
  * PendingIntentRecord{f8ffe6d com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{21ec2f6 com.meizu.flyme.launcher broadcastIntent}
  * PendingIntentRecord{d766397 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{6c426f7 com.meizu.location broadcastIntent}
  * PendingIntentRecord{ba01d1b com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b075b8e com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{206fff4 com.sina.weibo broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{f6e0964 android broadcastIntent}
  * PendingIntentRecord{22a5777 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{35c0d93 com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{ea7cd com.android.calendar broadcastIntent}
  * PendingIntentRecord{2ec1882 android broadcastIntent}
  * PendingIntentRecord{5861a16 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{1533347 com.meizu.flyme.update broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{a5f0f05 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{68283b7 android startActivity}
  * PendingIntentRecord{a314647 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{b8a9984 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{c419c40 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{48a3b93 com.tencent.mm startService}
  * PendingIntentRecord{fa5e649 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{19a1684 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{86d753 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7d78a1a com.sina.weibo broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{3b457d0 com.tencent.mm startService}
  * PendingIntentRecord{9f1f695 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{16af3c9 android startActivity}
  * PendingIntentRecord{94dc368 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{cce9b3a com.android.mms startActivity (whitelist: +30s0ms)}
  * PendingIntentRecord{c9f0890 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{7473ace com.meizu.assistant broadcastIntent}
  * PendingIntentRecord{692fdef android broadcastIntent}
  * PendingIntentRecord{9533daa com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{bcf20fc com.tencent.mm broadcastIntent}
  * PendingIntentRecord{42f4f41 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e668e09 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{ad0cccd com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e846361 com.meizu.flyme.update broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{2260385 com.iflytek.speechsuite broadcastIntent}
  * PendingIntentRecord{d2035da com.tencent.mm broadcastIntent}
  * PendingIntentRecord{7bb8a0b android startActivity}
  * PendingIntentRecord{e6b50e8 com.meizu.mzsyncservice broadcastIntent}
  * PendingIntentRecord{8e6ad57 com.meizu.cloud broadcastIntent (whitelist: +30s0ms)}
  * PendingIntentRecord{e3f33c9 android broadcastIntent}

ACTIVITY MANAGER BROADCAST STATE (dumpsys activity broadcasts)
  Historical broadcasts [foreground]:
  #0: BroadcastRecord{e12957c u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545040821771 flg=0x10000010 (has extras)
    targetComp: {com.meizu.cloud/com.meizu.cloud.push.CloudPushReceiver}
    extras: Bundle[{notification_extra_task_id=501652, method=notification_state, notification_extra_seq_id=13493, notification_extra_device_id=867828038942864, notification_extra_push_timestamp=1545040800, flyme:notification_pkg=com.meizu.media.reader, flyme:notification_id=33085694, flyme:notification_cause=0, flyme:notification_state=0, notification_extra_show_package_name=com.meizu.media.reader, notification_state_message={"appId":"com.meizu.media.reader","data":{"content":"农行正式宣布","title":"再见，银行卡","isDiscard":false,"clickType":3,"extra":{"activity":"","url":"","parameters":{"sk":"{\"t\":0,\"m\":{\"i\":1545031977120001,\"ui\":\"b9e8bfbc6db84c4c964315f028335365\",\"ot\":1,\"rt\":4,\"rst\":\"NORMAL\",\"rid\":48,\"r\":\"精选\",\"t\":\"再见，银行卡\",\"c\":\"农行正式宣布\",\"u\":\"http://reader.res.meizu.com/reader/articlecontent/20181217/1545031977120001.json\",\"d\":1545033041606,\"pid\":1545033041606,\"w\":0,\"bt\":0,\"img\":\"\",\"bcid\":-1,\"is\":0,\"indb\":false,\"vw\":0,\"vh\":0,\"ct\":4,\"bvcid\":46,\"pbt\":0,\"dp\":1}}"},"pk":"","task_id":"501652","ns":{"bs":2,"is":2,"et":"","ei":"http://image.res.meizu.com/image/garcia/340a1ae5c6184d73a113899059e8adcdz","bi":""},"as":{"it":0,"nt":{"v":1,"l":1,"s":1},"cn":1,"hn":1},"is":{"di":1,"li":""},"ts":{"td":0,"st":0,"et":0},"acts":{"wd":1,"au":""},"fns":{"fpgct":0},"no":{"ni":33085694,"nk":""},"se":"ZILKlTUshMhG0oCk02sTqSXQhaNr3y1fr64rqrCeVjWL+JnJAUN/fey6+lbZxBLHylMGklBWV+MtoW03rY4srGAjL5M7vVDSyQCWtovxax0X06FBN4tK2/2VQaK42iuT/mdN+bZat6TNoFXGb3NRH4SQwTm4N+vLK8NYaFNBO5U="}}}}]
  #1: BroadcastRecord{c1c96aa u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #2: BroadcastRecord{417c76a u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #3: BroadcastRecord{5d5d33f u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #4: BroadcastRecord{1c85301 u-1 android.intent.action.SCREEN_ON}
    act=android.intent.action.SCREEN_ON flg=0x50000010
  #5: BroadcastRecord{bed38ff u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #6: BroadcastRecord{f5bd5a6 u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545040606359 flg=0x10000010 (has extras)
    targetComp: {com.meizu.cloud/com.meizu.cloud.push.CloudPushReceiver}
    extras: Bundle[{notification_extra_task_id=NS20181217175644520_0_11012701, method=notification_state, notification_extra_seq_id=13492, notification_extra_device_id=867828038942864, notification_extra_push_timestamp=1545040604, flyme:notification_pkg=com.sina.weibo, flyme:notification_id=40604520, flyme:notification_cause=0, flyme:notification_state=-1, notification_extra_show_package_name=com.sina.weibo, notification_state_message={"appId":"com.sina.weibo","data":{"content":"集餐卡，赢小米家宴直通资格等壕礼！今日，最后一张重磅大餐卡「...(图)>>","title":"特别关注 小米公司","isDiscard":false,"clickType":2,"extra":{"activity":"","url":"sinaweibo://changegroup?groupid=10001&groupType=0&secondLevel=0&push_mid=4318275565046917&needuid=1&luicode=10000177&lfid=4318275565046917","pk":"","task_id":"NS20181217175644520_0_11012701","ns":{"bs":2,"is":0,"et":"","ei":"","bi":""},"as":{"it":0,"nt":{"v":1,"l":1,"s":1},"cn":1,"hn":1},"is":{"di":1,"li":""},"ts":{"td":0,"st":0,"et":0},"fns":{"fpgct":0},"no":{"ni":40604520,"nk":""},"se":"JkVZ9NtaaRuFYrRiXBcdUXHiTgjvI+1DwjHAKTAgw6SmsRKgV7k/546EwFinBQtfemaq7qPjIlbHS3O3mQBC8V4+7mnViu7xxo67qNjT5BmzIrUbTC6pQMehxAj5CbQJi76AkuwRHLQgWo69UY3JmcBS5vBvNvPqdxBommT8zWw="}}}}]
  #7: BroadcastRecord{e30a646 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #8: BroadcastRecord{af82b96 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #9: BroadcastRecord{3c98ab1 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #10: BroadcastRecord{99e0cca u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #11: BroadcastRecord{78a2035 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #12: BroadcastRecord{996bbe7 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #13: BroadcastRecord{e189394 u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545040223381 flg=0x10000010 (has extras)
    targetComp: {com.meizu.cloud/com.meizu.cloud.push.CloudPushReceiver}
    extras: Bundle[{notification_extra_task_id=501646, method=notification_state, notification_extra_seq_id=13491, notification_extra_device_id=867828038942864, notification_extra_push_timestamp=1545040203, flyme:notification_pkg=com.meizu.media.video, flyme:notification_id=32705735, flyme:notification_cause=0, flyme:notification_state=0, notification_extra_show_package_name=com.meizu.media.video, notification_state_message={"appId":"com.meizu.media.video","data":{"content":"2019年开始执行 >>","title":"这2种农房每户能领40万补助","isDiscard":false,"clickType":3,"extra":{"activity":"","url":"","parameters":{"sk":"{\"contentEnum\":\"VIDEO\",\"cpId\":32,\"description\":\"2019年开始执行 >>\\n\",\"idStr\":\"133099562\",\"ifCp\":0,\"imageUrl\":\"\",\"isPush\":1,\"openType\":\"API\",\"pushId\":4673,\"title\":\"这2种农房每户能领40万补助\",\"token\":\"%2FPzNZPdf2%2BAJOYr%2FrpcA1Q%3D%3D\"}"},"pk":"","task_id":"501646","ns":{"bs":2,"is":2,"et":"","ei":"http://image.res.meizu.com/image/garcia/db697e200d7f4fd6b99e62a35c82e797z","bi":""},"as":{"it":0,"nt":{"v":1,"l":1,"s":1},"cn":1,"hn":1},"is":{"di":1,"li":""},"ts":{"td":0,"st":0,"et":0},"acts":{"wd":1,"au":""},"fns":{"fpgct":0},"no":{"ni":32705735,"nk":""},"se":"A3OXb0n+L+OvC678+UewBqo0Lkc/0rpNWYA9vMg7IFhBsHG4lCIUc5dkOEDQWxChPWKVhtHHZzvaEjuIq9xMC8cP87VO1gZwnf86tavvnrhjeK9Pckv+CZuNIV8AmnKtCtByluJIV62qDSrQqtNXi5CGl/jR3pzF2MBJr4AyHd0="}}}}]
  #14: BroadcastRecord{5fa632 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #15: BroadcastRecord{5f7a809 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #16: BroadcastRecord{a092f83 u-1 org.codeaurora.bluetooth.RCC_CHANGED_ACTION}
    act=org.codeaurora.bluetooth.RCC_CHANGED_ACTION flg=0x14000010 (has extras)
    extras: Bundle[{org.codeaurora.bluetooth.EXTRA_CALLING_PACKAGE_NAME=com.meizu.media.music, org.codeaurora.bluetooth.EXTRA_FOCUS_CHANGED_VALUE=true, org.codeaurora.bluetooth.EXTRA_AVAILABLITY_CHANGED_VALUE=true}]
  #17: BroadcastRecord{4b55500 u0 com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD}
    act=com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD flg=0x10000014 (has extras)
    extras: Bundle[{seq=188, android.intent.extra.ALARM_COUNT=1}]
  #18: BroadcastRecord{589469d u-1 android.intent.action.SCREEN_OFF}
    act=android.intent.action.SCREEN_OFF flg=0x50000010
  #19: BroadcastRecord{17aaa1c u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #20: BroadcastRecord{f1b6139 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #21: BroadcastRecord{15df37e u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #22: BroadcastRecord{6a540df u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #23: BroadcastRecord{964c9da u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #24: BroadcastRecord{99ac12c u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, adb=true, configured=true}]
  #25: BroadcastRecord{5693f25 u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, adb=true, configured=false}]
  #26: BroadcastRecord{1c7d7f5 u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, adb=true, configured=true}]
  #27: BroadcastRecord{261c98a u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, adb=true, configured=false}]
  #28: BroadcastRecord{9c70bfb u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #29: BroadcastRecord{e88c418 u-1 android.intent.action.CLOSE_SYSTEM_DIALOGS}
    act=android.intent.action.CLOSE_SYSTEM_DIALOGS flg=0x50000010 (has extras)
    extras: Bundle[{reason=homekey}]
  #30: BroadcastRecord{e8afe71 u-1 android.intent.action.CLOSE_SYSTEM_DIALOGS}
    act=android.intent.action.CLOSE_SYSTEM_DIALOGS flg=0x50000010 (has extras)
    extras: Bundle[{reason=homekey}]
  #31: BroadcastRecord{9749836 u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, configured=true, charging=true}]
  #32: BroadcastRecord{71af456 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #33: BroadcastRecord{e229f95 u-1 android.intent.action.SCREEN_ON}
    act=android.intent.action.SCREEN_ON flg=0x50000010
  #34: BroadcastRecord{d706cd7 u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, configured=true, charging=true}]
  #35: BroadcastRecord{f5209c4 u-1 android.hardware.usb.action.USB_STATE}
    act=android.hardware.usb.action.USB_STATE flg=0x30000010 (has extras)
    extras: Bundle[{host_connected=false, connected=true, unlocked=false, configured=false, charging=true}]
  #36: BroadcastRecord{68010ad u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #37: BroadcastRecord{14b634c u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #38: BroadcastRecord{c69ffe2 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #39: BroadcastRecord{7fb8043 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #40: BroadcastRecord{bd9c1f2 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #41: BroadcastRecord{fe4ff73 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #42: BroadcastRecord{47fe30 u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545038894606 flg=0x10000010 (has extras)
    targetComp: {com.meizu.cloud/com.meizu.cloud.push.CloudPushReceiver}
    extras: Bundle[{notification_extra_task_id=NS20181217172752379_0_11448507_2_3, method=notification_state, notification_extra_seq_id=13490, notification_extra_device_id=867828038942864, notification_extra_push_timestamp=1545038872, flyme:notification_pkg=com.hexin.plat.android, flyme:notification_id=38872379, flyme:notification_cause=0, flyme:notification_state=0, notification_extra_show_package_name=com.hexin.plat.android, notification_state_message={"appId":"com.hexin.plat.android","data":{"content":"【超级收评】兰生股份今天得到有效支撑了吗？","title":"同花顺","isDiscard":false,"clickType":3,"extra":{"activity":"","url":"","parameters":{"sk":"{\"id\":\"5157897472\",\"msgtype\":0,\"importance\":\"5\",\"starttime\":\"1545038499\",\"comefrom\":\"103\",\"subcomefrom\":\"103\",\"M\":{\"A\":\"10\",\"F\":\"2729\",\"U\":\"http:\\/\\/eq.10jqka.com.cn\\/download\\/personalizedPush\\/index.html?date=20181217&id=2033150&code=600826&title=%E3%80%90%E8%B6%85%E7%BA%A7%E6%94%B6%E8%AF%84%E3%80%91%E5%85%B0%E7%94%9F%E8%82%A1%E4%BB%BD%E4%BB%8A%E5%A4%A9%E5%BE%97%E5%88%B0%E6%9C%89%E6%95%88%E6%94%AF%E6%92%91%E4%BA%86%E5%90%97%EF%BC%9F\"},\"title\":\"\\u540c\\u82b1\\u987a\",\"alert\":\"\\u3010\\u8d85\\u7ea7\\u6536\\u8bc4\\u3011\\u5170\\u751f\\u80a1\\u4efd\\u4eca\\u5929\\u5f97\\u5230\\u6709\\u6548\\u652f\\u6491\\u4e86\\u5417\\uff1f\"}"},"pk":"","task_id":"NS20181217172752379_0_11448507_2_3","ns":{"bs":2,"is":1,"et":"【超级收评】兰生股份今天得到有效支撑了吗？","ei":"","bi":""},"as":{"it":0,"nt":{"v":1,"l":1,"s":1},"cn":1,"hn":1},"is":{"di":1,"li":""},"ts":{"td":0,"st":0,"et":0},"fns":{"fpgct":0},"no":{"ni":38872379,"nk":"57897472"},"se":"twN1Qjqf1aJrCRzE79HeEqIO4T9UtAxSzkbT69MbS617DzdN8q3Enx8jDP22RsOl9Og9LcQlu1IIAiqanlo7TsieVurSdu94IwR1xZBcL12d/TI6t6PlpoIADKGK7kFrV7bcU4om+iO9I7Fudpxs3Hq7OdxkysVDhAHlM6J81A0="}}}}]
  #43: BroadcastRecord{fb2dc53 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #44: BroadcastRecord{73a0aa9 u-1 org.codeaurora.bluetooth.RCC_CHANGED_ACTION}
    act=org.codeaurora.bluetooth.RCC_CHANGED_ACTION flg=0x14000010 (has extras)
    extras: Bundle[{org.codeaurora.bluetooth.EXTRA_CALLING_PACKAGE_NAME=com.meizu.media.music, org.codeaurora.bluetooth.EXTRA_FOCUS_CHANGED_VALUE=true, org.codeaurora.bluetooth.EXTRA_AVAILABLITY_CHANGED_VALUE=true}]
  #45: BroadcastRecord{aac382e u0 com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD}
    act=com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD flg=0x10000014 (has extras)
    extras: Bundle[{seq=186, android.intent.extra.ALARM_COUNT=1}]
  #46: BroadcastRecord{a87925c u-1 android.intent.action.SCREEN_OFF}
    act=android.intent.action.SCREEN_OFF flg=0x50000010
  #47: BroadcastRecord{6061fcf u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #48: BroadcastRecord{766cd5c u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #49: BroadcastRecord{a7fa865 u-1 android.intent.action.TIME_TICK}
    act=android.intent.action.TIME_TICK flg=0x50000014 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #50: act=com.meizu.flyme.push.intent.MESSAGE dat=custom://1545040821771 flg=0x10000010 (has extras)
    0 dispatch +10ms finish
    enq=2018-12-17 18:00:21 disp=2018-12-17 18:00:21 fin=2018-12-17 18:00:21
    extras: Bundle[{notification_extra_task_id=501652, method=notification_state, notification_extra_seq_id=13493, notification_extra_device_id=867828038942864, notification_extra_push_timestamp=1545040800, flyme:notification_pkg=com.meizu.media.reader, flyme:notification_id=33085694, flyme:notification_cause=0, flyme:notification_state=0, notification_extra_show_package_name=com.meizu.media.reader, notification_state_message={"appId":"com.meizu.media.reader","data":{"content":"农行正式宣布","title":"再见，银行卡","isDiscard":false,"clickType":3,"extra":{"activity":"","url":"","parameters":{"sk":"{\"t\":0,\"m\":{\"i\":1545031977120001,\"ui\":\"b9e8bfbc6db84c4c964315f028335365\",\"ot\":1,\"rt\":4,\"rst\":\"NORMAL\",\"rid\":48,\"r\":\"精选\",\"t\":\"再见，银行卡\",\"c\":\"农行正式宣布\",\"u\":\"http://reader.res.meizu.com/reader/articlecontent/20181217/1545031977120001.json\",\"d\":1545033041606,\"pid\":1545033041606,\"w\":0,\"bt\":0,\"img\":\"\",\"bcid\":-1,\"is\":0,\"indb\":false,\"vw\":0,\"vh\":0,\"ct\":4,\"bvcid\":46,\"pbt\":0,\"dp\":1}}"},"pk":"","task_id":"501652","ns":{"bs":2,"is":2,"et":"","ei":"http://image.res.meizu.com/image/garcia/340a1ae5c6184d73a113899059e8adcdz","bi":""},"as":{"it":0,"nt":{"v":1,"l":1,"s":1},"cn":1,"hn":1},"is":{"di":1,"li":""},"ts":{"td":0,"st":0,"et":0},"acts":{"wd":1,"au":""},"fns":{"fpgct":0},"no":{"ni":33085694,"nk":""},"se":"ZILKlTUshMhG0oCk02sTqSXQhaNr3y1fr64rqrCeVjWL+JnJAUN/fey6+lbZxBLHylMGklBWV+MtoW03rY4srGAjL5M7vVDSyQCWtovxax0X06FBN4tK2/2VQaK42iuT/mdN+bZat6TNoFXGb3NRH4SQwTm4N+vLK8NYaFNBO5U="}}}}]
  ...

  Historical broadcasts [background]:
  #0: BroadcastRecord{15d9e05 u0 com.sina.smart.heartbeat.action1004}
    act=com.sina.smart.heartbeat.action1004 flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #1: BroadcastRecord{7dffe5a u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #2: BroadcastRecord{d7ae88b u0 com.meizu.net.pedometer.wakeup_service}
    act=com.meizu.net.pedometer.wakeup_service flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #3: BroadcastRecord{6b18d68 u0 com.tencent.mm.TrafficStatsReceiver}
    act=com.tencent.mm.TrafficStatsReceiver flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #4: BroadcastRecord{91f1581 u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #5: BroadcastRecord{d8fa626 u-1 android.intent.action.BATTERY_CHANGED}
    act=android.intent.action.BATTERY_CHANGED flg=0x60000010 (has extras)
    extras: Bundle[{technology=Li-ion, icon-small=17303284, max_charging_voltage=0, health=2, max_charging_current=0, status=2, plugged=2, present=true, charge_counter=0, level=100, scale=100, temperature=302, voltage=4403, invalid_charger=0}]
  #6: BroadcastRecord{22d8267 u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #7: BroadcastRecord{76ee9eb u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #8: BroadcastRecord{c416348 u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #9: BroadcastRecord{5c765e1 u0 com.tencent.mobileqq:MSF_85207335}
    act=com.tencent.mobileqq:MSF_85207335 flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #10: BroadcastRecord{3f71f06 u0 null}
    flg=0x14 (has extras)
    targetComp: {com.tencent.mm/com.tencent.mm.booter.MMReceivers$AlarmReceiver}
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #11: BroadcastRecord{5039c7 u0 com.tencent.mm.TrafficStatsReceiver}
    act=com.tencent.mm.TrafficStatsReceiver flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #12: BroadcastRecord{aad6bf4 u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #13: BroadcastRecord{5737f1d u0 com.baidu.input.entry.launcer}
    act=com.baidu.input.entry.launcer flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=40]
  #14: BroadcastRecord{978f154 u0 android.intent.action.MEDIA_SCANNER_SCAN_FILE}
    act=android.intent.action.MEDIA_SCANNER_SCAN_FILE dat=file:///sdcard/demo.mp4 flg=0x10
  #15: BroadcastRecord{562592 u0 com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING}
    act=com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=212]
  #16: BroadcastRecord{50cab63 u0 com.sina.push.msg.broadcast.1004}
    act=com.sina.push.msg.broadcast.1004 flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=212]
  #17: BroadcastRecord{42f5360 u0 com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING}
    act=com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=216]
  #18: BroadcastRecord{fcdf019 u0 com.sina.push.msg.broadcast.1004}
    act=com.sina.push.msg.broadcast.1004 flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=216]
  #19: BroadcastRecord{62d08de u0 action_unread_update}
    act=action_unread_update flg=0x10
  #20: BroadcastRecord{3949abf u0 com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING}
    act=com.sina.weibo.action.NEW_PUSH_WESYNC_MSG_COMING flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=212]
  #21: BroadcastRecord{c26458c u0 com.sina.push.msg.broadcast.1004}
    act=com.sina.push.msg.broadcast.1004 flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=212]
  #22: BroadcastRecord{bc474d5 u0 com.sina.push.action.receivegdid}
    act=com.sina.push.action.receivegdid flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=80]
  #23: BroadcastRecord{ffcd4ea u0 com.sina.push.msg.broadcast.1004}
    act=com.sina.push.msg.broadcast.1004 flg=0x10 pkg=com.sina.weibo (has extras)
    extras: Bundle[mParcelledData.dataSize=224]
  #24: BroadcastRecord{e8223db u0 com.meizu.flyme.update.download_completed}
    act=com.meizu.flyme.update.download_completed flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=308]
  #25: BroadcastRecord{ab82e78 u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE cat=[com.meizu.netcontactservice] flg=0x10 pkg=com.meizu.netcontactservice (has extras)
    targetComp: {com.meizu.netcontactservice/com.meizu.breakingscam.push.MzBreakingScamPushMessageReceiver}
    extras: Bundle[mParcelledData.dataSize=560]
  #26: BroadcastRecord{7648951 u0 com.meizu.cloud.pushservice.action.PUSH_SERVICE_START}
    act=com.meizu.cloud.pushservice.action.PUSH_SERVICE_START flg=0x10 pkg=com.meizu.mstore
  #27: BroadcastRecord{d4b55b6 u0 com.meizu.flyme.push.intent.MESSAGE}
    act=com.meizu.flyme.push.intent.MESSAGE cat=[com.meizu.mstore] flg=0x10 pkg=com.meizu.mstore (has extras)
    targetComp: {com.meizu.mstore/com.meizu.cloud.push.AppPushReceiver}
    extras: Bundle[mParcelledData.dataSize=560]
  #28: BroadcastRecord{df522b7 u0 com.meizu.cloud.pushservice.action.PUSH_SERVICE_START}
    act=com.meizu.cloud.pushservice.action.PUSH_SERVICE_START flg=0x10 pkg=com.meizu.mstore
  #29: BroadcastRecord{8101830 u0 android.intent.action.USER_PRESENT}
    act=android.intent.action.USER_PRESENT flg=0x24000010
  #30: BroadcastRecord{27dba24 u-1 com.android.server.action.NETWORK_STATS_UPDATED}
    act=com.android.server.action.NETWORK_STATS_UPDATED flg=0x40000010
  #31: BroadcastRecord{a17698d u0 android.intent.action.USER_PRESENT}
    act=android.intent.action.USER_PRESENT flg=0x24000010
  #32: BroadcastRecord{e2f1742 u0 com.meizu.net.pedometer.start_step}
    act=com.meizu.net.pedometer.start_step flg=0x10 pkg=com.meizu.net.pedometer
  #33: BroadcastRecord{22f3353 u-1 android.intent.action.DREAMING_STOPPED}
    act=android.intent.action.DREAMING_STOPPED flg=0x40000010
  #34: BroadcastRecord{4ee5490 u-1 android.location.HIGH_POWER_REQUEST_CHANGE}
    act=android.location.HIGH_POWER_REQUEST_CHANGE flg=0x10
  #35: BroadcastRecord{e9e1189 u-1 android.location.GPS_ENABLED_CHANGE}
    act=android.location.GPS_ENABLED_CHANGE flg=0x10 (has extras)
    extras: Bundle[{enabled=false}]
  #36: BroadcastRecord{b9b658e u-1 android.location.HIGH_POWER_REQUEST_CHANGE}
    act=android.location.HIGH_POWER_REQUEST_CHANGE flg=0x10
  #37: BroadcastRecord{3efb1af u-1 android.location.HIGH_POWER_REQUEST_CHANGE}
    act=android.location.HIGH_POWER_REQUEST_CHANGE flg=0x10
  #38: BroadcastRecord{18c29bc u-1 android.location.GPS_ENABLED_CHANGE}
    act=android.location.GPS_ENABLED_CHANGE flg=0x10 (has extras)
    extras: Bundle[{enabled=true}]
  #39: BroadcastRecord{cd3d45 u-1 android.location.HIGH_POWER_REQUEST_CHANGE}
    act=android.location.HIGH_POWER_REQUEST_CHANGE flg=0x10
  #40: BroadcastRecord{86c4c9a u0 ALARM_ACTION(16174)}
    act=ALARM_ACTION(16174) flg=0x14 (has extras)
    extras: Bundle[{ID=535, PID=16174, android.intent.extra.ALARM_COUNT=1}]
  #41: BroadcastRecord{e8fb9cb u-1 android.intent.action.BATTERY_CHANGED}
    act=android.intent.action.BATTERY_CHANGED flg=0x60000010 (has extras)
    extras: Bundle[{technology=Li-ion, icon-small=17303284, max_charging_voltage=0, health=2, max_charging_current=0, status=2, plugged=2, present=true, charge_counter=0, level=99, scale=100, temperature=300, voltage=4408, invalid_charger=0}]
  #42: BroadcastRecord{e6d2e3b u0 com.meizu.net.pedometer.wakeup_service}
    act=com.meizu.net.pedometer.wakeup_service flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #43: BroadcastRecord{bd025a8 u0 com.tencent.mm.TrafficStatsReceiver}
    act=com.tencent.mm.TrafficStatsReceiver flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #44: BroadcastRecord{b0968c1 u0 com.tencent.mobileqq:MSF_85207335}
    act=com.tencent.mobileqq:MSF_85207335 flg=0x14 (has extras)
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  #45: BroadcastRecord{7329866 u0 com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW}
    act=com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=92]
  #46: BroadcastRecord{fde27a7 u0 com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW}
    act=com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=92]
  #47: BroadcastRecord{3d5f454 u0 com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW}
    act=com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=92]
  #48: BroadcastRecord{f82cffd u0 com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW}
    act=com.qualcomm.intent.action.ACTION_UNSOL_RESPONSE_OEM_HOOK_RAW flg=0x10 (has extras)
    extras: Bundle[mParcelledData.dataSize=92]
  #49: BroadcastRecord{bfd4f2 u-1 android.intent.action.BATTERY_CHANGED}
    act=android.intent.action.BATTERY_CHANGED flg=0x60000010 (has extras)
    extras: Bundle[{technology=Li-ion, icon-small=17303284, max_charging_voltage=0, health=2, max_charging_current=0, status=2, plugged=2, present=true, charge_counter=0, level=98, scale=100, temperature=302, voltage=4409, invalid_charger=0}]
  #50: act=com.sina.smart.heartbeat.action1004 flg=0x14 (has extras)
    -17882d10h0m32s378ms dispatch +17882d10h0m32s379ms finish
    enq=2018-12-17 18:00:32 disp=1970-01-01 08:00:00 fin=2018-12-17 18:00:32
    extras: Bundle[{android.intent.extra.ALARM_COUNT=1}]
  ...

  Sticky broadcasts for user -1:
  * Sticky action android.intent.action.SERVICE_STATE
  * Sticky action android.net.wifi.WIFI_STATE_CHANGED
  * Sticky action android.net.wifi.p2p.CONNECTION_STATE_CHANGE
  * Sticky action android.net.conn.TETHER_STATE_CHANGED
  * Sticky action android.media.ACTION_SCO_AUDIO_STATE_UPDATED
  * Sticky action android.intent.action.NETWORK_SET_TIME
  * Sticky action android.net.wifi.p2p.THIS_DEVICE_CHANGED
  * Sticky action android.intent.action.BATTERY_CHANGED
  * Sticky action android.intent.action.ACTION_DEFAULT_SUBSCRIPTION_CHANGED
  * Sticky action android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED
  * Sticky action org.codeaurora.intent.action.ACTION_RADIO_CAPABILITY_UPDATED
  * Sticky action android.intent.action.NETWORK_SET_TIMEZONE
  * Sticky action android.net.conn.CONNECTIVITY_CHANGE
  * Sticky action android.telephony.action.CARRIER_CONFIG_CHANGED
  * Sticky action android.net.conn.CONNECTIVITY_CHANGE_SUPL
  * Sticky action android.intent.action.RADIO_TECHNOLOGY
  * Sticky action android.intent.action.DATA_CONNECTION_FAILED
  * Sticky action android.intent.action.ANY_DATA_STATE
  * Sticky action android.provider.Telephony.SPN_STRINGS_UPDATED
  * Sticky action android.net.nsd.STATE_CHANGED
  * Sticky action android.hardware.usb.action.USB_STATE
  * Sticky action android.net.wifi.RSSI_CHANGED
  * Sticky action android.net.wifi.STATE_CHANGE
  * Sticky action android.intent.action.SIM_STATE_CHANGED
  * Sticky action android.intent.action.SIG_STR
  * Sticky action android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED
  * Sticky action android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION
  * Sticky action android.net.wifi.supplicant.STATE_CHANGE
  * Sticky action android.net.wifi.p2p.PERSISTENT_GROUPS_CHANGED
  * Sticky action android.net.conn.INET_CONDITION_ACTION
  * Sticky action android.media.SCO_AUDIO_STATE_CHANGED
  * Sticky action android.os.UpdateLock.UPDATE_LOCK_CHANGED
  * Sticky action android.net.pppoe.PPPOE_STATE_CHANGED
  * Sticky action android.intent.action.internal_sim_state_changed
  * Sticky action android.media.MASTER_MUTE_CHANGED_ACTION
  * Sticky action wifi_scan_available
  * Sticky action android.net.wifi.p2p.STATE_CHANGED
  * Sticky action com.android.imscontection.DISCONNECTED
  * Sticky action android.intent.action.HDMI_PLUGGED
  * Sticky action android.media.RINGER_MODE_CHANGED

  Sticky broadcasts for user 0:
  * Sticky action com.android.music.playstatechanged
BroadcastPrefStrategy  
  mDisableBroadcasPref = false
    mPkgSuperWhiteList :
        packageName = com.tencent.mm
        packageName = com.tencent.mobileqq
    ExcludeAction :
        action = android.net.wifi.supplicant.STATE_CHANGE
        action = android.intent.action.CLOSE_SYSTEM_DIALOGS
        action = android.intent.action.TIME_TICK
        action = android.intent.action.PACKAGE_RESTARTED
        action = android.net.wifi.RSSI_CHANGED
        action = android.intent.action.SERVICE_STATE
        action = android.intent.action.CONFIGURATION_CHANGED
        action = android.intent.action.USER_PRESENT
    Action include pkg:
      Action:android.intent.action.PACKAGE_ADDED
        package:com.qihoo.cleandroid_cn
        package:com.xunlei.downloadprovider
        package:com.qihoo.appstore
        package:com.tencent.qqpim
        package:com.qihoo.browser
        package:com.cleanmaster.mguard_cn
        package:com.UCMobile
        package:com.eg.android.AlipayGphone
        package:com.baidu.browser.apps
        package:com.cleanmaster.security_cn
        package:com.kingroot.master
        package:com.estrongs.android.pop
        package:com.yandex.browser
        package:com.tencent.token
        package:com.aliyun.SecurityCenter
        package:sogou.mobile.explorer
        package:com.android.chrome
        package:com.jd.jrapp
        package:com.tencent.mtt
        package:com.tencent.qqpimsecure
        package:cn.opda.a.phonoalbumshoushou
        package:com.ludashi.benchmark
        package:com.qihoo360.mobilesafe
      Action:android.intent.action.BOOT_COMPLETED
        package:com.meizu.monitorphone
        package:com.youloft.calendar
        package:com.iflytek.inputmethod
        package:com.jb.gokeyboard
        package:com.tencent.androidqqmail
        package:com.meizu.activeviewlivewallpaper
        package:com.meizu.wifiadmin
        package:jp.naver.line.android
        package:com.vkei.wehelper.meizu
        package:com.android.cts.permissiondeclareappcompat
        package:com.netease.mobimail
        package:com.meizu.mstore
        package:com.alibaba.android.babylon
        package:android.accounts.cts.unaffiliated
        package:com.android.cts.launchertests.support
        package:com.baidu.input
        package:com.meizu.flyme.input
        package:com.android.cts.certinstaller
        package:com.tencent.qqpinyin
        package:com.meizu.health
        package:com.zq.powerdoctor
        package:com.android.cts.vpnfirewall
        package:com.google.android.apps.inputmethod.zhuyin
        package:com.meizu.monkey.errorreport
        package:com.meizu.location
        package:com.unionpay.tsmunifiedbleservice
        package:com.meizu.datamigration
        package:com.android.cts.intent.receiver
        package:com.tencent.mobileqqi
        package:com.facebook.katana
        package:com.meizu.logreport
        package:com.tencent.qqpimsecure
        package:com.android.cts.deviceowner
        package:com.alibaba.android.rimet
        package:lb.app.mx2home
        package:com.qihoo360.mobilesafe
        package:com.whatsapp
        package:com.greenpoint.android.mc10086.activity
        package:com.android.cts.deviceandprofileowner
        package:com.android.cts.widgetprovider
        package:com.eg.android.AlipayGphone
        package:com.meizu.lifekit
        package:com.baidu.BaiduMap.meizu
        package:android.jobscheduler.cts
        package:com.stabilitytest.sysmonkey
        package:com.meizu.flyme.redmine
        package:com.alibaba.android.rimet.fx
        package:im.yixin
        package:com.skype.rover
        package:com.meizu.desktopbackup
        package:com.tomato.inputmethod.pinyin
        package:com.meizu.watch
        package:android.deviceadmin.cts
        package:com.meizu.monkeytest
        package:com.sina.weibo
        package:com.android.cts.robot
        package:com.syntellia.fleksy.keyboard
        package:com.android.cts.permissiondeclareapp
        package:com.tencent.qqlite
        package:com.android.cts.managedprofile
        package:com.meizu.flyme.flymebbs
        package:com.google.android.apps.inputmethod.pinyin
        package:com.immomo.momo
        package:com.sohu.inputmethod.sogou
        package:com.bingime.ime
        package:com.android.cts.net.hostside.app2
        package:com.google.android.gm
        package:com.uxinsdk
        package:com.meizu.luckymoney
        package:com.meizu.net.pedometer
        package:com.cootek.smartinputv5
        package:com.android.cts.devicepolicy.accountmanagement
        package:com.renren.mobile.android
      Action:android.intent.action.PACKAGE_CHANGED
        package:com.qihoo.cleandroid_cn
        package:com.xunlei.downloadprovider
        package:com.tencent.qqpim
        package:com.qihoo.browser
        package:com.cleanmaster.mguard_cn
        package:com.UCMobile
        package:com.eg.android.AlipayGphone
        package:com.baidu.browser.apps
        package:com.cleanmaster.security_cn
        package:com.kingroot.master
        package:com.estrongs.android.pop
        package:com.yandex.browser
        package:com.tencent.token
        package:com.aliyun.SecurityCenter
        package:sogou.mobile.explorer
        package:com.android.chrome
        package:com.jd.jrapp
        package:com.tencent.mtt
        package:com.tencent.qqpimsecure
        package:cn.opda.a.phonoalbumshoushou
        package:com.ludashi.benchmark
        package:com.qihoo360.mobilesafe
      Action:android.intent.action.PACKAGE_REMOVED
        package:com.qihoo.cleandroid_cn
        package:com.xunlei.downloadprovider
        package:com.tencent.qqpim
        package:com.qihoo.browser
        package:com.cleanmaster.mguard_cn
        package:com.UCMobile
        package:com.eg.android.AlipayGphone
        package:com.baidu.browser.apps
        package:com.cleanmaster.security_cn
        package:com.kingroot.master
        package:com.estrongs.android.pop
        package:com.yandex.browser
        package:com.tencent.token
        package:com.aliyun.SecurityCenter
        package:sogou.mobile.explorer
        package:com.android.chrome
        package:com.jd.jrapp
        package:com.tencent.mtt
        package:com.tencent.qqpimsecure
        package:cn.opda.a.phonoalbumshoushou
        package:com.ludashi.benchmark
        package:com.qihoo360.mobilesafe
      Action:android.intent.action.BATTERY_CHANGED
        package:com.ijinshan.kbatterydoctor
        package:com.qihoo.cleandroid_cn
        package:com.cleanmaster.mguard_cn
        package:com.cleanmaster.security_cn
        package:com.kingroot.master
        package:com.lionmobi.battery
        package:imoblife.batterybooster
        package:com.tencent.token
        package:com.dianxinos.dxbs
        package:com.vkei.vservice.meizu
        package:ccc71.bmw.pro
        package:net.lepeng.batterydoctor
        package:com.tencent.qqpimsecure
        package:cn.opda.a.phonoalbumshoushou
        package:com.ludashi.benchmark
        package:com.qihoo360.mobilesafe

ACTIVITY MANAGER CONTENT PROVIDERS (dumpsys activity providers)
  Published single-user content providers (by class):
  * ContentProviderRecord{341104a u0 com.android.providers.telephony/.TelephonyProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    singleton=true
    authority=telephony
  * ContentProviderRecord{8e6abbb u0 com.android.providers.settings/.SettingsProvider}
    proc=ProcessRecord{7c6d553 1738:system/1000}
    singleton=true
    authority=settings
    54 connections, 0 external handles
      -> 2048:com.meizu.facerecognition/1000 s1/1 u0/0 +1d20h12m17s412ms
      -> 2053:com.android.systemui/u0a31 s1/1 u0/0 +1d20h12m17s239ms
      -> 2217:com.android.systemui:recents/u0a31 s1/1 u0/0 +1d20h12m16s880ms
      -> 2348:com.flyme.telecom.usagedata.service/1001 s1/1 u0/0 +1d20h12m16s387ms
      -> 2316:.dataservices/1000 s1/1 u0/0 +1d20h12m16s378ms
      -> 2332:com.android.phone/1001 s1/1 u0/0 +1d20h12m16s341ms
      -> 2520:com.qualcomm.qcrilmsgtunnel/1001 s1/1 u0/0 +1d20h12m15s371ms
      -> 2723:android.ext.services/u0a9 s1/1 u0/0 +1d20h12m11s947ms
      -> 2708:com.flyme.systemuitools/u0a31 s1/1 u0/0 +1d20h12m11s921ms
      -> 2838:com.baidu.input/u0a362 s1/1 u0/0 +1d20h12m11s200ms
      -> 3051:com.oma.drm.server/u0a58 s1/1 u0/0 +1d20h12m11s148ms
      -> 3023:com.qualcomm.qti.loadcarrier/1001 s1/1 u0/0 +1d20h12m11s147ms
      -> 3067:com.meizu.experiencedatasync/1000 s1/1 u0/0 +1d20h12m11s138ms
      -> 3009:com.android.incallui/u0a3 s1/1 u0/0 +1d20h12m11s68ms
      -> 3038:com.meizu.alphame/1000 s1/1 u0/0 +1d20h12m11s30ms
      -> 3137:com.meizu.flyme.launcher/u0a11 s1/1 u0/0 +1d20h12m10s800ms
      -> 3459:com.meizu.pps/1000 s1/1 u0/0 +1d20h12m7s385ms
      -> 3651:com.meizu.location/u0a16 s1/1 u0/0 +1d20h12m5s263ms
      -> 6133:com.android.providers.downloads/u0a12 s1/1 u0/0 +1d20h11m43s546ms
      -> 7329:com.meizu.account/u0a52 s1/1 u0/0 +1d3h4m40s470ms
      -> 24067:com.meizu.dataservice/1000 s1/1 u0/0 +23h55m14s288ms
      -> 30279:com.android.bluetooth/1002 s1/1 u0/0 +23h21m32s743ms
      -> 30354:com.meizu.share/u0a56 s1/1 u0/0 +23h21m31s983ms
      -> 16174:com.tencent.mm:push/u0a108 s1/1 u0/0 +8h50m47s694ms
      -> 16233:com.tencent.mm/u0a108 s1/1 u0/0 +8h50m39s813ms
      -> 16300:com.tencent.mm:exdevice/u0a108 s1/1 u0/0 +8h50m38s220ms
      -> 2265:com.ss.android.article.news:push/u0a103 s1/1 u0/0 +2h22m40s260ms
      -> 10805:com.ss.android.article.news/u0a103 s1/1 u0/0 +1h13m32s300ms
      -> 30463:com.android.settings/1000 s1/1 u0/0 +22m25s789ms
      -> 30521:com.meizu.mzsyncservice/u0a88 s1/1 u0/0 +22m19s819ms
      -> 30941:com.meizu.flyme.update/1000 s1/1 u0/0 +22m10s367ms
      -> 30975:com.qualcomm.telephony/1000 s1/1 u0/0 +22m9s896ms
      -> 31089:com.android.printspooler/u0a64 s1/1 u0/0 +22m2s195ms
      -> 31201:com.ss.android.article.news:miniapp0/u0a103 s1/1 u0/0 +21m59s804ms
      -> 31263:com.meizu.flyme.service.find/u0a54 s1/1 u0/0 +21m58s186ms
      -> 31367:com.meizu.safe/1000 s1/1 u0/0 +21m54s769ms
      -> 32215:com.iflytek.speechsuite/u0a51 s1/1 u0/0 +16m59s781ms
      -> 32513:com.tencent.mobileqq:MSF/u0a112 s1/1 u0/0 +16m36s956ms
      -> 32571:com.tencent.mobileqq/u0a112 s1/1 u0/0 +16m36s760ms
      -> 1110:com.meizu.battery/1000 s1/1 u0/0 +16m25s856ms
      -> 1208:com.meizu.net.pedometer/u0a89 s1/1 u0/0 +16m15s860ms
      -> 1231:com.meizu.cloud:mzservice_v1/u0a49 s1/1 u0/0 +16m5s905ms
      -> 1296:com.qualcomm.qti.appnetaccess/1000 s1/1 u0/0 +15m55s858ms
      -> 1489:com.meizu.cloud/u0a49 s1/1 u0/0 +15m44s855ms
      -> 1719:com.meizu.cloud:mz_tracker/u0a49 s1/1 u0/0 +10m28s40ms
      -> 2133:com.android.mms/u0a26 s1/1 u0/0 +3m28s778ms
      -> 2161:com.meizu.mstore/u0a40 s1/1 u0/0 +3m28s281ms
      -> 2216:com.meizu.net.search/u0a68 s1/1 u0/0 +3m28s85ms
      -> 2344:com.meizu.privacy/1338 s1/1 u0/0 +3m27s869ms
      -> 2454:com.meizu.netcontactservice/u0a8 s1/1 u0/0 +3m27s191ms
      -> 2646:com.sina.weibo/u0a131 s1/1 u0/0 +3m23s617ms
      -> 2726:com.sina.weibo:remote/u0a131 s1/1 u0/0 +3m22s639ms
      -> 2888:android.process.media/u0a12 s1/1 u0/0 +3m17s905ms
      -> 2923:com.meizu.filemanager/u0a48 s1/1 u0/0 +3m12s738ms
  * ContentProviderRecord{2c48d8 u0 com.android.providers.telephony/.HbpcdLookupProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    singleton=true
    authority=hbpcd_lookup
  * ContentProviderRecord{1d0431 u0 com.android.providers.settings/.ShareListProvider}
    proc=ProcessRecord{7c6d553 1738:system/1000}
    singleton=true
    authority=meizu_share
  * ContentProviderRecord{287e716 u0 com.android.providers.telephony/.SmsProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    singleton=true
    authority=sms
  * ContentProviderRecord{d4a4897 u0 android/com.android.server.am.DumpHeapProvider}
    proc=ProcessRecord{7c6d553 1738:system/1000}
    singleton=true
    authority=com.android.server.heapdump
  * ContentProviderRecord{6891a84 u0 com.android.providers.telephony/.MmsProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    singleton=true
    authority=mms
  * ContentProviderRecord{48cb26d u0 com.android.server.telecom/com.meizu.server.telecom.SwitchModeNotifyProvider}
    proc=ProcessRecord{7c6d553 1738:system/1000}
    singleton=true
    authority=com.meizu.server.telecom.SwitchModeNotifyProvider
  * ContentProviderRecord{b935ea2 u0 com.android.providers.telephony/.MmsSmsProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    singleton=true
    authority=mms-sms

  Published user 0 content providers (by class):
  * ContentProviderRecord{7e0d733 u0 com.ss.android.article.news/android.support.v4.content.FileProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}
    authority=com.ss.android.uri.key
  * ContentProviderRecord{bf5af0 u0 com.tencent.mm/.plugin.base.stub.WXCommProvider}
    proc=ProcessRecord{ebdbca 16233:com.tencent.mm/u0a108}
    authority=com.tencent.mm.sdk.comm.provider
  * ContentProviderRecord{4cf0869 u0 com.qualcomm.qti.appnetaccess/.AppNetAccessProvider}
    proc=ProcessRecord{aecc2ee 1296:com.qualcomm.qti.appnetaccess/1000}
    authority=com.qualcomm.qti.appnetaccess.AppNetAccessProvider
  * ContentProviderRecord{c47b38f u0 com.ss.android.article.news/com.tt.slog.SLogProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}
    authority=com.ss.android.article.news.slog.provider
  * ContentProviderRecord{2d7361c u0 com.meizu.flyme.launcher/.LauncherProvider}
    proc=ProcessRecord{ae7c33e 3137:com.meizu.flyme.launcher/u0a11}
    authority=com.meizu.flyme.launcher.settings
  * ContentProviderRecord{dc6c225 u0 com.meizu.netcontactservice/.geo.GeoFilesDirectoryProvider}
    proc=ProcessRecord{6d63095 2454:com.meizu.netcontactservice/u0a8}
    authority=com.meizu.netcontactservice.geofiles.directory
  * ContentProviderRecord{a661ffa u0 com.meizu.pps/.provider.PPSProvider}
    proc=ProcessRecord{95f9ab 3459:com.meizu.pps/1000}
    authority=com.meizu.pps.provider
  * ContentProviderRecord{72f9808 u0 com.android.settings/com.meizu.settings.search.FlymeGlobalSearchProvider}
    proc=ProcessRecord{9adc0ab 30463:com.android.settings/1000}
    authority=com.meizu.settings.global_search
  * ContentProviderRecord{dc65ba1 u0 com.ss.android.article.news/com.ss.android.account.share.provider.AccountShareProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}
    authority=com.ss.android.account.share.provider.tt
  * ContentProviderRecord{ce641c6 u0 com.meizu.mzsyncservice/com.meizu.sync.db.SyncSettingProvider}
    proc=ProcessRecord{b388587 30521:com.meizu.mzsyncservice/u0a88}
    authority=com.meizu.sync.settings
  * ContentProviderRecord{d2a2cb4 u0 com.tencent.mm/.plugin.ext.provider.ExtControlProviderEntry}
    proc=ProcessRecord{ebdbca 16233:com.tencent.mm/u0a108}
    authority=com.tencent.mm.plugin.ext.entry
  * ContentProviderRecord{bcc10dd u0 com.meizu.safe/.blockService.provider.BlockResultProvider}
    proc=ProcessRecord{52dba64 31367:com.meizu.safe/1000}
    authority=blockresult
  * ContentProviderRecord{af4b452 u0 com.meizu.flyme.service.find/.provider.DataProvider}
    proc=ProcessRecord{28df323 31263:com.meizu.flyme.service.find/u0a54}
    authority=com.meizu.flyme.service.find
  * ContentProviderRecord{6976020 u0 com.tencent.mm/.plugin.gwallet.GWalletQueryProvider}
    proc=ProcessRecord{ebdbca 16233:com.tencent.mm/u0a108}
    authority=com.tencent.mm.plugin.gwallet.queryprovider
  * ContentProviderRecord{67dddd9 u0 com.flyme.systemuitools/.gameassistant.model.GameSDKContentProvider}
    proc=ProcessRecord{c05c39e 2708:com.flyme.systemuitools/u0a31}
    authority=com.flyme.systemuitools.gameassistant.model.GameSDKContentProvider
  * ContentProviderRecord{a429e7f u0 com.android.providers.downloads/.DownloadProvider}
    proc=ProcessRecord{fb3bdb1 6133:com.android.providers.downloads/u0a12}
    launchingApp=ProcessRecord{fb3bdb1 6133:com.android.providers.downloads/u0a12}
    authority=downloads
    1 connections, 0 external handles
      -> 3051:com.oma.drm.server/u0a58 s1/1 u0/11 +1d20h11m38s430ms
  * ContentProviderRecord{fe25e4c u0 com.tencent.mobileqq/cooperation.readinjoy.content.ReadInJoyDataProvider}
    proc=ProcessRecord{a1d2ac3 32571:com.tencent.mobileqq/u0a112}
    authority=qq.readinjoy
  * ContentProviderRecord{6e57e95 u0 com.baidu.input/.layout.share.FileProvider}
    proc=ProcessRecord{9e0f98 2838:com.baidu.input/u0a362}
    authority=com.baidu.input
  * ContentProviderRecord{8867baa u0 com.sina.weibo/.provider.WeiboDetailProvider}
    proc=ProcessRecord{2dbe127 2646:com.sina.weibo/u0a131}
    authority=com.sina.weibo.weibodetail
  * ContentProviderRecord{a6ca39b u0 com.ss.android.article.news/com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}
    authority=com.ss.android.article.news.WsChannelMultiProcessSharedProvider
  * ContentProviderRecord{efd1338 u0 com.android.phone/.MzIccProvider}
    proc=ProcessRecord{5812e6a 2332:com.android.phone/1001}
    authority=icc
  * ContentProviderRecord{2ec6f11 u0 com.tencent.mobileqq/com.tencent.common.config.provider.QZoneConfigProvider}
    proc=ProcessRecord{a1d2ac3 32571:com.tencent.mobileqq/u0a112}
    authority=com.tencent.common.config.provider.QZoneConfigProvider
  * ContentProviderRecord{a8a876 u0 com.meizu.safe/.blockService.provider.BlackListProvider}
    proc=ProcessRecord{52dba64 31367:com.meizu.safe/1000}
    authority=blackList
  * ContentProviderRecord{c67de77 u0 com.meizu.mstore/android.support.v4.content.FileProvider}
    proc=ProcessRecord{331ed60 2161:com.meizu.mstore/u0a40}
    authority=com.meizu.mstore.fileprovider
  * ContentProviderRecord{50c2ae4 u0 com.meizu.mstore/com.meizu.cloud.app.update.exclude.AppUpdateExcludeProvider}
    proc=ProcessRecord{331ed60 2161:com.meizu.mstore/u0a40}
    authority=com.meizu.cloud.app.update.exclude
  * ContentProviderRecord{b97eb4d u0 com.meizu.safe/.alphame.SmartBGProvider}
    proc=ProcessRecord{52dba64 31367:com.meizu.safe/1000}
    authority=com.meizu.safe.smart_bg
  * ContentProviderRecord{3eed602 u0 com.iflytek.speechsuite/com.iflytek.speechcloud.providers.SettingsProvider}
    proc=ProcessRecord{971eb13 32215:com.iflytek.speechsuite/u0a51}
    authority=com.iflytek.speechsuite.providers.SettingsProvider
  * ContentProviderRecord{ed31150 u0 com.tencent.mm/.plugin.ext.provider.ExtControlProviderNearBy}
    proc=ProcessRecord{ebdbca 16233:com.tencent.mm/u0a108}
    authority=com.tencent.mm.plugin.ext.NearBy
  * ContentProviderRecord{304ef49 u0 com.sina.weibo/android.support.v4.content.FileProvider}
    proc=ProcessRecord{2dbe127 2646:com.sina.weibo/u0a131}
    authority=com.sina.weibo.fileprovider
  * ContentProviderRecord{578504e u0 com.meizu.safe/.blockService.provider.BlockMessagesProvider}
    proc=ProcessRecord{52dba64 31367:com.meizu.safe/1000}
    authority=blockmessage
  * ContentProviderRecord{706256f u0 com.ss.android.article.news/com.ss.android.downloadlib.core.newmedia.downloads.DownloadDatabaseProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}
    authority=com.ss.android.article.news.newmedia.downloads
  * ContentProviderRecord{fdff27c u0 com.ss.android.article.news/com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider}
    proc=ProcessRecord{f5bfef2 10805:com.ss.android.article.news/u0a103}

    mResumedActivity: ActivityRecord{aad8262 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.CategoryActivity t11866}

  Stack #0:
  mFullscreen=true
  mBounds=null
    Task id #11741
    mFullscreen=true
    mBounds=null
    mMinWidth=-1
    mMinHeight=-1
    mLastNonFullscreenBounds=null
      TaskRecord{cfb60a6 #11741 A=com.meizu.flyme.launcher U=0 StackId=0 sz=1}
      Intent { act=android.intent.action.MAIN cat=[android.intent.category.HOME] flg=0x10800100 cmp=com.meizu.flyme.launcher/.Launcher }
        Hist #0: ActivityRecord{254bd08 u0 com.meizu.flyme.launcher/.Launcher t11741}
          Intent { act=android.intent.action.MAIN cat=[android.intent.category.HOME] flg=0x10800100 cmp=com.meizu.flyme.launcher/.Launcher }
          ProcessRecord{ae7c33e 3137:com.meizu.flyme.launcher/u0a11}

    Running activities (most recent first):
      TaskRecord{cfb60a6 #11741 A=com.meizu.flyme.launcher U=0 StackId=0 sz=1}
        Run #0: ActivityRecord{254bd08 u0 com.meizu.flyme.launcher/.Launcher t11741}

  mFocusedActivity: ActivityRecord{aad8262 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.CategoryActivity t11866}
  mFocusedStack=com.android.server.am.ActivityStack{38ea2db stackId=1, 15 tasks} mLastFocusedStack=com.android.server.am.ActivityStack{38ea2db stackId=1, 15 tasks}
  mSleepTimeout=false
  mCurTaskIdForUser={0=11866}
  mUserStackInFront={}
  mActivityContainers={0=ActivtyContainer{0}A, 1=ActivtyContainer{1}A}
  mLockTaskModeState=NONE mLockTaskPackages (userId:packages)=
    0:[]
 mLockTaskModeTasks[]
{}

ACTIVITY MANAGER RUNNING PROCESSES (dumpsys activity processes)
  Isolated process list (sorted by uid):
    Isolated # 0: ProcessRecord{9bc4178 2043:WebViewLoader-arm64-v8a/1037}

  UID states:
    UID 1000: UidRecord{beaa051 1000 P  procs:13}
    UID 1001: UidRecord{f6520b6 1001 P  procs:4}
    UID 1002: UidRecord{ca91b7 1002 IF procs:1}
    UID 1037: UidRecord{92ffd24 1037 CE bg:+1d20h12m17s594ms idle procs:2}
    UID 1338: UidRecord{667f08d 1338 CE bg:+3m27s922ms idle procs:1}
    UID u0a3: UidRecord{f9a9242 u0a3 P  procs:1}
    UID u0a8: UidRecord{e6c9253 u0a8 CE bg:+3m27s242ms idle procs:1}
    UID u0a9: UidRecord{801c790 u0a9 SB procs:1}
    UID u0a11: UidRecord{cf00889 u0a11 HO bg:+3m12s288ms idle procs:1}
    UID u0a12: UidRecord{7c3908e u0a12 SB procs:2}
    UID u0a16: UidRecord{2400af u0a16 IB procs:1}
    UID u0a26: UidRecord{de3ccbc u0a26 CE bg:+3m29s88ms idle procs:1}
    UID u0a31: UidRecord{7c7a445 u0a31 P  procs:3}
    UID u0a40: UidRecord{66c279a u0a40 CE bg:+3m24s26ms idle procs:1}
    UID u0a48: UidRecord{b39f8cb u0a48 T  procs:1}
    UID u0a49: UidRecord{97ef8a8 u0a49 S  bg:+22m39s918ms idle procs:3}
    UID u0a51: UidRecord{4c33fc1 u0a51 S  bg:+16m59s823ms idle procs:1}
    UID u0a52: UidRecord{4d52366 u0a52 IF procs:1}
    UID u0a54: UidRecord{66d56a7 u0a54 S  bg:+21m58s230ms idle procs:1}
    UID u0a56: UidRecord{91ef754 u0a56 IF procs:1}
    UID u0a58: UidRecord{8316fd u0a58 P  procs:1}
    UID u0a64: UidRecord{d800ff2 u0a64 CE bg:+21m57s66ms idle procs:1}
    UID u0a68: UidRecord{b8eb643 u0a68 CE bg:+3m28s181ms idle procs:1}
    UID u0a88: UidRecord{21e34c0 u0a88 S  bg:+22m19s937ms idle procs:1}
    UID u0a89: UidRecord{76f25f9 u0a89 S  bg:+16m15s977ms idle procs:1}
    UID u0a103: UidRecord{f7b393e u0a103 S  bg:+16m36s902ms idle procs:3}
    UID u0a108: UidRecord{6dc739f u0a108 HW bg:+1h26m29s983ms idle procs:3}
    UID u0a112: UidRecord{311dcec u0a112 S  bg:+40s165ms procs:2}
    UID u0a131: UidRecord{a7328b5 u0a131 S  bg:+3m19s192ms idle procs:2}
    UID u0a362: UidRecord{a6dab4a u0a362 IB procs:1}

  UID validation:
    UID 1001: UidRecord{1eaabb 1001 P  procs:0}
    UID 1002: UidRecord{cb5dbd8 1002 IF procs:0}
    UID 1037: UidRecord{37a9b31 1037 CE idle procs:0}
    UID 1338: UidRecord{f633216 1338 CE idle procs:0}
    UID u0a3: UidRecord{9833797 u0a3 P  procs:0}
    UID u0a8: UidRecord{d98dd84 u0a8 CE idle procs:0}
    UID u0a9: UidRecord{5acb96d u0a9 SB procs:0}
    UID u0a11: UidRecord{25859a2 u0a11 HO idle procs:0}
    UID u0a12: UidRecord{39b633 u0a12 SB procs:0}
    UID u0a16: UidRecord{c884df0 u0a16 IB procs:0}
    UID u0a26: UidRecord{5687f69 u0a26 CE idle procs:0}
    UID u0a31: UidRecord{8866dee u0a31 P  procs:0}
    UID u0a40: UidRecord{acf828f u0a40 CE idle procs:0}
    UID u0a48: UidRecord{dbc591c u0a48 T  procs:0}
    UID u0a49: UidRecord{400a925 u0a49 S  idle procs:0}
    UID u0a51: UidRecord{f6f7afa u0a51 S  idle procs:0}
    UID u0a52: UidRecord{14bb8ab u0a52 IF procs:0}
    UID u0a54: UidRecord{ec3eb08 u0a54 S  idle procs:0}
    UID u0a56: UidRecord{b37b2a1 u0a56 IF procs:0}
    UID u0a58: UidRecord{8aa4cc6 u0a58 P  procs:0}
    UID u0a64: UidRecord{b0b3487 u0a64 CE idle procs:0}
    UID u0a68: UidRecord{530afb4 u0a68 CE idle procs:0}
    UID u0a88: UidRecord{47bd7dd u0a88 S  idle procs:0}
    UID u0a89: UidRecord{66e6f52 u0a89 S  idle procs:0}
    UID u0a103: UidRecord{e5c9223 u0a103 S  idle procs:0}
    UID u0a108: UidRecord{c031320 u0a108 HW idle procs:0}
    UID u0a112: UidRecord{7e314d9 u0a112 S  procs:0}
    UID u0a131: UidRecord{4e02e9e u0a131 S  idle procs:0}
    UID u0a362: UidRecord{ddc2d7f u0a362 IB procs:0}

  Process LRU list (sorted by oom_adj, 55 total, non-act at 8, non-svc at 8):
    PERS #54: sys   F/ /P  trm: 0 1738:system/1000 (fixed)
    PERS #53: pers  F/ /P  trm: 0 2048:com.meizu.facerecognition/1000 (fixed)
    PERS #52: pers  F/ /P  trm: 0 2053:com.android.systemui/u0a31 (fixed)
    PERS #50: pers  F/ /P  trm: 0 2316:.dataservices/1000 (fixed)
    PERS #49: pers  F/ /P  trm: 0 2332:com.android.phone/1001 (fixed)
    PERS #48: pers  F/ /P  trm: 0 2348:com.flyme.telecom.usagedata.service/1001 (fixed)
    PERS #46: pers  F/ /P  trm: 0 3009:com.android.incallui/u0a3 (fixed)
    PERS #45: pers  F/ /P  trm: 0 3023:com.qualcomm.qti.loadcarrier/1001 (fixed)
    PERS #44: pers  F/ /P  trm: 0 3038:com.meizu.alphame/1000 (fixed)
    PERS #43: pers  F/ /P  trm: 0 3051:com.oma.drm.server/u0a58 (fixed)
    PERS #42: pers  F/ /P  trm: 0 3067:com.meizu.experiencedatasync/1000 (fixed)
    Proc #37: psvc  T/ /IF trm: 0 30279:com.android.bluetooth/1002 (service)
        com.android.bluetooth/.btservice.AdapterService<=Proc{1738:system/1000}
    Proc # 0: fore  ?/A/T  trm: 0 2923:com.meizu.filemanager/u0a48 (top-activity)
    Proc #34: fore  F/ /SB trm: 0 6133:com.android.providers.downloads/u0a12 (provider)
        com.android.providers.downloads/.DownloadProvider<=Proc{3051:com.oma.drm.server/u0a58}
    Proc #47: vis   F/ /SB trm: 0 2723:android.ext.services/u0a9 (service)
        android.ext.services/.notification.Ranker<=Proc{1738:system/1000}
    Proc #23: vis   T/S/SF trm: 0 2708:com.flyme.systemuitools/u0a31 (service)
        com.flyme.systemuitools/com.meizu.notification.FlymeNotificationFilterService<=Proc{2053:com.android.systemui/u0a31}
    PERS #51: vis   F/ /IF trm: 0 2217:com.android.systemui:recents/u0a31 (service)
        com.android.systemui/com.flyme.systemui.recents.FlymeRecentsService<=Proc{2053:com.android.systemui/u0a31}
    Proc #41: vis   F/ /IF trm: 0 3459:com.meizu.pps/1000 (service)
        com.meizu.pps/.SystemEventService<=Proc{1738:system/1000}
    Proc #40: vis   F/ /IF trm: 0 2520:com.qualcomm.qcrilmsgtunnel/1001 (service)
        com.qualcomm.qcrilmsgtunnel/.QcrilMsgTunnelService<=Proc{2332:com.android.phone/1001}
    Proc #39: vis   T/ /IF trm: 0 7329:com.meizu.account/u0a52 (service)
        com.meizu.account/.core.internal.oauth.AndroidAccountService<=Proc{1738:system/1000}
    Proc #38: vis   T/ /IF trm: 0 30354:com.meizu.share/u0a56 (service)
        com.meizu.share/.ShareService<=Proc{30279:com.android.bluetooth/1002}
    Proc #19: vis   F/ /IF trm: 0 24067:com.meizu.dataservice/1000 (service)
        com.meizu.dataservice/.uxip.VccOfflineStatsService<=Proc{30354:com.meizu.share/u0a56}
    Proc #36: prcp  B/ /IB trm: 0 3651:com.meizu.location/u0a16 (service)
        com.meizu.location/.GeocodeService<=Proc{1738:system/1000}
    Proc # 6: prcp  B/ /IB trm: 0 2838:com.baidu.input/u0a362 (service)
        com.baidu.input/.ImeService<=Proc{1738:system/1000}
    Proc #35: hvy   B/ /HW trm: 0 16174:com.tencent.mm:push/u0a108 (service)
        com.tencent.mm/.booter.CoreService<=Proc{16233:com.tencent.mm/u0a108}
    Proc # 7: hvy   B/ /HW trm: 0 16300:com.tencent.mm:exdevice/u0a108 (service)
        com.tencent.mm/.plugin.sport.service.SportService<=Proc{16233:com.tencent.mm/u0a108}
    Proc # 5: hvy   B/ /HW trm: 0 16233:com.tencent.mm/u0a108 (alphame)
    Proc #27: svc   B/ /S  trm: 0 32571:com.tencent.mobileqq/u0a112 (started-services)
    Proc #16: svc   B/ /S  trm: 0 2726:com.sina.weibo:remote/u0a131 (started-services)
    Proc #15: svc   B/ /S  trm: 0 2646:com.sina.weibo/u0a131 (started-services)
    Proc #11: svc   B/ /S  trm: 0 32513:com.tencent.mobileqq:MSF/u0a112 (started-services)
    Proc #10: svc   B/ /S  trm: 0 1208:com.meizu.net.pedometer/u0a89 (started-services)
    Proc # 8: svc   B/ /S  trm: 0 1489:com.meizu.cloud/u0a49 (started-services)
    Proc # 1: home  B/ /HO trm: 0 3137:com.meizu.flyme.launcher/u0a11 (home)
    Proc # 4: prev  B/ /S  trm: 0 10805:com.ss.android.article.news/u0a103 (provider)
    Proc #33: svcb  T/ /S  trm: 0 30521:com.meizu.mzsyncservice/u0a88 (started-services)
    Proc #32: svcb  T/ /S  trm: 0 30975:com.qualcomm.telephony/1000 (started-services)
    Proc #31: svcb  T/ /S  trm: 0 31201:com.ss.android.article.news:miniapp0/u0a103 (started-services)
    Proc #30: svcb  T/ /S  trm: 0 31263:com.meizu.flyme.service.find/u0a54 (started-services)
    Proc #28: svcb  T/ /S  trm: 0 32215:com.iflytek.speechsuite/u0a51 (started-services)
    Proc #26: svcb  T/ /S  trm: 0 1110:com.meizu.battery/1000 (started-services)
    Proc #25: svcb  T/ /S  trm: 0 1231:com.meizu.cloud:mzservice_v1/u0a49 (started-services)
    Proc #24: svcb  T/ /S  trm: 0 1296:com.qualcomm.qti.appnetaccess/1000 (started-services)
    Proc #20: svcb  T/ /S  trm: 0 31367:com.meizu.safe/1000 (started-services)
    Proc # 3: cch   B/ /S  trm: 0 2265:com.ss.android.article.news:push/u0a103 (cch-bound-ui-services)
        com.ss.android.article.news/com.bytedance.common.wschannel.server.WsChannelService<=Proc{10805:com.ss.android.article.news/u0a103}
    Proc # 9: cch   B/ /CE trm: 0 1719:com.meizu.cloud:mz_tracker/u0a49 (cch-empty)
    Proc # 2: cch   B/ /CE trm: 0 30941:com.meizu.flyme.update/1000 (cch-empty)
    Proc #14: cch+2 B/ /CE trm: 0 2888:android.process.media/u0a12 (cch-empty)
    Proc #13: cch+2 B/ /CE trm: 0 30463:com.android.settings/1000 (cch-empty)
    Proc #12: cch+2 B/ /CE trm: 0 2161:com.meizu.mstore/u0a40 (cch-empty)
    Proc #21: cch+4 B/ /CE trm: 0 2344:com.meizu.privacy/1338 (cch-empty)
    Proc #18: cch+4 B/ /CE trm: 0 2454:com.meizu.netcontactservice/u0a8 (cch-empty)
    Proc #17: cch+4 B/ /CE trm: 0 2133:com.android.mms/u0a26 (cch-empty)
    Proc #29: cch+6 B/ /CE trm: 0 31089:com.android.printspooler/u0a64 (cch-empty)
    Proc #22: cch+6 B/ /CE trm: 0 2216:com.meizu.net.search/u0a68 (cch-empty)

  mStartedUsers:
    User #0: state=RUNNING_UNLOCKED
  mStartedUserArray: [0]
  mUserLru: [0]
  mHomeProcess: ProcessRecord{ae7c33e 3137:com.meizu.flyme.launcher/u0a11}
  mPreviousProcess: ProcessRecord{9adc0ab 30463:com.android.settings/1000}
  mConfiguration: {1.0 460mcc11mnc [zh_CN] ldltr sw360dp w360dp h618dp 480dpi nrml long port finger -keyb/v/h -nav/h s.22}
  mWakefulness=Awake
  mSleepTokens=[]
  mSleeping=false mLockScreenShown=LOCK_SCREEN_HIDDEN
  mShuttingDown=false mTestPssMode=false


```


## adb shell dumpsys activity top 全字符
```

TASK com.meizu.filemanager id=11866
  ACTIVITY com.meizu.filemanager/com.meizu.flyme.filemanager.activity.CategoryActivity aad8262 pid=2923
    Local FragmentActivity bd09ad4 State:
      mCreated=truemResumed=true mStopped=false mReallyStopped=false
      mLoadersStarted=true
    Active Fragments in 14975ae:
      #0: ad{10cf635 #0 id=0x7f1100a9}
        mFragmentId=#7f1100a9 mContainerId=#7f1100a9 mTag=null
        mState=5 mIndex=0 mWho=android:fragment:0 mBackStackNesting=2
        mAdded=true mRemoving=false mFromLayout=false mInLayout=false
        mHidden=true mDetached=false mMenuVisible=true mHasMenu=true
        mRetainInstance=false mRetaining=false mUserVisibleHint=true
        mFragmentManager=FragmentManager{14975ae in a{b64f4f}}
        mHost=android.support.v4.app.FragmentActivity$a@b64f4f
        mArguments=Bundle[{category_type=2}]
        mContainer=android.widget.FrameLayout{fe406dc V.E...... ........ 0,210-1080,1920 #7f1100a9 app:id/content_frame}
        mView=android.widget.FrameLayout{85623e5 G.E...... ......ID 0,0-1080,1710}
        mInnerView=android.widget.FrameLayout{85623e5 G.E...... ......ID 0,0-1080,1710}
        Child FragmentManager{3bb3eba in ad{10cf635}}:
          FragmentManager misc state:
            mHost=android.support.v4.app.FragmentActivity$a@b64f4f
            mContainer=android.support.v4.app.v$2@1b2916b
            mParent=ad{10cf635 #0 id=0x7f1100a9}
            mCurState=5 mStateSaved=false mDestroyed=false
      #1: ae{518b4c8 #1 id=0x7f1100a9}
        mFragmentId=#7f1100a9 mContainerId=#7f1100a9 mTag=null
        mState=5 mIndex=1 mWho=android:fragment:1 mBackStackNesting=1
        mAdded=true mRemoving=false mFromLayout=false mInLayout=false
        mHidden=false mDetached=false mMenuVisible=true mHasMenu=true
        mRetainInstance=false mRetaining=false mUserVisibleHint=true
        mFragmentManager=FragmentManager{14975ae in a{b64f4f}}
        mHost=android.support.v4.app.FragmentActivity$a@b64f4f
        mArguments=Bundle[{the_path=/storage/emulated/0, key_folder_name=存储盘}]
        mContainer=android.widget.FrameLayout{fe406dc V.E...... ........ 0,210-1080,1920 #7f1100a9 app:id/content_frame}
        mView=android.widget.FrameLayout{d4a1961 V.E...... ........ 0,0-1080,1710}
        mInnerView=android.widget.FrameLayout{d4a1961 V.E...... ........ 0,0-1080,1710}
        Child FragmentManager{e4e8c86 in ae{518b4c8}}:
          FragmentManager misc state:
            mHost=android.support.v4.app.FragmentActivity$a@b64f4f
            mContainer=android.support.v4.app.v$2@be6d947
            mParent=ae{518b4c8 #1 id=0x7f1100a9}
            mCurState=5 mStateSaved=false mDestroyed=false
    Added Fragments:
      #0: ad{10cf635 #0 id=0x7f1100a9}
      #1: ae{518b4c8 #1 id=0x7f1100a9}
    Fragments Created Menus:
      #0: ae{518b4c8 #1 id=0x7f1100a9}
    Back Stack:
      #0: BackStackEntry{b685574 #0}
        mName=null mIndex=0 mCommitted=true
        Operations:
          Op #0: ADD ad{10cf635 #0 id=0x7f1100a9}
      #1: BackStackEntry{12dea9d #1}
        mName=null mIndex=1 mCommitted=true
        Operations:
          Op #0: HIDE ad{10cf635 #0 id=0x7f1100a9}
          Op #1: ADD ae{518b4c8 #1 id=0x7f1100a9}
    Back Stack Indices:
      #0: BackStackEntry{b685574 #0}
      #1: BackStackEntry{12dea9d #1}
    FragmentManager misc state:
      mHost=android.support.v4.app.FragmentActivity$a@b64f4f
      mContainer=android.support.v4.app.FragmentActivity$a@b64f4f
      mCurState=5 mStateSaved=false mDestroyed=false
    View Hierarchy:
      com.android.internal.policy.DecorView{76eb12 V.E..... ... 0,0-1080,1920}
        android.widget.LinearLayout{2dc2e3 V.E..... ... 0,0-1080,1920}
          android.view.ViewStub{36f54e0 G.E..... ... 0,0-0,0 #10203f6 android:id/action_mode_bar_stub}
          android.widget.FrameLayout{7839399 V.E..... ... 0,0-1080,1920}
            flyme.support.v7.widget.ActionBarOverlayLayout{b54a65e V.E..... ... 0,0-1080,1920 #7f11008b app:id/decor_content_parent}
              flyme.support.v7.widget.FitsWindowsContentLayout{86faa3f V.E..... ... 0,0-1080,1920 #1020002 android:id/content}
                android.widget.FrameLayout{fe406dc V.E..... ... 0,210-1080,1920 #7f1100a9 app:id/content_frame}
                  android.widget.FrameLayout{85623e5 G.E..... ... 0,0-1080,1710}
                    android.widget.RelativeLayout{924df0c V.E..... ... 0,0-1080,1710 #7f1100ab app:id/list_layout}
                      com.meizu.flyme.filemanager.widget.DirectoryNavigation{ff0d055 V.E..... ... 0,0-1080,114 #7f1100ac app:id/truncated_text}
                        android.widget.ImageView{fc0ca6a V.ED..C. ... 66,34-108,79}
                        android.widget.ImageView{abeab5b V.ED.... ... 174,45-178,69}
                        android.widget.HorizontalScrollView{ec6dff8 VFED.... ... 180,0-1032,114}
                          android.widget.LinearLayout{4bb1cd1 V.E..... ... 0,0-204,114}
                            android.widget.TextView{1192336 I.ED..C. ... 0,0-204,114 #7f1101ea app:id/nav_item}
                        android.widget.TextView{232a237 V.ED..C. ... 180,0-384,114 #7f1101ea app:id/nav_item}
                      flyme.support.v7.widget.MzRecyclerView{a8603a4 VFED.V.L ... 0,114-1080,1710 #7f1100ad app:id/file_list}
                        com.meizu.flyme.filemanager.file.FileItemView{503b50d V.E..... ... 0,0-1080,282}
                          android.widget.LinearLayout{8cc3cc2 V.E..... ... 0,0-396,282 #7f1100b4 app:id/mz_icon_container}
                            android.view.View{9852ad3 V.ED.... ... 66,48-330,57}
                            android.view.View{2f1b610 V.ED.... ... 57,57-339,66}
                            android.widget.ImageView{be1dda V.ED.... ... 48,66-348,234 #7f110205 app:id/above_icon}
                          android.widget.RelativeLayout{6c39509 V.E..... ... 396,0-1032,282 #7f1100b5 app:id/mz_text_container}
                            android.widget.TextView{6a5630e V.ED.... ... 0,63-636,120 #1020014 android:id/text1}
                            android.widget.TextView{36da12f V.ED.... ... 0,129-636,171 #1020015 android:id/text2}
                            android.widget.TextView{624233c V.ED.... ... 0,183-636,225 #7f110206 app:id/text3}
                          android.widget.LinearLayout{c8778c5 V.E..... ... 1032,0-1080,282 #7f1100b6 app:id/mz_extra_container}
                            com.meizu.common.widget.AnimCheckBox{9d8a21a G.ED.... ... 0,0-0,0 #1020001 android:id/checkbox}
                        com.meizu.flyme.filemanager.file.FileItemView{cbd214b V.E..... ... 0,282-1080,564}
                          android.widget.LinearLayout{7ae3728 V.E..... ... 0,0-396,282 #7f1100b4 app:id/mz_icon_container}
                            android.view.View{aebdc41 V.ED.... ... 66,48-330,57}
                            android.view.View{ecec5e6 V.ED.... ... 57,57-339,66}
                            android.widget.ImageView{74688df V.ED.... ... 48,66-348,234 #7f110205 app:id/above_icon}
                          android.widget.RelativeLayout{43a8727 V.E..... ... 396,0-1032,282 #7f1100b5 app:id/mz_text_container}
                            android.widget.TextView{1439dd4 V.ED.... ... 0,63-636,120 #1020014 android:id/text1}
                            android.widget.TextView{9d8fb7d V.ED.... ... 0,129-636,171 #1020015 android:id/text2}
                            android.widget.TextView{db15a72 V.ED.... ... 0,183-636,225 #7f110206 app:id/text3}
                          android.widget.LinearLayout{43e6ec3 V.E..... ... 1032,0-1080,282 #7f1100b6 app:id/mz_extra_container}
                            com.meizu.common.widget.AnimCheckBox{226c340 G.ED.... ... 0,0-0,0 #1020001 android:id/checkbox}
                      flyme.support.v7.widget.RecyclerFastScrollLetter{d7ed279 I.E..... ... 0,114-1080,1710 #7f110209 app:id/fast_scroller}
                        android.widget.RelativeLayout{5b6abbe V.E..... ... 0,0-1071,1470}
                          android.widget.TextView{a0f341f I.ED.... ... 447,646-624,823 #7f11013f app:id/fastscroller_overlay}
                          android.widget.LinearLayout{954d36c V.E..... ... 1026,0-1071,1470 #7f110140 app:id/fastscroller_letterbar_layout}
                            android.widget.ImageView{3111d35 V.ED.... ... 0,150-36,1320 #7f110141 app:id/fastscroller_letterbar}
                    com.meizu.flyme.filemanager.widget.RecyclerViewFastScroller{12dc5ca G.E..... ... 0,0-0,0 #7f110215 app:id/fastScroller}
                      android.view.View{6fcf33b G.ED.... ... 0,0-0,0 #7f110216 app:id/fastscroll_handle}
                    android.widget.LinearLayout{671ba58 G.E...C. ... 0,0-0,0 #7f1100ae app:id/title_layout}
                      android.widget.ImageView{d4357b1 V.ED.... ... 0,0-0,0 #1020000 android:id/background}
                      android.widget.TextView{e4a7496 V.ED.... ... 0,0-0,0 #7f1100af app:id/text1}
                    com.meizu.common.widget.EmptyView{73d8817 G.E..... ... 0,0-0,0 #7f1100b0 app:id/no_result}
                      android.widget.RelativeLayout{d742404 V.E..... ... 0,0-0,0}
                        android.widget.RelativeLayout{c84bded V.E..... ... 0,0-0,0 #7f110151 app:id/layout}
                          android.widget.ImageView{8b14422 V.ED.... ... 0,0-0,0 #7f110188 app:id/empty_image}
                          com.meizu.common.widget.LimitedWHLinearLayout{a888eb3 V.E..... ... 0,0-0,0 #7f110189 app:id/content_panel}
                            android.widget.TextView{9117c70 V.ED.... ... 0,0-0,0 #7f11018a app:id/empty_title}
                            android.widget.TextView{efc4be9 G.ED.... ... 0,0-0,0 #7f11018b app:id/empty_summary}
                            android.view.View{fc3806e G.ED.... ... 0,0-0,0 #7f11018c app:id/titleDivider}
                            android.widget.LinearLayout{a73630f V.E..... ... 0,0-0,0 #7f11018d app:id/empty_tips_panel}
                    android.widget.RelativeLayout{7e9ef9c G.E..... ... 0,0-0,0 #7f1100b1 app:id/refresh_view}
                      com.meizu.common.widget.LoadingView{444bda5 V.ED.... ... 0,0-0,0 #7f1100b2 app:id/refresh_bar}
                      android.widget.TextView{5ab357a V.ED.... ... 0,0-0,0 #7f1100b3 app:id/refresh_text}
                  android.widget.FrameLayout{d4a1961 V.E..... ... 0,0-1080,1710}
                    android.widget.RelativeLayout{a8d212b V.E..... ... 0,0-1080,1710 #7f1100ab app:id/list_layout}
                      com.meizu.flyme.filemanager.widget.DirectoryNavigation{746988 V.E..... ... 0,0-1080,114 #7f1100ac app:id/truncated_text}
                        android.widget.ImageView{e88f21 V.ED..C. ... 66,34-108,79}
                        android.widget.ImageView{3272f46 V.ED.... ... 174,45-178,69}
                        android.widget.HorizontalScrollView{e3aa507 VFED.... ... 180,0-1032,114}
                          android.widget.LinearLayout{7aa9634 V.E..... ... 0,0-447,114}
                            android.widget.TextView{d9dfc5d I.ED..C. ... 0,0-204,114 #7f1101ea app:id/nav_item}
                            android.widget.TextView{c16f9d2 V.ED..C. ... 204,0-447,114 #7f1101ea app:id/nav_item}
                        android.widget.TextView{528aa3 V.ED..C. ... 180,0-384,114 #7f1101ea app:id/nav_item}
                      flyme.support.v7.widget.MzRecyclerView{674e1a0 VFED.V.L ... 0,114-1080,1710 #7f1100ad app:id/file_list}
                        android.widget.RelativeLayout{e430159 V.E..... ... 0,0-144,108 #7f1100da app:id/disk_directory_partition_header}
                          android.widget.RelativeLayout{c6e11e G.E..... ... 0,0-0,0 #7f1100db app:id/partition_grey_header}
                          android.widget.TextView{8792dff V.ED.... ... 72,0-144,108 #7f1100dc app:id/list_partition_header_text1}
                        android.widget.RelativeLayout{9d677cc V.E..... ... 0,108-1080,390 #7f110004 app:id/file_item}
                          android.widget.RelativeLayout{c995a15 V.E..... ... 0,0-396,282 #7f1100b4 app:id/mz_icon_container}
                            android.widget.ImageView{452fb8f V.ED.... ... 48,57-348,225 #1020006 android:id/icon}
                            android.widget.ImageView{7fbf12a G.ED.... ... 0,0-0,0 #7f1100f4 app:id/privacy_logo}
                          android.widget.RelativeLayout{a3cab1b V.E..... ... 396,0-1032,282 #7f1100b5 app:id/mz_text_container}
                            android.widget.TextView{9d944b8 V.ED.... ... 0,83-636,148 #1020014 android:id/text1}
                            android.widget.TextView{2c28291 V.ED.... ... 0,157-636,199 #1020015 android:id/text2}
                          android.widget.LinearLayout{dbff5f6 V.E..... ... 1032,0-1080,282 #7f1100b6 app:id/mz_extra_container}
                            com.meizu.common.widget.AnimCheckBox{9f0ddf7 G.ED.... ... 0,0-0,0 #1020001 android:id/checkbox}
                      flyme.support.v7.widget.RecyclerFastScrollLetter{339f464 G.E..... ... 0,114-1080,1710 #7f110209 app:id/fast_scroller}
                        android.widget.RelativeLayout{17bb6cd V.E..... ... 0,0-1071,1470}
                          android.widget.TextView{2ed7b82 I.ED.... ... 447,646-624,823 #7f11013f app:id/fastscroller_overlay}
                          android.widget.LinearLayout{34b6293 V.E..... ... 1026,0-1071,1470 #7f110140 app:id/fastscroller_letterbar_layout}
                            android.widget.ImageView{fd3f2d0 V.ED.... ... 0,150-36,1320 #7f110141 app:id/fastscroller_letterbar}
                    com.meizu.flyme.filemanager.widget.RecyclerViewFastScroller{e19f2c9 G.E..... ... 0,0-0,0 #7f110215 app:id/fastScroller}
                      android.view.View{97bcdce G.ED.... ... 0,0-0,0 #7f110216 app:id/fastscroll_handle}
                    android.widget.LinearLayout{dbf94ef G.E...C. ... 0,0-0,0 #7f1100ae app:id/title_layout}
                      android.widget.ImageView{9cd6bfc V.ED.... ... 0,0-0,0 #1020000 android:id/background}
                      android.widget.TextView{445f285 V.ED.... ... 0,0-0,0 #7f1100af app:id/text1}
                    com.meizu.common.widget.EmptyView{98af8da G.E..... ... 0,0-0,0 #7f1100b0 app:id/no_result}
                      android.widget.RelativeLayout{a9a910b V.E..... ... 0,0-0,0}
                        android.widget.RelativeLayout{5834be8 V.E..... ... 0,0-0,0 #7f110151 app:id/layout}
                          android.widget.ImageView{7783201 V.ED.... ... 0,0-0,0 #7f110188 app:id/empty_image}
                          com.meizu.common.widget.LimitedWHLinearLayout{b2fc8a6 V.E..... ... 0,0-0,0 #7f110189 app:id/content_panel}
                            android.widget.TextView{8df32e7 V.ED.... ... 0,0-0,0 #7f11018a app:id/empty_title}
                            android.widget.TextView{e353e94 G.ED.... ... 0,0-0,0 #7f11018b app:id/empty_summary}
                            android.view.View{34ed3d G.ED.... ... 0,0-0,0 #7f11018c app:id/titleDivider}
                            android.widget.LinearLayout{ffc932 V.E..... ... 0,0-0,0 #7f11018d app:id/empty_tips_panel}
                    android.widget.RelativeLayout{ae21683 G.E..... ... 0,0-0,0 #7f1100b1 app:id/refresh_view}
                      com.meizu.common.widget.LoadingView{171b000 V.ED.... ... 0,0-0,0 #7f1100b2 app:id/refresh_bar}
                      android.widget.TextView{1082039 V.ED.... ... 0,0-0,0 #7f1100b3 app:id/refresh_text}
              flyme.support.v7.widget.ActionBarContainer{f5d467e V.ED.... ... 0,0-1080,210 #7f11008c app:id/action_bar_container}
                flyme.support.v7.widget.Toolbar{9a597df V.E..... ... 0,66-1080,210 #7f11008d app:id/action_bar}
                  android.widget.TextView{541cc2c V.ED.... ... 147,39-243,104}
                  android.widget.ImageButton{f4186f5 VFED..C. ... 0,0-123,144 #7f110011 app:id/mz_toolbar_nav_button}
                  flyme.support.v7.widget.ActionMenuView{c834c8a V.E..... ... 816,0-1080,144 #7f11000c app:id/mz_action_menu_view}
                    flyme.support.v7.view.menu.ActionMenuItemView{1f5d2fb VFED..C. ... 0,6-120,138 #7f11024b app:id/menu_sort}
                    flyme.support.v7.view.menu.ActionMenuItemView{5157f18 VFED..C. ... 120,6-240,138 #7f11024c app:id/menu_search}
                flyme.support.v7.widget.ActionBarContextView{b709d71 G.E..... ... 0,0-0,0 #7f11008e app:id/action_context_bar}
              flyme.support.v7.widget.ActionBarContainer{551a756 G.ED.... ... 0,0-0,0 #7f110014 app:id/split_action_bar}
              android.view.ViewStub{744a3d7 G.E..... ... 0,0-0,0 #7f1101c8 app:id/mz_action_bar_dropdown_stub}


```


## adb shell pm list packages 全字符
```
package:/data/app/com.flyme.roamingpay-2/base.apk=com.flyme.roamingpay
package:/system/priv-app/CtsShimPrivPrebuilt/CtsShimPrivPrebuilt.apk=com.android.cts.priv.ctsshim
package:/system/priv-app/AppSandbox/AppSandbox.apk=com.meizu.pps
package:/data/app/com.google.android.youtube-1/base.apk=com.google.android.youtube
package:/system/app/SampleExtAuthService/SampleExtAuthService.apk=com.qualcomm.qti.auth.sampleextauthservice
package:/system/app/OmaDownload/OmaDownload.apk=com.qrd.omadownload
package:/system/MzApp/Pedometer/Pedometer.apk=com.meizu.net.pedometer
package:/data/app/com.farproc.wifi.analyzer-1/base.apk=com.farproc.wifi.analyzer
package:/system/priv-app/TelephonyProvider/TelephonyProvider.apk=com.android.providers.telephony
package:/system/priv-app/CalendarProvider/CalendarProvider.apk=com.android.providers.calendar
package:/system/priv-app/MediaProvider/MediaProvider.apk=com.android.providers.media
package:/system/vendor/app/colorservice/colorservice.apk=com.qti.service.colorservice
package:/data/app/com.qiyi.video-1/base.apk=com.qiyi.video
package:/system/priv-app/HomeTool/HomeTool.apk=com.meizu.flyme.hometools
package:/system/app/SystemHelper/SystemHelper.apk=com.mobiletools.systemhelper
package:/system/app/shutdownlistener/shutdownlistener.apk=com.qualcomm.shutdownlistner
package:/system/priv-app/FlymeLauncher/FlymeLauncher.apk=com.meizu.flyme.launcher
package:/data/app/SogouInput-1/SogouInput-1.apk=com.sohu.inputmethod.sogou
package:/system/priv-app/WallpaperCropper/WallpaperCropper.apk=com.android.wallpapercropper
package:/system/priv-app/CNEService/CNEService.apk=com.quicinc.cne.CNEService
package:/data/app/com.meizu.desktopbackup-1/base.apk=com.meizu.desktopbackup
package:/system/app/PhoneFeatures/PhoneFeatures.apk=com.qualcomm.qti.phonefeature
package:/data/app/com.youku.phone-2/base.apk=com.youku.phone
package:/system/priv-app/ActivatePhone/ActivatePhone.apk=com.activate.activatephone
package:/data/app/com.meizu.account.pay-2/base.apk=com.meizu.account.pay
package:/system/vendor/app/StorageCleaner/StorageCleaner.apk=com.qti.storagecleaner
package:/system/priv-app/DocumentsUI/DocumentsUI.apk=com.android.documentsui
package:/system/app/NetworkControl/NetworkControl.apk=com.qualcomm.qti.appnetaccess
package:/system/priv-app/ExternalStorageProvider/ExternalStorageProvider.apk=com.android.externalstorage
package:/system/app/AlarmClock/AlarmClock.apk=com.android.alarmclock
package:/system/app/uimremoteclient/uimremoteclient.apk=com.qualcomm.uimremoteclient
package:/system/priv-app/TimerSwitch/TimerSwitch.apk=com.android.timerswitch
package:/system/app/HTMLViewer/HTMLViewer.apk=com.android.htmlviewer
package:/system/vendor/app/SVIService/SVIService.apk=com.qualcomm.svi
package:/data/app/com.hexin.plat.android-1/base.apk=com.hexin.plat.android
package:/system/app/IflytekSpeechService/IflytekSpeechService.apk=com.iflytek.speechsuite
package:/data/app/com.tencent.androidqqmail-1/base.apk=com.tencent.androidqqmail
package:/system/priv-app/PowerSaveModeHome/PowerSaveModeHome.apk=com.meizu.powersave
package:/system/priv-app/MmsService/MmsService.apk=com.android.mms.service
package:/system/priv-app/DownloadProvider/DownloadProvider.apk=com.android.providers.downloads
package:/system/app/SampleAuthenticatorService/SampleAuthenticatorService.apk=com.qualcomm.qti.auth.sampleauthenticatorservice
package:/data/app/com.dianping.v1-1/base.apk=com.dianping.v1
package:/system/app/FileManager/FileManager.apk=com.meizu.filemanager
package:/data/app/cmb.pb-1/base.apk=cmb.pb
package:/system/app/PowerOnAlert/PowerOnAlert.apk=com.android.poweronalert
package:/data/app/com.MobileTicket-1/base.apk=com.MobileTicket
package:/system/app/QtiTelephonyService/QtiTelephonyService.apk=com.qualcomm.qti.telephonyservice
package:/data/app/com.baidu.quickmind-1/base.apk=com.baidu.quickmind
package:/data/app/com.android.browser-2/base.apk=com.android.browser
package:/data/app/com.meizu.flyme.directservice-1/base.apk=com.meizu.flyme.directservice
package:/data/app/com.meizu.compaign-2/base.apk=com.meizu.compaign
package:/data/app/miaopai-1/miaopai-1.apk=com.yixia.videoeditor
package:/system/app/FidoCryptoService/FidoCryptoService.apk=com.qualcomm.qti.auth.fidocryptoservice
package:/data/app/com.android.soundrecorder-2/base.apk=com.android.soundrecorder
package:/system/priv-app/DefaultContainerService/DefaultContainerService.apk=com.android.defcontainer
package:/data/app/com.ccdi.news-1/base.apk=com.ccdi.news
package:/data/app/com.meizu.backup-1/base.apk=com.meizu.backup
package:/system/app/DownloadProviderUi/DownloadProviderUi.apk=com.android.providers.downloads.ui
package:/system/app/PacProcessor/PacProcessor.apk=com.android.pacprocessor
package:/system/vendor/app/CABLService/CABLService.apk=com.qualcomm.cabl
package:/data/app/com.tencent.mm-1/base.apk=com.tencent.mm
package:/data/app/com.qihoo360.mobilesafe.strongbox-1/base.apk=com.qihoo360.mobilesafe.strongbox
package:/data/app/com.meizu.flyme.update-1/base.apk=com.meizu.flyme.update
package:/data/app/com.meizu.wifiadmin-1/base.apk=com.meizu.wifiadmin
package:/data/app/com.tencent.qqlive.player.meizu-2/base.apk=com.tencent.qqlive.player.meizu
package:/data/app/com.sankuai.meituan.takeoutnew-1/base.apk=com.sankuai.meituan.takeoutnew
package:/system/app/CertInstaller/CertInstaller.apk=com.android.certinstaller
package:/system/priv-app/CarrierConfig/CarrierConfig.apk=com.android.carrierconfig
package:/data/app/com.meizu.assistant-2/base.apk=com.meizu.assistant
package:/data/app/net.micode.fileexplorer-1/base.apk=net.micode.fileexplorer
package:/data/app/com.sdu.didi.psnger-2/base.apk=com.sdu.didi.psnger
package:/data/app/com.meizu.flyme.wallet-2/base.apk=com.meizu.flyme.wallet
package:/system/app/datastatusnotification/datastatusnotification.apk=com.qti.qualcomm.datastatusnotification
package:/system/framework/framework-res.apk=android
package:/system/app/WfdService/WfdService.apk=com.qualcomm.wfd.service
package:/data/app/com.meizu.feedback-2/base.apk=com.meizu.feedback
package:/data/app/com.meizu.media.camera-1/base.apk=com.meizu.media.camera
package:/system/app/DeviceInfo/DeviceInfo.apk=com.qti.qualcomm.deviceinfo
package:/data/app/com.baidu.tieba_mini-2/base.apk=com.baidu.tieba_mini
package:/data/app/com.meizu.mstore-1/base.apk=com.meizu.mstore
package:/data/app/com.app.shanghai.metro-2/base.apk=com.app.shanghai.metro
package:/data/app/com.nanchen.rxjava2examples-1/base.apk=com.nanchen.rxjava2examples
package:/system/app/Bridge/Bridge.apk=com.android.flyme.bridge.softsim
package:/data/app/com.android.mms-1/base.apk=com.android.mms
package:/system/priv-app/MtpDocumentsProvider/MtpDocumentsProvider.apk=com.android.mtp
package:/system/app/Stk/Stk.apk=com.android.stk
package:/data/app/com.meizu.net.search-2/base.apk=com.meizu.net.search
package:/system/priv-app/BackupRestoreConfirmation/BackupRestoreConfirmation.apk=com.android.backupconfirm
package:/system/MzApp/PerfUI/PerfUI.apk=com.meizu.perfui
package:/data/app/com.meizu.picker-2/base.apk=com.meizu.picker
package:/data/app/com.icbc-2/base.apk=com.icbc
package:/data/app/com.ygkj.chelaile.standard-1/base.apk=com.ygkj.chelaile.standard
package:/system/MzApp/SceneInfo/SceneInfo.apk=com.meizu.sceneinfo
package:/system/vendor/app/ims/ims.apk=org.codeaurora.ims
package:/system/priv-app/StatementService/StatementService.apk=com.android.statementservice
package:/system/MzApp/NotePaper/NotePaper.apk=com.meizu.notepaper
package:/data/app/com.meizu.flyme.calculator-1/base.apk=com.meizu.flyme.calculator
package:/system/app/CSoftSIMService/CSoftSIMService.apk=com.cainfoharbor.esim.service.control
package:/data/app/com.android.calendar-1/base.apk=com.android.calendar
package:/system/priv-app/ActiveViewLiveWallpaper/ActiveViewLiveWallpaper.apk=com.meizu.activeviewlivewallpaper
package:/data/app/com.meizu.safe-2/base.apk=com.meizu.safe
package:/system/app/SecureExtAuthService/SecureExtAuthService.apk=com.qualcomm.qti.auth.secureextauthservice
package:/data/app/com.meizu.media.reader-2/base.apk=com.meizu.media.reader
package:/system/priv-app/qcrilmsgtunnel/qcrilmsgtunnel.apk=com.qualcomm.qcrilmsgtunnel
package:/system/MzApp/ToolBox/ToolBox.apk=com.meizu.flyme.toolbox
package:/system/app/ConnectivitySettings/ConnectivitySettings.apk=com.meizu.connectivitysettings
package:/system/priv-app/SettingsProvider/SettingsProvider.apk=com.android.providers.settings
package:/system/priv-app/SharedStorageBackup/SharedStorageBackup.apk=com.android.sharedstoragebackup
package:/system/app/PrintSpooler/PrintSpooler.apk=com.android.printspooler
package:/data/app/com.ss.android.tuchong-2/base.apk=com.ss.android.tuchong
package:/system/app/access-qcom-logkit/access-qcom-logkit.apk=com.qualcomm.qti.qcom_accesslogkit
package:/system/priv-app/InCallUI/InCallUI.apk=com.android.incallui
package:/system/app/telresources/telresources.apk=com.android.frameworks.telresources
package:/system/app/webview/webview.apk=com.android.webview
package:/system/priv-app/InputDevices/InputDevices.apk=com.android.inputdevices
package:/system/app/SecureSampleAuthService/SecureSampleAuthService.apk=com.qualcomm.qti.auth.securesampleauthservice
package:/system/priv-app/SystemUIEx/SystemUIEx.apk=com.flyme.systemuiex
package:/system/app/CdmaCallOptions/CdmaCallOptions.apk=com.qualcomm.qti.CdmaCallOptions
package:/data/app/fm.xiami.main-2/base.apk=fm.xiami.main
package:/system/app/OmaDrmEngine/OmaDrmEngine.apk=com.oma.drm
package:/system/priv-app/dpmserviceapp/dpmserviceapp.apk=com.qti.dpmserviceapp
package:/data/app/com.meizu.gamecenter.service-1/base.apk=com.meizu.gamecenter.service
package:/system/MzApp/LockScreenFramework/LockScreenFramework.apk=com.ibimuyu.lockscreen
package:/data/app/com.cubic.autohome-2/base.apk=com.cubic.autohome
package:/system/app/xdivert/xdivert.apk=com.qti.xdivert
package:/system/MzApp/MusicFX/MusicFX.apk=com.android.musicfx
package:/data/app/com.balance6game.housingfund-2/base.apk=com.balance6game.housingfund
package:/system/app/ExtShared/ExtShared.apk=android.ext.shared
package:/system/app/SimSettings/SimSettings.apk=com.qualcomm.qti.simsettings
package:/data/app/com.wudaokou.hippo-1/base.apk=com.wudaokou.hippo
package:/data/app/com.ss.android.article.news-1/base.apk=com.ss.android.article.news
package:/system/priv-app/Telecom/Telecom.apk=com.android.server.telecom
package:/data/app/com.meizu.mznfcpay-1/base.apk=com.meizu.mznfcpay
package:/system/app/KeyChain/KeyChain.apk=com.android.keychain
package:/data/app/com.UCMobile-2/base.apk=com.UCMobile
package:/system/app/PrintRecommendationService/PrintRecommendationService.apk=com.android.printservice.recommendation
package:/system/priv-app/Dialer/Dialer.apk=com.android.dialer
package:/system/priv-app/SystemUITools/SystemUITools.apk=com.flyme.systemuitools
package:/data/app/com.ss.android.ugc.aweme-1/base.apk=com.ss.android.ugc.aweme
package:/system/app/MzAccount/MzAccount.apk=com.meizu.account
package:/system/priv-app/ExtServices/ExtServices.apk=android.ext.services
package:/data/app/com.baidu.input-2/base.apk=com.baidu.input
package:/system/priv-app/CallLogBackup/CallLogBackup.apk=com.android.calllogbackup
package:/data/app/com.tencent.mobileqq-2/base.apk=com.tencent.mobileqq
package:/data/app/com.ss.android.auto-1/base.apk=com.ss.android.auto
package:/system/priv-app/FaceRecognition/FaceRecognition.apk=com.meizu.facerecognition
package:/data/app/com.meizu.media.ebook-2/base.apk=com.meizu.media.ebook
package:/system/priv-app/PackageInstaller/PackageInstaller.apk=com.android.packageinstaller
package:/system/priv-app/Music/Music.apk=com.meizu.media.music
package:/data/app/com.meizu.media.video-1/base.apk=com.meizu.media.video
package:/system/app/btmultisim/btmultisim.apk=org.codeaurora.btmultisim
package:/system/app/PicoTts/PicoTts.apk=com.svox.pico
package:/system/app/AntHalService/AntHalService.apk=com.dsi.ant.server
package:/data/app/com.meizu.media.gallery-1/base.apk=com.meizu.media.gallery
package:/system/priv-app/ProxyHandler/ProxyHandler.apk=com.android.proxyhandler
package:/system/app/CarrierLoadService/CarrierLoadService.apk=com.qualcomm.qti.loadcarrier
package:/data/app/com.UCMobile.intl-1/base.apk=com.UCMobile.intl
package:/data/app/com.zhiliaoapp.musically-1/base.apk=com.zhiliaoapp.musically
package:/data/app/com.wandoujia.phoenix2-2/base.apk=com.wandoujia.phoenix2
package:/data/app/com.meizu.creator.launcher-1/base.apk=com.meizu.creator.launcher
package:/system/MzApp/Map/Map.apk=com.baidu.BaiduMap.meizu
package:/data/app/com.meizu.alphame-1/base.apk=com.meizu.alphame
package:/system/priv-app/ManagedProvisioning/ManagedProvisioning.apk=com.android.managedprovisioning
package:/data/app/com.meizu.dataservice-1/base.apk=com.meizu.dataservice
package:/system/app/NetAdmin/NetAdmin.apk=com.flyme.netadmin
package:/data/app/com.ximalaya.ting.android-1/base.apk=com.ximalaya.ting.android
package:/data/app/com.facebook.katana-1/base.apk=com.facebook.katana
package:/data/app/com.xiaomi.router-1/base.apk=com.xiaomi.router
package:/data/app/com.baidu.BaiduMap-2/base.apk=com.baidu.BaiduMap
package:/data/app/com.zhihu.android-2/base.apk=com.zhihu.android
package:/data/app/com.meizu.remotecooperation-1/base.apk=com.meizu.remotecooperation
package:/system/MzApp/SystemWallpaper/SystemWallpaper.apk=com.meizu.systemwallpaper
package:/data/app/com.meizu.voiceassistant-2/base.apk=com.meizu.voiceassistant
package:/data/app/com.chinamworld.main-2/base.apk=com.chinamworld.main
package:/data/app/com.meizu.cloud-1/base.apk=com.meizu.cloud
package:/system/priv-app/MzSetupWizard/MzSetupWizard.apk=com.meizu.setup
package:/data/app/com.meizu.share-1/base.apk=com.meizu.share
package:/data/app/com.flyme.meizu.store-1/base.apk=com.flyme.meizu.store
package:/system/priv-app/EasyLauncher/EasyLauncher.apk=com.meizu.flyme.easylauncher
package:/data/app/com.liulishuo.engzo-2/base.apk=com.liulishuo.engzo
package:/system/priv-app/PhotoService/PhotoService.apk=com.meizu.media.imageservice
package:/data/app/com.tencent.qqsports-1/base.apk=com.tencent.qqsports
package:/data/app/com.mobike.mobikeapp-2/base.apk=com.mobike.mobikeapp
package:/system/priv-app/TelephonyExtVodafonePack/TelephonyExtVodafonePack.apk=com.qualcomm.qti.telephony.vodafonepack
package:/system/app/CarrierCacheService/CarrierCacheService.apk=com.qualcomm.qti.accesscache
package:/system/app/CosMaster/CosMaster.apk=com.flyme.virtual.softsim
package:/system/priv-app/StorageManager/StorageManager.apk=com.android.storagemanager
package:/system/priv-app/ConfigurationClient/ConfigurationClient.apk=com.wdstechnology.android.kryten
package:/system/app/ExtSettings/ExtSettings.apk=com.qualcomm.qti.extsettings
package:/system/app/BookmarkProvider/BookmarkProvider.apk=com.android.bookmarkprovider
package:/system/priv-app/Settings/Settings.apk=com.android.settings
package:/system/vendor/app/imssettings/imssettings.apk=com.qualcomm.qti.ims
package:/data/app/com.meizu.battery-1/base.apk=com.meizu.battery
package:/data/app/com.jingdong.app.mall-1/base.apk=com.jingdong.app.mall
package:/system/app/MzSyncService/MzSyncService.apk=com.meizu.mzsyncservice
package:/system/app/PrivacyController/PrivacyController.apk=com.meizu.privacy
package:/data/app/com.meizu.datamigration-1/base.apk=com.meizu.datamigration
package:/data/app/com.tmall.wireless-2/base.apk=com.tmall.wireless
package:/system/priv-app/com.qualcomm.location/com.qualcomm.location.apk=com.qualcomm.location
package:/system/app/SimContacts/SimContacts.apk=com.qualcomm.simcontacts
package:/system/priv-app/MeizuNetworkLocation/MeizuNetworkLocation.apk=com.meizu.location
package:/system/MzApp/vlife-sdk-mx/vlife-sdk-mx.apk=com.vlife.mxlock.wallpaper
package:/data/app/com.taobao.idlefish-1/base.apk=com.taobao.idlefish
package:/system/app/QSService/QSService.apk=com.qualcomm.qti.qs
package:/data/app/ctrip.android.view-1/base.apk=ctrip.android.view
package:/data/app/cn.xq.shareelb-2/base.apk=cn.xq.shareelb
package:/system/app/CtsShimPrebuilt/CtsShimPrebuilt.apk=com.android.cts.ctsshim
package:/data/app/com.meizu.flyme.input-1/base.apk=com.meizu.flyme.input
package:/system/app/OmaDrmEngineServer/OmaDrmEngineServer.apk=com.oma.drm.server
package:/system/priv-app/QtiTetherService/QtiTetherService.apk=com.qualcomm.qti.tetherservice
package:/system/app/MdtpService/MdtpService.apk=com.qualcomm.qti.securemsm.mdtp
package:/data/app/com.tianyancha.skyeyequery-1/base.apk=com.tianyancha.skyeyequery
package:/system/priv-app/VpnDialogs/VpnDialogs.apk=com.android.vpndialogs
package:/system/priv-app/xtra_t_app/xtra_t_app.apk=com.qualcomm.location.XT
package:/data/app/com.zukgit.administrator.testjni_zukgit-1/base.apk=com.zukgit.administrator.testjni_zukgit
package:/system/vendor/app/DataStorageCleanerService/DataStorageCleanerService.apk=com.qti.datastoragecleanerservice
package:/system/MzApp/Email/Email.apk=com.android.email
package:/data/app/com.sina.weibo-2/base.apk=com.sina.weibo
package:/system/app/MzPhoneLocationService/MzPhoneLocationService.apk=com.meizu.flyme.service.find
package:/system/priv-app/TeleService/TeleService.apk=com.android.phone
package:/system/priv-app/Shell/Shell.apk=com.android.shell
package:/system/app/WallpaperBackup/WallpaperBackup.apk=com.android.wallpaperbackup
package:/system/priv-app/BlockedNumberProvider/BlockedNumberProvider.apk=com.android.providers.blockednumber
package:/data/app/com.jingdong.pdj-2/base.apk=com.jingdong.pdj
package:/data/app/com.alensw.PicFolder-1/base.apk=com.alensw.PicFolder
package:/system/MzApp/UserDictionaryProvider/UserDictionaryProvider.apk=com.android.providers.userdictionary
package:/system/app/LunarInfoProvider/LunarInfoProvider.apk=com.qualcomm.qti.lunarinfo
package:/data/app/com.meizu.customizecenter-1/base.apk=com.meizu.customizecenter
package:/data/app/com.meizu.flymelab-1/base.apk=com.meizu.flymelab
package:/system/priv-app/FusedLocation/FusedLocation.apk=com.android.location.fused
package:/data/app/com.meizu.flyme.gamecenter-1/base.apk=com.meizu.flyme.gamecenter
package:/system/priv-app/SystemUI/SystemUI.apk=com.android.systemui
package:/system/priv-app/ExperienceDataSync/ExperienceDataSync.apk=com.meizu.experiencedatasync
package:/system/priv-app/MzCallSetting/MzCallSetting.apk=com.meizu.callsetting
package:/system/app/BluetoothMidiService/BluetoothMidiService.apk=com.android.bluetoothmidiservice
package:/data/app/com.meizu.netcontactservice-1/base.apk=com.meizu.netcontactservice
package:/system/app/NetworkSetting/NetworkSetting.apk=com.qualcomm.qti.networksetting
package:/data/app/com.baidu.netdisk-1/base.apk=com.baidu.netdisk
package:/system/app/fastdormancy/fastdormancy.apk=com.qualcomm.fastdormancy
package:/system/app/FidoSuiService/FidoSuiService.apk=com.qualcomm.qti.auth.fidosuiservice
package:/data/app/cm.aptoide.pt-1/base.apk=cm.aptoide.pt
package:/data/app/com.xiaoming.vpn-1/base.apk=com.xiaoming.vpn
package:/data/app/com.taobao.taobao-2/base.apk=com.taobao.taobao
package:/data/app/in.zhaoj.shadowsocksrr-1/base.apk=in.zhaoj.shadowsocksrr
package:/system/app/Bluetooth/Bluetooth.apk=com.android.bluetooth
package:/data/app/com.meizu.media.life-1/base.apk=com.meizu.media.life
package:/system/app/TimeService/TimeService.apk=com.qualcomm.timeservice
package:/system/app/embms/embms.apk=com.qualcomm.embms
package:/system/priv-app/ContactsProvider/ContactsProvider.apk=com.android.providers.contacts
package:/data/app/com.ct.client-2/base.apk=com.ct.client
package:/data/app/tv.danmaku.bili-1/base.apk=tv.danmaku.bili
package:/system/app/CaptivePortalLogin/CaptivePortalLogin.apk=com.android.captiveportallogin
package:/data/app/com.meizu.flyme.weather-1/base.apk=com.meizu.flyme.weather
package:/data/app/com.eg.android.AlipayGphone-2/base.apk=com.eg.android.AlipayGphone
package:/system/app/TelecomUsageData/TelecomUsageData.apk=com.flyme.telecom.usagedata.service



```


## adb shell dumpsys window全字符
```

WINDOW MANAGER LAST ANR (dumpsys window lastanr)
  <no ANR has occurred since boot>

WINDOW MANAGER POLICY STATE (dumpsys window policy)
    mSafeMode=false mSystemReady=true mSystemBooted=true
    mLidState=1 mLidOpenRotation=-1 mCameraLensCoverState=-1 mHdmiPlugged=false
    mLastSystemUiFlags=0x2708 mResettingSystemUiFlags=0x0 mForceClearedSystemUiFlags=0x0
    mWakeGestureEnabledSetting=true
    mSupportAutoRotation=true
    mUiMode=1 mDockMode=0 mEnableCarDockHomeCapture=true mCarDockRotation=-1 mDeskDockRotation=-1
    mUserRotationMode=1 mUserRotation=0 mAllowAllRotations=0
    mCurrentAppOrientation=1
    mCarDockEnablesAccelerometer=true mDeskDockEnablesAccelerometer=true
    mLidKeyboardAccessibility=0 mLidNavigationAccessibility=0 mLidControlsScreenLock=true
 mLidControlsSleep=false
     mLongPressOnBackBehavior=0
    mShortPressOnPowerBehavior=2 mLongPressOnPowerBehavior=1
    mDoublePressOnPowerBehavior=0 mTriplePressOnPowerBehavior=0
    mHasSoftInput=true
    mAwake=true
    mScreenOnEarly=true mScreenOnFully=true
    mKeyguardDrawComplete=true mWindowManagerDrawComplete=true
    mOrientationSensorEnabled=false
    mOverscanScreen=(0,0) 1080x1920
    mRestrictedOverscanScreen=(0,0) 1080x1920
    mUnrestrictedScreen=(0,0) 1080x1920
    mRestrictedScreen=(0,0) 1080x1920
    mStableFullscreen=(0,0)-(1080,1920)
    mStable=(0,66)-(1080,1920)
    mSystem=(0,0)-(1080,1920)
    mCur=(0,66)-(1080,1920)
    mContent=(0,66)-(1080,1920)
    mVoiceContent=(0,66)-(1080,1920)
    mDock=(0,66)-(1080,1920)
    mDockLayer=268435456 mStatusBarLayer=161000
    mShowingLockscreen=false mShowingDream=false mDreamingLockscreen=false mDreamingSleepToken=null
    mStatusBar=Window{c2c702a u0 StatusBar} isStatusBarKeyguard=false
    mFocusedWindow=Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}
    mFocusedApp=Token{69427d8 ActivityRecord{96a6bb u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity t11866}}
    mTopFullscreenOpaqueWindowState=Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}
    mTopFullscreenOpaqueOrDimmingWindowState=Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}
    mTopIsFullscreen=false mHideLockScreen=false
    mForceStatusBar=false mForceStatusBarFromKeyguard=false
    mDismissKeyguard=0 mCurrentlyDismissingKeyguard=false
 mWinDismissingKeyguard=null mHomePressed=false
    mAllowLockscreenWhenOn=false mLockScreenTimeout=300000 mLockScreenTimerActive=false
    mEndcallBehavior=2 mIncallPowerBehavior=1 mIncallBackBehavior=0 mLongPressOnHomeBehavior=0
    mLandscapeRotation=1 mSeascapeRotation=3
    mPortraitRotation=0 mUpsideDownRotation=2
    mDemoHdmiRotation=1 mDemoHdmiRotationLock=false
    mUndockedHdmiRotation=-1
    mKeyMapping.size=0
    BarController.StatusBar
      mState=WINDOW_STATE_SHOWING
      mTransientBar=TRANSIENT_BAR_NONE
    PolicyControl.sImmersiveStatusFilter=null
    PolicyControl.sImmersiveNavigationFilter=null
    PolicyControl.sImmersivePreconfirmationsFilter=null
    WakeGestureListener
      mTriggerRequested=false
      mSensor=null
    WindowOrientationListener
      mEnabled=false
      mCurrentRotation=0
      mSensorType=null
      mSensor={Sensor name="ICM20608 Accelerometer", vendor="InvenSense", version=1, type=1, maxRange=78.4532, resolution=0.0011901855, power=0.325, minDelay=5000}
      mRate=2
      AccelSensorJudge
        mProposedRotation=-1
        mPredictedRotation=-1
        mLastFilteredX=-3.3374956
        mLastFilteredY=4.833953
        mLastFilteredZ=8.154727
        mLastFilteredTimestampNanos=142140672401008 (1.849714E7ms ago)
        mTiltHistory={last: 54.0}
        mFlat=false
        mSwinging=false
        mAccelerating=false
        mOverhead=false
        mTouched=false
        mTiltToleranceConfig=[[-25, 70], [-25, 65], [-25, 60], [-25, 65]]
    KeyguardServiceDelegate
      showing=false
      showingAndNotOccluded=true
      inputRestricted=false
      occluded=false
      secure=true
      dreaming=false
      systemIsReady=true
      deviceHasKeyguard=true
      enabled=true
      offReason=3
      currentUser=-10000
      bootCompleted=true
      screenState=2
      interactiveState=1
      KeyguardStateMonitor
        mIsShowing=false
        mSimSecure=false
        mInputRestricted=false
        mTrusted=false
        mCurrentUserId=0
    SystemGesture
Dump system gesture client

WINDOW MANAGER ANIMATOR STATE (dumpsys window animator)
    DisplayContentsAnimator #0:
      Window #0: WindowStateAnimator{e0adbbc com.android.systemui.ImageWallpaper}
      Window #1: WindowStateAnimator{d9f9979 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher}
      Window #2: WindowStateAnimator{a63f302 com.tencent.mm/com.tencent.mm.ui.LauncherUI}
      Window #3: WindowStateAnimator{e386ee0 com.android.incallui/com.android.incallui.InCallActivity}
      Window #4: WindowStateAnimator{cc055b com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity}
      Window #5: WindowStateAnimator{b39853e PopupWindow:8441a47}
      Window #6: WindowStateAnimator{a0acf9f PopupWindow:e87939d}
      Window #7: WindowStateAnimator{a203b80 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity}
      Window #8: WindowStateAnimator{7c1da9e com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}
      Window #9: WindowStateAnimator{1817487 InputMethod}
      Window #10: WindowStateAnimator{f0215e8 DockedStackFocusIndicator}
      Window #11: WindowStateAnimator{3401 DockedStackDivider}
      Window #12: WindowStateAnimator{9b4a2a6 }
      Window #13: WindowStateAnimator{ddc04e7 AssistPreviewPanel}
      Window #14: WindowStateAnimator{1d6a894 Games}
      Window #15: WindowStateAnimator{f798b3b KeyguardScrim}
      Window #16: WindowStateAnimator{8fb0745 StatusBar}
      Window #17: WindowStateAnimator{6726c75 Heads Up}


    mBulkUpdateParams=0x8 ORIENTATION_CHANGE_COMPLETE

WINDOW MANAGER SESSIONS (dumpsys window sessions)
  Session Session{269e768 2923:u0a10048}:
    mNumWindow=2 mClientDead=false mSurfaceSession=android.view.SurfaceSession@63b0f3d
  Session Session{2b61340 2838:u0a10362}:
    mNumWindow=2 mClientDead=false mSurfaceSession=android.view.SurfaceSession@bf84332
  Session Session{2f91567 10805:u0a10103}:
    mNumWindow=3 mClientDead=false mSurfaceSession=android.view.SurfaceSession@7da0883
  Session Session{33e39d5 2332:1001}:
    mNumWindow=0 mClientDead=false mSurfaceSession=android.view.SurfaceSession@ebfba00
  Session Session{46503f6 2053:u0a10031}:
    mNumWindow=5 mClientDead=false mSurfaceSession=android.view.SurfaceSession@e4e6239
  Session Session{572e6a5 2708:u0a10031}:
    mNumWindow=1 mClientDead=false mSurfaceSession=android.view.SurfaceSession@a63607e
  Session Session{884f90e 30941:1000}:
    mNumWindow=0 mClientDead=false mSurfaceSession=android.view.SurfaceSession@32aa9df
  Session Session{a013268 3137:u0a10011}:
    mNumWindow=1 mClientDead=false mSurfaceSession=android.view.SurfaceSession@a66762c
  Session Session{b593c72 2217:u0a10031}:
    mNumWindow=0 mClientDead=false mSurfaceSession=android.view.SurfaceSession@7a9e8f5
  Session Session{b8cae4f 3009:u0a10003}:
    mNumWindow=1 mClientDead=false mSurfaceSession=android.view.SurfaceSession@ad1068a
  Session Session{cc51ed9 16233:u0a10108}:
    mNumWindow=1 mClientDead=false mSurfaceSession=android.view.SurfaceSession@3ba04fb
  Session Session{df17933 1738:1000}:
    mNumWindow=2 mClientDead=false mSurfaceSession=android.view.SurfaceSession@8dac918


WINDOW MANAGER DISPLAY CONTENTS (dumpsys window displays)
  Display: mDisplayId=0
    init=1080x1920 480dpi cur=1080x1920 app=1080x1920 rng=1080x1014-1920x1854
    mTouchExcludeRegion = SkRegion((0,0,1080,1920))
    deferred=false layoutNeeded=false

  Application tokens in top down Z order:
    mStackId=1
    mDeferDetach=false
    mFullscreen=true
    mBounds=[0,0][1080,1920]
      taskId=11866
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{60fa9b4 token=Token{52e36c6 ActivityRecord{d1104a1 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity t11866}}}, AppWindowToken{a48f731 token=Token{69427d8 ActivityRecord{96a6bb u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity t11866}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #1 AppWindowToken{a48f731 token=Token{69427d8 ActivityRecord{96a6bb u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity t11866}}}
          windows=[Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}]
          windowType=2 hidden=false hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}]
          task={taskId=11866 appTokens size=2 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=false clientHidden=false reportedDrawn=true reportedVisible=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false
          Activity #0 AppWindowToken{60fa9b4 token=Token{52e36c6 ActivityRecord{d1104a1 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity t11866}}}
          windows=[Window{f9d2467 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity}]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{f9d2467 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity}]
          task={taskId=11866 appTokens size=2 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false
      taskId=11862
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{efd1f71 token=Token{5fbd7df ActivityRecord{a24867e u0 com.tencent.androidqqmail/com.tencent.qqmail.fragment.base.MailFragmentActivity t11862}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{efd1f71 token=Token{5fbd7df ActivityRecord{a24867e u0 com.tencent.androidqqmail/com.tencent.qqmail.fragment.base.MailFragmentActivity t11862}}}
          windows=[]
          windowType=2 hidden=true hasVisible=false
          app=true voiceInteraction=false
          task={taskId=11862 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=-1
          hiddenRequested=true clientHidden=false reportedDrawn=false reportedVisible=false
      taskId=11814
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{f676ca2 token=Token{e10386d ActivityRecord{96ef884 u0 com.ss.android.article.news/.activity.MainActivity t11814}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{f676ca2 token=Token{e10386d ActivityRecord{96ef884 u0 com.ss.android.article.news/.activity.MainActivity t11814}}}
          windows=[Window{3d0a880 u0 com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity}]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{3d0a880 u0 com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity}, Window{d4f5243 u0 PopupWindow:8441a47}, Window{54721f9 u0 PopupWindow:e87939d}]
          task={taskId=11814 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=3 numDrawnWindows=3 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false
      taskId=11817
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{3c53738 token=Token{320ffaa ActivityRecord{a6ed79b u0 com.hexin.plat.android/.Hexin t11817}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{3c53738 token=Token{320ffaa ActivityRecord{a6ed79b u0 com.hexin.plat.android/.Hexin t11817}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11817 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
      taskId=11846
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{b18f1d9 token=Token{ca5e723 ActivityRecord{763f852 u0 in.zhaoj.shadowsocksrr/com.github.shadowsocks.Shadowsocks t11846}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{b18f1d9 token=Token{ca5e723 ActivityRecord{763f852 u0 in.zhaoj.shadowsocksrr/com.github.shadowsocks.Shadowsocks t11846}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11846 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=-1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
      taskId=11835
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{3c10156 token=Token{72b755c ActivityRecord{cd467cf u0 cm.aptoide.pt/.view.FairyMainActivity t11835}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{3c10156 token=Token{72b755c ActivityRecord{cd467cf u0 cm.aptoide.pt/.view.FairyMainActivity t11835}}}
          windows=[]
          windowType=2 hidden=true hasVisible=false
          app=true voiceInteraction=false
          task={taskId=11835 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=-1
          hiddenRequested=true clientHidden=false reportedDrawn=false reportedVisible=false
      taskId=11791
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{4100af9 token=Token{139d343 ActivityRecord{2a180f2 u0 com.meizu.assistant/.ui.activity.FakeLauncherActivity t11791}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{4100af9 token=Token{139d343 ActivityRecord{2a180f2 u0 com.meizu.assistant/.ui.activity.FakeLauncherActivity t11791}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11791 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
      taskId=11759
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{43b172b token=Token{e843a5 ActivityRecord{66fcd9c u0 com.android.packageinstaller/.PackageInstallerActivity t11759}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{43b172b token=Token{e843a5 ActivityRecord{66fcd9c u0 com.android.packageinstaller/.PackageInstallerActivity t11759}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11759 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
      taskId=11825
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{a891b77 token=Token{19a2411 ActivityRecord{bac7c38 u0 net.micode.fileexplorer/.FileExplorerTabActivity t11825}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{a891b77 token=Token{19a2411 ActivityRecord{bac7c38 u0 net.micode.fileexplorer/.FileExplorerTabActivity t11825}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11825 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=4
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
      taskId=11792
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{db0f6c2 token=Token{8a9ada4 ActivityRecord{d52b437 u0 com.meizu.media.camera/.CameraLauncher t11792}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{db0f6c2 token=Token{8a9ada4 ActivityRecord{d52b437 u0 com.meizu.media.camera/.CameraLauncher t11792}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11792 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
      taskId=11785
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{5a763f0 token=Token{d7fffa2 ActivityRecord{8af6433 u0 com.mobike.mobikeapp/.ui.maintab.MainTabActivity t11785}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{5a763f0 token=Token{d7fffa2 ActivityRecord{8af6433 u0 com.mobike.mobikeapp/.ui.maintab.MainTabActivity t11785}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11785 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=2 numDrawnWindows=2 inPendingTransaction=false allDrawn=true (animator=true)
      taskId=11742
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{901de26 token=Token{51b0568 ActivityRecord{63f408b u0 com.android.incallui/.InCallActivity t11742}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{901de26 token=Token{51b0568 ActivityRecord{63f408b u0 com.android.incallui/.InCallActivity t11742}}}
          windows=[Window{e1069ba u0 com.android.incallui/com.android.incallui.InCallActivity}]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{e1069ba u0 com.android.incallui/com.android.incallui.InCallActivity}]
          task={taskId=11742 appTokens size=1 mdr=false}
           appFullscreen=false requestedOrientation=5
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false
      taskId=11813
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{6aad63 token=Token{74d311d ActivityRecord{a42ef92 u0 com.jingdong.app.mall/.MainFrameActivity t11813}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{6aad63 token=Token{74d311d ActivityRecord{a42ef92 u0 com.jingdong.app.mall/.MainFrameActivity t11813}}}
          windows=[]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          task={taskId=11813 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
      taskId=11773
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{8861c21 token=Token{e2aa62b ActivityRecord{8e7ae7a u0 com.tencent.mm/.ui.LauncherUI t11773}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{8861c21 token=Token{e2aa62b ActivityRecord{8e7ae7a u0 com.tencent.mm/.ui.LauncherUI t11773}}}
          windows=[Window{ff534c u0 com.tencent.mm/com.tencent.mm.ui.LauncherUI}]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{ff534c u0 com.tencent.mm/com.tencent.mm.ui.LauncherUI}]
          task={taskId=11773 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=-1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false
    mStackId=0
    mDeferDetach=false
    mFullscreen=true
    mBounds=[0,0][1080,1920]
      taskId=11741
        mFullscreen=true
        mBounds=[0,0][1080,1920]
        mdr=false
        appTokens=[AppWindowToken{e46ac23 token=Token{bdd1ca1 ActivityRecord{254bd08 u0 com.meizu.flyme.launcher/.Launcher t11741}}}]
        mTempInsetBounds=[0,0][0,0]
          Activity #0 AppWindowToken{e46ac23 token=Token{bdd1ca1 ActivityRecord{254bd08 u0 com.meizu.flyme.launcher/.Launcher t11741}}}
          windows=[Window{7a92b67 u0 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher}]
          windowType=2 hidden=true hasVisible=true
          app=true voiceInteraction=false
          allAppWindows=[Window{7a92b67 u0 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher}]
          task={taskId=11741 appTokens size=1 mdr=false}
           appFullscreen=true requestedOrientation=1
          hiddenRequested=true clientHidden=true reportedDrawn=false reportedVisible=false
          mAppStopped=true
          numInterestingWindows=1 numDrawnWindows=1 inPendingTransaction=false allDrawn=true (animator=true)
          startingData=null removed=false firstWindowDrawn=true mIsExiting=false


    DimLayerController
      Task=11741
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Stack=1
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11792
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11817
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11773
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11846
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654103
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11785
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11814
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Stack=0
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11825
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11835
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11866
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11791
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11862
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11759
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11742
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0
      Task=11813
        dimLayer=shared, animator=null, continueDimming=false
        mDimSurface=Surface(name=DimLayerController/Stack=0) mLayer=21049 mAlpha=0.0
        mLastBounds=[-270,-480][1350,2400] mBounds=[-270,-480][1350,2400]
        Last animation:  mDuration=200 mStartTime=49906436 curTime=52654104
         mStartAlpha=0.5 mTargetAlpha=0.0

    DockedStackDividerController
      mLastVisibility=false
      mMinimizedDock=false
      mAdjustedForIme=false
      mAdjustedForDivider=false

        DockedStackFocusIndicator
          mWindowVisible=false
          mViewVisible=true
          mStrokeWidth=12
          mStrokeColor=0xff0987f8
          mDrawRect=Rect(0, 0 - 0, 0)

WINDOW MANAGER TOKENS (dumpsys window tokens)
  All tokens:
  WindowToken{619f5d7 null}
  AppWindowToken{a891b77 token=Token{19a2411 ActivityRecord{bac7c38 u0 net.micode.fileexplorer/.FileExplorerTabActivity t11825}}}
  WindowToken{d3f5ec4 android.os.BinderProxy@cf7a103}
  AppWindowToken{b18f1d9 token=Token{ca5e723 ActivityRecord{763f852 u0 in.zhaoj.shadowsocksrr/com.github.shadowsocks.Shadowsocks t11846}}}
  WindowToken{27341ad android.os.Binder@cac08f3}
  AppWindowToken{901de26 token=Token{51b0568 ActivityRecord{63f408b u0 com.android.incallui/.InCallActivity t11742}}}
  AppWindowToken{e46ac23 token=Token{bdd1ca1 ActivityRecord{254bd08 u0 com.meizu.flyme.launcher/.Launcher t11741}}}
  AppWindowToken{4100af9 token=Token{139d343 ActivityRecord{2a180f2 u0 com.meizu.assistant/.ui.activity.FakeLauncherActivity t11791}}}
  AppWindowToken{db0f6c2 token=Token{8a9ada4 ActivityRecord{d52b437 u0 com.meizu.media.camera/.CameraLauncher t11792}}}
  AppWindowToken{efd1f71 token=Token{5fbd7df ActivityRecord{a24867e u0 com.tencent.androidqqmail/com.tencent.qqmail.fragment.base.MailFragmentActivity t11862}}}
  AppWindowToken{60fa9b4 token=Token{52e36c6 ActivityRecord{d1104a1 u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.HomeActivity t11866}}}
  AppWindowToken{6aad63 token=Token{74d311d ActivityRecord{a42ef92 u0 com.jingdong.app.mall/.MainFrameActivity t11813}}}
  WindowToken{41e1873 android.os.Binder@799ce8c}
  AppWindowToken{3c10156 token=Token{72b755c ActivityRecord{cd467cf u0 cm.aptoide.pt/.view.FairyMainActivity t11835}}}
  AppWindowToken{43b172b token=Token{e843a5 ActivityRecord{66fcd9c u0 com.android.packageinstaller/.PackageInstallerActivity t11759}}}
  AppWindowToken{f676ca2 token=Token{e10386d ActivityRecord{96ef884 u0 com.ss.android.article.news/.activity.MainActivity t11814}}}
  AppWindowToken{5a763f0 token=Token{d7fffa2 ActivityRecord{8af6433 u0 com.mobike.mobikeapp/.ui.maintab.MainTabActivity t11785}}}
  AppWindowToken{8861c21 token=Token{e2aa62b ActivityRecord{8e7ae7a u0 com.tencent.mm/.ui.LauncherUI t11773}}}
  AppWindowToken{a48f731 token=Token{69427d8 ActivityRecord{96a6bb u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity t11866}}}
  AppWindowToken{3c53738 token=Token{320ffaa ActivityRecord{a6ed79b u0 com.hexin.plat.android/.Hexin t11817}}}

  Wallpaper tokens:
  Wallpaper #0 WindowToken{41e1873 android.os.Binder@799ce8c}

WINDOW MANAGER WINDOWS (dumpsys window windows)
  Window #17 Window{4df4b8a u0 Heads Up}:
    mDisplayId=0 stackId=0 mSession=Session{46503f6 2053:u0a10031} mClient=android.os.BinderProxy@5a221f5
    mOwnerUid=10031 mShowToOwnerOnly=false package=com.android.systemui appop=NONE
    mAttrs=WM.LayoutParams{(0,0)(fillx406) gr=#30 sim=#10 ty=2014 fl=#1800328 fmt=-3 wanim=0x7f100108 meizuFlags=0x0Meizu WM.LayoutParams [ flags=0x0] }
    Requested w=1080 h=406 mLayoutSeq=14449
    mHasSurface=false mShownPosition=[0,0] isReadyForDisplay()=false hasSavedSurface()=false mWindowRemovalAllowed=false
    WindowStateAnimator{6726c75 Heads Up}:
  Window #16 Window{c2c702a u0 StatusBar}:
    mDisplayId=0 stackId=0 mSession=Session{46503f6 2053:u0a10031} mClient=android.os.BinderProxy@f507515
    mOwnerUid=10031 mShowToOwnerOnly=false package=com.android.systemui appop=NONE
    mAttrs=WM.LayoutParams{(0,0)(fillx66) gr=#30 sim=#10 ty=2000 fl=#81840048 fmt=-3 vsysui=0x600 meizuFlags=0x0Meizu WM.LayoutParams [ flags=0x0] }
    Requested w=1080 h=66 mLayoutSeq=18462
    mHasSurface=true mShownPosition=[0,0] isReadyForDisplay()=true hasSavedSurface()=false mWindowRemovalAllowed=false
    WindowStateAnimator{8fb0745 StatusBar}:
      Surface: shown=true layer=161000 alpha=1.0 rect=(0.0,0.0) 1080.0 x 66.0
    mLastFreezeDuration=+6h13m28s481ms
  Window #15 Window{c910ce0 u0 KeyguardScrim}:
    mDisplayId=0 stackId=0 mSession=Session{df17933 1738:1000} mClient=android.view.ViewRootImpl$W@6635ae3
    mOwnerUid=1000 mShowToOwnerOnly=false package=android appop=NONE
    mAttrs=WM.LayoutParams{(0,0)(fillxfill) sim=#10 ty=2029 fl=#1110900 pfl=0x1 fmt=-3 or=5 vsysui=0x3610000 meizuFlags=0x0Meizu WM.LayoutParams [ flags=0x0] }
    Requested w=1080 h=1920 mLayoutSeq=38
    mHasSurface=false mShownPosition=[0,0] isReadyForDisplay()=false hasSavedSurface()=false mWindowRemovalAllowed=false
    WindowStateAnimator{f798b3b KeyguardScrim}:
    mLastFreezeDuration=+54s104ms

  Window #0 Window{c01cd53 u0 com.android.systemui.ImageWallpaper}:
    mDisplayId=0 stackId=0 mSession=Session{46503f6 2053:u0a10031} mClient=android.os.BinderProxy@ba3d942
    mOwnerUid=10031 mShowToOwnerOnly=true package=com.android.systemui appop=NONE
    mAttrs=WM.LayoutParams{(0,0)(1080x1920) gr=#800033 ty=2013 fl=#10318 fmt=2 wanim=0x10302f2 meizuFlags=0x0Meizu WM.LayoutParams [ flags=0x0] }
    Requested w=1080 h=1920 mLayoutSeq=18460
    mIsImWindow=false mIsWallpaper=true mIsFloatingLayer=true mWallpaperVisible=false
    mHasSurface=true mShownPosition=[0,0] isReadyForDisplay()=false hasSavedSurface()=false mWindowRemovalAllowed=false
    WindowStateAnimator{e0adbbc com.android.systemui.ImageWallpaper}:
      Surface: shown=false layer=21000 alpha=1.0 rect=(0.0,0.0) 1080.0 x 1920.0
    mLastFreezeDuration=+2m33s377ms
    mWallpaperX=0.0 mWallpaperY=0.5

  mCurConfiguration={1.0 460mcc11mnc [zh_CN] ldltr sw360dp w360dp h618dp 480dpi nrml long port finger -keyb/v/h -nav/h s.22}
  mHasPermanentDpad=false
  mCurrentFocus=Window{412f3dd u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity}
  mFocusedApp=AppWindowToken{a48f731 token=Token{69427d8 ActivityRecord{96a6bb u0 com.meizu.filemanager/com.meizu.flyme.filemanager.activity.FileManagerActivity t11866}}}
  mInputMethodTarget=Window{ff534c u0 com.tencent.mm/com.tencent.mm.ui.LauncherUI}
  mInTouchMode=true mLayoutSeq=18462
  mLastDisplayFreezeDuration=+451ms due to Window{c2c702a u0 StatusBar}
  mLastWakeLockHoldingWindow=null mLastWakeLockObscuringWindow=null



```

## adb shell dumpsys wifi 全字符
```
Wi-Fi is enabled
Stay-awake conditions: 0
mMulticastEnabled 0
mMulticastDisabled 0
mInIdleMode false
mScanPending false
WifiController:
 total records=612
 rec[0]: time=12-17 13:13:05.633 processed=DefaultState org=ApStaDisabledState dest=<null> what=155651(0x26003)
 rec[1]: time=12-17 13:13:49.690 processed=DefaultState org=ApStaDisabledState dest=<null> what=155650(0x26002)

 rec[97]: time=12-17 18:20:26.195 processed=DefaultState org=DeviceActiveState dest=<null> what=155651(0x26003)
 rec[98]: time=12-17 18:24:13.106 processed=DefaultState org=DeviceActiveState dest=<null> what=155650(0x26002)
 rec[99]: time=12-17 18:24:14.569 processed=DeviceActiveState org=DeviceActiveState dest=<null> what=155660(0x2600c)
curState=DeviceActiveState
mScreenOff false
mDeviceIdle false
mPluggedType 2
mIdleMillis 900000
mSleepPolicy 2
mPersistWifiState 1
mAirplaneModeOn false
mNotificationEnabled false
mNotificationRepeatTime 0
mNotificationShown false
mNumScansSinceNetworkStateChange 0
mEnableTrafficStatsPoll true
mTrafficStatsPollToken 385
mTxPkts 549155
mRxPkts 721525
mDataActivity 0
Latest scan results:
    BSSID              Frequency  RSSI    Age      SSID                                 Flags
  28:6c:07:5a:f5:7c       5745    -27  5944.338    3CC_5G                            [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS]
  1c:5f:2b:5e:d5:54       5785    -37  5944.337    D-Link_DIR-816_5G                 [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]
  18:64:72:21:86:53       5260    -58  5944.337    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:86:50       5260    -58  5944.337    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:85:d0       5785    -65  5944.336    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:85:d3       5785    -66  5944.336    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  8c:21:0a:3c:fe:70       2412    -54  5944.328    TP-LINK_GG                        [WPA-PSK-CCMP][WPA2-PSK-CCMP][WPS][ESS]
  28:6c:07:5a:f5:7b       2412    -26  5944.327    3CC                               [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]
  1c:5f:2b:5e:d5:53       2422    -31  5944.327    D-Link_DIR-816                    [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]
  18:64:72:21:86:40       2437    -55  5944.327    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:86:43       2437    -57  5944.326    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:79:f0       5320    -68  5944.326    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:79:f3       5320    -68  5944.326    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  d4:ee:07:23:b7:c2       2452    -67  5944.323    jonychen                          [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS]
  18:64:72:21:9c:30       5765    -72  5944.323    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:6a:10       5180    -75  5944.322    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:21:6a:13       5180    -75  5944.322    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:85:c3       2412    -70  5944.321    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:11:3d:90       5240    -78  5944.321    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:11:3d:93       5240    -79  5944.320    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:79:e3       2462    -74  5944.315    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:79:e0       2462    -74  5944.315    lenovo                            [WPA2-EAP-CCMP][ESS]
  18:64:72:11:3d:83       2437    -77  5944.314    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:6a:03       2462    -84  5944.314    lenovo-RD                         [WPA2-EAP-CCMP][ESS]
  18:64:72:21:86:51       5260    -58  5944.313    lenovo-internet                   [ESS]
  18:64:72:21:86:54       5260    -58  5944.313    lenovo-guest                      [ESS]
  18:64:72:21:86:52       5260    -58  5944.313    RD-Test                           [ESS]
  18:64:72:21:85:d1       5785    -65  5944.312    lenovo-internet                   [ESS]
  18:64:72:21:85:d4       5785    -65  5944.308    lenovo-guest                      [ESS]
  18:64:72:21:85:d2       5785    -65  5944.308    RD-Test                           [ESS]
  fa:8f:ca:67:85:c1       2437    -39  5944.307    主卧.b                              [ESS]
  18:64:72:21:86:42       2437    -55  5944.307    lenovo-guest                      [ESS]
  18:64:72:21:86:41       2437    -55  5944.306    lenovo-internet                   [ESS]
  18:64:72:21:86:44       2437    -56  5944.306    RD-Test                           [ESS]
  18:64:72:21:79:f4       5320    -68  5944.305    lenovo-guest                      [ESS]
  18:64:72:21:79:f1       5320    -68  5944.305    lenovo-internet                   [ESS]
  18:64:72:21:79:f2       5320    -68  5944.305    RD-Test                           [ESS]
  18:64:72:21:9c:32       5765    -71  5944.302    RD-Test                           [ESS]
  18:64:72:21:9c:31       5765    -72  5944.302    lenovo-internet                   [ESS]
  18:64:72:21:9c:33       5765    -72  5944.301    lenovo-guest                      [ESS]
  18:64:72:21:6a:11       5180    -74  5944.301    lenovo-internet                   [ESS]
  18:64:72:21:6a:12       5180    -75  5944.300    RD-Test                           [ESS]
  18:64:72:21:6a:14       5180    -75  5944.300    lenovo-guest                      [ESS]
  18:64:72:21:85:c4       2412    -70  5944.300    RD-Test                           [ESS]
  18:64:72:21:85:c2       2412    -71  5944.299    lenovo-guest                      [ESS]
  18:64:72:21:85:c1       2412    -71  5944.299    lenovo-internet                   [ESS]
  18:64:72:11:3d:94       5240    -79  5944.297    lenovo-guest                      [ESS]
  18:64:72:11:3d:92       5240    -79  5944.296    RD-Test                           [ESS]
  18:64:72:11:3d:91       5240    -79  5944.296    lenovo-internet                   [ESS]
  18:64:72:21:79:e4       2462    -73  5944.295    RD-Test                           [ESS]
  18:64:72:21:79:e2       2462    -74  5944.295    lenovo-guest                      [ESS]
  18:64:72:21:79:e1       2462    -74  5944.294    lenovo-internet                   [ESS]
  18:64:72:21:9d:d2       5220    -84  5944.294    RD-Test                           [ESS]
  18:64:72:11:3d:81       2437    -77  5944.294    lenovo-internet                   [ESS]
  18:64:72:11:3d:82       2437    -77  5944.293    lenovo-guest                      [ESS]
  18:64:72:11:3d:84       2437    -78  5944.292    RD-Test                           [ESS]
  18:64:72:21:9c:23       2412    -81  5944.292    RD-Test                           [ESS]
  18:64:72:21:6a:04       2462    -83  5944.292    RD-Test                           [ESS]

Locks held:
Locks acquired: 1 full, 0 full high perf, 0 scan
Locks released: 1 full, 0 full high perf, 0 scan

Locks held:

Multicast Locks held:

WifiStateMachine:
 total records=11021
 rec[0]: time=12-17 18:18:24.626 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52257994/160241705 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146321934] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[1]: time=12-17 18:18:27.651 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52261018/160244729 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146318909] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[2]: time=12-17 18:18:30.675 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52264042/160247753 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146315886] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[3]: time=12-17 18:18:33.696 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52267064/160250775 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146312864] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[4]: time=12-17 18:18:36.720 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52270088/160253798 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146309840] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[5]: time=12-17 18:18:39.745 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52273112/160256823 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146306816] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[6]: time=12-17 18:18:42.768 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52276136/160259847 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-24 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146303792] gl hn rssi=-19 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[7]: time=12-17 18:18:45.793 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52279161/160262872 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146300767] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[8]: time=12-17 18:18:48.804 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52282171/160265882 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3010] from screen [on:0 period:-1146297757] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[9]: time=12-17 18:18:51.825 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52285193/160268904 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146294735] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[10]: time=12-17 18:18:54.846 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52288213/160271924 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3020] from screen [on:0 period:-1146291715] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[11]: time=12-17 18:18:57.867 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52291234/160274945 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1146288694] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[12]: time=12-17 18:19:00.887 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52294255/160277966 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1146285673] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[13]: time=12-17 18:19:03.910 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52297278/160280989 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146282650] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[14]: time=12-17 18:19:06.933 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52300301/160284012 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146279627] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[15]: time=12-17 18:19:09.957 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52303325/160287036 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146276603] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[16]: time=12-17 18:19:12.982 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52306349/160290060 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146273579] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[17]: time=12-17 18:19:15.992 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52309358/160293069 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3009] from screen [on:0 period:-1146270570] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[18]: time=12-17 18:19:19.012 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52312381/160296091 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146267548] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[19]: time=12-17 18:19:22.034 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52315402/160299113 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1146264527] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[20]: time=12-17 18:19:25.055 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52318423/160302134 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146261505] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[21]: time=12-17 18:19:28.078 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52321446/160305157 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146258482] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[22]: time=12-17 18:19:31.100 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52324467/160308178 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1146255461] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[23]: time=12-17 18:19:34.123 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52327491/160311201 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146252438] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[24]: time=12-17 18:19:37.144 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52330512/160314223 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-24 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146249416] gl hn rssi=-19 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[25]: time=12-17 18:19:40.167 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52333535/160317246 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146246393] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[26]: time=12-17 18:19:43.193 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52336561/160320271 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146243368] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[27]: time=12-17 18:19:46.218 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52339586/160323296 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146240343] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[28]: time=12-17 18:19:49.239 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52342607/160326318 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1146237321] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[29]: time=12-17 18:19:52.264 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52345632/160329343 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-26 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146234296] gl hn rssi=-21 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[30]: time=12-17 18:19:55.285 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52348653/160332363 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3020] from screen [on:0 period:-1146231276] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[31]: time=12-17 18:19:58.308 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52351675/160335386 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1146228253] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[32]: time=12-17 18:20:01.332 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52354699/160338410 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146225229] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[33]: time=12-17 18:20:04.356 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52357724/160341435 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-26 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146222204] gl hn rssi=-21 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[34]: time=12-17 18:20:07.384 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52360750/160344461 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-25 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3027] from screen [on:0 period:-1146219177] gl hn rssi=-20 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[35]: time=12-17 18:20:10.395 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52363763/160347474 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3012] from screen [on:0 period:-1146216165] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[36]: time=12-17 18:20:13.420 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52366787/160350498 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-26 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1146213141] gl hn rssi=-21 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[37]: time=12-17 18:20:16.432 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52369801/160353512 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3013] from screen [on:0 period:-1146210128] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[38]: time=12-17 18:20:19.458 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52372826/160356536 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1146207103] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[39]: time=12-17 18:20:22.468 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52375836/160359547 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3011] from screen [on:0 period:-1146204092] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[40]: time=12-17 18:20:25.488 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52378857/160362568 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3020] from screen [on:0 period:-1146201072] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[41]: time=12-17 18:20:26.187 processed=DefaultState org=ConnectedState dest=<null> what=131167(0x2005f) CMD_SCREEN_STATE_CHANGED  rt=52379557/160363267 0 0
 rec[42]: time=12-17 18:20:26.187 processed=L2ConnectedState org=ConnectedState dest=<null> what=131154(0x20052) CMD_ENABLE_RSSI_POLL  rt=52379557/160363268 0 0
 rec[43]: time=12-17 18:20:26.224 processed=DriverStartedState org=ConnectedState dest=<null> what=131158(0x20056) CMD_SET_SUSPEND_OPT_ENABLED  rt=52379593/160363304 1 1
 rec[44]: time=12-17 18:20:28.493 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) CMD_RSSI_POLL  rt=52381860/160365571 116 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3004] from screen [on:0 period:-1146198068] offload-stopped
 rec[45]: time=12-17 18:22:53.499 processed=SupplicantStartedState org=ConnectedState dest=<null> what=131135(0x2003f) CMD_GET_LINK_LAYER_STATS uid=1000  rt=52526868/160510579 0 0
 rec[46]: time=12-17 18:24:13.053 processed=DefaultState org=ConnectedState dest=<null> what=131167(0x2005f) !CMD_SCREEN_STATE_CHANGED  rt=52606423/160590133 1 0
 rec[47]: time=12-17 18:24:13.057 processed=L2ConnectedState org=ConnectedState dest=<null> what=131154(0x20052) !CMD_ENABLE_RSSI_POLL  rt=52606427/160590138 1 0
 rec[48]: time=12-17 18:24:13.086 processed=DriverStartedState org=ConnectedState dest=<null> what=131158(0x20056) !CMD_SET_SUSPEND_OPT_ENABLED  rt=52606455/160590166 0 0
 rec[49]: time=12-17 18:24:16.065 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52609434/160593145 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:227573] from screen [on:0 period:-1145970495] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[50]: time=12-17 18:24:19.090 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52612457/160596168 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145967471] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[51]: time=12-17 18:24:22.110 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52615477/160599188 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1145964450] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[52]: time=12-17 18:24:25.131 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52618499/160602210 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3020] from screen [on:0 period:-1145961430] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[53]: time=12-17 18:24:28.148 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52621517/160605228 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3018] from screen [on:0 period:-1145958412] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[54]: time=12-17 18:24:31.172 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52624540/160608251 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145955388] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[55]: time=12-17 18:24:34.201 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52627568/160611279 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3029] from screen [on:0 period:-1145952359] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[56]: time=12-17 18:24:37.207 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52630575/160614286 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3005] from screen [on:0 period:-1145949354] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[57]: time=12-17 18:24:40.231 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52633598/160617309 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145946330] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[58]: time=12-17 18:24:43.249 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52636618/160620329 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-36 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145943311] gl hn rssi=-31 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[59]: time=12-17 18:24:46.272 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52639640/160623350 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1145940288] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[60]: time=12-17 18:24:49.298 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52642666/160626377 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3026] from screen [on:0 period:-1145937262] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[61]: time=12-17 18:24:52.323 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52645691/160629402 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1145934237] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[62]: time=12-17 18:24:55.347 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52648714/160632425 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1145931214] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[63]: time=12-17 18:24:58.370 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52651737/160635448 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3023] from screen [on:0 period:-1145928191] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[64]: time=12-17 18:25:01.376 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52654744/160638455 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-33 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3007] from screen [on:0 period:-1145925184] gl hn rssi=-28 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[65]: time=12-17 18:25:04.401 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52657768/160641479 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-28 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1145922159] gl hn rssi=-23 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[66]: time=12-17 18:25:07.417 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52660786/160644497 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3016] from screen [on:0 period:-1145919143] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[67]: time=12-17 18:25:10.425 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52663794/160647505 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3008] from screen [on:0 period:-1145916135] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[68]: time=12-17 18:25:13.453 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52666820/160650531 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3027] from screen [on:0 period:-1145913108] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[69]: time=12-17 18:25:16.458 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52669826/160653537 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3006] from screen [on:0 period:-1145910102] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[70]: time=12-17 18:25:19.487 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52672854/160656565 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3028] from screen [on:0 period:-1145907074] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[71]: time=12-17 18:25:22.495 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52675864/160659575 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3009] from screen [on:0 period:-1145904065] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[72]: time=12-17 18:25:25.516 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52678885/160662596 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1145901044] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[73]: time=12-17 18:25:28.546 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52681914/160665625 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3030] from screen [on:0 period:-1145898014] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[74]: time=12-17 18:25:31.571 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52684938/160668649 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145894990] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[75]: time=12-17 18:25:34.598 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52687964/160671675 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3027] from screen [on:0 period:-1145891963] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[76]: time=12-17 18:25:37.612 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52690978/160674689 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-36 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3014] from screen [on:0 period:-1145888949] gl hn rssi=-31 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[77]: time=12-17 18:25:40.626 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52693994/160677705 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3015] from screen [on:0 period:-1145885934] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[78]: time=12-17 18:25:43.649 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52697016/160680727 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-29 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1145882912] gl hn rssi=-24 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[79]: time=12-17 18:25:46.660 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52700029/160683740 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3012] from screen [on:0 period:-1145879900] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[80]: time=12-17 18:25:49.682 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52703050/160686761 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1145876879] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[81]: time=12-17 18:25:52.700 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52706069/160689780 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145873860] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[82]: time=12-17 18:25:55.722 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52709091/160692801 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-30 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3022] from screen [on:0 period:-1145870838] gl hn rssi=-25 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[83]: time=12-17 18:25:58.729 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52712098/160695809 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3007] from screen [on:0 period:-1145867831] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[84]: time=12-17 18:26:01.736 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52715104/160698815 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3007] from screen [on:0 period:-1145864824] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[85]: time=12-17 18:26:04.755 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52718124/160701835 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145861805] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[86]: time=12-17 18:26:07.779 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52721147/160704858 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-35 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145858781] gl hn rssi=-30 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[87]: time=12-17 18:26:10.806 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52724173/160707884 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3027] from screen [on:0 period:-1145855754] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[88]: time=12-17 18:26:13.825 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52727194/160710905 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-27 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145852735] gl hn rssi=-22 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[89]: time=12-17 18:26:16.844 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52730213/160713923 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145849716] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[90]: time=12-17 18:26:19.863 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52733232/160716943 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3019] from screen [on:0 period:-1145846697] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[91]: time=12-17 18:26:22.884 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52736253/160719964 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3021] from screen [on:0 period:-1145843676] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[92]: time=12-17 18:26:25.897 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52739265/160722976 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3013] from screen [on:0 period:-1145840663] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[93]: time=12-17 18:26:26.339 processed=SupplicantStartedState org=ConnectedState dest=<null> what=131135(0x2003f) !CMD_GET_LINK_LAYER_STATS uid=1000  rt=52739709/160723420 0 0
 rec[94]: time=12-17 18:26:28.922 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52742289/160726000 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1145837638] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[95]: time=12-17 18:26:31.947 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52745315/160729026 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1145834613] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[96]: time=12-17 18:26:34.971 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52748338/160732049 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-32 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3024] from screen [on:0 period:-1145831589] gl hn rssi=-27 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[97]: time=12-17 18:26:37.997 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52751365/160735076 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-31 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3026] from screen [on:0 period:-1145828563] gl hn rssi=-26 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[98]: time=12-17 18:26:41.023 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52754390/160738101 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-33 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3026] from screen [on:0 period:-1145825537] gl hn rssi=-28 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
 rec[99]: time=12-17 18:26:44.048 processed=L2ConnectedState org=ConnectedState dest=<null> what=131155(0x20053) !CMD_RSSI_POLL  rt=52757416/160741126 118 0 "3CC_5G" 28:6c:07:5a:f5:7c rssi=-34 f=5745 sc=100 link=150 tx=0.0, 0.0, 0.0  rx=0.0 bcn=0 [on:0 tx:0 rx:0 period:3025] from screen [on:0 period:-1145822512] gl hn rssi=-29 ag=0 hr ls-=0 [56,56,56,56,61] brc=0 lrc=0 offload-stopped
curState=ConnectedState
SupplicantStateTracker:
 total records=74
 rec[0]: time=12-16 18:54:16.052 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[1]: time=12-16 18:54:17.539 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[2]: time=12-16 18:55:27.899 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[3]: time=12-16 18:55:29.470 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[4]: time=12-16 18:55:47.912 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[5]: time=12-16 18:55:49.502 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[6]: time=12-16 18:56:07.917 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[7]: time=12-16 18:56:09.602 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[8]: time=12-16 18:56:09.838 processed=DefaultState org=DisconnectedState dest=HandshakeState what=147462(0x24006)
 rec[9]: time=12-16 18:56:11.129 processed=DefaultState org=HandshakeState dest=DisconnectedState what=147462(0x24006)
 rec[10]: time=12-16 18:56:11.130 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[11]: time=12-16 18:56:12.729 processed=DefaultState org=ScanState dest=HandshakeState what=147462(0x24006)
 rec[12]: time=12-16 18:56:23.041 processed=DefaultState org=HandshakeState dest=DisconnectedState what=147462(0x24006)
 rec[13]: time=12-16 18:57:31.374 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[14]: time=12-16 18:57:32.946 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[15]: time=12-16 18:58:46.098 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[16]: time=12-16 18:58:47.445 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[17]: time=12-16 18:58:47.516 processed=DefaultState org=DisconnectedState dest=HandshakeState what=147462(0x24006)
 rec[18]: time=12-16 18:58:47.708 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)
 rec[19]: time=12-16 19:36:09.813 processed=CompletedState org=CompletedState dest=UninitializedState what=131183(0x2006f)
 rec[20]: time=12-16 19:36:13.972 processed=DefaultState org=UninitializedState dest=UninitializedState what=131183(0x2006f)
 rec[21]: time=12-16 19:36:13.999 processed=DefaultState org=UninitializedState dest=DisconnectedState what=147462(0x24006)
 rec[22]: time=12-16 19:36:14.006 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[23]: time=12-16 19:36:14.867 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[24]: time=12-16 19:36:14.951 processed=DefaultState org=DisconnectedState dest=HandshakeState what=147462(0x24006)
 rec[25]: time=12-16 19:36:15.182 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)
 rec[26]: time=12-17 09:37:07.891 processed=CompletedState org=CompletedState dest=DisconnectedState what=147462(0x24006)
 rec[27]: time=12-17 09:38:25.118 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[28]: time=12-17 09:38:26.490 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[29]: time=12-17 09:38:38.312 processed=DefaultState org=DisconnectedState dest=UninitializedState what=131183(0x2006f)
 rec[30]: time=12-17 11:17:59.807 processed=DefaultState org=UninitializedState dest=UninitializedState what=131183(0x2006f)
 rec[31]: time=12-17 11:17:59.823 processed=DefaultState org=UninitializedState dest=DisconnectedState what=147462(0x24006)
 rec[32]: time=12-17 11:17:59.858 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[33]: time=12-17 11:18:01.022 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[34]: time=12-17 11:18:01.221 processed=DefaultState org=DisconnectedState dest=HandshakeState what=147462(0x24006)
 rec[35]: time=12-17 11:18:01.807 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)
 rec[36]: time=12-17 12:02:38.344 processed=CompletedState org=CompletedState dest=DisconnectedState what=147462(0x24006)
 rec[37]: time=12-17 12:07:16.387 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[38]: time=12-17 12:07:17.573 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[39]: time=12-17 12:07:36.336 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[40]: time=12-17 12:07:37.500 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[41]: time=12-17 12:08:16.293 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[42]: time=12-17 12:08:17.498 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[43]: time=12-17 12:09:05.462 processed=DefaultState org=DisconnectedState dest=UninitializedState what=131183(0x2006f)
 rec[44]: time=12-17 15:37:02.657 processed=DefaultState org=UninitializedState dest=UninitializedState what=131183(0x2006f)
 rec[45]: time=12-17 15:37:02.706 processed=DefaultState org=UninitializedState dest=DisconnectedState what=147462(0x24006)
 rec[46]: time=12-17 15:37:02.748 processed=DefaultState org=DisconnectedState dest=ScanState what=147462(0x24006)
 rec[47]: time=12-17 15:37:03.839 processed=DefaultState org=ScanState dest=DisconnectedState what=147462(0x24006)
 rec[48]: time=12-17 15:37:03.981 processed=DefaultState org=DisconnectedState dest=HandshakeState what=147462(0x24006)
 rec[49]: time=12-17 15:37:04.455 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)
curState=CompletedState
mAuthenticationFailuresCount 0
mAuthFailureInSupplicantBroadcast false
mNetworksDisabledDuringConnect false
mAuthFailureSendWifiSettings true

mLinkProperties {InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.31.239/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [192.168.31.1,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}
mWifiInfo SSID: 3CC_5G, BSSID: 28:6c:07:5a:f5:7c, MAC: 2c:57:31:c2:1d:2a, Supplicant state: COMPLETED, RSSI: -34, Link speed: 150Mbps, Frequency: 5745MHz, Net ID: 26, Metered hint: false, score: 100
mDhcpResults IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds
mNetworkInfo [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: "3CC_5G", failover: false, available: true, roaming: false, metered: false]
mLastSignalLevel 3
mLastBssid 28:6c:07:5a:f5:7c
mLastNetworkId 26
mOperationalMode 1
mUserWantsSuspendOpt true
mSuspendOptNeedsDisabled 4
Supplicant status bssid=28:6c:07:5a:f5:7c
freq=5745
ssid=3CC_5G
id=26
id_str=%7B%22creatorUid%22%3A%2210373%22%2C%22configKey%22%3A%22%5C%223CC_5G%5C%22WPA_PSK%22%7D
mode=station
pairwise_cipher=CCMP
group_cipher=TKIP
key_mgmt=WPA2-PSK
wpa_state=COMPLETED
ip_address=192.168.31.239
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
CountryCode sent to driver CN
mConnectedModeGScanOffloadStarted false
mGScanPeriodMilli 0
mConnectionReqCount 0
mUntrustedReqCount 0
Wlan Wake Reasons:null

WifiMetrics:
WifiMetrics:
mConnectionEvents:
startTime=12-15 21:49:25.188, SSID="102", BSSID=bc:5f:f6:e9:2a:bb, durationMillis=13792, roamType=ROAM_NONE, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-65, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=2467, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=4, mSupportsIpv6=false
startTime=12-16 18:56:09.793, SSID="102", BSSID=any, durationMillis=1307, roamType=ROAM_NONE, connectionResult=0, level2FailureCode=ASSOCIATION_REJECTION, connectivityLevelFailureCode=NONE, signalStrength=-73, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=2412, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=0, mSupportsIpv6=false
startTime=12-16 18:58:47.504, SSID="102", BSSID=bc:5f:f6:e9:2a:bb, durationMillis=1033, roamType=ROAM_NONE, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-41, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=2467, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=4, mSupportsIpv6=false
startTime=12-16 19:36:14.934, SSID="102", BSSID=bc:5f:f6:e9:2a:bb, durationMillis=454, roamType=ROAM_NONE, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-60, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=2467, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=4, mSupportsIpv6=false
startTime=12-17 11:18:01.098, SSID="3CC_5G", BSSID=28:6c:07:5a:f5:7c, durationMillis=892, roamType=ROAM_NONE, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-37, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=5745, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=5, mSupportsIpv6=false
startTime=12-17 15:37:03.871, SSID="3CC_5G", BSSID=28:6c:07:5a:f5:7c, durationMillis=792, roamType=ROAM_NONE, connectionResult=1, level2FailureCode=NONE, connectivityLevelFailureCode=NONE, signalStrength=-31, wifiState=WIFI_DISCONNECTED, screenOn=true. mRouterFingerprint: mConnectionEvent.roamType=0, mChannelInfo=5745, mDtim=0, mAuthentication=2, mHidden=false, mRouterTechnology=5, mSupportsIpv6=false
mWifiLogProto.numSavedNetworks=28
mWifiLogProto.numOpenNetworks=13
mWifiLogProto.numPersonalNetworks=15
mWifiLogProto.numEnterpriseNetworks=0
mWifiLogProto.numHiddenNetworks=0
mWifiLogProto.numPasspointNetworks=0
mWifiLogProto.isLocationEnabled=false
mWifiLogProto.isScanningAlwaysEnabled=false
mWifiLogProto.numNetworksAddedByUser=0
mWifiLogProto.numNetworksAddedByApps=28
mWifiLogProto.numNonEmptyScanResults=226
mWifiLogProto.numEmptyScanResults=1
mWifiLogProto.numOneshotScans=234
mWifiLogProto.numBackgroundScans=0
mScanReturnEntries:
  SCAN_UNKNOWN: 0
  SCAN_SUCCESS: 234
  SCAN_FAILURE_INTERRUPTED: 0
  SCAN_FAILURE_INVALID_CONFIGURATION: 0
  FAILURE_WIFI_DISABLED: 0
mSystemStateEntries: <state><screenOn> : <scansInitiated>
  WIFI_UNKNOWN       ON: 0
  WIFI_DISABLED      ON: 0
  WIFI_DISCONNECTED  ON: 22
  WIFI_ASSOCIATED    ON: 212
  WIFI_UNKNOWN      OFF: 0
  WIFI_DISABLED     OFF: 0
  WIFI_DISCONNECTED OFF: 0
  WIFI_ASSOCIATED   OFF: 0
mWifiLogProto.numConnectivityWatchdogPnoGood=0
mWifiLogProto.numConnectivityWatchdogPnoBad=0
mWifiLogProto.numConnectivityWatchdogBackgroundGood=0
mWifiLogProto.numConnectivityWatchdogBackgroundBad=0
mWifiLogProto.numLastResortWatchdogTriggers=0
mWifiLogProto.numLastResortWatchdogBadAssociationNetworksTotal=0
mWifiLogProto.numLastResortWatchdogBadAuthenticationNetworksTotal=0
mWifiLogProto.numLastResortWatchdogBadDhcpNetworksTotal=0
mWifiLogProto.numLastResortWatchdogBadOtherNetworksTotal=0
mWifiLogProto.numLastResortWatchdogAvailableNetworksTotal=0
mWifiLogProto.numLastResortWatchdogTriggersWithBadAssociation=0
mWifiLogProto.numLastResortWatchdogTriggersWithBadAuthentication=0
mWifiLogProto.numLastResortWatchdogTriggersWithBadDhcp=0
mWifiLogProto.numLastResortWatchdogTriggersWithBadOther=0
mWifiLogProto.numLastResortWatchdogSuccesses=0
mWifiLogProto.recordDurationSec=160695
mWifiLogProto.rssiPollRssiCount: Printing counts for [-127, 0]
  0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 1 0 1 2 1 4 3 5 13 13 14 20 39 67 73 70 74 112 163 188 182 193 189 277 225 160 120 236 103 53 45 56 49 53 50 68 61 57 67 61 56 69 59 45 31 21 16 23 22 33 42 66 81 110 145 175 165 203 189 194 156 117 89 49 13 15 10 3 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
mWifiLogProto.alertReasonCounts=()
mWifiLogProto.numTotalScanResults=380
mWifiLogProto.numOpenNetworkScanResults=69
mWifiLogProto.numPersonalNetworkScanResults=283
mWifiLogProto.numEnterpriseNetworkScanResults=28
mWifiLogProto.numHiddenNetworkScanResults=10
mWifiLogProto.numHotspot2R1NetworkScanResults=0
mWifiLogProto.numHotspot2R2NetworkScanResults=0
mWifiLogProto.numScans=21
mWifiLogProto.WifiScoreCount: [0, 60]
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 4 0 0 0 4 

Dump of WifiConfigManager
mLastPriority 48
Configured networks
- DSBLE ID: 11 SSID: "HUAWEI nova 3e" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 25 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 135
update time=09-26 21:37:10.671
creationtime=08-06 20:42:41.356
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 0
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 1 SSID: "lenovo-internet" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 43 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "200E"WPA_PSK connect choice set time: 1544782706721 hasEverConnected: true
 numAssociation 136
 numNoInternetAccessReports 3
update time=12-14 19:00:03.863
creationtime=06-20 14:28:50.085
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=18:64:72:12:ae:71 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 516
 nearby: 111
 nearby: 405
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 6 SSID: "+Shjoycity Free Wifi" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 2 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 3
update time=06-30 20:26:24.285
creationtime=06-30 18:36:53.334
 validatedInternetAccess
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=60:da:83:42:92:11 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 15 SSID: "XXX的 iPhone" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 30 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 1
update time=10-01 10:39:52.427
creationtime=10-01 10:38:25.600
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=a2:3b:e3:df:30:ae cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 23 SSID: "3C_5G" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 0 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: false
update time=12-14 15:35:18.522
creationtime=12-14 15:24:42.022
 KeyMgmt: WPA_PSK Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10373 cname=com.xiaomi.router luid=10373 lname=com.xiaomi.router lcuid=10373 userApproved=USER_UNSPECIFIED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

* ID: 26 SSID: "3CC_5G" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 0 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 2
update time=12-17 15:37:03.847
creationtime=12-14 15:41:38.306
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=any cuid=10373 cname=com.xiaomi.router luid=10373 lname=com.xiaomi.router lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false 
lastConnected: 10179sec roamingFailureBlackListTimeMilli: 1000
 linked: "3CC"WPA_PSK
 nearby: 167
 nearby: 167
 nearby: 167
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 25 SSID: "3CC" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 47 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 1
update time=12-14 15:42:59.854
creationtime=12-14 15:41:38.200
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10373 cname=com.xiaomi.router luid=1000 lname=com.xiaomi.router lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 linked: "3CC_5G"WPA_PSK
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 17 SSID: "Xiaomi_F57A_0A85" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 32 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "200E"WPA_PSK connect choice set time: 1544782706721 hasEverConnected: true
 numAssociation 7
update time=12-14 15:35:20.256
creationtime=10-18 20:27:49.079
 validatedInternetAccess
 KeyMgmt: NONE Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10300 cname=com.xiaomi.router luid=10300 lname=com.xiaomi.router lcuid=10373 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 6
 nearby: 0
 nearby: 0
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 14 SSID: "102" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 45 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 230
update time=12-16 19:36:14.873
creationtime=09-28 13:20:15.602
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=bc:5f:f6:e9:2a:bb cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 1442
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 21 SSID: "D-Link_DIR-816" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 46 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "200E"WPA_PSK connect choice set time: 1544782706721 hasEverConnected: true
 numAssociation 6
update time=12-14 15:41:46.204
creationtime=12-14 10:45:35.912
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 1
 nearby: 1
 nearby: 1
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 16 SSID: "CMCC-aT9r" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 31 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 7
update time=10-03 07:34:53.107
creationtime=10-01 16:58:15.248
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=cc:f0:fd:29:99:68 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 7 SSID: "AuchanFreeWIFI" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 4 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 9
 numNoInternetAccessReports 20
update time=09-07 21:17:24.976
creationtime=07-01 20:45:35.564
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 2
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 2 SSID: "长泰广场免费wifi" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 22 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 303
 numNoInternetAccessReports 112
update time=11-30 13:12:54.874
creationtime=06-24 19:55:14.868
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=10:0f:0f:80:05:0a cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 53
 nearby: 257
 nearby: 117
 nearby: 79
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 20 SSID: "moto g(6) play test" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 41 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "lenovo-internet"NONE connect choice set time: 1541745472051 hasEverConnected: true
 numAssociation 4
 numNoInternetAccessReports 29
update time=11-09 14:25:52.410
creationtime=11-09 14:10:27.212
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 50
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 8 SSID: "Baidu_Friend" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 3 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 7
update time=07-02 20:19:24.816
creationtime=07-02 19:01:04.650
 validatedInternetAccess
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 13 SSID: "a1b2c3d4e5f0" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 20 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 mNetworkSelectionDisableReason NETWORK_SELECTION_DISABLED_ASSOCIATION_REJECTION 
 connect choice: "长泰广场免费wifi"NONE connect choice set time: 1535887409146 hasEverConnected: false
update time=09-02 19:38:59.496
creationtime=08-30 20:57:05.703
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=02:1c:c2:4f:94:10 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 22 SSID: "3C" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 0 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: false
update time=12-14 15:35:18.440
creationtime=12-14 15:24:41.939
 KeyMgmt: WPA_PSK Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10373 cname=com.xiaomi.router luid=10373 lname=com.xiaomi.router lcuid=0 userApproved=USER_UNSPECIFIED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 19 SSID: "love on" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 34 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 1
 numNoInternetAccessReports 11
update time=10-29 17:35:46.145
creationtime=10-29 17:35:46.064
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 5 SSID: "花生地铁WiFi" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 9 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 79
 numNoInternetAccessReports 16
update time=11-24 16:38:45.720
creationtime=06-27 15:01:09.012
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=06:14:4b:71:31:4c cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 106
 nearby: 113
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 3 SSID: "Hema-Free" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 7 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 27
update time=11-27 21:05:10.132
creationtime=06-25 21:08:29.806
 validatedInternetAccess
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=14:1f:ba:85:4c:18 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 0
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 4 SSID: "花生黑卡专享WiFi" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 5 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "花生地铁WiFi"NONE connect choice set time: 1530082869041 hasEverConnected: true
 numAssociation 9
 numNoInternetAccessReports 11
update time=07-07 21:04:42.048
creationtime=06-26 08:50:55.730
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10134 cname=com.nfyg.hsbb luid=10134 lname=com.nfyg.hsbb lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 27 SSID: "200E" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 48 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 6
update time=12-14 18:33:18.332
creationtime=12-14 18:18:26.573
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 0
 nearby: 0
 nearby: 0
 nearby: 0
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 18 SSID: "TRG" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 33 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 1
update time=10-20 22:02:46.755
creationtime=10-20 22:02:46.667
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 0 SSID: "Tenda_2036D0" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 10 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 133
update time=09-29 18:28:35.251
creationtime=08-04 01:51:44.752
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=f0:b4:29:69:d4:0d cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 266
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 9 SSID: "xmly2.4" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 16 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 139
update time=09-29 18:01:45.538
creationtime=07-09 13:44:21.848
 validatedInternetAccess
 KeyMgmt: WPA_PSK Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: *
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=00:27:e3:fc:33:b3 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 24 SSID: "Xiaomi_F57A_0A85_5G" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 0 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 connect choice: "200E"WPA_PSK connect choice set time: 1544782706721 hasEverConnected: true
 numAssociation 2
update time=12-14 15:41:40.084
creationtime=12-14 15:40:28.354
 validatedInternetAccess
 KeyMgmt: NONE Protocols: WPA RSN
 AuthAlgorithms: OPEN
 PairwiseCiphers: NONE TKIP CCMP
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=10373 cname=com.xiaomi.router luid=10373 lname=com.xiaomi.router lcuid=10373 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 0
 nearby: 0
 nearby: 0
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 12 SSID: "AP_A914DA" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 19 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: false
update time=08-30 20:57:05.640
creationtime=08-30 20:57:05.243
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=1000 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0

- DSBLE ID: 10 SSID: "360行车记录仪-CCAF" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 1 HIDDEN: false
 NetworkSelectionStatus NETWORK_SELECTION_ENABLED
 hasEverConnected: true
 numAssociation 2
 numNoInternetAccessReports 11
update time=10-24 09:13:01.952
creationtime=08-06 20:41:25.331
 KeyMgmt: NONE Protocols: WPA RSN WAPI
 AuthAlgorithms:
 PairwiseCiphers: TKIP CCMP SMS4
 GroupCiphers: TKIP CCMP SMS4
 PSK: 
 sim_num *
hex ssid:null
 PSK1: *
 wep: *



 WapiPskType: 0

Enterprise config:
altsubject_match NULL
password <removed>
ca_path NULL
subject_match NULL
engine 0
client_cert NULL
ca_cert NULL
anonymous_identity NULL
identity NULL
key_id NULL
domain_suffix_match NULL
engine_id NULL
IP config:
IP assignment: DHCP
Proxy settings: NONE
 networkSelectionBSSID=d0:fa:1d:3a:cc:af cuid=1000 cname=android.uid.system:1000 luid=1000 lname=android.uid.system:1000 lcuid=-1 userApproved=USER_APPROVED noInternetAccessExpected=false isCarrierNetwork=false roamingFailureBlackListTimeMilli: 1000
 nearby: 3
triggeredLow: 0 triggeredBad: 0 triggeredNotHigh: 0
ticksLow: 0 ticksBad: 0 ticksNotHigh: 0
triggeredJoin: 0



Chipset information :-----------------------------------------------
FW Version is: CNSS-PR-4-0-412-125084-2
Driver Version is: 3.0.11.66
Supported Feature set: -1
--------------------------------------------------------------------
Bug dump 0
system time = 12-16 18:56:22.866
kernel time = 17777.814
reason = 2
kernel log: 


system log: 
--------- beginning of main
02-28 21:31:05.544   675   694 D BootAnimation: Get Carrier Animation file: (null) failed
02-28 21:31:05.544   675   694 I zipro   : ZipFileRO open:/system/media/bootanimation.zip
--------- beginning of system
02-28 21:31:05.649   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_create_key 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_update_key_user_info
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_wipe_key 
02-28 21:31:05.649   464   605 E         : get_tmp_passwd: Passed argument is NULL 
02-28 21:31:05.891   464   605 E QSEECOMAPI: SUCESS::ioctl call to create encryption key for usage 4 success with ret = 0
02-28 21:31:05.891   464   605 I Cryptfs : Ascii password was updated
02-28 21:31:05.912   464   468 V vold    : ----------------
02-28 21:31:05.912   464   468 V vold    : handleBlockEvent with action 1
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 I Cryptfs : target_type = req-crypt
02-28 21:31:05.912   464   605 I Cryptfs : real_blk_name = /dev/block/bootdevice/by-name/userdata, extra_params = fde_enabled ice
02-28 21:31:05.914   464   468 V vold    : ----------------
02-28 21:31:05.914   464   468 V vold    : handleBlockEvent with action 3
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.914   464   468 D vold    : Disk at 253:1 changed
02-28 21:31:05.914   464   605 D Cryptfs : Password is default - restarting filesystem
02-28 21:31:05.915   464   605 D Cryptfs : unmounting /data succeeded
02-28 21:31:05.998   582   618 D PerMgrSrv: modem new state: is on-line
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Worker thread started
02-28 21:31:06.103   574   712 I rmt_storage: wake lock name: rmt_storage_548256216144, name creation success: 24
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Worker thread started
02-28 21:31:06.103   574   713 I rmt_storage: wake lock name: rmt_storage_548255179856, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Client found
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   714 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsg: Worker thread started
02-28 21:31:06.104   574   714 I rmt_storage: wake lock name: rmt_storage_548254143568, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Client found
02-28 21:31:06.104   574   715 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsc: Worker thread started
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   715 I rmt_storage: wake lock name: rmt_storage_548253107280, name creation success: 24
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Alloc request received: Size: 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: New client making a dummy request with buffer req size 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Send response: res=0 err=0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs1: req_h=0x6 msg_id=3: R/W request received
02-28 21:31:06.106   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.106   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Unblock worker thread (th_id: 548256216144)
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Bytes read = 512
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: About to block rmt_storage client thread (th_id: 548256216144) wakelock released: 1, error no: 0
02-28 21:31:06.109   574   712 I rmt_storage: 
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x7 msg_id=3: R/W request received
02-28 21:31:06.110   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Bytes read = 512
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.110   574   713 I rmt_storage: 
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x8 msg_id=3: R/W request received
02-28 21:31:06.112   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.112   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Bytes read = 1571840
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.118   574   713 I rmt_storage: 
02-28 21:31:06.118   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.167   576   576 E         : bt_qmi_dms_init :Error while Initializing qmi_client_init_instance: -3
02-28 21:31:06.167   576   576 E         : QMI initialization failed in 2 try...
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_os_wakelocks_la.c, 304] Wake-lock acquired successfully
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_server.c, 597] Server waiting for client-connection.
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server_utils.c, 101] Normalized file [/readwrite/server_check.txt] to : [/system/rfs/msm/mpss/readwrite/server_chec
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 721] Using temp file[/system/rfs/msm/mpss/readwrite/server_check.txt.rfs_tmp] for atomic put
02-28 21:31:06.194   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1301] Sent last ACK: *Waiting* on reply.
02-28 21:31:06.793   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.03 dur=1016.11 max=18.42 min=13.46
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1310] Success! No timeout detected on last ACK
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1267] WRQ stats : total-blocks = 1 : total-bytes = 5 : 5 timedout-pkts = 0, wrong-pkts = 0
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1283] WRQ was successfully processed.
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1514] WRQ stats in us:
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1515] Total API = 1558601
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1517] Total-TX = 99
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1519] Total-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1521] Max-TX = 50
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1523] Max-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1525] Min-TX = 49
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1527] Min-RX = 185
02-28 21:31:07.747   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_os_wakelocks_la.c, 334] Wake-lock released successfully
02-28 21:31:07.800   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=58.58 dur=1007.09 max=35.66 min=13.93
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: addr_resp.mac_address_valid 1 addr_resp.mac_address_len 6 
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: Succesfully Read BT address
02-28 21:31:08.169   576   576 E         : Read MODEM_NV_BD_ADDR 0x2b 0x1d 0xc2 0x31 0x57 0x2c 
02-28 21:31:08.804   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.76 dur=1004.06 max=17.31 min=15.50
02-28 21:31:09.816   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.28 dur=1012.03 max=17.35 min=7.01
02-28 21:31:10.822   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.62 dur=1006.43 max=18.34 min=15.30
02-28 21:31:11.828   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.26 max=21.20 min=12.24
02-28 21:31:12.834   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.81 max=17.02 min=16.43
02-28 21:31:13.840   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1005.96 max=16.93 min=16.50
02-28 21:31:14.846   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.01 max=17.09 min=16.53
02-28 21:31:15.852   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.04 max=16.96 min=16.49
02-28 21:31:16.858   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.10 max=17.02 min=16.57
02-28 21:31:17.864   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.88 max=17.04 min=16.54
02-28 21:31:18.870   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.86 max=16.94 min=16.59
02-28 21:31:19.876   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.94 max=16.91 min=16.52
02-28 21:31:20.882   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.21 max=17.10 min=16.46
02-28 21:31:21.888   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.13 max=17.04 min=16.52
02-28 21:31:22.894   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.03 max=17.06 min=16.48
02-28 21:31:23.901   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.24 max=17.04 min=16.48
02-28 21:31:24.906   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.67 dur=1005.53 max=16.92 min=16.53

ring-buffer = power_events_rb
eAGN00Fo0nEUwPG/WYkMTXcrJNnBQ4coojYoiGlJQ6eBKEPIosNiHWpuKGNjQRpUl42m26Ad6rCD
i05dihTKoJAOwx0klFgbbMFI6DBahHXIvYvvHd7j+cfbh8f393v/v9swH3T+260euXfIONx+jhr7
vwPtfywc9ob8l28MeYP+wauXgoa7TRMt81qHmiR6h1Bxar1ZQlNF+i6xhqgYUCVUnPrnxYVa51gi
TW8EEOUCTPuXlSWUmwrUEn+FpnIUVrA9X0WUCwD6i1Bxar2/VFfewN7ZVUS5ADiW+RymXADQTcP4
rQzozTkR5QLgBgKEcgFAvWXjo0P3FdjP9yAqBrgIFQMiw4sVZUBjfQVRMeDkN0zFgMd3Y5+VAT9m
byHKBcBiW4RyAUA9379uKAN8xSaiXAAsNkooFwC0dju1qQwIefKIigHXCBUDAjar3al7CQsjJxAV
A8qEigHl1lzsmC6g7/kyomJAkFAxYDjfHVcGJI+fQVQMyBAqBiQefhpXBrzp/YIoFwCvdoVQLgDo
pGXrSo9uBactfxEVA3yEigGPIv2jyoCbXTFEuQD4ttKEcgFAl56OJZUB8dwMolwA3OsIoVwA0L33
F6eUAcXXUUS5ADjWKqFcANCGa2BaGdB96jqiYsAgoWJANvXkvjJgfaKAKBcA9/qTUC4A6Mu3HzLK
AJujgagYMEQoFwArCOzsZpUBzxasDzpUDCgRygWY/gOeXhM2

ring-buffer = pkt_stats_rb

ring-buffer = connectivity_events_rb
eAGVmX9MlHUcxx+4O84Lve4CERFODjiBix9eqZMyR20lx/rPTlOTak5tsQKdjUINklmSQCfnjzWT
f4zKQWGFMOMaUaAQA12zokhha6tcDCmYuAHV83yfr3Vl0ft9jMGN1732+jz3/T678U1SDGGF941f
uH2vSYlQwtRv/WFUjOIXqxIufhrVv4UpSSr94JjhokbPmZU2qK/T6PWSNs9K33SXuILCjZXYNl4k
SpIljZUc2/a1cGeL7uDj16+620KvSZh6VbTnRjGlecWAoA3/mFKjbr2CD+XptDZ1hHKrW7vgRsWk
fmlXsEXS2jvw33SEMlfQW726W4Ho0cd0Ovx/aYdasrv+GzFlnJKg9mmzmdWijPWeTxPcc/5s00hT
q04uEKS2gjRSn1Qj9ddo5M4vQp2GWZwlo6FObXX+m1O7YoOmb0XnUjGVCqqP0PdOf35zzaU03HsJ
X/3eoXxBY6t/k6SxNVe08X2iJLbpA6Jk2tVMuHNzWgj3WkljU7rrW4kSJfUsURIlaazEXP0RURKx
LkiUxEoaKxkPfkyUHF7cTpTsbviEcEdt/oxwn+roJNyOJz4n3CfP9RLuzMV9hNvxSj/hDgQuEG77
/W0D+P2kdlmfoLH7SbeksVWVFa5cx0t+qrILGiuZkjRWsq5L6bTBnzCmysIFjZXMK9dprOS9Xw1E
yauDJqKkTtJYSZHXTJRca7QQJVc9kYT7/KZ5hPuKpLEpIzutRMmadBtRktxgJ9w1B6MJ93JjDOFe
UruIcHsm4gl3QYuDcPvSEwl3b46TcO/YfvQ8vosn+94RNLaL3f06ja0qR5mvBy+Jr94maKwkW9JY
ybs/DA7hJdtbfxY0VlIqaawkrXDXMF5S0f+coLES5/ISwt1V+gLhvixpbMrpK6VEye/L9hAlVSf2
Eu7ip8sI935JY1PODJcTJY0F+4iS24YrCHd+88uEe8J1gHBvaKgm3O2ZrxHulICfcKfYagl3nCtA
uGOsFqsd/uwzFBspaGxf/iJpbFUdODyXKKkasRIldZLGSioftRElaZfsRMnKVVGE+/Wl8wl3k6Sx
KUeOxRAlzQ/HEiXP9y4k3CWt8YTbe4+DcI/dSCbcN7a4CLe5Zwnh7s5PI9yXt6YT7sLpQ744eBc3
vnFS0Ngu7pI0tqreDNyxAS85440WNFgiaaxkVft8oqR7bAFRMihprOTMkwuJEqt7EVGSJGms5Kkj
8UTJrj4HUVIpaaykbXUiUXLouJMoufZbEuG+8zsX4R55IJVw5wYzCPeJtCzCHefPJtypkR7CneG4
i3DX1XTtxPd8+YqvBI3t+aOSxlZVypa3KrUS7H/rz979/RonfNd0mqYEjXWvlDTWnVOZW4SX9Fh8
gsZKEs7tKMbd9f5qQWPus5LGplRGV5fiJRNNjwgaK7Gc1mmsJN+XtwcvyZrcLGis5BlJYyX2U/5y
vKSj+G1BYyVfShormRzveAkvyZsZEDRWsl/SWMnB6PF9eMmLtZYKjcZKjksaK3GutQm3fs56+khN
3oc/hp7V/f2c1Z9pFzR2zuopSBS0fs76l3tGlWqnsrpbO2f9Ax6YJVY=

ring-buffer = firmware_prints_rb
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

ring-buffer = driver_prints_rb
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
vlxx/w8tTxU6

--------------------------------------------------------------------
--------------------------------------------------------------------
Bug dump 1
system time = 12-16 18:56:22.974
kernel time = 17777.922
reason = 2
kernel log: 


system log: 
--------- beginning of main
02-28 21:31:05.544   675   694 D BootAnimation: Get Carrier Animation file: (null) failed
02-28 21:31:05.544   675   694 I zipro   : ZipFileRO open:/system/media/bootanimation.zip
--------- beginning of system
02-28 21:31:05.649   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_create_key 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_update_key_user_info
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_wipe_key 
02-28 21:31:05.649   464   605 E         : get_tmp_passwd: Passed argument is NULL 
02-28 21:31:05.891   464   605 E QSEECOMAPI: SUCESS::ioctl call to create encryption key for usage 4 success with ret = 0
02-28 21:31:05.891   464   605 I Cryptfs : Ascii password was updated
02-28 21:31:05.912   464   468 V vold    : ----------------
02-28 21:31:05.912   464   468 V vold    : handleBlockEvent with action 1
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 I Cryptfs : target_type = req-crypt
02-28 21:31:05.912   464   605 I Cryptfs : real_blk_name = /dev/block/bootdevice/by-name/userdata, extra_params = fde_enabled ice
02-28 21:31:05.914   464   468 V vold    : ----------------
02-28 21:31:05.914   464   468 V vold    : handleBlockEvent with action 3
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.914   464   468 D vold    : Disk at 253:1 changed
02-28 21:31:05.914   464   605 D Cryptfs : Password is default - restarting filesystem
02-28 21:31:05.915   464   605 D Cryptfs : unmounting /data succeeded
02-28 21:31:05.998   582   618 D PerMgrSrv: modem new state: is on-line
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Worker thread started
02-28 21:31:06.103   574   712 I rmt_storage: wake lock name: rmt_storage_548256216144, name creation success: 24
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Worker thread started
02-28 21:31:06.103   574   713 I rmt_storage: wake lock name: rmt_storage_548255179856, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Client found
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   714 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsg: Worker thread started
02-28 21:31:06.104   574   714 I rmt_storage: wake lock name: rmt_storage_548254143568, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Client found
02-28 21:31:06.104   574   715 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsc: Worker thread started
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   715 I rmt_storage: wake lock name: rmt_storage_548253107280, name creation success: 24
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Alloc request received: Size: 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: New client making a dummy request with buffer req size 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Send response: res=0 err=0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs1: req_h=0x6 msg_id=3: R/W request received
02-28 21:31:06.106   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.106   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Unblock worker thread (th_id: 548256216144)
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Bytes read = 512
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: About to block rmt_storage client thread (th_id: 548256216144) wakelock released: 1, error no: 0
02-28 21:31:06.109   574   712 I rmt_storage: 
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x7 msg_id=3: R/W request received
02-28 21:31:06.110   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Bytes read = 512
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.110   574   713 I rmt_storage: 
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x8 msg_id=3: R/W request received
02-28 21:31:06.112   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.112   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Bytes read = 1571840
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.118   574   713 I rmt_storage: 
02-28 21:31:06.118   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.167   576   576 E         : bt_qmi_dms_init :Error while Initializing qmi_client_init_instance: -3
02-28 21:31:06.167   576   576 E         : QMI initialization failed in 2 try...
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_os_wakelocks_la.c, 304] Wake-lock acquired successfully
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_server.c, 597] Server waiting for client-connection.
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server_utils.c, 101] Normalized file [/readwrite/server_check.txt] to : [/system/rfs/msm/mpss/readwrite/server_chec
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 721] Using temp file[/system/rfs/msm/mpss/readwrite/server_check.txt.rfs_tmp] for atomic put
02-28 21:31:06.194   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1301] Sent last ACK: *Waiting* on reply.
02-28 21:31:06.793   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.03 dur=1016.11 max=18.42 min=13.46
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1310] Success! No timeout detected on last ACK
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1267] WRQ stats : total-blocks = 1 : total-bytes = 5 : 5 timedout-pkts = 0, wrong-pkts = 0
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1283] WRQ was successfully processed.
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1514] WRQ stats in us:
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1515] Total API = 1558601
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1517] Total-TX = 99
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1519] Total-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1521] Max-TX = 50
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1523] Max-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1525] Min-TX = 49
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1527] Min-RX = 185
02-28 21:31:07.747   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_os_wakelocks_la.c, 334] Wake-lock released successfully
02-28 21:31:07.800   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=58.58 dur=1007.09 max=35.66 min=13.93
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: addr_resp.mac_address_valid 1 addr_resp.mac_address_len 6 
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: Succesfully Read BT address
02-28 21:31:08.169   576   576 E         : Read MODEM_NV_BD_ADDR 0x2b 0x1d 0xc2 0x31 0x57 0x2c 
02-28 21:31:08.804   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.76 dur=1004.06 max=17.31 min=15.50
02-28 21:31:09.816   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.28 dur=1012.03 max=17.35 min=7.01
02-28 21:31:10.822   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.62 dur=1006.43 max=18.34 min=15.30
02-28 21:31:11.828   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.26 max=21.20 min=12.24
02-28 21:31:12.834   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.81 max=17.02 min=16.43
02-28 21:31:13.840   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1005.96 max=16.93 min=16.50
02-28 21:31:14.846   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.01 max=17.09 min=16.53
02-28 21:31:15.852   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.04 max=16.96 min=16.49
02-28 21:31:16.858   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.10 max=17.02 min=16.57
02-28 21:31:17.864   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.88 max=17.04 min=16.54
02-28 21:31:18.870   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.86 max=16.94 min=16.59
02-28 21:31:19.876   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.94 max=16.91 min=16.52
02-28 21:31:20.882   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.21 max=17.10 min=16.46
02-28 21:31:21.888   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.13 max=17.04 min=16.52
02-28 21:31:22.894   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.03 max=17.06 min=16.48
02-28 21:31:23.901   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.24 max=17.04 min=16.48
02-28 21:31:24.906   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.67 dur=1005.53 max=16.92 min=16.53

ring-buffer = power_events_rb
eAGN00Fo0nEUwPG/WYkMTXcrJNnBQ4coojYoiGlJQ6eBKEPIosNiHWpuKGNjQRpUl42m26Ad6rCD
i05dihTKoJAOwx0klFgbbMFI6DBahHXIvYvvHd7j+cfbh8f393v/v9swH3T+260euXfIONx+jhr7
vwPtfywc9ob8l28MeYP+wauXgoa7TRMt81qHmiR6h1Bxar1ZQlNF+i6xhqgYUCVUnPrnxYVa51gi
TW8EEOUCTPuXlSWUmwrUEn+FpnIUVrA9X0WUCwD6i1Bxar2/VFfewN7ZVUS5ADiW+RymXADQTcP4
rQzozTkR5QLgBgKEcgFAvWXjo0P3FdjP9yAqBrgIFQMiw4sVZUBjfQVRMeDkN0zFgMd3Y5+VAT9m
byHKBcBiW4RyAUA9379uKAN8xSaiXAAsNkooFwC0dju1qQwIefKIigHXCBUDAjar3al7CQsjJxAV
A8qEigHl1lzsmC6g7/kyomJAkFAxYDjfHVcGJI+fQVQMyBAqBiQefhpXBrzp/YIoFwCvdoVQLgDo
pGXrSo9uBactfxEVA3yEigGPIv2jyoCbXTFEuQD4ttKEcgFAl56OJZUB8dwMolwA3OsIoVwA0L33
F6eUAcXXUUS5ADjWKqFcANCGa2BaGdB96jqiYsAgoWJANvXkvjJgfaKAKBcA9/qTUC4A6Mu3HzLK
AJujgagYMEQoFwArCOzsZpUBzxasDzpUDCgRygWY/gOeXhM2

ring-buffer = pkt_stats_rb


--------------------------------------------------------------------
--------------------------------------------------------------------
Bug dump 2
system time = 12-17 18:26:17.262
kernel time = 52730.631
reason = 7
kernel log: 


system log: 
--------- beginning of main
02-28 21:31:05.544   675   694 D BootAnimation: Get Carrier Animation file: (null) failed
02-28 21:31:05.544   675   694 I zipro   : ZipFileRO open:/system/media/bootanimation.zip
--------- beginning of system
02-28 21:31:05.649   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_create_key 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_update_key_user_info
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_wipe_key 
02-28 21:31:05.649   464   605 E         : get_tmp_passwd: Passed argument is NULL 
02-28 21:31:05.891   464   605 E QSEECOMAPI: SUCESS::ioctl call to create encryption key for usage 4 success with ret = 0
02-28 21:31:05.891   464   605 I Cryptfs : Ascii password was updated
02-28 21:31:05.912   464   468 V vold    : ----------------
02-28 21:31:05.912   464   468 V vold    : handleBlockEvent with action 1
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 I Cryptfs : target_type = req-crypt
02-28 21:31:05.912   464   605 I Cryptfs : real_blk_name = /dev/block/bootdevice/by-name/userdata, extra_params = fde_enabled ice
02-28 21:31:05.914   464   468 V vold    : ----------------
02-28 21:31:05.914   464   468 V vold    : handleBlockEvent with action 3
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.914   464   468 D vold    : Disk at 253:1 changed
02-28 21:31:05.914   464   605 D Cryptfs : Password is default - restarting filesystem
02-28 21:31:05.915   464   605 D Cryptfs : unmounting /data succeeded
02-28 21:31:05.998   582   618 D PerMgrSrv: modem new state: is on-line
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Worker thread started
02-28 21:31:06.103   574   712 I rmt_storage: wake lock name: rmt_storage_548256216144, name creation success: 24
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Worker thread started
02-28 21:31:06.103   574   713 I rmt_storage: wake lock name: rmt_storage_548255179856, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Client found
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   714 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsg: Worker thread started
02-28 21:31:06.104   574   714 I rmt_storage: wake lock name: rmt_storage_548254143568, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Client found
02-28 21:31:06.104   574   715 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsc: Worker thread started
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   715 I rmt_storage: wake lock name: rmt_storage_548253107280, name creation success: 24
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Alloc request received: Size: 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: New client making a dummy request with buffer req size 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Send response: res=0 err=0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs1: req_h=0x6 msg_id=3: R/W request received
02-28 21:31:06.106   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.106   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Unblock worker thread (th_id: 548256216144)
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Bytes read = 512
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: About to block rmt_storage client thread (th_id: 548256216144) wakelock released: 1, error no: 0
02-28 21:31:06.109   574   712 I rmt_storage: 
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x7 msg_id=3: R/W request received
02-28 21:31:06.110   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Bytes read = 512
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.110   574   713 I rmt_storage: 
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x8 msg_id=3: R/W request received
02-28 21:31:06.112   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.112   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Bytes read = 1571840
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.118   574   713 I rmt_storage: 
02-28 21:31:06.118   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.167   576   576 E         : bt_qmi_dms_init :Error while Initializing qmi_client_init_instance: -3
02-28 21:31:06.167   576   576 E         : QMI initialization failed in 2 try...
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_os_wakelocks_la.c, 304] Wake-lock acquired successfully
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_server.c, 597] Server waiting for client-connection.
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server_utils.c, 101] Normalized file [/readwrite/server_check.txt] to : [/system/rfs/msm/mpss/readwrite/server_chec
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 721] Using temp file[/system/rfs/msm/mpss/readwrite/server_check.txt.rfs_tmp] for atomic put
02-28 21:31:06.194   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1301] Sent last ACK: *Waiting* on reply.
02-28 21:31:06.793   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.03 dur=1016.11 max=18.42 min=13.46
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1310] Success! No timeout detected on last ACK
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1267] WRQ stats : total-blocks = 1 : total-bytes = 5 : 5 timedout-pkts = 0, wrong-pkts = 0
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1283] WRQ was successfully processed.
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1514] WRQ stats in us:
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1515] Total API = 1558601
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1517] Total-TX = 99
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1519] Total-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1521] Max-TX = 50
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1523] Max-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1525] Min-TX = 49
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1527] Min-RX = 185
02-28 21:31:07.747   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_os_wakelocks_la.c, 334] Wake-lock released successfully
02-28 21:31:07.800   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=58.58 dur=1007.09 max=35.66 min=13.93
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: addr_resp.mac_address_valid 1 addr_resp.mac_address_len 6 
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: Succesfully Read BT address
02-28 21:31:08.169   576   576 E         : Read MODEM_NV_BD_ADDR 0x2b 0x1d 0xc2 0x31 0x57 0x2c 
02-28 21:31:08.804   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.76 dur=1004.06 max=17.31 min=15.50
02-28 21:31:09.816   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.28 dur=1012.03 max=17.35 min=7.01
02-28 21:31:10.822   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.62 dur=1006.43 max=18.34 min=15.30
02-28 21:31:11.828   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.26 max=21.20 min=12.24
02-28 21:31:12.834   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.81 max=17.02 min=16.43
02-28 21:31:13.840   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1005.96 max=16.93 min=16.50
02-28 21:31:14.846   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.01 max=17.09 min=16.53
02-28 21:31:15.852   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.04 max=16.96 min=16.49
02-28 21:31:16.858   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.10 max=17.02 min=16.57
02-28 21:31:17.864   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.88 max=17.04 min=16.54
02-28 21:31:18.870   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.86 max=16.94 min=16.59
02-28 21:31:19.876   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.94 max=16.91 min=16.52
02-28 21:31:20.882   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.21 max=17.10 min=16.46
02-28 21:31:21.888   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.13 max=17.04 min=16.52
02-28 21:31:22.894   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.03 max=17.06 min=16.48
02-28 21:31:23.901   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.24 max=17.04 min=16.48
02-28 21:31:24.906   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.67 dur=1005.53 max=16.92 min=16.53

ring-buffer = power_events_rb
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

ring-buffer = pkt_stats_rb

ring-buffer = connectivity_events_rb
eAGV3XlUjvkfxvFHm6VpJGuSQxEaS8oyjGislWhSSqLMFEqmiSFb2SKk1DRFQhFKJiGylEnMxGTL
FiHZd7JMRKl+474/f//OdfmHc7zP1+s+9+X7xDkOM412g8pHJ29Yh+lqGmoaaPQ06jcdjY7yg681
Wsr3Ov/9XAON2X/1r15/K7UeVLcLL1TqRv+31v7vV/ly9rVzlcTZ2sm6N7+4sbOtpMaeMi6lr3I2
9pST1owiJGukxiSP3OcTks3LIwnJWakxSfiHo4Ska58iQvKd1Jik4VcfCYndpoa38J24SI1Jgqb0
U87GdpLQbxQhSZMak5zwCyEkMTcjCEmq1JhkcGEOIfF/eJiQhEmNSSr8jhKSyhZ5hES3pVpjkuTE
Y4RkcEI+IXGVGpPEmRQQkhaVJwhJV6kxyaJ5fxGStJ8LCcmxYLXGJA0+nSIkOb5FhOSU1Jhk2Zkz
hOSIVzEhOSs1Jmnx8CIhOd/+CiEplxqTJEdfJSRXN5QQkvvbdcu+fDZg9/cfHo2UGvuKI19q7Clj
ihsTEutMfULiJDUm+cvSgJCYWTUlJP2lxiQ/7jEkJEsHGxGSQbnNibN1slsSZ5tKjT3lKtvWhGRE
XFtC4i41JtlYZUJIrGZ1JCTDpMYkWtfMCMm4z50Iia/UmMR8iQUh2X6gCyEZ4uFBnL0w3584O01q
7CmnOScSkuoruwmJ1VW1xiT6HjcJyfD654RkptSYpOSi8W38s6HdeEulxj4b+koNSjImEpIneoGE
pEZqTOJ9NImQ7O2TSUhuS41J+peVEZIJzm8IyRGpMUmxeYdyfCdr1tooNbaTXVJjkjtmUwmJcew8
QuIqNSa59E8WIVlgmE9ICqXGJOE9XhGS37zeEJJMqTFJ5+tvCUmfHpWE5EepMUnpzveExCa8ipAM
lxqTRGt9IiSnetcQkttSYxKdzM+EJO51HSHRfqPWmGT3Is0d/D6Z76ut1OB9IjUm6f9Uh5CMT29M
SKKkxiQPu+sTkvWbDQjJJakxSU6HpoTk83RDQjL+Kzvi7OrPzsTZFrVqjT3lFa2lhGSPfQwhyZca
k9xfm09IHHIuEZJZUmMS6zFad/Hfl5Gdmys19vtyk9SYJOjcUELS6IA7ITGWGpN8TFpJSFrlJRAS
S6kxyfuWhYTkxK3rhKRMakyibdrwHr6T0PI2So3tJEdqTOLvZ09I9iY5EpLTUmOSrFZOhOTluTGE
pE5qTKI/2pmQ1Jq6EJKm7dUak3injSMkdovcCMkEqTHJ2lvjCcm+dx6E5KTUmOTMZE9C0vneJELi
JjUmOeLvTUj8G/1ESNZLjUnuJvoSkrzaqYTksdSY5MZv0wmJR4I/IWk7IJE4+8K6JOLsu1JjTxlt
sJmQ2LzcQkhGSI1J/GamEJIOwdsIybdSYxLzF6mE5FzFDkJSLjUmeRuYRkgspu4iJEOkxiQPX2cQ
kg77MwmJtdSYZFPfLEKy42U2ITkkNSbZEHKQkKw2OkxINkqNSbTXHyEkpa+PEhKrgGfE2bEfqomz
x1WpNfaUj1Za3se/1gyJGajU2Neaq6XGJEsWBBCSZmELCckOqTHJPKvdhCRbk0dICqXGJM41zwiJ
54cqQhIoNSYp8OjyAN/J5M8DlRrbSajUmMQuegYhCfgYRkiypMYkOpF7CEldzwJC0qeXWmOSwq/f
EJIGx+sJiZHUmGRpaa+H+E5a+QxRamwn2VJjktjYIELS3nAWIRkvNSaZu2Y2IXnXYi4hsWyp1pik
Mj2EkBQYLCAkVVJjkoNbFhKSiA5hhCRPakxyOX4xITGuX0pIukuNScZELickWd0jCMlZqTFJfe4q
QvKyYxQh0ZipNSaZsjqakDw8FEtIqqTGJNuOHyck52dcIiSPpMYkhrc0j/A79lOQoVJjd6z+L2qN
SRoZ2BGSnDwXQnJaakwyd84yQtKrdQIh6W2s1phk1+m/CYn7oxJCkis1Jhm7XfcxvpM53VspNbaT
LKlBifZIQuKm70BIoqTGJC1DHQlJ/SInQtIsVK0xiW3tGEIyp8aZkKySGpMMWOJCSPb4uhKS41Jj
klk33QhJMwdvQmIuNSap+y6akAydv4WQuEqNSUaVnCck80PuE5JIqTHJzfhmT/D75E/rTkqN3Scv
pMYkhQ9cCcmq0qmE5IjUmGRFRgIhMe6cTkgcpcYkjjOvE5KmmU8ISUepMUnxw1ZP8Z1csuii1NhO
nkuNSdz13QnJjoFTCcllqTFJ6et4QhJwcwMhCZcak1Q6bSQk7jmbCEmM1JgkZMQWQrLLI4WQlEmN
SX4+tZWQuKalEpJlUmOS5uY7CEl1cBoh6TdLrTGJaU06IQmq2U1INkmNSexXZRISn6D9hGSd1Jhk
2ptsQpIxIYeQ5EmNSZLyDxGSYecPE5KUt4+Js/PCq4mz3VaoNfaUeW6Wz/BPkicetkqNfZIMmqDW
mOTiuZmE5ITeYkJyWWpM4jloDyGZ/30BIUmXGpMMtXhDSEoLtZ5/eZfY29E7pdaYxN/OWjkb+1d4
dmuGERJXqTFJ16hfCcmp/JWE5OxxtcYkdgE5hMTb+wwhiZYakyy794mQmId99QLfiYfUmMRo+SDl
bGwne1OdCclTqTFJaZslhCTFJI6QFEuNSeqenyAktplXCckvUmOSkR46L/Hbfn5ma6XG7pMsqTGJ
vtcoQnIuyouQRK1Ta0xyculaQtI4eBMhaS81JjkSe5aQNGhym5C0lBqT7NUxeIXvZItFe6XGdpIj
NSZZeGIMIdmwyoeQFEmNSQ75xBAS/axUQjJSakziZnuZkMw+9ICQxEiNSTTWzSvwnUQ17qTU2E62
SI1JRgS6EhLbJD9C4iI1JmmY+DshyYpPIySPpcYkz6OvEZKu2U8ZidSYpOWT1q/xnfy0rZtSYzsp
TVVrTFJs4klIjObOICQWUmOSqJSNhCSgwy5CskVqTOKee52QNL70nJA4SI1JMna2fYPv5JvM7kqN
7WSI1JhE74MXIXH3CCYk66TGJFvLUgiJ1qH9hMRKakwy3OABIQluW0lIlkmNSY56mb/FdxK62Uap
sZ1ES41JMgb4EhKT6rmEpLfUmMRp+k5C8k/sYUJSJjUmSdN/Qkg+Zn8iJL0OqDUm0d/W7R2+k2Kb
75Qa24luH7XGJFezAglJ04jFhGSQ1JikrVkWIfHfU0BIkqXGJIkz3xCSrcXa/355l9jbuSY1Jrl5
x0Y5G/tbi4RTIwlJkdSYJD47hJA8nR5JSGID1BqT/ByYS0jWeZwnJMekxiS9Hn8mJK88DSvxnXSa
qNaYZKSNnXI2tpMzLi6EpFpqTNLv36WEZEBFHCFJlxqTrI47SUiWWF8nJAelxiQv7ui9x297m11t
lBq7T+ZkqDUmCVlkT0h+GOdFSCKlxiTePdcSEtsJyYQkRGpM0snnAiHpZnqfkEySGpMcPNvsA76T
oeFmSo3txFVqTDLRfBwhudhxKiGxNFNrTOJ0KJ6QDGyTTkiCpcYk6bevEZKDw18QkgqpMcm7aJMq
fCdNtn6j1NhOOkmNSdaZTiIk/xQFERK9M2qNSdrNSyYkS/WzCcl+qTFJXeA9QlKm9ZaQvJIak+jd
6/AR38nwLn2VGtvJIqkxyeL9foSkietcQmIiNSYpDN1JSOYXHSEkSVJjkoCTTwnJFq86QnJAakxy
y7jnJ3wny87aKTW2k7vFao1JHCuCCUmtSzghsR6n1pjEr90BQjKq4CQhmS41Jkn5/R0hMfpDr/rL
u8TezmCpMUmUYz/lbOxPGQ32jiIk+/apNSYxCptHSNKyIwlJudSY5F1uLiG5ZnuBkKQMVmtMMrOm
lpAYhRvW4DsZJTUmuRdtp5yN7SQ32o2QvJYak/iOWkFIvv4hnpAMkRqTWC//m5CMrr9KSCZLjUmG
ttD9jN/2NYvbKDV2n/RdotaYpLKLPSHpGjeRkMxMUGtMsjIykpAkLd9ESF5KjUn2tTpLSBy2lxMS
L6kxSeJlg1p8J2vOdVRqbCe5UmMSnzAXQhIY50tIMqTGJBcjfyck5lk7CUmTvWqNSezXlhCSksdP
CMnXT9Qak7iltK7Dd/LLCEulxnZyTmpM0q/Uk5AMWx1ASFylxiRNp20kJN/HZRKSOVJjEvM2ZYSk
YkAFIdEMVGtMMjnCtB7fyYPzvZQa20mLC2qNSRpbTiEkWl6zCcm3UmOSLm1TCckVpxxC0nCMWmOS
R36PCEnErSpCkic1Jpk1qYvGBv5/VQKX91dqbCcrpMYkA1OmE5KeyQsJyWSpMcmLabsJSb/zxwjJ
dKkxicbnJSHpcaWekPx6Q60xyeMFvRrgO4npNlSpsZ1skxqTOI2fTUhKHFYQEitHtcYkFm8PEJIe
eacJia/UmKRsYhUh6Tm9idaXd4m9nR+lxiSX2g5Uzsb+NPpMx4mQmOqqNSZJ7hVKSBqviCIk7aXG
JLcr8giJ++gLhGSt1Jjk2o1aQuIf1FQb30mE1JjkkMcQ5WxsJze2jiMkRtvUGpMUpSwnJE0GxROS
4VJjkk7v/iIkO4pLCMlDqTGJfZauDn7b10a0UWrsPum/Sq0xSe9B9oQk3XgiIRliotaYxLhuDSGJ
6JZESBKlxiQF14sIydyYckISKzUmeVVkoIvv5FNBe6XGdtLkhFpjknbfjCUkY8unEJIIqTGJw9RY
QmIUnkpIHKTGJLejHUvwt/M+3UmpsbfTcJdaY5LTVmMJyW93nQnJTqkxyUs/F0KS2d+VkORLjUkc
NroRktAZ7oQkRmpMsvKCByH5c70nISmWGpO00PYiJJ5a3oQkUGpMEhHkQ0j63/MjJGOkxiQtxkwj
JKOz/QnJFKkxSUfTGYREYx4ISv4HDsUxDA==

ring-buffer = firmware_prints_rb
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

ring-buffer = driver_prints_rb
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

--------------------------------------------------------------------
--------------------------------------------------------------------
Bug dump 3
system time = 12-17 18:26:45.341
kernel time = 52758.711
reason = 7
kernel log: 


system log: 
--------- beginning of main
02-28 21:31:05.544   675   694 D BootAnimation: Get Carrier Animation file: (null) failed
02-28 21:31:05.544   675   694 I zipro   : ZipFileRO open:/system/media/bootanimation.zip
--------- beginning of system
02-28 21:31:05.649   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_create_key 
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_update_key_user_info
02-28 21:31:05.649   464   605 D         : Success loading QSEECom_wipe_key 
02-28 21:31:05.649   464   605 E         : get_tmp_passwd: Passed argument is NULL 
02-28 21:31:05.891   464   605 E QSEECOMAPI: SUCESS::ioctl call to create encryption key for usage 4 success with ret = 0
02-28 21:31:05.891   464   605 I Cryptfs : Ascii password was updated
02-28 21:31:05.912   464   468 V vold    : ----------------
02-28 21:31:05.912   464   468 V vold    : handleBlockEvent with action 1
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.912   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 D         : HW based disk encryption is enabled 
02-28 21:31:05.912   464   605 I Cryptfs : target_type = req-crypt
02-28 21:31:05.912   464   605 I Cryptfs : real_blk_name = /dev/block/bootdevice/by-name/userdata, extra_params = fde_enabled ice
02-28 21:31:05.914   464   468 V vold    : ----------------
02-28 21:31:05.914   464   468 V vold    : handleBlockEvent with action 3
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVPATH=/devices/virtual/block/dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MAJOR=253'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'MINOR=1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVNAME=dm-1'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'DEVTYPE=disk'
02-28 21:31:05.914   464   468 D NetlinkEvent: NL param 'NPARTS=0'
02-28 21:31:05.914   464   468 D vold    : Disk at 253:1 changed
02-28 21:31:05.914   464   605 D Cryptfs : Password is default - restarting filesystem
02-28 21:31:05.915   464   605 D Cryptfs : unmounting /data succeeded
02-28 21:31:05.998   582   618 D PerMgrSrv: modem new state: is on-line
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs1: req_h=0x1 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Worker thread started
02-28 21:31:06.103   574   712 I rmt_storage: wake lock name: rmt_storage_548256216144, name creation success: 24
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x1 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Client found
02-28 21:31:06.103   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fs2: req_h=0x2 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.103   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Worker thread started
02-28 21:31:06.103   574   713 I rmt_storage: wake lock name: rmt_storage_548255179856, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x2 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Client found
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsg: req_h=0x3 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   714 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsg: Worker thread started
02-28 21:31:06.104   574   714 I rmt_storage: wake lock name: rmt_storage_548254143568, name creation success: 24
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x3 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Client found
02-28 21:31:06.104   574   715 I rmt_storage: rmt_storage_client_thread: /boot/modem_fsc: Worker thread started
02-28 21:31:06.104   574   574 I rmt_storage: rmt_storage_open_cb: /boot/modem_fsc: req_h=0x4 msg_id=1: Send response: res=0 err=0
02-28 21:31:06.104   574   715 I rmt_storage: wake lock name: rmt_storage_548253107280, name creation success: 24
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x4 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Alloc request received: Size: 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: New client making a dummy request with buffer req size 0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_alloc_buff_cb: /boot/modem_fs1: req_h=0x5 msg_id=4: Send response: res=0 err=0
02-28 21:31:06.105   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x5 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.106   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs1: req_h=0x6 msg_id=3: R/W request received
02-28 21:31:06.106   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.106   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: Unblock worker thread (th_id: 548256216144)
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Bytes read = 512
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: req_h=0x6 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.109   574   712 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs1: About to block rmt_storage client thread (th_id: 548256216144) wakelock released: 1, error no: 0
02-28 21:31:06.109   574   712 I rmt_storage: 
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x6 conn_h=0x7fa6c2a000
02-28 21:31:06.109   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x7 msg_id=3: R/W request received
02-28 21:31:06.110   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Bytes read = 512
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x7 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.110   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.110   574   713 I rmt_storage: 
02-28 21:31:06.110   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x7 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_connect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.112   574   574 I rmt_storage: rmt_storage_rw_iovec_cb: /boot/modem_fs2: req_h=0x8 msg_id=3: R/W request received
02-28 21:31:06.112   574   574 I rmt_storage: wakelock acquired: 1, error no: 42
02-28 21:31:06.112   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: Unblock worker thread (th_id: 548255179856)
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Bytes read = 1571840
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: req_h=0x8 msg_id=3: Send response: res=0 err=0
02-28 21:31:06.118   574   713 I rmt_storage: rmt_storage_client_thread: /boot/modem_fs2: About to block rmt_storage client thread (th_id: 548255179856) wakelock released: 1, error no: 0
02-28 21:31:06.118   574   713 I rmt_storage: 
02-28 21:31:06.118   574   574 I rmt_storage: rmt_storage_disconnect_cb: clnt_h=0x8 conn_h=0x7fa6c2a000
02-28 21:31:06.167   576   576 E         : bt_qmi_dms_init :Error while Initializing qmi_client_init_instance: -3
02-28 21:31:06.167   576   576 E         : QMI initialization failed in 2 try...
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_os_wakelocks_la.c, 304] Wake-lock acquired successfully
02-28 21:31:06.188   575   575 D tftp_server: pid=575 tid=575 tftp-server : DBG :[tftp_server.c, 597] Server waiting for client-connection.
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server_utils.c, 101] Normalized file [/readwrite/server_check.txt] to : [/system/rfs/msm/mpss/readwrite/server_chec
02-28 21:31:06.188   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 721] Using temp file[/system/rfs/msm/mpss/readwrite/server_check.txt.rfs_tmp] for atomic put
02-28 21:31:06.194   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1301] Sent last ACK: *Waiting* on reply.
02-28 21:31:06.793   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.03 dur=1016.11 max=18.42 min=13.46
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_protocol.c, 1310] Success! No timeout detected on last ACK
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1267] WRQ stats : total-blocks = 1 : total-bytes = 5 : 5 timedout-pkts = 0, wrong-pkts = 0
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1283] WRQ was successfully processed.
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1514] WRQ stats in us:
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1515] Total API = 1558601
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1517] Total-TX = 99
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1519] Total-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1521] Max-TX = 50
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1523] Max-RX = 185
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1525] Min-TX = 49
02-28 21:31:07.746   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_server.c, 1527] Min-RX = 185
02-28 21:31:07.747   575   716 D tftp_server: pid=575 tid=716 tftp-server : DBG :[tftp_os_wakelocks_la.c, 334] Wake-lock released successfully
02-28 21:31:07.800   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=58.58 dur=1007.09 max=35.66 min=13.93
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: addr_resp.mac_address_valid 1 addr_resp.mac_address_len 6 
02-28 21:31:08.169   576   576 E         : qmi_dms_get_bt_address: Succesfully Read BT address
02-28 21:31:08.169   576   576 E         : Read MODEM_NV_BD_ADDR 0x2b 0x1d 0xc2 0x31 0x57 0x2c 
02-28 21:31:08.804   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.76 dur=1004.06 max=17.31 min=15.50
02-28 21:31:09.816   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=60.28 dur=1012.03 max=17.35 min=7.01
02-28 21:31:10.822   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.62 dur=1006.43 max=18.34 min=15.30
02-28 21:31:11.828   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.26 max=21.20 min=12.24
02-28 21:31:12.834   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.81 max=17.02 min=16.43
02-28 21:31:13.840   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1005.96 max=16.93 min=16.50
02-28 21:31:14.846   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.01 max=17.09 min=16.53
02-28 21:31:15.852   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.04 max=16.96 min=16.49
02-28 21:31:16.858   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.10 max=17.02 min=16.57
02-28 21:31:17.864   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.88 max=17.04 min=16.54
02-28 21:31:18.870   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.86 max=16.94 min=16.59
02-28 21:31:19.876   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.65 dur=1005.94 max=16.91 min=16.52
02-28 21:31:20.882   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.21 max=17.10 min=16.46
02-28 21:31:21.888   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.13 max=17.04 min=16.52
02-28 21:31:22.894   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.64 dur=1006.03 max=17.06 min=16.48
02-28 21:31:23.901   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.63 dur=1006.24 max=17.04 min=16.48
02-28 21:31:24.906   585   636 I BufferQueueProducer: [BootAnimation](this:0x7f9b966800,id:1,api:1,p:675,c:585) queueBuffer: fps=59.67 dur=1005.53 max=16.92 min=16.53

ring-buffer = power_events_rb
eAGN3FlUluUaxnHAAYdAUHHIEXNIU3FJUuKwHcNEKZxxbqsFWUmO5S4VQTQUtZAS3UJqhorpVitT
V0hKGiGSpuCAOKSiouacOO66O+C6D+6rt1Znv3Wt//s8z8dhDVzKlPV+eDO3yoflXMr/+W9tl7/+
cfvzv4EhIV379uwxanDX4J59Xuke7NLgTzrifplfSqkro28rSlePFu+EVUrTR/wClAbkKkpX763r
cLj0syidcqo3UCvA9a/Dmq2otSrUPWwzrFpUruDcklygVoDQ24rS1aNddh51eAJ3ns8BagXIZ5Vp
h9QKEHraxeWuw4CAT7yBWgFyAr0VtQKEds1wyfRy9ivwDPQFSgPqKkoD+o37dJ/DgGMn1wKlAa0K
kdKAee8MzHIYcHnh60CtALnY+4paAUKbnD9xymFAtx3FQK0AudgBiloBQg9HTD7tMKBvkwSgNGC4
ojSgt0dFT29njzA1vDlQGpChKA3IuP/xwKedBbyQshooDQhWlAaMS6ga5jAgsn5boDQgSlEaMCL2
x4kOA7YF5AG1AuRp71PUChD6vvtvQb7OrqCN+wOgNKCbojRgbr8uExwGvFZ5IFArQH5bUxS1AoQu
S3o30mFA2CfxQK0AOddwRa0AoXd2dZ7uMGDHtwOAWgHyWTmKWgFCj9Xt9YHDgKp+I4HSgD6K0oDZ
kxfPchhwcloqUCtAzvWaolaA0LTtP0Q5DPDwOgaUBgxW1AqQK+h98eZshwHJiRWjSykN2KmoFSAn
8Pb49k5XkxSlqz98MbxqI2d/XeJORAC1PksOK0lRK0BorYRbjZ5xFnA++jZQK0AOa2oMUitAaNeS
2bccBqzrsRKoFSCftU1RK0Dopszx3o2dnUDhzPlAaUDFWUhpQPLNDp0dBmSuCgZqBci55ilqBQhN
dff/wmFAYcNQoFaAnOsVRa0AobMjx7o2cXYFtdKigdKAporSgIsdr1dyGDC1s1vlUmoFyLnOU9QK
ELr/85yjbZ2dQO9uBUCtADnXIYpaAULXpj6AVUqH76l7rLSVBoxXlK5erj4QVin1+XYSUBrQVFG6
Guy1GlYp9Y37ESgN6KAoXU3Ovg2rlIZllzvu8ApiFaWrkcntYJXS4s1BQOkJVN+ClK5G9H8PVikt
2hoHlAZ4fo2Urv777nZYpTSsZxZQGhCuKF39odI9WKV0aJr7iX9+A/LXJUJRa1XoC6MDYNWi8icj
s0cQUOsEZPWQotaq0GVjp8KqRSXg4OVYoFaA0LOK0lWfH7+BVUo7rzwClAaEKEpXk1aVKyi9WErP
7mwGlAbcUJSuVu0/GFYpjS0XAZQGfKsoXf01ZCmsUupfNg0oDRirKF09P+g4rFLa06MYqBUgT3ug
otaq0Eu5tU86fAOHZrQAagXIr6BFHFIrQOieNUNhldLlPuOBWgHyWesVtVaFPt62DFYtKq27ozYA
tQKE/q4oXb1/tABWKT0WfR0oDXCLQUpX/es2LHT4Brav8gdqBci5ZitqBQhtW3ccrFpUzvXQvmlA
acBjRa1VCUjduxFWLSoBub7pQK0AoacVpasVW16FVUqvzvQ6VXpbNKDOLKR09ctKXWCV0hNtXwVq
Bci5VvBHaq0KdXOdBasW/fu3NXgRUCtAqOsQpHR1d1w6rFKaeOEgUCtAPmuPotaq0CnBbqdLL9ai
8lmTXqwG1AoQGqUoXd2b3Q1WKb2/ZxBQGlAxEyldTVs6B1YpHVaYCJQGTFGUrkb6/AirlD77KB8o
DeiuKF3Nrut+xuEbmOZRG6gVIC9rg6JWgNDlY3rBqkXlZaWkhQO1AoTmK0pX+wYshVVKXUq2A6UB
VRSlq+vDL8EqpZf8HgClAT5tkNLVg9Etzjp8AxkpgUCtALnYA4paAUJvvxcBqxaVi7395XSgNKBB
KlJrVQLW+a2HVYtKQNhTO4HSgEhFrVUJeHD/EqxaVAJyy94DSgNOKWqtSsDnfZv95vANvNCgA1Ar
QFoHKGoFCM2Z/yasUnq3wwygNKBuR6R0tXvsV7BK6YNRGUBpQJPRSOnq2krXYZXS7NwnQK0Audgi
Relqcb7fOYdvIPndfwG1AuRiNypKA2ovfgdWKc1LXwzUCpATuKkoXb2avgtWKe374UGgVoCcwChF
6erK4y7nHV7BgpleQK0AOYEURa0AoQee6gKrFpXP6pgXCtQKEBqiKF0NmBQFq5RWfTkRKA3orChd
bbg3E1Ypza2SB5QGFClKV6eklLvg8A3cnVQDKA1oNhkpDTjq+hKsUjpywEigVoC8rLcUtVaF7g2M
h1WLystK+mgFUBqwUVFrVQLuHc6BVYtKwLtzzwK1AoRGKUpXI5Z4Fzl8A52GNwZKA8YoSgNqHe8P
q5SOrvQ6UCtAznWRotaq0D/WJsKqReVcXwpLBWoFCJ2gKF2t+VY+rFIasLcIKA3ooyhdrXauxkWH
b+CRfzOgVoCcq8fzSK0AofUrD4JVi8q59pgwDqgVIDRSUboae2kJrFLa58g2oDRghKJ0taT4AqxS
mvzdfaBWgJxrlqLWqtDmA1pccvgGUmM7AbUC5Ap+UdQKEPq//W/BKqW3aswASgPK1ERKV6c9/xWs
Upo8IQOoFSDnGjARKV2d0eQ6rFI6qMTtcultWQFyrvMUpaubO7WFVUrD47sDtQLkBKYpaq0KvRU3
CVYtKp/ld3UOUCtAaC9F6erC8G9glVKvRT8DpQEdFaWrTc+UwCql6zY9VfzPb0DO9Zii1qrQDyZ3
hFWLyrme+fUVoNYJyKrnYaTWqtAvas6EVYtKgFfoJ0CtAKFdFKWrcy/8AKuUzjt5GCgNSFeUrrq/
WvZK6cVS6llQEygNCFSUrv4cFgSrlLrvGwbUCpCLbaeotSr041nzYdWicrEFUcuB0oCrilqrEjB/
cTasWlQCetY7CZQGDFLUWpWAB24eVx2+gQqB9YHSAB9FaUCrXX1h1aJyAslZo4BaAUL3K0pXW41a
BKuU/lK0CigNcL2IlK7mtT8Eq5ROy/4NqBUgFxuvqLUqtIlftWsO30C/eo2BWgFyBeGKWgFCD73R
H1YpHZA2FigNGKMoXf0iPgFWKW2S9SVQK0DO9RVFrVWhfnPzYNWiclhtf7oI1AoQ2kNRuhp6vubv
Dt/AmoLmQK0A+azDiloBQic8HQarFpXPqhr7JlAa0FhRa1UCnl2RBKsWlYBZwWuBWgFCNylKV/tt
z4dVSgeVLwZKA6IVpauT1jx93eEbeCq9JVAaUEdRGlD91jBYpXTCnEigVoBc7FJFrVWhrxekwKpF
5WL7l2wGagX8/VwUpat1PH6DVUqbP3cbqBUgn9VDUWtV6MMhz9xw+AbaZPgDtQLkBLoqagUIrd5+
DKxSOqrSVKA04B1F6eqzb6yBVUq7b9oGlAYMVZSu+lQuglVKd50rAUoDrihKV2NXNL/p8A1Mf6cD
UCtAXlaaolaA0Mrrx8OqReW5jN4/A6gVIHShonS1oe9GWKV0cEEGUBoQrShdbTX+OqxSWuRZ9lbp
bdEAnypI6eqiU/6wSmm1hy8BpQFBitLV+A1TYZXShJQ4oDQgXVG6WjtiB6xSmhidA5QGfK8oXU05
9xBWKa2c6HXb4RsIUJSu5rftAquUro4JBUpPYL+idPXajVmwSmmMewJQGvCZonQ1J343rFKa8VY+
UBrwh6J0dWRh+TulF0vp6FO1gFoB8vd1gaLWqtDsyb1g1aLy9/Vk9DCgNMA9Bqm1KgEVW82HVYtK
wMOEZKBWgNDnliClq6EDD8AqpWEjzwK1AuSz5ipqrQqts8/7rsM38PuKRkCtADkBl2SkVoDQwkb9
YJVSz7BxQGlAe0Xp6qotS2CV0q96pQK1AuRcK7yM1FoVuulEHqxaVA5r+8xioDSgSFFrVQI6xdf5
w+EbOLv5OaBWgLTeUNQKENqy3nBYpTTRZQJQK0A+a5ei1qrQnCnJsGpRaW3acQtQK0BoiKJ09UDE
GVildFHVG0CtAPmsFYpaq0KzTje85/ANNAprB9QKkBMIVNQKEDpw81hYpfTjsVOAWgHyWSsVtVaF
FkWugVWLSmvlx98BtQKEBihKVxt9fxFWKQ1f+BgoDfhUUbr6pHrrEodv4FSZrkBpgEdZpDQg6Eok
rFL60pxooDRgsqJ0tW3trbBK6a2C3UBpgOdJpHTVP+EmrFKadLb8/dLbogE/KUpX3+4dAKuU9isK
AkoDZihKV9//YBqsUpp5Lg6oFSA/7puKWqtC3XbsgFWLyo87fcEBoFaA0DuK0lWfkkewSmmP7V4P
HL6BiYrS1evzusAqpcH5A4DSE3hTUbrqHRQDq5TuWLEEKA24rChd3TMzE1YpfeJ1BKgVIC+rijdS
a1Wop3e5h6UXa1F5WWs31AJKAw4paq1KwEHfXrBqUQmYmj8UqBUgdI2idPXFuDhYpdRty3KgNKCF
onQ1pEY2rFI6d2shUBrwqaJ0NfCQxyOHbyDqsS9QGrBOURpw+b1QWKV0xd4xQGnAPkXpalJcAqxS
GlW0BqgVIE97g6LWqtArcUdg1aLytIf4XgRqBQidoyhdnbCi5mOHb+Cz/7QAagXIZ+1W1AoQuiA/
DFYtKp/VemUEUBrQSVFrVQJ6j02CVYtKwIc/bwBqBQhNU5SunvYpgFVKgwOvAbUC5LOGKmqtCs2K
qffkn9+A0K8e+QGlAScUpQGfNBsNqxaVc920cCJQGnBcUWtVPsur9ipYtagEPJn9DVAa0DIaqbUq
AZtfOw+rFpWA7rXuAbUChL6vKF0NGd7Mxd/Z/xas2WcvAKUBYYrSgODkN2CV0jY504HSgBGK0tUx
r6+HVUr7uH4PlAZMVZSuvjfyCqxS+m5zF9fS26IBqxWlq/ve94NVSkM7dgNqBcjTfk1Ra1Vo/Vcn
wqpF5WmfjokBSgP85yClq92vbIVVSmve2QeUBgQraq3KCUT3/wNWLSoncDylkpvDN1Dhc6R0dUqt
QFilNP/FPkDpCZRtj9RalRO41voDWLWonMD2xAVAaUC2otaqBNS8uhNWLSoBW2IOALUChP53DlK6
WuXXR7BKabWPqpRx+AbaKEpXawz+F6xSmnumH1B6Au5nkdLVySmzYZXSXpOXAKUB+YrS1dbX9sAq
pV9XzANKAy4oSleXrCpXtvRiKU3eXwsoDdipKF2N7tALVik9EjoUKA1w64eUrl59+BGsUpoUtAwo
DVinKF09mJcFq5RuXF8IlAZkKkpXV2d5lHP4Bvoeqg+UBgxXlAZMfzYEVil95P0aUBrQripSunp+
7GJYpfTC1lVAaUDtr5HS1TrxvY84vIKuzccDpQGvKmoG/B/ILDOk

ring-buffer = pkt_stats_rb

ring-buffer = connectivity_events_rb
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
JKOz/QnJFKkxSUfTGYREYx4ISv4HDsUxDA==

ring-buffer = firmware_prints_rb
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
SFW1maodMPH/AG7mAgA=

ring-buffer = driver_prints_rb
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
jAe/yTZYRfE11rfMGP8Pag8CpA==

--------------------------------------------------------------------
No fates fetched for "Last failed connection fates"
HAL provided zero fates for "Latest fates"
--------------------------------------------------------------------
WifiNative - Log Begin ----
12-15 21:48:56.294 - startHal stack:  - startHal - processMessage - processMsg - handleMessage - dispatchMessage - loop
12-15 21:48:56.411 - wlan0: IFNAME=wlan0 connectToSupplicant
12-15 21:48:57.080 - wlan0: IFNAME=wlan0 DRIVER MACADDR -> Macaddr = 2c:57:31:c2:1d:2a
12-15 21:48:57.082 - wlan0: IFNAME=wlan0 SET SETBAND AUTO -> true
12-15 21:48:57.083 - wlan0: IFNAME=wlan0 SET update_config 1 -> true
12-15 21:48:57.084 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=-1 -> network id / ssid / bssid / flags
0	Tenda_2036D0	f0:b4:29:69:d4:0d	[DISABLED]
1	lenovo-internet	18:64:72:12:ae:71	[DISABLED]
2	\xe9\x95\xbf\xe6\xb3\xb0\xe5\xb9\xbf\xe5\x9c\xba\xe5\x85\x8d\xe8\xb4\xb9wifi	10:0f:0f:80:05:0a	[DISABLED]
3	Hema-Free	14:1f:ba:85:4c:18	[DISABLED]
4	\xe8\x8a\xb1\xe7\x94\x9f\xe9\xbb\x91\xe5\x8d\xa1\xe4\xb8\x93\xe4\xba\xabWiFi	any	[DISABLED]
5	\xe8\x8a\xb1\xe7\x94\x9f\xe5\x9c\xb0\xe9\x93\x81WiFi	06:14:4b:71:31:4c	[DISABLED]
6	+Shjoycity Free Wifi	60:da:83:42:92:11	[DISABLED]
7	AuchanFreeWIFI	any	[DISABLED]
8	Baidu_Friend	any	[DISABLED]
9	xmly2.4	00:27:e3:fc:33:b3	[DISABLED]
10	360\xe8\xa1\x8c\xe8\xbd\xa6\xe8\xae\xb0\xe5\xbd\x95\xe4\xbb\xaa-CCAF	d0:fa:1d:3a:cc:af	[DISABLED]
11	HUAWEI nova 3e	any	[DISABLED]
12	AP_A914DA	any	[DISABLED]
13	a1b2c3d4e5f0	02:1c:c2:4f:94:10	[DISABLED]
14	102	bc:5f:f6:e9:2a:bb	
15	\xe9\x83\xad\xe6\x85\xa7\xe6\x9d\x8f\xe7\x9a\x84 iPhone	a2:3b:e3:df:30:ae	[DISABLED]
16	CMCC-aT9r	cc:f0:fd:29:99:68	[DISABLED]
17	Xiaomi_F57A_0A85	any	[DISABLED]
18	TRG	any	[DISABLED]
19	love on	any	[DISABLED]
20	moto g(6) play test	any	[DISABLED]
21	D-Link_DIR-816	any	[DISABLED]
22	3C	any	[DISABLED]
23	3C_5G	any	[DISABLED]
24	Xiaomi_F57A_0A85_5G	any	[DISABLED]
25	3CC	any	[DISABLED]
26	3CC_5G	any	[DISABLED]
27	200E	any	[DISABLED]
12-15 21:48:57.090 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=UNKNOWN]
12-15 21:48:57.451 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=27 -> network id / ssid / bssid / flags
12-15 21:48:57.474 - wlan0: IFNAME=wlan0 SET device_name meizu_M6Note_CN -> true
12-15 21:48:57.474 - wlan0: IFNAME=wlan0 SET manufacturer Meizu -> true
12-15 21:48:57.475 - wlan0: IFNAME=wlan0 SET model_name M6 Note -> true
12-15 21:48:57.475 - wlan0: IFNAME=wlan0 SET model_number M6 Note -> true
12-15 21:48:57.476 - wlan0: IFNAME=wlan0 SET serial_number 721QECRM2ZMT4 -> true
12-15 21:48:57.476 - wlan0: IFNAME=wlan0 SET config_methods physical_display virtual_push_button -> true
12-15 21:48:57.477 - wlan0: IFNAME=wlan0 SET device_type 10-0050F204-5 -> true
12-15 21:48:57.477 - wlan0: IFNAME=wlan0 SET tdls_external_control 1 -> true
12-15 21:48:57.477 - wlan0: IFNAME=wlan0 SET disable_scan_offload 1 -> true
12-15 21:48:57.478 - wlan0: IFNAME=wlan0 SET p2p_disabled 1 -> true
12-15 21:48:57.479 - wlan0: IFNAME=wlan0 SCAN_INTERVAL 15 -> true
12-15 21:48:57.480 - wlan0: IFNAME=wlan0 SET external_sim 1 -> true
12-15 21:48:57.482 - wlan0: IFNAME=wlan0 STA_AUTOCONNECT 0 -> true
12-15 21:48:57.783 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-15 21:48:57.785 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-15 21:48:57.786 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-15 21:48:57.793 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-15 21:48:57.797 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-15 21:48:57.798 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 2 -> true
12-15 21:48:57.799 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-15 21:48:57.800 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-15 21:48:57.802 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 3 -> true
12-15 21:48:57.803 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-15 21:48:57.804 - wlan0: IFNAME=wlan0 STATUS -> wpa_state=DISCONNECTED
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
12-15 21:48:57.805 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-15 21:48:57.807 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-15 21:48:57.842 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:48:57.844 - p2p0: SET persistent_reconnect 1 -> true
12-15 21:48:57.845 - p2p0: SET device_name 魅蓝 Note6 -> true
12-15 21:48:57.846 - p2p0: SET p2p_ssid_postfix -魅蓝 Note6 -> true
12-15 21:48:57.848 - p2p0: SET device_type 10-0050F204-5 -> true
12-15 21:48:57.850 - p2p0: SET config_methods virtual_push_button physical_display keypad -> true
12-15 21:48:57.851 - p2p0: P2P_SET conc_pref sta -> true
12-15 21:48:57.863 - p2p0: P2P_FLUSH -> true
12-15 21:48:57.864 - p2p0: P2P_SERVICE_FLUSH -> true
12-15 21:48:57.865 - p2p0: LIST_NETWORKS -> network id / ssid / bssid / flags
12-15 21:48:59.114 - p2p0: SAVE_CONFIG -> true
12-15 21:49:09.796 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-15 21:49:09.808 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-15 21:49:10.341 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:49:10.342 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-15 21:49:10.348 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:49:10.750 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-START -> true
12-15 21:49:11.457 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:49:11.457 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-15 21:49:11.457 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-15 21:49:21.355 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-15 21:49:23.368 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:49:23.509 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:49:23.510 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-15 21:49:23.511 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:49:24.852 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:49:24.853 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-15 21:49:24.855 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-15 21:49:24.933 - wlan0: IFNAME=wlan0 SET_NETWORK 14 ssid 313032 -> true
12-15 21:49:24.934 - wlan0: IFNAME=wlan0 SET_NETWORK 14 id_str "%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D" -> true
12-15 21:49:24.935 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid bc:5f:f6:e9:2a:bb -> true
12-15 21:49:24.936 - wlan0: IFNAME=wlan0 SET_NETWORK 14 key_mgmt WPA-PSK -> true
12-15 21:49:24.937 - wlan0: IFNAME=wlan0 SET_NETWORK 14 proto WPA RSN WAPI -> true
12-15 21:49:24.938 - wlan0: IFNAME=wlan0 SET_NETWORK 14 pairwise TKIP CCMP SMS4 -> true
12-15 21:49:24.938 - wlan0: IFNAME=wlan0 SET_NETWORK 14 group TKIP CCMP SMS4 -> true
12-15 21:49:24.939 - wlan0: IFNAME=wlan0 SET_NETWORK 14 sim_num 1 -> true
12-15 21:49:24.940 - wlan0: IFNAME=wlan0 SET_NETWORK 14 priority 45 -> true
12-15 21:49:24.941 - wlan0: IFNAME=wlan0 SET_NETWORK 14 flag 1 -> true
12-15 21:49:25.053 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-15 21:49:25.186 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-15 21:49:25.189 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-15 21:49:25.202 - Event [IFNAME=wlan0 Request association with bc:5f:f6:e9:2a:bb]
12-15 21:49:25.202 - wlan0: IFNAME=wlan0 SELECT_NETWORK 14 -> true
12-15 21:49:25.203 - Event [IFNAME=wlan0 Trying to associate with SSID '102']
12-15 21:49:25.204 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=5 BSSID=00:00:00:00:00:00 SSID=102]
12-15 21:49:25.301 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-15 21:49:25.463 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=6 BSSID=00:00:00:00:00:00 SSID=102]
12-15 21:49:25.464 - Event [IFNAME=wlan0 Associated with bc:5f:f6:e9:2a:bb]
12-15 21:49:25.464 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=7 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-15 21:49:25.465 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-15 21:49:25.478 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=8 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-15 21:49:25.481 - Event [IFNAME=wlan0 WPA: Key negotiation completed with bc:5f:f6:e9:2a:bb [PTK=CCMP GTK=CCMP]]
12-15 21:49:25.481 - Event [IFNAME=wlan0 CTRL-EVENT-CONNECTED - Connection to bc:5f:f6:e9:2a:bb completed [id=14 id_str=%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D]]
12-15 21:49:25.482 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=9 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-15 21:49:25.507 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-15 21:49:25.522 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-15 21:49:25.525 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-15 21:49:25.526 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-15 21:49:25.564 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-15 21:49:25.581 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-REMOVE 2 -> true
12-15 21:49:25.584 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-15 21:49:25.643 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-15 21:49:25.644 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:49:25.645 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-15 21:49:29.261 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:49:38.942 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 21:49:38.962 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-15 21:49:38.963 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-15 21:49:38.979 - wlan0: IFNAME=wlan0 BLACKLIST clear -> true
12-15 21:49:51.026 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:50:22.213 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:50:22.216 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:50:23.212 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:50:29.926 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:50:29.928 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:50:31.910 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:50:54.064 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 21:51:00.906 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:51:37.685 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:51:37.686 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:51:38.862 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:52:21.025 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 21:58:50.830 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 21:59:48.729 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 21:59:48.731 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 21:59:49.884 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 21:59:59.918 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 22:19:43.426 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 22:19:54.429 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 22:21:56.303 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 22:22:17.478 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 22:49:37.910 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-15 22:49:37.945 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 22:49:38.275 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-15 22:49:38.349 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 22:49:38.370 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-15 22:49:38.371 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-15 22:57:22.506 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 22:57:24.885 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 22:57:24.886 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 22:57:27.018 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 22:57:27.386 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 22:57:27.387 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 22:57:27.388 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 22:58:25.025 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 22:58:25.026 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 22:58:26.237 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 22:58:54.320 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 22:58:54.321 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 22:58:55.445 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 22:59:43.114 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 23:01:24.157 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 23:01:35.853 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 23:05:10.425 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 23:05:12.231 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 23:05:12.233 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 23:05:13.271 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 23:05:22.227 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 23:05:22.228 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 23:05:24.242 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 23:18:06.943 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-15 23:18:06.953 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-15 23:18:09.067 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-15 23:18:24.387 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 23:49:38.375 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-15 23:49:38.405 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-15 23:49:38.461 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-15 23:49:38.543 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-15 23:49:38.564 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-15 23:49:38.566 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 00:49:37.477 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 00:49:37.498 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 00:49:37.868 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 00:49:37.976 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 00:49:38.000 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 00:49:38.001 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 01:49:37.603 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 01:49:37.636 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 01:49:37.861 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 01:49:38.348 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 01:49:38.371 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 01:49:38.372 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 02:49:38.544 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 02:49:38.577 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 02:49:39.012 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 02:49:39.087 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 02:49:39.109 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 02:49:39.110 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 03:49:39.399 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 03:49:39.434 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 03:49:39.812 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 03:49:39.874 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 03:49:39.895 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 03:49:39.897 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 04:49:41.327 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 04:49:41.358 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 04:49:41.741 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 04:49:41.815 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 04:49:41.837 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 04:49:41.838 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 05:49:41.466 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 05:49:41.491 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 05:49:41.835 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 05:49:41.904 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 05:49:41.925 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 05:49:41.926 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 06:49:42.522 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 06:49:42.556 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 06:49:42.915 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 06:49:42.984 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 06:49:43.007 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 06:49:43.008 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 07:49:42.869 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 07:49:42.901 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 07:49:43.309 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 07:49:43.384 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 07:49:43.407 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 07:49:43.408 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 08:49:43.273 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 08:49:43.314 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 08:49:43.686 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 08:49:43.755 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 08:49:43.777 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 08:49:43.778 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 09:49:41.396 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 09:49:41.429 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 09:49:41.812 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 09:49:41.858 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 09:49:41.880 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 09:49:41.881 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 10:21:40.310 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:22:21.802 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:22:21.808 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:22:22.964 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:23:20.564 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:23:20.565 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:23:22.635 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:24:06.830 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:24:07.822 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:24:12.303 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:24:18.522 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:24:40.944 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:24:42.541 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:24:53.558 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:24:57.997 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:25:08.903 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:25:40.240 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:26:15.583 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:26:42.282 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:27:33.069 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:27:35.650 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:28:09.186 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:28:23.387 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:28:42.694 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:28:52.375 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:30:31.723 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:30:31.724 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:30:32.819 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:30:34.778 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:30:34.778 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:30:34.780 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:32:28.650 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:32:29.147 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:32:32.216 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:32:37.133 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:32:40.030 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:32:40.439 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:32:52.100 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:32:55.191 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:33:10.775 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:33:35.558 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:34:03.225 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:35:34.748 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:35:40.263 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:36:55.677 - Event [IFNAME=wlan0 CTRL-EVENT-DISCONNECTED bssid=bc:5f:f6:e9:2a:bb reason=0]
12-16 10:36:55.679 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=0 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 10:36:55.701 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 10:36:55.707 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 10:36:55.709 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 10:36:55.711 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 10:36:55.748 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 10:36:55.757 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:36:55.757 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:49:26.261 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:49:26.262 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:49:31.727 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:49:31.729 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:49:31.823 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:49:31.823 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:50:15.560 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:50:15.561 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:52:50.536 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 10:52:50.539 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 10:52:50.541 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:52:50.550 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:52:52.013 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:52:52.014 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 10:52:58.435 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:52:58.436 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:52:58.552 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 10:52:58.552 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 10:53:02.916 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 10:53:02.917 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 10:53:02.918 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 10:53:04.422 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 10:53:04.423 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 10:53:05.892 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 10:53:05.893 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 10:53:05.914 - wlan0: IFNAME=wlan0 TERMINATE -> true
12-16 10:53:06.280 - Event [IFNAME=wlan0 CTRL-EVENT-TERMINATING ]
12-16 10:53:06.281 - wlan0: IFNAME=wlan0 closeSupplicantConnection
12-16 18:39:35.617 - startHal stack:  - startHal - processMessage - processMsg - handleMessage - dispatchMessage - loop
12-16 18:39:35.726 - wlan0: IFNAME=wlan0 connectToSupplicant
12-16 18:39:36.782 - wlan0: IFNAME=wlan0 DRIVER MACADDR -> Macaddr = 2c:57:31:c2:1d:2a
12-16 18:39:36.785 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=UNKNOWN]
12-16 18:39:36.801 - wlan0: IFNAME=wlan0 SET SETBAND AUTO -> true
12-16 18:39:36.803 - wlan0: IFNAME=wlan0 SET update_config 1 -> true
12-16 18:39:36.804 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=-1 -> network id / ssid / bssid / flags
0	Tenda_2036D0	f0:b4:29:69:d4:0d	[DISABLED]
1	lenovo-internet	18:64:72:12:ae:71	[DISABLED]
2	\xe9\x95\xbf\xe6\xb3\xb0\xe5\xb9\xbf\xe5\x9c\xba\xe5\x85\x8d\xe8\xb4\xb9wifi	10:0f:0f:80:05:0a	[DISABLED]
3	Hema-Free	14:1f:ba:85:4c:18	[DISABLED]
4	\xe8\x8a\xb1\xe7\x94\x9f\xe9\xbb\x91\xe5\x8d\xa1\xe4\xb8\x93\xe4\xba\xabWiFi	any	[DISABLED]
5	\xe8\x8a\xb1\xe7\x94\x9f\xe5\x9c\xb0\xe9\x93\x81WiFi	06:14:4b:71:31:4c	[DISABLED]
6	+Shjoycity Free Wifi	60:da:83:42:92:11	[DISABLED]
7	AuchanFreeWIFI	any	[DISABLED]
8	Baidu_Friend	any	[DISABLED]
9	xmly2.4	00:27:e3:fc:33:b3	[DISABLED]
10	360\xe8\xa1\x8c\xe8\xbd\xa6\xe8\xae\xb0\xe5\xbd\x95\xe4\xbb\xaa-CCAF	d0:fa:1d:3a:cc:af	[DISABLED]
11	HUAWEI nova 3e	any	[DISABLED]
12	AP_A914DA	any	[DISABLED]
13	a1b2c3d4e5f0	02:1c:c2:4f:94:10	[DISABLED]
14	102	bc:5f:f6:e9:2a:bb	
15	\xe9\x83\xad\xe6\x85\xa7\xe6\x9d\x8f\xe7\x9a\x84 iPhone	a2:3b:e3:df:30:ae	[DISABLED]
16	CMCC-aT9r	cc:f0:fd:29:99:68	[DISABLED]
17	Xiaomi_F57A_0A85	any	[DISABLED]
18	TRG	any	[DISABLED]
19	love on	any	[DISABLED]
20	moto g(6) play test	any	[DISABLED]
21	D-Link_DIR-816	any	[DISABLED]
22	3C	any	[DISABLED]
23	3C_5G	any	[DISABLED]
24	Xiaomi_F57A_0A85_5G	any	[DISABLED]
25	3CC	any	[DISABLED]
26	3CC_5G	any	[DISABLED]
27	200E	any	[DISABLED]
12-16 18:39:36.953 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=27 -> network id / ssid / bssid / flags
12-16 18:39:36.974 - wlan0: IFNAME=wlan0 SET device_name meizu_M6Note_CN -> true
12-16 18:39:36.974 - wlan0: IFNAME=wlan0 SET manufacturer Meizu -> true
12-16 18:39:36.975 - wlan0: IFNAME=wlan0 SET model_name M6 Note -> true
12-16 18:39:36.975 - wlan0: IFNAME=wlan0 SET model_number M6 Note -> true
12-16 18:39:36.976 - wlan0: IFNAME=wlan0 SET serial_number 721QECRM2ZMT4 -> true
12-16 18:39:36.976 - wlan0: IFNAME=wlan0 SET config_methods physical_display virtual_push_button -> true
12-16 18:39:36.977 - wlan0: IFNAME=wlan0 SET device_type 10-0050F204-5 -> true
12-16 18:39:36.977 - wlan0: IFNAME=wlan0 SET tdls_external_control 1 -> true
12-16 18:39:36.977 - wlan0: IFNAME=wlan0 SET disable_scan_offload 1 -> true
12-16 18:39:36.978 - wlan0: IFNAME=wlan0 SET p2p_disabled 1 -> true
12-16 18:39:36.979 - wlan0: IFNAME=wlan0 SCAN_INTERVAL 15 -> true
12-16 18:39:36.980 - wlan0: IFNAME=wlan0 SET external_sim 1 -> true
12-16 18:39:36.982 - wlan0: IFNAME=wlan0 STA_AUTOCONNECT 0 -> true
12-16 18:39:36.983 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 18:39:36.985 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 18:39:36.988 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-16 18:39:36.989 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 18:39:36.989 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 2 -> true
12-16 18:39:36.990 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 18:39:36.990 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 18:39:36.991 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 3 -> true
12-16 18:39:36.992 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 18:39:36.992 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:39:36.993 - wlan0: IFNAME=wlan0 STATUS -> wpa_state=DISCONNECTED
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
12-16 18:39:36.994 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:39:36.995 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 18:39:37.006 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:39:37.007 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-START -> true
12-16 18:39:37.008 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:39:37.009 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:39:37.013 - p2p0: SET persistent_reconnect 1 -> true
12-16 18:39:37.014 - p2p0: SET device_name 魅蓝 Note6 -> true
12-16 18:39:37.014 - p2p0: SET p2p_ssid_postfix -魅蓝 Note6 -> true
12-16 18:39:37.015 - p2p0: SET device_type 10-0050F204-5 -> true
12-16 18:39:37.016 - p2p0: SET config_methods virtual_push_button physical_display keypad -> true
12-16 18:39:37.017 - p2p0: P2P_SET conc_pref sta -> true
12-16 18:39:37.021 - p2p0: P2P_FLUSH -> true
12-16 18:39:37.023 - p2p0: P2P_SERVICE_FLUSH -> true
12-16 18:39:37.024 - p2p0: LIST_NETWORKS -> network id / ssid / bssid / flags
12-16 18:39:37.081 - p2p0: SAVE_CONFIG -> true
12-16 18:39:38.581 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:39:38.582 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 18:39:38.582 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:39:47.633 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-16 18:39:50.852 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:50:37.431 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:50:37.433 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:50:37.434 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:50:37.500 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:50:38.791 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:50:38.792 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:50:57.437 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:50:57.438 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:50:57.439 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:50:59.274 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:50:59.274 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 18:50:59.275 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:51:15.646 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:53:41.875 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:53:41.876 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:53:41.877 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:53:41.879 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:53:43.321 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:53:43.322 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE-PBC ]
12-16 18:53:43.322 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:54:16.045 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:54:16.047 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:54:16.047 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:54:17.495 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:54:17.496 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE-PBC ]
12-16 18:54:17.497 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:54:51.729 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:55:27.893 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:55:27.895 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:55:27.896 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:55:27.897 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:55:29.408 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:55:29.408 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE-PBC ]
12-16 18:55:29.408 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:55:47.899 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:55:47.902 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:55:47.904 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:55:49.453 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:55:49.454 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 18:55:49.455 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:56:01.620 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:56:02.311 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:56:07.900 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:56:07.902 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:56:07.904 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:56:09.392 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:56:09.394 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 18:56:09.395 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:56:09.663 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid b0:95:8e:48:b3:e7 -> true
12-16 18:56:09.695 - wlan0: IFNAME=wlan0 SET_NETWORK 14 ssid 313032 -> true
12-16 18:56:09.698 - wlan0: IFNAME=wlan0 SET_NETWORK 14 id_str "%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D" -> true
12-16 18:56:09.700 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid b0:95:8e:48:b3:e7 -> true
12-16 18:56:09.702 - wlan0: IFNAME=wlan0 SET_NETWORK 14 key_mgmt WPA-PSK -> true
12-16 18:56:09.704 - wlan0: IFNAME=wlan0 SET_NETWORK 14 proto WPA RSN WAPI -> true
12-16 18:56:09.705 - wlan0: IFNAME=wlan0 SET_NETWORK 14 pairwise TKIP CCMP SMS4 -> true
12-16 18:56:09.707 - wlan0: IFNAME=wlan0 SET_NETWORK 14 group TKIP CCMP SMS4 -> true
12-16 18:56:09.709 - wlan0: IFNAME=wlan0 SET_NETWORK 14 sim_num 1 -> true
12-16 18:56:09.711 - wlan0: IFNAME=wlan0 SET_NETWORK 14 priority 45 -> true
12-16 18:56:09.713 - wlan0: IFNAME=wlan0 SET_NETWORK 14 flag 1 -> true
12-16 18:56:09.768 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 18:56:09.792 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 18:56:09.793 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-16 18:56:09.810 - Event [IFNAME=wlan0 Request association with b0:95:8e:48:b3:e7]
12-16 18:56:09.812 - wlan0: IFNAME=wlan0 SELECT_NETWORK 14 -> true
12-16 18:56:09.812 - Event [IFNAME=wlan0 Trying to associate with SSID '102']
12-16 18:56:09.814 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=5 BSSID=00:00:00:00:00:00 SSID=102]
12-16 18:56:09.819 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-16 18:56:10.911 - Event [IFNAME=wlan0 CTRL-EVENT-ASSOC-REJECT bssid=b0:95:8e:48:b3:e7 status_code=1]
12-16 18:56:10.913 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=0 BSSID=00:00:00:00:00:00 SSID=102]
12-16 18:56:11.010 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:56:11.012 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:56:12.532 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:56:12.535 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 18:56:12.674 - Event [IFNAME=wlan0 Request association with b0:95:8e:48:b3:e7]
12-16 18:56:12.674 - Event [IFNAME=wlan0 Trying to associate with SSID '102']
12-16 18:56:12.674 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=5 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:56:12.856 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=6 BSSID=00:00:00:00:00:00 SSID=102]
12-16 18:56:12.858 - Event [IFNAME=wlan0 Associated with b0:95:8e:48:b3:e7]
12-16 18:56:12.860 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-16 18:56:12.869 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=7 BSSID=b0:95:8e:48:b3:e7 SSID=102]
12-16 18:56:22.863 - Event [IFNAME=wlan0 Authentication with b0:95:8e:48:b3:e7 timed out.]
12-16 18:56:22.893 - Event [IFNAME=wlan0 CTRL-EVENT-DISCONNECTED bssid=b0:95:8e:48:b3:e7 reason=3 locally_generated=1]
12-16 18:56:22.894 - Event [IFNAME=wlan0 WPA: 4-Way Handshake failed - pre-shared key may be incorrect]
12-16 18:56:22.895 - Event [IFNAME=wlan0 CTRL-EVENT-SSID-TEMP-DISABLED id=14 ssid="102" auth_failures=1 duration=10 reason=WRONG_KEY]
12-16 18:56:22.908 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=0 BSSID=b0:95:8e:48:b3:e7 SSID=102]
12-16 18:56:26.606 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:57:31.359 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:57:31.359 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:57:31.360 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:57:31.362 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:57:32.849 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:57:32.850 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:57:33.027 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 18:58:46.075 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:58:46.082 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:58:46.083 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:58:46.085 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:58:47.413 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 18:58:47.414 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 18:58:47.455 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid bc:5f:f6:e9:2a:bb -> true
12-16 18:58:47.456 - wlan0: IFNAME=wlan0 SET_NETWORK 14 ssid 313032 -> true
12-16 18:58:47.457 - wlan0: IFNAME=wlan0 SET_NETWORK 14 id_str "%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D" -> true
12-16 18:58:47.457 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid bc:5f:f6:e9:2a:bb -> true
12-16 18:58:47.458 - wlan0: IFNAME=wlan0 SET_NETWORK 14 key_mgmt WPA-PSK -> true
12-16 18:58:47.458 - wlan0: IFNAME=wlan0 SET_NETWORK 14 proto WPA RSN WAPI -> true
12-16 18:58:47.459 - wlan0: IFNAME=wlan0 SET_NETWORK 14 pairwise TKIP CCMP SMS4 -> true
12-16 18:58:47.459 - wlan0: IFNAME=wlan0 SET_NETWORK 14 group TKIP CCMP SMS4 -> true
12-16 18:58:47.459 - wlan0: IFNAME=wlan0 SET_NETWORK 14 sim_num 1 -> true
12-16 18:58:47.460 - wlan0: IFNAME=wlan0 SET_NETWORK 14 priority 45 -> true
12-16 18:58:47.460 - wlan0: IFNAME=wlan0 SET_NETWORK 14 flag 1 -> true
12-16 18:58:47.486 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 18:58:47.502 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 18:58:47.505 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-16 18:58:47.510 - wlan0: IFNAME=wlan0 SELECT_NETWORK 14 -> true
12-16 18:58:47.510 - Event [IFNAME=wlan0 CTRL-EVENT-SSID-REENABLED id=14 ssid="102"]
12-16 18:58:47.511 - Event [IFNAME=wlan0 Request association with bc:5f:f6:e9:2a:bb]
12-16 18:58:47.511 - Event [IFNAME=wlan0 Trying to associate with SSID '102']
12-16 18:58:47.511 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=5 BSSID=00:00:00:00:00:00 SSID=102]
12-16 18:58:47.513 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-16 18:58:47.656 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=6 BSSID=00:00:00:00:00:00 SSID=102]
12-16 18:58:47.656 - Event [IFNAME=wlan0 Associated with bc:5f:f6:e9:2a:bb]
12-16 18:58:47.657 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=7 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 18:58:47.658 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-16 18:58:47.682 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=8 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 18:58:47.683 - Event [IFNAME=wlan0 WPA: Key negotiation completed with bc:5f:f6:e9:2a:bb [PTK=CCMP GTK=CCMP]]
12-16 18:58:47.684 - Event [IFNAME=wlan0 CTRL-EVENT-CONNECTED - Connection to bc:5f:f6:e9:2a:bb completed [id=14 id_str=%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D]]
12-16 18:58:47.684 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=9 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 18:58:47.693 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 18:58:47.696 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 18:58:47.697 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 18:58:47.698 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 18:58:47.709 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 18:58:47.710 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-REMOVE 2 -> true
12-16 18:58:47.711 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 18:58:47.778 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 18:58:47.778 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 18:58:47.779 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 18:58:48.531 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 18:58:48.532 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 18:58:48.536 - wlan0: IFNAME=wlan0 BLACKLIST clear -> true
12-16 18:58:49.592 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 18:58:49.592 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 18:58:52.871 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:02:23.455 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 19:02:23.470 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 19:02:25.440 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:29:11.438 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 19:29:54.386 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 19:30:16.373 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 19:30:16.392 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 19:30:17.423 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:30:18.209 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 19:30:18.211 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 19:30:18.212 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:30:22.621 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 19:30:22.623 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 19:30:24.713 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:31:27.053 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 19:35:19.707 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 19:36:09.659 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 19:36:09.674 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 19:36:09.681 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 19:36:09.697 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 19:36:09.699 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 19:36:09.720 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 19:36:09.792 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 19:36:09.799 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 19:36:09.810 - wlan0: IFNAME=wlan0 TERMINATE -> true
12-16 19:36:09.860 - Event [IFNAME=wlan0 CTRL-EVENT-DISCONNECTED bssid=bc:5f:f6:e9:2a:bb reason=3 locally_generated=1]
12-16 19:36:09.860 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=0 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 19:36:10.247 - Event [IFNAME=wlan0 CTRL-EVENT-TERMINATING ]
12-16 19:36:10.248 - wlan0: IFNAME=wlan0 closeSupplicantConnection
12-16 19:36:12.703 - startHal stack:  - startHal - processMessage - processMsg - handleMessage - dispatchMessage - loop
12-16 19:36:12.912 - wlan0: IFNAME=wlan0 connectToSupplicant
12-16 19:36:13.637 - wlan0: IFNAME=wlan0 DRIVER MACADDR -> Macaddr = 2c:57:31:c2:1d:2a
12-16 19:36:13.639 - wlan0: IFNAME=wlan0 SET SETBAND AUTO -> true
12-16 19:36:13.640 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=UNKNOWN]
12-16 19:36:13.640 - wlan0: IFNAME=wlan0 SET update_config 1 -> true
12-16 19:36:13.641 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=-1 -> network id / ssid / bssid / flags
0	Tenda_2036D0	f0:b4:29:69:d4:0d	[DISABLED]
1	lenovo-internet	18:64:72:12:ae:71	[DISABLED]
2	\xe9\x95\xbf\xe6\xb3\xb0\xe5\xb9\xbf\xe5\x9c\xba\xe5\x85\x8d\xe8\xb4\xb9wifi	10:0f:0f:80:05:0a	[DISABLED]
3	Hema-Free	14:1f:ba:85:4c:18	[DISABLED]
4	\xe8\x8a\xb1\xe7\x94\x9f\xe9\xbb\x91\xe5\x8d\xa1\xe4\xb8\x93\xe4\xba\xabWiFi	any	[DISABLED]
5	\xe8\x8a\xb1\xe7\x94\x9f\xe5\x9c\xb0\xe9\x93\x81WiFi	06:14:4b:71:31:4c	[DISABLED]
6	+Shjoycity Free Wifi	60:da:83:42:92:11	[DISABLED]
7	AuchanFreeWIFI	any	[DISABLED]
8	Baidu_Friend	any	[DISABLED]
9	xmly2.4	00:27:e3:fc:33:b3	[DISABLED]
10	360\xe8\xa1\x8c\xe8\xbd\xa6\xe8\xae\xb0\xe5\xbd\x95\xe4\xbb\xaa-CCAF	d0:fa:1d:3a:cc:af	[DISABLED]
11	HUAWEI nova 3e	any	[DISABLED]
12	AP_A914DA	any	[DISABLED]
13	a1b2c3d4e5f0	02:1c:c2:4f:94:10	[DISABLED]
14	102	bc:5f:f6:e9:2a:bb	
15	\xe9\x83\xad\xe6\x85\xa7\xe6\x9d\x8f\xe7\x9a\x84 iPhone	a2:3b:e3:df:30:ae	[DISABLED]
16	CMCC-aT9r	cc:f0:fd:29:99:68	[DISABLED]
17	Xiaomi_F57A_0A85	any	[DISABLED]
18	TRG	any	[DISABLED]
19	love on	any	[DISABLED]
20	moto g(6) play test	any	[DISABLED]
21	D-Link_DIR-816	any	[DISABLED]
22	3C	any	[DISABLED]
23	3C_5G	any	[DISABLED]
24	Xiaomi_F57A_0A85_5G	any	[DISABLED]
25	3CC	any	[DISABLED]
26	3CC_5G	any	[DISABLED]
27	200E	any	[DISABLED]
12-16 19:36:13.830 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=27 -> network id / ssid / bssid / flags
12-16 19:36:13.865 - wlan0: IFNAME=wlan0 SET device_name meizu_M6Note_CN -> true
12-16 19:36:13.866 - wlan0: IFNAME=wlan0 SET manufacturer Meizu -> true
12-16 19:36:13.866 - wlan0: IFNAME=wlan0 SET model_name M6 Note -> true
12-16 19:36:13.868 - wlan0: IFNAME=wlan0 SET model_number M6 Note -> true
12-16 19:36:13.869 - wlan0: IFNAME=wlan0 SET serial_number 721QECRM2ZMT4 -> true
12-16 19:36:13.869 - wlan0: IFNAME=wlan0 SET config_methods physical_display virtual_push_button -> true
12-16 19:36:13.871 - wlan0: IFNAME=wlan0 SET device_type 10-0050F204-5 -> true
12-16 19:36:13.872 - wlan0: IFNAME=wlan0 SET tdls_external_control 1 -> true
12-16 19:36:13.872 - wlan0: IFNAME=wlan0 SET disable_scan_offload 1 -> true
12-16 19:36:13.875 - wlan0: IFNAME=wlan0 SET p2p_disabled 1 -> true
12-16 19:36:13.877 - wlan0: IFNAME=wlan0 SCAN_INTERVAL 15 -> true
12-16 19:36:13.878 - wlan0: IFNAME=wlan0 SET external_sim 1 -> true
12-16 19:36:13.886 - wlan0: IFNAME=wlan0 STA_AUTOCONNECT 0 -> true
12-16 19:36:13.889 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-16 19:36:13.889 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-16 19:36:13.892 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-16 19:36:13.895 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 19:36:13.896 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 2 -> true
12-16 19:36:13.897 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 19:36:13.898 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 19:36:13.899 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 3 -> true
12-16 19:36:13.902 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 19:36:13.903 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 19:36:13.903 - wlan0: IFNAME=wlan0 STATUS -> wpa_state=DISCONNECTED
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
12-16 19:36:13.904 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 19:36:13.904 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 19:36:13.972 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 19:36:13.973 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-16 19:36:13.974 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 19:36:13.984 - p2p0: SET persistent_reconnect 1 -> true
12-16 19:36:13.988 - p2p0: SET device_name 魅蓝 Note6 -> true
12-16 19:36:13.989 - p2p0: SET p2p_ssid_postfix -魅蓝 Note6 -> true
12-16 19:36:13.990 - p2p0: SET device_type 10-0050F204-5 -> true
12-16 19:36:13.991 - p2p0: SET config_methods virtual_push_button physical_display keypad -> true
12-16 19:36:13.992 - p2p0: P2P_SET conc_pref sta -> true
12-16 19:36:13.998 - p2p0: P2P_FLUSH -> true
12-16 19:36:13.999 - p2p0: P2P_SERVICE_FLUSH -> true
12-16 19:36:14.000 - p2p0: LIST_NETWORKS -> network id / ssid / bssid / flags
12-16 19:36:14.044 - p2p0: SAVE_CONFIG -> true
12-16 19:36:14.842 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 19:36:14.842 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-16 19:36:14.843 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-16 19:36:14.868 - wlan0: IFNAME=wlan0 SET_NETWORK 14 ssid 313032 -> true
12-16 19:36:14.869 - wlan0: IFNAME=wlan0 SET_NETWORK 14 id_str "%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D" -> true
12-16 19:36:14.870 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid bc:5f:f6:e9:2a:bb -> true
12-16 19:36:14.870 - wlan0: IFNAME=wlan0 SET_NETWORK 14 key_mgmt WPA-PSK -> true
12-16 19:36:14.871 - wlan0: IFNAME=wlan0 SET_NETWORK 14 proto WPA RSN WAPI -> true
12-16 19:36:14.871 - wlan0: IFNAME=wlan0 SET_NETWORK 14 pairwise TKIP CCMP SMS4 -> true
12-16 19:36:14.872 - wlan0: IFNAME=wlan0 SET_NETWORK 14 group TKIP CCMP SMS4 -> true
12-16 19:36:14.872 - wlan0: IFNAME=wlan0 SET_NETWORK 14 sim_num 1 -> true
12-16 19:36:14.872 - wlan0: IFNAME=wlan0 SET_NETWORK 14 priority 45 -> true
12-16 19:36:14.873 - wlan0: IFNAME=wlan0 SET_NETWORK 14 flag 1 -> true
12-16 19:36:14.916 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 19:36:14.933 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-16 19:36:14.934 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-16 19:36:14.939 - wlan0: IFNAME=wlan0 SELECT_NETWORK 14 -> true
12-16 19:36:14.940 - Event [IFNAME=wlan0 Request association with bc:5f:f6:e9:2a:bb]
12-16 19:36:14.940 - Event [IFNAME=wlan0 Trying to associate with SSID '102']
12-16 19:36:14.941 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=5 BSSID=00:00:00:00:00:00 SSID=102]
12-16 19:36:14.942 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-16 19:36:15.098 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=6 BSSID=00:00:00:00:00:00 SSID=102]
12-16 19:36:15.099 - Event [IFNAME=wlan0 Associated with bc:5f:f6:e9:2a:bb]
12-16 19:36:15.099 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-16 19:36:15.110 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=7 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 19:36:15.127 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=8 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 19:36:15.129 - Event [IFNAME=wlan0 WPA: Key negotiation completed with bc:5f:f6:e9:2a:bb [PTK=CCMP GTK=CCMP]]
12-16 19:36:15.129 - Event [IFNAME=wlan0 CTRL-EVENT-CONNECTED - Connection to bc:5f:f6:e9:2a:bb completed [id=14 id_str=%7B%22creatorUid%22%3A%221000%22%2C%22configKey%22%3A%22%5C%22102%5C%22WPA_PSK%22%7D]]
12-16 19:36:15.129 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=9 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-16 19:36:15.148 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 19:36:15.159 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-16 19:36:15.160 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 19:36:15.161 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 19:36:15.182 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-16 19:36:15.183 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-REMOVE 2 -> true
12-16 19:36:15.184 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-16 19:36:15.264 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 19:36:15.265 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 19:36:15.265 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 19:36:15.382 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 19:36:15.383 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 19:36:15.387 - wlan0: IFNAME=wlan0 BLACKLIST clear -> true
12-16 20:06:39.710 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 20:36:15.499 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 20:36:15.532 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 20:36:15.695 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 20:36:15.926 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 20:36:15.949 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 20:36:15.951 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 20:52:13.041 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 20:52:27.252 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY freq=2467 -> true
12-16 20:52:27.269 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 20:52:27.335 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 20:55:05.704 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:00:43.124 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:00:44.455 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:00:46.795 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:15:23.089 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:18:00.600 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:19:09.671 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:19:36.140 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:24:56.745 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:29:41.365 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:33:44.348 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:36:14.725 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 21:36:14.749 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:36:14.859 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 21:36:14.954 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:36:14.976 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 21:36:14.977 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 21:50:21.445 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:50:46.311 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 21:50:46.323 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 21:50:48.341 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 21:52:04.537 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 21:57:48.874 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 21:59:08.115 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 21:59:08.123 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 21:59:10.217 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 22:00:24.836 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 22:00:57.821 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 22:00:59.766 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 22:11:25.237 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 22:11:39.962 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 22:11:39.964 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 22:11:40.994 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 22:12:34.914 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 22:36:15.949 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 22:36:15.983 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 22:36:16.314 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 22:36:16.780 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 22:36:16.803 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 22:36:16.805 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 22:38:37.589 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 22:40:10.648 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 22:40:10.656 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 22:40:12.634 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 22:51:12.995 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 23:06:10.822 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 23:06:14.162 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 23:06:14.175 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 23:06:16.246 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 23:06:53.641 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 23:07:55.261 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 23:08:54.324 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-16 23:08:54.332 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-16 23:08:55.540 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-16 23:24:04.271 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 23:36:15.324 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-16 23:36:15.359 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 23:36:15.535 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-16 23:36:15.712 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-16 23:36:15.734 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-16 23:36:15.735 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-16 23:36:37.908 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-16 23:36:40.205 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 00:36:13.783 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 00:36:13.816 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 00:36:14.171 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 00:36:14.236 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 00:36:14.256 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 00:36:14.257 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 01:36:17.232 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 01:36:17.266 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 01:36:17.617 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 01:36:17.693 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 01:36:17.714 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 01:36:17.715 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 02:36:17.540 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 02:36:17.566 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 02:36:17.865 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 02:36:17.926 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 02:36:17.949 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 02:36:17.951 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 03:36:16.744 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 03:36:16.766 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 03:36:16.936 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 03:36:17.026 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 03:36:17.047 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 03:36:17.049 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 04:36:16.544 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 04:36:16.578 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 04:36:16.931 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 04:36:17.007 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 04:36:17.030 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 04:36:17.031 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 05:36:17.351 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 05:36:17.382 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 05:36:17.769 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 05:36:17.842 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 05:36:17.865 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 05:36:17.866 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 06:36:15.542 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 06:36:15.565 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 06:36:15.712 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 06:36:15.805 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 06:36:15.828 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 06:36:15.829 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 07:36:18.097 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 07:36:18.131 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 07:36:18.490 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 07:36:18.550 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 07:36:18.572 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 07:36:18.573 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 08:36:17.632 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 08:36:17.667 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 08:36:17.837 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 08:36:17.917 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 08:36:17.942 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 08:36:17.943 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 08:53:28.579 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 08:53:32.876 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 08:55:23.502 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 08:56:05.418 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 08:56:05.449 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 08:56:07.527 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 09:04:52.407 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 09:04:52.418 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 09:04:54.448 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 09:09:34.527 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 09:15:14.024 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 09:15:44.730 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 09:36:16.896 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 09:36:16.936 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 09:36:17.291 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 09:36:17.492 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 09:36:17.511 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 09:36:17.513 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 09:36:40.008 - Event [IFNAME=wlan0 CTRL-EVENT-DISCONNECTED bssid=bc:5f:f6:e9:2a:bb reason=0]
12-17 09:36:40.028 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=14 state=0 BSSID=bc:5f:f6:e9:2a:bb SSID=102]
12-17 09:36:40.039 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-17 09:36:40.041 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 09:36:40.042 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 09:36:40.043 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 09:37:07.845 - wlan0: IFNAME=wlan0 SET_NETWORK 14 bssid any -> true
12-17 09:37:07.873 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-17 09:37:07.873 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-17 09:38:25.098 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 09:38:25.099 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 09:38:25.112 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 09:38:25.114 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 09:38:26.469 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 09:38:26.470 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 09:38:38.289 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 09:38:38.291 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 09:38:38.297 - wlan0: IFNAME=wlan0 TERMINATE -> true
12-17 09:38:38.427 - Event [IFNAME=wlan0 CTRL-EVENT-TERMINATING ]
12-17 09:38:38.427 - wlan0: IFNAME=wlan0 closeSupplicantConnection
12-17 11:17:58.462 - startHal stack:  - startHal - processMessage - processMsg - handleMessage - dispatchMessage - loop
12-17 11:17:58.756 - wlan0: IFNAME=wlan0 connectToSupplicant
12-17 11:17:59.604 - wlan0: IFNAME=wlan0 DRIVER MACADDR -> Macaddr = 2c:57:31:c2:1d:2a
12-17 11:17:59.606 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=UNKNOWN]
12-17 11:17:59.608 - wlan0: IFNAME=wlan0 SET SETBAND AUTO -> true
12-17 11:17:59.609 - wlan0: IFNAME=wlan0 SET update_config 1 -> true
12-17 11:17:59.609 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=-1 -> network id / ssid / bssid / flags
0	Tenda_2036D0	f0:b4:29:69:d4:0d	[DISABLED]
1	lenovo-internet	18:64:72:12:ae:71	[DISABLED]
2	\xe9\x95\xbf\xe6\xb3\xb0\xe5\xb9\xbf\xe5\x9c\xba\xe5\x85\x8d\xe8\xb4\xb9wifi	10:0f:0f:80:05:0a	[DISABLED]
3	Hema-Free	14:1f:ba:85:4c:18	[DISABLED]
4	\xe8\x8a\xb1\xe7\x94\x9f\xe9\xbb\x91\xe5\x8d\xa1\xe4\xb8\x93\xe4\xba\xabWiFi	any	[DISABLED]
5	\xe8\x8a\xb1\xe7\x94\x9f\xe5\x9c\xb0\xe9\x93\x81WiFi	06:14:4b:71:31:4c	[DISABLED]
6	+Shjoycity Free Wifi	60:da:83:42:92:11	[DISABLED]
7	AuchanFreeWIFI	any	[DISABLED]
8	Baidu_Friend	any	[DISABLED]
9	xmly2.4	00:27:e3:fc:33:b3	[DISABLED]
10	360\xe8\xa1\x8c\xe8\xbd\xa6\xe8\xae\xb0\xe5\xbd\x95\xe4\xbb\xaa-CCAF	d0:fa:1d:3a:cc:af	[DISABLED]
11	HUAWEI nova 3e	any	[DISABLED]
12	AP_A914DA	any	[DISABLED]
13	a1b2c3d4e5f0	02:1c:c2:4f:94:10	[DISABLED]
14	102	bc:5f:f6:e9:2a:bb	
15	\xe9\x83\xad\xe6\x85\xa7\xe6\x9d\x8f\xe7\x9a\x84 iPhone	a2:3b:e3:df:30:ae	[DISABLED]
16	CMCC-aT9r	cc:f0:fd:29:99:68	[DISABLED]
17	Xiaomi_F57A_0A85	any	[DISABLED]
18	TRG	any	[DISABLED]
19	love on	any	[DISABLED]
20	moto g(6) play test	any	[DISABLED]
21	D-Link_DIR-816	any	[DISABLED]
22	3C	any	[DISABLED]
23	3C_5G	any	[DISABLED]
24	Xiaomi_F57A_0A85_5G	any	[DISABLED]
25	3CC	any	[DISABLED]
26	3CC_5G	any	[DISABLED]
27	200E	any	[DISABLED]
12-17 11:17:59.754 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=27 -> network id / ssid / bssid / flags
12-17 11:17:59.771 - wlan0: IFNAME=wlan0 SET device_name meizu_M6Note_CN -> true
12-17 11:17:59.771 - wlan0: IFNAME=wlan0 SET manufacturer Meizu -> true
12-17 11:17:59.772 - wlan0: IFNAME=wlan0 SET model_name M6 Note -> true
12-17 11:17:59.772 - wlan0: IFNAME=wlan0 SET model_number M6 Note -> true
12-17 11:17:59.772 - wlan0: IFNAME=wlan0 SET serial_number 721QECRM2ZMT4 -> true
12-17 11:17:59.773 - wlan0: IFNAME=wlan0 SET config_methods physical_display virtual_push_button -> true
12-17 11:17:59.773 - wlan0: IFNAME=wlan0 SET device_type 10-0050F204-5 -> true
12-17 11:17:59.773 - wlan0: IFNAME=wlan0 SET tdls_external_control 1 -> true
12-17 11:17:59.773 - wlan0: IFNAME=wlan0 SET disable_scan_offload 1 -> true
12-17 11:17:59.774 - wlan0: IFNAME=wlan0 SET p2p_disabled 1 -> true
12-17 11:17:59.775 - wlan0: IFNAME=wlan0 SCAN_INTERVAL 15 -> true
12-17 11:17:59.776 - wlan0: IFNAME=wlan0 SET external_sim 1 -> true
12-17 11:17:59.779 - wlan0: IFNAME=wlan0 STA_AUTOCONNECT 0 -> true
12-17 11:17:59.780 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-17 11:17:59.782 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-17 11:17:59.785 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-17 11:17:59.786 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 11:17:59.786 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 2 -> true
12-17 11:17:59.787 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 11:17:59.787 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 11:17:59.788 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 3 -> true
12-17 11:17:59.788 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 11:17:59.789 - wlan0: IFNAME=wlan0 STATUS -> wpa_state=DISCONNECTED
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
12-17 11:17:59.789 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 11:17:59.789 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 11:17:59.790 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 11:17:59.801 - p2p0: SET persistent_reconnect 1 -> true
12-17 11:17:59.803 - p2p0: SET device_name 魅蓝 Note6 -> true
12-17 11:17:59.803 - p2p0: SET p2p_ssid_postfix -魅蓝 Note6 -> true
12-17 11:17:59.804 - p2p0: SET device_type 10-0050F204-5 -> true
12-17 11:17:59.805 - p2p0: SET config_methods virtual_push_button physical_display keypad -> true
12-17 11:17:59.805 - p2p0: P2P_SET conc_pref sta -> true
12-17 11:17:59.807 - p2p0: P2P_FLUSH -> true
12-17 11:17:59.808 - p2p0: P2P_SERVICE_FLUSH -> true
12-17 11:17:59.809 - p2p0: LIST_NETWORKS -> network id / ssid / bssid / flags
12-17 11:17:59.846 - p2p0: SAVE_CONFIG -> true
12-17 11:17:59.850 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 11:17:59.851 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 11:17:59.852 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 11:18:00.872 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 11:18:00.873 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-17 11:18:00.873 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 11:18:01.024 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid 28:6c:07:5a:f5:7c -> true
12-17 11:18:01.026 - wlan0: IFNAME=wlan0 SET_NETWORK 26 ssid 3343435f3547 -> true
12-17 11:18:01.027 - wlan0: IFNAME=wlan0 SET_NETWORK 26 id_str "%7B%22creatorUid%22%3A%2210373%22%2C%22configKey%22%3A%22%5C%223CC_5G%5C%22WPA_PSK%22%7D" -> true
12-17 11:18:01.027 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid 28:6c:07:5a:f5:7c -> true
12-17 11:18:01.028 - wlan0: IFNAME=wlan0 SET_NETWORK 26 key_mgmt WPA-PSK -> true
12-17 11:18:01.028 - wlan0: IFNAME=wlan0 SET_NETWORK 26 proto WPA RSN -> true
12-17 11:18:01.029 - wlan0: IFNAME=wlan0 SET_NETWORK 26 auth_alg OPEN -> true
12-17 11:18:01.029 - wlan0: IFNAME=wlan0 SET_NETWORK 26 pairwise NONE TKIP CCMP -> true
12-17 11:18:01.030 - wlan0: IFNAME=wlan0 SET_NETWORK 26 group TKIP CCMP SMS4 -> true
12-17 11:18:01.030 - wlan0: IFNAME=wlan0 SET_NETWORK 26 sim_num 1 -> true
12-17 11:18:01.031 - wlan0: IFNAME=wlan0 SET_NETWORK 26 priority 0 -> true
12-17 11:18:01.031 - wlan0: IFNAME=wlan0 SET_NETWORK 26 flag 1 -> true
12-17 11:18:01.046 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-17 11:18:01.097 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-17 11:18:01.099 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-17 11:18:01.208 - Event [IFNAME=wlan0 Request association with 28:6c:07:5a:f5:7c]
12-17 11:18:01.209 - wlan0: IFNAME=wlan0 SELECT_NETWORK 26 -> true
12-17 11:18:01.209 - Event [IFNAME=wlan0 Trying to associate with SSID '3CC_5G']
12-17 11:18:01.209 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=5 BSSID=00:00:00:00:00:00 SSID=3CC_5G]
12-17 11:18:01.212 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-17 11:18:01.436 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=6 BSSID=00:00:00:00:00:00 SSID=3CC_5G]
12-17 11:18:01.437 - Event [IFNAME=wlan0 Associated with 28:6c:07:5a:f5:7c]
12-17 11:18:01.437 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-17 11:18:01.746 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=7 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 11:18:01.777 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=8 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 11:18:01.778 - Event [IFNAME=wlan0 WPA: Key negotiation completed with 28:6c:07:5a:f5:7c [PTK=CCMP GTK=TKIP]]
12-17 11:18:01.778 - Event [IFNAME=wlan0 CTRL-EVENT-CONNECTED - Connection to 28:6c:07:5a:f5:7c completed [id=26 id_str=%7B%22creatorUid%22%3A%2210373%22%2C%22configKey%22%3A%22%5C%223CC_5G%5C%22WPA_PSK%22%7D]]
12-17 11:18:01.779 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=9 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 11:18:01.790 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 11:18:01.793 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 11:18:01.794 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 11:18:01.795 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 11:18:01.807 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 11:18:01.808 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-REMOVE 2 -> true
12-17 11:18:01.809 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 11:18:01.873 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 11:18:01.874 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 11:18:01.874 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 11:18:01.986 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 11:18:01.987 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 11:18:01.990 - wlan0: IFNAME=wlan0 BLACKLIST clear -> true
12-17 11:21:11.307 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 11:22:02.948 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 11:23:12.386 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 11:34:30.324 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 11:35:09.135 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY freq=2412,2422,2437,2462,5180,5240,5745,5765,5785 -> true
12-17 11:35:09.141 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 11:35:09.685 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 11:35:17.910 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 11:35:17.911 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 11:35:19.232 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 11:35:31.474 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 11:35:31.475 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 11:35:32.796 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 11:35:58.608 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 11:35:58.609 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 11:36:00.902 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 11:36:39.128 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 11:45:04.662 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 12:00:59.963 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 12:02:38.117 - Event [IFNAME=wlan0 CTRL-EVENT-DISCONNECTED bssid=28:6c:07:5a:f5:7c reason=0]
12-17 12:02:38.251 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=0 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 12:02:38.297 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 12:02:38.300 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 12:02:38.302 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 12:02:38.303 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 12:02:38.317 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 12:02:38.325 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-17 12:02:38.327 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-17 12:07:16.339 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 12:07:16.340 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 12:07:16.360 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:07:16.372 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 12:07:17.550 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 12:07:17.551 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:07:36.329 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 12:07:36.330 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:07:36.331 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 12:07:37.487 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 12:07:37.488 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:08:16.284 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 12:08:16.284 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:08:16.285 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 12:08:17.479 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 12:08:17.480 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 12:09:05.448 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 12:09:05.450 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 12:09:05.457 - wlan0: IFNAME=wlan0 TERMINATE -> true
12-17 12:09:05.757 - Event [IFNAME=wlan0 CTRL-EVENT-TERMINATING ]
12-17 12:09:05.758 - wlan0: IFNAME=wlan0 closeSupplicantConnection
12-17 15:37:01.264 - startHal stack:  - startHal - processMessage - processMsg - handleMessage - dispatchMessage - loop
12-17 15:37:01.565 - wlan0: IFNAME=wlan0 connectToSupplicant
12-17 15:37:02.211 - wlan0: IFNAME=wlan0 DRIVER MACADDR -> Macaddr = 2c:57:31:c2:1d:2a
12-17 15:37:02.212 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=UNKNOWN]
12-17 15:37:02.214 - wlan0: IFNAME=wlan0 SET SETBAND AUTO -> true
12-17 15:37:02.217 - wlan0: IFNAME=wlan0 SET update_config 1 -> true
12-17 15:37:02.218 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=-1 -> network id / ssid / bssid / flags
0	Tenda_2036D0	f0:b4:29:69:d4:0d	[DISABLED]
1	lenovo-internet	18:64:72:12:ae:71	[DISABLED]
2	\xe9\x95\xbf\xe6\xb3\xb0\xe5\xb9\xbf\xe5\x9c\xba\xe5\x85\x8d\xe8\xb4\xb9wifi	10:0f:0f:80:05:0a	[DISABLED]
3	Hema-Free	14:1f:ba:85:4c:18	[DISABLED]
4	\xe8\x8a\xb1\xe7\x94\x9f\xe9\xbb\x91\xe5\x8d\xa1\xe4\xb8\x93\xe4\xba\xabWiFi	any	[DISABLED]
5	\xe8\x8a\xb1\xe7\x94\x9f\xe5\x9c\xb0\xe9\x93\x81WiFi	06:14:4b:71:31:4c	[DISABLED]
6	+Shjoycity Free Wifi	60:da:83:42:92:11	[DISABLED]
7	AuchanFreeWIFI	any	[DISABLED]
8	Baidu_Friend	any	[DISABLED]
9	xmly2.4	00:27:e3:fc:33:b3	[DISABLED]
10	360\xe8\xa1\x8c\xe8\xbd\xa6\xe8\xae\xb0\xe5\xbd\x95\xe4\xbb\xaa-CCAF	d0:fa:1d:3a:cc:af	[DISABLED]
11	HUAWEI nova 3e	any	[DISABLED]
12	AP_A914DA	any	[DISABLED]
13	a1b2c3d4e5f0	02:1c:c2:4f:94:10	[DISABLED]
14	102	bc:5f:f6:e9:2a:bb	
15	\xe9\x83\xad\xe6\x85\xa7\xe6\x9d\x8f\xe7\x9a\x84 iPhone	a2:3b:e3:df:30:ae	[DISABLED]
16	CMCC-aT9r	cc:f0:fd:29:99:68	[DISABLED]
17	Xiaomi_F57A_0A85	any	[DISABLED]
18	TRG	any	[DISABLED]
19	love on	any	[DISABLED]
20	moto g(6) play test	any	[DISABLED]
21	D-Link_DIR-816	any	[DISABLED]
22	3C	any	[DISABLED]
23	3C_5G	any	[DISABLED]
24	Xiaomi_F57A_0A85_5G	any	[DISABLED]
25	3CC	any	[DISABLED]
26	3CC_5G	28:6c:07:5a:f5:7c	[DISABLED]
27	200E	any	[DISABLED]
12-17 15:37:02.538 - wlan0: IFNAME=wlan0 LIST_NETWORKS LAST_ID=27 -> network id / ssid / bssid / flags
12-17 15:37:02.589 - wlan0: IFNAME=wlan0 SET device_name meizu_M6Note_CN -> true
12-17 15:37:02.591 - wlan0: IFNAME=wlan0 SET manufacturer Meizu -> true
12-17 15:37:02.592 - wlan0: IFNAME=wlan0 SET model_name M6 Note -> true
12-17 15:37:02.593 - wlan0: IFNAME=wlan0 SET model_number M6 Note -> true
12-17 15:37:02.593 - wlan0: IFNAME=wlan0 SET serial_number 721QECRM2ZMT4 -> true
12-17 15:37:02.594 - wlan0: IFNAME=wlan0 SET config_methods physical_display virtual_push_button -> true
12-17 15:37:02.595 - wlan0: IFNAME=wlan0 SET device_type 10-0050F204-5 -> true
12-17 15:37:02.596 - wlan0: IFNAME=wlan0 SET tdls_external_control 1 -> true
12-17 15:37:02.597 - wlan0: IFNAME=wlan0 SET disable_scan_offload 1 -> true
12-17 15:37:02.598 - wlan0: IFNAME=wlan0 SET p2p_disabled 1 -> true
12-17 15:37:02.600 - wlan0: IFNAME=wlan0 SCAN_INTERVAL 15 -> true
12-17 15:37:02.601 - wlan0: IFNAME=wlan0 SET external_sim 1 -> true
12-17 15:37:02.604 - wlan0: IFNAME=wlan0 STA_AUTOCONNECT 0 -> true
12-17 15:37:02.605 - Event [IFNAME=wlan0 CTRL-EVENT-REGDOM-CHANGE init=USER type=COUNTRY alpha2=CN]
12-17 15:37:02.608 - wlan0: IFNAME=wlan0 DRIVER COUNTRY CN -> true
12-17 15:37:02.613 - wlan0: IFNAME=wlan0 DRIVER BTCOEXSCAN-STOP -> true
12-17 15:37:02.615 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 15:37:02.617 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 2 -> true
12-17 15:37:02.618 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 15:37:02.619 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 15:37:02.621 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-ADD 3 -> true
12-17 15:37:02.622 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 15:37:02.624 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 15:37:02.624 - wlan0: IFNAME=wlan0 STATUS -> wpa_state=DISCONNECTED
p2p_device_address=2e:57:31:c2:1d:2a
address=2c:57:31:c2:1d:2a
uuid=cd59c852-af04-57f8-a98f-d322844f3803
12-17 15:37:02.626 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 15:37:02.628 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 15:37:02.653 - p2p0: SET persistent_reconnect 1 -> true
12-17 15:37:02.655 - p2p0: SET device_name 魅蓝 Note6 -> true
12-17 15:37:02.655 - p2p0: SET p2p_ssid_postfix -魅蓝 Note6 -> true
12-17 15:37:02.656 - p2p0: SET device_type 10-0050F204-5 -> true
12-17 15:37:02.656 - p2p0: SET config_methods virtual_push_button physical_display keypad -> true
12-17 15:37:02.657 - p2p0: P2P_SET conc_pref sta -> true
12-17 15:37:02.660 - p2p0: P2P_FLUSH -> true
12-17 15:37:02.661 - p2p0: P2P_SERVICE_FLUSH -> true
12-17 15:37:02.661 - p2p0: LIST_NETWORKS -> network id / ssid / bssid / flags
12-17 15:37:02.743 - p2p0: SAVE_CONFIG -> true
12-17 15:37:02.744 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=3 BSSID=00:00:00:00:00:00 SSID=]
12-17 15:37:02.744 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:02.745 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:03.708 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:03.709 - Event [IFNAME=wlan0 WPS-AP-AVAILABLE ]
12-17 15:37:03.709 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=-1 state=0 BSSID=00:00:00:00:00:00 SSID=]
12-17 15:37:03.842 - wlan0: IFNAME=wlan0 SET_NETWORK 26 ssid 3343435f3547 -> true
12-17 15:37:03.843 - wlan0: IFNAME=wlan0 SET_NETWORK 26 id_str "%7B%22creatorUid%22%3A%2210373%22%2C%22configKey%22%3A%22%5C%223CC_5G%5C%22WPA_PSK%22%7D" -> true
12-17 15:37:03.843 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid 28:6c:07:5a:f5:7c -> true
12-17 15:37:03.844 - wlan0: IFNAME=wlan0 SET_NETWORK 26 key_mgmt WPA-PSK -> true
12-17 15:37:03.844 - wlan0: IFNAME=wlan0 SET_NETWORK 26 proto WPA RSN -> true
12-17 15:37:03.845 - wlan0: IFNAME=wlan0 SET_NETWORK 26 auth_alg OPEN -> true
12-17 15:37:03.845 - wlan0: IFNAME=wlan0 SET_NETWORK 26 pairwise NONE TKIP CCMP -> true
12-17 15:37:03.845 - wlan0: IFNAME=wlan0 SET_NETWORK 26 group TKIP CCMP SMS4 -> true
12-17 15:37:03.846 - wlan0: IFNAME=wlan0 SET_NETWORK 26 sim_num 1 -> true
12-17 15:37:03.846 - wlan0: IFNAME=wlan0 SET_NETWORK 26 priority 0 -> true
12-17 15:37:03.846 - wlan0: IFNAME=wlan0 SET_NETWORK 26 flag 1 -> true
12-17 15:37:03.861 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-17 15:37:03.870 - wlan0: IFNAME=wlan0 SAVE_CONFIG -> true
12-17 15:37:03.872 - wlan0: IFNAME=wlan0 SET HS20 0 -> false
12-17 15:37:03.957 - Event [IFNAME=wlan0 Request association with 28:6c:07:5a:f5:7c]
12-17 15:37:03.958 - wlan0: IFNAME=wlan0 SELECT_NETWORK 26 -> true
12-17 15:37:03.958 - Event [IFNAME=wlan0 Trying to associate with SSID '3CC_5G']
12-17 15:37:03.959 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=5 BSSID=00:00:00:00:00:00 SSID=3CC_5G]
12-17 15:37:03.962 - wlan0: IFNAME=wlan0 RECONNECT -> true
12-17 15:37:04.181 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=6 BSSID=00:00:00:00:00:00 SSID=3CC_5G]
12-17 15:37:04.181 - Event [IFNAME=wlan0 Associated with 28:6c:07:5a:f5:7c]
12-17 15:37:04.181 - Event [IFNAME=wlan0 CTRL-EVENT-SUBNET-STATUS-UPDATE status=0]
12-17 15:37:04.387 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=7 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 15:37:04.415 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=8 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 15:37:04.416 - Event [IFNAME=wlan0 WPA: Key negotiation completed with 28:6c:07:5a:f5:7c [PTK=CCMP GTK=TKIP]]
12-17 15:37:04.416 - Event [IFNAME=wlan0 CTRL-EVENT-CONNECTED - Connection to 28:6c:07:5a:f5:7c completed [id=26 id_str=%7B%22creatorUid%22%3A%2210373%22%2C%22configKey%22%3A%22%5C%223CC_5G%5C%22WPA_PSK%22%7D]]
12-17 15:37:04.417 - Event [IFNAME=wlan0 CTRL-EVENT-STATE-CHANGE id=26 state=9 BSSID=28:6c:07:5a:f5:7c SSID=3CC_5G]
12-17 15:37:04.431 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 15:37:04.433 - wlan0: IFNAME=wlan0 SET_NETWORK 26 bssid any -> true
12-17 15:37:04.434 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 15:37:04.435 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 15:37:04.455 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-STOP -> true
12-17 15:37:04.456 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-REMOVE 2 -> true
12-17 15:37:04.458 - wlan0: IFNAME=wlan0 DRIVER RXFILTER-START -> true
12-17 15:37:04.523 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 1 -> true
12-17 15:37:04.524 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 15:37:04.525 - wlan0: IFNAME=wlan0 SET ps 0 -> true
12-17 15:37:04.658 - wlan0: IFNAME=wlan0 SET ps 1 -> true
12-17 15:37:04.660 - wlan0: IFNAME=wlan0 DRIVER BTCOEXMODE 2 -> true
12-17 15:37:04.662 - wlan0: IFNAME=wlan0 BLACKLIST clear -> true
12-17 15:37:05.760 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:05.761 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:07.933 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:11.621 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:11.621 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:13.631 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:16.732 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:16.734 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:18.994 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:22.087 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:22.090 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:23.209 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:26.336 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:26.337 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:27.528 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:30.708 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:30.709 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:31.919 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:35.009 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:35.010 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:36.015 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:39.079 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:39.079 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:40.105 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:43.162 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:43.163 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:44.273 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:47.358 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:47.360 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:48.444 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:51.512 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:51.513 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:53.602 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:37:56.817 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:37:56.830 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:37:58.983 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:02.099 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:02.102 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:03.261 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:06.357 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:06.360 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:07.593 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:10.686 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:10.687 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:11.784 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:14.846 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:14.847 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:15.939 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:19.094 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:19.095 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:20.175 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:23.293 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:23.295 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:24.459 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:27.626 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:27.630 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:28.924 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:32.035 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:32.037 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:33.319 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:36.557 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:36.559 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:37.789 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:41.386 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:41.386 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:42.395 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:45.474 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:45.476 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:47.798 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:50.905 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:50.907 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:51.978 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:55.097 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:55.098 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:38:56.106 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:38:59.195 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:38:59.198 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:00.472 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:03.637 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:03.650 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:04.749 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:07.861 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:07.864 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:09.175 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:12.241 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:12.242 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:13.305 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:16.399 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:16.402 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:17.644 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:20.728 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:20.730 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:21.911 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:25.007 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:25.008 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:27.130 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:30.239 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:30.240 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:32.454 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:35.558 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:35.561 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:36.732 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:39.826 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:39.827 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:40.885 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:43.951 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:43.954 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:45.085 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:48.193 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:48.194 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:49.298 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:52.373 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:52.374 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:53.297 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:39:56.392 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:39:56.395 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:39:57.697 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:00.799 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:00.802 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:02.092 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:05.188 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:05.192 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:06.432 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:09.548 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:09.549 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:10.586 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:13.730 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:13.731 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:14.756 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:17.838 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:17.841 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:20.084 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:23.162 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:23.162 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:24.326 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:27.427 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:27.428 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:28.456 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:31.568 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:31.569 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:32.735 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:35.854 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:35.857 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:37.164 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:40.267 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:40.268 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:41.400 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:44.517 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:44.520 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:45.627 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:48.736 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:48.739 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:50.334 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:53.404 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:53.405 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:54.476 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:40:57.637 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:40:57.639 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:40:58.997 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:02.076 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:02.080 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:03.315 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:06.420 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:06.424 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:07.629 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:10.732 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:10.735 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:11.901 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:15.043 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:15.045 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:16.117 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:19.204 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:19.204 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:20.276 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:23.342 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:23.343 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:24.356 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:27.462 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:27.464 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:29.754 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:32.836 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:32.839 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:34.142 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:37.252 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:37.255 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:38.520 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:41.611 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:41.612 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:42.748 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:45.831 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:45.834 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:47.099 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:50.218 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:50.221 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:51.460 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:54.550 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:54.551 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:41:55.689 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:41:58.829 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:41:58.832 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:00.038 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:03.144 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:03.147 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:04.539 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:07.635 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:07.635 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:08.874 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:11.996 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:12.000 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:13.273 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:16.363 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:16.366 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:17.631 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:20.740 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:20.743 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:21.992 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:25.094 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:25.097 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:26.383 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:29.488 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:29.490 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:30.834 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:33.954 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:33.955 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:35.007 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:38.152 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:38.154 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:39.306 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:42.443 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:42.450 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:43.575 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:46.684 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:46.687 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:47.871 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:50.984 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:50.988 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:52.272 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:55.366 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:42:55.369 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:56.522 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:42:59.625 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:42:59.626 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:00.736 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:03.834 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:03.834 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:04.967 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:08.037 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:08.039 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:09.305 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:12.426 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:12.427 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:13.624 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:16.709 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:16.712 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:17.893 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:20.983 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:20.984 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:22.129 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:25.211 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:25.212 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:26.180 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:29.281 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:29.283 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:30.557 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:33.666 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:33.668 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:34.856 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:37.946 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:37.950 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:39.303 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:42.397 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:42.398 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:43.590 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:46.800 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:46.801 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:47.927 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:51.015 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:51.016 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:52.088 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:55.199 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:55.202 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:43:56.438 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:43:59.538 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:43:59.539 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:00.736 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:03.838 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:03.840 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:05.082 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:08.204 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:08.205 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:09.380 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:12.531 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:12.533 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:13.788 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:16.886 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:16.889 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:18.123 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:21.231 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:21.233 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:22.529 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:25.651 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:25.653 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:26.911 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:30.029 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:30.032 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:31.345 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:34.468 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:34.471 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:35.727 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:38.825 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:38.828 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:40.126 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:43.235 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:43.238 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:44.379 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:47.480 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:47.482 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:48.670 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:51.779 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:51.782 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:53.153 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:44:56.256 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:44:56.259 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:44:57.447 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:00.556 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:00.558 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:01.811 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:04.927 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:04.930 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:06.209 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:09.328 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:09.331 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:10.652 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:13.749 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:13.750 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:14.854 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:17.965 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:17.968 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:19.232 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:22.328 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:22.331 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:23.555 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:26.664 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:26.667 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:28.031 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:31.168 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:31.169 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:32.229 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:35.396 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:35.399 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:36.649 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:39.746 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:39.748 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:41.049 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:44.134 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:44.135 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:45.164 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:48.294 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:48.295 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:49.639 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:52.738 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:52.739 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:53.835 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:45:56.980 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:45:56.983 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:45:58.272 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:01.391 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:01.394 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:02.829 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:05.935 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:05.938 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:07.184 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:10.316 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:10.319 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:11.544 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:14.653 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:14.655 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:15.691 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:18.811 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:18.814 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:20.152 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:23.251 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:23.252 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:24.411 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:27.521 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:27.524 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:28.804 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:31.929 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:31.932 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:33.232 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:36.337 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:36.339 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:37.573 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:40.699 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:40.702 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:42.033 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:45.137 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:45.140 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:46.209 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:49.348 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:49.349 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:50.438 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:53.579 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:53.583 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:54.873 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:46:57.974 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:46:57.977 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:46:59.209 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:02.328 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:02.329 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:03.458 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:06.555 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:06.557 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:07.846 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:10.928 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:10.932 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:12.259 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:15.363 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:15.366 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:16.572 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:19.693 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:19.695 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:20.907 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:24.017 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:24.020 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:25.246 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:28.348 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:28.352 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:29.593 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:32.690 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:32.691 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:33.728 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:36.854 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:36.857 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:38.132 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:41.225 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:41.226 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:42.417 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:45.513 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:45.517 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:46.733 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:49.880 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:49.883 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:51.095 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:54.201 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:54.205 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:55.483 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:47:58.610 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:47:58.614 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:47:59.845 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:02.935 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:02.937 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:04.005 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:07.146 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:07.149 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:08.413 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:11.523 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:11.526 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:12.749 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:15.844 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:15.847 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:17.151 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:20.244 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:20.245 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:21.479 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:24.624 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:24.628 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:25.826 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:28.925 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:28.927 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:30.247 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:33.342 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:33.344 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:34.613 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:37.717 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:37.721 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:38.912 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:42.049 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:42.050 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:43.128 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:46.226 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:46.229 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:47.451 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:50.543 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:50.546 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:51.713 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:54.837 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:54.841 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:48:56.123 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:48:59.247 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:48:59.250 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:00.425 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:03.528 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:03.531 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:04.720 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:07.834 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:07.837 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:09.094 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:12.197 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:12.200 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:13.391 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:16.508 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:16.510 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:17.576 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:20.692 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:20.695 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:22.061 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:25.147 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:25.148 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:26.265 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:29.364 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:29.367 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:30.611 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:49:33.703 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 15:49:33.706 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 15:49:34.912 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 15:55:44.465 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 16:29:36.980 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 16:29:38.707 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 16:33:39.676 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 16:39:37.602 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 16:46:59.581 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 16:47:38.763 - wlan0: IFNAME=wlan0 SCAN TYPE=ONLY -> true
12-17 16:47:38.791 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-STARTED ]
12-17 16:47:40.939 - Event [IFNAME=wlan0 CTRL-EVENT-SCAN-RESULTS ]
12-17 16:52:28.276 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 17:11:29.584 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 17:27:40.533 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 17:38:09.531 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 17:44:14.486 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 17:57:19.359 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 18:02:45.200 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 18:07:50.190 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
12-17 18:20:26.223 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 1 -> true
12-17 18:24:13.086 - wlan0: IFNAME=wlan0 DRIVER SETSUSPENDMODE 0 -> true
WifiNative - Log End ----
Dump of WifiQualifiedNetworkSelector
WifiQualifiedNetworkSelector - Log Begin ----
12-15 21:49:11.501 - ==========start qualified Network Selection==========
12-15 21:49:11.503 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-15 21:49:11.504 - isCarrierNetworkEnabledByUser: false
12-15 21:49:11.509 - maomaochong:f0:b4:29:52:8e:bb(2.4GHz)-89 /  skipped due to low signal

12-15 21:49:11.509 - 102_plus:40:31:3c:92:07:b5 / yyy:06:d3:b0:fd:c0:87 / Zora:b8:f8:83:49:d9:20 / Danke:d4:ee:07:4c:3e:30 / JCG-6774FC-2.4G:06:5f:a7:77:74:fc /  skipped due to not saved
 
12-15 21:49:11.509 - d6:ee:07:4c:3e:31 /  skipped due to not valid SSID

12-15 21:49:11.510 - 
12-15 21:49:11.510 - Checking the externalScoreEvaluator for candidates...
12-15 21:49:11.510 - ExternalScoreEvaluator did not see any good candidates.
12-15 21:49:11.510 - Carrier candidate::null
12-15 21:49:11.510 - Can not find any suitable candidates
12-15 21:49:18.473 - mBroadcastReceiver: onReceive android.telephony.action.CARRIER_CONFIG_CHANGED
12-15 21:49:24.904 - ==========start qualified Network Selection==========
12-15 21:49:24.906 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-15 21:49:24.907 - isCarrierNetworkEnabledByUser: false
12-15 21:49:24.918 - maomaochong:f0:b4:29:52:8e:bb(2.4GHz)-89 / hongyu:28:6c:07:38:e8:78(2.4GHz)-86 /  skipped due to low signal

12-15 21:49:24.918 - 102_plus:40:31:3c:92:07:b5 / yyy:06:d3:b0:fd:c0:87 / Zora:b8:f8:83:49:d9:20 / Danke:d4:ee:07:4c:3e:30 / JCG-6774FC-2.4G:06:5f:a7:77:74:fc /  skipped due to not saved
 
12-15 21:49:24.919 - d6:ee:07:4c:3e:31 /  skipped due to not valid SSID

12-15 21:49:24.919 -  RSSI score: 80 Secure network Bonus:80 Score for scanResult: SSID: 102, BSSID: bc:5f:f6:e9:2a:bb, capabilities: [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS], level: -65, frequency: 2467, timestamp: 72631796, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 2457, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 14 final score:160


12-15 21:49:24.920 - After user choice adjust, the final candidate is:"102":14 : bc:5f:f6:e9:2a:bb
12-15 21:49:24.920 - reconnect from Disconnected to "102":14
12-15 21:49:37.633 - mBroadcastReceiver: onReceive android.telephony.action.CARRIER_CONFIG_CHANGED
12-15 21:49:37.780 - mBroadcastReceiver: onReceive android.telephony.action.CARRIER_CONFIG_CHANGED
12-16 10:52:52.048 - ==========start qualified Network Selection==========
12-16 10:52:52.052 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 10:52:52.053 - isCarrierNetworkEnabledByUser: false
12-16 10:52:52.058 -  skipped due to low signal

12-16 10:52:52.058 - bycscap:50:fa:84:6f:08:bb / Xiaomi_B8BD_5G:78:11:dc:5c:b8:bf / MERCURY_E04B:bc:5f:f6:7b:e0:4b / Tenda_2F888:c8:3a:35:2f:b4:70 / Xiaomi_B8BD:78:11:dc:5c:b8:be / laoding:18:31:bf:c4:57:f8 / bycscap:50:fa:84:6f:08:ba / bycscap:50:fa:84:70:35:b4 / laoding_5G:18:31:bf:c4:57:fc / TP-LINK_F112:14:75:90:63:f1:12 / MERCURY_BE38:bc:54:fc:13:be:38 / TP-LINK_B520:d8:15:0d:b1:b5:20 / MER661:1c:60:de:ff:61:9a / luosongtang:bc:54:fc:49:49:77 / TP-LINK_5G_7035B5:50:fa:84:70:35:b5 / TP-LINK_EFAD:20:6b:e7:bb:ef:ad / Tenda_37B7C8:c8:3a:35:37:b7:c8 /  skipped due to not saved
 
12-16 10:52:52.059 - 7a:11:dc:5c:b8:be /  skipped due to not valid SSID

12-16 10:52:52.060 - 
12-16 10:52:52.060 - Checking the externalScoreEvaluator for candidates...
12-16 10:52:52.060 - ExternalScoreEvaluator did not see any good candidates.
12-16 10:52:52.061 - Carrier candidate::null
12-16 10:52:52.061 - Can not find any suitable candidates
12-16 10:53:04.501 - ==========start qualified Network Selection==========
12-16 10:53:04.504 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 10:53:04.504 - isCarrierNetworkEnabledByUser: false
12-16 10:53:04.508 -  skipped due to low signal

12-16 10:53:04.508 - bycscap:50:fa:84:6f:08:bb / Xiaomi_B8BD_5G:78:11:dc:5c:b8:bf / MERCURY_E04B:bc:5f:f6:7b:e0:4b / Tenda_2F888:c8:3a:35:2f:b4:70 / Xiaomi_B8BD:78:11:dc:5c:b8:be / laoding:18:31:bf:c4:57:f8 / bycscap:50:fa:84:6f:08:ba / bycscap:50:fa:84:70:35:b4 / laoding_5G:18:31:bf:c4:57:fc / TP-LINK_F112:14:75:90:63:f1:12 / MERCURY_BE38:bc:54:fc:13:be:38 / TP-LINK_B520:d8:15:0d:b1:b5:20 / MER661:1c:60:de:ff:61:9a / luosongtang:bc:54:fc:49:49:77 / TP-LINK_5G_7035B5:50:fa:84:70:35:b5 / TP-LINK_EFAD:20:6b:e7:bb:ef:ad / Tenda_37B7C8:c8:3a:35:37:b7:c8 / MERCURY_5E36:6c:59:40:cc:5e:36 / MERCURY_1100:50:3a:a0:f0:1c:be /   小米共享WiFi_A7A2:36:ce:00:21:a7:a3 / AYCHC2:0e:4b:54:12:14:b5 /  skipped due to not saved
 
12-16 10:53:04.508 - 7a:11:dc:5c:b8:be /  skipped due to not valid SSID

12-16 10:53:04.508 - 
12-16 10:53:04.509 - Checking the externalScoreEvaluator for candidates...
12-16 10:53:04.509 - ExternalScoreEvaluator did not see any good candidates.
12-16 10:53:04.509 - Carrier candidate::null
12-16 10:53:04.509 - Can not find any suitable candidates
12-16 18:39:38.637 - ==========start qualified Network Selection==========
12-16 18:39:38.639 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:39:38.640 - isCarrierNetworkEnabledByUser: false
12-16 18:39:38.641 - TP-LINK_5G_618F:dc:fe:18:69:61:91(5GHz)-83 /  skipped due to low signal

12-16 18:39:38.641 - TP_101:de:fe:18:79:61:8f / TP-LINK_618F:dc:fe:18:69:61:8f / 6022:8c:a6:df:6e:e2:6d / attena01:b8:f8:83:fd:c7:14 / XJGCCX:8a:25:93:6e:3e:57 / ChinaNet-ZXSn:82:c7:14:56:4e:08 /  skipped due to not saved
 
12-16 18:39:38.641 - c4:f0:81:65:16:4d /  skipped due to not valid SSID

12-16 18:39:38.641 - 
12-16 18:39:38.641 - Checking the externalScoreEvaluator for candidates...
12-16 18:39:38.642 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:39:38.642 - Carrier candidate::null
12-16 18:39:38.642 - Can not find any suitable candidates
12-16 18:50:38.809 - ==========start qualified Network Selection==========
12-16 18:50:38.809 - empty scan result
12-16 18:50:38.811 - Quit qualified Network Selection since it is not forced and current network is qualified already
12-16 18:50:59.281 - ==========start qualified Network Selection==========
12-16 18:50:59.283 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:50:59.284 - isCarrierNetworkEnabledByUser: false
12-16 18:50:59.284 - LEKSH:0c:4b:54:08:bb:37(2.4GHz)-88 /  skipped due to low signal

12-16 18:50:59.285 - 212:20:6b:e7:a4:4b:1e / NETGEAR:84:1b:5e:37:73:be /  skipped due to not saved
 
12-16 18:50:59.285 -  skipped due to not valid SSID

12-16 18:50:59.285 - 
12-16 18:50:59.285 - Checking the externalScoreEvaluator for candidates...
12-16 18:50:59.285 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:50:59.286 - Carrier candidate::null
12-16 18:50:59.286 - Can not find any suitable candidates
12-16 18:53:43.357 - ==========start qualified Network Selection==========
12-16 18:53:43.359 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:53:43.359 - isCarrierNetworkEnabledByUser: false
12-16 18:53:43.362 - Tenda_5141B0:c8:3a:35:51:41:b0(2.4GHz)-86 /  skipped due to low signal

12-16 18:53:43.362 - 1102he666:80:89:17:a5:38:95 / TP-LINK_6AC4:14:75:90:be:6a:c4 / holiday:b8:08:d7:8b:38:80 / TP-LINK_71:20:6b:e7:9e:71:aa / TP-LINK_5356:24:69:68:96:53:56 / 518:1c:fa:68:b0:88:2c / TP_101:de:fe:18:79:61:8f / Xiaomi_7DF2:f0:b4:29:6a:7d:f3 / @PHICOMM_12:fc:7c:02:78:0b:13 / ChinaNet-vR2U-5G:60:2e:20:a1:55:d4 / 6022:8c:a6:df:6e:e2:6d / TP-LINK_618F:dc:fe:18:69:61:8f / 密码是不知道:74:c3:30:d7:29:0c / ChinaNet-vR2U:60:2e:20:a1:55:d0 / Free-WiFi:60:83:34:89:7d:90 / CarDV_WIFI:94:a1:a2:61:c4:56 / ChinaNet-DH9U:0c:6a:bc:5c:4f:9d / @PHICOMM_71:6a:db:54:15:f5:73 / midea_da_0031:38:21:87:5a:5f:61 / TP-LINK_575724:94:0c:6d:57:57:24 /  skipped due to not saved
 
12-16 18:53:43.362 -  skipped due to not valid SSID

12-16 18:53:43.362 - 
12-16 18:53:43.363 - Checking the externalScoreEvaluator for candidates...
12-16 18:53:43.363 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:53:43.363 - Carrier candidate::null
12-16 18:53:43.363 - Can not find any suitable candidates
12-16 18:54:17.544 - ==========start qualified Network Selection==========
12-16 18:54:17.556 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:54:17.556 - isCarrierNetworkEnabledByUser: false
12-16 18:54:17.561 - Tenda_5141B0:c8:3a:35:51:41:b0(2.4GHz)-86 /  skipped due to low signal

12-16 18:54:17.562 - 1102he666:80:89:17:a5:38:95 / TP-LINK_6AC4:14:75:90:be:6a:c4 / holiday:b8:08:d7:8b:38:80 / TP-LINK_71:20:6b:e7:9e:71:aa / TP-LINK_5356:24:69:68:96:53:56 / 518:1c:fa:68:b0:88:2c / TP_101:de:fe:18:79:61:8f / Xiaomi_7DF2:f0:b4:29:6a:7d:f3 / @PHICOMM_12:fc:7c:02:78:0b:13 / ChinaNet-vR2U-5G:60:2e:20:a1:55:d4 / 6022:8c:a6:df:6e:e2:6d / TP-LINK_618F:dc:fe:18:69:61:8f / 密码是不知道:74:c3:30:d7:29:0c / ChinaNet-vR2U:60:2e:20:a1:55:d0 / Free-WiFi:60:83:34:89:7d:90 / CarDV_WIFI:94:a1:a2:61:c4:56 / ChinaNet-DH9U:0c:6a:bc:5c:4f:9d / @PHICOMM_71:6a:db:54:15:f5:73 / midea_da_0031:38:21:87:5a:5f:61 / TP-LINK_575724:94:0c:6d:57:57:24 / QD_883B8B00E0FA:88:3b:8b:00:e0:fb / attena01:b8:f8:83:fd:c7:14 / HUAWEI-VSZTU2:c4:f0:81:65:16:4c / attena:f4:83:cd:2c:52:77 / TP-GTWC14-401:48:7d:2e:a0:c0:81 / TP-LINK_5G_B64E:14:75:90:1d:b6:4e / S6:40:16:9f:40:38:52 / TP-LINK_LOVE:e4:d3:32:eb:10:b8 / s6-3lou:20:6b:e7:ba:1f:93 / TP-LINK_601:bc:46:99:7f:c4:a8 / TP-LINK_MM:30:fc:68:60:c9:0b / Tenda_055AC8:c8:3a:35:05:5a:c8 / ChinaNet-4Sf6:34:4d:ea:04:4c:0b / MERCURY_A5C11F:cc:34:29:a5:c1:1f /  skipped due to not saved
 
12-16 18:54:17.562 -  skipped due to not valid SSID

12-16 18:54:17.562 - 
12-16 18:54:17.562 - Checking the externalScoreEvaluator for candidates...
12-16 18:54:17.563 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:54:17.563 - Carrier candidate::null
12-16 18:54:17.563 - Can not find any suitable candidates
12-16 18:55:29.472 - ==========start qualified Network Selection==========
12-16 18:55:29.474 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:55:29.475 - isCarrierNetworkEnabledByUser: false
12-16 18:55:29.480 - TP-LINK_AF80:34:96:72:9b:af:80(2.4GHz)-87 / dengfen2:bc:46:99:b8:2c:06(2.4GHz)-87 /  skipped due to low signal

12-16 18:55:29.480 - TP-GTWC14-401:48:7d:2e:a0:c0:81 / TP-LINK_5G_B64E:14:75:90:1d:b6:4e / TP-LINK_MM:30:fc:68:60:c9:0b / fujianshaxian:28:6c:07:31:21:c9 / 赵敏:9c:a6:15:a1:a8:a1 / 赵敏:9c:a6:15:a1:a8:9f / MERCURY_0543:bc:5f:f6:79:05:43 / Xiaomi_7AA3:28:6c:07:53:7a:a4 / ChinaNet-QELE:98:f4:28:02:b5:88 / CMCC-btAH:74:ec:42:57:18:22 / MYCQ:dc:fe:18:b3:1d:fd / HUAWEI-B310-6473:10:44:00:60:64:73 / TP-LINK_LXYCSHH:30:b4:9e:3c:34:10 / MYCQ:dc:fe:18:b3:25:3a / ChinaNet-KLGD:68:9f:f0:68:97:22 / YUANGONG:a4:fb:8d:06:f4:57 / Tenda_225148:c8:3a:35:22:51:48 / ChinaNet-F9GL:44:f4:36:5a:de:db / ChinaNet-GRbx:14:e9:b2:3c:13:5d /  skipped due to not saved
 
12-16 18:55:29.481 - 2a:6c:07:50:7a:a4 /  skipped due to not valid SSID

12-16 18:55:29.481 - 
12-16 18:55:29.481 - Checking the externalScoreEvaluator for candidates...
12-16 18:55:29.481 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:55:29.481 - Carrier candidate::null
12-16 18:55:29.482 - Can not find any suitable candidates
12-16 18:55:49.505 - ==========start qualified Network Selection==========
12-16 18:55:49.508 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:55:49.508 - isCarrierNetworkEnabledByUser: false
12-16 18:55:49.514 - dengfen2:bc:46:99:b8:2c:06(2.4GHz)-87 / midea_ca_0495:38:21:87:1f:d5:50(2.4GHz)-88 /  skipped due to low signal

12-16 18:55:49.515 - TP-GTWC14-401:48:7d:2e:a0:c0:81 / TP-LINK_5G_B64E:14:75:90:1d:b6:4e / TP-LINK_MM:30:fc:68:60:c9:0b / fujianshaxian:28:6c:07:31:21:c9 / 赵敏:9c:a6:15:a1:a8:a1 / 赵敏:9c:a6:15:a1:a8:9f / MERCURY_0543:bc:5f:f6:79:05:43 / Xiaomi_7AA3:28:6c:07:53:7a:a4 / ChinaNet-QELE:98:f4:28:02:b5:88 / CMCC-btAH:74:ec:42:57:18:22 / MYCQ:dc:fe:18:b3:1d:fd / HUAWEI-B310-6473:10:44:00:60:64:73 / TP-LINK_LXYCSHH:30:b4:9e:3c:34:10 / MYCQ:dc:fe:18:b3:25:3a / ChinaNet-KLGD:68:9f:f0:68:97:22 / YUANGONG:a4:fb:8d:06:f4:57 / Tenda_225148:c8:3a:35:22:51:48 / ChinaNet-F9GL:44:f4:36:5a:de:db / TP-LINK_AF80:34:96:72:9b:af:80 / ChinaNet-GRbx:14:e9:b2:3c:13:5d / 福建沙县小吃_plus:28:6c:07:00:5d:39 / shiang:88:25:93:4a:bd:13 / TP-LINK-THROW:fc:d7:33:76:b9:2c / SIYU:ec:8a:c7:1c:cb:3d / CU_7kMJ:98:f4:28:fe:fe:c8 / QIJI:6a:db:54:15:e3:16 / 201:c8:3a:35:3f:62:d0 / ChinaNet-Wpce:08:3f:bc:aa:89:f1 /   小米共享WiFi_21C8:2a:6c:07:31:21:c9 / OPPLE-2B56:b0:d5:9d:b8:2b:56 /  skipped due to not saved
 
12-16 18:55:49.515 - 2a:6c:07:50:7a:a4 /  skipped due to not valid SSID

12-16 18:55:49.515 - 
12-16 18:55:49.515 - Checking the externalScoreEvaluator for candidates...
12-16 18:55:49.516 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:55:49.516 - Carrier candidate::null
12-16 18:55:49.516 - Can not find any suitable candidates
12-16 18:56:09.482 - ==========start qualified Network Selection==========
12-16 18:56:09.484 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:56:09.484 - isCarrierNetworkEnabledByUser: false
12-16 18:56:09.597 - dengfen2:bc:46:99:b8:2c:06(2.4GHz)-87 / midea_ca_0495:38:21:87:1f:d5:50(2.4GHz)-88 / QIJI_5G:68:db:54:15:e3:16(5GHz)-83 / Tenda_3832:c8:3a:35:4e:40:70(2.4GHz)-87 /  skipped due to low signal

12-16 18:56:09.597 - TP-GTWC14-401:48:7d:2e:a0:c0:81 / TP-LINK_5G_B64E:14:75:90:1d:b6:4e / TP-LINK_MM:30:fc:68:60:c9:0b / fujianshaxian:28:6c:07:31:21:c9 / 赵敏:9c:a6:15:a1:a8:a1 / 赵敏:9c:a6:15:a1:a8:9f / MERCURY_0543:bc:5f:f6:79:05:43 / Xiaomi_7AA3:28:6c:07:53:7a:a4 / ChinaNet-QELE:98:f4:28:02:b5:88 / CMCC-btAH:74:ec:42:57:18:22 / MYCQ:dc:fe:18:b3:1d:fd / HUAWEI-B310-6473:10:44:00:60:64:73 / TP-LINK_LXYCSHH:30:b4:9e:3c:34:10 / MYCQ:dc:fe:18:b3:25:3a / ChinaNet-KLGD:68:9f:f0:68:97:22 / YUANGONG:a4:fb:8d:06:f4:57 / Tenda_225148:c8:3a:35:22:51:48 / ChinaNet-F9GL:44:f4:36:5a:de:db / TP-LINK_AF80:34:96:72:9b:af:80 / ChinaNet-GRbx:14:e9:b2:3c:13:5d / 福建沙县小吃_plus:28:6c:07:00:5d:39 / shiang:88:25:93:4a:bd:13 / TP-LINK-THROW:fc:d7:33:76:b9:2c / SIYU:ec:8a:c7:1c:cb:3d / CU_7kMJ:98:f4:28:fe:fe:c8 / QIJI:6a:db:54:15:e3:16 / 201:c8:3a:35:3f:62:d0 / ChinaNet-Wpce:08:3f:bc:aa:89:f1 /   小米共享WiFi_21C8:2a:6c:07:31:21:c9 / OPPLE-2B56:b0:d5:9d:b8:2b:56 / xiaowei:50:bd:5f:d3:82:4a / TP-LINK_302:8c:a6:df:99:05:81 / HUAWEI-9BDZRL:b0:e1:7e:bd:da:9c / MERCURY_4D6E:50:3a:a0:eb:4d:6e / dengfen3:24:69:68:00:50:90 / 360WiFi-239BB8:a8:6b:7c:23:9b:b8 / CMCC-s9QP:70:89:cc:c3:a5:b2 / 202:50:fa:84:91:18:40 / Xiaomi_9CEE:34:ce:00:6b:9c:ef /  skipped due to not saved
 
12-16 18:56:09.598 - 2a:6c:07:50:7a:a4 /  skipped due to not valid SSID

12-16 18:56:09.598 -  RSSI score: 48 Secure network Bonus:80 Score for scanResult: SSID: 102, BSSID: b0:95:8e:48:b3:e7, capabilities: [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS], level: -73, frequency: 2412, timestamp: 76089273312, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 2422, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 14 final score:128


12-16 18:56:09.599 - After user choice adjust, the final candidate is:"102":14 : b0:95:8e:48:b3:e7
12-16 18:56:09.599 - reconnect from Disconnected to "102":14
12-16 18:57:32.947 - ==========start qualified Network Selection==========
12-16 18:57:32.949 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 1 4 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:57:32.949 - isCarrierNetworkEnabledByUser: false
12-16 18:57:32.953 - ChinaNet-tr7S-5G:14:e9:b2:3b:89:ae(5GHz)-83 / CMCC-ANND-5G:e8:b5:41:d7:e0:00(5GHz)-85 / QD_883B8B00D2A3:88:3b:8b:00:d2:a4(2.4GHz)-88 /  skipped due to low signal

12-16 18:57:32.953 - HUAWEI-9BDZRL:b0:e1:7e:bd:da:9c / TP-LINK_A760:30:fc:68:5a:a7:60 / TP-LINK_102:20:6b:e7:a5:61:43 / TP-LINK_5G_5A7D:20:6b:e7:4f:5a:7f / knni:d0:76:e7:2b:60:3d / ChinaNGB-YdJAGS:98:bc:57:69:88:75 / Tenda_55EC80_5G:c8:3a:35:55:ec:85 / xuanzai:28:2c:b2:85:5a:58 / ChinaNet-DMEZ:ec:23:7b:7a:d3:d2 / weiyan5:0c:4b:54:83:a1:24 / maomaochong_5G:f0:b4:29:52:8e:bc / weiyan:0c:4b:54:83:a1:22 / Tenda_55EC80:c8:3a:35:55:ec:81 / @PHICOMM_10:cc:81:da:62:f6:18 / Chinanet-2.4G-7D70:c8:3a:35:ac:7d:71 / ChinaNet-2.4G-F3FE:08:10:7a:41:f3:fe / holiday:b8:08:d7:8b:38:80 / nononono:f0:b4:29:14:c6:de / ChinaNet-PzKy:a0:91:c8:46:66:3f / Michael:30:b4:9e:c0:11:08 / ChinaNet-mmAZ:74:e1:9a:da:19:5d / ChinaNet-StAF:2c:dd:95:35:c6:91 / ChinaNet-6PzR:78:f5:57:9d:da:78 / CMCC-ANND:e8:b5:41:d7:e0:ff / 电竞专用wifi:40:31:3c:0a:66:7c /  skipped due to not saved
 
12-16 18:57:32.953 -  skipped due to not valid SSID

12-16 18:57:32.953 - 
12-16 18:57:32.954 - Checking the externalScoreEvaluator for candidates...
12-16 18:57:32.954 - ExternalScoreEvaluator did not see any good candidates.
12-16 18:57:32.954 - Carrier candidate::null
12-16 18:57:32.955 - Can not find any suitable candidates
12-16 18:58:47.446 - ==========start qualified Network Selection==========
12-16 18:58:47.448 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 1 4 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 18:58:47.449 - isCarrierNetworkEnabledByUser: false
12-16 18:58:47.453 - Tenda_55EC80:c8:3a:35:55:ec:81(2.4GHz)-89 / HUAWEI-301:b4:cd:27:e2:af:10(2.4GHz)-87 / 802b:c8:3a:35:53:69:b8(2.4GHz)-87 / Danke:d4:ee:07:4c:3e:30(2.4GHz)-90 /  skipped due to low signal

12-16 18:58:47.454 - Zora:b8:f8:83:49:d9:20 / 102_plus:40:31:3c:92:07:b5 /  skipped due to not saved
 
12-16 18:58:47.454 - d6:ee:07:4c:3e:31 /  skipped due to not valid SSID

12-16 18:58:47.454 -  RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: 102, BSSID: bc:5f:f6:e9:2a:bb, capabilities: [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS], level: -41, frequency: 2467, timestamp: 76247293242, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 2457, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 14 final score:180


12-16 18:58:47.454 - After user choice adjust, the final candidate is:"102":14 : bc:5f:f6:e9:2a:bb
12-16 18:58:47.454 - reconnect from Disconnected to "102":14
12-16 19:36:14.858 - ==========start qualified Network Selection==========
12-16 19:36:14.860 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-16 19:36:14.861 - isCarrierNetworkEnabledByUser: false
12-16 19:36:14.865 - Ada:ec:88:8f:6c:d8:06(2.4GHz)-87 /  skipped due to low signal

12-16 19:36:14.865 - Zora:b8:f8:83:49:d9:20 / 102_plus:40:31:3c:92:07:b5 / LAOLILAOLAOWANG:e4:f3:f5:2c:3a:c4 / yyy:06:d3:b0:fd:c0:87 / JCG-6774FC-2.4G:06:5f:a7:77:74:fc / ChinaNet-rCq6:74:e1:9a:d4:6d:cd /  skipped due to not saved
 
12-16 19:36:14.865 -  skipped due to not valid SSID

12-16 19:36:14.865 -  RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: 102, BSSID: bc:5f:f6:e9:2a:bb, capabilities: [WPA-PSK-CCMP][WPA2-PSK-CCMP][ESS], level: -60, frequency: 2467, timestamp: 78494726826, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 2457, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 14 final score:180


12-16 19:36:14.865 - After user choice adjust, the final candidate is:"102":14 : bc:5f:f6:e9:2a:bb
12-16 19:36:14.866 - reconnect from Disconnected to "102":14
12-17 09:38:26.492 - ==========start qualified Network Selection==========
12-17 09:38:26.495 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 09:38:26.495 - isCarrierNetworkEnabledByUser: false
12-17 09:38:26.497 - 张亮麻辣烫_5G:68:db:54:e8:f1:8b(5GHz)-83 / shiang2:88:25:93:4a:bd:12(5GHz)-84 / ChinaNet-5nii-5G:dc:c6:4b:5a:8b:40(5GHz)-87 /  skipped due to low signal

12-17 09:38:26.497 - QIJI_5G:68:db:54:15:e3:16 / QIJI:6a:db:54:15:e3:16 / YICarCam_f01559:58:70:c6:f0:15:59 / BHJZ:48:7d:2e:f9:0e:a6 / 8号酒庄:94:d9:b3:be:97:78 / TP-LINK_64B2:bc:46:99:38:64:b2 / TP-LINK302:20:dc:e6:4f:aa:84 / LoriYe:f4:83:cd:cd:9f:7a / ChinaNet-isnH:b4:de:df:22:15:a6 / 201:d0:76:e7:74:c0:28 /  skipped due to not saved
 
12-17 09:38:26.497 - 46:d1:fa:07:97:e7 /  skipped due to not valid SSID

12-17 09:38:26.497 - 
12-17 09:38:26.497 - Checking the externalScoreEvaluator for candidates...
12-17 09:38:26.497 - ExternalScoreEvaluator did not see any good candidates.
12-17 09:38:26.498 - Carrier candidate::null
12-17 09:38:26.498 - Can not find any suitable candidates
12-17 11:18:00.919 - ==========start qualified Network Selection==========
12-17 11:18:00.922 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 11:18:00.922 - isCarrierNetworkEnabledByUser: false
12-17 11:18:01.020 - MAXHUB-H6F:00:25:92:19:ce:1b(5GHz)-83 / RD-Test:18:64:72:21:9d:c3(2.4GHz)-87 /  skipped due to low signal

12-17 11:18:01.020 - D-Link_DIR-816_5G:1c:5f:2b:5e:d5:54 / lenovo:18:64:72:21:9c:30 / lenovo-RD:18:64:72:21:85:d3 / lenovo:18:64:72:21:85:d0 / TP-LINK_GG:8c:21:0a:3c:fe:70 / DIRECT-GOZHUZJ5-PF10GBUUmsJQ:02:e1:8c:d8:25:61 / lenovo:18:64:72:21:86:40 / lenovo-RD:18:64:72:21:86:43 / lenovo-RD:18:64:72:21:6a:13 / lenovo:18:64:72:21:6a:10 / lenovo-RD:18:64:72:21:85:c3 / lenovo:18:64:72:21:85:c0 / lenovo:18:64:72:21:79:e0 / lenovo:18:64:72:11:3d:90 / lenovo-RD:18:64:72:11:3d:93 / lenovo-RD:18:64:72:21:79:e3 / lenovo-RD:18:64:72:11:3d:83 / lenovo:18:64:72:21:6a:00 / lenovo-RD:18:64:72:21:6a:03 / RD-Test:18:64:72:21:85:d2 / RD-Test:18:64:72:21:9c:32 / lenovo-guest:18:64:72:21:9c:33 / lenovo-guest:18:64:72:21:85:d4 / lenovo-guest:18:64:72:21:86:42 / 主卧.b:fa:8f:ca:67:85:c1 / RD-Test:18:64:72:21:86:44 / lenovo-guest:18:64:72:21:6a:14 / RD-Test:18:64:72:21:6a:12 / RD-Test:18:64:72:21:85:c4 / lenovo-guest:18:64:72:21:79:e2 / lenovo-guest:18:64:72:21:85:c2 / lenovo-guest:18:64:72:11:3d:94 / RD-Test:18:64:72:11:3d:92 / RD-Test:18:64:72:21:79:e4 / RD-Test:18:64:72:21:9d:d2 / RD-Test:18:64:72:11:3d:84 / lenovo-guest:18:64:72:21:6a:02 / RD-Test:18:64:72:21:6a:04 / RD-Test:18:64:72:21:9c:23 /  skipped due to not saved
 
12-17 11:18:01.020 -  skipped due to not valid SSID

12-17 11:18:01.020 -  RSSI score: 100 5GHz bonus: 40 Secure network Bonus:80 Score for scanResult: SSID: 3CC_5G, BSSID: 28:6c:07:5a:f5:7c, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS], level: -37, frequency: 5745, timestamp: 135015260271, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 2, centerFreq0: 5775, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 26 final score:220

 RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: 3CC, BSSID: 28:6c:07:5a:f5:7b, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS], level: -22, frequency: 2412, timestamp: 135015260226, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 25 final score:180

 RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: D-Link_DIR-816, BSSID: 1c:5f:2b:5e:d5:53, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS], level: -24, frequency: 2422, timestamp: 135015260262, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 21 final score:180

 RSSI score: 84 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:9c:31, capabilities: [ESS], level: -64, frequency: 5765, timestamp: 135015260413, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5755, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-136

 RSSI score: 80 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:85:d1, capabilities: [ESS], level: -65, frequency: 5785, timestamp: 135015260476, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5795, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-140

 RSSI score: 100 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:86:41, capabilities: [ESS], level: -42, frequency: 2437, timestamp: 135015260302, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-160

 RSSI score: 56 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:6a:11, capabilities: [ESS], level: -71, frequency: 5180, timestamp: 135015260694, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5190, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-164

 RSSI score: 76 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:79:e1, capabilities: [ESS], level: -66, frequency: 2462, timestamp: 135015260590, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-184

 RSSI score: 76 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:85:c1, capabilities: [ESS], level: -66, frequency: 2412, timestamp: 135015260619, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-184

 RSSI score: 40 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:11:3d:91, capabilities: [ESS], level: -75, frequency: 5240, timestamp: 135015260672, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5230, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-180

 RSSI score: 24 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:6a:01, capabilities: [ESS], level: -79, frequency: 2462, timestamp: 135015260770, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-236


12-17 11:18:01.021 - After user choice adjust, the final candidate is:"3CC_5G":26 : 28:6c:07:5a:f5:7c
12-17 11:18:01.021 - reconnect from Disconnected to "3CC_5G":26
12-17 12:07:17.566 - ==========start qualified Network Selection==========
12-17 12:07:17.568 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 12:07:17.568 - isCarrierNetworkEnabledByUser: false
12-17 12:07:17.569 -  skipped due to low signal

12-17 12:07:17.569 - cisco-B706:d8:24:bd:79:b7:08 /  skipped due to not saved
 
12-17 12:07:17.569 -  skipped due to not valid SSID

12-17 12:07:17.570 - 
12-17 12:07:17.570 - Checking the externalScoreEvaluator for candidates...
12-17 12:07:17.570 - ExternalScoreEvaluator did not see any good candidates.
12-17 12:07:17.570 - Carrier candidate::null
12-17 12:07:17.570 - Can not find any suitable candidates
12-17 12:07:37.493 - ==========start qualified Network Selection==========
12-17 12:07:37.495 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 12:07:37.496 - isCarrierNetworkEnabledByUser: false
12-17 12:07:37.497 -  skipped due to low signal

12-17 12:07:37.497 - cisco-B706:d8:24:bd:79:b7:08 /  skipped due to not saved
 
12-17 12:07:37.497 -  skipped due to not valid SSID

12-17 12:07:37.498 - 
12-17 12:07:37.498 - Checking the externalScoreEvaluator for candidates...
12-17 12:07:37.498 - ExternalScoreEvaluator did not see any good candidates.
12-17 12:07:37.498 - Carrier candidate::null
12-17 12:07:37.498 - Can not find any suitable candidates
12-17 12:08:17.485 - ==========start qualified Network Selection==========
12-17 12:08:17.490 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 12:08:17.491 - isCarrierNetworkEnabledByUser: false
12-17 12:08:17.492 -  skipped due to low signal

12-17 12:08:17.492 - cisco-B706:d8:24:bd:79:b7:08 /  skipped due to not saved
 
12-17 12:08:17.493 -  skipped due to not valid SSID

12-17 12:08:17.493 - 
12-17 12:08:17.494 - Checking the externalScoreEvaluator for candidates...
12-17 12:08:17.494 - ExternalScoreEvaluator did not see any good candidates.
12-17 12:08:17.494 - Carrier candidate::null
12-17 12:08:17.495 - Can not find any suitable candidates
12-17 15:37:03.743 - ==========start qualified Network Selection==========
12-17 15:37:03.745 - Saved Network List
    "HUAWEI nova 3e":11  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "lenovo-internet":1  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "+Shjoycity Free Wifi":6  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "XXX的 iPhone":15  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3C_5G":23  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC_5G":26  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "3CC":25  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85":17  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "102":14  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "D-Link_DIR-816":21  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "CMCC-aT9r":16  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "AuchanFreeWIFI":7  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "长泰广场免费wifi":2  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "moto g(6) play test":20  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"lenovo-internet"NONE set time:1541745472051
    "Baidu_Friend":8  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "a1b2c3d4e5f0":13  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"长泰广场免费wifi"NONE set time:1535887409146
    "3C":22  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "love on":19  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生地铁WiFi":5  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Hema-Free":3  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "花生黑卡专享WiFi":4  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"花生地铁WiFi"NONE set time:1530082869041
    "200E":27  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "TRG":18  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Tenda_2036D0":0  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "xmly2.4":9  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "Xiaomi_F57A_0A85_5G":24  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:"200E"WPA_PSK set time:1544782706721
    "AP_A914DA":12  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1
    "360行车记录仪-CCAF":10  User Preferred BSSID:null FQDN:null NETWORK_SELECTION_ENABLED Disable account: 0 0 0 0 0 0 0 0 0 0 Connect Choice:null set time:-1

12-17 15:37:03.745 - isCarrierNetworkEnabledByUser: false
12-17 15:37:03.833 - MAXHUB-H6F:00:25:92:19:ce:1b(5GHz)-86 / RD-Test:18:64:72:21:6a:04(2.4GHz)-89 /  skipped due to low signal

12-17 15:37:03.833 - D-Link_DIR-816_5G:1c:5f:2b:5e:d5:54 / TP-LINK_GG:8c:21:0a:3c:fe:70 / DIRECT-GOZHUZJ5-PF10GBUUmsJQ:02:e1:8c:d8:25:61 / lenovo:18:64:72:21:86:40 / lenovo-RD:18:64:72:21:86:43 / lenovo-RD:18:64:72:21:85:d3 / lenovo:18:64:72:21:85:d0 / lenovo:18:64:72:21:9c:30 / lenovo-RD:18:64:72:21:6a:13 / lenovo:18:64:72:21:6a:10 / jonychen:d4:ee:07:23:b7:c2 / lenovo-RD:18:64:72:11:3d:93 / lenovo:18:64:72:11:3d:90 / lenovo-RD:18:64:72:21:85:c3 / lenovo-RD:18:64:72:21:79:e3 / lenovo:18:64:72:21:79:e0 / Snc:08:10:77:75:c3:24 / lenovo-guest:18:64:72:21:86:42 / 主卧.b:fa:8f:ca:67:85:c1 / RD-Test:18:64:72:21:86:44 / lenovo-guest:18:64:72:21:85:d4 / RD-Test:18:64:72:21:85:d2 / lenovo-guest:18:64:72:21:9c:33 / RD-Test:18:64:72:21:9c:32 / lenovo-guest:18:64:72:21:6a:14 / RD-Test:18:64:72:21:6a:12 / RD-Test:18:64:72:11:3d:92 / lenovo-guest:18:64:72:11:3d:94 / RD-Test:18:64:72:21:85:c4 / RD-Test:18:64:72:21:9d:d2 / RD-Test:18:64:72:21:79:e4 / lenovo-guest:18:64:72:21:79:e2 /  skipped due to not saved
 
12-17 15:37:03.834 -  skipped due to not valid SSID

12-17 15:37:03.834 -  RSSI score: 100 5GHz bonus: 40 Secure network Bonus:80 Score for scanResult: SSID: 3CC_5G, BSSID: 28:6c:07:5a:f5:7c, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS], level: -31, frequency: 5745, timestamp: 150560783032, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 2, centerFreq0: 5775, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 26 final score:220

 RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: 3CC, BSSID: 28:6c:07:5a:f5:7b, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS], level: -31, frequency: 2412, timestamp: 150560783054, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 25 final score:180

 RSSI score: 100 Secure network Bonus:80 Score for scanResult: SSID: D-Link_DIR-816, BSSID: 1c:5f:2b:5e:d5:53, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS], level: -40, frequency: 2422, timestamp: 150560783083, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 21 final score:180

 RSSI score: 100 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:86:41, capabilities: [ESS], level: -55, frequency: 2437, timestamp: 150560783101, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-160

 RSSI score: 64 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:85:d1, capabilities: [ESS], level: -69, frequency: 5785, timestamp: 150560783186, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5795, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-156

 RSSI score: 44 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:9c:31, capabilities: [ESS], level: -74, frequency: 5765, timestamp: 150560783235, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5755, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-176

 RSSI score: 44 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:6a:11, capabilities: [ESS], level: -74, frequency: 5180, timestamp: 150560783284, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5190, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-176

 RSSI score: 36 5GHz bonus: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:11:3d:91, capabilities: [ESS], level: -76, frequency: 5240, timestamp: 150560783378, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 1, centerFreq0: 5230, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-184

 RSSI score: 40 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:85:c1, capabilities: [ESS], level: -75, frequency: 2412, timestamp: 150560783331, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-220

 RSSI score: 36 No internet Penalty:-260 Score for scanResult: SSID: lenovo-internet, BSSID: 18:64:72:21:79:e1, capabilities: [ESS], level: -76, frequency: 2462, timestamp: 150560783418, distance: ?(cm), distanceSd: ?(cm), passpoint: no, ChannelBandwidth: 0, centerFreq0: 0, centerFreq1: 0, 80211mcResponder: is not supported and Network ID: 1 final score:-224


12-17 15:37:03.834 - After user choice adjust, the final candidate is:"3CC_5G":26 : 28:6c:07:5a:f5:7c
12-17 15:37:03.834 - reconnect from Disconnected to "3CC_5G":26
12-17 16:34:27.201 - mBroadcastReceiver: onReceive android.telephony.action.CARRIER_CONFIG_CHANGED
WifiQualifiedNetworkSelector - Log End ----
IpManager.wlan0 APF dump:
  No apf support

IpManager.wlan0 StateMachine dump:
  12-15 21:48:53.527 - CMD_SET_MULTICAST_FILTER wlan0/-1 0 0 true [rcvd_in=StoppedState, proc_in=StoppedState]
  12-15 21:48:56.176 - OBSERVED interfaceAdded(wlan0)
  12-15 21:49:25.528 - CMD_STOP wlan0/-1 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-15 21:49:25.532 - CMD_UPDATE_HTTP_PROXY wlan0/-1 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-15 21:49:25.535 - INVOKE setNeighborDiscoveryOffload(true)
  12-15 21:49:25.536 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/22 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-15 21:49:25.537 - CMD_START wlan0/22 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-15 21:49:25.553 - INVOKE setFallbackMulticastFilter(true)
  12-15 21:49:25.582 - CMD_CONFIRM wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 21:49:25.594 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: []  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 21:49:25.629 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 21:49:25.642 - INVOKE onPreDhcpAction()
  12-15 21:49:25.643 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 21:49:25.646 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 21:49:38.940 - INVOKE onPostDhcpAction()
  12-15 21:49:38.948 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-15 21:49:38.949 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 21:49:38.950 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-15 21:49:38.964 - CMD_CONFIGURE_LINKADDRESS wlan0/22 0 0 192.168.221.108/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-15 21:49:38.966 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 22:49:37.906 - INVOKE onPreDhcpAction()
  12-15 22:49:37.910 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 22:49:38.276 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 22:49:38.347 - INVOKE onPostDhcpAction()
  12-15 22:49:38.348 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-15 22:49:38.349 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 22:49:38.349 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-15 23:49:38.365 - INVOKE onPreDhcpAction()
  12-15 23:49:38.367 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 23:49:38.464 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-15 23:49:38.542 - INVOKE onPostDhcpAction()
  12-15 23:49:38.543 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-15 23:49:38.543 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-15 23:49:38.544 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 00:49:37.452 - INVOKE onPreDhcpAction()
  12-16 00:49:37.454 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 00:49:37.870 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 00:49:37.974 - INVOKE onPostDhcpAction()
  12-16 00:49:37.975 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 00:49:37.976 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 00:49:37.977 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 01:49:37.601 - INVOKE onPreDhcpAction()
  12-16 01:49:37.604 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 01:49:37.862 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 01:49:38.347 - INVOKE onPostDhcpAction()
  12-16 01:49:38.348 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 01:49:38.349 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 01:49:38.349 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 02:49:38.542 - INVOKE onPreDhcpAction()
  12-16 02:49:38.544 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 02:49:39.020 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 02:49:39.086 - INVOKE onPostDhcpAction()
  12-16 02:49:39.086 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 02:49:39.088 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 02:49:39.089 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 03:49:39.398 - INVOKE onPreDhcpAction()
  12-16 03:49:39.400 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 03:49:39.814 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 03:49:39.871 - INVOKE onPostDhcpAction()
  12-16 03:49:39.872 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 03:49:39.873 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 03:49:39.874 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 04:49:41.323 - INVOKE onPreDhcpAction()
  12-16 04:49:41.325 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 04:49:41.744 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 04:49:41.813 - INVOKE onPostDhcpAction()
  12-16 04:49:41.814 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 04:49:41.814 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 04:49:41.815 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 05:49:41.464 - INVOKE onPreDhcpAction()
  12-16 05:49:41.466 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 05:49:41.837 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 05:49:41.903 - INVOKE onPostDhcpAction()
  12-16 05:49:41.903 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 05:49:41.904 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 05:49:41.905 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 06:49:42.521 - INVOKE onPreDhcpAction()
  12-16 06:49:42.522 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 06:49:42.916 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 06:49:42.982 - INVOKE onPostDhcpAction()
  12-16 06:49:42.983 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 06:49:42.984 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 06:49:42.984 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 07:49:42.865 - INVOKE onPreDhcpAction()
  12-16 07:49:42.868 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 07:49:43.311 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 07:49:43.382 - INVOKE onPostDhcpAction()
  12-16 07:49:43.383 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 07:49:43.383 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 07:49:43.385 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 08:49:43.270 - INVOKE onPreDhcpAction()
  12-16 08:49:43.273 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 08:49:43.688 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 08:49:43.753 - INVOKE onPostDhcpAction()
  12-16 08:49:43.754 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 08:49:43.755 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 08:49:43.755 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 09:49:41.394 - INVOKE onPreDhcpAction()
  12-16 09:49:41.396 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 09:49:41.814 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 09:49:41.857 - INVOKE onPostDhcpAction()
  12-16 09:49:41.858 - INVOKE onNewDhcpResults({IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 09:49:41.858 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.108/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 09:49:41.859 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.108/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 10:36:55.713 - CMD_STOP wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 10:36:55.940 - CMD_CLEAR_LINKADDRESS wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 10:36:55.940 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 10:36:55.941 - CMD_ON_QUIT wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 10:53:05.895 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 10:53:07.740 - OBSERVED interfaceRemoved(wlan0)
  12-16 18:39:35.578 - OBSERVED interfaceAdded(wlan0)
  12-16 18:58:47.700 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 18:58:47.701 - INVOKE setNeighborDiscoveryOffload(true)
  12-16 18:58:47.701 - CMD_UPDATE_HTTP_PROXY wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 18:58:47.702 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/24 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 18:58:47.703 - CMD_START wlan0/24 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 18:58:47.704 - INVOKE setFallbackMulticastFilter(true)
  12-16 18:58:47.731 - CMD_CONFIRM wlan0/24 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 18:58:47.732 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 18:58:47.777 - INVOKE onPreDhcpAction()
  12-16 18:58:47.780 - CMD_PRE_DHCP_ACTION wlan0/24 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 18:58:47.780 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/24 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 18:58:48.510 - INVOKE onPostDhcpAction()
  12-16 18:58:48.510 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 18:58:48.511 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 18:58:48.511 - CMD_POST_DHCP_ACTION wlan0/24 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 18:58:48.533 - CMD_CONFIGURE_LINKADDRESS wlan0/24 0 0 192.168.221.110/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-16 18:58:48.535 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 19:36:09.628 - CMD_STOP wlan0/24 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:09.658 - CMD_CLEAR_LINKADDRESS wlan0/24 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 19:36:09.682 - CMD_STOP wlan0/24 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 19:36:09.748 - CMD_ON_QUIT wlan0/24 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-16 19:36:09.800 - CMD_STOP wlan0/24 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 19:36:11.400 - OBSERVED interfaceRemoved(wlan0)
  12-16 19:36:12.683 - OBSERVED interfaceAdded(wlan0)
  12-16 19:36:15.162 - CMD_STOP wlan0/24 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 19:36:15.163 - CMD_UPDATE_HTTP_PROXY wlan0/24 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 19:36:15.164 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/24 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 19:36:15.164 - INVOKE setNeighborDiscoveryOffload(true)
  12-16 19:36:15.165 - CMD_START wlan0/26 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-16 19:36:15.171 - INVOKE setFallbackMulticastFilter(true)
  12-16 19:36:15.184 - CMD_CONFIRM wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:15.185 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 19:36:15.263 - INVOKE onPreDhcpAction()
  12-16 19:36:15.264 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:15.266 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:15.359 - INVOKE onPostDhcpAction()
  12-16 19:36:15.361 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 19:36:15.362 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 19:36:15.362 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:15.383 - CMD_CONFIGURE_LINKADDRESS wlan0/26 0 0 192.168.221.110/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-16 19:36:15.386 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 20:36:15.475 - INVOKE onPreDhcpAction()
  12-16 20:36:15.478 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 20:36:15.697 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 20:36:15.923 - INVOKE onPostDhcpAction()
  12-16 20:36:15.924 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 20:36:15.926 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 20:36:15.927 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 21:36:14.723 - INVOKE onPreDhcpAction()
  12-16 21:36:14.724 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 21:36:14.860 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 21:36:14.953 - INVOKE onPostDhcpAction()
  12-16 21:36:14.955 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 21:36:14.956 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 21:36:14.956 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 22:36:15.948 - INVOKE onPreDhcpAction()
  12-16 22:36:15.951 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 22:36:16.340 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 22:36:16.779 - INVOKE onPostDhcpAction()
  12-16 22:36:16.780 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 22:36:16.781 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 22:36:16.781 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-16 23:36:15.322 - INVOKE onPreDhcpAction()
  12-16 23:36:15.325 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 23:36:15.575 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-16 23:36:15.711 - INVOKE onPostDhcpAction()
  12-16 23:36:15.712 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-16 23:36:15.713 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-16 23:36:15.714 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 00:36:13.781 - INVOKE onPreDhcpAction()
  12-17 00:36:13.783 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 00:36:14.174 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 00:36:14.234 - INVOKE onPostDhcpAction()
  12-17 00:36:14.235 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 00:36:14.235 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 00:36:14.236 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 01:36:17.219 - INVOKE onPreDhcpAction()
  12-17 01:36:17.221 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 01:36:17.618 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 01:36:17.692 - INVOKE onPostDhcpAction()
  12-17 01:36:17.693 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 01:36:17.693 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 01:36:17.694 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 02:36:17.538 - INVOKE onPreDhcpAction()
  12-17 02:36:17.540 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 02:36:17.867 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 02:36:17.925 - INVOKE onPostDhcpAction()
  12-17 02:36:17.926 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 02:36:17.927 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 02:36:17.928 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 03:36:16.741 - INVOKE onPreDhcpAction()
  12-17 03:36:16.743 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 03:36:16.938 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 03:36:17.024 - INVOKE onPostDhcpAction()
  12-17 03:36:17.025 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 03:36:17.026 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 03:36:17.027 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 04:36:16.542 - INVOKE onPreDhcpAction()
  12-17 04:36:16.544 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 04:36:16.934 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 04:36:17.006 - INVOKE onPostDhcpAction()
  12-17 04:36:17.007 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 04:36:17.007 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 04:36:17.008 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 05:36:17.348 - INVOKE onPreDhcpAction()
  12-17 05:36:17.350 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 05:36:17.772 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 05:36:17.841 - INVOKE onPostDhcpAction()
  12-17 05:36:17.842 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 05:36:17.842 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 05:36:17.843 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 06:36:15.538 - INVOKE onPreDhcpAction()
  12-17 06:36:15.541 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 06:36:15.718 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 06:36:15.803 - INVOKE onPostDhcpAction()
  12-17 06:36:15.804 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 06:36:15.806 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 06:36:15.807 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 07:36:18.095 - INVOKE onPreDhcpAction()
  12-17 07:36:18.097 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 07:36:18.495 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 07:36:18.549 - INVOKE onPostDhcpAction()
  12-17 07:36:18.550 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 07:36:18.550 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 07:36:18.551 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 08:36:17.630 - INVOKE onPreDhcpAction()
  12-17 08:36:17.632 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 08:36:17.840 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 08:36:17.915 - INVOKE onPostDhcpAction()
  12-17 08:36:17.916 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 08:36:17.917 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 08:36:17.919 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 09:36:16.894 - INVOKE onPreDhcpAction()
  12-17 09:36:16.897 - CMD_PRE_DHCP_ACTION wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 09:36:17.293 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 09:36:17.489 - INVOKE onPostDhcpAction()
  12-17 09:36:17.490 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 09:36:17.491 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 09:36:17.492 - CMD_POST_DHCP_ACTION wlan0/26 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 09:36:40.044 - CMD_STOP wlan0/26 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 09:37:07.870 - CMD_STOP wlan0/26 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 09:38:18.529 - CMD_CLEAR_LINKADDRESS wlan0/26 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 09:38:18.529 - CMD_ON_QUIT wlan0/26 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 09:38:38.292 - CMD_STOP wlan0/26 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 09:38:39.571 - OBSERVED interfaceRemoved(wlan0)
  12-17 11:17:58.414 - OBSERVED interfaceAdded(wlan0)
  12-17 11:18:01.796 - CMD_STOP wlan0/26 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 11:18:01.798 - INVOKE setNeighborDiscoveryOffload(true)
  12-17 11:18:01.798 - CMD_UPDATE_HTTP_PROXY wlan0/26 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 11:18:01.798 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/30 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 11:18:01.799 - CMD_START wlan0/30 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 11:18:01.801 - INVOKE setFallbackMulticastFilter(true)
  12-17 11:18:01.812 - CMD_CONFIRM wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 11:18:01.813 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 11:18:01.872 - INVOKE onPreDhcpAction()
  12-17 11:18:01.873 - CMD_PRE_DHCP_ACTION wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 11:18:01.875 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 11:18:01.964 - INVOKE onPostDhcpAction()
  12-17 11:18:01.964 - INVOKE onNewDhcpResults({IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds})
  12-17 11:18:01.965 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 11:18:01.965 - CMD_POST_DHCP_ACTION wlan0/30 1 0 IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 11:18:01.988 - CMD_CONFIGURE_LINKADDRESS wlan0/30 0 0 192.168.31.239/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-17 11:18:01.989 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.31.239/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [192.168.31.1,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 12:02:38.304 - CMD_STOP wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 12:03:37.035 - CMD_CLEAR_LINKADDRESS wlan0/30 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 12:03:37.036 - CMD_ON_QUIT wlan0/30 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 12:03:37.170 - CMD_STOP wlan0/30 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 12:09:05.451 - CMD_STOP wlan0/30 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 12:09:07.069 - OBSERVED interfaceRemoved(wlan0)
  12-17 15:37:01.109 - OBSERVED interfaceAdded(wlan0)
  12-17 15:37:04.437 - CMD_STOP wlan0/30 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 15:37:04.438 - CMD_UPDATE_HTTP_PROXY wlan0/30 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 15:37:04.439 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/30 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 15:37:04.444 - INVOKE setNeighborDiscoveryOffload(true)
  12-17 15:37:04.446 - CMD_START wlan0/34 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 15:37:04.447 - INVOKE setFallbackMulticastFilter(true)
  12-17 15:37:04.463 - CMD_CONFIRM wlan0/34 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 15:37:04.467 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: []  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 15:37:04.468 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 15:37:04.522 - INVOKE onPreDhcpAction()
  12-17 15:37:04.523 - CMD_PRE_DHCP_ACTION wlan0/34 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 15:37:04.526 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/34 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 15:37:04.637 - INVOKE onPostDhcpAction()
  12-17 15:37:04.638 - INVOKE onNewDhcpResults({IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds})
  12-17 15:37:04.638 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 15:37:04.639 - CMD_POST_DHCP_ACTION wlan0/34 1 0 IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 15:37:04.660 - CMD_CONFIGURE_LINKADDRESS wlan0/34 0 0 192.168.31.239/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-17 15:37:04.662 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.31.239/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [192.168.31.1,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})

IpManager.wlan0 connectivity packet log:
  12-17 18:06:30.064 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:06:30.082 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:06:54.694 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:06:54.712 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:07:42.106 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:07:42.123 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:07:50.228 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:07:50.229 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:08:06.263 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:08:06.278 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:08:33.603 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:08:33.604 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:09:02.644 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:09:02.662 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:09:17.072 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:09:17.074 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:09:42.905 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:09:42.920 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:09:51.070 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:09:51.072 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:10:06.552 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:10:06.567 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:10:17.489 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:10:17.491 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:10:39.303 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:10:39.320 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:10:47.493 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:10:47.495 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:11:14.674 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:11:14.675 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:11:40.022 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:11:40.024 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:11:48.104 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:11:48.122 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:12:06.136 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:12:06.137 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:12:12.266 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp who-has 192.168.31.1
  [F57A00000000AAAA03000000080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:12:12.280 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:12:36.243 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:12:36.245 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:13:00.613 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:13:00.615 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:13:08.173 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:13:08.187 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:13:27.444 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:13:27.447 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:13:48.842 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:13:48.857 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:14:13.115 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:14:13.117 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:14:53.284 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp who-has 192.168.31.1
  [F57A00000000AAAA03000000080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:14:53.299 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:15:01.444 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:15:01.445 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:16:18.184 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:16:18.202 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:16:25.515 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:16:25.516 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:17:06.544 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:17:06.562 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:17:14.773 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:17:14.776 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:17:30.722 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:17:30.737 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:17:54.883 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:17:54.897 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:18:03.005 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:18:03.007 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:18:51.336 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:18:51.338 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:18:59.313 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:18:59.330 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:19:15.487 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:19:15.489 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:19:23.504 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:19:23.519 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:19:41.719 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:19:41.721 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:19:49.684 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:19:49.702 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:20:05.885 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:20:05.887 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:20:13.864 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:20:13.872 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:21:04.404 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:21:04.422 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:22:49.164 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:22:49.182 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:23:29.464 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:23:29.478 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:23:53.644 - TX 2c:57:31:c2:1d:2a > 28:6c:07:5a:f5:7a arp who-has 192.168.31.1
  [286C075AF57A2C5731C21D2A080600010800060400012C5731C21D2AC0A81FEF000000000000C0A81F01]
  12-17 18:23:53.663 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp reply 192.168.31.1 28:6c:07:5a:f5:7a
  [2C5731C21D2A286C075AF57A08060001080006040002286C075AF57AC0A81F012C5731C21D2AC0A81FEF]
  12-17 18:24:50.151 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:24:50.153 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:25:22.887 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:25:22.889 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:25:56.606 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:25:56.606 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-17 18:26:30.915 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-17 18:26:30.917 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
Dump of WifiConnectivityManager
WifiConnectivityManager - Log Begin ----
WifiConnectivityManager - Number of connectivity attempts rate limited: 0
12-17 09:38:18.501 - startConnectivityScan: screenOn=false wifiState=2 scanImmediately=true wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 09:38:18.501 - PNO Scan stop at screen off
12-17 09:38:25.095 - handleScreenStateChanged: screenOn=true
12-17 09:38:25.095 - startConnectivityScan: screenOn=true wifiState=2 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 09:38:25.113 - SingleScanListener onSuccess
12-17 09:38:26.492 - AllSingleScanListener onResults: start QNS
12-17 09:38:38.170 - handleConnectionStateChanged: state=3
12-17 09:38:38.170 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 09:39:04.177 - handleScreenStateChanged: screenOn=false
12-17 09:39:04.177 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 09:41:23.407 - handleScreenStateChanged: screenOn=true
12-17 09:41:23.407 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 09:58:21.047 - watchdogHandler
12-17 10:00:18.750 - handleScreenStateChanged: screenOn=false
12-17 10:00:18.750 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:06:25.173 - handleScreenStateChanged: screenOn=true
12-17 10:06:25.174 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:07:18.409 - handleScreenStateChanged: screenOn=false
12-17 10:07:18.409 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:07:36.510 - handleScreenStateChanged: screenOn=true
12-17 10:07:36.511 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:08:18.685 - handleScreenStateChanged: screenOn=false
12-17 10:08:18.686 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:10:54.515 - handleScreenStateChanged: screenOn=true
12-17 10:10:54.516 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:11:38.441 - handleScreenStateChanged: screenOn=false
12-17 10:11:38.441 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:11:39.336 - handleScreenStateChanged: screenOn=true
12-17 10:11:39.336 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:11:42.996 - handleScreenStateChanged: screenOn=false
12-17 10:11:42.996 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:20:14.907 - handleScreenStateChanged: screenOn=true
12-17 10:20:14.907 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:21:19.452 - handleScreenStateChanged: screenOn=false
12-17 10:21:19.452 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:21:51.057 - handleScreenStateChanged: screenOn=true
12-17 10:21:51.058 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:21:53.564 - handleScreenStateChanged: screenOn=false
12-17 10:21:53.565 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:24:56.817 - handleScreenStateChanged: screenOn=true
12-17 10:24:56.817 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:25:09.367 - handleScreenStateChanged: screenOn=false
12-17 10:25:09.368 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:34:08.686 - handleScreenStateChanged: screenOn=true
12-17 10:34:08.686 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:35:01.704 - handleScreenStateChanged: screenOn=false
12-17 10:35:01.704 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:36:33.228 - handleScreenStateChanged: screenOn=true
12-17 10:36:33.228 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:37:03.598 - handleScreenStateChanged: screenOn=false
12-17 10:37:03.599 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:43:06.366 - handleScreenStateChanged: screenOn=true
12-17 10:43:06.366 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:43:52.512 - handleScreenStateChanged: screenOn=false
12-17 10:43:52.512 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:45:44.854 - handleScreenStateChanged: screenOn=true
12-17 10:45:44.854 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:46:33.525 - handleScreenStateChanged: screenOn=false
12-17 10:46:33.525 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:49:18.828 - handleScreenStateChanged: screenOn=true
12-17 10:49:18.829 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:50:04.745 - handleScreenStateChanged: screenOn=false
12-17 10:50:04.745 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:50:15.225 - handleScreenStateChanged: screenOn=true
12-17 10:50:15.225 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:51:07.653 - handleScreenStateChanged: screenOn=false
12-17 10:51:07.654 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:51:27.671 - handleScreenStateChanged: screenOn=true
12-17 10:51:27.672 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:57:10.539 - handleScreenStateChanged: screenOn=false
12-17 10:57:10.540 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 10:58:38.317 - handleScreenStateChanged: screenOn=true
12-17 10:58:38.317 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:00:16.756 - handleScreenStateChanged: screenOn=false
12-17 11:00:16.756 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:02:05.656 - handleScreenStateChanged: screenOn=true
12-17 11:02:05.657 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:03:15.306 - handleScreenStateChanged: screenOn=false
12-17 11:03:15.307 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:03:49.893 - handleScreenStateChanged: screenOn=true
12-17 11:03:49.893 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:05:23.188 - handleScreenStateChanged: screenOn=false
12-17 11:05:23.188 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:06:05.825 - handleScreenStateChanged: screenOn=true
12-17 11:06:05.826 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:06:53.926 - handleScreenStateChanged: screenOn=false
12-17 11:06:53.926 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:13:57.971 - handleScreenStateChanged: screenOn=true
12-17 11:13:57.972 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:14:34.499 - handleScreenStateChanged: screenOn=false
12-17 11:14:34.500 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:16:35.692 - handleScreenStateChanged: screenOn=true
12-17 11:16:35.693 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:17:59.608 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=true wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:17:59.789 - handleScreenStateChanged: screenOn=true
12-17 11:17:59.790 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 11:17:59.793 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=true wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 11:17:59.793 - handleConnectionStateChanged: state=2
12-17 11:17:59.798 - startConnectivityScan: screenOn=true wifiState=2 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 11:17:59.809 - SingleScanListener onSuccess
12-17 11:18:00.919 - AllSingleScanListener onResults: start QNS
12-17 11:18:01.021 - AllSingleScanListener: QNS candidate-"3CC_5G"
12-17 11:18:01.022 - connectToNetwork: Reconnect from Disconnected to "3CC_5G" : 28:6c:07:5a:f5:7c
12-17 11:18:01.780 - handleConnectionStateChanged: state=3
12-17 11:18:01.781 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 11:18:02.048 - handleConnectionStateChanged: state=1
12-17 11:18:02.048 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 11:21:11.253 - handleScreenStateChanged: screenOn=false
12-17 11:21:11.254 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 11:22:02.921 - handleScreenStateChanged: screenOn=true
12-17 11:22:02.921 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 11:23:12.355 - handleScreenStateChanged: screenOn=false
12-17 11:23:12.356 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 11:34:30.256 - handleScreenStateChanged: screenOn=true
12-17 11:34:30.257 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 11:36:39.104 - handleScreenStateChanged: screenOn=false
12-17 11:36:39.105 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 11:42:06.842 - watchdogHandler
12-17 11:45:04.612 - handleScreenStateChanged: screenOn=true
12-17 11:45:04.612 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 12:00:59.887 - handleScreenStateChanged: screenOn=false
12-17 12:00:59.888 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 12:02:38.322 - handleConnectionStateChanged: state=3
12-17 12:02:38.324 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 12:02:38.327 - handleConnectionStateChanged: state=2
12-17 12:02:38.341 - startConnectivityScan: screenOn=false wifiState=2 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 12:02:38.370 - startConnectivityScan: screenOn=false wifiState=2 scanImmediately=true wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 12:02:38.371 - PNO Scan stop at screen off
12-17 12:07:16.268 - handleScreenStateChanged: screenOn=true
12-17 12:07:16.268 - startConnectivityScan: screenOn=true wifiState=2 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 12:07:16.340 - SingleScanListener onSuccess
12-17 12:07:17.565 - AllSingleScanListener onResults: start QNS
12-17 12:07:36.278 - periodicScanTimerHandler
12-17 12:07:36.331 - SingleScanListener onSuccess
12-17 12:07:37.493 - AllSingleScanListener onResults: start QNS
12-17 12:08:16.279 - periodicScanTimerHandler
12-17 12:08:16.281 - SingleScanListener onSuccess
12-17 12:08:17.485 - AllSingleScanListener onResults: start QNS
12-17 12:09:05.370 - handleConnectionStateChanged: state=3
12-17 12:09:05.370 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 12:13:19.660 - handleScreenStateChanged: screenOn=false
12-17 12:13:19.660 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:13:21.125 - handleScreenStateChanged: screenOn=true
12-17 12:13:21.126 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:16:08.068 - handleScreenStateChanged: screenOn=false
12-17 12:16:08.068 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:23:13.976 - watchdogHandler
12-17 12:28:50.398 - handleScreenStateChanged: screenOn=true
12-17 12:28:50.399 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:29:01.257 - handleScreenStateChanged: screenOn=false
12-17 12:29:01.257 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:53:57.717 - handleScreenStateChanged: screenOn=true
12-17 12:53:57.718 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 12:54:13.770 - handleScreenStateChanged: screenOn=false
12-17 12:54:13.770 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:11:40.851 - handleScreenStateChanged: screenOn=true
12-17 13:11:40.852 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:11:47.795 - handleScreenStateChanged: screenOn=false
12-17 13:11:47.795 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:11:48.228 - handleScreenStateChanged: screenOn=true
12-17 13:11:48.228 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:12:24.061 - handleScreenStateChanged: screenOn=false
12-17 13:12:24.061 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:12:52.815 - handleScreenStateChanged: screenOn=true
12-17 13:12:52.815 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:13:05.624 - handleScreenStateChanged: screenOn=false
12-17 13:13:05.625 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:13:49.680 - handleScreenStateChanged: screenOn=true
12-17 13:13:49.681 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:14:13.544 - handleScreenStateChanged: screenOn=false
12-17 13:14:13.545 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:15:18.772 - handleScreenStateChanged: screenOn=true
12-17 13:15:18.772 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:25:18.606 - handleScreenStateChanged: screenOn=false
12-17 13:25:18.606 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:32:30.064 - handleScreenStateChanged: screenOn=true
12-17 13:32:30.064 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:32:39.929 - handleScreenStateChanged: screenOn=false
12-17 13:32:39.930 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:39:38.132 - handleScreenStateChanged: screenOn=true
12-17 13:39:38.133 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:43:20.575 - handleScreenStateChanged: screenOn=false
12-17 13:43:20.576 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:43:22.780 - handleScreenStateChanged: screenOn=true
12-17 13:43:22.781 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 13:44:00.864 - handleScreenStateChanged: screenOn=false
12-17 13:44:00.864 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:02:39.813 - handleScreenStateChanged: screenOn=true
12-17 14:02:39.813 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:07:55.848 - handleScreenStateChanged: screenOn=false
12-17 14:07:55.849 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:16:40.827 - handleScreenStateChanged: screenOn=true
12-17 14:16:40.827 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:24:55.403 - handleScreenStateChanged: screenOn=false
12-17 14:24:55.404 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:29:43.965 - handleScreenStateChanged: screenOn=true
12-17 14:29:43.965 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:32:00.822 - handleScreenStateChanged: screenOn=false
12-17 14:32:00.823 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:32:01.972 - handleScreenStateChanged: screenOn=true
12-17 14:32:01.973 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 14:32:12.403 - handleScreenStateChanged: screenOn=false
12-17 14:32:12.403 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:32:52.168 - handleScreenStateChanged: screenOn=true
12-17 15:32:52.169 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:33:38.459 - handleScreenStateChanged: screenOn=false
12-17 15:33:38.460 - startConnectivityScan: screenOn=false wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:36:51.449 - handleScreenStateChanged: screenOn=true
12-17 15:36:51.450 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:37:02.215 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=true wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:37:02.626 - handleScreenStateChanged: screenOn=true
12-17 15:37:02.627 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=false wifiConnectivityManagerEnabled=true
12-17 15:37:02.633 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=true wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 15:37:02.633 - handleConnectionStateChanged: state=2
12-17 15:37:02.653 - startConnectivityScan: screenOn=true wifiState=2 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 15:37:02.658 - SingleScanListener onSuccess
12-17 15:37:03.742 - AllSingleScanListener onResults: start QNS
12-17 15:37:03.835 - AllSingleScanListener: QNS candidate-"3CC_5G"
12-17 15:37:03.835 - connectToNetwork: Reconnect from Disconnected to "3CC_5G" : 28:6c:07:5a:f5:7c
12-17 15:37:04.419 - handleConnectionStateChanged: state=3
12-17 15:37:04.419 - startConnectivityScan: screenOn=true wifiState=3 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 15:37:05.723 - handleConnectionStateChanged: state=1
12-17 15:37:05.724 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=true
12-17 15:55:44.390 - handleScreenStateChanged: screenOn=false
12-17 15:55:44.390 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:00:53.130 - watchdogHandler
12-17 16:29:36.929 - handleScreenStateChanged: screenOn=true
12-17 16:29:36.929 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:29:38.659 - handleScreenStateChanged: screenOn=false
12-17 16:29:38.660 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:33:39.625 - handleScreenStateChanged: screenOn=true
12-17 16:33:39.626 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:39:37.571 - handleScreenStateChanged: screenOn=false
12-17 16:39:37.571 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:46:59.531 - handleScreenStateChanged: screenOn=true
12-17 16:46:59.532 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 16:52:28.239 - handleScreenStateChanged: screenOn=false
12-17 16:52:28.239 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 17:11:29.555 - handleScreenStateChanged: screenOn=true
12-17 17:11:29.556 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 17:27:40.489 - handleScreenStateChanged: screenOn=false
12-17 17:27:40.489 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 17:38:09.503 - handleScreenStateChanged: screenOn=true
12-17 17:38:09.505 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 17:44:14.455 - handleScreenStateChanged: screenOn=false
12-17 17:44:14.455 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 17:57:19.265 - handleScreenStateChanged: screenOn=true
12-17 17:57:19.265 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 18:02:45.169 - handleScreenStateChanged: screenOn=false
12-17 18:02:45.170 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 18:07:50.073 - handleScreenStateChanged: screenOn=true
12-17 18:07:50.074 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 18:20:26.187 - handleScreenStateChanged: screenOn=false
12-17 18:20:26.187 - startConnectivityScan: screenOn=false wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
12-17 18:24:13.053 - handleScreenStateChanged: screenOn=true
12-17 18:24:13.053 - startConnectivityScan: screenOn=true wifiState=1 scanImmediately=false wifiEnabled=true wifiConnectivityManagerEnabled=false
WifiConnectivityManager - Log End ----



```

## adb shell dumpsys meminfo 全字符
```
Applications Memory Usage (in Kilobytes):
Uptime: 53073926 Realtime: 161057637

Total PSS by process:
    275,591K: com.ss.android.article.news (pid 10805 / activities)
    259,893K: system (pid 1738)
    226,882K: com.tencent.mm (pid 16233 / activities)
    224,511K: com.android.systemui (pid 2053)
    125,781K: com.tencent.mobileqq (pid 5885)
     88,139K: com.meizu.filemanager (pid 2923 / activities)
     84,121K: surfaceflinger (pid 585)
     64,469K: com.baidu.input (pid 2838)
     54,568K: com.meizu.flyme.launcher (pid 3137 / activities)
     48,857K: com.meizu.safe (pid 6564)
     48,684K: com.meizu.alphame (pid 3038)
     45,183K: com.android.phone (pid 2332)
     40,433K: com.android.systemui:recents (pid 2217)
     39,267K: mm-qcamera-daemon (pid 782)
     36,972K: com.meizu.flyme.update (pid 30941)
     35,215K: com.sina.weibo (pid 6982)
     32,974K: com.tencent.mobileqq:MSF (pid 5861)
     31,768K: remosaic_daemon (pid 2738)
     31,140K: com.ss.android.article.news:miniapp0 (pid 6336)
     28,151K: com.tencent.mm:push (pid 16174)
     27,955K: com.meizu.safe:SafeSubProcess (pid 7465)
     26,778K: com.flyme.systemuitools (pid 2708)
     24,655K: com.android.calendar:remote (pid 7257)
     22,919K: com.android.incallui (pid 3009 / activities)
     22,546K: com.meizu.dataservice (pid 24067)
     22,148K: com.sina.weibo:remote (pid 6965)
     21,852K: com.ss.android.article.news:push (pid 2265)
     21,742K: com.meizu.netcontactservice (pid 7323)
     19,111K: com.tencent.mm:exdevice (pid 16300)
     17,855K: com.meizu.location (pid 3651)
     14,677K: com.meizu.pps (pid 3459)
     14,412K: com.meizu.battery (pid 6751)
     13,342K: com.meizu.mzsyncservice (pid 7399)
     12,674K: com.meizu.account (pid 7329)
     11,859K: media.codec (pid 770)
     11,717K: com.flyme.telecom.usagedata.service (pid 2348)
     10,498K: com.android.providers.downloads (pid 6133)
     10,254K: com.meizu.flyme.service.find (pid 6485)
      9,006K: com.android.bluetooth (pid 30279)
      8,717K: com.meizu.share (pid 30354)
      8,477K: .dataservices (pid 2316)
      8,290K: audioserver (pid 753)
      8,134K: com.meizu.cloud (pid 7104)
      7,751K: netmgrd (pid 911)
      7,675K: rild (pid 775)
      7,569K: android.ext.services (pid 2723)
      7,064K: com.meizu.cloud:mzservice_v1 (pid 6912)
      6,981K: com.iflytek.speechsuite (pid 7149)
      6,702K: zygote (pid 752)
      6,286K: com.meizu.net.pedometer (pid 6879)
      6,135K: zygote64 (pid 751)
      6,019K: rild (pid 901)
      5,484K: cameraserver (pid 756)
      5,442K: media.extractor (pid 772)
      5,113K: ims_rtp_daemon (pid 916)
      4,911K: cnd (pid 737)
      4,718K: com.qualcomm.qcrilmsgtunnel (pid 2520)
      4,627K: mediaserver (pid 773)
      4,596K: mm-pp-dpps (pid 785)
      4,532K: imscmservice (pid 918)
      4,396K: com.qualcomm.qti.appnetaccess (pid 6538)
      4,292K: sdcard (pid 2101)
      4,130K: com.meizu.experiencedatasync (pid 3067)
      4,077K: com.qualcomm.telephony (pid 7231)
      4,076K: qseeproxydaemon (pid 781)
      3,880K: xtwifi-client (pid 1542)
      3,777K: com.oma.drm.server (pid 3051)
      3,385K: com.qualcomm.qti.loadcarrier (pid 3023)
      3,266K: com.meizu.facerecognition (pid 2048)
      2,991K: drmserver (pid 757)
      2,948K: netd (pid 774)
      2,086K: audiod (pid 784)
      2,066K: wpa_supplicant (pid 32222)
      1,948K: thermal-engine (pid 742)
      1,885K: slim_daemon (pid 1543)
      1,814K: imsdatadaemon (pid 815)
      1,740K: /init (pid 1)
      1,713K: dpmd (pid 830)
      1,611K: lowi-server (pid 1540)
      1,592K: mediadrmserver (pid 771)
      1,541K: vold (pid 464)
      1,321K: logd (pid 452)
      1,300K: installd (pid 758)
      1,272K: dumpsys (pid 7500)
      1,222K: loc_launcher (pid 778)
      1,200K: mdtpd (pid 786)
      1,199K: goodixfingerprintd (pid 733)
      1,133K: keystore (pid 768)
      1,095K: perfd (pid 476)
      1,047K: ipacm (pid 851)
      1,015K: time_daemon (pid 741)
      1,011K: cnss-daemon (pid 777)
        975K: adsprpcd (pid 747)
        959K: fingerprintd (pid 734)
        958K: sensors.qcom (pid 624)
        952K: qcom-system-daemon (pid 735)
        947K: qti (pid 907)
        904K: imsqmidaemon (pid 746)
        897K: pm-service (pid 582)
        879K: wcnss_service (pid 743)
        877K: ueventd (pid 425)
        862K: lmkd (pid 583)
        840K: qseecomd (pid 629)
        839K: adbd (pid 31728)
        781K: xtwifi-inet-agent (pid 1541)
        780K: gatekeeperd (pid 790)
        719K: pm-proxy (pid 601)
        712K: facerecod (pid 788)
        679K: esepmdaemon (pid 579)
        676K: servicemanager (pid 584)
        640K: ifaad (pid 787)
        605K: rmt_storage (pid 574)
        595K: tftp_server (pid 575)
        548K: msm_irqbalance (pid 825)
        543K: dpmd (pid 776)
        541K: qseecomd (pid 581)
        498K: ipacm-diag (pid 827)
        482K: hvdcp_opti (pid 748)
        476K: healthd (pid 571)
        466K: edatad (pid 750)
        307K: debuggerd64 (pid 463)
        306K: debuggerd64:signaller (pid 466)
        242K: debuggerd (pid 23709)
        242K: debuggerd:signaller (pid 23710)

Total PSS by OOM adjustment:
    315,017K: Native
         84,121K: surfaceflinger (pid 585)
         39,267K: mm-qcamera-daemon (pid 782)
         31,768K: remosaic_daemon (pid 2738)
         11,859K: media.codec (pid 770)
          8,290K: audioserver (pid 753)
          7,751K: netmgrd (pid 911)
          7,675K: rild (pid 775)
          6,702K: zygote (pid 752)
          6,135K: zygote64 (pid 751)
          6,019K: rild (pid 901)
          5,484K: cameraserver (pid 756)
          5,442K: media.extractor (pid 772)
          5,113K: ims_rtp_daemon (pid 916)
          4,911K: cnd (pid 737)
          4,627K: mediaserver (pid 773)
          4,596K: mm-pp-dpps (pid 785)
          4,532K: imscmservice (pid 918)
          4,292K: sdcard (pid 2101)
          4,076K: qseeproxydaemon (pid 781)
          3,880K: xtwifi-client (pid 1542)
          2,991K: drmserver (pid 757)
          2,948K: netd (pid 774)
          2,086K: audiod (pid 784)
          2,066K: wpa_supplicant (pid 32222)
          1,948K: thermal-engine (pid 742)
          1,885K: slim_daemon (pid 1543)
          1,814K: imsdatadaemon (pid 815)
          1,740K: /init (pid 1)
          1,713K: dpmd (pid 830)
          1,611K: lowi-server (pid 1540)
          1,592K: mediadrmserver (pid 771)
          1,541K: vold (pid 464)
          1,321K: logd (pid 452)
          1,300K: installd (pid 758)
          1,272K: dumpsys (pid 7500)
          1,222K: loc_launcher (pid 778)
          1,200K: mdtpd (pid 786)
          1,199K: goodixfingerprintd (pid 733)
          1,133K: keystore (pid 768)
          1,095K: perfd (pid 476)
          1,047K: ipacm (pid 851)
          1,015K: time_daemon (pid 741)
          1,011K: cnss-daemon (pid 777)
            975K: adsprpcd (pid 747)
            959K: fingerprintd (pid 734)
            958K: sensors.qcom (pid 624)
            952K: qcom-system-daemon (pid 735)
            947K: qti (pid 907)
            904K: imsqmidaemon (pid 746)
            897K: pm-service (pid 582)
            879K: wcnss_service (pid 743)
            877K: ueventd (pid 425)
            862K: lmkd (pid 583)
            840K: qseecomd (pid 629)
            839K: adbd (pid 31728)
            781K: xtwifi-inet-agent (pid 1541)
            780K: gatekeeperd (pid 790)
            719K: pm-proxy (pid 601)
            712K: facerecod (pid 788)
            679K: esepmdaemon (pid 579)
            676K: servicemanager (pid 584)
            640K: ifaad (pid 787)
            605K: rmt_storage (pid 574)
            595K: tftp_server (pid 575)
            548K: msm_irqbalance (pid 825)
            543K: dpmd (pid 776)
            541K: qseecomd (pid 581)
            498K: ipacm-diag (pid 827)
            482K: hvdcp_opti (pid 748)
            476K: healthd (pid 571)
            466K: edatad (pid 750)
            307K: debuggerd64 (pid 463)
            306K: debuggerd64:signaller (pid 466)
            242K: debuggerd (pid 23709)
            242K: debuggerd:signaller (pid 23710)
    259,893K: System
        259,893K: system (pid 1738)
    376,049K: Persistent
        224,511K: com.android.systemui (pid 2053)
         48,684K: com.meizu.alphame (pid 3038)
         45,183K: com.android.phone (pid 2332)
         22,919K: com.android.incallui (pid 3009 / activities)
         11,717K: com.flyme.telecom.usagedata.service (pid 2348)
          8,477K: .dataservices (pid 2316)
          4,130K: com.meizu.experiencedatasync (pid 3067)
          3,777K: com.oma.drm.server (pid 3051)
          3,385K: com.qualcomm.qti.loadcarrier (pid 3023)
          3,266K: com.meizu.facerecognition (pid 2048)
      9,006K: Persistent Service
          9,006K: com.android.bluetooth (pid 30279)
     98,637K: Foreground
         88,139K: com.meizu.filemanager (pid 2923 / activities)
         10,498K: com.android.providers.downloads (pid 6133)
    138,112K: Visible
         40,433K: com.android.systemui:recents (pid 2217)
         26,778K: com.flyme.systemuitools (pid 2708)
         22,546K: com.meizu.dataservice (pid 24067)
         14,677K: com.meizu.pps (pid 3459)
         12,674K: com.meizu.account (pid 7329)
          8,717K: com.meizu.share (pid 30354)
          7,569K: android.ext.services (pid 2723)
          4,718K: com.qualcomm.qcrilmsgtunnel (pid 2520)
     82,324K: Perceptible
         64,469K: com.baidu.input (pid 2838)
         17,855K: com.meizu.location (pid 3651)
    274,144K: Backup
        226,882K: com.tencent.mm (pid 16233 / activities)
         28,151K: com.tencent.mm:push (pid 16174)
         19,111K: com.tencent.mm:exdevice (pid 16300)
    116,606K: A Services
         48,857K: com.meizu.safe (pid 6564)
         35,215K: com.sina.weibo (pid 6982)
         13,342K: com.meizu.mzsyncservice (pid 7399)
          8,134K: com.meizu.cloud (pid 7104)
          6,981K: com.iflytek.speechsuite (pid 7149)
          4,077K: com.qualcomm.telephony (pid 7231)
     54,568K: Home
         54,568K: com.meizu.flyme.launcher (pid 3137 / activities)
    275,591K: Previous
        275,591K: com.ss.android.article.news (pid 10805 / activities)
    276,307K: B Services
        125,781K: com.tencent.mobileqq (pid 5885)
         32,974K: com.tencent.mobileqq:MSF (pid 5861)
         31,140K: com.ss.android.article.news:miniapp0 (pid 6336)
         22,148K: com.sina.weibo:remote (pid 6965)
         21,852K: com.ss.android.article.news:push (pid 2265)
         14,412K: com.meizu.battery (pid 6751)
         10,254K: com.meizu.flyme.service.find (pid 6485)
          7,064K: com.meizu.cloud:mzservice_v1 (pid 6912)
          6,286K: com.meizu.net.pedometer (pid 6879)
          4,396K: com.qualcomm.qti.appnetaccess (pid 6538)
    111,324K: Cached
         36,972K: com.meizu.flyme.update (pid 30941)
         27,955K: com.meizu.safe:SafeSubProcess (pid 7465)
         24,655K: com.android.calendar:remote (pid 7257)
         21,742K: com.meizu.netcontactservice (pid 7323)

Total PSS by category:
    399,555K: Dalvik
    304,221K: Native
    156,660K: .dex mmap
    121,872K: .oat mmap
    103,560K: GL mtrack
     78,184K: Gfx dev
     76,708K: EGL mtrack
     69,610K: .art mmap
     65,984K: Dalvik Other
     52,182K: .so mmap
     46,746K: .apk mmap
     39,855K: Unknown
     19,888K: Stack
      7,424K: Other mmap
      5,987K: Ashmem
      1,061K: Other dev
        668K: .jar mmap
        216K: .ttf mmap
          0K: Cursor
          0K: Other mtrack

Total RAM: 2,917,628K (status low)
 Free RAM:   598,080K (  111,324K cached pss +   417,540K cached kernel +    69,216K free)
 Used RAM: 2,646,362K (2,276,254K used pss +   370,108K kernel)
 Lost RAM:     4,433K
     ZRAM:   310,408K physical used for   849,960K in swap (1,048,572K total swap)
   Tuning: 192 (large 512), oom   322,560K, restore limit   107,520K (high-end-gfx)


```

## adb shell dumpsys input全字符
```
INPUT MANAGER (dumpsys input)

Input Manager State:
  Interactive: false
  System UI Visibility: 0x608
  Pointer Speed: 0
  Pointer Gestures Enabled: true
  Show Touches: false

Event Hub State:
  BuiltInKeyboardId: -2
  Devices:
    -1: Virtual
      Classes: 0x40000023
      Path: <virtual>
      Descriptor: a718a782d34bc767f4689c232d64d527998ea7fd
      Location: 
      ControllerNumber: 0
      UniqueId: <virtual>
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/Generic.kl
      KeyCharacterMapFile: /system/usr/keychars/Virtual.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    1: msm8953-snd-card-mtp Button Jack
      Classes: 0x00000001
      Path: /dev/input/event7
      Descriptor: 652d81422a52c35a5a6d7e3b5c5285d3affd5b11
      Location: ALSA
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/Generic.kl
      KeyCharacterMapFile: /system/usr/keychars/Generic.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    2: msm8953-snd-card-mtp Headset Jack
      Classes: 0x00000080
      Path: /dev/input/event6
      Descriptor: b6a4175a2792b5bb0355f949aea0d2724cfe89f9
      Location: ALSA
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: 
      KeyCharacterMapFile: 
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    3: hbtp_vm
      Classes: 0x00000008
      Path: /dev/input/event3
      Descriptor: 793e303307c34fc398863d5f83ee4c6ea6a7c5c6
      Location: 
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: 
      KeyCharacterMapFile: 
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    4: gf-keys
      Classes: 0x00000001
      Path: /dev/input/event2
      Descriptor: 88e3881958b1fbc6dc63e9a05a2dd32a94295cde
      Location: 
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/gf-keys.kl
      KeyCharacterMapFile: /system/usr/keychars/Generic.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    5: qpnp_pon
      Classes: 0x00000001
      Path: /dev/input/event0
      Descriptor: fb60d4f4370f5dbe8267b63d38dea852987571ab
      Location: qpnp_pon/input0
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/Generic.kl
      KeyCharacterMapFile: /system/usr/keychars/Generic.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    6: hall_sensor
      Classes: 0x00000080
      Path: /dev/input/event4
      Descriptor: 9760af4bed025850a5dc1bfb191f031fec4f6499
      Location: /dev/input/hall_dev
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0000, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: 
      KeyCharacterMapFile: 
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    7: gpio-keys
      Classes: 0x00000001
      Path: /dev/input/event5
      Descriptor: 485d69228e24f5e46da1598745890b214130dbc4
      Location: gpio-keys/input0
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0019, vendor=0x0001, product=0x0001, version=0x0100
      KeyLayoutFile: /system/usr/keylayout/gpio-keys.kl
      KeyCharacterMapFile: /system/usr/keychars/Generic.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false
    8: main_touch
      Classes: 0x00000015
      Path: /dev/input/event1
      Descriptor: 04a0e9017778c8ea9df055a59b19550f693f3fdd
      Location: 
      ControllerNumber: 0
      UniqueId: 
      Identifier: bus=0x0018, vendor=0x0000, product=0x0000, version=0x0000
      KeyLayoutFile: /system/usr/keylayout/main_touch.kl
      KeyCharacterMapFile: /system/usr/keychars/Generic.kcm
      ConfigurationFile: 
      HaveKeyboardLayoutOverlay: false

Input Reader State:
  Device -1: Virtual
    Generation: 2
    IsExternal: false
    HasMic:     false
    Sources: 0x00000301
    KeyboardType: 2
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 2
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 0
  Device 1: msm8953-snd-card-mtp Button Jack
    Generation: 19
    IsExternal: false
    HasMic:     false
    Sources: 0x00000101
    KeyboardType: 1
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 1
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 0
  Device 2: msm8953-snd-card-mtp Headset Jack
    Generation: 17
    IsExternal: false
    HasMic:     false
    Sources: 0x80000000
    KeyboardType: 0
    Switch Input Mapper:
      SwitchValues: 0
  Device 3: hbtp_vm
    Generation: 53
    IsExternal: false
    HasMic:     false
    Sources: 0x00002002
    KeyboardType: 0
    Motion Ranges:
      X: source=0x00002002, min=0.000, max=1079.000, flat=0.000, fuzz=0.000, resolution=0.000
      Y: source=0x00002002, min=0.000, max=1919.000, flat=0.000, fuzz=0.000, resolution=0.000
      PRESSURE: source=0x00002002, min=0.000, max=1.000, flat=0.000, fuzz=0.000, resolution=0.000
    Cursor Input Mapper:
      Parameters:
        HasAssociatedDisplay: true
        Mode: pointer
        OrientationAware: false
      XScale: 1.000
      YScale: 1.000
      XPrecision: 1.000
      YPrecision: 1.000
      HaveVWheel: false
      HaveHWheel: false
      VWheelScale: 1.000
      HWheelScale: 1.000
      Orientation: 0
      ButtonState: 0x00000000
      Down: false
      DownTime: 0
  Device 4: gf-keys
    Generation: 12
    IsExternal: false
    HasMic:     false
    Sources: 0x00000101
    KeyboardType: 1
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 1
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 54041632021000
  Device 5: qpnp_pon
    Generation: 10
    IsExternal: false
    HasMic:     false
    Sources: 0x00000101
    KeyboardType: 1
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 1
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 53946615635000
  Device 6: hall_sensor
    Generation: 8
    IsExternal: false
    HasMic:     false
    Sources: 0x80000000
    KeyboardType: 0
    Switch Input Mapper:
      SwitchValues: 0
  Device 7: gpio-keys
    Generation: 6
    IsExternal: false
    HasMic:     false
    Sources: 0x00000101
    KeyboardType: 1
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 1
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 54040570069000
  Device 8: main_touch
    Generation: 54
    IsExternal: false
    HasMic:     false
    Sources: 0x00001103
    KeyboardType: 1
    Motion Ranges:
      X: source=0x00001002, min=0.000, max=1079.000, flat=0.000, fuzz=0.000, resolution=0.000
      Y: source=0x00001002, min=0.000, max=1919.000, flat=0.000, fuzz=0.000, resolution=0.000
      PRESSURE: source=0x00001002, min=0.000, max=1.000, flat=0.000, fuzz=0.000, resolution=0.000
      SIZE: source=0x00001002, min=0.000, max=1.000, flat=0.000, fuzz=0.000, resolution=0.000
      TOUCH_MAJOR: source=0x00001002, min=0.000, max=2202.907, flat=0.000, fuzz=0.000, resolution=0.000
      TOUCH_MINOR: source=0x00001002, min=0.000, max=2202.907, flat=0.000, fuzz=0.000, resolution=0.000
      TOOL_MAJOR: source=0x00001002, min=0.000, max=2202.907, flat=0.000, fuzz=0.000, resolution=0.000
      TOOL_MINOR: source=0x00001002, min=0.000, max=2202.907, flat=0.000, fuzz=0.000, resolution=0.000
    Keyboard Input Mapper:
      Parameters:
        HasAssociatedDisplay: false
        OrientationAware: false
        HandlesKeyRepeat: false
      KeyboardType: 1
      Orientation: 0
      KeyDowns: 0 keys currently down
      MetaState: 0x0
      DownTime: 0
    Touch Input Mapper:
      Parameters:
        GestureMode: multi-touch
        DeviceType: touchScreen
        AssociatedDisplay: hasAssociatedDisplay=true, isExternal=false
        OrientationAware: true
      Raw Touch Axes:
        X: min=0, max=1080, flat=0, fuzz=0, resolution=0
        Y: min=0, max=1920, flat=0, fuzz=0, resolution=0
        Pressure: min=0, max=255, flat=0, fuzz=0, resolution=0
        TouchMajor: min=0, max=255, flat=0, fuzz=0, resolution=0
        TouchMinor: unknown range
        ToolMajor: unknown range
        ToolMinor: unknown range
        Orientation: unknown range
        Distance: unknown range
        TiltX: unknown range
        TiltY: unknown range
        TrackingId: min=0, max=15, flat=0, fuzz=0, resolution=0
        Slot: unknown range
      Calibration:
        touch.size.calibration: geometric
        touch.pressure.calibration: physical
        touch.orientation.calibration: none
        touch.distance.calibration: none
        touch.coverage.calibration: none
      Affine Transformation:
        X scale: 1.000
        X ymix: 0.000
        X offset: 0.000
        Y xmix: 0.000
        Y scale: 1.000
        Y offset: 0.000
      Viewport: displayId=0, orientation=0, logicalFrame=[0, 0, 1080, 1920], physicalFrame=[0, 0, 1080, 1920], deviceSize=[1080, 1920]
      SurfaceWidth: 1080px
      SurfaceHeight: 1920px
      SurfaceLeft: 0
      SurfaceTop: 0
      SurfaceOrientation: 0
      Translation and Scaling Factors:
        XTranslate: 0.000
        YTranslate: 0.000
        XScale: 0.999
        YScale: 0.999
        XPrecision: 1.001
        YPrecision: 1.001
        GeometricScale: 0.999
        PressureScale: 0.004
        SizeScale: 0.004
        OrientationScale: 0.000
        DistanceScale: 0.000
        HaveTilt: false
        TiltXCenter: 0.000
        TiltXScale: 0.000
        TiltYCenter: 0.000
        TiltYScale: 0.000
      Last Raw Button State: 0x00000000
      Last Raw Touch: pointerCount=0
      Last Cooked Button State: 0x00000000
      Last Cooked Touch: pointerCount=0
      Stylus Fusion:
        ExternalStylusConnected: false
        External Stylus ID: -1
        External Stylus Data Timeout: 9223372036854775807
      External Stylus State:
        When: 9223372036854775807
        Pressure: 0.000000
        Button State: 0x00000000
        Tool Type: 0
  Configuration:
    ExcludedDeviceNames: []
    VirtualKeyQuietTime: 0.0ms
    PointerVelocityControlParameters: scale=1.000, lowThreshold=500.000, highThreshold=3000.000, acceleration=3.000
    WheelVelocityControlParameters: scale=1.000, lowThreshold=15.000, highThreshold=50.000, acceleration=4.000
    PointerGesture:
      Enabled: true
      QuietInterval: 100.0ms
      DragMinSwitchSpeed: 50.0px/s
      TapInterval: 150.0ms
      TapDragInterval: 300.0ms
      TapSlop: 20.0px
      MultitouchSettleInterval: 100.0ms
      MultitouchMinDistance: 15.0px
      SwipeTransitionAngleCosine: 0.3
      SwipeMaxWidthRatio: 0.2
      MovementSpeedRatio: 0.8
      ZoomSpeedRatio: 0.3

Input Dispatcher State:
  DispatchEnabled: 1
  DispatchFrozen: 0
  FocusedApplication: name='AppWindowToken{353c5b7 token=Token{d7bb451 ActivityRecord{642a4b6 u0 com.meizu.connectivitysettings/.Settings t11872}}}', dispatchingTimeout=5000.000ms
  FocusedWindow: name='Window{c2c702a u0 StatusBar}'
  TouchStates: <no displays touched>
  Windows:
    0: name='Window{4df4b8a u0 Heads Up}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x01800328, type=0x000007de, layer=171000, frame=[0,0][1080,406], scale=1.000000, touchableRegion=[0,0][1080,397], inputFeatures=0x00000000, ownerPid=2053, ownerUid=10031, dispatchingTimeout=5000.000ms
    1: name='Window{c2c702a u0 StatusBar}', displayId=0, paused=false, hasFocus=true, hasWallpaper=false, visible=true, canReceiveKeys=true, flags=0x81860040, type=0x000007d0, layer=161000, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000004, ownerPid=2053, ownerUid=10031, dispatchingTimeout=5000.000ms
    2: name='Window{c910ce0 u0 KeyguardScrim}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x01110900, type=0x000007ed, layer=141000, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=1738, ownerUid=1000, dispatchingTimeout=5000.000ms
    3: name='Window{9d60ed2 u0 Games}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x01000038, type=0x000007d8, layer=71000, frame=[0,66][1080,1920], scale=1.000000, touchableRegion=[0,66][1080,1920], inputFeatures=0x00000000, ownerPid=2708, ownerUid=10031, dispatchingTimeout=5000.000ms
    4: name='Window{742e969 u0 AssistPreviewPanel}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x01000118, type=0x000007f1, layer=41000, frame=[0,1920][1080,1920], scale=1.000000, touchableRegion=<empty>, inputFeatures=0x00000000, ownerPid=2053, ownerUid=10031, dispatchingTimeout=5000.000ms
    5: name='Window{a0f9033 u0 DockedStackDivider}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x21840028, type=0x000007f2, layer=21045, frame=[0,0][0,0], scale=1.000000, touchableRegion=[-144,0][144,144], inputFeatures=0x00000000, ownerPid=2053, ownerUid=10031, dispatchingTimeout=5000.000ms
    6: name='Window{fc0758f u0 DockedStackFocusIndicator}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x21840038, type=0x000007fa, layer=21050, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=1738, ownerUid=1000, dispatchingTimeout=5000.000ms
    7: name='Window{b25af31 u0 com.meizu.connectivitysettings/com.meizu.connectivitysettings.Settings}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x85810120, type=0x00000001, layer=21040, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=9653, ownerUid=1000, dispatchingTimeout=5000.000ms
    8: name='Window{e856365 u0 com.android.settings/com.android.settings.Settings}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x85810120, type=0x00000001, layer=21035, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=9588, ownerUid=1000, dispatchingTimeout=5000.000ms
    9: name='Window{ff534c u0 com.tencent.mm/com.tencent.mm.ui.LauncherUI}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x81810120, type=0x00000001, layer=21030, frame=[151,134][1231,2054], scale=1.388889, touchableRegion=[151,134][928,1516], inputFeatures=0x00000000, ownerPid=16233, ownerUid=10108, dispatchingTimeout=5000.000ms
    10: name='Window{54721f9 u0 PopupWindow:e87939d}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x41820008, type=0x000003e8, layer=21025, frame=[0,66][0,1920], scale=1.000000, touchableRegion=<empty>, inputFeatures=0x00000000, ownerPid=10805, ownerUid=10103, dispatchingTimeout=5000.000ms
    11: name='Window{d4f5243 u0 PopupWindow:8441a47}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x41820008, type=0x000003e8, layer=21020, frame=[0,66][0,1920], scale=1.000000, touchableRegion=<empty>, inputFeatures=0x00000000, ownerPid=10805, ownerUid=10103, dispatchingTimeout=5000.000ms
    12: name='Window{3d0a880 u0 com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x85810120, type=0x00000001, layer=21015, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=10805, ownerUid=10103, dispatchingTimeout=5000.000ms
    13: name='Window{e1069ba u0 com.android.incallui/com.android.incallui.InCallActivity}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x85298120, type=0x00000001, layer=21010, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=3009, ownerUid=10003, dispatchingTimeout=5000.000ms
    14: name='Window{7a92b67 u0 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x81910120, type=0x00000001, layer=21005, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=3137, ownerUid=10011, dispatchingTimeout=5000.000ms
    15: name='Window{c01cd53 u0 com.android.systemui.ImageWallpaper}', displayId=0, paused=false, hasFocus=false, hasWallpaper=false, visible=false, canReceiveKeys=false, flags=0x00010318, type=0x000007dd, layer=21000, frame=[0,0][1080,1920], scale=1.000000, touchableRegion=[0,0][1080,1920], inputFeatures=0x00000000, ownerPid=2053, ownerUid=10031, dispatchingTimeout=5000.000ms
  MonitoringChannels:
    0: 'WindowManager (server)'
  RecentQueue: length=10
    MotionEvent(deviceId=8, source=0x00001002, action=2, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (167.8, 827.6)]), policyFlags=0x62000000, age=137511.3ms
    MotionEvent(deviceId=8, source=0x00001002, action=2, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (175.8, 878.5)]), policyFlags=0x62000000, age=137501.6ms
    MotionEvent(deviceId=8, source=0x00001002, action=2, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (183.8, 936.5)]), policyFlags=0x62000000, age=137492.2ms
    MotionEvent(deviceId=8, source=0x00001002, action=1, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (183.8, 936.5)]), policyFlags=0x62000000, age=137482.8ms
    MotionEvent(deviceId=8, source=0x00001002, action=0, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (93.9, 704.6)]), policyFlags=0x62000000, age=136374.9ms
    MotionEvent(deviceId=8, source=0x00001002, action=2, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (93.9, 704.6)]), policyFlags=0x62000000, age=136365.3ms
    MotionEvent(deviceId=8, source=0x00001002, action=2, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (93.9, 704.6)]), policyFlags=0x62000000, age=136355.6ms
    MotionEvent(deviceId=8, source=0x00001002, action=1, actionButton=0x00000000, flags=0x00000000, metaState=0x00000000, buttonState=0x00000000, edgeFlags=0x00000000, xPrecision=1.0, yPrecision=1.0, displayId=0, pointers=[0: (93.9, 704.6)]), policyFlags=0x62000000, age=136346.5ms
    KeyEvent(deviceId=-1, source=0x00000101, action=0, flags=0x00000000, keyCode=26, scanCode=0, metaState=0x00000000, repeatCount=0), policyFlags=0x2b000000, age=88317.0ms
    KeyEvent(deviceId=-1, source=0x00000101, action=1, flags=0x00000000, keyCode=26, scanCode=0, metaState=0x00000000, repeatCount=0), policyFlags=0x2b000000, age=88317.0ms
  PendingEvent: <none>
  InboundQueue: <empty>
  ReplacedKeys: <empty>
  Connections:
    0: channelName='WindowManager (server)', windowName='monitor', status=NORMAL, monitor=true, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    1: channelName='fc0758f DockedStackFocusIndicator (server)', windowName='Window{fc0758f u0 DockedStackFocusIndicator}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    2: channelName='c910ce0 KeyguardScrim (server)', windowName='Window{c910ce0 u0 KeyguardScrim}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    3: channelName='9d60ed2 Games (server)', windowName='Window{9d60ed2 u0 Games}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    4: channelName='e1069ba com.android.incallui/com.android.incallui.InCallActivity (server)', windowName='Window{e1069ba u0 com.android.incallui/com.android.incallui.InCallActivity}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    5: channelName='c01cd53 com.android.systemui.ImageWallpaper (server)', windowName='Window{c01cd53 u0 com.android.systemui.ImageWallpaper}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    6: channelName='a0f9033 DockedStackDivider (server)', windowName='Window{a0f9033 u0 DockedStackDivider}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    7: channelName='742e969 AssistPreviewPanel (server)', windowName='Window{742e969 u0 AssistPreviewPanel}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    8: channelName='d4f5243 PopupWindow:8441a47 (server)', windowName='Window{d4f5243 u0 PopupWindow:8441a47}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    9: channelName='7a92b67 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher (server)', windowName='Window{7a92b67 u0 com.meizu.flyme.launcher/com.meizu.flyme.launcher.Launcher}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    10: channelName='c2c702a StatusBar (server)', windowName='Window{c2c702a u0 StatusBar}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    11: channelName='4df4b8a Heads Up (server)', windowName='Window{4df4b8a u0 Heads Up}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    12: channelName='54721f9 PopupWindow:e87939d (server)', windowName='Window{54721f9 u0 PopupWindow:e87939d}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    13: channelName='ff534c com.tencent.mm/com.tencent.mm.ui.LauncherUI (server)', windowName='Window{ff534c u0 com.tencent.mm/com.tencent.mm.ui.LauncherUI}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    14: channelName='3d0a880 com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity (server)', windowName='Window{3d0a880 u0 com.ss.android.article.news/com.ss.android.article.news.activity.MainActivity}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    15: channelName='e856365 com.android.settings/com.android.settings.Settings (server)', windowName='Window{e856365 u0 com.android.settings/com.android.settings.Settings}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
    16: channelName='b25af31 com.meizu.connectivitysettings/com.meizu.connectivitysettings.Settings (server)', windowName='Window{b25af31 u0 com.meizu.connectivitysettings/com.meizu.connectivitysettings.Settings}', status=NORMAL, monitor=false, inputPublisherBlocked=false
      OutboundQueue: <empty>
      WaitQueue: <empty>
  AppSwitch: not pending
  Configuration:
    KeyRepeatDelay: 50.0ms
    KeyRepeatTimeout: 500.0ms


  Keyboard Layouts:
    "英语（英国） - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_uk
    "英语（美国） - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_us
    "英语（美国），国际风格 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_us_intl
    "英语（美国），Colemak 风格 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_us_colemak
    "英语（美国），Dvorak 风格 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_us_dvorak
    "英语（美国），Workman 风格 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_english_us_workman
    "德语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_german
    "法语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_french
    "法语（加拿大） - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_french_ca
    "俄语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_russian
    "俄语，Mac 风格 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_russian_mac
    "西班牙语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_spanish
    "瑞士法语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_swiss_french
    "瑞士德语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_swiss_german
    "比利时语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_belgian
    "保加利亚语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_bulgarian
    "意大利语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_italian
    "丹麦语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_danish
    "挪威语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_norwegian
    "瑞典语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_swedish
    "芬兰语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_finnish
    "克罗地亚语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_croatian
    "捷克语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_czech
    "爱沙尼亚语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_estonian
    "匈牙利语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_hungarian
    "冰岛语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_icelandic
    "巴西语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_brazilian
    "葡萄牙语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_portuguese
    "斯洛伐克语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_slovak
    "斯洛文尼亚语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_slovenian
    "土耳其语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_turkish
    "乌克兰语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_ukrainian
    "阿拉伯语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_arabic
    "希腊语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_greek
    "希伯来语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_hebrew
    "立陶宛语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_lithuanian
    "西班牙语（拉丁美洲） - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_spanish_latin
    "拉脱维亚语 - Android键盘": com.android.inputdevices/com.android.inputdevices.InputDeviceReceiver/keyboard_layout_latvian

  PersistentDataStore
    mLoaded=true
    mDirty=false
    InputDeviceStates:


```


## adb shell pm list permissions全字符
```
All Permissions:

permission:com.meizu.voiceassistant.push.permission.MESSAGE
permission:com.meizu.safe.alphame.permission.DATA
permission:android.permission.REAL_GET_TASKS
permission:com.meizu.flyme.wallet.permission.SEND_DOWNLOAD_COMPLETED_INTENTS
permission:android.permission.ACCESS_CACHE_FILESYSTEM
permission:android.permission.REMOTE_AUDIO_PLAYBACK
permission:com.meizu.flyme.input.push.permission.MESSAGE
permission:android.permission.REGISTER_WINDOW_MANAGER_LISTENERS
permission:android.permission.INTENT_FILTER_VERIFICATION_AGENT
permission:android.permission.BIND_INCALL_SERVICE
permission:com.ygkj.chelaile.standard.permission.JPUSH_MESSAGE
permission:android.permission.WRITE_SETTINGS
permission:android.permission.CONTROL_KEYGUARD
permission:com.meizu.flyme.weather.push.permission.MESSAGE
permission:com.taobao.idlefish.push.permission.MESSAGE
permission:com.taobao.taobao.permission.C2D_MESSAGE
permission:android.permission.CONFIGURE_WIFI_DISPLAY
permission:android.permission.CONFIGURE_DISPLAY_COLOR_MODE
permission:android.permission.ACCESS_WIMAX_STATE
permission:baidu.push.permission.WRITE_PUSHINFOPROVIDER.com.baidu.tieba_mini
permission:com.yixia.videoeditor.permission.C2D_MESSAGE
permission:android.permission.SET_INPUT_CALIBRATION
permission:android.permission.RECOVERY
permission:android.permission.TEMPORARY_ENABLE_ACCESSIBILITY
permission:android.permission.SET_PROCESS_LIMIT
permission:android.permission.FRAME_STATS
permission:com.meizu.safe.permission.default_protect
permission:com.UCMobile.permission.MESSAGE
permission:com.android.soundrecorder.push.permission.MESSAGE
permission:com.cubic.autohome.permission.JPUSH_MESSAGE
permission:com.android.browser.permission.PRELOAD
permission:ctrip.android.view.push.permission.MESSAGE
permission:com.meizu.net.search.permission.C2D_MESSAGE
permission:android.permission.BRICK
permission:com.qualcomm.permission.READPROC
permission:com.meizu.flyme.permission.SEND_ACTIVED_PHONE
permission:android.permission.BIND_PRINT_RECOMMENDATION_SERVICE
permission:com.liulishuo.service.scorer
permission:com.meizu.net.pedometer.push.permission.MESSAGE
permission:android.permission.RESTART_PACKAGES
permission:android.permission.STORAGE_INTERNAL
permission:android.permission.USE_CREDENTIALS
permission:android.permission.send.MEIZU_SUBSCREEN
permission:android.permission.TABLET_MODE
permission:com.codeaurora.permission.DATA_STORAGE_CLEANER
permission:android.permission.BIND_KEYGUARD_APPWIDGET
permission:com.tmall.wireless.permission.C2D_MESSAGE
permission:com.meizu.remotecooperation.push.permission.MESSAGE
permission:com.facebook.receiver.permission.ACCESS
permission:com.meizu.media.music.permission.C2D_MESSAGE
permission:android.permission.BIND_DEVICE_ADMIN
permission:android.permission.MODIFY_AUDIO_SETTINGS
permission:com.zhiliaoapp.musically.permission.MIPUSH_RECEIVE
permission:com.sina.push.sdk.broadcast.permission1004
permission:android.permission.ACCESS_CHECKIN_PROPERTIES
permission:android.permission.KILL_UID
permission:org.codeaurora.permission.POWER_ON_ALERT
permission:android.permission.SIGNAL_PERSISTENT_PROCESSES
permission:android.permission.BIND_PRINT_SERVICE
permission:android.permission.ACCESS_NOTIFICATION_POLICY
permission:com.youku.phone.push.permission.MESSAGE
permission:android.permission.BIND_RUNTIME_PERMISSION_PRESENTER_SERVICE
permission:android.permission.MODIFY_AUDIO_ROUTING
permission:android.permission.RECEIVE_BLUETOOTH_MAP
permission:android.permission.ACCESS_MOCK_LOCATION
permission:com.flyme.package_installer.restore_flyme_app
permission:android.permission.BIND_VR_LISTENER_SERVICE
permission:com.tencent.qqhead.permission.getheadresp
permission:com.meizu.flyme.gamecenter.push.permission.MESSAGE
permission:android.permission.GET_APP_OPS_STATS
permission:android.permission.RECEIVE_DATA_ACTIVITY_CHANGE
permission:com.meizu.flyme.input.permission.SEND_SETTINGS_CHANGE
permission:com.ss.android.tuchong.permission.C2D_MESSAGE
permission:ctrip.android.view.permission.third
permission:com.meizu.functionmanager
permission:com.meizu.location.push.permission.MESSAGE
permission:com.meizu.flyme.filemanager.permission.READ_REMARKPROVIDER
permission:android.permission.GET_PACKAGE_IMPORTANCE
permission:com.google.android.youtube.permission.C2D_MESSAGE
permission:com.meizu.customizecenter.push.permission.MESSAGE
permission:com.meizu.safe.push.permission.MESSAGE
permission:com.meizu.safe.permission.ANTI_VIRUS
permission:com.flyme.meizu.store.permission.C2D_MESSAGE
permission:android.permission.READ_WIFI_CREDENTIAL
permission:android.permission.BIND_DREAM_SERVICE
permission:com.meizu.battery.permission.ui
permission:com.dianping.v1.push.permission.MESSAGE
permission:android.permission.WRITE_GSERVICES
permission:com.android.printspooler.permission.ACCESS_ALL_PRINT_JOBS
permission:com.meizu.flyme.wallet.push.permission.MESSAGE
permission:android.permission.INSTALL_LOCATION_PROVIDER
permission:com.meizu.desktopbackup.permission.C2D_MESSAGE
permission:com.jingdong.pdj.permission.C2D_MESSAGE
permission:com.ct.client.permission.MIPUSH_RECEIVE
permission:com.tencent.qav.permission.broadcast
permission:com.meizu.flyme.permission.UPDATE
permission:com.meizu.customizecenter.permission.C2D_MESSAGE
permission:android.permission.MANAGE_ACCOUNTS
permission:android.permission.CONTROL_WIFI_DISPLAY
permission:com.sankuai.meituan.takeoutnew.permission.C2D_MESSAGE
permission:com.zhiliaoapp.musically.permission.WRITE_ACCOUNT
permission:com.meizu.flyme.appcenter.permission.EXTERNAL_INSTALL
permission:android.permission.SYSTEM_ALERT_WINDOW
permission:android.permission.XINHUAANALYTICS
permission:com.yixia.videoeditor.push.permission.MESSAGE
permission:android.permission.BIND_CARRIER_SERVICES
permission:com.sina.weibo.weibodetailprovider.READ
permission:android.permission.BROADCAST_PHONE_ACCOUNT_REGISTRATION
permission:android.permission.START_TASKS_FROM_RECENTS
permission:com.meizu.flyme.wallet.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED
permission:com.tencent.androidqqmail.permission.MIPUSH_RECEIVE
permission:android.permission.CONTROL_LOCATION_UPDATES
permission:com.UCMobile.permission.MIPUSH_RECEIVE
permission:com.alensw.PicFolder.permission.C2D_MESSAGE
permission:com.tencent.mm.matrix.strategynotify
permission:android.permission.GET_TOP_ACTIVITY_INFO
permission:com.meizu.notepaper.ACCESS_NOTES
permission:com.meizu.assistant.permission.UPDATE_CARD
permission:flyme.permission.APPCLONE
permission:com.meizu.cloud.push.permission.MESSAGE
permission:android.permission.CLEAR_APP_USER_DATA
permission:com.meizu.pps.permission.READ_SETTINGS
permission:baidu.push.permission.WRITE_PUSHINFOPROVIDER.com.baidu.netdisk
permission:android.permission.PROVIDE_TRUST_AGENT
permission:com.qihoo.antivirus.update.permission.block_sdk_104415
permission:android.permission.BROADCAST_CALLLOG_INFO
permission:android.permission.INSTALL_PACKAGES
permission:android.permission.MANAGE_SOUND_TRIGGER
permission:android.permission.SEND_RESPOND_VIA_MESSAGE
permission:com.android.permission.WHITELIST_BLUETOOTH_DEVICE
permission:android.permission.ACCESS_FM_RADIO
permission:com.jingdong.app.mall.permission.MIPUSH_RECEIVE
permission:com.yixia.videoeditor.permission.MIPUSH_RECEIVE
permission:com.qti.permission.AUDIO
permission:android.permission.MANAGE_VOICE_KEYPHRASES
permission:android.permission.SHUTDOWN
permission:android.permission.CHANGE_COMPONENT_ENABLED_STATE
permission:android.permission.BIND_QUICK_SETTINGS_TILE
permission:com.meizu.account.push.permission.MESSAGE
permission:android.permission.MMS_SEND_OUTBOX_MSG
permission:android.permission.BIND_TV_INPUT
permission:com.autohome.plugin.littlevideoshot.EDIT_RESULT
permission:android.permission.NFC
permission:android.permission.MODIFY_PARENTAL_CONTROLS
permission:android.permission.INTERNAL_SYSTEM_WINDOW
permission:baidu.push.permission.WRITE_PUSHINFOPROVIDER.com.baidu.BaiduMap
permission:com.youku.phone.permission.C2D_MESSAGE
permission:android.permission.PROCESS_PHONE_ACCOUNT_REGISTRATION
permission:android.permission.RETRIEVE_WINDOW_TOKEN
permission:com.meizu.flymelab.features.READ
permission:com.meizu.flyme.launcher.permission.RECEIVE_LAUNCH_BROADCASTS
permission:com.balance6game.housingfund.permission.C2D_MESSAGE
permission:com.ss.android.tuchong.push.permission.MESSAGE
permission:android.permission.BIND_WALLPAPER
permission:com.tmall.wireless.push.permission.MESSAGE
permission:android.permission.START_ANY_ACTIVITY
permission:android.permission.CAMERA_SEND_SYSTEM_EVENTS
permission:com.sankuai.meituan.takeoutnew.push.permission.MESSAGE
permission:com.sdu.didi.psnger.permission.bh.notification
permission:com.ss.android.account.share.provider.tt.TT_READ_PROVIDER
permission:android.permission.SEND_DOWNLOAD_COMPLETED_INTENTS
permission:com.ss.android.auto.permission.C2D_MESSAGE
permission:com.tencent.music.data.permission2
permission:android.permission.GET_PROCESS_STATE_AND_OOM_SCORE
permission:com.meizu.netcontactservice.push.permission.MESSAGE
permission:android.permission.BROADCAST_SMS
permission:android.permission.CALL_PRIVILEGED
permission:android.permission.BIND_INTENT_FILTER_VERIFIER
permission:com.youku.phone.permission.MIPUSH_RECEIVE
permission:android.permission.MANAGE_APP_TOKENS
permission:android.permission.CHANGE_NETWORK_STATE
permission:android.permission.CAPTURE_TV_INPUT
permission:android.permission.MASTER_CLEAR
permission:android.permission.PERSISTENT_ACTIVITY
permission:android.permission.BIND_TELECOM_CONNECTION_SERVICE
permission:com.baidu.input.permission.PLUGININTERFACESERVICE
permission:android.permission.WRITE_SYNC_SETTINGS
permission:com.baidu.netdisk.permission.BROADCAST
permission:com.ss.android.article.news.push.permission.MESSAGE
permission:android.permission.SET_DEBUG_APP
permission:com.sina.weibo.permission.C2D_MESSAGE
permission:com.zhiliaoapp.musically.permission.READ_ACCOUNT
permission:com.meizu.media.ebook.permission.C2D_MESSAGE
permission:android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY
permission:android.permission.RESET_SHORTCUT_MANAGER_THROTTLING
permission:android.permission.RESET_FINGERPRINT_LOCKOUT
permission:com.meizu.compaign.permission.C2D_MESSAGE
permission:com.meizu.picker.ocr.permission.RECOGNIZE
permission:android.permission.RECEIVE_BOOT_COMPLETED
permission:com.flyme.virtual.permission.USE_SOFTSIM
permission:ctrip.android.view.permission.C2D_MESSAGE
permission:com.meizu.flyme.wallet.permission.ACCESS_DOWNLOAD_MANAGER
permission:getui.permission.GetuiService.com.yixia.videoeditor
permission:flyme.permission.MEIZU_CONTROL_PACKAGE_PERMISSIONS
permission:com.facebook.katana.permission.CROSS_PROCESS_BROADCAST_MANAGER
permission:getui.permission.GetuiService.com.ccdi.news
permission:com.meizu.flyme.appcenter.license.WRITE
permission:android.permission.LOOP_RADIO
permission:android.permission.GLOBAL_SEARCH_CONTROL
permission:android.permission.SUBSCRIBED_FEEDS_READ
permission:flyme.permission.ROOT_RECOVERY_STATE
permission:com.ss.android.tuchong.permission.MIPUSH_RECEIVE
permission:com.android.launcher3.permission.RECEIVE_LAUNCH_BROADCASTS
permission:com.meizu.sceneinfo.push.permission.MESSAGE
permission:com.meizu.datamigration.push.permission.MESSAGE
permission:android.permission.PEERS_MAC_ADDRESS
permission:com.qualcomm.permission.USE_PHONE_SERVICE
permission:android.permission.DEVICE_POWER
permission:com.tencent.mm.permission.MM_MESSAGE
permission:com.meizu.flyme.service.find.permisson.PhoneLocationService
permission:com.meizu.breakingscam.permission.SEND_SENSITIVE_SMS
permission:com.meizu.flyme.service.find.push.permission.MESSAGE
permission:com.flyme.telecom.usagedata.service.push.permission.MESSAGE
permission:com.qualcomm.qti.permission.USE_QTI_TELEPHONY_SERVICE
permission:android.permission.SET_TIME_ZONE
permission:com.flyme.netadmin.push.permission.MESSAGE
permission:android.permission.REMOVE_TASKS
permission:com.wudaokou.hippo.permission.MIPUSH_RECEIVE
permission:android.permission.EXPAND_STATUS_BAR
permission:com.meizu.permission.WRITE_SCENE_INFO
permission:cmb.pb.permission.C2D_MESSAGE
permission:com.android.launcher.permission.UNINSTALL_SHORTCUT
permission:android.permission.MANAGE_PROFILE_AND_DEVICE_OWNERS
permission:com.meizu.safe.alphame.provider.permission
permission:com.meizu.breakingscam.permission.REMOTE_SUPPORT
permission:android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
permission:android.permission.READ_PROFILE
permission:android.permission.BIND_VOICE_INTERACTION
permission:com.ss.android.ugc.aweme.permission.WRITE_ACCOUNT
permission:com.meizu.account.permission.ROOT_PERMISSION
permission:ctrip.android.view.permission.MIPUSH_RECEIVE
permission:android.permission.BLUETOOTH
permission:android.permission.BROADCAST_WAP_PUSH
permission:android.permission.CHANGE_WIFI_MULTICAST_STATE
permission:android.permission.DVB_DEVICE
permission:com.flyme.permission.SEND_END_WELCOMEPAGE
permission:com.baidu.BaiduMap.CALL_MAIN_PROCESS_RECEIVER
permission:android.permission.PERFORM_SIM_ACTIVATION
permission:com.android.alarm.permission.SET_ALARM
permission:com.meizu.share.push.permission.MESSAGE
permission:android.permission.CAPTURE_AUDIO_HOTWORD
permission:com.facebook.katana.permission.RECEIVE_ADM_MESSAGE
permission:android.permission.WRITE_MEDIA_STORAGE
permission:com.android.voicemail.permission.READ_VOICEMAIL
permission:com.meizu.flyme.push.permission.SEND
permission:android.permission.WRITE_BLOCKED_NUMBERS
permission:com.qualcomm.permission.USE_QCRIL_MSG_TUNNEL
permission:android.permission.SET_ANIMATION_SCALE
permission:com.qualcomm.qti.permission.ACCESS_QCOM_LOGKIT
permission:android.permission.send.MEIZU_FINDPHONE_CHANGED
permission:com.meizu.flyme.service.find.permisson.ACCESS_DATA_PROVIDER
permission:android.permission.GET_TASKS
permission:android.permission.BROADCAST_NETWORK_PRIVILEGED
permission:android.permission.ACCESS_SURFACE_FLINGER
permission:com.meizu.flyme.update.QUERY_UPDATE
permission:android.permission.SUBSCRIBED_FEEDS_WRITE
permission:com.baidu.BaiduMap.meizu.receiver.permission
permission:android.permission.AUTHENTICATE_ACCOUNTS
permission:android.permission.INTERNET
permission:com.meizu.safe.permission.QR_CODE_LOCAL_DB
permission:com.flyme.permission.SEND_MARK_LOCAL_NOTIFICATION
permission:com.sina.weibo.weibodetailprovider.WRITE
permission:android.permission.TV_INPUT_HARDWARE
permission:android.permission.TRANSMIT_IR
permission:android.permission.RETRIEVE_WINDOW_CONTENT
permission:com.meizu.flyme.toolbox.push.permission.MESSAGE
permission:com.meizu.wifiadmin.permission.WIFI_CLICKED
permission:android.permission.REORDER_TASKS
permission:com.android.browser.permission.READ_HISTORY_BOOKMARKS
permission:com.facebook.katana.permission.C2D_MESSAGE
permission:com.meizu.pps.push.permission.MESSAGE
permission:android.permission.GET_INTENT_SENDER_INTENT
permission:com.meizu.media.video.push.permission.MESSAGE
permission:com.meizu.safe.blockService.SDKBlockResultProvider.QUERY_BLOCK_RESULT
permission:android.permission.BLUETOOTH_ADMIN
permission:android.permission.CONTROL_VPN
permission:android.permission.BAIDU_LOCATION_SERVICE.autohome.baidulocation
permission:android.permission.UPDATE_DEVICE_STATS
permission:android.permission.BLUETOOTH_STACK
permission:android.permission.BLUETOOTH_MAP
permission:android.permission.READ_PRECISE_PHONE_STATE
permission:android.permission.MANAGE_FINGERPRINT
permission:com.qualcomm.permission.IZAT
permission:com.meizu.flyme.gamecenter.permission.C2D_MESSAGE
permission:com.meizu.stats.permission.WRITE_USAGESTATS
permission:android.permission.NET_ADMIN
permission:com.liulishuo.engzo.permission.MIPUSH_RECEIVE
permission:android.permission.CARRIER_FILTER_SMS
permission:com.ss.android.ugc.aweme.permission.C2D_MESSAGE
permission:com.UCMobile.permission.C2D_MESSAGE
permission:android.permission.PERFORM_CDMA_PROVISIONING
permission:android.permission.BIND_CONNECTION_SERVICE
permission:com.meizu.media.music.push.permission.MESSAGE
permission:com.baidu.tieba_mini.permission.MIPUSH_RECEIVE
permission:com.meizu.flyme.directservice.push.permission.MESSAGE
permission:com.meizu.flyme.launcher.push.permission.MESSAGE
permission:android.permission.INSTALL_GRANT_RUNTIME_PERMISSIONS
permission:android.permission.WRITE_SOCIAL_STREAM
permission:com.taobao.idlefish.permission.C2D_MESSAGE
permission:android.permission.LAUNCH_TRUST_AGENT_SETTINGS
permission:org.codeaurora.omacp.permission.SHOW_CONFIGURATION_MESSAGE
permission:com.ss.android.article.news.permission.C2D_MESSAGE
permission:android.permission.REGISTER_CALL_PROVIDER
permission:com.mobike.mobikeapp.push.permission.MESSAGE
permission:com.qihoo360.mobilesafe.permission.strongbox.service.PrivacyFileService
permission:android.permission.GET_PACKAGE_SIZE
permission:com.meizu.mznfcpay.permission.PAY_CODE
permission:android.permission.CLEAR_APP_GRANTED_URI_PERMISSIONS
permission:android.permission.BIND_VPN_SERVICE
permission:android.permission.TRUST_LISTENER
permission:com.hexin.plat.android.permission.C2D_MESSAGE
permission:android.permission.ACCOUNT_MANAGER
permission:android.permission.MANAGE_USB
permission:com.qualcomm.permission.UIM_REMOTE_CLIENT
permission:com.meizu.gamecenter.service.push.permission.MESSAGE
permission:android.permission.ACCESS_ALL_DOWNLOADS
permission:android.permission.INTERACT_ACROSS_USERS_FULL
permission:com.jingdong.pdj.permission.MIPUSH_RECEIVE
permission:android.permission.MMS_SECRET_MODE_PROVIDER
permission:com.jingdong.app.mall.push.permission.MESSAGE
permission:android.permission.BIND_APPWIDGET
permission:android.permission.STOP_APP_SWITCHES
permission:android.permission.RECEIVE_MEDIA_RESOURCE_USAGE
permission:android.permission.BATTERY_STATS
permission:android.permission.PACKAGE_USAGE_STATS
permission:android.permission.WRITE_PROFILE
permission:android.permission.MOUNT_UNMOUNT_FILESYSTEMS
permission:android.permission.GET_ACCOUNTS_PRIVILEGED
permission:com.tencent.msf.service.permission
permission:com.meizu.flyme.calculator.push.permission.MESSAGE
permission:android.permission.TETHER_PRIVILEGED
permission:android.permission.WRITE_SECURE_SETTINGS
permission:android.permission.SERIAL_PORT
permission:com.zhihu.android.permission.C2D_MESSAGE
permission:android.permission.BIND_NOTIFICATION_LISTENER_SERVICE
permission:android.permission.BIND_SCREENING_SERVICE
permission:com.meizu.networkmanager.control_command
permission:com.xiaomi.router.permission.MIPUSH_RECEIVE
permission:com.eg.android.AlipayGphone.permission.C2D_MESSAGE
permission:com.tencent.mm.plugin.permission.READ
permission:android.permission.CAPTURE_AUDIO_OUTPUT
permission:android.permission.BIND_MIDI_DEVICE_SERVICE
permission:com.flyme.telecom.usagedata.service.permissions.PRIVATE_BROADCAST
permission:com.ss.android.ugc.aweme.push.permission.MESSAGE
permission:com.sina.weibo.push.permission.MESSAGE
permission:com.tencent.mm.wear.message
permission:ctrip.android.view.push
permission:android.permission.MOVE_PACKAGE
permission:com.tencent.mm.ext.permission.SPORT
permission:android.permission.SET_ACTIVITY_WATCHER
permission:com.tencent.mm.ext.permission.WRITE
permission:android.permission.READ_BLOCKED_NUMBERS
permission:com.jd.permissions.MSG_BROADCAST
permission:com.qualcomm.permission.wfd.QC_WFD
permission:com.meizu.permission.WRITE_PRIVACY_NUMBER
permission:android.permission.STATUS_BAR_SERVICE
permission:android.permission.READ_SEARCH_INDEXABLES
permission:com.meizu.media.video.permission.C2D_MESSAGE
permission:flyme.permission.READ_PRIVILEGED_CONTACTS
permission:cn.xq.shareelb.permission.MIPUSH_RECEIVE
permission:com.tencent.msg.permission.pushnotify
permission:com.sina.permission.SINA_PUSH
permission:android.permission.NFC_HANDOVER_STATUS
permission:com.meizu.permission.WRITE_BREAKING_SCAM
permission:android.permission.SCORE_NETWORKS
permission:com.meizu.account.permission.WRITE_ACCOUNT_INFO
permission:android.permission.DIAGNOSTIC
permission:android.permission.ACCESS_LOCATION_EXTRA_COMMANDS
permission:com.meizu.permission.READ_BREAKING_SCAM
permission:android.permission.RECEIVE_WIFI_CREDENTIAL_CHANGE
permission:android.permission.READ_PRIVILEGED_PHONE_STATE
permission:android.permission.UPDATE_LOCK_TASK_PACKAGES
permission:android.permission.ACCESS_DOWNLOAD_MANAGER
permission:android.permission.BROADCAST_STICKY
permission:com.ct.client.permission.JPUSH_MESSAGE
permission:android.permission.ASEC_ACCESS
permission:com.ccb.ebs.permission.ACCESS_CCBMOBILELIFE_PAYMENT
permission:com.android.mms.push.permission.MESSAGE
permission:com.zhihu.android.ReceivePlayerInfo
permission:android.permission.BIND_REMOTE_DISPLAY
permission:android.permission.BLUETOOTH_PRIVILEGED
permission:android.permission.RECEIVE_MMS_SUPER
permission:com.meizu.permission.READ_GEO
permission:com.meizu.media.ebook.push.permission.MESSAGE
permission:android.permission.SET_ALWAYS_FINISH
permission:com.sankuai.meituan.takeoutnew.permission.MIPUSH_RECEIVE
permission:android.permission.HARDWARE_TEST
permission:android.permission.BIND_NOTIFICATION_RANKER_SERVICE
permission:com.meizu.mznfcpay.push.permission.MESSAGE
permission:android.permission.ASEC_CREATE
permission:android.permission.WRITE_SMS
permission:com.didi.passenger.sdk.login.permission.broadcast.com.sdu.didi.psnger
permission:com.facebook.permission.prod.FB_APP_COMMUNICATION
permission:android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME
permission:com.qiyi.video.permission.MIPUSH_RECEIVE
permission:com.qualcomm.qti.permission.ACCESS_USER_AUTHENTICATION_APIS
permission:android.intent.category.MASTER_CLEAR.permission.C2D_MESSAGE
permission:com.ygkj.chelaile.standard.permission.MIPUSH_RECEIVE
permission:com.ss.android.article.news.permission.READ_ACCOUNT
permission:android.permission.ACCESS_KEYGUARD_SECURE_STORAGE
permission:android.permission.BIND_JOB_SERVICE
permission:android.permission.CONFIRM_FULL_BACKUP
permission:android.permission.CAPTURE_SECURE_VIDEO_OUTPUT
permission:com.meizu.dataservice.push.permission.MESSAGE
permission:android.permission.SET_TIME
permission:android.permission.WRITE_APN_SETTINGS
permission:com.meizu.networkmanager.permission.OFFER_OR_UPDATE_DATA
permission:flyme.permission.send.MEIZU_DATASAVE_MODE_CHANGED
permission:android.permission.CHANGE_WIFI_STATE
permission:com.sina.weibo.permission.PUSH_MSG_BROADCAST_PERMISSION
permission:com.hexin.plat.android.permission.READ_PASSPORT
permission:com.cubic.autohome.push.permission.MESSAGE
permission:com.meizu.backup.push.permission.MESSAGE
permission:android.permission.BIND_PACKAGE_VERIFIER
permission:android.permission.READ_INSTALL_SESSIONS
permission:com.meizu.battery.permission.service
permission:android.permission.MODIFY_CELL_BROADCASTS
permission:android.permission.MODIFY_DAY_NIGHT_MODE
permission:android.permission.BIND_ROUTE_PROVIDER
permission:android.permission.RECEIVE_STK_COMMANDS
permission:com.zhiliaoapp.musically.permission.RECEIVE_ADM_MESSAGE
permission:com.meizu.media.life.push.permission.MESSAGE
permission:android.permission.GET_PASSWORD
permission:com.sina.weibo.permission.NOUSER_BROADCAST
permission:android.permission.BIND_CARRIER_MESSAGING_SERVICE
permission:android.permission.ACCESS_BLUETOOTH_SHARE
permission:com.android.browser.push.permission.MESSAGE
permission:android.permission.PACKAGE_VERIFICATION_AGENT
permission:com.meizu.account.pay.push.permission.MESSAGE
permission:android.permission.SET_ORIENTATION
permission:android.permission.REVOKE_RUNTIME_PERMISSIONS
permission:com.UCMobile.intl.permission.C2D_MESSAGE
permission:android.permission.MANAGE_USERS
permission:com.qti.permission.DIAG
permission:android.permission.send.MEIZU_ACCELERATOR_EXECUTING
permission:android.permission.SET_PREFERRED_APPLICATIONS
permission:android.permission.BIND_TV_REMOTE_SERVICE
permission:android.permission.FLASHLIGHT
permission:android.permission.DELETE_CACHE_FILES
permission:android.permission.SET_WALLPAPER_COMPONENT
permission:android.permission.ACCESS_NETWORK_STATE
permission:com.balance6game.housingfund.push.permission.MESSAGE
permission:flyme.permission.QUICK_UNLOCK_MODE
permission:android.permission.BIND_CONDITION_PROVIDER_SERVICE
permission:com.ss.android.account.share.provider.tt.TT_WRITE_PROVIDER
permission:com.android.soundrecorder.permission.ACCESS_RECORD
permission:android.permission.ACCESS_MTP
permission:android.permission.DISABLE_KEYGUARD
permission:android.permission.BACKUP
permission:com.ximalaya.ting.android.permission.MIPUSH_RECEIVE
permission:android.permission.PROCESS_CALLLOG_INFO
permission:com.meizu.media.reader.permission.C2D_MESSAGE
permission:com.xiaomi.router.provider.READ_OFFLINE_VIDEO
permission:android.permission.CHANGE_CONFIGURATION
permission:android.permission.USER_ACTIVITY
permission:tv.danmaku.bili.permission.MIPUSH_RECEIVE
permission:android.permission.LOCAL_MAC_ADDRESS
permission:com.xiaomi.router.provider.WRITE_OFFLINE_VIDEO
permission:android.permission.READ_LOGS
permission:android.permission.COPY_PROTECTED_DATA
permission:com.baidu.input.permission.REGISTERRECEIVE
permission:com.meizu.customizecenterservice.READORWRITE_PROVIDER
permission:com.zhihu.android.push.permission.MESSAGE
permission:android.permission.ASEC_DESTROY
permission:meizu.alphame.USER_BEHAVIOR
permission:com.mobike.mobikeapp.permission.C2D_MESSAGE
permission:com.meizu.net.search.push.permission.MESSAGE
permission:android.permission.INTERACT_ACROSS_USERS
permission:com.facebook.katana.provider.ACCESS
permission:android.permission.SET_WALLPAPER
permission:android.permission.ASEC_RENAME
permission:com.meizu.safe.SDK_PROVIDER
permission:android.permission.SET_KEYBOARD_LAYOUT
permission:android.permission.UPDATE_CONFIG
permission:android.permission.ACCESS_NETWORK_CONDITIONS
permission:com.cubic.autohome.permission.MIPUSH_RECEIVE
permission:com.baidu.netdisk.permission.MIPUSH_RECEIVE
permission:com.meizu.notepaper.push.permission.MESSAGE
permission:com.taobao.taobao.permission.MIPUSH_RECEIVE
permission:com.tencent.androidqqmail.permission.INNER_IPC_BROADCAST
permission:android.permission.READ_DREAM_STATE
permission:android.permission.QUERY_DO_NOT_ASK_CREDENTIALS_ON_BOOT
permission:com.meizu.flyme.appcenter.recommend
permission:fm.xiami.main.permission.broadcast
permission:cmb.pb.permission.HCE_PUSH_MESSAGE
permission:com.flyme.telecom.usagedata.service.permission.C2D_MESSAGE
permission:android.permission.UPDATE_LOCK
permission:android.permission.READ_NETWORK_USAGE_HISTORY
permission:android.permission.ACCESS_DRM_CERTIFICATES
permission:com.sina.weibo.permission.EXTSERVICE
permission:com.tencent.mm.permission.C2D_MESSAGE
permission:com.meizu.flyme.mall.push.permission.MESSAGE
permission:com.meizu.facerecognition.permission.ACTION_FROZEN_RESET
permission:com.meizu.permission.SWITCH_MODE_CALLLOG_NOTIFY
permission:android.permission.TV_VIRTUAL_REMOTE_CONTROLLER
permission:android.permission.MANAGE_APP_OPS_RESTRICTIONS
permission:android.permission.READ_INPUT_STATE
permission:android.permission.KILL_BACKGROUND_PROCESSES
permission:android.permission.CACHE_CONTENT
permission:com.meizu.media.gallery.permission.C2D_MESSAGE
permission:android.permission.ACCESS_VOICE_INTERACTION_SERVICE
permission:android.permission.BIND_PRINT_SPOOLER_SERVICE
permission:com.android.flyme.bridge.permission.USE_SOFTSIM
permission:android.permission.BIND_DIRECTORY_SEARCH
permission:com.android.alarmclock.push.permission.MESSAGE
permission:android.permission.CONNECTIVITY_USE_RESTRICTED_NETWORKS
permission:flyme.permission.READPROC
permission:com.taobao.idlefish.permission.MIPUSH_RECEIVE
permission:com.meizu.mstore.push.permission.MESSAGE
permission:com.baidu.input.permission.SENDBROADCAST
permission:android.permission.WRITE_USER_DICTIONARY
permission:android.permission.REGISTER_SIM_SUBSCRIPTION
permission:com.eg.android.AlipayGphone.permission.MIPUSH_RECEIVE
permission:flyme.permission.DEVICE_SUB_DISPLAY
permission:com.flyme.meizu.store.push.permission.MESSAGE
permission:com.meizu.facerecognition.permission.FACE_RECOGNITION
permission:com.meizu.feebback.access.permission.FAQ_LIST
permission:android.permission.READ_SYNC_STATS
permission:android.permission.WRITE_DREAM_STATE
permission:com.sankuai.common.PERMISSION
permission:android.permission.FREEZE_SCREEN
permission:broadcast.cne.permission
permission:android.permission.DOWNLOAD_CACHE_NON_PURGEABLE
permission:android.permission.REBOOT
permission:android.permission.DISPATCH_NFC_MESSAGE
permission:flyme.permission.GET_FLYME_HIPS_SERVICE
permission:com.tencent.qqsports.permission.MIPUSH_RECEIVE
permission:com.dianping.v1.permission.C2D_MESSAGE
permission:meizu.permission.SWITCH_SYSTEM_MODES
permission:com.meizu.permission.READ_PRIVACY_NUMBER
permission:cmb.pb.push.permission.MESSAGE
permission:android.permission.MOUNT_FORMAT_FILESYSTEMS
permission:com.meizu.flyme.service.find.permisson.PLAY_ALARM_SOUND
permission:com.meizu.gamecenter.service.permission.GAMEBAR
permission:meizu.permission.RECEIVE_APPLICATION_SHADOW_ADD_OR_REMOVE
permission:android.permission.OEM_UNLOCK_STATE
permission:android.permission.REGISTER_CONNECTION_MANAGER
permission:com.dianping.v1.permission.MIPUSH_RECEIVE
permission:android.permission.MANAGE_DEVICE_ADMINS
permission:android.permission.CLEAR_APP_CACHE
permission:com.tmall.wireless.permission.MIPUSH_RECEIVE
permission:com.meizu.battery.push.permission.MESSAGE
permission:android.permission.CHANGE_APP_IDLE_STATE
permission:com.meizu.pps.permission.WRITE_SETTINGS
permission:com.meizu.mstore.permission.CHECK_UPDATE
permission:com.ss.android.auto.permission.MIPUSH_RECEIVE
permission:android.permission.BIND_TRUST_AGENT
permission:android.permission.MANAGE_DOCUMENTS
permission:android.permission.ACCESS_PDB_STATE
permission:android.permission.MANAGE_NETWORK_POLICY
permission:com.meizu.stats.permission.READ_USAGESTATS
permission:android.permission.BIND_REMOTEVIEWS
permission:com.xiaomi.router.permission.INTERNAL_COMMON
permission:android.permission.MEIZU_SMART_LOCK
permission:android.permission.ASEC_MOUNT_UNMOUNT
permission:android.permission.GRANT_RUNTIME_PERMISSIONS
permission:android.permission.SET_POINTER_SPEED
permission:android.permission.CHANGE_BACKGROUND_DATA_SETTING
permission:android.permission.MANAGE_NOTIFICATIONS
permission:com.meizu.netcontactservice.intercept.read
permission:com.android.systemui.permission.SELF
permission:android.permission.GLOBAL_SEARCH
permission:com.meizu.permission.BLUETOOTH_OPP_PROVIDER
permission:com.ss.android.article.news.permission.WRITE_ACCOUNT
permission:android.permission.NET_TUNNELING
permission:com.android.email.push.permission.MESSAGE
permission:com.hexin.plat.android.push.permission.MESSAGE
permission:android.permission.CONNECTIVITY_INTERNAL
permission:com.meizu.flyme.wallet.permission.C2D_MESSAGE
permission:android.permission.FACTORY_TEST
permission:fm.xiami.main.permission.MIPUSH_RECEIVE
permission:android.permission.SET_WALLPAPER_HINTS
permission:getui.permission.GetuiService.com.sdu.didi.psnger
permission:android.permission.MANAGE_MEDIA_PROJECTION
permission:com.baidu.BaiduMap.permission.SANDBOX_PERMISSION
permission:android.permission.READ_SYNC_SETTINGS
permission:cm.aptoide.pt.permission.BILLING
permission:com.android.calendar.permission.CUSTOM_EVENT
permission:com.jingdong.app.mall.permission.C2D_MESSAGE
permission:com.meizu.mzsyncservice.push.permission.MESSAGE
permission:com.baidu.BaiduMap.permission.MIPUSH_RECEIVE
permission:com.android.browser.permission.C2D_MESSAGE
permission:com.sdu.didi.psnger.permission.MIPUSH_RECEIVE
permission:com.android.browser.permission.WRITE_HISTORY_BOOKMARKS
permission:com.ss.android.ugc.aweme.permission.READ_ACCOUNT
permission:android.permission.BROADCAST_PACKAGE_REMOVED
permission:com.meizu.cloud.appcommon.write.settings
permission:android.permission.OVERRIDE_WIFI_CONFIG
permission:com.meizu.ftp.permission
permission:flyme.permission.MOCK_CALL
permission:android.permission.FORCE_STOP_PACKAGES
permission:com.meizu.permission.READ_SCENE_INFO
permission:meizu.permission.BIND_PRIVACY_CONTROLER
permission:android.permission.MANAGE_CA_CERTIFICATES
permission:android.permission.HIDE_NON_SYSTEM_OVERLAY_WINDOWS
permission:android.permission.CAPTURE_VIDEO_OUTPUT
permission:android.permission.ACCESS_NOTIFICATIONS
permission:com.tencent.mm.plugin.permission.WRITE
permission:getui.permission.GetuiService.com.jingdong.app.mall
permission:tv.danmaku.bili.permission.JPUSH_MESSAGE
permission:com.meizu.remotecooperation.access.permission.JUMP
permission:android.permission.VIBRATE
permission:com.android.certinstaller.INSTALL_AS_USER
permission:android.permission.BIND_ACCESSIBILITY_SERVICE
permission:com.meizu.battery.provider.permission
permission:com.meizu.feedback.submit.permission.QUESTION
permission:android.permission.MANAGE_ACTIVITY_STACKS
permission:com.meizu.mstore.permission.appdownload
permission:android.permission.SEND_CALL_LOG_CHANGE
permission:com.meizu.safe.alphame.permission.DATA_CHANGE
permission:android.permission.HDMI_CEC
permission:android.permission.BIND_INPUT_METHOD
permission:android.permission.INVOKE_CARRIER_SETUP
permission:com.meizu.compaign.push.permission.MESSAGE
permission:android.permission.CHANGE_DEVICE_IDLE_TEMP_WHITELIST
permission:android.permission.REMOVE_DRM_CERTIFICATES
permission:android.permission.READ_USER_DICTIONARY
permission:android.permission.FILTER_EVENTS
permission:com.meizu.media.reader.push.permission.MESSAGE
permission:com.meizu.permission.CONTROL_VPN
permission:com.baidu.BaiduMap.meizu.push.permission.MESSAGE
permission:com.meizu.filemanager.push.permission.MESSAGE
permission:com.android.dialer.push.permission.MESSAGE
permission:android.permission.RECEIVE_EMERGENCY_BROADCAST
permission:com.ss.android.article.news.permission.MIPUSH_RECEIVE
permission:com.meizu.account.permission.READ_ACCOUNT_INFO
permission:tv.danmaku.bili.permission.C2D_MESSAGE
permission:meizu.alphame.dump
permission:com.flyme.roamingpay.push.permission.MESSAGE
permission:com.meizu.flyme.gamecenter.permission.action.perform
permission:android.permission.CREATE_USERS
permission:com.ss.android.article.news.permission.JPUSH_MESSAGE
permission:com.meizu.battery.permission.C2D_MESSAGE
permission:android.permission.MODIFY_NETWORK_ACCOUNTING
permission:com.eg.android.AlipayGphone.push.permission.MESSAGE
permission:com.sina.weibo.permission.WEIBO_SDK_PERMISSION
permission:com.flyme.meizu.store.permission.MIPUSH_RECEIVE
permission:com.tencent.wifisdk.permission.disconnect
permission:android.permission.CRYPT_KEEPER
permission:android.permission.BIND_NFC_SERVICE
permission:com.android.launcher3.permission.RECEIVE_FIRST_LOAD_BROADCAST
permission:com.balance6game.housingfund.permission.MIPUSH_RECEIVE
permission:android.permission.START_PRINT_SERVICE_CONFIG_ACTIVITY
permission:com.android.voicemail.permission.WRITE_VOICEMAIL
permission:com.android.mms.permission.MAPS_RECEIVE
permission:android.permission.GET_DETAILED_TASKS
permission:com.meizu.safe.permission.QR_CODE_SCAN_SERVICE
permission:android.permission.NOTIFY_PENDING_SYSTEM_UPDATE
permission:meizu.permission.READ_PRIVACY
permission:android.permission.DISPATCH_PROVISIONING_MESSAGE
permission:android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK
permission:android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS
permission:android.permission.ACCESS_WIFI_STATE
permission:com.juphoon.rcs.notify.permission
permission:cmb.pb.permission.MIPUSH_RECEIVE
permission:android.permission.FORCE_BACK
permission:getui.permission.GetuiService.com.jingdong.pdj
permission:android.permission.CHANGE_WIMAX_STATE
permission:com.meizu.flyme.update.push.permission.MESSAGE
permission:android.permission.CAMERA_DISABLE_TRANSMIT_LED
permission:android.permission.REQUEST_INSTALL_PACKAGES
permission:android.permission.CONTROL_INCALL_EXPERIENCE
permission:android.permission.MODIFY_PHONE_STATE
permission:android.permission.ACCESS_VR_MANAGER
permission:com.cubic.autohome.permission.C2D_MESSAGE
permission:android.permission.PACKET_KEEPALIVE_OFFLOAD
permission:com.android.launcher.permission.INSTALL_SHORTCUT
permission:android.permission.BIND_TEXT_SERVICE
permission:android.permission.STATUS_BAR
permission:android.permission.READ_FRAME_BUFFER
permission:com.tencent.smartdevice.permission.broadcast
permission:com.zhiliaoapp.musically.push.permission.MESSAGE
permission:com.jingdong.app.mall.permission.self_broadcast
permission:com.baidu.BaiduMap.meizu.permission.C2D_MESSAGE
permission:android.permission.SEND_SMS_NO_CONFIRMATION
permission:com.meizu.flymelab.push.permission.MESSAGE
permission:com.sina.weibo.permission.PUSH_RECEIVER
permission:com.meizu.permission.SEARCH_SETTINGS
permission:android.permission.DUMP
permission:com.meizu.desktopbackup.push.permission.MESSAGE
permission:com.android.browser.permission.CHILD_SERVICE
permission:android.permission.GET_APP_GRANTED_URI_PERMISSIONS
permission:com.meizu.flyme.appcenter.license.READ
permission:com.ss.android.ugc.aweme.permission.MIPUSH_RECEIVE
permission:android.permission.LOCATION_HARDWARE
permission:com.tencent.photos.permission.DATA
permission:com.sina.weibo.data.sp.dbsharedpreference
permission:com.sina.weibo.permission.MIPUSH_RECEIVE
permission:getui.permission.GetuiService.com.zhihu.android
permission:android.permission.READ_SOCIAL_STREAM
permission:com.meizu.mzsyncservice.permission.C2D_MESSAGE
permission:com.mobike.mobikeapp.permission.MIPUSH_RECEIVE
permission:android.permission.WAKE_LOCK
permission:android.permission.INJECT_EVENTS
permission:com.taobao.taobao.push.permission.MESSAGE
permission:android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED
permission:com.hexin.plat.android.permission.MIPUSH_RECEIVE
permission:android.permission.BIND_CHOOSER_TARGET_SERVICE
permission:com.qihoo.antivirus.update.permission.clear_sdk_meizu
permission:com.android.email.permission.ACCESS_PROVIDER
permission:com.ss.android.auto.push.permission.MESSAGE
permission:com.wandoujia.phoenix2.permission.MIPUSH_RECEIVE
permission:com.jingdong.pdj.push.permission.MESSAGE
permission:android.permission.UPDATE_APP_OPS_STATS
permission:android.permission.ACCESS_EPHEMERAL_APPS
permission:android.permission.ACCESS_INPUT_FLINGER
permission:android.permission.OBSERVE_GRANT_REVOKE_PERMISSIONS
permission:android.permission.READ_OEM_UNLOCK_STATE
permission:com.tencent.mm.ext.permission.READ
permission:com.meizu.media.camera.push.permission.MESSAGE
permission:android.permission.SET_SCREEN_COMPATIBILITY
permission:reportfinishbroadcastreceiver.permission
permission:android.permission.MEDIA_CONTENT_CONTROL
permission:android.permission.DELETE_PACKAGES
permission:com.meizu.media.gallery.push.permission.MESSAGE
permission:com.android.calendar.push.permission.MESSAGE
permission:getui.permission.GetuiService.com.mobike.mobikeapp
permission:com.meizu.flyme.appcenter.permission.action.perform
permission:com.meizu.customizecenter.Permission.RW_PROVIDER


```

##  db shell content query --uri 全字符
```
Row: 0 _id=1, _data=/system, _size=0, format=12289, parent=0, date_added=172827, date_modified=0, mime_type=NULL, title=system, description=NULL, _display_name=system, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=47, bucket_display_name=, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=NULL, width=NULL, height=NULL, camera_refocus=0
Row: 1 _id=2, _data=/system/media, _size=0, format=12289, parent=1, date_added=172827, date_modified=1230739200, mime_type=NULL, title=media, description=NULL, _display_name=media, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=-2124328162, bucket_display_name=system, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=NULL, width=NULL, height=NULL, camera_refocus=0
Row: 2 _id=3, _data=/system/media/audio, _size=0, format=12289, parent=2, date_added=172827, date_modified=1230739200, mime_type=NULL, title=audio, description=NULL, _display_name=audio, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=1664432563, bucket_display_name=media, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=0, width=NULL, height=NULL, camera_refocus=0
Row: 3 _id=4, _data=/system/media/audio/alarms, _size=0, format=12289, parent=3, date_added=172827, date_modified=1230739200, mime_type=NULL, title=alarms, description=NULL, _display_name=alarms, picasa_id=NULL, orientation=NULL, latitude=NULL, longitude=NULL, datetaken=NULL, mini_thumb_magic=NULL, bucket_id=-43825030, bucket_display_name=audio, isprivate=NULL, title_key=NULL, artist_id=NULL, album_id=NULL, composer=NULL, track=NULL, year=NULL, date=NULL, is_ringtone=NULL, is_music=NULL, is_alarm=NULL, is_notification=NULL, is_podcast=NULL, album_artist=NULL, album_artist_key=NULL, duration=NULL, bookmark=NULL, artist=NULL, album=NULL, resolution=NULL, tags=NULL, category=NULL, language=NULL, mini_thumb_data=NULL, name=NULL, media_type=0, old_id=NULL, storage_id=0, is_drm=0, width=NULL, height=NULL, camera_refocus=0

```

##  adb shell dumpsys netstats detail全字符
```
Active interfaces:
  iface=tun0 ident=[{type=VPN, subType=COMBINED, metered=false}] 
  iface=wlan0 ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}] 
Active UID interfaces:
  iface=tun0 ident=[{type=VPN, subType=COMBINED, metered=false}] 
  iface=wlan0 ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}] 
Dev stats:
  Pending bytes: 0
  History since boot:
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=106613 rp=291 tb=39561 tp=334 op=0
      st=1545051600 rb=4297352 rp=7660 tb=8204613 tp=11037 op=0
      st=1545055200 rb=254016 rp=725 tb=157683 tp=833 op=0
      st=1545058800 rb=1868 rp=23 tb=2846 tp=30 op=0
      st=1545062400 rb=72 rp=1 tb=166 tp=2 op=0
      st=1545069600 rb=531 rp=7 tb=715 tp=8 op=0
      st=1545094800 rb=24638985 rp=30319 tb=4763461 tp=26555 op=0
      st=1545098400 rb=6258127 rp=9850 tb=1747421 tp=10521 op=0
      st=1545102000 rb=716947 rp=1378 tb=225295 tp=1449 op=0
  ident=[{type=WIFI, subType=COMBINED, networkId="102", metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=54443497 rp=48792 tb=7282128 tp=49567 op=0
      st=1545051600 rb=75071 rp=177 tb=42600 tp=216 op=0
      st=1545055200 rb=64664008 rp=55777 tb=5460622 tp=46474 op=0
      st=1545058800 rb=79045709 rp=66941 tb=6840433 tp=57304 op=0
      st=1545062400 rb=76734222 rp=54226 tb=5348766 tp=53860 op=0
      st=1545066000 rb=10050 rp=213 tb=346 tp=1 op=0
      st=1545069600 rb=7172 rp=151 tb=346 tp=1 op=0
      st=1545073200 rb=7481 rp=146 tb=346 tp=1 op=0
      st=1545076800 rb=6555 rp=140 tb=346 tp=1 op=0
      st=1545080400 rb=6351 rp=136 tb=346 tp=1 op=0
      st=1545084000 rb=6628 rp=140 tb=346 tp=1 op=0
      st=1545087600 rb=6699 rp=140 tb=346 tp=1 op=0
      st=1545091200 rb=650419 rp=1345 tb=359656 tp=1547 op=0
      st=1545094800 rb=53254322 rp=46254 tb=6950142 tp=41290 op=0
  ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545102000 rb=64970679 rp=52581 tb=4527887 tp=37284 op=0
  ident=[{type=VPN, subType=COMBINED, metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=48839617 rp=42182 tb=3248789 tp=32397 op=0
      st=1545051600 rb=3665873 rp=9238 tb=6999511 tp=9031 op=0
      st=1545055200 rb=59528515 rp=46752 tb=2279231 tp=32218 op=0
      st=1545058800 rb=72773728 rp=56153 tb=2565801 tp=38243 op=0
      st=1545062400 rb=73921792 rp=52458 tb=1662003 tp=34775 op=0
      st=1545091200 rb=452685 rp=1339 tb=242994 tp=1819 op=0
      st=1545094800 rb=69918327 rp=65432 tb=6235293 tp=49944 op=0
      st=1545098400 rb=4223882 rp=6954 tb=838206 tp=7665 op=0
      st=1545102000 rb=37759051 rp=29496 tb=1386538 tp=20080 op=0
Xt stats:
  Pending bytes: 0
  History since boot:
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=106213 rp=281 tb=36889 tp=334 op=0
      st=1545051600 rb=4258515 rp=6913 tb=7968437 tp=8234 op=0
      st=1545055200 rb=253541 rp=716 tb=150839 tp=830 op=0
      st=1545058800 rb=1868 rp=23 tb=2606 tp=30 op=0
      st=1545062400 rb=72 rp=1 tb=150 tp=2 op=0
      st=1545069600 rb=531 rp=7 tb=651 tp=8 op=0
      st=1545094800 rb=24394391 rp=25462 tb=4526357 tp=26026 op=0
      st=1545098400 rb=6221803 rp=9110 tb=1656576 tp=10367 op=0
      st=1545102000 rb=711609 rp=1249 tb=213228 tp=1438 op=0
  ident=[{type=WIFI, subType=COMBINED, networkId="102", metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=54435678 rp=48341 tb=6594389 tp=49606 op=0
      st=1545051600 rb=75038 rp=176 tb=39563 tp=216 op=0
      st=1545055200 rb=64607745 rp=53837 tb=4808728 tp=46429 op=0
      st=1545058800 rb=78970886 rp=64320 tb=6037366 tp=57264 op=0
      st=1545062400 rb=76731760 rp=54108 tb=4594939 tp=53857 op=0
      st=1545066000 rb=10354 rp=213 tb=664 tp=2 op=0
      st=1545069600 rb=7476 rp=151 tb=664 tp=2 op=0
      st=1545073200 rb=7683 rp=143 tb=664 tp=2 op=0
      st=1545076800 rb=6859 rp=140 tb=664 tp=2 op=0
      st=1545080400 rb=6683 rp=137 tb=664 tp=2 op=0
      st=1545084000 rb=6960 rp=141 tb=664 tp=2 op=0
      st=1545087600 rb=7031 rp=141 tb=664 tp=2 op=0
      st=1545091200 rb=650660 rp=1343 tb=338313 tp=1547 op=0
      st=1545094800 rb=53227871 rp=45315 tb=6372251 tp=41283 op=0
  ident=[{type=WIFI, subType=COMBINED, networkId="3CC_5G", metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545102000 rb=64966615 rp=52461 tb=4003363 tp=37193 op=0
  ident=[{type=VPN, subType=COMBINED, metered=false}] uid=-1 set=ALL tag=0x0
    NetworkStatsHistory: bucketDuration=3600
      st=1545048000 rb=48847098 rp=42231 tb=3246678 tp=32206 op=0
      st=1545051600 rb=3665873 rp=9238 tb=6942931 tp=7577 op=0
      st=1545055200 rb=59528515 rp=46752 tb=2273984 tp=32128 op=0
      st=1545058800 rb=72773728 rp=56153 tb=2562170 tp=38153 op=0
      st=1545062400 rb=73921792 rp=52458 tb=1744429 tp=36077 op=0
      st=1545066000 rb=0 rp=0 tb=60484 tp=994 op=0
      st=1545069600 rb=0 rp=0 tb=72266 tp=1189 op=0
      st=1545073200 rb=0 rp=0 tb=52143 tp=858 op=0
      st=1545076800 rb=0 rp=0 tb=75209 tp=1235 op=0
      st=1545080400 rb=0 rp=0 tb=55242 tp=907 op=0
      st=1545084000 rb=0 rp=0 tb=68859 tp=1132 op=0
      st=1545087600 rb=0 rp=0 tb=56007 tp=916 op=0
      st=1545091200 rb=452685 rp=1339 tb=258388 tp=2119 op=0
      st=1545094800 rb=69918332 rp=65432 tb=6201737 tp=49105 op=0
      st=1545098400 rb=4223877 rp=6954 tb=838515 tp=7650 op=0
      st=1545102000 rb=37759051 rp=29496 tb=1387569 tp=20089 op=0
UID stats:
  Pending bytes: 0
  History since boot:
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=0 set=DEFAULT tag=0x0
    NetworkStatsHistory: bucketDuration=7200
      st=1545048000 rb=86146 rp=1221 tb=47945 tp=944 op=0
      st=1545055200 rb=29851 rp=249 tb=13490 tp=202 op=0
      st=1545062400 rb=72 rp=1 tb=150 tp=2 op=0
      st=1545069600 rb=261 rp=3 tb=208 tp=3 op=0
      st=1545091200 rb=278100 rp=3403 tb=100828 tp=2035 op=0
      st=1545098400 rb=258816 rp=2536 tb=127247 tp=2410 op=0
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=0 set=DBG_VPN_IN tag=0x0
    NetworkStatsHistory: bucketDuration=7200
      st=1545048000 rb=36203 rp=594 tb=25756 tp=510 op=0
      st=1545055200 rb=14964 rp=116 tb=6608 tp=81 op=0
      st=1545091200 rb=87130 rp=1484 tb=51767 tp=1004 op=0
      st=1545098400 rb=79847 rp=1102 tb=64950 tp=1223 op=0
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=1000 set=DEFAULT tag=0x0
    NetworkStatsHistory: bucketDuration=7200
      st=1545048000 rb=60727 rp=113 tb=21327 tp=170 op=0
      st=1545055200 rb=1158 rp=15 tb=3122 tp=27 op=0
      st=1545069600 rb=270 rp=4 tb=443 tp=5 op=0
      st=1545091200 rb=24324 rp=123 tb=31789 tp=214 op=0
      st=1545098400 rb=28236 rp=132 tb=27600 tp=216 op=0
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=1000 set=DBG_VPN_IN tag=0x0
    NetworkStatsHistory: bucketDuration=7200
      st=1545048000 rb=8271 rp=48 tb=14036 tp=75 op=0
      st=1545055200 rb=336 rp=3 tb=760 tp=5 op=0
      st=1545091200 rb=23716 rp=114 tb=29680 tp=180 op=0
      st=1545098400 rb=20391 rp=109 tb=23318 tp=166 op=0
  ident=[{type=MOBILE, subType=COMBINED, subscriberId=460110..., metered=true}] uid=1001 set=FOREGROUND tag=0x0
    NetworkStatsHistory: bucketDuration=7200
      st=1545055200 rb=0 rp=0 tb=48 tp=1 op=0
      st=1545091200 rb=1558 rp=31 tb=8040 tp=50 op=0
      st=1545098400 rb=1018 rp=11 tb=2480 tp=21 op=0




```


## adb shell dumpsys SurfaceFlinger --latency  全字符
```

adb shell dumpsys SurfaceFlinger --latency       // 画面合成帧服务状态  全字符
16666666
9223372036854775807	24382784960224	9223372036854775807
9223372036854775807	24382868506891	9223372036854775807
9223372036854775807	24382885229391	9223372036854775807
9223372036854775807	24382901935381	9223372036854775807
9223372036854775807	24382935338141	9223372036854775807
9223372036854775807	24382968763089	9223372036854775807
9223372036854775807	24382985478610	9223372036854775807
9223372036854775807	24383002186943	9223372036854775807
9223372036854775807	24383018876474	9223372036854775807
9223372036854775807	24383035587724	9223372036854775807
9223372036854775807	24383052287360	9223372036854775807
9223372036854775807	24383068990433	9223372036854775807
9223372036854775807	24383085702412	9223372036854775807
9223372036854775807	24383102419964	9223372036854775807
9223372036854775807	24383119167151	9223372036854775807
9223372036854775807	24383135964808	9223372036854775807


```


## adb shell dumpsys sensorservice 全字符
```
adb shell dumpsys sensorservice              //  全字符


Sensor Device:
HAL: MultiHal Sensor Module (Google, Inc), version 0x01030001
Total 39 h/w sensors, 39 running:
0x00000001) active-count = 1; sampling_period(ms) = {66.7}, selected = 66.7 ms; batching_period(ms) = {100.0}, selected = 100.0 ms
0x00000007) active-count = 1; sampling_period(ms) = {500.0}, selected = 500.0 ms; batching_period(ms) = {0.0}, selected = 0.0 ms
0x00000013) active-count = 4; sampling_period(ms) = {200.0, 200.0, 200.0, 600.0}, selected = 200.0 ms; batching_period(ms) = {360000.0, 0.0, 180.0, 0.0}, selected = 0.0 ms
Sensor List:
0x00000001) ICM20608 Accelerometer    | InvenSense      | ver: 1 | type: android.sensor.accelerometer(1) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (10000, 0) events | non-wakeUp | 
0x00000002) ST480 ST480               | SENODIA         | ver: 1 | type: android.sensor.magnetic_field(2) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (1000, 0) events | non-wakeUp | 
0x00000003) ST480 ST480 Uncalibrated  | SENODIA         | ver: 1 | type: android.sensor.magnetic_field_uncalibrated(14) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (1000, 0) events | non-wakeUp | 
0x00000004) ICM20608 Gyroscope        | InvenSense      | ver: 1 | type: android.sensor.gyroscope(4) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=250.00Hz | FIFO (max,reserved) = (2500, 0) events | non-wakeUp | 
0x00000005) ICM20608 Gyroscope Uncalibrated | InvenSense      | ver: 1 | type: android.sensor.gyroscope_uncalibrated(16) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=250.00Hz | FIFO (max,reserved) = (2500, 0) events | non-wakeUp | 
0x00000006) LTR578 ALSPS              | LiteOn          | ver: 2 | type: android.sensor.proximity(8) | perm: n/a
	 on-change | minRate=0.02Hz | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | wakeUp | 
0x00000007) LTR578 ALSPS              | LiteOn          | ver: 1 | type: android.sensor.light(5) | perm: n/a
	 on-change | minRate=0.02Hz | minDelay=0us | FIFO (max,reserved) = (400, 0) events | non-wakeUp | 
0x00000008) ICM20608 Accelerometer -Wakeup Secondary | InvenSense      | ver: 1 | type: android.sensor.accelerometer(1) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (10000, 0) events | wakeUp | 
0x00000009) ST480 ST480 -Wakeup Secondary | SENODIA         | ver: 1 | type: android.sensor.magnetic_field(2) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (1000, 0) events | wakeUp | 
0x0000000a) ST480 ST480 Uncalibrated -Wakeup Secondary | SENODIA         | ver: 1 | type: android.sensor.magnetic_field_uncalibrated(14) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (1000, 0) events | wakeUp | 
0x0000000b) ICM20608 Gyroscope -Wakeup Secondary | InvenSense      | ver: 1 | type: android.sensor.gyroscope(4) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=250.00Hz | FIFO (max,reserved) = (2500, 0) events | wakeUp | 
0x0000000c) ICM20608 Gyroscope Uncalibrated -Wakeup Secondary | InvenSense      | ver: 1 | type: android.sensor.gyroscope_uncalibrated(16) | perm: n/a
	 continuous | minRate=1.00Hz | maxRate=250.00Hz | FIFO (max,reserved) = (2500, 0) events | wakeUp | 
0x0000000d) LTR578 ALSPS -Non Wakeup Secondary | LiteOn          | ver: 2 | type: android.sensor.proximity(8) | perm: n/a
	 on-change | minRate=0.02Hz | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | non-wakeUp | 
0x0000000e) LTR578 ALSPS -Wakeup Secondary | LiteOn          | ver: 1 | type: android.sensor.light(5) | perm: n/a
	 on-change | minRate=0.02Hz | minDelay=0us | FIFO (max,reserved) = (400, 0) events | wakeUp | 
0x0000000f) Gravity                   | QTI             | ver: 2 | type: android.sensor.gravity(9) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (5846, 0) events | non-wakeUp | 
0x00000010) Linear Acceleration       | QTI             | ver: 2 | type: android.sensor.linear_acceleration(10) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (5846, 0) events | non-wakeUp | 
0x00000011) Rotation Vector           | QTI             | ver: 2 | type: android.sensor.rotation_vector(11) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 0) events | non-wakeUp | 
0x00000012) Step Detector             | QTI             | ver: 2 | type: android.sensor.step_detector(18) | perm: n/a
	 special-trigger | maxDelay=0us | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | non-wakeUp | 
0x00000013) Step Counter              | QTI             | ver: 2 | type: android.sensor.step_counter(19) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | non-wakeUp | 
0x00000014) Significant Motion Detector | QTI             | ver: 2 | type: android.sensor.significant_motion(17) | perm: n/a
	 one-shot | maxDelay=0us | minDelay=-1us | no batching | wakeUp | 
0x00000015) Game Rotation Vector      | QTI             | ver: 2 | type: android.sensor.game_rotation_vector(15) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 300) events | non-wakeUp | 
0x00000016) GeoMagnetic Rotation Vector | QTI             | ver: 2 | type: android.sensor.geomagnetic_rotation_vector(20) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (4047, 0) events | non-wakeUp | 
0x00000017) Orientation               | QTI             | ver: 2 | type: android.sensor.orientation(3) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 0) events | non-wakeUp | 
0x00000018) Tilt Detector             | QTI             | ver: 2 | type: android.sensor.tilt_detector(22) | perm: n/a
	 special-trigger | maxDelay=0us | minDelay=0us | FIFO (max,reserved) = (26311, 0) events | wakeUp | 
0x00000019) Gravity -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.gravity(9) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (5846, 0) events | wakeUp | 
0x0000001a) Linear Acceleration -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.linear_acceleration(10) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (5846, 0) events | wakeUp | 
0x0000001b) Rotation Vector -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.rotation_vector(11) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 0) events | wakeUp | 
0x0000001c) Step Detector -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.step_detector(18) | perm: n/a
	 special-trigger | maxDelay=0us | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | wakeUp | 
0x0000001d) Step Counter -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.step_counter(19) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | wakeUp | 
0x0000001e) Game Rotation Vector -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.game_rotation_vector(15) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 300) events | wakeUp | 
0x0000001f) GeoMagnetic Rotation Vector -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.geomagnetic_rotation_vector(20) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=50.00Hz | FIFO (max,reserved) = (4047, 0) events | wakeUp | 
0x00000020) Orientation -Wakeup Secondary | QTI             | ver: 2 | type: android.sensor.orientation(3) | perm: n/a
	 continuous | minRate=5.00Hz | maxRate=200.00Hz | FIFO (max,reserved) = (7517, 0) events | wakeUp | 
0x00000021) AMD                       | QTI             | ver: 2 | type:   com.qti.sensor.amd(33171006) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | no batching | non-wakeUp | 
0x00000022) RMD                       | QTI             | ver: 2 | type:   com.qti.sensor.rmd(33171007) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | no batching | non-wakeUp | 
0x00000023) Basic Gestures            | QTI             | ver: 2 | type: com.qti.sensor.basic_gestures(33171000) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | no batching | non-wakeUp | 
0x00000024) Facing                    | QTI             | ver: 2 | type: com.qti.sensor.facing(33171002) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | no batching | non-wakeUp | 
0x00000025) Pedometer                 | QTI             | ver: 2 | type: com.qti.sensor.pedometer(33171009) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | FIFO (max,reserved) = (8770, 100) events | non-wakeUp | 
0x00000026) Motion Accel              | QTI             | ver: 2 | type: com.qti.sensor.motion_accel(33171011) | perm: n/a
	 special-trigger | maxDelay=0us | minDelay=0us | no batching | non-wakeUp | 
0x00000027) Coarse Motion Classifier  | QTI             | ver: 3 | type:   com.qti.sensor.cmc(33171012) | perm: n/a
	 on-change | minRate=0.00Hz | minDelay=0us | FIFO (max,reserved) = (2505, 0) events | non-wakeUp | 
0x5f636779) Corrected Gyroscope Sensor | AOSP            | ver: 1 | type: android.sensor.gyroscope(4) | perm: n/a
	 continuous | maxDelay=0us | maxRate=250.00Hz | no batching | non-wakeUp | 
0x5f676172) Game Rotation Vector Sensor | AOSP            | ver: 3 | type: android.sensor.game_rotation_vector(15) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f676273) Gyroscope Bias (debug)    | AOSP            | ver: 1 | type: android.sensor.accelerometer(1) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f67656f) GeoMag Rotation Vector Sensor | AOSP            | ver: 3 | type: android.sensor.geomagnetic_rotation_vector(20) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f677276) Gravity Sensor            | AOSP            | ver: 3 | type: android.sensor.gravity(9) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f6c696e) Linear Acceleration Sensor | AOSP            | ver: 3 | type: android.sensor.linear_acceleration(10) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f726f76) Rotation Vector Sensor    | AOSP            | ver: 3 | type: android.sensor.rotation_vector(11) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
0x5f797072) Orientation Sensor        | AOSP            | ver: 1 | type: android.sensor.orientation(3) | perm: n/a
	 continuous | maxDelay=0us | maxRate=200.00Hz | no batching | non-wakeUp | 
Fusion States:
9-axis fusion disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
game fusion(no mag) disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
geomag fusion (no gyro) disabled (0 clients), gyro-rate= 200.00Hz, q=< 0, 0, 0, 0 > (0), b=< 0, 0, 0 >
Recent Sensor events:
Orientation: last 10 events
	 1 (ts=49389.806111479, wall=10:25:40.599) 143.28, -38.01, -13.00, 
	 2 (ts=49389.826100493, wall=10:25:40.620) 143.29, -38.01, -13.00, 
	 3 (ts=49389.846089507, wall=10:25:40.642) 143.28, -38.01, -13.00, 
	 4 (ts=49389.866078520, wall=10:25:40.661) 143.21, -38.00, -13.02, 
	 5 (ts=49389.886067534, wall=10:25:40.682) 143.19, -37.99, -13.04, 
	 6 (ts=49389.906056548, wall=10:25:40.703) 143.16, -37.99, -13.07, 
	 7 (ts=49389.926045561, wall=10:25:40.723) 143.12, -37.98, -13.07, 
	 8 (ts=49389.946034575, wall=10:25:40.744) 143.10, -37.97, -13.07, 
	 9 (ts=49389.966054106, wall=10:25:40.765) 143.09, -37.95, -13.07, 
	10 (ts=49389.986043120, wall=10:25:40.785) 143.12, -37.94, -13.08, 
Game Rotation Vector: last 10 events
	 1 (ts=47218.567024985, wall=09:49:29.854) 0.22, 0.14, 0.69, 0.67, 
	 2 (ts=47218.577034751, wall=09:49:29.865) 0.22, 0.14, 0.69, 0.67, 
	 3 (ts=47218.587044517, wall=09:49:29.875) 0.21, 0.14, 0.69, 0.67, 
	 4 (ts=47218.597054282, wall=09:49:29.885) 0.21, 0.14, 0.69, 0.67, 
	 5 (ts=47218.607064048, wall=09:49:29.896) 0.21, 0.14, 0.69, 0.67, 
	 6 (ts=47218.617073813, wall=09:49:29.906) 0.21, 0.14, 0.69, 0.67, 
	 7 (ts=47218.627083579, wall=09:49:29.916) 0.21, 0.14, 0.69, 0.67, 
	 8 (ts=47218.637093345, wall=09:49:29.927) 0.21, 0.14, 0.69, 0.67, 
	 9 (ts=47218.647103110, wall=09:49:29.937) 0.21, 0.14, 0.69, 0.67, 
	10 (ts=47218.657112876, wall=09:49:29.947) 0.21, 0.14, 0.69, 0.67, 
Significant Motion Detector: last 2 events
	 1 (ts=2705.598469272, wall=21:27:53.805) 1.00, 
	 2 (ts=3366.641328087, wall=21:38:44.701) 1.00, 
Step Counter: last 50 events
	 1 (ts=58259.297141846, wall=12:53:29.837) 6707, 
	 2 (ts=58259.557395752, wall=12:53:29.837) 6708, 
	 3 (ts=58382.847742943, wall=12:55:32.968) 6709, 
	 4 (ts=58464.088502044, wall=12:56:54.328) 6710, 
	 5 (ts=58464.408814544, wall=12:56:55.310) 6711, 
	 6 (ts=58464.829224700, wall=12:56:55.310) 6712, 
	 7 (ts=58483.547826967, wall=12:57:14.028) 6713, 
	 8 (ts=58483.908178529, wall=12:57:14.028) 6714, 
	 9 (ts=58492.076119650, wall=12:57:22.858) 6715, 
	10 (ts=58493.137154807, wall=12:57:23.839) 6716, 
	11 (ts=58500.424253607, wall=12:57:30.706) 6717, 
	12 (ts=58501.084898138, wall=12:57:31.688) 6718, 
	13 (ts=58557.880377279, wall=12:58:28.342) 6719, 
	14 (ts=58620.080993349, wall=12:59:30.341) 6720, 
	15 (ts=58624.164977724, wall=12:59:34.746) 6721, 
	16 (ts=58624.645446474, wall=12:59:35.627) 6722, 
	17 (ts=58625.506286318, wall=12:59:35.627) 6723, 
	18 (ts=58626.046828519, wall=12:59:36.509) 6724, 
	19 (ts=58626.647414457, wall=12:59:37.388) 6725, 
	20 (ts=58628.309035551, wall=12:59:39.110) 6726, 
	21 (ts=58628.709426176, wall=12:59:39.110) 6727, 
	22 (ts=58632.453078519, wall=12:59:43.414) 6728, 
	23 (ts=58633.113723051, wall=12:59:43.414) 6729, 
	24 (ts=58633.594191801, wall=12:59:44.274) 6730, 
	25 (ts=58634.214797269, wall=12:59:45.136) 6731, 
	26 (ts=58634.815383207, wall=12:59:45.136) 6732, 
	27 (ts=58635.375930082, wall=12:59:45.997) 6733, 
	28 (ts=58635.936458541, wall=12:59:46.856) 6734, 
	29 (ts=58636.476985885, wall=12:59:46.856) 6735, 
	30 (ts=58636.937435103, wall=12:59:47.719) 6736, 
	31 (ts=58637.618099166, wall=12:59:47.719) 6737, 
	32 (ts=58638.178646041, wall=12:59:48.579) 6738, 
	33 (ts=58638.739192916, wall=12:59:49.440) 6739, 
	34 (ts=58639.239681197, wall=12:59:49.440) 6740, 
	35 (ts=58639.800228072, wall=12:59:50.301) 6741, 
	36 (ts=58640.280696822, wall=12:59:51.142) 6742, 
	37 (ts=58640.921321822, wall=12:59:51.142) 6743, 
	38 (ts=58641.521907760, wall=12:59:51.983) 6744, 
	39 (ts=58642.062435103, wall=12:59:52.823) 6745, 
	40 (ts=58642.622981978, wall=12:59:52.823) 6746, 
	41 (ts=58643.183528853, wall=12:59:53.664) 6747, 
	42 (ts=58643.684017135, wall=12:59:54.504) 6748, 
	43 (ts=58644.404720260, wall=12:59:54.504) 6749, 
	44 (ts=58644.925228072, wall=12:59:55.344) 6750, 
	45 (ts=58645.385677291, wall=12:59:56.187) 6751, 
	46 (ts=58646.166475031, wall=12:59:57.028) 6752, 
	47 (ts=58651.111299249, wall=13:00:02.073) 6753, 
	48 (ts=58656.636666758, wall=13:00:07.018) 6754, 
	49 (ts=58657.317330821, wall=13:00:07.838) 6755, 
	50 (ts=58657.897897227, wall=13:00:08.659) 6756, 
Step Detector: last 10 events
	 1 (ts=266.723278680, wall=20:47:04.996) 1.00, 
	 2 (ts=266.983532587, wall=20:47:04.996) 1.00, 
	 3 (ts=267.303845087, wall=20:47:04.996) 1.00, 
	 4 (ts=271.267712274, wall=20:47:08.799) 1.00, 
	 5 (ts=271.728161493, wall=20:47:09.740) 1.00, 
	 6 (ts=272.028454462, wall=20:47:09.740) 1.00, 
	 7 (ts=272.288708368, wall=20:47:09.740) 1.00, 
	 8 (ts=273.389782587, wall=20:47:11.622) 1.00, 
	 9 (ts=273.890270868, wall=20:47:11.622) 1.00, 
	10 (ts=274.150524774, wall=20:47:11.622) 1.00, 
Linear Acceleration: last 10 events
	 1 (ts=46750.969951023, wall=09:41:42.483) 0.03, 0.72, -0.53, 
	 2 (ts=46750.989970555, wall=09:41:42.503) 0.72, -0.04, -0.93, 
	 3 (ts=46751.009990086, wall=09:41:42.524) 0.53, -0.75, -1.49, 
	 4 (ts=46751.030009617, wall=09:41:42.544) 0.13, -0.88, -1.25, 
	 5 (ts=46751.050029148, wall=09:41:42.565) -0.65, -0.67, -0.97, 
	 6 (ts=46751.070048680, wall=09:41:42.586) -0.65, 0.15, -0.81, 
	 7 (ts=46751.090068211, wall=09:41:42.606) -0.46, 0.27, -1.09, 
	 8 (ts=46751.110087742, wall=09:41:42.627) -0.29, -0.44, -1.26, 
	 9 (ts=46751.130107273, wall=09:41:42.648) -0.05, -0.28, -0.07, 
	10 (ts=46751.150126805, wall=09:41:42.669) 0.20, 0.34, -0.70, 
Gravity: last 10 events
	 1 (ts=58918.870586946, wall=13:04:28.794) 3.32, 5.83, 7.15, 
	 2 (ts=58918.930553987, wall=13:04:28.856) 3.40, 5.71, 7.21, 
	 3 (ts=58918.990490511, wall=13:04:28.918) 3.53, 5.68, 7.17, 
	 4 (ts=58919.050457552, wall=13:04:28.979) 3.66, 5.70, 7.09, 
	 5 (ts=58919.110394075, wall=13:04:29.042) 4.02, 5.72, 6.88, 
	 6 (ts=58919.170330599, wall=13:04:29.104) 4.38, 5.77, 6.62, 
	 7 (ts=58919.230297640, wall=13:04:29.104) 3.91, 5.59, 7.05, 
	 8 (ts=58919.290234163, wall=13:04:29.165) 2.53, 5.04, 8.02, 
	 9 (ts=58919.350201204, wall=13:04:29.227) 2.00, 4.64, 8.41, 
	10 (ts=58919.410137728, wall=13:04:29.289) 1.79, 4.58, 8.48, 
LTR578 ALSPS -Non Wakeup Secondary: last 6 events
	 1 (ts=132.333002792, wall=20:44:49.526) 5.00, 0.00, 0.00, 
	 2 (ts=43964.505424129, wall=08:55:16.161) 5.00, 0.00, 0.00, 
	 3 (ts=50105.976380214, wall=10:37:36.759) 5.00, 0.00, 0.00, 
	 4 (ts=55841.195384255, wall=12:13:11.977) 5.00, 0.00, 0.00, 
	 5 (ts=55862.097364051, wall=12:13:32.879) 5.00, 0.00, 0.00, 
	 6 (ts=55945.130059701, wall=12:14:55.912) 5.00, 0.00, 0.00, 
ICM20608 Accelerometer -Wakeup Secondary: last 50 events
	 1 (ts=13344.782658201, wall=00:25:02.001) 1.88, 0.03, 9.62, 
	 2 (ts=13344.822666746, wall=00:25:02.049) 1.89, 0.04, 9.67, 
	 3 (ts=13344.862675291, wall=00:25:02.083) 1.89, 0.04, 9.62, 
	 4 (ts=13344.902683836, wall=00:25:02.124) 1.89, 0.06, 9.63, 
	 5 (ts=13344.942692381, wall=00:25:02.173) 1.88, 0.03, 9.63, 
	 6 (ts=13344.982700926, wall=00:25:02.206) 1.87, 0.04, 9.63, 
	 7 (ts=13345.022709471, wall=00:25:02.248) 1.86, 0.04, 9.65, 
	 8 (ts=13345.062718016, wall=00:25:02.289) 1.89, 0.07, 9.64, 
	 9 (ts=13345.102726561, wall=00:25:02.330) 1.86, 0.04, 9.58, 
	10 (ts=13345.142735106, wall=00:25:02.371) 1.89, 0.04, 9.65, 
	11 (ts=13345.182743651, wall=00:25:02.412) 1.89, 0.02, 9.64, 
	12 (ts=13345.222752195, wall=00:25:02.453) 1.89, 0.05, 9.66, 
	13 (ts=13345.262760740, wall=00:25:02.494) 1.86, 0.06, 9.58, 
	14 (ts=13345.302769285, wall=00:25:02.536) 1.86, 0.06, 9.60, 
	15 (ts=13345.342777830, wall=00:25:02.577) 1.88, 0.04, 9.63, 
	16 (ts=13345.382786375, wall=00:25:02.618) 1.89, 0.05, 9.66, 
	17 (ts=13345.422794920, wall=00:25:02.659) 1.88, 0.03, 9.63, 
	18 (ts=13345.462803465, wall=00:25:02.701) 1.87, 0.04, 9.58, 
	19 (ts=13775.365365262, wall=00:32:12.567) -2.36, 0.73, 9.49, 
	20 (ts=13775.405373807, wall=00:32:12.608) -2.36, 0.72, 9.48, 
	21 (ts=13775.445382351, wall=00:32:12.649) -2.37, 0.74, 9.52, 
	22 (ts=13775.485390896, wall=00:32:12.691) -2.37, 0.73, 9.53, 
	23 (ts=13775.525399441, wall=00:32:12.731) -2.37, 0.71, 9.48, 
	24 (ts=13775.565407986, wall=00:32:12.773) -2.35, 0.74, 9.53, 
	25 (ts=13775.605416531, wall=00:32:12.813) -2.37, 0.73, 9.53, 
	26 (ts=13775.645425076, wall=00:32:12.855) -2.38, 0.74, 9.54, 
	27 (ts=13775.685433621, wall=00:32:12.896) -2.35, 0.74, 9.52, 
	28 (ts=13775.725442166, wall=00:32:12.937) -2.39, 0.72, 9.46, 
	29 (ts=13775.765450711, wall=00:32:12.979) -2.38, 0.73, 9.54, 
	30 (ts=13775.805459256, wall=00:32:13.020) -2.36, 0.75, 9.51, 
	31 (ts=13775.845467801, wall=00:32:13.061) -2.37, 0.75, 9.51, 
	32 (ts=13775.885476346, wall=00:32:13.102) -2.40, 0.74, 9.53, 
	33 (ts=13775.925484891, wall=00:32:13.144) -2.38, 0.73, 9.47, 
	34 (ts=13775.965493435, wall=00:32:13.185) -2.39, 0.74, 9.50, 
	35 (ts=13776.005501980, wall=00:32:13.226) -2.38, 0.75, 9.49, 
	36 (ts=13776.045510525, wall=00:32:13.268) -2.38, 0.76, 9.53, 
	37 (ts=13776.085519070, wall=00:32:13.309) -2.38, 0.77, 9.49, 
	38 (ts=13776.125527615, wall=00:32:13.350) -2.40, 0.74, 9.50, 
	39 (ts=13776.165536160, wall=00:32:13.391) -2.41, 0.76, 9.55, 
	40 (ts=13776.205544705, wall=00:32:13.432) -2.39, 0.76, 9.51, 
	41 (ts=13776.245553250, wall=00:32:13.474) -2.38, 0.76, 9.50, 
	42 (ts=13776.285561795, wall=00:32:13.515) -2.41, 0.74, 9.50, 
	43 (ts=13776.325570340, wall=00:32:13.557) -2.37, 0.74, 9.45, 
	44 (ts=13776.365578885, wall=00:32:13.598) -2.39, 0.74, 9.53, 
	45 (ts=13776.405587430, wall=00:32:13.639) -2.39, 0.76, 9.55, 
	46 (ts=13776.445595975, wall=00:32:13.680) -2.40, 0.76, 9.53, 
	47 (ts=13776.485604519, wall=00:32:13.721) -2.39, 0.76, 9.49, 
	48 (ts=13776.525613064, wall=00:32:13.763) -2.39, 0.78, 9.51, 
	49 (ts=13776.565621609, wall=00:32:13.804) -2.40, 0.76, 9.49, 
	50 (ts=13776.605630154, wall=00:32:13.844) -2.41, 0.78, 9.53, 
LTR578 ALSPS: last 10 events
	 1 (ts=61925.051224808, wall=13:54:34.879) 21.00, 0.00, 0.00, 
	 2 (ts=61925.151749711, wall=13:54:34.980) 20.00, 0.00, 0.00, 
	 3 (ts=61927.363439384, wall=13:54:37.191) 21.00, 0.00, 0.00, 
	 4 (ts=61927.463964287, wall=13:54:37.293) 20.00, 0.00, 0.00, 
	 5 (ts=61927.966619316, wall=13:54:37.795) 21.00, 0.00, 0.00, 
	 6 (ts=61928.067144218, wall=13:54:37.895) 20.00, 0.00, 0.00, 
	 7 (ts=61928.670324150, wall=13:54:38.498) 21.00, 0.00, 0.00, 
	 8 (ts=61928.770849052, wall=13:54:38.599) 20.00, 0.00, 0.00, 
	 9 (ts=61932.289434257, wall=13:54:42.118) 21.00, 0.00, 0.00, 
	10 (ts=61932.389989677, wall=13:54:42.218) 20.00, 0.00, 0.00, 
LTR578 ALSPS: last 3 events
	 1 (ts=3932.802476950, wall=21:48:09.992) 5.00, 0.00, 0.00, 
	 2 (ts=3938.491332108, wall=21:48:15.679) 5.00, 0.00, 0.00, 
	 3 (ts=48937.148711242, wall=10:18:07.952) 0.00, 0.00, 0.00, 
ICM20608 Gyroscope: last 10 events
	 1 (ts=54245.196329028, wall=11:46:36.108) -0.00, -0.00, -0.00, 
	 2 (ts=54247.133255783, wall=11:46:37.968) 0.00, -0.01, -0.01, 
	 3 (ts=54399.907036643, wall=11:49:10.803) -0.04, -0.02, -0.02, 
	 4 (ts=57456.242454826, wall=12:40:07.227) 0.10, 0.13, 0.01, 
	 5 (ts=57456.441368400, wall=12:40:07.433) -0.11, -0.07, 0.01, 
	 6 (ts=57461.036461174, wall=12:40:11.915) -0.01, -0.01, 0.00, 
	 7 (ts=57471.508016875, wall=12:40:21.387) -0.01, 0.00, -0.01, 
	 8 (ts=57477.983758531, wall=12:40:27.927) 0.02, 0.00, 0.01, 
	 9 (ts=58945.657734524, wall=13:04:55.624) -0.05, 0.04, 0.03, 
	10 (ts=58948.495348330, wall=13:04:58.467) -0.00, -0.00, -0.01, 
ST480 ST480: last 10 events
	 1 (ts=48989.153875965, wall=10:18:59.965) 43.37, -31.01, -34.30, 
	 2 (ts=48989.220526355, wall=10:19:00.025) 43.22, -31.06, -33.35, 
	 3 (ts=48989.287176746, wall=10:19:00.105) 43.41, -32.31, -35.26, 
	 4 (ts=48989.353827136, wall=10:19:00.164) 42.13, -31.04, -34.65, 
	 5 (ts=48989.420477527, wall=10:19:00.245) 43.19, -31.40, -34.65, 
	 6 (ts=48989.487127918, wall=10:19:00.306) 43.17, -31.57, -33.98, 
	 7 (ts=48989.553778308, wall=10:19:00.364) 42.97, -31.83, -33.35, 
	 8 (ts=48989.620428699, wall=10:19:00.444) 42.51, -31.22, -33.58, 
	 9 (ts=48989.687079090, wall=10:19:00.504) 43.41, -32.72, -34.03, 
	10 (ts=48989.753729480, wall=10:19:00.564) 42.74, -31.83, -33.82, 
ICM20608 Accelerometer: last 50 events
	 1 (ts=61931.524541679, wall=13:54:41.381) -1.93, 0.06, -9.42, 
	 2 (ts=61931.589910332, wall=13:54:41.449) -1.91, 0.06, -9.39, 
	 3 (ts=61931.655278984, wall=13:54:41.518) -1.92, 0.06, -9.43, 
	 4 (ts=61931.720678154, wall=13:54:41.586) -1.91, 0.07, -9.42, 
	 5 (ts=61931.786046806, wall=13:54:41.655) -1.92, 0.06, -9.41, 
	 6 (ts=61931.851415459, wall=13:54:41.725) -1.91, 0.05, -9.40, 
	 7 (ts=61931.916814629, wall=13:54:41.792) -1.92, 0.05, -9.39, 
	 8 (ts=61931.982183281, wall=13:54:41.861) -1.92, 0.07, -9.40, 
	 9 (ts=61932.047582451, wall=13:54:41.930) -1.94, 0.03, -9.51, 
	10 (ts=61932.112951103, wall=13:54:41.998) -1.93, 0.06, -9.45, 
	11 (ts=61932.178319755, wall=13:54:42.067) -1.92, 0.07, -9.38, 
	12 (ts=61932.243718925, wall=13:54:42.135) -1.91, 0.08, -9.40, 
	13 (ts=61932.309087578, wall=13:54:42.204) -1.92, 0.07, -9.43, 
	14 (ts=61932.374456230, wall=13:54:42.273) -1.93, 0.06, -9.44, 
	15 (ts=61932.439855400, wall=13:54:42.342) -1.91, 0.06, -9.45, 
	16 (ts=61932.505224052, wall=13:54:42.410) -1.92, 0.07, -9.40, 
	17 (ts=61932.570623222, wall=13:54:42.480) -1.92, 0.06, -9.43, 
	18 (ts=61932.635991875, wall=13:54:42.480) -1.90, 0.06, -9.40, 
	19 (ts=61932.701360527, wall=13:54:42.548) -1.93, 0.06, -9.42, 
	20 (ts=61932.766759697, wall=13:54:42.617) -1.92, 0.06, -9.44, 
	21 (ts=61932.832128349, wall=13:54:42.685) -1.92, 0.06, -9.45, 
	22 (ts=61932.897497002, wall=13:54:42.754) -1.92, 0.06, -9.41, 
	23 (ts=61932.962896171, wall=13:54:42.823) -1.91, 0.06, -9.42, 
	24 (ts=61933.028264824, wall=13:54:42.892) -1.92, 0.06, -9.44, 
	25 (ts=61933.093663994, wall=13:54:42.960) -1.92, 0.06, -9.43, 
	26 (ts=61933.159032646, wall=13:54:43.028) -1.92, 0.06, -9.43, 
	27 (ts=61933.224401298, wall=13:54:43.098) -1.91, 0.06, -9.43, 
	28 (ts=61933.289800468, wall=13:54:43.166) -1.89, 0.03, -9.38, 
	29 (ts=61933.355169121, wall=13:54:43.235) -1.92, 0.08, -9.42, 
	30 (ts=61933.420568291, wall=13:54:43.304) -1.94, 0.05, -9.41, 
	31 (ts=61933.485936943, wall=13:54:43.373) -1.92, 0.05, -9.44, 
	32 (ts=61933.551305595, wall=13:54:43.441) -1.92, 0.06, -9.44, 
	33 (ts=61933.616704765, wall=13:54:43.510) -1.94, 0.04, -9.45, 
	34 (ts=61933.682073418, wall=13:54:43.578) -1.90, 0.05, -9.41, 
	35 (ts=61933.747442070, wall=13:54:43.647) -1.92, 0.06, -9.38, 
	36 (ts=61933.812841240, wall=13:54:43.716) -1.91, 0.06, -9.41, 
	37 (ts=61933.878209892, wall=13:54:43.784) -1.93, 0.05, -9.45, 
	38 (ts=61933.943609062, wall=13:54:43.853) -1.91, 0.06, -9.44, 
	39 (ts=61934.008977714, wall=13:54:43.922) -1.91, 0.06, -9.39, 
	40 (ts=61934.074346367, wall=13:54:43.990) -1.90, 0.07, -9.42, 
	41 (ts=61934.139745537, wall=13:54:44.059) -1.92, 0.06, -9.44, 
	42 (ts=61934.205114189, wall=13:54:44.059) -1.92, 0.06, -9.44, 
	43 (ts=61934.270482841, wall=13:54:44.128) -1.92, 0.04, -9.44, 
	44 (ts=61934.335882011, wall=13:54:44.197) -1.92, 0.08, -9.42, 
	45 (ts=61934.401250664, wall=13:54:44.265) -1.93, 0.05, -9.43, 
	46 (ts=61934.466649834, wall=13:54:44.334) -1.92, 0.06, -9.42, 
	47 (ts=61934.532018486, wall=13:54:44.403) -1.94, 0.05, -9.37, 
	48 (ts=61934.597387138, wall=13:54:44.473) -1.92, 0.07, -9.46, 
	49 (ts=61934.662786308, wall=13:54:44.540) -1.92, 0.05, -9.41, 
	50 (ts=61934.728154961, wall=13:54:44.608) -1.93, 0.06, -9.45, 
Active sensors:
ICM20608 Accelerometer (handle=0x00000001, connections=1)
LTR578 ALSPS (handle=0x00000007, connections=1)
Step Counter (handle=0x00000013, connections=4)
Socket Buffer size = 984 events
WakeLock Status: not held 
Mode : NORMAL
6 active connections
Connection Number: 0 
	Operating Mode: NORMAL
	 com.tencent.mobileqq.msf.core.ad | WakeLockRefCount 0 | uid 10112 | cache size 0 | max cache size 0
	 Step Counter 0x00000013 | status: active | pending flush events 0 
Connection Number: 1 
	Operating Mode: NORMAL
	 com.meizu.alphame.engine.movingstate.MovingStateEngine | WakeLockRefCount 0 | uid 1000 | cache size 0 | max cache size 0
	 Step Counter 0x00000013 | status: active | pending flush events 0 
Connection Number: 2 
	Operating Mode: NORMAL
	 com.meizu.net.pedometer.receiver.a | WakeLockRefCount 0 | uid 10089 | cache size 0 | max cache size 0
	 Step Counter 0x00000013 | status: active | pending flush events 0 
Connection Number: 3 
	Operating Mode: NORMAL
	 com.tencent.mm.plugin.sport.c.k | WakeLockRefCount 0 | uid 10108 | cache size 0 | max cache size 0
	 Step Counter 0x00000013 | status: active | pending flush events 0 
Connection Number: 4 
	Operating Mode: NORMAL
	 com.android.server.policy.WindowOrientationListener | WakeLockRefCount 0 | uid 1000 | cache size 0 | max cache size 0
	 ICM20608 Accelerometer 0x00000001 | status: active | pending flush events 0 
Connection Number: 5 
	Operating Mode: NORMAL
	  | WakeLockRefCount 0 | uid 1000 | cache size 0 | max cache size 0
	 LTR578 ALSPS 0x00000007 | status: active | pending flush events 0 
Previous Registrations:
13:46:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:46:29 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
13:36:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:36:29 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
13:26:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:26:29 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
13:16:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:16:29 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
13:07:42 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
13:06:32 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
13:06:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:06:07 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
13:05:18 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
13:05:01 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
13:04:58 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
13:04:58 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
13:04:58 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
13:04:58 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
13:04:55 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
13:04:55 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
13:04:55 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
13:04:55 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
13:04:29 de-activated handle=0x00000001 package=qi
13:04:29 de-activated handle=0x0000000f package=akk
13:04:22 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:04:19 de-activated handle=0x0000000f package=akk
13:03:52 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:49 de-activated handle=0x0000000f package=akk
13:03:44 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:41 de-activated handle=0x0000000f package=akk
13:03:39 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:34 de-activated handle=0x0000000f package=akk
13:03:33 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:32 de-activated handle=0x0000000f package=akk
13:03:29 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:24 de-activated handle=0x0000000f package=akk
13:03:23 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:23 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=qi
13:03:23 de-activated handle=0x00000001 package=qi
13:03:23 de-activated handle=0x0000000f package=akk
13:03:15 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:03:15 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=qi
13:03:12 de-activated handle=0x00000001 package=qi
13:03:11 de-activated handle=0x0000000f package=akk
13:02:59 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:56 de-activated handle=0x0000000f package=akk
13:02:50 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:47 de-activated handle=0x0000000f package=akk
13:02:35 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:31 de-activated handle=0x0000000f package=akk
13:02:27 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:26 de-activated handle=0x0000000f package=akk
13:02:24 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:23 de-activated handle=0x0000000f package=akk
13:02:20 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:18 de-activated handle=0x0000000f package=akk
13:02:15 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
13:02:11 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=qi
13:02:02 de-activated handle=0x00000001 package=android.view.OrientationEventListener
13:01:33 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=android.view.OrientationEventListener
13:00:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
13:00:29 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:55:27 de-activated handle=0x00000001 package=android.view.OrientationEventListener
12:54:34 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=android.view.OrientationEventListener
12:50:29 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:47:30 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:47:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:46:24 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:46:13 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:46:05 de-activated handle=0x00000001 package=android.view.OrientationEventListener
12:45:18 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=android.view.OrientationEventListener
12:45:16 de-activated handle=0x00000001 package=android.view.OrientationEventListener
12:45:15 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=android.view.OrientationEventListener
12:44:52 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:44:04 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:43:03 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:41:19 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:41:01 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:40:44 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:40:43 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:40:27 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
12:40:27 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
12:40:27 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:27 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:21 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
12:40:21 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
12:40:21 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:21 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:11 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
12:40:11 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
12:40:10 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:10 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:07 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
12:40:07 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
12:40:05 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:40:05 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
12:38:05 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
12:37:24 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:37:06 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:35:58 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:34:44 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:34:16 de-activated handle=0x0000000f package=cn.tongdun.android.core.fql.uuuvuuUUUUvUvuVuUUV.vVVUVvvVVUV
12:34:14 activated handle=0x0000000f samplingRate=5000us maxReportLatency=0us package=cn.tongdun.android.core.fql.uuuvuuUUUUvUvuVuUUV.vVVUVvvVVUV
12:33:01 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:32:35 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:30:55 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:25:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:25:28 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:15:32 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
12:15:29 de-activated handle=0x0000000d package=com.android.systemui.keyguard.PalmRejection
12:15:28 activated handle=0x00000007 samplingRate=200000us maxReportLatency=0us package=
12:15:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:15:28 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:15:28 activated handle=0x0000000d samplingRate=0us maxReportLatency=0us package=com.android.systemui.keyguard.PalmRejection
12:15:26 de-activated handle=0x00000007 package=
12:14:56 de-activated handle=0x0000000d package=com.android.systemui.keyguard.PalmRejection
12:14:56 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:14:56 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:14:55 activated handle=0x0000000d samplingRate=0us maxReportLatency=0us package=com.android.systemui.keyguard.PalmRejection
12:14:55 activated handle=0x00000007 samplingRate=200000us maxReportLatency=0us package=
12:14:55 de-activated handle=0x00000007 package=
12:13:33 de-activated handle=0x0000000d package=com.android.systemui.keyguard.PalmRejection
12:13:33 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:13:33 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:13:32 activated handle=0x0000000d samplingRate=0us maxReportLatency=0us package=com.android.systemui.keyguard.PalmRejection
12:13:32 activated handle=0x00000007 samplingRate=200000us maxReportLatency=0us package=
12:13:31 de-activated handle=0x00000007 package=
12:13:17 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:13:17 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:13:16 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
12:13:15 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
12:13:14 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
12:13:13 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
12:13:12 de-activated handle=0x0000000d package=com.android.systemui.keyguard.PalmRejection
12:13:11 activated handle=0x0000000d samplingRate=0us maxReportLatency=0us package=com.android.systemui.keyguard.PalmRejection
12:13:11 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
12:13:10 activated handle=0x00000007 samplingRate=200000us maxReportLatency=0us package=
12:13:10 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
12:13:10 de-activated handle=0x00000007 package=
12:13:10 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
12:10:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:10:28 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
12:10:07 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
12:00:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
12:00:28 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
11:56:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
11:56:28 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
11:49:23 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
11:49:10 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:49:10 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:49:10 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:49:10 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:48:42 de-activated handle=0x00000001 package=com.android.server.policy.WindowOrientationListener
11:48:38 activated handle=0x00000001 samplingRate=66667us maxReportLatency=100000us package=com.android.server.policy.WindowOrientationListener
11:46:37 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:46:37 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:46:37 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:37 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:36 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:46:36 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:46:35 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:35 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:28 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
11:46:27 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
11:46:11 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:46:11 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:46:10 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:10 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:06 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:46:06 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:46:05 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:46:05 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:42:55 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
11:42:35 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:42:35 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:42:35 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:42:35 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:42:30 de-activated handle=0x00000001 package=com.facebook.ads.internal.g.a
11:42:30 de-activated handle=0x00000004 package=com.facebook.ads.internal.g.a
11:42:30 activated handle=0x00000004 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:42:30 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=com.facebook.ads.internal.g.a
11:41:18 activated handle=0x00000013 samplingRate=200000us maxReportLatency=360000000us package=com.tencent.mobileqq.msf.core.ad
11:40:30 de-activated handle=0x00000001 package=android.view.OrientationEventListener
11:40:27 activated handle=0x00000001 samplingRate=200000us maxReportLatency=0us package=android.view.OrientationEventListener
11:37:20 activated handle=0x00000013 samplingRate=200000us maxReportLatency=180000us package=com.meizu.net.pedometer.receiver.a
11:37:20 de-activated handle=0x00000013 package=com.meizu.net.pedometer.receiver.a
11:34:23 de-activated handle=0x00000004 package=com.umeng.commonsdk.internal.utils.j
11:34:22 activated handle=0x00000004 samplingRate=50000us maxReportLatency=0us package=com.umeng.commonsdk.internal.utils.j
11:34:17 de-activated handle=0x00000004 package=com.umeng.commonsdk.internal.utils.j
11:34:16 activated handle=0x00000004 samplingRate=50000us maxReportLatency=0us package=com.umeng.commonsdk.internal.utils.j
11:34:06 de-activated handle=0x00000001 package=qi
11:34:05 de-activated handle=0x0000000f package=akk
11:34:00 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
11:33:57 de-activated handle=0x0000000f package=akk
11:33:54 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
11:33:51 de-activated handle=0x0000000f package=akk
11:33:43 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
11:33:40 de-activated handle=0x0000000f package=akk
11:33:38 activated handle=0x0000000f samplingRate=66667us maxReportLatency=0us package=akk
11:33:21 de-activated handle=0x0000000f package=akk

```

##  adb shell dumpsys wifi ipmanager全字符
```
adb shell dumpsys wifi ipmanager                //   WIFI配置相关信息




IpManager.wlan0 APF dump:
  No apf support

IpManager.wlan0 StateMachine dump:
  12-17 20:43:20.235 - CMD_SET_MULTICAST_FILTER wlan0/-1 0 0 true [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:22.669 - OBSERVED interfaceAdded(wlan0)
  12-17 20:43:35.293 - CMD_STOP wlan0/-1 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:35.305 - INVOKE setNeighborDiscoveryOffload(true)
  12-17 20:43:35.308 - CMD_UPDATE_HTTP_PROXY wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:35.309 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/22 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:35.311 - CMD_START wlan0/22 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 20:43:35.316 - INVOKE setFallbackMulticastFilter(true)
  12-17 20:43:35.338 - CMD_CONFIRM wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 20:43:35.339 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 20:43:35.398 - INVOKE onPreDhcpAction()
  12-17 20:43:35.399 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 20:43:35.402 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 20:43:52.820 - INVOKE onPostDhcpAction()
  12-17 20:43:52.821 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 20:43:52.821 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 20:43:52.821 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 20:43:52.847 - CMD_CONFIGURE_LINKADDRESS wlan0/22 0 0 192.168.221.110/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-17 20:43:52.849 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 21:02:19.775 - CMD_STOP wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 21:02:19.834 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 21:02:20.038 - CMD_CLEAR_LINKADDRESS wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 21:02:20.038 - CMD_ON_QUIT wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-17 22:14:37.692 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 22:14:37.693 - CMD_UPDATE_HTTP_PROXY wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 22:14:37.693 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/22 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 22:14:37.719 - INVOKE setNeighborDiscoveryOffload(true)
  12-17 22:14:37.722 - CMD_START wlan0/22 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-17 22:14:37.754 - INVOKE setFallbackMulticastFilter(true)
  12-17 22:14:37.781 - CMD_CONFIRM wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 22:14:37.782 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 22:14:37.837 - INVOKE onPreDhcpAction()
  12-17 22:14:37.842 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 22:14:37.843 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 22:14:38.003 - INVOKE onPostDhcpAction()
  12-17 22:14:38.004 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 22:14:38.005 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 22:14:38.005 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-17 22:14:38.030 - CMD_CONFIGURE_LINKADDRESS wlan0/22 0 0 192.168.221.110/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-17 22:14:38.032 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 23:14:38.049 - INVOKE onPreDhcpAction()
  12-17 23:14:38.052 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 23:14:38.163 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-17 23:14:38.251 - INVOKE onPostDhcpAction()
  12-17 23:14:38.252 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-17 23:14:38.252 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-17 23:14:38.253 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 00:14:39.817 - INVOKE onPreDhcpAction()
  12-18 00:14:39.822 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 00:14:45.132 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 00:14:45.359 - INVOKE onPostDhcpAction()
  12-18 00:14:45.363 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 00:14:45.364 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 00:14:45.365 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 01:14:46.843 - INVOKE onPreDhcpAction()
  12-18 01:14:46.844 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 01:14:59.287 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 01:14:59.816 - INVOKE onPostDhcpAction()
  12-18 01:14:59.817 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 01:14:59.818 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 01:14:59.819 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 02:15:00.763 - INVOKE onPreDhcpAction()
  12-18 02:15:00.767 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 02:15:01.208 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 02:15:01.290 - INVOKE onPostDhcpAction()
  12-18 02:15:01.291 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 02:15:01.292 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 02:15:01.293 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 03:15:03.600 - INVOKE onPreDhcpAction()
  12-18 03:15:03.603 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 03:15:04.087 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 03:15:04.542 - INVOKE onPostDhcpAction()
  12-18 03:15:04.543 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 03:15:04.543 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 03:15:04.544 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 04:15:04.390 - INVOKE onPreDhcpAction()
  12-18 04:15:04.403 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 04:15:05.361 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 04:15:05.400 - INVOKE onPostDhcpAction()
  12-18 04:15:05.401 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 04:15:05.401 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 04:15:05.402 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 05:15:05.402 - INVOKE onPreDhcpAction()
  12-18 05:15:05.405 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 05:15:05.605 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 05:15:05.697 - INVOKE onPostDhcpAction()
  12-18 05:15:05.698 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 05:15:05.698 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 05:15:05.699 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 06:15:05.534 - INVOKE onPreDhcpAction()
  12-18 06:15:05.537 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 06:15:05.919 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 06:15:05.983 - INVOKE onPostDhcpAction()
  12-18 06:15:05.984 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 06:15:05.984 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 06:15:05.985 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 07:15:05.740 - INVOKE onPreDhcpAction()
  12-18 07:15:05.742 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 07:15:05.921 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 07:15:06.000 - INVOKE onPostDhcpAction()
  12-18 07:15:06.001 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 07:15:06.002 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 07:15:06.003 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 08:15:07.777 - INVOKE onPreDhcpAction()
  12-18 08:15:07.782 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 08:15:08.160 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 08:15:08.234 - INVOKE onPostDhcpAction()
  12-18 08:15:08.235 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 08:15:08.236 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 08:15:08.236 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 09:15:06.619 - INVOKE onPreDhcpAction()
  12-18 09:15:06.623 - CMD_PRE_DHCP_ACTION wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 09:15:06.688 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 09:15:06.779 - INVOKE onPostDhcpAction()
  12-18 09:15:06.781 - INVOKE onNewDhcpResults({IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds})
  12-18 09:15:06.782 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.221.110/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.221.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.221.1 wlan0,] DnsAddresses: [10.64.0.10,10.64.0.100,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 09:15:06.782 - CMD_POST_DHCP_ACTION wlan0/22 1 0 IP address 192.168.221.110/24 Gateway 192.168.221.1  DNS servers: [ 10.64.0.10 10.64.0.100 ] Domains  DHCP server /192.168.221.1 Vendor info null lease 7200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 09:32:41.489 - CMD_STOP wlan0/22 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 09:32:41.569 - CMD_CLEAR_LINKADDRESS wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 09:32:41.570 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 09:32:41.570 - CMD_ON_QUIT wlan0/22 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 09:34:06.438 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 09:34:08.188 - OBSERVED interfaceRemoved(wlan0)
  12-18 11:12:20.258 - OBSERVED interfaceAdded(wlan0)
  12-18 11:12:23.636 - CMD_STOP wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 11:12:23.637 - CMD_UPDATE_HTTP_PROXY wlan0/22 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 11:12:23.638 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/22 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 11:12:23.638 - INVOKE setNeighborDiscoveryOffload(true)
  12-18 11:12:23.638 - CMD_START wlan0/25 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 11:12:23.639 - INVOKE setFallbackMulticastFilter(true)
  12-18 11:12:23.654 - CMD_CONFIRM wlan0/25 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 11:12:23.655 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 11:12:23.703 - INVOKE onPreDhcpAction()
  12-18 11:12:23.703 - CMD_PRE_DHCP_ACTION wlan0/25 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 11:12:23.705 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/25 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 11:12:23.807 - INVOKE onPostDhcpAction()
  12-18 11:12:23.807 - INVOKE onNewDhcpResults({IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds})
  12-18 11:12:23.808 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 11:12:23.809 - CMD_POST_DHCP_ACTION wlan0/25 1 0 IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 11:12:23.835 - CMD_CONFIGURE_LINKADDRESS wlan0/25 0 0 192.168.31.239/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-18 11:12:23.840 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.31.239/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [192.168.31.1,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 12:13:20.430 - CMD_STOP wlan0/25 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 12:13:20.782 - CMD_CLEAR_LINKADDRESS wlan0/25 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 12:13:20.782 - CMD_STOP wlan0/25 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 12:13:20.782 - CMD_ON_QUIT wlan0/25 0 0 null [rcvd_in=StoppingState, proc_in=StoppingState]
  12-18 12:13:30.380 - CMD_STOP wlan0/25 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 12:13:31.940 - OBSERVED interfaceRemoved(wlan0)
  12-18 13:04:46.448 - OBSERVED interfaceAdded(wlan0)
  12-18 13:04:50.561 - CMD_STOP wlan0/25 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 13:04:50.562 - CMD_UPDATE_HTTP_PROXY wlan0/25 0 0 null [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 13:04:50.563 - CMD_UPDATE_TCP_BUFFER_SIZES wlan0/25 0 0 524288,1048576,2097152,262144,524288,1048576 [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 13:04:50.563 - INVOKE setNeighborDiscoveryOffload(true)
  12-18 13:04:50.564 - CMD_START wlan0/30 0 0 ProvisioningConfiguration{mEnableIPv4: true, mEnableIPv6: true, mUsingIpReachabilityMonitor: true, mRequestedPreDhcpActionMs: 36000, mStaticIpConfig: null, mApfCapabilities: ApfCapabilities{version: 0, maxSize: 0 format: 1}, mProvisioningTimeoutMs: 36000} [rcvd_in=StoppedState, proc_in=StoppedState]
  12-18 13:04:50.569 - INVOKE setFallbackMulticastFilter(true)
  12-18 13:04:50.585 - CMD_CONFIRM wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 13:04:50.585 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 13:04:50.642 - INVOKE onPreDhcpAction()
  12-18 13:04:50.643 - CMD_PRE_DHCP_ACTION wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 13:04:50.646 - EVENT_PRE_DHCP_ACTION_COMPLETE wlan0/30 0 0 null [rcvd_in=RunningState, proc_in=RunningState]
  12-18 13:04:50.749 - INVOKE onPostDhcpAction()
  12-18 13:04:50.749 - INVOKE onNewDhcpResults({IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds})
  12-18 13:04:50.750 - INVOKE onLinkPropertiesChange({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})
  12-18 13:04:50.750 - CMD_POST_DHCP_ACTION wlan0/30 1 0 IP address 192.168.31.239/24 Gateway 192.168.31.1  DNS servers: [ 192.168.31.1 ] Domains  DHCP server /192.168.31.1 Vendor info miwifi-R3-2.20.26-101 lease 43200 seconds [rcvd_in=RunningState, proc_in=RunningState]
  12-18 13:04:50.770 - CMD_CONFIGURE_LINKADDRESS wlan0/30 0 0 192.168.31.239/24 [rcvd_in=RunningState, proc_in=RunningState]
  12-18 13:04:50.772 - INVOKE onProvisioningSuccess({{InterfaceName: wlan0 LinkAddresses: [fe80::2e57:31ff:fec2:1d2a/64,192.168.31.239/24,]  Routes: [fe80::/64 -> :: wlan0,192.168.31.0/24 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.31.1 wlan0,] DnsAddresses: [192.168.31.1,] Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}})

IpManager.wlan0 connectivity packet log:
  12-18 13:28:54.508 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-18 13:28:54.509 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-18 13:29:25.536 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-18 13:29:25.538 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a
  [F57A00000000AAAA03000000080600010800060400022C5731C21D2AC0A81FEF286C075AF57AC0A81F01]
  12-18 13:29:55.027 - RX 28:6c:07:5a:f5:7a > 2c:57:31:c2:1d:2a arp who-has 192.168.31.239
  [2C5731C21D2A286C075AF57A08060001080006040001286C075AF57AC0A81F01000000000000C0A81FEF]
  12-18 13:29:55.029 - RX aa:aa:03:00:00:00 > f5:7a:00:00:00:00 arp reply 192.168.31.239 2c:57:31:c2:1d:2a



```



## adb shell dumpsys media.camera全字符
```
adb shell dumpsys media.camera       //  查看camera  全信息


Camera module HAL API version: 0x100
Camera module API version: 0x204
Camera module name: QCamera Module
Camera module author: Qualcomm Innovation Center Inc
Number of camera devices: 3
Number of normal camera devices: 3
Active Camera Clients:
[]
Allowed users:
0
Vendor tag definitions:
    Dumping configured vendor tag descriptors: 32 entries
      0x80000000 (privatedata_reprocess) with type 1 (int32) defined in section org.codeaurora.qcamera3.privatedata
      0x80010000 (cds_mode) with type 1 (int32) defined in section org.codeaurora.qcamera3.CDS
      0x80010001 (cds_info) with type 0 (byte) defined in section org.codeaurora.qcamera3.CDS
      0x80020000 (opaque_raw_strides) with type 1 (int32) defined in section org.codeaurora.qcamera3.opaque_raw
      0x80020001 (opaque_raw_format) with type 0 (byte) defined in section org.codeaurora.qcamera3.opaque_raw
      0x80030000 (count) with type 1 (int32) defined in section org.codeaurora.qcamera3.crop
      0x80030001 (data) with type 1 (int32) defined in section org.codeaurora.qcamera3.crop
      0x80030002 (roimap) with type 1 (int32) defined in section org.codeaurora.qcamera3.crop
      0x80040000 (tuning_meta_data_blob) with type 1 (int32) defined in section org.codeaurora.qcamera3.tuning_meta_data
      0x80050000 (enable) with type 0 (byte) defined in section org.codeaurora.qcamera3.temporal_denoise
      0x80050001 (process_type) with type 1 (int32) defined in section org.codeaurora.qcamera3.temporal_denoise
      0x80060000 (use_iso_exp_priority) with type 3 (int64) defined in section org.codeaurora.qcamera3.iso_exp_priority
      0x80060001 (select_priority) with type 1 (int32) defined in section org.codeaurora.qcamera3.iso_exp_priority
      0x80070000 (use_saturation) with type 1 (int32) defined in section org.codeaurora.qcamera3.saturation
      0x80080000 (exposure_metering_mode) with type 1 (int32) defined in section org.codeaurora.qcamera3.exposure_metering
      0x80090000 (use_av_timer) with type 0 (byte) defined in section org.codeaurora.qcamera3.av_timer
      0x800a0000 (dynamic_black_level_pattern) with type 2 (float) defined in section org.codeaurora.qcamera3.sensor_meta_data
      0x800a0001 (is_mono_only) with type 0 (byte) defined in section org.codeaurora.qcamera3.sensor_meta_data
      0x800b0000 (enable) with type 0 (byte) defined in section org.codeaurora.qcamera3.dualcam_link_meta_data
      0x800b0001 (is_main) with type 0 (byte) defined in section org.codeaurora.qcamera3.dualcam_link_meta_data
      0x800b0002 (related_camera_id) with type 1 (int32) defined in section org.codeaurora.qcamera3.dualcam_link_meta_data
      0x800c0000 (dualcam_calib_meta_data_blob) with type 0 (byte) defined in section org.codeaurora.qcamera3.dualcam_calib_meta_data
      0x800d0000 (reprocess_flags) with type 0 (byte) defined in section org.codeaurora.qcamera3.hal_private_data
      0x800d0001 (reprocess_data_blob) with type 0 (byte) defined in section org.codeaurora.qcamera3.hal_private_data
      0x800e0000 (enable) with type 0 (byte) defined in section org.codeaurora.qcamera3.jpeg_encode_crop
      0x800e0001 (rect) with type 1 (int32) defined in section org.codeaurora.qcamera3.jpeg_encode_crop
      0x800e0002 (roi) with type 1 (int32) defined in section org.codeaurora.qcamera3.jpeg_encode_crop
      0x800f0000 (strength) with type 1 (int32) defined in section org.codeaurora.qcamera3.sharpness
      0x800f0001 (range) with type 1 (int32) defined in section org.codeaurora.qcamera3.sharpness
      0x80100000 (is_hdr_scene) with type 0 (byte) defined in section org.codeaurora.qcamera3.stats
      0x80100001 (is_hdr_scene_confidence) with type 2 (float) defined in section org.codeaurora.qcamera3.stats
      0x80110000 (brightness_val) with type 2 (float) defined in section org.codeaurora.qcamera3.brightness

Prior client events (most recent at top):
  12-18 11:34:17 : DISCONNECT device 1 client for package cameraserver (PID 761)
  12-18 09:34:12 : DISCONNECT device 0 client for package com.eg.android.AlipayGphone (PID 30804)
  12-18 09:34:10 : CONNECT device 0 client for package com.eg.android.AlipayGphone (PID 30804)
  12-17 21:44:28 : DISCONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:44:14 : CONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:43:33 : DISCONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:43:20 : CONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:41:47 : DISCONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:41:24 : CONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:41:03 : DISCONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 21:40:50 : CONNECT device 0 client for package com.tencent.mm (PID 21357)
  12-17 20:43:24 : USER_SWITCH previous allowed users:  , current allowed users: 0

Camera 0 information:
  Facing: BACK
  Orientation: 90
  Resource Cost: 100
  Conflicting Devices: NONE
  Device version: 0x100
  Camera1 API shim is using parameters:
        CameraParameters::dump: mMap.size = 192
	ae-bracket-hdr: Off
	ae-bracket-hdr-values: Off,AE-Bracket
	af-bracket: af-bracket-off
	af-bracket-values: af-bracket-off,af-bracket-on
	antibanding: auto
	antibanding-values: off,60hz,50hz,auto
	auto-exposure: frame-average
	auto-exposure-lock: false
	auto-exposure-lock-supported: true
	auto-exposure-values: frame-average,center-weighted,spot-metering,center-weighted,spot-metering-adv,center-weighted-adv
	auto-hdr-supported: true
	auto-whitebalance-lock: false
	auto-whitebalance-lock-supported: true
	avtimer: disable
	blurintensity-step: 1
	brightness-step: 1
	cache-video-buffers: disable
	camera-mode: 0
	cds-mode-values: off,on,auto
	chroma-flash: chroma-flash-off
	chroma-flash-values: chroma-flash-off,chroma-flash-on
	contrast: 5
	contrast-step: 1
	contrast-values: 0,5,10
	denoise: denoise-on
	denoise-values: denoise-off,denoise-on
	dis: disable
	dis-values: enable,disable
	dual-camera-id: 0
	dual-camera-main-camera: false
	dual-camera-mode: off
	effect: none
	effect-values: none,mz_lively,mz_moonlight,mz_black_and_white,mz_blue,mz_pink,mz_film,mz_natural,mz_dawn,mz_sakura,mz_dreamy,mz_bright
	exposure-compensation: 0
	exposure-compensation-step: 0.166667
	face-detection: off
	face-detection-values: off,on
	flash-mode: off
	flash-mode-values: off,auto,on,torch
	flip-mode-values: off,flip-v,flip-h,flip-vh
	focal-length: 3.94
	focus-areas: (0, 0, 0, 0, 0)
	focus-distances: Infinity,Infinity,Infinity
	focus-mode: auto
	focus-mode-values: auto,infinity,fixed,macro,continuous-video,continuous-picture,manual
	hdr-mode: hdr-mode-multiframe
	hdr-mode-values: hdr-mode-sensor,hdr-mode-multiframe
	hdr-need-1x: false
	hdr-need-1x-values: false,true
	hfr-size-values: 1280x720
	histogram: disable
	histogram-values: enable,disable
	horizontal-view-angle: 69.89
	instant-aec-values: 
	instant-capture-values: 
	internal-restart: true
	iso: auto
	iso-values: auto,ISO100,ISO200,ISO400,ISO800,ISO1600,ISO3200
	jpeg-quality: 85
	jpeg-thumbnail-height: 154
	jpeg-thumbnail-quality: 85
	jpeg-thumbnail-size-values: 256x154,240x160,320x320,320x240,256x144,240x144,176x144,240x320,144x176,0x0
	jpeg-thumbnail-width: 256
	lensshade: enable
	lensshade-values: enable,disable
	long-shot: off
	longshot-supported: true
	luma-adaptation: 3
	manual-exposure-modes: off,exp-time-priority,iso-priority,user-setting
	manual-focus-modes: off,scale-mode,diopter-mode
	manual-wb-modes: off,color-temperature,rbgb-gains
	max-blueintensity: 100
	max-brightness: 6
	max-contrast: 10
	max-exposure-compensation: 12
	max-exposure-time: 33113.058750
	max-focus-pos-dac: 1023
	max-focus-pos-diopter: 10
	max-focus-pos-index: 1023
	max-focus-pos-ratio: 100
	max-iso: 1600
	max-num-detected-faces-hw: 10
	max-num-detected-faces-sw: 10
	max-num-focus-areas: 1
	max-num-metering-areas: 10
	max-saturation: 10
	max-sce-factor: 100
	max-sharpness: 36
	max-wb-cct: 8000
	max-wb-gain: 4
	max-zoom: 90
	mce: enable
	mce-values: enable,disable
	metering-areas: (0, 0, 0, 0, 0)
	min-blueintensity: 0
	min-brightness: 0
	min-contrast: 0
	min-exposure-compensation: -12
	min-exposure-time: 0.027224
	min-focus-pos-dac: 0
	min-focus-pos-diopter: 0
	min-focus-pos-index: 0
	min-focus-pos-ratio: 0
	min-iso: 100
	min-saturation: 0
	min-sce-factor: -100
	min-sharpness: 0
	min-wb-cct: 2000
	min-wb-gain: 1
	num-retro-burst-per-shutter: 0
	num-snaps-per-shutter: 1
	opti-zoom: opti-zoom-off
	opti-zoom-values: opti-zoom-off,opti-zoom-on
	picture-format: jpeg
	picture-format-values: jpeg,bayer-mipi-10bggr
	picture-size: 176x144
	picture-size-values: 4032x3024,4000x3000,4032x2272,4000x2256,3860x2140,3840x2160,3200x2400,2624x1968,2592x1944,2592x1458,2560x1440,2304x1728,2016x1512,2048x1536,2000x1504,2000x1126,2016x1136,1920x1080,1600x1200,1440x1080,1280x960,1280x768,1280x720,1024x	preferred-preview-size-for-video: 1920x1080
	preview-flip: off
	preview-format: yuv420sp
	preview-format-values: yuv420sp,yuv420p,nv12-venus
	preview-fps-range: 7000,30000
	preview-fps-range-values: (15000,15000),(20000,20000),(7500,24000),(24000,24000),(7000,27000),(27000,27000),(7000,30000),(30000,30000)
	preview-frame-rate: 30
	preview-frame-rate-values: 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
	preview-size: 640x480
	preview-size-values: 1920x1080,1440x1080,1280x960,1280x720,960x720,800x600,864x480,800x480,768x432,720x480,640x480,480x640,640x360,576x432,480x360,480x320,384x288,352x288,320x240,240x320,240x160,176x144,144x176,160x120
	qc-max-num-requested-faces: 10
	raw-size: 4032x3024
	rdi-mode: disable
	rdi-mode-values: enable,disable
	re-focus: re-focus-off
	re-focus-values: re-focus-off,re-focus-on
	redeye-reduction: disable
	redeye-reduction-values: enable,disable
	saturation: 5
	saturation-step: 1
	saturation-values: 3,5,8
	sce-factor-step: 10
	scene-detect: off
	scene-detect-values: off,on
	scene-mode: auto
	scene-mode-values: auto,asd,landscape,snow,beach,sunset,night,portrait,backlight,sports,steadyphoto,flowers,candlelight,fireworks,party,night-portrait,theatre,action,AR,hdr
	scene-selection: disable
	secure-mode: disable
	secure-mode-values: enable,disable
	see-more: off
	see-more-values: off,on
	selectable-zone-af: auto
	selectable-zone-af-values: auto,spot-metering,center-weighted,frame-average
	sensor-hdr: off
	sensor-hdr-values: off,on
	sharpness: 12
	sharpness-step: 6
	shutter-value-supported: 0,200,500,1000,2000,5000,10000,20000,50000,100000,200000,300000,400000,500000,600000,800000,1000000,2000000,3000000,5000000,8000000,10000000,15000000,20000000
	skinToneEnhancement: 0
	skinToneEnhancement-values: enable,disable
	smooth-zoom-supported: false
	snapshot-burst-num: 0
	snapshot-picture-flip: off
	still-more: still-more-off
	still-more-values: still-more-off,still-more-on
	supported-live-snapshot-sizes: 3860x2140,3840x2160,2592x1944,2048x1536,2048x1080,1920x1080,1600x1200,1440x1080,1280x960,1280x768,1280x720,1024x768,800x600,864x480,800x480,720x480,640x480,640x360,352x288,320x240,176x144,160x120
	tnr-mode-values: off,on
	touch-af-aec: touch-off
	touch-af-aec-values: touch-off,touch-on
	true-portrait-values: true-portrait-off,true-portrait-on
	vertical-view-angle: 51.7
	video-batch-size: 0
	video-cds-mode-values: off,on,auto
	video-flip: off
	video-frame-format: android-opaque
	video-hdr: off
	video-hdr-values: off,on
	video-hfr: off
	video-hfr-values: 120,off
	video-hsr: off
	video-rotation: 0
	video-rotation-values: 0,90,180,270
	video-size: 3860x2140
	video-size-values: 3860x2140,3840x2160,1920x1080,1280x960,1280x720,864x480,800x480,720x480,640x480,480x640,640x360,480x360,480x320,352x288,320x240,240x320,176x144,144x176,160x120
	video-snapshot-supported: true
	video-stabilization-supported: false
	video-tnr-mode-values: off,on
	whitebalance: auto
	whitebalance-values: auto,incandescent,fluorescent,warm-fluorescent,daylight,cloudy-daylight,twilight,shade,manual
	zoom: 0
	zoom-ratios: 100,102,104,107,109,112,114,117,120,123,125,128,131,135,138,141,144,148,151,155,158,162,166,170,174,178,182,186,190,195,200,204,209,214,219,224,229,235,240,246,251,257,263,270,276,282,289,296,303,310,317,324,332,340,348,356,364,373,381,390,	zoom-supported: true
	zsl: off
	zsl-hdr-supported: true
	zsl-values: off,on
  Device 0 is closed, no client instance
Camera 1 information:
  Facing: FRONT
  Orientation: 270
  Resource Cost: 100
  Conflicting Devices: NONE
  Device version: 0x100
  Camera1 API shim is using parameters:
        CameraParameters::dump: mMap.size = 178
	ae-bracket-hdr: Off
	ae-bracket-hdr-values: Off,AE-Bracket
	antibanding: auto
	antibanding-values: off,60hz,50hz,auto
	auto-exposure: frame-average
	auto-exposure-lock: false
	auto-exposure-lock-supported: true
	auto-exposure-values: frame-average,center-weighted,spot-metering,center-weighted,spot-metering-adv,center-weighted-adv
	auto-hdr-supported: true
	auto-whitebalance-lock: false
	auto-whitebalance-lock-supported: true
	avtimer: disable
	blurintensity-step: 1
	brightness-step: 1
	cache-video-buffers: disable
	camera-mode: 0
	cds-mode-values: off,on,auto
	contrast: 5
	contrast-step: 1
	contrast-values: 0,5,10
	denoise: denoise-on
	denoise-values: denoise-off,denoise-on
	dis: disable
	dis-values: enable,disable
	dual-camera-id: 0
	dual-camera-main-camera: false
	dual-camera-mode: off
	effect: none
	effect-values: none,mz_lively,mz_moonlight,mz_black_and_white,mz_blue,mz_pink,mz_film,mz_natural,mz_dawn,mz_sakura,mz_dreamy,mz_bright
	exposure-compensation: 0
	exposure-compensation-step: 0.166667
	face-detection: off
	face-detection-values: off,on
	flip-mode-values: off,flip-v,flip-h,flip-vh
	focal-length: 3.519
	focus-distances: Infinity,Infinity,Infinity
	focus-mode: fixed
	focus-mode-values: fixed
	hdr-mode: hdr-mode-multiframe
	hdr-mode-values: hdr-mode-multiframe
	hdr-need-1x: false
	hdr-need-1x-values: false,true
	hfr-size-values: 
	histogram: disable
	histogram-values: enable,disable
	horizontal-view-angle: 64.9
	instant-aec-values: 
	instant-capture-values: 
	internal-restart: true
	iso: auto
	iso-values: auto,ISO100,ISO200,ISO400,ISO800,ISO1600,ISO3200
	jpeg-quality: 85
	jpeg-thumbnail-height: 154
	jpeg-thumbnail-quality: 85
	jpeg-thumbnail-size-values: 256x154,240x160,320x320,320x240,256x144,240x144,176x144,240x320,144x176,0x0
	jpeg-thumbnail-width: 256
	lensshade: enable
	lensshade-values: enable,disable
	long-shot: off
	longshot-supported: true
	luma-adaptation: 3
	manual-exposure-modes: off,exp-time-priority,iso-priority,user-setting
	manual-focus-modes: off
	manual-wb-modes: off,color-temperature,rbgb-gains
	max-blueintensity: 100
	max-brightness: 6
	max-contrast: 10
	max-exposure-compensation: 12
	max-exposure-time: 599.506523
	max-focus-pos-dac: 1023
	max-focus-pos-diopter: 0
	max-focus-pos-index: 1023
	max-focus-pos-ratio: 100
	max-iso: 1600
	max-num-detected-faces-hw: 10
	max-num-detected-faces-sw: 10
	max-num-focus-areas: 0
	max-num-metering-areas: 10
	max-saturation: 10
	max-sce-factor: 100
	max-sharpness: 36
	max-wb-cct: 8000
	max-wb-gain: 4
	max-zoom: 90
	mce: enable
	mce-values: enable,disable
	metering-areas: (0, 0, 0, 0, 0)
	min-blueintensity: 0
	min-brightness: 0
	min-contrast: 0
	min-exposure-compensation: -12
	min-exposure-time: 0.018295
	min-focus-pos-dac: 0
	min-focus-pos-diopter: 0
	min-focus-pos-index: 0
	min-focus-pos-ratio: 0
	min-iso: 100
	min-saturation: 0
	min-sce-factor: -100
	min-sharpness: 0
	min-wb-cct: 2000
	min-wb-gain: 1
	num-retro-burst-per-shutter: 0
	num-snaps-per-shutter: 1
	opti-zoom: opti-zoom-off
	opti-zoom-values: opti-zoom-off,opti-zoom-on
	picture-format: jpeg
	picture-format-values: jpeg,bayer-mipi-10gbrg
	picture-size: 176x144
	picture-size-values: 4608x3456,4160x3120,4032x3024,4000x3000,4032x2272,4000x2256,3860x2140,3840x2160,3200x2400,2624x1968,2592x1944,2592x1458,2560x1440,2304x1728,2016x1512,2048x1536,2000x1504,2000x1126,2016x1136,1920x1080,1600x1200,1440x1080,1280x960,128	preferred-preview-size-for-video: 1920x1080
	preview-flip: off
	preview-format: yuv420sp
	preview-format-values: yuv420sp,yuv420p,nv12-venus
	preview-fps-range: 7000,30000
	preview-fps-range-values: (15000,15000),(7500,20000),(20000,20000),(7500,24000),(24000,24000),(7000,30000),(30000,30000)
	preview-frame-rate: 30
	preview-frame-rate-values: 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
	preview-size: 640x480
	preview-size-values: 1920x1080,1440x1080,1280x960,1280x720,960x720,800x600,864x480,800x480,768x432,720x480,640x480,480x640,640x360,576x432,480x360,480x320,384x288,352x288,320x240,240x320,240x160,176x144,144x176,160x120
	qc-max-num-requested-faces: 10
	raw-size: 2304x1728
	rdi-mode: disable
	rdi-mode-values: enable,disable
	redeye-reduction: disable
	redeye-reduction-values: enable,disable
	saturation: 5
	saturation-step: 1
	saturation-values: 3,5,8
	sce-factor-step: 10
	scene-detect: off
	scene-detect-values: off,on
	scene-mode: auto
	scene-mode-values: auto,asd,landscape,snow,beach,sunset,night,portrait,backlight,sports,steadyphoto,flowers,candlelight,fireworks,party,night-portrait,theatre,action,AR,hdr
	scene-selection: disable
	secure-mode: disable
	secure-mode-values: enable,disable
	see-more: off
	see-more-values: off,on
	selectable-zone-af: auto
	selectable-zone-af-values: auto,spot-metering,center-weighted,frame-average
	sharpness: 12
	sharpness-step: 6
	skinToneEnhancement: 0
	skinToneEnhancement-values: enable,disable
	smooth-zoom-supported: false
	snapshot-burst-num: 0
	snapshot-picture-flip: off
	still-more: still-more-off
	still-more-values: still-more-off,still-more-on
	supported-live-snapshot-sizes: 4128x3096,4128x2322,4000x3000,4096x2160,3860x2140,3840x2160,3264x2448,3200x2400,2592x1944,2048x1536,2048x1080,1920x1080,1600x1200,1440x1080,1280x960,1280x768,1280x720,1024x768,800x600,864x480,800x480,720x480,640x480,640x36	tnr-mode-values: off,on
	touch-af-aec: touch-off
	touch-af-aec-values: touch-off,touch-on
	true-portrait-values: true-portrait-off,true-portrait-on
	vertical-view-angle: 51.6
	video-batch-size: 0
	video-cds-mode-values: off,on,auto
	video-flip: off
	video-frame-format: android-opaque
	video-hfr: off
	video-hfr-values: off
	video-hsr: off
	video-rotation: 0
	video-rotation-values: 0,90,180,270
	video-size: 4096x2160
	video-size-values: 4096x2160,3860x2140,3840x2160,1920x1080,1280x960,1280x720,864x480,800x480,720x480,640x480,480x640,640x360,480x360,480x320,352x288,320x240,240x320,176x144,144x176,160x120
	video-snapshot-supported: true
	video-stabilization-supported: false
	video-tnr-mode-values: off,on
	whitebalance: auto
	whitebalance-values: auto,incandescent,fluorescent,warm-fluorescent,daylight,cloudy-daylight,twilight,shade,manual
	zoom: 0
	zoom-ratios: 100,102,104,107,109,112,114,117,120,123,125,128,131,135,138,141,144,148,151,155,158,162,166,170,174,178,182,186,190,195,200,204,209,214,219,224,229,235,240,246,251,257,263,270,276,282,289,296,303,310,317,324,332,340,348,356,364,373,381,390,	zoom-supported: true
	zsl: off
	zsl-hdr-supported: true
	zsl-values: off,on
  Device 1 is closed, no client instance
Camera 2 information:
  Facing: BACK
  Orientation: 90
  Resource Cost: 100
  Conflicting Devices: NONE
  Device version: 0x100
  Device 2 is closed, no client instance

No active camera clients yet.

Camera traces (0):
  No camera traces collected.

```

##  adb shell dumpsys media.audio_flinger全字符
```
Library livemusicMz
  Effect Live Music / Meizu Technology Co., Ltd
    UUID: 0bbc89fe-52dc-4c40-8211-cae4da538b50
    TYPE: 38a6a248-94be-4007-bbe1-337d7dc49e6d
    apiVersion: 00020000
    flags: 00000048
Library virtualizerMz
  Virtualizer / Meizu Technology Co., Ltd
    UUID: 0e9779c9-4e8f-494d-b2b1-b4ad4e37c54c
    TYPE: 725d7fa7-9219-4f71-8fa7-240a6d489387
    apiVersion: 00020000
    flags: 00000210
Library equalizerMz
  Equalizer / Meizu Technology Co., Ltd
    UUID: 9626da93-9c71-4bb2-8e23-9fc707fb9703
    TYPE: fda57ecb-2b6f-40ec-a2b8-2b0e8c630647
    apiVersion: 00020000
    flags: 00000008
Library bassboostMz
  Bass Boost / Meizu Technology Co., Ltd
    UUID: 850b6319-bf66-4f93-bec0-dc6964367786
    TYPE: 47164cd2-526a-49f5-8f90-0ba88c408cd7
    apiVersion: 00020000
    flags: 00000008
Library audio_pre_processing
  Noise Suppression / Qualcomm Fluence
    UUID: 1d97bb0b-9e2f-4403-9ae3-58c2554306f8
    TYPE: 58b4b260-8e06-11e0-aa8e-0002a5d5c51b
    apiVersion: 00020000
    flags: 00000203
  Acoustic Echo Canceler / Qualcomm Fluence
    UUID: 0f8d0d2a-59e5-45fe-b6e4-248c8a799109
    TYPE: 7b491460-8d4d-11e0-bd61-0002a5d5c51b
    apiVersion: 00020000
    flags: 00000203
Library offload_bundle
  (no effects)
Library proxy
  Visualizer / The Android Open Source Project
    UUID: 1d0a1a53-7d5d-48f2-8e71-27fbd10d842c
    TYPE: e46b26a0-dddd-11db-8afd-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400008
  Qualcomm Technologies Insert Preset Reverb / Qualcomm Technologies, Inc.
    UUID: f3e178d2-ebcb-408e-8357-0002a5d5c51b
    TYPE: 47382d60-ddd8-11db-bf3a-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400088
  Qualcomm Technologies Auxiliary Preset Reverb / Qualcomm Technologies, Inc.
    UUID: 1b78f587-6d1c-422e-8b84-0002a5d5c51b
    TYPE: 47382d60-ddd8-11db-bf3a-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400001
  Qualcomm Technologies Insert Environmental Reverb / Qualcomm Technologies, Inc.
    UUID: b707403a-a1c1-4291-9573-0002a5d5c51b
    TYPE: c2e5d5f0-94bd-4763-9cac-4e234d06839e
    apiVersion: 00020000
    flags: 00400088
  Qualcomm Technologies Auxiliary Environmental Reverb / Qualcomm Technologies, Inc.
    UUID: 48404ac9-d202-4ccc-bf84-0002a5d5c51b
    TYPE: c2e5d5f0-94bd-4763-9cac-4e234d06839e
    apiVersion: 00020000
    flags: 00400001
  Equalizer / NXP Software Ltd.
    UUID: c8e70ecd-48ca-456e-8a4f-0002a5d5c51b
    TYPE: 0bed4300-ddd6-11db-8f34-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400048
  Qualcomm Technologies Virtualizer / Qualcomm Technologies, Inc.
    UUID: d3467faa-acc7-4d34-acaf-0002a5d5c51b
    TYPE: 37cc2c00-dddd-11db-8577-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400290
  Qualcomm Technologies Bass Boost / Qualcomm Technologies, Inc.
    UUID: 14804144-a5ee-4d24-aa88-0002a5d5c51b
    TYPE: 0634f220-ddd4-11db-a0fc-0002a5d5c51b
    apiVersion: 00020000
    flags: 00400290
Library loudness_enhancer
  Loudness Enhancer / The Android Open Source Project
    UUID: fa415329-2034-4bea-b5dc-5b381c8d1e2c
    TYPE: fe3199be-aed0-413f-87bb-11260eb63cf1
    apiVersion: 00020000
    flags: 00000008
Library downmix
  Multichannel Downmix To Stereo / The Android Open Source Project
    UUID: 93f04452-e4fe-41cc-91f9-e475b6d1d69f
    TYPE: 381e49cc-a858-4aa2-87f6-e8388e7601b2
    apiVersion: 00020000
    flags: 00000008
Library visualizer_hw
  (no effects)
Library visualizer_sw
  (no effects)
Library qcreverb
  (no effects)
Library qcvirt
  (no effects)
Library qcbassboost
  (no effects)
Library reverb
  (no effects)
Library bundle
  Volume / NXP Software Ltd.
    UUID: 119341a0-8469-11df-81f9-0002a5d5c51b
    TYPE: 09e8ede0-ddde-11db-b4f6-0002a5d5c51b
    apiVersion: 00020000
    flags: 00000050
Clients:
  pid: 2355
Notification Clients:
  pid: 760
  pid: 761
  pid: 769
  pid: 772
  pid: 1732
  pid: 2355
  pid: 2729
  pid: 3578
  pid: 7478
  pid: 9676
  pid: 13744
Global session refs:
  session   pid count
        9  2355     1
      425  9676     1
      889 13744     1
Hardware status: 0
Standby Time mSec: 3000

KARAOKE MODE:
  IsKaraokeEnabled: false
  IsInKaraoke: false
  mKaraokeBgVolume: 1.000000
  mKaraokeVoiceVolume: 1.000000

Output thread 0xf3283100 type 0 (MIXER):
  Thread name: AudioOut_D
  I/O handle: 13
  TID: 1171
  Standby: yes
  Sample rate: 48000 Hz
  HAL frame count: 480
  HAL format: 0x1 (pcm16)
  HAL buffer size: 1920 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x5 (pcmfloat)
  Processing frame size: 8 bytes
  Pending config events: none
  Output device: 0 (NONE)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 960
  Last write occurred (msecs): 19606192
  Total writes: 788
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf47fd000
  Mixer buffer: 0xf47db000
  Effect buffer: 0xf47ff000
  Fast track availMask=0xfc
  Standby delay ns=3000000000
  AudioStreamOut: 0xf47a3188 flags 0x6 (PRIMARY|FAST)
  Frames written: 756480
  Suspended frames: 0
  PipeSink frames written: 756480
  Hal stream dump:
  Thread throttle time (msecs): 0
  AudioMixer tracks: 0x00000003
  Master mono: off
  FastMixer command=COLD_IDLE writeSequence=3192 framesWritten=766080
            numTracks=1 writeErrors=0 underruns=0 overruns=0
            sampleRate=48000 frameCount=480 measuredWarmup=33.4 ms, warmupCycles=5
            mixPeriod=10.00 ms
  Simple moving statistics over last 3.3 seconds:
    wall clock time in ms per mix cycle:
      mean=10.00 min=9.55 max=10.49 stddev=0.09
    raw CPU load in us per mix cycle:
      mean=171 min=0 max=552 stddev=39
  Fast tracks: sMaxFastTracks=8 activeMask=0x1
  Index Active Full Partial Empty  Recent Ready    Written
      0    yes  549       0     0    full  1920     754560
      1     no    0       0     0    full     0          0
      2     no    0       0     0    full     0          0
      3     no    0       0     0    full     0          0
      4     no    0       0     0    full     0          0
      5     no    0       0     0    full     0          0
      6     no    0       0     0    full     0          0
      7     no    0       0     0    full     0          0
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 1
  Stream volumes in dB: 0:-24, 1:-inf, 2:-inf, 3:-23, 4:-10, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:0, 10:-23, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  2 Tracks of which 0 are active
    Name Active Client Type      Fmt Chn mask Session fCount S F SRate  L dB  R dB    Server Main buf  Aux Buf Flags UndFrmCnt
       1     no    760   12 00000005 00000003       0   3536 I 0 44100     0     0  00000000 0xf47fd000 0x0 0x000 0x00000000         0 
    F  1     no   2355    0 00000001 00000001       9    960 I 0 48000   -10   -10  00000000 0xf47fd000 0x0 0x000 0x00000004         0 
  0 Effect Chains

Output thread 0xf2e831c0 type 0 (MIXER):
  Thread name: AudioOut_15
  I/O handle: 21
  TID: 1173
  Standby: yes
  Sample rate: 48000 Hz
  HAL frame count: 480
  HAL format: 0x1 (pcm16)
  HAL buffer size: 1920 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x5 (pcmfloat)
  Processing frame size: 8 bytes
  Pending config events: none
  Output device: 0 (NONE)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 960
  Last write occurred (msecs): 32931330
  Total writes: 1645
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf3a5c000
  Mixer buffer: 0xf3a5a000
  Effect buffer: 0xf3a6a000
  Fast track availMask=0xfe
  Standby delay ns=3000000000
  AudioStreamOut: 0xf47a31c0 flags 0x104 (FAST|RAW)
  Frames written: 1579200
  Suspended frames: 0
  PipeSink frames written: 1579200
  Hal stream dump:
  Thread throttle time (msecs): 0
  AudioMixer tracks: 0x00000000
  Master mono: off
  FastMixer command=COLD_IDLE writeSequence=6584 framesWritten=1580160
            numTracks=1 writeErrors=0 underruns=0 overruns=0
            sampleRate=48000 frameCount=480 measuredWarmup=23.6 ms, warmupCycles=4
            mixPeriod=10.00 ms
  Simple moving statistics over last 32.9 seconds:
    wall clock time in ms per mix cycle:
      mean=10.00 min=8.69 max=11.11 stddev=0.16
    raw CPU load in us per mix cycle:
      mean=360 min=0 max=1198 stddev=155
  Distribution of mix cycle times in ms for the tails (> ~3 stddev outliers):
    left tail: mean=8.88 min=8.69 max=9.09 stddev=0.20
    right tail: mean=10.98 min=10.91 max=11.11 stddev=0.11
  Fast tracks: sMaxFastTracks=8 activeMask=0x1
  Index Active Full Partial Empty  Recent Ready    Written
      0    yes  216       0     0    full  1440    1577760
      1     no  939       0     2   empty     0    1433280
      2     no    0       0     0    full     0          0
      3     no    0       0     0    full     0          0
      4     no    0       0     0    full     0          0
      5     no    0       0     0    full     0          0
      6     no    0       0     0    full     0          0
      7     no    0       0     0    full     0          0
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 1
  Stream volumes in dB: 0:-24, 1:-inf, 2:-inf, 3:-23, 4:-10, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:0, 10:-23, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  0 Tracks
  0 Effect Chains

Output thread 0xf2b83640 type 0 (MIXER):
  Thread name: AudioOut_1D
  I/O handle: 29
  TID: 1178
  Standby: yes
  Sample rate: 48000 Hz
  HAL frame count: 1920
  HAL format: 0x1 (pcm16)
  HAL buffer size: 7680 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x1 (pcm16)
  Processing frame size: 4 bytes
  Pending config events: none
  Output device: 0 (NONE)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 1920
  Last write occurred (msecs): 9876576
  Total writes: 48263
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf3a5e000
  Mixer buffer: 0xf3afb000
  Effect buffer: 0xf3a60000
  Fast track availMask=0xfe
  Standby delay ns=3000000000
  AudioStreamOut: 0xf47a32d8 flags 0x8 (DEEP_BUFFER)
  Frames written: 92664960
  Suspended frames: 0
  Hal stream dump:
  Thread throttle time (msecs): 906
  AudioMixer tracks: 0x00000000
  Master mono: off
  FastMixer not initialized
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 0
  Stream volumes in dB: 0:-inf, 1:-inf, 2:-inf, 3:-37, 4:-19, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:-96, 10:-37, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  0 Tracks
  0 Effect Chains

Output thread 0xf2a03b80 type 0 (MIXER):
  Thread name: AudioOut_25
  I/O handle: 37
  TID: 1184
  Standby: yes
  Sample rate: 48000 Hz
  HAL frame count: 768
  HAL format: 0x1 (pcm16)
  HAL buffer size: 3072 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x5 (pcmfloat)
  Processing frame size: 8 bytes
  Pending config events: none
  Output device: 0 (NONE)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 1152
  Last write occurred (msecs): 33622460
  Total writes: 0
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf3a7c800
  Mixer buffer: 0xf3a7a000
  Effect buffer: 0xf47f9400
  Fast track availMask=0xfe
  Standby delay ns=3000000000
  AudioStreamOut: 0xf47a3310 flags 0 (NONE)
  Frames written: 0
  Suspended frames: 0
  PipeSink frames written: 0
  Hal stream dump:
  Thread throttle time (msecs): 0
  AudioMixer tracks: 0x00000000
  Master mono: off
  FastMixer command=COLD_IDLE writeSequence=0 framesWritten=0
            numTracks=0 writeErrors=0 underruns=0 overruns=0
            sampleRate=0 frameCount=0 measuredWarmup=0 ms, warmupCycles=0
            mixPeriod=nan ms
  No FastMixer statistics available currently
  Fast tracks: sMaxFastTracks=8 activeMask=0
  Index Active Full Partial Empty  Recent Ready    Written
      0     no    0       0     0    full     0          0
      1     no    0       0     0    full     0          0
      2     no    0       0     0    full     0          0
      3     no    0       0     0    full     0          0
      4     no    0       0     0    full     0          0
      5     no    0       0     0    full     0          0
      6     no    0       0     0    full     0          0
      7     no    0       0     0    full     0          0
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 1
  Stream volumes in dB: 0:-24, 1:-inf, 2:-inf, 3:-23, 4:-10, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:0, 10:-23, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  0 Tracks
  0 Effect Chains

Output thread 0xf0f83200 type 0 (MIXER):
  Thread name: AudioOut_125
  I/O handle: 293
  TID: 29193
  Standby: yes
  Sample rate: 44100 Hz
  HAL frame count: 896
  HAL format: 0x4 (pcm8.24)
  HAL buffer size: 7168 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x4 (pcm8.24)
  Processing frame size: 8 bytes
  Pending config events: none
  Output device: 0x80 (BLUETOOTH_A2DP)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 896
  Last write occurred (msecs): 7386074
  Total writes: 89853
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf2e5cc00
  Mixer buffer: 0xf1554000
  Effect buffer: 0xf1674000
  Fast track availMask=0xfe
  Standby delay ns=3000000000
  AudioStreamOut: 0xf3303380 flags 0 (NONE)
  Frames written: 80508288
  Suspended frames: 0
  Hal stream dump:
  Thread throttle time (msecs): 229044
  AudioMixer tracks: 0x00000001
  Master mono: off
  FastMixer not initialized
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 0
  Stream volumes in dB: 0:-21, 1:-inf, 2:-inf, 3:-5.9, 4:-12, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:-96, 10:0, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  1 Tracks of which 0 are active
    Name Active Client Type      Fmt Chn mask Session fCount S F SRate  L dB  R dB    Server Main buf  Aux Buf Flags UndFrmCnt
       0     no    760   12 00000005 00000003       0   3584 I 0 44100     0     0  00000000 0xf2e5cc00 0x0 0x000 0x00000000         0 
  0 Effect Chains

Output thread 0xf0f03500 type 2 (DUPLICATING):
  Thread name: AudioOut_12D
  I/O handle: 301
  TID: 29194
  Standby: yes
  Sample rate: 44100 Hz
  HAL frame count: 896
  HAL format: 0x4 (pcm8.24)
  HAL buffer size: 7168 bytes
  Channel count: 2
  Channel mask: 0x00000003 (front-left, front-right)
  Processing format: 0x5 (pcmfloat)
  Processing frame size: 8 bytes
  Pending config events: none
  Output device: 0x80 (BLUETOOTH_A2DP)
  Input device: 0 (NONE)
  Audio source: 0 (default)
  Normal frame count: 896
  Last write occurred (msecs): 33622462
  Total writes: 0
  Delayed writes: 0
  Blocked in write: no
  Suspend count: 0
  Sink buffer : 0xf26da000
  Mixer buffer: 0xf1fb3c00
  Effect buffer: 0xf1674e00
  Fast track availMask=0xfe
  Standby delay ns=3000000000
  AudioStreamOut: 0xf3303380 flags 0 (NONE)
  Frames written: 0
  Suspended frames: 0
  Hal stream dump:
  Thread throttle time (msecs): 0
  AudioMixer tracks: 0x00000000
  Master mono: off
  FastMixer not initialized
  mMasterVolume: 0(dB), mMasterMute: false, mMuteMode: 0
  Stream volumes in dB: 0:-21, 1:-inf, 2:-inf, 3:0, 4:-29, 5:-inf, 6:0, 7:-inf, 8:-inf, 9:-96, 10:0, 11:0, 12:0
  Normal mixer raw underrun counters: partial=0 empty=0
  0 Tracks
  0 Effect Chains

USB audio module:
  No output streams.

  No input streams.

Reroute submix audio module:
 route[0] rate in=0 out=0, addr=[]
 route[1] rate in=0 out=0, addr=[]
 route[2] rate in=0 out=0, addr=[]
 route[3] rate in=0 out=0, addr=[]
 route[4] rate in=0 out=0, addr=[]
 route[5] rate in=0 out=0, addr=[]
 route[6] rate in=0 out=0, addr=[]
 route[7] rate in=0 out=0, addr=[]
 route[8] rate in=0 out=0, addr=[]
 route[9] rate in=48000 out=48000, addr=[]



```