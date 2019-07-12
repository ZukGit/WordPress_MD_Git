
# iw 命令使用

## iw --debug 


## iw --version
```
iw --version
iw version 4.1
```

###  iw event 


####  iw event  -t
```
parker:/ # iw event -t
1559633553.396064: wlan0 (phy #0): scan started
1559633556.914404: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
1559633566.102894: wlan0 (phy #0): disconnected (local request)
1559633566.551131: regulatory domain change: set to CN by a user request
1559633567.397672: wlan0 (phy #0): vendor event 001374:54
1559633567.409337: wlan0: unknown event 88 (ch_switch_notify)
1559633567.425421: phy #0: vendor event 001374:165
1559633567.900197: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
1559633574.962514: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
1559633576.986614: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
1559633578.883375: wlan0 (phy #0): scan started
1559633580.720320: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
1559633580.943521: wlan0 (phy #0): connected to 1c:5f:2b:5e:d5:53
1559633584.709757: wlan0 (phy #0): scan started
1559633588.822316: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
1559633591.038426: wlan0 (phy #0): disconnected (local request)
1559633591.585914: phy #0: vendor event 001374:10
1559633591.586145: phy #0: unknown event 113 (Unknown command (113))


```

####  iw event  -r
```

130|parker:/ # iw event  -r
0.000000: wlan0 (phy #0): scan started
1.860247: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
1.754037: phy #0: vendor event 001374:10
0.000084: phy #0: unknown event 113 (Unknown command (113))
0.747677: wlan0 (phy #0): vendor event 001374:54
0.009524: wlan0: unknown event 88 (ch_switch_notify)
0.018314: phy #0: vendor event 001374:165
0.000164: phy #0: unknown event 113 (Unknown command (113))
0.000017: phy #0: vendor event 001374:10
2.643889: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
3.957315: phy #0: vendor event 001374:10
0.000167: phy #0: unknown event 113 (Unknown command (113))
0.136823: wlan0 (phy #0): scan started
1.902835: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
0.211292: wlan0 (phy #0): connected to 1c:5f:2b:5e:d5:53
1.136500: wlan0 (phy #0): disconnected (local request)
0.153199: phy #0: unknown event 113 (Unknown command (113))
0.001287: phy #0: vendor event 001374:10
0.824923: wlan0 (phy #0): vendor event 001374:54
0.011762: wlan0: unknown event 88 (ch_switch_notify)
0.017424: phy #0: vendor event 001374:165
2.110435: wlan0 (phy #0): scan started
1.881420: wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
0.199250: wlan0 (phy #0): connected to 1c:5f:2b:5e:d5:53
7.105992: wlan0 (phy #0): disconnected (local request)
3.028057: wlan0 (phy #0): vendor event 001374:54
0.010698: wlan0: unknown event 88 (ch_switch_notify)
0.022681: phy #0: vendor event 001374:165
0.225975: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
0.017137: wlan0 (phy #0): mgmt TX status (cookie 0): no ack
2.991887: wlan0 (phy #0): mgmt TX status (cookie 0): no a



```


####  iw event  -f
```
parker:/ # iw event  -f
wlan0 (phy #0): scan started
wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
wlan0 (phy #0): connected to 1c:5f:2b:5e:d5:53
wlan0 (phy #0): disconnected (local request)
wlan0 (phy #0): scan started
wlan0 (phy #0): scan finished: 2412 2417 2422 2427 2432 2437 2442 2447 2452 2457 2462 2467 2472 5180 5200 5220 5240 5260 5280 5300 5320 5745 5765 5785 5805 5825,
wlan0 (phy #0): connected to 1c:5f:2b:5e:d5:54
phy #0: vendor event 001374:165
vendor event: 30 00 01 00 2c 00 00 00 08 00 01 00 00 00 00 00
vendor event: 08 00 02 00 02 00 00 00 18 00 03 00 14 00 00 00
vendor event: 08 00 01 00 1e 00 00 00 08 00 02 00 71 16 00 00

wlan0 (phy #0): disconnected (local request)
wlan0 (phy #0): vendor event 001374:54
vendor event: 05 00 01 00 06 00 00 00 05 00 02 00 00 00 00 00
vendor event: 05 00 09 00 00 00 00 00 05 00 0a 00 00 00 00 00
vendor event: 06 00 07 00 14 00 00 00 05 00 03 00 01 00 00 00

wlan0: unknown event 88 (ch_switch_notify)
phy #0: vendor event 001374:165
vendor event: 30 00 01 00 2c 00 00 00 08 00 01 00 00 00 00 00
vendor event: 08 00 02 00 01 00 00 00 18 00 03 00 14 00 00 00
vendor event: 08 00 01 00 1e 00 00 00 08 00 02 00 85 09 00 00

wlan0 (phy #0): mgmt TX status (cookie 0): no ack
wlan0 (phy #0): mgmt TX status (cookie 0): no ack


```

