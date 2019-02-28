#  WIFI-Log综合

## wpa_supplicant


### EVENT(wpa_supplicant)

#### Supplicant产生的事件
```
adb logcat | findstr  "CTRL-EVENT-DISCONNECTED"       // 断连事件Log

wpa_supplicant: wlan0: CTRL-EVENT-DISCONNECTED bssid=10:62:eb:9a:dc:68 reason=3 locally_generated=1 disconnect_rssi=52
wpa_supplicant: wlan0: CTRL-EVENT-DISCONNECTED bssid=10:62:eb:9a:dc:68 reason=2 locally_generated=1 disconnect_rssi=52
wpa_supplicant: wlan0: CTRL-EVENT-DISCONNECTED bssid=10:62:eb:9a:dc:68 reason=1 locally_generated=1 disconnect_rssi=52
wpa_supplicant: wlan0: CTRL-EVENT-DISCONNECTED bssid=10:62:eb:9a:dc:68 reason=0 locally_generated=1 disconnect_rssi=53
wpa_supplicant: wlan0: CTRL-EVENT-DISCONNECTED bssid=xx:xx:xx:xx:xx:xx reason=x locally_generated=x disconnect_rssi=xx

```
```
//  对应打印代码:
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/events.c#2809

static void wpa_supplicant_event_disassoc(struct wpa_supplicant *wpa_s,u16 reason_code,int locally_generated){
......
	if (!is_zero_ether_addr(bssid) ||
	    wpa_s->wpa_state >= WPA_AUTHENTICATING) {
		wpa_msg(wpa_s, MSG_INFO, WPA_EVENT_DISCONNECTED "bssid=" MACSTR
			" reason=%d%s",
			MAC2STR(bssid), reason_code,
			locally_generated ? " locally_generated=1" : "");
	}
......
}


```

##### CTRL-EVENT-XXX集合

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/wpa_ctrl.h#26

#define WPA_EVENT_CONNECTED "CTRL-EVENT-CONNECTED "  /** Authentication completed successfully and data connection enabled */

#define WPA_EVENT_DISCONNECTED "CTRL-EVENT-DISCONNECTED "  /** Disconnected, data connection is not available */

#define WPA_EVENT_ASSOC_REJECT "CTRL-EVENT-ASSOC-REJECT "   /** Association rejected during connection attempt */

#define WPA_EVENT_AUTH_REJECT "CTRL-EVENT-AUTH-REJECT "   /** Authentication rejected during connection attempt */

#define WPA_EVENT_TERMINATING "CTRL-EVENT-TERMINATING "         /** wpa_supplicant is exiting */

#define WPA_EVENT_PASSWORD_CHANGED "CTRL-EVENT-PASSWORD-CHANGED "    /** Password change was completed successfully */

#define WPA_EVENT_EAP_NOTIFICATION "CTRL-EVENT-EAP-NOTIFICATION "    /** EAP-Request/Notification received */

#define WPA_EVENT_EAP_STARTED "CTRL-EVENT-EAP-STARTED "    /** EAP authentication started (EAP-Request/Identity received) */

#define WPA_EVENT_EAP_PROPOSED_METHOD "CTRL-EVENT-EAP-PROPOSED-METHOD "     /** EAP method proposed by the server */

#define WPA_EVENT_EAP_METHOD "CTRL-EVENT-EAP-METHOD "     /** EAP method selected */

#define WPA_EVENT_EAP_PEER_CERT "CTRL-EVENT-EAP-PEER-CERT "    /** EAP peer certificate from TLS */

#define WPA_EVENT_EAP_PEER_ALT "CTRL-EVENT-EAP-PEER-ALT "    /** EAP peer certificate alternative subject name component from TLS */

#define WPA_EVENT_EAP_TLS_CERT_ERROR "CTRL-EVENT-EAP-TLS-CERT-ERROR "    /** EAP TLS certificate chain validation error */

#define WPA_EVENT_EAP_STATUS "CTRL-EVENT-EAP-STATUS "    /** EAP status */

#define WPA_EVENT_EAP_SUCCESS "CTRL-EVENT-EAP-SUCCESS "    /** EAP authentication completed successfully */

#define WPA_EVENT_EAP_FAILURE "CTRL-EVENT-EAP-FAILURE "    /** EAP authentication failed (EAP-Failure received) */

#define WPA_EVENT_TEMP_DISABLED "CTRL-EVENT-SSID-TEMP-DISABLED "    /** Network block temporarily disabled (e.g., due to authentication failure) */

#define WPA_EVENT_REENABLED "CTRL-EVENT-SSID-REENABLED "         /** Temporarily disabled network block re-enabled */

#define WPA_EVENT_SCAN_STARTED "CTRL-EVENT-SCAN-STARTED "    /** New scan started */

#define WPA_EVENT_SCAN_RESULTS "CTRL-EVENT-SCAN-RESULTS "   /** New scan results available */

#define WPA_EVENT_SCAN_FAILED "CTRL-EVENT-SCAN-FAILED "     /** Scan command failed */

#define WPA_EVENT_STATE_CHANGE "CTRL-EVENT-STATE-CHANGE "   /** wpa_supplicant state change */

#define WPA_EVENT_BSS_ADDED "CTRL-EVENT-BSS-ADDED "     /** A new BSS entry was added (followed by BSS entry id and BSSID) */

#define WPA_EVENT_BSS_REMOVED "CTRL-EVENT-BSS-REMOVED "     /** A BSS entry was removed (followed by BSS entry id and BSSID) */

#define WPA_EVENT_NETWORK_NOT_FOUND "CTRL-EVENT-NETWORK-NOT-FOUND "    /** No suitable network was found */

#define WPA_EVENT_SIGNAL_CHANGE "CTRL-EVENT-SIGNAL-CHANGE "         /** Change in the signal level was reported by the driver */

