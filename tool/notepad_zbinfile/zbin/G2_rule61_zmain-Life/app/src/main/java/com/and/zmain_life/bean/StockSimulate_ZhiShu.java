package com.and.zmain_life.bean;

import android.content.Context;
import android.util.Log;

import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// 21 22年上证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sh000001.js?visitDstTime=1

// 21 22年创业指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399006.js?visitDstTime=1

// 21 22年深证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399001.js?visitDstTime=1

// 21 22 科创板指数
//https://data.gtimg.cn/flashdata/hushen/daily/22/sh000688.js?visitDstTime=1
//https://data.gtimg.cn/flashdata/hushen/daily/21/sh000688.js?visitDstTime=1


// 读取两年的指数的数据
public  class StockSimulate_ZhiShu implements Serializable {

    String zhishu_tscode;  //  399006.SZ   创业板指数   399001.SZ 深证指数     000001.SH 上证指数  000688.SH 科创板指数
    String zhishu_name ;   // 创业板指数  深证指数  上证指数  科创板指数
    String rawResponseText ; // 从 network 返回来的 原始数据
    HashMap<Integer,String> mYear_ResponseMap ; // 从 network 返回来的 原始数据

    int curYear ;   // 今年
    int preYear ; // 去年

    public double day3_zhushu_pct;
    public   double day5_zhushu_pct;
    public  double day10_zhushu_pct;
    public double day15_zhushu_pct;

    public double day30_zhushu_pct;
    public   double day60_zhushu_pct;
    public  double day90_zhushu_pct;
    public double year_zhushu_pct;

    public int getCurYearIndexInList(){
        if(!isContainCurYearData()){
            return 0;
        }

        int index = 0 ;
        int mYearBegin =  (TimeUtil.getCurrentYYYY()%100)*10000+101;

        for (int i = 0; i <allZhiShuItemList.size() ; i++) {
            if(allZhiShuItemList.get(i).trade_day < mYearBegin){
                 return i+1;

            }
        }
        if(!isContainPreYearData()){   // 如果 不包含 去年的数据  那么  今年的数据大小 就是 今年的数据
            return allZhiShuItemList.size();
        }


        return index;

    }

    public   ArrayList<StockSimulate_ZhiShu_Item> allZhiShuItemList = null ;  // 当前 指数的两年数据    用的是  同一个  static   应该 各用各的



    public  boolean isContainPreYearData(){
        boolean isContainPreYearData = false;
        int mYearBegin =  ((TimeUtil.getCurrentYYYY()-1)%100)*10000+101;
        // 2022+101 =   20220101
        // 210101-101   ═════════   210000
        // 2021 --> 21 * 10000
        for (int i = 0; i <allZhiShuItemList.size() ; i++) {
            if(allZhiShuItemList.get(i).trade_day > mYearBegin){
//                print(zhishu_name+ "  isContainCurYearData="+isContainCurYearData);
                return true;
            }
        }
//        print(zhishu_name+ "  isContainCurYearData="+isContainCurYearData);
        return isContainPreYearData;

    }
    // 检测是否包含今年的数据
    public  boolean isContainCurYearData(){  // 220101  这些是 tradeday 的数据
        boolean isContainCurYearData = false;
       int mYearBegin =  (TimeUtil.getCurrentYYYY()%100)*10000+101;
       // 2022+101 =   20220101
       // 210101-101   ═════════   210000
        // 2021 --> 21 * 10000
        for (int i = 0; i <allZhiShuItemList.size() ; i++) {
            if(allZhiShuItemList.get(i).trade_day > mYearBegin){
//                print(zhishu_name+ "  isContainCurYearData="+isContainCurYearData);
                return true;
            }
        }
//        print(zhishu_name+ "  isContainCurYearData="+isContainCurYearData);
       return isContainCurYearData;
    }

    StockSimulate_ZhiShu(String ts_code , String name , int mYear ){
        zhishu_tscode = ts_code;
        zhishu_name = name;
        curYear = mYear;
        preYear = mYear - 1;
        allZhiShuItemList = new  ArrayList<StockSimulate_ZhiShu_Item>();
        mYear_ResponseMap = new HashMap<Integer,String>();


    }

