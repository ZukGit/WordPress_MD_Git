# 打开/关闭WIFI 流程

# /packages/apps/Settings/[■]
```
/packages/apps/Settings/ ：设置APP提供WIFI的操作UI

```
## /src/com/android/settings/wifi[■]

### WifiEnable
```
http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java


public class WifiEnabler implements SwitchWidgetController.OnSwitchChangeListener  {

 // 监听  android:key="toggle_wifi"   这个Preference的值的变化  然后调用 OnSwitchChangeListener接口实现方法  WifiEnabler.onSwitchToggled
// mSwitchWidget.setListener(this【 WifiEnabler 】);
    private final SwitchWidgetController mSwitchWidget; // WIFI开关按钮


```
#### onSwitchToggled 方法
```
    @Override
    public boolean onSwitchToggled(boolean isChecked) {
        //Do nothing if called as a result of a state machine event
        if (mStateMachineEvent) {
            return true;
        }
        // Show toast message if Wi-Fi is not allowed in airplane mode
        if (isChecked && !WirelessUtils.isRadioAllowed(mContext, Settings.Global.RADIO_WIFI)) {
            Toast.makeText(mContext, R.string.wifi_in_airplane_mode, Toast.LENGTH_SHORT).show();
            // Reset switch to off. No infinite check/listener loop.
            mSwitchWidget.setChecked(false);
            return false;
        }

        if (isChecked) {
            mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_ON);
        } else {
            // Log if user was connected at the time of switching off.
            mMetricsFeatureProvider.action(mContext, MetricsEvent.ACTION_WIFI_OFF,
                    mConnected.get());
        }
        if (!mWifiManager.setWifiEnabled(isChecked)) { // 【 1★ 】
            // Error
            mSwitchWidget.setEnabled(true);
            Toast.makeText(mContext, R.string.wifi_error, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}

```
##### RuntimeLog
```
12-25 11:02:32.478  7446  7446 I zukgit1 : java.lang.RuntimeException
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.wifi.WifiEnabler.onSwitchToggled(WifiEnabler.java:239)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBarController.onSwitchChanged(SwitchBarController.java:77)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBar.propagateChecked(SwitchBar.java:277)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBar.onCheckedChanged(SwitchBar.java:287)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.CompoundButton.setChecked(CompoundButton.java:171)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.Switch.setChecked(Switch.java:1083)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.ToggleSwitch.setChecked(ToggleSwitch.java:57)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.Switch.toggle(Switch.java:1078)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.CompoundButton.performClick(CompoundButton.java:132)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View.performClickInternal(View.java:6577)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View.access$3100(View.java:781)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View$PerformClick.run(View.java:25912)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Handler.handleCallback(Handler.java:873)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Handler.dispatchMessage(Handler.java:99)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Looper.loop(Looper.java:193)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.app.ActivityThread.main(ActivityThread.java:6923)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at java.lang.reflect.Method.invoke(Native Method)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:870)

```

####  network_and_internet.xml
```
WifiEnable 关联界面

WifiEnable : 是设置APP调用SDK中WIFI相关接口的类，
1.响应设置界面用户点击打开关闭WIFI事件逻辑处理
2.调用 mWifiManager 这个SDK的API来设置WIFI开关

 

 <com.android.settings.widget.MasterSwitchPreference  【network_and_internet.xml 中定义的MasterSwitchPreference 】
        android:fragment="com.android.settings.wifi.WifiSettings"
        android:key="toggle_wifi"
        <intent
            android:action="android.settings.WIFI_SETTINGS"
            android:targetClass="Settings$WifiSettingsActivity"/>
    </com.android.settings.widget.MasterSwitchPreference>



```




#  /frameworks/base/wifi [■]
## java/android/net/wifi/  [■]
###   WifiManager.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/base/wifi/java/android/net/wifi/WifiManager.java

public class WifiManager {

  IWifiManager mService;


    public WifiManager(Context context, IWifiManager service, Looper looper) {
        mContext = context;
        mService = service;
        mLooper = looper;
        mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
    }
}

.............


WifiManager跨进程服务的构建代码：

final class SystemServiceRegistry {

 static {
......

    private static final HashMap<Class<?>, String>            SYSTEM_SERVICE_NAMES = new HashMap<Class<?>, String>();
    private static final HashMap<String, ServiceFetcher<?>>   SYSTEM_SERVICE_FETCHERS =   new HashMap<String, ServiceFetcher<?>>();

    /**
     * Statically registers a system service with the context.  【静态注册系统服务 添加到 Map 集合中】
     * This method must be called during static initialization only.
     */
    private static <T> void registerService(String serviceName, Class<T> serviceClass, ServiceFetcher<T> serviceFetcher) {
        SYSTEM_SERVICE_NAMES.put(serviceClass, serviceName);
        SYSTEM_SERVICE_FETCHERS.put(serviceName, serviceFetcher);
    }



        registerService(Context.WIFI_SERVICE, WifiManager.class,
                new CachedServiceFetcher<WifiManager>() {
            @Override
            public WifiManager createService(ContextImpl ctx) throws ServiceNotFoundException {
                IBinder b = ServiceManager.getServiceOrThrow(Context.WIFI_SERVICE 【 "wifi" 】 );
                IWifiManager service = IWifiManager.Stub.asInterface(b);
                return new WifiManager(ctx.getOuterContext(), service, ConnectivityThread.getInstanceLooper());
            }});

......
}

```

#### setWifiEnabled 方法
```

