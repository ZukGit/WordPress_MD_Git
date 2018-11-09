# AndroidManifest.xml 文件分析

## Activity的分析
### FileExplorerTabActivity
```
        <activity
            android:name=".FileExplorerTabActivity"

【对android:configChanges属性，一般认为有以下几点：
  1、不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次
  2、设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次
  3、设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"时，切屏不会重新调用各个生命周期， 只会执行onConfigurationChanged方法】
            android:configChanges="screenSize|keyboardHidden"
            android:screenOrientation="sensor"  【随手机方向进行横竖切换】
            android:uiOptions="splitActionBarWhenNarrow">


            <intent-filter >
                <action android:name="android.intent.action.MAIN" />  【主-Main-Activity】
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

            <intent-filter >
                <action android:name="android.intent.action.PICK" />【接收图片挑选的隐式Action 对应的Intent】
                <category android:name="android.intent.category.DEFAULT" />
                <action android:name="android.intent.action.MEDIA_MOUNTED" />【SD卡挂载】
                <action android:name="android.intent.action.MEDIA_UNMOUNTED" />【SD卡卸载】
                <data android:scheme="file" /> 
【Data通常是URI格式定义的操作数据。例如：file:// 。通过setData()方法设置】
【scheme://host:port/path】
            </intent-filter>
            <intent-filter >
                <action android:name="android.intent.action.GET_CONTENT" />
【intent.ACTION_GET_CONTENT这个是调用系统程序用的，比如一个打开一个文件的时候会提示你用哪个软件打开】
【setType（）就是设置默认用哪种格式打开，比如"video/*"，"audio/amr"】
                <category android:name="android.intent.category.OPENABLE" />
                <category android:name="android.intent.category.DEFAULT" />

                <data android:mimeType="*/*" />
            </intent-filter>
        </activity>

```
分析FileExplorerTabActivity的xml定义可知
1.  该Activity是一个MainActivty 首启动    "android.intent.action.MAIN"
2.  该Activity横竖切换时 会重新执行 Activity的生命周期函数 android:screenOrientation="sensor"
3.  该Activity接受文件打开请求的Action，即当未知文件没有默认打开软件,该APP将是一个候选软件 action.GET_CONTENT
4.  该Activity接受文件的选择Intent   android.intent.action.PICK
5.  该Activity 监听 SD卡的 挂载 卸载  MEDIA_MOUNTED MEDIA_UNMOUNTED

### SearchActivity
```
        <activity android:name=".SearchActivity" >
            <intent-filter >
                <action android:name="android.intent.action.SEARCH" />【执行搜索】

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
【处于AndroidManifests.xml下的meta-data则是对外界开放的，是向系统注册的信息，系统及外界是可以通过PackageInfo相关API获取到meta-data的信息】
            <meta-data
                android:name="android.app.searchable"  【搜索相关功能】
                android:resource="@xml/searchable" />
        </activity>

```
分析SearchActivity 的xml定义可知
1. 该Activity是一个响应 android.intent.action.SEARCH 执行搜索 的Activity  该类实现搜索相关功能

### FileExplorerPreferenceActivity
```
        <activity
            android:label="@string/menu_setting"
            android:launchMode="singleTop" 【Activity的启动模式】
            android:name="FileExplorerPreferenceActivity"
            android:theme="@android:style/Theme.Holo.Light.NoActionBar" >
        </activity>
```
分析 FileExplorerPreferenceActivity 的xml定义可知
1. 该Activity是一个singleTop 只能有一个并且总是在栈顶的Activity

### IFLYBrowser
```
<activity
      android:name="com.iflytek.voiceads.request.IFLYBrowser"
      android:screenOrientation="portrait" />

```
分析 IFLYBrowser 的Activity  xml定义可知
1. 该Activity是一个只显示竖屏的Activity  android:screenOrientation="portrait" 


## Service的分析

### AseoZdpUpdateService
```
<service android:name="com.zdp.aseo.content.AseoZdpUpdateService" />
```
分析 AseoZdpUpdateService 的 Service xml定义可知
1. 该Service 字面意思和上传文件有关系

### FTPServerService
```
<service android:name=".FTPServerService" />
```
分析 FTPServerService 的 Service xml定义可知
1. 该Service 字面意思和FTP文件传输有关系


# Activity 文件详细分析

## FileExplorerTabActivity.java分析

### FileExplorerTabActivity的成员属性分析
####  Object对象属性成员
```
1. Object对象属性成员


private static final String INSTANCESTATE_TAB = "tab";  【 Intent 中传递Tab索引的Key】
private static final int DEFAULT_OFFSCREEN_PAGES = 2;   【当前ViewPage的总共最后一个Page页面的索引  从0开始】
ViewPager mViewPager;         【 ViewGroup View容器 】
TabsAdapter mTabsAdapter;       【 View适配器  Data 和 View的桥梁】
ActionMode mActionMode;           【ActionMode 是 Android 提供的一种实现菜单方式  使用ActionMode，它会临时占据action bar的位置，但与ActionBar是独立的 】
 
```

#### 类Class 定义的静态属性成员
```
2. 静态属性成员

2.1 静态内部类： TabsAdapter       TabsAdapter.TabInfo
TabsAdapter  ---> FragmentPagerAdapter --->  PagerAdapter
    public static class TabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
        private final Context mContext;
        private final ActionBar mActionBar;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        static final class TabInfo { //  静态内部类的静态内部类
            private final Class<?> clss;  // Fragment 对应的 Class 
            private final Bundle args;  //  需要传递给该 Fragment的 Bundle<Key,Value> 数据
            private Fragment fragment;

            TabInfo(Class<?> _class, Bundle _args) {
                clss = _class;
                args = _args;
            }
        }

        public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {  // 添加数据的方法
            TabInfo info = new TabInfo(clss, args);
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mActionBar.addTab(tab);
            notifyDataSetChanged();
        }


}

2.2 静态内部类实现的接口  ActionBar.TabListener  ViewPager.OnPageChangeListener


    public interface OnPageChangeListener {  // 让Adapter能实现 监听到   页面上下滚动   页面切换  的能力
        void onPageScrolled(int var1, float var2, int var3);  
        void onPageSelected(int var1);
        void onPageScrollStateChanged(int var1);
    }

    public interface TabListener { // 让Adapter能实现 监听到   Tab控件选中 Tab控件离场 Tab控件释放的 能力
        void onTabSelected(ActionBar.Tab var1, FragmentTransaction var2);
        void onTabUnselected(ActionBar.Tab var1, FragmentTransaction var2);
        void onTabReselected(ActionBar.Tab var1, FragmentTransaction var2);
    }

2.3 ActionBar.TabListener  ViewPager.OnPageChangeListener 接口 具体的实现

-----------------------ViewPager.OnPageChangeListener 

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(int position) {
            mActionBar.setSelectedNavigationItem(position);  【当 ViewPage页面变化时 设置ActionBar也依据 position变化】
        }

        @Override
        public void onPageScrollStateChanged(int state) { }


---------------------ActionBar.TabListener
            
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            Object tag = tab.getTag();  //  【每个tab 都有一个 tabinfo 作为 tag标签】  tab.setTag(new TabInfo(clss, args));
            for (int i=0; i<mTabs.size(); i++) {
                if (mTabs.get(i) == tag) {  
 【当ActionBar中的一个Tab 被点击时执行  从tab中获取tag 然后遍历TabInfo数组 就能得到对应Tab索引 然后通过ViewPage切换页面   】
                    mViewPager.setCurrentItem(i);
                }
            }
            if(!tab.getText().equals(mContext.getString(R.string.tab_sd))) {
                ActionMode actionMode = ((FileExplorerTabActivity) mContext).getActionMode();
                if (actionMode != null) {
                    actionMode.finish();
                }
            }
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) { }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) { }



2.4 静态内部类  TabsAdapter  重载的父类方法

TabsAdapter  ---> FragmentPagerAdapter --->  PagerAdapter
---------------------- 重载父类FragmentPagerAdapter 的  getItem 方法
        @Override
        public Fragment getItem(int position) {
            TabInfo info = mTabs.get(position);
            if (info.fragment == null) {
                info.fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
            }
            return info.fragment;
        }
---------------------- 重载爷类 PagerAdapter 的  getItem 方法

        @Override
        public int getCount() {
            return mTabs.size();
        }

```


####  对象Object普通方法
```
3. 对象Object普通方法


    public void setActionMode(ActionMode actionMode) {
        mActionMode = actionMode;
    }

    public ActionMode getActionMode() {  【对外提供 设置ActionMode 和 获取ActionMode 的普通方法 】
        return mActionMode;
    }

    public Fragment getFragment(int tabIndex) {  【对外提供 索引对应 Fragment】
        return mTabsAdapter.getItem(tabIndex);
    }

    public void reInstantiateCategoryTab() {  // 对外提供重新加载 Adapter的 函数
        mTabsAdapter.destroyItem(mViewPager, Util.CATEGORY_TAB_INDEX, mTabsAdapter.getItem(Util.CATEGORY_TAB_INDEX));
        mTabsAdapter.instantiateItem(mViewPager, Util.CATEGORY_TAB_INDEX);
    }
 
```

#### 类Class的 静态函数方法
```
4. 类Class的 静态函数方法

无

```


#### 对象Object 的 定义的接口
```
5. 对象Object 的 定义的接口

    public interface IBackPressedListener {  //  定义接口的目的:  让其他View控件  能实现 监听到  定义接口类 传递的信息  并处理
        /**
         * 处理back事件。
         * @return True: 表示已经处理; False: 没有处理，让基类处理。   基类处理就直接回来起始界面  再退就退出APP的那个界面
         */
        boolean onBack();
    }

```

#### 对象Object  的 实现的接口
```
无
```
#### 对象Object重载的父类方法
// 重载父类的方法 使得本身专注于自身的客制化  Common的部分系统底部已经隐藏 每个Activity只要去重载自己希望special的部分
```
6. 对象Object重载的父类方法


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE);  【广告插件? 】

        setContentView(R.layout.fragment_pager);  
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(DEFAULT_OFFSCREEN_PAGES); 【 设置ViewPage的总页面】
        AseoZdpAseo.initTimer(this);  【广告插件? 】
        final ActionBar bar = getActionBar(); 【 getActionBar() 用来获取当前Activity页面的 ActionBar对象 】
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  【设置 ActionBar的样式  】
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_HOME);
        AseoZdpAseo.init(this,AseoZdpAseo.SCREEN_TYPE);  【广告插件? 】
        mTabsAdapter = new TabsAdapter(this, mViewPager); 【创建Adapter 】
【往Adapter 添加 ActionBar.newTab() 填充 ArrayList<TabInfo> 并往ActionMode添加Tab】
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_category), FileCategoryActivity.class, null); 
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_sd),  FileViewActivity.class, null);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_remote), ServerControlActivity.class, null);
        bar.setSelectedNavigationItem(PreferenceManager.getDefaultSharedPreferences(this).getInt(INSTANCESTATE_TAB, Util.CATEGORY_TAB_INDEX));【从SP找启动page索引 默认0】
    }

    public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_TABS = 2; //  ActionBar 样式
    public static final int DISPLAY_USE_LOGO = 1;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_SHOW_CUSTOM = 16;  // ActionBar 样式





    @Override
    protected void onPause() {  // 当界面Activity 暂停 不可见时   在SP保存当前的page索引 
        super.onPause();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt(INSTANCESTATE_TAB, getActionBar().getSelectedNavigationIndex());
        editor.commit();
    }





    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt(INSTANCESTATE_TAB, getActionBar().getSelectedNavigationIndex());
        editor.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {  
        if (getActionBar().getSelectedNavigationIndex() == Util.CATEGORY_TAB_INDEX) { // 如果当界面横竖切换时 如前page是 Category分类首页  
            FileCategoryActivity categoryFragement =(FileCategoryActivity) mTabsAdapter.getItem(Util.CATEGORY_TAB_INDEX);
            if (categoryFragement.isHomePage()) {  【如果Fragment 显示的首页 那么重新实例化 Fragment】
                reInstantiateCategoryTab();
            } else {
                categoryFragement.setConfigurationChanged(true);  【不是首页 那么设置 categoryFragement 变量 mConfigurationChanged 为 true】
            }
        }
        super.onConfigurationChanged(newConfig);
    }




    @Override
    public void onBackPressed() {
【从Adapter 得到那些 实现接口IBackPressedListener的控件类  并回调这些方法  让控制能处理当前的back事件】
        IBackPressedListener backPressedListener = (IBackPressedListener) mTabsAdapter.getItem(mViewPager.getCurrentItem());  
        if (!backPressedListener.onBack()) {  【如果所有控件 都 不处理(即返回false) 那么 在此会 Intent.FLAG_ACTIVITY_NEW_TASK 新任务栈 返回到起始界面】
			Intent intent = new Intent(Intent.ACTION_MAIN); 
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
        }
    }


```

#### Activity 与 Fragment的布局
FileExplorerTabActivity extends Activity                 R.layout.fragment_pager.xml
FileCategoryActivity extends Fragment                    R.layout.file_explorer_category
FileViewActivity extends Fragment                        R.layout.
class ServerControlActivity extends Fragment             R.layout.server_control_activity.xml


##### FileExplorerTabActivity 布局
```
\FileExplorer\app\src\main\res\layout\fragment_pager.xml

FileExplorerTabActivity extends Activity  {
setContentView(R.layout.fragment_pager);

TabsAdapter mTabsAdapter;

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_category),FileCategoryActivity.class, null);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_sd),FileViewActivity.class, null);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.tab_remote),ServerControlActivity.class, null);

}


    public static class TabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
        public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
            TabInfo info = new TabInfo(clss, args);  【把Fragment.class 封装在 TabInfo ，又把TabInfo 作为ActionBar.Tab 的 tag  setTag  getTag 】
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mActionBar.addTab(tab);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {  【实例化 Fragment 】
            TabInfo info = mTabs.get(position);
            if (info.fragment == null) {
                info.fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
            }
            return info.fragment;
        }

}



<?xml version="1.0" encoding="utf-8"?>

<android.support.v4.view.ViewPager
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</android.support.v4.view.ViewPager>


```



##### FileCategoryActivity布局

FileCategoryActivity extends Fragment                    R.layout.file_explorer_category
FileViewActivity extends Fragment                        R.layout.
class ServerControlActivity extends Fragment

