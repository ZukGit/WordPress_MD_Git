package com.and.zmain_life.adapter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.bean.StockSimulate_BankBean;
import com.and.zmain_life.stock_node.Stock_NodeImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 */

public class HardwareFunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;   // GPS   BT WIFI  NFC  描述
    private int ITEM_CONTENT = 2;  //  里面的详细的内容

    static  String TAG = "HardwareFunctionAdapter";



    public  int itemCount = 0 ;

    DecimalFormat decimalFormat  ;
    // = new DecimalFormat("##,##0.00");

    public HardwareFunctionAdapter mHardwareFunctionAdapter;
    public Context mContext;

    public Dialog mTitleBuyStockDialogView;

    // 内容页面 点击 买入 股票的 弹框
    public Dialog mSellStockDialogView;



    public HardwareFunctionAdapter(Context curCotext){
        mContext = curCotext;
        // = new DecimalFormat("##,##0.00");

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

