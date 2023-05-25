package com.and.zmain_life.adapter;


import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;


/**

 */
public class StockSimulateViewHolderContent extends RecyclerView.ViewHolder {
    TextView stockName_text;     // 股票名称
    TextView stockValue_text;  // 股票价值
    TextView stockBenefitmoney_text;   // 股票浮动盈亏
    TextView stockBenefitmoneyRate_text;   // 股票浮动盈亏比例
    TextView stockStockNum_text;   //  持股数量

    // 可用数量 和
    TextView stockAvaliableStockNum_or_buytime_text;  // 可用股票数量??  好像 不是有用


    TextView stockBuyPrice_text;   // 买入价格
    TextView stockNowPrice_text;  // 现价

    TextView stockBuyStock_text;    // 买股票按钮
    TextView stockSellStock_text;  // 卖股票按钮


    TextView today_stock_profitvalue_text;    // 今日浮盈金额
    TextView today_stock_updownrate_text;  // 今日涨跌幅度
    TextView today_stock_updownvalue_text;  // 今日涨跌值
    TextView preday_stock_close_price_text;    // 昨日收盘价格
    RelativeLayout userstock_content_lauout;  // 所有的内容的容器  用于长按 把股票名称复制到剪切板中


    TextView stock_day3_flow_rate_text;    // 3 天的总涨幅
    TextView stock_day5_flow_rate_text;    // 5 天的 总涨幅
    TextView stock_day10_flow_rate_text;    // 10 天的 总涨幅
    TextView stock_day15_flow_rate_text;    // 15 天的 总涨幅


    TextView stock_day30_flow_rate_text;    // 3 天的总涨幅
    TextView stock_day60_flow_rate_text;    // 5 天的 总涨幅
    TextView stock_day90_flow_rate_text;    // 10 天的 总涨幅
    TextView stock_dayyear_flow_rate_text;    // 15 天的 总涨幅


    TextView stock_day3_zhizhu_rate_text;    // 指数3 天的总涨幅
    TextView stock_day5_zhizhu_rate_text;    // 指数5 天的 总涨幅
    TextView stock_day10_zhizhu_rate_text;    //指数 10 天的 总涨幅
    TextView stock_day15_zhizhu_rate_text;    //指数 15 天的 总涨幅

    TextView stock_day30_zhizhu_rate_text;    // 指数3 天的总涨幅
    TextView stock_day60_zhizhu_rate_text;    // 指数5 天的 总涨幅
    TextView stock_day90_zhizhu_rate_text;    //指数 10 天的 总涨幅
    TextView stock_day_year_zhizhu_rate_text;    //指数 年 天的 总涨幅


    public StockSimulateViewHolderContent(View view) {
        super(view);
        stockName_text = (TextView) view.findViewById(R.id.content_stock_name);
        stockValue_text = (TextView) view.findViewById(R.id.content_stock_value);
        stockBenefitmoney_text = (TextView) view.findViewById(R.id.content_stock_benefitmoney);
        stockBenefitmoneyRate_text = (TextView) view.findViewById(R.id.content_stock_benefitmoney_rate);
        stockStockNum_text = (TextView) view.findViewById(R.id.content_stock_stocknum);
        stockAvaliableStockNum_or_buytime_text = (TextView) view.findViewById(R.id.content_stock_avaliable_stocknum);
        stockBuyPrice_text = (TextView) view.findViewById(R.id.content_stock_buyprice);
        stockNowPrice_text = (TextView) view.findViewById(R.id.content_stock_nowprice);
        stockBuyStock_text = (TextView) view.findViewById(R.id.content_stock_buystock);
        stockSellStock_text = (TextView) view.findViewById(R.id.content_stock_sellstock);


        stock_day3_flow_rate_text = (TextView) view.findViewById(R.id.day3_flow_rate);
        stock_day5_flow_rate_text = (TextView) view.findViewById(R.id.day5_flow_rate);
        stock_day10_flow_rate_text = (TextView) view.findViewById(R.id.day10_flow_rate);
        stock_day15_flow_rate_text = (TextView) view.findViewById(R.id.day15_flow_rate);


        stock_day30_flow_rate_text = (TextView) view.findViewById(R.id.day30_flow_rate);
        stock_day60_flow_rate_text = (TextView) view.findViewById(R.id.day60_flow_rate);
        stock_day90_flow_rate_text = (TextView) view.findViewById(R.id.day90_flow_rate);
        stock_dayyear_flow_rate_text = (TextView) view.findViewById(R.id.dayyear_flow_rate);


        today_stock_profitvalue_text  =  (TextView) view.findViewById(R.id.content_today_profit_value);  // 今日浮盈金额
        today_stock_updownrate_text =  (TextView) view.findViewById(R.id.content_today_updown_rate); // 今日涨跌幅度
        today_stock_updownvalue_text =  (TextView) view.findViewById(R.id.content_today_updown_value);   // 今日涨跌值
        preday_stock_close_price_text =  (TextView) view.findViewById(R.id.content_preday_price);     // 昨日收盘价格
        userstock_content_lauout = (RelativeLayout) itemView.findViewById(R.id.userstock_content_lauout);


        stock_day3_zhizhu_rate_text = (TextView) view.findViewById(R.id.day3_zhishu_rate);
        stock_day5_zhizhu_rate_text = (TextView) view.findViewById(R.id.day5_zhishu_rate);
        stock_day10_zhizhu_rate_text = (TextView) view.findViewById(R.id.day10_zhishu_rate);
        stock_day15_zhizhu_rate_text = (TextView) view.findViewById(R.id.day15_zhishu_rate);


        stock_day30_zhizhu_rate_text = (TextView) view.findViewById(R.id.day30_zhishu_rate);
        stock_day60_zhizhu_rate_text = (TextView) view.findViewById(R.id.day60_zhishu_rate);
        stock_day90_zhizhu_rate_text = (TextView) view.findViewById(R.id.day90_zhishu_rate);
        stock_day_year_zhizhu_rate_text = (TextView) view.findViewById(R.id.day_year_zhishu_rate);




    }
}
