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
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.fragment.Loan_Calculate_Fragment;

import java.text.DecimalFormat;
import java.util.List;

public class LoanUtilAdapter extends RecyclerView.Adapter<LoanUtilAdapter.MyHolder> {

    public List getList() {
        return mList;
    }

    public void setList(List mList) {
        this.mList = mList;
    }

    public Loan_Calculate_Fragment mFragment;

    private List mList;//数据源

    private Context mContext;//数据源

    private OnItemClickListener onItemClickListener;


    //这个地方的handler最好封装 防止内存泄漏
    // 100   跟新  Adapter
    Handler handler = null;

/*    Handler handler   =  new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    String filePath = msg.obj+"";
                    print("Douyin filePath  = "+ msg.obj+"");
                    notifyDataSetChanged();
                    File douyinFile = new File(filePath);
                    if(douyinFile.exists()){
                        showPathDialog(douyinFile);
                    }


                    break;
            }
        }
    };*/


    public LoanUtilAdapter(Context pContext, List txtList, Loan_Calculate_Fragment xFragment) {
        mContext = pContext;

        mList = txtList;
        mFragment = xFragment;
        initHandle();

    }


    void initHandle() {

/*        // 100--- 刷新数据
        handler =  new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 100:   // douyin 下载
                        String filePath = msg.obj+"";
                        print("Douyin filePath  = "+ msg.obj+"");
                        notifyDataSetChanged();
                        File douyinFile = new File(filePath);
                        if(douyinFile.exists()){
                            showPathDialog(douyinFile);
                        }


                        break;


                    case 101:   // git 下载
                        String filePath_git = msg.obj+"";
                        print("git 下载完成的路径 filePath  = "+ msg.obj+"");
                        notifyDataSetChanged();
                        File gitDownFile = new File(filePath_git);
                        if(gitDownFile.exists()){
                            showGitFileDialog(gitDownFile);
                        }


                        break;

                }
            }
        };*/


    }

