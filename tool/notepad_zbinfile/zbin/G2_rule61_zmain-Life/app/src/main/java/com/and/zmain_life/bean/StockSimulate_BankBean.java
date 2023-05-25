package com.and.zmain_life.bean;

import android.content.Context;
import android.util.Log;

import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


//
public class StockSimulate_BankBean  implements Serializable {


    //   一个 bank   包含 多个 user ,  一个 user 包含 多个  stock
    public List<StockSimulate_UserBean> simulateuserlist;


    public  boolean  isExistUser(String  userName){
        boolean existFlag = false ;

        if(userName != null && simulateuserlist != null && simulateuserlist.size() > 0 ){

            for (int i = 0; i < simulateuserlist.size() ; i++) {
                StockSimulate_UserBean user = simulateuserlist.get(i);
                if(userName.equals(user.getUsertitle())){

                    return true;
                }
            }


        }


        return existFlag;



    }



    // 初始化 所有的 buytime  ，  如果发现当前的 buytime 为 空 那么设置为  user的 time
    public void initAllUserTime(){

        if(simulateuserlist != null && simulateuserlist.size() > 0 ){
            for (int i = 0; i < simulateuserlist.size(); i++) {
                StockSimulate_UserBean userBean =    simulateuserlist.get(i);
                String userTime = userBean.getDaytime();
                if(userBean.getStocklist() != null && userBean.getStocklist().size() > 0){

                    for (int j = 0; j < userBean.getStocklist().size(); j++) {
                        StockSimulate_UserBean.StockSimulate_UserStockBean stockItem = userBean.getStocklist().get(j);

                        if(stockItem.getStockbuytime() == null || "".equals(stockItem.getStockbuytime())){
                            stockItem.setStockbuytime(userTime);
                        }

                    }

                }

            }

        }

    }



    public  void  copyUser (StockSimulate_UserBean imageRawUser){
        StockSimulate_UserBean  copyUser = new StockSimulate_UserBean();


        copyUser.stocklist = new ArrayList<StockSimulate_UserBean.StockSimulate_UserStockBean>();

        copyUser.usertitle = imageRawUser.usertitle;
        // 设置为 可交易的 UserItem
        copyUser.isImageUserBean = false;
        copyUser.daytime = imageRawUser.daytime;
        copyUser.initsimulatemoney  = imageRawUser.initsimulatemoney;
        copyUser.restsimuletemoney = imageRawUser.restsimuletemoney;

        if(imageRawUser.stocklist != null){


            for (int i = 0; i < imageRawUser.stocklist.size(); i++) {
                StockSimulate_UserBean.StockSimulate_UserStockBean rawStock =   imageRawUser.stocklist.get(i);
                StockSimulate_UserBean.StockSimulate_UserStockBean newStock = new StockSimulate_UserBean.StockSimulate_UserStockBean();

                newStock.stocktscode = rawStock.stocktscode;
                newStock.stockkeepcount = rawStock.stockkeepcount;
                newStock.stockname = rawStock.stockname;
                newStock.isImageStockBean = false;  // 设置为 可交易的 StockItem
                newStock.predaystockprice = rawStock.predaystockprice;
                newStock.stockcostforone = rawStock.stockcostforone;
                newStock.stocknowprice = rawStock.stocknowprice;
                newStock.dynamicUserAllStockWorth = rawStock.dynamicUserAllStockWorth;
                copyUser.stocklist.add(newStock);
            }

        }

        // 设置为 可交易的 StockItem
//        for (int i = 0; i < copyUser.stocklist.size(); i++) {
//            copyUser.stocklist.get(i).setImageStockBean(false);
//        }

        simulateuserlist.add(copyUser);

    }


    public void refreshBankStockNowPriceWithJson(){
        if(simulateuserlist != null && simulateuserlist.size() > 0){

            for (int i = 0; i < simulateuserlist.size(); i++) {
                StockSimulate_UserBean  userBean =  simulateuserlist.get(i);
                userBean.refreshUserStockNowPriceWithJson();

            }

        }


    }


    public void refreshBankStockNowPriceWithSina(Context mContent , StockSimulateTradeAdapter adapter){
        if(simulateuserlist != null && simulateuserlist.size() > 0){

            for (int i = 0; i < simulateuserlist.size(); i++) {
                StockSimulate_UserBean  userBean =  simulateuserlist.get(i);
                userBean.getSinaQueryUrl();
                userBean.refreshUserStockNowPriceWithSina(mContent,adapter);

            }

        }


    }



    public void addUser(double initMoney,String dayStr,String title){
        StockSimulate_UserBean newUserBean = new StockSimulate_UserBean();
        newUserBean.setInitsimulatemoney(initMoney);
        newUserBean.setRestsimuletemoney(initMoney);   //  剩余 资金可用 和 初始化 资金 一开始是 一样的
        newUserBean.setUsertitle(title);
        newUserBean.setDaytime(dayStr);
        // 把 新加入的账户 放到 第一的位置
        List<StockSimulate_UserBean> oldUserBeanList =    getSimulateuserlist();
        List<StockSimulate_UserBean> newUserBeanList  = new ArrayList<StockSimulate_UserBean>();
        newUserBeanList.add(newUserBean);
        if(oldUserBeanList != null){
            newUserBeanList.addAll(oldUserBeanList);
        }

        setSimulateuserlist(newUserBeanList);

    }

    public void  sortUserList(){

        if(simulateuserlist != null){

            for (int i = 0; i < simulateuserlist.size(); i++) {
                simulateuserlist.get(i).sortStockList();
            }

        }


    }


    public void addUser(StockSimulate_UserBean newUserBean){
        List<StockSimulate_UserBean> oldUserBeanList =    getSimulateuserlist();
        List<StockSimulate_UserBean> newUserBeanList  = new ArrayList<StockSimulate_UserBean>();
        newUserBeanList.add(newUserBean);
        if(oldUserBeanList != null){
            newUserBeanList.addAll(oldUserBeanList);
        }

        setSimulateuserlist(newUserBeanList);

    }

