# 代码

## SignalClusterView.java
```
/frameworks/base/packages/SystemUI/src/com/android/systemui/statusbar/SignalClusterView.java



    // Run after each indicator change.
 private void apply() {}


    @Override
    public void setWifiIndicators(boolean enabled, IconState statusIcon, IconState qsIcon,
            boolean activityIn, boolean activityOut, String description, boolean isTransient,
            String secondaryLabel) {
        mWifiVisible = statusIcon.visible && !mBlockWifi;
        mWifiStrengthId = statusIcon.icon;
        mWifiDescription = statusIcon.contentDescription;
        mWifiIn = activityIn && mActivityEnabled && mWifiVisible;
        mWifiOut = activityOut && mActivityEnabled && mWifiVisible;

        apply();
    }

```




# 图标
## settings

```
settings/res/drawable地址:  
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/res/drawable/

```


### ic_appwidget_settings_wifi_off_holo.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_appwidget_settings_wifi_off_holo_x.jpg"  />


### ic_appwidget_settings_wifi_on_holo.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_appwidget_settings_wifi_on_holo_x.jpg"  />

### ic_auto_wifi.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_auto_wifi_x.jpg"  />

```
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="24dp"
        android:height="24dp"
        android:viewportWidth="24.0"
        android:viewportHeight="24.0"
        android:tint="?android:attr/colorControlNormal">
    <path
        android:pathData="M18 10l3 0 -10 -9 -10 9 3 0 0 8 7.1 0c0 -0.3 -0.1 -0.7 -0.1 -1 0 -3.9 3.1 -7 7 -7z"
        android:fillColor="#FFFFFFFF" />
    <path
        android:pathData="M18 14l0 1.6 2.1 -2.1 -2.1 -2.2 0 1.6c-2.3 0 -4.2 1.9 -4.2 4.3 0 0.8 0.2 1.6 0.7 2.3l0.8 -0.8C15.1 18.3 14.9 17.8 14.9 17.2 14.8 15.4 16.2 14 18 14"
        android:strokeWidth="0.5"
        android:fillColor="#FFFFFFFF"
        android:strokeMiterLimit="10" />
    <path
        android:pathData="M20.8 15.7c0.2 0.4 0.4 0.9 0.4 1.5 0 1.8 -1.4 3.2 -3.2 3.2l0 -1.6 -2.1 2.1 2.1 2.1 0 -1.6c2.3 0 4.2 -1.9 4.2 -4.2 0 -0.8 -0.2 -1.6 -0.7 -2.3l-0.7 0.8z"
        android:strokeWidth="0.5"
        android:fillColor="#FFFFFFFF"
        android:strokeMiterLimit="10" />
</vector>

```


### ic_friction_lock_closed.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_friction_lock_closed_x.jpg"  />
```

<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:viewportWidth="48"
        android:viewportHeight="48"
        android:width="18dp"
        android:height="18dp">
    <path
        android:pathData="M36 16l-2 0 0 -4C34 6.48 29.52 2 24 2 18.48 2 14 6.48 14 12l0 4 -2 0c-2.21 0 -4 1.79 -4 4l0 20c0 2.21 1.79 4 4 4l24 0c2.21 0 4 -1.79 4 -4l0 -20c0 -2.21 -1.79 -4 -4 -4zM24 34c-2.21 0 -4 -1.79 -4 -4 0 -2.21 1.79 -4 4 -4 2.21 0 4 1.79 4 4 0 2.21 -1.79 4 -4 4zm6.2 -18l-12.4 0 0 -4c0 -3.42 2.78 -6.2 6.2 -6.2 3.42 0 6.2 2.78 6.2 6.2l0 4z"
        android:fillColor="?android:attr/colorForeground"
        android:fillAlpha="0.54" />
</vector>


```

### ic_friction_money.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_friction_money_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:viewportWidth="18"
        android:viewportHeight="18"
        android:width="18dp"
        android:height="18dp">

    <path android:fillColor="?android:attr/colorForeground"
          android:fillAlpha="0.54"
          android:pathData="M9.56 8.1c-1.6-.51-2.66-.71-2.66-1.88 0-.83 .72 -1.62 2.1-1.62 1.59 0 2.1 .88
          2.1 1.94H13c0-1.79-1.17-3.09-3-3.44V1H8v2.11c-1.58 .32 -3 1.37-3 3.12 0 2.25
          1.78 2.8 4 3.52 1.88 .61 2.25 1.04 2.25 2.09 0 .9-.67 1.56-2.25 1.56-1.2
          0-2.25-.84-2.25-2.06h-2c0 1.88 1.38 3.2 3.25 3.56V17h2v-2.07c2.04-.29 3.2-1.49
          3.2-3.1 0-1.87-.94-2.87-3.64-3.73z" />
    <path android:pathData="M0 0h18v18H0z" />
