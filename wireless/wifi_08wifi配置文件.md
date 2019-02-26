# /vendor/etc/wifi/WCNSS_qcom_cfg.ini
```
# This file allows user to override the factory
# defaults for the WLAN Driver

# IKSWO-42425: Enable user triggered SSR
gEnableForceTargetAssert=1

# Enable IMPS or not
gEnableImps=1

# Enable BMPS or not
gEnableBmps=1

# Phy Mode (auto, b, g, n, etc)
# Valid values are 0-9, with 0 = Auto, 4 = 11n, 9 = 11ac
# 1 = 11abg, 2 = 11b, 3 = 11g, 5 = 11g only, 6 = 11n only
# 7 = 11b only 8 = 11ac only.
gDot11Mode=0

# Assigned MAC Addresses - This will be used until NV items are in place
# Each byte of MAC address is represented in Hex format as XX
Intf0MacAddress=000AF58989FF
Intf1MacAddress=000AF58989FE
Intf2MacAddress=000AF58989FD
Intf3MacAddress=000AF58989FC

# UAPSD service interval for VO,VI, BE, BK traffic
InfraUapsdVoSrvIntv=0
InfraUapsdViSrvIntv=0
InfraUapsdBeSrvIntv=0
InfraUapsdBkSrvIntv=0

# Flag to allow STA send AddTspec even when ACM is Off
gAddTSWhenACMIsOff=1

#Flag to enable HostARPOffload feature or not
hostArpOffload=1

#Flag to enable HostNSOffload feature or not
hostNSOffload=1

# 802.11n Protection flag
gEnableApProt=1

#Enable OBSS protection
gEnableApOBSSProt=1

#Enable/Disable UAPSD for SoftAP
gEnableApUapsd=1

# Fixed Rate
gFixedRate=0

# Maximum Tx power
# gTxPowerCap=30

# Fragmentation Threshold
# gFragmentationThreshold=2346

# RTS threshold
RTSThreshold=1048576

# Intra-BSS forward
gDisableIntraBssFwd=0

# WMM Enable/Disable
WmmIsEnabled=0

# 802.11d support
g11dSupportEnabled=0

# 802.11h support
g11hSupportEnabled=1

# DFS Master Capability
gEnableDFSMasterCap=0

# ESE Support and fast transition
EseEnabled=0

ImplicitQosIsEnabled=0

gNeighborScanTimerPeriod=200
gNeighborLookupThreshold=76
gNeighborScanChannelMinTime=20
gNeighborScanChannelMaxTime=30
gMaxNeighborReqTries=3

# Legacy (non-ESE, non-802.11r) Fast Roaming Support
# To enable, set FastRoamEnabled=1
# To disable, set FastRoamEnabled=0
FastRoamEnabled=1

# Check if the AP to which we are roaming is better than current AP in
# terms of RSSI.  Checking is disabled if set to Zero.Otherwise it will
# use this value as to how better the RSSI of the new/roamable AP should
# be for roaming
RoamRssiDiff=5

# To enable, set gRoamIntraBand=1 (Roaming within band)
# To disable, set gRoamIntraBand=0 (Roaming across band)
gRoamIntraBand=0

#Short Guard Interval Enable/disable
gShortGI20Mhz=1
gShortGI40Mhz=1

#Auto Shutdown  Value in seconds. A value of 0 means Auto shutoff is disabled
gAPAutoShutOff=0

#Auto Shutdown wlan : Value in Seconds. 0 means disabled. Max 1 day = 86400 sec
gWlanAutoShutdown = 0

# Not used.
gApAutoChannelSelection=0

#Preferred band (both or 2.4 only or 5 only)
BandCapability=0

#Channel Bonding
gChannelBondingMode5GHz=1

#Say gGoKeepAlivePeriod(5 seconds) and gGoLinkMonitorPeriod(10 seconds).
#For every 10 seconds DUT send Qos Null frame(i.e., Keep Alive frame if link
#is idle for last 10 seconds.) For both active and power save clients.

#Power save clients: DUT set TIM bit from 10th second onwards and till client
#honors TIM bit. If doesn't honor for 5 seconds then DUT remove client.

#Active clients: DUT send Qos Null frame for 10th seconds onwards if it is not
#success still we try on 11th second if not tries on 12th and so on till 15th
#second. Hence before disconnection DUT will send 5 NULL frames. Hence in any
#case DUT will detect client got removed in (10+5) seconds.
#i.e., (gGoKeepAlivePeriod + gGoLinkMonitorPeriod)..

#gGoLinkMonitorPeriod/ gApLinkMonitorPeriod is period where link is idle and
#it is period where we send NULL frame.
#gApLinkMonitorPeriod = 10
#gGoLinkMonitorPeriod = 10

#gGoKeepAlivePeriod/gApKeepAlivePeriod is time to spend to check whether frame
#are succeed to send or not. Hence total effective detection time is
# (gGoLinkMonitorPeriod + gGoKeepAlivePeriod) /
# (gApLinkMonitorPeriod + gApKeepAlivePeriod)
gGoKeepAlivePeriod = 20
gApKeepAlivePeriod = 20

#If set will start with active scan after driver load, otherwise will start with
#passive scan to find out the domain
gEnableBypass11d=1

#If set to 0, will not scan DFS channels
gEnableDFSChnlScan=1

# Enable DFS channel roam
# 0: DISABLE, 1: ENABLED_NORMAL, 2: ENABLED_ACTIVE
gAllowDFSChannelRoam=1

gVhtChannelWidth=2

#Data Inactivity Timeout when in powersave (in ms)
gDataInactivityTimeout=200

# Set txchainmask and rxchainmask
# These parameters are used only if gEnable2x2 is 0
# Valid values are 1,2
# Set gSetTxChainmask1x1=1 or gSetRxChainmask1x1=1 to select chain0.
# Set gSetTxChainmask1x1=2 or gSetRxChainmask1x1=2 to select chain1.
gSetTxChainmask1x1=1
gSetRxChainmask1x1=1

# Scan Timing Parameters
# gPassiveMaxChannelTime=110
# gPassiveMinChannelTime=60
gActiveMaxChannelTime=40
gActiveMinChannelTime=20

#If set to 0, MCC is not allowed.
gEnableMCCMode=1

# MCC to SCC Switch mode:
# 0-Disable
# 1-Enable
# 2-Force SCC if same band, with SAP restart
# 3-Force SCC if same band, without SAP restart by sending (E)CSA
# 4-Force SCC if same band (or) use SAP mandatory channel for DBS,
#   without SAP restart by sending (E)CSA
gWlanMccToSccSwitchMode = 3

# 1=enable STBC; 0=disable STBC
gEnableRXSTBC=1

# 1=enable tx STBC; 0=disable
gEnableTXSTBC=1

# 1=enable rx LDPC; 0=disable
gEnableRXLDPC=1

#Enable/Disable Tx beamforming
gTxBFEnable=1

#Enable/Disable Tx beamformee in SAP mode
gEnableTxBFeeSAP=1

# Enable Tx beamforming in VHT20MHz
# Valid values are 0,1. If commented out, the default value is 0.
# 0=disable, 1=enable
gEnableTxBFin20MHz=1

#Enable/Disable SU Tx beamformer support.
gEnableTxSUBeamformer=1

gEnableFastRoamInConcurrency=1

#Enable/Disable PER based roaming
gper_roam_enabled=0

#Maxium Channel time in msec
gMaxMediumTime = 6000

# 802.11K support
gRrmEnable=1

#Enable Power Save offload
gEnablePowerSaveOffload=2

#Enable firmware uart print
gEnablefwprint=0

# Firmware log mode
# Valid values are 0,1,2
# 0=Disable, 1=WMI, 2=DIAG
gEnablefwlog=1

# Maximum Receive AMPDU size (VHT only. Valid values:
# 0->8k 1->16k 2->32k 3->64k 4->128k)
gVhtAmpduLenExponent=7

# Maximum MPDU length (VHT only. Valid values:
# 0->3895 octets, 1->7991 octets, 2->11454 octets)
gVhtMpduLen=2

# Maximum number of wow filters required
#gMaxWoWFilters=22

# WOW Enable/Disable.
# 0 - Disable both magic pattern match and pattern byte match.
# 1 - Enable magic pattern match on all interfaces.
# 2 - Enable pattern byte match on all interfaces.
# 3 - Enable both magic pattern and pattern byte match on all interfaces.
# Default value of gEnableWoW is 3.
# gEnableWoW=0

# Enable or Disable MCC Adaptive Scheduler at the FW
# 1=Enable (default), 0=Disable
gEnableMCCAdaptiveScheduler=1

#Enable or Disable p2p device address administered
isP2pDeviceAddrAdministrated=1

# Set Thermal Power limit
TxPower2g=30
TxPower5g=30

# Remove Overlap channel restriction
gEnableOverLapCh=0

#Enable VHT on 2.4Ghz
gEnableVhtFor24GHzBand=1

#Maximum number of offload peers supported
# gMaxOffloadPeers=2

# controlling the following offload patterns
# through ini parameter. Default value is 1
# to disable set it to zero. ssdp = 0
# Setup multicast pattern for mDNS 224.0.0.251,
# SSDP 239.255.255.250 and LLMNR 224.0.0.252
ssdp=0

#Enable Memory Deep Sleep
gEnableMemDeepSleep=1

# Regulatory Setting; 0=STRICT; 1=CUSTOM
gRegulatoryChangeCountry=1

# RA filtering rate limit param, the current value would not
# help if the lifetime in RA is less than 3*60=3min. Then
# we need to change it, though it is uncommon.
# gRAFilterEnable=0
gRArateLimitInterval=600

# Maximum number of concurrent connections
gMaxConcurrentActiveSessions=3

# Disable/Enable GreenAP
# 0 to disable, 1 to enable, default: 1
gEnableGreenAp=1

# Disable/Enable EGAP
gEnableEGAP=1
gEGAPWaitTime=150
gEGAPFeatures=3

gIgnorePeerErpInfo=1


# Radar PRI multiplier
gDFSradarMappingPriMultiplier=4

gPNOScanSupport=1

#Enable/Disable LPASS support
# 0 to disable, 1 to enable
gEnableLpassSupport=1

# Whether userspace country code setting shld have priority
gCountryCodePriority=1

# Enable(1)/Disable(0) SIFS burst
gEnableSifsBurst=1

# Enable or Disable Multi-user MIMO
# 1=Enable (default), 0=Disable
gEnableMuBformee=1

# Enable/Disable channel avoidance for SAP in SCC scenario
# 0 - disable
# 1 - enable
gSapSccChanAvoidance=0

# Inactivity time (in ms) to end TX Service Period while in IBSS power save mode
gIbssTxSpEndInactivityTime=10

# Enable support for TDLS
#  0 - disable
#  1 - enable
gEnableTDLSSupport=1

# Enable support for Implicit Trigger of TDLS. That is, wlan driver shall
# initiate TDLS Discovery towards a peer whenever setup criteria (throughput
# and RSSI) is met and then will initiate teardown when teardown criteria
# (idle packet count and RSSI) is met.
#  0 - disable
#  1 - enable
gEnableTDLSImplicitTrigger=1

# Enable TDLS External Control. That is, user space application has to
# first configure a peer MAC in wlan driver towards which TDLS is desired.
# Device will establish TDLS only towards those configured peers whenever
# TDLS criteria (throughput and RSSI threshold) is met and teardown TDLS
# when teardown criteria (idle packet count and RSSI) is met. However,
# device will accept TDLS connection if it is initiated from any other peer,
# even if that peer is not configured.
#  0 - disable
#  1 - enable
# For TDLS External Control, Implicit Trigger must also be enabled.
gTDLSExternalControl=1

# Enable support for TDLS off-channel operation
#  0 - disable
#  1 - enable
# TDLS off-channel operation will be invoked when there is only one
# TDLS connection.
gEnableTDLSOffChannel=0
gEnableTDLSScan=1
gTDLSTxStatsPeriod=500
gTDLSTxPacketThreshold=10
gTDLSIdlePacketThreshold=1

# Enable or Disable Random MAC (Spoofing)
# 1=Enable (default), 0=Disable
gEnableMacAddrSpoof=1

################ Datapath feature set Begin ################
# Bus bandwidth threshold values in terms of number of packets
gBusBandwidthHighThreshold=2000
gBusBandwidthMediumThreshold=500
gBusBandwidthLowThreshold=150

# Bus bandwidth compute timeout value in ms
gBusBandwidthComputeInterval=100

# VHT Tx/Rx MCS values
# Valid values are 0,1,2. If commented out, the default value is 0.
# 0=MCS0-7, 1=MCS0-8, 2=MCS0-9
gVhtRxMCS=2
gVhtTxMCS=2

# VHT Tx/Rx MCS values for 2x2
# Valid values are 0,1,2. If commented out, the default value is 0.
# 0=MCS0-7, 1=MCS0-8, 2=MCS0-9
gEnable2x2=1
gVhtRxMCS2x2=2
gVhtTxMCS2x2=2

#IPA config is a bit mask and following are the configurations.
#bit0 IPA Enable
#bit1 IPA PRE Filter enable
#bit2 IPv6 enable
#bit3 IPA Resource Manager (RM) enable
#bit4 IPA Clock scaling enable
#bit5 IPA uC ENABLE
#bit6 IPA uC STA ENABLE
#bit8 IPA Real Time Debugging
gIPAConfig=0x7d
gIPADescSize=800

# Enable/Disable RX full reorder offload
gReorderOffloadSupported=1

# Enable CE classification
# 1 - enable(default)  0 - disable
gCEClassifyEnable=1

# Enable Rx handling options
# Rx_thread=1 RPS=2(default for ROME) NAPI=4(default for ihelium)
rx_mode=5

# Enable(Tx) fastpath for data traffic.
# 1 - enable(default)  0 - disable
gEnableFastPath=1

# This flag enables IP, TCP and UDP checksum offload
# 1 - enable(default)  0 - disable
gEnableIpTcpUdpChecksumOffload=1

# Enable TCP Segmentation Offload
# 1 - enable  0 - disable
TSOEnable=1

# Enable Generic Receive Offload
# 1 - enable(default)  0 - disable
GROEnable=1

# Enable HT MPDU Density
# 4 for 2 micro sec
ght_mpdu_density=4

# Enable flow steering to enable multiple CEs for Rx flows.
# Multiple Rx CEs<==>Multiple Rx IRQs<==>probably different CPUs.
# Parallel Rx paths.
# 1 - enable 0 - disable(default)
gEnableFlowSteering=1

# Time in microseconds after which a NAPI poll must yield
ce_service_max_yield_time=500

#Maximum number of HTT messages to be processed per NAPI poll
ce_service_max_rx_ind_flush=1

# Maximum number of MSDUs the firmware will pack in one HTT_T2H_MSG_TYPE_RX_IN_ORD_PADDR_IND
maxMSDUsPerRxInd=8

# Enable NUD tracking feature
# 1 - enable 0 - disable(default)
gEnableNUDTracking=1

# Enable PEER UNMAP CONF SUPPORT
# 1 - enable 0 - disable(default)
gEnablePeerUnmapConfSupport=1

################ Datapath feature set End ################

################ NAN feature set start ###################

# Enable NAN discovery (NAN 1.0)
# 1 - enable  0 - disable(default)
gEnableNanSupport=0

################ NAN feature set end #####################

# Enable/Disable FILS support in driver
# 1 - enable(default)  0 - disable
g_is_fils_enabled=0

# Flag to enable/disable FILS element in Probe Request
g_enable_bcast_probe_rsp=0

adaptive_dwell_mode_enabled=1

hostscan_adaptive_dwell_mode=1

adapt_dwell_lpf_weight=80

adapt_dwell_wifi_act_threshold=10

MAWCEnabled=0

drop_bcn_on_chan_mismatch=0

# Enable/Disable rtt sta mac randomization
enable_rtt_mac_randomization=1

#Enable/Disable SNR monitoring
gEnableSNRMonitoring=1

#Enable/Disable LPRx
gEnableLPRx=0

# Enable the FW crash inject
gEnableForceTargetAssert=1

END

# Note: Configuration parser would not read anything past the END marker




```