    double getDynamicDay_Pct_Chg(int dayCount){
        double  sumDay_pct_chg = 0d;

        if(dayCount > allZhiShuItemList.size() || dayCount <= 0){
            return sumDay_pct_chg;
        }

        for (int i = 0; i < dayCount; i++) {
            sumDay_pct_chg+= allZhiShuItemList.get(i).pct_chg;
        }


        return sumDay_pct_chg;
    }


   ArrayList<String> getZhiShuUrlList(){
       // 获得 zishu 路径url 的 方法
       ArrayList<String> urlList = new      ArrayList<String>();


       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1   深
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1   上
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1   创
       //    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000688.js?visitDstTime=1   科

       String cur_year_url = "https://data.gtimg.cn/flashdata/hushen/daily/"+(curYear%100)+"/"+getRequest_TsCode()+".js?visitDstTime=1";

       String pre_year_url = "https://data.gtimg.cn/flashdata/hushen/daily/"+(preYear%100)+"/"+getRequest_TsCode()+".js?visitDstTime=1";

       //  多次 尝试 获取 今年的数据
       urlList.add(cur_year_url);
       urlList.add(cur_year_url);
       urlList.add(cur_year_url);

       urlList.add(pre_year_url);
       return urlList;
    }

    /*    daily_data_22="\n\
            220104 3649.15 3632.33 3651.89 3610.09 405027769\n\
            220105 3628.26 3595.18 3628.26 3583.47 423902028\n\
            220106 3581.22 3586.08 3594.49 3559.88 371540543\n\
            ;
     */

    boolean isListContain_TradeDay_Item(StockSimulate_ZhiShu_Item item){
        boolean isContain = false;
        if(item == null || item.trade_day == 0){
            return false;
        }


        for (int i = 0; i < allZhiShuItemList.size() ; i++) {
            StockSimulate_ZhiShu_Item mExistItem =  allZhiShuItemList.get(i);

            if(mExistItem.trade_day == item.trade_day){
                return true;
            }
        }

        return isContain;


    }


    // 399006.SZ   --> sz399006;
    String getRequest_TsCode(){
        String request_tx_code = null;
        String onlyCharStr_TEMP = zhishu_tscode.replace(".","");
        String onlyCharStr = DataHolder.clearDigital(onlyCharStr_TEMP).trim();
        String onlyDigitalStr = onlyCharStr_TEMP.replace(onlyCharStr,"");
        request_tx_code = (onlyCharStr+onlyDigitalStr).toLowerCase().trim();
        print("zhishu_tscode="+zhishu_tscode+"   request_tx_code="+ request_tx_code);
        return request_tx_code;
    }


