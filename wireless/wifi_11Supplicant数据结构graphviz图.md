# python
```
from graphviz import Digraph

f = Digraph('finite_state_machine', filename='fsm.gv')
f.attr(rankdir='LR')

f.attr('node', shape='circle')
## wpa_global
f.node('ctrl_iface_global_priv',label='1.ctrl_iface_global_priv')
f.node('wpa_global',label='2.wpa_global')
f.node('wpa_supplicant',label='3.wpa_supplicant',weight='10')
f.node('ctrl_iface_priv',label='4.ctrl_iface_priv')


## wpa_global
f.node('wpa_params',label='5.wpa_params')
f.node('wpas_hidl_priv',label='6.wpas_hidl_priv')
f.node('void**',label='7.void**')
f.node('p2p_data',label='7.p2p_data')
f.node('wpa_freq_range_list',label='wpa_freq_range_list')
f.node('psk_list_entry',label='9.psk_list_entry')

## wpa_supplicant
f.node('wpa_sm',label='8.wpa_sm')
f.node('eapol_sm',label='9.eapol_sm')

f.node('hostapd_iface',label='hostapd_iface')
f.node('wpa_driver_ops',label='wpa_driver_ops')
f.node('wps_context',label='wps_context')
f.node('hostapd_iface',label='hostapd_iface')
f.node('wpa_radio_work',label='wpa_radio_work')
f.node('wpa_driver_scan_params',label='wpa_driver_scan_params')
f.node('wps_ap_info',label='wps_ap_info')
f.node('gas_query',label='gas_query')
f.node('void*',label='void*')
f.node('wpa_radio',label='wpa_radio')
f.node('wpa_radio_work',label='wpa_radio_work')
f.node('wpa_config',label='wpa_config')
f.node('l2_packet_data',label='l2_packet_data')
f.node('wpa_bss',label='wpa_bss')
f.node('wpa_ssid',label='wpa_ssid')
f.node('wpa_ssid_value',label='wpa_ssid_value')
f.node('sched_scan_plan',label='sched_scan_plan')
f.node('scard_data',label='scard_data')
f.node('wpa_blacklist',label='wpa_blacklist')
f.node('wps_er',label='wps_er')
f.node('mesh_rsn',label='mesh_rsn')
f.node('wpabuf',label='wpabuf')
f.node('ibss_rsn',label='ibss_rsn')
f.node('p2p_go_neg_results',label='p2p_go_neg_results')
f.node('p2p_group',label='p2p_group')
f.node('bgscan_ops',label='bgscan_ops')
f.node('autoscan_ops',label='autoscan_ops')
f.node('osu_provider',label='osu_provider')
f.node('ext_password_data',label='ext_password_data')
f.node('hostapd_hw_modes',label='hostapd_hw_modes')
f.node('neighbor_report',label='neighbor_report')
f.node('wmm_ac_assoc_data',label='wmm_ac_assoc_data')
f.node('wmm_tspec_element',label='wmm_tspec_element')
f.node('wmm_ac_addts_request',label='wmm_ac_addts_request')
f.node('rrm_data',label='rrm_data')
f.node('beacon_rep_data',label='beacon_rep_data')
f.node('fst_iface',label='fst_iface')
f.node('sched_scan_relative_params',label='sched_scan_relative_params')

## wpa_sm
f.node('wpa_ptk',label='wpa_ptk')
f.node('wpa_gtk',label='wpa_gtk')
f.node('wpa_igtk',label='wpa_igtk')
f.node('rsn_pmksa_cache',label='rsn_pmksa_cache')
f.node('rsn_pmksa_cache_entry',label='rsn_pmksa_cache_entry')
f.node('wpa_sm_ctx',label='wpa_sm_ctx')
f.node('wpa_tdls_peer',label='wpa_tdls_peer')

## eapol_sm
f.node('eap_sm',label='eap_sm')
f.node('eapol_config',label='eapol_config')
f.node('eapol_ctx',label='eapol_ctx')
f.node('eap_proxy_sm',label='eap_proxy_sm')

## eap_sm
f.node('eap_method',label='eap_method')
f.node('eapol_callbacks',label='eapol_callbacks')


## hostapd_iface
f.node('hapd_interfaces',label='hapd_interfaces')
f.node('hostapd_config',label='hostapd_config')
f.node('hostapd_data',label='hostapd_data')
f.node('ap_info',label='ap_info')
f.node('hostapd_rate_data',label='hostapd_rate_data')


## p2p_data
f.node('p2p_config',label='p2p_config')
f.node('p2p_device',label='p2p_device')
f.node('p2p_sd_query',label='p2p_sd_query')
f.node('p2p_channels',label='p2p_channels')
f.node('p2p_pending_action_tx',label='p2p_pending_action_tx')
f.node('p2ps_advertisement',label='p2ps_advertisement')
f.node('p2ps_provision',label='p2ps_provision')

## wpa_freq_range_list
f.node('wpa_freq_range',label='wpa_freq_range')


## wps_context

f.node('wps_registrar',label='wps_registrar')
f.node('wps_device_data',label='wps_device_data')
f.node('upnp_wps_device_sm',label='upnp_wps_device_sm')
f.node('upnp_pending_message',label='upnp_pending_message')


# wpa_driver_scan_params
f.node('wpa_driver_scan_ssid',label='wpa_driver_scan_ssid')
f.node('wpa_driver_scan_filter',label='wpa_driver_scan_filter')
f.node('sched_scan_plan',label='sched_scan_plan')


# gas_query
f.node('gas_query_pending',label='gas_query_pending')



## wpa_config wpa_cred   hostapd_wmm_ac_params
f.node('wpa_cred',label='wpa_cred')
f.node('hostapd_wmm_ac_params',label='hostapd_wmm_ac_params')

## l2_packet_data
f.node('sockaddr_un',label='sockaddr_un')


## wpa_bss
f.node('wpa_bss_anqp',label='wpa_bss_anqp')


## wps_er
f.node('http_server',label='http_server')
f.node('in_addr',label='in_addr')

## mesh_rsn
f.node('wpa_authenticator',label='wpa_authenticator')

## ibss_rsn
f.node('ibss_rsn_peer',label='ibss_rsn_peer')

## p2p_group
f.node('p2p_group_config',label='p2p_group_config')
f.node('p2p_group_member',label='p2p_group_member')

## osu_provider

f.node('osu_lang_string',label='osu_lang_string')
f.node('osu_icon',label='osu_icon')

## ext_password_data

f.node('ext_password_backend',label='ext_password_backend')

## hostapd_hw_modes
f.node('hostapd_channel_data',label='hostapd_channel_data')
f.node('he_capabilities',label='he_capabilities')


## hostapd_hw_modes
f.node('measurement_pilot',label='measurement_pilot')
f.node('multiple_bssid',label='multiple_bssid')

## wmm_ac_assoc_data
f.node('ac_params',label='ac_params')

## beacon_rep_data
f.node('bitfield',label='bitfield')

##fst_iface_cfg
f.node('fst_group',label='fst_group')
f.node('fst_wpa_obj',label='fst_wpa_obj')
f.node('fst_iface_cfg',label='fst_iface_cfg')

## eap_peer_config
f.node('eap_method_type',label='eap_method_type')



## hostapd_config
f.node('hostapd_bss_config',label='hostapd_bss_config')
f.node('hostapd_tx_queue_params',label='hostapd_tx_queue_params')



## hostapd_bss_config
f.node('hostapd_eap_user',label='hostapd_eap_user')
f.node('hostapd_ip_addr',label='hostapd_ip_addr')
f.node('hostapd_radius_servers',label='hostapd_radius_servers')
f.node('hostapd_radius_attr',label='hostapd_radius_attr')
f.node('hostapd_ssid',label='hostapd_ssid')
f.node('mac_acl_entry',label='mac_acl_entry')

## hostapd_ssid
f.node('hostapd_wpa_psk',label='hostapd_wpa_psk')
f.node('hostapd_wep_keys',label='hostapd_wep_keys')

## mac_acl_entry

f.node('vlan_description',label='vlan_description')

## hostapd_data
f.node('sta_info',label='sta_info')
f.node('radius_client_data',label='radius_client_data')
f.node('radius_das_data',label='radius_das_data')
f.node('iapp_data',label='iapp_data')
f.node('hostapd_cached_radius_acl',label='hostapd_cached_radius_acl')
f.node('hostapd_acl_query_data',label='hostapd_acl_query_data')
f.node('eapol_authenticator',label='eapol_authenticator')
f.node('rsn_preauth_interface',label='rsn_preauth_interface')
f.node('wps_stat',label='wps_stat')
f.node('hostapd_probereq_cb',label='hostapd_probereq_cb')
f.node('hostapd_freq_params',label='hostapd_freq_params')



## p2p_config

f.node('p2p_channel',label='p2p_channel')


## p2p_channels
f.node('p2p_reg_class',label='p2p_reg_class')

## p2p_device

f.node('p2p_peer_info',label='p2p_peer_info')


## wps_registrar
f.node('wps_pbc_session',label='wps_pbc_session')
f.node('wps_registrar_device',label='wps_registrar_device')



## upnp_wps_device_sm
f.node('advertisement_state_machine',label='advertisement_state_machine')


## wpa_authenticator
f.node('wpa_group',label='wpa_group')
f.node('wpa_auth_config',label='wpa_auth_config')
f.node('wpa_auth_callbacks',label='wpa_auth_callbacks')
f.node('wpa_ft_pmk_cache',label='wpa_ft_pmk_cache')

## wpa_ft_pmk_r0_sa

f.node('wpa_ft_pmk_r1_sa',label='wpa_ft_pmk_r1_sa')
f.node('wpa_ft_pmk_r0_sa',label='wpa_ft_pmk_r0_sa')
################################################


# 独立结构

f.node('p2p_message',label='p2p_message')
f.node('p2p_group_info',label='p2p_group_info')
f.node('p2p_client_info',label='p2p_client_info')
f.node('wpa_driver_capa',label='wpa_driver_capa')
f.node('wowlan_triggers',label='wowlan_triggers')
################################################
f.attr('node', shape='doublecircle')
f.node('dl_list')
f.edge('dl_list', 'dl_list', label='pre')
f.edge('dl_list', 'dl_list', label='next')

f.edge('ctrl_iface_global_priv', 'wpa_global', label='N[*global]')
f.edge('ctrl_iface_global_priv', 'dl_list', label='ctrl_dst')
f.edge('ctrl_iface_global_priv', 'dl_list', label='msg_queue')


f.edge('wpa_global', 'ctrl_iface_global_priv', label='N[*ctrl_iface]')
f.edge('wpa_global', 'dl_list', label='p2p_srv_bonjour')
f.edge('wpa_global', 'dl_list', label='p2p_srv_upnp')
f.edge('wpa_global', 'wpa_supplicant', label='N[*ifaces]')
f.edge('wpa_global', 'wpa_supplicant', label='N[*p2p_init_wpa_s]')
f.edge('wpa_global', 'wpa_supplicant', label='N[*p2p_group_formation]')
f.edge('wpa_global', 'wpa_supplicant', label='N[*p2p_invite_group]')
f.edge('wpa_global', 'wpa_params', label='params')
f.edge('wpa_global', 'wpas_hidl_priv', label='N[*hidl]')
f.edge('wpa_global', 'void**', label='drv_privMethod')
f.edge('wpa_global', 'p2p_data', label='N[*p2p]')
f.edge('wpa_global', 'wpa_freq_range_list', label='p2p_disallow_freq')
f.edge('wpa_global', 'wpa_freq_range_list', label='p2p_go_avoid_freq')
f.edge('wpa_global', 'psk_list_entry', label='N[*add_psk]')


f.edge('wpa_supplicant', 'wpa_global', label='N[*global]')
f.edge('wpa_supplicant', 'wpa_supplicant', label='N[*parent]')
f.edge('wpa_supplicant', 'wpa_supplicant', label='N[*p2pdev]')
f.edge('wpa_supplicant', 'wpa_supplicant', label='N[*next]')
f.edge('wpa_supplicant', 'dl_list', label='radio_list')
f.edge('wpa_supplicant', 'dl_list', label='bss')
f.edge('wpa_supplicant', 'dl_list', label='bss_id')
f.edge('wpa_supplicant', 'dl_list', label='icon_head')
f.edge('wpa_supplicant', 'dl_list', label='bss_tmp_disallowed')
f.edge('wpa_supplicant', 'dl_list', label='fils_hlp_req')
f.edge('wpa_supplicant', 'ctrl_iface_priv', label='N[*ctrl_iface]')
f.edge('wpa_supplicant', 'wpa_sm', label='N[*wpa]')

f.edge('wpa_supplicant', 'eapol_sm', label='N[*eapol]')
f.edge('wpa_supplicant', 'hostapd_iface', label='N[*ifmsh]')
f.edge('wpa_supplicant', 'hostapd_iface', label='N[*ap_iface]')

f.edge('wpa_supplicant', 'wpa_driver_ops', label='N[*driver]')
f.edge('wpa_supplicant', 'wps_context', label='N[*wps]')
f.edge('wpa_supplicant', 'wpa_radio', label='N[*radio]')
f.edge('wpa_supplicant', 'wpa_radio_work', label='N[*scan_work]')
f.edge('wpa_supplicant', 'wpa_radio_work', label='N[*p2p_scan_work]')
f.edge('wpa_supplicant', 'wpa_radio_work', label='N[*p2p_listen_work]')
f.edge('wpa_supplicant', 'wpa_radio_work', label='N[*p2p_send_action_work]')
f.edge('wpa_supplicant', 'wpa_radio_work', label='N[*connect_work]')

f.edge('wpa_supplicant', 'wpa_driver_scan_params', label='N[*autoscan_params]')
f.edge('wpa_supplicant', 'wps_ap_info', label='N[*wps_ap]')
f.edge('wpa_supplicant', 'gas_query', label='N[*gas]')
f.edge('wpa_supplicant', 'void*', label='hidl_object_key(func)')
f.edge('wpa_supplicant', 'void*', label='drv_priv(func)')
f.edge('wpa_supplicant', 'void*', label='global_drv_priv(func)')
f.edge('wpa_supplicant', 'void*', label='scan_res_handler(func)')
f.edge('wpa_supplicant', 'void*', label='ap_configured_cb(func)')
f.edge('wpa_supplicant', 'void*', label='ap_configured_cb_ctx(func)')
f.edge('wpa_supplicant', 'void*', label='ap_configured_cb_data(func)')
f.edge('wpa_supplicant', 'void*', label='pending_action_tx_status_cb(func)')
f.edge('wpa_supplicant', 'void*', label='autoscan_priv(func)')

f.edge('wpa_supplicant', 'wpa_config', label='N[*conf]')
f.edge('wpa_supplicant', 'l2_packet_data', label='N[*l2]')
f.edge('wpa_supplicant', 'l2_packet_data', label='N[*l2_br]')

f.edge('wpa_supplicant', 'wpa_bss', label='N[*current_bss]')
f.edge('wpa_supplicant', 'wpa_bss', label='N[**last_scan_res]')
f.edge('wpa_supplicant', 'wpa_bss', label='N[*interworking_gas_bss]')

f.edge('wpa_supplicant', 'wpa_ssid', label='N[*current_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*last_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*next_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*prev_scan_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*prev_sched_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*p2p_last_4way_hs_fail]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*bgscan_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid', label='N[*connect_without_scan]')

f.edge('wpa_supplicant', 'wpa_ssid_value', label='N[*disallow_aps_ssid]')
f.edge('wpa_supplicant', 'wpa_ssid_value', label='N[*ssids_from_scan_req]')
f.edge('wpa_supplicant', 'sched_scan_plan', label='N[*sched_scan_plans]')
f.edge('wpa_supplicant', 'scard_data', label='N[*scard]')
f.edge('wpa_supplicant', 'wpa_blacklist', label='N[*blacklist]')
f.edge('wpa_supplicant', 'wps_er', label='N[*wps_er]')
f.edge('wpa_supplicant', 'mesh_rsn', label='N[*mesh_rsn]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*pending_eapol_rx]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*pending_action_tx]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*p2p_oob_dev_pw]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*last_gas_resp]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*prev_gas_resp]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*vendor_elem<15> ]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*fst_ies]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*received_mb_ies]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*lci]')
f.edge('wpa_supplicant', 'wpabuf', label='N[*ric_ies]')
f.edge('wpa_supplicant', 'ibss_rsn', label='N[*ibss_rsn]')
f.edge('wpa_supplicant', 'p2p_go_neg_results', label='N[*go_params]')
f.edge('wpa_supplicant', 'p2p_group', label='N[*p2p_group]')
f.edge('wpa_supplicant', 'bgscan_ops', label='N[*bgscan_ops]')
f.edge('wpa_supplicant', 'autoscan_ops', label='N[*autoscan_ops]')
f.edge('wpa_supplicant', 'osu_provider', label='N[*osu_prov]')
f.edge('wpa_supplicant', 'ext_password_data', label='N[*ext_pw]')
f.edge('wpa_supplicant', 'hostapd_hw_modes', label='N[*modes]')
f.edge('wpa_supplicant', 'neighbor_report', label='N[*wnm_neighbor_report_elements]')
f.edge('wpa_supplicant', 'wmm_ac_assoc_data', label='N[*]')
f.edge('wpa_supplicant', 'wmm_tspec_element', label='N[*tspecs<4><3>]')
f.edge('wpa_supplicant', 'wmm_tspec_element', label='N[*last_tspecs]')
f.edge('wpa_supplicant', 'wmm_ac_addts_request', label='N[*addts_request]')
f.edge('wpa_supplicant', 'rrm_data', label='rrm')
f.edge('wpa_supplicant', 'beacon_rep_data', label='beacon_rep_data')
f.edge('wpa_supplicant', 'fst_iface', label='N[*fst]')
f.edge('wpa_supplicant', 'sched_scan_relative_params', label='srp')

f.edge('ctrl_iface_priv', 'wpa_supplicant', label='N[*wpa_s]')
f.edge('ctrl_iface_priv', 'dl_list', label='ctrl_dst')
f.edge('ctrl_iface_priv', 'dl_list', label='msg_queue')

f.edge('wpas_hidl_priv', 'wpa_global', label='N[*global]')
f.edge('wpas_hidl_priv', 'void*', label='hidl_manager')


f.edge('wpa_sm', 'eapol_sm', label='N[*eapol]')
f.edge('wpa_sm', 'eapol_sm', label='N[*preauth_eapol]')
f.edge('wpa_sm', 'dl_list', label='pmksa_candidates')
f.edge('wpa_sm', 'l2_packet_data', label='N[*l2_preauth]')
f.edge('wpa_sm', 'l2_packet_data', label='N[*l2_preauth_br]')
f.edge('wpa_sm', 'l2_packet_data', label='N[*l2_tdls]')
f.edge('wpa_sm', 'void*', label='scard_ctx')
f.edge('wpa_sm', 'wpa_ptk', label='ptk')
f.edge('wpa_sm', 'wpa_ptk', label='tptk')
f.edge('wpa_sm', 'wpa_gtk', label='gtk')
f.edge('wpa_sm', 'wpa_gtk', label='gtk_wnm_sleep')
f.edge('wpa_sm', 'wpa_igtk', label='igtk')
f.edge('wpa_sm', 'wpa_igtk', label='igtk_wnm_sleep')
f.edge('wpa_sm', 'rsn_pmksa_cache', label='[*pmksa]')
f.edge('wpa_sm', 'rsn_pmksa_cache_entry', label='[*cur_pmksa]')
f.edge('wpa_sm', 'wpa_sm_ctx', label='N[*ctx]')
f.edge('wpa_sm', 'wpa_tdls_peer', label='N[*tdls]')



f.edge('eapol_sm', 'eap_sm', label='N[*eap_sm]')
f.edge('eapol_sm', 'eap_peer_config', label='N[*config]')
f.edge('eapol_sm', 'wpabuf', label='N[*eapReqData]')
f.edge('eapol_sm', 'eapol_config', label='conf')
f.edge('eapol_sm', 'eapol_ctx', label='N[*ctx]')
f.edge('eapol_sm', 'eap_proxy_sm', label='N[*eap_proxy]')


f.edge('eap_sm', 'wpabuf', label='N[*lastRespData]')
f.edge('eap_sm', 'eap_method', label='N[*m]')
f.edge('eap_sm', 'eapol_callbacks', label='N[*eapol_cb]')
f.edge('eap_sm', 'void*', label='msg_ctx')
f.edge('eap_sm', 'void*', label='scard_ctx')
f.edge('eap_sm', 'void*', label='ssl_ctx')
f.edge('eap_sm', 'void*', label='ssl_ctx2')
f.edge('eap_sm', 'dl_list', label='erp_keys')

f.edge('eap_method', 'void**', label='N[manyFuncPoint]')
f.edge('eap_method', 'eap_method', label='N[*next]')


f.edge('hostapd_iface', 'hapd_interfaces', label='N[*interfaces]')
f.edge('hostapd_iface', 'hostapd_config', label='N[*conf]')
f.edge('hostapd_iface', 'wpabuf', label='N[*fst_ies]')
f.edge('hostapd_iface', 'fst_iface', label='N[*fst]')
f.edge('hostapd_iface', 'ap_info', label='N[*ap_list]')
f.edge('hostapd_iface', 'ap_info', label='N[*ap_hash<256>]')
f.edge('hostapd_iface', 'hostapd_hw_modes', label='N[*current_mode]')
f.edge('hostapd_iface', 'hostapd_hw_modes', label='N[*hw_features]')
f.edge('hostapd_iface', 'hostapd_rate_data', label='N[*current_rates]')
f.edge('hostapd_iface', 'dl_list', label='sta_seen')
f.edge('hostapd_iface', 'void*', label='scan_cb(hostapd_iface)')
f.edge('hostapd_iface', 'hostapd_data', label='N[**bss]')
f.edge('wpa_driver_ops', 'void**', label='Many[driverMethod]')



f.edge('p2p_data', 'dl_list', label='devices')
f.edge('p2p_data', 'p2p_config', label='N[*cfg]')
f.edge('p2p_data', 'p2p_device', label='N[*go_neg_peer]')
f.edge('p2p_data', 'p2p_device', label='N[*invite_peer]')
f.edge('p2p_data', 'p2p_device', label='N[*last_p2p_find_oper]')
f.edge('p2p_data', 'p2p_device', label='N[*sd_peer]')
f.edge('p2p_data', 'p2p_device', label='N[*pending_client_disc_go]')
f.edge('p2p_data', 'p2p_sd_query', label='N[*sd_query]')
f.edge('p2p_data', 'p2p_sd_query', label='N[*sd_queries]')
f.edge('p2p_data', 'wpa_freq_range_list', label='no_go_freq')
f.edge('p2p_data', 'p2p_pending_action_tx', label='N[*after_scan_tx]')
f.edge('p2p_data', 'p2p_group', label='N[**groups]')
f.edge('p2p_data', 'p2ps_advertisement', label='N[*p2ps_adv_list]')
f.edge('p2p_data', 'p2ps_provision', label='N[*p2ps_prov]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_beacon]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_probe_req]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_probe_resp]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_assoc_req]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_invitation]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_prov_disc_req]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_prov_disc_resp]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_ie_go_neg]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_dev_info]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_assoc_bssid]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_coupled_sink_info]')
f.edge('p2p_data', 'wpabuf', label='N[*wfd_r2_dev_info]')
f.edge('p2p_data', 'wpabuf', label='N[**vendor_elem]')

f.edge('wpa_freq_range_list', 'wpa_freq_range', label='N[*next]')
f.edge('psk_list_entry', 'dl_list', label='list')


f.edge('wps_context', 'wps_registrar', label='N[*]')
f.edge('wps_context', 'wps_device_data', label='dev')
f.edge('wps_context', 'void*', label='N[*funcPoint]')
f.edge('wps_context', 'void*',  label='cb_ctx')
f.edge('wps_context', 'upnp_wps_device_sm', label='N[*wps_upnp]')
f.edge('wps_context', 'upnp_pending_message', label='N[*upnp_msgs]')
f.edge('wps_context', 'wpabuf',  label='N[*dh_privkey]')
f.edge('wps_context', 'wpabuf',  label='N[*dh_pubkey]')
f.edge('wps_context', 'wpabuf',  label='N[*ap_nfc_dh_pubkey]')
f.edge('wps_context', 'wpabuf',  label='N[*ap_nfc_dh_privkey]')
f.edge('wps_context', 'wpabuf',  label='N[*ap_nfc_dev_pw]')

f.edge('wpa_radio', 'dl_list',  label='ifaces')
f.edge('wpa_radio', 'dl_list',  label='work')

f.edge('wpa_radio_work', 'dl_list',  label='list')
f.edge('wpa_radio_work', 'wpa_supplicant',  label='N[wpa_s]')
f.edge('wpa_radio_work', 'void*',  label='(*cb)(wpa_radio_work)')
f.edge('wpa_radio_work', 'void*',  label='ctxMethodPoint')


f.edge('wpa_driver_scan_params', 'wpa_driver_scan_ssid',  label='N[ssids<16>]')
f.edge('wpa_driver_scan_params', 'wpa_driver_scan_filter',  label='N[*filter_ssids]')
f.edge('wpa_driver_scan_params', 'sched_scan_plan',  label='N[*sched_scan_plans]')



f.edge('gas_query', 'wpa_supplicant',  label='N[*wpa_s]')
f.edge('gas_query', 'dl_list',  label='pending')
f.edge('gas_query', 'gas_query_pending',  label='N[*current]')
f.edge('gas_query', 'wpa_radio_work',  label='N[*work]')


f.edge('wpa_config', 'wpa_ssid',  label='N[*ssid]')
f.edge('wpa_config', 'wpa_ssid',  label='N[**pssid]')
f.edge('wpa_config', 'wpa_cred',  label='N[*cred]')
f.edge('wpa_config', 'p2p_channels',  label='N[*p2p_pref_chan]')
f.edge('wpa_config', 'wpa_freq_range_list',  label='p2p_no_go_freq')
f.edge('wpa_config', 'hostapd_wmm_ac_params',  label='wmm_ac_params[4]')
f.edge('wpa_config', 'wpabuf',  label='N[*wps_vendor_ext_m1]')
f.edge('wpa_config', 'wpabuf',  label='N[*wps_vendor_ext<10>]')
f.edge('wpa_config', 'wpabuf',  label='N[*wps_nfc_dh_pubkey]')
f.edge('wpa_config', 'wpabuf',  label='N[*wps_nfc_dh_privkey]')
f.edge('wpa_config', 'wpabuf',  label='N[*wps_nfc_dev_pw]')
f.edge('wpa_config', 'wpabuf',  label='N[*ap_vendor_elements]')


f.edge('l2_packet_data', 'void*',  label='rx_callback')
f.edge('l2_packet_data', 'void*',  label='rx_callback_ctx')
f.edge('l2_packet_data', 'sockaddr_un',  label='priv_addr')

f.edge('wpa_bss', 'dl_list',  label='list')
f.edge('wpa_bss', 'dl_list',  label='list_id')
f.edge('wpa_bss', 'wpa_bss_anqp',  label='N[*anqp]')


f.edge('wpa_ssid', 'wpa_ssid',  label='N[*next]')
f.edge('wpa_ssid', 'wpa_ssid',  label='N[*pre]')
f.edge('wpa_ssid', 'eap_peer_config',  label='eap')
f.edge('wpa_ssid', 'dl_list',  label='psk_list')

f.edge('wpa_blacklist', 'wpa_blacklist',  label='N[*next]')


f.edge('wps_er', 'wps_context',  label='N[*wps]')
f.edge('wps_er', 'dl_list',  label='ap')
f.edge('wps_er', 'dl_list',  label='ap_unsubscribing')
f.edge('wps_er', 'dl_list',  label='ap_settings')
f.edge('wps_er', 'http_server',  label='N[*http_srv]')
f.edge('wps_er', 'in_addr',  label='filter_addr')

f.edge('wps_er', 'void*',  label='deinit_done_cb')
f.edge('wps_er', 'void*',  label='deinit_done_ctx')


f.edge('mesh_rsn', 'wpa_supplicant',  label='N[*wpa_s]')
f.edge('mesh_rsn', 'wpa_authenticator',  label='N[*auth]')

f.edge('ibss_rsn', 'wpa_supplicant',  label='N[*wpa_s]')
f.edge('ibss_rsn', 'wpa_authenticator',  label='N[*auth_group]')
f.edge('ibss_rsn', 'ibss_rsn_peer',  label='N[*peers]')



f.edge('p2p_group', 'p2p_data',  label='N[*p2p]')
f.edge('p2p_group', 'p2p_group_config',  label='N[*cfg]')
f.edge('p2p_group', 'p2p_group_member',  label='N[*members]')
f.edge('p2p_group', 'wpabuf',  label='N[*noa]')
f.edge('p2p_group', 'wpabuf',  label='N[*wfd_ie]')

f.edge('bgscan_ops', 'void**',  label='N[**initMethod]')
f.edge('bgscan_ops', 'void*',  label='N[*methodPoint]')


f.edge('autoscan_ops', 'void**',  label='N[**initMethod]')
f.edge('autoscan_ops', 'void*',  label='N[*notify_scan]')

f.edge('osu_provider', 'osu_lang_string',  label='N[friendly_name<10>]')
f.edge('osu_provider', 'osu_lang_string',  label='N[serv_desc<10>]')
f.edge('osu_provider', 'osu_icon',  label='N[osu_icon<10>]')


f.edge('ext_password_data', 'ext_password_backend',  label='N[*backend]')
f.edge('ext_password_data', 'void*',  label='priv')


f.edge('ext_password_backend', 'void**',  label='[init**]')
f.edge('ext_password_backend', 'void**',  label='[wpabuf get**]')


f.edge('hostapd_hw_modes', 'hostapd_channel_data',  label='[*channels]')
f.edge('hostapd_hw_modes', 'he_capabilities',  label='he_capab')

f.edge('neighbor_report', 'measurement_pilot',  label='N[*meas_pilot]')
f.edge('neighbor_report', 'multiple_bssid',  label='N[*mul_bssid]')

f.edge('wmm_ac_assoc_data', 'ac_params',  label='N[ ac_params<4> ]')

f.edge('wmm_ac_addts_request', 'wmm_tspec_element',  label='tspec')

f.edge('rrm_data', 'void*',  label='notify_neighbor_rep(func)')
f.edge('rrm_data', 'void*',  label='neighbor_rep_cb_ctx(func)')


f.edge('beacon_rep_data', 'wpa_driver_scan_params',  label='scan_params')
f.edge('beacon_rep_data', 'bitfield',  label='N[*eids]')


f.edge('fst_iface', 'fst_group',  label='N[*group]')
f.edge('fst_iface', 'fst_wpa_obj',  label='iface_obj')
f.edge('fst_iface', 'fst_iface_cfg',  label='cfg')
f.edge('fst_iface', 'dl_list',  label='N[*mb_ie]')
f.edge('fst_iface', 'wpabuf',  label='group_lentry')

f.edge('eap_peer_config', 'eap_method_type',  label='N[*eap_methods]')


f.edge('eapol_ctx', 'void*',  label='*ctx(func)')
f.edge('eapol_ctx', 'void*',  label='*msg_ctx(func)')
f.edge('eapol_ctx', 'void*',  label='*cb_ctx(func)')
f.edge('eapol_ctx', 'void*',  label='*eapol_done_cb')
f.edge('eapol_ctx', 'wps_context',  label='N[*wps]')



f.edge('eapol_callbacks', 'void*',  label='N[*many(func)]')


f.edge('hapd_interfaces', 'void*',  label='N[*many(func)]')
f.edge('hapd_interfaces', 'void*',  label='driver_init(func)')
f.edge('hapd_interfaces', 'void*',  label='ctrl_iface_init(func)')


f.edge('hostapd_config', 'hostapd_bss_config',  label='N[**bss]')
f.edge('hostapd_config', 'hostapd_bss_config',  label='N[*last_bss]')
f.edge('hostapd_config', 'wpa_freq_range_list',  label='acs_ch_list')
f.edge('hostapd_config', 'wpa_driver_ops',  label='N[*driver]')
f.edge('hostapd_config', 'hostapd_tx_queue_params',  label='tx_queue[4]')
f.edge('hostapd_config', 'hostapd_wmm_ac_params',  label='wmm_ac_params[4]')
f.edge('hostapd_config', 'fst_iface_cfg',  label='fst_cfg')
f.edge('hostapd_config', 'wpabuf',  label='N[*lci]')
f.edge('hostapd_config', 'wpabuf',  label='N[*civic]')


f.edge('hostapd_bss_config', 'hostapd_eap_user',  label='N[*eap_user]')
f.edge('hostapd_bss_config', 'hostapd_ip_addr',  label='radius_das_client_addr')
f.edge('hostapd_bss_config', 'hostapd_ip_addr',  label='own_ip_addr')
f.edge('hostapd_bss_config', 'hostapd_radius_servers',  label='N[*radius]')
f.edge('hostapd_bss_config', 'hostapd_radius_attr',  label='N[*radius_auth_req_attr]')
f.edge('hostapd_bss_config', 'hostapd_radius_attr',  label='N[*radius_acct_req_attr]')
f.edge('hostapd_bss_config', 'hostapd_ssid',  label='ssid')
f.edge('hostapd_bss_config', 'mac_acl_entry',  label='N[*accept_mac]')
f.edge('hostapd_bss_config', 'mac_acl_entry',  label='N[*deny_mac]')



f.edge('hostapd_eap_user', 'hostapd_eap_user',  label='N[*next]')
f.edge('hostapd_eap_user', 'hostapd_radius_attr',  label='N[*accept_attr]')
f.edge('hostapd_eap_user', 'void*',  label='methods[8]')


f.edge('hostapd_radius_servers', 'hostapd_radius_servers',  label='[*auth_servers]')
f.edge('hostapd_radius_servers', 'hostapd_radius_servers',  label='[*auth_server]')
f.edge('hostapd_radius_servers', 'hostapd_radius_servers',  label='[*acct_servers]')
f.edge('hostapd_radius_servers', 'hostapd_radius_servers',  label='[*acct_server]')
f.edge('hostapd_radius_servers', 'hostapd_ip_addr',  label='N[*next]')


f.edge('hostapd_radius_attr', 'hostapd_radius_attr',  label='N[*next]')
f.edge('hostapd_radius_attr', 'wpabuf',  label='N[*val]')


f.edge('hostapd_ssid', 'hostapd_wpa_psk',  label='N[*wpa_psk]')
f.edge('hostapd_ssid', 'hostapd_wep_keys',  label='wep')

f.edge('mac_acl_entry', 'vlan_description',  label='vlan_id')


f.edge('hostapd_data', 'hostapd_iface',  label='N[*iface]')
f.edge('hostapd_data', 'hostapd_config',  label='N[*iconf]')
f.edge('hostapd_data', 'hostapd_bss_config',  label='N[*conf]')
f.edge('hostapd_data', 'void*',  label='*drv_priv')
f.edge('hostapd_data', 'void*',  label='(*new_assoc_sta_cb)')
f.edge('hostapd_data', 'void*',  label='*msg_ctx')
f.edge('hostapd_data', 'void*',  label='*ssl_ctx')
f.edge('hostapd_data', 'void*',  label='*eap_sim_db_priv')
f.edge('hostapd_data', 'void*',  label='(*sta_authorized_cb)')
f.edge('hostapd_data', 'void*',  label='*sta_authorized_cb_ctx')
f.edge('hostapd_data', 'void*',  label='*new_psk_cb_ctx')

f.edge('hostapd_data', 'dl_list',  label='N[*erp_keys]')
f.edge('hostapd_data', 'dl_list',  label='N[*nr_db]')
f.edge('hostapd_data', 'wpabuf',  label='N[*time_adv]')
f.edge('hostapd_data', 'wpabuf',  label='N[*wps_beacon_ie]')
f.edge('hostapd_data', 'wpabuf',  label='N[*wps_probe_resp_ie]')
f.edge('hostapd_data', 'wpabuf',  label='N[*p2p_beacon_ie]')
f.edge('hostapd_data', 'wpabuf',  label='N[*p2p_probe_resp_ie]')
f.edge('hostapd_data', 'l2_packet_data',  label='N[*l2]')
f.edge('hostapd_data', 'wps_context',  label='N[*wps]')
f.edge('hostapd_data', 'p2p_data',  label='N[*p2p]')
f.edge('hostapd_data', 'p2p_group',  label='N[*p2p_group]')
f.edge('hostapd_data', 'wpa_authenticator',  label='N[*wpa_auth]')
f.edge('hostapd_data', 'upnp_wps_device_sm',  label='N[*wps_upnp]')
f.edge('hostapd_data', 'sta_info',  label='N[*sta_hash[256]]')
f.edge('hostapd_data', 'radius_client_data',  label='N[*radius]')
f.edge('hostapd_data', 'radius_das_data',  label='N[*radius_das]')
f.edge('hostapd_data', 'iapp_data',  label='N[*iapp]')
f.edge('hostapd_data', 'hostapd_cached_radius_acl',  label='N[*acl_cache]')
f.edge('hostapd_data', 'hostapd_acl_query_data',  label='N[*acl_queries]')
f.edge('hostapd_data', 'eapol_authenticator',  label='N[*eapol_auth]')
f.edge('hostapd_data', 'rsn_preauth_interface',  label='N[*preauth_iface]')
f.edge('hostapd_data', 'radius_server_data',  label='N[*radius_srv]')
f.edge('hostapd_data', 'wps_stat',  label='N[*wps_stats]')
f.edge('hostapd_data', 'hostapd_probereq_cb',  label='N[*probereq_cb]')
f.edge('hostapd_data', 'hostapd_freq_params',  label='N[*cs_freq_params]')



f.edge('p2p_config', 'p2p_channels',  label='channels')
f.edge('p2p_config', 'p2p_channels',  label='cli_channels')
f.edge('p2p_config', 'p2p_channel',  label='N[*pref_chan]')

f.edge('p2p_config', 'void*',  label='*cb_ctx')
f.edge('p2p_config', 'void*',  label='*p2p_scan')
f.edge('p2p_config', 'void*',  label='*send_probe_resp')
f.edge('p2p_config', 'void*',  label='*send_action)')
f.edge('p2p_config', 'void*',  label='*get_noa')
f.edge('p2p_config', 'void*',  label='N[Many(func)]')


f.edge('p2p_channels', 'p2p_reg_class',  label='N[reg_class<15>]')

f.edge('p2p_device', 'dl_list',  label='list')
f.edge('p2p_device', 'p2p_peer_info',  label='info')
f.edge('p2p_device', 'p2p_channels',  label='channels')
f.edge('p2p_device', 'wpabuf',  label='N[*go_neg_conf]')


f.edge('p2p_peer_info', 'wpabuf',  label='N[*p2ps_instance]')

f.edge('p2p_sd_query', 'p2p_sd_query',  label='N[*next]')
f.edge('p2p_sd_query', 'wpabuf',  label='N[*tlvs]')

f.edge('p2ps_advertisement', 'p2ps_advertisement',  label='N[*next]')


f.edge('wps_registrar', 'wps_context',  label='N[*wps]')
f.edge('wps_registrar', 'dl_list',  label='pins')
f.edge('wps_registrar', 'dl_list',  label='nfc_pw_tokens')
f.edge('wps_registrar', 'wps_pbc_session',  label='N[*pbc_sessions]')
f.edge('wps_registrar', 'wps_registrar_device',  label='N[*devices]')
f.edge('wps_registrar', 'wpabuf',  label='N[*extra_cred]')
f.edge('wps_registrar', 'void*',  label='N[*many(func)]')
f.edge('wps_registrar', 'void*',  label='*cb_ctx')
f.edge('wps_registrar', 'void*',  label='*reg_success_cb')

f.edge('wps_pbc_session', 'wps_pbc_session',  label='N[*next]')


f.edge('wps_registrar_device', 'wps_registrar_device',  label='N[*next]')
f.edge('wps_registrar_device', 'wps_device_data',  label='dev')

f.edge('wps_device_data', 'wpabuf',  label='N[*vendor_ext]')



f.edge('upnp_wps_device_sm', 'advertisement_state_machine',  label='advertisement')
f.edge('upnp_wps_device_sm', 'http_server',  label='N[*web_srv]')
f.edge('upnp_wps_device_sm', 'dl_list',  label='subscriptions')
f.edge('upnp_wps_device_sm', 'dl_list',  label='interfaces')
f.edge('upnp_wps_device_sm', 'dl_list',  label='msearch_replies')

f.edge('upnp_pending_message', 'upnp_pending_message',   label='N[*next]')


f.edge('wpa_authenticator', 'wpa_group',   label='N[*group]')
f.edge('wpa_authenticator', 'wpa_auth_config',   label='conf')
f.edge('wpa_authenticator', 'wpa_auth_callbacks',   label='N[*cb]')
f.edge('wpa_authenticator', 'wpa_ft_pmk_cache',   label='N[*ft_pmk_cache]')
f.edge('wpa_authenticator', 'void*',   label='*cb_ctx')
f.edge('wpa_authenticator', 'rsn_pmksa_cache',   label='N[*pmksa]')
f.edge('wpa_authenticator', 'bitfield',   label='N[*ip_pool]')

f.edge('wpa_group', 'wpa_group',    label='N[*next]')


f.edge('wpa_auth_callbacks', 'void*',    label='*disconnect')
f.edge('wpa_auth_callbacks', 'void*',    label='*many(func)')


f.edge('wpa_ft_pmk_cache', 'wpa_ft_pmk_r0_sa',    label='N[*pmk_r0]')
f.edge('wpa_ft_pmk_cache', 'wpa_ft_pmk_r1_sa',    label='N[*pmk_r1]')

f.edge('wpa_ft_pmk_r0_sa', 'wpa_ft_pmk_r0_sa',    label='N[*next]')
f.edge('wpa_ft_pmk_r1_sa', 'wpa_ft_pmk_r1_sa',    label='N[*next]')


f.edge('rsn_pmksa_cache', 'rsn_pmksa_cache_entry',    label='N[*pmksa]')
f.edge('rsn_pmksa_cache', 'wpa_sm',    label='N[*sm]')
f.edge('rsn_pmksa_cache', 'void*',    label='*ctx')


f.edge('rsn_pmksa_cache_entry', 'rsn_pmksa_cache_entry',    label='N[*next]')
f.edge('rsn_pmksa_cache_entry', 'void*',    label='*network_ctx')

#### 独立
f.edge('p2p_message', 'wpabuf',    label='N[*p2p_attributes]')
f.edge('p2p_message', 'wpabuf',    label='N[*wps_attributes]')
f.edge('p2p_message', 'wpabuf',    label='N[*wfd_subelems]')


f.edge('p2p_group_info', 'p2p_client_info',    label='N[client<50>]')
f.edge('wpa_driver_capa', 'wowlan_triggers',    label='wowlan_triggers')

f.view()
print(f.source)

```