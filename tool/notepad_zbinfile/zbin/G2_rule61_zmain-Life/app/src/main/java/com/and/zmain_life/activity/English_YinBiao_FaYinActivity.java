package com.and.zmain_life.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.and.zmain_life.R;
import com.and.zmain_life.base.BaseActivity;
import com.and.zmain_life.utils.English_YinBiao_StartAssetsAudio;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class English_YinBiao_FaYinActivity extends BaseActivity {
    private Context mContext;
    private String name,audioName;
    private LinearLayout layout_a,layout_b,layout_c;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        hideStatusBar();
        setFullScreen();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        hideStatusBar();
        setFullScreen();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.english_yinbiao_fayin_fragment;
    }

    @Override
    protected void init() {
        hideStatusBar();
        setFullScreen();
        mContext = this;

        name = getIntent().getStringExtra("name");
        Log.i("---zukgit---","name : " + name);
        audioName = getIntent().getStringExtra("audioName");
        Log.i("---zukgit---","audioName : " + audioName);
        String jsonData = English_YinBiao_StartAssetsAudio.getJson(mContext,audioName).trim().replace(" ", "");

        Log.i("---zukgit---","str : " + jsonData);

        layout_a = (LinearLayout)findViewById(R.id.id_layout_a);
        layout_b = (LinearLayout)findViewById(R.id.id_layout_b);
        layout_c = (LinearLayout)findViewById(R.id.id_layout_c);


        TextView title_view = (TextView)findViewById(R.id.id_title_view);
        title_view.setText("音标 "+name);

        findViewById(R.id.id_right_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                English_YinBiao_StartAssetsAudio.getInstance().playAssetsAudio(mContext,audioName);
            }
        });

        setData(jsonData);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.english_yinbiao_fayin_fragment);

    }



    private void setData(String jsonData) {
        try {
            if(!TextUtils.isEmpty(jsonData)){
                if(jsonData.startsWith("{")){
                    JSONObject jsonObject = new JSONObject(jsonData);
                    JSONArray jsonArrayA = jsonObject.getJSONArray("a");
                    JSONArray jsonArrayB = jsonObject.getJSONArray("b");
                    JSONArray jsonArrayC = jsonObject.getJSONArray("c");

                    //发音方法
                    for(int i = 0; i < jsonArrayA.length(); i++){
                        String str = jsonArrayA.getString(i);
                        TextView tva = new TextView(mContext);
                        tva.setPadding(3,3,3,3);
                        tva.setText(str);
                        layout_a.addView(tva);
                    }

                    //发音秘诀
                    for(int i = 0; i < jsonArrayB.length(); i++){
                        String str = jsonArrayB.getString(i);
                        TextView tvb = new TextView(mContext);
                        tvb.setPadding(3,3,3,3);
                        tvb.setText(str);
                        layout_b.addView(tvb);
                    }

                    //发音规则
                    for(int i = 0; i < jsonArrayC.length(); i++){
                        JSONObject json = jsonArrayC.getJSONObject(i);
                        String name = json.getString("name");
                        JSONArray values = json.getJSONArray("values");

                        LinearLayout.LayoutParams mLayoutParams0 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        LinearLayout layout0 = new LinearLayout(mContext);
                        layout0.setPadding(15,1,1,15);
                        layout0.setOrientation(LinearLayout.VERTICAL);
                        TextView tvc = new TextView(mContext);
                        tvc.setPadding(3,3,3,3);
                        tvc.setText(name);
                        layout_c.addView(tvc);

                        List<String> list = new ArrayList<String>();

                        for(int j = 0; j < values.length(); j++){
                            String value = values.getString(j);
                            list.add(value);
                            if(list.size() == 2 || values.length() == j + 1){
                                LinearLayout.LayoutParams mLayoutParams2 = new LinearLayout.LayoutParams(0,-2,1);
                                LinearLayout layout2 = new LinearLayout(mContext);
//                                layout2.setPadding(2,1,1,15);
                                layout2.setOrientation(LinearLayout.HORIZONTAL);
                                for (String s:list) {
                                    LinearLayout layout3 = new LinearLayout(mContext);
                                    layout3.setOrientation(LinearLayout.HORIZONTAL);
                                    layout3.setPadding(2,2,2,2);
                                    layout3.setBackgroundResource(R.drawable.btn_a);
                                    layout3.setOnClickListener(new MyOnClickListener());
                                    String[] ss = s.split("-");

                                    if(ss.length >= 3){
                                        layout3.setTag(ss[0]);
                                        TextView tvc01 = new TextView(mContext);
                                        tvc01.setText(ss[1]);

                                        TextView tvc02 = new TextView(mContext);
                                        tvc02.setTextColor(Color.parseColor("#FF0000"));
                                        tvc02.setText(ss[2]);//
                                        TextView tvc03 = new TextView(mContext);
                                        tvc03.setText(ss[3]);
                                        tvc03.setGravity(Gravity.LEFT);
                                        layout3.addView(tvc01);
                                        layout3.addView(tvc02);
                                        layout3.addView(tvc03);
                                    }
                                    layout2.addView(layout3,mLayoutParams2);
                                }
                                layout0.addView(layout2,mLayoutParams0);
                                list.clear();
                            }
                        }
                        layout_c.addView(layout0);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LinearLayout layout = (LinearLayout)v;
            String str = (String)layout.getTag();
            Log.i("---zukgit---","点击：" + str);
            String url = "http://dict.youdao.com/dictvoice?audio="+str+"&type=1";

            English_YinBiao_StartAssetsAudio.getInstance().playHttpAudio(mContext,url);
        }
    }
}