```
FileExplorer\app\src\main\res\layout\file_explorer_category.xml

public class FileCategoryActivity extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.file_explorer_category, container, false);
       return mRootView;
}

}


FileExplorer\app\src\main\res\layout\file_explorer_category.xml

<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/file_browse_frame" android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical">

    <!-- path -->             【导航栏】
    <LinearLayout android:id="@+id/navigation_bar"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:background="@drawable/secondary_title_background"
        android:visibility="gone"
        android:paddingLeft="10dip"
        android:paddingRight="10dip">
        <LinearLayout android:layout_width="0dip"
            android:layout_weight="1" android:layout_height="wrap_content"
            android:gravity="center_vertical">
            <LinearLayout android:id="@+id/current_path_pane"
                android:background="@drawable/path_pane_bg"
                android:layout_width="fill_parent" android:maxWidth="300dip"
                android:gravity="center_vertical" android:layout_height="wrap_content">
                <LinearLayout android:id="@+id/current_path_scroller"
                    android:layout_width="0dip" android:layout_weight="1"
                    android:layout_marginRight="5dip"
                    android:layout_height="wrap_content">
                    <TextView
                        android:id="@+id/current_path_view"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:ellipsize="start"
                        android:paddingLeft="10dip"
                        android:singleLine="true"
                        style="?android:attr/textAppearanceMedium"/>
                </LinearLayout>
                <ImageView android:id="@+id/path_pane_arrow"
                    android:src="@drawable/arrow_down"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical" />
            </LinearLayout>
        </LinearLayout>
        <ImageView android:id="@+id/path_pane_up_level"            【返回上一级目录的 ImageView 】
            android:src="@drawable/path_up_level" android:layout_width="wrap_content"
            android:layout_height="wrap_content" android:layout_gravity="center_vertical|right"
            android:layout_marginLeft="5dip" />
    </LinearLayout>
    <!-- end path -->

    <FrameLayout
        android:layout_width="fill_parent"
        android:layout_height="0dip"
        android:layout_weight="1">

        <ListView
            android:id="@+id/file_path_list"  【文件路径列表】
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:footerDividersEnabled="true"
            android:headerDividersEnabled="true"
            android:visibility="gone" />

        <ListView
            android:id="@+id/favorite_list"     【用户收藏 文件列表】
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:footerDividersEnabled="true"
            android:headerDividersEnabled="true"
            android:visibility="gone" />

        <LinearLayout
            android:id="@+id/empty_view"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:gravity="center"
            android:orientation="vertical"
            android:visibility="gone">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/empty_icon" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:singleLine="true"
                android:text="@string/no_file"    <string name="no_file">没有文件</string>
                android:textColor="#e2e2e2"
                android:textSize="27px" />
        </LinearLayout>

        <ScrollView
            android:id="@+id/dropdown_navigation"  【当前路径导航栏  点击之后显示的下拉框】
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:background="@drawable/dropdown"
            android:overScrollMode="never"
            android:visibility="gone">

            <LinearLayout
                android:id="@+id/dropdown_navigation_list"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"></LinearLayout>
        </ScrollView>

        <LinearLayout
            android:id="@+id/sd_not_available_page"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:gravity="center"
            android:orientation="vertical"
            android:visibility="gone">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginBottom="25px"
                android:src="@drawable/sd_not_available" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="@string/enable_sd_card"  <string name="enable_sd_card">无可用SD卡，请插入或连接SD卡</string>
                android:textSize="22px" />

        </LinearLayout>

        <LinearLayout
            android:id="@+id/category_page"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:orientation="vertical">

            <LinearLayout
                android:id="@+id/category_buttons"
                android:layout_width="fill_parent"
                android:layout_height="0dip"
                android:layout_weight="1"
                android:orientation="vertical">

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="0dip"
                    android:layout_gravity="center"
                    android:layout_weight="1"
                    android:orientation="horizontal">

                    <LinearLayout
                        android:id="@+id/category_music"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_left">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_music" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_music" />  <string name="category_music">音乐</string>

                        <TextView
                            android:id="@+id/category_music_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        android:id="@+id/category_video"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_middle">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_video" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_video" /> <string name="category_video">视频</string>

                        <TextView
                            android:id="@+id/category_video_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        android:id="@+id/category_picture"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_right">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_picture" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_picture" />  <string name="category_picture">图片</string>

                        <TextView
                            android:id="@+id/category_picture_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="0dip"
                    android:layout_gravity="center"
                    android:layout_weight="1"
                    android:orientation="horizontal">

                    <LinearLayout
                        android:id="@+id/category_theme"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_left">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_theme" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_theme" />   <string name="category_theme">主题</string>

                        <TextView
                            android:id="@+id/category_theme_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        android:id="@+id/category_document"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_middle">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_document" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_document" />   <string name="category_document">文档</string>

                        <TextView
                            android:id="@+id/category_document_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        android:id="@+id/category_zip"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_right">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_zip" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_zip" />   <string name="category_zip">压缩包</string>

                        <TextView
                            android:id="@+id/category_zip_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="0dip"
                    android:layout_gravity="center"
                    android:layout_weight="1"
                    android:orientation="horizontal">

                    <LinearLayout
                        android:id="@+id/category_apk"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_left">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_apk" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_apk" /> <string name="category_apk">安装包</string>

                        <TextView
                            android:id="@+id/category_apk_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        android:id="@+id/category_favorite"
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_middle">

                        <ImageView
                            style="@style/CategoryButtonIconStyle"
                            android:src="@drawable/category_icon_favorite" />

                        <TextView
                            style="@style/CategoryButtonTextStyle"
                            android:text="@string/category_favorite" />  <string name="category_favorite">收藏夹</string>

                        <TextView
                            android:id="@+id/category_favorite_count"
                            style="@style/CategoryButtonCountTextStyle" />
                    </LinearLayout>

                    <LinearLayout
                        style="@style/CategoryButtonStyle"
                        android:background="@drawable/category_button_right"></LinearLayout>
                </LinearLayout>
            </LinearLayout>

            <!-- category information -->
            <LinearLayout
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="bottom"
                android:background="@drawable/information_bg"
                android:gravity="center_vertical"
                android:orientation="vertical"
                android:paddingTop="5dip">

                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginBottom="5dip"
                    android:layout_marginLeft="20px"
                    android:orientation="horizontal">

                    <TextView
                        android:id="@+id/sd_card_capacity"
                        style="@style/InformationTextStyle"
                        android:drawableLeft="@drawable/sd_card_icon" />

                    <TextView
                        android:id="@+id/sd_card_available"
                        style="@style/InformationTextStyle"
                        android:layout_marginLeft="10dip"
                        android:drawableLeft="@drawable/sd_card_available_icon" />
                </LinearLayout>

                <net.micode.fileexplorer.CategoryBar        【自定义视图  用于绘制各种类型所占百分比】
                    android:id="@+id/category_bar"
                    android:layout_width="fill_parent"
                    android:layout_height="35px"
                    android:layout_gravity="center"
                    android:layout_marginBottom="5dip"></net.micode.fileexplorer.CategoryBar>

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical">

                    <LinearLayout
                        android:layout_width="fill_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="5dip"
                        android:layout_marginLeft="20px"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/category_legend_music"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_music" />

                        <TextView
                            android:id="@+id/category_legend_video"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_video" />

                        <TextView
                            android:id="@+id/category_legend_picture"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_picture" />
                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="fill_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="5dip"
                        android:layout_marginLeft="20px"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/category_legend_theme"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_theme" />

                        <TextView
                            android:id="@+id/category_legend_document"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_document" />

                        <TextView
                            android:id="@+id/category_legend_zip"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_zip" />
                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="fill_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="5dip"
                        android:layout_marginLeft="20px"
                        android:orientation="horizontal">

                        <TextView
                            android:id="@+id/category_legend_apk"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_apk" />

                        <TextView
                            android:id="@+id/category_legend_other"
                            style="@style/CategoryLegendStyle"
                            android:drawableLeft="@drawable/legend_other" />

                        <TextView style="@style/CategoryLegendStyle" />
                    </LinearLayout>
                </LinearLayout>
            </LinearLayout>
        </LinearLayout>
    </FrameLayout>

    <LinearLayout android:id="@+id/moving_operation_bar"   【是否移动布局  包含确认的两个按钮】
        android:visibility="gone"
        style="@style/BottomButtonBar">

        <Button android:id="@+id/button_moving_confirm"
            android:text="@string/confirm"          <string name="confirm">确定</string>
            style="@style/BottomButton.Left"/>

        <Button android:id="@+id/button_moving_cancel"
            android:text="@string/operation_cancel"    <string name="operation_cancel">取消</string>
            style="@style/BottomButton.Right"/>
    </LinearLayout>
</LinearLayout>



```


##### FileViewActivity 布局

```
public class FileViewActivity extends Fragment{

View mRootView = inflater.inflate(R.layout.file_explorer_list, container, false);

return  mRootView;
}



FileExplorer\app\src\main\res\layout\file_explorer_list.xml


<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/file_browse_frame"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical">

    <!-- path -->
    <LinearLayout
        android:id="@+id/navigation_bar"    【顶部导航栏】
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:background="@drawable/secondary_title_background"
        android:paddingLeft="10dip"
        android:paddingRight="10dip">
        <LinearLayout
            android:layout_width="0dip"
            android:layout_weight="1"
            android:layout_height="wrap_content"
            android:gravity="center_vertical">
            <LinearLayout
                android:id="@+id/current_path_pane"
                android:background="@drawable/path_pane_bg"
                android:layout_width="fill_parent"
                android:maxWidth="300dip"
                android:layout_height="wrap_content"
                android:gravity="center_vertical">
                <LinearLayout
                    android:id="@+id/current_path_scroller"
                    android:layout_width="0dip"
                    android:layout_weight="1"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="5dip"
                    android:gravity="center_vertical">
                    <TextView
                        android:id="@+id/current_path_view"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:ellipsize="start"
                        android:paddingLeft="10dip"
                        android:singleLine="true"
                        style="?android:attr/textAppearanceMedium" />
                </LinearLayout>
                <ImageView
                    android:id="@+id/path_pane_arrow"
                    android:src="@drawable/arrow_down"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical" />
            </LinearLayout>
        </LinearLayout>
        <ImageView
            android:id="@+id/path_pane_up_level"       【返回上一层图标 】
            android:src="@drawable/path_up_level"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical|right"
            android:layout_marginLeft="5dip" />
    </LinearLayout>
    <!-- end path -->

    <FrameLayout
        android:layout_width="fill_parent"
        android:layout_height="0dip"
        android:layout_weight="1">
        <ListView
            android:id="@+id/file_path_list"          【文件的ListView】
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:headerDividersEnabled="true"
            android:footerDividersEnabled="true" android:fastScrollEnabled="true"/>
        <LinearLayout            【文件为空 无文件 时  显示的 布局】
            android:id="@+id/empty_view"
            android:orientation="vertical"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:gravity="center"
            android:visibility="gone">
            <ImageView
                android:src="@drawable/empty_icon"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/no_file"
                android:textSize="27px"
                android:singleLine="true" />
        </LinearLayout>
        <ScrollView
            android:id="@+id/dropdown_navigation"           【 导航栏的 下拉框】
            android:overScrollMode="never"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:background="@drawable/dropdown"
            android:visibility="gone">
            <LinearLayout
                android:id="@+id/dropdown_navigation_list"
                android:orientation="vertical"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content">
            </LinearLayout>
        </ScrollView>
        <LinearLayout
            android:id="@+id/sd_not_available_page"       【SD卡无效时 显示的布局】
            android:orientation="vertical"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:gravity="center"
            android:visibility="gone">
            <ImageView
                android:src="@drawable/sd_not_available"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginBottom="25px" />

            <TextView
                android:text="@string/enable_sd_card"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:textSize="22px"
                android:gravity="center" />

        </LinearLayout>
    </FrameLayout>

    <LinearLayout
        android:id="@+id/moving_operation_bar"  【移动文件的确认栏】
        android:visibility="gone"
        style="@style/BottomButtonBar">

        <Button
            android:id="@+id/button_moving_confirm"
            style="@style/BottomButton.Left"
            android:text="@string/operation_paste" />

        <Button
            android:id="@+id/button_moving_cancel"
            style="@style/BottomButton.Right"
            android:text="@string/operation_cancel" />
    </LinearLayout>

    <LinearLayout
        android:id="@+id/pick_operation_bar"
        android:visibility="gone"
        style="@style/BottomButtonBar">

        <Button
            android:id="@+id/button_pick_confirm"
            style="@style/BottomButton.Left"
            android:text="@string/confirm" />

        <Button
            android:id="@+id/button_pick_cancel"
            style="@style/BottomButton.Right"
            android:text="@string/operation_cancel" />
    </LinearLayout>

    <LinearLayout
        android:id="@+id/operation_bar"          【选中 CheckBox 时  底层 出现的 操作栏  尼玛没有文字  只有图片 这也可以?】
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:visibility="gone"
        android:background="@drawable/operation_bar_bg">
        <TextView
            android:id="@+id/button_operation_delete"
            android:text="@string/operation_delete"
            android:drawableTop="@drawable/operation_button_delete"
            style="@style/FileViewOperationButtonStytle" />
        <TextView
            android:id="@+id/button_operation_copy"
            android:drawableTop="@drawable/operation_button_copy"
            android:text="@string/operation_copy"
            style="@style/FileViewOperationButtonStytle" />
        <TextView
            android:id="@+id/button_operation_move"
            android:drawableTop="@drawable/operation_button_move"
            android:text="@string/operation_move"
            style="@style/FileViewOperationButtonStytle" />
        <TextView
            android:id="@+id/button_operation_send"
            android:drawableTop="@drawable/operation_button_send"
            android:text="@string/operation_send"
            style="@style/FileViewOperationButtonStytle" />
        <TextView
            android:id="@+id/button_operation_cancel"
            android:drawableTop="@drawable/operation_button_cancel"
            android:text="@string/operation_cancel"
            style="@style/FileViewOperationButtonStytle" />
    </LinearLayout>
</LinearLayout>



```

##### ServerControlActivity布局





```
FileExplorer\app\src\main\res\layout\server_control_activity.xml


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View mRootView = inflater.inflate(R.layout.server_control_activity, container, false);
       return mRootView;
}


<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dip"
        android:layout_weight="1"
        android:orientation="vertical"
        android:gravity="center">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/wifi_state" 【WiFi状态】
            android:layout_gravity="center_horizontal"
            android:singleLine="true"
            style="?android:attr/textAppearanceMedium" />
        <TextView
            android:id="@+id/wifi_state"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/wifi_state"  【WiFi状态】
            android:layout_gravity="center_horizontal"
            android:singleLine="true"
            android:padding="5px"
            style="?android:attr/textAppearanceSmall"/>
        <ImageView
            android:id="@+id/wifi_state_image"
            android:src="@drawable/wifi_state4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal" />
    </LinearLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:orientation="vertical"
        android:layout_height="wrap_content"
        android:gravity="bottom">
        <TextView
            android:id="@+id/instruction_pre"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:layout_gravity="center_horizontal"
            android:text="@string/instruction_pre" 【启动后可以从电脑端远程管理手机文件】
            android:visibility="gone"
            style="?android:attr/textAppearanceSmall" />
        <TextView
            android:id="@+id/instruction"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:layout_gravity="center_horizontal"
            android:text="@string/instruction"      【请在“我的电脑”地址栏中输入：】
            style="?android:attr/textAppearanceSmall" />

        <TextView
            android:id="@+id/ip_address"  【IP地址  10.xxx.xxx.xxx】
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:minWidth="150px"
            android:layout_gravity="center_horizontal"
            android:background="@drawable/ipaddress_bg"
            android:textStyle="bold"
            android:singleLine="true"
            android:textSize="32px"
            android:gravity="center"
            android:layout_marginTop="15px"
            android:paddingLeft="15px"
            android:paddingRight="15px"
            android:textColor="#ffffff" />

        <LinearLayout
            android:id="@+id/start_stop_button"
            android:layout_height="wrap_content"
            android:layout_width="274px"
            android:layout_gravity="center_horizontal"
            android:gravity="center"
            android:layout_marginTop="20px"
            android:layout_marginBottom="20px"
            android:background="@drawable/btn_bottom_last">
            <TextView
                android:id="@+id/start_stop_button_text"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:textSize="28px"
                android:drawablePadding="10px"
                android:gravity="center" />
        </LinearLayout>
    </LinearLayout>
</LinearLayout>


```




### FileExplorerTabActivity的Fragment类分析

#### FileCategoryActivity extends Fragment
##### Object的普通属性
FileCategoryActivity  的普通属性
```
public class FileCategoryActivity extends Fragment implements IFileInteractionListener, FavoriteDatabaseListener, IBackPressedListener {
    
// 用于保存  文件类型FileCategory【key】===文件类型对应的索引 Interger【Value】的Map类型数据
    private HashMap<FileCategory, Integer> categoryIndex = new HashMap<FileCategory, Integer>();  

    private FileListCursorAdapter mAdapter; 【当前文件列表数据索引Adapter】

    private FileViewInteractionHub mFileViewInteractionHub; 【 与用户交互的接口汇总? 】

    private FileCategoryHelper mFileCagetoryHelper; 【 用于协助 文件分类的类 】

    private FileIconHelper mFileIconHelper; 【 用于获取文件图标的类 】

    private CategoryBar mCategoryBar;  【用于显示 文件分类Item 的 视图容器】

//Intent.ACTION_MEDIA_SCANNER_FINISHED);
//Intent.ACTION_MEDIA_MOUNTED);
//Intent.ACTION_MEDIA_UNMOUNTED);
    private ScannerReceiver mScannerReceiver; 【ScannerReceiver extends BroadcastReceiver 是一个监听系统 卸载SD 转载SD 扫描多媒体的 广播监听器】

    private FavoriteList mFavoriteList;  // 用户点击收藏的文件集合 包含数据ArrayList<FavoriteItem>

 // 标记当前ViewPage显示的内容  enum ViewPage {  Home, Favorite, Category, NoSD, Invalid  }   
// 正在Home主页面内容  正在Favorite喜爱页面内容  正在Category页面内容  正在NoSD页面内容  Invalid无效内容标记  
    private ViewPage curViewPage = ViewPage.Invalid; 

    private ViewPage preViewPage = ViewPage.Invalid;

    private Activity mActivity;  // 保存当前调用Fragment的Activity   用来与该Activity交互

    private View mRootView;   // 当前 Fragment 加载的xml资源视图

    private FileViewActivity mFileViewActivity;  【 用户点击Category 进入到的 文件Fragment 对应 FileViewActivity 它有增删改查显示File的能力】

    private boolean mConfigurationChanged = false;  //  依据是否切屏 赋值 然后判断是否 重新初始化 当前 Fragment

    private static final int MSG_FILE_CHANGED_TIMER = 100;  // 用于在两个线程handler发送Message时保存在Message 的 用于标记Message的事件的标记值
    private Timer timer;  【 用于延迟执行 发送Message给handler 的消息的操作 timer.schedule(XXX,1000）; 】


// setupClick(R.id.category_music);        

//private void setupClick(int id) {View button = mRootView.findViewById(id);
//        button.setOnClickListener(onClickListener);
//    }

    View.OnClickListener onClickListener = new View.OnClickListener() { //CategoryBar 中  文件分类Item 的点击处理函数
        @Override
        public void onClick(View v) {
            FileCategory f = button2Category.get(v.getId());
            if (f != null) {
                onCategorySelected(f);  【调用该函数 来进入 FileViewActivity 依据参数 v.getId()-->FileCategory 得到要显示什么类型的数据  】
                if (f != FileCategory.Favorite) {   【用户Favorite 的 FileViewActivity 界面 没有 Menu菜单】
                    setHasOptionsMenu(true);
                }
            }
        }

    };


    private Handler handler = new Handler() {  //  定义在主线程的 用于非UI线程发送的消息Message 并在主线程执行UI更新操作
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FILE_CHANGED_TIMER:
                    updateUI();
                    break;
            }
            super.handleMessage(msg);
        }

    };


```