# /vendor/etc/wifi/wpa_supplicant.conf
```
update_config=1
ctrl_interface=wlan0
eapol_version=1
ap_scan=1
fast_reauth=1
p2p_add_cli_chan=1
p2p_no_group_iface=1
config_methods=virtual_display virtual_push_button
disable_scan_offload=1



```
## update_config
```

# Whether to allow wpa_supplicant to update (overwrite) configuration
#
# This option can be used to allow wpa_supplicant to overwrite configuration
# file whenever configuration is changed (e.g., new network block is added with
# wpa_cli or wpa_gui, or a password is changed). This is required for
# wpa_cli/wpa_gui to be able to store the configuration changes permanently.
# Please note that overwriting configuration file will remove the comments from
# it.

update_config=1
```

## ctrl_interface
```
# global configuration (shared by all network blocks)
#
# Parameters for the control interface. If this is specified, wpa_supplicant
# will open a control interface that is available for external programs to
# manage wpa_supplicant. The meaning of this string depends on which control
# interface mechanism is used. For all cases, the existance of this parameter
# in configuration is used to determine whether the control interface is
# enabled.
#
# For UNIX domain sockets (default on Linux and BSD): This is a directory that
# will be created for UNIX domain sockets for listening to requests from
# external programs (CLI/GUI, etc.) for status information and configuration.
# The socket file will be named based on the interface name, so multiple
# wpa_supplicant processes can be run at the same time if more than one
# interface is used.
# /var/run/wpa_supplicant is the recommended directory for sockets and by
# default, wpa_cli will use it when trying to connect with wpa_supplicant.
#
# Access control for the control interface can be configured by setting the
# directory to allow only members of a group to use sockets. This way, it is
# possible to run wpa_supplicant as root (since it needs to change network
# configuration and open raw sockets) and still allow GUI/CLI components to be
# run as non-root users. However, since the control interface can be used to
# change the network configuration, this access needs to be protected in many
# cases. By default, wpa_supplicant is configured to use gid 0 (root). If you
# want to allow non-root users to use the control interface, add a new group
# and change this value to match with that group. Add users that should have
# control interface access to this group. If this variable is commented out or
# not included in the configuration file, group will not be changed from the
# value it got by default when the directory or socket was created.

ctrl_interface=wlan0
```

