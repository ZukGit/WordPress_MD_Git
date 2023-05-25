package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.Pause_Stock_Simulate_Event;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.bean.StockSimulate_BankBean;
import com.and.zmain_life.stock_node.Stock_NodeImpl;
import com.and.zmain_life.utils.RxBus;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import butterknife.BindView;
import rx.functions.Action1;


public class Stock_Simulate_Fragment extends BaseFragment {
    @BindView(R.id.simulate_bank_rv)
    RecyclerView SimulateBankRecyclerView;

    StockSimulateTradeAdapter simulateTradeAdapter;

    @BindView(R.id.stock_simulate_adduser_txt)
    TextView stock_simulate_adduser_txt;

    //  插入 当前标题日期显示的一些类型的股票到 模拟账户中
    // 比如 把当前所有涨停的股票加入一个今日日期的模拟账户中
    // 加入当日 接近跌停的股票到 今日账户中
    @BindView(R.id.stock_simulate_insert_dailystock_txt)
    TextView stock_simulate_insert_dailystock_txt;


    @BindView(R.id.stock_simulate_title_txt)
    TextView stock_simulate_title_txt;


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(simulateTradeAdapter != null && simulateTradeAdapter.mTitleBuyStockDialogView != null){
            simulateTradeAdapter.mTitleBuyStockDialogView.dismiss();
        }
        if(simulateTradeAdapter != null && simulateTradeAdapter.mSellStockDialogView != null){
            simulateTradeAdapter.mSellStockDialogView.dismiss();
        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.simulate_main_layout;
    }


/*    private void initView() {
        mOpenRecordRv = (RecyclerView) findViewById(R.id.simulate_bank_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mOpenRecordRv.setLayoutManager(manager);
        StockSimulateTradeAdapter addapter = new StockSimulateTradeAdapter();
        mOpenRecordRv.setAdapter(addapter);
        addapter.setDate(new SimulateStockModel().getData());
        //  给 这个 StockSimulateTradeAdapter 设置 数据源

    }*/




    //  用于显示 当前 日期 插入 用户想要插入的一些 股票的列表
    private void showDailyStockInsertDialog(View clickview) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.simulate_dailystock_insert_dialog_layout_2, null);

        TextView cancel =view.findViewById(R.id.stockdaily_dialog_cancel_text);
        TextView sure =view.findViewById(R.id.stockdaily_dialog_sure_text);
        sure.setVisibility(View.INVISIBLE);



        TextView dailystock_title_text =view.findViewById(R.id.stockdaily_dialog_title_text);
        TextView up_stock_username_text  =view.findViewById(R.id.stockdaily_dialog_up_stock_username_text);
        TextView down_stock_username_text =view.findViewById(R.id.stockdaily_dialog_down_stock_username_text);


        TextView bigdeal_stock_username_text  =view.findViewById(R.id.stockdaily_dialog_bigdeal_stock_username_text);
        TextView zhenfu_stock_username_text =view.findViewById(R.id.stockdaily_dialog_zhenfu_stock_username_text);
        TextView changerate_stock_username_text  =view.findViewById(R.id.stockdaily_dialog_changerate_stock_username_text);



        TextView up_stock_count_text  =view.findViewById(R.id.stockdaily_dialog_up_stock_count_text);
        TextView down_stock_count_text =view.findViewById(R.id.stockdaily_dialog_down_stock_count_text);

        TextView time_title_text  =view.findViewById(R.id.stockdaily_dialog_time_text);


        Switch up_switch  =view.findViewById(R.id.stockdaily_dialog_down_stock_up_switchbutton);
        Switch down_switch  =view.findViewById(R.id.stockdaily_dialog_down_stock_down_switchbutton);
        Switch changerate_switch  =view.findViewById(R.id.stockdaily_dialog_changerate_stock_down_switchbutton);
        Switch bigdeal_switch  =view.findViewById(R.id.stockdaily_dialog_bigdeal_stock_down_switchbutton);
        Switch zhenfu_switch =view.findViewById(R.id.stockdaily_dialog_zhenfu_stock_down_switchbutton);



        Switch day3_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_day3_zhangfu_stock_switchbutton);
        Switch day3_down_pct_switch  =view.findViewById(R.id.dialog_diefu_day3_diefu_stock_switchbutton);

        Switch day5_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_day5_zhangfu_stock_switchbutton);
        Switch day5_down_pct_switch  =view.findViewById(R.id.dialog_diefu_day5_diefu_stock_switchbutton);

        Switch day10_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_day10_zhangfu_stock_switchbutton);
        Switch day10_down_pct_switch  =view.findViewById(R.id.dialog_diefu_day10_diefu_stock_switchbutton);


        Switch day30_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_day30_zhangfu_stock_switchbutton);
        Switch day30_down_pct_switch  =view.findViewById(R.id.dialog_diefu_day30_diefu_stock_switchbutton);

        Switch day90_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_dayseason_zhangfu_stock_switchbutton);
        Switch day90_down_pct_switch  =view.findViewById(R.id.dialog_diefu_dayseason_diefu_stock_switchbutton);


        Switch dayyear_up_pct_switch  =view.findViewById(R.id.dialog_zhenfu_dayyear_zhangfu_stock_switchbutton);
        Switch dayyear_down_pct_switch  =view.findViewById(R.id.dialog_diefu_dayyear_diefu_stock_switchbutton);