    // 创建的 账户的 初始金额 和  创建的日期
    public void addUser(int initMoney,String dayStr){
        StockSimulate_UserBean newUserBean = new StockSimulate_UserBean();
        newUserBean.setInitsimulatemoney(initMoney);
        newUserBean.setRestsimuletemoney(initMoney);   //  剩余 资金可用 和 初始化 资金 一开始是 一样的
        newUserBean.setDaytime(dayStr);
        // 把 新加入的账户 放到 第一的位置
        List<StockSimulate_UserBean> oldUserBeanList =    getSimulateuserlist();
        List<StockSimulate_UserBean> newUserBeanList  = new ArrayList<StockSimulate_UserBean>();
        newUserBeanList.add(newUserBean);
        if(oldUserBeanList != null){
            newUserBeanList.addAll(oldUserBeanList);
        }

        setSimulateuserlist(newUserBeanList);

    }



    public List<StockSimulate_UserBean> getSimulateuserlist() {
        return simulateuserlist;
    }

    // 把  所有 股票详情数据 显示  隐藏的 借口
    public void setAllUserShowOrHidden(boolean isShow){
        if(simulateuserlist != null && simulateuserlist.size() > 0){
            for (int i = 0; i < simulateuserlist.size(); i++) {
                StockSimulate_UserBean userBen =    simulateuserlist.get(i);
                userBen.setDynamicisshowstock(isShow);
            }
        }

    }


    public void setSimulateuserlist(List<StockSimulate_UserBean> simulateuserlist) {
        this.simulateuserlist = simulateuserlist;
    }

    public void  addSimulateuserList(List<StockSimulate_UserBean> mSimulateUserList){
        if(simulateuserlist == null){
            simulateuserlist = new ArrayList<StockSimulate_UserBean>();
        }

        simulateuserlist.addAll(mSimulateUserList);
    }

    public  int getUserCount(){
        if(getSimulateuserlist() != null){
            return getSimulateuserlist().size();
        }

        return 0;

    }

    public String toBankJson(){



        StringBuilder mBankJsonSB = new StringBuilder();
        List<StockSimulate_UserBean>   userList = getSimulateuserlist();
        mBankJsonSB.append("{\n");


        if(userList == null || userList.size() == 0) {
            mBankJsonSB.append("\"simulateuserlist\": "+"[]\n");
        }else {
            mBankJsonSB.append("\"simulateuserlist\": "+"[\n");
            for (int i = 0; i < userList.size(); i++) {
                StockSimulate_UserBean mUserBean = userList.get(i);
                if(mUserBean != null && mUserBean.getStocklist() != null && mUserBean.getStocklist().size() > 0) {
                    System.out.println("mUserBean getStocklist 不为空    mUserBean.getStocklist().size()="+ mUserBean.getStocklist().size());
                    // 子节点 之间 使用 , 逗号 分隔
                    if(i == userList.size()-1 ) {
                        mBankJsonSB.append(mUserBean.toUserJson()+"\n");
                    }else {
                        mBankJsonSB.append(mUserBean.toUserJson()+",\n");
                    }


                }else {
                    System.out.println("mUserBean getStocklist为空    ");

                    if(mUserBean != null) {
                        if(i == userList.size()-1 ) {
                            mBankJsonSB.append(mUserBean.toUserJson()+"\n");
                        }else {
                            mBankJsonSB.append(mUserBean.toUserJson()+",\n");
                        }

                    }

                }

            }
            mBankJsonSB.append("]\n");
        }

        mBankJsonSB.append("}\n");
        return mBankJsonSB.toString();
    }

    // 一个 模拟账号 下明细
    public static class StockSimulate_UserBean {
        //  把  股票 卖光 后 的 情况 没有考虑
        //  ____________ 动态计算的属性 列表 Begin  ____________
        public  int      dynamicuserindex;  // 在 队列中的 索引  用于定位
        public   boolean dynamicisshowstock;   //   是否隐藏 stock 列表




        // 记录在 图片中的 userbin
        // 1.并不保存到 json 文件中去
        // 2.该文件不能 买入 也不能 卖出  只能 更新
        public boolean isImageUserBean;   // 是否是 记录 在 图片信息中的 UserBean
        //  ____________ 动态计算的属性 列表 END  ____________


        //新浪股票API，url类似：https://hq.sinajs.cn/list=sh600000,sh600536
        // 创建 账户下 所有股票的 请求列表




        //  ____________ Json 需要的属性 列表 Begin  ____________
        String usertitle;    //  模拟用户的标题

        public   String daytime;  // 模拟账号创建的日期
        public   double initsimulatemoney ;  //  当前初始化时模拟的总的金额
        public   double restsimuletemoney;   // 剩余可用资金
        public   List<StockSimulate_UserStockBean> stocklist;       //  持仓的集合


        public  void parseSinaReponse(String mSinaResponse){

            // var hq_str_sz300561="汇金科技,15.510,14.930,17.920,17.920,14.940,17.920,0.000,55215311,896430568.570,14716121,17.920,5300,17.910,200,17.900,1000,17.880,1400,17.830,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,2022-01-20,11:30:03,00";
            // var hq_str_sh601929="吉视传媒,3.370,3.060,3.370,3.370,3.230,3.370,0.000,226528194,762749188.000,117853312,3.370,595700,3.360,632000,3.350,281585,3.340,506900,3.330,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,2022-01-20,11:30:00,00,";

            // 如果 不是 以  var 开头 那么 跳过
            if(!mSinaResponse.startsWith("var ") || !mSinaResponse.contains(";")) {

                return;

            }

            String[] resultArr = mSinaResponse.split(";");


            for (int i = 0; i < resultArr.length; i++) {
                String resultItem = resultArr[i];
                System.out.println("index["+i+"] result["+i+"] = "+ resultItem);
                if(!resultItem.trim().startsWith("var") || !resultItem.contains("=")) {
                    continue;
                }

                String raw_tscode = resultItem.substring(0,resultItem.indexOf("=")).trim();

                String tscode = raw_tscode.replace("var hq_str_sz", "").replace("var hq_str_bj", "").replace("var hq_str_sh", "").trim();

                if(tscode == null || tscode.length() !=6){
                    continue;
                }


                String[] leftRight = resultItem.split("=");
                String right = leftRight[1].replaceAll("\"", "");
                String[] stockArray = right.split(",");
                double    preClodePrice = Double.parseDouble(stockArray[2]);
                double    nowPrice = Double.parseDouble(stockArray[3]);

                System.out.println("index["+i+"]"+"  tscode["+tscode+"]"+" nowprice["+nowPrice+"]  preClosePrice["+preClodePrice+"]   result["+i+"] = "+ resultItem);



                for (int j = 0; j < stocklist.size();  j++) {

                    StockSimulate_UserStockBean  stockItem  = stocklist.get(j);
                    if(stockItem.getStocktscode().contains(tscode)){   // 更新 它的数据
                        stockItem.setPredaystockprice(preClodePrice);
                        stockItem.setStocknowprice(nowPrice);

                    }
                }


            }



        }