## eapol_version
```
# IEEE 802.1X/EAPOL version
# wpa_supplicant is implemented based on IEEE Std 802.1X-2004 which defines
# EAPOL version 2. However, there are many APs that do not handle the new
# version number correctly (they seem to drop the frames completely). In order
# to make wpa_supplicant interoperate with these APs, the version number is set
# to 1 by default. This configuration value can be used to set it to the new
# version (2).

eapol_version=1


```

## ap_scan
```
# AP scanning/selection
# By default, wpa_supplicant requests driver to perform AP scanning and then
# uses the scan results to select a suitable AP. Another alternative is to
# allow the driver to take care of AP scanning and selection and use
# wpa_supplicant just to process EAPOL frames based on IEEE 802.11 association
# information from the driver.
# 1: wpa_supplicant initiates scanning and AP selection
# 0: driver takes care of scanning, AP selection, and IEEE 802.11 association
#    parameters (e.g., WPA IE generation); this mode can also be used with
#    non-WPA drivers when using IEEE 802.1X mode; do not try to associate with
#    APs (i.e., external program needs to control association). This mode must
#    also be used when using wired Ethernet drivers.
# 2: like 0, but associate with APs using security policy and SSID (but not
#    BSSID); this can be used, e.g., with ndiswrapper and NDIS drivers to
#    enable operation with hidden SSIDs and optimized roaming; in this mode,
#    the network blocks in the configuration file are tried one by one until
#    the driver reports successful association; each network block should have
#    explicit security policy (i.e., only one option in the lists) for
#    key_mgmt, pairwise, group, proto variables

ap_scan=1
```