</vector>


```

### ic_open_wifi_autoconnect.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_open_wifi_autoconnect_x.jpg"  />
```
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="24dp"
        android:height="24dp"
        android:viewportWidth="24.0"
        android:viewportHeight="24.0"
        android:tint="?android:attr/colorControlNormal">
    <path
        android:pathData="M12.7 10C11.9 7.7 9.7 6 7 6 3.7 6 1 8.7 1 12c0 3.3 2.7 6 6 6 2.6 0 4.8 -1.7 5.7 -4l4.3 0 0 4 4 0 0 -4 2 0 0 -4 -10.3 0zM7 16C4.8 16 3 14.2 3 12 3 9.8 4.8 8 7 8 8.1 8 9 8.4 9.6 9.1L8.5 10.2C8.3 9.9 7.8 9.5 7 9.5 5.6 9.5 4.6 10.6 4.6 12c0 1.4 1.1 2.5 2.4 2.5 1.6 0 2.1 -1.1 2.2 -1.7l-2.2 0 0 -1.4 3.8 0c0 0.2 0.1 0.4 0.1 0.6 -0.1 2.3 -1.7 4 -3.9 4z"
        android:fillColor="#FFFFFFFF"/>
</vector>



```
### ic_open_wifi_notifications.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_open_wifi_notifications_x.jpg"  />
```

<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="24.0dp"
        android:height="24.0dp"
        android:viewportWidth="24.0"
        android:viewportHeight="24.0"
        android:tint="?android:attr/colorControlNormal">
    <path
        android:pathData="M8 22.5c0.8 0 1.5 -0.7 1.5 -1.5l-3 0c0 0.8 0.7 1.5 1.5 1.5z"
        android:fillColor="#FFFFFFFF" />
    <path
        android:pathData="M12.5 17.8l0 -3.6C12.5 12 11.3 10.1 9.1 9.6l0 -0.5C9.1 8.5 8.6 8 8 8 7.4 8 6.9 8.5 6.9 9.1l0 0.5C4.8 10.1 3.5 12 3.5 14.2l0 3.6 -1.5 1.5 0 0.7 12 0 0 -0.7 -1.5 -1.5z"
        android:fillColor="#FFFFFFFF" />
    <path
        android:pathData="M10 4.6l1.1 1.1c2.8 -2.8 7.4 -2.8 10.2 0L22.4 4.6C19 1.1 13.5 1.1 10 4.6"
        android:fillColor="#FFFFFFFF" />
    <path
        android:pathData="M14.5 9.1L16.2 10.8 18 9.1C17 8.2 15.5 8.2 14.5 9.1"
        android:fillColor="#FFFFFFFF" />
    <path
        android:pathData="M12.3 6.9L13.4 8C15 6.4 17.5 6.4 19.1 8L20.2 6.9C18 4.7 14.5 4.7 12.3 6.9"
        android:fillColor="#FFFFFFFF" />
</vector>


```
### ic_wifi_lock_signal_1_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_lock_signal_1_dark_x.jpg"  />

### ic_wifi_lock_signal_2_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_lock_signal_2_dark_x.jpg"  />

### ic_wifi_lock_signal_3_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_lock_signal_3_dark_x.jpg"  />


### ic_wifi_lock_signal_4_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_lock_signal_4_dark_x.jpg"  />


### ic_wifi_signal_0.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_0_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
  android:width="26dp"
  android:height="24dp"
  android:viewportWidth="26"
  android:viewportHeight="24">
  <path
    android:fillAlpha="0.3"
    android:fillColor="#FF4081"
    android:pathData="M13.0,22.0L25.6,6.5C25.1,6.1 20.3,2.1 13.0,2.1S0.9,6.1 0.4,6.5L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
</vector>

```

### ic_wifi_signal_1.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_1_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>

