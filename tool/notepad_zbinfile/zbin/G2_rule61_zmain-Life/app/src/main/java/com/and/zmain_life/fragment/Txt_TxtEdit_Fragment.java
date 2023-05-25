package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.activity.VideoLayoutActivity;
import com.and.zmain_life.adapter.GifAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.APP_In_BackGround_Event;
import com.and.zmain_life.bean.CurUserBean;
import com.and.zmain_life.bean.DataCreate;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_Gif_Common_Event;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.txt_edit.TxtAdapter;
import com.and.zmain_life.utils.RxBus;
import com.and.zmain_life.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.and.zmain_life.view.viewpagerlayoutmanager.ViewPagerLayoutManager;
import com.and.zmain_life.wireless.*;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;
import rx.functions.Action1;

import static androidx.core.content.ContextCompat.getSystemService;


public class Txt_TxtEdit_Fragment extends BaseFragment {
    @BindView(R.id.showtxt_recycleview)
    RecyclerView txtRecyclerView;


    ClipboardManager clipManager ;



    TxtAdapter txtAdapter;

    @BindView(R.id.append_edittext)
    EditText mEditText;

    @BindView(R.id.text_clicp_tip)
    TextView clip_tip_Text;




    @BindView(R.id.del_edittext_img)
    ImageView del_edittext_img;

    @BindView(R.id.insert_txt_img)
    ImageView insert_edittext_img;


    @BindView(R.id.clear_txt_button)
    Button clearTxtButton;


    //    insert_zand_btn
    @BindView(R.id.insert_zand_btn)
    Button inserZandBtn;

    //    zand_spinner
    @BindView(R.id.zand_spinner)
    Spinner zandSpinner;

    ArrayList<String>  spinnerTipArr = new ArrayList<String>();


//

    private ViewPagerLayoutManager viewPagerLayoutManager;

    // zand_file_@delete12_x_@/sdcard/1/_@/sdcard/2/【删除文件】
    // zand_file_@make12_x_@/sdcard/1/1.txt_@/sdcard/1/2.txt【创建文件】
    // zand_file_@renamenum123_x_@/sdcard/1/_@*_@1【名称修改为数字】
    // zand_file_@renamenumpre123_x_@/sdcard/1/_@*_@1【前缀加入数字】
    // zand_file_@renametime12_x_@/sdcard/1/_@*【名称修改为时间戳】
    // zand_file_@copytype123_x_@/sdcard/1/_@*_@/sdcard/2【复制文件夹1内容到文件夹2】
    // zand_file_@copy
    // zand_file_@copys
    // zand_file_@badtype123_x_@/sdcard/1/_@*_@/sdcard/2【加密文件夹1内容到文件夹2】
    // zand_file_@bads
    // zand_file_@bad
    // zand_file_@goodtype123_x_@/sdcard/1/_@*_@/sdcard/2【解密文件夹1内容到文件夹2】
    // zand_file_@goods
    // zand_file_@good

    // zand_file_@movetype123_x_@/sdcard/1/_@*_@/sdcard/1/2【剪切文件夹1文件到文件夹2】
    // zand_file_@moves
    // zand_file_@move
    // zand_file_@renameprefix123_x_@/sdcard/1/_@*_@_pre
    // zand_file_@renameappend123_x_@/sdcard/1/_@*_@_end
    // zand_file_@retype123_x_@/sdcard/1/_@jpg_@png
    // zand_file_@renameplace123_x_@/sdcard/1/_@jpg_@111_@222
    // zcmd_run_shutdown -p

     int getCurrentYear() {

        SimpleDateFormat df = new SimpleDateFormat("YYYY");

        return Integer.parseInt(df.format(new Date()));

    }

    int getCurrentMonth() {

        SimpleDateFormat df = new SimpleDateFormat("MM");

        return Integer.parseInt(df.format(new Date()));

    }