## fast_reauth
```
# EAP fast re-authentication
# By default, fast re-authentication is enabled for all EAP methods that
# support it. This variable can be used to disable fast re-authentication.
# Normally, there is no need to disable this.
fast_reauth=1

```





## disable_scan_offload
```
# disable_scan_offload - Disable automatic offloading of scan requests
# By default, wpa_supplicant tries to offload scanning if the driver
# indicates support for this (sched_scan). This configuration
# parameter can be used to disable this offloading mechanism.
disable_scan_offload=1


```


## load_dynamic_eap
```
# Dynamic EAP methods
# If EAP methods were built dynamically as shared object files, they need to be
# loaded here before being used in the network blocks. By default, EAP methods
# are included statically in the build, so these lines are not needed

load_dynamic_eap=/usr/lib/wpa_supplicant/eap_tls.so
load_dynamic_eap=/usr/lib/wpa_supplicant/eap_md5.so

```


## driver_param
```

# Driver interface parameters
# This field can be used to configure arbitrary driver interace parameters. The
# format is specific to the selected driver interface. This field is not used
# in most cases.
driver_param="field=value"

```


## country
```
# Country code
# The ISO/IEC alpha2 country code for the country in which this device is
# currently operating.
country=US

```

## dot11RSNAConfigPMKLifetime
```
# Maximum lifetime for PMKSA in seconds; default 43200
dot11RSNAConfigPMKLifetime=43200

```

## dot11RSNAConfigPMKReauthThreshold
```
# Threshold for reauthentication (percentage of PMK lifetime); default 70
dot11RSNAConfigPMKReauthThreshold=70

```

## dot11RSNAConfigSATimeout
```

# Timeout for security association negotiation in seconds; default 60
dot11RSNAConfigSATimeout=60

```

## config_methods
```
# Wi-Fi Protected Setup (WPS) parameters
# Config Methods
# List of the supported configuration methods
# Available methods: usba ethernet label display ext_nfc_token int_nfc_token
#   nfc_interface push_button keypad virtual_display physical_display
#   virtual_push_button physical_push_button
# For WSC 2.0:

config_methods=virtual_display virtual_push_button

```

## uuid
```

# Universally Unique IDentifier (UUID; see RFC 4122) of the device
# If not configured, UUID will be generated based on the local MAC address.
#uuid=12345678-9abc-def0-1234-56789abcdef0

```


## device_name
```
# Device Name
# User-friendly description of device; up to 32 octets encoded in UTF-8
device_name=Wireless Client

```

## wps_cred_processing
```
# Credential processing
#   0 = process received credentials internally (default)
#   1 = do not process received credentials; just pass them over ctrl_iface to
#	external program(s)
#   2 = process received credentials internally and pass them over ctrl_iface
#	to external program(s)
wps_cred_processing=0

```

## p2p_no_group_iface
```
# Seperate Group interface creation
#  1 = use same inteface for GO
#  0 = use separate interface per GO (for driver)
p2p_no_group_iface=1

```

