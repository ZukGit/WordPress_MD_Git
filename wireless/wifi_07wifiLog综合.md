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




# wpa_supplicant相关资料

## wpa_supplicant手机内相关文件
```
手机内相关文件

可执行文件:
/vendor/bin/hw/wpa_supplicant
/vendor/bin/wpa_cli

共享库文件
/vendor/lib/libwpa_client.so
/vendor/lib64/libwpa_client.so


配置文件
/vendor/etc/wifi/p2p_supplicant_overlay.conf
/vendor/etc/wifi/wpa_supplicant_overlay.conf
/data/vendor/wifi/wpa/p2p_supplicant.conf
/data/vendor/wifi/wpa/wpa_supplicant.conf
/data/vendor/wifi/wigig_supplicant.conf 
/vendor/etc/wifi/wpa_supplicant.conf
/vendor/etc/wifi/WCNSS_qcom_cfg.ini
/data/vendor/wifi/cnss_diag.conf
/data/vendor/wifi/hostapd/hostapd.conf
/data/vendor/wifi/hostapd/hostapd.accept
/data/vendor/wifi/hostapd/hostapd.deny



数据文件
/data/misc/wifi/WifiConfigStore.xml
/data/misc/wifi/softap.conf


socket文件
/dev/socket/vendor_wpa_wlan0
/data/vendor/misc/cutback/wpa_ctrl_1465-5
/data/vendor/misc/cutback/wpa_ctrl_1465-6
srw-rw---- 1 radio radio 0 2019-02-27 15:17 wpa_ctrl_1470-5
srw-rw---- 1 radio radio 0 2019-02-27 15:17 wpa_ctrl_1470-6
/data/vendor/wifi/hostapd/ctrl/wlan0   
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-1
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-2
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1145-5
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1145-6
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1147-15
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1147-16
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1150-3
srwxrwx--- 1 wifi   wifi 0 2019-02-27 15:17 wlan0
srw-rw---- 1 system wifi 0 2019-02-01 17:34 wpa_ctrl_1142-1
srw-rw---- 1 system wifi 0 2019-02-01 17:34 wpa_ctrl_1142-2


Log文件
foles:/data/vendor/wifi/wlan_logs/cnss_fw_logs_000.txt  
foles:/data/vendor/wifi/wlan_logs/host_driver_logs_000.txt
foles:/data/vendor/wifi/wlan_logs/txrx_pktlog_000.dat



APK应用文件
/system/priv-app/WpaConfigApp/WpaConfigApp.apk


```

## wpa_supplicant初始化文件
```



/device/xxxx/common/etc/init.xxx.rc
service wpa_supplicant /vendor/bin/hw/wpa_supplicant \        #  【 以运行服务的方式 执行二进制可执行文件 wpa_supplicant /vendor/bin/hw/wpa_supplicant 】
    -O/data/vendor/wifi/wpa/sockets  \                        #  【-O  参数指定 ctrl_interface 控制接口工作目录】                         
    -puse_p2p_group_interface=1  \                            #  【-p 配置驱动参数 key=value 】
    -dd \                                                     #  【 -dd 设置log等级2  -ddd   -dddd  Log等级越高 】
    -g@android:vendor_wpa_wlan0                               #  【-g 配置global ctrl_interface 全局控制接口 [ @android:vendor_wpa_wlan0 ] /dev/socket/vendor_wpa_wlan0 】
#   we will start as root and wpa_supplicant will switch to user wifi
#   after setting up the capabilities required for WEXT
#   user wifi
#   group wifi inet keystore
    interface android.hardware.wifi.supplicant@1.0::ISupplicant default #【 安卓O的 HAL 接口 定义 http://androidxref.com/9.0.0_r3/xref/hardware/interfaces/current.txt 接口集合 】
    interface android.hardware.wifi.supplicant@1.1::ISupplicant default
    class main
    socket vendor_wpa_wlan0 dgram 660 wifi wifi  ##【 表示新建一个socket 这个socket的名字是 vendor_wpa_wlan0 属于用户群组 wifi  socket路径为 /dev/socket/vendor_wpa_wlan0】
    disabled     
    oneshot       ## 【表示 只创建只能存在一个相同进程】
------------------------------

/device/xxxx/common/etc/init.xxx.rc
service vendor.xx_supplicant /vendor/bin/hw/wpa_supplicant \  #  【 以运行服务的方式 执行二进制可执行文件 wpa_supplicant /vendor/bin/hw/wpa_supplicant 】
    -iwigig0                                               
    -Dnl80211 
    -c/data/vendor/wifi/wpa/wpa_supplicant.conf \
    -m/data/vendor/wifi/wigig_p2p_supplicant.conf \
    -O/data/vendor/wifi/wigig_sockets  \
    -dd \
    -e/data/vendor/wifi/wigig_entropy.bin -g@android:wpa_wigig0 \
    -S wigigsvc 
    #   we will start as root and wpa_supplicant will switch to user wifi
    #   after setting up the capabilities required for WEXT
    #   user wifi
    #   group wifi inet keystore
    class main
    socket wpa_wigig0 dgram 660 wifi wifi
    disabled
    oneshot

------------------------------


init.mt6795.rc
service wpa_supplicant /system/bin/wpa_supplicant \
        -iwlan0 -Dnl80211 -c/data/misc/wifi/wpa_supplicant.conf -e/data/misc/wifi/entropy.bin -ddd \
        -I/system/etc/wifi/wpa_supplicant_overlay.conf \
        -O/data/misc/wifi/sockets -g@android:wpa_wlan0
        #   we will start as root and wpa_supplicant will switch to user wifi
        socket wpa_wlan0 dgram 660 wifi wifi

解析:
1.启动一个服务，这个服务的名字为wpa_supplicant，这个服务是执行/system/bin/wpa_supplicant二进制可执行文件启动
2.参数说明
-i 用于配置 wpa_supplicant 硬件接口名 指定网络设备接口名  [wlan0]
-D 用于配置 wpa_supplicant 使用的驱动程序[nl80211]
-e 用于配置产生随机数的可执行程序
-I 用于配置当前wpa_supplicant程序的一些feature 以及一些预定义网络所需要的配置文件
-d 增加调试信息
-O 配置 override ctrl_interface parameter for new interfaces
-g 配置global ctrl_interface 全局控制接口 [ @android:wpa_wlan0 ] wpa_wlan0
  socket wpa_wlan0 dgram 660 wifi wifi 表示新建一个socket 这个socket的名字是wpa_wlan0 属于用户wifi
  对应的socket路径为 /dev/socket/wpa_wlan0


ps -g wifi
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           912     1   27136  10140 binder_io+          0 S android.hardware.wifi@1.0-service
wifi          1267     1   19180   6132 ep_poll             0 S wificond
wifi         31519     1 2144672  13008 poll_sche+          0 S wpa_supplicant


```