#define WPA_EVENT_BEACON_LOSS "CTRL-EVENT-BEACON-LOSS "         /** Beacon loss reported by the driver */

#define WPA_EVENT_REGDOM_CHANGE "CTRL-EVENT-REGDOM-CHANGE "    /** Regulatory domain channel */

#define WPA_EVENT_CHANNEL_SWITCH "CTRL-EVENT-CHANNEL-SWITCH "    /** Channel switch (followed by freq=<MHz> and other channel parameters) */

/*The event has a status=<0/1/2> parameter where   0=unknown  1=IP subnet unchanged (can continue to use the old IP address)   2=IP subnet changed (need to get a new IP address) */
/* indicate whether IP subnet has changed */
#define WPA_EVENT_SUBNET_STATUS_UPDATE "CTRL-EVENT-SUBNET-STATUS-UPDATE "  

#define WPA_EVENT_FREQ_CONFLICT "CTRL-EVENT-FREQ-CONFLICT "    /*Notification of frequency conflict due to a concurrent operation The indicated network is disabled */

#define WPA_EVENT_AVOID_FREQ "CTRL-EVENT-AVOID-FREQ "    /** Frequency ranges that the driver recommends to avoid  驱动需要规避的信道频率*/



```



#### Supplicant接受Driver通知事件

```

adb logcat | findstr  "nl80211:.Drv.Event"


wpa_supplicant: nl80211: Drv Event 103 (NL80211_CMD_VENDOR) received for p2p0
wpa_supplicant: nl80211: Drv Event 103 (NL80211_CMD_VENDOR) received for p2p0
wpa_supplicant: nl80211: Drv Event 55 (NL80211_CMD_REMAIN_ON_CHANNEL) received for p2p0
wpa_supplicant: nl80211: Drv Event 56 (NL80211_CMD_CANCEL_REMAIN_ON_CHANNEL) received for p2p0
wpa_supplicant: nl80211: Drv Event 103 (NL80211_CMD_VENDOR) received for p2p0
wpa_supplicant: nl80211: Drv Event 103 (NL80211_CMD_VENDOR) received for p2p0
wpa_supplicant: nl80211: Drv Event 55 (NL80211_CMD_REMAIN_ON_CHANNEL) received for p2p0
wpa_supplicant: nl80211: Drv Event 56 (NL80211_CMD_CANCEL_REMAIN_ON_CHANNEL) received for p2p0
wpa_supplicant: nl80211: Drv Event 34 (NL80211_CMD_NEW_SCAN_RESULTS) received for wlan0
wpa_supplicant: nl80211: Drv Event 33 (NL80211_CMD_TRIGGER_SCAN) received for wlan0
wpa_supplicant: nl80211: Drv Event 34 (NL80211_CMD_NEW_SCAN_RESULTS) received for wlan0
wpa_supplicant: nl80211: Drv Event 48 (NL80211_CMD_DISCONNECT) received for wlan0
wpa_supplicant: nl80211: Drv Event 33 (NL80211_CMD_TRIGGER_SCAN) received for wlan0
wpa_supplicant: nl80211: Drv Event 34 (NL80211_CMD_NEW_SCAN_RESULTS) received for wlan0
wpa_supplicant: nl80211: Drv Event 46 (NL80211_CMD_CONNECT) received for wlan0
 
```

```
//  对应打印代码:
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver_nl80211_event.c#2185



static void do_process_drv_event(struct i802_bss *bss, int cmd,struct nlattr **tb)
{
.....
wpa_printf(MSG_DEBUG, "nl80211: Drv Event %d (%s) received for %s", cmd, nl80211_command_to_string(cmd), bss->ifname);
wpa_printf(MSG_DEBUG, "nl80211: Connect event (status=%u ignore_next_local_disconnect=%d)", status_code, drv->ignore_next_local_disconnect);
.....
}


```

##### NL80211_CMD_XXX事件集合

```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver_nl80211_event.c#23

