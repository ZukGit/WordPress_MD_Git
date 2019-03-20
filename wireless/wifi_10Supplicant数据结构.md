# wpa数据结构

## ctrl_iface_global_priv 
```
1.ctrl_iface_global_priv
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#ctrl_iface_global_priv

struct ctrl_iface_global_priv {
	struct wpa_global *global;
	int sock;
	struct dl_list ctrl_dst;
	int android_control_socket;
	struct dl_list msg_queue;
	unsigned int throttle_count;
};

struct ctrl_iface_global_priv {
	struct wpa_global *global;
	struct dl_list ctrl_dst;
	struct dl_list msg_queue;
};


```


## wpa_global 
```
2.wpa_global
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#264



struct wpa_global {

	struct ctrl_iface_global_priv *ctrl_iface;

	struct dl_list p2p_srv_bonjour; /* struct p2p_srv_bonjour */
	struct dl_list p2p_srv_upnp; /* struct p2p_srv_upnp */
	
	struct wpa_supplicant *ifaces;
	struct wpa_supplicant *p2p_init_wpa_s;
	struct wpa_supplicant *p2p_group_formation;
	struct wpa_supplicant *p2p_invite_group;
	
	struct wpa_params params;

	struct wpas_hidl_priv *hidl;
	void **drv_priv;


	struct p2p_data *p2p;


	struct wpa_freq_range_list p2p_disallow_freq;
	struct wpa_freq_range_list p2p_go_avoid_freq;
	struct psk_list_entry *add_psk; /* From group formation */
	}
	
```

## wpa_supplicant
```
3.wpa_supplicant
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#485


struct wpa_supplicant {
	struct wpa_global *global;
	struct wpa_supplicant *parent;
	struct wpa_supplicant *p2pdev;
	struct wpa_supplicant *next;
	struct dl_list radio_list; /* list head: struct wpa_radio::ifaces */
	struct dl_list bss; /* struct wpa_bss::list */
	struct dl_list bss_id; /* struct wpa_bss::list_id */
	struct dl_list icon_head; /* struct icon_entry */
    struct dl_list bss_tmp_disallowed;
    struct dl_list fils_hlp_req;
	struct ctrl_iface_priv *ctrl_iface;
	
	
	struct wpa_sm *wpa;
	struct eapol_sm *eapol;
	struct hostapd_iface *ifmsh;
	struct hostapd_iface *ap_iface;
	struct wpa_driver_ops *driver;
	struct wps_context *wps;



	struct wpa_radio *radio; /* shared radio context */
	
	struct wpa_radio_work *scan_work;
	struct wpa_radio_work *p2p_scan_work;
	struct wpa_radio_work *p2p_listen_work;
	struct wpa_radio_work *p2p_send_action_work;
	struct wpa_radio_work *connect_work;
	
	struct wpa_driver_scan_params *autoscan_params;
	struct wps_ap_info *wps_ap;
	struct gas_query *gas;
	
	void * hidl_object_key;
	void *drv_priv; /* private data used by driver_ops */
	void *global_drv_priv;
	void *(scan_res_handler) (struct wpa_supplicant *wpa_s,struct wpa_scan_results *scan_res);
	void (*ap_configured_cb)(void *ctx, void *data);
	void *ap_configured_cb_ctx;
	void *ap_configured_cb_data;
	void (*pending_action_tx_status_cb)(struct wpa_supplicant *wpa_s,
					unsigned int freq, const u8 *dst,
					const u8 *src, const u8 *bssid,
					const u8 *data, size_t data_len,
					enum offchannel_send_action_result
					result);
	void *autoscan_priv;
	

	
    struct wpa_config *conf;
	struct l2_packet_data *l2;
	struct l2_packet_data *l2_br;
	struct wpa_bss *current_bss;
	struct wpa_bss **last_scan_res;
	struct wpa_bss *interworking_gas_bss;
	struct wpa_ssid *current_ssid;
	struct wpa_ssid *last_ssid;
	struct wpa_ssid *next_ssid;
	struct wpa_ssid *prev_scan_ssid; 
	struct wpa_ssid *prev_sched_ssid;
	struct wpa_ssid *p2p_last_4way_hs_fail;
	struct wpa_ssid *bgscan_ssid;
	struct wpa_ssid *connect_without_scan;
	struct wpa_ssid_value *disallow_aps_ssid;
	struct wpa_ssid_value *ssids_from_scan_req;
	struct sched_scan_plan *sched_scan_plans;

	struct scard_data *scard;
	struct wpa_blacklist *blacklist;
	struct wps_er *wps_er;
	struct mesh_rsn *mesh_rsn;
	struct wpabuf *pending_eapol_rx;
	struct wpabuf *pending_action_tx;
	struct wpabuf *p2p_oob_dev_pw; 
	struct wpabuf *last_gas_resp, *prev_gas_resp;
	struct wpabuf *vendor_elem[15];
	struct wpabuf *fst_ies;
	struct wpabuf *received_mb_ies;
	struct wpabuf *lci;
	struct wpabuf *ric_ies;
	struct ibss_rsn *ibss_rsn;
	struct p2p_go_neg_results *go_params;
	struct p2p_group *p2p_group;
	struct bgscan_ops *bgscan;
	struct autoscan_ops *autoscan;
    struct osu_provider *osu_prov;
	struct ext_password_data *ext_pw;
	struct {
		struct hostapd_hw_modes *modes;
		u16 num_modes;
		u16 flags;
	} hw
	
	}
	struct neighbor_report *wnm_neighbor_report_elements;
	struct wmm_ac_assoc_data *wmm_ac_assoc_info;
	struct wmm_tspec_element *tspecs[WMM_AC_NUM][TS_DIR_IDX_COUNT];
	struct wmm_tspec_element *last_tspecs;
	struct wmm_ac_addts_request *addts_request;
	struct rrm_data rrm;
	struct beacon_rep_data beacon_rep_data;
    struct fst_iface *fst;
	struct sched_scan_relative_params
	

	
```

