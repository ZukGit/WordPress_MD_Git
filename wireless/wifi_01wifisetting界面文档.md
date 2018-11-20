#WIFI的UI界面显示以及对应的UserAction(UA)

## WIFI UI界面以及对应的UA位置
<img src="//../zimage/wireless/wifi/01_wifisetting/1.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/2.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/3.png" width = "25%" height="25%"/> 


<img src="//../zimage/wireless/wifi/01_wifisetting/4.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/5.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/6.png" width = "25%" height="25%"/> 

<img src="//../zimage/wireless/wifi/01_wifisetting/7.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/8.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/9.png" width = "25%" height="25%"/> 

<img src="//../zimage/wireless/wifi/01_wifisetting/10.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/12.png" width = "25%" height="25%"/> 

<img src="//../zimage/wireless/wifi/01_wifisetting/13.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/14.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/15.png" width = "25%" height="25%"/> 

<img src="//../zimage/wireless/wifi/01_wifisetting/16.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/17.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/18.png" width = "25%" height="25%"/> 

<img src="//../zimage/wireless/wifi/01_wifisetting/29.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/30.png" width = "25%" height="25%"/>  
  



## WIFI UA1分析
###WIFI UA1 操作列

1. **UA编号**   UA1
1. **UA说明**  ** WIFI设置界面入口**
1. **UA触发函数**  **在network_and_internet.xml布局文件的Preference指定了targetClass和Action的Intent** 在DashboardFeatureProviderImpl.java的openTileIntent函数
   activity.startActivityForResult(intent, 0) 函数被执行
1. **UA字符可选值**    Summary简要字符可选:     1.关闭 2.未连接 3.连接热点名字 
1. **UA布局及ID**     NetworkDashboardFragment的布局文件network_and_internet.xml中 android:key="toggle_wifi"
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key ** 
1. **数据库可选值 ** 
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**  WifiSettingsActivity   (static) extends SettingsActivity
    ```<meta-data android:name="com.android.settings.FRAGMENT_CLASS" android:value="com.android.settings.wifi.WifiSettings" />```
1. **UA触发函数所在类**   NetworkDashboardActivity(static) extends SettingsActivity 依据[Manifest](file/AndroidManifest.xml)【加载 NetworkDashboardFragment 页面】
     ```<meta-data android:name="com.android.settings.FRAGMENT_CLASS"  android:value="com.android.settings.network.NetworkDashboardFragment"/>```
---




