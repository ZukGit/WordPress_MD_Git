package com.and.zmain_life.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/4 20:02
 * Description:This is OpenRecordModel
 */
public class SimulateStockModel {
    public SimulateStockModel() {

    }


    public List<StockSimulate_BankBean.StockSimulate_UserBean> getData() {
        return null;
    }

    public List<StockSimulate_BankBean.StockSimulate_UserBean> getData2() {
        //模拟一组数据
        StockSimulate_BankBean bankbean = new StockSimulate_BankBean();
List<StockSimulate_BankBean.StockSimulate_UserBean> userbeanList = new ArrayList<StockSimulate_BankBean.StockSimulate_UserBean>();

        for (int i = 0; i < 10; i++) {
            StockSimulate_BankBean.StockSimulate_UserBean userbean = new     StockSimulate_BankBean.StockSimulate_UserBean();
            userbean.setDaytime(getTimeStamp_YYYMMDD());

            userbean.setInitsimulatemoney(i);



            userbeanList.add(userbean);

            List<StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean> stockList = new ArrayList<StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean>();
            for (int j = 0; j < 10 ; j++) {

                StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean stockbean = new     StockSimulate_BankBean.StockSimulate_UserBean.StockSimulate_UserStockBean();
                stockbean.setStockname("name_"+i+"_"+j);
                stockbean.setStocktscode("tscode_"+i+"_"+j);
                stockbean.setStockcostforone(9);
                stockbean.setStocknowprice(10);
                stockbean.setStockkeepcount(1000);
                stockList.add(stockbean);
            }
            userbean.setStocklist(stockList);

        }
        bankbean.setSimulateuserlist(userbeanList);

        return bankbean.getSimulateuserlist();
    }


    String getTimeStamp_YYYMMDD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }







}