## ctrl_iface_priv
```
4.ctrl_iface_priv

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/ctrl_iface_unix.c#34

struct ctrl_iface_priv {
	struct wpa_supplicant *wpa_s;
	int sock;
	struct dl_list ctrl_dst;
	int android_control_socket;
	struct dl_list msg_queue;
	unsigned int throttle_count;
};


struct ctrl_iface_priv {
	struct wpa_supplicant *wpa_s;
	struct dl_list ctrl_dst;
	struct dl_list msg_queue;

};


```

## wpa_params
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#wpa_params

5.wpa_params

struct wpa_params {

all base-type
}

```

## wpas_hidl_priv
```
6.wpas_hidl_priv

struct wpas_hidl_priv
{
	int hidl_fd;
	struct wpa_global *global;
	void *hidl_manager;
};


```
## void**
```
7.void**  方法函数

```
## wpa_sm
```
8.wpa_sm
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/rsn_supp/wpa_i.h#20

struct wpa_sm {
struct eapol_sm *eapol; /* EAPOL state machine from upper level code */
struct eapol_sm *preauth_eapol;

struct dl_list pmksa_candidates;
struct l2_packet_data *l2_preauth;
struct l2_packet_data *l2_preauth_br;
struct l2_packet_data *l2_tdls;
void *scard_ctx; /* context for smartcard callbacks */


struct wpa_ptk ptk, tptk;
struct wpa_gtk gtk;
struct wpa_gtk gtk_wnm_sleep;
struct wpa_igtk igtk;
struct wpa_igtk igtk_wnm_sleep;

struct rsn_pmksa_cache *pmksa; /* PMKSA cache */
struct rsn_pmksa_cache_entry *cur_pmksa; /* current PMKSA entry */


struct wpa_sm_ctx *ctx;

struct wpa_tdls_peer *tdls;
}


```

## eapol_sm
```
9.eapol_sm

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eapol_supp/eapol_supp_sm.c#32


/**
 * struct eapol_sm - Internal data for EAPOL state machines
 */
struct eapol_sm {


	struct eap_sm *eap;
	struct eap_peer_config *config;
    struct wpabuf *eapReqData; /* for EAP */
	struct eapol_config conf;
	struct eapol_ctx *ctx;
	struct eap_proxy_sm *eap_proxy;
}


```

## eap_sm
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/eap_peer/eap_i.h#307

struct eap_sm {

struct wpabuf *lastRespData;
 struct eap_method *m;
 struct eapol_callbacks *eapol_cb;
 
 	void *msg_ctx;
	void *scard_ctx;
	void *ssl_ctx;
	void *ssl_ctx2;
	struct dl_list erp_keys; /* struct eap_erp_key */
}


```
## eap_method
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eap_server/eap_i.h#24
struct eap_method {
void** manyFuncPoint;
struct eap_method *next;
}


```


## hostapd_iface
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/hostapd.h#388

/**
 * struct hostapd_iface - hostapd per-interface data structure
 */
struct hostapd_iface {
	struct hapd_interfaces *interfaces;

	struct hostapd_config *conf;
	struct hostapd_data **bss;
	struct wpabuf *fst_ies;
	struct fst_iface *fst;
    struct wpabuf *fst_ies;
	struct ap_info *ap_list; /* AP info list head */
	struct ap_info *ap_hash[STA_HASH_SIZE];
	struct hostapd_hw_modes *hw_features;
	struct hostapd_hw_modes *current_mode;
	struct hostapd_rate_data *current_rates;
	struct dl_list sta_seen; /* struct hostapd_sta_info */
	void (*scan_cb)(struct hostapd_iface *iface);
	}
```
## wpa_driver_ops
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver.h#2061

struct wpa_driver_ops {
void** driverMethod

}



```

## p2p_data
```
p2p_data 

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/p2p/p2p_i.h#174

struct p2p_data {
struct dl_list devices;

struct p2p_config *cfg;

struct p2p_device *go_neg_peer;
struct p2p_device *invite_peer;
struct p2p_device *last_p2p_find_oper;
struct p2p_device *sd_peer;
struct p2p_device *pending_client_disc_go;

struct p2p_sd_query *sd_query;
struct p2p_sd_query *sd_queries;
struct p2p_channels channels;
struct wpa_freq_range_list no_go_freq;

struct p2p_pending_action_tx *after_scan_tx;
struct p2p_group **groups;
struct p2ps_advertisement *p2ps_adv_list;
struct p2ps_provision *p2ps_prov;

struct wpabuf *sd_resp; /* Fragmented SD response */
struct wpabuf *sd_rx_resp; /* Reassembled SD response */
struct wpabuf *wps_vendor_ext[P2P_MAX_WPS_VENDOR_EXT];

struct wpabuf *wfd_ie_beacon;
struct wpabuf *wfd_ie_probe_req;
struct wpabuf *wfd_ie_probe_resp;
struct wpabuf *wfd_ie_assoc_req;
struct wpabuf *wfd_ie_invitation;
struct wpabuf *wfd_ie_prov_disc_req;
struct wpabuf *wfd_ie_prov_disc_resp;
struct wpabuf *wfd_ie_go_neg;
struct wpabuf *wfd_dev_info;
struct wpabuf *wfd_assoc_bssid;
struct wpabuf *wfd_coupled_sink_info;
struct wpabuf *wfd_r2_dev_info;
struct wpabuf **vendor_elem;
}