### /vendor/bin/hw/wpa_supplicant 命令使用说明
```
wpa_supplicant v2.7-devel-9
Copyright (c) 2003-2017, Jouni Malinen <j@w1.fi> and contributors

This software may be distributed under the terms of the BSD license.
See README for more details.

This product includes software developed by the OpenSSL Project
for use in the OpenSSL Toolkit (http://www.openssl.org/)

usage:
  wpa_supplicant [-BddhKLqqtvW] [-P<pid file>] [-g<global ctrl>] \
        [-G<group>] \
        -i<ifname> -c<config file> [-C<ctrl>] [-D<driver>] [-p<driver_param>] \
        [-b<br_ifname>] [-e<entropy file>] \
        [-o<override driver>] [-O<override ctrl>] \
        [-N -i<ifname> -c<conf> [-C<ctrl>] [-D<driver>] \
        [-m<P2P Device config file>] \
        [-p<driver_param>] [-b<br_ifname>] [-I<config file>] ...]

drivers:
  nl80211 = Linux nl80211/cfg80211
options:
  -b = optional bridge interface name      网桥接口名称 -bxxx【wpa_interface.bridge_ifname】
  -B = run daemon in the background       daemonize守护进程  -B  【 wpa_params.daemon】
  -c = Configuration file   配置文件文件路径全名    -c/data/vendor/wifi/wpa/wpa_supplicant.conf   【wpa_interface.confname】
  -C = ctrl_interface parameter (only used if -c is not)
  -d = increase debugging verbosity (-dd even more)  Log打印等级信息  -dd -ddd -dddd   【wpa_params.wpa_debug_level】
  -D = driver name (can be multiple drivers: nl80211,wext)    驱动程序类型   -Dnl80211    -Dwext 【wpa_interface.driver】
  -e = entropy file   用于产生随机数的可执行程序用于加密  -e/data/misc/wifi/entropy.bin    【wpa_params.entropy_file 】
  -g = global ctrl_interface   全局控制接口  -g@android:vendor_wpa_wlan0             【wpa_params.ctrl_interface】
  -G = global ctrl_interface group
  -h = show this help text
  -i = interface name       网卡接口名称    -iwlan0  【wpa_interface.ifname】
  -I = additional configuration file  备份附加的配置文件全名   -I/system/etc/wifi/wpa_supplicant_overlay.conf  【wpa_interface.confanother】
  -K = include keys (passwords, etc.) in debug output
  -L = show license (BSD)
  -m = Configuration file for the P2P Device interface     P2P设备接口配置文件 -m/data/vendor/wifi/wigig_p2p_supplicant.conf  【wpa_params.conf_p2p_dev 】
  -N = start describing new interface
  -o = override driver parameter for new interfaces        
  -O = override ctrl_interface parameter for new interfaces    -O 指定控制接口参数 -O/data/misc/wifi/sockets  【wpa_params.override_ctrl_interface】
  -p = driver parameters   驱动参数键值对 -puse_p2p_group_interface=1  【wpa_interface.driver_param】
  -P = PID file
  -q = decrease debugging verbosity (-qq even less)
  -S = override default HIDL service name
  -t = include timestamp in debug messages
  -v = show version
  -W = wait for a control interface monitor before starting
example:
  wpa_supplicant -Dnl80211 -iwlan0 -c/etc/wpa_supplicant.conf


```

## wpa_supplicant上行接口
```
wpa_supplicant的上行接口 
wpa_supplicant 提供两种由外部模块获取信息的方式：
一种是外部模块通过发送request 命令然后获取response的问答模式，
一种是wpa_supplicant主动向外部发送event事件，由外部模块监听接收。

一般的常用做法是外部模块通过调用wpa_ctrl_open()两次，分别建立两个control interface接口。
一个为ctrl interface，用于发送命令，获取信息。
一个接口调用wpa_ctrl_attach，成为 monitor interface，用于监听接收来自于wpa_supplicant的event事件。此举可以降低通信的耦合性，避免response和event的相互干扰。

主要作用: 通过Unix domainsocket建立一个control interface 的client结点，与作为server的wpa_supplicant结点通信


/data/vendor/wifi/wpa/sockets
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-1
/data/vendor/wifi/wpa/sockets/wpa_ctrl_1142-2

/dev/socket/vendor_wpa_wlan0
```

## wpa_supplicant下行接口
```
WPA_SUPPLICANT 中的NL80211 数据结构  使用netlink与驱动交互
nl80211其实就是利用netlink机制将一些802.11相关的命令和参数发送给驱动去执行
// 发送netlink消息
ret = send_and_recv_msgs(drv, msg, NULL, NULL);


```