//        StockHolder.day_lastjson_timestamp
        String lastStockDailyStr =  StockHolder.day_lastjson_timestamp;
        if(lastStockDailyStr == null){
            Toast.makeText(getContext(),"数据正在准备中!请稍后!",Toast.LENGTH_SHORT).show();
            return ;

        }
        if(lastStockDailyStr.contains("_")){
            lastStockDailyStr =   lastStockDailyStr.replace("_",".");

        }

        String dayJsonTime =   StockHolder.day_lastjson_timestamp;
        dayJsonTime = dayJsonTime.replace("_","").replace(".","").trim();

        String nowDateStr = getTimeStamp_YYYY_MM_DD().replace("_","").replace(".","").trim();

        boolean isJsonDateMathNowDate = false;
        if(nowDateStr.equals(dayJsonTime)){
            isJsonDateMathNowDate = true;
        }

        String dateTip = "";
        if(isJsonDateMathNowDate){
            dateTip="(匹配股票列表)";
            time_title_text.setText("今日日期:"+getTimeStamp_YYYY_MM_DD()+" "+dateTip);
            time_title_text.setTextColor(Color.parseColor("#FF3030"));
        }else{
            dateTip="(不匹配股票列表"+")";
            time_title_text.setText("今日日期:"+getTimeStamp_YYYY_MM_DD()+" "+dateTip);
            time_title_text.setTextColor(Color.parseColor("#32CD32"));

        }


        dailystock_title_text.setText("创建"+lastStockDailyStr+"日涨跌账户提示");



        changerate_stock_username_text.setText(changerate_stock_username_text.getText().toString().replace("xxx",lastStockDailyStr));
        bigdeal_stock_username_text.setText(bigdeal_stock_username_text.getText().toString().replace("xxx",lastStockDailyStr));
        zhenfu_stock_username_text.setText(zhenfu_stock_username_text.getText().toString().replace("xxx",lastStockDailyStr));



        up_stock_username_text.setText("涨停账户名:"+lastStockDailyStr+"涨↑");
        up_stock_username_text.setTextColor(Color.parseColor("#FF3030"));
        up_stock_count_text.setTextColor(Color.parseColor("#FF3030"));

        down_stock_username_text.setText("跌停账户名:"+lastStockDailyStr+"跌↓");



        down_stock_username_text.setTextColor(Color.parseColor("#32CD32"));
        down_stock_count_text.setTextColor(Color.parseColor("#32CD32"));

        ArrayList<Stock_NodeImpl> stock_up_arr = null;
            ArrayList<Stock_NodeImpl> stock_down_arr = null;

        ArrayList<Stock_NodeImpl> changerate_arr = null;
        ArrayList<Stock_NodeImpl> bigdeal_arr = null;
        ArrayList<Stock_NodeImpl> zhenfu_arr = null;

            int matchStockSize = 0 ;

if(StockHolder.mStock_NodeImplArr_Type_Map != null){

 stock_up_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_UP);
  stock_down_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_DOWN);
    // 换手率
     changerate_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate);


    // 成交量      public   double RiXianXingQingvShiJianWeiXu_amount;     //  日线行情 成交额
     bigdeal_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_BigDeal);

    // 振幅
    zhenfu_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ZhenFu);


    if(changerate_arr != null){
        matchStockSize += changerate_arr.size();
    }

    if(bigdeal_arr != null){

        matchStockSize += bigdeal_arr.size();
    }

    if(zhenfu_arr != null){

        matchStockSize += bigdeal_arr.size();
    }


    if(stock_up_arr != null){
        matchStockSize += stock_up_arr.size();

        up_stock_count_text.setText("涨停:"+stock_up_arr.size());
        sure.setVisibility(View.VISIBLE);
    }

    if(stock_down_arr != null){
        matchStockSize += stock_down_arr.size();
        down_stock_count_text.setText("跌停:"+stock_down_arr.size());
        sure.setVisibility(View.VISIBLE);
    }

}

      final   int mFinalStockSize = matchStockSize ;

// 如果当前的 交易不是 今日的话  那么  提示用户 无法批量创建



        // 获取 跌停 和 涨停 的 股票的 类型



        final Dialog dialog= builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Switch up_switch  =view.findViewById(R.id.stockdaily_dialog_down_stock_up_switchbutton);