### iw phy0 info  【 iw phy  】【 iw list 】【iw phy45 info】
```
130|parker:/ # iw phy0 info 
Wiphy phy0
        max # scan SSIDs: 10
        max scan IEs length: 2048 bytes
        max # sched scan SSIDs: 16
        max # match sets: 16
        Retry short limit: 7
        Retry long limit: 4
        Coverage class: 0 (up to 0m)
        Device supports roaming.
        Device supports T-DLS.
        Supported Ciphers:
                * WEP40 (00-0f-ac:1)
                * WEP104 (00-0f-ac:5)
                * TKIP (00-0f-ac:2)
                * 00-40-96:254
                * 00-40-96:255
                * CCMP (00-0f-ac:4)
                * WPI-SMS4 (00-14-72:1)
                * CMAC (00-0f-ac:6)
                * 00-0f-ac:11
                * 00-0f-ac:12
                * GCMP (00-0f-ac:8)
                * 00-0f-ac:9
        Available Antennas: TX 0 RX 0
        Supported interface modes:
                 * IBSS
                 * managed
                 * AP
                 * monitor
                 * P2P-client
                 * P2P-GO
        Band 1:
                Capabilities: 0x9072
                        HT20/HT40
                        Static SM Power Save
                        RX Greenfield
                        RX HT20 SGI
                        RX HT40 SGI
                        No RX STBC
                        Max AMSDU length: 3839 bytes
                        DSSS/CCK HT40
                        L-SIG TXOP protection
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 16 usec (0x07)
                HT Max RX data rate: 72 Mbps
                HT TX/RX MCS rate indexes supported: 0-7
                Bitrates (non-HT):
                        * 1.0 Mbps
                        * 2.0 Mbps
                        * 5.5 Mbps
                        * 11.0 Mbps
                        * 6.0 Mbps
                        * 9.0 Mbps
                        * 12.0 Mbps
                        * 18.0 Mbps
                        * 24.0 Mbps
                        * 36.0 Mbps
                        * 48.0 Mbps
                        * 54.0 Mbps
                Frequencies:
                        * 2412 MHz [1] (20.0 dBm)
                        * 2417 MHz [2] (20.0 dBm)
                        * 2422 MHz [3] (20.0 dBm)
                        * 2427 MHz [4] (20.0 dBm)
                        * 2432 MHz [5] (20.0 dBm)
                        * 2437 MHz [6] (20.0 dBm)
                        * 2442 MHz [7] (20.0 dBm)
                        * 2447 MHz [8] (20.0 dBm)
                        * 2452 MHz [9] (20.0 dBm)
                        * 2457 MHz [10] (20.0 dBm)
                        * 2462 MHz [11] (20.0 dBm)
                        * 2467 MHz [12] (20.0 dBm)
                        * 2472 MHz [13] (20.0 dBm)
        Band 2:
                Capabilities: 0x9072
                        HT20/HT40
                        Static SM Power Save
                        RX Greenfield
                        RX HT20 SGI
                        RX HT40 SGI
                        No RX STBC
                        Max AMSDU length: 3839 bytes
                        DSSS/CCK HT40
                        L-SIG TXOP protection
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 16 usec (0x07)
                HT Max RX data rate: 72 Mbps
                HT TX/RX MCS rate indexes supported: 0-7
                VHT Capabilities (0x039173b2):
                        Max MPDU length: 11454
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        short GI (80 MHz)
                        TX STBC
                        SU Beamformee
                        MU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-7
                        2 streams: MCS 0-7
                        3 streams: MCS 0-7
                        4 streams: MCS 0-7
                        5 streams: MCS 0-7
                        6 streams: MCS 0-7
                        7 streams: MCS 0-7
                        8 streams: MCS 0-7
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-7
                        2 streams: MCS 0-7
                        3 streams: MCS 0-7
                        4 streams: MCS 0-7
                        5 streams: MCS 0-7
                        6 streams: MCS 0-7
                        7 streams: MCS 0-7
                        8 streams: MCS 0-7
                VHT TX highest supported: 0 Mbps
                Bitrates (non-HT):
                        * 6.0 Mbps
                        * 9.0 Mbps
                        * 12.0 Mbps
                        * 18.0 Mbps
                        * 24.0 Mbps
                        * 36.0 Mbps
                        * 48.0 Mbps
                        * 54.0 Mbps
                Frequencies:
                        * 5180 MHz [36] (23.0 dBm)
                        * 5200 MHz [40] (23.0 dBm)
                        * 5220 MHz [44] (23.0 dBm)
                        * 5240 MHz [48] (23.0 dBm)
                        * 5260 MHz [52] (23.0 dBm) (radar detection)
                          DFS state: usable (for 184 sec)
                          DFS CAC time: 60000 ms
                        * 5280 MHz [56] (23.0 dBm) (radar detection)
                          DFS state: usable (for 184 sec)
                          DFS CAC time: 60000 ms
                        * 5300 MHz [60] (23.0 dBm) (radar detection)
                          DFS state: usable (for 184 sec)
                          DFS CAC time: 60000 ms
                        * 5320 MHz [64] (23.0 dBm) (radar detection)
                          DFS state: usable (for 184 sec)
                          DFS CAC time: 60000 ms
                        * 5500 MHz [100] (disabled)
                        * 5520 MHz [104] (disabled)
                        * 5540 MHz [108] (disabled)
                        * 5560 MHz [112] (disabled)
                        * 5580 MHz [116] (disabled)
                        * 5600 MHz [120] (disabled)
                        * 5620 MHz [124] (disabled)
                        * 5640 MHz [128] (disabled)
                        * 5660 MHz [132] (disabled)
                        * 5680 MHz [136] (disabled)
                        * 5700 MHz [140] (disabled)
                        * 5720 MHz [144] (disabled)
                        * 5745 MHz [149] (33.0 dBm)
                        * 5765 MHz [153] (33.0 dBm)
                        * 5785 MHz [157] (33.0 dBm)
                        * 5805 MHz [161] (33.0 dBm)
                        * 5825 MHz [165] (33.0 dBm)
                        * 5845 MHz [169] (disabled)
                        * 5865 MHz [173] (disabled)
        Supported commands:
                 * new_interface
                 * set_interface
                 * new_key
                 * start_ap
                 * new_station
                 * set_bss
                 * join_ibss
                 * set_pmksa
                 * del_pmksa
                 * flush_pmksa
                 * remain_on_channel
                 * frame
                 * frame_wait_cancel
                 * set_channel
                 * tdls_mgmt
                 * tdls_oper
                 * start_sched_scan
                 * testmode
                 * connect
                 * disconnect
                 * channel_switch
                 * Unknown command (122)
        Supported TX frame types:
                 * IBSS: 0x00 0x10 0x20 0x30 0x40 0x50 0x60 0x70 0x80 0x90 0xa0 0xb0 0xc0 0xd0 0xe0 0xf0
                 * managed: 0x00 0x10 0x20 0x30 0x40 0x50 0x60 0x70 0x80 0x90 0xa0 0xb0 0xc0 0xd0 0xe0 0xf0
                 * AP: 0x00 0x10 0x20 0x30 0x40 0x50 0x60 0x70 0x80 0x90 0xa0 0xb0 0xc0 0xd0 0xe0 0xf0
                 * P2P-client: 0x00 0x10 0x20 0x30 0x40 0x50 0x60 0x70 0x80 0x90 0xa0 0xb0 0xc0 0xd0 0xe0 0xf0
                 * P2P-GO: 0x00 0x10 0x20 0x30 0x40 0x50 0x60 0x70 0x80 0x90 0xa0 0xb0 0xc0 0xd0 0xe0 0xf0
        Supported RX frame types:
                 * IBSS: 0x00 0x20 0x40 0xa0 0xb0 0xc0 0xd0
                 * managed: 0x40 0xb0 0xd0
                 * AP: 0x00 0x20 0x40 0xa0 0xb0 0xc0 0xd0
                 * P2P-client: 0x40 0xd0
                 * P2P-GO: 0x00 0x20 0x40 0xa0 0xb0 0xc0 0xd0
        WoWLAN support:
                 * wake up on anything (device continues operating normally)
                 * wake up on disconnect
                 * wake up on magic packet
                 * wake up on pattern match, up to 4 patterns of 6-64 bytes,
                   maximum packet offset 0 bytes
                 * can do GTK rekeying
                 * wake up on GTK rekey failure
                 * wake up on EAP identity request
                 * wake up on 4-way handshake
                 * wake up on rfkill release
        software interface modes (can always be added):
        valid interface combinations:
                 * #{ managed } <= 3,
                   total <= 3, #channels <= 2
                 * #{ managed } <= 1, #{ IBSS } <= 1,
                   total <= 2, #channels <= 2
                 * #{ AP } <= 3,
                   total <= 3, #channels <= 2
                 * #{ P2P-client } <= 1, #{ P2P-GO } <= 1,
                   total <= 2, #channels <= 2
                 * #{ managed } <= 2, #{ AP } <= 2,
                   total <= 4, #channels <= 2, STA/AP BI must match
                 * #{ managed } <= 2, #{ P2P-client, P2P-GO } <= 2,
                   total <= 4, #channels <= 2, STA/AP BI must match
                 * #{ managed } <= 2, #{ P2P-GO } <= 1, #{ AP } <= 1,
                   total <= 4, #channels <= 2, STA/AP BI must match
                 * #{ managed } <= 1, #{ P2P-client, P2P-GO } <= 1, #{ AP } <= 1,
                   total <= 3, #channels <= 2, STA/AP BI must match
                 * #{ managed } <= 1, #{ P2P-client, P2P-GO } <= 2,
                   total <= 3, #channels <= 2, STA/AP BI must match
                 * #{ monitor } <= 3,
                   total <= 3, #channels <= 2
        Device supports HT-IBSS.
        Device has client inactivity timer.
        Device accepts cell base station regulatory hints.
        Device supports SAE with AUTHENTICATE command
        Device supports scan flush.
        Driver/device bandwidth changes during BSS lifetime (AP/GO mode)
        Device supports VHT-IBSS.


```