##### Class的类Class静态属性
FileCategoryActivity 定义的Class的类Class静态属性
```
public class FileCategoryActivity extends Fragment implements IFileInteractionListener,
        FavoriteDatabaseListener, IBackPressedListener {

    private static final String LOG_TAG = "FileCategoryActivity";   【Log标记】
    private static final int MSG_FILE_CHANGED_TIMER = 100;    【Message的标记】

【 用于保存CategoryBar 中 按钮id 和 该按钮所代表的的 Category分类 一对一关系】
    private static HashMap<Integer, FileCategory> button2Category = new HashMap<Integer, FileCategory>();  

   【public enum FileCategory {All, Music, Video, Picture, Theme, Doc, Zip, Apk, Custom, Other, Favorite }】

```

#####  对象Object的普通方法
FileCategoryActivity 定义的对象Object的普通方法
```

    public void setConfigurationChanged(boolean changed) {  【让外界能设置变量成员mConfigurationChanged 】
        mConfigurationChanged = changed;
    }



    private void registerScannerReceiver() {   【注册广播 监听 SD卡 装载卸载】
        mScannerReceiver = new ScannerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addDataScheme("file");
        mActivity.registerReceiver(mScannerReceiver, intentFilter);
    }



    private void setupCategoryInfo() { 【初始化 mCategoryBar 填充图片资源ID 填充分类-索引对应的Map HashMap<FileCategory, Integer> 】
        mFileCagetoryHelper = new FileCategoryHelper(mActivity);

        mCategoryBar = (CategoryBar) mRootView.findViewById(R.id.category_bar);
        int[] imgs = new int[] {
                R.drawable.category_bar_music, R.drawable.category_bar_video,
                R.drawable.category_bar_picture, R.drawable.category_bar_theme,
                R.drawable.category_bar_document, R.drawable.category_bar_zip,
                R.drawable.category_bar_apk, R.drawable.category_bar_other
        };

        for (int i = 0; i < imgs.length; i++) {
            mCategoryBar.addCategory(imgs[i]);
        }

        for (int i = 0; i < FileCategoryHelper.sCategories.length; i++) {
            categoryIndex.put(FileCategoryHelper.sCategories[i], i);
        }
    }




    public class CategoryInfo {
        public long count;  // 对应 分类文件的 数量 
        public long size; // 对应 分类文件的 存储大小
    }

    public void refreshCategoryInfo() {  【 更新 分类Fragment 显示的内容】
SDCardInfo sdCardInfo = Util.getSDCardInfo();  // 获取SD卡的信息
sdCardInfo.total；
sdCardInfo.free；

        for (FileCategory fc : FileCategoryHelper.sCategories) {
            CategoryInfo categoryInfo = mFileCagetoryHelper.getCategoryInfos().get(fc);
            setCategoryCount(fc, categoryInfo.count);  【设置对应分类的 TextView 显示分类的数量】
            setCategorySize(fc, categoryInfo.size);  【 设置底部 显示的  Music: 300MB  等的存储信息】
            setCategoryBarValue(fc, categoryInfo.size);  【把 每个Category的size 设置到CategoryBar 显示自定义的 InfomationBar 】
            size += categoryInfo.size;      【计算所有分类文件的大小】
          }

            long otherSize = sdCardInfo.total - sdCardInfo.free - size;   【计算 Other 其余文件的大小 】
            setCategorySize(FileCategory.Other, otherSize);  【设置其余分类文件的大小 TextView 】
            setCategoryBarValue(FileCategory.Other, otherSize); 【设置其余分类文件的大小 InfomationBar  】
            setCategoryCount(FileCategory.Favorite, mFavoriteList.getCount());  【显示 用户收藏文件的大小  自定义】

}


    public enum ViewPage {
        Home, Favorite, Category, NoSD, Invalid
    }

    private void showPage(ViewPage p) {  【 依据当前页面枚举Enum 来决定显示什么控件 】
        if (curViewPage == p) return;

        curViewPage = p;  // 保存到 当前 页面类型Enum

        showView(R.id.file_path_list, false);
        showView(R.id.navigation_bar, false);
        showView(R.id.category_page, false);
        showView(R.id.operation_bar, false);
        showView(R.id.sd_not_available_page, false);
        mFavoriteList.show(false);
        showEmptyView(false);   【 首先把所有控件 都不可见 】
        switch (p) {  【 依据页面类型Enum  来决定是否显示控件  】
            case Home:   
                showView(R.id.category_page, true);
                if (mConfigurationChanged) {
                    ((FileExplorerTabActivity) mActivity).reInstantiateCategoryTab();   【分类首页 判断boolean 后 重新载入】
                    mConfigurationChanged = false;
                }
                break;
            case Favorite:
                showView(R.id.navigation_bar, true);       【 个人收藏页面  显示导航栏navigation_bar  显示收藏列表mFavoriteList  数量为空判断是否显示空】
                mFavoriteList.show(true);
                showEmptyView(mFavoriteList.getCount() == 0);
                break;
            case Category:                                  【 进入分类详细File页面  显示导航栏navigation_bar  显示file_path_list当前路径的所有文件  数量为空判断是否显示空    】
                showView(R.id.navigation_bar, true);
                showView(R.id.file_path_list, true);
                showEmptyView(mAdapter.getCount() == 0);
                break;
            case NoSD:
                showView(R.id.sd_not_available_page, true);    【 没有SD卡 显示 id.sd_not_available_page】
                break;
        }
    }


    private void showEmptyView(boolean show) {   【依据参数 决定是否 显示 empty_view 这个控件 】
        View emptyView = mActivity.findViewById(R.id.empty_view);
        if (emptyView != null)
            emptyView.setVisibility(show ? View.VISIBLE : View.GONE);   
    }

    private void showView(int id, boolean show) { 【 依据 id 和 参数bool 控制 id对应view的显示  复用】
        View view = mRootView.findViewById(id);
        if (view != null) {
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }




    private void setCategoryCount(FileCategory fc, long count) {  【 依据 FileCategory 枚举Enum 来得到 对应的TextView控件ID ，并显示该分类文件数量】
        int id = getCategoryCountId(fc);
        if (id == 0)
            return;

        setTextView(id, "(" + count + ")");
    }




    private void onCategorySelected(FileCategory f) {   【当首页Home的分类文件Item 被点击执行的方法】
        if (mFileCagetoryHelper.getCurCategory() != f) {    【当 当前选中的FileCategory 和已保存的 对比  ，如果不同 那么更新以保存的 FileCategory】
            mFileCagetoryHelper.setCurCategory(f); 【 设置 FileView 视图当前 路径Path 为  当前根目录( 分类浏览 )+ 当前分类目录( 音乐，文档，主题，安装包 ...)】
            mFileViewInteractionHub.setCurrentPath(mFileViewInteractionHub.getRootPath() + getString(mFileCagetoryHelper.getCurCategoryNameResId()));
            mFileViewInteractionHub.refreshFileList();  // 以新的目录 更新文件列表
        }

        if (f == FileCategory.Favorite) {  // 如果是收藏分类  那么显示  ViewPage.Favorite 这个页面 
            showPage(ViewPage.Favorite);
        } else {
            showPage(ViewPage.Category);  【 其余 显示 分类详情页面 】  enum ViewPage {  Home, Favorite, Category, NoSD, Invalid  }  
        }
    }



    private void setupClick() {
        setupClick(R.id.category_music);
        setupClick(R.id.category_video);
        setupClick(R.id.category_picture);
        setupClick(R.id.category_theme);
        setupClick(R.id.category_document);
        setupClick(R.id.category_zip);
        setupClick(R.id.category_apk);
        setupClick(R.id.category_favorite);
    }
    private void setupClick(int id) {  // 把对应id的控件 设置点击事件 
        View button = mRootView.findViewById(id);
        button.setOnClickListener(onClickListener);  【 设置 Home 页面的 分类Item 的 点击事件】
    }

    View.OnClickListener onClickListener = new View.OnClickListener() { //CategoryBar 中  文件分类Item 的点击处理函数
        @Override
        public void onClick(View v) {
            FileCategory f = button2Category.get(v.getId());
            if (f != null) {
                onCategorySelected(f);  【调用该函数 来进入 FileViewActivity 依据参数 v.getId()-->FileCategory 得到要显示什么类型的数据  】
                if (f != FileCategory.Favorite) {   【用户Favorite 的 FileViewActivity 界面 没有 Menu菜单】
                    setHasOptionsMenu(true);
                }
            }
        }

    };


    public boolean isHomePage() {   【对外提供函数 是否为Home 页面】
        return curViewPage == ViewPage.Home;
    }



    private void refreshList() {
        mFileViewInteractionHub.refreshFileList();  // 调用 File列表的 刷新函数 将以当前设置的path 去刷新列表
    }



    private void copyFileInFileView(ArrayList<FileInfo> files) { // 【 当 在Category 页面 复制文件时 调用，复制按钮点击之后将切入到Tab_index2 文件管理界面 去选择 要粘贴的位置】 
        if (files.size() == 0) return;
        mFileViewActivity.copyFile(files); //【 把 要复制的文件 传递到 FileView 】
        mActivity.getActionBar().setSelectedNavigationItem(Util.SDCARD_TAB_INDEX);  【 页面跳转到Tab-文件管理页面中去】 程序出错 需要除 Bug
    }



    private void startMoveToFileView(ArrayList<FileInfo> files) {// 【 当 在Category 页面 剪切文件时 调用，剪切按钮点击之后将切入到Tab_index2 文件管理界面 去选择 要粘贴的位置】 
        if (files.size() == 0) return;
        mFileViewActivity.moveToFile(files);  //【把 要剪切的文件 传递到 FileView 】
        mActivity.getActionBar().setSelectedNavigationItem(Util.SDCARD_TAB_INDEX);
    }



    private void setCategorySize(FileCategory fc, long size) {   【 复用函数  用来设置Home 页面的 底部 文件类型 存储大小的通用函数 】
        int txtId = 0;
        int resId = 0;
        switch (fc) {
            case Music:
                txtId = R.id.category_legend_music;
                resId = R.string.category_music;
                break;
            case Video:
                txtId = R.id.category_legend_video;
                resId = R.string.category_video;
                break;
            case Picture:
                txtId = R.id.category_legend_picture;
                resId = R.string.category_picture;
                break;
            case Theme:
                txtId = R.id.category_legend_theme;
                resId = R.string.category_theme;
                break;
            case Doc:
                txtId = R.id.category_legend_document;
                resId = R.string.category_document;
                break;
            case Zip:
                txtId = R.id.category_legend_zip;
                resId = R.string.category_zip;
                break;
            case Apk:
                txtId = R.id.category_legend_apk;
                resId = R.string.category_apk;
                break;
            case Other:
                txtId = R.id.category_legend_other;
                resId = R.string.category_other;
                break;
        }

        if (txtId == 0 || resId == 0)
            return;

        setTextView(txtId, getString(resId) + ":" + Util.convertStorage(size));
    }


【 用来设置 自定义视图CategoryBar 这个类似InfomationBar的 对应分类所占大小 ，它会以百分比显示】
    private void setCategoryBarValue(FileCategory f, long size) {   
        if (mCategoryBar == null) {
            mCategoryBar = (CategoryBar) mRootView.findViewById(R.id.category_bar);
        }
        mCategoryBar.setCategoryValue(categoryIndex.get(f), size);
    }




    private void updateUI() { 【如果SD卡 没有 显示 NoSD页面  ，如果有SD卡 如果之前的页面不是 Invalid 那么 显示之前的页面，如果当前之前页面是Invalid NoSD那么显示Home页面】
        boolean sdCardReady = Util.isSDCardReady();
        if (sdCardReady) {
            if (preViewPage != ViewPage.Invalid) {
                showPage(preViewPage);
                preViewPage = ViewPage.Invalid;
            } else if (curViewPage == ViewPage.Invalid || curViewPage == ViewPage.NoSD) {
                showPage(ViewPage.Home);
            }
            refreshCategoryInfo();
            // refresh file list
            mFileViewInteractionHub.refreshFileList();
            // refresh file list view in another tab
            mFileViewActivity.refresh();
        } else {
            preViewPage = curViewPage;
            showPage(ViewPage.NoSD);
        }
    }





    synchronized public void notifyFileChanged() {  //【 使用定时器 去通知主线程 更新 文件列表】
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                timer = null;
                Message message = new Message();
                message.what = MSG_FILE_CHANGED_TIMER;
                handler.sendMessage(message);
            }

        }, 1000);
    }


```


#####  类Class的静态方法
FileCategoryActivity 定义的Class的静态方法
```

    private static int getCategoryCountId(FileCategory fc) { // 表示 文件分类FileCategory 这个枚举Enum 和 显示分类文件数量的TextView的一对一关系
        switch (fc) {
            case Music:
                return R.id.category_music_count;
            case Video:
                return R.id.category_video_count;
            case Picture:
                return R.id.category_picture_count;
            case Theme:
                return R.id.category_theme_count;
            case Doc:
                return R.id.category_document_count;
            case Zip:
                return R.id.category_zip_count;
            case Apk:
                return R.id.category_apk_count;
            case Favorite:
                return R.id.category_favorite_count;
        }

        return 0;
    }

```

##### 定义的接口
FileCategoryActivity 定义的接口
```
无

```