```

## wpa_freq_range_list
```
wpa_freq_range_list

struct wpa_freq_range_list {
	struct wpa_freq_range  * next;
	
	}
	}
	
	
```


## psk_list_entry
```
psk_list_entry

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/config_ssid.h#41


struct psk_list_entry {
	struct dl_list list;
	u8 addr[ETH_ALEN];
	u8 psk[32];
	u8 p2p;
};

```

## wps_context

```
struct wps_context {
	struct wps_registrar *registrar;
	struct wps_device_data dev;
	void* N[*funcPoint]
	void* cb_ctx;
	struct upnp_wps_device_sm *wps_upnp;
	struct upnp_pending_message *upnp_msgs;

	struct wpabuf *dh_privkey;
	struct wpabuf *dh_pubkey;	
	struct wpabuf *ap_nfc_dh_pubkey;
	struct wpabuf *ap_nfc_dh_privkey;
	struct wpabuf *ap_nfc_dev_pw;
	
}



```

## wpa_radio
```
struct wpa_radio {
	char name[16]; /* from driver_ops get_radio_name() or empty if not * available */
	unsigned int external_scan_running:1;
	unsigned int num_active_works;
	struct dl_list ifaces; /* struct wpa_supplicant::radio_list entries */
	struct dl_list work; /* struct wpa_radio_work::list entries */
};

```


## wpa_radio_work
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#314


struct wpa_radio_work {
	struct dl_list list;
	struct wpa_supplicant *wpa_s;
	void (*cb)(struct wpa_radio_work *work, int deinit);
	void *ctx;
};

```

## wpa_driver_scan_params
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver.h#359
wpa_driver_scan_params



struct wpa_driver_scan_params {
	struct wpa_driver_scan_ssid ssids[16];
	struct wpa_driver_scan_filter  *filter_ssids;
    struct sched_scan_plan *sched_scan_plans;
	}
	
```

## wpa_driver_scan_ssid
```
	struct wpa_driver_scan_ssid {
	const u8 *ssid;
	size_t ssid_len;
};


```

## wpa_driver_scan_filter
```
	struct wpa_driver_scan_filter {
		u8 ssid[SSID_MAX_LEN];
		size_t ssid_len;
	} *filter_ssids;
	


	 
```


## wps_ap_info
```
struct wps_ap_info {
	u8 bssid[ETH_ALEN];
	enum wps_ap_info_type {
		WPS_AP_NOT_SEL_REG,
		WPS_AP_SEL_REG,
		WPS_AP_SEL_REG_OUR
	} type;
	unsigned int tries;
	struct os_reltime last_attempt;
	unsigned int pbc_active;
	u8 uuid[WPS_UUID_LEN];
};


```


## gas_query
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/gas_query.c#62

struct gas_query {
	struct wpa_supplicant *wpa_s;
	struct dl_list pending; /* struct gas_query_pending */
	struct gas_query_pending *current;
	struct wpa_radio_work *work;
};



```

## wpa_config
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/config.h#349


struct wpa_config {
	struct wpa_ssid *ssid;
	struct wpa_ssid **pssid;
	struct wpa_cred *cred;
	struct p2p_channel *p2p_pref_chan;
	struct wpa_freq_range_list p2p_no_go_freq;
	struct hostapd_wmm_ac_params wmm_ac_params[4];
	struct wpabuf *wps_vendor_ext_m1;
	struct wpabuf *wps_vendor_ext[MAX_WPS_VENDOR_EXT];
	struct wpabuf *wps_nfc_dh_pubkey;
	struct wpabuf *wps_nfc_dh_privkey;
	struct wpabuf *wps_nfc_dev_pw;
	struct wpabuf *ap_vendor_elements;
}


```

## l2_packet_data

```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/l2_packet/l2_packet_privsep.c#18

struct l2_packet_data {

	void* rx_callback)(void *ctx, const u8 *src_addr,const u8 *buf, size_t len);
	void* rx_callback_ctx;

	struct sockaddr_un priv_addr;
};




```


## wpa_bss
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/bss.h#62


struct wpa_bss {
	struct dl_list list;
	struct dl_list list_id;
	struct wpa_bss_anqp *anqp;

}


```


## wpa_ssid
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/config_ssid.h#56


struct wpa_ssid {

struct wpa_ssid *next;
struct wpa_ssid *pnext;
struct eap_peer_config eap;
struct dl_list psk_list;
		
}


```
## wpa_ssid_value
```
struct wpa_ssid_value {
	u8 ssid[SSID_MAX_LEN];
	size_t ssid_len;
};

```

## sched_scan_plan
```
	struct sched_scan_plan {
		 u32 interval; /* In seconds */
		 u32 iterations; /* Zero to run infinitely */
	 } *sched_scan_plans;


```

## scard_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/pcsc_funcs.c#114


struct scard_data {
	SCARDCONTEXT ctx;
	SCARDHANDLE card;
	sim_types sim_type;
	int pin1_required;
};

```

## wpa_blacklist
```

struct wpa_blacklist {
	struct wpa_blacklist *next;
	u8 bssid[ETH_ALEN];
	int count;
};

```

## wps_er
```
struct wps_er {
	struct wps_context *wps;

	struct dl_list ap;
	struct dl_list ap_unsubscribing;
	struct dl_list ap_settings;
	struct http_server *http_srv;
	void* deinit_done_cb)(void *ctx);
	void* deinit_done_ctx;
	struct in_addr filter_addr;
};

```


## mesh_rsn
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/mesh_rsn.h#12


struct mesh_rsn {
	struct wpa_supplicant *wpa_s;
	struct wpa_authenticator *auth;
};

