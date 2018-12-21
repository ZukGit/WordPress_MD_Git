# 文件
## frameworks/base/packages/SettingsLib/res/values/
```
<string name="no_internet_access_text">This network has no internet access. Stay connected?</string>

<string name="wifi_connected_no_internet" >"已连接，但无法访问互联网"</string> 
<!-- Summary for Connected wifi network without internet --> 
<string name="wifi_connected_no_internet">Connected, no internet</string>
```


## WifiSettings.java
### updateAccessPointPreferences()

```
    private void updateAccessPointPreferences() {
        // in case state has changed
        if (!mWifiManager.isWifiEnabled()) {
            return;
        }
        // AccessPoints are sorted by the WifiTracker
        final List<AccessPoint> accessPoints = mWifiTracker.getAccessPoints();
        if (isVerboseLoggingEnabled()) {
            Log.i(TAG, "updateAccessPoints called for: " + accessPoints);
        }

        boolean hasAvailableAccessPoints = false;
        mAccessPointsPreferenceCategory.removePreference(mStatusMessagePreference);
        cacheRemoveAllPrefs(mAccessPointsPreferenceCategory);

        int index =
                configureConnectedAccessPointPreferenceCategory(accessPoints) ? 1 : 0;
        int numAccessPoints = accessPoints.size();
        for (; index < numAccessPoints; index++) {
            AccessPoint accessPoint = accessPoints.get(index);
            // Ignore access points that are out of range.
            if (accessPoint.isReachable()) {
                String key = accessPoint.getKey();
                hasAvailableAccessPoints = true;
                LongPressAccessPointPreference pref =
                        (LongPressAccessPointPreference) getCachedPreference(key);
                if (pref != null) {
                    pref.setOrder(index);
                    continue;
                }
                LongPressAccessPointPreference preference =
                        createLongPressAccessPointPreference(accessPoint);
                preference.setKey(key);
                preference.setOrder(index);
                if (mOpenSsid != null && mOpenSsid.equals(accessPoint.getSsidStr())
                        && accessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
                    if (!accessPoint.isSaved() || isDisabledByWrongPassword(accessPoint)) {
			
                        onPreferenceTreeClick(preference);
                        mOpenSsid = null;
                    }
                }
                mAccessPointsPreferenceCategory.addPreference(preference);
                accessPoint.setListener(WifiSettings.this);
                preference.refresh();
            }
        }
        removeCachedPrefs(mAccessPointsPreferenceCategory);
        mAddPreference.setOrder(index);
        mAccessPointsPreferenceCategory.addPreference(mAddPreference);
        setAdditionalSettingsSummaries();

        if (!hasAvailableAccessPoints) {
            setProgressBarVisible(true);
            Preference pref = new Preference(getPrefContext());
            pref.setSelectable(false);
            pref.setSummary(R.string.wifi_empty_list_wifi_on);
 //  <string name="wifi_empty_list_wifi_on" >"正在搜索WLAN网络…"</string>
            pref.setOrder(index++);
            pref.setKey(PREF_KEY_EMPTY_WIFI_LIST);
            mAccessPointsPreferenceCategory.addPreference(pref);
        } else {
            // Continuing showing progress bar for an additional delay to overlap with animation
            getView().postDelayed(mHideProgressBarRunnable, 1700 /* delay millis */);
        }
    }


```