#####  实现的接口
FileCategoryActivity 对象实现的接口
```
public class FileCategoryActivity extends Fragment implements IFileInteractionListener,  FavoriteDatabaseListener, IBackPressedListener {}

接口1：
public interface IFileInteractionListener {

    public View getViewById(int id);  // 为 FileViewInteractionHub 提供可以依据id获得view的方法   对类FileViewInteractionHub暴露方法  使得它可以和FileCategoryActivity交互 
    public Context getContext();  // 
    public void startActivity(Intent intent);
    public void onDataChanged();
    public void onPick(FileInfo f);
    public boolean shouldShowOperationPane();

    /**
     * Handle operation listener.
     * @param id
     * @return true: indicate have operated it; false: otherwise.
     */
    public boolean onOperation(int id);
    public String getDisplayPath(String path);
    public String getRealPath(String displayPath);
    public void runOnUiThread(Runnable r);

    // return true indicates the navigation has been handled
    public boolean onNavigation(String path);
    public boolean shouldHideMenu(int menu);
    public FileIconHelper getFileIconHelper();
    public FileInfo getItem(int pos);
    public void sortCurrentList(FileSortHelper sort);
    public Collection<FileInfo> getAllFiles();
    public void addSingleFile(FileInfo file);
    public boolean onRefreshFileList(String path, FileSortHelper sort);
    public int getItemCount();
}
----------------------------
public class FileViewInteractionHub implements IOperationProgressListener {
    private IFileInteractionListener mFileViewListener;   【 FileViewInteractionHub 关联这个接口的实例，】
}

FileCategoryActivity implements IFileInteractionListener 是为了 
一：与FileViewInteractionHub 交互 通过接口的方式 解耦 对FileViewInteractionHub仅暴露部分定义的接口方法
二：FileViewInteractionHub 的某些状态需要传递到 FileCategoryActivity，FileCategoryActivity 接收到状态会通知其他与FileViewInteractionHub无关的控件 
    更新，FileViewInteractionHub只需管好自己的View，与其他View交互的动作交给 接口函数定义的FileCategoryActivity ， View与View之间解耦
----------------------------

IFileInteractionListener接口的实现函数

    @Override
    public View getViewById(int id) {
        return mRootView.findViewById(id);
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

public void startActivity(Intent intent);  // Activity实现

    @Override
    public void onDataChanged() { // 更新UI View的方法
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mFavoriteList.getArrayAdapter().notifyDataSetChanged();
                showEmptyView(mAdapter.getCount() == 0);
            }

        });
    }

    @Override
    public void onPick(FileInfo f) {  // do nothing }  【?? 不懂】


    @Override
    public boolean shouldShowOperationPane() { return true; }  // 是否应该显示文件操作菜单



    @Override
    public boolean onOperation(int id) {  【 文件操作菜单 复制 剪切 返回主界面 对应的操作函数 】
        mFileViewInteractionHub.addContextMenuSelectedItem();
        switch (id) {
            case R.id.button_operation_copy:
            case GlobalConsts.MENU_COPY:
                copyFileInFileView(mFileViewInteractionHub.getSelectedFileList());
                mFileViewInteractionHub.clearSelection();
                break;
            case R.id.button_operation_move:
            case GlobalConsts.MENU_MOVE:
                startMoveToFileView(mFileViewInteractionHub.getSelectedFileList());
                mFileViewInteractionHub.clearSelection();
                break;
            case GlobalConsts.OPERATION_UP_LEVEL:
                setHasOptionsMenu(false);
                showPage(ViewPage.Home);
                break;
            default:
                return false;
        }
        return true;
    }




    @Override
    public String getDisplayPath(String path) {  【获得当前展示的路径Path】
        return getString(R.string.tab_category) + path;
    }


    @Override
    public String getRealPath(String displayPath) {  【没有实现】
        return "";
    }


    @Override
    public void runOnUiThread(Runnable r) {  【运行在主线程 Runnable】使得FileViewInteractionHub 能更新自己控制的View
        mActivity.runOnUiThread(r);
    }



    @Override
    public boolean onNavigation(String path) { 【返回  显示主界面】
        showPage(ViewPage.Home);
        return true;
    }




    @Override
    public boolean shouldHideMenu(int menu) {   【是否 隐藏 文件操作菜单】
        return (menu == GlobalConsts.MENU_NEW_FOLDER || menu == GlobalConsts.MENU_FAVORITE
                || menu == GlobalConsts.MENU_PASTE || menu == GlobalConsts.MENU_SHOWHIDE);
    }


    @Override
    public FileIconHelper getFileIconHelper() {  【提供 Icon文件 图标获取的帮助类】
        return mFileIconHelper;
    }


    @Override
    public FileInfo getItem(int pos) {  【获得 对应位置的文件的 FileInfo Bean数据类】
        return mAdapter.getFileItem(pos);
    }




    @Override
    public void sortCurrentList(FileSortHelper sort) {  【排序当前的列表】
        refreshList();
    }



    @Override
    public Collection<FileInfo> getAllFiles() {   【获得当前路径下所有的 FileInfo 集合 】
        return mAdapter.getAllFiles();
    }



    @Override
    public void addSingleFile(FileInfo file) {     【新增文件  刷新界面】
        refreshList();
    }



    public boolean onRefreshFileList(String path, FileSortHelper sort) {  【以某个排序的方法 对路径下的文件进行排序】
        FileCategory curCategory = mFileCagetoryHelper.getCurCategory();
        if (curCategory == FileCategory.Favorite || curCategory == FileCategory.All)
            return false;

        Cursor c = mFileCagetoryHelper.query(curCategory, sort.getSortMethod());
        showEmptyView(c == null || c.getCount() == 0);
        mAdapter.changeCursor(c);

        return true;
    }



    @Override
    public int getItemCount() {     【获得当前 路径的文件的总数】
        return mAdapter.getCount();
    }





2. 接口2
    public interface FavoriteDatabaseListener【FavoriteDatabaseHelper】 {
        void onFavoriteDatabaseChanged();
    }


    @Override
    public void onFavoriteDatabaseChanged() {  【 主要是新增一个功能 使本类增加对对  用户收藏文件数据改变的事件处理  处理方式为：更新自身的 用户收藏文件个数信息】
        setCategoryCount(FileCategory.Favorite, mFavoriteList.getCount());
    }


3.接口3

    public interface IBackPressedListener {
        boolean onBack();
    }


    @Override
    public boolean onBack() {  【 新增 对 back事件的处理  】
        if (isHomePage() || curViewPage == ViewPage.NoSD || mFileViewInteractionHub == null) {
            return false;
        }

        return mFileViewInteractionHub.onBackPressed();
    }

```

##### 对象重载的父类方法
```

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 【 完成该Fragment的视图的加载 】
        mActivity = getActivity();
        mFileViewActivity = (FileViewActivity) ((FileExplorerTabActivity) mActivity)
                .getFragment(Util.SDCARD_TAB_INDEX);
        mRootView = inflater.inflate(R.layout.file_explorer_category, container, false); 【 从 xml文件加载视图 】
        curViewPage = ViewPage.Invalid;  【初始化变量 curViewPage 为 ViewPage.Invalid】
        mFileViewInteractionHub = new FileViewInteractionHub(this);  【 创建对象 FileViewInteractionHub】
        mFileViewInteractionHub.setMode(Mode.View);     //【选择模式为View 正常显示  Pick为选择目录粘贴的模式】  public enum Mode {  View, Pick };
        mFileViewInteractionHub.setRootPath("/");  【设置根目录】
        mFileIconHelper = new FileIconHelper(mActivity);   【创建文件图标帮助工具类】
        mFavoriteList = new FavoriteList(mActivity, (ListView) mRootView.findViewById(R.id.favorite_list), this, mFileIconHelper); 【创建用户收藏文件列表 FavoriteList 】
        mFavoriteList.initList();

        mAdapter = new FileListCursorAdapter(mActivity, null, mFileViewInteractionHub, mFileIconHelper);  【创建 文件列表 数据库cursor游标 Adapter】
        ListView fileListView = (ListView) mRootView.findViewById(R.id.file_path_list); 【从 xml视图中找到 文件列表ListView 设置它的Adapter 为 FileListCursorAdapter】
        fileListView.setAdapter(mAdapter);

        setupClick();  // 初始化 分类按钮的点击事件
        setupCategoryInfo(); 【初始化 mCategoryBar 填充图片资源ID 每个分类有一个对应的颜色的方框图片 填充分类-CategoryBar对应颜色索资源ID Map HashMap<FileCategory, Integer> 】
        updateUI(); 【如果SD卡 没有 显示 NoSD页面  ，如果有SD卡 如果之前的页面不是 Invalid 那么 显示之前的页面，如果当前之前页面是Invalid NoSD那么显示Home页面】 这里将显示Home页
        registerScannerReceiver();  【注册广播 】
        return mRootView;
    }





【 当页面在Category 和Favorite页面时 显示上次文菜单 enum ViewPage {  Home, Favorite, Category, NoSD, Invalid  }  】
【只会调用一次，他只会在Menu显示之前去调用一次，之后就不会在去调用。】
   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {  
        if (curViewPage != ViewPage.Category && curViewPage != ViewPage.Favorite) {
            return;
        }
        mFileViewInteractionHub.onCreateOptionsMenu(menu); 【依据当前页面类型 创建 menu菜单 】
    }




【onPrepareOptionsMenu是每次在display Menu之前，都会去调用，只要按一次Menu按鍵，就会调用一次。所以可以在这里动态的改变menu。】
【场景  在FileList页面 上下文菜单有Show Hiden File 菜单，点击之后，下次再点击上下文菜单  该项将显示 Hide Hiden File 动态改变菜单】
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!isHomePage() && mFileCagetoryHelper.getCurCategory() != FileCategory.Favorite) {
            mFileViewInteractionHub.onPrepareOptionsMenu(menu);
        }
    }


    // menu
    private static final int MENU_SEARCH = 1;
    // private static final int MENU_NEW_FOLDER = 2;
    private static final int MENU_SORT = 3;
    private static final int MENU_SEND = 7;
    private static final int MENU_RENAME = 8;
    private static final int MENU_DELETE = 9;
    private static final int MENU_INFO = 10;
    private static final int MENU_SORT_NAME = 11;
    private static final int MENU_SORT_SIZE = 12;
    private static final int MENU_SORT_DATE = 13;
    private static final int MENU_SORT_TYPE = 14;
    private static final int MENU_REFRESH = 15;
    private static final int MENU_SELECTALL = 16;
    private static final int MENU_SETTING = 17;
    private static final int MENU_EXIT = 18;


    public void onDestroy() {   【Fragment的析构函数 取消注册的广播】
        super.onDestroy();
        if (mActivity != null) {
            mActivity.unregisterReceiver(mScannerReceiver);
        }
    }

```

##### 对象使用到的工具类成员属性
FileCategoryActivity 使用到的工具类成员属性分析

```

   private FileListCursorAdapter mAdapter; 【当前文件列表数据索引Adapter】

   private FileViewInteractionHub mFileViewInteractionHub; 【 与用户交互的接口汇总? 】

    private FileCategoryHelper mFileCagetoryHelper; 【 用于协助 文件分类的类 】

    private FileIconHelper mFileIconHelper; 【 用于获取文件图标的类 】

    private CategoryBar mCategoryBar;  【用于显示 文件分类Item 的 视图容器】


    private FavoriteList mFavoriteList;  // 用户点击收藏的文件集合 包含数据ArrayList<FavoriteItem>

    private FileViewActivity mFileViewActivity;  【 用户点击Category 进入到的 文件Fragment 对应 FileViewActivity 它有增删改查显示File的能力】

```

