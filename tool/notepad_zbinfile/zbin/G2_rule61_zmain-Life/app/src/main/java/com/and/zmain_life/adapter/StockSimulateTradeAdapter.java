package com.and.zmain_life.adapter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.bean.StockSimulate_BankBean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.StockSimulate_ZhiShu;
import com.and.zmain_life.bean.TimeUtil;
import com.and.zmain_life.node.StockNodeShareInfo;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

/**

 */

public class StockSimulateTradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<StockSimulate_BankBean.StockSimulate_UserBean> stockbeanlist;
    public  int itemCount = 0 ;

    DecimalFormat decimalFormat  ;
    // = new DecimalFormat("##,##0.00");

    public  StockSimulateTradeAdapter mSimulateAdapter;
    public Context mContext;

    public Dialog mTitleBuyStockDialogView;

    // 内容页面 点击 卖出 股票的 弹框
    public Dialog mSellStockDialogView;


    // 记录点击 1w 的 次数
    public int buy_1w_clicknum = 0;

    // 记录点击 10w 的 次数
    public int buy_10w_clicknum = 0;

    // 记录点击 100w 的 次数
    public int buy_100w_clicknum = 0;

    // 记录点击 1000w 的 次数    // 点击  半仓时就全部 全部清零  或者 diss时清零
    public int buy_1000w_clicknum = 0;




    //  三个 Map
    // Map<Position,Type>;  User 是 1   ， Content 是 2
    // Map<Position,StockSimulate_UserBean>;  User 是 1   ， Content 是 2
    // Map<Position,StockSimulate_UserStockBean>;  User 是 1   ， Content 是 2


    // 用户索引 依据  0,1,2,3,4,自然顺序排列

    // 放置位置从0 开始   以及 这个位置对应的 viewtype  类型
    // Interger 是 position 是唯一的
    Map<Integer, Integer> mPositionViewTypeMap;
    // 0,2,5,6,7,10 跳着的排列
    Map<Integer,StockSimulate_BankBean.StockSimulate_UserBean> mPositionUserBeanMap;
    Map<Integer,StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean> mPositionStockBeanMap;


    public  StockSimulateTradeAdapter(Context curCotext){
        mContext = curCotext;
        // = new DecimalFormat("##,##0.00");
        decimalFormat = new DecimalFormat(",#00.00");
        mSimulateAdapter = this;
    }



    public void   notifyDataChange_WithJson(){
        StockHolder.mBankBean.sortUserList();
        stockbeanlist = StockHolder.mBankBean.getSimulateuserlist();
        itemCount =  calculStockBeanAndUserSize(stockbeanlist);
        DataHolder.writeContentToFile(StockHolder.Simulate_Stock_JSON_File , StockHolder.mBankBean.toBankJson());
        notifyDataSetChanged();
    }

    public void notifyDataChange_NoJson(){
        StockHolder.mBankBean.sortUserList();
        stockbeanlist = StockHolder.mBankBean.getSimulateuserlist();
        itemCount =  calculStockBeanAndUserSize(stockbeanlist);
        notifyDataSetChanged();
    }

    /**
     * 传入数据
     * @param objects
     */
    public void setSimulateUserListData(List<StockSimulate_BankBean.StockSimulate_UserBean> objects) {
        this.stockbeanlist = objects;

        StockHolder.mBankBean.sortUserList();
        itemCount =  calculStockBeanAndUserSize(stockbeanlist);

        notifyDataSetChanged();
    }


    static  String TAG = "StockSimulateAdapter";


    private void showTitleBuyStockDialog(View clickview) {

        // 传入的 数据是 position_tscode
        // tag-0  是 对应的 user 的position
        // tag-1 是对应的 stock的position
        // tag-2 是对应的 tscode 股票的代码

        String mUserIndexStr = ""+clickview.getTag(R.id.buyuserindextag);
        String mStockInUserIndexStr = ""+clickview.getTag(R.id.buystockindextag);
        String mStockTsCode = ""+clickview.getTag(R.id.buytscodetag);


        Log.i(TAG,"showTitleBuyStockDialog  mUserIndexStr="+mUserIndexStr+"   mStockInUserIndexStr="+mStockInUserIndexStr+" mStockTsCode="+mStockTsCode);


        if("".equals(mUserIndexStr) || !StockHolder.isNumeric(mUserIndexStr)){
            Log.i(TAG,"showTitleBuyStockDialog  拿到的 positionTag 错误: positionStr="+mUserIndexStr);
            return;
        }

        int userIndex= Integer.parseInt(mUserIndexStr);
        int stockIndex=  -1 ;
        if(mStockInUserIndexStr != null && !"".equals(mStockInUserIndexStr) && !"null".equals(mStockInUserIndexStr)){

            stockIndex =      Integer.parseInt(mStockInUserIndexStr);
        }

        final  int stockIndexFinal = stockIndex;

        //
        StockSimulate_BankBean.StockSimulate_UserBean userBean  =    StockHolder.mBankBean.getSimulateuserlist().get(userIndex);

        if(userBean == null){
            Log.i(TAG,"showTitleBuyStockDialog   获取到的 UserBean 为空  userBean="+userBean+"  userIndex="+userIndex);

            return ;
        }
        double avaliableMoney = userBean.getRestsimuletemoney();




//                userBean.getStocklist().get(mStockInUserIndexStr);


        AlertDialog.Builder builder= new AlertDialog.Builder(mContext);


        View view = null;
        int o = mContext.getResources().getConfiguration().orientation;

        if (o == Configuration.ORIENTATION_LANDSCAPE){
            view= LayoutInflater.from(mContext).inflate(R.layout.simulate_title_buystock_land, null);

        } else{
            view= LayoutInflater.from(mContext).inflate(R.layout.simulate_title_buystock_port, null);

        }


        // 当前成本
        final TextView user_stock_cost =view.findViewById(R.id.user_stock_cost);
        final TextView buystock_title =view.findViewById(R.id.buystock_title);
        final TextView daytext =view.findViewById(R.id.today_dialog_text);
        final TextView btn_buy = (TextView)view.findViewById(R.id.btn_buy);

        // 全仓
        final TextView all_buy =view.findViewById(R.id.all_buy);
        final TextView half_buy =view.findViewById(R.id.half_buy);
        final TextView one_third_buy =view.findViewById(R.id.one_third_buy);
        final TextView one_fourth_buy =view.findViewById(R.id.one_fourth_buy);


        // 购买 指定金额的 股票
        final TextView buy_1000w =view.findViewById(R.id.buy_1000w_tv);
        final TextView buy_100w =view.findViewById(R.id.buy_100w_tv);
        final TextView buy_10w =view.findViewById(R.id.buy_10w_tv);
        final TextView buy_1w =view.findViewById(R.id.buy_1w_tv);



        // 股票数量 Begin________________
        final EditText et_count =(EditText)view.findViewById(R.id.et_count); //  股票数量
        final TextView can_buy_count =view.findViewById(R.id.can_buy_count);  // 可买数量
        final TextView count_plus =view.findViewById(R.id.count_plus);    // 增加股数
        final TextView count_minus =view.findViewById(R.id.count_minus);    // 增加股数
        // 股票数量 END________________
        // 涨停价格
        final TextView max_value =view.findViewById(R.id.max_value);
        // 当日价格
        final TextView today_value =view.findViewById(R.id.today_value);
        // 跌停价格
        final TextView min_value =view.findViewById(R.id.min_value);

        // 价格+
        final TextView value_plus =view.findViewById(R.id.value_plus);
        // 价格-
        final TextView value_minus =view.findViewById(R.id.value_minus);
        // 价格目录
        final EditText et_buyvalue_edittext =(EditText) view.findViewById(R.id.et_buy_value);

        final Dialog buyStockDialog= builder.create();


        mTitleBuyStockDialogView = buyStockDialog;
        // 股票名称
        final EditText et_stock  =view.findViewById(R.id.et_stock);
        //  股票代码


        // --------------------------------     股票代码 监听 事件
        final EditText et_code  =view.findViewById(R.id.et_code);
        TextWatcher tsCodeTextWatch =    new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String curTsCode = s.toString();
                Log.i(TAG,"curTsCode = " + curTsCode);


                // TScode_List    .SH  .SZ
                // 只有 6位数值  才能 检查

                if(curTsCode.length() == 6){
                    if(StockHolder.TScode_List == null || StockHolder.TScode_List.size() == 0 ){
                        Toast.makeText(mContext,StockHolder.day_lastjson_Day+"_Stock-Info 尚未初始数 请初始化!!" ,Toast.LENGTH_SHORT).show();
                        return;
                    }

        
                    StockHolder.queryStockNowPriceWith_Sina_Tscode(curTsCode,mContext,et_buyvalue_edittext,all_buy,min_value,today_value,max_value);

                    String stock_SH = curTsCode+".SH";
                    String stock_SZ = curTsCode+".SZ";
                    String stock_MatchCode = null;
                    if(!StockHolder.TScode_List.contains(stock_SH) && !StockHolder.TScode_List.contains(stock_SZ)  ){

                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 不存在对应股票，请检查" ,Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(StockHolder.TScode_List.contains(stock_SH)){
                        stock_MatchCode = stock_SH;
                    }else if(StockHolder.TScode_List.contains(stock_SZ)){
                        stock_MatchCode = stock_SZ;
                    }

                    if(stock_MatchCode == null){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 不存在对应股票，请检查" ,Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Stock_NodeImpl stockNode =  StockHolder.getStockNodeWithTsCode(stock_MatchCode);

                    if(stockNode == null ){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空" ,Toast.LENGTH_SHORT).show();
                        Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空  stockShareNode = "+ stockNode);
                        return ;
                    }

                    // 当前输入TScode[000002] 找到的 StockNode stockShareNode = StockNodeShareInfo{price=19.13, ts_code='null', XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_ts_code='null'}
                    Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode stockShareNode = "+ stockNode);

                    // 2021-10-29 16:21:37.987 22915-22915/? I/StockSimulateAdapter: 当前输入TScode[000002] 找到的 StockNode stockShareNode = Stock_NodeImpl{color_int=0, id=3596, count=0, children=[], level=2, selectedChildId=0, parentid=0, area='深圳', curr_type='CNY', name='万科A', enname='China Vanke Co.,Ltd.', exchange='SZSE', fullname='万科企业股份有限公司', industry='全国地产', is_hs='S', list_date='19910129', delist_date='', list_status='L', market='主板', symbol='000002', ts_code='000002.SZ', level3_key='null', level3_value='null', showindex=0, price=19.13, XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_sub_code='null', XinGuLieBiaoIPO_ts_code='null', XinGuLieBiaoIPO_amount=0.0, positionInAdapter=0}
                    String stockName = stockNode.name;
                    double stockPriceInJson = stockNode.price;   //  股票价格
                    //  需要联网获取 股票价格

                    double stockPriceInEdit = stockPriceInJson;
                    if(stockPriceInJson < 1){
                        stockPriceInEdit = 1;  // 防止 没有 价格的 项 出现
                    }



                    double stockPriceInSina = 0;
                    StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean stockBean   = null;

                    if(stockIndexFinal >= 0 ){
                        stockBean = userBean.getStocklist().get(stockIndexFinal);
                    }

                    if(stockBean != null){
                        stockPriceInSina = stockBean.getStocknowprice();

                    }

                    if(stockPriceInSina != 0 && stockPriceInSina != stockPriceInJson){
                        stockPriceInEdit = stockPriceInSina;
                    }
                    Log.i(TAG,"stockPriceInEdit["+stockPriceInEdit+"]  stockPriceInSina["+stockPriceInSina+"]  stockPriceInJson["+stockPriceInJson+"]  stockIndexFinal["+stockIndexFinal+"]");

                    int allBuyCount =   calculAllBuyStockCount(avaliableMoney,stockPriceInEdit);

                    et_count.setText(allBuyCount+"");
                    et_count.setSelection((allBuyCount+"").length());//将光标移至文字末尾
                    can_buy_count.setText("可买[ "+allBuyCount+" ]股");
                    et_buyvalue_edittext.setText(""+decimalFormat.format(stockPriceInEdit));

//                    stockPriceInSina

                    String editstockName = et_stock.getText().toString();
                    if(!stockName.equals(editstockName)){
                        et_stock.setText(stockName+"");
                    }
                    et_code.setSelection((curTsCode+"").length());
                    double allCost = allBuyCount * stockPriceInEdit;
                    user_stock_cost.setText("成本["+decimalFormat.format(allCost)+"]");


/*

                    StockNodeShareInfo stockShareNode =  StockHolder.tscode_stockShareInfo_Map.get(stock_MatchCode);

                    if(stockShareNode == null ){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空" ,Toast.LENGTH_SHORT).show();
Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空  stockShareNode = "+ stockShareNode);
                        return ;
                    }

  // 当前输入TScode[000002] 找到的 StockNode stockShareNode = StockNodeShareInfo{price=19.13, ts_code='null', XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_ts_code='null'}
                    Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode stockShareNode = "+ stockShareNode);
*/

                }

            }
        };

        et_code.addTextChangedListener(tsCodeTextWatch);


        // --------------------------------     股票名称 监听 事件
        TextWatcher stockNameTextWatch =    new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String stockName = s.toString();

                Log.i(TAG,"stockName = " + stockName);

                if(StockHolder.TScode_List == null || StockHolder.TScode_List.size() == 0 ){
                    Toast.makeText(mContext,StockHolder.day_lastjson_Day+"_Stock-Info 尚未初始数 请初始化!!" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                Stock_NodeImpl matchNodeImpl =      StockHolder.getStockNodeWithStockName(stockName);

                if(matchNodeImpl != null){
                    String stockNameStr = matchNodeImpl.name;
                    double stockPrice = matchNodeImpl.price;
                    String tscode = matchNodeImpl.ts_code;
                    tscode = tscode.replace(".SH","").replace(".SZ","");


                    String editTscode =     et_code.getText().toString();
                    if(tscode != editTscode){  // 不等于 才会去变化
                        et_code.setText(matchNodeImpl.ts_code);
                    }

                }




            }
        };
        et_stock.addTextChangedListener(stockNameTextWatch);



        //    点击事件

        View.OnClickListener buyStockClickListener =    new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();

                switch (viewId){
                    case R.id.value_plus:   // 股票价格加
                        buy_1000w_clicknum = 0;
                        buy_100w_clicknum = 0;
                        buy_10w_clicknum = 0;
                        buy_1w_clicknum = 0;
                        String stockValueStr_plus =  et_buyvalue_edittext.getText().toString();
                        if("".equals(stockValueStr_plus)){
                            et_buyvalue_edittext.setText("1.00");
                            return;
                        }

                        Double stockValueDouble_plus = Double.parseDouble(stockValueStr_plus);
                        if(stockValueDouble_plus < 0 ){
                            et_buyvalue_edittext.setText("1.00");
                            return;
                        }
                        double stockValue_Plus = stockValueDouble_plus+0.01;
                        et_buyvalue_edittext.setText(decimalFormat.format(stockValue_Plus)+"");

                        String stockCountStr_PlusStr =  et_count.getText().toString();

                        double allBuyCount_valuePlus = 0;
                        if("".equals(stockCountStr_PlusStr)){
                            allBuyCount_valuePlus = 0;
                        }
                        allBuyCount_valuePlus = Double.parseDouble(stockCountStr_PlusStr);

                        double allCost_value_plus = allBuyCount_valuePlus * stockValue_Plus;
                        user_stock_cost.setText("成本["+decimalFormat.format(allCost_value_plus)+"]");

                        break;

                    case R.id.value_minus:   // 股票价格减
                        buy_1000w_clicknum = 0;
                        buy_100w_clicknum = 0;
                        buy_10w_clicknum = 0;
                        buy_1w_clicknum = 0;

                        String stockValueStr_minus =  et_buyvalue_edittext.getText().toString();
                        if("".equals(stockValueStr_minus)){
                            et_buyvalue_edittext.setText("1");
                            return;
                        }
                        Double stockValueDouble_minus = Double.parseDouble(stockValueStr_minus);
                        if(stockValueDouble_minus < 0   ){
                            et_buyvalue_edittext.setText("0.00");
                            return;
                        }
                        double stockValue_Minus = stockValueDouble_minus-0.01;
                        if(stockValue_Minus < 0){
                            et_buyvalue_edittext.setText("0.00");
                            return;
                        }
                        et_buyvalue_edittext.setText(decimalFormat.format(stockValue_Minus)+"");

                        String stockCountStr_MinusA =  et_count.getText().toString();

                        double allBuyCount_valueMinus = 0;
                        if("".equals(stockCountStr_MinusA)){
                            allBuyCount_valueMinus = 0;
                        }
                        allBuyCount_valueMinus = Double.parseDouble(stockCountStr_MinusA);

                        double allCost_value_minus = allBuyCount_valueMinus * stockValue_Minus;
                        user_stock_cost.setText("成本["+decimalFormat.format(allCost_value_minus)+"]");



                        break;

                    case  R.id.count_minus:   // 股票数量减
                        buy_1000w_clicknum = 0;
                        buy_100w_clicknum = 0;
                        buy_10w_clicknum = 0;
                        buy_1w_clicknum = 0;

                        String stockCountStr_minus =  et_count.getText().toString();
                        if("".equals(stockCountStr_minus)){
                            et_count.setText("100");
                            return;
                        }
                        int stockCountInt_minus =  Integer.parseInt(stockCountStr_minus);
                        if(stockCountInt_minus < 0){
                            et_count.setText("100");
                            return;
                        }

                        int stockCount_minus = stockCountInt_minus-100;
                        if(stockCount_minus < 0){
                            et_count.setText("100");
                            return;
                        }
                        int stockCountMinus_100fixed = stockCount_minus -stockCount_minus%100;
                        et_count.setText(stockCountMinus_100fixed+"");


                        String stockValueStr_MinusCountB =  et_buyvalue_edittext.getText().toString();

                        double stockValue_CountMinus = 0;
                        if("".equals(stockValueStr_MinusCountB)){
                            stockValue_CountMinus = 0;
                        }
                        stockValue_CountMinus = Double.parseDouble(stockValueStr_MinusCountB);

                        double allCost_CountMinus = stockCountMinus_100fixed * stockValue_CountMinus;
                        user_stock_cost.setText("成本["+decimalFormat.format(allCost_CountMinus)+"]");


                        break;
                    case R.id.count_plus:   //   股票数量加
                        buy_1000w_clicknum = 0;
                        buy_100w_clicknum = 0;
                        buy_10w_clicknum = 0;
                        buy_1w_clicknum = 0;
                        String stockCountStr =  et_count.getText().toString();
                        if("".equals(stockCountStr)){
                            et_count.setText("100");
                            return;
                        }
                        int stockCountInt =  Integer.parseInt(stockCountStr);
                        if(stockCountInt < 0){
                            et_count.setText("100");
                            return;
                        }
                        int stockCountPlus = stockCountInt+100;
                        int stockCountPlus_100fixed = stockCountPlus -stockCountPlus%100;
                        et_count.setText(stockCountPlus_100fixed+"");


                        String stockValueStr_count_plus =  et_buyvalue_edittext.getText().toString();

                        double stockValue_count_plus = 0;
                        if("".equals(stockValueStr_count_plus)){
                            stockValue_count_plus = 0;
                        }
                        stockValue_CountMinus = Double.parseDouble(stockValueStr_count_plus);

                        double allCost_count_plus = stockCountPlus_100fixed * stockValue_CountMinus;
                        user_stock_cost.setText("成本["+decimalFormat.format(allCost_count_plus)+"]");



                        break;


                    case R.id.one_fourth_buy:
                    case R.id.one_third_buy:
                    case R.id.half_buy:
                    case R.id.all_buy:
                        buy_1000w_clicknum = 0 ;
                        buy_100w_clicknum = 0 ;
                        buy_10w_clicknum = 0 ;
                        buy_1w_clicknum = 0 ;

                        String stockCountStr_allbuy =  et_count.getText().toString();
                        String stockValueStr_allbuy =  et_buyvalue_edittext.getText().toString();

                        if("".equals(stockCountStr_allbuy) && "".equals(stockValueStr_allbuy)  ){
                            Toast.makeText(mContext,"当前 【股票数量 && 股票价格】 为空,默认初始化到100股1元",Toast.LENGTH_SHORT).show();
                            et_count.setText("100");
                            et_buyvalue_edittext.setText("1.00");

                            user_stock_cost.setText("成本["+decimalFormat.format(100D)+"]");


                            return;
                        }

                        if("".equals(stockValueStr_allbuy)){
                            Toast.makeText(mContext,"当前股票价格为空,默认初始化到 1.00元",Toast.LENGTH_SHORT).show();
                            et_buyvalue_edittext.setText("1.00");
                            String stockCountStr_tempallbuy =   et_count.getText().toString();
                            double stockCountDouble = 0;
                            if(!"".equals(stockCountStr_tempallbuy)){
                                stockCountDouble = Double.parseDouble(stockCountStr_tempallbuy);
                            }

                            user_stock_cost.setText("成本["+decimalFormat.format(stockCountDouble)+"]");


                            return;
                        }

                        if("".equals(stockCountStr_allbuy)){
                            Toast.makeText(mContext,"当前股票数量为空,默认初始化到100",Toast.LENGTH_SHORT).show();
                            et_count.setText("100");

                            String stockValueStr_tempallbuy =   et_buyvalue_edittext.getText().toString();
                            double stockValueDouble = 0;
                            if(!"".equals(stockValueStr_tempallbuy)){
                                stockValueDouble = Double.parseDouble(stockValueStr_tempallbuy);
                            }

                            user_stock_cost.setText("成本["+decimalFormat.format(stockValueDouble*100)+"]");


                            return;
                        }
                        double stockPrice =  Double.parseDouble(stockValueStr_allbuy);
                        double least100Money = 100 * stockPrice;

                        if(avaliableMoney <  least100Money ){
                            Toast.makeText(mContext,"当前资金不足以购买一手(100股股票)",Toast.LENGTH_SHORT).show();
                            user_stock_cost.setText("成本["+decimalFormat.format(0)+"]");

                            return;
                        }

                        int curBuyCount = 0 ;
                        if(viewId == R.id.one_fourth_buy){
                            int stockCount_one_fourth_buy = calculAllBuyStockCount(avaliableMoney/4,stockPrice);
                            curBuyCount = stockCount_one_fourth_buy;
                        }

                        if(viewId == R.id.one_third_buy){
                            int stockCount_one_third_buy = calculAllBuyStockCount(avaliableMoney/3,stockPrice);
                            curBuyCount = stockCount_one_third_buy;

                        }
                        if(viewId == R.id.half_buy){
                            int stockCount_halfBuy = calculAllBuyStockCount(avaliableMoney/2,stockPrice);
                            curBuyCount = stockCount_halfBuy;

                        }
                        if(viewId == R.id.all_buy){

                            int stockCount_allBuy = calculAllBuyStockCount(avaliableMoney,stockPrice);
                            curBuyCount = stockCount_allBuy;
                            can_buy_count.setText("可买[ "+curBuyCount+" ]股");
                        }
                        et_count.setText(curBuyCount+"");

                        double curStockPrice = 0;
                        String stockPriceStr =  et_buyvalue_edittext.getText().toString();
                        if(!"".equals(stockPriceStr)){
                            curStockPrice = Double.parseDouble(stockPriceStr);
                        }
                        user_stock_cost.setText("成本["+decimalFormat.format(curStockPrice*curBuyCount)+"]");

                        break;



                    case R.id.buy_1w_tv:
                    case R.id.buy_10w_tv:
                    case R.id.buy_100w_tv:
                    case R.id.buy_1000w_tv:

                        // 多次点击时是否 可以相加
                        // 当前股票数量
                        String stockCountStr_moneyBuy =  et_count.getText().toString();

                        // 当前股票价格
                        String stockValueStr_moneyBuy =  et_buyvalue_edittext.getText().toString();


                        long  stock_buy_count = 0;
                        if(isNumeric(stockCountStr_moneyBuy)){
                            stock_buy_count = Long.parseLong(stockCountStr_moneyBuy)   ;
                        }

                        if(stock_buy_count == 0  ){
                            Toast.makeText(mContext,"当前购买股票数量为空或者为0,请检查",Toast.LENGTH_SHORT).show();
                            return;
                        }



                        double stock_now_price = 0;



                        if(isDoubleNumeric(stockValueStr_moneyBuy)){

                            stock_now_price =      Double.parseDouble(stockValueStr_moneyBuy);

                        }


                        if(stock_now_price == 0  ){
                            Toast.makeText(mContext,"当前股价为空或者为0,请检查",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 如果时 false  那么就是从0开始增加 购买的数量
                        // 如果为 true  那么就从当前购买 股票数量的基础上增加股票
                        //  判断当前的

                        // 当前剩余的资金
                        double restMoney = userBean.getRestsimuletemoney();


                        //  当前股价固定，股票数量是变化的
                        long dynamicStockCount = 0 ;

                        if(viewId == R.id.buy_1000w_tv){
                            buy_1000w_clicknum++;

                            double mMoneyBuy  = buy_1w_clicknum*10000 + buy_10w_clicknum*100000+buy_100w_clicknum*1000000+buy_1000w_clicknum*10000000;

                            if(restMoney <= mMoneyBuy){
                                // 多余的点击 是 不生效的
                                Toast.makeText(mContext,"当前增加1000w后购买股票金额["+mMoneyBuy+"] 已经超过\n账户余额["+restMoney+"]",Toast.LENGTH_SHORT).show();

                                buy_1000w_clicknum -- ;

                            }

                        }


                        if(viewId == R.id.buy_100w_tv){
                            buy_100w_clicknum++;

                            double mMoneyBuy  = buy_1w_clicknum*10000 + buy_10w_clicknum*100000+buy_100w_clicknum*1000000+buy_1000w_clicknum*10000000;

                            if(restMoney <= mMoneyBuy){
                                // 多余的点击 是 不生效的
                                Toast.makeText(mContext,"当前增加100w后购买股票金额["+mMoneyBuy+"] 已经超过\n账户余额["+restMoney+"]",Toast.LENGTH_SHORT).show();

                                buy_100w_clicknum -- ;

                            }

                        }

                        if(viewId == R.id.buy_10w_tv){
                            buy_10w_clicknum++;

                            double mMoneyBuy  = buy_1w_clicknum*10000 + buy_10w_clicknum*100000+buy_100w_clicknum*1000000+buy_1000w_clicknum*10000000;

                            if(restMoney <= mMoneyBuy){
                                // 多余的点击 是 不生效的
                                Toast.makeText(mContext,"当前增加10w购买股票金额["+mMoneyBuy+"] 已经超过\n账户余额["+restMoney+"]",Toast.LENGTH_SHORT).show();

                                buy_10w_clicknum -- ;

                            }

                        }


                        if(viewId == R.id.buy_1w_tv){
                            buy_1w_clicknum++;

                            double mMoneyBuy  = buy_1w_clicknum*10000 + buy_10w_clicknum*100000+buy_100w_clicknum*1000000+buy_1000w_clicknum*10000000;

                            if(restMoney <= mMoneyBuy){
                                // 多余的点击 是 不生效的
                                Toast.makeText(mContext,"当前增加1w购买股票金额["+mMoneyBuy+"] 已经超过\n账户余额["+restMoney+"]",Toast.LENGTH_SHORT).show();

                                buy_1w_clicknum -- ;

                            }

                        }

                        double mMoneyBuy  = buy_1w_clicknum*10000 + buy_10w_clicknum*100000+buy_100w_clicknum*1000000+buy_1000w_clicknum*10000000;

                        if(restMoney <= mMoneyBuy){
                            // 多余的点击 是 不生效的

                            Toast.makeText(mContext,"当前购买股票金额["+mMoneyBuy+"] 已经超过\n账户余额["+restMoney+"]",Toast.LENGTH_SHORT).show();
                            return;

                        }

                      double  mBuyCount =       Math.floor(mMoneyBuy/stock_now_price);

                        dynamicStockCount = (long)(mBuyCount-(mBuyCount%10));
                        et_count.setText(""+dynamicStockCount);
                        et_count.setSelection((""+dynamicStockCount).length());

                        user_stock_cost.setText("成本["+decimalFormat.format(dynamicStockCount*stock_now_price)+"]");

                        break;

                    case R.id.btn_buy:    // 买入股票 操作逻辑    //  买入股票的逻辑
                        StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean  stockNew = new  StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean();
                        String  tsCodeStr =  et_code.getText().toString();
                        String  buyCountStr =   et_count.getText().toString();
                        String  buyPriceStr =    et_buyvalue_edittext.getText().toString();
                        String  buyStockNameStr =   et_stock.getText().toString();

                        if( "".equals(tsCodeStr) ||
                                "".equals(buyCountStr) ||
                                "".equals(buyPriceStr) ||
                                "".equals(buyStockNameStr)

                        ){

                            Toast.makeText(mContext,"当前购买股票信息错误,请检查",Toast.LENGTH_SHORT).show();

                            return;

                        }

                        // 买入的卖出金额
                        int buyStockCountInt = Integer.parseInt(buyCountStr);
                        double allBuyMoney = Integer.parseInt(buyCountStr) * Double.parseDouble(buyPriceStr);
                        double dynamicRestMoney = userBean.getRestsimuletemoney();   // 用户的余额
                        if(allBuyMoney > dynamicRestMoney || buyStockCountInt == 0 ){
                            Toast.makeText(mContext,"当前剩余现金["+decimalFormat.format(dynamicRestMoney)+"] 无法购买["+Integer.parseInt(buyCountStr)+"]股  公司["+buyStockNameStr+"] 价值["+decimalFormat.format(allBuyMoney)+"]的股票",Toast.LENGTH_SHORT).show();

                            return;

                        }

                        Log.i("buyStock_Tag","buyCountStr="+buyCountStr+"  tsCodeStr="+tsCodeStr+"  buyPriceStr="+buyPriceStr+"    buyStockNameStr = "+ buyStockNameStr);
                        stockNew.setStockkeepcount(Integer.parseInt(buyCountStr));  //  买入数量

                        stockNew.setStocktscode(tsCodeStr);
                        stockNew.setStockcostforone(Double.parseDouble(buyPriceStr));
                        // buyStockNameStr  (乐视退) 暴风退  这样的括号 是不能出现的  否则 json 就会错误
                        buyStockNameStr = buyStockNameStr.replace("(","").replace(")","").replace("退退","退");
                        stockNew.setStockname(buyStockNameStr);

                        double nowStockPrice = Double.parseDouble(buyPriceStr);
                        Stock_NodeImpl stockNode =  StockHolder.getStockNodeWithTsCodeNoSHSZ(tsCodeStr);
                        if(stockNode != null){

                            nowStockPrice = stockNode.price;
                        }

                        stockNew.setStocknowprice(nowStockPrice);
                        // 检测 在已经 存在的 股票列表中 是否 有 这个股票
                        if(userBean.getStocklist() != null && userBean.getStocklist().size() > 0){
                            boolean isAlredyContain = userBean.isContainStock(stockNew);
                            if(isAlredyContain){   //  已经 有这个股票的话
                                userBean.addExistStock(stockNew);   // 添加新已经存在的股票
                                // 设置新的余额    买后余额=买前余额-股票成本
                                userBean.setRestsimuletemoney(dynamicRestMoney-allBuyMoney);


                                notifyDataChange_WithJson();

                                buyStockDialog.dismiss();
                                Toast.makeText(mContext,"【已拥有股票】\n 以价格["+decimalFormat.format(Double.parseDouble(buyPriceStr))+"] 购买["+Integer.parseInt(buyCountStr)+"]股  公司["+buyStockNameStr+"]股票",Toast.LENGTH_SHORT).show();

                                return;
                            }


                        }
                        // 新买的股票的时间 覆盖掉 原有的时间
                        stockNew.setStockbuytime(StockHolder.getTimeStamp_YYYY_MM_DD());
                        userBean.buyNewStock(stockNew);
                        userBean.setDynamicisshowstock(true);
                        notifyDataChange_WithJson();
                        buyStockDialog.dismiss();
                        Toast.makeText(mContext,"以价格["+decimalFormat.format(Double.parseDouble(buyPriceStr))+"] 购买["+Integer.parseInt(buyCountStr)+"]股  公司["+buyStockNameStr+"]股票",Toast.LENGTH_SHORT).show();


                        break;

                }


            }
        };


        // 股票数量逻辑
        count_plus.setOnClickListener(buyStockClickListener);
        count_minus.setOnClickListener(buyStockClickListener);
        value_plus.setOnClickListener(buyStockClickListener);
        value_minus.setOnClickListener(buyStockClickListener);

        all_buy.setOnClickListener(buyStockClickListener);
        half_buy.setOnClickListener(buyStockClickListener);
        one_third_buy.setOnClickListener(buyStockClickListener);
        one_fourth_buy.setOnClickListener(buyStockClickListener);


        buy_1w.setOnClickListener(buyStockClickListener);
        buy_10w.setOnClickListener(buyStockClickListener);
        buy_100w.setOnClickListener(buyStockClickListener);
        buy_1000w.setOnClickListener(buyStockClickListener);




        btn_buy.setOnClickListener(buyStockClickListener);