##  WifiUtils.java
frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/WifiUtils.java
```
// 创建 Summary 详情
   public static String buildLoggingSummary(AccessPoint accessPoint, WifiConfiguration config) {
        final StringBuilder summary = new StringBuilder();
        final WifiInfo info = accessPoint.getInfo();  // 1.获得 WifiInfo
        // Add RSSI/band information for this config, what was seen up to 6 seconds ago
        // verbose WiFi Logging is only turned on thru developers settings
        if (accessPoint.isActive() && info != null) {    // 2.获得 频率   isActive 标识能扫描得到的
            summary.append(" f=" + Integer.toString(info.getFrequency()));
        }
        summary.append(" " + getVisibilityStatus(accessPoint));
        if (config != null && !config.getNetworkSelectionStatus().isNetworkEnabled()) {
            summary.append(" (" + config.getNetworkSelectionStatus().getNetworkStatusString());
            if (config.getNetworkSelectionStatus().getDisableTime() > 0) {
                long now = System.currentTimeMillis();
                long diff = (now - config.getNetworkSelectionStatus().getDisableTime()) / 1000;
                long sec = diff % 60; //seconds
                long min = (diff / 60) % 60; //minutes
                long hour = (min / 60) % 60; //hours
                summary.append(", ");
                if (hour > 0) summary.append(Long.toString(hour) + "h ");
                summary.append(Long.toString(min) + "m ");
                summary.append(Long.toString(sec) + "s ");
            }
            summary.append(")");
        }

        if (config != null) {
            WifiConfiguration.NetworkSelectionStatus networkStatus =
                    config.getNetworkSelectionStatus();
            for (int index = WifiConfiguration.NetworkSelectionStatus.NETWORK_SELECTION_ENABLE;
                    index < WifiConfiguration.NetworkSelectionStatus
                            .NETWORK_SELECTION_DISABLED_MAX; index++) {
                if (networkStatus.getDisableReasonCounter(index) != 0) {
                    summary.append(" " + WifiConfiguration.NetworkSelectionStatus
                            .getNetworkDisableReasonString(index) + "="
                            + networkStatus.getDisableReasonCounter(index));
                }
            }
        }

        return summary.toString();
    }


```
##  AccessPoint.java
/frameworks/base/packages/SettingsLib/src/com/android/settingslib/wifi/AccessPoint.java