### WIFI-UA1代码分析
 <img src="//../zimage/wireless/wifi/01_wifisetting/19.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/20.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/21.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/22.png" width = "25%" height="25%"/> 
 
 [http://blog.sina.com.cn/s/blog_c5db74db0102wu0k.html](http://blog.sina.com.cn/s/blog_c5db74db0102wu0k.html)    为安卓8.0设置添加一级菜单的操作
 [https://blog.csdn.net/qq_31012033/article/details/79522813](https://blog.csdn.net/qq_31012033/article/details/79522813)  Android 8.0 Settings流程分析与变动
 
 
```
 /packages/apps/Settings/src/com/android/settings/core/gateway/SettingsGateway.java
public class SettingsGateway {

//该数组定义了设置界面所有的Activity以及对应显示Activity的顺序
public static final String[] SETTINGS_FOR_RESTRICTED = { 
// New IA
// Home page
Settings.NetworkDashboardActivity.class.getName(), // <string name="network_dashboard_title">"网络和互联网"</string>
Settings.ConnectedDeviceDashboardActivity.class.getName(), //<string name="connected_devices_dashboard_title">"已关联的设备"</string>
Settings.AppAndNotificationDashboardActivity.class.getName(),// <string name="app_and_notification_dashboard_title">"应用和通知"</string>
Settings.DisplaySettingsActivity.class.getName(),// <string name="display_settings_title">"显示"</string>
Settings.SoundSettingsActivity.class.getName(), //<string name="notification_channel_sound_title">"提示音"</string>
Settings.StorageDashboardActivity.class.getName(), //<string name="storage_settings">"存储"</string>
Settings.PowerUsageSummaryActivity.class.getName(), // <string name="power_usage_summary_title">"电池"</string>
Settings.UserAndAccountDashboardActivity.class.getName(),  // <string name="account_dashboard_title">"用户和帐号"</string>
Settings.SecuritySettingsActivity.class.getName(),  //  <string name="security_settings_title">"安全性与位置信息"</string>
Settings.AccessibilitySettingsActivity.class.getName(), // <string name="accessibility_settings">"无障碍"</string>
Settings.SystemDashboardActivity.class.getName(), //<string name="header_category_system">"系统"</string>
Settings.SupportDashboardActivity.class.getName(), //<string name="system_update_settings_list_item_title">"系统更新"</string>
// Home page > Network & Internet  // 网络与互联网 点击进入后会显示到的Activity
Settings.WifiSettingsActivity.class.getName(), // <string name="wifi_settings_title">"WLAN"</string>
Settings.DataUsageSummaryActivity.class.getName(),//<string name="network_settings_title">"移动网络"</string>
Settings.SimSettingsActivity.class.getName(), // <string name="sim_settings_title">"SIM 卡"</string>
// Home page > Connected devices
Settings.BluetoothSettingsActivity.class.getName(), // <string name="bluetooth_settings_title">"蓝牙"</string>
Settings.WifiDisplaySettingsActivity.class.getName(), //<string name="wifi_display_settings_title">"投射"</string>
Settings.PrintSettingsActivity.class.getName(),  //<string name="print_settings">"打印"</string>
// Home page > Apps & Notifications
Settings.UserSettingsActivity.class.getName(),
Settings.ConfigureNotificationSettingsActivity.class.getName(),
Settings.ManageApplicationsActivity.class.getName(),
Settings.PaymentSettingsActivity.class.getName(),
// Home page > Security & screen lock
Settings.LocationSettingsActivity.class.getName(),
// Home page > System
Settings.LanguageAndInputSettingsActivity.class.getName(), // <string name="language_keyboard_settings_title">"语言和输入法"</string>
Settings.DateTimeSettingsActivity.class.getName(), // <string name="date_and_time_settings_title" >"日期和时间"</string>
Settings.DeviceInfoSettingsActivity.class.getName(), // <string name="about_settings">"关于手机"</string>
Settings.EnterprisePrivacySettingsActivity.class.getName(),
    };
	

	
// 保存各种包含设置Fragment的设置类的集合
public static final String[] ENTRY_FRAGMENTS = {
WifiSettings.class.getName(),  // 
ConfigureWifiSettings.class.getName(),
SavedAccessPointsWifiSettings.class.getName(),
TetherSettings.class.getName(),
WifiP2pSettings.class.getName(),
WifiCallingSettings.class.getName(),
WifiDisplaySettings.class.getName(),
WifiAPITest.class.getName(),
WifiInfo.class.getName(),

.......
};


}




```
  <img src="//../zimage/wireless/wifi/01_wifisetting/24.png" width = "75%" height="75%"/>
```
AndroidmaniFest.xml 文件
一级菜单是完全动态的加载，二级菜单则是动态加载和静态xml布局文件

  在AndroidmaniFest.xml 文件中 定义的Activity 存在 <meta-data>子标签  在该标签内定义了 "com.android.settings.category" 所属类别  
  
   <activity android:name="Settings$NetworkDashboardActivity"  【一级菜单】    //【网络和互联网设置界面Activity AndroidManifest的定义  属于homepage的一级菜单】
       android:label="@string/wifi_settings"
       android:icon="@drable/ic_settings_wireless">
  <meta-data  android:name="com.android.settings.category"
  android:value="com.android.settings.category.ia.homepage" // "com.android.settings.category.ia.homepage"  说明它是设置应用的一级菜单
  android:value="com.android.settings.category.ia.system" // "com.android.settings.category.ia.system"  说明它是设置应用homepage的一级菜单System系统 下的二级菜单
  android:value="com.android.settings.category.ia.development"  // "com.android.settings.category.ia.development"  说明它是设置应用homepage的一级菜单开发者选项下的二级菜单
  android:value="com.android.settings.category.ia.device"  // 二级菜单
  android:value="com.android.settings.category.ia.wireless"   // 二级菜单
  android:value="com.android.settings.category.ia.account"   // 二级菜单
  android:value="com.android.settings.category.ia.app"    // 二级菜单
  android:value="com.android.settings.category.ia.notifications"   // 二级菜单
  /> 
  
<meta-data android:name="com.android.settings.FRAGMENT_CLASS"  该 标签表示 该 Activity 启动时 使用的Fragment的类进行界面的加载
android:value="com.android.settings.network.NetworkDashboardFragment"/>
</activity>



 <activity android:name="Settings$WifiSettingsActivity"   【可以认为是三级菜单了】
                android:taskAffinity=""
                android:label="@string/wifi_settings"
                android:configChanges="orientation|keyboardHidden|screenSize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <action android:name="android.settings.WIFI_SETTINGS" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.VOICE_LAUNCH" />
                <category android:name="com.android.settings.SHORTCUT" />
            </intent-filter>
            <meta-data android:name="com.android.settings.FRAGMENT_CLASS"
                android:value="com.android.settings.wifi.WifiSettings" />  // 指定了加载该Activity显示的Fragment "com.android.settings.wifi.WifiSettings" Fragment
            <meta-data android:name="com.android.settings.TOP_LEVEL_HEADER_ID"
                android:resource="@id/wifi_settings" />
        </activity>
                

<activity-alias android:name="Settings"              【应用入口】
                android:taskAffinity="com.android.settings"  
                android:label="@string/settings_label_launcher"  
                android:launchMode="singleTask"  
                android:targetActivity="Settings">  
            <intent-filter>  
                <action android:name="android.intent.action.MAIN" />  
                <category android:name="android.intent.category.DEFAULT" />  
                <category android:name="android.intent.category.LAUNCHER" />   // Settings是启动的主页面
            </intent-filter>  
            <meta-data android:name="android.app.shortcuts" android:resource="@xml/shortcuts"/>  
</activity-alias>  

Settings.java类 除了大量静态类继承SettingsActivity，就没什么东西了。那再去父类SettingsActivity.java找找

packages\apps\Settings\src\com\android\settings\SettingsActivity.java

  protected void onCreate(Bundle savedState) {  
  .........
  
        
  
        
   final String initialFragmentName = intent.getStringExtra(EXTRA_SHOW_FRAGMENT);  // 获取 <meta-data "com.android.settings.FRAGMENT_CLASS" 对应的显示Fragment的名字 >
  
   mIsShowingDashboard = className.equals(Settings.class.getName());   //当前类是否为Settings.class，即进入方式为点击launcher上的图标进入的主设置界面  
   setContentView(mIsShowingDashboard ?  R.layout.settings_main_dashboard : R.layout.settings_main_prefs);  // 加载布局文件包含两个Framgment 一个search_bar_container 另一个是 main_content Fragment
  
   launchSettingFragment(initialFragmentName, isSubSettings, intent); // 加载界面
  }
  
      

 void launchSettingFragment(String initialFragmentName, boolean isSubSettings, Intent intent) {  
.......
    //进入主页走的这里，替换目标 往 R.layout.settings_main_dashboard  中的 Fragment 中的main_content这个Fragment 填充指定DashboardSummary.class.getName() 名字的 Fragment
     switchToFragment(DashboardSummary.class.getName(), null /* args */, false, false,   mInitialTitleResId, mInitialTitle, false);   // 通过FramentTransaction 完成跳转
.......
    }  
 
 

packages\apps\Settings\src\com\android\settings\dashboard\DashboardSummary.java
DashboardSummary.java，对于它我们主要是想知道它的数据加载，它是怎么加载自己的子项的。
对子项的数据获取在updateCategoryAndSuggestion(）中得到实现



public void onViewCreated(View view，Bundle bundle) {  
  FocusReclerView mDashboard = findViewbyId(R.id.dashboard_container);
  mAdapter = new DashboardAdapter(xxx);
  
  rebuildUI(){ updateCategoryAndSuggestion();}
 }
 


 void updateCategoryAndSuggestion(List<Tile> suggestions) {  
        final Activity activity = getActivity();  
        if (activity == null) {  
            return;  
        }  
        /*根据"com.android.settings.category"的值查询子项数据，这里的值为"com.android.settings.category.ia.homepage"。 
        具体获取办法追踪到frameworks\base\packages\SettingsLib\src\com\android\settingslib\drawer\TileUtils.java中。 
        通过PackageManager查询所有在AndroidManifest.xml中定义<meta-data/>中含有该值的类。注意：会过滤掉非系统级应用的数据！ 
        CategoryKey.CATEGORY_HOMEPAGE = "com.android.settings.category.ia.homepage"*/  
        
        final DashboardCategory category = mDashboardFeatureProvider.getTilesForCategory(CategoryKey.CATEGORY_HOMEPAGE);  
        if (category == null) {  
            return;  
        }  
        mSummaryLoader.updateSummaryToCache(category);  
        if (suggestions != null) {  
            mAdapter.setCategoriesAndSuggestions(category, suggestions);  
        } else {  
            //数据的绑定在适配器中,->packages\apps\Settings\src\com\android\settings\dashboard\DashboardAdapter.java  
            mAdapter.setCategory(category);   // 往适配器 填充 Category
        }  
    }  

*************************追踪mDashboardFeatureProvider.getTilesForCategory******************************
思路:
1. 遍历Androidmanifest.xml 所有的组件  每一个组件都是 Tilt 那么就成为  List<Tile> 这个集合(和组件一样长度)
2. 遍历Androidmanifest.xml 所有的组件  每一个组件 取得metaData 得到Key为   Pair<String, String> key = new Pair<String, String>(activityInfo.packageName,activityInfo.name);  
   Value为 Tile的 Map<Pair<String,String>,Tile>  这个集合(和组件一样长度)
3. 依次遍历Tile ， Tile 中包含 tile.category  ，  从categoryMap 取得key 为 tile.category 的 DashboardCategory ， 并把 自身Tile 添加到该DashboardCategory 中 category.addTile(tile);
   HashMap<String, DashboardCategory> categoryMap = new HashMap<>();
  category中的List<Tile> tiles 用于保存  Tile
 
 
 
 
 
    @Override  DashboardFeatureProviderImpl.java
    public DashboardCategory getTilesForCategory(String key) {
        return mCategoryManager.getTilesByCategory(mContext, key);
    }





    CategoryManager.java   TileUtils.SETTING_PKG = "com.android.settings"    categoryKey="com.android.settings.category.ia.homepage"
    public synchronized DashboardCategory getTilesByCategory(Context context, String categoryKey) {
        return getTilesByCategory(context, categoryKey, TileUtils.SETTING_PKG);
    }

    CategoryManager.java
    public synchronized DashboardCategory getTilesByCategory(Context context, String categoryKey,
            String settingPkg) {
        tryInitCategories(context, settingPkg);

        return mCategoryByKeyMap.get(categoryKey); // private final Map<String, DashboardCategory> mCategoryByKeyMap;
    }



    CategoryManager.java
    private synchronized void tryInitCategories(Context context, boolean forceClearCache,
            String settingPkg) {
        if (mCategories == null) {
            if (forceClearCache) {
                mTileByComponentCache.clear();
            }
            mCategoryByKeyMap.clear();
    // Tile cache (key: <packageName, activityName>, value: tile) private final Map<Pair<String, String>, Tile> mTileByComponentCache;
    // Tile cache (key: category key, value: category) private final Map<String, DashboardCategory> mCategoryByKeyMap;
  
// 从AndroidManiest.xml获得DashboardCategory集合 List<DashboardCategory> mCategories;  private String mExtraAction;
            mCategories = TileUtils.getCategories(context, mTileByComponentCache,  
                    false /* categoryDefinedInManifest */, mExtraAction, settingPkg);  //  private List<DashboardCategory> mCategories;
            for (DashboardCategory category : mCategories) {
                mCategoryByKeyMap.put(category.key, category);
            }
            backwardCompatCleanupForCategory(mTileByComponentCache, mCategoryByKeyMap);
            sortCategories(context, mCategoryByKeyMap);
            filterDuplicateTiles(mCategoryByKeyMap);
        }
    }



TileUtils.java
    public static List<DashboardCategory> getCategories(Context context,
            Map<Pair<String, String>, Tile> cache, boolean categoryDefinedInManifest,
            String extraAction, String settingPkg) {
        final long startTime = System.currentTimeMillis();
        boolean setup = Global.getInt(context.getContentResolver(), Global.DEVICE_PROVISIONED, 0)
                != 0;
        ArrayList<Tile> tiles = new ArrayList<>();
        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        for (UserHandle user : userManager.getUserProfiles()) {
            // TODO: Needs much optimization, too many PM queries going on here.
            if (user.getIdentifier() == ActivityManager.getCurrentUser()) {
                // Only add Settings for this user.
                getTilesForAction(context, user, SETTINGS_ACTION, cache, null, tiles, true,
                        settingPkg);
                getTilesForAction(context, user, OPERATOR_SETTINGS, cache,
                        OPERATOR_DEFAULT_CATEGORY, tiles, false, true, settingPkg);
                getTilesForAction(context, user, MANUFACTURER_SETTINGS, cache,
                        MANUFACTURER_DEFAULT_CATEGORY, tiles, false, true, settingPkg);
            }
            if (setup) {
                getTilesForAction(context, user, EXTRA_SETTINGS_ACTION, cache, null, tiles, false,
                        settingPkg);
                if (!categoryDefinedInManifest) {  // run Here  IA_SETTINGS_ACTION="com.android.settings.action.IA_SETTINGS"
                    getTilesForAction(context, user, IA_SETTINGS_ACTION, cache, null, tiles, false,settingPkg);
                    if (extraAction != null) {
                        getTilesForAction(context, user, extraAction, cache, null, tiles, false,settingPkg);
                    }
                }
            }
        }

        HashMap<String, DashboardCategory> categoryMap = new HashMap<>();
        for (Tile tile : tiles) { // 遍历所有的Title
            DashboardCategory category = categoryMap.get(tile.category); // 通过Tile的 category 拿到 对应的 category
            if (category == null) {
                category = createCategory(context, tile.category, categoryDefinedInManifest);
                if (category == null) {
                    Log.w(LOG_TAG, "Couldn't find category " + tile.category);
                    continue;
                }
                categoryMap.put(category.key, category);
            }
            category.addTile(tile); // 往Category 增加 Tile
        }
        ArrayList<DashboardCategory> categories = new ArrayList<>(categoryMap.values());
        for (DashboardCategory category : categories) {
            Collections.sort(category.tiles, TILE_COMPARATOR);
        }
        Collections.sort(categories, CATEGORY_COMPARATOR);
        if (DEBUG_TIMING) Log.d(LOG_TAG, "getCategories took "
                + (System.currentTimeMillis() - startTime) + " ms");
        return categories;
    }


TileUtils.java    // 创建DashboardCategory
    private static DashboardCategory createCategory(Context context, String categoryKey,
            boolean categoryDefinedInManifest) {
        DashboardCategory category = new DashboardCategory();
        category.key = categoryKey;
        if (!categoryDefinedInManifest) {
            return category;
        }
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> results = pm.queryIntentActivities(new Intent(categoryKey), 0);
        if (results.size() == 0) {
            return null;
        }
        for (ResolveInfo resolved : results) { // 过滤掉所有非系统设置的ResolveInfo  
            if (!resolved.system) {
                // Do not allow any app to add to settings, only system ones.
                continue;
            }
            category.title = resolved.activityInfo.loadLabel(pm);
            category.priority = SETTING_PKG.equals(
                    resolved.activityInfo.applicationInfo.packageName) ? resolved.priority : 0;
            if (DEBUG) Log.d(LOG_TAG, "Adding category " + category.title);
        }

        return category;
    }
    
    
TileUtils.java
    private static void getTilesForAction(Context context,
            UserHandle user, String action, Map<Pair<String, String>, Tile> addedCache,
            String defaultCategory, ArrayList<Tile> outTiles, boolean requireSettings,
            boolean usePriority, String settingPkg) {
        Intent intent = new Intent(action);
        if (requireSettings) {
            intent.setPackage(settingPkg);
        }
        getTilesForIntent(context, user, intent, addedCache, defaultCategory, outTiles,usePriority, true, true);
    }


TileUtils.java
    public static void getTilesForIntent(
            Context context, UserHandle user, Intent intent,
            Map<Pair<String, String>, Tile> addedCache, String defaultCategory, List<Tile> outTiles,
            boolean usePriority, boolean checkCategory, boolean forceTintExternalIcon,
            boolean shouldUpdateTiles) {
        PackageManager pm = context.getPackageManager();

       List<ResolveInfo> results = pm.queryIntentActivitiesAsUser(intent,PackageManager.GET_META_DATA, user.getIdentifier()); // 每一个AndroidmaniFest组件都是一个Tile
       Map<String, IContentProvider> providerMap = new HashMap<>();
        for (ResolveInfo resolved : results) { // 查看当前AndroidManifest.xml 中定义的四大组件
            ActivityInfo activityInfo = resolved.activityInfo; // 获得ActivityInfo信息
            Bundle metaData = activityInfo.metaData;
            String categoryKey = metaData.getString(EXTRA_CATEGORY_KEY); // EXTRA_CATEGORY_KEY ="com.android.settings.category" 获得 该key对应的值

// 创建Map<Pair<String, String>, Tile> mTileByComponentCache; 对应的 key ---Pair<String, String> key 
            Pair<String, String> key = new Pair<String, String>(activityInfo.packageName,activityInfo.name);  
            Tile tile = addedCache.get(key);
            if (tile == null) {
                tile = new Tile();  // 创建Tile类
                tile.intent = new Intent().setClassName(activityInfo.packageName, activityInfo.name); // 设置Tile类的Intent 
                tile.category = categoryKey;
                tile.priority = usePriority ? resolved.priority : 0;
                tile.metaData = activityInfo.metaData;
                updateTileData(context, tile, activityInfo, activityInfo.applicationInfo,pm, providerMap, forceTintExternalIcon);
                addedCache.put(key, tile);  //  往  Map<Pair<String, String>, Tile> mTileByComponentCache;   填充数据
            } else if (shouldUpdateTiles) {
                updateSummaryAndTitle(context, providerMap, tile);
            }

}


TileUtils.java // 更新 Tile的数据
    private static boolean updateTileData(Context context, Tile tile,
            ActivityInfo activityInfo, ApplicationInfo applicationInfo, PackageManager pm,
            Map<String, IContentProvider> providerMap, boolean forceTintExternalIcon) {
        if (applicationInfo.isSystemApp()) {
            boolean forceTintIcon = false;
            int icon = 0;
            Pair<String, Integer> iconFromUri = null;
            CharSequence title = null;
            String summary = null;
            String keyHint = null;
            boolean isIconTintable = false;

            // Get the activity's meta-data
            try {
                Resources res = pm.getResourcesForApplication(applicationInfo.packageName);
                Bundle metaData = activityInfo.metaData;

                if (forceTintExternalIcon
                        && !context.getPackageName().equals(applicationInfo.packageName)) {
                    isIconTintable = true;
                    forceTintIcon = true;
                }

                if (res != null && metaData != null) {
                    if (metaData.containsKey(META_DATA_PREFERENCE_ICON)) {
                        icon = metaData.getInt(META_DATA_PREFERENCE_ICON);
                    }
                    if (metaData.containsKey(META_DATA_PREFERENCE_ICON_TINTABLE)) {
                        if (forceTintIcon) {
                            Log.w(LOG_TAG, "Ignoring icon tintable for " + activityInfo);
                        } else {
                            isIconTintable =
                                    metaData.getBoolean(META_DATA_PREFERENCE_ICON_TINTABLE);
                        }
                    }
                    int resId = 0;
                    if (metaData.containsKey(META_DATA_PREFERENCE_TITLE_RES_ID)) {
                        resId = metaData.getInt(META_DATA_PREFERENCE_TITLE_RES_ID);
                        if (resId != 0) {
                            title = res.getString(resId);
                        }
                    }
                    if ((resId == 0) && metaData.containsKey(META_DATA_PREFERENCE_TITLE)) {
                        if (metaData.get(META_DATA_PREFERENCE_TITLE) instanceof Integer) {
                            title = res.getString(metaData.getInt(META_DATA_PREFERENCE_TITLE));
                        } else {
                            title = metaData.getString(META_DATA_PREFERENCE_TITLE);
                        }
                    }
                    if (metaData.containsKey(META_DATA_PREFERENCE_SUMMARY)) {
                        if (metaData.get(META_DATA_PREFERENCE_SUMMARY) instanceof Integer) {
                            summary = res.getString(metaData.getInt(META_DATA_PREFERENCE_SUMMARY));
                        } else {
                            summary = metaData.getString(META_DATA_PREFERENCE_SUMMARY);
                        }
                    }
                    if (metaData.containsKey(META_DATA_PREFERENCE_KEYHINT)) {
                        if (metaData.get(META_DATA_PREFERENCE_KEYHINT) instanceof Integer) {
                            keyHint = res.getString(metaData.getInt(META_DATA_PREFERENCE_KEYHINT));
                        } else {
                            keyHint = metaData.getString(META_DATA_PREFERENCE_KEYHINT);
                        }
                    }
                    if (metaData.containsKey(META_DATA_PREFERENCE_CUSTOM_VIEW)) {
                        int layoutId = metaData.getInt(META_DATA_PREFERENCE_CUSTOM_VIEW);
                        tile.remoteViews = new RemoteViews(applicationInfo.packageName, layoutId);
                        updateSummaryAndTitle(context, providerMap, tile);
                    }
                }
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                if (DEBUG) Log.d(LOG_TAG, "Couldn't find info", e);
            }

            // Set the preference title to the activity's label if no
            // meta-data is found
            if (TextUtils.isEmpty(title)) {
                title = activityInfo.loadLabel(pm).toString();
            }

            // Set the icon 设置 Tile的图标资源
            if (iconFromUri != null) {
                tile.icon = Icon.createWithResource(iconFromUri.first, iconFromUri.second);
            } else {
                if (icon == 0) {
                    icon = activityInfo.icon;
                }
                tile.icon = Icon.createWithResource(activityInfo.packageName, icon); 
            }

            // Set title and summary for the preference  设置 Tile的标题  和  简要
//  title = activityInfo.loadLabel(pm).toString();
//// title 从updateSummaryAndTitle 方法中获得  String overrideTitle = getString(bundle, META_DATA_PREFERENCE_TITLE); // "com.android.settings.title
            tile.title = title;  

//META_DATA_PREFERENCE_SUMMARY = "com.android.settings.summary"
// summary = res.getString(metaData.getInt(META_DATA_PREFERENCE_SUMMARY)); 
// summary = metaData.getString(META_DATA_PREFERENCE_SUMMARY); // 使用 "com.android.settings.summary" 指定的字符串
// summary从updateSummaryAndTitle 方法中获得  String overrideSummary = getString(bundle, META_DATA_PREFERENCE_SUMMARY);
            tile.summary = summary; 

            // Replace the intent with this specific activity
            tile.intent = new Intent().setClassName(activityInfo.packageName,
                    activityInfo.name);
            // Suggest a key for this tile
            tile.key = keyHint;
            tile.isIconTintable = isIconTintable;

            return true;
        }

        return false;
    }




TileUtils.java
    private static void updateSummaryAndTitle(
            Context context, Map<String, IContentProvider> providerMap, Tile tile) {
        if (tile == null || tile.metaData == null
                || !tile.metaData.containsKey(META_DATA_PREFERENCE_SUMMARY_URI)) {
            return;
        }
// META_DATA_PREFERENCE_SUMMARY_URI = "com.android.settings.summary_uri"   如果包含"com.android.settings.summary"这个meta-data
        String uriString = tile.metaData.getString(META_DATA_PREFERENCE_SUMMARY_URI);
        Bundle bundle = getBundleFromUri(context, uriString, providerMap);
        String overrideSummary = getString(bundle, META_DATA_PREFERENCE_SUMMARY);// "com.android.settings.summary"
        String overrideTitle = getString(bundle, META_DATA_PREFERENCE_TITLE); // "com.android.settings.title"
        if (overrideSummary != null) {
            tile.remoteViews.setTextViewText(android.R.id.summary, overrideSummary); // 设置Tile的显示的简要
        }

        if (overrideTitle != null) {
            tile.remoteViews.setTextViewText(android.R.id.title, overrideTitle); // 设置Tile的显示标题
        }
    }



TileUtils.java
    private static Bundle getBundleFromUri(Context context, String uriString,
            Map<String, IContentProvider> providerMap) {
        if (TextUtils.isEmpty(uriString)) {
            return null;
        }
        Uri uri = Uri.parse(uriString);
        String method = getMethodFromUri(uri);
        if (TextUtils.isEmpty(method)) {
            return null;
        }
 //  相当于IContentProvider provider = providerMap.get(uri.getAuthority());
        IContentProvider provider = getProviderFromUri(context, uri, providerMap); 
        if (provider == null) {
            return null;
        }
        try {
            return provider.call(context.getPackageName(), method, uriString, null);  // 反射机制阿
        } catch (RemoteException e) {
            return null;
        }
    }

TileUtils.java
    private static IContentProvider getProviderFromUri(Context context, Uri uri,
            Map<String, IContentProvider> providerMap) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        if (!providerMap.containsKey(authority)) {  //如果没有这个Key  则添加这个Key
            providerMap.put(authority, context.getContentResolver().acquireUnstableProvider(uri));
        }
        return providerMap.get(authority);
    }

TileUtils.java
    static String getMethodFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        if ((pathSegments == null) || pathSegments.isEmpty()) {
            return null;
        }
        return pathSegments.get(0);
    }

  



*******************************************************
~~~~~~~~~~~~~~~~~
public class DashboardCategory  implements Parcelable {

    public CharSequence title;
    public String key;
    public int prioprity;
    public List<Tile> tiles;  // 主要成员  一级菜单对应的 Tile的集合   每个Tile 都包含 title标题  summary简要 Icon图标  Intent等信息

}


~~~~~~~~~~~~~~~~~

```






[/packages/apps/Settings/src/com/android/settings/dashboard/DashboardAdapter.java](file/DashboardAdapter.java)
[/frameworks/base/packages/SettingsLib/src/com/android/settingslib/drawer/Tile.java](file/Tile.java)




```
/packages/apps/Settings/src/com/android/settings/dashboard/DashboardAdapter.java  // 该文件是Setting的主界面适配器类

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardItemHolder>
        implements SummaryLoader.SummaryConsumer, SuggestionDismissController.Callback {
        
        
     DashboardData mDashboardData;  // 数据集合


    public void setCategory(List<DashboardCategory> category) {
        final DashboardData prevData = mDashboardData;
        Log.d(TAG, "adapter setCategory called");
        mDashboardData = new DashboardData.Builder(prevData)
                .setCategories(category)     //【从DashboardSummary 调用setCategory 设置了 数据源】
                .build();
        notifyDashboardDataChanged(prevData);
    }
    
    
    

    private View.OnClickListener mTileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: get rid of setTag/getTag
            mDashboardFeatureProvider.openTileIntent((Activity) mContext, (Tile) v.getTag()); // 打开在Tag中定义的Intent
        }
    };     
        
        
        
        
        
     @Override 【/packages/apps/Settings/src/com/android/settings/dashboard/DashboardFeatureProviderImpl.java】
    public void openTileIntent(Activity activity, Tile tile) {
        if (tile == null) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }

        if (tile.intent == null) {
            return;
        }
        final Intent intent = new Intent(tile.intent)    //  从Title中获取Intent
                .putExtra(SettingsActivity.EXTRA_SOURCE_METRICS_CATEGORY,
                        MetricsEvent.DASHBOARD_SUMMARY)
                .putExtra(SettingsDrawerActivity.EXTRA_SHOW_MENU, true)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        launchIntentOrSelectProfile(activity, tile, intent, MetricsEvent.DASHBOARD_SUMMARY); 【最终调用 activity.startActivityForResult(intent, 0);】
    }
 
        
        
        
    【DashboardData】
    public int size() {
        return mItems.size();
    }


    @Override
    public int getItemCount() {
        return mDashboardData.size();  //【显示的数量来源于 mDashboardData.size() 最终来源于 DashboardData.mItems【List】.size()】
    }
    
    
        @Override
    public DashboardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) { // 绘制该adater的View
        return new DashboardItemHolder(LayoutInflater.from(parent.getContext()).inflate(
                viewType, parent, false));
    }
~~~~~~~~~~~~~~~~~
       public static class DashboardItemHolder extends RecyclerView.ViewHolder { // 绑定ICON  Title 和 summary 对应的 View
        public final ImageView icon;
        public final TextView title;
        public final TextView summary;

        public DashboardItemHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(android.R.id.icon);
            title = itemView.findViewById(android.R.id.title);
            summary = itemView.findViewById(android.R.id.summary);
        }
    }
    
     private void onBindTile(DashboardItemHolder holder, Tile tile) {  // 设置最终显示的字样
        holder.icon.setImageDrawable(mCache.getIcon(tile.icon));
        holder.title.setText(tile.title);
        if (!TextUtils.isEmpty(tile.summary)) {
            holder.summary.setText(tile.summary);
            holder.summary.setVisibility(View.VISIBLE);
        } else {
            holder.summary.setVisibility(View.GONE);
        }
    }
~~~~~~~~~~~~~~~~~
    
    
    @Override
    public void onBindViewHolder(DashboardItemHolder holder, int position) {  // 设置主界面的点击逻辑设置函数
        final int type = mDashboardData.getItemTypeByPosition(position);   // mDashboardData 是数据集合
        switch (type) {
            case R.layout.dashboard_category:
                onBindCategory(holder,
                        (DashboardCategory) mDashboardData.getItemEntityByPosition(position));
                break;
            case R.layout.dashboard_tile: //  表示当前的类型是 catetory_title 类型的
                final Tile tile = (Tile) mDashboardData.getItemEntityByPosition(position);
                onBindTile(holder, tile);   // 绑定当前的ViewItem  和 tile   ，使得显示正确的数据字符  和 图标
                holder.itemView.setTag(tile); // 设置每一项的Tag  Tag来源为  mDashboardData.getItemEntityByPosition(position);
                holder.itemView.setOnClickListener(mTileClickListener); // 设置 catetory_title 的点击函数
                break;
            case R.layout.suggestion_header:
                onBindSuggestionHeader(holder, (DashboardData.SuggestionHeaderData)
                        mDashboardData.getItemEntityByPosition(position));
                break;
            case R.layout.suggestion_tile:
                final Tile suggestion = (Tile) mDashboardData.getItemEntityByPosition(position);
                final String suggestionId = mSuggestionFeatureProvider.getSuggestionIdentifier(
                        mContext, suggestion);
                // This is for cases when a suggestion is dismissed and the next one comes to view
                if (!mSuggestionsShownLogged.contains(suggestionId)) {
                    mMetricsFeatureProvider.action(
                            mContext, MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION, suggestionId);
                    mSuggestionsShownLogged.add(suggestionId);
                }
                onBindTile(holder, suggestion);
                holder.itemView.setOnClickListener(v -> {
                    mMetricsFeatureProvider.action(mContext,
                            MetricsEvent.ACTION_SETTINGS_SUGGESTION, suggestionId);
                    ((SettingsActivity) mContext).startSuggestion(suggestion.intent);
                });
                break;
            case R.layout.condition_card:
                final boolean isExpanded = mDashboardData.getItemEntityByPosition(position)
                        == mDashboardData.getExpandedCondition();
                ConditionAdapterUtils.bindViews(
                        (Condition) mDashboardData.getItemEntityByPosition(position),
                        holder, isExpanded, mConditionClickListener, v -> onExpandClick(v));
                break;
        }
    }

        
}
     

public class DashboardData { // 保存数据的List
    private final List<Item> mItems;
    private final List<DashboardCategory> mCategories;  // DashboardCategory 应该是解析的AndroidManifest.xml 文件中  Acctivity的<meta-data>属性
    private final List<Condition> mConditions;
    private final List<Tile> mSuggestions;
    
    
    public Object getItemEntityByPosition(int position) {
        return mItems.get(position).entity;
    }
    
    
     private void buildItemsData() { // 构建 ITEM
     .....
        for (int i = 0; mCategories != null && i < mCategories.size(); i++) { // 先遍历 mCategories ，  然后再遍历每个DashboardCategory的 tiles
            DashboardCategory category = mCategories.get(i);
            countItem(category, R.layout.dashboard_category,!TextUtils.isEmpty(category.title), NS_ITEMS);
            for (int j = 0; j < category.tiles.size(); j++) {
                Tile tile = category.tiles.get(j);  // title 来自于 category  【/frameworks/base/packages/SettingsLib/src/com/android/settingslib/drawer/Tile.java】
                countItem(tile, R.layout.dashboard_tile, true, NS_ITEMS);  // 资源布局ID  R.layout.dashboard_tile
            }
         }
     .....
     }
     
~~~~~~~~~~~~~~~~~
     /frameworks/base/packages/SettingsLib/src/com/android/settingslib/drawer/Tile.java
    public class Tile implements Parcelable {
    public CharSequence title;
    public CharSequence summary;
    public Icon icon;
    public Intent intent;
    public ArrayList<UserHandle> userHandle = new ArrayList<>();
    public Bundle extras;
    public String category;
    public int priority;
    public Bundle metaData;
    public String key;
    }
~~~~~~~~~~~~~~~~~
    
     
        private void countItem(Object object, int type, boolean add, int nameSpace) {
        if (add) {
            mItems.add(new Item(object【Tile】, type, mId + nameSpace, object == mExpandedCondition)); // 添加Item
        }
        mId++;
    }
    
    
    
    private static class Item {
    public final Object entity;  //  最终能转化为 (/frameworks/base/packages/SettingsLib/src/com/android/settingslib/drawer/Tile.java)  包含 (tile.intent) 
    
    
    public Item(Object entity, @ItemTypes int type, int id, boolean conditionExpanded) {
    this.entity = entity;  // 构造函数   获取到 entity
    this.type = type;
    this.id = id;
    this.conditionExpanded = conditionExpanded;
    }
        
    }
    
    
    
    
    
    
}


        
```



```
网络和互联网界面引用的布局XML文件   二级菜单加载 使用 xml布局文件
// 在AndroidManifest.xml 文件中指定了该NetworkDashboardActivity启动时加载的NetworkDashboardFragment的加载界面类的 <meta-data>数据
/packages/apps/Settings/src/com/android/settings/network/NetworkDashboardFragment.java   
    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.network_and_internet;  // 引入布局的XML文件
 }
 
 
 
 
```
[/packages/apps/Settings/res/xml/network_and_internet.xml](file/network_and_internet.xml)

### WIFI-UA1布局分析


<img src="//../zimage/wireless/wifi/01_wifisetting/23.png" width = "25%" height="25%"/>
[/packages/apps/Settings/res/layout/dashboard_tile.xml](file/dashboard_tile.xml)
```
设置主界面的每一项Item的布局文件 dashboard_tile.xml
/packages/apps/Settings/res/layout/dashboard_tile.xml

<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/dashboard_tile"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?android:attr/selectableItemBackground"
    android:gravity="center_vertical"
    android:minHeight="@dimen/dashboard_tile_minimum_height"
    android:clickable="true"
    android:focusable="true">

    <ImageView
        android:id="@android:id/icon"
        android:layout_width="@dimen/dashboard_tile_image_size"
        android:layout_height="@dimen/dashboard_tile_image_size"
        android:scaleType="centerInside"
        android:layout_marginStart="@dimen/dashboard_tile_image_margin_start"
        android:layout_marginEnd="@dimen/dashboard_tile_image_margin_end" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView android:id="@android:id/title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:singleLine="true"
            android:textAppearance="@style/TextAppearance.TileTitle"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal" />

        <TextView android:id="@android:id/summary"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textAppearance="@style/TextAppearance.Small"
            android:textColor="?android:attr/textColorSecondary"
            android:maxLines="1"
            android:ellipsize="end"
            android:paddingEnd="@dimen/dashboard_tile_image_margin_start" />

    </LinearLayout>

</LinearLayout>

```




<img src="//../zimage/wireless/wifi/01_wifisetting/20.png" width = "25%" height="25%"/>
```
packages/apps/Settings/res/xml/network_and_internet.xml

<PreferenceScreen
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
    android:title="@string/network_dashboard_title">

    <com.android.settings.widget.MasterSwitchPreference
        android:fragment="com.android.settings.wifi.WifiSettings"
        android:key="toggle_wifi"
        android:title="@string/wifi_settings" 【WLAN】
【<string name="summary_placeholder" >&#160;</string> 由WIFI状态动态设置 这里只是占位符】
        android:summary="@string/summary_placeholder" 
        android:icon="@drawable/ic_settings_wireless"
        android:order="-30">
【在配置每个Preference元素节点时，我们可以显示为点击它时所跳转的Intent。点击该Preference，跳转至目标Intent】
        <intent
            android:action="android.settings.WIFI_SETTINGS"
            android:targetClass="Settings$WifiSettingsActivity"/> // WifiSettingsActivity 目标类名 
    </com.android.settings.widget.MasterSwitchPreference>

    <com.android.settingslib.RestrictedPreference
        android:key="mobile_network_settings"
        android:title="@string/network_settings_title"
        android:icon="@drawable/ic_network_cell"
        android:dependency="toggle_airplane"
        android:order="-15"
        settings:keywords="@string/keywords_more_mobile_networks"
        settings:userRestriction="no_config_mobile_networks"
        settings:useAdminDisabledSummary="true">
        <intent
            android:action="android.intent.action.MAIN"
            android:targetPackage="com.android.phone"
            android:targetClass="com.android.phone.MobileNetworkSettings"/>
    </com.android.settingslib.RestrictedPreference>

    <com.android.settingslib.RestrictedPreference
        android:fragment="com.android.settings.TetherSettings"
        android:key="tether_settings"
		【<string name="tether_settings_title_all">"热点和网络共享"</string>】
        android:title="@string/tether_settings_title_all"
        android:icon="@drawable/ic_wifi_tethering"
        android:order="-5"
        android:summary="@string/summary_placeholder"
        settings:userRestriction="no_config_tethering"
        settings:useAdminDisabledSummary="true"/>

    <com.android.settingslib.RestrictedPreference
        android:fragment="com.android.settings.vpn2.VpnSettings"
        android:key="vpn_settings"
        android:title="@string/vpn_settings_title"
        android:icon="@drawable/ic_vpn_key"
        android:order="0"
        android:summary="@string/summary_placeholder"
        settings:userRestriction="no_config_vpn"
        settings:useAdminDisabledSummary="true"/>

    <com.android.settingslib.RestrictedPreference
        android:key="manage_mobile_plan"
        android:title="@string/manage_mobile_plan_title"
        android:persistent="false"
        settings:userRestriction="no_config_mobile_networks"
        settings:useAdminDisabledSummary="true"/>

    <SwitchPreference
        android:key="toggle_airplane"
        android:title="@string/airplane_mode"
        android:icon="@drawable/ic_airplanemode_active"
        android:disableDependentsState="true"
        android:order="5"/>

    <Preference
        android:fragment="com.android.settings.ProxySelector"
        android:key="proxy_settings"
        android:title="@string/proxy_settings_title"/>

</PreferenceScreen>
```



## WIFI UA2分析
###WIFI UA2 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/1.png" width = "25%" height="25%"/>
1. **UA编号**   UA2
1. **UA说明**  ** WIFI功能开关**
1. **UA触发函数**  **在network_and_internet.xml布局文件的Preference指定了Swith开关对应的类 MasterSwitchPreference.java文件中定义的onclick函数
   业务处理函数为： /packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java的 onSwitchToggled 方法
     ```
         <com.android.settings.widget.MasterSwitchPreference    // Switch控件
        android:fragment="com.android.settings.wifi.WifiSettings"  //   触发的Fragment
        android:key="toggle_wifi"
        <intent
            android:action="android.settings.WIFI_SETTINGS"   // 点击content时执行Intent
            android:targetClass="Settings$WifiSettingsActivity"/>
         </com.android.settings.widget.MasterSwitchPreference>
         
             widgetView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSwitch != null && !mSwitch.isEnabled()) {
                        return;
                    }
                    setChecked(!mChecked);
                    if (!callChangeListener(mChecked)) {
                        setChecked(!mChecked);
                    } else {
                        persistBoolean(mChecked);
                    }
                }
            });
            
     ```
1. **UA字符可选值**    1.switch开启     2.switch关闭    3.switch不可用
1. **UA布局及ID**      NetworkDashboardFragment的布局文件network_and_internet.xml中 <com.android.settings.widget.MasterSwitchPreference android:key="toggle_wifi" 自定义控件
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key ** 
1. **数据库可选值 ** 
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**  
1. **UA触发函数所在类**   MasterSwitchPreference.java文件中 onBindViewHolder 定义的onclick函数           

---


###WIFI UA2 代码分析


```
    <com.android.settings.widget.MasterSwitchPreference  【network_and_internet.xml中定义的MasterSwitchPreference 】
        android:fragment="com.android.settings.wifi.WifiSettings"
        android:key="toggle_wifi"
        <intent
            android:action="android.settings.WIFI_SETTINGS"
            android:targetClass="Settings$WifiSettingsActivity"/>
    </com.android.settings.widget.MasterSwitchPreference>
	
	
	继承关系：
	/packages/apps/Settings/src/com/android/settings/widget/MasterSwitchPreference.java extends /frameworks/base/packages/SettingsLib/src/com/android/settingslib/TwoTargetPreference.java
	/frameworks/base/packages/SettingsLib/src/com/android/settingslib/TwoTargetPreference.java extends /frameworks/base/core/java/android/preference/Preference.java
	
	public class MasterSwitchPreference extends TwoTargetPreference {

    private Switch mSwitch;  // Switch控件
    private boolean mChecked;  // 当前开关状态
    private boolean mEnableSwitch = true;  // 当前View是否使能
	
	
	@Override
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_master_switch;
    }
	
	    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        final View widgetView = holder.findViewById(android.R.id.widget_frame);
        if (widgetView != null) {
            widgetView.setOnClickListener(new OnClickListener() { // 为Switch按钮设置点击事件
                @Override
                public void onClick(View v) {
                    if (mSwitch != null && !mSwitch.isEnabled()) {
                        return;
                    }
                    setChecked(!mChecked);
                    if (!callChangeListener(mChecked)) { // 调用监听类通知wifi开关状态改变 在爷爷类 /frameworks/base/core/java/android/preference/Preference.java 定义的方法
                        setChecked(!mChecked);
                    } else {
                        persistBoolean(mChecked);
                    }
                }
            });
        }
        mSwitch = (Switch) holder.findViewById(R.id.switchWidget);
        if (mSwitch != null) {
            mSwitch.setContentDescription(getTitle());
            mSwitch.setChecked(mChecked);
            mSwitch.setEnabled(mEnableSwitch);
        }
    }
	}


 /frameworks/base/core/java/android/preference/Preference.java
 
    private OnPreferenceChangeListener mOnChangeListener;
 
     public interface OnPreferenceChangeListener {
        boolean onPreferenceChange(Preference preference, Object newValue);
    }
    
     protected boolean callChangeListener(Object newValue) {
        return mOnChangeListener == null || mOnChangeListener.onPreferenceChange(this, newValue); // 调用 监听MasterSwitchController类mOnChangeListener的实现的onPreferenceChange接口
    }
    
    
 /packages/apps/Settings/src/com/android/settings/widget/MasterSwitchController.java 继承 
 public class MasterSwitchController extends SwitchWidgetController implements
    Preference.OnPreferenceChangeListener {  // 实现了监听接口 Preference.OnPreferenceChangeListener 

    private final MasterSwitchPreference mPreference; // 该类对应控件network_and_internet.xml中   com.android.settings.widget.MasterSwitchPreference


   @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (mListener != null) {
            return mListener.onSwitchToggled((Boolean) newValue); // 又在onPreferenceChange的监听方法中 继续调用 OnSwitchChangeListener的监听接口 onSwitchToggled
        }
        return false;
    }
    
    }
    

/packages/apps/Settings/src/com/android/settings/widget/SwitchWidgetController.java
public abstract class SwitchWidgetController {

    protected OnSwitchChangeListener mListener;

    public interface OnSwitchChangeListener {
        boolean onSwitchToggled(boolean isChecked);
    }
    }
    
// 判断哪里实现了监听的逻辑代码
/packages/apps/Settings/src/com/android/settings/wifi/WifiMasterSwitchPreferenceController.java

public class WifiMasterSwitchPreferenceController extends PreferenceController
        implements SummaryUpdater.OnSummaryChangeListener,
        LifecycleObserver, OnResume, OnPause, OnStart, OnStop {

    public static final String KEY_TOGGLE_WIFI = "toggle_wifi";

    private MasterSwitchPreference mWifiPreference;  //【该类对应控件network_and_internet.xml中   com.android.settings.widget.MasterSwitchPreference】
    private WifiEnabler mWifiEnabler;
    private final WifiSummaryUpdater mSummaryHelper;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    
    
        @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mWifiPreference = (MasterSwitchPreference) screen.findPreference(KEY_TOGGLE_WIFI【"toggle_wifi"】);  // 对MasterSwitchPreference 得到实例
    }
    
    
        @Override
    public void onStart() {
        mWifiEnabler = new WifiEnabler(mContext, new MasterSwitchController(mWifiPreference),mMetricsFeatureProvider); // 创建WifiEnabler  并添加mWifiPreference 为参数 在WifiEnabler 设置关联监听关系
    }
    

    
    /packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java
public class WifiEnabler implements SwitchWidgetController.OnSwitchChangeListener  {  // 实现了 SwitchWidgetController.OnSwitchChangeListener  接口

    private final SwitchWidgetController mSwitchWidget;
    
        WifiEnabler(Context context, SwitchWidgetController switchWidget,
            MetricsFeatureProvider metricsFeatureProvider,
            ConnectivityManagerWrapper connectivityManagerWrapper) {
        mContext = context;
        mSwitchWidget = switchWidget;
        mSwitchWidget.setListener(this); // 实现了监听关系   SwitchWidgetController.setListener(WifiEnabler)
        
        }
       
       
    @Override
    public boolean onSwitchToggled(boolean isChecked) {  //  当前 UA2 的 Switch Click事件的逻辑处理函数 

        // Show toast message if Wi-Fi is not allowed in airplane mode 如果当前处于飞行模式   那么弹框提示
        if (isChecked && !WirelessUtils.isRadioAllowed(mContext, Settings.Global.RADIO_WIFI)) {
            Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
            mSwitchWidget.setChecked(false);
            return false;
        }

        // Disable tethering if enabling Wifi
        if (mayDisableTethering(isChecked)) {  // 如果当前热点打开的   那么关闭当前的热点
            mConnectivityManager.stopTethering(ConnectivityManager.TETHERING_WIFI);
        }
        if (!mWifiManager.setWifiEnabled(isChecked)) { // 调用mWifiManager.setWifiEnabled 打开或者关闭WIFI  ▲1   UA完成后分析Framework时继续往下分析
            // Error
            mSwitchWidget.setEnabled(true);
            Toast.makeText(mContext, R.string.wifi_error, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    
    
```



## WIFI UA3分析
###WIFI UA3 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/2.png" width = "25%" height="25%"/>
1. **UA编号**   UA3
1. **UA说明**  ** WIFI功能开关**
1. **UA触发函数**  **在network_and_internet.xml布局文件的Preference指定了Swith开关对应的类 MasterSwitchPreference.java文件中定义的onclick函数
   业务处理函数为：  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
   public class WifiSettings extends RestrictedSettingsFragment 中设置处理函数
     ```
             <activity android:name="Settings$WifiSettingsActivity"   WIFI的设置界面WifiSettingsActivity在 AndroidManifest.xml 声明
                android:taskAffinity=""
                android:label="@string/wifi_settings"
                android:configChanges="orientation|keyboardHidden|screenSize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <action android:name="android.settings.WIFI_SETTINGS" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.VOICE_LAUNCH" />
                <category android:name="com.android.settings.SHORTCUT" />
            </intent-filter>
            <meta-data android:name="com.android.settings.FRAGMENT_CLASS"
                android:value="com.android.settings.wifi.WifiSettings" />  // 指定了加载该Activity显示的Fragment "com.android.settings.wifi.WifiSettings" Fragment类
            <meta-data android:name="com.android.settings.TOP_LEVEL_HEADER_ID"
                android:resource="@id/wifi_settings" />
        </activity>
            
     ```
1. **UA字符可选值**    1.switch开启     2.switch关闭    
1. **UA布局及ID**      WifiSettings(Fragment)的布局文件 R.xml.wifi_settings.xml
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key ** 
1. **数据库可选值 ** 
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**  
1. **UA触发函数所在类**   在WifiSettings(Fragment)中创建了新的     new WifiEnabler(activity, new SwitchBarController(activity.getSwitchBar()),mMetricsFeatureProvider);        
   这个WifiEnabler 的onSwitchToggled() 函数是当前页面activity.getSwitchBar()的点击响应事件   同UA2 
---

###WIFI UA3 代码分析
```
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreate(Bundle icicle) {
        addPreferencesFromResource(R.xml.wifi_settings);  // 当前WIFI设置界面的布局文件
        }
        
        
WIFI开关关闭时 显示的字符串
<string name="wifi_empty_list_wifi_off" msgid="8056223875951079463">"要查看可用网络，请打开 WLAN。"</string>
<string name="wifi_scan_notify_text">
"为了提高位置信息的精确度，系统应用和服务仍然会扫描 WLAN 网络。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>

<string name="wifi_scan_notify_text_scanning_off" msgid="3426075479272242098">
"要提高位置信息的精确度，请在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中开启 WLAN 扫描功能。"
</string>


    @Override
    public void onWifiStateChanged(int state) {
    final int wifiState = mWifiManager.getWifiState();
    .....
            case WifiManager.WIFI_STATE_DISABLED:  //  如果当前WIFI是关闭的话
                setOffMessage();  // 显示 关闭字符串
                setProgressBarVisible(false);
                break;
        }
    }

  private void setOffMessage() {
final CharSequence title = getText(R.string.wifi_empty_list_wifi_off);
final CharSequence description = wifiScanningMode ? getText(R.string.wifi_scan_notify_text) : getText(R.string.wifi_scan_notify_text_scanning_off);
        
        final LinkifyUtils.OnClickListener clickListener = new LinkifyUtils.OnClickListener() {
            @Override
            public void onClick() {
                final SettingsActivity activity = (SettingsActivity) getActivity();
                activity.startPreferencePanel(WifiSettings.this,
                        ScanningSettings.class.getName(),
                        null, R.string.location_scanning_screen_title, null, null, 0);
            }
        };
mStatusMessagePreference.setText(title, description, clickListener);  // 设置WIFI开关关闭时 显示的字符串   简要信息和  蓝色链接字点击事件  LinkifyUtils.OnClickListener clickListener 


}



        
     /packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java
     @Override
    public boolean onSwitchToggled(boolean isChecked) {  //  当前 UA2 的 Switch Click事件的逻辑处理函数 

        // Show toast message if Wi-Fi is not allowed in airplane mode 如果当前处于飞行模式   那么弹框提示
        if (isChecked && !WirelessUtils.isRadioAllowed(mContext, Settings.Global.RADIO_WIFI)) {
            Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
            mSwitchWidget.setChecked(false);
            return false;
        }

        // Disable tethering if enabling Wifi
        if (mayDisableTethering(isChecked)) {  // 如果当前热点打开的   那么关闭当前的热点
            mConnectivityManager.stopTethering(ConnectivityManager.TETHERING_WIFI);
        }
        if (!mWifiManager.setWifiEnabled(isChecked)) { // 调用mWifiManager.setWifiEnabled 打开或者关闭WIFI  ▲1   UA完成后分析Framework时继续往下分析
            // Error
            mSwitchWidget.setEnabled(true);
            Toast.makeText(mContext, R.string.wifi_error, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    
``` 

###WIFI UA3 布局分析
R.xml.wifi_settings.xml 布局文件
```
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_settings"  // values-zh-rCN/  <string name="wifi_settings" msgid="29722149822540994">"WLAN"</string>
        settings:keywords="@string/keywords_wifi">

    <PreferenceCategory android:key="connected_access_point" />  // 当前已连接的热点显示的Preference 

    <PreferenceCategory android:key="access_points"/>  // 当前手机扫描到的热点的列表

    <PreferenceCategory android:key="additional_settings"> // 添加网络 Preference Item
        <Preference
                android:key="configure_settings"  // <string name="wifi_configure_settings_preference_title" >"WLAN 偏好设置"</string>   WLAN偏好设置选项
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" />  // 偏好设置选项显示的Fragment   ConfigureWifiSettings

        <Preference
                android:key="saved_networks"      // <string name="wifi_saved_access_points_label">"已保存的网络"</string> 
                android:title="@string/wifi_saved_access_points_label"
                android:fragment="com.android.settings.wifi.SavedAccessPointsWifiSettings" />  // 已保存的网络显示的Fragment   SavedAccessPointsWifiSettings
    </PreferenceCategory> 
</PreferenceScreen>


WIFI开关关闭时 显示的字符串
<string name="wifi_empty_list_wifi_off" msgid="8056223875951079463">"要查看可用网络，请打开 WLAN。"</string>
<string name="wifi_scan_notify_text">
"为了提高位置信息的精确度，系统应用和服务仍然会扫描 WLAN 网络。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"</string>

<string name="wifi_scan_notify_text_scanning_off" msgid="3426075479272242098">
"要提高位置信息的精确度，请在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中开启 WLAN 扫描功能。"
</string>


```


## WIFI UA4分析
###WIFI UA4 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/2.png" width = "25%" height="25%"/> 
1. **UA编号**   UA4
1. **UA说明**  **扫描设置入口**
1. **UA触发函数**   在  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 的 final LinkifyUtils.OnClickListener clickListener 函数进行响应
   ```
     private void setOffMessage() {
     final CharSequence title = getText(R.string.wifi_empty_list_wifi_off);
     final CharSequence description = wifiScanningMode ? getText(R.string.wifi_scan_notify_text) : getText(R.string.wifi_scan_notify_text_scanning_off);
        
        final LinkifyUtils.OnClickListener clickListener = new LinkifyUtils.OnClickListener() {
            @Override
            public void onClick() {
                final SettingsActivity activity = (SettingsActivity) getActivity();
                activity.startPreferencePanel(WifiSettings.this,
                        ScanningSettings.class.getName(),   // 启动另一个 /packages/apps/Settings/src/com/android/settings/location/ScanningSettings.java  Fragment类
                                                            // public class ScanningSettings extends SettingsPreferenceFragment {
                        null, R.string.location_scanning_screen_title, null, null, 0);
            }
        };
        mStatusMessagePreference.setText(title, description, clickListener);  // 设置WIFI开关关闭时 显示的字符串   简要信息和  蓝色链接字点击事件  LinkifyUtils.OnClickListener clickListener 
       }
            
     ```
1. **UA字符可选值**    
    ```
    <string name="wifi_scan_notify_text">
    "为了提高位置信息的精确度，系统应用和服务仍然会扫描 WLAN 网络。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"
    </string>
    ```
1. **UA布局及ID**      WifiSettings(Fragment)的布局文件 R.xml.wifi_settings.xml
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key ** 
1. **数据库可选值 ** 
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   ScanningSettings extends SettingsPreferenceFragment 
1. **UA触发函数所在类**   在WifiSettings(Fragment)中的  LinkifyUtils.OnClickListener
---





## WIFI UA5分析
###WIFI UA5 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/2.png" width = "25%" height="25%"/>
1. **UA编号**   UA5
1. **UA说明**  **WIFI票号设置入口**
1. **UA触发函数**   在  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 的 布局文件存在指定fragment  ConfigureWifiSettings

        /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
       Preference  mConfigureWifiSettingsPreference = findPreference(PREF_KEY_CONFIGURE_WIFI_SETTINGS【"configure_settings"】); // findPreference 函数
          /frameworks/support/v14/preference/src/android/support/v14/preference/PreferenceFragment.java
       @Override
       public Preference findPreference(CharSequence key) {
        if (mPreferenceManager == null) {
            return null;
        }
        return mPreferenceManager.findPreference(key);
        }
        //R.xml.wifi_settings.xml 中 Preference 布局
        <Preference
                android:key="configure_settings"
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" />

1. **UA字符可选值**    
    ```
    <string name="wifi_scan_notify_text">
    "为了提高位置信息的精确度，系统应用和服务仍然会扫描 WLAN 网络。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"
    </string>
    ```
1. **UA布局及ID**      WifiSettings(Fragment)的布局文件 R.xml.wifi_settings.xml 中的  <Preference  android:key="configure_settings"
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key ** 
1. **数据库可选值 ** 
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   ScanningSettings extends SettingsPreferenceFragment 
1. **UA触发函数所在类**   在WifiSettings(Fragment)中的  <Preference   android:key="configure_settings"   android:fragment="com.android.settings.wifi.ConfigureWifiSettings" 指定
---






## WIFI UA6分析
###WIFI UA6 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/3.png" width = "25%" height="25%"/>   <img src="//../zimage/wireless/wifi/01_wifisetting/4.png" width = "25%" height="25%"/>
1. **UA编号**   UA6
1. **UA说明**  **显示WLAN弹出消息 Switch开关**
1. **UA触发函数**   在  /packages/apps/Settings/src/com/android/settings/wifi/ConfigureWifiSettings.java  的 布局文件存在指定fragment  ConfigureWifiSettings

        /packages/apps/Settings/src/com/android/settings/wifi/ConfigureWifiSettings.java
         public class ConfigureWifiSettings extends DashboardFragment {
               private UseOpenWifiPreferenceController mUseOpenWifiPreferenceController; //显示WLAN弹出消息 开关
          //
            protected int getPreferenceScreenResId() {
                return R.xml.wifi_configure_settings; // 当前ConfigureWifiSettings 这个Fragment的布局文件
            }
           //
          protected List<PreferenceController> getPreferenceControllers(Context context) {
           final NetworkScoreManagerWrapper networkScoreManagerWrapper = new NetworkScoreManagerWrapper(context.getSystemService(NetworkScoreManager.class));
          mUseOpenWifiPreferenceController = new UseOpenWifiPreferenceController(context, this,networkScoreManagerWrapper, getLifecycle()); // 创建 显示WLAN弹出消息的 ViewController
           final List<PreferenceController> controllers = new ArrayList<>();
           
          controllers.add(new NotifyOpenNetworksPreferenceController(context, getLifecycle())); 
          }
            //////////////////
          /packages/apps/Settings/src/com/android/settings/wifi/NotifyOpenNetworksPreferenceController.java 
          public class NotifyOpenNetworksPreferenceController extends PreferenceController implements
          LifecycleObserver, OnResume, OnPause {
        
        private static final String KEY_NOTIFY_OPEN_NETWORKS = "notify_open_networks"; //有可用WIFI时通知
        
            @Override
        public boolean handlePreferenceTreeClick(Preference preference) {  // 当Preference被点击时触发的函数
        if (!TextUtils.equals(preference.getKey(), KEY_NOTIFY_OPEN_NETWORKS)) {
            return false;
        }
        if (!(preference instanceof SwitchPreference)) {
            return false;
        }
        Settings.Global.putInt(mContext.getContentResolver(), // 把设置数据库中的"wifi_networks_available_notification_on"依据switch状态进行改变
                Settings.Global.WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON 【"wifi_networks_available_notification_on"】,
                ((SwitchPreference) preference).isChecked() ? 1 : 0);
        return true;
       }

1. **UA字符可选值**    
1. **UA布局及ID**      WifiSettings(Fragment)的布局文件 R.xml.wifi_settings.xml 中的  <Preference  android:key="configure_settings"
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  "wifi_networks_available_notification_on"
1. **数据库可选值 **   0关闭   1打开   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   ConfigureWifiSettings extends SettingsPreferenceFragment 
1. **UA触发函数所在类**   在 ConfigureWifiSettings (Fragment)中的  <SwitchPreference   android:key="notify_open_networks"   指定
---


###WIFI UA6 布局分析
```
R.xml.wifi_configure_settings
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_configure_titlebar">

    <SwitchPreference
            android:key="enable_wifi_wakeup" 
            android:title="@string/wifi_wakeup" // <string name="wifi_wakeup" >"自动开启 WLAN"</string>
            android:icon="@drawable/ic_auto_wifi"
            android:summary="@string/wifi_wakeup_summary" />

    <SwitchPreference
        android:key="use_open_wifi_automatically"
        android:icon="@drawable/ic_open_wifi_autoconnect" 
        android:title="@string/use_open_wifi_automatically_title" // <string name="use_open_wifi_automatically_title" >"连接到开放网络"</string>
        android:summary="@string/use_open_wifi_automatically_summary" /> // <string name="use_open_wifi_automatically_summary">"自动连接到高品质的公共网络"</string>

    <SwitchPreference
            android:key="notify_open_networks"
            android:title="@string/wifi_notify_open_networks"  // <string name="wifi_notify_open_networks" msgid="76298880708051981">"打开网络通知"</string>
            android:icon="@drawable/ic_open_wifi_notifications"
            android:summary="@string/wifi_notify_open_networks_summary" /> //<string name="wifi_notify_open_networks_summary" >"有可用的高品质公共网络时通知我"</string>

    <SwitchPreference
        android:key="wifi_cellular_data_fallback"
        android:title="@string/wifi_cellular_data_fallback_title"
        android:summary="@string/wifi_cellular_data_fallback_summary"/>

    <ListPreference
            android:key="sleep_policy"   // 在安卓8.1中 WIFI的休眠策略被去除  new WifiSleepPolicyPreferenceController(context));类进行处理
            android:title="@string/wifi_setting_sleep_policy_title"// <string name="wifi_setting_sleep_policy_title">"在休眠状态下保持WLAN网络连接"</string>
            android:entries="@array/wifi_sleep_policy_entries"
            android:entryValues="@array/wifi_sleep_policy_values" />

    <Preference
            android:key="install_credentials"
            android:title="@string/wifi_install_credentials"> // <string name="wifi_install_credentials" >"安装证书"</string>
        <intent android:action="android.credentials.INSTALL_AS_USER"
                android:targetPackage="com.android.certinstaller"
                android:targetClass="com.android.certinstaller.CertInstallerMain">
            <extra android:name="install_as_uid" android:value="1010"/>
        </intent>
    </Preference>

    <Preference
            android:key="wifi_calling_settings"
            android:title="@string/wifi_calling_settings_title"
            android:fragment="com.android.settings.WifiCallingSettings"
            settings:keywords="@string/keywords_wifi_calling"/>

    <Preference
            android:key="network_scorer_picker"
            android:title="@string/network_scorer_picker_title" // <string name="network_scorer_picker_title">"网络评分服务提供方"</string>
            android:fragment="com.android.settings.network.NetworkScorerPicker"/>

    <Preference
            android:key="wifi_direct"    //  new WifiP2pPreferenceController(context, getLifecycle(), wifiManager)) 类进行处理
            android:title="@string/wifi_menu_p2p"> // <string name="wifi_menu_p2p">"WLAN直连"</string>  WLAN直连指定了目标Activity类
        <intent android:targetPackage="com.android.settings"
                android:targetClass="com.android.settings.Settings$WifiP2pSettingsActivity"/>
    </Preference>

    <Preference
            android:key="wps_push_button"    // new WpsPreferenceController( context, getLifecycle(), wifiManager, getFragmentManager()) 类进行处理
            android:title="@string/wifi_menu_wps_pbc" />  // <string name="wifi_menu_wps_pbc">"WPS按钮"</string>

    <Preference
            android:key="wps_pin_entry"   // new WpsPreferenceController( context, getLifecycle(), wifiManager, getFragmentManager()) 类进行处理
            android:title="@string/wifi_menu_wps_pin" />  // <string name="wifi_menu_wps_pin">"WPS PIN码输入"</string>

    <Preference
            android:key="mac_address"    // new WifiInfoPreferenceController(context, getLifecycle(), wifiManager)); 类进行处理
            android:title="@string/wifi_advanced_mac_address_title"/>  // <string name="wifi_advanced_mac_address_title" >"MAC地址"</string>

    <Preference
            android:key="current_ip_address"  // new WifiInfoPreferenceController(context, getLifecycle(), wifiManager)); 类进行处理
            android:title="@string/wifi_advanced_ip_address_title"/> //<string name="wifi_advanced_ip_address_title">"IP 地址"</string>

</PreferenceScreen>


```


## WIFI UA9分析
 <img src="//../zimage/wireless/wifi/01_wifisetting/4.png" width = "25%" height="25%"/>
###WIFI UA9 操作列 (同UA6大致相同)
```
R.xml.wifi_configure_settings 中定义了 SwitchPreference   同

new NotifyOpenNetworksPreferenceController(context, getLifecycle() 来完成 SwitchPreference  android:key="notify_open_networks" 的 点击业务事件


    <SwitchPreference
            android:key="notify_open_networks"
            android:title="@string/wifi_notify_open_networks"  // <string name="wifi_notify_open_networks">"打开网络通知"</string>
            android:icon="@drawable/ic_open_wifi_notifications"
            android:summary="@string/wifi_notify_open_networks_summary" /> //<string name="wifi_notify_open_networks_summary" >"有可用的高品质公共网络时通知我"</string>
            


            //////////////////
          /packages/apps/Settings/src/com/android/settings/wifi/NotifyOpenNetworksPreferenceController.java 
          public class NotifyOpenNetworksPreferenceController extends PreferenceController implements
          LifecycleObserver, OnResume, OnPause {
        
        private static final String KEY_NOTIFY_OPEN_NETWORKS = "notify_open_networks"; //有可用WIFI时通知
        
            @Override
        public boolean handlePreferenceTreeClick(Preference preference) {  // 当Preference被点击时触发的函数
        if (!TextUtils.equals(preference.getKey(), KEY_NOTIFY_OPEN_NETWORKS)) {
            return false;
        }
        if (!(preference instanceof SwitchPreference)) {
            return false;
        }
        Settings.Global.putInt(mContext.getContentResolver(), // 把设置数据库中的"wifi_networks_available_notification_on"依据switch状态进行改变
                Settings.Global.WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON 【"wifi_networks_available_notification_on"】,
                ((SwitchPreference) preference).isChecked() ? 1 : 0);
        return true;
       }

```



## WIFI UA10分析
 <img src="//../zimage/wireless/wifi/01_wifisetting/4.png" width = "25%" height="25%"/>
###WIFI UA10 操作列 (同UA6大致相同)
```
R.xml.wifi_configure_settings 中定义了 Preference   同  并指定了目标Class WifiP2pSettingsActivity

    <Preference   
            android:key="wifi_direct"    //  new WifiP2pPreferenceController(context, getLifecycle(), wifiManager)) 类进行处理
            android:title="@string/wifi_menu_p2p"> // <string name="wifi_menu_p2p">"WLAN直连"</string>  WLAN直连指定了目标Activity类
        <intent android:targetPackage="com.android.settings"
                android:targetClass="com.android.settings.Settings$WifiP2pSettingsActivity"/>
    </Preference>
    


public static class WifiP2pSettingsActivity extends SettingsActivity { /* empty */ }


        <activity android:name="Settings$WifiP2pSettingsActivity"
                android:taskAffinity="com.android.settings"
                android:parentActivityName="Settings$WifiSettingsActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.VOICE_LAUNCH" />
            </intent-filter>
            <meta-data android:name="com.android.settings.FRAGMENT_CLASS"
                android:value="com.android.settings.wifi.p2p.WifiP2pSettings" />  // WifiP2pSettings extends Fragment  会被展示出来
        </activity>



/packages/apps/Settings/src/com/android/settings/wifi/p2p/WifiP2pPreferenceController.java

    private void togglePreferences() {
        if (mWifiDirectPref != null) {
            mWifiDirectPref.setEnabled(mWifiManager.isWifiEnabled());  // 当WIFI关闭时  当前的WIFI直连 Preference 不可用
        }
    }
    
```




## WIFI UA12分析
 <img src="//../zimage/wireless/wifi/01_wifisetting/6.png" width = "25%" height="25%"/>
###WIFI UA12 操作列 (同UA6大致相同)
```
ScanningSettings (Fragment)
 在  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 的 final LinkifyUtils.OnClickListener clickListener 函数是入口

    <string name="wifi_scan_notify_text">
    "为了提高位置信息的精确度，系统应用和服务仍然会扫描 WLAN 网络。您可以在<xliff:g id="LINK_BEGIN_0">LINK_BEGIN</xliff:g>扫描设置<xliff:g id="LINK_END_1">LINK_END</xliff:g>中更改此设置。"
    </string>
    
        final LinkifyUtils.OnClickListener clickListener = new LinkifyUtils.OnClickListener() {
            @Override
            public void onClick() {
                final SettingsActivity activity = (SettingsActivity) getActivity();
                activity.startPreferencePanel(WifiSettings.this,   //  进入 ScanningSettings
                        ScanningSettings.class.getName(),
                        null, R.string.location_scanning_screen_title, null, null, 0);
            }
        }
        
        


/packages/apps/Settings/src/com/android/settings/location/ScanningSettings.java
public class ScanningSettings extends SettingsPreferenceFragment {
    private static final String KEY_WIFI_SCAN_ALWAYS_AVAILABLE = "wifi_always_scanning";   //两个Key
    private static final String KEY_BLUETOOTH_SCAN_ALWAYS_AVAILABLE = "bluetooth_always_scanning";
    
    
    
        @Override  // Switch按钮点击的业务处理
    public boolean onPreferenceTreeClick(Preference preference) {  // 设置 "wifi_always_scanning"   和 "bluetooth_always_scanning" 在设置数据库中的值
        String key = preference.getKey();
        if (KEY_WIFI_SCAN_ALWAYS_AVAILABLE.equals(key)) {
            Global.putInt(getContentResolver(), Global.WIFI_SCAN_ALWAYS_AVAILABLE, ((SwitchPreference) preference).isChecked() ? 1 : 0);
        } else if (KEY_BLUETOOTH_SCAN_ALWAYS_AVAILABLE.equals(key)) {
            Global.putInt(getContentResolver(), Global.BLE_SCAN_ALWAYS_AVAILABLE, ((SwitchPreference) preference).isChecked() ? 1 : 0);
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }
    
    
    }
    
```


### WIFI UA12 布局分析
```
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
        android:title="@string/location_scanning_screen_title"
        android:key="scanning_screen">

        <SwitchPreference
            android:title="@string/location_scanning_wifi_always_scanning_title"  // <string name="location_scanning_wifi_always_scanning_title" >"WLAN 扫描"</string>
            android:summary="@string/location_scanning_wifi_always_scanning_description"
            //<string name="location_scanning_wifi_always_scanning_description" >"允许系统应用和服务随时检测 WLAN 网络，以便提高位置信息的精确度。"</string>
            android:defaultValue="true"
            android:key="wifi_always_scanning" />


//<string name="location_scanning_bluetooth_always_scanning_description" >"允许系统应用和服务随时检测蓝牙设备，以便提高位置信息的精确度。"</string>
        <SwitchPreference  // <string name="location_scanning_bluetooth_always_scanning_title" >"蓝牙扫描"</string>
            android:title="@string/location_scanning_bluetooth_always_scanning_title"
            android:summary="@string/location_scanning_bluetooth_always_scanning_description"
            android:defaultValue="true"
            android:key="bluetooth_always_scanning" />

</PreferenceScreen>
```



## WIFI UA13分析
###WIFI UA13 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/7.png" width = "25%" height="25%"/> 
1. **UA编号**   UA13
1. **UA说明**  **当前已连接网络Preference的点击事件**
1. **UA触发函数**   在  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java函数 addConnectedAccessPointPreference
   pref.setFragment(WifiNetworkDetailsFragment.class.getName());设置了当前Preference 点击是 显示的 Fragment    WifiSettings的onPreferenceTreeClick 为点击响应函数
1. **UA字符可选值**    
1. **UA布局及ID**     LongPressAccessPointPreference 的布局    private int mLayoutResId = com.android.internal.R.layout.preference;/frameworks/base/core/res/res/layout/preference.xml
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    WifiNetworkDetailsFragment 
1. **UA触发函数所在类**    WifiNetworkDetailsFragment
---


###WIFI UA13 代码分析
```
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
 
 public class WifiSettings extends RestrictedSettingsFragment implements Indexable, WifiTracker.WifiListener, AccessPointListener, WifiDialog.WifiDialogListener {
        
private static final String PREF_KEY_CONNECTED_ACCESS_POINTS = "connected_access_point";
private PreferenceCategory  mConnectedAccessPointPreferenceCategory；
private PreferenceCategory mAccessPointsPreferenceCategory;
private LinkablePreference mStatusMessagePreference; // 用来显示提示 显示  要查看可用网络，请打开WLAN   或者  正在搜索WLAN网络...   等一些提示信息的 Preference


  @Override
    public void onCreate(Bundle icicle) {
        addPreferencesFromResource(R.xml.wifi_settings);
         // 实例化mConnectedAccessPointPreferenceCategory 已连接WIFI热点 PreferenceCategory
            mConnectedAccessPointPreferenceCategory =  (PreferenceCategory) findPreference(PREF_KEY_CONNECTED_ACCESS_POINTS);
             
            
        Context prefContext = getPrefContext();
        mAddPreference = new Preference(prefContext);
        mAddPreference.setIcon(R.drawable.ic_menu_add_inset);
        mAddPreference.setTitle(R.string.wifi_add_network);
        mStatusMessagePreference = new LinkablePreference(prefContext);  // 创建实例化   LinkablePreference  mStatusMessagePreference
       
       }
    
    
    
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
  @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) { 
//  如果当前 从 preference获取的 Fragment 不为 空  那么  调用   super.onPreferenceTreeClick(preference)  
//  connectPref.setFragment(WifiNetworkDetailsFragment.class.getName())  由于此前程序设置了  已连接热点的 Preference 的fragment 为  WifiNetworkDetailsFragment  所以 程序 调用到这里
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        }

        if (preference instanceof LongPressAccessPointPreference) {
            mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            if (mSelectedAccessPoint == null) {
                return false;
            }
            if (mSelectedAccessPoint.isActive()) {
                return super.onPreferenceTreeClick(preference);
            }
            /**
             * Bypass dialog and connect to unsecured networks, or previously connected saved
             * networks, or Passpoint provided networks.
             */
            WifiConfiguration config = mSelectedAccessPoint.getConfig();
            if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) {
                mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(mSelectedAccessPoint.getConfig(), mSelectedAccessPoint.isSaved());
            } else if (mSelectedAccessPoint.isSaved() && config != null
                    && config.getNetworkSelectionStatus() != null
                    && config.getNetworkSelectionStatus().getHasEverConnected()) {
                connect(config, true /* isSavedNetwork */);
            } else if (mSelectedAccessPoint.isPasspoint()) {
                // Access point provided by an installed Passpoint provider, connect using
                // the associated config.
                connect(config, true /* isSavedNetwork */);
            } else {
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);
            }
        } else if (preference == mAddPreference) {
            onAddNetworkPressed();
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }
    
############
/frameworks/base/core/java/android/preference/PreferenceFragment.java    WifiSettings.java  的 爷爷类
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference.getFragment() != null && getActivity() instanceof OnPreferenceStartFragmentCallback) {
            return ((OnPreferenceStartFragmentCallback)getActivity()【SettingsActivity】).onPreferenceStartFragment(this, preference); // 调用 onPreferenceStartFragment
        }
        return false;
    }
    
    
/packages/apps/Settings/src/com/android/settings/SettingsActivity.java
public class SettingsActivity extends SettingsDrawerActivity
        implements PreferenceManager.OnPreferenceTreeClickListener,
        PreferenceFragment.OnPreferenceStartFragmentCallback【由于当前的Activity SettingsActivity 实现了  PreferenceFragment.OnPreferenceStartFragmentCallback 接口】,
        // 所以 调用到了 SettingsActivity 方法实现的接口方法 onPreferenceStartFragment
        ButtonBarHandler, FragmentManager.OnBackStackChangedListener {


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        startPreferencePanel(caller, pref.getFragment(), pref.getExtras(), -1, pref.getTitle(), null, 0);  //
        return true;
    }
    
    
    public void startPreferencePanel(Fragment caller, String fragmentClass, Bundle args,int titleRes, CharSequence titleText, Fragment resultTo, int resultRequestCode) {
        String title = null;
        if (titleRes < 0) {
            if (titleText != null) {
                title = titleText.toString();
            } else {
                title = "";
            }
        }
        // 完成 跳转
        Utils.startWithFragment(this, fragmentClass, args, resultTo, resultRequestCode,titleRes, title, mIsShortcut, mMetricsFeatureProvider.getMetricsCategory(caller));
    }
    }
    
    
    /packages/apps/Settings/src/com/android/settings/Utils.java
    
public static void startWithFragment(Context context, String fragmentName, Bundle args,Fragment resultTo, int resultRequestCode, int titleResId,CharSequence title, int metricsCategory) {
        startWithFragment(context, fragmentName, args, resultTo, resultRequestCode,null /* titleResPackageName */, titleResId, title, false /* not a shortcut */, metricsCategory);
    }
    
    
-------------
    private void setOffMessage() {
        mStatusMessagePreference.setText(title, description, clickListener);
        removeConnectedAccessPointPreference();
        mAccessPointsPreferenceCategory.removeAll();
    // 当当前WLAN 不可用时  把 mStatusMessagePreference 放到 mAccessPointsPreferenceCategory中
        mAccessPointsPreferenceCategory.addPreference(mStatusMessagePreference);
    }
    
    
    
        public static void startWithFragment(Context context, String fragmentName, Bundle args,Fragment resultTo, int resultRequestCode, int titleResId, CharSequence title, boolean isShortcut, int metricsCategory) {
        Intent intent = onBuildStartFragmentIntent(context, fragmentName, args, null /* titleResPackageName */, titleResId, title, isShortcut, metricsCategory);
        if (resultTo == null) {
            context.startActivity(intent);  // intent 启动Activity 
        } else {
            resultTo.getActivity().startActivityForResult(intent, resultRequestCode);  // intent 启动Activity 
        }
    }
       
       
------------
           private void updateAccessPointPreferences() {
           
           
         final List<AccessPoint> accessPoints = mWifiTracker.getAccessPoints();  //从 mWifiTracker 获得AccessPoint ▲2     UA完成后分析Framework时继续往下分析
         boolean hasAvailableAccessPoints = false;
        mAccessPointsPreferenceCategory.removePreference(mStatusMessagePreference); // 把提示信息 LinkablePreference mStatusMessagePreference 移除
        cacheRemoveAllPrefs(mAccessPointsPreferenceCategory);  //  把当前的Preference 缓存到 cache中  然后 填充新的 Prefernce
        
        
        
         // 如果显示已连接WIFI项 那么 index就从1开始 因为index=0 就是已连接的WIFI热点，如果 不显示已连接WIIF网络  那么index=0开始遍历 显示所有的扫描热点 
        int index =configureConnectedAccessPointPreferenceCategory(accessPoints) ? 1 : 0;
        int numAccessPoints = accessPoints.size();
        for (; index < numAccessPoints; index++) {
           
            AccessPoint accessPoint = accessPoints.get(index);  // 获得对应索引位置的 AccessPoint
            // Ignore access points that are out of range.
            if (accessPoint.isReachable()) {  //  // 当accessPoint的 mRssi 有被赋予正常值时  就表示 当前AccessPoint 可达
                String key = accessPoint.getBssid();  // 获得类似mac地址的 bssid 字符串   private String bssid;
                if (TextUtils.isEmpty(key)) {
                    key = accessPoint.getSsidStr();  // 如果 bssid为空  那么 就获取 热点的 ssid 热点名为 key
                }
                hasAvailableAccessPoints = true;
                // 依据 key 来从 cacheRemoveAllPrefs 方法 填充的  ArrayMap<String, Preference> mPreferenceCache 获取Preference 并强制转换为 LongPressAccessPointPreference  
                LongPressAccessPointPreference pref = (LongPressAccessPointPreference) getCachedPreference(key);
                if (pref != null) {
                    pref.setOrder(index);  // 设置 Preference 的显示位置
                    continue;  // 重新开始  for循环
                }
                
                // 如果从 cache中取得的 Preference为空  那么 就需要新建 new LongPressAccessPointPreference
                LongPressAccessPointPreference preference = createLongPressActionPointPreference(accessPoint);
                preference.setKey(key);  //  设置 key 索引 
                preference.setOrder(index);  // 设置 显示的位置 索引 
                if (mOpenSsid != null && mOpenSsid.equals(accessPoint.getSsidStr())
                        && !accessPoint.isSaved()
                        && accessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {  // mOpenSsid 保存的是已连接网络的 ssid
                    onPreferenceTreeClick(preference);  // 如果当前的preference 就是 需要连接的网络  那么 触发 onPreferenceTreeClick 事件 进行连接
                    mOpenSsid = null;
                }
                mAccessPointsPreferenceCategory.addPreference(preference);  // 把该 LongPressAccessPointPreference 添加到  mAccessPointsPreferenceCategory
                accessPoint.setListener(WifiSettings.this);  // accessPoint添加监听类为 WifiSettings
                preference.refresh();
            }  //   if (accessPoint.isReachable())----end
        }  // for end
        removeCachedPrefs(mAccessPointsPreferenceCategory);  //   把 ArrayMap<String, Preference> mPreferenceCache; 置空 mPreferenceCache = null;
        mAddPreference.setOrder(index); 
        mAccessPointsPreferenceCategory.addPreference(mAddPreference); // for循环 结束后 添加 mAddPreference 这个  添加 网络的 mPreference
        setAdditionalSettingsSummaries();

        if (!hasAvailableAccessPoints) {  //如果 没有搜索到 任何  可达 的 AccessPoint 那么 就显示 正在搜索WLAN网络… 
            setProgressBarVisible(true);
            Preference pref = new Preference(getPrefContext());
            pref.setSelectable(false);
            pref.setSummary(R.string.wifi_empty_list_wifi_on); // <string name="wifi_empty_list_wifi_on" >"正在搜索WLAN网络…"</string>
            pref.setOrder(index++);
            pref.setKey(PREF_KEY_EMPTY_WIFI_LIST);
            mAccessPointsPreferenceCategory.addPreference(pref);
        } else {
            // Continuing showing progress bar for an additional delay to overlap with animation
            getView().postDelayed(mHideProgressBarRunnable, 1700 /* delay millis */);  // 有搜索到WIFI 那么就一直显示 ProgressBar动画   间隔 1.7秒
        }
    }
    
 ##############
 /frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
     public static final int UNREACHABLE_RSSI = Integer.MIN_VALUE;
     private int mRssi = UNREACHABLE_RSSI;
    
     public boolean isReachable() {
        return mRssi != UNREACHABLE_RSSI; // 当mRssi 有被赋予正常值时  就表示 当前AccessPoint 可达
    }
    }
    
    AccessPointListener mAccessPointListener;
    
        public void setListener(AccessPointListener listener) {
        mAccessPointListener = listener;
    }
    

    public interface AccessPointListener {
        void onAccessPointChanged(AccessPoint accessPoint);
        void onLevelChanged(AccessPoint accessPoint);
    }
    
 ##############
/packages/apps/Settings/src/com/android/settings/SettingsPreferenceFragment.java    

 private ArrayMap<String, Preference> mPreferenceCache;
 
     protected void cacheRemoveAllPrefs(PreferenceGroup group) {
        mPreferenceCache = new ArrayMap<String, Preference>();
        final int N = group.getPreferenceCount();
        for (int i = 0; i < N; i++) {
            Preference p = group.getPreference(i);
            if (TextUtils.isEmpty(p.getKey())) {
                continue;
            }
            mPreferenceCache.put(p.getKey(), p);
        }
    }
    
    
     protected void removeCachedPrefs(PreferenceGroup group) {
        for (Preference p : mPreferenceCache.values()) {
            group.removePreference(p);
        }
        mPreferenceCache = null;
    }
 
 ##############
 /packages/apps/Settings/src/com/android/settings/SettingsPreferenceFragment.java  
  private ArrayMap<String, Preference> mPreferenceCache;
  
    protected Preference getCachedPreference(String key) {
        return mPreferenceCache != null ? mPreferenceCache.remove(key) : null;  // 依据之前的 cacheRemoveAllPrefs() 方法 填充了 mPreferenceCache， 现在从这个cache 取出对应的 Preference
    }
    
    
 ##############
  /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
        private LongPressAccessPointPreference createLongPressActionPointPreference( AccessPoint accessPoint) {
        return new LongPressAccessPointPreference(accessPoint, getPrefContext(), mUserBadgeCache,false, R.drawable.ic_wifi_signal_0, this);
    }
    
    
----------------
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java

     // Configure the ConnectedAccessPointPreferenceCategory and return true if the Category was shown.
     //配置当前已连接的热点  如果函数 configureConnectedAccessPointPreferenceCategory() 返回为true  那么就显示 mConnectedAccessPointPreferenceCategory 这个以连接 PreferenceCategory
     // 如果 返回为 false 那么就不显示已连接 WIFI 这个 PreferenceCategory
    private boolean configureConnectedAccessPointPreferenceCategory( List<AccessPoint> accessPoints) {
        if (accessPoints.size() == 0) {  // 如果List<AccessPoint> accessPoints 大小为0  说明 没有搜到WIFI网络 也就没有已连接网络
            removeConnectedAccessPointPreference();  // 去除 mConnectedAccessPointPreferenceCategory.removeAll();  并设置为不可见
            return false;
        }

        AccessPoint connectedAp = accessPoints.get(0);   // 获取排序第一的  index=0 的那么热点 AccessPoint   
        if (!connectedAp.isActive()) {  // 如果该热点不是isActive 不是可用  那么 也去掉mConnectedAccessPointPreferenceCategory显示 
            removeConnectedAccessPointPreference();
            return false;
        }

        // Is the preference category empty?
        if (mConnectedAccessPointPreferenceCategory.getPreferenceCount() == 0) { // 如果当前 mConnectedAccessPointPreferenceCategory 没有 Preference 那么就添加 当前的 Preference 返回 true
            addConnectedAccessPointPreference(connectedAp);  // 新建 当前已连接的  LongPressAccessPointPreference
            return true;
        }

        // Is the previous currently connected SSID different from the new one?
        if (!((AccessPointPreference)
                mConnectedAccessPointPreferenceCategory.getPreference(0))
                        .getAccessPoint().getSsidStr().equals(
                                connectedAp.getSsidStr())) {  // 如果当前mConnectedAccessPointPreferenceCategory已经有了prefernce 那么需要对比  把旧的移除 添加新的
            removeConnectedAccessPointPreference();  
            addConnectedAccessPointPreference(connectedAp);
            return true;
        }

        // Else same AP is connected, simply refresh the connected access point preference
        // (first and only access point in this category).
        ((LongPressAccessPointPreference) mConnectedAccessPointPreferenceCategory.getPreference(0))
                .refresh();
        return true;
    }
    
}

###########
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    private void removeConnectedAccessPointPreference() {
        mConnectedAccessPointPreferenceCategory.removeAll();
        mConnectedAccessPointPreferenceCategory.setVisible(false);
    }
    


    private void addConnectedAccessPointPreference(AccessPoint connectedAp) {
        String key = connectedAp.getBssid();
        LongPressAccessPointPreference pref = (LongPressAccessPointPreference)getCachedPreference(key); // 从cache 获取 LongPressAccessPointPreference
        if (pref == null) {
            pref = createLongPressActionPointPreference(connectedAp); // 如果cache为空 那么 new  LongPressActionPointPreference
        }

        // Save the state of the current access point in the bundle so that we can restore it
        // in the Wifi Network Details Fragment
        pref.getAccessPoint().saveWifiState(pref.getExtras());
        pref.setFragment(WifiNetworkDetailsFragment.class.getName());  // 设置当前 Preference的 目标 Fragment 是 WifiNetworkDetailsFragment.class.getName()
        pref.refresh();

        mConnectedAccessPointPreferenceCategory.addPreference(pref);  // 添加 当前已连接WIFI 的 Preference 到 mConnectedAccessPointPreferenceCategory
        mConnectedAccessPointPreferenceCategory.setVisible(true); // 设置  当前已连接WIFI 可见 
    }
    
    
#############
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java

 private NetworkInfo mNetworkInfo;
 private int networkId = WifiConfiguration.INVALID_NETWORK_ID【-1】;
  
    public boolean isActive() {
        return mNetworkInfo != null &&
                (networkId != WifiConfiguration.INVALID_NETWORK_ID ||
                 mNetworkInfo.getState() != State.DISCONNECTED);
    }
    

    public void saveWifiState(Bundle savedState) {
        if (ssid != null) savedState.putString(KEY_SSID, getSsidStr());
        savedState.putInt(KEY_SECURITY, security);
        savedState.putInt(KEY_PSKTYPE, pskType);
        if (mConfig != null) savedState.putParcelable(KEY_CONFIG, mConfig);
        savedState.putParcelable(KEY_WIFIINFO, mInfo);
        evictOldScanResults();
        savedState.putParcelableArrayList(KEY_SCANRESULTCACHE,
                new ArrayList<ScanResult>(mScanResultCache.values()));
        if (mNetworkInfo != null) {
            savedState.putParcelable(KEY_NETWORKINFO, mNetworkInfo);
        }
        if (mFqdn != null) {
            savedState.putString(KEY_FQDN, mFqdn);
        }
        if (mProviderFriendlyName != null) {
            savedState.putString(KEY_PROVIDER_FRIENDLY_NAME, mProviderFriendlyName);
        }
    }



------------------
UA13 的  LongPressAccessPointPreference 
/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java
public class LongPressAccessPointPreference extends AccessPointPreference {

    private final Fragment mFragment;
    
        public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(mFragment);
            view.itemView.setTag(this);
            view.itemView.setLongClickable(true);
        }
    }
}



/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java

public class AccessPointPreference extends Preference {

    <string name="accessibility_wifi_one_bar">"WLAN 信号强度为一格。"</string>
    <string name="accessibility_wifi_two_bars" >"WLAN 信号强度为两格。"</string>
    <string name="accessibility_wifi_three_bars" >"WLAN 信号强度为三格。"</string>
    <string name="accessibility_wifi_signal_full" >"WLAN 信号满格。"</string>
    
    static final int[] WIFI_CONNECTION_STRENGTH = {
            R.string.accessibility_wifi_one_bar,
            R.string.accessibility_wifi_two_bars,
            R.string.accessibility_wifi_three_bars,
            R.string.accessibility_wifi_signal_full
    };


    public AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache cache,boolean forSavedNetworks) {
        setWidgetLayoutResource(R.layout.access_point_friction_widget);  // 布局文件为  access_point_friction_widget.xml
        
        }
        
}

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java

import android.support.v7.preference.PreferenceViewHolder;

public class AccessPointPreference extends Preference {

 private TextView mTitleView;  // 显示  各个 热点名字的  TextView
 
    @Override
    public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mAccessPoint == null) {
            // Used for dummy pref.
            return;
        }
        Drawable drawable = getIcon();
        if (drawable != null) {
            drawable.setLevel(mLevel); 
        }

        mTitleView = (TextView) view.findViewById(com.android.internal.R.id.title);  // 显示  各个 热点名字的  TextView
        if (mTitleView != null) {
            // Attach to the end of the title view
            mTitleView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mBadge, null);
            mTitleView.setCompoundDrawablePadding(mBadgePadding);
        }
        view.itemView.setContentDescription(mContentDescription);  // 设置 简要text  在 refresh() 方法中查看  mContentDescription  获取的值

        ImageView frictionImageView = (ImageView) view.findViewById(R.id.friction_icon);
        bindFrictionImage(frictionImageView);
    }

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java
    protected void updateIcon(int level, Context context) {
        if (level == -1) {
            safeSetDefaultIcon();
            return;
        }
        TronUtils.logWifiSettingsBadge(context, mWifiBadge);
        Drawable drawable = NetworkBadging.getWifiIcon(level, mWifiBadge, getContext().getTheme()); // 依据 level 获得 wifi信号 图标
        if (!mForSavedNetworks && drawable != null) {
            drawable.setTint(Utils.getColorAttr(context, android.R.attr.colorControlNormal));
            setIcon(drawable); // 设置 图标 
        } else {
            safeSetDefaultIcon();
        }
    }



/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPointPreference.java
    public void refresh() {
        if (mForSavedNetworks) {
            setTitle(mAccessPoint.getConfigName());  // 在此处设置 标题显示的字符串
        } else {
            setTitle(mAccessPoint.getSsid()); // 在此处设置 标题显示的字符串
        }

        final Context context = getContext();
        int level = mAccessPoint.getLevel();  // 获得信号的强度值
        int wifiBadge = mAccessPoint.getBadge();
        if (level != mLevel || wifiBadge != mWifiBadge) {
            mLevel = level;
            mWifiBadge = wifiBadge;
            updateIcon(mLevel, context);
            notifyChanged();
        }

        updateBadge(context);

        setSummary(mForSavedNetworks ? mAccessPoint.getSavedNetworkSummary(): mAccessPoint.getSettingsSummary());

        mContentDescription = getTitle();
        if (getSummary() != null) {
            mContentDescription = TextUtils.concat(mContentDescription, ",", getSummary());
        }
        if (level >= 0 && level < WIFI_CONNECTION_STRENGTH.length) {
            mContentDescription = TextUtils.concat(mContentDescription, ",",
                    getContext().getString(WIFI_CONNECTION_STRENGTH[level]));
        }
    }
    
    
    
#########
/frameworks/support/v7/preference/src/android/support/v7/preference/PreferenceViewHolder.java
public class PreferenceViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mCachedViews = new SparseArray<>(4);
    /* package */ PreferenceViewHolder(View itemView) {
        super(itemView);

        // Pre-cache the views that we know in advance we'll want to find
        mCachedViews.put(android.R.id.title, itemView.findViewById(android.R.id.title)); // title 标题
        mCachedViews.put(android.R.id.summary, itemView.findViewById(android.R.id.summary));  // 简要
        mCachedViews.put(android.R.id.icon, itemView.findViewById(android.R.id.icon));  // 图标
        mCachedViews.put(R.id.icon_frame, itemView.findViewById(R.id.icon_frame));
        mCachedViews.put(AndroidResources.ANDROID_R_ICON_FRAME,itemView.findViewById(AndroidResources.ANDROID_R_ICON_FRAME));
    }
    
#############
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java

    public int getLevel() {
        return WifiManager.calculateSignalLevel(mRssi, SIGNAL_LEVELS【5】);
    }



    public String getSavedNetworkSummary() {
        WifiConfiguration config = mConfig;
        if (config != null) {
            PackageManager pm = mContext.getPackageManager();
            String systemName = pm.getNameForUid(android.os.Process.SYSTEM_UID);
            int userId = UserHandle.getUserId(config.creatorUid);
            ApplicationInfo appInfo = null;
            if (config.creatorName != null && config.creatorName.equals(systemName)) {
                appInfo = mContext.getApplicationInfo();
            } else {
                try {
                    IPackageManager ipm = AppGlobals.getPackageManager();
                    appInfo = ipm.getApplicationInfo(config.creatorName, 0 /* flags */, userId);
                } catch (RemoteException rex) {
                }
            }
            if (appInfo != null &&
                    !appInfo.packageName.equals(mContext.getString(R.string.settings_package)) &&
                    !appInfo.packageName.equals(
                    mContext.getString(R.string.certinstaller_package))) {
                return mContext.getString(R.string.saved_network, appInfo.loadLabel(pm));
     //  <string name="saved_network">"由<xliff:g id="NAME">%1$s</xliff:g>保存"</string>
            }
        }
        return "";
    }
    


private WifiConfiguration mConfig;

    private String getSettingsSummary(WifiConfiguration config) {
        // Update to new summary
        StringBuilder summary = new StringBuilder();

        if (isActive() && config != null && config.isPasspoint()) { // 一.  1.当前 AccessPoint 是Active   2.  WifiConfiguration mConfig 不为空  3. 当前 mConfig 类型为 passpoint网络
            // This is the active connection on passpoint
            summary.append(getSummary(mContext, getDetailedState(),false, config.providerFriendlyName));
        } else if (isActive()) { // 二.   1.当前 AccessPoint 是Active
            // This is the active connection on non-passpoint network
            summary.append(getSummary(mContext, getDetailedState(),mInfo != null && mInfo.isEphemeral()));
        } else if (config != null && config.isPasspoint() && config.getNetworkSelectionStatus().isNetworkEnabled()) {
            String format = mContext.getString(R.string.available_via_passpoint);
            //  <string name="available_via_passpoint" msgid="1617440946846329613">"可通过%1$s连接"</string>
            summary.append(String.format(format, config.providerFriendlyName));
        } else if (config != null && config.hasNoInternetAccess()) {
            int messageID = config.getNetworkSelectionStatus().isNetworkPermanentlyDisabled() ? R.string.wifi_no_internet_no_reconnect : R.string.wifi_no_internet;
            //<string name="wifi_no_internet_no_reconnect" msgid="5724903347310541706">"无法自动连接"</string> 
            //<string name="wifi_no_internet" msgid="3880396223819116454">"无法连接到互联网"</string>
            summary.append(mContext.getString(messageID));
        } else if (config != null && !config.getNetworkSelectionStatus().isNetworkEnabled()) {
            WifiConfiguration.NetworkSelectionStatus networkStatus =config.getNetworkSelectionStatus();
            switch (networkStatus.getNetworkSelectionDisableReason()) {
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_AUTHENTICATION_FAILURE:
                    summary.append(mContext.getString(R.string.wifi_disabled_password_failure));
                    // <string name="wifi_disabled_password_failure" msgid="8659805351763133575">"身份验证出现问题"</string>
                    break;
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_DHCP_FAILURE:
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_DNS_FAILURE:
                    summary.append(mContext.getString(R.string.wifi_disabled_network_failure));
                    // <string name="wifi_disabled_network_failure" msgid="2364951338436007124">"IP 配置失败"</string>
                    break;
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_ASSOCIATION_REJECTION:
                    summary.append(mContext.getString(R.string.wifi_disabled_generic));
                    // <string name="wifi_disabled_generic" msgid="4259794910584943386">"已停用"</string>
                    break;
            }
        } else if (config != null && config.getNetworkSelectionStatus().isNotRecommended()) {
            summary.append(mContext.getString(R.string.wifi_disabled_by_recommendation_provider));
            //   <string name="wifi_disabled_by_recommendation_provider" msgid="5168315140978066096">"网络质量较差，因此未连接"</string>
        } else if (!isReachable()) { // Wifi out of range
            summary.append(mContext.getString(R.string.wifi_not_in_range));
            // <string name="wifi_not_in_range" msgid="1136191511238508967">"不在范围内"</string> 
        } else { // In range, not disabled.
            if (config != null) { // Is saved network
                summary.append(mContext.getString(R.string.wifi_remembered));
                // <string name="wifi_remembered" msgid="4955746899347821096">"已保存"</string>
            }
        }

        if (WifiTracker.sVerboseLogging > 0) { // 如果打开了 开发者选项的   wifi-verbose 按钮  那么
            // Add RSSI/band information for this config, what was seen up to 6 seconds ago
            // verbose WiFi Logging is only turned on thru developers settings
            if (mInfo != null && mNetworkInfo != null) { // This is the active connection
                summary.append(" f=" + Integer.toString(mInfo.getFrequency()));  // f=5724  打印出当前的 通信信道
            }
            summary.append(" " + getVisibilityStatus());  // 返回  WifiConfiguration 的信息
            if (config != null && !config.getNetworkSelectionStatus().isNetworkEnabled()) {
                summary.append(" (" + config.getNetworkSelectionStatus().getNetworkStatusString());
                if (config.getNetworkSelectionStatus().getDisableTime() > 0) {
                    long now = System.currentTimeMillis();
                    long diff = (now - config.getNetworkSelectionStatus().getDisableTime()) / 1000;
                    long sec = diff%60; //seconds
                    long min = (diff/60)%60; //minutes
                    long hour = (min/60)%60; //hours
                    summary.append(", ");
                    if (hour > 0) summary.append(Long.toString(hour) + "h ");
                    summary.append( Long.toString(min) + "m ");
                    summary.append( Long.toString(sec) + "s ");  //   添加 断开连接的时间
                }
                summary.append(")");
            }

            if (config != null) {
                WifiConfiguration.NetworkSelectionStatus networkStatus = config.getNetworkSelectionStatus();
                for (int index = WifiConfiguration.NetworkSelectionStatus.NETWORK_SELECTION_ENABLE 【0】;
                        index < WifiConfiguration.NetworkSelectionStatus.NETWORK_SELECTION_DISABLED_MAX 【12 】; index++) {
                    if (networkStatus.getDisableReasonCounter(index) != 0) {
                        summary.append(" " + WifiConfiguration.NetworkSelectionStatus.getNetworkDisableReasonString(index) + "="+ networkStatus.getDisableReasonCounter(index));
                    }
                }
            }
        }
        return summary.toString();
    }
    
#############
/frameworks/base/wifi/java/android/net/wifi/WifiConfiguration.java

private int mStatus;

        public static final String[] QUALITY_NETWORK_SELECTION_STATUS = {
                "NETWORK_SELECTION_ENABLED",
                "NETWORK_SELECTION_TEMPORARY_DISABLED",
                "NETWORK_SELECTION_PERMANENTLY_DISABLED"};
                
        public String getNetworkStatusString() {
            return QUALITY_NETWORK_SELECTION_STATUS[mStatus];
        }


        public static String getNetworkDisableReasonString(int reason) {
            if (reason >= NETWORK_SELECTION_ENABLE && reason < NETWORK_SELECTION_DISABLED_MAX) {
                return QUALITY_NETWORK_SELECTION_DISABLE_REASON[reason];
            } else {
                return null;
            }
        }
        

        public int getDisableReasonCounter(int reason) {
            if (reason >= NETWORK_SELECTION_ENABLE【0】 && reason < NETWORK_SELECTION_DISABLED_MAX【12】) {
                return mNetworkSeclectionDisableCounter[reason];
            } else {
                throw new IllegalArgumentException("Illegal reason value: " + reason);
            }
        }


 Arrays.fill(mNetworkSeclectionDisableCounter, NETWORK_SELECTION_ENABLE);
 
        public static final String[] QUALITY_NETWORK_SELECTION_DISABLE_REASON = {  // 连接失败的原因
                "NETWORK_SELECTION_ENABLE",
                "NETWORK_SELECTION_DISABLED_BAD_LINK", // deprecated
                "NETWORK_SELECTION_DISABLED_ASSOCIATION_REJECTION ",
                "NETWORK_SELECTION_DISABLED_AUTHENTICATION_FAILURE",
                "NETWORK_SELECTION_DISABLED_DHCP_FAILURE",
                "NETWORK_SELECTION_DISABLED_DNS_FAILURE",
                "NETWORK_SELECTION_DISABLED_WPS_START",
                "NETWORK_SELECTION_DISABLED_TLS_VERSION",
                "NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_CREDENTIALS",
                "NETWORK_SELECTION_DISABLED_NO_INTERNET",
                "NETWORK_SELECTION_DISABLED_BY_WIFI_MANAGER",
                "NETWORK_SELECTION_DISABLED_BY_USER_SWITCH"
        };


---------------

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    /**
     * Returns the visibility status of the WifiConfiguration.
     *
     * @return autojoin debugging information
     * TODO: use a string formatter
     * ["rssi 5Ghz", "num results on 5GHz" / "rssi 5Ghz", "num results on 5GHz"]
     * For instance [-40,5/-30,2]
     */
 private WifiInfo mInfo;
 private int mRankingScore = Integer.MIN_VALUE;
 
 private String getVisibilityStatus() {
        StringBuilder visibility = new StringBuilder();
        StringBuilder scans24GHz = null;
        StringBuilder scans5GHz = null;
        String bssid = null;

        long now = System.currentTimeMillis();

        if (mInfo != null) {
            bssid = mInfo.getBSSID();
            if (bssid != null) {
                visibility.append(" ").append(bssid); // 显示当前连接wifi的 Bssid
            }
            visibility.append(" rssi=").append(mInfo.getRssi());  //信号实时量
            visibility.append(" ");
            visibility.append(" score=").append(mInfo.score);  // 当前 wifi网络的 评分
            if (mRankingScore != Integer.MIN_VALUE) {
              visibility.append(" rankingScore=").append(getRankingScore()); // 排行
            }
            if (mBadge != NetworkBadging.BADGING_NONE) {
              visibility.append(" badge=").append(getBadge());
            }
            visibility.append(String.format(" tx=%.1f,", mInfo.txSuccessRate));  // public double txSuccessRate;  成功发送速率
            visibility.append(String.format("%.1f,", mInfo.txRetriesRate));   //public double txRetriesRate;  重传发送速率  
            visibility.append(String.format("%.1f ", mInfo.txBadRate));   //  public double txBadRate;   失败发送速率
            visibility.append(String.format("rx=%.1f", mInfo.rxSuccessRate));  //  public double rxSuccessRate; 成功接收的速率
        }

        int rssi5 = WifiConfiguration.INVALID_RSSI;
        int rssi24 = WifiConfiguration.INVALID_RSSI;
        int num5 = 0;
        int num24 = 0;
        int numBlackListed = 0;
        int n24 = 0; // Number scan results we included in the string
        int n5 = 0; // Number scan results we included in the string
        evictOldScanResults();
        // TODO: sort list by RSSI or age
        for (ScanResult result : mScanResultCache.values()) {

            if (result.frequency >= LOWER_FREQ_5GHZ  && result.frequency <= HIGHER_FREQ_5GHZ) {
                // Strictly speaking: [4915, 5825]
                // number of known BSSID on 5GHz band
                num5 = num5 + 1;
            } else if (result.frequency >= LOWER_FREQ_24GHZ  && result.frequency <= HIGHER_FREQ_24GHZ) {
                // Strictly speaking: [2412, 2482]
                // number of known BSSID on 2.4Ghz band
                num24 = num24 + 1;
            }


            if (result.frequency >= LOWER_FREQ_5GHZ && result.frequency <= HIGHER_FREQ_5GHZ) {
                if (result.level > rssi5) {
                    rssi5 = result.level;
                }
                 scans5GHz.append("max=").append(max5Grssi); // 在安卓8.1 显示 最大的 max的 5G信号量
                if (n5 < 4) {  // 显示最多四个 5G 信道信息
                    if (scans5GHz == null) scans5GHz = new StringBuilder();
                    scans5GHz.append(" \n{").append(result.BSSID);  // 添加  {  bssid = frequency，
                    if (bssid != null && result.BSSID.equals(bssid)) scans5GHz.append("*");
                    scans5GHz.append("=").append(result.frequency);
                    scans5GHz.append(",").append(result.level);
                     scans5GHz.append(",").append(now - result.timestamp/1000*1000).append("s");  //  用于显示在几秒之前探测到的结果
                    scans5GHz.append("}");
                    n5++;
                }
            } else if (result.frequency >= LOWER_FREQ_24GHZ && result.frequency <= HIGHER_FREQ_24GHZ) {
                if (result.level > rssi24) {
                    rssi24 = result.level;
                }
                scans24GHz.append("max=").append(max24Grssi); // 在安卓8.1 显示 最大的 max的 2.4G信号量
                if (n24 < 4) {   // 显示最多四个 2.4G 信道信息
                    if (scans24GHz == null) scans24GHz = new StringBuilder();
                    scans24GHz.append(" \n{").append(result.BSSID);
                    if (bssid != null && result.BSSID.equals(bssid)) scans24GHz.append("*");
                    scans24GHz.append("=").append(result.frequency);
                    scans24GHz.append(",").append(result.level);
                    scans5GHz.append(",").append(now - result.timestamp/1000*1000).append("s");  //  用于显示在几秒之前探测到的结果
                    scans24GHz.append("}");
                    n24++;
                }
            }
        }
        visibility.append(" [");
        if (num24 > 0) {
            visibility.append("(").append(num24).append(")");
            if (n24 <= 4) {
                if (scans24GHz != null) {
                    visibility.append(scans24GHz.toString());
                }
            } else {
                visibility.append("max=").append(rssi24); // 2.4G 最好的信号质量 信号量
                if (scans24GHz != null) {
                    visibility.append(",").append(scans24GHz.toString());
                }
            }
        }
        visibility.append(";");
        if (num5 > 0) {
            visibility.append("(").append(num5).append(")");
            if (n5 <= 4) {
                if (scans5GHz != null) {
                    visibility.append(scans5GHz.toString());
                }
            } else {
                visibility.append("max=").append(rssi5);  // 5G 最好的信号质量 信号量
                if (scans5GHz != null) {
                    visibility.append(",").append(scans5GHz.toString());
                }
            }
        }
        if (numBlackListed > 0)
            visibility.append("!").append(numBlackListed);
        visibility.append("]");

        return visibility.toString();  // 返回 简要显示的 字符串 Summary
    }
    
    
    
------------------------------------

    public static String getSummary(Context context, String ssid, DetailedState state,boolean isEphemeral, String passpointProvider) {
        if (state == DetailedState.CONNECTED && ssid == null) {
            if (TextUtils.isEmpty(passpointProvider) == false) {
                // Special case for connected + passpoint networks.
                String format = context.getString(R.string.connected_via_passpoint);
           // <string name="connected_via_passpoint" msgid="2826205693803088747">"已通过%1$s连接"</string>    已通过 passpoint 网络连接 
           
                return String.format(format, passpointProvider);
            } else if (isEphemeral) {
                // Special case for connected + ephemeral networks.
                final NetworkScoreManager networkScoreManager = context.getSystemService( NetworkScoreManager.class);
                NetworkScorerAppData scorer = networkScoreManager.getActiveScorer();
                if (scorer != null && scorer.getRecommendationServiceLabel() != null) {
                    String format = context.getString(R.string.connected_via_network_scorer);
               //  <string name="connected_via_network_scorer" msgid="5713793306870815341">"已通过%1$s自动连接"</string>   已通过 passpoint网络  自动连接
                    return String.format(format, scorer.getRecommendationServiceLabel());
                } else {
                    return context.getString(R.string.connected_via_network_scorer_default);
               //  <string name="connected_via_network_scorer_default" msgid="7867260222020343104">"已自动连接（通过网络评分服务提供方）"</string>
                }
            }
        }
        //  if (state == DetailedState.CONNECTED && ssid == null) {----------end
        // Case when there is wifi connected without internet connectivity.
        final ConnectivityManager cm = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (state == DetailedState.CONNECTED) {
            IWifiManager wifiManager = IWifiManager.Stub.asInterface( ServiceManager.getService(Context.WIFI_SERVICE));
            NetworkCapabilities nc = null;
            try {
                nc = cm.getNetworkCapabilities(wifiManager.getCurrentNetwork());
            } catch (RemoteException e) {}

            if (nc != null) {
                if (nc.hasCapability(nc.NET_CAPABILITY_CAPTIVE_PORTAL)) {   //  如果当前网络的 NetworkCapabilities  属于 NET_CAPABILITY_CAPTIVE_PORTAL PORTAL网络 那么显示   登录到网络
                    return context.getString(com.android.internal.R.string.network_available_sign_in);
                    // <string name="network_available_sign_in" msgid="1848877297365446605">"登录到网络"</string>
                } else if (!nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) { 
            //  如果当前网络的 NetworkCapabilities 包含  NET_CAPABILITY_VALIDATED【网络有效】 返回为false 那么显示  当前网络无   internet访问能力 
            // <string name="wifi_connected_no_internet" msgid="3149853966840874992">"已连接，但无法访问互联网"</string>
                    return context.getString(R.string.wifi_connected_no_internet);
                }
            }
        }
        if (state == null) {
            Log.w(TAG, "state is null, returning empty summary");
            return "";
        }
        String[] formats = context.getResources().getStringArray((ssid == null)? R.array.wifi_status : R.array.wifi_status_with_ssid); // 依据 ssid 是否为空 获取 字符数组
        int index = state【DetailedState】.ordinal();  // 获得当前State 在 emnu数组中的 索引    因为状态是数组索引是一一对应的关系

        if (index >= formats.length || formats[index].length() == 0) {
            return "";
        }
        return String.format(formats[index], ssid);  //  返回字符串
    }



  <string-array name="wifi_status">
    <item msgid="1922181315419294640"></item>
    <item msgid="8934131797783724664">"正在扫描..."</item>
    <item msgid="8513729475867537913">"正在连接..."</item>
    <item msgid="515055375277271756">"正在进行身份验证..."</item>
    <item msgid="1943354004029184381">"正在获取IP地址..."</item>
    <item msgid="4221763391123233270">"已连接"</item>
    <item msgid="624838831631122137">"已暂停"</item>
    <item msgid="7979680559596111948">"正在断开连接..."</item>
    <item msgid="1634960474403853625">"已断开连接"</item>
    <item msgid="746097431216080650">"失败"</item>
    <item msgid="6367044185730295334">"已停用"</item>
    <item msgid="503942654197908005">"暂时关闭（网络状况不佳）"</item>
  </string-array>
  
  
    <string-array name="wifi_status_with_ssid">
    <item msgid="7714855332363650812"></item>
    <item msgid="8878186979715711006">"正在扫描..."</item>
    <item msgid="355508996603873860">"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item msgid="554971459996405634">"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item msgid="7928343808033020343">"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item msgid="8937994881315223448">"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item msgid="1330262655415760617">"已暂停"</item>
    <item msgid="7698638434317271902">"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item msgid="197508606402264311">"已断开连接"</item>
    <item msgid="8578370891960825148">"失败"</item>
    <item msgid="5660739516542454527">"已停用"</item>
    <item msgid="1805837518286731242">"暂时关闭（网络状况不佳）"</item>
  </string-array>
  
##############
/frameworks/base/core/java/android/net/NetworkInfo.java

public class NetworkInfo implements Parcelable {

    public enum DetailedState {   // 与字符串数组   <string-array name="wifi_status_with_ssid">     <string-array name="wifi_status"> 的索引是一一对应的关系
        IDLE,/** Ready to start data connection setup. */
        SCANNING,    /** Searching for an available access point. */
        CONNECTING,   /** Currently setting up data connection. */
        AUTHENTICATING,/** Network link established, performing authentication. */
        OBTAINING_IPADDR,/** Awaiting response from DHCP server in order to assign IP address information. */
        CONNECTED, /** IP traffic should be available. */
        SUSPENDED,/** IP traffic is suspended */
        DISCONNECTING,/** Currently tearing down data connection. */
        DISCONNECTED, /** IP traffic not available. */
        FAILED, /** Attempt to connect failed. */
        BLOCKED,  /** Access to this network is blocked. */
        VERIFYING_POOR_LINK,  /** Link has poor connectivity. */
        CAPTIVE_PORTAL_CHECK  /** Checking if network is a captive portal */
    }




#########

/frameworks/base/wifi/java/android/net/wifi/WifiManager.java
    public static int calculateSignalLevel(int rssi, int numLevels) {
        if (rssi <= MIN_RSSI 【-100】) {
            return 0;
        } else if (rssi >= MAX_RSSI【-50】) {
            return numLevels - 1;
        } else {
            float inputRange = (MAX_RSSI - MIN_RSSI); // 50
            float outputRange = (numLevels - 1); // 4 
            return (int)((float)(rssi - MIN_RSSI) * outputRange / inputRange);  // 在 -50 到 -100 之间 取值
        }
    }
    

###########
/frameworks/base/core/java/android/net/NetworkBadging.java

    @NonNull public static Drawable getWifiIcon(@IntRange(from=0, to=4) int signalLevel, @Badging int badging, @Nullable Theme theme) {
        return Resources.getSystem().getDrawable(getWifiSignalResource(signalLevel), theme);
    }


    @DrawableRes private static int getWifiSignalResource(int signalLevel) {
        switch (signalLevel) {
            case 0:
                return com.android.internal.R.drawable.ic_wifi_signal_0;  // 信号图标资源ID
            case 1:
                return com.android.internal.R.drawable.ic_wifi_signal_1;
            case 2:
                return com.android.internal.R.drawable.ic_wifi_signal_2;
            case 3:
                return com.android.internal.R.drawable.ic_wifi_signal_3;
            case 4:
                return com.android.internal.R.drawable.ic_wifi_signal_4;
            default:
                throw new IllegalArgumentException("Invalid signal level: " + signalLevel);
        }
    }
    
```

###WIFI UA13 布局分析
```

     wifi_settings.xml
     <?xml version="1.0" encoding="utf-8"?>

      <PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_settings"
        settings:keywords="@string/keywords_wifi">

    <PreferenceCategory android:key="connected_access_point" /> // 当前已连接网络的PreferenceCategory

    <PreferenceCategory android:key="access_points"/>  // 当前搜索到的网络的PreferenceCategory

    <PreferenceCategory android:key="additional_settings">  //  添加网络的 PreferenceCategory
        <Preference
                android:key="configure_settings"           // WLAN偏好设置
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" />

        <Preference
                android:key="saved_networks"    // 已保存网络Preference
                android:title="@string/wifi_saved_access_points_label"
                android:fragment="com.android.settings.wifi.SavedAccessPointsWifiSettings" />
    </PreferenceCategory>
      </PreferenceScreen>
      
      
  access_point_friction_widget.xml
  
  <?xml version="1.0" encoding="utf-8"?>
<ImageView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/friction_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:contentDescription="@null" />





com.android.internal.R.layout.preference  资源ID
/frameworks/base/core/res/res/layout/preference.xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" 
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:minHeight="?android:attr/listPreferredItemHeight"
    android:gravity="center_vertical"
    android:paddingEnd="?android:attr/scrollbarSize"
    android:background="?android:attr/selectableItemBackground" >

    <ImageView
        android:id="@+android:id/icon"   // 显示  wifi信号质量的图标
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        />

    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="15dip"
        android:layout_marginEnd="6dip"
        android:layout_marginTop="6dip"
        android:layout_marginBottom="6dip"
        android:layout_weight="1">

        <TextView android:id="@+android:id/title"   // 显示  WIFI 热点  名字
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:singleLine="true"
            android:textAppearance="?android:attr/textAppearanceLarge"
            android:ellipsize="marquee"
            android:fadingEdge="horizontal" />

        <TextView android:id="@+android:id/summary"   //   显示 热点 简要
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@android:id/title"
            android:layout_alignStart="@android:id/title"
            android:textAppearance="?android:attr/textAppearanceSmall"
            android:textColor="?android:attr/textColorSecondary"
            android:maxLines="4" />

    </RelativeLayout>

    <!-- Preference should place its actual preference widget here. -->
    <LinearLayout android:id="@+android:id/widget_frame"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:gravity="center_vertical"
        android:orientation="vertical" />

</LinearLayout>

```



## WIFI UA14分析
###WIFI UA14 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/7.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/10.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/>  
1. **UA编号**   UA14
1. **UA说明**  **当前已连接网络Preference的长按事件**
1. **UA触发函数**   在 /packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java 函数onBindViewHolder 设置了 setOnCreateContextMenuListener(mFragment)
   /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java这个Fragemnt 以及setLongClickable(true);   来接受长按事件 
  
1. **UA字符可选值**    
1. **UA布局及ID**    系统上下文 menu
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    WifiNetworkDetailsFragment 
1. **UA触发函数所在类**    WifiNetworkDetailsFragment
---


###WIFI UA14 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java
public class LongPressAccessPointPreference extends AccessPointPreference {

    @Override
    public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(mFragment); // 长按事件的响应类
            view.itemView.setTag(this); // 设置tag 用于保存当前preference
            view.itemView.setLongClickable(true);  // 长按事件使能
        }
    }
    
  }
    







/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) { // 长按 AccessPoint时触发的函数
            Preference preference = (Preference) view.getTag();

            if (preference instanceof LongPressAccessPointPreference) {
            
            //  private AccessPoint mSelectedAccessPoint;  用户长按的Prference 对应的  AccessPoint 
                mSelectedAccessPoint =((LongPressAccessPointPreference) preference).getAccessPoint();
                menu.setHeaderTitle(mSelectedAccessPoint.getSsid()); //  长按 显示的第一个项  为 热点的名字
                if (mSelectedAccessPoint.isConnectable()) {  // 如果当前网络热点有信号 的话 isConnectable() = true
                //   <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>    显示  连接到网络
                    menu.add(Menu.NONE, MENU_ID_CONNECT, 0, R.string.wifi_menu_connect);
                }

                WifiConfiguration config = mSelectedAccessPoint.getConfig();
                // Some configs are ineditable
                if (isEditabilityLockedDown(getActivity(), config)) {  // 判断该热点对应的 WifiConfiguration 是否可修改
                    return;
                }

                if (mSelectedAccessPoint.isSaved() || mSelectedAccessPoint.isEphemeral()) { // 如果当前preference 对应的AccessPoint 是已保存过 或是 临时的话  显示
                    // Allow forgetting a network if either the network is saved or ephemerally
                    // connected. (In the latter case, "forget" blacklists the network so it won't
                    // be used again, ephemerally).
                    // <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
                    menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
                }
                if (mSelectedAccessPoint.isSaved()) {
                //  <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
                    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
                    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                    if (nfcAdapter != null && nfcAdapter.isEnabled() && mSelectedAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                        // Only allow writing of NFC tags for password-protected networks.
                        // <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>  只在 NFC有效的条件下
                        menu.add(Menu.NONE, MENU_ID_WRITE_NFC, 0, R.string.wifi_menu_write_to_nfc);
                    }
                }
            }
    }
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
   static boolean isEditabilityLockedDown(Context context, WifiConfiguration config) {
        return !canModifyNetwork(context, config); //canModifyNetwork 用于判断是否 WifiConfiguration 可修改    
    }
    

/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    static boolean canModifyNetwork(Context context, WifiConfiguration config) {
        if (config == null) {
            return true;
        }
        final DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService( Context.DEVICE_POLICY_SERVICE);
        final PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_DEVICE_ADMIN) && dpm == null) {
            return false;
        }

        boolean isConfigEligibleForLockdown = false;
        if (dpm != null) {
            final ComponentName deviceOwner = dpm.getDeviceOwnerComponentOnAnyUser();
            if (deviceOwner != null) {
                final int deviceOwnerUserId = dpm.getDeviceOwnerUserId();
                try {
                    final int deviceOwnerUid = pm.getPackageUidAsUser(deviceOwner.getPackageName(),deviceOwnerUserId);
                    isConfigEligibleForLockdown = deviceOwnerUid == config.creatorUid;
                } catch (NameNotFoundException e) {
                    // don't care
                }
            }
        }
        if (!isConfigEligibleForLockdown) {
            return true;
        }