static const char * nl80211_command_to_string(enum nl80211_commands cmd)
{
#define C2S(x) case x: return #x;
	switch (cmd) {
	C2S(NL80211_CMD_UNSPEC)
	C2S(NL80211_CMD_GET_WIPHY)
	C2S(NL80211_CMD_SET_WIPHY)
	C2S(NL80211_CMD_NEW_WIPHY)
	C2S(NL80211_CMD_DEL_WIPHY)
	C2S(NL80211_CMD_GET_INTERFACE)
	C2S(NL80211_CMD_SET_INTERFACE)
	C2S(NL80211_CMD_NEW_INTERFACE)
	C2S(NL80211_CMD_DEL_INTERFACE)
	C2S(NL80211_CMD_GET_KEY)
	C2S(NL80211_CMD_SET_KEY)
	C2S(NL80211_CMD_NEW_KEY)
	C2S(NL80211_CMD_DEL_KEY)
	C2S(NL80211_CMD_GET_BEACON)
	C2S(NL80211_CMD_SET_BEACON)
	C2S(NL80211_CMD_START_AP)
	C2S(NL80211_CMD_STOP_AP)
	C2S(NL80211_CMD_GET_STATION)
	C2S(NL80211_CMD_SET_STATION)
	C2S(NL80211_CMD_NEW_STATION)
	C2S(NL80211_CMD_DEL_STATION)
	C2S(NL80211_CMD_GET_MPATH)
	C2S(NL80211_CMD_SET_MPATH)
	C2S(NL80211_CMD_NEW_MPATH)
	C2S(NL80211_CMD_DEL_MPATH)
	C2S(NL80211_CMD_SET_BSS)
	C2S(NL80211_CMD_SET_REG)
	C2S(NL80211_CMD_REQ_SET_REG)
	C2S(NL80211_CMD_GET_MESH_CONFIG)
	C2S(NL80211_CMD_SET_MESH_CONFIG)
	C2S(NL80211_CMD_SET_MGMT_EXTRA_IE)
	C2S(NL80211_CMD_GET_REG)
	C2S(NL80211_CMD_GET_SCAN)
	C2S(NL80211_CMD_TRIGGER_SCAN)
	C2S(NL80211_CMD_NEW_SCAN_RESULTS)
	C2S(NL80211_CMD_SCAN_ABORTED)
	C2S(NL80211_CMD_REG_CHANGE)
	C2S(NL80211_CMD_AUTHENTICATE)
	C2S(NL80211_CMD_ASSOCIATE)
	C2S(NL80211_CMD_DEAUTHENTICATE)
	C2S(NL80211_CMD_DISASSOCIATE)
	C2S(NL80211_CMD_MICHAEL_MIC_FAILURE)
	C2S(NL80211_CMD_REG_BEACON_HINT)
	C2S(NL80211_CMD_JOIN_IBSS)
	C2S(NL80211_CMD_LEAVE_IBSS)
	C2S(NL80211_CMD_TESTMODE)
	C2S(NL80211_CMD_CONNECT)
	C2S(NL80211_CMD_ROAM)
	C2S(NL80211_CMD_DISCONNECT)
	C2S(NL80211_CMD_SET_WIPHY_NETNS)
	C2S(NL80211_CMD_GET_SURVEY)
	C2S(NL80211_CMD_NEW_SURVEY_RESULTS)
	C2S(NL80211_CMD_SET_PMKSA)
	C2S(NL80211_CMD_DEL_PMKSA)
	C2S(NL80211_CMD_FLUSH_PMKSA)
	C2S(NL80211_CMD_REMAIN_ON_CHANNEL)
	C2S(NL80211_CMD_CANCEL_REMAIN_ON_CHANNEL)
	C2S(NL80211_CMD_SET_TX_BITRATE_MASK)
	C2S(NL80211_CMD_REGISTER_FRAME)
	C2S(NL80211_CMD_FRAME)
	C2S(NL80211_CMD_FRAME_TX_STATUS)
	C2S(NL80211_CMD_SET_POWER_SAVE)
	C2S(NL80211_CMD_GET_POWER_SAVE)
	C2S(NL80211_CMD_SET_CQM)
	C2S(NL80211_CMD_NOTIFY_CQM)
	C2S(NL80211_CMD_SET_CHANNEL)
	C2S(NL80211_CMD_SET_WDS_PEER)
	C2S(NL80211_CMD_FRAME_WAIT_CANCEL)
	C2S(NL80211_CMD_JOIN_MESH)
	C2S(NL80211_CMD_LEAVE_MESH)
	C2S(NL80211_CMD_UNPROT_DEAUTHENTICATE)
	C2S(NL80211_CMD_UNPROT_DISASSOCIATE)
	C2S(NL80211_CMD_NEW_PEER_CANDIDATE)
	C2S(NL80211_CMD_GET_WOWLAN)
	C2S(NL80211_CMD_SET_WOWLAN)
	C2S(NL80211_CMD_START_SCHED_SCAN)
	C2S(NL80211_CMD_STOP_SCHED_SCAN)
	C2S(NL80211_CMD_SCHED_SCAN_RESULTS)
	C2S(NL80211_CMD_SCHED_SCAN_STOPPED)
	C2S(NL80211_CMD_SET_REKEY_OFFLOAD)
	C2S(NL80211_CMD_PMKSA_CANDIDATE)
	C2S(NL80211_CMD_TDLS_OPER)
	C2S(NL80211_CMD_TDLS_MGMT)
	C2S(NL80211_CMD_UNEXPECTED_FRAME)
	C2S(NL80211_CMD_PROBE_CLIENT)
	C2S(NL80211_CMD_REGISTER_BEACONS)
	C2S(NL80211_CMD_UNEXPECTED_4ADDR_FRAME)
	C2S(NL80211_CMD_SET_NOACK_MAP)
	C2S(NL80211_CMD_CH_SWITCH_NOTIFY)
	C2S(NL80211_CMD_START_P2P_DEVICE)
	C2S(NL80211_CMD_STOP_P2P_DEVICE)
	C2S(NL80211_CMD_CONN_FAILED)
	C2S(NL80211_CMD_SET_MCAST_RATE)
	C2S(NL80211_CMD_SET_MAC_ACL)
	C2S(NL80211_CMD_RADAR_DETECT)
	C2S(NL80211_CMD_GET_PROTOCOL_FEATURES)
	C2S(NL80211_CMD_UPDATE_FT_IES)
	C2S(NL80211_CMD_FT_EVENT)
	C2S(NL80211_CMD_CRIT_PROTOCOL_START)
	C2S(NL80211_CMD_CRIT_PROTOCOL_STOP)
	C2S(NL80211_CMD_GET_COALESCE)
	C2S(NL80211_CMD_SET_COALESCE)
	C2S(NL80211_CMD_CHANNEL_SWITCH)
	C2S(NL80211_CMD_VENDOR)
	C2S(NL80211_CMD_SET_QOS_MAP)
	C2S(NL80211_CMD_ADD_TX_TS)
	C2S(NL80211_CMD_DEL_TX_TS)
	default:
		return "NL80211_CMD_UNKNOWN";
	}
#undef C2S
}




http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/nl80211_copy.h#951

enum nl80211_commands {
/* don't change the order or add anything between, this is ABI! */
	NL80211_CMD_UNSPEC,

	NL80211_CMD_GET_WIPHY,		/* can dump */
	NL80211_CMD_SET_WIPHY,
	NL80211_CMD_NEW_WIPHY,
	NL80211_CMD_DEL_WIPHY,

	NL80211_CMD_GET_INTERFACE,	/* can dump */
	NL80211_CMD_SET_INTERFACE,
	NL80211_CMD_NEW_INTERFACE,
	NL80211_CMD_DEL_INTERFACE,