## iw commands
```
ocean:/ # iw commands
1 (0x1): get_wiphy
2 (0x2): set_wiphy
3 (0x3): new_wiphy
4 (0x4): del_wiphy
5 (0x5): get_interface
6 (0x6): set_interface
7 (0x7): new_interface
8 (0x8): del_interface
9 (0x9): get_key
10 (0xa): set_key
11 (0xb): new_key
12 (0xc): del_key
13 (0xd): get_beacon
14 (0xe): set_beacon
15 (0xf): start_ap
16 (0x10): stop_ap
17 (0x11): get_station
18 (0x12): set_station
19 (0x13): new_station
20 (0x14): del_station
21 (0x15): get_mpath
22 (0x16): set_mpath
23 (0x17): new_mpath
24 (0x18): del_mpath
25 (0x19): set_bss
26 (0x1a): set_reg
27 (0x1b): req_set_reg
28 (0x1c): get_mesh_config
29 (0x1d): set_mesh_config
30 (0x1e): Unknown command (30)
31 (0x1f): get_reg
32 (0x20): get_scan
33 (0x21): trigger_scan
34 (0x22): new_scan_results
35 (0x23): scan_aborted
36 (0x24): reg_change
37 (0x25): authenticate
38 (0x26): associate
39 (0x27): deauthenticate
40 (0x28): disassociate
41 (0x29): michael_mic_failure
42 (0x2a): reg_beacon_hint
43 (0x2b): join_ibss
44 (0x2c): leave_ibss
45 (0x2d): testmode
46 (0x2e): connect
47 (0x2f): roam
48 (0x30): disconnect
49 (0x31): set_wiphy_netns
50 (0x32): get_survey
51 (0x33): new_survey_results
52 (0x34): set_pmksa
53 (0x35): del_pmksa
54 (0x36): flush_pmksa
55 (0x37): remain_on_channel
56 (0x38): cancel_remain_on_channel
57 (0x39): set_tx_bitrate_mask
58 (0x3a): register_frame
59 (0x3b): frame
60 (0x3c): frame_tx_status
61 (0x3d): set_power_save
62 (0x3e): get_power_save
63 (0x3f): set_cqm
64 (0x40): notify_cqm
65 (0x41): set_channel
66 (0x42): set_wds_peer
67 (0x43): frame_wait_cancel
68 (0x44): join_mesh
69 (0x45): leave_mesh
70 (0x46): unprot_deauthenticate
71 (0x47): unprot_disassociate
72 (0x48): new_peer_candidate
73 (0x49): get_wowlan
74 (0x4a): set_wowlan
75 (0x4b): start_sched_scan
76 (0x4c): stop_sched_scan
77 (0x4d): sched_scan_results
78 (0x4e): sched_scan_stopped
79 (0x4f): set_rekey_offload
80 (0x50): pmksa_candidate
81 (0x51): tdls_oper
82 (0x52): tdls_mgmt
83 (0x53): unexpected_frame
84 (0x54): probe_client
85 (0x55): register_beacons
86 (0x56): unexpected_4addr_frame
87 (0x57): set_noack_map
88 (0x58): ch_switch_notify
89 (0x59): start_p2p_device
90 (0x5a): stop_p2p_device
91 (0x5b): conn_failed
92 (0x5c): set_mcast_rate
93 (0x5d): set_mac_acl
94 (0x5e): radar_detect
95 (0x5f): get_protocol_features
96 (0x60): update_ft_ies
97 (0x61): ft_event
98 (0x62): crit_protocol_start
99 (0x63): crit_protocol_stop
100 (0x64): get_coalesce
101 (0x65): set_coalesce
102 (0x66): channel_switch
103 (0x67): vendor
104 (0x68): set_qos_map
105 (0x69): add_tx_ts
106 (0x6a): del_tx_ts
107 (0x6b): get_mpp
108 (0x6c): join_ocb
109 (0x6d): leave_ocb
110 (0x6e): ch_switch_started_notify
111 (0x6f): Unknown command (111)
112 (0x70): Unknown command (112)

```



## iw features
```
ocean:/ # iw features
nl80211 features: 0x1
        * split wiphy dump

```


## iw phy xxx
```

phy <phyname> interface add <name> type <type> [mesh_id <meshid>] [4addr on|off] [flags <flag>*] [addr <mac-addr>]
		Add a new virtual interface with the given configuration.
		Valid interface types are: managed, ibss, monitor, mesh, wds.

		The flags are only used for monitor interfaces, valid flags are:
		none:     no special flags
		fcsfail:  show frames with FCS errors
		control:  show control frames
		otherbss: show frames from other BSSes
		cook:     use cooked mode
		active:   use active mode (ACK incoming unicast packets)

		The mesh_id is used only for mesh mode.
				
				
iw dev phy0 set freq <freq> [HT20|HT40+|HT40-]
iw dev phy45 set freq <freq> [HT20|HT40+|HT40-]
```


## iw wlan0 info
```
ocean:/ # iw wlan0 info
Interface wlan0
        ifindex 137
        wdev 0x2d00000001
        addr cc:2b:85:4f:af:a5
        ssid shanghai
        type managed
        wiphy 45

```




## iw dev
```
ocean:/ # iw dev
phy#45
        Interface p2p0
                ifindex 138
                wdev 0x2d00000002
                addr 0e:cb:85:4f:af:a5
                type managed
        Interface wlan0
                ifindex 137
                wdev 0x2d00000001
                addr cc:2b:85:4f:af:a5
                ssid shanghai
                type managed

```

## iw  dev wlan0 station dump
## iw  dev wlan0 survey dump
```
dev <devname> survey dump
dev <devname> station dump

```