//                Switch down_switch  =view.findViewById(R.id.stockdaily_dialog_down_stock_down_switchbutton);
//                Switch changerate_switch  =view.findViewById(R.id.stockdaily_dialog_changerate_stock_down_switchbutton);
//                Switch bigdeal_switch  =view.findViewById(R.id.stockdaily_dialog_bigdeal_stock_down_switchbutton);
//                Switch zhenfu_switch =view.findViewById(R.id.stockdaily_dialog_zhenfu_stock_down_switchbutton);


                StringBuilder tipSB = new StringBuilder();
                boolean up_switch_bool =  up_switch.isChecked();
                boolean down_switch_bool =  down_switch.isChecked();
                boolean changerate_switch_bool =  changerate_switch.isChecked();
                boolean bigdeal_switch_bool =  bigdeal_switch.isChecked();
                boolean zhenfu_switch_bool =  zhenfu_switch.isChecked();


                boolean  day3_up_flag  =  day3_up_pct_switch.isChecked();
                boolean  day3_down_flag  =  day3_down_pct_switch.isChecked();
                boolean  day5_up_flag  =  day5_up_pct_switch.isChecked();
                boolean  day5_down_flag  =  day5_down_pct_switch.isChecked();
                boolean  day10_up_flag  =  day10_up_pct_switch.isChecked();
                boolean  day10_down_flag  =  day10_down_pct_switch.isChecked();
                boolean  day30_up_flag  =  day30_up_pct_switch.isChecked();
                boolean  day30_down_flag  =  day30_down_pct_switch.isChecked();
                boolean  day90_up_flag  =  day90_up_pct_switch.isChecked();
                boolean  day90_down_flag  =  day90_down_pct_switch.isChecked();
                boolean  dayyear_up_flag  =  dayyear_up_pct_switch.isChecked();
                boolean  dayyear_down_flag  =  dayyear_down_pct_switch.isChecked();




                ArrayList<Stock_NodeImpl>      stock_up_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_UP);
                ArrayList<Stock_NodeImpl>         stock_down_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_DOWN);

                // 换手率
                ArrayList<Stock_NodeImpl>         changerate_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate);


                // 成交量      public   double RiXianXingQingvShiJianWeiXu_amount;     //  日线行情 成交额
                ArrayList<Stock_NodeImpl>         bigdeal_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_BigDeal);

                // 振幅
                ArrayList<Stock_NodeImpl>         zhenfu_arr =   StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ZhenFu);


                ArrayList<Stock_NodeImpl>  Day3_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day3_UP);
                ArrayList<Stock_NodeImpl>  Day3_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day3_DOWN);
                ArrayList<Stock_NodeImpl>  Day5_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day5_UP);
                ArrayList<Stock_NodeImpl>  Day5_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day5_DOWN);
                ArrayList<Stock_NodeImpl>  Day10_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day10_UP);
                ArrayList<Stock_NodeImpl>  Day10_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day10_DOWN);
                ArrayList<Stock_NodeImpl>  Day30_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day30_UP);
                ArrayList<Stock_NodeImpl>  Day30_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day30_DOWN);
                ArrayList<Stock_NodeImpl>  Day90_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day90_UP);
                ArrayList<Stock_NodeImpl>  Day90_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_Day90_DOWN);
                ArrayList<Stock_NodeImpl>  DayYear_UP_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_DayYear_UP);
                ArrayList<Stock_NodeImpl>  DayYear_DOWN_pct_arr = StockHolder.mStock_NodeImplArr_Type_Map.get(StockHolder.Stock_TYPE_ChangeRate_DayYear_DOWN);










                // 5个switch 都是 false 的 话 直接 diss
                if(!up_switch_bool && !down_switch_bool && !changerate_switch_bool
                && !bigdeal_switch_bool && !zhenfu_switch_bool &&
                 !day3_up_flag && !day3_down_flag &&    !day5_up_flag && !day5_down_flag  &&
                   !day10_up_flag && !day10_down_flag &&    !day30_up_flag && !day30_down_flag  &&
                        !day90_up_flag && !day90_down_flag &&    !dayyear_up_flag && !dayyear_down_flag
                ){
                    dialog.dismiss();
                    return;

                }





                if(mFinalStockSize == 0){
                    Toast.makeText(getContext(), "当前日期【"+StockHolder.day_lastjson_timestamp+"】匹配到的股票列表为空!\n无法创建账户", Toast.LENGTH_SHORT).show();
                    //
                    dialog.dismiss();
                    return;

                }

                String lastStockDailyStr =  StockHolder.day_lastjson_timestamp;
                if(lastStockDailyStr.contains("_")){
                    lastStockDailyStr =   lastStockDailyStr.replace("_",".");

                }








                // 振幅 的逻辑
                if(zhenfu_switch_bool   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+"振幅↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+"振幅↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_zhenfu_user = null;

                        if(zhenfu_arr != null && zhenfu_arr.size() > 0){

                            daily_zhenfu_user =        createUserWithStockList(zhenfu_arr ,StockHolder.day_lastjson_timestamp,"振幅↑");

                            if(daily_zhenfu_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+"振幅↑")){

                                    StockHolder.mBankBean.addUser(daily_zhenfu_user);
                                    tipSB.append("前振幅账户【"+lastStockDailyStr+"振幅↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("当前振幅账户【"+lastStockDailyStr+"振幅↑】不被选中\n");
                }



                // 成交额 的逻辑
                if(bigdeal_switch_bool  ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+"成交额↑")){

                        tipSB.append("前成交额账户【"+lastStockDailyStr+"成交额↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_bigdeal_user = null;

                        if(bigdeal_arr != null && bigdeal_arr.size() > 0){

                            daily_bigdeal_user =        createUserWithStockList(bigdeal_arr,StockHolder.day_lastjson_timestamp,"成交额↑");

                            if(daily_bigdeal_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+"成交额↑")){

                                    StockHolder.mBankBean.addUser(daily_bigdeal_user);
                                    tipSB.append("前成交额账户【"+lastStockDailyStr+"成交额↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("当前成交额账户【"+lastStockDailyStr+"成交额↑】不被选中\n");
                }



                // 换手率的逻辑
                if(changerate_switch_bool ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+"换手率↑")){

                        tipSB.append("前换手率账户【"+lastStockDailyStr+"换手率↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_changerate_user = null;

                        if(changerate_arr != null && changerate_arr.size() > 0){

                            daily_changerate_user =        createUserWithStockList(changerate_arr,StockHolder.day_lastjson_timestamp,"换手率↑");

                            if(daily_changerate_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+"换手率↑")){

                                    StockHolder.mBankBean.addUser(daily_changerate_user);
                                    tipSB.append("前换手率账户【"+lastStockDailyStr+"换手率↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("当前跌停账户【"+lastStockDailyStr+"跌↓】不被选中\n");
                }



                // 跌停的逻辑
                if(down_switch_bool){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+"跌↓")){

                        tipSB.append("前跌停账户【"+lastStockDailyStr+"跌↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_down_user = null;

                        if(stock_down_arr != null && stock_down_arr.size() > 0){

                            daily_down_user =        createUserWithStockList(stock_down_arr,StockHolder.day_lastjson_timestamp,"跌↓");

                            if(daily_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+"跌↓")){

                                    StockHolder.mBankBean.addUser(daily_down_user);
                                    tipSB.append("前跌停账户【"+lastStockDailyStr+"跌↓】创建成功\n");

                                }
                            }
                        }


                    }



                } else {

                    tipSB.append("当前跌停账户【"+lastStockDailyStr+"跌↓】不被选中\n");
                }



                if(up_switch_bool){


                    if(StockHolder.mBankBean.isExistUser(lastStockDailyStr+"涨↑")   ){
//                        Toast.makeText(getContext(), "\n当前涨停账户【"+lastStockDailyStr+"涨↑】已存在!\n前跌停账户【"+lastStockDailyStr+"跌↓】已存在!\n无法创建账户", Toast.LENGTH_SHORT).show();
                        //
//                        dialog.dismiss();
//                        return;

                        tipSB.append("当前涨停账户【"+lastStockDailyStr+"涨↑】已存在!\n");


                    }else{  // 当前 不存在  要创建的 up账户


                        StockSimulate_BankBean.StockSimulate_UserBean daily_up_user = null;

                        if(stock_up_arr != null && stock_up_arr.size() > 0){

                            daily_up_user =  createUserWithStockList(stock_up_arr,StockHolder.day_lastjson_timestamp,"涨↑");

                            if(daily_up_user != null){
                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+"涨↑")){
                                    StockHolder.mBankBean.addUser(daily_up_user);
                                    tipSB.append("当前涨停账户【"+lastStockDailyStr+"涨↑】创建成功\n");

                                }


                            }

                        }




                    }



                }else{

                    tipSB.append("当前涨停账户【"+lastStockDailyStr+"涨↑】不被选中\n");

                }




                // day3_up_flag =================================== 的逻辑
                if(day3_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".3d↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".3d↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_3day_up_user = null;

                        if(Day3_UP_pct_arr != null && Day3_UP_pct_arr.size() > 0){

                            daily_3day_up_user =        createUserWithStockList(Day3_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".3d↑");

                            if(daily_3day_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".3d↑")){

                                    StockHolder.mBankBean.addUser(daily_3day_up_user);
                                    tipSB.append("前3d_up账户【"+lastStockDailyStr+".3d↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前3d_up账户【"+lastStockDailyStr+".3d_up↑】不被选中\n");
                }



                // day3_down_flag =============================================  的逻辑
                if(day3_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".3d↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".3d↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_3day_down_user = null;

                        if(Day3_DOWN_pct_arr != null && Day3_DOWN_pct_arr.size() > 0){

                            daily_3day_down_user =        createUserWithStockList(Day3_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".3d↓");

                            if(daily_3day_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".3d↓")){

                                    StockHolder.mBankBean.addUser(daily_3day_down_user);
                                    tipSB.append("前3d_down账户【"+lastStockDailyStr+".3d↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前3d_down账户【"+lastStockDailyStr+".3d↓】不被选中\n");
                }


                // day5_up_flag =================================== 的逻辑
                if(day5_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".5d↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".5d↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_5day_up_user = null;

                        if(Day5_UP_pct_arr != null && Day5_UP_pct_arr.size() > 0){

                            daily_5day_up_user =        createUserWithStockList(Day5_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".5d↑");

                            if(daily_5day_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".5d↑")){

                                    StockHolder.mBankBean.addUser(daily_5day_up_user);
                                    tipSB.append("前5d_up账户【"+lastStockDailyStr+".5d↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("当前振幅账户【"+lastStockDailyStr+".5d_up↑】不被选中\n");
                }



                // day5_down_flag =============================================  的逻辑
                if(day5_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".5d↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".5d↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_5day_down_user = null;

                        if(Day5_DOWN_pct_arr != null && Day5_DOWN_pct_arr.size() > 0){

                            daily_5day_down_user =        createUserWithStockList(Day5_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".5d↓");

                            if(daily_5day_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".5d↓")){

                                    StockHolder.mBankBean.addUser(daily_5day_down_user);
                                    tipSB.append("前5d_down账户【"+lastStockDailyStr+".5d↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前5d_down账户【"+lastStockDailyStr+".5d↓】不被选中\n");
                }



                // day10_up_flag =================================== 的逻辑
                if(day10_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".10d↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".10d↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_10day_up_user = null;

                        if(Day10_UP_pct_arr != null && Day10_UP_pct_arr.size() > 0){

                            daily_10day_up_user =        createUserWithStockList(Day10_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".10d↑");

                            if(daily_10day_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".10d↑")){

                                    StockHolder.mBankBean.addUser(daily_10day_up_user);
                                    tipSB.append("前10d_up账户【"+lastStockDailyStr+".10d↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前10d_up账户【"+lastStockDailyStr+".10d_up↑】不被选中\n");
                }



                // day10_down_flag =============================================  的逻辑
                if(day10_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".10d↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".10d↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_10day_down_user = null;

                        if(Day10_DOWN_pct_arr != null && Day10_DOWN_pct_arr.size() > 0){

                            daily_10day_down_user =        createUserWithStockList(Day10_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".10d↓");

                            if(daily_10day_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".10d↓")){

                                    StockHolder.mBankBean.addUser(daily_10day_down_user);
                                    tipSB.append("前10d_down账户【"+lastStockDailyStr+".10d↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前10d_down账户【"+lastStockDailyStr+".10d↓】不被选中\n");
                }


                // day30_up_flag =================================== 的逻辑
                if(day30_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".30d↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".30d↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_30day_up_user = null;

                        if(Day30_UP_pct_arr != null && Day30_UP_pct_arr.size() > 0){

                            daily_30day_up_user =        createUserWithStockList(Day30_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".30d↑");

                            if(daily_30day_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".30d↑")){

                                    StockHolder.mBankBean.addUser(daily_30day_up_user);
                                    tipSB.append("前30d_up账户【"+lastStockDailyStr+".30d↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前30d_up账户【"+lastStockDailyStr+".30d_up↑】不被选中\n");
                }



                // day30_down_flag =============================================  的逻辑
                if(day30_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".30d↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".30d↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_30day_down_user = null;

                        if(Day30_DOWN_pct_arr != null && Day30_DOWN_pct_arr.size() > 0){

                            daily_30day_down_user =        createUserWithStockList(Day30_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".30d↓");

                            if(daily_30day_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".30d↓")){

                                    StockHolder.mBankBean.addUser(daily_30day_down_user);
                                    tipSB.append("前30d_down账户【"+lastStockDailyStr+".30d↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前30d_down账户【"+lastStockDailyStr+".30d↓】不被选中\n");
                }



                // day90_up_flag =================================== 的逻辑
                if(day90_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".90d↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".90d↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_90day_up_user = null;

                        if(Day90_UP_pct_arr != null && Day90_UP_pct_arr.size() > 0){

                            daily_90day_up_user =        createUserWithStockList(Day90_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".90d↑");

                            if(daily_90day_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".90d↑")){

                                    StockHolder.mBankBean.addUser(daily_90day_up_user);
                                    tipSB.append("前90d_up账户【"+lastStockDailyStr+".90d↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前90d_up账户【"+lastStockDailyStr+".90d_up↑】不被选中\n");
                }



                // day90_down_flag =============================================  的逻辑
                if(day90_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".90d↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".90d↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_90day_down_user = null;

                        if(Day90_DOWN_pct_arr != null && Day90_DOWN_pct_arr.size() > 0){

                            daily_90day_down_user =        createUserWithStockList(Day90_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".90d↓");

                            if(daily_90day_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".90d↓")){

                                    StockHolder.mBankBean.addUser(daily_90day_down_user);
                                    tipSB.append("前90d_down账户【"+lastStockDailyStr+".90d↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前90d_down账户【"+lastStockDailyStr+".90d↓】不被选中\n");
                }




                // dayYear_up_flag =================================== 的逻辑
                if(dayyear_up_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".年↑")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".年↑】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_Yearay_up_user = null;

                        if(DayYear_UP_pct_arr != null && DayYear_UP_pct_arr.size() > 0){

                            daily_Yearay_up_user =        createUserWithStockList(DayYear_UP_pct_arr ,StockHolder.day_lastjson_timestamp,".年↑");

                            if(daily_Yearay_up_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".年↑")){

                                    StockHolder.mBankBean.addUser(daily_Yearay_up_user);
                                    tipSB.append("前Year_up账户【"+lastStockDailyStr+".年↑】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前Year_up账户【"+lastStockDailyStr+".年↑】不被选中\n");
                }



                // dayYear_down_flag =============================================  的逻辑
                if(dayyear_down_flag   ){

                    if( StockHolder.mBankBean.isExistUser(lastStockDailyStr+".年↓")){

                        tipSB.append("前振幅账户【"+lastStockDailyStr+".年↓】已存在!无法创建账户\n");

                    }else{  // 创建  跌停账户
                        StockSimulate_BankBean.StockSimulate_UserBean daily_Yearay_down_user = null;

                        if(DayYear_DOWN_pct_arr != null && DayYear_DOWN_pct_arr.size() > 0){

                            daily_Yearay_down_user =        createUserWithStockList(DayYear_DOWN_pct_arr ,StockHolder.day_lastjson_timestamp,".年↓");

                            if(daily_Yearay_down_user != null){

                                if(!StockHolder.mBankBean.isExistUser(lastStockDailyStr+".年↓")){

                                    StockHolder.mBankBean.addUser(daily_Yearay_down_user);
                                    tipSB.append("前Year_down账户【"+lastStockDailyStr+".年↓】创建成功\n");

                                }
                            }
                        }


                    }


                } else {

                    tipSB.append("前Year_down账户【"+lastStockDailyStr+".年↓】不被选中\n");
                }




                Toast.makeText(getContext(), tipSB.toString(), Toast.LENGTH_SHORT).show();


//                if(daily_down_StockSize > 0 ||  daily_up_StockSize >0 ){
//                    simulateTradeAdapter.notifyDataChange_WithJson();
//                    Toast.makeText(getContext(), "创建"+StockHolder.day_lastjson_timestamp+" Daily账户成功!\n涨停账户股票【"+daily_up_StockSize+"】\n跌停账户股票【"+daily_down_StockSize+"】", Toast.LENGTH_SHORT).show();
//
//                }else{
//
//                    Toast.makeText(getContext(), "创建"+StockHolder.day_lastjson_timestamp+" Daily账户失败!\n涨停账户股票【"+daily_up_StockSize+"】\n跌停账户股票【"+daily_down_StockSize+"】", Toast.LENGTH_SHORT).show();
//
//                }


                dialog.dismiss();
                return;




            }
        });

    }


    StockSimulate_BankBean.StockSimulate_UserBean  createUserWithStockList(ArrayList<Stock_NodeImpl> stockList , String jsonDayStr  ,String tagName){

        StockSimulate_BankBean.StockSimulate_UserBean  dailyUser = new     StockSimulate_BankBean.StockSimulate_UserBean();
        String userName = jsonDayStr;

            userName = jsonDayStr.replace("_",".")+tagName;



        dailyUser.setInitsimulatemoney(99999999D);  // 默认资产 一个亿
        dailyUser.setRestsimuletemoney(99999999D);  // 默认资产 一个亿  可用资金
        dailyUser.setImageUserBean(false);


        String YYYYMMDD = jsonDayStr.replace("_","").replace(".","").trim();

        dailyUser.setUsertitle(userName);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(YYYYMMDD);
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
            dailyUser.setDaytime( df.format(dateTime));
        } catch (Exception e) {
            dailyUser.setDaytime(getTimeStamp_YYYY_MM_DD());
            e.printStackTrace();
        }

            for (int i = 0; i < stockList.size(); i++) {
                double buyStockMoney = 100000D;  // 每只股票允许买10w
                Stock_NodeImpl stockItem = stockList.get(i);
                double stockPrice = stockItem.price;
                String stockTsCode = stockItem.ts_code;
                stockTsCode = stockTsCode.replace(".SH", "").replace(".SZ", "").replace(".", "").trim();

                String stockName = stockItem.name;
                if (stockPrice <= 0 || "".equals(stockTsCode) || stockTsCode.length() != 6) {
                    // 如果 当前的 股价 没有得到的话 那么是0  那么跳过这只股票
                    continue;
                }


                StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean simulateStock = new StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean();

                simulateStock.setStocktscode(stockTsCode.trim());

                simulateStock.setStockcostforone(stockPrice);  // 成本
                simulateStock.setStockname(stockName.trim());

                int buyStockCount = (int) Math.floor(buyStockMoney / stockPrice);  // 10w能买到的股票的数量
                simulateStock.setStockkeepcount(buyStockCount);
                simulateStock.setStockbuytime(dailyUser.getDaytime()); //  批量增加的user的购买时间和 user一致
                synchronized (this) {
//                double curRestMoney = dailyUser.getRestsimuletemoney();
//                double newRestMoney = curRestMoney - (buyStockCount * stockPrice);

                dailyUser.addNewStock(simulateStock);  // 这里 已经 计算 剩余的钱的操作了
//                print("dailyUser[" + dailyUser.getUsertitle() + "] oldRestMoney=[" + curRestMoney + "]  newRestMoney[" + newRestMoney + "]  ZResetMoney=[" + dailyUser.getRestsimuletemoney() + "]  buyStockCount[" + buyStockCount + "] stockPrice[" + stockPrice + "]  cost[" + ((buyStockCount * stockPrice)) + "]");



                }


            }

        return dailyUser;



    }

    private void print(String log) {
        android.util.Log.i("Stock_Simulate_Fragment",log);

    }

    private void showCreateUserBeanDialog(View clickview) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.simulate_adduser_dialog_edittext_layout, null);
        TextView cancel =view.findViewById(R.id.simulate_dialog_cancel_text);
        TextView sure =view.findViewById(R.id.simulate_dialog_sure_text);
        final TextView daytext =view.findViewById(R.id.today_dialog_text);
        daytext.setText("今日日期:"+" "+getTimeStamp_YYYY_MM_DD());
        final EditText initmoney_edittext =view.findViewById(R.id.simulate_initmoney_edittext);

        // 用户名称
        final EditText user_title_edit =view.findViewById(R.id.user_title_edittext);

        user_title_edit.setHint("模拟账户名称(默认:"+getTimeStamp_YY_MM_DD_Line()+"模拟)");
        final Dialog dialog= builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String initMoney = initmoney_edittext.getText().toString();
//                Toast.makeText(getContext(), "cancel_"+initMoney, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String initMoney = initmoney_edittext.getText().toString();
                String mUserTitle = user_title_edit.getText().toString();
                if(mUserTitle == null || "".equals(mUserTitle.trim())){
                    mUserTitle = getTimeStamp_YY_MM_DD_Line()+"模拟";

                }

                String daystr = daytext.getText().toString().replace("今日日期:","");
                if(StockHolder.isDoubleNumeric(initMoney)){
                    double initMoneyInt = Double.parseDouble(initMoney);

                    StockHolder.mBankBean.addUser(initMoneyInt,daystr,mUserTitle);
                    simulateTradeAdapter.notifyDataChange_WithJson();
                    Toast.makeText(getContext(), "成功创建 初始化资产["+initMoneyInt+"]用户", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), "输入字符"+initMoney+"不是数值无法添加模拟User", Toast.LENGTH_SHORT).show();
                }


                dialog.dismiss();
            }
        });
    }

    // 标题长按  事件 处理 打开 一个  Dialog  用来选择  daily 的 json 文件
    private boolean titleLongClickMethod(View view) {


        final AlertDialog.Builder jsonSelectedDiaglog = new AlertDialog.Builder(getContext());
        int daily_json_file_count = (StockHolder.day_timestamp_jsonFileArr == null ? 0:StockHolder.day_timestamp_jsonFileArr.size());

        jsonSelectedDiaglog.setTitle("daily_json选择框【"+daily_json_file_count+"】:");//文字


        jsonSelectedDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        jsonSelectedDiaglog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        // Spinner----Begin
        ArrayList<String>  spinnerTipArr = new ArrayList<String>();
        spinnerTipArr.clear();

        for (int i = 0; i < daily_json_file_count; i++) {
           File jsonFile =  StockHolder.day_timestamp_jsonFileArr.get(i);
           String file_name = jsonFile.getName();
            spinnerTipArr.add(file_name);
        }

        // 把 day_2022_0205.json 转为 20220205
        spinnerTipArr.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String num1Str =  o1.replace(".json","").replace("day","").replace("_","").trim();
                String num2Str =   o2.replace(".json","").replace("day","").replace("_","").trim();
//                print("SSFragment SSkey num1Str="+num1Str+"    num2Str="+num2Str);
                if(DataHolder.isNumeric(num1Str) && DataHolder.isNumeric(num2Str)){
                    int num1 = Integer.parseInt(num1Str);
                    int num2 = Integer.parseInt(num2Str);
                    if(num1 == num2 ){
                        return  0 ;
                    } else if(num1 > num2){
                        return  -1 ;
                    }
                    return  1 ;
                }
                return o1.compareTo(o2);
            }
        });

        final  CharSequence[] items = new  CharSequence[spinnerTipArr.size()];
        int matchedJsonIndex = -1;
        for (int i = 0; i < spinnerTipArr.size(); i++) {
            int index_num = i+1;
            String little_index_str = DataHolder.calculLittleDigital(index_num+"");
            String categoryKey = spinnerTipArr.get(i);

//          String curLastJsonFileIntStr =   StockHolder.day_last_jsonFile.getName().replace(".json","").replace("day","").replace("_","").trim();

            if(categoryKey.startsWith(StockHolder.day_last_jsonFile.getName())){
                matchedJsonIndex = i ;
            }
            items[i] = little_index_str+spinnerTipArr.get(i)+"";
            print("StockSimulate Title   Topkey items["+i+"] = "+items[i]  );
        }




        jsonSelectedDiaglog.setSingleChoiceItems(items,matchedJsonIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CharSequence categoryName = DataHolder.clearLittleDigital(items[which]+"");
                String userSelectedJsonName = categoryName+"";  //all_7



               // public static ArrayList<File> day_timestamp_jsonFileArr;
//                public static File  day_last_jsonFile ; // 时间上最新的 那个  day_ 开头的 时间戳的 文件
//                public static String day_lastjson_timestamp;   // 时间上最新的那个文件的 时间戳  要显示的就是那个文件的内容
//                public static String day_lastjson_Year;
//                public static String day_lastjson_Month;
//                public static String day_lastjson_Day;

//                public static ArrayList<File> day_timestamp_xlsxFileArr;     // 以 day_20200202.xlsx 这样形式命名的文件的集合
//                public static File  day_last_xlsxFile ; // 时间上最新的 那个  day_ 开头的 时间戳的 文件
//                public static String day_lastxlsx_timestamp;   // 时间上最新的那个文件的 时间戳  要显示的就是那个文件的内容
//                public static String day_lastxlsx_Year;
//                public static String day_lastxlsx_Month;
//                public static String day_lastxlsx_Day;

                // 五个 属性 要更改

               //  依据选中的 名称虫 ArrayList<File> 选中  day_last_jsonFile
              File selectedJsonFile =   StockHolder.calculateSelectedJsonFile(userSelectedJsonName);

              if(selectedJsonFile != null && selectedJsonFile.exists()){
                  StockHolder.day_last_jsonFile = selectedJsonFile;
                  File lastXlsxFile = new File(selectedJsonFile.getAbsolutePath().replace(".json",".xlsx"));


                  if(lastXlsxFile.exists()){
                      StockHolder.day_last_xlsxFile = new File(selectedJsonFile.getAbsolutePath().replace(".json",".xlsx"));
                  }


                  String lastDayFileName = selectedJsonFile.getName();
                  String onlyTimeStampStr = lastDayFileName.replace(".json","").replace("day_","").replace("_","");
                  StockHolder.day_lastjson_timestamp =       onlyTimeStampStr;
                  StockHolder.day_lastxlsx_timestamp = onlyTimeStampStr;


                  if(StockHolder.isNumeric(onlyTimeStampStr) && onlyTimeStampStr.length() == 8){

                      StockHolder.day_lastjson_Year = onlyTimeStampStr.substring(0,4);
                      StockHolder.day_lastjson_Month = onlyTimeStampStr.substring(4,6);
                      StockHolder.day_lastjson_Day = onlyTimeStampStr.substring(6,8);

                      StockHolder.day_lastxlsx_Year = onlyTimeStampStr.substring(0,4);
                      StockHolder.day_lastxlsx_Month = onlyTimeStampStr.substring(4,6);
                      StockHolder.day_lastxlsx_Day = onlyTimeStampStr.substring(6,8);






                      // 更新 json 数据
                      new Thread(new Runnable() {
                          @Override
                          public void run() {
                              StockHolder.TScode_List.clear();
                              StockHolder.TScode_List = new ArrayList<String>();
                              StockHolder.GuPiaoLieBiao_NodeJson_Str = null;   // 把当前已经读取到的json 置空 重新读取
                              // 重置
                              StockHolder.isShowWindows_For_CommonFragment_When_Dismiss = false;
                              StockHolder.StockMarket_Node_ID_Num = 0;
                              if(StockHolder.mStock_NodeImplArr_Type_Map != null){
                                  StockHolder.mStock_NodeImplArr_Type_Map.clear();
                              }

                              StockHolder.Root_StockNodeImpl = new Stock_NodeImpl(0, -1, "All",0);
                              StockHolder.init_stock(false);
                              StockHolder.Stock_NodeImpl_Root =StockHolder.generate_gupiaoliebiao_withZMainStockDir(getActivity());


                          }
                      }).start();



                      simulateTradeAdapter.notifyDataSetChanged();

                      // 重新以指定的 day_json 文件来作为解析的基础
                      if(stock_simulate_title_txt != null) {
                          stock_simulate_title_txt.setText("CC" + StockHolder.day_lastjson_timestamp + "-Stock-Simu");

                      }
                  }


              }


                Toast.makeText(getContext(), "用户选中的daily-json文件:"+userSelectedJsonName+"  selectedJsonFile:"+StockHolder.day_last_jsonFile+"  day_lastjson_timestamp="+StockHolder.day_lastjson_timestamp, Toast.LENGTH_SHORT).show();



                    // 依据 这个  ArrayList<File> 重新生成数据
                if(SimulateTitle_Dialog != null){
                    SimulateTitle_Dialog.dismiss();
                }

            }
        });


        SimulateTitle_Dialog =   jsonSelectedDiaglog.show();

        return false;
    }

    private  Dialog SimulateTitle_Dialog;
    private void showOrHidenDetail(View view) {

        StockHolder.isShowForSimulateDetail = !StockHolder.isShowForSimulateDetail;
        StockHolder.mBankBean.setAllUserShowOrHidden(StockHolder.isShowForSimulateDetail);

        if(StockHolder.isShowForSimulateDetail && StockHolder.isGuPiaoLieBiao_FInish_MapInit_Flag){
            StockHolder.mBankBean.refreshBankStockNowPriceWithJson();
        }

        StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);

        simulateTradeAdapter.notifyDataChange_NoJson();

        StockHolder.check_Zhishu(getContext());

    }

    @Override
    protected void init() {


/*        if(StockHolder.mBankBean  == null){
//                    StockHolder.mBankBean = new StockSimulate_BankBean();
//                    StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());
            // 初始化  模拟股票数据的 JSON 文件
            // 从 图片 读取 Stock 出来
            StockHolder.initSimulateStockWithJson();
            StockHolder.initSimulateStockWithImage();
            StockHolder.mBankBean.refreshBankStockNowPriceWithSina(null,null);

        }*/




        LinearLayoutManager mLinearManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        SimulateBankRecyclerView.setLayoutManager(mLinearManager);

        simulateTradeAdapter = new StockSimulateTradeAdapter(getContext());
        SimulateBankRecyclerView.setAdapter(simulateTradeAdapter);

        if(!StockHolder.isAlredyShowSimulateDetailWhenFirstOpen && StockHolder.mBankBean != null){
            StockHolder.mBankBean.setAllUserShowOrHidden(StockHolder.isAlredyShowSimulateDetailWhenFirstOpen);
            StockHolder.isAlredyShowSimulateDetailWhenFirstOpen = true;
        }


        if(StockHolder.mBankBean != null){

            StockHolder.mBankBean.initAllUserTime();
            simulateTradeAdapter.setSimulateUserListData(StockHolder.mBankBean.getSimulateuserlist());

        }else{


            if(StockHolder.mBankBean  == null){
//                    StockHolder.mBankBean = new StockSimulate_BankBean();
//                    StockHolder.mBankBean.setSimulateuserlist(new SimulateStockModel().getData());
                // 初始化  模拟股票数据的 JSON 文件
                // 从 图片 读取 Stock 出来
                // StockHolder.initSimulateStockWithJson();
                StockHolder.initSimulateStockWithImage();
                Log.i("RefreshX","StockHolder.mBankBean == null ");
                if(StockHolder.mBankBean != null){
                    StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),null);
                } else {

                    new CountDownTimer(5000, 200) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            if(StockHolder.mBankBean != null){
                                StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),null);
                            }
                        }
                    }.start();
                }


            }


        }


        stock_simulate_title_txt.setOnClickListener(this::showOrHidenDetail);

        stock_simulate_title_txt.setOnLongClickListener(this::titleLongClickMethod);

        stock_simulate_insert_dailystock_txt.setOnClickListener(this::showDailyStockInsertDialog);
        stock_simulate_adduser_txt.setOnClickListener(this::showCreateUserBeanDialog);



        if(StockHolder.day_lastxlsx_timestamp != null){
            stock_simulate_title_txt.setText(StockHolder.day_lastjson_timestamp+"-Stock-Simu");
        }

        if(StockHolder.mSimulateRequestqueue == null ){
            StockHolder.mSimulateRequestqueue = Volley.newRequestQueue(getContext());

            Log.i("RefreshX","Stock_Simulate_Fragment  init() C3");
            if(StockHolder.mBankBean != null){
                StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
                simulateTradeAdapter.notifyDataChange_WithJson();
            } else {

                new CountDownTimer(5000, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if(StockHolder.mBankBean != null){
                            StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
                            simulateTradeAdapter.notifyDataChange_WithJson();
                        }
   }
                }.start();
            }

        }







        RxBus.getDefault().toObservable(Pause_Stock_Simulate_Event.class).subscribe((Action1<Pause_Stock_Simulate_Event>) event -> {

            if (event.isPlayOrPause()) { //  进入
                StockHolder.isInSimulateFragment = true;
                StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
                startRefreshPriceWithSina();
                simulateTradeAdapter.notifyDataChange_NoJson();
Log.i("SimulateFragment","进入 event.isPlayOrPause()="+event.isPlayOrPause());
            } else {   // 离开  应该是这里了
                Log.i("SimulateFragment","离开 event.isPlayOrPause()="+event.isPlayOrPause());
                Log.i("RefreshX","Stock_Simulate_Fragment  init() D4");
                if(StockHolder.isInSimulateFragment){
                    StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
                    stopRefreshPriceWithSina();
                }

                StockHolder.isInSimulateFragment = false;  // 只在 第一次离开的时候 进行刷新一次
            }

        });













    }