```

## wpabuf
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/wpabuf.h#20

struct wpabuf {
	size_t size; /* total size of the allocated buffer */
	size_t used; /* length of data in the buffer */
	u8 *buf;     /* pointer to the head of the buffer */
	unsigned int flags;  /* optionally followed by the allocated buffer */
};

```

## ibss_rsn
```

struct ibss_rsn {
	struct wpa_supplicant *wpa_s;
	struct wpa_authenticator *auth_group;
	struct ibss_rsn_peer *peers;
	u8 psk[PMK_LEN];
};



```


## p2p_go_neg_results
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#79


struct p2p_go_neg_results {

}

```


## p2p_group
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_group.c#34


struct p2p_group {
	struct p2p_data *p2p;
	struct p2p_group_config *cfg;
	struct p2p_group_member *members;

	struct wpabuf *noa;
	struct wpabuf *wfd_ie;
};



```


## bgscan_ops
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/bgscan.h#15

struct bgscan_ops {
	const char *name;
	void * (*init)(struct wpa_supplicant *wpa_s, const char *params,const struct wpa_ssid *ssid);
	void (*deinit)(void *priv);
	int (*notify_scan)(void *priv, struct wpa_scan_results *scan_res);
	void (*notify_beacon_loss)(void *priv);
	void (*notify_signal_change)(void *priv, int above,int current_signal,int current_noise,int current_txrate);
};

```

## autoscan_ops
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/autoscan.h#14


struct autoscan_ops {
	const char *name;

	void * (*init)(struct wpa_supplicant *wpa_s, const char *params);
	void (*deinit)(void *priv);
	int (*notify_scan)(void *priv, struct wpa_scan_results *scan_res);
};

```


## osu_provider
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/hs20_supplicant.c#49
struct osu_provider {
	u8 bssid[ETH_ALEN];
	u8 osu_ssid[SSID_MAX_LEN];
	u8 osu_ssid_len;
	char server_uri[256];
	u32 osu_methods; /* bit 0 = OMA-DM, bit 1 = SOAP-XML SPP */
	char osu_nai[256];
	struct osu_lang_string friendly_name[OSU_MAX_ITEMS];
	size_t friendly_name_count;
	struct osu_lang_string serv_desc[OSU_MAX_ITEMS];
	size_t serv_desc_count;
	struct osu_icon icon[OSU_MAX_ITEMS];
	size_t icon_count;
};

struct osu_lang_string {
	char lang[4];
	char text[253];
};

struct osu_icon {
	u16 width;
	u16 height;
	char lang[4];
	char icon_type[256];
	char filename[256];
	unsigned int id;
	unsigned int failed:1;
};

```

## ext_password_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/ext_password.c#26

struct ext_password_data {
	const struct ext_password_backend *backend;
	void *priv;
};


http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/ext_password_i.h#14
struct ext_password_backend {
	const char *name;
	void * (*init)(const char *params);
	void (*deinit)(void *ctx);
	struct wpabuf * (*get)(void *ctx, const char *name);
};

```


## hostapd_hw_modes
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver.h#173

struct hostapd_hw_modes {

struct hostapd_channel_data *channels;
struct he_capabilities he_capab;

}


```


## neighbor_report
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wnm_sta.h#24


struct neighbor_report {

	struct measurement_pilot *meas_pilot;
	struct multiple_bssid *mul_bssid;
	int freq;
};

struct multiple_bssid {
	u8 max_bssid_indicator;
	u8 subelem_len;
	u8 subelems[255];
};

struct measurement_pilot {
	u8 measurement_pilot;
	u8 subelem_len;
	u8 subelems[255];
};



```


## wmm_ac_assoc_data

```

struct wmm_ac_assoc_data {
	struct {
		unsigned int acm:1;
		unsigned int uapsd:1;
	} ac_params[4];
};




```

## wmm_tspec_element
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/ieee802_11_defs.h#1280


struct wmm_tspec_element {
	u8 eid; /* 221 = 0xdd */
	u8 length; /* 6 + 55 = 61 */
	u8 oui[3]; /* 00:50:f2 */
	u8 oui_type; /* 2 */
	u8 oui_subtype; /* 2 */
	u8 version; /* 1 */
	/* WMM TSPEC body (55 octets): */
	u8 ts_info[3];
	le16 nominal_msdu_size;
	le16 maximum_msdu_size;
	le32 minimum_service_interval;
	le32 maximum_service_interval;
	le32 inactivity_interval;
	le32 suspension_interval;
	le32 service_start_time;
	le32 minimum_data_rate;
	le32 mean_data_rate;
	le32 peak_data_rate;
	le32 maximum_burst_size;
	le32 delay_bound;
	le32 minimum_phy_rate;
	le16 surplus_bandwidth_allowance;
	le16 medium_time;
} STRUCT_PACKED;

```

## wmm_ac_addts_request
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wmm_ac.h#89

struct wmm_ac_addts_request {

	struct wmm_tspec_element tspec;
};



```


## rrm_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#410


struct rrm_data {
	void (*notify_neighbor_rep)(void *ctx, struct wpabuf *neighbor_rep);
	void *neighbor_rep_cb_ctx;
}

```

## beacon_rep_data
```
struct beacon_rep_data {
	u8 token;
	struct wpa_driver_scan_params scan_params;
	u8 ssid[SSID_MAX_LEN];
	size_t ssid_len;
	u8 bssid[ETH_ALEN];
	enum beacon_report_detail report_detail;
	struct bitfield *eids;
};




```
## bitfield
```

struct bitfield {
	u8 *bits;
	size_t max_bits;
};