## network_block网络字段说明
```

# network block
#
# Each network (usually AP's sharing the same SSID) is configured as a separate
# block in this configuration file. The network blocks are in preference order
# (the first match is used).
#
# network block fields:
#
# disabled:
#	0 = this network can be used (default)
#	1 = this network block is disabled (can be enabled through ctrl_iface,
#	    e.g., with wpa_cli or wpa_gui)
#
# id_str: Network identifier string for external scripts. This value is passed
#	to external action script through wpa_cli as WPA_ID_STR environment
#	variable to make it easier to do network specific configuration.
#
# ssid: SSID (mandatory); either as an ASCII string with double quotation or
#	as hex string; network name
#
# scan_ssid:
#	0 = do not scan this SSID with specific Probe Request frames (default)
#	1 = scan with SSID-specific Probe Request frames (this can be used to
#	    find APs that do not accept broadcast SSID or use multiple SSIDs;
#	    this will add latency to scanning, so enable this only when needed)
#
# bssid: BSSID (optional); if set, this network block is used only when
#	associating with the AP using the configured BSSID
#
# priority: priority group (integer)
# By default, all networks will get same priority group (0). If some of the
# networks are more desirable, this field can be used to change the order in
# which wpa_supplicant goes through the networks when selecting a BSS. The
# priority groups will be iterated in decreasing priority (i.e., the larger the
# priority value, the sooner the network is matched against the scan results).
# Within each priority group, networks will be selected based on security
# policy, signal strength, etc.
# Please note that AP scanning with scan_ssid=1 and ap_scan=2 mode are not
# using this priority to select the order for scanning. Instead, they try the
# networks in the order that used in the configuration file.
#
# mode: IEEE 802.11 operation mode
# 0 = infrastructure (Managed) mode, i.e., associate with an AP (default)
# 1 = IBSS (ad-hoc, peer-to-peer)
# Note: IBSS can only be used with key_mgmt NONE (plaintext and static WEP)
# and key_mgmt=WPA-NONE (fixed group key TKIP/CCMP). In addition, ap_scan has
# to be set to 2 for IBSS. WPA-None requires following network block options:
# proto=WPA, key_mgmt=WPA-NONE, pairwise=NONE, group=TKIP (or CCMP, but not
# both), and psk must also be set.
#
# frequency: Channel frequency in megahertz (MHz) for IBSS, e.g.,
# 2412 = IEEE 802.11b/g channel 1. This value is used to configure the initial
# channel for IBSS (adhoc) networks. It is ignored in the infrastructure mode.
# In addition, this value is only used by the station that creates the IBSS. If
# an IBSS network with the configured SSID is already present, the frequency of
# the network will be used instead of this configured value.
#
# proto: list of accepted protocols
# WPA = WPA/IEEE 802.11i/D3.0
# RSN = WPA2/IEEE 802.11i (also WPA2 can be used as an alias for RSN)
# If not set, this defaults to: WPA RSN
#
# key_mgmt: list of accepted authenticated key management protocols
# WPA-PSK = WPA pre-shared key (this requires 'psk' field)
# WPA-EAP = WPA using EAP authentication
# IEEE8021X = IEEE 802.1X using EAP authentication and (optionally) dynamically
#	generated WEP keys
# NONE = WPA is not used; plaintext or static WEP could be used
# WPA-PSK-SHA256 = Like WPA-PSK but using stronger SHA256-based algorithms
# WPA-EAP-SHA256 = Like WPA-EAP but using stronger SHA256-based algorithms
# If not set, this defaults to: WPA-PSK WPA-EAP
#
# auth_alg: list of allowed IEEE 802.11 authentication algorithms
# OPEN = Open System authentication (required for WPA/WPA2)
# SHARED = Shared Key authentication (requires static WEP keys)
# LEAP = LEAP/Network EAP (only used with LEAP)
# If not set, automatic selection is used (Open System with LEAP enabled if
# LEAP is allowed as one of the EAP methods).
#
# pairwise: list of accepted pairwise (unicast) ciphers for WPA
# CCMP = AES in Counter mode with CBC-MAC [RFC 3610, IEEE 802.11i/D7.0]
# TKIP = Temporal Key Integrity Protocol [IEEE 802.11i/D7.0]
# NONE = Use only Group Keys (deprecated, should not be included if APs support
#	pairwise keys)
# If not set, this defaults to: CCMP TKIP
#
# group: list of accepted group (broadcast/multicast) ciphers for WPA
# CCMP = AES in Counter mode with CBC-MAC [RFC 3610, IEEE 802.11i/D7.0]
# TKIP = Temporal Key Integrity Protocol [IEEE 802.11i/D7.0]
# WEP104 = WEP (Wired Equivalent Privacy) with 104-bit key
# WEP40 = WEP (Wired Equivalent Privacy) with 40-bit key [IEEE 802.11]
# If not set, this defaults to: CCMP TKIP WEP104 WEP40
#
# psk: WPA preshared key; 256-bit pre-shared key
# The key used in WPA-PSK mode can be entered either as 64 hex-digits, i.e.,
# 32 bytes or as an ASCII passphrase (in which case, the real PSK will be
# generated using the passphrase and SSID). ASCII passphrase must be between
# 8 and 63 characters (inclusive).
# This field is not needed, if WPA-EAP is used.
# Note: Separate tool, wpa_passphrase, can be used to generate 256-bit keys
# from ASCII passphrase. This process uses lot of CPU and wpa_supplicant
# startup and reconfiguration time can be optimized by generating the PSK only
# only when the passphrase or SSID has actually changed.
#
# eapol_flags: IEEE 802.1X/EAPOL options (bit field)
# Dynamic WEP key required for non-WPA mode
# bit0 (1): require dynamically generated unicast WEP key
# bit1 (2): require dynamically generated broadcast WEP key
# 	(3 = require both keys; default)
# Note: When using wired authentication, eapol_flags must be set to 0 for the
# authentication to be completed successfully.
#
# mixed_cell: This option can be used to configure whether so called mixed
# cells, i.e., networks that use both plaintext and encryption in the same
# SSID, are allowed when selecting a BSS form scan results.
# 0 = disabled (default)
# 1 = enabled
#
# proactive_key_caching:
# Enable/disable opportunistic PMKSA caching for WPA2.
# 0 = disabled (default)
# 1 = enabled
#
# wep_key0..3: Static WEP key (ASCII in double quotation, e.g. "abcde" or
# hex without quotation, e.g., 0102030405)
# wep_tx_keyidx: Default WEP key index (TX) (0..3)
#
# peerkey: Whether PeerKey negotiation for direct links (IEEE 802.11e DLS) is
# allowed. This is only used with RSN/WPA2.
# 0 = disabled (default)
# 1 = enabled
#peerkey=1
#
# wpa_ptk_rekey: Maximum lifetime for PTK in seconds. This can be used to
# enforce rekeying of PTK to mitigate some attacks against TKIP deficiencies.
#
# Following fields are only used with internal EAP implementation.
# eap: space-separated list of accepted EAP methods
#	MD5 = EAP-MD5 (unsecure and does not generate keying material ->
#			cannot be used with WPA; to be used as a Phase 2 method
#			with EAP-PEAP or EAP-TTLS)
#       MSCHAPV2 = EAP-MSCHAPv2 (cannot be used separately with WPA; to be used
#		as a Phase 2 method with EAP-PEAP or EAP-TTLS)
#       OTP = EAP-OTP (cannot be used separately with WPA; to be used
#		as a Phase 2 method with EAP-PEAP or EAP-TTLS)
#       GTC = EAP-GTC (cannot be used separately with WPA; to be used
#		as a Phase 2 method with EAP-PEAP or EAP-TTLS)
#	TLS = EAP-TLS (client and server certificate)
#	PEAP = EAP-PEAP (with tunnelled EAP authentication)
#	TTLS = EAP-TTLS (with tunnelled EAP or PAP/CHAP/MSCHAP/MSCHAPV2
#			 authentication)
#	If not set, all compiled in methods are allowed.
#
# identity: Identity string for EAP
#	This field is also used to configure user NAI for
#	EAP-PSK/PAX/SAKE/GPSK.
# anonymous_identity: Anonymous identity string for EAP (to be used as the
#	unencrypted identity with EAP types that support different tunnelled
#	identity, e.g., EAP-TTLS)
# password: Password string for EAP. This field can include either the
#	plaintext password (using ASCII or hex string) or a NtPasswordHash
#	(16-byte MD4 hash of password) in hash:<32 hex digits> format.
#	NtPasswordHash can only be used when the password is for MSCHAPv2 or
#	MSCHAP (EAP-MSCHAPv2, EAP-TTLS/MSCHAPv2, EAP-TTLS/MSCHAP, LEAP).
#	EAP-PSK (128-bit PSK), EAP-PAX (128-bit PSK), and EAP-SAKE (256-bit
#	PSK) is also configured using this field. For EAP-GPSK, this is a
#	variable length PSK.
# ca_cert: File path to CA certificate file (PEM/DER). This file can have one
#	or more trusted CA certificates. If ca_cert and ca_path are not
#	included, server certificate will not be verified. This is insecure and
#	a trusted CA certificate should always be configured when using
#	EAP-TLS/TTLS/PEAP. Full path should be used since working directory may
#	change when wpa_supplicant is run in the background.
#	On Windows, trusted CA certificates can be loaded from the system
#	certificate store by setting this to cert_store://<name>, e.g.,
#	ca_cert="cert_store://CA" or ca_cert="cert_store://ROOT".
#	Note that when running wpa_supplicant as an application, the user
#	certificate store (My user account) is used, whereas computer store
#	(Computer account) is used when running wpasvc as a service.
# ca_path: Directory path for CA certificate files (PEM). This path may
#	contain multiple CA certificates in OpenSSL format. Common use for this
#	is to point to system trusted CA list which is often installed into
#	directory like /etc/ssl/certs. If configured, these certificates are
#	added to the list of trusted CAs. ca_cert may also be included in that
#	case, but it is not required.
# client_cert: File path to client certificate file (PEM/DER)
#	Full path should be used since working directory may change when
#	wpa_supplicant is run in the background.
#	Alternatively, a named configuration blob can be used by setting this
#	to blob://<blob name>.
# private_key: File path to client private key file (PEM/DER/PFX)
#	When PKCS#12/PFX file (.p12/.pfx) is used, client_cert should be
#	commented out. Both the private key and certificate will be read from
#	the PKCS#12 file in this case. Full path should be used since working
#	directory may change when wpa_supplicant is run in the background.
#	Windows certificate store can be used by leaving client_cert out and
#	configuring private_key in one of the following formats:
#	cert://substring_to_match
#	hash://certificate_thumbprint_in_hex
#	for example: private_key="hash://63093aa9c47f56ae88334c7b65a4"
#	Note that when running wpa_supplicant as an application, the user
#	certificate store (My user account) is used, whereas computer store
#	(Computer account) is used when running wpasvc as a service.
#	Alternatively, a named configuration blob can be used by setting this
#	to blob://<blob name>.
# private_key_passwd: Password for private key file (if left out, this will be
#	asked through control interface)
# dh_file: File path to DH/DSA parameters file (in PEM format)
#	This is an optional configuration file for setting parameters for an
#	ephemeral DH key exchange. In most cases, the default RSA
#	authentication does not use this configuration. However, it is possible
#	setup RSA to use ephemeral DH key exchange. In addition, ciphers with
#	DSA keys always use ephemeral DH keys. This can be used to achieve
#	forward secrecy. If the file is in DSA parameters format, it will be
#	automatically converted into DH params.
# subject_match: Substring to be matched against the subject of the
#	authentication server certificate. If this string is set, the server
#	sertificate is only accepted if it contains this string in the subject.
#	The subject string is in following format:
#	/C=US/ST=CA/L=San Francisco/CN=Test AS/emailAddress=as@example.com
# altsubject_match: Semicolon separated string of entries to be matched against
#	the alternative subject name of the authentication server certificate.
#	If this string is set, the server sertificate is only accepted if it
#	contains one of the entries in an alternative subject name extension.
#	altSubjectName string is in following format: TYPE:VALUE
#	Example: EMAIL:server@example.com
#	Example: DNS:server.example.com;DNS:server2.example.com
#	Following types are supported: EMAIL, DNS, URI
# phase1: Phase1 (outer authentication, i.e., TLS tunnel) parameters
#	(string with field-value pairs, e.g., "peapver=0" or
#	"peapver=1 peaplabel=1")
#	'peapver' can be used to force which PEAP version (0 or 1) is used.
#	'peaplabel=1' can be used to force new label, "client PEAP encryption",
#	to be used during key derivation when PEAPv1 or newer. Most existing
#	PEAPv1 implementation seem to be using the old label, "client EAP
#	encryption", and wpa_supplicant is now using that as the default value.
#	Some servers, e.g., Radiator, may require peaplabel=1 configuration to
#	interoperate with PEAPv1; see eap_testing.txt for more details.
#	'peap_outer_success=0' can be used to terminate PEAP authentication on
#	tunneled EAP-Success. This is required with some RADIUS servers that
#	implement draft-josefsson-pppext-eap-tls-eap-05.txt (e.g.,
#	Lucent NavisRadius v4.4.0 with PEAP in "IETF Draft 5" mode)
#	include_tls_length=1 can be used to force wpa_supplicant to include
#	TLS Message Length field in all TLS messages even if they are not
#	fragmented.
#	sim_min_num_chal=3 can be used to configure EAP-SIM to require three
#	challenges (by default, it accepts 2 or 3)
#	result_ind=1 can be used to enable EAP-SIM and EAP-AKA to use
#	protected result indication.
#	'crypto_binding' option can be used to control PEAPv0 cryptobinding
#	behavior:
#	 * 0 = do not use cryptobinding (default)
#	 * 1 = use cryptobinding if server supports it
#	 * 2 = require cryptobinding
#	EAP-WSC (WPS) uses following options: pin=<Device Password> or
#	pbc=1.
# phase2: Phase2 (inner authentication with TLS tunnel) parameters
#	(string with field-value pairs, e.g., "auth=MSCHAPV2" for EAP-PEAP or
#	"autheap=MSCHAPV2 autheap=MD5" for EAP-TTLS)
# Following certificate/private key fields are used in inner Phase2
# authentication when using EAP-TTLS or EAP-PEAP.
# ca_cert2: File path to CA certificate file. This file can have one or more
#	trusted CA certificates. If ca_cert2 and ca_path2 are not included,
#	server certificate will not be verified. This is insecure and a trusted
#	CA certificate should always be configured.
# ca_path2: Directory path for CA certificate files (PEM)
# client_cert2: File path to client certificate file
# private_key2: File path to client private key file
# private_key2_passwd: Password for private key file
# dh_file2: File path to DH/DSA parameters file (in PEM format)
# subject_match2: Substring to be matched against the subject of the
#	authentication server certificate.
# altsubject_match2: Substring to be matched against the alternative subject
#	name of the authentication server certificate.
#
# fragment_size: Maximum EAP fragment size in bytes (default 1398).
#	This value limits the fragment size for EAP methods that support
#	fragmentation (e.g., EAP-TLS and EAP-PEAP). This value should be set
#	small enough to make the EAP messages fit in MTU of the network
#	interface used for EAPOL. The default value is suitable for most
#	cases.
#
# EAP-FAST variables:
# pac_file: File path for the PAC entries. wpa_supplicant will need to be able
#	to create this file and write updates to it when PAC is being
#	provisioned or refreshed. Full path to the file should be used since
#	working directory may change when wpa_supplicant is run in the
#	background. Alternatively, a named configuration blob can be used by
#	setting this to blob://<blob name>
# phase1: fast_provisioning option can be used to enable in-line provisioning
#         of EAP-FAST credentials (PAC):
#         0 = disabled,
#         1 = allow unauthenticated provisioning,
#         2 = allow authenticated provisioning,
#         3 = allow both unauthenticated and authenticated provisioning
#	fast_max_pac_list_len=<num> option can be used to set the maximum
#		number of PAC entries to store in a PAC list (default: 10)
#	fast_pac_format=binary option can be used to select binary format for
#		storing PAC entries in order to save some space (the default
#		text format uses about 2.5 times the size of minimal binary
#		format)
#
# wpa_supplicant supports number of "EAP workarounds" to work around
# interoperability issues with incorrectly behaving authentication servers.
# These are enabled by default because some of the issues are present in large
# number of authentication servers. Strict EAP conformance mode can be
# configured by disabling workarounds with eap_workaround=0.


```