//         public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown"; 用于判断是否允许用户修改wifi配置的数据库项  1-true  0 false
        final ContentResolver resolver = context.getContentResolver();
        final boolean isLockdownFeatureEnabled = Settings.Global.getInt(resolver,Settings.Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN, 0) != 0;
        return !isLockdownFeatureEnabled;
    }
    
    
    
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }    
    public int getLevel() {
        return WifiManager.calculateSignalLevel(mRssi, SIGNAL_LEVELS);
    }
    public DetailedState getDetailedState() {
        if (mNetworkInfo != null) {
            return mNetworkInfo.getDetailedState();
        }
        Log.w(TAG, "NetworkInfo is null, cannot return detailed state");
        return null;
    }
    

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
public boolean isSaved() {
        return networkId != WifiConfiguration.INVALID_NETWORK_ID;
    }

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isEphemeral() {
        return mInfo != null && mInfo.isEphemeral() &&
                mNetworkInfo != null && mNetworkInfo.getState() != State.DISCONNECTED;
    }
    

    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
     @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (mSelectedAccessPoint == null) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) { // 用户选择 上下文按钮
            case MENU_ID_CONNECT: {  // 选择  连接到网络
                boolean isSavedNetwork = mSelectedAccessPoint.isSaved(); // 判断当前网络是否有 networkId
                if (isSavedNetwork) {
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork); // 如果有执行 connect(final WifiConfiguration config, boolean isSavedNetwork) 方法
                // // 如果当前网络没有加密认证 则先生成 networkdId 再 connect(final WifiConfiguration config, boolean isSavedNetwork)
                } else if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { 
                    /** Bypass dialog for unsecured networks */
                    mSelectedAccessPoint.generateOpenNetworkConfig();  // 用于产生 WifiConfiguration 
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork);
                } else {
                    showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT); // 弹出 Dialog框 用于 用户输入 一些认证信息才能连接
                }
                return true;
            }
            case MENU_ID_FORGET: {   // 选择 取消保存网络
                forget();   
                return true;
            }
            case MENU_ID_MODIFY: {  // 选择 修改网络
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);
                return true;
            }
            case MENU_ID_WRITE_NFC:  // 选择 写入 NFC标签
                showDialog(WRITE_NFC_DIALOG_ID);
                return true;

        }
        return super.onContextItemSelected(item);
    }
    
   



