package com.and.zmain_life.adapter;


import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;


public class StockSimulateViewHolderTitle extends RecyclerView.ViewHolder {

    TextView index_riqi_text;  // 1_1_日期:
    TextView daytimetext;  // 还要增加 [1_21] 标识是第几账户 共有几个账户
    TextView initmoney_text;   //  初始化金额
    TextView allworthmoney_text; // 总资产
    TextView allstockvalue_text;  // 当前所有股票市值
    TextView avaliablemoney_text; // 可用余额现金

    TextView buystock_text;    // 买股票
    TextView clearuser_usertitle_text;   // 清除账户
    TextView hide_show_stock_text; // 显示股票
    TextView   user_stock_cost ; // 花销成本
    TextView allstockworth_stockcount_tv; // 股票总市值字样以及包含股票个数
    TextView avaliablemoney_cangweirate_tv; //  余额[仓位:100%] 字样的 Title
    TextView today_profit_value_tv;   // 今日盈亏值
    TextView today_profit_rate_tv;   // 今日盈亏值百分比

    TextView benifitmoney_text;  // 利润亏损   总盈亏
    TextView all_profit_rate_tv;   // 总盈亏比:

    LinearLayout title3_linear1_layout ;




    public StockSimulateViewHolderTitle(View itemView) {
        super(itemView);
        daytimetext = (TextView) itemView.findViewById(R.id.title1_daytime_tv);
        if(daytimetext == null){
            Log.i("SimulateTradeAdapter","R.id.title1_daytime_tv == null ");
        }
        initmoney_text = (TextView) itemView.findViewById(R.id.title1_initmoney_tv);
        allworthmoney_text = (TextView) itemView.findViewById(R.id.title_allworth_money);
        allstockvalue_text = (TextView) itemView.findViewById(R.id.title_allstockvalue_tv);
        avaliablemoney_text = (TextView) itemView.findViewById(R.id.title_avaliablemoney_tv);
        benifitmoney_text = (TextView) itemView.findViewById(R.id.title_benifitmoney_tv);
        buystock_text = (TextView) itemView.findViewById(R.id.title_buystock_tv);
        clearuser_usertitle_text = (TextView) itemView.findViewById(R.id.title_clearuser_tv);
        hide_show_stock_text = (TextView) itemView.findViewById(R.id.title_hidestock_tv);
        index_riqi_text = (TextView) itemView.findViewById(R.id.title1_riqi_index_tv);
        allstockworth_stockcount_tv = (TextView) itemView.findViewById(R.id.title2_allstockworth_stockcount_tv);

        user_stock_cost = (TextView) itemView.findViewById(R.id.user_stock_cost);

        title3_linear1_layout =  (LinearLayout) itemView.findViewById(R.id.title3_linear1_layout);

        avaliablemoney_cangweirate_tv = (TextView) itemView.findViewById(R.id.title2_stock_cangwei_rate_tv);

        today_profit_value_tv = (TextView) itemView.findViewById(R.id.title3_today_profitvalue_tv);


        today_profit_rate_tv = (TextView) itemView.findViewById(R.id.title3_today_profitrate_tv);

        all_profit_rate_tv = (TextView) itemView.findViewById(R.id.title3_all_profitrate_tv);



    }
}