###### FileViewInteractionHub分析
```
//  FileViewInteractionHub  是 MVP架构里面的 Presenter 对象的作用      
//  FileCategoryActivity extends Fragment implements IFileInteractionListener      这个接口 IFileInteractionListener  就是一个 ViewInterface的作用
//  FileOperationHelper   FileSortHelper  ArrayList<FileInfo> mCheckedFileNameList     这几个就是 Model的角色

public class FileViewInteractionHub implements IOperationProgressListener {
private String mCurrentPath;   【保存当前文件夹路径】 
private ArrayList<FileInfo> mCheckedFileNameList = new ArrayList<FileInfo>();   【当前路径下的 已选中文件的集合】
}


 1.UserAction ---> 2.View ---> 3.Presenter 《====》4.Model的处理过程
~~~~~~~~~~~~~~~~~~~~~~~~~~~~1.Begin~~~~~~~~~~~~~~~~~~~~~~
1. 分类按钮的点击事件 【UserAction】  ---> 
2. Fragment.onClickListener.onClick( onCategorySelected（） ) 【View】 ----> 
3. 
  mFileViewInteractionHub.setCurrentPath()【mCurrentPath 保存当前文件夹路径】 这步完成了Model的操作    
  mFileViewInteractionHub.refreshFileList()  【Presenter】 ---->  完成Model 操作后的View视图的更新


    public void refreshFileList() {
        clearSelection();
        updateNavigationPane();
        mFileViewListener.onRefreshFileList(mCurrentPath, mFileSortHelper); // onRefreshFileList returns true indicates list has changed
        updateConfirmButtons(); // update move operation button state

    }



// private ArrayList<FileInfo> mCheckedFileNameList = new ArrayList<FileInfo>();   【当前路径下的 已选中文件的集合】
    public void clearSelection() {  // 清空已选中的文件  修改标识Flag   boolean  FileInfo.Selected
        if (mCheckedFileNameList.size() > 0) {
            for (FileInfo f : mCheckedFileNameList) {
                if (f == null) {
                    continue;
                }
                f.Selected = false;
            }
            mCheckedFileNameList.clear();
            mFileViewListener.onDataChanged();
        }
    }





    private void updateNavigationPane() {
        View upLevel = mFileViewListener.getViewById(R.id.path_pane_up_level);  // 获得返回上级路径的 ImageView
        upLevel.setVisibility(mRoot.equals(mCurrentPath) ? View.INVISIBLE : View.VISIBLE);  // 当当前目录不是根目录   显示 返回上级路径

        View arrow = mFileViewListener.getViewById(R.id.path_pane_arrow);  // 获得下拉图标 展示路径
        arrow.setVisibility(mRoot.equals(mCurrentPath) ? View.GONE : View.VISIBLE);

        mNavigationBarText.setText(mFileViewListener.getDisplayPath(mCurrentPath));  // 导航页面显示的当前路径
    }



    public boolean onRefreshFileList(String path, FileSortHelper sort)【FileCategoryActivity 实现的接口方法   更新界面的path对应的 cursor文件】{
        FileCategory curCategory = mFileCagetoryHelper.getCurCategory();
        if (curCategory == FileCategory.Favorite || curCategory == FileCategory.All)
            return false;

        Cursor c = mFileCagetoryHelper.query(curCategory, sort.getSortMethod());
        showEmptyView(c == null || c.getCount() == 0);
        mAdapter.changeCursor(c);

        return true;
    }



    private void updateConfirmButtons() {
        if (mConfirmOperationBar.getVisibility() == View.GONE) //  如果 底部确认移动的 bar 不可见 那么就不用更新  ConfirmBar不可见 那么就不用更新   
            return;

        Button confirmButton = (Button) mConfirmOperationBar.findViewById(R.id.button_moving_confirm);  // 如果可见  就设置 OK_Button显示的文字Text
        int text = R.string.operation_paste;
        if (isSelectingFiles()) {
            confirmButton.setEnabled(mCheckedFileNameList.size() != 0);  // 当前 ArrayList<FileInfo>();   【当前路径下的 已选中文件的集合】 不为空  那么设置它为 可点击enable
            text = R.string.operation_send;
        } else if (isMoveState()) {
            confirmButton.setEnabled(mFileOperationHelper.canMove(mCurrentPath));
        }

        confirmButton.setText(text);
    }


~~~~~~~~~~~~~~~~~~~~~~~~~~~~1.End~~~~~~~~~~~~~~~~~~~~~~


~~~~~~~~~~~~~~~~~~~~~~2.Begin~~~~~~~~~~~~~~~~~~~~~~
1. 返回按钮的点击事件 

    @Override
    public void onBackPressed() { 【FileExplorerTabActivity 的返回按钮点击事件处理 】
        IBackPressedListener backPressedListener = (IBackPressedListener) mTabsAdapter
                .getItem(mViewPager.getCurrentItem());
        if (!backPressedListener.onBack()) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
        }
    }

2. Fragment.onBack( FileViewInteractionHub.onBackPressed() ) 【View】 ----> 
    @Override
    public boolean onBack() {  【  】
        if (isHomePage() || curViewPage == ViewPage.NoSD || mFileViewInteractionHub == null) {
            return false;
        }

        return mFileViewInteractionHub.onBackPressed(); 【mFileViewInteractionHub 的返回按钮处理函数】
    }



3. Presenter的处理函数逻辑        
    如果下拉框可见  那么处理函数就是把下拉框隐藏
    public boolean onBackPressed() {
        if (mDropdownNavigation.getVisibility() == View.VISIBLE) {
            mDropdownNavigation.setVisibility(View.GONE);
        } else if (isInSelection()) {  // 如果处于选择文件状态  那么就清除选择  mCheckedFileNameList ArrayList<FileInfo>()
            clearSelection();
        } else if (!onOperationUpLevel()) {
            return false;
        }
        return true;
    }




    public boolean onOperationUpLevel() {
        showDropdownNavigation(false);

        if (mFileViewListener.onOperation(GlobalConsts.OPERATION_UP_LEVEL)) {
            return true;
        }

        if (!mRoot.equals(mCurrentPath)) {
            mCurrentPath = new File(mCurrentPath).getParent();
            refreshFileList();
            return true;
        }

        return false;
    }


    public boolean isInSelection() {  【判断是否在选择文件状态】
        return mCheckedFileNameList.size() > 0;
    }



   public boolean onOperationUpLevel() {  【操作返回上级目录的逻辑】
        showDropdownNavigation(false);

        if (mFileViewListener.onOperation(GlobalConsts.OPERATION_UP_LEVEL)) {
            return true;
        }

        if (!mRoot.equals(mCurrentPath)) {
            mCurrentPath = new File(mCurrentPath).getParent();
            refreshFileList();
            return true;
        }

        return false;
    }


~~~~~~~~~~~~~~~~~~~~~~2.End~~~~~~~~~~~~~~~~~~~~~~
 


~~~~~~~~~~~~~~~~~~~~~~3.Begin~~~~~~~~~~~~~~~~~~~~~~
1. 用户点击ActionMode 中的  更多: 按钮时弹出的MenuItem    

2. Fragment.onCreateOptionsMenu( FileViewInteractionHub.onCreateOptionsMenu  )        View---->Present
3. Fragment.onPrepareOptionsMenu( FileViewInteractionHub.onPrepareOptionsMenu  )
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (curViewPage != ViewPage.Category && curViewPage != ViewPage.Favorite) {
            return;
        }
        mFileViewInteractionHub.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!isHomePage() && mFileCagetoryHelper.getCurCategory() != FileCategory.Favorite) {
            mFileViewInteractionHub.onPrepareOptionsMenu(menu);
        }
    }





    public boolean onCreateOptionsMenu(Menu menu) {  【 添加Menu 】
        clearSelection();
        showDropdownNavigation(false);

        // menu.add(0, MENU_SEARCH, 0,
        // R.string.menu_item_search).setOnMenuItemClickListener(
        // menuItemClick);
        addMenuItem(menu, MENU_SELECTALL, 0, R.string.operation_selectall,
                R.drawable.ic_menu_select_all);

        SubMenu sortMenu = menu.addSubMenu(0, MENU_SORT, 1, R.string.menu_item_sort).setIcon(
                R.drawable.ic_menu_sort);
        addMenuItem(sortMenu, MENU_SORT_NAME, 0, R.string.menu_item_sort_name);
        addMenuItem(sortMenu, MENU_SORT_SIZE, 1, R.string.menu_item_sort_size);
        addMenuItem(sortMenu, MENU_SORT_DATE, 2, R.string.menu_item_sort_date);
        addMenuItem(sortMenu, MENU_SORT_TYPE, 3, R.string.menu_item_sort_type);
        sortMenu.setGroupCheckable(0, true, true);
        sortMenu.getItem(0).setChecked(true);

        // addMenuItem(menu, GlobalConsts.MENU_PASTE, 2,
        // R.string.operation_paste);
        addMenuItem(menu, GlobalConsts.MENU_NEW_FOLDER, 3, R.string.operation_create_folder,
                R.drawable.ic_menu_new_folder);
        addMenuItem(menu, GlobalConsts.MENU_FAVORITE, 4, R.string.operation_favorite,
                R.drawable.ic_menu_delete_favorite);
        addMenuItem(menu, GlobalConsts.MENU_SHOWHIDE, 5, R.string.operation_show_sys,
                R.drawable.ic_menu_show_sys);
        addMenuItem(menu, MENU_REFRESH, 6, R.string.operation_refresh,
                R.drawable.ic_menu_refresh);
        addMenuItem(menu, MENU_SETTING, 7, R.string.menu_setting, drawable.ic_menu_preferences);
        addMenuItem(menu, MENU_EXIT, 8, R.string.menu_exit, drawable.ic_menu_close_clear_cancel);
        return true;
    }




    public boolean onPrepareOptionsMenu(Menu menu) {
        updateMenuItems(menu);
        return true;
    }



    private void addMenuItem(Menu menu, int itemId, int order, int string, int iconRes) {
        if (!mFileViewListener.shouldHideMenu(itemId)) {
            MenuItem item = menu.add(0, itemId, order, string).setOnMenuItemClickListener(menuItemClick);   【为上下文按钮提供点击处理】
            if (iconRes > 0) {
                item.setIcon(iconRes);
            }
        }
    }


 
    private void updateMenuItems(Menu menu) {   【动态改变Menu 显示的Text 以及对应的点击事件 】
        menu.findItem(MENU_SELECTALL).setTitle(
                isSelectedAll() ? R.string.operation_cancel_selectall : R.string.operation_selectall);
        menu.findItem(MENU_SELECTALL).setEnabled(mCurrentMode != Mode.Pick);

        MenuItem menuItem = menu.findItem(GlobalConsts.MENU_SHOWHIDE);
        if (menuItem != null) {
            menuItem.setTitle(Settings.instance().getShowDotAndHiddenFiles() ? R.string.operation_hide_sys
                    : R.string.operation_show_sys);
        }

        FavoriteDatabaseHelper databaseHelper = FavoriteDatabaseHelper.getInstance();
        if (databaseHelper != null) {
            MenuItem item = menu.findItem(GlobalConsts.MENU_FAVORITE);
            if (item != null) {
                item.setTitle(databaseHelper.isFavorite(mCurrentPath) ? R.string.operation_unfavorite
                        : R.string.operation_favorite);
            }
        }

    }






    private OnMenuItemClickListener menuItemClick = new OnMenuItemClickListener() {  【FileViewInteractionHub 上下文菜单点击处理事件】

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            mListViewContextMenuSelectedItem = info != null ? info.position : -1;

            int itemId = item.getItemId();
            if (mFileViewListener.onOperation(itemId)) {
                return true;
            }

            addContextMenuSelectedItem();

            switch (itemId) {
                case MENU_SEARCH:
                    onOperationSearch();
                    break;
                case GlobalConsts.MENU_NEW_FOLDER:
                    onOperationCreateFolder();
                    break;
                case MENU_REFRESH:
                    onOperationReferesh();
                    break;
                case MENU_SELECTALL:
                    onOperationSelectAllOrCancel();
                    break;
                case GlobalConsts.MENU_SHOWHIDE:
                    onOperationShowSysFiles();
                    break;
                case GlobalConsts.MENU_FAVORITE:
                    onOperationFavorite();
                    break;
                case MENU_SETTING:
                    onOperationSetting();
                    break;
                case MENU_EXIT:
                    ((FileExplorerTabActivity) mContext).finish();
                    break;
                // sort
                case MENU_SORT_NAME:
                    item.setChecked(true);
                    onSortChanged(SortMethod.name);
                    break;
                case MENU_SORT_SIZE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.size);
                    break;
                case MENU_SORT_DATE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.date);
                    break;
                case MENU_SORT_TYPE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.type);
                    break;

                case GlobalConsts.MENU_COPY:
                    onOperationCopy();
                    break;
                case GlobalConsts.MENU_COPY_PATH:
                    onOperationCopyPath();
                    break;
                case GlobalConsts.MENU_PASTE:
                    onOperationPaste();
                    break;
                case GlobalConsts.MENU_MOVE:
                    onOperationMove();
                    break;
                case MENU_SEND:
                    onOperationSend();
                    break;
                case MENU_RENAME:
                    onOperationRename();
                    break;
                case MENU_DELETE:
                    onOperationDelete();
                    break;
                case MENU_INFO:
                    onOperationInfo();
                    break;
                default:
                    return false;
            }

            mListViewContextMenuSelectedItem = -1;
            return true;
        }

    };


~~~~~~~~~~~~~~~~~~~~~~3.End~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~4.Begin 返回上级目录按钮   当前目录导航按钮  移动文件确认按钮  移动文件取消按钮的处理函数~~~~~~~~~~~~~~~~~~~~~~

FileViewInteractionHub 处理的 FileListView的  【设置 当前路径pane的点击函数】【设置 返回上一级的点击处理函数】 【设置 确认框的 ok  cancel 函数】

View clickable = mFileViewListener.getViewById(R.id.current_path_pane);
clickable.setOnClickListener(buttonClick);  【设置 当前路径pane的点击函数】 


setupClick(mNavigationBar, R.id.path_pane_up_level);  【设置 返回上一级的点击处理函数】



    private void setupOperationPane() {
        mConfirmOperationBar = mFileViewListener.getViewById(R.id.moving_operation_bar);
        setupClick(mConfirmOperationBar, R.id.button_moving_confirm); 【设置 确认框的 ok  cancel 函数】 
        setupClick(mConfirmOperationBar, R.id.button_moving_cancel);
    }




    private void setupClick(View v, int id) {
        View button = (v != null ? v.findViewById(id) : mFileViewListener.getViewById(id));
        if (button != null)
            button.setOnClickListener(buttonClick);  【设置处理函数】
    }






    private View.OnClickListener buttonClick = new View.OnClickListener() {  【 FileList界面 的点击事件处理】
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_operation_copy: 
                    onOperationCopy();
                    break;
                case R.id.button_operation_move: 
                    onOperationMove();
                    break;
                case R.id.button_operation_send: 
                    onOperationSend();
                    break;
                case R.id.button_operation_delete: 
                    onOperationDelete();
                    break;
                case R.id.button_operation_cancel: 
                    onOperationSelectAllOrCancel();
                    break;
                case R.id.current_path_pane:    【用户点击 导航Bar的处理】
                    onNavigationBarClick();
                    break;
                case R.id.button_moving_confirm: 【用户点击 确认框 确认按钮的处理 】
                    onOperationButtonConfirm();
                    break;
                case R.id.button_moving_cancel: 【用户点击 确认框 取消按钮的处理 】
                    onOperationButtonCancel();
                    break;
                case R.id.path_pane_up_level: 【用户点击 FileList界面  返回上级界面的处理函数】
                    onOperationUpLevel();   // 显示当前目录的上级目录的方法     在分类界面的话  点击back 按钮就直接回到 Home目录
                    ActionMode mode = ((FileExplorerTabActivity) mContext).getActionMode();
                    if (mode != null) {
                        mode.finish();
                    }
                    break;
            }
        }

    };

~~~~~~~~~~~~~~~~~~~~~~4.End~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~5.Begin 【FileListItem】   用户点击FileListItem的 checkbox时的处理 ~~~~~~~~~~~~~~~~~~~~~~


    public View getView(int position, View convertView, ViewGroup parent) { 【FileListAdapter】
// 在Adapter的 getView 方法中为  Item的 方框设置点击事件处理  FileListAdapter
view.findViewById(R.id.file_checkbox_area).setOnClickListener(new FileListItem.FileItemOnClickListener(mContext,mFileViewInteractionHub));

}




    public static class FileItemOnClickListener implements OnClickListener {
        private Context mContext;
        private FileViewInteractionHub mFileViewInteractionHub;

        @Override
        public void onClick(View v) {   【用户点击 FileListItem 的 onClick方法 】
            ImageView img = (ImageView) v.findViewById(R.id.file_checkbox);
            assert (img != null && img.getTag() != null);

            FileInfo tag = (FileInfo) img.getTag();
            tag.Selected = !tag.Selected;
            ActionMode actionMode = ((FileExplorerTabActivity) mContext).getActionMode();
            if (actionMode == null) {
                actionMode = ((FileExplorerTabActivity) mContext)
                        .startActionMode(new ModeCallback(mContext,
                                mFileViewInteractionHub));  // 实现自己的ActionMode  显示增删改查
                ((FileExplorerTabActivity) mContext).setActionMode(actionMode);
            } else {
                actionMode.invalidate();
            }
            if (mFileViewInteractionHub.onCheckItem(tag, v)) {  // 判断是否应该显示 V已选中图标
                img.setImageResource(tag.Selected ? R.drawable.btn_check_on_holo_light : R.drawable.btn_check_off_holo_light);
            } else {
                tag.Selected = !tag.Selected;
            }
            Util.updateActionModeTitle(actionMode, mContext,mFileViewInteractionHub.getSelectedFileList().size());  // 更新ActionMode的标题
        }
    }




~~~~~~~~~~~~~~~~~~~~~~5.End~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~6.Begin 【FileListItem】 用户点击Item时 显示在ActionModeBar上的增删改查~~~~~~~~~~~~~~~~~~~~~~

实现自己的Contextual Action Mode
private ActionMode mActionMode;
ActionMode.Callback mActionModeCallback = new ActionMode.Callback( xxxx );
mActionMode.startActionMode(mActionModeCallback)  实现自己的ActionMode
https://www.cnblogs.com/JinyaoLi/p/3868719.html 

actionMode = ((FileExplorerTabActivity) mContext).startActionMode(new ModeCallback (mContext,mFileViewInteractionHub) ); 【 FileListItem 的 onClick 事件中得到执行】


public static class ModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {    【区别于 onCreateOptionsMenu】
            MenuInflater inflater = ((Activity) mContext).getMenuInflater();
            mMenu = menu;
            inflater.inflate(R.menu.operation_menu, mMenu);
            initMenuItemSelectAllOrCancel();
            return true;
        }



operation_menu.xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/action_delete"
        android:icon="@drawable/operation_button_delete"
        android:title="@string/operation_delete"
        android:showAsAction="always" />
    <item android:id="@+id/action_copy"
        android:icon="@drawable/operation_button_copy"
        android:title="@string/operation_copy"
        android:showAsAction="always" />
    <item android:id="@+id/action_move"
        android:icon="@drawable/operation_button_move"
        android:title="@string/operation_move" />
    <item android:id="@+id/action_send"
        android:icon="@drawable/operation_button_send"
        android:title="@string/operation_send" />
    <item android:id="@+id/action_copy_path"
        android:title="@string/operation_copy_path" />
    <item android:id="@+id/action_select_all"
        android:icon="@drawable/operation_button_selectall"
        android:title="@string/operation_selectall" />
    <item android:id="@+id/action_cancel"
        android:icon="@drawable/operation_button_cancel"
        android:title="@string/operation_cancel" />
</menu>



        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {  【区别于 onPrepareOptionsMenu】
            mMenu.findItem(R.id.action_copy_path).setVisible(
                    mFileViewInteractionHub.getSelectedFileList().size() == 1);
            mMenu.findItem(R.id.action_cancel).setVisible(
            		mFileViewInteractionHub.isSelected());
            mMenu.findItem(R.id.action_select_all).setVisible(
            		!mFileViewInteractionHub.isSelectedAll());
            return true;
        }






        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {   【用户点击 FileListItem 弹出的底部增删改查menu的处理函数】
            switch (item.getItemId()) {
                case R.id.action_delete:
                    mFileViewInteractionHub.onOperationDelete();
                    mode.finish();
                    break;
                case R.id.action_copy:
                    ((FileViewActivity) ((FileExplorerTabActivity) mContext)
                            .getFragment(Util.SDCARD_TAB_INDEX))
                            .copyFile(mFileViewInteractionHub.getSelectedFileList());
                    mode.finish();
                    scrollToSDcardTab();
                    break;
                case R.id.action_move:
                    ((FileViewActivity) ((FileExplorerTabActivity) mContext)
                            .getFragment(Util.SDCARD_TAB_INDEX))
                            .moveToFile(mFileViewInteractionHub.getSelectedFileList());
                    mode.finish();
                    scrollToSDcardTab();
                    break;
                case R.id.action_send:
                    mFileViewInteractionHub.onOperationSend();
                    mode.finish();
                    break;
                case R.id.action_copy_path:
                    mFileViewInteractionHub.onOperationCopyPath();
                    mode.finish();
                    break;
                case R.id.action_cancel:
                    mFileViewInteractionHub.clearSelection();
                    initMenuItemSelectAllOrCancel();
                    mode.finish();
                    break;
                case R.id.action_select_all:
                    mFileViewInteractionHub.onOperationSelectAll();
                    initMenuItemSelectAllOrCancel();
                    break;
            }
            Util.updateActionModeTitle(mode, mContext, mFileViewInteractionHub
                    .getSelectedFileList().size());
            return false;
        }

~~~~~~~~~~~~~~~~~~~~~~6.End~~~~~~~~~~~~~~~~~~~~~~




~~~~~~~~~~~~~~~~~~~~~~7.Begin 【FileViewInteractionHub onListItemClick 】~~~~~~~~~~~~~~~~~~~~~~
FileListItem的 Item的点击事件



public FileViewInteractionHub(IFileInteractionListener fileViewListener) {
setup();  // 设置 各种 监听事件 
}

    private void setup() {
 ......
        setupFileListView();
 ......
    }



    private void setupFileListView() {
        mFileListView = (ListView) mFileViewListener.getViewById(R.id.file_path_list);
        mFileListView.setLongClickable(true);  // 设置长按 Enable
        mFileListView.setOnCreateContextMenuListener(mListViewContextMenuListener);  // 长按 监听器
        mFileListView.setOnItemClickListener(new OnItemClickListener() {   //Item的 点击事件 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(parent, view, position, id);
            }
        });
    }



    public void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        FileInfo lFileInfo = mFileViewListener.getItem(position);  // 依据位置取到 对应的 文件类 FileInfo
        showDropdownNavigation(false); // 隐藏下拉框

        if (lFileInfo == null) { // 如果文件为空 那么 打 Log 报错
            Log.e(LOG_TAG, "file does not exist on position:" + position);
            return;
        }

        if (isInSelection()) {  // 如果已经 有选中的文件  选择模式下的话
            boolean selected = lFileInfo.Selected;  //  取得 当前文件是否 选中标识
            ActionMode actionMode = ((FileExplorerTabActivity) mContext).getActionMode(); // 获得ActionMode 的引用
            ImageView checkBox = (ImageView) view.findViewById(R.id.file_checkbox); // 获得已选中 图片checkbox的 id
            if (selected) { 
                mCheckedFileNameList.remove(lFileInfo);  // 如果已选中 那么再次点击 就把这个文件从 选中列表 ArrayList<FileInfo> 中去除
                checkBox.setImageResource(R.drawable.btn_check_off_holo_light);  // 图片资源为  不选中
            } else {
                mCheckedFileNameList.add(lFileInfo); //  如果之前 未选中  那么 再次点击 就把这个文件  加入到 选中列表 ArrayList<FileInfo> 
                checkBox.setImageResource(R.drawable.btn_check_on_holo_light); //  设置 图片资源
            }
            if (actionMode != null) {
//如果当前 去除了FileInfo  之后  列表 ArrayList<FileInfo>  为空  那么就取消ActionMode的显示
                if (mCheckedFileNameList.size() == 0) actionMode.finish();    
                else actionMode.invalidate();   // 否则  就更新 ModeAction
            }
            lFileInfo.Selected = !selected;   // 把 当前的FileINfo 的 选中标志位 至反   

            Util.updateActionModeTitle(actionMode, mContext, mCheckedFileNameList.size());   // 更新 ActionMode的标题  以显示新的选中文件的个数
            return;
        }

        if (!lFileInfo.IsDir) {  // 如果 lFileInfo 不是 文件夹 ， 如果当前的lFileInfo 是  文件 
//  adb shell am broadcast -a  "android.intent.action.PICK"   —ez  pick_folder true        adb shell am broadcast -a net.micode.fileexplorer  "android.intent.action.PICK"
// adb shell am broadcast -a  "android.intent.action.GET_CONTENT"
            if (mCurrentMode == Mode.Pick) { // 如果当前的 选择模式是 Mode.Pick（ActionIntent 引起） 不是Mode.View(浏览)

 // 调用   mFileViewListener.onPick  去处理这个情况 FileCategoryActivity @Override public void onPick(FileInfo f) {   // do nothing }
                mFileViewListener.onPick(lFileInfo); 
            } else {
                viewFile(lFileInfo);  // 否则就打开这个 文件 
            }
            return;
        }

        mCurrentPath = getAbsoluteName(mCurrentPath, lFileInfo.fileName);  // 如果当前的路径 是文件夹则会执行这里  获得当前的路径  并设置到 mCurrentPath
        ActionMode actionMode = ((FileExplorerTabActivity) mContext).getActionMode();  
        if (actionMode != null) {
            actionMode.finish();
        }
        refreshFileList();  // 调用 refreshFileList() 去更新界面 ， 由于 mCurrentPath 已更改，refreshFileList() 将会去加载新的路径下的文件 
    }

~~~~~~~~~~~~~~~~~~~~~~7.End~~~~~~~~~~~~~~~~~~~~~~


~~~~~~~~~~~~~~~~~~~~~~8.Begin  【FileViewInteractionHub onLongClick 】~~~~~~~~~~~~~~~~~~~~~~

FileListItem的 Item的长按事件  弹出上下文菜单



public FileViewInteractionHub(IFileInteractionListener fileViewListener) {
setup();  // 设置 各种 监听事件 
}

    private void setup() {
 ......
        setupFileListView();
 ......
    }



    private void setupFileListView() {
        mFileListView = (ListView) mFileViewListener.getViewById(R.id.file_path_list);
        mFileListView.setLongClickable(true);  // 设置长按 Enable
        mFileListView.setOnCreateContextMenuListener(mListViewContextMenuListener);  // 长按 监听器
    }



ContextMenu 上下文菜单    Menu 普通ActionMode的 更多 Menu
    private OnCreateContextMenuListener mListViewContextMenuListener = new OnCreateContextMenuListener() {
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            if (isInSelection() || isMoveState())  // 选择模式下  移动模式下  长按无效
                return;

            showDropdownNavigation(false);  // 设置路径下拉框 隐藏

            AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;  // 把 参数强转 为 AdapterContextMenuInfo

            FavoriteDatabaseHelper databaseHelper = FavoriteDatabaseHelper.getInstance();
            FileInfo file = mFileViewListener.getItem(info.position);  // 获得当前 AdapterContextMenuInfo的index  对应的 FileInfo
            if (databaseHelper != null && file != null) {
                int stringId = databaseHelper.isFavorite(file.filePath) ? R.string.operation_unfavorite
                        : R.string.operation_favorite;   // 依据文件路径 FilePath 来判断 当前文件是否 是已经添加到收藏的文件  来决定字符串显示 收藏 或者 取消收藏
                addMenuItem(menu, GlobalConsts.MENU_FAVORITE, 0, stringId);  // 添加到长按的上下文 第一个 Index
            }

            addMenuItem(menu, GlobalConsts.MENU_COPY, 0, R.string.operation_copy);  // 添加 copy按钮
            addMenuItem(menu, GlobalConsts.MENU_COPY_PATH, 0, R.string.operation_copy_path); //添加  复制路径 按钮
            // addMenuItem(menu, GlobalConsts.MENU_PASTE, 0,
            // R.string.operation_paste);
            addMenuItem(menu, GlobalConsts.MENU_MOVE, 0, R.string.operation_move);  //  剪切    addMenuItem
            addMenuItem(menu, MENU_SEND, 0, R.string.operation_send);  // 发送
            addMenuItem(menu, MENU_RENAME, 0, R.string.operation_rename);  // 重命名
            addMenuItem(menu, MENU_DELETE, 0, R.string.operation_delete);  // 删除 
            addMenuItem(menu, MENU_INFO, 0, R.string.operation_info);  // 详情

        }
    };

ContextMenu 上下文菜单    Menu 普通ActionMode的 更多 Menu
    private void addMenuItem(Menu menu, int itemId, int order, int string, int iconRes) {
        if (!mFileViewListener.shouldHideMenu(itemId)) {
            MenuItem item = menu.add(0, itemId, order, string).setOnMenuItemClickListener(menuItemClick);   // 添加 Menu的 点击处理函数
            if (iconRes > 0) {
                item.setIcon(iconRes);
            }
        }
    }






    private OnMenuItemClickListener menuItemClick = new OnMenuItemClickListener() {  // 长按 上下文Menu的点击处理函数 

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            mListViewContextMenuSelectedItem = info != null ? info.position : -1;

            int itemId = item.getItemId();
            if (mFileViewListener.onOperation(itemId)) {
                return true;
            }

            addContextMenuSelectedItem();

            switch (itemId) {
                case MENU_SEARCH:
                    onOperationSearch();
                    break;
                case GlobalConsts.MENU_NEW_FOLDER:
                    onOperationCreateFolder();
                    break;
                case MENU_REFRESH:
                    onOperationReferesh();
                    break;
                case MENU_SELECTALL:
                    onOperationSelectAllOrCancel();
                    break;
                case GlobalConsts.MENU_SHOWHIDE:
                    onOperationShowSysFiles();
                    break;
                case GlobalConsts.MENU_FAVORITE:
                    onOperationFavorite();
                    break;
                case MENU_SETTING:
                    onOperationSetting();
                    break;
                case MENU_EXIT:
                    ((FileExplorerTabActivity) mContext).finish();
                    break;
                // sort
                case MENU_SORT_NAME:
                    item.setChecked(true);
                    onSortChanged(SortMethod.name);
                    break;
                case MENU_SORT_SIZE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.size);
                    break;
                case MENU_SORT_DATE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.date);
                    break;
                case MENU_SORT_TYPE:
                    item.setChecked(true);
                    onSortChanged(SortMethod.type);
                    break;

                case GlobalConsts.MENU_COPY:
                    onOperationCopy();
                    break;
                case GlobalConsts.MENU_COPY_PATH:
                    onOperationCopyPath();
                    break;
                case GlobalConsts.MENU_PASTE:
                    onOperationPaste();
                    break;
                case GlobalConsts.MENU_MOVE:
                    onOperationMove();
                    break;
                case MENU_SEND:
                    onOperationSend();
                    break;
                case MENU_RENAME:
                    onOperationRename();
                    break;
                case MENU_DELETE:
                    onOperationDelete();
                    break;
                case MENU_INFO:
                    onOperationInfo();
                    break;
                default:
                    return false;
            }

            mListViewContextMenuSelectedItem = -1;
            return true;
        }

    };

~~~~~~~~~~~~~~~~~~~~~~8.End~~~~~~~~~~~~~~~~~~~~~~


~~~~~~~~~~~~~~~~~~~~~~9.Begin~~~~~~~~~~~~~~~~~~~~~~
onNavigationBarClick();  // 显示当前目录的上级目录的方法  之后显示的目录的 DropDown中的Item的 点击事件



    private View.OnClickListener buttonClick = new View.OnClickListener() {  【 FileList界面 的点击事件处理】
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.current_path_pane:    【用户点击 导航Bar的处理】
                    onNavigationBarClick();  //  点击导航栏Bar的处理函数
                    break;
.....
}







    protected void onNavigationBarClick() {
        if (mDropdownNavigation.getVisibility() == View.VISIBLE) {
            showDropdownNavigation(false);
        } else {
            LinearLayout list = (LinearLayout) mDropdownNavigation.findViewById(R.id.dropdown_navigation_list);  // 导航栏布局ID
            list.removeAllViews();
            int pos = 0;
            String displayPath = mFileViewListener.getDisplayPath(mCurrentPath);  // 获得当前的路径
            boolean root = true;
            int left = 0;
            while (pos != -1 && !displayPath.equals("/")) {//如果当前位置在根文件夹则不显示导航条   以 / 分割路径Path 然后逐级往前赋值
                int end = displayPath.indexOf("/", pos);
                if (end == -1)
                    break;

                View listItem = LayoutInflater.from(mContext).inflate(R.layout.dropdown_item, null);  // DropItem

                View listContent = listItem.findViewById(R.id.list_item);  
                listContent.setPadding(left, 0, 0, 0);
                left += 20;   // 逐级往后 增加 20dp的距离
                ImageView img = (ImageView) listItem.findViewById(R.id.item_icon);

                img.setImageResource(root ? R.drawable.dropdown_icon_root : R.drawable.dropdown_icon_folder); // 设置路径图标
                root = false;

                TextView text = (TextView) listItem.findViewById(R.id.path_name);  // 设置路径 String
                String substring = displayPath.substring(pos, end);
                if(substring.isEmpty())substring = "/";
                text.setText(substring);

                listItem.setOnClickListener(navigationClick);  // 设置 DropItem 的 点击事件 navigationClick
                listItem.setTag(mFileViewListener.getRealPath(displayPath.substring(0, end))); // 绑定tag tag里保存 对应路径
                pos = end + 1;
                list.addView(listItem);
            }
            if (list.getChildCount() > 0)
                showDropdownNavigation(true);  // 显示 下拉框

        }
    }






    private OnClickListener navigationClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            String path = (String) v.getTag();  // 获得当前的路径
            assert (path != null);
            showDropdownNavigation(false);  // 关闭导航栏
            if (mFileViewListener.onNavigation(path))  // 设置当前的路径
                return;

            if(path.isEmpty()){
                mCurrentPath = mRoot;
            } else{
                mCurrentPath = path;
            }
            refreshFileList();
        }

    };



    @Override
    public boolean onNavigation(String path) 【对于Catagory 页面 直接 返回主页面 】{
        showPage(ViewPage.Home);
        return true;
    }


~~~~~~~~~~~~~~~~~~~~~~9.End~~~~~~~~~~~~~~~~~~~~~~


```