## 预置网络实例

### WPA-PSK网络配置1
```
# Simple case: WPA-PSK, PSK as an ASCII passphrase, allow all valid ciphers
network={
	ssid="simple"
	psk="very secret passphrase"
	priority=5
}

```

### WPA-PSK网络配置2
```
## Same as previous, but request SSID-specific scanning (for APs that reject broadcast SSID)
network={
	ssid="second ssid"
	scan_ssid=1
	psk="very secret passphrase"
	priority=2
}


```


### WPA-PSK网络配置3
```
## Only WPA-PSK is used. Any valid cipher combination is accepted.
network={
	ssid="example"
	proto=WPA
	key_mgmt=WPA-PSK
	pairwise=CCMP TKIP
	group=CCMP TKIP WEP104 WEP40
	psk=06b4be19da289f475aa46a33cb793029d4ab3db7a23ee92382eb0106c72ac7bb
	priority=2
}

```

### WPA-PSK(TKIP)网络配置4
```

## WPA-Personal(PSK) with TKIP and enforcement for frequent PTK rekeying
network={
	ssid="example"
	proto=WPA
	key_mgmt=WPA-PSK
	pairwise=TKIP
	group=TKIP
	psk="not so secure passphrase"
	wpa_ptk_rekey=600
}

```

###  WPA-EAP 网络
```
## Only WPA-EAP is used. Both CCMP and TKIP is accepted. An AP that used WEP104
## or WEP40 as the group cipher will not be accepted.
network={
	ssid="example"
	proto=RSN
	key_mgmt=WPA-EAP
	pairwise=CCMP TKIP
	group=CCMP TKIP
	eap=TLS
	identity="user@example.com"
	ca_cert="/etc/cert/ca.pem"
	client_cert="/etc/cert/user.pem"
	private_key="/etc/cert/user.prv"
	private_key_passwd="password"
	priority=1
}


```