```

## fst_iface
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/fst/fst_iface.h#18

struct fst_iface {
	struct fst_group *group;
	struct fst_wpa_obj iface_obj;
	u8 own_addr[ETH_ALEN];
	struct wpabuf *mb_ie;
	char ifname[IFNAMSIZ + 1];
	struct fst_iface_cfg cfg;
	struct dl_list group_lentry;
};


```

## sched_scan_relative_params
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#1145

	struct sched_scan_relative_params {
		int relative_rssi_set;
		int relative_rssi;
		enum set_band relative_adjust_band;

		int relative_adjust_rssi;
	} srp;


```

## eap_peer_config
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eap_peer/eap_config.h#15


struct eap_peer_config {

	struct eap_method_type *eap_methods;
	
	
	}
	

```

## eap_method_type

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eap_peer/eap.h#20
struct eap_method_type {
	int vendor;
	u32 method;
};





```
## eapol_config
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eapol_supp/eapol_supp_sm.h#20

struct eapol_config {


}
```

## eapol_ctx

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eapol_supp/eapol_supp_sm.h#82


struct eapol_ctx {

	void *ctx;
	void (*cb)(struct eapol_sm *eapol, enum eapol_supp_result result,void *ctx);
	void *msg_ctx;  
	void *cb_ctx;  
	void (*eapol_done_cb)(void *ctx);
	struct wps_context *wps;
		   }
```


## eap_proxy_sm
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/eap_peer/eap_proxy_qmi_oc.h#113

struct eap_proxy_sm {
        qmi_client_type qmi_auth_svc_client_ptr[MAX_NO_OF_SIM_SUPPORTED]; ? 查不到定义 枚举 联合体
        qmi_state_e qmi_state;
        eap_proxy_qmi_srv_result srvc_result;
        qmi_eap_sync_rsp_data_type qmi_resp_data;
        eap_proxy_state  proxy_state;
        Boolean iskey_valid;
        u8 *key;
        Boolean is_state_changed;
        void *ctx;
        void *msg_ctx;
        struct eapol_callbacks *eapol_cb;
        u8 *eapReqData;
        size_t eapReqDataLen;
        Boolean isEap;
        int eap_type;
        int user_selected_sim;
        int eap_auth_session_flag[MAX_NO_OF_SIM_SUPPORTED];
        int notification_code;
        pthread_t thread_id;
        wpa_uim_struct_type   wpa_uim[MAX_NO_OF_SIM_SUPPORTED];
        Boolean qmi_uim_svc_client_initialized[MAX_NO_OF_SIM_SUPPORTED];
};



```

## eapol_callbacks
```
struct eapol_callbacks {

	struct eap_peer_config * (*get_config)(void *ctx);
	void (*set_config_blob)(void *ctx, struct wpa_config_blob *blob);
	void (*eap_param_needed)(void *ctx, enum wpa_ctrl_req_type field,const char *txt);
	void (*set_anon_id)(void *ctx, const u8 *id, size_t len);
};
			 
```

## hapd_interfaces
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/hostapd.h#32


struct hapd_interfaces {
	int (*reload_config)(struct hostapd_iface *iface);
	struct hostapd_config * (*config_read_cb)(const char *config_fname);
	int (*ctrl_iface_init)(struct hostapd_data *hapd);
	void (*ctrl_iface_deinit)(struct hostapd_data *hapd);
	int (*for_each_interface)(struct hapd_interfaces *interfaces,
	int (*cb)(struct hostapd_iface *iface,void *ctx), void *ctx);
	int (*driver_init)(struct hostapd_iface *iface);

	size_t count;
	int global_ctrl_sock;
	struct dl_list global_ctrl_dst;
	char *global_iface_path;
	char *global_iface_name;

	size_t terminate_on_error;

	int eloop_initialized;
};



```

## hostapd_config
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#679

struct hostapd_config {
	struct hostapd_bss_config **bss, *last_bss;
	struct wpa_freq_range_list acs_ch_list;
    struct wpa_driver_ops *driver;
    struct hostapd_tx_queue_params tx_queue[4];
    struct hostapd_wmm_ac_params wmm_ac_params[4];
    struct fst_iface_cfg fst_cfg;
	struct wpabuf *lci;
	struct wpabuf *civic;
	
}

```

## hostapd_bss_config
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#237

struct hostapd_bss_config {

	struct hostapd_eap_user *eap_user;
	struct hostapd_ip_addr own_ip_addr;
	struct hostapd_ip_addr radius_das_client_addr;	
	struct hostapd_radius_servers *radius;
	struct hostapd_radius_attr *radius_auth_req_attr;
	struct hostapd_radius_attr *radius_acct_req_attr;

	
	struct hostapd_ssid ssid;
	struct mac_acl_entry *accept_mac;
	struct mac_acl_entry *deny_mac;
		
		
}


```

## hostapd_eap_user
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#153

struct hostapd_eap_user {
	struct hostapd_eap_user *next;
	u8 *identity;
	size_t identity_len;
	struct {
		int vendor;
		u32 method;
	} methods[8];
	u8 *password;
	size_t password_len;
	int phase2;
	int force_version;
	unsigned int wildcard_prefix:1;
	unsigned int password_hash:1; /* whether password is hashed with
				       * nt_password_hash() */
	unsigned int remediation:1;
	unsigned int macacl:1;
	int ttls_auth; /* EAP_TTLS_AUTH_* bitfield */
	struct hostapd_radius_attr *accept_attr;
};



```

## hostapd_ip_addr
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/utils/ip_addr.h#12

struct hostapd_ip_addr {
	int af; /* AF_INET / AF_INET6 */
	union {
		struct in_addr v4;
		u8 max_len[16];
	} u;
};


