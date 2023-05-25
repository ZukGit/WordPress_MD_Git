package com.and.zmain_life.loan_calculate;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.fragment.Loan_Calculate_Fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public  class HomeDownPay {



    String cal_name;
    public String getCal_name() {
        return cal_name;
    }

    public void setCal_name(String cal_name) {
        this.cal_name = cal_name;
    }




    enum LoanType{
        GONGJIJIN ,   // 公积金贷款
        SHANGYE ,     // 商业贷款
        HUNHE        // 混合贷款
    }  ;


    double buyer_buyhome_zengzhishui_percent;  // 增值税百分比
    double buyer_buyhome_zengzhishui_price;  //  增值税金额         卖家承担 增值税和个税 附加税    实际上买方付钱



    double buyer_buyhome_fujiashui_percent;  // 附加税百分比
    double buyer_buyhome_fujiashui_price;  //  附加税金额         卖家承担 增值税和个税 附加税    实际上买方付钱



    double buyer_buyhome_geshui_percent;   // 个税百分比
    double buyer_buyhome_geshui_price;   // 个税金额


    double buyer_buyhome_qishui_percent;  // 契税百分比
    double buyer_buyhome_qishui_price;  // 契税金额         卖家承担 增值税和个税    实际上买方付钱




    double buyer_all_tax_price;  // 首次买房税费总额


    double buyer_first_buyhome_price;  // 首次买房金额




    ArrayList<String> mtableStr_MacOS_Notepadxx_Output_List;   // macos_notepad-- 方形打印
    ArrayList<String> mtableStr_MacOS_Notepadxx_Input_ModelList;   // macos_notepad--  输入参考模型 打印可以可以被识别的参考输入模型


    ArrayList<String> mtableStr_MacOS_Notepad_Output_List;   // macos_notepad 方形打印 方形打印
    ArrayList<String> mtableStr_MacOS_Notepad_Input_ModelList;   // macos_notepad--  输入参考模型 打印可以可以被识别的参考输入模型



    ArrayList<String> mtableStr_WinOS_Notepadxx_Output_List;   // windows_notepad++ 方形打印 方形打印

    ArrayList<String> mtableStr_WinOS_Notepadxx_Input_ModelList;   // macos_notepad--  输入参考模型 打印可以可以被识别的参考输入模型





// ###########################   需要得到的输入 ##########################

    boolean seller_home_isputong_flag; // 普通住宅   非普通住宅
    boolean seller_first_home_flag; // 卖家首套--true    卖家非首套--false

    boolean seller_home_fiveyear_flag; // 卖家满五年--true    卖家不满五年--false



    boolean buyer_first_home_flag; // 买家首套--true    买家非首套--false

    double home_area ;   // 房屋的面积

    double seller_buyhome_price;  // 卖家买入价格

    double buyer_buyhome_seller_sellhome_price;  // 买家买入价格  卖家卖出价格



    // 二选一   一个固定  一个 百分比
    double buyer_buyhome_downpayment_percent;  // 买家首付款百分比
    double buyer_buyhome_downpayment_price;  // 买家首付款



    // 二选一   一个固定  一个 百分比
    double input_buyer_buyhome_zhongjie_percent;  // 买家中介费
    double input_buyer_buyhome_zhongjie_price;  // 买家中介费










    LoanEntry mLoanEntry;




    String getSimple_Seperator_String(){



        DecimalFormat double_format = new DecimalFormat("0.00");
        int loanType = 0 ;

if(mLoanEntry.mLoanType == LoanType.GONGJIJIN){
    loanType  = 0 ;
} else if(mLoanEntry.mLoanType == LoanType.SHANGYE){
    loanType  = 1 ;

} else {
    loanType  = 2 ;

}



        if(mLoanEntry.mLoanType == LoanType.GONGJIJIN){
            return cal_name+"_"
                    + seller_home_isputong_flag+"_"  + seller_first_home_flag+"_"+ seller_home_fiveyear_flag+"_"+ buyer_first_home_flag+"_"
                    + double_format.format(home_area) +"_"   + (int)seller_buyhome_price +"_"+ (int)buyer_buyhome_seller_sellhome_price +"_"+ double_format.format(input_buyer_buyhome_zhongjie_percent) +"_"+ double_format.format(buyer_buyhome_downpayment_percent )+"_"
                    +loanType+"_"
                    +  double_format.format(mLoanEntry.loanentry_gongjijin_loan_percent_inallprice)  +"_"   + (int)mLoanEntry.loanentry_gongjijin_loan_price +"_"+ double_format.format(mLoanEntry.loanentry_gongjijin_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_gongjijin_loan_yearcount +"_"+ mLoanEntry.loanentry_gongjijin_loan_is_samepay_anymonth+"_"
                    +  0  +"_"   + 0 +"_"+ double_format.format(mLoanEntry.loanentry_shangye_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_shangye_loan_yearcount +"_"+ mLoanEntry.loanentry_shangye_loan_is_samepay_anymonth
                    ;

        } else if(mLoanEntry.mLoanType == LoanType.SHANGYE){


            return cal_name+"_"
                    + seller_home_isputong_flag+"_"  + seller_first_home_flag+"_"+ seller_home_fiveyear_flag+"_"+ buyer_first_home_flag+"_"
                    + double_format.format(home_area) +"_"   + (int)seller_buyhome_price +"_"+ (int)buyer_buyhome_seller_sellhome_price +"_"+ double_format.format(input_buyer_buyhome_zhongjie_percent) +"_"+ double_format.format(buyer_buyhome_downpayment_percent)+"_"
                    +loanType+"_"
                    +  0  +"_"   + 0 +"_"+ double_format.format(mLoanEntry.loanentry_gongjijin_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_gongjijin_loan_yearcount +"_"+ mLoanEntry.loanentry_gongjijin_loan_is_samepay_anymonth+"_"
                    +  double_format.format(mLoanEntry.loanentry_shangye_loan_percent_inallprice)  +"_"   + (int)mLoanEntry.loanentry_shangye_loan_price +"_"+ double_format.format(mLoanEntry.loanentry_shangye_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_shangye_loan_yearcount +"_"+ mLoanEntry.loanentry_shangye_loan_is_samepay_anymonth
                    ;


        } else {

            if(mLoanEntry.loanentry_gongjijin_loan_percent_inallprice != 0 || mLoanEntry.loanentry_shangye_loan_percent_inallprice != 0  ){   // 公积金贷款率 不等于0   说明使用占比
                return cal_name+"_"
                        + seller_home_isputong_flag+"_"  + seller_first_home_flag+"_"+ seller_home_fiveyear_flag+"_"+ buyer_first_home_flag+"_"
                        + double_format.format(home_area) +"_"   + (int)seller_buyhome_price +"_"+ (int)buyer_buyhome_seller_sellhome_price +"_"+ double_format.format(input_buyer_buyhome_zhongjie_percent) +"_"+ double_format.format(buyer_buyhome_downpayment_percent)+"_"
                        +loanType+"_"
                        +  double_format.format(mLoanEntry.loanentry_gongjijin_loan_percent_inallprice)   +"_"   + 0 +"_"+ double_format.format(mLoanEntry.loanentry_gongjijin_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_gongjijin_loan_yearcount +"_"+ mLoanEntry.loanentry_gongjijin_loan_is_samepay_anymonth+"_"
                        + double_format.format(mLoanEntry.loanentry_shangye_loan_percent_inallprice)   +"_"   + 0 +"_"+ double_format.format(mLoanEntry.loanentry_shangye_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_shangye_loan_yearcount +"_"+ mLoanEntry.loanentry_shangye_loan_is_samepay_anymonth
                        ;

            }  else {   //

                return cal_name+"_"
                        + seller_home_isputong_flag+"_"  + seller_first_home_flag+"_"+ seller_home_fiveyear_flag+"_"+ buyer_first_home_flag+"_"
                        + double_format.format(home_area) +"_"   + (int)seller_buyhome_price +"_"+ (int)buyer_buyhome_seller_sellhome_price +"_"+ double_format.format(input_buyer_buyhome_zhongjie_percent) +"_"+ double_format.format(buyer_buyhome_downpayment_percent)+"_"
                        +loanType+"_"
                        +  0  +"_"   + (int)mLoanEntry.loanentry_gongjijin_loan_price +"_"+ double_format.format(mLoanEntry.loanentry_gongjijin_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_gongjijin_loan_yearcount +"_"+ mLoanEntry.loanentry_gongjijin_loan_is_samepay_anymonth+"_"
                        +  0  +"_"   + (int)mLoanEntry.loanentry_shangye_loan_price +"_"+ double_format.format(mLoanEntry.loanentry_shangye_loan_benefit_rate) +"_"+ mLoanEntry.loanentry_shangye_loan_yearcount +"_"+ mLoanEntry.loanentry_shangye_loan_is_samepay_anymonth
                        ;

            }

        }





   }


    HomeDownPay(boolean xseller_home_isputong_flag ,boolean xseller_first_home_flag , boolean xseller_home_fiveyear_flag , boolean xbuyer_first_home_flag
            , double xhome_area , double xseller_buyhome_price , double xbuyer_buyhome_seller_sellhome_price
            ,   double xbuyer_buyhome_zhongjie_percent ,   double xbuyer_buyhome_downpayment_percent  , LoanType loraType
            ,   double percent_gongjijin_loan_in_all_price    ,   double gongjijin_loan_price ,  double gongjijin_loan_benefit_rate ,  int gongjijin_loan_yearcount ,  boolean  mgongjijin_loan_pay_same_allmonth
            ,   double percent_shangye_loan_in_all_price    ,   double shangye_loan_price ,  double shangye_loan_benefit_rate ,  int shangye_loan_yearcount ,  boolean  mshangye_loan_pay_same_allmonth){
        seller_home_isputong_flag =  xseller_home_isputong_flag ;
        seller_first_home_flag =    xseller_first_home_flag;
        seller_home_fiveyear_flag =    xseller_home_fiveyear_flag;
        buyer_first_home_flag =    xbuyer_first_home_flag;
        home_area =    xhome_area;
        seller_buyhome_price =    xseller_buyhome_price;
        buyer_buyhome_seller_sellhome_price =    xbuyer_buyhome_seller_sellhome_price;
        buyer_buyhome_downpayment_percent = xbuyer_buyhome_downpayment_percent;
        input_buyer_buyhome_zhongjie_percent = xbuyer_buyhome_zhongjie_percent;


        // 首付比例 计算得到的  首付金额
        buyer_buyhome_downpayment_price =  buyer_buyhome_seller_sellhome_price * buyer_buyhome_downpayment_percent * 0.01;


        // 中介费  中介费率 计算得到的  中介费
        input_buyer_buyhome_zhongjie_price =  buyer_buyhome_seller_sellhome_price * input_buyer_buyhome_zhongjie_percent * 0.01;



        // 动态计算的值
        // 增值税
        buyer_buyhome_zengzhishui_percent = cal_buyer_buyhome_zengzhi_percent();
        buyer_buyhome_zengzhishui_price = buyer_buyhome_zengzhishui_percent*cal_buyer_buyhome_zengzhi_basevalue();


        // 附加税
        buyer_buyhome_fujiashui_percent = cal_buyer_buyhome_fujiashui_percent();
        buyer_buyhome_fujiashui_price = buyer_buyhome_fujiashui_percent*cal_buyer_buyhome_fujiashui_basevalue();

        // 个税
        buyer_buyhome_geshui_percent = cal_buyer_buyhome_geshui_percent();
        buyer_buyhome_geshui_price = cal_buyer_buyhome_geshui_price(buyer_buyhome_geshui_percent , buyer_buyhome_zengzhishui_price);

        // 契税
        buyer_buyhome_qishui_percent = cal_buyer_buyhome_qishui_percent();
        buyer_buyhome_qishui_price = buyer_buyhome_qishui_percent*cal_buyer_buyhome_qishui_basevalue(buyer_buyhome_zengzhishui_price);


        buyer_all_tax_price = buyer_buyhome_zengzhishui_price + buyer_buyhome_fujiashui_price+ buyer_buyhome_geshui_price+ buyer_buyhome_qishui_price;
        buyer_first_buyhome_price = buyer_buyhome_downpayment_price
                + input_buyer_buyhome_zhongjie_price
                + buyer_buyhome_zengzhishui_price
                + buyer_buyhome_fujiashui_price
                + buyer_buyhome_geshui_price
                + buyer_buyhome_qishui_price;

        // 两种情况
        // 1.只输入 金额
        // 2.只输入 百分比
        // 3. 其余的不管了 乱算

        if(percent_gongjijin_loan_in_all_price == 0 && percent_shangye_loan_in_all_price == 0 &&
                ( gongjijin_loan_price != 0 ||  shangye_loan_price != 0 )){   // 1.只输入 金额

            double real_gongjijin_loan_price = gongjijin_loan_price;
            double real_shangye_loan_price = shangye_loan_price;

            mLoanEntry = new  LoanEntry(loraType , 0 , real_gongjijin_loan_price ,gongjijin_loan_benefit_rate ,  gongjijin_loan_yearcount  , mgongjijin_loan_pay_same_allmonth
                    , 0 , real_shangye_loan_price ,shangye_loan_benefit_rate ,  shangye_loan_yearcount  , mshangye_loan_pay_same_allmonth);

        } else if(gongjijin_loan_price == 0 && shangye_loan_price == 0
                && ( percent_gongjijin_loan_in_all_price != 0  || percent_shangye_loan_in_all_price != 0 )){  //  2.只输入百分比
            double real_gongjijin_loan_price = (percent_gongjijin_loan_in_all_price * 0.01) * buyer_buyhome_seller_sellhome_price;
            double real_shangye_loan_price =   (percent_shangye_loan_in_all_price * 0.01) * buyer_buyhome_seller_sellhome_price;

            mLoanEntry = new  LoanEntry(loraType ,
                    percent_gongjijin_loan_in_all_price , real_gongjijin_loan_price ,gongjijin_loan_benefit_rate ,  gongjijin_loan_yearcount  , mgongjijin_loan_pay_same_allmonth
                    , percent_shangye_loan_in_all_price , real_shangye_loan_price ,shangye_loan_benefit_rate ,  shangye_loan_yearcount  , mshangye_loan_pay_same_allmonth);


        } else {

            mLoanEntry = new  LoanEntry(loraType ,
                    percent_gongjijin_loan_in_all_price , gongjijin_loan_price ,gongjijin_loan_benefit_rate ,  gongjijin_loan_yearcount  , mgongjijin_loan_pay_same_allmonth
                    , percent_shangye_loan_in_all_price , shangye_loan_price ,shangye_loan_benefit_rate ,  shangye_loan_yearcount  , mshangye_loan_pay_same_allmonth);

        }
        mLoanEntry.set_buyer_buyhome_seller_sellhome_price(buyer_buyhome_seller_sellhome_price);





/*        mtableStr_MacOS_Notepad_List = new ArrayList<String>();
        MacOS_Notepad_initTableStrLit();

        mtableStr_WinOS_Notepadpp_List = new ArrayList<String>();
        WinOS_Notepadpp_initTableStrLit();*/

    }

    double cal_buyer_buyhome_qishui_basevalue(double mzengzhi_price){   // 计算附加税率基数
        return buyer_buyhome_seller_sellhome_price - mzengzhi_price;
    }





    void WinOS_Notepadxx_init_input_model_list(ArrayList<String> inputList , String lastEndLine){
        mtableStr_WinOS_Notepadxx_Input_ModelList = new ArrayList<String>();
        mtableStr_WinOS_Notepadxx_Input_ModelList.clear();

        for (int i = 0; i < inputList.size(); i++) {
            mtableStr_WinOS_Notepadxx_Input_ModelList.add(inputList.get(i)+"\n");
        }
        mtableStr_WinOS_Notepadxx_Input_ModelList.add(lastEndLine+"\n");
    }

    void MacOS_Notepadxx_init_input_model_list(ArrayList<String> inputList , String lastEndLine){
        mtableStr_MacOS_Notepadxx_Input_ModelList = new ArrayList<String>();
        mtableStr_MacOS_Notepadxx_Input_ModelList.clear();

        for (int i = 0; i < inputList.size(); i++) {
            mtableStr_MacOS_Notepadxx_Input_ModelList.add(inputList.get(i)+"\n");
        }
        mtableStr_MacOS_Notepadxx_Input_ModelList.add(lastEndLine+"\n");
    }




    DecimalFormat mMoneyFormat = new DecimalFormat("#000000,000");
    String getPriceStr(double percentValue){
        String priceStr =  (mMoneyFormat.format(percentValue)+"").trim();
        if(percentValue == 0){
            priceStr="000,000,000";
            priceStr="          0";
            return priceStr;
        }

        return priceStr;
    }




    DecimalFormat mAreaFormat = new DecimalFormat("#00.00");
    String getAreaPercentStr(double percentValue) {
        String percentStr =  (decimalFormat.format(percentValue)+"").trim();
        if(percentStr.length() == 5){
            percentStr = " "+percentStr;

        }  // 80.00
        return percentStr;
    }





    DecimalFormat twoIntFormat = new DecimalFormat("00");

    DecimalFormat threeIntFormat = new DecimalFormat("000");


    String getTwoIntStr(int  intValue){
        String getTwoIntStr =  (twoIntFormat.format(intValue)).trim();
        return getTwoIntStr;
    }


    String getThreeIntStr(int  intValue){
        String getThreeIntStr =  (threeIntFormat.format(intValue)).trim();
        return getThreeIntStr;
    }

    String getDoublePoint_FiveStr(double  mValue){

        DecimalFormat FiveStrFormst = new DecimalFormat("00.00");
        if(mValue == 0){
            return   " 0   ";
//            return "00.00";
        }
        String getThreeIntStr =  (FiveStrFormst.format(mValue)).trim();
        return getThreeIntStr;
    }

    DecimalFormat decimalFormat = new DecimalFormat(",#00.00");

    String getTwoPointTwoPercentStr(double percentValue){
        DecimalFormat decimalFormat = new DecimalFormat(",#00.00");
        String percentStr =  (decimalFormat.format(percentValue*100)+"").trim();

        if(percentStr.startsWith("0")){

            percentStr = " "+ percentStr.substring(1);
        }

        return  percentStr+"%";

    }



    String getPercentStr(double percentValue){

        String percentStr =  (decimalFormat.format(percentValue*100)+"").trim();
        if(percentValue == 0){
            percentStr = " 0   %";
            return percentStr;
        }
//			4.90
        if(percentStr.startsWith("00.")){

            percentStr =    percentStr.replace("00."," 0.");
        }

        if(percentStr.endsWith(".00")){   // 02.00% ->  02  %

            percentStr =  percentStr.replace(".00","   ");
        }

        if(percentStr.startsWith("0")){

            percentStr = " "+ percentStr.substring(1);
        }

        if(percentStr.endsWith("0") && !percentStr.endsWith("00")){

            percentStr =  percentStr.substring(0,percentStr.length()-1) +" ";
        }

        return  percentStr+"%";

    }
    double cal_buyer_buyhome_qishui_percent(){   // 计算契税
        double qishui_percent = 0.03 ;
        if(buyer_first_home_flag){   //  买家首套房子

            if(home_area < 90){   //  买家首套 && 面积小于90
                qishui_percent = 0.01;

            } else {  //  买家首套 && 面积大于等于90

                qishui_percent = 0.015;
            }

        } else{   // 买家 二套房子  契税都是 3%    不管面积了

            qishui_percent = 0.03;
        }
        return qishui_percent;
    }




    double cal_buyer_buyhome_geshui_price(double mgeshui_percent , double mzengzhi_price ){   // 计算个税税率
        if(mgeshui_percent == 0 ){  //  个税 比率为0  那么说明免征
            return 0 ;
        }


        double geshui_basevalue = buyer_buyhome_seller_sellhome_price -  mzengzhi_price; // 成交价-增值税

        double geshui_price_type1 = geshui_basevalue * mgeshui_percent;

        // 塾低原则  哪个少选哪个         (成交价-买入价)*0.2
        double geshui_price_type2 = (buyer_buyhome_seller_sellhome_price - seller_buyhome_price ) * 0.2;

        if(geshui_price_type1 < geshui_price_type2){

            return geshui_price_type1;
        }
        return geshui_price_type2 ;
    }


    double cal_buyer_buyhome_geshui_percent(){   // 计算个税税率
        double geshui_percent = 0.02 ;
        if(seller_home_isputong_flag){  // 普通住宅
            if(seller_home_fiveyear_flag){    // 普通住宅 && 满五年
                if(seller_first_home_flag){  // 普通住宅 && 满五年 && 唯一    那么 个税免征 为 0

                    geshui_percent = 0 ;
                } else{   // 普通住宅 && 满五年 && 非唯一   个税税率为 差额 1%
                    geshui_percent = 0.01 ;
                }

            } else {   // 普通住宅 && 满两年

                if(seller_first_home_flag){  //  普通住宅 &&  满两年   && 唯一    那么 个税免征 为 1%

                    geshui_percent = 0.01 ;
                } else{   // 普通住宅 && 满两年 && 非唯一      个税税率为 差额  1%
                    geshui_percent = 0.01 ;
                }

            }


        } else{   // 非普通住宅
            if(seller_home_fiveyear_flag){    // 非普通住宅 && 满五年
                if(seller_first_home_flag){  // 非普通住宅 && 满五年 && 唯一    那么 个税免征 为 0
                    geshui_percent = 0 ;
                } else{   // 非普通住宅 && 满五年 && 非唯一   个税税率为 差额 2%
                    geshui_percent = 0.02 ;
                }

            } else {   // 非普通住宅 && 满两年

                if(seller_first_home_flag){  //  非普通住宅 &&  满两年   && 唯一    那么 个税免征 为 2%

                    geshui_percent = 0.02 ;
                } else{   // 非普通住宅 && 满两年 && 非唯一   个税税率为 差额  2%
                    geshui_percent = 0.02 ;
                }

            }
        }
        return geshui_percent;
    }





    double cal_buyer_buyhome_fujiashui_percent(){   // 计算增值税率
        double fujiashui_percent = 0 ;
        if(seller_home_isputong_flag){  // 普通住宅
            if(seller_home_fiveyear_flag){    // 普通住宅 && 满五年   增值税为0
                fujiashui_percent = 0 ;
            } else {   // 普通住宅 && 满两年   增值税为5%
                fujiashui_percent = 0.003 ;
            }


        } else{   // 非普通住宅
            if(seller_home_fiveyear_flag){    // 非普通住宅 && 满五年   5%(差额)
                fujiashui_percent = 0.003 ;
            } else {   // 非普通住宅 && 满两年   增值税为5%(全额)
                fujiashui_percent = 0.003 ;
            }
        }
        return fujiashui_percent;
    }


    double cal_buyer_buyhome_fujiashui_basevalue(){   // 计算附加税率基数
        double fujiashui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
        if(seller_home_isputong_flag){  // 普通住宅
            if(seller_home_fiveyear_flag){    // 普通住宅 && 满五年   附加税为0   附加税率基数为0
                fujiashui_basevalue =  0 ;

            } else {   // 普通住宅 && 满两年   附加税率基数为   卖出价/1.05
                fujiashui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
            }


        } else{   // 非普通住宅

            if(seller_home_fiveyear_flag){    // 非普通住宅 && 满五年   附加税率基数为 差价/1.05
                fujiashui_basevalue = ((buyer_buyhome_seller_sellhome_price - seller_buyhome_price) /1.05)  ;
            } else {   // 非普通住宅 && 满两年   附加税为5%(全额)
                fujiashui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
            }

        }
        return fujiashui_basevalue;
    }




    double cal_buyer_buyhome_zengzhi_percent(){   // 计算增值税率
        double zengzhishui_percent = 0 ;
        if(seller_home_isputong_flag){  // 普通住宅
            if(seller_home_fiveyear_flag){    // 普通住宅 && 满五年   增值税为0
                zengzhishui_percent = 0 ;
            } else {   // 普通住宅 && 满两年   增值税为5%
                zengzhishui_percent = 0.05 ;
            }


        } else{   // 非普通住宅

            if(seller_home_fiveyear_flag){    // 非普通住宅 && 满五年   5%(差额)
                zengzhishui_percent = 0.05 ;
            } else {   // 非普通住宅 && 满两年   增值税为5%(全额)
                zengzhishui_percent = 0.05 ;
            }

        }
        return zengzhishui_percent;
    }


    double cal_buyer_buyhome_zengzhi_basevalue(){   // 计算增值税率基数
        double zengzhishui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
        if(seller_home_isputong_flag){  // 普通住宅
            if(seller_home_fiveyear_flag){    // 普通住宅 && 满五年   增值税为0   增值税率基数为0
                zengzhishui_basevalue =  0 ;

            } else {   // 普通住宅 && 满两年   增值税率基数为   卖出价/1.05
                zengzhishui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
            }


        } else{   // 非普通住宅

            if(seller_home_fiveyear_flag){    // 非普通住宅 && 满五年   增值税率基数为 差价/1.05
                zengzhishui_basevalue = ((buyer_buyhome_seller_sellhome_price - seller_buyhome_price) /1.05)  ;
            } else {   // 非普通住宅 && 满两年   增值税为5%(全额)
                zengzhishui_basevalue = (buyer_buyhome_seller_sellhome_price/1.05)  ;
            }

        }
        return zengzhishui_basevalue;
    }


    public  class LoanEntry{
        public LoanType mLoanType;
        public double loanentry_shangye_loan_price;  // 买家商业贷款额
        public double loanentry_shangye_loan_percent_inallprice;  // 买家商业贷款额 占房价百分比
        public double loanentry_shangye_loan_benefit_rate;  // 买家商业贷款利率
        public int loanentry_shangye_loan_yearcount;  // 买家商业贷款年限
        public boolean loanentry_shangye_loan_is_samepay_anymonth;   // true--------等额本息（每月还款相等）   false--等额本金  逐月递减

        public double loanentry_gongjijin_loan_price;  // 买家公积金贷款额
        public double loanentry_gongjijin_loan_percent_inallprice;  // 买家公积金贷款额 占房价百分比
        public double loanentry_gongjijin_loan_benefit_rate;  // 买家公积金贷款利率
        public int loanentry_gongjijin_loan_yearcount;  // 买家公积金贷款年限
        public boolean loanentry_gongjijin_loan_is_samepay_anymonth;   // true--------等额本息（每月还款相等）   false--等额本金  逐月递减

        public double buyer_buyhome_seller_sellhome_price ;
        public void  set_buyer_buyhome_seller_sellhome_price(double  xbuyer_buyhome_seller_sellhome_price){
            buyer_buyhome_seller_sellhome_price =   xbuyer_buyhome_seller_sellhome_price ;
        }

        double getGongJiJin_LoanPrice_In_AllPrice(){
            return   loanentry_gongjijin_loan_price/buyer_buyhome_seller_sellhome_price;
        }

        double getShangYe_LoanPrice_In_AllPrice(){
            return   loanentry_shangye_loan_price/buyer_buyhome_seller_sellhome_price;
        }

        LoanEntry( LoanType xLoanType
                ,  double xloanentry_gongjijin_loan_percent_inallprice   , double xloanentry_gongjijin_loan_price  ,  double xloanentry_gongjijin_loan_benefit_rate
                , int xloanentry_gongjijin_loan_yearcount  ,  boolean xloanentry_gongjijin_loan_is_samepay_anymonth  ,
                   double xloanentry_shangye_loan_percent_inallprice  , double xloanentry_shangye_loan_price  ,  double xloanentry_shangye_loan_benefit_rate
                , int xloanentry_shangye_loan_yearcount  ,  boolean xloanentry_shangye_loan_is_samepay_anymonth
        ){
            mLoanType = xLoanType;
            loanentry_gongjijin_loan_price = xloanentry_gongjijin_loan_price;
            loanentry_gongjijin_loan_percent_inallprice =  xloanentry_gongjijin_loan_percent_inallprice;
            loanentry_gongjijin_loan_benefit_rate = xloanentry_gongjijin_loan_benefit_rate;
            loanentry_gongjijin_loan_yearcount = xloanentry_gongjijin_loan_yearcount;
            loanentry_gongjijin_loan_is_samepay_anymonth = xloanentry_gongjijin_loan_is_samepay_anymonth;

            loanentry_shangye_loan_price = xloanentry_shangye_loan_price;
            loanentry_shangye_loan_percent_inallprice =  xloanentry_shangye_loan_percent_inallprice;
            loanentry_shangye_loan_benefit_rate = xloanentry_shangye_loan_benefit_rate;
            loanentry_shangye_loan_yearcount = xloanentry_shangye_loan_yearcount;
            loanentry_shangye_loan_is_samepay_anymonth = xloanentry_shangye_loan_is_samepay_anymonth;

        }





        double get_zonghuang() {  // 公积金总还款


            return get_shangye_zonghuang()+get_gongjijin_zonghuang();
        }



        double get_gongjijin_zonghuang(){  // 公积金总还款


            double  gongjijin_zonghuang = 0 ;
            int gongjijin_loan_month_count = loanentry_gongjijin_loan_yearcount * 12;

            double gongjijin_benefit_rate_percent = loanentry_gongjijin_loan_benefit_rate * 0.01;

            if(loanentry_gongjijin_loan_is_samepay_anymonth){  // 等额本息总还 = 月供 * 总期数+1)
                gongjijin_zonghuang =   getGongJiJin_YueGong() * ( gongjijin_loan_month_count + 1) ;

            } else{   // 等额本金
                // 还款总利息=(还款月数+1)×贷款额×月利率÷2；
                gongjijin_zonghuang = ((( gongjijin_loan_month_count + 1) * loanentry_gongjijin_loan_price * (gongjijin_benefit_rate_percent/12)) / 2) + loanentry_gongjijin_loan_price;
            }

            return gongjijin_zonghuang;
        }


        double get_shangye_zonghuang(){  // 公积金总还款


            double  shangye_zonghuang = 0 ;
            int shangye_loan_month_count = loanentry_shangye_loan_yearcount * 12;

            double shangye_benefit_rate_percent = loanentry_shangye_loan_benefit_rate * 0.01;

            if(loanentry_shangye_loan_is_samepay_anymonth){  // 等额本息总还 = 月供 * 总期数+1)
                shangye_zonghuang =   getShangYe_YueGong() * ( shangye_loan_month_count + 1) ;

            } else{   // 等额本金
                // 还款总利息=(还款月数+1)×贷款额×月利率÷2；
                shangye_zonghuang = ((( shangye_loan_month_count + 1) * loanentry_shangye_loan_price * (shangye_benefit_rate_percent/12)) / 2) + loanentry_shangye_loan_price;
            }

            return shangye_zonghuang;
        }

        double get_zonglixi(){
            return get_shangye_zonglixi() + get_gongjijin_zonglixi();
        }

        double get_shangye_zonglixi(){  // 公积金总利息


            double  shangye_zonglixi = 0 ;
            int shangye_loan_month_count = loanentry_shangye_loan_yearcount * 12;

            double shangye_benefit_rate_percent = loanentry_shangye_loan_benefit_rate * 0.01;

            if(loanentry_shangye_loan_is_samepay_anymonth){  // 等额本息总还 = 月供 * 总期数+1)
                shangye_zonglixi =   getShangYe_YueGong() * ( shangye_loan_month_count + 1) - loanentry_shangye_loan_price ;

            } else{   // 等额本金
                // 还款总利息=(还款月数+1)×贷款额×月利率÷2；
                shangye_zonglixi = ((( shangye_loan_month_count + 1) * loanentry_shangye_loan_price * (shangye_benefit_rate_percent/12)) / 2) ;
            }

            return shangye_zonglixi;
        }



        double get_gongjijin_zonglixi(){  // 公积金总利息


            double  gongjijin_zonglixi = 0 ;
            int gongjijin_loan_month_count = loanentry_gongjijin_loan_yearcount * 12;

            double gongjijin_benefit_rate_percent = loanentry_gongjijin_loan_benefit_rate * 0.01;

            if(loanentry_gongjijin_loan_is_samepay_anymonth){  // 等额本息总还 = 月供 * 总期数+1)
                gongjijin_zonglixi =   getGongJiJin_YueGong() * ( gongjijin_loan_month_count + 1) - loanentry_gongjijin_loan_price ;

            } else{   // 等额本金
                // 还款总利息=(还款月数+1)×贷款额×月利率÷2；
                gongjijin_zonglixi = ((( gongjijin_loan_month_count + 1) * loanentry_gongjijin_loan_price * (gongjijin_benefit_rate_percent/12)) / 2) ;
            }

            return gongjijin_zonglixi;
        }


        int  get_min_yearcount(){
            if(loanentry_gongjijin_loan_yearcount > loanentry_shangye_loan_yearcount){
                return loanentry_shangye_loan_yearcount;
            }
            return loanentry_gongjijin_loan_yearcount;
        }




        double get_all_yuegong(){
            return getShangYe_YueGong() + getGongJiJin_YueGong();
        }


        double get_all_yuegong_downvalue_permonth(){
            return getGongJiin_YueGong_DownValue_PerMonth() + getShangYe_YueGong_DownValue_PerMonth();
        }

        double getGongJiin_YueGong_DownValue_PerMonth(){
            double gongjijin_downvalue_permonth = 0 ;   // 每个月月供的 减少值
            if(loanentry_gongjijin_loan_is_samepay_anymonth){
                return 0 ;
            }
            double first_month_gongjijin_yuegong = getGongJiJin_YueGong();

            int gongjijin_loan_month_count = loanentry_gongjijin_loan_yearcount * 12;

            double gongjijin_benefit_rate_percent = loanentry_gongjijin_loan_benefit_rate * 0.01;
            double second_month_gongjijin_yuegong =  (loanentry_gongjijin_loan_price/gongjijin_loan_month_count) + ((gongjijin_benefit_rate_percent/12) * (loanentry_gongjijin_loan_price - (loanentry_gongjijin_loan_price/gongjijin_loan_month_count)));

            gongjijin_downvalue_permonth =   first_month_gongjijin_yuegong  - second_month_gongjijin_yuegong;
            return gongjijin_downvalue_permonth;
        }

        //
        double getShangYe_YueGong_DownValue_PerMonth(){
            double shangye_downvalue_permonth = 0 ;   // 每个月月供的 减少值
            if(loanentry_shangye_loan_is_samepay_anymonth){
                return 0 ;
            }
            double first_month_shangye_yuegong = getShangYe_YueGong();

            int shangye_loan_month_count = loanentry_shangye_loan_yearcount * 12;

            double shangye_benefit_rate_percent = loanentry_shangye_loan_benefit_rate * 0.01;
            double second_month_shangye_yuegong =  (loanentry_shangye_loan_price/shangye_loan_month_count) + ((shangye_benefit_rate_percent/12) * (loanentry_shangye_loan_price - (loanentry_shangye_loan_price/shangye_loan_month_count)));

            shangye_downvalue_permonth =   first_month_shangye_yuegong  - second_month_shangye_yuegong;
            return shangye_downvalue_permonth;
        }

        double getShangYe_YueGong(){
            double shangye_yuegong = 0 ;
            int shangye_loan_month_count = loanentry_shangye_loan_yearcount * 12;

            double shangye_benefit_rate_percent = loanentry_shangye_loan_benefit_rate * 0.01;

            if(loanentry_shangye_loan_is_samepay_anymonth){  // 等额本息

                // 第一步  计算每月的本息和

//                double loanentry_shangye_loan_price = 200000;
//                double shangye_benefit_rate_percent = 0.049;
//                double shangye_loan_month_count = 240;


                DecimalFormat decimalFormat = new DecimalFormat(",#00.00");
                double all_shangye_dengxi_yuegong = ( loanentry_shangye_loan_price *
                        Math.pow(1+(shangye_benefit_rate_percent/12),shangye_loan_month_count) *
                        (shangye_benefit_rate_percent/12)) /(Math.pow((1+(shangye_benefit_rate_percent/12)),shangye_loan_month_count) -1) ;

                shangye_yuegong  = all_shangye_dengxi_yuegong;

            } else{    //  等额本金 (逐月递减)
                double all_shangye_dengjin_yuegong = (loanentry_shangye_loan_price/shangye_loan_month_count) + ((shangye_benefit_rate_percent/12) * loanentry_shangye_loan_price);
                shangye_yuegong  = all_shangye_dengjin_yuegong;
            }
            return  shangye_yuegong;
        }

        double getGongJiJin_YueGong(){
            double gongjijin_yuegong = 0 ;
            int gongjijin_loan_month_count = loanentry_gongjijin_loan_yearcount * 12;

            double gongjijin_benefit_rate_percent = loanentry_gongjijin_loan_benefit_rate * 0.01;

            if(loanentry_gongjijin_loan_is_samepay_anymonth){  // 等额本息

                // 第一步  计算每月的本息和

//                double loanentry_gongjijin_loan_price = 200000;
//                double gongjijin_benefit_rate_percent = 0.049;
//                double gongjijin_loan_month_count = 240;


                DecimalFormat decimalFormat = new DecimalFormat(",#00.00");
                double all_gongjijin_dengxi_yuegong = ( loanentry_gongjijin_loan_price *
                        Math.pow(1+(gongjijin_benefit_rate_percent/12),gongjijin_loan_month_count) *
                        (gongjijin_benefit_rate_percent/12)) /(Math.pow((1+(gongjijin_benefit_rate_percent/12)),gongjijin_loan_month_count) -1) ;

                gongjijin_yuegong  = all_gongjijin_dengxi_yuegong;

            } else{    //  等额本金 (逐月递减)
                double all_gongjijin_dengjin_yuegong = (loanentry_gongjijin_loan_price/gongjijin_loan_month_count) + ((gongjijin_benefit_rate_percent/12) * loanentry_gongjijin_loan_price);
                gongjijin_yuegong  = all_gongjijin_dengjin_yuegong;
            }
            return  gongjijin_yuegong;
        }


        double getAll_loan_price(){

            return loanentry_shangye_loan_price + loanentry_gongjijin_loan_price;
        }

        boolean isHasGongJiJin_Loan(){
            boolean is_has_gongjijin_loan =  false;
            if(mLoanType == LoanType.SHANGYE){   // 纯商业贷款  没有公积金贷款
                return is_has_gongjijin_loan;
            }

            if(mLoanType == LoanType.HUNHE){   // 混合贷款   没有占比 和 贷款额 那么默认 也没有公积金贷款
                if(loanentry_gongjijin_loan_price == 0 && loanentry_gongjijin_loan_percent_inallprice == 0 &&
                        loanentry_shangye_loan_price != 0 ){
                    return is_has_gongjijin_loan;
                }

                if(loanentry_gongjijin_loan_price == 0 && loanentry_gongjijin_loan_percent_inallprice == 0 &&
                        loanentry_shangye_loan_percent_inallprice != 0 ){
                    return is_has_gongjijin_loan;
                }
                is_has_gongjijin_loan = true;
            }


            return true;
        }



        boolean isHasShangYe_Loan(){
            boolean is_has_shangye_loan =  false;
            if(mLoanType == LoanType.GONGJIJIN){   // 纯公积金贷款    没有商业贷款
                return is_has_shangye_loan;
            }

            if(mLoanType == LoanType.HUNHE){   // 混合贷款   没有占比 和 贷款额 那么默认 也没有公积金贷款
                if(loanentry_shangye_loan_price == 0 && loanentry_shangye_loan_percent_inallprice == 0 &&
                        loanentry_gongjijin_loan_price != 0 ){
                    return is_has_shangye_loan;
                }

                if(loanentry_shangye_loan_price == 0 && loanentry_shangye_loan_percent_inallprice == 0 &&
                        loanentry_gongjijin_loan_percent_inallprice != 0 ){
                    return is_has_shangye_loan;
                }
            }

            return true;
        }

    }


}