	NL80211_CMD_GET_KEY,
	NL80211_CMD_SET_KEY,
	NL80211_CMD_NEW_KEY,
	NL80211_CMD_DEL_KEY,

	NL80211_CMD_GET_BEACON,
	NL80211_CMD_SET_BEACON,
	NL80211_CMD_START_AP,
	NL80211_CMD_NEW_BEACON = NL80211_CMD_START_AP,
	NL80211_CMD_STOP_AP,
	NL80211_CMD_DEL_BEACON = NL80211_CMD_STOP_AP,

	NL80211_CMD_GET_STATION,
	NL80211_CMD_SET_STATION,
	NL80211_CMD_NEW_STATION,
	NL80211_CMD_DEL_STATION,

	NL80211_CMD_GET_MPATH,
	NL80211_CMD_SET_MPATH,
	NL80211_CMD_NEW_MPATH,
	NL80211_CMD_DEL_MPATH,

	NL80211_CMD_SET_BSS,

	NL80211_CMD_SET_REG,
	NL80211_CMD_REQ_SET_REG,

	NL80211_CMD_GET_MESH_CONFIG,
	NL80211_CMD_SET_MESH_CONFIG,

	NL80211_CMD_SET_MGMT_EXTRA_IE /* reserved; not used */,

	NL80211_CMD_GET_REG,

	NL80211_CMD_GET_SCAN,
	NL80211_CMD_TRIGGER_SCAN,
	NL80211_CMD_NEW_SCAN_RESULTS,
	NL80211_CMD_SCAN_ABORTED,

	NL80211_CMD_REG_CHANGE,

	NL80211_CMD_AUTHENTICATE,
	NL80211_CMD_ASSOCIATE,
	NL80211_CMD_DEAUTHENTICATE,
	NL80211_CMD_DISASSOCIATE,

	NL80211_CMD_MICHAEL_MIC_FAILURE,

	NL80211_CMD_REG_BEACON_HINT,

	NL80211_CMD_JOIN_IBSS,
	NL80211_CMD_LEAVE_IBSS,

	NL80211_CMD_TESTMODE,

	NL80211_CMD_CONNECT,
	NL80211_CMD_ROAM,
	NL80211_CMD_DISCONNECT,

	NL80211_CMD_SET_WIPHY_NETNS,

	NL80211_CMD_GET_SURVEY,
	NL80211_CMD_NEW_SURVEY_RESULTS,

	NL80211_CMD_SET_PMKSA,
	NL80211_CMD_DEL_PMKSA,
	NL80211_CMD_FLUSH_PMKSA,

	NL80211_CMD_REMAIN_ON_CHANNEL,
	NL80211_CMD_CANCEL_REMAIN_ON_CHANNEL,

	NL80211_CMD_SET_TX_BITRATE_MASK,

	NL80211_CMD_REGISTER_FRAME,
	NL80211_CMD_REGISTER_ACTION = NL80211_CMD_REGISTER_FRAME,
	NL80211_CMD_FRAME,
	NL80211_CMD_ACTION = NL80211_CMD_FRAME,
	NL80211_CMD_FRAME_TX_STATUS,
	NL80211_CMD_ACTION_TX_STATUS = NL80211_CMD_FRAME_TX_STATUS,

	NL80211_CMD_SET_POWER_SAVE,
	NL80211_CMD_GET_POWER_SAVE,

	NL80211_CMD_SET_CQM,
	NL80211_CMD_NOTIFY_CQM,

	NL80211_CMD_SET_CHANNEL,
	NL80211_CMD_SET_WDS_PEER,

	NL80211_CMD_FRAME_WAIT_CANCEL,

	NL80211_CMD_JOIN_MESH,
	NL80211_CMD_LEAVE_MESH,

	NL80211_CMD_UNPROT_DEAUTHENTICATE,
	NL80211_CMD_UNPROT_DISASSOCIATE,

	NL80211_CMD_NEW_PEER_CANDIDATE,

	NL80211_CMD_GET_WOWLAN,
	NL80211_CMD_SET_WOWLAN,

	NL80211_CMD_START_SCHED_SCAN,
	NL80211_CMD_STOP_SCHED_SCAN,
	NL80211_CMD_SCHED_SCAN_RESULTS,
	NL80211_CMD_SCHED_SCAN_STOPPED,

	NL80211_CMD_SET_REKEY_OFFLOAD,

	NL80211_CMD_PMKSA_CANDIDATE,

	NL80211_CMD_TDLS_OPER,
	NL80211_CMD_TDLS_MGMT,

	NL80211_CMD_UNEXPECTED_FRAME,

	NL80211_CMD_PROBE_CLIENT,

	NL80211_CMD_REGISTER_BEACONS,

	NL80211_CMD_UNEXPECTED_4ADDR_FRAME,

	NL80211_CMD_SET_NOACK_MAP,

	NL80211_CMD_CH_SWITCH_NOTIFY,

	NL80211_CMD_START_P2P_DEVICE,
	NL80211_CMD_STOP_P2P_DEVICE,

	NL80211_CMD_CONN_FAILED,

	NL80211_CMD_SET_MCAST_RATE,

	NL80211_CMD_SET_MAC_ACL,

	NL80211_CMD_RADAR_DETECT,

	NL80211_CMD_GET_PROTOCOL_FEATURES,

	NL80211_CMD_UPDATE_FT_IES,
	NL80211_CMD_FT_EVENT,

	NL80211_CMD_CRIT_PROTOCOL_START,
	NL80211_CMD_CRIT_PROTOCOL_STOP,

	NL80211_CMD_GET_COALESCE,
	NL80211_CMD_SET_COALESCE,

	NL80211_CMD_CHANNEL_SWITCH,

	NL80211_CMD_VENDOR,

	NL80211_CMD_SET_QOS_MAP,

	NL80211_CMD_ADD_TX_TS,
	NL80211_CMD_DEL_TX_TS,