<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="26dp"
    android:height="24dp"
    android:viewportWidth="26"
    android:viewportHeight="24">
    <path
        android:fillAlpha="0.3"
        android:fillColor="#FF4081"
        android:pathData="M13.1,22.0L25.6,6.5C25.1,6.1 20.3,2.1 13.0,2.1S0.9,6.1 0.5,6.5L13.1,22.0L13.1,22.0L13.1,22.0L13.1,22.0L13.1,22.0z"/>
    <path
        android:fillColor="#FF4081"
        android:pathData="M13.1,22.0l5.5,-6.8c-0.2,-0.2 -2.3,-1.9 -5.5,-1.9s-5.3,1.8 -5.5,1.9L13.1,22.0L13.1,22.0L13.1,22.0L13.1,22.0L13.1,22.0z"/>
</vector>


```
### ic_wifi_signal_1_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_1_dark_x.jpg"  />


### ic_wifi_signal_2.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_2_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>

<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="26dp"
    android:height="24dp"
    android:viewportWidth="26"
    android:viewportHeight="24">
    <path
        android:fillAlpha="0.3"
        android:fillColor=""
        android:pathData="M13.0,22.0L25.6,6.5C25.1,6.1 20.3,2.1 13.0,2.1S0.9,6.1 0.4,6.5L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
    <path
        android:fillColor="#FF4081"
        android:pathData="M13.0,22.0l7.6,-9.4C20.3,12.4 17.4,10.0 13.0,10.0s-7.3,2.4 -7.6,2.7L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
</vector>


```

### ic_wifi_signal_2_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_2_dark_x.jpg"  />


### ic_wifi_signal_3.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_3_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="26dp"
    android:height="24dp"
    android:viewportWidth="26"
    android:viewportHeight="24">
    <path
        android:fillAlpha="0.3"
        android:fillColor="#FF4081"
        android:pathData="M13.0,22.0L25.6,6.5C25.1,6.1 20.3,2.1 13.0,2.1S0.9,6.1 0.4,6.5L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
    <path
        android:fillColor="#FF4081"
        android:pathData="M13.0,22.0l9.2,-11.4c-0.4,-0.3 -3.9,-3.2 -9.2,-3.2s-8.9,3.0 -9.2,3.2L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
</vector>


```

### ic_wifi_signal_3_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_3_dark_x.jpg"  />


### ic_wifi_signal_4.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_4_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="26dp"
    android:height="24dp"
    android:viewportWidth="26"
    android:viewportHeight="24">
    <path
        android:fillColor="#FF4081"
        android:pathData="M13.0,22.0L25.6,6.5C25.1,6.1 20.3,2.1 13.0,2.1S0.9,6.1 0.4,6.5L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0L13.0,22.0z"/>
</vector>


```

### ic_wifi_signal_4_dark.png
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_4_dark_x.jpg"  />
```


```

### ic_wifi_signal_lock.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_signal_lock_x.jpg"  />
```

<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="72"
    android:viewportHeight="72">
    <group
        android:translateX="52.0"
        android:translateY="42.0">
        <path
            android:fillColor="#FF4081"
            android:pathData="M18.0,8.0l-1.0,0.0L17.0,6.0c0.0,-2.76 -2.24,-5.0 -5.0,-5.0S7.0,3.24 7.0,6.0l0.0,2.0L6.0,8.0c-1.1,0.0 -2.0,0.9 -2.0,2.0l0.0,10.0c0.0,1.0 0.9,2.0 2.0,2.0l12.0,0.0c1.1,0.0 2.0,-0.9 2.0,-2.0L20.0,10.0c0.0,-1.1 -0.9,-2.0 -2.0,-2.0zm-6.0,9.0c-1.1,0.0 -2.0,-0.9 -2.0,-2.0s0.9,-2.0 2.0,-2.0 2.0,0.9 2.0,2.0 -0.9,2.0 -2.0,2.0zm3.1,-9.0L8.9,8.0L8.9,6.0c0.0,-1.71 1.39,-3.1 3.1,-3.1 1.71,0.0 3.1,1.39 3.1,3.1l0.0,2.0z"/>
    </group>
</vector>
```