struct in_addr {
	__be32	s_addr;
};

```

## hostapd_radius_servers

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/radius/radius_client.h#120

struct hostapd_radius_servers {

	struct hostapd_radius_server *auth_servers;
	struct hostapd_radius_server *auth_server;
	struct hostapd_radius_server *acct_servers;
	struct hostapd_radius_server *acct_server;
	struct hostapd_ip_addr client_addr;
}
```

## hostapd_radius_attr

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#174
struct hostapd_radius_attr {
	u8 type;
	struct wpabuf *val;
	struct hostapd_radius_attr *next;
};


```

## hostapd_ssid
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#86


struct hostapd_ssid {
struct hostapd_wpa_psk *wpa_psk;
struct hostapd_wep_keys wep;

}

```

## hostapd_wpa_psk
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#145

struct hostapd_wpa_psk {
	struct hostapd_wpa_psk *next;
	int group;
	u8 psk[PMK_LEN];
	u8 addr[ETH_ALEN];
	u8 p2p_dev_addr[ETH_ALEN];
};
```

## hostapd_wep_keys
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#69

struct hostapd_wep_keys {
	u8 idx;
	u8 *key[NUM_WEP_KEYS];
	size_t len[NUM_WEP_KEYS];
	int keys_set;
	size_t default_len; /* key length used for dynamic key generation */
};

```

## mac_acl_entry
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#hostapd_wpa_psk

struct mac_acl_entry {
	macaddr addr;
	struct vlan_description vlan_id;
};

```

## vlan_description
```

struct vlan_description {
	int notempty; /* 0 : no vlan information present, 1: else */
	int untagged; /* >0 802.1q vid */
	int tagged[MAX_NUM_TAGGED_VLAN]; /* first k items, ascending order */
};


```

## hostapd_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/hostapd.h#121

struct hostapd_data {
	struct hostapd_iface *iface;
	struct hostapd_config *iconf;
	struct hostapd_bss_config *conf;
void *drv_priv;
void (*new_assoc_sta_cb)(struct hostapd_data *hapd,struct sta_info *sta, int reassoc);
void *msg_ctx; /* ctx for wpa_msg() calls */	
void *ssl_ctx;
void *eap_sim_db_priv;
void (*sta_authorized_cb)(void *ctx, const u8 *mac_addr,int authorized, const u8 *p2p_dev_addr);
void *sta_authorized_cb_ctx;
void *new_psk_cb_ctx;
struct dl_list erp_keys; /* struct eap_server_erp_key */
struct dl_list nr_db;


struct wpabuf *time_adv;
struct wpabuf *wps_beacon_ie;
struct wpabuf *wps_probe_resp_ie;
struct wpabuf *p2p_beacon_ie;
struct wpabuf *p2p_probe_resp_ie;

struct l2_packet_data *l2;
struct wps_context *wps;

struct p2p_data *p2p;
struct p2p_group *p2p_group;


struct wpa_authenticator *wpa_auth;
struct upnp_wps_device_sm *wps_upnp;



struct sta_info *sta_hash[256];
struct radius_client_data *radius;
struct radius_das_data *radius_das;
struct iapp_data *iapp;
struct hostapd_cached_radius_acl *acl_cache;
struct hostapd_acl_query_data *acl_queries;	
struct eapol_authenticator *eapol_auth;
struct rsn_preauth_interface *preauth_iface;
struct radius_server_data *radius_srv;
struct wps_stat wps_stats;
struct hostapd_probereq_cb *probereq_cb;
struct hostapd_freq_params cs_freq_params;


}
	
```

## hostapd_rate_data

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/hostapd.h#76
struct hostapd_rate_data {
	int rate; /* rate in 100 kbps */
	int flags; /* HOSTAPD_RATE_ flags */
};

```

## p2p_config
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#426
struct p2p_config {
	struct p2p_channels channels;
	struct p2p_channels cli_channels;
	struct p2p_channel *pref_chan;
		void *cb_ctx;
	int (*p2p_scan)(void *ctx, enum p2p_scan_type type, int freq,
			  unsigned int num_req_dev_types,
			const u8 *req_dev_types, const u8 *dev_id, u16 pw_id);
	int (*send_probe_resp)(void *ctx, const struct wpabuf *buf,
			       unsigned int freq);
	int (*send_action)(void *ctx, unsigned int freq, const u8 *dst,
			   const u8 *src, const u8 *bssid, const u8 *buf,
			   size_t len, unsigned int wait_time);
	int (*start_listen)(void *ctx, unsigned int freq,
			    unsigned int duration,
			    const struct wpabuf *probe_resp_ie);
	int (*get_noa)(void *ctx, const u8 *interface_addr, u8 *buf,
		       size_t buf_len);

}


```

## p2p_channels
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#44


struct p2p_channels {
	struct p2p_reg_class {
		u8 reg_class;
		u8 channel[P2P_MAX_REG_CLASS_CHANNELS];
		size_t channels;
	} reg_class[P2P_MAX_REG_CLASSES];
	size_t reg_classes;
};



```

## p2p_reg_class
```
	struct p2p_reg_class {
		u8 reg_class;
		u8 channel[P2P_MAX_REG_CLASS_CHANNELS];
		size_t channels;
	} 

```


## p2p_channel
```

struct p2p_channel {
	u8 op_class;
	u8 chan;
};


```


## p2p_device
```

http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/p2p/p2p_i.h#41

struct p2p_device {
	struct dl_list list;
	struct p2p_peer_info info;
	struct p2p_channels channels;
    struct wpabuf *go_neg_conf;
}
		