## iw dev wlan0 scan dump -u
```
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 8e 15 00 00
BSS 18:64:72:11:3d:92(on wlan0)
        TSF: 5372080702365 usec (62d, 04:14:40)
        freq: 5280
        beacon interval: 100 TUs
        capability: ESS SpectrumMgmt (0x0101)
        signal: -80.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: RD-Test
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 56
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 56
                 * secondary channel offset: below
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 8e 15 00 00
BSS 18:64:72:11:3d:91(on wlan0)
        TSF: 5372080702379 usec (62d, 04:14:40)
        freq: 5280
        beacon interval: 100 TUs
        capability: ESS SpectrumMgmt (0x0101)
        signal: -80.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: lenovo-internet
        Supported rates: 18.0 24.0* 36.0* 48.0 54.0
        DS Parameter set: channel 56
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 56
                 * secondary channel offset: below
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 8e 15 00 00
BSS 18:64:72:11:3d:90(on wlan0)
        TSF: 5372080702393 usec (62d, 04:14:40)
        freq: 5280
        beacon interval: 100 TUs
        capability: ESS Privacy SpectrumMgmt (0x0111)
        signal: -80.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: lenovo
        Supported rates: 18.0 24.0* 36.0* 48.0 54.0
        DS Parameter set: channel 56
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        RSN:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: IEEE 802.1X
                 * Capabilities: 4-PTKSA-RC 4-GTKSA-RC (0x0028)
        BSS Load:
                 * station count: 0
                 * channel utilisation: 13/255
                 * available admission capacity: 29687 [*32us]
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 56
                 * secondary channel offset: below
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 8e 15 00 00
BSS 88:25:93:c5:0e:aa(on wlan0)
        TSF: 5372080702477 usec (62d, 04:14:40)
        freq: 2422
        beacon interval: 100 TUs
        capability: ESS Privacy ShortPreamble ShortSlotTime (0x0431)
        signal: -82.00 dBm
        last seen: 8574 ms ago
        SSID: zjgk_guest
        Supported rates: 1.0* 2.0* 5.5* 11.0* 6.0 9.0 12.0 18.0
        DS Parameter set: channel 3
        ERP: <no flags>
        RSN:     * Version: 1
                 * Group cipher: TKIP
                 * Pairwise ciphers: CCMP TKIP
                 * Authentication suites: PSK
                 * Capabilities: 1-PTKSA-RC 1-GTKSA-RC (0x0000)
        Extended supported rates: 24.0 36.0 48.0 54.0
        HT capabilities:
                Capabilities: 0x1ac
                        HT20
                        SM Power Save disabled
                        RX HT20 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 3839 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 8 usec (0x06)
                HT TX/RX MCS rate indexes supported: 0-23
        HT operation:
                 * primary channel: 3
                 * secondary channel offset: no secondary
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: 20 MHz
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Overlapping BSS scan params:
                 * passive dwell: 20 TUs
                 * active dwell: 10 TUs
                 * channel width trigger scan interval: 300 s
                 * scan passive total per channel: 200 TUs
                 * scan active total per channel: 20 TUs
                 * BSS width channel transition delay factor: 5
                 * OBSS Scan Activity Threshold: 0.25 %
        Extended capabilities: HT Information Exchange Supported, 6
        WPA:     * Version: 1
                 * Group cipher: TKIP
                 * Pairwise ciphers: CCMP TKIP
                 * Authentication suites: PSK
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:03:7f, data: 01 01 00 00 ff 7f
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 ae 16 00 00
BSS dc:99:14:7c:ab:20(on wlan0)
        TSF: 5372080702493 usec (62d, 04:14:40)
        freq: 2437
        beacon interval: 100 TUs
        capability: ESS Privacy ShortPreamble ShortSlotTime RadioMeasure (0x1431)
        signal: -83.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: higon
        Supported rates: 1.0* 2.0* 5.5 11.0 6.0 9.0 12.0 18.0
        DS Parameter set: channel 6
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x1 Bitmap[0] 0x0 (+ 3 octets)
        Country: CN     Environment: Indoor/Outdoor
                Channels [1 - 13] @ 20 dBm
        ERP: <no flags>
        Extended supported rates: 24.0 36.0 48.0 54.0
        Unknown IE (70): 71 10 00 00 0c
        HT capabilities:
                Capabilities: 0x98c
                        HT20
                        SM Power Save disabled
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: No restriction (0x00)
                HT TX/RX MCS rate indexes supported: 0-31
        RSN:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: PSK
                 * Capabilities: 1-PTKSA-RC 1-GTKSA-RC (0x0000)
        HT operation:
                 * primary channel: 6
                 * secondary channel offset: no secondary
                 * STA channel width: 20 MHz
                 * RIFS: 0
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, TFS, WNM-Sleep Mode, TIM Broadcast, BSS Transition, 2, 6
        VHT capabilities:
                VHT Capabilities (0x30000192):
                        Max MPDU length: 11454
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        RX antenna pattern consistency
                        TX antenna pattern consistency
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: MCS 0-9
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: MCS 0-9
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0xfffc
        WPA:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: PSK
                 * Capabilities: 1-PTKSA-RC 1-GTKSA-RC (0x0000)
        WMM:     * Parameter version 1
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:03:7f, data: 01 01 00 00 ff 7f
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 78 16 00 00
BSS 18:64:72:12:0d:94(on wlan0)
        TSF: 5372080702522 usec (62d, 04:14:40)
        freq: 5280
        beacon interval: 100 TUs
        capability: ESS Privacy SpectrumMgmt (0x0111)
        signal: -93.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: 5G-Test
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 56
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        RSN:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: IEEE 802.1X
                 * Capabilities: 4-PTKSA-RC 4-GTKSA-RC (0x0028)
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 56
                 * secondary channel offset: below
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 88 15 00 00
BSS 18:64:72:12:0d:92(on wlan0)
        TSF: 5372080702536 usec (62d, 04:14:40)
        freq: 5280
        beacon interval: 100 TUs
        capability: ESS SpectrumMgmt (0x0101)
        signal: -92.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: RD-Test
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 56
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 56
                 * secondary channel offset: below
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 88 15 00 00
BSS 18:64:72:21:9d:d4(on wlan0)
        TSF: 5372080702550 usec (62d, 04:14:40)
        freq: 5300
        beacon interval: 100 TUs
        capability: ESS Privacy SpectrumMgmt (0x0111)
        signal: -91.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: 5G-Test
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 60
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        RSN:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: IEEE 802.1X
                 * Capabilities: 4-PTKSA-RC 4-GTKSA-RC (0x0028)
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 60
                 * secondary channel offset: above
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 6e 15 00 00
BSS 18:64:72:21:9d:d2(on wlan0)
        TSF: 5372080702565 usec (62d, 04:14:40)
        freq: 5300
        beacon interval: 100 TUs
        capability: ESS SpectrumMgmt (0x0101)
        signal: -91.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: RD-Test
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 60
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        Country: CN     Environment: Indoor/Outdoor
                Channels [36 - 64] @ 23 dBm
                Channels [149 - 165] @ 33 dBm
        Power constraint: 0 dB
        TPC report: TX power: 15 dBm
        HT capabilities:
                Capabilities: 0x9ef
                        RX LDPC
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 60
                 * secondary channel offset: above
                 * STA channel width: any
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Extended capabilities: Extended Channel Switching, BSS Transition, WNM-Notification, 6
        VHT capabilities:
                VHT Capabilities (0x0f825991):
                        Max MPDU length: 7991
                        Supported Channel Width: neither 160 nor 80+80
                        RX LDPC
                        TX STBC
                        SU Beamformer
                        SU Beamformee
                VHT RX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT RX highest supported: 0 Mbps
                VHT TX MCS set:
                        1 streams: MCS 0-9
                        2 streams: MCS 0-9
                        3 streams: MCS 0-9
                        4 streams: not supported
                        5 streams: not supported
                        6 streams: not supported
                        7 streams: not supported
                        8 streams: not supported
                VHT TX highest supported: 0 Mbps
        VHT operation:
                 * channel width: 0 (20 or 40 MHz)
                 * center freq segment 1: 0
                 * center freq segment 2: 0
                 * VHT basic MCS set: 0x0000
        Unknown IE (195): 01 2e 2e
        Vendor specific: OUI 00:0b:86, data: 01 04 08 0f
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 6e 15 00 00
BSS 18:64:72:21:6a:02(on wlan0)
        TSF: 5372080702421 usec (62d, 04:14:40)
        freq: 2462
        beacon interval: 100 TUs
        capability: ESS ShortPreamble ShortSlotTime (0x0421)
        signal: -77.00 dBm
        last seen: 8574 ms ago
        SSID: lenovo-guest
        Supported rates: 18.0 24.0* 36.0* 48.0 54.0
        DS Parameter set: channel 11
        ERP: <no flags>
        HT capabilities:
                Capabilities: 0x19ad
                        RX LDPC
                        HT20
                        SM Power Save disabled
                        RX HT20 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 11
                 * secondary channel offset: no secondary
                 * STA channel width: 20 MHz
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Overlapping BSS scan params:
                 * passive dwell: 20 TUs
                 * active dwell: 10 TUs
                 * channel width trigger scan interval: 300 s
                 * scan passive total per channel: 200 TUs
                 * scan active total per channel: 20 TUs
                 * BSS width channel transition delay factor: 5
                 * OBSS Scan Activity Threshold: 0.25 %
        Extended capabilities: HT Information Exchange Supported, Extended Channel Switching, BSS Transition, WNM-Notification, 6
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 05 0c 00 00
BSS 18:64:72:21:6a:00(on wlan0)
        TSF: 5372080702450 usec (62d, 04:14:40)
        freq: 2462
        beacon interval: 100 TUs
        capability: ESS Privacy ShortPreamble ShortSlotTime (0x0431)
        signal: -77.00 dBm
        last seen: 8574 ms ago
        SSID: lenovo
        Supported rates: 18.0 24.0* 36.0* 48.0 54.0
        DS Parameter set: channel 11
        ERP: <no flags>
        RSN:     * Version: 1
                 * Group cipher: CCMP
                 * Pairwise ciphers: CCMP
                 * Authentication suites: IEEE 802.1X
                 * Capabilities: 4-PTKSA-RC 4-GTKSA-RC (0x0028)
        BSS Load:
                 * station count: 1
                 * channel utilisation: 79/255
                 * available admission capacity: 25625 [*32us]
        HT capabilities:
                Capabilities: 0x19ad
                        RX LDPC
                        HT20
                        SM Power Save disabled
                        RX HT20 SGI
                        TX STBC
                        RX STBC 1-stream
                        Max AMSDU length: 7935 bytes
                        DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 4 usec (0x05)
                HT RX MCS rate indexes supported: 3-7, 11-15, 19-23
                HT TX MCS rate indexes are undefined
        HT operation:
                 * primary channel: 11
                 * secondary channel offset: no secondary
                 * STA channel width: 20 MHz
                 * RIFS: 1
                 * HT protection: no
                 * non-GF present: 1
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        Overlapping BSS scan params:
                 * passive dwell: 20 TUs
                 * active dwell: 10 TUs
                 * channel width trigger scan interval: 300 s
                 * scan passive total per channel: 200 TUs
                 * scan active total per channel: 20 TUs
                 * BSS width channel transition delay factor: 5
                 * OBSS Scan Activity Threshold: 0.25 %
        Extended capabilities: HT Information Exchange Supported, Extended Channel Switching, BSS Transition, WNM-Notification, 6
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 05 0c 00 00
BSS 00:25:92:19:ce:1b(on wlan0)
        TSF: 5372080702507 usec (62d, 04:14:40)
        freq: 5180
        beacon interval: 100 TUs
        capability: ESS Privacy (0x0011)
        signal: -86.00 dBm
        last seen: 8574 ms ago
        Information elements from Probe Response frame:
        SSID: MAXHUB-H6F
        Supported rates: 6.0* 9.0 12.0* 18.0 24.0* 36.0 48.0 54.0
        DS Parameter set: channel 36
        TIM: DTIM Count 0 DTIM Period 1 Bitmap Control 0x0 Bitmap[0] 0x0
        WPA:     * Version: 1
                 * Group cipher: TKIP
                 * Pairwise ciphers: CCMP TKIP
                 * Authentication suites: PSK
        HT capabilities:
                Capabilities: 0x6e
                        HT20/HT40
                        SM Power Save disabled
                        RX HT20 SGI
                        RX HT40 SGI
                        No RX STBC
                        Max AMSDU length: 3839 bytes
                        No DSSS/CCK HT40
                Maximum RX AMPDU length 65535 bytes (exponent: 0x003)
                Minimum RX AMPDU time spacing: 16 usec (0x07)
                HT Max RX data rate: 952 Mbps
                HT TX/RX MCS rate indexes supported: 0-15
        HT operation:
                 * primary channel: 36
                 * secondary channel offset: above
                 * STA channel width: any
                 * RIFS: 0
                 * HT protection: no
                 * non-GF present: 0
                 * OBSS non-GF present: 0
                 * dual beacon: 0
                 * dual CTS protection: 0
                 * STBC beacon: 0
                 * L-SIG TXOP Prot: 0
                 * PCO active: 0
                 * PCO phase: 0
        WMM:     * Parameter version 1
                 * u-APSD
                 * BE: CW 15-1023, AIFSN 3
                 * BK: CW 15-1023, AIFSN 7
                 * VI: CW 7-15, AIFSN 2, TXOP 3008 usec
                 * VO: CW 3-7, AIFSN 2, TXOP 1504 usec
        Vendor specific: OUI 00:a0:c6, data: 00 01 00 00 95 00 00 00
```



