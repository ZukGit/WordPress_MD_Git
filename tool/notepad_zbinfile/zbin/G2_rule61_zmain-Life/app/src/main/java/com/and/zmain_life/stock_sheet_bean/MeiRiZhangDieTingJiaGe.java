package com.and.zmain_life.stock_sheet_bean;
import java.util.List;

/**
 *auto generate
 *
 *@author Zukgit
 *
 */
public class MeiRiZhangDieTingJiaGe{
    String cname;
    Double down_limit;
    Double pre_close;
    String trade_date;
    String ts_code;
    Double up_limit;

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public Double getDown_limit(){
        return down_limit;
    }

    public void setDown_limit(Double down_limit){
        this.down_limit=down_limit;
    }

    public Double getPre_close(){
        return pre_close;
    }

    public void setPre_close(Double pre_close){
        this.pre_close=pre_close;
    }

    public String getTrade_date(){
        return trade_date;
    }

    public void setTrade_date(String trade_date){
        this.trade_date=trade_date;
    }

    public String getTs_code(){
        return ts_code;
    }

    public void setTs_code(String ts_code){
        this.ts_code=ts_code;
    }

    public Double getUp_limit(){
        return up_limit;
    }

    public void setUp_limit(Double up_limit){
        this.up_limit=up_limit;
    }

}