    public boolean setWifiEnabled(boolean enabled) {
        try {
            return mService.setWifiEnabled(mContext.getOpPackageName(), enabled);  【★】 // 客户端线程在此阻塞  等待 跨进程的服务端线程返回后，继续执行
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

```



### IWifiManager.aidl 
```
http://androidxref.com/9.0.0_r3/xref/frameworks/base/wifi/java/android/net/wifi/IWifiManager.aidl

IWifiManager.aidl 接口 定义： 

interface IWifiManager
{
...
boolean setWifiEnabled(String packageName, boolean enable);
...
}


当你通过aidl去访问服务端时，默认客户端会阻塞在proxy,【服务端处理完后，通知proxy返回然后继续在客户端执行】
但是如果在 aidl 接口中方法使用 oneway进行修饰的话,那么【客户端调用oneway接口不会阻塞，并且继续执行】


adb shell serive list | findstr   wifi     
64  wifi: [android.met.wifi.IWifiManager]      // 查询提供系统提供的跨进程服务API (这样的服务一般都实现了aidl接口)

```


#### IWifiManager.Stub 实现类 WifiServiceImpl
```
IWifiManager.aidl 接口 的实现： 

public class WifiServiceImpl extends IWifiManager.Stub  【 2★ 】{   // 跳转到 WifiServiceImpl.java
xxxxx
}
```



#   /frameworks/opt/net/wifi [■]
##  /service/java/com/android/server/wifi/ [■]
  
### WifiServiceImpl.java  
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java


WifiServiceImpl :  运行进程  PID=617
1. 作为 IWifiManager.aidl 接口的实现类， 运行在系统进程中  
2. 使用命令可查看在SystemService注册的wifi服务  adb shell serive list | findstr   wifi     
      输出:    64  wifi: [android.met.wifi.IWifiManager]        // 该服务API的跨进程实现类
3. 使用 ps -A | grep servicemanager  可查看  ServiceManager注册的服务 包括  WifiServiceImpl 所运行的进程

ps -A | grep servicemanager
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system         657     1   11620   2788 binder_thread_read  0 S servicemanager
system         658     1 2127156   3668 binder_thread_read  0 S hwservicemanager
system         659     1   12332   2548 binder_thread_read  0 S vndservicemanager



public class WifiServiceImpl extends IWifiManager.Stub {




```
#### setWifiEnabled 方法
```


    /**
     * see {@link android.net.wifi.WifiManager#setWifiEnabled(boolean)}
     * @param enable {@code true} to enable, {@code false} to disable.
     * @return {@code true} if the enable/disable operation was
     *         started or is already in the queue.
     */
    @Override
    public synchronized boolean setWifiEnabled(String packageName, boolean enable) throws RemoteException {
        if (enforceChangePermission(packageName) != MODE_ALLOWED) {
            return false;
        }

        Slog.d(TAG, "setWifiEnabled: " + enable + " pid=" + Binder.getCallingPid()    + ", uid=" + Binder.getCallingUid() + ", package=" + packageName);
        mLog.info("setWifiEnabled package=% uid=% enable=%").c(packageName).c(Binder.getCallingUid()).c(enable).flush();

        boolean isFromSettings = checkNetworkSettingsPermission(Binder.getCallingPid(), Binder.getCallingUid());

        // If Airplane mode is enabled, only Settings is allowed to toggle Wifi
        if (mSettingsStore.isAirplaneModeOn() && !isFromSettings) {  //  如果打开了飞行模式 或者 热点 , 那么只有系统设置APK 来操作 WIFI的开关 其余APK没有权限
            mLog.info("setWifiEnabled in Airplane mode: only Settings can enable wifi").flush();
            return false;
        }

        // If SoftAp is enabled, only Settings is allowed to toggle wifi
        boolean apEnabled = mWifiApState == WifiManager.WIFI_AP_STATE_ENABLED;

        if (apEnabled && !isFromSettings) {
            mLog.info("setWifiEnabled SoftAp not disabled: only Settings can enable wifi").flush();
            return false;
        }

        /*
        * Caller might not have WRITE_SECURE_SETTINGS,
        * only CHANGE_WIFI_STATE is enforced
        */
        long ident = Binder.clearCallingIdentity();
        try {
            if (! mSettingsStore.handleWifiToggled(enable)) {
                // Nothing to do if wifi cannot be toggled
                return true;
            }
        } finally {
            Binder.restoreCallingIdentity(ident);
        }


        if (mPermissionReviewRequired) { // APK没有打开权限需要请求权限的情况
            final int wiFiEnabledState = getWifiEnabledState();
            if (enable) {
                if (wiFiEnabledState == WifiManager.WIFI_STATE_DISABLING
                        || wiFiEnabledState == WifiManager.WIFI_STATE_DISABLED) {
                    if (startConsentUi(packageName, Binder.getCallingUid(),
                            WifiManager.ACTION_REQUEST_ENABLE)) {
                        return true;
                    }
                }
            } else if (wiFiEnabledState == WifiManager.WIFI_STATE_ENABLING
                    || wiFiEnabledState == WifiManager.WIFI_STATE_ENABLED) {
                if (startConsentUi(packageName, Binder.getCallingUid(),
                        WifiManager.ACTION_REQUEST_DISABLE)) {
                    return true;
                }
            }
        }
// 发送打开CMD_WIFI_TOGGLED 给 WifiController 继续往下执行， ?奇怪  为什么没有传递 enable 这个标识打开还没关闭的flag
        mWifiController.sendMessage(CMD_WIFI_TOGGLED);     // 【★】
        return true;
    }

}



```



### WifiController.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiController.java


public class WifiController extends StateMachine {   // WifiController 状态机_1   

StateMachine 起到Common Code的作用  具体分析该类 请移步到该类查看



```
#### handleMessage(CMD_WIFI_TOGGLED) 方法
```
WifiController 是 wifi功能在服务端的实现类， 为 WifiServiceImpl 提供 API 调用 , 有状态机机制


public class WifiController extends StateMachine {


}


/frameworks/base/core/java/com/android/internal/util/StateMachine.java


 private static class SmHandler extends Handler {
.....  处理消息  handleMessage(CMD_WIFI_TOGGLED) 
}


}



```



##### StateInfo[] mStateStack 状态树以及数据的决定
```
WifiController.StateMachine.SmHandler.StateInfo[] mStateStack 状态数组的初始化 (确定最大长度)


   private static class SmHandler extends Handler {

        /** The map of all of the states in the state machine */ 
        // State 是和 StateMachine 同目录独立目录
        //  StateInfo 是内部类 SmHandler的内部类 
        private HashMap<State, StateInfo> mStateInfo = new HashMap<State, StateInfo>();



        /**
         * Complete the construction of the state machine.
         */
        private final void completeConstruction() {
            if (mDbg) mSm.log("completeConstruction: E");

            /**
             * Determine the maximum depth of the state hierarchy so we can allocate the state stacks.
             */
            int maxDepth = 0;
            for (StateInfo si : mStateInfo.values()) {  //  去搜索所有的状态信息对应的状态栈  找到纵深最大的那个 作为数组的长度
                int depth = 0;
                for (StateInfo i = si; i != null; depth++) {
                    i = i.parentStateInfo;
                }
                if (maxDepth < depth) {
                    maxDepth = depth;   //  判断是否最大纵深 
                }
            }
            if (mDbg) mSm.log("completeConstruction: maxDepth=" + maxDepth);

            mStateStack = new StateInfo[maxDepth];
            mTempStateStack = new StateInfo[maxDepth];
            setupInitialStateStack();

            /** Sending SM_INIT_CMD message to invoke enter methods asynchronously */
            sendMessageAtFrontOfQueue(obtainMessage(SM_INIT_CMD, mSmHandlerObj));

            if (mDbg) mSm.log("completeConstruction: X");
        }


}


        private class StateInfo {
            /** The state */
            State state; // 当前的状态

            /** The parent of this state, null if there is no parent */
            StateInfo parentStateInfo;   // 当前状态的父状态  栈下面的一个状态

            /** True when the state has been entered and on the stack */
            boolean active;

            /**
             * Convert StateInfo to string
             */
            @Override
            public String toString() {
                return "state=" + state.getName() + ",active=" + active + ",parent="
                        + ((parentStateInfo == null) ? "null" : parentStateInfo.state.getName());
            }
        }

```

```
State的分析 (  StateMachine 与同目录独立文件   )： 
State 提供了 各种实现 IState 接口的 空的实现， 具体的实现 交给各个具体继承于 State的子类去实现
/frameworks/base/core/java/com/android/internal/util/StateMachine.java
/frameworks/base/core/java/com/android/internal/util/State.java

State的空实现
public class State implements IState {
    protected State() {  // State状态的空的构造器
    }

    @Override
    public void enter() {  // 进入State 方法时 执行的方法
    }

    @Override
    public void exit() {  // 离开State 方法时 执行的 方法 
    }



// ★  该方法标识 当前的状态是否处理消息，如果处理不了返回 false 那么会往状态栈下一个转态去请求处理
//  如果返回 true ， 那么说明当前的消息被我这个State处理了，那么这个消息的处理到此为止 不继续往下传递消息了
    @Override
    public boolean processMessage(Message msg) {  
        return false;
    }

 
    @Override
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf('$');
        return name.substring(lastDollar + 1);
    }


}

```


```
WifiController.StateMachine.SmHandler.HashMap<State, StateInfo> mStateInfo     状态Map数据 来源填充分析

public class StateMachine {

   private static class SmHandler extends Handler {


        private HashMap<State, StateInfo> mStateInfo = new HashMap<State, StateInfo>();


        状态是通过函数 addState 添加到map , 并且该函数需要满足 子State,父State  来完成 状态的添加
        private final StateInfo addState(State state, State parent) {  
            if (mDbg) {
                mSm.log("addStateInternal: E state=" + state.getName() + ",parent="  + ((parent == null) ? "" : parent.getName()));
            }
            StateInfo parentStateInfo = null;
            if (parent != null) {
                parentStateInfo = mStateInfo.get(parent);
                if (parentStateInfo == null) { // 如果查找到当前的父类的父类(爷爷类)为空, 不管，先把父类弄到Maps集合中
                    // Recursively add our parent as it's not been added yet.
                    parentStateInfo = addState(parent, null);   
                }
            }

 // 如果从  mStateInfo 依据 State 取出的 StateINfo 为空 ， 那么说明就需要新建 StateInfo 加入Map
            StateInfo stateInfo = mStateInfo.get(state);
            if (stateInfo == null) {
                stateInfo = new StateInfo();
                mStateInfo.put(state, stateInfo);  //   新建 StateInfo ，并与State成对的 加入Map集合
            }

            // Validate that we aren't adding the same state in two different hierarchies.
            if ((stateInfo.parentStateInfo != null) && (stateInfo.parentStateInfo != parentStateInfo)) {
                throw new RuntimeException("state already added");
            }
            stateInfo.state = state;
            stateInfo.parentStateInfo = parentStateInfo;
            stateInfo.active = false;
            if (mDbg) mSm.log("addStateInternal: X stateInfo: " + stateInfo);
            return stateInfo;
        }


        /**
         * State entered when transitionToHaltingState is called.
         */
        private class HaltingState extends State {
            @Override
            public boolean processMessage(Message msg) {
                mSm.haltedProcessMessage(msg);
                return true;
            }
        }

        /**
         * State entered when a valid quit message is handled.
         */
        private class QuittingState extends State {
            @Override
            public boolean processMessage(Message msg) {
                return NOT_HANDLED;
            }
        }

        /** State used when state machine is halted停下来 */
        private HaltingState mHaltingState = new HaltingState();

        /** State used when state machine is quitting */
        private QuittingState mQuittingState = new QuittingState();
-----------------------------------------------------------
        private SmHandler(Looper looper, StateMachine sm) {  // 在 SmHandler 的构造器中 添加了 两个状态 HaltingState 和 QuittingState 2 个状态
            super(looper);
            mSm = sm;

            addState(mHaltingState, null);
            addState(mQuittingState, null);
        }


}



  public final void 【StateMachine】addState(State state, State parent) {  // StateMachine 提供了往内部类SmHandler 添加状态的接口
        mSmHandler.addState(state, parent);
    }


-----------------------------------------------------------

在  WifiController  的 构造器中  添加了  6 个状态

 WifiController(Context context, WifiStateMachine wsm, Looper wifiStateMachineLooper,
                   WifiSettingsStore wss, Looper wifiServiceLooper, FrameworkFacade f,
                   WifiStateMachinePrime wsmp) {

        // CHECKSTYLE:OFF IndentationCheck
        addState(mDefaultState);
            addState(mStaDisabledState, mDefaultState);
            addState(mStaEnabledState, mDefaultState);
                addState(mDeviceActiveState, mStaEnabledState);
            addState(mStaDisabledWithScanState, mDefaultState);
            addState(mEcmState, mDefaultState);
        // CHECKSTYLE:ON IndentationCheck

}


-----------------------------------------------------------  组成的 WifiController 状态集合如下
由下图可知在 WifiController 的状态栈如下 ， 由 8 个状态组成，纵深最大为 3  , 即 WifiController.StateMachine.SmHandler.StateInfo[] mStateStack 大小为3 

                                                 mDeviceActiveState        【ChildLawer_3】
                      mStaDisabledWithScanState  mStaEnabledState  mStaDisabledState       mEcmState    【ChildLawer_2】
 HaltingState         ___________________________mDefaultState【FatherLawer_1】_______________________________________    QuittingState
```
<img src="//../zimage/wireless/wifi/04_wifi_open_close/1.jpg"/>  



#### StateMachine.SmHandler.performTransitions(msgProcessedState, msg)
```
performTransitions 函数用于完成状态栈的切换

public class StateMachine {

   private static class SmHandler extends Handler {

  public final void handleMessage(Message msg) {
.....
if (mIsConstructionCompleted || (mMsg.what == SM_QUIT_CMD)) {
msgProcessedState = processMsg(msg);
} 
performTransitions(msgProcessedState, msg);  // msgProcessedState的processMsg返回true 说明这个状态需要处理这个消息  那么完成状态的切换 
.....
}


}
}


```

```
performTransitions 函数的详细分析



```
```




```

```



```


#### StateMachine.SmHandler.onPostHandleMessage(msg)



### WifiStateMachinePrime.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachinePrime.java

  private ModeStateMachine mModeStateMachine ;

    WifiStateMachinePrime(WifiInjector wifiInjector,
                          Context context,
                          Looper looper,
                          WifiNative wifiNative,
                          DefaultModeManager defaultModeManager,
                          IBatteryStats batteryStats) {
        mWifiInjector = wifiInjector;
        mContext = context;
        mLooper = looper;
        mHandler = new Handler(looper);
        mWifiNative = wifiNative;
        mActiveModeManagers = new ArraySet();
        mDefaultModeManager = defaultModeManager;
        mBatteryStats = batteryStats;
        mSelfRecovery = mWifiInjector.getSelfRecovery();
        mWifiDiagnostics = mWifiInjector.getWifiDiagnostics();
        mScanRequestProxy = mWifiInjector.getScanRequestProxy();
        mModeStateMachine = new ModeStateMachine();
        mWifiNativeStatusListener = new WifiNativeStatusListener();
        mWifiNative.registerStatusListener(mWifiNativeStatusListener);
    }


```
#### disableWifi() 
```
    /**
     * Method to disable wifi in sta/client mode scenarios.
     * This mode will stop any client/scan modes and will not perform any network scans.
     */
    public void disableWifi() {
        changeMode(ModeStateMachine.CMD_DISABLE_WIFI);
    }


```

#### disableWifi() 
```

    /**
     * Method to switch wifi into client mode where connections to configured networks will be  attempted.
     */
    public void enterClientMode() {
        changeMode(ModeStateMachine.CMD_START_CLIENT_MODE);
    }


```
#### changeMode() 
```
    private void changeMode(int newMode) {
        mModeStateMachine.sendMessage(newMode);
    }
```

####  ModeStateMachine  内部类
```
 private class ModeStateMachine extends StateMachine {
           addState(mClientModeActiveState);
            addState(mScanOnlyModeActiveState);
            addState(mWifiDisabledState);
}



```

##### class ModeActiveState extends State 
```


```
###### enter() 方法
```
           public void enter() {
                Log.d(TAG, "Entering WifiDisabledState");
                mDefaultModeManager.sendScanAvailableBroadcast(mContext, false);
                mScanRequestProxy.enableScanningForHiddenNetworks(false);
                mScanRequestProxy.clearScanResults();
            }

```

##### class WifiDisabledState extends ModeActiveState
##### class ClientModeActiveState extends ModeActiveState 
```
ClientListener mListener;
WifiInjector mWifiInjector;
ActiveModeManager mManager;

```
###### enter() 方法
```
            @Override
            public void enter() {
                Log.d(TAG, "Entering ClientModeActiveState");

                mListener = new ClientListener();
                mManager = mWifiInjector.makeClientModeManager(mListener);
                mManager.start();
                mActiveModeManagers.add(mManager);

            }

```
##### class ScanOnlyModeActiveState extends ModeActiveState
```



```

### WifiInjector.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiInjector.java



```
#### makeClientModeManager() 方法
```

public class ClientModeManager implements ActiveModeManager {

    public ClientModeManager makeClientModeManager(ClientModeManager.Listener listener) {
        return new ClientModeManager(mContext, mWifiStateMachineHandlerThread.getLooper(),
                mWifiNative, listener, mWifiMetrics, mScanRequestProxy, mWifiStateMachine);
    }


```
###  ClientModeManager.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ClientModeManager.java

 private final ClientModeStateMachine mStateMachine;

```

#### start() 函数
````

    /**
     * Start client mode.
     */
    public void start() {
        mStateMachine.sendMessage(ClientModeStateMachine.CMD_START);
    }


````

#### ClientModeStateMachine.java 内部类
```
    private class ClientModeStateMachine extends StateMachine {


```
#####  class IdleState extends State 
#####  class StartedState extends State 
```
private final ScanRequestProxy mScanRequestProxy;
private final WifiNative mWifiNative;
```
###### enter() 方法
```
           @Override
            public void enter() {
                Log.d(TAG, "entering StartedState");
                mIfaceIsUp = false;
                onUpChanged(mWifiNative.isInterfaceUp(mClientInterfaceName));
                mScanRequestProxy.enableScanningForHiddenNetworks(true);
            }

```



###### onUpChanged() 方法
```

            private void onUpChanged(boolean isUp) {
                if (isUp == mIfaceIsUp) {
                    return;  // no change
                }
                mIfaceIsUp = isUp;
                if (isUp) {
                    Log.d(TAG, "Wifi is ready to use for client mode");
                    sendScanAvailableBroadcast(true);
                    mWifiStateMachine.setOperationalMode(WifiStateMachine.CONNECT_MODE, mClientInterfaceName);
                    updateWifiState(WifiManager.WIFI_STATE_ENABLED,
                                    WifiManager.WIFI_STATE_ENABLING);
                } else {
                    if (mWifiStateMachine.isConnectedMacRandomizationEnabled()) {
                        // Handle the error case where our underlying interface went down if we
                        // do not have mac randomization enabled (b/72459123).
                        return;
                    }
                    // if the interface goes down we should exit and go back to idle state.
                    Log.d(TAG, "interface down!");
                    updateWifiState(WifiManager.WIFI_STATE_UNKNOWN, WifiManager.WIFI_STATE_ENABLED);
                    mStateMachine.sendMessage(CMD_INTERFACE_DOWN);
                }
            }


```

######  processMessage 方法
```
            @Override
            public boolean processMessage(Message message) {
                switch(message.what) {
                    case CMD_START:
                        // Already started, ignore this command.
                        break;
                    case CMD_INTERFACE_DOWN:
                        Log.e(TAG, "Detected an interface down, reporting failure to SelfRecovery");
                        mWifiStateMachine.failureDetected(SelfRecovery.REASON_STA_IFACE_DOWN);

                        updateWifiState(WifiManager.WIFI_STATE_DISABLING, WifiManager.WIFI_STATE_UNKNOWN);
                        transitionTo(mIdleState);
                        break;
                    case CMD_INTERFACE_STATUS_CHANGED:
                        boolean isUp = message.arg1 == 1;
                        onUpChanged(isUp);
                        break;
                    case CMD_INTERFACE_DESTROYED:
                        Log.d(TAG, "interface destroyed - client mode stopping");

                        updateWifiState(WifiManager.WIFI_STATE_DISABLING,  WifiManager.WIFI_STATE_ENABLED);
                        mClientInterfaceName = null;
                        transitionTo(mIdleState);
                        break;
                    default:
                        return NOT_HANDLED;
                }
                return HANDLED;
            }

```

### WifiNative.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiNative.java

```
#### isInterfaceUp()  方法
```

    /**
     *
     * Check if the interface is up or down.
     *
     * @param ifaceName Name of the interface.
     * @return true if iface is up, false if it's down or on error.
     */
    public boolean isInterfaceUp(@NonNull String ifaceName) {
        synchronized (mLock) {
            final Iface iface = mIfaceMgr.getIface(ifaceName);
            if (iface == null) {
                Log.e(TAG, "Trying to get iface state on invalid iface=" + ifaceName);
                return false;
            }
            InterfaceConfiguration config = null;
            try {
                config = mNwManagementService.getInterfaceConfig(ifaceName);
            } catch (RemoteException e) {
            }
            if (config == null) {
                return false;
            }
            return config.isUp();
        }
    }

```

###  ActiveModeManager.java 
```
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/ActiveModeManager.java

public interface ActiveModeManager {
    String TAG = "ActiveModeManager";

    /**
     * Method used to start the Manager for a given Wifi operational mode.
     */
    void start();

    /**
     * Method used to stop the Manager for a give Wifi operational mode.
     */
    void stop();

    /**
     * Method to dump for logging state.
     */
    void dump(FileDescriptor fd, PrintWriter pw, String[] args);

    /**
     * Method that allows Mode Managers to update WifiScanner about the current state.
     * @param context Context to use for the notification
     * @param available boolean indicating if scanning is available
     */
    default void sendScanAvailableBroadcast(Context context, boolean available) {
        Log.d(TAG, "sending scan available broadcast: " + available);
        final Intent intent = new Intent(WifiManager.WIFI_SCAN_AVAILABLE);
        intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
        if (available) {
            intent.putExtra(WifiManager.EXTRA_SCAN_AVAILABLE, WifiManager.WIFI_STATE_ENABLED);
        } else {
            intent.putExtra(WifiManager.EXTRA_SCAN_AVAILABLE, WifiManager.WIFI_STATE_DISABLED);
        }
        context.sendStickyBroadcastAsUser(intent, UserHandle.ALL);
    }

```

#  /frameworks/base/core [■]
## /java/android/net/[■]
## java/com/android/internal/util/
### StateMachine.java
```
http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/com/android/internal/util/StateMachine.java



StateMachine  [WifiController 的父类] 实现的 状态机基本方法:

状态转换栈示意图
          mP0
         /   \
        mP1   mS0
       /   \
      mS2   mS1
     /  \    \
    mS3  mS4  mS5  ---&gt; initial state


public class StateMachine {

 private SmHandler mSmHandler;  // 状态机中处理消息的Handler  是一个extends Handler 自定义Handler

```
#### sendMessage() 方法
```

    /**
     * Enqueue a message to this state machine.
     * Message is ignored if state machine has quit.
     */
    public void sendMessage(int what) {
        // mSmHandler can be null if the state machine has quit.
        SmHandler smh = mSmHandler;
        if (smh == null) return;

        smh.sendMessage(obtainMessage(what));
    }



    /**
     * Enqueue a message to this state machine.
     * Message is ignored if state machine has quit.
     */
    public void sendMessage(Message msg) {
        // mSmHandler can be null if the state machine has quit.
        SmHandler smh = mSmHandler;
        if (smh == null) return;

        smh.sendMessage(msg 【Message(CMD_WIFI_TOGGLED) ★】);  // 将会执行到 SmHandler.handleMessage() 方法中
    }


```

#### SmHandler 内部类
```

public class StateMachine {
 private static class SmHandler extends Handler {

       // 在 completeConstruction() 方法完成计算 数组大小maxDepth 并初始化数组  
        private StateInfo mStateStack[];  //  填充转态信息的数组  它的大小固定 但是是通过动态计算得到的  /** Stack used to manage the current hierarchy of states */
  
        private int mStateStackTopIndex = -1;  // 标记当状态栈中有多少个状态  与 top_index 一一对应        /** Top of mStateStack */



```
#####  handleMessage() 方法
```

        /**
         * Handle messages sent to the state machine by calling
         * the current state's processMessage. It also handles
         * the enter/exit calls and placing any deferred messages
         * back onto the queue when transitioning to a new state.
         */
        @Override
        public final void handleMessage(Message msg) {

                if (mSm != null && msg.what != SM_INIT_CMD && msg.what != SM_QUIT_CMD) {
                    mSm.onPreHandleMessage(msg);
                }

                if (mDbg) mSm.log("handleMessage: E msg.what=" + msg.what);  // 打印wificontroller 状态机接收到的消息

                /** Save the current message */
                mMsg = msg;

                /** State that processed the message */
                State msgProcessedState = null;
                if (mIsConstructionCompleted || (mMsg.what == SM_QUIT_CMD)) {  
                    /** Normal path */  // 如果 wifiControl的完成初始化标识 mIsConstructionCompleted 为true 或者消息为 SM_QUIT_CMD 停止
                    msgProcessedState = processMsg(msg);    //【★】 把消息放入 process 函数进行处理
                } else if (!mIsConstructionCompleted && (mMsg.what == SM_INIT_CMD) && (mMsg.obj == mSmHandlerObj)) { 
                    /** Initial one time path. */ // 完成标识为false 待处理消息为 SM_INIT_CMD 初始化时
                    mIsConstructionCompleted = true;
                    invokeEnterMethods(0);
                } else {
                    throw new RuntimeException("StateMachine.handleMessage: "  + "The start method not called, received msg: " + msg);
                }
                performTransitions(msgProcessedState, msg); //【★】

                // We need to check if mSm == null here as we could be quitting.
                if (mDbg && mSm != null) mSm.log("handleMessage: X");

                if (mSm != null && msg.what != SM_INIT_CMD && msg.what != SM_QUIT_CMD) {
                    mSm.onPostHandleMessage(msg);  //【★】
                }
        }


}

```

##### processMsg(msg) 方法
```

       private final State processMsg(Message msg) {
            StateInfo curStateInfo = mStateStack[mStateStackTopIndex];  // 1. 取出当前状态StateInfo数组(长度为3) 中 位于最顶层的状态
            if (mDbg) {
                mSm.log("processMsg: " + curStateInfo.state.getName());
            }

            if (isQuit(msg)) {  //如果处理的消息是  SM_QUIT_CMD  那么切换状态到 QuittingState 停止状态
                transitionTo(mQuittingState);
            } else {
                while (!curStateInfo.state.processMessage(msg)) {  ★
 //2. 从StateInfo 取出 State 并调用 这个State的ProcessMessage 传递Message,
// 如果状态返回false 即不处理 那么转到状态栈的一下层状态处理  最后的父类一般都是 DefaultState
                    /**
                     * Not processed
                     */
                    curStateInfo = curStateInfo.parentStateInfo;  //3. ★ 往下切换当前状态信息为父类信息 
                    if (curStateInfo == null) {
                        /** 如果父类StateInfo为空 那么说明状态遍历完毕 那么这个消息处理不了 跳出循环
                         * No parents left so it's not handled
                         */
                        mSm.unhandledMessage(msg);
                        break;
                    }
                    if (mDbg) {
                        mSm.log("processMsg: " + curStateInfo.state.getName());  
                    }
                }
            }

//4. ★ 如果跳出了while循环 那么说明有消息被状态处理了,那么返回这个处理消息的状态
            return (curStateInfo != null) ? curStateInfo.state : null;   
        }

}




      private final boolean isQuit(Message msg) {
            return (msg.what == SM_QUIT_CMD) && (msg.obj == mSmHandlerObj);
        }

```

##### performTransitions(msgProcessedState,msg) 方法
```

        private void performTransitions(State msgProcessedState, Message msg) {

            State orgState = mStateStack[mStateStackTopIndex].state;  // 拿到当前状态栈最顶层的转态

            State destState = mDestState;  //  需要把当前栈顶切换到的 目的状态    ▲
            if (destState != null) {
                while (true) {

                    /**
                     * Determine the states to exit and enter and return the
                     * common ancestor state of the enter/exit states. Then
                     * invoke the exit methods then the enter methods.
                     */

                    //★  查找  目的 DesState转态为栈顶  那么这个栈中 状态标识为 StateInfo.active 为 true的那个转态 ,
                    // 如果有  说明 当前的 srcState 所形成的的栈 中  和 DesState转态为栈顶形成的栈  有 相重合的 状态栈 
                    // 那么切换到 DesState 那么就必须先切换到 这个 CommonState 
                    StateInfo commonStateInfo = setupTempStateStackWithStatesToEnter(destState);

                    // flag is cleared in invokeEnterMethods before entering the target state
                    mTransitionInProgress = true;

                    invokeExitMethods(commonStateInfo); // 该方法表示 在当前状态栈离开  知道栈顶状态是 commonStateInfo
                    int stateStackEnteringIndex = moveTempStateStackToStateStack();
                    invokeEnterMethods(stateStackEnteringIndex);

                    /**
                     * Since we have transitioned to a new state we need to have
                     * any deferred messages moved to the front of the message queue
                     * so they will be processed before any other messages in the
                     * message queue.
                     */
                    moveDeferredMessageAtFrontOfQueue();

                    if (destState != mDestState) {
                        // A new mDestState so continue looping
                        destState = mDestState;
                    } else {
                        // No change in mDestState so we're done
                        break;
                    }
                }
                mDestState = null;
            }

            /**
             * After processing all transitions check and
             * see if the last transition was to quit or halt.
             */
            if (destState != null) {
                if (destState == mQuittingState) {
                    /**
                     * Call onQuitting to let subclasses cleanup.
                     */
                    mSm.onQuitting();
                    cleanupAfterQuitting();
                } else if (destState == mHaltingState) {
                    /**
                     * Call onHalting() if we've transitioned to the halting
                     * state. All subsequent messages will be processed in
                     * in the halting state which invokes haltedProcessMessage(msg);
                     */
                    mSm.onHalting();
                }
            }
        }


```
##### invokeExitMethods() 方法
```
invokeExitMethods  该方法表示 在当前状态栈离开  直到栈顶状态是 commonStateInfo

        /**
         * Call the exit method for each state from the top of stack
         * up to the common ancestor state.
         */
        private final void invokeExitMethods(StateInfo commonStateInfo) {
            while ((mStateStackTopIndex >= 0)
                    && (mStateStack[mStateStackTopIndex] != commonStateInfo)) {
                State curState = mStateStack[mStateStackTopIndex].state;
                if (mDbg) mSm.log("invokeExitMethods: " + curState.getName());
                curState.exit();
                mStateStack[mStateStackTopIndex].active = false;
                mStateStackTopIndex -= 1;
            }
        }


```

##### setupTempStateStackWithStatesToEnter()方法
```

       //★  查找  目的 DesState转态为栈顶  那么这个栈中 状态标识为 StateInfo.active 为 true的那个转态 ,
        // 如果有  说明 当前的 srcState 所形成的的栈 中  和 DesState转态为栈顶形成的栈  有 相重合的 状态栈 
        // 那么切换到 DesState 那么就必须先切换到 这个 CommonState 

        private final StateInfo setupTempStateStackWithStatesToEnter(State destState) {
            /**
             * Search up the parent list of the destination state for an active
             * state. Use a do while() loop as the destState must always be entered
             * even if it is active. This can happen if we are exiting/entering
             * the current state.
             */
            mTempStateStackCount = 0;
            StateInfo curStateInfo = mStateInfo.get(destState);
            do {
                mTempStateStack[mTempStateStackCount++] = curStateInfo;
                curStateInfo = curStateInfo.parentStateInfo;
            } while ((curStateInfo != null) && !curStateInfo.active); ★ 这个active 标识位 很关键

            if (mDbg) {
                mSm.log("setupTempStateStackWithStatesToEnter: X mTempStateStackCount="
                        + mTempStateStackCount + ",curStateInfo: " + curStateInfo);
            }
            return curStateInfo;
        }


```

#### StateInfo.java  内部类
```
http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/com/android/internal/util/StateMachine.java#726


        private class StateInfo {
            State state;    /** The state */
            StateInfo parentStateInfo;   /** The parent of this state, null if there is no parent */
            boolean active;  /** True when the state has been entered and on the stack */

            public String toString() {
                return "state=" + state.getName() + ",active=" + active + ",parent="
                        + ((parentStateInfo == null) ? "null" : parentStateInfo.state.getName());
            }
        }



```

## /java/android/hardware/display/[■]





#  /frameworks/base/packages/SettingsLib [■]
## /src/com/android/settingslib/wifi/ [■]


# /frameworks/base/packages/SystemUI [■]
## /src/com/android/systemui/statusbar/policy/ [■]


# /frameworks/base/packages/CaptivePortalLogin [■]


#  /external/wpa_supplicant_8/ [■]



#  /hardware/qcom/wlan/qcwcn/wcnss-service/ [■]



# 代码逻辑

```



1.  如果打开了飞行模式 或者 热点 , 那么只有系统设置APK 来操作 WIFI的开关 其余APK没有权限 ---WifiServiceImpl.setWifiEnabled
2.  if (mDbg) mSm.log("handleMessage: E msg.what=" + msg.what);  // 打印wificontroller extends StateMachine 状态机接收到的消息  ---  Wificontroller.StateMachine.SmHandler.handleMessage
3.   mSm.log("processMsg: " + curStateInfo.state.getName()); // 打印wificontroller 处理消息的状态 从栈顶到栈底  ---------- Wificontroller.StateMachine.SmHandler.processMsg
```