	NL80211_CMD_GET_MPP,

	NL80211_CMD_JOIN_OCB,
	NL80211_CMD_LEAVE_OCB,

	NL80211_CMD_CH_SWITCH_STARTED_NOTIFY,

	NL80211_CMD_TDLS_CHANNEL_SWITCH,
	NL80211_CMD_TDLS_CANCEL_CHANNEL_SWITCH,

	NL80211_CMD_WIPHY_REG_CHANGE,

	NL80211_CMD_ABORT_SCAN,

	NL80211_CMD_START_NAN,
	NL80211_CMD_STOP_NAN,
	NL80211_CMD_ADD_NAN_FUNCTION,
	NL80211_CMD_DEL_NAN_FUNCTION,
	NL80211_CMD_CHANGE_NAN_CONFIG,
	NL80211_CMD_NAN_MATCH,

	NL80211_CMD_SET_MULTICAST_TO_UNICAST,

	NL80211_CMD_UPDATE_CONNECT_PARAMS,

	/* add new commands above here */

	/* used to define NL80211_CMD_MAX below */
	__NL80211_CMD_AFTER_LAST,
	NL80211_CMD_MAX = __NL80211_CMD_AFTER_LAST - 1
};

```



####  Supplicant执行事件通知

```
adb logcat | findstr  "wlan0:.Event"
 
wpa_supplicant: wlan0: Event DEAUTH (12) received
wpa_supplicant: wlan0: Event CHANNEL_LIST_CHANGED (28) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event ASSOC (0) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event DEAUTH (12) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event CHANNEL_LIST_CHANGED (28) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received
wpa_supplicant: wlan0: Event ASSOC (0) received
wpa_supplicant: wlan0: Event DEAUTH (12) received
wpa_supplicant: wlan0: Event SCAN_STARTED (47) received
wpa_supplicant: wlan0: Event SCAN_RESULTS (3) received


```

```
//  对应打印代码:
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/events.c#3821




void wpa_supplicant_event(void *ctx, enum wpa_event_type event,union wpa_event_data *data)
{
		wpa_dbg(wpa_s, level, "Event %s (%d) received", event_to_string(event), event);
}



```

##### Supplicant_Event_xxx 事件类型集合

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver_common.c#27


const char * event_to_string(enum wpa_event_type event)
{
#define E2S(n) case EVENT_ ## n: return #n
	switch (event) {
	E2S(ASSOC);
	E2S(DISASSOC);
	E2S(MICHAEL_MIC_FAILURE);
	E2S(SCAN_RESULTS);
	E2S(ASSOCINFO);
	E2S(INTERFACE_STATUS);
	E2S(PMKID_CANDIDATE);
	E2S(TDLS);
	E2S(FT_RESPONSE);
	E2S(IBSS_RSN_START);
	E2S(AUTH);
	E2S(DEAUTH);
	E2S(ASSOC_REJECT);
	E2S(AUTH_TIMED_OUT);
	E2S(ASSOC_TIMED_OUT);
	E2S(WPS_BUTTON_PUSHED);
	E2S(TX_STATUS);
	E2S(RX_FROM_UNKNOWN);
	E2S(RX_MGMT);
	E2S(REMAIN_ON_CHANNEL);
	E2S(CANCEL_REMAIN_ON_CHANNEL);
	E2S(RX_PROBE_REQ);
	E2S(NEW_STA);
	E2S(EAPOL_RX);
	E2S(SIGNAL_CHANGE);
	E2S(INTERFACE_ENABLED);
	E2S(INTERFACE_DISABLED);
	E2S(CHANNEL_LIST_CHANGED);
	E2S(INTERFACE_UNAVAILABLE);
	E2S(BEST_CHANNEL);
	E2S(UNPROT_DEAUTH);
	E2S(UNPROT_DISASSOC);
	E2S(STATION_LOW_ACK);
	E2S(IBSS_PEER_LOST);
	E2S(DRIVER_GTK_REKEY);
	E2S(SCHED_SCAN_STOPPED);
	E2S(DRIVER_CLIENT_POLL_OK);
	E2S(EAPOL_TX_STATUS);
	E2S(CH_SWITCH);
	E2S(WNM);
	E2S(CONNECT_FAILED_REASON);
	E2S(DFS_RADAR_DETECTED);
	E2S(DFS_CAC_FINISHED);
	E2S(DFS_CAC_ABORTED);
	E2S(DFS_NOP_FINISHED);
	E2S(SURVEY);
	E2S(SCAN_STARTED);
	E2S(AVOID_FREQUENCIES);
	E2S(NEW_PEER_CANDIDATE);
	E2S(ACS_CHANNEL_SELECTED);
	E2S(DFS_CAC_STARTED);
	E2S(P2P_LO_STOP);
	E2S(BEACON_LOSS);
	E2S(DFS_PRE_CAC_EXPIRED);
	}

	return "UNKNOWN";
#undef E2S
}

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/drivers/driver.h#4009


/**  在方法 wpa_supplicant_event()  被调用了的事件Event 类型分类
 * enum wpa_event_type - Event type for wpa_supplicant_event() calls   
 */
enum wpa_event_type {   // 注释较详细
EVENT_ASSOC,  【0】
EVENT_DISASSOC,
EVENT_MICHAEL_MIC_FAILURE,
EVENT_SCAN_RESULTS,
EVENT_ASSOCINFO,
EVENT_INTERFACE_STATUS,
EVENT_PMKID_CANDIDATE,
EVENT_TDLS,
EVENT_FT_RESPONSE,
EVENT_IBSS_RSN_START,
EVENT_AUTH,
EVENT_DEAUTH,
EVENT_ASSOC_REJECT,
EVENT_AUTH_TIMED_OUT,
EVENT_ASSOC_TIMED_OUT,
EVENT_WPS_BUTTON_PUSHED,
EVENT_TX_STATUS,
EVENT_RX_FROM_UNKNOWN,
EVENT_RX_MGMT,
EVENT_REMAIN_ON_CHANNEL,
EVENT_CANCEL_REMAIN_ON_CHANNEL,
EVENT_RX_PROBE_REQ,
EVENT_NEW_STA,
EVENT_EAPOL_RX,
EVENT_SIGNAL_CHANGE,
EVENT_INTERFACE_ENABLED,
EVENT_INTERFACE_DISABLED,
EVENT_CHANNEL_LIST_CHANGED,
EVENT_INTERFACE_UNAVAILABLE,
EVENT_BEST_CHANNEL,
EVENT_UNPROT_DEAUTH,
EVENT_UNPROT_DISASSOC,
EVENT_STATION_LOW_ACK,
EVENT_IBSS_PEER_LOST,
EVENT_DRIVER_GTK_REKEY,
EVENT_SCHED_SCAN_STOPPED,
EVENT_DRIVER_CLIENT_POLL_OK,
EVENT_EAPOL_TX_STATUS,
EVENT_CH_SWITCH,
EVENT_WNM,
EVENT_CONNECT_FAILED_REASON,
EVENT_DFS_RADAR_DETECTED,
EVENT_DFS_CAC_FINISHED,
EVENT_DFS_CAC_ABORTED,
EVENT_DFS_NOP_FINISHED,   /* Notify that non-occupancy period is over不可占用信道的时间段已经过去 信道开始可用 The channel which was previously unavailable is now available again */
EVENT_SURVEY,
EVENT_SCAN_STARTED,
EVENT_AVOID_FREQUENCIES,
EVENT_NEW_PEER_CANDIDATE,
EVENT_ACS_CHANNEL_SELECTED,
EVENT_DFS_CAC_STARTED,
EVENT_P2P_LO_STOP,
EVENT_BEACON_LOSS,
EVENT_DFS_PRE_CAC_EXPIRED,
}


```

 