###### Presenter成员分类
```

*********Model***********
    private ArrayList<FileInfo> mCheckedFileNameList = new ArrayList<FileInfo>();
    private FileOperationHelper mFileOperationHelper;
    private FileSortHelper mFileSortHelper;
    private int mListViewContextMenuSelectedItem;

    private String mCurrentPath;
    private String mRoot;


    // menu
    private static final int MENU_SEARCH = 1;
    // private static final int MENU_NEW_FOLDER = 2;
    private static final int MENU_SORT = 3;
    private static final int MENU_SEND = 7;
    private static final int MENU_RENAME = 8;
    private static final int MENU_DELETE = 9;
    private static final int MENU_INFO = 10;
    private static final int MENU_SORT_NAME = 11;
    private static final int MENU_SORT_SIZE = 12;
    private static final int MENU_SORT_DATE = 13;
    private static final int MENU_SORT_TYPE = 14;
    private static final int MENU_REFRESH = 15;
    private static final int MENU_SELECTALL = 16;
    private static final int MENU_SETTING = 17;
    private static final int MENU_EXIT = 18;


*********View***********
    private IFileInteractionListener mFileViewListener;   // mFileViewListener.getItemCount() 等得到当前路径所有文件数量
    private View mConfirmOperationBar;
    private ProgressDialog progressDialog;
    private View mNavigationBar;
    private TextView mNavigationBarText;
    private View mDropdownNavigation;
    private ImageView mNavigationBarUpDownArrow;
    private ListView mFileListView;


```

###### Model实现类 
**mFileOperationHelper分析**
```
mFileOperationHelper分析

public class FileOperationHelper {
    private ArrayList<FileInfo> mCurFileNameList = new ArrayList<FileInfo>();   // 当前 文件列表 File集合

    private boolean mMoving; // 是否在 剪切标识

    private IOperationProgressListener mOperationListener;  // 接口实现类  让Model 对外提供 回调  让实现类增加功能处理

    public interface IOperationProgressListener {
        void onFinish();
        void onFileChanged(String path);
    }



    private FilenameFilter mFilter;   // 接口实现类  让Model 对外提供 回调  让实现类增加功能处理

   public interface FilenameFilter {
    boolean accept(File var1, String var2);
}

    public boolean CreateFolder(String path, String name) {}   // 文件的增删改查
    public boolean Delete(ArrayList<FileInfo> files) {}
    protected void DeleteFile(FileInfo f) {}
    public boolean Rename(FileInfo f, String newName) {}
    public boolean Paste(String path) {}
    public void Copy(ArrayList<FileInfo> files) {}
    private boolean MoveFile(FileInfo f, String dest) {}





}


```

**FileSortHelper分析**