/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    protected void connect(final WifiConfiguration config, boolean isSavedNetwork) {
        // Log subtype if configuration is a saved network.
        mMetricsFeatureProvider.action(getActivity(), MetricsEvent.ACTION_WIFI_CONNECT, isSavedNetwork);
        mWifiManager.connect(config, mConnectListener);  //  调用 mWifiManager的 connect 方法  ▲3 UA分析完之后分析
    }
    
    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    /* package */ void forget() {
        mMetricsFeatureProvider.action(getActivity(), MetricsEvent.ACTION_WIFI_FORGET);
        if (!mSelectedAccessPoint.isSaved()) {
            if (mSelectedAccessPoint.getNetworkInfo() != null && mSelectedAccessPoint.getNetworkInfo().getState() != State.DISCONNECTED) {
                // Network is active but has no network ID - must be ephemeral.
       //针对临时网络  调用 mWifiManager的 disableEphemeralNetwork 方法  ▲4 UA分析完之后分析
                mWifiManager.disableEphemeralNetwork(AccessPoint.convertToQuotedString(mSelectedAccessPoint.getSsidStr()));
            } else {
                // Should not happen, but a monkey seems to trigger it
                Log.e(TAG, "Failed to forget invalid network " + mSelectedAccessPoint.getConfig());
                return;
            }
        } else if (mSelectedAccessPoint.getConfig().isPasspoint()) {
            mWifiManager.removePasspointConfiguration(mSelectedAccessPoint.getConfig().FQDN); //调用 mWifiManager的 removePasspointConfiguration 方法  ▲5 UA分析完之后分析  针对passpoint网络
        } else {
            mWifiManager.forget(mSelectedAccessPoint.getConfig().networkId, mForgetListener); //调用 mWifiManager的 forget 方法  ▲6 UA分析完之后分析  针对趋同网络
        }

        mWifiTracker.resumeScanning();

        // We need to rename/replace "Next" button in wifi setup context.
        changeNextButtonState(false);
    }
    
    
    
    
    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
      private void showDialog(AccessPoint accessPoint, int dialogMode) {
        if (accessPoint != null) {
            WifiConfiguration config = accessPoint.getConfig();
            if (isEditabilityLockedDown(getActivity(), config) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(),RestrictedLockUtils.getDeviceOwner(getActivity()));
                return;
            }
        }

        if (mDialog != null) {
            removeDialog(WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint;    // 对话框 所显示的 AccessPoint
        mDialogMode = dialogMode;

        showDialog(WIFI_DIALOG_ID);  // 显示Diaglog 对话框
    }
    
    
    
    
/packages/apps/Settings/src/com/android/settings/SettingsPreferenceFragment.java    WifiSettings.java的父父类
    protected void showDialog(int dialogId) {
        if (mDialogFragment != null) {
            Log.e(TAG, "Old dialog fragment not null!");
        }
        mDialogFragment = new SettingsDialogFragment(this, dialogId);  // 创建 SettingsDialogFragment   并调用 show 方法 显示 Dialog对话框
        mDialogFragment.show(getChildFragmentManager(), Integer.toString(dialogId));
    }



    public static class SettingsDialogFragment extends InstrumentedDialogFragment {
        private static final String KEY_DIALOG_ID = "key_dialog_id";
        private static final String KEY_PARENT_FRAGMENT_ID = "key_parent_fragment_id";
        
        public SettingsDialogFragment(DialogCreatable fragment, int dialogId) {
            super(fragment, dialogId);
            if (!(fragment instanceof Fragment)) {
                throw new IllegalArgumentException("fragment argument must be an instance of "
                        + Fragment.class.getName());
            }
            mParentFragment = (Fragment) fragment;
        }
        




        @Override  SettingsDialogFragment.java
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                mDialogId = savedInstanceState.getInt(KEY_DIALOG_ID, 0);
                mParentFragment = getParentFragment();
                int mParentFragmentId = savedInstanceState.getInt(KEY_PARENT_FRAGMENT_ID, -1);
                if (mParentFragment == null) {
                    mParentFragment = getFragmentManager().findFragmentById(mParentFragmentId);
                }
                if (!(mParentFragment instanceof DialogCreatable)) {
                    throw new IllegalArgumentException();
                }
                // This dialog fragment could be created from non-SettingsPreferenceFragment
                if (mParentFragment instanceof SettingsPreferenceFragment) {
                    // restore mDialogFragment in mParentFragment
                    ((SettingsPreferenceFragment) mParentFragment).mDialogFragment = this;
                }
            }
            return ((DialogCreatable) mParentFragment).onCreateDialog(mDialogId); // 调用子类的 onCreateDialog 方法
        }
        }
        
        
        
        
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WIFI_DIALOG_ID:
                AccessPoint ap = mDlgAccessPoint; // For manual launch
                if (ap == null) { // For re-launch from saved state
                    if (mAccessPointSavedState != null) {
                        ap = new AccessPoint(getActivity(), mAccessPointSavedState);
                        // For repeated orientation changes
                        mDlgAccessPoint = ap;
                        // Reset the saved access point data
                        mAccessPointSavedState = null;
                    }
                }
                // If it's null, fine, it's for Add Network
                mSelectedAccessPoint = ap;
                mDialog = new WifiDialog(getActivity(), this, ap, mDialogMode, /* no hide submit/connect */ false); // 查看WIfiDialog对象
                return mDialog;
            case WPS_PBC_DIALOG_ID:
                return new WpsDialog(getActivity(), WpsInfo.PBC);
            case WPS_PIN_DIALOG_ID:
                return new WpsDialog(getActivity(), WpsInfo.DISPLAY);
            case WRITE_NFC_DIALOG_ID:
                if (mSelectedAccessPoint != null) {
                    mWifiToNfcDialog = new WriteWifiConfigToNfcDialog(
                            getActivity(),
                            mSelectedAccessPoint.getSecurity(),
                            new WifiManagerWrapper(mWifiManager));
                } else if (mWifiNfcDialogSavedState != null) {
                    mWifiToNfcDialog = new WriteWifiConfigToNfcDialog(getActivity(),
                            mWifiNfcDialogSavedState, new WifiManagerWrapper(mWifiManager));
                }

                return mWifiToNfcDialog;
        }
        return super.onCreateDialog(dialogId);
    }