### STATE(wpa_supplicant)
```

adb logcat | findstr "wlan0:.State"    //【其中 . 点号是通配符】


wpa_supplicant: wlan0: State: DISCONNECTED -> DISCONNECTED
wpa_supplicant: wlan0: State: DISCONNECTED -> INACTIVE
wpa_supplicant: wlan0: State: INACTIVE -> DISCONNECTED

wpa_supplicant: wlan0: State: DISCONNECTED -> SCANNING
wpa_supplicant: wlan0: State: SCANNING -> AUTHENTICATING
wpa_supplicant: wlan0: State: AUTHENTICATING -> ASSOCIATING
wpa_supplicant: wlan0: State: ASSOCIATING -> DISCONNECTED
wpa_supplicant: wlan0: State: DISCONNECTED -> ASSOCIATING
wpa_supplicant: wlan0: State: ASSOCIATING -> ASSOCIATED
wpa_supplicant: wlan0: State: ASSOCIATED -> 4WAY_HANDSHAKE
wpa_supplicant: wlan0: State: 4WAY_HANDSHAKE -> 4WAY_HANDSHAKE
wpa_supplicant: wlan0: State: 4WAY_HANDSHAKE -> GROUP_HANDSHAKE
wpa_supplicant: wlan0: State: GROUP_HANDSHAKE -> COMPLETED
wpa_supplicant: wlan0: State: COMPLETED -> DISCONNECTED
wpa_supplicant: wlan0: State: DISCONNECTED -> DISCONNECTED
wpa_supplicant: wlan0: State: DISCONNECTED -> ASSOCIATING
wpa_supplicant: wlan0: State: ASSOCIATING -> ASSOCIATED
wpa_supplicant: wlan0: State: ASSOCIATED -> 4WAY_HANDSHAKE
wpa_supplicant: wlan0: State: 4WAY_HANDSHAKE -> 4WAY_HANDSHAKE
wpa_supplicant: wlan0: State: 4WAY_HANDSHAKE -> GROUP_HANDSHAKE
wpa_supplicant: wlan0: State: GROUP_HANDSHAKE -> COMPLETED
wpa_supplicant: wlan0: State: COMPLETED -> DISCONNECTED



```


```
//  打印Log位置
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant.c#819

void wpa_supplicant_set_state(struct wpa_supplicant *wpa_s,enum wpa_states state)
{
...
	enum wpa_states old_state = wpa_s->wpa_state;
 
	wpa_dbg(wpa_s, MSG_DEBUG, "State: %s -> %s",wpa_supplicant_state_txt(wpa_s->wpa_state),wpa_supplicant_state_txt(state));
...
}
 
 
 
const char * wpa_supplicant_state_txt(enum wpa_states state)
{
	switch (state) {
	case WPA_DISCONNECTED:
		return "DISCONNECTED";
	case WPA_INACTIVE:
		return "INACTIVE";
	case WPA_INTERFACE_DISABLED:
		return "INTERFACE_DISABLED";
	case WPA_SCANNING:
		return "SCANNING";
	case WPA_AUTHENTICATING:
		return "AUTHENTICATING";
	case WPA_ASSOCIATING:
		return "ASSOCIATING";
	case WPA_ASSOCIATED:
		return "ASSOCIATED";
	case WPA_4WAY_HANDSHAKE:
		return "4WAY_HANDSHAKE";
	case WPA_GROUP_HANDSHAKE:
		return "GROUP_HANDSHAKE";
	case WPA_COMPLETED:
		return "COMPLETED";
	default:
		return "UNKNOWN";
	}
}


```


#### WPA状态wpa_states集合
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/defs.h#202

enum wpa_states {
WPA_DISCONNECTED,  【0】
WPA_INTERFACE_DISABLED,   /* 网络接口被禁用 */
WPA_INACTIVE,             /* 非活动状态 禁用wpa_supplicant */
WPA_SCANNING,
WPA_AUTHENTICATING,
WPA_ASSOCIATING,
WPA_ASSOCIATED,
WPA_4WAY_HANDSHAKE,
WPA_GROUP_HANDSHAKE,
WPA_COMPLETED   【9】
}

