package com.and.zmain_life.wireless;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.and.zmain_life.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;


// 0. Wifi点击弹框的增加
// 1.Androidmanifest.xml 的 修改
// 2. res/layout 文件的添加
// 3. Activity 文件的增加处理
public   class Wireless_GPS_Activity extends WirelessBaseActivity {


    @Override
    protected void init() {


    }



    @Override
    protected  int setLayoutId(){

      return R.layout.wireless_gps_main_layout;

    }


}