```

## p2p_peer_info
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#317

struct p2p_peer_info {

	struct wpabuf *wps_vendor_ext[P2P_MAX_WPS_VENDOR_EXT];
	struct wpabuf *wfd_subelems;
	struct wpabuf *vendor_elems;
	struct wpabuf *p2ps_instance;

}


```

## p2p_sd_query
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#153

struct p2p_sd_query {
	struct p2p_sd_query *next;
	u8 peer[ETH_ALEN];
	int for_all_peers;
	int wsd; /* Wi-Fi Display Service Discovery Request */
	struct wpabuf *tlvs;
};


```

## p2p_pending_action_tx
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#161

struct p2p_pending_action_tx {
	unsigned int freq;
	u8 dst[ETH_ALEN];
	u8 src[ETH_ALEN];
	u8 bssid[ETH_ALEN];
	size_t len;
	unsigned int wait_time;
	/* Followed by len octets of the frame */
};

```

## p2ps_advertisement
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#246

struct p2ps_advertisement {
	struct p2ps_advertisement *next;
	
	
	}
	
	
```

## p2ps_provision
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p.h#p2ps_provision

struct p2ps_provision {

};
```


## wps_registrar
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/wps/wps_registrar.c#141

struct wps_registrar {
	struct wps_context *wps;

	int (*new_psk_cb)(void *ctx, const u8 *mac_addr, const u8 *p2p_dev_addr,
			  const u8 *psk, size_t psk_len);
	int (*set_ie_cb)(void *ctx, struct wpabuf *beacon_ie,
			 struct wpabuf *probe_resp_ie);
	void (*pin_needed_cb)(void *ctx, const u8 *uuid_e,
			      const struct wps_device_data *dev);
	void (*reg_success_cb)(void *ctx, const u8 *mac_addr,
			       const u8 *uuid_e, const u8 *dev_pw,
			       size_t dev_pw_len);
	void (*set_sel_reg_cb)(void *ctx, int sel_reg, u16 dev_passwd_id,
			       u16 sel_reg_config_methods);
	void (*enrollee_seen_cb)(void *ctx, const u8 *addr, const u8 *uuid_e,
				 const u8 *pri_dev_type, u16 config_methods,
				 u16 dev_password_id, u8 request_type,
				 const char *dev_name);
	void *cb_ctx;
	
	struct dl_list pins;
	struct dl_list nfc_pw_tokens;
	struct wps_pbc_session *pbc_sessions;
	struct wps_registrar_device *devices;
	struct wpabuf *extra_cred;

	}
```

## wps_pbc_session
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/wps/wps_registrar.c#wps_pbc_session
struct wps_pbc_session {
	struct wps_pbc_session *next;

};

```
## wps_registrar_device
```
struct wps_registrar_device {
	struct wps_registrar_device *next;
	struct wps_device_data dev;
	u8 uuid[WPS_UUID_LEN];
};


```

## wps_device_data
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/wps/wps.h#85


struct wps_device_data {
	u8 mac_addr[ETH_ALEN];
	char *device_name;
	char *manufacturer;
	char *model_name;
	char *model_number;
	char *serial_number;
	u8 pri_dev_type[WPS_DEV_TYPE_LEN];
#define WPS_SEC_DEVICE_TYPES 5
	u8 sec_dev_type[WPS_SEC_DEVICE_TYPES][WPS_DEV_TYPE_LEN];
	u8 num_sec_dev_types;
	u32 os_version;
	u8 rf_bands;
	u16 config_methods;
	struct wpabuf *vendor_ext_m1;
	struct wpabuf *vendor_ext[MAX_WPS_VENDOR_EXTENSIONS];

	int p2p;
};

```

## upnp_wps_device_sm

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/wps/wps_upnp_i.h#123



struct upnp_wps_device_sm {

	struct advertisement_state_machine advertisement;

	struct http_server *web_srv;

	struct dl_list subscriptions;
	struct dl_list interfaces; /* struct upnp_wps_device_interface */
	struct dl_list msearch_replies;
};



```

## upnp_pending_message
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/wps/wps.h#581


struct upnp_pending_message {
	struct upnp_pending_message *next;
	u8 addr[ETH_ALEN];
	struct wpabuf *msg;
	enum wps_msg_type type;
};



```

## hostapd_wmm_ac_params
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/common/ieee802_11_common.h#140
struct hostapd_wmm_ac_params {
	int cwmin;
	int cwmax;
	int aifs;
	int txop_limit; /* in units of 32us */
	int admission_control_mandatory;
};


```



## fst_iface_cfg 

```
struct fst_iface_cfg {
	char group_id[FST_MAX_GROUP_ID_LEN + 1];
	u8 priority;
	u32 llt;
};

```

## hostapd_tx_queue_params 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/ap_config.h#183
struct hostapd_tx_queue_params {
	int aifs;
	int cwmin;
	int cwmax;
	int burst; /* maximum burst time in 0.1 ms, i.e., 10 = 1 ms */
};



```

## wpa_authenticator 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/wpa_auth_i.h#193


struct wpa_authenticator {
	struct wpa_group *group;
	struct wpa_auth_config conf;
	struct wpa_auth_callbacks *cb;
	struct wpa_ft_pmk_cache *ft_pmk_cache;
		
		
		void *cb_ctx;
	unsigned int dot11RSNAStatsTKIPRemoteMICFailures;
	u32 dot11RSNAAuthenticationSuiteSelected;
	u32 dot11RSNAPairwiseCipherSelected;
	u32 dot11RSNAGroupCipherSelected;
	u8 dot11RSNAPMKIDUsed[PMKID_LEN];
	u32 dot11RSNAAuthenticationSuiteRequested; /* FIX: update */
	u32 dot11RSNAPairwiseCipherRequested; /* FIX: update */
	u32 dot11RSNAGroupCipherRequested; /* FIX: update */
	unsigned int dot11RSNATKIPCounterMeasuresInvoked;
	unsigned int dot11RSNA4WayHandshakeFailures;

	struct rsn_pmksa_cache *pmksa;
	struct bitfield *ip_pool;

};

```