```
文件的排序功能 实现 
    private abstract class FileComparator implements Comparator<FileInfo> {

        @Override
        public int compare(FileInfo object1, FileInfo object2) {
            if (object1.IsDir == object2.IsDir) {
                return doCompare(object1, object2);
            }

            if (mFileFirst) {
                // the files are listed before the dirs
                return (object1.IsDir ? 1 : -1);
            } else {
                // the dir-s are listed before the files
                return object1.IsDir ? -1 : 1;
            }
        }

        protected abstract int doCompare(FileInfo object1, FileInfo object2);
    }

    private Comparator cmpName = new FileComparator() {
        @Override
        public int doCompare(FileInfo object1, FileInfo object2) {
            return object1.fileName.compareToIgnoreCase(object2.fileName);
        }
    };

    private Comparator cmpSize = new FileComparator() {
        @Override
        public int doCompare(FileInfo object1, FileInfo object2) {
            return longToCompareInt(object1.fileSize - object2.fileSize);
        }
    };

    private Comparator cmpDate = new FileComparator() {
        @Override
        public int doCompare(FileInfo object1, FileInfo object2) {
            return longToCompareInt(object2.ModifiedDate - object1.ModifiedDate);
        }
    };

    private int longToCompareInt(long result) {
        return result > 0 ? 1 : (result < 0 ? -1 : 0);
    }

    private Comparator cmpType = new FileComparator() {
        @Override
        public int doCompare(FileInfo object1, FileInfo object2) {
            int result = Util.getExtFromFilename(object1.fileName).compareToIgnoreCase(
                    Util.getExtFromFilename(object2.fileName));
            if (result != 0)
                return result;

            return Util.getNameFromFilename(object1.fileName).compareToIgnoreCase(
                    Util.getNameFromFilename(object2.fileName));
        }
    };

```
###### 数据源Cursor 构造的SQL语句
```
【音乐文件 分类目录 四种排序方式的 Select 语句 】
select _id  ,_data , _size , date_modified from  audio ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  mime_type asc, title asc ;


【视频文件 分类目录 四种排序方式的 Select 语句 】
select _id  ,_data , _size , date_modified from  video ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  mime_type asc, title asc ;

【图片文件 分类目录 四种排序方式的 Select 语句  】

select _id  ,_data , _size , date_modified from  images ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  mime_type asc, title asc ;


【主题文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  mime_type asc, title asc ;


【 Doc 文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  title asc ;

select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  _size asc ;

select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  date_modified desc ;


select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  mime_type asc, title asc  ;


【 Zip 文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  mime_type asc, title asc ;

【 APK  文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  mime_type asc, title asc ;
```
###### FileExplorerTabActivity 数据源Cursor
```
public class FileCategoryActivity extends Fragment implements IFileInteractionListener {
  private FileListCursorAdapter mAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAdapter = new FileListCursorAdapter(mActivity, null【创建Adapter的时候 Cursor为空】, mFileViewInteractionHub, mFileIconHelper);
        ListView fileListView = (ListView) mRootView.findViewById(R.id.file_path_list);
        fileListView.setAdapter(mAdapter);

}

1. 点击分类Item时被调用 

View.OnClickListener onClickListener = new View.OnClickListener() {
onCategorySelected(f);
}


private void onCategorySelected(FileCategory f) {
mFileViewInteractionHub.refreshFileList();  ■1
}


2.

    @Override
    public void addSingleFile(FileInfo file) {
        refreshList();
    }


    private void refreshList() {
        mFileViewInteractionHub.refreshFileList();
    }






    public void refreshFileList 【FileViewInteractionHub 】() {  只要属性变化就要调用的方法    18个地方调用这里
        clearSelection();
        updateNavigationPane();

        // onRefreshFileList returns true indicates list has changed
        mFileViewListener.onRefreshFileList(mCurrentPath, mFileSortHelper);  【导致 Cursor变化的关键 】

        // update move operation button state
        updateConfirmButtons();

    }






    public boolean onRefreshFileList【FileExplorerTabActivity】(String path, FileSortHelper sort) {  // FileViewInteractionHub 的方法 refreshFileList 会运行到这里
        FileCategory curCategory = mFileCagetoryHelper.getCurCategory();
        if (curCategory == FileCategory.Favorite || curCategory == FileCategory.All)
            return false;

        Cursor c = mFileCagetoryHelper.query(curCategory, sort.getSortMethod());    【在此处获得得到Cursor 】
        showEmptyView(c == null || c.getCount() == 0);
        ca// 

        return true;
    }



    public Cursor query【FileCagetoryHelper】(FileCategory fc, SortMethod sort) {
        Uri uri = getContentUriByCategory(fc);  // 依据Category 分类 类型 去 构建 Uri
        String selection = buildSelectionByCategory(fc);  // 依据 Category 去构建要选择的部分
        String sortOrder = buildSortOrder(sort);   //  依据SortMethod  构建排序方式       public enum SortMethod {  name, size, date, type }

        if (uri == null) {
            Log.e(LOG_TAG, "invalid uri, category:" + fc.name());
            return null;
        }

        String[] columns = new String[] {
                FileColumns._ID  【_id 】, FileColumns.DATA 【 _data 】, FileColumns.SIZE 【_size】, FileColumns.DATE_MODIFIED 【date_modified】     // 数据库表的字段
        };

        return mContext.getContentResolver().query(uri, columns, selection, null, sortOrder);
    }

adb push  1.jpg /mnt/sdcard/


【音乐文件 分类目录 四种排序方式的 Select 语句 】
select _id  ,_data , _size , date_modified from  audio ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  audio ORDER BY  mime_type asc, title asc ;


【视频文件 分类目录 四种排序方式的 Select 语句 】
select _id  ,_data , _size , date_modified from  video ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  video ORDER BY  mime_type asc, title asc ;

【图片文件 分类目录 四种排序方式的 Select 语句  】

select _id  ,_data , _size , date_modified from  images ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from  images ORDER BY  mime_type asc, title asc ;


【主题文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.mtz'  ORDER BY  mime_type asc, title asc ;


【 Doc 文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  title asc ;

select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  _size asc ;

select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  date_modified desc ;


select _id  ,_data , _size , date_modified from files where (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')  ORDER BY  mime_type asc, title asc  ;


【 Zip 文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where (mime_type == 'application/zip')  ORDER BY  mime_type asc, title asc ;

【 APK  文件分类的  四种排序方式 的 Select 语句】
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  title asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  _size asc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  date_modified desc ;
select _id  ,_data , _size , date_modified from files where _data LIKE '%.apk'  ORDER BY  mime_type asc, title asc ;




Files(Uri): content://media/external/file
Music(Uri): content://media/external/audio/media
Video(Uri): content://media/external/video/media
Picture(Uri): content://media/external/images/media

String[] columns = new String[] {    【_id 】,  【 _data 】,  【_size】, 【date_modified】};     // 数据库表的字段 

theme(Selection 【Where】): _data LIKE '%.mtz'
doc(Selection)  : (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')
zip(Selection)  : (mime_type == 'application/zip')
apk(Selection)  : _data LIKE '%.apk'

by_name(Sort): title asc
by_size(Sort): _size asc
by_data(Sort): date_modified desc
by_type(Sort): mime_type asc, title asc





    private Uri getContentUriByCategory【FileCagetoryHelper】(FileCategory cat) {  【所有的资源都来自于 android.provider.MediaStore  多媒体仓库类 】
        Uri uri;
        String volumeName = "external"; // 外部存空间
        switch(cat) {
            case Theme:
            case Doc:
            case Zip:
            case Apk:
                uri = Files.getContentUri(volumeName); // import android.provider.MediaStore.Files;   获得存储器file路径
                break;
            case Music:   import android.provider.MediaStore.Audio;
                uri = Audio.Media.getContentUri(volumeName);
                break;

            case Video: //import android.provider.MediaStore.Video;
                uri = Video.Media.getContentUri(volumeName);
                break;
            case Picture:  import android.provider.MediaStore.Images;
                uri = Images.Media.getContentUri(volumeName);
                break;
           default:
               uri = null;
        }
        return uri;
    }


获得的路径分别是：
Files(Uri): content://media/external/file
Music(Uri): content://media/external/audio/media
Video(Uri): content://media/external/video/media
Picture(Uri): content://media/external/images/media




    private String buildSelectionByCategory(FileCategory cat) {
        String selection = null;
        switch (cat) {
            case Theme:
                selection = FileColumns.DATA【"_data"】 + " LIKE '%.mtz'";
                break;
            case Doc:
                selection = buildDocSelection();
                break;
            case Zip:
                selection = "(" + FileColumns.MIME_TYPE【mime_type】 + " == '" + Util.sZipFileMimeType【"application/zip"】 + "')";
                break;
            case Apk:
                selection = FileColumns.DATA 【"_data"】 + " LIKE '%.apk'";
                break;
            default:
                selection = null;
        }
        return selection;
    }

theme(Selection): _data LIKE '%.mtz'
doc(Selection): (mime_type=='application/msword') OR (mime_type=='text/plain') OR (mime_type=='application/vnd.ms-excel') OR (mime_type=='application/pdf')
zip(Selection): (mime_type == 'application/zip')
apk(Selection): _data LIKE '%.apk'

    private String buildDocSelection() {
        StringBuilder selection = new StringBuilder();
        Iterator<String> iter = Util.sDocMimeTypesSet.iterator();   // 迭代 sDocMimeTypesSet HashSet<String>
        while(iter.hasNext()) {
            selection.append("(" + FileColumns.MIME_TYPE + "=='" + iter.next() + "') OR ");
        }
        return  selection.substring(0, selection.lastIndexOf(")") + 1);
    }

    public static HashSet<String> sDocMimeTypesSet = new HashSet<String>() {
        {
            add("text/plain");
            add("text/plain");
            add("application/pdf");
            add("application/msword");
            add("application/vnd.ms-excel");
            add("application/vnd.ms-excel");
        }
    };



    private String buildSortOrder(SortMethod sort) {
        String sortOrder = null;
        switch (sort) {
            case name:
                sortOrder = FileColumns.TITLE + " asc";
                break;
            case size:
                sortOrder = FileColumns.SIZE + " asc";
                break;
            case date:
                sortOrder = FileColumns.DATE_MODIFIED + " desc";
                break;
            case type:
                sortOrder = FileColumns.MIME_TYPE + " asc, " + FileColumns.TITLE + " asc";
                break;
        }
        return sortOrder;
}

by_name(Sort): title asc
by_size(Sort): _size asc
by_data(Sort): date_modified desc
by_type(Sort): mime_type asc, title asc









```
###### FileExplorerTabActivity 数据源相关方法
 1.FileExplorerTabActivity 数据源FileListCursorAdapter  里面的 Cursor 
 2.FileCategoryActivity 里面的 FavoriteList成员  ArrayAdapter<FavoriteItem> mFavoriteListAdapter 以及 类 FavoriteDatabaseHelper