### ic_wifi_tethering.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/ic_wifi_tethering_x.jpg"  />
```
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="24dp"
        android:height="24dp"
        android:viewportWidth="24.0"
        android:viewportHeight="24.0"
        android:tint="?android:attr/colorControlNormal">
    <path
        android:pathData="M12,11c-1.1,0 -2,0.9 -2,2s0.9,2 2,2 2,-0.9 2,-2 -0.9,-2 -2,-2zM18,13a6,6 0,0 0,-6.75 -5.95c-2.62,0.32 -4.78,2.41 -5.18,5.02 -0.32,2.14 0.49,4.11 1.92,5.39 0.48,0.43 1.24,0.33 1.56,-0.23 0.24,-0.42 0.14,-0.94 -0.22,-1.26a3.99,3.99 0,0 1,-1.22 -3.94,3.954 3.954,0 0,1 2.9,-2.91A4.007,4.007 0,0 1,16 13c0,1.18 -0.51,2.23 -1.33,2.96 -0.36,0.33 -0.47,0.85 -0.23,1.27 0.31,0.54 1.04,0.69 1.5,0.28A5.97,5.97 0,0 0,18 13zM10.83,3.07c-4.62,0.52 -8.35,4.33 -8.78,8.96a9.966,9.966 0,0 0,4.02 9.01c0.48,0.35 1.16,0.2 1.46,-0.31 0.25,-0.43 0.14,-0.99 -0.26,-1.29 -2.28,-1.69 -3.65,-4.55 -3.16,-7.7 0.54,-3.5 3.46,-6.29 6.98,-6.68C15.91,4.51 20,8.28 20,13c0,2.65 -1.29,4.98 -3.27,6.44 -0.4,0.3 -0.51,0.85 -0.26,1.29 0.3,0.52 0.98,0.66 1.46,0.31A9.96,9.96 0,0 0,22 13c0,-5.91 -5.13,-10.62 -11.17,-9.93z"
        android:fillColor="#FFFFFFFF"/>
</vector>


```
### wifi_friction.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/wifi_friction_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings">
    <item
        settings:state_encrypted="true"
        android:drawable="@drawable/ic_friction_lock_closed"/>
    <item
        settings:state_metered="true"
        android:drawable="@drawable/ic_friction_money"/>
</selector>

```

### wifi_signal.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/wifi_signal_x.jpg"  />
```

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings">
    <item settings:state_encrypted="true">
        <layer-list>
            <item>
                <level-list>
                    <item android:maxLevel="0" android:drawable="@drawable/ic_wifi_signal_1" />
                    <item android:maxLevel="1" android:drawable="@drawable/ic_wifi_signal_2" />
                    <item android:maxLevel="2" android:drawable="@drawable/ic_wifi_signal_3" />
                    <item android:maxLevel="3" android:drawable="@drawable/ic_wifi_signal_4" />
                </level-list>
            </item>
            <item android:drawable="@drawable/ic_wifi_signal_lock" />
        </layer-list>
    </item>
    <item settings:state_encrypted="false">
        <level-list>
            <item android:maxLevel="0" android:drawable="@drawable/ic_wifi_signal_1" />
            <item android:maxLevel="1" android:drawable="@drawable/ic_wifi_signal_2" />
            <item android:maxLevel="2" android:drawable="@drawable/ic_wifi_signal_3" />
            <item android:maxLevel="3" android:drawable="@drawable/ic_wifi_signal_4" />
        </level-list>
    </item>
</selector>


```

### wifi_signal_dark.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/wifi_signal_dark_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>

<selector xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings">
    <item settings:state_encrypted="true" android:drawable="@drawable/wifi_signal_lock_dark" />
    <item settings:state_encrypted="false" android:drawable="@drawable/wifi_signal_open_dark" />
</selector>




```

### wifi_signal_lock_dark.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/wifi_signal_lock_dark_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>

<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:maxLevel="0" android:drawable="@drawable/ic_wifi_lock_signal_1_dark" />
    <item android:maxLevel="1" android:drawable="@drawable/ic_wifi_lock_signal_2_dark" />
    <item android:maxLevel="2" android:drawable="@drawable/ic_wifi_lock_signal_3_dark" />
    <item android:maxLevel="3" android:drawable="@drawable/ic_wifi_lock_signal_4_dark" />
</level-list>



```


### wifi_signal_open_dark.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/settings/wifi_signal_open_dark_x.jpg"  />
```
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:maxLevel="0" android:drawable="@drawable/ic_wifi_signal_1_dark" />
    <item android:maxLevel="1" android:drawable="@drawable/ic_wifi_signal_2_dark" />
    <item android:maxLevel="2" android:drawable="@drawable/ic_wifi_signal_3_dark" />
    <item android:maxLevel="3" android:drawable="@drawable/ic_wifi_signal_4_dark" />
</level-list>


```









## systemui
```
systemui的 drawable地址
http://androidxref.com/9.0.0_r3/xref/frameworks/base/packages/SystemUI/res/drawable/
```
### ic_qs_wifi_0.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_0_s.jpg"  />
```

```
### ic_qs_wifi_1.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_1_s.jpg"  />
```

```

### ic_qs_wifi_2.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_2_s.jpg"  />
```

