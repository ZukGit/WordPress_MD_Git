package com.and.zmain_life.pure_node;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;

import java.util.List;

import utils.ZUtil;

public class Pure_MultiLevelPickerWindow<T extends Pure_Node> extends PopupWindow {

    public RecyclerView rv1, rv2, rv3;
    int mBGColorId;



    static int CurViewPosition;

    private int selectedLevel = -1;
    private T selectedItem = null;


    public Pure_MultiLevelItemAdapter<T> mAdapter1, mAdapter2, mAdapter3;

    /**
     * 储存已选择的数据
     */
    private long[] storage = new long[]{-1L/* 全部 */, -1L, -1L};


    public void showAdapterNode3StaticInfo(String tag) {
        if(mAdapter3 != null){
            mAdapter3.showStaticProp(tag);
        }

    }








    public Pure_MultiLevelPickerWindow(Context context , int colorId) {
        mBGColorId = colorId;
        init(context);
    }

    private View mRootView;
    Context mcontext;








    private void init(final Context context) {
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______init() Begin════════════════════════════");

        mRootView = LayoutInflater.from(context).inflate(
                R.layout.mlp_window_picker, null);
        mcontext = context;
        buildView(mRootView);
        Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点A ");
        this.setOnDismissListener(() -> {
            if (mListener != null) {
                mListener.onDismiss();
            }
        });

        this.setContentView(mRootView);
        this.setWidth(context.getResources().getDisplayMetrics().widthPixels);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点B ");

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点C ");

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(android.R.color.transparent));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismissListener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        Log.i("zukgit", "【MultiLevelPickerWindow__init()】_____起点D ");