        // 解析 TX 股价返回的api
        public  void parseTXReponse(String mSinaResponse){

            // v_sz000002="51~万 科Ａ~000002~21.56~22.11~21.92~421353~205487~215865~21.55~797~21.54~284~21.53~392~21.52~1086~21.51~2609~21.56~127~21.57~420~21.58~16~21.59~80~21.60~193~~20220121110815~-0.55~-2.49~22.07~21.55~21.56/421353/917441968~421353~91744~0.43~6.54~~22.07~21.55~2.35~2095.10~2506.43~1.10~24.32~19.90~0.92~4332~21.77~11.26~6.04~~~0.95~91744.1968~0.0000~0~ ~GP-A~9.11~4.15~5.79~16.84~2.74~33.35~17.52~-1.51~9.72~12.70~9717553125~11625383750~72.15~-18.43~9717553125";
            // v_sz000002="51~万 科Ａ~000002~21.53~22.11~21.92~430517~205914~224603~21.53~254~21.52~341~21.51~32~21.50~9913~21.49~331~21.55~2510~21.56~147~21.57~373~21.58~17~21.59~80~~20220121110845~-0.58~-2.62~22.07~21.50~21.53/430517/937168735~430517~93717~0.44~6.53~~22.07~21.50~2.58~2092.19~2502.95~1.10~24.32~19.90~0.94~7744~21.77~11.25~6.03~~~0.95~93716.8735~0.0000~0~ ~GP-A~8.96~4.01~5.80~16.84~2.74~33.35~17.52~-1.64~9.57~12.55~9717553125~11625383750~55.32~-18.54~9717553125"; v_sh600519="1~贵州茅台~600519~1982.54~1969.52~1960.00~15600~8190~7410~1982.37~3~1982.20~1~1982.00~2~1980.85~1~1980.00~19~1983.99~3~1984.00~18~1984.14~1~1984.85~2~1984.94~1~~20220121110845~13.02~0.66~1993.49~1951.01~1982.54/15600/3087034772~15600~308703~0.12~49.67~~1993.49~1951.01~2.16~24904.62~24904.62~14.28~2166.47~1772.57~1.28~1~1978.87~50.12~53.33~~~1.38~308703.4772~0.0000~0~ ~GP-A~-3.29~6.19~0.97~28.76~23.70~2608.59~1525.50~2.09~-6.48~8.60~1256197812~1256197812~1.96~-1.99~1256197812";
            // 如果 不是 以  var 开头 那么 跳过
            if(!mSinaResponse.startsWith("v_") || !mSinaResponse.contains(";")) {

                return;

            }

            String[] resultArr = mSinaResponse.split(";");


            for (int i = 0; i < resultArr.length; i++) {
                String resultItem = resultArr[i];
                System.out.println("index["+i+"] result["+i+"] = "+ resultItem);
                if(!resultItem.trim().startsWith("v_") || !resultItem.contains("=") || !resultItem.contains("~")) {
                    continue;
                }

                String raw_tscode = resultItem.substring(0,resultItem.indexOf("=")).trim();

                String tscode = raw_tscode.replace("v_sz", "").replace("v_sh", "").replace("v_bj", "").trim();

                if(tscode == null || tscode.length() !=6){
                    continue;
                }


                double    nowPrice  = 0 ;
                double    preClodePrice = 0 ;

                String[] leftRight = resultItem.split("=");
                String right = leftRight[1].replaceAll("\"", "");
                String[] stockArray = right.split("~");

//            for (int j = 0; j < stockArray.length; j++) {
//				System.out.println("splitArr["+j+"] = "+ stockArray[j]);
//			}

                if(stockArray != null && stockArray.length > 6) {
//
//            	        splitArr[1] = 万 科Ａ
//            			splitArr[2] = 000002
//            			splitArr[3] = 21.53
//            			splitArr[4] = 22.11

                    String responseTScode = 	stockArray[2].trim();
                    if(tscode.equals(responseTScode)) {



                        nowPrice = Double.parseDouble(stockArray[3]);
                        preClodePrice = Double.parseDouble(stockArray[4]);


                    }

                }


                System.out.println("TXAPI: index["+i+"]"+"  tscode["+tscode+"]"+" nowprice["+nowPrice+"]  preClosePrice["+preClodePrice+"]   result["+i+"] = "+ resultItem);


                if(nowPrice != 0 && preClodePrice != 0){
                    for (int j = 0; j < stocklist.size();  j++) {

                        StockSimulate_UserStockBean  stockItem  = stocklist.get(j);
                        if(stockItem.getStocktscode().contains(tscode)){   // 更新 它的数据
                            stockItem.setPredaystockprice(preClodePrice);
                            stockItem.setStocknowprice(nowPrice);

                        }
                    }
                }


            }



        }


        //  貌似是腾讯的 股价的api 接口
        public String getQTQueryUrl(){
            StringBuilder queryStockListSB = new StringBuilder();
            String queryUrl = null;

            if(stocklist != null && stocklist.size() > 0){

                for (int i = 0; i < stocklist.size(); i++) {
                    StockSimulate_UserStockBean  stockItem  = stocklist.get(i);
                    String mTscode = stockItem.getStocktscode();

                    String  shTscode = "sh"+mTscode;
                    if(mTscode.startsWith("6")){
                        shTscode = "sh"+mTscode;
                    } else if(mTscode.startsWith("8")){
                        shTscode = "bj"+mTscode;
                    } else {
                        shTscode = "sz"+mTscode;
                    }

                    if (shTscode == null || shTscode.equals("") || shTscode.length() != 8) {
                        continue;
                    }
                    queryStockListSB.append(shTscode+",");

                }
                String queryListStr = queryStockListSB.toString();
                if(queryListStr.endsWith(",")){
                    queryListStr = queryListStr.substring(0,queryListStr.length()-1);

                }

                queryUrl = "http://qt.gtimg.cn/q="+queryListStr;

            }

            android.util.Log.i("TXQueryUrl","userTitle["+getUsertitle()+"]  queryUrl="+ queryUrl);
            return queryUrl;

        }