   boolean initWithResponseString(String responseText , boolean isEnd){
         boolean init_flag = false;
         if(responseText == null || !responseText.contains("\\n")){

             return init_flag;
         }

         String[] splitArrr = responseText.split("\\n");

         if(splitArrr == null || splitArrr.length == 0){
            print("当前获取到的 指数的 response 无法解析成功! responseText="+ responseText);
         }

       for (int i = 0; i <splitArrr.length ; i++) {
           String splitItem = splitArrr[i].trim();
           splitItem = splitItem.replace(";","");
           splitItem = splitItem.replace("\\n","");
           splitItem = splitItem.replace("\\","");
           if(!splitItem.contains(" ")){  // 不包含 空格 无法解析出 数据
               continue;
           }
           StockSimulate_ZhiShu_Item item = new StockSimulate_ZhiShu_Item();
           if(item.initWithOneLine(splitItem)){   // 初始化成功

               if(!isListContain_TradeDay_Item(item)){
                   allZhiShuItemList.add(item);

               }


           }



       }

       if(allZhiShuItemList.size() > 0 ){
           allZhiShuItemList.sort(new Comparator<StockSimulate_ZhiShu_Item>() {
               @Override
               public int compare(StockSimulate_ZhiShu_Item o1, StockSimulate_ZhiShu_Item o2) {

                   if(o1.trade_day > o2.trade_day){
                       return -1;
                   } else if(o1.trade_day < o2.trade_day ){
                       return 1;
                   }

                   if(o1.trade_day == o2.trade_day){
                       return 0;
                   }

                   return 1;
               }
           });
       }

       if(isEnd || isContainCurYearData()){   //  两日的数据已经 加载完了

           init_pct_chg_And_preday_close_price();  // 开始动态的计算 每个 item 的 pct_chg

//           for (int i = 0; i < allZhiShuItemList.size() ; i++) {
//               print("showZhiShu "+zhishu_name+"  "+zhishu_tscode+"["+i+"]  "+ allZhiShuItemList.get(i).toString());
//           }

           if(isContainCurYearData()){
               day3_zhushu_pct = getDynamicDay_Pct_Chg(3);
               day5_zhushu_pct = getDynamicDay_Pct_Chg(5);
               day10_zhushu_pct = getDynamicDay_Pct_Chg(10);
               day15_zhushu_pct = getDynamicDay_Pct_Chg(15);

               day30_zhushu_pct = getDynamicDay_Pct_Chg(30);
               day60_zhushu_pct = getDynamicDay_Pct_Chg(60);
               day90_zhushu_pct = getDynamicDay_Pct_Chg(90);

               year_zhushu_pct = getDynamicDay_Pct_Chg(getCurYearIndexInList());



           }

           print(zhishu_name +" day3_zhushu_pct = "+ day3_zhushu_pct +"   day5_zhushu_pct="+day5_zhushu_pct+"  day10_zhushu_pct="+day10_zhushu_pct+"  day15_zhushu_pct="+day15_zhushu_pct+
                   "  day30_zhushu_pct ="+day30_zhushu_pct+"  day60_zhushu_pct="+day60_zhushu_pct+"  day90_zhushu_pct="+day90_zhushu_pct+"   year_zhushu_pct="+year_zhushu_pct+"  getCurYearIndexInList()="
                   +getCurYearIndexInList()+" isContainCurYearData()="+isContainCurYearData() +"   allZhiShuItemList.size()="+ allZhiShuItemList.size());


       }

       print( zhishu_name+"   "+zhishu_tscode+" allZhiShuItemList.size() = "+ allZhiShuItemList.size());
         return init_flag;



    }

    public double getDayYear_Pct_Chg(){

     return   getDynamicDay_Pct_Chg(getCurYearIndexInList());

    }

    void init_pct_chg_And_preday_close_price(){

        StockSimulate_ZhiShu_Item mOldDay_Item ;
        StockSimulate_ZhiShu_Item mNewDay_Item ;
        if(allZhiShuItemList.size() < 1){
            print("init_pct_chg_And_preday_close_price 当前读取到的数据 allZhiShuItemList.size() = "+allZhiShuItemList.size() +" 太少  无法动态计算!");
            return;
        }

        for (int i = 0; i < allZhiShuItemList.size() ; i++) {
            if(i == 0){
                continue;
            }

            if(i == allZhiShuItemList.size() -1){
                mOldDay_Item   = allZhiShuItemList.get(i);
                mNewDay_Item  = allZhiShuItemList.get(i -1);
            }

            mOldDay_Item   = allZhiShuItemList.get(i);
            mNewDay_Item  = allZhiShuItemList.get(i -1);

            if(mOldDay_Item.close_price == 0){
                continue;
            }

            mNewDay_Item.preday_close_price = mOldDay_Item.close_price;
            mNewDay_Item.pct_chg = (mNewDay_Item.close_price - mOldDay_Item.close_price)/mOldDay_Item.close_price;

        }



    }



    public static class StockSimulate_ZhiShu_Item implements Serializable {

        int trade_day ;
        double open_price;
        double close_price;
        double high_price;
        double lower_price;


        double pct_chg;   // 当前涨幅  今天的收盘-昨天的收盘/昨天的收盘 = 今天的涨幅
        long trade_money;  // 交易金额

        double preday_close_price; //  昨天的交易的收盘价

        double get_pct_chg(){
            return pct_chg;
        }


        StockSimulate_ZhiShu_Item(){}

