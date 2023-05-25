package com.and.zmain_life.wireless;

import com.and.zmain_life.R;


// 0. Wifi点击弹框的增加
// 1.Androidmanifest.xml 的 修改
// 2. res/layout 文件的添加
// 3. Activity 文件的增加处理
public   class Wireless_Common_Activity extends WirelessBaseActivity {


    @Override
    protected void init() {


    }



    @Override
    protected  int setLayoutId(){
      return R.layout.wireless_common_main_layout;

    }


}