# wpa_supplicant初始化过程分析
```
main 函数位置
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/main.c#181



int main(int argc, char *argv[])
{
	int c, i;
	struct wpa_interface *ifaces, *iface;
	int iface_count, exitcode = -1;
	struct wpa_params params;
	struct wpa_global *global;

	if (os_program_init())
		return -1;

	os_memset(&params, 0, sizeof(params));
	params.wpa_debug_level = MSG_INFO;

	iface = ifaces = os_zalloc(sizeof(struct wpa_interface));
	if (ifaces == NULL)
		return -1;
	iface_count = 1;

	wpa_supplicant_fd_workaround(1);

	for (;;) {
		c = getopt(argc, argv,
			   "b:Bc:C:D:de:f:g:G:hi:I:KLMm:No:O:p:P:qsTtuvW");
		if (c < 0)
			break;
		switch (c) {
		case 'b':
			iface->bridge_ifname = optarg;
			break;
		case 'B':
			params.daemonize++;
			break;
		case 'm':
			params.conf_p2p_dev = optarg;
			break;
		case 'e':
			params.entropy_file = optarg;
			break;
		case 'g':
			params.ctrl_interface = optarg;
			break;
		case 'p':
			iface->driver_param = optarg;
			break;
		case 'O':
			params.override_ctrl_interface = optarg;
			break;
		case 'i':
			iface->ifname = optarg;
			break;
		case 'I':
			iface->confanother = optarg;
			break;
		case 'D':
			iface->driver = optarg;
			break;
		case 'd':
			break;
			params.wpa_debug_level--;  
		case 'c':
			iface->confname = optarg;
			break;
 // ...................   循环查找命令行输入参数

}


```
#  WIFI数据结构


## wpa_supplicant(struct)

http://w1.fi/wpa_supplicant/devel/classes.html


### wpa_interface
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#53
http://w1.fi/wpa_supplicant/devel/structwpa__interface.html

/**  为函数wpa_supplicant_add_iface() 准备基础数据的 数据结构
 * struct wpa_interface - Parameters for wpa_supplicant_add_iface()
 */
struct wpa_interface {

	const char *confname;   // 配置文件路径名称    -c/data/vendor/wifi/wpa/wpa_supplicant.conf  -c 参数指明     
	const char *confanother;  // 备份的配置文件路径名称   -I参数指定   -I/system/etc/wifi/wpa_supplicant_overlay.conf 
	const char *ctrl_interface;  // 全局控制接口 -g参数指定  控制接口  .rc文件执行命令如果没有设置-g 参数 , 该值默认从配置文件读取
	const char *driver;           //  WPA适配的驱动程序类型 -D 参数指定    -Dnl80211  
	const char *driver_param;      // WPA 适配驱动程序键值对参数 -p参数指定 
	const char *ifname;                 //  	Interface name.网卡接口名称  -i 参数指定  -iwlan0
	const char *bridge_ifname;       //  -b 参数指定  桥接接口 主要用于接收EAP认证帧 -b
	int p2p_mgmt;                    // 指示是否必须为此接口调用 wpas_p2p_init()
};

```

### wpa_params
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#126
http://w1.fi/wpa_supplicant/devel/structwpa__params.html




/**
 * struct wpa_params - Parameters for wpa_supplicant_init()
 */
struct wpa_params {
int 	     daemonize                       // Run wpa_supplicant in the background.       开关用来设置 wpa_supplicant作为守护进程运行    -B 参数指定
int 	     wait_for_monitor               // Wait for a monitor program before starting.  开关控制   启动之前等待 监视程序运行 wpa才继续运行
char * 	     pid_file                       //Path to a PID (process ID) file. More...      WPA进程文件全路径名
int 	     wpa_debug_level                // Debugging verbosity level (e.g., MSG_INFO)   WPA的LOG等级  1-高  2-中  3-低
int 	     wpa_debug_show_keys            // Whether keying material is included in debug. Key配置信息是否打印开关  仅用于开发调试 用户版本必须关闭
int 	     wpa_debug_timestamp            // Whether to include timestamp in debug messages.  时间戳是否显示在Log中的 开关
char * 	     ctrl_interface                // Global ctrl_iface path/parameter.  全局控制接口 ( 单个 )   -g @android:vendor_wpa_wlan0   
char * 	     ctrl_interface_group          //	Global ctrl_iface group.   全局控制接口( 群组 )
int 	     dbus_ctrl_interface           // Enable the DBus control interface.   是否开启DBus 控制接口 开关
const char*  wpa_debug_file_path           // Path of debug file or NULL to use stdout.   -f 参数指定  Log文件全路径
int 	     wpa_debug_syslog              // Enable log output through syslog.   通过 syslog系统Log日志 来输出WPA的Log 开关
int 	     wpa_debug_tracing              // Enable log output through Linux tracing.  通过 Linux tracing 输出 WPA的LOG 开关
char *       override_driver                //Optional driver parameter override. More...  可选的driver驱动参数覆盖  key-value覆盖
char *       override_ctrl_interface      // Optional ctrl_interface override. More...  可选的 -O参数指定 ctrl_interface 控制接口参数覆盖   key-value覆盖 
char *       entropy_file                 // Optional entropy file. More... 用于生产随机数字的二进制执行程序全路径  用于加密  -e 参数指定 -e/data/misc/wifi/entropy.bin
char *       conf_p2p_dev                 //Configuration file P2P Device configuration parameters  WLAN P2P的配置文件全路径  -m 参数指定 -m/data/vendor/wifi/wigig_p2p_supplicant.conf 
}


```


