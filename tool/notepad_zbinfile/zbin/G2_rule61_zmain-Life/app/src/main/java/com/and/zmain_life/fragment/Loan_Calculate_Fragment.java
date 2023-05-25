package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.APP_In_BackGround_Event;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.loan_calculate.LoanUtilAdapter;
import com.and.zmain_life.txt_edit.TxtAdapter;
import com.and.zmain_life.utils.RxBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import rx.functions.Action1;

// 房贷计算 Fragment
public class Loan_Calculate_Fragment extends BaseFragment {
    @BindView(R.id.show_cal_recycleview)
    RecyclerView CalcultorRecyclerView;

    @BindView(R.id.clear_cal_button)
    Button clearCultorButton;


    //    insert_zand_btn
    @BindView(R.id.insert_cal_btn)
    Button inserCalcultorBtn;

    LoanUtilAdapter mLoanAdapter;


    @BindView(R.id.cal_spinner)
    Spinner calSpinner;






    @Override
    protected int setLayoutId() {
        return R.layout.loan_calculate_layout;
    }




    @Override
    protected void init() {
//        inserCalcultorBtn.setOnClickListener(this::insert_edittext_toFile);
//        clearCultorButton.setOnClickListener(this::clear_txt_File);
//
//        inserCalcultorBtn.setOnClickListener(this::inset_zand_to_edittext);

        // 中间的数据 contentList 使用来保存数据的
        mLoanAdapter = new LoanUtilAdapter(getActivity(),DataHolder.sdcard_zmain_loan_contentList,this);
        CalcultorRecyclerView.setAdapter(mLoanAdapter);
        InitCalcultorSpinner();


    }

    void InitCalcultorSpinner(){

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {  // 恢复页面时执行
        super.onViewCreated(view, savedInstanceState);

        inserCalcultorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击生成计算器Cal",Toast.LENGTH_SHORT).show();
            }
        });

        inserCalcultorBtn.setOnClickListener(this::showInsertCalculDialog);

        clearCultorButton.setOnClickListener(this::showClearAllCalculDialog);
    }


    private void showClearAllCalculDialog(View clickview) {

        // 删除  /sdcard/zmain/loan.txt  的内容
        // 删除  loanAdapter.list 里的 内容 Data.sdcard_1_txt_contentList  清空

        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());


        builder.setMessage("清空当前所有计算器!");
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataHolder.sdcard_zmain_loan_contentList.clear();
                mLoanAdapter.setList(DataHolder.sdcard_zmain_loan_contentList);
                mLoanAdapter.notifyDataSetChanged();
                DataHolder.writeContentToFile(DataHolder.sdcard_main_loan_file,DataHolder.sdcard_zmain_loan_contentList);

            }
        });

        final Dialog dialog= builder.create();
        dialog.show();
    }


    static String getTimeStamp_yyMMdd_HHmmss() {

        SimpleDateFormat df = new SimpleDateFormat("yyMMdd-HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    private void showInsertCalculDialog(View clickview) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.loan_addcal_dialog_edittext_layout, null);
        TextView cancel =view.findViewById(R.id.loan_insert_cal_dialog_cancel_text);
        TextView sure =view.findViewById(R.id.loan_insert_cal_dialog_sure_text);

        final EditText calcul_name_edit =view.findViewById(R.id.loan_cal_name_edittext);

        // 用户名称

        final Dialog dialog= builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calcul_name = calcul_name_edit.getText().toString();

                if(calcul_name == null || "".equals(calcul_name.trim())){
                    calcul_name = getTimeStamp_yyMMdd_HHmmss();

                } else {
                    calcul_name = calcul_name.replace("_","-");
                }





                    String one_line_insert =calcul_name+"_"+  // 0
                            "true_true_true_true"+"_"    // 1-4
                            +"100_1000000_3000000_2_35"+"_"+ // 5-9
                            "0"+"_" +   // 10
                            "65_0_3.25_15_true"+"_"+    // 11-15
                            "0_0_4.9_30_true";    // 16-20
                print("one_line_insert = "+ one_line_insert);


                DataHolder.sdcard_zmain_loan_contentList.add(0 , one_line_insert);
                mLoanAdapter.setList(DataHolder.sdcard_zmain_loan_contentList);
                DataHolder.writeContentToFile(DataHolder.sdcard_main_loan_file,DataHolder.sdcard_zmain_loan_contentList);
                mLoanAdapter.notifyDataSetChanged();


                dialog.dismiss();
            }
        });


    }

    void print(String line){

     android.util.Log.i("ZHomeDownPay",line);

    }
    @Override
    public void onResume() {
        super.onResume();



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }







    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


}