        public String getSinaQueryUrl(){
            StringBuilder queryStockListSB = new StringBuilder();
            String queryUrl = null;

            if(stocklist != null && stocklist.size() > 0){

                for (int i = 0; i < stocklist.size(); i++) {
                    StockSimulate_UserStockBean  stockItem  = stocklist.get(i);
                    String mTscode = stockItem.getStocktscode();

                    String  shTscode = "sh"+mTscode;
                    if(mTscode.startsWith("6")){
                        shTscode = "sh"+mTscode;
                    } else if(mTscode.startsWith("8")){
                        shTscode = "bj"+mTscode;
                    } else {
                        shTscode = "sz"+mTscode;
                    }

                    if (shTscode == null || shTscode.equals("") || shTscode.length() != 8) {
                        continue;
                    }
                    queryStockListSB.append(shTscode+",");

                }
                String queryListStr = queryStockListSB.toString();
                if(queryListStr.endsWith(",")){
                    queryListStr = queryListStr.substring(0,queryListStr.length()-1);

                }

                queryUrl = "https://hq.sinajs.cn/list="+queryListStr;

            }

            android.util.Log.i("SinaQueryUrl","userTitle["+getUsertitle()+"]  queryUrl="+ queryUrl);
            return queryUrl;

        }

        public void sortStockList(){

            if(stocklist != null && stocklist.size() > 0){

                stocklist.sort(new Comparator<StockSimulate_UserStockBean>() {
                    @Override
                    public int compare(StockSimulate_UserStockBean o1, StockSimulate_UserStockBean o2) {

                        double diff =    o1.getStockkeepcount()*o1.getStocknowprice() -  o2.getStockkeepcount()*o2.getStocknowprice();
                        if(diff == 0){
                            return 0 ;
                        }
                        return diff > 0 ? -1: 1;
                    }
                });

            }



        }


        public String getExifTag(){
            StringBuilder tagSB = new StringBuilder();

            DecimalFormat decimalFormat  = new DecimalFormat("#0.000");
            DecimalFormat priceFormat  = new DecimalFormat("#0.000");
            // 【创建日期:】【初始资金:】【万科A:code:买入价格:买入数量】
            tagSB.append("【创建日期:"+daytime+"】");
            tagSB.append("【初始资金:"+decimalFormat.format(initsimulatemoney)+"】");
            if(usertitle != null && !"null".equals(usertitle) && !"".equals(usertitle.trim())){
                tagSB.append("【标题:"+usertitle+"】");
            }else{
                tagSB.append("【标题:】");
            }

            for (int i = 0; i < stocklist.size(); i++) {
                StockSimulate_UserStockBean stockBean = stocklist.get(i);
                tagSB.append("【"+stockBean.stockname+":"+stockBean.stocktscode+":"+priceFormat.format(stockBean.getStockcostforone())+":"+stockBean.stockkeepcount+"】");
            }
            return tagSB.toString();

        }

        public boolean isImageUserBean() {
            return isImageUserBean;
        }

        public void setImageUserBean(boolean imageUserBean) {
            isImageUserBean = imageUserBean;
        }


        // 每次 都刷新 有错误
        public  void refreshUserStockNowPriceWithSina(Context mContent , StockSimulateTradeAdapter adapter){

            if(stocklist != null && stocklist.size() > 0){

                StockHolder.queryUserStockListWithSina(this,mContent,adapter);
//                for (int i = 0; i < stocklist.size(); i++) {
//                    StockSimulate_UserStockBean  stockBean = stocklist.get(i);
//                    stockBean.refreshStockNowPriceWithSina(mContent , adapter);
//
//                }

            }



        }

        public  void refreshUserStockNowPriceWithJson(){

            if(stocklist != null && stocklist.size() > 0){

                for (int i = 0; i < stocklist.size(); i++) {
                    StockSimulate_UserStockBean  stockBean = stocklist.get(i);
                    // 只是用 从 新浪 得到的 数据 来显示
                    //  stockBean.refreshStockNowPriceWithJson();
                }

            }



        }

        public String getUsertitle() {
            return usertitle;
        }

        public void setUsertitle(String usertitle) {
            this.usertitle = usertitle;
        }


        public double getRestsimuletemoney() {  // 设置剩余 可用资金
            if(isImageUserBean){   // 如果是 从 图片 读取到的 User的话 那么 剩余金钱就是 初始化金钱减去持股成本
                return getInitsimulatemoney() - getStockCostWorth();
            }

            return restsimuletemoney;
        }

        public void setRestsimuletemoney(double xRestsimuletemoney) {
            this.restsimuletemoney = xRestsimuletemoney;
        }


        //  ____________ Json 需要的属性 列表 END  ____________





        //    ___________ 动态计算方法  Begin  ___________





/*
        // 动态计算 剩余的现金
        public   double getRestsimuletemoney(){
            double restAvaliableMoney = 0 ;
//         double getBenifit = getBenifitMoney();
            if(getStocklist() != null && getStocklist().size() > 0){

                // 如果 有股票 那么就是初  所有的资产 减去 股票的买入价格  就是 可用价格
//             restAvaliableMoney = allWorthMoney -  stockWorth ;
                restAvaliableMoney = getAllworth() -  getStockCostWorth();
            }else{
                // 如果 股票市值为0  利润为0  那么说明才初始化 完成 那么可用余额就是 初始化金额
                restAvaliableMoney = getInitsimulatemoney();

            }
            return restAvaliableMoney;
        }
*/