### EAP-PEAP/MSCHAPv2
```

## EAP-PEAP/MSCHAPv2 configuration for RADIUS servers that use the new peaplabel
## (e.g., Radiator)
network={
	ssid="example"
	key_mgmt=WPA-EAP
	eap=PEAP
	identity="user@example.com"
	password="foobar"
	ca_cert="/etc/cert/ca.pem"
	phase1="peaplabel=1"
	phase2="auth=MSCHAPV2"
	priority=10
}

```


### EAP-TTLS/EAP-MD5-Challenge
```

## EAP-TTLS/EAP-MD5-Challenge configuration with anonymous identity for the
## unencrypted use. Real identity is sent only within an encrypted TLS tunnel.
network={
	ssid="example"
	key_mgmt=WPA-EAP
	eap=TTLS
	identity="user@example.com"
	anonymous_identity="anonymous@example.com"
	password="foobar"
	ca_cert="/etc/cert/ca.pem"
	priority=2
}


```



### EAP-TTLS/MSCHAPv2
```
## EAP-TTLS/MSCHAPv2 configuration with anonymous identity for the unencrypted
## use. Real identity is sent only within an encrypted TLS tunnel.
network={
	ssid="example"
	key_mgmt=WPA-EAP
	eap=TTLS
	identity="user@example.com"
	anonymous_identity="anonymous@example.com"
	password="foobar"
	ca_cert="/etc/cert/ca.pem"
	phase2="auth=MSCHAPV2"
}


```

