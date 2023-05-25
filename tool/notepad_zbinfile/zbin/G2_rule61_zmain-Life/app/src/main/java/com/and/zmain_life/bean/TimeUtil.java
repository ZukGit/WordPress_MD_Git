package com.and.zmain_life.bean;

import android.content.Context;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.application.ZMainApplication;
import com.and.zmain_life.node.StockNodeShareInfo;
import com.and.zmain_life.stock_node.Stock_Node;
import com.and.zmain_life.stock_node.Stock_NodeImpl;
import com.and.zmain_life.stock_sheet_bean.DaZongJiaoYi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongMeiRiChengJiaoTongJi;
import com.and.zmain_life.stock_sheet_bean.GangGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.GeGuZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.GuDongZengJianChi;
import com.and.zmain_life.stock_sheet_bean.GuPiaoHuiGou;
import com.and.zmain_life.stock_sheet_bean.HuShenGangGuTongChiGuMingXi;
import com.and.zmain_life.stock_sheet_bean.HuShenGangTongZiJinLiuXiang;
import com.and.zmain_life.stock_sheet_bean.HuShenGuTongShiDaChengJiaoGu;
import com.and.zmain_life.stock_sheet_bean.LongHuBangJiGouMingXi;
import com.and.zmain_life.stock_sheet_bean.LongHuBangMeiRiMingXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiTingFuPaiXinXi;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhangDieTingJiaGe;
import com.and.zmain_life.stock_sheet_bean.MeiRiZhiBiao;
import com.and.zmain_life.stock_sheet_bean.RiXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiHuiZong;
import com.and.zmain_life.stock_sheet_bean.RongZiRongQuanJiaoYiMingXi;
import com.and.zmain_life.stock_sheet_bean.XianShouGuJieJin;
import com.and.zmain_life.stock_sheet_bean.XinGuLieBiaoIPO;
import com.and.zmain_life.stock_sheet_bean.YueXianXingQingvMeiGeJiaoYiYueDeZuiHouYiRi;
import com.and.zmain_life.stock_sheet_bean.ZhouXianXingQingvShiJianWeiXu;
import com.and.zmain_life.stock_sheet_bean.ZuiJinYueZhangDieTingTongJi;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimeUtil {


    static  public     String getTimeStamp_YYYY_MM_DD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static int getCurrentYYYYMMDD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        return Integer.parseInt(df.format(new Date()));

    }

    static int getCurrentYYYY() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");

        return Integer.parseInt(df.format(new Date()));

    }



    public static Long getToday17PointTimeLong(){
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


    static boolean  isWeekDate(int day ){
        boolean flag = false ;
        if((""+day).length() != 8 ){
            return false;
        }

        String YearStr = (""+day).substring(0,4);
        String MonthStr = (""+day).substring(4,6);
        String DayStr = (""+day).substring(6,8);

        int xyear = Integer.parseInt(YearStr);
        int xmonth = Integer.parseInt(MonthStr) ;
        int xday =  Integer.parseInt(DayStr);
        String xinqiTag = calculXinQi2Chinese(xyear,xmonth,xday);
        if("6".equals(xinqiTag) || "7".equals(xinqiTag)){
            return true;
        }



        return flag;

    }



    static String calculXinQi2Chinese(int year , int month ,int day) {
        String xinqiValue = "";
        Calendar  calendar_object = Calendar.getInstance();
        calendar_object.set(Calendar.YEAR,year);
        calendar_object.set(Calendar.MONTH,month-1);
        calendar_object.set(Calendar.DAY_OF_MONTH,day);
        int xinqi = calendar_object.get(Calendar.DAY_OF_WEEK) - 1;  //
        System.out.println(" Bxiniq = "+ xinqi);
        // 0 ---> 周一
        // 1 ---> 周二
        // 2---> 周三
        //
        switch (xinqi) {
            case 0:
                xinqiValue = "7";
                break;
            case 1:
                xinqiValue = "1";
                break;
            case 2:
                xinqiValue = "2";
                break;
            case 3:
                xinqiValue = "3";
                break;
            case 4:
                xinqiValue = "4";
                break;
            case 5:
                xinqiValue = "5";
                break;
            case 6:
                xinqiValue = "6";
                break;
            case 7:
                xinqiValue = "7";
                break;
            default:
                xinqiValue = "1";  // 默认周一
        }
        return xinqiValue;
    }


    static int  getYesterTradeDayIntFlag(int dayFlagInt ){
        int step = -1;
        int tomorrow = getFutureDayFlag(dayFlagInt,step);


        while(isWeekDate(tomorrow)) {  //  如果 不包含今日  那么 往后 计算

            tomorrow = getFutureDayFlag(tomorrow,step);
        }

        return tomorrow;
    }



    static int getFutureDayFlag(int dayFlagInt , int DaySpace){
        int curDay =  dayFlagInt;
        int futureDay = dayFlagInt;
        String daysDesc = dayFlagInt+"";
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        try {
            Date nowDate =    simple.parse(daysDesc);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return futureDay;
    }



    static int getCurrentYYYYMMDDWith17Point() {


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

    static  public     String getYYYY() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String date = df.format(new Date());
        return date;
    }



    static  public     String getTimeStamp_YYYY_MMDD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy_MMdd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }



    public static int differentDays_YYYYMMDD(String mYYYYMMDD1,String mYYYYMMDD2){
        int distanceDay = -1 ;
        if(mYYYYMMDD1 == null || mYYYYMMDD2 == null){
            return -1 ;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        try
        {
            Date date1 = format.parse(mYYYYMMDD1);
            Date date2 = format.parse(mYYYYMMDD2);
            distanceDay = differentDays(date1,date2);
        } catch (Exception e) {
            android.util.Log.i("TimeUtil","differentDays_YYYYMMDD  解析 字符串 为 时间 Date 失败:" );

        }


        return distanceDay;
    }

    public  static void  print(String tip){
        android.util.Log.i("TimeUtil",tip );
    }
    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

}