        // 总资产
        public double getAllworth() {
            double allWorthValue = 0;

            if(getStocklist() != null && getStocklist().size() > 0 ){
                double restMoney =    getRestsimuletemoney();  //
                allWorthValue+=restMoney;

                for (int i = 0; i < getStocklist().size(); i++) {
                    double stockprice =    getStocklist().get(i).getStocknowprice();
                    int stockCount =    getStocklist().get(i).getStockkeepcount();

                    allWorthValue+=stockCount*stockprice;
                }
            }else{
                allWorthValue = getRestsimuletemoney();
            }
            return allWorthValue;
        }




        // 股票 现在的 价格
        public double getStockNowWorth() {
            double allStockWorth = 0;
            if(getStocklist() != null && getStocklist().size() > 0 ){
                for (int i = 0; i < getStocklist().size(); i++) {
                    double stockprice =    getStocklist().get(i).getStocknowprice();
                    int stockCount =    getStocklist().get(i).getStockkeepcount();

                    allStockWorth+=stockCount*stockprice;
                }
            }
            return allStockWorth;
        }

        //股票的成本
        public double getStockCostWorth() {
            double allStockCost = 0;
            if(getStocklist() != null && getStocklist().size() > 0 ){
                for (int i = 0; i < getStocklist().size(); i++) {
                    double stockcost =    getStocklist().get(i).getStockcostforone();
                    int stockCount =    getStocklist().get(i).getStockkeepcount();

                    allStockCost+=stockCount*stockcost;
                }
            }
            return allStockCost;
        }

        // 盈利数据    //  股票的价值 + 可用现金 - 初始化金额 就是 盈利的数据
        //  // 利润亏损   总盈亏
        public double    getBenifitMoney(){
            return getStockNowWorth() + getRestsimuletemoney()   -  getInitsimulatemoney();
        }

        //    ___________ 动态计算方法  End  ___________



        //  ____________业务方法 列表 Begin   ____________

        // 卖出股票
        public void    sellExistStock(StockSimulate_UserStockBean mSellStockBean){

            if(getStocklist() != null && getStocklist().size() > 0){

                for (int i = 0; i <  getStocklist().size(); i++) {
                    StockSimulate_UserStockBean existStockBean =    getStocklist().get(i);
                    String existTscode = existStockBean.stocktscode;

                    String newTscode = mSellStockBean.stocktscode;
                    if(existTscode != null &&  newTscode != null && !"".equals(newTscode) && newTscode.equals(existTscode)){

                        //  卖出的价格
                        double newStockPriceForOne = mSellStockBean.getStockcostforone();
                        // 卖出的数量
                        int mSellStockCount = mSellStockBean.getStockkeepcount();
                        // 卖出的金额
                        double newSellStockMoney = newStockPriceForOne * mSellStockCount;




                        // 当前卖出后剩余的股票
                        int refreshStockCount = existStockBean.getStockkeepcount() - mSellStockCount;
                        // 卖出后  股票的成本
                        double refreshCostMoney = existStockBean.getDynamicStockAllCost() - newSellStockMoney;


                        // 新的股票的成本价
                        double  refreshCostPriceForOne = refreshCostMoney/refreshStockCount;


                        // 卖出股票的盈利情况
                        double mSellBenifit = mSellStockCount * (mSellStockBean.getStocknowprice()-mSellStockBean.getStockcostforone());

                        double userAvaliableMoney =    StockHolder.mBankBean.getSimulateuserlist().get(mSellStockBean.getDynamicparentuserindex()).getRestsimuletemoney();

                        double freshAvaliableMoney = userAvaliableMoney + newSellStockMoney;
                        StockHolder.mBankBean.getSimulateuserlist().get(mSellStockBean.getDynamicparentuserindex()).setRestsimuletemoney(freshAvaliableMoney);




                        existStockBean.setStockkeepcount(refreshStockCount);
                        existStockBean.setStockcostforone(refreshCostPriceForOne);
                        existStockBean.setStocknowprice(mSellStockBean.getStocknowprice());


                        // 如果当前 股票 数量是 0  那么 意味着  已经 把股票 全部卖光了  需要删除这个 stockBean
                        if(refreshStockCount == 0){
                            StockHolder.mBankBean.getSimulateuserlist().get(mSellStockBean.getDynamicparentuserindex()).getStocklist().remove(mSellStockBean.getDynamicStockIndex());
                        }

//                        stockNew.setStockcount(Integer.parseInt(buyCountStr));  //  买入数量
//                        stockNew.setStock_tscode(tsCodeStr);
//                        stockNew.setStockcostforone(Double.parseDouble(buyPriceStr));
//                        stockNew.setStockname(buyStockNameStr);



                    }
                }

            }



        }
        public void addNewStock(StockSimulate_UserStockBean stockBean){
            buyNewStock(stockBean);
        }

        // 添加新的股票
        public void buyNewStock(StockSimulate_UserStockBean stockBean){


            List<StockSimulate_UserStockBean> oldStockBeanList =    getStocklist();
            List<StockSimulate_UserStockBean> newStockBeanList  = new ArrayList<StockSimulate_UserStockBean>();
            newStockBeanList.add(stockBean);
            if(oldStockBeanList != null){
                newStockBeanList.addAll(oldStockBeanList);
            }
            // 可用余额 减去  成本  就是 最新的 可用余额
            setRestsimuletemoney(getRestsimuletemoney()-stockBean.getDynamicStockAllCost());

            setStocklist(newStockBeanList);
        }


        // 买入股已经存在的一只股票 但 价格不同的时候
        public void    addExistStock(StockSimulate_UserStockBean stockBean){

            if(getStocklist() != null && getStocklist().size() > 0){

                for (int i = 0; i <  getStocklist().size(); i++) {
                    StockSimulate_UserStockBean existStockBean =    getStocklist().get(i);
                    String existTscode = existStockBean.stocktscode;
                    String newTscode = stockBean.stocktscode;
                    if(existTscode != null &&  newTscode != null && !"".equals(newTscode) && newTscode.equals(existTscode)){

                        double newStockPriceForOne = stockBean.getStockcostforone();
                        int newStockCount = stockBean.getStockkeepcount();
                        double newAllCostMoney = newStockPriceForOne * newStockCount;




                        int refreshStockCount = existStockBean.getStockkeepcount() + newStockCount;
                        double refreshCostMoney = existStockBean.getDynamicStockAllCost() + newAllCostMoney;
                        double  refreshCostPriceForOne = refreshCostMoney/refreshStockCount;
                        existStockBean.setStockkeepcount(refreshStockCount);
                        existStockBean.setStockcostforone(refreshCostPriceForOne);
                        existStockBean.setStocknowprice(stockBean.getStocknowprice());

                        // 新买的股票的时间 覆盖掉 原有的时间
                        existStockBean.setStockbuytime(StockHolder.getTimeStamp_YYYY_MM_DD());


//                        stockNew.setStockcount(Integer.parseInt(buyCountStr));  //  买入数量
//                        stockNew.setStock_tscode(tsCodeStr);
//                        stockNew.setStockcostforone(Double.parseDouble(buyPriceStr));
//                        stockNew.setStockname(buyStockNameStr);



                    }
                }

            }


        }