## iw reg get
```

ocean:/ # iw reg get
global
country CN: DFS-FCC
        (2402 - 2482 @ 40), (N/A, 20), (N/A)
        (5170 - 5250 @ 80), (N/A, 23), (N/A), AUTO-BW
        (5250 - 5330 @ 80), (N/A, 23), (0 ms), DFS, AUTO-BW
        (5735 - 5835 @ 80), (N/A, 33), (N/A)
        (59400 - 63720 @ 2160), (N/A, 44), (N/A)
		

```

## iw reg set BR
```

ocean:/ # iw reg set BR
ocean:/ # iw reg get
global
country BR: DFS-FCC
        (2402 - 2482 @ 40), (N/A, 30), (N/A)
        (5170 - 5250 @ 80), (N/A, 24), (N/A), AUTO-BW
        (5250 - 5330 @ 80), (N/A, 24), (0 ms), DFS, AUTO-BW
        (5490 - 5730 @ 160), (N/A, 24), (0 ms), DFS
        (5735 - 5835 @ 80), (N/A, 30), (N/A)
        (57240 - 63720 @ 2160), (N/A, 40), (N/A)
		

```



## iw phy0 reg get  【 iw phy45 reg get  】
```
global
country BR: DFS-FCC
        (2402 - 2482 @ 40), (N/A, 30), (N/A)
        (5170 - 5250 @ 80), (N/A, 24), (N/A), AUTO-BW
        (5250 - 5330 @ 80), (N/A, 24), (0 ms), DFS, AUTO-BW
        (5490 - 5730 @ 160), (N/A, 24), (0 ms), DFS
        (5735 - 5835 @ 80), (N/A, 30), (N/A)
        (57240 - 63720 @ 2160), (N/A, 40), (N/A)


```