// 7 20秒 间隔  重复请求
    static int sina_request_distance =  1000*5;
    public CountDownTimer  loopRefreshPrice_Timer ;


    public void stopRefreshPriceWithSina(){
        if(loopRefreshPrice_Timer != null){
            loopRefreshPrice_Timer.cancel();
        }


    }
    public void startRefreshPriceWithSina(){
if(loopRefreshPrice_Timer == null){
    loopRefreshPrice_Timer =    new CountDownTimer(sina_request_distance, 500) {
        @Override
        public void onTick(long millisUntilFinished) { }
        @Override
        public void onFinish() {
            StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
            loopRefreshPrice_Timer.cancel();
            loopRefreshPrice_Timer.start();
        }
    };
}


  loopRefreshPrice_Timer.start();
 }








    @Override
    public void onResume() {
        super.onResume();



        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {
            android.util.Log.i("clicpxxx"," StockSimulateFragment .curPage="+  MainFragment.curPage);

            if(StockHolder.day_lastxlsx_timestamp != null){
                stock_simulate_title_txt.setText(StockHolder.day_lastjson_timestamp+"-Stock-Simu");
            }
            Log.i("RefreshX","Stock_Sumulate_Fragment onResume() A1");
            StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
            simulateTradeAdapter.notifyDataChange_WithJson();

        }


        if(simulateTradeAdapter != null){
            simulateTradeAdapter.notifyDataChange_NoJson();
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
            android.util.Log.i("clicpxxx","setUserVisibleHint  SimulateFragment ");
            StockHolder.mBankBean.refreshBankStockNowPriceWithSina(getContext(),simulateTradeAdapter);
            if(simulateTradeAdapter != null) {
                simulateTradeAdapter.notifyDataChange_NoJson();
            }
        } else {
            //相当于Fragment的onPause
            if(simulateTradeAdapter != null){
                simulateTradeAdapter.notifyDataChange_NoJson();
            }

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


    static String getTimeStamp_YYYY_MM_DD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static String getTimeStamp_YY_MM_DD_Line() {

        SimpleDateFormat df = new SimpleDateFormat("yy_MM_dd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }




}