//        buyStockDialog.setTitle("购买股票"+":");//文字
        buystock_title.setText("可用购股资金["+decimalFormat.format(avaliableMoney)+"]");


        buyStockDialog.show();
        buyStockDialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        buyStockDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


        buyStockDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                buy_1000w_clicknum = 0;
                buy_100w_clicknum = 0;
                buy_10w_clicknum = 0;
                buy_1w_clicknum = 0;
            }
        });
        // 如果 有 传递过来 股票 tscode  那么 就设置 ts_code
        if(mStockTsCode != null && !"".equals(mStockTsCode) && !"null".equals(mStockTsCode)){
            et_code.setText(mStockTsCode);
            et_code.setSelection(mStockTsCode.length());
        }



    }


    int calculAllBuyStockCount(double allAvaliableMoney , double stockPrice){

        if(allAvaliableMoney <= 0 ){
            return 0;
        }

        double allBuyCount = Math.floor(allAvaliableMoney/stockPrice);
       double allBuyCount_100fixed = allBuyCount - allBuyCount%100;
        //    double allBuyCount_100fixed = allBuyCount ;
        return (int)allBuyCount_100fixed;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simulate_user_title, parent, false);
            holder = new StockSimulateViewHolderTitle(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simulate_userstock_content, parent, false);
            holder = new StockSimulateViewHolderContent(view);

        }
        return holder;
    }

    public  boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }

        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        if (str.equals("")) {
            return false;
        }
        return true;
    }



    public  boolean isDoubleNumeric(String str){

        Pattern Double_Pattern = Pattern.compile("-?[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");

        Matcher isNum = Double_Pattern.matcher(str);

        if( !isNum.matches() ){
            return false;

        }

        return true;

    }

    /**
     * 设置剪切板内容
     * @return
     */
    public  void setClipContent(String content2Clip , String tip  ){


        if (TextUtils.isEmpty(content2Clip)) {


            return ;
        }

        ClipboardManager manager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            manager.setPrimaryClip(ClipData.newPlainText(content2Clip,content2Clip));
            Toast.makeText(mContext,"\n"+tip+"\n"+content2Clip+"\n已写入剪切板成功",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(mContext,content2Clip+"\n已写入剪切板失败",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof StockSimulateViewHolderTitle) {
            StockSimulate_BankBean.StockSimulate_UserBean userbean =  mPositionUserBeanMap.get(position);

            if(userbean != null  ){
                ((StockSimulateViewHolderTitle) holder).index_riqi_text.setText("日期:");

                // "["+userbean.getUserindex()+"_"+userbean.getUsercount()+"]"+



                if(userbean.isImageUserBean()){
                    ((StockSimulateViewHolderTitle) holder).buystock_text.setText("["+(userbean.getDynamicuserindex()+1)+"_"+StockHolder.mBankBean.getUserCount()+"]"+"禁止买卖");

                } else {
                    ((StockSimulateViewHolderTitle) holder).buystock_text.setText("["+(userbean.getDynamicuserindex()+1)+"_"+StockHolder.mBankBean.getUserCount()+"]"+"买入股票");

                }

                ((StockSimulateViewHolderTitle) holder).daytimetext.setText(userbean.getDaytime());


                BigDecimal mInitmoneybigdecimal =new BigDecimal(userbean.getInitsimulatemoney()+"");//10位
                ((StockSimulateViewHolderTitle) holder).initmoney_text.setText(decimalFormat.format(mInitmoneybigdecimal));


                // 初始化资产
                ((StockSimulateViewHolderTitle) holder).allstockworth_stockcount_tv.setText("总市值"+"["+(userbean.getStocklist() == null ? 0 :userbean.getStocklist().size())+ "]");

                // 余额[仓位:100%]

             ((StockSimulateViewHolderTitle) holder).avaliablemoney_cangweirate_tv.setText("余额[仓位:"+userbean.getCangWeiRate()+ "]");

                // 总资产

                //  NaN   userbean.getAllworth()+""
                Log.i("Allworth_A","Allworth = "+userbean.getAllworth()+"");
                BigDecimal mallWorthbigdecimal =new BigDecimal(userbean.getAllworth()+"");//10位
                ((StockSimulateViewHolderTitle) holder).allworthmoney_text.setText(decimalFormat.format(mallWorthbigdecimal));

                // 总市值
                BigDecimal mallStockWorthbigdecimal =new BigDecimal(userbean.getStockNowWorth()+"");//10位
                ((StockSimulateViewHolderTitle) holder).allstockvalue_text.setText(decimalFormat.format(mallStockWorthbigdecimal));

                // 可用余额
                BigDecimal mRestMoneybigdecimal =new BigDecimal(userbean.getRestsimuletemoney()+"");//10位
                ((StockSimulateViewHolderTitle) holder).avaliablemoney_text.setText(decimalFormat.format(mRestMoneybigdecimal));


                // 盈利情况
                double userAllBenifit =   userbean.getBenifitMoney();
                BigDecimal mBenifitMoneybigdecimal =new BigDecimal(userAllBenifit+"");//10位

                ((StockSimulateViewHolderTitle) holder).benifitmoney_text.setText(decimalFormat.format(mBenifitMoneybigdecimal));

                ((StockSimulateViewHolderTitle) holder).all_profit_rate_tv.setText(userbean.getAllProfirRateStr());

                if(userAllBenifit > 0){
                    ((StockSimulateViewHolderTitle) holder).benifitmoney_text.setTextColor(Color.parseColor("#FF3030"));

                    ((StockSimulateViewHolderTitle) holder).all_profit_rate_tv.setTextColor(Color.parseColor("#FF3030"));

                }else{
                    ((StockSimulateViewHolderTitle) holder).benifitmoney_text.setTextColor(Color.parseColor("#32CD32"));
                    ((StockSimulateViewHolderTitle) holder).all_profit_rate_tv.setTextColor(Color.parseColor("#32CD32"));

                }


                // 今日 盈亏 情况
                double userTodayBenifit =   userbean.getTodayProfirValueDouble();

                ((StockSimulateViewHolderTitle) holder).today_profit_rate_tv.setText(userbean.getTodayProfirRateStr());
                ((StockSimulateViewHolderTitle) holder).today_profit_value_tv.setText(userbean.getTodayProfirValueStr());

                if(userTodayBenifit > 0){
                    ((StockSimulateViewHolderTitle) holder).today_profit_rate_tv.setTextColor(Color.parseColor("#FF3030"));
                    ((StockSimulateViewHolderTitle) holder).today_profit_value_tv.setTextColor(Color.parseColor("#FF3030"));

                }else{
                    ((StockSimulateViewHolderTitle) holder).today_profit_rate_tv.setTextColor(Color.parseColor("#32CD32"));
                    ((StockSimulateViewHolderTitle) holder).today_profit_value_tv.setTextColor(Color.parseColor("#32CD32"));

                }



                // all_profit_rate_tv


                // 动态计算的结果
                ((StockSimulateViewHolderTitle) holder).hide_show_stock_text.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                       String tagStr =  userbean.getExifTag();
                        setClipContent(tagStr,"");
                        return false;
                    }
                });

                ((StockSimulateViewHolderTitle) holder).hide_show_stock_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean nowHiddenState =    userbean.isDynamicisshowstock();
                        int userIndex =     userbean.getDynamicuserindex();
                        userbean.setDynamicisshowstock(!nowHiddenState);
                        if(userbean.getStocklist() == null || userbean.getStocklist().size() == 0){
                            Toast.makeText(mContext,"当前 userIndex["+userIndex+"]"+"还没有股票资源!",Toast.LENGTH_SHORT).show();
                        }
                        if(StockHolder.isGuPiaoLieBiao_FInish_MapInit_Flag){
                            userbean.refreshUserStockNowPriceWithJson();
                        }
                        Log.i("RefreshX","点击时刷新操作! refreshUserStockNowPriceWithSina ");
                        userbean.getSinaQueryUrl();   // 获取到 用户的所有的 请求列表

                        userbean.refreshUserStockNowPriceWithSina(mContext,mSimulateAdapter);
                        StockHolder.check_Zhishu(mContext);
                        notifyDataChange_WithJson();
                    }
                });
                ((StockSimulateViewHolderTitle) holder).hide_show_stock_text.setText(getTimeStamp()+"_"+calculXinQi()+"市");

                if(userbean.isImageUserBean()){
//                    ((StockSimulateViewHolderTitle) holder).clearuser_text.setTextColor(Color.parseColor("#2828FE"));
                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setTextColor(Color.parseColor("#FFFFFF"));
                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean nowHiddenState =    userbean.isDynamicisshowstock();
                            int userIndex =     userbean.getDynamicuserindex();
                            userbean.setDynamicisshowstock(!nowHiddenState);
                            if(userbean.getStocklist() == null || userbean.getStocklist().size() == 0){
                                Toast.makeText(mContext,"当前 userIndex["+userIndex+"]"+"还没有股票资源!",Toast.LENGTH_SHORT).show();
                            }
                            if(StockHolder.isGuPiaoLieBiao_FInish_MapInit_Flag){
                                userbean.refreshUserStockNowPriceWithJson();
                            }
                            Log.i("RefreshX","点击时刷新操作! refreshUserStockNowPriceWithSina ");
                            userbean.getSinaQueryUrl();   // 获取到 用户的所有的 请求列表

                            userbean.refreshUserStockNowPriceWithSina(mContext,mSimulateAdapter);

                            notifyDataChange_WithJson();
                        }
                    });
                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                           // 复制一个自身的 可交易的账户到 mBank

                            final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);
                            alterDiaglog.setTitle("复刻可交易账户提示:");//文字
                            alterDiaglog.setMessage("请确认是否以["+userbean.getUsertitle()+"]复刻一个可交易账户:\n");//提示消息

                            alterDiaglog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    StockHolder.mBankBean.copyUser(userbean);
                                    notifyDataChange_WithJson();
                                    Toast.makeText(mContext, "复刻"+userbean.getUsertitle()+"个可交易User成功!" ,Toast.LENGTH_SHORT).show();

                                }
                            });


                            //中立的选择
                            alterDiaglog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {



                                }
                            });

                            //显示
                            alterDiaglog.show();



                            return false;
                        }
                    });

                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setText(((userbean.getUsertitle() == null || "".equals(userbean.getUsertitle()) || "null".equals(userbean.getUsertitle() ))?"":"("+(userbean.getUsertitle())+")"));

                    ((StockSimulateViewHolderTitle) holder).title3_linear1_layout.setBackgroundColor(Color.parseColor("#99556677"));



                } else {
                    ((StockSimulateViewHolderTitle) holder).title3_linear1_layout.setBackgroundColor(Color.parseColor("#990000FF"));
                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setTextColor(Color.parseColor("#FFFFFF"));
                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean nowHiddenState =    userbean.isDynamicisshowstock();
                            int userIndex =     userbean.getDynamicuserindex();
                            userbean.setDynamicisshowstock(!nowHiddenState);
                            if(userbean.getStocklist() == null || userbean.getStocklist().size() == 0){
                                Toast.makeText(mContext,"当前 userIndex["+userIndex+"]"+"还没有股票资源!",Toast.LENGTH_SHORT).show();
                            }
                            if(StockHolder.isGuPiaoLieBiao_FInish_MapInit_Flag){
                                userbean.refreshUserStockNowPriceWithJson();
                            }
                            Log.i("RefreshX","点击时刷新操作! refreshUserStockNowPriceWithSina ");
                            userbean.getSinaQueryUrl();   // 获取到 用户的所有的 请求列表

                            userbean.refreshUserStockNowPriceWithSina(mContext,mSimulateAdapter);

                            notifyDataChange_WithJson();
                        }
                    });

                    if(userbean.getUsertitle() != null && userbean.getUsertitle().length() >4){
                        ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setText( userbean.getUsertitle());

                    }else{
                        ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setText("销户"+((userbean.getUsertitle() == null || "".equals(userbean.getUsertitle()) ||  "null".equals(userbean.getUsertitle()))?"":"("+(userbean.getUsertitle())+")"));

                    }


                    ((StockSimulateViewHolderTitle) holder).clearuser_usertitle_text.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            showDeleteUserBeanDialog(userbean.dynamicuserindex);
                            return false;
                        }
                    });

                }

