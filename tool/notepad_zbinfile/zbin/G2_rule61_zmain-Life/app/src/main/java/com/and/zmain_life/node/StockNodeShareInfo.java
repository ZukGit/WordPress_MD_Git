package com.and.zmain_life.node;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.and.zmain_life.bean.DataHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
*    用于在 Node 中 共享的 Object 对象  携带一些 关键的信息  避免 4000*4000=1600万次 的 for循环
 */
public class StockNodeShareInfo  {   // 多扩展  多添加

    public  float price;
    public  String ts_code;

public   boolean XinGuLieBiaoIPO_isIPO;    // 是否是在 IPO中显示的项
public   double XinGuLieBiaoIPO_ballot;
public   String XinGuLieBiaoIPO_cname;
public   double XinGuLieBiaoIPO_funds;
public   String XinGuLieBiaoIPO_ipo_date;
public   String XinGuLieBiaoIPO_issue_date;
public   double XinGuLieBiaoIPO_limit_amount;
public   double XinGuLieBiaoIPO_market_amount;
public   String XinGuLieBiaoIPO_name;
public   double XinGuLieBiaoIPO_pe;
public   double XinGuLieBiaoIPO_price;

public   String XinGuLieBiaoIPO_ts_code;




    public   double MeiRiZhiBiao_turnover_rate_f;  // 自由股换手率
    public   float MeiRiZhiBiao_price;   // 每日指标  价格
    public  float  MeiRiZhiBiao_total_mv; // 	总市值 （万元）
    public  float  MeiRiZhiBiao_circ_mv;  // 流通市值（万元）


    public   double RiXianXingQingvShiJianWeiXu_change;  // 日线行情 涨跌值
    public   double RiXianXingQingvShiJianWeiXu_pct_chg;  // 日线行情 涨跌幅度
    public   double RiXianXingQingvShiJianWeiXu_amount;     //  日线行情 成交额
    public   double RiXianXingQingvShiJianWeiXu_high;     //  日线行情  最高价
    public   double RiXianXingQingvShiJianWeiXu_low;     //  日线行情 最低价



    public   double RiXianXingQingvShiJianWeiXu_day3_pct_chg;  // 日线行情 三天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_day5_pct_chg;     //  日线行情 五天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_day10_pct_chg;     //  日线行情  十天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_day15_pct_chg;     //  日线行情 十五天内涨幅


    public   double RiXianXingQingvShiJianWeiXu_day30_pct_chg;  // 日线行情 三天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_day60_pct_chg;     //  日线行情 五天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_day90_pct_chg;     //  日线行情  十天内涨幅
    public   double RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;     //  日线行情 十五天内涨幅