```
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
                    mContext.getString(R.string.certinstaller_package)) &&
                    !appInfo.packageName.equals(mContext.getString(R.string.WpaConfigApp_package))) {
					// <string name="saved_network" >"由“<xliff:g id="NAME">%1$s</xliff:g>”保存"</string>
                return mContext.getString(R.string.saved_network, appInfo.loadLabel(pm));
            }
        }
        return "";
    }

	public static String getSummary(Context context, DetailedState state, boolean isEphemeral) {
        return getSummary(context, null, state, isEphemeral, null);
    }

    public static String getSummary(Context context, DetailedState state, boolean isEphemeral, String passpointProvider) {
        return getSummary(context, null, state, isEphemeral, passpointProvider);
    }
	
    public String getSummary() {
        return getSettingsSummary(mConfig);
    }

    public String getSettingsSummary() {
        return getSettingsSummary(mConfig);
    }

    private String getSettingsSummary(WifiConfiguration config) {
        // Update to new summary
        StringBuilder summary = new StringBuilder();

        if (isActive() && config != null && config.isPasspoint()) {
            // This is the active connection on passpoint
            summary.append(getSummary(mContext, getDetailedState(),false, config.providerFriendlyName));
        } else if (isActive() && config != null && getDetailedState() == DetailedState.CONNECTED  && mIsCarrierAp) {
            summary.append(String.format(mContext.getString(R.string.connected_via_carrier), mCarrierName));
			//  <string name="connected_via_carrier" >"已通过%1$s连接"</string>
        } else if (isActive()) {
            // This is the active connection on non-passpoint network
            summary.append(getSummary(mContext, getDetailedState(), mInfo != null && mInfo.isEphemeral()));  // 三参数
        } else if (config != null && config.isPasspoint()
                && config.getNetworkSelectionStatus().isNetworkEnabled()) {
            String format = mContext.getString(R.string.available_via_passpoint);
			// <string name="available_via_passpoint" >"可通过%1$s连接"</string>
            summary.append(String.format(format, config.providerFriendlyName));
        } else if (config != null && config.hasNoInternetAccess()) {
            int messageID = config.getNetworkSelectionStatus().isNetworkPermanentlyDisabled()
                    ? R.string.wifi_no_internet_no_reconnect
                    : R.string.wifi_no_internet;
					//<string name="wifi_no_internet_no_reconnect" >"无法自动连接"</string>
					//<string name="wifi_no_internet" >"无法访问互联网"</string>
				
            summary.append(mContext.getString(messageID));
        } else if (config != null && !config.getNetworkSelectionStatus().isNetworkEnabled()) {
            WifiConfiguration.NetworkSelectionStatus networkStatus =
                    config.getNetworkSelectionStatus();
            switch (networkStatus.getNetworkSelectionDisableReason()) {
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_AUTHENTICATION_FAILURE:
                    summary.append(mContext.getString(R.string.wifi_disabled_password_failure));
                 // <string name="wifi_disabled_password_failure" >"身份验证出现问题"</string>
                    break;
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_BY_WRONG_PASSWORD:
                    summary.append(mContext.getString(R.string.wifi_check_password_try_again));
					//  <string name="wifi_check_password_try_again" >"请检查密码，然后重试"</string>
                    break;
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_DHCP_FAILURE:
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_DNS_FAILURE:
                    summary.append(mContext.getString(R.string.wifi_disabled_network_failure));
					//<string name="wifi_disabled_network_failure" >"IP 配置失败"</string>
                    break;
                case WifiConfiguration.NetworkSelectionStatus.DISABLED_ASSOCIATION_REJECTION:
                    summary.append(mContext.getString(R.string.wifi_disabled_generic));
					// <string name="wifi_disabled_generic" >"已停用"</string>
                    break;
            }
        } else if (config != null && config.getNetworkSelectionStatus().isNotRecommended()) {
            summary.append(mContext.getString(R.string.wifi_disabled_by_recommendation_provider));
			//<string name="wifi_disabled_by_recommendation_provider" >"网络质量较差，因此未连接"</string>
        } else if (mIsCarrierAp) {
            summary.append(String.format(mContext.getString(R.string.available_via_carrier), mCarrierName));
			//<string name="available_via_carrier" >"可通过%1$s连接"</string>
        } else if (!isReachable()) { // Wifi out of range
            summary.append(mContext.getString(R.string.wifi_not_in_range));
			//<string name="wifi_not_in_range" >"不在范围内"</string>
        } else { // In range, not disabled.
            if (config != null) { // Is saved network
                // Last attempt to connect to this failed. Show reason why
                switch (config.recentFailure.getAssociationStatus()) {
                    case WifiConfiguration.RecentFailure.STATUS_AP_UNABLE_TO_HANDLE_NEW_STA:
                        summary.append(mContext.getString(R.string.wifi_ap_unable_to_handle_new_sta));
						// <string name="wifi_ap_unable_to_handle_new_sta" >"接入点暂时满载"</string>
                        break;
                    default:
                        // "Saved"   
                        summary.append(mContext.getString(R.string.wifi_remembered));
						// <string name="wifi_remembered" >"已保存"</string>
                        break;
                }
            }
        }

        if (isVerboseLoggingEnabled()) {
            summary.append(WifiUtils.buildLoggingSummary(this, config));
        }

        if (config != null && (WifiUtils.isMeteredOverridden(config) || config.meteredHint)) {
//  <string name="preference_summary_default_combination" >"<xliff:g id="STATE">%1$s</xliff:g>/<xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
            return mContext.getResources().getString(
                    R.string.preference_summary_default_combination,
                    WifiUtils.getMeteredLabel(mContext, config),
                    summary.toString());
        }

        // If Speed label and summary are both present, use the preference combination to combine
        // the two, else return the non-null one.
        if (getSpeedLabel() != null && summary.length() != 0) {
//  <string name="preference_summary_default_combination" >"<xliff:g id="STATE">%1$s</xliff:g>/<xliff:g id="DESCRIPTION">%2$s</xliff:g>"</string>
            return mContext.getResources().getString(
                    R.string.preference_summary_default_combination,
                    getSpeedLabel(),
                    summary.toString());
        } else if (getSpeedLabel() != null) {
            return getSpeedLabel();
        } else {
            return summary.toString();
        }
    }

 // 五参数
public static String getSummary(Context context, String ssid, DetailedState state, boolean isEphemeral, String passpointProvider) {
        if (state == DetailedState.CONNECTED && ssid == null) {
            if (TextUtils.isEmpty(passpointProvider) == false) {
                // Special case for connected + passpoint networks.
                String format = context.getString(R.string.connected_via_passpoint);
				//  <string name="connected_via_passpoint" >"已通过%1$s连接"</string>
                return String.format(format, passpointProvider);
            } else if (isEphemeral) {
                // Special case for connected + ephemeral networks.
                final NetworkScoreManager networkScoreManager = context.getSystemService(
                        NetworkScoreManager.class);
                NetworkScorerAppData scorer = networkScoreManager.getActiveScorer();
                if (scorer != null && scorer.getRecommendationServiceLabel() != null) {
                    String format = context.getString(R.string.connected_via_network_scorer);
                    return String.format(format, scorer.getRecommendationServiceLabel());
                } else {
                    return context.getString(R.string.connected_via_network_scorer_default);
                }
            }
        }

        // Case when there is wifi connected without internet connectivity.  当网络没有internet能力的时候  ▲
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (state == DetailedState.CONNECTED) {
            IWifiManager wifiManager = IWifiManager.Stub.asInterface(ServiceManager.getService(Context.WIFI_SERVICE));
            NetworkCapabilities nc = null;

            try {
                nc = cm.getNetworkCapabilities(wifiManager.getCurrentNetwork()); // 依据networkId 获取网络能力
            } catch (RemoteException e) {}

            if (nc != null) {
                if (nc.hasCapability(nc.NET_CAPABILITY_CAPTIVE_PORTAL)) {  // Portal 网络
                    int id = context.getResources().getIdentifier("network_available_sign_in", "string", "android");
					// <string name="network_available_sign_in" >"登录到网络"</string>
					
                    return context.getString(id);
                } else if (!nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
				//  <string name="wifi_connected_no_internet">Connected, no internet</string>
				//  <string name="wifi_connected_no_internet" >"已连接，但无法访问互联网"</string>
                    return context.getString(R.string.wifi_connected_no_internet);
					
                }
            } else {
                return "";
            }
        }
        if (state == null) {
            Log.w(TAG, "state is null, returning empty summary");
            return "";
        }
        String[] formats = context.getResources().getStringArray((ssid == null) ? R.array.wifi_status : R.array.wifi_status_with_ssid);
        int index = state.ordinal();

        if (index >= formats.length || formats[index].length() == 0) {
            return "";
        }
        return String.format(formats[index], ssid);
    }

  <string-array name="wifi_status">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接..."</item>
    <item >"正在验证身份…"</item>
    <item >"正在获取IP地址..."</item>
    <item >"已连接"</item>
    <item >"已暂停"</item>
    <item >"正在断开连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>


  <string-array name="wifi_status_with_ssid">
    <item ></item>
    <item >"正在扫描..."</item>
    <item >"正在连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>..."</item>
    <item >"正在通过 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 进行身份验证..."</item>
    <item >"正在从<xliff:g id="NETWORK_NAME">%1$s</xliff:g>获取IP地址..."</item>
    <item >"已连接到 <xliff:g id="NETWORK_NAME">%1$s</xliff:g>"</item>
    <item >"已暂停"</item>
    <item >"正在断开与 <xliff:g id="NETWORK_NAME">%1$s</xliff:g> 的连接..."</item>
    <item >"已断开连接"</item>
    <item >"失败"</item>
    <item >"已停用"</item>
    <item >"暂时关闭（网络状况不佳）"</item>
  </string-array>

```