### wpa_global
```
Internal, global data for all wpa_supplicant interfaces
内部的全局的所有数据  对于所有 wpa_supplicant 接口


http://w1.fi/wpa_supplicant/devel/structwpa__global.html
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#264

/**
 * struct wpa_global - Internal, global data for all %wpa_supplicant interfaces
 *
 * This structure is initialized by calling wpa_supplicant_init() when starting
 * %wpa_supplicant.
 */
struct wpa_global {
	struct wpa_supplicant *ifaces;
	struct wpa_params params;
	struct ctrl_iface_global_priv *ctrl_iface;
	struct wpas_dbus_priv *dbus;
	struct wpas_hidl_priv *hidl;
	void **drv_priv;
	size_t drv_count;
	struct os_time suspend_time;
	struct p2p_data *p2p;
	struct wpa_supplicant *p2p_init_wpa_s;
	struct wpa_supplicant *p2p_group_formation;
	struct wpa_supplicant *p2p_invite_group;
	u8 p2p_dev_addr[ETH_ALEN];
	struct os_reltime p2p_go_wait_client;
	struct dl_list p2p_srv_bonjour; /* struct p2p_srv_bonjour */
	struct dl_list p2p_srv_upnp; /* struct p2p_srv_upnp */
	int p2p_disabled;
	int cross_connection;
	struct wpa_freq_range_list p2p_disallow_freq;
	struct wpa_freq_range_list p2p_go_avoid_freq;
	enum wpa_conc_pref {
		WPA_CONC_PREF_NOT_SET,
		WPA_CONC_PREF_STA,
		WPA_CONC_PREF_P2P
	} conc_pref;
	unsigned int p2p_per_sta_psk:1;
	unsigned int p2p_fail_on_wps_complete:1;
	unsigned int p2p_24ghz_social_channels:1;
	unsigned int pending_p2ps_group:1;
	unsigned int pending_group_iface_for_p2ps:1;
	unsigned int pending_p2ps_group_freq;
	int wifi_display;
#define MAX_WFD_SUBELEMS 12
	struct wpabuf *wfd_subelem[MAX_WFD_SUBELEMS];
#endif 

	struct psk_list_entry *add_psk; /* From group formation */
};


```


<img src="//../zimage/wireless/wifi/07_wifilog/wpa_global.png" width = "50%" height="50%"/>