    public double getRiXianXingQingvShiJianWeiXu_day30_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day30_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day30_pct_chg(double riXianXingQingvShiJianWeiXu_day30_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day30_pct_chg = riXianXingQingvShiJianWeiXu_day30_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day60_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day60_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day60_pct_chg(double riXianXingQingvShiJianWeiXu_day60_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day60_pct_chg = riXianXingQingvShiJianWeiXu_day60_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day90_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day90_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day90_pct_chg(double riXianXingQingvShiJianWeiXu_day90_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day90_pct_chg = riXianXingQingvShiJianWeiXu_day90_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_dayyear_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_dayyear_pct_chg(double riXianXingQingvShiJianWeiXu_dayyear_pct_chg) {
        RiXianXingQingvShiJianWeiXu_dayyear_pct_chg = riXianXingQingvShiJianWeiXu_dayyear_pct_chg;
    }




    public double getRiXianXingQingvShiJianWeiXu_day3_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day3_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day3_pct_chg(double riXianXingQingvShiJianWeiXu_day3_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day3_pct_chg = riXianXingQingvShiJianWeiXu_day3_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day5_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day5_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day5_pct_chg(double riXianXingQingvShiJianWeiXu_day5_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day5_pct_chg = riXianXingQingvShiJianWeiXu_day5_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day10_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day10_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day10_pct_chg(double riXianXingQingvShiJianWeiXu_day10_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day10_pct_chg = riXianXingQingvShiJianWeiXu_day10_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_day15_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_day15_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_day15_pct_chg(double riXianXingQingvShiJianWeiXu_day15_pct_chg) {
        RiXianXingQingvShiJianWeiXu_day15_pct_chg = riXianXingQingvShiJianWeiXu_day15_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_high() {
        return RiXianXingQingvShiJianWeiXu_high;
    }

    public void setRiXianXingQingvShiJianWeiXu_high(double riXianXingQingvShiJianWeiXu_high) {
        RiXianXingQingvShiJianWeiXu_high = riXianXingQingvShiJianWeiXu_high;
    }

    public double getRiXianXingQingvShiJianWeiXu_low() {
        return RiXianXingQingvShiJianWeiXu_low;
    }

    public void setRiXianXingQingvShiJianWeiXu_low(double riXianXingQingvShiJianWeiXu_low) {
        RiXianXingQingvShiJianWeiXu_low = riXianXingQingvShiJianWeiXu_low;
    }





    public double getRiXianXingQingvShiJianWeiXu_change() {
        return RiXianXingQingvShiJianWeiXu_change;
    }

    public void setRiXianXingQingvShiJianWeiXu_change(double riXianXingQingvShiJianWeiXu_change) {
        RiXianXingQingvShiJianWeiXu_change = riXianXingQingvShiJianWeiXu_change;
    }

    public double getRiXianXingQingvShiJianWeiXu_pct_chg() {
        return RiXianXingQingvShiJianWeiXu_pct_chg;
    }

    public void setRiXianXingQingvShiJianWeiXu_pct_chg(double riXianXingQingvShiJianWeiXu_pct_chg) {
        RiXianXingQingvShiJianWeiXu_pct_chg = riXianXingQingvShiJianWeiXu_pct_chg;
    }

    public double getRiXianXingQingvShiJianWeiXu_amount() {
        return RiXianXingQingvShiJianWeiXu_amount;
    }

    public void setRiXianXingQingvShiJianWeiXu_amount(double riXianXingQingvShiJianWeiXu_amount) {
        RiXianXingQingvShiJianWeiXu_amount = riXianXingQingvShiJianWeiXu_amount;
    }



    public float getMeiRiZhiBiao_total_mv() {
        return MeiRiZhiBiao_total_mv;
    }

    public void setMeiRiZhiBiao_total_mv(float meiRiZhiBiao_total_mv) {
        MeiRiZhiBiao_total_mv = meiRiZhiBiao_total_mv;
    }

    public float getMeiRiZhiBiao_circ_mv() {
        return MeiRiZhiBiao_circ_mv;
    }

    public void setMeiRiZhiBiao_circ_mv(float meiRiZhiBiao_circ_mv) {
        MeiRiZhiBiao_circ_mv = meiRiZhiBiao_circ_mv;
    }









    public double getMeiRiZhiBiao_turnover_rate_f() {
        return MeiRiZhiBiao_turnover_rate_f;
    }

    public void setMeiRiZhiBiao_turnover_rate_f(double meiRiZhiBiao_turnover_rate_f) {
        MeiRiZhiBiao_turnover_rate_f = meiRiZhiBiao_turnover_rate_f;
    }

    public float getMeiRiZhiBiao_price() {
        return MeiRiZhiBiao_price;
    }

    public void setMeiRiZhiBiao_price(float meiRiZhiBiao_price) {
        MeiRiZhiBiao_price = meiRiZhiBiao_price;
    }






    public boolean isXinGuLieBiaoIPO_isIPO() {
        return XinGuLieBiaoIPO_isIPO;
    }

    public void setXinGuLieBiaoIPO_isIPO(boolean xinGuLieBiaoIPO_isIPO) {
        XinGuLieBiaoIPO_isIPO = xinGuLieBiaoIPO_isIPO;
    }

    public double getXinGuLieBiaoIPO_ballot() {
        return XinGuLieBiaoIPO_ballot;
    }

    public void setXinGuLieBiaoIPO_ballot(double xinGuLieBiaoIPO_ballot) {
        XinGuLieBiaoIPO_ballot = xinGuLieBiaoIPO_ballot;
    }

    public String getXinGuLieBiaoIPO_cname() {
        return XinGuLieBiaoIPO_cname;
    }

    public void setXinGuLieBiaoIPO_cname(String xinGuLieBiaoIPO_cname) {
        XinGuLieBiaoIPO_cname = xinGuLieBiaoIPO_cname;
    }

    public double getXinGuLieBiaoIPO_funds() {
        return XinGuLieBiaoIPO_funds;
    }

    public void setXinGuLieBiaoIPO_funds(double xinGuLieBiaoIPO_funds) {
        XinGuLieBiaoIPO_funds = xinGuLieBiaoIPO_funds;
    }

    public String getXinGuLieBiaoIPO_ipo_date() {
        return XinGuLieBiaoIPO_ipo_date;
    }

    public void setXinGuLieBiaoIPO_ipo_date(String xinGuLieBiaoIPO_ipo_date) {
        XinGuLieBiaoIPO_ipo_date = xinGuLieBiaoIPO_ipo_date;
    }

    public String getXinGuLieBiaoIPO_issue_date() {
        return XinGuLieBiaoIPO_issue_date;
    }

    public void setXinGuLieBiaoIPO_issue_date(String xinGuLieBiaoIPO_issue_date) {
        XinGuLieBiaoIPO_issue_date = xinGuLieBiaoIPO_issue_date;
    }

    public double getXinGuLieBiaoIPO_limit_amount() {
        return XinGuLieBiaoIPO_limit_amount;
    }

    public void setXinGuLieBiaoIPO_limit_amount(double xinGuLieBiaoIPO_limit_amount) {
        XinGuLieBiaoIPO_limit_amount = xinGuLieBiaoIPO_limit_amount;
    }

    public double getXinGuLieBiaoIPO_market_amount() {
        return XinGuLieBiaoIPO_market_amount;
    }

    public void setXinGuLieBiaoIPO_market_amount(double xinGuLieBiaoIPO_market_amount) {
        XinGuLieBiaoIPO_market_amount = xinGuLieBiaoIPO_market_amount;
    }

    public String getXinGuLieBiaoIPO_name() {
        return XinGuLieBiaoIPO_name;
    }

    public void setXinGuLieBiaoIPO_name(String xinGuLieBiaoIPO_name) {
        XinGuLieBiaoIPO_name = xinGuLieBiaoIPO_name;
    }

    public double getXinGuLieBiaoIPO_pe() {
        return XinGuLieBiaoIPO_pe;
    }

    public void setXinGuLieBiaoIPO_pe(double xinGuLieBiaoIPO_pe) {
        XinGuLieBiaoIPO_pe = xinGuLieBiaoIPO_pe;
    }

    public double getXinGuLieBiaoIPO_price() {
        return XinGuLieBiaoIPO_price;
    }

    public void setXinGuLieBiaoIPO_price(double xinGuLieBiaoIPO_price) {
        XinGuLieBiaoIPO_price = xinGuLieBiaoIPO_price;
    }



    public String getXinGuLieBiaoIPO_ts_code() {
        return XinGuLieBiaoIPO_ts_code;
    }

    public void setXinGuLieBiaoIPO_ts_code(String xinGuLieBiaoIPO_ts_code) {
        XinGuLieBiaoIPO_ts_code = xinGuLieBiaoIPO_ts_code;
    }



    public StockNodeShareInfo() {

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTs_code() {
        return ts_code;
    }

    public void setTs_code(String ts_code) {
        this.ts_code = ts_code;
    }


//
//    @Override
//    public String toString() {
//        return "StockNodeShareInfo{" +
//                "price=" + price +
//                ", ts_code='" + ts_code + '\'' +
//                ", XinGuLieBiaoIPO_isIPO=" + XinGuLieBiaoIPO_isIPO +
//                ", XinGuLieBiaoIPO_ballot=" + XinGuLieBiaoIPO_ballot +
//                ", XinGuLieBiaoIPO_cname='" + XinGuLieBiaoIPO_cname + '\'' +
//                ", XinGuLieBiaoIPO_funds=" + XinGuLieBiaoIPO_funds +
//                ", XinGuLieBiaoIPO_ipo_date='" + XinGuLieBiaoIPO_ipo_date + '\'' +
//                ", XinGuLieBiaoIPO_issue_date='" + XinGuLieBiaoIPO_issue_date + '\'' +
//                ", XinGuLieBiaoIPO_limit_amount=" + XinGuLieBiaoIPO_limit_amount +
//                ", XinGuLieBiaoIPO_market_amount=" + XinGuLieBiaoIPO_market_amount +
//                ", XinGuLieBiaoIPO_name='" + XinGuLieBiaoIPO_name + '\'' +
//                ", XinGuLieBiaoIPO_pe=" + XinGuLieBiaoIPO_pe +
//                ", XinGuLieBiaoIPO_price=" + XinGuLieBiaoIPO_price +
//                ", XinGuLieBiaoIPO_ts_code='" + XinGuLieBiaoIPO_ts_code + '\'' +
//                ", MeiRiZhiBiao_turnover_rate_f=" + MeiRiZhiBiao_turnover_rate_f +
//                ", MeiRiZhiBiao_price=" + MeiRiZhiBiao_price +
//                ", MeiRiZhiBiao_total_mv=" + MeiRiZhiBiao_total_mv +
//                ", MeiRiZhiBiao_circ_mv=" + MeiRiZhiBiao_circ_mv +
//                ", RiXianXingQingvShiJianWeiXu_change=" + RiXianXingQingvShiJianWeiXu_change +
//                ", RiXianXingQingvShiJianWeiXu_pct_chg=" + RiXianXingQingvShiJianWeiXu_pct_chg +
//                ", RiXianXingQingvShiJianWeiXu_low=" + RiXianXingQingvShiJianWeiXu_low +
//                ", RiXianXingQingvShiJianWeiXu_high=" + RiXianXingQingvShiJianWeiXu_high +
//                ", RiXianXingQingvShiJianWeiXu_amount=" + RiXianXingQingvShiJianWeiXu_amount +
//                '}';
//    }


    @Override
    public String toString() {
        return "StockNodeShareInfo{" +
                "price=" + price +
                ", ts_code='" + ts_code + '\'' +
                ", XinGuLieBiaoIPO_isIPO=" + XinGuLieBiaoIPO_isIPO +
                ", XinGuLieBiaoIPO_ballot=" + XinGuLieBiaoIPO_ballot +
                ", XinGuLieBiaoIPO_cname='" + XinGuLieBiaoIPO_cname + '\'' +
                ", XinGuLieBiaoIPO_funds=" + XinGuLieBiaoIPO_funds +
                ", XinGuLieBiaoIPO_ipo_date='" + XinGuLieBiaoIPO_ipo_date + '\'' +
                ", XinGuLieBiaoIPO_issue_date='" + XinGuLieBiaoIPO_issue_date + '\'' +
                ", XinGuLieBiaoIPO_limit_amount=" + XinGuLieBiaoIPO_limit_amount +
                ", XinGuLieBiaoIPO_market_amount=" + XinGuLieBiaoIPO_market_amount +
                ", XinGuLieBiaoIPO_name='" + XinGuLieBiaoIPO_name + '\'' +
                ", XinGuLieBiaoIPO_pe=" + XinGuLieBiaoIPO_pe +
                ", XinGuLieBiaoIPO_price=" + XinGuLieBiaoIPO_price +
                ", XinGuLieBiaoIPO_ts_code='" + XinGuLieBiaoIPO_ts_code + '\'' +
                ", MeiRiZhiBiao_turnover_rate_f=" + MeiRiZhiBiao_turnover_rate_f +
                ", MeiRiZhiBiao_price=" + MeiRiZhiBiao_price +
                ", MeiRiZhiBiao_total_mv=" + MeiRiZhiBiao_total_mv +
                ", MeiRiZhiBiao_circ_mv=" + MeiRiZhiBiao_circ_mv +
                ", RiXianXingQingvShiJianWeiXu_change=" + RiXianXingQingvShiJianWeiXu_change +
                ", RiXianXingQingvShiJianWeiXu_pct_chg=" + RiXianXingQingvShiJianWeiXu_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_amount=" + RiXianXingQingvShiJianWeiXu_amount +
                ", RiXianXingQingvShiJianWeiXu_high=" + RiXianXingQingvShiJianWeiXu_high +
                ", RiXianXingQingvShiJianWeiXu_low=" + RiXianXingQingvShiJianWeiXu_low +
                ", RiXianXingQingvShiJianWeiXu_day3_pct_chg=" + RiXianXingQingvShiJianWeiXu_day3_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day5_pct_chg=" + RiXianXingQingvShiJianWeiXu_day5_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day10_pct_chg=" + RiXianXingQingvShiJianWeiXu_day10_pct_chg +
                ", RiXianXingQingvShiJianWeiXu_day15_pct_chg=" + RiXianXingQingvShiJianWeiXu_day15_pct_chg +
                '}';
    }
}