###########
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

class WifiDialog extends AlertDialog implements WifiConfigUiBase, DialogInterface.OnClickListener {

    public interface WifiDialogListener {
        void onForget(WifiDialog dialog);
        void onSubmit(WifiDialog dialog);
    }
    
        public WifiDialog(Context context, WifiDialogListener listener, AccessPoint accessPoint,
            int mode, boolean hideSubmitButton) {
        this(context, listener, accessPoint, mode);
        mHideSubmitButton = hideSubmitButton;
    }
    
    
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null); // 对话框布局 wifi_dialog  输入密码等对话框 详见file文档
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint, mMode);
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) { // 是否隐藏提交按钮
            mController.hideSubmitButton();
        } else {
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }
    
    
    
    
        @Override    WifiDialog
    public void onClick(DialogInterface dialogInterface, int id) {
        if (mListener != null) {
            switch (id) {
                case BUTTON_SUBMIT:  // 点击对话框的 提交按钮
                    mListener.onSubmit(this);
                    break;
                case BUTTON_FORGET:  // 点击对话框的  取消 按钮
                    if (WifiSettings.isEditabilityLockedDown(
                            getContext(), mAccessPoint.getConfig())) {
                        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(),
                                RestrictedLockUtils.getDeviceOwner(getContext()));
                        return;
                    }
                    mListener.onForget(this);
                    break;
            }
        }
    }
    
    
############
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
   public void generateOpenNetworkConfig() {
        if (security != SECURITY_NONE) // 只有 security 为SECURITY_NONE 才能这样配置
            throw new IllegalStateException();
        if (mConfig != null)
            return;
        mConfig = new WifiConfiguration();
        mConfig.SSID = AccessPoint.convertToQuotedString(ssid);
        mConfig.allowedKeyManagement.set(KeyMgmt.NONE);
    }
    
```




###WIFI UA14  布局分析
<img src="//../zimage/wireless/wifi/01_wifisetting/25.png" width = "25%" height="25%"/> <img src="//../zimage/wireless/wifi/01_wifisetting/26.png" width = "25%" height="25%"/> 
[R.layout.wifi_dialog](file/wifi_dialog.xml)
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

 R.layout.wifi_dialog
 
 
             <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    style="@style/wifi_item" >
                <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_label"
                        android:text="@string/wifi_ssid"   //  热点的名字
                        android:textDirection="locale" />

                <EditText android:id="@+id/ssid"   // 输入热点密码的输入框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_edit_content"
                        android:hint="@string/wifi_ssid_hint"
                        android:maxLength="32"
                        android:singleLine="true"
                        android:inputType="textNoSuggestions" />
             </LinearLayout>
      

                <CheckBox android:id="@+id/show_password"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_content"
                        android:textSize="14sp"
                        android:text="@string/wifi_show_password" />  // 是否明文显示密码 选框
                        
                        
                     <Spinner android:id="@+id/ca_cert"   // 证书下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_ca_cert" />
                        
                    <TextView
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_label"
                            android:text="@string/wifi_eap_method" />

                    <Spinner android:id="@+id/method"  // EAP 方法下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_method"
                            android:entries="@array/wifi_eap_method" />
                      <Spinner android:id="@+id/phase2"  // eap 阶段2 身份验证下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/please_select_phase2"
                            android:entries="@array/wifi_phase2_entries" />


                            
                 <Spinner android:id="@+id/proxy_settings"   // 代理设置 下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/proxy_settings_title"
                        android:entries="@array/wifi_proxy_settings" />
                        
                        
                   <Spinner android:id="@+id/ip_settings"  // Ip代理设置下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/wifi_ip_settings"
                        android:entries="@array/wifi_ip_settings" />
```






## WIFI UA15分析
###WIFI UA15 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/7.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/10.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/>  
1. **UA编号**   UA15( 同UA13)
1. **UA说明**  **当前搜索到的网络Preference的点击事件**
1. **UA触发函数**   /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 这个Fragemnt的  WifiSettings的 onPreferenceTreeClick 为点击响应函数
  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick 
1. **UA触发函数所在类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java   onPreferenceTreeClick
---


###WIFI UA15 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);  // 如果 preference设置了 fragment 则 跳转到该 fragment
        }

        if (preference instanceof LongPressAccessPointPreference) {
            mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            if (mSelectedAccessPoint == null) {
                return false;
            }
            if (mSelectedAccessPoint.isActive()) {  // 如果当前 AccessPoint 是 Active 活动的 则让 父类处置
                return super.onPreferenceTreeClick(preference);
            }
            /**
             * Bypass dialog and connect to unsecured networks, or previously connected saved
             * networks, or Passpoint provided networks.
             */
            WifiConfiguration config = mSelectedAccessPoint.getConfig();
            if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { // 如果当前 AccessPoint 是不需要认证的 那么直接 连接它 通过 WifiConfiguration
                mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(mSelectedAccessPoint.getConfig(), mSelectedAccessPoint.isSaved());
            } else if (mSelectedAccessPoint.isSaved() && config != null
                    && config.getNetworkSelectionStatus() != null
                    && config.getNetworkSelectionStatus().getHasEverConnected()) {
                connect(config, true /* isSavedNetwork */);
            } else if (mSelectedAccessPoint.isPasspoint()) {  // 如果当前网络是 Passpoint类型的网络 那么 直接连接 
                // Access point provided by an installed Passpoint provider, connect using
                // the associated config.
                connect(config, true /* isSavedNetwork */);
            } else {
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);  // 否则就弹出对话框  请求用户进行认证输入 已达到连接的目的
            }
        } else if (preference == mAddPreference) {
            onAddNetworkPressed(); // 如果点击的是  mAddPreference  添加网络那项 Preference  那么执行   onAddNetworkPressed() 执行添加网络的操作
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }


```




## WIFI UA16分析
###WIFI UA16 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/7.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/10.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/>  
1. **UA编号**   UA16( 同UA14)
1. **UA说明**  **当前搜索到的网络Preference的长按事件**
1. **UA触发函数**     在 /packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java 函数onBindViewHolder 设置了 setOnCreateContextMenuListener(mFragment)
   /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java这个Fragemnt 以及s etLongClickable(true);   来接受长按事件 
  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    
1. **UA触发函数所在类**   
---



###WIFI UA16 代码分析
<img src="//../zimage/wireless/wifi/01_wifisetting/27.png" width = "25%" height="25%"/>
```

/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) { // 长按 AccessPoint时触发的函数
            Preference preference = (Preference) view.getTag();

            if (preference instanceof LongPressAccessPointPreference) {
            
            //  private AccessPoint mSelectedAccessPoint;  用户长按的Prference 对应的  AccessPoint 
                mSelectedAccessPoint =((LongPressAccessPointPreference) preference).getAccessPoint();
                menu.setHeaderTitle(mSelectedAccessPoint.getSsid()); //  长按 显示的第一个项  为 热点的名字
                if (mSelectedAccessPoint.isConnectable()) {  // 如果当前网络热点有信号 的话 isConnectable() = true
                //   <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>    显示  连接到网络 
                    menu.add(Menu.NONE, MENU_ID_CONNECT, 0, R.string.wifi_menu_connect);
                }

                WifiConfiguration config = mSelectedAccessPoint.getConfig();
                // Some configs are ineditable
                if (isEditabilityLockedDown(getActivity(), config)) {  // 判断该热点对应的 WifiConfiguration 是否可修改
                    return;
                }

                if (mSelectedAccessPoint.isSaved() || mSelectedAccessPoint.isEphemeral()) { // 如果当前preference 对应的AccessPoint 是已保存过 或是 临时的话  显示
                    // Allow forgetting a network if either the network is saved or ephemerally
                    // connected. (In the latter case, "forget" blacklists the network so it won't
                    // be used again, ephemerally).
                    // <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
                    menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
                }
                if (mSelectedAccessPoint.isSaved()) {
                //  <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
                    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
                    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                    if (nfcAdapter != null && nfcAdapter.isEnabled() && mSelectedAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                        // Only allow writing of NFC tags for password-protected networks.
                        // <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>  只在 NFC有效的条件下
                        menu.add(Menu.NONE, MENU_ID_WRITE_NFC, 0, R.string.wifi_menu_write_to_nfc);
                    }
                }
            }
    }



```



## WIFI UA17分析
###WIFI UA17 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/8.png" width = "25%" height="25%"/>
1. **UA编号**   UA17
1. **UA说明**  **添加网络的Preference点击事件**
1. **UA触发函数**   packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java   onPreferenceTreeClick  
   /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  private Preference mAddPreference; mAddPreference对应 添加网络Preference
  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    
1. **UA触发函数所在类**   
---



###WIFI UA17 代码分析


```


/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 

private Preference mAddPreference;



    @Override
    public void onCreate(Bundle icicle) {
        Context prefContext = getPrefContext();
        mAddPreference = new Preference(prefContext);
        mAddPreference.setIcon(R.drawable.ic_menu_add_inset);
        mAddPreference.setTitle(R.string.wifi_add_network);   // <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>
        mAccessPointsPreferenceCategory.addPreference(mAddPreference);
       }




    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);  // 如果 preference设置了 fragment 则 跳转到该 fragment
        }

        if (preference instanceof LongPressAccessPointPreference) {
            mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            if (mSelectedAccessPoint == null) {
                return false;
            }
            if (mSelectedAccessPoint.isActive()) {  // 如果当前 AccessPoint 是 Active 活动的 则让 父类处置
                return super.onPreferenceTreeClick(preference);
            }
            /**
             * Bypass dialog and connect to unsecured networks, or previously connected saved
             * networks, or Passpoint provided networks.
             */
            WifiConfiguration config = mSelectedAccessPoint.getConfig();
            if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { // 如果当前 AccessPoint 是不需要认证的 那么直接 连接它 通过 WifiConfiguration
                mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(mSelectedAccessPoint.getConfig(), mSelectedAccessPoint.isSaved());
            } else if (mSelectedAccessPoint.isSaved() && config != null
                    && config.getNetworkSelectionStatus() != null
                    && config.getNetworkSelectionStatus().getHasEverConnected()) {
                connect(config, true /* isSavedNetwork */);
            } else if (mSelectedAccessPoint.isPasspoint()) {  // 如果当前网络是 Passpoint类型的网络 那么 直接连接 
                // Access point provided by an installed Passpoint provider, connect using
                // the associated config.
                connect(config, true /* isSavedNetwork */);
            } else {
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);  // 否则就弹出对话框  请求用户进行认证输入 已达到连接的目的
            }
        } else if (preference == mAddPreference) {
            onAddNetworkPressed(); // 如果点击的是  mAddPreference  添加网络那项 Preference  那么执行   onAddNetworkPressed() 执行添加网络的操作
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }




/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 


void onAddNetworkPressed() {
        mMetricsFeatureProvider.action(getActivity(), MetricsEvent.ACTION_WIFI_ADD_NETWORK);
        // No exact access point is selected.
        mSelectedAccessPoint = null;
        showDialog(null, WifiConfigUiBase.MODE_CONNECT); // 显示 添加网络的Dialog
    }
  
  
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    private void showDialog(AccessPoint accessPoint, int dialogMode) {
        if (accessPoint != null) {
            WifiConfiguration config = accessPoint.getConfig();
            if (isEditabilityLockedDown(getActivity(), config) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(),
                        RestrictedLockUtils.getDeviceOwner(getActivity()));
                return;
            }
        }
        if (mDialog != null) {
            removeDialog(WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint;  // 为空  null
        mDialogMode = dialogMode;  

        showDialog(WIFI_DIALOG_ID); // showDialog
    }



/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WIFI_DIALOG_ID:
                AccessPoint ap = mDlgAccessPoint; // For manual launch
                if (ap == null) { // For re-launch from saved state
                    if (mAccessPointSavedState != null) {
                        ap = new AccessPoint(getActivity(), mAccessPointSavedState);
                        // For repeated orientation changes
                        mDlgAccessPoint = ap;
                        // Reset the saved access point data
                        mAccessPointSavedState = null;
                    }
                }
                // If it's null, fine, it's for Add Network
                mSelectedAccessPoint = ap;
                mDialog = new WifiDialog(getActivity(), this, ap, mDialogMode【WifiConfigUiBase.MODE_CONNECT】,/* no hide submit/connect */ false);  // 
                return mDialog;
                ......
             }
             
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