        this.setAnimationStyle(R.style.popupAnimation);
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______init() End════════════════════════════");

    }



    public void showLastNode3Impl(){
        if(mAdapter3 != null){
            mAdapter3.notifyDataSetChanged();
            mAdapter3.screenToLastClickNode3Position();
            Log.i("zukgit", "mAdapter3.notifyDataSetChanged()   mAdapter3.last_click_level3_ID = "+mAdapter3.last_click_level3_ID);

        }

    }



    public void playScreenOffMusic(){
        if(mAdapter3 != null){

        }

    }


    public void   setCurrentClickNode3Id( int node3_id ){
        if(mAdapter3 != null){
                mAdapter3.last_click_level3_ID =node3_id;
        }
    }



    public int   getCurrentClickNode3Id(){
        if(mAdapter3 != null){
        return    mAdapter3.last_click_level3_ID;
        }
        return -100;
    }







    private void buildView(View view) {
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______buildView() Begin════════════════════════════");

        rv1 = view.findViewById(R.id.rv_1);
        rv2 = view.findViewById(R.id.rv_2);
        rv3 = view.findViewById(R.id.rv_3);

        Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点1 ");
/*        rv1.setBackgroundColor(mBGColorId);
        rv2.setBackgroundColor(mBGColorId);
        rv3.setBackgroundColor(mBGColorId);*/
        mAdapter1 = new Pure_MultiLevelItemAdapter<>(null, 0,mcontext,this);
        rv1.setAdapter(mAdapter1);
        Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点2 ");

        mAdapter1.setOnSelectListener((parent, selectedChild) -> {

            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 0;
            storage[0] = selectedChild.id();
            storage[1] = -1;
            storage[2] = -1;


            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter1.setNewData((T) parent);

            if (selectedChild.id() != mDefaultRootId) {//是全部的话 后面2级不展示啦
                selectedChild.setSelectedChild(-1);
                //noinspection unchecked
                mAdapter2.setNewData((T) selectedChild);
            } else {
                mAdapter2.setNewData(null);
            }
            mAdapter3.setNewData(null);
        });

        mAdapter2 = new Pure_MultiLevelItemAdapter<>(null, 1,mcontext,this);
        rv2.setAdapter(mAdapter2);
        Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点3 ");

        mAdapter2.setOnSelectListener((parent, selectedChild) -> {
            // 1 数据记录工作
            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 1;
            storage[1] = selectedChild.id();
            storage[2] = -1;

            // 2 刷新父节点
            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter2.setNewData((T) parent);

            // 3 展示子树
            selectedChild.setSelectedChild(-1);
            //noinspection unchecked
            mAdapter3.setNewData((T) selectedChild);
        });

        mAdapter3 = new  Pure_MultiLevelItemAdapter<>(null, 2,mcontext,this);
        rv3.setAdapter(mAdapter3);
        Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点4 ");

        mAdapter3.setOnSelectListener((parent, selectedChild) -> {
            // 1 数据记录工作
            //noinspection unchecked
            selectedItem = (T) selectedChild;
            selectedLevel = 2;
            storage[2] = selectedChild.id();

            // 2 刷新父节点
            parent.setSelectedChild(selectedChild.id());
            //noinspection unchecked
            mAdapter3.setNewData((T) parent);
        });
        Log.i("zukgit", "【MultiLevelPickerWindow__buildView()】_____起点5 ");


/*        view.findViewById(R.id.btnclose).setOnClickListener(v -> dismiss());
        view.findViewById(R.id.btndo).setOnClickListener(v -> {
            callbackSelected(false);
            MultiLevelPickerWindow.this.dismiss();
        });*/
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______buildView() End════════════════════════════");

    }

    /**
     * 更新节点数据, 并采用降级策略
     * 降级策略:
     * 被选择级别节点被删除则选中它的上一级选中节点, 一级节点丢失, 则选中"全部"(即id为0的那项)
     * 比如:
     * 1 被选择的三级节点被删除(或三级菜单丢失),选中二级菜单
     * 2 被选择的二级节点被删除(或二级菜单丢失),选中一级菜单
     * 2 被选择的一级节点被删除(或二级菜单丢失),选中一级菜单的"全部"(要是全部节点也没有? 就恢复无选择状态)
     *
     * @return 是否发生节点变更(节点丢失, backup策略启动)
     */
    public boolean updateData(T t) {
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() Begin════════════════════════════");

        Log.i("zukgit","   selectedLevel3  A20 =");
        boolean isDownGraded = false;
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点1 ");

        if (t == null) {
            mAdapter1.setNewData(null);
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A18 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点2 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_1 ════════════════════════════");

            return false;
        }
        /* ***************** 1级 ***************** */
        // 1级数据监测与校准
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点3 ");

        if (storage[0] < 0) {
            // 第一次进来 或 没选择过
            mAdapter1.setNewData(t);
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A19 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点4 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_2 ════════════════════════════");

            return false;
        }
        // 从内存中读取上次选中的一级菜单ID
        t.setSelectedChild(storage[0]);
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点5 ");

        // 尝试获取上次选中的一级目录节点
        //noinspection unchecked
        @Nullable T selectedLevel1 = (T) t.getSelectedChild();
        //noinspection StatementWithEmptyBody
        if (selectedLevel1 != null) { // 找到了上次选中的节点
            // as normal. do nothing. 不用给selectedItem赋值
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点6 ");

        } else { // 没找到上次选中的一级节点
            isDownGraded = true;
            storage[0] = mDefaultRootId; // 切到"全部"
            t.setSelectedChild(storage[0]);
            // 重点: 当原来选择的一级目录被删除后, 选择"全部"
            //noinspection unchecked
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点7 ");

            T all = (T) t.getSelectedChild();
            if (all != null) { // 找到了全部
                selectedItem = all;
                selectedLevel = 0;
                callbackSelected(true);
                storage[0] = all.id();
                storage[1] = -1;
                storage[2] = -1;
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点8 ");

            } else { // 没找到全部, 降级到未选择状态
                selectedItem = null;
                selectedLevel = -1;
                callbackSelected(true);
                storage[0] = -1;
                storage[1] = -1;
                storage[2] = -1;
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点9 ");

            }
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点10 ");

        }
        mAdapter1.setNewData(t);
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点11 ");

        if (isDownGraded) {

            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A17 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点12 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_3 ════════════════════════════");

            return true;
        }
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点13 ");

        /* ***************** 2级 ***************** */
        //noinspection unchecked
        List<T> secondList = (List<T>) t.children();
        if (secondList == null) {
            //noinspection StatementWithEmptyBody
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点14 ");

            if (selectedLevel > 0) {// 说明之前选中的二或三级目录被删除了
                isDownGraded = true; // useless, but for good reading.
                selectedItem = selectedLevel1;
                selectedLevel = 0;
                callbackSelected(true);
                storage[0] = selectedItem.id();
                storage[1] = -1;
                storage[2] = -1;
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点15 ");

            } else {
                // 之前选中的二级目录没被删, 本来就是选的一级目录，都不需要通知外界
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点15 ");

            }
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A16 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点16 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_4 ════════════════════════════");

            return isDownGraded;
        }
        if (storage[1] < 0) { // 表示本来就没选择二级菜单
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A15 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点17 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_5 ════════════════════════════");

            return false;
        }
        //noinspection UnnecessaryLocalVariable
        T second = selectedLevel1; // 二级菜单的父节点
        second.setSelectedChild(storage[1]);
        //noinspection unchecked
        T selectedLevel2 = (T) second.getSelectedChild(); // 尝试获取上次选择的二级菜单节点
        //noinspection StatementWithEmptyBody
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点18 ");

        if (selectedLevel2 != null) {
            // 之前选中的二级目录没被删, 且找到了.(有可能新增了菜单项, 且index也变了)
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点19 ");

        } else {
            // 之前的二级目录被删除
            isDownGraded = true; // useless, but for good reading.
            selectedItem = selectedLevel1;
            selectedLevel = 0;
            callbackSelected(true);
            storage[0] = selectedItem.id();
            storage[1] = -1;
            storage[2] = -1;
            mAdapter2.setNewData(null);
            mAdapter3.setNewData(null);
            Log.i("zukgit","   selectedLevel3  A14 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点20 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_6 ════════════════════════════");

            return true;
        }
        mAdapter2.setNewData(second);
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点21 ");

        /* ***************** 3级 ***************** */
        //noinspection unchecked
        List<T> thirdList = (List<T>) second.children();
        if (thirdList == null) {
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点22 ");

            //noinspection StatementWithEmptyBody
            if (selectedLevel > 1) {// 说明之前选中的三级目录被删除了
                isDownGraded = true; // useless, but for good reading.
                selectedItem = selectedLevel2;
                selectedLevel = 1;
                callbackSelected(true);
                storage[1] = selectedItem.id();
                storage[2] = -1;
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点23 ");

            } else {
                Log.i("zukgit","   selectedLevel3  A13 =");
                Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点24 ");

                // 说明本来就没来选三级菜单
            }
            Log.i("zukgit","   selectedLevel3  A12 =");
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点25 ");

            mAdapter3.setNewData(null);

            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_7 ════════════════════════════");

            return isDownGraded;
        }
        Log.i("zukgit","   selectedLevel3  A11 =");
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点26 ");

        if (storage[2] < 0) { // 表示本来就没选择三级菜单
            mAdapter3.setNewData(null);
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点27 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_8 ════════════════════════════");

            return false;
        }
        //noinspection UnnecessaryLocalVariable
        T third = selectedLevel2; // 三级菜单的父节点
        // 其实到了这里用selectedItem/selectedLevel和storage[2]已经没有区别
        third.setSelectedChild(storage[2]);
        //noinspection unchecked
        T selectedLevel3 = (T) third.getSelectedChild();// 尝试获取上次选择的三级菜单节点
        //noinspection StatementWithEmptyBody
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点28 ");

        if (selectedLevel3 != null) {
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点29 selectedLevel3 ="+selectedLevel3.text());

            mAdapter3.setNewData(third);
            // 之前选中的三级目录没被删, 且找到了.(有可能新增了菜单项, 且index也变了)
            Log.i("zukgit","   selectedLevel3 ="+selectedLevel3.text());
        } else {
            // 之前选择的三级菜单被删除了
            isDownGraded = true; // useless, but for good reading.
            selectedItem = selectedLevel2;
            selectedLevel = 1;
            callbackSelected(true);
            storage[1] = selectedItem.id();
            storage[2] = -1;
            mAdapter3.setNewData(null);
            Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点30 ");
            Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_9 ════════════════════════════");

            return true;
        }
        Log.i("zukgit","  selectedLevel3  mAdapter3.setNewData(third) =");
        Log.i("zukgit", "【MultiLevelPickerWindow__updateData()】_____起点31 ");
        Log.i("zukgit", "════════════════════════════ MultiLevelPickerWindow_______updateData() End_OVer════════════════════════════");

        return false;
    }

    public void callbackSelected_Text(T selectedItem) {
        if (mListener != null) {
            //noinspection unchecked
            Log.i("zzzz","CC 回调方法  selectedLevel3 ！！！selectedItem.level()=  "+selectedItem.level());
//            mListener.onSelect(3, selectedItem);
            mListener.onSelect(selectedItem.level(), selectedItem,false);
        }

    }


    public void callbackSelected(boolean isDownGraded) {
        if (isDownGraded) {
            updateSelection();
            if (mListener != null) {
                //noinspection unchecked
                mListener.onDownGraded(selectedLevel, selectedItem);
            }
            if (mListener != null) {
                //noinspection unchecked
                Log.i("zzzz","AA 回调方法  selectedLevel3 ！！！");
                mListener.onSelect(selectedLevel, selectedItem,false);
            }
            return;
        }
        if (mListener != null) {
            //noinspection unchecked
            Log.i("zzzz","BB 回调方法  selectedLevel3 ！！！");
            mListener.onSelect(selectedLevel, selectedItem,false);
        }
    }

    /**
     * 尝试刷新selectedLevel和selectedItem
     * (其实可以把这部分工作放在updateData中, 但逻辑过于臃肿)
     */
    private void updateSelection() {
        // 更新数据以确保selectedItem的其他值(除id以外的值变更)变更,比如修改了名字, 数量变化等
        T root = mAdapter1.getTree();
        if (root == null) return;// never occur
        if (storage[0] < 0) return; // never occur
        root.setSelectedChild(storage[0]);
        //noinspection unchecked
        T selectedLevel1 = (T) root.getSelectedChild();// 获取被选择的一级节点
        if (selectedLevel1 == null) {
            // 选择了一级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略
            Log.i("zzzz","A_1 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的一级节点
        if (storage[1] < 0) { // 说明只选了一级节点
            selectedLevel = 0;
            selectedItem = selectedLevel1;
            // 下面三行代码其实可以不用执行, how to say, insurance.
            storage[0] = selectedLevel1.id();
            storage[1] = -1;
            storage[2] = -1;
            Log.i("zzzz","A_2 updateSelection  selectedLevel3 ！！！");

            return;
        }
        selectedLevel1.setSelectedChild(storage[1]);
        // 代码执行到这句注释时, 说明选择了不止一级菜单(二级、三级 or more)
        //noinspection unchecked
        T selectedLevel2 = (T) selectedLevel1.getSelectedChild();// 获取被选择的二级节点
        if (selectedLevel2 == null) {
            // 选择了二级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略

            Log.i("zzzz","A_3 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的二级节点
        if (storage[2] < 0) { // 说明只选到了二级节点
            selectedLevel = 1;
            selectedItem = selectedLevel2;
            // 下面两行代码其实可以不用执行, how to say, insurance.
            storage[1] = selectedLevel2.id();
            storage[2] = -1;
            Log.i("zzzz","A_4 updateSelection  selectedLevel3 ！！！");

            return;
        }
        selectedLevel2.setSelectedChild(storage[2]);
        // 代码执行到这句注释时, 说明选择了不止二级菜单(三级 or more)
        //noinspection unchecked
        T selectedLevel3 = (T) selectedLevel2.getSelectedChild();// 获取被选择的三级节点
        if (selectedLevel3 == null) {
            // 选择了三级目录, 却没找到节点，可能吗? 不可能，updateData中已采用降级策略
            Log.i("zzzz","A_5 updateSelection  selectedLevel3 ！！！");

            return;
        }
        // 代码执行到这句注释时, 已取到了正确的选中的三级节点
        selectedLevel = 2;
        selectedItem = selectedLevel3;
        // 下面这行代码其实可以不用执行, how to say, insurance.
        storage[2] = selectedLevel3.id();
        Log.i("zzzz","A_6 updateSelection  selectedLevel3 ！！！");

    }

    public void update() {
        super.update();

        if(mAdapter1 != null){
            mAdapter1.notifyDataSetChanged();
        }

        if(mAdapter2 != null)
            mAdapter2.notifyDataSetChanged();

        if(mAdapter3 != null)
            mAdapter3.notifyDataSetChanged();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mAdapter1 != null)
        mAdapter1.recoverySelectedTextViewColor();

        if(mAdapter2 != null)
        mAdapter2.recoverySelectedTextViewColor();

        if(mAdapter3 != null)
        mAdapter3.recoverySelectedTextViewColor();
    }

    public void show(View view) {
        if (!this.isShowing()) {
//            PopupWindowCompat.showAsDropDown(this, view, 0, 0, Gravity.END);

//            this.setHeight(ScreenUtil.getScreenHeight(context));//屏幕的高



            int device_height = ZUtil.getRealScreenHeight();
            int device_width = ZUtil.getRealScreenWidth();
            int view_height = view.getHeight();
            int pop_height = device_height - view_height;

            Log.i("zukigt","  pop_height="+pop_height +" device_height = "+ device_height);
            this.setHeight(pop_height);//屏幕的高
            this.setWidth(device_width);//屏幕的宽

            this.setClippingEnabled(false);

            PopupWindowCompat.showAsDropDown(this, view, 0, 0, Gravity.FILL);

            if (mListener != null)
                mListener.onShow();
        } else {
            this.dismiss();
        }
    }


    public OnSelectListener mListener;

    public void setOnSelectListener(OnSelectListener l) {
        setOnSelectListener(0L, l);
    }

    private long mDefaultRootId = -1;

    public void setOnSelectListener(long defaultRootId, OnSelectListener l) {
        mListener = l;
        mDefaultRootId = defaultRootId;
    }

    public void onBackgroundChanged( ) {
        if (mListener != null) {
            mListener.onBackgroundColorChanged();
        }
    }

    public void onMusicTitlePlay( boolean isPlay ) {
        if (mListener != null) {
            mListener.onMusicPlay(isPlay);
        }
    }

    public void removeSelectListener() {
        if (mListener != null) {
            mListener = null;
        }
    }

    /**
     * 选择性实现方法用的适配器
     */
    public abstract class OnSelectAdapter implements OnSelectListener<T> {

        @Override
        public void onDownGraded(int selectLevel, @Nullable T data) {

        }

        @Override
        public void onShow() {

        }

        @Override
        public void onDismiss() {

        }
    }

    public interface OnSelectListener<T> {
        /**
         * @param selectLevel 被选择的菜单节点所处层级
         * @param data        数据
         */
        void onSelect(int selectLevel, @Nullable T data, boolean isOffScreenMediaPlayer);

        void onBackgroundColorChanged ();

        void onMusicPlay(boolean isPlay);
        /**
         * 当执行了降级策略时
         *
         * @param selectLevel -1 表示降级到了未选择状态
         * @param data        当selectLevel为-1时, data为null
         */
        void onDownGraded(int selectLevel, @Nullable T data);

        /**
         * 展示时
         */
        void onShow();

        /**
         * 消失时
         */
        void onDismiss();
    }
}