        // 220707 3353.13 3364.40 3375.86 3332.31 334400202\n\
       boolean initWithOneLine(String oneLine){
            boolean init_flag = false;
            if(oneLine == null || !oneLine.contains(" ")){
print(" 初始化 StockSimulate_ZhiShu_Item 失败  无法解析1 oneLine = "+ oneLine);
                return init_flag;
            }

            String[]  prop_item = oneLine.split(" ");
            if(prop_item == null || prop_item.length != 6){

                print(" 初始化 StockSimulate_ZhiShu_Item 失败2  无法解析 oneLine = "+ oneLine);

                return init_flag;

            }

           for (int i = 0; i < prop_item.length; i++) {
               String  itemProp = prop_item[i];
               print(" initWithOneLine prop_item.length["+i+"] = "+ itemProp);
               if(i == 0){
                   if(StockHolder.isNumeric(itemProp) && !itemProp.contains(".")){
                       trade_day = Integer.parseInt(itemProp);
                   }
                   continue;
               }


               if(i == 1){
                   if(StockHolder.isDoubleNumeric(itemProp) ){
                       open_price = Double.parseDouble(itemProp);
                   }
                   continue;
               }



               if(i == 2){
                   if(StockHolder.isDoubleNumeric(itemProp) ){
                       close_price = Double.parseDouble(itemProp);
                   }
                   continue;
               }

               if(i == 3){
                   if(StockHolder.isDoubleNumeric(itemProp) ){
                       high_price = Double.parseDouble(itemProp);
                   }
                   continue;
               }

               if(i == 4){
                   if(StockHolder.isDoubleNumeric(itemProp) ){
                       lower_price = Double.parseDouble(itemProp);
                   }
                   continue;
               }


               if(i == 5){
                   if(StockHolder.isNumeric(itemProp) ){
                       trade_money = Long.parseLong(itemProp);
                   }
                   continue;
               }




           }

           if(trade_day != 0 && open_price != 0 &&  close_price != 0
                   && high_price != 0 &&  lower_price != 0  && trade_money != 0 ){

               init_flag = true;
           }


               print("trade_day="+trade_day+" open_price="+open_price+" close_price="
                       +close_price+" high_price="+high_price+"  lower_price="+lower_price+"  trade_money="+ trade_money+"  init_flag="+init_flag);


           return init_flag;


        }

        @Override
        public String toString() {
            return "StockSimulate_ZhiShu_Item{" +
                    "trade_day=" + trade_day +
                    ", open_price=" + open_price +
                    ", close_price=" + close_price +
                    ", high_price=" + high_price +
                    ", lower_price=" + lower_price +
                    ", pct_chg=" + pct_chg +
                    ", trade_money=" + trade_money +
                    ", preday_close_price=" + preday_close_price +
                    '}';
        }
    }


    static void print(String logStr){
        android.util.Log.i("ZhiShu",logStr);


    }

}


// 21 22年上证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sh000001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sh000001.js?visitDstTime=1

// 21 22年创业指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399006.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399006.js?visitDstTime=1

// 21 22年深证指数
//    https://data.gtimg.cn/flashdata/hushen/daily/22/sz399001.js?visitDstTime=1
//    https://data.gtimg.cn/flashdata/hushen/daily/21/sz399001.js?visitDstTime=1

// 21 22 科创板指数
//https://data.gtimg.cn/flashdata/hushen/daily/22/sh000688.js?visitDstTime=1
//https://data.gtimg.cn/flashdata/hushen/daily/21/sh000688.js?visitDstTime=1


/*    daily_data_22="\n\
            220104 3649.15 3632.33 3651.89 3610.09 405027769\n\
            220105 3628.26 3595.18 3628.26 3583.47 423902028\n\
            220106 3581.22 3586.08 3594.49 3559.88 371540543\n\
.........
            220706 3391.03 3355.35 3391.03 3333.10 384630047\n\
            220707 3353.13 3364.40 3375.86 3332.31 334400202\n\
            【日期】【开盘价】【收盘价】【最高价】【最低价】【成交金额】
            (【今日收盘价】-【昨日收盘价】)/【昨日收盘价】= 当日涨幅
            ";*/