private WifiConfigController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null);
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint 【NULL】, mMode 【WifiConfigUiBase.MODE_CONNECT】); // 依据 新建了 WifiConfigController  业务交由WifiConfigController实现
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) {
            mController.hideSubmitButton();
        } else {
            /* During creation, the submit button can be unavailable to determine
             * visibility. Right after creation, update button visibility */
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }



/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java

          public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint【null】,int mode 【WifiConfigUiBase.MODE_CONNECT】) {
        mConfigUi = parent;

        mView = view;
        mAccessPoint = accessPoint;
        mAccessPointSecurity = (accessPoint == null) ? AccessPoint.SECURITY_NONE : accessPoint.getSecurity();  // mAccessPointSecurity在此等于  AccessPoint.SECURITY_NONE
        mMode = mode;

        mTextViewChangedHandler = new Handler();
        mContext = mConfigUi.getContext();
        final Resources res = mContext.getResources();

//    <string-array name="wifi_signal">
//       <item>Poor</item>
//        <item>Poor</item>
//        <item>Fair</item>
//        <item>Good</item>
//       <item>Excellent</item>
//    </string-array>
    
        mLevels = res.getStringArray(R.array.wifi_signal);
        // <bool name="config_eap_sim_based_auth_supported">true</bool>
        if (Utils.isWifiOnly(mContext) || !mContext.getResources().getBoolean(com.android.internal.R.bool.config_eap_sim_based_auth_supported)) {
        
//  <string-array name="wifi_peap_phase2_entries">
//    <item msgid="2577747762745812488">"无"</item>
//    <item msgid="937786527870979616">"MSCHAPV2"</item>
//    <item msgid="5302613883318643629">"GTC"</item>
//  </string-array>
  
            mPhase2PeapAdapter = new ArrayAdapter<String>( mContext, android.R.layout.simple_spinner_item,res.getStringArray(R.array.wifi_peap_phase2_entries));
        } else {
        
        
//<string-array name="wifi_peap_phase2_entries_with_sim_auth">
//    <item msgid="5760470455461128892">"无"</item>
 //   <item msgid="7480272092408291086">"MSCHAPV2"</item>
//    <item msgid="5881794903338319324">"GTC"</item>
 //   <item msgid="5610607665198791980">"SIM"</item>
 //   <item msgid="2860798636241124128">"AKA"</item>
 //   <item msgid="8926455723452645935">"AKA\'"</item>
 // </string-array>
  
            mPhase2PeapAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,res.getStringArray(R.array.wifi_peap_phase2_entries_with_sim_auth));
        }
        mPhase2PeapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// <string-array name="wifi_phase2_entries">
//    <item msgid="1818786254010764570">"无"</item>
//    <item msgid="6189918678874123056">"PAP"</item>
//    <item msgid="1524112260493662517">"MSCHAP"</item>
//    <item msgid="5923246669412752932">"MSCHAPV2"</item>
//    <item msgid="8651992560135239389">"GTC"</item>
//  </string-array>
  
  
        mPhase2FullAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,res.getStringArray(R.array.wifi_phase2_entries));
        
        
        mPhase2FullAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
// <string name="wifi_unspecified" msgid="4917316464723064807">"请选择"</string>
        mUnspecifiedCertString = mContext.getString(R.string.wifi_unspecified);
        
        // <string name="wifi_multiple_cert_added" msgid="3240743501460165224">"（已添加多份证书）"</string>
        mMultipleCertSetString = mContext.getString(R.string.wifi_multiple_cert_added);
        
//  <string name="wifi_use_system_certs" msgid="5270879895056893783">"使用系统证书"</string>
        mUseSystemCertsString = mContext.getString(R.string.wifi_use_system_certs);
        
        
// <string name="wifi_do_not_provide_eap_user_cert" msgid="5160499244977160665">"不提供"</string>
        mDoNotProvideEapUserCertString = mContext.getString(R.string.wifi_do_not_provide_eap_user_cert);
        
       // <string name="wifi_do_not_validate_eap_server" msgid="4266754430576348471">"不验证"</string>
        mDoNotValidateEapServerString = mContext.getString(R.string.wifi_do_not_validate_eap_server);

        mIpSettingsSpinner = (Spinner) mView.findViewById(R.id.ip_settings);
        mIpSettingsSpinner.setOnItemSelectedListener(this);
        mProxySettingsSpinner = (Spinner) mView.findViewById(R.id.proxy_settings);
        mProxySettingsSpinner.setOnItemSelectedListener(this);
        mSharedCheckBox = (CheckBox) mView.findViewById(R.id.shared);

        if (mAccessPoint == null) { // new network  // 当前的 AccessPoint为空 说明是 需要 新增 网络
            mConfigUi.setTitle(R.string.wifi_add_network);  // <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>

            mSsidView = (TextView) mView.findViewById(R.id.ssid);
            mSsidView.addTextChangedListener(this);
            mSecuritySpinner = ((Spinner) mView.findViewById(R.id.security));
            mSecuritySpinner.setOnItemSelectedListener(this);
            mView.findViewById(R.id.type).setVisibility(View.VISIBLE);

            showIpConfigFields();
            showProxyFields();
            mView.findViewById(R.id.wifi_advanced_toggle).setVisibility(View.VISIBLE);
            ((CheckBox) mView.findViewById(R.id.wifi_advanced_togglebox)).setOnCheckedChangeListener(this);
            mConfigUi.setSubmitButton(res.getString(R.string.wifi_save));  // <string name="wifi_save" msgid="3331121567988522826">"保存"</string>
        } else {
            if (!mAccessPoint.isPasspointConfig()) {
                mConfigUi.setTitle(mAccessPoint.getSsid());
            } else {
                mConfigUi.setTitle(mAccessPoint.getConfigName());
            }

            ViewGroup group = (ViewGroup) mView.findViewById(R.id.info);

            boolean showAdvancedFields = false;
            if (mAccessPoint.isSaved()) {
                WifiConfiguration config = mAccessPoint.getConfig();
                if (config.getIpAssignment() == IpAssignment.STATIC) {
                    mIpSettingsSpinner.setSelection(STATIC_IP);
                    showAdvancedFields = true;
                    // Display IP address.
                    StaticIpConfiguration staticConfig = config.getStaticIpConfiguration();
                    if (staticConfig != null && staticConfig.ipAddress != null) {
                   // <string name="wifi_ip_address" msgid="1440054061044402918">"IP 地址"</string>
                    addRow(group, R.string.wifi_ip_address,staticConfig.ipAddress.getAddress().getHostAddress());
                    }
                } else {
                    mIpSettingsSpinner.setSelection(DHCP);
                }

                mSharedCheckBox.setEnabled(config.shared);
                if (!config.shared) {
                    showAdvancedFields = true;
                }

                if (config.getProxySettings() == ProxySettings.STATIC) {
                    mProxySettingsSpinner.setSelection(PROXY_STATIC);
                    showAdvancedFields = true;
                } else if (config.getProxySettings() == ProxySettings.PAC) {
                    mProxySettingsSpinner.setSelection(PROXY_PAC);
                    showAdvancedFields = true;
                } else {
                    mProxySettingsSpinner.setSelection(PROXY_NONE);
                }
                if (config != null && config.isPasspoint()) {
                // <string name="passpoint_label" msgid="6381371313076009926">"保存方式："</string>  
                // <string name="passpoint_content" msgid="8447207162397870483">"<xliff:g id="NAME">%1$s</xliff:g>凭据"</string>
                    addRow(group, R.string.passpoint_label,String.format(mContext.getString(R.string.passpoint_content),config.providerFriendlyName));
                }
            }

            if ((!mAccessPoint.isSaved() && !mAccessPoint.isActive() && !mAccessPoint.isPasspointConfig()) || mMode != WifiConfigUiBase.MODE_VIEW) {
                showSecurityFields();
                showIpConfigFields();
                showProxyFields();
                final CheckBox advancedTogglebox = (CheckBox) mView.findViewById(R.id.wifi_advanced_togglebox); //高级设置选项
                mView.findViewById(R.id.wifi_advanced_toggle).setVisibility(View.VISIBLE);
                advancedTogglebox.setOnCheckedChangeListener(this);
                advancedTogglebox.setChecked(showAdvancedFields);
                mView.findViewById(R.id.wifi_advanced_fields).setVisibility(showAdvancedFields ? View.VISIBLE : View.GONE);
            }

            if (mMode == WifiConfigUiBase.MODE_MODIFY) { // 如果是 修改模式的话
                mConfigUi.setSubmitButton(res.getString(R.string.wifi_save));  //   <string name="wifi_save" msgid="3331121567988522826">"保存"</string>
            } else if (mMode == WifiConfigUiBase.MODE_CONNECT) {
                mConfigUi.setSubmitButton(res.getString(R.string.wifi_connect));
            } else {
                final DetailedState state = mAccessPoint.getDetailedState();
                final String signalLevel = getSignalString();

                if ((state == null || state == DetailedState.DISCONNECTED) && signalLevel != null) {
                    mConfigUi.setSubmitButton(res.getString(R.string.wifi_connect));  // <string name="wifi_connect" msgid="1076622875777072845">"连接"</string> 
                } else {
                    if (state != null) {
                        boolean isEphemeral = mAccessPoint.isEphemeral();
                        WifiConfiguration config = mAccessPoint.getConfig();
                        String providerFriendlyName = null;
                        if (config != null && config.isPasspoint()) {
                            providerFriendlyName = config.providerFriendlyName;
                        }
                        String summary = AccessPoint.getSummary(mConfigUi.getContext(), state, isEphemeral, providerFriendlyName);
                        //  <string name="wifi_status" msgid="4824568012414605414">"状态信息"</string>
                        addRow(group, R.string.wifi_status, summary);
                    }

                    if (signalLevel != null) {
                        addRow(group, R.string.wifi_signal, signalLevel);  //  <string name="wifi_signal" msgid="5514120261628065287">"信号强度"</string>
                    }

                    WifiInfo info = mAccessPoint.getInfo();
                    if (info != null && info.getLinkSpeed() != -1) {
                    //  <string name="wifi_speed" msgid="3526198708812322037">"连接速度"</string>  
                    //  <string name="link_speed" msgid="8896664974117585555">"%1$d Mbps"</string>
                        addRow(group, R.string.wifi_speed, String.format(res.getString(R.string.link_speed), info.getLinkSpeed()));
                    }

                    if (info != null && info.getFrequency() != -1) {
                        final int frequency = info.getFrequency();
                        String band = null;

                        if (frequency >= AccessPoint.LOWER_FREQ_24GHZ  && frequency < AccessPoint.HIGHER_FREQ_24GHZ) {
                            band = res.getString(R.string.wifi_band_24ghz);  // <string name="wifi_band_24ghz" msgid="852929254171856911">"2.4 GHz"</string>
                        } else if (frequency >= AccessPoint.LOWER_FREQ_5GHZ && frequency < AccessPoint.HIGHER_FREQ_5GHZ) {
                            band = res.getString(R.string.wifi_band_5ghz);  // <string name="wifi_band_5ghz" msgid="6433822023268515117">"5 GHz"</string>
                        } else {
                            Log.e(TAG, "Unexpected frequency " + frequency);
                        }
                        if (band != null) {
                            addRow(group, R.string.wifi_frequency, band);
                        }
                    }
//   <string name="wifi_security" msgid="6603611185592956936">"安全性"</string>
                    addRow(group, R.string.wifi_security, mAccessPoint.getSecurityString(false));
                    mView.findViewById(R.id.ip_fields).setVisibility(View.GONE);
                }
                if (mAccessPoint.isSaved() || mAccessPoint.isActive() || mAccessPoint.isPasspointConfig()) {
                    mConfigUi.setForgetButton(res.getString(R.string.wifi_forget));    //  <string name="wifi_forget" msgid="8168174695608386644">"取消保存"</string>
                }
            }
        }

        if (!isSplitSystemUser()) {
            mSharedCheckBox.setVisibility(View.GONE);
        }

        mConfigUi.setCancelButton(res.getString(R.string.wifi_cancel));  // <string name="wifi_cancel" msgid="6763568902542968964">"取消"</string>
        if (mConfigUi.getSubmitButton() != null) {
            enableSubmitIfAppropriate();
        }
    }
```


### UA17  布局分析
[R.layout.wifi_dialog](file/wifi_dialog.xml)
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

 R.layout.wifi_dialog
 
 
             <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    style="@style/wifi_item" >
                <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_label"
                        android:text="@string/wifi_ssid"   //  热点的名字
                        android:textDirection="locale" />

                <EditText android:id="@+id/ssid"   // 输入热点密码的输入框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_edit_content"
                        android:hint="@string/wifi_ssid_hint"
                        android:maxLength="32"
                        android:singleLine="true"
                        android:inputType="textNoSuggestions" />
             </LinearLayout>
      

                <CheckBox android:id="@+id/show_password"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_content"
                        android:textSize="14sp"
                        android:text="@string/wifi_show_password" />  // 是否明文显示密码 选框
                        
                        
                     <Spinner android:id="@+id/ca_cert"   // 证书下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_ca_cert" />
                        
                    <TextView
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_label"
                            android:text="@string/wifi_eap_method" />

                    <Spinner android:id="@+id/method"  // EAP 方法下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_method"
                            android:entries="@array/wifi_eap_method" />
                      <Spinner android:id="@+id/phase2"  // eap 阶段2 身份验证下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/please_select_phase2"
                            android:entries="@array/wifi_phase2_entries" />


                            
                 <Spinner android:id="@+id/proxy_settings"   // 代理设置 下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/proxy_settings_title"
                        android:entries="@array/wifi_proxy_settings" />
                        
                        
                   <Spinner android:id="@+id/ip_settings"  // Ip代理设置下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/wifi_ip_settings"
                        android:entries="@array/wifi_ip_settings" />
```


## WIFI UA18分析  (同UA5)
###WIFI UA18 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/8.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/4.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/5.png" width = "25%" height="25%"/>
1. **UA编号**   UA18
1. **UA说明**  **Wifi Preference 偏好设置的 Preference点击事件** android:key="configure_settings"
1. **UA触发函数**   packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 的布局文件 wifi_settings.xml 中 指定了 点击打开的Fragment ConfigureWifiSettings 具体的逻辑事件同 UA13
  WifiSettings.java onPreferenceTreeClick 是它的触发函数   该Preference无长按事件
          <Preference
                android:key="configure_settings"  // <string name="wifi_configure_settings_preference_title" >"WLAN 偏好设置"</string>   WLAN偏好设置选项
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" /> 
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    
1. **UA触发函数所在类**   
---



###WIFI UA18 代码分析
```
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
  @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) { 
//  如果当前 从 preference获取的 Fragment 不为 空  那么  调用   super.onPreferenceTreeClick(preference)  
// xml   文件 定义了 该 Fragment   SavedAccessPointsWifiSettings
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        }



/packages/apps/Settings/src/com/android/settings/wifi/ConfigureWifiSettings.java

public class ConfigureWifiSettings extends DashboardFragment {


    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_configure_settings;  //  wifi偏好设置的 xml布局文件
    }
    
    

    @Override
    protected List<PreferenceController> getPreferenceControllers(Context context) {  //  添加各个 List<PreferenceController> controllers 到界面
        final NetworkScoreManagerWrapper networkScoreManagerWrapper = new NetworkScoreManagerWrapper(context.getSystemService(NetworkScoreManager.class));
        mUseOpenWifiPreferenceController = new UseOpenWifiPreferenceController(context, this, networkScoreManagerWrapper, getLifecycle());
        final WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        final List<PreferenceController> controllers = new ArrayList<>();
        controllers.add(new WifiWakeupPreferenceController(context, getLifecycle()));
        controllers.add(new NetworkScorerPickerPreferenceController(context,  networkScoreManagerWrapper));
        controllers.add(new NotifyOpenNetworksPreferenceController(context, getLifecycle()));
        controllers.add(mUseOpenWifiPreferenceController);
        controllers.add(new WifiSleepPolicyPreferenceController(context));
        controllers.add(new WifiInfoPreferenceController(context, getLifecycle(), wifiManager));
        controllers.add(new CellularFallbackPreferenceController(context));
        controllers.add(new WifiP2pPreferenceController(context, getLifecycle(), wifiManager));
        controllers.add(new WifiCallingPreferenceController(context));
        controllers.add(new WpsPreferenceController( context, getLifecycle(), wifiManager, getFragmentManager()));
        return controllers;
    }




############
/frameworks/base/core/java/android/preference/PreferenceFragment.java    WifiSettings.java  的 爷爷类
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference.getFragment() != null && getActivity() instanceof OnPreferenceStartFragmentCallback) {
            return ((OnPreferenceStartFragmentCallback)getActivity()【SettingsActivity】).onPreferenceStartFragment(this, preference); // 调用 onPreferenceStartFragment
        }
        return false;
    }
    
    
/packages/apps/Settings/src/com/android/settings/SettingsActivity.java
public class SettingsActivity extends SettingsDrawerActivity
        implements PreferenceManager.OnPreferenceTreeClickListener,
        PreferenceFragment.OnPreferenceStartFragmentCallback【由于当前的Activity SettingsActivity 实现了  PreferenceFragment.OnPreferenceStartFragmentCallback 接口】,
        // 所以 调用到了 SettingsActivity 方法实现的接口方法 onPreferenceStartFragment
        ButtonBarHandler, FragmentManager.OnBackStackChangedListener {


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        startPreferencePanel(caller, pref.getFragment(), pref.getExtras(), -1, pref.getTitle(), null, 0);  //
        return true;
    }
    
    
    public void startPreferencePanel(Fragment caller, String fragmentClass, Bundle args,int titleRes, CharSequence titleText, Fragment resultTo, int resultRequestCode) {
        String title = null;
        if (titleRes < 0) {
            if (titleText != null) {
                title = titleText.toString();
            } else {
                title = "";
            }
        }
        // 完成 跳转
        Utils.startWithFragment(this, fragmentClass, args, resultTo, resultRequestCode,titleRes, title, mIsShortcut, mMetricsFeatureProvider.getMetricsCategory(caller));
    }
    }
    
    
    /packages/apps/Settings/src/com/android/settings/Utils.java
    
public static void startWithFragment(Context context, String fragmentName, Bundle args,Fragment resultTo, int resultRequestCode, int titleResId,CharSequence title, int metricsCategory) {
        startWithFragment(context, fragmentName, args, resultTo, resultRequestCode,null /* titleResPackageName */, titleResId, title, false /* not a shortcut */, metricsCategory);
    }
    


```


###WIFI UA18 布局分析
R.xml.wifi_settings.xml 布局文件
```
R.xml.wifi_settings.xml    android:key="configure_settings"    WLAN偏好设置选项
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_settings"  // values-zh-rCN/  <string name="wifi_settings" msgid="29722149822540994">"WLAN"</string>
        settings:keywords="@string/keywords_wifi">

    <PreferenceCategory android:key="connected_access_point" />  // 当前已连接的热点显示的Preference 

    <PreferenceCategory android:key="access_points"/>  // 当前手机扫描到的热点的列表

    <PreferenceCategory android:key="additional_settings"> // 添加网络 Preference Item
        <Preference
                android:key="configure_settings"  // <string name="wifi_configure_settings_preference_title" >"WLAN 偏好设置"</string>   WLAN偏好设置选项
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" />  // 偏好设置选项显示的Fragment   ConfigureWifiSettings

        <Preference
                android:key="saved_networks"      // <string name="wifi_saved_access_points_label">"已保存的网络"</string> 
                android:title="@string/wifi_saved_access_points_label"
                android:fragment="com.android.settings.wifi.SavedAccessPointsWifiSettings" />  // 已保存的网络显示的Fragment   SavedAccessPointsWifiSettings
    </PreferenceCategory> 
</PreferenceScreen>



R.xml.wifi_display_saved_access_points  // 已保存热点的布局文件

<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    android:title="@string/wifi_display_settings_title">  // <string name="wifi_display_settings_title" msgid="8740852850033480136">"投射"</string>
</PreferenceScreen>




R.xml.wifi_configure_settings
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_configure_titlebar">

    <SwitchPreference
            android:key="enable_wifi_wakeup" 
            android:title="@string/wifi_wakeup" // <string name="wifi_wakeup" >"自动开启 WLAN"</string>
            android:icon="@drawable/ic_auto_wifi"
            android:summary="@string/wifi_wakeup_summary" />

    <SwitchPreference
        android:key="use_open_wifi_automatically"
        android:icon="@drawable/ic_open_wifi_autoconnect" 
        android:title="@string/use_open_wifi_automatically_title" // <string name="use_open_wifi_automatically_title" >"连接到开放网络"</string>
        android:summary="@string/use_open_wifi_automatically_summary" /> // <string name="use_open_wifi_automatically_summary">"自动连接到高品质的公共网络"</string>

    <SwitchPreference
            android:key="notify_open_networks"
            android:title="@string/wifi_notify_open_networks"  // <string name="wifi_notify_open_networks" msgid="76298880708051981">"打开网络通知"</string>
            android:icon="@drawable/ic_open_wifi_notifications"
            android:summary="@string/wifi_notify_open_networks_summary" /> //<string name="wifi_notify_open_networks_summary" >"有可用的高品质公共网络时通知我"</string>

    <SwitchPreference
        android:key="wifi_cellular_data_fallback"
        android:title="@string/wifi_cellular_data_fallback_title"
        android:summary="@string/wifi_cellular_data_fallback_summary"/>

    <ListPreference
            android:key="sleep_policy"   // 在安卓8.1中 WIFI的休眠策略被去除  new WifiSleepPolicyPreferenceController(context));类进行处理
            android:title="@string/wifi_setting_sleep_policy_title"// <string name="wifi_setting_sleep_policy_title">"在休眠状态下保持WLAN网络连接"</string>
            android:entries="@array/wifi_sleep_policy_entries"
            android:entryValues="@array/wifi_sleep_policy_values" />

    <Preference
            android:key="install_credentials"
            android:title="@string/wifi_install_credentials"> // <string name="wifi_install_credentials" >"安装证书"</string>
        <intent android:action="android.credentials.INSTALL_AS_USER"
                android:targetPackage="com.android.certinstaller"
                android:targetClass="com.android.certinstaller.CertInstallerMain">
            <extra android:name="install_as_uid" android:value="1010"/>
        </intent>
    </Preference>

    <Preference
            android:key="wifi_calling_settings"
            android:title="@string/wifi_calling_settings_title"
            android:fragment="com.android.settings.WifiCallingSettings"
            settings:keywords="@string/keywords_wifi_calling"/>

    <Preference
            android:key="network_scorer_picker"
            android:title="@string/network_scorer_picker_title" // <string name="network_scorer_picker_title">"网络评分服务提供方"</string>
            android:fragment="com.android.settings.network.NetworkScorerPicker"/>

    <Preference
            android:key="wifi_direct"    //  new WifiP2pPreferenceController(context, getLifecycle(), wifiManager)) 类进行处理
            android:title="@string/wifi_menu_p2p"> // <string name="wifi_menu_p2p">"WLAN直连"</string>  WLAN直连指定了目标Activity类
        <intent android:targetPackage="com.android.settings"
                android:targetClass="com.android.settings.Settings$WifiP2pSettingsActivity"/>
    </Preference>

    <Preference
            android:key="wps_push_button"    // new WpsPreferenceController( context, getLifecycle(), wifiManager, getFragmentManager()) 类进行处理
            android:title="@string/wifi_menu_wps_pbc" />  // <string name="wifi_menu_wps_pbc">"WPS按钮"</string>

    <Preference
            android:key="wps_pin_entry"   // new WpsPreferenceController( context, getLifecycle(), wifiManager, getFragmentManager()) 类进行处理
            android:title="@string/wifi_menu_wps_pin" />  // <string name="wifi_menu_wps_pin">"WPS PIN码输入"</string>

    <Preference
            android:key="mac_address"    // new WifiInfoPreferenceController(context, getLifecycle(), wifiManager)); 类进行处理
            android:title="@string/wifi_advanced_mac_address_title"/>  // <string name="wifi_advanced_mac_address_title" >"MAC地址"</string>

    <Preference
            android:key="current_ip_address"  // new WifiInfoPreferenceController(context, getLifecycle(), wifiManager)); 类进行处理
            android:title="@string/wifi_advanced_ip_address_title"/> //<string name="wifi_advanced_ip_address_title">"IP 地址"</string>

</PreferenceScreen>


```







## WIFI UA19分析
###WIFI UA19 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/8.png" width = "25%" height="25%"/>
1. **UA编号**   UA19
1. **UA说明**  **Wifi Preference 设置的 Preference点击事件**
1. **UA触发函数**   packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 的布局文件 wifi_settings.xml 中 指定了 点击打开的Fragment SavedAccessPointsWifiSettings 具体的逻辑事件同 UA13
  WifiSettings.java onPreferenceTreeClick 是它的触发函数   该Preference无长按事件
          <Preference
                android:key="saved_networks"      // <string name="wifi_saved_access_points_label">"已保存的网络"</string> 
                android:title="@string/wifi_saved_access_points_label"
                android:fragment="com.android.settings.wifi.SavedAccessPointsWifiSettings" />  // 已保存的网络显示的Fragment   SavedAccessPointsWifiSettings
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    
1. **UA触发函数所在类**   
---



###WIFI UA19 代码分析
```
 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
  @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) { 
//  如果当前 从 preference获取的 Fragment 不为 空  那么  调用   super.onPreferenceTreeClick(preference)  
// xml   文件 定义了 该 Fragment   SavedAccessPointsWifiSettings
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        }



/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java


public class SavedAccessPointsWifiSettings extends SettingsPreferenceFragment implements Indexable, WifiDialog.WifiDialogListener {
        
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.wifi_display_saved_access_points);  // 已保存热点的布局文件
        mUserBadgeCache = new AccessPointPreference.UserBadgeCache(getPackageManager());
         initPreferences();
    }



/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    private void initPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        final Context context = getPrefContext();

        final List<AccessPoint> accessPoints = WifiSavedConfigUtils.getAllConfigs(context, mWifiManager); // 获得所有保存的热点的 AccessPoint
        Collections.sort(accessPoints, SAVED_NETWORK_COMPARATOR); // 热点集合进行排序
        preferenceScreen.removeAll();

        final int accessPointsSize = accessPoints.size();
        for (int i = 0; i < accessPointsSize; ++i){
        // 新建LongPressAccessPointPreference  项Item  添加到  PreferenceScreen
            LongPressAccessPointPreference preference =new LongPressAccessPointPreference(accessPoints.get(i), context, mUserBadgeCache, true, this);
            preference.setIcon(null);  // 不设置图标
            preferenceScreen.addPreference(preference);  // 添加到  preferenceScreen
        }

        if(getPreferenceScreen().getPreferenceCount() < 1) {
            Log.w(TAG, "Saved networks activity loaded, but there are no saved networks!");
        }
    }
}

  
/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {  // 已保存网络里的Preference 的点击事件
        if (preference instanceof LongPressAccessPointPreference) {
            showDialog((LongPressAccessPointPreference) preference, false);
            return true;
        } else{
            return super.onPreferenceTreeClick(preference);
        }
    }
     


/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    private void showDialog(LongPressAccessPointPreference accessPoint, boolean edit) {
        if (mDialog != null) {
            removeDialog(WifiSettings.WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint.getAccessPoint();

        showDialog(WifiSettings.WIFI_DIALOG_ID);  // 显示提示框
    }
    
    
packages/apps/Settings/src/com/android/settings/SettingsPreferenceFragment.java    SavedAccessPointsWifiSettings.java的父父类
    protected void showDialog(int dialogId) {
        if (mDialogFragment != null) {
            Log.e(TAG, "Old dialog fragment not null!");
        }
        mDialogFragment = new SettingsDialogFragment(this, dialogId);  // 创建 SettingsDialogFragment   并调用 show 方法 显示 Dialog对话框
        mDialogFragment.show(getChildFragmentManager(), Integer.toString(dialogId));
    }
    

        @Override  SettingsDialogFragment.java
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                mDialogId = savedInstanceState.getInt(KEY_DIALOG_ID, 0);
                mParentFragment = getParentFragment();
                int mParentFragmentId = savedInstanceState.getInt(KEY_PARENT_FRAGMENT_ID, -1);
                if (mParentFragment == null) {
                    mParentFragment = getFragmentManager().findFragmentById(mParentFragmentId);
                }
                if (!(mParentFragment instanceof DialogCreatable)) {
                    throw new IllegalArgumentException();
                }
                // This dialog fragment could be created from non-SettingsPreferenceFragment
                if (mParentFragment instanceof SettingsPreferenceFragment) {
                    // restore mDialogFragment in mParentFragment
                    ((SettingsPreferenceFragment) mParentFragment).mDialogFragment = this;
                }
            }
            return ((DialogCreatable) mParentFragment).onCreateDialog(mDialogId); // 调用子类的 onCreateDialog 方法
        }
        }
        
    
     
 /packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
     @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WifiSettings.WIFI_DIALOG_ID:
                if (mDlgAccessPoint == null) { // For re-launch from saved state
                    mDlgAccessPoint = new AccessPoint(getActivity(), mAccessPointSavedState);
                    // Reset the saved access point data
                    mAccessPointSavedState = null;
                }
                mSelectedAccessPoint = mDlgAccessPoint;
                // 创建 WifiDialog  并继续创建  WifiConfigController  来显示对应的 弹框内容
                mDialog = new WifiDialog(getActivity(), this, mDlgAccessPoint,WifiConfigUiBase.MODE_VIEW, true /* hide the submit button */);
                return mDialog;

        }
        return super.onCreateDialog(dialogId);
    }
```