#### wpa_supplicant
```
Internal data for wpa_supplicant interface
http://w1.fi/wpa_supplicant/devel/structwpa__supplicant.html




struct wpa_global * 	global
struct wpa_radio * 	radio
struct dl_list 	radio_list
struct wpa_supplicant * 	parent
struct wpa_supplicant * 	next
struct l2_packet_data * 	l2
struct l2_packet_data * 	l2_br
unsigned char 	own_addr [ETH_ALEN]
unsigned char 	perm_addr [ETH_ALEN]
char 	ifname [100]
char 	bridge_ifname [16]
char * 	confname
char * 	confanodther
struct wpa_config * 	conf
int 	countermeasures
struct os_reltime 	last_michael_mic_error
u8 	bssid [ETH_ALEN]
u8 	pending_bssid [ETH_ALEN]
int 	reassociate
int 	reassoc_same_bss
int 	disconnected
struct wpa_ssid * 	current_ssid
struct wpa_ssid * 	last_ssid
struct wpa_bss * 	current_bss
int 	ap_ies_from_associnfo
unsigned int 	assoc_freq
int 	pairwise_cipher
int 	group_cipher
int 	key_mgmt
int 	wpa_proto
int 	mgmt_group_cipher
void * 	drv_priv
void * 	global_drv_priv
u8 * 	bssid_filter
size_t 	bssid_filter_count
u8 * 	disallow_aps_bssid
size_t 	disallow_aps_bssid_count
struct wpa_ssid_value * 	disallow_aps_ssid
size_t 	disallow_aps_ssid_count
enum set_band 	setband
struct wpa_ssid * 	next_ssid
int 	prev_scan_wildcard
struct wpa_ssid * 	prev_scan_ssid
struct wpa_ssid * 	prev_sched_ssid
int 	sched_scan_timeout
int 	sched_scan_interval
int 	first_sched_scan
int 	sched_scan_timed_out
void(* 	scan_res_handler )(struct wpa_supplicant *wpa_s, struct wpa_scan_results *scan_res)
struct dl_list 	bss
struct dl_list 	bss_id
size_t 	num_bss
unsigned int 	bss_update_idx
unsigned int 	bss_next_id
struct wpa_bss ** 	last_scan_res
unsigned int 	last_scan_res_used
unsigned int 	last_scan_res_size
struct os_reltime 	last_scan
const struct wpa_driver_ops * 	driver
int 	interface_removed
struct wpa_sm * 	wpa
struct eapol_sm * 	eapol
struct ctrl_iface_priv * 	ctrl_iface
enum wpa_states 	wpa_state
struct wpa_radio_work * 	scan_work
int 	scanning
int 	sched_scanning
int 	new_connection
int 	eapol_received
struct scard_data * 	scard
char 	imsi [20]
int 	mnc_len
unsigned char 	last_eapol_src [ETH_ALEN]
unsigned int 	keys_cleared
struct wpa_blacklist * 	blacklist
int 	extra_blacklist_count
 	Sum of blacklist counts after last connection. More...
enum wpa_supplicant::scan_req_type 	scan_req
enum wpa_supplicant::scan_req_type 	last_scan_req
enum wpa_states 	scan_prev_wpa_state
struct os_reltime scan_trigger_time 	scan_start_time
struct os_reltime 	scan_min_time
int 	scan_runs
int * 	next_scan_freqs
int * 	manual_scan_freqs
int * 	manual_sched_scan_freqs
unsigned int 	manual_scan_passive:1
unsigned int 	manual_scan_use_id:1
unsigned int 	manual_scan_only_new:1
unsigned int 	own_scan_requested:1
unsigned int 	own_scan_running:1
unsigned int 	clear_driver_scan_cache:1
unsigned int 	manual_scan_id
int 	scan_interval
int 	normal_scans
int 	scan_for_connection
int 	scan_id [MAX_SCAN_ID]
unsigned int 	scan_id_count
struct wpa_ssid_value * 	ssids_from_scan_req
unsigned int 	num_ssids_from_scan_req
u64 	drv_flags
unsigned int 	drv_enc
unsigned int 	drv_smps_modes
unsigned int 	drv_rrm_flags
unsigned int 	probe_resp_offloads
const u8 * 	extended_capa
const u8 * 	extended_capa_mask
unsigned int 	extended_capa_len
int 	max_scan_ssids
int 	max_sched_scan_ssids
int 	sched_scan_supported
unsigned int 	max_match_sets
unsigned int 	max_remain_on_chan
unsigned int 	max_stations
int 	pending_mic_error_report
int 	pending_mic_error_pairwise
int 	mic_errors_seen
struct wps_context * 	wps
int 	wps_success
struct wps_er * 	wps_er
unsigned int 	wps_run
struct os_reltime 	wps_pin_start_time
int 	blacklist_cleared
struct wpabuf * 	pending_eapol_rx
struct os_reltime 	pending_eapol_rx_time
u8 	pending_eapol_rx_src [ETH_ALEN]
unsigned int 	last_eapol_matches_bssid:1
unsigned int 	eap_expected_failure:1
unsigned int 	reattach:1
unsigned int 	mac_addr_changed:1
unsigned int 	added_vif:1
struct os_reltime 	last_mac_addr_change
int 	last_mac_addr_style
struct ibss_rsn * 	ibss_rsn
int 	set_sta_uapsd
int 	sta_uapsd
int 	set_ap_uapsd
int 	ap_uapsd
struct hostapd_iface * 	ifmsh
unsigned int 	off_channel_freq
struct wpabuf * 	pending_action_tx
u8 	pending_action_src [ETH_ALEN]
u8 	pending_action_dst [ETH_ALEN]
u8 	pending_action_bssid [ETH_ALEN]
unsigned int 	pending_action_freq
int 	pending_action_no_cck
int 	pending_action_without_roc
unsigned int 	pending_action_tx_done:1
void(* 	pending_action_tx_status_cb )(struct wpa_supplicant *wpa_s, unsigned int freq, const u8 *dst, const u8 *src, const u8 *bssid, const u8 *data, size_t data_len, enum offchannel_send_action_result result)
unsigned int 	roc_waiting_drv_freq
int 	action_tx_wait_time
int 	p2p_mgmt
struct p2p_go_neg_results * 	go_params
int 	create_p2p_iface
u8 	pending_interface_addr [ETH_ALEN]
char 	pending_interface_name [100]
int 	pending_interface_type
int 	p2p_group_idx
unsigned int 	pending_listen_freq
unsigned int 	pending_listen_duration
enum wpa_supplicant:: { ... }  	p2p_group_interface
struct p2p_group * 	p2p_group
int 	p2p_long_listen
char 	p2p_pin [10]
int 	p2p_wps_method
u8 	p2p_auth_invite [ETH_ALEN]
int 	p2p_sd_over_ctrl_iface
int 	p2p_in_provisioning
int 	p2p_in_invitation
int 	p2p_invite_go_freq
int 	pending_invite_ssid_id
int 	show_group_started
u8 	go_dev_addr [ETH_ALEN]
int 	pending_pd_before_join
u8 	pending_join_iface_addr [ETH_ALEN]
u8 	pending_join_dev_addr [ETH_ALEN]
int 	pending_join_wps_method
u8 	p2p_join_ssid [SSID_MAX_LEN]
size_t 	p2p_join_ssid_len
int 	p2p_join_scan_count
int 	auto_pd_scan_retry
int 	force_long_sd
u16 	pending_pd_config_methods
enum wpa_supplicant:: { ... }  	pending_pd_use
int 	cross_connect_disallowed
int 	cross_connect_enabled
int 	cross_connect_in_use
char 	cross_connect_uplink [100]
unsigned int 	p2p_auto_join:1
unsigned int 	p2p_auto_pd:1
unsigned int 	p2p_persistent_group:1
unsigned int 	p2p_fallback_to_go_neg:1
unsigned int 	p2p_pd_before_go_neg:1
unsigned int 	p2p_go_ht40:1
unsigned int 	p2p_go_vht:1
unsigned int 	user_initiated_pd:1
unsigned int 	p2p_go_group_formation_completed:1
unsigned int 	group_formation_reported:1
unsigned int 	waiting_presence_resp
int 	p2p_first_connection_timeout
unsigned int 	p2p_nfc_tag_enabled:1
unsigned int 	p2p_peer_oob_pk_hash_known:1
unsigned int 	p2p_disable_ip_addr_req:1
unsigned int 	p2ps_method_config_any:1
unsigned int 	p2p_cli_probe:1
int 	p2p_persistent_go_freq
int 	p2p_persistent_id
int 	p2p_go_intent
int 	p2p_connect_freq
struct os_reltime 	p2p_auto_started
struct wpa_ssid * 	p2p_last_4way_hs_fail
struct wpa_radio_work * 	p2p_scan_work
struct wpa_radio_work * 	p2p_listen_work
struct wpa_radio_work * 	p2p_send_action_work
u16 	p2p_oob_dev_pw_id
struct wpabuf * 	p2p_oob_dev_pw
u8 	p2p_peer_oob_pubkey_hash [WPS_OOB_PUBKEY_HASH_LEN]
u8 	p2p_ip_addr_info [3 *4]
int * 	p2p_group_common_freqs
unsigned int 	p2p_group_common_freqs_num
u8 	p2ps_join_addr [ETH_ALEN]
struct wpa_ssid * 	bgscan_ssid
const struct bgscan_ops * 	bgscan
void * 	bgscan_priv
const struct autoscan_ops * 	autoscan
struct wpa_driver_scan_params * 	autoscan_params
void * 	autoscan_priv
struct wpa_ssid * 	connect_without_scan
struct wps_ap_info * 	wps_ap
size_t 	num_wps_ap
int 	wps_ap_iter
int 	after_wps
int 	known_wps_freq
unsigned int 	wps_freq
int 	wps_fragment_size
int 	auto_reconnect_disabled
int 	best_24_freq
int 	best_5_freq
int 	best_overall_freq
struct gas_query * 	gas
unsigned int 	drv_capa_known
struct {
   struct hostapd_hw_modes *   modes
   u16   num_modes
   u16   flags
} 	hw
enum wpa_supplicant::local_hw_capab 	hw_capab
int 	pno
int 	pno_sched_pending
int 	disconnect_reason
struct ext_password_data * 	ext_pw
struct wpabuf * 	last_gas_resp
struct wpabuf * 	prev_gas_resp
u8 	last_gas_addr [ETH_ALEN]
u8 	prev_gas_addr [ETH_ALEN]
u8 	last_gas_dialog_token
u8 	prev_gas_dialog_token
unsigned int 	no_keep_alive:1
unsigned int 	ext_mgmt_frame_handling:1
unsigned int 	ext_eapol_frame_io:1
unsigned int 	wmm_ac_supported:1
unsigned int 	ext_work_in_progress:1
unsigned int 	own_disconnect_req:1
unsigned int 	mac_addr_rand_supported
unsigned int 	mac_addr_rand_enable
u8 * 	mac_addr_scan
u8 * 	mac_addr_sched_scan
u8 * 	mac_addr_pno
unsigned int 	num_multichan_concurrent
struct wpa_radio_work * 	connect_work
unsigned int 	ext_work_id
struct wpabuf * 	vendor_elem [NUM_VENDOR_ELEM_FRAMES]
struct wmm_ac_assoc_data * 	wmm_ac_assoc_info
struct wmm_tspec_element * 	tspecs [WMM_AC_NUM][TS_DIR_IDX_COUNT]
struct wmm_ac_addts_request * 	addts_request
u8 	wmm_ac_last_dialog_token
struct wmm_tspec_element * 	last_tspecs
u8 	last_tspecs_count
struct rrm_data 	rrm

```

