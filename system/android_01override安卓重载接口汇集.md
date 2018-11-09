# A
## Activity extends ContextThemeWrapper 重载接口
<img src="https://upload-images.jianshu.io/upload_images/2244681-1532340d63d59dc6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/513">
```

   ■  protected void onCreate(Bundle savedInstanceState) {
    }

   ■  protected void onStart() {
    }

   ■  protected void onResume() {
    }

  ■   protected void onPause() {
    }

   ■  protected void onStop() {
    }

   ■  protected void onRestart() {
    }

   ■  protected void onDestroy() {
    }


前提:ActivityA已经启动过,处于当前应用的Activity堆栈中; 
当ActivityA的LaunchMode为SingleTop时，如果ActivityA在栈顶,且现在要再启动ActivityA，这时会调用onNewIntent()方法 
当ActivityA的LaunchMode为SingleInstance,SingleTask时,如果已经ActivityA已经在堆栈中，那么此时会调用onNewIntent()方法 
当ActivityA的LaunchMode为Standard时，由于每次启动ActivityA都是启动新的实例，和原来启动的没关系，所以不会调用原来ActivityA的onNewIntent方法
    protected void onNewIntent(Intent intent) {
    }


这个方法会在该activity被杀死之前调用 例如屏幕旋转 按下Home按键 关闭屏幕按电源  启动新的Activity ( 按返回键时不会执行) ,用来保存当前activity的一些状态，当在同一线程再次创建同一个Activity时 
方法onCreate(Bundle savedInstanceState) 可以从参数 Bundle 获得之前保存的信息
当这个app所属的线程被杀死,也就是app重启之后,该方法不会执行，必须是同一个线程再次创建 才能保存数据outState
https://blog.csdn.net/u010163141/article/details/56485232
执行位置   onPause-------【onSaveInstanceState】--------onStop

■ protected void onSaveInstanceState(Bundle outState) {}


onRestoreInstanceState函数是用来恢复之前在onSaveInstanceState中保存的数据，他的参数Bundle 和 onCreate(Bundle savedInstanceState) 周期函数的参数 Bundle 是同一个
执行位置  onPause -> onSaveInstanceState -> onStop -> onDestroy -> onCreate -> onStart -> onRestoreInstanceState -> onResume 
■  protected void onRestoreInstanceState(Bundle savedInstanceState) {}


屏幕方向改变可以触发onConfigurationChanged ，其他的一些系统设置改变也可以触发，比如打开或者隐藏键盘
在manifest.xml 中声明 Activity 能够捕获旋转事件 切换键盘事件  <activity  Android:configChanges="orientation|keyboard">
http://www.360doc.com/content/17/0510/15/41302481_652722497.shtml
1、不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行一次。
2.设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横执行一次再切回竖屏时并不会重新调用。
3.设置Activity的android:configChanges="orientation|keyboardHidden”时，和2反应一样。
4.设置Activity的android:configChanges=“orientation|screeSize”时，切屏不会重新调用各个声明周期。

■ public void onConfigurationChanged(Configuration newConfig) {}




当手机按下back键时，执行此方法
Activity可以直接重写onBackPressed监听返回键，但是fragment不能直接监听。
一种方法是通过接口回调实现监听，就是在Fragment中实现回调接口，Activity中重写onBackPressed，然后调用接口
Fragment的实现方式 在Fragment中重写onResume方法，实现OnKeyListener并监听按键：
■ public void onBackPressed() {}

   Activity 对触摸事件的处理  Return true if you have consumed the event,return  false if you haven't 
■  public boolean onTouchEvent(MotionEvent event) {}   

```