##  AccessPointPreference.java
```
public AccessPointPreference(AccessPoint accessPoint, Context context, UserBadgeCache cache,boolean forSavedNetworks) {
setWidgetLayoutResource(R.layout.access_point_friction_widget);  // 图标布局文件为  access_point_friction_widget.xml


 setSummary(mForSavedNetworks ? mAccessPoint.getSavedNetworkSummary(): mAccessPoint.getSettingsSummary());  // ▲

}
		

```

## ConnectivityManager.java
frameworks/base/core/java/android/net/ConnectivityManager.java
getNetworkCapabilities
```
    public NetworkCapabilities getNetworkCapabilities(Network network) {
        try {
            return mService.getNetworkCapabilities(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }


```

##  ConnectivityService.java
frameworks/base/services/core/java/com/android/server/ConnectivityService.java
```
    @Override
    public NetworkCapabilities getNetworkCapabilities(Network network) {
        enforceAccessPermission();   //  检查权限
        return getNetworkCapabilitiesInternal(getNetworkAgentInfoForNetwork(network));
    }
	
	
    private NetworkCapabilities getNetworkCapabilitiesInternal(NetworkAgentInfo nai) {
        if (nai != null) {
            synchronized (nai) {
                if (nai.networkCapabilities != null) {
                    return networkCapabilitiesRestrictedForCallerPermissions(
                            nai.networkCapabilities,
                            Binder.getCallingPid(), Binder.getCallingUid());
                }
            }
        }
        return null;
    }


	    private NetworkCapabilities networkCapabilitiesRestrictedForCallerPermissions( NetworkCapabilities nc, int callerPid, int callerUid) {
        final NetworkCapabilities newNc = new NetworkCapabilities(nc);
        if (!checkSettingsPermission(callerPid, callerUid)) {
            newNc.setUids(null);
            newNc.setSSID(null);
        }
        return newNc;
    }
	
	
    private NetworkAgentInfo getNetworkAgentInfoForNetwork(Network network) {
        if (network == null) {
            return null;
        }
        return getNetworkAgentInfoForNetId(network.netId);
    }

	
	
	  private NetworkAgentInfo getNetworkAgentInfoForNetId(int netId) {
        synchronized (mNetworkForNetId) {
            return mNetworkForNetId.get(netId);
        }
    }
	
```

