package com.and.zmain_life.stock_sheet_bean;
import java.util.List;

/**
 *auto generate
 *
 *@author Zukgit
 *
 */
public class GangGuTongMeiRiChengJiaoTongJi{
    Double buy_amount;
    Double buy_volume;
    Double sell_amount;
    Double sell_volume;
    String trade_date;

    public Double getBuy_amount(){
        return buy_amount;
    }

    public void setBuy_amount(Double buy_amount){
        this.buy_amount=buy_amount;
    }

    public Double getBuy_volume(){
        return buy_volume;
    }

    public void setBuy_volume(Double buy_volume){
        this.buy_volume=buy_volume;
    }

    public Double getSell_amount(){
        return sell_amount;
    }

    public void setSell_amount(Double sell_amount){
        this.sell_amount=sell_amount;
    }

    public Double getSell_volume(){
        return sell_volume;
    }

    public void setSell_volume(Double sell_volume){
        this.sell_volume=sell_volume;
    }

    public String getTrade_date(){
        return trade_date;
    }

    public void setTrade_date(String trade_date){
        this.trade_date=trade_date;
    }

}