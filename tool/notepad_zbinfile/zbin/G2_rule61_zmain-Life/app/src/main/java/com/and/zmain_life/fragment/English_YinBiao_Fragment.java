package com.and.zmain_life.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.English_YinBiao_FaYinActivity;
import com.and.zmain_life.activity.English_YinBiao_ShowActivity;
import com.and.zmain_life.base.BaseFragment;
import com.and.zmain_life.bean.English_YinBiao_Bean;
import com.and.zmain_life.bean.English_YinBiao_GridAdapter;
import com.and.zmain_life.utils.English_YinBiao_StartAssetsAudio;
import com.and.zmain_life.view.Englisg_YinBiao_GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*import com.and.zvideo_and_dy.view.CommentDialog;
import com.and.zvideo_and_dy.view.ControllerView;
import com.and.zvideo_and_dy.view.FullScreenVideoView;
import com.and.zvideo_and_dy.view.LikeView;
import com.and.zvideo_and_dy.view.ShareDialog;*/


/*1.显示 github.zukgit.io 的 webview */


public class English_YinBiao_Fragment extends BaseFragment {


//    private Englisg_YinBiao_GridView grid_yuan_yin,grid_fu_yin;
    private List<English_YinBiao_Bean> list_yuan = new ArrayList<English_YinBiao_Bean>();
    private List<English_YinBiao_Bean> list_fu = new ArrayList<English_YinBiao_Bean>();
    private English_YinBiao_GridAdapter yuanGridAdapter;
    private English_YinBiao_GridAdapter fuGridAdapter;




     Context mContext;


    @BindView(R.id.id_biao_er)
    TextView biao_er;

    @BindView(R.id.id_grid_yuan_yin)
    Englisg_YinBiao_GridView grid_yuan_yin;

    @BindView(R.id.id_grid_fu_yin)
    Englisg_YinBiao_GridView       grid_fu_yin;



    @Override
    protected int setLayoutId() {
        return R.layout.english_yinbiao_layout;
    }

    public boolean isScreenOriatationPortrait() {
        return mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }


  void  initGridColumn(){

        if(!isScreenOriatationPortrait()){
            if(grid_yuan_yin != null){
                grid_yuan_yin.setNumColumns(14);
                grid_yuan_yin.requestLayout();
            }
            if(grid_fu_yin != null){
                grid_fu_yin.setNumColumns(14);
                grid_yuan_yin.requestLayout();
            }

        }else{
            if(grid_yuan_yin != null){
                grid_yuan_yin.setNumColumns(5);
                grid_yuan_yin.requestLayout();
            }
            if(grid_fu_yin != null){
                grid_fu_yin.setNumColumns(5);
                grid_yuan_yin.requestLayout();
            }


        }

    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initGridColumn();
    }

    @Override
    protected void init() {

        mContext = getContext();


        getYinBiaoList();

        init_YinBiao();
        initGridColumn();

    }



    public void biao_er_click(View v) {

        startActivity(new Intent(getActivity(), English_YinBiao_ShowActivity.class));
    }