    int getPreviousMonthBeginDayFlagInt() {
       int mYear =  getCurrentYear();
        int mMonth =  getCurrentMonth() - 1;
        if(mMonth <= 0 ){
            mMonth = 12 ;
            mYear = mYear - 1 ;
        }
          int mDay =  1;
          // 2023*10000 = 20230000
         // 3*100 = 300
         // 1
         //  20230301
          int dayFlagInt = mYear * 10000 + mMonth* 100 + 1;

          return dayFlagInt;

    }


    int getYYYYMMdd() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(df.format(new Date()));

    }


     int  getYesterTradeDayIntFlag(int dayFlagInt ){
        int step = -1;

        int yesterdayIntFlag = getFutureDayFlag(dayFlagInt,step);

         String YearStr = (""+yesterdayIntFlag).substring(0,4);
         String MonthStr = (""+yesterdayIntFlag).substring(4,6);
         String DayStr = (""+yesterdayIntFlag).substring(6);
         int xyear = Integer.parseInt(YearStr);
         int xmonth = Integer.parseInt(MonthStr);
         int xday =  Integer.parseInt(DayStr);
        String xinqi = StockHolder.calculXinQi2Chinese(xyear,xmonth,xday);
        if("7".equals(xinqi)){
            yesterdayIntFlag = getFutureDayFlag(yesterdayIntFlag,-2);
        }

         if("6".equals(xinqi)){
             yesterdayIntFlag = getFutureDayFlag(yesterdayIntFlag,-1);
         }


        return yesterdayIntFlag;
    }






     int getFutureDayFlag(int dayFlagInt , int DaySpace){
        int curDay =  dayFlagInt;
        int futureDay = dayFlagInt;
        String daysDesc = dayFlagInt+"";
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        try {
            Date nowDate = null;
            try {
                nowDate = simple.parse(daysDesc);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar    curCalendar =Calendar.getInstance();
            curCalendar.setTime(nowDate);
            int curDayYear =   curCalendar.get(Calendar.DAY_OF_YEAR);
            int newDay2Year = curDayYear + DaySpace;
            curCalendar.set(Calendar.DAY_OF_YEAR,newDay2Year);

            int year = curCalendar.get(Calendar.YEAR);
            int month = curCalendar.get(Calendar.MONTH)+1;
            int day2month = curCalendar.get(Calendar.DAY_OF_MONTH);
            String monthDesc = month>=10?month+"":"0"+month;
            String dayDesc = day2month>=10?day2month+"":"0"+day2month;

            String dayIntFalg = year+""+monthDesc+dayDesc;
            futureDay = Integer.parseInt(dayIntFalg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return futureDay;
    }



    // 只有过了 17 点 才会有 今天的 交易数据  所以 这里 判断 如果 过了 17点 那么返回今天的日期  不到17点 那么返回昨天的日期
     int getCurrentYYYYMMDDWith17Point() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int refrashDay  = getCurrentYYYYMMDD() ;


        long today17Long =   getToday17PointTimeLong();

        long nowLong  = Calendar.getInstance().getTimeInMillis();
        if(nowLong > today17Long) {    // 晚上过来17点了  可以得到今天数据
            refrashDay = getCurrentYYYYMMDD() ;
        }else {
            // 还没到今天17点  只可以得到昨天数据
            refrashDay  = 	getYesterTradeDayIntFlag(getCurrentYYYYMMDD());

        }

        return refrashDay;

    }


    public  Long getToday17PointTimeLong(){
        Calendar c1 = Calendar.getInstance();
//    c1.add(Calendar.DATE,-1);
        c1.set(Calendar.HOUR_OF_DAY,17);
        c1.set(Calendar.MINUTE,0);
        c1.set(Calendar.SECOND,0);
        c1.set(Calendar.MILLISECOND,0);
        //下面两句可以省略
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    System.out.print("getToday17PointTimeLong:"+simpleDateFormat.format(c1.getTime()));
        return c1.getTimeInMillis();
    }


     int getCurrentYYYYMMDD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        return Integer.parseInt(df.format(new Date()));

    }




    String buildYearMainStockTip(){
        StringBuilder sb = new StringBuilder();
//        String mYearMainStockTip = "https://github.do/https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/2022_main_stock.xlsx";
        String mYearMainStockTip =  getCurrentYear()+"_main_stock.xlsx";

        sb.append(mYearMainStockTip);

        return sb.toString();

    }


//        zstock_tushare_tool_J0.bat file_C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log  &&
//          C:\Users\zhuzj5\Desktop\zbin\J0_DayPython\J0_0000_call_fileday_python.bat C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log   &&
//          zrule_apply_G2.bat #_38 C:\Users\zhuzj5\Desktop\zbin\J0_Data\
//        && zgupiao_analysis_J0.bat  #_1  yyyymmdd_20211210

    String buildStockTip(){
         StringBuilder sb = new StringBuilder();

        sb.append("zstock_tushare_tool_J0.bat file_%zbin%"+File.separator+"J0_Data"+File.separator+getCurrentYear()+"_main_stock.log "+" && ");

        sb.append("%zbin%"+File.separator+"J0_DayPython"+File.separator+"J0_0000_call_fileday_python.bat  %zbin%"+File.separator+"J0_Data"+File.separator+getCurrentYear()+"_main_stock.log "+" && ");

        sb.append("zrule_apply_G2.bat #_38 %zbin%"+File.separator+"J0_Data"+" && ");

        sb.append("zgupiao_analysis_J0.bat  #_1  yyyymmdd_"+getCurrentYYYYMMDDWith17Point());


         return sb.toString();

    }

    private void InitZandSpinner() {
        spinnerTipArr = new ArrayList<String>();
        spinnerTipArr.clear();
        spinnerTipArr.add("zand_bash_【在安卓系统bash本身执行 获取系统属性并粘贴到剪切板】@allprop");
        spinnerTipArr.add("zand_bash_【获取小米_SNO_设备号】@ ");

        spinnerTipArr.add("zgit_down_【"+getCurrentYear()+"_main_stock.xlsx "+"文件打开】"+buildYearMainStockTip()+"【path:"+DataHolder.Sdcard_File+File.separator+"1"+File.separator+getCurrentYear()+"_main_stock.xlsx"+"】");
        spinnerTipArr.add("zgit_down_【"+getCurrentYear()+"_main_stock.xlsx "+"文件更新下载】"+buildYearMainStockTip());
        spinnerTipArr.add("zgit_jsondailydown_【"+getPreviousMonthBeginDayFlagInt()+"_"+getCurrentYYYYMMDDWith17Point()+"下载最近月份 stock_daily_xxx.json】"+ getPreviousMonthBeginDayFlagInt()+"_"+getCurrentYYYYMMDDWith17Point());
        spinnerTipArr.add("zand_file_【指定目录进行lin操该该目录jpg下生成lin-dir 去类型md5文件】@lindirs1_x_@/sdcard/1/lin");
        spinnerTipArr.add("zand_file_【指定原src目录 经过bad后复制到dst目录】@baddirs12_x_@/sdcard/1/lin_@/sdcard/zapp");
        spinnerTipArr.add("zand_file_【删除文件】@delete12_x_@/sdcard/1/1.jpg_@/sdcard/1/2.txt");
        spinnerTipArr.add("zand_file_【依据类型删除文件目录文件】@deletetype12_x_@/sdcard/zapp/_@mp4");
        spinnerTipArr.add("zand_file_【删除文件目录所有多媒体文件】@deletetype12_x_@/sdcard/zapp/_@media");
        spinnerTipArr.add("zand_file_【删除文件目录相同类型md5文件】@delsamemd12_x_@/sdcard/zapp/jpg_lin_port_@*");
        spinnerTipArr.add("zand_file_【删除文件目录相同类型md5文件】@delsamemd12_x_@/sdcard/zapp/jpg_lin_land_@*");
        spinnerTipArr.add("zand_file_【创建文件】@make12_x_@/sdcard/1/3.txt_@/sdcard/1/2.txt");
        spinnerTipArr.add("zand_file_【名称修改为数字】@renamenum123_x_@/sdcard/1/_@*_@1");
        spinnerTipArr.add("zand_file_【名称前缀加入数字】@renamenumpre123_x_@/sdcard/1/_@*_@1");
        spinnerTipArr.add("zand_file_【名称修改为时间戳】@renametime12_x_@/sdcard/1/_@*");
        spinnerTipArr.add("zand_file_【名称修改为MD5】@renamemd12_x_@/sdcard/1/_@*");
        spinnerTipArr.add("zand_file_【所有目录文件名称修改为MD5】@renameallmd12_x_@/sdcard/1/_@*");

        spinnerTipArr.add("zand_file_【复制文件夹1内容到文件夹2】@copytype123_x_@/sdcard/1/_@*_@/sdcard/2");
        spinnerTipArr.add("zand_file_【加密文件夹1内容到文件夹2】@badtype123_x_@/sdcard/1/_@*_@/sdcard/2");

        spinnerTipArr.add("zand_file_【解密文件夹2内容到文件夹3】@goodtype123_x_@/sdcard/2/_@*_@/sdcard/3");
        spinnerTipArr.add("zand_file_【剪切文件夹1文件到文件夹2】@movetype123_x_@/sdcard/1/_@*_@/sdcard/1/2");

        spinnerTipArr.add("zand_file_【修改过滤的文件名称加入前缀】@renameprefix123_x_@/sdcard/1/_@*_@pre_ ");
        spinnerTipArr.add("zand_file_【修改过滤的文件名称加入后缀】@renameappend123_x_@/sdcard/1/_@*_@_end ");

        spinnerTipArr.add("zand_file_【修改过滤的文件的文件类型】@retype123_x_@/sdcard/1/_@jpg_@png ");
        spinnerTipArr.add("zand_file_【修改过滤的文件名称进行替换】@renameplace1234_x_@/sdcard/1/_@jpg_@111_@222 ");


        spinnerTipArr.add("zand_file_【加密文件夹/1/port/ 内容到 lin_port】@badtype123_x_@/sdcard/1/port_@jpg_@/sdcard/zapp/jpg_lin_port/");
        spinnerTipArr.add("zand_file_【加密文件夹/1/land/ 内容到 lin_land】@badtype123_x_@/sdcard/1/land_@jpg_@/sdcard/zapp/jpg_lin_land/");

        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件PC朗读 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_48  voice_狗狗狗狗狗狗12345677654321Dog ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件PC即刻重启 】shutdown -r -t 0 ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【邮件对主机zhuzj5执行关机操作】shutdown -p ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件PC无限弹框  】for /l %I in (1,1,10000) do mshta vbscript:msgbox(\"Hello-World!\",64,\"警告\") ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件查看PC截屏与截屏 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_02  ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件无线查看PC截屏_间隔30秒 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_02  loop_true interval_30 ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件查看PC文件列表 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_1   query_D:\\ ");
        spinnerTipArr.add("mailcmd_zhuzj5_run_【 邮件下载PC附件 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_3   upfile_D:\\1A\\123.txt  ");
        spinnerTipArr.add("mailcmder_zhuzj5_run_【邮件对主机zhuzj5执行代码雨_屏保】prule_apply_Z9.bat  #_1 ");
        spinnerTipArr.add("mailcmder_zhuzj5_run_【 邮件PC轰炸 】zbatrule_I9_Rule30.bat _996_ ");
        spinnerTipArr.add("mailcmder_zhuzj5_run_【 邮件 AI智能贪吃蛇(观看) 】prule_apply_Z9.bat  #_3 ");
        spinnerTipArr.add("mailcmder_zhuzj5_run_【 邮件微信飞机大战 】prule_apply_Z9.bat  #_2 ");
        spinnerTipArr.add("mailcmder_zhuzj5_run_【 邮件代码雨_屏保 】prule_apply_Z9.bat  #_1 ");

        spinnerTipArr.add("zcmd_run_【 PC即刻关机 】shutdown -p");
        spinnerTipArr.add("zcmd_run_【 PC即刻重启 】shutdown -r -t 0 ");
        spinnerTipArr.add("zcmd_run_【 PC弹框 】mshta vbscript:msgbox(\"Hello-World!\",64,\"警告\")(window.close)");
        spinnerTipArr.add("zcmd_run_【 PC无限弹框  】for /l %I in (1,1,10000) do mshta vbscript:msgbox(\"Hello-World!\",64,\"警告\") ");

        // cmder.exe 需要在  zcmder_prexe_G2.bat 中 执行 ,而 这里又 不认识 中文  只能尝试在 cmd 下运行
        spinnerTipArr.add("zcmd_run_【 qq_txt PC朗读 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_48  voice_狗狗狗狗狗狗Dog ");
        spinnerTipArr.add("zcmd_run_【 qq_txt 邮件查看PC截屏与截屏 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_02  ");
        spinnerTipArr.add("zcmd_run_【 qq_txt 邮件无线查看PC截屏_间隔30秒 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_02  loop_true interval_30 ");
        spinnerTipArr.add("zcmd_run_【 qq_txt 邮件查看PC文件列表 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_1   query_D:\\ ");
        spinnerTipArr.add("zcmd_run_【 qq_txt 邮件下载PC附件 】 %win_zbin%"+File.separator+"zrule_apply_G2.bat  #_54  type_3   upfile_D:\\1A\\123.txt  ");

//        zstock_tushare_tool_J0.bat file_C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log  &&  C:\Users\zhuzj5\Desktop\zbin\J0_DayPython\J0_0000_call_fileday_python.bat C:\Users\zhuzj5\Desktop\zbin\J0_Data\2021_main_stock.log && zrule_apply_G2.bat #_38 C:\Users\zhuzj5\Desktop\zbin\J0_Data\ && zgupiao_analysis_J0.bat  #_1  yyyymmdd_20211210
        spinnerTipArr.add("zcmder_run_【 qq_txt PC轰炸 】zbatrule_I9_Rule30.bat _996_ ");

        // zstock_tushare_tool_J0.bat file_%zbin%"+File.separator+"J0_Data
        spinnerTipArr.add("zcmder_run_【股票"+getCurrentYYYYMMDDWith17Point()+"更新】"+buildStockTip());
        spinnerTipArr.add("zcmder_run_【 代码雨_屏保 】prule_apply_Z9.bat  #_1 ");
        spinnerTipArr.add("zcmder_run_【 微信飞机大战 】prule_apply_Z9.bat  #_2 ");
        spinnerTipArr.add("zcmder_run_【 AI智能贪吃蛇(观看) 】prule_apply_Z9.bat  #_3 ");
        spinnerTipArr.add("zcmder_run_【 贪吃蛇(可玩) 】prule_apply_Z9.bat  #_4 ");
        spinnerTipArr.add("zcmder_run_【 坦克大战(双人可玩) 】prule_apply_Z9.bat  #_5 ");
        spinnerTipArr.add("zcmder_run_【 ADB滑动Test 】zbatrule_I9_Rule30.bat _19_ ");
        spinnerTipArr.add("fmt1.link.ac.cn【 Openconnect VPN_1 】");
        spinnerTipArr.add("tw1.link.ac.cn 【 Openconnect VPN_2 】");
        spinnerTipArr.add("hk1.link.ac.cn 【 Openconnect VPN_3 】");


        ArrayAdapter   arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, spinnerTipArr);
        zandSpinner.setAdapter(arrayAdapter);
        zandSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                showToast.showTextToast(getContext(),String.valueOf(spinnerArr.get(position)));
//                Toast.makeText(getActivity(),String.valueOf(spinnerTipArr.get(position)),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }



    @Override
    protected int setLayoutId() {
        return R.layout.txt_edittext_layout;
    }


    public void showWirelessDialog() {  // showWirelessDialog_Begin
        final android.app.AlertDialog.Builder alterDiaglog = new android.app.AlertDialog.Builder(mContext);
        alterDiaglog.setTitle("wisl_ztimestamp_zabspath");
        java.util.ArrayList<String> singleListAr = new java.util.ArrayList<String>();
        singleListAr.add("wifi");
        singleListAr.add("gps");
        singleListAr.add("bt");
        singleListAr.add("nfc");
        singleListAr.add("common");

        final  CharSequence[] items = new  CharSequence[singleListAr.size()];
        int matchedCategory = -1;
        for (int i = 0; i < singleListAr.size(); i++) {
            items[i] = singleListAr.get(i);
        }


        alterDiaglog.setSingleChoiceItems(items,matchedCategory, new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                CharSequence categoryName = items[which];
                String typeStr = categoryName+"";

                Toast.makeText(mContext,"which["+which+"]"+"【"+categoryName+"】",Toast.LENGTH_SHORT).show();

                switch (typeStr){

                    case "wifi":
                        Intent wifi_intent = new Intent(mContext, Wireless_Wifi_Activity.class);
                        mContext.startActivity(wifi_intent);

                        break;

                    case "gps":
                        Intent gps_intent = new Intent(mContext, Wireless_GPS_Activity.class);
                        mContext.startActivity(gps_intent);
                        break;

                    case "bt":
                        Intent bt_intent = new Intent(mContext, Wireless_Bt_Activity.class);
                        mContext.startActivity(bt_intent);
                        break;


                    case "nfc":
                        Intent nfc_intent = new Intent(mContext, Wireless_NFC_Activity.class);
                        mContext.startActivity(nfc_intent);
                        break;

                    case "common":
                        Intent common_intent = new Intent(mContext, Wireless_Common_Activity.class);
                        mContext.startActivity(common_intent);
                        break;


                    default:


                }



            }
        });

        alterDiaglog.setPositiveButton("Positiv", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                Toast.makeText(mContext,"Positiv_click",Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNegativeButton("Negative", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                Toast.makeText(mContext,"Negative_click",Toast.LENGTH_SHORT).show();

            }
        });

        alterDiaglog.setNeutralButton("Neutral", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                Toast.makeText(mContext,"Neutral_click",Toast.LENGTH_SHORT).show();

            }
        });

        alterDiaglog.show();
    }    // showWirelessDialog_End


    Context mContext;
    @Override
    protected void init() {
        mContext = getContext();
        insert_edittext_img.setOnClickListener(this::insert_edittext_toFile);
        clearTxtButton.setOnClickListener(this::clear_txt_File);
        clearTxtButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showWirelessDialog();
                return false;
            }
        });
        del_edittext_img.setOnClickListener(this::del_edittext_content);
        clip_tip_Text.setOnClickListener(this::getfromClip);

        inserZandBtn.setOnClickListener(this::inset_zand_to_edittext);

        txtAdapter = new TxtAdapter(getActivity(), DataHolder.sdcard_1_txt_contentList,this);
        txtRecyclerView.setAdapter(txtAdapter);
        InitZandSpinner();

