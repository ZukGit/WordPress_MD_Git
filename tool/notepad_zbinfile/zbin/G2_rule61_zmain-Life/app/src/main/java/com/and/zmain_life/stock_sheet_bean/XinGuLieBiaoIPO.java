package com.and.zmain_life.stock_sheet_bean;
import java.util.List;

/**
 *auto generate
 *
 *@author Zukgit
 *
 */
public class XinGuLieBiaoIPO{
    Double amount;
    Double ballot;
    String cname;
    Double funds;
    String ipo_date;
    String issue_date;
    Double limit_amount;
    Double market_amount;
    String name;
    Double pe;
    Double price;
    String sub_code;
    String ts_code;

    public Double getAmount(){
        return amount;
    }

    public void setAmount(Double amount){
        this.amount=amount;
    }

    public Double getBallot(){
        return ballot;
    }

    public void setBallot(Double ballot){
        this.ballot=ballot;
    }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public Double getFunds(){
        return funds;
    }

    public void setFunds(Double funds){
        this.funds=funds;
    }

    public String getIpo_date(){
        return ipo_date;
    }

    public void setIpo_date(String ipo_date){
        this.ipo_date=ipo_date;
    }

    public String getIssue_date(){
        return issue_date;
    }

    public void setIssue_date(String issue_date){
        this.issue_date=issue_date;
    }

    public Double getLimit_amount(){
        return limit_amount;
    }

    public void setLimit_amount(Double limit_amount){
        this.limit_amount=limit_amount;
    }

    public Double getMarket_amount(){
        return market_amount;
    }

    public void setMarket_amount(Double market_amount){
        this.market_amount=market_amount;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Double getPe(){
        return pe;
    }

    public void setPe(Double pe){
        this.pe=pe;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price=price;
    }

    public String getSub_code(){
        return sub_code;
    }

    public void setSub_code(String sub_code){
        this.sub_code=sub_code;
    }

    public String getTs_code(){
        return ts_code;
    }

    public void setTs_code(String ts_code){
        this.ts_code=ts_code;
    }

    @Override
    public String toString() {
        return "XinGuLieBiaoIPO{" +
                "amount=" + amount +
                ", ballot=" + ballot +
                ", cname='" + cname + '\'' +
                ", funds=" + funds +
                ", ipo_date='" + ipo_date + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", limit_amount=" + limit_amount +
                ", market_amount=" + market_amount +
                ", name='" + name + '\'' +
                ", pe=" + pe +
                ", price=" + price +
                ", sub_code='" + sub_code + '\'' +
                ", ts_code='" + ts_code + '\'' +
                '}';
    }
}