<img src="http://img.blog.csdn.net/20130713110145171?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTFZYSUFOR0FO/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">
<img src="http://img.blog.csdn.net/20130713110208640?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTFZYSUFOR0FO/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">
<img src="http://img.blog.csdn.net/20130713110235281?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTFZYSUFOR0FO/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">
<img src="http://img.blog.csdn.net/20130713110250484?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTFZYSUFOR0FO/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast">
```

当触摸事件发生的时候，首先会被当前的activity进行分发，即当前activity的dispatchTouchEvent方法会被执行。
这个时候,该方法有三种返回的情况：  https://www.cnblogs.com/ethanlee/p/4073979.html        https://www.cnblogs.com/lbangel/p/3791431.html
          return false： 表明事件不会被进行分发。事件会以冒泡的方式被传递给上层的view或activity的onTouchEvent方法进行消费掉。
          return true：表明该事件已经被处理。事件会被当前view或activity的dispatchTouchEvent给消费掉。不会再进行传递，事件到此结束。
          return super.dispatchTouchEvent(ev)：表明该事件将会被分发。此时当前View的 onIntercepterTouchEvent 方法会捕获该事件，判断需不需要进行事件的拦截。
传递的顺序为：   其中包含关系为  <MainActivity> <FatherView> <ChildView> </ChildView>  </FatherView> </MainActivity>
            MainActivity-dispatchTouchEvent-->ACTION_DOWN
            FatherView-dispatchTouchEvent-->ACTION_DOWN
            FatherView-onInterceptTouchEven-->ACTION_DOWN
            ChildView-dispatchTouchEvent-->ACTION_DOWN
            ChildView-onInterceptTouchEvent-->ACTION_DOWN。
            ChildView-onTouchEvent-->ACTION_DOWN
            Father-onTouchEvent-->ACTION_DOWN。 
            MainActivty-onTouchEvent-->ACTION_DOWN

■  public boolean dispatchTouchEvent(MotionEvent ev) { }

```


## ActionBar.TabListener
```
ActionBar 的点击监听事件 
■    public interface TabListener {
        void onTabSelected(ActionBar.Tab var1, FragmentTransaction var2);
        void onTabUnselected(ActionBar.Tab var1, FragmentTransaction var2);
        void onTabReselected(ActionBar.Tab var1, FragmentTransaction var2);
    }

ClassA implements ActionBar.TabListener{
	void onTabSelected(ActionBar.Tab var1, FragmentTransaction var2){}
	void onTabUnselected(ActionBar.Tab var1, FragmentTransaction var2){}
	void onTabReselected(ActionBar.Tab var1, FragmentTransaction var2){}

}

ActionBar.Tab tab;
tab.setTabListener(new ClassA());   // 设置 监听

```
# B

## BroadcastReceiver【abstract class】
```
要创建广播  就必须 实现 onReceive 方法
public abstract class BroadcastReceiver {
■     public abstract void onReceive(Context var1, Intent var2);
}

```
# C
## CursorAdapter 【abstract class】
```

public abstract class CursorAdapter extends BaseAdapter {
    public abstract View newView(Context var1, Cursor var2, ViewGroup var3);   // 需要实现类 去实现的抽象方法
    public abstract void bindView(View var1, Context var2, Cursor cur) {Cursor.getPosition() 可以获得对应的位置 };  // 需要实现类 去实现的抽象方法

    public void changeCursor(Cursor cursor) { }  // 需要实现类 重载的方法  用于切换数据 设置Adapter的 Cursor


//  CursorAdapter 提供的 接口 用于返回 对应位置的Cursor  在 bindView中Cursor可被调用的,   Cursor.java 是一个
//   Cursor cursor = (Cursor) getItem(cursor.getPosition()); 【 调用 CursorAdapter的getItem 方法 可以返回 Cursor的实例】
    public Object getItem(int position) {}   
}

```

## Cursor 接口
```
public interface Cursor extends Closeable {
    int FIELD_TYPE_NULL = 0;
    int FIELD_TYPE_INTEGER = 1;
    int FIELD_TYPE_FLOAT = 2;
    int FIELD_TYPE_STRING = 3;
    int FIELD_TYPE_BLOB = 4;

    int getCount();
    int getPosition();
    boolean move(int var1);
    boolean moveToPosition(int var1);
    boolean moveToFirst();
    boolean moveToLast();
    boolean moveToNext();
    String getColumnName(int var1);
    String[] getColumnNames();
    int getColumnCount();
    short getShort(int var1);
    int getInt(int var1);
    long getLong(int var1);
    float getFloat(int var1);
    double getDouble(int var1);
    Bundle getExtras();

......
}
```