###WIFI UA19 布局分析
R.xml.wifi_settings.xml 布局文件
```
R.xml.wifi_settings.xml
<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings"
        android:title="@string/wifi_settings"  // values-zh-rCN/  <string name="wifi_settings" msgid="29722149822540994">"WLAN"</string>
        settings:keywords="@string/keywords_wifi">

    <PreferenceCategory android:key="connected_access_point" />  // 当前已连接的热点显示的Preference 

    <PreferenceCategory android:key="access_points"/>  // 当前手机扫描到的热点的列表

    <PreferenceCategory android:key="additional_settings"> // 添加网络 Preference Item
        <Preference
                android:key="configure_settings"  // <string name="wifi_configure_settings_preference_title" >"WLAN 偏好设置"</string>   WLAN偏好设置选项
                android:title="@string/wifi_configure_settings_preference_title"
                android:fragment="com.android.settings.wifi.ConfigureWifiSettings" />  // 偏好设置选项显示的Fragment   ConfigureWifiSettings

        <Preference
                android:key="saved_networks"      // <string name="wifi_saved_access_points_label">"已保存的网络"</string> 
                android:title="@string/wifi_saved_access_points_label"
                android:fragment="com.android.settings.wifi.SavedAccessPointsWifiSettings" />  // 已保存的网络显示的Fragment   SavedAccessPointsWifiSettings
    </PreferenceCategory> 
</PreferenceScreen>



R.xml.wifi_display_saved_access_points  // 已保存热点的布局文件

<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    android:title="@string/wifi_display_settings_title">  // <string name="wifi_display_settings_title" msgid="8740852850033480136">"投射"</string>
</PreferenceScreen>



```









## WIFI UA20分析 (同UA15)
###WIFI UA20 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/9.png" width = "25%" height="25%"/>
1. **UA编号**   UA20
1. **UA说明**  **Wifi搜素到的热点 LongPressPreference 的点击事件弹出的对话框 WifiDialog**
1. **UA触发函数**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick 点击事件执行了函数 showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);
  弹出的对话框

1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick   showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);
1. **UA触发函数所在类**   
---



###WIFI UA20 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);  // 如果 preference设置了 fragment 则 跳转到该 fragment
        }

        if (preference instanceof LongPressAccessPointPreference) {
            mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            if (mSelectedAccessPoint == null) {
                return false;
            }
            if (mSelectedAccessPoint.isActive()) {  // 如果当前 AccessPoint 是 Active 活动的 则让 父类处置
                return super.onPreferenceTreeClick(preference);
            }
            /**
             * Bypass dialog and connect to unsecured networks, or previously connected saved
             * networks, or Passpoint provided networks.
             */
            WifiConfiguration config = mSelectedAccessPoint.getConfig();
            if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { // 如果当前 AccessPoint 是不需要认证的 那么直接 连接它 通过 WifiConfiguration
                mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(mSelectedAccessPoint.getConfig(), mSelectedAccessPoint.isSaved());
            } else if (mSelectedAccessPoint.isSaved() && config != null
                    && config.getNetworkSelectionStatus() != null
                    && config.getNetworkSelectionStatus().getHasEverConnected()) {
                connect(config, true /* isSavedNetwork */);
            } else if (mSelectedAccessPoint.isPasspoint()) {  // 如果当前网络是 Passpoint类型的网络 那么 直接连接 
                // Access point provided by an installed Passpoint provider, connect using
                // the associated config.
                connect(config, true /* isSavedNetwork */);
            } else {
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);  // 否则就弹出对话框  请求用户进行认证输入 已达到连接的目的 UA20执行的函数
            }
        } else if (preference == mAddPreference) {
            onAddNetworkPressed(); // 如果点击的是  mAddPreference  添加网络那项 Preference  那么执行   onAddNetworkPressed() 执行添加网络的操作
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }




/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
      private void showDialog(AccessPoint accessPoint, int dialogMode) {
        if (accessPoint != null) {
            WifiConfiguration config = accessPoint.getConfig();
            if (isEditabilityLockedDown(getActivity(), config) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(),RestrictedLockUtils.getDeviceOwner(getActivity()));
                return;
            }
        }

        if (mDialog != null) {
            removeDialog(WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint;    // 对话框 所显示的 AccessPoint
        mDialogMode = dialogMode;

        showDialog(WIFI_DIALOG_ID);  // 显示Diaglog 对话框
    }
    


/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WIFI_DIALOG_ID:
                AccessPoint ap = mDlgAccessPoint; // For manual launch
                if (ap == null) { // For re-launch from saved state
                    if (mAccessPointSavedState != null) {
                        ap = new AccessPoint(getActivity(), mAccessPointSavedState);
                        // For repeated orientation changes
                        mDlgAccessPoint = ap;
                        // Reset the saved access point data
                        mAccessPointSavedState = null;
                    }
                }
                // If it's null, fine, it's for Add Network
                mSelectedAccessPoint = ap;
                mDialog = new WifiDialog(getActivity(), this, ap, mDialogMode【WifiConfigUiBase.MODE_CONNECT】,/* no hide submit/connect */ false);  // 
                return mDialog;
                ......
             }
             




/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

private WifiConfigController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null);
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint 【NULL】, mMode 【WifiConfigUiBase.MODE_CONNECT】); // 依据 新建了 WifiConfigController  业务交由WifiConfigController实现
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) {
            mController.hideSubmitButton();
        } else {
            /* During creation, the submit button can be unavailable to determine
             * visibility. Right after creation, update button visibility */
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }


/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java   // WifiConfigController 是对话框的控制对象  具体UA17中查看

          public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint【null】,int mode 【WifiConfigUiBase.MODE_CONNECT】) {
        mConfigUi = parent;

        mView = view;
        mAccessPoint = accessPoint;
        mAccessPointSecurity = (accessPoint == null) ? AccessPoint.SECURITY_NONE : accessPoint.getSecurity();  // mAccessPointSecurity在此等于  AccessPoint.SECURITY_NONE
        mMode = mode;

        mTextViewChangedHandler = new Handler();
        
        .....
        
        
        }
        
```

###WIFI UA20 对话框布局代码分析

[R.layout.wifi_dialog](file/wifi_dialog.xml)
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

 R.layout.wifi_dialog
 
 
             <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    style="@style/wifi_item" >
                <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_label"
                        android:text="@string/wifi_ssid"   //  热点的名字
                        android:textDirection="locale" />

                <EditText android:id="@+id/ssid"   // 输入热点密码的输入框  UA20对应的 弹出框输入 EditText
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_edit_content"
                        android:hint="@string/wifi_ssid_hint"
                        android:maxLength="32"
                        android:singleLine="true"
                        android:inputType="textNoSuggestions" />
             </LinearLayout>
      

                <CheckBox android:id="@+id/show_password"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_content"
                        android:textSize="14sp"
                        android:text="@string/wifi_show_password" />  // 是否明文显示密码 选框
                        
                        
                     <Spinner android:id="@+id/ca_cert"   // 证书下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_ca_cert" />
                        
                    <TextView
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_label"
                            android:text="@string/wifi_eap_method" />

                    <Spinner android:id="@+id/method"  // EAP 方法下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/wifi_eap_method"
                            android:entries="@array/wifi_eap_method" />
                      <Spinner android:id="@+id/phase2"  // eap 阶段2 身份验证下拉框
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            style="@style/wifi_item_spinner"
                            android:prompt="@string/please_select_phase2"
                            android:entries="@array/wifi_phase2_entries" />


                            
                 <Spinner android:id="@+id/proxy_settings"   // 代理设置 下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/proxy_settings_title"
                        android:entries="@array/wifi_proxy_settings" />
                        
                        
                   <Spinner android:id="@+id/ip_settings"  // Ip代理设置下拉框
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        style="@style/wifi_item_spinner"
                        android:prompt="@string/wifi_ip_settings"
                        android:entries="@array/wifi_ip_settings" />
```





## WIFI UA21分析 (同UA14)
###WIFI UA21 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/10.png" width = "25%" height="25%"/>
1. **UA编号**   UA21   (同UA14)
1. **UA说明**  **Wifi搜素到的热点 LongPressPreference 的长按事件弹出的上下文菜单**
1. **UA触发函数**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick 点击事件执行了函数 showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);
  弹出的对话框

1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick   showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);
1. **UA触发函数所在类**   
---



###WIFI UA21 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java
public class LongPressAccessPointPreference extends AccessPointPreference {

    @Override
    public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(mFragment); // 长按事件的响应类
            view.itemView.setTag(this); // 设置tag 用于保存当前preference
            view.itemView.setLongClickable(true);  // 长按事件使能
        }
    }
    
  }
    







/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) { // 长按 AccessPoint时触发的函数
            Preference preference = (Preference) view.getTag();

            if (preference instanceof LongPressAccessPointPreference) {
            
            //  private AccessPoint mSelectedAccessPoint;  用户长按的Prference 对应的  AccessPoint 
                mSelectedAccessPoint =((LongPressAccessPointPreference) preference).getAccessPoint();
                menu.setHeaderTitle(mSelectedAccessPoint.getSsid()); //  长按 显示的第一个项  为 热点的名字
                if (mSelectedAccessPoint.isConnectable()) {  // 如果当前网络热点有信号 的话 isConnectable() = true
                //   <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>    显示  连接到网络
                    menu.add(Menu.NONE, MENU_ID_CONNECT, 0, R.string.wifi_menu_connect);
                }

                WifiConfiguration config = mSelectedAccessPoint.getConfig();
                // Some configs are ineditable
                if (isEditabilityLockedDown(getActivity(), config)) {  // 判断该热点对应的 WifiConfiguration 是否可修改
                    return;
                }

                if (mSelectedAccessPoint.isSaved() || mSelectedAccessPoint.isEphemeral()) { // 如果当前preference 对应的AccessPoint 是已保存过 或是 临时的话  显示
                    // Allow forgetting a network if either the network is saved or ephemerally
                    // connected. (In the latter case, "forget" blacklists the network so it won't
                    // be used again, ephemerally).
                    // <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
                    menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
                }
                if (mSelectedAccessPoint.isSaved()) {
                //  <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
                    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
                    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                    if (nfcAdapter != null && nfcAdapter.isEnabled() && mSelectedAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                        // Only allow writing of NFC tags for password-protected networks.
                        // <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>  只在 NFC有效的条件下
                        menu.add(Menu.NONE, MENU_ID_WRITE_NFC, 0, R.string.wifi_menu_write_to_nfc);
                    }
                }
            }
    }
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
   static boolean isEditabilityLockedDown(Context context, WifiConfiguration config) {
        return !canModifyNetwork(context, config); //canModifyNetwork 用于判断是否 WifiConfiguration 可修改    
    }
    

/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    static boolean canModifyNetwork(Context context, WifiConfiguration config) {
        if (config == null) {
            return true;
        }
        final DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService( Context.DEVICE_POLICY_SERVICE);
        final PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_DEVICE_ADMIN) && dpm == null) {
            return false;
        }

        boolean isConfigEligibleForLockdown = false;
        if (dpm != null) {
            final ComponentName deviceOwner = dpm.getDeviceOwnerComponentOnAnyUser();
            if (deviceOwner != null) {
                final int deviceOwnerUserId = dpm.getDeviceOwnerUserId();
                try {
                    final int deviceOwnerUid = pm.getPackageUidAsUser(deviceOwner.getPackageName(),deviceOwnerUserId);
                    isConfigEligibleForLockdown = deviceOwnerUid == config.creatorUid;
                } catch (NameNotFoundException e) {
                    // don't care
                }
            }
        }
        if (!isConfigEligibleForLockdown) {
            return true;
        }
//         public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown"; 用于判断是否允许用户修改wifi配置的数据库项  1-true  0 false
        final ContentResolver resolver = context.getContentResolver();
        final boolean isLockdownFeatureEnabled = Settings.Global.getInt(resolver,Settings.Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN, 0) != 0;
        return !isLockdownFeatureEnabled;
    }
    
    
    
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }    
    public int getLevel() {
        return WifiManager.calculateSignalLevel(mRssi, SIGNAL_LEVELS);
    }
    public DetailedState getDetailedState() {
        if (mNetworkInfo != null) {
            return mNetworkInfo.getDetailedState();
        }
        Log.w(TAG, "NetworkInfo is null, cannot return detailed state");
        return null;
    }
    

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
public boolean isSaved() {
        return networkId != WifiConfiguration.INVALID_NETWORK_ID;
    }

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isEphemeral() {
        return mInfo != null && mInfo.isEphemeral() &&
                mNetworkInfo != null && mNetworkInfo.getState() != State.DISCONNECTED;
    }
    

    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
     @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (mSelectedAccessPoint == null) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) { // 用户选择 上下文按钮
            case MENU_ID_CONNECT: {  // 选择  连接到网络  UA20 执行到这里    连接到网络
                boolean isSavedNetwork = mSelectedAccessPoint.isSaved(); // 判断当前网络是否有 networkId
                if (isSavedNetwork) {
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork); // 如果有执行 connect(final WifiConfiguration config, boolean isSavedNetwork) 方法
                // // 如果当前网络没有加密认证 则先生成 networkdId 再 connect(final WifiConfiguration config, boolean isSavedNetwork)
                } else if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { 
                    /** Bypass dialog for unsecured networks */
                    mSelectedAccessPoint.generateOpenNetworkConfig();  // 用于产生 WifiConfiguration 
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork);
                } else {
                    showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT); // 弹出 Dialog框 用于 用户输入 一些认证信息才能连接
                }
                return true;
            }
            case MENU_ID_FORGET: {   // 选择 取消保存网络
                forget();   
                return true;
            }
            case MENU_ID_MODIFY: {  // 选择 修改网络
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);
                return true;
            }
            case MENU_ID_WRITE_NFC:  // 选择 写入 NFC标签
                showDialog(WRITE_NFC_DIALOG_ID);
                return true;

        }
        return super.onContextItemSelected(item);
    }
    
   



```










## WIFI UA22分析 (同UA14)
###WIFI UA22 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/>
1. **UA编号**   UA22   (同UA14)
1. **UA说明**  **Wifi搜素到的热点 LongPressPreference 的长按事件弹出的上下文菜单中的 Forget 忘记当前已保存的网络菜单选项**
1. **UA触发函数**  packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick   menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
   packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  forget();   
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick   menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
1. **UA触发函数所在类**     packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  forget();   
---



###WIFI UA22 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java
public class LongPressAccessPointPreference extends AccessPointPreference {

    @Override
    public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(mFragment); // 长按事件的响应类
            view.itemView.setTag(this); // 设置tag 用于保存当前preference
            view.itemView.setLongClickable(true);  // 长按事件使能
        }
    }
    
  }
    







/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) { // 长按 AccessPoint时触发的函数
            Preference preference = (Preference) view.getTag();

            if (preference instanceof LongPressAccessPointPreference) {
            
            //  private AccessPoint mSelectedAccessPoint;  用户长按的Prference 对应的  AccessPoint 
                mSelectedAccessPoint =((LongPressAccessPointPreference) preference).getAccessPoint();
                menu.setHeaderTitle(mSelectedAccessPoint.getSsid()); //  长按 显示的第一个项  为 热点的名字
                if (mSelectedAccessPoint.isConnectable()) {  // 如果当前网络热点有信号 的话 isConnectable() = true
                //   <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>    显示  连接到网络
                    menu.add(Menu.NONE, MENU_ID_CONNECT, 0, R.string.wifi_menu_connect);
                }

                WifiConfiguration config = mSelectedAccessPoint.getConfig();
                // Some configs are ineditable
                if (isEditabilityLockedDown(getActivity(), config)) {  // 判断该热点对应的 WifiConfiguration 是否可修改
                    return;
                }

                if (mSelectedAccessPoint.isSaved() || mSelectedAccessPoint.isEphemeral()) { // 如果当前preference 对应的AccessPoint 是已保存过 或是 临时的话  显示
                    // Allow forgetting a network if either the network is saved or ephemerally
                    // connected. (In the latter case, "forget" blacklists the network so it won't
                    // be used again, ephemerally).
                    // <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
                    menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
                }
                if (mSelectedAccessPoint.isSaved()) {
                //  <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
                    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
                    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                    if (nfcAdapter != null && nfcAdapter.isEnabled() && mSelectedAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                        // Only allow writing of NFC tags for password-protected networks.
                        // <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>  只在 NFC有效的条件下
                        menu.add(Menu.NONE, MENU_ID_WRITE_NFC, 0, R.string.wifi_menu_write_to_nfc);
                    }
                }
            }
    }
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
   static boolean isEditabilityLockedDown(Context context, WifiConfiguration config) {
        return !canModifyNetwork(context, config); //canModifyNetwork 用于判断是否 WifiConfiguration 可修改    
    }
    

/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    static boolean canModifyNetwork(Context context, WifiConfiguration config) {
        if (config == null) {
            return true;
        }
        final DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService( Context.DEVICE_POLICY_SERVICE);
        final PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_DEVICE_ADMIN) && dpm == null) {
            return false;
        }

        boolean isConfigEligibleForLockdown = false;
        if (dpm != null) {
            final ComponentName deviceOwner = dpm.getDeviceOwnerComponentOnAnyUser();
            if (deviceOwner != null) {
                final int deviceOwnerUserId = dpm.getDeviceOwnerUserId();
                try {
                    final int deviceOwnerUid = pm.getPackageUidAsUser(deviceOwner.getPackageName(),deviceOwnerUserId);
                    isConfigEligibleForLockdown = deviceOwnerUid == config.creatorUid;
                } catch (NameNotFoundException e) {
                    // don't care
                }
            }
        }
        if (!isConfigEligibleForLockdown) {
            return true;
        }
//         public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown"; 用于判断是否允许用户修改wifi配置的数据库项  1-true  0 false
        final ContentResolver resolver = context.getContentResolver();
        final boolean isLockdownFeatureEnabled = Settings.Global.getInt(resolver,Settings.Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN, 0) != 0;
        return !isLockdownFeatureEnabled;
    }
    
    
    
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }    
    public int getLevel() {
        return WifiManager.calculateSignalLevel(mRssi, SIGNAL_LEVELS);
    }
    public DetailedState getDetailedState() {
        if (mNetworkInfo != null) {
            return mNetworkInfo.getDetailedState();
        }
        Log.w(TAG, "NetworkInfo is null, cannot return detailed state");
        return null;
    }
    

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
public boolean isSaved() {
        return networkId != WifiConfiguration.INVALID_NETWORK_ID;
    }

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isEphemeral() {
        return mInfo != null && mInfo.isEphemeral() &&
                mNetworkInfo != null && mNetworkInfo.getState() != State.DISCONNECTED;
    }
    

    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
     @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (mSelectedAccessPoint == null) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) { // 用户选择 上下文按钮
            case MENU_ID_CONNECT: {  // 选择  连接到网络  UA20 执行到这里    连接到网络
                boolean isSavedNetwork = mSelectedAccessPoint.isSaved(); // 判断当前网络是否有 networkId
                if (isSavedNetwork) {
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork); // 如果有执行 connect(final WifiConfiguration config, boolean isSavedNetwork) 方法
                // // 如果当前网络没有加密认证 则先生成 networkdId 再 connect(final WifiConfiguration config, boolean isSavedNetwork)
                } else if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { 
                    /** Bypass dialog for unsecured networks */
                    mSelectedAccessPoint.generateOpenNetworkConfig();  // 用于产生 WifiConfiguration 
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork);
                } else {
                    showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT); // 弹出 Dialog框 用于 用户输入 一些认证信息才能连接
                }
                return true;
            }
            case MENU_ID_FORGET: {   // 选择 取消保存网络
                forget();      //  UA22 执行的 逻辑业务 
                return true;
            }
            case MENU_ID_MODIFY: {  // 选择 修改网络
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);
                return true;
            }
            case MENU_ID_WRITE_NFC:  // 选择 写入 NFC标签
                showDialog(WRITE_NFC_DIALOG_ID);
                return true;

        }
        return super.onContextItemSelected(item);
    }
    
   



```






## WIFI UA23分析 (同UA14)
###WIFI UA23 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/11.png" width = "25%" height="25%"/><img src="//../zimage/wireless/wifi/01_wifisetting/28.png" width = "25%" height="25%"/>
1. **UA编号**   UA23   (同UA14)
1. **UA说明**  **Wifi搜素到的热点 LongPressPreference 的长按事件弹出的上下文菜单中的 修改网络 的 菜单选项**
1. **UA触发函数**  packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
   showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);  // 展示修改网络的Dialog  UA23 的业务函数 
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**    packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java  onPreferenceTreeClick    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
1. **UA触发函数所在类**    showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);  // 展示修改网络的Dialog  UA23 的业务函数  
---



###WIFI UA23 代码分析
```
/packages/apps/Settings/src/com/android/settings/wifi/LongPressAccessPointPreference.java
public class LongPressAccessPointPreference extends AccessPointPreference {

    @Override
    public void onBindViewHolder(final PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(mFragment); // 长按事件的响应类
            view.itemView.setTag(this); // 设置tag 用于保存当前preference
            view.itemView.setLongClickable(true);  // 长按事件使能
        }
    }
    
  }
    







/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) { // 长按 AccessPoint时触发的函数
            Preference preference = (Preference) view.getTag();

            if (preference instanceof LongPressAccessPointPreference) {
            
            //  private AccessPoint mSelectedAccessPoint;  用户长按的Prference 对应的  AccessPoint 
                mSelectedAccessPoint =((LongPressAccessPointPreference) preference).getAccessPoint();
                menu.setHeaderTitle(mSelectedAccessPoint.getSsid()); //  长按 显示的第一个项  为 热点的名字
                if (mSelectedAccessPoint.isConnectable()) {  // 如果当前网络热点有信号 的话 isConnectable() = true
                //   <string name="wifi_menu_connect" msgid="4996220309848349408">"连接到网络"</string>    显示  连接到网络
                    menu.add(Menu.NONE, MENU_ID_CONNECT, 0, R.string.wifi_menu_connect);
                }

                WifiConfiguration config = mSelectedAccessPoint.getConfig();
                // Some configs are ineditable
                if (isEditabilityLockedDown(getActivity(), config)) {  // 判断该热点对应的 WifiConfiguration 是否可修改
                    return;
                }

                if (mSelectedAccessPoint.isSaved() || mSelectedAccessPoint.isEphemeral()) { // 如果当前preference 对应的AccessPoint 是已保存过 或是 临时的话  显示
                    // Allow forgetting a network if either the network is saved or ephemerally
                    // connected. (In the latter case, "forget" blacklists the network so it won't
                    // be used again, ephemerally).
                    // <string name="wifi_menu_forget" msgid="8736964302477327114">"取消保存网络"</string>
                    menu.add(Menu.NONE, MENU_ID_FORGET, 0, R.string.wifi_menu_forget);
                }
                if (mSelectedAccessPoint.isSaved()) {
                //  <string name="wifi_menu_modify" msgid="2068554918652440105">"修改网络"</string>
                    menu.add(Menu.NONE, MENU_ID_MODIFY, 0, R.string.wifi_menu_modify);
                    NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                    if (nfcAdapter != null && nfcAdapter.isEnabled() && mSelectedAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                        // Only allow writing of NFC tags for password-protected networks.
                        // <string name="wifi_menu_write_to_nfc" msgid="7692881642188240324">"写入NFC标签"</string>  只在 NFC有效的条件下
                        menu.add(Menu.NONE, MENU_ID_WRITE_NFC, 0, R.string.wifi_menu_write_to_nfc);
                    }
                }
            }
    }
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
   static boolean isEditabilityLockedDown(Context context, WifiConfiguration config) {
        return !canModifyNetwork(context, config); //canModifyNetwork 用于判断是否 WifiConfiguration 可修改    
    }
    

/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    static boolean canModifyNetwork(Context context, WifiConfiguration config) {
        if (config == null) {
            return true;
        }
        final DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService( Context.DEVICE_POLICY_SERVICE);
        final PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_DEVICE_ADMIN) && dpm == null) {
            return false;
        }

        boolean isConfigEligibleForLockdown = false;
        if (dpm != null) {
            final ComponentName deviceOwner = dpm.getDeviceOwnerComponentOnAnyUser();
            if (deviceOwner != null) {
                final int deviceOwnerUserId = dpm.getDeviceOwnerUserId();
                try {
                    final int deviceOwnerUid = pm.getPackageUidAsUser(deviceOwner.getPackageName(),deviceOwnerUserId);
                    isConfigEligibleForLockdown = deviceOwnerUid == config.creatorUid;
                } catch (NameNotFoundException e) {
                    // don't care
                }
            }
        }
        if (!isConfigEligibleForLockdown) {
            return true;
        }
//         public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown"; 用于判断是否允许用户修改wifi配置的数据库项  1-true  0 false
        final ContentResolver resolver = context.getContentResolver();
        final boolean isLockdownFeatureEnabled = Settings.Global.getInt(resolver,Settings.Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN, 0) != 0;
        return !isLockdownFeatureEnabled;
    }
    
    
    
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }    
    public int getLevel() {
        return WifiManager.calculateSignalLevel(mRssi, SIGNAL_LEVELS);
    }
    public DetailedState getDetailedState() {
        if (mNetworkInfo != null) {
            return mNetworkInfo.getDetailedState();
        }
        Log.w(TAG, "NetworkInfo is null, cannot return detailed state");
        return null;
    }
    

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
public boolean isSaved() {
        return networkId != WifiConfiguration.INVALID_NETWORK_ID;
    }

/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java
    public boolean isEphemeral() {
        return mInfo != null && mInfo.isEphemeral() &&
                mNetworkInfo != null && mNetworkInfo.getState() != State.DISCONNECTED;
    }
    

    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
     @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (mSelectedAccessPoint == null) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) { // 用户选择 上下文按钮
            case MENU_ID_CONNECT: {  // 选择  连接到网络  UA20 执行到这里    连接到网络
                boolean isSavedNetwork = mSelectedAccessPoint.isSaved(); // 判断当前网络是否有 networkId
                if (isSavedNetwork) {
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork); // 如果有执行 connect(final WifiConfiguration config, boolean isSavedNetwork) 方法
                // // 如果当前网络没有加密认证 则先生成 networkdId 再 connect(final WifiConfiguration config, boolean isSavedNetwork)
                } else if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { 
                    /** Bypass dialog for unsecured networks */
                    mSelectedAccessPoint.generateOpenNetworkConfig();  // 用于产生 WifiConfiguration 
                    connect(mSelectedAccessPoint.getConfig(), isSavedNetwork);
                } else {
                    showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT); // 弹出 Dialog框 用于 用户输入 一些认证信息才能连接
                }
                return true;
            }
            case MENU_ID_FORGET: {   // 选择 取消保存网络
                forget();      //  UA22 执行的 逻辑业务 
                return true;
            }
            case MENU_ID_MODIFY: {  // 选择 修改网络
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_MODIFY);  // 展示修改网络的Dialog  UA23 的业务函数
                return true;
            }
            case MENU_ID_WRITE_NFC:  // 选择 写入 NFC标签
                showDialog(WRITE_NFC_DIALOG_ID);
                return true;

        }
        return super.onContextItemSelected(item);
    }
    
   
   
   
   /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
      private void showDialog(AccessPoint accessPoint, int dialogMode  【WifiConfigUiBase.MODE_MODIFY】) {
        if (accessPoint != null) {
            WifiConfiguration config = accessPoint.getConfig();
            if (isEditabilityLockedDown(getActivity(), config) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(),RestrictedLockUtils.getDeviceOwner(getActivity()));
                return;
            }
        }

        if (mDialog != null) {
            removeDialog(WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint;    // 对话框 所显示的 AccessPoint
        mDialogMode = dialogMode;

        showDialog(WIFI_DIALOG_ID);  // 显示Diaglog 对话框
    }
    
    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WIFI_DIALOG_ID:
                AccessPoint ap = mDlgAccessPoint; // For manual launch
                if (ap == null) { // For re-launch from saved state
                    if (mAccessPointSavedState != null) {
                        ap = new AccessPoint(getActivity(), mAccessPointSavedState);
                        // For repeated orientation changes
                        mDlgAccessPoint = ap;
                        // Reset the saved access point data
                        mAccessPointSavedState = null;
                    }
                }
                // If it's null, fine, it's for Add Network
                mSelectedAccessPoint = ap;
                mDialog = new WifiDialog(getActivity(), this, ap, mDialogMode【WifiConfigUiBase.MODE_MODIFY】,/* no hide submit/connect */ false);  // 
                return mDialog;
                ......
             }





/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

private WifiConfigController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null);  //  对话框的布局文件
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint 【NULL】, mMode 【WifiConfigUiBase.MODE_MODIFY】); // 依据 新建了 WifiConfigController  业务交由WifiConfigController实现
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) {
            mController.hideSubmitButton();
        } else {
            /* During creation, the submit button can be unavailable to determine
             * visibility. Right after creation, update button visibility */
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }


/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java   // WifiConfigController 是对话框的控制对象  具体UA17中查看

          public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint【null】,int mode 【WifiConfigUiBase.MODE_MODIFY】) {
        mConfigUi = parent;

        mView = view;
        mAccessPoint = accessPoint;
        mAccessPointSecurity = (accessPoint == null) ? AccessPoint.SECURITY_NONE : accessPoint.getSecurity();  // mAccessPointSecurity在此等于  AccessPoint.SECURITY_NONE
        mMode = mode;

        mTextViewChangedHandler = new Handler();
        
        .....
        
        
        }
```