##  NetworkAgentInfo.java
/frameworks/base/services/core/java/com/android/server/connectivity/NetworkAgentInfo.java
NetworkAgentInfo.networkCapabilities     android.net.NetworkCapabilities;
```

  public NetworkCapabilities networkCapabilities;

  
      public String toString() {
        return "NetworkAgentInfo{ ni{" + networkInfo + "}  " +
                "network{" + network + "}  nethandle{" + network.getNetworkHandle() + "}  " +
                "lp{" + linkProperties + "}  " +
                "nc{" + networkCapabilities + "}  Score{" + getCurrentScore() + "}  " +
                "everValidated{" + everValidated + "}  lastValidated{" + lastValidated + "}  " +
                "created{" + created + "} lingering{" + isLingering() + "} " +
                "explicitlySelected{" + networkMisc.explicitlySelected + "} " +
                "acceptUnvalidated{" + networkMisc.acceptUnvalidated + "} " +
                "everCaptivePortalDetected{" + everCaptivePortalDetected + "} " +
                "lastCaptivePortalDetected{" + lastCaptivePortalDetected + "} " +
                "clat{" + clatd + "} " +
                "}";
    }
	
```
## NetworkCapabilities.java
/frameworks/base/core/java/android/net/NetworkCapabilities.java
```

public final class NetworkCapabilities implements Parcelable {
    private static final String TAG = "NetworkCapabilities";
    private static final int INVALID_UID = -1;

	
	public NetworkCapabilities() {
        clearAll();
        mNetworkCapabilities = DEFAULT_CAPABILITIES;
    }
	
    public NetworkCapabilities(NetworkCapabilities nc) {
        if (nc != null) {
            set(nc);
        }
    }

	    public void clearAll() {
        mNetworkCapabilities = mTransportTypes = mUnwantedNetworkCapabilities = 0;
        mLinkUpBandwidthKbps = mLinkDownBandwidthKbps = LINK_BANDWIDTH_UNSPECIFIED;
        mNetworkSpecifier = null;
        mSignalStrength = SIGNAL_STRENGTH_UNSPECIFIED;
        mUids = null;
        mEstablishingVpnAppUid = INVALID_UID;
        mSSID = null;
    }
	
    public void set(NetworkCapabilities nc) {
        mNetworkCapabilities = nc.mNetworkCapabilities;
        mTransportTypes = nc.mTransportTypes;
        mLinkUpBandwidthKbps = nc.mLinkUpBandwidthKbps;
        mLinkDownBandwidthKbps = nc.mLinkDownBandwidthKbps;
        mNetworkSpecifier = nc.mNetworkSpecifier;
        mSignalStrength = nc.mSignalStrength;
        setUids(nc.mUids); // Will make the defensive copy
        mEstablishingVpnAppUid = nc.mEstablishingVpnAppUid;
        mUnwantedNetworkCapabilities = nc.mUnwantedNetworkCapabilities;
        mSSID = nc.mSSID;
    }
	
	
	
	
    public static final int NET_CAPABILITY_MMS            = 0;   // 发MMS 短信息的功能
    public static final int NET_CAPABILITY_SUPL           = 1; // 能访问运营商的 SUPL server服务器 得到 GPS位置信息的功能

	// Dial Up Networking拨号网络的简称 
	//用于执行一个拨号网络网桥，使载体能知道拨号网络流量的应用程序，此连接能与default连接同时使用
    public static final int NET_CAPABILITY_DUN            = 2;  // 能拨号上网的能力
    public static final int NET_CAPABILITY_FOTA           = 3;   // Firmware Over-The-Air  能连接到运营商的FOTA 进行升级

	// IMS(IP Multimedia Subsystem)是IP多媒体子系统 
    public static final int NET_CAPABILITY_IMS            = 4; // 连接到运营商IMS服务器的能力

	// Cloud Service Broker  云服务?
    public static final int NET_CAPABILITY_CBS            = 5;  // 连接到运营商CBS服务器的能力
    public static final int NET_CAPABILITY_WIFI_P2P       = 6; // 标记当前网络是WIFI P2P的网络    【】
    public static final int NET_CAPABILITY_IA             = 7;  // Initial Attach servers IA服务器
    public static final int NET_CAPABILITY_RCS            = 8;  //  RCS servers  运营商RCS服务器

    public static final int NET_CAPABILITY_XCAP           = 9;  // 运营商 XCAP 服务器     配置和控制
    public static final int NET_CAPABILITY_EIMS           = 10;  // Emergency IMS  EIMS servers  紧急通信服务
    public static final int NET_CAPABILITY_NOT_METERED    = 11;   // 标记当前网络未测量  【】
    public static final int NET_CAPABILITY_INTERNET       = 12;  //   标记当前网络有访问互联网的能力 【】 true:可访问互联网   false 不可访问互联网

    public static final int NET_CAPABILITY_NOT_RESTRICTED = 13;  // 可以正常使用的网络 【】

	 // sim 卡选择的网络   相互连接的BT设备，   用户主动加入的网络  ，  不信任的网络仅限于未知网络 【】
    public static final int NET_CAPABILITY_TRUSTED        = 14;  // 标识用户对该网络隐式信任  【】
    public static final int NET_CAPABILITY_NOT_VPN        = 15;  //  默认的   不是VPN的网络   【】
	
	
//  <string name="wifi_connected_no_internet">Connected, no internet</string>
//  <string name="wifi_connected_no_internet" >"已连接，但无法访问互联网"</string>
    public static final int NET_CAPABILITY_VALIDATED      = 16; // 标识在该网络成功认证 true:认证成功  false: 认证失败 已连接无网络
    public static final int NET_CAPABILITY_CAPTIVE_PORTAL = 17;  // 指示该网络是 Portal网络 captive portal    【】
    public static final int NET_CAPABILITY_NOT_ROAMING = 18;    // 指示该网络没有 漫游    【】
    public static final int NET_CAPABILITY_FOREGROUND = 19; // 表示该网络可供应用程序使用 前台网络    【】
    public static final int NET_CAPABILITY_NOT_CONGESTED = 20;   // 指示当前网络具有推迟发送 防止拥挤的能力

	 // 网络挂起 IP地址和网络连接保持有效 但是网络暂时无法发送数据
	 // 窝蜂网络暂时丢失信号 
    public static final int NET_CAPABILITY_NOT_SUSPENDED = 21;    // 指示当前网络不会挂起  会一直传输数据 【】

    public static final int NET_CAPABILITY_OEM_PAID = 22; // 指示通过此网络的流量由oem支付  免流
	
	
	
	   // 能改变的性能指标
	   private static final long MUTABLE_CAPABILITIES =
            (1 << NET_CAPABILITY_TRUSTED)
            | (1 << NET_CAPABILITY_VALIDATED)
            | (1 << NET_CAPABILITY_CAPTIVE_PORTAL)
            | (1 << NET_CAPABILITY_NOT_ROAMING)
            | (1 << NET_CAPABILITY_FOREGROUND)
            | (1 << NET_CAPABILITY_NOT_CONGESTED)
            | (1 << NET_CAPABILITY_NOT_SUSPENDED);


		// 默认构造 NetworkCapabilities 对象时设置为true的标识
		private static final long DEFAULT_CAPABILITIES =
            (1 << NET_CAPABILITY_NOT_RESTRICTED) |
            (1 << NET_CAPABILITY_TRUSTED) |
            (1 << NET_CAPABILITY_NOT_VPN);
			
}
	
	
nc{[ Transports: WIFI Capabilities: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED 

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        if (0 != mTransportTypes) {
            sb.append(" Transports: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, mTransportTypes,
                    NetworkCapabilities::transportNameOf, "|");
        }
        if (0 != mNetworkCapabilities) {
            sb.append(" Capabilities: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, mNetworkCapabilities,
                    NetworkCapabilities::capabilityNameOf, "&");
        }
        if (0 != mNetworkCapabilities) {
            sb.append(" Unwanted: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, mUnwantedNetworkCapabilities,
                    NetworkCapabilities::capabilityNameOf, "&");
        }
        if (mLinkUpBandwidthKbps > 0) {
            sb.append(" LinkUpBandwidth>=").append(mLinkUpBandwidthKbps).append("Kbps");
        }
        if (mLinkDownBandwidthKbps > 0) {
            sb.append(" LinkDnBandwidth>=").append(mLinkDownBandwidthKbps).append("Kbps");
        }
        if (mNetworkSpecifier != null) {
            sb.append(" Specifier: <").append(mNetworkSpecifier).append(">");
        }
        if (hasSignalStrength()) {
            sb.append(" SignalStrength: ").append(mSignalStrength);
        }

        if (null != mUids) {
            if ((1 == mUids.size()) && (mUids.valueAt(0).count() == 1)) {
                sb.append(" Uid: ").append(mUids.valueAt(0).start);
            } else {
                sb.append(" Uids: <").append(mUids).append(">");
            }
        }
        if (mEstablishingVpnAppUid != INVALID_UID) {
            sb.append(" EstablishingAppUid: ").append(mEstablishingVpnAppUid);
        }

        if (null != mSSID) {
            sb.append(" SSID: ").append(mSSID);
        }

        sb.append("]");
        return sb.toString();
    }


	
	    public static String capabilityNameOf(@NetCapability int capability) {
        switch (capability) {
            case NET_CAPABILITY_MMS:            return "MMS";
            case NET_CAPABILITY_SUPL:           return "SUPL";
            case NET_CAPABILITY_DUN:            return "DUN";
            case NET_CAPABILITY_FOTA:           return "FOTA";
            case NET_CAPABILITY_IMS:            return "IMS";
            case NET_CAPABILITY_CBS:            return "CBS";
            case NET_CAPABILITY_WIFI_P2P:       return "WIFI_P2P";
            case NET_CAPABILITY_IA:             return "IA";
            case NET_CAPABILITY_RCS:            return "RCS";
            case NET_CAPABILITY_XCAP:           return "XCAP";
            case NET_CAPABILITY_EIMS:           return "EIMS";
            case NET_CAPABILITY_NOT_METERED:    return "NOT_METERED";
            case NET_CAPABILITY_INTERNET:       return "INTERNET";
            case NET_CAPABILITY_NOT_RESTRICTED: return "NOT_RESTRICTED";
            case NET_CAPABILITY_TRUSTED:        return "TRUSTED";
            case NET_CAPABILITY_NOT_VPN:        return "NOT_VPN";
            case NET_CAPABILITY_VALIDATED:      return "VALIDATED";
            case NET_CAPABILITY_CAPTIVE_PORTAL: return "CAPTIVE_PORTAL";
            case NET_CAPABILITY_NOT_ROAMING:    return "NOT_ROAMING";
            case NET_CAPABILITY_FOREGROUND:     return "FOREGROUND";
            case NET_CAPABILITY_NOT_CONGESTED:  return "NOT_CONGESTED";
            case NET_CAPABILITY_NOT_SUSPENDED:  return "NOT_SUSPENDED";
            case NET_CAPABILITY_OEM_PAID:       return "OEM_PAID";
            default:                            return Integer.toString(capability);
        }
		
	
 public static void appendStringRepresentationOfBitMaskToStringBuilder(StringBuilder sb,long bitMask, NameOf nameFetcher, String separator) {
        int bitPos = 0;
        boolean firstElementAdded = false;
        while (bitMask != 0) {
            if ((bitMask & 1) != 0) {
                if (firstElementAdded) {
                    sb.append(separator);
                } else {
                    firstElementAdded = true;
                }
                sb.append(nameFetcher.nameOf(bitPos));
            }
            bitMask >>= 1;
            ++bitPos;
        }
    }
	
	
	
WIFI Capabilities: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED
WIFI CAPABILITIES: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&VALIDATED&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED



	
```