## iw wlan0 disconnect
```
断开当前连接

```


 
 
## iw wlan0 link
```
ocean:/ # iw wlan0 link
Connected to 1c:5f:2b:5e:d5:53 (on wlan0)
        SSID: D-Link_DIR-816
        freq: 2457
        signal: -31 dBm
        tx bitrate: 72.2 MBit/s MCS 7 short GI
		
		
```




## iw wlan0 offchannel 5660 30
```
 dev <devname> offchannel <freq> <duration>
 
  iw wlan0 offchannel 2457 30
  
  iw wlan0 offchannel 5745 30

```



## iw phy0 wowlan show  【 iw phy45 wowlan show 】
```
phy <phyname> wowlan show

ocean:/ #  iw phy45 wowlan show
WoWLAN is enabled:
 * wake up on magic packet

```

## iw wlan0 set power_save on【 iw wlan0 set power_save off 】
```

iw wlan0 set power_save on
iw wlan0 set power_save off


```



## iw
```


```


## iw help
```
parker:/ # iw help
Usage:  iw [options] command
Options:
        --debug         enable netlink debugging
        --version       show version (4.1)
Commands:
        help [command]
                Print usage for all or a specific command, e.g.
                "help wowlan" or "help wowlan enable".

        event [-t] [-r] [-f]
                Monitor events from the kernel.
                -t - print timestamp
                -r - print relative timstamp
                -f - print full frame for auth/assoc etc.

        phy <phyname> info
                Show capabilities for the specified wireless device.

        list
                List all wireless devices and their capabilities.

        phy
        commands
                list all known commands and their decimal & hex value

        features


        phy <phyname> interface add <name> type <type> [mesh_id <meshid>] [4addr on|off] [flags <flag>*] [addr <mac-addr>]
                Add a new virtual interface with the given configuration.
                Valid interface types are: managed, ibss, monitor, mesh, wds.

                The flags are only used for monitor interfaces, valid flags are:
                none:     no special flags
                fcsfail:  show frames with FCS errors
                control:  show control frames
                otherbss: show frames from other BSSes
                cook:     use cooked mode
                active:   use active mode (ACK incoming unicast packets)

                The mesh_id is used only for mesh mode.

        dev <devname> interface add <name> type <type> [mesh_id <meshid>] [4addr on|off] [flags <flag>*] [addr <mac-addr>]
        dev <devname> del
                Remove this virtual interface

        dev <devname> info
                Show information for this interface.

        dev
                List all network interfaces for wireless hardware.

        dev <devname> ibss leave
                Leave the current IBSS cell.

        dev <devname> ibss join <SSID> <freq in MHz> [HT20|HT40+|HT40-|NOHT|5MHZ|10MHZ] [fixed-freq] [<fixed bssid>] [beacon-interval <TU>] [basic-rates <rate in Mbps,rate2,...>] [mcast-rate <rate in Mbps>] [key d:0:abcde]
                Join the IBSS cell with the given SSID, if it doesn't exist create
                it on the given frequency. When fixed frequency is requested, don't
                join/create a cell on a different frequency. When a fixed BSSID is
                requested use that BSSID and do not adopt another cell's BSSID even
                if it has higher TSF and the same SSID. If an IBSS is created, create
                it with the specified basic-rates, multicast-rate and beacon-interval.

        dev <devname> station get <MAC address>
                Get information for a specific station.

        dev <devname> station del <MAC address>
                Remove the given station entry (use with caution!)

        dev <devname> station set <MAC address> plink_action <open|block>
                Set mesh peer link action for this station (peer).

        dev <devname> station set <MAC address> vlan <ifindex>
                Set an AP VLAN for this station.

        dev <devname> station set <MAC address> mesh_power_mode <active|light|deep>
                Set link-specific mesh power mode for this station

        dev <devname> station dump
                List all stations known, e.g. the AP on managed interfaces

        dev <devname> survey dump
                List all gathered channel survey data

        dev <devname> ocb join <freq in MHz> <5MHZ|10MHZ>
                Join the OCB mode network.

        dev <devname> ocb leave
                Leave the OCB mode network.

        dev <devname> mesh join <mesh ID> [[freq <freq in MHz> <HT20|HT40+|HT40-|NOHT>] [basic-rates <rate in Mbps,rate2,...>]], [mcast-rate <rate in Mbps>] [beacon-interval <time in TUs>] [dtim-period <value>] [vendor_sync on|off] [<param>=<value>]*
                Join a mesh with the given mesh ID with frequency, basic-rates,
                mcast-rate and mesh parameters. Basic-rates are applied only if
                frequency is provided.

        dev <devname> mesh leave
                Leave a mesh.

        dev <devname> mpath get <MAC address>
                Get information on mesh path to the given node.

        dev <devname> mpath del <MAC address>
                Remove the mesh path to the given node.

        dev <devname> mpath new <destination MAC address> next_hop <next hop MAC address>
                Create a new mesh path (instead of relying on automatic discovery).

        dev <devname> mpath set <destination MAC address> next_hop <next hop MAC address>
                Set an existing mesh path's next hop.

        dev <devname> mpath dump
                List known mesh paths.

        dev <devname> mpp get <MAC address>
                Get information on mesh proxy path to the given node.

        dev <devname> mpp dump
                List known mesh proxy paths.

        dev <devname> scan [-u] [freq <freq>*] [ies <hex as 00:11:..>] [meshid <meshid>] [lowpri,flush,ap-force] [randomise[=<addr>/<mask>]] [ssid <ssid>*|passive]
                Scan on the given frequencies and probe for the given SSIDs
                (or wildcard if not given) unless passive scanning is requested.
                If -u is specified print unknown data in the scan results.
                Specified (vendor) IEs must be well-formed.

        dev <devname> scan dump [-u]
                Dump the current scan results. If -u is specified, print unknown
                data in scan results.

        dev <devname> scan trigger [freq <freq>*] [ies <hex as 00:11:..>] [meshid <meshid>] [lowpri,flush,ap-force] [randomise[=<addr>/<mask>]] [ssid <ssid>*|passive]
                Trigger a scan on the given frequencies with probing for the given
                SSIDs (or wildcard if not given) unless passive scanning is requested.

        dev <devname> scan sched_start interval <in_msecs> [delay <in_secs>] [freqs <freq>+] [matches [ssid <ssid>]+]] [active [ssid <ssid>]+|passive] [randomise[=<addr>/<mask>]]
                Start a scheduled scan at the specified interval on the given frequencies
                with probing for the given SSIDs (or wildcard if not given) unless passive
                scanning is requested.  If matches are specified, only matching results
                will be returned.

        dev <devname> scan sched_stop
                Stop an ongoing scheduled scan.

        reg set <ISO/IEC 3166-1 alpha2>
                Notify the kernel about the current regulatory domain.

        reg get
                Print out the kernel's current regulatory domain information.

        phy <phyname> reg get
                Print out the devices' current regulatory domain information.

        dev <devname> disconnect
                Disconnect from the current network.

        dev <devname> connect [-w] <SSID> [<freq in MHz>] [<bssid>] [key 0:abcde d:1:6162636465]
                Join the network with the given SSID (and frequency, BSSID).
                With -w, wait for the connect to finish or fail.

        dev <devname> auth <SSID> <bssid> <type:open|shared> <freq in MHz> [key 0:abcde d:1:6162636465]
                Authenticate with the given network.


        dev <devname> link
                Print information about the current link, if any.

        dev <devname> offchannel <freq> <duration>
                Leave operating channel and go to the given channel for a while.

        dev <devname> cqm rssi <threshold|off> [<hysteresis>]
                Set connection quality monitor RSSI threshold.


        phy <phyname> wowlan enable [any] [disconnect] [magic-packet] [gtk-rekey-failure] [eap-identity-request] [4way-handshake] [rfkill-release] [net-detect interval <in_msecs> [delay <in_secs>] [freqs <freq>+] [matches [ssid <ssid>]+]] [active [ssid <ssid>]+|passive] [randomise[=<addr>/<mask>]]] [tcp <config-file>] [patterns [offset1+]<pattern1> ...]
                Enable WoWLAN with the given triggers.
                Each pattern is given as a bytestring with '-' in places where any byte
                may be present, e.g. 00:11:22:-:44 will match 00:11:22:33:44 and
                00:11:22:33:ff:44 etc.
                Offset and pattern should be separated by '+', e.g. 18+43:34:00:12 will match '43:34:00:12' after 18 bytes of offset in Rx packet.

                The TCP configuration file contains:
                  source=ip[:port]
                  dest=ip:port@mac
                  data=<hex data packet>
                  data.interval=seconds
                  [wake=<hex packet with masked out bytes indicated by '-'>]
                  [data.seq=len,offset[,start]]
                  [data.tok=len,offset,<token stream>]

                Net-detect configuration example:
                 iw phy0 wowlan enable net-detect interval 5000 delay 30 freqs 2412 2422 matches ssid foo ssid bar

        phy <phyname> wowlan disable
                Disable WoWLAN.

        phy <phyname> wowlan show
                Show WoWLAN status.

        phy <phyname> coalesce enable <config-file>
                Enable coalesce with given configuration.
                The configuration file contains coalesce rules:
                  delay=<delay>
                  condition=<condition>
                  patterns=<[offset1+]<pattern1>,<[offset2+]<pattern2>,...>
                  delay=<delay>
                  condition=<condition>
                  patterns=<[offset1+]<pattern1>,<[offset2+]<pattern2>,...>
                  ...
                delay: maximum coalescing delay in msec.
                condition: 1/0 i.e. 'not match'/'match' the patterns
                patterns: each pattern is given as a bytestring with '-' in
                places where any byte may be present, e.g. 00:11:22:-:44 will
                match 00:11:22:33:44 and 00:11:22:33:ff:44 etc. Offset and
                pattern should be separated by '+', e.g. 18+43:34:00:12 will
                match '43:34:00:12' after 18 bytes of offset in Rx packet.


        phy <phyname> coalesce disable
                Disable coalesce.

        phy <phyname> coalesce show
                Show coalesce status.

        dev <devname> roc start <freq> <time in ms>


        wdev <idx> p2p start


        wdev <idx> p2p stop


        dev <devname> vendor send <oui> <subcmd> <filename|-|hex data>


        dev <devname> get mesh_param [<param>]
                Retrieve mesh parameter (run command without any to see available ones).

        dev <devname> get power_save <param>
                Retrieve power save state.

        phy <phyname> set name <new name>
                Rename this wireless device.

        phy <phyname> set freq <freq> [HT20|HT40+|HT40-]
                Set frequency/channel the hardware is using, including HT
                configuration.

        dev <devname> set freq <freq> [HT20|HT40+|HT40-]
        dev <devname> set freq <control freq> [20|40|80|80+80|160] [<center freq 1>] [<center freq 2>]
        phy <phyname> set channel <channel> [HT20|HT40+|HT40-]
        dev <devname> set channel <channel> [HT20|HT40+|HT40-]
        phy <phyname> set frag <fragmentation threshold|off>
                Set fragmentation threshold.

        phy <phyname> set rts <rts threshold|off>
                Set rts threshold.

        phy <phyname> set retry [short <limit>] [long <limit>]
                Set retry limit.

        phy <phyname> set netns { <pid> | name <nsname> }
                Put this wireless device into a different network namespace:
                    <pid>    - change network namespace by process id
                    <nsname> - change network namespace by name from /var/run/netns
                               or by absolute path (man ip-netns)


        phy <phyname> set coverage <coverage class>
                Set coverage class (1 for every 3 usec of air propagation time).
                Valid values: 0 - 255.

        phy <phyname> set distance <auto|distance>
                Enable ACK timeout estimation algorithm (dynack) or set appropriate
                coverage class for given link distance in meters.
                To disable dynack set valid value for coverage class.
                Valid values: 0 - 114750

        phy <phyname> set txpower <auto|fixed|limit> [<tx power in mBm>]
                Specify transmit power level and setting type.

        dev <devname> set txpower <auto|fixed|limit> [<tx power in mBm>]
                Specify transmit power level and setting type.

        phy <phyname> set antenna <bitmap> | all | <tx bitmap> <rx bitmap>
                Set a bitmap of allowed antennas to use for TX and RX.
                The driver may reject antenna configurations it cannot support.

        dev <devname> set monitor <flag>*
                Set monitor flags. Valid flags are:
                none:     no special flags
                fcsfail:  show frames with FCS errors
                control:  show control frames
                otherbss: show frames from other BSSes
                cook:     use cooked mode
                active:   use active mode (ACK incoming unicast packets)

        dev <devname> set meshid <meshid>
        dev <devname> set type <type>
                Set interface type/mode.
                Valid interface types are: managed, ibss, monitor, mesh, wds.

        dev <devname> set 4addr <on|off>
                Set interface 4addr (WDS) mode.

        dev <devname> set noack_map <map>
                Set the NoAck map for the TIDs. (0x0009 = BE, 0x0006 = BK, 0x0030 = VI, 0x00C0 = VO)

        dev <devname> set peer <MAC address>
                Set interface WDS peer.

        dev <devname> set mcast_rate <rate in Mbps>
                Set the multicast bitrate.

        dev <devname> set mesh_param <param>=<value> [<param>=<value>]*
                Set mesh parameter (run command without any to see available ones).

        dev <devname> set power_save <on|off>
                Set power save state to on or off.

        dev <devname> set bitrates [legacy-<2.4|5> <legacy rate in Mbps>*] [ht-mcs-<2.4|5> <MCS index>*] [vht-mcs-<2.4|5> <NSS:MCSx,MCSy... | NSS:MCSx-MCSy>*] [sgi-2.4|lgi-2.4] [sgi-5|lgi-5]
                Sets up the specified rate masks.
                Not passing any arguments would clear the existing mask (if any).


Commands that use the netdev ('dev') can also be given the 'wdev' instead to identify the device.

You can omit the 'phy' or 'dev' if the identification is unique, e.g. "iw wlan0 info" or "iw phy0 info". (Don't when scripting.)

Do NOT screenscrape this tool, we don't consider its output stable.



```