## WIFI UA24分析 (同UA17)
###WIFI UA24 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/12.png" width = "25%" height="25%"/>
1. **UA编号**   UA24   (同UA17)
1. **UA说明**  **增加网络Preference点击后进入的增加网络页面中的 网络SSID输入框**
1. **UA触发函数**  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA24 代码分析


```


/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 

private Preference mAddPreference;
    @Override
    public void onCreate(Bundle icicle) {
        Context prefContext = getPrefContext();
        mAddPreference = new Preference(prefContext);
        mAddPreference.setIcon(R.drawable.ic_menu_add_inset);
        mAddPreference.setTitle(R.string.wifi_add_network);   // <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>
        mAccessPointsPreferenceCategory.addPreference(mAddPreference);
       }




    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);  // 如果 preference设置了 fragment 则 跳转到该 fragment
        }

        if (preference instanceof LongPressAccessPointPreference) {
            mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            if (mSelectedAccessPoint == null) {
                return false;
            }
            if (mSelectedAccessPoint.isActive()) {  // 如果当前 AccessPoint 是 Active 活动的 则让 父类处置
                return super.onPreferenceTreeClick(preference);
            }
            /**
             * Bypass dialog and connect to unsecured networks, or previously connected saved
             * networks, or Passpoint provided networks.
             */
            WifiConfiguration config = mSelectedAccessPoint.getConfig();
            if (mSelectedAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) { // 如果当前 AccessPoint 是不需要认证的 那么直接 连接它 通过 WifiConfiguration
                mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(mSelectedAccessPoint.getConfig(), mSelectedAccessPoint.isSaved());
            } else if (mSelectedAccessPoint.isSaved() && config != null
                    && config.getNetworkSelectionStatus() != null
                    && config.getNetworkSelectionStatus().getHasEverConnected()) {
                connect(config, true /* isSavedNetwork */);
            } else if (mSelectedAccessPoint.isPasspoint()) {  // 如果当前网络是 Passpoint类型的网络 那么 直接连接 
                // Access point provided by an installed Passpoint provider, connect using
                // the associated config.
                connect(config, true /* isSavedNetwork */);
            } else {
                showDialog(mSelectedAccessPoint, WifiConfigUiBase.MODE_CONNECT);  // 否则就弹出对话框  请求用户进行认证输入 已达到连接的目的
            }
        } else if (preference == mAddPreference) {
            onAddNetworkPressed(); // 如果点击的是  mAddPreference  添加网络那项 Preference  那么执行   onAddNetworkPressed() 执行添加网络的操作
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }




/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 


void onAddNetworkPressed() {
        mMetricsFeatureProvider.action(getActivity(), MetricsEvent.ACTION_WIFI_ADD_NETWORK);
        // No exact access point is selected.
        mSelectedAccessPoint = null;
        showDialog(null, WifiConfigUiBase.MODE_CONNECT); // 显示 添加网络的Dialog    此处 mSelectedAccessPoint 为空
    }
    
    
    
    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WIFI_DIALOG_ID:
                AccessPoint ap = mDlgAccessPoint; // For manual launch
                if (ap == null) { // For re-launch from saved state
                    if (mAccessPointSavedState != null) {
                        ap = new AccessPoint(getActivity(), mAccessPointSavedState);
                        // For repeated orientation changes
                        mDlgAccessPoint = ap;
                        // Reset the saved access point data
                        mAccessPointSavedState = null;
                    }
                }
                // If it's null, fine, it's for Add Network
                mSelectedAccessPoint = ap;
                mDialog = new WifiDialog(getActivity(), this, ap【NULL】, mDialogMode【WifiConfigUiBase.MODE_MODIFY】,/* no hide submit/connect */ false);  // 
                return mDialog;
                ......
             }
             
             
             
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java   
private WifiConfigController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null);  //  对话框的布局文件
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint 【NULL】, mMode 【WifiConfigUiBase.MODE_MODIFY】); // 依据 新建了 WifiConfigController  业务交由WifiConfigController实现
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) {
            mController.hideSubmitButton();
        } else {
            /* During creation, the submit button can be unavailable to determine
             * visibility. Right after creation, update button visibility */
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }


/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java   // WifiConfigController 是对话框的控制对象  具体UA17中查看

          public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint【null】,int mode 【WifiConfigUiBase.MODE_MODIFY】) {
        mConfigUi = parent;

        mView = view;
        mAccessPoint = accessPoint;
        mAccessPointSecurity = (accessPoint == null) ? AccessPoint.SECURITY_NONE : accessPoint.getSecurity();  // mAccessPointSecurity在此等于  AccessPoint.SECURITY_NONE
        mMode = mode;

        mTextViewChangedHandler = new Handler();
        
        .....
        
        if (mAccessPoint == null) { // new network  // 当前的 AccessPoint为空 说明是 需要 新增 网络
            mConfigUi.setTitle(R.string.wifi_add_network);  // <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>

            mSsidView = (TextView) mView.findViewById(R.id.ssid);
            mSsidView.addTextChangedListener(this);
            mSecuritySpinner = ((Spinner) mView.findViewById(R.id.security)); 【安全性】
            mSecuritySpinner.setOnItemSelectedListener(this);
            mView.findViewById(R.id.type).setVisibility(View.VISIBLE);

            showIpConfigFields();
            showProxyFields();
            mView.findViewById(R.id.wifi_advanced_toggle).setVisibility(View.VISIBLE);
            ((CheckBox) mView.findViewById(R.id.wifi_advanced_togglebox)).setOnCheckedChangeListener(this); 【高级选项】
            mConfigUi.setSubmitButton(res.getString(R.string.wifi_save));  // <string name="wifi_save" msgid="3331121567988522826">"保存"</string>
        }
        .....
        
        }

```


### UA24  布局分析
<img src="//../zimage/wireless/wifi/01_wifisetting/12.png" width = "25%" height="25%"/>
[R.layout.wifi_dialog](file/wifi_dialog.xml)
```
/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java

 R.layout.wifi_dialog
 
........
<EditText android:id="@+id/ssid"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@style/wifi_item_edit_content"
        android:hint="@string/wifi_ssid_hint"  // <string name="wifi_ssid_hint" msgid="897593601067321355">"输入SSID"</string>
        android:maxLength="32"
        android:singleLine="true"
        android:inputType="textNoSuggestions" />
........
```



## WIFI UA25分析 (接 UA25的代码分析)
###WIFI UA25 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/12.png" width = "25%" height="25%"/>
1. **UA编号**   UA25   (同UA14)
1. **UA说明**  **增加网络Preference点击后进入的增加网络页面中的  确认保存按钮**
1. **UA触发函数**  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA25 代码分析


```

/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java   // WifiConfigController 是对话框的控制对象  具体UA17中查看
private final WifiConfigUiBase mConfigUi;

          public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint【null】,int mode 【WifiConfigUiBase.MODE_MODIFY】) {
        mConfigUi = parent;

        mView = view;
        mAccessPoint = accessPoint;
        mAccessPointSecurity = (accessPoint == null) ? AccessPoint.SECURITY_NONE : accessPoint.getSecurity();  // mAccessPointSecurity在此等于  AccessPoint.SECURITY_NONE
        mMode = mode;

        mTextViewChangedHandler = new Handler();
        
        .....
        
        if (mAccessPoint == null) { // new network  // 当前的 AccessPoint为空 说明是 需要 新增 网络
            mConfigUi.setTitle(R.string.wifi_add_network);  // <string name="wifi_add_network" msgid="6234851776910938957">"添加网络"</string>

            mSsidView = (TextView) mView.findViewById(R.id.ssid);
            mSsidView.addTextChangedListener(this);
            mSecuritySpinner = ((Spinner) mView.findViewById(R.id.security)); 【安全性】
            mSecuritySpinner.setOnItemSelectedListener(this);
            mView.findViewById(R.id.type).setVisibility(View.VISIBLE);

            showIpConfigFields();
            showProxyFields();
            mView.findViewById(R.id.wifi_advanced_toggle).setVisibility(View.VISIBLE);
            ((CheckBox) mView.findViewById(R.id.wifi_advanced_togglebox)).setOnCheckedChangeListener(this); 【高级选项】
            mConfigUi.setSubmitButton(res.getString(R.string.wifi_save));  // <string name="wifi_save" msgid="3331121567988522826">"保存"</string>
        }
        .....
        
        }
        
        
        
        
 /packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java
       @Override
    public void onClick(DialogInterface dialogInterface, int id) {
        if (mListener != null) {
            switch (id) {
                case BUTTON_SUBMIT:
                    mListener.onSubmit(this); //  提交后 UA25 执行到这里
                    break;
                case BUTTON_FORGET:
                    if (WifiSettings.isEditabilityLockedDown(
                            getContext(), mAccessPoint.getConfig())) {
                        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(),
                                RestrictedLockUtils.getDeviceOwner(getContext()));
                        return;
                    }
                    mListener.onForget(this);
                    break;
            }
        }
    }
    
 /packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java 
 private WifiConfigController mController;
    @Override
    public WifiConfigController getController() {
        return mController;
    }
    
    
    
/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
     @Override
    public void onForget(WifiDialog dialog) {
        forget();
    }



/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    @Override
    public void onSubmit(WifiDialog dialog) {
        if (mDialog != null) {
            submit(mDialog.getController());
        }
    }




/packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java
    /* package */ void submit(WifiConfigController configController) {

        final WifiConfiguration config = configController.getConfig();

        if (config == null) {  // 如果配置为空  那么尝试 连接它
            if (mSelectedAccessPoint != null && mSelectedAccessPoint.isSaved()) { 
            connect(mSelectedAccessPoint.getConfig(), true /* isSavedNetwork */);
            }
        } else if (configController.getMode() == WifiConfigUiBase.MODE_MODIFY) {
            mWifiManager.save(config, mSaveListener);  // 如果 config 配置不为空  并且是保存模式  那么就 执行  mWifiManager.save  ▲6 分析UA后分析 WIFIManager
        } else {
            mWifiManager.save(config, mSaveListener);
            if (mSelectedAccessPoint != null) { // Not an "Add network"
                connect(config, false /* isSavedNetwork */);    //  尝试 连接
            }
        }

        mWifiTracker.resumeScanning();
    }
    
```







## WIFI UA26分析 (同 UA19的代码分析)
###WIFI UA26 操作列
<img src="//../zimage/wireless/wifi/01_wifisetting/13.png" width = "25%" height="25%"/>
1. **UA编号**   UA26   (同UA19)
1. **UA说明**  **已保存网络页面中的 Preference Item的点击事件  无 长按事件**
1. **UA触发函数**  SavedAccessPointsWifiSettings 中的   onPreferenceTreeClick 函数
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA26 代码分析
```

 /packages/apps/Settings/src/com/android/settings/wifi/WifiSettings.java 
  @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        // If the preference has a fragment set, open that
        if (preference.getFragment() != null) { 
//  如果当前 从 preference获取的 Fragment 不为 空  那么  调用   super.onPreferenceTreeClick(preference)  
// xml   文件 定义了 该 Fragment   SavedAccessPointsWifiSettings
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        }



/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java


public class SavedAccessPointsWifiSettings extends SettingsPreferenceFragment implements Indexable, WifiDialog.WifiDialogListener {
        
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.wifi_display_saved_access_points);  // 已保存热点的布局文件
        mUserBadgeCache = new AccessPointPreference.UserBadgeCache(getPackageManager());
         initPreferences();
    }



/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    private void initPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        final Context context = getPrefContext();

        final List<AccessPoint> accessPoints = WifiSavedConfigUtils.getAllConfigs(context, mWifiManager); // 获得所有保存的热点的 AccessPoint
        Collections.sort(accessPoints, SAVED_NETWORK_COMPARATOR); // 热点集合进行排序
        preferenceScreen.removeAll();

        final int accessPointsSize = accessPoints.size();
        for (int i = 0; i < accessPointsSize; ++i){
        // 新建LongPressAccessPointPreference  项Item  添加到  PreferenceScreen
            LongPressAccessPointPreference preference =new LongPressAccessPointPreference(accessPoints.get(i), context, mUserBadgeCache, true, this);
            preference.setIcon(null);  // 不设置图标
            preferenceScreen.addPreference(preference);  // 添加到  preferenceScreen
        }

        if(getPreferenceScreen().getPreferenceCount() < 1) {
            Log.w(TAG, "Saved networks activity loaded, but there are no saved networks!");
        }
    }
}

  
/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {  // 已保存网络里的Preference 的点击事件
        if (preference instanceof LongPressAccessPointPreference) {
            showDialog((LongPressAccessPointPreference) preference, false);
            return true;
        } else{
            return super.onPreferenceTreeClick(preference);
        }
    }
     


/packages/apps/Settings/src/com/android/settings/wifi/SavedAccessPointsWifiSettings.java
    private void showDialog(LongPressAccessPointPreference accessPoint, boolean edit) {
        if (mDialog != null) {
            removeDialog(WifiSettings.WIFI_DIALOG_ID);
            mDialog = null;
        }

        // Save the access point and edit mode
        mDlgAccessPoint = accessPoint.getAccessPoint();

        showDialog(WifiSettings.WIFI_DIALOG_ID);  // 显示提示框
    }
    
    
    @Override
    public Dialog onCreateDialog(int dialogId) {
        switch (dialogId) {
            case WifiSettings.WIFI_DIALOG_ID:
                if (mDlgAccessPoint == null) { // For re-launch from saved state
                    mDlgAccessPoint = new AccessPoint(getActivity(), mAccessPointSavedState);
                    // Reset the saved access point data
                    mAccessPointSavedState = null;
                }
                mSelectedAccessPoint = mDlgAccessPoint;

                mDialog = new WifiDialog(getActivity(), this, mDlgAccessPoint, WifiConfigUiBase.MODE_VIEW, true /* hide the submit button */); // 显示的模式是 WifiConfigUiBase.MODE_VIEW
                return mDialog;

        }
        return super.onCreateDialog(dialogId);
    }




/packages/apps/Settings/src/com/android/settings/wifi/WifiDialog.java   
private WifiConfigController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mView = getLayoutInflater().inflate(R.layout.wifi_dialog, null);  //  对话框的布局文件
        setView(mView);
        setInverseBackgroundForced(true);
        mController = new WifiConfigController(this, mView, mAccessPoint 【NULL】, mMode 【WifiConfigUiBase.MODE_VIEW 】); // 依据 新建了 WifiConfigController  业务交由WifiConfigController实现
        super.onCreate(savedInstanceState);

        if (mHideSubmitButton) {
            mController.hideSubmitButton();
        } else {
            /* During creation, the submit button can be unavailable to determine
             * visibility. Right after creation, update button visibility */
            mController.enableSubmitIfAppropriate();
        }

        if (mAccessPoint == null) {
            mController.hideForgetButton();
        }
    }
    


/packages/apps/Settings/src/com/android/settings/wifi/WifiConfigController.java
    public WifiConfigController(WifiConfigUiBase parent, View view, AccessPoint accessPoint,int mode) {




         mConfigUi.setForgetButton(res.getString(R.string.wifi_forget)); 【取消保存】
        mConfigUi.setCancelButton(res.getString(R.string.wifi_cancel));
        
        
WifiConfiguration getConfig() {  // 模式MODE_VIEW 下 返回的WifiConfiguration 设置为空
        if (mMode == WifiConfigUiBase.MODE_VIEW) {
            return null;
        }
}

<string name="wifi_cancel" msgid="6763568902542968964">"取消"</string>
<string name="wifi_forget" msgid="8168174695608386644">"取消保存"</string>
          
```





## WIFI UA34分析 
###WIFI UA34 操作列   《系统》>《开发者选项》>《无线显示认证》
<img src="//../zimage/wireless/wifi/01_wifisetting/18.png" width = "25%" height="25%"/>
1. **UA编号**   UA34  
1. **UA说明**  **《系统》>《开发者选项》>《无线显示认证》的switch开关**
1. **UA触发函数**  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **   wifi_display_certification_on
1. **数据库可选值 **     0   1
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---
```

D:\gitrank\articles-master>adb shell dumpsys window | findstr mCurrentFocus
  mCurrentFocus=Window{e2b2e4a u0 com.android.settings/com.android.settings.SubSettings}
  


    <plurals name="show_dev_countdown" formatted="false" msgid="7201398282729229649">
      <item quantity="other">现在只需再执行 <xliff:g id="STEP_COUNT_1">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
      <item quantity="one">现在只需再执行 <xliff:g id="STEP_COUNT_0">%1$d</xliff:g> 步操作即可进入开发者模式。</item>
    </plurals>
    
<string name="show_dev_already" msgid="2151632240145446227">"您已处于开发者模式，无需进行此操作。"</string>
 <string name="status_operator" msgid="2274875196954742087">"网络"</string>
 
 
/packages/apps/Settings/src/com/android/settings/deviceinfo/BuildNumberPreferenceController.java


    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
mDevHitToast = Toast.makeText(mContext,mContext.getResources().getQuantityString(R.plurals.show_dev_countdown, mDevHitCountdown,mDevHitCountdown),Toast.LENGTH_SHORT);
mDevHitToast.show();  // 显示Toast   还差多少步进入开发者模式


mDevHitToast = Toast.makeText(mContext, R.string.show_dev_already,Toast.LENGTH_LONG);
mDevHitToast.show();
mMetricsFeatureProvider.action( mContext, MetricsEvent.ACTION_SETTINGS_BUILD_NUMBER_PREF, Pair.create(MetricsEvent.FIELD_SETTINGS_BUILD_NUMBER_DEVELOPER_MODE_ENABLED, 1));
 }
        
....

}



/packages/apps/Settings/src/com/android/settings/development/DevelopmentSettings.java


    private VerifyAppsOverUsbPreferenceController mVerifyAppsOverUsbController;
    private SwitchPreference mWifiDisplayCertification; // 无线显示认证
    private SwitchPreference mWifiVerboseLogging;
    private SwitchPreference mWifiAggressiveHandover;
    
    
    private static final String WIFI_DISPLAY_CERTIFICATION_KEY = "wifi_display_certification";   // 无线显示认证
    private static final String WIFI_VERBOSE_LOGGING_KEY = "wifi_verbose_logging";   // 启用 wiif详细日志记录功能
    private static final String WIFI_AGGRESSIVE_HANDOVER_KEY = "wifi_aggressive_handover";  // 主动从WLAN网络切换到移动数据网络
    private static final String WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY = "wifi_allow_scan_with_traffic";  //  始终允许WLAN漫游扫描
    
    
    
    
    mWifiDisplayCertification = findAndInitSwitchPref(WIFI_DISPLAY_CERTIFICATION_KEY);
    
    
    
    
    
    private void updateWifiDisplayCertificationOptions() {
        updateSwitchPreference(mWifiDisplayCertification, Settings.Global.getInt( getActivity().getContentResolver(),Settings.Global.WIFI_DISPLAY_CERTIFICATION_ON, 0) != 0);
    }

    private void writeWifiDisplayCertificationOptions() {
    // 设置数据库中 wifi_display_certification_on 这个Item的值    默认值为0
        Settings.Global.putInt(getActivity().getContentResolver(),Settings.Global.WIFI_DISPLAY_CERTIFICATION_ON【"wifi_display_certification_on"】,mWifiDisplayCertification.isChecked() ? 1 : 0);
    }



    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
    
    else if (preference == mWifiDisplayCertification) {
            writeWifiDisplayCertificationOptions();
        }
    
    
    /frameworks/base/core/java/android/provider/Settings.java
       public static final String WIFI_DISPLAY_CERTIFICATION_ON ="wifi_display_certification_on";
               
```



###WIFI UA34 代码分析





## WIFI UA35分析 
###WIFI UA35 操作列   《系统》>《开发者选项》>《启用 wiif详细日志记录功能》
<img src="//../zimage/wireless/wifi/01_wifisetting/18.png" width = "25%" height="25%"/>
1. **UA编号**   UA35  
1. **UA说明**  **《系统》>《开发者选项》>《启用 wiif详细日志记录功能》的switch开关**
1. **UA触发函数**   WifiManager.enableVerboseLogging 函数
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA35 代码分析

```

/packages/apps/Settings/src/com/android/settings/development/DevelopmentSettings.java


    private VerifyAppsOverUsbPreferenceController mVerifyAppsOverUsbController;
    private SwitchPreference mWifiDisplayCertification; // 无线显示认证
    private SwitchPreference mWifiVerboseLogging; // 启用 wiif详细日志记录功能
    private SwitchPreference mWifiAggressiveHandover;
    
    
    private static final String WIFI_DISPLAY_CERTIFICATION_KEY = "wifi_display_certification";   // 无线显示认证
    private static final String WIFI_VERBOSE_LOGGING_KEY = "wifi_verbose_logging";   // 启用 wiif详细日志记录功能
    private static final String WIFI_AGGRESSIVE_HANDOVER_KEY = "wifi_aggressive_handover";  // 主动从WLAN网络切换到移动数据网络
    private static final String WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY = "wifi_allow_scan_with_traffic";  //  始终允许WLAN漫游扫描
    
    
    
    
      mWifiVerboseLogging  = findAndInitSwitchPref(WIFI_VERBOSE_LOGGING_KEY);
    
    
    

    private void updateWifiVerboseLoggingOptions() {
        boolean enabled = mWifiManager.getVerboseLoggingLevel() > 0;
        updateSwitchPreference(mWifiVerboseLogging, enabled);
    }

    private void writeWifiVerboseLoggingOptions() {
        mWifiManager.enableVerboseLogging(mWifiVerboseLogging.isChecked() ? 1 : 0);  // 设置 Wifimanager 中的值
    }


/frameworks/base/wifi/java/android/net/wifi/WifiManager.java    // ▲8  UA分析完后 在 Framework分析
    public void enableVerboseLogging (int verbose) {
        try {
            mService.enableVerboseLogging(verbose);
        } catch (Exception e) {
            //ignore any failure here
            Log.e(TAG, "enableVerboseLogging " + e.toString());
        }
    }
    

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
    
else if (preference == mWifiVerboseLogging) {
            writeWifiVerboseLoggingOptions();
        }
    
        
```



## WIFI UA36分析 
###WIFI UA36 操作列   《系统》>《开发者选项》>《主动从WLAN网络切换到移动数据网络》
<img src="//../zimage/wireless/wifi/01_wifisetting/18.png" width = "25%" height="25%"/>
1. **UA编号**   UA35  
1. **UA说明**  **《系统》>《开发者选项》>《主动从WLAN网络切换到移动数据网络》的switch开关**
1. **UA触发函数**   WifiManager.enableAggressiveHandover
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA36 代码分析

```

/packages/apps/Settings/src/com/android/settings/development/DevelopmentSettings.java


    private VerifyAppsOverUsbPreferenceController mVerifyAppsOverUsbController;
    private SwitchPreference mWifiDisplayCertification; // 无线显示认证
    private SwitchPreference mWifiVerboseLogging; // 启用 wiif详细日志记录功能
    private SwitchPreference mWifiAggressiveHandover; // 主动从WLAN网络切换到移动数据网络
    
    
    private static final String WIFI_DISPLAY_CERTIFICATION_KEY = "wifi_display_certification";   // 无线显示认证
    private static final String WIFI_VERBOSE_LOGGING_KEY = "wifi_verbose_logging";   // 启用 wiif详细日志记录功能
    private static final String WIFI_AGGRESSIVE_HANDOVER_KEY = "wifi_aggressive_handover";  // 主动从WLAN网络切换到移动数据网络
    private static final String WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY = "wifi_allow_scan_with_traffic";  //  始终允许WLAN漫游扫描
    
    
    
    
      mWifiAggressiveHandover  = findAndInitSwitchPref(WIFI_AGGRESSIVE_HANDOVER_KEY);
    
    
    
    private void updateWifiAggressiveHandoverOptions() {
        boolean enabled = mWifiManager.getAggressiveHandover() > 0;
        updateSwitchPreference(mWifiAggressiveHandover, enabled);
    }

    private void writeWifiAggressiveHandoverOptions() {
        mWifiManager.enableAggressiveHandover(mWifiAggressiveHandover.isChecked() ? 1 : 0);  //  调用 WIFIManager的enableAggressiveHandover 方法 ▲9  UA分析完后 在 Framework分析
    }


/frameworks/base/wifi/java/android/net/wifi/WifiManager.java    // ▲8  UA分析完后 在 Framework分析
    public void enableVerboseLogging (int verbose) {
        try {
            mService.enableVerboseLogging(verbose);
        } catch (Exception e) {
            //ignore any failure here
            Log.e(TAG, "enableVerboseLogging " + e.toString());
        }
    }
    

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
    
else if (preference == mWifiVerboseLogging) {
            writeWifiVerboseLoggingOptions();
        }
    
        
```





## WIFI UA37分析 
###WIFI UA37 操作列   《系统》>《开发者选项》>《始终允许WLAN漫游扫描》
<img src="//../zimage/wireless/wifi/01_wifisetting/18.png" width = "25%" height="25%"/>
1. **UA编号**   UA35  
1. **UA说明**  **《系统》>《开发者选项》>《始终允许WLAN漫游扫描》的switch开关**
1. **UA触发函数**  
1. **UA字符可选值**    
1. **UA布局及ID**    
1. **代码Key**     
1. **代码可选值  **
1. **数据库Key **  
1. **数据库可选值 **   
1. **SP的Key**  
1. **SP可选值**  
1. **UA操作后UI显示类**   
1. **UA触发函数所在类**    
---



###WIFI UA37 代码分析

```

/packages/apps/Settings/src/com/android/settings/development/DevelopmentSettings.java


    private SwitchPreference mWifiDisplayCertification; // 无线显示认证
    private SwitchPreference mWifiVerboseLogging; // 启用 wiif详细日志记录功能
    private SwitchPreference mWifiAggressiveHandover; // 主动从WLAN网络切换到移动数据网络
    private SwitchPreference mWifiAllowScansWithTraffic;    //  始终允许WLAN漫游扫描
    
    private static final String WIFI_DISPLAY_CERTIFICATION_KEY = "wifi_display_certification";   // 无线显示认证
    private static final String WIFI_VERBOSE_LOGGING_KEY = "wifi_verbose_logging";   // 启用 wiif详细日志记录功能
    private static final String WIFI_AGGRESSIVE_HANDOVER_KEY = "wifi_aggressive_handover";  // 主动从WLAN网络切换到移动数据网络
    private static final String WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY = "wifi_allow_scan_with_traffic";  //  始终允许WLAN漫游扫描
    
    
    
    
mWifiAllowScansWithTraffic = findAndInitSwitchPref(WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY);
    

    private void updateWifiAllowScansWithTrafficOptions() {
        boolean enabled = mWifiManager.getAllowScansWithTraffic() > 0;
        updateSwitchPreference(mWifiAllowScansWithTraffic, enabled);
    }

    private void writeWifiAllowScansWithTrafficOptions() {
        mWifiManager.setAllowScansWithTraffic(mWifiAllowScansWithTraffic.isChecked() ? 1 : 0);  //  调用 WIFIManager的 setAllowScansWithTraffic 方法 ▲10  UA分析完后 在 Framework分析
    }
    


    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
    
else if (preference == mWifiAllowScansWithTraffic) {
            writeWifiAllowScansWithTrafficOptions();
        }
    
        
```


## WIFI UA38 分析

<img src="//../zimage/wireless/wifi/01_wifisetting/30.png" width = "25%" height="25%"/>
```
packages/apps/Settings/res/values/strings.xml#6730

<!-- [CHAR LIMIT=130] Preference title for Wi-Fi always scanning -->
<string name="location_scanning_wifi_always_scanning_title">Wi\u2011Fi scanning</string>


<!-- Preference description text for Wi-Fi always scanning -->
<string name="location_scanning_wifi_always_scanning_description">Allow apps and services to scan for Wi\u2011Fi networks at any time, even when Wi\u2011Fi is off. This can be used, for example, to improve location-based features and services.</string>



```

```
/packages/apps/Settings/res/xml/location_scanning.xml


<?xml version="1.0" encoding="utf-8"?>

<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
        android:title="@string/location_scanning_screen_title"
        android:key="scanning_screen">

        <SwitchPreference
            android:title="@string/location_scanning_wifi_always_scanning_title"
            android:summary="@string/location_scanning_wifi_always_scanning_description"
            android:defaultValue="true"
            android:key="wifi_always_scanning" />

        <SwitchPreference
            android:title="@string/location_scanning_bluetooth_always_scanning_title"
            android:summary="@string/location_scanning_bluetooth_always_scanning_description"
            android:defaultValue="true"
            android:key="bluetooth_always_scanning" />

</PreferenceScreen>




```


## WIFI UA39 分析

<img src="//../zimage/wireless/wifi/01_wifisetting/30.png" width = "25%" height="25%"/>
```
<!-- [CHAR LIMIT=130] Preference title for Wi-Fi always scanning -->
<string name="location_scanning_wifi_always_scanning_title">Wi\u2011Fi scanning</string>


<!-- Preference description text for Wi-Fi always scanning -->
<string name="location_scanning_wifi_always_scanning_description">Allow apps and services to scan for Wi\u2011Fi networks at any time, even when Wi\u2011Fi is off. This can be used, for example, to improve location-based features and services.</string>


```
