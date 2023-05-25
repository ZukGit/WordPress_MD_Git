package com.and.zmain_life.base;

import static android.R.layout.simple_dropdown_item_1line;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.and.zmain_life.activity.VideoLayoutActivity;
import com.and.zmain_life.bean.Pause_Base_Event;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import com.and.zmain_life.R;

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    public  int curPageIndex;
    public File[] mBaseFileArr;
    public Pause_Base_Event curPauseEvent;


    public void showMultiWindowsDialog(){

        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.videolayout_multiwindows_dialog, null);

        Spinner row_spiner = (Spinner)view.findViewById(R.id.multi_win_line1_videolayout_spinner_1);
        Spinner colnum_spiner = (Spinner)view.findViewById(R.id.multi_win_line1_videolayout_spinner_2);
        Spinner same_spiner = (Spinner)view.findViewById(R.id.multi_win_line1_videolayout_spinner_3);
        TextView open_videoLayout_text =  (TextView)view.findViewById(R.id.multi_win_line1_open_textview);

        // row_spiner ----Begin
        ArrayList row_spiner_list = new ArrayList<String>();
        row_spiner_list.add("1");
        row_spiner_list.add("2");
        row_spiner_list.add("3");
        row_spiner_list.add("4");
        ArrayAdapter row_spiner_ArrayAdapter = new ArrayAdapter(getContext(), simple_dropdown_item_1line, row_spiner_list);
        row_spiner.setAdapter(row_spiner_ArrayAdapter);
        row_spiner.setSelection(1);
        // row_spiner----End

        // colnum_spiner ----Begin
        ArrayList  colnum_spiner_list = new ArrayList<String>();
        colnum_spiner_list.add("1");
        colnum_spiner_list.add("2");
        colnum_spiner_list.add("3");
        colnum_spiner_list.add("4");
        ArrayAdapter colnum_spiner_ArrayAdapter = new ArrayAdapter(getContext(), simple_dropdown_item_1line, colnum_spiner_list);
        colnum_spiner.setAdapter(colnum_spiner_ArrayAdapter);
        colnum_spiner.setSelection(1);
        // colnum_spiner----End

        // same_spiner ----Begin
        ArrayList  same_spiner_list = new ArrayList<String>();
        same_spiner_list.add("false");
        same_spiner_list.add("true");
        ArrayAdapter same_spiner_ArrayAdapter = new ArrayAdapter(getContext(), simple_dropdown_item_1line, same_spiner_list);
        same_spiner.setAdapter(same_spiner_ArrayAdapter);
        same_spiner.setSelection(0);
        // same_spiner----End

        Dialog multi_win__Dialog =   builder.create();
        open_videoLayout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String row_spiner_str = row_spiner.getSelectedItem().toString();
                String colnum_spiner_str = colnum_spiner.getSelectedItem().toString();
                String same_spiner_str = same_spiner.getSelectedItem().toString();

                System.out.println("BaseActivity: "+"row_spiner_str="+row_spiner_str+"  colnum_spiner_str="+colnum_spiner_str+"  same_spiner_str="+same_spiner_str);
                multi_win__Dialog.dismiss();


                Intent intent = new Intent(getContext(), VideoLayoutActivity.class);
                intent.putExtra("row",row_spiner_str);
                intent.putExtra("column",colnum_spiner_str);
                intent.putExtra("issame",same_spiner_str);
                intent.putExtra("srcfragmentindex",curPageIndex+"");
                startActivity(intent);


            }
        });


        multi_win__Dialog.show();
        multi_win__Dialog.getWindow().setContentView(view);


    }


    public  int allPageCount;

    public  String getPageTip(){
        return "["+curPageIndex+"_"+allPageCount+"]";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutId(), container, false);
        ButterKnife.bind(this, rootView);

        init();
        return rootView;
    }

    protected abstract int setLayoutId();

    protected abstract void init();
}
