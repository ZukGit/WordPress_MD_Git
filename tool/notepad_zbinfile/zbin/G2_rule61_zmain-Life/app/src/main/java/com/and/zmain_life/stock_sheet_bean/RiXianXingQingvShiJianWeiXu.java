package com.and.zmain_life.stock_sheet_bean;
import java.util.List;

/**
 *auto generate
 *
 *@author Zukgit
 *
 */
public class RiXianXingQingvShiJianWeiXu{
    Double amount;
    Double change;
    Double close;
    String cname;
    Double high;
    Double low;
    Double open;
    Double pct_chg;
    Double pre_close;
    String trade_date;
    String ts_code;
    Double vol;

    //  动态增加的数据  动态增加的属性
    Double day3_pct_chg;
    Double day5_pct_chg;
    Double day10_pct_chg;
    Double day15_pct_chg;



    //  动态增加的数据  动态增加的属性
    public Double day30_pct_chg;
    public Double day60_pct_chg;
    public Double day90_pct_chg;
    public Double year_pct_chg;

    public Double getDay30_pct_chg() {
        return day30_pct_chg;
    }

    public void setDay30_pct_chg(Double day30_pct_chg) {
        this.day30_pct_chg = day30_pct_chg;
    }

    public Double getDay60_pct_chg() {
        return day60_pct_chg;
    }

    public void setDay60_pct_chg(Double day60_pct_chg) {
        this.day60_pct_chg = day60_pct_chg;
    }

    public Double getDay90_pct_chg() {
        return day90_pct_chg;
    }

    public void setDay90_pct_chg(Double day90_pct_chg) {
        this.day90_pct_chg = day90_pct_chg;
    }

    public Double getYear_pct_chg() {
        return year_pct_chg;
    }

    public void setYear_pct_chg(Double year_pct_chg) {
        this.year_pct_chg = year_pct_chg;
    }


    public Double getAmount(){
        return amount;
    }

    public void setAmount(Double amount){
        this.amount=amount;
    }

    public Double getChange(){
        return change;
    }

    public void setChange(Double change){
        this.change=change;
    }

    public Double getClose(){
        return close;
    }

    public void setClose(Double close){
        this.close=close;
    }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public Double getHigh(){
        return high;
    }

    public void setHigh(Double high){
        this.high=high;
    }

    public Double getLow(){
        return low;
    }

    public void setLow(Double low){
        this.low=low;
    }

    public Double getOpen(){
        return open;
    }

    public void setOpen(Double open){
        this.open=open;
    }

    public Double getPct_chg(){
        return pct_chg;
    }

    public void setPct_chg(Double pct_chg){
        this.pct_chg=pct_chg;
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

    public Double getVol(){
        return vol;
    }

    public void setVol(Double vol){
        this.vol=vol;
    }

    public Double getDay3_pct_chg() {
        return day3_pct_chg;
    }

    public void setDay3_pct_chg(Double day3_pct_chg) {
        this.day3_pct_chg = day3_pct_chg;
    }

    public Double getDay5_pct_chg() {
        return day5_pct_chg;
    }

    public void setDay5_pct_chg(Double day5_pct_chg) {
        this.day5_pct_chg = day5_pct_chg;
    }

    public Double getDay10_pct_chg() {
        return day10_pct_chg;
    }

    public void setDay10_pct_chg(Double day10_pct_chg) {
        this.day10_pct_chg = day10_pct_chg;
    }

    public Double getDay15_pct_chg() {
        return day15_pct_chg;
    }

    public void setDay15_pct_chg(Double day15_pct_chg) {
        this.day15_pct_chg = day15_pct_chg;
    }
}