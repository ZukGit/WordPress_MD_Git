import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class E6_Android_Info {

    static NumberFormat nf = new DecimalFormat("0.00");
    static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
  //  static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss",Locale.US);
    static File wifiLogFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "E2_WifiDetail.txt");



    static File propFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "1_Prop.txt");
    static File cpuinfoFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "2_1_CpuInfo.txt");
    static File meminfoFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "2_2_MemInfo.txt");
    static File batteryFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "2_3_Battery.txt");


    static File densityFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "3_1_WM_Density.txt");
    static File sizeFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "3_2_WM_Size.txt");

    static File telecomFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "4_1_Telephony_Registry.txt");

    static File bluetoothFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "5_1_Bluetooth_Manager.txt");


    static File dumpsys_wifi_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "6_1_Wifi.txt");
    static File dumpsys_wifiscanner_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "6_2_Wifiscanner.txt");
    static File iw_phy0_reg_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "6_3_IW_Reg.txt");
    static File wifiConfigStorehFile = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator+"E6"+File.separator + "6_4_WifiConfigStore.txt");




    static class WIFI_DUMP_ITEM{
        String originLine;
        String mItemKey;
        String mItemValue;
        ArrayList<String> mItemValueList;
        String mDesc;
        String mPath;  // 属性路径

        WIFI_DUMP_ITEM(String originLineX , String mItemKeyX , String mItemValueX ,  String mDescX ,ArrayList<String>  mValueList){
            this.originLine = originLineX;
            this.mItemKey = mItemKeyX;
            this.mItemValue = mItemValueX;
            this.mDesc = mDescX;
            this.mItemValueList = mValueList;
        }

        WIFI_DUMP_ITEM(String originLineX , String mItemKeyX , String mItemValueX ,  String mDescX ,String path){
            this.originLine = originLineX;
            this.mItemKey = mItemKeyX;
            this.mItemValue = mItemValueX;
            this.mDesc = mDescX;
            this.mPath = path;
        }

        WIFI_DUMP_ITEM(String originLineX , String mItemKeyX , String mItemValueX ,  String mDescX ){
            this.originLine = originLineX;
            this.mItemKey = mItemKeyX;
            this.mItemValue = mItemValueX;
            this.mDesc = mDescX;
            this.mPath = "";
        }


        WIFI_DUMP_ITEM(String mItemKeyTemp , String mDesc){
            this.mItemKey = mItemKeyTemp;
            this.mDesc = mDesc;
        }

        public void setmItemValue(String mItemValue) {
            this.mItemValue = mItemValue;
        }

        public void setOriginLine(String originLine) {
            this.originLine = originLine;
        }

        public String getmDesc() {
            return mDesc;
        }

        public String getmItemKey() {
            return mItemKey;
        }

        public String getmItemValue() {
            return mItemValue;
        }

        public String getOriginLine() {
            return originLine;
        }
    }

    abstract class Wifi_Dump_Base{
     public   ArrayList<String>  keyList; // key list
     public   Map<String,ArrayList<WIFI_DUMP_ITEM>> curDataMap;   // key  value
     public   String beginStr;
        public   String blockTitle;
     public   boolean hasBegin;
        public   boolean hasEnd;
        abstract  String tryAnalysisLine(String oriLine);
        abstract  void initMap();
        abstract  void initKeyList();
        abstract boolean isBeginForLine(String curLine);
        abstract String  getValueFromOriLine(String curLine,String key);
    }

    abstract class WifiCommon extends  Wifi_Dump_Base{
        WifiCommon(String beginStrParam , String title){
        this.beginStr = beginStrParam;
        this.blockTitle = title;
        this.hasBegin = false;
        this.hasEnd = false;

        if(keyList == null){
            keyList = new  ArrayList<String>();
        }else{
            keyList.clear();
        }
        initKeyList();
        initMap();
        }

        @Override
        void initMap() {
            if(this.keyList != null && this.keyList.size() > 0){
                this.curDataMap = new HashMap<String,ArrayList<WIFI_DUMP_ITEM>>();
                for (int i = 0; i < this.keyList.size(); i++) {
                    String keyStr = this.keyList.get(i);
                    ArrayList<WIFI_DUMP_ITEM> itemArr = new   ArrayList<WIFI_DUMP_ITEM>();
                    curDataMap.put(keyStr,itemArr);

                }

            }
        }

        @Override
        synchronized  boolean  isBeginForLine(String curLine) {
          //  if(curLine.contains(this.beginStr) ){
                if(curLine.contains(this.beginStr) && curLine.trim().startsWith(this.beginStr.trim())){
             //   System.out.println("curLine ="+curLine);
             //   System.out.println("beginStr ="+beginStr);
                this.hasBegin = true;
                this.hasEnd = false;
                return true;
            }

            for (int i = 0; i < allBase_Static.size(); i++) {
                String beginStrTemp = allBase_Static.get(i).beginStr;
                if(this.beginStr.equals(beginStrTemp)){
                    continue;
                }else{
                    if(curLine.contains(beginStrTemp) ){
                        if(curLine.contains(beginStrTemp)&& curLine.trim().startsWith(beginStrTemp.trim())){
                    //    if(this.hasBegin && !this.hasEnd){
                            this.hasBegin = false;
                            this.hasEnd = true;
//                               System.out.println("curLineBB ="+curLine);
//                               System.out.println("beginStrBB ="+beginStr);
                            return false; // 其余的信息部分了  不能再继续读取了
                        }

                    }

                }
//                System.out.println("curLineCC ="+curLine);
//                System.out.println("beginStrCC ="+beginStr);
//                System.out.println("beginStrCC ="+beginStr);
            }
            return true;  // 当前的语句没有 检测到  起始标记  那么  就继续读取
        }

        @Override
        String tryAnalysisLine(String oriLine) {

            for (int i = 0; i <keyList.size() ; i++) {
                String curKey = keyList.get(i);
                if(oriLine.contains(curKey)){ // 取出 value
                    String value = getValueFromOriLine(oriLine,curKey);
                }
            }
            return null;
        }

    }

    class WifiSummary1 extends WifiCommon{
        WifiSummary1(String beginStr , String title){
            super(beginStr,title);
        }

        @Override
        void initKeyList() {
        this.keyList.add("Wi-Fi is enabled");
        this.keyList.add("Wi-Fi is disabled");
        this.keyList.add("Verbose logging is on");
        this.keyList.add("Verbose logging is off");
        this.keyList.add("Stay-awake conditions:");
        this.keyList.add("mInIdleMode");
        this.keyList.add("mScanPending");



        }

        @Override
        String getValueFromOriLine(String curLine,String key) {
         //   public   Map<String,ArrayList<WIFI_DUMP_ITEM>> curDataMap;
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("Wi-Fi is enabled")){
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,"on","WIFI开启");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("Wi-Fi is disabled")){
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,"off","WIFI关闭");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("Verbose logging is on")){
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,"on","WIFI详情开关开启");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("Verbose logging is off")){
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,"off","WIFI详情开关关闭");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("Stay-awake conditions:")){
                String value = curLine.substring(curLine.indexOf("Stay-awake conditions:")+"Stay-awake conditions:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mInIdleMode")){
                String value = curLine.substring(curLine.indexOf("mInIdleMode")+"mInIdleMode".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mScanPending")){
                String value = curLine.substring(curLine.indexOf("mScanPending")+"mScanPending".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }

            return null;
        }

    }

    class WifiControllerLog extends WifiCommon {
        WifiControllerLog(String beginStr, String title) {
            super(beginStr, title);
        }

        @Override
        void initKeyList() {
            keyList.add("curState=");
            keyList.add("mPersistWifiState");
            keyList.add("mAirplaneModeOn");
            keyList.add("mEnableTrafficStatsPoll");
            keyList.add("mTrafficStatsPollToken");
            keyList.add("mTxPkts");
            keyList.add("mRxPkts");
            keyList.add("mDataActivity");
            this.keyList.add("mMulticastEnabled");
            this.keyList.add("mMulticastDisabled");
        }

        @Override
        String getValueFromOriLine(String curLine, String key) {
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("curState=")){
                String value = curLine.substring(curLine.indexOf("curState=")+"curState=".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WifiController的当前状态");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mPersistWifiState")){
                String value = curLine.substring(curLine.indexOf("mPersistWifiState")+"mPersistWifiState".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mAirplaneModeOn")){
                String value = curLine.substring(curLine.indexOf("mAirplaneModeOn")+"mAirplaneModeOn".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"飞行模式");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mEnableTrafficStatsPoll")){
                String value = curLine.substring(curLine.indexOf("mEnableTrafficStatsPoll")+"mEnableTrafficStatsPoll".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mTrafficStatsPollToken")){
                String value = curLine.substring(curLine.indexOf("mTrafficStatsPollToken")+"mTrafficStatsPollToken".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mTxPkts")){
                String value = curLine.substring(curLine.indexOf("mTxPkts")+"mTxPkts".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"发送到的package");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mRxPkts")){
                String value = curLine.substring(curLine.indexOf("mRxPkts")+"mRxPkts".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"接收到的package");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mDataActivity")){
                String value = curLine.substring(curLine.indexOf("mDataActivity")+"mDataActivity".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mMulticastEnabled")){
                String value = curLine.substring(curLine.indexOf("mMulticastEnabled")+"mMulticastEnabled".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mMulticastDisabled")){
                String value = curLine.substring(curLine.indexOf("mMulticastDisabled")+"mMulticastDisabled".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }


            return null;
        }
    }

    class WifiStateMachinePrimeLog extends WifiCommon {
        WifiStateMachinePrimeLog(String beginStr, String title) {
            super(beginStr, title);
        }

        @Override
        void initKeyList() {
            keyList.add("Current wifi mode:");
            keyList.add("NumActiveModeManagers:");
        }

        @Override
        String getValueFromOriLine(String curLine, String key) {
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("Current wifi mode:")){
                String value = curLine.substring(curLine.indexOf("Current wifi mode:")+"Current wifi mode:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WifiStateMachinePrime的模式");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("NumActiveModeManagers:")){
                String value = curLine.substring(curLine.indexOf("NumActiveModeManagers:")+"NumActiveModeManagers:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }
            return null;
        }
    }


    class WifiStateMachineLog extends WifiCommon {
        WifiStateMachineLog(String beginStr, String title) {
            super(beginStr, title);
        }

        @Override
        void initKeyList() {
            keyList.add("curState=");
        }

        @Override
        String getValueFromOriLine(String curLine, String key) {
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("curState=")){
                String value = curLine.substring(curLine.indexOf("curState=")+"curState=".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WifiStateMachine的状态");
                wifi_Dump_Arr.add(curArr);
            }
            return null;
        }
    }

    class SupplicantStateTrackerLog extends WifiCommon {
        SupplicantStateTrackerLog(String beginStr, String title) {
            super(beginStr, title);
        }

        @Override
        void initKeyList() {
            keyList.add("curState=");
            keyList.add("mAuthFailureReason");
            keyList.add("mNetworksDisabledDuringConnect");
            keyList.add("mWifiInfo");
            keyList.add("mNetworkInfo");
            keyList.add("mLastSignalLevel");
            keyList.add("mLastBssid");
            keyList.add("mLastNetworkId");
            keyList.add("mOperationalMode");
            keyList.add("mUserWantsSuspendOpt");
            keyList.add("mSuspendOptNeedsDisabled");
            keyList.add("CountryCode sent to driver:");
            keyList.add("mConnectionReqCount");
            keyList.add("mUntrustedReqCount");
            keyList.add("Wlan Wake Reasons:");
        }

        @Override
        String getValueFromOriLine(String curLine, String key) {
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("curState=")){
                String value = curLine.substring(curLine.indexOf("curState=")+"curState=".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"SupplicantState的状态");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mAuthFailureReason")){
                String value = curLine.substring(curLine.indexOf("mAuthFailureReason")+"mAuthFailureReason".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"认证失败原因");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mNetworksDisabledDuringConnect")){
                String value = curLine.substring(curLine.indexOf("mNetworksDisabledDuringConnect")+"mNetworksDisabledDuringConnect".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"是否突然不可用");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mWifiInfo")){
                String value = curLine.substring(curLine.indexOf("mWifiInfo")+"mWifiInfo".length()).trim();
                //mWifiInfo SSID: D-Link_DIR-816_5G, BSSID: 1c:5f:2b:5e:d5:54, MAC: 90:73:5a:c2:91:1e, Supplicant state: COMPLETED, RSSI: -46, Link speed: 292Mbps, Frequency: 5745MHz, Net ID: 2, Metered hint: false, score: 60
             ArrayList<String> curValueList = null;
                if(value.length() > MAX_COUNT_CHAR_IN_ROW -30){
                    curValueList = new  ArrayList<String>();
                  String[] valueStrArr =   value.split(",");
                  if(valueStrArr != null){
                      for (int i = 0; i < valueStrArr.length; i++) {
                          curValueList.add(valueStrArr[i].trim());
                      }
                  }
                }
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WIFI-Info信息",curValueList);
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mNetworkInfo")){
                String value = curLine.substring(curLine.indexOf("mNetworkInfo")+"mNetworkInfo".length()).trim();
                //mNetworkInfo [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]
                ArrayList<String> curValueList = null;
                if(value.length() > MAX_COUNT_CHAR_IN_ROW -30){
                    curValueList = new  ArrayList<String>();
                    String[] valueStrArr =   value.split(",");
                    if(valueStrArr != null){
                        for (int i = 0; i < valueStrArr.length; i++) {
                            curValueList.add(valueStrArr[i].trim());
                        }
                    }
                }
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"NetworkInfo信息",curValueList);
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mLastSignalLevel")){
                String value = curLine.substring(curLine.indexOf("mLastSignalLevel")+"mLastSignalLevel".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"信号强度");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mLastBssid")){
                String value = curLine.substring(curLine.indexOf("mLastBssid")+"mLastBssid".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"最后连接网络的BSSID");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mLastNetworkId")){
                String value = curLine.substring(curLine.indexOf("mLastNetworkId")+"mLastNetworkId".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"最后连接网络的netid");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mOperationalMode")){
                String value = curLine.substring(curLine.indexOf("mOperationalMode")+"mOperationalMode".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mUserWantsSuspendOpt")){
                String value = curLine.substring(curLine.indexOf("mUserWantsSuspendOpt")+"mUserWantsSuspendOpt".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mSuspendOptNeedsDisabled")){
                String value = curLine.substring(curLine.indexOf("mSuspendOptNeedsDisabled")+"mSuspendOptNeedsDisabled".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("CountryCode sent to driver:")){
                String value = curLine.substring(curLine.indexOf("CountryCode sent to driver:")+"CountryCode sent to driver:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"设置到驱动的国家码");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mConnectionReqCount")){
                String value = curLine.substring(curLine.indexOf("mConnectionReqCount")+"mConnectionReqCount".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"连接请求次数");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("mUntrustedReqCount")){
                String value = curLine.substring(curLine.indexOf("mUntrustedReqCount")+"mUntrustedReqCount".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"不清楚");
                wifi_Dump_Arr.add(curArr);
            }else if(curLine.contains("Wlan Wake Reasons:")){


                String value = curLine.substring(curLine.indexOf("Wlan Wake Reasons:")+"Wlan Wake Reasons:".length()).trim();
          // Wlan Wake Reasons: totalCmdEventWake 0 totalDriverFwLocalWake 0 totalRxDataWake 0 rxUnicast 0 rxMulticast 0 rxBroadcast 0 icmp 0 icmp6 0 icmp6Ra 0 icmp6Na 0 icmp6Ns 0 ipv4RxMulticast 0 ipv6Multicast 0 otherRxMulticast 127

                ArrayList<String> curValueList = null;
                if(value.length() > MAX_COUNT_CHAR_IN_ROW -30){
                    curValueList = new  ArrayList<String>();
                    String[] valueStrArr =   value.split(" ");
                    if(valueStrArr != null){
                        for (int i = 0; i < valueStrArr.length; i++) {
                            if(isNumeric(valueStrArr[i])){
                                continue;
                            }
                            int nextIndex = i +1;
                            if(nextIndex >= valueStrArr.length){
                                continue;
                            }
                            curValueList.add(valueStrArr[i].trim()+":"+valueStrArr[nextIndex]);
                        }
                    }
                }
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"Wlan唤醒原因",curValueList);
                wifi_Dump_Arr.add(curArr);

            }




            return null;
        }
    }


    class ChipInfoLog extends WifiCommon {
        ChipInfoLog(String beginStr, String title) {
            super(beginStr, title);
        }

        @Override
        void initKeyList() {
            keyList.add("FW Version is:");
            keyList.add("Driver Version is:");
            keyList.add("Supported Feature set:");
        }

        @Override
        String getValueFromOriLine(String curLine, String key) {
            ArrayList<WIFI_DUMP_ITEM>  wifi_Dump_Arr =  curDataMap.get(key);
            if(curLine.contains("FW Version is:")){
                String value = curLine.substring(curLine.indexOf("FW Version is:")+"FW Version is:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WIFI固件版本");
                wifi_Dump_Arr.add(curArr);
            }else  if(curLine.contains("Driver Version is:")){
                String value = curLine.substring(curLine.indexOf("Driver Version is:")+"Driver Version is:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"WIFI驱动版本");
                wifi_Dump_Arr.add(curArr);
            }else  if(curLine.contains("Supported Feature set:")){
                String value = curLine.substring(curLine.indexOf("Supported Feature set:")+"Supported Feature set:".length()).trim();
                WIFI_DUMP_ITEM curArr = new WIFI_DUMP_ITEM(curLine,key,value,"功能bit值");
                wifi_Dump_Arr.add(curArr);
            }

            return null;
        }
    }

    public static void addDumpWifiBase() {
        Wifi_Dump_Base wifi_part_1 = new E6_Android_Info().new WifiSummary1("Wi-Fi is", "【WIFI状态】");
        Wifi_Dump_Base wifi_WifiControllerLog = new E6_Android_Info().new WifiControllerLog("WifiController:", "【WifiController信息】");
        Wifi_Dump_Base wifi_WifiStateMachinePrimeLog = new E6_Android_Info().new    WifiStateMachinePrimeLog("Dump of WifiStateMachinePrime", "【WifiStateMachinePrime信息】");

        Wifi_Dump_Base wifi_WifiStateMachineLog = new E6_Android_Info().new WifiStateMachineLog("WifiStateMachine:", "【WifiStateMachine信息】");

        Wifi_Dump_Base wifi_SupplicantStateTrackerLog = new E6_Android_Info().new SupplicantStateTrackerLog(   "SupplicantStateTracker:", "【SupplicantStateTracker信息】");

        Wifi_Dump_Base wifi_ChipInfoLog = new E6_Android_Info().new    ChipInfoLog(   "Chipset information :", "【WIFI芯片版本信息】");

        allBase_Static.add(wifi_part_1);
        allBase_Static.add(wifi_WifiControllerLog);
        allBase_Static.add(wifi_WifiStateMachinePrimeLog);
        allBase_Static.add(wifi_WifiStateMachineLog);
        allBase_Static.add(wifi_SupplicantStateTrackerLog);
        allBase_Static.add(wifi_ChipInfoLog);
    }

    public static void main(String[] args) {
        try {

            getAndroid_PropInfo();
            System.out.println("----------------------------------");
            getCpuInfo();
            System.out.println("----------------------------------");
            getScreenInfo();
            System.out.println("----------------------------------");
            getMemeryInfo();
            System.out.println("----------------------------------");

            System.out.println("----------------------------------");
            getBatteryInfo();
            System.out.println("----------------------------------");
            getWifiNetworkInfo();
            System.out.println("----------------------------------");
            getWifiDFSChannelInfo();
            System.out.println("----------------------------------");
            getWifiScanInfo();
            System.out.println("----------------------------------");
            getDumpWifiInfo();
            System.out.println("----------------------------------");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



    public static class PropItem{
        String mProName;
        String mValue;
        String mCurOriLineStr;
        String mDesc;
        String mDateString;
        PropItem(String proName , String desc){
            this.mProName = proName;
            this.mDesc = desc;
            this.mValue="";
            this.mCurOriLineStr="";
            this.mDateString="";
        }

      void  initWithOriLine(String oriLine){
            this.mCurOriLineStr = oriLine;
            String temp =  oriLine.substring(oriLine.lastIndexOf("[")+1).trim();
            while(temp.endsWith("]")){
                temp = temp.substring(0,temp.length()-1);
            }
            this.mValue = temp;
            if(!"".equals(mValue) && isNumeric(this.mValue) && Long.parseLong(mValue) > 1000000000L){
                if(mValue.length() == 13){   // 毫秒为单位
                    this.mDateString = df.format(new Date(Long.parseLong(mValue)));
                }else if(mValue.length() == 10){  // 秒为单位
                    this.mDateString = df.format(new Date(Long.parseLong(mValue) * 1000));
                }

            }
        //  System.out.println("XXXXXXXXXXXXx this.mValue = "+ this.mValue);
        }
        @Override
        public String toString() {

            if("".equals(mCurOriLineStr.trim())){
                return "";  // 无该项
            }
            if("".equals(mValue.trim())){
                return mProName+":"+ ("".equals(mDesc)?"":"空【"+mDesc+"】");
            }
            return mProName+":"+mValue+"   "+mDateString+ ("".equals(mDesc)?"":"【"+mDesc+"】");
        }
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }



    public static void getScreenInfo(){
        ArrayList<String> screenInfoDetail = new     ArrayList<String>();
        String size =  readStringFromFile(sizeFile);
        String density =  readStringFromFile(densityFile);
        screenInfoDetail.add(size);
        screenInfoDetail.add(density);
        ArrayPrint(screenInfoDetail,"screen情况");

    }

  static  ArrayList<Wifi_Dump_Base> allBase_Static = new ArrayList<Wifi_Dump_Base>();




    public static void getDumpWifiInfo() {
          addDumpWifiBase();

        if(checkFileExist(iw_phy0_reg_File)){
            ArrayList<String> dumpWifiInfoDetail = readDumpWifiInfoFromFile(dumpsys_wifi_File); // detail
            tryCheckWifiDumpBase(dumpWifiInfoDetail,allBase_Static);
           // ArrayPrint(dumpWifiInfoDetail,"WIFI信道信息");
        }

        ArrayList<String> wifiPrintInfo = tryGetInfoFromCommonBase(allBase_Static);
        ArrayPrint(wifiPrintInfo,"WIFI-Dump信息");
    }

    public static  ArrayList<String>  tryGetInfoFromCommonBase(ArrayList<Wifi_Dump_Base> wifiBaseArr ) {
        ArrayList<String> dumpWifiInfoList = new  ArrayList<String>();
        for (int i = 0; i < wifiBaseArr.size(); i++) { // 块
            Wifi_Dump_Base curBase = wifiBaseArr.get(i);
            String blockTitle = curBase.blockTitle;
            dumpWifiInfoList.add("区块信息标识:"+blockTitle);
            for (int j = 0; j < curBase.curDataMap.size(); j++) {  // 属性集合
                String key = curBase.keyList.get(j);
              ArrayList< E6_Android_Info.WIFI_DUMP_ITEM> curWIFIItem  = curBase.curDataMap.get(key); // 属性的值
                for (int k = 0; k < curWIFIItem.size(); k++) {
                    E6_Android_Info.WIFI_DUMP_ITEM wifiItem = curWIFIItem.get(k);
                    String mValue = wifiItem.mItemValue;
                    String mKey = wifiItem.mItemKey;
                    String mDesc =wifiItem.mDesc;
                    String mPath = wifiItem.mPath;
                    ArrayList<String> mCurrentValueList = wifiItem.mItemValueList;
                    if(mCurrentValueList == null){
                        String curKey = mKey+"";
                        curKey = curKey.replace(":","");  // 去掉:
                        dumpWifiInfoList.add("当前属性:"+mKey+"  【"+mValue+"】"+"  "+mDesc+"   "+(mPath == null ?"":mPath));
                    }else{
                        dumpWifiInfoList.add("当前属性:"+mKey+"  【"+mDesc+"】   "+(mPath == null ?"":mPath));
                        for (int g = 0; g < mCurrentValueList.size(); g++) {
                            String tempProp = mCurrentValueList.get(g).trim();
                            while(tempProp.startsWith("[")){
                                tempProp =tempProp.substring(1);
                            }
                            while(tempProp.endsWith("]") && tempProp.indexOf("[") < 0 ){
                                tempProp =tempProp.substring(0,tempProp.length()-1);
                            }

                            dumpWifiInfoList.add("属性值Item:"+tempProp);
                        }
                    }

                }

            }
            dumpWifiInfoList.add("===============");


        }
        return dumpWifiInfoList;
    }

    public static void tryCheckWifiDumpBase(ArrayList<String> logFileStrArr ,ArrayList<Wifi_Dump_Base> wifiBaseArr ) {
        for (int i = 0; i < wifiBaseArr.size(); i++) {
            Wifi_Dump_Base curDumpBase = wifiBaseArr.get(i);
            for (int j = 0; j < logFileStrArr.size(); j++) {
                String curLine = logFileStrArr.get(j);
                if(curDumpBase.isBeginForLine(curLine) && curDumpBase.hasBegin && !curDumpBase.hasEnd){
                    curDumpBase.tryAnalysisLine(curLine);
                }
            }
        }

    }


    public static void getWifiScanInfo() {
        MAX_COUNT_CHAR_IN_ROW = 132;
        if(checkFileExist(dumpsys_wifiscanner_File)){
            ArrayList<String> channelInfoDetail = readScanInfo(dumpsys_wifiscanner_File); // detail
            ArrayPrint(channelInfoDetail,"WIFI扫描信息");
        }
        MAX_COUNT_CHAR_IN_ROW =MAX_COUNT_CHAR_IN_ROW_DEFAULT;
    }

    public static void getWifiDFSChannelInfo() {
        if(checkFileExist(iw_phy0_reg_File)){
            ArrayList<String> channelInfoDetail = readChannelInfoFromFile(iw_phy0_reg_File); // detail
            ArrayPrint(channelInfoDetail,"WIFI信道");
        }
    }

    public static void getWifiNetworkInfo(){
        if(checkFileExist(wifiConfigStorehFile)){
            ArrayList<WifiItem> wifiInfoDetail = readWifiNetworkInfoFromFile(wifiConfigStorehFile); // detail
            ArrayList<String> wifiInfoStrList = new  ArrayList<String>();
            for (int i = 0; i < wifiInfoDetail.size(); i++) {
                int index = i + 1;
                WifiItem wifiItem = wifiInfoDetail.get(i);
                wifiInfoStrList.add("WIFI索引:"+index);
                wifiInfoStrList.add("WIFI名称:"+wifiItem.getName());
                wifiInfoStrList.add("WIFI密码:"+wifiItem.getKey());
                wifiInfoStrList.add("===========");
            }

            ArrayPrint(wifiInfoStrList,"wifi网络情况");

        }
    }

    public static boolean checkFileExist(File curFile){
        if(!curFile.exists()){
            System.out.println(" 当前文件不存在:"+curFile.getAbsolutePath());
            return false;
        }
        return true;
    }

    public static void getBatteryInfo(){
        ArrayList<String> batteryInfoDetail = readBatteryInfoFromFile(batteryFile); // detail

        ArrayPrint(batteryInfoDetail,"battery情况");

    }


    public static void getMemeryInfo(){
       ArrayList<String> memeryInfoDetail = readMemeryInfoFromFile(meminfoFile); // detail

        ArrayPrint(memeryInfoDetail,"memery情况");

    }

    public static void getCpuInfo(){
       // ArrayList<String> cpuInfoDetail = readCpuInfoFromFile(cpuinfoFile); // detail
        ArrayList<String> cpuInfoSimple = readCpuInfoFromFileSimple(cpuinfoFile); // simple
        ArrayPrint(cpuInfoSimple,"cpu简况");
    }

    static   ArrayList<String>  readCpuInfoFromFileSimple(File fileItem) {
        ArrayList<String> cpuInfoList = new  ArrayList<String>();
        String cpuInfoStr = readStringFromFile(cpuinfoFile);

        if(cpuInfoStr.contains("CPU architecture")) {
            String[] cpuArr =  cpuInfoStr.split("CPU architecture");
            if(cpuArr != null){
                cpuInfoList.add("CPU数量:"+(cpuArr.length-1));
            }

        }

        if(cpuInfoStr.contains("Hardware")){
            String lineContent = cpuInfoStr.substring(cpuInfoStr.lastIndexOf("Hardware"));
            while(lineContent != null && lineContent.contains(" :") || lineContent.contains("\t:")){
                lineContent = lineContent.replace(": ",":");
                lineContent = lineContent.replace(" :",":");
                lineContent = lineContent.replace("\t:",":");
            }
            cpuInfoList.add(lineContent);
        }


        return cpuInfoList;
    }


    static class WifiItem{
        String name;
        String key;
        WifiItem(){

        }
        WifiItem(String name ,String key){
            this.name = name;
            this.key = key;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public String getKey() {
            return key;
        }
        public String getName() {
            return name;
        }
    }

    static   ArrayList<WifiItem>  readWifiNetworkInfoFromFile(File fileItem) {
        ArrayList<WifiItem> wifiNetworkInfoList = new  ArrayList<WifiItem>();
        String word1 = "\"SSID\"";   // 接口 WLAN 上的配置文件 debugtheworld:
        String word2 = "name=\"PreSharedKey\"";    // 关键内容            : 12345678

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";

            String wifiName = "";
            String wifiKey = "";
            boolean wifiNameReadyFlag = false;
            boolean wifiKeyReadyFlag = false;

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }

                if(lineContent.contains(word1)){
                    // <string name="SSID">&quot;mix3&quot;</string>
                    // mix3&quot;</string>
                    String wifiNameTemp = new String(lineContent);
                    wifiNameTemp = wifiNameTemp.substring(wifiNameTemp.indexOf(";")+1).trim();
                   // System.out.println("lastIndexA="+wifiNameTemp.lastIndexOf("&quot;"));
                    wifiName = wifiNameTemp.substring(0,wifiNameTemp.lastIndexOf("&quot;")); // mix3
                    wifiName = wifiName.trim().replace("&amp;","&");
                    wifiNameReadyFlag = true;
                    wifiKeyReadyFlag = false;
                    wifiKey="";
                }


                if(lineContent.contains(word2)){
//<null name="PreSharedKey" />
//<string name="PreSharedKey">&quot;88888888&quot;</string>
//<string name="PreSharedKey">&quot;00000000&quot;</string>

                    String wifiKeyTemp = new String(lineContent).trim();
                    if(wifiKeyTemp.startsWith("<null")){
                        wifiKey = "无密码(开放网络)";
                    }else{
                  //      System.out.println("wifiKeyTemp="+wifiKeyTemp);

                        wifiKeyTemp = wifiKeyTemp.substring(wifiKeyTemp.indexOf(";")+1).trim();
                      //  System.out.println("lastIndexB="+wifiKeyTemp.lastIndexOf("&quot;"));
                        wifiKey = wifiKeyTemp.substring(0,wifiKeyTemp.lastIndexOf("&quot;")); // mix3
                    }
                    wifiKey = wifiKey.trim().replace("&amp;","&");
                    if(wifiNameReadyFlag){
                        wifiKeyReadyFlag = true;
                    }
                    if(wifiNameReadyFlag && wifiNameReadyFlag){
                        WifiItem  wifiItem = new WifiItem(wifiName , wifiKey);
                        wifiNetworkInfoList.add(wifiItem);
                        wifiNameReadyFlag = false;
                        wifiKeyReadyFlag = false;
                    }
                }
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println("Wifi Netowrk 异常:"+e.fillInStackTrace());
            e.printStackTrace();
        }
        return wifiNetworkInfoList;


    }


    static   ArrayList<String>  readBatteryInfoFromFile(File fileItem) {
        ArrayList<String> batteryInfoList = new  ArrayList<String>();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
if(lineContent.contains("Current Battery Service state")){
    continue;
}
                while(lineContent.contains(" :") || lineContent.contains(": ") || lineContent.contains("\t:")){
                    lineContent = lineContent.replace(": ",":");
                    lineContent = lineContent.replace(" :",":");
                    lineContent = lineContent.replace("\t:",":");
                }
                //      System.out.println("Mem-lineContentA="+lineContent);
if(lineContent.contains("voltage")){
    lineContent = lineContent + " 【电压_单位微伏(uV)】";
}else if(lineContent.contains("temperature")){
    lineContent = lineContent + " 【温度_单位0.1摄氏度】";
}else  if(lineContent.contains("Max charging current")){
    lineContent = lineContent + " 【最大充电电流_单位微安(uA)】";

}else  if(lineContent.contains("Max charging voltage")){
    lineContent = lineContent + " 【最大充电电压_单位微伏(uV)】";
}else  if(lineContent.contains("status")){
    lineContent = lineContent + " 【充电状态_UNKNOWN=1_CHARGING=2_DISCHARGING=3_NOT_CHARGING=4_FULL=5】";
}




                //     System.out.println("Mem-lineContentB="+lineContent);
                batteryInfoList.add(lineContent);
                //   sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println("Battery 异常:"+e.toString());
        }
        return batteryInfoList;


    }

    static   ArrayList<String>  readMemeryInfoFromFile(File fileItem) {
        ArrayList<String> memryInfoList = new  ArrayList<String>();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }

                while(lineContent.contains(" :") || lineContent.contains(": ") || lineContent.contains("\t:")){
                    lineContent = lineContent.replace(": ",":");
                    lineContent = lineContent.replace(" :",":");
                    lineContent = lineContent.replace("\t:",":");
                }
          //      System.out.println("Mem-lineContentA="+lineContent);

                if(lineContent.contains("kB")){
                    String kBStr = lineContent.substring(lineContent.lastIndexOf(":")+1).trim();
                    kBStr = kBStr.replace("kB","").trim();
                    double kBdoube = Double.valueOf(kBStr);
                    if(kBdoube < 0){
                        continue;
                    }

                    //         MemLogList.add("当前内存剩余量:" + Double.parseDouble(nf.format((Double) (mem.getFree() / (1024d * 1024d * 1024d)))) + "GB"); // 当前内存使用量

                    String mGBinfo = "【"+Double.parseDouble(nf.format((Double) (kBdoube / (1024d * 1024d )))) + "GB"+"】";
                    lineContent = lineContent+"  "+mGBinfo;
                }

           //     System.out.println("Mem-lineContentB="+lineContent);
                memryInfoList.add(lineContent);
                //   sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println("Memery 异常:"+e.toString());
        }
        return memryInfoList;


    }



    static   ArrayList<String>  readDumpWifiInfoFromFile(File fileItem) {
        ArrayList<String> fileStrArr =     readArrStringFromFile(fileItem);
        ArrayList<String> fixedStrArr = new  ArrayList<String>();
        for (int i = 0; i < fileStrArr.size(); i++) {
            fixedStrArr.add(fileStrArr.get(i));
        }
        return fixedStrArr;
    }

    static   ArrayList<String>  readChannelInfoFromFile(File fileItem) {
        ArrayList<String> fileStrArr =     readArrStringFromFile(fileItem);
        ArrayList<String> fixedStrArr = new  ArrayList<String>();
        for (int i = 0; i < fileStrArr.size(); i++) {
            fixedStrArr.add("信道信息:"+fileStrArr.get(i));
        }
               return fixedStrArr;
    }


    static   ArrayList<String>  readScanInfo(File fileItem) {
        ArrayList<String> fileStrArr =     readScanInfoFromFile(fileItem);
        ArrayList<String> fixedStrArr = new  ArrayList<String>();
        for (int i = 0; i < fileStrArr.size(); i++) {
            fixedStrArr.add("扫描:"+fileStrArr.get(i));
        }
        return fixedStrArr;
    }

    static   ArrayList<String>  readScanInfoFromFile(File fileItem) {
        //  StringBuilder sb = new StringBuilder();
        ArrayList<String> scanResultList = new  ArrayList<String>();
        boolean isBegin = false;
        String beginStr = "Latest scan results:";
        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }

                if( lineContent.trim().equals(beginStr)){
                    isBegin = true;
                }
                if(isBegin){
                    String value = lineContent.trim().replace("    "," ");
                    scanResultList.add(value);
                }

                //   sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return scanResultList;

    }
    static   ArrayList<String>  readCpuInfoFromFile(File fileItem) {
      //  StringBuilder sb = new StringBuilder();
        ArrayList<String> cpuInfoList = new  ArrayList<String>();
        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                lineContent = lineContent.replace(": ",":");
                while(lineContent.contains(" :") || lineContent.contains("\t:")){
                    lineContent = lineContent.replace(" :",":");
                    lineContent = lineContent.replace("\t:",":");
                }

                cpuInfoList.add(lineContent);
                if(lineContent.contains("CPU revision")){
                    cpuInfoList.add("==============");
                }
            //   sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return cpuInfoList;

    }


    public static void getAndroid_PropInfo(){

        ArrayList<PropItem> mListPropItem = new ArrayList<PropItem>();
        mListPropItem.add(new PropItem("ro.product.brand","") ); //  [motorola]
        mListPropItem.add(new PropItem("ro.boot.hardware","") ); //  [mt6771]
        mListPropItem.add(new PropItem("ro.hardware","") ); //  [mt6771]
        mListPropItem.add(new PropItem("ro.mediatek.platform","") ); //  [MT6771]
        mListPropItem.add(new PropItem("ro.boot.hardware.revision","") ); //  [DVT1B]
        mListPropItem.add(new PropItem("ro.revision","硬件类型") ); //  [DVT1B]  硬件类型
        mListPropItem.add(new PropItem("ro.hardware.sensors","") ); //  [lima]
        mListPropItem.add(new PropItem("ro.boot.device","") ); //  [lima]
        mListPropItem.add(new PropItem("ro.product.board","") ); //  [lima]
        mListPropItem.add(new PropItem("ro.product.name","") ); //  [lima]
        mListPropItem.add(new PropItem("persist.vendor.connsys.chipid","CPU标号") ); //  [0x6771]
        mListPropItem.add(new PropItem("ro.serialno","手机串号") ); //  [NJIR1C0061]  手机串号
        mListPropItem.add(new PropItem("ro.boot.serialno","手机串号") ); //  [NJIR1C0061] 手机串号
        mListPropItem.add(new PropItem("ro.product.manufacturer","制造商") ); //  [motorola] 制造商
        mListPropItem.add(new PropItem("ro.product.model","型号") ); //  [moto g(7) play] 型号
        mListPropItem.add(new PropItem("ro.boot.wifimacaddr","wifi地址") ); //  [BC:98:DF:42:FA:E4,BC:98:DF:42:FA:E5] wifi地址
        mListPropItem.add(new PropItem("ro.boot.btmacaddr","蓝牙地址") ); //  [BC:98:DF:42:FA:E3]  蓝牙地址
        mListPropItem.add(new PropItem("ro.bootimage.build.fingerprint","版本标识") ); //  [motorola/lima_retail/lima:9/PMD29.45/2db6c:userdebug/intcfg,test-keys]
        mListPropItem.add(new PropItem("gsm.version.baseband","基带版本") ); //  [MT6771_V65.01.01.45R]  基带版本
        mListPropItem.add(new PropItem("ro.product.locale","语言") ); //  [en-US]   语言
        mListPropItem.add(new PropItem("net.bt.name","蓝牙名称") ); //  [Android]  蓝牙名称
        mListPropItem.add(new PropItem("net.dns1","") ); //  [192.168.0.1]
        mListPropItem.add(new PropItem("ro.runtime.firstboot","开机时间") ); //  [1568020360734]  开机时间
        mListPropItem.add(new PropItem("persist.sys.timezone","时区") ); //  [Asia/Shanghai]  时区
        mListPropItem.add(new PropItem("ro.wifi.channels","wifi信道") ); //  []
        mListPropItem.add(new PropItem("ro.bootimage.build.date.utc","版本编译时间") ); //  [1561209471  【2019/6/22 21:17:51】]  版本编译时间
        mListPropItem.add(new PropItem("ro.bootloader","Modem版本") ); //  [MBM-2.1-lima_retail-231730c-190622] Modem 调制版本
        mListPropItem.add(new PropItem("ro.build.description","版本信息") ); //  [lima-userdebug 9 PMD29.45 2db6c intcfg,test-keys] 版本信息
        mListPropItem.add(new PropItem("ro.build.flavor","版本类型") ); //  [lima-userdebug] 版本类型
        mListPropItem.add(new PropItem("ro.build.id","版本号") ); //  [PMD29.45] 版本号
        mListPropItem.add(new PropItem("ro.build.version.sdk","当前系统SDK") ); //  [28] 当前系统SDK数值
        mListPropItem.add(new PropItem("ro.build.version.min_supported_target_sdk","支持最小SDK") ); //  [17] 最小支持的SDK数值
        mListPropItem.add(new PropItem("ro.build.version.release","当前安卓版本") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.build.user","编译人员") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.build.version.full","全局版本号") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.build.version.incremental","编译版本段") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.config.wallpaper","壁纸路径") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.build.type","版本类型") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.build.version.qcom","高通芯片版本") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.hw.boardversion","高通硬件类型B") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.hw.revision","高通硬件类型A") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.hw.ram","运行内存") ); //  [9]    安卓版本
        mListPropItem.add(new PropItem("ro.vendor.hw.storage","存储空间") );
        mListPropItem.add(new PropItem("vendor.bluetooth.soc","蓝牙soc") );
        mListPropItem.add(new PropItem("vendor.display.lcd_density","像素密度PPI") );

        toGetPropInfo(propFile ,mListPropItem );
        printArrObject(mListPropItem.toArray(),"prop属性");
    }


    static  public void printArrObject(Object[] objArr,String title){
        ArrayList<String> curPropStrArr = new  ArrayList<String>();
        for (int i = 0; i < objArr.length; i++) {
            if("".equals(objArr[i].toString())){
                continue;
            }
            curPropStrArr.add(objArr[i].toString());
        }
       ArrayPrint(curPropStrArr , title);
    }

    public static void toGetPropInfo(File logFile , ArrayList<PropItem> propList){
        if(!logFile.exists()){
            System.out.println("无法读取不存在文件:"+ logFile.getAbsolutePath());
        }

        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
           //     System.out.println("lineContent ="+ lineContent);
                for (int i = 0; i < propList.size(); i++) {
                    PropItem  propItem = propList.get(i);
                    if(lineContent.contains(propItem.mProName)){
                        propItem.initWithOriLine(new String(lineContent));
                     //   break;
                    }
                }

            }
            curBR.close();
        } catch (Exception e) {
            System.out.println("读取文件 报异常 ！e ="+ e.toString());

        }



    }





    static  String readStringFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
         //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return sb.toString();
    }

    static  ArrayList<String> readArrStringFromFile(File fileItem) {
        ArrayList<String> fileStrArr = new  ArrayList<String>();
        try {
            //   BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem)));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                fileStrArr.add(lineContent.trim());
            }
            curBR.close();
        } catch (Exception e) {
        }
        return fileStrArr;
    }



    private static void addEnvironmentPATH(String value) {
        Properties props = System.getProperties();
        //  user.home = C:\Users\zhuzj5
        // String mE2_Dll_Path_Value = props.getProperty("user.home")+ File.separator+"Desktop"+File.separator+"zbin"+File.separator+"E2" ; // +File.separator+"sigar-amd64-winnt.dll";
        String curLibraryPath = props.getProperty("java.library.path");
        while (curLibraryPath.endsWith(";") || curLibraryPath.endsWith(".")) {
            curLibraryPath = curLibraryPath.substring(0, curLibraryPath.length() - 1);
        }
        String newLibraryPath = curLibraryPath + ";" + value + ";;.";
        props.setProperty("java.library.path", newLibraryPath);
        //System.out.println("加载库时搜索的路径列表B:" + props.getProperty("java.library.path"));
        //加载库时搜索的路径列表:  C:\Program Files\Java\jdk1.8.0_191\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\Program Files (x86)\Graphviz2.38\bin;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\RSA SecurID Token Common;C:\Program Files\RSA SecurID Token Common;C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\windows\system32;C:\windows;C:\windows\System32\Wbem;C:\windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\Git\cmd;C:\Program Files\MacType;C:\Program Files\Java\jdk1.6.0_45\bin;D:\software\apache-maven-3.2.5\bin;C:\Program Files\Java\jdk1.8.0_191\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\ProgramData\chocolatey\bin;C:\Program Files\osquery;C:\Program Files\Microsoft Network Monitor 3\;C:\Users\zhuzj5\AppData\Local\Programs\Python\Python37\Scripts\;C:\Users\zhuzj5\AppData\Local\Programs\Python\Python37\;C:\Users\zhuzj5\AppData\Local\Microsoft\WindowsApps;D:\fireware_stone\platform-tools;C:\Users\zhuzj5\AppData\Roaming\starrynote.cn\StarryNote\;D:\software\cmder171025\cmder;C:\Program Files (x86)\Graphviz2.38\bin;;.
    }

    private static void property() throws UnknownHostException {
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        ArrayList<String> propertyList = new ArrayList<String>();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        propertyList.add("用户名:" + userName);
        propertyList.add("计算机名:" + computerName);
        propertyList.add("计算机域名:" + userDomain);
        propertyList.add("本地ip地址:" + ip);
        propertyList.add("本地主机名:" + addr.getHostName());
        propertyList.add("JVM可以使用的总内存:" + r.totalMemory());
        propertyList.add("JVM可以使用的剩余内存:  " + r.freeMemory());
        propertyList.add("JVM可以使用的处理器个数:" + r.availableProcessors());
        propertyList.add("Java的运行环境版本:" + props.getProperty("java.version"));
        propertyList.add("Java的运行环境供应商:" + props.getProperty("java.vendor"));
        propertyList.add("Java供应商的URL:" + props.getProperty("java.vendor.url"));
        propertyList.add("Java的安装路径:" + props.getProperty("java.home"));
        propertyList.add("Java的虚拟机规范版本:" + props.getProperty("java.vm.specification.version"));
        propertyList.add("Java的虚拟机规范供应商:" + props.getProperty("java.vm.specification.vendor"));
        propertyList.add("Java的虚拟机规范名称:" + props.getProperty("java.vm.specification.name"));
        propertyList.add("Java的虚拟机实现版本:" + props.getProperty("java.vm.version"));
        propertyList.add("Java的虚拟机实现供应商:" + props.getProperty("java.vm.vendor"));
        propertyList.add("Java的虚拟机实现名称:" + props.getProperty("java.vm.name"));
        propertyList.add("Java运行时环境规范版本:" + props.getProperty("java.specification.version"));
        propertyList.add("Java运行时环境规范供应商:" + props.getProperty("java.specification.vender"));
        propertyList.add("Java运行时环境规范名称:" + props.getProperty("java.specification.name"));
        propertyList.add("Java的类格式版本号:" + props.getProperty("java.class.version"));
        propertyList.add("Java的类路径:" + props.getProperty("java.class.path"));
        propertyList.add("加载库时搜索的路径列表:" + props.getProperty("java.library.path"));
/*        System.out.println("用户名:  " + userName);
        System.out.println("计算机名:  " + computerName);
        System.out.println("计算机域名:  " + userDomain);
        System.out.println("本地ip地址:  " + ip);
        System.out.println("本地主机名:  " + addr.getHostName());
        System.out.println("JVM可以使用的总内存:  " + r.totalMemory());
        System.out.println("JVM可以使用的剩余内存:  " + r.freeMemory());
        System.out.println("JVM可以使用的处理器个数:  " + r.availableProcessors());
        System.out.println("Java的运行环境版本:  " + props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商:  " + props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL:  " + props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径:  " + props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本:  " + props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商:  " + props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称:  " + props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本:  " + props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商:  " + props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称:  " + props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本:  " + props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商:  " + props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称:  " + props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号:  " + props.getProperty("java.class.version"));
        System.out.println("Java的类路径:  " + props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表:  " + props.getProperty("java.library.path"));

        System.out.println("默认的临时文件路径:  " + props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径:  " + props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称:  " + props.getProperty("os.name"));
        System.out.println("操作系统的构架:  " + props.getProperty("os.arch"));
        System.out.println("操作系统的版本:  " + props.getProperty("os.version"));
        System.out.println("文件分隔符:  " + props.getProperty("file.separator"));
        System.out.println("路径分隔符:  " + props.getProperty("path.separator"));
        System.out.println("行分隔符:  " + props.getProperty("line.separator"));
        System.out.println("用户的账户名称:  " + props.getProperty("user.name"));
        System.out.println("用户的主目录:  " + props.getProperty("user.home"));
        System.out.println("用户的Desktop目录:  " + props.getProperty("user.desktop"));
        System.out.println("用户的当前工作目录:  " + props.getProperty("user.dir"));*/

        ArrayPrint(propertyList, "system-info");
    }


    public static String getPaddingEmptyString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "-";
        }
        return str;
    }

    // 加载库时搜索的路径列表AC-:\Program Files\Java\jdk1.8.0_191\bin
    // 加载库时搜索的路径列表A-:C\Program Files\Java\jdk1.8.0_191\bin
    public static String addMaoChinese(String oriStr) {
        String resultStr = "";
        int chinesePosition = getFirstChinesePosition(oriStr);
        resultStr = oriStr.substring(0, chinesePosition) + ":" + oriStr.substring(chinesePosition);
        return resultStr;
    }


    public static String addMaoBlank(String oriStr) {
        String resultStr = "";
        int blankPosition = oriStr.indexOf(" ");
        resultStr = oriStr.substring(0, blankPosition) + ":" + oriStr.substring(blankPosition);
        return resultStr;
    }

    public static ArrayList<String> CheckAndAddMaoMethod(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            if (isCommonMao(curItem)) {
                fixedArr.add(curItem);
            } else {
                // 对于那些没有冒号的  字段的处理
                //1.如果包含汉字 那么就在 汉字的最后添加一个冒号
                // 2. 如果字符串中有空格 并且第一个空格的位置小于 (MAX_COUNT_CHAR_IN_ROW/2) 那么把它换成冒号
                if (isContainChinese(curItem)) {
                    String addMaoStr = addMaoChinese(curItem);
                    fixedArr.add(addMaoStr);
                } else if (curItem.contains(" ") && curItem.indexOf(" ") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
                    String addMaoStr = addMaoBlank(curItem);
                    fixedArr.add(addMaoStr);
                } else {  // 如果以上条件都不满足   那么就在字符串最前面添加一个冒号

                    fixedArr.add(":" + curItem);

                }

            }

        }
        return fixedArr;
    }


    // 存在冒号 并且 冒号的位置小于 总的一行字数的一半长度  返回true
    public static boolean isCommonMao(String oriStr) {
        boolean flag = false;
        if (oriStr.contains(":") && oriStr.indexOf(":") == oriStr.lastIndexOf(":")) {
            flag = true;  // 只有一个冒号
        } else if (oriStr.contains(":") && oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.indexOf(":\\") && oriStr.indexOf(":") < (MAX_COUNT_CHAR_IN_ROW / 2)) {
            flag = true; // 多个冒号 并且  第一个冒号  :   在 :\ 之前
        } else if (oriStr.contains(":") && !oriStr.contains(":\\") && oriStr.indexOf(":") < oriStr.lastIndexOf(":")) {
            flag = true;   // 多个冒号
        }
        return flag;
    }

    public static ArrayList<String> firstFixedStringArr(ArrayList<String> mStrList) {
        ArrayList<String> fixedArr = new ArrayList<String>();
        int maxMaoPosition = getMaxMaoPosition(mStrList);
        for (int i = 0; i < mStrList.size(); i++) {
            String curItem = mStrList.get(i);
            int curMaoPosition = curItem.indexOf(":");
            String pre = curItem.substring(0, curMaoPosition);
            String end = curItem.substring(curMaoPosition); // 去掉:
            int preLength = getPaddingChineseLength(pre); // 中文对齐  取中文长度
            // 需要达到一样的ChineseLength
            // int padding_chinese =  getChineseCount(pre);
            String padding = "";
            if (preLength <= maxMaoPosition) {
                int paddingLength = maxMaoPosition - preLength;
                padding = getPaddingEmptyString(paddingLength);
            }
            String fixedItem = pre + padding + end;
            fixedArr.add(fixedItem);


        }
        return fixedArr;
    }

    public static void ArrayPrint(ArrayList<String> mStrList, String title) {

        ArrayList<String> addMao = CheckAndAddMaoMethod(mStrList);
        // 对mStrList 进行 对其处理  重新转换为 对其的  ArrayList<String> new
        // 1. 判断所有字符串中 第一次出现冒号的位置   查找出最大的位置的那个 并 记录这个最大位置 xMaxLengh
        // 2.  重新排序的规则是  小字符串需要在: 之后添加  xMaxLengh - self().length 的空格 并重新加入新的数组
        ArrayList<String> firstFixedStringArrA = firstFixedStringArr(addMao);
        boolean isOver100 = isItemLengthOver100(firstFixedStringArrA);

        if (isOver100) {
            //     System.out.println("当前的字符串Item 存在大于 100字符的！");
            ArrayList<String> newLessList = toMakeListItemLess100(firstFixedStringArrA, MAX_COUNT_CHAR_IN_ROW);
            showTableLogCommon100(newLessList, title);  //  每一行都小于100个字的打印
        } else { //
            //   System.out.println("当前的字符串Item 不 存在大于 100字符的！");
            showTableLogCommon100(firstFixedStringArrA, title);  //  每一行都小于100个字的打印


        }
    }

    static  int MAX_COUNT_CHAR_IN_ROW = 120;
    static  int MAX_COUNT_CHAR_IN_ROW_DEFAULT = 120;
    public static void showTableLogCommon100(ArrayList<String> mStrList, String title) {
        int maxLength = getItemMaxLength(mStrList);
        ArrayList<String> fixStrArr = fixStrArrMethodCommon100(mStrList, MAX_COUNT_CHAR_IN_ROW);
        int chineseCount = getChineseCount(title);


        String beginRow = "╔════════════════════════════════════════════════" + title + "═════════════════════════════════════════════════════╗";
        String endRow = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝";
        int fixLength = 0;
        int oriLength = title.length();
        if (chineseCount == 0) { // 不包含汉字
            fixLength = oriLength;

        } else {
            if (chineseCount == oriLength) { // 全部包含汉字
                fixLength = 2 * oriLength;
            } else { // 一部分汉字  一部分英语

                fixLength = oriLength - chineseCount + (2 * chineseCount);
            }

        }
        String templateString = "╗";
        if (fixLength > 0) {
            for (int i = 0; i < fixLength; i++) {
                templateString = "═" + templateString;
            }
        }

        beginRow = beginRow.replace(templateString, "╗");
        //  System.out.println(" fixStrArr.size() =" + fixStrArr.size());
        beginRow =  resetBeginRowToDefaultSize(beginRow);
        System.out.println(beginRow);
        for (int i = 0; i < fixStrArr.size(); i++) {
            System.out.println(fixStrArr.get(i));
        }
        endRow =  resetEndRowToDefaultSize(endRow);
        System.out.println(endRow);
    }

  static String resetBeginRowToDefaultSize(String beginRow){
        String curBeginStr = new String(beginRow);
       int curPaddingLength =  getPaddingChineseLength(curBeginStr);
       int distance = 0 ;
       if(curPaddingLength < MAX_COUNT_CHAR_IN_ROW){
           distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
       }
       String paddingString = getRepeatString("═",distance + 3);
      curBeginStr = curBeginStr.replace("╗",paddingString+"╗");
      return curBeginStr;
    }

    static String resetEndRowToDefaultSize(String beginRow){
        String curBeginStr = new String(beginRow);
        int curPaddingLength =  getPaddingChineseLength(curBeginStr);
        int distance = 0 ;
        if(curPaddingLength < MAX_COUNT_CHAR_IN_ROW){
            distance = MAX_COUNT_CHAR_IN_ROW - curPaddingLength;
        }
        String paddingString = getRepeatString("═",distance + 3);
        curBeginStr = curBeginStr.replace("╝",paddingString+"╝");
        return curBeginStr;
    }


    static String   getRepeatString(String repeatSrc,int repeatCount){
        String src = "";
        for (int i = 0; i < repeatCount; i++) {
            src += repeatSrc;
        }
        return src;
    }


    public static ArrayList<String> toMakeListItemLess100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixLengthArr = new ArrayList<String>();

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() < MAX_COUNT_CHAR_IN_ROW) {
                fixLengthArr.add(mStrList.get(i));
            } else {
                String curMaxStr = mStrList.get(i);
                ArrayList<String> fixA = null;
                if (curMaxStr.contains(";")) {
                    fixA = sqlitString(curMaxStr, ";");
                } else if (curMaxStr.contains("。")) {
                    fixA = sqlitString(curMaxStr, "。");
                } else if (curMaxStr.contains(":")) {
                    fixA = sqlitString(curMaxStr, ":");
                } else if (curMaxStr.contains(".")) {
                    fixA = sqlitString(curMaxStr, ".");
                } else if (curMaxStr.contains(" ")) {
                    fixA = sqlitString(curMaxStr, " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(curMaxStr, MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixLengthArr.add(tempArr.get(j));
                    }
                }

                if (fixA != null) {
                    //   System.out.println(" fixA.size="+ fixA.size());
                    for (int j = 0; j < fixA.size(); j++) {
                        // System.out.println(" fixA.size="+ fixA.size() + " i="+j+"   value:"+fixA.get(j));
                    }
                } else {
                    //  System.out.println(" fixA.size= null!");
                }
                if (isItemLengthOver100(fixA)) {
                    String fixSub = curMaxStr.substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixLengthArr.add(fixSub);
                } else {
                    if (fixA != null) {
                        for (int j = 0; j < fixA.size(); j++) {
                            fixLengthArr.add(fixA.get(j));
                        }
                    }
                }


            }
        }

        return fixLengthArr;
    }

    public static ArrayList<String> sqlitString(String bigString, String sqlitChar) {
        ArrayList<String> fixArr = new ArrayList<String>();
        ArrayList<String> subArr = new ArrayList<String>();
        String[] strArr = bigString.trim().split(sqlitChar.trim());
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].length() > MAX_COUNT_CHAR_IN_ROW) {
                ArrayList<String> subArrA = null;
                if (strArr[i].contains(";")) {
                    subArrA = sqlitString(strArr[i], ";");

                } else if (strArr[i].contains("。")) {
                    subArrA = sqlitString(strArr[i], "。");

                } else if (strArr[i].contains(":")) {
                    subArrA = sqlitString(strArr[i], ":");
                } else if (strArr[i].contains(".")) {
                    subArrA = sqlitString(strArr[i], ".");
                } else if (strArr[i].contains(" ")) {
                    subArrA = sqlitString(strArr[i], " ");
                } else {
                    // 对于超过最大长度  并且没有特殊字符的  每 80刀一分割 把它分割
                    ArrayList<String> tempArr = makeStringGroup(strArr[i], MAX_COUNT_CHAR_IN_ROW);
                    for (int j = 0; j < tempArr.size(); j++) {
                        fixArr.add(tempArr.get(j));
                    }

                }

                if (subArrA != null && isItemLengthOver100(subArrA)) {
                    String fixSub = strArr[i].substring(0, MAX_COUNT_CHAR_IN_ROW);
                    fixArr.add(fixSub);
                } else {
                    if (subArrA != null) {
                        for (int j = 0; j < subArrA.size(); j++) {
                            fixArr.add(subArrA.get(j));
                        }

                    }
                }

            } else {
                fixArr.add(strArr[i]);
            }
        }
        return fixArr;
    }

    public static ArrayList<String> makeStringGroup(String code, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        String oriStr = code.trim();
        while (oriStr.length() > maxcount) {
            String str1 = oriStr.substring(0, maxcount);
            fixArr.add(str1);
            oriStr = oriStr.substring(maxcount);
        }


        return fixArr;
    }

    public static ArrayList<String> fixStrArrMethodCommon100(ArrayList<String> mStrList, int maxcount) {
        ArrayList<String> fixArr = new ArrayList<String>();
        for (int i = 0; i < mStrList.size(); i++) {
            String curStr = mStrList.get(i);
            String fixCurStr = getFixLengthNewStr(curStr, maxcount);
            fixArr.add(fixCurStr);
        }

        return fixArr;
    }

    public static String getFixLengthNewStr(String oriStr, int maxLength) {
        String fixStr = "";
        String beginChar = "║ ";
        String endChar = "║";
        String oriStrTrim = oriStr.trim();
        int curLength = oriStrTrim.length();
        int paddingLength = maxLength - curLength;


        int chineseCount = getChineseCount(oriStr);
        paddingLength = paddingLength - chineseCount;
        if (paddingLength < 0) {
           // return "curString:" + oriStr + "  length more than" + maxLength;
            return "";
        }

        for (int i = 0; i < paddingLength; i++) {
            oriStrTrim += " ";
        }
        oriStrTrim = beginChar + oriStrTrim + endChar;
        //  oriStrTrim = beginChar + oriStrTrim ;
        fixStr = oriStrTrim;
        return fixStr;
    }


    public static boolean isItemLengthOver100(ArrayList<String> mStrList) {
        boolean flag = false;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > MAX_COUNT_CHAR_IN_ROW) {
                //   System.out.println("index["+i+"]  size= "+mStrList.get(i).length()+"     Value:" + mStrList.get(i) );
                return true;
            }
        }
        return flag;

    }


    public static int getMaxMaoPosition(ArrayList<String> mStrList) {
        int maoPosition = 0;
        String maxString = "";
        for (int i = 0; i < mStrList.size(); i++) {
            if ((mStrList.get(i).contains(":"))) {
                int curMaoPosition = mStrList.get(i).indexOf(":");
                String maoString = mStrList.get(i).substring(0, curMaoPosition + 1);
                int paddingSize = getPaddingChineseLength(maoString);
                if (paddingSize > maoPosition) {
                    maoPosition = paddingSize;
                    maxString = mStrList.get(i);
                }
            }

        }
        //  System.out.println("最长的冒号位置: maoPosition="+maoPosition+"   string="+maxString);
        return maoPosition;
    }


    public static int getItemMaxLength(ArrayList<String> mStrList) {
        int itemLength = 0;

        for (int i = 0; i < mStrList.size(); i++) {
            if (mStrList.get(i).length() > itemLength) {
                itemLength = mStrList.get(i).length();
            }

        }
        return itemLength;
    }

    public static int getPaddingChineseLength(String oriStr) {
        int resultLength = 0;
        int oriSize = oriStr.length();
        int chinseSize = getChineseCount(oriStr);   // 所有中文的个数
        int distanceSize = oriSize - chinseSize; // 所有英文的个数
        resultLength = chinseSize * 2 + distanceSize;
        return resultLength;

    }

    public static int getChineseCount(String oriStr) {
        int count = 0;
        for (int i = 0; i < oriStr.length(); i++) {
            char itemChar = oriStr.charAt(i);
            /*

|| (itemChar+"").equals("，")
|| (itemChar+"").equals("’")
|| (itemChar+"").equals("‘")

|| (itemChar+"").equals("；")
             */
            if ((itemChar + "").equals("：")
                    || (itemChar + "").equals("】") || (itemChar + "").equals("【") || (itemChar + "").equals("）")
                    || (itemChar + "").equals("（") || (itemChar + "").equals("￥") || (itemChar + "").equals("—")
                    || (itemChar + "").equals("？") || (itemChar + "").equals("，") || (itemChar + "").equals("；")
                    || (itemChar + "").equals("！") || (itemChar + "").equals("《")
                    || (itemChar + "").equals("》") || (itemChar + "").equals("。") || (itemChar + "").equals("、")
            ) {
                count++;
                continue;
            }
            boolean isChinese = isContainChinese(itemChar + "");
            if (isChinese) {
                count++;
            }
        }
        return count;
    }


    public static int getFirstChinesePosition(String str) {
        int position = 0;
        boolean getFirstChinese = false;
        char[] newChar = str.toCharArray();  //转为单个字符
        for (int i = 0; i < newChar.length; i++) {
            char curChar = newChar[i];
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(curChar + "");
            if (m.find()) {
                getFirstChinese = true;
                position = i;
            } else {
                if (getFirstChinese == true) {
                    return i;
                }
            }

        }
        return position;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


}