    /**
     * 供外部调用设置监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 自定义的接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    DecimalFormat mMoneyFormat = new DecimalFormat("#,000");
    String getPriceStr(double percentValue){

        if(percentValue == 0){
            return "0";
        }

        String priceStr =  (mMoneyFormat.format(percentValue)+"").trim();

        return priceStr;
    }







    DecimalFormat mPriceBlankFormat = new DecimalFormat("#,000");
    String getPriceWithOneBlank(double percentValue){
        if(percentValue == 0){
            return "0 】";
        }
        String priceStr =  (mPriceBlankFormat.format(percentValue)).trim()+" 】";

        return priceStr;
    }

    DecimalFormat mTwoBlankPercentFormat = new DecimalFormat("0.00");
    String getPercentWithTwoBlank(double percentValue){
        String priceStr =  "["+(mTwoBlankPercentFormat.format(percentValue)).trim()+"%]";

        return priceStr;
    }









    DecimalFormat mPercentFormat = new DecimalFormat("0.0");
    String getPercentWithBlank(double percentValue){
        String priceStr =  (mPercentFormat.format(percentValue)).trim()+" % 】";

        return priceStr;
    }






    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cacultor_loanutil_item, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyHolder holder = new MyHolder(view);



        holder.calculator_input8_middle_percent_in_allprice_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double all_price = 0;
                String input_all_price_valuestr = holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input_all_price_valuestr) || ".".equals(input_all_price_valuestr)){
                    all_price = 0 ;
                } else{
                    all_price = Double.parseDouble(input_all_price_valuestr);
                }
                String input_value = s.toString();
                double input_double = 0;
                if("".equals(input_value) || ".".equals(input_value)){
                    input_double = 0 ;
                } else {
                    input_double = Double.parseDouble(input_value);
                }

                double current_value = input_double * all_price * 0.01;
                holder.calculator_input8_tip_textview.setText(getPriceStr(current_value));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        holder.calculator_input9_firsthomepaydone_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double all_price = 0;
                String input_all_price_valuestr = holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input_all_price_valuestr) || ".".equals(input_all_price_valuestr)){
                    all_price = 0 ;
                } else{
                    all_price = Double.parseDouble(input_all_price_valuestr);
                }
                String input_value = s.toString();
                double input_double = 0;
                if("".equals(input_value) || ".".equals(input_value)){
                    input_double = 0 ;
                } else {
                    input_double = Double.parseDouble(input_value);
                }

                double current_value = input_double * all_price * 0.01;
                holder.calculator_input9_tip_textview.setText(getPriceStr(current_value));



                // ------------------- input17—2  差额 计算 begin --------------------

                double input7_double = 0;   //  公积金贷款
                String input7_value =  holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input7_value) || ".".equals(input7_value)){
                    input7_double = 0;
                }else{
                    input7_double = Double.parseDouble(input7_value);
                }


                double input9_double_percent = 0;   //  公积金贷款
                String input9_value =  holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_value) || ".".equals(input9_value)){
                    input9_double_percent = 0;
                }else{
                    input9_double_percent = Double.parseDouble(input9_value);
                }

                double  input9_double = input7_double * input9_double_percent * 0.01 ;  // 首付


                double input12_double = 0;   //  公积金贷款
                String input12_value =  holder.calculator_input12_gongjijinloan_price_edt.getText().toString();
                if("".equals(input12_value) || ".".equals(input12_value)){
                    input12_double = 0;
                }else{
                    input12_double = Double.parseDouble(input12_value);
                }

                //差额 = 合同总价 - 首付 - 公积金贷款

                double distance_17_tip2_double  = input7_double - input9_double -input12_double;


                holder.calculator_input17_tip2_textview.setText(""+getPriceStr(distance_17_tip2_double));
                // ------------------- input17—2  差额 计算 end --------------------

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        holder.calculator_input11_gongjijinloan_percent_inallloan_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double all_price = 0;
                String input_all_price_valuestr = holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input_all_price_valuestr) || ".".equals(input_all_price_valuestr)){
                    all_price = 0 ;
                } else{
                    all_price = Double.parseDouble(input_all_price_valuestr);
                }
                String input_value = s.toString();
                double input_double = 0;
                if("".equals(input_value) || ".".equals(input_value)){
                    input_double = 0 ;
                } else {
                    input_double = Double.parseDouble(input_value);
                }

                double current_value = input_double * all_price * 0.01;
                holder.calculator_input11_tip_textview.setText(getPriceStr(current_value));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        holder.calculator_input16_shangyeloan_percent_inallloan_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double all_price = 0;
                String input_all_price_valuestr = holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input_all_price_valuestr) || ".".equals(input_all_price_valuestr)){
                    all_price = 0 ;
                } else{
                    all_price = Double.parseDouble(input_all_price_valuestr);
                }
                String input_value = s.toString();
                double input_double = 0;
                if("".equals(input_value) || ".".equals(input_value)){
                    input_double = 0 ;
                } else {
                    input_double = Double.parseDouble(input_value);
                }

                double current_value = input_double * all_price * 0.01;
                holder.calculator_input16_tip_textview.setText(getPriceStr(current_value));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        holder.calculator_input6_sellerbuyhomeprice_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input_value = s.toString();
                if("".equals(input_value) || ".".equals(input_value)){
                    holder.calculator_input6_tip_textview.setText("");
                    holder.calculator_input6_averageprice_textview.setText("");

                    return;
                }
                double input_double = Double.parseDouble(input_value);
                if(input_double < 1 ){
                    holder.calculator_input6_tip_textview.setText("毛级");
                } else if(input_double < 10){
                    holder.calculator_input6_tip_textview.setText("块级");
                } else if(input_double < 100){
                    holder.calculator_input6_tip_textview.setText("十级");
                } else if(input_double < 1000){
                    holder.calculator_input6_tip_textview.setText("百级");
                }else if(input_double < 10000){
                    holder.calculator_input6_tip_textview.setText("千级");
                }else if(input_double < 100000){
                    holder.calculator_input6_tip_textview.setText("万级");
                }else if(input_double < 1000000){
                    holder.calculator_input6_tip_textview.setText("十万级");
                }else if(input_double < 10000000){
                    holder.calculator_input6_tip_textview.setText("百万级");
                }else if(input_double < 100000000){
                    holder.calculator_input6_tip_textview.setText("千万级");
                }else if(input_double < 1000000000){
                    holder.calculator_input6_tip_textview.setText("亿级");
                }

               String input5_home_are_rawstr =  holder.calculator_input5_homearea_edt.getText().toString();

                if("".equals(input5_home_are_rawstr) || ".".equals(input5_home_are_rawstr)){
                    holder.calculator_input6_averageprice_textview.setText("");
                    return;
                }

                double input5_home_area_double = Double.parseDouble(input5_home_are_rawstr);
                double input6_average_price = input_double/input5_home_area_double;
                holder.calculator_input6_averageprice_textview.setText(getPriceStr(input6_average_price));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        // 合同  总价变更   其他的  百分比 也会跟着  变更
        holder.calculator_input7_allhomeprice_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input_value = s.toString();
                if("".equals(input_value) || ".".equals(input_value)){
                    holder.calculator_input7_tip_textview.setText("");
                    holder.calculator_input7_averageprice_textview.setText("");


                    return;
                }
                double input7_double = Double.parseDouble(input_value);
                if(input7_double < 1 ){
                    holder.calculator_input7_tip_textview.setText("毛级");
                } else if(input7_double < 10){
                    holder.calculator_input7_tip_textview.setText("块级");
                } else if(input7_double < 100){
                    holder.calculator_input7_tip_textview.setText("十级");
                } else if(input7_double < 1000){
                    holder.calculator_input7_tip_textview.setText("百级");
                }else if(input7_double < 10000){
                    holder.calculator_input7_tip_textview.setText("千级");
                }else if(input7_double < 100000){
                    holder.calculator_input7_tip_textview.setText("万级");
                }else if(input7_double < 1000000){
                    holder.calculator_input7_tip_textview.setText("十万级");
                }else if(input7_double < 10000000){
                    holder.calculator_input7_tip_textview.setText("百万级");
                }else if(input7_double < 100000000){
                    holder.calculator_input7_tip_textview.setText("千万级");
                }else if(input7_double < 1000000000){
                    holder.calculator_input7_tip_textview.setText("亿级");
                }

                String input5_home_are_rawstr =  holder.calculator_input5_homearea_edt.getText().toString();

                if("".equals(input5_home_are_rawstr) || ".".equals(input5_home_are_rawstr)){
                    holder.calculator_input7_averageprice_textview.setText("");
                    return;
                }

                double input5_home_area_double = Double.parseDouble(input5_home_are_rawstr);
                double input7_average_price = input7_double/input5_home_area_double;

                // input7_均价
                holder.calculator_input7_averageprice_textview.setText(getPriceStr(input7_average_price));


                // input8_tip   因为总价变更了   所以  中介费总额 也 跟着变更
                String input8_middle_percent = holder.calculator_input8_middle_percent_in_allprice_edt.getText().toString();
                if("".equals(input8_middle_percent) || ".".equals(input8_middle_percent)){
                    holder.calculator_input8_tip_textview.setText("");
                }else{
                    double middle_price = Double.parseDouble(input8_middle_percent) * input7_double * 0.01;
                    holder.calculator_input8_tip_textview.setText(getPriceStr(middle_price));

                }


                // input9_tip   因为总价变更了   所以  首付总额 也 跟着变更
                String input9_shoufu_percent = holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_shoufu_percent) || ".".equals(input9_shoufu_percent)){
                    holder.calculator_input9_tip_textview.setText("");
                }else{
                    double input9_shoufu_price = Double.parseDouble(input9_shoufu_percent) * input7_double * 0.01;
                    holder.calculator_input9_tip_textview.setText(getPriceStr(input9_shoufu_price));

                }

                // input11_tip   因为总价变更了   所以  公积金贷款额 也 跟着变更
                String input11_gongjijin_loan_percent = holder.calculator_input11_gongjijinloan_percent_inallloan_edt.getText().toString();
                if("".equals(input11_gongjijin_loan_percent) || ".".equals(input11_gongjijin_loan_percent)){
                    holder.calculator_input11_tip_textview.setText("");
                }else{
                    double input11_gongjijin_loan_price = Double.parseDouble(input11_gongjijin_loan_percent) * input7_double * 0.01;
                    holder.calculator_input11_tip_textview.setText(getPriceStr(input11_gongjijin_loan_price));

                }



                // input16_tip   因为总价变更了   所以  商业金贷款额 也 跟着变更
                String input16_shangye_loan_percent = holder.calculator_input11_gongjijinloan_percent_inallloan_edt.getText().toString();
                if("".equals(input16_shangye_loan_percent) || ".".equals(input16_shangye_loan_percent)){
                    holder.calculator_input16_tip_textview.setText("");
                }else{
                    double input16_shangye_loan_price = Double.parseDouble(input16_shangye_loan_percent) * input7_double * 0.01;
                    holder.calculator_input16_tip_textview.setText(getPriceStr(input16_shangye_loan_price));
                }



                // ------------------- input17—2  差额 计算 begin --------------------
                double input9_double_percent = 0;   //  公积金贷款
                String input9_value =  holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_value) || ".".equals(input9_value)){
                    input9_double_percent = 0;
                }else{
                    input9_double_percent = Double.parseDouble(input9_value);
                }

                double  input9_double = input7_double * input9_double_percent * 0.01 ;  // 首付


                double input12_double = 0;   //  公积金贷款
                String input12_value =  holder.calculator_input12_gongjijinloan_price_edt.getText().toString();
                if("".equals(input12_value) || ".".equals(input12_value)){
                    input12_double = 0;
                }else{
                    input12_double = Double.parseDouble(input12_value);
                }

                //差额 = 合同总价 - 首付 - 公积金贷款

                double distance_17_tip2_double  = input7_double - input9_double -input12_double;


                holder.calculator_input17_tip2_textview.setText(""+getPriceStr(distance_17_tip2_double));
                // ------------------- input17—2  差额 计算 end --------------------
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        holder.calculator_input12_gongjijinloan_price_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input_value = s.toString();
                if("".equals(input_value) || ".".equals(input_value)){
                    holder.calculator_input12_tip_textview.setText("");
                    return;
                }
                double input_double = Double.parseDouble(input_value);
                if(input_double < 1 ){
                    holder.calculator_input12_tip_textview.setText("毛级");
                } else if(input_double < 10){
                    holder.calculator_input12_tip_textview.setText("块级");
                } else if(input_double < 100){
                    holder.calculator_input12_tip_textview.setText("十级");
                } else if(input_double < 1000){
                    holder.calculator_input12_tip_textview.setText("百级");
                }else if(input_double < 10000){
                    holder.calculator_input12_tip_textview.setText("千级");
                }else if(input_double < 100000){
                    holder.calculator_input12_tip_textview.setText("万级");
                }else if(input_double < 1000000){
                    holder.calculator_input12_tip_textview.setText("十万级");
                }else if(input_double < 10000000){
                    holder.calculator_input12_tip_textview.setText("百万级");
                }else if(input_double < 100000000){
                    holder.calculator_input12_tip_textview.setText("千万级");
                }else if(input_double < 1000000000){
                    holder.calculator_input12_tip_textview.setText("亿级");
                }





                // ------------------- input17—2  差额 计算 begin --------------------

                double input7_double = 0;   //  公积金贷款
                String input7_value =  holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input7_value) || ".".equals(input7_value)){
                    input7_double = 0;
                }else{
                    input7_double = Double.parseDouble(input7_value);
                }


                double input9_double_percent = 0;   //  公积金贷款
                String input9_value =  holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_value) || ".".equals(input9_value)){
                    input9_double_percent = 0;
                }else{
                    input9_double_percent = Double.parseDouble(input9_value);
                }

                double  input9_double = input7_double * input9_double_percent * 0.01 ;  // 首付


                double input12_double = 0;   //  公积金贷款
                String input12_value =  holder.calculator_input12_gongjijinloan_price_edt.getText().toString();
                if("".equals(input12_value) || ".".equals(input12_value)){
                    input12_double = 0;
                }else{
                    input12_double = Double.parseDouble(input12_value);
                }

                //差额 = 合同总价 - 首付 - 公积金贷款

                double distance_17_tip2_double  = input7_double - input9_double -input12_double;


                holder.calculator_input17_tip2_textview.setText(""+getPriceStr(distance_17_tip2_double));
                // ------------------- input17—2  差额 计算 end --------------------


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        holder.calculator_input17_shangyeloan_price_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input17_value = s.toString();
                if("".equals(input17_value) || ".".equals(input17_value)){
                    holder.calculator_input17_tip_textview.setText("");
                    return;
                }
                double input17_double = Double.parseDouble(input17_value);
                if(input17_double < 1 ){
                    holder.calculator_input17_tip2_textview.setText("毛级");
                } else if(input17_double < 10){
                    holder.calculator_input17_tip2_textview.setText("块级");
                } else if(input17_double < 100){
                    holder.calculator_input17_tip2_textview.setText("十级");
                } else if(input17_double < 1000){
                    holder.calculator_input17_tip2_textview.setText("百级");
                }else if(input17_double < 10000){
                    holder.calculator_input17_tip2_textview.setText("千级");
                }else if(input17_double < 100000){
                    holder.calculator_input17_tip2_textview.setText("万级");
                }else if(input17_double < 1000000){
                    holder.calculator_input17_tip2_textview.setText("十万级");
                }else if(input17_double < 10000000){
                    holder.calculator_input17_tip2_textview.setText("百万级");
                }else if(input17_double < 100000000){
                    holder.calculator_input17_tip2_textview.setText("千万级");
                }else if(input17_double < 1000000000){
                    holder.calculator_input17_tip2_textview.setText("亿级");
                }


                holder.calculator_input17_tip2_textview.setText("亿级");



                double input7_double = 0;   //  合同总价
              String input7_value =  holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input7_value) || ".".equals(input7_value)){
                    input7_double = 0;
                }else{

                    input7_double = Double.parseDouble(input7_value);
                }

                double input9_double_percent = 0;   //  公积金贷款
                String input9_value =  holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_value) || ".".equals(input9_value)){
                    input9_double_percent = 0;
                }else{
                    input9_double_percent = Double.parseDouble(input9_value);
                }

                double  input9_double = input7_double * input9_double_percent * 0.01 ;  // 首付


                double input12_double = 0;   //  公积金贷款
                String input12_value =  holder.calculator_input12_gongjijinloan_price_edt.getText().toString();
                if("".equals(input12_value) || ".".equals(input12_value)){
                    input12_double = 0;
                }else{
                    input12_double = Double.parseDouble(input12_value);
                }

                //差额 = 合同总价 - 首付 - 公积金贷款

                double distance_17_tip2_double  = input7_double - input9_double -input12_double;


                holder.calculator_input17_tip2_textview.setText(""+getPriceStr(distance_17_tip2_double));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        holder.calculator_input17_shangyeloan_price_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input_value = s.toString();
                if("".equals(input_value) || ".".equals(input_value)){
                    holder.calculator_input17_tip_textview.setText("");
                    return;
                }
                double input_double = Double.parseDouble(input_value);
                if(input_double < 1 ){
                    holder.calculator_input17_tip_textview.setText("毛级");
                } else if(input_double < 10){
                    holder.calculator_input17_tip_textview.setText("块级");
                } else if(input_double < 100){
                    holder.calculator_input17_tip_textview.setText("十级");
                } else if(input_double < 1000){
                    holder.calculator_input17_tip_textview.setText("百级");
                }else if(input_double < 10000){
                    holder.calculator_input17_tip_textview.setText("千级");
                }else if(input_double < 100000){
                    holder.calculator_input17_tip_textview.setText("万级");
                }else if(input_double < 1000000){
                    holder.calculator_input17_tip_textview.setText("十万级");
                }else if(input_double < 10000000){
                    holder.calculator_input17_tip_textview.setText("百万级");
                }else if(input_double < 100000000){
                    holder.calculator_input17_tip_textview.setText("千万级");
                }else if(input_double < 1000000000){
                    holder.calculator_input17_tip_textview.setText("亿级");
                }





                double input7_double = 0;   //  合同总价
                String input7_value =  holder.calculator_input7_allhomeprice_edt.getText().toString();
                if("".equals(input7_value) || ".".equals(input7_value)){
                    input7_double = 0;
                }else{

                    input7_double = Double.parseDouble(input7_value);
                }

                double input9_double_percent = 0;   //  公积金贷款
                String input9_value =  holder.calculator_input9_firsthomepaydone_edt.getText().toString();
                if("".equals(input9_value) || ".".equals(input9_value)){
                    input9_double_percent = 0;
                }else{
                    input9_double_percent = Double.parseDouble(input9_value);
                }

                double  input9_double = input7_double * input9_double_percent * 0.01 ;  // 首付


                double input12_double = 0;   //  公积金贷款
                String input12_value =  holder.calculator_input12_gongjijinloan_price_edt.getText().toString();
                if("".equals(input12_value) || ".".equals(input12_value)){
                    input12_double = 0;
                }else{
                    input12_double = Double.parseDouble(input12_value);
                }

                //差额 = 合同总价 - 首付 - 公积金贷款

                double distance_17_tip2_double  = input7_double - input9_double -input12_double;


                holder.calculator_input17_tip2_textview.setText(""+getPriceStr(distance_17_tip2_double));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        holder.calculator_input10_group_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // input_line_11  VISIBLE
                    holder.calculator_input11_layout.setVisibility(View.VISIBLE);
                    holder.calculator_input11_gongjijinloan_percent_inallloan_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input11_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common4_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common2_textview.setVisibility(View.VISIBLE);


                    // input_line_12 VISIBLE
                    holder.calculator_input12_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_gongjijinloan_price_edt.setVisibility(View.VISIBLE);


                    // input_line_13 VISIBLE

                    holder.calculator_input13_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input13_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input13_gongjijinloan_benefitrate_edt.setVisibility(View.VISIBLE);


                    // input_line_14 VISIBLE
                    holder.calculator_input14_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input14_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input14_gongjijinloan_yearcount_edt.setVisibility(View.VISIBLE);

                    //
                    // input_line_15 VISIBLE
                    holder.calculator_input15_group.setVisibility(View.VISIBLE);


                    // input_line_16 INVISIBLE
                    holder.calculator_input16_shangyeloan_percent_inallloan_edt.setVisibility(View.INVISIBLE);
                    holder.calculator_input16_tip_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input16_common4_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input16_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input16_common2_textview.setVisibility(View.INVISIBLE);


                    // input_line_17 INVISIBLE
                    holder.calculator_input17_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input17_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input17_tip_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input17_shangyeloan_price_edt.setVisibility(View.INVISIBLE);
                    holder.calculator_input17_tip2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input17_common4_textview.setVisibility(View.INVISIBLE);
                    // input_line_18 INVISIBLE

                    holder.calculator_input18_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input18_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input18_shangye_benefitrate_edt.setVisibility(View.INVISIBLE);


                    // input_line_19 INVISIBLE
                    holder.calculator_input19_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input19_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input19_shangyeloan_yearcount_edt.setVisibility(View.INVISIBLE);

                    //
                    // input_line_20 INVISIBLE
                    holder.calculator_input20_group.setVisibility(View.INVISIBLE);

                }

            }
        });


        holder.calculator_input10_group_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
//                    holder.calculator_input11_layout.setVisibility(View.INVISIBLE);


                    // input_line_11  invisible
                    holder.calculator_input11_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input11_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input11_common4_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input11_gongjijinloan_percent_inallloan_edt.setVisibility(View.INVISIBLE);
                    holder.calculator_input11_tip_textview.setVisibility(View.INVISIBLE);



                    // input_line_12 invisible
                    holder.calculator_input12_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input12_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input12_tip_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input12_gongjijinloan_price_edt.setVisibility(View.INVISIBLE);


                    // input_line_13 invisible
                    holder.calculator_input13_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input13_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input13_gongjijinloan_benefitrate_edt.setVisibility(View.INVISIBLE);


                   // input_line_14 invisible
                    holder.calculator_input14_common2_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input14_common3_textview.setVisibility(View.INVISIBLE);
                    holder.calculator_input14_gongjijinloan_yearcount_edt.setVisibility(View.INVISIBLE);


                    // input_line_15 invisible
                    holder.calculator_input15_group.setVisibility(View.INVISIBLE);



                    // input_line_16  VISIBLE
                    holder.calculator_input16_shangyeloan_percent_inallloan_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input16_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common4_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common2_textview.setVisibility(View.VISIBLE);


                    // input_line_17 VISIBLE
                    holder.calculator_input17_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_shangyeloan_price_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input17_tip2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_common4_textview.setVisibility(View.VISIBLE);

                    // input_line_18 VISIBLE

                    holder.calculator_input18_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input18_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input18_shangye_benefitrate_edt.setVisibility(View.VISIBLE);


                    // input_line_19 VISIBLE
                    holder.calculator_input19_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input19_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input19_shangyeloan_yearcount_edt.setVisibility(View.VISIBLE);

                    //
                    // input_line_20 VISIBLE
                    holder.calculator_input20_group.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.calculator_input10_group_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    // input_line_11  VISIBLE
                    holder.calculator_input11_layout.setVisibility(View.VISIBLE);
                    holder.calculator_input11_gongjijinloan_percent_inallloan_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input11_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common4_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input11_common2_textview.setVisibility(View.VISIBLE);


                    // input_line_12 VISIBLE
                    holder.calculator_input12_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input12_gongjijinloan_price_edt.setVisibility(View.VISIBLE);


                    // input_line_13 VISIBLE

                    holder.calculator_input13_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input13_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input13_gongjijinloan_benefitrate_edt.setVisibility(View.VISIBLE);


                    // input_line_14 VISIBLE
                    holder.calculator_input14_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input14_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input14_gongjijinloan_yearcount_edt.setVisibility(View.VISIBLE);

                    //
                    // input_line_15 VISIBLE
                    holder.calculator_input15_group.setVisibility(View.VISIBLE);


                    // input_line_16  VISIBLE
                    holder.calculator_input16_shangyeloan_percent_inallloan_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input16_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common4_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input16_common2_textview.setVisibility(View.VISIBLE);


                    // input_line_17 VISIBLE
                    holder.calculator_input17_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_tip_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_shangyeloan_price_edt.setVisibility(View.VISIBLE);
                    holder.calculator_input17_tip2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input17_common4_textview.setVisibility(View.VISIBLE);

                    // input_line_18 VISIBLE

                    holder.calculator_input18_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input18_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input18_shangye_benefitrate_edt.setVisibility(View.VISIBLE);


                    // input_line_19 VISIBLE
                    holder.calculator_input19_common2_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input19_common3_textview.setVisibility(View.VISIBLE);
                    holder.calculator_input19_shangyeloan_yearcount_edt.setVisibility(View.VISIBLE);

                    //
                    // input_line_20 VISIBLE
                    holder.calculator_input20_group.setVisibility(View.VISIBLE);

                }

            }
        });



        return holder;

    }

    void print(String logstr){
        android.util.Log.i("ZHomeDownPay",logstr);
    }


    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {


//        name_xx20参数_
        String oneLine = (String) (mList.get(position));

        String[] strArr = oneLine.split("_");

        if(strArr == null || strArr.length != 21){

            return ;
        }

        String mline0_str = strArr[0];
        String mline1_boolean = strArr[1];
        String mline2_boolean = strArr[2];
        String mline3_boolean = strArr[3];
        String mline4_boolean = strArr[4];
        String mline5_double = strArr[5];
        String mline6_double = strArr[6];
        String mline7_double = strArr[7];
        String mline8_double = strArr[8];
        String mline9_double = strArr[9];
        String mline10_int = strArr[10];   // 0--公积金  1--商业  2--混合
        String mline11_double = strArr[11];
        String mline12_double = strArr[12];
        String mline13_double = strArr[13];
        String mline14_double = strArr[14];
        String mline15_boolean = strArr[15];
        String mline16_double = strArr[16];
        String mline17_double = strArr[17];
        String mline18_double = strArr[18];
        String mline19_double = strArr[19];
        String mline20_boolean = strArr[20];

        print("one_line_insert mline0_str__A oneLine = "+ oneLine);
        holder.calcul_title_textview.setText("沪市房贷计算器【"+mline0_str.trim()+"】");

        // line_1
        if(Boolean.parseBoolean(mline1_boolean)){
            holder.calculator_input1_group_btn1.setChecked(true);
            holder.calculator_input1_group_btn2.setChecked(false);
        } else {
            holder.calculator_input1_group_btn1.setChecked(false);
            holder.calculator_input1_group_btn2.setChecked(true);
        }


        // line_2
        if(Boolean.parseBoolean(mline2_boolean)){
            holder.calculator_input2_group_btn1.setChecked(true);
            holder.calculator_input2_group_btn2.setChecked(false);
        } else {
            holder.calculator_input2_group_btn1.setChecked(false);
            holder.calculator_input2_group_btn2.setChecked(true);
        }


        // line_3
        if(Boolean.parseBoolean(mline3_boolean)){
            holder.calculator_input3_group_btn1.setChecked(true);
            holder.calculator_input3_group_btn2.setChecked(false);
        } else {
            holder.calculator_input3_group_btn1.setChecked(false);
            holder.calculator_input3_group_btn2.setChecked(true);
        }


        // line_4
        if(Boolean.parseBoolean(mline4_boolean)){
            holder.calculator_input4_group_btn1.setChecked(true);
            holder.calculator_input4_group_btn2.setChecked(false);
        } else {
            holder.calculator_input4_group_btn1.setChecked(false);
            holder.calculator_input4_group_btn2.setChecked(true);
        }


        // line_5
        holder.calculator_input5_homearea_edt.setText(mline5_double);

        // line_6
        holder.calculator_input6_sellerbuyhomeprice_edt.setText(mline6_double);

        // line_7
        holder.calculator_input7_allhomeprice_edt.setText(mline7_double);

        // line_8
        holder.calculator_input8_middle_percent_in_allprice_edt.setText(mline8_double);

        // line_9
        holder.calculator_input9_firsthomepaydone_edt.setText(mline9_double);


        // line_10


        HomeDownPay.LoanType mLoanType = HomeDownPay.LoanType.GONGJIJIN;

        if( Integer.parseInt(mline10_int) == 0 ){
            mLoanType = HomeDownPay.LoanType.GONGJIJIN;
            holder.calculator_input10_group_btn1.setChecked(true);
            holder.calculator_input10_group_btn2.setChecked(false);
            holder.calculator_input10_group_btn3.setChecked(false);
        } else if(Integer.parseInt(mline10_int) == 1){

            mLoanType = HomeDownPay.LoanType.SHANGYE;
            holder.calculator_input10_group_btn1.setChecked(false);
            holder.calculator_input10_group_btn2.setChecked(true);
            holder.calculator_input10_group_btn3.setChecked(false);

        } else {
            mLoanType = HomeDownPay.LoanType.HUNHE;
            holder.calculator_input10_group_btn1.setChecked(false);
            holder.calculator_input10_group_btn2.setChecked(false);
            holder.calculator_input10_group_btn3.setChecked(true);
        }

        // line_11
        holder.calculator_input11_gongjijinloan_percent_inallloan_edt.setText(mline11_double);

        if(Double.parseDouble(mline11_double) == 0){
            holder.calculator_input12_gongjijinloan_price_edt.setText(mline12_double);
        } else {
            holder.calculator_input12_gongjijinloan_price_edt.setText("0");
        }
        // line_12


        // line_13
        holder.calculator_input13_gongjijinloan_benefitrate_edt.setText(mline13_double);

        // line_14
        holder.calculator_input14_gongjijinloan_yearcount_edt.setText(mline14_double);

        // line_15
        if(Boolean.parseBoolean(mline15_boolean )){
            holder.calculator_input15_group_btn1.setChecked(true);
            holder.calculator_input15_group_btn2.setChecked(false);
        } else {
            holder.calculator_input15_group_btn1.setChecked(false);
            holder.calculator_input15_group_btn2.setChecked(true);
        }

        // line_16
        holder.calculator_input16_shangyeloan_percent_inallloan_edt.setText(mline16_double);


        if(Double.parseDouble(mline16_double) == 0){
            holder.calculator_input17_shangyeloan_price_edt.setText(mline17_double);
        } else {
            holder.calculator_input17_shangyeloan_price_edt.setText("0");
        }

        // line_17

        // line_18
        holder.calculator_input18_shangye_benefitrate_edt.setText(mline18_double);

        // line_19
        holder.calculator_input19_shangyeloan_yearcount_edt.setText(mline19_double);

        // line_20
        if(Boolean.parseBoolean(mline20_boolean )){
            holder.calculator_input20_group_btn1.setChecked(true);
            holder.calculator_input20_group_btn2.setChecked(false);
            print("one_line_insert mline20_boolean_A  mline20_boolean = "+ mline20_boolean);
        } else {
            holder.calculator_input20_group_btn1.setChecked(false);
            holder.calculator_input20_group_btn2.setChecked(true);
            print("one_line_insert mline20_boolean_B  mline20_boolean = "+ mline20_boolean);
        }



        holder.begin_calcul_textview.setText("开始依据输入计算贷款结果("+mline0_str.trim()+")");

        HomeDownPay mHomeDownPay = new HomeDownPay(Boolean.parseBoolean(mline1_boolean),Boolean.parseBoolean(mline2_boolean),Boolean.parseBoolean(mline3_boolean),Boolean.parseBoolean(mline4_boolean),
              Double.parseDouble(mline5_double),              Double.parseDouble(mline6_double),              Double.parseDouble(mline7_double),              Double.parseDouble(mline8_double),              Double.parseDouble(mline9_double),
                mLoanType,
                Double.parseDouble(mline11_double),              Double.parseDouble(mline12_double),              Double.parseDouble(mline13_double),              (int)Double.parseDouble(mline14_double),              Boolean.parseBoolean(mline15_boolean),
                Double.parseDouble(mline16_double),              Double.parseDouble(mline17_double),              Double.parseDouble(mline18_double),              (int)Double.parseDouble(mline19_double),              Boolean.parseBoolean(mline20_boolean)
        );

                mHomeDownPay.setCal_name(mline0_str);
        print("one_line_insert mline0_str__B  oneLine = "+ oneLine);

        // result_1
        print("ZHomeDownPay1  buyer_buyhome_zengzhishui_percent:"+mHomeDownPay.buyer_buyhome_zengzhishui_percent);
        print("ZHomeDownPay1  buyer_buyhome_zengzhishui_price:"+mHomeDownPay.buyer_buyhome_zengzhishui_price);
        holder.calcul_line1_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_zengzhishui_percent * 100));
        holder.calcul_line1_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_zengzhishui_price));


        // result_2
        print("ZHomeDownPay2  buyer_buyhome_fujiashui_percent:"+mHomeDownPay.buyer_buyhome_fujiashui_percent);
        print("ZHomeDownPay2  buyer_buyhome_fujiashui_price:"+mHomeDownPay.buyer_buyhome_fujiashui_price);
        holder.calcul_line2_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_fujiashui_percent * 100));
        holder.calcul_line2_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_fujiashui_price));

        // result_3
        print("ZHomeDownPay3  buyer_buyhome_geshui_percent:"+mHomeDownPay.buyer_buyhome_geshui_percent);
        print("ZHomeDownPay3  buyer_buyhome_geshui_price:"+mHomeDownPay.buyer_buyhome_geshui_price);
        holder.calcul_line3_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_geshui_percent * 100));
        holder.calcul_line3_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_geshui_price));


        // result_4
        print("ZHomeDownPay4  buyer_buyhome_qishui_percent:"+mHomeDownPay.buyer_buyhome_qishui_percent);
        print("ZHomeDownPay4  buyer_buyhome_qishui_price:"+mHomeDownPay.buyer_buyhome_qishui_price);
        holder.calcul_line4_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_qishui_percent * 100));
        holder.calcul_line4_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_qishui_price));


        // result_5
        print("ZHomeDownPay5  input_buyer_buyhome_zhongjie_percent:"+mHomeDownPay.input_buyer_buyhome_zhongjie_percent);
        print("ZHomeDownPay5  input_buyer_buyhome_zhongjie_price:"+mHomeDownPay.input_buyer_buyhome_zhongjie_price);
        holder.calcul_line5_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.input_buyer_buyhome_zhongjie_percent ));
        holder.calcul_line5_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.input_buyer_buyhome_zhongjie_price));


        // result_6
        print("ZHomeDownPay6  buyer_buyhome_downpayment_percent:"+mHomeDownPay.buyer_buyhome_downpayment_percent);
        print("ZHomeDownPay6  buyer_buyhome_downpayment_price:"+mHomeDownPay.buyer_buyhome_downpayment_price);
        holder.calcul_line6_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_downpayment_percent ));
        holder.calcul_line6_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price));


        // zong_1

        print("ZHomeDownPay_zong1  buyer_all_tax_price:"+mHomeDownPay.buyer_all_tax_price);
        holder.calcul_line_zong1_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_all_tax_price ));

        // zong_2
        print("ZHomeDownPay_zong2  buyer_first_buyhome_price:"+mHomeDownPay.buyer_first_buyhome_price);
        holder.calcul_line_zong2_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_first_buyhome_price ));


        // result_7
        print("ZHomeDownPay7  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price);
        print("ZHomeDownPay7  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate);
        holder.calcul_line7_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price));
        holder.calcul_line7_result2_rate_textview.setText(getPercentWithTwoBlank(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate ));


        // result_8
        print("ZHomeDownPay8  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price);
        print("ZHomeDownPay8  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate);
        holder.calcul_line8_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price));
        holder.calcul_line8_result2_rate_textview.setText(getPercentWithTwoBlank(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate ));


        // result_9
        print("ZHomeDownPay9  mHomeDownPay.mLoanEntry.getAll_loan_price():"+mHomeDownPay.mLoanEntry.getAll_loan_price());
        holder.calcul_line9_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getAll_loan_price()));

        // result_10
        print("ZHomeDownPay10  mHomeDownPay.buyer_buyhome_downpayment_price:"+mHomeDownPay.buyer_buyhome_downpayment_price);
        holder.calcul_line10_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price));


        // result_11
        print("ZHomeDownPay11  估房总价::"+(mHomeDownPay.buyer_buyhome_downpayment_price + mHomeDownPay.mLoanEntry.getAll_loan_price()));
        holder.calcul_line11_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price + mHomeDownPay.mLoanEntry.getAll_loan_price()));


        // result_12
        print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getGongJiJin_YueGong():"+mHomeDownPay.mLoanEntry.getGongJiJin_YueGong());
        print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount);
        print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth():"+mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth());
        holder.calcul_line12_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getGongJiJin_YueGong()));
        holder.calcul_line12_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount * 12)+"_月减");
        holder.calcul_line12_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth()+" ]");


        // result_13
        print("ZHomeDownPay13  mHomeDownPay.mLoanEntry.getShangYe_YueGong():"+mHomeDownPay.mLoanEntry.getShangYe_YueGong());
        print("ZHomeDownPay13  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount);
        print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth():"+mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth());
        holder.calcul_line13_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getShangYe_YueGong()));
        holder.calcul_line13_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount * 12)+"_月减");
        holder.calcul_line13_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth()+" ]");



        // zong_3
        print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_all_yuegong():"+mHomeDownPay.mLoanEntry.get_all_yuegong());
        print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_min_yearcount():"+mHomeDownPay.mLoanEntry.get_min_yearcount());
        print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth():"+mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth());
        holder.calcul_line_zong3_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_all_yuegong()));
        holder.calcul_line_zong3_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.get_min_yearcount() * 12)+"_月减");
        holder.calcul_line_zong3_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth()+" ]");


        // result_14
        print("ZHomeDownPay14  mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()="+( mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()));
        holder.calcul_line14_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()));



        // result_15
        print("ZHomeDownPay15  mHomeDownPay.mLoanEntry.get_shangye_zonglixi()="+( mHomeDownPay.mLoanEntry.get_shangye_zonglixi()));
        holder.calcul_line15_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_shangye_zonglixi()));


        // zong_4
        print("ZHomeDownPay_zong4  mHomeDownPay.mLoanEntry.get_zonglixi():"+mHomeDownPay.mLoanEntry.get_zonglixi());
        holder.calcul_line_zong4_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_zonglixi()));


        // result_16
        print("ZHomeDownPay16  mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()="+( mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()));
        holder.calcul_line16_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()));



        // result_17
        print("ZHomeDownPay17  mHomeDownPay.mLoanEntry.get_shangye_zonghuang()="+( mHomeDownPay.mLoanEntry.get_shangye_zonghuang()));
        holder.calcul_line17_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_shangye_zonghuang()));


        // zong_5
        print("ZHomeDownPay_zong5  mHomeDownPay.mLoanEntry.get_zonghuang():"+mHomeDownPay.mLoanEntry.get_zonghuang());
        holder.calcul_line_zong5_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_zonghuang()));




        //  ———————————————— 计算数据  End ————————————————

        holder.begin_calcul_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean input1_isputong =  holder.calculator_input1_group_btn1.isChecked();
                boolean input2_onlyone_for_seller =  holder.calculator_input2_group_btn1.isChecked();
                boolean input3_is_five_year =  holder.calculator_input3_group_btn1.isChecked();
                boolean input4_is_buyer_firsthome =  holder.calculator_input4_group_btn1.isChecked();

                // line 5
                String input5_home_are_rawstr = holder.calculator_input5_homearea_edt.getText().toString().trim();;
                if("".equals(input5_home_are_rawstr) || ".".equals(input5_home_are_rawstr)){
                    Toast.makeText(mContext,"当前 input5_房屋输入面积为空! 请检查输入!",Toast.LENGTH_LONG).show();
                    return;
                }
                double input5_home_are =  Double.parseDouble(input5_home_are_rawstr);

                // line 6
                String input6_seller_origin_buy_homr_price_rawstr = holder.calculator_input6_sellerbuyhomeprice_edt.getText().toString().trim();;
                if("".equals(input6_seller_origin_buy_homr_price_rawstr) || ".".equals(input6_seller_origin_buy_homr_price_rawstr)){
                    Toast.makeText(mContext,"当前 input6_房主买入价输入为空! 请检查输入!",Toast.LENGTH_LONG).show();
                    return;
                }
                double input6_seller_origin_price =  Double.parseDouble(input6_seller_origin_buy_homr_price_rawstr);

                // line 7
                String input7_buyer_buy_homr_allprice_rawstr = holder.calculator_input7_allhomeprice_edt.getText().toString().trim();;
                if("".equals(input7_buyer_buy_homr_allprice_rawstr) || ".".equals(input7_buyer_buy_homr_allprice_rawstr)){
                    Toast.makeText(mContext,"当前 input7_当前买入价格输入为空! 请检查输入!",Toast.LENGTH_LONG).show();
                    return;
                }
                double input7_buyer_buy_home_allprice =  Double.parseDouble(input7_buyer_buy_homr_allprice_rawstr);

                // line 8
                String input8_middle_percent_rawstr = holder.calculator_input8_middle_percent_in_allprice_edt.getText().toString().trim();;
                double input8_middle_percent = 0 ;
                if("".equals(input8_middle_percent_rawstr) || ".".equals(input8_middle_percent_rawstr)){
                    input8_middle_percent = 0 ;
                } else {
                    input8_middle_percent =  Double.parseDouble(input8_middle_percent_rawstr);
                }

                // line 9
                String input9_firsthomepaydone_percent_rawstr = holder.calculator_input9_firsthomepaydone_edt.getText().toString().trim();;
                double input9_firsthomepaydone_percent = 0 ;
                if("".equals(input9_firsthomepaydone_percent_rawstr) || ".".equals(input9_firsthomepaydone_percent_rawstr)){
                    input9_firsthomepaydone_percent = 0 ;
                } else {
                    input9_firsthomepaydone_percent =  Double.parseDouble(input9_firsthomepaydone_percent_rawstr);
                }


                // line 10
                boolean is_chun_gongjijin_loan =  holder.calculator_input10_group_btn1.isChecked();
                boolean is_chun_shengye_loan =  holder.calculator_input10_group_btn2.isChecked();
                boolean is_hunhe_loan =  holder.calculator_input10_group_btn3.isChecked();

                HomeDownPay.LoanType input10_loan_type = HomeDownPay.LoanType.GONGJIJIN ;  // 0--公积金贷款   1-商业贷款   2-混合贷款
                int mLoan_Int_Flag = 0;
                if(is_chun_gongjijin_loan){
                    input10_loan_type = HomeDownPay.LoanType.GONGJIJIN ;
                    mLoan_Int_Flag = 0;
                } else if(is_chun_shengye_loan){
                    input10_loan_type = HomeDownPay.LoanType.SHANGYE ;
                    mLoan_Int_Flag = 1;
                } else if(is_hunhe_loan){
                    input10_loan_type = HomeDownPay.LoanType.HUNHE ;
                    mLoan_Int_Flag = 2;
                }


                // line 11
                String input11_chun_gongjijinloan_percent_inallloan_rawstr = holder.calculator_input11_gongjijinloan_percent_inallloan_edt.getText().toString().trim();;
                double input11_chun_gongjijinloan_inallloan_percent = 0 ;
                if("".equals(input11_chun_gongjijinloan_percent_inallloan_rawstr) || ".".equals(input11_chun_gongjijinloan_percent_inallloan_rawstr)){
                    input11_chun_gongjijinloan_inallloan_percent = 0 ;
                } else {
                    input11_chun_gongjijinloan_inallloan_percent =  Double.parseDouble(input11_chun_gongjijinloan_percent_inallloan_rawstr);
                }


                // line 12
                String input12_gongjijinloan_price_rawstr = holder.calculator_input12_gongjijinloan_price_edt.getText().toString().trim();;
                double input12_gongjijinloan_price = 0 ;
                if("".equals(input12_gongjijinloan_price_rawstr) || ".".equals(input12_gongjijinloan_price_rawstr )){
                    input12_gongjijinloan_price = 0 ;
                } else {
                    input12_gongjijinloan_price =  Double.parseDouble(input12_gongjijinloan_price_rawstr);
                }


                // line 13
                String input13_gongjijinloan_benefitrate_rawstr = holder.calculator_input13_gongjijinloan_benefitrate_edt.getText().toString().trim();;
                double input13_gongjijinloan_benefitrate = 0 ;
                if("".equals(input13_gongjijinloan_benefitrate_rawstr) || ".".equals(input13_gongjijinloan_benefitrate_rawstr)){
                    input13_gongjijinloan_benefitrate = 0 ;
                } else {
                    input13_gongjijinloan_benefitrate =  Double.parseDouble(input13_gongjijinloan_benefitrate_rawstr);
                }


                // line 14
                String input14_gongjijinloan_yearcount_rawstr = holder.calculator_input14_gongjijinloan_yearcount_edt.getText().toString().trim();;
                int  input14_gongjijinloan_yearcount = 0 ;
                if("".equals(input14_gongjijinloan_yearcount_rawstr) || ".".equals(input14_gongjijinloan_yearcount_rawstr)){
                    input14_gongjijinloan_yearcount = 0 ;
                } else {
                    input14_gongjijinloan_yearcount =  Integer.parseInt(input14_gongjijinloan_yearcount_rawstr);
                }


                // line15   公积金 等额本息
                boolean input15_gongjijin_isSame_paydown_permonth = holder.calculator_input15_group_btn1.isChecked();



                // line 16
                String input16_chun_shangyeloan_percent_inallloan_rawstr = holder.calculator_input16_shangyeloan_percent_inallloan_edt.getText().toString().trim();;
                double input16_chun_shangyeloan_inallloan_percent = 0 ;
                if("".equals(input16_chun_shangyeloan_percent_inallloan_rawstr) || ".".equals(input16_chun_shangyeloan_percent_inallloan_rawstr)){
                    input16_chun_shangyeloan_inallloan_percent = 0 ;
                } else {
                    input16_chun_shangyeloan_inallloan_percent =  Double.parseDouble(input16_chun_shangyeloan_percent_inallloan_rawstr);
                }


                // line 17
                String input17_shangyeloan_price_rawstr = holder.calculator_input17_shangyeloan_price_edt.getText().toString().trim();;
                double input17_shangyeloan_price = 0 ;
                if("".equals(input17_shangyeloan_price_rawstr) || ".".equals(input17_shangyeloan_price_rawstr)){
                    input17_shangyeloan_price = 0 ;
                } else {
                    input17_shangyeloan_price =  Double.parseDouble(input17_shangyeloan_price_rawstr);
                }


                // line 18
                String input18_shangyeloan_benefitrate_rawstr = holder.calculator_input18_shangye_benefitrate_edt.getText().toString().trim();;
                double input18_shangyeloan_benefitrate = 0 ;
                if("".equals(input18_shangyeloan_benefitrate_rawstr) || ".".equals(input18_shangyeloan_benefitrate_rawstr)){
                    input18_shangyeloan_benefitrate = 0 ;
                } else {
                    input18_shangyeloan_benefitrate =  Double.parseDouble(input18_shangyeloan_benefitrate_rawstr);
                }


                // line 19
                String input19_shangyeloan_yearcount_rawstr = holder.calculator_input19_shangyeloan_yearcount_edt.getText().toString().trim();
                int  input19_shangyeloan_yearcount = 0 ;
                if("".equals(input19_shangyeloan_yearcount_rawstr) || ".".equals(input19_shangyeloan_yearcount_rawstr)){
                    input19_shangyeloan_yearcount = 0 ;
                } else {
                    input19_shangyeloan_yearcount =  Integer.parseInt(input19_shangyeloan_yearcount_rawstr);
                }

                // line20    商贷 等额本息
                boolean input20_shangye_isSame_paydown_permonth = holder.calculator_input20_group_btn1.isChecked();



                //  ———————————————— 合规判断  Begin ————————————————


                if(input10_loan_type == HomeDownPay.LoanType.GONGJIJIN){   // 公积金贷款
                    input17_shangyeloan_price = 0;   // 公积金贷款   那么 默认把  商业贷款视为 0
                    input16_chun_shangyeloan_inallloan_percent  = 0 ;

                    if(input11_chun_gongjijinloan_inallloan_percent ==0 && input12_gongjijinloan_price == 0){
                        Toast.makeText(mContext,"当前 input11_公积贷率  和 input12_公积贷额! 不可以同时为0,请选择一个输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }


                    if(input11_chun_gongjijinloan_inallloan_percent !=0 && input12_gongjijinloan_price != 0){
                        Toast.makeText(mContext,"当前 input11_公积贷率  和 input12_公积贷额! 不可以同时输入,请选择一个输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }


                    if(input11_chun_gongjijinloan_inallloan_percent == 0 ){   // 当前输入的是额度  那么判断 贷款 input12_gongjijinloan_price + 首付款 是否等于 房价款

                        double dynamic_home_all_price =  input7_buyer_buy_home_allprice * input9_firsthomepaydone_percent * 0.01 + input12_gongjijinloan_price;

                        double price_distance = input7_buyer_buy_home_allprice - dynamic_home_all_price;

                        if(Math.abs(price_distance) >= 10){
                            Toast.makeText(mContext,"当前 input9_首付额度  与 input12_公积贷额【"+getPriceStr(dynamic_home_all_price)+"】!  与 input7_合同总价【"+getPriceStr(input7_buyer_buy_home_allprice)+"】不相近! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }
                    } else if(input12_gongjijinloan_price == 0){  //  用户输入的是公积金的贷款额度

                        double dynamic_all_loan_percent = input11_chun_gongjijinloan_inallloan_percent + input9_firsthomepaydone_percent;

                        if(dynamic_all_loan_percent != 100){
                            Toast.makeText(mContext,"当前 input9_首付费率("+getPercentWithBlank(input9_firsthomepaydone_percent)+")  与 input11_公积贷率("+getPercentWithBlank(input11_chun_gongjijinloan_inallloan_percent)+")【"+getPercentWithBlank(dynamic_all_loan_percent)+"%】不是 100%  ! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }

                    }

                }  else if(input10_loan_type == HomeDownPay.LoanType.SHANGYE){

                    input12_gongjijinloan_price = 0;   // 商业贷款 那么 默认把  公积金贷款视为 0
                    input11_chun_gongjijinloan_inallloan_percent   = 0 ;

                    if(input16_chun_shangyeloan_inallloan_percent ==0 && input17_shangyeloan_price == 0){
                        Toast.makeText(mContext,"当前 input16_商业贷率  和 input17_商业贷额! 不可以同时为0,请选择一个输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }

                    if(input16_chun_shangyeloan_inallloan_percent !=0 && input17_shangyeloan_price != 0){
                        Toast.makeText(mContext,"当前 input16_商业贷率  和 input17_商业贷额! 不可以同时输入,请选择一个输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }


                    if(input16_chun_shangyeloan_inallloan_percent == 0 ){   // 当前输入的是额度  那么判断 贷款 input12_gongjijinloan_price + 首付款 是否等于 房价款

                        double dynamic_home_all_price =  input7_buyer_buy_home_allprice * input9_firsthomepaydone_percent * 0.01  + input17_shangyeloan_price;

                        double price_distance = input7_buyer_buy_home_allprice - dynamic_home_all_price;

                        if(Math.abs(price_distance) >= 10){
                            Toast.makeText(mContext,"当前 input9_首付额度  与 input17_商业贷额【"+getPriceStr(dynamic_home_all_price)+"】!  与 input7_合同总价【"+getPriceStr(input7_buyer_buy_home_allprice)+"】不相近! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }
                    } else if(input17_shangyeloan_price == 0){  //  用户输入的是公积金的贷款额度

                        double dynamic_all_loan_percent = input16_chun_shangyeloan_inallloan_percent + input9_firsthomepaydone_percent;

                        if(dynamic_all_loan_percent != 100){
                            Toast.makeText(mContext,"当前 input9_首付费率  与 input16_商业贷率【"+getPriceStr(dynamic_all_loan_percent)+"%】不是 100%  ! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }

                    }

                } else if(input10_loan_type == HomeDownPay.LoanType.HUNHE){    //  混合贷款


                    if(input11_chun_gongjijinloan_inallloan_percent ==0 && input12_gongjijinloan_price == 0 && input16_chun_shangyeloan_inallloan_percent ==0 && input17_shangyeloan_price == 0 ){
                        Toast.makeText(mContext,"混合贷款模式下 当前 【input11_公积贷率  和 input12_公积贷额】! 当前 【input16_商业贷率  和 input17_商业贷额】! 不可以同时为0,请选择一个输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }

                    if(input11_chun_gongjijinloan_inallloan_percent !=0 && input12_gongjijinloan_price != 0 && input16_chun_shangyeloan_inallloan_percent !=0 && input17_shangyeloan_price != 0 ){
                        Toast.makeText(mContext,"混合贷款模式下 只能单独输入贷额 或贷率! 如下可选项: \n1.【input11_公积贷率  和 input16_商业贷率 】  \n2.【input12_公积贷额  和 input17_商业贷额】! \n请选择一个组合输入!",Toast.LENGTH_LONG).show();
                        return ;
                    }

                    if(input16_chun_shangyeloan_inallloan_percent == 0 && input11_chun_gongjijinloan_inallloan_percent== 0){   // 用户输入的是额度信息


                        double dynamic_home_all_price =  input7_buyer_buy_home_allprice * input9_firsthomepaydone_percent* 0.01 + input17_shangyeloan_price + input12_gongjijinloan_price;

                        double price_distance = input7_buyer_buy_home_allprice - dynamic_home_all_price;

                        if(Math.abs(price_distance) >= 10){
                            Toast.makeText(mContext,"当前 input9_首付额度+input12_公积贷额+input17_商业贷额【"+getPriceStr(dynamic_home_all_price)+"】!  与 input7_合同总价【"+getPriceStr(input7_buyer_buy_home_allprice)+"】不相近! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }


                    } else if(input17_shangyeloan_price == 0 && input12_gongjijinloan_price == 0){

                        double dynamic_all_loan_percent = input11_chun_gongjijinloan_inallloan_percent+input16_chun_shangyeloan_inallloan_percent + input9_firsthomepaydone_percent;

                        if(dynamic_all_loan_percent != 100){
                            Toast.makeText(mContext,"当前 input9_首付费率 + input11_公积贷率+ input16_商业贷率【"+getPriceStr(dynamic_all_loan_percent)+"%】不是 100%  ! 请检查",Toast.LENGTH_LONG).show();
                            return ;
                        }

                    }





                }

                //  ———————————————— 视图显示View Begin  ————————————————



                //  ———————————————— 视图显示View End  ————————————————
                if(input10_loan_type == HomeDownPay.LoanType.GONGJIJIN){

                    holder.calcul_result_line8_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line13_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line15_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line17_layout.setVisibility(View.INVISIBLE);

                    holder.calcul_result_line7_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line12_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line14_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line16_layout.setVisibility(View.VISIBLE);

                } else if(input10_loan_type == HomeDownPay.LoanType.SHANGYE){


                    holder.calcul_result_line8_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line13_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line15_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line17_layout.setVisibility(View.VISIBLE);

                    holder.calcul_result_line7_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line12_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line14_layout.setVisibility(View.INVISIBLE);
                    holder.calcul_result_line16_layout.setVisibility(View.INVISIBLE);


                } else if(input10_loan_type == HomeDownPay.LoanType.HUNHE){

                    holder.calcul_result_line8_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line13_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line15_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line17_layout.setVisibility(View.VISIBLE);

                    holder.calcul_result_line7_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line12_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line14_layout.setVisibility(View.VISIBLE);
                    holder.calcul_result_line16_layout.setVisibility(View.VISIBLE);
                }


                //  ———————————————— 合规判断  End ————————————————



                //  ———————————————— 计算数据  Begin ————————————————
                HomeDownPay mHomeDownPay = new HomeDownPay(input1_isputong,input2_onlyone_for_seller,input3_is_five_year,input4_is_buyer_firsthome,
                        input5_home_are,input6_seller_origin_price,input7_buyer_buy_home_allprice,input8_middle_percent,input9_firsthomepaydone_percent,
                        input10_loan_type,
                        input11_chun_gongjijinloan_inallloan_percent,input12_gongjijinloan_price,input13_gongjijinloan_benefitrate,input14_gongjijinloan_yearcount,input15_gongjijin_isSame_paydown_permonth,
                        input16_chun_shangyeloan_inallloan_percent,input17_shangyeloan_price,input18_shangyeloan_benefitrate,input19_shangyeloan_yearcount,input20_shangye_isSame_paydown_permonth);

                String head_str = holder.calcul_title_textview.getText().toString();

                String calcul_name = "";
                if(head_str.contains("【") && head_str.contains("】") ){

                    calcul_name = head_str.substring( head_str.lastIndexOf("【")+1,head_str.lastIndexOf("】")).trim();
                }
                mHomeDownPay.setCal_name(calcul_name);
                // 把 数据进行保存

                print(" mHomeDownPay.getSimple_Seperator_String() = "+mHomeDownPay.getSimple_Seperator_String());
                DataHolder.sdcard_zmain_loan_contentList.set(position,mHomeDownPay.getSimple_Seperator_String());

                DataHolder.writeContentToFile(DataHolder.sdcard_main_loan_file,DataHolder.sdcard_zmain_loan_contentList);


                // result_1
                print("ZHomeDownPay1  buyer_buyhome_zengzhishui_percent:"+mHomeDownPay.buyer_buyhome_zengzhishui_percent);
                print("ZHomeDownPay1  buyer_buyhome_zengzhishui_price:"+mHomeDownPay.buyer_buyhome_zengzhishui_price);
                holder.calcul_line1_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_zengzhishui_percent * 100));
                holder.calcul_line1_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_zengzhishui_price));


                // result_2
                print("ZHomeDownPay2  buyer_buyhome_fujiashui_percent:"+mHomeDownPay.buyer_buyhome_fujiashui_percent);
                print("ZHomeDownPay2  buyer_buyhome_fujiashui_price:"+mHomeDownPay.buyer_buyhome_fujiashui_price);
                holder.calcul_line2_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_fujiashui_percent * 100));
                holder.calcul_line2_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_fujiashui_price));

                // result_3
                print("ZHomeDownPay3  buyer_buyhome_geshui_percent:"+mHomeDownPay.buyer_buyhome_geshui_percent);
                print("ZHomeDownPay3  buyer_buyhome_geshui_price:"+mHomeDownPay.buyer_buyhome_geshui_price);
                holder.calcul_line3_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_geshui_percent * 100));
                holder.calcul_line3_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_geshui_price));


                // result_4
                print("ZHomeDownPay4  buyer_buyhome_qishui_percent:"+mHomeDownPay.buyer_buyhome_qishui_percent);
                print("ZHomeDownPay4  buyer_buyhome_qishui_price:"+mHomeDownPay.buyer_buyhome_qishui_price);
                holder.calcul_line4_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_qishui_percent * 100));
                holder.calcul_line4_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_qishui_price));


                // result_5
                print("ZHomeDownPay5  input_buyer_buyhome_zhongjie_percent:"+mHomeDownPay.input_buyer_buyhome_zhongjie_percent);
                print("ZHomeDownPay5  input_buyer_buyhome_zhongjie_price:"+mHomeDownPay.input_buyer_buyhome_zhongjie_price);
                holder.calcul_line5_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.input_buyer_buyhome_zhongjie_percent ));
                holder.calcul_line5_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.input_buyer_buyhome_zhongjie_price));


                // result_6
                print("ZHomeDownPay6  buyer_buyhome_downpayment_percent:"+mHomeDownPay.buyer_buyhome_downpayment_percent);
                print("ZHomeDownPay6  buyer_buyhome_downpayment_price:"+mHomeDownPay.buyer_buyhome_downpayment_price);
                holder.calcul_line6_result1_percent_textview.setText(getPercentWithBlank(mHomeDownPay.buyer_buyhome_downpayment_percent ));
                holder.calcul_line6_result2_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price));


                // zong_1

                print("ZHomeDownPay_zong1  buyer_all_tax_price:"+mHomeDownPay.buyer_all_tax_price);
                holder.calcul_line_zong1_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_all_tax_price ));

                // zong_2
                print("ZHomeDownPay_zong2  buyer_first_buyhome_price:"+mHomeDownPay.buyer_first_buyhome_price);
                holder.calcul_line_zong2_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_first_buyhome_price ));


                // result_7
                print("ZHomeDownPay7  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price);
                print("ZHomeDownPay7  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate);
                holder.calcul_line7_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_price));
                holder.calcul_line7_result2_rate_textview.setText(getPercentWithTwoBlank(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_benefit_rate ));


                // result_8
                print("ZHomeDownPay8  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price);
                print("ZHomeDownPay8  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate);
                holder.calcul_line8_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_price));
                holder.calcul_line8_result2_rate_textview.setText(getPercentWithTwoBlank(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_benefit_rate ));


                // result_9
                print("ZHomeDownPay9  mHomeDownPay.mLoanEntry.getAll_loan_price():"+mHomeDownPay.mLoanEntry.getAll_loan_price());
                holder.calcul_line9_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getAll_loan_price()));

                // result_10
                print("ZHomeDownPay10  mHomeDownPay.buyer_buyhome_downpayment_price:"+mHomeDownPay.buyer_buyhome_downpayment_price);
                holder.calcul_line10_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price));


                // result_11
                print("ZHomeDownPay11  估房总价::"+(mHomeDownPay.buyer_buyhome_downpayment_price + mHomeDownPay.mLoanEntry.getAll_loan_price()));
                holder.calcul_line11_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.buyer_buyhome_downpayment_price + mHomeDownPay.mLoanEntry.getAll_loan_price()));


                // result_12
                print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getGongJiJin_YueGong():"+mHomeDownPay.mLoanEntry.getGongJiJin_YueGong());
                print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount:"+mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount);
                print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth():"+mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth());
                holder.calcul_line12_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getGongJiJin_YueGong()));
                holder.calcul_line12_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.loanentry_gongjijin_loan_yearcount * 12)+"_月减");
                holder.calcul_line12_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.getGongJiin_YueGong_DownValue_PerMonth()+" ]");


                // result_13
                print("ZHomeDownPay13  mHomeDownPay.mLoanEntry.getShangYe_YueGong():"+mHomeDownPay.mLoanEntry.getShangYe_YueGong());
                print("ZHomeDownPay13  mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount:"+mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount);
                print("ZHomeDownPay12  mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth():"+mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth());
                holder.calcul_line13_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.getShangYe_YueGong()));
                holder.calcul_line13_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.loanentry_shangye_loan_yearcount * 12)+"_月减");
                holder.calcul_line13_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.getShangYe_YueGong_DownValue_PerMonth()+" ]");



                // zong_3
                print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_all_yuegong():"+mHomeDownPay.mLoanEntry.get_all_yuegong());
                print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_min_yearcount():"+mHomeDownPay.mLoanEntry.get_min_yearcount());
                print("ZHomeDownPay_zong3  mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth():"+mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth());
                holder.calcul_line_zong3_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_all_yuegong()));
                holder.calcul_line_zong3_result2_monthtip_textview.setText("逐_"+(mHomeDownPay.mLoanEntry.get_min_yearcount() * 12)+"_月减");
                holder.calcul_line_zong3_result3_moneytip_textview.setText("[ "+(int)mHomeDownPay.mLoanEntry.get_all_yuegong_downvalue_permonth()+" ]");


                // result_14
                print("ZHomeDownPay14  mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()="+( mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()));
                holder.calcul_line14_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_gongjijin_zonglixi()));



                // result_15
                print("ZHomeDownPay15  mHomeDownPay.mLoanEntry.get_shangye_zonglixi()="+( mHomeDownPay.mLoanEntry.get_shangye_zonglixi()));
                holder.calcul_line15_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_shangye_zonglixi()));


                // zong_4
                print("ZHomeDownPay_zong4  mHomeDownPay.mLoanEntry.get_zonglixi():"+mHomeDownPay.mLoanEntry.get_zonglixi());
                holder.calcul_line_zong4_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_zonglixi()));


                // result_16
                print("ZHomeDownPay16  mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()="+( mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()));
                holder.calcul_line16_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_gongjijin_zonghuang()));



                // result_17
                print("ZHomeDownPay17  mHomeDownPay.mLoanEntry.get_shangye_zonghuang()="+( mHomeDownPay.mLoanEntry.get_shangye_zonghuang()));
                holder.calcul_line17_result1_price_textview.setText(getPriceWithOneBlank( mHomeDownPay.mLoanEntry.get_shangye_zonghuang()));


                // zong_5
                print("ZHomeDownPay_zong5  mHomeDownPay.mLoanEntry.get_zonghuang():"+mHomeDownPay.mLoanEntry.get_zonghuang());
                holder.calcul_line_zong5_result1_price_textview.setText(getPriceWithOneBlank(mHomeDownPay.mLoanEntry.get_zonghuang()));

                Toast.makeText(mContext,"结果已更新!",Toast.LENGTH_SHORT).show();


                //  ———————————————— 计算数据  End ————————————————

            }
        });


    }


    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }


    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

       TextView calcul_title_textview;


        // ___________________input_area_begin ___________________
        RadioButton calculator_input1_group_btn1;
        RadioButton calculator_input1_group_btn2;

        RadioButton calculator_input2_group_btn1;
        RadioButton calculator_input2_group_btn2;

        RadioButton calculator_input3_group_btn1;
        RadioButton calculator_input3_group_btn2;

        RadioButton calculator_input4_group_btn1;
        RadioButton calculator_input4_group_btn2;

        EditText calculator_input5_homearea_edt;

        EditText calculator_input6_sellerbuyhomeprice_edt;
        TextView calculator_input6_tip_textview;

        TextView calculator_input6_averageprice_textview;



        EditText calculator_input7_allhomeprice_edt;
        TextView calculator_input7_tip_textview;
        TextView calculator_input7_averageprice_textview;


        EditText calculator_input8_middle_percent_in_allprice_edt;
        TextView calculator_input8_tip_textview;


        EditText calculator_input9_firsthomepaydone_edt;
        TextView calculator_input9_tip_textview;


        RadioButton calculator_input10_group_btn1;
        RadioButton calculator_input10_group_btn2;
        RadioButton calculator_input10_group_btn3;

        LinearLayout calculator_input11_layout;
        EditText calculator_input11_gongjijinloan_percent_inallloan_edt;
        TextView calculator_input11_tip_textview;

        TextView  calculator_input11_common1_textview;
        TextView calculator_input11_common2_textview;
        TextView calculator_input11_common3_textview;
        TextView  calculator_input11_common4_textview;



        LinearLayout calculator_input12_layout;
        TextView calculator_input12_tip_textview;
        EditText calculator_input12_gongjijinloan_price_edt;

        TextView calculator_input12_common1_textview;
        TextView calculator_input12_common2_textview;
        TextView calculator_input12_common3_textview;


        LinearLayout calculator_input13_layout;
        TextView calculator_input13_common1_textview;
        TextView calculator_input13_common2_textview;
        TextView calculator_input13_common3_textview;
        EditText calculator_input13_gongjijinloan_benefitrate_edt;


        LinearLayout calculator_input14_layout;
        EditText calculator_input14_gongjijinloan_yearcount_edt;
        TextView  calculator_input14_common1_textview;
        TextView  calculator_input14_common2_textview;
        TextView calculator_input14_common3_textview;


        LinearLayout calculator_input15_layout;

        TextView calculator_input15_common1_textview ;
        RadioGroup calculator_input15_group;
        RadioButton calculator_input15_group_btn1;
        RadioButton calculator_input15_group_btn2;


        LinearLayout calculator_input16_layout;
        EditText calculator_input16_shangyeloan_percent_inallloan_edt;
        TextView calculator_input16_tip_textview;

        TextView  calculator_input16_common1_textview;
        TextView calculator_input16_common2_textview;
        TextView calculator_input16_common3_textview;
        TextView  calculator_input16_common4_textview;


        LinearLayout calculator_input17_layout;
        EditText calculator_input17_shangyeloan_price_edt;
        TextView calculator_input17_tip_textview;
        TextView calculator_input17_common1_textview;
        TextView calculator_input17_common2_textview;
        TextView calculator_input17_common3_textview;
        TextView calculator_input17_common4_textview;
        TextView calculator_input17_tip2_textview;

        LinearLayout calculator_input18_layout;
        EditText calculator_input18_shangye_benefitrate_edt;
        TextView calculator_input18_common1_textview;
        TextView calculator_input18_common2_textview;
        TextView calculator_input18_common3_textview;

        LinearLayout calculator_input19_layout;
        EditText calculator_input19_shangyeloan_yearcount_edt;

        TextView  calculator_input19_common1_textview;
        TextView  calculator_input19_common2_textview;
        TextView  calculator_input19_common3_textview;

        LinearLayout calculator_input20_layout;
        TextView  calculator_input20_common1_textview;
        RadioGroup calculator_input20_group;
        RadioButton calculator_input20_group_btn1;
        RadioButton calculator_input20_group_btn2;

        // ___________________input_area_end ___________________

        TextView  begin_calcul_textview ;



        // ___________________result_define_area_ begin  ___________________





        TextView calcul_line1_result1_percent_textview;
        TextView calcul_line1_result2_price_textview;
        TextView calcul_line2_result1_percent_textview;
        TextView calcul_line2_result2_price_textview;
        TextView calcul_line3_result1_percent_textview;
        TextView calcul_line3_result2_price_textview;
        TextView calcul_line4_result1_percent_textview;
        TextView calcul_line4_result2_price_textview;
        TextView calcul_line5_result1_percent_textview;
        TextView calcul_line5_result2_price_textview;
        TextView calcul_line6_result1_percent_textview;
        TextView calcul_line6_result2_price_textview;
        TextView calcul_line_zong1_result1_price_textview;
        TextView calcul_line_zong2_result1_price_textview;

        LinearLayout calcul_result_line7_layout ;

        TextView calcul_line7_result1_price_textview;
        TextView calcul_line7_result2_rate_textview;

     LinearLayout    calcul_result_line8_layout ;

        TextView calcul_line8_result1_price_textview;
        TextView calcul_line8_result2_rate_textview;


        TextView calcul_line9_result1_price_textview;
        TextView calcul_line10_result1_price_textview;
        TextView calcul_line11_result1_price_textview;

        LinearLayout calcul_result_line12_layout ;

        TextView  calcul_line12_result1_price_textview;
        TextView calcul_line12_result2_monthtip_textview;
        TextView  calcul_line12_result3_moneytip_textview;

        LinearLayout calcul_result_line13_layout ;
        TextView  calcul_line13_result1_price_textview;
        TextView calcul_line13_result2_monthtip_textview;
        TextView  calcul_line13_result3_moneytip_textview;
        TextView  calcul_line_zong3_result1_price_textview;
        TextView calcul_line_zong3_result2_monthtip_textview;
        TextView  calcul_line_zong3_result3_moneytip_textview;

        LinearLayout calcul_result_line14_layout ;

        TextView calcul_line14_result1_price_textview;

        LinearLayout calcul_result_line15_layout ;

        TextView calcul_line15_result1_price_textview;
        TextView calcul_line_zong4_result1_price_textview;
        LinearLayout calcul_result_line16_layout ;

        TextView calcul_line16_result1_price_textview;

        LinearLayout calcul_result_line17_layout ;
        TextView calcul_line17_result1_price_textview;
        TextView calcul_line_zong5_result1_price_textview;

        // ___________________result_define_area_end ___________________
        public MyHolder(View itemView) {
            super(itemView);

            // ___________________input_area_begin ___________________



            calcul_title_textview = itemView.findViewById(R.id.calcul_title_textview);

            calculator_input1_group_btn1 = itemView.findViewById(R.id.calculator_input1_group_btn1);
            calculator_input1_group_btn2 = itemView.findViewById(R.id.calculator_input1_group_btn2);
            calculator_input2_group_btn1 = itemView.findViewById(R.id.calculator_input2_group_btn1);
            calculator_input2_group_btn2 = itemView.findViewById(R.id.calculator_input2_group_btn2);
            calculator_input3_group_btn1 = itemView.findViewById(R.id.calculator_input3_group_btn1);
            calculator_input3_group_btn2 = itemView.findViewById(R.id.calculator_input3_group_btn2);

            calculator_input4_group_btn1 = itemView.findViewById(R.id.calculator_input4_group_btn1);
            calculator_input4_group_btn2 = itemView.findViewById(R.id.calculator_input4_group_btn2);


            calculator_input5_homearea_edt = itemView.findViewById(R.id.calculator_input5_homearea_edt);

            calculator_input6_sellerbuyhomeprice_edt = itemView.findViewById(R.id.calculator_input6_sellerbuyhomeprice_edt);
            calculator_input6_tip_textview = itemView.findViewById(R.id.calculator_input6_tip_textview);
            calculator_input6_averageprice_textview= itemView.findViewById(R.id.calculator_input6_averageprice_textview);

            calculator_input7_allhomeprice_edt = itemView.findViewById(R.id.calculator_input7_allhomeprice_edt);
            calculator_input7_tip_textview = itemView.findViewById(R.id.calculator_input7_tip_textview);
            calculator_input7_averageprice_textview= itemView.findViewById(R.id.calculator_input7_averageprice_textview);
            calculator_input8_middle_percent_in_allprice_edt = itemView.findViewById(R.id.calculator_input8_middle_percent_in_allprice_edt);
            calculator_input8_tip_textview = itemView.findViewById(R.id.calculator_input8_tip_textview);

            calculator_input9_firsthomepaydone_edt = itemView.findViewById(R.id.calculator_input9_firsthomepaydone_edt);
            calculator_input9_tip_textview = itemView.findViewById(R.id.calculator_input9_tip_textview);


            calculator_input10_group_btn1 = itemView.findViewById(R.id.calculator_input10_group_btn1);
            calculator_input10_group_btn2 = itemView.findViewById(R.id.calculator_input10_group_btn2);
            calculator_input10_group_btn3 = itemView.findViewById(R.id.calculator_input10_group_btn3);


            calculator_input11_layout = itemView.findViewById(R.id.calculator_input11_layout);
            calculator_input11_gongjijinloan_percent_inallloan_edt = itemView.findViewById(R.id.calculator_input11_gongjijinloan_percent_inallloan_edt);

            calculator_input11_tip_textview = itemView.findViewById(R.id.calculator_input11_tip_textview);
            calculator_input11_common1_textview = itemView.findViewById(R.id.calculator_input11_common1_textview);
            calculator_input11_common2_textview = itemView.findViewById(R.id.calculator_input11_common2_textview);
            calculator_input11_common3_textview= itemView.findViewById(R.id.calculator_input11_common3_textview);
            calculator_input11_common4_textview= itemView.findViewById(R.id.calculator_input11_common4_textview);


            calculator_input12_layout = itemView.findViewById(R.id.calculator_input12_layout);
            calculator_input12_tip_textview = itemView.findViewById(R.id.calculator_input12_tip_textview);
            calculator_input12_gongjijinloan_price_edt = itemView.findViewById(R.id.calculator_input12_gongjijinloan_price_edt);
            calculator_input12_common1_textview= itemView.findViewById(R.id.calculator_input12_common1_textview);
            calculator_input12_common2_textview= itemView.findViewById(R.id.calculator_input12_common2_textview);
            calculator_input12_common3_textview= itemView.findViewById(R.id.calculator_input12_common3_textview);

            calculator_input13_layout = itemView.findViewById(R.id.calculator_input13_layout);
            calculator_input13_gongjijinloan_benefitrate_edt = itemView.findViewById(R.id.calculator_input13_gongjijinloan_benefitrate_edt);
            calculator_input13_common1_textview= itemView.findViewById(R.id.calculator_input13_common1_textview);
            calculator_input13_common2_textview= itemView.findViewById(R.id.calculator_input13_common2_textview);
            calculator_input13_common3_textview= itemView.findViewById(R.id.calculator_input13_common3_textview);


            calculator_input14_layout = itemView.findViewById(R.id.calculator_input14_layout);
            calculator_input14_gongjijinloan_yearcount_edt = itemView.findViewById(R.id.calculator_input14_gongjijinloan_yearcount_edt);
            calculator_input14_common1_textview= itemView.findViewById(R.id.calculator_input14_common1_textview);
            calculator_input14_common2_textview= itemView.findViewById(R.id.calculator_input14_common2_textview);
            calculator_input14_common3_textview= itemView.findViewById(R.id.calculator_input14_common3_textview);

            calculator_input15_layout = itemView.findViewById(R.id.calculator_input15_layout);
            calculator_input15_common1_textview = itemView.findViewById(R.id.calculator_input15_common1_textview);
            calculator_input15_group= itemView.findViewById(R.id.calculator_input15_group);
            calculator_input15_group_btn1 = itemView.findViewById(R.id.calculator_input15_group_btn1);
            calculator_input15_group_btn2 = itemView.findViewById(R.id.calculator_input15_group_btn2);


            calculator_input16_layout = itemView.findViewById(R.id.calculator_input16_layout);
            calculator_input16_shangyeloan_percent_inallloan_edt = itemView.findViewById(R.id.calculator_input16_shangyeloan_percent_inallloan_edt);
            calculator_input16_tip_textview = itemView.findViewById(R.id.calculator_input16_tip_textview);
          calculator_input16_common1_textview = itemView.findViewById(R.id.calculator_input16_common1_textview);
         calculator_input16_common2_textview = itemView.findViewById(R.id.calculator_input16_common2_textview);
         calculator_input16_common3_textview = itemView.findViewById(R.id.calculator_input16_common3_textview);
          calculator_input16_common4_textview = itemView.findViewById(R.id.calculator_input16_common4_textview);

            calculator_input17_layout = itemView.findViewById(R.id.calculator_input17_layout);
            calculator_input17_shangyeloan_price_edt = itemView.findViewById(R.id.calculator_input17_shangyeloan_price_edt);
            calculator_input17_tip_textview = itemView.findViewById(R.id.calculator_input17_tip_textview);
             calculator_input17_common1_textview= itemView.findViewById(R.id.calculator_input17_common1_textview);
             calculator_input17_common2_textview= itemView.findViewById(R.id.calculator_input17_common2_textview);
             calculator_input17_common3_textview= itemView.findViewById(R.id.calculator_input17_common3_textview);
            calculator_input17_common4_textview= itemView.findViewById(R.id.calculator_input17_common4_textview);
            calculator_input17_tip2_textview= itemView.findViewById(R.id.calculator_input17_tip2_textview);

            calculator_input18_shangye_benefitrate_edt = itemView.findViewById(R.id.calculator_input18_shangye_benefitrate_edt);
            calculator_input18_layout = itemView.findViewById(R.id.calculator_input18_layout);
            calculator_input18_common1_textview= itemView.findViewById(R.id.calculator_input18_common1_textview);
            calculator_input18_common2_textview= itemView.findViewById(R.id.calculator_input18_common2_textview);
            calculator_input18_common3_textview= itemView.findViewById(R.id.calculator_input18_common3_textview);



            calculator_input19_layout = itemView.findViewById(R.id.calculator_input19_layout);
            calculator_input19_shangyeloan_yearcount_edt = itemView.findViewById(R.id.calculator_input19_shangyeloan_yearcount_edt);
             calculator_input19_common1_textview = itemView.findViewById(R.id.calculator_input19_common1_textview);
             calculator_input19_common2_textview = itemView.findViewById(R.id.calculator_input19_common2_textview);
             calculator_input19_common3_textview = itemView.findViewById(R.id.calculator_input19_common3_textview);


            calculator_input20_layout = itemView.findViewById(R.id.calculator_input20_layout);
            calculator_input20_group_btn1 = itemView.findViewById(R.id.calculator_input20_group_btn1);
            calculator_input20_group_btn2 = itemView.findViewById(R.id.calculator_input20_group_btn2);
            calculator_input20_common1_textview= itemView.findViewById(R.id.calculator_input20_common1_textview);
             calculator_input20_group= itemView.findViewById(R.id.calculator_input20_group);
            // ___________________input_area_begin ___________________

            begin_calcul_textview = itemView.findViewById(R.id.begin_calcul_textview);

            // ___________________result_area_begin ___________________
            calcul_line1_result1_percent_textview =itemView.findViewById(R.id.calcul_line1_result1_pecent_textview  				);
            calcul_line1_result2_price_textview=itemView.findViewById(R.id.calcul_line1_result2_price_textview     	 			);
            calcul_line2_result1_percent_textview =itemView.findViewById(R.id.calcul_line2_result1_pecent_textview   				);
            calcul_line2_result2_price_textview=itemView.findViewById(R.id.calcul_line2_result2_price_textview       			);
            calcul_line3_result1_percent_textview=itemView.findViewById(R.id.calcul_line3_result1_percent_textview   			);
            calcul_line3_result2_price_textview=itemView.findViewById(R.id.calcul_line3_result2_price_textview      				);
            calcul_line4_result1_percent_textview =itemView.findViewById(R.id.calcul_line4_result1_pecent_textview     			);
            calcul_line4_result2_price_textview=itemView.findViewById(R.id.calcul_line4_result2_price_textview					);
            calcul_line5_result1_percent_textview =itemView.findViewById(R.id.calcul_line5_result1_pecent_textview					);
            calcul_line5_result2_price_textview=itemView.findViewById(R.id.calcul_line5_result2_price_textview					);
            calcul_line6_result1_percent_textview =itemView.findViewById(R.id.calcul_line6_result1_pecent_textview					);
            calcul_line6_result2_price_textview=itemView.findViewById(R.id.calcul_line6_result2_price_textview					);
            calcul_line_zong1_result1_price_textview =itemView.findViewById(R.id.calcul_line_zong1_result1_price_textview			);
            calcul_line_zong2_result1_price_textview =itemView.findViewById(R.id.calcul_line_zong2_result1_price_textview			);

            calcul_result_line7_layout = itemView.findViewById(R.id.calcul_result_line7_layout						);
            calcul_line7_result1_price_textview=itemView.findViewById(R.id.calcul_line7_result1_price_textview					);
            calcul_line7_result2_rate_textview=itemView.findViewById(R.id.calcul_line7_result2_rate_textview						);


            calcul_result_line8_layout =itemView.findViewById(R.id.calcul_result_line8_layout						);
            calcul_line8_result1_price_textview=itemView.findViewById(R.id.calcul_line8_result1_price_textview					);
            calcul_line8_result2_rate_textview=itemView.findViewById(R.id.calcul_line8_result2_rate_textview						);
            calcul_line9_result1_price_textview=itemView.findViewById(R.id.calcul_line9_result1_price_textview					);
            calcul_line10_result1_price_textview=itemView.findViewById(R.id.calcul_line10_result1_price_textview					);
            calcul_line11_result1_price_textview=itemView.findViewById(R.id.calcul_line11_result1_price_textview					);

            calcul_result_line12_layout =itemView.findViewById(R.id.calcul_result_line12_layout						);
            calcul_line12_result1_price_textview=itemView.findViewById(R.id.calcul_line12_result1_price_textview					);
            calcul_line12_result2_monthtip_textview=itemView.findViewById(R.id.calcul_line12_result2_monthtip_textview			);
            calcul_line12_result3_moneytip_textview=itemView.findViewById(R.id.calcul_line12_result3_moneytip_textview			);

            calcul_result_line13_layout =itemView.findViewById(R.id.calcul_result_line13_layout			);
            calcul_line13_result1_price_textview=itemView.findViewById(R.id.calcul_line13_result1_price_textview					);
            calcul_line13_result2_monthtip_textview=itemView.findViewById(R.id.calcul_line13_result2_monthtip_textview			);
             calcul_line13_result3_moneytip_textview=itemView.findViewById(R.id.calcul_line13_result3_moneytip_textview	);
            calcul_line_zong3_result1_price_textview=itemView.findViewById(R.id.calcul_line_zong3_result1_price_textview			);
            calcul_line_zong3_result2_monthtip_textview=itemView.findViewById(R.id.calcul_line_zong3_result2_monthtip_textview	);
            calcul_line_zong3_result3_moneytip_textview=itemView.findViewById(R.id.calcul_line_zong3_result3_moneytip_textview	);

            calcul_result_line14_layout =itemView.findViewById(R.id.calcul_result_line14_layout			);
            calcul_line14_result1_price_textview=itemView.findViewById(R.id.calcul_line14_result1_price_textview					);

            calcul_result_line15_layout =itemView.findViewById(R.id.calcul_result_line15_layout			);
            calcul_line15_result1_price_textview=itemView.findViewById(R.id.calcul_line15_result1_price_textview					);
            calcul_line_zong4_result1_price_textview=itemView.findViewById(R.id.calcul_line_zong4_result1_price_textview			);

            calcul_result_line16_layout =itemView.findViewById(R.id.calcul_result_line16_layout			);

            calcul_line16_result1_price_textview=itemView.findViewById(R.id.calcul_line16_result1_price_textview					);

            calcul_result_line17_layout =itemView.findViewById(R.id.calcul_result_line17_layout			);

            calcul_line17_result1_price_textview=itemView.findViewById(R.id.calcul_line17_result1_price_textview					);
            calcul_line_zong5_result1_price_textview=itemView.findViewById(R.id.calcul_line_zong5_result1_price_textview			);
            // ___________________result_area_end ___________________

        }
    }



}