## ContentResolver  【abstract class】
```
public abstract class ContentResolver {

访问系统提供的数据资源 并返回Cursor游标 
uri   访问资源的路径标识  如  content://media/external/file    content://media/external/audio/media    content://media/external/images/media
projection    资源数据库中表的字段  {"_data" ， "_size" ，"_display_name" ， "title" ，  "date_added"，"date_modified"，"mime_type"}  等等
selection     是Select语句中的 Where部分    _data LIKE '%.apk'    (mime_type == 'application/zip')  ......
selectionArgs    Where部分如果有？ 问号占位符 那么就在这里写入一一对应的占位符具体的字符串内容
sortOrder       返回结果的排序方式 order          title asc       date_modified desc  等等
■    public final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {}

}




```
# D
# E
# F
##  FragmentPagerAdapter
```
FragmentPagerAdapter 抽象类 继承自  抽象类PagerAdapter    实现了 PagerAdapter 的一个抽象方法 并增加自己的抽象方法  需要实现的抽象方法为
public abstract class FragmentPagerAdapter extends PagerAdapter {

对于每个位置索引  返回对应的Fragment
■  public abstract Fragment getItem(int var1);  

返回 使用的 Fragment的数量
■   public int getCount()；
}



```


## Fragment
<img src="https://upload-images.jianshu.io/upload_images/2244681-3685a0866eb07d3a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/317"><img src="http://img.my.csdn.net/uploads/201211/29/1354170682_3824.png">
<img src="https://upload-images.jianshu.io/upload_images/2244681-5fbe2b15f9935f4a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/317">
```
public class Fragment {
Activity 重绘时( 横竖屏切换时)，我们的 Fragment 不会被重复绘制，当Fragment setRetainInstance(true)时 重绘Activity时 Fragment不会被销毁，
所以利用这点ViewModel能实现 生命周期感知类型的 LivingData，来实现 MVVM设计模式
在onCreate()方法中调用setRetainInstance(true/false)方法是最佳位置    https://www.jianshu.com/p/1b3f829810a1
当在onCreate()方法中调用了setRetainInstance(true)后，Fragment恢复时会跳过onCreate()和onDestroy()方法，因此不能在onCreate()中放置一些初始化逻辑，切忌！
■   public void setRetainInstance(boolean retain);

//-------------------------声明周期函数Begin
onAttach -> onCreate -> onCreateView ->onViewCreated -> onActivityCreated -> onViewStateRestored -> onStart -> onResumeonPause->onStop->onDestoryView->onDestory->onDetach

■     public void onAttach(Activity activity) {
    }

■     public void onCreate(Bundle savedInstanceState) {
    }

■     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    }

■    public void onViewCreated(View view, Bundle savedInstanceState) {
    }


■    public void onActivityCreated(Bundle savedInstanceState) {
    }

■    public void onStart() { }

■    public void onResume() { }

■     public void onPause() { }

■     public void onStop() {}

■   public void onDestroyView() { }

■   public void onDestroy() { }

■   public void onDetach() {  }

//-------------------------声明周期函数END


android一共有三种形式的菜单:  1.选项菜单（optinosMenu） 2.上下文菜单（ContextMenu） 3.子菜单(subMenu) 
其中最常用的就是选项菜单(optionsMenu), 该菜单在点击 menu 按键 后会在对应的Activity底部显示出来。 
设置 Fragment的OptionMenu 一般是下拉 : 点击出现的菜单  onCreateOptionsMenu 用于创建Menu  onPrepareOptionsMenu 用于动态修改
■   public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { }

在onCreateOptionsMenu执行后，菜单被显示前调用 用于动态修改菜单内容
■  public void onPrepareOptionsMenu(Menu menu) {  }   




}



```
# G
# H
## Handler
[Andorid中Handler造成的内存泄漏浅析与处理](https://blog.csdn.net/zheng2186096/article/details/52372294)

**内存泄露的 Handler 内部类Handler的实例**
<img src="https://img-blog.csdn.net/20160830194333134?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center">


**最终优化的 静态内部类MyHandler   继承自Handler 帮我们管理资源回收**
<img src="https://img-blog.csdn.net/20160830195449368?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center">
<img src="https://img-blog.csdn.net/20160830195525738?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center">

```
    private Handler mHandler = new Handler() {  // 实现Handler方式一:  创建Handler 重载  handleMessage函数  会导致内存泄漏
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FILE_CHANGED_TIMER:
                    updateUI();
                    break;
            }
            super.handleMessage(msg);
        }

    };
方式一内存泄露分析：
1.由于mHandler 是 Activity 内部类的实例，所以它持有外部类Activity的引用，
2.息队列是在一个Looper线程中不断轮询处理消息，而消息队列中的Message持有mHandler实例的引用
因为： mHandler又持有Activity的引用，Message 又持有mHandler的引用， 
所以   当这个Activity退出时，消息队列中可能还有未处理的消息或者正在处理消息
结果： 将导致该Activity的内存资源无法及时回收，引发内存泄漏。

修改方案： 静态内部类继承Handler  方法中弱引用Activity  详情如上图
【在Loop.loop() 的 while(true){} 方法内首先会执行 CallBack的 handleMessage() 方法，该方法 返回的 boolean 用于判断 在当前的 Handler.CallBack 是否处理完当前的消息】
【所以 这个 Handler.CallBack 才会有返回值    所以继承了Handler的类 重载的 handleMessage 返回值 为 void】

private Handler mHandler2 = new Handler(new Handler.Callback() { 【Handler(Handler.Callback callback) 】  实现Handler方式二:  创建Handler 填充参数 Handler.Callback
       @Override  
       public boolean handleMessage(Message msg) {  
           return false;  
       }  
   }); 
两种返回的参数不同


public class Handler {

Handler 用于处理消息的函数 
■    public void handleMessage(Message msg) {
    }

Handler 提供的接口
■    public interface Callback {
        boolean handleMessage(Message var1);
    }
}

```
# I

## IntentService
[IntentService ](https://blog.csdn.net/lyj1005353553/article/details/53647121)
```
IntentService  是一个继承自Service的抽象类  实例化必须实现方法 onHandleIntent() 
public abstract class IntentService extends Service {
■    protected abstract void onHandleIntent(@Nullable Intent intent);

}


IntentService 是继承于 Service 并处理异步请求的一个类，【在 IntentService 内有一个工作线程来处理耗时操作】
启动 IntentService 的方式和启动传统 Service 一样，同时，当任务执行完后，【IntentService 会自动停止】，而【不需要我们去手动控制】
，可以启动 IntentService 多次，而每一个耗时操作会以工作队列的方式在IntentService 的 onHandleIntent 回调方法中执行，
并且，每次只会执行一个工作线程，执行完第一个再执行第二个，以此类推。

用 IntentService的好处：
第一，我们省去了在 Service 中手动开线程的麻烦，
第二，当操作完成时，我们不用手动停止 Service




```
# J
# K
# L
# M
# N
# O
# P
##   PagerAdapter【 abstract class 】
```
public abstract class PagerAdapter {
■  public abstract int getCount();
■  public abstract boolean isViewFromObject(View var1, Object var2);  【被 FragmentPagerAdapter 抽象类实现该方法】
}

```


## PopupWindow
```
Android 中的弹窗基本有两种:
一种是AlertDialog，
另一种是PopupWindow，AlertDialog的显示位置是固定的，PopWindow 的显示位置是我们可以设置和调整的


popup window 是一个出现在当前Activity顶层的悬浮容器，可以用来展示任意的View。

1,构造函数，这个不用多说，多个重载函数，传递不同的参数。
public PopupWindow(int width, int height)
public PopupWindow(View contentView, int width, int height) 
public PopupWindow(View contentView, int width, int height, boolean focusable)

2，设置显示的View：
public void setContentView(View contentView)


3,设置展示的宽、高，构造函数传了宽高就不用重新设置
// 设置宽，其实构造函数也是调用的这个方法
public void setWidth(int width)
//设置高
public void setHeight(int height)

4，设置是否获取焦点
public void setFocusable(boolean focusable)


5,设置点击PopupWindow 以外区域是否可以隐藏PopupWindow
public void setOutsideTouchable(boolean touchable)


6，隐藏PopupWindow
public void dismiss()

7,设置dissmiss 回调监听
public void setOnDismissListener(OnDismissListener onDismissListener)

8,显示 PopupWindow
//直接显示在参照View 的左下方
public void showAsDropDown(View anchor)
// 显示在参照View的左下方，可以通过xoff，yOff,来调节x,y方向的偏移
public void showAsDropDown(View anchor, int xoff, int off)
public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
//显示在指定位置，相对于整个屏幕的window而言，通过gravity调解显示在左、上、右、下、中. x,y调整两个方向的偏移
public void showAtLocation(View parent, int gravity, int x, int y)


PopupWindow 适配性问题：
https://www.jianshu.com/p/dbd792b910ce
PopupWindow 的height 使用 match_parent 或 fill_parent导致的问题
api >=24(Android 7.0)时 View anchor 相对于anchor popupwindow 的位置 无效果 效果是全屏
public void showAsDropDown(View anchor)


if (Build.VERSION.SDK_INT < 24) {
                    popupWindow.showAsDropDown(button);
   } else {
        int[] location = new int[2];  // 获取控件在屏幕的位置
        button.getLocationOnScreen(location);
      if (Build.VERSION.SDK_INT == 25) {
         int tempheight = popupWindow.getHeight();
      if (tempheight == WindowManager.LayoutParams.MATCH_PARENT || screenHeight <= tempheight) {
             popupWindow.setHeight(screenHeight - location[1] - button.getHeight());
           }
     }
       popupWindow.showAtLocation(button, Gravity.NO_GRAVITY, location[0], location[1] + button.getHeight());
 }

```

## 
# Q
# R

## reflection (Java反射)
[Java反射](https://blog.csdn.net/u010842019/article/category/6338912)

```
【获得Class的方式:】
Test testObject = new Test();  
Class testClass = testObject.getClass();  
	
Class test = Class.forName("D1.Test");  
		
【获得方法:】
Method objectMethod =  testClass.getMethod("object_method", null);  
Method staticMethod =  testClass.getMethod("static_method", null);  
staticMethod.invoke(testObject, null);   // 调用静态方法
staticMethod.invoke(testClass, null);   // 调用静态方法
objectMethod.invoke(testObject, null);   // 调用对象方法

testClass = Class.forName("D1.Test");  
Method[] methods = test.getMethods();   // 好的方法列表

methods[i].getReturnType().toString()  // 获得方法返回值

Class[] pList = methods[i].getParameterTypes();// 获得参数列表  

【获取属性列表:】
Class test = Class.forName("D1.Test"); 
Field[] fList = testClass.getDeclaredFields();  
fList[i].getName()  // 获得属性的名称

fList[i].get(testClass)  // 获得静态属性的值
fList[i].get(testObject) // 获得对象属性的值

fList[i].getType() // 获得属性类型
```


## RecyclerView
```
什么是RecyclerView：
RecyclerView 是在Android5.0之后推出的，是一个比ListView更加灵活更加高效的适配器类型控件。
但是RecyclerView不同于其他类型的适配器，它还需要一个LayoutManager进行页面控制展示。


■   RecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
■   RecyclerView.setLayoutManager(new GridLayoutManager(this,3));
■   RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
■   RecyclerView.Adapter.notifyDataSetChanged();

RecyclerView的使用：
　　1、引入RecyclerView依赖包，V7下的，兼容到API17.
　　2、在xml布局中声明，在Java代码中初始化。
　　3、设置布局管理器
　　4、创建适配器，设置数据源，绑定适配器
　　　　具体创建适配器：①创建一个类，继承RecyclerView.Adapter<ViewHolder>.
　　　　　　　　　　　　②创建一个类ViewHolder，继承RecyclerView。VIewHolder，该类需要创建一个匹配父类的构造。
　　　　　　　　　　　　③重写适配器中的方法：getItemCount():获取数据源的个数（item的数量）；onCreateViewHolder():该方法中导入布局，实例化VIewHolder；onBindViewHolder():绑定VIewHolder，加载数据。


RecyclerView的适配器 以及 ViewHolder：
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {


    public static class ViewHolder extends RecyclerView.ViewHolder{   //  RecyclerView.ViewHolder 用于绑定视图
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.teach_item_name);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {  // RecyclerView.ViewHolder 用于缓存视图

        private Map<Integer, View> mCacheView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCacheView = new HashMap<>();
        }

        public View getView(int resId) {
            View view;
            if (mCacheView.containsKey(resId)) {
                view = mCacheView.get(resId);
            } else {
                view = itemView.findViewById(resId);
                mCacheView.put(resId, view);
            }
            return view;
        }
    }




  @Override
    public int getItemViewType(int position) {
        return data.get(position).getShow_type();
    }



   /**
     * 创建VIewHolder，导入布局，实例化itemView
     * @param parent
     * @param viewType
     * @return
     */
参数一：ViewGroup parent，是指RecycleView的布局 
参数二：int viewType，是指Item的属性，该属性是在public int getItemViewType(int position)中进行设置获取的。 【getItemViewType可实现Recycle加载不同的item.xml 文件】
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  【导入布局文件  并转化为ViewHoder】
        View itemView = inflater.inflate(R.layout.item, parent, false);
        //导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }



    /**
     * 绑定VIewHolder，加载数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {    【ViewHoder 与 position 决定了什么控件  在哪里显示  需要在此方法实现 显示什么数据】
        holder.name.setText(data.get(position).getName());//加载数据
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height=data.get(position).getHeight();
        holder.itemView.setLayoutParams(layoutParams);
    }


    /**
     * 数据源的数量，item的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }


    /**
     * 接口回调
     * 1、定义接口，定义接口中的方法
     * 2、在数据产生的地方持有接口，并提供初始化方法，在数据产生的时候调用接口的方法
     * 3、在需要处理数据的地方实现接口，实现接口中的方法，并将接口传递到数据产生的地方
     */
    public interface OnItemClickListener{
        void onItemClick(int position,Model model);
    }


}


```
# S

## SwipeRefreshLayout
[Google官方更新的一个Widget 下拉加载 没有实现上拉加载 需自定义](https://blog.csdn.net/huaxun66/article/details/74614792)

```
SwipeRefreshLayout是Google官方更新的一个Widget,可以实现下拉刷新的效果
也可以通过一些方法来设置SwipeRefreshLayout是否可以刷新。

setRefreshing(true)，展开刷新动画。
setRefreshing(false)，取消刷新动画。
setEnable(false)下拉刷新将不可用。


package android.support.v4.widget;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent,
 NestedScrollingChild {
 
■  isRefreshing()   判断当前的状态是否是刷新状态

设置下拉进度条的颜色主题，参数为可变参数，并且是资源id，最多设置四种不同的颜色，每转一圈就显示一种颜色。
■  setColorSchemeResources(int… colorResIds)  


设置监听，需要重写onRefresh()方法，顶部下拉时会调用这个方法，在里面实现请求数据的逻辑，设置下拉进度条消失等等。
■   setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) 


设置下拉进度条的背景颜色，默认白色。 
■  setProgressBackgroundColorSchemeResource(int colorRes) 

设置刷新状态，true表示正在刷新，false表示取消刷新。
■  setRefreshing(boolean refreshing) 

}



使用方法：
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.SwipeRefreshLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/operators_refresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/operators_recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </android.support.v7.widget.RecyclerView>

</android.support.v4.widget.SwipeRefreshLayout>


...
// 设置下拉进度的背景颜色，默认就是白色的       
swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
// 设置下拉进度的主题颜色        
swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

// 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态，经测试这里可以省略
//                swipeRefreshLayout.setRefreshing(true);

                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.add(0, "我是刷新出来的：" + random.nextInt(100));
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(FirstActivity.this, "刷新了一条数据", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
        
        

```
# T
# U
# V
## View 重载接口
```
当前的view把事件进行了拦截，则事件则会被传递到该方法中
 return false：表明没有消费该事件，事件将会以冒泡的方式一直被传递到上层的view或Activity中的onTouchEvent事件处理。
               如果最上层的view或Activity中的onTouchEvent还是返回false。则该事件将消失。接下来来的一系列事件都将会直接被上层的onTouchEvent方法捕获
 return true: 表明消费了该事件，事件到此结束。
 return super.onTouchEvent(event)：默认情况，和return false一样。
■  public boolean onTouchEvent(MotionEvent event) {}

```

## ViewGroup接口   ViewGroup extends View
```

同Activity的 dispatchTouchEvent 一样 用于在ViewGroup内传递触摸事件 
■  public boolean dispatchTouchEvent(MotionEvent ev) {} 



该方法用于拦截 dispatchTouchEvent 传递过来的事件，用于判断被传递过来的事件是否需要被当前的view进行处理。 Layout 继承了 ViewGroup 
     return false : 不对事件进行拦截，放行该事件。事件会被传递到当前view的子控件中，由子控件中的dispatchTouchEvent方法进行分发处理。
     return true : 拦截该事件，将该事件交给当前view的onTouchEvent方法进行处理。
     return super.onInterceptTouchEvent(ev)：默认拦截方式，和return true一样。
■   public boolean onInterceptTouchEvent(MotionEvent ev) {}


```

## ViewPager
```
提供ViewPage 页面切换的接口
■     public interface OnPageChangeListener {
        void onPageScrolled(int var1, float var2, int var3);
        void onPageSelected(int var1);
        void onPageScrollStateChanged(int var1);
}


ViewPager.OnPageChangeListener


ClassA implements ViewPager.OnPageChangeListener{
        void onPageScrolled(int var1, float var2, int var3){}
        void onPageSelected(int var1){}
        void onPageScrollStateChanged(int var1){}

}

ViewPager mViewPager;
mViewPager.setOnPageChangeListener(new ClassA());  // 设置 监听




```

# W
# X
# Y
# Z