## access_point_friction_widget.xml
SettingsLib/res/layout/access_point_friction_widget.xml      WIFI 图标ImageView
```
<ImageView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/friction_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:contentDescription="@null" />
	
	
AccessPointPreference.java
 private final StateListDrawable mFrictionSld;
 ImageView frictionImageView = (ImageView) view.findViewById(R.id.friction_icon);
 bindFrictionImage(frictionImageView);
 
 
     /**
     * Binds the friction icon drawable using a StateListDrawable.
     *
     * <p>Friction icons will be rebound when notifyChange() is called, and therefore
     * do not need to be managed in refresh()</p>.
     */
    private void bindFrictionImage(ImageView frictionImageView) {
        if (frictionImageView == null || mFrictionSld == null) {
            return;
        }
        if (mAccessPoint.getSecurity() != AccessPoint.SECURITY_NONE) {
            mFrictionSld.setState(STATE_SECURED);
        } else if (mAccessPoint.isMetered()) {
            mFrictionSld.setState(STATE_METERED);
        }
        Drawable drawable = mFrictionSld.getCurrent();
        frictionImageView.setImageDrawable(drawable);
    }
	
	
	 getFrictionStateListDrawable(context)
	 
	 
	     private static StateListDrawable getFrictionStateListDrawable(Context context) {
        TypedArray frictionSld;
        try {
            frictionSld = context.getTheme().obtainStyledAttributes(FRICTION_ATTRS); // { R.attr.wifi_friction }
        } catch (Resources.NotFoundException e) {
            // Fallback for platforms that do not need friction icon resources.
            frictionSld = null;
        }
        return frictionSld != null ? (StateListDrawable) frictionSld.getDrawable(0) : null;
    }
	
	    private static final int[] FRICTION_ATTRS = {
            R.attr.wifi_friction
    };
	
/SettingsLib/res/values/attrs.xml  <attr name="wifi_friction" format="reference" />
/packages/apps/Settings/res/values/themes.xml      <item name="wifi_friction">@drawable/wifi_friction</item>

packages/apps/Settings/res/drawable/wifi_friction.xml	
```
## wifi_friction.xml	
packages/apps/Settings/res/drawable/wifi_friction.xml	
```
<?xml version="1.0" encoding="utf-8"?>
packages/apps/Settings/res/drawable/wifi_friction.xml	
<selector xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:settings="http://schemas.android.com/apk/res/com.android.settings">
    <item
        settings:state_encrypted="true"
        android:drawable="@drawable/ic_friction_lock_closed"/>
    <item
        settings:state_metered="true"
        android:drawable="@drawable/ic_friction_money"/>
</selector>


packages/apps/Settings/res/drawable/ic_friction_lock_closed.xml	
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:viewportWidth="48"
        android:viewportHeight="48"
        android:width="18dp"
        android:height="18dp">
    <path
        android:pathData="M36 16l-2 0 0 -4C34 6.48 29.52 2 24 2 18.48 2 14 6.48 14 12l0 4 -2 0c-2.21 0 -4 1.79 -4 4l0 20c0 2.21 1.79 4 4 4l24 0c2.21 0 4 -1.79 4 -4l0 -20c0 -2.21 -1.79 -4 -4 -4zM24 34c-2.21 0 -4 -1.79 -4 -4 0 -2.21 1.79 -4 4 -4 2.21 0 4 1.79 4 4 0 2.21 -1.79 4 -4 4zm6.2 -18l-12.4 0 0 -4c0 -3.42 2.78 -6.2 6.2 -6.2 3.42 0 6.2 2.78 6.2 6.2l0 4z"
        android:fillColor="?android:attr/colorForeground"
        android:fillAlpha="0.54" />
</vector>


packages/apps/Settings/res/drawable/ic_friction_money.xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:viewportWidth="18"
        android:viewportHeight="18"
        android:width="18dp"
        android:height="18dp">

    <path android:fillColor="?android:attr/colorForeground"
          android:fillAlpha="0.54"
          android:pathData="M9.56 8.1c-1.6-.51-2.66-.71-2.66-1.88 0-.83 .72 -1.62 2.1-1.62 1.59 0 2.1 .88
          2.1 1.94H13c0-1.79-1.17-3.09-3-3.44V1H8v2.11c-1.58 .32 -3 1.37-3 3.12 0 2.25
          1.78 2.8 4 3.52 1.88 .61 2.25 1.04 2.25 2.09 0 .9-.67 1.56-2.25 1.56-1.2
          0-2.25-.84-2.25-2.06h-2c0 1.88 1.38 3.2 3.25 3.56V17h2v-2.07c2.04-.29 3.2-1.49
          3.2-3.1 0-1.87-.94-2.87-3.64-3.73z" />
    <path android:pathData="M0 0h18v18H0z" />
</vector>

```
< img src="ic_friction_lock_closed" />
< img src="ic_friction_money" />



## preference.xml
/frameworks/base/core/res/res/layout/preference.xml    Acesspoint热点布局

```
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


# Log
```
	
WIFI Capabilities: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED
WIFI CAPABILITIES: NOT_METERED&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&VALIDATED&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED



```