## iw 使用说明
```
Usage:  iw [options] command
Options:
        --debug         enable netlink debugging
        --version       show version (4.1)
Commands:
        help [command]
        event [-t] [-r] [-f]
        phy <phyname> info
        list
        phy
        commands
        features
        phy <phyname> interface add <name> type <type> [mesh_id <meshid>] [4addr on|off] [flags <flag>*] [addr <mac-addr>]
        dev <devname> interface add <name> type <type> [mesh_id <meshid>] [4addr on|off] [flags <flag>*] [addr <mac-addr>]
        dev <devname> del
        dev <devname> info
        dev
        dev <devname> ibss leave
        dev <devname> ibss join <SSID> <freq in MHz> [HT20|HT40+|HT40-|NOHT|5MHZ|10MHZ] [fixed-freq] [<fixed bssid>] [beacon-interval <TU>] [basic-rates <rate in Mbps,rate2,...>] [mcast-rate <rate in Mbps>] [key d:0:abcde]
        dev <devname> station get <MAC address>
        dev <devname> station del <MAC address>
        dev <devname> station set <MAC address> plink_action <open|block>
        dev <devname> station set <MAC address> vlan <ifindex>
        dev <devname> station set <MAC address> mesh_power_mode <active|light|deep>
        dev <devname> station dump
        dev <devname> survey dump
        dev <devname> ocb join <freq in MHz> <5MHZ|10MHZ>
        dev <devname> ocb leave
        dev <devname> mesh join <mesh ID> [[freq <freq in MHz> <HT20|HT40+|HT40-|NOHT>] [basic-rates <rate in Mbps,rate2,...>]], [mcast-rate <rate in Mbps>] [beacon-interval <time in TUs>] [dtim-period <value>] [vendor_sync on|off] [<param>=<value>]*
        dev <devname> mesh leave
        dev <devname> mpath get <MAC address>
        dev <devname> mpath del <MAC address>
        dev <devname> mpath new <destination MAC address> next_hop <next hop MAC address>
        dev <devname> mpath set <destination MAC address> next_hop <next hop MAC address>
        dev <devname> mpath dump
        dev <devname> mpp get <MAC address>
        dev <devname> mpp dump
        dev <devname> scan [-u] [freq <freq>*] [ies <hex as 00:11:..>] [meshid <meshid>] [lowpri,flush,ap-force] [randomise[=<addr>/<mask>]] [ssid <ssid>*|passive]
        dev <devname> scan dump [-u]
        dev <devname> scan trigger [freq <freq>*] [ies <hex as 00:11:..>] [meshid <meshid>] [lowpri,flush,ap-force] [randomise[=<addr>/<mask>]] [ssid <ssid>*|passive]
        dev <devname> scan sched_start interval <in_msecs> [delay <in_secs>] [freqs <freq>+] [matches [ssid <ssid>]+]] [active [ssid <ssid>]+|passive] [randomise[=<addr>/<mask>]]
        dev <devname> scan sched_stop
        reg set <ISO/IEC 3166-1 alpha2>
        reg get
        phy <phyname> reg get
        dev <devname> disconnect
        dev <devname> connect [-w] <SSID> [<freq in MHz>] [<bssid>] [key 0:abcde d:1:6162636465]
        dev <devname> auth <SSID> <bssid> <type:open|shared> <freq in MHz> [key 0:abcde d:1:6162636465]
        dev <devname> link
        dev <devname> offchannel <freq> <duration>
        dev <devname> cqm rssi <threshold|off> [<hysteresis>]
        phy <phyname> wowlan enable [any] [disconnect] [magic-packet] [gtk-rekey-failure] [eap-identity-request] [4way-handshake] [rfkill-release] [net-detect interval <in_msecs> [delay <in_secs>] [freqs <freq>+] [matches [ssid <ssid>]+]] [active [ssid <ssid>]+|passive] [randomise[=<addr>/<mask>]]] [tcp <config-file>] [patterns [offset1+]<pattern1> ...]
        phy <phyname> wowlan disable
        phy <phyname> wowlan show
        phy <phyname> coalesce enable <config-file>
        phy <phyname> coalesce disable
        phy <phyname> coalesce show
        dev <devname> roc start <freq> <time in ms>
        wdev <idx> p2p start
        wdev <idx> p2p stop
        dev <devname> vendor send <oui> <subcmd> <filename|-|hex data>
        dev <devname> get mesh_param [<param>]
        dev <devname> get power_save <param>
        phy <phyname> set name <new name>
        phy <phyname> set freq <freq> [HT20|HT40+|HT40-]
        dev <devname> set freq <freq> [HT20|HT40+|HT40-]
        dev <devname> set freq <control freq> [20|40|80|80+80|160] [<center freq 1>] [<center freq 2>]
        phy <phyname> set channel <channel> [HT20|HT40+|HT40-]
        dev <devname> set channel <channel> [HT20|HT40+|HT40-]
        phy <phyname> set frag <fragmentation threshold|off>
        phy <phyname> set rts <rts threshold|off>
        phy <phyname> set retry [short <limit>] [long <limit>]
        phy <phyname> set netns { <pid> | name <nsname> }
        phy <phyname> set coverage <coverage class>
        phy <phyname> set distance <auto|distance>
        phy <phyname> set txpower <auto|fixed|limit> [<tx power in mBm>]
        dev <devname> set txpower <auto|fixed|limit> [<tx power in mBm>]
        phy <phyname> set antenna <bitmap> | all | <tx bitmap> <rx bitmap>
        dev <devname> set monitor <flag>*
        dev <devname> set meshid <meshid>
        dev <devname> set type <type>
        dev <devname> set 4addr <on|off>
        dev <devname> set noack_map <map>
        dev <devname> set peer <MAC address>
        dev <devname> set mcast_rate <rate in Mbps>
        dev <devname> set mesh_param <param>=<value> [<param>=<value>]*
        dev <devname> set power_save <on|off>
        dev <devname> set bitrates [legacy-<2.4|5> <legacy rate in Mbps>*] [ht-mcs-<2.4|5> <MCS index>*] [vht-mcs-<2.4|5> <NSS:MCSx,MCSy... | NSS:MCSx-MCSy>*] [sgi-2.4|lgi-2.4] [sgi-5|lgi-5]

Commands that use the netdev ('dev') can also be given the
'wdev' instead to identify the device.

You can omit the 'phy' or 'dev' if the identification is unique,
e.g. "iw wlan0 info" or "iw phy0 info". (Don't when scripting.)

Do NOT screenscrape this tool, we don't consider its output stable.
```