```
<img src="//../zimage/wireless/wifi/07_wifilog/sta_state.jpg" />

#### WPA状态切换图

<img src="//../zimage/wireless/wifi/07_wifilog/wpa_state1.png" />
```
from graphviz import Digraph

f = Digraph('finite_state_machine', filename='fsm.png', format='png')
f.attr(rankdir='TB')

f.attr('node', shape='circle')
f.node('DISCONNECTED',weight='8')
f.node('COMPLETED',weight='10')
f.node('INTERFACE_DISABLED')
f.node('INACTIVE')
f.node('SCANNING')
f.node('AUTHENTICATING')
f.node('ASSOCIATING')
f.node('ASSOCIATED')
f.node('4WAY_HANDSHAKE',weight='8')
f.node('GROUP_HANDSHAKE')


f.edge('DISCONNECTED', 'INACTIVE', label='disable_wpa')
f.edge('INACTIVE', 'DISCONNECTED', label='enable_wpa')
f.edge('DISCONNECTED', 'SCANNING', label='scan' )
f.edge('SCANNING', 'AUTHENTICATING', label='auth')
f.edge('SCANNING', 'DISCONNECTED', label='disconnected')
f.edge('AUTHENTICATING', 'ASSOCIATING', label='associating')
f.edge('AUTHENTICATING', 'DISCONNECTED', label='disconnected')
f.edge('ASSOCIATING', 'DISCONNECTED', label='disconnected')
f.edge('DISCONNECTED', 'ASSOCIATING', label='associating')
f.edge('ASSOCIATING', 'ASSOCIATED', label='associated')
f.edge('ASSOCIATED', '4WAY_HANDSHAKE', label='4(1)')
f.edge('ASSOCIATED', 'DISCONNECTED', label='disconnected')
f.edge('4WAY_HANDSHAKE', '4WAY_HANDSHAKE', label='4(2)')
f.edge('4WAY_HANDSHAKE', 'DISCONNECTED', label='disconnected')
f.edge('4WAY_HANDSHAKE', 'GROUP_HANDSHAKE', label='4(3)')
f.edge('GROUP_HANDSHAKE', 'COMPLETED', label='4(4)')
f.edge('GROUP_HANDSHAKE', 'DISCONNECTED', label='disconnected')
f.edge('COMPLETED', 'DISCONNECTED', label='disconnected')
f.edge('DISCONNECTED', 'DISCONNECTED', label='disconnected')

f.view()
print(f.source)



```

```
digraph finite_state_machine {
	rankdir=TB
	node [shape=circle]
	DISCONNECTED [weight=8]
	COMPLETED [weight=10]
	INTERFACE_DISABLED
	INACTIVE
	SCANNING
	AUTHENTICATING
	ASSOCIATING
	ASSOCIATED
	"4WAY_HANDSHAKE" [weight=8]
	GROUP_HANDSHAKE
	DISCONNECTED -> INACTIVE [label=disable_wpa]
	INACTIVE -> DISCONNECTED [label=enable_wpa]
	DISCONNECTED -> SCANNING [label=scan]
	SCANNING -> AUTHENTICATING [label=auth]
	SCANNING -> DISCONNECTED [label=disconnected]
	AUTHENTICATING -> ASSOCIATING [label=associating]
	AUTHENTICATING -> DISCONNECTED [label=disconnected]
	ASSOCIATING -> DISCONNECTED [label=disconnected]
	DISCONNECTED -> ASSOCIATING [label=associating]
	ASSOCIATING -> ASSOCIATED [label=associated]
	ASSOCIATED -> "4WAY_HANDSHAKE" [label="4(1)"]
	ASSOCIATED -> DISCONNECTED [label=disconnected]
	"4WAY_HANDSHAKE" -> "4WAY_HANDSHAKE" [label="4(2)"]
	"4WAY_HANDSHAKE" -> DISCONNECTED [label=disconnected]
	"4WAY_HANDSHAKE" -> GROUP_HANDSHAKE [label="4(3)"]
	GROUP_HANDSHAKE -> COMPLETED [label="4(4)"]
	GROUP_HANDSHAKE -> DISCONNECTED [label=disconnected]
	COMPLETED -> DISCONNECTED [label=disconnected]
	DISCONNECTED -> DISCONNECTED [label=disconnected]
}


```
##  WifiStateMechine


### EVENT(WifiStateMechine)

#### WifiStateMechine状态机处理事件

```
adb logcat | findstr   "WifiStateMachine:.*State.*CMD.*"

// 查看WifiStateMachine.java中 Framework层状态机变化 以及处理消息的Log
// DefaultState 是最终处理消息 CMD_GET_SUPPORTED_FEATURES的状态

WifiStateMachine:  ConnectedState CMD_GET_SUPPORTED_FEATURES uid=1000 rt=3600136/3600136 0 0
WifiStateMachine:  L2ConnectedState CMD_GET_SUPPORTED_FEATURES uid=1000 rt=3600136/3600136 0 0
WifiStateMachine:  ConnectModeState CMD_GET_SUPPORTED_FEATURES uid=1000 rt=3600136/3600136 0 0
WifiStateMachine:  SupplicantStartedState CMD_GET_SUPPORTED_FEATURES uid=1000 rt=3600136/3600136
WifiStateMachine:  DefaultState CMD_GET_SUPPORTED_FEATURES uid=1000 rt=3600136/3600136 0 0