<img src="//../zimage/wireless/wifi/07_wifilog/wpa_supplicant.png" width = "50%" height="50%"/>






#### wpa_driver_ops

```
Driver interface API definition
与驱动交互的API接口
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/hostapd/src/drivers/driver.h#2061
http://w1.fi/wpa_supplicant/devel/structwpa__driver__ops.html



const char * 	name    // Name of the driver interface.
const char * 	desc    // One line description of the driver interface.
int(* 	get_bssid )(void *priv, u8 *bssid)  //Get the current BSSID. More...
int(* 	get_ssid )(void *priv, u8 *ssid)  // Get the current SSID. More...

// Configure encryption key. More...
int(* 	set_key )(const char *ifname, void *priv, enum wpa_alg alg, const u8 *addr, int key_idx, int set_tx, const u8 *seq, size_t seq_len, const u8 *key, size_t key_len)

// Initialize driver interface. More...
void *(* 	init )(void *ctx, const char *ifname)

void(* 	deinit )(void *priv)  // Deinitialize driver interface. More...

int(* 	set_param )(void *priv, const char *param)  // Set driver configuration parameters. More...

int(* 	set_countermeasures )(void *priv, int enabled)  // Enable/disable TKIP countermeasures. More...

int(* 	deauthenticate )(void *priv, const u8 *addr, int reason_code)  // Request driver to deauthenticate. More...

int(* 	associate )(void *priv, struct wpa_driver_associate_params *params)  //Request driver to associate. More...

int(* 	add_pmkid )(void *priv, const u8 *bssid, const u8 *pmkid)  // Add PMKSA cache entry to the driver. More...

int(* 	remove_pmkid )(void *priv, const u8 *bssid, const u8 *pmkid)  // Remove PMKSA cache entry to the driver. More...

int(* 	flush_pmkid )(void *priv)  // Flush PMKSA cache. More...

int(* 	get_capa )(void *priv, struct wpa_driver_capa *capa)  // Get driver capabilities. More...

void(* 	poll )(void *priv)   // Poll driver for association information. More...

const char *(* 	get_ifname )(void *priv)  // Get interface name. More...

const u8 *(* 	get_mac_addr )(void *priv)   // Get own MAC address. More...

int(* 	set_operstate )(void *priv, int state)   // Sets device operating state to DORMANT or UP. More...

//MLME-SETPROTECTION.request primitive. More...
int(* 	mlme_setprotection )(void *priv, const u8 *addr, int protect_type, int key_type)

//Get hardware support data (channels and rates) More...
struct hostapd_hw_modes *(* 	get_hw_feature_data )(void *priv, u16 *num_modes, u16 *flags)

//Send management frame from MLME. More...
int(* 	send_mlme )(void *priv, const u8 *data, size_t data_len, int noack, unsigned int freq)

//Update FT (IEEE 802.11r) IEs. More...
int(* 	update_ft_ies )(void *priv, const u8 *md, const u8 *ies, size_t ies_len)

//Fetch the latest scan results. More...
struct wpa_scan_results *(* 	get_scan_results2 )(void *priv)


// Set country. More...
int(* 	set_country )(void *priv, const char *alpha2)

// Get country. More...
int(* 	get_country )(void *priv, char *alpha2)


// Global driver initialization. More...
void *(* 	global_init )(void)

// Global driver deinitialization. More...
void(* 	global_deinit )(void *priv)

//Initialize driver interface (with global data) More...
void *(* 	init2 )(void *ctx, const char *ifname, void *global_priv)

//Get information about available interfaces. More...
struct wpa_interface_info *(* 	get_interfaces )(void *global_priv)

//Request the driver to initiate scan. More...
int(* 	scan2 )(void *priv, struct wpa_driver_scan_params *params)

//Request driver to authenticate. More...
int(* 	authenticate )(void *priv, struct wpa_driver_auth_params *params)

//Set Beacon and Probe Response information for AP mode. More...
int(* 	set_ap )(void *priv, struct wpa_driver_ap_params *params)

//Set ACL in AP mode. More...
int(* 	set_acl )(void *priv, struct hostapd_acl_params *params)

//Initialize driver interface (hostapd only) More...
void *(* 	hapd_init )(struct hostapd_data *hapd, struct wpa_init_params *params)

// Deinitialize driver interface (hostapd only) More...
void(* 	hapd_deinit )(void *priv)

// Enable/disable IEEE 802.1X support (AP only) More...
int(* 	set_ieee8021x )(void *priv, struct wpa_bss_params *params)

// Enable/disable privacy (AP only) More...
int(* 	set_privacy )(void *priv, int enabled)

// Fetch the current TSC/packet number (AP only) More...
int(* 	get_seqnum )(const char *ifname, void *priv, const u8 *addr, int idx, u8 *seq)

// Flush all association stations (AP only) More...
int(* 	flush )(void *priv)

// Add IEs into Beacon/Probe Response frames (AP) More...
int(* 	set_generic_elem )(void *priv, const u8 *elem, size_t elem_len)

// Fetch station data. More...
int(* 	read_sta_data )(void *priv, struct hostap_sta_driver_data *data, const u8 *addr)

// Send an EAPOL packet (AP only) More...
int(* 	hapd_send_eapol )(void *priv, const u8 *addr, const u8 *data, size_t data_len, int encrypt, const u8 *own_addr, u32 flags)

//Deauthenticate a station (AP only) More...
int(* 	sta_deauth )(void *priv, const u8 *own_addr, const u8 *addr, int reason)

// Disassociate a station (AP only) More...
int(* 	sta_disassoc )(void *priv, const u8 *own_addr, const u8 *addr, int reason)

//Remove a station entry (AP only) More...
int(* 	sta_remove )(void *priv, const u8 *addr)

// Get the current SSID (AP only) More...
int(* 	hapd_get_ssid )(void *priv, u8 *buf, int len)

// Set SSID (AP only) More...
int(* 	hapd_set_ssid )(void *priv, const u8 *buf, int len)

// Enable/disable TKIP countermeasures (AP) More...
int(* 	hapd_set_countermeasures )(void *priv, int enabled)

//Add a station entry. More...
int(* 	sta_add )(void *priv, struct hostapd_sta_add_params *params)

// Get station inactivity duration (AP only) More...
int(* 	get_inact_sec )(void *priv, const u8 *addr)

// Clear station statistics (AP only) More...
int(* 	sta_clear_stats )(void *priv, const u8 *addr)

//Set channel/frequency (AP only) More...
int(* 	set_freq )(void *priv, struct hostapd_freq_params *freq)

// Set RTS threshold. More...
int(* 	set_rts )(void *priv, int rts)

//Set fragmentation threshold. More...
int(* 	set_frag )(void *priv, int frag)

// Set station flags (AP only) More...
int(* 	sta_set_flags )(void *priv, const u8 *addr, unsigned int total_flags, unsigned int flags_or, unsigned int flags_and)

//Set TX queue parameters. More...
int(* 	set_tx_queue_params )(void *priv, int queue, int aifs, int cw_min, int cw_max, int burst_time)


// Add a virtual interface. More...
int(* 	if_add )(void *priv, enum wpa_driver_if_type type, const char *ifname, const u8 *addr, void *bss_ctx, void **drv_priv, char *force_ifname, u8 *if_addr, const char *bridge, int use_existing)

// Remove a virtual interface. More...
int(* 	if_remove )(void *priv, enum wpa_driver_if_type type, const char *ifname)

// Bind a station into a specific interface (AP only) More...
int(* 	set_sta_vlan )(void *priv, const u8 *addr, const char *ifname, int vlan_id)

// Optional commit changes handler (AP only) More...
int(* 	commit )(void *priv)

//Send an ethernet packet (AP only) More...
int(* 	send_ether )(void *priv, const u8 *dst, const u8 *src, u16 proto, const u8 *data, size_t data_len)

//Notification of RADIUS ACL change. More...
int(* 	set_radius_acl_auth )(void *priv, const u8 *mac, int accepted, u32 session_timeout)

// Notification of RADIUS ACL expiration. More...
int(* 	set_radius_acl_expire )(void *priv, const u8 *mac)

// Add WPS IE(s) into Beacon/Probe Response frames (AP) More...
int(* 	set_ap_wps_ie )(void *priv, const struct wpabuf *beacon, const struct wpabuf *proberesp, const struct wpabuf *assocresp)

// Set IEEE 802.1X Supplicant Port status. More...
int(* 	set_supp_port )(void *priv, int authorized)

// Bind a station into a 4-address WDS (AP only) More...
int(* 	set_wds_sta )(void *priv, const u8 *addr, int aid, int val, const char *bridge_ifname, char *ifname_wds)

// Transmit an Action frame. More...
int(* 	send_action )(void *priv, unsigned int freq, unsigned int wait, const u8 *dst, const u8 *src, const u8 *bssid, const u8 *data, size_t data_len, int no_cck)

// Cancel action frame TX wait. More...
void(* 	send_action_cancel_wait )(void *priv)

//Remain awake on a channel. More...
int(* 	remain_on_channel )(void *priv, unsigned int freq, unsigned int duration)

// Cancel remain-on-channel operation. More...
int(* 	cancel_remain_on_channel )(void *priv)

// Request Probe Request frames to be indicated. More...
int(* 	probe_req_report )(void *priv, int report)

// Deinitialize AP mode. More...
int(* 	deinit_ap )(void *priv)

// Deinitialize P2P client mode. More...
int(* 	deinit_p2p_cli )(void *priv)

//Notification on system suspend/hibernate event. More...
void(* 	suspend )(void *priv)

// Notification on system resume/thaw event. More...
void(* 	resume )(void *priv)

// Set signal monitoring parameters. More...
int(* 	signal_monitor )(void *priv, int threshold, int hysteresis)

// Send IEEE 802.11 frame (testing use only) More...
int(* 	send_frame )(void *priv, const u8 *data, size_t data_len, int encrypt)

// Get current Notice of Absence attribute payload. More...
int(* 	get_noa )(void *priv, u8 *buf, size_t buf_len)


// Set Notice of Absence parameters for GO (testing) More...
int(* 	set_noa )(void *priv, u8 count, int start, int duration)

//Set P2P power save options. More...
int(* 	set_p2p_powersave )(void *priv, int legacy_ps, int opp_ps, int ctwindow)

// Enable/disable aggregation. More...
int(* 	ampdu )(void *priv, int ampdu)

//Get physical radio name for the device. More...
const char *(* 	get_radio_name )(void *priv)

//for sending TDLS management packets More...
int(* 	send_tdls_mgmt )(void *priv, const u8 *dst, u8 action_code, u8 dialog_token, u16 status_code, u32 peer_capab, int initiator, const u8 *buf, size_t len)

// Ask the driver to perform high-level TDLS operations. More...
int(* 	tdls_oper )(void *priv, enum tdls_oper oper, const u8 *peer)

// Notify driver of the WNM frame reception. More...
int(* 	wnm_oper )(void *priv, enum wnm_oper oper, const u8 *peer, u8 *buf, u16 *buf_len)

// Set QoS Map. More...
int(* 	set_qos_map )(void *priv, const u8 *qos_map_set, u8 qos_map_set_len)

// Add a neigh to the bridge ip neigh table. More...
int(* 	br_add_ip_neigh )(void *priv, u8 version, const u8 *ipaddr, int prefixlen, const u8 *addr)

// Remove a neigh from the bridge ip neigh table. More...
int(* 	br_delete_ip_neigh )(void *priv, u8 version, const u8 *ipaddr)


// Set a bridge port attribute. More...
int(* 	br_port_set_attr )(void *priv, enum drv_br_port_attr attr, unsigned int val)

// Set a bridge network parameter. More...
int(* 	br_set_net_param )(void *priv, enum drv_br_net_param param, unsigned int val)

// Set wake-on-wireless triggers. More...
int(* 	set_wowlan )(void *priv, const struct wowlan_triggers *triggers)

//Get current connection information. More...
int(* 	signal_poll )(void *priv, struct wpa_signal_info *signal_info)

// Set authentication algorithm(s) for static WEP. More...
int(* 	set_authmode )(void *priv, int authmode)

// Execute vendor specific command. More...
int(* 	vendor_cmd )(void *priv, unsigned int vendor_id, unsigned int subcmd, const u8 *data, size_t data_len, struct wpabuf *buf)

// Set rekey information. More...
void(* 	set_rekey_info )(void *priv, const u8 *kek, size_t kek_len, const u8 *kck, size_t kck_len, const u8 *replay_ctr)

// Station association indication. More...
int(* 	sta_assoc )(void *priv, const u8 *own_addr, const u8 *addr, int reassoc, u16 status, const u8 *ie, size_t len)

// Station authentication indication. More...
int(* 	sta_auth )(void *priv, const u8 *own_addr, const u8 *addr, u16 seq, u16 status, const u8 *ie, size_t len)

// Add traffic stream. More...
int(* 	add_tspec )(void *priv, const u8 *addr, u8 *tspec_ie, size_t tspec_ielen)

// Add a station node in the driver. More...
int(* 	add_sta_node )(void *priv, const u8 *addr, u16 auth_alg)


// Request the driver to initiate scheduled scan. More...
int(* 	sched_scan )(void *priv, struct wpa_driver_scan_params *params, u32 interval)

// Request the driver to stop a scheduled scan. More...
int(* 	stop_sched_scan )(void *priv)

// Probe (null data or such) the given station. More...
void(* 	poll_client )(void *priv, const u8 *own_addr, const u8 *addr, int qos)

// Disable/enable radio. More...
int(* 	radio_disable )(void *priv, int disabled)

// Announce channel switch and migrate the GO to the given frequency. More...
int(* 	switch_channel )(void *priv, struct csa_settings *settings)

// Add traffic stream. More...
int(* 	add_tx_ts )(void *priv, u8 tsid, const u8 *addr, u8 user_prio, u16 admitted_time)

// Delete traffic stream. More...
int(* 	del_tx_ts )(void *priv, u8 tsid, const u8 *addr)

// Enable channel-switching with TDLS peer. More...
int(* 	tdls_enable_channel_switch )(void *priv, const u8 *addr, u8 oper_class, const struct hostapd_freq_params *params)

//Disable channel switching with TDLS peer. More...
int(* 	tdls_disable_channel_switch )(void *priv, const u8 *addr)

// Listen for radar interference on the channel. More...
int(* 	start_dfs_cac )(void *priv, struct hostapd_freq_params *freq)

// Removes beacon from AP. More...
int(* 	stop_ap )(void *priv)

// Retrieve survey data. More...
int(* 	get_survey )(void *priv, unsigned int freq)

// Get driver interface status information. More...
int(* 	status )(void *priv, char *buf, size_t buflen)

// Set roaming policy for driver-based BSS selection. More...
int(* 	roaming )(void *priv, int allowed, const u8 *bssid)

// Set MAC address. More...
int(* 	set_mac_addr )(void *priv, const u8 *addr)

// Driver specific initialization for mesh. More...
int(* 	init_mesh )(void *priv)

// Join a mesh network. More...
int(* 	join_mesh )(void *priv, struct wpa_driver_mesh_join_params *params)

// Leave a mesh network. More...
int(* 	leave_mesh )(void *priv)

// Automatically select channel. More...
int(* 	do_acs )(void *priv, struct drv_acs_params *params)

//Notify driver of band selection. More...
int(* 	set_band )(void *priv, enum set_band band)

//Get preferred frequency list for an interface. More...
int(* 	get_pref_freq_list )(void *priv, enum wpa_driver_if_type if_type, unsigned int *num, unsigned int *freq_list)

//Indicate probable P2P operating channel. More...
int(* 	set_prob_oper_freq )(void *priv, unsigned int freq)



```
<img src="//../zimage/wireless/wifi/07_wifilog/wpa_driver_ops.png" width = "50%" height="50%"/>