## wpa_group
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/ap/wpa_auth_i.h#156


/* per group key state machine data */
struct wpa_group {
	struct wpa_group *next;
};

```


## wpa_auth_config 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/wpa_auth.h#157

struct wpa_auth_config {




}



```

## wpa_auth_callbacks

```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/wpa_auth.h#229

struct wpa_auth_callbacks {
	void (*logger)(void *ctx, const u8 *addr, logger_level level,const char *txt);
	void (*disconnect)(void *ctx, const u8 *addr, u16 reason);
	int (*mic_failure_report)(void *ctx, const u8 *addr);
	void (*psk_failure_report)(void *ctx, const u8 *addr);
	void (*set_eapol)(void *ctx, const u8 *addr, wpa_eapol_variable var,int value);

void* disconnect
void* many(func)	
}		

```

## wpa_ft_pmk_cache
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/wpa_auth_ft.c#855


struct wpa_ft_pmk_cache {
	struct wpa_ft_pmk_r0_sa *pmk_r0;
	struct wpa_ft_pmk_r1_sa *pmk_r1;
};
```


## wpa_ft_pmk_r0_sa 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/ap/wpa_auth_ft.c#wpa_ft_pmk_r0_sa

struct wpa_ft_pmk_r0_sa {
	struct wpa_ft_pmk_r0_sa *next;
	u8 pmk_r0[PMK_LEN];
	u8 pmk_r0_name[WPA_PMK_NAME_LEN];
	u8 spa[ETH_ALEN];
	int pairwise; /* Pairwise cipher suite, WPA_CIPHER_* */
	/* TODO: expiration, identity, radius_class, EAP type, VLAN ID */
	int pmk_r1_pushed;
};
```

## wpa_ft_pmk_r1_sa
```
struct wpa_ft_pmk_r1_sa {
	struct wpa_ft_pmk_r1_sa *next;
	u8 pmk_r1[PMK_LEN];
	u8 pmk_r1_name[WPA_PMK_NAME_LEN];
	u8 spa[ETH_ALEN];
	int pairwise; /* Pairwise cipher suite, WPA_CIPHER_* */
	/* TODO: expiration, identity, radius_class, EAP type, VLAN ID */
};
```

## rsn_pmksa_cache 
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/rsn_supp/pmksa_cache.c#22


struct rsn_pmksa_cache {
	struct rsn_pmksa_cache_entry *pmksa; /* PMKSA cache */
	int pmksa_count; /* number of entries in PMKSA cache */
	struct wpa_sm *sm; /* TODO: get rid of this reference(?) */

	void (*free_cb)(struct rsn_pmksa_cache_entry *entry, void *ctx,
			enum pmksa_free_reason reason);
	void *ctx;
};



```

## rsn_pmksa_cache_entry
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/rsn_supp/pmksa_cache.h#15


struct rsn_pmksa_cache_entry {
	struct rsn_pmksa_cache_entry *next;

	void* network_ctx;

};

```

## wpa_interface
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/wpa_supplicant_i.h#53

struct wpa_interface {

	const char *confname;
	const char *confanother;
	const char *ctrl_interface;
	const char *driver;
	const char *driver_param;

	const char *ifname;
	const char *bridge_ifname;
	int p2p_mgmt;

}

```


## p2p_message(独立)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#568


struct p2p_message {
	struct wpabuf *p2p_attributes;
	struct wpabuf *wps_attributes;
	struct wpabuf *wfd_subelems;
	}
	
```

## p2p_group_info(独立)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/p2p/p2p_i.h#673

struct p2p_group_info {
	unsigned int num_clients;
	struct p2p_client_info {
		const u8 *p2p_device_addr;
		const u8 *p2p_interface_addr;
		u8 dev_capab;
		u16 config_methods;
		const u8 *pri_dev_type;
		u8 num_sec_dev_types;
		const u8 *sec_dev_types;
		const char *dev_name;
		size_t dev_name_len;
	} client[P2P_MAX_GROUP_ENTRIES];
};


```
## p2p_client_info(独立)
```
	struct p2p_client_info {
		const u8 *p2p_device_addr;
		const u8 *p2p_interface_addr;
		u8 dev_capab;
		u16 config_methods;
		const u8 *pri_dev_type;
		u8 num_sec_dev_types;
		const u8 *sec_dev_types;
		const char *dev_name;
		size_t dev_name_len;
	} client[P2P_MAX_GROUP_ENTRIES];


```

## wpa_driver_capa (独立)
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/src/drivers/driver.h#1398
struct wpa_driver_capa {
struct wowlan_triggers wowlan_triggers;

}





```

##  wowlan_triggers
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/src/drivers/driver.h#1096

struct wowlan_triggers {
	u8 any;
	u8 disconnect;
	u8 magic_pkt;
	u8 gtk_rekey_failure;
	u8 eap_identity_req;
	u8 four_way_handshake;
	u8 rfkill_release;
};

```

## wpa_cred
```
http://androidxref.com/9.0.0_r3/xref/external/wpa_supplicant_8/wpa_supplicant/config.h#53


http://w1.fi/wpa_supplicant/devel/structwpa__cred.html


struct wpa_cred {
	struct wpa_cred *next;
	int id;
	int temporary;
	char *username;
	char *password;
	int ext_password;
	char *ca_cert;
.....
}

```