// DefaultState 是最终处理消息 CMD_GET_SUPPORTED_FEATURES的状态
WifiStateMachine:  ConnectedState CMD_GET_LINK_LAYER_STATS uid=1000 rt=3600144/3600144 0 0
WifiStateMachine:  L2ConnectedState CMD_GET_LINK_LAYER_STATS uid=1000 rt=3600144/3600144 0 0
WifiStateMachine:  ConnectModeState CMD_GET_LINK_LAYER_STATS uid=1000 rt=3600144/3600144 0 0
WifiStateMachine:  SupplicantStartedState CMD_GET_LINK_LAYER_STATS uid=1000 rt=3600144/3600144 0 0



```

```

//  打印Log位置
 Wificontroller.StateMachine.SmHandler.processMsg
http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/com/android/internal/util/StateMachine.java#992


        private final State processMsg(Message msg) {
......
            StateInfo curStateInfo = mStateStack[mStateStackTopIndex];
                while (!curStateInfo.state.processMessage(msg)) {
                    /** Not processed */
                    curStateInfo = curStateInfo.parentStateInfo;  // 溯源状态树
                  } 
......
}


ObtainingIpState       http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#4959
ConnectedState         http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#5237
RoamingState           http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#5094
L2ConnectedState       http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#4695
DisconnectingState     http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#5442 
DisconnectedState      http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#5494
ConnectModeState       http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#3803
DefaultState


 
ObtainingIpState           WifiStateMachine.java {class ObtainingIpState extends State } 
ConnectedState             WifiStateMachine.java {class ConnectedState extends State }
RoamingState               WifiStateMachine.java {class RoamingState extends State }
L2ConnectedState           WifiStateMachine.java {class L2ConnectedState extends State }
DisconnectingState         WifiStateMachine.java {class DisconnectingState extends State }
DisconnectedState          WifiStateMachine.java {class DisconnectedState extends State }
ConnectModeState           WifiStateMachine.java {class ConnectModeState extends State }
DefaultState               WifiStateMachine.java {class ConnectModeState extends State }

http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/com/android/internal/util/State.java
public class State implements IState {
       @Override     public void enter() { xxx }
        @Override    public void exit() { xxx } 
        @Override    public boolean processMessage(Message message) {    logStateAndMessage(message, this);   }

    @Override
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf('$');
        return name.substring(lastDollar + 1);
    }

}


http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#1934
    private void logStateAndMessage(Message message, State state) {
        messageHandlingStatus = 0;
        if (mVerboseLoggingEnabled) {
            logd(" " + state.getClass().getSimpleName() + " " + getLogRecString(message));  //这里输出  L2ConnectedState 
        }
    }

http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#1958
protected String getLogRecString(Message msg) {
  StringBuilder sb = new StringBuilder();
 sb.append(smToString(msg));             //  这里输出  CMD_GET_LINK_LAYER_STATS
 ......
}


http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiStateMachine.java#3553
    private static final SparseArray<String> sSmToString =MessageUtils.findMessageNames(sMessageClasses);
    
    // 反射找到 AsyncChannel.class, WifiStateMachine.class, DhcpClient.class 三个类中以 "CMD_", "EVENT_" 开头命令的变量
    private static final Class[] sMessageClasses = {AsyncChannel.class, WifiStateMachine.class, DhcpClient.class };
    public static final String[] DEFAULT_PREFIXES = {"CMD_", "EVENT_"};


    String smToString(int what) {
        String s = sSmToString.get(what);   // 依据 int 找到对应的变量名称  CMD_xxxx  EVENT_xxxx   
        if (s != null) {
            return s;
        }
        switch (what) {
            case AsyncChannel.CMD_CHANNEL_HALF_CONNECTED:
                s = "AsyncChannel.CMD_CHANNEL_HALF_CONNECTED";
                break;
            case AsyncChannel.CMD_CHANNEL_DISCONNECTED:
                s = "AsyncChannel.CMD_CHANNEL_DISCONNECTED";
                break;
            case WifiP2pServiceImpl.DISCONNECT_WIFI_REQUEST:
                s = "WifiP2pServiceImpl.DISCONNECT_WIFI_REQUEST";
                break;
            case WifiManager.DISABLE_NETWORK:
                s = "WifiManager.DISABLE_NETWORK";
                break;
            case WifiManager.CONNECT_NETWORK:
                s = "CONNECT_NETWORK";
                break;
            case WifiManager.SAVE_NETWORK:
                s = "SAVE_NETWORK";
                break;
            case WifiManager.FORGET_NETWORK:
                s = "FORGET_NETWORK";
                break;
............
            default:
                s = "what:" + Integer.toString(what);
                break;
        }
        return s;
    }
```

<img src="//../zimage/wireless/wifi/07_wifilog/StateMachine.jpg" width = "50%" height="50%"/>


## WifiService

### EVENT(WifiService)

#### WIFI按钮开关事件
```

adb logcat | findstr  "setWifiEnabled"      // 查看WIFI开关打开关闭事件


WifiService: setWifiEnabled: false pid=3155, uid=10080, package=com.android.systemui
WifiService: setWifiEnabled: true pid=3155, uid=10080, package=com.android.systemui
WifiService: setWifiEnabled: false pid=11571, uid=1000, package=com.android.settings
WifiService: setWifiEnabled: true pid=11571, uid=1000, package=com.android.settings
WifiService: setWifiEnabled: false pid=11571, uid=1000, package=com.android.settings
WifiService: setWifiEnabled: true pid=11571, uid=1000, package=com.android.settings
WifiService: setWifiEnabled package=com.android.settings uid=1000 enable=true

```


```
//  打印Log位置
http://androidxref.com/9.0.0_r3/xref/frameworks/opt/net/wifi/service/java/com/android/server/wifi/WifiServiceImpl.java#807
    public synchronized boolean setWifiEnabled(String packageName, boolean enable)   throws RemoteException {
......
   Slog.d(TAG, "setWifiEnabled: " + enable + " pid=" + Binder.getCallingPid()+ ", uid=" + Binder.getCallingUid() + ", package=" + packageName);
.....
}

               
```