```

### ic_qs_wifi_3.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_3_s.jpg"  />
```

```


### ic_qs_wifi_4.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_4_s.jpg"  />
```

```

### ic_qs_wifi_detail_empty.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_detail_empty_s.jpg"  />
```

```

### ic_qs_wifi_disabled.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_disabled_s.jpg"  />
```

```

### ic_qs_wifi_disconnected.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_disconnected_s.jpg"  />
```

```

### ic_qs_wifi_full_0.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_full_0_s.jpg"  />
```

```

### ic_qs_wifi_full_1.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_full_1_s.jpg"  />
```

```

### ic_qs_wifi_full_2.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_full_2_s.jpg"  />
```

```

### ic_qs_wifi_full_3.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_full_3_s.jpg"  />
```

```

### ic_qs_wifi_full_4.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_full_4_s.jpg"  />
```

```

### ic_qs_wifi_no_network.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_qs_wifi_no_network_s.jpg"  />
```

```

### ic_signal_wifi_transient.xml

<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_signal_wifi_transient_s.jpg"  />
```

```
### ic_signal_wifi_transient_animation.xm
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/ic_signal_wifi_transient_animation_s.jpg"  />
```

```
### qs_ic_wifi_lock.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/qs_ic_wifi_lock_s.jpg"  />
```

```
### stat_sys_ethernet.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_ethernet_s.jpg"  />
```

```
### stat_sys_ethernet_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_ethernet_fully_s.jpg"  />
```

```
### stat_sys_wifi_in.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_in_s.jpg"  />
```

```
### stat_sys_wifi_inout.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_inout_s.jpg"  />
```

```
### stat_sys_wifi_out.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_out_s.jpg"  />
```

```

### stat_sys_wifi_signal_0.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_0_s.jpg"  />
```

```
### stat_sys_wifi_signal_0_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_0_fully_s.jpg"  />
```

```
### stat_sys_wifi_signal_1.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_1_s.jpg"  />
```

```
### stat_sys_wifi_signal_1_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_1_fully_s.jpg"  />
```

```
### stat_sys_wifi_signal_2.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_2_s.jpg"  />
```

```
### stat_sys_wifi_signal_2_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_2_fully_s.jpg"  />
```

```
### stat_sys_wifi_signal_3.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_3_s.jpg"  />
```

```
### stat_sys_wifi_signal_3_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_3_fully_s.jpg"  />
```

```
### stat_sys_wifi_signal_4.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_4_s.jpg"  />
```

```
### stat_sys_wifi_signal_4_fully.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_4_fully_s.jpg"  />
```

```
### stat_sys_wifi_signal_null.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/systemui/stat_sys_wifi_signal_null_s.jpg"  />
```

```


## framework
```
framework 的 drawable地址
http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/res/res/drawable/

```


### ic_signal_wifi_badged_0_bars.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_0_bars_f.jpg"  />
```

```
### ic_signal_wifi_badged_1_bar.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_1_bar_f.jpg"  />
```

```
### ic_signal_wifi_badged_2_bars.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_2_bars_f.jpg"  />
```

```
### ic_signal_wifi_badged_3_bars.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_3_bars_f.jpg"  />
```

```

### ic_signal_wifi_badged_4_bars.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_4_bars_f.jpg"  />
```

```
### ic_signal_wifi_badged_4k.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_4k_f.jpg"  />
```

```

### ic_signal_wifi_badged_hd.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_hd_f.jpg"  />
```

```
### ic_signal_wifi_badged_ld.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_ld_f.jpg"  />
```

```
### ic_signal_wifi_badged_sd.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_signal_wifi_badged_sd_f.jpg"  />
```

```
### ic_wifi_settings.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_settings_f.jpg"  />
```

```
### ic_wifi_signal_0.xml.f
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_signal_0_f.jpg"  />
```

```
### ic_wifi_signal_1.xml.f
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_signal_1_f.jpg"  />
```

```
### ic_wifi_signal_2.xml.f
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_signal_2_f.jpg"  />
```

```
### ic_wifi_signal_3.xml.f
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_signal_3_f.jpg"  />
```

```
### ic_wifi_signal_4.xml.f
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/ic_wifi_signal_4_f.jpg"  />
```

```
### stat_notify_wifi_in_range.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/stat_notify_wifi_in_range_f.jpg"  />
```

```
### stat_sys_tether_wifi.xml
<img src="//../zimage/wireless/wifi/06_wifiicon/framework/stat_sys_tether_wifi_f.jpg"  />
```

```
