import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class F8_Dump_Location {

//   getprop.txt  中读取 [ro.bui  ld.version.release]: [10]
//getprop.txt   [ro.hardware.soc.manufacturer]: [qcom]   制造商
//  如果不包含  那么 检测 是否包含MTK
    // .mtk  或者  .mtk
    // .qcom  或者  qcom.




    static String F8DirPathStr = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + "F8";
    static File F8DirFile = new File(F8DirPathStr);
    static  String getpropFileName = "getprop.txt";
    static  String gpsFileName = "location.txt";
    static Map<String,File> allDumpMap = new HashMap<String,File>();

    // 获取在目录 F8DirFile 下 所有文件的 绝对路径
    static   ArrayList<File> AllDumpFileList  = new ArrayList<File>();

    static Map<String,String> fileNameMapClass = new HashMap<String,String>();
    static{

        // class LocationManagerService extends ILocationManager.Stub {
        fileNameMapClass.put(gpsFileName,"/frameworks/base/services/core/java/com/android/server/LocationManagerService.java");

    }

    static  String mAndroidVersion;   // 产商
    static String mVerndor;  // 版本
    static  AndroidAnalysis curAndroidAnalysis;



    enum OS_TYPE{
        Windows,
        Linux,
        MacOS
    }
    static OS_TYPE curOS_TYPE = OS_TYPE.Windows;


    static void initSystemInfo(){
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if(osName.contains("window")){
            curOS_TYPE = OS_TYPE.Windows;
        }else if(osName.contains("linux")){
            curOS_TYPE = OS_TYPE.Linux;
        }else if(osName.contains("mac")){
            curOS_TYPE = OS_TYPE.MacOS;
        }
    }

    static ArrayList<String> mKeyWordName = new ArrayList<>();


    public static void main(String[] args) {
        initSystemInfo();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
                mKeyWordName.add(args[i]);
            }
        }

        // 先去 读取当前的安卓版本  厂商
        if(!InitFileAndGetAndroidVersion()){
            System.out.println("无法获得当前的设备安卓系统版本，以及对应的生产厂商， 退出!");
            return;
        }
        F8_Dump_Location mDumpAnalysis = new F8_Dump_Location();
        mDumpAnalysis.initAnalysisWithVersion();
        curAndroidAnalysis.analysisFile();
        NotePadOpenTargetFile();


    }

    static void NotePadOpenTargetFile(){
        String absPath = F8DirPathStr+File.separator+gpsFileName;
        String commandNotead = "";
        if(curOS_TYPE == OS_TYPE.Windows){
            commandNotead = "cmd.exe /c start   Notepad++.exe " + absPath;
        }else if(curOS_TYPE == OS_TYPE.Linux){
            commandNotead  = " gedit " + absPath;
        }else if(curOS_TYPE == OS_TYPE.MacOS){
            commandNotead  = " gedit " + absPath;
        }
        execCMD(commandNotead);
    }

    public static String execCMD(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

// Zukgit1  -- 依据版本生成 对不同版本的解析类  ( 目前只完成了 Android10Analysis )
    void initAnalysisWithVersion(){

        int version = Integer.parseInt(mAndroidVersion);
        System.out.println("initAnalysisWithVersion version = "+ version);
//        switch (version){
//            case 9:
//                curAndroidAnalysis = new Android9Analysis(mAndroidVersion,mVerndor);
//                break;
//            case 10:
//                curAndroidAnalysis = new Android10Analysis(mAndroidVersion,mVerndor);
//                System.out.println("curAndroidAnalysis 10 = "+curAndroidAnalysis);
//                break;
//
//            case 11:
//                curAndroidAnalysis = new Android11Analysis(mAndroidVersion,mVerndor);
//                break;
//            default:
//                curAndroidAnalysis = new Android10Analysis(mAndroidVersion,mVerndor);
//
//        }

        curAndroidAnalysis = new Android10Analysis(mAndroidVersion,mVerndor);

    }

    //  创建一个 分别解析  8 9 10 11 的  类  它的类用来对数据进行分析

    class AndroidAnalysisBase extends AndroidAnalysis{


        public AndroidAnalysisBase(String mVersion, String mVendor) {
            super(mVersion, mVendor);
        }

        @Override
        void preAnalysisFile(String fileName, File targetFile) {
            System.out.println("开始解析文件:  "+fileName +"   路径:"+targetFile.getAbsolutePath());
        }

        @Override
        ArrayList<String> doAnalysisFile(String fileName, File targetFile, ArrayList<String> rawContent, ArrayList<KeyWordItem> keyWordList) {
            ArrayList<String> fixedArr = new  ArrayList<String>();

            for (int i = 0; i < rawContent.size(); i++) {
                String rawLine = rawContent.get(i);
                String fixedLine = "";
                if(keyWordList == null){
                    System.out.println("当前文件:"+targetFile.getAbsolutePath()+" 没有可以匹配的 关键词匹配列表 keyWordList ");
                    return null;
                }
                boolean directUp = true;
                for (int j = 0; j < keyWordList.size(); j++) {
                    KeyWordItem keyItem = keyWordList.get(j);
                    if(!keyItem.isContain){
                        if( !"".equals(rawLine) && rawLine.contains(keyItem.keyword) && rawLine.startsWith(keyItem.keyword)){
                            fixedLine = keyItem.fixLine(rawLine,keyItem);
                            directUp = keyItem.directUp;
                            break;
                        }
                    }else{
                        if( !"".equals(rawLine) && rawLine.contains(keyItem.keyword)){
                            fixedLine = keyItem.fixLine(rawLine,keyItem);
                            directUp = keyItem.directUp;
                            break;
                        }

                    }

                }
                if(!"".equals(fixedLine)){
                    if(directUp){
                        fixedArr.add(fixedLine);
                        fixedArr.add(rawLine);
                    }else{
                        fixedArr.add(rawLine);
                        fixedArr.add(fixedLine);
                    }

                }else{
                    fixedArr.add(rawLine);
                }

            }

            return fixedArr;
        }

        @Override
        void endAnalysisFile(String fileName, File targetFile, ArrayList<String> fixContent) {
            //  把 对应 生成的  ArrayList<String> 写入文件
            writeContentToFile(targetFile,fixContent);

        }

        @Override
        HashMap<String, ArrayList<KeyWordItem>> getKeyWordMap() {
            return null;
        }
    }

    class KeyWordItem{
        String fileName;  // dump文件名称
        String  rawLine;   // 匹配到的当前的dump中的一行数据
        String version;   // 当前分析的版本
        String keyword;  //  当前分析的关键字
        String explain;  // 个人描述
        String expain1;
        String printLogUrl;   // 打印Log的位置
        String printcode; // 打印的代码
        String classUrl;  // 属性对应的地址
        String classNameForShuxing;  // 属性定义的类名称
        String  shuxingCall;   //  属性调用的地方
        String  shuxingDefine;   // 属性定义的样式
        boolean directUp ;
        boolean isContain; // false 为 start检测  ---   true为  contain检测

        KeyWordItem(){
            this.version = mAndroidVersion+"";
            directUp = true;
            isContain = false;
        }
        public KeyWordItem( KeyWordItem samekeyWord) {
            this.fileName = new String(samekeyWord.fileName);
            this.rawLine = new String(samekeyWord.rawLine);
            this.version = new String(samekeyWord.version);
            this.keyword = new String(samekeyWord.keyword);
            this.explain = new String(samekeyWord.explain);
            this.printLogUrl = new String(samekeyWord.printLogUrl);
            this.printcode = new String(samekeyWord.printcode);
            this.classUrl = new String(samekeyWord.classUrl);
            this.classNameForShuxing = new String(samekeyWord.classNameForShuxing);
            this.shuxingCall = new String(samekeyWord.shuxingCall);
            this.shuxingDefine = new String(samekeyWord.shuxingDefine);
            directUp = true;
            isContain = false;
        }





        String fixLine(String rawLine , KeyWordItem keyWord){
            String pre1 = "\n═════════════ "+keyWord.keyword+" Begin═══════════════\n";
            String result  = "【版本:"+version+ "】 \n【解释:" +explain+"】 \n【属性类:"+classNameForShuxing+"】 \n【属性定义:"+shuxingDefine+"】"+"\n【 打印代码:"+printcode+"】\n【 类路径:"+classUrl+"】"+"\n【 打印Log的地址:"+printLogUrl+"】" ;
            String end = "\n═════════════ "+keyWord.keyword+" End════════════════";
            String explain1 = keyWord.expain1 == null ? "": "\n\n"+keyWord.expain1;
            return pre1 + result +explain1+ end;
        }
    }

    abstract   class AndroidAnalysis{
        String version;
        String vendor;
        ArrayList<File> curAllDumpFile ;
        ArrayList<String> fileNameList;

        abstract  void preAnalysisFile(String fileName,File targetFile);
        abstract  ArrayList<String> doAnalysisFile(String fileName,File targetFile , ArrayList<String> rawContent ,ArrayList<KeyWordItem> keyWordList);
        abstract  void endAnalysisFile(String fileName,File targetFile,ArrayList<String> fixContent);
        abstract  HashMap<String,ArrayList<KeyWordItem>> getKeyWordMap(); //   当前 key为 文件名称  value为对应的keyword

        public AndroidAnalysis(String mVersion ,String mVendor ){
            this.version = mVersion;
            this.vendor = mVendor;
            curAllDumpFile = getAllDumpFile();
            System.out.println("version = "+ version);
            fileNameList = getVersionNeedAnalysisFileNameList(version);
        }



        public AndroidAnalysis(String version, String vendor , ArrayList<File> allDumpFile , ArrayList<String> fileNameList ) {
            this.version = version;
            this.vendor = vendor;
            this.curAllDumpFile = allDumpFile;
            this.fileNameList = fileNameList;
        }


        void analysisFile(){

            for (int i = 0; i <curAllDumpFile.size(); i++) {
                File curFile = curAllDumpFile.get(i);
                String name = curFile.getName();
                if(fileNameList.contains(name)){
                    preAnalysisFile(name,curFile);
                    ArrayList<String> rawContent =  ReadFileRawContent(curFile);
                    // 读取每行
                    ArrayList<String> fixContent =   doAnalysisFile(name,curFile,rawContent,getKeyWordMap().get(name));
                    if(fixContent != null && fixContent.size() > 0){
                        endAnalysisFile(name,curFile,fixContent);
                    }

                }

            }


        }


        ArrayList<File> getAllDumpFile(){
            return AllDumpFileList;
        }

        // Zukgit2  -- 依据版本生成 对 不同的 输入文件 进行解析  目前 支持 gps
        ArrayList<String> getVersionNeedAnalysisFileNameList(String versionStr){
            if(versionStr == null)
                return null;
            int version = Integer.parseInt(versionStr);
            ArrayList<String> listFilename = new  ArrayList<String>();
            switch (version){
                case 9:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    break;
                case 10:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);
                    break;

                case 11:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);

                    break;
                default:
                    listFilename.add(getpropFileName);
                    listFilename.add(gpsFileName);

            }


            return listFilename;
        }

    }


    class Android9Analysis extends AndroidAnalysisBase {


        public Android9Analysis(String version, String vendor ) {
            super(version, vendor);
        }
    }


    class Android10Analysis extends AndroidAnalysisBase {


        public Android10Analysis(String version, String vendor ) {
            super(version, vendor);
        }


        @Override
        HashMap<String, ArrayList<KeyWordItem>> getKeyWordMap() {
            HashMap<String, ArrayList<KeyWordItem>>  file_keyword_map = new  HashMap<String, ArrayList<KeyWordItem>>();

            // location.txt;
            file_keyword_map.put(gpsFileName,initLocationkeyWordList());

            return file_keyword_map;
        }



        ArrayList<KeyWordItem>    initLocationkeyWordList(){
            ArrayList<KeyWordItem> keyWordList = new ArrayList<KeyWordItem>();
// http://androidxref.com/9.0.0_r3/xref/frameworks/base/services/core/java/com/android/server/LocationManagerService.java#3374
            KeyWordItem location_1_1 = new KeyWordItem();
            location_1_1.keyword = "Current Location Manager state:";
            location_1_1.explain="当前 LocationManager的状态  LocationManagerService.java ";
            location_1_1.classNameForShuxing = " LocationManagerService extends ILocationManager.Stub {}  ";
            location_1_1.printcode="LocationManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            location_1_1.printLogUrl="";
            location_1_1.expain1="LOG示例: \n" +
                    " Current System Time: 12-10 00:39:05.015 【当前手机系统时间(可能不正确)】 \n" +
                    " Current Elapsed Time: +5d22h5m14s323ms  【当前手机开机持续时间  天时分秒】\n" +
                    " \n" +
                    " 经纬度查询: http://www.gpsspg.com/maps.htm\n" +
                    " 位置provider类型:   adb shell settings get secure  location_providers_allowed    【gps,network】\n" +
                    " \n" +
                    "GPS定位模式:        adb shell settings get secure location_mode       【3】\n" +
                    "adb shell settings put secure location_mode   0  【 关闭 GPS定位 】\n" +
                    "adb shell settings put secure location_mode   3  【 打开 GPS定位 】\n" +
                    "【0-GPS关闭 1-仅使用设备(GPS)定位  2-低功耗定位模式，仅使用网络定位(WiFi和基站定位)  3-高精度定位模式，GPS与网络综合定位】\n" +
                    "\n" +
                    "WIFI扫描定位:  adb shell settings get global wifi_scan_always_enabled       【0-WIFI扫描定位关闭】【1-WIFI扫描定位开启】\n" +
                    "adb shell settings put global wifi_scan_always_enabled   0    【 关闭WIFI扫描定位 】\n" +
                    "adb shell settings put global wifi_scan_always_enabled   1    【 开启WIFI扫描定位 】\n" +
                    "\t \n" +
                    "\n" +
                    "蓝牙BLE扫描定位:  adb shell settings get ble_scan_always_enabled   【0-蓝牙扫描定位关闭】【1-蓝牙扫描定位开启】\n" +
                    "adb shell settings put global ble_scan_always_enabled   0    【 关闭蓝牙扫描定位 】\n" +
                    "adb shell settings put global ble_scan_always_enabled   1    【 开启蓝牙扫描定位 】\n" +
                    "\n" +
                    "\t\t\n" +
                    " 搜索关键词:\n" +
                    "【当前passtive 被动监听位置数据的APP 列表 】———————— 搜索 【     Reciever[】\n" +
                    "LocationManagerService HashMap<String, ArrayList<UpdateRecord>>  mRecordsByProvide = \n" +
                    "【key:【\"fused\",\"gps\",\"passive\",\"network\"】 value=【 ArrayList<UpdateRecord>】\n" +
                    "【活跃的provide的location被请求记录集合】———————— 搜索 【Active Records by Provider: End】\n" +
                    "【所有的Provider的location被请求记录集合 】———————— 搜索 【Historical Records by Provider: End】\n" +
                    "【实现aidl接口 对GPS状态进行监听的APP统计(APP重启会打开新的PID进程ID) 】————— 搜索 【Active GnssStatus Listeners: End】\n" +
                    "【对地址进行偏移的情况 】————— 搜索 【fudger: offset:】\n" +
                    "【 GPS provider的情况 开启的时间 】————— 搜索 【gps provider: End】\n" +
                    "【 GPS的功能bitfeature比特位 】————— 搜索 【mTopHalCapabilities=0x】\n" +
                    "【 定位 卫星 北斗 GPS 等卫星信息 】————— 搜索 【native internal state: End═】\n" +
                    "【 最近最新搜索到的GPS经纬度信息 】————— 搜索 【Gnss Location Data:: LatitudeDegrees:】";


/*
            location_1_1.expain1="P:  http://androidxref.com/9.0.0_r3/xref/frameworks/base/services/core/java/com/android/server/LocationManagerService.java#3374\n" +
                    "Q:  https://www.androidos.net.cn/android/10.0.0_r6/xref/frameworks/base/services/core/java/com/android/server/LocationManagerService.java\n" +
                    "\t\n" +
                    "    protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t  // 检查是否有Dump的权限?   需要 android.permission.DUMP permission 的权限\n" +
                    "        if (!DumpUtils.checkDumpPermission(mContext, TAG, pw)) return;\n" +
                    "\n" +
                    "        synchronized (mLock) {\n" +
                    "            if (args.length > 0 && args[0].equals(\"--gnssmetrics\")) {\n" +
                    "                if (mGnssMetricsProvider != null) {\n" +
                    "                    pw.append(mGnssMetricsProvider.getGnssMetricsAsProtoString());\n" +
                    "                }\n" +
                    "                return;\n" +
                    "            }\n" +
                    "            pw.println(\"Current Location Manager state:\");\n" +
                    "            pw.print(\"  Current System Time: \" + TimeUtils.logTimeOfDay(System.currentTimeMillis()));\n" +
                    "            pw.println(\", Current Elapsed Time: \"  + TimeUtils.formatDuration(SystemClock.elapsedRealtime()));\n" +
                    "            pw.println(\"  Current user: \" + mCurrentUserId + \" \" + Arrays.toString(   mCurrentUserProfiles));\n" +
                    "            pw.println(\"  Location mode: \" + isLocationEnabled());\n" +
                    "            pw.println(\"  Battery Saver Location Mode: \"  + locationPowerSaveModeToString(mBatterySaverMode));\n" +
                    "            pw.println(\"  Location Listeners:\");\n" +
                    "// 当前对地址进行监听的类  HashMap<Object, Receiver> mReceivers = new HashMap<>();\n" +
                    "//  key 是  IBinder(Object) \n" +
                    "// final class Receiver extends LinkedListenerBase implements PendingIntent.OnFinished {}\n" +
                    "// abstract static class LinkedListenerBase implements IBinder.DeathRecipient {\n" +
                    "//  A wrapper class holding either an ILocationListener or a PendingIntent to receive location updates.\n" +
                    "            for (Receiver receiver : mReceivers.values()) {\n" +
                    "                pw.println(\"    \" + receiver);\n" +
                    "            }\n" +
                    "            pw.println(\"  Active Records by Provider:\");\n" +
                    "//更新记录  HashMap<String, ArrayList<UpdateRecord>> mRecordsByProvider = new HashMap<>();\n" +
                    "// key 是 \"passive\"   \"network\"  等等  , value 是更新记录的集合 \n" +
                    "// class UpdateRecord {\n" +
                    "//        String mProvider;\n" +
                    "//        LocationRequest mRealRequest;  // original request from client\n" +
                    "//        LocationRequest mRequest;  // possibly throttled version of the request\n" +
                    "//        Receiver mReceiver;\n" +
                    "//        boolean mIsForegroundUid; // 是否是前台的UID执行的请求? \n" +
                    "//        Location mLastFixBroadcast;\n" +
                    "//        long mLastStatusBroadcast;\n" +
                    "//        Throwable mStackTrace;  // for debugging only  堆栈信息\n" +
                    "// }\n" +
                    "\t\t\n" +
                    "            for (Map.Entry<String, ArrayList<UpdateRecord>> entry : mRecordsByProvider.entrySet()) {\n" +
                    "                pw.println(\"    \" + entry.getKey() + \":\");\n" +
                    "                for (UpdateRecord record : entry.getValue()) {\n" +
                    "                    pw.println(\"      \" + record);\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "            pw.println(\"  Active GnssMeasurement Listeners:\");\n" +
                    "//  GNSS 全球卫星导航系统  \n" +
                    "// ArrayMap<IBinder, LinkedListener<IGnssMeasurementsListener>> mGnssMeasurementsListeners = new ArrayMap<>();\n" +
                    "// key 是 IBinder ,  value 是 GnssMeasurement 事件的监听类集合\n" +
                    "// 放在泛型 LinkedListener 中 LinkedListener 应该实现了Identity接口    \n" +
                    "//  static class LinkedListener<TListener> extends LinkedListenerBase {} \n" +
                    "//  abstract static class LinkedListenerBase implements IBinder.DeathRecipient {\n" +
                    "//  protected final CallerIdentity mCallerIdentity;\n" +
                    "//  protected final String mListenerName;} END \tLinkedListenerBase\t\n" +
                    "// /frameworks/base/location/java/android/location/IGnssMeasurementsListener.aidl\n" +
                    "//【 IGnssMeasurementsListener.aidl 】  oneway interface IGnssMeasurementsListener {\n" +
                    "//    void onGnssMeasurementsReceived(in GnssMeasurementsEvent event);\n" +
                    "//    void onStatusChanged(in int status);\n" +
                    "//}\n" +
                    "\n" +
                    "            dumpGnssDataListenersLocked(pw, mGnssMeasurementsListeners);\n" +
                    "            pw.println(\"  Active GnssNavigationMessage Listeners:\");\n" +
                    "\n" +
                    "// ArrayMap<IBinder, LinkedListener<IGnssNavigationMessageListener>> mGnssNavigationMessageListeners = new ArrayMap<>();\n" +
                    "//  static class LinkedListener<TListener> extends LinkedListenerBase {} \n" +
                    "// 【 IGnssNavigationMessageListener.aidl 】 oneway interface IGnssNavigationMessageListener {\n" +
                    "//    void onGnssNavigationMessageReceived(in GnssNavigationMessage event);\n" +
                    "//    void onStatusChanged(in int status);\n" +
                    "//}\n" +
                    "\n" +
                    "            dumpGnssDataListenersLocked(pw, mGnssNavigationMessageListeners);\n" +
                    "            pw.println(\"  Active GnssStatus Listeners:\");\n" +
                    "\t\t\t\n" +
                    "// ArrayMap<IBinder, LinkedListener<IGnssStatusListener>> mGnssStatusListeners = new ArrayMap<>();\n" +
                    "//  static class LinkedListener<TListener> extends LinkedListenerBase {} \n" +
                    "// 【 IGnssStatusListener.aidl 】oneway interface IGnssStatusListener {\n" +
                    "// void onGnssStarted();\n" +
                    "// void onGnssStopped();\n" +
                    "// void onFirstFix(int ttff);\n" +
                    "// void onSvStatusChanged(int svCount, in int[] svidWithFlags, in float[] cn0s,  in float[] elevations, in float[] azimuths,in float[] carrierFreqs);\n" +
                    "// void onNmeaReceived(long timestamp, String nmea);\n" +
                    "// }\n" +
                    "            dumpGnssDataListenersLocked(pw, mGnssStatusListeners);\n" +
                    "\n" +
                    "//  LocationRequestStatistics mRequestStatistics = new LocationRequestStatistics();\n" +
                    "// Maps package name and provider to location request statistics.  包名与位置请求统计的map\n" +
                    "//   mRequestStatistics.statistics === HashMap<PackageProviderKey, PackageStatistics> statistics  = new HashMap<PackageProviderKey, PackageStatistics>();\n" +
                    "//  static class PackageProviderKey { String packageName;  String providerName;【gps,network....】} \n" +
                    "//  public static class PackageStatistics {\n" +
                    "// long mInitialElapsedTimeMs;  // 这个应用首次请求位置的时间戳  \n" +
                    "// int mNumActiveRequests;  // 请求次数  Number of active location requests this package currently has.\n" +
                    "// long mLastActivitationElapsedTimeMs; 记录当前APP开始请求位置并得到回馈的时间戳//  Time when this package most recently went from not requesting location to requesting.\n" +
                    "// long mFastestIntervalMs;  // 位置请求之间的最短  最快时间间隔\n" +
                    "// long mSlowestIntervalMs; // 位置请求之间的最大 最慢 时间间隔 The slowest interval this package has ever requested.\n" +
                    "// long mTotalDurationMs;  // 请求位置花费总的时间 The total time this app has requested location\n" +
                    "//  long mLastForegroundElapsedTimeMs; // APP 最近一次进入前台请求位置的时间戳  ，0 标识当前不在前台\n" +
                    "// long mForegroundDurationMs;  // 当前APP在前台请求位置的时间长度\n" +
                    "// long mLastStopElapsedTimeMs;  // APP 最近停止请求位置信息的时间戳\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\t\tpw.println(\"  Historical Records by Provider:\");\n" +
                    "\t\tfor (Map.Entry<PackageProviderKey, PackageStatistics> entry : mRequestStatistics.statistics.entrySet()) {\n" +
                    "\t\t\tPackageProviderKey key = entry.getKey();\n" +
                    "\t\t\tPackageStatistics stats = entry.getValue();\n" +
                    "\t\t\tpw.println(\"    \" + key.packageName + \": \" + key.providerName + \": \" + stats);\n" +
                    "\t\t}\n" +
                    "\t\tpw.println(\"  Last Known Locations:\");\n" +
                    "\t\t\n" +
                    "// mapping from provider name to last known location \n" +
                    "//  Key为Provider【\"gps\",\"network\"】 Value为地址 Location \n" +
                    "// HashMap<String, Location> mLastLocation = new HashMap<>(); \n" +
                    "// /frameworks/base/location/java/android/location/Location.java   class Location implements Parcelable {}\n" +
                    "\n" +
                    "\t\tfor (Map.Entry<String, Location> entry : mLastLocation.entrySet()) {\n" +
                    "\t\t\tString provider = entry.getKey();\n" +
                    "\t\t\tLocation location = entry.getValue();\n" +
                    "\t\t\tpw.println(\"    \" + provider + \": \" + location);\n" +
                    "\t\t}\n" +
                    "\n" +
                    "            pw.println(\"  Last Known Locations Coarse Intervals:\");\n" +
                    "// same as mLastLocation, but is not updated faster than LocationFudger.FASTEST_INTERVAL_MS.\n" +
                    "// locations stored here are not fudged for coarse permissions. \n" +
                    "// HashMap<String, Location> mLastLocationCoarseInterval = new HashMap<>();\n" +
                    "// /frameworks/base/location/java/android/location/Location.java   class Location implements Parcelable {}\n" +
                    "\n" +
                    "            for (Map.Entry<String, Location> entry : mLastLocationCoarseInterval.entrySet()) {\n" +
                    "                String provider = entry.getKey();\n" +
                    "                Location location = entry.getValue();\n" +
                    "                pw.println(\"    \" + provider + \": \" + location);\n" +
                    "            }\n" +
                    "\n" +
                    "//  GeofenceManager mGeofenceManager; \t\n" +
                    "//  /frameworks/base/services/core/java/com/android/server/location/GeofenceManager.java\n" +
                    "//  class GeofenceManager implements LocationListener, PendingIntent.OnFinished { }\n" +
                    "// A list containing all registered geofences.\n" +
                    "// List<GeofenceState> mFences = new LinkedList<GeofenceState>();\n" +
                    "//    public 【 GeofenceManager 】 void dump(PrintWriter pw) {\n" +
                    "//        pw.println(\"  Geofences:\");\n" +
                    "//        for (GeofenceState state : mFences) {\n" +
                    "//            pw.append(\"    \");\n" +
                    "//            pw.append(state.mPackageName);\n" +
                    "//            pw.append(\" \");\n" +
                    "//            pw.append(state.mFence.toString());\n" +
                    "//            pw.append(\"\\n\");\n" +
                    "//        }\n" +
                    "//    }\n" +
                    "\n" +
                    "// /frameworks/base/services/core/java/com/android/server/location/GeofenceState.java\n" +
                    "//  Represents state associated with a geofence \n" +
                    "//  public class GeofenceState {}\n" +
                    "//final static int FLAG_ENTER = 0x01; 【状态标识?】\n" +
                    "//final static int FLAG_EXIT = 0x02;\n" +
                    "//\n" +
                    "//static final int STATE_UNKNOWN = 0;  【当前位置状态标识? 】\n" +
                    "//static final int STATE_INSIDE = 1;\n" +
                    "//static final int STATE_OUTSIDE = 2;\n" +
                    "//\n" +
                    "//final Geofence mFence;\n" +
                    "//final Location mLocation;\n" +
                    "//final long mExpireAt;\n" +
                    "//final int mAllowedResolutionLevel;\n" +
                    "//final int mUid;\n" +
                    "//final String mPackageName;\n" +
                    "//final PendingIntent mIntent;\n" +
                    "//\n" +
                    "//int mState;  // current state  【 当前地址状态保存值 】\n" +
                    "//double mDistanceToCenter;  // 与中心位置距离 current distance to center of fence\n" +
                    "\n" +
                    "//  Represents a geographical boundary, also known as a geofence. \n" +
                    "//  /frameworks/base/location/java/android/location/Geofence.java\n" +
                    "// public final class Geofence implements Parcelable {   // 代表一个地理界线\n" +
                    "//     public static final int TYPE_HORIZONTAL_CIRCLE = 1;\n" +
                    "//   final int mType;  // 类型\n" +
                    "//   final double mLatitude;  // 经度\n" +
                    "//   final double mLongitude; // 维度\n" +
                    "//   final float mRadius;     // 半径\n" +
                    "// }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "            if (mGeofenceManager != null) {\n" +
                    "                mGeofenceManager.dump(pw);\n" +
                    "            } else {\n" +
                    "                pw.println(\"  Geofences: null\");\n" +
                    "            }\n" +
                    "\n" +
                    "//  LocationBlacklist mBlacklist;\n" +
                    "// final class LocationBlacklist extends ContentObserver { \n" +
                    "// 允许应用在运行时被列入 位置更新的黑名单\n" +
                    "// Allows applications to be blacklisted from location updates at run-time.\n" +
                    "            if (mBlacklist != null) {\n" +
                    "                pw.append(\"  \");\n" +
                    "                mBlacklist.dump(pw);\n" +
                    "            } else {\n" +
                    "                pw.println(\"  mBlacklist=null\");\n" +
                    "            }\n" +
                    "\n" +
                    "\t\t\t// String mExtraLocationControllerPackage; 【 com.google.android.gms.location.history 】\n" +
                    "\t\t\t// boolean mExtraLocationControllerPackageEnabled;   【 false 】\n" +
                    "            if (mExtraLocationControllerPackage != null) {\n" +
                    "                pw.println(\" Location controller extra package: \" + mExtraLocationControllerPackage\n" +
                    "                        + \" enabled: \" + mExtraLocationControllerPackageEnabled);\n" +
                    "            }\n" +
                    "\n" +
                    "\t\t\t// ArraySet<String> mBackgroundThrottlePackageWhitelist = new ArraySet<>(); \n" +
                    "\t\t\t// 位置信息请求-次数控制类型 白名单列表 \n" +
                    "            if (!mBackgroundThrottlePackageWhitelist.isEmpty()) {\n" +
                    "                pw.println(\"  Throttling Whitelisted Packages:\");\n" +
                    "                for (String packageName : mBackgroundThrottlePackageWhitelist) {\n" +
                    "                    pw.println(\"    \" + packageName);\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "\t\t\t// 位置信息请求-请求完全接受 白名单列表 \n" +
                    "\t\t\t// ArraySet<String> mIgnoreSettingsPackageWhitelist = new ArraySet<>(); \n" +
                    "            if (!mIgnoreSettingsPackageWhitelist.isEmpty()) {\n" +
                    "                pw.println(\"  Bypass Whitelisted Packages:\");\n" +
                    "                for (String packageName : mIgnoreSettingsPackageWhitelist) {\n" +
                    "                    pw.println(\"    \" + packageName);\n" +
                    "                }\n" +
                    "            }\n" +
                    "//   /frameworks/base/services/core/java/com/android/server/location/LocationFudger.java\n" +
                    "//   LocationFudger mLocationFudger;    包含低精度位置信息\n" +
                    "            if (mLocationFudger != null) {\n" +
                    "                pw.append(\"  fudger: \");\n" +
                    "                mLocationFudger.dump(fd, pw, args);\n" +
                    "            } else {\n" +
                    "                pw.println(\"  fudger: null\");\n" +
                    "            }\n" +
                    "\n" +
                    "            if (args.length > 0 && \"short\".equals(args[0])) {\n" +
                    "                return;\n" +
                    "            }\n" +
                    "\t\t\t\n" +
                    "// list of currently active providers  当前位置提供功能实现类的集合\n" +
                    "// ArrayList<LocationProvider> mProviders = new ArrayList<>();\n" +
                    "// /frameworks/base/location/java/android/location/LocationProvider.java\n" +
                    "\t\t\t\n" +
                    "            for (LocationProvider provider : mProviders) {\n" +
                    "                provider.dumpLocked(fd, pw, args);\n" +
                    "            }\n" +
                    "\t\t\t\n" +
                    "\t\t\t//  boolean mGnssBatchingInProgress = false; 是否正在批处理 位置信息请求\n" +
                    "            if (mGnssBatchingInProgress) {\n" +
                    "                pw.println(\"  GNSS batching in progress\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t";

            */
            keyWordList.add(location_1_1);



/*
            KeyWordItem location_1_2 = new KeyWordItem();
            location_1_2.keyword = "  Current user:";
            location_1_2.explain="当前用户类型记录  0-系统用户  ";
            location_1_2.classNameForShuxing = " LocationManagerService extends ILocationManager.Stub {} \n" +
                    "int mCurrentUserId = UserHandle.USER_SYSTEM 【0】;\n" +
                    "int[] mCurrentUserProfiles = new int[]{UserHandle.USER_SYSTEM【0】}; ";
            location_1_2.printcode="LocationManagerService  void dump(FileDescriptor fd, PrintWriter pw, String[] args) {} ";
            location_1_2.printLogUrl="";
            location_1_2.expain1="";

               location_1_2.expain1="public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "   // current active user on the device - other users are denied location data\n" +
                    "   int mCurrentUserId = UserHandle.USER_SYSTEM;\n" +
                    "   int[] mCurrentUserProfiles = new int[]{UserHandle.USER_SYSTEM【0】};\n" +
                    "\t\n" +
                    "   protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t...\n" +
                    "\tpw.println(\"  Current user: \" + mCurrentUserId + \" \" + Arrays.toString(   mCurrentUserProfiles));\n" +
                    "\t...\n" +
                    "\t}\n" +
                    "\t}";

                     keyWordList.add(location_1_2);

                    */



            KeyWordItem location_1_3 = new KeyWordItem();
            location_1_3.keyword = "  Location mode:";
            location_1_3.explain="地址模式  0-【LOCATION_MODE_NO_CHANGE】  ";
            location_1_3.classNameForShuxing = "  LocationManagerService extends ILocationManager.Stub {  int mBatterySaverMode; } \n ";
            location_1_3.printLogUrl=" pw.println(\"  Battery Saver Location Mode: \"  + locationPowerSaveModeToString(mBatterySaverMode)); ";
            location_1_3.expain1="public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "    // current active user on the device - other users are denied location data\n" +
                    "    private int mCurrentUserId = UserHandle.USER_SYSTEM【0】;\n" +
                    "\n" +
                    "   protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t...\n" +
                    "    pw.println(\"  Location mode: \" + isLocationEnabled());\n" +
                    "\t...\n" +
                    "\t}\n" +
                    "\t\n" +
                    "    private boolean isLocationEnabled() {\n" +
                    "        return isLocationEnabledForUser(mCurrentUserId);\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "public boolean isLocationEnabledForUser(int userId) {  // 查看当前APP是否有权限使用Location\n" +
                    "// Check INTERACT_ACROSS_USERS permission if userId is not current user id.\n" +
                    "if (UserHandle.getCallingUserId() != userId) {\n" +
                    "\tmContext.enforceCallingOrSelfPermission( Manifest.permission.INTERACT_ACROSS_USERS,\"Requires INTERACT_ACROSS_USERS permission\");\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "long identity = Binder.clearCallingIdentity();\n" +
                    "try {\n" +
                    "int userA = Settings.Secure.getIntForUser(mContext.getContentResolver(),Settings.Secure.LOCATION_MODE 【\"location_mode\"】,\n" +
                    "Settings.Secure.LOCATION_MODE_OFF,userId);  // 获取 Setting值    adb shell settings get secure location_mode  \n" +
                    "// 如果Setting获取的 location_mode  不为0  LOCATION_MODE_OFF=0 , 那么说明位置模式打开的\n" +
                    "return userA != Settings.Secure.LOCATION_MODE_OFF  \n" +
                    "} finally {\n" +
                    "\tBinder.restoreCallingIdentity(identity);\n" +
                    "}\n" +
                    "\n" +
                    "******************* Settings.Secure.java Begin  *******************\n" +
                    "        public static final int LOCATION_MODE_OFF = 0;  // 标识位置模式关闭\n" +
                    "        public static final int LOCATION_MODE_SENSORS_ONLY = 1; // 废弃--但标识 位置模式打开\n" +
                    "        public static final int LOCATION_MODE_BATTERY_SAVING = 2; // 废弃--但标识 位置模式打开\n" +
                    "        public static final int LOCATION_MODE_HIGH_ACCURACY = 3; // 废弃--但标识 位置模式打开\n" +
                    "        public static final int LOCATION_MODE_ON = LOCATION_MODE_HIGH_ACCURACY;  // 标识当前位置模式打开\n" +
                    "******************* Settings.Secure.java End  *******************\n" +
                    "\n" +
                    "提示: \n" +
                    "Location mode: true  标识设置中 位置模式打开 adb shell settings get secure location_mode  不为 0 \n" +
                    "Location mode: false 标识设置中 位置模式没有打开 adb shell settings get secure location_mode  为 0 ";
            keyWordList.add(location_1_3);



            KeyWordItem location_1_4 = new KeyWordItem();
            location_1_4.keyword = "  Battery Saver Location Mode:";
            location_1_4.explain="电池省电程序对 地址服务的 控制策略  ";
            location_1_4.classNameForShuxing = " public class LocationManagerService extends ILocationManager.Stub {  int mBatterySaverMode } ";
            location_1_4.printLogUrl="LocationManagerService=》dump()  =》  pw.println(\"  Battery Saver Location Mode: \"  + locationPowerSaveModeToString(mBatterySaverMode)); ";
            location_1_4.expain1="public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "    @PowerManager.LocationPowerSaveMode\n" +
                    "    private int mBatterySaverMode;\n" +
                    "\t\n" +
                    "   protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t...\n" +
                    "  pw.println(\"  Battery Saver Location Mode: \"  + locationPowerSaveModeToString(mBatterySaverMode));\n" +
                    "\t...\n" +
                    "\t}\n" +
                    "\n" +
                    "省电PowerSave下的地址访问控制策略 \n" +
                    "0=【 NO_CHANGE LOCATION_MODE_NO_CHANGE || 定位不受电池PowerSave影响的 Location模式 】\n" +
                    "1=【 GPS_DISABLED_WHEN_SCREEN_OFF LOCATION_MODE_GPS_DISABLED_WHEN_SCREEN_OFF || GPS-Location功能在电池powersave省电模式 下不可用 】\n" +
                    "2=【 ALL_DISABLED_WHEN_SCREEN_OFF LOCATION_MODE_ALL_DISABLED_WHEN_SCREEN_OFF || 所有的定位服务都不可用 在电池powersave省电模式开启 】\n" +
                    "3=【 FOREGROUND_ONLY LOCATION_MODE_FOREGROUND_ONLY || 所有的定位服务都可用,但是这些定位服务只可以服务于前台的APP 】\n" +
                    "4=【 THROTTLE_REQUESTS_WHEN_SCREEN_OFF LOCATION_MODE_THROTTLE_REQUESTS_WHEN_SCREEN_OFF ||  灭屏时对请求位置进行限制的模式 】\n";
            keyWordList.add(location_1_4);



            KeyWordItem location_1_5 = new KeyWordItem();
            location_1_5.keyword = "  Location Listeners:";
            location_1_5.explain="那些需要对位置数据进行请求的APP们的一些请求request 数据    ";
            location_1_5.classNameForShuxing = " 打印 LocationManagerService.HashMap<Object, Receiver> mReceivers 的 Receiver的集合 ";
            location_1_5.printLogUrl="public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "HashMap<Object, Receiver> mReceivers ; \n" +
                    "protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t\t.....\n" +
                    "\t\tpw.println(\"  Location Listeners:\");\n" +
                    "\t\tfor (Receiver receiver : mReceivers.values()) {\n" +
                    "\t\t\tpw.println(\"    \" + receiver);\n" +
                    "\t\t}\n" +
                    "\t\t.....\n" +
                    "\t}";
            location_1_5.expain1="******************  ILocationListener.aidl  Begin  ******************  \n" +
                    "oneway interface ILocationListener\n" +
                    "{\n" +
                    "    void onLocationChanged(in Location location);\n" +
                    "    void onProviderEnabled(String provider);\n" +
                    "    void onProviderDisabled(String provider);\n" +
                    "    void onStatusChanged(String provider, int status, in Bundle extras);\n" +
                    "}\n" +
                    "******************  ILocationListener.aidl  End  ******************  \n" +
                    "\n" +
                    "\n" +
                    "******************  /frameworks/base/location/java/android/location/LocationRequest.java  Begin  ******************  \n" +
                    "public final class LocationRequest implements Parcelable {\n" +
                    "\n" +
                    "\n" +
                    "static final double FASTEST_INTERVAL_FACTOR = 6.0;  // 6x\n" +
                    "long mInterval = 60 * 60 * 1000;   // 60 minutes\n" +
                    "long mFastestInterval = (long) (mInterval / FASTEST_INTERVAL_FACTOR);  // 10 minutes  默认的请求位置时间间隔\n" +
                    "\n" +
                    "boolean mExplicitFastestInterval = false;  // 标识是否设置APP自定义 刷新位置间隔， 没有定义的话 那么默认为  10 minutes\n" +
                    "long mExpireAt = Long.MAX_VALUE;  // 当前请求的过期时间戳   (以开机时间为起点) no expiry\n" +
                    "int mNumUpdates = Integer.MAX_VALUE;  // APP请求位置更新的请求Request数量  no expiry\n" +
                    "float mSmallestDisplacement = 0.0f;    // 最小的偏差位移  米 meters\n" +
                    "boolean mHideFromAppOps = false; // True if this request shouldn't be counted by AppOps\n" +
                    "boolean mLocationSettingsIgnored = false;  // 极端情况下使用 如拨打911  请求忽略用户位置设置以满足此请求。\n" +
                    "\n" +
                    "boolean mLowPowerMode = false;  // 低电量工作模式\n" +
                    "\n" +
                    "public static String qualityToString(int quality) {\n" +
                    "\tswitch (quality) {\n" +
                    "\t\tcase ACCURACY_FINE:\n" +
                    "\t\t\treturn \"ACCURACY_FINE\";\n" +
                    "\t\tcase ACCURACY_BLOCK:\n" +
                    "\t\t\treturn \"ACCURACY_BLOCK\";\n" +
                    "\t\tcase ACCURACY_CITY:\n" +
                    "\t\t\treturn \"ACCURACY_CITY\";\n" +
                    "\t\tcase POWER_NONE:\n" +
                    "\t\t\treturn \"POWER_NONE\";\n" +
                    "\t\tcase POWER_LOW:\n" +
                    "\t\t\treturn \"POWER_LOW\";\n" +
                    "\t\tcase POWER_HIGH:\n" +
                    "\t\t\treturn \"POWER_HIGH\";\n" +
                    "\t\tdefault:\n" +
                    "\t\t\treturn \"???\";\n" +
                    "\t}\n" +
                    "}\n" +
                    "\n" +
                    "\t\n" +
                    "int mQuality = POWER_LOW;\n" +
                    "static final int ACCURACY_FINE = 100;  // 标识 请求最精确的位置数据请求类型  1米精度 \n" +
                    "static final int ACCURACY_BLOCK = 102;  // 100 米精度  \n" +
                    "static final int ACCURACY_CITY = 104;   // 请求精确的城市地质 10km 精度\n" +
                    "static final int POWER_NONE = 200;   // 无需关注 电量的请求  一般用于 \"passive\" 监听位置数据的请求\n" +
                    "static final int POWER_LOW = 201;  //  省电模式请求  避免大功率工作\n" +
                    "static final int POWER_HIGH = 203; //  高电量下才允许的请求   允许使用大功率工作\n" +
                    "\n" +
                    "\n" +
                    "String mProvider = LocationManager.FUSED_PROVIDER;  // 请求位置数据的方式\n" +
                    "// Provider数据来源类型 \"network\"   \"gps\"  \"passive\"   \"fused\"\n" +
                    "// \"network\"  通过WIFI和信号搭来确定的位置  位置结果通过  \"network\" 来查找\n" +
                    "// \"gps\"通过GPS卫星来定位 需要一定的时间花销来定位, APP 必须有权限android.Manifest.permission.ACCESS_FINE_LOCATION 才能使用\n" +
                    "//\"passive\" 即监听位置信息  APP实现监听接口  数据一来，相应的接口被调用 , 被动的监听位置数据,如果GPS未打开 则返回粗略的位置信息 需要权限 ACCESS_FINE_LOCATION\n" +
                    "// \"fused\" 此提供程序组合所有可能的位置源的输入提供最好的位置修正。 它是 LocationRequest 的 默认方式\n" +
                    "\n" +
                    "\n" +
                    "    public String toString() {\n" +
                    "        StringBuilder s = new StringBuilder();\n" +
                    "        s.append(\"Request[\").append(qualityToString(mQuality));  // 请求位置数据策略精度的类型  int mQuality\n" +
                    "        if (mProvider != null) s.append(' ').append(mProvider);   // 请求位置数据的来源 类型 String mProvider\n" +
                    "        if (mQuality != POWER_NONE) {\n" +
                    "            s.append(\" requested=\");\n" +
                    "            TimeUtils.formatDuration(mInterval, s);  //   请求位置数据的 间隔 默认 long mInterval = 60 * 60 * 1000;   // 60 minutes\n" +
                    "        }\n" +
                    "        s.append(\" fastest=\");\n" +
                    "        TimeUtils.formatDuration(mFastestInterval, s);  // 最快间隔  long mFastestInterval =  10 minutes  默认\n" +
                    "        if (mExpireAt != Long.MAX_VALUE) {\n" +
                    "            long expireIn = mExpireAt - SystemClock.elapsedRealtime();\n" +
                    "            s.append(\" expireIn=\");\n" +
                    "            TimeUtils.formatDuration(expireIn, s);  // expireIn 标识请求剩下的生命时间  ， long mExpireAt 请求失效的时间点 \n" +
                    "        }\n" +
                    "        if (mNumUpdates != Integer.MAX_VALUE) {\n" +
                    "            s.append(\" num=\").append(mNumUpdates);   // 请求次数 int mNumUpdates \n" +
                    "        }\n" +
                    "        if (mLowPowerMode) {\n" +
                    "            s.append(\" lowPowerMode\");  // 是否是低电量工作模式  boolean mLowPowerMode = false;  // 低电量工作模式\n" +
                    "        }\n" +
                    "        if (mLocationSettingsIgnored) {  // 是否是紧急状态 911 下等 忽略用户位置设置标识\n" +
                    "            s.append(\" locationSettingsIgnored\");  \n" +
                    "        }\n" +
                    "        s.append(']');\n" +
                    "        return s.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "****************** /frameworks/base/location/java/android/location/LocationRequest.java  End  ******************  \n" +
                    "\n" +
                    "******************  LocationManagerService.UpdateRecord Begin  ******************  \n" +
                    "private class UpdateRecord {\n" +
                    "\tfinal String mProvider;  //  对应 监听位置数据的来源 (gps network passive 等等 )\n" +
                    "\tprivate final LocationRequest mRealRequest;  // 来自APP的原始的位置请求 original request from client\n" +
                    "\tLocationRequest mRequest;  // 用于节流模式下位置请求 possibly throttled version of the request\n" +
                    "\tprivate final Receiver mReceiver;   // UpdateRecord 和 Receiver 相互引用 \n" +
                    "\tprivate boolean mIsForegroundUid;   // 当前的APP是否是在前台运行的APP\n" +
                    "\tprivate Location mLastFixBroadcast;   // 最新的可能需要广播的 Location 位置 元数据 \n" +
                    "\tprivate long mLastStatusBroadcast;   //  最新最近的更新位置信息的时间戳\n" +
                    "\tprivate Throwable mStackTrace;  // for debugging only\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t        @Override\n" +
                    "        public String toString() {\n" +
                    "            StringBuilder b = new StringBuilder(\"UpdateRecord[\");\n" +
                    "            b.append(mProvider).append(\" \"); //  对应 监听位置数据的来源 (gps network passive 等等 )\n" +
                    "\t\t\t // 监听位置信息的包名  如 com.google.android.gms \n" +
                    "            b.append(mReceiver.mCallerIdentity.mPackageName); \n" +
                    "\t\t\t// 监听位置信息的包APP 对应的 UID 编号   如 com.google.android.gms(10175 )\n" +
                    "            b.append(\"(\").append(mReceiver.mCallerIdentity.mUid);\n" +
                    "            if (mIsForegroundUid) {  // 标识APP应用是否在前台 \n" +
                    "                b.append(\" foreground\");\n" +
                    "            } else {\n" +
                    "                b.append(\" background\");\n" +
                    "            }\n" +
                    "            b.append(\") \");\n" +
                    "\t\t\t// 打印  来自APP的位置请求 LocationRequest  // 打印  WorkSource mWorkSource; 一般为空 null\n" +
                    "            b.append(mRealRequest).append(\" \").append(mReceiver.mWorkSource);  \n" +
                    " // 堆栈是否开启标识 决定 mStackTrace 是否为空  boolean D = Log.isLoggable(TAG【\"LocationManagerService\"】, Log.DEBUG);\n" +
                    "// public static native boolean isLoggable(@Nullable String tag, @Level int level);  //  Whether or not that this is allowed to be logged.\n" +
                    "// You can also create a local.prop file that with the following in it:'log.tag.&lt;YOUR_LOG_TAG>=&lt;LEVEL>'and place that in /data/local.prop.\n" +
                    "//  getprop | grep log.tag            \n" +
                    "// [log.tag.APM_AudioPolicyManager]: [D]\n" +
                    "// [log.tag.WifiHAL]: [D]\n" +
                    "// [log.tag.stats_log]: [I]\n" +
                    "//  可以设置   log.tag.LocationManagerService = D  来打开Debug  , \n" +
                    "// adb shell setprop log.tag.LocationManagerService D\n" +
                    " if (mStackTrace != null) { \n" +
                    "                ByteArrayOutputStream tmp = new ByteArrayOutputStream();\n" +
                    "                mStackTrace.printStackTrace(new PrintStream(tmp));\n" +
                    "                b.append(\"\\n\\n\").append(tmp.toString()).append(\"\\n\");  // 打印堆栈信息\n" +
                    "            }\n" +
                    "\n" +
                    "            b.append(\"]\");\n" +
                    "            return b.toString();\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "\t}\n" +
                    "\t\t\n" +
                    "\n" +
                    "******************  LocationManagerService.UpdateRecord End  ******************  \n" +
                    "\n" +
                    "******************  LocationManagerService.dump  Begin ******************\n" +
                    "\n" +
                    "\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// key 是  IBinder(Object)   \n" +
                    "// Value 是 Receiver 【 继承自 LinkedListenerBase (抽象类) , 实现接口PendingIntent.OnFinished , IBinder.DeathRecipient    】\n" +
                    "// class Receiver extends LinkedListenerBase implements PendingIntent.OnFinished {}\n" +
                    "//  abstract static class LinkedListenerBase implements IBinder.DeathRecipient {}\n" +
                    "// 当前对地址数据进行请求的那些APP的Bean 即 Receiver   \n" +
                    " HashMap<Object, Receiver> mReceivers = new HashMap<>();\n" +
                    "\t\n" +
                    "protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\t\t.....\n" +
                    "\t\tpw.println(\"  Location Listeners:\");\n" +
                    "\t\tfor (Receiver receiver : mReceivers.values()) {\n" +
                    "\t\t\tpw.println(\"    \" + receiver);\n" +
                    "\t\t}\n" +
                    "\t\t.....\n" +
                    "\t}\n" +
                    "******************  LocationManagerService.dump  Begin ******************\n" +
                    "\n" +
                    "******************  LocationManagerService.Receiver Begin ******************\n" +
                    "\n" +
                    "private final class Receiver extends LinkedListenerBase implements PendingIntent.OnFinished {\n" +
                    "private static final long WAKELOCK_TIMEOUT_MILLIS = 60 * 1000;    // 锁超时时间?  60秒\n" +
                    "\t\n" +
                    "private final int mAllowedResolutionLevel;  // APP请求数据的精细程度resolution level allowed to receiver\n" +
                    "final int RESOLUTION_LEVEL_NONE = 0;     // 不需要位置数据  Location resolution level: no location data whatsoever\n" +
                    "final int RESOLUTION_LEVEL_COARSE = 1;     //粗略的位置数据  Location resolution level: coarse location data only\n" +
                    "final int RESOLUTION_LEVEL_FINE = 2;     //详细的位置数据   Location resolution level: fine location data\n" +
                    "\n" +
                    "// 对应 /frameworks/base/location/java/android/location/ILocationListener.aidl  APP自身需要实现的监听接口 跨进程\n" +
                    "     final ILocationListener mListener;   // APP 自身需要实现的, 用于接收地址数据\n" +
                    "\n" +
                    "\tfinal PendingIntent mPendingIntent;  //  通过 PendingIntent 通知对应的APP 接收数据  \n" +
                    "\tfinal WorkSource mWorkSource; // 未知? WorkSource for battery blame, or null to assign to caller.\n" +
                    "\t\n" +
                    "\t//如果对应的APP不应该接收位置数据 那么为true  True if AppOps should not monitor this receiver.\n" +
                    "\tprivate final boolean mHideFromAppOps; \n" +
                    "\t\n" +
                    "\tprivate final Object mKey;  // 是一个APP 对端的 IBinder \n" +
                    "\n" +
                    "\tfinal HashMap<String, UpdateRecord> mUpdateRecords = new HashMap<>();\n" +
                    "\n" +
                    "\t// True if app ops has started monitoring this receiver for locations.\n" +
                    "\tprivate boolean mOpMonitoring;  // 如果对应APP 开始监听位置数据  那么值为 true\n" +
                    "\t// True if app ops has started monitoring this receiver for high power (gps) locations.\n" +
                    "\tprivate boolean mOpHighPowerMonitoring; // 如果对应APP 开始监听GPS位置数据  那么值为 true\n" +
                    "\tprivate int mPendingBroadcasts;  // 继续发送 PendingIntent的广播的个数\n" +
                    "\tPowerManager.WakeLock mWakeLock;  // 电源锁\n" +
                    "\t\n" +
                    "\tpublic String toString() {\n" +
                    "            StringBuilder s = new StringBuilder();\n" +
                    "            s.append(\"Reciever[\");\n" +
                    "\t\t\t// // Receiver 对应的7位十六进制 db3de4a   28个比特位 4.5个字节\n" +
                    "            s.append(Integer.toHexString(System.identityHashCode(this)));  \n" +
                    "            if (mListener != null) {\n" +
                    "                s.append(\" listener\");   // 以 实现 ILocationListener.aidl接口的方式监听 位置数据 \n" +
                    "            } else {\n" +
                    "                s.append(\" intent\");  //  以 intent的方式 监听位置数据 \n" +
                    "            }\n" +
                    "\t\t\t// 打印 HashMap的 Value  HashMap<String, UpdateRecord>   key是什么? \n" +
                    "\t\t\t// LocationManagerService.UpdateRecord ==   private class UpdateRecord {} \n" +
                    "            for (String p : mUpdateRecords.keySet()) {\n" +
                    "                s.append(\" \").append(mUpdateRecords.get(p).toString());\n" +
                    "            }\n" +
                    "            s.append(\" monitoring location: \").append(mOpMonitoring);\n" +
                    "            s.append(\"]\");\n" +
                    "            return s.toString();\n" +
                    "        }\n" +
                    "\t\t\n" +
                    "\t}\n" +
                    "******************  LocationManagerService.Receiver End ******************\n" +
                    "\n" +
                    "Reciever[ db3de4a = 7位16进制 = 4.5字节 = 28个比特位 = Reciever的hashCode\n" +
                    "listener = Reciever 的 ILocationListener mListener 不为空  这是一个被动监听位置数据的\"passive\" ProvideeType的 APP 实现接口ILocationListener\n" +
                    "intent = Reciever 的PendingIntent mPendingIntent 不为空,这是一个发送广播通知APP数据的APP类型\n" +
                    "// \"network\"  通过WIFI和信号搭来确定的位置  位置结果通过  \"network\" 来查找\n" +
                    "// \"gps\"通过GPS卫星来定位 需要一定的时间花销来定位, APP 必须有权限android.Manifest.permission.ACCESS_FINE_LOCATION 才能使用\n" +
                    "//\"passive\" 即监听位置信息  APP实现监听接口  数据一来，相应的接口被调用 , 被动的监听位置数据,如果GPS未打开 则返回粗略的位置信息 需要权限 ACCESS_FINE_LOCATION\n" +
                    "// \"fused\" 此提供程序组合所有可能的位置源的输入提供最好的位置修正。 它是 LocationRequest 的 默认方式\n" +
                    "UpdateRecord[数据源【 \"network\",\"gps\",\"passive\",\"fused\" 】,监听获取位置数据的包名( 包的UID ,foreground-APP在前台 background-APP在后台  )  ]\n" +
                    "\n" +
                    "\n" +
                    "static final int ACCURACY_FINE = 100;  // 标识 请求最精确的位置数据请求类型  1米精度 \n" +
                    "static final int ACCURACY_BLOCK = 102;  // 100 米精度  \n" +
                    "static final int ACCURACY_CITY = 104;   // 请求精确的城市地质 10km 精度\n" +
                    "static final int POWER_NONE = 200;   // 无需关注 电量的请求  一般用于 \"passive\" 监听位置数据的请求\n" +
                    "static final int POWER_LOW = 201;  //  省电模式请求  避免大功率工作\n" +
                    "static final int POWER_HIGH = 203; //  高电量下才允许的请求   允许使用大功率工作\n" +
                    "Request[ LocationRequest.【 int mQuality 】, 【String mProvider,(\"network\",\"gps\",\"passive\",\"fused\" )】,fastest = long expireIn 标识请求剩下的生命时间 \n" +
                    "\n" +
                    "null = 标识WorkSource为空  Receiver.<String, UpdateRecord> =》 UpdateRecord.mReceiver.mWorkSource  \n" +
                    "monitoring location: true | false 标识:Receiver.boolean mOpMonitoring;//如果对应APP开始监听位置数据那么值为true\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "Reciever[db3de4a listener UpdateRecord[passive com.google.android.gms(10175 foreground) \n" +
                    "Request[POWER_NONE passive fastest=0] null] monitoring location: false]\n" +
                    "\n" +
                    "Reciever[2db5bcf listener UpdateRecord[passive android(1000 foreground) \n" +
                    "Request[POWER_NONE passive fastest=+30m0s0ms] null] monitoring location: true]";
            keyWordList.add(location_1_5);


            KeyWordItem location_1_6 = new KeyWordItem();
            location_1_6.keyword = "  Active Records by Provider:";
            location_1_6.explain="以 【 \"network\",\"gps\",\"passive\",\"fused\" 】 不同分类 来保存对应的 UpdateRecord集合   ";
            location_1_6.classNameForShuxing = " LocationManagerService.HashMap<String, ArrayList<UpdateRecord>> mRecordsByProvider = new HashMap<>();";
            location_1_6.printLogUrl="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    " // Key来自【 \"network\",\"gps\",\"passive\",\"fused\" 】 , 以不同类型来保存 对应的 UpdateRecord 的集合\n" +
                    " // UpdateRecord 以有分析 \n" +
                    "HashMap<String, ArrayList<UpdateRecord>> mRecordsByProvider = new HashMap<>();\n" +
                    "......\n" +
                    "\tpw.println(\"  Active Records by Provider:\");\n" +
                    "\tfor (Map.Entry<String, ArrayList<UpdateRecord>> entry : mRecordsByProvider.entrySet()) {\n" +
                    "\t\tpw.println(\"    \" + entry.getKey() + \":\");\n" +
                    "\t\tfor (UpdateRecord record : entry.getValue()) {\n" +
                    "\t\t\tpw.println(\"      \" + record);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "......\n" +
                    "}";
            location_1_6.expain1="******************  LocationManagerService.UpdateRecord Begin  ******************  \n" +
                    "private class UpdateRecord {\n" +
                    "\tfinal String mProvider;  //  对应 监听位置数据的来源 (gps network passive 等等 )\n" +
                    "\tprivate final LocationRequest mRealRequest;  // 来自APP的原始的位置请求 original request from client\n" +
                    "\tLocationRequest mRequest;  // 用于节流模式下位置请求 possibly throttled version of the request\n" +
                    "\tprivate final Receiver mReceiver;   // UpdateRecord 和 Receiver 相互引用 \n" +
                    "\tprivate boolean mIsForegroundUid;   // 当前的APP是否是在前台运行的APP\n" +
                    "\tprivate Location mLastFixBroadcast;   // 最新的可能需要广播的 Location 位置 元数据 \n" +
                    "\tprivate long mLastStatusBroadcast;   //  最新最近的更新位置信息的时间戳\n" +
                    "\tprivate Throwable mStackTrace;  // for debugging only\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t        @Override\n" +
                    "        public String toString() {\n" +
                    "            StringBuilder b = new StringBuilder(\"UpdateRecord[\");\n" +
                    "            b.append(mProvider).append(\" \"); //  对应 监听位置数据的来源 (gps network passive 等等 )\n" +
                    "\t\t\t // 监听位置信息的包名  如 com.google.android.gms \n" +
                    "            b.append(mReceiver.mCallerIdentity.mPackageName); \n" +
                    "\t\t\t// 监听位置信息的包APP 对应的 UID 编号   如 com.google.android.gms(10175 )\n" +
                    "            b.append(\"(\").append(mReceiver.mCallerIdentity.mUid);\n" +
                    "            if (mIsForegroundUid) {  // 标识APP应用是否在前台 \n" +
                    "                b.append(\" foreground\");\n" +
                    "            } else {\n" +
                    "                b.append(\" background\");\n" +
                    "            }\n" +
                    "            b.append(\") \");\n" +
                    "\t\t\t// 打印  来自APP的位置请求 LocationRequest  // 打印  WorkSource mWorkSource; 一般为空 null\n" +
                    "            b.append(mRealRequest).append(\" \").append(mReceiver.mWorkSource);  \n" +
                    " // 堆栈是否开启标识 决定 mStackTrace 是否为空  boolean D = Log.isLoggable(TAG【\"LocationManagerService\"】, Log.DEBUG);\n" +
                    "// public static native boolean isLoggable(@Nullable String tag, @Level int level);  //  Whether or not that this is allowed to be logged.\n" +
                    "// You can also create a local.prop file that with the following in it:'log.tag.&lt;YOUR_LOG_TAG>=&lt;LEVEL>'and place that in /data/local.prop.\n" +
                    "//  getprop | grep log.tag            \n" +
                    "// [log.tag.APM_AudioPolicyManager]: [D]\n" +
                    "// [log.tag.WifiHAL]: [D]\n" +
                    "// [log.tag.stats_log]: [I]\n" +
                    "//  可以设置   log.tag.LocationManagerService = D  来打开Debug  , \n" +
                    "// adb shell setprop log.tag.LocationManagerService D\n" +
                    " if (mStackTrace != null) { \n" +
                    "                ByteArrayOutputStream tmp = new ByteArrayOutputStream();\n" +
                    "                mStackTrace.printStackTrace(new PrintStream(tmp));\n" +
                    "                b.append(\"\\n\\n\").append(tmp.toString()).append(\"\\n\");  // 打印堆栈信息\n" +
                    "            }\n" +
                    "\n" +
                    "            b.append(\"]\");\n" +
                    "            return b.toString();\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "\t}\n" +
                    "\t\t\n" +
                    "\n" +
                    "******************  LocationManagerService.UpdateRecord End  ****************** \n" +
                    "// \"network\"  通过WIFI和信号搭来确定的位置  位置结果通过  \"network\" 来查找\n" +
                    "// \"gps\"通过GPS卫星来定位 需要一定的时间花销来定位, APP 必须有权限android.Manifest.permission.ACCESS_FINE_LOCATION 才能使用\n" +
                    "//\"passive\" 即监听位置信息  APP实现监听接口  数据一来，相应的接口被调用 , 被动的监听位置数据,如果GPS未打开 则返回粗略的位置信息 需要权限 ACCESS_FINE_LOCATION\n" +
                    "// \"fused\" 此提供程序组合所有可能的位置源的输入提供最好的位置修正。 它是 LocationRequest 的 默认方式\n" +
                    "UpdateRecord[数据源【 \"network\",\"gps\",\"passive\",\"fused\" 】,监听获取位置数据的包名( 包的UID ,foreground-APP在前台 background-APP在后台  )  ]\n" +
                    "\n" +
                    "\n" +
                    "static final int ACCURACY_FINE = 100;  // 标识 请求最精确的位置数据请求类型  1米精度 \n" +
                    "static final int ACCURACY_BLOCK = 102;  // 100 米精度  \n" +
                    "static final int ACCURACY_CITY = 104;   // 请求精确的城市地质 10km 精度\n" +
                    "static final int POWER_NONE = 200;   // 无需关注 电量的请求  一般用于 \"passive\" 监听位置数据的请求\n" +
                    "static final int POWER_LOW = 201;  //  省电模式请求  避免大功率工作\n" +
                    "static final int POWER_HIGH = 203; //  高电量下才允许的请求   允许使用大功率工作\n" +
                    "Request[ LocationRequest.【 int mQuality 】, 【String mProvider,(\"network\",\"gps\",\"passive\",\"fused\" )】,fastest = long expireIn 标识请求剩下的生命时间 \n" +
                    "\n" +
                    "null = 标识WorkSource为空  Receiver.<String, UpdateRecord> =》 UpdateRecord.mReceiver.mWorkSource  \n" +
                    "monitoring location: true | false 标识:Receiver.boolean mOpMonitoring;//如果对应APP开始监听位置数据那么值为true\n" +
                    "\n" +
                    "Log示例:\n" +
                    " UpdateRecord[passive android(1000 foreground) Request[POWER_NONE passive fastest=0] null]";
            keyWordList.add(location_1_6);


            KeyWordItem location_1_7 = new KeyWordItem();
            location_1_7.keyword = " Active GnssMeasurement Listeners:";
            location_1_7.explain="打印 实现接口 IGnssMeasurementsListener.aidl 并注册请求位置数据的 APP的信息  ";
            location_1_7.classNameForShuxing = " LocationManagerService.ArrayMap<IBinder, LinkedListener<IGnssMeasurementsListener>> mGnssMeasurementsListeners = new ArrayMap<>();\n";
            location_1_7.printLogUrl=" LocationManagerService.dump() => dumpGnssDataListenersLocked(pw, mGnssMeasurementsListeners); ";
            location_1_7.expain1="****************** IGnssMeasurementsListener.aidl Begin ******************\n" +
                    "/frameworks/base/location/java/android/location/IGnssMeasurementsListener.aidl\n" +
                    "APP实现该接口并注册 那么就能检测到全球导航系统给设备下发的数据\n" +
                    "\n" +
                    "oneway interface IGnssMeasurementsListener {\n" +
                    "    void onGnssMeasurementsReceived(in GnssMeasurementsEvent event);\n" +
                    "    void onStatusChanged(in int status);\n" +
                    "}\n" +
                    "\n" +
                    "****************** IGnssMeasurementsListener.aidl End ******************\n" +
                    "\n" +
                    "\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "ArrayMap<IBinder, LinkedListener<IGnssMeasurementsListener>> mGnssMeasurementsListeners = new ArrayMap<>();\n" +
                    "......\n" +
                    "\tpw.println(\"  Active GnssMeasurement Listeners:\");\n" +
                    "\tdumpGnssDataListenersLocked(pw, mGnssMeasurementsListeners);\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "private void dumpGnssDataListenersLocked(PrintWriter pw,\n" +
                    "\t\tArrayMap<IBinder, ? extends LinkedListenerBase> gnssDataListeners) {\n" +
                    "\t\t// 获取实现了 IGnssMeasurementsListener 接口的APP 并打印出他们的信息\n" +
                    "\tfor (LinkedListenerBase listener : gnssDataListeners.values()) { \n" +
                    "\t\tCallerIdentity callerIdentity = listener.mCallerIdentity;\n" +
                    "\t\t// PID-进程ID   UID-APP的用户ID   APP_NAME-包名  该APP是否包含在位置白名单APP列表中(是否权限高)\n" +
                    "\t\tpw.println(\"    \" + callerIdentity.mPid + \" \" + callerIdentity.mUid + \" \"\n" +
                    "\t\t  + callerIdentity.mPackageName + \": \" + isThrottlingExemptLocked(callerIdentity));\n" +
                    "\t}\n" +
                    "}\n" +
                    "LOG示例: 实现 IGnssMeasurementsListener.aidl 接口 的 APP 信息\n" +
                    "1.PID-进程ID    2.UID-APP的用户ID     3.APP_NAME-包名    4.该APP是否包含在位置白名单APP列表中(是否权限高)";
            keyWordList.add(location_1_7);


            KeyWordItem location_1_8 = new KeyWordItem();
            location_1_8.keyword = "  Active GnssNavigationMessage Listeners:";
            location_1_8.explain="打印 实现接口 IGnssNavigationMessageListener.aidl 并注册请求位置数据的 APP的信息    ";
            location_1_8.classNameForShuxing = "LocationManagerService.ArrayMap<IBinder, LinkedListener<IGnssNavigationMessageListener>> mGnssNavigationMessageListeners ;\n ";
            location_1_8.printLogUrl="LocationManagerService.dump() => dumpGnssDataListenersLocked(pw, mGnssNavigationMessageListeners);";
            location_1_8.expain1="****************** IGnssNavigationMessageListener.aidl Begin ******************\n" +
                    "/frameworks/base/location/java/android/location/IGnssNavigationMessageListener.aidl\n" +
                    "APP实现该接口并注册 那么就能检测到全球导航消息系统给设备下发的数据\n" +
                    "\n" +
                    "oneway interface IGnssNavigationMessageListener {\n" +
                    "    void onGnssNavigationMessageReceived(in GnssNavigationMessage event);\n" +
                    "    void onStatusChanged(in int status);\n" +
                    "}\n" +
                    "\n" +
                    "****************** IGnssNavigationMessageListener.aidl End ******************\n" +
                    "\n" +
                    "\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "ArrayMap<IBinder, LinkedListener<IGnssNavigationMessageListener>> mGnssNavigationMessageListeners = new ArrayMap<>();\n" +
                    "......\n" +
                    "\tpw.println(\"  Active GnssNavigationMessage Listeners:\");\n" +
                    "\tdumpGnssDataListenersLocked(pw, mGnssNavigationMessageListeners);\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "private void dumpGnssDataListenersLocked(PrintWriter pw,\n" +
                    "\t\tArrayMap<IBinder, ? extends LinkedListenerBase> gnssDataListeners) {\n" +
                    "\t\t// 获取实现了 IGnssNavigationMessageListener 接口的APP 并打印出他们的信息\n" +
                    "\tfor (LinkedListenerBase listener : gnssDataListeners.values()) { \n" +
                    "\t\tCallerIdentity callerIdentity = listener.mCallerIdentity;\n" +
                    "\t\t// PID-进程ID   UID-APP的用户ID   APP_NAME-包名  该APP是否包含在位置白名单APP列表中(是否权限高)\n" +
                    "\t\tpw.println(\"    \" + callerIdentity.mPid + \" \" + callerIdentity.mUid + \" \"\n" +
                    "\t\t  + callerIdentity.mPackageName + \": \" + isThrottlingExemptLocked(callerIdentity));\n" +
                    "\t}\n" +
                    "}\n" +
                    "LOG示例: 实现 IGnssNavigationMessageListener.aidl 接口 的 APP 信息\n" +
                    "1.PID-进程ID    2.UID-APP的用户ID     3.APP_NAME-包名    4.该APP是否包含在位置白名单APP列表中(是否权限高)";
            keyWordList.add(location_1_8);




            KeyWordItem location_1_9 = new KeyWordItem();
            location_1_9.keyword = "  Active GnssStatus Listeners:";
            location_1_9.explain="打印 实现接口 IGnssStatusListener.aidl 用来监听 导航系统状态变化的信息   ";
            location_1_9.classNameForShuxing = "LocationManagerService.ArrayMap<IBinder, LinkedListener<IGnssStatusListener>> mGnssStatusListeners ; ";
            location_1_9.printLogUrl="LocationManagerService.dump() => dumpGnssDataListenersLocked(pw, mGnssStatusListeners);";
            location_1_9.expain1="****************** IGnssStatusListener.aidl Begin ******************\n" +
                    "/frameworks/base/location/java/android/location/IGnssStatusListener.aidl\n" +
                    "APP实现该接口并注册 那么就能检测到 全球导航状态变化的消息 给APP下发的数据\n" +
                    "\n" +
                    "oneway interface IGnssStatusListener\n" +
                    "{\n" +
                    "void onGnssStarted();\n" +
                    "void onGnssStopped();\n" +
                    "void onFirstFix(int ttff);\n" +
                    "void onSvStatusChanged(int svCount, in int[] svidWithFlags, in float[] cn0s, in float[] elevations, in float[] azimuths,  in float[] carrierFreqs);\n" +
                    "void onNmeaReceived(long timestamp, String nmea);\n" +
                    "}\n" +
                    "\n" +
                    "****************** IGnssStatusListener.aidl End ******************\n" +
                    "\n" +
                    "\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "ArrayMap<IBinder, LinkedListener<IGnssStatusListener>> mGnssStatusListeners = new ArrayMap<>();\n" +
                    "......\n" +
                    "\tpw.println(\"  Active GnssStatus Listeners:\");\n" +
                    "\tdumpGnssDataListenersLocked(pw, mGnssStatusListeners);\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "private void dumpGnssDataListenersLocked(PrintWriter pw,\n" +
                    "\t\tArrayMap<IBinder, ? extends LinkedListenerBase> gnssDataListeners) {\n" +
                    "\t\t// 获取实现了 IGnssStatusListener 接口的APP 并打印出他们的信息\n" +
                    "\tfor (LinkedListenerBase listener : gnssDataListeners.values()) { \n" +
                    "\t\tCallerIdentity callerIdentity = listener.mCallerIdentity;\n" +
                    "\t\t// PID-进程ID   UID-APP的用户ID   APP_NAME-包名  该APP是否包含在位置白名单APP列表中(是否权限高)\n" +
                    "\t\tpw.println(\"    \" + callerIdentity.mPid + \" \" + callerIdentity.mUid + \" \"\n" +
                    "\t\t  + callerIdentity.mPackageName + \": \" + isThrottlingExemptLocked(callerIdentity));\n" +
                    "\t}\n" +
                    "}\n" +
                    "LOG示例: 实现 IGnssStatusListener.aidl 接口 的 APP 信息\n" +
                    "1.PID-进程ID    2.UID-APP的用户ID     3.APP_NAME-包名    4.该APP是否包含在位置白名单APP列表中(是否权限高)";
            keyWordList.add(location_1_9);



            KeyWordItem location_1_10 = new KeyWordItem();
            location_1_10.keyword = "  Historical Records by Provider:";
            location_1_10.explain="Location Provider的请求记录  ";
            location_1_10.classNameForShuxing = " LocationManagerService.LocationRequestStatistics.HashMap<PackageProviderKey, PackageStatistics>";
            location_1_10.printLogUrl="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    " LocationRequestStatistics mRequestStatistics = new LocationRequestStatistics(); // 位置请求统计类\n" +
                    " \n" +
                    "void dump(){\n" +
                    "......\n" +
                    " pw.println(\"  Historical Records by Provider:\");\n" +
                    "for (Map.Entry<PackageProviderKey, PackageStatistics> entry : mRequestStatistics.statistics.entrySet()) {\n" +
                    "\tPackageProviderKey key = entry.getKey();\n" +
                    "\tPackageStatistics stats = entry.getValue();\n" +
                    "\tpw.println(\"    \" + key.packageName + \": \" + key.providerName + \": \" + stats);  //  打印 请求统计记录\n" +
                    "}\n" +
                    "......\n" +
                    "}";
            location_1_10.expain1="********************  LocationRequestStatistics Begin  ********************\n" +
                    "\n" +
                    "public class LocationRequestStatistics {\n" +
                    "\n" +
                    "    // Maps package name and provider to location request statistics.   // 包与请求的MAP集合统计信息\n" +
                    "    public final HashMap<PackageProviderKey, PackageStatistics> statistics  = new HashMap<PackageProviderKey, PackageStatistics>();\n" +
                    "\t\n" +
                    "static class PackageProviderKey { \n" +
                    "String packageName; //  包名\n" +
                    "String providerName; //  请求的provider类型 (\"network\",\"gps\",\"passive\",\"fused\" )\n" +
                    "} \n" +
                    "\t\n" +
                    " public static class PackageStatistics {\n" +
                    "   long mInitialElapsedTimeMs;  // 这个应用首次请求位置的时间戳  \n" +
                    "   int mNumActiveRequests;  // 请求次数  Number of active location requests this package currently has.\n" +
                    "   long mLastActivitationElapsedTimeMs; 记录当前APP开始请求位置并得到回馈的时间戳//  Time when this package most recently went from not requesting location to requesting.\n" +
                    "   long mFastestIntervalMs;  // 位置请求之间的最短  最快时间间隔\n" +
                    "   long mSlowestIntervalMs; // 位置请求之间的最大 最慢 时间间隔 The slowest interval this package has ever requested.\n" +
                    "   long mTotalDurationMs;  // 请求位置花费总的时间 The total time this app has requested location\n" +
                    "   long mLastForegroundElapsedTimeMs; // APP 最近一次进入前台请求位置的时间戳  ，0 标识当前APP 不在前台\n" +
                    "   long mForegroundDurationMs;  // 当前APP在前台请求位置的时间长度\n" +
                    "   long mLastStopElapsedTimeMs;  // APP 最近停止请求位置信息的时间戳\n" +
                    "}\n" +
                    "\n" +
                    "public long getDurationMs() {\n" +
                    "\tlong currentDurationMs = mTotalDurationMs;\n" +
                    "\tif (mNumActiveRequests > 0) {\n" +
                    "\t\tcurrentDurationMs  += SystemClock.elapsedRealtime() - mLastActivitationElapsedTimeMs;\n" +
                    "\t}\n" +
                    "\treturn currentDurationMs;\n" +
                    "}\n" +
                    "\n" +
                    "\tpublic boolean isActive() {\n" +
                    "\t\treturn mNumActiveRequests > 0;\n" +
                    "\t}\n" +
                    "\t\n" +
                    "   public long getTimeSinceFirstRequestMs() {\n" +
                    "\treturn SystemClock.elapsedRealtime() - mInitialElapsedTimeMs;\n" +
                    "}\n" +
                    "\n" +
                    "\t\t\n" +
                    "public String toString() {\n" +
                    "\tStringBuilder s = new StringBuilder();\n" +
                    "\tif (mFastestIntervalMs == mSlowestIntervalMs) { \n" +
                    "\t // 如果 最大最慢间隔时间 和 最小最快间隔时间相等 打印 Interval 间隔时间 秒:\n" +
                    "\t\ts.append(\"Interval \").append(mFastestIntervalMs / 1000).append(\" seconds\");\n" +
                    "\t} else {\n" +
                    "\t// 打印最大间隔  最小间隔时间\n" +
                    "\t\ts.append(\"Min interval \").append(mFastestIntervalMs / 1000).append(\" seconds\");\n" +
                    "\t\ts.append(\": Max interval \").append(mSlowestIntervalMs / 1000).append(\" seconds\");\n" +
                    "\t}\n" +
                    "\ts.append(\": Duration requested \")\n" +
                    "\t\t\t.append((getDurationMs() / 1000) / 60)   // 打印 位置请求持续时间 \n" +
                    "\t\t\t.append(\" total, \")\n" +
                    "\t\t\t.append((getForegroundDurationMs() / 1000) / 60)  // 当前APP在前台请求位置的时间长度\n" +
                    "\t\t\t.append(\" foreground, out of the last \")\n" +
                    "\t\t\t.append((getTimeSinceFirstRequestMs() / 1000) / 60)  // APP离第一次请求位置差距时间 \n" +
                    "\t\t\t.append(\" minutes\");\n" +
                    "\tif (isActive()) {\n" +
                    "\t\ts.append(\": Currently active\");  // 当前请求还有效\n" +
                    "\t} else {\n" +
                    "\t\ts.append(\": Last active \")    // 当前请求已无效 \n" +
                    "\t\t\t\t.append((getTimeSinceLastRequestStoppedMs() / 1000) / 60)  // 该请求在 XX 分钟前 已失效\n" +
                    "\t\t\t\t.append(\" minutes ago\");\n" +
                    "\t}\n" +
                    "\treturn s.toString();\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "********************  LocationRequestStatistics End  ********************\n" +
                    "// \"network\"  通过WIFI和信号搭来确定的位置  位置结果通过  \"network\" 来查找\n" +
                    "// \"gps\"通过GPS卫星来定位 需要一定的时间花销来定位, APP 必须有权限android.Manifest.permission.ACCESS_FINE_LOCATION 才能使用\n" +
                    "//\"passive\" 即监听位置信息  APP实现监听接口  数据一来，相应的接口被调用 , 被动的监听位置数据,如果GPS未打开 则返回粗略的位置信息 需要权限 ACCESS_FINE_LOCATION\n" +
                    "// \"fused\" 此提供程序组合所有可能的位置源的输入提供最好的位置修正。 它是 LocationRequest 的 默认方式\n" +
                    "【provider-name】 =  \"network\",\"gps\",\"passive\",\"fused\"\n" +
                    "LOG示例:\n" +
                    "android 【包名】 : passive 【provider-name】 : Min interval 0 seconds 【最小间隔: 秒】 : Max interval 1800 seconds 【最大间隔 秒】: \n" +
                    "Duration requested 1532 total 【请求持续时间 分钟 】, 1532 foreground  【APP 前台请求位置的时间长度 分钟】,\n" +
                    " out of the last 1532 minutes 【 APP离第一次请求位置差距时间 】: Currently active 【APP仍然活跃】\n" +
                    "\n" +
                    "android 【包名】 : network 【provider-name】 : Interval 1 seconds 【最小最大间隔一样都是1秒】: \n" +
                    "Duration requested 16 total 【请求持续时间16分钟 】, 16 foreground 【APP 前台请求位置的时间长度 16分钟】,\n" +
                    " out of the last 1503 minutes 【 APP离第一次请求位置差距时间 】 : Last active 13 minutes ago 【APP在13分钟前活跃,现在不活跃】\n" +
                    "\n" +
                    "com.google.android.gms【包名】: passive 【provider-name】: Interval 0 seconds 【最小最大间隔一样都是0秒】: \n" +
                    "Duration requested 1532 total 【请求持续时间 分钟 】 , 1532 foreground  【APP 前台请求位置的时间长度 1532分钟】,\n" +
                    " out of the last 1532 minutes 【 APP离第一次请求位置差距时间 】 : Currently active 【APP仍然活跃】";
            keyWordList.add(location_1_10);



            KeyWordItem location_1_11 = new KeyWordItem();
            location_1_11.keyword = "  Last Known Locations:";
            location_1_11.explain="打印当前系统中记录的 Location以provider分类集合 ";
            location_1_11.classNameForShuxing = "LocationManagerService.HashMap<String, Location> mLastLocation ";
            location_1_11.printLogUrl="  \npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    " \n" +
                    "// key是[\"network\",\"gps\",\"passive\",\"fused\"] , value是位置信息 Location  \n" +
                    "//  mapping from provider name to last known location\n" +
                    "HashMap<String, Location> mLastLocation = new HashMap<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    ".......\n" +
                    "\tpw.println(\"  Last Known Locations:\");\n" +
                    "\tfor (Map.Entry<String, Location> entry : mLastLocation.entrySet()) {\n" +
                    "\t\tString provider = entry.getKey();\n" +
                    "\t\tLocation location = entry.getValue();\n" +
                    "\t\tpw.println(\"    \" + provider + \": \" + location);  // 打印当前记录的位置信息\n" +
                    "\t}\n" +
                    ".......\n" +
                    "}";
            location_1_11.expain1="************************ Location Begin  ************************\n" +
                    "/frameworks/base/location/java/android/location/Location.java\n" +
                    "\n" +
                    "\n" +
                    "public class Location implements Parcelable {\n" +
                    "static final int FORMAT_DEGREES = 0;  // 地球位置信息 地球是个圆的     D度M分S秒\n" +
                    "static final int FORMAT_MINUTES = 1;\n" +
                    "static final int FORMAT_SECONDS = 2;\n" +
                    "\n" +
                    "// Intent 中 Extra 中 夹带的数据的 key\n" +
                    "static final String EXTRA_COARSE_LOCATION = \"coarseLocation\";  // 粗略地址\n" +
                    "static final String EXTRA_NO_GPS_LOCATION = \"noGPSLocation\";   // 非 GPS地址\n" +
                    "\n" +
                    "int mFieldsMask = 0;  // 比特功能位  用于记录 地址信息\n" +
                    "final int HAS_ALTITUDE_MASK = 1;  // 2^0 = 1  第0位 标识是否记录 了 海拔信息\n" +
                    "final int HAS_SPEED_MASK = 2;   //   2^1 = 2    第1位 标识是否记录 了 速度信息\n" +
                    "final int HAS_BEARING_MASK = 4;//   2^2 = 4    第2位 标识是否记录 了 方位信息\n" +
                    "final int HAS_HORIZONTAL_ACCURACY_MASK = 8; //  2^3 = 8   第3 位 标识是否记录 了 水平精确度信息\n" +
                    "final int HAS_MOCK_PROVIDER_MASK = 16; //  2^4 = 16    第4 位 标识是位置信息是否来源 模拟提供者 mock provider\n" +
                    "final int HAS_VERTICAL_ACCURACY_MASK = 32;  //  2^5 = 32    第5 位 标识是否记录 了 垂直精确度信息\n" +
                    "final int HAS_SPEED_ACCURACY_MASK = 64; //  2^6 = 64    第6 位 标识是否记录 了 SpeedAccuracy 速度精确度信息\n" +
                    "final int HAS_BEARING_ACCURACY_MASK = 128;  //  2^7 = 128    第7 位 标识是否记录 了 方位精确度信息\n" +
                    "final int HAS_ELAPSED_REALTIME_UNCERTAINTY_MASK = 256;  //  2^8 = 256    第8 位 标识是否记录 时间值 纳秒\n" +
                    "\n" +
                    "// 方位距离? \n" +
                    "static ThreadLocal<BearingDistanceCache> sBearingDistanceCache = new ThreadLocal<BearingDistanceCache>()\n" +
                    "\t\t\t\n" +
                    "private String mProvider;  //  [ \"network\",\"gps\",\"passive\",\"fused\" ]\n" +
                    "private long mTime = 0;  // 时间戳 \n" +
                    "\n" +
                    "private long mElapsedRealtimeNanos = 0;  // 时间戳 纳秒\n" +
                    "private double mElapsedRealtimeUncertaintyNanos = 0.0f; \n" +
                    "private double mLatitude = 0.0;  //  维度\n" +
                    "private double mLongitude = 0.0;  // 精度\n" +
                    "private double mAltitude = 0.0f;  // 海拔\n" +
                    "private float mSpeed = 0.0f;      // 速度 \n" +
                    "private float mBearing = 0.0f;    //  方位 \n" +
                    "private float mHorizontalAccuracyMeters = 0.0f; //  水平精确度\n" +
                    "private float mVerticalAccuracyMeters = 0.0f; //  垂直精确度\n" +
                    "private float mSpeedAccuracyMetersPerSecond = 0.0f; //  速度精确度\n" +
                    "private float mBearingAccuracyDegrees = 0.0f;  //  方位 精确度\n" +
                    "private Bundle mExtras = null;   // 携带的Bundle Map信息\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "   public String toString() {\n" +
                    "        StringBuilder s = new StringBuilder();\n" +
                    "        s.append(\"Location[\");\n" +
                    "        s.append(mProvider);\n" +
                    "        s.append(String.format(\" %.6f,%.6f\", mLatitude, mLongitude));\n" +
                    "        if (hasAccuracy()) s.append(String.format(\" hAcc=%.0f\", mHorizontalAccuracyMeters));\n" +
                    "        else s.append(\" hAcc=???\");\n" +
                    "        if (mTime == 0) {\n" +
                    "            s.append(\" t=?!?\");\n" +
                    "        }\n" +
                    "        if (mElapsedRealtimeNanos == 0) {\n" +
                    "            s.append(\" et=?!?\");\n" +
                    "        } else {\n" +
                    "            s.append(\" et=\");\n" +
                    "            TimeUtils.formatDuration(mElapsedRealtimeNanos / 1000000L, s);\n" +
                    "        }\n" +
                    "        if (hasElapsedRealtimeUncertaintyNanos()) {\n" +
                    "            s.append(\" etAcc=\");\n" +
                    "            TimeUtils.formatDuration((long) (mElapsedRealtimeUncertaintyNanos / 1000000), s);\n" +
                    "        }\n" +
                    "        if (hasAltitude()) s.append(\" alt=\").append(mAltitude);\n" +
                    "        if (hasSpeed()) s.append(\" vel=\").append(mSpeed);\n" +
                    "        if (hasBearing()) s.append(\" bear=\").append(mBearing);\n" +
                    "        if (hasVerticalAccuracy()) s.append(String.format(\" vAcc=%.0f\", mVerticalAccuracyMeters));\n" +
                    "        else s.append(\" vAcc=???\");\n" +
                    "        if (hasSpeedAccuracy()) s.append(String.format(\" sAcc=%.0f\", mSpeedAccuracyMetersPerSecond));\n" +
                    "        else s.append(\" sAcc=???\");\n" +
                    "        if (hasBearingAccuracy()) s.append(String.format(\" bAcc=%.0f\", mBearingAccuracyDegrees));\n" +
                    "        else s.append(\" bAcc=???\");\n" +
                    "        if (isFromMockProvider()) s.append(\" mock\");\n" +
                    "        if (mExtras != null) {\n" +
                    "            s.append(\" {\").append(mExtras).append('}');\n" +
                    "        }\n" +
                    "        s.append(']');\n" +
                    "        return s.toString();\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "************************ Location End  ************************\n" +
                    "LOG示例:";
            keyWordList.add(location_1_11);


            KeyWordItem location_1_12 = new KeyWordItem();
            location_1_12.keyword = "  Geofences:";
            location_1_12.explain="// https://blog.csdn.net/btyh17mxy/article/details/9038443\n" +
                    "// Geofence是一个基于Google Play Services的虚拟地理区域，是一个由中心点经纬度和半径描述的圆形区域。\n" +
                    "// Location Service会以低功耗的方式获取用户的位置，当用户进入或退出Geofence范围时会通知应用，\n" +
                    "// 应用接受到通知后可采取相应的操作，例如在通知栏显示这样的通知：\n打印  GeofenceManager.dump()  ";
            location_1_12.classNameForShuxing = " LocationManagerService=》GeofenceManager mGeofenceManager";
            location_1_12.printLogUrl="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    " GeofenceManager mGeofenceManager;\n" +
                    " \n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "            if (mGeofenceManager != null) {\n" +
                    "                mGeofenceManager.dump(pw);  // 打印  GeofenceManager\n" +
                    "            } else {\n" +
                    "                pw.println(\"  Geofences: null\");\n" +
                    "            }\n" +
                    "......\n" +
                    "}";
            location_1_12.expain1="***************** GeofenceState Begin *****************\n" +
                    "public class GeofenceState {\n" +
                    "public final static int FLAG_ENTER = 0x01;\n" +
                    "public final static int FLAG_EXIT = 0x02;\n" +
                    "\n" +
                    "private static final int STATE_UNKNOWN = 0;\n" +
                    "private static final int STATE_INSIDE = 1;\n" +
                    "private static final int STATE_OUTSIDE = 2;\n" +
                    "\n" +
                    "public final Geofence mFence;   //  谷歌虚拟地理信息 \n" +
                    " final Location mLocation;  // 位置信息\n" +
                    "public final long mExpireAt;   //  时间戳 endLifeTime\n" +
                    "public final int mAllowedResolutionLevel;   // APP请求数据的精细程度resolution level allowed to receiver\n" +
                    "final int RESOLUTION_LEVEL_NONE = 0;     // 不需要位置数据  Location resolution level: no location data whatsoever\n" +
                    "final int RESOLUTION_LEVEL_COARSE = 1;     //粗略的位置数据  Location resolution level: coarse location data only\n" +
                    "final int RESOLUTION_LEVEL_FINE = 2;     //详细的位置数据   Location resolution level: fine location data\n" +
                    "\n" +
                    "\n" +
                    "public final int mUid;   // APP的UID\n" +
                    "public final String mPackageName;  // 包名 \n" +
                    "public final PendingIntent mIntent;  // 给 APP 发送消息的 Intent 包含 Location 信息\n" +
                    "\n" +
                    "int mState;  // current state 状态?  STATE_UNKNOWN=0=未知  STATE_INSIDE=1=室内?  STATE_OUTSIDE=2=户外?\n" +
                    "double mDistanceToCenter;  // 与中心距离  差距 米 current distance to center of fence\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "***************** GeofenceState End *****************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "***************** GeofenceManager Begin *****************\n" +
                    "public class GeofenceManager implements LocationListener, PendingIntent.OnFinished {\n" +
                    "private final Context mContext;\n" +
                    "private final LocationManager mLocationManager;\n" +
                    "private final AppOpsManager mAppOps;\n" +
                    "private final PowerManager.WakeLock mWakeLock;\n" +
                    "private final GeofenceHandler mHandler;\n" +
                    "private final LocationBlacklist mBlacklist;\n" +
                    "\n" +
                    "\n" +
                    "//GeofenceState=地理信息   地理信息列表 \n" +
                    "  private List<GeofenceState> mFences = new LinkedList<GeofenceState>();\n" +
                    "\n" +
                    "    public void dump(PrintWriter pw) {\n" +
                    "        pw.println(\"  Geofences:\");\n" +
                    "\n" +
                    "        for (GeofenceState state : mFences) {\n" +
                    "            pw.append(\"    \");\n" +
                    "            pw.append(state.mPackageName);  // 打印包名? \n" +
                    "            pw.append(\" \");\n" +
                    "            pw.append(state【Geofence】.mFence.toString());\n" +
                    "            pw.append(\"\\n\");\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    "\t\n" +
                    "***************** GeofenceManager End *****************\n" +
                    "\n" +
                    "************************ Geofence Begin  ************************\n" +
                    "public final class Geofence implements Parcelable {\n" +
                    "// https://blog.csdn.net/btyh17mxy/article/details/9038443\n" +
                    "// Geofence是一个基于Google Play Services的虚拟地理区域，是一个由中心点经纬度和半径描述的圆形区域。\n" +
                    "// Location Service会以低功耗的方式获取用户的位置，当用户进入或退出Geofence范围时会通知应用，\n" +
                    "// 应用接受到通知后可采取相应的操作，例如在通知栏显示这样的通知：\n" +
                    "\n" +
                    "    public static final int TYPE_HORIZONTAL_CIRCLE = 1;\n" +
                    "\n" +
                    "    private final int mType;  //  1=水平\n" +
                    "    private final double mLatitude;  // 纬度 \n" +
                    "    private final double mLongitude;  // 经度\n" +
                    "    private final float mRadius;   // 半径\n" +
                    "\n" +
                    "\tpublic String toString() {\n" +
                    "// 打印 Geofence[CIRCLE, 纬度,经度 半径(米)]\n" +
                    "\treturn String.format(\"Geofence[%s %.6f, %.6f %.0fm]\",\n" +
                    "\t\t\ttypeToString(mType), mLatitude, mLongitude, mRadius);\n" +
                    "}\n" +
                    "\t}\n" +
                    "\n" +
                    "************************ Geofence End  ************************";
            keyWordList.add(location_1_12);


            KeyWordItem location_1_13 = new KeyWordItem();
            location_1_13.keyword = " mBlacklist=";
            location_1_13.isContain = true;
            location_1_13.explain="class LocationBlacklist extends ContentObserver {}   请求位置信息的白名单 黑名单   ";
            location_1_13.classNameForShuxing = " LocationManagerService =》 LocationBlacklist.mBlacklist ";
            location_1_13.printLogUrl=" \npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    " LocationBlacklist mBlacklist;\n" +
                    " \n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "\tif (mBlacklist != null) {\n" +
                    "\t\tpw.append(\"  \");\n" +
                    "\t\tmBlacklist.dump(pw);   //   LocationBlacklist mBlacklist;\n" +
                    "\t} else {\n" +
                    "\t\tpw.println(\"  mBlacklist=null\");  //  mBlacklist=\n" +
                    "\t}\n" +
                    "......\n" +
                    "}";
            location_1_13.expain1="************************ LocationBlacklist Begin  ************************\n" +
                    "public final class LocationBlacklist extends ContentObserver {\n" +
                    "\n" +
                    "String BLACKLIST_CONFIG_NAME = \"locationPackagePrefixBlacklist\";\n" +
                    "String WHITELIST_CONFIG_NAME = \"locationPackagePrefixWhitelist\";\n" +
                    "\n" +
                    "mWhitelist = getStringArrayLocked(WHITELIST_CONFIG_NAME);\n" +
                    "mBlacklist = getStringArrayLocked(BLACKLIST_CONFIG_NAME);\n" +
                    "[黑名单]ListString = Settings.Secure.getStringForUser(mContext.getContentResolver(), \"locationPackagePrefixBlacklist\");\n" +
                    "[白名单]ListString = Settings.Secure.getStringForUser(mContext.getContentResolver(), \"locationPackagePrefixWhitelist\");\n" +
                    "\n" +
                    "// all fields below synchronized on mLock\n" +
                    " String[] mWhitelist = new String[0];\n" +
                    " String[] mBlacklist = new String[0];\n" +
                    "\n" +
                    "     public void dump(PrintWriter pw) {\n" +
                    "        pw.println(\"mWhitelist=\" + Arrays.toString(mWhitelist) +\n" +
                    " \t\t          \" mBlacklist=\" +  Arrays.toString(mBlacklist));\n" +
                    "    }\n" +
                    "}\n" +
                    "查看Settings.Secure.locationPackagePrefixBlacklist 的值:\n" +
                    "//  adb shell settings get secure locationPackagePrefixWhitelist     【白名单以 , 逗号分割】\n" +
                    "//  adb shell settings get secure locationPackagePrefixBlacklist     【黑名单以 , 逗号分割】\n" +
                    "************************ LocationBlacklist End  ************************";
            keyWordList.add(location_1_13);


            KeyWordItem location_1_14 = new KeyWordItem();
            location_1_14.keyword = " Location controller extra package:";
            location_1_14.explain="补充的位置服务包 APP 信息";
            location_1_14.classNameForShuxing = "String mExtraLocationControllerPackage;   //  补充的位置服务包 APP 名称\n" +
                    "boolean mExtraLocationControllerPackageEnabled;  //  补充的位置服务包 APP 是否使能 ";
            location_1_14.printLogUrl=" LocationManagerService.java void  dump(){} ";
            location_1_14.expain1="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "String mExtraLocationControllerPackage;   //  补充的位置服务包 APP 名称\n" +
                    "boolean mExtraLocationControllerPackageEnabled;  //  补充的位置服务包 APP 是否使能\n" +
                    "\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "if (mExtraLocationControllerPackage != null) {\n" +
                    "\tpw.println(\" Location controller extra package: \" + mExtraLocationControllerPackage\n" +
                    "\t\t\t+ \" enabled: \" + mExtraLocationControllerPackageEnabled);\n" +
                    "}\n" +
                    "......\n" +
                    "}";
            keyWordList.add(location_1_14);



            KeyWordItem location_1_15 = new KeyWordItem();
            location_1_15.keyword = "  Throttling Whitelisted Packages:";
            location_1_15.explain="后台能运行 但请求次数受限的 白名单APP名称 列表  ";
            location_1_15.classNameForShuxing = " ArraySet<String> mBackgroundThrottlePackageWhitelist ";
            location_1_15.printLogUrl="";
            location_1_15.expain1="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 后台能请求地址 但请求次数受限的白名单 \n" +
                    "ArraySet<String> mBackgroundThrottlePackageWhitelist = new ArraySet<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "if (!mBackgroundThrottlePackageWhitelist.isEmpty()) {\n" +
                    "\tpw.println(\"  Throttling Whitelisted Packages:\");\n" +
                    "\tfor (String packageName : mBackgroundThrottlePackageWhitelist) {\n" +
                    "\t\tpw.println(\"    \" + packageName);\n" +
                    "\t}\n" +
                    "}\n" +
                    "......\n" +
                    "}";
            keyWordList.add(location_1_15);


            KeyWordItem location_1_16 = new KeyWordItem();
            location_1_16.keyword = "  Bypass Whitelisted Packages:";
            location_1_16.explain="可以忽略用户位置设置的 不受用户设置Settings影响,正常使用Location的 APP的名单集合  ";
            location_1_16.classNameForShuxing = "LocationManagerService =》 ArraySet<String> mIgnoreSettingsPackageWhitelist = new ArraySet<>(); ";
            location_1_16.printLogUrl="";
            location_1_16.expain1="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 可以忽略用户位置设置的 不受用户设置Settings影响,正常使用Location的 APP的名单集合\n" +
                    "ArraySet<String> mIgnoreSettingsPackageWhitelist = new ArraySet<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "if (!mIgnoreSettingsPackageWhitelist.isEmpty()) {\n" +
                    "\tpw.println(\"  Bypass Whitelisted Packages:\");\n" +
                    "\tfor (String packageName : mIgnoreSettingsPackageWhitelist) {\n" +
                    "\t\tpw.println(\"    \" + packageName);\n" +
                    "\t}\n" +
                    "}\n" +
                    "......\n" +
                    "}\t\t\n";
            keyWordList.add(location_1_16);



            KeyWordItem location_1_17 = new KeyWordItem();
            location_1_17.keyword = "  fudger:";
            location_1_17.explain="位置偏移量 LocationManagerService -》 LocationFudger mLocationFudger;";
            location_1_17.classNameForShuxing = " public class LocationFudger {}  // 使位置 模糊的逻辑?   ";
            location_1_17.printLogUrl="\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 使位置 模糊的逻辑? \n" +
                    "LocationFudger mLocationFudger;\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "if (mLocationFudger != null) {\n" +
                    "\tpw.append(\"  fudger: \");\n" +
                    "\tmLocationFudger.dump(fd, pw, args);  \n" +
                    "} else {\n" +
                    "\tpw.println(\"  fudger: null\");\n" +
                    "}\n" +
                    "......\n" +
                    "}";
            location_1_17.expain1="*********************  LocationFudger Begin *********************\n" +
                    "\n" +
                    "public class LocationFudger {\n" +
                    "\n" +
                    "final float DEFAULT_ACCURACY_IN_METERS = 2000.0f;  // 2000米   默认的位置模糊程度\n" +
                    "final float MINIMUM_ACCURACY_IN_METERS = 200.0f; // 200米   最小的位置模糊程度\n" +
                    "\n" +
                    "//     adb shell settings get secure locationCoarseAccuracy \n" +
                    "final String COARSE_ACCURACY_CONFIG_NAME = \"locationCoarseAccuracy\";  // 设置项中保存的模糊程度\n" +
                    "final long FASTEST_INTERVAL_MS = 10 * 60 * 1000;  // 10 minutes  希望接收模糊数据的应用程序接收到模糊数据的时间间隔\n" +
                    "final long CHANGE_INTERVAL_MS = 60 * 60 * 1000;  // 1 hour   改变随机位置偏移的timeout时间 random offset \n" +
                    "\n" +
                    "// 0.0 indicates the random offset doesn't change. \n" +
                    "// 1.0 indicates the random offset is completely replaced every interval.\n" +
                    "final double CHANGE_PER_INTERVAL = 0.03;  // 3% change   每次改变随机偏移量的百分比\n" +
                    "\n" +
                    "final int APPROXIMATE_METERS_PER_DEGREE_AT_EQUATOR = 111000;  // 每个经度 维度 之间的距离  大约 111km\n" +
                    "\n" +
                    "double MAX_LATITUDE = 90.0 -  (1.0 / APPROXIMATE_METERS_PER_DEGREE_AT_EQUATOR);  // 最大的维度值\n" +
                    "\n" +
                    "double mOffsetLatitudeMeters;   // 维度偏移距离\n" +
                    "double mOffsetLongitudeMeters;  // 经度偏移距离\n" +
                    "\t\n" +
                    " \n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tpw.println(String.format(\"offset: %.0f, %.0f (meters)\", mOffsetLongitudeMeters,mOffsetLatitudeMeters));\n" +
                    "}\n" +
                    "}\n" +
                    "Log示例:\n" +
                    "offset: 1000[经度偏移位置], 500[维度偏移位置] (meters)\n" +
                    "\n" +
                    "*********************  LocationFudger End *********************";
            keyWordList.add(location_1_17);



            KeyWordItem location_1_18 = new KeyWordItem();
            location_1_18.keyword = "  passive provider:";
            location_1_18.explain=" 打印 passive provider 实现类 PassiveProvider 的一些信息   \n " +
                    "LocationManagerService =》ArrayList<LocationProvider> mProviders =》 Item【 LocationProvider 】.AbstractLocationProvider mProvider [ PassiveProvider ] \n" +
                    "   ";
            location_1_18.classNameForShuxing = "LocationManagerService =》ArrayList<LocationProvider> mProviders  中 的 LocationProvider 中 的 AbstractLocationProvider 抽象引用的实现类  PassiveProvider   ";
            location_1_18.printLogUrl="public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 是 LocationManagerService.LocationProvider 这个 内部类 \n" +
                    " ArrayList<LocationProvider> mProviders = new ArrayList<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "for (LocationProvider provider : mProviders) {\n" +
                    "\tprovider.dumpLocked(fd, pw, args);   // 打印  LocationProvider 的 dump方法\n" +
                    "}\n" +
                    "......\n" +
                    "}";
            location_1_18.expain1="*********************  ProviderProperties Begin *********************\n" +
                    " final class ProviderProperties implements Parcelable {\n" +
                    "public final boolean mRequiresNetwork;   // 是否需要网络\n" +
                    "public final boolean mRequiresSatellite;   // 是否需要卫星\n" +
                    "public final boolean mRequiresCell;        //  是否需要信号搭 信号塔ID 有具体位置\n" +
                    "public final boolean mHasMonetaryCost;   // 是否需要花费金钱  true-花费钱  false--免费\n" +
                    "public final boolean mSupportsAltitude;   // 是否支持海拔信息\n" +
                    "public final boolean mSupportsSpeed;   // 是否支持速度信息\n" +
                    "public final boolean mSupportsBearing;   // 是否支持方位信息\n" +
                    "public final int mAccuracy;   //  Criteria.ACCURACY_COARSE=2=低精度  or   Criteria.ACCURACY_FINE=1=高精度\n" +
                    " }\n" +
                    "*********************  ProviderProperties End *********************\n" +
                    "\n" +
                    "********************* abstract AbstractLocationProvider Begin *********************\n" +
                    "\n" +
                    "public abstract class AbstractLocationProvider {\n" +
                    "protected final Context mContext;  // 上下文\n" +
                    "final LocationProviderManager mLocationProviderManager;  // 位置管理服务\n" +
                    "\n" +
                    "    // 返回一个位置管理服务的 接口  具体实现位置功能的类去实现\n" +
                    "public interface LocationProviderManager {\n" +
                    "void onSetEnabled(boolean enabled);  // 服务开关\n" +
                    "void onSetProperties(ProviderProperties properties); // 用于通知位置服务信息变化 ProviderProperties保存变化的信息\n" +
                    "void onReportLocation(Location location);  // Provider 报告了一个新的位置\n" +
                    "void onReportLocation(List<Location> locations);// Provider 报告了一个新的位置列表\n" +
                    "}\n" +
                    "}\n" +
                    "\n" +
                    "********************* abstract  AbstractLocationProvider End *********************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "*********************  LocationManagerService.LocationProvider Begin *********************\n" +
                    "private class LocationProvider implements AbstractLocationProvider.LocationProviderManager {\n" +
                    "\n" +
                    "final String mName;  // provider的名称  (\"network\",\"gps\",\"passive\",\"fused\" )之一\n" +
                    "\n" +
                    "final boolean mIsManagedBySettings;  // 是否由 SettingAPK 管理 是否需要请求权限LOCATION_PROVIDERS_ALLOWED\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "private boolean mUseable;  // 是否可用  combined state\n" +
                    "private boolean mAllowed;  // 是否得到了用户授权 点击确认 state of LOCATION_PROVIDERS_ALLOWED\n" +
                    "private boolean mEnabled;  //  是否使能 state of provider\n" +
                    "\n" +
                    "// remember to clear binder identity before invoking any provider operation\n" +
                    "// class PassiveProvider extends AbstractLocationProvider {}\n" +
                    "// class MockProvider extends AbstractLocationProvider {}\n" +
                    "// class LocationProviderProxy extends AbstractLocationProvider {}\n" +
                    "// class GnssLocationProvider extends AbstractLocationProvider implements {}\n" +
                    "\n" +
                    "@Nullable protected AbstractLocationProvider mProvider;   //  抽象类引用 猜测为一个封装的 MAP1\n" +
                    "@Nullable private ProviderProperties mProperties;   // 猜测为一个封装的 MAP2\n" +
                    "\n" +
                    "\n" +
                    "public void dumpLocked(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tpw.print(\"  \" + mName + \" provider\");    // 打印 provider的名称\n" +
                    "\tif (isMock()) {\n" +
                    "\t\tpw.print(\" [mock]\");\n" +
                    "\t}\n" +
                    "\tpw.println(\":\");\n" +
                    "\n" +
                    "\tpw.println(\"    useable=\" + mUseable);\n" +
                    "\tif (!mUseable) { //  如果不可用  mUseable = false时  打印 \n" +
                    "\t\tpw.println(\"    attached=\" + (mProvider != null));\n" +
                    "\t\tif (mIsManagedBySettings) {\n" +
                    "\t\t\tpw.println(\"    allowed=\" + mAllowed);\n" +
                    "\t\t}\n" +
                    "\t\tpw.println(\"    enabled=\" + mEnabled);\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpw.println(\"    properties=\" + mProperties);\n" +
                    "\n" +
                    "\tif (mProvider != null) {\n" +
                    "\t\tlong identity = Binder.clearCallingIdentity();\n" +
                    "\t\ttry {\n" +
                    "\t\t// AbstractLocationProvider mProvider 是抽象类的引用 指向 实现类\n" +
                    "\t\t// \"passive\"  =>  class PassiveProvider extends AbstractLocationProvider {}\n" +
                    "\t\t\tmProvider.dump(fd, pw, args);\n" +
                    "\t\t} finally {\n" +
                    "\t\t\tBinder.restoreCallingIdentity(identity);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "public class PassiveProvider extends AbstractLocationProvider {\n" +
                    "boolean mReportLocation; // 是否有报告位置信息\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\" report location=\" + mReportLocation);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "示例:  LocationManagerService =》ArrayList<LocationProvider> mProviders =》 Item【 LocationProvider 】.AbstractLocationProvider mProvider [ PassiveProvider ] \n" +
                    "  passive provider:   // 对应  class PassiveProvider extends AbstractLocationProvider { }\n" +
                    "    useable=true   //  LocationProvider 【Item】 .  boolean mUseable;  // 是否可用  combined state\n" +
                    "    properties=com.android.internal.location.ProviderProperties@28abd0d   // LocationProvider.ProviderProperties mProperties    在 ProviderProperties 没有实现 toString shit!\n" +
                    " report location=true   // PassiveProvider.boolean mReportLocation; 是否有报告位置 \n" +
                    "\n" +
                    "  \n" +
                    "*********************  LocationManagerService.LocationProvider End *********************";
            keyWordList.add(location_1_18);


            KeyWordItem location_1_19 = new KeyWordItem();
            location_1_19.keyword = "  gps provider:";
            location_1_19.explain=" 打印 gps provider 实现类 GnssLocationProvider  的一些信息   \n " +
                    "LocationManagerService =》ArrayList<LocationProvider> mProviders =》 Item【 LocationProvider 】.AbstractLocationProvider mProvider [ GnssLocationProvider ] \n" +
                    "   ";
            location_1_19.classNameForShuxing = " class GnssLocationProvider extends AbstractLocationProvider implements {} ";
            location_1_19.printLogUrl="";
            location_1_19.expain1="public class GnssLocationProvider extends AbstractLocationProvider implements InjectNtpTimeCallback,\n" +
                    "        GnssSatelliteBlacklistCallback {\n" +
                    "\n" +
                    "private boolean mStarted;  // 是否开始导航   由硬件导航\n" +
                    "\n" +
                    "long mStartedChangedElapsedRealtime;  // 开始导航时的时间戳 用于记录最新的更改，并在停止后警告正在进行的位置\n" +
                    "\n" +
                    "// requested frequency of fixes, in milliseconds\n" +
                    "private int mFixInterval = 1000;   //  请求修复位置频率 间隔时间  毫秒 \n" +
                    "\n" +
                    "// true if low power mode for the GNSS chipset is part of the latest request.\n" +
                    "private boolean mLowPowerMode = false;  // 是否 请求工作在低功耗模式 \n" +
                    "\t\n" +
                    "// True if gps should be disabled because of PowerManager controls\n" +
                    "private boolean mDisableGpsForPowerManager = false; // 是否可以由 电量管理类 去 禁用 GPS\n" +
                    "\t\n" +
                    "// capabilities reported through the top level IGnssCallback.hal\n" +
                    "private volatile int mTopHalCapabilities;  //  硬件功能的比特位? \n" +
                    "// The GPS_CAPABILITY_* flags must match Capabilities enum in IGnssCallback.hal\n" +
                    "final int GPS_CAPABILITY_SCHEDULING = 0x0000001; // 2^0 = 1  第0比特位   // 是否支持 循环调度 \n" +
                    "final int GPS_CAPABILITY_MSB = 0x0000002;  // 2^1 = 2         第1比特位  /** GPS supports MS-Based AGPS mode */  \n" +
                    "final int GPS_CAPABILITY_MSA = 0x0000004; // 2^2 = 4           第2比特位  /** GPS supports MS-Assisted AGPS mode */  \n" +
                    "final int GPS_CAPABILITY_SINGLE_SHOT = 0x0000008;  // 2^3 = 8       第3比特位  /** GPS supports single-shot fixes */  \n" +
                    "final int GPS_CAPABILITY_ON_DEMAND_TIME = 0x0000010; // 2^4 = 16 =0x10       第4比特位  /** GPS supports on demand time injection */  \n" +
                    "final int GPS_CAPABILITY_GEOFENCING = 0x0000020; // 2^5 = 32 =0x20   第5比特位  /** GPS supports Geofencing  */  \n" +
                    "final int GPS_CAPABILITY_MEASUREMENTS = 0x0000040;// 2^6 = 64 =0x40   第6比特位  /** GPS supports Measurements */  \n" +
                    "final int GPS_CAPABILITY_NAV_MESSAGES = 0x0000080; // 2^7 = 128 =0x80   第7比特位  /** GPS supports Navigation Messages */  \n" +
                    "final int GPS_CAPABILITY_LOW_POWER_MODE = 0x0000100;// 2^8 = 254 =0x100   第8比特位  /** GPS supports LOW_POWER_MODE */  \n" +
                    "final int GPS_CAPABILITY_SATELLITE_BLACKLIST = 0x0000200;// 2^9 = 512 =0x200   第9比特位  /** GPS supports SATELLITE BLACKLIST */  \n" +
                    "final int GPS_CAPABILITY_MEASUREMENT_CORRECTIONS = 0x0000400;// 2^10 = 1024 =0x400   第10比特位 /** GPS supports Measurements CORRECTIONS 位置检测更正 */  \n" +
                    "\t\n" +
                    "GnssMeasurementsProvider mGnssMeasurementsProvider;  // 位置测量?\n" +
                    "\n" +
                    "GnssNavigationMessageProvider mGnssNavigationMessageProvider;  //  导航测量?\n" +
                    "\n" +
                    "GnssMeasurementCorrectionsProvider mGnssMeasurementCorrectionsProvider; // 位置测量修正?\n" +
                    "\n" +
                    "private GnssMetrics mGnssMetrics;   // GNSS Metrics 常量? 事件 统计?  \n" +
                    "\n" +
                    " native String native_get_internal_state();  // 硬件状态?\n" +
                    " \n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        StringBuilder s = new StringBuilder();\n" +
                    "        s.append(\"  mStarted=\").append(mStarted).append(\"   (changed \");\n" +
                    "        TimeUtils.formatDuration(SystemClock.elapsedRealtime()\n" +
                    "                - mStartedChangedElapsedRealtime, s);\n" +
                    "        s.append(\" ago)\").append('\\n');\n" +
                    "        s.append(\"  mFixInterval=\").append(mFixInterval).append('\\n');\n" +
                    "        s.append(\"  mLowPowerMode=\").append(mLowPowerMode).append('\\n');\n" +
                    "        s.append(\"  mGnssMeasurementsProvider.isRegistered()=\")\n" +
                    "                .append(mGnssMeasurementsProvider.isRegistered()).append('\\n');\n" +
                    "        s.append(\"  mGnssNavigationMessageProvider.isRegistered()=\")\n" +
                    "                .append(mGnssNavigationMessageProvider.isRegistered()).append('\\n');\n" +
                    "        s.append(\"  mDisableGpsForPowerManager=\").append(mDisableGpsForPowerManager).append('\\n');\n" +
                    "        s.append(\"  mTopHalCapabilities=0x\").append(Integer.toHexString(mTopHalCapabilities));\n" +
                    "        s.append(\" ( \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_SCHEDULING)) s.append(\"SCHEDULING \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_MSB)) s.append(\"MSB \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_MSA)) s.append(\"MSA \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_SINGLE_SHOT)) s.append(\"SINGLE_SHOT \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_ON_DEMAND_TIME)) s.append(\"ON_DEMAND_TIME \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_GEOFENCING)) s.append(\"GEOFENCING \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_MEASUREMENTS)) s.append(\"MEASUREMENTS \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_NAV_MESSAGES)) s.append(\"NAV_MESSAGES \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_LOW_POWER_MODE)) s.append(\"LOW_POWER_MODE \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_SATELLITE_BLACKLIST)) s.append(\"SATELLITE_BLACKLIST \");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_MEASUREMENT_CORRECTIONS)) {\n" +
                    "            s.append(\"MEASUREMENT_CORRECTIONS \");\n" +
                    "        }\n" +
                    "        s.append(\")\\n\");\n" +
                    "        if (hasCapability(GPS_CAPABILITY_MEASUREMENT_CORRECTIONS)) {\n" +
                    "            s.append(\"  SubHal=MEASUREMENT_CORRECTIONS[\");\n" +
                    "            s.append(mGnssMeasurementCorrectionsProvider.toStringCapabilities());\n" +
                    "            s.append(\"]\\n\");\n" +
                    "        }\n" +
                    "        s.append(mGnssMetrics.dumpGnssMetricsAsText());\n" +
                    "        s.append(\"  native internal state: \").append(native_get_internal_state());\n" +
                    "        s.append(\"\\n\");\n" +
                    "        pw.append(s);\n" +
                    "    }" +
                    "" +
                    "\npublic class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 是 LocationManagerService.LocationProvider 这个 内部类 \n" +
                    " ArrayList<LocationProvider> mProviders = new ArrayList<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "for (LocationProvider provider : mProviders) {\n" +
                    "\tprovider.dumpLocked(fd, pw, args);   // 打印  LocationProvider 的 dump方法\n" +
                    "}       ||\n" +
                    "......  ||\n" +
                    "}       ||\n" +
                    "        ||\n" +
                    "\t\t\\/ 【 LocationProvider 】\n" +
                    "public void dumpLocked(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tpw.print(\"  \" + mName + \" provider\");    // 打印 provider的名称\n" +
                    "\tif (isMock()) {\n" +
                    "\t\tpw.print(\" [mock]\");\n" +
                    "\t}\n" +
                    "\tpw.println(\":\");\n" +
                    "\n" +
                    "\tpw.println(\"    useable=\" + mUseable);\n" +
                    "\tif (!mUseable) { //  如果不可用  mUseable = false时  打印 \n" +
                    "\t\tpw.println(\"    attached=\" + (mProvider != null));\n" +
                    "\t\tif (mIsManagedBySettings) {\n" +
                    "\t\t\tpw.println(\"    allowed=\" + mAllowed);\n" +
                    "\t\t}\n" +
                    "\t\tpw.println(\"    enabled=\" + mEnabled);\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpw.println(\"    properties=\" + mProperties);\n" +
                    "\n" +
                    "\tif (mProvider != null) {\n" +
                    "\t\tlong identity = Binder.clearCallingIdentity();\n" +
                    "\t\ttry {\n" +
                    "\t\t// AbstractLocationProvider mProvider 是抽象类的引用 指向 实现类\n" +
                    "\t\t// \"gps\"  =>  class GnssLocationProvider extends AbstractLocationProvider {}\n" +
                    "\t\t\tmProvider.dump(fd, pw, args);\n" +
                    "\t\t} finally {\n" +
                    "\t\t\tBinder.restoreCallingIdentity(identity);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\t\n" +
                    "}";
            keyWordList.add(location_1_19);


            KeyWordItem location_1_20 = new KeyWordItem();
            location_1_20.keyword = "  mTopHalCapabilities=";
            location_1_20.explain="GPS  硬件的功能的比特位  public class GnssLocationProvider extends AbstractLocationProvider implements InjectNtpTimeCallback,\n" +
                    "        GnssSatelliteBlacklistCallback {}  【 GnssLocationProvider. int mTopHalCapabilitiess 】 ";
            location_1_20.classNameForShuxing = " LocationManagerService =》ArrayList<LocationProvider> mProviders  中 的 LocationProvider 中 的 AbstractLocationProvider 抽象引用的实现类  GnssLocationProvider\n" +
                    "中的 int 比特功能位  int mTopHalCapabilities;  //  硬件功能的比特位?  ";
            location_1_20.printLogUrl="GnssLocationProvider.dump() ";
            location_1_20.expain1="public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "...............\n" +
                    "s.append(\"  mTopHalCapabilities=0x\").append(Integer.toHexString(mTopHalCapabilities));\n" +
                    "s.append(\" ( \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_SCHEDULING)) s.append(\"SCHEDULING \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_MSB)) s.append(\"MSB \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_MSA)) s.append(\"MSA \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_SINGLE_SHOT)) s.append(\"SINGLE_SHOT \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_ON_DEMAND_TIME)) s.append(\"ON_DEMAND_TIME \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_GEOFENCING)) s.append(\"GEOFENCING \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_MEASUREMENTS)) s.append(\"MEASUREMENTS \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_NAV_MESSAGES)) s.append(\"NAV_MESSAGES \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_LOW_POWER_MODE)) s.append(\"LOW_POWER_MODE \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_SATELLITE_BLACKLIST)) s.append(\"SATELLITE_BLACKLIST \");\n" +
                    "if (hasCapability(GPS_CAPABILITY_MEASUREMENT_CORRECTIONS)) {\n" +
                    "\ts.append(\"MEASUREMENT_CORRECTIONS \");\n" +
                    "}\n" +
                    "s.append(\")\\n\");\n" +
                    "..........\n" +
                    "}\n" +
                    "\n" +
                    "}";
            keyWordList.add(location_1_20);



            KeyWordItem location_1_21 = new KeyWordItem();
            location_1_21.keyword = "GNSS_KPI_START";
            location_1_21.explain="GNSS 的 一些统计报告的信息!   ";
            location_1_21.classNameForShuxing = " LocationManagerService =》ArrayList<LocationProvider> mProviders  中 的 LocationProvider 中 的 AbstractLocationProvider 抽象引用的实现类  GnssLocationProvider \n " +
                    " GnssLocationProvider.mGnssMetrics  == >    public class GnssMetrics {}  ";
            location_1_21.printLogUrl=" public class GnssLocationProvider extends AbstractLocationProvider implements InjectNtpTimeCallback,\n" +
                    "        GnssSatelliteBlacklistCallback {\n" +
                    "\t\t\n" +
                    "private GnssMetrics mGnssMetrics;   // GNSS Metrics 常量? 事件 统计?  \n" +
                    "\n" +
                    "\n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    ".......\n" +
                    "mGnssMetrics.dumpGnssMetricsAsText()\n" +
                    ".......\n" +
                    "}\n" +
                    "\n" +
                    "} ";
            location_1_21.expain1="********************** GnssStatus  End **********************\t\n" +
                    "\n" +
                    "    public static String constellationTypeToString(@ConstellationType int constellationType) {\n" +
                    "        switch (constellationType) {\n" +
                    "            case CONSTELLATION_UNKNOWN:   // int CONSTELLATION_UNKNOWN = 0;  Unknown constellation type. \n" +
                    "                return \"UNKNOWN\";\n" +
                    "            case CONSTELLATION_GPS:  // int CONSTELLATION_GPS = 1;     /** Constellation type constant for GPS. */\n" +
                    "                return \"GPS\";\n" +
                    "            case CONSTELLATION_SBAS:   //int CONSTELLATION_SBAS = 2;  /** Constellation type constant for SBAS. */\n" +
                    "                return \"SBAS\";\n" +
                    "            case CONSTELLATION_GLONASS:  // int CONSTELLATION_GLONASS = 3;  /** Constellation type constant for Glonass. */\n" +
                    "                return \"GLONASS\";\n" +
                    "            case CONSTELLATION_QZSS:  // int CONSTELLATION_QZSS = 4;   /** Constellation type constant for QZSS. */\n" +
                    "                return \"QZSS\";\n" +
                    "            case CONSTELLATION_BEIDOU:  // int CONSTELLATION_BEIDOU = 5;  /** 北斗卫星 Constellation type constant for Beidou. */\n" +
                    "                return \"BEIDOU\";\n" +
                    "            case CONSTELLATION_GALILEO:  // int CONSTELLATION_GALILEO = 6;\n" +
                    "                return \"GALILEO\";\n" +
                    "            case CONSTELLATION_IRNSS:  //  int CONSTELLATION_IRNSS = 7; \n" +
                    "                return \"IRNSS\";\n" +
                    "            default:\n" +
                    "                return Integer.toString(constellationType);\n" +
                    "        }\n" +
                    "//2=\"SBAS\"=星基增强系统 ===> 通过增强系统的辅助配合 GNSS 的使用，使 GNSS 的定位精度等导航性能进一步提升，以满足不同区域不同领域特殊的定位服务需求\n" +
                    "\n" +
                    "0=\"UNKNOWN\"  \n" +
                    "1=\"GPS\"=GPS-美国全球定位系统 = 全球定位系统(Global Positioning System，GPS)\n" +
                    "2=\"SBAS\"= SBAS（Satellite-Based Augmentation System），即星基增强系统 =美国WAAS(Wide Area Augmentation System)\n" +
                    "3=\"GLONASS\"=格洛纳斯-俄罗斯实现的国家导航系统 \n" +
                    "4=\"QZSS\"=准天顶卫星系统 Quasi-Zenith Satellite System；缩写：QZSS = 日本发展的国家定位系统 \n" +
                    "5=\"BEIDOU\"=中国北斗卫星导航系统（英文名称：BeiDou Navigation Satellite System，简称BDS）\n" +
                    "6=\"GALILEO\"= 欧盟实现的 伽利略卫星导航系统（Galileo satellite navigation system）\n" +
                    "7=\"IRNSS\" = 印度区域导航卫星系统（英语：Indian Regional Navigation Satellite System (IRNSS)、NAVIC）\n" +
                    "\n" +
                    "********************** GnssStatus  Begin **********************\t\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "********************** GnssMetrics.Statistics Begin **********************\n" +
                    "\n" +
                    "class Statistics {      /** 存储统计信息 Class for storing statistics */ \n" +
                    "\n" +
                    "// 数据   [key=0:value=10]  [key=1:value=14]  [key=2:value=5]  ....  \n" +
                    "int count;    //  统计次数  0,1,2,3,4.....\n" +
                    "double sum;   // 统计每次的增值  10,14,5.....\n" +
                    "double sumSquare;  //  10^2 + 14^2 + 5^2 ..... 统计每次  的 增益平方    更好的体现差异\n" +
                    "\n" +
                    "  \n" +
                    "    public void addItem(double item) {     /** Adds an item  添加一个项  */ \n" +
                    "      count++;\n" +
                    "      sum += item;\n" +
                    "      sumSquare += item * item;\n" +
                    "    }\n" +
                    "\n" +
                    "    public double getMean() {      /** 返回数据 value平均值, (10+14+5)/3 = 9.33  Returns mean */\n" +
                    "      return sum/count;\n" +
                    "    }\n" +
                    "\n" +
                    "    public double getStandardDeviation() {  /** Returns standard deviation */\n" +
                    "      double m = sum/count;   //  平均值\n" +
                    "      m = m * m;   // 平均值的平方\n" +
                    "      double v = sumSquare/count;   // 10^2 + 14^2 + 5^2 ..... 每个值的平方的和/ count  \n" +
                    "      if (v > m) {  // 平方偏差\n" +
                    "        return Math.sqrt(v - m);  // 根号下  去掉 平方    ，计算得到  统计意义上的 平方偏差\n" +
                    "      }\n" +
                    "      return 0;\n" +
                    "    }\n" +
                    "\n" +
                    "\t\n" +
                    "********************** Statistics End **********************\n" +
                    "\n" +
                    "********************** GnssMetrics Begin **********************\n" +
                    "\n" +
                    "public class GnssMetrics {\n" +
                    "\n" +
                    "String logStartInElapsedRealTime;   // 开始记录 GPS 位置信息的时间字符\n" +
                    "\n" +
                    "\n" +
                    "Statistics locationFailureStatistics;   /** 定位失败的统计 Location failure statistics */\n" +
                    "Statistics timeToFirstFixSecStatistics;   // 修复位置信息的 统计  /** Time to first fix statistics */ \n" +
                    "Statistics positionAccuracyMeterStatistics;  // 精确的位置数据统计   Position accuracy statistics\n" +
                    "Statistics topFourAverageCn0Statistics; // CN0 标识载噪比  GPS的灵敏度是以载噪比 CN0 来进行衡量的   /** Top 4 average CN0 statistics */\n" +
                    "// 载噪比是载波功率有效值/噪声功率有效值,肯定大一点好,国标规定C/N要大于或等于43db \n" +
                    "//  单从载噪比角度考虑，当然是愈大愈好。但是在有线电视系统的设计和调试中，往往是提高载噪比的措施会降低失真指标，\n" +
                    "// 反之亦然。所以得全面考虑，不能单求载噪比最大。\n" +
                    "\n" +
                    "boolean[] mConstellationTypes;   // boolen的数组 指示是否在位置修正中使用了 星座类型 constellation types\n" +
                    "\n" +
                    "\t\n" +
                    "\n" +
                    "  public String dumpGnssMetricsAsText() {    // Dumps GNSS Metrics as text   @return GNSS Metrics \n" +
                    "    StringBuilder s = new StringBuilder();\n" +
                    "    s.append(\"GNSS_KPI_START\").append('\\n');\n" +
                    "    s.append(\"  KPI logging start time: \").append(logStartInElapsedRealTime).append(\"\\n\");  // 起始时间\n" +
                    "    s.append(\"  KPI logging end time: \");  \n" +
                    "    TimeUtils.formatDuration(SystemClock.elapsedRealtimeNanos() / 1000000L, s);  // 当前系统时间   单位 :  秒\n" +
                    "    s.append(\"\\n\");\n" +
                    "    s.append(\"  Number of location reports: \").append(\n" +
                    "        locationFailureStatistics.getCount()).append(\"\\n\");  // 定位失败的统计次数\n" +
                    "    if (locationFailureStatistics.getCount() > 0) {\n" +
                    "      s.append(\"  Percentage location failure: \").append(\n" +
                    "          100.0 * locationFailureStatistics.getMean()).append(\"\\n\");  // 打印  Statistics.getMean() value-sum/value-count\n" +
                    "    }\n" +
                    "    s.append(\"  Number of TTFF reports: \").append(\n" +
                    "\t\n" +
                    "\t// ttff的意思是Time to First Fix，就是到了一个完全陌生的地方，GPS里面没有存任何信息，\n" +
                    "\t// 时间也不对的情况下，从开机到得到坐标信息和时间信息的时间。(也叫冷启动时间）\n" +
                    "\t// 这个理想值是18秒。一般现代的GPS都可以在30秒定位。 \n" +
                    "        timeToFirstFixSecStatistics.getCount()).append(\"\\n\");  // 冷启动报告统计次数 ,TTFF 报告的次数    ttff的意思是 Time to First Fix,\n" +
                    "    if (timeToFirstFixSecStatistics.getCount() > 0) {\n" +
                    "\t//  冷启动报告统计的平均时间 \n" +
                    "      s.append(\"  TTFF mean (sec): \").append(timeToFirstFixSecStatistics.getMean()).append(\"\\n\");\n" +
                    "      s.append(\"  TTFF standard deviation (sec): \").append(\n" +
                    "          timeToFirstFixSecStatistics.getStandardDeviation()).append(\"\\n\");   // 统计意义上的 平方偏差 \n" +
                    "    }\n" +
                    "    s.append(\"  Number of position accuracy reports: \").append(\n" +
                    "        positionAccuracyMeterStatistics.getCount()).append(\"\\n\");  // 详细地址报告统计 个数\n" +
                    "    if (positionAccuracyMeterStatistics.getCount() > 0) {\n" +
                    "      s.append(\"  Position accuracy mean (m): \").append(  // 详细位置的平均值\n" +
                    "          positionAccuracyMeterStatistics.getMean()).append(\"\\n\");\n" +
                    "      s.append(\"  Position accuracy standard deviation (m): \").append(  //  详细位置的  统计意义上的 平方偏差 \n" +
                    "          positionAccuracyMeterStatistics.getStandardDeviation()).append(\"\\n\");\n" +
                    "    }\n" +
                    "    s.append(\"  Number of CN0 reports: \").append(\n" +
                    "        topFourAverageCn0Statistics.getCount()).append(\"\\n\");  //  CN0载噪比报告个数\n" +
                    "    if (topFourAverageCn0Statistics.getCount() > 0) {\n" +
                    "      s.append(\"  Top 4 Avg CN0 mean (dB-Hz): \").append( \n" +
                    "          topFourAverageCn0Statistics.getMean()).append(\"\\n\"); // CN0载噪比的平均值\n" +
                    "      s.append(\"  Top 4 Avg CN0 standard deviation (dB-Hz): \").append(\n" +
                    "          topFourAverageCn0Statistics.getStandardDeviation()).append(\"\\n\");  // CN0载噪比的 统计意义上的 平方偏差 \n" +
                    "    }\n" +
                    "\t\ts.append(\"  Used-in-fix constellation types: \");\n" +
                    "\t// boolean[] mConstellationTypes;   // boolen的数组 指示是否在位置修正中使用了 星座类型 constellation types\n" +
                    "\tfor (int i = 0; i < mConstellationTypes.length; i++) {\n" +
                    "\t\tif (mConstellationTypes[i]) {  \n" +
                    "\t\t// 如果对应index的序号值为 true, 那么打印 GnssStatus.constellationTypeToString(1,2,3,4....)\n" +
                    "\t\t\ts.append(GnssStatus.constellationTypeToString(i)).append(\" \");\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\ts.append(\"\\n\");\n" +
                    "s.append(\"GNSS_KPI_END\").append(\"\\n\");\n" +
                    ".........\n" +
                    "\n" +
                    "\n" +
                    "GNSS_KPI_START\n" +
                    "  KPI logging start time: +1m15s145ms    【GPS的Log开始记录的时间】\n" +
                    "  KPI logging end time: +4d23h24m53s220ms 【当前手机持续记录时间(手机开机为起点)】\n" +
                    "  Number of location reports: 0           【 locationFailureStatistics 定位失败报告的记录 】\n" +
                    "  Number of TTFF reports: 0      【 冷启动 定位报告的记录 】          \n" +
                    "  Number of position accuracy reports: 0   【 positionAccuracyMeterStatistics  精确定位报告的记录 】\n" +
                    "  Number of CN0 reports: 0      【 CN0载噪比报告记录个数 】\n" +
                    "  Used-in-fix constellation types: 【在用的 星座类型 】\n" +
                    "0=\"UNKNOWN\"  \n" +
                    "1=\"GPS\"=GPS-美国全球定位系统 = 全球定位系统(Global Positioning System，GPS)\n" +
                    "2=\"SBAS\"= SBAS（Satellite-Based Augmentation System），即星基增强系统 =美国WAAS(Wide Area Augmentation System)\n" +
                    "3=\"GLONASS\"=格洛纳斯-俄罗斯实现的国家导航系统 \n" +
                    "4=\"QZSS\"=准天顶卫星系统 Quasi-Zenith Satellite System；缩写：QZSS = 日本发展的国家定位系统 \n" +
                    "5=\"BEIDOU\"=中国北斗卫星导航系统（英文名称：BeiDou Navigation Satellite System，简称BDS）\n" +
                    "6=\"GALILEO\"= 欧盟实现的 伽利略卫星导航系统（Galileo satellite navigation system）\n" +
                    "7=\"IRNSS\" = 印度区域导航卫星系统（英语：Indian Regional Navigation Satellite System (IRNSS)、NAVIC）\n" +
                    "\n" +
                    "********************** GnssMetrics END **********************";
            keyWordList.add(location_1_21);



            KeyWordItem location_1_22 = new KeyWordItem();
            location_1_22.keyword = "Power Metrics";
            location_1_22.explain=" GPS 电量使用情况  ";
            location_1_22.classNameForShuxing = " LocationManagerService =》ArrayList<LocationProvider> mProviders  中 的 LocationProvider 中 的 AbstractLocationProvider 抽象引用的实现类  GnssLocationProvider \n" +
                    "  GnssLocationProvider.mGnssMetrics  == >    public class GnssMetrics {} => GpsBatteryStats ";
            location_1_22.printLogUrl="";
            location_1_22.expain1="********************** GpsBatteryStats Begin **********************\n" +
                    "/**\n" +
                    " * API for GPS power stats\n" +
                    " */\n" +
                    "public final class GpsBatteryStats implements Parcelable {\n" +
                    "\n" +
                    "  private long mLoggingDurationMs;  // LOG 持续时间 \n" +
                    "  private long mEnergyConsumedMaMs;  // 耗能时间\n" +
                    "  // 记录 GPS 信号   long[0] = goodstate高信号=0的持续时间   以及 long[1] = poorstate=1低信号的持续时间   以及\n" +
                    "  private long[]  mTimeInGpsSignalQualityLevel = new long[GnssMetrics.NUM_GPS_SIGNAL_QUALITY_LEVELS【2】];\n" +
                    "\n" +
                    "\n" +
                    "  public long[] getTimeInGpsSignalQualityLevel() {  // 不懂  \n" +
                    "    return mTimeInGpsSignalQualityLevel;\n" +
                    "  }\n" +
                    "  \n" +
                    "********************** GpsBatteryStats End **********************\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "********************** GnssMetrics.GnssPowerMetrics Begin **********************\n" +
                    "\n" +
                    "  private class GnssPowerMetrics {   //用于记录GPS的 耗电信息 /* Class for handling GNSS power related metrics */\n" +
                    "\n" +
                    "// 阈值   前四个CN0 载噪比的 平均数 如果 低于这个值  说明 GPS 信号低  \n" +
                    "double POOR_TOP_FOUR_AVG_CN0_THRESHOLD_DB_HZ = 20.0;    /* Threshold for Top Four Average CN0 below which GNSS signal quality is declared poor */\n" +
                    "// 当 CN0 载噪比 变化 1 个 dbHz的话  那么就需要进行记录 的阈值\n" +
                    "final double REPORTING_THRESHOLD_DB_HZ = 1.0;     /* Minimum change in Top Four Average CN0 needed to trigger a report */\n" +
                    "\n" +
                    " \n" +
                    "IBatteryStats mBatteryStats; // 电源API调用   /* BatteryStats API */\n" +
                    "\n" +
                    "mLastAverageCn0;  // 最新的前4个 SN0载噪比 的平均值    /* Last reported Top Four Average CN0 */\n" +
                    "// 当前 载噪比 对应 GPS 信号数值 \n" +
                    "int mLastSignalLevel; /* Last reported signal quality bin (based on Top Four Average CN0) */\n" +
                    "//【 -1=GPS_SIGNAL_QUALITY_UNKNOWN=GPS信号未知   0=GPS_SIGNAL_QUALITY_POOR=GPS信号低 1=GPS_SIGNAL_QUALITY_GOOD=GPS信号高】\n" +
                    "\n" +
                    "  }\n" +
                    "\n" +
                    "  \n" +
                    "********************** GnssMetrics.GnssPowerMetrics END **********************\n" +
                    "\n" +
                    "\n" +
                    "********************** GnssMetrics END **********************\n" +
                    "\t\n" +
                    "public class GnssMetrics {\n" +
                    "GnssPowerMetrics mGnssPowerMetrics;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  public String dumpGnssMetricsAsText() {  \n" +
                    "  ...........\n" +
                    "    GpsBatteryStats stats = mGnssPowerMetrics.getGpsBatteryStats();  【 GpsBatteryStats.java 】\n" +
                    "    if (stats != null) {\n" +
                    "      s.append(\"Power Metrics\").append(\"\\n\");\n" +
                    "      s.append(\"  Time on battery (min): \"\n" +
                    "          + stats.getLoggingDurationMs() / ((double) DateUtils.MINUTE_IN_MILLIS)).append(\"\\n\");\n" +
                    "      long[] t = stats.getTimeInGpsSignalQualityLevel();  // 获取只有2个size的 long[] 数值 0:goodstate 1:poorstate\n" +
                    "      if (t != null && t.length == NUM_GPS_SIGNAL_QUALITY_LEVELS【2】) {  // GPS 好信号的时间长度 \n" +
                    "        s.append(\"  Amount of time (while on battery) Top 4 Avg CN0 > \" +\n" +
                    "            Double.toString(GnssPowerMetrics.POOR_TOP_FOUR_AVG_CN0_THRESHOLD_DB_HZ) + \n" +
                    "//POOR_TOP_FOUR_AVG_CN0_THRESHOLD_DB_HZ = 20  是阈值   前四个CN0 载噪比的 平均数 如果 低于这个值说明 GPS 信号低  \n" +
                    "            \" dB-Hz (min): \").append(t[1] / ((double) DateUtils.MINUTE_IN_MILLIS)).append(\"\\n\");\n" +
                    "        s.append(\"  Amount of time (while on battery) Top 4 Avg CN0 <= \" +   // GPS 差 信号的时间长度 \n" +
                    "            Double.toString(GnssPowerMetrics.POOR_TOP_FOUR_AVG_CN0_THRESHOLD_DB_HZ) +\n" +
                    "            \" dB-Hz (min): \").append(t[0] / ((double) DateUtils.MINUTE_IN_MILLIS)).append(\"\\n\");\n" +
                    "      }\n" +
                    "      s.append(\"  Energy consumed while on battery (mAh): \").append(  //  耗能时间 \n" +
                    "          stats.getEnergyConsumedMaMs() / ((double) DateUtils.HOUR_IN_MILLIS)).append(\"\\n\");\n" +
                    "    }    // 打印 硬件版本号 \n" +
                    "    s.append(\"Hardware Version: \" + SystemProperties.get(\"ro.boot.revision\", \"\")).append(\"\\n\");\n" +
                    "    return s.toString();\n" +
                    "  }\n" +
                    "  ...........\n" +
                    "}\n" +
                    "\n" +
                    "********************** GnssMetrics END **********************\n" +
                    "Log示例:\n" +
                    "Power Metrics\n" +
                    "  Time on battery (min): 0.027016666666666668    【 日志持续时间 】\n" +
                    "  Amount of time (while on battery) Top 4 Avg CN0 > 20.0 dB-Hz (min): 0.0  【 好信号的时间 】\n" +
                    "  Amount of time (while on battery) Top 4 Avg CN0 <= 20.0 dB-Hz (min): 0.0  【 差信号的时间 】\n" +
                    "  Energy consumed while on battery (mAh): 0.0   【 耗能时间 】\n" +
                    "Hardware Version: dvt2   【 与GPS有关的版本号信息 SystemProperties.get(\"ro.boot.revision\", \"\") 】";
            keyWordList.add(location_1_22);


            KeyWordItem location_1_23 = new KeyWordItem();
            location_1_23.keyword = "  native internal state:";
            location_1_23.explain="从 native 传递过来的一些信息   【GnssLocationProvider.java】 native String native_get_internal_state();    ";
            location_1_23.classNameForShuxing = " LocationManagerService =》ArrayList<LocationProvider> mProviders  中 的 LocationProvider 中 \n" +
                    "的 AbstractLocationProvider 抽象引用的实现类  GnssLocationProvider \n" +
                    "  GnssLocationProvider 的native方法  【 native String native_get_internal_state() 】";
            location_1_23.printLogUrl="public class GnssLocationProvider extends AbstractLocationProvider implements InjectNtpTimeCallback,\n" +
                    "        GnssSatelliteBlacklistCallback {\n" +
                    "\t\t\n" +
                    " native String native_get_internal_state();  // 硬件状态?\n" +
                    " \n" +
                    "public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "{\n" +
                    "......\n" +
                    "s.append(\"  native internal state: \").append(native_get_internal_state());\n" +
                    "......\n" +
                    "}\n" +
                    "}";
            location_1_23.expain1="************* jni/com_android_server_location_GnssLocationProvider.cpp  Begin  *************\n" +
                    "/frameworks/base/services/core/jni/com_android_server_location_GnssLocationProvider.cpp\n" +
                    "static const JNINativeMethod sMethods[] = {\n" +
                    " {\"native_get_internal_state\", \"()Ljava/lang/String;\", reinterpret_cast<void *>(\n" +
                    "            android_location_GnssLocationProvider_get_internal_state)},\n" +
                    "}\n" +
                    "\n" +
                    "sp<IGnssDebug_V1_0> gnssDebugIface = nullptr;   // gnss_hal_v1 智能指针 \n" +
                    "sp<IGnssDebug_V2_0> gnssDebugIface_V2_0 = nullptr;  // gnss_hal_v2 智能指针 \n" +
                    "\n" +
                    "// 把 Gnss 的一些 Debug 信息 输出\n" +
                    "static jstring android_location_GnssLocationProvider_get_internal_state(JNIEnv* env, jobject /* obj */) {\n" +
                    "    jstring result = nullptr;\n" +
                    "\n" +
                    "    std::stringstream internalState;\n" +
                    "\n" +
                    "    if (gnssDebugIface == nullptr) {\n" +
                    "        internalState << \"Gnss Debug Interface not available\"  << std::endl;\n" +
                    "    } else if (gnssDebugIface_V2_0 != nullptr) {\n" +
                    "        IGnssDebug_V2_0::DebugData data;\n" +
                    "\t\t// 调用  getDebugData_2_0  传入一个回调  , 把 回调的地址 赋值 给  IGnssDebug_V2_0::DebugData data;\n" +
                    "        gnssDebugIface_V2_0->getDebugData_2_0([&data](const IGnssDebug_V2_0::DebugData& debugData) {\n" +
                    "            data = debugData;\n" +
                    "        });\n" +
                    "\t\t// parseDebugData 把 C++ 层的 转为 Java 层的 字符串 \n" +
                    "        result = parseDebugData(env, internalState, data);\n" +
                    "    } else {\n" +
                    "        IGnssDebug_V1_0::DebugData data;\n" +
                    "        gnssDebugIface->getDebugData([&data](const IGnssDebug_V1_0::DebugData& debugData) {\n" +
                    "            data = debugData;\n" +
                    "        });\n" +
                    "        result = parseDebugData(env, internalState, data);  // parseDebugData 把 C++ 层的 转为 Java 层的 字符串 \n" +
                    "    }\n" +
                    "    return result;\n" +
                    "\t\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "template<class T>\n" +
                    "static jstring parseDebugData(JNIEnv* env, std::stringstream& internalState, const T& data) {\n" +
                    "    internalState << \"Gnss Location Data:: \";  // internalState 是一个输入流  可以看成是 StringBuilder\n" +
                    "    if (!data.position.valid) {  // 数据是否有效 \n" +
                    "        internalState << \"not valid\";   //  如果  数据无效 打印  not valid\n" +
                    "    } else {   //  如果  数据无效 打印  更加详细的内容 \n" +
                    "        internalState << \"LatitudeDegrees: \" << data.position.latitudeDegrees  // 维度数据\n" +
                    "                      << \", LongitudeDegrees: \" << data.position.longitudeDegrees  // 经度数据\n" +
                    "                      << \", altitudeMeters: \" << data.position.altitudeMeters  // 海拔数据\n" +
                    "                      << \", speedMetersPerSecond: \" << data.position.speedMetersPerSec // 速度数据 xxx米/s\n" +
                    "                      << \", bearingDegrees: \" << data.position.bearingDegrees  // 方位数据\n" +
                    "                      << \", horizontalAccuracyMeters: \"\n" +
                    "                      << data.position.horizontalAccuracyMeters   // 水平精度数据\n" +
                    "                      << \", verticalAccuracyMeters: \" << data.position.verticalAccuracyMeters  // 垂直精度数据\n" +
                    "                      << \", speedAccuracyMetersPerSecond: \"\n" +
                    "                      << data.position.speedAccuracyMetersPerSecond  // 速度精度数据\n" +
                    "                      << \", bearingAccuracyDegrees: \" << data.position.bearingAccuracyDegrees // 方位精度数据\n" +
                    "                      << \", ageSeconds: \" << data.position.ageSeconds;  // 数据的时间戳\n" +
                    "    }\n" +
                    "    internalState << std::endl;\n" +
                    "\n" +
                    "\t// GNSS 的 时间数据 \n" +
                    "    internalState << \"Gnss Time Data:: timeEstimate: \" << data.time.timeEstimate   //   GnssUtcTime timeEstimate; 估计时间? \n" +
                    "                  << \", timeUncertaintyNs: \" << data.time.timeUncertaintyNs   //  float timeUncertaintyNs;   68% error estimate in time  不确定时间? \n" +
                    "                  << \", frequencyUncertaintyNsPerSec: \"   //  以 纳秒为单位 的 本地漂移无法估计    68% error estimate in local clock drift\n" +
                    "                  << data.time.frequencyUncertaintyNsPerSec << std::endl;  //  float frequencyUncertaintyNsPerSec;\n" +
                    "\n" +
                    "    if (data.satelliteDataArray.size() != 0) {\n" +
                    "        internalState << \"Satellite Data for \" << data.satelliteDataArray.size()  // 卫星数据大小\n" +
                    "                      << \" satellites:: \" << std::endl;\n" +
                    "    }\n" +
                    "\n" +
                    "    internalState << \"constell: 1=GPS, 2=SBAS, 3=GLO, 4=QZSS, 5=BDS, 6=GAL, 7=IRNSS; \"\n" +
                    "                  << \"ephType: 0=Eph, 1=Alm, 2=Unk; \"\n" +
                    "                  << \"ephSource: 0=Demod, 1=Supl, 2=Server, 3=Unk; \"\n" +
                    "                  << \"ephHealth: 0=Good, 1=Bad, 2=Unk\" << std::endl;   // 卫星数据  表头 \n" +
                    "    for (size_t i = 0; i < data.satelliteDataArray.size(); i++) {\n" +
                    "        IGnssDebug_V1_0::SatelliteData satelliteData =\n" +
                    "                getSatelliteData(data.satelliteDataArray, i);\n" +
                    "        internalState << \"constell: \"   // 卫星的 类型  哪个国家的 \n" +
                    "                      << getConstellationType(data.satelliteDataArray, i)\n" +
                    "                      << \", svid: \" << std::setw(3) << satelliteData.svid  // 卫星的识别码 一一对应  Satellite vehicle ID number\n" +
                    "                      << \", serverPredAvail: \"\n" +
                    "                      << satelliteData.serverPredictionIsAvailable  // 卫星是否可用 True if a server has provided a predicted orbit and clock model\n" +
                    "                      << \", serverPredAgeSec: \" << std::setw(7)\n" +
                    "                      << satelliteData.serverPredictionAgeSeconds  // 该卫星的使用时间 单位秒\n" +
                    "                      << \", ephType: \"\n" +
                    "\t\t\t\t\t  \n" +
                    "\t/**\n" +
                    "\t* Defines the standard broadcast ephemeris or almanac availability for the satellite.  \n" +
                    "\t* To report status of predicted orbit and clock information, see the serverPrediction fields below.\n" +
                    "*\t   SatelliteEphemerisType ephemerisType; \n" +
                    "*\t enum SatelliteEphemerisType : uint8_t {\n" +
                    "*        EPHEMERIS,  /** 对于这颗卫星 已知它的导航电文  GPS卫星导航电文[ 星历=Ephemeris ] Ephemeris is known for this satellite. */\n" +
                    "*        ALMANAC_ONLY,   //  Ephemeris is not known, but GPS卫星导航历书[历书=Almanac] (approximate location) is known.\n" +
                    "*        NOT_AVAILABLE  //  Both ephemeris & almanac are not known (e.g. during a cold start blind search.)\n" +
                    "*    };\n" +
                    "*历书与星历都是表示卫星运行的参数。\n" +
                    "*历书包括全部卫星的大概位置，用于卫星预报；\n" +
                    "*星历只是当前接收机观测到的卫星的精确位置，用于定位。\n" +
                    "*历书是从导航电文中提取的，每12.5分钟的导航电文才能得到一组完整的历书。\n" +
                    "*http://www.voidcn.com/article/p-vqzwkcbm-bgh.html   [ 星历=Ephemeris ] 和  [历书=Almanac] 区别地址:    \n" +
                    "*历书作用:\n" +
                    "*【利用历书和当地的位置， 我们可以计算出卫星的方位和高度角，\n" +
                    "*由此可以计算出当地能观测到的卫星和持续时间，\n" +
                    "*即卫星高度角大于5°的出现时间。】\n" +
                    "*\t*/\n" +
                    "// 卫星数据类型 0=Eph(星历-用于定位), 1=Alm (历书 用于计算卫星位置), 2=Unk( 星历 历书 都不是的情况 );\n" +
                    "                      << static_cast<uint32_t>(satelliteData.ephemerisType)  \n" +
                    "\t\t\t\t\t  \n" +
                    "// enum SatelliteEphemerisSource : uint8_t {\n" +
                    "// \tDEMODULATED = 0 ,   // 数据来源于卫星  The ephemeris (or almanac only) information was demodulated from the  signal received on the device\n" +
                    "// \tSUPL_PROVIDED = 1,  // 数据来源于 SUPL server 定位服务器  The ephemeris (or almanac only) information was received from a SUPL server\n" +
                    "// \tOTHER_SERVER_PROVIDED = 2, // 数据来源于非SUPL的其他类型服务器 The ephemeris (or almanac only) information was provided by another server\n" +
                    "// \tOTHER = 3,   // 来自不可用数据类型的卫星的来源  The ephemeris (or almanac only) information was provided by another  method, e.g. injected via a local debug tool\n" +
                    "// };\n" +
                    "\t\t\t\t      << \", ephSource: \"\n" +
                    "                      << static_cast<uint32_t>(satelliteData.ephemerisSource)\n" +
                    "                      << \", ephHealth: \"\n" +
                    "                      << static_cast<uint32_t>(satelliteData.ephemerisHealth)  // 信号质量 0=较好的GPS信号  1=较差的GPS信号  2=没有GPS信号-没有收到信号\n" +
                    "                      << \", ephAgeSec: \" << std::setw(7)\n" +
                    "                      << satelliteData.ephemerisAgeSeconds << std::endl;  // 该卫星数据的剩余有效时间 \n" +
                    "    }\n" +
                    "    return (jstring) env->NewStringUTF(internalState.str().c_str());\n" +
                    "}\n" +
                    "************* jni/com_android_server_location_GnssLocationProvider.cpp  End  *************\n" +
                    "\n" +
                    "\t\n" +
                    "Log示例:\n" +
                    "  native internal state: Gnss Location Data:: not valid  【 not valid 标识当前无 GPS 有效位置信息 】\n" +
                    "  【 IGnssDebug.hal=>struct TimeDebug {} 时间相关信息  】\n" +
                    "Gnss Time Data:: timeEstimate: 1483228800000, timeUncertaintyNs: 999, frequencyUncertaintyNsPerSec: 200000  \n" +
                    "Satellite Data for 148 satellites::  【 (IGnssDebug_V2_0::DebugData)data.satelliteDataArray.size()  卫星数据集合】\n" +
                    "0=\"UNKNOWN\"  \n" +
                    "1=\"GPS\"=GPS-美国全球定位系统 = 全球定位系统(Global Positioning System，GPS)\n" +
                    "2=\"SBAS\"= SBAS（Satellite-Based Augmentation System），即星基增强系统 =美国WAAS(Wide Area Augmentation System)\n" +
                    "3=\"GLONASS\"=格洛纳斯-俄罗斯实现的国家导航系统 \n" +
                    "4=\"QZSS\"=准天顶卫星系统 Quasi-Zenith Satellite System；缩写：QZSS = 日本发展的国家定位系统 \n" +
                    "5=\"BEIDOU\"=中国北斗卫星导航系统（英文名称：BeiDou Navigation Satellite System，简称BDS）\n" +
                    "6=\"GALILEO\"= 欧盟实现的 伽利略卫星导航系统（Galileo satellite navigation system）\n" +
                    "7=\"IRNSS\" = 印度区域导航卫星系统（英语：Indian Regional Navigation Satellite System (IRNSS)、NAVIC）\n" +
                    "\n" +
                    "*历书与星历都是表示卫星运行的参数。\n" +
                    "*历书包括全部卫星的大概位置，用于卫星预报；\n" +
                    "*星历只是当前接收机观测到的卫星的精确位置，用于定位。\n" +
                    "*历书是从导航电文中提取的，每12.5分钟的导航电文才能得到一组完整的历书。\n" +
                    "*http://www.voidcn.com/article/p-vqzwkcbm-bgh.html   [ 星历=Ephemeris ] 和  [历书=Almanac] 区别地址:    \n" +
                    "*历书作用:\n" +
                    "*【利用历书和当地的位置， 我们可以计算出卫星的方位和高度角，\n" +
                    "*由此可以计算出当地能观测到的卫星和持续时间，\n" +
                    "*即卫星高度角大于5°的出现时间。】\n" +
                    "*\t*/\n" +
                    "\n" +
                    "// ephType 卫星数据类型 \n" +
                    "0=Eph(星历-用于定位), \n" +
                    "1=Alm (历书 用于计算卫星位置), \n" +
                    "2=Unk( 星历 历书 都不是的情况 );\n" +
                    "\n" +
                    "\n" +
                    "ephSource 卫星数据来源   \n" +
                    "0=Demod【设备接收到卫星的电报】, \n" +
                    "1=Supl【手机自身配置的Supl定位服务器】, \n" +
                    "2=Server【其他类型的服务器】\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "ephHealth 信号质量:\n" +
                    "0=较好的GPS信号  \n" +
                    "1=较差的GPS信号  \n" +
                    "2=没有GPS信号-没有收到信号\n" +
                    "ephAgeSec:  该电报 离 过期时间点 的剩余时间 \n" +
                    "\n" +
                    "svid : 卫星的编号   唯一确定  一一对应\n" +
                    "serverPredAvail:  0-不可用 1-可用 卫星是否可用 True if a server has provided a predicted orbit and clock model\n" +
                    "serverPredAgeSec:  该卫星的使用时间 单位秒 (卫星一直的飞 所以有一个可见 可用的时间窗口 )\n" +
                    "\n" +
                    "LOG示例:\n" +
                    "constell: 1=GPS, 2=SBAS, 3=GLO, 4=QZSS, 5=BDS, 6=GAL, 7=IRNSS; ephType: 0=Eph, 1=Alm, 2=Unk; ephSource: 0=Demod, 1=Supl, 2=Server, 3=Unk; ephHealth: 0=Good, 1=Bad, 2=Unk\n" +
                    "constell: 1, svid:   1, serverPredAvail: 0, serverPredAgeSec:       0, ephType: 2, ephSource: 3, ephHealth: 2, ephAgeSec:       0\n" +
                    "constell: 1, svid:   2, serverPredAvail: 0, serverPredAgeSec:       0, ephType: 2, ephSource: 3, ephHealth: 2, ephAgeSec:       0\n";
            keyWordList.add(location_1_23);




            KeyWordItem location_1_24 = new KeyWordItem();
            location_1_24.keyword = "  network provider:";
            location_1_24.explain=" network 来提供定位服务的 provider  ";
            location_1_24.classNameForShuxing = " LocationManagerService =》ArrayList<LocationProvider> mProviders =》 Item【 LocationProvider 】.AbstractLocationProvider mProvider [ LocationProviderProxy ]  \n  ";
            location_1_24.printLogUrl="";
            location_1_24.expain1="*********************  LocationManagerService.LocationProvider Begin *********************\n" +
                    "private class LocationProvider implements AbstractLocationProvider.LocationProviderManager {\n" +
                    "\n" +
                    "final String mName;  // 这里是 \"network\"   provider的名称  (\"network\",\"gps\",\"passive\",\"fused\" )之一\n" +
                    "\n" +
                    "final boolean mIsManagedBySettings;  // 是否由 SettingAPK 管理 是否需要请求权限LOCATION_PROVIDERS_ALLOWED\n" +
                    "\n" +
                    "private boolean mUseable;  // 是否可用  combined state\n" +
                    "private boolean mAllowed;  // 是否得到了用户授权 点击确认 state of LOCATION_PROVIDERS_ALLOWED\n" +
                    "private boolean mEnabled;  //  是否使能 state of provider\n" +
                    "\n" +
                    "\n" +
                    "// remember to clear binder identity before invoking any provider operation\n" +
                    "// class PassiveProvider extends AbstractLocationProvider {}\n" +
                    "// class MockProvider extends AbstractLocationProvider {}\n" +
                    "// class LocationProviderProxy extends AbstractLocationProvider {}\n" +
                    "// class GnssLocationProvider extends AbstractLocationProvider implements {}\n" +
                    "\n" +
                    "@Nullable protected AbstractLocationProvider mProvider;   //  抽象类引用 猜测为一个封装的 MAP1\n" +
                    "@Nullable private ProviderProperties mProperties;   // 猜测为一个封装的 MAP2\n" +
                    "\n" +
                    "\n" +
                    "public void dumpLocked(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "\tpw.print(\"  \" + mName + \" provider\");    // 打印 provider的名称\n" +
                    "\tif (isMock()) {\n" +
                    "\t\tpw.print(\" [mock]\");\n" +
                    "\t}\n" +
                    "\tpw.println(\":\");\n" +
                    "\n" +
                    "\tpw.println(\"    useable=\" + mUseable);\n" +
                    "\tif (!mUseable) { //  如果不可用  mUseable = false时  打印 \n" +
                    "\t\tpw.println(\"    attached=\" + (mProvider != null));\n" +
                    "\t\tif (mIsManagedBySettings) {\n" +
                    "\t\t\tpw.println(\"    allowed=\" + mAllowed);\n" +
                    "\t\t}\n" +
                    "\t\tpw.println(\"    enabled=\" + mEnabled);\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpw.println(\"    properties=\" + mProperties);\n" +
                    "\n" +
                    "\tif (mProvider != null) {\n" +
                    "\t\tlong identity = Binder.clearCallingIdentity();\n" +
                    "\t\ttry {\n" +
                    "\t\t// AbstractLocationProvider mProvider 是抽象类的引用 指向 实现类\n" +
                    "\t\t// \"network\"  =>  class LocationProviderProxy extends AbstractLocationProvider {}\n" +
                    "\t\t\tmProvider.dump(fd, pw, args);\n" +
                    "\t\t} finally {\n" +
                    "\t\t\tBinder.restoreCallingIdentity(identity);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n" +
                    "\t\t\n" +
                    "}\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "*********************  LocationManagerService.LocationProvider End *********************\n" +
                    "\n" +
                    "public class LocationManagerService extends ILocationManager.Stub {\n" +
                    "\n" +
                    "// 是 LocationManagerService.LocationProvider 这个 内部类 \n" +
                    " ArrayList<LocationProvider> mProviders = new ArrayList<>();\n" +
                    "\n" +
                    "void  dump(){ \n" +
                    "......\n" +
                    "for (LocationProvider provider : mProviders) {\n" +
                    "// 打印  LocationProvider 实现类  NetworkLocationService.java 的 dumpLocked  方法\n" +
                    "\tprovider 【 NetworkLocationService 】.dumpLocked(fd, pw, args);   \n" +
                    "}\n" +
                    "......\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    " \n" +
                    "*********************   LocationProviderProxy Begin *********************  \n" +
                    "public class LocationProviderProxy extends AbstractLocationProvider {\n" +
                    "final ServiceWatcher mServiceWatcher;  // 提供 network 定位的服务  \n" +
                    "\n" +
                    "    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {\n" +
                    "        pw.println(\"    service=\" + mServiceWatcher);\n" +
                    "        synchronized (mProviderPackagesLock) {\n" +
                    "            if (mProviderPackages.size() > 1) {\n" +
                    "                pw.println(\"    additional packages=\" + mProviderPackages);\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\t\n" +
                    "\n" +
                    " class NetworkLocationService extends Service {}\n" +
                    " abstract class Service extends ContextWrapper implements ComponentCallbacks2 {} \n" +
                    " public class ContextWrapper extends Context {}\n" +
                    " public abstract class Context {}\n" +
                    " \n" +
                    "*********************   LocationProviderProxy End *********************  \n" +
                    "\n" +
                    " LOG示例: \n" +
                    "network provider:\n" +
                    "  useable=true  【是否可用】\n" +
                    "  properties=com.android.internal.location.ProviderProperties@b772d3    【一个map数据 未打印 】 \n" +
                    "  【LocationProvider(\"network\") => AbstractLocationProvider mProvider】== class LocationProviderProxy extends AbstractLocationProvider\n" +
                    "  【 LocationProviderProxy. ServiceWatcher mServiceWatcher = com.qualcomm.location.izatprovider.NetworkLocationService  】\n" +
                    "  service={com.qualcomm.location/com.qualcomm.location.izatprovider.NetworkLocationService}@1\n" +
                    "\n" +
                    "  fused provider:\n" +
                    "    useable=true 【模糊数据提供者 是否可用】\n" +
                    "    properties=com.android.internal.location.ProviderProperties@54a5e10 【一个map数据 未打印 】 \n" +
                    "   【LocationProvider(\"fused\") => AbstractLocationProvider mProvider】== class MockProvider extends AbstractLocationProvider {}\n" +
                    "   【 MockProvider. ServiceWatcher mServiceWatcher = com.android.location.fused.FusedLocationService  】\n" +
                    "    service={com.android.location.fused/com.android.location.fused.FusedLocationService}@0";
            keyWordList.add(location_1_24);



//            KeyWordItem location_1_25 = new KeyWordItem();
//            location_1_25.keyword = "Current user:";
//            location_1_25.explain="当前用户类型记录  0-系统用户  ";
//            location_1_25.classNameForShuxing = " ";
//            location_1_25.printLogUrl="";
//            location_1_25.expain1="";
//            keyWordList.add(location_1_25);


/*            KeyWordItem location_1_3 = new KeyWordItem();
            location_1_3.keyword = "Current user:";
            location_1_3.explain="当前用户类型记录  0-系统用户  ";
            location_1_3.classNameForShuxing = " ";
            location_1_3.printLogUrl="";
            location_1_3.expain1="";
            keyWordList.add(location_1_3); */


            return keyWordList;
        }
    }


    class Android11Analysis extends AndroidAnalysisBase {

        public Android11Analysis(String version, String vendor  ) {
            super(version, vendor);
        }

    }









    static boolean InitFileAndGetAndroidVersion(){
        boolean flag = false ;
        if(!F8DirFile.exists()){
            System.out.println("当前保存 dump文件的目录(不存在):"+F8DirFile.getAbsolutePath() );
            return false;
        }

        File[] dumpFileList = F8DirFile.listFiles();

        for (int i = 0; i < dumpFileList.length; i++) {
            allDumpMap.put(dumpFileList[i].getName(),dumpFileList[i]);
            AllDumpFileList.add(dumpFileList[i]);
        }


        File propFile =   getFileWithName(getpropFileName);
        if(!propFile.exists()){
            System.out.println("保存版本信息的 getprop.txt文件不存在!  路径:" +F8DirPathStr+File.separator+getpropFileName);
            return false;
        }

        mAndroidVersion =   getSystemVersionFromFile(propFile);

        if(mAndroidVersion == null || "".equals(mAndroidVersion.trim())){
            System.out.println("读取到的 getprop.txt 文件中  配置项 ro.build.version.release 为空!  程序执行结束！" );
            return false;
        }
        String propContent = ReadFileContent(propFile);
        if(propContent != null){
            if(propContent.contains(".mtk") || propContent.contains("mtk.")){
                mVerndor = "MTK";
            } else if(propContent.contains(".qcom") || propContent.contains("qcom.")){
                mVerndor = "Qcom";
            }else{
                mVerndor = "Other(huawei-hisi)";
            }
        }
        flag = true;
        System.out.println("版本:"+ mAndroidVersion + "   产商:"+mVerndor);

        return flag;

    }


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i)+"\n");
        }
        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(sb.toString());
                curBW.flush();
                curBW.close();
                //   System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static String ReadFileContent( File mFilePath) {

        if (mFilePath != null  && mFilePath.exists()) {
            System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }
        StringBuilder sb= new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine+"\n");
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


    public static ArrayList<String> ReadFileRawContent( File mFilePath) {
        ArrayList<String> rawContent = new ArrayList<String>();
        if (mFilePath != null  && mFilePath.exists()) {
            System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            System.out.println("不存在 当前文件 "+ mFilePath.getAbsolutePath() );

            return null;
        }

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null ) {
                    continue;
                }

                rawContent.add(oldOneLine);
//                    System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawContent;

    }


    static String getSystemVersionFromFile(File versionFile) {
        String versionTag = "";

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(versionFile)));
            String lineContent = "";

            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                String content = lineContent.trim();
                if (content.contains("ro.build.version.release")) {  // 最后一个字母为数字
                    curBR.close();
                    content = content.replace("ro.build.version.release","");
                    content = content.replace("[","");
                    content = content.replace("]","");
                    content = content.replace(":","");
                    return content.trim();
                }
            }
            curBR.close();
        } catch (Exception e) {
            System.out.println(" AndroidVersion 读取 异常:" + e.fillInStackTrace());
            e.printStackTrace();
        }
        return versionTag;
    }

    static File getFileWithName(String fileName){
        return  allDumpMap.get(fileName);
    }


}