### WPA-EAP/EAP-TTLS/Different_CA_Certificate
```
## WPA-EAP, EAP-TTLS with different CA certificate used for outer and inner
## authentication.
network={
	ssid="example"
	key_mgmt=WPA-EAP
	eap=TTLS
	# Phase1 / outer authentication
	anonymous_identity="anonymous@example.com"
	ca_cert="/etc/cert/ca.pem"
	# Phase 2 / inner authentication
	phase2="autheap=TLS"
	ca_cert2="/etc/cert/ca2.pem"
	client_cert2="/etc/cer/user.pem"
	private_key2="/etc/cer/user.prv"
	private_key2_passwd="password"
	priority=2
}

```

### WPA-PSK/WPA-EAP/CCMP
```
## Both WPA-PSK and WPA-EAP is accepted. Only CCMP is accepted as pairwise and
## group cipher.
network={
	ssid="example"
	bssid=00:11:22:33:44:55
	proto=WPA RSN
	key_mgmt=WPA-PSK WPA-EAP
	pairwise=CCMP
	group=CCMP
	psk=06b4be19da289f475aa46a33cb793029d4ab3db7a23ee92382eb0106c72ac7bb
}




```


### WPA-PSK/WPA-EAP/(Special characters in SSID)
```
## Special characters in SSID, so use hex string. Default to WPA-PSK, WPA-EAP
## and all valid ciphers.
network={
	ssid=00010203
	psk=000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f
}

```



### EAP-SIM
```
## EAP-SIM with a GSM SIM or USIM
network={
	ssid="eap-sim-test"
	key_mgmt=WPA-EAP
	eap=SIM
	pin="1234"
	pcsc=""
}

```

### EAP-PSK
```

## EAP-PSK
network={
	ssid="eap-psk-test"
	key_mgmt=WPA-EAP
	eap=PSK
	anonymous_identity="eap_psk_user"
	password=06b4be19da289f475aa46a33cb793029
	identity="eap_psk_user@example.com"
}

```

### 802.1X/EAP-TLS/EAPOL(WEP NO-WPA)
```
## IEEE 802.1X/EAPOL with dynamically generated WEP keys (i.e., no WPA) using
## EAP-TLS for authentication and key generation; require both unicast and
## broadcast WEP keys.
network={
	ssid="1x-test"
	key_mgmt=IEEE8021X
	eap=TLS
	identity="user@example.com"
	ca_cert="/etc/cert/ca.pem"
	client_cert="/etc/cert/user.pem"
	private_key="/etc/cert/user.prv"
	private_key_passwd="password"
	eapol_flags=3
}

```


### LEAP
```
# LEAP with dynamic WEP keys
network={
	ssid="leap-example"
	key_mgmt=IEEE8021X
	eap=LEAP
	identity="user"
	password="foobar"
}


```



### EAP-IKEv2
```
## EAP-IKEv2 using shared secrets for both server and peer authentication
network={
	ssid="ikev2-example"
	key_mgmt=WPA-EAP
	eap=IKEV2
	identity="user"
	password="foobar"
}

```



### EAP-FAST
```
## EAP-FAST with WPA (WPA or WPA2)
network={
	ssid="eap-fast-test"
	key_mgmt=WPA-EAP
	eap=FAST
	anonymous_identity="FAST-000102030405"
	identity="username"
	password="password"
	phase1="fast_provisioning=1"
	pac_file="/etc/wpa_supplicant.eap-fast-pac"
}



network={
	ssid="eap-fast-test"
	key_mgmt=WPA-EAP
	eap=FAST
	anonymous_identity="FAST-000102030405"
	identity="username"
	password="password"
	phase1="fast_provisioning=1"
	pac_file="blob://eap-fast-pac"
}

```


### Plaintext connection|open network开放网络
```
## Plaintext connection (no WPA, no IEEE 802.1X)
network={
	ssid="plaintext-test"
	key_mgmt=NONE
}

```
### Shared WEP key connection (no WPA, no IEEE 802.1X)
```
## Shared WEP key connection (no WPA, no IEEE 802.1X) using Shared Key
## IEEE 802.11 authentication
network={
	ssid="static-wep-test2"
	key_mgmt=NONE
	wep_key0="abcde"
	wep_key1=0102030405
	wep_key2="1234567890123"
	wep_tx_keyidx=0
	priority=5
	auth_alg=SHARED
}


```


### IBSS/ad-hoc network with WPA-None/TKIP.
```
## IBSS/ad-hoc network with WPA-None/TKIP.
network={
	ssid="test adhoc"
	mode=1
	frequency=2412
	proto=WPA
	key_mgmt=WPA-NONE
	pairwise=NONE
	group=TKIP
	psk="secret passphrase"
}


```

### network可选可写配置项
```
network={
	ssid="example"
	scan_ssid=1
	key_mgmt=WPA-EAP WPA-PSK IEEE8021X NONE
	pairwise=CCMP TKIP
	group=CCMP TKIP WEP104 WEP40
	psk="very secret passphrase"
	eap=TTLS PEAP TLS
	identity="user@example.com"
	password="foobar"
	ca_cert="/etc/cert/ca.pem"
	client_cert="/etc/cert/user.pem"
	private_key="/etc/cert/user.prv"
	private_key_passwd="password"
	phase1="peaplabel=0"
}

```

# /system/etc/wifi/p2p_supplicant.conf
```
disable_scan_offload=1
p2p_no_group_iface=1
persistent_reconnect=1
bss_max_count=400

```