```    
 1.FileExplorerTabActivity 数据源FileListCursorAdapter  里面的 Cursor 

public class FileCategoryActivity extends Fragment {
private FileListCursorAdapter mAdapter;


        FileListCursorAdapter mAdapter = new FileListCursorAdapter(mActivity, null, mFileViewInteractionHub, mFileIconHelper);
        ListView fileListView = (ListView) mRootView.findViewById(R.id.file_path_list);  // <ListView>  </ListView>
        fileListView.setAdapter(mAdapter);



mAdapter.getCount();  // 获得Adapter 中 Item 数量  地图 谷歌
mAdapter.changeCursor(c);   // 改变索引  将导致更新数据
mAdapter.getAllFiles(); //  自定义方法  获得Adapter的所有Item 对应的 FileInfo
mAdapter.getFileItem(pos);  // 从 Adapter 中 获得 索引 index 中 对应的 FileiNfo
}



public class FileListCursorAdapter extends CursorAdapter {     【CursorAdapter 是抽象类 abstract class CursorAdapter extends BaseAdapter】

private HashMap<Integer, FileInfo> mFileNameList = new HashMap<Integer, FileInfo>();


    private final LayoutInflater mLayoutInflater; 

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {  【用于 解析 xml 文件 用于返回 每一个Item 对应的 Item布局xml 生成的View】
        return mLayoutInflater.inflate(R.layout.category_file_browser_item, parent, false);
    }




    @Override
    public void bindView(View view, Context context, Cursor cursor) {  【用于在 newView中创建的 View  ，用View 绑定它对应的数据 ,Cursor 应用是 View 对应的Cursor 一对一的】
 // bindView中的 cursor 不能直接查询吗？  不对这样做是为了填充集合 HashMap<Integer, FileInfo> ，并复用 有利于内存优化
        FileInfo fileInfo = getFileItem(cursor.getPosition());    
        if (fileInfo == null) {
            // file is not existing, create a fake info  如果文件 不存在 创建一个假的 FackInfo?  第一次听
            fileInfo = new FileInfo();
            fileInfo.dbId = cursor.getLong(FileCategoryHelper.COLUMN_ID);
            fileInfo.filePath = cursor.getString(FileCategoryHelper.COLUMN_PATH);
            fileInfo.fileName = Util.getNameFromFilepath(fileInfo.filePath);
            fileInfo.fileSize = cursor.getLong(FileCategoryHelper.COLUMN_SIZE);
            fileInfo.ModifiedDate = cursor.getLong(FileCategoryHelper.COLUMN_DATE);
        }


           // 设置 Item的点击事件 ，以及显示的具体内容
        FileListItem.setupFileListItemInfo(mContext, view, fileInfo, mFileIcon, mFileViewInteractionHub);

         // 设置 Item中 checkbox的点击事件
        view.findViewById(R.id.category_file_checkbox_area).setOnClickListener(new FileListItem. FileItemOnClickListener(mContext, mFileViewInteractionHub));
    }




    public FileInfo getFileItem(int pos) {   【以位置 取得 FielInfo 】
        Integer position = Integer.valueOf(pos);
        if (mFileNameList.containsKey(position))  【 如果当前  HashMap<Integer, FileInfo> mFileNameList  有这个Interger 位置 Key， 那么返回对应的 Value FileInfo】
            return mFileNameList.get(position);

        Cursor cursor = (Cursor) getItem(pos); // getItem【CursorAdapter方法】通过position来取得 Cursor  【 调用 CursorAdapter的getItem 方法 可以返回 Cursor的实例】
        FileInfo fileInfo = getFileInfo(cursor); // 从Cursor 中 获取FileInfo  并加入 Map<int , FileInfo>
        if (fileInfo == null)
            return null;

        fileInfo.dbId = cursor.getLong(FileCategoryHelper.COLUMN_ID);
        mFileNameList.put(position, fileInfo);
        return fileInfo;
    }





    private FileInfo getFileInfo(Cursor cursor) {
// 取得Cursor的中保存的File路径【String】  并以这个路径为参数 构建 FileInfo
        return (cursor == null || cursor.getCount() == 0) ? null : Util.GetFileInfo(cursor.getString(FileCategoryHelper.COLUMN_PATH【1】));  
    }


    public static FileInfo GetFileInfo(String filePath) {
        File lFile = new File(filePath);
        if (!lFile.exists())
            return null;

        FileInfo lFileInfo = new FileInfo();
        lFileInfo.canRead = lFile.canRead();
        lFileInfo.canWrite = lFile.canWrite();
        lFileInfo.isHidden = lFile.isHidden();
        lFileInfo.fileName = Util.getNameFromFilepath(filePath);
        lFileInfo.ModifiedDate = lFile.lastModified();
        lFileInfo.IsDir = lFile.isDirectory();
        lFileInfo.filePath = filePath;
        lFileInfo.fileSize = lFile.length();
        return lFileInfo;
    }



      【FileItem的 点击初始化静态函数 】
    public static void setupFileListItemInfo(Context context, View view, FileInfo fileInfo, FileIconHelper fileIcon,
            FileViewInteractionHub fileViewInteractionHub) {

        // if in moving mode, show selected file always
        if (fileViewInteractionHub.isMoveState()) { // 如果移动剪切状态 
            fileInfo.Selected = fileViewInteractionHub.isFileSelected(fileInfo.filePath);  // 判断当前文件是否选中
        }

        ImageView checkbox = (ImageView) view.findViewById(R.id.file_checkbox);
        if (fileViewInteractionHub.getMode() == Mode.Pick) {
            checkbox.setVisibility(View.GONE);
        } else {
            checkbox.setVisibility(fileViewInteractionHub.canShowCheckBox() ? View.VISIBLE : View.GONE);  // 判断是否显示 checkbox
            checkbox.setImageResource(fileInfo.Selected ? R.drawable.btn_check_on_holo_light: R.drawable.btn_check_off_holo_light);  //依据当前属性 Selected 判断显示什么图标
            checkbox.setTag(fileInfo);  // checkbox 绑定 fileInfo
            view.setSelected(fileInfo.Selected);  // 用来切换背景显示 突出选中   https://blog.csdn.net/jacklittlepig/article/details/79077282
        }

        Util.setText(view, R.id.file_name, fileInfo.fileName);
        Util.setText(view, R.id.file_count, fileInfo.IsDir ? "(" + fileInfo.Count + ")" : "");
        Util.setText(view, R.id.modified_time, Util.formatDateString(context, fileInfo.ModifiedDate));
        Util.setText(view, R.id.file_size, (fileInfo.IsDir ? "" : Util.convertStorage(fileInfo.fileSize)));   // 设置 TextView的显示文字

        ImageView lFileImage = (ImageView) view.findViewById(R.id.file_image);   // 文件图标
        ImageView lFileImageFrame = (ImageView) view.findViewById(R.id.file_image_frame);   // 文件图标框架

        if (fileInfo.IsDir) {  
            lFileImageFrame.setVisibility(View.GONE);  // 如果是文件夹 不显示  file_image_frame
            lFileImage.setImageResource(R.drawable.folder);  // 图片资源 设置为  R.drawable.folder
        } else {
            fileIcon.setIcon(fileInfo, lFileImage, lFileImageFrame);   // 如果不是文件夹那么 传递信息过去FileIconHelper方法 加工  尼玛 这一大句  引起一大串代码执行
        }
    }




    public static int getFileIcon(String ext) {
        Integer i = fileExtToIcons.get(ext.toLowerCase());
        if (i != null) {
            return i.intValue();
        } else {
            return R.drawable.file_icon_default;
        }

    }

    public void setIcon【FileIconHelper】(FileInfo fileInfo, ImageView fileImage, ImageView fileImageFrame) {
        String filePath = fileInfo.filePath;  // 获得文件的路径
        long fileId = fileInfo.dbId;   // 获得文件的 数据库ID 
        String extFromFilename = Util.getExtFromFilename(filePath);  // 获得文件的扩展名
        FileCategory fc = FileCategoryHelper.getCategoryFromPath(filePath);  // 获得文件的类型Menu
        fileImageFrame.setVisibility(View.GONE);
        boolean set = false;
        int id = getFileIcon(extFromFilename);  //依据扩展名 获得 应该显示的资源ID
        fileImage.setImageResource(id);  // 设置 显示的文件图标

// 【    private FileIconLoader mIconLoader;     public void cancelRequest(ImageView view) { mPendingRequests.remove(view);}】 用于取消 这个ImageView的图片请求
        mIconLoader.cancelRequest(fileImage);  
        switch (fc) {  // 依据类型 进行 loadIcon?  从数据库中加载 Icon?  尼玛  是为了从数据库中加载  照片和视频的缩略图 放入到  ImageView fileImageFrame
            case Apk:
                set = mIconLoader.loadIcon(fileImage, filePath, fileId, fc);   // APK的话  从 数据库中加载 APK对应的图标
                break;
            case Picture:
            case Video:
                set = mIconLoader.loadIcon(fileImage, filePath, fileId, fc);    // Picture  Video的话  从 数据库 加载 照片和视频的缩略图
                if (set)  // 如果已经加载过 那么把缩略图显示出来
                    fileImageFrame.setVisibility(View.VISIBLE);  
                else {
                    fileImage.setImageResource(fc == FileCategory.Picture ? R.drawable.file_icon_picture : R.drawable.file_icon_video);  // 如果没有加载过  显示默认的图标

//private static HashMap<ImageView, ImageView> imageFrames = new HashMap<ImageView, ImageView>();  
                    imageFrames.put(fileImage, fileImageFrame);
                    set = true;
                }
                break;
            default:
                set = true;
                break;
        }

        if (!set)
            fileImage.setImageResource(R.drawable.file_icon_default);  // 如果没有再过 那么 显示默认图标
    }


~~~~~~~~~~~~~~~
FileIconLoader.java
//  ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力，但同时降低了对读一致性的要求
    private final static ConcurrentHashMap<String, ImageHolder【抽象类的实现类】> mImageCache = new ConcurrentHashMap<String, ImageHolder>();



    private static abstract class ImageHolder {
        public static final int NEEDED = 0;
        public static final int LOADING = 1;
        public static final int LOADED = 2;
        int state;
        public static ImageHolder create(FileCategory cate) {
            switch (cate) {
                case Apk:
                    return new DrawableHolder();
                case Picture:
                case Video:
                    return new BitmapHolder();
            }
            return null;
        };
        public abstract boolean setImageView(ImageView v);
        public abstract boolean isNull();
        public abstract void setImage(Object image);
    }

    private static class BitmapHolder extends ImageHolder { SoftReference<Bitmap> bitmapRef; }
    private static class DrawableHolder extends ImageHolder { SoftReference<Drawable> drawableRef; }


    private final ConcurrentHashMap<ImageView, FileId> mPendingRequests = new ConcurrentHashMap<ImageView, FileId>();



   public static class FileId {
        public String mPath;
        public long mId; // database id
        public FileCategory mCategory;
        public FileId(String path, long id, FileCategory cate) {
            mPath = path;
            mId = id;
            mCategory = cate;
        }
    }


    public boolean loadIcon【 FileIconLoader.java 】(ImageView view, String path, long id, FileCategory cate) {
        boolean loaded = loadCachedIcon(view, path, cate);  
        if (loaded) {
            mPendingRequests.remove(view);    // 如果已经加载过那么 从集合  ConcurrentHashMap<ImageView, FileId>  剔除 ImageView 
        } else {
            FileId p = new FileId(path, id, cate);  // 如果没有加载 那么创建 FieldID  并 放入 集合  ConcurrentHashMap<ImageView, FileId> 
            mPendingRequests.put(view, p);
            if (!mPaused) {
                // Send a request to start loading photos
                requestLoading();  // 请求加载 
            }
        }
        return loaded;   // 返回是否加载过
    }

    private static final int MESSAGE_REQUEST_LOADING = 1;
    private boolean mLoadingRequested;
    private void requestLoading() {
        if (!mLoadingRequested) {
            mLoadingRequested = true;
            mMainThreadHandler.sendEmptyMessage(MESSAGE_REQUEST_LOADING);  【发送消息给一个线程】
        }
    }



    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_REQUEST_LOADING: {
                mLoadingRequested = false;
                if (!mPaused) {
                    if (mLoaderThread == null) {
                        mLoaderThread = new LoaderThread();  // 新启动一个 LoaderThread 来处理 加载的任务
                        mLoaderThread.start();
                    }

                    mLoaderThread.requestLoading();  // 去发送消息给新建的 LoaderThread  去执行加载
                }
                return true;
            }

            case MESSAGE_ICON_LOADED: {
                if (!mPaused) {
                    processLoadedIcons();
                }
                return true;
            }
        }
        return false;
    }



        public void requestLoading() {
            if (mLoaderThreadHandler == null) {
                mLoaderThreadHandler = new Handler(getLooper(), this);
            }
            mLoaderThreadHandler.sendEmptyMessage(0);  // 空消息
        }


        public boolean handleMessage(Message msg) {
            Iterator<FileId> iterator = mPendingRequests.values().iterator();  //从  ConcurrentHashMap<ImageView, FileId> mPendingRequests 拿FieldID
            while (iterator.hasNext()) {
                FileId id = iterator.next();  // 拿取到 FieldID

//  从 ConcurrentHashMap<String, ImageHolder【抽象类的实现类】> mImageCache  依据FieldID.path拿到对应的ImageHolder
                ImageHolder holder = mImageCache.get(id.mPath);  
                if (holder != null && holder.state == ImageHolder.NEEDED) { // 如果不为空 并需要加载  那么执行
                    // Assuming atomic behavior
                    holder.state = ImageHolder.LOADING;  // 改变字段为 LOADING
                    switch (id.mCategory) {
                        case Apk:
                            Drawable icon = Util.getApkIcon(mContext, id.mPath);  // 获得APK的Drawable
                            holder.setImage(icon);
                            break;
                        case Picture:
                        case Video:
                            boolean isVideo = id.mCategory == FileCategory.Video;
                            if (id.mId == 0)
                                id.mId = getDbId(id.mPath, isVideo);
                            if (id.mId == 0) {
                                Log.e("FileIconLoader", "Fail to get dababase id for:" + id.mPath);
                            }
                            holder.setImage(isVideo ? getVideoThumbnail(id.mId) : getImageThumbnail(id.mId));  // 获得图片 video的缩略图
                            break;
                    }

                    holder.state = BitmapHolder.LOADED;  // 获得之后  字段变为  LOADED
                    mImageCache.put(id.mPath, holder);   //  添加到 cache 中 
                }
            }

            mMainThreadHandler.sendEmptyMessage(MESSAGE_ICON_LOADED);  // 发送给主线程 已经加载完毕的消息
            return true;
        }



    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_ICON_LOADED: {  // 接收到 加载完成的消息
                if (!mPaused) {
                    processLoadedIcons();  // 执行  processLoadedIcons()  处理结果
                }
                return true;
            }
        }
        return false;


}


    private void processLoadedIcons() {
// ConcurrentHashMap<ImageView, FileId> mPendingRequests    遍历ImageView
        Iterator<ImageView> iterator = mPendingRequests.keySet().iterator();
        while (iterator.hasNext()) {  // 
            ImageView view = iterator.next();
            FileId fileId = mPendingRequests.get(view);  // 依据 ImageView 为 Key  获得 FieldID
            boolean loaded = loadCachedIcon(view, fileId.mPath, fileId.mCategory);  // 创建 ImageHoder 放入缓存?
            if (loaded) {
                iterator.remove();  //  从  ConcurrentHashMap<ImageView, FileId> mPendingRequests 删除 当前遍历元素
                iconLoadListener.onIconLoadFinished(view);  // 通知  缩略图已经加载好了  FileIconHelper implements IconLoadFinishListener
            }
        }

        if (!mPendingRequests.isEmpty()) {
            requestLoading();  // 如果 请求Map 非空  那么继续发送消息创建 HandlerThread 出加载
        }
    }


    @Override
    public void onIconLoadFinished(ImageView view) {
        ImageView frame = imageFrames.get(view);
        if (frame != null) {
            frame.setVisibility(View.VISIBLE);  【设置 对应的 ImageView缩略图可见】

 //  private static HashMap<ImageView, ImageView> imageFrames = new HashMap<ImageView, ImageView>();  从Map 删除当前项
            imageFrames.remove(view);
        }
    }



    private boolean loadCachedIcon(ImageView view, String path, FileCategory cate) {
        ImageHolder holder = mImageCache.get(path);  // <String,ImageHoder> Map  获得ImageHoder
        
        if (holder == null) {
            holder = ImageHolder.create(cate);
            if (holder == null)
                return false;

            mImageCache.put(path, holder);  // 放入缓存
        } else if (holder.state == ImageHolder.LOADED) {
            if (holder.isNull()) {
                return true;
            }

            // failing to set imageview means that the soft reference was
            // released by the GC, we need to reload the photo.
            if (holder.setImageView(view)) {  // 设置 对应的显示   view.setImage<holder.SoftwareRef<mDrable>>   v.setImageBitmap(bitmapRef.get());   v.setImageDrawable(drawableRef.get());
                return true;
            }
        }

        holder.state = ImageHolder.NEEDED;
        return false;
    }



    public static Drawable getApkIcon(Context context, String apkPath) {  // 获得APK的Drawable
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
        return null;
    }

        private Bitmap getVideoThumbnail(long id) {  // 获得Video的缩略图
            return Video.Thumbnails.getThumbnail(mContext.getContentResolver(), id, MICRO_KIND, null);
        }



          private Bitmap getImageThumbnail(long id) { // 获得Photo的缩略图
            return Images.Thumbnails.getThumbnail(mContext.getContentResolver(), id, MICRO_KIND, null);
        }



    private boolean loadCachedIcon(ImageView view, String path, FileCategory cate) {
        ImageHolder holder = mImageCache.get(path);   //【  ConcurrentHashMap<String, ImageHolder【抽象类的实现类】】
        
        if (holder == null) {  // 如果 从缓存  拿到 ImageHolder 为空  说明 没有 ，需要 重新 创建
            holder = ImageHolder.create(cate);  // 重新创建 ImageHolder
            if (holder == null)
                return false;

            mImageCache.put(path, holder);
        } else if (holder.state == ImageHolder.LOADED) {   // 如果已经加载 那么检查 状态是否为空 null
            if (holder.isNull()) {
                return true;
            }

            // failing to set imageview means that the soft reference was
            // released by the GC, we need to reload the photo.
            if (holder.setImageView(view)) {  //  不为空 那么设置  ImageHoder持有的 显示的 view
                return true;
            }
        }

        holder.state = ImageHolder.NEEDED;  // 对于新创建的  ImageHolder 设置它的初始状态为 NEEDED 需要加载图片
        return false;
    }



        public static ImageHolder create(FileCategory cate) {
            switch (cate) {
                case Apk:
                    return new DrawableHolder();
                case Picture:
                case Video:
                    return new BitmapHolder();
            }

            return null;
        };








~~~~~~~~~~~~~~

    public static String getExtFromFilename(String filename) {  【获得文件的扩展名】
        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            return filename.substring(dotPosition + 1, filename.length());
        }
        return "";
    }


    public static int getFileIcon(String ext) {  【获得文件应该显示的资源图片ID  依据后缀】
        Integer i = fileExtToIcons【HashMap<String 后缀, Integer 资源ID> MAP】.get(ext.toLowerCase());
        if (i != null) {
            return i.intValue();
        } else {
            return R.drawable.file_icon_default;
        }

    }

    private static HashMap<String, Integer> fileExtToIcons = new HashMap<String, Integer>();




//  CursorAdapter 提供的 接口 用于返回 对应位置的Cursor  在 bindView中Cursor可被调用的,   Cursor.java 是一个
//   Cursor cursor = (Cursor) getItem(cursor.getPosition()); 
    public Object getItem【CursorAdapter】(int position) {}   



}


实现 CursorAdapter 提供的接口   以及抽象方法
abstract class CursorAdapter extends BaseAdapter{

    public abstract View newView(Context var1, Cursor var2, ViewGroup var3); // 需要让实现类实现的方法 newView
    public abstract void bindView(View var1, Context var2, Cursor var3); // 需要让实现类实现的方法 bindView

    public void changeCursor(Cursor cursor) {  // 需要让实现类重载的方法
    }
}

```



#### FileViewActivity extends Fragment

```
public class FileViewActivity extends Fragment implements  IFileInteractionListener, IBackPressedListener {

    private ListView mFileListView;   // 文件列表 

    private ArrayAdapter<FileInfo> mAdapter;  // 文件ListView的 Adapter     这个adapter的数据源 应该是  mFileNameList

    private FileViewInteractionHub mFileViewInteractionHub; // 对于Fragment  都是扮演 Presenter 的角色


    private FileCategoryHelper mFileCagetoryHelper;  // 文件分类工具

    private FileIconHelper mFileIconHelper;  // 图片 Video 缩略图加载工具 

    private ArrayList<FileInfo> mFileNameList = new ArrayList<FileInfo>();   // 当前 Path 对应的 文件的 集合 ， File()  DirFile().getFiles() 获得当前目录下的所有子目录

    private static final String sdDir =  Environment.getExternalStorageDirectory().getPath()； // 外部SD卡路径


 【 FileViewInteractionHub 用于 FileViewActivity 的更新UI的方法】
    public void refreshFileList() {
        clearSelection();
        updateNavigationPane();

        // onRefreshFileList returns true indicates list has changed
        mFileViewListener.onRefreshFileList(mCurrentPath, mFileSortHelper);

        // update move operation button state
        updateConfirmButtons();

    }

}





    public enum FileCategory {   All, Music, Video, Picture, Theme, Doc, Zip, Apk, Custom, Other, Favorite  }
    public static HashMap<FileCategory, FilenameExtFilter> filters = new HashMap<FileCategory, FilenameExtFilter>();


    public boolean onRefreshFileList(String path, FileSortHelper sort) {   【 用于FileViewActivity 更新 path 】
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {  // 如果当前的路径 对应的 File  不是文件夹 那么返回 false
            return false;
        }
        final int pos = computeScrollPosition(path);  // 计算 滑块的位置?
        ArrayList<FileInfo> fileList = mFileNameList;   // 把当前的   ArrayList<FileInfo> mFileNameList  引用， 清空当前 ArrayList<FileInfo> 
        fileList.clear();  //  清空当前 ArrayList<FileInfo> 

        File[] listFiles = file.listFiles(mFileCagetoryHelper.getFilter());  // 依据File 查询出当前的子文件集合
        if (listFiles == null)
            return true;

        for (File child : listFiles) {  // 遍历 子文件目录 
            // do not show selected file if in move state
            if (mFileViewInteractionHub.isMoveState() && mFileViewInteractionHub.isFileSelected(child.getPath()))
                continue;

            String absolutePath = child.getAbsolutePath();  // 获得子文件的绝对路径
            if (Util.isNormalFile(absolutePath) && Util.shouldShowFile(absolutePath)) {  // 检查文件是否 是 以 . 开头的文件
                // 依据 File 类 获取 FileInfo类
                FileInfo lFileInfo = Util.GetFileInfo(child, mFileCagetoryHelper.getFilter(), Settings.instance().getShowDotAndHiddenFiles());
                if (lFileInfo != null) {
                    fileList.add(lFileInfo);  // 添加到当前的  ArrayList<FileInfo> mFileNameList  集合 
                }
            }
        }

        sortCurrentList(sort);  // 对 ArrayList<FileInfo> mFileNameList 进行排序
        showEmptyView(fileList.size() == 0);   // 如果 文件及合为空  那么 显示 Empty
        mFileListView.post(new Runnable() {
            @Override
            public void run() {
// 这个方法的作用就是将第position个item显示在listView的最上面一项，假如有一个ListView控件，
// 其一次只能显示10个item，但现在有20个数据项，设置好adapter以后，默认是第一个数据项显示在最上面，如
// 果我现在调用setSelection(2),则第3个数据项会显示在最上面，调用setSelection(9),则第10个数据项会显示在最上面。

                mFileListView.setSelection(pos);
            }
        });
        return true;
    }

```
#### ServerControlActivity extends Fragment



# File文件管理器的关键属性
```
---------------
1.当前在Category分类页面显示的页面类型      FileCategoryActivity.curViewPage      enum ViewPage {  Home, Favorite, Category, NoSD, Invalid  }


---------------
1.用于保存当前分类的变量  FileCategoryHelper.mCategory      enum FileCategory {All, Music, Video, Picture, Theme, Doc, Zip, Apk, Custom, Other, Favorite } 
               
---------------
 1.当前路径保存变量   FileViewInteractionHub.mCurrentPath【String】
 2.当前选中文件保存的变量  FileViewInteractionHub.mCheckedFileNameList 【ArrayList<FileInfo>】
 3.当前页面显示模式的变量 FileViewInteractionHub.mCurrentMode;    enum Mode {  View, Pick }
 4.当前路径选择模式下最新选中的那么文件的索引int index     FileViewInteractionHub.mListViewContextMenuSelectedItem  【int 】   
 5.当前上下文菜单选中的menu位置索引 FileViewInteractionHub.mListViewContextMenuSelectedItem 【int】
---------------
 1.用于保存当前路径下文件的列表   FileOperationHelper.mCurFileNameList = new ArrayList<FileInfo>();
---------------
1.保存当前排列的顺序   SortMethod mSort;  public enum SortMethod {name, size, date, type }
2.保存是否让文件先显示的标识       boolean mFileFirst;
3.排序方式-排序实现的集合    HashMap<SortMethod, Comparator> mComparatorList = new HashMap<SortMethod, Comparator>();
---------------
FileListCursorAdapter
1. 保存Cursor中查找出来的FileInfo( 当前Path的FileInfo ) 以及对应关系的 Map关系  HashMap<Integer, FileInfo> mFileNameList = new HashMap<Integer, FileInfo>();


---------------
FileIconHelper
1.文件后缀-图片资源id Map集合   static HashMap<String, Integer> fileExtToIcons = new HashMap<String, Integer>();



```




# Util 工具类分析