//                showDeleteUserBeanDialog(userbean.dynamicuserindex);


                ((StockSimulateViewHolderTitle) holder).daytimetext.setText(userbean.daytime);

                // 传入的 数据是 position_tscode
                // R.id.userindextag  tag-0  是 对应的 user 的position
                // R.id.stockindextag  tag-1 是对应的 stock的position
                // R.id.tscodetag  tag-2 是对应的 tscode 股票的代码

                ((StockSimulateViewHolderTitle) holder).buystock_text.setTag(R.id.buyuserindextag,""+userbean.getDynamicuserindex());

            if(userbean.isImageUserBean()){
//                ((StockSimulateViewHolderTitle) holder).buystock_text.setTextColor(Color.parseColor("#2828FE"));

                ((StockSimulateViewHolderTitle) holder).buystock_text.setOnClickListener(null);
            }else{   // 非 ImageUser  才 可以 购买按钮 事件
                ((StockSimulateViewHolderTitle) holder).buystock_text.setTextColor(Color.parseColor("#FFFFFF"));

                ((StockSimulateViewHolderTitle) holder).buystock_text.setOnClickListener(this::showTitleBuyStockDialog);
            }


            }


        }else if (holder instanceof StockSimulateViewHolderContent) {
            //  获取 每个  stock_item 的 实例 数据
            StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean  stockbean = mPositionStockBeanMap.get(position);
            if(stockbean != null){

                Log.i("TradeAdapter","X1 stockbean = "+stockbean.toString());


//                stockNew.setStockcount(Integer.parseInt(buyCountStr));  //  买入数量
//                stockNew.setStock_tscode(tsCodeStr);
//                stockNew.setStockcostforone(Double.parseDouble(buyPriceStr));
//                stockNew.setStockname(buyStockNameStr);


                // 股票名称
                ((StockSimulateViewHolderContent) holder).stockName_text.setText(StockHolder.calculLittleDigital(stockbean.dynamicstockindex+1)+""+stockbean.stockname+stockbean.getSelfStockOnAllPercentTip());


                // 持股数量
                ((StockSimulateViewHolderContent) holder).stockStockNum_text.setText(""+stockbean.stockkeepcount);
                Log.i("showAdapter","   stockbean.stockcount="+stockbean.stockkeepcount +"    stockbean.stockname="+stockbean.stockname+"  stockbean.getStockcostforone()="+stockbean.getStockcostforone()+"  stockbean.getStockprofit()="+stockbean.getStockprofit() +"  stockbean.getStocknowprice()="+stockbean.getStocknowprice()+"  stockbean.getStocknowvalue()="+stockbean.getStocknowprice());
                // 买入每股成本
                ((StockSimulateViewHolderContent) holder).stockBuyPrice_text.setText(""+decimalFormat.format(stockbean.getStockcostforone()));


                // 可用 股票数量 -___ 感觉 没作用  ⁰¹²³⁴⁵⁶⁷⁸⁹
                if(stockbean.getStockbuytime() != null){
                  String shortTimeStr =   stockbean.getStockbuytime();
                    shortTimeStr = shortTimeStr.replace(".","");
                    if(shortTimeStr.startsWith("20")){
                        shortTimeStr = shortTimeStr.substring(2);
                    }

                    String now = StockHolder.getTimeStamp_YYYY_MM_DD();
                    // 计算两个时间点的差距(天数)
                    int distanceTime = TimeUtil.differentDays_YYYYMMDD(stockbean.getStockbuytime(),now);

                  String mLittleNumberStr =   StockHolder.calculLittleDigital(distanceTime);
                    // 170606  170606_1000
                    shortTimeStr = shortTimeStr+mLittleNumberStr;
                    ((StockSimulateViewHolderContent) holder).stockAvaliableStockNum_or_buytime_text.setText(""+ shortTimeStr);

                }



                // 股票盈利亏损的钱
                double  stockBenifit = stockbean.getStockprofit();
                ((StockSimulateViewHolderContent) holder).stockBenefitmoney_text.setText(decimalFormat.format(stockBenifit)+"");
                if(stockBenifit > 0){
                    ((StockSimulateViewHolderContent) holder).stockBenefitmoney_text.setTextColor(Color.parseColor("#FF3030"));
                    ((StockSimulateViewHolderContent) holder).stockBenefitmoneyRate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{
                    ((StockSimulateViewHolderContent) holder).stockBenefitmoney_text.setTextColor(Color.parseColor("#32CD32"));
                    ((StockSimulateViewHolderContent) holder).stockBenefitmoneyRate_text.setTextColor(Color.parseColor("#32CD32"));

                }



                // 浮亏百分比
                ((StockSimulateViewHolderContent) holder).stockBenefitmoneyRate_text.setText(decimalFormat.format(stockbean.getDynamicStockProfitRate())+"%");

                // 现在的价格
                ((StockSimulateViewHolderContent) holder).stockNowPrice_text.setText(decimalFormat.format(stockbean.getStocknowprice())+"");

                // 现在的股票的市值
                ((StockSimulateViewHolderContent) holder).stockValue_text.setText(decimalFormat.format(stockbean.getStocknowprice() * stockbean.getStockkeepcount())+"");




                if(stockbean.getPredaystockprice() != 0){

                    String mTodayUpDownRateStr =  stockbean.getTodayStockUpDownRate(); // 今日股票的涨跌幅度
                    String mTodayUpDownValueStr =  stockbean.getTodayStockUpDownValue();  // 今日股票的涨跌值





                    // 今日股票的浮动盈亏
                    String mTodayBenifitStr = stockbean.getTodayStockProfitValueStr();
                    Log.i("ShowRateX","tsname="+stockbean.stockname+"  isImage="+stockbean.isImageStockBean+"  mTodayBenifitStr=["+mTodayBenifitStr+"] "+"  mTodayUpDownValueStr["+mTodayUpDownValueStr+"]   mTodayUpDownRateStr=["+mTodayUpDownRateStr+"]   getPredaystockprice["+stockbean.getPredaystockprice()+"]   stocknowprice["+stockbean.getStocknowprice()+"]");
                    if(mTodayBenifitStr.startsWith("-")){
                        ((StockSimulateViewHolderContent) holder).today_stock_profitvalue_text.setText(mTodayBenifitStr+"↓");
                        ((StockSimulateViewHolderContent) holder).today_stock_profitvalue_text.setTextColor(Color.parseColor("#32CD32"));
                        ((StockSimulateViewHolderContent) holder).today_stock_updownrate_text.setText(mTodayUpDownRateStr+"↓");
                        ((StockSimulateViewHolderContent) holder).today_stock_updownrate_text.setTextColor(Color.parseColor("#32CD32"));

                        ((StockSimulateViewHolderContent) holder).today_stock_updownvalue_text.setText(mTodayUpDownValueStr+"↓");
                        ((StockSimulateViewHolderContent) holder).today_stock_updownvalue_text.setTextColor(Color.parseColor("#32CD32"));

                    }else{
                        ((StockSimulateViewHolderContent) holder).today_stock_profitvalue_text.setText(mTodayBenifitStr+"↑");
                        ((StockSimulateViewHolderContent) holder).today_stock_profitvalue_text.setTextColor(Color.parseColor("#FF3030"));

                        ((StockSimulateViewHolderContent) holder).today_stock_updownrate_text.setText(mTodayUpDownRateStr+"↑");
                        ((StockSimulateViewHolderContent) holder).today_stock_updownrate_text.setTextColor(Color.parseColor("#FF3030"));

                        ((StockSimulateViewHolderContent) holder).today_stock_updownvalue_text.setText(mTodayUpDownValueStr+"↑");
                        ((StockSimulateViewHolderContent) holder).today_stock_updownvalue_text.setTextColor(Color.parseColor("#FF3030"));

                    }

                    // 股票昨日的价格
                    ((StockSimulateViewHolderContent) holder).preday_stock_close_price_text.setText(stockbean.getPredaystockPriceStr()+"");



                }


//

                // 600004.SH   300321.SZ
                // stockbean.getStocktscode() == 300321

                String mTscode =  stockbean.getStocktscode();

                String mTscode_Area =    StockHolder.tscode_To_tscodeArea(mTscode);
                StockNodeShareInfo shareObj = null ;
                if(mTscode_Area != null && StockHolder.tscode_stockShareInfo_Map != null ){
                    shareObj =  StockHolder.tscode_stockShareInfo_Map.get(mTscode_Area);
                }


                double day3_pct_chg =   0;
                double day5_pct_chg =  0 ;
                double day10_pct_chg =  0 ;
                double day15_pct_chg =  0 ;


                double day30_pct_chg =   0;
                double day60_pct_chg =  0 ;
                double day90_pct_chg =  0 ;
                double dayyear_pct_chg =  0 ;

                if(shareObj != null){

                     day3_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day3_pct_chg;
                     day5_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day5_pct_chg;
                     day10_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day10_pct_chg;
                     day15_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day15_pct_chg;
                    day30_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day30_pct_chg;
                    day60_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day60_pct_chg;
                    day90_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_day90_pct_chg;
                    dayyear_pct_chg =   shareObj.RiXianXingQingvShiJianWeiXu_dayyear_pct_chg;

                }






                DecimalFormat priceFormat  = new DecimalFormat("#0.0");
                if(day3_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day3_flow_rate_text.setText("₃"+priceFormat.format(day3_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day3_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day3_flow_rate_text.setText("₃"+priceFormat.format(day3_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day3_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }

                if(day5_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day5_flow_rate_text.setText("₅"+priceFormat.format(day5_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day5_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day5_flow_rate_text.setText("₅"+priceFormat.format(day5_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day5_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }

                if(day10_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day10_flow_rate_text.setText("₁₀"+priceFormat.format(day10_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day10_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day10_flow_rate_text.setText("₁₀"+priceFormat.format(day10_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day10_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                if(day15_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day15_flow_rate_text.setText("₁₅"+priceFormat.format(day15_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day15_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day15_flow_rate_text.setText("₁₅"+priceFormat.format(day15_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day15_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }


                if(day30_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day30_flow_rate_text.setText("₃₀"+priceFormat.format(day30_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day30_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day30_flow_rate_text.setText("₃₀"+priceFormat.format(day30_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day30_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }

                if(day60_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day60_flow_rate_text.setText("₆₀"+priceFormat.format(day60_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day60_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day60_flow_rate_text.setText("₆₀"+priceFormat.format(day60_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day60_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }

                if(day90_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day90_flow_rate_text.setText("₉₀"+priceFormat.format(day90_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day90_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day90_flow_rate_text.setText("₉₀"+priceFormat.format(day90_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day90_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                if(dayyear_pct_chg > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_dayyear_flow_rate_text.setText("年"+priceFormat.format(dayyear_pct_chg)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_dayyear_flow_rate_text.setTextColor(Color.parseColor("#FF3030"));
                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_dayyear_flow_rate_text.setText("年"+priceFormat.format(dayyear_pct_chg)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_dayyear_flow_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }






                // ==================   指数 数据显示  =============




                StockSimulate_ZhiShu matchZhishu ;
                String preZhiShu_Tip = "沪";



                if(StockHolder.kcbzs_zhishu == null ||
                        StockHolder.shzs_zhishu == null ||
                        StockHolder.cybzs_zhishu == null ||
                        StockHolder.szzs_zhishu == null
                ){
                    StockHolder.init_ZhiShu(mContext);
                }


                if(mTscode_Area.toLowerCase().contains("sh") && mTscode_Area.startsWith("68")){
                    matchZhishu = StockHolder.kcbzs_zhishu;
                    preZhiShu_Tip = "科";
                } else if(mTscode_Area.toLowerCase().contains("sh") && mTscode_Area.startsWith("60")) {
                    matchZhishu = StockHolder.shzs_zhishu;
                    preZhiShu_Tip = "沪";

                } else if(mTscode_Area.toLowerCase().contains("sz") && mTscode_Area.startsWith("3")) {
                    matchZhishu = StockHolder.cybzs_zhishu;
                    preZhiShu_Tip = "创";

                }else{
                    matchZhishu = StockHolder.szzs_zhishu;
                    preZhiShu_Tip = "深";
                }


                // 检测 没有包含数据的时候
                if(matchZhishu.allZhiShuItemList == null || matchZhishu.allZhiShuItemList.size() == 0){

                    if(matchZhishu.allZhiShuItemList == null){


                        android.util.Log.i("zukgit_Exception","Exception  matchZhishu = "+matchZhishu+"  mTscode_Area="+mTscode_Area+"  preZhiShu_Tip="+preZhiShu_Tip);

//                        matchZhishu.init_allZhiShuItemList();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (this){
                                if(matchZhishu.allZhiShuItemList == null || matchZhishu.allZhiShuItemList.size() == 0 || !matchZhishu.isContainCurYearData() ){  // 不包含今年的数据
                                   synchronized (this){

                                       if(matchZhishu == null || !matchZhishu.isContainCurYearData() ){
                                           StockHolder.requestNetwork_ZhiShu(mContext,matchZhishu);

                                       }

                                   }
                                }

                            }

                        }
                    }).start();

                }

                if(matchZhishu == null){
                    Toast.makeText(mContext,"指数数据尚未初始化完成,请等待!",Toast.LENGTH_SHORT).show();
                    return;
                }

                double day3_zhishu_rate = matchZhishu.day3_zhushu_pct * 100;
                double day5_zhishu_rate = matchZhishu.day5_zhushu_pct* 100;
                double day10_zhishu_rate = matchZhishu.day10_zhushu_pct* 100;
                double day15_zhishu_rate = matchZhishu.day15_zhushu_pct* 100;

                double day30_zhishu_rate = matchZhishu.day30_zhushu_pct* 100;
                double day60_zhishu_rate = matchZhishu.day60_zhushu_pct* 100;
                double day90_zhishu_rate = matchZhishu.day90_zhushu_pct* 100;
                double year_zhishu_rate = matchZhishu.year_zhushu_pct* 100;
                if(year_zhishu_rate == 0 ){
                    matchZhishu.year_zhushu_pct = matchZhishu.getDayYear_Pct_Chg();
                    year_zhishu_rate = matchZhishu.year_zhushu_pct * 100;
                }

                DecimalFormat zhishuFormat  = new DecimalFormat("#0.00");
                if(day3_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day3_zhizhu_rate_text.setText(preZhiShu_Tip+"₃"+zhishuFormat.format(day3_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day3_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day3_zhizhu_rate_text.setText(preZhiShu_Tip+"₃"+zhishuFormat.format(day3_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day3_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }


                if(day5_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day5_zhizhu_rate_text.setText(preZhiShu_Tip+"₅"+zhishuFormat.format(day5_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day5_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day5_zhizhu_rate_text.setText(preZhiShu_Tip+"₅"+zhishuFormat.format(day5_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day5_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }


                if(day10_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day10_zhizhu_rate_text.setText(preZhiShu_Tip+"₁₀"+zhishuFormat.format(day10_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day10_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day10_zhizhu_rate_text.setText(preZhiShu_Tip+"₁₀"+zhishuFormat.format(day10_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day10_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                if(day30_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day30_zhizhu_rate_text.setText(preZhiShu_Tip+"₃₀"+zhishuFormat.format(day30_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day30_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day30_zhizhu_rate_text.setText(preZhiShu_Tip+"₃₀"+zhishuFormat.format(day30_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day30_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }


                if(day60_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day60_zhizhu_rate_text.setText(preZhiShu_Tip+"₆₀"+zhishuFormat.format(day60_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day60_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day60_zhizhu_rate_text.setText(preZhiShu_Tip+"₆₀"+zhishuFormat.format(day60_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day60_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }


                if(day90_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day90_zhizhu_rate_text.setText(preZhiShu_Tip+"₉₀"+zhishuFormat.format(day90_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day90_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day90_zhizhu_rate_text.setText(preZhiShu_Tip+"₉₀"+zhishuFormat.format(day90_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day90_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                if(year_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day_year_zhizhu_rate_text.setText("岁"+zhishuFormat.format(year_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day_year_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day_year_zhizhu_rate_text.setText("岁"+zhishuFormat.format(year_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day_year_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                if(day15_zhishu_rate > 0){  // 红色涨
                    ((StockSimulateViewHolderContent) holder).stock_day15_zhizhu_rate_text.setText(preZhiShu_Tip+"₁₅"+zhishuFormat.format(day15_zhishu_rate)+"%↑");
                    ((StockSimulateViewHolderContent) holder).stock_day15_zhizhu_rate_text.setTextColor(Color.parseColor("#FF3030"));

                }else{   // 绿色 跌
                    ((StockSimulateViewHolderContent) holder).stock_day15_zhizhu_rate_text.setText(preZhiShu_Tip+"₁₅"+zhishuFormat.format(day15_zhishu_rate)+"%↓");
                    ((StockSimulateViewHolderContent) holder).stock_day15_zhizhu_rate_text.setTextColor(Color.parseColor("#32CD32"));
                }



                // 传入的 数据是 position_tscode
                // tag-0  是 对应的 user 的position
                // tag-1 是对应的 stock的position
                // tag-2 是对应的 tscode 股票的代码
                ((StockSimulateViewHolderContent) holder).stockBuyStock_text.setTag(R.id.buyuserindextag,""+stockbean.getDynamicparentuserindex());

                ((StockSimulateViewHolderContent) holder).stockBuyStock_text.setTag(R.id.buystockindextag,""+stockbean.getDynamicStockIndex());

                ((StockSimulateViewHolderContent) holder).stockBuyStock_text.setTag(R.id.buytscodetag,""+stockbean.getStocktscode());
               if(stockbean.isImageStockBean()){
                   ((StockSimulateViewHolderContent) holder).stockBuyStock_text.setOnClickListener(null);
                   ((StockSimulateViewHolderContent) holder).stockSellStock_text.setOnClickListener(null);
               }else{
                   ((StockSimulateViewHolderContent) holder).stockBuyStock_text.setOnClickListener(this::showTitleBuyStockDialog);
                   ((StockSimulateViewHolderContent) holder).stockSellStock_text.setOnClickListener(this::showSellStockDialog);
               }

                ((StockSimulateViewHolderContent) holder).userstock_content_lauout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                       String tsCode =  stockbean.getStocktscode();
                      String stockName =   stockbean.getStockname();

                      String tip = "股票["+stockName+"]["+tsCode+"]";
                        setClipContent(stockName,tip);

                        return false;
                    }
                });



                // 传入的 数据是 position_tscode
                // tag-0  是 对应的 user 的position
                // tag-1 是对应的 stock的position
                // tag-2 是对应的 tscode 股票的代码

                ((StockSimulateViewHolderContent) holder).stockSellStock_text.setTag(R.id.selluserindextag,""+stockbean.getDynamicparentuserindex());

                ((StockSimulateViewHolderContent) holder).stockSellStock_text.setTag(R.id.sellstockindextag,""+stockbean.getDynamicStockIndex());

                ((StockSimulateViewHolderContent) holder).stockSellStock_text.setTag(R.id.selltscodetag,""+stockbean.getStocktscode());




            }

        }

    }






    String calculXinQi() {
         Calendar now = Calendar.getInstance();
         int xinqi = now.get(Calendar.DAY_OF_WEEK) - 1;  //

        String xinqiValue = "";
//      System.out.println(" Axiniq = "+ xiniq);
        switch (xinqi) {
            case 1:
                xinqiValue = "一";
                break;
            case 2:
                xinqiValue = "二";
                break;
            case 3:
                xinqiValue = "三";
                break;
            case 4:
                xinqiValue = "四";
                break;
            case 5:
                xinqiValue = "五";
                break;
            case 6:
                xinqiValue = "六";
                break;
            case 7:
                xinqiValue = "天";
                break;
            default:
                xinqiValue = "一";  // 默认周一
        }

        return xinqiValue;
    }


    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }


    void showDeleteUserBeanDialog( int userbeanIndex ){
        int alluserCount = StockHolder.mBankBean.getSimulateuserlist().size();

       String userTitle =  StockHolder.mBankBean.getSimulateuserlist().get(userbeanIndex).getUsertitle();
       if(userTitle == null || "null".equals(userTitle)){
           userTitle = "";

       }else{
           userTitle =   "["+userTitle+"]";
       }
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);
        alterDiaglog.setTitle("删除模拟账户User"+"["+userbeanIndex+"_"+alluserCount+"]"+userTitle+":");//文字
        alterDiaglog.setMessage("请确认是否删除以下股票模拟账户:\n"+"User["+userbeanIndex+"_"+alluserCount+"]"+userTitle);//提示消息

        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StockHolder.mBankBean.getSimulateuserlist().remove(userbeanIndex);
                notifyDataChange_WithJson();


            }
        });

        //显示
        alterDiaglog.show();


    }

    @Override
    public int getItemViewType(int position) {
        // title【A】-list_size【B】
//        if (stockbeanlist.get(position) instanceof String) {
//            return ITEM_TITLE;
//        } else if (stockbeanlist.get(position) instanceof OpenRecordBean.DataBean.LogDOListBean) {
//            return ITEM_CONTENT;
//        }
        return mPositionViewTypeMap.get(position);


//        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
//        return stockbeanlist == null ? 0 : stockbeanlist.size();

        return stockbeanlist == null ? 0 : itemCount;
    }

    //  三个 Map
    // Map<Position,Type>;  User 是 1   ， Content 是 2
    // Map<Position,StockSimulate_UserBean>;  User 是 1   ， Content 是 2
    // Map<Position,StockSimulate_UserStockBean>;  User 是 1   ， Content 是 2


    int calculStockBeanAndUserSize(List<StockSimulate_BankBean.StockSimulate_UserBean> mStockUserlist){


        mPositionViewTypeMap =   new HashMap<Integer, Integer>();
        mPositionUserBeanMap =   new HashMap<Integer,StockSimulate_BankBean.StockSimulate_UserBean>();
        mPositionStockBeanMap =  new HashMap<Integer,StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean>();

        List<Object> objList = new ArrayList<Object>();





        if(mStockUserlist  == null){
            return 0;
        }
//        int userCount_stockCount = mStockUserlist.size();

        mStockUserlist.sort(new Comparator<StockSimulate_BankBean.StockSimulate_UserBean>() {
            @Override
            public int compare(StockSimulate_BankBean.StockSimulate_UserBean o1, StockSimulate_BankBean.StockSimulate_UserBean o2) {
                Log.i(TAG,"o1.getDaytime()="+o1.getDaytime()+"     o2.getDaytime()="+o2.getDaytime());
                return o2.getDaytime().compareTo(o1.getDaytime());
            }
        });

        for (int i = 0; i < mStockUserlist.size() ; i++) {
            StockSimulate_BankBean.StockSimulate_UserBean userBean =     mStockUserlist.get(i);
            if(userBean == null ){
                continue;
            }else{
                mPositionViewTypeMap.put(objList.size(),1);  //  Title
                userBean.setDynamicuserindex(i);
                mPositionUserBeanMap.put(objList.size(),userBean);
                objList.add(userBean);
            }
            List<StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean> stockList =       userBean.getStocklist();

            if(userBean.isDynamicisshowstock()){
                if(stockList != null &&  stockList.size() > 0 ){
//                userCount_stockCount += stockList.size();

                    for (int j = 0; j < stockList.size(); j++) {
                        StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean   stockBean = stockList.get(j);
                        if(stockBean != null){
                            mPositionViewTypeMap.put(objList.size(),2);  //  Cintent
                            stockBean.setDynamicstockindex(j);
                            stockBean.setDynamicUserAllStockWorth(userBean.getStockNowWorth());
                            stockBean.setDynamicparentuserindex(userBean.getDynamicuserindex());
                            mPositionStockBeanMap.put(objList.size(),stockBean);

                            objList.add(stockBean);
                        }
                    }
                }

            }

        }

        // 模拟账户的总数
        int mUserCount = mPositionUserBeanMap.size();
        for (int i = 0; i < mStockUserlist.size() ; i++) {
            StockSimulate_BankBean.StockSimulate_UserBean userBean =     mStockUserlist.get(i);
            if(userBean == null ){
                continue;
            }  else{
//                userBean.setUsercount(mUserCount);
            }
            if(userBean.getStocklist() != null && userBean.getStocklist().size() > 0){

                for (int j = 0; j <  userBean.getStocklist().size() ; j++) {
                    StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean   stockBean =  userBean.getStocklist().get(j);


                }
            }
        }

        return objList.size();
    }








    private void showSellStockDialog(View clickview) {

        // 传入的 数据是 position_tscode
        // tag-0  是 对应的 user 的position
        // tag-1 是对应的 stock的position
        // tag-2 是对应的 tscode 股票的代码

        String mUserIndexStr = ""+clickview.getTag(R.id.selluserindextag);
        String mStockInUserIndexStr = ""+clickview.getTag(R.id.sellstockindextag);
        String mStockTsCode = ""+clickview.getTag(R.id.selltscodetag);
        Log.i(TAG,"showSellStockDialog   mUserIndexStr="+mUserIndexStr+" mStockInUserIndexStr="+mStockInUserIndexStr+" mStockTsCode="+mStockTsCode);




        if("".equals(mUserIndexStr) || !StockHolder.isNumeric(mUserIndexStr)){
            Log.i(TAG,"showTitlesellStockDialog  拿到的 positionTag 错误: positionStr="+mUserIndexStr);
            return;
        }

        if("".equals(mStockInUserIndexStr) || !StockHolder.isNumeric(mStockInUserIndexStr)){
            Log.i(TAG,"showTitlesellStockDialog  拿到的 stockindextag 错误: stockindextag="+mStockInUserIndexStr+"   mUserIndexStr="+ mUserIndexStr);
            return;
        }


        int userIndex= Integer.parseInt(mUserIndexStr);
        int stockIndex= Integer.parseInt(mStockInUserIndexStr);

        //s
        StockSimulate_BankBean.StockSimulate_UserBean userBean  =    StockHolder.mBankBean.getSimulateuserlist().get(userIndex);
//        StockSimulate_BankBean.StockSimulate_UserBean userBean  =  mPositionUserBeanMap.get(userIndex);


        if(userBean == null){
            Log.i(TAG,"showTitlesellStockDialog   获取到的 UserBean 为空  userBean="+userBean+"  userIndex="+userIndex);

            return ;
        }

        StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean stockBean =  StockHolder.mBankBean.getSimulateuserlist().get(userIndex).getStocklist().get(stockIndex);


        if(stockBean == null){
            Log.i(TAG,"showTitlesellStockDialog   获取到的 stockBean 为空  userBean="+userBean+"  stockBean="+stockBean);

            return ;
        }


        // 持有的股票数量
        int stockCountNow = stockBean.getStockkeepcount();

        double avaliableMoney = userBean.getRestsimuletemoney();

        AlertDialog.Builder builder= new AlertDialog.Builder(mContext);


        View view = null;
        int o = mContext.getResources().getConfiguration().orientation;

        if (o == Configuration.ORIENTATION_LANDSCAPE){
            view= LayoutInflater.from(mContext).inflate(R.layout.simulate_content_sellstock_land, null);

        } else{
            view= LayoutInflater.from(mContext).inflate(R.layout.simulate_content_sellstock_port, null);

        }


        // 卖出金额
        final TextView user_stock_cost =view.findViewById(R.id.user_sellstock_money);

        final TextView sellstock_title =view.findViewById(R.id.sellstock_title);

        //  最多卖出[ xxxx ] 股
        final TextView user_sell_benifit =view.findViewById(R.id.user_sell_benifit);



        // 卖出股票按钮
        final TextView btn_sell = (TextView)view.findViewById(R.id.btn_sell);

        // 全仓股票
        final TextView all_sell =view.findViewById(R.id.all_sell);

        // 半仓股票
        final TextView half_sell =view.findViewById(R.id.half_sell);

        // 三分之一股票
        final TextView one_third_sell =view.findViewById(R.id.one_third_sell);

        // 四分之一 股票
        final TextView one_fourth_sell =view.findViewById(R.id.one_fourth_sell);

        // 卖出股票数量  EditText Begin________________
        final EditText sell_et_count =(EditText)view.findViewById(R.id.et_sell_count); //  股票数量

        // 卖出去股票的成本
        final TextView sell_ChengBenMoney =view.findViewById(R.id.user_sellstock_cost);  // 卖出去股票的成本

        // 卖出股票增加
        final TextView sell_count_plus =view.findViewById(R.id.sell_count_plus);    // 增加股数

        // 卖出股票减少
        final TextView sell_count_minus =view.findViewById(R.id.sell_count_minus);    //
        // 股票数量 END________________
        // 涨停价格
        final TextView sell_max_value =view.findViewById(R.id.sell_max_value);
        // 当日价格
        final TextView sell_today_value =view.findViewById(R.id.sell_today_value);

        // 跌停价格
        final TextView sell_min_value =view.findViewById(R.id.sell_min_value);


        sell_min_value.setText("跌停:"+stockBean.getTodayDieTingPriceStr());
        DecimalFormat priceFormat  = new DecimalFormat("#0.00");
        sell_today_value.setText("开盘:"+priceFormat.format(stockBean.getPredaystockprice()));

        sell_max_value.setText("涨停:"+stockBean.getTodayZhangTingPriceStr());

        // 卖出价格+
        final TextView sell_value_plus =view.findViewById(R.id.sell_value_plus);
        // 卖出价格-
        final TextView sell_value_minus =view.findViewById(R.id.sell_value_minus);

        // 卖出价格editText
        final EditText sell_et_value =(EditText)view.findViewById(R.id.et_sell_value);

        final Dialog sellStockDialog= builder.create();


        mSellStockDialogView = sellStockDialog;
        // 股票名称 EditText
        final EditText sell_et_stock  =view.findViewById(R.id.sell_et_stock);
        //  股票代码


        // --------------------------------     股票代码 监听 事件
        final EditText sell_et_code  =view.findViewById(R.id.sell_et_code);
        TextWatcher tsCodeTextWatch =    new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String curTsCode = s.toString();
                Log.i(TAG,"curTsCode = " + curTsCode);


                // TScode_List    .SH  .SZ
                // 只有 6位数值  才能 检查

                if(curTsCode.length() == 6){
                    if(StockHolder.TScode_List == null || StockHolder.TScode_List.size() == 0 ){
                        Toast.makeText(mContext,StockHolder.day_lastjson_Day+"_Stock-Info 尚未初始数 请初始化!!" ,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String stock_SH = curTsCode+".SH";
                    String stock_SZ = curTsCode+".SZ";
                    String stock_MatchCode = null;
                    if(!StockHolder.TScode_List.contains(stock_SH) && !StockHolder.TScode_List.contains(stock_SZ)  ){

                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 不存在对应股票，请检查" ,Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(StockHolder.TScode_List.contains(stock_SH)){
                        stock_MatchCode = stock_SH;
                    }else if(StockHolder.TScode_List.contains(stock_SZ)){
                        stock_MatchCode = stock_SZ;
                    }

                    if(stock_MatchCode == null){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 不存在对应股票，请检查" ,Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Stock_NodeImpl stockNode =  StockHolder.getStockNodeWithTsCode(stock_MatchCode);

                    if(stockNode == null ){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空" ,Toast.LENGTH_SHORT).show();
                        Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空  stockShareNode = "+ stockNode);
                        return ;
                    }

                    // 当前输入TScode[000002] 找到的 StockNode stockShareNode = StockNodeShareInfo{price=19.13, ts_code='null', XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_ts_code='null'}
                    Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode stockShareNode = "+ stockNode);

                    // 2021-10-29 16:21:37.987 22915-22915/? I/StockSimulateAdapter: 当前输入TScode[000002] 找到的 StockNode stockShareNode = Stock_NodeImpl{color_int=0, id=3596, count=0, children=[], level=2, selectedChildId=0, parentid=0, area='深圳', curr_type='CNY', name='万科A', enname='China Vanke Co.,Ltd.', exchange='SZSE', fullname='万科企业股份有限公司', industry='全国地产', is_hs='S', list_date='19910129', delist_date='', list_status='L', market='主板', symbol='000002', ts_code='000002.SZ', level3_key='null', level3_value='null', showindex=0, price=19.13, XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_sub_code='null', XinGuLieBiaoIPO_ts_code='null', XinGuLieBiaoIPO_amount=0.0, positionInAdapter=0}
                    String stockName = stockNode.name;
                    double stockPriceInDayJson = stockNode.price;
                    double stockPriceInEdit = 0 ;
                    if(stockPriceInDayJson < 1){
                        stockPriceInEdit = 1;  // 防止 没有 价格的 项 出现
                    }

                   double stockPriceSina =  stockBean.getStocknowprice();
                    if(stockPriceSina != 0 && stockPriceSina != stockPriceInDayJson ){

                        stockPriceInEdit = stockPriceSina;
                    }



                    int allsellCount =    stockCountNow;

                    sell_et_count.setText(allsellCount+"");
                    sell_et_count.setSelection((allsellCount+"").length());//将光标移至文字末尾


                    sell_et_value.setText(""+decimalFormat.format(stockPriceInEdit));
                    String editstockName = sell_et_stock.getText().toString();
                    if(!stockName.equals(editstockName)){
                        sell_et_stock.setText(stockName+"");
                    }
                    sell_et_code.setSelection((curTsCode+"").length());
                    double allSellStockMoney = allsellCount * stockPriceInEdit;
                    // 卖出金额
                    user_stock_cost.setText("卖出金额["+decimalFormat.format(allSellStockMoney)+"]");

                    double benifitMoney = allSellStockMoney - allsellCount * stockBean.getStockcostforone();
                    user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney)+"]");
                    sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(allsellCount * stockBean.getStockcostforone() )+"]");



                    /*

                    StockNodeShareInfo stockShareNode =  StockHolder.tscode_stockShareInfo_Map.get(stock_MatchCode);

                    if(stockShareNode == null ){
                        Toast.makeText(mContext,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空" ,Toast.LENGTH_SHORT).show();
Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode 为空  stockShareNode = "+ stockShareNode);
                        return ;
                    }

  // 当前输入TScode[000002] 找到的 StockNode stockShareNode = StockNodeShareInfo{price=19.13, ts_code='null', XinGuLieBiaoIPO_isIPO=false, XinGuLieBiaoIPO_ballot=0.0, XinGuLieBiaoIPO_cname='null', XinGuLieBiaoIPO_funds=0.0, XinGuLieBiaoIPO_ipo_date='null', XinGuLieBiaoIPO_issue_date='null', XinGuLieBiaoIPO_limit_amount=0.0, XinGuLieBiaoIPO_market_amount=0.0, XinGuLieBiaoIPO_name='null', XinGuLieBiaoIPO_pe=0.0, XinGuLieBiaoIPO_price=0.0, XinGuLieBiaoIPO_ts_code='null'}
                    Log.i(TAG,"当前输入TScode["+curTsCode+"] 找到的 StockNode stockShareNode = "+ stockShareNode);
*/

                }

            }
        };

        sell_et_code.addTextChangedListener(tsCodeTextWatch);


        // --------------------------------     股票名称 监听 事件
        TextWatcher stockNameTextWatch =    new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String stockName = s.toString();

                Log.i(TAG,"stockName = " + stockName);

                if(StockHolder.TScode_List == null || StockHolder.TScode_List.size() == 0 ){
                    Toast.makeText(mContext,StockHolder.day_lastjson_Day+"_Stock-Info 尚未初始数 请初始化!!" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                Stock_NodeImpl matchNodeImpl =      StockHolder.getStockNodeWithStockName(stockName);

                if(matchNodeImpl != null){
                    String stockNameStr = matchNodeImpl.name;
                    double stockPrice = matchNodeImpl.price;
                    String tscode = matchNodeImpl.ts_code;
                    tscode = tscode.replace(".SH","").replace(".SZ","");


                    String editTscode =     sell_et_code.getText().toString();
                    if(tscode != editTscode){  // 不等于 才会去变化
                        sell_et_code.setText(matchNodeImpl.ts_code);
                    }

                }




            }
        };
        sell_et_stock.addTextChangedListener(stockNameTextWatch);



        //    点击事件

        View.OnClickListener sellStockClickListener =    new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();

                switch (viewId){
                    case R.id.sell_value_plus:   // 股票价格加
                        String stockValueStr_plus =  sell_et_value.getText().toString();
                        if("".equals(stockValueStr_plus)){
                            sell_et_value.setText("1.00");
                            return;
                        }

                        Double stockValueDouble_plus = Double.parseDouble(stockValueStr_plus);
                        if(stockValueDouble_plus < 0 ){
                            sell_et_value.setText("1.00");
                            return;
                        }
                        double stockValue_Plus = stockValueDouble_plus+0.01;
                        sell_et_value.setText(decimalFormat.format(stockValue_Plus)+"");

                        String stockCountStr_PlusStr =  sell_et_count.getText().toString();

                        double allsellCount_valuePlus = 0;
                        if("".equals(stockCountStr_PlusStr)){
                            allsellCount_valuePlus = 0;
                        }
                        allsellCount_valuePlus = Double.parseDouble(stockCountStr_PlusStr);

                        double allCost_value_plus = allsellCount_valuePlus * stockValue_Plus;
                        user_stock_cost.setText("卖出金额["+decimalFormat.format(allCost_value_plus)+"]");
                        double benifitMoney = allCost_value_plus - allsellCount_valuePlus * stockBean.getStockcostforone();
                        user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney)+"]");
                        sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(allsellCount_valuePlus * stockBean.getStockcostforone() )+"]");

                        break;

                    case R.id.sell_value_minus:   // 股票价格减
                        String stockValueStr_minus =  sell_et_value.getText().toString();
                        if("".equals(stockValueStr_minus)){
                            sell_et_value.setText("1");
                            return;
                        }
                        Double stockValueDouble_minus = Double.parseDouble(stockValueStr_minus);
                        if(stockValueDouble_minus < 0   ){
                            sell_et_value.setText("0.00");
                            return;
                        }
                        double stockValue_Minus = stockValueDouble_minus-0.01;
                        if(stockValue_Minus < 0){
                            sell_et_value.setText("0.00");
                            return;
                        }
                        sell_et_value.setText(decimalFormat.format(stockValue_Minus)+"");

                        String stockCountStr_MinusA =  sell_et_count.getText().toString();

                        double allsellCount_valueMinus = 0;
                        if("".equals(stockCountStr_MinusA)){
                            allsellCount_valueMinus = 0;
                        }
                        allsellCount_valueMinus = Double.parseDouble(stockCountStr_MinusA);

                        double allCost_value_minus = allsellCount_valueMinus * stockValue_Minus;
                        user_stock_cost.setText("卖出金额["+decimalFormat.format(allCost_value_minus)+"]");
                        double benifitMoney_valueMinus = allCost_value_minus - allsellCount_valueMinus * stockBean.getStockcostforone();
                        user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney_valueMinus)+"]");
                        sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(allsellCount_valueMinus * stockBean.getStockcostforone() )+"]");


                        break;

                    case  R.id.sell_count_minus:   // 股票数量减
                        String stockCountStr_minus =  sell_et_count.getText().toString();
                        if("".equals(stockCountStr_minus)){
                            sell_et_count.setText("100");
                            return;
                        }
                        int stockCountInt_minus =  Integer.parseInt(stockCountStr_minus);
                        if(stockCountInt_minus < 0){
                            sell_et_count.setText("100");
                            return;
                        }

                        int stockCount_minus = stockCountInt_minus-100;
                        if(stockCount_minus < 0){
                            sell_et_count.setText("100");
                            return;
                        }
                        int stockCountMinus_100fixed = stockCount_minus -stockCount_minus%100;
                        sell_et_count.setText(stockCountMinus_100fixed+"");


                        String stockValueStr_MinusCountB =  sell_et_value.getText().toString();

                        double stockValue_CountMinus = 0;
                        if("".equals(stockValueStr_MinusCountB)){
                            stockValue_CountMinus = 0;
                        }
                        stockValue_CountMinus = Double.parseDouble(stockValueStr_MinusCountB);

                        double allCost_CountMinus = stockCountMinus_100fixed * stockValue_CountMinus;
                        user_stock_cost.setText("卖出金额["+decimalFormat.format(allCost_CountMinus)+"]");
                        double benifitMoney_CountMinus = allCost_CountMinus - stockCountMinus_100fixed * stockBean.getStockcostforone();
                        user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney_CountMinus)+"]");
                        sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(stockCountMinus_100fixed * stockBean.getStockcostforone() )+"]");


                        break;
                    case R.id.sell_count_plus:   //   股票数量加
                        String stockCountStr =  sell_et_count.getText().toString();
                        if("".equals(stockCountStr)){
                            sell_et_count.setText("100");
                            return;
                        }
                        int stockCountInt =  Integer.parseInt(stockCountStr);
                        if(stockCountInt < 0){
                            sell_et_count.setText("100");
                            return;
                        }
                        int stockCountPlus = stockCountInt+100;
                        int stockCountPlus_100fixed = stockCountPlus -stockCountPlus%100;
                        sell_et_count.setText(stockCountPlus_100fixed+"");


                        String stockValueStr_count_plus =  sell_et_value.getText().toString();

                        double stockValue_count_plus = 0;
                        if("".equals(stockValueStr_count_plus)){
                            stockValue_count_plus = 0;
                        }
                        stockValue_CountMinus = Double.parseDouble(stockValueStr_count_plus);

                        double allCost_count_plus = stockCountPlus_100fixed * stockValue_CountMinus;
                        user_stock_cost.setText("卖出金额["+decimalFormat.format(allCost_count_plus)+"]");
                        double benifitMoney_countplus = allCost_count_plus - stockCountPlus_100fixed * stockBean.getStockcostforone();
                        user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney_countplus)+"]");
                        sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(stockCountPlus_100fixed * stockBean.getStockcostforone() )+"]");



                        break;


                    case R.id.one_fourth_sell:
                    case R.id.one_third_sell:
                    case R.id.half_sell:
                    case R.id.all_sell:
                        String stockCountStr_allsell =  sell_et_count.getText().toString();
                        String stockValueStr_allsell =  sell_et_value.getText().toString();

                        if("".equals(stockCountStr_allsell) && "".equals(stockValueStr_allsell)  ){
                            Toast.makeText(mContext,"当前 【股票数量 && 股票价格】 为空,默认初始化到100股1元",Toast.LENGTH_SHORT).show();
                            sell_et_count.setText("100");
                            sell_et_value.setText("1.00");

                            user_stock_cost.setText("卖出金额["+decimalFormat.format(100D)+"]");
//                            double benifitMoney_countplus = allCost_count_plus - stockCountPlus_100fixed * stockBean.getStockcostforone();
                            user_sell_benifit.setText("盈利浮亏[??]");
                            sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(100 )+"]");


                            return;
                        }

                        if("".equals(stockValueStr_allsell)){
                            Toast.makeText(mContext,"当前股票价格为空,默认初始化到 1.00元",Toast.LENGTH_SHORT).show();
                            sell_et_value.setText("1.00");
                            String stockCountStr_tempallsell =   sell_et_count.getText().toString();
                            double stockCountDouble = 0;
                            if(!"".equals(stockCountStr_tempallsell)){
                                stockCountDouble = Double.parseDouble(stockCountStr_tempallsell);
                            }

                            user_stock_cost.setText("卖出金额["+decimalFormat.format(stockCountDouble)+"]");
                            user_sell_benifit.setText("盈利浮亏[??]");
                            sell_ChengBenMoney.setText("股票成本[??]");


                            return;
                        }

                        if("".equals(stockCountStr_allsell)){
                            Toast.makeText(mContext,"当前股票数量为空,默认初始化到100",Toast.LENGTH_SHORT).show();
                            sell_et_count.setText("100");

                            String stockValueStr_tempallsell =   sell_et_value.getText().toString();
                            double stockValueDouble = 0;
                            if(!"".equals(stockValueStr_tempallsell)){
                                stockValueDouble = Double.parseDouble(stockValueStr_tempallsell);
                            }

                            user_stock_cost.setText("卖出金额["+decimalFormat.format(stockValueDouble*100)+"]");
                            user_sell_benifit.setText("盈利浮亏[??]");
                            sell_ChengBenMoney.setText("股票成本[??]");
                            return;
                        }
                        double stockPrice =  Double.parseDouble(stockValueStr_allsell);
                        double least100Money = 100 * stockPrice;

                        int  stockAvaliableCount  =  Integer.parseInt(stockCountStr_allsell);

                        if(stockAvaliableCount > stockCountNow || stockAvaliableCount < 0){
                            Toast.makeText(mContext,"当前最多持有[ "+stockCountNow+" ] 股 无法超额卖出["+stockAvaliableCount+"] 股",Toast.LENGTH_SHORT).show();
                            return;

                        }



                        int cursellCount = stockCountNow;

                        if(viewId == R.id.one_fourth_sell){
                            int stockCount_one_fourth_sell =  (stockCountNow/4-(stockCountNow/4)%100) < 0? 0 :(stockCountNow/4-(stockCountNow/4)%100);
                            cursellCount = stockCount_one_fourth_sell;
                        }

                        if(viewId == R.id.one_third_sell){
                            int stockCount_one_third_sell = (stockCountNow/3-(stockCountNow/3)%100) < 0? 0 :(stockCountNow/3-(stockCountNow/3)%100);
                            cursellCount = stockCount_one_third_sell;

                        }
                        if(viewId == R.id.half_sell){
                            int stockCount_halfsell = (stockCountNow/2-(stockCountNow/2)%100) < 0? 0 :(stockCountNow/2-(stockCountNow/2)%100);
                            cursellCount = stockCount_halfsell;

                        }
                        if(viewId == R.id.all_sell){

                            int stockCount_allsell = stockCountNow;
                            cursellCount = stockCount_allsell;
                        }
                        sell_et_count.setText(cursellCount+"");

                        double curStockPrice = 0;
                        String stockPriceStr =  sell_et_value.getText().toString();
                        if(!"".equals(stockPriceStr)){
                            curStockPrice = Double.parseDouble(stockPriceStr);
                        }
                        double userSellStockMoney = curStockPrice*cursellCount;
                        user_stock_cost.setText("卖出金额["+decimalFormat.format(userSellStockMoney)+"]");
                        double benifitMoney_Sell = userSellStockMoney - cursellCount * stockBean.getStockcostforone();
                        user_sell_benifit.setText("盈利浮亏["+decimalFormat.format(benifitMoney_Sell)+"]");
                        sell_ChengBenMoney.setText("股票成本["+decimalFormat.format(cursellCount * stockBean.getStockcostforone() )+"]");


                        break;




                    case R.id.btn_sell:    // 卖出股票 操作逻辑
                        StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean  mShellStockBeanNew = new  StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean();
                        String  tsCodeStr =  sell_et_code.getText().toString();
                        String  sellCountStr =   sell_et_count.getText().toString();
                        String  sellPriceStr =    sell_et_value.getText().toString();
                        String  sellStockNameStr =   sell_et_value.getText().toString();

                        if( "".equals(tsCodeStr) ||
                                "".equals(sellCountStr) ||
                                "".equals(sellPriceStr) ||
                                "".equals(sellStockNameStr)

                        ){

                            Toast.makeText(mContext,"当前卖出股票信息错误,请检查",Toast.LENGTH_SHORT).show();

                            return;

                        }

                        // 卖出股票数量
                        int sellStockCountInt = Integer.parseInt(sellCountStr);

                        // 卖出股票卖出金额
                        double allsellMoney = Integer.parseInt(sellCountStr) * Double.parseDouble(sellPriceStr);

                        // 可用余额现金
                        double dynamicRestMoney = userBean.getRestsimuletemoney();


                        if(stockCountNow < 0 || sellStockCountInt >  stockCountNow ){
                            Toast.makeText(mContext,"当前卖出股票["+sellStockCountInt+"] 超过持有的股票数量["+stockCountNow+"] 无法卖出",Toast.LENGTH_SHORT).show();

                            return;

                        }

                        Log.i("sellStock_Tag","sellCountStr="+sellCountStr+"  tsCodeStr="+tsCodeStr+"  sellPriceStr="+sellPriceStr+"    sellStockNameStr = "+ sellStockNameStr);
                        mShellStockBeanNew.setStockkeepcount(Integer.parseInt(sellCountStr));  //  卖出数量

                        mShellStockBeanNew.setStocktscode(tsCodeStr);  // 卖出股票的 代码

                        // 卖出股票的价格
                        mShellStockBeanNew.setStockcostforone(Double.parseDouble(sellPriceStr));  // 卖出股票的价格
                        mShellStockBeanNew.setStockname(sellStockNameStr);

                        double nowStockPrice = Double.parseDouble(sellPriceStr);
                        Stock_NodeImpl stockNode =  StockHolder.getStockNodeWithTsCodeNoSHSZ(tsCodeStr);
                        if(stockNode != null){

                            nowStockPrice = stockNode.price;
                        }

                        // 现在的价格
                        mShellStockBeanNew.setStocknowprice(nowStockPrice);
                        mShellStockBeanNew.setDynamicparentuserindex(userIndex);
                        mShellStockBeanNew.setDynamicstockindex(stockIndex);
                        // 卖出股票的盈利情况
                        double mSellBenifit = mShellStockBeanNew.getStockkeepcount() * (mShellStockBeanNew.getStocknowprice()-mShellStockBeanNew.getStockcostforone());



                        // 检测 在已经 存在的 股票列表中 是否 有 这个股票
                        if(userBean.getStocklist() != null && userBean.getStocklist().size() > 0){
                            boolean isAlredyContain = userBean.isContainStock(mShellStockBeanNew);
                            if(isAlredyContain){
                                userBean.sellExistStock(mShellStockBeanNew);
                                notifyDataChange_WithJson();
                                sellStockDialog.dismiss();
                                Toast.makeText(mContext,"【已卖出股票】\n 以价格["+decimalFormat.format(Double.parseDouble(sellPriceStr))+"] 卖出["+Integer.parseInt(sellCountStr)+"]股  公司["+sellStockNameStr+"]股票",Toast.LENGTH_SHORT).show();

                                return;
                            }


                        }

                        sellStockDialog.dismiss();
                        Toast.makeText(mContext,"【卖出股票__出错】 以价格["+decimalFormat.format(Double.parseDouble(sellPriceStr))+"] 卖出["+Integer.parseInt(sellCountStr)+"]股  公司["+sellStockNameStr+"]股票",Toast.LENGTH_SHORT).show();


                        break;

                }


            }
        };


        // 股票数量逻辑
        sell_count_plus.setOnClickListener(sellStockClickListener);
        sell_count_minus.setOnClickListener(sellStockClickListener);
        sell_value_plus.setOnClickListener(sellStockClickListener);
        sell_value_minus.setOnClickListener(sellStockClickListener);

        all_sell.setOnClickListener(sellStockClickListener);
        half_sell.setOnClickListener(sellStockClickListener);
        one_third_sell.setOnClickListener(sellStockClickListener);
        one_fourth_sell.setOnClickListener(sellStockClickListener);
        btn_sell.setOnClickListener(sellStockClickListener);



//        sellStockDialog.setTitle("购买股票"+":");//文字
        sellstock_title.setText("最多卖出[ "+stockCountNow+" ]股票:");


        sellStockDialog.show();
        sellStockDialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        sellStockDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


        // 如果 有 传递过来 股票 tscode  那么 就设置 ts_code
        if(mStockTsCode != null && !"".equals(mStockTsCode) && !"null".equals(mStockTsCode)){
            sell_et_code.setText(mStockTsCode);
            sell_et_code.setSelection(mStockTsCode.length());
        }



    }






}