        // 是否 包含股票
        public boolean    isContainStock(StockSimulate_UserStockBean stockBean){
            boolean isExist =false;
            if(getStocklist() != null && getStocklist().size() > 0){

                for (int i = 0; i <  getStocklist().size(); i++) {
                    StockSimulate_UserStockBean existStockBean =    getStocklist().get(i);
                    String existTscode = existStockBean.stocktscode;
                    String newTscode = stockBean.stocktscode;
                    if(existTscode != null &&  newTscode != null && !"".equals(newTscode) && newTscode.equals(existTscode)){

                        return true;
                    }
                }

            }

            return isExist;
        }

        //  ____________业务方法 列表 Begin   ____________









        public int getDynamicuserindex() {
            return dynamicuserindex;
        }

        public void setDynamicuserindex(int dynamicuserindex) {
            this.dynamicuserindex = dynamicuserindex;
        }


        void StockSimulate_UserBean(int userIndex){
            this.dynamicuserindex = userIndex;
        }


        public boolean isDynamicisshowstock() {
            return dynamicisshowstock;
        }

        public void setDynamicisshowstock(boolean dynamicisshowstock) {
            this.dynamicisshowstock = dynamicisshowstock;
        }



        public String getDaytime() {
            return daytime.trim();
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public double getInitsimulatemoney() {
            return initsimulatemoney;
        }

        public void setInitsimulatemoney(double initsimulatemoney) {
            this.initsimulatemoney = initsimulatemoney;
        }



        public String getAllProfirRateStr(){

            DecimalFormat    decimalFormat = new DecimalFormat("##.##%");
            return decimalFormat.format(getAllProfirRateDouble());
        }


        public double getAllProfirRateDouble(){

            double profitMoney =    getBenifitMoney();
            double allStockNowWorth = getStockNowWorth();

            double mAllProfitRate = 0 ;
            if(profitMoney < 0){
                mAllProfitRate = profitMoney/(allStockNowWorth+Math.abs(profitMoney));
            }else{
                mAllProfitRate = profitMoney/(profitMoney+allStockNowWorth);
            }
            return mAllProfitRate;
        }


        public String getTodayProfirRateStr(){

            DecimalFormat    decimalFormat = new DecimalFormat("##.##%");
            return decimalFormat.format(getTodayProfirRateDouble());

        }


        // 是以 昨日的 收盘价 为分母   今日的盈利为分子
        // 现在是 以当前的 价格为分母  有错误

        public double getTodayProfirRateDouble(){

            double todayProfirValue  =  getTodayProfirValueDouble();
            double allStockNowWorth = getStockNowWorth();


            double mTodayProfitRate = 0 ;
            if(todayProfirValue < 0){
                mTodayProfitRate = todayProfirValue/(allStockNowWorth+Math.abs(mTodayProfitRate));
            }else{
                mTodayProfitRate = todayProfirValue/(todayProfirValue+allStockNowWorth);
            }


            return mTodayProfitRate;

        }



        public String getTodayProfirValueStr(){
            DecimalFormat    decimalFormat = new DecimalFormat(",#00.00");
            return decimalFormat.format(getTodayProfirValueDouble());

        }

        public double getTodayProfirValueDouble(){

            double mUserTodayProfitValue = 0;
            if(stocklist == null){
                return 0;
            }

            for (int i = 0; i < stocklist.size(); i++) {

                StockSimulate_UserStockBean stockItem = stocklist.get(i);
                double stockTodayProbitValue = stockItem.getTodayStockProfitValueDouble();

                mUserTodayProfitValue += stockTodayProbitValue;
            }

            return mUserTodayProfitValue ;

        }




        public String getCangWeiRate(){

            double canweiRate = (1 - (getRestsimuletemoney()/getInitsimulatemoney()));

            DecimalFormat decimalFormat  = new DecimalFormat("##.#%");

            return  decimalFormat.format(canweiRate);

        }



        public List<StockSimulate_UserStockBean> getStocklist() {
            return stocklist;
        }

        public void setStocklist(List<StockSimulate_UserStockBean> stocklist) {
            this.stocklist = stocklist;
        }




        public String  toUserJson(){


            if(isImageUserBean){
                return "";  // Image 的 UserBean 并不保存到 json 文件中去
            }
            StringBuilder mUserJsonSB = new StringBuilder();
            List<StockSimulate_UserStockBean>   mStockList = getStocklist();

            DecimalFormat decimalFormat  = new DecimalFormat("#0.000000");

            mUserJsonSB.append("{\n");

            mUserJsonSB.append("\"daytime\": "+"\""+daytime+"\""+",\n");
            mUserJsonSB.append("\"usertitle\": "+"\""+ usertitle +"\""+",\n");


            mUserJsonSB.append("\"initsimulatemoney\": "+decimalFormat.format(initsimulatemoney)+",\n");
            mUserJsonSB.append("\"restsimuletemoney\": "+decimalFormat.format(restsimuletemoney)+",\n");

            if(mStockList == null || mStockList.size() == 0) {
                mUserJsonSB.append("\"stocklist\": "+"[]\n");
            }else {
                mUserJsonSB.append("\"stocklist\": "+"[\n");
                for (int i = 0; i < mStockList.size(); i++) {
                    StockSimulate_UserStockBean mStockBean = mStockList.get(i);
                    if(mStockBean != null && mStockBean != null ) {
                        System.out.println("mUserBean mStockBean 不为空    mStockBean ="+ mStockBean);
                        // 子节点 之间 使用 , 逗号 分隔
                        if(i == mStockList.size()-1 ) {
                            mUserJsonSB.append(mStockBean.toStockJson()+"\n");
                        }else {
                            mUserJsonSB.append(mStockBean.toStockJson()+",\n");
                        }


                    }else {
                        System.out.println("mUserBean getStocklist为空    ");

                        if(mStockBean != null) {
                            if(i == mStockList.size()-1 ) {
                                mUserJsonSB.append(mStockBean.toStockJson()+"\n");
                            }else {
                                mUserJsonSB.append(mStockBean.toStockJson()+",\n");
                            }

                        }

                    }

                }
                mUserJsonSB.append("]\n");
            }

            mUserJsonSB.append("}\n");

            return mUserJsonSB.toString();

        }


        // 一个  股票 stock 持仓明细
        public static class StockSimulate_UserStockBean {


            // __________  dynamic 动态数据数据 Begin __________
            public int dynamicstockindex;
            public int dynamicparentuserindex;



            // 当用所占股票的 用户的 股票的总价值 , 用于计算自身所占百分比
            public double dynamicUserAllStockWorth;


            public double getDynamicUserAllStockWorth() {
                return dynamicUserAllStockWorth;
            }

            public void setDynamicUserAllStockWorth(double dynamicUserAllStockWorth) {
                this.dynamicUserAllStockWorth = dynamicUserAllStockWorth;
            }

            // 记录在 图片中的 userbin
            // 1.并不保存到 json 文件中去
            // 2.该文件不能 买入 也不能 卖出  只能 更新
            public boolean isImageStockBean;   // 是否是 记录 在 图片信息中的 StockBean

            // __________  dynamic 动态数据数据 End   __________


            // __________  Json 标记 数据数据 Begin    __________

            // 持有 股票的数量
            public int stockkeepcount;
            public String stockname;      //  股票名称
            public String stocktscode;   // 股票代码   600001   不包含.SH  .SZ  .BJ
            public double stockcostforone;// 每股股票成本
            public double stocknowprice;//股票现价


            public   String stockbuytime;  //  购买股票的时间 YYYY.MM.DD
            // __________  Json 标记 数据数据 Begin    __________



            // Json 中动态得到的数据
            public double day3_flowrate;// 3 天的浮动
            public double day5_flowrate;// 5 天的浮动
            public double day10_flowrate;// 10 天的浮动
            public double day15_flowrate;// 15 天的浮动



            // 从Network 获取到的数据 Begin
            public double predaystockprice;//股票昨天的收盘价
//1.今日涨跌幅
//2.今日涨跌值
//3.今日浮动盈亏
//4.昨日收盘价格


            public String getStockbuytime() {
                return stockbuytime;
            }

            public void setStockbuytime(String stockbuytime) {
                this.stockbuytime = stockbuytime;
            }




            //1.今日涨跌幅
            public        String getTodayStockUpDownRate(){
                String todayRate = "";


                Log.i("ShowRateX_A"," predaystockprice="+predaystockprice+"   stocknowprice="+stocknowprice+"  name="+stockname+"  isImage="+isImageStockBean);
                if(predaystockprice == 0 || getStocknowprice() == 0){   // 还没获取到 昨日的 股价
                    return todayRate;
                }
                Log.i("ShowRateX_B"," predaystockprice="+predaystockprice+"   stocknowprice="+stocknowprice+"  name="+stockname+"  isImage="+isImageStockBean);

//                double diffRate = ((getStocknowprice() - predaystockprice)/getStocknowprice() ) * 100;
                double diffRate = ((getStocknowprice() - predaystockprice)/predaystockprice ) * 100;   // 应该以昨日的收盘价 作为分母
                DecimalFormat    decimalFormat = new DecimalFormat(",#00.00");
                Log.i("ShowRateX_C","diffRate="+diffRate+" predaystockprice"+predaystockprice+"+   stocknowprice="+stocknowprice+"  name="+stockname+"  isImage="+isImageStockBean);

                return decimalFormat.format(diffRate)+"%";
            }
            // 从Network 获取到的数据 End



            //2.今日涨跌值
            public       String getTodayStockUpDownValue(){
                String todayValue = "";

                if(predaystockprice == 0 || getStocknowprice() == 0){   // 还没获取到 昨日的 股价
                    return todayValue;
                }
                double diffValue = (getStocknowprice() - predaystockprice);

                DecimalFormat    decimalFormat = new DecimalFormat(",#00.00");
                return decimalFormat.format(diffValue);
            }

            //3.今日浮动盈亏
            public     String getTodayStockProfitValueStr(){
                String todayValue = "";

                if(predaystockprice == 0 || getStocknowprice() == 0){   // 还没获取到 昨日的 股价
                    return todayValue;
                }
                double diffValue = (getStocknowprice() - predaystockprice);
                double todayProfitValue = diffValue * getStockkeepcount();
                Log.i("ProfitValue","todayProfitValue = "+ todayProfitValue);
//           todayProfitValue = -2000.0000000000462

                DecimalFormat    decimalFormat = new DecimalFormat(",#00.00");
                return decimalFormat.format(todayProfitValue);
            }

            public     double getTodayStockProfitValueDouble(){

                if(predaystockprice == 0 || stocknowprice == 0){   // 还没获取到 昨日的 股价
                    return 0;
                }
                double diffValue = (stocknowprice - predaystockprice);
                double todayProfitValue = diffValue * getStockkeepcount();

                return todayProfitValue;
            }



            public double getPredaystockprice() {
                return predaystockprice;
            }

            //  4.昨日收盘价格
            public String getPredaystockPriceStr() {
                if(predaystockprice == 0 ){
                    return "" ;
                }

                DecimalFormat    decimalFormat = new DecimalFormat(",#00.00");
                return decimalFormat.format(predaystockprice);

            }



            public boolean isImageStockBean() {
                return isImageStockBean;
            }

            public void setImageStockBean(boolean imageStockBean) {
                isImageStockBean = imageStockBean;
            }



            public  void refreshStockNowPriceWithSina(Context mContent,StockSimulateTradeAdapter adapter){
                if(stocktscode != null ){

                    // 访问新浪接口 来 得到 实时 数据
                    //新浪股票API，url类似：http://hq.sinajs.cn/list=sh600000,sh600536

                    StockHolder.queryStockWithSina(this,mContent,adapter);
//                    queryStockWithSina

                }




            }




            public void setPredaystockprice(double predaystockprice) {
                this.predaystockprice = predaystockprice;
            }


            public String getTodayZhangTingPriceStr(){
                double upValue = 0 ;
                DecimalFormat priceFormat  = new DecimalFormat("#0.00");
                String resultStr = "";
                if(stocktscode.startsWith("3")){  // 创业板
                    upValue = predaystockprice * 0.2+predaystockprice;
                    resultStr =  priceFormat.format(upValue);
                } else if(stocktscode.startsWith("8")){
                    resultStr =  "+∞";

                }else if(predaystockprice == 0){
                    resultStr ="NaN";

                } else {

                    upValue = predaystockprice * 0.1+predaystockprice;
                    resultStr =  priceFormat.format(upValue);
                }

                return resultStr;
            }


            public String getTodayDieTingPriceStr(){
                DecimalFormat priceFormat  = new DecimalFormat("#0.00");
                double upValue = 0 ;
                String resultStr = "";
                if(stocktscode.startsWith("3")){  // 创业板
                    upValue = predaystockprice - predaystockprice * 0.2;
                    resultStr =  priceFormat.format(upValue);
                } else if(stocktscode.startsWith("8")){
                    resultStr =  "-∞";

                }else if(predaystockprice == 0){
                    resultStr ="NaN";

                } else {

                    upValue = predaystockprice - predaystockprice * 0.1;
                    resultStr =  priceFormat.format(upValue);
                }

                return resultStr;
            }




            public  void refreshStockNowPriceWithJson(){
                if(stocktscode != null ){

                    Stock_NodeImpl stockNode = StockHolder.getStockNodeWithTsCodeNoSHSZ(stocktscode);
                    Log.i("refreshStockNowPrice"," stocktscode="+stocktscode+"   stockNode = "+ stockNode + "【str=】"+toString());
                    if(stockNode != null && stockNode.price != 0){
                        setStocknowprice(stockNode.price);

                    }
                }




            }

            public String  toStockJson(){

                DecimalFormat decimalFormat  = new DecimalFormat("#0.000000");
                StringBuilder mStockJsonSB = new StringBuilder();

                mStockJsonSB.append("{\n");

                mStockJsonSB.append("\"stockkeepcount\": "+stockkeepcount+",\n");  // int 值   没有引号

                mStockJsonSB.append("\"stockbuytime\": "+   "\""+stockbuytime+"\""+",\n");  // 字符串 有引号  购买股票的时间
                mStockJsonSB.append("\"stockname\": "+   "\""+stockname+"\""+",\n");
                mStockJsonSB.append("\"stocktscode\": "+  "\""+stocktscode+"\""+",\n");
                mStockJsonSB.append("\"stockcostforone\": "+decimalFormat.format(stockcostforone)+",\n");
                mStockJsonSB.append("\"stocknowprice\": "+decimalFormat.format(stocknowprice)+"\n");
                mStockJsonSB.append("}\n");

                return mStockJsonSB.toString();
            }


            public int getStockkeepcount() {
                return this.stockkeepcount;
            }

            public void setStockkeepcount(int mstockcount) {
                this.stockkeepcount = mstockcount;
            }

            // public   double stocknowvalue;//股票市值   // 动态计算的结果

            //       public   double stockprofit;//股票盈亏   // 动态计算出来的




            // 现在股票的价值
            public double getDynamicStockNowValue() {
                return getStockkeepcount() * getStocknowprice();
            }

            //
            public int getDynamicparentuserindex() {
                return dynamicparentuserindex;
            }

            public void setDynamicparentuserindex(int dynamicparentuserindex) {
                this.dynamicparentuserindex = dynamicparentuserindex;
            }


            public double getDynamicStockAllCost() {
                return getStockcostforone() * getStockkeepcount();
            }


            public int getDynamicStockIndex() {
                return dynamicstockindex;
            }



            public void setDynamicstockindex(int dynamicstockindex) {
                this.dynamicstockindex = dynamicstockindex;
            }


            public String getStockname() {
                return stockname;
            }

            public void setStockname(String stockname) {
                this.stockname = stockname;
            }

            public String getStocktscode() {
                return stocktscode;
            }

            public void setStocktscode(String stocktscode) {
                this.stocktscode = stocktscode;
            }


            // 获取当前自己的 股票所占总市值的百分比
            public String getSelfStockOnAllPercentTip(){
                String tip="";

                if(dynamicUserAllStockWorth == 0){
                    return "";
                }
                double nowStockPrice = getStocknowprice() * getStockkeepcount();


                double stockWothRate= (nowStockPrice/dynamicUserAllStockWorth) * 100;

                DecimalFormat priceFormat  = new DecimalFormat("#");

                return  ""+priceFormat.format(stockWothRate)+"%";


            }

            public double getStockcostforone() {
                return stockcostforone;
            }

            public void setStockcostforone(double stockcostforone) {
                this.stockcostforone = stockcostforone;
            }

            public double getStocknowprice() {
                if(stocknowprice == 0){
                    return getStockcostforone();
                }
                return stocknowprice;
            }

            public void setStocknowprice(double mStocknowprice) {
                this.stocknowprice = mStocknowprice;
            }





            // 浮动盈亏
            public double getStockprofit() {

                return getDynamicStockNowValue() - getDynamicStockAllCost();
            }


            // 盈亏比例 *
            public double getDynamicStockProfitRate() {
                return (getStockprofit()/ getDynamicStockAllCost()) * 100 ;

            }




            @Override
            public String toString() {
                return "StockSimulate_UserStockBean{" +
                        "stockindex=" + dynamicstockindex +
                        ", stockcount=" + stockkeepcount +
                        ", stockname='" + stockname + '\'' +
                        ", stock_tscode='" + stocktscode + '\'' +
                        ", stockcostforone=" + stockcostforone +
                        ", stocknowprice=" + stocknowprice +
                        ", stocknowvalue=" + getDynamicStockNowValue() +
                        ", stockprofit=" + getStockprofit() +
                        '}';
            }
        }
    }


}
