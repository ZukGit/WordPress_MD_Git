package com.and.zmain_life.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.MainActivity;
import com.and.zmain_life.adapter.HardwareFunctionAdapter;
import com.and.zmain_life.adapter.StockSimulateTradeAdapter;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.Pause_Hardware_Function_Event;
import com.and.zmain_life.bean.Pause_Stock_Simulate_Event;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.utils.RxBus;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import rx.functions.Action1;


public class Hardware_Function_Fragment extends BaseFragment {
    @BindView(R.id.hardware_function_rv)
    RecyclerView mHardwareFunctionRecyclerView;


    HardwareFunctionAdapter mHardwareFunctionAdapter;


    @BindView(R.id.hardware_function_title_txt)
    TextView mHardWareFunctionTitle;




    @Override
    protected int setLayoutId() {
        return R.layout.hardware_function_main_layout;
    }






    @Override
    protected void init() {

        LinearLayoutManager mLinearManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);






        RxBus.getDefault().toObservable(Pause_Hardware_Function_Event.class).subscribe((Action1<Pause_Hardware_Function_Event>) event -> {

            if (event.isPlayOrPause()) { //  进入
            } else {   // 离开  应该是这里了
         }

        });













    }








    @Override
    public void onResume() {
        super.onResume();

        if (MainActivity.curMainPage == 0 && MainFragment.curPage == curPageIndex) {

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("clicpxxx","setUserVisibleHint  SimulateFragment ");
            if(mHardwareFunctionAdapter != null) {

            }
        } else {
            //相当于Fragment的onPause
            if(mHardwareFunctionAdapter != null){

            }

        }
    }







    @Override
    public void onPause() {
        super.onPause();

//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }




    static String getTimeStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }


    static String getTimeStamp_YYYY_MM_DD() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
        String date = df.format(new Date());
        return date;
    }







}