    private void init_YinBiao() {
        yuanGridAdapter = new English_YinBiao_GridAdapter(list_yuan,getLayoutInflater());
        fuGridAdapter = new English_YinBiao_GridAdapter(list_fu,getLayoutInflater());

        grid_yuan_yin.setAdapter(yuanGridAdapter);
        grid_fu_yin.setAdapter(fuGridAdapter);


        biao_er.setOnClickListener(this::biao_er_click);
        grid_yuan_yin.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                English_YinBiao_Bean bean = yuanGridAdapter.getItem(position);
                English_YinBiao_StartAssetsAudio.getInstance().playAssetsAudio(mContext,bean.audio);
            }
        });
        grid_yuan_yin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                English_YinBiao_Bean bean = yuanGridAdapter.getItem(position);
                Intent intent = new Intent(mContext,English_YinBiao_FaYinActivity.class);
                intent.putExtra("audioName",bean.audio);
                intent.putExtra("name",bean.name);
                startActivity(intent);
                return true;
            }
        });


        grid_fu_yin.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                English_YinBiao_Bean bean = fuGridAdapter.getItem(position);
                English_YinBiao_StartAssetsAudio.getInstance().playAssetsAudio(mContext,bean.audio);
            }
        });
        grid_fu_yin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                English_YinBiao_Bean bean = fuGridAdapter.getItem(position);
                Intent intent = new Intent(mContext, English_YinBiao_FaYinActivity.class);
                intent.putExtra("audioName",bean.audio);
                intent.putExtra("name",bean.name);
                startActivity(intent);
                return true;
            }
        });

    }

    private void getYinBiaoList() {
        if(list_yuan != null){
            list_yuan.clear();
        }else{
            list_yuan = new ArrayList<English_YinBiao_Bean>();
        }

        if(list_fu != null){
            list_fu.clear();
        }else{
            list_fu = new ArrayList<English_YinBiao_Bean>();
        }

        //元音	单元音	前元音
        list_yuan.add(new English_YinBiao_Bean("/iː/","i-sound2"));
        list_yuan.add(new English_YinBiao_Bean("/i/","i-sound"));
        list_yuan.add(new English_YinBiao_Bean("/e/","e-sound"));
        list_yuan.add(new English_YinBiao_Bean("/æ/","an-sound"));

        //中元音
        list_yuan.add(new English_YinBiao_Bean("/ɜː/","er-sound"));
        list_yuan.add(new English_YinBiao_Bean("/ə/","e5E-sound"));
        list_yuan.add(new English_YinBiao_Bean("/ʌ/","5E-sound"));

        //后元音
        list_yuan.add(new English_YinBiao_Bean("/uː/","u-sound2"));
        list_yuan.add(new English_YinBiao_Bean("/ʊ/","u-sound"));
        list_yuan.add(new English_YinBiao_Bean("/ɔː/","o-sound2"));
        list_yuan.add(new English_YinBiao_Bean("/ɒ/","o-sound"));
        list_yuan.add(new English_YinBiao_Bean("/ɑː/","a-sound2"));

        //双元音	开合双元音
        list_yuan.add(new English_YinBiao_Bean("/ei/","ei"));
        list_yuan.add(new English_YinBiao_Bean("/ai/","ai"));
        list_yuan.add(new English_YinBiao_Bean("/ɔi/","oi"));
        list_yuan.add(new English_YinBiao_Bean("/aʊ/","ao"));
        list_yuan.add(new English_YinBiao_Bean("/əʊ/","eu"));

        //集中双元音
        list_yuan.add(new English_YinBiao_Bean("/iə/","ir"));
        list_yuan.add(new English_YinBiao_Bean("/eə/","er"));
        list_yuan.add(new English_YinBiao_Bean("/ʊə/","uer"));

// ------------------------------------------------------------------------------------------------
        //辅音	爆破音	清辅音
        list_fu.add(new English_YinBiao_Bean("/p/","p"));
        list_fu.add(new English_YinBiao_Bean("/t/","t"));
        list_fu.add(new English_YinBiao_Bean("/k/","k"));

        //浊辅音
        list_fu.add(new English_YinBiao_Bean("/b/","b"));
        list_fu.add(new English_YinBiao_Bean("/d/","d"));
        list_fu.add(new English_YinBiao_Bean("/ɡ/","g"));

        //摩擦音	清辅音
        list_fu.add(new English_YinBiao_Bean("/f/","f"));
        list_fu.add(new English_YinBiao_Bean("/s/","s"));
        list_fu.add(new English_YinBiao_Bean("/ʃ/","ss"));
        list_fu.add(new English_YinBiao_Bean("/θ/","si"));
        list_fu.add(new English_YinBiao_Bean("/h/","h"));

        //浊辅音
        list_fu.add(new English_YinBiao_Bean("/v/","v"));
        list_fu.add(new English_YinBiao_Bean("/z/","z"));
        list_fu.add(new English_YinBiao_Bean("/ʒ/","n3"));
        list_fu.add(new English_YinBiao_Bean("/ð/","qq"));
        list_fu.add(new English_YinBiao_Bean("/r/","r"));

        //破擦音	清辅音
        list_fu.add(new English_YinBiao_Bean("/tʃ/","tss"));
        list_fu.add(new English_YinBiao_Bean("/tr/","tr"));
        list_fu.add(new English_YinBiao_Bean("/ts/","ts"));

        //浊辅音
        list_fu.add(new English_YinBiao_Bean("/dʒ/","d3"));
        list_fu.add(new English_YinBiao_Bean("/dr/","dr"));
        list_fu.add(new English_YinBiao_Bean("/dz/","dz"));

        //鼻音	（浊辅音）
        list_fu.add(new English_YinBiao_Bean("/m/","m"));
        list_fu.add(new English_YinBiao_Bean("/n/","n"));
        list_fu.add(new English_YinBiao_Bean("/ŋ/","ng"));

        //舌则音	（浊辅音）
        list_fu.add(new English_YinBiao_Bean("/l/","l"));

        //半元音	（浊辅音）
        list_fu.add(new English_YinBiao_Bean("/j/","j"));
        list_fu.add(new English_YinBiao_Bean("/w/","w"));

    }



    @Override
    public void onResume() {
        super.onResume();
        android.util.Log.i("zukgit", " English_YinBiao_Fragment onResume ");
    }




    @Override
    public void onPause() {
        super.onPause();
        android.util.Log.i("zukgit", " English_YinBiao_Fragment onPause ");

//        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        android.util.Log.i("zukgit", " English_YinBiao_Fragment onStop ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.util.Log.i("zukgit", " English_YinBiao_Fragment onDestroy ");

    }

}
