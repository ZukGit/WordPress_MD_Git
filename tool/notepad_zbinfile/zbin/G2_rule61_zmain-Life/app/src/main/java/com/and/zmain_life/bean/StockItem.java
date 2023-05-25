package com.and.zmain_life.bean;
import java.lang.reflect.Field;
import java.util.List;

/**
 *auto generate
 *
 *@author Zukgit
 *
 */
public class StockItem {
 public   String area;
 public   String curr_type;
 public   String delist_date;
 public   String enname;
 public   String exchange;
 public   String fullname;
 public   String industry;
 public   String is_hs;
 public   String list_date;


    public int index;

    public   String list_status;
 public   String market;
 public   String name;
 public   String symbol;
 public   String ts_code;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        int curIndex = index;
        this.index = curIndex;
    }



    @Override
    public String toString() {
        return "StockItem{" +
                " index=" + index +
                ",ts_code='" + ts_code + '\'' +
                ", market='" + market + '\'' +
                ", name='" + name + '\'' +
                ",area='" + area + '\'' +
                ", list_date='" + list_date + '\'' +
                ", industry='" + industry + '\'' +

                ", fullname='" + fullname + '\'' +
                ", is_hs='" + is_hs + '\'' +
                ", curr_type='" + curr_type + '\'' +
                ", delist_date='" + delist_date + '\'' +
                ", enname='" + enname + '\'' +
                ", exchange='" + exchange + '\'' +

                ", list_status='" + list_status + '\'' +
                ", symbol='" + symbol + '\'' +

                '}';
    }

    public void  initKeyAndValue(String mfieldName , String mfieldValue){
      if(mfieldName == null){
          return;
      }
        try {

          Field[] fields =   this.getClass().getFields();
      if(fields == null){
          android.util.Log.i("StockItem"," FiledList 为空！！");
          return;
      }

            for (int i = 0; i < fields.length; i++) {
                Field fielditem = fields[i];
                String fieldName = fielditem.getName();
                if(fieldName.equals(mfieldName)){
                    fielditem.set(this,mfieldValue);
                    return;

                }
//                android.util.Log.i("StockItem"," index["+i+"] fielditem.getName()="+fielditem.getName() +"   fielditem.toString()="+fielditem.toString()  +"   fielditem.toGenericString()="+fielditem.toGenericString());
            }



        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public String getArea(){
        return area;
    }

    public void setArea(String area){
        this.area=area;
    }

    public String getCurr_type(){
        return curr_type;
    }

    public void setCurr_type(String curr_type){
        this.curr_type=curr_type;
    }

    public String getDelist_date(){
        return delist_date;
    }

    public void setDelist_date(String delist_date){
        this.delist_date=delist_date;
    }

    public String getEnname(){
        return enname;
    }

    public void setEnname(String enname){
        this.enname=enname;
    }

    public String getExchange(){
        return exchange;
    }

    public void setExchange(String exchange){
        this.exchange=exchange;
    }

    public String getFullname(){
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname=fullname;
    }

    public String getIndustry(){
        return industry;
    }

    public void setIndustry(String industry){
        this.industry=industry;
    }

    public String getIs_hs(){
        return is_hs;
    }

    public void setIs_hs(String is_hs){
        this.is_hs=is_hs;
    }

    public String getList_date(){
        return list_date;
    }

    public void setList_date(String list_date){
        this.list_date=list_date;
    }

    public String getList_status(){
        return list_status;
    }

    public void setList_status(String list_status){
        this.list_status=list_status;
    }

    public String getMarket(){
        return market;
    }

    public void setMarket(String market){
        this.market=market;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol=symbol;
    }

    public String getTs_code(){
        return ts_code;
    }

    public void setTs_code(String ts_code){
        this.ts_code=ts_code;
    }

}