/*        adapter = new TxtAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);
*/

        RxBus.getDefault().toObservable(APP_In_BackGround_Event.class).subscribe((Action1<APP_In_BackGround_Event>) event -> {
            if (event.isPlayOrPause()) {  // true  后台



            }else{   // 前台

                String clipContent =  getClipContent();

                if(clipContent == null || "".equals(clipContent) || isExistInTxtList(clipContent)){
                    android.util.Log.i("clicpxxx","APP_In_BackGround_Event clipContent  为 空  clipContent="+ clipContent);
                    return;
                }

                android.util.Log.i("clicpxxx","APP_In_BackGround_Event clipContent ="+ clipContent);
                mEditText.setText(clipContent);
                insert_edittext_img.performClick();



                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

                android.util.Log.i("zukgit", " hideSoftInputFromWindow DD ");



                android.util.Log.i("clipxxx", "APP_In_BackGround_Event APP_In_BackGround_Event=前台事件 ");
            }
        });







    }


    boolean isExistInTxtList(String  rawClipContent){
        boolean exist = false;

        for (int i = 0; i < DataHolder.sdcard_1_txt_contentList.size(); i++) {
            String stritem =  DataHolder.sdcard_1_txt_contentList.get(i);
            if(stritem.startsWith(rawClipContent)){
                return true;
            }
        }
        return exist;

    }
    private void    inset_zand_to_edittext(View view) {

       int selectedIndex =  zandSpinner.getSelectedItemPosition();
       String selectedText = spinnerTipArr.get(selectedIndex);
//       String rawCommand = clearChinese(selectedText);
        String rawCommand =selectedText.trim();

//        rawCommand = rawCommand.replace("【","").replace("】","").trim();

// 不要  去掉 【】  让执行时去掉
//        if(rawCommand.contains("【")){
//            rawCommand =    rawCommand.substring(0,rawCommand.indexOf("【"));
//        }

if(DataHolder.sdcard_1_txt_contentList == null){
    DataHolder.sdcard_1_txt_contentList = new ArrayList<String>();
}
        DataHolder.sdcard_1_txt_contentList.add(rawCommand);
        DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
        txtAdapter.setList(DataHolder.sdcard_1_txt_contentList);
        txtAdapter.notifyDataSetChanged();

    }

    public String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    private void del_edittext_content(View view) {
        mEditText.setHint("插入内容到 /sdcard/1/1.txt 文件");
        mEditText.setText("");

    }

    private void clear_txt_File(View view) {

        showClearTxtDialog();
    }


    void showClearTxtDialog(){

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getContext());
        alterDiaglog.setTitle("删除TXT内容");//文字
        alterDiaglog.setMessage("请确认删除TXT内容:\n");//提示消息

        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"执行删除TXT内容"
                        ,Toast.LENGTH_SHORT).show();


                // 打开 Dialog 只有  Ok  才执行删除
                DataHolder.sdcard_1_txt_contentList.clear();
                DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,"");
                txtAdapter.setList(DataHolder.sdcard_1_txt_contentList);
                txtAdapter.notifyDataSetChanged();

                if(mClearTxtDialog != null){
                    mClearTxtDialog.dismiss();
                }
            }
        });

        //显示
        mClearTxtDialog =    alterDiaglog.show();


    }
    private   AlertDialog  mClearTxtDialog ;
    private void insert_edittext_toFile(View view) {
     String insertText =    mEditText.getText().toString();
        String lastText = null;
        if(DataHolder.sdcard_1_txt_contentList == null){
            System.out.println("/sdcard/1/1.txt 读取列表为空 可能没有权限读取");
            Toast.makeText(getActivity(),"/sdcard/1/1.txt 读取列表为空 可能没有权限读取",Toast.LENGTH_SHORT).show();
            return;
        }
     if(DataHolder.sdcard_1_txt_contentList.size() == 0){
         lastText = "";
     }else{
         lastText = DataHolder.sdcard_1_txt_contentList.get(DataHolder.sdcard_1_txt_contentList.size()-1);
     }

     if(insertText != null && !insertText.equals(lastText) &&  !DataHolder.sdcard_1_txt_contentList.contains(insertText) ){
         DataHolder.sdcard_1_txt_contentList.add(insertText);
         DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
         txtAdapter.setList(DataHolder.sdcard_1_txt_contentList);
         txtAdapter.notifyDataSetChanged();

     }else{

         Log.i("Zcontent2Clip","不能插入到文本的  insertText="+insertText);
         Log.i("Zcontent2Clip","不能插入到文本的  !insertText.equals(lastText)="+(!insertText.equals(lastText)) +"   !DataHolder.sdcard_1_txt_contentList.contains(insertText)=="+(!DataHolder.sdcard_1_txt_contentList.contains(insertText)));

     }

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

    }


    private void getfromClip(View view) {
        String clipContent =  getClipContent();

        if(clipContent == null || "".equals(clipContent) || isExistInTxtList(clipContent)){
            android.util.Log.i("clicpxxx","getfromClip clipContent  为 空  clipContent="+ clipContent);
            return;
        }

        android.util.Log.i("clicpxxx","getfromClip clipContent ="+ clipContent);
        mEditText.setText(clipContent);
        insert_edittext_img.performClick();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String clipContent =  getClipContent();

        if(clipContent == null || "".equals(clipContent) || isExistInTxtList(clipContent)){
            android.util.Log.i("clicpxxx","onViewCreated clipContent  为 空  clipContent="+ clipContent);
            return;
        }

        android.util.Log.i("clicpxxx","onViewCreated clipContent ="+ clipContent);
        mEditText.setText(clipContent);
        insert_edittext_img.performClick();

    }

    @Override
    public void onResume() {
        super.onResume();



        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("clicpxxx"," MainFragment.curPage="+  MainFragment.curPage);
            CountDownTimer loopPlay_Timer =    new CountDownTimer(800, 600) {
                @Override
                public void onTick(long millisUntilFinished) { }
                @Override
                public void onFinish() {
                    String clipContent =  getClipContent();

                    if(clipContent == null || "".equals(clipContent) || isExistInTxtList(clipContent)){
                        android.util.Log.i("clicpxxx","onResume clipContent  为 空  clipContent="+ clipContent);
                        return;
                    }

                    android.util.Log.i("clicpxxx","onResume clipContent ="+ clipContent);
                    mEditText.setText(clipContent);
                    insert_edittext_img.performClick();


                }
            };

            loopPlay_Timer.start();



            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

            android.util.Log.i("zukgit", " hideSoftInputFromWindow EE ");



        }




    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume

            String clipContent =  getClipContent();

            if(clipContent == null || "".equals(clipContent) || isExistInTxtList(clipContent)){
                android.util.Log.i("clicpxxx","setUserVisibleHint clipContent  为 空  clipContent="+ clipContent);
                return;
            }

            android.util.Log.i("clicpxxx","setUserVisibleHint  clipContent ="+ clipContent);
            mEditText.setText(clipContent);
            insert_edittext_img.performClick();


        } else {
            //相当于Fragment的onPause
        }
    }







    @Override
    public void onPause() {
        super.onPause();

//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }




    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }



    /**
     * 获取剪切板内容
     * @return
     */
    public  String getClipContent(){
        if(getActivity() != null){
            ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (manager != null) {
                if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                    CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                    String addedTextString = String.valueOf(addedText);
                    if (!TextUtils.isEmpty(addedTextString)) {
                        return addedTextString;
                    }
                }
            }
        }

        return "";
    }

    /**
     * 设置剪切板内容
     * @return
     */
    public  void setClipContent(String content2Clip ){

        Log.i("linenum_textView_click"," content2Clip = "+content2Clip);

        if (TextUtils.isEmpty(content2Clip)) {

            Log.i("linenum_textView_click"," content2Clip = isEmpty "+content2Clip);

            return ;
        }

        ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            Log.i("linenum_textView_click"," content2Clip = "+content2Clip+"  setPrimaryClip ");
            manager.setPrimaryClip(ClipData.newPlainText(content2Clip,content2Clip));
        }
        Log.i("linenum_textView_click"," content2Clip = "+content2Clip+"  Toast ");
        Toast.makeText(getActivity(),"已复制到剪切板:\n"+content2Clip,Toast.LENGTH_SHORT).show();

    }


    /**
     * 清空剪切板
     */
    public  void clearClipContent(){
        ClipboardManager manager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setPrimaryClip(ClipData.newPlainText("",""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
